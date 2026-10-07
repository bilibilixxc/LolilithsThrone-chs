package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.nyan.SMNyanSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.4
 * @author Innoxia
 */
public class ClothingEmporium {
	
	public static final String NYAN_HIDING_DAY_ID = "nyanHidingDay";
	
	public static String incrementAffection(GameCharacter character, float increment, float minimumLimit, float maximumLimit) {
		float currentAffection = character.getAffection(Main.game.getPlayer());
		
		
		if(increment>=0) {
			increment = Math.min(increment, Math.max(0, maximumLimit-currentAffection));
		} else {
			increment = Math.max(increment, Math.min(0, minimumLimit-currentAffection));//TODO test
		}
		
		if(increment==0 || (increment>0 && currentAffection>=maximumLimit)) {
			return "<p style='text-align:center'>"
						+ UtilText.parse(character, "[style.italicsDisabled(此举并没有让[npc.Name]对你更有好感……)]")
						+ "<br/>"
						+ AffectionLevel.getDescription(character, Main.game.getPlayer(), true)
					+ "</p>";
		}
		if(increment<0 && currentAffection<=minimumLimit) {
			return "<p style='text-align:center'>"
						+ UtilText.parse(character, "[style.italicsDisabled(此举并没有让[npc.Name]对你更有好感……)]")
						+ "<br/>"
						+ AffectionLevel.getDescription(character, Main.game.getPlayer(), true)
					+ "</p>";
		}
		
		return character.incrementAffection(Main.game.getPlayer(), increment);
	}
	
