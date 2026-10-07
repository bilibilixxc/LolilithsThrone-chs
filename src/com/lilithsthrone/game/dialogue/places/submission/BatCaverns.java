package com.lilithsthrone.game.dialogue.places.submission;

import java.time.DayOfWeek;

import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.BatCavernsEncounterDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.WesQuest;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.3
 * @version 0.3.21
 * @author Innoxia, DSG
 */
public class BatCaverns {
	
	private static Response getElleSearchResponse() {
		if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_1) {
			if(Main.game.getDayOfWeek()!=DayOfWeek.WEDNESDAY || Main.game.getHourOfDay()<12 || Main.game.getHourOfDay()>=15) {
				return new Response("寻找埃勒",
						"如果想找到艾勒，必须在"
						+ (Main.game.getDayOfWeek()!=DayOfWeek.WEDNESDAY
							?"[style.italicsBad(星期三)]"
							:"[style.italicsGood(星期三)]")
						+ "的"
						+ ((Main.game.getHourOfDay()<12 || Main.game.getHourOfDay()>=15)
								?"[style.italicsBad([units.time(12)]-[units.time(15)])]"
								:"[style.italicsGood([units.time(12)]-[units.time(15)])]")
						+ "！",
						null);
				
			} else {
				return new Response("寻找埃勒", "开始在蝙蝠洞窟此处寻找埃勒的痕迹……", WesQuest.ELLE_SEARCH);
			}
		}
		return null;
	}
	
	public static final DialogueNode STAIRCASE = new DialogueNode("旋转楼梯", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "STAIRCASE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("屈城区", "回到屈城区。", PlaceType.SUBMISSION_BAT_CAVERNS.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_BAT_CAVERNS, false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SHAFT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "SHAFT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isAbleToFly()) {
					if(!Main.game.getPlayer().isPartyAbleToFly()) {
						return new Response("御城区", "由于你的队伍成员无法飞行，所以不能使用这口竖井回到御城区……", null);
						
					} else {
						return new Response("御城区", "飞上竖井，回到御城区。", SHAFT_FLY_UP) {
							@Override
							public void effects() {
								if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_EXIT_TO_BAT_CAVERNS)==null) {
									Cell referenceCell = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_WAREHOUSES);
									Cell shaftCell = Main.game.getWorlds().get(WorldType.DOMINION).getCell(new Vector2i(referenceCell.getLocation().getX()+2, referenceCell.getLocation().getY()));
									shaftCell.getPlace().setPlaceType(PlaceType.DOMINION_EXIT_TO_BAT_CAVERNS);
									shaftCell.getPlace().setName(PlaceType.BAT_CAVERN_SHAFT.getName());
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SHAFT_FLY_UP_FIRST_TIME"));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SHAFT_FLY_UP"));
								}
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_EXIT_TO_BAT_CAVERNS, false);
							}
						};
					}
					
				} else {
					return new Response("御城区", "由于你无法飞行，所以不能使用这口竖井回到御城区……", null);
				}
			}
			return null;
		}
	};

	public static final DialogueNode SHAFT_FLY_UP = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DominionPlaces.CITY_EXIT_BAT_CAVERNS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CAVERN_DARK = new DialogueNode("阴暗洞窟", "", false) {
		@Override
		public String getAuthor() {
			return "Duner & Innoxia";
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() { // If this is going to be changed, bear in mind that this is called in the REBEL_BASE DialogueNodes below
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "CAVERN_DARK"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return AbstractEncounter.exploreArea("阴暗的洞窟深处");
				
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
						
			} else if(index==3) {
				return getElleSearchResponse();
				
			} else if(index==4
				    && (Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_ONE
				    	|| Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
					if (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.rebelBaseDarkPassFound)) {
					    if (Util.random.nextInt(100) <= 20 + (Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT) ? 30 : 0)) {
							if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
							    return new Response("搜寻密码", 
										"向黑暗中窥探，努力寻找神秘通道的密码。", 
										BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_ONE);
							    
							} else {
							    return new Response("搜寻密码", 
										"向黑暗中窥探，努力寻找神秘通道剩下的密码。", 
										BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_TWO);
							}
					    } else {
					    	return new Response("搜寻密码", 
									"向黑暗中窥探，努力寻找神秘通道的密码。", 
									BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_SEARCH_FAILED);
					    }
					    
					} else {
					    return new Response("搜寻密码", 
							    "你已经找到此区域的密码了。", 
							    null);
					}
					
			} else if(index==5
				&& Main.game.isSillyMode()
				&& (Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_ONE
					|| Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
				    return new Response("我可是个大忙人！", 
							    "简直是浪费时间。"
								    + "<br/>[style.boldBad(这将会跳过“摸金校尉”的所有内容和奖励！)]", 
							    BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_SILLY);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAVERN_LIGHT = new DialogueNode("生物微光照亮的洞窟", "", false) {
		@Override
		public String getAuthor() {
			return "Duner & Innoxia";
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "CAVERN_LIGHT"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return AbstractEncounter.exploreArea("荧光森林");
				
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
						
			} else if(index==3) {
				return getElleSearchResponse();
				
			} else if(index==4
				    && (Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_ONE
				    	|| Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
					if (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.rebelBaseLightPassFound)) {
					    if (Util.random.nextInt(100) <= 20 + (Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT) ? 30 : 0)) {
							if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
							    return new Response("搜寻密码", 
										"拨开蘑菇丛，努力寻找神秘通道的密码。", 
										BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_ONE);
							} else {
							    return new Response("搜寻密码", 
										"拨开蘑菇丛，努力寻找神秘通道剩下的密码。", 
										BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_TWO);
							}
					    } else {
					    	return new Response("搜寻密码", 
									"拨开蘑菇丛，努力寻找神秘通道的密码。", 
									BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_SEARCH_FAILED);
					    }
					} else {
					    return new Response("搜寻密码", 
							    "你已经找到此区域的密码了。", 
							    null);
					}
			} else if(index==5
				&& Main.game.isSillyMode()
				&& (Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_ONE
				|| Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
				    return new Response("我可是个大忙人！", 
							    "简直是浪费时间。"
								    + "<br/>[style.boldBad(这将会跳过“摸金校尉”的所有内容和奖励！)]", 
							    BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_SILLY);
			}
			return null;
		}
	};
	
	public static final DialogueNode RIVER = new DialogueNode("地下河", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return AbstractEncounter.exploreArea("河边");
				
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
						
			} else if(index==3) {
				return getElleSearchResponse();
			}
			return null;
		}
	};
	
	public static final DialogueNode RIVER_BRIDGE = new DialogueNode("蘑菇桥", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER_BRIDGE"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return AbstractEncounter.exploreArea("桥梁周边");
				
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
						
			} else if(index==3) {
				return getElleSearchResponse();
			}
			return null;
		}
	};
	
	public static final DialogueNode RIVER_END = new DialogueNode("地下河", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER_END"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return AbstractEncounter.exploreArea("河边");
				
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
						
			} else if(index==3) {
				return getElleSearchResponse();
			}
			return null;
		}
	};
	
	public static final DialogueNode SLIME_LAKE = new DialogueNode("史莱姆湖", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE"));
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLIME_QUEEN)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_QUEEN_UNKNOWN"));
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_THREE)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_QUEEN_KNOWLEDGE"));
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_QUEEN_GUESS"));
				
			}

			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription(false));
			});
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return AbstractEncounter.exploreArea("湖边");
				
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
						
			} else if(index==3 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				return new Response("摆渡上岛", "你可以用船穿过湖面，到达岛上。", SLIME_LAKE_ISLAND) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_BOAT"));
						if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_PACIFIED"));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_READY_FIGHT"));
						}
					}
				};
				
			} else if(index==4 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("飞上岛", "飞到岛上。", SLIME_LAKE_ISLAND) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_FLY"));
							if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_PACIFIED"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_READY_FIGHT"));
							}
						}
					};
				} else {
					return new Response("飞上岛", "你无法飞行。看样子你得用船了……", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode SLIME_LAKE_ISLAND = new DialogueNode("史莱姆湖", "", true) {
		
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
				return new Response("探索", "你需要到湖的另一边才能探索该区域！", null);
						
			} else if(index==2 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				return new Response("摆渡返回", "用船返回湖的另一边。", SLIME_LAKE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_BOAT_RETURN"));
					}
				};
				
			} else if(index==3 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("飞回去", "飞到湖的另一边。", SLIME_LAKE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_FLY_RETURN"));
						}
					};
					
				} else {
					return new Response("飞回去", "你无法飞行。看样子你得用船了……", null);
				}
				
			} else if (index==4) {
				return new ResponseEffectsOnly(
						"开门",
						"推开高塔的正门，然后进入其中。"){
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.SLIME_QUEENS_LAIR_GROUND_FLOOR, PlaceType.SLIME_QUEENS_LAIR_ENTRANCE);
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "你走到石制高塔的底部，将[pc.hand]放在铁栅装饰的橡木大门上，用力一推，便将其推开，走了进去。"
										+ "</p>");
								if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR));
								}
								Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
							}
						};
			}
			return null;
		}
	};
		
	public static final DialogueNode REBEL_BASE_ENTRANCE_HANDLE = new DialogueNode("奇怪的拉杆", "", false) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(CAVERN_DARK.getContent());
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_ENTRANCE_HANDLE"));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_ONE)) {
					return new Response("拉动拉杆", "能有什么问题呢？", BatCavernsEncounterDialogue.REBEL_BASE_DOOR_NO_PASS);
					
				} else if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
					return new Response("拉动拉杆", "拉杆纹丝不动，看来你需要个密码。", null);
					
				} else if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_COMPLETE)) {
					return new Response("拉动拉杆", "你还没有完整的密码！", null);
					
				} else if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION)) {
					return new Response("拉动拉杆", "你已经拥有完整的密码，可以再次拉动拉杆了……如果你愿意的话。", REBEL_BASE_DOOR_OPENED) {
						@Override
						public void effects() {
							Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR);
							Main.game.getPlayerCell().getPlace().setName(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR.getName());
						}
					};
				}
			} else {
				return CAVERN_DARK.getResponse(responseTab, index-1);
			}
			return null;
		}
	};
			
	public static final DialogueNode REBEL_BASE_DOOR_OPENED = new DialogueNode("隐藏通道", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR);
			Main.game.getPlayerCell().getPlace().setName(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR.getName());
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_OPENED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("进入", "这个洞穴不是自然形成的，而是人工开凿的，所以它一定通向某个地方。", PlaceType.REBEL_BASE_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION));
							Main.game.getPlayer().setLocation(WorldType.REBEL_BASE, PlaceType.REBEL_BASE_ENTRANCE);
					}
				};
				
			} else if (index == 2) {
				return new Response("不要进入", "鬼知道洞里有什么……", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_ENTRANCE_EXTERIOR = new DialogueNode("隐藏洞穴", "", false) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(CAVERN_DARK.getContent());
			
			if (Main.game.getPlayer().isQuestFailed(QuestLine.SIDE_REBEL_BASE) || Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_REBEL_BASE)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_ENTRANCE_EXTERIOR_COLLAPSED"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_ENTRANCE_EXTERIOR"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getPlayer().isQuestFailed(QuestLine.SIDE_REBEL_BASE) 
					&& !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION)) {
				if (index == 1) {
					return new Response("进入", "这个洞穴不是自然形成的，而是人工开凿的，所以它一定通向某个地方。", PlaceType.REBEL_BASE_ENTRANCE.getDialogue(false)){
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION));
							Main.game.getPlayer().setLocation(WorldType.REBEL_BASE, PlaceType.REBEL_BASE_ENTRANCE);
						}
					};
				}
				
			} else { // If cave is cleared, give normal responses
				return CAVERN_DARK.getResponse(responseTab, index);
			}
			return null;
		}
	};
		
}
