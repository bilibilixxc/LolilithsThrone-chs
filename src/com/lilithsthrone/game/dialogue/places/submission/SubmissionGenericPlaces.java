package com.lilithsthrone.game.dialogue.places.submission;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.character.npc.submission.HazmatRat;
import com.lilithsthrone.game.character.npc.submission.RatWarrensCaptive;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.WesQuest;
import com.lilithsthrone.game.dialogue.places.dominion.EnforcerWarehouse;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.places.submission.gamblingDen.GamblingDenDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.story.LyssiethReveal;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5
 * @author Innoxia
 */
public class SubmissionGenericPlaces {
	
	private static void applyClaireMeetingEffects() {
		if(Main.game.getNpc(Claire.class).isVisiblyPregnant()) {
			Main.game.getNpc(Claire.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
	}

	public static final DialogueNode WALKWAYS = new DialogueNode("通道", "", false) {
		
		@Override
		public String getAuthor() {
			return "Duner & Innoxia";
		}
		
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "WALKWAYS")
					+ (Math.random()<0.2f
							?UtilText.parseFromXMLFile("places/submission/submissionPlaces", "WALKWAYS_EXTRA")
							:"");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode TUNNEL = new DialogueNode("隧道", "", false) {

		@Override
		public String getAuthor() {
			return "Duner";
		}

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "TUNNEL"));

			boolean pacified = true;
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
				pacified = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated);
			}
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_DEMON)) {
				pacified = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated);
			}
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
				pacified = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated);
			}
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)) {
				pacified = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated);
			}
			
			if(!pacified) {
				UtilText.nodeContentSB.append(
						"<span style='color:"+Main.game.getPlayer().getLocationPlace().getPlaceType().getColour().toWebHexString()+";'>"
								+ UtilText.parseFromXMLFile("places/submission/submissionPlaces", "TUNNEL_IMP_CONTROL")
						+"</span>");
			}
			
			for(GameCharacter npc : Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell())) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription(!pacified));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return AbstractEncounter.exploreArea("隧道");
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
			}
			return null;
		}
	};

	public static final DialogueNode BAT_CAVERNS = new DialogueNode("蝙蝠洞窟", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "BAT_CAVERNS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("蝙蝠洞窟", "进入蝙蝠洞窟。", PlaceType.BAT_CAVERN_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "BAT_CAVERNS_ENTRY"));
						Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_ENTRANCE, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode RAT_WARREN = new DialogueNode("鼠窟", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR) || Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_THREE_END) {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "RAT_WARREN_CLOSED");
			}
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "RAT_WARREN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR) && Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)!=Quest.VENGAR_THREE_END) {
				if(index==1) {
					return new Response("敲敲",
							"敲门，看是否有人回应。",
							RAT_WARREN_KNOCK_ON_DOOR) {
						@Override
						public void effects() {
							RatWarrensDialogue.init();
							Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode RAT_WARREN_KNOCK_ON_DOOR = new DialogueNode("鼠窟", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "RAT_WARREN_KNOCK_ON_DOOR", RatWarrensDialogue.getGuards(false));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			boolean freeEntry = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntry) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore);
			if(index == 1) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_VENGAR)) {
					return new Response("进入",
							"你没有合适的理由进入鼠窟……",
							null);
				} else {
					if(freeEntry) {
						return new Response("进入",
								"卫兵认出了你，你可以自由进入鼠窟。",
								RatWarrensDialogue.RAT_WARREN_INITIAL_ENTRY);
					}
					return new Response("解释",
							"告诉守卫你是代表阿克塞尔来和文加谈生意的。",
							RatWarrensDialogue.RAT_WARREN_INITIAL_ENTRY);
				}
				
			} else if(index==2) {
				return new Response("退开",
						freeEntry
							?"决定不进入鼠窟，返回大门。"
							:"你在这里没有任何生意要谈，所以你不能进去。最好在守卫实施威胁之前离开……",
						RAT_WARREN_STEP_BACK) {
					@Override
					public void effects() {
						if(freeEntry) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "RAT_WARREN_ENTRY_STEP_BACK", RatWarrensDialogue.getGuards(false)));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "RAT_WARREN_STEP_BACK", RatWarrensDialogue.getGuards(false)));
						}
						RatWarrensDialogue.exit();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode RAT_WARREN_STEP_BACK = new DialogueNode("鼠窟", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return RAT_WARREN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode GAMBLING_DEN = new DialogueNode("赌场", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "GAMBLING_DEN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("赌场", "进入赌场。", GamblingDenDialogue.ENTRANCE) {
					@Override
					public void effects() {
						List<NPC> gamblersPresent = Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.GAMBLING_DEN).getCell(PlaceType.GAMBLING_DEN_GAMBLING));
						
						for(NPC npc : gamblersPresent) {
							if(npc instanceof GamblingDenPatron) {
								Main.game.banishNPC(npc);
							}
						}
						
						try {
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.COPPER, false), false);
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.COPPER, false), false);
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.SILVER, false), false);
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.SILVER, false), false);
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.GOLD, false), false);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode LILIN_PALACE_CAVERN = new DialogueNode("洞窟", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_CAVERN"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethIntroduced)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_CAVERN_MET_ELIZABETH"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_CAVERN_NOT_MET_ELIZABETH"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode LILIN_PALACE_GATE = new DialogueNode("莉西丝的宫殿门", "", true) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)
					|| (Main.game.getNpc(Elizabeth.class).getWorldLocation()==WorldType.SUBMISSION
						&& Main.game.getNpc(Elizabeth.class).isVisiblyPregnant()
						&& !Main.game.getNpc(Elizabeth.class).isCharacterReactedToPregnancy(Main.game.getPlayer()));
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutUniforms, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutSurname, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutRoutine, false);
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE"));
			
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_GRANTED"));
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_C_SIRENS_FALL) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED_QUEST_COMPLETED"));
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_B_SIRENS_CALL) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED_QUEST_GAINED"));
			
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethIntroduced)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED_INTRODUCED"));
					
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED"));
			}
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			
			// First time visiting this tile:
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethIntroduced)) {
				if(index==1) {
					return new Response("介绍", "告诉魅魔和她的小弟们你是谁。", LILIN_PALACE_GATE_GENERIC_TALK) {
						@Override
						public void effects() {
							Main.game.getNpc(Elizabeth.class).setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethIntroduced, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_INTRODUCTION"));
						}
					};
				}
				return null;
			}
			
			// Completed the Siren's quest:
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
				if(Main.game.getNpc(Elizabeth.class).getWorldLocation()==WorldType.SUBMISSION) {
					if(Main.game.getNpc(Elizabeth.class).isVisiblyPregnant() && !Main.game.getNpc(Elizabeth.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
						if (index == 1) {
							return new Response("怀孕", "问问伊丽莎白在怀孕期间是否需要什么帮助。", LILIN_PALACE_GATE_GENERIC_TALK) {
								@Override
								public void effects() {
									Main.game.getNpc(Elizabeth.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_PREGNANCY"));
								}
							};
						}
						
					} else {
						if (index == 1) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutUniforms)) {
								return new Response("制服", "你已经问过伊丽莎白关于她的制服了……", null);
							}
							return new Response("制服", "问问伊丽莎白为什么她和她的部队穿着历史悠久的制服。", LILIN_PALACE_GATE_GENERIC_TALK) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutUniforms, true);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_UNIFORMS"));
								}
							};
							
						} else if (index == 2) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutSurname)) {
								return new Response("姓氏", "你已经问过伊丽莎白她的姓了……", null);
							}
							return new Response("姓氏", "问问伊丽莎白为什么不愿意别人称呼她的姓氏。", LILIN_PALACE_GATE_GENERIC_TALK) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutSurname, true);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_SURNAME"));
								}
							};
							
						} else if (index == 3) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutRoutine)) {
								return new Response("日常", "你已经问过伊丽莎白她的日常生活了……", null);
							}
							return new Response("日常", "问问伊丽莎白她的日常生活。", LILIN_PALACE_GATE_GENERIC_TALK) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutRoutine, true);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ROUTINE"));
								}
							};
						}
					}
				}
				return null;
			}

			// Handing in the Siren's quest:
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_C_SIRENS_FALL) {
				if (index == 1) {
					return new Response("汇报", "告诉伊丽莎白你是怎么对付塞壬的。", LILIN_PALACE_GATE_GENERIC_TALK) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_COMPLETED_QUEST_WITH_COMBAT"));
								Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().removedItemFromInventoryText(ItemType.LYSSIETHS_RING));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_COMPLETED_QUEST_WITH_TRICKERY"));
							}
							if(!Main.game.getPlayer().hasClothingType(ClothingType.FINGER_LYSSIETHS_RING, true)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(ClothingType.FINGER_LYSSIETHS_RING, false), false));
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN));
						}
					};
					
				}
				return null;
			}
			
			// Normal dialogue, for before completing the Siren's quest:
			if (index == 1) {
				return new ResponseEffectsOnly("走开", "决定离开伊丽莎白和王室卫队，回到屈城区。") {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
						Main.game.setContent(new Response("", "", LILIN_PALACE_CAVERN));
					}
				};

			} else if (index == 2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutUniforms)) {
					return new Response("制服", "你已经问过伊丽莎白关于她的制服了……", null);
				}
				return new Response("制服", "问问伊丽莎白为什么她和她的部队穿着历史悠久的制服。", LILIN_PALACE_GATE_GENERIC_TALK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutUniforms, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_UNIFORMS"));
					}
				};

			} else if (index == 3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutSurname)) {
					return new Response("姓氏", "你已经问过伊丽莎白她的姓了……", null);
				}
				return new Response("姓氏", "问问伊丽莎白为什么不愿意别人称呼她的姓氏。", LILIN_PALACE_GATE_GENERIC_TALK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutSurname, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_SURNAME"));
					}
				};
			} else if (index == 4) {
				if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_A_INTO_THE_DEPTHS) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
						return new Response("觐见", "问问伊丽莎白如何才能觐见莉西丝。", LILIN_PALACE_GATE_GENERIC_TALK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_AUDIENCE_SKIP"));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(ClothingType.FINGER_LYSSIETHS_RING, false), false));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN));
							}
						};
						
					} else {
						return new Response("觐见", "问问伊丽莎白如何才能觐见莉西丝。", LILIN_PALACE_GATE_GENERIC_TALK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_AUDIENCE"));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING), false));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL));
							}
						};
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_B_SIRENS_CALL) {
					return new Response("觐见", "你已经问过伊丽莎白如何才能觐见莉西丝了。你需要找到并征服莉西丝的女儿，“暗夜塞壬”。", null);
					
				} else {
					return new Response("觐见",
							"伊丽莎白说不莉西丝接待访客，至少目前你没有特别的理由要见她。"
								+ "也许在未来的某一天，你需要求见莉西丝……",
							null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode LILIN_PALACE_GATE_GENERIC_TALK = new DialogueNode("莉西丝的宫殿门", "", true, true) {

		@Override
		public boolean isTravelDisabled() {
			return LILIN_PALACE_GATE.isTravelDisabled();
		}
		
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
			return LILIN_PALACE_GATE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LILIN_PALACE = new DialogueNode("莉西丝的宫殿", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_D_MEETING_A_LILIN) {
				if(Main.game.getPlayer().hasCompanions()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_ELIZABETH_LEADS_COMPANIONS", Main.game.getPlayer().getMainCompanion()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_ELIZABETH_LEADS"));
				}
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_ELIZABETH_ESCORT"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_D_MEETING_A_LILIN) {
					return new Response("进入",
							"与伊丽莎白进入宫殿，继续跟随她到正殿。"
									+ (Main.game.getPlayer().hasCompanions()?"<br/>[style.italicsMinorBad(这将遣散你的所有同伴，他们将被送回家。)]":""),
							LyssiethReveal.ENTRANCE_WITH_ELIZABETH) {
						@Override
						public void effects() {
							Main.game.getPlayer().removeAllCompanions(true);
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_ENTRANCE);
							Main.game.getNpc(Elizabeth.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_ENTRANCE);
							((DarkSiren)Main.game.getNpc(DarkSiren.class)).postDefeatReset();
						}
					};
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
					return new Response("进入",
							Main.game.getNpc(Elizabeth.class).getWorldLocation()==WorldType.SUBMISSION
								?"告诉伊丽莎白你想进入宫殿，让她为你打开大门。"
								:"告诉守卫你要进入宫殿，让她为你打开大门。",
							PlaceType.LYSSIETH_PALACE_ENTRANCE.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_ENTRANCE);
							Main.game.getNpc(Elizabeth.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_GATE);
						}
					};
					
				} else {
					return new Response("进入", "门锁着……", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode IMP_FORTRESS_ALPHA = new DialogueNode("粗糙要塞", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ALPHA"));

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaPacified)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_PACIFIED"));
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_DEFEATED"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			boolean canEnter = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaPacified);
			
			if(index==1) {
				return new Response(canEnter?"进入":"靠近",
						canEnter?"进入要塞。":"靠近要塞入口的守卫。",
								PlaceType.FORTRESS_ALPHA_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
							if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_ALPHA, false).isEmpty()) {
								ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_ALPHA);
							} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_ALPHA, false).contains(Main.game.getNpc(FortressAlphaLeader.class))) {
								Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP, true);
							} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_ALPHA).isEmpty()) {
								ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_ALPHA);
							}
						}
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_ENTRANCE);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("飞行",
							canEnter
								?"你只需穿过前门即可，除非你想炫耀一下，否则没必要这么做……"
								:"飞越要塞的围墙，避免与门口的小恶魔守卫发生冲突。",
							PlaceType.FORTRESS_ALPHA_COURTYARD.getDialogue(false)) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
								if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_ALPHA, false).isEmpty()) {
									ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_ALPHA);
								} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_ALPHA, false).contains(Main.game.getNpc(FortressAlphaLeader.class))) {
									Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP, true);
								} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_ALPHA).isEmpty()) {
									ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_ALPHA);
								}
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_FLY_ENTRY"));
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_COURTYARD);
						}
					};
				} else {
					return new Response("飞行", "你或你队伍中的一员无法飞行，你不能飞越围墙进入要塞！", null);
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode IMP_FORTRESS_FEMALES = new DialogueNode("粗糙要塞", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_FEMALES"));

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesPacified)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_PACIFIED"));
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_DEFEATED"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			boolean canEnter = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesPacified);
			
			if(index==1) {
				return new Response(canEnter?"进入":"靠近",
						canEnter?"进入要塞。":"靠近要塞入口的守卫。",
								PlaceType.FORTRESS_FEMALES_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
							if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_FEMALES, false).isEmpty()) {
								ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_FEMALES);
							} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_FEMALES, false).contains(Main.game.getNpc(FortressFemalesLeader.class))) {
								Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP, true);
							} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_FEMALES).isEmpty()) {
								ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_FEMALES);
							}
						}
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_ENTRANCE);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("飞行",
							canEnter
								?"你只需穿过前门即可，除非你想炫耀一下，否则没必要这么做……"
								:"飞越要塞的围墙，避免与门口的小恶魔守卫发生冲突。",
							PlaceType.FORTRESS_FEMALES_COURTYARD.getDialogue(false)) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
								if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_FEMALES, false).isEmpty()) {
									ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_FEMALES);
								} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_FEMALES, false).contains(Main.game.getNpc(FortressFemalesLeader.class))) {
									Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP, true);
								} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_FEMALES).isEmpty()) {
									ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_FEMALES);
								}
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_FLY_ENTRY"));
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_COURTYARD);
						}
					};
				} else {
					return new Response("飞行", "你或你队伍中的一员无法飞行，你不能飞越围墙进入要塞！", null);
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode IMP_FORTRESS_MALES = new DialogueNode("粗糙要塞", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_MALES"));

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesPacified)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_PACIFIED"));
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_DEFEATED"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			boolean canEnter = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesPacified);
			
			if(index==1) {
				return new Response(canEnter?"进入":"靠近",
						canEnter?"进入要塞。":"靠近要塞入口的守卫。",
								PlaceType.FORTRESS_MALES_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
							if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_MALES, false).isEmpty()) {
								ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_MALES);
							} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_MALES, false).contains(Main.game.getNpc(FortressMalesLeader.class))) {
								Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP, true);
							} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_MALES).isEmpty()) {
								ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_MALES);
							}
						}
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_ENTRANCE);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("飞行",
							canEnter
								?"你只需穿过前门即可，除非你想炫耀一下，否则没必要这么做……"
								:"飞越要塞的围墙，避免与门口的小恶魔守卫发生冲突。",
							PlaceType.FORTRESS_MALES_COURTYARD.getDialogue(false)) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
								if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_MALES, false).isEmpty()) {
									ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_MALES);
								} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_MALES, false).contains(Main.game.getNpc(FortressMalesLeader.class))) {
									Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP, true);
								} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_MALES).isEmpty()) {
									ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_MALES);
								}
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_FLY_ENTRY"));
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_COURTYARD);
						}
					};
				} else {
					return new Response("飞行", "你或你队伍中的一员无法飞行，你不能飞越围墙进入要塞！", null);
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode IMP_FORTRESS_DEMON = new DialogueNode("石制城堡", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_DEFEATED");
			}
			
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelEncountered, true);
			
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_BASE"));

			if((Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY)
							&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_2)
							&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_3))
						|| Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEYS"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_NO_KEYS"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
					return new Response("进入", "进入暗夜塞壬城堡的废墟。", PlaceType.FORTRESS_DEMON_ENTRANCE.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_ENTRANCE);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_RUINS_ENTRY"));
						}
					};
					
				} else if((Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY)
								&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_2)
								&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_3))
							|| Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
					return new Response("进入", "进入城堡。", FORTRESS_DEMON_ENTRANCE_KEY_ENTRY) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_ENTRANCE);
							ImpCitadelDialogue.applyEntry();

							if(!Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEY_ENTRY"));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), false), false));
								Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
								Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2));
								Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEY_ENTRY_REPEAT"));
								if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonImpsDefeated)) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEY_ENTRY_REPEAT_IMPS"));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEY_ENTRY_REPEAT_NO_IMPS"));
								}
							}
						}
					};
					
				} else {
					return new Response("进入", "你没集齐三把钥匙，打不开大门……", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode FORTRESS_DEMON_ENTRANCE_KEY_ENTRY = new DialogueNode("", "", false) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ImpCitadelDialogue.ENTRANCE.getResponse(responseTab, index);
		}
	};

	// Entrance and exits:

	public static final DialogueNode SEWER_ENTRANCE = new DialogueNode("执法者检查点", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public boolean isTravelDisabled() {
			return Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR) && Main.game.getPlayer().isHasSlaverLicense() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.milkersClaireDialogue);
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.milkersClaireDialogue)) {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "SEWER_ENTRANCE_CLAIRE_MILKERS");
			} else {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "SEWER_ENTRANCE");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR) && Main.game.getPlayer().isHasSlaverLicense() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.milkersClaireDialogue)) {
				if (index == 1) {
					return new Response("收下“奶牛”", "告诉克莱尔，你会对从鼠窟里救出的奴隶负责", SEWER_ENTRANCE_MILKERS_RESOLVED) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.milkersClaireDialogue, true);
							Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "SEWER_ENTRANCE_CLAIRE_MILKERS_ACCEPTED"));
							if(RatWarrensDialogue.getMilkers().isEmpty()) {
								RatWarrensDialogue.spawnMilkers();
							}
							for(GameCharacter milker : RatWarrensDialogue.getMilkers()) {
								((RatWarrensCaptive)milker).applyMilkingEquipment(false, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.VAGINA));
								milker.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
								Main.game.getPlayer().addSlave((NPC) milker);
								milker.setObedience(100);
								milker.setHistory(Occupation.NPC_SLAVE);
							}
						}
					};

				} else if (index == 2) {
					return new Response("谢绝", "告诉克莱尔你对从鼠窟救出的奴隶不感兴趣。", SEWER_ENTRANCE_MILKERS_RESOLVED) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.milkersClaireDialogue, true);
							Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "SEWER_ENTRANCE_CLAIRE_MILKERS_DECLINED"));
							RatWarrensDialogue.banishMilkers();
						}
					};
				}
				
			} else {
				if (index == 1) {
					return new Response("御城区", "回到御城区。", PlaceType.DOMINION_EXIT_TO_SUBMISSION.getDialogue(false)){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_EXIT_TO_SUBMISSION, false);
						}
					};

				} else if (index == 2) {
					return new Response("克莱尔", "靠近克莱尔和她打招呼。", CLAIRE);
					
				} else if (index == 3) {
					return new Response("自动售货机", "靠近执法者前哨站外的自动售货机。", VENDING_MACHINE);
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode SEWER_ENTRANCE_MILKERS_RESOLVED = new DialogueNode("", "", false, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SEWER_ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE = new DialogueNode("克莱尔", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			responses.add(new Response("返回", "告诉克莱尔你得走了。", SEWER_ENTRANCE));
			

			if(Main.game.getCurrentDialogueNode()==CLAIRE_INFO_SUBMISSION_SOCIETY) {
				responses.add(new Response("信息", "你已经问过克莱尔屈城区的社会情况了。", null));
			} else {
				responses.add(new Response("信息", "问问克莱尔屈城区的社会状况。", CLAIRE_INFO_SUBMISSION_SOCIETY) {
					@Override
					public void effects() {
						applyClaireMeetingEffects();
					}
				});
			}
			
			if(Main.game.getCurrentDialogueNode()==CLAIRE_INFO_LYSSIETH) {
				responses.add(new Response("莉西丝", "你已经问过克莱尔关于莉西丝了。", null));
			} else {
				responses.add(new Response("莉西丝", "问问克莱尔关于莉西丝。", CLAIRE_INFO_LYSSIETH) {
					@Override
					public void effects() {
						applyClaireMeetingEffects();
					}
				});
			}
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireAskedTeleportation)) {
				if(Main.game.getCurrentDialogueNode()==CLAIRE_INFO_SWORD_ORICL) {
					responses.add(new Response("SWORD & ORICL", "你已经问过克莱尔关于执法者的分部“SWORD”和“ORICL”。", null));
				} else {
					responses.add(new Response("SWORD & ORICL", "问问克莱尔执法者的分部“SWORD”和“ORICL”。", CLAIRE_INFO_SWORD_ORICL));
				}
				
			} else {
				responses.add(new Response("传送板", "问问克莱尔传送板的事。", CLAIRE_INFO_TELEPORTATION) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.claireAskedTeleportation, true);
						applyClaireMeetingEffects();
					}
				});
			}
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireAskedTeleportation)) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_TELEPORTATION) || !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_TELEPORTATION)) {
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN)) {
						responses.add(new Response("传送板",
								"在屈城区执行者有理由相信你之前，你无法看到传送板……"
										+ "<br/>[style.italicsMinorBad(要选择这个选项，你需要完成任务'"+QuestLine.SIDE_SLIME_QUEEN.getName()+"'.)]",
								null));
						
					} else {
						responses.add(new Response("传送板",
								"告诉克莱尔你想看看传送板。"
										+ "<br/>[style.italicsQuestSide(这将会开启一个支线任务，你需要先解决该任务，然后才能继续之前的事……)]",
								CLAIRE_TELEPORTATION_PADS) {
							@Override
							public void effects() {
								applyClaireMeetingEffects();
							}
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_SIDE;
							}
						});
					}
					
				} else {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireAskedWarehouseEscape)) {
						responses.add(new ResponseSex(
								"危险性爱",
								"从你在SWORD仓库的经历来看，克莱尔似乎对危险的性爱情有独钟。"
										+ "如果你想的话，你总是可以满足她对它的渴望……",
								true,
								true,
								new SexManagerDefault(
										SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Claire.class), SexSlotStanding.STANDING_SUBMISSIVE))) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								null,
								null,
								AFTER_CLAIRE_SEX,
								UtilText.parseFromXMLFile("places/submission/submissionPlaces", "START_CLAIRE_SEX")));
						
					} else {
						responses.add(new Response("仓库", "问问克莱尔是否还记得你从SWORD仓库逃跑的事。", CLAIRE_WAREHOUSE) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.claireAskedWarehouseEscape, true);
								applyClaireMeetingEffects();
							}
						});
					}
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN)==Quest.SLIME_QUEEN_TWO) {
				responses.add(new Response("返回汇报", "报告史莱姆所说的“史莱姆女王”。", CLAIRE_INFO_REPORT_BACK) {
					@Override
					public void effects() {
						applyClaireMeetingEffects();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(5000));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_THREE));
					}
				});
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN)==Quest.SLIME_QUEEN_SIX_SUBMIT
						|| Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN)==Quest.SLIME_QUEEN_SIX_FORCE
						|| Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN)==Quest.SLIME_QUEEN_SIX_CONVINCE) {
				responses.add(new Response("返回汇报", "向克莱尔报告你已经打败了史莱姆女王。", CLAIRE_INFO_SLIME_QUEEN_REPORT_BACK) {
					@Override
					public void effects() {
						applyClaireMeetingEffects();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(20000));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("dsg_quest_hazmat_rat_card"), false));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SIDE_UTIL_COMPLETE));
					}
				});
			}
			
			if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_VENGAR)
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_OPTIONAL_CLAIRE)
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_END)
					&& !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				responses.add(new Response("文加", "请克莱尔帮忙对付文加。", CLAIRE_VENGAR_HELP) {
					@Override
					public void effects() {
						applyClaireMeetingEffects();
					}
				});
			}

			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_2) {
				responses.add(new Response("匿名举报",
						"询问克莱尔是否有匿名提交犯罪活动证据的方法，这样你就可以存储有埃勒与黑帮打交道证据的奥术录音机了。",
						WesQuest.CLAIRE_ELLE_EVIDENCE) {
					@Override
					public void effects() {
						applyClaireMeetingEffects();
					}
				});
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_REPORT_BACK = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_REPORT_BACK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "让克莱尔回到她的工作岗位，继续旅途。", SEWER_ENTRANCE);
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode CLAIRE_INFO_SLIME_QUEEN_REPORT_BACK = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeQueenHelped)) {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_SLIME_QUEEN_REPORT_BACK_LIE");
			} else {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_SLIME_QUEEN_REPORT_BACK");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE_VENGAR_HELP = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_VENGAR_HELP");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待",
						"照克莱尔说的做，等她回来。",
						CLAIRE_VENGAR_HELP_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addOptionalQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_OPTIONAL_CLAIRE));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.RESONANCE_STONE), false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CLAIRE_VENGAR_HELP_WAIT = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_VENGAR_HELP_WAIT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_SUBMISSION_SOCIETY = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_SUBMISSION_SOCIETY"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_LYSSIETH = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_LYSSIETH"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_TELEPORTATION = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_TELEPORTATION"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_SWORD_ORICL = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_SWORD_ORICL"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	
	
	public static final DialogueNode CLAIRE_TELEPORTATION_PADS = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_TELEPORTATION_PADS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("传送！", "传送板已经启动，将你和克莱尔送往未知目的地！", ENFORCER_WARHOUSE_APPEARANCE) {
					@Override
					public void effects() {
						EnforcerWarehouse.initWarehouse();
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_PADS, false);
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);

						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "ENFORCER_WARHOUSE_APPEARANCE"));
						
						if(Main.game.getPlayer().hasCompanions()) {
							if(Main.game.getPlayer().getMainCompanion().isElemental()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "ENFORCER_WARHOUSE_APPEARANCE_ELEMENTAL", Main.game.getPlayer().getMainCompanion()));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "ENFORCER_WARHOUSE_APPEARANCE_COMPANIONS", Main.game.getPlayer().getMainCompanion()));
							}
							Main.game.getPlayer().removeAllCompanions(true);
							
						} else {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "ENFORCER_WARHOUSE_APPEARANCE_SOLO"));
						}

						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "ENFORCER_WARHOUSE_APPEARANCE_END"));
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_TELEPORTATION));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENFORCER_WARHOUSE_APPEARANCE = new DialogueNode("", "", true) {

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
			return EnforcerWarehouse.ENCLOSURE_TELEPORT_PADS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode CLAIRE_WAREHOUSE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_WAREHOUSE"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AFTER_CLAIRE_SEX = new DialogueNode("结束", "克莱尔和你交缠的身体分开……", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "AFTER_CLAIRE_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	private static boolean vendingMachineInspected = false;
	private static boolean vendingMachineTalked = false;
	
	public static final DialogueNode VENDING_MACHINE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			vendingMachineInspected = false;
			vendingMachineTalked = false;
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "VENDING_MACHINE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseTrade("交易",
						"看看自动售货机今天卖什么。",
						Main.game.getNpc(HazmatRat.class)) {
					@Override
					public void effects() {
						((HazmatRat)Main.game.getNpc(HazmatRat.class)).applyRestock();
					}
				};
				
			} else if(index==2) {
				return new Response("审视",
						vendingMachineInspected
							?"你已经近距离看过自动售货机了…… "
							:"近距离看看自动售货机。",
						vendingMachineInspected
							?null
							:VENDING_MACHINE_MISC) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "VENDING_MACHINE_INSPECT"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vendingMachineTalked, true);
						vendingMachineInspected = true;
					}
				};
				
			} else if(index==3 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vendingMachineTalked)) {
				return new Response("交谈",
						vendingMachineTalked
							?"你已经尝试过和自动售货机说话了……"
							:"你确信里面一定有人，于是决定尝试与自动售货机说话。",
							vendingMachineTalked
								?null
								:VENDING_MACHINE_MISC) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "VENDING_MACHINE_TALK"));
						vendingMachineTalked = true;
					}
				};
				
			} else if(index==0) {
				return new Response("返回",
						"离开自动售货机，去别处看看……",
						SEWER_ENTRANCE);
			}
			return null;
		}
	};

	public static final DialogueNode VENDING_MACHINE_MISC = new DialogueNode("", "", true, true) {
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
			return VENDING_MACHINE.getResponse(responseTab, index);
		}
	};
}
