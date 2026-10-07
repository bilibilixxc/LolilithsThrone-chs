package com.lilithsthrone.game.dialogue.npcDialogue.offspring;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.8?
 * @version 0.3.1
 * @author Innoxia
 */
public class GenericOffspringDialogue {
	
	private static NPCOffspring offspring() {
		return (NPCOffspring) Main.game.getActiveNPC();
	}
	
	private static String getOffspringLabel() {
		if(offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
			return UtilText.parse(offspring(), "与[npc.Name]交谈");
		} else {
			return "一张熟悉的面孔";
		}
	}
	
	private static String getTextFilePath() {
		return offspring().getWorldLocation().getOffspringTextFilePath(offspring());
	}
	

	private static String getStatus() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(offspring().getPlayerRelationStatusDescription());
		
		// Time limitation:
		sb.append("<p style='text-align:center;'><i>");
		int tokens = Main.game.getDialogueFlags().offspringDialogueTokens;
		if(tokens>=1) {
			sb.append("[npc.Name]的时间只够再谈[style.italicsMinorBad("+Util.intToString(tokens)+"件事了)]。");
		} else {
			sb.append("[npc.Name][style.italicsBad(没时间了)]，得赶紧去工作。");
		}
		sb.append("</i></p>");
		
		return UtilText.parse(offspring(), sb.toString());
	}
	
	public static final DialogueNode OFFSPRING_ENCOUNTER = new DialogueNode("", "你遇见了很眼熟的人……", true) {
		@Override
		public void applyPreParsingEffects(){
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).isEmpty()) {
				Main.game.initOffspringEncounter(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocationPlaceType());
			}
			List<GameCharacter> offspringList = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
			offspringList.removeIf(c->!c.isRelatedTo(Main.game.getPlayer()));
			Main.game.setActiveNPC((NPC) offspringList.get(0));
			
			if(Main.game.getPlayer().getWorldLocation()==WorldType.BAT_CAVERNS) { // If offspring is in the bat caverns, they are a mushroom hunter
				if(offspring().getItemCount(ItemType.MUSHROOM)<5) {
					offspring().addItem(Main.game.getItemGen().generateItem(ItemType.MUSHROOM), 5+Util.random.nextInt(10), false, false);
					offspring().setOccupation(Occupation.NPC_MUSHROOM_FORAGER);
				}
			}
		}
		
		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			
			if(offspring().getAffection(Main.game.getPlayer()) < AffectionLevel.NEGATIVE_TWO_DISLIKE.getMaximumValue()) {
				if (index == 1) {
					return new Response("道歉", UtilText.parse(offspring(), "对[npc.name]道歉。"), OFFSPRING_ENCOUNTER_APOLOGY) {
						@Override
						public void effects() {
							if(!offspring().hasFlag(NPCFlagValue.flagOffspringFightApologyNeeded) && !offspring().hasFlag(NPCFlagValue.flagOffspringRapeApologyNeeded)) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 20));
							} else {
								if(offspring().hasFlag(NPCFlagValue.flagOffspringFightApologyNeeded)) {
									offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
									Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
								}
								if(offspring().hasFlag(NPCFlagValue.flagOffspringRapeApologyNeeded)) {
									offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, false);
									Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
								}
							}
							setOffspringFlags();
						}
					};
					
				} else if (index == 8) {
					return new Response("做爱", UtilText.parse(offspring(), "[npc.she]现在很生气，不可能会跟你做爱。"), null) {
						@Override
						public void effects() {
							setOffspringFlags();
						}
					};
					
				} else if (index == 10) {
					return new Response("袭击", UtilText.parse(offspring(), "[npc.name]怎么敢这么跟你说话！得让[npc.herHim]认清自己的地位了！"), OFFSPRING_ENCOUNTER_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
							offspring().setFlag(NPCFlagValue.fightOffspringInApartment, false);
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -100));
							setOffspringFlags();
						}
					};
					
				} else if (index == 0) {
					return new Response("离开", UtilText.parse(offspring(), "告诉[npc.name]你下次再来。"), OFFSPRING_ENCOUNTER) {
							@Override
							public DialogueNode getNextDialogue() {
								setOffspringFlags();
								return Main.game.getDefaultDialogue(false);
							}
							@Override
							public void effects() {
								offspring().setProtectedFromArcaneStorm(false);
							}
						};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("打招呼", UtilText.parse(offspring(), "向[npc.name]问好。"), OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_GREETING", offspring()));
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 2) {
					return new Response("拥抱", UtilText.parse(offspring(), "拥抱[npc.name]。"), OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_HUG", offspring()));
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 5));
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 3) {
					return new Response("轻吻", UtilText.parse(offspring(), "抱抱[npc.name]，并且给[npc.her]一个吻。"), OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_KISS", offspring()));
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 4 && Main.game.isIncestEnabled()) {
					return new Response("热吻", UtilText.parse(offspring(), "跟[npc.name]热吻起来，并且开始抚摸[npc.herHim]。"), OFFSPRING_ENCOUNTER_TALKING,
							Util.newArrayListOfValues(Fetish.FETISH_INCEST),
							CorruptionLevel.FOUR_LUSTFUL,
							null,
							null,
							null) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_PASSIONATE_KISS", offspring()));
							if(offspring().isAttractedTo(Main.game.getPlayer())) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 25));
								
							} else if(
									((Main.game.getPlayer().isFeminine() && offspring().getSexualOrientation()==SexualOrientation.GYNEPHILIC)
									|| (!Main.game.getPlayer().isFeminine() && offspring().getSexualOrientation()==SexualOrientation.ANDROPHILIC)
									|| (offspring().getSexualOrientation()==SexualOrientation.AMBIPHILIC))
									|| offspring().hasFetish(Fetish.FETISH_INCEST)) {
								//Incest fetish and not attracted to player, or attracted to player and no incest fetish:
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -5));
								
							} else {
								//Not attracted to player, and no incest fetish:
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -20));
							}
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} if (index == 5) {
					return new Response("斥责[npc.herHim]",
							UtilText.parse(offspring(), "问[npc.name]知不知道[npc.she]脑子里想的什么东西！"
									+(offspring().getHistory()==Occupation.NPC_PROSTITUTE
											?"(这将对[npc.herHim]身为妓女表达不满。)"
											:"(这将对[npc.herHim]身为抢劫犯表达不满。)")),
							OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_SCOLDING", offspring()));
							// If masochist fetish, +5, if not, -5:
							if(offspring().hasFetish(Fetish.FETISH_MASOCHIST)) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 5)); 
							} else {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -5));
							}
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 10) {
					return new Response("袭击", UtilText.parse(offspring(), "[npc.name]怎么敢这么跟你说话！得让[npc.herHim]认清自己的地位了！"), OFFSPRING_ENCOUNTER_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
							offspring().setFlag(NPCFlagValue.fightOffspringInApartment, false);
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -100));
							setOffspringFlags();
						}
					};
					
				} else if (index == 0 && offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
					return new Response("离开", UtilText.parse(offspring(), "告诉[npc.name]你会换个时间找[npc.herHim]。"), OFFSPRING_ENCOUNTER) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogue(false);
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_LEAVE", offspring()));
								setOffspringFlags();
								offspring().setProtectedFromArcaneStorm(false);
							}	
						};
					
				} else {
					return null;
				}
			}
		}
	};
	
	private static void setOffspringFlags() {
		if(offspring().isVisiblyPregnant()) {
			offspring().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(offspring(), true);
		}
		offspring().setFlag(NPCFlagValue.flagOffspringIntroduced, true);
		Main.game.getDialogueFlags().offspringDialogueTokens = 2;
	}
	
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_APOLOGY = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_APOLOGY", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "让[npc.name]思考一会儿，继续你的旅程。", OFFSPRING_ENCOUNTER_APOLOGY) {
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						offspring().setProtectedFromArcaneStorm(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_FIGHT = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_FIGHT", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗",
						"你跟自己的[npc.daughter]打起来了！",
						offspring());
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_TALKING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			offspring().setProtectedFromArcaneStorm(true);
		}
		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_TALKING", offspring()));

			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().offspringDialogueTokens<=0) {
				if (index == 1) {
					return new Response("该走了", UtilText.parse(offspring(), "[npc.Name]看向墙上的表，明确表示自己必须要走了。"), OFFSPRING_ENCOUNTER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_TALKING_OUT_OF_TIME", offspring()));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							offspring().setProtectedFromArcaneStorm(false);
						}
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("背景", UtilText.parse(offspring(), "询问[npc.name]的背景，还有[npc.she]现在靠什么谋生。"), OFFSPRING_ENCOUNTER_BACKGROUND) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 2) {
					return new Response("闲聊", UtilText.parse(offspring(), "与[npc.name]聊这聊那。"), OFFSPRING_ENCOUNTER_SMALL_TALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 5));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 3) {
					return new Response("鼓励", UtilText.parse(offspring(), "鼓励[npc.name]相信自己加油干。"), OFFSPRING_ENCOUNTER_ENCOURAGE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 4) {
					return new Response("斥责", UtilText.parse(offspring(), "斥责[npc.name]，说[npc.herHim]应该做得更好。"), OFFSPRING_ENCOUNTER_SCOLD) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -10));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 5) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) || !offspring().isAffectionHighEnoughToInviteHome()) {
						return new Response("提供房间",
								UtilText.parse(offspring(), "你觉得在邀请[npc.name]住进莉莱雅的宅邸之前，最好能花时间多了解[npc.herHim]一些……<br/>"
								+ "[style.italics(要求[npc.name]对你拥有至少"+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()+"的好感。)]"),
								null);
						
					} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
						return new Response("提供房间",
								UtilText.parse(offspring(), "你要先获得莉莱雅的同意才能邀请[npc.name]到她的宅邸……"),
								null);
						
					} else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
						return new Response("提供房间",
								UtilText.parse(offspring(), "你没有合适的房间让[npc.name]搬进去。你得先将莉莱雅宅邸中的一个空房间升级为“客房”。"),
								null);
						
					} else {
						return new Response("提供房间", UtilText.parse(offspring(), "询问[npc.name]想不想在莉莱雅的宅邸中要一个房间。"), OFFSPRING_OFFER_ROOM) {
							@Override
							public void effects() {
								offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
								if(offspring().getAffection(Main.game.getPlayer())<0) {
									Main.game.getTextEndStringBuilder().append(offspring().setAffection(Main.game.getPlayer(), 25));
								} else {
									Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 25));
								}
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								offspring().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
							}
						};
					}

				} else if (index == 6) {
					return new Response("昵称", UtilText.parse(offspring(), "让[npc.name]用另一个名字称呼你。"), OFFSPRING_ENCOUNTER_CHOOSE_NAME) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				}  else if (index == 7) {
					if(Main.game.getPlayer().hasItemType(ItemType.PRESENT)) {
						return new Response("赠送礼物", UtilText.parse(offspring(), "把你携带的礼物交给[npc.name]。"), OFFSPRING_PRESENT) {
							@Override
							public void effects() {
								Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.PRESENT));
								
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 15));
								
								offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
								Main.game.getDialogueFlags().offspringDialogueTokens--;
							}
						};
					} else {
						return null;
					}
					
				} else if (index == 8 && Main.game.isIncestEnabled()) {
					return new Response("做爱", UtilText.parse(offspring(), "告诉[npc.name]，你很想跟[npc.herHim]做爱。"), OFFSPRING_ENCOUNTER_SEX,
							Util.newArrayListOfValues(Fetish.FETISH_INCEST),
							CorruptionLevel.FIVE_CORRUPT,
							null,
							null,
							null) {
						@Override
						public void effects() {
							if(offspring().isAttractedTo(Main.game.getPlayer())) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 20));
							} else if(offspring().getHistory()!=Occupation.NPC_PROSTITUTE){
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -10));
							}
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}	
					};
					
				} else if (index == 10) {
					return new Response("袭击", UtilText.parse(offspring(), "是时候让[npc.herHim]认清自己在家庭里的地位了！"), OFFSPRING_ENCOUNTER_APARTMENT_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
							offspring().setFlag(NPCFlagValue.fightOffspringInApartment, true);
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -100));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
						}
					};
					
				} else if (index == 11) {
					if(!offspring().isAllowingPlayerToManageInventory()) {
						return new Response("物品栏", UtilText.parse(offspring(), "[npc.Name]不够喜欢你，所以你无法决定[npc.she]的吃喝和穿戴。"), null);
					} else {
						return new ResponseEffectsOnly("物品栏", UtilText.parse(offspring(), "管理[npc.namePos]的物品栏。")) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_TALKING_INVENTORY", offspring()));
								Main.mainController.openInventory(offspring(), InventoryInteraction.FULL_MANAGEMENT);
							}
						};
					}
					
				} else if (index == 0) {
					return new Response("离开", UtilText.parse(offspring(), "告诉[npc.name]你会换个时间找[npc.herHim]。"), OFFSPRING_ENCOUNTER) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_TALKING_LEAVE", offspring()));
								offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
								offspring().setProtectedFromArcaneStorm(false);
							}
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogue(false);
							}
						};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_BACKGROUND = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() { // TODO use offspring().flagBackgroundProgress
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_BACKGROUND", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("背景", "你已经聊过[npc.namePos]的背景了。", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_SMALL_TALK = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_SMALL_TALK", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 2 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("闲聊", "你刚刚与[npc.name]交谈了几句。", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_ENCOURAGE = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_ENCOURAGE", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 3 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("鼓励", "你鼓励过[npc.name]了。", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_SCOLD = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_SCOLD", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 4 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("责备", "你已经责备过[npc.name]了。", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_OFFER_ROOM = new DialogueNode("提供房间", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_OFFER_ROOM", offspring());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("带去房间", "将[npc.name]带去新房间。", OFFSPRING_OFFER_ROOM_BACK_HOME) {
					@Override
					public void effects() {
						Cell c = OccupancyUtil.getFreeRoomForOccupant();
						offspring().setLocation(c.getType(), c.getLocation(), true);
						Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
						Main.game.getPlayer().addFriendlyOccupant(offspring());
						Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 50));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_OFFER_ROOM_BACK_HOME = new DialogueNode("新房间", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_OFFER_ROOM_BACK_HOME", offspring());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "给[npc.name]点时间收拾[npc.her]新房间。你随时可以回来和[npc.herHim]聊天。", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_CHOOSE_NAME = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "你决定让[npc.name]用另一个名字称呼你。"
						+ "从今往后，[npc.sheIs]会称呼你为“[npc.pcName]”。"
					+ "</p>"
					
					// TODO align this properly
					
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div style='position:relative; display: inline-block; padding:0 auto; margin:0 auto;vertical-align:middle;width:100%;'>"
							+ "<p style='float:left; padding:0; margin:0; height:25px; line-height:25px;'>[npc.Name]会称呼你为: </p>"
							+ "<form style='float:left; padding:auto 0 auto 0;'><input type='text' id='offspringPetNameInput' value='"+ UtilText.parseForHTMLDisplay(offspring().getPetName(Main.game.getPlayer()))+ "'></form>"
							+ " <div class='SM-button' id='"+offspring().getId()+"_PET_NAME' style='float:left; width:auto; height:28px;'>"
								+ "重命名"
							+ "</div>"
						+ "</div>"
						+ "<p>"
						+ "<i>“爸爸”/“妈妈”、“爹地”/“妈咪”是特殊的名字，会自动根据角色女性化程度切换。</i>"
						+ "</p>"
					+ "</div>"
					
					+ "<p id='hiddenFieldName' style='display:none;'></p>");
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 5 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("昵称", "你已经让[npc.name]用另一个名字称呼你了。", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_PRESENT = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_PRESENT", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 7 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("赠送礼物", "你已经送过[npc.name]礼物了。", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_SEX = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_SEX", offspring()));

//			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(offspring().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("乱伦",
							"是时候让[npc.daughter]瞧瞧[pc.mother]的本领了！",
							true, true,
							new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(offspring()),
								null,
								null),
							AFTER_SEX_CONSENSUAL,
							"");
					
				} else if (index == 2) {
					return new ResponseSex("服从型性爱",
							"是时候让[npc.daughter]给你展示一下[npc.she]的本领了！",
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null),
							AFTER_SEX_CONSENSUAL,
							"");
					
				} else {
					return null;
				}
				
			} else if(offspring().getHistory()==Occupation.NPC_PROSTITUTE){
				if (index == 8) {
					if(Main.game.getPlayer().getMoney()>=100) {
						return new ResponseSex("乱伦("+UtilText.formatAsMoney(100, "span")+")",
								"付给[npc.daughter]100火币就能如愿！",
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(offspring()),
										null,
										null) {
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(!character.isPlayer()) {
											return SexPace.SUB_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_CONSENSUAL,
								"");
					} else {
						return new Response("支付"+UtilText.formatAsMoneyUncoloured(100, "span"), "你的钱不够……", null);
					}
					
				} else if (index == 9) {
					if(Main.game.getPlayer().getMoney()>=100) {
						return new ResponseSex("服从型性爱("+UtilText.formatAsMoney(100, "span")+")",
								"付给[npc.daughter]100火币就能如愿！",
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null) {
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(!character.isPlayer()) {
											return SexPace.DOM_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_CONSENSUAL,
								"");
					} else {
						return new Response("支付"+UtilText.formatAsMoneyUncoloured(100, "span"), "你的钱不够……", null);
					}
				} else {
					return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
				}
				
				
			} else {
				if (index == 8 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
					return new Response("做爱", "你已经让[npc.name]跟你做爱了……", null);
					
				} else {
					return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
				}
			}
			
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_APARTMENT_FIGHT = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_APARTMENT_FIGHT", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗", "你跟自己的[npc.daughter]打起来了！", offspring());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("胜利", "", true) {

		@Override
		public String getDescription() {
			return "你打败了[npc.name]！";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY", offspring());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			
			if (index == 1) {
				return new Response("道歉", "可能你做得太过了……或许应该道个歉？", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_APOLOGISE", offspring()));
						Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 25));
						offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						offspring().setProtectedFromArcaneStorm(false);
					}
				};
				
			} else if (index == 2 && Main.game.isIncestEnabled()) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("做爱",
							"反正这<i>正是</i>[npc.she]想要的！",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, Fetish.FETISH_INCEST.getAssociatedCorruptionLevel(), null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
							null,
							null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_SEX", offspring()));
					
				} else {
					return new ResponseSex(
							"强奸[npc.herHim]", "[npc.She]竟敢对你发起攻击，就该受罚……",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
							null,
							null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_RAPE", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, true);
						}
					};
				}
				
			} else if (index == 3 && Main.game.isIncestEnabled()) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("做爱(温柔)",
							"反正这<i>正是</i>[npc.she]想要的！",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_SEX_GENTLE", offspring()));
					
				} else {
					return new ResponseSex("强奸[npc.herHim](温柔)",
							"[npc.she]竟敢对你发起攻击，就该受罚……",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_RAPE_GENTLE", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, true);
						}
					};
				}
				
			} else if (index == 4 && Main.game.isIncestEnabled()) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("做爱(粗暴)",
							"反正这<i>正是</i>[npc.she]想要的！",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_SEX_ROUGH", offspring()));
					
				} else {
					return new ResponseSex("强奸[npc.herHim](粗暴)",
							"[npc.she]竟敢对你发起攻击，就该受罚……",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_RAPE_ROUGH", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, true);
						}
					};
				}
				
			} else if (index == 5 && Main.game.isIncestEnabled()) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("顺从",
							"你不太确定现在该做什么……<br/>"
								+ "也许最好让[npc.name]来决定接下来做什么？",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_SUBMIT", offspring())) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 50));
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
				} else {
					return new Response("屈服",
							"你不能向[npc.herHim]屈服，因为[npc.sheHasFull]对和你做爱没有任何兴趣！",
							null);
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("物品栏", "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具伸出手……"){
					@Override
					public void effects() {
						Main.mainController.openInventory(offspring(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"移除角色",
						UtilText.parse(offspring(), "赶[npc.name]走。"
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
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_REMOVE_CHARACTER", offspring()));
						
						Main.game.banishNPC(offspring());
					}
				};
				
			} else if (index == 0) {
				return new Response("离开", "现在你已经给[npc.name]上了一课，可以继续你的旅程……", AFTER_COMBAT_VICTORY) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_LEAVE", offspring()));
						offspring().setProtectedFromArcaneStorm(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("落败", "", true) {

		@Override
		public String getDescription() {
			return "你被[npc.name]打败了！";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_DEFEAT", offspring());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(offspring().isAttractedTo(Main.game.getPlayer()) && Main.game.isIncestEnabled()) {
				if (index == 1) {
					return new ResponseSex("做爱",
							"[npc.Name]强行压住了你……",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_DEFEAT_SEX", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex("做爱(渴求)",
							"[npc.Name]强行压住了你……",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_DEFEAT_SEX_EAGER", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("抵抗做爱",
							"[npc.Name]强行压住了你……",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_DEFEAT_SEX_RESIST", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("继续", "你该继续你的旅程了……", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNode getNextDialogue(){
							return Main.game.getDefaultDialogue(false);
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
							offspring().setProtectedFromArcaneStorm(false);
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_CONSENSUAL = new DialogueNode("性交之后", "", true) {
		
		@Override
		public String getDescription(){
			return "你已经满足了你[npc.daughter]的欲望……暂时的……";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_CONSENSUAL", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", AFTER_SEX_CONSENSUAL){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						offspring().setProtectedFromArcaneStorm(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("返回", "", true) {
		
		@Override
		public String getDescription(){
			return "你已经爽过了，离开这里让[npc.name]恢复一下吧。";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_VICTORY", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", AFTER_SEX_VICTORY){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_VICTORY_LEAVING", offspring()));
						offspring().setProtectedFromArcaneStorm(false);
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("物品栏", "现在你可以随意拿取[npc.namePos]衣服和道具，谁也拦不了你……"){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10 && Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.DOMINION_BACK_ALLEYS)) {
				return new Response(
						"移除角色",
						UtilText.parse(offspring(), "赶[npc.name]走。"
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
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_VICTORY_REMOVE_CHARACTER", offspring()));
						
						Main.game.banishNPC(offspring());
					}
				};
				
			} else {
				return null;
			}
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
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_DEFEAT", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", AFTER_SEX_VICTORY){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						offspring().setProtectedFromArcaneStorm(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
