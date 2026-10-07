package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.9
 * @version 0.3.9
 * @author Innoxia
 */
public class RoomArthur {

	private static int getDaysRemainingUntilArcaneLightningUnlocked() {
		return (int) (14 - (Main.game.getDayNumber() - Main.game.getDialogueFlags().getSavedLong("arthur_globe_day_start")));
	}
	
	public static final DialogueNode ROOM_ARTHUR_INSTALLATION = new DialogueNode("亚瑟的房间", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_INSTALLATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("寻找莉西丝", "如果你想弄明白来龙去脉，最好还是同意帮忙。", ROOM_ARTHUR_INSTALLATION_AGREE_TO_CONVINCE_LYSSIETH){
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_A_INTO_THE_DEPTHS));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_INSTALLATION_AGREE_TO_CONVINCE_LYSSIETH = new DialogueNode("亚瑟的房间", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_INSTALLATION_AGREE_TO_CONVINCE_LYSSIETH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("继续", "让亚瑟继续实验。", ROOM_ARTHUR);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			if(index == 1) {
				return new Response("莉西丝", "询问亚瑟莉莱雅的母亲——莉西丝的事情。", ROOM_ARTHUR_LYSSIETH);
				
			} else if(index == 2) {
				return new Response("莉莱雅", "询问亚瑟他之前跟莉莱雅是什么关系。", ROOM_ARTHUR_LILAYA);
				
			}
			List<Response> additionalResponses = new ArrayList<>();
			
			// Hypno-watch quest:
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HYPNO_WATCH)) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_HYPNO_WATCH)) {
					additionalResponses.add(
							new Response("实验", "询问亚瑟他正在进行什么实验。", ROOM_ARTHUR_HYPNO_WATCH_START) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_HYPNO_WATCH));
								}
								@Override
								public Colour getHighlightColour() {
									return PresetColour.QUEST_SIDE;
								}
							});
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HYPNO_WATCH) == Quest.SIDE_HYPNO_WATCH_VICKY) {
					if(!Main.game.getPlayer().hasItem(Main.game.getItemGen().generateItem(ItemType.ARTHURS_PACKAGE))) {
						additionalResponses.add(new Response("递交包裹", "你需要先从“奥术艺术”拿到包裹！", null));
						
					} else {
						additionalResponses.add(
								new Response("递交包裹", "将你从“奥术艺术”拿到的包裹交出去。", ROOM_ARTHUR_HYPNO_WATCH_DELIVERY) {
									@Override
									public void effects() {
										Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.ARTHURS_PACKAGE));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HYPNO_WATCH, Quest.SIDE_HYPNO_WATCH_TEST_SUBJECT));
										Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
									}
								});
					}
				}
			}
			
			// Arcane lightning quest:
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ARCANE_LIGHTNING)) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ARCANE_LIGHTNING) && Main.game.getPlayer().hasWeaponType(WeaponType.getWeaponTypeFromId("innoxia_lightningGlobe_lightning_globe"), true)) {
					additionalResponses.add(
							new Response("发出闪电的球体",
									"将那个从执法者仓库找到的发出闪电的球体给亚瑟瞧瞧，问问他能不能解开其中力量的秘密。",
									ROOM_ARTHUR_ARCANE_LIGHTNING_START) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ARCANE_LIGHTNING));
								}
								@Override
								public Colour getHighlightColour() {
									return PresetColour.QUEST_SIDE;
								}
							});
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_ARCANE_LIGHTNING) == Quest.LIGHTNING_SPELL_1_PAYMENT) {
					if(Main.game.getPlayer().getEssenceCount()<500) {
						additionalResponses.add(new Response("给出球体", "你至少需要在灵气中吸收500精华才能继续！", null));
						
					} else {
						additionalResponses.add(new Response("给出球体",
								"将奥术闪电球交给亚瑟，并且允许他从你的灵气中提取精华用以解锁其秘密。"
									+ "<br/>[style.italicsMinorBad(你将会失去500精华！)]",
								ROOM_ARTHUR_ARCANE_LIGHTNING_PAYMENT));
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_ARCANE_LIGHTNING) == Quest.LIGHTNING_SPELL_2_WAITING) {
					if(Main.game.getDayNumber() - Main.game.getDialogueFlags().getSavedLong("arthur_globe_day_start") < 14) {
						additionalResponses.add(new Response("奥术闪电",
								"这段时间亚瑟还不足以从这个奥术闪电球中解锁其秘密。"
									+ (getDaysRemainingUntilArcaneLightningUnlocked()==1
										?"明天"
										:Util.intToString(getDaysRemainingUntilArcaneLightningUnlocked())+"天之后")
									+"再回来看看他能发现什么。",
								null));
						
					} else {
						additionalResponses.add(new Response("奥术闪电",
								"自从你将奥术闪电球交给亚瑟已经过去了两周，你应该问问他是否从其中解锁了什么秘密。",
								ROOM_ARTHUR_ARCANE_LIGHTNING_END));
					}
				}
			}
			
			for(int i=0 ; i<additionalResponses.size(); i++) {
				if(index==i+3) {
					return additionalResponses.get(i);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_LYSSIETH = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_LYSSIETH");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index == 1) {
					return new Response("莉西丝", "你已经问过亚瑟莉西丝的事情了。", null);
					
				}
			}
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_LILAYA = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_LILAYA");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index == 2) {
					return new Response("莉莱雅", "你已经问过亚瑟莉莱雅的事情了。", null);
				}
			}
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_START = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_START");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_DELIVERY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_DELIVERY");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("同意", "你相信莉莱雅，同意了她的请求。", ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF);
				
			} else if(index == 2) {
				return new Response("拒绝", "你是不可能同意做莉莱雅和亚瑟的实验对象的！或许萝丝能替你“自愿”参加……", ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 10));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("醒来", "你突然从幻境中跳出。", ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.ORIENTATION_HYPNO_WATCH), false, true));

						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HYPNO_WATCH, Quest.SIDE_UTIL_COMPLETE));
						
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60*15;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "让亚瑟继续其他实验。", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Rose.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("叫醒萝丝", "帮莉莱雅一起叫醒萝丝。", ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED_WAKE_ROSE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.ORIENTATION_HYPNO_WATCH), false, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HYPNO_WATCH, Quest.SIDE_UTIL_COMPLETE));
						
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED_WAKE_ROSE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED_WAKE_ROSE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "让亚瑟继续其他实验。", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	
	// Lightning globe:

	public static final DialogueNode ROOM_ARTHUR_ARCANE_LIGHTNING_START = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getEssenceCount()<500) {
					return new Response("给出球体", "你至少需要在灵气中吸收500精华才能继续！", null);
					
				} else {
					return new Response("给出球体",
							"将奥术闪电球交给亚瑟，并且允许他从你的灵气中提取精华用以解锁其秘密。"
									+ "<br/>[style.italicsMinorBad(你将会失去500精华！)]",
							ROOM_ARTHUR_ARCANE_LIGHTNING_PAYMENT);
				}
				
			} else if(index==2) {
				return new Response("稍后", "告诉亚瑟你要考虑一下，之后再回来让他对球体做实验。", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_START_LEAVE"));
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROOM_ARTHUR_ARCANE_LIGHTNING_PAYMENT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().removeWeaponTypeIntoVoid(WeaponType.getWeaponTypeFromId("innoxia_lightningGlobe_lightning_globe"), true, true, true);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(-500, false));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ARCANE_LIGHTNING, Quest.LIGHTNING_SPELL_2_WAITING));
			Main.game.getDialogueFlags().setSavedLong("arthur_globe_day_start", Main.game.getDayNumber());
		}
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_PAYMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "从亚瑟的房间走出，让他继续他的实验。", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_PAYMENT_LEAVE"));
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROOM_ARTHUR_ARCANE_LIGHTNING_END = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("学习法术", "通读笔记，学会亚瑟创造的两种法术。", ROOM_ARTHUR_ARCANE_LIGHTNING_END_FINAL);
			}
			return null;
		}
	};

	public static final DialogueNode ROOM_ARTHUR_ARCANE_LIGHTNING_END_FINAL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_lightningGlobe_lightning_globe_ring"), 1, false, true));
			Main.game.getPlayer().addSpell(Spell.ARCANE_CHAIN_LIGHTNING);
			Main.game.getPlayer().addSpell(Spell.ARCANE_LIGHTNING_SUPERBOLT);
			Main.game.getTextEndStringBuilder().append(
					"<p style='text-align:center;'>"
						+ "你学会了以下法术:"
						+ "<br/><b style='color:"+PresetColour.SPELL_SCHOOL_ARCANE.toWebHexString()+";'>"+Spell.ARCANE_CHAIN_LIGHTNING.getName()+"</b>"
						+ "<br/><b style='color:"+PresetColour.SPELL_SCHOOL_ARCANE.toWebHexString()+";'>"+Spell.ARCANE_LIGHTNING_SUPERBOLT.getName()+"</b>"
					+ "</p>");
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ARCANE_LIGHTNING, Quest.SIDE_UTIL_COMPLETE));
		}
		@Override
		public int getSecondsPassed() {
			return 60 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_END_FINAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "让亚瑟继续其他实验。", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_END_FINAL_LEAVE"));
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
}
