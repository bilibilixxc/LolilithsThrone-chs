package com.lilithsthrone.game.dialogue.places.dominion.nyansApartment;

import com.lilithsthrone.game.character.body.CoverableArea;
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
public class NyanRepeatDate {

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
			getNyan().wearDress();
			((Nyan)getNyan()).wearCoat(true, true);
			getNyanMum().setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true); // So Nyanmum isn't visible
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWeekendDated, true);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_START");
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
			((Nyan)getNyan()).wearCoat(false, false);
			travelTo(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RESTAURANT);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM, true); // Return Nyanmum to bedroom
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_1_ARRIVED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				int cost = 350;
				return new Response("狐狸葡萄园("+UtilText.formatAsMoney(cost, "span")+")", "点餐单上最便宜的葡萄酒，尽管如此，它仍然是一款非常好的佳酿。", DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("狐狸葡萄园", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_2_VULPINE"));
					}
				};
				
			} else if(index==2) {
				int cost = 600;
				return new Response("天角兽精选 ("+UtilText.formatAsMoney(cost, "span")+")", "点餐单上更昂贵的葡萄酒之一。", DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("天角兽精选", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_2_ALICORN"));
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
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_2_YOUKO"));
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_2_WINE_ORDER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("共享美酒", "说你会和妮安共享这瓶酒。", DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_3_SHARE"));
						playerAlcoholIncrement = 0.1f;
					}
				};
				
			} else if(index==2) {
				return new Response("水", "给自己点一瓶水。", DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=25;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_3_WATER"));
						playerAlcoholIncrement = 0f;
					}
				};
				
			} else if(index==3) {
				return new Response("啤酒", "给自己点一扎啤酒。", DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=50;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_3_BEER"));
						playerAlcoholIncrement = 0.05f;
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_3_MAIN_DATE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			dateBill+=1200;
			getNyan().incrementAlcoholLevel(0.1f*2);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement*2);
		}
		@Override
		public int getSecondsPassed() {
			return 100*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(dateBill), true);
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_3_MAIN_DATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开("+UtilText.formatAsMoney(dateBill, "span")+")", "支付账单，然后陪妮安回她的公寓楼。", DATE_END_RETURN) {
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
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_END_RETURN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("[nyanmum.Name]", "是时候和[nyanmum.name]再次见面了……", POST_DATE_APARTMENT_INTERVIEW_2_FOOD);
			}
			return null;
		}
	};
	

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_2_FOOD = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("沙拉", "告诉[nyanmum.name]，妮安最喜欢的食物是沙拉。", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() { failEffects("沙拉"); }
				};
			} else if(index==2) {
				return new Response("提斯普拉金鲷鱼", "告诉[nyanmum.name]，妮安最喜欢的食物是一道鱼做的菜，名为“提斯普拉金鲷鱼”。", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 1, -20, 20));
					}
				};
			} else if(index==3) {
				return new Response("披萨", "告诉[nyanmum.name]，妮安最喜欢的食物是披萨。", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() { failEffects("披萨"); }
				};
			} else if(index==4) {
				return new Response("猫薄荷", "告诉[nyanmum.name]，妮安最喜欢的食物是‘荆芥’，又名猫薄荷。", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() { failEffects("猫薄荷"); }
				};
			} else if(index==5) {
				return new Response("冰淇淋", "告诉[nyanmum.name]，妮安最喜欢的食物是冰淇淋。", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("安排日程", "告诉[nyanmum.name]，妮安以前每天早上都为她安排当天的日程。", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() { failEffects("为她安排日程"); }
				};
			} else if(index==2) {
				return new Response("亲吻她", "告诉[nyanmum.name]，妮安以前每天早上都会亲吻她。", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() { failEffects("吻她"); }
				};
			} else if(index==3) {
				return new Response("床上早餐", "告诉[nyanmum.name]，妮安以前每天早上都会做好早餐送到她床上。", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() { failEffects("做好早餐送到她床上"); }
				};
			} else if(index==4) {
				return new Response("烤点心", "告诉[nyanmum.name]，妮安以前每天早上都会为她烤点心。", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 2, -20, 20));
					}
				};
			} else if(index==5) {
				return new Response("弹钢琴", "告诉[nyanmum.name]，妮安以前每天早上都会为她弹钢琴。", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("太空", "告诉[nyanmum.name]，妮安的商店以前叫做“太空”。", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() { failEffects("太空"); }
				};
			} else if(index==2) {
				return new Response("时尚小垃圾", "告诉[nyanmum.name]，妮安的商店以前叫做“时尚小垃圾”。", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() { failEffects("时尚小垃圾"); }
				};
			} else if(index==3) {
				return new Response("连衣裙专卖店", "告诉[nyanmum.name]，妮安的商店以前叫做“连衣裙专卖店”。", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() { failEffects("连衣裙专卖店"); }
				};
			} else if(index==4) {
				return new Response("[nyanmum.NamePos]的最佳选择", "告诉[nyanmum.name]，妮安的商店以前叫做'[nyanmum.NamePos]的最佳选择'。", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() { failEffects("[nyanmum.NamePos]的最佳选择"); }
				};
			} else if(index==5) {
				return new Response("金丝银线", "告诉[nyanmum.name]，妮安的商店以前叫做“金丝银线”。", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE_SUCCESS"));
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("雏菊", "告诉[nyanmum.name]，妮安最喜欢的花是雏菊。", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 4, -20, 20));
					}
				};
			} else if(index==2) {
				return new Response("郁金香", "告诉[nyanmum.name]，妮安最喜欢的花是郁金香。", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() { failEffects("郁金香"); }
				};
			} else if(index==3) {
				return new Response("玫瑰", "告诉[nyanmum.name]，妮安最喜欢的花是玫瑰。", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() { failEffects("玫瑰"); }
				};
			} else if(index==4) {
				return new Response("报春花", "告诉[nyanmum.name]，妮安最喜欢的花是报春花。", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() { failEffects("报春花"); }
				};
			} else if(index==5) {
				return new Response("绣球花", "告诉[nyanmum.name]，妮安最喜欢的花是绣球花。", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("梦魇礼拜堂", "告诉[nyanmum.name]，妮安最喜欢的书叫做“梦魇礼拜堂”。", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() { failEffects("梦魇礼拜堂"); }
				};
			} else if(index==2) {
				return new Response("孔雀的进化", "告诉[nyanmum.name]，妮安最喜欢的书叫做“孔雀的进化”。", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() { failEffects("孔雀的进化"); }
				};
			} else if(index==3) {
				return new Response("六月的一天", "告诉[nyanmum.name]，妮安最喜欢的书叫做“六月的一天”。", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 5, -20, 20));
					}
				};
			} else if(index==4) {
				return new Response("八之符号", "告诉[nyanmum.name]，妮安最喜欢的书叫做“八之符号”。", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() { failEffects("八之符号"); }
				};
			} else if(index==5) {
				return new Response("帽子之主", "告诉[nyanmum.name]，妮安最喜欢的书叫做“帽子之主”。", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() { failEffects("帽子之主"); }
				};
			}
			return null;
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_PASS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) { 
				return new Response("拥抱", "看起来妮安现在真的需要一个安慰的拥抱。", NyanFirstDate.POST_DATE_APARTMENT_BEDROOM);
			}
			return null;
		}
	};
	
}
