package com.lilithsthrone.game.dialogue.companions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.dominion.SMMilkingStall;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.85
 * @version 0.3.9.2
 * @author Innoxia
 */
public class SlaveDialogue {

	private static NPC slave;
	private static NPC characterForSex;
	private static NPC characterForSexSecondary;
	
	private static List<NPC> charactersPresent;
	
	private static boolean initFromCharactersPresent;
	private static boolean dollStatueInterrupted;
	
	public static void initDialogue(NPC targetedSlave, boolean initFromCharactersPresent) {
		CompanionManagement.initManagement(SLAVE_START, 2, targetedSlave);
		slave = targetedSlave;
		characterForSex = targetedSlave;

		characterForSexSecondary = null;
		charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
		charactersPresent.removeIf((npc) -> !Main.game.getPlayer().getCompanions().contains(npc) && (!npc.isSlave() || !npc.getOwner().isPlayer()) && !Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()));
		if(charactersPresent.size()>1) {
			if(charactersPresent.contains(Main.game.getPlayer().getMainCompanion()) && !getSlave().equals(Main.game.getPlayer().getMainCompanion())) {
				characterForSexSecondary = (NPC) Main.game.getPlayer().getMainCompanion();
				
			} else {
				characterForSexSecondary = charactersPresent.stream().filter((npc) -> !npc.equals(getSlave())).findFirst().get();
			}
		}
		
		SlaveDialogue.initFromCharactersPresent = initFromCharactersPresent;
		dollStatueInterrupted = false;
	}
	
	private static DialogueNode getAfterSexDialogue() {
		if(initFromCharactersPresent) {
			return CharactersPresentDialogue.AFTER_SEX;
		} else {
			return AFTER_SEX;
		}
	}
	
	private static NPC getSlave() {
		return slave;
	}
	
	private static boolean isDoll() {
		return getSlave().isDoll();
	}
	
	private static boolean isDollStatue() {
		return getSlave().isDollStatue();
	}
	
	private static ResponseTag getDollSexTag() {
		if(isDollStatue()) {
			if(getSlave().hasSlaveJobSetting(SlaveJob.DOLL_STATUE, SlaveJobSetting.DOLL_STATUE_ALL_FOURS)) {
				return ResponseTag.PREFER_DOGGY;
				
			} else if(getSlave().hasSlaveJobSetting(SlaveJob.DOLL_STATUE, SlaveJobSetting.DOLL_STATUE_MISSIONARY) || getSlave().hasSlaveJobSetting(SlaveJob.DOLL_STATUE, SlaveJobSetting.DOLL_STATUE_BRIDGE)) {
				return ResponseTag.PREFER_MISSIONARY;
				
			} else if(getSlave().hasSlaveJobSetting(SlaveJob.DOLL_STATUE, SlaveJobSetting.DOLL_STATUE_SQUATTING)) {
				return ResponseTag.PREFER_ORAL;
			}
		}
		return null;
	}
	
	public static boolean isDollStatueInterrupted() {
		return dollStatueInterrupted;
	}
	
	private static SlaveJob getCurrentJob() {
		return getSlave().getSlaveJob(Main.game.getHourOfDay());
	}

	public static String getTextFilePath() {
		if(getSlave().isRelatedTo(Main.game.getPlayer())) {
			return "characters/offspring/slave";
		} else {
			return "misc/slaveDialogue";
		}
	}

	private static String getThreesomeTextFilePath() {
		if(characterForSex.isRelatedTo(Main.game.getPlayer()) || (characterForSexSecondary!=null && characterForSexSecondary.isRelatedTo(Main.game.getPlayer()))) {
			return "characters/offspring/slave";
		} else {
			return "misc/slaveDialogue";
		}
	}
	
	private static void applyReactionReset() {
		if(getSlave().isVisiblyPregnant()){
			getSlave().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(getSlave(), true);
		}
	}

	private static boolean isCompanionSexPublic() {
		return Main.game.getPlayer().getLocationPlace().isPopulated()
				&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_SEATING_AREA)
				&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS);
	}
	
	private static GameCharacter enslavementTarget;
	private static DialogueNode followupEnslavementDialogue;

	public static GameCharacter getEnslavementTarget() {
		return enslavementTarget;
	}

	public static void setEnslavementTarget(GameCharacter enslavementTarget) {
		SlaveDialogue.enslavementTarget = enslavementTarget;
	}
	
	public static DialogueNode getFollowupEnslavementDialogue() {
		return followupEnslavementDialogue;
	}

	public static void setFollowupEnslavementDialogue(DialogueNode followupEnslavementDialogue) {
		SlaveDialogue.followupEnslavementDialogue = followupEnslavementDialogue;
	}

	private static SMGeneric getGenericSlaveSexManager(
			List<GameCharacter> dominantParticipants,
			List<GameCharacter> submissiveParticipants,
			List<ResponseTag> tags,
			boolean forcePlayerFullControl) {
		return getGenericSlaveSexManager(dominantParticipants,
						submissiveParticipants,
						getDominantSpectators(),
						getSubmissiveSpectators(),
						tags,
						forcePlayerFullControl,
						new HashMap<>());
	}
	
	private static SMGeneric getGenericSlaveSexManager(
			List<GameCharacter> dominantParticipants,
			List<GameCharacter> submissiveParticipants,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			List<ResponseTag> tags,
			boolean forcePlayerFullControl,
			Map<GameCharacter, SexPace> startingSexPaces) {
		return new SMGeneric(
				dominantParticipants,
				submissiveParticipants,
				dominantSpectators,
				submissiveSpectators,
				tags) {
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(startingSexPaces!=null && startingSexPaces.containsKey(character)) {
					return startingSexPaces.get(character);
				}
				return super.getStartingSexPaceModifier(character);
			}
			@Override
			public boolean isPublicSex() {
				return isCompanionSexPublic();
			}
			@Override
			public SexControl getSexControl(GameCharacter participant) {
				if(participant.isPlayer() && forcePlayerFullControl) {
					return SexControl.FULL;
				}
				return super.getSexControl(participant);
			}
			@Override
			public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
				ImmobilisationType immobilisationType = null;
				for(AbstractPlaceUpgrade upgrade : Main.game.getPlayer().getLocationPlace().getPlaceUpgrades()) {
					immobilisationType = upgrade.getImmobilisationType();
					if(immobilisationType!=null) {
						break;
					}
				}
				
				Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
				map.put(immobilisationType, new HashMap<>());
				map.get(immobilisationType).put(dominantParticipants.get(0), new HashSet<>());
				if(immobilisationType!=null) {
					for(GameCharacter character : submissiveParticipants) {
						if(character.isSlave() && !character.isDollStatue()) {
							map.get(immobilisationType).get(dominantParticipants.get(0)).add(character);
						}
					}
				}
				
				map.put(ImmobilisationType.COMMAND, new HashMap<>());
				map.get(ImmobilisationType.COMMAND).put(dominantParticipants.get(0), new HashSet<>());
				for(GameCharacter character : submissiveParticipants) {
					if(character.isDollStatue()) {
						map.get(ImmobilisationType.COMMAND).get(dominantParticipants.get(0)).add(character);
					}
				}

				map.put(ImmobilisationType.SLEEP, new HashMap<>());
				map.get(ImmobilisationType.SLEEP).put(dominantParticipants.get(0), new HashSet<>());
				for(GameCharacter character : submissiveParticipants) {
					if(character.isAsleep()) {
						map.get(ImmobilisationType.SLEEP).get(dominantParticipants.get(0)).add(character);
					}
				}
				
				return map;
			}
		};
	}
	
	//TODO delete -> was moved into Sex init in 0.4.9.5
