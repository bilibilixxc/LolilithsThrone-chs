package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMZaranixCockSucking;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.89
 * @version 0.3.4
 * @author Innoxia
 */
public class ZaranixHomeFirstFloor {
	
	public static final DialogueNode STAIRS = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getLabel() {
			return "楼梯";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "STAIRS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("下楼", "前往扎拉尼克斯家一楼。", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getLabel() {
			return "走廊";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR"));
			
			if(((Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1) != null
					&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1).getPlace().getPlaceType().equals(PlaceType.ZARANIX_FF_MAID))
					
						|| (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1) != null
								&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1).getPlace().getPlaceType().equals(PlaceType.ZARANIX_FF_MAID)))
					
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKellySubdued)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR_KELLY_NOT_SUBDUED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR_MAID = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKellySubdued)) {
				return 30;
			}
			return 60;
		}

		@Override
		public String getLabel() {
			return "走廊";
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKellySubdued)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR_MAID_KELLY_SUBDUED");
			
			} else if(Main.game.getNpc(ZaranixMaidKelly.class).getFoughtPlayerCount()==0) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR_MAID_KELLY_ENCOUNTER");
			
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR_MAID_KELLY_ENCOUNTER_REPEAT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKellySubdued)) {
				if(index==1) {
					return new ResponseSex("使用凯丽", "和女仆做点有意思的事。",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
							null,
							null), ZaranixMaidKelly.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR_MAID_KELLY_SEX"));
					
				} else if(index==2) {
					return new ResponseSex("顺从",
							"你无法让自己成为支配方，但你<i>确实</i>想和凯丽做爱。或许你可以顺从，她会愿意操你吗？",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), ZaranixMaidKelly.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "CORRIDOR_MAID_KELLY_SEX_SUBMIT"));
					
				} else if (index == 3) {
					return new Response("转化",
							"让凯丽使用[kelly.her]的恶魔能力来自我转化……",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getNpc(ZaranixMaidKelly.class));
						}
					};
					
				} else {
					return null;
				}
			} else {
				if(index==1) {
					return new ResponseCombat("战斗", "保护你自己，击败极度愤怒的女仆！", Main.game.getNpc(ZaranixMaidKelly.class)) {
						@Override
						public void effects() {
							Main.game.getNpc(ZaranixMaidKelly.class).setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
						}
					};
				} else {
					return null;
				}
			}
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKellySubdued);
		}
	};
	
	public static final DialogueNode ROOM = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getLabel() {
			return "房间";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "扎拉尼克斯的实验室";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("扎拉尼克斯的实验室", "你发现自己进入了一间实验室，和莉莱雅家的很像。", ZARANIX_ROOM_ENTRY) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_ENTRY = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "扎拉尼克斯的实验室";
		}

		@Override
		public String getContent() {
			if(Main.game.getNpc(Zaranix.class).getFoughtPlayerCount()==0) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_ENTRY");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_ENTRY_REPEAT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Zaranix.class).getFoughtPlayerCount()==0) {
				if(index==1) {
					return new Response("索取亚瑟", "拒绝告诉扎拉尼克斯你为何来此，转而仅仅要求他把亚瑟交给你。", ZARANIX_ROOM_NO_EXPLANATION) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if(index==2) {
					return new Response("坦白一切", "告诉扎拉尼克斯，莉莱雅需要亚瑟来帮助她揭示跨位面传送的谜题。", ZARANIX_ROOM_EXPLANATION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE));
							Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
					
				} else {
					return null;
				}
			} else {
				if(index==1) {
					return new ResponseCombat("战斗", "保护你自己，抵御扎拉尼克斯的进攻！", Main.game.getNpc(Zaranix.class));
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_NO_EXPLANATION = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "扎拉尼克斯的实验室";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_NO_EXPLANATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("战斗", "保护你自己，抵御扎拉尼克斯的进攻！", Main.game.getNpc(Zaranix.class));
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_EXPLANATION = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "扎拉尼克斯的实验室";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_EXPLANATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "离开扎拉尼克斯家。", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_EXPLANATION_THANKS"));
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						ZaranixHomeGroundFloorRepeat.resetHouseAfterLeaving();
					}
				};
				
			} else if(index==2) {
				return new Response("‘感谢’扎拉尼克斯", "你有点儿不好意思接受扎拉尼克斯的帮助。或许你可以用口交来感谢他……", ZARANIX_ROOM_EXPLANATION_THANK_ZARANIX,
						Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), CorruptionLevel.TWO_HORNY, null, null, null) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_EXPLANATION_THANK_ZARANIX = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "扎拉尼克斯的实验室";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_EXPLANATION_SEXY_THANKS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("吮吸鸡巴", "给扎拉尼克斯展示你吮吸鸡巴的高超技艺。",
						true, true,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_THANKING_ZARANIX,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_EXPLANATION_SEXY_THANKS_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_THANKING_ZARANIX = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "扎拉尼克斯已完成";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloor", "ZARANIX_ROOM_EXPLANATION_SEXY_THANKS_POST_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续旅途。",  PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						ZaranixHomeGroundFloorRepeat.resetHouseAfterLeaving();
					}
				};
			} else {
				return null;
			}
		}
	};
}
