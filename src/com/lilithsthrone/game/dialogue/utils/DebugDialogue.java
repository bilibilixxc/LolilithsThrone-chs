package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.fields.ElisAlleywayAttacker;
import com.lilithsthrone.game.character.npc.misc.BasicDoll;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.npc.submission.SubmissionAttacker;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityCategory;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.moves.CombatMove;
import com.lilithsthrone.game.combat.moves.CombatMoveCategory;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlags;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.EncounterType;
import com.lilithsthrone.game.dialogue.encounters.ExternalEncounterData;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.AbstractSetBonus;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.RandomEnchantment;
import com.lilithsthrone.game.inventory.enchanting.RandomPotionEnchantment;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.outfit.AbstractOutfit;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.BaseColour;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

import static com.lilithsthrone.LolificationProject.ModCommon.NPCSummoner.SUMMON_NPC;

/**
 * What a mess...
 * 
 * @since 0.1.0
 * @version 0.4.10.10
 * @author Innoxia
 */
public class DebugDialogue {

	private static String dollID;
	
	private static Femininity filterFemininity = Femininity.ANDROGYNOUS;
	
	private static GameCharacter targetedCharacter;
	
	public static final DialogueNode DEBUG_MENU = new DialogueNode("强力工具", "打开Debug菜单。", false) {
//		@Override
//		public void applyPreParsingEffects() {
//			targetedCharacter = null; // Reset this value when opening the debug menu to make sure that the targetedCharacter is not from a previous save file
//		}
		@Override
		public void applyPreParsingEffects() {
			// Just in case this dialogue node is accessed before initialising targetedCharacter, or if targetedCharacter is no longer present:
			if(targetedCharacter==null
					|| (!targetedCharacter.isPlayer() && !Main.game.getCharactersPresent().contains(targetedCharacter))) {
				targetedCharacter = Main.game.getPlayer();
			}
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "你刚一念完咒语，便听到身后传来咚咚咚的声音。"
						+ "你转过身，看到一个小金属装置躺在地上，有点像旧世界里的电视遥控器。"
					+ "</p>"

					+ "<p>"
						+ "你俯下身把它捡起来，当你把它在手中翻转时，你看到背面贴着一个小标签。"
						+ "有人在上面写了一条信息，你读了出来：" 
					+ "</p>"

					+ "<p style='text-align:center;'><i>"
						+ "嗨，[pc.name]！<br/>"
						+ "看来你知道神奇的调试代码！给你提个醒，这里的所有选项都很容易出异常！"
						+ "请记住，生成出来的衣服和物品有许多还没实装进游戏，所以可能会触发一些意外。"
						+ "谢谢你玩我的游戏！<br/><br/>"
						+ "~Innoxia~<br/></i>"
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "主要";
				
			} else if(index == 1) {
				return "状态";
				
			} else if(index == 2) {
				return "杂项";

			} else if(index == 3) {
				return "物品";
				
			} else if(index == 4) {
				return "个性";
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("返回", "", DEBUG_MENU){
					@Override
					public DialogueNode getNextDialogue() {
						if(Main.game.isInSex()) {
							return Main.sex.SEX_DIALOGUE;
						}
						return Main.game.getDefaultDialogue(false);
					}
				};
			}
			
			if(responseTab==0) {
				if (index == 1) {
					return new Response("打开解析器", "测试解析器。", PARSER);
					
				} else if (index == 2) {
					return new Response("Debug模式：", "无需完成任务，即刻解锁附魔和奴役。", DEBUG_MENU){
						@Override
						public String getTitle() {
							return "Debug模式："+(Main.game.isDebugMode()?"[style.colourGood(开启)]":"[style.colourDisabled(关闭)]");
						}
						
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.debugMode, !Main.game.isDebugMode());
							Main.getProperties().savePropertiesAsXML();
						}
					};
					
				} else if (index == 3) {
					return new Response("显示全地图：", "显示所有地图地块。", DEBUG_MENU){
						@Override
						public String getTitle() {
							return "显示全地图："+(Main.game.isMapReveal()?"[style.colourGood(开启)]":"[style.colourDisabled(关闭)]");
						}
						
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.mapReveal, !Main.game.isMapReveal());
							Main.getProperties().savePropertiesAsXML();
						}
					};
					
				} else if (index == 4) {
					return new Response("显示身体信息：",
							"开启时，衣物不再遮挡物品栏位，你无需亲眼看就能知道所有角色的性器官外形。"
									+ "也会解锁特殊NPC的裸体和内衣图像。",
							DEBUG_MENU){
						@Override
						public String getTitle() {
							return "显示身体信息："+(Main.game.isConcealedSlotsReveal()?"[style.colourGood(开启)]":"[style.colourDisabled(关闭)]");
						}
						
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.concealedSlotsReveal, !Main.game.isConcealedSlotsReveal());
							Main.getProperties().savePropertiesAsXML();
						}
					};
					
				} else if (index == 5) {
					if(!Util.newArrayListOfValues(
							PlaceType.DOMINION_BACK_ALLEYS,
							PlaceType.DOMINION_CANAL,
							PlaceType.DOMINION_CANAL_END,
							PlaceType.DOMINION_ALLEYS_CANAL_CROSSING,
							PlaceType.SUBMISSION_TUNNELS,
							PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley"),
							PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_abandoned_bakery")
							).contains(Main.game.getPlayer().getLocationPlace().getPlaceType())) {
						return new Response("生成袭击者", "你只能在以下地块生成袭击者：御城区的小巷和运河、屈城区的隧道、伊利斯的小巷。", null);
					}
					if(!Main.game.getNonCompanionCharactersPresent().isEmpty()) {
						return new Response("生成袭击者", "你只能在空闲地块上生成袭击者。", null);
					}
					return new Response("生成袭击者", "在此地块上生成一名袭击者。", ATTACKER_SPAWN_MENU);
					
				} else if (index == 6) {
					return new Response("生成菜单", "查看衣物、武器和道具生成菜单。", SPAWN_MENU);
					
				} else if (index == 7) {
					return new Response("转化", "转化你的身体。", BodyChanging.BODY_CHANGING_CORE) {
						@Override
						public void effects() {
							BodyChanging.setTarget(Main.game.getPlayer(), true);
						}
					};
					
				} else if (index == 8) {
					return new Response("设置身体材质", "调整你身体的材质。", BODY_PART_MATERIAL);
					
				} else if (index == 9) {
					return new Response("重置种族", "查看重置种族选项。", BODY_PART_RACE_RESET);
					
				} else if (index == 10) {
					return new Response("生成套装", "查看游戏中的所有衣物/武器套装并生成它们。", SPAWN_MENU_SET);
					
				} else if (index == 11) {
					return new Response(UtilText.formatAsMoney(100_000, "span"), "增加十万火币。", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(100_000);
						}
					};
					
				} else if (index == 12) {
					return new Response("+1000精华", "增加1000奥术精华。", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementEssenceCount(1000, false);
						}
					};
					
				} else if (index == 13) {
//					return new Response("Very long action text for testing", "Very long action text for testing.", null);
					return new Response("遭遇", "查看该地块上所有可触发的遭遇。", ENCOUNTER_MENU);
					
				} else if (index == 14) {
					return new Response("解锁贴图: ",
							"使所有衣物贴图可用。这将忽略贴图的unavailabilityText和availabilityText。因此，如果你要测试这些内容，打开此选项将造成问题！",
							DEBUG_MENU){
						@Override
						public String getTitle() {
							return "解锁贴图: "+(Main.game.isAllStickersUnlocked()?"[style.colourGood(开)]":"[style.colourDisabled(关)]");
						}
						
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.allStickersUnlocked, !Main.game.isAllStickersUnlocked());
							Main.getProperties().savePropertiesAsXML();
						}
					};
					
				} else if(index==15) {
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE)) {
						return new Response("跳过御城区任务", "你已经完成了屈城区任务线之前的所有主线任务！", null);
						
					} else {
						return new Response("跳过御城区任务", "跳过屈城区任务线之前的所有主线任务。", DEBUG_MENU){
							@Override
							public void effects() {
								List<Quest> dominionSkipQuests = Util.newArrayListOfValues(
										Quest.MAIN_1_A_LILAYAS_TESTS,
										Quest.MAIN_1_B_DEMON_HOME,
										Quest.MAIN_1_C_WOLFS_DEN,
										Quest.MAIN_1_D_SLAVERY,
										Quest.MAIN_1_E_REPORT_TO_HELENA,
										Quest.MAIN_1_F_SCARLETTS_FATE,
										Quest.MAIN_1_G_SLAVERY,
										Quest.MAIN_1_H_THE_GREAT_ESCAPE,
										Quest.MAIN_1_I_ARTHURS_TALE,
										Quest.MAIN_2_A_INTO_THE_DEPTHS
										);
								for(int i=0; i<dominionSkipQuests.size()-1; i++) {
									Quest q = dominionSkipQuests.get(i);
									if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==q) {
										q.applySkipQuestEffects();
										Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, dominionSkipQuests.get(i+1));
									}
								}
							}
						};
					}
					
				} else if(index==16) {
					if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_2_A_INTO_THE_DEPTHS)) {
						return new Response("跳过屈城区任务", "你当前的主线进度不够，还不能跳过屈城区任务。", null);
						
					} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
						return new Response("跳过屈城区任务", "你已经完成了伊利斯任务线之前的所有主线任务！", null);
						
					} else {
						return new Response("跳过屈城区任务", "跳过伊利斯任务线之前的所有主线任务。", DEBUG_MENU){
							@Override
							public void effects() {
								List<Quest> submissionSkipQuests = Util.newArrayListOfValues(
										Quest.MAIN_2_A_INTO_THE_DEPTHS,
										Quest.MAIN_2_B_SIRENS_CALL,
										Quest.MAIN_2_C_SIRENS_FALL,
										Quest.MAIN_2_D_MEETING_A_LILIN,
										Quest.MAIN_3_ELIS
										);
								for(int i=0; i<submissionSkipQuests.size()-1; i++) {
									Quest q = submissionSkipQuests.get(i);
									if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==q) {
										q.applySkipQuestEffects();
										Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, submissionSkipQuests.get(i+1));
									}
								}
							
							}
						};
					}
					
				} else if(index==25) {
					return new Response("附魔服装", "查看所有可生成的随机附魔服装列表。", RANDOM_ENCHANTMENT_CLOTHING);
					
				} else if(index==26) {
					return new Response("附魔魔药", "查看所有可生成的随机附魔妖狐魔药列表。", RANDOM_ENCHANTMENT_POTIONS);

				} else if(index==29) {
					return new Response("Summon NPCS", "Summon NPCS.", SUMMON_NPC);
				}

				
			} else if(responseTab==1) {
				if (index == 1) {
					return new Response("<span style='color:"+PresetColour.GENERIC_EXPERIENCE.toWebHexString()+";'>+500经验</span>(自己)", "给你自己加500经验值", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementExperience(500, false);
						}
					};
					
				} else if (index == 2) {
					if(!Main.game.getPlayer().hasCompanions()) {
						return new Response("+500经验(队伍)", "你没有任何盟友，所以没人可给……", null);
					}
					return new Response("<span style='color:"+PresetColour.GENERIC_EXPERIENCE.toWebHexString()+";'>+500经验</span>(队伍)", "给所有成员都加500经验。", DEBUG_MENU){
						@Override
						public void effects() {
							for(GameCharacter character : Main.game.getPlayer().getParty()) {
								character.incrementExperience(500, false);
							}
						}
					};
					
				} else if(index==3) {
					return new Response("<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>+5</span> <span style='color:"+PresetColour.ATTRIBUTE_PHYSIQUE.toWebHexString()+";'>体格</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_PHYSIQUE, 5);
						}
					};
					
				} else if(index==4) {
					return new Response("<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>+5</span> <span style='color:"+PresetColour.ATTRIBUTE_ARCANE.toWebHexString()+";'>奥术</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_ARCANE, 5);
						}
					};
					
				} else if(index==5) {
					return new Response("<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>+5</span> <span style='color:"+PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString()+";'>堕落</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_CORRUPTION, 5);
						}
					};
					
				} else if(index==6) {
					return new Response("<span style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>所有属性拉满</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().setAttribute(Attribute.MAJOR_PHYSIQUE, 100);
							Main.game.getPlayer().setAttribute(Attribute.MAJOR_ARCANE, 100);
							Main.game.getPlayer().setAttribute(Attribute.MAJOR_CORRUPTION, 100);
						}
					};
					
				}  else if(index==7) {
					return new Response("<span style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>+1</span> <span style='color:"+PresetColour.PERK.toWebHexString()+";'>天赋点</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementPerkPoints(1);
						}
					};
					
				} else if(index==8) {
					return new Response("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>-5</span> <span style='color:"+PresetColour.ATTRIBUTE_PHYSIQUE.toWebHexString()+";'>体格</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_PHYSIQUE, -5);
						}
					};
				} else if(index==9) {
					return new Response("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>-5</span> <span style='color:"+PresetColour.ATTRIBUTE_ARCANE.toWebHexString()+";'>奥术</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_ARCANE, -5);
						}
					};
				} else if(index==10) {
					return new Response("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>-5</span> <span style='color:"+PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString()+";'>堕落</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_CORRUPTION, -5);
						}
					};
					
				} else if (index == 11) {
					return new Response("重置法术", "重置所有法术和升级，并移除所有法术升级点数。", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().resetSpells();
							Main.game.getPlayer().clearSpellUpgradePoints();
							
						}
					};
					
				} else if (index == 12) {
					return new Response("+10法术点", "全学派+10法术点。", DEBUG_MENU){
						@Override
						public void effects() {
							for(SpellSchool school : SpellSchool.values()) {
								Main.game.getPlayer().incrementSpellUpgradePoints(school, 10);
							}
						}
					};
					
				} else if (index == 13) {
					return new Response("[style.colourGood(完成)]百科全书", "解锁共享百科全书中的每个条目。", DEBUG_MENU){
						@Override
						public void effects() {
							Main.getProperties().completeSharedEncyclopedia();
							Main.saveProperties();
						}
					};
					
				} else if (index == 14) {
					return new Response("[style.colourBad(重置)]共享百科全书", "删除共享百科全书中的所有条目。", DEBUG_MENU){
						@Override
						public void effects() {
							Main.getProperties().resetRaceDiscovered();
							Main.getProperties().resetAdvancedRaceKnowledge();
							Main.getProperties().resetItemDiscovered();
							Main.getProperties().resetClothingDiscovered();
							Main.getProperties().resetWeaponDiscovered();
								
							Main.saveProperties();
						}
					};
					
				} else if (index == 15) {
					return new Response("重置童贞", "删除所有失贞信息，并将所有腔穴重设为“保留贞操”。", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().completeVirginityReset();
						}
					};
					
				}
				
			} else if(responseTab==2) {
				if (index == 1) {
					return new Response("测试颜色", "可读性测试文本", COLOURS){
						@Override
						public void effects() {
							coloursSB = new StringBuilder("<p>");
							for (Colour c : PresetColour.getAllPresetColours()) {
								coloursSB.append(c.getId() + ": <span style='color:" + c.toWebHexString() + ";'>可读性测试文本。</span><br/>");
							}
							coloursSB.append("<br/><br/>");
							for (BaseColour bc : BaseColour.values()) {
								coloursSB.append(bc.toString() + ": <span style='color:" + bc.toWebHexString() + ";'>可读性测试文本。</span><br/>");
							}
							coloursSB.append("</p>");
							
						}
					};
					
				} else if (index == 2) {
					return new Response("后代", "查看可找到的后代", OFFSPRING);
					
				} else if (index == 3) {
					return new Response("[style.boldBad(月份-)]", "当前月份数减1。", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.setStartingDateMonth(Main.game.getStartingDate().getMonth().minus(1));
						}
					};
					
				} else if (index == 4) {
						return new Response("[style.boldGood(月份+)]", "当前月份数加1。", DEBUG_MENU){
							@Override
							public void effects() {
								Main.game.setStartingDateMonth(Main.game.getStartingDate().getMonth().plus(1));
							}
						};
						
				}
				else if (index == 5) {
					return new Response("战斗动作", "查看游戏中所有可用战斗动作的列表。", COMBAT_MOVES);
					
//					if(!Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.DOMINION_BACK_ALLEYS)) {
//						return new Response("Lumi test", "Lumi can only be spawned in alleyway tiles.", null);
//						
//					} else if(!Main.game.getNonCompanionCharactersPresent().isEmpty()) {
//						return new Response("Lumi test", "Lumi can only be spawned into empty tiles!", null);
//						
//					}  else if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
//						return new Response("Lumi test", "Lumi can not be spawned during an arcane storm.", null);
//					}
//					return new ResponseEffectsOnly("Lumi test", "Spawn Lumi to test her dialogue and scenes."){
//						@Override
//						public void effects() {
//							Main.game.setContent(new Response("", "", LumiDialogue.LUMI_APPEARS));
//						}
//					};
					
				} 
				else if (index == 6) {
					return new Response("布拉克斯的复仇", "布拉克斯射在了你的阴道里！", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().guaranteePregnancyOnNextRoll();
							if(Main.game.getPlayer().hasHymen()) {
								Main.game.getPlayer().setVaginaVirgin(false);
								SexType sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
								Main.game.getPlayer().setVirginityLoss(sexType, Main.game.getNpc(Brax.class),"while fooling around in the debug menu");
							}
							Main.game.getPlayer().ingestFluid(Main.game.getNpc(Brax.class), Main.game.getNpc(Brax.class).getCum(), SexAreaOrifice.VAGINA, 1000);
						}
					};
					
				} else if (index == 7) {
					return new Response("莉莱雅的伪善", "莉莱雅射在了你的阴道里！", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().guaranteePregnancyOnNextRoll();
							if(Main.game.getPlayer().hasHymen()) {
								Main.game.getPlayer().setVaginaVirgin(false);
								SexType sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
								Main.game.getPlayer().setVirginityLoss(sexType, Main.game.getNpc(Lilaya.class), "while fooling around in the debug menu");
							}
							Main.game.getPlayer().ingestFluid(Main.game.getNpc(Lilaya.class), Main.game.getNpc(Lilaya.class).getCum(), SexAreaOrifice.VAGINA, 1000);
						}
					};
					
				} else if (index == 8) {
					return new Response("莉莱雅的测试", "自动完成莉莱雅的附魔任务，使你立刻学会附魔。", DEBUG_MENU){
						@Override
						public void effects() {
							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)){ //If the player hasn't completed the enchantment quest
								if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)){ //But has started it
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_UTIL_COMPLETE)); //Finish it
								}
								else{ //But hasn't started it
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)); //Start the quest
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_UTIL_COMPLETE)); //And finish it
								}
							}
						}
					};

				} else if (index == 9) {
					return new Response("+1伊波娜印章", "获得一个怀孕轮盘赌奖励的印章。", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().eponaStamps += 1;
							Main.game.getTextEndStringBuilder().append("印章数加1，现在你拥有"  + Main.game.getDialogueFlags().eponaStamps + "枚印章");
						}
					};

				} else if(index == 10){
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLAVERY)) { //If the player doesn't have the slaver license
						return new Response("获得贩奴许可", "自动完成获得贩奴许可的任务。如果你还未接取任务，这将开启并完成任务。", DEBUG_MENU){
							@Override
							public void effects() {
								if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLAVERY)){
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_SLAVERY));
								}
								List<Quest> slaverSkipQuests = Util.newArrayListOfValues(
										Quest.SIDE_SLAVER_NEED_RECOMMENDATION,
										Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED,
										Quest.SIDE_UTIL_COMPLETE);
								for(int i=0; i<slaverSkipQuests.size()-1; i++) {
									Quest q = slaverSkipQuests.get(i);
									if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY)==q) {
										q.applySkipQuestEffects();
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLAVERY, slaverSkipQuests.get(i+1)));
									}
								}
							}
						};
						
					} else {
						return new Response("获得贩奴许可", "你已经完成了获取贩奴许可的支线任务。", null);
					}
					
				} else if(index == 11){
					return new Response("半人马", "一只野生的半人马出现了！(建议在无特征的地块上使用，否则可能会造成损坏。)", CENTAUR_SEX){
						@Override
						public void effects(){
							NPC target = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false,  false), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false, (s)->s!=Subspecies.CENTAUR);
							try {
								Main.game.addNPC(target, false);
								Main.game.setActiveNPC(target);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					};
				} else if (index == 12) {
					return new Response("[style.boldMinorBad(日期-)]", "当前日期数减1。", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.incrementStartingDateDays(-1);
						}
					};
					
				} else if (index == 13) {
						return new Response("[style.boldMinorGood(日期+)]", "当前日期数加1。", DEBUG_MENU){
							@Override
							public void effects() {
								Main.game.incrementStartingDateDays(1);
							}
						};
						
				} else if (index == 14) {
					return new Response("+1000[style.mule]点数", "给予你最大额度的[style.mule]点数(在御城速递取得[style.mule]资格后可以使用)。", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(1000));
						}
					};
					
				} else if(index==15) {
					return new Response("无脑大胸！", "将游戏中的每名女性NPC都变成丰满邋遢的无脑大胸女。<br/>[style.italicsBad(警告！此行为无法取消！)]", DEBUG_MENU){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.BASE_PINK_DEEP;
						}
						@Override
						public void effects() {
							for(NPC npc : Main.game.getAllNPCs()) {
								if(npc.isFeminine()) {
									npc.addFetish(Fetish.FETISH_BIMBO);
									npc.addPersonalityTrait(PersonalityTrait.SLOVENLY);
									if(npc.getBreastSize().getMeasurement()<CupSize.E.getMeasurement()) {
										npc.setBreastSize(CupSize.E);
									}
								}
							}
						}
					};
					
				} else if(index==16) {
					return new Response("虎人模式", "将游戏中的每名NPC都变成纯兽虎化形。<br/>[style.italicsBad(警告！此行为无法取消！)]", DEBUG_MENU){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.RACE_CAT_MORPH_TIGER;
						}
						@Override
						public void effects() {
							for(NPC npc : Main.game.getAllNPCs()) {
								Main.game.getCharacterUtils().reassignBody(
										npc,
										npc.getBody(),
										npc.getGender(),
										Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_tiger"),
										RaceStage.GREATER,
										false);
							}
						}
					};
					
				} else if(index==17) {
					return new Response("毛茸茸模式", "每名皮毛种类允许的NPC都会获得“松软”修饰。<br/>[style.italicsBad(警告！此行为无法取消！)]", DEBUG_MENU){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.BASE_ROSE;
						}
						@Override
						public void effects() {
							for(NPC npc : Main.game.getAllNPCs()) {
								for(BodyPartInterface part : npc.getBody().getAllBodyParts()) {
									AbstractBodyCoveringType bct = npc.getCovering(part);
									if(bct!=null
											&& (bct.getNaturalModifiers().contains(CoveringModifier.FLUFFY) || bct.getExtraModifiers().contains(CoveringModifier.FLUFFY))) {
										npc.getCovering(bct).setModifier(CoveringModifier.FLUFFY);
									}
								}
								
							}
						}
					};
					
				} else if(index==18) {
					return new Response("哞哞模式",
							"每名女性NPC的乳房尺寸都会增加5，"
									+ "获得“"+Fetish.FETISH_LACTATION_SELF.getName(null)+"”性癖，"
									+ "获得500ml乳汁储量，"
									+ "屁股尺寸增加1，"
									+ "同时臀部尺寸增加1。"
							+ "<br/>[style.italicsBad(警告！此行为无法取消！)]",
							DEBUG_MENU){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.RACE_COW_MORPH;
						}
						@Override
						public void effects() {
							for(NPC npc : Main.game.getAllNPCs()) {
								if(npc.isFeminine()) {
									npc.addFetish(Fetish.FETISH_LACTATION_SELF);
									npc.incrementBreastMilkStorage(500);
									npc.incrementBreastSize(5);
									npc.incrementAssSize(1);
									npc.incrementHipSize(1);
								}
							}
						}
					};
				} else if(index==19) {
					return new Response("鼠人模式", "将游戏中的每名NPC都变成纯兽鼠化形。<br/>[style.italicsBad(警告！此行为无法取消！)]", DEBUG_MENU){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.RACE_RAT_MORPH;
						}
						@Override
						public void effects() {
							for(NPC npc : Main.game.getAllNPCs()) {
								Main.game.getCharacterUtils().reassignBody(
										npc,
										npc.getBody(),
										npc.getGender(),
										Subspecies.RAT_MORPH,
										RaceStage.GREATER,
										false);
							}
						}
					};
					
				} else if(index>=20 && index<=26) {
					ArrayList<AbstractPerk> powerPerks = Util.newArrayListOfValues(Perk.POWER_OF_LIRECEA_1,
							Perk.POWER_OF_LOVIENNE_2,
							Perk.POWER_OF_LASIELLE_3,
							Perk.POWER_OF_LYSSIETH_4,
							Perk.POWER_OF_LUNETTE_5,
							Perk.POWER_OF_LYXIAS_6,
							Perk.POWER_OF_LISOPHIA_7);

					ArrayList<AbstractPerk> powerPerksDemon = Util.newArrayListOfValues(Perk.POWER_OF_LIRECEA_1_DEMON,
							Perk.POWER_OF_LOVIENNE_2_DEMON,
							Perk.POWER_OF_LASIELLE_3_DEMON,
							Perk.POWER_OF_LYSSIETH_4_DEMON,
							Perk.POWER_OF_LUNETTE_5_DEMON,
							Perk.POWER_OF_LYXIAS_6_DEMON,
							Perk.POWER_OF_LISOPHIA_7_DEMON);
					
					AbstractPerk perk = powerPerks.get(index-20);
					AbstractPerk perkDemon = powerPerksDemon.get(index-20);
					
					return new Response("莉琳长老天赋", "切换天赋开关状态。", DEBUG_MENU) {
						@Override
						public String getTitle() {
							return perk.getName(null)+": "+(Main.game.getPlayer().hasPerkAnywhereInTree(perk) || Main.game.getPlayer().hasPerkAnywhereInTree(perkDemon)?"[style.colourGood(开启)]":"[style.colourDisabled(关闭)]");
						}
						
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasPerkAnywhereInTree(perk) || Main.game.getPlayer().hasPerkAnywhereInTree(perkDemon)) {
								Main.game.getPlayer().removeSpecialPerk(perk);
								Main.game.getPlayer().removeSpecialPerk(perkDemon);
							} else {
								if(Main.game.getPlayer().getTrueRace()==Race.DEMON) {
									Main.game.getPlayer().addSpecialPerk(perkDemon);
								} else {
									Main.game.getPlayer().addSpecialPerk(perk);
								}
							}
						}
					};
					
				} else if(index==29)  {
					return new Response("生成概率", "列出此地块的种族生成概率。", SPAWN_RATES) {
						@Override
						public void effects() {
							spawnrateSB = new StringBuilder("<table><tr><th>种族名</th><th>总计</th><th>男性</th><th>女性</th><th>种族名</th><th>总计</th><th>男性</th><th>女性</th></tr>");
							spawnTotal = 0;
							spawnTotalMasculine = 0;
							spawnTotalFeminine = 0;
							float spawn;
							float spawnMasculine;
							float spawnFeminine;
							for (AbstractSubspecies s : Subspecies.getAllSubspecies()) {
								if (Subspecies.getWorldSpecies(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocationPlace().getPlaceType(), false).containsKey(s)) {
									spawn = (1000 * Subspecies.getWorldSpecies(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocationPlace().getPlaceType(), false).get(s).getChanceMultiplier());
									spawnTotalMasculine += (spawn * Main.getProperties().getSubspeciesMasculinePreferencesMap().get(s).getValue());
									spawnTotalFeminine += (spawn * Main.getProperties().getSubspeciesFemininePreferencesMap().get(s).getValue());
									spawnTotal = spawnTotalMasculine + spawnTotalFeminine;
								}
							}
							boolean even = false;
							for (AbstractSubspecies s : Subspecies.getAllSubspecies()) {
								if (Subspecies.getWorldSpecies(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocationPlace().getPlaceType(), false).containsKey(s)) {
									spawn = (1000 * Subspecies.getWorldSpecies(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocationPlace().getPlaceType(), false).get(s).getChanceMultiplier());
									spawnMasculine = (spawn * Main.getProperties().getSubspeciesMasculinePreferencesMap().get(s).getValue());
									spawnFeminine = (spawn * Main.getProperties().getSubspeciesFemininePreferencesMap().get(s).getValue());
									if (!even) {
										spawnrateSB.append("<tr>");
									}
									spawnrateSB.append("<td style='color:").append(s.getColour(null).toWebHexString()).append(";'>").append(Util.capitaliseSentence(s.getNamePlural(null))).append("</td>")
												.append("<td style='color:").append(s.getColour(null).toWebHexString()).append(";'>").append(String.format("%.02f", (((spawnMasculine + spawnFeminine) * 100) / spawnTotal))).append("%</td>")
												.append("<td style='color:").append(s.getColour(null).toWebHexString()).append(";'>").append(String.format("%.02f", ((spawnMasculine * 100) / spawnTotalMasculine))).append("%</td>")
												.append("<td style='color:").append(s.getColour(null).toWebHexString()).append(";'>").append(String.format("%.02f", ((spawnFeminine * 100) / spawnTotalFeminine))).append("%</td>");
									if (even) {
										spawnrateSB.append("</tr>");
									}
									even = !even;
								}
							}
							if (!even) {
								spawnrateSB.append("</tr>");
							}
							spawnrateSB.append("</table>");
						}
					};
					
				} else if(index==30) {
					return new Response("物品图标", "查看由当前游戏中所有道具、武器和衣物图标。<br/>[style.italicsMinorBad(加载和显示的过程会很慢！)]", CLOTHING_COLLAGE);
					
				}
				
			} else if(responseTab == 3) {
				if(index==1) {
					String femName = "无";
					if(filterFemininity==Femininity.FEMININE) {
						femName = "女性化";
					} else if(filterFemininity==Femininity.MASCULINE) {
						femName = "男性化";
					}
					return new Response("过滤：<span style='color:"+(filterFemininity.getColour().toWebHexString())+";'>"+femName+"</span>",
							"筛选女性化服装。",
							Main.game.getCurrentDialogueNode()==DEBUG_MENU
								?DEBUG_MENU
								:ITEM_VIEWER) {
						@Override
						public void effects() {
							if(filterFemininity==Femininity.ANDROGYNOUS) {
								filterFemininity = Femininity.FEMININE;
							} else if(filterFemininity==Femininity.FEMININE) {
								filterFemininity = Femininity.MASCULINE;
							} else {
								filterFemininity = Femininity.ANDROGYNOUS;
							}
						}
					};

					// The StringBuilder gets too big and throws: java.lang.OutOfMemoryError: Java heap space
					// So replaced with filter above view
//					return new Response("All",
//							"View icons and ids of all the clothing, weapons, and items in the game. You can also spawn these items by clicking on their icons. <i>Warning: Very sluggish and slow to load.</i>",
//							ITEM_VIEWER) {
//						@Override
//						public void effects() {
//							viewItemVariablesReset();
//							viewAll = true;
//						}
//						@Override
//						public Colour getHighlightColour() {
//							return PresetColour.GENERIC_EXCELLENT;
//						}
//					};
					
				} else if(index==2) {
					return new Response("道具",
							"查看游戏中所有道具的图标和id。你也可以点击对应图标来生成物品。<i>警告：加载可能较慢。</i>",
							ITEM_VIEWER) {
						@Override
						public void effects() {
							viewItemVariablesReset();
						}
						@Override
						public Colour getHighlightColour() {
							return PresetColour.BASE_BLUE_LIGHT;
						}
					};
					
				} else if(index==3) {
					return new Response("武器",
							"查看游戏中所有武器的图标和id。你也可以点击对应图标来生成物品。<i>警告：加载可能较慢。</i>",
							ITEM_VIEWER) {
						@Override
						public void effects() {
							viewItemVariablesReset();
							itemViewSlot = InventorySlot.WEAPON_MAIN_1;
						}
						@Override
						public Colour getHighlightColour() {
							return PresetColour.BASE_CRIMSON;
						}
					};
					
				} else if(index==4) {
					// The StringBuilder gets too big and throws: java.lang.OutOfMemoryError: Java heap space
					// So replaced with tattoo view
//					return new Response("All clothing",
//							"View icons and ids of all the clothing in the game. You can also spawn these items by clicking on their icons. <i>Warning: Very sluggish and slow to load.</i>",
//							ITEM_VIEWER) {
//						@Override
//						public void effects() {
//							viewItemVariablesReset();
//							viewAllClothing = true;
//						}
//						@Override
//						public Colour getHighlightColour() {
//							return PresetColour.BASE_YELLOW;
//						}
//					};
					return new Response("纹身",
							"查看游戏中所有纹身的图标和id。<i>警告：加载可能较慢。</i>",
							ITEM_VIEWER) {
						@Override
						public void effects() {
							viewItemVariablesReset();
							viewAllTattoos = true;
						}
					};
					
				} else if(index==5) {
					return new Response("服装",
							"进入装备生成器，让你测试所有游戏中的装备。",
							OUTFIT_VIEWER) {
						@Override
						public void effects() {
							BasicDoll doll = new BasicDoll();
							try {
								dollID = Main.game.addNPC(doll, false);
							} catch (Exception e) {
								e.printStackTrace();
							}
							doll.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.HUMAN, RaceStage.GREATER, true);
							doll.setBodyMaterial(BodyMaterial.SILICONE);
							doll.setTailType(TailType.DEMON_COMMON);
							doll.setWingType(WingType.DEMON_COMMON);
							doll.setHornType(HornType.STRAIGHT);
							doll.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
							doll.setArmRows(3);
							
							doll.setPiercedEar(true);
							doll.setPiercedLip(true);
							doll.setPiercedNavel(true);
							doll.setPiercedNipples(true);
							doll.setPiercedNipplesCrotch(true);
							doll.setPiercedNose(true);
							doll.setPiercedPenis(true);
							doll.setPiercedTongue(true);
							doll.setPiercedVagina(true);
							
							doll.setName("装扮玩偶");
							doll.setLocation(Main.game.getPlayer());
							Main.game.setActiveNPC(doll);
						}
						@Override
						public Colour getHighlightColour() {
							return PresetColour.BASE_PINK_SALMON;
						}
					};
					
				} else {
					int indexOffset = 6;
					List<InventorySlot> clothingSlots = new ArrayList<>(Arrays.asList(InventorySlot.values()));
					clothingSlots.remove(InventorySlot.WEAPON_MAIN_1);
					clothingSlots.remove(InventorySlot.WEAPON_MAIN_2);
					clothingSlots.remove(InventorySlot.WEAPON_MAIN_3);
					clothingSlots.remove(InventorySlot.WEAPON_OFFHAND_1);
					clothingSlots.remove(InventorySlot.WEAPON_OFFHAND_2);
					clothingSlots.remove(InventorySlot.WEAPON_OFFHAND_3);
					
					if(index-indexOffset < clothingSlots.size()) {
						InventorySlot is = clothingSlots.get(index-indexOffset);
						return new Response(Util.capitaliseSentence(is.getName()),
								"查看所有装备于“"+is.getName()+"”栏位的衣物。你也可以点击对应图标来生成物品。<i>警告：加载可能较慢。</i>",
								ITEM_VIEWER) {
							@Override
							public void effects() {
								viewItemVariablesReset();
								itemViewSlot = is;
							}
						};
						
					}
//					else if(index-indexOffset == clothingSlots.size()) {
//						return new Response("Tattoos",
//								"View icons and ids of all the tattoos in the game. <i>Warning: May be sluggish and slow to load.</i>",
//								ITEM_VIEWER) {
//							@Override
//							public void effects() {
//								viewItemVariablesReset();
//								viewAllTattoos = true;
//							}
//						};
//					}
				}
				
			} else if(responseTab==4) {
				if(index==1) {
					return new ResponseEffectsOnly(UtilText.parse(targetedCharacter, "[npc.Name]"),
							UtilText.parse(targetedCharacter,
									"[npc.NameIsFull]为当前目标，个性改变将作用于[npc.herHim]。"
									+ "<br/><i>点击循环切换该地块的在场角色。</i>")) {
						@Override
						public Colour getHighlightColour() {
							return targetedCharacter.getGender().getColour();
						}
						@Override
						public void effects() {
							boolean preparedForNext = targetedCharacter.isPlayer();
							for(GameCharacter character : Main.game.getCharactersPresent()) {
								if(preparedForNext) {
									targetedCharacter = character;
									preparedForNext = false;
									break;
								}
								if(character.equals(targetedCharacter)) {
									preparedForNext = true;
								}
							}
							if(preparedForNext) {
								targetedCharacter = Main.game.getPlayer();
							}
						}
					};
				}
				
				List<PersonalityTrait> pt = Arrays.asList(PersonalityTrait.values());
				for(int i=2; i<=pt.size()+1;i++) {
					if(i==index) {
						PersonalityTrait perTr = pt.get(index-2);
						boolean hasTrait = targetedCharacter.hasPersonalityTrait(perTr);
						return new Response(
								hasTrait
									?"<b style='color:"+perTr.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(perTr.getName())+"</b>"
									:Util.capitaliseSentence(perTr.getName()),
									(hasTrait
										?"[style.boldGood(已拥有！)] "
										:"[style.colourMinorBad(尚未拥有！)] ")
									+perTr.getDescription(targetedCharacter, true, true),
								DEBUG_MENU) {
							@Override
							public void effects() {
								if(hasTrait) {
									targetedCharacter.removePersonalityTrait(perTr);
								} else {
									targetedCharacter.addPersonalityTrait(perTr);
								}
							}
						};
					}
				}
				
				if(index==pt.size()+2) {
					return new Response("[style.colourMinorBad(重新生成)]",
								UtilText.parse(targetedCharacter, "根据种族和经历，重新生成[npc.namePos]的个性特性。"),
								DEBUG_MENU) {
						@Override
						public void effects() {
							targetedCharacter.clearPersonalityTraits();
							
							// Starting personalities based on race, copied from GameCharacter.additionalBodySetup():
							for(Entry<PersonalityTrait, Float> entry : targetedCharacter.getRace().getRacialBody().getPersonalityTraitChances().entrySet()) {
								double rnd = Math.random();
								if(rnd<=entry.getValue()) {
									targetedCharacter.addPersonalityTrait(entry.getKey());
								}
							}
			
							for(Entry<PersonalityTrait, Float> entry : targetedCharacter.getTrueSubspecies().getPersonalityTraitChances().entrySet()) {
								double rnd = Math.random();
								if(rnd<=entry.getValue()) {
									targetedCharacter.addPersonalityTrait(entry.getKey());
								}
							}
							
							if(targetedCharacter.hasPersonalityTrait(PersonalityTrait.MUTE)) { // If mute, remove all other speech traits
								targetedCharacter.removePersonalityTraits(PersonalityCategory.SPEECH);
								targetedCharacter.addPersonalityTrait(PersonalityTrait.MUTE);
							}
							
							// Roughly copying effects from CharacterUtils' setHistoryAndPersonality():
							if(targetedCharacter.getHistory()==Occupation.NPC_PROSTITUTE) {
								targetedCharacter.removePersonalityTrait(PersonalityTrait.PRUDE);
								targetedCharacter.removePersonalityTrait(PersonalityTrait.INNOCENT);
							}
							if(targetedCharacter.getHistory().isLowlife()) {
								if(Math.random()<0.25f) {
									targetedCharacter.addPersonalityTrait(PersonalityTrait.SLOVENLY);
								}
							}
						}
					};
				}
				

				
			}
			
			return null;
		}
	};
	private static StringBuilder coloursSB;
	public static final DialogueNode COLOURS = new DialogueNode("", "", false) {

		@Override
		public String getContent() {
			return coloursSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};
	
	private static NPC activeOffspring = null;
	
	public static final DialogueNode OFFSPRING = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			for(NPC npc : Main.game.getOffspring()) {
				boolean isBorn = true;
				if(npc.getMother()!=null && npc.getMother().getPregnantLitter()!=null && npc.getMother().getPregnantLitter().getOffspring().contains(npc.getId())) {
					isBorn = false;
				}
				UtilText.nodeContentSB.append((isBorn?"":"(尚未出生) ")+"<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName(true)+" "+npc.getSurname()+"</span>"
						+ " ("+npc.getSubspecies().getName(npc.getBody())+" | "+npc.getHalfDemonSubspecies().getName(npc.getBody())+")"
						+ " ("+npc.getCovering(npc.getBody().getTorsoType().getBodyCoveringType(npc.getBody())).getPrimaryColour().getName()+")" // Primary covering colour
						+ "母："+(npc.getMother()!=null?npc.getMother().getName(true):"被删除的NPC")
						+ "父："+(npc.getFather()!=null?npc.getFather().getName(true):"被删除的NPC")+"<br/>");
			}
			for(OffspringSeed os : Main.game.getOffspringNotSpawned(os -> true,true)) {
				if(!os.isBorn()) {
					UtilText.nodeContentSB.append("[style.colourBad(尚未出生)] ");
				} else {
					UtilText.nodeContentSB.append("还未遇到");
				}
				
				UtilText.nodeContentSB.append("<span style='color:"+os.getFemininity().getColour().toWebHexString()+";'>"+os.getName()+" "+os.getSurname()+"</span>");
				
				UtilText.nodeContentSB.append(" (<i style='color:"+os.getGender().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(os.getGender().getName())+"</i>)");
				
				UtilText.nodeContentSB.append(" ("+os.getSubspecies().getName(os.getBody()));
				if(os.getSubspecies()==Subspecies.HALF_DEMON) {
					UtilText.nodeContentSB.append("/"+os.getHalfDemonSubspecies().getName(os.getBody()));
				}
				UtilText.nodeContentSB.append(")");
				
				Colour primaryCoveringColour = os.getBody().getCovering(os.getBody().getTorsoType().getBodyCoveringType(os.getBody()), true).getPrimaryColour();
				UtilText.nodeContentSB.append("(<span style='color:"+primaryCoveringColour.toWebHexString()+";'>"+primaryCoveringColour.getName()+"</span>)"); // Primary covering colour
				
				UtilText.nodeContentSB.append("母："+(os.getMother()!=null?os.getMother().getName(true):"被删除的NPC"));
				UtilText.nodeContentSB.append("父："+(os.getFather()!=null?os.getFather().getName(true):"被删除的NPC")+"<br/>");
			}
			if(activeOffspring!=null) {
				for(AbstractFetish f : activeOffspring.getFetishes(true)) {
					UtilText.nodeContentSB.append("<br/>[style.boldSex(性癖：)] "+f.getName(activeOffspring));
				}
				UtilText.nodeContentSB.append(
						"<br/>" + activeOffspring.getDescription()
						+"<br/>" + activeOffspring.getBodyDescription());
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "", DEBUG_MENU);
				
			} else if(index-1 < Main.game.getOffspring().size()) {
				return new Response(Main.game.getOffspring().get(index-1).getName(true), "查看该后代的角色页面。", OFFSPRING) {
					@Override
					public void effects() {
						activeOffspring = Main.game.getOffspring().get(index-1);
						for(CoverableArea ca : CoverableArea.values()) {
							activeOffspring.setAreaKnownByCharacter(ca, Main.game.getPlayer(), true);
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static List<AbstractClothingType> clothingTotal = new ArrayList<>();
	public static InventorySlot activeSlot = null;
	public static ItemTag itemTag = null;
	public static int spawnCount = 1;
	public static List<AbstractItemType> itemsTotal = new ArrayList<>();
	public static List<AbstractWeaponType> weaponsTotal = new ArrayList<>();
	public static List<AbstractTattooType> tattoosTotal = new ArrayList<>();
	static {
		clothingTotal.addAll(ClothingType.getAllClothing());
		clothingTotal.removeIf((c) -> c.getDefaultItemTags().contains(ItemTag.REMOVE_FROM_DEBUG_SPAWNER) || c.getDefaultItemTags().contains(ItemTag.CHEAT_ITEM));
		Collections.sort(clothingTotal, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		weaponsTotal.addAll(WeaponType.getAllWeapons());
		weaponsTotal.removeIf((w) -> w.getItemTags().contains(ItemTag.REMOVE_FROM_DEBUG_SPAWNER) || w.getItemTags().contains(ItemTag.CHEAT_ITEM));
		Collections.sort(weaponsTotal, (i1, i2) -> Main.game.getItemGen().generateWeapon(i1).getRarity().compareTo(Main.game.getItemGen().generateWeapon(i2).getRarity()));

		tattoosTotal.addAll(TattooType.getAllTattooTypes());
		Collections.sort(tattoosTotal, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		itemsTotal.addAll(ItemType.getAllItems());
		itemsTotal.removeIf((i) -> i.getItemTags().contains(ItemTag.REMOVE_FROM_DEBUG_SPAWNER) || i.getItemTags().contains(ItemTag.CHEAT_ITEM));
		Collections.sort(itemsTotal, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
	}
	private static StringBuilder inventorySB = new StringBuilder();
	
	public static final DialogueNode SPAWN_MENU = new DialogueNode("生成菜单", "进入生成菜单。", false) {
		@Override
		public String getHeaderContent() {
			inventorySB.setLength(0);
			
			inventorySB.append("<div class='container-half-width'>");

			inventorySB.append(
					"<p style='width:100%; text-align:center; padding:0 margin:0;'>"
						+ (itemTag==ItemTag.CHEAT_ITEM
							?"[style.boldGreenDark(作弊道具)]"
							:(activeSlot==null
								?"<b style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>生成道具</b>"
								:(activeSlot.isWeapon()
									? "<b style='color:"+PresetColour.BASE_RED_LIGHT.toWebHexString()+";'>生成武器</b>("+Util.capitaliseSentence(activeSlot==InventorySlot.WEAPON_MAIN_1?"近战":"远程")+")"
									: "<b style='color:"+PresetColour.BASE_YELLOW_LIGHT.toWebHexString()+";'>生成衣物</b>("+Util.capitaliseSentence(activeSlot.getName())+")")))
					+"</p>");
			
			int count=0;
			inventorySB.append("<div class='inventory-not-equipped'>");
			if(itemTag==ItemTag.CHEAT_ITEM) {
				for(AbstractClothingType c : ClothingType.getAllClothing()) {
					if(c.getDefaultItemTags().contains(ItemTag.CHEAT_ITEM)) {
						inventorySB.append("<div class='inventory-item-slot unequipped' style='background-color:"+c.getRarity().getBackgroundColour().toWebHexString()+";'>"
								+ "<div class='inventory-icon-content'>"
									+c.getSVGImage()
								+"</div>"
								+ "<div class='overlay' id='" + c.getId() + "_SPAWN'></div>"
							+ "</div>");
					}
				}
				for(AbstractWeaponType weaponType : WeaponType.getAllWeapons()) {
					if(weaponType.getItemTags().contains(ItemTag.CHEAT_ITEM)) {
						inventorySB.append("<div class='inventory-item-slot unequipped' style='background-color:"+weaponType.getRarity().getBackgroundColour().toWebHexString()+";'>"
								+ "<div class='inventory-icon-content'>"+weaponType.getSVGImage()
								+"</div>"
								+ "<div class='overlay' id='" + weaponType.getId() + "_SPAWN'></div>"
							+ "</div>");
					}
				}
				for(AbstractItemType itemType : ItemType.getAllItems()) {
					if(itemType.getItemTags().contains(ItemTag.CHEAT_ITEM)) {
						inventorySB.append("<div class='inventory-item-slot unequipped' style='background-color:"+itemType.getRarity().getBackgroundColour().toWebHexString()+";'>"
								+ "<div class='inventory-icon-content'>"+itemType.getSVGString()+"</div>"
								+ "<div class='overlay' id='" + itemType.getId() + "_SPAWN'></div>"
							+ "</div>");
					}
				}
				
			} else if(activeSlot == null) {
				for(AbstractItemType itemType : itemsTotal) {
					if((itemTag==null
							&& (!itemType.getItemTags().contains(ItemTag.BOOK)
							&& !itemType.getItemTags().contains(ItemTag.ESSENCE)
							&& !itemType.getItemTags().contains(ItemTag.SPELL_BOOK)
							&& !itemType.getItemTags().contains(ItemTag.SPELL_SCROLL)))
							|| (itemTag!=null
								&& (itemType.getItemTags().contains(itemTag)
										|| (itemTag==ItemTag.SPELL_BOOK && itemType.getItemTags().contains(ItemTag.SPELL_SCROLL))))) {
						inventorySB.append("<div class='inventory-item-slot unequipped' style='background-color:"+itemType.getRarity().getBackgroundColour().toWebHexString()+";'>"
												+ "<div class='inventory-icon-content'>"+itemType.getSVGString()+"</div>"
												+ "<div class='overlay' id='" + itemType.getId() + "_SPAWN'></div>"
											+ "</div>");
					}
					count++;
				}
				
			} else if(activeSlot.isWeapon()) {
				for(AbstractWeaponType weaponType : weaponsTotal) {
					if((weaponType.isMelee() && activeSlot==InventorySlot.WEAPON_MAIN_1)
							|| (!weaponType.isMelee() && activeSlot==InventorySlot.WEAPON_OFFHAND_1)) {
						Rarity rarity = Main.game.getItemGen().generateWeapon(weaponType).getRarity();
						inventorySB.append("<div class='inventory-item-slot unequipped' style='background-color:"+rarity.getBackgroundColour().toWebHexString()+";'>"
												+ "<div class='inventory-icon-content'>"+weaponType.getSVGImage()
												+"</div>"
												+ "<div class='overlay' id='" + weaponType.getId() + "_SPAWN'></div>"
											+ "</div>");
						count++;
					}
				}
				
			} else {
				for(AbstractClothingType clothingType : clothingTotal) {
					if(clothingType.getEquipSlots().contains(activeSlot)) {
						inventorySB.append("<div class='inventory-item-slot unequipped' style='background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+";'>"
												+ "<div class='inventory-icon-content'>"
													+clothingType.getSVGImage()
												+"</div>"
												+ "<div class='overlay' id='" + clothingType.getId() + "_SPAWN'></div>"
											+ "</div>");
						count++;
					}
				}
			}
			
			// Fill space:
			for (int i = count; i <48; i++) {
				inventorySB.append("<div class='inventory-item-slot unequipped'></div>");
			}
			inventorySB.append("</div>"
					+ "</div>");
			
			inventorySB.append("<div class='container-half-width'>");
			for(InventorySlot slot : InventorySlot.values()) {
				if(slot!=InventorySlot.WEAPON_MAIN_2
						&& slot!=InventorySlot.WEAPON_MAIN_3
						&& slot!=InventorySlot.WEAPON_OFFHAND_2
						&& slot!=InventorySlot.WEAPON_OFFHAND_3) {
					inventorySB.append("<div class='normal-button' id='"+slot+"_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"
							+ (slot.isWeapon() ? PresetColour.BASE_RED_LIGHT.toWebHexString() : PresetColour.BASE_YELLOW_LIGHT.toWebHexString())+";'>"
							+(slot == InventorySlot.WEAPON_MAIN_1
								?"近战"
								:(slot == InventorySlot.WEAPON_OFFHAND_1
										?"远程"
										:Util.capitaliseSentence(slot.getName())))
							+"</div>");
				}
			}
			inventorySB.append("<div class='normal-button' id='ITEM_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>道具</div>");
			inventorySB.append("<div class='normal-button' id='ESSENCE_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>精华</div>");
			inventorySB.append("<div class='normal-button' id='BOOK_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.BASE_ORANGE.toWebHexString()+";'>书本</div>");
			inventorySB.append("<div class='normal-button' id='SPELL_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.DAMAGE_TYPE_SPELL.toWebHexString()+";'>法术</div>");
			inventorySB.append("<div class='normal-button' id='HIDDEN_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; opacity:0; cursor:default; color:"+PresetColour.BASE_GREEN_DARK.toWebHexString()+";'>作弊</div>");

			inventorySB.append("</div>");
			
			return inventorySB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SPAWN_MENU_SET = new DialogueNode("生成套装", "", false) {
		@Override
		public String getHeaderContent() {
			inventorySB.setLength(0);
			
			inventorySB.append("<div class='container-full-width'>");
			
			List<AbstractSetBonus> bonuses = new ArrayList<>(SetBonus.allSetBonuses);
			bonuses.sort((sb1, sb2) -> sb1.getName().compareTo(sb2.getName()));
			
			for(AbstractSetBonus sb : bonuses) {
				inventorySB.append("<div class='normal-button' id='SET_BONUS_"+SetBonus.getIdFromSetBonus(sb)+"' style='text-align:center;width:23%; margin:1%; padding:2px; font-size:0.9em;'>");
				inventorySB.append("<b style='color:"+sb.getAssociatedStatusEffect().getColour().toWebHexString()+";'>#</b>");
				inventorySB.append(sb.getName());
				inventorySB.append("<b style='color:"+sb.getAssociatedStatusEffect().getColour().toWebHexString()+";'>#</b>");
				inventorySB.append("</div>");
			}
			
			inventorySB.append("</div>");
			
			return inventorySB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};
	
	private static void viewItemVariablesReset() {
		viewAll = false;
		viewAllClothing = false;
		viewAllTattoos = false;
		itemViewSlot = null;
	}
	
	private static boolean viewAll = false;
	private static boolean viewAllClothing = false;
	private static boolean viewAllTattoos = false;
	private static InventorySlot itemViewSlot = null;
	
	public static final DialogueNode ITEM_VIEWER = new DialogueNode("", "", false) {

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			int width = 33;
			if(Main.primaryStage.getWidth()>=1900) {
				width = 25;
			}
			int imgWidth = 15;
			
			if(!viewAllClothing && !viewAllTattoos && (viewAll || itemViewSlot == null)) {
				sb.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
						+ "<h5>道具总计："+itemsTotal.size()+"</h5>");
				for(AbstractItemType itemType : itemsTotal) {
					if((itemTag==null
							&& (!itemType.getItemTags().contains(ItemTag.BOOK)
							&& !itemType.getItemTags().contains(ItemTag.ESSENCE)
							&& !itemType.getItemTags().contains(ItemTag.SPELL_BOOK)
							&& !itemType.getItemTags().contains(ItemTag.SPELL_SCROLL)))
							|| (itemTag!=null
								&& (itemType.getItemTags().contains(itemTag)
										|| (itemTag==ItemTag.SPELL_BOOK && itemType.getItemTags().contains(ItemTag.SPELL_SCROLL))))) {
						sb.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:0; margin:0;'>"
												+ "<div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+itemType.getRarity().getBackgroundColour().toWebHexString()+";'>"
													+ "<div class='inventory-icon-content'>"+itemType.getSVGString()+"</div>"
													+ "<div class='overlay' id='" + itemType.getId() + "_SPAWN'></div>"
												+ "</div>"
												+ ItemType.getItemToIdMap().get(itemType)
											+ "</div>");
					}
				}
				sb.append("</div>");
			}

			if(viewAll || (itemViewSlot!=null && itemViewSlot.isWeapon())) {
				sb.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
						+ "<h5>武器总计："+weaponsTotal.size()+"</h5>");
				for(AbstractWeaponType weaponType : weaponsTotal) {
					sb.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:0; margin:0;'>"
											+ "<div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+weaponType.getRarity().getBackgroundColour().toWebHexString()+";'>"
												+ "<div class='inventory-icon-content'>"+weaponType.getSVGImage()
												+"</div>"
												+ "<div class='overlay' id='" + weaponType.getId() + "_SPAWN'></div>"
											+ "</div>"
											+ WeaponType.getIdFromWeaponType(weaponType)
										+ "</div>");
				}
				sb.append("</div>");
			}

			if(viewAll || viewAllClothing) {
				sb.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>");
				sb.append("<h5>");
					sb.append("总服装数："+clothingTotal.size());
				sb.append("</h5>");
				for(AbstractClothingType clothingType : clothingTotal) {
					sb.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:0; margin:0;'>"
										+ "<div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+";'>"
											+ "<div class='inventory-icon-content'>"
												+clothingType.getSVGImage()
											+"</div>"
											+ "<div class='overlay' id='" + clothingType.getId() + "_SPAWN'></div>"
										+ "</div>"
										+ (clothingType.getPhysicalResistance()>0
												?"[style.boldPhysical("+UtilText.getShieldSymbol()+clothingType.getPhysicalResistance()+")][style.colourGreenLight("+ClothingType.getIdFromClothingType(clothingType)+")]"
												:ClothingType.getIdFromClothingType(clothingType))
									+ "</div>");
				}
				sb.append("</div>");
				
			} else if(viewAllTattoos) {
				sb.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
						+ "<h5>纹身总计："+tattoosTotal.size()+"</h5>");
				for(AbstractTattooType tattooType : tattoosTotal) {
					sb.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:0; margin:0;'>"
											+ "<div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+tattooType.getRarity().getBackgroundColour().toWebHexString()+";'>"
												+ "<div class='inventory-icon-content'>"
													+tattooType.getSVGImage(Main.game.getPlayer())
												+"</div>"
												+ "<div class='overlay' id='" + tattooType.getId() + "_SPAWN'></div>"
											+ "</div>"
											+ TattooType.getIdFromTattooType(tattooType)
										+ "</div>");
				}
				sb.append("</div>");
				
			} else if(itemViewSlot!=null && !itemViewSlot.isWeapon()) {
				List<AbstractClothingType> clothingToDisplay = clothingTotal.stream().filter((c) -> c.getEquipSlots().get(0)==itemViewSlot).collect(Collectors.toList());
				int maxItems = clothingToDisplay.size();
				for(AbstractClothingType clothingType : new ArrayList<>(clothingToDisplay)) {
					if(clothingType.getCoreFemininityRestriction()!=null) {
						if(filterFemininity==Femininity.FEMININE && (!clothingType.getCoreFemininityRestriction().isFeminine() && clothingType.getCoreFemininityRestriction()!=Femininity.ANDROGYNOUS)) {
							clothingToDisplay.remove(clothingType);
						} else if(filterFemininity==Femininity.MASCULINE && (clothingType.getCoreFemininityRestriction().isFeminine() && clothingType.getCoreFemininityRestriction()!=Femininity.ANDROGYNOUS)) {
							clothingToDisplay.remove(clothingType);
						}
					}
				}
				
				sb.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>");
				sb.append("<h5>");
					sb.append("“"+itemViewSlot.getName()+"”槽位总服装数："+clothingToDisplay.size()+(filterFemininity!=Femininity.ANDROGYNOUS?"/"+maxItems:""));
					if(filterFemininity!=Femininity.ANDROGYNOUS) {
						sb.append("<br/>");
						sb.append("(<span style='color:"+filterFemininity.getColour().toWebHexString()+";'>"+(filterFemininity.isFeminine()?"女性化":"男性化")+"过滤器</span>)");
					}
				sb.append("</h5>");
				for(AbstractClothingType clothingType : clothingToDisplay) {
					sb.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:0; margin:0;'>"
							+ "<div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+";'>"
								+ "<div class='inventory-icon-content'>"
									+clothingType.getSVGImage()
								+"</div>"
								+ "<div class='overlay' id='" + clothingType.getId() + "_SPAWN'></div>"
							+ "</div>"
							+ ClothingType.getIdFromClothingType(clothingType)
						+ "</div>");
				}
				sb.append("</div>");
			}
			
			return sb.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode OUTFIT_VIEWER = new DialogueNode("装备查看器", "", true) {
		@Override
		public String getContent() {
			inventorySB.setLength(0);
			
			inventorySB.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
					+ "<h5>Outfits: "+OutfitType.getAllOutfits().size()+"</h5>");
			int i=0;
			for(AbstractOutfit outfit : OutfitType.getAllOutfits()) {
				String id = OutfitType.getIdFromOutfitType(outfit);
				inventorySB.append("<div class='container-full-width' style='width:95%; padding:4px; margin:4px 2.5% 4px 2.5%; background-color:"+(i%2==0?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");

					inventorySB.append("<div class='container-full-width' style='position:relative; padding:0; margin:0; width:100%; background-color:#00000000; -webkit-user-select:auto; text-align:left;'>");
							inventorySB.append("<b>"+outfit.getName()+":</b><i>"+outfit.getDescription()+"</i>");
							inventorySB.append("<br/>");
							inventorySB.append("ID: "+id);
							inventorySB.append("<br/>");
							inventorySB.append("女性化程度：<span style='color:"+outfit.getFemininity().getColour().toWebHexString()+";'>"+outfit.getFemininity().toString()+"</span>");
							inventorySB.append("<br/>");
							inventorySB.append("条件：<span style='font-family:monospace; font-size:0.85em; background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; padding:2px;'>"+outfit.getConditional()+"</span>");
							
							inventorySB.append("<br/>");
							inventorySB.append("Leg configurations: ");
							if(outfit.getAcceptableLegConfigurations()!=null && !outfit.getAcceptableLegConfigurations().isEmpty()) {
								List<String> lcNames = new ArrayList<>();
								for(LegConfiguration lc : outfit.getAcceptableLegConfigurations()) {
									lcNames.add(lc.toString());
								}
								inventorySB.append(Util.stringsToStringList(lcNames, false));
							} else {
								inventorySB.append("[style.colourMinorGood(任何)]");
							}
							
							inventorySB.append("<br/>");
							inventorySB.append("Outfit types: ");
							if(outfit.getOutfitTypes()!=null && !outfit.getOutfitTypes().isEmpty()) {
								List<String> otNames = new ArrayList<>();
								for(OutfitType ot : outfit.getOutfitTypes()) {
									otNames.add(ot.toString());
								}
								inventorySB.append(Util.stringsToStringList(otNames, false));
							} else {
								inventorySB.append("[style.colourBad(无！)]");
							}
							inventorySB.append("<div class='normal-button' id='OUTFIT_"+id+"'"
									+ " style='position:absolute; text-align:center; width:18%; margin:1%; right:2px; bottom:2px; padding:2px; font-size:0.9em; color:"+PresetColour.GENERIC_MINOR_GOOD.toWebHexString()+";'>Apply</div>");
					inventorySB.append("</div>");
					
					
				inventorySB.append("</div>");
				i++;
			}
			inventorySB.append("</div>");
			
			return inventorySB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("返回", "返回到debug主菜单", DEBUG_MENU) {
					@Override
					public void effects() {
						Main.game.banishNPC(dollID);
					}
				};
			}
			return null;
		}
	};
	
	public static void applyOutfitToDoll(AbstractOutfit outfit) {
		try {
			NPC doll = (NPC) Main.game.getNPCById(dollID);
			
			doll.resetInventory(true);
			if(outfit.getAcceptableLegConfigurations()!=null
					&& !outfit.getAcceptableLegConfigurations().isEmpty()
					&& !outfit.getAcceptableLegConfigurations().contains(doll.getLegConfiguration())) {
				doll.setLegConfiguration(outfit.getAcceptableLegConfigurations().get(0), true);
			}
			if(doll.getLegConfiguration()!=LegConfiguration.BIPEDAL && (outfit.getAcceptableLegConfigurations()==null || outfit.getAcceptableLegConfigurations().isEmpty())) {
				doll.setLegConfiguration(LegConfiguration.BIPEDAL, true);
			}
			
			outfit.applyOutfit(doll, EquipClothingSetting.getAllClothingSettings());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static final DialogueNode BODY_PART_MATERIAL = new DialogueNode("设置身体材质", "设置身体材质。", false) {

		@Override
		public String getContent() {
			return "<p>选择一种材质类型。</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < BodyMaterial.values().length+1) {
				return new Response(Util.capitaliseSentence(BodyMaterial.values()[index - 1].getName()), "将你的身体变成由"+BodyMaterial.values()[index - 1].getName()+"构成。", BODY_PART_MATERIAL){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setBodyMaterial(BodyMaterial.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
	};
	
	public static Map<AbstractEncounter, Float> encounters;
	public static final DialogueNode ENCOUNTER_MENU = new DialogueNode("可用遭遇", "", false) {
		@Override
		public void applyPreParsingEffects() {
			encounters = Main.game.getPlayerCell().getPlaceType().getPossibleEncountersMap(true);
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
					+ "<h5>遭遇："+encounters.size()+"</h5>");
			int i=0;
			for(Entry<AbstractEncounter, Float> entry : encounters.entrySet()) {
				AbstractEncounter encounter = entry.getKey();
				float chance = entry.getValue();
				String id = encounter.getId();
				
				sb.append("<div class='container-full-width' style='border:1px solid "+(encounter.isFromExternalFile()?PresetColour.GENERIC_MINOR_GOOD:PresetColour.GENERIC_MINOR_BAD).toWebHexString()+";"
						+ " width:95%; padding:4px; margin:4px 2.5% 4px 2.5%; background-color:"+(i%2==0?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");

					sb.append("<div class='container-full-width' style='position:relative; padding:0; margin:0; width:100%; background-color:#00000000; -webkit-user-select:auto; text-align:left;'>");
						if(encounter.isFromExternalFile()) {
							sb.append(UtilText.parse("[style.colourGreenLight(模组)] "));
						} else {
							sb.append(UtilText.parse("[style.colourRedLight(资源)] "));
						}
						sb.append("<b>"+id+": "+chance+"%</b>");
						sb.append("<br/>");

						sb.append("遭遇：");
						if(encounter.isFromExternalFile()) {
							int encounterIndex=0;
							int rowCount=0;
							for(ExternalEncounterData data : encounter.getPossibleEncounters()) {
								float triggerChance = data.getTriggerChance();
								rowCount++;
								sb.append("<div class='container-full-width' style='margin:2px 0; padding:4px; width:100%;position:relative;"
										+ " background:"+(rowCount%2==0?PresetColour.BACKGROUND.getShades()[3]:PresetColour.BACKGROUND_ALT.getShades()[3])+";'>");
									sb.append(data.getName()+": "+(triggerChance==0?"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>":"<span>")+triggerChance+"%</span>");
									sb.append("<br/>");
									sb.append("条件: <span style='font-family:monospace; font-size:0.85em; background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; padding:2px;'>"+data.getTriggerConditional()+"</span>");
									sb.append("<br/>");
									sb.append("触发对话: "+data.getDialogueId());
									sb.append("<div class='normal-button' id='DEBUG_ENCOUNTER_"+id+"_"+encounterIndex+"'"
											+ " style='position:absolute; text-align:center; width:18%; height:auto; margin:1%; top:-8px; right:2px; padding:2px;"
											+ " font-size:0.9em; color:"+(triggerChance==0?PresetColour.GENERIC_BAD:PresetColour.GENERIC_MINOR_GOOD).toWebHexString()+";'>");
										sb.append("触发");
									sb.append("</div>");
								sb.append("</div>");
								encounterIndex++;
							}
							
						} else {
							int rowCount = 0;
							for(Entry<EncounterType, Float> typeEntry : encounter.getDialogues().entrySet()) {
								rowCount++;
								EncounterType et = typeEntry.getKey();
								float etChance = typeEntry.getValue();
								sb.append("<div class='container-full-width' style='margin:2px 0; padding:4px; width:100%;position:relative;"
										+ " background:"+(rowCount%2==0?PresetColour.BACKGROUND.getShades()[3]:PresetColour.BACKGROUND_ALT.getShades()[3])+";'>");
									sb.append(et.toString()+": "+etChance+"%");
									sb.append("<div class='normal-button' id='DEBUG_ENCOUNTER_"+id+"_"+et+"'"
											+ " style='position:absolute; text-align:center; width:18%; height:auto; margin:1%; top:-8px; right:2px; padding:2px; font-size:0.9em; color:"+PresetColour.GENERIC_MINOR_GOOD.toWebHexString()+";'>");
										sb.append("触发");
									sb.append("</div>");
								sb.append("</div>");
							}
						}
						
						
//							sb.append("Conditional: <span style='font-family:monospace; font-size:0.85em; background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; padding:2px;'>"+outfit.getConditional()+"</span>");
							
					sb.append("</div>");
					
				sb.append("</div>");
				i++;
			}
			sb.append("</div>");
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
		@Override
		public boolean isContentParsed() {
			return false;
		}
	};
	
	private static NPC attacker;
	private static RaceStage attackerRaceStage;
	private static AbstractSubspecies attackerSubspecies;
	private static AbstractSubspecies attackerHalfDemonSubspecies;
	private static void initAttacker() {
		if(Main.game.getPlayer().getWorldLocation()==WorldType.DOMINION) {
			attacker = new DominionAlleywayAttacker(Gender.getGenderFromUserPreferences(false, false));
		} else if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley")
				|| Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_abandoned_bakery")) {
			attacker = new ElisAlleywayAttacker(Gender.getGenderFromUserPreferences(false, false));
		} else {
			attacker = new SubmissionAttacker(Gender.getGenderFromUserPreferences(false, false));
		}
		
		try {
			Main.game.addNPC(attacker, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		attacker.setLocation(Main.game.getPlayer(), true);
		Main.game.setActiveNPC(attacker);
	}
	
	public static final DialogueNode ATTACKER_SPAWN_MENU = new DialogueNode("生成袭击者", "", false) {
		@Override
		public void applyPreParsingEffects() {
			attacker = null;
			attackerSubspecies = Subspecies.HUMAN;
			attackerHalfDemonSubspecies = Subspecies.HUMAN;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
				sb.append("选择袭击者的种族。");
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "[style.colourTfPartial(似兽)]";

			} else if(index == 1) {
				return "[style.colourTfMinor(泛兽)]";
				
			} else if(index == 2) {
				return "[style.colourTfLesser(亚兽)]";
				
			} else if(index == 3) {
				return "[style.colourTfGreater(纯兽)]";
				
			} else if(index == 4) {
				return "[style.colourHalfDemon(半恶魔)]";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<AbstractSubspecies> availableSubspecies = new ArrayList<>(Subspecies.getAllSubspecies());
			availableSubspecies.removeIf(s->
					s.getRace()==Race.ELEMENTAL
//					|| s==Subspecies.LILIN
//					|| s==Subspecies.ELDER_LILIN
//					|| s==Subspecies.ANGEL
					);
			
			if (index!=0 && index<availableSubspecies.size()+1) {
				AbstractSubspecies subspecies = availableSubspecies.get(index - 1);
				String name = subspecies.getName(null);
				
				return new Response(
						Util.capitaliseSentence(name),
						"生成亚种的袭击者："+name,
						ATTACKER_SPAWN) {
					@Override
					public Colour getHighlightColour() {
						return subspecies.getColour(null);
					}
					@Override
					public void effects() {
						attackerRaceStage = RaceStage.PARTIAL;
						if(responseTab==1) {
							attackerRaceStage = RaceStage.PARTIAL_FULL;
						} else if(responseTab==2) {
							attackerRaceStage = RaceStage.LESSER;
						} else if(responseTab==3) {
							attackerRaceStage = RaceStage.GREATER;
						}
						if(subspecies==Subspecies.HALF_DEMON || responseTab==4) {
							attackerSubspecies = Subspecies.HALF_DEMON;
							attackerHalfDemonSubspecies = responseTab==4?subspecies:Subspecies.HUMAN;
						} else {
							attackerSubspecies = subspecies;
							attackerHalfDemonSubspecies = Subspecies.HUMAN;
						}
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "", DEBUG_MENU);
			}
			return null;
		}
	};
	
	public static final DialogueNode ATTACKER_SPAWN = new DialogueNode("生成袭击者", "", false) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
				sb.append("选择袭击者的性别。");
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Gender> availableGedners = Arrays.asList(Gender.values());
			
			if (index!=0 && index<availableGedners.size()+1) {
				Gender gender = availableGedners.get(index - 1);
				String name = attackerSubspecies.getName(null);
				
				return new ResponseEffectsOnly(
						Util.capitaliseSentence(gender.getName()),
						"生成一名袭击者。"
						+ "<br/>亚种："+name
						+"<br/>性别："+gender.getName()
						+" ("
							+(gender.getGenderName().isHasPenis()?"[style.italicsGood(阴茎)] ":"[style.italicsBad(阴茎)] ")
							+(gender.getGenderName().isHasVagina()?"[style.italicsGood(阴道)] ":"[style.italicsBad(阴道)] ")
							+(gender.getGenderName().isHasBreasts()?"[style.italicsGood(胸部)]":"[style.italicsBad(胸部)]")
						+")") {
					@Override
					public Colour getHighlightColour() {
						return gender.getColour();
					}
					@Override
					public void effects() {
						initAttacker();
						
						if(attackerSubspecies==Subspecies.HALF_DEMON) {
							attacker.setSubspeciesOverride(null);
							attacker.setBody(
									Main.game.getCharacterUtils().generateHalfDemonBody(attacker, gender, attackerHalfDemonSubspecies, false),
									false);
						} else {
							attacker.setSubspeciesOverride(null);
							
							if(attackerSubspecies==Subspecies.DEMON) {
								attackerRaceStage = RaceStage.GREATER;
							}
							
							attacker.setBody(gender, attackerSubspecies, attackerRaceStage, true);
							
//							Main.game.getCharacterUtils().reassignBody(
//									attacker,
//									attacker.getBody(),
//									attacker.getGender(),
//									subspecies,
//									stage,
//									false);
						}

						attacker.resetInventory(true);
						attacker.clearNonEquippedInventory(false);
						Main.game.getCharacterUtils().generateItemsInInventory(attacker, true, true, true);
						attacker.equipClothing();
						
						Main.game.setContent(new Response("", "", attacker.getEncounterDialogue()));
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "", DEBUG_MENU);
			}
			return null;
		}
	};
	
	public static final DialogueNode BODY_PART_RACE_RESET = new DialogueNode("重置种族", "设置种族。", false) {
		@Override
		public void applyPreParsingEffects() {
			// Just in case this dialogue node is accessed before initialising targetedCharacter, or if targetedCharacter is no longer present:
			if(targetedCharacter==null
					|| (!targetedCharacter.isPlayer() && !Main.game.getCharactersPresent().contains(targetedCharacter))) {
				targetedCharacter = Main.game.getPlayer();
			}
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>"
						+ UtilText.parse(targetedCharacter, "选择一个种族，将[npc.namePos]的身体重置为该种族的默认值。(也就是说，将[npc.her]当前的身体重生成不同种族的身体)。")
					+ "</p>"
					+ "<p>"
						+ "[style.colourTfPartial(似兽)]：拥有人类的身体，长着所选种族的触角、眼睛、耳朵、毛发、角、尾巴和翅膀。</br>"
						+ "[style.colourTfMinor(泛兽)]：在似兽的基础上，额外长有所选种族的屁股、胸部、阴茎和阴道。</br>"
						+ "[style.colourTfLesser(半兽)]：在泛兽的基础上，额外长有所选种族的上肢和下肢。</br>"
						+ "[style.colourTfGreater(纯兽)]：身体所有部位都变为所选种族。</br>"
					+ "</p>"
					+ "<p>"
					+ "<b>ID代码：</b><br/>");
			for(AbstractSubspecies sub : Subspecies.getAllSubspecies()) {
				sb.append("<span style='color:"+sub.getColour(targetedCharacter).toWebHexString()+";'>"+Util.capitaliseSentence(sub.getName(targetedCharacter.getBody()))+"</span>: "+Subspecies.getIdFromSubspecies(sub));
				sb.append("</br>");
			}
			
			sb.append("</p>");
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "[style.colourTfPartial(似兽)]";

			} else if(index == 1) {
				return "[style.colourTfMinor(泛兽)]";
				
			} else if(index == 2) {
				return "[style.colourTfLesser(亚兽)]";
				
			} else if(index == 3) {
				return "[style.colourTfGreater(纯兽)]";
				
			} else if (index == 4) {
				return "目标";
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==4) {
				if (index == 1) {
					if (targetedCharacter == Main.game.getPlayer()) {
						return new Response(Main.game.getPlayer().getName(), "你已是当前目标。", null);
					} else {
						return new Response(Main.game.getPlayer().getName(), "以自己为目标。", BODY_PART_RACE_RESET) {
							@Override
							public void effects() {
								targetedCharacter = Main.game.getPlayer();
							}
						};
					}
					
				} else {
					index-=2;
				}

				if (index >= Main.game.getCharactersPresent().size() || index<0) {
					return null;
				}
				GameCharacter gc = Main.game.getCharactersPresent().get(index);
				if (!gc.isUnique()) {
					if (targetedCharacter == gc) {
						return new Response(gc.getName(), gc.getName()+"已是当前目标。", null);
					} else {
						return new Response(gc.getName(), "将目标改为"+gc.getName(), BODY_PART_RACE_RESET) {
							@Override
							public void effects() {
								targetedCharacter = gc;
							}
						};
					}
				}
			
			}
			
			List<AbstractSubspecies> availableSubspecies = new ArrayList<>();
			availableSubspecies.addAll(Subspecies.getAllSubspecies());
			availableSubspecies.removeIf(s->s.getRace()==Race.ELEMENTAL);
			
			if (index!=0 && index<availableSubspecies.size()+1) {
				AbstractSubspecies subspecies = availableSubspecies.get(index - 1);
				String name = subspecies.getName(null);
				
				return new Response(
						Util.capitaliseSentence(name),
						"将你的身体变为"+UtilText.generateSingularDeterminer(name)+" "+name+"。",
						BODY_PART_RACE_RESET){
					@Override
					public void effects() {
						if(subspecies==Subspecies.HALF_DEMON) {
							targetedCharacter.setSubspeciesOverride(null);
							targetedCharacter.setBody(
									Main.game.getCharacterUtils().generateHalfDemonBody(targetedCharacter, targetedCharacter.getGender(), Subspecies.HUMAN, false),
									false);
//							System.out.println("Subspecies override: "+targetedCharacter.getSubspeciesOverride());
							
						} else {
							targetedCharacter.setSubspeciesOverride(null);
							RaceStage stage = responseTab==0
									?RaceStage.PARTIAL
									:(responseTab==1
										?RaceStage.PARTIAL_FULL
										:(responseTab==2
											?RaceStage.LESSER
											:RaceStage.GREATER));
							
							if(subspecies==Subspecies.DEMON) {
								stage = RaceStage.GREATER;
								
								DialogueFlags dialogueFlags = Main.game.getDialogueFlags();
								if(!dialogueFlags.hasFlag("innoxia_child_of_lyssieth")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lunette")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lirecea")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lovienne")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lasielle")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lyxias")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lisophia")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lilith")){
									Main.game.getDialogueFlags().setFlag("innoxia_child_of_lyssieth", true);
								}
							}
							
							Main.game.getCharacterUtils().reassignBody(
									targetedCharacter,
									targetedCharacter.getBody(),
									targetedCharacter.getGender(),
									subspecies,
									stage,
									false);
						}
						Main.game.getTextEndStringBuilder().append(
								"<p>"
									+ "[style.boldTfGeneric(转化完成：)]你现在是"+UtilText.generateSingularDeterminer(name)+" "+name+"了！"
								+ "</p>");
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNode CLOTHING_COLLAGE = new DialogueNode("服装拼贴", "服装拼贴。", false) {
		@Override
		public String getContent() {
			inventorySB.setLength(0);
			
			float width = 100/20f;
			int imgWidth = 100;
			
			inventorySB.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
					+ "<h5>道具总计："+itemsTotal.size()+"</h5>");
			for(AbstractItemType itemType : itemsTotal) {
				inventorySB.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:1px; margin:0;'>"
										+ "<div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+itemType.getRarity().getBackgroundColour().toWebHexString()+";'>"
											+ "<div class='inventory-icon-content'>"+itemType.getSVGString()+"</div>"
											+ "<div class='overlay' id='" + itemType.getId() + "_SPAWN'></div>"
										+ "</div>"
									+ "</div>");
			}
			inventorySB.append("</div>");

			inventorySB.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
					+ "<h5>武器总计："+weaponsTotal.size()+"</h5>");
			for(AbstractWeaponType weaponType : weaponsTotal) {
				inventorySB.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:1px; margin:0;'>"
										+ "<div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+weaponType.getRarity().getBackgroundColour().toWebHexString()+";'>"
											+ "<div class='inventory-icon-content'>"+weaponType.getSVGImage()
											+"</div>"
											+ "<div class='overlay' id='" + weaponType.getId() + "_SPAWN'></div>"
										+ "</div>"
									+ "</div>");
			}
			inventorySB.append("</div>");

			inventorySB.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
					+ "<h5>衣物总计："+clothingTotal.size()+"</h5>");
			for(AbstractClothingType clothingType : clothingTotal) {
				inventorySB.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:1px; margin:0;'>"
									+ "<div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+";'>"
										+ "<div class='inventory-icon-content'>"
											+clothingType.getSVGImageRandomColour(true, false, false)
										+"</div>"
										+ "<div class='overlay' id='" + clothingType.getId() + "_SPAWN'></div>"
									+ "</div>"
								+ "</div>");
			}
			inventorySB.append("</div>");
			
			return inventorySB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
	};

	private static String parsedText = "";
	private static String rawText = "";
	private static String xmlFileText = "res/txt/ENTER_PATH.xml";
	public static final DialogueNode PARSER = new DialogueNode("解析器", "", true) {

		@Override
		public String getHeaderContent() {
			return ("<p>"
					+ "在查看“指令”页面之前，<b>请</b>先浏览一下“帮助”页面！(指令本身其实很简单，但如果你没弄明白，那指令的数量可能会多到你生理性不适。)"
					+ "</p>"
					

					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div style='position:relative; display:inline-block; padding-bottom:0; margin 0 auto; vertical-align:middle; width:100%; text-align:center;'>"
							+ "<p style='display:inline-block; padding:0; margin:0; width:100%;'>XML测试(输入完整路径名，包括.xml): </p>"
							+ "<br/>"
							+ "<form style='display:inline-block; width:100%; padding:0; margin:0; text-align:center;'><input type='text' id='xmlTest' style='width:50%;' placeholder='res/txt/……' value='"+xmlFileText+"'></form>"
						+ "</div>"
					+ "</div>"
					
					+ "<p>"
						+ "<b>解析器可识别HTML文本格式。</b>"
					+ "</p>"

					+ "<p style='padding:0;margin:0;text-align:center;'>"
						+ "解析器:"
					+ "</p>"
					+ "<form style='padding:0;margin:0;text-align:center;'>"
					+ "<textarea id='parseInput' name='Text1' style='width:760px;height:200px;'>"+rawText+"</textarea>"
					+ "</form>");
		}
		
		@Override
		public String getContent() {
			return  "<p>"
					+ "<b>结果：</b>"
					+ "</p>"
					+ "<p>"+parsedText+"</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("解析！", "解析你输入的文本。"){
					@Override
					public void effects() {
						rawText = (String) Main.mainController.getWebEngine().executeScript("document.getElementById('parseInput').value");
						parsedText = UtilText.parse(rawText);
						if(Main.game.getCurrentDialogueNode()==PARSER) {
							Main.game.setContent(new Response("", "", PARSER));
						}
					}
				};
				
			} else if (index == 2) {
				return new Response("帮助", "", PARSER_HELP);
				
			} else if (index == 3) {
				return new Response("目标", "", PARSER_TARGETS);
				
			} else if (index == 4) {
				return new Response("指令", "", PARSER_COMMANDS_NEAT);
				
			} else if (index == 5) {
				return new Response("指令列表", "", PARSER_COMMANDS);
				
			} else if (index == 6) {
				return new Response("载入测试用例1", "", PARSER){
					@Override
					public void effects() {
						rawText = "你看到莉莱雅坐在[lilaya.herHis]实验室的一张桌子前，把玩着一块失效的魔石。"
								+ "萝丝坐在[lilaya.herPro]旁边，深情地凝视着莉莱雅[lilaya.eyes+]，身后[rose.tail+]温柔地前后摆动。"
								+ "\n<br/><br/>\n"
								+ "莉莱雅的奴隶靠得太近了，这让[lilaya.her]无法集中注意力，[lilaya.her]将魔石放在桌子上，[lilaya.arms+]环绕萝丝身后并把"
								+ "萝丝向后拉到[lilaya.her]大腿上。萝丝想得到[rose.she]主人关注的强烈愿望终于被满足了，欢快地叫了一声。莉莱雅的手向下滑入"
								+ "萝丝的裙子，萝丝发出了愉悦的呻吟。";
					}
				};
				
			} else if (index == 7) {
				return new Response("载入测试用例2", "", PARSER){
					@Override
					public void effects() {
						rawText = "布拉克斯在[brax.herHis]办公室里来回踱步，[brax.himself]低声咆哮，[brax.speech(呃啊……我的新海报还没送来……)]";
					}
				};
				
			} else if (index == 11) {
				return new Response("Xml测试",
						"解析指定路径文件中的每个对话条目，以检查是否有错误。莉莱雅、布拉克斯、萝丝、拉尔夫、妮安和扎拉尼克斯会被导入为解析器可用目标。"
						+ "特殊解析的格式为“SP1”，最多到10。",
						PARSER){
					@Override
					public void effects() {
						for(int i=1;i<=10;i++) {
							UtilText.addSpecialParsingString("SP"+i, i==1);
						}
						xmlFileText = ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('xmlTest').value")).replaceAll("\u200b", "");
						parsedText = Main.game.runXmlTest(xmlFileText);
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	public static final DialogueNode PARSER_HELP = new DialogueNode("Innoxia超级快乐有趣的解析器使用指南", "", true) {

		/*
		 * I've seen String concatenation... String concatenation that you've seen.
		 * But you have no right to call me a bad programmer.
		 * You have a right to ridicule my code. You have a right to do that... but you have no right to judge me.
		 * 
		 * It's impossible for words to describe what is necessary to those who do not know what String concatenation means.
		 * String concatenation... String concatenation has a face... and you must make a friend of String concatenation. String concatenation and html parsing are your friends. If they are not, then they are enemies to be feared.
		 */
		@Override
		public String getHeaderContent() {
			return  "<p style='text-align:center;'><i>你提交了输入文本，返回了不错的输出文本！</i></p>"
					
					+ "<p>"
					+ "<h6>输入文本：</h6><br/>"
					+"方括号内的所有内容都会被解析，分为以下模式：<br/>"
					+"[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>目标</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>指令</i>]<br/>"
					+"或者，有一些需求参数的特殊指令：<br/>"
					+"[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>目标</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>指令</i>"
							+ "<i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(参数)</i>]<br/>"
					+"或者，被当做脚本进行解析：<br/>"
					+"[#<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>指令</i>]<br/>"
					+"或者，被当做无输出的脚本进行解析：<br/>"
					+"[##<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>指令</i>]<br/>"
					+ "<i>(意思是指令会被执行，但不会输出可显示的字符串。)</i>"
					+ "</p>"
					
					+ "<p>"
					+"举一个在句子中应用的例子：<br/><br/>"
					+"<i>当你开始阅读Innoxia乏味的解析说明时，莉莱雅走到你身后，把[lilaya.her][lilaya.tail+]绕在你的[pc.leg]上。"
					+"[lilaya.she]靠在你的肩膀上，叹息着，[lilaya.speech(我的老天啊，真是太无聊了，[#pc.getName(true)]！)]'</i><br/><br/>"
					+ "解析为：<br/><br/>"
					+ UtilText.parse("当你开始阅读Innoxia乏味的解析说明时，莉莱雅走到你身后，把[lilaya.her][lilaya.tail+]绕在你的[pc.leg]上。"
							+ "[lilaya.she]靠在你的肩膀上，叹息着，[lilaya.speech(我的老天啊，真是太无聊了，[#pc.getName(true)]！)]")
					+ "</p>"
					+ "<br/>"
					
					
					+"<h6><b style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>目标</b><b>标签：</b></h6>"
					+"<p>"
					+"指令的目标是NPC的名字，或是代表玩家角色的“pc”。目标标签<b>不区分大小写</b>。(即pc与PC、pC或Pc视作相同处理)<br/>"
					+"如果输入了无法识别的名称，则将输出“INVALID_TARGET_NAME”。<br/>"
					+"可在“目标”页面中查看当前识别的目标标签。<br/>"
					+ "例如：<br/>"
					+ "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>lilaya</i>.指令]<br/>"
					+ "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>pc</i>.指令(参数)]<br/>"
					+ "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>npc</i>.指令]"
					+ "</p>"
					+ "<br/>"
					
					
					+"<h6><b style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>指令</b><b>与</b><b style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>参数</b><b>标签：</b></h6>"
					+"<p>"
					+"指令标签决定输出文本的内容。它们分两种类型：有参数和无参数。<br/>"
					+"在指令后面括号里的参数会被读入，中间插入的空格会被忽略。<br/>"
					+ "例如：[pc.指令(参数)]与[pc.指令   (参数)]的效果相同。<br/>"
					+"指令标签<b>只有首字母区分大小写</b>。(例如：command与cOMMAND、cOmMaNd和commanD视为相同处理)<br/>"
					+ "每个指令都有特定的参数，你需要查阅指令文档来了解一个指令的相关参数。(别担心，用到参数的地方不多。)<br/>"
					+ "例如：<br/>"
					+ "[pc.speech<i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(读者你好！)</i>]输出"+UtilText.parsePlayerSpeech("读者你好！")+""
					+ ""
					+ "</p>"
					
					+"<p>"
					+ "<b>指令修饰词(a_ an_)</b><br/>"
					+"你可以在参数前插入“a_”或“an_”以自动生成适当的代词。(你可以根据自己的喜好选择a_或an_，它们的效果完全相同。)<br/>"
					+"例如:<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>height</i>]输出“高挑”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_height</i>]输出“高挑”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>an_height</i>]<b>同样</b>输出“高挑”<br/><br/>"
					
					+"对于某些身体部位的名称，情况可能会复杂一些。<br/>"
					+ "例如：<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>arms</i>]输出“翅膀”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_arms</i>]输出“一对翅膀”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>an_arms</i>]<b>同样</b>输出“一对翅膀”<br/><br/>"
					+ "</p>"
					
					+"<p>"
					+ "<b>指令修饰词(大写字母)(注：以下内容在汉化版无法生效)</b><br/>"
					+"大部分指令能够使用大写字母。有些指令，比如输出数字的指令，它们能够读取大写字母指令，但无法输出大写字母<br/>"
					+ "要想输出大写字母，你要做的就是将指令名称的<b>第一个字母</b>大写。<br/>"
					+"例如:<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>height</i>]输出“tall”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>hEIGHT</i>]<b>同样</b>输出“tall”(应该没人会用这种指令吧……)<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>Height</i>]输出“Tall”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_height</i>]输出“a tall”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>A_height</i>]输出“A tall”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_Height</i>]<b>同样</b>输出“A tall”<br/><br/>"
					
					+"<p>"
					+ "<b>指令修饰词(+ D)</b><br/>"
					+"大多数输出名词的指令可以应用额外的<b>随机描述词</b>。(你可以在“指令”页面查看哪些指令能应用“+”和“D”修饰词。)<br/>"
					+ "若要将额外描述词应用于输出文本，只需在指令末尾添加“+”、“d”或“D”。<br/>"
					+ "<b>可与“a_ an_”以及“大写字母”修饰词一起使用。</b><br/>"
					+"例如:<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>arms</i>]输出“翅膀”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>arms+</i>]输出“覆羽翅膀”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>armsD</i>]<b>同样</b>输出“覆羽翅膀”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>armsd</i>]<b>同样</b>输出“覆羽翅膀”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>ArmsD</i>]输出“覆羽翅膀”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_arms+</i>]输出“一双覆羽翅膀”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>A_arms+</i>]输出“一双覆羽翅膀”<br/><br/>"
					
					+"有些输出文本具有更强的随机性。<br/>"
					+ "例如：<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy</i>]输出“秘缝”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy</i>]输出“小穴”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy+</i>]输出“潮湿的小穴”<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy+</i>]输出“长有触手的秘缝”<br/><br/>"
					+ "</p>"
					+ "<br/>"

					+ "<h6>结语：</h6><br/>"
					+"<p>"
					+"<b>有效的</b>指令语法：<br/>"
					+"[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>目标</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>指令</i>]<br/>"
					+"或是<br/>"
					+"[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>目标</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>指令</i>"
							+ "<i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(参数)</i>]<br/><br/>"
					+ "<br/>"

					+ "<h6>例如：</h6><br/>"
					+ "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>brax</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]"
						+ "向后靠在[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>brax</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>his</i>]"
						+ "的椅子上，想知道[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>arthur</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]"
						+ "在[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>brax</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>he</i>]"
						+ "把[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>arthur</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>him</i>]交给斯嘉丽。<br/>"
					+ "输出：<br/>"
					+ "“布拉克斯向后靠在他的椅子上，想知道亚瑟在他把他交给斯嘉丽后发生了什么。”<br/><br/>"
					
					+ "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>rose</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]"
						+ "向后靠在"
						+ "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>rose</i>.<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>herHuis</i>]"
						+ "椅子上，"
						+ "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>rose</i>.<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>shyeHe</i>]"
						+ "浅尝了一口[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>rose</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]咖啡并发出了一声轻叹。<br/>"
					+ "输出：<br/>"
					+ UtilText.parse("萝丝向后靠在[rose.herHuis]椅子上，[rose.shyeHe]浅尝了一口萝丝咖啡并发出了一声轻叹。")+"<br/>"
					+ "<b>注意：</b><i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>错别字</i>将导致解析器输出无效的指令字符串，但是"
							+ "<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>不正确的指令</i>(比如说把.herHis错打成.name)并不会报错！<br/><br/>"
					
					+ "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>lilaya</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]"
						+ "怒气冲冲地找到[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>innoxia</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]，"
						+ "发现"
						+ "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>lilaya</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>her</i>]性爱场景至今还没修复好，于是生气地大喊道，"
								+ "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>lilaya</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>speech</i>"
										+ "<i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(你到底都在忙些什么，Innoxia？！一星期前你就在说要重写我的场景对话了！)</i>]<br/>"
					+ "输出：<br/>"
					+ "莉莱雅怒气冲冲地找到Innoxia，发现她的性爱场景至今还没修复好，于是生气地大喊道，"
						+UtilText.parseSpeech("你到底都在忙些什么，Innoxia？！一星期前你就在说要重写我的场景对话了！", Main.game.getNpc(Lilaya.class))+""
					
					+ "</p>";
		}
		
		@Override
		public String getContent(){
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("解析器", "", PARSER);
					
			} else if (index == 2) {
				return new Response("帮助", "", null);
					
			} else if (index == 3) {
				return new Response("目标", "", PARSER_TARGETS);
				
			} else if (index == 4) {
				return new Response("指令", "", PARSER_COMMANDS_NEAT);
				
			} else if (index == 5) {
				return new Response("指令列表", "", PARSER_COMMANDS);
				
			} else if (index == 0) {
				return new Response("返回", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	public static final DialogueNode PARSER_TARGETS = new DialogueNode("解析器", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>以下列出一些可被识别的<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>目标</i>，应用于解析句式：<br/>"
					+ "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>目标</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>指令</i>"
							+ "<i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(参数)</i>]</p>");
			
			for(AbstractParserTarget character : ParserTarget.getAllParserTargets()) {
				UtilText.nodeContentSB.append("<hr/>"
						+"<p>");
				
				boolean first=true;
				for(String s : character.getTags()) {
					UtilText.nodeContentSB.append((first?"":" | ") +"<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>"+s+"</i>");
					first = false;
				}
				
				UtilText.nodeContentSB.append("<br/>"
						+character.getDescription()
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return  "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("解析器", "", PARSER);
				
			} else if (index == 2) {
				return new Response("帮助", "", PARSER_HELP);
				
			} else if (index == 3) {
				return new Response("目标", "", null);
				
			} else if (index == 4) {
				return new Response("指令", "", PARSER_COMMANDS_NEAT);
				
			} else if (index == 5) {
				return new Response("指令列表", "", PARSER_COMMANDS);
				
			} else if (index == 0) {
				return new Response("返回", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	public static final DialogueNode PARSER_COMMANDS = new DialogueNode("解析器", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>以下列出一些可被识别的<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>指令</i>，应用于解析句式：<br/>"
						+ "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>目标</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>指令</i>"
						+ "<i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(参数)</i>]</p>"
						+ "<p>"
							+ "<b>请不要被指令的数目吓倒！</b>"
							+ "它们中的<i>绝大多数</i>都是根据易懂(希望如此)的命名系统自动生成的身体部位的“标准”变体。"
						+ "</p>");
			
			int count=1;
			for(ParserCommand command : UtilText.commandsList) {
				UtilText.nodeContentSB.append("<hr/>"
						+ "<p>"
						+ "<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"+String.format("%03d.", count)+"</b><i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(0)+"</i>");
				
				if(command.getTags().size()>1) {
					for(int i = 1; i<command.getTags().size(); i++)
						UtilText.nodeContentSB.append(" |<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(i)+"</i>");
				}
				
				UtilText.nodeContentSB.append("</p>");
				count++;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return  "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("解析器", "", PARSER);
				
			} else if (index == 2) {
				return new Response("帮助", "", PARSER_HELP);
				
			} else if (index == 3) {
				return new Response("目标", "", PARSER_TARGETS);
				
			} else if (index == 4) {
				return new Response("指令", "", PARSER_COMMANDS_NEAT);
				
			} else if (index == 5) {
				return new Response("指令列表", "", null);
				
			} else if (index == 0) {
				return new Response("返回", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	public static final DialogueNode PARSER_COMMANDS_NEAT = new DialogueNode("解析器", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>以下列出一些可被识别的<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>指令</i>，应用于解析句式：<br/>"
						+ "[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>目标</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>指令</i>"
						+ "<i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(参数)</i>]</p>"
						+ "<p>"
							+ "<b>请不要被指令的数目吓倒！</b>"
							+ "它们中的<i>绝大多数</i>都是根据易懂(希望如此)的命名系统自动生成的身体部位的“标准”变体。"
						+ "</p>");
			
			int count = 1;
			for(BodyPartType bpt : BodyPartType.values()) {
				UtilText.nodeContentSB.append("<details>"
						+ "<summary style='cursor:pointer;'><b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>("
							+String.format("%03d", count)+"-"+String.format("%03d", count+UtilText.commandsMap.get(bpt).size()-1)+")</b>"+Util.capitaliseSentence(bpt.getName())+"</summary>");
				for(ParserCommand command : UtilText.commandsMap.get(bpt)) {
					UtilText.nodeContentSB.append("<p>"
							+ "<hr/>"
							+ "<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"+String.format("%03d.", count)+"</b><i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(0)+"</i>");
					
					if(command.getTags().size()>1) {
						for(int i = 1; i<command.getTags().size(); i++)
							UtilText.nodeContentSB.append(" |<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(i)+"</i>");
					}
					
					UtilText.nodeContentSB.append("<br/>"
							+(command.getArguments()==""?"<i style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>无参数</i>":"<i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>"+command.getArguments()+"</i>")+"<br/>"
							+(command.isAllowsCapitalisation()?"<i style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>大写字母</i>":"<i style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>大写字母</i>")
								+ " | " +(command.isAllowsPronoun()?"<i style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>代词</i>":"<i style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>代词</i>")
								+"<br/>"
							+command.getDescription()+"<br/>"
							+"示例:<br/>"
							+ command.getExampleBeforeParse("lilaya", (command.getArguments()==""?"":command.getArgumentExample()))+" -> "
								+UtilText.parse("[lilaya."+command.getTags().get(0)+(command.getArguments()==""?"":"("+command.getArgumentExample()+")")+"]")+"<br/>"
							
							+ command.getExampleBeforeParse("brax", (command.getArguments()==""?"":command.getArgumentExample()))+" -> "
								+UtilText.parse("[brax."+command.getTags().get(0)+(command.getArguments()==""?"":"("+command.getArgumentExample()+")")+"]")+"<br/>"
							
							+ command.getExampleBeforeParse("kate", (command.getArguments()==""?"":command.getArgumentExample()))+" -> "
								+UtilText.parse("[kate."+command.getTags().get(0)+(command.getArguments()==""?"":"("+command.getArgumentExample()+")")+"]")
							+"</p>");
					
					count++;
				}

				UtilText.nodeContentSB.append("</details>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return  "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("解析器", "", PARSER);
				
			} else if (index == 2) {
				return new Response("帮助", "", PARSER_HELP);
				
			} else if (index == 3) {
				return new Response("目标", "", PARSER_TARGETS);
				
			} else if (index == 4) {
				return new Response("指令", "", null);
				
			} else if (index == 5) {
				return new Response("指令列表", "", PARSER_COMMANDS);
				
			} else if (index == 0) {
				return new Response("返回", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	
	public static final DialogueNode POST_SEX_2KOMA = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			if(Main.sex.isDom(Main.game.getPlayer())) {
				GameCharacter target = Main.sex.getSubmissiveParticipants(false).entrySet().iterator().next().getKey();
				return UtilText.parseFromXMLFile("misc/misc", "POST_SEX_2KOMA", target);
			} else {
				GameCharacter target = Main.sex.getDominantParticipants(false).entrySet().iterator().next().getKey();
				return UtilText.parseFromXMLFile("misc/misc", "POST_SEX_2KOMA_AS_SUB", target);
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.sex.isDom(Main.game.getPlayer())) {
					return new Response("继续", "你现在已经将这个婊子放在了正确的位置，可以继续干别的事了……", Main.game.getDefaultDialogue(false));
				} else {
					return new Response("继续", "你这个婊子现在已经被放在了该在的位置，可以继续干你的事了……", Main.game.getDefaultDialogue(false));
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode CENTAUR_SEX = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			NPC centaur = Main.game.getActiveNPC();
			return UtilText.parseFromXMLFile("misc/misc", "A_WILD_CENTAUR_APPEARS", centaur);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			NPC centaur = Main.game.getActiveNPC();
			
			if(index==1) {
				return new ResponseSex(
						UtilText.parse(centaur, "支配[npc.herHim]"),
						UtilText.parse(centaur, "作为支配的角色，操[npc.race]。"),
						true,
						false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(centaur),
								Main.game.getPlayer().getCompanions(),
								null),
						DebugDialogue.POST_SEX_CENTAUR,
						"<p>"
							+ "你决定被这只魔法般出现在你面前的[npc.race]支配，走到[npc.herHim]身前呜叫，"
							+ "[pc.speech(是时候把你放到正确的位置上了！)]"
						+ "</p>");
				
			} else if(index==2) {
				return new ResponseSex(
						UtilText.parse(centaur, "顺从[npc.herHim]"),
						UtilText.parse(centaur, "作为顺从的角色，让[npc.race]操你。"),
						true,
						false,
						new SMGeneric(
								Util.newArrayListOfValues(centaur),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Main.game.getPlayer().getCompanions()),
						DebugDialogue.POST_SEX_CENTAUR,
						"<p>"
							+ "你决定被这只魔法般出现在你面前的[npc.race]支配，走到[npc.herHim]身前恳求，"
							+ "[pc.speech(是时候把我放到正确的位置上了！)]"
						+ "</p>");
				
			} else if(index==0) {
				return new Response("放着不管", UtilText.parse(centaur, "你决定放着[npc.race]不动，继续你该干的事……"), Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.banishNPC(centaur);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_SEX_CENTAUR = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			if(Main.sex.isDom(Main.game.getPlayer())) {
				GameCharacter target = Main.sex.getSubmissiveParticipants(false).entrySet().iterator().next().getKey();
				return UtilText.parseFromXMLFile("misc/misc", "POST_SEX_CENTAUR", target);
			} else {
				GameCharacter target = Main.sex.getDominantParticipants(false).entrySet().iterator().next().getKey();
				return UtilText.parseFromXMLFile("misc/misc", "POST_SEX_CENTAUR_AS_SUB", target);
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				NPC centaur = Main.game.getActiveNPC();
				if(Main.sex.isDom(Main.game.getPlayer())) {
					return new Response("继续", UtilText.parse(centaur, "你现在已经将[npc.race]放在了正确的位置，可以继续干别的事了……"), Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.banishNPC(centaur);
						}
					};
				} else {
					return new Response("继续", UtilText.parse(centaur, "你现在已经将[npc.race]放在了正确的位置，可以继续干别的事了……"), Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.banishNPC(centaur);
						}
					};
				}
			}
			return null;
		}
	};
	
	private static StringBuilder spawnrateSB;
	private static float spawnTotal, spawnTotalMasculine, spawnTotalFeminine;
	public static final DialogueNode SPAWN_RATES = new DialogueNode("", "", false) {
		@Override
		public String getAuthor() { return "AceXp"; }
		@Override
		public String getContent() {
			return spawnrateSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode COMBAT_MOVES = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			for(CombatMoveCategory cat : CombatMoveCategory.values()) {
				sb.append("<h4>"+cat.toString()+":</h4>");
				int i=0;
				List<AbstractCombatMove> categoryMoves = new ArrayList<>(CombatMove.getAllCombatMovesInCategory(cat));
				Collections.sort(categoryMoves, (m1, m2) ->
					m1.getType().compareTo(m2.getType())==0 && cat==CombatMoveCategory.SPELL
						?m1.getAssociatedSpell().getSpellSchool().compareTo(m2.getAssociatedSpell().getSpellSchool())
						:m1.getType().compareTo(m2.getType()));
				
				for(AbstractCombatMove move : categoryMoves) {
					sb.append("<div class='container-full-width' style='width:95%; padding:4px; margin:4px 2.5% 4px 2.5%; background-color:"+(i%2==0?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");
						sb.append("<p style='span:0; margin:0; -webkit-user-select:auto;'>");
							sb.append("<b style='color:"+move.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(move.getName(0, Main.game.getPlayer()))+"</b>: "+move.getDescription(0, Main.game.getPlayer()));
							sb.append("<br/>");
							if(cat==CombatMoveCategory.SPELL) {
								sb.append("种类：<span style='color:"+move.getAssociatedSpell().getSpellSchool().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(move.getAssociatedSpell().getSpellSchool().getName())+"法术</span> | ");
							} else {
								sb.append("种类：<span style='color:"+move.getType().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(move.getType().getName())+"</span> | ");
							}
							sb.append("ID: <span style='font-family:monospace;'>"+move.getIdentifier()+"</span>");
						sb.append("</p>");
					sb.append("</div>");
					i++;
				}
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};


	public static final DialogueNode RANDOM_ENCHANTMENT_CLOTHING = new DialogueNode("随机衣物附魔", "", false) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parse("<h4>[style.colourMinorGood(正面附魔:)]</h4>"));
			int i=0;
			for(RandomEnchantment randomEnchantment : RandomEnchantment.getAllPositiveClothingEnchantments()) {
				sb.append(getFormattedEnchantmentEntry(i%2==0, randomEnchantment));
				i++;
			}
			
			sb.append(UtilText.parse("<h4>[style.colourMinorBad(负面附魔:)]</h4>"));
			i=0;
			for(RandomEnchantment randomEnchantment : RandomEnchantment.getAllNegativeClothingEnchantments()) {
				sb.append(getFormattedEnchantmentEntry(i%2==0, randomEnchantment));
				i++;
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
		@Override
		public boolean isContentParsed() {
			return false;
		}
	};
	
	public static List<String> randomEnchantmentClothingIds = Util.newArrayListOfValues("innoxia_groin_panties", "innoxia_torso_tshirt", "WRIST_MENS_WATCH");
	
	private static String getFormattedEnchantmentEntry(boolean oddRow, RandomEnchantment randomEnchantment) {
		StringBuilder sb = new StringBuilder();
		
		String preParsingString = randomEnchantment.getConditionalPreParsingString();
		UtilText.parse(Main.game.getPlayer(), preParsingString); // Parse the conditional to make sure that any variables for name have been set
		preParsingString = preParsingString.replaceAll("(?<!^)\\[#", "<br/>\\[#"); // 格式化以供显示
		
		sb.append("<div class='container-full-width' style='width:95%; padding:4px; margin:4px 2.5% 4px 2.5%; background-color:"+(oddRow?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");
			sb.append("<div class='container-full-width' style='span:0; margin:0; width:50%; -webkit-user-select:auto; text-align:center; background:#00000000;'>");
				sb.append("<b>"+Util.capitaliseSentence(UtilText.parse(randomEnchantment.getName(null)))+"</b>");
			sb.append("</div>");
			sb.append("<div class='container-full-width' style='span:0; margin:0; width:50%; -webkit-user-select:auto; font-family:monospace; font-size:0.75em;background:#00000000;'>");
				if(randomEnchantment.isMod()) {
					sb.append(UtilText.parse("[style.colourGreenLight(模组)] "));
				} else {
					sb.append(UtilText.parse("[style.colourBlueLight(资源)] "));
				}
				sb.append(RandomEnchantment.getIdFromRandomEnchantment(randomEnchantment));
			sb.append("</div>");

			sb.append("<div class='container-full-width' style='background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; -webkit-user-select:auto; margin-top:0;'>");
				sb.append("生成权重:<br/>");
				sb.append("<span style='font-family:monospace; font-size:0.75em;'>"+randomEnchantment.getSpawnWeighting()+"</span>");
			sb.append("</div>");
			
			if(!preParsingString.isEmpty()) {
				sb.append("<div class='container-full-width' style='background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; -webkit-user-select:auto; margin-top:0;'>");
					sb.append("条件预解析:<br/>");
					sb.append("<span style='font-family:monospace; font-size:0.75em;'>"+preParsingString+"</span>");
				sb.append("</div>");
			}
			
			
			sb.append("<div class='container-full-width' style='margin:0; padding:0; background:#00000000; text-align:center;'>");
				sb.append("<div class='normal-button' id='RANDOM_ENCHANTMENT_"+randomEnchantmentClothingIds.get(0)+"_"+RandomEnchantment.getIdFromRandomEnchantment(randomEnchantment)+"'"
						+ " style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.FEMININE.toWebHexString()+";'>生成内裤</div>");
				sb.append("<div class='normal-button' id='RANDOM_ENCHANTMENT_"+randomEnchantmentClothingIds.get(1)+"_"+RandomEnchantment.getIdFromRandomEnchantment(randomEnchantment)+"'"
						+ " style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>生成T恤衫</div>");
				sb.append("<div class='normal-button' id='RANDOM_ENCHANTMENT_"+randomEnchantmentClothingIds.get(2)+"_"+RandomEnchantment.getIdFromRandomEnchantment(randomEnchantment)+"'"
						+ " style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.MASCULINE.toWebHexString()+";'>生成手表</div>");
			sb.append("</div>");
			
		sb.append("</div>");
		
		return sb.toString();
	}
	
	//TODO
	public static final DialogueNode RANDOM_ENCHANTMENT_POTIONS = new DialogueNode("随机药水附魔", "", false) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parse("<h4>[style.colourMinorGood(正面附魔：)]</h4>"));
			int i=0;
			for(RandomPotionEnchantment randomEnchantment : RandomPotionEnchantment.getAllPositivePotionEnchantments()) {
				sb.append(getFormattedPotionEnchantmentEntry(i%2==0, randomEnchantment));
				i++;
			}
			
			sb.append(UtilText.parse("<h4>[style.colourMinorBad(负面附魔：)]</h4>"));
			i=0;
			for(RandomPotionEnchantment randomEnchantment : RandomPotionEnchantment.getAllNegativePotionEnchantments()) {
				sb.append(getFormattedPotionEnchantmentEntry(i%2==0, randomEnchantment));
				i++;
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
		@Override
		public boolean isContentParsed() {
			return false;
		}
	};
	
	private static String getFormattedPotionEnchantmentEntry(boolean oddRow, RandomPotionEnchantment randomEnchantment) {
		StringBuilder sb = new StringBuilder();
		
		String preParsingString = randomEnchantment.getConditionalPreParsingString();
		UtilText.parse(Main.game.getPlayer(), preParsingString); // Parse the conditional to make sure that any variables for name have been set
		preParsingString = preParsingString.replaceAll("(?<!^)\\[#", "<br/>\\[#"); // Format it for display
		
		sb.append("<div class='container-full-width' style='width:95%; padding:4px; margin:4px 2.5% 4px 2.5%; background-color:"+(oddRow?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");
			sb.append("<div class='container-full-width' style='span:0; margin:0; width:50%; -webkit-user-select:auto; text-align:center; background:#00000000;'>");
				sb.append("<b>"+Util.capitaliseSentence(UtilText.parse(randomEnchantment.getName(null)))+"</b>");
			sb.append("</div>");
			sb.append("<div class='container-full-width' style='span:0; margin:0; width:50%; -webkit-user-select:auto; font-family:monospace; font-size:0.75em;background:#00000000;'>");
				if(randomEnchantment.isMod()) {
					sb.append(UtilText.parse("[style.colourGreenLight(模组)] "));
				} else {
					sb.append(UtilText.parse("[style.colourBlueLight(资源)] "));
				}
				sb.append(RandomPotionEnchantment.getIdFromRandomEnchantment(randomEnchantment));
			sb.append("</div>");

			sb.append("<div class='container-full-width' style='background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; -webkit-user-select:auto; margin-top:0;'>");
				sb.append("生成权重:<br/>");
				sb.append("<span style='font-family:monospace; font-size:0.75em;'>"+randomEnchantment.getSpawnWeighting()+"</span>");
			sb.append("</div>");
			
			if(!preParsingString.isEmpty()) {
				sb.append("<div class='container-full-width' style='background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; -webkit-user-select:auto; margin-top:0;'>");
					sb.append("条件预解析:<br/>");
					sb.append("<span style='font-family:monospace; font-size:0.75em;'>"+preParsingString+"</span>");
				sb.append("</div>");
			}
			
			
			sb.append("<div class='container-full-width' style='margin:0; padding:0; background:#00000000; text-align:center;'>");
				sb.append("<div class='normal-button' id='RANDOM_POTION_ENCHANTMENT_"+RandomPotionEnchantment.getIdFromRandomEnchantment(randomEnchantment)+"'"
						+ " style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>生成魔药</div>");
			sb.append("</div>");
			
		sb.append("</div>");
		
		return sb.toString();
	}
}
