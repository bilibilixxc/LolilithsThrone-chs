package com.lilithsthrone.game.dialogue.places.dominion;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHole;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class HomeImprovements {

	public static GameCharacter getGloryHoleCharacter() {
		List<GameCharacter> characters = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		characters.removeIf((npc) -> !(npc instanceof GenericSexualPartner));
		return characters.get(0);
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("阿格斯的DIY仓库", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "OUTSIDE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isExtendedWorkTime()) {
					return new Response("进入", "进入张贴着向公众开放的仓库。", PlaceType.HOME_IMPROVEMENTS_ENTRANCE.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.HOME_IMPROVEMENTS, PlaceType.HOME_IMPROVEMENTS_ENTRANCE, false);
						}
					};
				} else {
					return new Response("进入", "仓库目前关门。如果你想进去，得晚点再来……", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "离开仓库，回到御城区。", PlaceType.DOMINION_HOME_IMPROVEMENT.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HOME_IMPROVEMENT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "CORRIDOR"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				if(!Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
						&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "CORRIDOR_PAINT_OPTIONS"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "CORRIDOR_PAINT_PURCHASED"));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SHELVING_PREMIUM = new DialogueNode("", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_PREMIUM"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				if(!Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
						&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
					UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN_PREMIUM.getValue()), true);
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_PREMIUM_PRICE"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_PAINT_PURCHASED"));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
				if(index==1) {
					if(Main.game.getPlayer().getMoney()<ItemType.PAINT_CAN_PREMIUM.getValue()) {
						return new Response("购买("+UtilText.formatAsMoneyUncoloured(ItemType.PAINT_CAN_PREMIUM.getValue(), "span")+")",
								"虽然[style.colourGood(海伦娜需要)]这罐油漆，但是你[style.colourbad(买不起)]！",
								null);
					}
					return new Response("购买("+UtilText.formatAsMoney(ItemType.PAINT_CAN_PREMIUM.getValue(), "span")+")",
							"购买一罐"+ItemType.PAINT_CAN_PREMIUM.getName(false)+"，[style.colourGood(海伦娜需要这种)]。",
							PAINT_PURCHASED) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.HOME_IMPROVEMENTS, PlaceType.HOME_IMPROVEMENTS_ENTRANCE);
							UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN_PREMIUM.getValue()), true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-ItemType.PAINT_CAN_PREMIUM.getValue()));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.PAINT_CAN_PREMIUM), false, true));
							((Helena)Main.game.getNpc(Helena.class)).sellOffRemainingSlaves();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode SHELVING_STANDARD = new DialogueNode("", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_STANDARD"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				if(!Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
						&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
					UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN.getValue()), true);
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_STANDARD_PRICE"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_PAINT_PURCHASED"));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
				if(index==1) {
					if(Main.game.getPlayer().getMoney()<ItemType.PAINT_CAN.getValue()) {
						return new Response("购买("+UtilText.formatAsMoneyUncoloured(ItemType.PAINT_CAN.getValue(), "span")+")",
								"海伦娜不需要[style.colourBad(这罐油漆)]，而且你也[style.colourbad(买不起它)]！",
								null);
					}
					return new Response("购买("+UtilText.formatAsMoney(ItemType.PAINT_CAN.getValue(), "span")+")",
							"购买一罐"+ItemType.PAINT_CAN.getName(false)+"，[style.colourBad(海伦娜不需要这个)]！",
							PAINT_PURCHASED) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.HOME_IMPROVEMENTS, PlaceType.HOME_IMPROVEMENTS_ENTRANCE);
							UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN.getValue()), true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-ItemType.PAINT_CAN.getValue()));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.PAINT_CAN), false, true));
							((Helena)Main.game.getNpc(Helena.class)).sellOffRemainingSlaves();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PAINT_PURCHASED = new DialogueNode("", "。", true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().getRace()==Race.DEMON) {
				return 5*60;
			} else {
				return 15*60;
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "PAINT_PURCHASED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开",
						"你已经得到想要的了，或许可以离开了。",
						PlaceType.DOMINION_HOME_IMPROVEMENT.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HOME_IMPROVEMENT);
					}
				};
				
			} else if(index==2) {
				return new Response("继续购物",
						"你离开之前可以停下再看看。",
						ENTRANCE);
			}
			return null;
		}
	};
	
	public static final DialogueNode BUILDING_SUPPLIES = new DialogueNode("", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "BUILDING_SUPPLIES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
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
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("厕所", "使用厕所。", TOILETS_USE);
				
			} else if(index==2) {
				List<InventorySlot> washSlots = Util.newArrayListOfValues(InventorySlot.HEAD, InventorySlot.EYES, InventorySlot.MOUTH, InventorySlot.NECK, InventorySlot.HAIR, InventorySlot.FINGER, InventorySlot.HAND, InventorySlot.WRIST);
				return new Response("洗澡",
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
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_USE");
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
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_WASH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_DOM = new DialogueNode("厕所", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_DOM", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开", "你又想了下，你不太想让陌生人用你的隐私部位寻欢作乐……", TOILETS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_DOM_LEAVE", getGloryHoleCharacter()));
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
						UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_DOM_START", getGloryHoleCharacter()));
			}
			return null;
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_DOM_POST_SEX = new DialogueNode("厕所", "陌生人迅速离开小隔间，回到商店。你也该离开了……", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_DOM_POST_SEX", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "走出摊位。", TOILETS) {
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
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_SUB", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开", "你又想了下，你其实不太想吸随机路人的屌……", TOILETS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_SUB_LEAVE", getGloryHoleCharacter()));
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
						UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_SUB_START", getGloryHoleCharacter()));
			}
			return null;
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_SUB_POST_SEX = new DialogueNode("厕所", "陌生人迅速离开小隔间，回到商店。你也该离开了……", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_SUB_POST_SEX", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "走出摊位。", TOILETS) {
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
