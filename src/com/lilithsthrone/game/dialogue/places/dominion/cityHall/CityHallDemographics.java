package com.lilithsthrone.game.dialogue.places.dominion.cityHall;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Vanessa;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.dominion.vanessa.SMVanessaOral;
import com.lilithsthrone.game.sex.managers.dominion.vanessa.SMVanessaOralFootjob;
import com.lilithsthrone.game.sex.managers.dominion.vanessa.SMVanessaSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.2
 * @version 0.3.2
 * @author Innoxia
 */
public class CityHallDemographics {
	
	private static void applyLeavingEffects() {
		Main.game.getNpc(Vanessa.class).applyWash(true, true, null, 0);
		Main.game.getNpc(Vanessa.class).equipClothing();
	}
	
	public static final DialogueNode CITY_HALL_DEMOGRAPHICS_ENTRANCE = new DialogueNode("人口统计局", "，", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "CITY_HALL_DEMOGRAPHICS_ENTRANCE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("响铃",
						Main.game.getPlayer().hasCompanions()
							?UtilText.parse(Main.game.getPlayer().getMainCompanion(), "告诉[npc.name]去外面等待, 然后按响[vanessa.namePos]桌上的铃铛，等她过来。")
							:"按响[vanessa.namePos]桌上的铃铛并等她出现。",
						CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "CITY_HALL_DEMOGRAPHICS_RING_BELL"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaIntroduced, true);
						
