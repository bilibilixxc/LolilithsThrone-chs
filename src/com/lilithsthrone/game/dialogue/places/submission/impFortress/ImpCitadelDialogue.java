package com.lilithsthrone.game.dialogue.places.submission.impFortress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.npc.submission.Takahashi;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.12
 * @author Innoxia
 */
public class ImpCitadelDialogue {
	
	public static boolean isImpsDefeated() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonImpsDefeated);
	}
	
	public static boolean isDefeated() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated);
	}
	
	private static boolean isBossEncountered() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonBossEncountered);
	}

	/**
	 * The demon leaders are placed in the siren's throne room.
	 */
	public static void applyEntry() {
		if(!isDefeated()) {
			Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
			Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
			Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
		}
	}
	
	/**
	 * If the demon leaders haven't been defeated, they return to their keeps.
	 */
	public static void applyExit() {
		Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_DEMON);
		
		if(Main.game.getNpc(FortressAlphaLeader.class).getWorldLocation()==WorldType.IMP_FORTRESS_DEMON) {
			if(isDefeated() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
				Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
			} else {
				Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP, true);
			}
		}
		if(Main.game.getNpc(FortressMalesLeader.class).getWorldLocation()==WorldType.IMP_FORTRESS_DEMON) {
			if(isDefeated() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
				Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
			} else {
				Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP, true);
			}
		}
		if(Main.game.getNpc(FortressFemalesLeader.class).getWorldLocation()==WorldType.IMP_FORTRESS_DEMON) {
			if(isDefeated() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
				Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
			} else {
				Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP, true);
			}
		}
	}
	
	public static void clearFortress(boolean withQuestProgress) {
		
		for(GameCharacter character : getBossGroup(false)) {
			character.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
		}
		Main.game.getNpc(Takahashi.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
		
		// Sort out boss:
		getBoss().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
		Main.game.getNpc(Lyssieth.class).addSlave((NPC) getBoss());
		((NPC) getBoss()).equipClothing(EquipClothingSetting.getAllClothingSettings()); // In case the player used steal on her.
		
		// Increment quest:
		if(withQuestProgress && Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_2_B_SIRENS_CALL) {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL));
		}
		
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonDefeated, true);

		Main.game.getDialogueFlags().setSavedLong(ImpFortressDialogue.FORTRESS_ALPHA_CLEAR_TIMER_ID, Main.game.getMinutesPassed());
		Main.game.getDialogueFlags().setSavedLong(ImpFortressDialogue.FORTRESS_MALES_CLEAR_TIMER_ID, Main.game.getMinutesPassed());
		Main.game.getDialogueFlags().setSavedLong(ImpFortressDialogue.FORTRESS_FEMALES_CLEAR_TIMER_ID, Main.game.getMinutesPassed());
		
		// Move NPCs out of hiding:
		for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
			if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_DEMON)) {
				character.returnToHome();
			}
		}
		
		getArcanist().equipClothing(EquipClothingSetting.getAllClothingSettings()); // Remove lab coat
	}
	
	private static void banishImps() {
		Cell[][] grid =  Main.game.getWorlds().get(WorldType.IMP_FORTRESS_DEMON).getGrid();
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[0].length;j++) {
				List<GameCharacter> characters = new ArrayList<>(Main.game.getCharactersPresent(grid[i][j]));
				for(GameCharacter character : characters) {
					if(character instanceof ImpAttacker && !character.isSlave()) {
						Main.game.banishNPC((NPC) character);
					}
				}
			}
		}
	}
	
	public static void spawnImps() {
		banishImps();
		
		List<GameCharacter> impGroup = new ArrayList<>();
		try {
			
			impGroup = new ArrayList<>();
			List<String> impAdjectives = new ArrayList<>();
			
			int impCount = 6;
			if(Main.game.getDialogueFlags().impCitadelImpWave==5) {
				impCount = 4;
			}
			
			for(int i=0; i<impCount; i++) {
				AbstractSubspecies subspecies = i<3?Subspecies.IMP_ALPHA:Subspecies.IMP;
				
				ImpAttacker imp = new ImpAttacker(subspecies, Gender.getGenderFromUserPreferences(false, false), false);
				imp.setLevel(12-(i*2)+Util.random.nextInt(3));
				Main.game.addNPC(imp, false);
				if(i==0) {
					imp.setGenericName("阿尔法小恶魔首领");
					imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
					imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
					
				} else if(i==1) {
					imp.setGenericName("阿尔法小恶魔弓箭手");
					imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_shortbow"));
					
				} else if(i==2) {
					imp.setGenericName("阿尔法小恶魔奥术师");
					imp.setAttribute(Attribute.MAJOR_ARCANE, 50);
					imp.addSpell(Spell.ARCANE_AROUSAL);
					imp.addSpell(Spell.FIREBALL);
					imp.addSpell(Spell.ICE_SHARD);
					imp.addSpell(Spell.TELEKENETIC_SHOWER);
					
				} else {
					impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
					imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				}
				impGroup.add(imp);
			}
			
			for(GameCharacter impCharacter : impGroup) {
				impCharacter.setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), true);
				((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<GameCharacter> getImpGroup() {
		List<GameCharacter> impGuards = new ArrayList<>();

		for(GameCharacter character : Main.game.getCharactersPresent()) {
			if(character instanceof ImpAttacker && character.getPartyLeader()==null && !character.isSlave()) {
				impGuards.add(character);
			}
		}
		
		impGuards.sort((imp1, imp2) -> imp2.getLevel()-imp1.getLevel());
		return impGuards;
	}
	
	public static GameCharacter getImpGroupLeader() {
		return getImpGroup().get(0);
	}
	
	/**
	 * Only to be used in Game.importGame() for versions prior to 0.2.12.5
	 */
	public static void resetFortress() {
		
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonDefeated, false);
		
		banishImps();
		
		// Move boss back to fortress:
		Main.game.getNpc(DarkSiren.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);

		// Move defeated leaders into fortress:
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
			Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
		}
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
			Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
		}
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
			Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
		}
		
		// Move NPCs into hiding:
		Cell[][] cells = Main.game.getWorlds().get(WorldType.SUBMISSION).getCellGrid();
		for(int i=0; i< cells.length;i++) {
			for(int j=0; j< cells[i].length;j++) {
				Cell cell = cells[j][i];
				if(cell.getPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_DEMON)) {
					for(GameCharacter character : Main.game.getCharactersPresent(cell)) {
						if(!Main.game.getPlayer().getCompanions().contains(character)) {
							character.setHomeLocation(WorldType.SUBMISSION, character.getLocation());
							character.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
						}
					}
				}
			}
		}
	}
	
	public static List<GameCharacter> getBossGroup(boolean includeBoss) {
		List<GameCharacter> bossGroup = new ArrayList<>();
		
		if(includeBoss) {
			bossGroup.add(getBoss());
		}
		bossGroup.add(Main.game.getNpc(FortressMalesLeader.class));
		bossGroup.add(Main.game.getNpc(FortressAlphaLeader.class));
		bossGroup.add(Main.game.getNpc(FortressFemalesLeader.class));
		
		return bossGroup;
	}

	public static NPC getDemonLeader() {
		return Main.game.getNpc(FortressMalesLeader.class);
	}

	public static GameCharacter getBoss() {
		return Main.game.getNpc(DarkSiren.class);
	}
	
	public static Takahashi getArcanist() {
		return (Takahashi) Main.game.getNpc(Takahashi.class);
	}

	public static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	public static boolean isCompanionDialogue() {
		return !Main.game.getPlayer().getCompanions().isEmpty();
	}
	
	public static List<GameCharacter> getAllCharacters() {
		// There's a reason I can't just add all from getCharactersPresent(), but I forgot. Maybe it was because the Elemental companion gets added?
		List<GameCharacter> allCharacters = new ArrayList<>();
		
		if(isCompanionDialogue()) {
			allCharacters.add(getMainCompanion());
		}
		
		if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_DEMON_KEEP)) {
			allCharacters.add(getBoss());
			allCharacters.add(Main.game.getNpc(FortressMalesLeader.class));
			allCharacters.add(Main.game.getNpc(FortressAlphaLeader.class));
			allCharacters.add(Main.game.getNpc(FortressFemalesLeader.class));
		}

		// For the arcanist:
		for(NPC character : Main.game.getCharactersPresent()) {
			if(!allCharacters.contains(character)) {
				allCharacters.add(character);
			}
		}
		
		return allCharacters;
	}
	
	public static String getDialogueEncounterId() {
		if(isCompanionDialogue()) {
			return "Companions";
		}
		return "";
	}
	
	// Dialogues:
	
	private static Response getImpChallengeResponse() {
		return new Response("挑战",
				"宣布你是来打败这个城堡的所有居民的！<br/>"
						+ "<i>你认为城堡里大约有[style.boldBad(三十只小恶魔)]，他们会一波接一波来帮助他们的盟友，所以准备好与他们战斗吧！</i>",
				IMP_CHALLENGE) {
			@Override
			public boolean isCombatHighlight() {
				return true;
			}
			@Override
			public void effects() {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE", getAllCharacters()));
				Main.game.getDialogueFlags().impCitadelImpWave = 0;
				spawnImps();
				getArcanist().setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
			}
		};
	}
	
	public static final DialogueNode ENTRANCE = new DialogueNode("大门", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ENTRANCE_RUINS", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ENTRANCE", getAllCharacters()));
				
				if(isImpsDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "GENERIC_IMPS_DEFEATED", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "GENERIC_IMP_GUARDS", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "回到隧道里去。", LEAVE_FORTRESS) {
					@Override
					public void effects() {
						applyExit();
					}
				};

			} else if(index==5 && !isImpsDefeated() && !isDefeated()) {
				return getImpChallengeResponse();
			}
			return null;
		}
	};
	
	public static final DialogueNode LEAVE_FORTRESS = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LEAVE_FORTRESS", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_DEMON.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode IMP_CHALLENGE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				try {
					return new ResponseCombat("战斗", "是时候让这些小恶魔乖乖听话了！", (NPC) getImpGroupLeader(), getImpGroup(), null);
				} catch(Exception ex) {
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode IMP_CHALLENGE_CONTINUE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE_CONTINUE", getAllCharacters()));
			
			switch(Main.game.getDialogueFlags().impCitadelImpWave) {
				case 1:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE_CONTINUE_WAVE_2", getAllCharacters()));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE_CONTINUE_WAVE_3", getAllCharacters()));
					break;
				case 3:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE_CONTINUE_WAVE_4", getAllCharacters()));
					break;
				case 4:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE_CONTINUE_WAVE_5", getAllCharacters()));
					break;
				case 5:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE_CONTINUE_WAVE_6", getAllCharacters()));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("更多小恶魔", "然而有更多小恶魔来支援倒下的同伴！", (NPC) getImpGroup().get(0), getImpGroup(), null);
			}
			return null;
		}
	};
	
	public static final DialogueNode IMP_FIGHT_AFTER_COMBAT_VICTORY = new DialogueNode("胜利", "。", true) {

		@Override
		public String getDescription() {
			return "你已击败这些小恶魔！";
		}

		@Override
		public String getContent() {
			if(getImpGroup().isEmpty()) {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ALL_ENSLAVED");
				
			} else if(getImpGroup().size()==1) {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ENSLAVED_ONE", getImpGroup());
				
			} else if(getImpGroup().size()<4) {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ENSLAVED", getImpGroup());
			}
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_VICTORY", getImpGroup());
			// IMP_FIGHT_AFTER_COMBAT_VICTORY_ATTRIBUTE_BOOST is appended to this (in ImpAttacker class's endCombat() method)
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!getImpGroup().isEmpty()) {
				if(index==0) {
					return "互动";
					
				} else if(index==1) {
					return "物品栏";
					
				} else if(index==2) {
					return "转化";
					
				}
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getImpGroup().isEmpty()) {
				if(index==1) {
					return new Response("继续", "因为你已经奴役了所有的小恶魔，除了穿过城堡以外你没有别的事情可以做了……", Main.game.getDefaultDialogue(false));
				}
				return null;
			}
			if(!isCompanionDialogue()) {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("吓跑", "告诉这些小恶魔趁着他们还能走赶紧滚出去……", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SCARE_OFF", getImpGroup()));
								banishImps();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("做爱",
								"当然，这些家伙<i>就是</i>想要这个！",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX", getImpGroup()));
						
					} else if (index == 3) {
						return new ResponseSex("做爱(温柔)",
								"当然，这些家伙<i>就是</i>想要这个！",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								IMP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX_GENTLE", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("做爱(粗暴)",
								"当然，这些家伙<i>就是</i>想要这个！",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								IMP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX_ROUGH", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("顺从",
								"你不太确定现在该做什么……也许最好让小恶魔来决定接下来做什么……",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGroup(),
								Main.game.getPlayer().getParty(),
								null,
								null,
								IMP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX_SUBMIT", getImpGroup()));
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具伸出手……")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "仔细观察[npc.name]会将自己转化成什么样……"),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			} else {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("吓跑", "告诉这些小恶魔趁着他们还能走赶紧滚出去……", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SCARE_OFF", getImpGroup()));
								banishImps();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("单人做爱",
								UtilText.parse(getMainCompanion(), "让[npc.Name]站在一边，看着你和小恶魔做爱。"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX", getImpGroup()));
						
					} else if (index == 3) {
						return new ResponseSex("单人做爱(温柔)",
								UtilText.parse(getMainCompanion(), "让[npc.Name]站在一边，看着你和小恶魔做爱。"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								IMP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX_GENTLE", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("单人做爱(粗暴)",
								UtilText.parse(getMainCompanion(), "让[npc.Name]站在一边，看着你和小恶魔做爱。"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								IMP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX_ROUGH", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("单人屈从",
								UtilText.parse(getMainCompanion(), "让[npc.Name]站在一边，看着你臣服于小恶魔，让他们与你发生支配型性爱。"),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGroup(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX_SUBMIT", getImpGroup()));
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGroupLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("群交",
									UtilText.parse(companion, "[npc.Name]半点也不想同小恶魔做爱，而且[npc.sheIs]不是你的奴隶，所以你也无法强迫[npc.herHim]……"), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "群交"),
									UtilText.parse(companion, "与小恶魔进行支配型性爱，并让[npc.Name]也加入其中找找乐子。"),
									true,
									false,
									Main.game.getPlayer().getParty(),
									getImpGroup(),
									null,
									null,
									IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_GROUP_SEX", getImpGroup()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGroupLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("群体屈从",
									UtilText.parse(companion, "[npc.Name]半点也不想同小恶魔做爱，而且[npc.sheIs]不是你的奴隶，所以你也无法强迫[npc.herHim]……"), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "群体屈从"),
									UtilText.parse(companion, "让[npc.name]和你一起臣服于小恶魔，让他们和你俩发生支配型性爱。"),
									true,
									false,
									getImpGroup(),
									Main.game.getPlayer().getParty(),
									null,
									null,
									IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getImpGroup()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGroupLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "给[npc.name]"),
									UtilText.parse(companion, "[npc.Name]半点也不想同小恶魔做爱，而且[npc.sheIs]不是你的奴隶，所以你也无法强迫[npc.herHim]……"), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "交给[npc.name]"),
									UtilText.parse(companion, "告诉[npc.Name]让[npc.she]和小恶魔玩玩，你在旁边看着。"),
									false,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpGroup(),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_GIVE_TO_COMPANION", getImpGroup()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getImpGroupLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "献上[npc.name]"),
									UtilText.parse(companion, "你看出[npc.name]一点也不想跟小恶魔做爱，你也没法逼[npc.herHim]去做……"),
									null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
									UtilText.parse(companion, "将[npc.Name]交给小恶魔，然后看着他们与[npc.herHim]发生性关系。"),
									true,
									false,
									getImpGroup(),
									Util.newArrayListOfValues(getMainCompanion()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_OFFER_COMPANION", getImpGroup())) {
								@Override
								public void effects() {
									if(!companion.isAttractedTo(getImpGroupLeader()) && Main.game.isNonConEnabled()) {
										Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
						}
						
					} else {
						return null;
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具伸出手……")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "仔细观察[npc.name]会将自己转化成什么样……"),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			}
		}
	};

	public static final DialogueNode IMP_FIGHT_AFTER_COMBAT_DEFEAT = new DialogueNode("落败", "。", true) {
		
		@Override
		public String getDescription() {
			return "你已被小恶魔们击败！";
		}

		@Override
		public String getContent() {
			if(Main.game.isNonConEnabled()) {
				if(Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.FORTRESS_LAB)) {
					return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_DEFEAT_IN_LAB", getAllCharacters());
				}
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_DEFEAT", getAllCharacters());
				
			} else {
				if(Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.FORTRESS_LAB)) {
					return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_DEFEAT_IN_LAB_NO_NON_CON", getAllCharacters());
				}
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_DEFEAT_NO_NON_CON", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					return new Response("恢复", "花点时间喘口气然后继续前进吧。", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishImps();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_DEFEAT_RECOVER", getAllCharacters()));
						}
					};
				}
				return null;
			}
			
			if(Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.FORTRESS_LAB)
					&& (Main.game.getPlayer().isFeminine() || (isCompanionDialogue() && getMainCompanion().isFeminine() && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)))) {
				
				Map<GameCharacter, SexSlot> subSlots;
				List<GameCharacter> spectators = new ArrayList<>();
				
				if(!Main.game.getPlayer().isFeminine()) {
					spectators.add(Main.game.getPlayer());
					subSlots = Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL));
					
				} else if(isCompanionDialogue () && !getMainCompanion().isFeminine()) {
					spectators.add(getMainCompanion());
					subSlots = Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL));
					
				} else {
					if(isCompanionDialogue()) {
						subSlots = Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
								new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL_TWO));
					} else {
						subSlots = Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL));
					}
				}

				SexManagerInterface manager = new SMStanding(
						Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
						subSlots) {
					@Override
					public boolean isPositionChangingAllowed(GameCharacter character) {
						return false;
					}
					@Override
					public SexPace getStartingSexPaceModifier(GameCharacter character) {
						if(character.isPlayer()) {
							if(index==2) {
								return SexPace.SUB_EAGER;
							} else if(index==3) {
								return SexPace.SUB_RESISTING;
							}
						}
						return null;
					}
				};
				
				//If the arcanist is just using the player's companion, then just "watch":
				if(spectators.contains(Main.game.getPlayer())) {
					if(index==1) {
						return new ResponseSex(
								"旁观",
								UtilText.parse(getArcanist(), getMainCompanion(), "接受你失败的代价，然后看着[npc2.name]被迫给[npc.Name]口交。"),
								false,
								false,
								manager,
								null,
								null,
								IMP_AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX_ARCANIST_WATCH", getAllCharacters()));
					} 
					
				} else {
					if(index==1) {
						return new ResponseSex(
								"性爱",
								isCompanionDialogue() && !spectators.contains(getMainCompanion())
									?UtilText.parse(getArcanist(), getMainCompanion(), "接受你失败的代价：站到[com.name]旁边准备给[npc.Name]口交。")
									:UtilText.parse(getArcanist(), "接受你失败的代价：准备给[npc.Name]口交。"),
								false,
								false,
								manager,
								null,
								null,
								IMP_AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX_ARCANIST", getAllCharacters()));
						
					} else if(index==2) {
						return new ResponseSex(
								"做爱(渴求)",
								isCompanionDialogue() && !spectators.contains(getMainCompanion())
									?UtilText.parse(getArcanist(), getMainCompanion(), "开心的接受别人的要求，准备和[com.name]一起给[npc.Name]口交。")
									:UtilText.parse(getArcanist(), "愉快地接受别人对你的要求，然后心急地准备给[npc.Name]口交。"),
								false,
								false,
								manager,
								null,
								null,
								IMP_AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX_ARCANIST_EAGER", getAllCharacters()));

					} else if (index == 3 && Main.game.isNonConEnabled()) {
						return new ResponseSex(
								"抵抗做爱",
								UtilText.parse(getArcanist(), "你与[npc.Name]搏斗并尝试把[npc.herHim]推离你身边。"),
								false,
								false,
								manager,
								null,
								null,
								IMP_AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX_ARCANIST_RESIST", getAllCharacters()));
					}
				}
				
				
			} else {
				if (index == 1) {
					return new ResponseSex("做爱",
							"让小恶魔把你移到合适的位置……",
							false,
							false,
							getImpGroup(),
							Main.game.getPlayer().getParty(),
							null,
							null,
							IMP_AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
					
				} else if (index == 2) {
					return new ResponseSex("做爱(渴求)",
							"在这群小恶魔跟你摆好姿势的时候，表现得十分饥渴……",
							false,
							false,
							getImpGroup(),
							Main.game.getPlayer().getParty(),
							null,
							null,
							IMP_AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()),
							ResponseTag.START_PACE_PLAYER_SUB_EAGER);
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("抵抗做爱",
							"在这群小恶魔跟你摆好姿势的时候，奋力抵抗……",
							false,
							false,
							getImpGroup(),
							Main.game.getPlayer().getParty(),
							null,
							null,
							IMP_AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()),
							ResponseTag.START_PACE_PLAYER_SUB_RESISTING);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode IMP_AFTER_SEX_VICTORY = new DialogueNode("退开", "", true) {
		
		@Override
		public String getDescription(){
			return "你已经爽过了，于是退到一旁，让这群小恶魔恢复过来后，自行散去了。";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_SEX_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return IMP_FIGHT_AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("吓跑", "把小恶魔吓跑然后继续做你的事。", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishImps();
						}
					};
				}
				
			} else if(responseTab==1) {
				return IMP_FIGHT_AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode IMP_AFTER_SEX_DEFEAT = new DialogueNode("瘫软", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return "你和这些小恶魔做爱都累坏了……";
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().contains(getArcanist())) {
				if(isCompanionDialogue() && Main.sex.getAllParticipants().contains(getMainCompanion())) {
					if(Main.sex.getAllParticipants().contains(Main.game.getPlayer())) {
						return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "AFTER_IMP_DEFEAT_ARCANIST_SEX_BOTH", getAllCharacters());
					} else {
						return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "AFTER_IMP_DEFEAT_ARCANIST_SEX_COMPANION", getAllCharacters());
					}
				} else {
					return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "AFTER_IMP_DEFEAT_ARCANIST_SEX", getAllCharacters());
				}
			}
			if(Main.sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "AFTER_IMP_DEFEAT_SEX_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "AFTER_IMP_DEFEAT_SEX", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("恢复", "花点时间喘口气然后继续前进吧。", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						banishImps();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_SEX_DEFEAT_RECOVER", getAllCharacters()));
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode COURTYARD = new DialogueNode("庭院", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "COURTYARD_RUINS", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "COURTYARD", getAllCharacters()));
				
				if(isImpsDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "GENERIC_IMPS_DEFEATED", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "GENERIC_IMP_GUARDS", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==5 && !isImpsDefeated() && !isDefeated()) {
				return getImpChallengeResponse();
			}
			return null;
		}
	};

	public static final DialogueNode WELL = new DialogueNode("井", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "WELL_RUINS", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "WELL", getAllCharacters()));
				
				if(isImpsDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "GENERIC_IMPS_DEFEATED", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "GENERIC_IMP_GUARDS", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==5 && !isImpsDefeated() && !isDefeated()) {
				return getImpChallengeResponse();
			}
			
			return null;
		}
	};

	public static final DialogueNode LABORATORY = new DialogueNode("实验室", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_RUINS", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY", getAllCharacters()));
				
				if(isImpsDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_IMPS_DEFEATED", getAllCharacters()));
				} else {
					if(getArcanist().isPlayerKnowsName()) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_IMPS_ARCANIST_MET", getAllCharacters()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_IMPS", getAllCharacters()));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isDefeated() && !isImpsDefeated()) {
				if(index==1) {
					return new Response("监督",
							getArcanist().isPlayerKnowsName()
								?"靠近妖狐监工。"
								:"靠近狐女监工。",
							LABORATORY_ARCANIST) {
						@Override
						public void effects() {
							getArcanist().setPlayerKnowsName(true);
						}
					};
				}
			} else if(isDefeated()) {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelLaboratorySearched)) {
						return new Response("搜寻", "你已经搜过实验室了。里面没东西了！", null);
					}
					return new Response("搜寻", "搜寻废墟，搜刮有价值的东西。", LABORATORY_SEARCH) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelLaboratorySearched, true);

							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(1000));

							TransformativePotion effects = ((NPC)getArcanist()).generateTransformativePotion(Main.game.getPlayer());
							AbstractItem potion = EnchantingUtils.craftItem(
								Main.game.getItemGen().generateItem(effects.getItemType()),
								effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
							potion.setName("狐媚子操剂");
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(potion, 1, false, true));
							
							if(isCompanionDialogue()) {
								effects = ((NPC)getArcanist()).generateTransformativePotion(Main.game.getPlayer().getMainCompanion());
								potion = EnchantingUtils.craftItem(
									Main.game.getItemGen().generateItem(effects.getItemType()),
									effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
								potion.setName("狐媚子操剂");
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(potion, 1, false, true));
							}
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode LABORATORY_SEARCH = new DialogueNode("", "", false, true) {
	
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
	
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_SEARCH", getAllCharacters());
		}
	
		@Override
		public Response getResponse(int responseTab, int index) {
			return LABORATORY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode LABORATORY_ARCANIST = new DialogueNode("", "。", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			//TODO check for mouth blocked
			//TODO add conversation action?
			UtilText.nodeContentSB.setLength(0);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelArcanistEncountered)) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_REPEAT_NO_TF", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_REPEAT", getAllCharacters()));
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST", getAllCharacters()));
			}
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("拒绝", UtilText.parse(getArcanist(), "对[npc.name]说不， 让[npc.herHim]回去工作。"), LABORATORY_ARCANIST_EXIT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistEncountered, true);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_DECLINE", getAllCharacters()));
					}
				};
				
			// Action 2: Player accepts TF potion.
			// If already drank TF potion, or is already a fox-morph, action is for performing oral sex.
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF) || Main.game.getPlayer().getRace()==Race.FOX_MORPH) {
					if(!Main.game.getPlayer().isFeminine()) {
						return new Response(isCompanionDialogue()?"口交(单人)":"口交",
								UtilText.parse(getArcanist(), "因为[npc.sheIsFull]是女性恋，[npc.name]对你给她口交不感兴趣。"),
								null);
					} else {
						return new ResponseSex(isCompanionDialogue()?"口交(单人)":"口交",
								UtilText.parse(getArcanist(), "同意按[npc.Name]说的做，然后跪下来给她口交。"),
								true,
								false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))){
									@Override
									public boolean isPositionChangingAllowed(GameCharacter character) {
										return false;
									}
								},
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								LABORATORY_ARCANIST_POST_SEX,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ARCANIST_SOLO_START_SEX", getAllCharacters()));
					}
				}
				return new Response(isCompanionDialogue()?"接受(单人)":"接受",
						UtilText.parse(getArcanist(), "同意喝[npc.namePos]的药水。<br/>"
								+ "这将[style.italicsTfGeneric(把你转化为狐女)]，且可能会带来额外的非种族转化效果！"),
						LABORATORY_ARCANIST_SOLO_TF) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistEncountered, true);

						TransformativePotion effects = ((NPC)getArcanist()).generateTransformativePotion(Main.game.getPlayer());
						AbstractItem potion = EnchantingUtils.craftItem(
							Main.game.getItemGen().generateItem(effects.getItemType()),
							effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
						Main.game.getTextEndStringBuilder().append(getArcanist().useItem(potion, Main.game.getPlayer(), false));
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_SOLO_TF_OFFER_SEX", getAllCharacters()));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF, true);
					}
					@Override
					public Colour getHighlightColour() {
						return PresetColour.TRANSFORMATION_GENERIC;
					}
				};

			// Action 3: Companion accepts TF potion.
			// If already drank TF potion, or is already a fox-morph, action is for getting companion to perform oral sex.
			} else if(index==3 && isCompanionDialogue()) {
				if((Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF) || getMainCompanion().getRace()==Race.FOX_MORPH) && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					if(!getMainCompanion().isAttractedTo(getArcanist()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response(
								UtilText.parse(getMainCompanion(), "口交([npc.name])"),
								UtilText.parse(getArcanist(), getMainCompanion(),
									"[npc2.name]没被[npc2.name]诱惑，[npc2.she]将拒绝与[npc.herHim]发生性关系。而你也不能强迫[npc2.herHim]这样做……"),
								null);
						
					} else if(!getMainCompanion().isFeminine()) {
						return new Response(
								UtilText.parse(getMainCompanion(), "口交([npc.name])"),
								UtilText.parse(getArcanist(), getMainCompanion(), "因为[npc.sheIsFull]是女性恋，[npc.name]对[npc2.name]给她口交不感兴趣。"),
								null);
						
					} else {
						return new ResponseSex(
								UtilText.parse(getMainCompanion(), "口交([npc.name])"),
								UtilText.parse(getArcanist(), getMainCompanion(), "让[npc2.name]跪下，你旁观[npc2.her]给[npc.Name]口交。"),
								true,
								false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL))) {
									@Override
									public boolean isPositionChangingAllowed(GameCharacter character) {
										return false;
									}
								},
								null,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								LABORATORY_ARCANIST_POST_SEX,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ARCANIST_COMPANION_START_SEX", getAllCharacters()));
					}
				}
				if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive() && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response(
							UtilText.parse(getMainCompanion(), "接受([npc.name])"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"因为[npc2.name]不愿意被转化，[npc2.she]将拒绝喝下[npc.namePos]药水。你也不能强迫[npc2.herHim]喝下它……"),
							null);
				}
				if(getMainCompanion().getRace()==Race.FOX_MORPH) {
					return new Response(UtilText.parse(
							getMainCompanion(), "接受([npc.name])"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"由于[npc2.name]已经是狐化形了，所以[npc.Name]不愿意对[npc2.herHim]使用药水……"),
							null);
				}
				if(getMainCompanion().getRace()==Race.DEMON || (getMainCompanion().isElemental())) {
					return new Response(UtilText.parse(
							getMainCompanion(), "接受([npc.name])"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"由于[npc2.name]已经是恶魔了，因此无法转化，[npc.Name]不愿意在[npc2.herHim]身上浪费药水……"),
							null);
				}
				return new Response(
						UtilText.parse(getMainCompanion(), "接受([npc.name])"),
						UtilText.parse(getArcanist(), getMainCompanion(),
								"让[npc2.name]喝下[npc.namePos]的药水。<br/>"
								+ "这将[style.italicsTfGeneric(把[npc2.name]转化为狐女)]，且可能会带来额外的非种族转化效果！"),
						LABORATORY_ARCANIST_COMPANION_TF) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistEncountered, true);
						
						TransformativePotion effects = ((NPC)getArcanist()).generateTransformativePotion(getMainCompanion());
						AbstractItem potion = EnchantingUtils.craftItem(
							Main.game.getItemGen().generateItem(effects.getItemType()),
							effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
						Main.game.getTextEndStringBuilder().append(getArcanist().useItem(potion, getMainCompanion(), false));
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_COMPANION_TF_OFFER_SEX", getAllCharacters()));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF, true);
					}
					@Override
					public Colour getHighlightColour() {
						return PresetColour.TRANSFORMATION_GENERIC;
					}
				};

			// Action 4: Both player and companion accept TF potion.
			// If already drank TF potion, or both are already fox-morphs, action is for performing oral sex.
			} else if(index==4 && isCompanionDialogue()) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF) || (Main.game.getPlayer().getRace()==Race.FOX_MORPH && getMainCompanion().getRace()==Race.FOX_MORPH)) {
					if(!getMainCompanion().isAttractedTo(getArcanist()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response(
								"口交(双人)",
								UtilText.parse(getArcanist(), getMainCompanion(),
									"[npc2.name]没被[npc2.name]诱惑，[npc2.she]将拒绝与[npc.herHim]发生性关系。而你也不能强迫[npc2.herHim]这样做……"),
								null);
						
					} else if(!Main.game.getPlayer().isFeminine()) {
						return new Response(
								"口交(双人)",
								UtilText.parse(getArcanist(), "因为[npc.sheIsFull]是女性恋，[npc.name]对你给她口交不感兴趣。"),
								null);
						
					} else if(!getMainCompanion().isFeminine()) {
						return new Response(
								"口交(双人)",
								UtilText.parse(getArcanist(), getMainCompanion(), "因为[npc.sheIsFull]是女性恋，[npc.name]对[npc2.name]给她口交不感兴趣。"),
								null);
						
					} else {
						return new ResponseSex(
								UtilText.parse(getMainCompanion(), "口交(双人)"),
								UtilText.parse(getArcanist(), getMainCompanion(), "你让[npc2.name]跪下来，一起给[npc.name]口交。"),
								true,
								false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
												new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL_TWO))) {
									@Override
									public boolean isPositionChangingAllowed(GameCharacter character) {
										return false;
									}
								},
								null,
								null,
								LABORATORY_ARCANIST_POST_SEX,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ARCANIST_BOTH_START_SEX", getAllCharacters()));
					}
				}
				if(getMainCompanion().getRace()==Race.FOX_MORPH) {
					return new Response(UtilText.parse(
							getMainCompanion(), "接受(双人)"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"由于[npc2.name]已经是狐化形了，所以[npc.Name]不愿意对[npc2.herHim]使用药水……"),
							null);
				}
				if(getMainCompanion().getRace()==Race.DEMON || (getMainCompanion().isElemental())) {
					return new Response(UtilText.parse(
							getMainCompanion(), "接受(双人)"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"[npc2.name]已经是恶魔了，因此无法转化，[npc.Name]也不愿意在[npc2.herHim]身上浪费药水……"),
							null);
				}
				if(Main.game.getPlayer().getRace()==Race.FOX_MORPH) {
					return new Response(UtilText.parse(
							getMainCompanion(), "接受(双人)"),
							UtilText.parse(getArcanist(),
								"因为你已经是狐化形了，[npc.Name]不愿意对你使用药剂……"),
							null);
				}
				if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive() && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(
							getMainCompanion(), "接受(双人)"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"因为[npc2.name]不愿意被转化，[npc2.she]将拒绝喝下[npc.namePos]的药水。你也不能强迫[npc2.herHim]喝下它……"),
							null);
				}
				return new Response("接受(双飞)", 
						UtilText.parse(getArcanist(), getMainCompanion(),
								"同意与[npc2.name]共享[npc.namePos]的药水。<br/>"
								+ "这将[style.italicsTfGeneric(把你与[npc2.name]都转化为狐女)]，且可能会带来额外的非种族转化效果！"),
						LABORATORY_ARCANIST_BOTH_TF) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistEncountered, true);

						TransformativePotion effects = ((NPC)getArcanist()).generateTransformativePotion(Main.game.getPlayer());
						AbstractItem potion = EnchantingUtils.craftItem(
							Main.game.getItemGen().generateItem(effects.getItemType()),
							effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
						Main.game.getTextEndStringBuilder().append(getArcanist().useItem(potion, Main.game.getPlayer(), false));
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ARCANIST_BOTH_TF", getAllCharacters()));

						effects = ((NPC)getArcanist()).generateTransformativePotion(getMainCompanion());
						potion = EnchantingUtils.craftItem(
							Main.game.getItemGen().generateItem(effects.getItemType()),
							effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
						Main.game.getTextEndStringBuilder().append(getArcanist().useItem(potion, getMainCompanion(), false));
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_BOTH_TF_OFFER_SEX", getAllCharacters()));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF, true);
					}
					@Override
					public Colour getHighlightColour() {
						return PresetColour.TRANSFORMATION_GENERIC;
					}
				};
				
			// Action 5: Combat
			} else if(index==5) {
				return new Response("攻击",
						"你不能让这个狡猾的狐狸以这种方式侮辱你！<br/>"
							+ "<i>你认为城堡里大约有[style.boldBad(三十只小恶魔)]，他们会一波接一波来帮助他们的盟友，所以准备好与他们战斗吧！</i>",
						IMP_CHALLENGE) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ARCANIST_FIGHT_START", getAllCharacters()));
						Main.game.getDialogueFlags().impCitadelImpWave = 0;
						spawnImps();
						getArcanist().setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistEncountered, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode LABORATORY_ARCANIST_EXIT = new DialogueNode("", "。", false) {

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LABORATORY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode LABORATORY_ARCANIST_SOLO_TF = new DialogueNode("", "。", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_SOLO_TF", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("拒绝",
						UtilText.parse(getArcanist(),
								"告诉[npc.Name]你没有兴趣与[npc.herHim]发生关系。"),
						LABORATORY_ARCANIST_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_SOLO_TF_SEX_DECLINED", getAllCharacters()));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("口交",
						UtilText.parse(getArcanist(), "你同意按[npc.Name]说的做，跪下来给[npc.herHim]口交。"),
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))){
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
						},
						null,
						Util.newArrayListOfValues(getMainCompanion()),
						LABORATORY_ARCANIST_POST_SEX,
						UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_SOLO_TF_SEX_ACCEPTED", getAllCharacters()));
				
			}
			return null;
		}
	};

	public static final DialogueNode LABORATORY_ARCANIST_COMPANION_TF = new DialogueNode("", "。", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_COMPANION_TF", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("拒绝",
						UtilText.parse(getArcanist(), getMainCompanion(),
								"告诉[npc.Name]你没有兴趣让[npc2.name]给[npc.herHim]口交。"),
						LABORATORY_ARCANIST_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_COMPANION_TF_SEX_DECLINED", getAllCharacters()));
					}
				};
				
			} else if(index==2 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				if(!getMainCompanion().isAttractedTo(getArcanist()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response(
							UtilText.parse(getMainCompanion(), "同意"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"[npc2.name]没被[npc2.name]诱惑，[npc2.she]将拒绝与[npc.herHim]发生性关系。而你也不能强迫[npc2.herHim]这样做……"),
							null);
				} 
				return new ResponseSex("同意",
						UtilText.parse(getArcanist(), getMainCompanion(), "你同意按[npc.name]说的做，让[npc2.name]在你看着的情况下跪下为[npc.herHim]口交。"),
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL))){
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
						},
						null,
						Util.newArrayListOfValues(Main.game.getPlayer()),
						LABORATORY_ARCANIST_POST_SEX,
						UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_COMPANION_TF_SEX_ACCEPTED", getAllCharacters()));
			}
			
			return null;
		}
	};

	public static final DialogueNode LABORATORY_ARCANIST_BOTH_TF = new DialogueNode("", "。", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_BOTH_TF", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("拒绝",
						UtilText.parse(getArcanist(),
								"告诉[npc.Name]你没有兴趣与[npc.herHim]发生关系。"),
						LABORATORY_ARCANIST_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_BOTH_TF_SEX_DECLINED", getAllCharacters()));
					}
				};
				
			} else if(index==2) {
				if(!getMainCompanion().isAttractedTo(getArcanist()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response(
							UtilText.parse(getMainCompanion(), "同意"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"[npc2.name]没被[npc2.name]诱惑，[npc2.she]将拒绝与[npc.herHim]发生性关系。而你也不能强迫[npc2.herHim]这样做……"),
							null);
				} 
				return new ResponseSex("同意",
						UtilText.parse(getArcanist(), getMainCompanion(), "同意按[npc.name]说的做，你让[npc2.name]和你一起跪下给[npc.name]口交。"),
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
										new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL_TWO))){
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						LABORATORY_ARCANIST_POST_SEX,
						UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_BOTH_TF_SEX_ACCEPTED", getAllCharacters()));
			}
			
			return null;
		}
	};

	public static final DialogueNode LABORATORY_ARCANIST_POST_SEX = new DialogueNode("结束", "", true) {
		
		@Override
		public String getDescription(){
			return "[citadelArcanist.Name]跟你做完之后，她推开你走了。";
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().contains(Main.game.getPlayer())) {
				if(Main.sex.getAllParticipants().contains(getMainCompanion())) {
					return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_POST_SEX_BOTH", getAllCharacters());
				} else {
					return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_POST_SEX_SOLO", getAllCharacters());
				}
			} else {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_POST_SEX_COMPANION", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};

	public static final DialogueNode TREASURY = new DialogueNode("宝藏", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelTreasurySearched)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "TREASURY_SEARCHED", getAllCharacters()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "TREASURY_NOT_SEARCHED", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelTreasurySearched)) {
					return new Response("搜寻", "你已经搜过金库了。里面没东西了！", null);
				}
				return new Response("搜寻", "搜寻废墟，搜刮有价值的东西。", TREASURY_SEARCH) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelTreasurySearched, true);
						
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(15000));
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(
										Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_darkSiren_siren_amulet"), PresetColour.CLOTHING_RED_VERY_DARK, PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_PURPLE_DARK, false),
										false));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(
								Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_darkSiren_siren_cloak"), PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_RED_VERY_DARK, PresetColour.CLOTHING_STEEL, false),
								false));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(
								Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_darkSiren_siren_seal"), PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, false),
								false));

						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(
								Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_scythe_scythe"), DamageType.PHYSICAL, Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_RED_DARK)),
								false));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode TREASURY_SEARCH = new DialogueNode("", "", false, true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "TREASURY_SEARCH", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return TREASURY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode KEEP = new DialogueNode("主楼", "。", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !isDefeated();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_DEFEATED", getAllCharacters()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isDefeated()) {
				if(index==1) {
					return new Response("进入", "推开主楼的门走进去。", KEEP_ENTRY) {
						@Override
						public void effects() {
							getBoss().setPlayerKnowsName(true);
						}
					};
					
				} else if(index==2) {
					return new Response("离开", "从门关处退开。", COURTYARD) {
						@Override
						public void effects() {
							Main.game.getPlayer().moveToAdjacentMatchingCellType(false, PlaceType.FORTRESS_DEMON_COURTYARD);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_BACK_OFF", getAllCharacters()));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode KEEP_ENTRY = new DialogueNode("主楼", "。", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isBossEncountered()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_ENTRY_RETURN", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_ENTRY", getAllCharacters()));
			}
			if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_ENTRY_RING", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().hasTraitActivated(Perk.CHUUNI)) {
					return new Response("挑战", UtilText.parse(getBoss(), "和[npc.name]展开世界上两个最伟大的奥术术士之间的决斗！"), KEEP_CHALLENGE);
					
				} else {
					return new Response("挑战", "你不能让自己与这个魅魔进行这样尴尬的对话，但如果你也是中二病的话事情可能会不一样……", null);
				}
				
			} else if(index==2) {
				return new ResponseCombat("战斗", UtilText.parse(getBoss(), "保护自己，和三个恶魔战斗！"), getDemonLeader(), getBossGroup(false), null);
				
			}
				
			return null;
		}
	};
	
	public static final DialogueNode KEEP_DEMONS_DEFEATED = new DialogueNode("胜利", "", true) {

		@Override
		public String getDescription() {
			return "你已击败这些恶魔！";
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_DEMONS_DEFEATED", getAllCharacters()));
			
			if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗", UtilText.parse(getBoss(), "现在[npc.Name]的恶魔同伴被击败了，是时候和[npc.her]战斗了！"), (NPC) getBoss(), Util.newArrayListOfValues(getBoss()), null);

			} else if(index==2 && Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				return new Response("使用指环",
						UtilText.parse(getBoss(), "向[npc.name]展示你有[npc.her]母亲的戒指， 然后骗她把戒指从你手上拿走并戴上！<br/>"
								+ "[style.italicsExcellent(你可以告诉[npc.she]，她将立刻戴上它，这不会导致与[npc.herHim]的战斗！)]"),
						KEEP_CHALLENGE_RING_TRICK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_POST_FIGHT_RING_TRICK", getAllCharacters()));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING_TRICK", getAllCharacters()));
						Main.game.getTextEndStringBuilder().append(getBoss().incrementAffection(Main.game.getPlayer(), -50));
						Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.meraxisTrickedWithRing, true);
						clearFortress(true);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode KEEP_CHALLENGE = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE", getAllCharacters()));
			if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("决斗", UtilText.parse(getBoss(), "接受[npc.namePos]的提议，与[npc.herHim]一对一决斗！"), null, (NPC) getBoss(), Util.newArrayListOfValues(getBoss()), null);

			} else if(index==2 && Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				return new Response("使用指环",
						UtilText.parse(getBoss(), "向[npc.name]展示你有[npc.her]母亲的戒指， 然后骗她把戒指从你手上拿走并戴上！<br/>"
								+ "[style.italicsExcellent(你可以告诉[npc.she]，她将立刻戴上它，这不会导致与[npc.herHim]的战斗！)]"),
						KEEP_CHALLENGE_RING_TRICK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_DUEL_RING_TRICK", getAllCharacters()));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING_TRICK", getAllCharacters()));
						Main.game.getTextEndStringBuilder().append(getBoss().incrementAffection(Main.game.getPlayer(), -50));
						Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
						clearFortress(true);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode KEEP_CHALLENGE_RING_TRICK = new DialogueNode("主楼", "。", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("逃跑",
						"城堡正在崩塌！在被碾碎之前赶紧逃到屈城区安全的地方！",
						KEEP_COLLAPSE_ESCAPE) {
					@Override
					public void effects() {
						applyExit();
						if(isImpsDefeated()) {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING_TRICK_ESCAPE_NO_IMPS", getAllCharacters()));
						} else {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING_TRICK_ESCAPE", getAllCharacters()));
						}
					}
				};

			}
			return null;
		}
	};

	public static final DialogueNode KEEP_COLLAPSE_ESCAPE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("解释",
						UtilText.parse(getBoss(), "向莉西丝的护卫解释发生了什么事。"),
						KEEP_COLLAPSE_ESCAPE_END) {
					@Override
					public void effects() {
						if(Main.game.getCharactersPresent().contains(getBoss())) {
							if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_2_B_SIRENS_CALL) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_GUARDS_EXPLAIN", getAllCharacters()));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_GUARDS_EXPLAIN_PRE_QUEST", getAllCharacters()));
							}
							
						} else {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_GUARDS_ENSLAVE", getAllCharacters()));
						}
						
						if(isImpsDefeated()) {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_GUARDS_NO_IMPS", getAllCharacters()));
						} else {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_GUARDS_IMPS", getAllCharacters()));
						}
						
						clearFortress(true);
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode KEEP_COLLAPSE_ESCAPE_END = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_DEMON.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KEEP_AFTER_COMBAT_VICTORY = new DialogueNode("胜利", "", true) {
		
		@Override
		public String getDescription() {
			return UtilText.parse(getBoss(), "[npc.Name]终于到了失败的边缘！");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(UtilText.parse(getBoss(), "救她"),
						UtilText.parse(getBoss(), "城堡要塌了！趁你们被碾碎之前救下[npc.name]然后拎着[npc.herHim]跑到屈城区安全的地方！"),
						KEEP_COLLAPSE_ESCAPE) {
					@Override
					public void effects() {
						applyExit();
						getBoss().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_DEMON);
						if(isImpsDefeated()) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_COMBAT_HERO_NO_IMPS", getAllCharacters()));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_COMBAT_HERO", getAllCharacters()));
						}
						Main.game.getTextStartStringBuilder().append(getBoss().incrementAffection(Main.game.getPlayer(), 50));

						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_COMBAT_HERO_END", getAllCharacters()));
					}
				};

			} else if (index == 2) {
				return new Response("跑",
						UtilText.parse(getBoss(), "城堡要塌了！在你被碾碎之前放弃[npc.name]然后独自跑到屈城区安全的地方！"),
						KEEP_COLLAPSE_ESCAPE) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementKarma(-25); // Really? You'd just leave her to die? ;_;
						applyExit();
						getBoss().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_DEMON);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_COMBAT", getAllCharacters()));
						Main.game.getTextStartStringBuilder().append(getBoss().incrementAffection(Main.game.getPlayer(), -100));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_COMBAT_END", getAllCharacters()));
					}
				};

			}
			return null;
		}
	};

	
	public static final DialogueNode KEEP_AFTER_COMBAT_DEFEAT = new DialogueNode("主楼", "。", true) {
		
		@Override
		public String getDescription() {
			return "你已被击败！";
		}

		@Override
		public String getContent() {
			// KEEP_CHALLENGE_LEADER_DEFEAT or KEEP_CHALLENGE_BOSS_DEFEAT are appended to the start of this content (from DS's or Male leader's endCombat() methods)
			
			if(Main.game.isNonConEnabled()) { 
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SIREN_VOYEUR", getAllCharacters());
//				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_CHOICES", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_NO_NON_CON", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					return new Response("被扔出去", "三个恶魔拖着你把你扔出城堡。", THROWN_OUT) {
						@Override
						public void effects() {
							applyExit();
						}
					};
				}
				
			} else {
				Map<GameCharacter, SexSlot> domSlots = new HashMap<>();
				
				if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					domSlots.put(Main.game.getNpc(FortressMalesLeader.class), SexSlotAllFours.BEHIND);
				} else if(isCompanionDialogue() && getMainCompanion().hasVagina() && getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					domSlots.put(Main.game.getNpc(FortressMalesLeader.class), SexSlotAllFours.BEHIND_TWO);
				} else {
					domSlots.put(Main.game.getNpc(FortressMalesLeader.class), SexSlotAllFours.BEHIND);
				}
				if(isCompanionDialogue()) {
					domSlots.put(Main.game.getNpc(FortressAlphaLeader.class),
							domSlots.get(Main.game.getNpc(FortressMalesLeader.class))==SexSlotAllFours.BEHIND_TWO
								?SexSlotAllFours.BEHIND
								:SexSlotAllFours.BEHIND_TWO);
					domSlots.put(Main.game.getNpc(FortressFemalesLeader.class), SexSlotAllFours.IN_FRONT);
				} else {
					domSlots.put(Main.game.getNpc(FortressAlphaLeader.class), SexSlotAllFours.IN_FRONT);
					domSlots.put(Main.game.getNpc(FortressFemalesLeader.class), SexSlotAllFours.IN_FRONT_TWO);
				}
				
				Map<GameCharacter, SexSlot> subSlots = new HashMap<>();

				subSlots.put(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS);
				if(isCompanionDialogue()) {
					subSlots.put(getMainCompanion(), SexSlotAllFours.ALL_FOURS_TWO);
				}
				
				SexManagerInterface manager = new SMAllFours(
						domSlots,
						subSlots) {
					@Override
					public boolean isPositionChangingAllowed(GameCharacter character) {
						return false;
					}
					@Override
					public SexPace getStartingSexPaceModifier(GameCharacter character) {
						if(character.isPlayer()) {
							if(index==2) {
								return SexPace.SUB_EAGER;
							} else if(index==3) {
								return SexPace.SUB_RESISTING;
							}
						}
						return null;
					}
				};
				
				if(index==1) {
					return new ResponseSex(
							"性爱",
							isCompanionDialogue()
								?UtilText.parse(getArcanist(), getMainCompanion(), "接受你失败的代价：和[com.name]一起被三个恶魔操。")
								:UtilText.parse(getArcanist(), "接受失败的代价，准备好被三个恶魔操吧。"),
							false,
							false,
							manager,
							Util.newArrayListOfValues(getBoss()),
							null,
							KEEP_AFTER_COMBAT_DEFEAT_POST_SEX,
							UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
					
				} else if(index==2) {
					return new ResponseSex(
							"做爱(渴求)",
							isCompanionDialogue()
								?UtilText.parse(getArcanist(), getMainCompanion(), "你高兴于即将发生的事情，你渴望和[com.name]一起被三个恶魔操。")
								:UtilText.parse(getArcanist(), "高兴的结束你的失败，然后急切的准备被三个恶魔操。"),
							false,
							false,
							manager,
							Util.newArrayListOfValues(getBoss()),
							null,
							KEEP_AFTER_COMBAT_DEFEAT_POST_SEX,
							UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex(
							"抵抗做爱",
							UtilText.parse(getArcanist(), "你奋力抵抗，试图将三个恶魔从你身上推开。"),
							false,
							false,
							manager,
							Util.newArrayListOfValues(getBoss()),
							null,
							KEEP_AFTER_COMBAT_DEFEAT_POST_SEX,
							UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()));
				}
				
			}
			

			return null;
			
			
			// Removed for now due to unexpected complexity. Will be added at some point.
			
