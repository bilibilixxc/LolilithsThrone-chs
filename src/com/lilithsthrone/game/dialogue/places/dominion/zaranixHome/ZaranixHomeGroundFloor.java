package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Amber;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKatherine;
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
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMAmberDoggyFucked;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMZaranixCockSucking;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.89
 * @version 0.3.4
 * @author Innoxia
 */
public class ZaranixHomeGroundFloor {
	
	public static void resetHouseAfterLeaving() {
		// Maids:
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixAmberSubdued, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKatherineSubdued, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKellySubdued, false);
		
		Main.game.getNpc(Amber.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, true);
		
		((Zaranix)Main.game.getNpc(Zaranix.class)).setStartingBody(false);
		((Amber)Main.game.getNpc(Amber.class)).setStartingBody(false);
		((ZaranixMaidKatherine)Main.game.getNpc(ZaranixMaidKatherine.class)).setStartingBody(false);
		((ZaranixMaidKelly)Main.game.getNpc(ZaranixMaidKelly.class)).setStartingBody(false);
	}

	private static void travelFromEntranceToLounge() {
		for(Cell c : Pathing.aStarPathing(
				Main.game.getWorlds().get(WorldType.ZARANIX_HOUSE_GROUND_FLOOR).getCellGrid(),
				Main.game.getWorlds().get(WorldType.ZARANIX_HOUSE_GROUND_FLOOR).getCell(PlaceType.ZARANIX_GF_ENTRANCE).getLocation(),
				Main.game.getWorlds().get(WorldType.ZARANIX_HOUSE_GROUND_FLOOR).getCell(PlaceType.ZARANIX_GF_LOUNGE).getLocation(),
				false)) {
			c.setDiscovered(true);
			c.setTravelledTo(true);
		}
		Main.game.getNpc(Amber.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
		Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
		
		for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
			companion.setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
		}
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return "扎拉尼克斯的家";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixDiscoveredHome)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE_REPEAT"));
				
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixMaidsHostile)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE_REPEAT_HOSTILE_MAIDS"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE_REPEAT_NON_HOSTILE_MAIDS"));
				}
				
				return UtilText.nodeContentSB.toString();
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.isExtendedWorkTime()) {
					return new Response("敲门", "这么晚了，没人会来开门的。你只能白天再来，或者另想办法进去。", null);
				}
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixMaidsHostile)) {
					return new Response("敲门", "扎拉尼克斯的女仆会一眼认出你，不会让你进去的。你必须另想办法进去。", null);
				}
				return new Response("敲门", "敲敲门，等待应答。", OUTSIDE_KNOCK_ON_DOOR) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
					}
				};

			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					if(!Main.game.isExtendedWorkTime()) {
						return new Response("飞过栅栏", "这么晚了，房子肯定锁得严严实实。你只能白天再来，或者另想办法进去。", null);
					}
					return new Response("飞过栅栏", "飞越花园的栅栏，看看有没有办法从那里进去。", GARDEN_ENTRY) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_GARDEN_ENTRY, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
							Main.game.getTextStartStringBuilder()
								.append("<p>A small fence like the one before you is no obstacle for someone who can fly.")
								.append(!Main.game.getPlayer().isAbleToFlyFromExtraParts() ? " Spreading your wings, you" : "You")
								.append(" take a little run up before launching yourself into the air. Quickly gaining altitude, you wheel around and swoop down into the garden adjoining Zaranix's home.</p>");
						}
					};
				}
				if(!Main.game.isExtendedWorkTime()) {
					return new Response("爬栅栏", "这么晚了，房子肯定锁得严严实实。你只能白天再来，或者另想办法进去。", null);
				}
				return new Response("爬栅栏", "爬过花园的栅栏，看看有没有办法从那里进去。", GARDEN_ENTRY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_GARDEN_ENTRY, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
						Main.game.getTextStartStringBuilder().append(
							"<p>"
								+ "决定偷偷溜进扎拉尼克斯家，在他家花园和公共街道之间的栅栏边徘徊，伺机翻越障碍物。"
								+ "一旦确定没有人注意到你，你就迅速爬上栅栏的铁条，腿从上面荡过去，跳到私人花园里。"
							+ "</p>");
					}
				};

			} else if (index == 3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKickedDownDoor)) {
					return new Response("踢开门", "你上次进入后，前门已被加固。无法再这么进去。", null);
					
				} else if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE) >= 35) {
					return new Response("踢开门", "一脚踹开前门。", ENTRANCE_KICK_DOWN_DOOR) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKickedDownDoor, true);
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
							Main.game.getNpc(Amber.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						}
					};
				} else {
					return new Response("踢开门", "你不认为自己有足够的力气踢开这么结实的门。(需要35体格。)", null);
				}
				
			} else if (index == 0) {
				return new Response("离开", "转身离开。", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().incrementSavedLong("amber_door_knock_repeat_count", 1);
		}
		@Override
		public String getLabel() {
			return "扎拉尼克斯的家";
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_REPEAT");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor)) {
				
				if (index == 1) {
					return new Response("退开", "从门前退开，找另外一条道进去。", OUTSIDE) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).returnToHome();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_SLAMMED_IN_FACE"));
						}
					};

				} else if (index == 2) {
					return new Response("乞求", "乞求女仆让你进去。", OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), CorruptionLevel.THREE_DIRTY, null, null, null);

				} else if(index == 3 && Main.game.getDialogueFlags().getSavedLong("amber_door_knock_repeat_count")>=4) {
					return new Response("进入", "看起来你的坚持得到了回报！", MEETING_ZARANIX) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
							travelFromEntranceToLounge();
						}
					};
					
				}
				return null;
				
			} else {
				if (index == 1) {
					return new Response("亚瑟", "打听亚瑟。", OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKnockedOnDoor, true);
						}
					};
					
				} else if (index == 0) {
					return new Response("离开", "说你搞错了房门，打算离开。", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).returnToHome();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKnockedOnDoor, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_WRONG_HOUSE"));
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "扎拉尼克斯的家";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("退开", "从门前退开，找另外一条道进去。", OUTSIDE) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_SLAMMED_IN_FACE"));
					}
				};

			} else if (index == 2) {
				return new Response("乞求", "乞求女仆让你进去。", OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG,
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), CorruptionLevel.THREE_DIRTY, null, null, null);
			}
			return null;
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "扎拉尼克斯的家";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("反悔", "起身退开。你当然不想再做<i>那样</i>的事。", OUTSIDE) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_REFUSE"));
					}
				};

			}
			if(!Main.game.isFootContentEnabled()) {
				if(index == 1) {
					return new Response("好狗狗", "服从支配魅魔的命令，五体投地，告诉她你是只好狗狗。",
							OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_GOOD_DOGGY,
							Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST), CorruptionLevel.TWO_HORNY, null, null, null) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
						}
					};
				}
				
			} else {
				if(index == 1) {
					return new Response("不情愿地舔", "如果这样能见到亚瑟，你会去做的，尽管你对整件事非常不情愿。",
							OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_RELUCTANT_LICK,
							Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.TWO_HORNY, null, null, null) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
						}
					};
	
				} else if(index == 2) {
					return new Response("饥渴地舔", "立即四脚着地着地，热情地舔女仆的鞋子。",
							OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK,
							Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.FOUR_LUSTFUL, null, null, null) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_GOOD_DOGGY = new DialogueNode("", "", true, true) {
		@Override
		public String getLabel() {
			return "扎拉尼克斯的家";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_GOOD_DOGGY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("进入", "跟在安柏身边，她会带你进屋。", MEETING_ZARANIX) {
					@Override
					public void effects() {
						travelFromEntranceToLounge();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_RELUCTANT_LICK = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "扎拉尼克斯的家";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_RELUCTANT_LICK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("进入", "跟在安柏身边，她会带你进屋。", MEETING_ZARANIX) {
					@Override
					public void effects() {
						travelFromEntranceToLounge();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "扎拉尼克斯的家";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_EAGER_LICK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("等待", "按安柏说的等她回来。", MEETING_ZARANIX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_WAITING"));
						travelFromEntranceToLounge();
					}
				};

			} else if (index == 2) {
				return new Response("舔舔鞋底", "不让安柏现在离开！你还没有清理干净她的鞋子！",
						OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK_SOLES,
						Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.FIVE_CORRUPT, null, null, null);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK_SOLES = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "扎拉尼克斯的家";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_EAGER_LICK_SOLES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("进入", "跟在安柏身边，她会带你进屋。", MEETING_ZARANIX) {
					@Override
					public void effects() {
						travelFromEntranceToLounge();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Set this to true here so that the repeat encounter with Amber at the door doesn't end up with her acting as though you broke in
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.amberRepeatEncountered, true);
		}
		@Override
		public String getLabel() {
			return "休息室";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("坐在地上", "听从安柏的命令，坐在地上。", MEETING_ZARANIX_SIT_FLOOR) {
					@Override
					public void effects() {
						Main.game.getNpc(Zaranix.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.amberSatOnFloor, true);
					}
				};
				
			} else if (index == 2) {
				return new Response("坐在沙发上", "违抗安柏，坐在其中一张沙发上。", MEETING_ZARANIX_SIT_SOFA) {
					@Override
					public void effects() {
						Main.game.getNpc(Zaranix.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_SIT_FLOOR = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "休息室";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_SIT_FLOOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("隐瞒", "只需说莉莱雅想要亚瑟回来，避免告诉扎拉尼克斯原因。", MEETING_ZARANIX_HOLD_BACK);
				
			} else if (index == 2) {
				return new Response("坦白一些", "告诉扎拉尼克斯你在这个世界上出现的所有事情，以及莉莱雅需要亚瑟的帮助才能研究出发生了什么事。", MEETING_ZARANIX_EXPLAIN_EVERYTHING);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_SIT_SOFA = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "休息室";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_SIT_SOFA");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("隐瞒", "只需说莉莱雅想要亚瑟回来，避免告诉扎拉尼克斯原因。", MEETING_ZARANIX_HOLD_BACK);
				
			} else if (index == 2) {
				return new Response("坦白一些", "告诉扎拉尼克斯你在这个世界上出现的所有事情，以及莉莱雅需要亚瑟的帮助才能研究出发生了什么事。", MEETING_ZARANIX_EXPLAIN_EVERYTHING);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_HOLD_BACK = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "休息室";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_HOLD_BACK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("亚瑟", "你终于与难找的猎物面对面了。", MEETING_ZARANIX_ARTHUR) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE));
						Main.game.getNpc(Arthur.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_EXPLAIN_EVERYTHING = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "休息室";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_EXPLAIN_EVERYTHING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("亚瑟", "你终于与难找的猎物面对面了。", MEETING_ZARANIX_ARTHUR) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE));
						Main.game.getNpc(Arthur.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_ARTHUR = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "休息室";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "拒绝为扎拉尼克斯或安柏提供任何性服务，离开这里。",  REFUSE_SEX) {
					@Override
					public void effects() {
						Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
					}
				};
				
			} else if (index == 2) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("“感谢”扎拉尼克斯", "你不能嗦扎拉尼克斯的肉棒，你的嘴被限制！", null);
					
				} else {
					return new Response("“感谢”扎拉尼克斯", "告诉扎拉尼克斯你有多感谢他。<br/>[style.italicsSex(这会导致你给扎拉尼克斯口交！)]", MEETING_ZARANIX_ARTHUR_THANK_ZARANIX) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getNpc(Zaranix.class).displaceClothingForAccess(CoverableArea.PENIS, null);
							Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else if (index == 3) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
						&& (Main.game.getPlayer().hasVagina()
								?!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
								:true)) {
					return new Response("“感谢”安柏", "你不能被安柏操，你的屁穴"+(Main.game.getPlayer().hasVagina()?"或阴道":"")+"被限制！", null);
					
				} else {
					return new Response("“感谢”安柏", "告诉安柏你有多感谢她。<br/>[style.italicsSex(这会导致你被安柏操！)]", MEETING_ZARANIX_ARTHUR_THANK_AMBER) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).unequipClothingIntoVoid(Main.game.getNpc(Amber.class).getClothingInSlot(InventorySlot.TORSO_UNDER), true, Main.game.getNpc(Amber.class));
							Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_ARTHUR_THANK_ZARANIX = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "休息室";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR_THANK_ZARANIX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("吮吸肉棒",
						"向扎拉尼克斯展示你多擅长嗦鸡巴。",
						true,
						true,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING),
										new Value<>(Main.game.getNpc(Amber.class), SexSlotSitting.PERFORMING_ORAL_TWO)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_THANKING_ZARANIX,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR_THANK_ZARANIX_START_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Zaranix.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_ARTHUR_THANK_AMBER = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "休息室";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR_THANK_AMBER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("抬起屁股", "按照安柏的命令，抬屁股对着她。",
						true, true,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Amber.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_SEX_THANKING_AMBER,
						"<p>"
							+ "你顺从地抬起屁股朝向安柏，突然感觉到她的手在你的右屁股上狠狠地拍了一下，你发出了一声轻呼，她咆哮起来："
							+ "[amber.speech(叫吧，婊子！<i>你现在是我的了！</i>)]"
						+ "</p>");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode REFUSE_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR_REFUSE_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。",  PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_THANKING_ZARANIX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
		}
		@Override
		public String getLabel() {
			return "扎拉尼克斯已完成";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "AFTER_SEX_THANKING_ZARANIX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。",  PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_THANKING_AMBER = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
		}
		@Override
		public String getLabel() {
			return "安柏好了";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "AFTER_SEX_THANKING_AMBER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。",  PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false));
			}
			return null;
		}
	};
	
	
	// Combat route:
	
	public static final DialogueNode ENTRANCE_KICK_DOWN_DOOR = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "门厅";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_KICK_DOWN_DOOR"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_KICK_DOWN_DOOR_MAIDS_MET"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_KICK_DOWN_DOOR_MAIDS_NOT_MET"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("战斗", "保护你自己，击败极度愤怒的女仆！", Main.game.getNpc(Amber.class)) {
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
	
	
	// General places:
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			if(Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_ENTRANCE)) {
				return 60;
			}
			return 20;
		}

		@Override
		public String getLabel() {
			return "门厅";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE"));
			
			if(Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_ENTRANCE)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_AMBER_PRESENT"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "离开扎拉尼克斯家，回到恶魔之家。<b>如果你现在选择离开，就必须重新获得进入许可！</b>", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						resetHouseAfterLeaving();
					}
				};

			} else if(index==2 && Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_ENTRANCE)) {
				return new ResponseSex("使用安柏", "和这个暴躁的女仆爽爽。",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
						null,
						null), Amber.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_AMBER_SEX"));
				
			} else if(index==3 && Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_ENTRANCE)) {
				return new ResponseSex("顺从",
						"安柏的暴脾气让你性欲高涨。你没法主导性爱，但又<i>非常</i>想跟她做爱。或许只要选择顺从，她也会愿意干你的？",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null), Amber.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_AMBER_SEX_SUBMIT"));
				
			} else if (index == 4 && Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_ENTRANCE)) {
				return new Response("转化",
						"让安柏使用[kelly.her]的恶魔能力来转化自己……",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getNpc(Amber.class));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
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
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "STAIRS"));
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "STAIRS_KATHERINE_NOT_SUBDUED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("上楼", "前往扎拉尼克斯家二楼。", PlaceType.ZARANIX_FF_STAIRS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_STAIRS, false);
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

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR"));
			
			if(Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1) != null
					&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1).getPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_MAID)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_KATHERINE_NOT_SUBDUED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode GARDEN_ENTRY = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getLabel() {
			return "扎拉尼克斯的花园";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN_ENTRY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("飞过栅栏",
							"飞越花园的栅栏，回到恶魔之家。<b>如果你离开，在扎拉尼克斯家取得的所有进展都将重置！</b>",
							PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
						@Override
						public void effects() {
							resetHouseAfterLeaving();
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "你决定下次再来，在花园小径上小跑了几步，腾空而起。"
										+ "从栅栏上俯冲而下，你很快就发现自己回到了恶魔之家……"
									+ "</p>");
						}
					};
				}
				return new Response("爬栅栏",
						"爬过花园的栅栏，回到恶魔之家。<b>如果你离开，在扎拉尼克斯家取得的所有进展都将重置！</b>",
						PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						resetHouseAfterLeaving();
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "你决定下次再来，于是爬上栅栏，很快发现自己回到了恶魔之家……"
								+ "</p>");
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode GARDEN = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getLabel() {
			return "扎拉尼克斯的花园";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode GARDEN_ROOM = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getLabel() {
			return "花园房";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN_ROOM"));
			
			if(Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX()-1, Main.game.getPlayer().getLocation().getY()) != null
					&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX()-1, Main.game.getPlayer().getLocation().getY()).getPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_MAID)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN_ROOM_KATHERINE_NOT_SUBDUED"));
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
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
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
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_SUBDUED");
			
			} else if(Main.game.getNpc(ZaranixMaidKatherine.class).getFoughtPlayerCount()==0) {
				if(Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_ENTRANCE)) {
					return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_ENCOUNTER_FOUGHT_AMBER");
				}
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_ENCOUNTER");
			
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_ENCOUNTER_REPEAT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				if(index==1) {
					return new ResponseSex("使用凯瑟琳", "和女仆做点有意思的事。",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKatherine.class)),
							null,
							null), ZaranixMaidKatherine.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_SEX"));
					
				} else if(index==2) {
					return new ResponseSex("顺从",
							"你没法主导性爱，但又<i>非常</i>想和凯瑟琳做爱。或许只要选择顺从，她也会愿意干你的？",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKatherine.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), ZaranixMaidKatherine.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_SEX_SUBMIT"));
					
				} else if (index == 3) {
					return new Response("转化",
							"让凯瑟琳使用恶魔之力转化自己……",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getNpc(ZaranixMaidKatherine.class));
						}
					};
					
				} else {
					return null;
				}
			} else {
				if(index==1) {
					return new ResponseCombat("战斗", "保护你自己，击败极度愤怒的女仆！", Main.game.getNpc(ZaranixMaidKatherine.class)) {
						@Override
						public void effects() {
							Main.game.getNpc(ZaranixMaidKatherine.class).setPlayerKnowsName(true);
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
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued);
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode LOUNGE = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getLabel() {
			return "休息室";
		}

		@Override
		public String getContent() {
			if(!Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_LOUNGE)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_EMPTY");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixAmberSubdued)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_SUBDUED");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor) || Main.game.getNpc(Amber.class).getFoughtPlayerCount()!=0) {
				UtilText.nodeContentSB.setLength(0);
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT_ALREADY_MET"));
				
				if(Main.game.getNpc(Amber.class).getFoughtPlayerCount()!=0) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT_ALREADY_MET_KNOWS_NAME"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT_ALREADY_MET_DOES_NOT_KNOW_NAME"));
				}
				
				return UtilText.nodeContentSB.toString();
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_LOUNGE)) {
				return null;
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixAmberSubdued)) {
				if(index==1) {
					return new ResponseSex("使用安柏", "和这个暴躁的女仆爽爽。",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
							null,
							null), Amber.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_SEX")
							);
					
				} else if(index==2) {
					return new ResponseSex("顺从",
							"安柏的暴脾气让你性欲高涨。你没法主导性爱，但又<i>非常</i>想跟她做爱。或许只要选择顺从，她也会愿意干你的？",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), Amber.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_SEX_SUBMIT")
							);
					
				} else if (index == 3) {
					return new Response("转化",
							"让安柏使用[kelly.her]的恶魔能力来转化自己……",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getNpc(Amber.class));
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("靠近女仆", "走到女仆身后。<b>她肯定会注意到你的行为，这很可能导致你不得不和她战斗！</b>", LOUNGE_AMBER) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
						}
					};
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode LOUNGE_AMBER = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "休息室";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_GREETING"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor) || Main.game.getNpc(Amber.class).getFoughtPlayerCount()!=0) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_GREETING_ALREADY_FOUGHT"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_GREETING_NEVER_FOUGHT"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("战斗", "保护你自己，击败极度愤怒的女仆！", Main.game.getNpc(Amber.class)) {
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
}