	private static void applyRepeatMeetingReactionUpdates() {
		if(getNyan().isVisiblyPregnant()) {
			getNyan().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanRestaurantDateCompleted) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumInterviewPassed)) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanApologised, true);
		}
	}
	
	private static Nyan getNyan() {
		return ((Nyan)Main.game.getNpc(Nyan.class));
	}
	
	public static final DialogueNode EXTERIOR = new DialogueNode("妮安服装店(外部)", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXTERIOR");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ShoppingArcadeDialogue.getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					if(!Main.game.isHourBetween(9, 20)) {
						return new Response("进入", "妮安服装店目前关门。你得过一会再来……", null);
						
					} else if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.nyanIntroduced)) {
						return new Response("进入", "进入妮安服装商场。", SHOP_CLOTHING_REPEAT);
						
					} else {
						return new Response("进入", "进入妮安服装商场。", SHOP_CLOTHING);
					}
				}
			}
			return ShoppingArcadeDialogue.getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_CLOTHING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("仓库", "妮安让你进了仓库。", SHOP_CLOTHING_STOCK_ROOM) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.nyanIntroduced);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SHOP_CLOTHING_STOCK_ROOM = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_CLOTHING_STOCK_ROOM");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_CLOTHING_REPEAT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getAuthor() {
			if(getNyan().isPregnant() && !getNyan().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				return "Duner";
			} else {
				return "Innoxia";
			}
		}
		@Override
		public String getContent() {
			if(!Main.game.getCharactersPresent().contains(getNyan())) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_CLOTHING_REPEAT_NO_NYAN");
				
			} else {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanHiding)) {
					if(Main.game.getDialogueFlags().getSavedLong(NYAN_HIDING_DAY_ID)<Main.game.getDayNumber()) {
						return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_NEXT_DAY");
					} else {
						return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING");
					}
				} else if(getNyan().isVisiblyPregnant() && !getNyan().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
					return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_GREETING_REPEAT_PREGNANT");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_GREETING_REPEAT");
				}
			}
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanHiding)
					|| !Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)
					|| !Main.game.getCharactersPresent().contains(getNyan())) {
				return null;
			}

			if(index==0) {
				return "交易";
			} else if(index==1) {
				return "对话";
			} else {
				return null;
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanHiding)) {
				if(index==1) {
					if(Main.game.getDialogueFlags().getSavedLong(NYAN_HIDING_DAY_ID)<Main.game.getDayNumber()) {
						return new Response("跟随", "跟随妮安再次进入仓库，听听她想说什么。", NYAN_HIDING_END) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanHiding, false);
							}
						};
						
					} else {
						return new Response("离开", "现在妮安躲了起来，店里又没别人帮手，你没什么事能做，只能离开。", EXTERIOR) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_LEAVE"));
							}
						};
					}
				}
				
				return null;
			}
			
			if(responseTab==1) {
				float currentAffection = getNyan().getAffection(Main.game.getPlayer());
				
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanDating)) {
					if(index==1) {
						return new Response("女朋友",
								"告妮安你改变想法了，现在想让她当你的女朋友。",
								NYAN_HIDING_END_SHOP) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanDating, true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_CLOTHING_REPEAT_GIRLFRIEND"));
								Main.game.setResponseTab(1);
							}
						};
					}
					
				} else {
					if(index==1) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanTalkedTo)) {
							return new Response("对话", "你今天已经跟妮安交谈过了。明天才能再次对话。", null);
							
						} else {
							return new Response("对话", "跟妮安谈一会儿，更多地了解她。", ROMANCE_TALK) {
								@Override
								public void effects() {
									UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK_BASE"));
									
									List<String> topics = Util.newArrayListOfValues(
											"NYAN_NOVELS",
											"NYAN_WORK",
											"NYAN_HOBBIES",
											"NYAN_HOME");
									long lowestValue = 1_000_000;
									for(String topic : topics) {
										if(Main.game.getDialogueFlags().getSavedLong(topic)<lowestValue) {
											lowestValue = Main.game.getDialogueFlags().getSavedLong(topic);
										}
									}
									long thanksJava = lowestValue;
									topics.removeIf(s -> Main.game.getDialogueFlags().getSavedLong(s)>thanksJava);
									String topicSelected = Util.randomItemFrom(topics);
									if(Main.game.getDialogueFlags().getSavedLong("NYAN_NOVELS")==-1) { // Make sure that novels is first topic
										topicSelected = "NYAN_NOVELS";
									}
									
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK_"+topicSelected));
									Main.game.getDialogueFlags().incrementSavedLong(topicSelected, 1);
									
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK_FINAL"));
									
									Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 30, 50));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanTalkedTo, true);
								
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
						
					} else if(index==2) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanComplimented)) {
							return new Response("称赞", "你今天已经称赞过妮安了。明天才能再进行此动作。", null);
							
						} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanTalkedTo) && currentAffection<50) {
							return new Response("称赞",
									"你看得出来，如果不先跟妮安谈一谈就发出溢美之词，必然会搞砸的。"
										+ "<br/>[style.italicsMinorBad(你需要先跟妮安交谈才能称赞她！)]",
									null);
							
						} else {
							return new Response("称赞", "称赞妮安的外貌和品格。", ROMANCE_COMPLIMENT) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 30, 50));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanComplimented, true);
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
						
					} else if(index==3) {
						int requiredAffection = 40;
						if(currentAffection<requiredAffection) {
							return new Response(
									"调情",
									"你看得出来现在跟妮安调情肯定不会有好结果。"
										+ "你应该先多了解她一下。"
										+ "<br/>[style.italicsMinorBad(需要妮安的好感至少达到"+requiredAffection+"目前是"+currentAffection+"。)]",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanFlirtedWith)) {
							return new Response("调情", "你今天已经和妮安调情了。你只能明天再调情。", null);
							
						} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanTalkedTo) && currentAffection<50) {
							return new Response("调情",
									"你看得出来，如果不先跟妮安谈一谈就跟她调情，必然会搞砸的。"
										+ "<br/>[style.italicsMinorBad(你需要先跟妮安交谈才能跟她调情！)]",
									null);
							
						} else {
							return new Response("调情", "和妮安调情一下。", ROMANCE_FLIRT) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 3, 30, 50));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanFlirtedWith, true);
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
						
					} else if(index==6) {
						int requiredAffection = 50;
						if(currentAffection<requiredAffection) {
							return new Response(
									"散步",
									"你觉得妮安还不会跟你散步……"
										+ "<br/>[style.italicsMinorBad(需要妮安的好感至少达到"+requiredAffection+"目前是"+currentAffection+"。)]",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanWalked)) {
							return new Response("散步", "你今天已经跟妮安散过步了。明天才能再进行此动作。", null);
							
						} else {
							return new Response("散步", "询问妮安愿不愿意跟你一起去散步。", ROMANCE_WALK) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 50, 60));
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
					}
					
					// Requires first kiss:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanFirstKissed)) {
						if(index==4) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanHeadPatted)) {
								return new Response("摸头", "你今天已经摸过妮安的脑袋了。明天才能再进行此动作。", null);
								
							} else {
								return new Response("摸头", "摸摸妮安的脑袋，夸她是个好姑娘。", ROMANCE_HEAD_PAT) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 50, 60));
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanHeadPatted, true);
										applyRepeatMeetingReactionUpdates();
									}
								};
							}
							
						} else if(index==5) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanKissed)) {
								return new Response("亲吻", "你今天已经亲过妮安了。明天才能再进行此动作。", null);
								
							} else {
								return new Response("亲吻", "你从妮安看向你的目光就能看出她还想再亲一次。于是你靠了上去，给了她想要的。", ROMANCE_KISS) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 50, 60));
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanKissed, true);
										applyRepeatMeetingReactionUpdates();
									}
								};
							}
						}

						if(index==7) {
							int requiredAffection = 60;
							if(currentAffection<requiredAffection) {
								return new Response(
										"餐厅约会",
										"考虑到妮安胆怯和敏感的性格，你需要确定她能否接受公开的约会……"
											+ "<br/>[style.italicsMinorBad(需要妮安的好感至少达到"+requiredAffection+"目前是"+currentAffection+"。)]",
										null);
								
							} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanRestaurantDateRequested)) {
								if(Main.game.getDayOfWeek()==DayOfWeek.FRIDAY || Main.game.getDayOfWeek()==DayOfWeek.SATURDAY) {
									return new Response("餐厅约会",
											"问问妮安愿不愿意跟你去“橡木林荫”餐厅约会。"
												+ "<br/>[style.italicsMinorBad(该动作不能在周五和周六使用！)]",
											null);
									
								} else {
									return new Response("餐厅约会",
											"问问妮安愿不愿意跟你去“橡木林荫”餐厅约会。",
											ROMANCE_DATE_REQUESTED) {
										@Override
										public void effects() {
											applyRepeatMeetingReactionUpdates();
										}
									};
								}
							}
						}
					}
					
					// Requires affection of over 60, which can only be achieved after first date (added flag check just to be sure):
					if(currentAffection>60 || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanRestaurantDateCompleted)) {
						if(index==7) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanMakeOut)) {
								return new Response("亲热", "你今天已经跟妮安亲热过了。明天才能再进行此动作。", null);
								
							} else {
								return new Response("亲热", "你看得出来妮安迫不及待地想跟你来一些亲密的身体接触，于是也热情地俯身过去，亲热起来。", ROMANCE_MAKE_OUT) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 60, 100));
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanMakeOut, true);
										applyRepeatMeetingReactionUpdates();
									}
								};
							}
						}
					}
					
					if(index==8) {
						if(getNyan().isVisiblyPregnant()) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanTummyRubbed)) {
								return new Response("揉肚子", "你今天已经揉妮安的肚子了。明天才能再进行此动作。", null);
								
							} else {
								return new Response("揉肚子",
										"揉揉妮安怀孕的小肚子，问她是怎么应对这场怀孕的。",
										ROMANCE_RUB_TUMMY) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 50, 60));
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanTummyRubbed, true);
										applyRepeatMeetingReactionUpdates();
									}
								};
							}
						}
					}
					
					if(index==10) {
						int requiredAffection = 40;
						if(currentAffection<requiredAffection) {
							return new Response(
									"送礼",
									"对于现在还十分脆弱的猫女来说，她可能接受不了收到礼物的感受……"
										+ "<br/>[style.italicsMinorBad(需要妮安的好感至少达到"+requiredAffection+"目前是"+currentAffection+"。)]",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanGift)) { // This is set in Nyan's getGiftReaction() method
							return new Response("送礼", "你今天已经送给过妮安礼物了。明天才能再进行此动作。", null);
						} else {
							return new Response("送礼", "送妮安一份礼物(打开礼物选择界面)。", ROMANCE_GIFT) {
								@Override
								public DialogueNode getNextDialogue() {
									return GiftDialogue.getGiftDialogue(getNyan(), ROMANCE_GIFT, 1);
								}
								@Override
								public void effects() {
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
					}
				}
				
				if(index == 0) {
					return new Response("离开", "跟妮安说你有别的事情要做。", EXTERIOR) {
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT"));
							applyRepeatMeetingReactionUpdates();
						}
					};
				}
				
				return null;
				
			} else if(responseTab==0) {
				String descriptionStart = "问她";
				if(!Main.game.getCharactersPresent().contains(getNyan())) {
					descriptionStart = "看一眼";
				}
				
				if (index == 1) {
					return new ResponseTrade("女性衣物", descriptionStart+"有什么女性衣物。", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonFemaleClothing()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 2) {
					return new ResponseTrade("女性内衣", descriptionStart+"有什么女性内衣。", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonFemaleUnderwear()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 3) {
					return new ResponseTrade("女性配件", descriptionStart+"有什么女性配件。", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonFemaleAccessories()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 6) {
					return new ResponseTrade("男性衣物", descriptionStart+"有什么男性衣物。", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonMaleClothing()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 7) {
					return new ResponseTrade("男性内衣", descriptionStart+"有什么男性内衣。", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonMaleLingerie()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 8) {
					return new ResponseTrade("男性配件", descriptionStart+"有什么男性配件。", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonMaleAccessories()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 11) {
					return new ResponseTrade("通用服装", descriptionStart+"通用服装目前可用。", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonAndrogynousClothing()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 12) {
					return new ResponseTrade("通用内衣", descriptionStart+"有什么通用内衣。", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonAndrogynousLingerie()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 13) {
					return new ResponseTrade("通用配件", descriptionStart+"有什么通用配件。", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonAndrogynousAccessories()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 5) {
					if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
						return new ResponseTrade("附魔衣物", descriptionStart+"库存中有什么特殊衣物。", getNyan()){
							@Override
							public void effects() {
								getNyan().clearNonEquippedInventory(false);
		
								for (AbstractClothing c : getNyan().getSpecials()) {
									if(getNyan().isInventoryFull()) {
										break;
									}
									getNyan().addClothing(c, false);
								}
							}
						};
						
					} else if(!Main.game.getPlayer().hasQuest(QuestLine.RELATIONSHIP_NYAN_HELP)) {
						return new Response("附魔衣物",
								"询问妮安是否有附魔衣物卖。"
										+ "<br/>[style.italicsQuestRomance(将会开启妮安的浪漫任务！)]",
								SHOP_ENCHANTED_CLOTHING) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.RELATIONSHIP_NYAN_HELP));
							}
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_RELATIONSHIP;
							}
						};
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_NYAN_HELP) == Quest.RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN) {
						return new Response("报告", "告诉妮安供货的问题已经解决了。", SHOP_REPORT_BACK) {
							@Override
							public void effects() {
								getNyan().setSellModifier(1.25f);
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(5000));
								Main.game.getTextEndStringBuilder().append(getNyan().setAffection(Main.game.getPlayer(), 30));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.SIDE_UTIL_COMPLETE));
							}
						};
						
					} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_1_STOCK_ISSUES)) {
						return new Response("报告", "解决供货问题后再向妮安报告。", null);
						
					} else {
						return new Response("提供帮助", "告诉妮安你可以帮她解决供货问题。", SHOP_OFFER_HELP) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(getNyan().setAffection(Main.game.getPlayer(), 10));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP));
							}
						};
					}
					 
				} else if (index == 0) {
					return new Response("离开", "跟妮安说你有别的事情要做。", EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT"));
						}
					};
				}
				
				return null;
			}
			
			return null;
		}
	};
	
	public static final DialogueNode SHOP_ENCHANTED_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_ENCHANTED_CLOTHING");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_OFFER_HELP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_OFFER_HELP");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_REPORT_BACK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_REPORT_BACK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("离开", "退出商店。", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT_EMBARRASSED"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanHiding, true);
						Main.game.getDialogueFlags().setSavedLong(NYAN_HIDING_DAY_ID, Main.game.getDayNumber());
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode NYAN_HIDING_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("女朋友",
						"告诉妮安你愿意让她成为你的女朋友。",
						NYAN_HIDING_END_SHOP) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanDating, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_END_GIRLFRIEND"));
						Main.game.setResponseTab(1);
					}
				};
				
			} else if(index==2) {
				return new Response("拒绝",
						"告诉妮安你现在还没准备好交女朋友。"
							+ "<br/>[style.italicsMinorGood(之后如果改变了主意，还可以询问妮安能否当你的女朋友。)]",
							NYAN_HIDING_END_SHOP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_END_DECLINE"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode NYAN_HIDING_END_SHOP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_TALK = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 55*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_COMPLIMENT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_FLIRT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_HEAD_PAT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_HEAD_PAT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_HEAD_PAT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_HEAD_PAT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_KISS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_RUB_TUMMY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_RUB_TUMMY"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_WALK = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Cell destination = null;
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanWalked)) {
				destination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL"));
				
			} else {
				Map<AbstractPlaceType, String> places = Util.newHashMapOfValues(
						new Value<>(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP, "ASHLEY"),
						new Value<>(PlaceType.SHOPPING_ARCADE_ANTIQUES, "ANTIQUES"),
						new Value<>(PlaceType.SHOPPING_ARCADE_KATES_SHOP, "KATE"),
						new Value<>(PlaceType.SHOPPING_ARCADE_PIXS_GYM, "PIX"),
						new Value<>(PlaceType.SHOPPING_ARCADE_RALPHS_SHOP, "RALPH"),
						new Value<>(PlaceType.SHOPPING_ARCADE_RESTAURANT, "RESTAURANT"),
						new Value<>(PlaceType.SHOPPING_ARCADE_VICKYS_SHOP, "VICKY"));
				AbstractPlaceType place = Util.randomItemFrom(places.keySet());
				destination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(place);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_REPEAT_START"));
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_REPEAT_"+places.get(place)));
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_REPEAT_END"));
			}
			
			Cell finalDestination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getClosestCell(destination.getLocation(), PlaceType.SHOPPING_ARCADE_PATH);
			Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, finalDestination.getLocation(), false);
			getNyan().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanFirstKissed)) {
				if(index==1) {
					return new Response("继续", "继续跟妮安在购物中心散步。", ROMANCE_WALK_INITIAL_CONTINUE);
				}
				
			} else {
				if(index==1) {
					return new Response("返回", "跟妮安走回她的商店。", ROMANCE_WALK_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_END_NO_EVENT"));
						}
					};
					
				} else if(index==2) {
					if(!Main.game.getPlayer().hasArms()) {
						return new Response("牵手", "由于你没有手臂，无法握住妮安的手！", null);
						
					} else if(Main.game.getPlayer().isArmMovementHindered()) {
						return new Response("牵手", "由于你的[pc.arm]行动受到了衣物的阻碍，无法很舒服地跟妮安牵手！", null);
						
					} else {
						return new Response("牵手", "走回商店的路上跟妮安牵手。", ROMANCE_WALK_END) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_END_HOLD_HANDS"));
								Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 50, 60));
							}
						};
					}
					
				} else if(index==3) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("接吻", "你不能使用嘴巴，所以你无法和妮安接吻！", null);
						
					} else {
						return new Response("接吻", "趁机在大庭广众下亲一亲妮安。", ROMANCE_WALK_END) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_END_KISS"));
								Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 50, 60));
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_WALK_INITIAL_CONTINUE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Cell destination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_RESTAURANT);
				
			Cell finalDestination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getClosestCell(destination.getLocation(), PlaceType.SHOPPING_ARCADE_PATH);
			Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, finalDestination.getLocation(), false);
			getNyan().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL_CONTINUE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("返回", "陪妮安回她的商店。", ROMANCE_WALK_INITIAL_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_WALK_INITIAL_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, false);
			getNyan().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("接吻", "你不能使用嘴巴，所以你无法和妮安接吻！", null);
					
				} else {
					return new Response("亲吻", "抓住机会亲亲妮安。", ROMANCE_WALK_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL_END_KISS"));
							Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 50, 60));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanFirstKissed, true);
						}
					};
				}
				
			} else if(index==2) {
				return new Response("还是算了", "之后仍然可以再经历这段剧情……", ROMANCE_WALK_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL_END_NO_KISS"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_WALK_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, false);
			getNyan().setLocation(Main.game.getPlayer(), false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWalked, true);
			Main.game.setResponseTab(1);
			
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
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_DATE_REQUESTED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanRestaurantDateRequested, true);
			Cell shoppingArcadeCell = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_SHOPPING_ARCADE);
			Cell nyanApartmentCell = Main.game.getWorlds().get(WorldType.DOMINION).getCell(new Vector2i(shoppingArcadeCell.getLocation().getX()-1, shoppingArcadeCell.getLocation().getY()));
			nyanApartmentCell.getPlace().setPlaceType(PlaceType.DOMINION_NYAN_APARTMENT);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_DATE_REQUESTED");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_MAKE_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "结束亲热环节。", POST_MAKEOUT);
				
			} else if(index==2) {
				return new ResponseSex("舔阴", "向欲火中烧的猫女建议，你可以在仓库里帮她舔阴。",
						true, true,
						new SMNyanSex(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.PERFORMING_ORAL_WALL)),
								Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotAgainstWall.BACK_TO_WALL))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
								}
							}