//			if (index == 1) {
//				return new Response(UtilText.parse(Main.game.getNpc(FortressMalesLeader.class), "[npc.Name]"),
//						UtilText.parse(Main.game.getNpc(FortressMalesLeader.class), getBoss(),
//								"Tell [npc2.name] that you submit to [npc.name]...<br/><i>It's obvious from what [npc.sheHas] just said that [npc.she] wants to turn you into an imp broodmother!</i>"),
//						PRISONER_INITIAL_SCENE) {
//					@Override
//					public void effects() {
//						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelPrisonerMale, true);
//						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CHOOSE_MALE", getAllCharacters()));
//					}
//				};
//				
//			} else if (index == 2) {
//				return new Response(UtilText.parse(Main.game.getNpc(FortressAlphaLeader.class), "[npc.Name]"),
//						UtilText.parse(Main.game.getNpc(FortressAlphaLeader.class), getBoss(),
//								"Tell [npc2.name] that you submit to [npc.name]...<br/><i>It's obvious from what [npc.sheHas] just said that [npc.she] wants to abuse you and turn you into [npc.her] worthless cum-dump!</i>"),
//						PRISONER_INITIAL_SCENE) {
//					@Override
//					public void effects() {
//						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelPrisonerAlpha, true);
//						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CHOOSE_ALPHA", getAllCharacters()));
//					}
//				};
//				
//			} else if (index == 3) {
//				return new Response(UtilText.parse(Main.game.getNpc(FortressFemalesLeader.class), "[npc.Name]"),
//						UtilText.parse(Main.game.getNpc(FortressFemalesLeader.class), getBoss(),
//								"Tell [npc2.name] that you submit to [npc.name]...<br/><i>It's obvious from what [npc.sheHas] just said that [npc.she] wants to give you a big cock and put you to work breeding imps!</i>"),
//						PRISONER_INITIAL_SCENE) {
//					@Override
//					public void effects() {
//						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelPrisonerFemale, true);
//						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CHOOSE_FEMALE", getAllCharacters()));
//					}
//				};
//				
//			} else if (index == 4) {
//				return new Response("Refuse",
//						UtilText.parse(getBoss(),
//								"Tell [npc.name] that you refuse to submit to anyone!<br/><i>It's obvious from what [npc.sheHas] just said that [npc.she] will let the demons decide amongst themselves as to who gets possession of you!</i>"),
//						PRISONER_INITIAL_SCENE) {
//					@Override
//					public void effects() {
//						if(Main.game.getPlayer().hasVagina()) {
//							if(Main.game.getPlayer().isFeminine()) {
//								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelPrisonerMale, true);
//								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CHOOSE_REFUSE_MALE", getAllCharacters()));
//							} else {
//								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelPrisonerAlpha, true);
//								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CHOOSE_REFUSE_ALPHA", getAllCharacters()));
//							}
//						} else {
//							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelPrisonerFemale, true);
//							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CHOOSE_REFUSE_FEMALE", getAllCharacters()));
//						}
//					}
//				};
//				
//			} else {
//				return null;
//			}
		}
	};
	
	public static final DialogueNode THROWN_OUT = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			if(isImpsDefeated()) {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "THROWN_OUT_NO_IMPS", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "THROWN_OUT", getAllCharacters());
			}
			
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_DEMON.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KEEP_AFTER_COMBAT_DEFEAT_POST_SEX = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_POST_SEX", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被扔出去", "三个恶魔拖着你把你扔出城堡。", THROWN_OUT) {
					@Override
					public void effects() {
						applyExit();
					}
				};
			}
			return null;
		}
	};
	
	
	// Kept as prisoner dialogue: TODO Come back to this and add it another time.

	private static boolean isPrisonerMale() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerMale);
	}

	private static boolean isPrisonerFemale() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerFemale);
	}
	
	private static boolean isPrisonerAlpha() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerAlpha);
	}
	
	private static NPC getOwner() {
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerAlpha)) {
			return Main.game.getNpc(FortressAlphaLeader.class);
		} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerFemale)) {
			return Main.game.getNpc(FortressFemalesLeader.class);
		}
		return Main.game.getNpc(FortressMalesLeader.class);
	}
	
	private static String getOwnerDialogueIdEnding() {
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerAlpha)) {
			return "_ALPHA";
		} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerFemale)) {
			return "_FEMALE";
		}
		return "_MALE";
	}
	
	private static boolean isPrisoner() {
		return isPrisonerMale() || isPrisonerFemale() || isPrisonerAlpha();
	}
	
	private static int cellTimePassed = 0;
	
	/* TODO
		She tells the one you chose to fuck you in front of her
		Then you are put in cells.
		Wake up
			Youko feeds you potion to TF into chosen type.
			Demon puts bitch collar on you, then fucks you
	 */
	
	// TODO parsing allCharacters() needs owner as second npc
	
	public static final DialogueNode PRISONER_INITIAL_SCENE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("被剥夺",
						UtilText.parse(getOwner(), "[npc.Name]剥夺了你所有未被封印的衣服！"),
						isCompanionDialogue()
							?PRISONER_INITIAL_SCENE_COMPANION_STRIP
							:PRISONER_STRIPPED) {
					@Override
					public void effects() {
						//TODO remove everything like in Rat Warrens captive
//						List<AbstractClothing> clothingRemoved = Main.game.getPlayer().unequipAllClothing(getOwner(), true);
//
//						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED"+getOwnerDialogueIdEnding(), getAllCharacters()));
//						
//						for(AbstractClothing clothing : clothingRemoved) {
//							Main.game.getWorlds().get(WorldType.IMP_FORTRESS_DEMON).getCell(PlaceType.FORTRESS_DEMON_TREASURY).getInventory().addClothing(clothing);
//						}
//						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().getUnequipAllClothingDescription());
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_END"+getOwnerDialogueIdEnding(), getAllCharacters()));
						
						// Equip collar and wrist restraints
						Colour c = PresetColour.CLOTHING_PINK_HOT;
						if(isPrisonerFemale()) {
							c = PresetColour.CLOTHING_BLACK;
						}
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_choker", c, false), true, getOwner()));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_restraints", c, false), true, getOwner()));
						
						//TODO siren wants to watch them fuck your face(s)
						if(getOwner() instanceof FortressFemalesLeader) {
							((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
						}
					}
				};
			}
			return null;
		}
	};


	public static final DialogueNode PRISONER_INITIAL_SCENE_COMPANION_STRIP = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(UtilText.parse(getMainCompanion(), "[npc.NamePos]的回合"),
						UtilText.parse(getOwner(), getMainCompanion(), "[npc.Name]脱下了你同伴所有未被封印的衣物！"),
						PRISONER_STRIPPED) {
					@Override
					public void effects() {
						//TODO remove everything like in Rat Warrens captive
//						List<AbstractClothing> clothingRemoved = getMainCompanion().unequipAllClothing(getOwner(), true);
//						
//						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_COMPANION"+getOwnerDialogueIdEnding(), getAllCharacters()));
//						clothingRemoved = getMainCompanion().unequipAllClothing(getOwner(), true);
//						for(AbstractClothing clothing : clothingRemoved) {
//							Main.game.getWorlds().get(WorldType.IMP_FORTRESS_DEMON).getCell(PlaceType.FORTRESS_DEMON_TREASURY).getInventory().addClothing(clothing);
//						}
//						Main.game.getTextEndStringBuilder().append(getMainCompanion().getUnequipAllClothingDescription());
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_COMPANION_END"+getOwnerDialogueIdEnding(), getAllCharacters()));

						// Equip collar and wrist restraints
						Colour c = PresetColour.CLOTHING_PINK_LIGHT;
						if(isPrisonerFemale()) {
							c = PresetColour.CLOTHING_BROWN_DARK;
						}
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_choker", c, false), true, getOwner()));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_restraints", c, false), true, getOwner()));
						
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode PRISONER_STRIPPED = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			
			Map<GameCharacter, SexSlot> subSlots;
			if(isCompanionDialogue()) {
				subSlots = Util.newHashMapOfValues(
						new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
						new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL_TWO));
			} else {
				subSlots = Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL));
			}
			
			SexManagerInterface manager = new SMStanding(
					Util.newHashMapOfValues(new Value<>(getOwner(), SexSlotStanding.STANDING_DOMINANT)),
					subSlots) {
				@Override
				public boolean isPositionChangingAllowed(GameCharacter character) {
					return false;
				}
				@Override
				public SexPace getStartingSexPaceModifier(GameCharacter character) {
					if(character.isPlayer()) {
						if(index==2) {
							return SexPace.SUB_EAGER;
						} else if(index==3) {
							return SexPace.SUB_RESISTING;
						}
					}
					return null;
				}
			};
			
			if(index==1) {
				return new ResponseSex(
						"口交",
						isCompanionDialogue()
							?UtilText.parse(getOwner(), getMainCompanion(), "你接受失败，准备和[com.name]一起给[npc.name]口交。")
							:UtilText.parse(getOwner(), "你接受失败，准备给[npc.name]口交。"),
						false,
						false,
						manager,
						Util.newArrayListOfValues(getBoss()),
						null,
						PRISONER_STRIPPED_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_START_ORAL"+getOwnerDialogueIdEnding(), getAllCharacters()));
				
			} else if(index==2) {
				return new ResponseSex(
						"口交(渴求)",
						isCompanionDialogue()
							?UtilText.parse(getOwner(), getMainCompanion(), "你高兴地接受这个要求，你渴望地和[com.name]一起准备给[npc.name]口交。")
							:UtilText.parse(getOwner(), "你高兴地接受这个要求，渴望地准备给[npc.name]口交。"),
						false,
						false,
						manager,
						Util.newArrayListOfValues(getBoss()),
						null,
						PRISONER_STRIPPED_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_START_ORAL_EAGER"+getOwnerDialogueIdEnding(), getAllCharacters()));

			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex(
						"拒绝口交",
						UtilText.parse(getOwner(), "你奋力抵抗，试图将[npc.name]从你身上推开。"),
						false,
						false,
						manager,
						Util.newArrayListOfValues(getBoss()),
						null,
						PRISONER_STRIPPED_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_START_ORAL_RESIST"+getOwnerDialogueIdEnding(), getAllCharacters()));
			}
			
			return null;
		}
	};
	
	public static final DialogueNode PRISONER_STRIPPED_AFTER_SEX = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_AFTER_SEX"+getOwnerDialogueIdEnding(), getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("监狱",
						isCompanionDialogue()
							?UtilText.parse(getOwner(), getMainCompanion(), "[npc.Name]将你与[npc2.name]带进城堡的牢房……")
							:UtilText.parse(getOwner(), "[npc.Name]把你带到城堡的牢房……"),
							CELLS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_CELLS);
						cellTimePassed = Main.game.getMinutesUntilTimeInMinutes(7*60);
						if(cellTimePassed<120) {
							cellTimePassed+=24*60;
						}
					}
				};	
			}
			return null;
		}
	};
	
	public static final DialogueNode CELLS = new DialogueNode("监狱", "", false) {

		@Override
		public int getSecondsPassed() {
			return cellTimePassed*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isPrisoner()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CELL"+getOwnerDialogueIdEnding(), getAllCharacters()));
				
			} else {
				if(isDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "CELLS_RUINS", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "CELLS", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			/* It's ok if some are randomised. I don't mind players save/loading to try to get different events.
			(Maximum times/day)Events:
				(1) Fucked by demon - unseals clothing one at a time
				(-) Fucked by imps
				(1) Siren summon
					Use as trophy foot-rest
					Watch imps fuck you
				(-) Milked/cum milked
				(1) DS watches imps fuck you as amusement
				(1) If female owner
					gets you to breed trespasser she caught
				(1) If alpha owner
					Tattoos you
					Punishes you
					Orifice training
					Walks around citadel
			*/
			
			if(isPrisoner()) {
				if(Main.game.getHourOfDay()<=8) { // Wake up event
					// Imp arrives to give breakfast. Imp is generated based on player's SO.
					// Fucks player's throat. If player refuses, gain ring gag.
					// If player has cum addict, the imp cums on food.
					
					// TF
					
				} else if(Main.game.getHourOfDay()<=11) { // Morning event
					// If being milked, append milking description
					if(getOwner().getMinutesSinceLastTimeHadSex()>36*60) {
						// Demon sex. If player refuses, gain spreader bar.
					}
					
				} else if(Main.game.getHourOfDay()<=14) { // Lunch event
					// Imp feeds you. If player has cum addict, the imp cums on food.

					// TF
					
				} else if(Main.game.getHourOfDay()<=18) { // Afternoon event
					// Use same as morning
					
				} else if(Main.game.getHourOfDay()<=21) { // Dinner event

					// TF
					
				} else if(Main.game.getHourOfDay()<=24) { // Night event
					// Sleep or Escape chance
				}
			}
			
			return null;
		}
	};
	

	public static final DialogueNode PRISONER_BREAKFAST = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_BREAKFAST"+getOwnerDialogueIdEnding(), getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("监狱",
						isCompanionDialogue()
							?UtilText.parse(getOwner(), getMainCompanion(), "[npc.Name]将你与[npc2.name]带进城堡的牢房……")
							:UtilText.parse(getOwner(), "[npc.Name]把你带到城堡的牢房……"),
							CELLS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_CELLS);
					}
				};	
			}
			return null;
		}
	};
}
