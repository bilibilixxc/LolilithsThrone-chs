package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderNames;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.dominion.SMDaddyDinnerOral;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.3.10
 * @version 0.3.3.10
 * @author Innoxia
 */
public class DaddyDialogue {

	private static boolean isLilayaPresent() {
		return Main.game.getCharactersPresent().contains(Main.game.getNpc(Lilaya.class));
	}
	
	private static String getDialoguePrefix() {
		if(isLilayaPresent()) {
			return "LILAYA_";
		}
		return "";
	}
	
	private static void completeQuest() {
		Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.SIDE_UTIL_COMPLETE));
	}
	
	private static void acceptAsDaddy() {
		Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 15));
		Main.game.getPlayer().setPetName(Main.game.getNpc(Daddy.class), "daddy");
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.daddySendingReward, true);
		Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_ACCEPTED));
	}
	
	// Util place dialogues:
	
	public static final DialogueNode PLACE_ENTRANCE_HALL = new DialogueNode("门厅", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PLACE_LOUNGE = new DialogueNode("休息室", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PLACE_KITCHEN = new DialogueNode("厨房", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PLACE_BEDROOM = new DialogueNode("卧室", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode BLANK_DEFAULT_DIALOGUE = new DialogueNode("", "", false) {
		@Override
		public String getLabel() {
			return Main.game.getDefaultDialogue(false).getLabel();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CONVINCING_LILAYA = new DialogueNode("卧室", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/daddy", "CONVINCING_LILAYA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待", "等待莉莱雅换好衣服，然后出发前往恶魔之家的[daddy.namePos]处。", MEETING) {
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
					@Override
					public void effects() {
						((Lilaya) Main.game.getNpc(Lilaya.class)).applyDinnerDateChange();
						
						Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_ENTRANCE);
						Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						
						Main.game.getNpc(Lilaya.class).setPetName(Main.game.getNpc(Daddy.class), "daddy");
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_LILAYA_MEETING));

						if(Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getPlayer().isCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class))) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(500));
						}
					}
				};
			}
			return null;
		}
	};
	
	// Main dialogues:
	
	public static final DialogueNode FIRST_ENCOUNTER = new DialogueNode("不受欢迎的客人", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_ENCOUNTER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("纠正他", "告诉[daddy.name]，你和莉莱雅是亲戚，你和她住在这里。", BLANK_DEFAULT_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getNpc(Daddy.class).setPlayerKnowsName(true);
						
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_ENCOUNTER_ANSWER"));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_DADDY));
						
						((Daddy)Main.game.getNpc(Daddy.class)).sendToNewHome();
						
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
					}
				};
				
			} else if(index==2) {
				return new Response("无视他", "拒绝回答这个无礼的淫梦魔的问题，直接从他身边走过，进入莉莱雅的家。", BLANK_DEFAULT_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getNpc(Daddy.class).setPlayerKnowsName(true);
						
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_ENCOUNTER_PUSH_PAST"));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_DADDY));

						((Daddy)Main.game.getNpc(Daddy.class)).sendToNewHome();
						
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, true);
					}
				};
			}
			
			return null;
		}
	};
	
	
	public static final DialogueNode MEETING = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return "与[daddy.name]见面";
		}

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED) {
				return UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_RETURN_AFTER_REFUSED");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED_2) { //TODO if refused like this, make it so the only option is agree/refuse, then proceed with regular options.
				return UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_RETURN_AFTER_REFUSED_AT_DINNER");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_MEETING) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.rudeToDaddy)) {
					return UtilText.parseFromXMLFile("characters/dominion/daddy", "INITIAL_MEETING_RUDE");
				} else {
					return UtilText.parseFromXMLFile("characters/dominion/daddy", "INITIAL_MEETING");
				}
				
			} else if(isLilayaPresent() && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DADDY)) {
				return UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_INITIAL_MEETING");
				
			} else {
				return UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_MEETING
					|| Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED) { // First dinner:
				if(index==1) {
					return new Response("同意",
							"在决定跟[daddy.name]说什么前，不妨先了解一下[daddy.herHim]为什么这么想见莉莱雅。",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_AGREE"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 5));
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						}
					};
					
				} else if(index==2) {
					return new Response("同意(调情)",
							"与[daddy.name]约会听起来是度过夜晚的完美方式！",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_AGREE_FLIRT"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 10));
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						}
					};
					
				} else if(index==3) {
					boolean returning = Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED;
					return new Response(
							"拒绝",
							returning
								? "告诉[daddy.name]你改变主意了，不想和[daddy.herHim]一起出去吃饭了。<br/>"
									+ "[style.italicsMinorBad(你仍然可以改变主意并且明天或之后的任何时间回来。)]"
								: "拒绝和[daddy.name]共进晚餐，告诉他再也不要来打扰莉莱雅。<br/>"
									+ "[style.italicsMinorBad(这将完成任务，'"+QuestLine.SIDE_DADDY.getName()+"'但你仍然可以随时返回并重新开启。)]",
							BLANK_DEFAULT_DIALOGUE) {
						@Override
						public void effects() {
							if(!returning) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_REFUSE"));
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_REFUSED));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_REFUSE_AFTER_RETURN"));	
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
						}
					};
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED_2) {
				if(index==1) {
					return new Response("接受",
							"告诉[daddy.name]，你愿意喊[daddy.herHim][daddy.mommy]，并说服莉莱雅来见[daddy.herHim]。",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "ACCEPT_AFTER_RETURN"));

							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);

							acceptAsDaddy();
						}
					};
					
				} else if(index==2) {
					return new Response("接受(调情)",
							"调情地告诉[daddy.name]，你愿意叫[daddy.herHim][daddy.mommy]，并说服莉莱雅来见[daddy.herHim]。",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "ACCEPT_AFTER_RETURN_FLIRT"));

							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							
							acceptAsDaddy();
						}
					};
					
				} else if(index==3) {
					return new Response("拒绝",
								"告诉[daddy.name]，你不会说服莉莱雅去见[daddy.herHim]，当然也不会叫[daddy.herHim]“[daddy.daddy]”。<br/>"
										+ "<i>这将完成任务，'"+QuestLine.SIDE_DADDY.getName()+"'但你仍然可以随时返回并重新开启。</i>",
								BLANK_DEFAULT_DIALOGUE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "REFUSE_AFTER_RETURN"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
						}
					};
				}
				
			} else if(isLilayaPresent() && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DADDY)) {
				if(index==1) {
					return new Response("帮助莉莱雅",
							"帮助莉莱雅，让她接受[daddy.name]成为她父亲的想法。",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_INITIAL_MEETING_TO_DINNER"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 20));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getNpc(Lilaya.class), 25));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getNpc(Daddy.class), 25));

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							if(Main.game.getPlayer().isVisiblyPregnant()) {
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class), true);
							}
						}
					};
					
				} else if(index==2) {
					return new Response("帮助莉莱雅(调情)",
							"帮助莉莱雅，提醒她接受让[daddy.name]成为她父亲的想法，然后再进一步，开始与他们两人调情，为今晚定下基调。",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_INITIAL_MEETING_TO_DINNER_FLIRT"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 30));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getNpc(Lilaya.class), 25));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getNpc(Daddy.class), 25));
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							if(Main.game.getPlayer().isVisiblyPregnant()) {
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class), true);
							}
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("晚餐",
							isLilayaPresent()
								?"告诉[daddy.name]，你和莉莱雅很想再和[daddy.herHim]一起出去吃饭。"
								:"告诉[daddy.name]你很想再和[daddy.herHim]一起出去吃饭。",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING_OUT_FOR_DINNER"));
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());
							
							if(Main.game.getPlayer().isVisiblyPregnant()) {
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class), true);
							}
							if(isLilayaPresent() && Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
								Main.game.getNpc(Lilaya.class).setCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class), true);
							}
						}
					};
					
				} else if(index==2) {
					return new Response("晚餐(调情)",
							isLilayaPresent()
								?"用调情的方式告诉[daddy.name]，你和莉莱雅很想再和[daddy.herHim]一起出去吃饭。"
								:"用调情的方式告诉[daddy.name]，你很想再和[daddy.herHim]一起出去吃饭。",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING_OUT_FOR_DINNER_FLIRTING"));
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							if(Main.game.getPlayer().isVisiblyPregnant()) {
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class), true);
							}
							if(isLilayaPresent() && Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
								Main.game.getNpc(Lilaya.class).setCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class), true);
							}
						}
					};
					
				} else if(index==3) {
					return new Response("进入",
							isLilayaPresent()
								?"问问[daddy.name]，你们三个能不能进去，在[daddy.her]的公寓里待一会儿。"
								:"问问[daddy.name]，你们俩能不能进去，在[daddy.her]的公寓里待一会儿。",
							APARTMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING_STAY_IN_APARTMENT"));
							
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							}
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.talkedWithDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());
						}
					};
					
				} else if(index==4) {
					return new Response("进入(调情)",
							isLilayaPresent()
								?"用调情的方式问[daddy.name]，你们三个人是否可以进去，在[daddy.her]的公寓里共度一段时光。"
								:"用调情的方式问[daddy.name]，你们俩能不能进去，在[daddy.her]的公寓里共度一段时光。",
							APARTMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING_STAY_IN_APARTMENT_FLIRTING"));
							
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							}
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.talkedWithDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());
						}
					};
					
				}
			}
			
			return null;
		}
	};
	
	private static void applyCompanionDrinks(boolean includePlayer) {
		if(includePlayer) {
			Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.1f));
		}
		Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAlcoholLevel(0.1f));
		if(isLilayaPresent()) {
			Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAlcoholLevel(0.1f));
		}
	}
	
	public static final DialogueNode DINNER = new DialogueNode("引诱", "", true) {

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
			DialogueNode nextNode = DINNER_MID;
			boolean firstDinner = Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_MEETING || Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED;
			if(firstDinner) {
				nextNode = FIRST_DINNER_TRANSFORM;
			}
			if(index==1) {
				return new Response("水",
						"拒绝[daddy.namePos]的酒，坚持喝水以免喝醉。",
						nextNode) {
					@Override
					public void effects() {
						if(firstDinner) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_WATER"));
							applyCompanionDrinks(false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_CORE"));
							
						} else {
							if(isLilayaPresent() && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DADDY)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_DINNER_WATER_INITIAL"));
								completeQuest();
								applyCompanionDrinks(false);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_DINNER_CORE_INITIAL"));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_WATER"));
								applyCompanionDrinks(false);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_CORE"));
							}
						}
					}
				};
				
			} else if(index==2) {
				return new Response("酒",
						"接受[daddy.namePos]的提议，今晚喝一瓶酒。",
						nextNode) {
					@Override
					public void effects() {
						if(firstDinner) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_WINE"));
							applyCompanionDrinks(true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_CORE"));
							
						} else {
							if(isLilayaPresent() && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DADDY)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_DINNER_WINE_INITIAL"));
								completeQuest();
								applyCompanionDrinks(true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_DINNER_CORE_INITIAL"));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_WINE"));
								applyCompanionDrinks(true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_CORE"));
							}
						}
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode FIRST_DINNER_TRANSFORM = new DialogueNode("引诱", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("淫梦魔",
						"告诉[daddy.name][daddy.sheIs]现在的样子就挺好。<br/>"
								+ "[style.italicsMasculine(这会使得[daddy.name]<b>永久</b>保持一个"+GenderNames.Y_PENIS_N_VAGINA_N_BREASTS.getMasculine()+"淫梦魔的状态。)]",
						FIRST_DINNER) {
					
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_NO_TF"));
					}
					@Override
					public Colour getHighlightColour() {
						return PresetColour.MASCULINE;
					}
				};
				
			} else if(index==2) {
				return new Response("魅魔",
						"劝说[daddy.name]，如果[daddy.she]想勾引莉西丝，转化成一个魅魔会简单得多。<br/>"
								+ "[style.italicsFeminine(这会使得[daddy.name]<b>永久</b>转化为一个"+GenderNames.Y_PENIS_N_VAGINA_Y_BREASTS.getFeminine()+"魅魔。)]",
						FIRST_DINNER) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_SUCCUBUS_TF"));
						Main.game.getNpc(Daddy.class).returnToHome();
						Main.game.getNpc(Daddy.class).setGenderIdentity(Gender.F_P_B_SHEMALE);
						Main.game.getNpc(Daddy.class).setStartingBody(false);
						Main.game.getNpc(Daddy.class).equipClothing();
						Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_SUCCUBUS_TF_END"));
					}
					@Override
					public Colour getHighlightColour() {
						return PresetColour.FEMININE;
					}
				};
				
			} else if(index==3 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.flirtingWithDaddy)) {
				return new Response("魅魔(调情)",
						"说服[daddy.name]，如果[daddy.she]转化成魅魔，[daddy.she]会更容易勾引莉西丝。让他转化后，开始和她调情。<br/>"
								+ "[style.italicsFeminine(这会使得[daddy.name]<b>永久</b>转化为一个"+GenderNames.Y_PENIS_N_VAGINA_Y_BREASTS.getFeminine()+"魅魔。)]",
						FIRST_DINNER) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_SUCCUBUS_TF"));
						Main.game.getNpc(Daddy.class).returnToHome();
						Main.game.getNpc(Daddy.class).setGenderIdentity(Gender.F_P_B_SHEMALE);
						Main.game.getNpc(Daddy.class).setStartingBody(false);
						Main.game.getNpc(Daddy.class).equipClothing();
						Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_SUCCUBUS_TF_END"));
					}
					@Override
					public Colour getHighlightColour() {
						return PresetColour.FEMININE;
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode FIRST_DINNER = new DialogueNode("引诱", "", true, true) {

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
			if(index==1) {
				return new Response("接受",
						"告诉[daddy.name]，你愿意喊[daddy.herHim][daddy.mommy]，并说服莉莱雅来见[daddy.herHim]。",
						DINNER_MID) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_ACCEPT"));

						acceptAsDaddy();
					}
				};
				
			} else if(index==2) {
				return new Response("拒绝",
							"告诉[daddy.name]，你不会说服莉莱雅去见[daddy.herHim]，当然也不会叫[daddy.herHim]“[daddy.daddy]”。<br/>"
									+ "<i>这将完成任务，'"+QuestLine.SIDE_DADDY.getName()+"'但你仍然可以随时返回并重新开启。</i>",
							BLANK_DEFAULT_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_REFUSE"));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_REFUSED_2));
					}
				};
			}
			
			return null;
		}
	};
	

	public static final DialogueNode DINNER_MID = new DialogueNode("引诱", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 45*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("甜点",
						isLilayaPresent()
							?"告诉[daddy.name]你和莉莱雅想点一些甜点。"
							:"告诉[daddy.name]你想点甜点。",
						DINNER_END) {
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_DESSERT"));
					}
				};
				
			} else if(index==2) {
				return new Response("无甜点",
						"告诉[daddy.name]你已经吃饱了，吃不下甜点了。",
						DINNER_END) {
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_NO_DESSERT"));
					}
				};
				
			} else if(index==3) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.flirtingWithDaddy)) {
					return new Response("“甜点”",
							isLilayaPresent()
								?"由于你今晚没有和[daddy.name]或莉莱雅调情，所以任何想从[daddy.name]那里得到特别“甜点”的尝试几乎肯定会以灾难性的尴尬告终。"
								:"由于你今晚还没有和[daddy.herHim]调情让[daddy.him]进入状态，任何想从[daddy.name]那里得到特别“甜点”的尝试几乎肯定会以灾难性的尴尬告终。",
							null);
				
				} else if(Main.game.getPlayer().isTaur()) {
					return new Response("“甜点”",
							"由于你的下半身太大，你根本不可能钻到桌子底下为[daddy.name]口交……",
							null);
					
				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("“甜点”",
							"你的嘴被堵住了，你不能为[daddy.name]口交！",
							null);
					
				} else {
					return new ResponseSex("“甜点”",
							isLilayaPresent()
								?"你想吃的甜点可不一样。让莉莱雅和你一起趴到桌子下面，和你一起给[daddy.namePos]口交来品尝“甜点”。"
								:"你的甜点与众不同。趴到桌子下面，开始为[daddy.namePos]口交。",
							true,
							true,
							new SMDaddyDinnerOral(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Daddy.class), SexSlotSitting.SITTING)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL),
											isLilayaPresent()
												?new Value<>(Main.game.getNpc(Lilaya.class), SexSlotSitting.PERFORMING_ORAL_TWO)
												:null)),
							null,
							null,
							AFTER_UNDER_TABLE_SEX,
							UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_DADDYS_DESSERT")) {
								@Override
								public int getSecondsPassed() {
									return 2*60;
								}
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getNpc(Daddy.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true),
											isLilayaPresent()
												?new InitialSexActionInformation(Main.game.getNpc(Daddy.class), Main.game.getNpc(Lilaya.class), PenisMouth.BLOWJOB_START_ADDITIONAL, true, true)
												:null);
								}
							};
					
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_UNDER_TABLE_SEX = new DialogueNode("侍者接近", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_UNDER_TABLE_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DINNER_END.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DINNER_END = new DialogueNode("引诱", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().applyFoodConsumed();
			Main.game.getPlayer().applyDrinkConsumed();
			Main.game.getNpc(Daddy.class).applyFoodConsumed();
			Main.game.getNpc(Daddy.class).applyDrinkConsumed();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.flirtingWithDaddy)) {
				if(index==1) {
					return new Response("拒绝",
							isLilayaPresent()
								?"告诉[daddy.name]，你和莉莱雅该回家了。"
								:"告诉[daddy.name]你得回家了。",
							BLANK_DEFAULT_DIALOGUE) {
						@Override
						public int getSecondsPassed() {
							if(isLilayaPresent()) {
								return 30*60;
							}
							return 2*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_END_REFUSE"));
							if(isLilayaPresent()) {
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
								((Lilaya)Main.game.getNpc(Lilaya.class)).equipClothing();
							} else {
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							Main.game.getNpc(Daddy.class).returnToHome();
						}
					};
					
				} else if(index==2) {
					return new Response("接受",
							isLilayaPresent()
								?"告诉[daddy.name]，你和莉莱雅很想回[daddy.her]那里再玩玩……"
								:"告诉[daddy.name]，你很想回到[daddy.her]的地方再找点乐子……",
							AFTER_DINNER) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_END_ACCEPT"));
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							}
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("道别",
							isLilayaPresent()
								?"感谢[daddy.name]的款待，并告诉[daddy.herHim]你们下次再见。"
								:"感谢[daddy.name]的款待，并告诉[daddy.herHim]你和莉莱雅下次再来看[daddy.herHim]。",
							BLANK_DEFAULT_DIALOGUE) {
						@Override
						public int getSecondsPassed() {
							if(isLilayaPresent()) {
								return 30*60;
							}
							return 2*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_END_HOME"));
							if(isLilayaPresent()) {
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
								((Lilaya)Main.game.getNpc(Lilaya.class)).equipClothing();
							} else {
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							Main.game.getNpc(Daddy.class).returnToHome();
						}
					};
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_DINNER = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getLabel() {
			return "[daddy.NamePos]公寓";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("顺从",
						isLilayaPresent()
							?"照我说的做，先到[daddy.namePos]床上和莉莱雅会合，然后再去见[daddy.herHim]。"
							:"按照[daddy.name]说的做，先上[daddy.her]的床，然后再向[daddy.herHim]展示自己。",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getNpc(Daddy.class)),
								Util.newArrayListOfValues(
										Main.game.getPlayer(),
										isLilayaPresent()
											?Main.game.getNpc(Lilaya.class)
											:null),
								null,
								null,
								ResponseTag.PREFER_DOGGY) {
							@Override
							public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.equals(Main.game.getNpc(Daddy.class));
							}
							@Override
							public Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> exposeAtStartOfSexMapExtendedInformation() {
								Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> map = new HashMap<>();
								map.put(true, new HashMap<>());
								if(isLilayaPresent()) {
									map.get(true).put(Main.game.getNpc(Lilaya.class),
											Util.newHashMapOfValues(
													new Value<>(CoverableArea.VAGINA, Util.newArrayListOfValues(InventorySlot.GROIN)),
													new Value<>(CoverableArea.FEET, Util.newArrayListOfValues(InventorySlot.SOCK)),
													new Value<>(CoverableArea.STOMACH, null)));
								}
								map.get(true).put(Main.game.getPlayer(),
										Util.newHashMapOfValues(
												new Value<>(CoverableArea.VAGINA, Util.newArrayListOfValues(InventorySlot.GROIN)),
												new Value<>(CoverableArea.FEET, Util.newArrayListOfValues(InventorySlot.SOCK)),
												new Value<>(CoverableArea.STOMACH, null)));
								return map;
							}
						},
						null,
						null,
						AFTER_APARTMENT_SEX,
						UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_DINNER_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								isLilayaPresent()
									?new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getPlayer(), TongueMouth.KISS_START, true, true)
									:null);
					}
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						if(isLilayaPresent()) {
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						}
						Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("支配",
						isLilayaPresent()
							?"告诉[daddy.name]，事情不是这样的，然后把[daddy.herHim]推进卧室，和莉莱雅一起骑在[daddy.herHim]身上。"
							:"告诉[daddy.name]，事情不是这样的，然后把[daddy.herHim]推进卧室，准备骑在[daddy.herHim]身上。",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer(),
										isLilayaPresent()
											?Main.game.getNpc(Lilaya.class)
											:null),
								Util.newArrayListOfValues(
										Main.game.getNpc(Daddy.class)),
								null,
								null,
								ResponseTag.PREFER_COW_GIRL) {
							@Override
							public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return Main.sex.getAllParticipants().size()==2;
							}
							@Override
							public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
								return character.isPlayer() && target.equals(Main.game.getNpc(Lilaya.class));
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.equals(Main.game.getNpc(Daddy.class));
							}
							@Override
							public Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> exposeAtStartOfSexMapExtendedInformation() {
								Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> map = new HashMap<>();
								map.put(true, new HashMap<>());
								if(isLilayaPresent()) {
									map.get(true).put(Main.game.getNpc(Lilaya.class),
											Util.newHashMapOfValues(
													new Value<>(CoverableArea.VAGINA, Util.newArrayListOfValues(InventorySlot.GROIN)),
													new Value<>(CoverableArea.FEET, Util.newArrayListOfValues(InventorySlot.SOCK)),
													new Value<>(CoverableArea.STOMACH, null),
													new Value<>(CoverableArea.VAGINA, null)));
								}
								map.get(true).put(Main.game.getPlayer(),
										Util.newHashMapOfValues(
												new Value<>(CoverableArea.VAGINA, Util.newArrayListOfValues(InventorySlot.GROIN)),
												new Value<>(CoverableArea.FEET, Util.newArrayListOfValues(InventorySlot.SOCK)),
												new Value<>(CoverableArea.STOMACH, null)));
								return map;
							}
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(!Main.sex.isDom(character)) {
									return SexControl.ONGOING_ONLY;
								}
								return super.getSexControl(character);
							}
						},
						null,
						null,
						AFTER_APARTMENT_SEX,
						UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_DINNER_DOMINATE")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								isLilayaPresent()
									?new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getNpc(Daddy.class), TongueVagina.RECEIVING_CUNNILINGUS_START, true, true)
									:null);
					}
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						if(isLilayaPresent()) {
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						}
						Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_APARTMENT_SEX = new DialogueNode("结束", "淫荡的滥交夜晚来到了尽头……", true, true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_APARTMENT_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("道别",
						isLilayaPresent()
							?"感谢[daddy.name]今晚给你们带来的欢乐，并告诉[daddy.herHim]，你和莉莱雅下次再来看[daddy.herHim]。"
							:"感谢[daddy.name]今晚给你们带来的所有乐趣，并告诉[daddy.herHim]，你们下次再来看[daddy.herHim]。",
						BLANK_DEFAULT_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_APARTMENT_SEX_HOME"));
						if(isLilayaPresent()) {
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
							((Lilaya)Main.game.getNpc(Lilaya.class)).equipClothing();
						} else {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
						}
						Main.game.getNpc(Daddy.class).returnToHome();
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode APARTMENT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return "[daddy.NamePos]公寓";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.flirtingWithDaddy)) {
				if(index==1 || index==2) {
					return AFTER_DINNER.getResponse(responseTab, index);
				}
				
			} else {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.talkedWithDaddy)) {
						return new Response("交谈",
								"你已经花了一些时间和[daddy.name]交谈了。",
								null);
					}
					return new Response("交谈",
							"花一些时间和[daddy.name]简单谈谈[daddy.her]的工作和为见到莉西丝所做的努力。",
							APARTMENT) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"APARTMENT_TALK"));
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.talkedWithDaddy, true);
						}
					};
					
				} else if(index==2) {
					return new Response("诱惑",
							isLilayaPresent()
								?"引诱[daddy.name]和莉莱雅，开始和他们在沙发上亲热，然后继续和他们做爱……"
								:"勾引[daddy.name]，开始和[daddy.herHim]在沙发上亲热，然后继续和[daddy.herHim]做爱……",
							APARTMENT) {
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"APARTMENT_SEDUCE"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
						}
					};
					
				} else if(index==3) {
					return new Response("离开",
							"在起身告辞之前，告诉[daddy.name]很高兴再次见到[daddy.herHim]。",
							BLANK_DEFAULT_DIALOGUE) {
						@Override
						public int getSecondsPassed() {
							if(isLilayaPresent()) {
								return 30*60;
							}
							return 2*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"APARTMENT_LEAVE"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.talkedWithDaddy, false);
							if(isLilayaPresent()) {
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
								((Lilaya)Main.game.getNpc(Lilaya.class)).equipClothing();
							} else {
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
						}
					};
				}
			}
			
			return null;
		}
	};
	
}
