package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.QuickTransformations;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.FetishPotion;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.10
 * @version 0.3.7.4
 * @author Innoxia
 */
public class AlleywayDemonDialogue {

	private static boolean talked = false;
	private static boolean transformationsApplied = false;
	
	private static boolean isCanal() {
		AbstractPlaceType pt = getDemon().getLocationPlace().getPlaceType();
		return pt.equals(PlaceType.DOMINION_ALLEYS_CANAL_CROSSING)
				|| pt.equals(PlaceType.DOMINION_CANAL)
				|| pt.equals(PlaceType.DOMINION_CANAL_END);
	}
	
	private static boolean isWantsToFight() {
		return getDemon().isAffectionAggressionTrigger(Main.game.getPlayer());
	}

	private static boolean isCompanionDialogue() {
		return Main.game.getPlayer().hasCompanions();
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static NPC getDemon() {
		return Main.game.getActiveNPC();
	}
	
	private static void applyPregnancyReactions() {
		if(getDemon().isVisiblyPregnant()){
			getDemon().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(getDemon(), true);
		}
		if(isCompanionDialogue() && getMainCompanion().isVisiblyPregnant()) {
			getMainCompanion().setCharacterReactedToPregnancy(getDemon(), true);
		}
	}
	
	private static String getStatus() {
		return AffectionLevel.getAttitudeDescription(getDemon(), Main.game.getPlayer(), true);
	}
	
	public static final DialogueNode DEMON_ATTACK = new DialogueNode("敌袭！", "一个家伙从阴影中跳了出来！", true) {
		@Override
		public void applyPreParsingEffects() {
			if(!isWantsToFight()) { // Catch for if the player has somehow raised this npc's affection without using the talk option. Should be impossible but someone managed it somehow (maybe with mods)
				getDemon().setPlayerKnowsName(true);
			}
			talked = false;
			getDemon().generatePostCombatPotions();
			transformationsApplied = false;
			Main.game.getDialogueFlags().setFlag("innoxia_alleyway_transformations_applied", false);
			
			if(getDemon().getPlayerSurrenderCount()>=4) { 
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) { // Even if immune, only give fuck option as others dno't make sense to trigger during a storm
					Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 4);
				} else {
					Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", Util.random.nextInt(6)+1);
					if(Main.game.getDialogueFlags().getSavedLong("randomResponseIndex")==1 && Main.game.getPlayer().getMoney()<Main.game.getDialogueFlags().getMuggerDemand3()) {
						Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 2);
					}
					if(Main.game.getDialogueFlags().getSavedLong("randomResponseIndex")==6
							&& ((Main.game.getPlayer().getTattooInSlot(InventorySlot.GROIN)!=null || !Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
										&& (!Main.game.isAnalContentEnabled() || Main.game.getPlayer().getTattooInSlot(InventorySlot.TORSO_UNDER)!=null || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)))) {
						Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 4);
					}
					if(Main.game.getDialogueFlags().getSavedLong("randomResponseIndex")==4 && (!getDemon().isAttractedTo(Main.game.getPlayer()) || getDemon().hasStatusEffect(StatusEffect.RECOVERING_AURA))) {
						Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 5);
					}
				}
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			boolean pregnancyReaction = false;
			
			if(getDemon().getLastTimeEncountered() != -1) {
				if(isWantsToFight()) {
					if(getDemon().getPlayerSurrenderCount()>=4) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_SUBMITTED", getDemon()));
						
					} else if(getDemon().getPlayerSurrenderCount()==3) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_DEMAND_SUBMIT", getDemon()));
						
					} else {
						if(isCanal()) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_CANAL_REPEAT_INTRO", getDemon()));
							
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT_INTRO", getDemon()));
						}
						
						if(getDemon().isVisiblyPregnant()) {
							pregnancyReaction = true;
							
							if(!getDemon().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT_PREGNANCY_REVEAL", getDemon()));
							
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT_STILL_PREGNANT", getDemon()));
							}
						}
						
						if(Main.game.getPlayer().isVisiblyPregnant() || (isCompanionDialogue() && getMainCompanion().isVisiblyPregnant())) {
							pregnancyReaction = true;
							
							if((Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getPlayer().isCharacterReactedToPregnancy(getDemon()))
									|| (isCompanionDialogue() && getMainCompanion().isVisiblyPregnant() && !getMainCompanion().isCharacterReactedToPregnancy(getDemon()))) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT_PLAYER_PREGNANCY", getDemon()));
							
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT_CONTINUED_PLAYER_PREGNANCY", getDemon()));
							}
						}
	
						if(!pregnancyReaction) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT", getDemon()));
						}
						
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_REPEAT_END", getDemon()));
					}
					
				} else { // The mugger doesn't want to attack the player:
					if(isCanal()) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_CANAL_INTRO", getDemon()));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_INTRO", getDemon()));
					}
					
					if(getDemon().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if(!getDemon().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_PREGNANCY_REVEAL", getDemon()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_STILL_PREGNANT", getDemon()));
						}
					}
					
					if(Main.game.getPlayer().isVisiblyPregnant() || (isCompanionDialogue() && getMainCompanion().isVisiblyPregnant())) {
						pregnancyReaction = true;
						
						if((Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getPlayer().isCharacterReactedToPregnancy(getDemon()))
								|| (isCompanionDialogue() && getMainCompanion().isVisiblyPregnant() && !getMainCompanion().isCharacterReactedToPregnancy(getDemon()))) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_PLAYER_PREGNANCY", getDemon()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_CONTINUED_PLAYER_PREGNANCY", getDemon()));
						}
					}
					
					if(!pregnancyReaction) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL", getDemon()));
					}
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_END", getDemon()));

					UtilText.nodeContentSB.append(getStatus());
				}
				
			} else {
				if(isCanal()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_CANAL_INTRO", getDemon()));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_INTRO", getDemon()));
				}

				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK", getDemon()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(getDemon().getPlayerSurrenderCount()>=3) { // Bitch content
				return DialogueManager.getDialogueFromId("innoxia_encounters_dominion_alleyway_demon_start").getResponse(responseTab, index);
			}
			
			if(isWantsToFight()) {
				if (index == 1) {
					return new ResponseCombat("战斗", "挺身而出，与[npc.name]决一胜负！", getDemon()) {
						@Override
						public void effects() {
							applyPregnancyReactions();
						}
					};
					
				} else if (index == 2) {
					return new Response("献上财物", UtilText.parse(getDemon(), "与普通的抢劫者不同，[npc.Name]对你的钱不感兴趣！"), null);
					
				} else if (index == 3) {
					if(getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("献上身体",
								"向[npc.name]献出身体，以避免发生暴力对抗。"
									+"<br/>[style.italicsSex(重复性地向[npc.name]屈服将会导致[npc.herHim]要求你成为[npc.her]的荡妇……)]",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Main.game.getPlayer().getCompanions()) {
									@Override
									public SexControl getSexControl(GameCharacter character) {
										if(character.isPlayer()) {
											return SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS;
										}
										return super.getSexControl(character);
									}
								},
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_OFFER_BODY", getDemon())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								getDemon().incrementPlayerSurrenderCount(1);
							}
						};
						
					} else {
						return new Response("献出身体", "你可以看出来[npc.name]对和你做爱完全不感兴趣。你只能选择要么给[npc.herHim]些钱，要么准备战斗！", null);
					}
					
				} else if (index == 4 && getDemon().isApplyingPostCombatTransformations()) {
					return new Response("投降",
							"向[npc.name]投降，让[npc.herHim]能够对你的身体为所欲为……"
									+"<br/>[style.italicsTfGeneric(这会导致[npc.Name]可能在选择操你之前，会先试着让你喝下转化药水！)]"
									+"<br/>[style.italicsSex(重复性地向[npc.name]屈服将会导致[npc.herHim]要求你成为[npc.her]的荡妇……)]",
								SURRENDER,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_TRANSFORMATION_RECEIVING), Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.TRANSFORMATION_GENERIC;
						}
						@Override
						public void effects() {
							applyPregnancyReactions();
							getDemon().incrementPlayerSurrenderCount(1);
						}
					};
					
				} else if (index == 6 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();
	
					if(!getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new Response(UtilText.parse(companion, "三人行"),
								UtilText.parse(getDemon(), companion, "你看得出来，[npc.name]一点也不想和你做爱，所以也就不会想要和你三人行……"),
								null);
						
					} else if(!getDemon().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "三人行"),
								UtilText.parse(getDemon(), companion, "你看得出来，[npc.name]一点也不想和[npc2.name]做爱，所以也就不会想要和你三人行……"),
								null);
						
					} else if(!companion.isAttractedTo(getDemon()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "三人行"),
								UtilText.parse(getDemon(), companion, "你看得出来[npc2.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[npc2.herHim]这么做……"),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "三人行"),
								UtilText.parse(getDemon(), companion, "让[npc.name]有和你，以及[npc2.name]一起做爱的机会，以此来避免暴力冲突。"),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer(), companion),
										null,
										null,
										ResponseTag.PREFER_DOGGY),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_OFFER_BODY_THREESOME", getDemon(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
					}
					
				} else if (index == 7 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
	
					if(!getDemon().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(getDemon(), companion, "你看得出来[npc.name]完全没兴趣和[npc2.name]做爱……"),
								null);
						
					} else if(!companion.isAttractedTo(getDemon()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(getDemon(), companion, "你看得出来[npc2.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[npc2.herHim]这么做……"),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(getDemon(), companion, "为了避免发生暴力冲突，你告诉[npc.name]，[npc.she]可以享用[npc2.namePos]的身体。"),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(companion),
										null,
										Util.newArrayListOfValues(Main.game.getPlayer())),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_OFFER_COMPANION", getDemon(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								if(!companion.isAttractedTo(getDemon()) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
						};
					}
					
				} else {
					return null;
				}
			
			} else {
				if (index == 1) {
					if(talked) {
						return new Response("对话", "你今天已经跟[npc.Name]谈过话了……", null);
					}
					return new Response("对话", "和[npc.name]聊一会儿，以便于多了解[npc.herHim]一点。", DEMON_PEACEFUL_TALK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 10));
							
							if(getDemon().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 2) {
					return new Response("献上财物", UtilText.parse(getDemon(), "与普通的抢劫者不同，[npc.Name]不需要你的钱！"), null);
					
				} else if (index == 3) {
					if(getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("做爱(支配)", "同[npc.name]做爱，并占据支配地位。",
								Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, Fetish.FETISH_DOMINANT.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getDemon()),
										Main.game.getPlayer().getCompanions(),
										null),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_SEX_AS_DOM", getDemon())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("做爱(支配)", "你看得出[npc.name]不想和你做爱……", null);
					}
					
				} else if (index == 4) {
					if(getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("做爱(顺从)", "向[npc.name]献上身体。",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Main.game.getPlayer().getCompanions()),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_SEX_AS_SUB", getDemon())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("做爱(顺从)", "你看得出[npc.name]不想和你做爱……", null);
					}
					
				} if (index == 5) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) || !getDemon().isAffectionHighEnoughToInviteHome()) {
						return new Response("提供房间",
								"你觉得在邀请[npc.name]回莉莱雅的宅邸之前，最好能花时间多了解[npc.herHim]一些……<br/>"
								+ "[style.italics(要求[npc.name]对你拥有至少"+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()+"的好感。)]",
								null);
						
					} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
						return new Response("提供房间",
								"你要先获得莉莱雅的同意才能邀请[npc.name]到她的宅邸……",
								null);
						
					} else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
						return new Response("提供房间",
								"你没有合适的房间让[npc.name]搬进去。你得先将莉莱雅宅邸中的一个空房间升级为“客房”。",
								null);
						
					}else {
						return new Response("提供房间", "问一问[npc.name]想不想在莉莱雅的宅邸中要一个房间。", DEMON_PEACEFUL_OFFER_ROOM) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 25));
							}
						};
					}
					
				} else if(index==6) {
					if(getDemon().getPlayerSurrenderCount()<3 && getDemon().isApplyingPostCombatTransformations()) {
						if(transformationsApplied) {
							return new Response("开始被转化",
									"[npc.Name]早就把[npc.she]所有的转化药水给你了！",
									null);
							
						} else {
							return new Response("开始被转化",
									"告诉[npc.name]你愿意喝下[npc.she]所拥有的任何转化药水……"
										+"<br/>[style.italicsTfGeneric(这将会导致[npc.name]让你喝下一剂转化药水！)]",
										DEMON_PEACEFUL_TRANSFORMED,
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_TRANSFORMATION_RECEIVING), Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel(), null, null, null) {
								@Override
								public Colour getHighlightColour() {
									return PresetColour.TRANSFORMATION_GENERIC;
								}
								@Override
								public void effects() {
									applyPregnancyReactions();
									Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "PEACEFUL_TRANSFORMATIONS", getDemon()));
									Main.game.appendToTextStartStringBuilder(getDemon().applyPostCombatTransformation());
									transformationsApplied = true;
								}
							};
						}
					}
					
				} else if (index==10) {
					return new Response("攻击", "背叛[npc.namePos]的信任并攻击[npc.herHim]！", DEMON_PEACEFUL_ATTACK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), -50));
							getDemon().addFlag(NPCFlagValue.genericNPCBetrayedByPlayer);
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if (index == 11 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();
	
					if(!getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new Response(UtilText.parse(companion, "三人行"),
								UtilText.parse(getDemon(), companion, "你看得出来，[npc.name]一点也不想和你做爱，所以也就不会想要和你三人行……"),
								null);
						
					} else if(!getDemon().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "三人行"),
								UtilText.parse(getDemon(), companion, "你看得出来，[npc.name]一点也不想和[npc2.name]做爱，所以也就不会想要和你三人行……"),
								null);
						
					} else if(!companion.isAttractedTo(getDemon()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "三人行"),
								UtilText.parse(getDemon(), companion, "你看得出来[npc2.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[npc2.herHim]这么做……"),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "三人行"),
								UtilText.parse(getDemon(), companion, "让[npc.name]拥有和你，以及[npc2.name]一起做爱的机会。"),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer(), companion),
										null,
										null,
										ResponseTag.PREFER_DOGGY),
								AFTER_SEX_PEACEFUL_THREESOME, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_OFFER_BODY_THREESOME", getDemon(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
					}
					
				} else if (index == 12 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
	
					if(!getDemon().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(getDemon(), companion, "你看得出来[npc.name]完全没兴趣和[npc2.name]做爱……"),
								null);
						
					} else if(!companion.isAttractedTo(getDemon()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(getDemon(), companion, "你看得出来[npc2.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[npc2.herHim]这么做……"),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(getDemon(), companion, "告诉[npc.name][npc.she]可以使用[npc2.namePos]身体。"),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(companion),
										null,
										Util.newArrayListOfValues(Main.game.getPlayer())),
								AFTER_SEX_PEACEFUL_OFFERED_COMPANION, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_ATTACK_PEACEFUL_OFFER_COMPANION", getDemon(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								if(!companion.isAttractedTo(getDemon()) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
						};
					}
					
				} else if (index == 0) {
					return new Response("离开", "告诉[npc.name]你急着去别的地方，继续旅程。", Main.game.getDefaultDialogue(false));
				}
				return null;
			}
		}
	};
	
	public static final DialogueNode DEMON_PEACEFUL_TALK = new DialogueNode("谈话", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			talked = true;
		}
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_PEACEFUL_TALK", getDemon()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(getDemon().isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_PEACEFUL_CAN_INVITE_HOME", getDemon()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getDemon()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEMON_ATTACK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DEMON_PEACEFUL_OFFER_ROOM = new DialogueNode("提供房间", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_PEACEFUL_OFFER_ROOM", getDemon());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("带回家", "带[npc.name]到[npc.her]的新房间。", DEMON_PEACEFUL_OFFER_ROOM_BACK_HOME) {
					@Override
					public void effects() {
						Cell c = OccupancyUtil.getFreeRoomForOccupant();
						getDemon().setLocation(c.getType(), c.getLocation(), true);
						Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
						Main.game.getPlayer().addFriendlyOccupant(getDemon());
						Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 10));
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode DEMON_PEACEFUL_OFFER_ROOM_BACK_HOME = new DialogueNode("新房间", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_PEACEFUL_OFFER_ROOM_BACK_HOME", getDemon());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "让[npc.name]安顿下来。", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};

	public static final DialogueNode DEMON_PEACEFUL_TRANSFORMED = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEMON_ATTACK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DEMON_PEACEFUL_ATTACK = new DialogueNode("攻击", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEMON_PEACEFUL_ATTACK", getDemon());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗", "开始与[npc.name]战斗！", getDemon());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL = new DialogueNode("继续", "从[npc.name]身边走开，准备继续你的旅程。", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(getDemon().isSatisfiedFromLastSex()) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_PEACEFUL", getDemon());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_PEACEFUL_NO_ORGASM", getDemon());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL_THREESOME = new DialogueNode("继续", "从[npc.name]身边走开，准备继续你的旅程。", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_PEACEFUL_THREESOME", getDemon());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL_OFFERED_COMPANION = new DialogueNode("继续", "从[npc.name]身边走开，准备继续你的旅程。", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(getMainCompanion().isAttractedTo(getDemon())) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_PEACEFUL_OFFERED_COMPANION", getDemon());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_PEACEFUL_OFFERED_COMPANION_RELUCTANT", getDemon());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("胜利", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getDemon().setPlayerSurrenderCount(0);
			getDemon().clearPetName(Main.game.getPlayer());
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getDescription() {
			return "你打败了[npc.name]！";
		}
		@Override
		public String getContent() {
			if(getDemon().isAttractedTo(Main.game.getPlayer())
					&& !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_ATTRACTION", getDemon());
				
			} else {
				if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_BETRAYED", getDemon());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_NO_ATTRACTION", getDemon());
				}
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			boolean noSex = getDemon().isPostCombatNoSex();
			boolean wantsSex = getDemon().isPostCombatWantsSex();
			boolean rapePlay = getDemon().isPostCombatRapePlay();
			
			if (index == 1) {
				return new Response("继续",
						"继续前行……"
							+ (getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)
								?UtilText.parse(getDemon(), "<br/>[style.italicsBad([npc.Name]将会从游戏中永久移除。)]")
								:""),
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						return super.getHighlightColour();
					}
					@Override
					public void effects() {
						if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getDemon());
						}
					}
				};
				
			} else if (index == 2) {
				if(noSex) {
					return new Response("做爱", "[npc.Name]不想和你做爱！", null);
					
				} else if(wantsSex) {
					return new ResponseSex(rapePlay?"强奸play":"做爱",
							"反正这<i>正是</i>[npc.she]想要的！",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									Main.game.getPlayer().getCompanions(),
									null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", rapePlay?"AFTER_COMBAT_VICTORY_RAPE":"AFTER_COMBAT_VICTORY_SEX", getDemon()));
				} else {
					return new ResponseSex(
							"强奸[npc.herHim]",
							"[npc.she]竟敢对你发起攻击，就该受罚……",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									Main.game.getPlayer().getCompanions(),
									null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_RAPE", getDemon()));
				}
				
			} else if (index == 3) {
				if(noSex) {
					return new Response("做爱(温柔)", "[npc.Name]不想和你做爱！", null);
					
				} else if(wantsSex) {
					return new ResponseSex(rapePlay?"强奸play (温柔)":"做爱(温柔)",
							"反正这<i>正是</i>[npc.she]想要的！",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", rapePlay?"AFTER_COMBAT_VICTORY_RAPE_GENTLE":"AFTER_COMBAT_VICTORY_SEX_GENTLE", getDemon()));
					
				} else {
					return new ResponseSex("强奸[npc.herHim](温柔)",
							"[npc.she]竟敢对你发起攻击，就该受罚……",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getDemon()));
				}
				
			} else if (index == 4) {
				if(noSex) {
					return new Response("做爱(粗暴)", "[npc.Name]对你不感兴趣！", null);
					
				} else if(wantsSex) {
					return new ResponseSex(rapePlay?"强奸play (粗暴)":"做爱(粗暴)",
							"反正这<i>正是</i>[npc.she]想要的！",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", rapePlay?"AFTER_COMBAT_VICTORY_RAPE_ROUGH":"AFTER_COMBAT_VICTORY_SEX_ROUGH", getDemon()));
					
				} else {
					return new ResponseSex("强奸[npc.herHim](粗暴)",
							"[npc.she]竟敢对你发起攻击，就该受罚……",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getDemon()));
				}
				
			} else if (index == 5) {
				if(!getDemon().isAttractedTo(Main.game.getPlayer()) || getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response("屈服",
							"你不能向[npc.herHim]屈服，因为[npc.sheHasFull]对和你做爱没有任何兴趣！",
							null);
				} else {
					return new ResponseSex("顺从",
							"你不太确定现在该做什么……也许最好让[npc.name]来决定接下来做什么？",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getDemon()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									Util.newArrayListOfValues(getMainCompanion())),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getDemon()));
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("物品栏", "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具伸出手……"){
					@Override
					public void effects() {
						Main.mainController.openInventory(getDemon(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 7) {
				if(Main.game.getCurrentDialogueNode()==AFTER_COMBAT_VICTORY_TALK) {
					return new Response("对话", "你已经在和[npc.name]交谈了……", null);
					
				} else if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response("对话", "在背叛了[npc.namePos]的信任之后，[npc.she]不会想再跟你说话。", null);
					
				} else {
					return new Response("对话", "和[npc.name]交谈，问问[npc.herHim]为什么会袭击你。", AFTER_COMBAT_VICTORY_TALK){
						@Override
						public void effects() {
							getDemon().setPlayerKnowsName(true);
							Main.game.getTextEndStringBuilder().append(getDemon().setAffection(Main.game.getPlayer(), 10));
						}
					};
				}
				
			} else if (index == 8 && getDemon().isAbleToSelfTransform()) {
				return new Response("转化[npc.herHim]",
						"仔细观察[npc.name]会将自己转化成什么样……",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getDemon());
					}
				};
				
			} else if (index == 9 && getDemon().isAbleToSelfTransform()) {
				return new Response("快速转化",
						"如果[npc.she]想要的只是性爱，那么你会非常乐意满足她。此外，如果[npc.sheIs]能够转化[npc.herself] ，你也会有一些想法……"
								+ "(你完成[npc.herHim]的转化后将返回选项界面。)",
						QuickTransformations.initQuickTransformations("misc/quickTransformations", getDemon(), AFTER_COMBAT_VICTORY));
			
			} else if (index == 10 && !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"移除角色",
						UtilText.parse(getDemon(), "赶[npc.name]走。"
								+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]"),
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_BANISH_NPC", getDemon()));
						Main.game.banishNPC(getDemon());
					}
				};
				
			} else if (index == 11 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!Main.game.isNonConEnabled() && (!getDemon().isAttractedTo(Main.game.getPlayer()) || !getDemon().isAttractedTo(companion))) {
					return new Response("三人行", UtilText.parse(companion, getDemon(), "[npc2.Name]没兴趣和你或[npc.name]做爱！"), null);
					
				} else if(!companion.isAttractedTo(getDemon())) {
					return new Response(UtilText.parse(companion, "三人行"), UtilText.parse(companion, getDemon(), "[npc.Name]并没有被[npc2.name]所吸引，所以将不会愿意和[npc2.herHim]一起做爱！"), null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "三人行"),
							UtilText.parse(getDemon(), companion, "和[npc.name]来一场支配型性爱，同时让[npc2.name]也来爽一爽。"),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									Util.newArrayListOfValues(getDemon()),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_THREESOME", getDemon(), companion));
				}
				
			} else if (index == 12 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !getDemon().isAttractedTo(companion)) {
					return new Response(UtilText.parse(companion, "给予[npc.name]"), UtilText.parse(companion, getDemon(), "[npc2.Name]并没有被[npc.name]所吸引，所以并不愿意和[npc.herHim]一起做爱！"), null);
					
				} else if(!companion.isAttractedTo(getDemon())) {
					return new Response(UtilText.parse(companion, "给予[npc.name]"), UtilText.parse(companion, getDemon(), "[npc.Name]并没有被[npc2.name]所吸引，所以将不会愿意和[npc2.herHim]一起做爱！"), null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "交给[npc.name]"),
							UtilText.parse(companion, getDemon(), "告诉[npc.name]让[npc.she]和[npc2.name]好好玩玩，你在旁边看着。"),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(companion),
									Util.newArrayListOfValues(getDemon()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getDemon(), companion));
				}
				
			} else if (index == 13 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !companion.isAttractedTo(getDemon())) {
					return new Response(UtilText.parse(companion, "献上[npc.name]"), UtilText.parse(companion, getDemon(), "[npc.Name]没兴趣跟[npc2.name]做爱！"), null);
					
				} else if(!getDemon().isAttractedTo(companion)) {
					return new Response(UtilText.parse(companion, "献上[npc.name]"), UtilText.parse(companion, getDemon(), "[npc2.Name]没兴趣和[npc.name]做爱！"), null);
					
				} else if(!companion.isAttractedTo(getDemon()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "献上[npc.name]"),
							UtilText.parse(getDemon(), companion, "你看得出来[npc2.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[npc2.herHim]这么做……"),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
							UtilText.parse(getDemon(), companion, "告诉[npc.name][npc.she]可以使用[npc2.name]的身体。"),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getDemon()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getDemon(), companion)) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getDemon()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY_TALK = new DialogueNode("交谈", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_TALK", getDemon());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
		}
	};

	private static String applyTransformation(GameCharacter target,
			TransformativePotion potion,
			boolean forcedTF,
			FetishPotion fetishPotion,
			boolean forcedFetish) {
		
		StringBuilder sb = new StringBuilder();
		
		if(potion!=null && forcedTF) {
			sb.append(UtilText.parse(getDemon(), target,
					"<p>"
						+ "[npc.Name]向后退去，看到[npc2.name]听话地喝下了那奇怪的液体，嘴角露出了一抹笑容。"
						+ "[npc.speech(乖[npc2.girl]！我要把你变成我完美的"+getDemon().getPreferredBodyDescription("b")+"啊！)]"
					+ "</p>"));
			sb.append(getDemon().applyPotion(potion, target));
		}
		
		if(fetishPotion!=null && forcedFetish) {
			sb.append(UtilText.parse(getDemon(),
					"<p>"
						+ "[npc.name]的[npc.eyes]投射出恶魔般的喜悦神情，[npc.her]兴奋地喊叫，"
						+ "[npc.speech(这就对了，全都咽下去！这些变化没坏处的！)]"
					+ "</p>"));
			sb.append(getDemon().applyPotion(fetishPotion, target));
		}
		return sb.toString();
	}

	public static final DialogueNode SURRENDER = new DialogueNode("", "", true) {
		public void applyPreParsingEffects() {
			AFTER_COMBAT_DEFEAT.applyPreParsingEffects();
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "SURRENDER", getDemon());		
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_COMBAT_DEFEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("落败", "", true) {

		TransformativePotion potion = null;
		TransformativePotion companionPotion = null;
		FetishPotion fetishPotion = null;
		FetishPotion companionFetishPotion = null;
		
		public void applyPreParsingEffects() {
			transformationsApplied = false;
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getDemon().generateTransformativePotion(Main.game.getPlayer());
				fetishPotion = getDemon().generateFetishPotion(Main.game.getPlayer(), true);
			} else {
				potion = null;
				fetishPotion = null;
			}
			if(isCompanionDialogue()) {
				if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					companionPotion = getDemon().generateTransformativePotion(getMainCompanion());
					companionFetishPotion = getDemon().generateFetishPotion(getMainCompanion(), true);
				} else {
					companionPotion = null;
					companionFetishPotion = null;
				}
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getDescription() {
			return "你被[npc.name]打败了！";
		}
		@Override
		public String getContent() {
			if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_BETRAYED", getDemon());
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_GENERIC_START", getDemon()));
			
			boolean forcedTF = getDemon().isUsingForcedTransform(Main.game.getPlayer());
			boolean forcedFetish = getDemon().isUsingForcedFetish(Main.game.getPlayer());
			boolean companionForcedTF = isCompanionDialogue() && getDemon().isUsingForcedTransform(getMainCompanion());
			boolean companionForcedFetish = isCompanionDialogue() && getDemon().isUsingForcedFetish(getMainCompanion());
			if((forcedTF && potion!=null)
					|| (forcedFetish && fetishPotion!=null)
					|| (companionForcedTF && companionPotion!=null)
					|| (companionForcedFetish && companionFetishPotion!=null)) {
				if(((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null))
						&& ((companionForcedTF || companionPotion!=null) && (companionForcedFetish || companionFetishPotion!=null))) { // Both TF:
					
					if(fetishPotion!=null && forcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_TF_AND_FETISH", getDemon()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_FETISH", getDemon()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_TF", getDemon()));
					}
	
					if(companionFetishPotion!=null && companionForcedFetish) {
						if(companionPotion!=null && companionForcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_COMPANION_TF_AND_FETISH", getDemon()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_COMPANION_FETISH", getDemon()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_COMPANION_TF", getDemon()));
					}
					
					return sb.toString();
					
				} else if((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null)) { // Player TF:
					if(fetishPotion!=null && forcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_TF_AND_FETISH", getDemon()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_FETISH", getDemon()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_TF", getDemon()));
					}
					return sb.toString();
					
				} else if(isCompanionDialogue()) { // Companion TF:
					if(companionFetishPotion!=null && companionForcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_TF_AND_FETISH", getDemon()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_FETISH", getDemon()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_TF", getDemon()));
					}
					return sb.toString();
				}
			}
			
			// If no transformations are going to be applied, just return content (and responses) as though it's the AFTER_COMBAT_TRANSFORMATION node:
			sb.append(AFTER_COMBAT_TRANSFORMATION.getContent());
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if (index == 1) {
					return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.banishNPC(getDemon());
						}
					};
				}
				return null;
			}

			// Response variables:
			boolean forcedTF = getDemon().isUsingForcedTransform(Main.game.getPlayer());
			boolean forcedFetish = getDemon().isUsingForcedFetish(Main.game.getPlayer());
			List<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(
					forcedTF && potion!=null
						?Fetish.FETISH_TRANSFORMATION_RECEIVING
						:null,
					forcedFetish && fetishPotion!=null
						?Fetish.FETISH_KINK_RECEIVING
						:null);
			CorruptionLevel applicableCorruptionLevel = forcedFetish && fetishPotion!=null
					?Fetish.FETISH_KINK_RECEIVING.getAssociatedCorruptionLevel()
					:Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
			boolean multiplePotions = applicableFetishes.size()>1;
			
			// Companion's response variables:
			boolean companionForcedTF = isCompanionDialogue() && getDemon().isUsingForcedTransform(getMainCompanion());
			boolean companionForcedFetish = isCompanionDialogue() && getDemon().isUsingForcedFetish(getMainCompanion());
			boolean companionMultiplePotions = (companionForcedTF && companionPotion!=null) && (companionForcedFetish && companionFetishPotion!=null);
			
			// Swallow/spit responses:
			if((!forcedTF || potion==null)
					&& (!forcedFetish || fetishPotion==null)
					&& (!companionForcedTF || companionPotion==null)
					&& (!companionForcedFetish || companionFetishPotion==null)) {
				return AFTER_COMBAT_TRANSFORMATION.getResponse(responseTab, index);
				
			} else if(((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null))
						&& ((companionForcedTF || companionPotion!=null) && (companionForcedFetish || companionFetishPotion!=null))) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					if(!Collections.disjoint(Main.game.getPlayer().getFetishes(true), applicableFetishes)) {
						return new Response("吐出",
									"由于你的[style.boldFetish("+applicableFetishes.get(0).getName(Main.game.getPlayer())+")]性癖，"
										+ "你太喜欢"+applicableFetishes.get(0).getShortDescriptor(Main.game.getPlayer())+"了，甚至无法吐出转化药剂！",
								null);
					} else {
						return new Response("吐出", 
								UtilText.parse(getMainCompanion(),
										"吐出药水。"
												+ "([npc.Name]更有可能选择"
													+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()?"[style.boldTfGeneric(吞下)]":"[style.boldMinorBad(吐出)]")
													+"自己的药剂！)"),
								AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects(){
								transformationsApplied = true;
								if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SPIT", getDemon()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_COMPANION_SWALLOW", getDemon()));
									Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
									
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_BOTH_SPIT", getDemon()));
								}
							}
						};
					}
					
				} else if (index == 2) {
					return new Response("吞咽",
							UtilText.parse(getMainCompanion(),
								"吞下药水。"
										+ "([npc.Name]更有可能选择"
											+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()?"[style.boldTfGeneric(吞下)]":"[style.boldMinorBad(吐出)]")
											+"自己的药剂！)"),
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							transformationsApplied = true;
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SWALLOW", getDemon()));
							Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
							
							if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_COMPANION_SWALLOW", getDemon()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_COMPANION_SPIT", getDemon()));
							}
						}
					};
					
				} else if (index == 6) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("吐出(双人)");
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("吐出(双人)",
								UtilText.parse(getMainCompanion(),
									"由于你<b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
										+"</b>的性癖，你乐于被转化，所以无法把转化液体吐出来，也不能让[npc.Name]这么做！"),
								null);
						
					} else {
						return new Response("吐出(双人)",
								UtilText.parse(getMainCompanion(),
										"吐出药剂，而且也让[npc.name]吐出来。"
										+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
												?" (然而，因为[npc.name]拥有"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+"的性癖，[npc.sheIsFull]并不愿意听你的。)"
												:"")),
								AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects(){
								transformationsApplied = true;
								if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SPIT", getDemon()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SPIT_REFUSED", getDemon()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_COMPANION_SWALLOW", getDemon()));
									Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
									
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SPIT", getDemon()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SPIT", getDemon()));
								}
							}
						};
						
					}
					
				} else if (index == 7) {
					return new Response("吞咽(双人)",
							UtilText.parse(getMainCompanion(),
									"吞下药剂，而且让[npc.name]也喝下去。"
									+ (getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
											?"(然而，因为[npc.name]对于"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+"性癖有负面情绪，[npc.sheIsFull]并不愿意听你的。)"
											:"")),
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							transformationsApplied = true;
							if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SWALLOW", getDemon()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
								
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SWALLOW", getDemon()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SWALLOW", getDemon()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SWALLOW_REFUSED", getDemon()));
							}
						}
					};
				}
				
			} else if((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null)) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					};
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("吐出",
								"由于你<b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b>的性癖，你乐于被转化，所以无法把转化液体吐出来，也不能让[npc.Name]这么做！",
								null);
					} else {
						return new Response("吐出", "吐出药水。", AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects() {
								transformationsApplied = true;
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SPIT", getDemon()));
							}
						};
					}
					
				} else if (index == 2) {
					return new Response("吞咽",
							"吞下药水。",
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							transformationsApplied = true;
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_SWALLOW", getDemon()));
							Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
						}
					};
					
				} else if (index == 6 && isCompanionDialogue()) {
					return new Response("吞咽(双人)",
							UtilText.parse(getMainCompanion(),
									getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
										?"[npc.Name]没有被强制喂下转化药剂！"
										:"[npc.namePos]的嘴被堵住了，所以[npc.she]无法喝下任何转化药水！"),
							null);
					
				}  else if (index == 7 && isCompanionDialogue()) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("吐出(双人)");
					}
					return new Response("吐出(双人)",
							UtilText.parse(getMainCompanion(),
									getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
										?"[npc.Name]没有被强制喂下转化药剂！"
										:"[npc.namePos]的嘴被堵住了，所以[npc.she]无法喝下任何转化药水！"),
							null);
				}
				
			} else if(isCompanionDialogue()) {
				if (index == 1) {
					return new Response("吐出",
							UtilText.parse(getMainCompanion(),
									Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
										?"你没有被强制喂下转化药剂！"
										:"因为你的嘴被堵上了，所以你不能喝下任何转化药水！"),
							null);
					
				} else if (index == 2) {
					return new Response("吞咽",
							UtilText.parse(getMainCompanion(),
									Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
										?"你没有被强制喂下转化药剂！"
										:"因为你的嘴被堵上了，所以你不能喝下任何转化药水！"),
							null);
					
				} else if (index == 6) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("命令吐出");
					}
					return new Response("要求吐出",
							UtilText.parse(getMainCompanion(), "告诉[npc.name]把"+(companionMultiplePotions?"药水吐出来":"药水吐出来")+"！"
									+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
											?" (然而，因为[npc.name]拥有"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+"的性癖，[npc.sheIsFull]并不愿意听你的。)"
											:"")),
							AFTER_COMBAT_TRANSFORMATION) {
						@Override
						public void effects(){
							transformationsApplied = true;
							if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SPIT_REFUSED", getDemon()));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "TF_COMPANION_SWALLOW", getDemon()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SPIT", getDemon()));
							}
						}
					};
					
				} else if (index == 7) {
					return new Response("要求吞咽",
							UtilText.parse(getMainCompanion(),
								"叫[npc.name]吞下药水。"
								+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
										?" (然而，由于讨厌被转化，[npc.sheIsFull]并不愿意听从你！)"
										:"")),
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							transformationsApplied = true;
							if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SWALLOW", getDemon()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ORDER_SWALLOW_REFUSED", getDemon()));
							}
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATION = new DialogueNode("被转化", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(isCompanionDialogue()) {
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					if(getDemon().isAttractedTo(getMainCompanion())) {
						if(getDemon().isWillingToRape()) {
							return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "RAPE_BOTH", getDemon());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "OFFER_SEX_BOTH", getDemon());
						}
						
					} else {
						if(getDemon().isWillingToRape()) {
							return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "RAPE_PLAYER", getDemon());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "OFFER_SEX", getDemon());
						}
					}
					
				} else if(getDemon().isAttractedTo(getMainCompanion()) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) {
					if(getDemon().isWillingToRape()) {
						return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "RAPE_COMPANION", getDemon());
					} else {
						return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "OFFER_SEX_COMPANION", getDemon());
					}
				}
				
			} else {
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					if(getDemon().isWillingToRape()) {
						return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "RAPE_PLAYER", getDemon());
					} else {
						return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "OFFER_SEX", getDemon());
					}
				}
			}

			if(transformationsApplied) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "NO_SEX_POST_TRANSFORM", getDemon());
			}
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "NO_SEX", getDemon());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()) {
				boolean companionHappyToHaveSex = getMainCompanion().isAttractedTo(getDemon()) || getMainCompanion().isAttractedTo(Main.game.getPlayer());
				boolean companionSex = getDemon().isAttractedTo(getMainCompanion()) && (companionHappyToHaveSex || getDemon().isWillingToRape());
				
				
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					if(getDemon().isAttractedTo(getMainCompanion())) { // Threesome sex:
						if (index == 1) {
							return new ResponseSex("做爱",
									UtilText.parse(getDemon(),
											getDemon().isWillingToRape()
												?"[npc.Name]强行压住了你"+(companionSex?UtilText.parse(getMainCompanion(), "和[npc.name]"):"")+"……"
												:"告诉[npc.name]"+(companionSex?UtilText.parse(getMainCompanion(), "和[npc.name]"):"")+"你愿意和[npc.herHim]做爱。"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
											Util.newArrayListOfValues(
													Main.game.getPlayer(),
													companionSex
														?getMainCompanion()
														:null),
											null,
											Util.newArrayListOfValues(
												companionSex
													?null
													:getMainCompanion())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_THREESOME", getDemon()));
							
						} else if (index == 2) {
							return new ResponseSex("做爱(渴求)",
									UtilText.parse(getDemon(),
											getDemon().isWillingToRape()
												?"[npc.Name]强行压住了你"+(companionSex?UtilText.parse(getMainCompanion(), "和[npc.name]"):"")+"……"
												:"告诉[npc.name]你"+(companionSex?UtilText.parse(getMainCompanion(), "和[npc.name]"):"")+"十分乐意同[npc.herHim]做爱。"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
											Util.newArrayListOfValues(
													Main.game.getPlayer(),
													companionSex
														?getMainCompanion()
														:null),
											null,
											Util.newArrayListOfValues(
												companionSex
													?null
													:getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_EAGER),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_THREESOME", getDemon()));
							
						} else if (index == 3 && Main.game.isNonConEnabled()) {
							return new ResponseSex("抵抗做爱",
									UtilText.parse(getDemon(), "[npc.Name]强行压住了你"+(companionSex?UtilText.parse(getMainCompanion(), "和[npc.name]"):"")+"……"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
											Util.newArrayListOfValues(
													Main.game.getPlayer(),
													companionSex
														?getMainCompanion()
														:null),
											null,
											Util.newArrayListOfValues(
												companionSex
													?null
													:getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_THREESOME_RESIST", getDemon()));
							
						} else if (index == 4 && !getDemon().isWillingToRape()) {
							return new Response("拒绝",
									UtilText.parse(getDemon(), "拒绝和[npc.name]做爱，你继续你的行程。"),
									Main.game.getDefaultDialogue(false)) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEFEATED_REFUSE_THREESOME", getDemon()));
								}
							};
						}
						return null;
						
					} else { // Solo sex with player:
						if (index == 1) {
							return new ResponseSex("做爱",
									UtilText.parse(getDemon(),
											getDemon().isWillingToRape()
												?"[npc.Name]强行压住了你……"
												:"告诉[npc.name]你愿意同[npc.herHim]做爱。"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX", getDemon()));
							
						} else if (index == 2) {
							return new ResponseSex("做爱(渴求)",
									UtilText.parse(getDemon(),
											getDemon().isWillingToRape()
												?"[npc.Name]强行压住了你……"
												:"告诉[npc.name]非常乐意和[npc.herHim]做爱。"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_EAGER),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX", getDemon()));
							
						} else if (index == 3 && Main.game.isNonConEnabled()) {
							return new ResponseSex("抵抗做爱",
									UtilText.parse(getDemon(), "[npc.Name]强行压住了你……"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_RESIST", getDemon()));
							
						} else if (index == 4 && !getDemon().isWillingToRape()) {
							return new Response("拒绝",
									UtilText.parse(getDemon(), "拒绝和[npc.name]做爱，你继续你的行程。"),
									Main.game.getDefaultDialogue(false)) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEFEATED_REFUSE_SEX", getDemon()));
								}
							};
						}
						return null;
					}
					
				} else if(getDemon().isAttractedTo(getMainCompanion())
						&& Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) { // Solo sex with companion:
					if(getDemon().isWillingToRape()) {
						if (index == 1) {
							return new ResponseSex("旁观强奸",
									UtilText.parse(getDemon(), getMainCompanion(),
											"你无能为力，只得眼睁睁看着[npc.name]强迫[npc2.name]。"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
											Util.newArrayListOfValues(getMainCompanion()),
											null,
											Util.newArrayListOfValues(Main.game.getPlayer())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_SOLO_COMPANION_RAPE", getDemon()));
						}
						
					} else if(companionHappyToHaveSex) {
						if (index == 1) {
							return new ResponseSex("观看性爱",
									UtilText.parse(getDemon(), getMainCompanion(),
											"你无能为力，值得眼睁睁地看着[npc2.name]欣然同意[npc.name]来干[npc2.herHim]。"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getDemon()),
											Util.newArrayListOfValues(getMainCompanion()),
											null,
											Util.newArrayListOfValues(Main.game.getPlayer())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_SOLO_COMPANION", getDemon()));
						}
						
					} else if (index == 1) {
						return new Response(
								UtilText.parse(getMainCompanion(), "[npc.Name]拒绝"),
								UtilText.parse(getDemon(), getMainCompanion(), "看起来[npc2.name]打算拒绝和[npc.name]做爱。"),
								Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEFEATED_REFUSE_SEX_SOLO_COMPANION", getDemon()));
							}
						};
					}
				}
				
			} else {
				if(getDemon().isAttractedTo(Main.game.getPlayer())) { // Solo sex with player:
					if (index == 1) {
						return new ResponseSex("做爱",
								UtilText.parse(getDemon(),
										getDemon().isWillingToRape()
											?"[npc.Name]强行压住了你……"
											:"告诉[npc.name]你愿意同[npc.herHim]做爱。"),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion())),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX", getDemon()));
						
					} else if (index == 2) {
						return new ResponseSex("做爱(渴求)",
								UtilText.parse(getDemon(),
										getDemon().isWillingToRape()
											?"[npc.Name]强行压住了你……"
											:"告诉[npc.name]非常乐意和[npc.herHim]做爱。"),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion()),
										ResponseTag.START_PACE_PLAYER_SUB_EAGER),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX", getDemon()));
						
					} else if (index == 3 && Main.game.isNonConEnabled()) {
						return new ResponseSex("抵抗做爱",
								UtilText.parse(getDemon(), "[npc.Name]强行压住了你……"),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion()),
										ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "START_DEFEATED_SEX_RESIST", getDemon()));
						
					} else if (index == 4 && !getDemon().isWillingToRape()) {
						return new Response("拒绝",
								UtilText.parse(getDemon(), "拒绝和[npc.name]做爱，你继续你的行程。"),
								Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEFEATED_REFUSE_SEX", getDemon()));
							}
						};
					}
					return null;
				}
			}
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "DEFEATED_NO_SEX", getDemon()));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("返回", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getDescription(){
			return "你已经满足了，可以离开让[npc.name]休息一下。";
		}
		@Override
		public String getContent() {
			if((getDemon().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if(Main.sex.getNumberOfOrgasms(getDemon()) >= getDemon().getOrgasmsBeforeSatisfied()) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_VICTORY", getDemon());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_VICTORY_NO_ORGASM", getDemon());
				}
				
			} else {
				if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_VICTORY_RAPE_BETRAYED", getDemon());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_VICTORY_RAPE", getDemon());
				}
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getDemon());
						}
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("物品栏", "现在你可以随意拿取[npc.namePos]衣服和道具，谁也拦不了你……"){
					@Override
					public void effects() {
						Main.mainController.openInventory(getDemon(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10 && !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"移除角色",
						UtilText.parse(getDemon(), "赶[npc.name]走。"
								+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]"),
						AFTER_COMBAT_VICTORY){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getDemon());
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("瘫软", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getDescription(){
			return "你在[npc.namePos]的支配下精疲力竭，需要休息一会儿。";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_DEFEAT", getDemon());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", AFTER_SEX_VICTORY) {
					@Override
					public void effects() {
						if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getDemon());
						}
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
			}
			return null;
		}
	};
	
}
