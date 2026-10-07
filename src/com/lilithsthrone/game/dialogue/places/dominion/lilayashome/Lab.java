package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.submission.LyssiethPalaceDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.75
 * @version 0.3.9
 * @author Innoxia
 */
public class Lab {
	
	public static boolean isLilayaAngryAtPlayerDemonTF() {
		return Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")
				&& Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)
				&& Main.game.getNpc(Lilaya.class).getSubspeciesOverride()!=Subspecies.DEMON;
	}
	
	public static Cell addArthurRoom() {
		Vector2i labLocation = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_LAB).getLocation();
		Cell arthurRoomCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(labLocation.increment(1, 0));
		arthurRoomCell.setPlace(new GenericPlace(PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR), true);
		arthurRoomCell.addPlaceUpgrade(PlaceUpgrade.LILAYA_ARTHUR_ROOM);
		arthurRoomCell.setTravelledTo(true);
		return arthurRoomCell;
	}
	
	public static final DialogueNode LAB = new DialogueNode("莉莱雅的实验室", "", false) {
		@Override
		public String getContent() {
			if(Main.game.getNpc(Lilaya.class).getLocationPlaceType()==PlaceType.LILAYA_HOME_LAB) {
				if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0)) {
						return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_PREGNANCY_RISK");
					
					} else if(Main.game.getNpc(Lilaya.class).isPregnant() && Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
						return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_PREGNANT");
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaBirthNews)) {
						return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_PREGNANCY_RESOLVED");
					}
				}
			}
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB");
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
			if(index==1) {
				if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0)) {
						return new Response("进入", "莉莱雅实验室的门紧闭着。你最好晚点再来。", null);
						
					} else if((Main.game.getNpc(Lilaya.class).isPregnant() && Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getPlayer()))) {
						return new Response("进入", "莉莱雅实验室的门紧锁着。直到她解决了怀孕之前，你都无法进入。", null);
					}
				}
				
				if(Main.game.getNpc(Lilaya.class).getLocationPlaceType()!=PlaceType.LILAYA_HOME_LAB) {
					return new Response("进入", "莉莱雅实验室的门紧锁着，考虑到时间点，她现在估计在楼上睡觉。", null);
				}
				
				return new Response("进入", "穿过门，进入莉莱雅的实验室。", LAB_ENTRY) {
					@Override
					public void effects() {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roseToldOnYou)
								&& Main.game.getPlayer().getQuest(QuestLine.MAIN) != Quest.MAIN_1_I_ARTHURS_TALE
								&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults)
								&& Main.game.getNpc(Lilaya.class).getAffection(Main.game.getPlayer())>0) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), -10));
						}
					}
				};
				
			} else if(index==2) {
				if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && (Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0) || Main.game.getNpc(Lilaya.class).isPregnant())) {
					return null;
				}
				if(Main.game.getNpc(Lilaya.class).getLocationPlaceType()!=PlaceType.LILAYA_HOME_LAB || Main.game.getNpc(Arthur.class).getLocationPlaceType()==PlaceType.LILAYA_HOME_LAB) {
					return null;
				}
				return DialogueManager.getDialogueFromId("acexp_dominion_lilaya_lab_voyeurism").getResponse(0, 1);
			}
			return null;
		}
	};
	
	private static void setEntryFlags() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, false);
		if(Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults, false);
			if(Main.game.getNpc(Lilaya.class).isPregnant()) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaBirthNews, true);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaBirthNews, false);
			}
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaBirthNews, false);
		}
		if(Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
			Main.game.getNpc(Lilaya.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
	}
	
	private static List<Response> getLabEntryGeneratedResponses() {
		List<Response> generatedResponses = new ArrayList<>();
		
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaHug)) {
			generatedResponses.add(new Response("拥抱",
					"你今天已经突袭过莉莱雅一次了，而她正严防着第二次拥抱……"
							+ "<br/><i>你应该等明天再来抱她！</i>",
					null));
		} else if(isLilayaAngryAtPlayerDemonTF()) {
			generatedResponses.add(new Response("拥抱",
					"你因变成了完整体恶魔而遭到莉莱雅的怨恨，她现在绝对不想抱你！",
					null));
		} else {
			generatedResponses.add(new Response("拥抱", "面对莉莱雅，给她个大大的拥抱。", LAB_LILAYA_HUG));
		}
		
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
				generatedResponses.add(new Response("怀孕", "你需要完成莉莱雅的初步测试，她才会愿意帮你处理怀孕事宜。", null));
				
			} else {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == Quest.SIDE_PREGNANCY_CONSULT_LILAYA) {
					generatedResponses.add(new Response("怀孕", "跟莉莱雅聊聊你怀孕的事情。", LilayaBirthing.LILAYA_ASSISTS_PREGNANCY){
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY, Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE));
						}
					});
					
				} else {
					generatedResponses.add(new Response("怀孕", "跟莉莱雅聊聊你怀孕的事情。", LilayaBirthing.LILAYA_ASSISTS_PREGNANCY_REPEAT){
						@Override
						public void effects() {
							setEntryFlags();
						}
					});
				}
			}
		}

