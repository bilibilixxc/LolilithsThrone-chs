package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.dominion.Elle;
import com.lilithsthrone.game.character.npc.dominion.Wes;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.EnforcerHQDialogue;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.9.4
 * @version 0.3.9.5
 * @author DSG (concept and characters), Innoxia
 */
public class WesQuest {

	public static final String QUEST_COMPLETION_MINUTES_TIMER_ID = "wes_completion_timer";
	
	private static List<String> backgroundTalkIds = new ArrayList<>();
	private static List<String> workTalkIds = new ArrayList<>();
	
	static {
		resetBackgroundIds();
		resetWorkTalkIds();
	}
	
	private static void resetBackgroundIds() {
		backgroundTalkIds = Util.newArrayListOfValues("1", "2", "3", "4", "5");
	}

	private static void resetWorkTalkIds() {
		workTalkIds = Util.newArrayListOfValues("1", "2", "3", "4", "5");
	}
	
	public static final DialogueNode WES_QUEST_START = new DialogueNode("突如其来的打断", "", true) {
		@Override
		public void applyPreParsingEffects() {
			((Wes)Main.game.getNpc(Wes.class)).applyDisguise();
			Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("同意", "同意跟这个神秘的执法者在购物中心的古董店外见面。", WES_QUEST_START_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_AGREE"));
					}
				};
				
			} else if(index==2) {
				return new Response("疑问", "问这个神秘的执法者为什么想见你。", WES_QUEST_START_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_QUESTION"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode WES_QUEST_START_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).returnToHome();
			Main.game.getNpc(Wes.class).equipClothing();
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_WES));
		}
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
			if(index==1) {
				return new Response("继续", "继续你在御城区的旅程……", Main.game.getPlayer().getLocationPlace().getPlaceType().getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode WES_QUEST_SHOPPING_ARCADE_MEETING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			((Wes)Main.game.getNpc(Wes.class)).applyDisguise();
			Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Wes.class).setPlayerKnowsName(true);
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_SHOPPING_ARCADE_MEETING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestRefused)) {
				return WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION.getResponse(responseTab, index);
				
			} else {
				if(index==1) {
					return new Response("疑问", "问韦斯他想让你做什么，有没有更多细节。", WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION);
				}
			}
			return null;
		}
	};

	public static final DialogueNode WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("同意", "同意韦斯的请求，收集他长官贪污腐败的证据……", WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION_END);
				
			} else if(index==2) {
				return new Response("拒绝",
						"告诉韦斯你不想牵扯进这种事情里。"
								+ "<br/>[style.italicsMinorGood(之后如果改变了主意，还可以找到韦斯帮助他。)]",
								WES_QUEST_SHOPPING_ARCADE_MEETING_FAIL);
			}
			return null;
		}
	};

	public static final DialogueNode WES_QUEST_SHOPPING_ARCADE_MEETING_FAIL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).returnToHome();
			Main.game.getNpc(Wes.class).equipClothing();
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestRefused)) {
				Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).setAffection(Main.game.getPlayer(), -10));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_SHOPPING_ARCADE_MEETING_FAIL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续你在购物中心的旅程……", Main.game.getPlayer().getLocationPlace().getPlaceType().getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestMet, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestRefused, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).returnToHome();
			Main.game.getNpc(Wes.class).equipClothing();
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.WES_1));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).incrementAffection(Main.game.getPlayer(), 10));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_quest_recorder"), false));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_QUEST_SHOPPING_ARCADE_MEETING_QUESTION_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续你在购物中心的旅程……", Main.game.getPlayer().getLocationPlace().getPlaceType().getDialogue(false));
			}
			return null;
		}
	};
	

	public static final DialogueNode ELLE_SEARCH = new DialogueNode("寻找埃勒", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Vector2i playerLoc = Main.game.getPlayer().getLocation();
			Main.game.getPlayer().setRandomLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_LIGHT, false);
			List<Cell> cells = Pathing.aStarPathing(Main.game.getWorlds().get(WorldType.BAT_CAVERNS).getCellGrid(), playerLoc, Main.game.getPlayer().getLocation(), true);
			for(Cell c : cells) {
				c.setDiscovered(true);
				c.setTravelledTo(true);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "ELLE_SEARCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进入洞窟", "进入洞窟并且环顾四周……", ELLE_SEARCH_CAVE);
			}
			return null;
		}
	};
	
	public static final DialogueNode ELLE_SEARCH_CAVE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "ELLE_SEARCH_CAVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("开始录像", "藏在板条箱后，录下埃勒的交易现场……", ELLE_SEARCH_CAVE_RECORDING);
			}
			return null;
		}
	};
	
	public static final DialogueNode ELLE_SEARCH_CAVE_RECORDING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Elle.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "ELLE_SEARCH_CAVE_RECORDING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "离开你的藏身处，进入蝙蝠洞窟……", ELLE_SEARCH_CAVE_LEAVE);
			}
			return null;
		}
	};
	
	public static final DialogueNode ELLE_SEARCH_CAVE_LEAVE = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.WES_2));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "ELLE_SEARCH_CAVE_LEAVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getPlayer().getLocationPlace().getPlaceType().getDialogue(false).getResponse(responseTab, index);
		}
	};

	public static final DialogueNode CLAIRE_ELLE_EVIDENCE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.WES_3_WES));
			Main.game.getPlayer().removeItemByType(ItemType.getItemTypeFromId("innoxia_quest_recorder"));
			Main.game.getDialogueFlags().setSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID, Main.game.getMinutesPassed());
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "CLAIRE_ELLE_EVIDENCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SubmissionGenericPlaces.CLAIRE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CANDI_ELLE_EVIDENCE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.WES_3_WES));
			Main.game.getPlayer().removeItemByType(ItemType.getItemTypeFromId("innoxia_quest_recorder"));
			Main.game.getDialogueFlags().setSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID, Main.game.getMinutesPassed());
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "CANDI_ELLE_EVIDENCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getPlayer().getLocationPlace().getPlaceType().getDialogue(false).getResponse(responseTab, index);
		}
	};

	public static final DialogueNode APPROACH_ELLE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 45*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "APPROACH_ELLE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("解释", "告诉埃勒韦斯让你做的一切，并且展示奥术录像机中的证据。", APPROACH_ELLE_EXPLAIN);
			}
			return null;
		}
	};

	public static final DialogueNode APPROACH_ELLE_EXPLAIN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.WES_3_ELLE));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Elle.class).incrementAffection(Main.game.getPlayer(), 25));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).setAffection(Main.game.getPlayer(), -100));
			Main.game.getPlayer().removeItemByType(ItemType.getItemTypeFromId("innoxia_quest_recorder"));
			Main.game.getNpc(Elle.class).returnToHome();
			Main.game.getDialogueFlags().setSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID, Main.game.getMinutesPassed());
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "APPROACH_ELLE_EXPLAIN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续你的旅程……", Main.game.getPlayer().getLocationPlace().getPlaceType().getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode INTRO_HQ_WES = new DialogueNode("会见韦斯", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_WES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待", "等韦斯现身。", INTRO_HQ_WES_ARRIVE);
			}
			return null;
		}
	};

	public static final DialogueNode INTRO_HQ_WES_ARRIVE = new DialogueNode("会见韦斯", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).addSlave(Main.game.getNpc(Elle.class));
			Main.game.getNpc(Elle.class).equipClothing();
			Main.game.getNpc(Wes.class).equipClothing();
			Main.game.getNpc(Wes.class).dailyUpdate(); // To stock items for sale
			Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Elle.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_WES_ARRIVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("跟随", "跟着韦斯进入执法者总部。", INTRO_HQ_WES_OFFICE);
			}
			return null;
		}
	};
	
	public static final DialogueNode INTRO_HQ_WES_OFFICE = new DialogueNode("会见韦斯", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Vector2i playerLoc = Main.game.getPlayer().getLocation();
			
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER);
			Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
			
			List<Cell> cells = Pathing.aStarPathing(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCellGrid(), playerLoc, Main.game.getPlayer().getLocation(), true);
			for(Cell c : cells) {
				c.setDiscovered(true);
				c.setTravelledTo(true);
			}
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.SIDE_UTIL_COMPLETE));
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_WES_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("军需处", "前往军需处，看看韦斯能给你提供什么。", INTRO_HQ_WES_REQUISITIONS);
			}
			return null;
		}
	};

	public static final DialogueNode INTRO_HQ_WES_REQUISITIONS = new DialogueNode("会见韦斯", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, false);
			Main.game.getNpc(Wes.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_quest_special_pass"), false));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_WES_REQUISITIONS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REQUISITIONS_INTERACTION.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode INTRO_HQ_ELLE = new DialogueNode("会见埃勒", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_ELLE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待", "等待埃勒现身。", INTRO_HQ_ELLE_ARRIVE);
			}
			return null;
		}
	};

	public static final DialogueNode INTRO_HQ_ELLE_ARRIVE = new DialogueNode("会见埃勒", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Elle.class).addSlave(Main.game.getNpc(Wes.class));
			Main.game.getNpc(Wes.class).equipClothing();
			Main.game.getNpc(Elle.class).equipClothing();
			Main.game.getNpc(Elle.class).dailyUpdate(); // To stock items for sale
			Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Wes.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);

			Main.game.getNpc(Wes.class).addFetish(Fetish.FETISH_ORAL_RECEIVING);
			Main.game.getNpc(Wes.class).addFetish(Fetish.FETISH_VAGINAL_GIVING);
			Main.game.getNpc(Wes.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ONE_DISLIKE);
			Main.game.getNpc(Wes.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.ONE_DISLIKE);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_ELLE_ARRIVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("跟随", "跟随埃勒进入执法者总部。", INTRO_HQ_ELLE_OFFICE);
			}
			return null;
		}
	};

	public static final DialogueNode INTRO_HQ_ELLE_OFFICE = new DialogueNode("会见埃勒", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Vector2i playerLoc = Main.game.getPlayer().getLocation();

			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER);
			Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
			
			List<Cell> cells = Pathing.aStarPathing(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCellGrid(), playerLoc, Main.game.getPlayer().getLocation(), true);
			for(Cell c : cells) {
				c.setDiscovered(true);
				c.setTravelledTo(true);
			}
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_WES, Quest.SIDE_UTIL_COMPLETE));
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_ELLE_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("军需处", "前往军需处，看看埃勒能给你提供什么。", INTRO_HQ_ELLE_REQUISITIONS);
			}
			return null;
		}
	};

	public static final DialogueNode INTRO_HQ_ELLE_REQUISITIONS = new DialogueNode("会见埃勒", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, false);
			Main.game.getNpc(Elle.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_quest_special_pass_elle"), false));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "INTRO_HQ_ELLE_REQUISITIONS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REQUISITIONS_INTERACTION.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode REQUISITIONS_INTERACTION = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_INTERACTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			// Commented out in 0.4.1.5
			// No idea why this was here, seeing as every time REQUISITIONS_INTERACTION is accessed, travel is disabled, so there always needs to be an escape response...
//			if(!Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Elle.class))) {
//				return null;
//			}
			
			if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_WES, Quest.WES_3_ELLE)) {
				if(index==0) {
					return new Response("离开", "告诉埃勒你现在必须走了，然后回到走廊。", PlaceType.ENFORCER_HQ_CELLS_CORRIDOR.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_INTERACTION_LEAVE"));
							Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_CORRIDOR, false);
						}
					};
					
				} else if(index==1) {
					return new ResponseTrade("交易", "从埃勒处买一些装备。", Main.game.getNpc(Elle.class));
					
				} else if(index==2) {
					if(Main.game.getCurrentDialogueNode()==REQUISITIONS_BACKGROUND) {
						return new Response("背景", "你已经问过埃勒她自己的事情了！", null);
					}
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestTalked)) {
						return new Response("背景", "你已经跟埃勒谈过她自己了……<br/>[style.italicsMinorBad(每天只能一次。)]", null);
					}
					return new Response("背景", "问问埃勒她自己的事情。<br/>[style.italicsMinorGood(每天可以问一次。)]", REQUISITIONS_BACKGROUND) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestTalked, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Elle.class).incrementAffection(Main.game.getPlayer(), 10));
						}
					};
					
				} else if(index==3) {
					if(Main.game.getCurrentDialogueNode()==REQUISITIONS_WORK) {
						return new Response("工作", "你已经问过埃勒最近工作如何了！", null);
					}
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestTalkedAlt)) {
						return new Response("工作", "你已经问过埃勒最近工作如何了……<br/>[style.italicsMinorBad(每天只能一次。)]", null);
					}
					return new Response("工作", "问问埃勒最近工作如何。<br/>[style.italicsMinorGood(每天可以问一次。)]", REQUISITIONS_WORK) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestTalkedAlt, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Elle.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				} else if(index==4) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestFlirted)) {
						return new Response("调情", "你今天已经与埃勒调情过了……<br/>[style.italicsMinorBad(一天只能调情一次。)]", null);
					}
					return new Response("调情", "与埃勒调情。<br/>[style.italicsMinorGood(你一天只能与埃勒调情一次。)]", REQUISITIONS_FLIRT_ELLE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestFlirted, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Elle.class).incrementAffection(Main.game.getPlayer(), 10));
						}
					};
				}
				
			} else {
				if(index==0) {
					return new Response("离开", "告诉韦斯你现在必须走了，然后回到走廊。", PlaceType.ENFORCER_HQ_CELLS_CORRIDOR.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_INTERACTION_LEAVE"));
							Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_CORRIDOR, false);
							if(Main.game.getPlayer().isVisiblyPregnant()) {
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Wes.class), true);
							}
						}
					};
					
				} else if(index==1) {
					return new ResponseTrade("交易", "从韦斯处买一些装备。", Main.game.getNpc(Wes.class));
					
				} else if(index==2) {
					if(Main.game.getCurrentDialogueNode()==REQUISITIONS_BACKGROUND) {
						return new Response("背景", "你已经问过韦斯他自己的事情了！", null);
					}
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestTalked)) {
						return new Response("背景", "你已经跟韦斯谈过他自己了……<br/>[style.italicsMinorBad(每天只能一次。)]", null);
					}
					return new Response("背景", "问问韦斯他自己的事情。<br/>[style.italicsMinorGood(每天可以问一次。)]", REQUISITIONS_BACKGROUND) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestTalked, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).incrementAffection(Main.game.getPlayer(), 10));
							if(Main.game.getPlayer().isVisiblyPregnant()) {
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Wes.class), true);
							}
						}
					};
					
				} else if(index==3) {
					if(Main.game.getCurrentDialogueNode()==REQUISITIONS_WORK) {
						return new Response("工作", "你已经问过韦斯最近工作如何了！", null);
					}
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestTalkedAlt)) {
						return new Response("工作", "你已经问过韦斯最近工作如何了……<br/>[style.italicsMinorBad(每天只能一次。)]", null);
					}
					return new Response("工作", "问问韦斯最近工作如何。<br/>[style.italicsMinorGood(每天可以问一次。)]", REQUISITIONS_WORK) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestTalkedAlt, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).incrementAffection(Main.game.getPlayer(), 5));
							if(Main.game.getPlayer().isVisiblyPregnant()) {
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Wes.class), true);
							}
						}
					};
					
				} else if(index==4) {
					if(!Main.game.getNpc(Wes.class).isAttractedTo(Main.game.getPlayer())) {
						return new Response("调情", "韦斯没有被你吸引，你能想到与他调情不会有好结果……", null);
					}
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestFlirted)) {
						return new Response("调情", "你今天已经与韦斯调情过了……<br/>[style.italicsMinorBad(你一天只能调情一次。)]", null);
					}
					return new Response("调情", "与韦斯调情。<br/>[style.italicsMinorGood(你一天可以调情一次。)]", REQUISITIONS_FLIRT_WES) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestFlirted, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Wes.class).incrementAffection(Main.game.getPlayer(), 5));
							if(Main.game.getPlayer().isVisiblyPregnant()) {
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Wes.class), true);
							}
						}
					};
					
				} else if(index==5) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestSex)) {
						return new Response("埃勒", "你今天已经跟陪过埃勒一段时间了……<br/>[style.italicsMinorBad(每天只能一次。)]", null);
					}
					return new Response("埃勒", "询问韦斯你能不能陪埃勒一会儿。<br/>[style.italicsMinorGood(每天可以一次。)]", WES_ELLE_OFFICE) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestSex, true);
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER);
							Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
							if(Main.game.getPlayer().isVisiblyPregnant()) {
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Wes.class), true);
							}
						}
					};
				}
				
			}
			return null;
		}
	};
	
	public static final DialogueNode REQUISITIONS_BACKGROUND = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			String dialogueId;
			if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_WES, Quest.WES_3_ELLE)) {
				dialogueId = "REQUISITIONS_BACKGROUND_ELLE_";
				if(Main.game.getDialogueFlags().hasSavedLong("elle_background_progress")) {
					Main.game.getDialogueFlags().incrementSavedLong("elle_background_progress", 1);
				} else {
					Main.game.getDialogueFlags().setSavedLong("elle_background_progress", 1);
				}
				
				int progress = (int) Main.game.getDialogueFlags().getSavedLong("elle_background_progress");
				if(progress>5) { // If completed, random background:
					if(backgroundTalkIds.isEmpty()) {
						resetBackgroundIds();
					}
					String idAddition = Util.randomItemFrom(backgroundTalkIds);
					dialogueId = dialogueId + idAddition;
					backgroundTalkIds.remove(idAddition);
					
				} else {
					dialogueId += progress;
				}
				
			} else {
				dialogueId = "REQUISITIONS_BACKGROUND_WES_";
				if(Main.game.getDialogueFlags().hasSavedLong("wes_background_progress")) {
					Main.game.getDialogueFlags().incrementSavedLong("wes_background_progress", 1);
				} else {
					Main.game.getDialogueFlags().setSavedLong("wes_background_progress", 1);
				}
				int progress = (int) Main.game.getDialogueFlags().getSavedLong("wes_background_progress");
				if(progress==4) {
					Main.game.getNpc(Wes.class).addFetish(Fetish.FETISH_ORAL_RECEIVING);
					Main.game.getNpc(Wes.class).addFetish(Fetish.FETISH_VAGINAL_GIVING);
					Main.game.getNpc(Wes.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ONE_DISLIKE);
					Main.game.getNpc(Wes.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.ONE_DISLIKE);
				}
				if(progress>5) { // If completed, random background:
					if(backgroundTalkIds.isEmpty()) {
						resetBackgroundIds();
					}
					String idAddition = Util.randomItemFrom(backgroundTalkIds);
					dialogueId = dialogueId + idAddition;
					backgroundTalkIds.remove(idAddition);
					
				} else {
					dialogueId += progress;
				}
			}
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/wes", dialogueId));
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REQUISITIONS_INTERACTION.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode REQUISITIONS_WORK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			String dialogueId;
			if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_WES, Quest.WES_3_ELLE)) {
				dialogueId = "REQUISITIONS_WORK_ELLE_";
			} else {
				dialogueId = "REQUISITIONS_WORK_WES_";
			}
			if(workTalkIds.isEmpty()) {
				resetWorkTalkIds();
			}
			String idAddition = Util.randomItemFrom(workTalkIds);
			dialogueId = dialogueId + idAddition;
			workTalkIds.remove(idAddition);

			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_WORK"));
			sb.append(UtilText.parseFromXMLFile("characters/dominion/wes", dialogueId));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REQUISITIONS_INTERACTION.getResponse(responseTab, index);
		}
	};
	
	
	// Elle interactions
	
	
	public static final DialogueNode REQUISITIONS_FLIRT_ELLE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_ELLE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("参观办公室", "告诉埃勒你想去她办公室“参观”一下……", REQUISITIONS_FLIRT_ELLE_OFFICE) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER);
						Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
					}
				};
				
			} if(index==2) {
				return new Response("拒绝", "告诉埃勒你没时间去参观她的办公室……", REQUISITIONS_FLIRT_ELLE_DECLINED) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Elle.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode REQUISITIONS_FLIRT_ELLE_DECLINED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_ELLE_DECLINED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REQUISITIONS_INTERACTION.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode REQUISITIONS_FLIRT_ELLE_OFFICE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_ELLE_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("主导", "跟埃勒来一场支配型性爱。",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Elle.class)),
								null,
								null) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						ELLE_END_SEX,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_ELLE_OFFICE_SEX_START_AS_DOM"));
				
			} else if(index==2) {
				return new ResponseSex("顺从", "放任埃勒做主，让她支配你。",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Elle.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						ELLE_END_SEX,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_ELLE_OFFICE_SEX_START_AS_SUB"));
			}
			return null;
		}
	};

	public static final DialogueNode ELLE_END_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Elle.class).applyWash(false, true, null, 0);
			Main.game.getNpc(Elle.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "ELLE_END_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "回到走廊，继续你的旅程……", EnforcerHQDialogue.CORRIDOR_PLAIN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	
	
	// Wes interactions:
	
	

	public static final DialogueNode REQUISITIONS_FLIRT_WES = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("参观办公室", "暗示韦斯你想去他办公室“参观”一番……", REQUISITIONS_FLIRT_WES_OFFICE) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER);
						Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
					}
				};
				
			} else if(index==2) {
				int progress = (int) Main.game.getDialogueFlags().getSavedLong("wes_background_progress");
				if(progress<5) {
					return new Response("一齐参观",
							"你需要跟韦斯关系更好一些，他才会愿意跟埃勒一起去“参观”他的办公室……",
							null);
				}
				return new Response("一齐参观", "暗示韦斯带着埃勒一起去他办公室“参观”一番……", REQUISITIONS_FLIRT_WES_OFFICE_THREESOME) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER);
						Main.game.getNpc(Wes.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Elle.class).setLocation(Main.game.getPlayer(), false);
					}
				};
				
			} else if(index==3) {
				return new Response("结束", "跟韦斯玩乐一番后，你决定不再跟他继续了……", REQUISITIONS_FLIRT_WES_FINISH);
				
			}
			return null;
		}
	};

	public static final DialogueNode REQUISITIONS_FLIRT_WES_FINISH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_FINISH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REQUISITIONS_INTERACTION.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode REQUISITIONS_FLIRT_WES_OFFICE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("主导", "跟韦斯来一场支配型性爱。",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Wes.class)),
								null,
								null),
						WES_END_SEX,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_SEX_START_AS_DOM"));
				
			} else if(index==2) {
				return new ResponseSex("顺从", "放任韦斯做主，让他支配你。",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Wes.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						WES_END_SEX,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_SEX_START_AS_SUB"));
			}
			return null;
		}
	};

	public static final DialogueNode WES_END_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).applyWash(false, true, null, 0);
			Main.game.getNpc(Wes.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_END_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "回到走廊，继续你的旅程……", EnforcerHQDialogue.CORRIDOR_PLAIN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode REQUISITIONS_FLIRT_WES_OFFICE_THREESOME = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_THREESOME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("主导", "跟韦斯和埃勒来一场支配型性爱。",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer()),
								Util.newArrayListOfValues(
										Main.game.getNpc(Wes.class),
										Main.game.getNpc(Elle.class)),
								null,
								null) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						WES_END_SEX_THREESOME,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_THREESOME_AS_DOM"));
				
			} if(index==2) {
				return new ResponseSex("支配埃勒", "跟韦斯一起支配埃勒。",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer(),
										Main.game.getNpc(Wes.class)),
								Util.newArrayListOfValues(
										Main.game.getNpc(Elle.class)),
								null,
								null) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						WES_END_SEX_THREESOME,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_THREESOME_AS_JOINT_DOM"));
				
			} else if(index==3) {
				return new ResponseSex("加入埃勒", "让韦斯主导，跟埃勒一起被他支配。",
						true, true,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Wes.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS),
										new Value<>(Main.game.getNpc(Elle.class), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						null,
						null,
						WES_END_SEX_THREESOME,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_THREESOME_AS_JOINT_SUB"));
				
			} else if(index==4) {
				return new ResponseSex("顺从", "放任埃勒和韦斯做主，让他们支配你。",
						true, true,
						Main.game.getPlayer().isTaur()
							?new SMAllFours(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Wes.class), SexSlotAllFours.BEHIND),
											new Value<>(Main.game.getNpc(Elle.class), SexSlotAllFours.IN_FRONT)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)))
							:new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Wes.class), SexSlotLyingDown.MISSIONARY),
											new Value<>(Main.game.getNpc(Elle.class), SexSlotLyingDown.FACE_SITTING_REVERSE)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public boolean isSelfTransformDisabled(GameCharacter character) {
									return character.equals(Main.game.getNpc(Elle.class));
								}
							},
						null,
						null,
						WES_END_SEX_THREESOME,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_THREESOME_AS_SUB"));
				
			} else if(index==5) {
				return new ResponseSex("埃勒的复仇", "让埃勒主导，跟韦斯一起被她支配。",
						true, true,
						new SMLyingDown(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Elle.class), SexSlotLyingDown.COWGIRL)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
										new Value<>(Main.game.getNpc(Wes.class), SexSlotLyingDown.LYING_DOWN_TWO))) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						null,
						null,
						WES_END_SEX_THREESOME,
						UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_FLIRT_WES_OFFICE_THREESOME_ELLE_REVENGE"));
			}
			return null;
		}
	};

	public static final DialogueNode WES_END_SEX_THREESOME = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Wes.class).applyWash(false, true, null, 0);
			Main.game.getNpc(Wes.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
			Main.game.getNpc(Elle.class).applyWash(false, true, null, 0);
			Main.game.getNpc(Elle.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_END_SEX_THREESOME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "回到走廊，继续你的旅程……", EnforcerHQDialogue.CORRIDOR_PLAIN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode WES_ELLE_OFFICE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_ELLE_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("主导", "跟埃勒来一场支配型性爱。",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Elle.class)),
								null,
								null) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						WES_END_SEX_ELLE,
						UtilText.parseFromXMLFile("characters/dominion/wes", "WES_ELLE_OFFICE_START_AS_DOM"));
				
			} else if(index==2) {
				return new ResponseSex("顺从", "放任埃勒做主，让她支配你。",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Elle.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null) {
							@Override
							public boolean isSelfTransformDisabled(GameCharacter character) {
								return character.equals(Main.game.getNpc(Elle.class));
							}
						},
						WES_END_SEX_ELLE,
						UtilText.parseFromXMLFile("characters/dominion/wes", "WES_ELLE_OFFICE_START_AS_SUB"));
			}
			return null;
		}
	};

	public static final DialogueNode WES_END_SEX_ELLE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Elle.class).applyWash(false, true, null, 0);
			Main.game.getNpc(Elle.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/wes", "WES_END_SEX_ELLE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "回到走廊，继续你的旅程……", EnforcerHQDialogue.CORRIDOR_PLAIN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
}
