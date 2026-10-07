package com.lilithsthrone.game.dialogue.places.dominion.nyansApartment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.NyanMum;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ClothingEmporium;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.nyan.SMNyanSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class NyanFirstDate {

	private static int dateBill;
	
	private static float playerAlcoholIncrement = 0;
	
	private static Nyan getNyan() {
		return ((Nyan)Main.game.getNpc(Nyan.class));
	}

	private static NyanMum getNyanMum() {
		return ((NyanMum)Main.game.getNpc(NyanMum.class));
	}

	private static void travelTo(AbstractWorldType worldType, AbstractPlaceType placeType) {
		Main.game.getPlayer().setLocation(worldType, placeType);
		getNyan().setLocation(Main.game.getPlayer(), false);
	}

	public static final DialogueNode DATE_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			dateBill = 0;
			getNyanMum().setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true); // So Nyanmum isn't visible
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWeekendDated, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("休息室", "跟随妮安进入她的休息室。", DATE_START_LOUNGE);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_START_LOUNGE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_START_LOUNGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("妮安", "妮安回来了……", DATE_START_LOUNGE_NYAN_DRESS);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_START_LOUNGE_NYAN_DRESS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().wearDress();
			getNyan().wearCoat(true, true);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_START_LOUNGE_NYAN_DRESS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("餐厅", "陪妮安去餐厅。", DATE_RESTAURANT_1_ARRIVED);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_1_ARRIVED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RESTAURANT);
			getNyan().wearCoat(false, false);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM, true); // Return Nyanmum to bedroom
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_1_ARRIVED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				int cost = 350;
				return new Response("狐狸庄园 (" + UtilText.formatAsMoney(cost, "span") + ")","点菜单上最便宜的葡萄酒，尽管如此，它仍然是一款非常好的佳酿。", DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("狐狸葡萄园", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_2_VULPINE"));
					}
				};
				
			} else if(index==2) {
				int cost = 600;
				return new Response("天角兽精选 (" + UtilText.formatAsMoney(cost, "span") + ")", "点菜单上较贵的一款葡萄酒。", DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("天角兽精选", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_2_ALICORN"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 1, 60, 100));
					}
				};
				
			} else if(index==3) {
				int cost = 950;
				return new Response("妖狐的珍藏("+UtilText.formatAsMoney(cost, "span")+")", "点餐单上最昂贵的葡萄酒。", DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("妖狐的珍藏", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_2_YOUKO"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_2_WINE_ORDER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_2_WINE_ORDER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("共享美酒", "说你会和妮安共享这瓶酒。", DATE_RESTAURANT_3_PLAYER_DRINK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_3_SHARE"));
						playerAlcoholIncrement = 0.1f;
					}
				};
				
			} else if(index==2) {
				return new Response("水", "给自己点一瓶水。", DATE_RESTAURANT_3_PLAYER_DRINK) {
					@Override
					public void effects() {
						dateBill+=25;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_3_WATER"));
						playerAlcoholIncrement = 0f;
					}
				};
				
			} else if(index==3) {
				return new Response("啤酒", "给自己点一扎啤酒。", DATE_RESTAURANT_3_PLAYER_DRINK) {
					@Override
					public void effects() {
						dateBill+=50;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_3_BEER"));
						playerAlcoholIncrement = 0.05f;
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_3_PLAYER_DRINK = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_3_PLAYER_DRINK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("点餐", "下单并等待开胃菜送上来。", DATE_RESTAURANT_4_STARTERS) {
					@Override
					public void effects() {
						dateBill+=700;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_4_ORDER"));
					}
				};
				
			} else if(index==2) {
				return new Response("点餐(蜡烛)", "点餐并等待开胃菜送上来。同时请求在你们的桌子上点燃浪漫的蜡烛。", DATE_RESTAURANT_4_STARTERS) {
					@Override
					public void effects() {
						dateBill+=700;
						dateBill+=10;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_4_ORDER_CANDLE"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 1, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_4_STARTERS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.1f);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_4_STARTERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("交谈", "与妮安闲聊，问问她今天过得如何。", DATE_RESTAURANT_5_MAIN_MEAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_5_TALK"));
					}
				};
				
			} else if(index==2) {
				return new Response("恭维", "恭维妮安看起来很漂亮。", DATE_RESTAURANT_5_MAIN_MEAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_5_COMPLIMENT"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
				
			} else if(index==3) {
				return new Response("说骚话", "告诉妮安你想在今晚脱下她的裤子。<br/>[style.italicsMinorBad(你简直难以想象她对此反应多大……)]", DATE_RESTAURANT_5_MAIN_MEAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_5_DIRTY"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -2, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_5_MAIN_MEAL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.1f);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_5_MAIN_MEAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("谈谈食物", "与妮安闲聊，说说你的饭尝起来怎么样。", DATE_RESTAURANT_6_DESSERT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_6_TALK"));
					}
				};
				
			} else if(index==2) {
				return new Response("调情", "和妮安调情，充满深意地暗示你和她吃完晚餐后，还想加餐。", DATE_RESTAURANT_6_DESSERT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_6_FLIRT"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
				
			} else if(index==3) {
				return new Response("用脚轻轻摩擦", "在妮安的腿上磨蹭你的[pc.foot]，告诉她你等不及要操她了。<br/>[style.italicsBad(你简直难以想象她对此反应多大！)]", DATE_RESTAURANT_6_DESSERT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_6_FOOTSIE"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -5, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_6_DESSERT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.05f);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement/2);
		}
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_6_DESSERT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("点心", "为你和妮安点各自的点心。", DATE_7_END) {
					@Override
					public void effects() {
						dateBill+=500;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_7_INDIVIDUAL"));
					}
				};
				
			} else if(index==2) {
				return new Response("共享点心", "点一份甜点，和妮安分享。", DATE_7_END) {
					@Override
					public void effects() {
						dateBill+=250;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_7_SHARE"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_7_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanRestaurantDateCompleted, true);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(dateBill), true);
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_7_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开("+UtilText.formatAsMoney(dateBill, "span")+")", "付账然后陪妮安回她的公寓。", DATE_END_RETURN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-dateBill));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_END_RETURN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.DOMINION, PlaceType.DOMINION_NYAN_APARTMENT);
			((Nyan)getNyan()).wearCoat(true, false);
			Main.game.getPlayer().applyFoodConsumed(10);
			Main.game.getPlayer().applyDrinkConsumed(10);
			getNyan().applyFoodConsumed(10);
			getNyan().applyDrinkConsumed(10);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_END_RETURN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进入", "接受妮安的邀请进入她的公寓。", POST_DATE_APARTMENT);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			((Nyan)getNyan()).wearCoat(false, true);
			AbstractClothing shoes = getNyan().getClothingInSlot(InventorySlot.FOOT);
			if(shoes!=null) {
				getNyan().unequipClothingIntoVoid(shoes, true, getNyan());
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("休息室", "进入休息室……", POST_DATE_APARTMENT_LOUNGE);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_LOUNGE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_LOUNGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("介绍", "向妮安的妈妈介绍自己。", POST_DATE_APARTMENT_INTERVIEW_1_SUPPORT);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_1_SUPPORT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyanMum().setPlayerKnowsName(true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumIntroduced, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_1_SUPPORT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<1_000_000) {
					return new Response("财富", "你需要至少拥有一百万火币，才能凭借你的财富给[nyanmum.name]留下深刻印象！", null);
				}
				return new Response("财富", "告诉[nyanmum.name]你有相当可观数量的财富，所以能轻易支持妮安。", POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_WEALTH"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_LILAYA_REVEAL"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 5, -20, 100));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getSlavesOwned().size()<20) {
					return new Response("奴隶", "你需要至少拥有二十个奴隶，才能让[nyanmum.name]印象深刻！", null);
				}
				return new Response("奴隶",
						"告诉[nyanmum.name]你拥有很多奴隶，所以支持妮安的开销对你来说不成问题。",
						POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_SLAVES"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_LILAYA_REVEAL"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 5, -20, 100));
					}
				};
				
			} else if(index==3) {
				if(Main.game.getPlayer().getSubspeciesOverrideRace()!=Race.DEMON) {
					return new Response("恶魔", "你不是恶魔，所以你没法通过这种方式让[nyanmum.name]记住你……", null);
				}
				return new Response("是[#pc.getLilinMother().getName()]的[pc.daughter]",
						"告诉[nyanmum.name]你可是个莉琳的直系[pc.daughter]，你永远不会为支持妮安发愁。",
						POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_DEMON"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 10, -20, 100));
					}
				};
				
			} else if(index==4) {
				if(!Util.newArrayListOfValues(
						Occupation.CHEF,
						Occupation.MUSICIAN,
						Occupation.ARISTOCRAT
						).contains(Main.game.getPlayer().getOccupation())) {
					return new Response("职业", "你没有一个适合让[nyanmum.name]印象深刻的职业……", null);
				}
				return new Response("职业",
						Main.game.getPlayer().getOccupation()==Occupation.ARISTOCRAT
							?"告诉[nyanmum.name]你是雍容华贵的名门望族成员，以你的财富和地位足以支持妮安。"
							:"告诉[nyanmum.name]你有一份高薪的[pc.job]，所以你能通过工资支持妮安。",
						POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_OCCUPATION"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_LILAYA_REVEAL"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 1, -20, 100));
					}
				};
				
			} else if(index==5) {
				return new Response("尽力而为", "告诉[nyanmum.name]你会尽力而为……", POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_DO_BEST"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_LILAYA_REVEAL"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_2_FOOD = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("沙拉", "告诉[nyanmum.name]，妮安最喜欢的食物是沙拉。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("沙拉"); }
				};
			} else if(index==2) {
				return new Response("提斯普拉金鲷鱼", "告诉[nyanmum.name]，妮安最喜欢的食物是一道鱼做的菜，名为“提斯普拉金鲷鱼”。", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 1, -20, 20));
					}
				};
			} else if(index==3) {
				return new Response("披萨", "告诉[nyanmum.name]，妮安最喜欢的食物是披萨。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("披萨"); }
				};
			} else if(index==4) {
				return new Response("猫薄荷", "告诉[nyanmum.name]，妮安最喜欢的食物是‘荆芥’，又名猫薄荷。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("猫薄荷"); }
				};
			} else if(index==5) {
				return new Response("冰淇淋", "告诉[nyanmum.name]，妮安最喜欢的食物是冰淇淋。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("冰激凌"); }
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_3_HISTORY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("安排日程", "告诉[nyanmum.name]，妮安以前每天早上都为她安排当天的日程。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("为她安排日程"); }
				};
			} else if(index==2) {
				return new Response("亲吻她", "告诉[nyanmum.name]，妮安以前每天早上都会亲吻她。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("吻她"); }
				};
			} else if(index==3) {
				return new Response("床上早餐", "告诉[nyanmum.name]，妮安以前每天早上都会做好早餐送到她床上。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("做好早餐送到她床上"); }
				};
			} else if(index==4) {
				return new Response("烤点心", "告诉[nyanmum.name]，妮安以前每天早上都会为她烤点心。", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 2, -20, 20));
					}
				};
			} else if(index==5) {
				return new Response("弹钢琴", "告诉[nyanmum.name]，妮安以前每天早上都会为她弹钢琴。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("给她弹钢琴"); }
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_4_STORE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("太空", "告诉[nyanmum.name]，妮安的店以前叫做 “太空”。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("太空"); }
				};
			} else if(index==2) {
				return new Response("时尚小垃圾", "告诉[nyanmum.name]，妮安的商店以前叫做“时尚小垃圾”。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("时尚小垃圾"); }
				};
			} else if(index==3) {
				return new Response("连衣裙专卖店", "告诉[nyanmum.name]，妮安的商店以前叫做“连衣裙专卖店”。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("连衣裙专卖店"); }
				};
			} else if(index==4) {
				return new Response("[nyanmum.NamePos]的最佳选择", "告诉[nyanmum.name]，妮安的商店以前叫做'[nyanmum.NamePos]的最佳选择'。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("[nyanmum.NamePos]的最佳选择"); }
				};
			} else if(index==5) {
				return new Response("金丝银线", "告诉[nyanmum.name]，妮安的商店以前叫做“金丝银线”。", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 3, -20, 20));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_5_FLOWER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("雏菊", "告诉[nyanmum.name]，妮安最喜欢的花是雏菊。", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 4, -20, 20));
					}
				};
			} else if(index==2) {
				return new Response("郁金香", "告诉[nyanmum.name]，妮安最喜欢的花是郁金香。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("郁金香"); }
				};
			} else if(index==3) {
				return new Response("玫瑰花", "告诉[nyanmum.name]，妮安最喜欢的花是玫瑰花。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("玫瑰"); }
				};
			} else if(index==4) {
				return new Response("报春花", "告诉[nyanmum.name]，妮安最喜欢的花是报春花。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("报春花"); }
				};
			} else if(index==5) {
				return new Response("绣球花", "告诉[nyanmum.name]，妮安最喜欢的花是绣球花。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("绣球花"); }
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_6_BOOK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setKnowsCharacterArea(CoverableArea.VAGINA, getNyanMum(), true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("梦魇礼拜堂", "告诉[nyanmum.name]，妮安最喜欢的书叫做“梦魇礼拜堂”。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("梦魇礼拜堂"); }
				};
			} else if(index==2) {
				return new Response("孔雀的进化", "告诉[nyanmum.name]，妮安最喜欢的书叫做“孔雀的进化”。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("孔雀的进化"); }
				};
			} else if(index==3) {
				return new Response("六月的某天", "告诉[nyanmum.name]，妮安最喜欢的书叫做 “六月的某天”。", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 5, -20, 20));
					}
				};
			} else if(index==4) {
				return new Response("八之符号", "告诉[nyanmum.name]，妮安最喜欢的书叫做“八之符号”。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("八之符号"); }
				};
			} else if(index==5) {
				return new Response("帽子之主", "告诉[nyanmum.name]，妮安最喜欢的书叫做“帽子之主”。", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("帽子之主"); }
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_FAIL = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_FAIL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "由于你不想冒险破坏你和妮安的关系，实际上，别无选择，只能遵从[nyanmum.name]的话离开……", POST_DATE_APARTMENT_INTERVIEW_FAIL_LEAVE);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_FAIL_LEAVE = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM);
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NYAN_APARTMENT);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_FAIL_LEAVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index); //TODO test
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_PASS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumInterviewPassed, true);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_PASS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) { 
				return new Response("拥抱", "看起来妮安现在真的需要一个安慰的拥抱。", POST_DATE_APARTMENT_BEDROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("亲吻", "给妮安她想要的……", POST_DATE_APARTMENT_BEDROOM_KISS);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_KISS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			AbstractClothing dress = getNyan().getClothingInSlot(InventorySlot.TORSO_UNDER);
			if(dress!=null) {
				getNyan().unequipClothingIntoVoid(dress, true, getNyan());
			}
			getNyan().removePersonalityTrait(PersonalityTrait.STUTTER);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_KISS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("舔阴", "舔舐她的阴部，给淫荡小猫女想要的。",
						true, true,
						new SMNyanSex(
								SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotSitting.SITTING))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
								}
							}
						},
						null,
						null,
						POST_DATE_APARTMENT_BEDROOM_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_KISS_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("指交", "温柔地把妮安压在床上，边亲吻边指交。",
						true, true,
						new SMNyanSex(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN_TWO)),
								Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
								}
							}
							@Override
							public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						POST_DATE_APARTMENT_BEDROOM_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_KISS_FINGERING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueMouth.KISS_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), FingerVagina.FINGERING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_POST_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> receiveOralResponses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
				if(!penisAccess) {
					receiveOralResponses.add(new Response("六九式"+(Main.game.getPlayer().hasVagina()?"(口交)":""), "因为你不能使用自己的阴茎。所以你没法和妮安玩六九式！", null));

				} else if(Main.game.getPlayer().isTaur()) {
					receiveOralResponses.add(new Response("六九式"+(Main.game.getPlayer().hasVagina()?" (口交)":""), "因为你[pc.legRace]的下半身，你不能采用这个体位！", null));
						
				} else {
					receiveOralResponses.add(
							new ResponseSex("六九式"+(Main.game.getPlayer().hasVagina()?" (口交)":""), "让妮安跨坐在你脸上，屈身吸吮你的鸡巴，你再次舔舐着她的下体。",
									true, true,
									new SMNyanSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotLyingDown.SIXTY_NINE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
										}
									},
									null,
									null,
									POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_SIXTY_NINE_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					receiveOralResponses.add(new Response("六九式"+(Main.game.getPlayer().hasPenisIgnoreDildo()?"(舔阴)":""), "你的阴部无法被触及，所以你没法和妮安玩六九式！", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					receiveOralResponses.add(new Response("六九式"+(Main.game.getPlayer().hasVagina()?" (舔阴)":""), "因为你[pc.legRace]的下半身，你不能采用这个体位！", null));
						
				} else {
					receiveOralResponses.add(
							new ResponseSex("六九式"+(Main.game.getPlayer().hasPenisIgnoreDildo()?" (舔阴)":""), "让妮安跨坐在你脸上，屈下腰舔舐着你的下面，你也回敬她。",
									true, true,
									new SMNyanSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotLyingDown.SIXTY_NINE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
										}
									},
									null,
									null,
									POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_SIXTY_NINE_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
				if(!penisAccess) {
					receiveOralResponses.add(new Response("被口交", "因为你不能使用自己的阴茎，所以你没法让妮安吸你的鸡巴！", null)); 
				} else {
					receiveOralResponses.add(
							new ResponseSex("被口交", "和妮安交换位置，让她吮吸你的鸡巴。",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotLyingDown.MISSIONARY_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
										}
									},
									null,
									null,
									POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					receiveOralResponses.add(new Response("被舔阴", "因为你的阴部无法被触及，所以你不能让妮安给你舔阴！", null)); 
				} else {
					receiveOralResponses.add(
							new ResponseSex("被舔阴", "和妮安交换位置，让她尝尝你的味道。",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotLyingDown.MISSIONARY_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
										}
									},
									null,
									null,
									POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			
			if(index==1) {
				return new Response("睡觉", "让妮安休息，和她一起去床上睡觉。", POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP_START"));
						getNyan().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
						Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("颜面骑乘", "让妮安骑在你脸上，你再次舔舐她的下体。", 
								true, true,
								new SMNyanSex(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
										Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotLyingDown.FACE_SITTING))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										}
									}
									@Override
									public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
										return Util.newHashMapOfValues(
												new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
									}
								},
								null,
								null,
								POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_FACE_SITTING")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true));
							}
						};
			
			} else if(index>0 && index-3<receiveOralResponses.size()) {
				return receiveOralResponses.get(index-3);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX = new DialogueNode("结束", "妮安看起来筋疲力尽……", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("睡觉", "让妮安休息，和她一起去床上睡觉。", POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX_SLEEP_START"));// These names...
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
			Main.game.getPlayer().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			getNyan().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			getNyanMum().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("厨房", "去厨房找妮安", POST_DATE_APARTMENT_BEDROOM_MORNING);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_MORNING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().equipClothing();
			getNyan().wearApron(true);
			getNyanMum().wearCasual();
			Main.game.getPlayer().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_MORNING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("面点", "吃掉妮安为你和[nyanmum.name]做的面点。", POST_DATE_APARTMENT_BEDROOM_MORNING_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_MORNING_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_MORNING_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("再见", "亲亲妮安，和她告别，并告诉她你们很快会再见。", POST_DATE_NO_CONTENT_END) {
					@Override
					public void effects() {
						getNyan().wearApron(false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_MORNING_END_LEAVE"));
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_NO_CONTENT_END = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NYAN_APARTMENT);
		}
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
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
}