						if(Main.game.getNpc(Vanessa.class).isVisiblyPregnant()) {
							Main.game.getNpc(Vanessa.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
						
						for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
							character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
						}
					}
				};
				
			} else if (index == 0) {
				return new Response("离开", "决定不打扰[vanessa.name]，你返回走廊。", CityHall.CITY_HALL_CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
						applyLeavingEffects();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "CITY_HALL_DEMOGRAPHICS_ENTRANCE_TURN_BACK"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_DEMOGRAPHICS_MAIN = new DialogueNode("人口统计局", "-", true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("修改姓名", "询问[vanessa.name]有关于你改名的事。", NAME_CHANGE);
				
			} else if (index == 2) {
				if(Main.game.getPlayer().hasItemType(ItemType.OFFSPRING_MAP)) {
					return new Response("后代地图", "你已经从[vanessa.name]购买过后代地图，而且你不需要另一份。", null);
					
				} else {
					return new Response("后代地图", "询问[vanessa.name]如何获得能够显示后代位置的地图。", OFFSPRING_MAP);
				}
				
			} else if (index == 3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyHelped)) {
					return new Response("提供帮助", "你今天已经帮[vanessa.name]做过工作了。", null);
				}
				return new Response("提供帮助", "询问[vanessa.name]需不需要帮助。", OFFER_HELP) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyHelped, true);
						Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_ARCHIVES);
						Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_ARCHIVES);
						
						for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
							character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
						}
					}
				};
				
			} else if (index == 4 && (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyHelped) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaMassaged))) {
				if(Main.game.isFootContentEnabled()) { // Foot massage:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyMassage)) {
						return new Response("足部按摩", "你今天已经给[vanessa.name]做过足部按摩。明天之前她都没有这个打算了。", null);
					}
					
					return new Response("足部按摩", "提出要给[vanessa.name]足部按摩。", FOOT_MASSAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyMassage, true);
							
							try {
								Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.FOOT), true, Main.game.getNpc(Vanessa.class));
							} catch(Exception ex) { System.err.println("Vanessa is not wearing any shoes!"); }
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				} else { // Shoulder massage:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyMassage)) {
						return new Response("肩部按摩", "你今天已经给[vanessa.name]按摩过了。明天之前她都没有这个打算了。", null);
					}
					
					return new Response("肩部按摩", "提出要给[vanessa.name]肩部按摩。", SHOULDER_MASSAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyMassage, true);

							try {
								Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.TORSO_OVER), true, Main.game.getNpc(Vanessa.class));
							} catch(Exception ex) { System.err.println("Vanessa is not wearing a cardigan!"); }
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
				}
				
			} else if (index == 5
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyHelped)
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaFucked)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyMassage)) {
				if(Main.game.isFootContentEnabled()) { // Foot massage:
					return new Response("让她求你", "让[vanessa.name]求你，你才愿意给她足部按摩。", FOOT_MASSAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyMassage, true);
							
							try {
								Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.FOOT), true, Main.game.getNpc(Vanessa.class));
							} catch(Exception ex) { System.err.println("Vanessa is not wearing any shoes!"); }
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE_BEG"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				} else { // Shoulder massage:
					return new Response("让她求你", "让[vanessa.name]求你，你才愿意给她肩部按摩。", SHOULDER_MASSAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyMassage, true);

							try {
								Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.TORSO_OVER), true, Main.game.getNpc(Vanessa.class));
							} catch(Exception ex) { System.err.println("Vanessa is not wearing a cardigan!"); }
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE_BEG"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
				}
				
			} else if (index == 6) {
				return new Response("登记", "询问[vanessa.name]是负责哪一类文件的登记工作的。", QUESTION_CATALOGUING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "QUESTION_CATALOGUING"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaAskedAboutCatalogue, true);
					}
				};
				
			} else if (index == 7) {
				return new Response("独自一人", "询问[vanessa.name]为什么这个部门就她一个人工作。", QUESTION_SOLITARY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "QUESTION_SOLITARY"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaAskedAboutSolitary, true);
					}
				};
				
			} else if(index==10 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaHelped)) {
				return new ResponseEffectsOnly("称呼她为: [vanessa.Name]", "循环选择对这个成熟狐女的称呼，“坎宁安女士”或者“凡妮莎”。") {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).setPlayerKnowsName(!Main.game.getNpc(Vanessa.class).isPlayerKnowsName());
					}
				};
				
			} if (index == 0) {
				return new Response("离开", "决定不打扰[vanessa.name]，你返回走廊。", CityHall.CITY_HALL_CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
						applyLeavingEffects();
						Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_ARCHIVES);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode QUESTION_CATALOGUING = new DialogueNode("人口统计局", "-", true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==6) {
				return new Response("登记", "你刚刚问过[vanessa.name]姓名是如何登记的。", null); 
			}
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode QUESTION_SOLITARY = new DialogueNode("人口统计局", "-", true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==7) {
				return new Response("独自一人", "你刚刚问过[vanessa.name]为什么这个部门就她一个人工作。", null); 
			}
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFER_HELP = new DialogueNode("人口统计局", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFER_HELP");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaHelped)) {
				if (index == 1) {
					return new Response("工作", "跟[vanessa.name]一起登记这些文件。", OFFER_HELP_FINISH) {
						@Override
						public void effects() {
							Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 10));
							
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
							}
						}
					};	
					
				}
				
			} else {
				if (index == 1) {
					return new Response("凡妮莎",
							"告诉这个成熟的狐女，你很乐意称呼她为“凡妮莎”，然后跟她一起登记文件。"
									+ "<br/><i>(你可以随后随意切换称呼，“凡妮莎”或者“坎宁安女士”。)</i>",
							OFFER_HELP_FINISH) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFER_HELP_VANESSA"));
							
							Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 15));
							
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
							}
							Main.game.getNpc(Vanessa.class).setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaHelped, true);
						}
					};	
					
				} else if(index==2) {
					return new Response("坎宁安女士",
							"告诉这个成熟的狐女，你很乐意称呼她为“坎宁安女士”，然后跟她一起登记文件。"
									+ "<br/><i>(你可以随后随意切换称呼，“凡妮莎”或者“坎宁安女士”。)</i>",
							OFFER_HELP_FINISH) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFER_HELP_MS_CUNNINGHAM"));
							
							Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 10));
							
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
							}
							Main.game.getNpc(Vanessa.class).setPlayerKnowsName(false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaHelped, true);
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode OFFER_HELP_FINISH = new DialogueNode("人口统计局", "-", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFER_HELP_FINISH");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode FOOT_MASSAGE = new DialogueNode("人口统计局", "-", true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("裸足", "脱下[vanessa.namePos]的连裤袜，给她光着脚按摩。", BARE_FOOT_MASSAGE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaMassaged, true);
						try {
							Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.SOCK), true, Main.game.getNpc(Vanessa.class));
						} catch(Exception ex) { System.err.println("Vanessa is not wearing pantyhose!"); }
						
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if (index == 2) {
				return new Response("停止", "停止给[vanessa.name]足部按摩。", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaMassaged, true);
						Main.game.getNpc(Vanessa.class).endSex(); // Cleans & replaces clothing
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE_STOP"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BARE_FOOT_MASSAGE = new DialogueNode("人口统计局", "-", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "BARE_FOOT_MASSAGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("口交", "你无法使用自己的嘴巴，所以不能给[vanessa.name]舔阴……", null);
				}
				return new ResponseSex("口交",
						"慢慢挪到[vanessa.namePos]的双腿之间，开始给她舔阴。",
						true, true,
						new SMVanessaOral(
								SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						END_ORAL_SEX,
						UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_ORAL")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Vanessa.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
					}
				};
				
			} else if (index == 2) {
				if(!Main.game.getPlayer().hasPenis()) {
					return new Response("口交同时被足交", "你没有阴茎，所以无法接受来自[vanessa.name]的足交……", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("口交同时被足交", "你无法使用自己的阴茎，所以无法接受来自[vanessa.name]的足交……", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("口交同时被足交", "你无法使用自己的嘴巴，所以不能给[vanessa.name]舔阴……", null);
				}
				return new ResponseSex("口交同时被足交",
						"慢慢挪到[vanessa.namePos]的双腿之间，开始给她舔阴，同时她会为你提供足交。",
						true, true,
						new SMVanessaOralFootjob(
								SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						END_ORAL_SEX,
						UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_ORAL_FOOTJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Vanessa.class), Main.game.getPlayer(), PenisFeet.FOOT_JOB_DOUBLE_GIVING_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Vanessa.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
					}
				};
				
			} else if (index == 3) {
				return new Response("停止", "停止给[vanessa.name]足部按摩。", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).endSex(); // Cleans & replaces clothing
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "BARE_FOOT_MASSAGE_STOP"));
					}
				};
				
			}
			
			
			return null;
		}
	};

	public static final DialogueNode SHOULDER_MASSAGE = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("大腿按摩", "[vanessa.Name]踢掉了鞋子，问你能不能帮她把连裤袜脱下来，直接按摩她的大腿。", THIGH_MASSAGE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaMassaged, true);
						try {
							Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.FOOT), true, Main.game.getNpc(Vanessa.class));
						} catch(Exception ex) { System.err.println("Vanessa is not wearing any shoes!"); }
						try {
							Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.SOCK), true, Main.game.getNpc(Vanessa.class));
						} catch(Exception ex) { System.err.println("Vanessa is not wearing pantyhose!"); }
						
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if (index == 2) {
				return new Response("停止", "停止给[vanessa.name]按摩。", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaMassaged, true);
						Main.game.getNpc(Vanessa.class).endSex(); // Cleans & replaces clothing
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE_STOP"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode THIGH_MASSAGE = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "THIGH_MASSAGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("舔阴", "你无法使用自己的嘴巴，不能给[vanessa.name]舔阴！", null);
				}
				return new ResponseSex("舔阴",
						"慢慢挪到[vanessa.namePos]的双腿之间，开始给她舔阴。",
						true, true,
						new SMVanessaOral(
								SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						END_ORAL_SEX,
						UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_ORAL")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Vanessa.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
					}
				};
				
			} else if (index == 2) {
				return new Response("停止", "停止给[vanessa.name]大腿按摩。", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).endSex(); // Cleans & replaces clothing
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "THIGH_MASSAGE_STOP"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode END_ORAL_SEX = new DialogueNode("退开", "[vanessa.Name]把你推开，笑着看向你……", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_ORAL_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().hasPenis() && (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true) || !Main.game.getPlayer().hasVagina())) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						boolean biped = !Main.game.getPlayer().isTaur();
						return new ResponseSex("做爱",
								biped
									?"把[vanessa.name]推倒在桌子上，开始做爱。"
									:"骑到[vanessa.name]身上，开始做爱。",
								true, true,
								biped
									?new SMVanessaSex(
											SexPosition.OVER_DESK,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.BETWEEN_LEGS)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotDesk.OVER_DESK_ON_BACK)))
									:new SMVanessaSex(
											SexPosition.ALL_FOURS,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotAllFours.ALL_FOURS))),
								null,
								null,
								END_SEX,
								UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_SEX_FUCKING_VANESSA")){
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Vanessa.class), PenisVagina.PENIS_FUCKING_START, false, true));
							}
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
							}
						};
						
					} else {
						return new Response("做爱", "你无法使用阴茎，[vanessa.name]不能跟你做爱……", null);
					}
					
				} else if(Main.game.getPlayer().hasVagina()) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						boolean biped = !Main.game.getPlayer().isTaur();
						return new ResponseSex("接受舔阴",
								biped
									?"让[vanessa.name]跟你换个位置，让她给你舔阴。"
									:"让[vanessa.name]跪在你的身后，让他给你舔阴。",
								true, true,
								biped
									?new SMVanessaOral(
											SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.PERFORMING_ORAL)))
									:new SMVanessaOral(
											SexPosition.STANDING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotStanding.PERFORMING_ORAL_BEHIND))),
								null,
								null,
								END_SEX_ORAL_RECEIVING,
								UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_SEX_EATEN_OUT")){
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vanessa.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
							}
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
							}
						};
						
					} else {
						return new Response("接受舔阴", "你无法使用自己的阴道，[vanessa.name]不能跟你做爱……", null);
					}
					
				} else {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						boolean biped = !Main.game.getPlayer().isTaur();
						return new ResponseSex("接受口部侍奉",
								biped
									?"让[vanessa.name]跟你换个位置，用嘴巴侍奉你雌雄难辨的下体。"
									:"让[vanessa.name]跪在你身后，用嘴巴侍奉你雌雄难辨的下体。",
								true, true,
								biped
									?new SMVanessaOral(
											SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.PERFORMING_ORAL)))
									:new SMVanessaOral(
											SexPosition.STANDING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotStanding.PERFORMING_ORAL_BEHIND))),
								null,
								null,
								END_SEX_ORAL_RECEIVING,
								UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_SEX_EATEN_OUT_MOUND")){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
							}
						};
						
					} else {
						return new Response("接受口部侍奉", "你无法使用自己无性别的下体，[vanessa.name]不能跟你做爱……", null);
					}
				}
				
			} else if(index==2) {
				return new Response("拒绝", "告诉[vanessa.name]你已经玩够了。", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).equipClothing();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_ORAL_SEX_DECLINE_MORE"));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode END_SEX = new DialogueNode("结束", "你们暂时饕足了，一件件穿起衣服来……", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Vanessa.class).equipClothing();
			Main.game.getNpc(Vanessa.class).applyWash(true, true, null, 0); // Wet wipes are mentioned in ending description
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>0) {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_SEX_NO_PLAYER_ORGASM");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode END_SEX_ORAL_RECEIVING = new DialogueNode("结束", "你们暂时饕足了，一件件穿起衣服来……", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Vanessa.class).equipClothing();
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>0) {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_SEX_NO_PLAYER_ORGASM");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFSPRING_MAP = new DialogueNode("人口统计局", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFSPRING_MAP");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()>=ItemType.OFFSPRING_MAP.getValue()) {
					return new Response("购买("+UtilText.formatAsMoney(ItemType.OFFSPRING_MAP.getValue(), "span")+")", "告诉[vanessa.name]你想买一份奥术后代地图。", OFFSPRING_MAP_PURCHASE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-ItemType.OFFSPRING_MAP.getValue()));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.OFFSPRING_MAP), false));
						}
					};
					
				} else {
					return new Response("购买("+UtilText.formatAsMoneyUncoloured(ItemType.OFFSPRING_MAP.getValue(), "span")+")", "你的钱不够买下这个……", null);
				}
				
			} else if (index == 2) {
				return new Response("改变主意", "决定还是不买后代地图了。", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFSPRING_MAP_BACK"));
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode OFFSPRING_MAP_PURCHASE = new DialogueNode("人口统计局", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFSPRING_MAP_PURCHASE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};
	
	private static boolean unsuitableName = false;
	private static boolean unsuitableSurname = false;
	
	public static final DialogueNode NAME_CHANGE = new DialogueNode("人口统计局", "-", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "NAME_CHANGE"));
			
			UtilText.nodeContentSB.append("<br/>"
					+ "<div class='container-full-width' style='text-align:center;'>"
					+ "<div style='position:relative; display:inline-block; padding-bottom:0; margin 0 auto; vertical-align:middle; width:100%; text-align:center;'>"
					+ "<i>"
						+ "你的名字可以设置三种差分：男性名，中性名和女性名。"
						+ "你的名字会与你身体的女性化程度自动关联。"
					+ "</i>"
					+ "<br/>"
					+ "<p style='display:inline-block; padding:0; margin:0; height:25px; line-height:25px; width:100px;'>名：</p>"
					+ "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"
							+ "<input type='text' id='nameMasculineInput' style=' color:"+PresetColour.MASCULINE.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getMasculine())+ "'>"
							
					+ "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"
						+ "<input type='text' id='nameAndrogynousInput' style=' color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getAndrogynous())+ "'>"
						
					+ "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"
						+ "<input type='text' id='nameFeminineInput' style=' color:"+PresetColour.FEMININE.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getFeminine())+ "'>"
					
					+ "<br/>"
					+ "<p style='display:inline-block; padding:0; margin:0; height:25px; line-height:25px; width:100px;'>姓：</p>"
					+ "<form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='surnameInput' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getSurname())+ "'></form>"
				+ "</div>"
						+ "<br/>"
						+ "<i>你的姓名长度必须在 1 到 32 个字符之间。不能使用方括号或句号。(姓氏可留空。)</i>"
						+ (unsuitableName ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>无效的名字</b></p>" : "")
						+ (unsuitableSurname ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>无效的姓氏</b></p>" : "")
					+ "</div>"
					
					+ "<p id='hiddenFieldName' style='display:none;'></p>"
					+ "<p id='hiddenFieldSurname' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getMoney() < 100) {
					return new Response("确认("+UtilText.formatAsMoneyUncoloured(100, "span")+")",
							"修改你的名字。<br/>[style.italicsBad(你付不起！)]",
							null);
					
				} else {
					return new ResponseEffectsOnly("确认("+UtilText.formatAsMoney(100, "span")+")", "修改你的名字。<br/>将会花费"+UtilText.formatAsMoney(100)+"。"){
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public void effects() {
							applyNameChange(false);
						}
					};
				}
				
			} else if(index==2) {
				if(Main.game.getPlayer().getAllCharactersOfRelationType(Relationship.Parent).isEmpty()) {
					return new Response("后代("+UtilText.formatAsMoneyUncoloured(5000, "span")+")",
							"修改名字，同时让你的所有后代的姓氏更新为你的姓氏。<br/>[style.italicsBad(你还没有孩子，所以不能这么做！)]", null);
					
				} else if (Main.game.getPlayer().getMoney() < 5000) {
					return new Response("后代("+UtilText.formatAsMoneyUncoloured(5000, "span")+")",
							"修改名字，同时让你的所有后代的姓氏更新为你的姓氏。<br/>[style.italicsBad(你付不起！)]", null);
					
				} else {
					return new ResponseEffectsOnly("后代("+UtilText.formatAsMoney(5000, "span")+")",
							"更改名字，同时让你的所有后代的姓氏更新为你的姓氏。<br/>将花费"+UtilText.formatAsMoney(5000)+"。"){
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public void effects() {
							applyNameChange(true);
						}
					};
				}
				
			} else if (index == 0) {
				return new Response("返回", "决定不修改你的姓名。", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "NAME_CHANGE_BACK"));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	private static void applyNameChange(boolean applyOffspringSurnames) {
		List<String> fieldsList = Util.newArrayListOfValues("nameMasculineInput", "nameAndrogynousInput", "nameFeminineInput");
		List<String> namesList = new ArrayList<>();
		for(String s : fieldsList) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('"+s+"').value;");
			if(Main.mainController.getWebEngine().getDocument()!=null) {
				if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
						|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32
						|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().matches("[^\\[\\]\\.]+")) {
					unsuitableName = true;
				} else {
					unsuitableName = false;
					namesList.add(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
				}
			}
		}
		Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldSurname').innerHTML=document.getElementById('surnameInput').value;");
		if(Main.mainController.getWebEngine().getDocument()!=null) {
			if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1
					&& (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length() > 32
							|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().matches("[^\\[\\]\\.]+"))) {
				unsuitableSurname = true;
			} else {
				unsuitableSurname = false;
			}
		}
		
		if(applyOffspringSurnames && Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()<1) {
			unsuitableSurname = true;
		}
		
		if (unsuitableName || unsuitableSurname)  {
			Main.game.setContent(new Response("" ,"", NAME_CHANGE));
			
		} else {
			Main.game.getPlayer().setName(new NameTriplet(namesList.get(0), namesList.get(1), namesList.get(2)));
			Main.game.getPlayer().setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent());
			
			if(applyOffspringSurnames && Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1) {
				for(NPC npc : Main.game.getAllNPCs()) {
					GameCharacter mother = npc.getMother();
					if(mother!=null) {
						while(mother.getMother()!=null) {
							mother = mother.getMother();
						}
						if(mother.isPlayer()) {
							npc.setSurname(Main.game.getPlayer().getSurname());
						}
					}
				}
			}
			if(applyOffspringSurnames) {
				Main.game.getTextEndStringBuilder().append(
						"<p style='text-align:center;'>"
							+ "你填好表单，交上了所需的费用，就将名字修改为:<br/>"
							+ "<b>[pc.Name]"+(!Main.game.getPlayer().getSurname().isEmpty()?"·[pc.Surname]":"")+"</b><br/>"
							+ "你所有的后代，即家族树之下的所有角色，其姓氏都会被合法修改为:<br/>"
							+ "<b>[pc.Surname]</b><br/>"
						+ "</p>"
						+Main.game.getPlayer().incrementMoney(-5000));
			} else {
				Main.game.getTextEndStringBuilder().append(
						"<p style='text-align:center;'>"
							+ "你填好表单，交上了所需的费用，就将名字修改为:<br/>"
							+ "<b>[pc.Name]"+(!Main.game.getPlayer().getSurname().isEmpty()?"·[pc.Surname]":"")+"</b>"
						+ "</p>"
						+Main.game.getPlayer().incrementMoney(-100));
			}
			Main.game.setContent(new Response("" ,"", NAME_CHANGE));
		}
	}
}
