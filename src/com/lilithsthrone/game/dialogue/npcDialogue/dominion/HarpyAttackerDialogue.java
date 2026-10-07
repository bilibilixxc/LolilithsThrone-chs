package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.ArrayList;
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

/**
 * @since 0.2.11
 * @version 0.3.7.8
 * @author Innoxia
 */
public class HarpyAttackerDialogue {
	
	private static boolean transformationsApplied = false;

	private static boolean isStorm() {
		return getHarpy().isVulnerableToArcaneStorm()
				&& Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
	}
	
	private static boolean isWantsToFight() {
		return getHarpy().isAffectionAggressionTrigger(Main.game.getPlayer());
	}

	private static boolean isCompanionDialogue() {
		return Main.game.getPlayer().hasCompanions();
	}
	
	private static NPC getHarpy() {
		return Main.game.getActiveNPC();
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static List<GameCharacter> getAllCharacters() {
		List<GameCharacter> allCharacters = new ArrayList<>();
		allCharacters.add(getHarpy());
		allCharacters.addAll(Main.game.getPlayer().getCompanions());
		Collections.sort(allCharacters, (c1, c2) -> c1.isElemental()?(c2.isElemental()?0:1):(c2.isElemental()?-1:0));
		return allCharacters;
	}
	
	private static String getFileLocation() {
		return isStorm()?"harpyAttackStorm":"harpyAttack";
	}
	
	private static void applyPregnancyReactions() {
		if(getHarpy().isVisiblyPregnant()){
			getHarpy().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(getHarpy(), true);
		}
		if(isCompanionDialogue() && getMainCompanion().isVisiblyPregnant()) {
			getMainCompanion().setCharacterReactedToPregnancy(getHarpy(), true);
		}
	}
	
	private static String getStatus() {
		return AffectionLevel.getAttitudeDescription(getHarpy(), Main.game.getPlayer(), true);
	}
	
	public static final DialogueNode HARPY_ATTACK = new DialogueNode("敌袭！", "一个家伙从阴影中跳了出来！", true) {
		@Override
		public void applyPreParsingEffects() {
			if(!isWantsToFight()) { // Catch for if the player has somehow raised this npc's affection without using the talk option. Should be impossible but someone managed it somehow (maybe with mods)
				getHarpy().setPlayerKnowsName(true);
			}
			getHarpy().generatePostCombatPotions();
			transformationsApplied = false;
			Main.game.getDialogueFlags().setFlag("innoxia_alleyway_transformations_applied", false);

			if(getHarpy().getPlayerSurrenderCount()>=4) { 
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
					if(Main.game.getDialogueFlags().getSavedLong("randomResponseIndex")==4 && (!getHarpy().isAttractedTo(Main.game.getPlayer()) || getHarpy().hasStatusEffect(StatusEffect.RECOVERING_AURA))) {
						Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 5);
					}
				}
			}
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(getHarpy().hasEncounteredBefore()) {
				if(isWantsToFight()) {
					if(getHarpy().getPlayerSurrenderCount()>=4) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_SUBMITTED", getAllCharacters()));
						
					} else if(getHarpy().getPlayerSurrenderCount()==3) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_DEMAND_SUBMIT", getAllCharacters()));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_REPEAT", getAllCharacters()));
					}
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_PEACEFUL", getHarpy()));

					UtilText.nodeContentSB.append(getStatus());
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(getHarpy().getPlayerSurrenderCount()>=3) { // Bitch content
				return DialogueManager.getDialogueFromId("innoxia_encounters_dominion_harpy_nest_start").getResponse(responseTab, index);
			}
			
			if(isWantsToFight()) {
				if (index == 1) {
					return new ResponseCombat("战斗", "挺身而出，与[npc.name]决一胜负！", getHarpy()) {
						@Override
						public void effects() {
							applyPregnancyReactions();
						}
					};
					
				} else if (index == 2) {
					if(isStorm()) {
						return new Response("献上财物("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand1(), "span")+")",
								"[npc.Name]受到奥术风暴的影响，满脑子只想着和你做爱！",
								null);
						
					} else if(Main.game.getPlayer().getMoney()<Main.game.getDialogueFlags().getMuggerDemand1()) {
						return new Response("献上财物("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand1(), "span")+")",
								"你没有足够的给[npc.name]。你只能选择战斗，或是向[npc.herHim]献上身体！",
								null);
					} else {
						return new Response("献上财物("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand1(), "span")+")",
								"给了[npc.Name]"+Util.intToString(Main.game.getDialogueFlags().getMuggerDemand1())+"火币，你最终得以脱身。", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getPlayer().incrementMoney(-Main.game.getDialogueFlags().getMuggerDemand1());
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_PAY_OFF", getAllCharacters()));
							}
						};
					}
					
				} else if (index == 3) {
					if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("献上身体",
								"向[npc.name]献出身体，以避免发生暴力对抗。"
									+"<br/>[style.italicsSex(重复性地向[npc.name]屈服将会导致[npc.herHim]要求你成为[npc.her]的荡妇……)]",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
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
								UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_OFFER_BODY", getAllCharacters())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								getHarpy().incrementPlayerSurrenderCount(1);
							}
						};
						
					} else {
						return new Response("献出身体", "你可以看出来[npc.name]对和你做爱完全不感兴趣。你只能选择给[npc.herHim]一些财物，或是做好战斗的准备！", null);
					}
					
				} else if (index == 4 && getHarpy().isApplyingPostCombatTransformations()) {
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
							getHarpy().incrementPlayerSurrenderCount(1);
						}
					};
					
				} else if (index == 6 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();
	
					if(!getHarpy().isAttractedTo(Main.game.getPlayer())) {
						return new Response(UtilText.parse(companion, "三人行"),
								UtilText.parse(getHarpy(), companion, "你看得出来，[npc.name]一点也不想和你做爱，所以也就不会想要和你三人行……"),
								null);
						
					} else if(!getHarpy().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "三人行"),
								UtilText.parse(getHarpy(), companion, "你看得出来，[npc.name]一点也不想和[npc2.name]做爱，所以也就不会想要和你三人行……"),
								null);
						
					} else if(!companion.isAttractedTo(getHarpy()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "三人行"),
								UtilText.parse(getHarpy(), companion, "你看得出来[npc2.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[npc2.herHim]这么做……"),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "三人行"),
								UtilText.parse(getHarpy(), companion, "让[npc.name]有和你，以及[npc2.name]一起做爱的机会，以此来避免一场暴力冲突。"),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer(), companion),
										null,
										null,
										ResponseTag.PREFER_DOGGY),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_OFFER_THREESOME", getHarpy(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
					}
					
				} else if (index == 7 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
	
					if(!getHarpy().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(getHarpy(), companion, "你看得出来[npc.name]完全没兴趣和[npc2.name]做爱……"),
								null);
						
					} else if(!companion.isAttractedTo(getHarpy()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(getHarpy(), companion, "你看得出来[npc2.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[npc2.herHim]这么做……"),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(getHarpy(), companion, "为了避免发生暴力冲突，你告诉[npc.name]，[npc.she]可以享用[npc2.namePos]的身体。"),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(companion),
										null,
										Util.newArrayListOfValues(Main.game.getPlayer())),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_OFFER_COMPANION", getHarpy(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								if(!companion.isAttractedTo(getHarpy()) && Main.game.isNonConEnabled()) {
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
					if(isStorm()) {
						return new Response("交谈",
								"[npc.Name]受到奥术风暴的影响，满脑子只想着和你做爱！",
								null);
					}
					return new Response("对话", "和[npc.name]聊一会儿，以便于多了解[npc.herHim]一点。", HARPY_PEACEFUL_TALK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), 10));
							
							if(getHarpy().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 2) {
					if(isStorm()) {
						return new Response("献上财物("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand1(), "span")+")",
								"[npc.Name]受到奥术风暴的影响，满脑子只想着和你做爱！",
								null);
					}
					if(Main.game.getPlayer().getMoney()<Main.game.getDialogueFlags().getMuggerDemand1()) {
						return new Response("献上财物("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand1(), "span")+")",
								"你没有足够的钱可以给予[npc.name]。", null);
					} else {
						return new Response("献上财物("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand1(), "span")+")",
								"给[npc.name]一些钱，让[npc.herHim]能够买一些食物和衣服。", HARPY_PEACEFUL_OFFER_MONEY) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getDialogueFlags().getMuggerDemand1()));
								Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), 10));

								if(getHarpy().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
								}
							}
						};
					}
					
				} else if (index == 3) {
					if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("做爱(支配)", "同[npc.name]做爱，并占据支配地位。",
								Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, Fetish.FETISH_DOMINANT.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getHarpy()),
										Main.game.getPlayer().getCompanions(),
										null),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_PEACEFUL_SEX_AS_DOM", getAllCharacters())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("做爱(支配)", "你看得出[npc.name]不想和你做爱……", null);
					}
					
				} else if (index == 4) {
					if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("做爱(顺从)", "向[npc.name]献上身体。",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Main.game.getPlayer().getCompanions()),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_PEACEFUL_SEX_AS_SUB", getAllCharacters())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("做爱(顺从)", "你看得出[npc.name]不想和你做爱……", null);
					}
					
				} if (index == 5) {
					if(isStorm()) {
						return new Response("提供房间",
								"[npc.Name]受到奥术风暴的影响，满脑子只想着和你做爱！",
								null);
						
					} else if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) || !getHarpy().isAffectionHighEnoughToInviteHome()) {
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
						return new Response("提供房间", "问一问[npc.name]想不想在莉莱雅的宅邸中要一个房间。", HARPY_PEACEFUL_OFFER_ROOM) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), 25));
							}
						};
					}
					
				} else if(index==6) {
					if(getHarpy().getPlayerSurrenderCount()<3 && getHarpy().isApplyingPostCombatTransformations()) {
						if(transformationsApplied) {
							return new Response("开始被转化",
									"[npc.Name]早就把[npc.she]所有的转化药水给你了！",
									null);
							
						} else {
							return new Response("开始被转化",
									"告诉[npc.name]你愿意喝下[npc.she]所拥有的任何转化药水……"
										+"<br/>[style.italicsTfGeneric(这将会导致[npc.name]让你喝下一剂转化药水！)]",
										HARPY_PEACEFUL_TRANSFORMED,
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_TRANSFORMATION_RECEIVING), Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel(), null, null, null) {
								@Override
								public Colour getHighlightColour() {
									return PresetColour.TRANSFORMATION_GENERIC;
								}
								@Override
								public void effects() {
									applyPregnancyReactions();
									Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "PEACEFUL_TRANSFORMATIONS", getAllCharacters()));
									Main.game.appendToTextStartStringBuilder(getHarpy().applyPostCombatTransformation());
									transformationsApplied = true;
								}
							};
						}
					}
					
				} else if (index==10) {
					return new Response("攻击", "背叛[npc.namePos]的信任并攻击[npc.herHim]！", HARPY_PEACEFUL_ATTACK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), -50));
							getHarpy().addFlag(NPCFlagValue.genericNPCBetrayedByPlayer);
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if (index == 11 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();
	
					if(!getHarpy().isAttractedTo(Main.game.getPlayer())) {
						return new Response(UtilText.parse(companion, "三人行"),
								UtilText.parse(getHarpy(), companion, "你看得出来，[npc.name]一点也不想和你做爱，所以也就不会想要和你三人行……"),
								null);
						
					} else if(!getHarpy().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "三人行"),
								UtilText.parse(getHarpy(), companion, "你看得出来，[npc.name]一点也不想和[npc2.name]做爱，所以也就不会想要和你三人行……"),
								null);
						
					} else if(!companion.isAttractedTo(getHarpy()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "三人行"),
								UtilText.parse(getHarpy(), companion, "你看得出来[npc2.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[npc2.herHim]这么做……"),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "三人行"),
								UtilText.parse(getHarpy(), companion, "提供给[npc.name]和你，以及[npc2.name]一起做爱的机会。"),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer(), companion),
										null,
										null,
										ResponseTag.PREFER_DOGGY),
								AFTER_SEX_PEACEFUL_THREESOME,
								UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_PEACEFUL_OFFER_THREESOME", getHarpy())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
					}
					
				} else if (index == 12 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
	
					if(!getHarpy().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(getHarpy(), companion, "你看得出来[npc.name]完全没兴趣和[npc2.name]做爱……"),
								null);
						
					} else if(!companion.isAttractedTo(getHarpy()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(getHarpy(), companion, "你看得出来[npc2.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[npc2.herHim]这么做……"),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(getHarpy(), companion, "告诉[npc.name][npc.she]可以随意使用[npc2.namePos]的身体。"),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(companion),
										null,
										Util.newArrayListOfValues(Main.game.getPlayer())),
								AFTER_SEX_PEACEFUL_OFFERED_COMPANION, UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_PEACEFUL_OFFER_COMPANION", getHarpy(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								if(!companion.isAttractedTo(getHarpy()) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
						};
					}
					
				} else if (index == 0) {
					return new Response("离开",
							"告诉[npc.Name]你急着去别的地方，然后继续赶路。",
							Main.game.getDefaultDialogue(false));
				}
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_PEACEFUL_TALK = new DialogueNode("对话", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_TALK", getAllCharacters()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(getHarpy().isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_CAN_INVITE_HOME", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "让[npc.name]走。", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode HARPY_PEACEFUL_OFFER_MONEY = new DialogueNode("献上财物", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_OFFER_MONEY", getAllCharacters()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(getHarpy().isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_CAN_INVITE_HOME", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "让[npc.name]离开去买吃的。", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode HARPY_PEACEFUL_OFFER_ROOM = new DialogueNode("提供房间", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_OFFER_ROOM", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("带回家", "带[npc.name]到[npc.her]的新房间。", HARPY_PEACEFUL_OFFER_ROOM_BACK_HOME) {
					@Override
					public void effects() {
						Cell c = OccupancyUtil.getFreeRoomForOccupant();
						getHarpy().setLocation(c.getType(), c.getLocation(), true);
						Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
						Main.game.getPlayer().addFriendlyOccupant(getHarpy());
						Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), 50));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HARPY_PEACEFUL_OFFER_ROOM_BACK_HOME = new DialogueNode("新房间", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_OFFER_ROOM_BACK_HOME", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "让[npc.name]安顿下来。", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};

	public static final DialogueNode HARPY_PEACEFUL_TRANSFORMED = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HARPY_ATTACK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HARPY_PEACEFUL_ATTACK = new DialogueNode("攻击", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_ATTACK", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗", "开始跟[npc.name]战斗！", getHarpy());
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL = new DialogueNode("继续", "从[npc.name]身边走开，准备继续你的旅程。", true) {
		
		@Override
		public String getContent() {
			if(getHarpy().isSatisfiedFromLastSex()) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_PEACEFUL", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_PEACEFUL_NO_ORGASM", getAllCharacters());
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
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_PEACEFUL_THREESOME", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL_OFFERED_COMPANION = new DialogueNode("继续", "从[npc.Name]身旁走开，继续你的旅途。", true) {
		
		@Override
		public String getContent() {
			if(getMainCompanion().isAttractedTo(getHarpy())) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_PEACEFUL_OFFERED_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_PEACEFUL_OFFERED_COMPANION_RELUCTANT", getAllCharacters());
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
			getHarpy().setPlayerSurrenderCount(0);
			getHarpy().clearPetName(Main.game.getPlayer());
		}
		@Override
		public String getDescription() {
			return "你打败了[npc.name]！";
		}
		@Override
		public String getContent() {
			if(getHarpy().isAttractedTo(Main.game.getPlayer()) && !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_ATTRACTION", getHarpy());
				
			} else {
				if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_BETRAYED", getHarpy());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_NO_ATTRACTION", getHarpy());
				}
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			boolean noSex = getHarpy().isPostCombatNoSex();
			boolean wantsSex = getHarpy().isPostCombatWantsSex();
			boolean rapePlay = getHarpy().isPostCombatRapePlay();
			
			if (index == 1) {
				return new Response("继续",
						"继续前行……"
							+ (getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)
								?UtilText.parse(getHarpy(), "<br/>[style.italicsBad([npc.Name]将会从游戏中永久移除。)]")
								:""),
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						return super.getHighlightColour();
					}
					@Override
					public void effects() {
						if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getHarpy());
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
									Util.newArrayListOfValues(getHarpy()),
									Main.game.getPlayer().getCompanions(),
									null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", rapePlay?"AFTER_COMBAT_VICTORY_RAPE":"AFTER_COMBAT_VICTORY_SEX", getAllCharacters()));
				} else {
					return new ResponseSex(
							"强奸[npc.herHim]",
							"[npc.she]竟敢对你发起攻击，就该受罚……",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
									Main.game.getPlayer().getCompanions(),
									null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_RAPE", getAllCharacters()));
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
									Util.newArrayListOfValues(getHarpy()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", rapePlay?"AFTER_COMBAT_VICTORY_RAPE_GENTLE":"AFTER_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()));
					
				} else {
					return new ResponseSex("强奸[npc.herHim](温柔)",
							"[npc.she]竟敢对你发起攻击，就该受罚……",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getAllCharacters()));
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
									Util.newArrayListOfValues(getHarpy()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", rapePlay?"AFTER_COMBAT_VICTORY_RAPE_ROUGH":"AFTER_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()));
					
				} else {
					return new ResponseSex("强奸[npc.herHim](粗暴)",
							"[npc.she]竟敢对你发起攻击，就该受罚……",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getAllCharacters()));
				}
				
			} else if (index == 5) {
				if(!getHarpy().isAttractedTo(Main.game.getPlayer()) || getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
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
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									Util.newArrayListOfValues(getMainCompanion())),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("物品栏", "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具伸出手……"){
					@Override
					public void effects() {
						Main.mainController.openInventory(getHarpy(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 7) {
				if(isStorm()) {
					return new Response("交谈",
							"[npc.Name]受到奥术风暴的影响，满脑子只想着和你做爱！",
							null);
				}
				if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response("对话", "在背叛了[npc.namePos]的信任之后，[npc.she]不会想再跟你说话。", null);
					
				} else {
					return new Response("对话", "和[npc.name]交谈，问问[npc.herHim]为什么会袭击你。", AFTER_COMBAT_VICTORY_TALK){
						@Override
						public void effects() {
							getHarpy().setPlayerKnowsName(true);
							Main.game.getTextEndStringBuilder().append(getHarpy().setAffection(Main.game.getPlayer(), 10));
						}
					};
				}
				
			} else if (index == 8 && getHarpy().isAbleToSelfTransform()) {
				return new Response("转化[npc.herHim]",
						"仔细观察[npc.name]会将自己转化成什么样……",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getHarpy());
					}
				};
				
			} else if (index == 9 && getHarpy().isAbleToSelfTransform()) {
				return new Response("快速转化",
						"[npc.she]能够转化自己，你脑海中闪过一些点子……"
								+ "(你完成[npc.herHim]的转化后将返回选项界面。)",
						QuickTransformations.initQuickTransformations("misc/quickTransformations", getHarpy(), AFTER_COMBAT_VICTORY));
			
			} else if (index == 10 && !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"移除角色",
						UtilText.parse(getHarpy(), "赶[npc.name]走。"
								+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]"),
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_BANISH_NPC", getAllCharacters()));
						Main.game.banishNPC(getHarpy());
					}
				};
				
			} else if (index == 11 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!Main.game.isNonConEnabled() && (!getHarpy().isAttractedTo(Main.game.getPlayer()) || !getHarpy().isAttractedTo(companion))) {
					return new Response("三人行", UtilText.parse(companion, getHarpy(), "[npc2.Name]没兴趣和你或[npc.name]做爱！"), null);
					
				} else if(!companion.isAttractedTo(getHarpy())) {
					return new Response(UtilText.parse(companion, "三人行"), UtilText.parse(companion, getHarpy(), "[npc.Name]并没有被[npc2.name]所吸引，所以并不愿意和[npc2.herHim]一起做爱！"), null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "三人行"),
							UtilText.parse(getHarpy(), companion, "和[npc.name]来一场支配型性爱，同时让[npc2.name]也来爽一爽。"),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									Util.newArrayListOfValues(getHarpy()),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_THREESOME", getHarpy(), companion));
				}
				
			} else if (index == 12 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !getHarpy().isAttractedTo(companion)) {
					return new Response(UtilText.parse(companion, "给予[npc.name]"), UtilText.parse(companion, getHarpy(), "[npc2.Name]并没有被[npc.name]所吸引，所以并不愿意和[npc.herHim]一起做爱！"), null);
					
				} else if(!companion.isAttractedTo(getHarpy())) {
					return new Response(UtilText.parse(companion, "给予[npc.name]"), UtilText.parse(companion, getHarpy(), "[npc.Name]并没有被[npc2.name]所吸引，所以并不愿意和[npc2.herHim]一起做爱！"), null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "交给[npc.name]"),
							UtilText.parse(companion, getHarpy(), "告诉[npc.name]，[npc.she]可以跟[npc2.name]找找乐子，你在一旁看着。"),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(companion),
									Util.newArrayListOfValues(getHarpy()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getHarpy(), companion));
				}
				
			} else if (index == 13 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !companion.isAttractedTo(getHarpy())) {
					return new Response(UtilText.parse(companion, "献上[npc.name]"), UtilText.parse(companion, getHarpy(), "[npc.Name]没兴趣和[npc2.name]做爱！"), null);
					
				} else if(!getHarpy().isAttractedTo(companion)) {
					return new Response(UtilText.parse(companion, "献上[npc.name]"), UtilText.parse(companion, getHarpy(), "[npc2.Name]没兴趣和[npc.name]做爱！"), null);
					
				} else if(!companion.isAttractedTo(getHarpy()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "献上[npc.name]"),
							UtilText.parse(getHarpy(), companion, "你看得出来[npc2.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[npc2.herHim]这么做……"),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
							UtilText.parse(getHarpy(), companion, "告诉[npc.name][npc.she]可以随意使用[npc2.name]。"),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getHarpy(), companion)) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getHarpy()) && Main.game.isNonConEnabled()) {
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
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_TALK", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续",
						"让[npc.name]走。",
						Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};

	private static String applyTransformation(GameCharacter target,
			TransformativePotion potion,
			boolean forcedTF,
			FetishPotion fetishPotion,
			boolean forcedFetish) {
		
		StringBuilder sb = new StringBuilder();
		
		if(potion!=null && forcedTF) {
			sb.append(UtilText.parse(getHarpy(), target,
					"<p>"
						+ "[npc.Name]向后退去，看到[npc2.name]听话地喝下了那奇怪的液体，嘴角露出了一抹笑容。"
						+ "[npc.speech(真是好孩子！我要把你变成我完美的"+getHarpy().getPreferredBodyDescription("b")+"啊！)]"
					+ "</p>"));
			sb.append(getHarpy().applyPotion(potion, target));
		}
		
		if(fetishPotion!=null && forcedFetish) {
			sb.append(UtilText.parse(getHarpy(),
					"<p>"
						+ "[npc.name]的[npc.eyes]投射出恶魔般的喜悦神情，[npc.her]兴奋地喊叫，"
						+ "[npc.speech(这就对了，全都咽下去！这些变化没坏处的！)]"
					+ "</p>"));
			sb.append(getHarpy().applyPotion(fetishPotion, target));
		}
		return sb.toString();
	}

	public static final DialogueNode SURRENDER = new DialogueNode("", "", true) {
		public void applyPreParsingEffects() {
			AFTER_COMBAT_DEFEAT.applyPreParsingEffects();
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "SURRENDER", getHarpy());		
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
				potion = getHarpy().generateTransformativePotion(Main.game.getPlayer());
				fetishPotion = getHarpy().generateFetishPotion(Main.game.getPlayer(), true);
			} else {
				potion = null;
				fetishPotion = null;
			}
			if(isCompanionDialogue()) {
				if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					companionPotion = getHarpy().generateTransformativePotion(getMainCompanion());
					companionFetishPotion = getHarpy().generateFetishPotion(getMainCompanion(), true);
				} else {
					companionPotion = null;
					companionFetishPotion = null;
				}
			}
		}
		
		@Override
		public String getDescription() {
			return "你被[npc.name]打败了！";
		}

		@Override
		public String getContent() {
			if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_BETRAYED", getAllCharacters());
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_GENERIC_START", getAllCharacters()));
			
			boolean forcedTF = getHarpy().isUsingForcedTransform(Main.game.getPlayer());
			boolean forcedFetish = getHarpy().isUsingForcedFetish(Main.game.getPlayer());
			boolean companionForcedTF = isCompanionDialogue() && getHarpy().isUsingForcedTransform(getMainCompanion());
			boolean companionForcedFetish = isCompanionDialogue() && getHarpy().isUsingForcedFetish(getMainCompanion());
			if((forcedTF && potion!=null)
					|| (forcedFetish && fetishPotion!=null)
					|| (companionForcedTF && companionPotion!=null)
					|| (companionForcedFetish && companionFetishPotion!=null)) {
				if(((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null))
						&& ((companionForcedTF || companionPotion!=null) && (companionForcedFetish || companionFetishPotion!=null))) { // Both TF:
					
					if(fetishPotion!=null && forcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_TF_AND_FETISH", getAllCharacters()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_FETISH", getAllCharacters()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_TF", getAllCharacters()));
					}
	
					if(companionFetishPotion!=null && companionForcedFetish) {
						if(companionPotion!=null && companionForcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_COMPANION_TF_AND_FETISH", getAllCharacters()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_COMPANION_FETISH", getAllCharacters()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_COMPANION_TF", getAllCharacters()));
					}
					
					return sb.toString();
					
				} else if((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null)) { // Player TF:
					if(fetishPotion!=null && forcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_TF_AND_FETISH", getAllCharacters()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_FETISH", getAllCharacters()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_TF", getAllCharacters()));
					}
					return sb.toString();
					
				} else if(isCompanionDialogue()) { // Companion TF:
					if(companionFetishPotion!=null && companionForcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_TF_AND_FETISH", getAllCharacters()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_FETISH", getAllCharacters()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_TF", getAllCharacters()));
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
			if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if (index == 1) {
					return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.banishNPC(getHarpy());
						}
					};
				}
				return null;
			}

			// Response variables:
			boolean forcedTF = getHarpy().isUsingForcedTransform(Main.game.getPlayer());
			boolean forcedFetish = getHarpy().isUsingForcedFetish(Main.game.getPlayer());
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
			boolean companionForcedTF = isCompanionDialogue() && getHarpy().isUsingForcedTransform(getMainCompanion());
			boolean companionForcedFetish = isCompanionDialogue() && getHarpy().isUsingForcedFetish(getMainCompanion());
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
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SPIT", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_COMPANION_SWALLOW", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
									
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_BOTH_SPIT", getAllCharacters()));
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
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SWALLOW", getAllCharacters()));
							Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
							
							if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_COMPANION_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_COMPANION_SPIT", getAllCharacters()));
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
												?" (然而，因为[npc.name]拥有"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+"的性癖，[npc.sheIsFull]并不愿意听从你。)"
												:"")),
								AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects(){
								transformationsApplied = true;
								if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SPIT", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SPIT_REFUSED", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_COMPANION_SWALLOW", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
									
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SPIT", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SPIT", getAllCharacters()));
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
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
								
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SWALLOW_REFUSED", getAllCharacters()));
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
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SPIT", getAllCharacters()));
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
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SWALLOW", getAllCharacters()));
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
											?" (然而，因为[npc.name]拥有"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+"的性癖，[npc.sheIsFull]并不愿意听从你。)"
											:"")),
							AFTER_COMBAT_TRANSFORMATION) {
						@Override
						public void effects(){
							transformationsApplied = true;
							if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SPIT_REFUSED", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_COMPANION_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SPIT", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 7) {
					return new Response("要求吞咽",
							UtilText.parse(getMainCompanion(),
								"叫[npc.name]吞下药水。"
								+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
										?" (然而，[npc.she]由于讨厌被转化，所以并不愿意服从你！)"
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
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SWALLOW_REFUSED", getAllCharacters()));
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
		public String getContent() {
			if(isCompanionDialogue()) {
				if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
					if(getHarpy().isAttractedTo(getMainCompanion())) {
						if(getHarpy().isWillingToRape()) {
							return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "RAPE_BOTH", getAllCharacters());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "OFFER_SEX_BOTH", getAllCharacters());
						}
						
					} else {
						if(getHarpy().isWillingToRape()) {
							return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "RAPE_PLAYER_SOLO", getAllCharacters());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "OFFER_SEX_SOLO", getAllCharacters());
						}
					}
					
				} else if(getHarpy().isAttractedTo(getMainCompanion()) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) {
					if(getHarpy().isWillingToRape()) {
						return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "RAPE_COMPANION", getAllCharacters());
					} else {
						return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "OFFER_SEX_COMPANION", getAllCharacters());
					}
				}
				
			} else {
				if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
					if(getHarpy().isWillingToRape()) {
						return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "RAPE_PLAYER", getAllCharacters());
					} else {
						return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "OFFER_SEX", getAllCharacters());
					}
				}
			}

			if(transformationsApplied) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "NO_SEX_POST_TRANSFORM", getAllCharacters());
			}
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "NO_SEX", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()) {
				boolean companionHappyToHaveSex = getMainCompanion().isAttractedTo(getHarpy()) || getMainCompanion().isAttractedTo(Main.game.getPlayer());
				boolean companionSex = getHarpy().isAttractedTo(getMainCompanion()) && (companionHappyToHaveSex || getHarpy().isWillingToRape());
				
				if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
					if(getHarpy().isAttractedTo(getMainCompanion())) { // Threesome sex:
						if (index == 1) {
							return new ResponseSex("做爱",
									UtilText.parse(getHarpy(),
											getHarpy().isWillingToRape()
												?"[npc.Name]强行压住了你"+(companionSex?UtilText.parse(getMainCompanion(), "和[npc.name]"):"")+"……"
												:"告诉[npc.name]"+(companionSex?UtilText.parse(getMainCompanion(), "和[npc.name]"):"")+"你愿意和[npc.herHim]做爱。"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
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
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_THREESOME", getAllCharacters()));
							
						} else if (index == 2) {
							return new ResponseSex("做爱(渴求)",
									UtilText.parse(getHarpy(),
											getHarpy().isWillingToRape()
												?"[npc.Name]强行压住了你"+(companionSex?UtilText.parse(getMainCompanion(), "和[npc.name]"):"")+"……"
												:"告诉[npc.name]你"+(companionSex?UtilText.parse(getMainCompanion(), "和[npc.name]"):"")+"十分乐意同[npc.herHim]做爱。"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
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
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_THREESOME", getAllCharacters()));
							
						} else if (index == 3 && Main.game.isNonConEnabled()) {
							return new ResponseSex("抵抗做爱",
									UtilText.parse(getHarpy(), "[npc.Name]强行压住了你"+(companionSex?UtilText.parse(getMainCompanion(), "和[npc.name]"):"")+"……"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
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
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_THREESOME_RESIST", getAllCharacters()));
							
						} else if (index == 4 && !getHarpy().isWillingToRape()) {
							return new Response("拒绝",
									UtilText.parse(getHarpy(), "你拒绝和[npc.name]做爱，继续你的行程。"),
									Main.game.getDefaultDialogue(false)) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "DEFEATED_REFUSE_THREESOME", getAllCharacters()));
								}
							};
						}
						return null;
						
					} else { // Solo sex with player:
						if (index == 1) {
							return new ResponseSex("做爱",
									UtilText.parse(getHarpy(),
											getHarpy().isWillingToRape()
												?"[npc.Name]强行压住了你……"
												:"告诉[npc.name]你愿意同[npc.herHim]做爱。"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_SOLO", getAllCharacters()));
							
						} else if (index == 2) {
							return new ResponseSex("做爱(渴求)",
									UtilText.parse(getHarpy(),
											getHarpy().isWillingToRape()
												?"[npc.Name]强行压住了你……"
												:"告诉[npc.name]非常乐意和[npc.herHim]做爱。"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_EAGER),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_SOLO", getAllCharacters()));
							
						} else if (index == 3 && Main.game.isNonConEnabled()) {
							return new ResponseSex("抵抗做爱",
									UtilText.parse(getHarpy(), "[npc.Name]强行压住了你……"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_SOLO_RESIST", getAllCharacters()));
							
						} else if (index == 4 && !getHarpy().isWillingToRape()) {
							return new Response("拒绝",
									UtilText.parse(getHarpy(), "你拒绝和[npc.name]做爱，继续你的行程。"),
									Main.game.getDefaultDialogue(false)) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "DEFEATED_REFUSE_SEX_SOLO", getAllCharacters()));
								}
							};
						}
						return null;
					}
					
				} else if(getHarpy().isAttractedTo(getMainCompanion())
						&& Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) { // Solo sex with companion:
					if(getHarpy().isWillingToRape()) {
						if (index == 1) {
							return new ResponseSex("旁观强奸",
									UtilText.parse(getHarpy(), getMainCompanion(),
											"你无能为力，只得眼睁睁看着[npc.name]强迫[npc2.name]。"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
											Util.newArrayListOfValues(getMainCompanion()),
											null,
											Util.newArrayListOfValues(Main.game.getPlayer())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_SOLO_COMPANION_RAPE", getAllCharacters()));
						}
						
					} else if(companionHappyToHaveSex) {
						if (index == 1) {
							return new ResponseSex("观看性爱",
									UtilText.parse(getHarpy(), getMainCompanion(),
											"你无能为力，值得眼睁睁地看着[npc2.name]欣然同意[npc.name]来干[npc2.herHim]。"),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
											Util.newArrayListOfValues(getMainCompanion()),
											null,
											Util.newArrayListOfValues(Main.game.getPlayer())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_SOLO_COMPANION", getAllCharacters()));
						}
						
					} else if (index == 1) {
						return new Response(
								UtilText.parse(getMainCompanion(), "[npc.Name]拒绝"),
								UtilText.parse(getHarpy(), getMainCompanion(), "看起来[npc2.name]打算拒绝和[npc.name]做爱。"),
								Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "DEFEATED_REFUSE_SEX_SOLO_COMPANION", getAllCharacters()));
							}
						};
					}
				}
				
			} else {
				if(getHarpy().isAttractedTo(Main.game.getPlayer())) { // Solo sex with player:
					if (index == 1) {
						return new ResponseSex("做爱",
								UtilText.parse(getHarpy(),
										getHarpy().isWillingToRape()
											?"[npc.Name]强行压住了你……"
											:"告诉[npc.name]你愿意同[npc.herHim]做爱。"),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion())),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX", getAllCharacters()));
						
					} else if (index == 2) {
						return new ResponseSex("做爱(渴求)",
								UtilText.parse(getHarpy(),
										getHarpy().isWillingToRape()
											?"[npc.Name]强行压住了你……"
											:"告诉[npc.name]非常乐意和[npc.herHim]做爱。"),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion()),
										ResponseTag.START_PACE_PLAYER_SUB_EAGER),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX", getAllCharacters()));
						
					} else if (index == 3 && Main.game.isNonConEnabled()) {
						return new ResponseSex("抵抗做爱",
								UtilText.parse(getHarpy(), "[npc.Name]强行压住了你……"),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion()),
										ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_RESIST", getAllCharacters()));
						
					} else if (index == 4 && !getHarpy().isWillingToRape()) {
						return new Response("拒绝",
								UtilText.parse(getHarpy(), "你拒绝和[npc.name]做爱，继续你的行程。"),
								Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "DEFEATED_REFUSE_SEX", getAllCharacters()));
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
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "DEFEATED_NO_SEX", getAllCharacters()));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("返回", "", true) {
		
		@Override
		public String getDescription(){
			return "你已经爽过了，离开这里让[npc.name]恢复一下吧。";
		}

		@Override
		public String getContent() {
			if((getHarpy().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if(Main.sex.getNumberOfOrgasms(getHarpy()) >= getHarpy().getOrgasmsBeforeSatisfied()) {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_VICTORY", getAllCharacters());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_VICTORY_NO_ORGASM", getAllCharacters());
				}
				
			} else {
				if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_VICTORY_RAPE_BETRAYED", getAllCharacters());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_VICTORY_RAPE", getAllCharacters());
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getHarpy());
						}
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("物品栏", "现在你可以随意拿取[npc.namePos]衣服和道具，谁也阻拦不了你……"){
					@Override
					public void effects() {
						Main.mainController.openInventory(getHarpy(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10 && !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"移除角色",
						UtilText.parse(getHarpy(), "赶[npc.name]走。"
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
						Main.game.banishNPC(getHarpy());
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
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_DEFEAT", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", AFTER_SEX_VICTORY) {
					@Override
					public void effects() {
						if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getHarpy());
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