//	private static void applyImmobilisationText(List<GameCharacter> submissiveParticipants) {
//		// Immobilisation from room upgrades:
//		ImmobilisationType immobilisationType = null;
//		for(AbstractPlaceUpgrade upgrade : Main.game.getPlayer().getLocationPlace().getPlaceUpgrades()) {
//			immobilisationType = upgrade.getImmobilisationType();
//			if(immobilisationType!=null) {
//				break;
//			}
//		}
//		if(immobilisationType!=null) {
//			List<GameCharacter> slaveSubs = new ArrayList<>(submissiveParticipants);
//			slaveSubs.removeIf(s->!s.isSlave() || s.isDollStatue());
//			if(!slaveSubs.isEmpty()) {
//				List<String> names = new ArrayList<>();
//				for(GameCharacter slave : slaveSubs) {
//					names.add(UtilText.parse(slave, "[npc.name]"));
//				}
//				StringBuilder sb = new StringBuilder();
//				sb.append("<p style='text-align:center;'>[style.italicsTerrible(");
//					switch(immobilisationType) {
//						case CHAINS:
//							sb.append("Thanks to the chains which have been added to this cell, ");
//							break;
//						case ROPE:
//							sb.append("Thanks to the ropes which have been added to this cell, ");
//							break;
//						case COCOON:
//						case TAIL_CONSTRICTION:
//						case TENTACLE_RESTRICTION:
//						case WITCH_SEAL:
//						case COMMAND:
//						case SLEEP:
//							break;
//					}
//					sb.append(Util.stringsToStringList(names, false));
//					if(names.size()>1) {
//						sb.append(" are");
//					} else {
//						sb.append(" is");
//					}
//					sb.append(" bound and unable to move!");
//				sb.append(")]</p>");
//				
//				Main.game.appendToTextEndStringBuilder(sb.toString());
//			}
//		}
//
//		// Doll:
//		List<GameCharacter> slaveSubs = new ArrayList<>(submissiveParticipants);
//		slaveSubs.removeIf(s->!s.isDollStatue());
//		if(!slaveSubs.isEmpty()) {
//			List<String> names = new ArrayList<>();
//			for(GameCharacter slave : slaveSubs) {
//				names.add(UtilText.parse(slave, "[npc.name]"));
//			}
//			StringBuilder sb = new StringBuilder();
//			sb.append("<p style='text-align:center;'>[style.italicsTerrible(");
//				sb.append("Having been ordered to do so, ");
//				sb.append(Util.stringsToStringList(names, false));
//				if(names.size()>1) {
//					sb.append(" are");
//				} else {
//					sb.append(" is");
//				}
//				sb.append(" remaining frozen in place!");
//			sb.append(")]</p>");
//			
//			Main.game.appendToTextEndStringBuilder(sb.toString());
//		}
//		
//		// Sleeping:
//		slaveSubs = new ArrayList<>(submissiveParticipants);
//		slaveSubs.removeIf(s->!s.isAsleep());
//		if(!slaveSubs.isEmpty()) {
//			List<String> names = new ArrayList<>();
//			for(GameCharacter slave : slaveSubs) {
//				names.add(UtilText.parse(slave, "[npc.name]"));
//			}
//			StringBuilder sb = new StringBuilder();
//			sb.append("<p style='text-align:center;'>[style.italicsTerrible(");
//				sb.append("As ");
//				if(names.size()>1) {
//					sb.append(" they are deep sleepers, ");
//				} else {
//					sb.append(UtilText.parse(slaveSubs.get(0), "[npc.sheIs] a deep sleeper, "));
//				}
//				sb.append(Util.stringsToStringList(names, false));
//				if(names.size()>1) {
//					sb.append(" remain");
//				} else {
//					sb.append(" remains");
//				}
//				sb.append(" asleep!");
//			sb.append(")]</p>");
//			
//			Main.game.appendToTextEndStringBuilder(sb.toString());
//		}
//	}
	
	public static List<GameCharacter> getDominantSpectators() {
		return Main.game.getPlayer().getCompanions();
	}

	public static List<GameCharacter> getSubmissiveSpectators() {
		// Removed in 0.4.1.5, as this was allowing sex with random characters who were on the tile that shouldn't have been involved in sex
//		List<NPC> characters = Main.game.getCharactersPresent();
//		characters.removeAll(Main.game.getPlayer().getCompanions());
//		return new ArrayList<>(characters);
		
//		List<NPC> characters = Main.game.getCharactersPresent();
//		characters.removeAll(Main.game.getPlayer().getCompanions());
//		characters.removeIf(c ->
//			(!c.isSlave() || !c.getOwner().isPlayer()) // Character is not a slave or is a slave that doesn't belong to the player
//			&& (!Main.game.getPlayer().getFriendlyOccupants().contains(c.getId()) || !c.isAttractedTo(Main.game.getPlayer()))); // AND is not a friend or is not attracted to the player
		
		List<NPC> characters = new ArrayList<>();
		for(NPC character : Main.game.getCharactersPresent()) {
			if(((character.isSlave() && character.getOwner().isPlayer()) // Add if character is player's slave
					|| (Main.game.getPlayer().getFriendlyOccupants().contains(character.getId()) && character.isAttractedTo(Main.game.getPlayer()))) // OR character is friend who is attracted to player
				&& !character.isAsleep()) { // AND not asleep
				characters.add(character);
			}
		}
		return new ArrayList<>(characters);
	}
	
	private static boolean enslavementWorked = false;
	public static final DialogueNode DEFAULT_ENSLAVEMENT_DIALOGUE = new DialogueNode("新奴隶", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(!SlaveDialogue.getEnslavementTarget().isSlave() && SlaveDialogue.getEnslavementTarget().isAbleToBeEnslaved() && Main.game.getPlayer().isHasSlaverLicense()) {
				Main.game.getTextEndStringBuilder().append(enslavementTarget.applyEnslavementEffects(Main.game.getPlayer()));
				
			} else {
				Main.game.getTextEndStringBuilder().append(enslavementTarget.incrementAffection(Main.game.getPlayer(), -25));
			}
			
			// Generate content:
			GameCharacter target = enslavementTarget;
			AbstractClothing enslavementClothing = target.getEnslavementClothing();
			UtilText.addSpecialParsingString(enslavementClothing.getName(), true);
			UtilText.addSpecialParsingString(enslavementClothing.getClothingType().isPlural()?"它们":"它", false);
			String path = "characters/enslavement";
			if(target instanceof NPCOffspring) {
				path = "characters/offspring/enslavement";
			}
			
			if(!target.isSlave() && target.isAbleToBeEnslaved() && Main.game.getPlayer().isHasSlaverLicense()) {
				if(enslavementClothing.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar"))) {
					Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile(path, "ENSLAVEMENT_SUCCESS_COLLAR", target));
				} else {
					Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile(path, "ENSLAVEMENT_SUCCESS", target));
				}
				enslavementWorked = true;
				
				// Apply effects after content has been generated due to conditional checks:
				Main.game.getPlayer().addSlave((NPC) enslavementTarget);
				enslavementTarget.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
				
			} else {
				if(target.isSlave()) {
					Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile(path, "ENSLAVEMENT_FAIL_ALREADY_SLAVE", target));
					
				} else if(!target.isAbleToBeEnslaved()) {
					if(target.getSubspecies()==Subspecies.DEMON) {
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile(path, "ENSLAVEMENT_FAIL_NOT_WANTED_DEMON", target));
						
					} else {
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile(path, "ENSLAVEMENT_FAIL_NOT_WANTED", target));
					}
					
				} else {
					Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile(path, "ENSLAVEMENT_FAIL_NO_LICENSE", target));
				}
				enslavementWorked = false;
			}
			
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(enslavementWorked) {
					return new Response("继续",
							"继续你的旅程。",
							SlaveDialogue.getFollowupEnslavementDialogue()){
						@Override
						public void effects() {
//							Main.game.getPlayer().addSlave((NPC) enslavementTarget);
//							enslavementTarget.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
						}
						@Override
						public DialogueNode getNextDialogue(){
							return SlaveDialogue.getFollowupEnslavementDialogue();
						}
					};
					
				} else {
					return new Response("继续",
							UtilText.parse(SlaveDialogue.getEnslavementTarget(), "虽然不起效果，但你可没想放过[npc.name]！"),
							SlaveDialogue.getFollowupEnslavementDialogue());
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode FREEDOM_DIALOG = new DialogueNode("已释放奴隶", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(enslavementTarget.incrementAffection(Main.game.getPlayer(), -25));
		}
		@Override
		public String getContent() {
			GameCharacter target = enslavementTarget;
			AbstractClothing enslavementClothing = target.getEnslavementClothing();
			UtilText.addSpecialParsingString(enslavementClothing.getName(), true);
			UtilText.addSpecialParsingString(enslavementClothing.getClothingType().isPlural()?"它们":"它", false);
			return UtilText.parseFromXMLFile("characters/enslavement", "ENSLAVEMENT_FAIL_FREEDOM_CERTIFICATION", target);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续",
						UtilText.parse(SlaveDialogue.getEnslavementTarget(), "虽然不起效果，但你可没想放过[npc.name]！"),
						SlaveDialogue.getFollowupEnslavementDialogue());
			}
			return null;
		}
	};
	
	private static String getSlaveStartCoreContent() {
		StringBuilder sb = new StringBuilder();
		
		if(!isDollStatue()) {
			if(getSlave().isVisiblyPregnant()) {
				// Pregnant encounters:
				if(!getSlave().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
					sb.append("<p>");
						sb.append("当你看到[npc.race]时，很难不注意到[npc.sheIs]正挺着孕肚。");
						sb.append("[npc.She]和你对视时心神恍惚地#IF(npc.isTaur())示意着#ELSE轻抚着#ENDIF[npc.her]隆起的腹部，");
					
					GameCharacter father = getSlave().getPregnantLitter().getFather();
					
					if(father!=null && father.isPlayer()) {
						switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
							case DISLIKE:
								switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
									case DISOBEDIENT:
										sb.append("[npc.she]向你吐口水时甚至没有掩饰[npc.eyes]中的恨意，"
												+ "[npc.speech(呸，都是<i>你</i>。你让我怀孕了，所以我这段时间不能工作，该死的混蛋……)]");
										break;
									case NEUTRAL:
										sb.append("[npc.she]努力抑制[npc.eyes]中流露出的恨意，说道，"
												+ "[npc.speech(哦，嗨，[npc.pcName]。你让我怀孕了，所以我需要一些时间来休息。)]");
										break;
									case OBEDIENT:
										sb.append("[npc.she]呼唤着你并顺从地掩饰着[npc.her]眼中的恨意，"
												+ "[npc.speech(嗨，[npc.pcName]。我相信你看得出来，你让我怀孕了……)]");
										break;
								}
								break;
							case NEUTRAL:
								switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
									case DISOBEDIENT:
										sb.append("叹了口气，"
												+ "[npc.speech(嗨，[npc.pcName]。你让我怀孕了，所以我这段时间需要放松一下，好吗？)]");
										break;
									case NEUTRAL:
										sb.append("叹了口气，"
												+ "[npc.speech(嗨，[npc.pcName]。你让我怀孕了……)]");
										break;
									case OBEDIENT:
										sb.append("叹了口气，"
												+ "[npc.speech(嗨，[npc.pcName]。你让我怀孕了……所以我得做好照顾好孩子的准备！)]");
										break;
								}
								break;
							case LIKE:
								switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
									case DISOBEDIENT:
										sb.append("[npc.she]绽放出灿烂的笑容，高兴地喊道，"
												+ "[npc.speech([npc.PcName]！看！你让我怀孕了，是不是很棒？！我需要休息一段时间，这样才能照顾好自己，怎么样？)]");
										break;
									case NEUTRAL:
										sb.append("[npc.she]绽放出灿烂的笑容，高兴地喊道，"
												+ "[npc.speech([npc.PcName]！你让我怀孕了，是不是很棒？！我一定会照顾好我们的孩子！)]");
										break;
									case OBEDIENT:
										sb.append("[npc.she]绽放出灿烂的笑容，高兴地喊道，"
												+ "[npc.speech(嗨，[npc.pcName]！你让我怀孕了！所以我得做好照顾好孩子的准备！)]");
										break;
								}
								break;
						}
						sb.append("</p>");
						sb.append(
								"<p>"
									+ "你[pc.step]到你的奴隶面前并用[pc.hands]抚摸着[npc.her]的孕肚。"
									+ "你放心地对你孩子的母亲微笑，告诉[npc.herHim]一旦时间到了，莉莱雅就能帮助[npc.herHim]分娩。"
								+ "</p>"
								+ "<p>"
									+ "[npc.Name]点了点头，于是在你抚摸了一会[npc.her]的腹部后，你退了出去，想着接下来该干什么……"
								+ "</p>");
						
					} else {
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								sb.append("叹了口气，"
										+ "[npc.speech(嗨，[npc.pcName]。"
											+(father==null || father.equals(getSlave())
												?"结果我怀孕了"
												:father.getName("")+"让我怀孕了")
											+"，所以说我要休息一阵子。找个别的奴隶来顶替我，好吗？)]");
								break;
							case NEUTRAL:
								sb.append("叹了口气，"
										+ "[npc.speech(嗨，[npc.pcName]。"
											+(father==null || father.equals(getSlave())
												?"结果我怀孕了"
												:father.getName("")+"让我怀孕了")
											+", 所以我这段时间需要放松一下，可以吧？)]");
								break;
							case OBEDIENT:
								sb.append("顺从地告诉你发生了什么，"
										+ "[npc.speech(你好，[npc.pcName]。"
											+(father==null || father.equals(getSlave())
												?"结果我怀孕了"
												:father.getName("")+"让我怀孕了")
											+"，但我不会让这妨碍我履行职责的！)]");
								break;
						}
						sb.append("</p>");
						sb.append(
								"<p>"
									+ "你[pc.step]到你的奴隶面前并用[pc.hands]抚摸着[npc.her]的孕肚。"
									+ "你放心地对[npc.race]微笑，告诉[npc.herHim]一旦时间到了，莉莱雅就能帮助[npc.herHim]分娩。"
								+ "</p>"
								+ "<p>"
									+ "[npc.Name]点了点头，于是在你抚摸了一会[npc.her]的腹部后，你退了出去，想着接下来该干什么……"
								+ "</p>");
					}
				
				} else {
					sb.append(
							"<p>"
								+ "在你看向[npc.race]时，你看见[npc.sheIs]依然挺着孕肚，而[npc.She]一边你对视一边心神恍惚地抚摸着她肿胀的腹部，");
					switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
						case DISLIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									sb.append("[npc.she]向你吐口水时甚至没有掩饰[npc.eyes]中的恨意，"
											+ "[npc.speech(呸，是<i>你</i>。你他妈现在又想干什么？！)]");
									break;
								case NEUTRAL:
									sb.append("[npc.she]努力抑制[npc.eyes]中流露出的恨意，说道，"
											+ "[npc.speech(哦，你好，[npc.pcName]。你想要干什么？)]");
									break;
								case OBEDIENT:
									sb.append("[npc.she]呼唤着你并顺从地掩饰着[npc.her]眼中的恨意，"
											+ "[npc.speech(您好，[npc.pcName]。我能为您做些什么？)]");
									break;
							}
							break;
						case NEUTRAL:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									sb.append("叹了口气，"
											+ "[npc.speech(嗨，[npc.pcName]。我现在很轻松，因为我怀孕了，好吗？)]");
									break;
								case NEUTRAL:
									sb.append("叹了口气，"
											+ "[npc.speech(您好，[npc.pcName]。我能为您做些什么？)]");
									break;
								case OBEDIENT:
									sb.append("叹了口气，"
											+ "[npc.speech(嗨，[npc.pcName]。有何贵干？)]");
									break;
							}
							break;
						case LIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									sb.append("[npc.she]绽放出灿烂的笑容，高兴地喊道，"
											+ "[npc.speech(嗨，[npc.pcName]！您好吗？！我现在很轻松，可以照顾好自己，好吗？)]");
									break;
								case NEUTRAL:
									sb.append("[npc.she]绽放出灿烂的笑容，高兴地喊道，"
											+ "[npc.speech(嗨，[npc.pcName]！我能照顾好自己，您怎么样？)]");
									break;
								case OBEDIENT:
									sb.append("[npc.she]绽放出灿烂的笑容，高兴地喊道，"
											+ "[npc.speech(嗨，[npc.pcName]！有何贵干？)]");
									break;
							}
							break;
					}
					sb.append("</p>");
					sb.append(
							"<p>"
								+ "你[pc.step]到你的奴隶面前并用[pc.hands]抚摸着[npc.her]的孕肚。"
								+ "你放心地对[npc.race]微笑，告诉[npc.herHim]一旦时间到了，莉莱雅就能帮助[npc.herHim]分娩。"
							+ "</p>"
							+ "<p>"
								+ "[npc.Name]点了点头，于是在你抚摸了一会[npc.her]的腹部后，你退了出去，想着接下来该干什么……"
							+ "</p>");
				}
				
			} else { // Standard repeat encounter:

				sb.append("<p>");
				
				if(isDoll()) {
					sb.append("[npc.speech([style.morning]好，[pc.name]，)][npc.she]发出略显机械的声音，[npc.speech(我能帮你做点什么？)]");
					
				} else {
					sb.append("你看着[npc.race]，[npc.she]同样凝视着你，");
					
					switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
						case DISLIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									sb.append("[npc.she]向你吐口水时甚至没有掩饰[npc.eyes]中的恨意，"
											+ "[npc.speech(呸，是<i>你</i>。你他妈现在又想干什么？！)]");
									break;
								case NEUTRAL:
									sb.append("[npc.she]努力抑制[npc.eyes]中流露出的恨意，说道，"
											+ "[npc.speech(哦，嗨，[npc.pcName]。你想要干什么？)]");
									break;
								case OBEDIENT:
									sb.append("[npc.she]呼唤着你并顺从地掩饰着[npc.her]眼中的恨意，"
											+ "[npc.speech(嗨，[npc.pcName]。我能为您做些什么？)]");
									break;
							}
							break;
						case NEUTRAL:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									sb.append("叹了口气，"
											+ "[npc.speech(嗨，[npc.pcName]。你需要些什么呢？)]");
									break;
								case NEUTRAL:
									sb.append("叹了口气，"
											+ "[npc.speech(嗨，[npc.pcName]。我能为您做些什么？)]");
									break;
								case OBEDIENT:
									sb.append("叹了口气，"
											+ "[npc.speech(嗨，[npc.pcName]。有何贵干？)]");
									break;
							}
							break;
						case LIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									sb.append("[npc.she]绽放出灿烂的笑容，高兴地喊道，"
											+ "[npc.speech(嗨，[npc.pcName]！我看见您真是太高兴了，我一直都表现的很好！)]");
									break;
								case NEUTRAL:
									sb.append("[npc.she]绽放出灿烂的笑容，高兴地喊道，"
											+ "[npc.speech(嗨，[npc.pcName]！您好吗？有何贵干？)]");
									break;
								case OBEDIENT:
									sb.append("[npc.she]绽放出灿烂的笑容，高兴地喊道，"
											+ "[npc.speech(嗨，[npc.pcName]！有何贵干？)]");
									break;
							}
							break;
					}
				}
				
				sb.append("</p>");
				sb.append(
						"<p>"
							+ "奴隶正在等待你的命令，但你不知道现在该如何安排[npc.herHim]……"
						+ "</p>");
			}
		}
		
		sb.append(getFooterText());
		
		return UtilText.parse(getSlave(), sb.toString());
	}
	
	public static final DialogueNode SLAVE_START_NO_CONTENT = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	private static Response getWakeUpResponse() {
		return new Response(UtilText.parse(getSlave(), "叫醒[npc.herHim]"),
				UtilText.parse(getSlave(),
						"把[npc.name]叫醒，这样你就可以和[npc.herHim]进行互动。"
						+ "<br/>[style.italicsMinorBad(要是把[npc.She]吵醒了，[npc.herHim]肯定会很生气的……)]"),
				SLAVE_START_NO_CONTENT) {
			@Override
			public void effects() {
				getSlave().wakeUp();
				
				StringBuilder sb = new StringBuilder();
				sb.append("<p>");
					sb.append("你想与[npc.name]互动，便[pc.step]到[npc.sheIs]睡觉的地方，然后");
					if(Main.game.getPlayer().isMute()) {
						sb.append("摇了摇[npc.herHim]。");
					} else {
						sb.append("叫着[npc.her]的名字。");
					}
					if(getSlave().hasTrait(Perk.HEAVY_SLEEPER, true)) {
						sb.append("因为[npc.name]是重度沉睡者，这并不足以唤醒[npc.herHim]，"
								+ "你不得不用力推了[npc.herHim]好几次，[npc.she]才终于睁开疲倦的[npc.eyes]。");
					}
					sb.append("[npc.name]醒过来看你站在旁边，便迅速站起身面向你。");
				sb.append("</p>");
				if (ModProps.hasValue(PropertyValue.wakeAffectionLoss)) {
					sb.append("<p style='text-align:center;'>[style.italicsMinorBad([npc.Name]不想被叫醒……)]</p>");
					Main.game.appendToTextEndStringBuilder(getSlave().incrementAffection(Main.game.getPlayer(), -1));
				}
				Main.game.appendToTextEndStringBuilder(UtilText.parse(getSlave(), sb.toString()));
				Main.game.appendToTextEndStringBuilder(getSlaveStartCoreContent());
			}
		};
	}
	
	public static final DialogueNode SLAVE_START = new DialogueNode("", "。", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
				if(getSlave().isAsleep()) {
					UtilText.nodeContentSB.append("[npc.name]现在正在睡觉，你得先叫醒[npc.herHim]，才能和[npc.herHim]互动。");
					UtilText.nodeContentSB.append("这样做肯定会让[npc.herHim]有些恼火……");
				} else {
					if(isDoll()) {
						UtilText.nodeContentSB.append("你想要和[npc.name]互动，便[pc.walk]到玩偶面前，引起了[npc.her]的注意。");
						if(isDollStatue()) {
							UtilText.nodeContentSB.append("因为你命令[npc.herHim]扮演雕像，所以[npc.she]尽职尽责地无视你的靠近，在原地保持静止。");
						}
					} else {
						UtilText.nodeContentSB.append("你想要和[npc.name]互动，便[pc.walk]到[npc.herHim]面前引起了[npc.her]的注意。");
					}
				}
			UtilText.nodeContentSB.append("</p>");
			
			if(!getSlave().isAsleep() && !isDollStatue()) {
				UtilText.nodeContentSB.append(getSlaveStartCoreContent());
			}
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "互动";
			} else if(index == 1) {
				return UtilText.parse("[style.colourSex(做爱)]");
			} else if(index == 2) {
				return UtilText.parse("[style.colourCompanion(管理)]");
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab == 0) {
				if(index == 0) {
					return new Response("离开",
							UtilText.parse(getSlave(),
									isDollStatue()
										?"让[npc.name]静止在原地，继续你的旅程。"
										:(getSlave().isAsleep()
											?"离开，让[npc.name]继续睡觉……"
											:"告诉[npc.name]你会在其他时间再去找[npc.herHim]。")),
							Main.game.getDefaultDialogue(false)) {
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							applyReactionReset();
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
				}
				
				if(isDollStatue()) {
					if(index == 1) {
						return new Response("暂停扮演雕像",
								UtilText.parse(getSlave(), "让[npc.name]暂时停止扮演雕像，这样你就可以与[npc.herHim]互动了。"),
								SLAVE_START_NO_CONTENT) {
							@Override
							public void effects() {
								dollStatueInterrupted = true;
								Main.game.appendToTextEndStringBuilder("<p>");
									Main.game.appendToTextEndStringBuilder("想要与[npc.name]互动，而不想让[npc.herHim]继续像一尊毫无反应的雕像一样，你应该允许[npc.herHim]再次暂时自由移动。");
								Main.game.appendToTextEndStringBuilder("</p>");
								Main.game.appendToTextEndStringBuilder(getSlaveStartCoreContent());
							}
						};
					}
					
				} else if(getSlave().isAsleep()) {
					if(index == 1) {
						return getWakeUpResponse();
					}

				} else {
					if(index == 1) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveBackground)) {
							return new Response("过往历史",
									UtilText.parse(getSlave(),
											isDoll()
												?"问[npc.name]被创造时的记忆。"
												:"问[npc.name]过去的生活。"),
									SLAVE_PROGRESSION) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveBackground);
									if(!isDoll()) {
										Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 3));
									}
								}
							};
						} else {
							return new Response("过往历史", UtilText.parse(getSlave(), "你今天已经和[npc.name]聊过这个问题了……"), null);
						}
						
					} else if(index == 2) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveSmallTalk)) {
							return new Response("闲聊", UtilText.parse(getSlave(), "与[npc.name]聊这聊那。"), SLAVE_MINOR) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveSmallTalk);
									if(!isDoll()) {
										switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
											case DISLIKE:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), -1f));
												break;
											case NEUTRAL:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 2f));
												break;
											case LIKE:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 4f));
												break;
										}
									}
								}
							};
						} else {
							return new Response("闲聊", UtilText.parse(getSlave(), "你今天已经花时间和[npc.name]聊过了。"), null);
						}
						
					} else if(index == 3
							&& getSlave().equals(Main.game.getNpc(Scarlett.class))
							&& !Main.getProperties().hasValue(PropertyValue.companionContent)
							&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
						if(Main.game.getNpc(Helena.class).getLocationPlace().getPlaceType()!=PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP) {
							return new Response("海伦娜",
									UtilText.parse(getSlave(), "海伦娜的商店目前关闭，因此你暂时无法带[npc.Name]去找她……"),
									null);
						}
						return new Response("海伦娜",
								UtilText.parse(getSlave(), "陪同[npc.Name]前往奴隶巷中海伦娜的商店。"),
								ScarlettsShop.ROMANCE_SHOP_CORE) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_RELATIONSHIP;
							}
							@Override
							public void effects() {
	//							Main.game.getPlayer().addCompanion(getSlave());
								applyReactionReset();
								Main.game.getDialogueFlags().setManagementCompanion(null);
								// Move them both here to make sure they haven't gone due to time ticking over into night time when player arrives:
								Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
								Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
								Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
							}
						};
						
					} else if(index == 5 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
						if(!Main.game.getPlayer().hasCompanion(getSlave())) {
							if(!getSlave().isCompanionAvailable(Main.game.getPlayer())) {
								return new Response("加入队伍",
										UtilText.parse(getSlave(), "[npc.Name]不能加入你的队伍！"),
										null);
									
							} else if(Main.game.getPlayer().canHaveMoreCompanions()) {
								return new Response("加入队伍",
										UtilText.parse(getSlave(), "命令[npc.name]跟随你。"),
										SLAVE_START){
									@Override
									public void effects() {
										applyReactionReset();
										Main.game.getPlayer().addCompanion(getSlave());
									}
								};
							} else {
								return new Response("加入队伍",
										"你的队伍满员了！",
										null);
							}
						} else {
							return new Response("从队伍移除",
									UtilText.parse(getSlave(), "命令[npc.name]从你的队伍里离开。"),
									SLAVE_START){
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getPlayer().removeCompanion(getSlave());
								}
							};
						}
						
					} else if(index == 6) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveEncourage)) {
							if(getCurrentJob()==SlaveJob.IDLE) {
								return new Response("工作",
										UtilText.parse(getSlave(),
												"[npc.Name]现在没有任何工作……<br/>[style.italicsMinorBad(在[npc.name]无所事事的时候，你不能问[npc.her]工作相关的事情！)]"),
										null);
								
							} else {
								return new Response("工作", UtilText.parse(getSlave(), "询问[npc.name][npc.her]的工作如何。"), SLAVE_ENCOURAGE) {
									@Override
									public void effects() {
										applyReactionReset();
										getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveEncourage);
										if(!isDoll()) {
											switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
												case DISLIKE:
													Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 0.5f));
													Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(0.1f));
													break;
												case NEUTRAL:
													Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 2f));
													Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(1f));
													break;
												case LIKE:
													Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 4f));
													Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(2f));
													break;
											}
										}
									}
								};
							}
							
						} else {
							return new Response("工作", UtilText.parse(getSlave(), "你已经问过[npc.name][npc.her]的工作了。"), null);
						}
						
					} else if(index == 7) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveHug)) {
							return new Response("拥抱", UtilText.parse(getSlave(), "拥抱[npc.name]。"), SLAVE_HUG) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveHug);
	
									if(!isDoll()) {
										switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
											case DISLIKE:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), -2));
												Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-1f));
												break;
											case NEUTRAL:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 2));
												Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-1));
												break;
											case LIKE:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 5));
												Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-2));
												break;
										}
									}
								}
							};
						} else {
							return new Response("拥抱", UtilText.parse(getSlave(), "你今天已经花时间拥抱[npc.name]了。"), null);
						}
						
					} else if(index == 8) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlavePettings)) {
							return new Response("爱抚", UtilText.parse(getSlave(), "给[npc.name]充满爱的抚摸。"), SLAVE_PETTINGS) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlavePettings);
									
									if(!isDoll()) {
										switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
											case DISLIKE:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), -2));
												Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-1f));
												break;
											case NEUTRAL:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 2));
												Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-1));
												break;
											case LIKE:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 5));
												Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-2));
												break;
										}
									}
								}
							};
						} else {
							return new Response("爱抚", UtilText.parse(getSlave(), "你今天已经花时间爱抚[npc.name]了。"), null);
						}
						
					} else if(index == 9) {
						if(Main.game.getPlayer().hasItemType(ItemType.PRESENT)) {
							if(isDoll()) {
								return new Response("赠送礼物", UtilText.parse(getSlave(), "作为一个玩偶，[npc.name]不需要也不喜欢礼物……"), null);
							}
							return new Response("赠送礼物", UtilText.parse(getSlave(), "把你携带的礼物交给[npc.name]。"), SLAVE_PRESENT) {
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.PRESENT));
	
									if(!isDoll()) {
										Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 10));
										Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-2));
									}
								}
							};
							
						} else {
							return null;
						}
						
					} else if(index == 11) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveInspect)) {
							return new Response("检查", UtilText.parse(getSlave(), "让[npc.name]脱光衣服，在房间里绕圈并接受你的检查。"), SLAVE_INSPECT) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveInspect);
		
									getSlave().setAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.ASS, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.MOUND, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.MOUTH, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.TESTICLES, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer(), true);
	
									if(!isDoll()) {
										if(getSlave().getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() || getSlave().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isPositive()) {
											Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 10));
										} else {
											Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), -5));
										}
										Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(5));
									}
								}
							};
						} else {
							return new Response("检查", UtilText.parse(getSlave(), "你今天已经花时间检查[npc.name]了。"), null);
						}
						
					} else if(index == 12) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveSpanking)) {
							return new Response("打屁股", UtilText.parse(getSlave(), "把[npc.name]按在你膝盖上，粗暴地打[npc.herHim]的屁股。"), SLAVE_SPANKING) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveSpanking);
									if(!isDoll()) {
										if(getSlave().getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive()) {
											Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 10));
										} else {
											Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), -5));
										}
										Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(10));
									}
								}
							};
						} else {
							return new Response("打屁股", UtilText.parse(getSlave(), "你今天已经打过[npc.name]的屁股了。"), null);
						}
						
					} else if(index == 13) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveMolest)) {
							return new Response("猥亵", UtilText.parse(getSlave(), "猥亵[npc.name]的身体，并让[npc.her]保持不动。"), SLAVE_MOLEST) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveMolest);
	
									if(!isDoll()) {
										if(getSlave().isAttractedTo(Main.game.getPlayer())
												&& (getSlave().hasFetish(Fetish.FETISH_SUBMISSIVE) || getSlave().hasFetish(Fetish.FETISH_NON_CON_SUB))) {
											Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 10));
											
										} else if(!getSlave().isAttractedTo(Main.game.getPlayer()) && !getSlave().hasFetish(Fetish.FETISH_SUBMISSIVE) && !getSlave().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
											Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), -10));
										}
										Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(10));
									}
								}
							};
						} else {
							return new Response("猥亵", UtilText.parse(getSlave(), "你今天已经猥亵过[npc.name]了。"), null);
						}
						
					}
				}
				
				return null;
			
			} else if(responseTab == 1) {
				if(index == 0) {
					return new Response("离开", 
							UtilText.parse(getSlave(),
									isDollStatue()
										?"让[npc.name]静止在原地，继续你的旅程。"
										:(getSlave().isAsleep()
											?"让[npc.name]继续睡觉……"
											:"告诉[npc.name]你会在其他时间再去找[npc.herHim]。"))
							, Main.game.getDefaultDialogue(false)) {
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							applyReactionReset();
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
				}
				
				if(Main.game.getPlayer().getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
					if(index == 1) {
						if((!characterForSex.isSlave() || !characterForSex.getOwner().isPlayer())
								&& !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("做爱", UtilText.parse(characterForSex, "[npc.Name]没被你吸引，你不能强迫[npc.herHim]和你做爱……"), null);
							
						} else if(Main.game.isNonConEnabled() && !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new ResponseSex("强奸", UtilText.parse(characterForSex, "[npc.Name]显然不想跟你做爱，[npc.sheHasFull]似乎别无选择……"), 
									false, false,
									new SMMilkingStall(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
											Util.newHashMapOfValues(new Value<>(characterForSex, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))),
									getDominantSpectators(),
									getSubmissiveSpectators(),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "RAPE_START_MILKING_ROOM", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									if(!characterForSex.isDoll()) {
										if(characterForSex.getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
											Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
										} else {
											Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), -25));
										}
									}
								}
							};
							
						} else {
							return new ResponseSex("做爱", UtilText.parse(characterForSex, "和[npc.name]做爱。"), 
									true, false,
									new SMMilkingStall(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
											Util.newHashMapOfValues(new Value<>(characterForSex, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))),
									getDominantSpectators(),
									getSubmissiveSpectators(),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_START_MILKING_ROOM", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									if(!characterForSex.isDoll()) {
										Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
									}
								}
							};
						}
					}
					
				} else if(getSlave().isAsleep()) {
					if(index == 1) {
						if((!characterForSex.isSlave() || !characterForSex.getOwner().isPlayer())
								&& !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("睡奸", UtilText.parse(characterForSex, "[npc.Name]没被你吸引，你不能强迫[npc.herHim]和你做爱……"), null);
							
						} else if(Main.game.isNonConEnabled() && !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!getSlave().hasTrait(Perk.HEAVY_SLEEPER, true)) {
								return new Response("睡奸", UtilText.parse(characterForSex, "[npc.Name]并不是重度沉睡者，因此你不能对[npc.herHim]进行睡奸……"), null);
							}
							return new ResponseSex("睡奸",
									UtilText.parse(characterForSex,
											"[npc.Name]显然不想跟你做爱，但[npc.sheHasFull]似乎别无选择……"
											+ "因为[npc.sheIs]是个重度沉睡者，你可以在不吵醒[npc.herHim]的前提下温柔地操[npc.herHim]……"), 
									false, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											new ArrayList<>(),
											new ArrayList<>(),
											Util.newArrayListOfValues(ResponseTag.PREFER_MISSIONARY),
											false,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPace.DOM_GENTLE))),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "RAPE_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(characterForSex));
								}
							};
						
						} else {
							if(!getSlave().hasTrait(Perk.HEAVY_SLEEPER, true)) {
								return new Response("睡奸", UtilText.parse(characterForSex, "[npc.Name]并不是重度沉睡者，因此你不能对[npc.herHim]进行睡奸……"), null);
							}
							return new ResponseSex("睡奸",
									UtilText.parse(characterForSex, "因为[npc.sheIs]是个重度沉睡者，你可以在不吵醒[npc.herHim]的前提下温柔地操[npc.herHim]……"), 
									true, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											new ArrayList<>(),
											new ArrayList<>(),
											Util.newArrayListOfValues(ResponseTag.PREFER_MISSIONARY),
											false,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPace.DOM_GENTLE))),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(characterForSex));
								}
							};
						}
						
					}
					
				} else {
					if(index == 1) {
						if((!characterForSex.isSlave() || !characterForSex.getOwner().isPlayer())
								&& !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("做爱", UtilText.parse(characterForSex, "[npc.Name]没被你吸引，你不能强迫[npc.herHim]和你做爱……"), null);
							
						} else if(Main.game.isNonConEnabled() && !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new ResponseSex("强奸", UtilText.parse(characterForSex, "[npc.Name]显然不想跟你做爱，[npc.sheHasFull]似乎别无选择……"), 
									false, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											(characterForSex.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)
												?Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY)
												:new ArrayList<>()), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "RAPE_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									if(!characterForSex.isDoll()) {
										if(characterForSex.getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
											Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
										} else {
											Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), -25));
										}
									}
									//applyImmobilisationText(Util.newArrayListOfValues(characterForSex));
								}
							};
						
						} else {
							return new ResponseSex("做爱", UtilText.parse(characterForSex, "和[npc.name]做爱。"), 
									true, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											isDollStatue()
												?Util.newArrayListOfValues(getDollSexTag())
												:(characterForSex.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)
													?Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY)
													:new ArrayList<>()),
											false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									if(!characterForSex.isDoll()) {
										Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
									}
									//applyImmobilisationText(Util.newArrayListOfValues(characterForSex));
								}
							};
						}
						
					} else if(index == 2) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("“串肉串”(在前)", "得有第三个人在场，你才能开始“串肉串”……", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("“串肉串”(在前)", "你不能以自己为目标应用这个动作！", null);
							
						} else if (characterForSexSecondary.isDollStatue()) {
							return new Response("“串肉串”(在前)",
									UtilText.parse(characterForSexSecondary,
											"[npc.name]是一个无法移动的玩偶，不能在做爱中占据支配地位……"),
									null);
							
						} else if (characterForSexSecondary.isAsleep()) {
							return new Response("“串肉串”(在前)",
									UtilText.parse(characterForSexSecondary,
											"[npc.name]现在完全睡着了，不能参与做爱……"),
									null);
							
						} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
							return new Response("“串肉串”(在前)",
									UtilText.parse(characterForSexSecondary, characterForSex,
											"[npc.Name]对[npc2.name]不感兴趣，因此不可能让[npc.herHim]占据主导地位与[npc2.herHim]做爱……"),
									null);
								
						} else if((!Main.game.isNonConEnabled() || !characterForSex.isSlave()) && !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("“串肉串”(在前)",
									UtilText.parse(characterForSex,
											"[npc.Name]对你没有兴趣，因此不愿意在[npc.she]与你三人行的……"),
									null);
							
						} else if((!Main.game.isNonConEnabled() || !characterForSex.isSlave()) && !characterForSex.isAttractedTo(characterForSexSecondary)) {
							return new Response("“串肉串”(在前)",
									UtilText.parse(characterForSexSecondary, characterForSex,
											"[npc2.Name]对[npc.name]没有兴趣，因此不愿意参与[npc2.she]与[npc.herHim]的三人行……"),
									null);
							
						} else {
							boolean isRape = !characterForSex.isAttractedTo(Main.game.getPlayer()) && !characterForSex.isAttractedTo(characterForSexSecondary);
							return new ResponseSex(
									isRape
										?"“串肉串”强奸(在前)"
										:"“串肉串”(在前)",
									UtilText.parse(characterForSex, characterForSexSecondary, "移动到[npc.name]前面，[npc2.name]玩弄[npc.her]的时候，你可以使用[npc.her]的嘴。"),
									null, null, null, null, null, null,
									!isRape, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(characterForSexSecondary, Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROAST_FRONT_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(characterForSex));
								}
							};
						}
						
					
					} else if(index == 3) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("“串肉串”(在后)", "得有第三个人在场，你才能开始“串肉串”……", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("“串肉串”(在后)", "你不能以自己为目标应用这个动作！", null);
							
						} else if (characterForSexSecondary.isDollStatue()) {
							return new Response("“串肉串”(在后)",
									UtilText.parse(characterForSexSecondary,
											"[npc.name]是一个无法移动的玩偶，不能在做爱中占据支配地位……"),
									null);
							
						} else if (characterForSexSecondary.isAsleep()) {
							return new Response("“串肉串”(在后)",
									UtilText.parse(characterForSexSecondary,
											"[npc.name]现在完全睡着了，不能参与做爱……"),
									null);
							
						} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
							return new Response("“串肉串”(在后)",
									UtilText.parse(characterForSexSecondary, characterForSex,
											"[npc.Name]对[npc2.name]不感兴趣，因此不可能让[npc.herHim]占据主导地位与[npc2.herHim]做爱……"),
									null);
								
						} else {
							 if((!Main.game.isNonConEnabled() || !characterForSex.isSlave()) && !characterForSex.isAttractedTo(Main.game.getPlayer())) {
								return new Response("“串肉串”(在后)",
										UtilText.parse(characterForSex,
												"[npc.Name]对你没有兴趣，因此不愿意在[npc.she]与你三人行的……"),
										null);
								
							} else if((!Main.game.isNonConEnabled() || !characterForSex.isSlave()) && !characterForSex.isAttractedTo(characterForSexSecondary)) {
								return new Response("“串肉串”(在后)",
										UtilText.parse(characterForSexSecondary, characterForSex,
												"[npc2.Name]对[npc.name]没有兴趣，因此不愿意参与[npc2.she]与[npc.herHim]的三人行……"),
										null);
								
							} else {
								boolean isRape = !characterForSex.isAttractedTo(Main.game.getPlayer()) && !characterForSex.isAttractedTo(characterForSexSecondary);
								return new ResponseSex(
										isRape
											?"“串肉串”强奸(在后)"
											:"“串肉串”(在后)",
										UtilText.parse(characterForSex, characterForSexSecondary, "移动到[npc.name]背后，[npc2.name]使用[npc.her]的嘴的时候，你可以玩弄[npc.her]的后面。"),
										null, null, null, null, null, null,
										!isRape, false,
										getGenericSlaveSexManager(
												Util.newArrayListOfValues(Main.game.getPlayer(), characterForSexSecondary),
												Util.newArrayListOfValues(characterForSex),
												Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY), false),
										getAfterSexDialogue(),
										UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROAST_BEHIND_START", characterForSex, characterForSexSecondary)) {
									@Override
									public void effects() {
										applyReactionReset();
										//applyImmobilisationText(Util.newArrayListOfValues(characterForSex));
									}
								};
							}
						}
					
					} else if(index == 4) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("并排后入(作为支配方)", "得有第三个人在场，你才能开始并排后入……", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("并排后入(作为支配方)", "你不能以自己为目标应用这个动作！", null);
							
						} else if (characterForSexSecondary.isAsleep()) {
							return new Response("并排后入(作为支配方)",
									UtilText.parse(characterForSexSecondary,
											"[npc.name]现在完全睡着了，不能参与做爱……"),
									null);
							
						} else if((!Main.game.isNonConEnabled() || !characterForSexSecondary.isSlave()) && !characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("并排后入(作为支配方)",
									UtilText.parse(characterForSexSecondary,
											"[npc.Name]对你没有兴趣，因此不愿意在[npc.she]与你三人行的……"),
									null);
								
						} else if((!Main.game.isNonConEnabled() || !characterForSex.isSlave()) && !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("并排后入(作为支配方)",
									UtilText.parse(characterForSex,
											"[npc.Name]对你没有兴趣，因此不愿意在[npc.she]与你三人行的……"),
									null);
							
						} else {
							boolean isRape = !characterForSex.isAttractedTo(Main.game.getPlayer()) || !characterForSexSecondary.isAttractedTo(Main.game.getPlayer());
							return new ResponseSex(
									isRape
										?"并排后入(强奸)(在上)"
										:"并排后入(在上)",
									UtilText.parse(characterForSex, characterForSexSecondary, "让[npc.name]和[npc2.name]四体投地，跪在[npc.Name]身后，准备并排后入他们。"),
									null, null, null, null, null, null,
									!isRape, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex, characterForSexSecondary),
											Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SIDE_BY_SIDE_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(characterForSex, characterForSexSecondary));
								}
							};
						}
						
					} else if(index==5) {
						if(charactersPresent.size()<3) {
							return new Response("一受多攻(作为支配方)",
									"至少要有三个角色在场才能开始群交。",
									null);
							
						} else if (charactersPresent.stream().anyMatch(c->c.isAsleep())) {
							boolean soloSleeper = charactersPresent.stream().filter(c->c.isAsleep()).count()==1;
							String names = Util.charactersToStringListOfNames(charactersPresent.stream().filter(c->c.isAsleep()).collect(Collectors.toList()));
							return new Response("一受多攻(作为支配方)",
											names+"需要醒来才能群交！",
									null);
							
						} else {
							boolean isRape = charactersPresent.stream().anyMatch(c->!c.isAttractedTo(Main.game.getPlayer()));
							// Handle attraction/non-con:
							if(isRape) {
								if(charactersPresent.stream().anyMatch(c->!c.isSlave())) {
									List<GameCharacter> nonSlaves = new ArrayList<>();
									for(GameCharacter character : charactersPresent) {
										if(!character.isSlave() && !character.isAttractedTo(Main.game.getPlayer())) {
											nonSlaves.add(character);
										}
									}
									String isAre = nonSlaves.size()==1?"is":"are";
									String slavePural = nonSlaves.size()==1?"slave":"slaves";
									String them = nonSlaves.size()==1?UtilText.parse(nonSlaves.get(0), "[npc.herHim]"):"他们";
									return new Response("一受多攻(作为支配方)",
											"由于"+Util.charactersToStringListOfNames(nonSlaves)+" "+isAre+" 对你不感兴趣，并且"+isAre+"不是你的"+slavePural+"，你不能与"+them+"进行群交。",
											null);
									
								} else if(!Main.game.isNonConEnabled()) {
									List<GameCharacter> nonAttracted = new ArrayList<>();
									for(GameCharacter character : charactersPresent) {
										if(!character.isAttractedTo(Main.game.getPlayer())) {
											nonAttracted.add(character);
										}
									}
									String isAre = nonAttracted.size()==1?"is":"are";
									String them = nonAttracted.size()==1?UtilText.parse(nonAttracted.get(0), "[npc.herHim]"):"他们";
									return new Response("一受多攻(作为支配方)",
											"由于"+Util.charactersToStringListOfNames(nonAttracted)+" "+isAre+" 对你不感兴趣，你不能与"+them+"进行群交。",
											null);
								}
							}
							
							List<GameCharacter> nonAttracted = new ArrayList<>();
							for(GameCharacter character : charactersPresent) {
								if(!character.isAttractedTo(Main.game.getPlayer())) {
									nonAttracted.add(character);
								}
							}
							String isAre = nonAttracted.size()==1?"is":"are";
							String them = nonAttracted.size()==1?UtilText.parse(nonAttracted.get(0), "[npc.herHim]"):"他们";
							List<GameCharacter> sexParticipants = new ArrayList<GameCharacter>(charactersPresent);
							return new ResponseSex(
									isRape
										?"轮奸(作为支配方)"
										:"一受多攻(作为支配方)",
									"同"+Util.charactersToStringListOfNames(sexParticipants)+"做爱。"
										+ (isRape
											?"由于"+Util.charactersToStringListOfNames(nonAttracted)+"没被你吸引，你必须强迫"+them+"加入……"
											:""),//TODO sleeping
									null, null, null, null, null, null,
									!isRape, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											sexParticipants,
											Util.newArrayListOfValues(), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), sexParticipants.size()==3?"SEX_GANGBANG_3":"SEX_GANGBANG_4", sexParticipants)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(sexParticipants);
								}
							};
						}
						
					} else if(index == 6) {
						if (characterForSex.isDollStatue()) {
							return new Response("服从型性爱",
									UtilText.parse(characterForSex,
											"[npc.name]是一个无法移动的玩偶，不能在做爱中占据支配地位……"),
									null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("服从型性爱",
									UtilText.parse(characterForSex, 
										"[npc.Name]对你不感兴趣，"
										+ (Main.game.isNonConEnabled() && characterForSex.isSlave()
												?" 所以如果你想和[npc.herHim]发生性关系，就需要作为主导者强奸[npc.herHim]。"
												:"所以你不能与[npc.herHim]进行服从型性爱。")),
									null);
							
						} else {
							return new ResponseSex("服从性爱",
									UtilText.parse(characterForSex, "和[npc.name]来一场服从型性爱。"), 
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
									true, true,
									getGenericSlaveSexManager(
										Util.newArrayListOfValues(characterForSex),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										(characterForSex.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)
												?Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY)
												:new ArrayList<>()), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_AS_SUB_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									if(!characterForSex.isDoll()) {
										Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
									}
									//applyImmobilisationText(Util.newArrayListOfValues(Main.game.getPlayer()));
								}
							};
						}
						
					} else if(index == 7) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("接受“串肉串”(在前)", "得有第三个人在场，你才能接受“串肉串”……", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("接受“串肉串”(在前)", "你不能以自己为目标应用这个动作！", null);
							
						} else if (characterForSex.isDollStatue()) {
							return new Response("接受“串肉串”(在前)",
									UtilText.parse(characterForSex,
											"[npc.name]是一个无法移动的玩偶，不能在做爱中占据支配地位……"),
									null);
							
						} else if (characterForSexSecondary.isAsleep()) {
							return new Response("接受“串肉串”(在前)",
									UtilText.parse(characterForSexSecondary,
											"[npc.name]现在完全睡着了，不能参与做爱……"),
									null);
							
						} else if (characterForSexSecondary.isDollStatue()) {
							return new Response("接受“串肉串”(在前)",
									UtilText.parse(characterForSexSecondary,
											"[npc.name]是一个无法移动的玩偶，不能在做爱中占据支配地位……"),
									null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("接受“串肉串”(在前)",
										UtilText.parse(characterForSexSecondary, characterForSex,
												"[npc.name]和[npc2.name]都对你不感兴趣，"
												+ (Main.game.isNonConEnabled() && characterForSexSecondary.isSlave()
														?"所以，如果你想和他们进行性关系，你就必须以支配者的身份强奸他们。"
														:"所以你不能和他们进行服从型性爱。")),
										null);
							} else {
								return new Response("接受“串肉串”(在前)",
										UtilText.parse(characterForSex,
												"[npc.Name]对你不感兴趣，"
												+ (Main.game.isNonConEnabled()
													?" 所以如果你想和[npc.herHim]发生性关系，就需要作为主导者强奸[npc.herHim]。"
													:"所以你不能与[npc.herHim]进行服从型性爱。")),
										null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("接受“串肉串”(在前)",
									UtilText.parse(characterForSexSecondary,
										"[npc.Name]对你不感兴趣，"
										+ (Main.game.isNonConEnabled() && characterForSexSecondary.isSlave()
											?" 所以如果你想和[npc.herHim]发生性关系，就需要作为主导者强奸[npc.herHim]。"
											:"所以你不能与[npc.herHim]进行服从型性爱。")),
									null);
							
						} else {
							return new ResponseSex(
									"接受“串肉串”(在前)",
									UtilText.parse(characterForSex, characterForSexSecondary, "你四肢着地，面朝[npc.name]，[npc2.name]玩弄你的后面的时候，[npc.she]就可以使用你的嘴了。"),
									null, null, null, null, null, null,
									true, true,
									getGenericSlaveSexManager(
										Util.newArrayListOfValues(characterForSexSecondary, characterForSex),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROASTED_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(Main.game.getPlayer()));
								}
							};
						}
							
						
					} else if(index == 8) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("“串肉串”(在后)", "得有第三个人在场，你才能开始“串肉串”……", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("接受“串肉串”(在后)", "你不能以自己为目标应用这个动作！", null);
							
						} else if (characterForSex.isDollStatue()) {
							return new Response("接受“串肉串”(在后)",
									UtilText.parse(characterForSex,
											"[npc.name]是一个无法移动的玩偶，不能在做爱中占据支配地位……"),
									null);
							
						} else if (characterForSexSecondary.isDollStatue()) {
							return new Response("接受“串肉串”(在后)",
									UtilText.parse(characterForSexSecondary,
											"[npc.name]是一个无法移动的玩偶，不能在做爱中占据支配地位……"),
									null);
							
						} else if (characterForSexSecondary.isAsleep()) {
							return new Response("接受“串肉串”(在后)",
									UtilText.parse(characterForSexSecondary,
											"[npc.name]现在完全睡着了，不能参与做爱……"),
									null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("接受“串肉串”(在后)",
										UtilText.parse(characterForSexSecondary, characterForSex,
												"[npc.name]和[npc2.name]都对你不感兴趣，"
												+ (Main.game.isNonConEnabled() && characterForSexSecondary.isSlave()
														?"所以，如果你想和他们进行性关系，你就必须以支配者的身份强奸他们。"
														:"所以你不能和他们进行服从型性爱。")),
										null);
							} else {
								return new Response("接受“串肉串”(在后)",
										UtilText.parse(characterForSex,
												"[npc.Name]对你不感兴趣，"
												+ (Main.game.isNonConEnabled()
													?" 所以如果你想和[npc.herHim]发生性关系，就需要作为主导者强奸[npc.herHim]。"
													:"所以你不能与[npc.herHim]进行服从型性爱。")),
										null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("接受“串肉串”(在后)",
									UtilText.parse(characterForSexSecondary,
										"[npc.Name]对你不感兴趣，"
										+ (Main.game.isNonConEnabled() && characterForSexSecondary.isSlave()
											?" 所以如果你想和[npc.herHim]发生性关系，就需要作为主导者强奸[npc.herHim]。"
											:"所以你不能与[npc.herHim]进行服从型性爱。")),
									null);
							
						} else {
							return new ResponseSex(
									"接受“串肉串”(在后)",
									UtilText.parse(characterForSex, characterForSexSecondary, "你四肢跪地，将身后展现给[npc.name]，这样在[npc.she]干你的时候[npc2.name]可以同时使用你的嘴巴。"),
									null, null, null, null, null, null,
									true, true,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(characterForSex, characterForSexSecondary),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROASTED_START", characterForSexSecondary, characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(Main.game.getPlayer()));
								}
							};
						}
					
					} else if(index == 9) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("并排后入(在下)", UtilText.parse(characterForSex, "你需要第三个人在场，才能被他们或者[npc.name]操……"), null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("并排后入(作为服从方)", "你不能以自己为目标应用这个动作！", null);
							
						} else if (characterForSex.isDollStatue()) {
							return new Response("并排后入(作为服从方)",
									UtilText.parse(characterForSex,
											"[npc.name]是一个无法移动的玩偶，不能在做爱中占据支配地位……"),
									null);
							
						} else if (characterForSexSecondary.isDollStatue()) {
							return new Response("并排后入(作为服从方)",
									UtilText.parse(characterForSexSecondary,
											"[npc.name]是一个无法移动的玩偶，不能在做爱中占据支配地位……"),
									null);
							
						} else if (characterForSexSecondary.isAsleep()) {
							return new Response("并排后入(作为服从方)",
									UtilText.parse(characterForSexSecondary,
											"[npc.name]现在完全睡着了，不能参与做爱……"),
									null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("并排后入(在下)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.name]和[npc2.name]都没被你吸引……"), null);
							} else {
								return new Response("并排后入(在下)", UtilText.parse(characterForSex, "[npc.Name]没被你吸引，不想参加三人行……"), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("并排后入(在下)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name]没被你吸引，[npc.she]和[npc2.name]都不愿意三人行……"), null);
							
						} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
							return new Response("并排后入(作为服从方)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name]没被[npc2.name]吸引，他们不想彼此接触，所以都不会参加三人行……"),
									null);

						} else if(!characterForSex.isAttractedTo(characterForSexSecondary)) {
							return new Response("并排后入(作为服从方)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc2.name]没被[npc2.name]吸引，他们不想彼此接触，所以都不会参加三人行……"),
									null);
							
						} else {
							return new ResponseSex("并排后入(在下)",
									UtilText.parse(characterForSex, characterForSexSecondary, "在[npc2.name]身边四肢跪地，这样[npc.name]就可以跪在你们身后，跟挨在一起的你们两个做爱了。"),
									null, null, null, null, null, null,
									true, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(characterForSex),
											Util.newArrayListOfValues(Main.game.getPlayer(), characterForSexSecondary),
											Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SIDE_BY_SIDE_AS_SUB_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(Main.game.getPlayer(), characterForSexSecondary));
								}
							};
						}
					
					}else if(index==10) {
						if(charactersPresent.size()<3) {
							return new Response("一受多攻(作为服从方)",
									"至少要有三个角色在场才能开始群交。",
									null);
							
						} else if (charactersPresent.stream().anyMatch(c->c.isDollStatue())) {
							return new Response("一受多攻(作为服从方)",
									UtilText.parse(charactersPresent.stream().filter(c->c.isDollStatue()).findFirst().get(),
											"[npc.name]是一个无法移动的玩偶，不能在做爱中占据支配地位……"),
									null);
							
						} else if (charactersPresent.stream().anyMatch(c->c.isAsleep())) {
							boolean soloSleeper = charactersPresent.stream().filter(c->c.isAsleep()).count()==1;
							String names = Util.charactersToStringListOfNames(charactersPresent.stream().filter(c->c.isAsleep()).collect(Collectors.toList()));
							return new Response("一受多攻(作为服从方)",
											names+"需要醒来才能群交！",
									null);
							
						} else {
							boolean isRape = charactersPresent.stream().anyMatch(c->!c.isAttractedTo(Main.game.getPlayer()));
							// Handle attraction/non-con:
							if(isRape) {
								List<GameCharacter> nonAttracted = new ArrayList<>();
								for(GameCharacter character : charactersPresent) {
									if(!character.isAttractedTo(Main.game.getPlayer())) {
										nonAttracted.add(character);
									}
								}
								String isAre = nonAttracted.size()==1?"is":"are";
								String them = nonAttracted.size()==1?UtilText.parse(nonAttracted.get(0), "[npc.herHim]"):"他们";
								return new Response("一受多攻(作为服从方)",
										"由于"+Util.charactersToStringListOfNames(nonAttracted)+" "+isAre+" 不被你吸引，你不能让"+them+"在群交中占据主导。",
										null);
							}
							
							List<GameCharacter> sexParticipants = new ArrayList<GameCharacter>(charactersPresent);
							return new ResponseSex(
									"一受多攻(作为服从方)",
									"让"+Util.charactersToStringListOfNames(sexParticipants)+"占据主导地位，并让她们与你开展群交。",
									null, null, null, null, null, null,
									true, false,
									getGenericSlaveSexManager(
											sexParticipants,
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(),
											true),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), sexParticipants.size()==3?"SEX_GANGBANG_AS_SUB_3":"SEX_GANGBANG_AS_SUB_4", sexParticipants)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(sexParticipants);
								}
							};
						}
						
					} else if(index==11) {
						if(characterForSexSecondary!=null) {
							return new ResponseEffectsOnly(
									UtilText.parse(characterForSex, "目标：<b style='color:"+characterForSex.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
									"循环切换选择群交角色。") {
								@Override
								public void effects() {
									if(charactersPresent.size()>1) {
										for(int i=0; i<charactersPresent.size();i++) {
											if(charactersPresent.get(i).equals(characterForSex)) {
												if(i==charactersPresent.size()-1) {
													characterForSex = charactersPresent.get(0);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSexSecondary = charactersPresent.get(1);
													}
												} else {
													characterForSex = charactersPresent.get(i+1);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSexSecondary = charactersPresent.get((i+2)<charactersPresent.size()?(i+2):0);
													}
													break;
												}
											}
										}
									}
									Main.game.updateResponses();
								}
							};
							
						} else {
							return new Response(
									UtilText.parse(characterForSex, "目标：<b>[npc.Name]</b>"),
									"循环切换选择群交目标角色。<br/>[style.italicsBad(若要解锁该动作，需要带着一名同伴！)]",
									null); 
						}
						
					} else if(index==12) {
						if(characterForSexSecondary!=null) {
							return new ResponseEffectsOnly(
									UtilText.parse(characterForSexSecondary, "次目标：<b style='color:"+characterForSexSecondary.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
									"循环切换选择群交次目标角色。") {
								@Override
								public void effects() {
									if(charactersPresent.size()>1) {
										for(int i=0; i<charactersPresent.size();i++) {
											if(charactersPresent.get(i).equals(characterForSexSecondary)) {
												if(i==charactersPresent.size()-1) {
													characterForSexSecondary = charactersPresent.get(0);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSex = charactersPresent.get(1);
													}
												} else {
													characterForSexSecondary = charactersPresent.get(i+1);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSex = charactersPresent.get((i+2)<charactersPresent.size()?(i+2):0);
													}
												}
												break;
											}
										}
									}
									Main.game.updateResponses();
								}
							};
							
						} else {
							return new Response(
									UtilText.parse(characterForSex, "次目标：<b>[npc.Name]</b>"),
									"循环切换选择群交次目标角色。<br/>[style.italicsBad(若要解锁该动作，需要带着一名同伴！)]",
									null);
						}
					}
				}
				return null;
				
			} else if(responseTab == 2) {
				if(isDollStatue()) {
					if(index == 1) {
						return new Response("暂停扮演雕像",
								UtilText.parse(getSlave(), "让[npc.name]暂时停止扮演雕像，这样你就可以管理[npc.herHim]了。"),
								SLAVE_START_NO_CONTENT) {
							@Override
							public void effects() {
								dollStatueInterrupted = true;
								Main.game.appendToTextEndStringBuilder("<p>");
									Main.game.appendToTextEndStringBuilder("想要与[npc.name]互动，而不想让[npc.herHim]继续像一尊毫无反应的雕像一样，你应该允许[npc.herHim]再次暂时自由移动。");
								Main.game.appendToTextEndStringBuilder("</p>");
								Main.game.appendToTextEndStringBuilder(getSlaveStartCoreContent());
							}
						};
						
					} else if(index==0) {
						return CompanionManagement.getManagementResponses(index);
					}
					return null;
					
				} else if(getSlave().isAsleep()) {
					if(index == 1) {
						return getWakeUpResponse();
						
					} else if(index==0) {
						return CompanionManagement.getManagementResponses(index);
					}
					return null;

				} else {
					return CompanionManagement.getManagementResponses(index);
				}
			
			} else {
				return null;
			}
		}
	};
	
	private static String getFooterText() {
		if(isDoll()) {
			return "";
		}
		return UtilText.parse(getSlave(),
				"<p><i>"
					+ (getSlave().isAttractedTo(Main.game.getPlayer())
						?"[npc.she]如饥似渴地盯着你的身体，你看出[npc.sheIs]被你吸引了……"
						:"[npc.she]似乎并没有被你吸引……")
				+ "</i></p>");
	}
	
	public static final DialogueNode SLAVE_PROGRESSION = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "与[npc.Name]交谈");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isDoll()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "你想要了解[npc.Name]的看法，问了问[npc.she]被创造时的记忆"
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(抱歉[pc.name]，但我已经记不清了)]，[npc.she]回答道，[npc.speech(我最初的记忆是，我被命令和其他十几个玩偶站在一起，等着被卖掉。)]"
						+ "</p>"
						+ "<p>"
							+ "看样子，[npc.Name]似乎无法提供任何赛拉特里克斯未曾告诉过你的信息，所以你决定不再就这个问题浪费时间提问……"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<i>还没做完！</i>" //TODO
						+ "</p>"
						+ "<p>"
							+ "你决定要和[npc.Name]谈一些更严肃的话题，于是你向[npc.herHim]询问了[npc.her]在成为你的奴隶之前的生活，"
							+ "[pc.speech(我想了解一下你的过去，[npc.name]。来这里之前，你的生活是怎样的？)]"
						+ "</p>"
						+ "<p>");
				
				switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append("[npc.her]的[npc.eyes]中流露出强烈的仇恨神情，[npc.she]迅速做出了无礼的回应，"
										+ "[npc.speech(滚你妈的！说得好像我会跟你谈这些似的！傻逼！)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append("虽然[npc.she]极力掩饰，但在[npc.she]说话时，你还是看到了[npc.her][npc.eyes]里明显的仇恨眼神，"
										+ "[npc.speech(我没做什么。真的没什么好说的了，[npc.pcName]。)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append("在[npc.she]回答时，[npc.she]顺从地竭力掩饰[npc.her][npc.eyes]中的仇恨神情，"
										+ "[npc.speech(其实这没什么好说的，[npc.pcName]。在成为你的财产之前，我过着平淡无奇的生活。你还需要问什么吗？)]");
								break;
						}
						break;
					case NEUTRAL:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append("虽然[npc.she]似乎并不讨厌你，但[npc.Name]显然不太愿意和你谈论[npc.her]的过去，于是叹了口气，"
										+ "[npc.speech(我不知道，[npc.pcName]，好像没什么好说的，真的。我们还是谈点别的吧，好吗？)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append("虽然[npc.she]似乎并不讨厌你，但[npc.Name]显然不太愿意和你谈论[npc.her]的过去，于是叹了口气，"
										+ "[npc.speech(抱歉[npc.pcName]，关于我的过去，真的没什么好说的。也许我能为你做点别的什么？)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append("虽然[npc.she]似乎并不讨厌你，但[npc.Name]显然不太愿意和你谈论[npc.her]的过去，只是简单地回应了一下，"
										+ "[npc.speech(这没什么好说的，[npc.pcName]。在成为你的奴隶之前，我的生活一帆风顺。我还能为你做什么吗？)]");
								break;
						}
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) { //TODO
							case DISOBEDIENT:
								UtilText.nodeContentSB.append("在被问及[npc.her]的过往时，[npc.her]几乎无法抑制激动的心情，[npc.Name]很快就做出了回答，"
										+ "[npc.speech(谢谢你的关心，[npc.pcName]！哦，但也许我们应该改天再讨论这个问题……)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append("你向[npc.Name]问及[npc.her]过去的生活，[npc.herHim]微笑着回答，"
										+ "[npc.speech(啊，[npc.pcName]，也许我们应该改天再讨论这个问题……)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append("被问及[npc.Name]的过往让[npc.her]显然很高兴，很快就做出了回答，"
										+ "[npc.speech(对不起，[npc.pcName]，我们得改天再谈这个……)]");
								break;
						}
						break;
				}
				UtilText.nodeContentSB.append("</p>");
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLAVE_MINOR = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "与[npc.Name]交谈");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isDoll()) {
				UtilText.nodeContentSB.append("<p>"
							+ "鉴于[npc.name]不仅仅是一个性玩偶，所以你决定与[npc.herHim]闲聊一会。"
						+ "</p>"
						+ "<p>"
							+ "[npc.name]以一种积极参与的方式回应你的问题和陈述，让你感觉就像是在与一个正常人交谈。"
							+ "你很快发现，[npc.she]实际上没讲什么，仅仅是对你言听计从。"
						+ "</p>"
						+ "<p>"
							+ "在注意到[npc.namePos]的局限性后，你结束了这次对话，随后开始思考接下来该做什么……"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append("<p>"
							+ "你决定尝试与[npc.name]闲聊几句，于是向[npc.herHim]提出了一系列问题，从[npc.sheIs]作为你的奴隶生活感觉如何开始，"
							+ "到[npc.she]如何看待御城区这奇特的奥术天气。");
				
				switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append("然而，无论你如何努力，你的谈话尝试都会遭到令人难以置信的粗鲁评价。"
										+ "</p>"
										+ "<p>"
										+ "意识到这样下去不会有结果，你放弃了与[npc.name]对话的尝试。"
										+ "当你转身离开时，[npc.she]皱起了眉头。"
										+ "[npc.speech(请您能滚蛋了吗？！)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append("然而，无论你如何努力尝试，你的谈话都会遭到简短、轻蔑的回应。"
										+ "</p>"
										+ "<p>"
										+ "意识到这样下去不会有结果，你放弃了与[npc.name]对话的尝试。"
										+ "当你转身离开时，[npc.she]皱起了眉头。"
										+ "[npc.speech(你说完了吗，[npc.pcName]？)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append("然而，无论你如何努力尝试，你的谈话都会遭到简短、轻蔑的回应。"
										+ "</p>"
										+ "<p>"
										+ "意识到这样下去不会有结果，你放弃了与[npc.name]对话的尝试。"
										+ "当你转身离开时，[npc.she]问道，"
										+ "[npc.speech(还有什么需要我帮忙的吗，[npc.pcName]？)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>由于[npc.Name]"
										+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
										+ "你，你想和对方套近乎的做法弊大于利！</i>"
								+ "</p>");
						break;
					case NEUTRAL:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											" 虽然[npc.she]一开始似乎对与你交谈不感兴趣，但[npc.name]还是友好地回答了你的每一个问题。"
											+ "从[npc.her]脸上慢慢绽放的笑容中，你可以看出[npc.she]很感激你试图让[npc.herHim]安心。"
										+ "</p>"
										+ "<p>"
											+ "你与[npc.name]聊了一会，决定结束对话。这时你的奴隶嘟囔了一句，"
											+ "[npc.speech(谢谢你跟我说话，[npc.pcName]……)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"虽然[npc.she]在与你交谈时显得有些犹豫，但[npc.name]还是友好地回答了你的每一个问题。"
										+ "从[npc.her]脸上慢慢绽放的笑容中，你可以看出[npc.she]很感激你试图让[npc.herHim]安心。"
									+ "</p>"
									+ "<p>"
										+ "你与[npc.name]聊了一会，决定结束对话。这时你的奴隶嘟囔了一句，"
										+ "[npc.speech(谢谢你，[npc.pcName]。我很高兴与你交谈……)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.she]顺从且友好地回答你的每一个问题。"
										+ "从[npc.her]脸上慢慢绽放的笑容中，你可以看出[npc.she]很感激你试图让[npc.herHim]安心。"
									+ "</p>"
									+ "<p>"
										+ "你与[npc.Name]聊了一会，决定结束对话。在这时，你的奴隶露出了笑容。"
										+ "[npc.speech(希望我的回答能让你满意，[npc.pcName]。)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>由于[npc.Name]"
										+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
										+ "你，你试图闲聊的举动正在让[npc.herHim]更喜欢你！</i>"
								+ "</p>");
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											"[npc.Name]满面笑容，热情地回答你的每一个问题，[npc.her]的态度更像是一位亲密的朋友，而不是你的奴隶。"
											+ "从[npc.her]的微笑和[npc.she]凝视你[pc.eyes]的眼神中，你可以看出[npc.name]非常感激你花时间和[npc.herHim]交谈。"
										+ "</p>"
										+ "<p>"
											+ "过了一会儿，你决定结束谈话，就在你结束谈话时，你的奴隶对你咧嘴一笑。"
											+ "[npc.speech(谢谢，[npc.pcName]！偶尔能和你聊聊天真的很开心！)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"[npc.Name]满面笑容，热情地回答你的每一个问题，但[npc.she]确保不会做得太过火，保持一点距离，因为[npc.she]努力表现得像个好奴隶。"
										+ "从[npc.her]的微笑和[npc.she]凝视你[pc.eyes]的眼神中，你可以看出[npc.name]非常感激你花时间和[npc.herHim]交谈。"
									+ "</p>"
									+ "<p>"
										+ "过了一会儿，你决定结束谈话，这时你的奴隶露出了笑容。"
										+ "[npc.speech(谢谢你，[npc.pcName]。感谢你抽出时间和我说话。)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.Name]努力控制住[npc.her]激动的声音，热情地回答你的每一个问题，"
												+ "尽管[npc.sheIs]小心翼翼地保持[npc.her]的镇定，竭尽全力表现得像个听话的奴隶。"
										+ "不过，[npc.she]无法完全抑制[npc.her]的微笑和[npc.she]渴望地仰望你[pc.eyes]的眼神，"
											+ "让你知道，[npc.she]非常感激你花时间与[npc.herHim]交谈。"
									+ "</p>"
									+ "<p>"
										+ "过了一会儿，你决定结束谈话，这时你的奴隶露出了笑容。"
										+ "[npc.speech(希望我的回答能让你满意，[npc.pcName]。)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>由于[npc.Name]"
										+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
										+ "你，你试图闲聊的举动大大有助于让[npc.herHim]更喜欢你！</i>"
								+ "</p>");
						break;
				}
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLAVE_ENCOURAGE = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "鼓励[npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDoll()) {
				String jobName = getCurrentJob().getName(getSlave());
				UtilText.nodeContentSB.append("<p>"
							+ "你想听听[npc.Name]对[npc.her]工作的想法，便问[npc.herHim]觉得工作怎么样"
							+ UtilText.generateSingularDeterminer(jobName)+""+jobName+"。"
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(我没想过，[pc.name]，)]玩偶回应道，[npc.speech(我只是来服务你的，任凭差遣。)]"
						+ "</p>"
						+ "<p>"
							+ "看起来在这个话题上，你从[npc.name]那里得不到太多信息……"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append("<p>");
				
				switch(getCurrentJob()) {
					case CLEANING:
						UtilText.nodeContentSB.append("希望鼓励[npc.Name]在工作时做到最好，作为你的 ");
						UtilText.nodeContentSB.append(Util.capitaliseSentence(SlaveJob.CLEANING.getName(getSlave())));
						UtilText.nodeContentSB.append("，你询问[npc.sheIs]发现了什么。");
						break;
					case IDLE:
						UtilText.nodeContentSB.append("虽然[npc.name]还没有被分配工作，但你还是要问问[npc.her]作为你的奴隶生活得怎么样。");
						break;
					case GARDEN:
						UtilText.nodeContentSB.append("为了鼓励[npc.name]在做园丁时尽心尽力，你问[npc.her]做得怎么样。");
						break;
					case SECURITY:
						UtilText.nodeContentSB.append("为了鼓励[npc.name]在做保安时尽心尽力，你问[npc.sheIs]觉得怎么样。");
						break;
					case KITCHEN:
						UtilText.nodeContentSB.append("为了鼓励[npc.Name]在厨房做厨师时尽心尽力，你问[npc.her]觉得怎么样。");
						break;
					case LAB_ASSISTANT:
						UtilText.nodeContentSB.append("为了鼓励[npc.Name]作为你的[lilaya.relation(pc)]的助手在莉莱雅的实验室工作时尽心尽力，你问[npc.her]觉得怎么样。");
						break;
					case LIBRARY:
						UtilText.nodeContentSB.append("为了鼓励[npc.name]在莉莱雅琳琅满目的图书馆担任图书管理员期间尽心尽力，你询问[npc.her]觉得怎么样。");
						break;
					case OFFICE:
						UtilText.nodeContentSB.append("为了鼓励[npc.Name]在办公室工作时尽心尽力，你问[npc.her]处理这些文书工作是否开心。");
						break;
					case TEST_SUBJECT:
						UtilText.nodeContentSB.append("你想鼓励[npc.name]在作为莉莱雅改造工作的试验品时尽全力做好[npc.her]的工作，于是问[npc.her]觉得怎么样。");
						break;
					case PUBLIC_STOCKS:
						UtilText.nodeContentSB.append("为了鼓励[npc.Name]在奴隶巷被公开利用的同时，尽最大努力做好[npc.her]的工作，你问[npc.her]感觉如何。");
						break;
					case PROSTITUTE:
						UtilText.nodeContentSB.append("为了鼓励[npc.name]在“天使之吻”做妓女时尽心尽力，你询问[npc.her]感觉如何。");
						break;
					case MILKING:
						UtilText.nodeContentSB.append("为了鼓励[npc.name]在挤奶间工作时尽心尽力，你问[npc.her]感觉如何。");
						break;
					case BEDROOM:
						UtilText.nodeContentSB.append("为了让[npc.nameIsFull]满意，你询问[npc.herHim]对于[npc.she]被指派在卧室里伺候你感觉如何。");
						break;
					case SPA:
						UtilText.nodeContentSB.append("为了让[npc.nameIsFull]开心，你询问[npc.herHim]对[npc.she]被分配到水疗中心工作的感受。");
						break;
					case SPA_RECEPTIONIST:
						UtilText.nodeContentSB.append("为了让[npc.nameIsFull]开心，你询问[npc.herHim]对[npc.she]被分配到水疗中心工作的感受。");
						break;
					case DINING_HALL:
						UtilText.nodeContentSB.append("希望鼓励[npc.Name]在工作时做到最好，作为你的 ");
						UtilText.nodeContentSB.append(Util.capitaliseSentence(SlaveJob.DINING_HALL.getName(getSlave())));
						UtilText.nodeContentSB.append("，你询问[npc.sheIs]发现了什么。");
						break;
					case DOLL_STATUE:
						break;
				}
				
				switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append("你话音刚落，不听话的奴隶就啐了出来，"
										+ "[npc.speech(滚你妈的！我讨厌这样，而且我在值班的时候根本没工作，所以去你妈了个逼的！)]"
										+ "</p>"
										+ "<p>"
										+ "从[npc.her]粗鲁的反应来看，很明显[npc.Name]不仅讨厌你，而且也不情愿过作为你的奴隶的生活。"
										+ "还没等你试着要求[npc.herHim]给你一个合适的答案，[npc.she]就朝你转过身咆哮起来，"
										+ "[npc.speech(你为什么不滚开去给莉莱雅舔鸡巴？)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append("你话音刚落，你的奴隶就做出了尖锐的回应，"
										+ "[npc.speech(我做我必须做的。我不喜欢这样，也不喜欢做你的奴隶，但我会做我必须做的。)]"
										+ "</p>"
										+ "<p>"
										+ "从[npc.her]急促的反应来看，[npc.Name]显然不太适应[npc.her]的奴隶生活。"
										+ "还没等你开口，[npc.she]就不耐烦地问，"
										+ "[npc.speech(还有什么事吗，[npc.pcName]？或者说你现在已经说完了？)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append("你话音刚落，听话的奴隶就迅速做出反应，"
										+ "[npc.speech([npc.pcName]让我做什么我就做什么。我会尽我所能履行我的职责，因为这是对我的期望。)]"
										+ "</p>"
										+ "<p>"
										+ "从[npc.her]粗鲁的反应中可以看出，虽然[npc.Name]会顺从地履行[npc.her]作为奴隶的职责，但[npc.she]并不喜欢你。"
										+ "还没等你开口，[npc.she]问道，"
										+ "[npc.speech(你还需要我做什么，[npc.pcName]？)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>由于[npc.Name]"
										+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
										+ "你，对[npc.her]的工作表现来说，兴趣只是起到了微不足道的作用！</i>"
								+ "</p>");
						break;
					case NEUTRAL:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append("你话音刚落，你不听话的奴隶就发出呜呜声，"
										+ "[npc.speech(我也没得选择啊。我是说，谢谢你的关心，但我是个奴隶，所以不管我喜不喜欢，你命令我做什么，我就得做什么……)]"
										+ "</p>"
										+ "<p>"
										+ "从[npc.her]粗暴诚实的反应来看，[npc.Name]显然还没有完全适应作为你的奴隶的生活。"
										+ "你思忖着如何回答，[npc.she]问，"
										+ "[npc.speech(所以，你想要什么？[npc.pcName]？)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append("你话音刚落，你的奴隶就回答，"
										+ "[npc.speech(还不错，[npc.pcName]。谢谢你的关心，你让我做什么我就做什么……)]"
										+ "</p>"
										+ "<p>"
										+ "从[npc.her]诚实的反应来看，很明显[npc.Name]对成为你的奴隶还有些保留意见。"
										+ "你思忖着如何回答，[npc.she]问，"
										+ "[npc.speech(还有什么需要我帮忙的吗，[npc.pcName]？)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
											" 你话音刚落，你的奴隶就回答，"
											+ "[npc.speech(我总是尽我所能，[npc.pcName]。谢谢您的关心，但请放心，我一定会尽我所能。)]"
										+ "</p>"
										+ "<p>"
											+ "从[npc.her]的反应来看，很明显[npc.Name]已经完全接受了[npc.her]作为你的奴隶的地位。"
											+ "[npc.her]的回答似乎有点冷淡，你意识到虽然[npc.she]并不恨你，但[npc.she]也并不完全爱你。"
											+ "你还没来得及发表评论，[npc.she]就继续说道，"
											+ "[npc.speech(我还能为你做什么，[npc.pcName]？)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>由于[npc.Name]"
										+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
										+ "你, 你对[npc.her]对工作表现出兴趣产生了明显的效果！</i>"
								+ "</p>");
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											"你话音刚落，你不听话的奴隶就笑了，"
											+ "[npc.speech(好吧，在这件事上我也别无选择，不是吗？哈哈！不过别担心，我爱你！我，我是说！我喜欢为你<i>工作</i>！)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.she]试图匆忙掩饰自己的失言，脸颊涨得通红，但即使没有意外表白，你也已经知道[npc.Name]深深地关心着你。"
											+ "从[npc.she]看你的眼神，到每次你对[npc.herHim]表示关注时[npc.her]脸上绽放的笑容，大家都清楚[npc.she]是多么迷恋你。"
											+ "还没等你开口，你的奴隶就赶紧试图把话题转移到让[npc.herHim]不那么尴尬的地方，"
											+ "[npc.speech(我还能为你做什么吗，[npc.pcName]？什么都行，尽管开口！)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"你一说完，你的奴隶就会微笑着回应，"
										+ "[npc.speech(一切都很顺利，谢谢你的关心，[npc.pcName]。我喜欢为你工作……)]"
									+ "</p>"
									+ "<p>"
										+ "当[npc.she]承认[npc.she]喜欢做你的奴隶时，[npc.her]的脸颊变得通红，但即使没有[npc.she]害羞的表现，你也已经知道[npc.Name]非常在乎你。"
										+ "从[npc.she]看你的眼神，到每次你对[npc.herHim]表示关注时[npc.her]脸上绽放的笑容，大家都清楚[npc.she]是多么迷恋你。"
										+ "还没等你开口，你的奴隶就赶紧试图把话题转移到让[npc.herHim]不那么尴尬的地方，"
										+ "[npc.speech(您还需要什么吗，[npc.pcName]？)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"你一说完，你的奴隶就会微笑着回应，"
										+ "[npc.speech(一切都很顺利，[npc.pcName]。我喜欢为你工作……)]"
									+ "</p>"
									+ "<p>"
										+ "当[npc.she]承认[npc.she]喜欢做你的奴隶时，[npc.her]的脸颊变得通红，但即使没有[npc.she]害羞的表现，你也已经知道[npc.Name]非常在乎你。"
										+ "从[npc.she]看你的眼神，到每次你对[npc.herHim]表示关注时[npc.her]脸上绽放的笑容，大家都清楚[npc.she]是多么迷恋你。"
										+ "还没等你开口，你的奴隶就赶紧试图把话题转移到让[npc.herHim]不那么尴尬的地方，"
										+ "[npc.speech(您还需要什么吗，[npc.pcName]？)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>由于[npc.Name]"
										+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
										+ "你, 显示你对[npc.her]工作的兴趣产生了重大影响！</i>"
								+ "</p>");
						break;
				}
			}
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLAVE_HUG = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "拥抱[npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDoll()) {
				UtilText.nodeContentSB.append("<p>"
							+ "出于给予[npc.name]一些肢体关怀的愿望，你[pc.step]上前，将[npc.herHim]紧紧拥抱在怀中。"
						+ "</p>"
						+ "<p>"
							+ "你的玩偶立刻作出了同样的回应，[npc.she]用[npc.her]的[npc.arms]环绕住你，将你拉近[npc.herHim]怀里。"
							+ "尽管这是你期待的回应，但[npc.namePos]的拥抱却缺乏温度和亲密感，因此你很快结束了拥抱并离开了[npc.herHim]。"
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(我希望这能满足您的需求，[pc.name]。)][npc.she]回答道，接下来你要对[npc.herHim]有何需求……"
						+ "</p>");
				
			} else {
				AffectionLevelBasic slaveAffection = AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()));
				
				boolean silly = Main.game.isSillyMode() && Math.random()<0.25f && slaveAffection==AffectionLevelBasic.NEUTRAL;
				
				UtilText.nodeContentSB.append("<p>");
				
				if(silly) {
					UtilText.nodeContentSB.append("想让[npc.Name]向你表达一些身体上的好感，你要求[npc.herHim]给你一个拥抱。");
				} else {
					UtilText.nodeContentSB.append("你认为[npc.Name]需要一些身体上的安慰，于是走上前去，伸手拉住[npc.herHim]，然后给[npc.herHim]一个紧紧的拥抱。");
				}
				
				switch(slaveAffection) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											"[npc.she]立刻试图挣脱你的控制，并大喊大叫，"
											+ "[npc.speech(放开我！滚开！)]"
										+ "</p>"
										+ "<p>"
											+ "你无视不听话的奴隶的抗议，将[npc.herHim]紧紧抱在怀里，呼吸着[npc.her][npc.scent]的气息。"
											+ "[npc.she]反抗，丝毫不为你安抚[npc.herHim]的行为所动。"
											+ "过了一小会儿，你终于松开了[npc.Name]，而[npc.she]则踉踉跄跄地后退并大喊大叫，"
											+ "[npc.speech(我不需要你的同情，你个"+(Main.game.getPlayer().isFeminine()?"婊子":"混蛋")+"！)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"[npc.she]本能地试图挣脱你的控制，并发出呜呜声，"
										+ "[npc.speech([npc.PcName]求你了！我不喜欢这样！)]"
									+ "</p>"
									+ "<p>"
										+ "你无视奴隶的抗议，将[npc.herHim]紧紧抱在怀里，呼吸着[npc.her][npc.scent]的气味。"
										+ "[npc.she]试图挣脱你的控制，显然对你安抚[npc.herHim]的方法不以为然，但你还是紧紧抓住，防止[npc.her]逃脱。"
										+ "过了一会儿，你终于松开了[npc.Name]，而[npc.she]则踉踉跄跄地后退，嘴里还在嘟囔着什么，"
										+ "[npc.speech(我甚至都不喜欢拥抱……)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"当你抱住[npc.herHim]时，[npc.she]愣住了，喃喃自语，声音中没有一丝真诚，"
										+ "[npc.speech(谢谢，[npc.pcName]……)]"
									+ "</p>"
									+ "<p>"
										+ "你无视奴隶的冷淡反应，将[npc.herHim]紧紧抱在怀里，呼吸着[npc.her][npc.scent]的气味。"
										+ "[npc.she]一动不动，显然对你安抚的方法不以为然，但你无视[npc.her]的抗拒的反应，继续把自己压在[npc.herHim]身上。"
										+ "过了一会儿，你终于松开了[npc.Name]，而[npc.she]则退后一步，低头看着地面。"
										+ "[npc.speech(谢谢，[npc.pcName]。你还需要什么？)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>由于[npc.Name]"
										+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
										+ "你，你试图强行进行身体接触的做法弊大于利！</i>"
								+ "</p>");
						break;
					case NEUTRAL:
						if(silly) {
							UtilText.nodeContentSB.append(
									"</p>"
									+ "<p>"
										+ "[npc.speech(~呃！好吧……)][npc.Name]松了口气，然后红着脸向你伸出[npc.her][npc.arms]。"
										+ "将你拥入[npc.her]的怀抱，她叹息道，[npc.speech(我猜你是我的小秀儿……过来……)]"
									+ "</p>"
									+ "<p>"
										+ "[pc.Stepping]向前，你无视[npc.namePos]的尴尬，让[npc.herHim]给了你一个大大的拥抱。"
										+ "[npc.she]轻轻拍了拍你的背，你明显感觉到[npc.she]只是在回应你的动作，因为这是[npc.herHim]所期望的。"
										+ "过了一小会儿，你终于松开了[npc.Name]，[npc.herHim]向后[npc.step]并喃喃自语，"
										+ "[npc.speech(这有点秀了……)]");
						} else {
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
											"[npc.she]半信半疑地回到你的怀抱，叹了口气，"
											+ "[npc.speech(谢谢，[npc.pcName]，我想偶尔来个拥抱也不错……)]"
										+ "</p>"
										+ "<p>"
											+ "你将[npc.name]拉近一点，将[npc.herHim]紧紧抱在怀里，呼吸[npc.her][npc.scent]的气味。"
											+ "[npc.she]轻轻拍了拍你的背，你明显感觉到[npc.she]只是在回应你的动作，因为这是[npc.herHim]所期望的。"
											+ "过了一小会儿，你终于松开了[npc.Name]，而[npc.she]则微笑着后退了一步。"
											+ "[npc.speech(很不错吧？你还想要什么吗？)]");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											"[npc.she]半信半疑地回到你的怀抱，叹了口气，"
											+ "[npc.speech(谢谢，[npc.pcName]……)]"
										+ "</p>"
										+ "<p>"
											+ "你将[npc.name]拉近一点，将[npc.herHim]紧紧抱在怀里，呼吸[npc.her][npc.scent]的气味。"
											+ "[npc.she]轻轻拍了拍你的背，你明显感觉到[npc.she]只是在回应你的动作，因为这是[npc.herHim]所期望的。"
											+ "过了一小会儿，你终于松开了[npc.Name]，而[npc.she]则微笑着后退了一步。"
											+ "[npc.speech(你还需要什么吗，[npc.pcName]？)]");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											"[npc.she]半信半疑地回到你的怀抱，叹了口气，"
											+ "[npc.speech(谢谢你，[npc.pcName]。)]"
										+ "</p>"
										+ "<p>"
											+ "你将[npc.name]拉近一点，将[npc.herHim]紧紧抱在怀里，呼吸[npc.her][npc.scent]的气味。"
											+ "[npc.she]轻轻拍了拍你的背，你明显感觉到[npc.sheIs]只是在回应你的动作，因为这是[npc.herHim]所期望的。"
											+ "过了一小会儿，你终于松开了[npc.Name]，而[npc.she]则微笑着后退了一步。"
											+ "[npc.speech(你还需要什么吗，[npc.pcName]？)]");
									break;
							}
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>由于[npc.Name]"
										+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
										+ "你，给[npc.herHim]一个安心的拥抱，对[npc.she]如何看待你产生了积极的影响！不过，以如此熟悉的方式对待[npc.she]，对[npc.her]的服从性有一点负面影响……</i>"
								+ "</p>");
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											"[npc.she]热切地回应你的拥抱，发出一声深深的叹息，"
											+ "[npc.speech(谢谢你，[npc.pcName]！)]"
										+ "</p>"
										+ "<p>"
											+ "你将[npc.name]拉近一点，将[npc.herHim]紧紧抱在怀里，呼吸[npc.her][npc.scent]的气味。"
											+ "[npc.she]依偎着你，[npc.her]的[npc.arms]环绕着你的后背，发出满足的叹息。"
											+ "从[npc.her]的反应来看，[npc.she]显然非常欣赏你的行为，在[npc.her]热情的鼓励下，你花了不少时间拥抱你的奴隶。"
										+ "</p>"
										+ "<p>"
											+ "过了一会儿，你终于松开了[npc.Name]，而[npc.she]则微笑着退后。"
											+ "[npc.speech(谢谢你，[npc.pcName]！我真的需要它……)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"[npc.she]热切地回应你的拥抱，发出一声深深的叹息，"
										+ "[npc.speech(谢谢，[npc.pcName]……)]"
									+ "</p>"
									+ "<p>"
										+ "你将[npc.name]拉近一点，紧紧抱在怀里，呼吸[npc.her][npc.scent]的气味。"
										+ "[npc.she]依偎着你，[npc.her]的[npc.arms]环绕着你的后背，发出满足的叹息。"
										+ "从[npc.her]的反应来看，[npc.she]显然非常欣赏你的行为，在[npc.her]热情的鼓励下，你花了不少时间拥抱你的奴隶。"
									+ "</p>"
									+ "<p>"
										+ "过了一会儿，你终于松开了[npc.Name]，而[npc.she]则微笑着退后。"
										+ "[npc.speech(谢谢你，[npc.pcName]。我真的需要这个……有什么我能为你做的吗？)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.she]热切地回应你的拥抱，发出一声深深的叹息，"
										+ "[npc.speech(谢谢，[npc.pcName]……)]"
									+ "</p>"
									+ "<p>"
										+ "你将[npc.name]拉近一点，将[npc.herHim]紧紧抱在怀里，呼吸[npc.her][npc.scent]的气味。"
										+ "[npc.she]依偎着你，[npc.her]的[npc.arms]环绕着你的后背，发出满足的叹息。"
										+ "从[npc.her]的反应来看，[npc.she]显然非常欣赏你的行为，在[npc.her]热情的鼓励下，你花了不少时间拥抱你的奴隶。"
									+ "</p>"
									+ "<p>"
										+ "过了一会儿，你终于松开了[npc.Name]，而[npc.she]则微笑着退后。"
										+ "[npc.speech(谢谢你，[npc.pcName]。有什么需要我帮忙的吗？)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>由于[npc.Name]"
										+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
										+ "你，给[npc.herHim]一个安心的拥抱，对[npc.she]如何看待你产生了巨大的积极影响！不过，如此亲昵地对待[npc.she]，会降低[npc.her]的服从性……</i>"
								+ "</p>");
						break;
				}
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLAVE_PETTINGS = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "爱抚[npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDoll()) {
				UtilText.nodeContentSB.append("<p>"
						+ "你想让[npc.Name]感到安心，于是伸[pc.hand]在[npc.her]的头上摸了一把。"
						+ "在[npc.she]还没反应过来之前，你开始抚摸[npc.her]的[npc.hair+]，然后向下轻轻挠在[npc.her]的[npc.ears]后面。"
					+ "</p>"
					+ "<p>"
						+ "你的玩偶在你的爱抚之下轻轻依偎过来，同时回应道：[npc.speech(谢谢你，[pc.name]。我希望我能很好地服务于你。)]"
					+ "</p>"
					+ "<p>"
						+ "虽然你继续抚摸[npc.name]几秒钟，但[npc.her]那冷淡且机械般的反应并不符合你期望的温情互动。"
						+ "来自你的玩偶那一片空白且毫无表情的凝视成了最后一根稻草，你收回了你的[pc.hand]，并然后开始思索下一步该怎么做……"
					+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append("<p>"
								+ "你想让[npc.Name]感到安心，于是伸[pc.hand]在[npc.her]的头上摸了一把。"
								+ "在[npc.she]还没反应过来之前，你开始抚摸[npc.her]的[npc.hair+]，然后向下轻轻挠在[npc.her]的[npc.ears]后面。"
							+ "</p>"
							+ "<p>");
				
				switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											"[npc.Name]被你的举动吓了一跳，过了一会儿才反应过来。[npc.she]一声怒喝，拍开你的[pc.hand]，后退几步，大声喊道，"
											+ "[npc.speech(干你娘的？！放开我！你他妈的让我自己待会！)]"
										+ "</p>");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"[npc.Name]被你的行为吓了一跳，过了一会儿才反应过来，不自在地哼了一声，[npc.she]向后退了一步，表示歉意，"
										+ "[npc.speech(对不起，[npc.pcName]，但是，你能不能不要这样做？)]"
									+ "</p>");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.Name]被你的举动吓了一跳，过了一会儿才反应过来，发出不舒服的呜呜声，"
											+ "[npc.she]一动不动，眯起[npc.eyes]，强迫自己忍受你多余的关注。"
										+ "过了一会儿，你停下手中的活儿，把[pc.hand]拿开，[npc.Name]松了一口气，问道，"
										+ "[npc.speech(还有什么需要我帮忙的吗，[npc.pcName]？)]"
									+ "</p>");
								break;
						}
						UtilText.nodeContentSB.append(
								"<p>"
									+ "<i>由于[npc.Name]"
										+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
										+ "你，你试图强行进行身体接触的做法弊大于利！</i>"
								+ "</p>");
						break;
					case NEUTRAL:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											"[npc.Name]被你的举动吓了一跳，过了一会儿才反应过来，叹了口气，[npc.she]把[npc.her]的头偏向一边。"
											+ "[npc.speech(感觉有点不错……继续不要停！)]"
										+ "</p>"
										+ "<p>"
											+ "你按照[npc.she]的要求，继续抚摸和拍打[npc.her]的头好一会儿。"
											+ "最后，你觉得[npc.name]已经享受够了，于是把[pc.hand]拿开，看着你的奴隶发出满足的喘息，你笑了。"
										+ "</p>");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"[npc.Name]被你的举动吓了一跳，过了一会儿才反应过来，叹了口气，[npc.she]把[npc.her]的头偏向一边。"
										+ "[npc.speech(感觉还不错……谢谢你，[npc.pcName]……)]"
									+ "</p>"
									+ "<p>"
										+ "在[npc.her]反应的鼓励下，你继续抚摸和拍打[npc.her]的头，持续了好一会儿。"
										+ "最后，你觉得[npc.name]已经享受够了，于是把[pc.hand]拿开，看着你的奴隶发出满足的喘息，你笑了。"
									+ "</p>");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.Name]被你的举动吓了一跳，过了一会儿才反应过来，叹了口气，[npc.she]把[npc.her]的头偏向一边。"
										+ "[npc.speech(谢谢，[npc.pcName]……)]"
									+ "</p>"
									+ "<p>"
										+ "在[npc.her]反应的鼓励下，你继续抚摸和拍打[npc.her]的头，持续了好一会儿。"
										+ "最后，你觉得[npc.name]已经享受够了，于是把[pc.hand]拿开，看着你的奴隶发出满足的喘息，你笑了。"
									+ "</p>");
								break;
						}
						UtilText.nodeContentSB.append(
								"<p>"
									+ "<i>由于[npc.Name]"
										+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
										+ "你，给予[npc.herHim]如此亲密的身体接触，让[npc.her]更喜欢你了！不过，受到如此熟悉的对待对[npc.her]的服从性产生了一点负面影响……</i>"
								+ "</p>");
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											"[npc.Name]发出一声充满爱意的喘息，歪了歪头，向你走来。"
											+ "[npc.speech(感觉真好……继续不要停！)]"
										+ "</p>"
										+ "<p>"
											+ "你按照[npc.she]的要求，继续抚摸和拍打[npc.her]的头好一会儿。"
											+ "最后，你觉得[npc.name]已经享受够了，于是把[pc.hand]拿开，看着你的奴隶发出满足的喘息，你笑了。"
										+ "</p>");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
											"[npc.Name]发出一声充满爱意的喘息，歪了歪头，向你走来。"
											+ "[npc.speech(谢谢你，[npc.pcName]！感觉真的很好！请不要停下来！)]"
										+ "</p>"
										+ "<p>"
											+ "你按照[npc.she]的要求，继续抚摸和拍打[npc.her]的头好一会儿。"
											+ "最后，你觉得[npc.name]已经享受够了，于是把[pc.hand]拿开，看着你的奴隶发出满足的喘息，你笑了。"
										+ "</p>");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
											"[npc.Name]发出一声充满爱意的喘息，歪了歪头，向你走来。"
											+ "[npc.speech(谢谢你，[npc.pcName]！)]"
										+ "</p>"
										+ "<p>"
											+ "在[npc.her]反应的鼓励下，你继续抚摸和拍打[npc.her]的头，持续了好一会儿。"
											+ "最后，你觉得[npc.name]已经享受够了，于是把[pc.hand]拿开，看着你的奴隶发出满足的喘息，你笑了。"
										+ "</p>");
								break;
						}
						UtilText.nodeContentSB.append(
								"<p>"
									+ "<i>由于[npc.Name]"
										+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
										+ "你，给予[npc.herHim]如此亲密的身体接触，让[npc.her]更喜欢你了！不过，受到如此熟悉的对待对[npc.her]的服从性产生了一点负面影响……</i>"
								+ "</p>");
						break;
				}
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLAVE_PRESENT = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "给[npc.Name]礼物");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
							+ "你认为[npc.Name]值得在这个圣诞节收到一份礼物，于是将礼物递给了[npc.herHim]。"
							+ "[pc.speech(这是给你的，[npc.name]。圣诞快乐)]"
						+ "</p>"
						+ "<p>");
			
			switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
				case DISLIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"你把礼物塞进[npc.Name][npc.hands]里，[npc.she]喃喃自语，"
										+ "[npc.speech(给，给我的？你知道我还是恨你的……)]"
									+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"当你把礼物塞进[npc.her][npc.hands]里的时候，[npc.she]喃喃自语道，"
									+ "[npc.speech(给，给我的？那我就收下了……)]"
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"[npc.Name]完全措手不及，不知道该如何回应。你把礼物塞进[npc.her][npc.hands]里。[npc.she]镇定下来，冷冷地说，"
									+ "[npc.speech(如果这是你的所想的话，我会接受的，[npc.pcName]。)]"
								+ "</p>");
							break;
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>尽管[npc.she]"
									+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ "你，[npc.name]很高兴能得到一份礼物！</i>"
							+ "</p>");
					break;
				case NEUTRAL:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"当你把礼物塞进[npc.her][npc.hands]里的时候，[npc.she]对你微笑。"
										+ "[npc.speech(给-给我的？谢谢你，[npc.pcName]！)]"
									+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"当你把礼物塞进[npc.her][npc.hands]里的时候，[npc.she]对你微笑。"
									+ "[npc.speech(给-给我的？谢谢你，[npc.pcName]！)]"
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"你把礼物塞进[npc.Name][npc.hands]里，[npc.she]对你微笑。"
									+ "[npc.speech(谢谢你，[npc.pcName]，我也祝你圣诞快乐！)]"
								+ "</p>");
							break;
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>虽然[npc.Name]很高兴能得到礼物，但受到如此友好的对待降低了[npc.her]的服从性……</i>"
							+ "</p>");
					break;
				case LIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"当你把礼物塞进[npc.her][npc.hands]里的时候，[npc.she]迸发出一阵狂喜，"
										+ "[npc.speech([npc.PcName]！非常感谢！也祝你圣诞快乐)]"
									+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"当你把礼物塞进[npc.her][npc.hands]里的时候，[npc.she]迸发出一阵狂喜，"
									+ "[npc.speech([npc.PcName]！非常感谢！也祝你圣诞快乐)]"
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"当你把礼物塞进[npc.her][npc.hands]里的时候，[npc.she]向你投来赞许的目光。"
									+ "[npc.speech(谢谢你，[npc.pcName]！也祝你圣诞快乐！)]"
								+ "</p>");
							break;
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>虽然[npc.Name]很高兴能得到礼物，但受到如此友好的对待降低了[npc.her]的服从性……</i>"
							+ "</p>");
					break;
			}
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	private static boolean isSlaveNaked() {
		return getSlave().isCoverableAreaVisible(CoverableArea.ANUS)
				&& getSlave().isCoverableAreaVisible(CoverableArea.NIPPLES)
				&& getSlave().isCoverableAreaVisible(CoverableArea.PENIS)
				&& getSlave().isCoverableAreaVisible(CoverableArea.VAGINA);
	}
	
	public static final DialogueNode SLAVE_INSPECT = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "审视[npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
						"<p>"
							+ (isSlaveNaked()
									?"你决定要检查一下[npc.namePos]的裸体，于是命令[npc.herHim]退后一步，这样你就能更好地观察[npc.herHim]。"
									:"你决定检查一下[npc.namePos]的身体，于是命令[npc.herHim]脱光衣服，然后退后一步，以便更好地观察[npc.herHim]。")
						+ "</p>"
						+ "<p>");
			String legsSpreading = getSlave().hasLegs()
					?"张开[npc.her]的[npc.legs]并展示[npc.her]"
					:"展示给你[npc.her]";
			
			if(isDoll()) {
				UtilText.nodeContentSB.append(
						(isSlaveNaked()
								?"[npc.She]立即回应了你的命令，顺从地后退一步并询问,"
								:"[npc.She]立即响应你的命令，顺从地将[npc.her]的衣服丢弃在了地板上。"
									+ "当[npc.she]脱下[npc.her]的最后一件衣服时，[npc.she]问道，")
						+ "[npc.speech(你满意了吗，[npc.pcName]？)]"
					+ "</p>"
					+ "<p>"
						+ "你给予了肯定的回答，然后命令[npc.Name]在你面前展示自己；[npc.she]再次尽职尽责地执行了这一命令。"
						+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
						+ "最后，你命令你的玩偶展示颈部后面的接口，并检查确保其处于完美状态。"
					+ "</p>"
					+ "<p>"
						+ "你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")+"，然后[npc.she]毫无怨言地再次照你说的做了。"
					+ "</p>");
				
			} else {
				if(getSlave().getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() || getSlave().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isPositive()) {
					switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
						case DISLIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
												"[npc.she]听到你的命令，生气地皱起了眉头。但[npc.she]意识到自己别无选择，只好勉强服从。"
												+ (isSlaveNaked()
														?"[npc.she]双目圆睁，怒视着你，咆哮道，"
														:"[npc.she]咆哮着脱下衣服，")
												+ "[npc.speech(该死的"+(Main.game.getPlayer().isFeminine()?"婊子":"混球")+"！继续！仔细看看你的<i>财产</i>吧，你这个病态的混蛋！)]"
											+ "</p>"
											+ "<p>"
												+ "尽管[npc.namePos]言辞激烈，但你还是从[npc.her]的声音中听出了一丝兴奋，"
													+ "你命令[npc.herHim]在你面前露出，[npc.she]几乎没有抵抗。"
												+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
												+"你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")
													+"，而尽管这让你那不顺从的奴隶又发表了一句恼怒的言论，"
													+ "你确信[npc.she]在偷偷享受向你展示自己。"
											+ "</p>");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											"听到你的命令，[npc.she]无奈地叹了口气，但还是努力控制住[npc.her]的情绪，勉强服从命令。"
											+ (isSlaveNaked()
													?"瞪着你，[npc.she]冷笑道："
													:"[npc.she]恶声恶气地脱下衣服，")
											+ "[npc.speech(很好，<i>[npc.pcName]</i>。)]"
										+ "</p>"
										+ "<p>"
											+ "尽管[npc.her]的语气略显叛逆，但你还是从[npc.namePos]的声音中听出了一丝快感，"
												+ "你命令[npc.herHim]在你面前展示自己，[npc.she]意外地几乎没抵抗。"
											+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
											+"你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")
												+"，而从[npc.her]失望的叹息声中，你可以看出[npc.she]很喜欢向你展示[npc.herself]。"
										+ "</p>");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											(isSlaveNaked()
													? "[npc.she]立即服从你的命令，但你看到[npc.her][npc.eyes]里明显流露出憎恨的神情，[npc.her]顺从地后退并问道，"
													: "[npc.she]顺从地把[npc.her]的衣服扔到地上，[npc.her]立即移动脚步去服从你的命令，但你看到了[npc.her][npc.eyes]中明显的仇恨神情。"
														+ "当[npc.she]脱下[npc.her]的最后一件衣服时，[npc.she]问道，")
											+ "[npc.speech(你满意了吗，[npc.pcName]？)]"
										+ "</p>"
										+ "<p>"
											+ "你给予了肯定的回答，然后命令[npc.Name]在你面前展示自己；[npc.she]再次尽职尽责地执行了这一命令。"
											+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
											+ "你对[npc.Name]的外表感到满意，便让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")
												+"，然后，[npc.she]毫无怨言地再次照你说的做了。"
											+ "从[npc.her]脸颊泛红的样子可以看出，[npc.she]很喜欢向你展示[npc.herself]。"
										+ "</p>");
									break;
							}
							break;
						case NEUTRAL:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
												"[npc.she]听到你的命令后，发出了欢快的叫声，并迅速行动起来服从命令。"
												+ (isSlaveNaked()
														?"对着你露出诱人的微笑，[npc.she][npc.moansVerb]道，"
														:"[npc.she][npc.moansVerb]，脱下衣服，")
												+ "[npc.speech(~姆姆姆~ 希望你和我一样喜欢这个，[npc.pcName]……)]"
											+ "</p>"
											+ "<p>"
												+ "你无视[npc.her]的话语，命令[npc.Name]在你面前展示自己；[npc.she]再次迫不及待地执行了这一命令。"
												+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
												+"你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")
												+"这引来了你不听话的奴隶的一声失望的叹息。"
											+ "</p>");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											"[npc.she]听到你的命令，高兴地叫了一声，但还是努力控制住[npc.her]的情绪，迅速行动起来服从命令。"
											+ (isSlaveNaked()
													?"对着你露出诱人的微笑，[npc.she][npc.moansVerb]道，"
													:"[npc.she][npc.moansVerb]，脱下衣服，")
											+ "[npc.speech(我希望这能让你满意，[npc.pcName]。)]"
										+ "</p>"
										+ "<p>"
											+ "在[npc.she]脱光衣服后，你命令[npc.Name]在你面前展示自己；[npc.she]再次迫不及待地执行了这一命令。"
											+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
											+"你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")+"，然后[npc.she]失望地叹了口气，照你的吩咐做了。"
										+ "</p>");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											(isSlaveNaked()
													?"[npc.she]顺从地后退，你看到[npc.her]的[npc.eyes]里流露出明显的兴奋神情，问道，"
													:"[npc.she]顺从地把[npc.her]的衣服脱到地上，你看到[npc.her]的[npc.eyes]里流露出明显的兴奋神情。"
														+ "当[npc.she]脱下[npc.her]的最后一件衣服时，[npc.she]问道，")
											+ "[npc.speech(你满意了吗，[npc.pcName]？)]"
										+ "</p>"
										+ "<p>"
											+ "你给予了肯定的回答，然后命令[npc.Name]在你面前展示自己；[npc.she]再次尽职尽责地执行了这一命令。"
											+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
											+ "你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")+"，然后[npc.she]毫无怨言地再次照你说的做了。"
											+ "从[npc.her]脸颊泛红的样子可以看出，[npc.she]很喜欢向你展示[npc.herself]。"
										+ "</p>");
									break;
							}
							break;
						case LIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
												"[npc.she]听到你的命令后高兴地叫了一声，并迅速行动起来服从命令。"
												+ (isSlaveNaked()
														?"对着你露出诱人的微笑，[npc.she][npc.moansVerb]道，"
														:"[npc.she][npc.moansVerb]，脱下衣服，")
												+ "[npc.speech(~姆姆姆~被迫这么做真是有失身份……我喜欢……)]"
											+ "</p>"
											+ "<p>"
												+ "你无视[npc.her]的话语，命令[npc.Name]在你面前展示自己；[npc.she]再次高兴地执行了这一命令。"
												+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
												+"你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")
													+"这引来了你不听话的奴隶的一声失望的叹息。"
											+ "</p>");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											"[npc.she]听到你的命令，高兴地叫了一声，但还是努力控制住情绪，服从命令，迅速行动起来。"
											+ (isSlaveNaked()
													?"对着你露出诱人的微笑，[npc.she][npc.moansVerb]道，"
													:"[npc.she][npc.moansVerb]，脱下衣服，")
											+ "[npc.speech(我希望你喜欢，[npc.pcName]……)]"
										+ "</p>"
										+ "<p>"
											+ "你无视[npc.her]充满情欲的语气，命令[npc.Name]在你面前走来走去；[npc.she]再次愉快地执行了这一命令。"
											+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
											+"你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")+"，然后[npc.she]失望地叹了口气，照你的吩咐做了。"
										+ "</p>");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											(isSlaveNaked()
													?"[npc.she]顺从地后退，你看到[npc.her]的[npc.eyes]里流露出明显的兴奋神情，问道，"
													:"[npc.she]顺从地把[npc.her]的衣服脱到地上，你看到[npc.her]的[npc.eyes]里流露出明显的兴奋神情。"
														+ "当[npc.she]脱下[npc.her]的最后一件衣服时，[npc.she]问道，")
											+ "[npc.speech(你满意了吗，[npc.pcName]？)]"
										+ "</p>"
										+ "<p>"
											+ "你给予了肯定的回答，然后命令[npc.Name]在你面前展示自己；[npc.she]再次尽职尽责地执行了这一命令。"
											+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
											+ "你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")+"，然后[npc.she]毫无怨言地再次照你说的做了。"
											+ "从[npc.her]脸颊泛红的样子可以看出，[npc.she]很喜欢向你展示[npc.herself]。"
										+ "</p>");
									break;
							}
							break;
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>[npc.Name]喜欢这样展示[npc.herself]，[npc.her]对你的好感和[npc.her]的服从性都会增加！</i>"
							+ "</p>");
					
				} else {
					switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
						case DISLIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
												"[npc.she]听到你的命令，生气地皱起了眉头。但[npc.she]意识到自己别无选择，只好勉强服从。"
												+ (isSlaveNaked()
														?"[npc.she]双目圆睁，怒视着你，咆哮道，"
														:"[npc.she]咆哮着脱下衣服，")
												+ "[npc.speech(该死的"+(Main.game.getPlayer().isFeminine()?"婊子":"混球")+"！继续！仔细看看你的<i>财产</i>吧，你这个病态的混蛋！)]"
											+ "</p>"
											+ "<p>"
												+ "你无视[npc.her]的反叛言论，命令[npc.Name]在你面前展示自己；[npc.she]再次勉强地执行了这一命令。"
												+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
												+"你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前站起来":"重新穿上衣服")
													+"你不听话的奴隶[npc.lips]之间又发出了一句愤怒的话语。"
											+ "</p>");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											"听到你的命令，[npc.she]无奈地叹了口气，但还是努力控制住[npc.her]的情绪，勉强服从命令。"
												+ (isSlaveNaked()
														?"瞪着你，[npc.she]冷笑道："
														:"[npc.she]恶声恶气地脱下衣服，")
											+ "[npc.speech(很好，<i>[npc.pcName]</i>。)]"
										+ "</p>"
										+ "<p>"
											+ "你无视[npc.her]略带反抗的语气，命令[npc.Name]在你面前展示自己；[npc.she]再次勉强执行了这一命令。"
											+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
											+"你对[npc.Name]的外表感到满意，便让[npc.her]"+(isSlaveNaked()?"在你面前重新起身":"重新穿上衣服")+"[npc.she]解脱般舒了口气，遵从了你的命令。"
										+ "</p>");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											(isSlaveNaked()
													? "[npc.she]立即服从你的命令，但你看到[npc.her][npc.eyes]里明显流露出憎恨的神情，[npc.her]顺从地后退并问道，"
													: "[npc.she]顺从地把[npc.her]的衣服扔到地上，[npc.her]立即移动脚步去服从你的命令，但你看到了[npc.her][npc.eyes]中明显的仇恨神情。"
														+ "当[npc.she]脱下[npc.her]的最后一件衣服时，[npc.she]问道，")
											+ "[npc.speech(你满意了吗，[npc.pcName]？)]"
										+ "</p>"
										+ "<p>"
											+ "你给予了肯定的回答，然后命令[npc.Name]在你面前展示自己；[npc.she]再次尽职尽责地执行了这一命令。"
											+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
											+ "你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")+"，然后[npc.she]毫无怨言地再次照你说的做了。"
										+ "</p>");
									break;
							}
							break;
						case NEUTRAL:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
												"[npc.she]听到你的命令，慌张地叫了一声，但意识到[npc.she]在这件事上真的别无选择，只好勉强服从命令。"
												+ (isSlaveNaked()
														?"[npc.her]摆出最哀求的表情，抱怨道，"
														:"[npc.she]抱怨着脱下衣服，")
												+ "[npc.speech(我真的必须这么做吗？被迫这样做有点有辱人格……)]"
											+ "</p>"
											+ "<p>"
												+ "你无视[npc.her]的话语，命令[npc.Name]在你面前展示自己；[npc.she]再次勉强执行了这一命令。"
												+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
												+"你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")
													+"你不听话的奴隶的[npc.lips]间发出一声如释重负的叹息。"
											+ "</p>");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											"[npc.she]听到你的命令后，发出了一声慌乱的叫喊，但还是努力控制住[npc.her]的情绪，勉强服从命令。"
											+ (isSlaveNaked()
													?"[npc.her]摆出最哀求的表情，抱怨道，"
													:"[npc.she]抱怨着脱下衣服，")
											+ "[npc.speech(给我一点时间，[npc.pcName]。)]"
										+ "</p>"
										+ "<p>"
											+ "在[npc.sheIs]被剥光衣服后，你命令[npc.Name]在你面前展示自己；[npc.she]再次不情愿地执行了这个命令。"
											+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
											+"你对[npc.Name]的外表感到满意，便让[npc.her]"+(isSlaveNaked()?"在你面前重新起身":"重新穿上衣服")+"[npc.she]解脱般舒了口气，遵从了你的命令。"
										+ "</p>");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											(isSlaveNaked()
													?"[npc.she]顺从地后退，你看到[npc.her]的[npc.eyes]里流露出明显的痛苦神情，问道，"
													:"[npc.she]顺从地把[npc.her]的衣服脱到地上，你看到[npc.her]的[npc.eyes]里流露出明显的痛苦神情。"
														+ "当[npc.she]脱下[npc.her]的最后一件衣服时，[npc.she]问道，")
											+ "[npc.speech(你满意了吗，[npc.pcName]？)]"
										+ "</p>"
										+ "<p>"
											+ "你给予了肯定的回答，然后命令[npc.Name]在你面前展示自己；[npc.she]再次尽职尽责地执行了这一命令。"
											+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
											+ "你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")+"，然后[npc.she]毫无怨言地再次照你说的做了。"
										+ "</p>");
									break;
							}
							break;
						case LIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
												"[npc.she]听到你的命令，伤心地哭出来，但意识到[npc.she]在这件事上真的别无选择，只好勉强服从命令。"
												+ (isSlaveNaked()
														?"[npc.her]摆出最哀求的表情，抱怨道，"
														:"[npc.she]抱怨着脱下衣服，")
												+ "[npc.speech(你知道吗，被迫这样做有点有辱人格……我以为你喜欢我的……)]"
											+ "</p>"
											+ "<p>"
												+ "你无视[npc.her]的话语，命令[npc.Name]在你面前展示自己；[npc.she]再次勉强执行了这一命令。"
												+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
												+"你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")
													+"你不听话的奴隶的[npc.lips]间发出一声如释重负的叹息。"
											+ "</p>");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											"当[npc.she]听到你的命令时，[npc.she]发出了悲伤的哭声，但她努力控制住[npc.her]的情绪，慢慢地走去服从命令。"
											+ (isSlaveNaked()
													?"[npc.her]摆出最哀求的表情，抱怨道，"
													:"[npc.she]抱怨着脱下衣服，")
											+ "[npc.speech(我以为你喜欢我，[npc.pcName]……但如果这是你想要的……)]"
										+ "</p>"
										+ "<p>"
											+ "你无视[npc.her]略带反抗的抗议，命令[npc.Name]在你面前展示自己；[npc.she]再次不情愿地执行了这一命令。"
											+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
											+"你对[npc.Name]的外表感到满意，便让[npc.her]"+(isSlaveNaked()?"在你面前重新起身":"重新穿上衣服")+"[npc.she]解脱般舒了口气，遵从了你的命令。"
										+ "</p>");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											(isSlaveNaked()
													?"[npc.she]顺从地后退，你看到[npc.her]的[npc.eyes]里流露出明显的痛苦神情，问道，"
													:"[npc.she]顺从地把[npc.her]的衣服脱到地上，你看到[npc.her]的[npc.eyes]里流露出明显的悲伤神情。"
														+ "当[npc.she]脱下[npc.her]的最后一件衣服时，[npc.she]问道，")
											+ "[npc.speech(你满意了吗，[npc.pcName]？)]"
										+ "</p>"
										+ "<p>"
											+ "你给予了肯定的回答，然后命令[npc.Name]在你面前展示自己；[npc.she]再次尽职尽责地执行了这一命令。"
											+ "在这之后，你让[npc.herHim]"+legsSpreading+partInspection()
											+ "你对[npc.Name]的外表感到满意，让[npc.her]"+(isSlaveNaked()?"在你面前重新站起来":"重新穿上衣服")+"，然后[npc.she]毫无怨言地再次照你说的做了。"
										+ "</p>");
									break;
							}
							break;
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>这并没有改变[npc.name]"
									+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ "你的事实，[npc.she]被强行脱光衣服，像一块肉一样在你面前展示自己，这对[npc.her]对你的好感产生了巨大的负面影响，但同时也会增加[npc.her]的服从性！</i>"
							+ "</p>");
				}
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	private static String partInspection() {
		if(getSlave().hasPenis()) {
			if(getSlave().hasVagina()) {
				return ("[npc.penis+]和[npc.vagina+]供你检查。");
			} else {
				return ("[npc.penis+]供你检查。");
			}
			
		} else if(getSlave().hasVagina()) {
			return ("[npc.vagina+]供你检查。");
			
		} else {
			return ("无性别的下体供你检查。");
		}
	}
	
	
	public static final DialogueNode SLAVE_SPANKING = new DialogueNode("", "", true) { //TODO
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "打[npc.Name]的屁股");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDoll()) {
				UtilText.nodeContentSB.append("<p>"
						+ "你认为有必要让[npc.name]记住[npc.her]的身份地位，因此在坐下之后，你命令[npc.herHim]脱掉衣物并在你的膝上弯腰屈身。"
					+ "</p>"
					+ "<p>"
						+ "[npc.She]立即遵从你的命令，乖乖地将[npc.her]的衣服放到地上。"
						+ "[npc.she]脱下[npc.her]的最后一件衣服，[npc.she]问道，"
						+ "[npc.speech(你满意了吗，[npc.pcName]？)]"
					+ "</p>"
					+ "<p>"
						+ "你给出了肯定的回答，然后再次命令[npc.name]趴在你的膝盖上；[npc.she]立刻遵从了这一命令。"
						+ "[npc.she]躺在你的腿上并展现出[npc.her][npc.ass+]，你迅速抬起[pc.hand]，重重地拍打[npc.her]那裸露的臀部。"
						+ "一阵类似机器的吱啦声伴随着硅胶被打时的清脆声响，这使得你再次出手打击了[npc.her]的[npc.ass]。"
					+ "</p>"
					+ "<p>"
						+ "你继续这样做了片刻；但你的玩偶只是顺从地趴在你的膝上，任由你一掌又一掌地拍打在[npc.her]那柔弱的臀部上。"
						+ "尽管[npc.she]在你的惩罚下以尖叫和喘息着回应，但你总觉得[npc.she]是为了迎合你而做出这样的反应，实际上这种惩罚并未对[npc.herHim]造成任何伤害。"
						+ "见此你只有结束这场惩戒……"
					+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append("<p>"
								+ "你觉得[npc.Name]可以回想一下谁是老大，于是，你坐下来，命令[npc.herHim]脱光衣服，弯腰趴在你腿上。"
							+ "</p>"
							+ "<p>");
	
				switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											"当[npc.her]听到你命令[npc.herHim]做什么时，[npc.she]嘴里发出愤怒的咆哮声，但是"
													+ "[npc.she]明白[npc.she]别无选择，只好勉强服从。"
											+ "[npc.she]恶声恶气慢慢脱掉[npc.her]的衣服，"
											+ "[npc.speech(你这该死的"+(Main.game.getPlayer().isFeminine()?"婊子":"混球")+"！我真他妈的恨死你了！)]"
										+ "</p>"
										+ "<p>"
											+ "你无视[npc.her]的无礼言论，再次命令[npc.Name]跪下；[npc.she]再次尽可能磨蹭地执行命令。"
											+ "[npc.she]趴在你大腿上，展示[npc.her][npc.ass+]，你发出胜利的笑声，然后迅速用[pc.hand]扇[npc.her]裸露的屁股。"
											+ "一声尖叫响起，和着肉体被拍打的声音，促使你再次打向[npc.her]的[npc.ass]。"
										+ "</p>"
										+ "<p>"
											+ "你继续这样做了一会儿；你的奴隶伏在大腿上扭动着，尖叫着，你一下又一下地打着[npc.her]脆弱的脊背。"
											+ "最终，你觉得[npc.sheIs]已经得到了教训，于是你命令[npc.herHim]站起来，重新穿上[npc.her]的衣服。"
										+ "</p>");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"当[npc.she]听到你命令[npc.herHim]做什么时，[npc.she]发出了一声惊慌失措的叫声，但是"
												+ "[npc.her]努力控制的情绪，勉强做出服从的举动。"
										+ "[npc.she]慢慢脱下[npc.her]的衣服，叹了口气，"
										+ "[npc.speech(这需要一点点时间，<i>[npc.pcName]</i>。)]"
									+ "</p>"
									+ "<p>"
										+ "你没有理会[npc.her]略带反抗的语气，再次命令[npc.Name]把自己趴到你的膝盖上；[npc.she]尽可能磨蹭地执行了这一命令。"
										+ "[npc.she]趴在你大腿上，展示[npc.her][npc.ass+]，你发出胜利的笑声，然后迅速用[pc.hand]扇[npc.her]裸露的屁股。"
										+ "一声尖叫响起，和着肉体被拍打的声音，促使你再次打向[npc.her]的[npc.ass]。"
									+ "</p>"
									+ "<p>"
										+ "你继续这样做了一会儿；你的奴隶伏在大腿上扭动着，尖叫着，你一下又一下地打着[npc.her]脆弱的脊背。"
										+ "最终，你觉得[npc.sheIs]已经得到了教训，于是你命令[npc.herHim]站起来，重新穿上[npc.her]的衣服。"
									+ "</p>");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.she]顺从地把[npc.her]的衣服扔到地上，立即移动脚步去服从你的命令，但你看到了[npc.her]的[npc.eyes]中明显的仇恨神情。"
										+ "[npc.she]脱下[npc.her]的最后一件衣服，[npc.she]问道，"
										+ "[npc.speech(你满意了吗，[npc.pcName]？)]"
									+ "</p>"
									+ "<p>"
										+ "你给予了肯定的回答，然后再次命令[npc.Name]让[npc.herself]跪下；[npc.she]尽可能磨蹭地执行了这一命令。"
										+ "[npc.she]趴在你大腿上，展示[npc.her][npc.ass+]，你发出胜利的笑声，然后迅速用[pc.hand]扇[npc.her]裸露的屁股。"
										+ "一声尖叫响起，和着肉体被拍打的声音，促使你再次打向[npc.her]的[npc.ass]。"
									+ "</p>"
									+ "<p>"
										+ "你继续这样做了一会儿；你的奴隶伏在大腿上扭动着，尖叫着，你一下又一下地打着[npc.her]脆弱的脊背。"
										+ "最终，你觉得[npc.sheIs]已经得到了教训，于是你命令[npc.herHim]站起来，重新穿上[npc.her]的衣服。"
									+ "</p>");
								break;
						}
						break;
					case NEUTRAL:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.she]听到你的命令，慌张地叫了一声，但意识到[npc.she]在这件事上真的别无选择，只好勉强服从命令。"
										+ "[npc.she]抱怨着脱下衣服，"
										+ "[npc.speech(我真的必须这么做吗？被迫这样做有点有辱人格……)]"
									+ "</p>"
									+ "<p>"
										+ "你没有理会[npc.her]的话，再次命令[npc.Name]趴到你膝盖上；[npc.she]再次尽可能磨蹭地执行了这一命令。"
										+ "[npc.she]趴在你大腿上，展示[npc.her][npc.ass+]，你发出胜利的笑声，然后迅速用[pc.hand]扇[npc.her]裸露的屁股。"
										+ "一声尖叫响起，和着肉体被拍打的声音，促使你再次打向[npc.her]的[npc.ass]。"
									+ "</p>"
									+ "<p>"
										+ "你继续这样做了一会儿；你的奴隶伏在大腿上扭动着，尖叫着，你一下又一下地打着[npc.her]脆弱的脊背。"
										+ "最终，你觉得[npc.sheIs]已经得到了教训，于是你命令[npc.herHim]站起来，重新穿上[npc.her]的衣服。"
									+ "</p>");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"[npc.she]听到你的命令后，发出了一声慌乱的叫喊，但还是努力控制住[npc.her]的情绪，勉强服从命令。"
										+ "[npc.she]抱怨着脱下衣服，"
										+ "[npc.speech(给我一点时间，[npc.pcName]。)]"
									+ "</p>"
									+ "<p>"
										+ "在[npc.sheIs]被脱光后，你再次命令[npc.Name]趴到你膝盖上；[npc.she]再次以尽可能慢的速度执行命令。"
										+ "[npc.she]趴在你大腿上，展示[npc.her][npc.ass+]，你发出胜利的笑声，然后迅速用[pc.hand]扇[npc.her]裸露的屁股。"
										+ "一声尖叫响起，和着肉体被拍打的声音，促使你再次打向[npc.her]的[npc.ass]。"
									+ "</p>"
									+ "<p>"
										+ "你继续这样做了一会儿；你的奴隶伏在大腿上扭动着，尖叫着，你一下又一下地打着[npc.her]脆弱的脊背。"
										+ "最终，你觉得[npc.sheIs]已经得到了教训，于是你命令[npc.herHim]站起来，重新穿上[npc.her]的衣服。"
									+ "</p>");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.she]立即服从你的命令，但当[npc.she]顺从地把[npc.her]的衣服扔到地上时，你看到[npc.her]的[npc.eyes]里明显流露出痛苦的神情。"
										+ "[npc.she]脱下[npc.her]的最后一件衣服，[npc.she]问道，"
										+ "[npc.speech(你满意了吗，[npc.pcName]？)]"
									+ "</p>"
									+ "<p>"
										+ "你给出了肯定的回答，接着再次命令[npc.name]伏在你的膝上；[npc.she]立马遵从了命令。"
										+ "[npc.she]趴在你大腿上，展示[npc.her][npc.ass+]，你发出胜利的笑声，然后迅速用[pc.hand]扇[npc.her]裸露的屁股。"
										+ "一声尖叫响起，和着肉体被拍打的声音，促使你再次打向[npc.her]的[npc.ass]。"
									+ "</p>"
									+ "<p>"
										+ "你继续这样做了一会儿；你的奴隶伏在大腿上扭动着，尖叫着，你一下又一下地打着[npc.her]脆弱的脊背。"
										+ "最终，你觉得[npc.sheIs]已经得到了教训，于是你命令[npc.herHim]站起来，重新穿上[npc.her]的衣服。"
									+ "</p>");
								break;
						}
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.she]听到你的命令，伤心地哭出来，但意识到[npc.she]在这件事上真的别无选择，只好勉强服从命令。"
										+ "[npc.she]抱怨着脱下衣服，"
										+ "[npc.speech(你知道吗，被迫这样做有点有辱人格……我以为你喜欢我的……)]"
									+ "</p>"
									+ "<p>"
										+ "你没有理会[npc.her]的话，再次命令[npc.Name]趴到你膝盖上；[npc.she]再次尽可能磨蹭地执行了这一命令。"
										+ "[npc.she]趴在你大腿上，展示[npc.her][npc.ass+]，你发出胜利的笑声，然后迅速用[pc.hand]扇[npc.her]裸露的屁股。"
										+ "一声尖叫响起，和着肉体被拍打的声音，促使你再次打向[npc.her]的[npc.ass]。"
									+ "</p>"
									+ "<p>"
										+ "你继续这样做了一会儿；你的奴隶伏在大腿上扭动着，尖叫着，你一下又一下地打着[npc.her]脆弱的脊背。"
										+ "最终，你觉得[npc.sheIs]已经得到了教训，于是你命令[npc.herHim]站起来，重新穿上[npc.her]的衣服。"
									+ "</p>");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"当[npc.she]听到你的命令时，[npc.she]发出了悲伤的哭声，但她努力控制住[npc.her]的情绪，慢慢地走去服从命令。"
										+ "[npc.she]抱怨着脱下衣服，"
										+ "[npc.speech(我以为你喜欢我，[npc.pcName]……但如果这是你想要的……)]"
									+ "</p>"
									+ "<p>"
										+ "你没有理会[npc.her]的话，再次命令[npc.Name]趴到你膝盖上；[npc.she]再次尽可能磨蹭地执行了这一命令。"
										+ "[npc.she]趴在你大腿上，展示[npc.her][npc.ass+]，你发出胜利的笑声，然后迅速用[pc.hand]扇[npc.her]裸露的屁股。"
										+ "一声尖叫响起，和着肉体被拍打的声音，促使你再次打向[npc.her]的[npc.ass]。"
									+ "</p>"
									+ "<p>"
										+ "你继续这样做了一会儿；你的奴隶伏在大腿上扭动着，尖叫着，你一下又一下地打着[npc.her]脆弱的脊背。"
										+ "最终，你觉得[npc.sheIs]已经得到了教训，于是你命令[npc.herHim]站起来，重新穿上[npc.her]的衣服。"
									+ "</p>");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.she]顺从地把[npc.her]的衣服扔到地上，立即移动脚步去服从你的命令，但你看到[npc.her]的[npc.eyes]中流露出明显的悲伤神情。"
										+ "[npc.she]脱下[npc.her]的最后一件衣服，[npc.she]问道，"
										+ "[npc.speech(你满意了吗，[npc.pcName]？)]"
									+ "</p>"
									+ "<p>"
										+ "你的回答是肯定的，然后再次命令[npc.Name]让[npc.herself]屈膝；[npc.she]再次尽可能磨蹭地执行命令。"
										+ "[npc.she]趴在你大腿上，展示[npc.her][npc.ass+]时，你发出胜利的笑声，然后迅速用[pc.hand]扇[npc.her]裸露的屁股。"
										+ "一声尖叫响起，和着肉体被拍打的声音，促使你再次打向[npc.her]的[npc.ass]。"
									+ "</p>"
									+ "<p>"
										+ "你继续这样做了一会儿；你的奴隶伏在大腿上扭动着，尖叫着，你一下又一下地打着[npc.her]脆弱的脊背。"
										+ "最终，你觉得[npc.sheIs]已经得到了教训，于是你命令[npc.herHim]站起来，重新穿上[npc.her]的衣服。"
									+ "</p>");
								break;
						}
						break;
				}
	
				if(getSlave().getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive()) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>由于[npc.Name]是个受虐狂，打[npc.herHim]的屁股实际上让[npc.herHim]更喜欢你了，也增加了[npc.her]的服从性！</i>"
							+ "</p>");
					
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>这并没有改变[npc.name]"
									+ "<span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ "你的事实，[npc.she]被迫接受惩罚性打屁股，这对[npc.her]对你的好感产生了巨大的负面影响，但同时也会增加[npc.her]的服从性！</i>"
							+ "</p>");
				}
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLAVE_MOLEST = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "性侵[npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDoll()) {
				UtilText.nodeContentSB.append("<p>"
							+ "你无法抵挡[npc.namePos][npc.feminine]身体的诱惑，绕到[npc.her]背后，然后向前迈出一步，用[pc.arms]环抱住[npc.herHim]。"
							+ "你抓住玩偶[npc.breasts+]，[npc.her]顺服地一动不动。你向下摸到[npc.her][npc.legs]间，才发出[npc.a_moan+]。"
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(是的，[pc.name]，请随意按照您的意愿使用我，)][npc.she]回答道。"
						+ "</p>"
						+ "<p>"
							+ "就这样，你继续对[npc.name]进行猥亵和摸索，直到尽兴并且探索了[npc.her]硅胶身体的每一寸肌肤才停止。"
							+ "你退了开来，思索着下一步该怎么做……"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append("<p>"
							+ "你无法抵挡[npc.namePos][npc.feminine]身体的诱惑，绕到[npc.her]背后，然后向前迈出一步，用[pc.arms]环抱住[npc.herHim]。");
	
				String firstCry = "一声惊恐的哭喊";
				String firstReaction = "突然开始扭动，试图挣脱你的抚摸";
				String firstSpeech = "滚开！别烦我！";
				String firstPCReaction = "[npc.her]的言语无法阻止你向[npc.her]不情愿的身体前进";
				
				String secondReaction = "[npc.she]继续呼喊和挣扎";
				String thirdReaction = "，尽管[npc.her]提出了抗议，";
				String secondSpeech = "[npc.speech(停下！滚……滚开！)]"
							+ "[npc.she]喊道，你对[npc.her]的话充耳不闻，继续享受你的乐趣。";
				
				String finalDescription = "最终，你觉得自己满足了，于是松开[npc.Name]让[npc.herHim]到房间的另一边去，你上下打量着[npc.herHim]，咧嘴笑了起来。"
							+ "[npc.she]继续吐口水诅咒你，让你不要再打扰[npc.herHim]，你不知道是应该按照你的奴隶的要求去做，还是对[npc.herHim]做些别的事情……";
				
				switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								break;
							case NEUTRAL:
								firstCry = "令人不适的哭声";
								firstReaction = "开始扭动并试图将[npc.her]的身体从你的抚摸中移开";
								firstSpeech = "你……你能停下了吗？！";
								firstPCReaction = "[npc.her]的言语无法阻止你向[npc.her]不情愿的身体前进";
								
								secondReaction = "[npc.She]继续在不适中挣扎";
								thirdReaction = "，尽管[npc.her]并不情愿，";
								secondSpeech = "[npc.speech(求……求你了！快……快住手！)]"
											+ "[npc.she]哭泣着，你对[npc.her]的话被充耳不闻，继续享受你的乐趣。";
								
								finalDescription = "最终，你觉得自己满足了，于是松开[npc.Name]让[npc.herHim]冲到房间的另一边，你上下打量着[npc.herHim]，咧嘴笑了起来。"
											+ "当[npc.she]请求你放过[npc.herHim]时，[npc.she]避开了你的视线，你不知道是应该按照你的奴隶的要求去做，还是对[npc.herHim]做些别的事情……";
								break;
							case OBEDIENT:
								firstCry = "哭喊";
								firstReaction = "顺从地为你保持[npc.her]的身体一动不动";
								firstSpeech = "我随你使用，[npc.pcName]。";
								firstPCReaction = "[npc.her]的冷言冷语无助于阻止你向[npc.her]的身体前进";
								
								secondReaction = "[npc.she]继续顺从地允许[npc.herself]被使用";
								thirdReaction = "，尽管[npc.her]态度冷淡，";
								secondSpeech = "[npc.speech(这是否令你满意？[npc.pcName]？)]"
											+ "[npc.she]尽职尽责地问道，在你们继续享受乐趣时没有表现出任何情绪。";
								
								finalDescription = "最终，你觉得自己满足了，松开[npc.Name]让[npc.herHim]往后退了一步，然后上下打量着[npc.herHim]，咧嘴笑了起来。"
											+ "[npc.she]顺从地询问你是否还需要[npc.herHim]做什么，这让你怀疑是否应该对[npc.herHim]做其他事情……";
								break;
						}
						break;
					case NEUTRAL:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								firstCry = "惊呼";
								firstReaction = "开始扭动并试图将[npc.her]的身体从你的抚摸中移开";
								firstSpeech = "你……你一定要这么做吗？";
								firstPCReaction = "[npc.her]忧心忡忡的语气丝毫不能阻止你向[npc.her]的身体前进";
								
								secondReaction = "[npc.She]继续轻轻地挣扎";
								thirdReaction = "，尽管[npc.her]的态度并不热情，";
								secondSpeech = "[npc.speech(这还要持续多久，[npc.pcName]？)]"
											+ "[npc.her]不服气地质问，但没有得到任何回答，你们继续享受乐趣。";
								
								finalDescription = "最终，你觉得自己满足了，于是松开[npc.Name]让[npc.herHim]往后退了几步，然后上下打量着[npc.herHim]，咧嘴笑了起来。"
											+ "当[npc.she]请你放过[npc.herHim]时，[npc.she]叹了一口气，你不知道是应该按照你的奴隶的要求去做，还是对[npc.herHim]做些别的事情……";
								break;
							case NEUTRAL:
								firstCry = "半压抑的哭泣";
								firstReaction = "开始扭动并试图将[npc.her]的身体从你的抚摸中移开";
								firstSpeech = "[npc.pcName]，真的要这样做吗？";
								firstPCReaction = "[npc.her]忧心忡忡的语气丝毫不能阻止你向[npc.her]的身体前进";
								
								secondReaction = "[npc.She]继续轻轻地挣扎";
								thirdReaction = "，尽管[npc.her]的态度并不热情，";
								secondSpeech = "[npc.speech(你快完事了吗？[npc.pcName]？)]"
											+ "[npc.her]不服气地质问，但没有得到任何回答，你们继续享受乐趣。";
								
								finalDescription = "最终，你觉得自己满足了，于是松开[npc.Name]让[npc.herHim]往后退了几步，然后上下打量着[npc.herHim]，咧嘴笑了起来。"
											+ "当[npc.she]避开你的视线时，[npc.she]稍稍松了一口气，你在想是否应该对[npc.herHim]做点别的什么……";
								break;
							case OBEDIENT:
								firstCry = "哭喊";
								firstReaction = "顺从地为你保持[npc.her]的身体一动不动";
								firstSpeech = "我随你使用，[npc.pcName]。";
								firstPCReaction = "[npc.her]的冷言冷语无助于阻止你向[npc.her]的身体前进";
								
								secondReaction = "[npc.she]继续顺从地允许[npc.herself]被使用";
								thirdReaction = "，尽管[npc.her]态度冷淡，";
								secondSpeech = "[npc.speech(这是否令你满意？[npc.pcName]？)]"
											+ "[npc.she]尽职尽责地问道，在你们继续享受乐趣时没有表现出任何情绪。";
								
								finalDescription = "最终，你觉得自己满足了，松开[npc.Name]让[npc.herHim]往后退了一步，然后上下打量着[npc.herHim]，咧嘴笑了起来。"
											+ "[npc.she]顺从地询问你是否还需要[npc.herHim]做什么，这让你怀疑是否应该对[npc.herHim]做其他事情……";
								break;
						}
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								firstCry = "惊呼";
								firstReaction = "开始扭动并试图将[npc.her]的身体从你的抚摸中移开";
								firstSpeech = "[npc.pcName]！我以为你喜欢我的！你为什么要这么做？！";
								firstPCReaction = "[npc.her]痛苦的语气丝毫不能阻止你向[npc.her]的身体前进";
								
								secondReaction = "[npc.She]继续轻轻地挣扎";
								thirdReaction = "，尽管[npc.her]的态度并不热情，";
								secondSpeech = "[npc.speech([npc.PcName]，求你了！我……我只是想抱抱……)]"
											+ "[npc.she]叹了口气，[npc.she]不听话的话语没有得到任何回答，你继续享受你的乐趣。";
								
								finalDescription = "最终，你觉得自己满足了，于是松开[npc.Name]让[npc.herHim]往后退了几步，然后上下打量着[npc.herHim]，咧嘴笑了起来。"
											+ "当[npc.she]对你微笑时，[npc.She]松了一口气，你不知道现在是否应该对[npc.herHim]做点别的什么……";
								break;
							case NEUTRAL:
								firstCry = "一声轻呼";
								firstReaction = "为你保持[npc.her]的身体一动不动";
								firstSpeech = "[npc.PcName]，真的要这样做吗？";
								firstPCReaction = "[npc.her]忧心忡忡的语气丝毫不能阻止你向[npc.her]的身体前进";
								
								secondReaction = "[npc.She]继续保持一动不动";
								thirdReaction = "，尽管[npc.her]有一点不听话，";
								secondSpeech = "[npc.speech(这就是你喜欢的吗？[npc.pcName]？)]"
											+ "[npc.she]问道，[npc.her]的质问没有得到回答，你们继续享受乐趣。";
								
								finalDescription = "最终，你觉得自己满足了，于是松开[npc.Name]让[npc.herHim]往后退了几步，然后上下打量着[npc.herHim]，咧嘴笑了起来。"
											+ "当[npc.she]避开你的视线时，[npc.she]稍稍松了一口气，你在想是否应该对[npc.herHim]做点别的什么……";
								break;
							case OBEDIENT:
								firstCry = "娇喘";
								firstReaction = "顺从地为你保持[npc.her]的身体一动不动";
								firstSpeech = "我随你使用，[npc.pcName]。";
								firstPCReaction = "[npc.her]的冷言冷语无助于阻止你向[npc.her]的身体前进";
								
								secondReaction = "[npc.she]继续顺从地允许[npc.herself]被使用";
								thirdReaction = "，尽管[npc.her]态度冷淡，";
								secondSpeech = "[npc.speech(这是否令你满意？[npc.pcName]？)]"
											+ "[npc.she]尽职尽责地问道，在你们继续享受乐趣时没有表现出任何情绪。";
								
								finalDescription = "最终，你觉得自己满足了，松开[npc.Name]让[npc.herHim]往后退了一步，然后上下打量着[npc.herHim]，咧嘴笑了起来。"
											+ "[npc.she]顺从地询问你是否还需要[npc.herHim]做什么，这让你怀疑是否应该对[npc.herHim]做其他事情……";
								break;
						}
						break;
				}
				
				
				
				if(getSlave().hasBreasts()) {
					UtilText.nodeContentSB.append(
								"你的[pc.hands]伸到罩杯然后揉捏[npc.namePos][npc.breasts+]，[npc.she]"+firstReaction+"，口中发出"+firstCry+"，"
								+ "[npc.speech("+firstSpeech+")]"
							+ "</p>"
							+ "<p>"
								+ firstPCReaction+"，你用一只[pc.hand]继续揉捏着[npc.her][npc.breasts+]，将另一只手滑#IF(npc.hasLegs())到[npc.her]两腿之间#ELSE向[npc.her]的胯部#ENDIF。");
				} else {
					UtilText.nodeContentSB.append(
							"你伸出[pc.hands]摸向[npc.her]的胸部，让[npc.namePos]在"+firstReaction+"的同时从口中发出"+firstCry+"，"
							+ "[npc.speech("+firstSpeech+")]"
						+ "</p>"
						+ "<p>"
							+ firstPCReaction+"，你用一只[pc.hand]继续揉捏着[npc.her]的胸部，将另一只手滑#IF(npc.hasLegs())到[npc.her]两腿之间#ELSE向[npc.her]的胯部#ENDIF。");
				}
	
				if(getSlave().hasVagina() && getSlave().hasPenis()) {
					UtilText.nodeContentSB.append(
								"你对着[npc.her][npc.penis+]抚摸、撸动、挑弄，"+secondReaction+"，你忍不住在奴隶的[npc.ear]边[pc.moan]着"+thirdReaction+"你还是能感觉到[npc.her][npc.cock+]正在你的戏弄下逐渐胀大。"
								+ "你将[pc.hand]继续向下探索，逐渐将重心转移到[npc.her][npc.pussy+]上，"
									+ "你感觉到在你的刺激下，[npc.her][npc.clit+]和[npc.labia+]已经慢慢湿润起来，不禁又露出了笑容。"
							+ "</p>");
					
				} else if(getSlave().hasVagina()) {
					UtilText.nodeContentSB.append(
							"你对着[npc.her][npc.clit+]抚摸、撸动、挑弄，"+secondReaction+"，你忍不住在奴隶的[npc.ear]边[pc.moan]着"+thirdReaction+"你还是能感觉到[npc.her][npc.pussy+]"
									+ "在你的戏弄下湿润了。"
						+ "</p>");
					
				} else if(getSlave().hasPenis()) {
					UtilText.nodeContentSB.append(
							"你对着[npc.her][npc.penis+]抚摸、撸动、挑弄，"+secondReaction+"，你忍不住在奴隶的[npc.ear]边[pc.moan]着"+thirdReaction+"你还是能感觉到[npc.her][npc.cock+]正在你的戏弄下逐渐胀大。"
						+ "</p>");
					
				} else {
					UtilText.nodeContentSB.append(
							"你对着[npc.her]无性别的下体抚摸、挑弄，"+secondReaction+"，你忍不住在奴隶的[npc.ear]边[pc.moan]着"+thirdReaction+"你还是能感觉到[npc.her]在你的戏弄下晃动起了腰身。"
						+ "</p>");
					
				}
				UtilText.nodeContentSB.append(
						"<p>"
							+ secondSpeech
						+ "</p>"
						+ "<p>"
							+ finalDescription
						+ "</p>");
				
				
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<i>调戏[npc.name]有助于强化[npc.her]的意识——[npc.she]是属于你的——这会提高[npc.her]的服从！");
				if(getSlave().isAttractedTo(Main.game.getPlayer())) {
					if(getSlave().hasFetish(Fetish.FETISH_SUBMISSIVE) || getSlave().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
						UtilText.nodeContentSB.append(
									"由于[npc.sheIs]不仅被你吸引，而且"
									+ (getSlave().hasFetish(Fetish.FETISH_SUBMISSIVE)
											?(getSlave().hasFetish(Fetish.FETISH_NON_CON_SUB)
													?"既愿意做顺从者，又因未经同意的性行为而兴奋，"
													:"愿意做顺从者，")
											:"因未经同意的性行为而兴奋，")
									+ "你对于[npc.namePos]身体的使用不容拒绝，也没有得到同意，反而让[npc.herHim]更喜欢你了！</i>"
								+ "</p>");
					} else {
						UtilText.nodeContentSB.append(
								"由于[npc.sheIs]受到了你的吸引，而你对于[npc.namePos]身体的使用不容拒绝，也没有得到同意，并没有让[npc.herHim]对你的好感上升或下降！</i>"
							+ "</p>");
					}
				} else {
					if(getSlave().hasFetish(Fetish.FETISH_SUBMISSIVE) || getSlave().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
						UtilText.nodeContentSB.append(
								"尽管[npc.sheIs]并没有被你吸引，但[npc.she]"
								+ (getSlave().hasFetish(Fetish.FETISH_SUBMISSIVE)
										?(getSlave().hasFetish(Fetish.FETISH_NON_CON_SUB)
												?"既愿意做顺从者，又因未经同意的性行为而兴奋，"
												:"愿意做顺从者，")
										:"因未经同意的性行为而兴奋，")
								+ "你对于[npc.namePos]身体的使用不容拒绝，也没有得到同意，却并没有让[npc.herHim]对你的好感上升或下降！</i>"
							+ "</p>");
					} else {
						UtilText.nodeContentSB.append(
								"由于[npc.sheIs]并没有被你吸引，而你对于[npc.namePos]身体的使用不容拒绝，也没有得到同意，所以[npc.herHim]失去了一些对你的好感！</i>"
							+ "</p>");
					}
				}
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("返回", "", true) {
		@Override
		public String getDescription(){
			return "你已经爽过了，离开这里让[npc.name]恢复一下吧。";
		}
		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants(false).size()==4) {
				List<GameCharacter> sexParticipants = new ArrayList<GameCharacter>(charactersPresent);
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_GANGBANG_3", sexParticipants);
				
			} else if(Main.sex.getAllParticipants(false).size()==5) {
				List<GameCharacter> sexParticipants = new ArrayList<GameCharacter>(charactersPresent);
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_GANGBANG_4", sexParticipants);
				
			} else {
				if(Main.sex.getNumberOfOrgasms(getSlave()) >= getSlave().getOrgasmsBeforeSatisfied()) {
					return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX", getSlave());
				} else {
					return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_NO_ORGASM", getSlave());
				}
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("继续", "决定接下来该做什么。", SLAVE_START_NO_CONTENT) {
					@Override
					public void effects() {
						SlaveDialogue.initDialogue(getSlave(), false); // Need to re-init this dialogue as it gets cleared in Game turnUpdate during this scene.
						Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_CONTINUE", getSlave()));
						if(!getSlave().isAsleep() && !isDollStatue()) {
							Main.game.appendToTextEndStringBuilder(getFooterText());
						}
//						Main.game.appendToTextEndStringBuilder(getSlaveStartCoreContent());
					}
				};
			}
			return null;
		}
	};
	
}
