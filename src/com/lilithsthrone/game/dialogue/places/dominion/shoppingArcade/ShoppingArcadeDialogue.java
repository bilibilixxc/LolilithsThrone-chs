package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.WesQuest;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHole;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5.5
 * @author Innoxia
 */
public class ShoppingArcadeDialogue {
	
	public static GameCharacter getGloryHoleCharacter() {
		List<GameCharacter> characters = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		characters.removeIf((npc) -> !(npc instanceof GenericSexualPartner));
		return characters.get(0);
	}
	
	public static String getCoreResponseTab(int index) {
		if(index==0) {
			return "行动";
		} else if(index==1) {
			return "快速旅行";
		}
		return null;
	}
	
	public static Response getFastTravelResponses(int responseTab, int index) {
		if(responseTab==1) {
			if (index == 1) {
				return new Response("入口", "快速旅行到购物中心的主入口", PlaceType.SHOPPING_ARCADE_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 2) {
				return new Response("拉尔夫小吃店", "快速旅行到拉尔夫小吃店。", PlaceType.SHOPPING_ARCADE_RALPHS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RALPHS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 3) {
				return new Response("妮安服装店", "快速旅行到妮安服装店。", PlaceType.SHOPPING_ARCADE_NYANS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 4) {
				return new Response("奥术艺术", "快速旅行到奥术艺术。", PlaceType.SHOPPING_ARCADE_VICKYS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_VICKYS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 5) {
				return new Response("魅魔的秘密", "快速旅行到魅魔的秘密。", PlaceType.SHOPPING_ARCADE_KATES_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_KATES_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 6) {
				return new Response("皮克斯操练场", "快速旅行到皮克斯操练场的健身房内。", PlaceType.SHOPPING_ARCADE_PIXS_GYM.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_PIXS_GYM, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 7) {
				return new Response("梦中爱侣", "快速旅行到梦中爱侣。", PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
				
			} else if (index == 8) {
				return new Response("橡木林荫", "快速旅行到“橡木林荫”餐厅。", PlaceType.SHOPPING_ARCADE_RESTAURANT.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RESTAURANT, false);
						Main.game.setResponseTab(0);
					}
				};
			}
		}
		return null;
	}
	
	// Dialogue noes:
	
	public static final DialogueNode OUTSIDE = new DialogueNode("购物中心", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "OUTSIDE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("进入", "穿过入口进入购物中心。", PlaceType.SHOPPING_ARCADE_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENTRY = new DialogueNode("商场入口", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ENTRY");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("离开", "离开购物中心", PlaceType.DOMINION_SHOPPING_ARCADE.getDialogue(false)){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_SHOPPING_ARCADE, false);
						}
					};
				}
			}
			return getFastTravelResponses(responseTab, index);
		}
		
	};
	
	public static final DialogueNode ARCADE = new DialogueNode("购物中心", "-", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ARCADE"));
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_START
					&& Vector2i.getDistance(Main.game.getPlayer().getLocation(), Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_ANTIQUES).getLocation())==1) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ARCADE_WES_REMINDER"));
			}
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index==1) {
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_START) {
						if(Vector2i.getDistance(Main.game.getPlayer().getLocation(), Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_ANTIQUES).getLocation())==1) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestMet)) {
								return new Response("会见执法者", "你今天已经见过韦斯了，最早要明天才能再见到他……", null);
								
							} else if(Main.game.getHourOfDay()!=13) {
								return new Response("会见执法者", "那个神秘的执法者告诉你在[units.time(13)]到[units.time(14)]之间见面，到时候再来吧……", null);
								
							} else {
								return new Response("会见执法者", "在这片区域闲逛一会儿，等那个神秘的执法者跟你联系……", WesQuest.WES_QUEST_SHOPPING_ARCADE_MEETING);
							}
						}
					}
				}
			}
			return getFastTravelResponses(responseTab, index);
		}
		
	};
	
	public static final DialogueNode GENERIC_SHOP = new DialogueNode("商店", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "GENERIC_SHOP");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode RESTAURANT = new DialogueNode("橡木林荫", "-", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "RESTAURANT");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode ANTIQUES = new DialogueNode("古董店", "-", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index==1) {
					if(!Main.game.isExtendedWorkTime()) {
						return new Response("进入", "古玩店目前关门。如果你想进去转转，得换个时间再来。", null);
					}
					return new Response("进入", "进入古玩店并在里面转转。", ANTIQUES_INTERIOR);
				}
			}
			return getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR = new DialogueNode("古董店", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR"));
			
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).contains(Main.game.getNpc(Scarlett.class))) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_END"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).contains(Main.game.getNpc(Scarlett.class))) {
				if(index==1) {
					return new Response("斯嘉丽", "去找店员询问斯嘉丽的事情。", ANTIQUES_INTERIOR_SCARLETT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_START"));
							if(Main.game.getNpc(Scarlett.class).getRace()!=Race.HARPY || (Main.game.getNpc(Scarlett.class).getGender()!=Gender.F_P_TRAP && Main.game.getNpc(Scarlett.class).getGender()!=Gender.F_P_B_SHEMALE)) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_TRANSFORMED"));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT"));
							}
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_END"));
							
							((Scarlett)Main.game.getNpc(Scarlett.class)).completeBodyReset();
							Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), true);
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar"), false), true, Main.game.getNpc(Scarlett.class));
						}
					};
				}
			}
			if(index==0) {
				return new Response("离开", "回头返回购物中心。", ANTIQUES);
			}
			return null;
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT = new DialogueNode("", "", true) {
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
				return new Response("解释", "解释说你不是来讨论那个松鼠男说的事情的，<i>而是</i>要他放了斯嘉丽。", ANTIQUES_INTERIOR_SCARLETT_EXPLAIN);
			}
			return null;
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT_EXPLAIN = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_EXPLAIN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<10_000) {
					return new Response("支付("+UtilText.formatAsMoney(10_000, "span")+")", "你付不起那个松鼠男要求的一万火币！", null);
					
				} else {
					return new Response("支付("+UtilText.formatAsMoney(10_000, "span")+")", "付给那个松鼠男要求的一万火币。", ANTIQUES_INTERIOR_SCARLETT_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_PURCHASED"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-10_000));
						}
					};
				}
				
			} else if(index==2) {
				return new Response("讨价还价", "跟那个松鼠男讨价还价，希望能拉低一些对斯嘉丽的报价。", ANTIQUES_INTERIOR_SCARLETT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_HAGGLE"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
			Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK).setSealed(false);
			Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK), true, Main.game.getNpc(Scarlett.class));
			Main.game.getNpc(Scarlett.class).getOwner().removeSlave(Main.game.getNpc(Scarlett.class));
			Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX()+1, Main.game.getPlayer().getLocation().getY()));
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaScarlettToldToReturn, true);
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
			if(index==1) {
				return new Response("继续",
						"继续走出到购物中心。",
						ARCADE);
			}
			return null;
		}
	};
	
	public static final DialogueNode TOILETS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("厕所", "使用厕所。", TOILETS_USE);
				
			} else if(index==2) {
				List<InventorySlot> washSlots = Util.newArrayListOfValues(InventorySlot.HEAD, InventorySlot.EYES, InventorySlot.MOUTH, InventorySlot.NECK, InventorySlot.HAIR, InventorySlot.FINGER, InventorySlot.HAND, InventorySlot.WRIST);
				return new Response("清洗",
						"用洗手池清洗手和脸。"
							+ "<br/>[style.italicsGood(这将清理你的"+Util.inventorySlotsToParsedStringList(washSlots, Main.game.getPlayer())+"，以及穿着在这些栏位上的衣物。)]"
							+ "<br/>[style.italicsMinorBad(<b>不会</b>为同伴清理。)]",
						TOILETS_WASH) {
					@Override
					public void effects() {
						for(InventorySlot slot : washSlots) {
							Main.game.getPlayer().removeDirtySlot(slot, true);
							AbstractClothing c = Main.game.getPlayer().getClothingInSlot(slot);
							if(c!=null) {
								c.setDirty(Main.game.getPlayer(), false);
							}
						}
					}
				};
				
			} else if(index==3) {
				boolean penisAvailable = Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
				boolean vaginaAvailable = Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
				
				if((penisAvailable && !Main.game.getPlayer().isTaur()) || vaginaAvailable) {
					return new Response("寻欢洞(使用)",
							"厕所的隔间里有个寻欢洞。进入隔间，等待对面的人服务你。",
							TOILETS_GLORY_HOLE_DOM) {
						@Override
						public void effects() {
							Main.game.spawnSubGloryHoleNPC("陌生人");
						}
					};
					
				} else if(penisAvailable && Main.game.getPlayer().isTaur()) {
					return new Response("寻欢洞(使用)",
							"由于你[pc.legRace]身躯的构造，你找不到一个使用寻欢洞的合适姿势……",
							null);
					
				} else {
					return new Response("寻欢洞(使用)",
							"你无法使用你的生殖器，所以无法在寻欢洞接受服务。",
							null);
				}
				
			} else if(index==4) {
				if((Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true))
						|| (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
						|| (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))) {
					return new Response("寻欢洞(服务)",
							"厕所的隔间里有个寻欢洞。进入隔间，等待给对面的人服务。",
							TOILETS_GLORY_HOLE_SUB) {
						@Override
						public void effects() {
							Main.game.spawnDomGloryHoleNPC("陌生人");
							getGloryHoleCharacter().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						}
					};
				
				} else {
					return new Response("寻欢洞(服务)",
							"你无法使用嘴巴、生殖器或肛门，所以无法在寻欢洞为陌生人服务。",
							null);
				}
			}
			return null;
		}
	};

	public static final DialogueNode TOILETS_USE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_USE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TOILETS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode TOILETS_WASH = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_WASH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_DOM = new DialogueNode("厕所", "", true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.isExtendedWorkTime()) {
				return 20*60;
			}
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_DOM", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开", "你又想了下，你不太想让陌生人用你的隐私部位寻欢作乐……", TOILETS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_DOM_LEAVE", getGloryHoleCharacter()));
						Main.game.banishNPC((NPC) getGloryHoleCharacter());
					}
				};
				
			} else if(index==1) {
				return new ResponseSex("开始",
						UtilText.parse(getGloryHoleCharacter(), "按照[npc.name]的指示，靠近寻欢洞。"),
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
								Util.newHashMapOfValues(new Value<>(getGloryHoleCharacter(), SexSlotUnique.GLORY_HOLE_KNEELING))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						TOILETS_GLORY_HOLE_DOM_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_DOM_START", getGloryHoleCharacter()));
			}
			return null;
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_DOM_POST_SEX = new DialogueNode("厕所", "那个陌生人匆匆离开了隔间，走向一家商店，你也该离开了……", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_DOM_POST_SEX", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "走出隔间。", TOILETS) {
					@Override
					public void effects() {
						Main.game.banishNPC((NPC) getGloryHoleCharacter());
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_SUB = new DialogueNode("厕所", "", true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.isExtendedWorkTime()) {
				return 20*60;
			}
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_SUB", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开", "你又想了下，你其实不太想吸随机路人的屌……", TOILETS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_SUB_LEAVE", getGloryHoleCharacter()));
						Main.game.banishNPC((NPC) getGloryHoleCharacter());
					}
				};
				
			} else if(index==1) {
				return new ResponseSex("开始",
						UtilText.parse(getGloryHoleCharacter(), "按照[npc.name]说的，准备给[npc.her]的肉棒服务。"),
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(new Value<>(getGloryHoleCharacter(), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_KNEELING))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						TOILETS_GLORY_HOLE_SUB_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_SUB_START", getGloryHoleCharacter()));
			}
			return null;
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_SUB_POST_SEX = new DialogueNode("厕所", "那个陌生人匆匆离开了隔间，走向一家商店，你也该离开了……", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_SUB_POST_SEX", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "走出隔间。", TOILETS) {
					@Override
					public void effects() {
						Main.game.banishNPC((NPC) getGloryHoleCharacter());
					}
				};
			}
			return null;
		}
	};
}
