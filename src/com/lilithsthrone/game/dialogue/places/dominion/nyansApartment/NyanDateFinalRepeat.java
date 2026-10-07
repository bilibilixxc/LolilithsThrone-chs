package com.lilithsthrone.game.dialogue.places.dominion.nyansApartment;

import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.NyanMum;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ClothingEmporium;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class NyanDateFinalRepeat {

	private static boolean kinky = false;
	
	private static boolean doubleDate = false;
	
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
		if(doubleDate) {
			getNyanMum().setLocation(Main.game.getPlayer(), false);
		}
	}

	public static final DialogueNode SOLO_DATE_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			doubleDate = false;
			dateBill = 0;
			getNyan().wearDress();
			((Nyan)getNyan()).wearCoat(true, true);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWeekendDated, true);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				  return new Response("餐厅", "陪妮安去餐厅。", SOLO_DATE_RESTAURANT_1_ARRIVED);
			}
			return null;
		}
	};

	public static final DialogueNode SOLO_DATE_RESTAURANT_1_ARRIVED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			((Nyan)getNyan()).wearCoat(false, false);
			travelTo(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RESTAURANT);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_1_ARRIVED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				int cost = 350;
				return new Response("狐狸葡萄园 (" + UtilText.formatAsMoney(cost, "span") + ")", "点餐单上最便宜的葡萄酒，尽管如此，它仍然是一款非常好的佳酿。", SOLO_DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("狐狸葡萄园", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_2_VULPINE"));
					}
				};
				
			} else if(index==2) {
				int cost = 600;
				return new Response("天角兽精选 (" + UtilText.formatAsMoney(cost, "span") + ")", "点餐单上较贵的一款葡萄酒。", SOLO_DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("天角兽精选", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_2_ALICORN"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 1, 60, 100));
					}
				};
				
			} else if(index==3) {
				int cost = 950;
				return new Response("妖狐的珍藏 (" + UtilText.formatAsMoney(cost, "span") + ")", "点餐单上最贵的一款葡萄酒。", SOLO_DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("妖狐的珍藏", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_2_YOUKO"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SOLO_DATE_RESTAURANT_2_WINE_ORDER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_2_WINE_ORDER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("共享瓶子", "说你会和妮安一起分享这瓶酒。", SOLO_DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_3_SHARE"));
						playerAlcoholIncrement = 0.1f;
					}
				};
				
			} else if(index==2) {
				return new Response("水", "给自己点一瓶水。", SOLO_DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=25;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_3_WATER"));
						playerAlcoholIncrement = 0f;
					}
				};
				
			} else if(index==3) {
				return new Response("啤酒", "给自己点一扎啤酒。", SOLO_DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=50;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_3_BEER"));
						playerAlcoholIncrement = 0.05f;
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SOLO_DATE_RESTAURANT_3_MAIN_DATE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.1f*2);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement*2);
			dateBill+=1400;
		}
		@Override
		public int getSecondsPassed() {
			return 100*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(dateBill), true);
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_RESTAURANT_3_MAIN_DATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开("+UtilText.formatAsMoney(dateBill, "span")+")", "付账然后陪妮安回公寓。", SOLO_DATE_END_RETURN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-dateBill));
						Main.game.getTextEndStringBuilder().append(getNyan().incrementAffection(Main.game.getPlayer(), 10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SOLO_DATE_END_RETURN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			((Nyan)getNyan()).wearCoat(false, true);
			AbstractClothing shoes = getNyan().getClothingInSlot(InventorySlot.FOOT);
			if(shoes!=null) {
				getNyan().unequipClothingIntoVoid(shoes, true, getNyan());
			}
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_END_RETURN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进入", "接受妮安的邀请进入她的公寓。", SOLO_DATE_END_RETURN_INSIDE);
			}
			return null;
		}
	};
	
	public static final DialogueNode SOLO_DATE_END_RETURN_INSIDE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			NyanApartment.setActiveSexPartner(getNyan());
			getNyan().wearLingerie(false);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "SOLO_DATE_END_RETURN_INSIDE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return NyanApartment.SOLO_SEX_FOREPLAY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOUBLE_DATE_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			doubleDate = true;
			dateBill = 0;
			getNyanMum().equipClothing();
			getNyan().wearDress();
			((Nyan)getNyan()).wearCoat(true, true);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWeekendDated, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("餐厅", "陪妮安和[nyanmum.name]去餐厅。", DOUBLE_DATE_RESTAURANT_1_ARRIVED);
			}
			return null;
		}
	};

	public static final DialogueNode DOUBLE_DATE_RESTAURANT_1_ARRIVED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RESTAURANT);
			getNyan().wearCoat(false, false);
			dateBill+=950;
		}
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_RESTAURANT_1_ARRIVED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("喝同一瓶", "同意与妮安和[nyanmum.name]喝同一瓶。", DOUBLE_DATE_RESTAURANT_2_MAIN_DATE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_RESTAURANT_2_SHARE"));
						playerAlcoholIncrement = 0.1f;
					}
				};
				
			} else if(index==2) {
				return new Response("水", "给自己点一瓶水。", DOUBLE_DATE_RESTAURANT_2_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=25;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_RESTAURANT_2_WATER"));
						playerAlcoholIncrement = 0f;
					}
				};
				
			} else if(index==3) {
				return new Response("啤酒", "给自己点一扎啤酒。", DOUBLE_DATE_RESTAURANT_2_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=50;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_RESTAURANT_2_BEER"));
						playerAlcoholIncrement = 0.05f;
					}
				};
			}
			return null;
		}
	};
	

	public static final DialogueNode DOUBLE_DATE_RESTAURANT_2_MAIN_DATE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.1f*2);
			getNyanMum().incrementAlcoholLevel(0.1f*2);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement*2);
			dateBill+=1200;
		}
		@Override
		public int getSecondsPassed() {
			return 100*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(dateBill), true);
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_RESTAURANT_2_MAIN_DATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开("+UtilText.formatAsMoney(dateBill, "span")+")", "付账然后陪猫女们回公寓。", DOUBLE_DATE_END_RETURN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-dateBill));
						Main.game.getTextEndStringBuilder().append(getNyan().incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextEndStringBuilder().append(getNyanMum().incrementAffection(Main.game.getPlayer(), 10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DOUBLE_DATE_END_RETURN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			((Nyan)getNyan()).wearCoat(false, true);
			AbstractClothing shoes = getNyan().getClothingInSlot(InventorySlot.FOOT);
			if(shoes!=null) {
				getNyan().unequipClothingIntoVoid(shoes, true, getNyan());
			}
			Main.game.getPlayer().applyFoodConsumed(10);
			Main.game.getPlayer().applyDrinkConsumed(10);
			getNyan().applyFoodConsumed(10);
			getNyan().applyDrinkConsumed(10);
			getNyanMum().applyFoodConsumed(10);
			getNyanMum().applyDrinkConsumed(10);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_END_RETURN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("休息室", " 听[nyanmum.name]的话去休息室等待……", POST_DOUBLE_DATE_APARTMENT_LOUNGE) {
					@Override
					public void effects() {
						kinky = false;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_END_RETURN_LOUNGE"));
					}
				};
				
			} else if(index==2) {
				return new Response("休息室(调皮)", "告诉[nyanmum.name]你喜欢她的“调皮主意”，听她的话在休息室乖乖等待……", POST_DOUBLE_DATE_APARTMENT_LOUNGE) {
					@Override
					public void effects() {
						kinky = true;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "DOUBLE_DATE_END_RETURN_LOUNGE_KINKY"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DOUBLE_DATE_APARTMENT_LOUNGE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM);
			Main.game.getPlayer().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "POST_DOUBLE_DATE_APARTMENT_LOUNGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("卧室", "进入[nyanmum.namePos]的卧室……", POST_DOUBLE_DATE_APARTMENT_BEDROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DOUBLE_DATE_APARTMENT_BEDROOM = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().wearLingerie(kinky);
			getNyanMum().wearLingerie(kinky);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "POST_DOUBLE_DATE_APARTMENT_BEDROOM_START"));
			if(kinky) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "POST_DOUBLE_DATE_APARTMENT_BEDROOM_KINKY"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "POST_DOUBLE_DATE_APARTMENT_BEDROOM"));
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/finalRepeatDate", "POST_DOUBLE_DATE_APARTMENT_BEDROOM_END"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return NyanApartment.DOUBLE_SEX_FOREPLAY.getResponse(responseTab, index);
		}
	};

}