//		if(!Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
//			if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
//				generatedResponses.add(new Response("Incubation", "You'll need to complete Lilaya's initial tests before she'll agree to help you deal with the eggs you're currently incubating.", null));
//				
//			} else {
//				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION) == Quest.SIDE_INCUBATION_CONSULT_LILAYA) {
//					generatedResponses.add(new Response("Incubation", "Speak to Lilaya about the eggs you're currently incubating.", LilayaBirthing.LILAYA_ASSISTS_INCUBATION){
//						@Override
//						public void effects() {
//							setEntryFlags();
//							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_INCUBATION, Quest.SIDE_INCUBATION_LILAYA_HELP));
//						}
//					});
//					
//				} else {
//					generatedResponses.add(new Response("Incubation", "Speak to Lilaya about the eggs you're currently incubating.", LilayaBirthing.LILAYA_ASSISTS_INCUBATION_REPEAT){
//						@Override
//						public void effects() {
//							setEntryFlags();
//						}
//					});
//				}
//			}
//		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
				if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
					generatedResponses.add(new Response("精华&附魔", "你需要完成莉莱雅的初步测试，才好向她询问你吸收的那些奇怪能量。", null));
					
				} else {
					generatedResponses.add(new Response("精华&附魔", "向莉莱雅询问你吸收的奇怪能量。", LILAYA_EXPLAINS_ESSENCES){
						@Override
						public void effects() {
							setEntryFlags();
						}
					});
				}
				
			} else {
				generatedResponses.add(new Response("提取精华",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.essenceExtractionKnown)
								?"向莉莱雅询问是否可以使用她的仪器来抽取一些精华。"
								:"向莉莱雅询问有没有什么办法来抽出你吸收的精华",
							ESSENCE_EXTRACTION){
					@Override
					public void effects() {
						setEntryFlags();
					}
				});
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLAVERY)) {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY) == Quest.SIDE_SLAVER_NEED_RECOMMENDATION) {
				if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
					generatedResponses.add(new Response("奴隶", "你需要先完成莉莱雅的初步测试，再考虑向她申请推荐信。", null));
					
				} else {
					generatedResponses.add(new Response("奴隶", "向莉莱雅要一份贩奴许可需要的推荐信。", LILAYA_SLAVER_RECOMMENDATION){
						@Override
						public void effects() {
							setEntryFlags();
						}
					});
				}
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
			if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
				generatedResponses.add(new Response("住处", "你需要先通过莉莱雅的初步测试，才好问她能不能邀请朋友来家里住！", null));
				
			} else {
				generatedResponses.add(new Response("住处", "问莉莱雅能不能让你朋友住进宅邸里闲置的房间里。", LILAYA_FRIEND_ACCOMMODATION){
					@Override
					public void effects() {
						setEntryFlags();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ACCOMMODATION, Quest.SIDE_UTIL_COMPLETE));
					}
				});
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_DOLL_STORAGE) && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DOLL_STORAGE)) {
			if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
				generatedResponses.add(new Response("储存玩偶", "你需要先通过莉莱雅的初步测试，才好问她能否在着地中储存性爱玩偶！", null));
				
			} else {
				generatedResponses.add(new Response("储存玩偶", "问问莉莱雅能不能让你用宅邸中的闲置房间来存放性爱玩偶。", LILAYA_DOLL_STORAGE){
					@Override
					public void effects() {
						setEntryFlags();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DOLL_STORAGE, Quest.SIDE_UTIL_COMPLETE));
					}
				});
			}
		}
		
		
		
		if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaDateTalk)
				&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.knowsDate)) {
			generatedResponses.add(new Response("现在日期", "向莉莱雅询问你房间里的日历为什么比现在时间早了三年。", LILAYA_CURRENT_DATE_TALK) {
				@Override
				public void effects() {
					setEntryFlags();
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaDateTalk, true);
				}
			});
		}
		
		if(Main.game.getPlayer().hasItemType(ItemType.PRESENT) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent3)) {
			if(isLilayaAngryAtPlayerDemonTF()) {
				generatedResponses.add(new Response("送礼", "虽然你物品栏中有礼物，但莉莱雅并不愿意收礼，因为你已经成为了完全体恶魔，而她没有。", null));
				
			} else {
				generatedResponses.add(new Response("送礼", "从你的物品栏中挑一件礼物送给莉莱雅。", LILAYA_PRESENT) {
					@Override
					public void effects() {
						setEntryFlags();
						Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.PRESENT));
						
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent2)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.givenLilayaPresent3, true);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent1)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.givenLilayaPresent2, true);
							
						} else {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.givenLilayaPresent1, true);
						}
					}
				});
			}
		}
		
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent3)) {
			if(isLilayaAngryAtPlayerDemonTF()) {
				generatedResponses.add(new Response("艺伎(莉莱雅)", "莉莱雅现在没兴趣展示她的和服，更没兴趣和你做爱，除非她也变成了完全体恶魔。", null));
				
			} else {
				generatedResponses.add(new Response("艺伎(莉莱雅)", "问问莉莱雅愿不愿意穿上你送她的那件礼物。", LILAYA_GEISHA) {
					@Override
					public void effects() {
						setEntryFlags();
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_LILAYA, true);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_LILAYA, true);
						
						((Lilaya) Main.game.getNpc(Lilaya.class)).applyGeishaChange();
					}
				});
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_DADDY)) {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_ACCEPTED) {
				if(!Daddy.isAvailable()) {
					generatedResponses.add(new Response("与[daddy.name]见面", Daddy.getAvailabilityText(), null));
					
				} else if(Main.game.getPlayer().hasCompanions()) {
					generatedResponses.add(new Response("与[daddy.name]见面",
							"[style.italicsBad(在你队伍中已有同伴的情况下，你无法邀请莉莱雅和你一起去见[daddy.name]！)]",
							null));
					
				} else {
					generatedResponses.add(new Response("与[daddy.name]见面", "说服莉莱雅与你和[daddy.name]共进晚餐。", DaddyDialogue.CONVINCING_LILAYA) {
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_LILAYA_MEETING));
						}
					});
					
				}
	
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_DADDY, Quest.DADDY_LILAYA_MEETING)) {
				if(!Daddy.isAvailable()) {
					generatedResponses.add(new Response("拜访[daddy.name]", Daddy.getAvailabilityText(), null));
					
				} else if(Main.game.getPlayer().hasCompanions()) {
					generatedResponses.add(new Response("拜访[daddy.name]",
							"[style.italicsBad(在你队伍中已有同伴的情况下，你无法邀请莉莱雅和你一起去拜访[daddy.name]！)]",
							null));
					
				} else  {
					generatedResponses.add(new Response("拜访[daddy.name]", "问问莉莱雅愿不愿意和你一起去拜访[daddy.name]。", DaddyDialogue.MEETING) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							setEntryFlags();
							((Lilaya) Main.game.getNpc(Lilaya.class)).applyDinnerDateChange();
							
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_ENTRANCE);
							Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
	
							if(Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getPlayer().isCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class))) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(500));
							}
							if(Main.game.getNpc(Lilaya.class).isVisiblyPregnant() && !Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class))) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementMoney(500));
							}
						}
					});
					
				}
			}
		}
		
		if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
				&& Main.game.getPlayer().getClothingCurrentlyEquipped().stream().anyMatch(c -> c.isSelfTransformationInhibiting())
				&& Main.game.getPlayer().getClothingCurrentlyEquipped().stream().anyMatch(c -> c.isSealed())) {
			generatedResponses.add(new Response("关于封印的问题",
					"告诉莉莱雅，你身上封印着附魔衣物，但由于你衣物上的另一道附魔，你无法将其移除。"
							+ "<br/>[style.italicsMinorGood(莉莱雅将会移除你所有衣物上的“仆役”附魔！)]",
						LAB_JINX_REMOVAL){
				@Override
				public void effects() {
					for(AbstractClothing clothing : new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped())) {
						if(clothing.isSelfTransformationInhibiting()) {
							clothing.removeServitudeEnchantment();
						}
					}
				}
			});
		}
		
		if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_1) {
			if(Main.game.getCurrentDialogueNode()==LILAYA_ELLE_HELP) {
				generatedResponses.add(new Response("埃勒的位置", "你已经问过莉莱雅埃勒可能会在哪了！Y", null));
				
			} else {
				generatedResponses.add(new Response("埃勒的位置",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestLilayaHelp)
							?"问莉莱雅能不能给你点提示，埃勒究竟传送去了哪里。"
							:"向莉莱雅寻求帮助，猜想埃勒到底传送去了哪里。",
						LILAYA_ELLE_HELP){
					@Override
					public void effects() {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestLilayaHelp)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_ELLE_HELP_REPEAT"));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_ELLE_HELP"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestLilayaHelp, true);
						}
						setEntryFlags();
					}
				});
			}
		}
		
		if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.obtainedScientistClothing)) {
			generatedResponses.add(new Response("实验室服装",
					"问问莉莱雅能不能分你一件实验室外套。",
					LILAYA_SCIENTIST_OUTFIT){
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_scientist_lab_coat", false), false));
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_scientist_safety_goggles", false), false));
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.obtainedScientistClothing, true);
					setEntryFlags();
				}
			});
		}
		
		return generatedResponses;
	}
	
	public static final DialogueNode LAB_ENTRY = new DialogueNode("莉莱雅的实验室", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_I_ARTHURS_TALE) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR_PREGNANCY_BASE"));
					
					if(Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR_PREGNANT"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR_NOT_PREGNANT"));
					}
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR_BASE"));
				}

				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR"));
				
			} else {
				if(Main.game.getNpc(Lilaya.class).getRaceStage()==RaceStage.GREATER) { // Lilaya a full demon:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roseToldOnYou)) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ROSE_TOLD_ON_YOU"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_BASE_END"));
					}
					
				} else { // Lilaya is not a full demon:
					if(isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaBirthNews)) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_DEMON_REACTION_PREGNANCY_RESOLVED"));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_DEMON_REACTION_NO_PREGNANCY"));
						}
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_DEMON_REACTION"));
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults)) {
						if(Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_PREGNANT"));
							
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_NOT_PREGNANT"));
						}
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaBirthNews)) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_PREGNANCY_RESOLVED"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_BASE"));
	
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_NAUGHTY_ROSE"));
						
						if(isLilayaAngryAtPlayerDemonTF()) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roseToldOnYou)) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ROSE_TOLD_ON_YOU_DEMON"));
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_BASE_END_DEMON"));
							}
							
						} else {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roseToldOnYou)) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ROSE_TOLD_ON_YOU"));
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_BASE_END"));
							}
						}
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
				if(index == 1) {
					return new Response("同意",
							"你告诉莉莱雅你会说服莉西丝将她也转化成纯粹的恶魔。<br/>[style.italicsDemon(莉莱雅将会永转化为恶魔！)]",
							LAB_DEMON_TF_AGREE){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.RACE_DEMON;
						}
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_DEMON_TF_AGREE"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 50));
						}
					};
					
				} else if(index == 2) {
					return new Response("拒绝",
							"尝试说莉莱雅，她应该保持半恶魔的形态。<br/>[style.italics(你可以随时改变主意，告诉莉莱雅你会帮她变成纯粹的恶魔。)]",
							LAB_DEMON_TF_REFUSE){
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon, true);
						}
					};
				}
				return null;
					
			} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_I_ARTHURS_TALE || Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_J_ARTHURS_ROOM) {
				if(index == 1) {
					return new Response("同意", "你很清楚你的[lilaya.relation(pc)]在此等情绪下一定会怒不可遏，你别无他法……", LAB_ARTHURS_TALE){
						@Override
						public void effects() {
							setEntryFlags();
						}
					};
				}
				return null;
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults)
					&& Main.game.getNpc(Lilaya.class).isVisiblyPregnant()
					&& Main.game.getNpc(Lilaya.class).getRaceStage()!=RaceStage.GREATER) {
				if(index==0) {
					return new Response("离去", "莉莱雅在孕期结束之前，不愿意跟你说话……", LAB_EXIT_THROWN_OUT) {
						@Override
						public void effects() {
							setEntryFlags();
						}
					};
				}
				return null;
				
			} else {
				List<Response> generatedResponses = getLabEntryGeneratedResponses();
				
				// Return responses:
				if(index==0) {
					return new Response("离开", "告别莉莱雅并离开她的实验室。", LAB) {
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "你告诉莉莱雅自己必须要走了，道别之后就走出了实验室的大门。"
									+ "</p>");
						}
					};
					
				} else if (index == 1) {
					if(isLilayaAngryAtPlayerDemonTF()) {
						return new Response("完全的恶魔",
								"你告诉莉莱雅你会帮她说服她的母亲，将她转化成完全的恶魔<br/>[style.italicsDemon(莉莱雅将会永转化为恶魔！)]",
								LAB_DEMON_TF_AGREE) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.RACE_DEMON;
							}
							@Override
							public void effects() {
								setEntryFlags();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_DEMON_TF_AGREE_AFTER_REPEAT"));
								Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 50));
							}
						};
						
					} else {
						if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_A_LILAYAS_TESTS) {
							return new Response("测试", "告诉莉莱雅她可以测试你了。", AUNT_HOME_LABORATORY_TESTING){
								@Override
								public void effects() {
									setEntryFlags();
								}
							};
							
						} else {
							if(Main.game.getNpc(Arthur.class).getLocationPlace().getPlaceType().equals(PlaceType.LILAYA_HOME_LAB)) {
								return new Response("“测试”", "亚瑟还在实验室，莉莱雅不能对你进行“测试”。先给他找个房间住吧。", null);
								
							} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hadSexWithLilaya)) {
								return new Response("“测试”", "告诉莉莱雅她可以“测试”你了", AUNT_HOME_LABORATORY_TESTING_MORE_SEX){
									@Override
									public void effects() {
										setEntryFlags();
									}
								};
							} else {
								return new Response("测试", "告诉莉莱雅可以继续给你“测试了”。", AUNT_HOME_LABORATORY_TESTING_REPEAT){
									@Override
									public void effects() {
										setEntryFlags();
									}
								};
							}
						}
					}

				} else if(index<10) {
					if(index-2 < generatedResponses.size()) {
						return generatedResponses.get(index-2);
					} else {
						return null;
					}
					
				} else if(index==11 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
					// Teleport
					return new Response("莉西丝的办公室", "让莉莱雅使用回声石跟莉西丝联系，将你传送到她的办公室。", LyssiethPalaceDialogue.LYSSIETH_OFFICE_TALK){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
							if(Main.game.getPlayer().hasCompanions()) { //TODO test
								for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
									companion.setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE, false);
								}
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_TELEPORT_TO_LYSSIETHS_OFFICE_COMPANION", Main.game.getPlayer().getMainCompanion()));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_TELEPORT_TO_LYSSIETHS_OFFICE"));
							}
						}
					};
				}
				
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode LILAYA_END_SEX = new DialogueNode("起身", "莉莱雅确实得去工作了。", true) {

		@Override
		public String getDescription() {
			if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
				return "莉莱雅看上去很生气，你应该射在外面的……";
			} else {
				return "莉莱雅确实得去工作了。";
			}
		}

		@Override
		public String getContent() {
			if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_END_SEX_CREAMPIE");
			} else {
				if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lilaya.class))==0) {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_END_SEX_NO_ORGASM");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_END_SEX");
				}
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
						&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
						&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
					return new Response("离去", "或许最好还是让莉莱雅先冷静一会儿。", Lab.LAB_EXIT_THROWN_OUT){
						@Override
						public void effects() {
							if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
									&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults, true);
							}
							Main.game.getNpc(Lilaya.class).washAllOrifices(true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
							
							Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
					
				} else {
					return new Response("继续", "离开实验室，让莉莱雅继续她的工作。", Lab.LAB_EXIT) {
						@Override
						public void effects() {
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
							Main.game.getNpc(Lilaya.class).washAllOrifices(true);
							
							Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LAB_EXIT = new DialogueNode("莉莱雅的实验室", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_EXIT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(0, index);
		}
	};
	
	public static final DialogueNode LAB_EXIT_THROWN_OUT = new DialogueNode("莉莱雅的实验室", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_EXIT_THROWN_OUT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode LAB_DEMON_TF_AGREE = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_DEMON_TF_AGREE_CORE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("传送", "莉西丝将你们三个传送回她的宫殿……", LyssiethPalaceDialogue.LILAYA_DEMON_TF_START){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						if(Main.game.getPlayer().hasCompanions()) { //TODO test
							for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
								companion.setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
							}
						}
						Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Lyssieth.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_DEMON_TF_REFUSE = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_DEMON_TF_REFUSE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode LILAYA_PRESENT = new DialogueNode("莉莱雅的实验室", "", true) {
		
		@Override
		public String getContent() {

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent3)) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_PRESENT_3");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent2)) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_PRESENT_2");
					
			} else {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_PRESENT_1");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(0, index);
		}
	};
	
	public static final DialogueNode LILAYA_GEISHA = new DialogueNode("莉莱雅的卧室", "", true) {
		

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_GEISHA");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("性爱", "跟莉莱雅做爱。",
						Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Lilaya.class)),
						null,
						null),
						END_SEX_GEISHA,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_GEISHA_SEX_START")){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
					}
				};

			} if (index == 2) {
				return new ResponseSex("服从型性爱", "跟莉莱雅来一场服从型性爱。",
						Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Lilaya.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						END_SEX_GEISHA,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_GEISHA_SUBMISSIVE_SEX_START")){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
					}
				};

			} else if (index == 3) {
				return new Response("离开",
						"告诉莉莱雅她漂亮极了，但你并不想跟她做爱。",
						RoomPlayer.ROOM){
					@Override public void effects() {

						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						Main.game.getNpc(Lilaya.class).resetInventory(false);
						
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, true);
						
						Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
						
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_GEISHA_SEX_REFUSED"));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode END_SEX_GEISHA = new DialogueNode("结束", "莉莱雅倒在床上，满意地叹息着。", true) {
		
		@Override
		public String getDescription() {
			if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
				return "莉莱雅看上去很生气，你应该射在外面的……";
			} else {
				return "莉莱雅倒在床上，满意地叹息着。";
			}
		}

		@Override
		public String getContent() {
			if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "END_SEX_GEISHA_CREAMPIE");
				
			} else {
				if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lilaya.class))==0) {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "END_SEX_GEISHA_NO_ORGASM");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "END_SEX_GEISHA");
				}
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
						&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
						&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
					return new Response("离去", "或许最好还是让莉莱雅先冷静一会儿。", RoomPlayer.ROOM){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, true);
							if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
									&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults, true);
							}
							Main.game.getNpc(Lilaya.class).washAllOrifices(true);

							Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
					
				} else {
					return new Response("回到你的房间", "回到你的房间。", RoomPlayer.ROOM){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, true);
							Main.game.getNpc(Lilaya.class).washAllOrifices(true);
							
							Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LAB_JINX_REMOVAL = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_JINX_REMOVAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(0, index);
		}
	};

	public static final DialogueNode LILAYA_ELLE_HELP = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(0, index);
		}
	};
	
	public static final DialogueNode LILAYA_SCIENTIST_OUTFIT = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_SCIENTIST_OUTFIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(0, index);
		}
	};
	
	public static final DialogueNode LAB_LILAYA_HUG = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 5));
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaHug, true);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_LILAYA_HUG");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(0, index);
		}
	};
	
	public static final DialogueNode LILAYA_EXPLAINS_ESSENCES = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			if(getJinxedClothingExample() != null) {
				UtilText.addSpecialParsingString(getJinxedClothingExample().getName(), true);
				UtilText.addSpecialParsingString((getJinxedClothingExample().getClothingType().isPlural()?"它们":"它"), false);
				UtilText.addSpecialParsingString((getJinxedClothingExample().getClothingType().isPlural()?"这些":"这个"), false);
			}
			
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_EXPLAINS_ESSENCES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("哪里有问题？", "询问莉莱雅出什么问题了。", LILAYA_EXPLAINS_ESSENCES_2) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.essenceBottledDiscovered)
								&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.essenceOrgasmDiscovered)
								&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.essencePostCombatDiscovered)) {
							Main.game.getPlayer().incrementEssenceCount(1, false);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_EXPLAINS_ESSENCES_2 = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_EXPLAINS_ESSENCES_2");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("聆听", "听莉莱雅向你展示如何使用储存的精华来附魔物品。", LILAYA_EXPLAINS_ESSENCES_3);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_EXPLAINS_ESSENCES_3 = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_EXPLAINS_ESSENCES_3");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("表达感谢", "感谢莉莱雅教你如何附魔物品。", LAB_EXIT){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_EXPLAINS_ESSENCES_END")
								+ Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_UTIL_COMPLETE));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ESSENCE_EXTRACTION = new DialogueNode("莉莱雅的实验室", "-", true, false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "ESSENCE_EXTRACTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			
			if(index == 1) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE)))) {
					if(Main.game.getPlayer().getEssenceCount()>=1) {
						return new Response("提取(1)", "提取一份奥术精华。", ESSENCE_EXTRACTION_BOTTLED) {
							@Override
							public void effects() {
								Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE), false, false);
								int count = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE));
								
								Main.game.getTextEndStringBuilder().append(
										"<p style='text-align:center;'>"
											+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>添加至物品栏:</b><b>" + (ItemType.BOTTLED_ESSENCE_ARCANE).getDisplayName(true) + "</b>"
										+ "</p>"
										+ "<p>"
											+ "你的物品栏中现在有<b>"+count+"瓶"+(count>1?ItemType.BOTTLED_ESSENCE_ARCANE.getNamePlural(true):ItemType.BOTTLED_ESSENCE_ARCANE.getName(true))+"</b>。"
										+ "</p>");
								Main.game.getPlayer().incrementEssenceCount(-1, false);
							}
						};
						
					} else {
						return new Response("提取(1)", "你没有奥术精华！", null);
					}
				} else {
					return new Response("提取(1)", "你的物品栏中没有多余空间了！", null);
				}
				
				
			} else if(index == 2) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE)))) {
					if(Main.game.getPlayer().getEssenceCount()>=5) {
						return new Response("提取(5)", "提取五份奥术精华。", ESSENCE_EXTRACTION_BOTTLED) {
							@Override
							public void effects() {
								for(int i =0; i<5; i++) {
									Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE), false, false);
								}
								int count = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE));
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "你又拿来一些小瓶子，准备再重复几次提取过程……"
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>添加至物品栏:</b><b>5x</b><b>" + ItemType.BOTTLED_ESSENCE_ARCANE.getDisplayName(true) + "</b>"
										+ "</p>"
										+ "<p>"
											+ "你的物品栏中现在有<b>"+count+"瓶"+(count>1?ItemType.BOTTLED_ESSENCE_ARCANE.getNamePlural(true):ItemType.BOTTLED_ESSENCE_ARCANE.getName(true))+"</b>。"
										+ "</p>");
								Main.game.getPlayer().incrementEssenceCount(-5, false);
							}
						};
						
					} else {
						return new Response("提取(5)", "你没有足够的奥术精华！", null);
					}
				} else {
					return new Response("提取(5)", "你的物品栏没有多余的空间了！", null);
				}
				
			} else if(index == 3) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE)))) {
					if(Main.game.getPlayer().getEssenceCount()>=25) {
						return new Response("提取(25)", "提取二十五份奥术精华。", ESSENCE_EXTRACTION_BOTTLED) {
							@Override
							public void effects() {
								for(int i =0; i<25; i++) {
									Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE), false, false);
								}
								int count = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE));
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "你又拿来一些小瓶子，准备再重复几次提取过程……"
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>添加至物品栏:</b><b>25x</b><b>" + ItemType.BOTTLED_ESSENCE_ARCANE.getDisplayName(true) + "</b>"
										+ "</p>"
										+ "<p>"
											+ "你的物品栏中现在有<b>"+count+"瓶"+(count>1?ItemType.BOTTLED_ESSENCE_ARCANE.getNamePlural(true):ItemType.BOTTLED_ESSENCE_ARCANE.getName(true))+"</b>。"
										+ "</p>");
								Main.game.getPlayer().incrementEssenceCount(-25, false);
							}
						};
						
					} else {
						return new Response("提取(25)", "你没有足够的奥术精华！", null);
					}
				} else {
					return new Response("提取(25)", "你的物品栏没有多余的空间了！", null);
				}
				
			} else if(index == 4) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE)))) {
					if(Main.game.getPlayer().getEssenceCount()>=1) {
						return new Response("提取(所有)", "提取所有奥术精华。", ESSENCE_EXTRACTION_BOTTLED) {
							@Override
							public void effects() {
								for(int i =0; i<Main.game.getPlayer().getEssenceCount(); i++) {
									Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE), false, false);
								}
								int count = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE));
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "你又拿来一些小瓶子，准备再重复几次提取过程……"
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>添加至物品栏:</b><b>"+Main.game.getPlayer().getEssenceCount()+"x</b><b>"
												+ ItemType.BOTTLED_ESSENCE_ARCANE.getDisplayName(true) + "</b>"
										+ "</p>"
										+ "<p>"
											+ "你的物品栏中现在有<b>"+count+"瓶"+(count>1?ItemType.BOTTLED_ESSENCE_ARCANE.getNamePlural(true):ItemType.BOTTLED_ESSENCE_ARCANE.getName(true))+"</b>。"
										+ "</p>");
								Main.game.getPlayer().incrementEssenceCount(-Main.game.getPlayer().getEssenceCount(), false);
								
							}
						};
						
					} else {
						return new Response("提取(所有)", "你没有奥术精华！", null);
					}
				} else {
					return new Response("提取(所有)", "你的物品栏没有多余空间了！", null);
				}
				
			} else if (index == 0) {
				return new Response("返回", "停止提取精华。", LAB_ENTRY) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.essenceExtractionKnown, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ESSENCE_EXTRACTION_BOTTLED = new DialogueNode("莉莱雅的实验室", "-", true, false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "ESSENCE_EXTRACTION_BOTTLED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ESSENCE_EXTRACTION.getResponse(0, index);
		}
	};
	
	
	public static final DialogueNode LILAYA_CURRENT_DATE_TALK = new DialogueNode("莉莱雅的实验室", "-", true, false) {
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_CURRENT_DATE_TALK_KNOW_TRUTH");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_CURRENT_DATE_TALK");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("表示感谢", "感谢莉莱雅提供的信息(或者也没有提供)，思索还需不需要问些别的。", LAB_EXIT);
			} else {
				return null;
			}
		}
	};
	
	
	//----------------------------------

	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("回家", "询问莉莱雅有没有找到把你送回家的方法。", AUNT_HOME_LABORATORY_TESTING_ARTHUR){
					@Override
					public void effects() {
						if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_A_LILAYAS_TESTS) {
							Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME);
							((Arthur) Main.game.getNpc(Arthur.class)).generateNewTile();
						}
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_REPEAT = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_REPEAT");
			
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(
						!Main.game.getPlayer().isTaur()
							?"坐下"
							:"上前",
						"你很清楚为什么提到“测试”会很尴尬了……",
						AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA){
					@Override
					public void effects() {
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
						}
					}
				};

			} else if (index == 2) {
				return new Response("拒绝", "告诉莉莱雅你改变主意了。她或许会有一点失望，但如果你又有想法，也可以随时接受她的建议。", LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_LEAVE"));
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_MORE_SEX = new DialogueNode("", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_MORE_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().isTaur()) {
					return new ResponseSex("做爱",
							"跟莉莱雅做爱。",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMSitting(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lilaya.class), SexSlotSitting.SITTING_IN_LAP))),
							null,
							null,
							LILAYA_END_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_MORE_SEX_START")){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE, false);
						}
					};
				} else {
					return new ResponseSex("做爱",
							"跟莉莱雅做爱。",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Lilaya.class)),
									null,
									null,
									ResponseTag.PREFER_ORAL),
							null,
							null,
							LILAYA_END_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_MORE_SEX_START_TAUR")){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE, false);
						}
					};
				}


			} else if (index == 2) {
				return new Response("停止", "告诉莉莱雅你得走了。她肯定会有点失望，但你改变主意的话就可以随时回来。",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_MORE_SEX_STOP"));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_ARTHUR = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_ARTHUR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(
						"“测试”",
						"同意莉莱雅继续“测试”。你不太清楚她意欲何为，但你相信如果她要做些奇怪的事情，你可以随时制止。",
						AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA);

			} else if (index == 2) {
				return new Response("拒绝",
						"告诉莉莱雅你不想再参与这种事情了。她可能会有一点失望，但如果你改变了主意，随时可以回来。",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_ARTHUR_DECLINED"));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("张开嘴",
						"让莉莱雅把手指伸进你的嘴巴里。毕竟这也可能是测试的一环呢？",
						AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX);

			} else if (index == 2) {
				return new Response("阻止她",
						"从椅子上站起来，告诉莉莱雅她做得太过了。突然被打断后，她肯定会很沮丧，但之后如果你改变了主意，她还会愿意继续尝试。",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_DECLINED"));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().isTaur()) {
					return new ResponseSex("顺其自然",
							Main.game.getPlayer().hasFetish(Fetish.FETISH_INCEST)
								?"你知道只有一种结束的方法，而莉莱雅让你想起了你的姨妈莉莉，这反而让你更加兴奋……"
								:"你知道只有一种结束的方法。虽然莉莱雅让你想起你的[lilaya.relation(pc)]莉莉，但你觉得这不会影响你的享受……",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMSitting(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lilaya.class), SexSlotSitting.SITTING_IN_LAP))),
							null,
							null,
							LILAYA_END_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX_START")){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE, false);
						}
					};
					
				} else {
					return new ResponseSex("顺其自然",
							Main.game.getPlayer().hasFetish(Fetish.FETISH_INCEST)
								?"你知道只有一种结束的方法，而莉莱雅让你想起了你的姨妈莉莉，这反而让你更加兴奋……"
								:"你知道只有一种结束的方法。虽然莉莱雅让你想起你的[lilaya.relation(pc)]莉莉，但你觉得这不会影响你的享受……",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Lilaya.class)),
									null,
									null,
									ResponseTag.PREFER_ORAL),
							null,
							null,
							LILAYA_END_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX_START_TAUR")){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE, false);
						}
					};
				}

			} else if (index == 2) {
				return new Response("阻止她",
						"从椅子上站起来，告诉莉莱雅她做得太过了。突然被打断后，她肯定会很沮丧，但之后如果你改变了主意，她还会愿意继续尝试。",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX_DECLINED"));
					}
				};

			} else {
				return null;
			}
		}
	};

	private static AbstractClothing getJinxedClothingExample() {
		for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
			if(c.isSealed()) {
				return c;
			}
		}
		return null;
	}
	
	public static final DialogueNode LILAYA_FRIEND_ACCOMMODATION = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_FRIEND_ACCOMMODATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "你获得了莉莱雅的许可，可以邀请朋友来家里住了！", LAB_EXIT);
			}
			return null;
		}
	};
	
	public static final DialogueNode LILAYA_DOLL_STORAGE = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_DOLL_STORAGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "你现在已获得莉莱雅的许可，可以在她的宅邸中存放玩偶！", LAB_EXIT);
			}
			return null;
		}
	};
	
	public static final DialogueNode LILAYA_SLAVER_RECOMMENDATION = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_SLAVER_RECOMMENDATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("提供住处", "你也同意莉莱雅说的，奴隶需要一个住的地方。", LILAYA_SLAVER_RECOMMENDATION_SLAVE_ACCOMMODATION) {
					@Override
					public void effects() {
						if (Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY) == Quest.SIDE_SLAVER_NEED_RECOMMENDATION) {
							Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLAVERY, Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LILAYA_SLAVER_RECOMMENDATION_SLAVE_ACCOMMODATION = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_SLAVER_RECOMMENDATION_SLAVE_ACCOMMODATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "既然已经拿到了莉莱雅的推荐信，就应该回奴隶巷找[finch.name]了。", LAB_EXIT);
			}
			return null;
		}
	};

	public static final DialogueNode LAB_ARTHURS_TALE = new DialogueNode("莉莱雅的实验室", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ARTHURS_TALE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseEffectsOnly("清理储物间", "去实验室的储物间帮萝丝一起打扫，当作亚瑟的卧室。") {
					@Override
					public void effects() {
						Cell arthurRoomCell = addArthurRoom();
						
						Main.game.getPlayer().setLocation(arthurRoomCell);
						Main.game.getNpc(Arthur.class).setLocation(arthurRoomCell, true);
						
						Main.game.setContent(new Response("", "", PlaceUpgrade.LILAYA_ARTHUR_ROOM.getInstallationDialogue(arthurRoomCell)));
					}
				};
			}
			return null;
		}
	};
	
}