//							@Override
//							public Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> exposeAtStartOfSexMapExtendedInformation() {
//								Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> defaultMap = super.exposeAtStartOfSexMapExtendedInformation();
//								
//								defaultMap.get(true).putIfAbsent(getNyan(), new HashMap<>());
//								defaultMap.get(true).get(getNyan()).put(CoverableArea.VAGINA, new ArrayList<>());
//								
//								return defaultMap;
//							}
							@Override
							public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						POST_ORAL,
						UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true));
					}
				};
				
			} else if(index==3) {
				return new ResponseSex("指交", "掀开妮安的迷你裙，一面与她亲吻一面指交。",
						true, true,
						new SMNyanSex(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.STANDING_WALL)),
								Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotAgainstWall.BACK_TO_WALL))) {
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
						POST_ORAL,
						UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_FINGERING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueMouth.KISS_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), FingerVagina.FINGERING_START, false, true));
					}
				};
			}
			List<Response> receiveOralResponses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
				if(!penisAccess) {
					receiveOralResponses.add(new Response("接受口交", "因为你不能使用自己的阴茎，所以你没法让妮安吸你的鸡巴！", null)); 
				} else {
					receiveOralResponses.add(
							new ResponseSex("接受口交", "让妮安跪下来，在仓库里舔你的肉棒。",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.AGAINST_WALL,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotAgainstWall.BACK_TO_WALL)),
											Util.newHashMapOfValues(new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotAgainstWall.PERFORMING_ORAL_WALL))) {
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
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)));
										}
										@Override
										public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
											return false;
										}
									},
									null,
									null,
									POST_ORAL,
									UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_RECEIVE_BLOWJOB")) {
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
					receiveOralResponses.add(new Response("接受舔阴", "因为你的阴部无法被触及，所以你不能让妮安给你舔阴！", null)); 
				} else {
					receiveOralResponses.add(
							new ResponseSex("接受舔阴", "让妮安跪下来，在仓库中给你舔阴。",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.AGAINST_WALL,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotAgainstWall.BACK_TO_WALL)),
											Util.newHashMapOfValues(new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotAgainstWall.PERFORMING_ORAL_WALL))) {
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
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
										}
										@Override
										public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
											return false;
										}
									},
									null,
									null,
									POST_ORAL,
									UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_RECEIVE_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			if(Main.game.isAnalContentEnabled() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted)) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk)) {
					receiveOralResponses.add(new Response("后庭尝试", "问问妮安愿不愿意跟你尝试肛门的新玩法。", ANAL_TALK));
					
				} else {
					receiveOralResponses.add(
							new ResponseSex("进行舔肛",
								"让这个欲火中烧的猫女露出屁股来，为她舔弄外缘。",
								true, true,
								new SMNyanSex(
										SexPosition.AGAINST_WALL,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.STANDING_WALL)),
										Util.newHashMapOfValues(
												new Value<>(getNyan(), SexSlotAgainstWall.FACE_TO_WALL))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
										}
									}
									@Override
									public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
										return Util.newHashMapOfValues(
												new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.ANUS)));
									}
									@Override
									public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
										return false;
									}
								},
								null,
								null,
								POST_ORAL,
								UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_PERFORM_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueAnus.ANILINGUS_START, false, true));
							}
						});
				}
			}
			if(index>0 && index-4<receiveOralResponses.size()) {
				return receiveOralResponses.get(index-4);
			}
			return null;
		}
	};

	public static final DialogueNode ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "ANAL_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_MAKE_OUT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode POST_MAKEOUT = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "POST_MAKEOUT");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode POST_ORAL = new DialogueNode("结束", "妮安已经满足了……", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().cleanAllDirtySlots(true);
			getNyan().cleanAllClothing(false, false);
			Main.game.setResponseTab(1);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "POST_ORAL");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_GIFT = new DialogueNode("赠送礼物", "", true, true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
}
