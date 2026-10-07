package com.lilithsthrone.game.dialogue.places.dominion;

import java.time.Month;

import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Felicia;
import com.lilithsthrone.game.character.npc.dominion.Fiammetta;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.feliciaApartment.FeliciaApartment;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.3.10
 * @author Innoxia
 */
public class DemonHome {
    
    private static Felicia getFelicia() {
        return ((Felicia)Main.game.getNpc(Felicia.class));
    }
	
    private static String getAdditionalDescriptions() {
    	StringBuilder sb = new StringBuilder();
    	
		if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
			sb.append(
					"<p>"
						+ "头顶上肆虐的奥术风暴让这片区域出现了大量的恶魔执法者。"
						+ "这些精英执法者不会受到奥术风暴催情之力的影响，他们会在你经过被称为恶魔之家的荒芜地区时密切关注你的动向。"
						+ "在他们的注视下，任何人都不可能攻击你，让你安安静静地继续前行……"
					+ "</p>");
		}

		if(Main.game.getDateNow().getMonth()==Month.OCTOBER) {
			sb.append(
				"<p>"
					+ "<b style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+";'>十月;</b><b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>莉莉丝月:</b><br/>"
					+ "几乎每扇窗户上都飘扬着橙色、黑色和紫色的旗帜，抬头一看，街道两旁挂满了巨大的横幅，每条横幅上都有不同的标语，歌颂着莉莉丝的统治。"
					+ "偶尔看到的恶魔通常都穿着万圣节式的服装，这无助于缓解阴森恐怖的气氛。"
				+ "</p>");
		}
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter) && Main.game.getSeason()==Season.WINTER) {
			sb.append(
				"<p>"
					+ "驯鹿化形的工人们很好地保持了御城区街道上的清洁，但屋顶、树木和灯柱顶上都覆盖了一层厚厚的白雪。"
					+ "你看到自己呼出的气在口中凝结成一小团雾气，尽管空气中的冰冷温度一目了然，但你的奥术灵气却保护着你的身体，让你感觉不到寒冷。"
				+ "</p>");
		}
		
		return sb.toString();
    }
    
	public static final DialogueNode DEMON_HOME_GATE = new DialogueNode("恶魔之家(大门)", "恶魔之家", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<p>"
						+ "这里的街道对面建有一扇巨大的铁门，将御城区的常规区域与外面的“恶魔之家”分隔开来。"
						+ "半打精英恶魔执法者驻扎在这里，密切地监视着路过的人。"
					+ "</p>"
					+ "<p>"
						+ "当你走过大门时，你注意到其中一位恶魔守卫正在盯着你"
						+ "你无视守卫锐利的目光，大步向前走去。当你顺利到达另一侧时，你长长地舒了一口气。"
					+ "</p>");
			
			UtilText.nodeContentSB.append(getAdditionalDescriptions());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET = new DialogueNode("恶魔之家", "恶魔之家", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "从大理石铺就的宽阔街道，到摄政风格建筑完美无瑕的正面，“恶魔之家”所在区域显然是御城区较为高档的区域之一。"
						+ "该地区分布着许多雕刻精美的雕像，其中绝大多数都描绘了某种形式的恶魔，"
							+ "你认为这些雕塑就是这个地区的名称由来。"
					+ "</p>"
					+ "<p>"
						+ "走在街上，你经过几个用栅栏围起来的私人花园；它们郁郁葱葱，打破了周围建筑乳白色石头外墙的单调感。"
						+ "尽管“恶魔之家”比御城区的其他大部分地区都要安静一些，但你注意到街上巡逻的执法者还是稍微多一些；"
									+ "这些证据表明，城市里富有的和有影响力的居民受到了额外的保护。"
					+ "</p>");
			
			if(Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.DOMINION_DEMON_HOME_DADDY)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.RACE_DEMON.toWebHexString()+";'>[daddy.NamePos]住处：</b><br/>"
							+ "[Daddy.NamePos]公寓位于恶魔之家的这一特定区域。"
							+ Daddy.getAvailabilityText()
						+ "</p>");
			}

			if(Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.DOMINION_DEMON_HOME_ARTHUR)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.RACE_HUMAN.toWebHexString()+";'>圣尔蒂旅馆:</b><br/>"
							+ "亚瑟的公寓楼“圣尔蒂旅馆”就位于恶魔之家的这一特殊区域。"
						+ "</p>");
			}

			UtilText.nodeContentSB.append(getAdditionalDescriptions());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET_ARTHUR = new DialogueNode("恶魔之家", "恶魔之家", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			return DEMON_HOME_STREET.getContent();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
					return new Response("圣尔蒂旅馆", "按照莉莱雅给你的指示找到亚瑟在大楼里的公寓。", DEMON_HOME_ARTHURS_APARTMENT);
					
				} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return new Response("圣尔蒂旅馆", "前往圣尔蒂旅馆公寓楼。", DEMON_HOME_ARTHURS_APARTMENT);
					
				} else {
					return null;
				}

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET_ZARANIX = new DialogueNode("恶魔之家", "恶魔之家", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			return DEMON_HOME_STREET.getContent();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_H_THE_GREAT_ESCAPE) {
					return new Response("扎拉尼克斯的家", "从亚瑟的公寓楼往下走一段路，就到了扎拉尼克斯的家，也就是斯嘉丽跟你说过的那个恶魔。", ZaranixHomeGroundFloor.OUTSIDE);
					
				} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
					return new Response("扎拉尼克斯的家", "再次访问扎拉尼克斯。", ZaranixHomeGroundFloorRepeat.OUTSIDE);
				}
				return null;

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET_DADDY = new DialogueNode("恶魔之家", "恶魔之家", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			return DEMON_HOME_STREET.getContent();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Daddy.isAvailable()) {
					return new Response("[daddy.Name]",
							Daddy.getAvailabilityText(),
							null);
					
				} else if(Main.game.getPlayer().hasCompanions()) {
					return new Response("[daddy.Name]",
							"[style.italicsBad(在有同伴的情况下，你无法与[daddy.name]会面！)]",
							null);
					
				} else {
					return new Response("[daddy.Name]",
							"前往[daddy.namePos]公寓，敲开[daddy.her]的门。",
							DaddyDialogue.MEETING) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_DADDY, Quest.DADDY_MEETING)) {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_MEETING));
							}
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_ENTRANCE);
							Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						}
					};
					
				}

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT = new DialogueNode("", "-", true) {
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/arthursApartment/apartment", "DEMON_HOME_ARTHURS_APARTMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
					return new Response("亚瑟的房间", "进入亚瑟的房间", DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM){
						@Override
						public void effects() {
							if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN));
							}
						}
					};
					
				} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return new Response("亚瑟的房间", "亚瑟已经不住在这里了……", null);
				}
				
			} else if (index == 2) {
				if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return new Response("[felicia.Name]的房间", "前往[felicia.namePos]的房间。", DEMON_HOME_ARTHURS_APARTMENT_FELICIAS_ROOM);
				}
				
			} else if (index == 0) {
				return new Response("离开", "离开建筑，回到恶魔之家的大街上", DEMON_HOME_STREET_ARTHUR);
			}
			
			return null;
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM = new DialogueNode("亚瑟的房间", "-", true) {
		@Override
		public void applyPreParsingEffects() {
            getFelicia().equipOutsideClothing();
            getFelicia().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getLabel() {
			return "亚瑟的房间";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/arthursApartment/apartment", "DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("询问犬女", "询问犬女是否知道有关亚瑟被捕的事情", DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM_END);
			}
			return null;
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM_END = new DialogueNode("亚瑟的房间", "-", true, true) {
		@Override
		public void applyPreParsingEffects() {
            getFelicia().setPlayerKnowsName(true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/arthursApartment/apartment", "DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "你在这已经做了所有能做的。转身离开恶魔之家。", DEMON_HOME_STREET_ARTHUR){
                        @Override
                        public void effects() {
                            getFelicia().equipInsideClothing();
                            getFelicia().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA, true);
                        }
                    };
			}
			return null;
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT_FELICIAS_ROOM = new DialogueNode("", "", true) {
		public int h;
        @Override
        public void applyPreParsingEffects() {
            h = Main.game.getHourOfDay();
            if(h >= 6 && h <= 14) {
                getFelicia().setLocation(Main.game.getPlayer(), false);
                getFelicia().setIntroducedToPlayer(true);
            }
        }
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getLabel() {
			return "[felicia.NamePos]房间";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/arthursApartment/apartment", "DEMON_HOME_ARTHURS_APARTMENT_FELICIAS_ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaRejectedPlayer)) {
	                 return new Response("离开", "看来菲利希亚不想和你说话。回到外面的恶魔之家", DEMON_HOME_STREET_ARTHUR);
				}
				if(h < 6 || h > 14) {
					return new Response("离开", "看起来[felicia.name]不在这里。回到恶魔之家门外。", DEMON_HOME_STREET_ARTHUR);
					
				} else if(h >= 6 && h <= 14) {
	                if (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaToldAboutArthur)) {
	                    return new Response("进入", "进入[felicia.namePos]家。", FeliciaApartment.ARTHUR_WHEREABOUTS) {
	                        @Override
	                        public void effects() {
	                            getFelicia().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA, false);
	                            Main.game.getPlayer().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA);
	                        }
	                    };
	                    
	                } else {
	                   return new Response("进入", "进入[felicia.namePos]家。", FeliciaApartment.FELICIA_GREETINGS) {
	                        @Override
	                        public void effects() {
	                            getFelicia().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA, false);
	                            Main.game.getPlayer().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA);
	                        }
	                    }; 
	                }
	                
				}
			}
			return null;
		}
	};

	public static final DialogueNode DEMON_HOME_SEX_SHOP = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
            if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A && !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_riot_witnessed")) {
            	Main.game.getNpc(Fiammetta.class).setLocation(Main.game.getPlayer());
            	Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().incrementMoney(50_000));
            }
            if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A
            		&& Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (7 * 24 * 60 * 60)) {
            	int daysToGo = 7 - (int) (((Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time")) / (24 * 60 * 60)));
            	if(daysToGo<=1) {
                	UtilText.addSpecialParsingString("[style.italicsGood(洛维耶纳奢侈品店将会在一两天后重新开张。)]", true);
            	} else {
                	UtilText.addSpecialParsingString("[style.italicsMinorGood(洛维耶纳奢侈品店将会在"+daysToGo+"天后重新开张……)]", true);
            	}
            }
            if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7B
            		&& Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (1 * 24 * 60 * 60)) {
            	UtilText.addSpecialParsingString("[style.italicsGood(洛维耶纳奢侈品店将会在一两天后重新开张。)]", true);
            }
		}
		@Override
		public boolean isTravelDisabled() {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A) {
				if(Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (7 * 24 * 60 * 60)) {
					return !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_riot_witnessed");
				} else {
					return !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen");
				}
			}
			return false;
		}
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A) {
				if(Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (7 * 24 * 60 * 60)) {
					return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DEMON_HOME_SEX_SHOP_RIOT");
				}
				if(!Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen")) {
					return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DEMON_HOME_SEX_SHOP_RIOT_ENDED");
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7B
					&& (Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (1 * 24 * 60 * 60))) {
				return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DEMON_HOME_SEX_SHOP_FIA_CLOSED");
			}
			
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DEMON_HOME_SEX_SHOP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A
						&& ((Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (7 * 24 * 60 * 60))// 7 days
							|| !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_riot_witnessed")
							|| !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen"))) {
					if(!Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_riot_witnessed")) {
						return new Response("告别",
								"向菲亚梅塔道别。",
								DEMON_HOME_SEX_SHOP_RIOT_CONTINUE) {
							@Override
							public void effects() {
								Main.game.getNpc(Fiammetta.class).returnToHome();
								Main.game.getDialogueFlags().setFlag("innoxia_doll_factory_ending_riot_witnessed", true);
								// If enough time has passed to reopen, reset timer so one more day is needed
								if(Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") >= (7 * 24 * 60 * 60)) {
									Main.game.getDialogueFlags().setSavedLong("doll_quest_choice_time", Main.game.getSecondsPassed() - (6 * 24 * 60 * 60));
								}
							}
						};
						
					} else if((Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") >= (7 * 24 * 60 * 60))
							&& !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen")) {
						return new Response("继续",
								"继续你的旅程。",
								DEMON_HOME_SEX_SHOP) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag("innoxia_doll_factory_ending_reopen_scene_seen", true);
							}
						};
						
					} else {
						int daysRemaining = 7 - (int) ((Main.game.getSecondsPassed() - (Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time"))) / (24 * 60 * 60));
						
						return new Response("洛维耶纳奢侈品店",
								"由于公众的骚乱，洛维耶纳奢侈品店现在[style.colourBad(关闭)]了。"
									+ "<br/>[style.italicsMinorGood(大约会在"+Util.intToString(daysRemaining)+"天后重新开张……)]",
								null);
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7B
						&& Main.game.getDialogueFlags().hasSavedLong("doll_quest_choice_time")
						&& (Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (1 * 24 * 60 * 60))) { // 1 day
					return new Response("洛维耶纳奢侈品店",
							"由于需要处理菲亚梅塔的文章所引发的情况，洛维耶纳奢侈品店目前[style.colourBad(关闭)]了。"
								+ "<br/>[style.italicsMinorGood(大约会在一两天后重新开张……)]",
							null);
					
				} else if(!Main.game.isHourBetween(11, 23)) {
					return new Response("洛维耶纳奢侈品店",
							"洛维耶纳奢侈品店[units.time(11)]-[units.time(23)]开放，因此目前是[style.colourBad(关闭)]的。",
							null);
					
				} else {
					return new Response("洛维耶纳奢侈品店",
							"推开前门，进入洛维耶纳奢侈品店",
							DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_enter")) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_exit"));
						}
					};
					
				}
				
			} else if(index==2) {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A
						&& ((Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (7 * 24 * 60 * 60))// 7 days
								|| !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_riot_witnessed")
								|| !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen"))
						&& !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_riot_witnessed")) {
					return null; // If talking to Fia, don't show 'Fia' action
				}
				
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A
						&& (Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") >= (7 * 24 * 60 * 60))
						&& !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen")) {
					return null; // If this is the 'riot end' scene, don't show the 'Fia' action
				}
				
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_2) {
					if(!Main.game.isHourBetween(1, 4)) {
						return new Response("寻找菲亚",
								"你需要等到[units.time(1)]到[units.time(4)]之间，再跟菲亚一起闯入洛维耶纳奢侈品店……",
								null);
						
					} else if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
						return new Response("寻找菲亚",
								"虽然你在[units.time(1)]到[units.time(4)]之间按时到达。可菲亚却由于奥术风暴无法现身……",
								null);
						
					} else {
						return new Response("寻找菲亚",
								"寻找菲亚的踪迹，准备两人一起闯入洛维耶纳奢侈品店，搜寻是否有些证据，记录了被绑架者的所在。"
									+"<br/>[style.italicsCombat(请做好准备，因为这将开启一段漫长的支线任务，期间可能会有艰难的战斗！)]",
								DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_factory_meet_fia_start"));
					}
					
				} else if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_DOLL_FACTORY, Quest.DOLL_FACTORY_7A)) {
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DOLL_FACTORY)) {
						return new Response("菲亚",
								"菲亚还在继续躲着不再露面，现在唯一能做的只有等洛维耶纳奢侈品店重新开张……"
									+ (!Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen")
											?"<br/>[style.italics(等你去过重新开张的洛维耶纳奢侈品店之后菲亚才会再次现身。)]"
											:""),
								null);
					} else {
						if(!Main.game.isHourBetween(19, 00)) {
							return new Response("菲亚",
									"菲亚这个时间不在这里……"
									+ "<br/><i>等到[units.time(19)]-[units.time(00)]之间再回来找菲亚。</i>",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag("innoxia_fia_bar_seen")) {
							return new Response("寻找菲亚",
									"你今晚已经见过菲亚了，下次见面只能等明天了。",
									null);
								
						} else {
							return new Response("菲亚",
									"在附近的酒吧寻找菲亚。",
									DialogueManager.getDialogueFromId("innoxia_places_dominion_demon_home_fia_start"));
						}
					}
					
				} else if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_DOLL_FACTORY, Quest.DOLL_FACTORY_7B) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DOLL_FACTORY)) {
					if(!Main.game.isHourBetween(16, 22)) {
						return new Response("安吉莉克丝",
								"这个时间，安吉莉克丝和她的儿子应该在外面“工作”，如果想见到他们，你得晚点再来。"
								+ "<br/><i>等到[units.time(16)]-[units.time(22)]之间再回来与安吉莉克丝和她的儿子们见面。</i>",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_angelixx_apartment_visited")) {
							return new Response("安吉莉克丝",
									"你今晚已经访问过安吉莉克丝的住处了，下次见面只能等明天了。",
									null);
							
					} else {
						return new Response("安吉莉克丝",
								"去安吉莉克丝的住处拜访她和她的儿子们。",
								DialogueManager.getDialogueFromId("innoxia_places_dominion_angelixx_apartment_generic_visit"));
					}
				}
			}
			return null;
		}
	};
	

	public static final DialogueNode DEMON_HOME_SEX_SHOP_RIOT_CONTINUE = new DialogueNode("", "", false, true) {
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DEMON_HOME_SEX_SHOP_RIOT_CONTINUE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEMON_HOME_SEX_SHOP.getResponse(responseTab, index);
		}
	};
}
