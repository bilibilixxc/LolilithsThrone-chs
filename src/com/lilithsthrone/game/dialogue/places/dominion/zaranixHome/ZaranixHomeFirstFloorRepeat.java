package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
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
 * @since 0.2.2
 * @version 0.2.3
 * @author Innoxia
 */
public class ZaranixHomeFirstFloorRepeat {

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
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered) && Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class));
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "STAIRS"));
			
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_PRESENT"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_PRESENT_REPEAT"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
				if (index == 1) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
						return new Response("下楼", "你得先回复凯丽！", null);
						
					} else {
						return new Response("下楼", "前往扎拉尼克斯家一楼。", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
							}
						};
					}
	
				} else if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
					if(index==2) {
						return new ResponseSex("做爱", "跟凯丽云雨一番。",
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
								null,
								null), AFTER_KELLY_SEX, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
							}
						};
						
					} else if(index==3) {
						return new ResponseSex("顺从",
								"你没法接受自己占据主导，但又<i>超级想</i>跟凯莉做爱。或许你表示屈服的话，[kelly.she]也愿意来干你？",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
										Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null), AFTER_KELLY_SEX, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX_SUB")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
							}
						};
						
					} else if (index == 4) {
						return new Response("转化",
								"让凯丽使用[kelly.her]的恶魔能力来自我转化……",
								BodyChanging.BODY_CHANGING_CORE){
							@Override
							public void effects() {
								Main.game.saveDialogueNode();
								BodyChanging.setTarget(Main.game.getNpc(ZaranixMaidKelly.class));
							}
						};
						
					} else if(index == 5 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
						return new Response("拒绝",
								"拒绝那个饥渴的恶魔，继续前进。",
								STAIRS){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX_DECLINED"));
							}
						};
						
					} else {
						return null;
					}
				} else {
					return null;
				}
			} else {
				if(index == 1) {
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
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered) && Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class));
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "CORRIDOR"));

			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_PRESENT"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_PRESENT_REPEAT"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
				if(index==1) {
					return new ResponseSex("做爱", "跟凯丽云雨一番。",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
							null,
							null), AFTER_KELLY_SEX, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
						}
					};
					
				} else if(index==2) {
					return new ResponseSex("顺从",
							"你没法接受自己占据主导，但又<i>超级想</i>跟凯莉做爱。或许你表示屈服的话，[kelly.she]也愿意来干你？",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_KELLY_SEX, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX_SUB")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
						}
					};
					
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
					
				} else if(index == 4 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
					return new Response("拒绝",
							"拒绝那个饥渴的恶魔，继续前进。",
							KELLY_DECLINE){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
						}
					};
					
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode KELLY_DECLINE = new DialogueNode("", "", false, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX_DECLINED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_FF_STAIRS)) {
				if(index==1) {
					return new Response("下楼", "前往扎拉尼克斯家一楼。", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_KELLY_SEX = new DialogueNode("", "凯丽粗重地喘息，后退几步。", false) {

		@Override
		public String getLabel() {
			return "完成";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(ZaranixMaidKelly.class)) == 0) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "AFTER_KELLY_SEX_NO_ORGASM"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "AFTER_KELLY_SEX"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_FF_STAIRS)) {
				if(index==1) {
					return new Response("下楼", "前往扎拉尼克斯家一楼。", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
						}
					};
				}
			}
			return null;
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_OUTSIDE"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("进入", "敲敲扎拉尼克斯的门并进去。", ZARANIX_ROOM_ENTER);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_EXIT = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_EXIT"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("进入", "敲敲扎拉尼克斯的门并进去。", ZARANIX_ROOM_ENTER);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_ENTER = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixRepeatEncountered)) {
				return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_ENTER"));
			} else {
				return(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_ENTER_REPEAT"));
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("离开", "道别后再次回到二楼的走廊。", ZARANIX_ROOM_EXIT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixRepeatEncountered, true);
					}
				};
				
			} else if(index == 1) {
				return new Response("女仆", "向扎拉尼克斯打听他的女仆。", ZARANIX_ROOM_MAIDS) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixRepeatEncountered, true);
					}
				};
				
			} else if(index == 2) {
				return new Response("实验", "询问扎拉尼克斯正在进行什么实验，又准备让亚瑟做什么。", ZARANIX_ROOM_EXPERIMENTS) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixRepeatEncountered, true);
					}
				};
				
			} else if(index == 3) {
				return new Response("淫梦魔形态", "询问扎拉尼克斯其淫梦魔形态的事情。毕竟大多数恶魔都会选择保持女性外表。", ZARANIX_ROOM_INCUBUS_FORM) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixRepeatEncountered, true);
					}
				};
				
			} else if(index == 4) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("口交", "你无法使用自己的嘴巴，所以不能给扎拉尼克斯舔鸡巴！", null);
				}
				return new ResponseSex("口交", "询问扎拉尼克斯他愿不愿意被口……",
						true, true,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_ZARANIX_BLOWJOB,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_BLOWJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index == 5) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("口交(魅魔)", "你无法使用自己的嘴巴，所以不能给扎拉尼克斯舔鸡巴！", null);
				}
				return new Response("口交(魅魔)", "告诉扎拉尼克斯，如果他愿意转化成魅魔形态，你就很乐意给“她”来段口交了……", ZARANIX_ROOM_BLOWJOB_TF) {
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
	
	public static final DialogueNode ZARANIX_ROOM_MAIDS = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_MAIDS"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("女仆", "你已经问过扎拉尼克斯那些女仆的事情了！", null);
			} else {
				return ZARANIX_ROOM_ENTER.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_EXPERIMENTS = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_EXPERIMENTS"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 2) {
				return new Response("实验", "你已经问过扎拉尼克斯实验的事情了！", null);
			} else {
				return ZARANIX_ROOM_ENTER.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_INCUBUS_FORM = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_INCUBUS_FORM"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 3) {
				return new Response("淫梦魔形态", "你已经问过扎拉尼克斯为何选择淫梦魔形态了！", null);
			} else {
				return ZARANIX_ROOM_ENTER.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_BLOWJOB_TF = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_BLOWJOB_TF_START"));
			((Zaranix)Main.game.getNpc(Zaranix.class)).transformFeminine();
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_BLOWJOB_TF_END"));
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("舔鸡巴", "开始侍奉扎拉尼克斯的肉棒……",
						true, true,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_ZARANIX_BLOWJOB,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_BLOWJOB_TF_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SEX_ZARANIX_BLOWJOB = new DialogueNode("结束", "扎拉尼克斯体验够了你的口技……", true) {
		@Override
		public String getLabel() {
			return "完成";
		}
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "AFTER_SEX_ZARANIX_BLOWJOB"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("继续", "继续穿过扎拉尼克斯府邸……", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_CORRIDOR);
						if(Main.game.getNpc(Zaranix.class).isFeminine()) {
							Main.game.getNpc(Zaranix.class).setStartingBody(false);
						}
					}
				};
			}
			return null;
		}
	};
}
