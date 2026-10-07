package com.lilithsthrone.game.dialogue.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.fetishes.FetishLevel;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.pregnancy.Litter;
import com.lilithsthrone.game.character.pregnancy.PregnancyPossibility;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.quests.QuestType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.npcDialogue.elemental.ElementalDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaBirthing;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.universal.SMMasturbation;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.time.DateAndTime;
import com.lilithsthrone.utils.time.SolarElevationAngle;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.0
 * @version 0.3.9
 * @author Innoxia, tukaima
 */
public class PhoneDialogue {
	
	private static class offspringTableLineSubject {
		boolean female;
		boolean is_feral;
		String child_name;
		String race_color;
		String species_name;
		String mother;
		String father;
		String incubator;
		List<String> relationships;

		offspringTableLineSubject(NPC npc) {
			this.female = npc.isFeminine();
			this.is_feral = npc.isFeral();
			this.child_name = npc.getName(true);
			this.race_color = npc.getRace().getColour().toWebHexString();
			this.species_name = this.female
					? Util.capitaliseSentence(npc.getSubspecies().getSingularFemaleName(npc.getBody()))
					: Util.capitaliseSentence(npc.getSubspecies().getSingularMaleName(npc.getBody()));
			this.mother = (npc.getMother() == null ? "???" : (npc.getMother().isPlayer() ? "[style.colourExcellent(你)]" : Util.capitaliseSentence(npc.getMother().getName(true))));
			if(npc.getMother()==null && !npc.getMotherName().equals("???")) {
				mother = npc.getMotherName();
			}

			this.father = (npc.getFather() == null ? "???" : (npc.getFather().isPlayer() ? "[style.colourExcellent(你)]" : Util.capitaliseSentence(npc.getFather().getName(true))));
			if(npc.getFather()==null && !npc.getFatherName().equals("???")) {
				father = npc.getFatherName();
			}

			this.incubator = (npc.getIncubator() == null ? "[style.colourDisabled(n/a)]" : (npc.getIncubator().isPlayer() ? "[style.colourExcellent(你)]" : Util.capitaliseSentence(npc.getIncubator().getName(true))));
			if(npc.getIncubator()==null && !npc.getIncubatorName().equals("???")) {
				incubator = npc.getIncubatorName();
			}

			Set<Relationship> extraRelationships = Main.game.getPlayer().getRelationshipsTo(npc, Relationship.Parent);
			this.relationships = extraRelationships.stream().map((relationship) -> relationship.getName(Main.game.getPlayer())).collect(Collectors.toList());
			if(npc.getIncubator()!=null && npc.getIncubator().isPlayer()) {
				int insertIndex = 0;
				this.relationships.add(insertIndex, "Incubator-mother");

				if(npc.getMother()!=null && npc.getMother().isPlayer()) {
					insertIndex++;
					this.relationships.add(insertIndex, "mother");
				}
				if(npc.getFather()!=null && npc.getFather().isPlayer()) {
					insertIndex++;
					this.relationships.add(insertIndex, "father");
				}

			} else if(npc.getMother()!=null && npc.getMother().isPlayer()) {
				this.relationships.add(0, "Mother");

				if(npc.getFather()!=null && npc.getFather().isPlayer()) {
					this.relationships.add(1, "father");
				}

			} else {
				this.relationships.add(0, "Father");
			}
		}

		offspringTableLineSubject(OffspringSeed os) {
			this.female = os.isFeminine();
			this.is_feral = os.isFeral();
			this.child_name = "未知";
			this.race_color = os.getRace().getColour().toWebHexString();
			this.species_name = this.female
					? Util.capitaliseSentence(os.getSubspecies().getSingularFemaleName(os.getBody()))
					: Util.capitaliseSentence(os.getSubspecies().getSingularMaleName(os.getBody()));
			this.mother = (os.getMother() == null ? "???" : (os.getMother().isPlayer() ? "[style.colourExcellent(你)]" : Util.capitaliseSentence(os.getMother().getName(true))));
			if(os.getMother()==null && !os.getMotherName().equals("???")) {
				mother = os.getMotherName();
			}

			this.father = (os.getFather() == null ? "???" : (os.getFather().isPlayer() ? "[style.colourExcellent(你)]" : Util.capitaliseSentence(os.getFather().getName(true))));
			if(os.getFather()==null && !os.getFatherName().equals("???")) {
				father = os.getFatherName();
			}

			this.incubator = (os.getIncubator() == null ? "[style.colourDisabled(n/a)]" : (os.getIncubator().isPlayer() ? "[style.colourExcellent(你)]" : Util.capitaliseSentence(os.getIncubator().getName(true))));
			if(os.getIncubator()==null && !os.getIncubatorName().equals("???")) {
				incubator = os.getIncubatorName();
			}
			this.relationships = new ArrayList<>();
//			Set<Relationship> extraRelationships = Main.game.getPlayer().getRelationshipsTo(os, Relationship.Parent);
//			this.relationships = extraRelationships.stream().map((relationship) -> relationship.getName(Main.game.getPlayer())).collect(Collectors.toList());
			if(os.getIncubator()!=null && os.getIncubator().isPlayer()) {
				int insertIndex = 0;
				this.relationships.add(insertIndex, "Incubator-mother");
				
				if(os.getMother()!=null && os.getMother().isPlayer()) {
					insertIndex++;
					this.relationships.add(insertIndex, "mother");
				}
				if(os.getFather()!=null && os.getFather().isPlayer()) {
					insertIndex++;
					this.relationships.add(insertIndex, "father");
				}

			} else if(os.getMother()!=null && os.getMother().isPlayer()) {
				this.relationships.add(0, "Mother");

				if(os.getFather()!=null && os.getFather().isPlayer()) {
					this.relationships.add(1, "father");
				}

			} else {
				this.relationships.add(0, "Father");
			}

		}
	}

	private static List<GameCharacter> charactersEncountered;
	private static StringBuilder journalSB;
	private static SexAreaOrifice layingEggsArea;
	private static Set<String> incubationOffspringBirthed;
	
	private static void applyEggLayingEffects() {
		incubationOffspringBirthed = new HashSet<>();
		incubationOffspringBirthed.addAll(Main.game.getPlayer().getIncubationLitter(layingEggsArea).getOffspring());
		Main.game.getPlayer().endIncubationPregnancy(layingEggsArea, true);
		switch(layingEggsArea) {
			case ANUS:
				if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
					Main.game.getPlayer().incrementAssStretchedCapacity(15);
					Main.game.getPlayer().incrementAssCapacity(
							(Main.game.getPlayer().getAssStretchedCapacity()-Main.game.getPlayer().getAssRawCapacityValue())*Main.game.getPlayer().getAssPlasticity().getCapacityIncreaseModifier(),
							false);
				}
				break;
			case NIPPLE:
				if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
					Main.game.getPlayer().incrementNippleStretchedCapacity(15);
					Main.game.getPlayer().incrementNippleCapacity(
							(Main.game.getPlayer().getNippleStretchedCapacity()-Main.game.getPlayer().getNippleRawCapacityValue())*Main.game.getPlayer().getNipplePlasticity().getCapacityIncreaseModifier(),
							false);
				}
				break;
			case NIPPLE_CROTCH:
				if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
					Main.game.getPlayer().incrementNippleCrotchStretchedCapacity(15);
					Main.game.getPlayer().incrementNippleCrotchCapacity(
							(Main.game.getPlayer().getNippleCrotchStretchedCapacity()-Main.game.getPlayer().getNippleCrotchRawCapacityValue())*Main.game.getPlayer().getNippleCrotchPlasticity().getCapacityIncreaseModifier(),
							false);
				}
				break;
			case VAGINA:
				if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
					Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
					Main.game.getPlayer().incrementVaginaCapacity(
							(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
							false);
				}
				break;
			default:
				break;
		}
		Main.game.getPlayer().setMana(0);
	}
	
	public static final DialogueNode MENU = new DialogueNode("手机主界面", "手机", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
//			if(Main.game.isInGlobalMap()) {
//				UtilText.nodeContentSB.append(RenderingEngine.ENGINE.getFullWorldMap());
//			} else {
//				UtilText.nodeContentSB.append(RenderingEngine.ENGINE.getFullMap(Main.game.getPlayer().getWorldLocation(), true));
//			}
			
			UtilText.nodeContentSB.append("<p>你拿出手机，输入锁屏密码。</p>");
			
			if(Main.game.isInNewWorld()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+"你通过强大的灵能，成功找到了一种利用奥术给手机充电的方法，然而考虑到这是这个世界唯一的手机，你没办法给别人打电话。"
							+"作为代替，你把它用来保存在这个陌生的新世界里发现的事物的信息。"
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(!Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
				if(index==0) {
					return "手机";
				} else if(index==1) {
					return "[style.colourYellowLight(蛋)]";
				}
			}
			return super.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response(
							(Main.game.getPlayer().isMainQuestUpdated() || Main.game.getPlayer().isSideQuestUpdated() || Main.game.getPlayer().isRelationshipQuestUpdated())
								?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>任务</span>"
								:"任务",
							"打开日程表以查看当前任务。", PLANNER_MAIN){
						@Override
						public void effects() {
							Main.game.getPlayer().setMainQuestUpdated(false);
						}
					};
					
				} else if (index == 2) {
					return new Response(
							Main.getProperties().hasValue(PropertyValue.levelUpHightlight)
								? "<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>天赋树</span>"
								:"天赋树",
							"查看角色信息。", CHARACTER_PERK_TREE);
					
				} else if (index == 3) {
					return new Response("法术", "查看你的法术页面。", SpellManagement.CHARACTER_SPELLS_EARTH) {
						@Override
						public void effects() {
							SpellManagement.setSpellOwner(Main.game.getPlayer(), MENU);
						}
					};
					
				} else if (index == 4) {
					return new Response("性癖", "查看你的性癖页面。", CHARACTER_FETISHES);
					
				} else if (index == 5) {
					return new Response("统计", "查看你的统计数据。", CHARACTER_STATS);
					
				} else if (index == 6) {
					return new Response("自拍", "自拍一张，好好欣赏下自己。", CHARACTER_APPEARANCE);
					
				} else if (index == 7) {
					if(Main.game.getPlayer().getCharactersEncountered().isEmpty()) {
						return new Response("联系人", "你还没见过任何人！", null);
					} else {
						return new Response("联系人", "因为这个世界上没有电话所以你并不能给任何人打电话，但依旧会记录下你接触过的任何人", CONTACTS) {
							@Override
							public void effects() {
								Main.game.getPlayer().sortCharactersEncountered();
								charactersEncountered = Main.game.getPlayer().getCharactersEncounteredAsGameCharacters(false);
							}
						};
					}
					
				} else if (index == 8) {
					return new Response(
							(Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered)
									|| Main.getProperties().hasValue(PropertyValue.newClothingDiscovered)
									|| Main.getProperties().hasValue(PropertyValue.newItemDiscovered)
									|| Main.getProperties().hasValue(PropertyValue.newRaceDiscovered))
								? "<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>百科全书</span>"
								:"百科全书",
							"查看目前为止你发现的所有种族和物品", ENCYCLOPEDIA){
						@Override
						public void effects() {
							resetContentForRaces();
						}
					};
					
				} else if (index == 9) {
					if(Main.game.getPlayer().isAbleToSelfTransform()) {
						return new Response("转化",
								"转化你的身体",
								BodyChanging.BODY_CHANGING_CORE) {
							@Override
							public void effects() {
								BodyChanging.setTarget(Main.game.getPlayer());
							}
						};
					} else {
						return new Response("转化", Main.game.getPlayer().getUnableToTransformDescription(), null);
					}
					
				} else if (index == 10) {
					return new Response("地图", "查看你去过的地方", MAP) {
						@Override
						public void effects() {
							worldTypeMap = Main.game.getPlayer().getWorldLocation();
						}
					};
					
				} else if (index == 11) {
					if(Main.game.isSavedDialogueNeutral()) {
						return new Response("战斗动作", "调整你在战斗中使用的核心战斗动作。", CombatMovesSetup.COMBAT_MOVES_CORE) {
							@Override
							public void effects() {
								CombatMovesSetup.setTarget(Main.game.getPlayer(), PhoneDialogue.MENU);
							}
						};
					} else {
						return new Response("战斗动作", "你现在可没空改你的核心战斗动作。", null);
					}
					
				} else if (index == 12) {
					if(!Main.game.isSavedDialogueNeutral()) {
							return new Response("自慰", "此时无法手淫 (只能在自由行动的场景触发)", null);
							
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						return new Response("自慰", Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getValue(), null);
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						return new Response("自慰",
								"鉴于你没有[style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+"性癖)]，你在会被别人看见的地方自慰会很难受！",
								null);
						
					} else {
						return new ResponseSex("自慰",
								"决定从目前正在做的事情中抽出时间来自慰。",
								true,
								true,
								new SMMasturbation(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMasturbation.STANDING))),
								Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)
									?Main.game.getPlayer().getParty()
									:null,
								null,
								AFTER_MASTURBATION,
								UtilText.parseFromXMLFile("misc/misc", "MASTURBATION"));
					}
					
				} else if (index == 13){
					String title = Main.game.isSillyMode()?"延后":"闲逛";
					if(!Main.game.isSavedDialogueNeutral()) {
						return new Response(title, "你只能在一个中立的对话场景中闲逛来打发时间！", null);
					}
					if(Main.game.getPlayerCell().getPlace().isDangerous()) {
						return new Response(title, "你只能在安全的地方闲逛来打发时间！", null);
					}
					if(Main.game.getPlayer().getLocationPlace().getPlaceType().isLoiteringEnabledOverride()) {
						if(!Main.game.getPlayer().getLocationPlace().getPlaceType().isLoiteringEnabled()) {
							return new Response(title, "这不是个闲逛的好地方！", null);
						}
					} else {
						if(!Main.game.getPlayerCell().getType().isLoiteringEnabled()) {
							return new Response(title, "这不是个闲逛的好地方！", null);
						}
					}
					return new Response(title, "准备在这个区域闲逛一段不确定的时间。", LOITER_SELECTION);
					
				} else if (index == 14){
					if(!Main.game.getPlayer().isElementalSummoned()) {
						if(Main.game.getPlayer().hasDiscoveredElemental()) {
							return new Response("[el.Name]",
									"你还没有召唤[el.name]，所以你不能和[el.herHim]说话！"
											+ "<br/>[style.italicsMinorGood(你可以从你的“法术”屏幕召唤你的元素！)]",
									null);
						}
						return new Response("元素",
								"你还没有召唤你的元素，所以你不能和他们说话……"
										+ "<br/>[style.italicsMinorGood(你可以通过学习一个元素召唤法术并在你的“法术”屏幕上施放它来召唤你的元素！)]",
								null);
					}
					if(!Main.game.isSavedDialogueNeutral()) {
						return new Response("[el.Name]",
								Main.game.isInSex()
									?"你不能在性爱中与[el.name]说话！"
									:(Main.game.isInCombat()
										?"你不能在战斗中与[el.name]说话！"
										:"你不能在这个场景和[el.name]说话！"),
								null);
					}
					return new Response("[el.Name]",
							"花一些时间和[el.name]说话。",
							ElementalDialogue.ELEMENTAL_START);
					
				}
				
			} else if(responseTab==1) {
				Set<SexAreaOrifice> incubationAreas = Main.game.getPlayer().getIncubatingLitters().keySet();
				List<Response> responses = new ArrayList<>();
				
				if(incubationAreas.contains(SexAreaOrifice.VAGINA)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("产卵(子宫)", "现在不是产卵的时候。(只能在中立场景触发)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("产卵(子宫)", "附近没有合适的地方供你产卵！", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						responses.add(new Response("产卵(子宫)", "你当前无法使用你的阴道，所以你无法产出子宫中成熟的卵！", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("产卵(子宫)",
								"鉴于你并非[style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)]，你在会被别人看见的地方产卵会感到不适！",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_3)) {
						responses.add(new Response("产卵(子宫)", "你必须等待子宫中的蛋卵发育成熟才能产卵", null));
						
					} else {
						responses.add(new Response("产卵(子宫)", "在附近找一个合适的地方把你在子宫里孵化的蛋产下来", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.VAGINA;
								applyEggLayingEffects();
							}
						});
					}
				}
				if(incubationAreas.contains(SexAreaOrifice.ANUS)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("产卵(腹部)", "现在不是产卵的时候(只能在中立场景触发)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("产卵(腹部)", "附近没有合适的地方供你产卵！", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						responses.add(new Response("产卵(腹部)", "你当前无法使用你的肛门，所以你无法产出腹部中成熟的卵！", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("产卵(腹部)",
								"鉴于你并非[style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)]，你在会被别人看见的地方产卵会感到不适！",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_3)) {
						responses.add(new Response("产卵(腹部)", "你必须等待腹部里的蛋卵发育成熟才能产卵", null));
						
					} else {
						responses.add(new Response("产卵(腹部)", "在附近找一个合适的地方把你在腹部里孵化的蛋产下来", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.ANUS;
								applyEggLayingEffects();
							}
						});
					}
				}
				if(incubationAreas.contains(SexAreaOrifice.NIPPLE)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("产卵(胸部)", "现在不是产卵的时候。(只能在中立场景触发)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("产卵(胸部)", "附近没有合适的地方供你产卵！", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
						responses.add(new Response("产卵(胸部)", "你当前无法使用你的乳头，所以你无法产出胸部中成熟的卵！", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("产卵(胸部)",
								"鉴于你并非[style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)]，你在会被别人看见的地方产卵会感到不适！",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_3)) {
						responses.add(new Response("产卵(胸部)", "你必须等待胸部中的蛋卵发育成熟才能产卵", null));
						
					} else {
						responses.add(new Response("产卵(胸部)", "在附近找一个合适的地方把你在胸部里孵化的蛋产下来", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.NIPPLE;
								applyEggLayingEffects();
							}
						});
					}
				}
				if(incubationAreas.contains(SexAreaOrifice.NIPPLE_CROTCH)) {
					String udderName = Main.game.getPlayer().getBreastCrotchShape()==BreastShape.UDDERS?"腹乳":"胯乳";
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("产卵("+udderName+")", "现在不是产卵的时候。(只能在中立场景触发)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("产卵("+udderName+")", "附近没有合适的地方供你产卵！", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES_CROTCH, true)) {
						responses.add(new Response("产卵("+udderName+")", "你当前无法使用你的[pc.crotchNipples]，所以你无法产出[pc.crotchBoobs]中成熟的卵！", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("产卵("+udderName+")",
								"鉴于你并非[style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)]，你在会被别人看见的地方产卵会感到不适！",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_3)) {
						responses.add(new Response("产卵("+udderName+")", "你必须等待[pc.crotchBoobs]中的蛋卵发育成熟才能产卵", null));
						
					} else {
						responses.add(new Response("产卵("+udderName+")", "在附近找一个合适的地方把你在[pc.crotchBoobs]里孵化的蛋产下来", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.NIPPLE_CROTCH;
								applyEggLayingEffects();
							}
						});
					}
				
				}
				if(incubationAreas.contains(SexAreaOrifice.SPINNERET)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("产卵(丝囊)", "现在不是产卵的时候。(只能在中立场景触发)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("产卵(丝囊)", "附近没有合适的地方供你产卵！", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("产卵(丝囊)",
								"鉴于你并非[style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)]，你在会被别人看见的地方产卵会感到不适！",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_3)) {
						responses.add(new Response("产卵(丝囊)", "你必须等待吐丝器中的蛋卵发育成熟才能产卵", null));
						
					} else {
						responses.add(new Response("产卵(丝囊)", "在附近找一个合适的地方把你在吐丝器里孵化的蛋产下来", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.SPINNERET;
								applyEggLayingEffects();
							}
						});
					}
				}
				
				if(index>0 && index-1<responses.size()) {
					return responses.get(index-1);
				}
				
				// Removed due to description handling being too messy
//				if(incubationAreas.size()>1) {
//					Set<SexAreaOrifice> readyToLay = new HashSet<>();
//					if(incubationAreas.contains(SexAreaOrifice.VAGINA) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_3)) {
//						readyToLay.add(SexAreaOrifice.VAGINA);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.ANUS) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_3)) {
//						readyToLay.add(SexAreaOrifice.ANUS);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.NIPPLE) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_3)) {
//						readyToLay.add(SexAreaOrifice.NIPPLE);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.NIPPLE_CROTCH) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_3)) {
//						readyToLay.add(SexAreaOrifice.NIPPLE_CROTCH);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.SPINNERET) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_3)) {
//						readyToLay.add(SexAreaOrifice.SPINNERET);
//					}
//					if(readyToLay.size()<=1) {
//						return new Response("Lay eggs (all)", "You need to wait until the eggs in at least two of your orifices have finished maturing before you're able to lay them all at once.", null);
//					} else {
//						return new Response("Lay eggs (all)", "Find a suitable place nearby in which to lay all of the matured eggs which you've been incubating in your orifices.", INCUBATION_EGG_LAYING) {
//							@Override
//							public void effects() {
//								layingEggsAreas = new HashSet<>(readyToLay);
//								applyEggLayingEffects();
//							}
//						};
//					}
//				}
			}
			
			if(index == 0) {
				return new ResponseEffectsOnly("返回", "把手机收起来。"){
					@Override
					public void effects() {
						Main.game.restoreSavedContent(false);
					}
				};
				
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode INCUBATION_EGG_LAYING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.addSpecialParsingString(Util.intToString(incubationOffspringBirthed.size()), true);
			UtilText.addSpecialParsingString(incubationOffspringBirthed.size()==1?"卵":"卵", false);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_"+layingEggsArea.toString()));
			
			if(incubationOffspringBirthed.size()==1) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_"+layingEggsArea.toString()+"_SINGLE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_"+layingEggsArea.toString()+"_MULTIPLE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("休息", "你的体力所剩无几了，眼皮开始变得沉重……", INCUBATION_EGG_LAYING_FINISHED){
					@Override
					public void effects() {
						if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_INCUBATION, Quest.SIDE_UTIL_COMPLETE));
						}
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode INCUBATION_EGG_LAYING_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.addSpecialParsingString(Util.intToString(incubationOffspringBirthed.size()), true);

			if(incubationOffspringBirthed.size()==1) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_FINISHED_SINGLE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_FINISHED_MULTIPLE"));
			}
			
			UtilText.nodeContentSB.append("<p style='text-align:center;'>");
				for(String id : incubationOffspringBirthed) {
					try {
						OffspringSeed offspring = Main.game.getOffspringSeedById(id);
						String descriptor = LilayaBirthing.getOffspringDescriptor(offspring);
						UtilText.nodeContentSB.append("<br/>"
								+ Util.capitaliseSentence(UtilText.generateSingularDeterminer(descriptor))+""+descriptor
								+ "<i style='color:"+offspring.getGender().getColour().toWebHexString()+";'>"+offspring.getGenderName()+"</i>"
								+ "<i style='color:"+offspring.getSubspecies().getColour(null).toWebHexString()+";'>"+offspring.getSubspecies().getName(offspring.getBody())+"</i>");
					} catch(Exception ex) {
					}
				}
			UtilText.nodeContentSB.append("</p>");
			
			if(incubationOffspringBirthed.size()==1) {
				try {
					OffspringSeed offspring = Main.game.getOffspringSeedById(incubationOffspringBirthed.iterator().next());
					UtilText.addSpecialParsingString(offspring.getSubspecies().getName(offspring.getBody()), true);
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_FINISHED_END_SINGLE"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_FINISHED_END_MULTIPLE"));
			}
	
			return UtilText.nodeContentSB.toString();
		}
	
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("继续", "成功产下卵后，你现在可以自由地继续前进了", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_MASTURBATION = new DialogueNode("完成", "你已经自慰得足够多了", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/misc", "AFTER_MASTURBATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程……", Main.game.getDefaultDialogue(false));

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode PLANNER_MAIN = new DialogueNode("任务栏", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			// Main Quests:
			QuestLine questLine = QuestLine.MAIN;
			List<Quest> questList = Main.game.getPlayer().getQuests().get(questLine);
			int index = questList.size()-1;
			Quest q = questList.get(index);
			
			if (Main.game.getPlayer().isQuestCompleted(questLine)) {
				journalSB.append(
						"<details open>"
						+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>"
							+ "Completed - " + questLine.getName()
						+ "</summary>");
				journalSB.append(getQuestBoxDiv(q, true));
				
			} else{
				journalSB.append(
						"<details open>"
							+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
								+ questLine.getName()
							+ "</summary>");
				journalSB.append(getQuestBoxDiv(q, false));
			}
			
			index--;
				
			while(index>=0) {
				q = questList.get(index);
				journalSB.append(getQuestBoxDiv(q, true));
				index--;
			}

			journalSB.append("</details>");

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("主线任务", "查看主线任务的进度。", null);
				
			} else if (index == 2) {
				return new Response(
						(Main.game.getPlayer().isSideQuestUpdated()
							?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>支线任务</span>"
							:"支线任务"),
						"查看支线任务。",
						PLANNER_SIDE){
					@Override
					public void effects() {
						Main.game.getPlayer().setSideQuestUpdated(false);
					}
				};
				
			} else if (index == 3) {
				return new Response(
						(Main.game.getPlayer().isRelationshipQuestUpdated()
							?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>浪漫任务</span>"
							:"浪漫任务"),
						"查看浪漫任务。",
						PLANNER_RELATIONSHIP){
					@Override
					public void effects() {
						Main.game.getPlayer().setRelationshipQuestUpdated(false);
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "返回到手机主页面。", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode PLANNER_SIDE = new DialogueNode("任务栏", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			boolean sideQuestsFound = false;
			
			// Side Quests:
			List<QuestLine> sideQuests = new ArrayList<>(Main.game.getPlayer().getQuests().keySet());
			sideQuests.sort((q1, q2)->
				Main.game.getPlayer().isQuestCompleted(q1)
					?(Main.game.getPlayer().isQuestCompleted(q2)
						?0
						:1)
					:(Main.game.getPlayer().isQuestCompleted(q2)
						?-1
						:0));
			for (QuestLine questLine : sideQuests) {
				if(questLine.getType()==QuestType.SIDE) {
					sideQuestsFound = true;
					
					List<Quest> questList = Main.game.getPlayer().getQuests().get(questLine);
					int index = questList.size()-1;
					Quest q = questList.get(index);
					
					if(Main.game.getPlayer().isQuestFailed(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + PresetColour.GENERIC_TERRIBLE.getShades()[1] + ";'>"
									+ "失败 - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDivFailed(Main.game.getPlayer().getQuestsFailed().get(questLine)));
//						journalSB.append(getQuestBoxDiv(q, true)); // Do not append, as this was the failed Quest
						
					} else if(Main.game.getPlayer().isQuestCompleted(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>"
									+ "完成 - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, true));
						
					} else{
						journalSB.append(
								"<details open>"
									+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, false));
					}
					
					index--;
						
					while(index>=0) {
						q = questList.get(index);
						journalSB.append(getQuestBoxDiv(q, true));
						index--;
					}
	
					journalSB.append("</details>");
				}
			}
			
			if(!sideQuestsFound) {
				journalSB.append("<div class='subTitle'>你还没有接受任何支线任务！</div>");
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("主线任务", "查看主线任务进度。", PLANNER_MAIN);
			} else if (index == 2) {
				return new Response("支线任务", "查看支线任务。", null);
			} else if (index == 3) {
				return new Response(
						(Main.game.getPlayer().isRelationshipQuestUpdated()
						?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>浪漫任务</span>"
							:"浪漫任务"),
							"查看浪漫任务。",
						PLANNER_RELATIONSHIP){
					@Override
					public void effects() {
						Main.game.getPlayer().setRelationshipQuestUpdated(false);
					}
				};
			} else if (index == 0) {
				return new Response("返回", "返回到手机主页面。", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode PLANNER_RELATIONSHIP = new DialogueNode("任务栏", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			boolean relationshipQuestFound = false;
			
			// Romance Quests:
			for (QuestLine questLine : Main.game.getPlayer().getQuests().keySet()) {
				if(questLine.getType()==QuestType.RELATIONSHIP) {
					relationshipQuestFound = true;
					
					List<Quest> questList = Main.game.getPlayer().getQuests().get(questLine);
					int index = questList.size()-1;
					Quest q = questList.get(index);
					
					if(Main.game.getPlayer().isQuestFailed(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + PresetColour.GENERIC_TERRIBLE.getShades()[1] + ";'>"
									+ "失败 - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDivFailed(Main.game.getPlayer().getQuestsFailed().get(questLine)));
//						journalSB.append(getQuestBoxDiv(q, true)); // Do not append, as this was the failed Quest
						
					} else if(Main.game.getPlayer().isQuestCompleted(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>"
									+ "完成 - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, true));
						
					} else{
						journalSB.append(
								"<details open>"
									+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, false));
					}
					
					index--;
						
					while(index>=0) {
						q = questList.get(index);
						journalSB.append(getQuestBoxDiv(q, true));
						index--;
					}
	
					journalSB.append("</details>");
				}
			}
			
			if(!relationshipQuestFound) {
				journalSB.append("<div class='subTitle'>你还没有接受任何浪漫任务！</div>");
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("主线任务", "查看主线任务进度。", PLANNER_MAIN);
			} else if (index == 2) {
				return new Response((Main.game.getPlayer().isSideQuestUpdated()
						?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>支线任务</span>"
						:"支线任务"), "查看支线任务。", PLANNER_SIDE){
					@Override
					public void effects() {
						Main.game.getPlayer().setSideQuestUpdated(false);
					}
				};
			} else if (index == 3) {
				return new Response("浪漫任务", "查看浪漫任务。", null);
			} else if (index == 0) {
				return new Response("返回", "返回到手机主页面。", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static String getQuestBoxDivFailed(Quest q) {
		return "<div class='quest-box'>"
				+ getLevelAndExperienceHTML(q, true)
				+ "<h6 style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";text-align:center;'>"
						+ "<b>失败 - "+ q.getName() + "</b>"
				+ "</h6>"
				+ "<p style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
					+ q.getCompletedDescription()
				+ "</p>" 
			+ "</div>";
	}
	
	private static String getQuestBoxDiv(Quest q, boolean completed) {
		if(q==Quest.SIDE_UTIL_COMPLETE) {
			return "";
//			return "<div class='quest-box'>"
//					+ "<h6 style='color:" + q.getQuestType().getColour().getShades()[1] + ";text-align:center;'>"
//							+ "<b>Completed - "+ q.getName() + "</b>"
//					+ "</h6>"
//				+ "</div>";
		}
		
		if(completed) {
			return "<div class='quest-box'>"
					+ getLevelAndExperienceHTML(q, completed)
					+ "<h6 style='color:" + q.getQuestType().getColour().getShades()[1] + ";text-align:center;'>"
							+ "<b>完成 - "+ q.getName() + "</b>"
					+ "</h6>"
					+ "<p style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";text-align:center; margin-top:0;'>"
						+ q.getCompletedDescription()
					+ "</p>" 
				+ "</div>";
			
		} else {
			return "<div class='quest-box'>"
					+ getLevelAndExperienceHTML(q, completed)
					+ "<h6 style='color:" + q.getQuestType().getColour().toWebHexString()+ "; text-align:center; margin-top:0;'>"
						+ "<b>" + q.getName() + "</b>"
					+ "</h6>"
					+ "<p style='text-align:center;'>"
						+ q.getDescription()
					+ "</p>"
				+ "</div>";
		}
	}
	
	private static String getLevelAndExperienceHTML(Quest q, boolean completed) {
		if(q==Quest.SIDE_UTIL_COMPLETE) {
			return "";
		}
		
		if (!completed) {
			if(q.getLevel() <= Main.game.getPlayer().getLevel() - 3) {
				return "<b class='quest-extra level' style='color:"+  PresetColour.GENERIC_GOOD.toWebHexString() + ";'>等级" + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + PresetColour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " 经验</b>";
				
			} else if (q.getLevel() >= Main.game.getPlayer().getLevel() + 3) {
				return "<b class='quest-extra level' style='color:"+  PresetColour.GENERIC_BAD.toWebHexString() + ";'>等级" + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + PresetColour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " 经验</b>";
				
			} else {
				return "<b class='quest-extra level'>等级" + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + PresetColour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " 经验</b>";
			}
			
		} else {
			return "<b class='quest-extra level' style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>等级" + q.getLevel() + "</b>"
					+ "<b class='quest-extra experience' style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>" + q.getExperienceReward() + " 经验</b>";
		}
	}
	

	public static final DialogueNode CHARACTER_APPEARANCE = new DialogueNode("自拍照片", "拍一张自拍", true) {

		@Override
		public String getContent() {
//			return Main.game.getPlayer().getBodyDescription();
			return Main.game.getPlayer().getCharacterInformationScreen(true);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回到手机主界面。", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static String getAttributeDisplayValue(AbstractAttribute att) {
		String valueForDisplay = Units.number(Main.game.getPlayer().getAttributeValue(att));
		
		if(att.isInfiniteAtUpperLimit() && Main.game.getPlayer().getAttributeValue(att)>=att.getUpperLimit()) {
			valueForDisplay = UtilText.getInfinitySymbol(true);
		}
		
		return valueForDisplay;
	}
	
	public static final DialogueNode CHARACTER_STATS = new DialogueNode("角色属性", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			AbstractStatusEffect physiqueSE = PhysiqueLevel.getPhysiqueLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)).getRelatedStatusEffect();
			AbstractStatusEffect arcaneSE = IntelligenceLevel.getIntelligenceLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)).getRelatedStatusEffect();
			AbstractStatusEffect corruptionSE = CorruptionLevel.getCorruptionLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION)).getRelatedStatusEffect();
					
			UtilText.nodeContentSB.append(
				"<div class='container-full-width'>"
					+ statAttributeHeader()
					
					+ "<p style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'><b>核心属性</b></p>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_PHYSIQUE,
							"获得“<b style='color:"+physiqueSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(physiqueSE.getName(Main.game.getPlayer()))+"</b>”状态")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_ARCANE,
							"获得“<b style='color:"+arcaneSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(arcaneSE.getName(Main.game.getPlayer()))+"</b>”状态")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_CORRUPTION,
							"获得“<b style='color:"+corruptionSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(corruptionSE.getName(Main.game.getPlayer()))+"</b>”状态")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.HEALTH_MAXIMUM,
							"当数值减到0，你就会输掉战斗")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MANA_MAXIMUM,
							"用作施法的资源")
//				+"</div>"
//				
//				+"<div class='container-full-width'>"
					+ "<p style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'><b>其他属性</b></p>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.FERTILITY,
							"增加怀孕几率")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.VIRILITY,
							"提升受孕几率")

					+ "<div class='container-full-width' style='text-align:center; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>"
						+ "<b style='color:"+PresetColour.BASE_PINK_LIGHT.toWebHexString()+";'>受孕率计算：</b>"
								+ "<br/>"
								+ "<i>"+GameCharacter.PREGNANCY_CALCULATION+"</i>"
					+ "</div>"
//				+"</div>"
//					
//				+"<div class='container-full-width'>"
					+ "<p style='color:"+PresetColour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'><b>战斗属性</b></p>"
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.CRITICAL_DAMAGE,
							"造成<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.CRITICAL_DAMAGE))+"%</b>普通伤害",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.ENERGY_SHIELDING,
							"<b>"+getAttributeDisplayValue(Attribute.ENERGY_SHIELDING)+"</b>生命护盾/回合",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.SPELL_COST_MODIFIER,
							"法术消耗降低<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.SPELL_COST_MODIFIER))+"%</b>")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_SPELLS,
							"法术伤害增加<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_SPELLS))+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_UNARMED,
							"徒手伤害增加<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_UNARMED))+"%</b>",
							true)
					

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_MELEE_WEAPON,
							"近战伤害增加<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_MELEE_WEAPON))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_RANGED_WEAPON,
							"远程伤害增加<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_RANGED_WEAPON))+"%</b>",
							true)
					
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_PHYSICAL,
							"物理伤害增加<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_PHYSICAL))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_PHYSICAL,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_PHYSICAL)+"</b>物理护盾/每回合",
							true)
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_FIRE,
							"火焰伤害增加<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_FIRE))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_FIRE,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_FIRE)+"</b>火焰护盾/每回合",
							true)
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_ICE,
							"寒冰伤害增加<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_ICE))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_ICE,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_ICE)+"</b>寒冰护盾/每回合",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_POISON,
							"毒素伤害增加<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_POISON))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_POISON,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_POISON)+"</b>毒素护盾/每回合",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_LUST,
							"性欲伤害增加<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LUST))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_LUST,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_LUST)+"</b>性欲护盾/每回合",
							true)

//				+"</div>"
//				+"<div class='container-full-width'>"
					+ "<p style='color:"+PresetColour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'><b>种族特化增伤属性</b></p>");
			
			List<AbstractRace> raceListSorted = new ArrayList<>(Attribute.racialAttributes.keySet());
			raceListSorted.sort((r1, r2) -> r1.getName(true).compareTo(r2.getName(true)));
			
			for(AbstractRace race : raceListSorted) {
				AbstractAttribute attribute = Attribute.racialAttributes.get(race);
				int damageModifier = (int) Main.game.getPlayer().getAttributeValue(attribute);
				if(race==Race.DEMON) {
					// DEMON is split in IMP, DEMON, LILIN, and ELDER_LILIN damage
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_IMP,
									"对小恶魔的伤害增加<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_IMP))+"%</b>",
									true));
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), attribute,
									"对"+race.getNamePlural(true)+"的伤害增加<b>"+Units.number(damageModifier)+"%</b>",
									true));
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_LILIN,
									"对莉琳的伤害增加<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LILIN))+"%</b>",
									true));
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_ELDER_LILIN,
									"对莉琳长老的伤害增加<b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_ELDER_LILIN))+"%</b>",
									true));
				} else {
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), attribute,
									"对"+race.getNamePlural(true)+"的伤害增加<b>"+Units.number(damageModifier)+"%</b>",
									true));
				}
			}
			
//			List<AbstractAttribute> encounteredAttributes = new ArrayList<>();
//			for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
//				AbstractAttribute damageModifier = subspecies.getDamageMultiplier();
//				if(!encounteredAttributes.contains(damageModifier)) {
//					UtilText.nodeContentSB.append(
//							getAttributeBox(Main.game.getPlayer(), damageModifier,
//									"Increases damage vs "+subspecies.getNamePlural(null)+" by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(damageModifier))+"%</b>",
//									true));
//					encounteredAttributes.add(damageModifier);
//				}
//			}
			
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("核心属性", "详细了解你的核心属性。", null);
			
			} else if (index == 2) {
				return new Response("身体属性", "详细了解你身体的各项数据。", CHARACTER_STATS_BODY);
			
			} else if (index == 3) {
				return new Response("性状态", "详细了解你的性状态", CHARACTER_STATS_SEX);
			
			} else if (index == 4) {
				return new Response("怀孕状态", "详细了解你的怀孕状态", CHARACTER_STATS_PREGNANCY);
			
			} else if (index == 0) {
				return new Response("返回", "返回到手机主界面。", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static String getBodyStatsPanel(GameCharacter character) {
		boolean knowsNipples = character.isAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer());
		boolean knowsCrotchNipples = character.isAreaKnownByCharacter(CoverableArea.NIPPLES_CROTCH, Main.game.getPlayer());
		boolean knowsPenis = character.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer());
		boolean knowsVagina = character.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer());
		boolean knowsAnus = character.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer());
		boolean knowsTussy = character.isAreaKnownByCharacter(CoverableArea.TUSSY, Main.game.getPlayer());
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='margin-bottom:0;'>");
		rowCount = 0;
		
		sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "身体属性")
				+ statHeader()
				+ statRow(PresetColour.ANDROGYNOUS, "女性化程度",
						PresetColour.TEXT, String.valueOf(character.getFemininityValue()),
						character.getFemininity().getColour(), Util.capitaliseSentence(character.getFemininity().getName(false)))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "身高",
						PresetColour.TEXT, Units.size(character.getHeightValue()),
						character.getHeight().getColour(), Util.capitaliseSentence(character.getHeight().getDescriptor()))
				+ statRow(PresetColour.MUSCLE_THREE, "肌肉量",
						PresetColour.TEXT, String.valueOf(character.getMuscleValue()),
						character.getMuscle().getColour(), Util.capitaliseSentence(character.getMuscle().getName(false)))
				+ statRow(PresetColour.BODY_SIZE_THREE, "体型",
						PresetColour.TEXT, String.valueOf(character.getBodySizeValue()),
						character.getBodySize().getColour(), Util.capitaliseSentence(character.getBodySize().getName(false)))
				+ statRow(character.getBodyShape().toWebHexStringColour(), "身体形态",
						PresetColour.TEXT,
						"<b style='color:"+character.getMuscle().getColour().toWebHexString()+";'>"+character.getMuscleValue()+"</b>"
								+ "<b>|</b><b style='color:"+character.getBodySize().getColour().toWebHexString()+";'>"+character.getBodySizeValue()+"</b>",
						character.getBodyShape().toWebHexStringColour(), Util.capitaliseSentence(character.getBodyShape().getName(false))));
		
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		sb.append(statRowHeader(PresetColour.TRANSFORMATION_GREATER, "头部和喉咙属性")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "头发长度",
						PresetColour.TEXT, Units.size(character.getHairRawLengthValue()),
						character.getHairLength().getColour(), Util.capitaliseSentence(character.getHairLength().getDescriptor()))
				+ (character.hasHorns()
						?statRow(PresetColour.TRANSFORMATION_GENERIC, "角长度",
							PresetColour.TEXT, Units.size(character.getHornLengthValue()),
							character.getHornLength().getColour(), Util.capitaliseSentence(character.getHornLength().getDescriptor()))
						:statRow(PresetColour.TRANSFORMATION_GENERIC, "角长度",
								PresetColour.TEXT, Units.size(0),
								PresetColour.TEXT_GREY, "N/A"))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "舌头长度",
						PresetColour.TEXT, Units.size(character.getTongueLengthValue()),
						PresetColour.TRANSFORMATION_GENERIC, Util.capitaliseSentence(character.getTongueLength().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "喉咙湿润度",
						PresetColour.TEXT, String.valueOf(character.getFaceWetness().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceWetness().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "喉咙直径",
						PresetColour.TEXT, Units.size(character.getFaceRawCapacityValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceCapacity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "喉咙深度",
						PresetColour.TEXT, String.valueOf(character.getFaceDepth().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceDepth().getDescriptor()) + " ("+(Math.round(character.getFaceDepth().getDepthPercentage()*100))+"%)")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "喉咙舒适深度极限",
						PresetColour.GENERIC_MINOR_GOOD, Units.size(character.getFaceMaximumPenetrationDepthComfortable()),
						PresetColour.TEXT, "N/A")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "喉咙不舒适深度极限",
						PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"没有限制":Units.size(character.getFaceMaximumPenetrationDepthUncomfortable()),
						PresetColour.TEXT, "N/A")
					:"")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "喉咙弹性等级",
						PresetColour.TEXT, String.valueOf(character.getFaceElasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceElasticity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "喉咙可塑性等级",
						PresetColour.TEXT, String.valueOf(character.getFacePlasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFacePlasticity().getDescriptor())));
				
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(character.hasNipples()) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "乳房属性")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "罩杯大小",
							PresetColour.TEXT, String.valueOf(character.getBreastRawSizeValue()),
							PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getBreastSize().getCupSizeName()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "数量",
							PresetColour.TEXT, String.valueOf(character.getBreastRows()),
							PresetColour.GENERIC_SEX, Util.capitaliseSentence(Util.capitaliseSentence(Util.intToString(character.getBreastRows()))+"对"))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "乳汁储量",
							PresetColour.TEXT, !knowsNipples?"未知":Units.fluid(character.getBreastRawMilkStorageValue()),
							PresetColour.GENERIC_SEX, !knowsNipples?"未知":Util.capitaliseSentence(character.getBreastMilkStorage().getDescriptor()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "乳汁再生速度(每乳房)",
							PresetColour.TEXT, !knowsNipples?"未知":Units.fluid(character.getLactationRegenerationPerSecond(false)*60)+"/分钟",
							PresetColour.GENERIC_SEX, !knowsNipples?"未知":Util.capitaliseSentence(character.getBreastLactationRegeneration().getName()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "总乳汁再生速度",
							PresetColour.TEXT, !knowsNipples?"未知":Units.fluid(character.getLactationRegenerationPerSecond(true)*60)+"/分钟",
							PresetColour.GENERIC_SEX, !knowsNipples?"未知":Util.capitaliseSentence(character.getBreastLactationRegeneration().getName()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "直径",
							PresetColour.TEXT, !knowsNipples?"未知":Units.size(character.getNippleRawCapacityValue()),
							PresetColour.GENERIC_SEX, !knowsNipples?"未知":Util.capitaliseSentence(character.getNippleCapacity().getDescriptor()))
					+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleRawCapacityValue()>0
						?statRow(PresetColour.TRANSFORMATION_GENERIC, "乳头深度等级",
							PresetColour.TEXT, String.valueOf(character.getNippleDepth().getValue()),
							PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getNippleDepth().getDescriptor()) + " ("+(Math.round(character.getNippleDepth().getDepthPercentage()*100))+"%)")
						:"")
					+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleRawCapacityValue()>0
						?statRow(PresetColour.TRANSFORMATION_GENERIC, "乳头舒适深度极限",
							PresetColour.GENERIC_MINOR_GOOD, (!knowsNipples?"未知":Units.size(character.getNippleMaximumPenetrationDepthComfortable())),
							PresetColour.TEXT, "N/A")
						:"")
					+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleRawCapacityValue()>0
						?statRow(PresetColour.TRANSFORMATION_GENERIC, "乳头不舒适深度极限",
							PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"No limit":(!knowsNipples?"未知":Units.size(character.getNippleMaximumPenetrationDepthUncomfortable())),
							PresetColour.TEXT, "N/A")
						:"")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "弹性等级",
							PresetColour.TEXT, !knowsNipples?"未知":String.valueOf(character.getNippleElasticity().getValue()),
							PresetColour.GENERIC_SEX, !knowsNipples?"未知":Util.capitaliseSentence(character.getNippleElasticity().getDescriptor()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "可塑性等级",
							PresetColour.TEXT, !knowsNipples?"未知":String.valueOf(character.getNipplePlasticity().getValue()),
							PresetColour.GENERIC_SEX, !knowsNipples?"未知":Util.capitaliseSentence(character.getNipplePlasticity().getDescriptor())));
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "胸部属性")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull]没有胸部……")));
		}
		
		if(character.hasBreastsCrotch()) {
			sb.append("<span style='height:16px;width:100%;float:left;'></span>");
			rowCount = 0;
			
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, (character.getBreastCrotchShape()==BreastShape.UDDERS?"腹乳":"胯乳"))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "大小",
								PresetColour.TEXT, !character.isBreastsCrotchVisibleThroughClothing()&&!knowsCrotchNipples?"未知":String.valueOf(character.getBreastCrotchRawSizeValue()),
								PresetColour.GENERIC_SEX,
								!character.isBreastsCrotchVisibleThroughClothing()&&!knowsCrotchNipples
									?"未知"
									:(character.getBreastCrotchShape()==BreastShape.UDDERS
										?Util.capitaliseSentence(character.getBreastCrotchSize().getDescriptor())
										:Util.capitaliseSentence(character.getBreastCrotchSize().getCupSizeName())))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "数量",
								PresetColour.TEXT, !knowsCrotchNipples?"未知":String.valueOf(character.getBreastCrotchRows()),
								PresetColour.GENERIC_SEX,
								!knowsCrotchNipples
									?"未知"
									:(character.getBreastCrotchRows()==0
										?"单个腹乳"
										:Util.capitaliseSentence(Util.intToString(character.getBreastCrotchRows()))+"对"))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "乳汁储量",
								PresetColour.TEXT, !knowsCrotchNipples?"未知":Units.fluid(character.getBreastCrotchRawMilkStorageValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"未知":Util.capitaliseSentence(character.getBreastCrotchMilkStorage().getDescriptor()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "乳汁再生速度(每胯乳)",
								PresetColour.TEXT, !knowsCrotchNipples?"未知":Units.fluid(character.getCrotchLactationRegenerationPerSecond(false)*60)+"/分钟",
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"未知":Util.capitaliseSentence(character.getBreastCrotchLactationRegeneration().getName()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "总乳汁再生速度",
								PresetColour.TEXT, !knowsCrotchNipples?"未知":Units.fluid(character.getCrotchLactationRegenerationPerSecond(true)*60)+"/分钟",
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"未知":Util.capitaliseSentence(character.getBreastCrotchLactationRegeneration().getName()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "直径",
								PresetColour.TEXT, !knowsCrotchNipples?"未知":String.valueOf(character.getNippleCrotchRawCapacityValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"未知":Util.capitaliseSentence(character.getNippleCrotchCapacity().getDescriptor()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "乳头深度等级",
								PresetColour.TEXT, String.valueOf(character.getNippleCrotchDepth().getValue()),
								PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getNippleCrotchDepth().getDescriptor()) + " ("+(Math.round(character.getNippleCrotchDepth().getDepthPercentage()*100))+"%)")
						+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleCrotchRawCapacityValue()>0
							?statRow(PresetColour.TRANSFORMATION_GENERIC, "乳头舒适深度极限",
								PresetColour.GENERIC_MINOR_GOOD, (!knowsCrotchNipples?"未知":Units.size(character.getNippleCrotchMaximumPenetrationDepthComfortable())),
								PresetColour.TEXT, "N/A")
							:"")
						+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleCrotchRawCapacityValue()>0
							?statRow(PresetColour.TRANSFORMATION_GENERIC, "乳头不舒适深度极限",
								PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"No limit":(!knowsCrotchNipples?"未知":Units.size(character.getNippleCrotchMaximumPenetrationDepthUncomfortable())),
								PresetColour.TEXT, "N/A")
							:"")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "弹性等级",
								PresetColour.TEXT, !knowsCrotchNipples?"未知":String.valueOf(character.getNippleCrotchElasticity().getValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"未知":Util.capitaliseSentence(character.getNippleCrotchElasticity().getDescriptor()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "可塑性等级",
								PresetColour.TEXT, !knowsCrotchNipples?"未知":String.valueOf(character.getNippleCrotchPlasticity().getValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"未知":Util.capitaliseSentence(character.getNippleCrotchPlasticity().getDescriptor())));
		}

		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(character.hasTail()) {
			if(Main.game.isPenetrationLimitationsEnabled()) {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "尾巴属性")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "长度 | 能用于插入的长度",
							PresetColour.TEXT, Units.size(character.getTailLength(false))+" | "+Units.size(character.getTailLength(true)),
							PresetColour.GENERIC_SEX, "N/A"));
			} else {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "尾巴属性")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "长度",
							PresetColour.TEXT, Units.size(character.getTailLength(false)),
							PresetColour.GENERIC_SEX, "N/A"));
			}
			sb.append(
					statRow(PresetColour.TRANSFORMATION_GENERIC,
						"沿长度的直径分布",
						PresetColour.TEXT,
						"[style.colourSize4("+Units.size(character.getTailDiameter(0))+")]"
							+"<br/>[style.colourSize3("+Units.size(character.getTailDiameter(character.getTailLength(false)*0.33f))+")]"
							+"<br/>[style.colourSize2("+Units.size(character.getTailDiameter(character.getTailLength(false)*0.66f))+")]"
							+"<br/>[style.colourSize1("+Units.size(character.getTailDiameter(character.getTailLength(false)))+")]",
						PresetColour.TRANSFORMATION_GENERIC,
						"[style.colourSize4(根部)]"
							+ "<br/>[style.colourSize3(33%)]"
							+ "<br/>[style.colourSize2(66%)]"
							+ "<br/>[style.colourSize1(尖端)]")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC,
							"沿长度的周长分布",
							PresetColour.TEXT,
							"[style.colourSize4("+Units.size(character.getTailCircumference(0))+")]"
								+"<br/>[style.colourSize3("+Units.size(character.getTailCircumference(character.getTailLength(false)*0.33f))+")]"
								+"<br/>[style.colourSize2("+Units.size(character.getTailCircumference(character.getTailLength(false)*0.66f))+")]"
								+"<br/>[style.colourSize1("+Units.size(character.getTailCircumference(character.getTailLength(false)))+")]",
							PresetColour.TRANSFORMATION_GENERIC,
							"[style.colourSize4(根部)]"
								+ "<br/>[style.colourSize3(33%)]"
								+ "<br/>[style.colourSize2(66%)]"
								+ "<br/>[style.colourSize1(尖端)]"));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "尾巴属性")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull]没有尾巴……")));
		}

		sb.append( "<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;

		if(!knowsTussy) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Tussy Attributes")
					+ statRow(PresetColour.TEXT_GREY, "未知！"));

		} else if(character.hasTussy()) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Tussy Attributes")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "湿润度",
					PresetColour.TEXT, String.valueOf(character.getTussyWetness().getValue()),
					PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getTussyWetness().getDescriptor()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "直径",
					PresetColour.TEXT, Units.size(character.getTussyRawCapacityValue()),
					PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getTussyCapacity().getDescriptor()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "深度等级",
					PresetColour.TEXT, String.valueOf(character.getTussyDepth().getValue()),
					PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getTussyDepth().getDescriptor()) + " ("+(Math.round(character.getTussyDepth().getDepthPercentage()*100))+"%)")
					+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "舒适深度极限",
					PresetColour.GENERIC_MINOR_GOOD, (Units.size(character.getTussyMaximumPenetrationDepthComfortable())),
					PresetColour.TEXT, "N/A")
					:"")
					+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "不舒适深度极限",
					PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"No limit":(Units.size(character.getTussyMaximumPenetrationDepthUncomfortable())),
					PresetColour.TEXT, "N/A")
					:"")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "弹性等级",
					PresetColour.TEXT, String.valueOf(character.getTussyElasticity().getValue()),
					PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getTussyElasticity().getDescriptor()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "可塑性等级",
					PresetColour.TEXT, String.valueOf(character.getTussyPlasticity().getValue()),
					PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getTussyPlasticity().getDescriptor())));

		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Tussy Attributes")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull] no tussy... :c")));
		}

		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;

		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(character.hasTentacle()) {
			if(Main.game.isPenetrationLimitationsEnabled()) {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "触手属性")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "长度 | 能用于插入的长度",
							PresetColour.TEXT, Units.size(character.getTentacleLength(false))+" | "+Units.size(character.getTentacleLength(true)),
							PresetColour.GENERIC_SEX, "N/A"));
			} else {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "触手属性")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "长度",
							PresetColour.TEXT, Units.size(character.getTentacleLength(false)),
							PresetColour.GENERIC_SEX, "N/A"));
			}
			sb.append(
					statRow(PresetColour.TRANSFORMATION_GENERIC,
						"沿长度的直径分布",
						PresetColour.TEXT,
						"[style.colourSize4("+Units.size(character.getTentacleDiameter(0))+")]"
							+"<br/>[style.colourSize3("+Units.size(character.getTentacleDiameter(character.getTentacleLength(false)*0.33f))+")]"
							+"<br/>[style.colourSize2("+Units.size(character.getTentacleDiameter(character.getTentacleLength(false)*0.66f))+")]"
							+"<br/>[style.colourSize1("+Units.size(character.getTentacleDiameter(character.getTentacleLength(false)))+")]",
						PresetColour.TRANSFORMATION_GENERIC,
						"[style.colourSize4(根部)]"
							+ "<br/>[style.colourSize3(33%)]"
							+ "<br/>[style.colourSize2(66%)]"
							+ "<br/>[style.colourSize1(尖端)]")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC,
							"沿长度的周长分布",
							PresetColour.TEXT,
							"[style.colourSize4("+Units.size(character.getTentacleCircumference(0))+")]"
								+"<br/>[style.colourSize3("+Units.size(character.getTentacleCircumference(character.getTentacleLength(false)*0.33f))+")]"
								+"<br/>[style.colourSize2("+Units.size(character.getTentacleCircumference(character.getTentacleLength(false)*0.66f))+")]"
								+"<br/>[style.colourSize1("+Units.size(character.getTentacleCircumference(character.getTentacleLength(false)))+")]",
							PresetColour.TRANSFORMATION_GENERIC,
							"[style.colourSize4(根部)]"
								+ "<br/>[style.colourSize3(33%)]"
								+ "<br/>[style.colourSize2(66%)]"
								+ "<br/>[style.colourSize1(尖端)]"));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "触手属性")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull]没有触手……")));
		}
		
		sb.append( "<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(!knowsPenis) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "阴茎属性")
					+ statRow(PresetColour.TEXT_GREY, "未知！"));
			
		} else if(character.hasPenis()) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "阴茎属性")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "长度",
						PresetColour.TEXT, (Units.size(character.getPenisRawSizeValue())),
						PresetColour.GENERIC_SEX, (Util.capitaliseSentence(character.getPenisSize().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "直径|周长",
						PresetColour.TEXT, (Units.size(character.getPenisDiameter())+" | "+Units.size(character.getPenisCircumference())),
						PresetColour.TEXT_GREY, "N/A")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "睾丸大小",
						PresetColour.TEXT, (String.valueOf(character.getTesticleSize().getValue())),
						PresetColour.GENERIC_SEX, (Util.capitaliseSentence(character.getTesticleSize().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "精液储量",
						PresetColour.TEXT, (Units.fluid(character.getPenisRawCumStorageValue())),
						PresetColour.GENERIC_SEX, (Util.capitaliseSentence(character.getPenisCumStorage().getDescriptor())))
				+ (Main.getProperties().hasValue(PropertyValue.cumRegenerationContent) ? statRow(PresetColour.TRANSFORMATION_GENERIC, "精液再生速度",
						PresetColour.TEXT, Units.fluid(character.getCumRegenerationPerSecond()*60)+"/分钟",
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getPenisCumProductionRegeneration().getName()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "射精量 (占精液储量的百分比)",
						PresetColour.TEXT, String.valueOf(character.getPenisRawCumExpulsionValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getPenisCumExpulsion().getDescriptor())) : ""));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "阴茎属性")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull]没有阴茎……")));
		}
			
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(!knowsVagina) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "阴道属性")
					+ statRow(PresetColour.TEXT_GREY, "未知！"));
			
		} else if(character.hasVagina()) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "阴道属性")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "阴蒂大小",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Units.size(character.getVaginaRawClitorisSizeValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaClitorisSize().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "湿润度",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaWetness().getValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaWetness().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "直径",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Units.size(character.getVaginaRawCapacityValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaCapacity().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "深度等级",
						PresetColour.TEXT, String.valueOf(character.getVaginaDepth().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getVaginaDepth().getDescriptor()) + " ("+(Math.round(character.getVaginaDepth().getDepthPercentage()*100))+"%)")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Comfortable Depth Limit",
						PresetColour.GENERIC_MINOR_GOOD, (Units.size(character.getVaginaMaximumPenetrationDepthComfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Uncomfortable Depth Limit",
						PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"无限制":(Units.size(character.getVaginaMaximumPenetrationDepthUncomfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "弹性等级",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaElasticity().getValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaElasticity().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "可塑性等级",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaPlasticity().getValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaPlasticity().getDescriptor()))));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "阴道属性")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull]没有阴道……")));
		}
			
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;

		if(!knowsAnus) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "肛门属性")
					+ statRow(PresetColour.TEXT_GREY, "Unknown!"));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "肛门属性")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Wetness",
						PresetColour.TEXT, String.valueOf(character.getAssWetness().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssWetness().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Capacity",
						PresetColour.TEXT, Units.size(character.getAssRawCapacityValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssCapacity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Depth",
						PresetColour.TEXT, String.valueOf(character.getAssDepth().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssDepth().getDescriptor()) + " ("+(Math.round(character.getAssDepth().getDepthPercentage()*100))+"%)")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "肛门舒适深度极限",
						PresetColour.GENERIC_MINOR_GOOD, (Units.size(character.getAssMaximumPenetrationDepthComfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "肛门不舒适深度极限",
						PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"无限制":(Units.size(character.getAssMaximumPenetrationDepthUncomfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Elasticity",
						PresetColour.TEXT, String.valueOf(character.getAssElasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssElasticity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Plasticity",
						PresetColour.TEXT, String.valueOf(character.getAssPlasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssPlasticity().getDescriptor())));
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode CHARACTER_STATS_BODY = new DialogueNode("身体属性", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			
			UtilText.nodeContentSB.append(
					"<details>"
							+ "<summary style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>腔穴相关机能</summary>"
						
						+ "[style.boldSex(直径:)]腔穴的直径决定了多粗的物体可以被舒服地容纳入腔穴当中。"
						+ "<b>直径越大意味着腔穴能够在不扩张的情况下容纳更粗的物体。</b>"
						+ "<br/>- 阴茎的直径是由“长度”和“周长”值组合计算得出的，附加的修饰特性(如“喇叭形”或“锥形”)会进一步改变最终值。"
						+ "<br/>- 尾巴的直径是根据它的“周长”值和角色的身高来计算的。"
						+ "<br/>- 直径范围从0[units.sizes](极细) 到[units.sizes("+Math.round(Capacity.SEVEN_GAPING.getMaximumValue(false))+")](极粗)。"
						
						+ "<br/><br/>");
			
			if(Main.game.isPenetrationLimitationsEnabled()) {
				UtilText.nodeContentSB.append(
					"[style.boldSex(深度:)]腔穴的深度决定了多长的物体可以被舒适地或不舒适地容纳入腔穴之中。"
						+ "<b>更高的深度等级意味着腔穴在感到不舒适之前可以容纳更长的物体。</b>"
						+ "<br/>- 腔穴的舒适/不舒适深度首先取决于角色的身高，然后被深度等级所修正。"
						+ "<br/>- 性对象如果没有达到“粗暴”的性爱烈度，并且他们拥有不能够完全插入腔穴的物体，那么该物体插入腔穴时将只会停留在最大舒适深度。"
						+ "<br/>- 性对象如果进入“粗暴”的性爱烈度，那么他们将不会遵循停留在最大舒适深度的规则，造成角色不舒服。"
						+ "<br/>- “尺寸为王”性癖会使得插入深度中不舒适深度的部分转化为舒适深度。"
						+ "<br/>- 史莱姆和元素的每一个腔穴的深度等级都将永远保持最大。"
						+ "<br/>- 深度等级的范围从0 ("+OrificeDepth.ZERO_EXTREMELY_SHALLOW.getDescriptor()+") 到7 ("+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+")。"
						
						+ "<br/><br/>");
			}
			
			UtilText.nodeContentSB.append("[style.boldSex(弹性等级:)]一个腔穴的弹性等级决定了这个腔穴有多快被扩张，并且对决定插入腔穴的物体是否过大有影响。"
						+ "如果对象的阴茎尺寸大于你腔穴所能容纳的尺寸，你的腔穴在性爱中就会被扩张，腔穴有<b>更高的弹性等级意味着扩张得会更快</b>。"
						+ "<b>更高的弹性等级同时还能提升腔穴对过大尺寸物体的容忍度，使得腔穴在扩张开始之前就可以接受较大物体的插入</b>。"
						+ "<br/>弹性等级范围从0 (极度抗拒扩张) 到7 (几乎不抗拒扩张)。"
						
						+ "<br/><br/>"
						
					+ "[style.boldSex(可塑性等级:)]一个腔穴的可塑性等级决定了这个腔穴在被扩张之后恢复的速度。"
						+ "如果你的腔穴在性爱中被扩张，<b>高可塑性等级意味着你的腔穴会更难从扩张中恢复，而极高的可塑性等级则意味着你的腔穴几乎不会从扩张中恢复。</b>。"
						+ "<br/>可塑性等级范围从0 (性爱结束后几乎立刻回到原有大小) 到7 (性爱结束之后几乎不会回到原有大小)。"
			+ "</details>"
					
			+ getBodyStatsPanel(Main.game.getPlayer()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("核心状态", "详细了解你的核心状态", CHARACTER_STATS);
			
			} else if (index == 2) {
				return new Response("身体状态", "详细了解你的身体数值", null);
			
			} else if (index == 3) {
				return new Response("性状态", "详细了解你的性状态", CHARACTER_STATS_SEX);
			
			} else if (index == 4) {
				return new Response("怀孕状态", "详细了解你的怀孕状态", CHARACTER_STATS_PREGNANCY);
			
			} else if (index == 0) {
				return new Response("返回", "返回到手机主界面。", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CHARACTER_STATS_SEX = new DialogueNode("性属性", "", true) {

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<div class='container-full-width' style='text-align:center; width:100%; padding:0; margin:4px 0;'>"
						+ "你今天高潮了[style.boldSex("+Main.game.getPlayer().getDaysOrgasmCount()+")]次"
							+"，你的高潮总次数为[style.boldSex("+Main.game.getPlayer().getTotalOrgasmCount()+")]。"
						+ "<br/>"
						+ "你的单日最多高潮次数现在是[style.boldSex("+Main.game.getPlayer().getDaysOrgasmCountRecord()+")]。"
						+ "<br/>"
						+ "你总共与[style.boldSex("+Main.game.getPlayer().getUniqueSexPartnerCount()+")]个对象做过。"
					+ "</div>");
					
			sb.append(sexStatHeader());
			
			boolean oddRow = false;
			
			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "指交",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
							-1,
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
							-1,
							oddRow));
			
			oddRow = !oddRow;
			
			sb.append((Main.game.isAnalContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "肛门指交",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
									-1,
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
									-1,
									oddRow)
							:""));
			
			if(Main.game.isAnalContentEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "口交",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
							oddRow));

			oddRow = !oddRow;
			
			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "舔阴",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
							-1,
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
							-1,
							oddRow));

			oddRow = !oddRow;
			
			sb.append((Main.game.isAnalContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "舔肛",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
									-1,
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
									-1,
									oddRow)
							:""));
			
			if(Main.game.isAnalContentEnabled()) {
				oddRow = !oddRow;
			}

			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "腿交",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
									oddRow));

			oddRow = !oddRow;
					
			sb.append((Main.game.isFootContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "足交",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
									oddRow)
							:""));
			
			if(Main.game.isFootContentEnabled()) {
				oddRow = !oddRow;
			}

			sb.append((Main.game.isArmpitContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "腋交",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
									oddRow)
							:""));
			
			if(Main.game.isArmpitContentEnabled()) {
				oddRow = !oddRow;
			}
					
			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "阴道性交",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
							oddRow));

			oddRow = !oddRow;
			
			sb.append((Main.game.isAnalContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "肛交",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
									oddRow)
							:""));

			if(Main.game.isAnalContentEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append((Main.game.isNipplePenEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "乳头插入",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
									oddRow)
							:""));

			if(Main.game.isNipplePenEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append((Main.game.isNipplePenEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "胯乳乳头插入",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
									oddRow)
							:""));

			if(Main.game.isNipplePenEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append((Main.game.isUrethraEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "阴茎尿道插入",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
									oddRow)
							:""));

			if(Main.game.isUrethraEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append((Main.game.isUrethraEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "阴户尿道插入",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
									oddRow)
							:""));
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("核心状态", "详细了解你的核心状态", CHARACTER_STATS);
			
			} else if (index == 2) {
				return new Response("身体状态", "详细了解你的身体数值", CHARACTER_STATS_BODY);
			
			} else if (index == 3) {
				return new Response("性状态", "详细了解你的性状态", null);
			
			} else if (index == 4) {
				return new Response("怀孕状态", "详细了解你的怀孕状态", CHARACTER_STATS_PREGNANCY);
			
			} else if (index == 0) {
				return new Response("返回", "返回到手机主界面。", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CHARACTER_STATS_PREGNANCY = new DialogueNode("怀孕状态", "", true) {

		private void OffspringHeaderDisplay(StringBuilder output, String word_one, String word_two, String color, int count) {
			output.append("<div class='extraAttribute-quarter'>");
			output.append(word_one);
			output.append("<br/>");
			output.append("<b style='color:").append(color).append(";'>");
			output.append(word_two);
			output.append("</b>");
			output.append("<br/>");
			output.append(count);
			output.append("</div>");
		}

		private void offspringTableLine(StringBuilder output, offspringTableLineSubject subject, boolean evenRow, boolean includeIncubationColumn) {
			String color = subject.female ? PresetColour.FEMININE.toWebHexString() : PresetColour.MASCULINE.toWebHexString();
			String feralString = "<span style='color:" + RaceStage.FERAL.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(RaceStage.FERAL.getName()) + "</span> ";
			
			String innerEntryStyle = "background:transparent; margin:0; padding:0; width:"+(includeIncubationColumn?"15":"20")+"%;";
			String innerEntryStyle2 = "background:transparent; margin:0; padding:0; width:15%;";
			String innerEntryStyleWide = "background:transparent; margin:0; padding:0; width:25%;";
			
			output.append("<div class='container-full-width' style='margin:0; width:100%; background:"+(evenRow?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");

				output.append("<div class='container-full-width' style='"+innerEntryStyle+"'>");
					output.append("<span style='color:").append(color).append(";'>");
						output.append(subject.child_name);
					output.append("</span>");
				output.append("</div>");
	
				output.append("<div class='container-full-width' style='"+innerEntryStyle2+"'>");
					output.append(subject.is_feral ? feralString : "");
					output.append("<span style='color:").append(subject.race_color).append(";'>");
						output.append(subject.is_feral ? subject.species_name.toLowerCase() : subject.species_name);
					output.append("</span>");
				output.append("</div>");
	
				output.append("<div class='container-full-width' style='"+innerEntryStyle+"'>");
//					output.append("<b>");
						output.append(subject.mother);
//					output.append("</b>");
				output.append("</div>");
	
				output.append("<div class='container-full-width' style='"+innerEntryStyle+"'>");
//					output.append("<b>");
						output.append(subject.father);
//					output.append("</b>");
				output.append("</div>");
	
				if(includeIncubationColumn) {
					output.append("<div class='container-full-width' style='"+innerEntryStyle+"'>");
						output.append(subject.incubator);
					output.append("</div>");
				}
				
				output.append("<div class='container-full-width' style='"+innerEntryStyleWide+"'>");
//					output.append("<b>");
						output.append(Util.stringsToStringList(subject.relationships, false));
//						output.append(
//								isGreyedOut
//									?"[style.colourDisabled("+Util.stringsToStringList(relationships, false)+")]"
//									:Util.stringsToStringList(relationships, false));
//					output.append("</b>");
				output.append("</div>");
			
			output.append("</div>");
		}

		@Override
		public String getContent() {
			int sonsBirthed=0;
			int daughtersBirthed=0;
			int sonsFathered=0;
			int daughtersFathered=0;
			int offspringIncubatedCount=0;
			
			List<String> implantedLittersEncountered = new ArrayList<>();
			
			// Birthed with player as the mother:
			for (Litter litter : Main.game.getPlayer().getLittersBirthed()){
				sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
				daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
			}
			// Birthed with player as the father:
			for (Litter litter : Main.game.getPlayer().getLittersFathered()){
				sonsFathered+=(litter.isSelfImpregnation()?0:litter.getSonsFromMother()+litter.getSonsFromFather());
				daughtersFathered+=(litter.isSelfImpregnation()?0:litter.getDaughtersFromMother()+litter.getDaughtersFromFather());
			}
			// Egg-incubated offspring who have been birthed:
			for (Litter litter : Main.game.getPlayer().getLittersIncubated()) {
				implantedLittersEncountered.add(litter.getId());
				for (String id : litter.getOffspring()) {
					if (id.contains("NPCOffspring")) {
						//NPCOffspring is always born
						offspringIncubatedCount += 1;
						sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
						daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
					} else {
						try {
							OffspringSeed o = Main.game.getOffspringSeedById(id);
							//OffspringSeed may be born or unborn
							if (o.isBorn()) {
								offspringIncubatedCount += 1;
								sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
								daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
			// Egg-implanted offspring who have been birthed:
			for (Litter litter : Main.game.getPlayer().getLittersImplanted()) {
				if(!implantedLittersEncountered.contains(litter.getId())) {
					for (String id : litter.getOffspring()) {
						if (id.contains("NPCOffspring")) {
							//NPCOffspring is always born
							offspringIncubatedCount += 1;
							sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
							daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
						} else {
							try {
								OffspringSeed o = Main.game.getOffspringSeedById(id);
								//OffspringSeed may be born or unborn
								if (o.isBorn()) {
									offspringIncubatedCount += 1;
									sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
									daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}

			UtilText.nodeContentSB.setLength(0);

			OffspringHeaderDisplay(UtilText.nodeContentSB, "身为母亲拥有的", "儿子", PresetColour.MASCULINE.toWebHexString(), sonsBirthed);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "身为母亲拥有的", "女儿", PresetColour.FEMININE.toWebHexString(), daughtersBirthed);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "身为父亲拥有的", "儿子", PresetColour.MASCULINE.toWebHexString(), sonsFathered);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "作为父亲拥有的", "女儿", PresetColour.FEMININE.toWebHexString(), daughtersFathered);

			int childrenMet = Main.game.getOffspring().size();
			int totalChildren = (sonsBirthed+daughtersBirthed+sonsFathered+daughtersFathered+offspringIncubatedCount);
			int percentageMet = totalChildren == 0 ? 100 : (100 * childrenMet / totalChildren);
			
			UtilText.nodeContentSB.append(
					"<div class='subTitle'>后代总数："+ totalChildren+" (见过的孩子："+ percentageMet +"%)</div>"
					
					+ "<span style='height:16px;width:100%;float:left;'></span>"
					
					+ pregnancyDetails()

					+ "<span style='height:16px;width:100%;float:left;'></span>");
			
			UtilText.nodeContentSB.append(
					"<div class='subTitle'>后代列表</div>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div class='container-full-width'style='float:left; margin:0; width:100%; font-weight:bold;'>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:20%;'>"
								+ "名字"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:15%;'>"
								+ "种族"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:20%;'>"
								+ "母亲"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:20%;'>"
								+ "父亲"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:25%;'>"
								+ "与你的关系"
							+ "</div>"
						+ "</div>");
			
			int rowCount = 0;
			List<NPC> offspringMet= new ArrayList<>(Main.game.getOffspring());
			offspringMet.removeIf(npc -> npc.getIncubator()!=null && npc.getIncubator().isPlayer()); // Only non-egg incubated offspring
			List<OffspringSeed> offspringUnknown = new ArrayList<>(Main.game.getOffspringNotSpawned(os->true));
			if(offspringMet.isEmpty() && offspringUnknown.isEmpty()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='margin:0; padding:0; width:100%;float:left;'>"
												+ "[style.italicsDisabled(没有后代……)]"
											+ "</div>");
			} else {
				offspringMet.sort(Comparator.comparing(GameCharacter::getBirthday));
				for(NPC npc : offspringMet) {
					offspringTableLineSubject subject = new offspringTableLineSubject(npc);
					offspringTableLine(UtilText.nodeContentSB, subject, rowCount % 2 == 0, false);
					rowCount++;
				}

				offspringUnknown.sort(Comparator.comparing(OffspringSeed::getConceptionDate));
				for(OffspringSeed os : offspringUnknown) {
					offspringTableLineSubject subject = new offspringTableLineSubject(os);
					offspringTableLine(UtilText.nodeContentSB, subject, rowCount%2==0, false);
					rowCount++;
				}
			}

			UtilText.nodeContentSB.append("</div>");
			
			UtilText.nodeContentSB.append("<span style='height:16px;width:100%;float:left;'></span>");
			
			UtilText.nodeContentSB.append(
					"<div class='subTitle'>孵化而来的后代列表</div>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div class='container-full-width'style='float:left; margin:0; width:100%; font-weight:bold;'>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:15%;'>"
								+ "名字"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:15%;'>"
								+ "种族"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:15%;'>"
								+ "母亲"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:15%;'>"
								+ "父亲"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:15%;'>"
								+ "孵化于"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:25%;'>"
								+ "与你的关系"
							+ "</div>"
						+ "</div>");
			
			rowCount = 0;
			List<NPC> offspringIncubated = new ArrayList<>(Main.game.getOffspring());
			offspringIncubated.removeIf(npc -> npc.getIncubator()==null || !npc.getIncubator().isPlayer()); // Only egg incubated offspring
			offspringIncubated.removeAll(offspringMet);
			List<OffspringSeed> offspringIncubatedUnknown = new ArrayList<>(Main.game.getOffspringNotSpawned(os->true,true));
			offspringIncubatedUnknown.removeIf(os -> os.getIncubator()==null || !os.getIncubator().isPlayer()); // Only egg incubated offspring
			if(offspringIncubated.isEmpty() && offspringIncubatedUnknown.isEmpty()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='float:left; margin:0; width:100%;'>"
												+ "[style.italicsDisabled(没有孵化的后代……)]"
											+ "</div>");
			} else {
				offspringIncubated.sort(Comparator.comparing(GameCharacter::getBirthday));
				for(NPC npc : offspringIncubated) {
					offspringTableLineSubject subject = new offspringTableLineSubject(npc);
					offspringTableLine(UtilText.nodeContentSB, subject, rowCount%2==0, true);
					rowCount++;
				}
				offspringIncubatedUnknown.sort(Comparator.comparing(OffspringSeed::getConceptionDate));
				for(OffspringSeed os : offspringIncubatedUnknown) {
					offspringTableLineSubject subject = new offspringTableLineSubject(os);
					offspringTableLine(UtilText.nodeContentSB, subject, rowCount%2==0, true);
					rowCount++;
				}
			}
			
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("核心状态", "详细了解你的核心状态", CHARACTER_STATS);
			
			} else if (index == 2) {
				return new Response("身体状态", "详细了解你的身体数值。", CHARACTER_STATS_BODY);
			
			} else if (index == 3) {
				return new Response("性状态", "详细了解你的性状态", CHARACTER_STATS_SEX);
			
			} else if (index == 4) {
				return new Response("怀孕状态", "详细了解你的怀孕状态", null);
			
			} else if (index == 0) {
				return new Response("返回", "返回到手机主界面。", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	private static String sexStatHeader() {
		return "<div class='container-full-width' style='width:100%; padding:0; margin:4px 0; font-weight:bold; text-align:center;'>"
					+ "<div class='container-full-width' style='width:33.3%; padding:0; margin:0;'>"
						+ "类型"
					+ "</div>"
					+ "<div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>"
						+ "提供"
					+ "</div>"
					+ "<div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>"
						+ "内射<br/>给出"
					+ "</div>"
					+ "<div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>"
						+ "接受"
					+ "</div>"
					+ "<div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>"
						+ "内射<br/>接受"
					+ "</div>"
				+ "</div>";
	}
	
	private static String sexStatRow(Colour colour, String name, int given, int loadsGiven, int received, int loadsReceived, boolean light) {
		return 
//				"<div class='container-full-width' style='width:100%; padding:0; margin:4px 0; text-align:center;'>"
				"<div class='container-full-width inner' style='text-align:center; margin-bottom:0;"+(light?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
					+ "<div style='float:left; width:33.3%; padding:0; margin:0;'>"
						+ "<span style='color:" + colour.toWebHexString() + ";'>" + name + "</span>"
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ given
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ (loadsGiven < 0 ? "<span class='option-disabled'>-</span>" : loadsGiven)
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ received
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ (loadsReceived < 0 ? "<span class='option-disabled'>-</span>" : loadsReceived) 
					+ "</div>"
				+ "</div>";
	}
	
	private static String pregnancyRow(String topLeft, String bottomLeft, String topRight, String bottomRight) {
		StringBuilder contentSB = new StringBuilder();
		
		contentSB.append("<div class='container-full-width' style='text-align:center; margin-top:0; margin-bottom:4px;'>");
			contentSB.append("<div class='container-full-width' style='float:left; margin:0; width:25%; background:transparent;'>");
				contentSB.append("<div class='container-full-width' style='float:left; margin:0; padding:0; width:100%; background:transparent;'>");
					contentSB.append(topLeft);
				contentSB.append("</div>");
				contentSB.append("<div class='container-full-width' style='float:left; margin:0; padding:0; width:100%; background:transparent; white-space:nowrap;'>");
					contentSB.append(bottomLeft);
				contentSB.append("</div>");
			contentSB.append("</div>");
	
			contentSB.append("<div class='container-full-width' style='float:left; margin:0; width:75%; background:transparent;'>");
				contentSB.append("<div class='container-full-width' style='float:left; margin:0; padding:0; width:100%; background:transparent;'>");
					contentSB.append(topRight);
				contentSB.append("</div>");
				contentSB.append("<div class='container-full-width' style='float:left; margin:0; padding:0; width:100%; background:transparent;'>");
					contentSB.append(bottomRight);
				contentSB.append("</div>");
			contentSB.append("</div>");
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String pregnancyDetails() {
		StringBuilder contentSB = new StringBuilder();

		// Mothered children:
		
		boolean noPregnancies=true;

		contentSB.append("<div class='subTitle'>作为母亲或孵化而来的后代</div>");
		
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_0)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_1)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_2)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)){
			
			StringBuilder possibleFathersSB = new StringBuilder();
			int potentialFatherCount = 0;
			for(PregnancyPossibility pp : new ArrayList<>(Main.game.getPlayer().getPotentialPartnersAsMother())){
				if(pp.getFather()!=null) {
					if(potentialFatherCount>0) {
						possibleFathersSB.append("<br/>");
					}
					possibleFathersSB.append(UtilText.parse(pp.getFather(),
							"[npc.Name(A)] ("
								+ (!pp.getFather().getRaceStage().getName().isEmpty()
										?"<span style='color:"+pp.getFather().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(pp.getFather().getRaceStage().getName())+"</span> "
										:"")
								+ "<span style='color:"+pp.getFather().getRace().getColour().toWebHexString()+";'>"
								+ (pp.getFather().getGender().isFeminine()
										?Util.capitaliseSentence(pp.getFather().getSubspecies().getSingularFemaleName(pp.getFather().getBody()))
										:Util.capitaliseSentence(pp.getFather().getSubspecies().getSingularMaleName(pp.getFather().getBody())))
								+ "</span>)概率："));
					
					if (pp.getProbability() <= 0) {
						possibleFathersSB.append("[style.italicsTerrible(无)]");
					} else if(pp.getProbability()<=0.15f) {
						possibleFathersSB.append("[style.italicsBad(低)]");
					} else if(pp.getProbability()<=0.3f) {
						possibleFathersSB.append("[style.italicsMinorGood(平均)]");
					} else if(pp.getProbability()<1) {
						possibleFathersSB.append("[style.italicsGood(高)]");
					} else {
						possibleFathersSB.append("[style.italicsExcellent(必然)]");
					}
					
					possibleFathersSB.append("</b>");
					potentialFatherCount++;
				}
			}
			
			String stage = "";
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_0)) {
				stage = StatusEffect.PREGNANT_0.getName(Main.game.getPlayer());
			} else if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_1)) {
				stage = StatusEffect.PREGNANT_1.getName(Main.game.getPlayer());
			} else if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_2)) {
				stage = StatusEffect.PREGNANT_2.getName(Main.game.getPlayer());
			} else if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)) {
				stage = StatusEffect.PREGNANT_3.getName(Main.game.getPlayer());
			}
			
			contentSB.append(pregnancyRow("[style.boldBad(正在进行的怀孕)]", "[style.italicsSex('"+Util.capitaliseSentence(stage)+"')]", "<b>可能的父亲：</b>", possibleFathersSB.toString()));
			
			noPregnancies=false;
		}
		
		Map<SexAreaOrifice, List<AbstractStatusEffect>> incubationEffectMap = Util.newHashMapOfValues(
				new Value<>(SexAreaOrifice.VAGINA, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_WOMB_1, StatusEffect.INCUBATING_EGGS_WOMB_2, StatusEffect.INCUBATING_EGGS_WOMB_3)),
				new Value<>(SexAreaOrifice.ANUS, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_STOMACH_1, StatusEffect.INCUBATING_EGGS_STOMACH_2, StatusEffect.INCUBATING_EGGS_STOMACH_3)),
				new Value<>(SexAreaOrifice.NIPPLE, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_NIPPLES_1, StatusEffect.INCUBATING_EGGS_NIPPLES_2, StatusEffect.INCUBATING_EGGS_NIPPLES_3)),
				new Value<>(SexAreaOrifice.NIPPLE_CROTCH, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_1, StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_2, StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_3)),
				new Value<>(SexAreaOrifice.SPINNERET, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_SPINNERET_1, StatusEffect.INCUBATING_EGGS_SPINNERET_2, StatusEffect.INCUBATING_EGGS_SPINNERET_3)));
		
		for(Entry<SexAreaOrifice, List<AbstractStatusEffect>> incubationEntry : incubationEffectMap.entrySet()) {
			if(Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(0))
					|| Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(1))
					|| Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(2))) {
				String stage = "";
				if(Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(0))) {
					stage = incubationEntry.getValue().get(0).getName(Main.game.getPlayer());
				} else if(Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(1))) {
					stage = incubationEntry.getValue().get(1).getName(Main.game.getPlayer());
				} else if(Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(2))) {
					stage = incubationEntry.getValue().get(2).getName(Main.game.getPlayer());
				}
				Litter litter = Main.game.getPlayer().getIncubationLitter(incubationEntry.getKey());
				contentSB.append(pregnancyRow("[style.boldBad(正在进行的)][style.boldPurple(孵化)]",
						(litter.getMother()!=null
							?(litter.getMother().isPlayer()
								?"授卵者：[style.colourExcellent(你自己)]"
								:UtilText.parse(litter.getMother(), "授卵者：<span style='color:"+litter.getMother().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"授卵者：[style.colourDisabled(未知)]"),
						"授卵于"
							+(incubationEntry.getKey()==SexAreaOrifice.VAGINA
								?"子宫"
								:(incubationEntry.getKey()==SexAreaOrifice.ANUS || incubationEntry.getKey()==SexAreaOrifice.MOUTH
									?"腹部"
									:incubationEntry.getKey().getName(Main.game.getPlayer(), true)))
							+": "
							+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG),
						"[style.italicsSex('"+Util.capitaliseSentence(stage)+"')]"));
				noPregnancies=false;
			}
		}
		
		
		// Birthed:
		
		if(!Main.game.getPlayer().getLittersBirthed().isEmpty()) {
			for(Litter litter : Main.game.getPlayer().getLittersBirthed()) {
				String unknownName = "[style.colourDisabled(未知)]";
				try {
					String offspring0 = litter.getOffspring().iterator().next();
					if(offspring0.contains("NPCOffspring")) {
						GameCharacter c = Main.game.getNPCById(offspring0);
						if(!c.getFatherName().equals("???")) {
							unknownName = "<span style='color:"+c.getFatherFemininity().getColour().toWebHexString()+";'>"+c.getFatherName()+"</span>";
						}
					} else {
						OffspringSeed o = Main.game.getOffspringSeedById(offspring0);
						if(!o.getFatherName().equals("???")) {
							unknownName = "<span style='color:"+o.getFatherFemininity().getColour().toWebHexString()+";'>"+o.getFatherName()+"</span>";
						}
					}
				} catch(Exception ex) {
				}
				contentSB.append(pregnancyRow("[style.boldGood(已经结束的怀孕)]",
						(litter.getFather()!=null
							?(litter.getFather().isPlayer()
								?"父亲：[style.colourExcellent(你自己)]"
								:UtilText.parse(litter.getFather(), "父亲：<span style='color:"+litter.getFather().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"父亲："+unknownName),
						"受孕于："
							+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
							+" | 生产于："
							+Units.date(litter.getBirthDate(), Units.DateType.LONG),
						"生育："
							+litter.getBirthedDescription()));
			}
			noPregnancies=false;
		}
		if(!Main.game.getPlayer().getLittersIncubated().isEmpty()) {
			for(Litter litter : Main.game.getPlayer().getLittersIncubated()) {
				contentSB.append(pregnancyRow("[style.boldGood(已经结束的)][style.boldPurple(孵化)]",
						(litter.getMother()!=null
							?(litter.getMother().isPlayer()
								?"授卵者：[style.colourExcellent(你自己)]"
								:UtilText.parse(litter.getMother(), "授卵者：<span style='color:"+litter.getMother().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"授卵者：[style.colourDisabled(未知)]"),
						"授卵于:"
								+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG)
								+" | 生产于："
								+Units.date(litter.getBirthDate(), Units.DateType.LONG),
						"生育："
							+litter.getBirthedDescription()));
			}
			noPregnancies=false;
		}
		
		if(noPregnancies){
			contentSB.append("<div class='subTitle'>"
					+ "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() +
					(Main.game.getPlayer().getLittersImplanted().isEmpty()
					?";'>你还没有怀孕过……</span>"
					:";'>你还没有生育过……</span>")
					+ "</div>");
		}
		
		
		// Fathered:
		noPregnancies=true;
		
		contentSB.append("<span style='height:16px;width:100%;float:left;'></span>");
		
		contentSB.append("<div class='subTitle'>作为父亲或授卵而来的后代</div>");
		
		for(PregnancyPossibility pp : new ArrayList<>(Main.game.getPlayer().getPotentialPartnersAsFather())) {
			if(pp.getMother()!=null) {
				String impregnationChance = "";
				if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_0)) {
					impregnationChance = "Probability of impregnation: ";
					if (pp.getProbability() <= 0) {
						impregnationChance += "[style.italicsTerrible(None)]";
					} else if(pp.getProbability()<=0.15f) {
						impregnationChance += "[style.italicsBad(Low)]";
					} else if(pp.getProbability()<=0.3f) {
						impregnationChance += "[style.italicsMinorGood(Average)]";
					} else if(pp.getProbability()<1) {
						impregnationChance += "[style.italicsGood(High)]";
					} else {
						impregnationChance += "[style.italicsExcellent(Certainty)]";
					}
				}
				
				String stage = "";
				if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_0)) {
					stage = StatusEffect.PREGNANT_0.getName(pp.getMother());
				} else if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_1)) {
					stage = StatusEffect.PREGNANT_1.getName(pp.getMother());
				} else if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_2)) {
					stage = StatusEffect.PREGNANT_2.getName(pp.getMother());
				} else if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_3)) {
					stage = StatusEffect.PREGNANT_3.getName(pp.getMother());
				}
				
				String motherName = UtilText.parse(pp.getMother(), "[npc.Name(A)]")
						+" ("
						+ (!pp.getMother().getRaceStage().getName().isEmpty()
								?"<span style='color:"+pp.getMother().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(pp.getMother().getRaceStage().getName())+"</span> "
								:"")
						+ "<span style='color:"+pp.getMother().getRace().getColour().toWebHexString()+";'>"
						+ (pp.getMother().getGender().isFeminine()
								?Util.capitaliseSentence(pp.getMother().getSubspecies().getSingularFemaleName(pp.getMother().getBody()))
								:Util.capitaliseSentence(pp.getMother().getSubspecies().getSingularMaleName(pp.getMother().getBody())))
						+ "</span>)";
				
				contentSB.append(pregnancyRow("[style.boldBad(正在进行的怀孕)]", "[style.italicsSex("+Util.capitaliseSentence(stage)+")]", motherName, impregnationChance));
				
			}
			noPregnancies=false;
		}
		List<Litter> incubatorOngoingLitters = new ArrayList<>(Main.game.getPlayer().getLittersImplanted());
		incubatorOngoingLitters.removeIf(npc -> npc.getIncubator()==null || npc.getIncubator().getIncubatingLitters().isEmpty());
		Set<GameCharacter> incubatorCharacters = new HashSet<>();
		for(Litter litter : incubatorOngoingLitters) {
			incubatorCharacters.add(litter.getIncubator());
		}
		for(GameCharacter incubator : incubatorCharacters) {
			for(Entry<SexAreaOrifice, List<AbstractStatusEffect>> incubationEntry : incubationEffectMap.entrySet()) {
				Litter litter = incubator.getIncubatingLitters().get(incubationEntry.getKey());
				if(litter!=null) {
					if(incubator.hasStatusEffect(incubationEntry.getValue().get(0))
							|| incubator.hasStatusEffect(incubationEntry.getValue().get(1))
							|| incubator.hasStatusEffect(incubationEntry.getValue().get(2))) {
						String stage = "";
						if(incubator.hasStatusEffect(incubationEntry.getValue().get(0))) {
							stage = incubationEntry.getValue().get(0).getName(incubator);
						} else if(incubator.hasStatusEffect(incubationEntry.getValue().get(1))) {
							stage = incubationEntry.getValue().get(1).getName(incubator);
						} else if(incubator.hasStatusEffect(incubationEntry.getValue().get(2))) {
							stage = incubationEntry.getValue().get(2).getName(incubator);
						}
						contentSB.append(pregnancyRow("[style.boldBad(正在进行的)][style.boldPurple(孵化)]",
								UtilText.parse(incubator, "孵化者：<span style='color:"+incubator.getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"),
								"授卵于"
									+(incubationEntry.getKey()==SexAreaOrifice.VAGINA
										?"子宫"
										:(incubationEntry.getKey()==SexAreaOrifice.ANUS || incubationEntry.getKey()==SexAreaOrifice.MOUTH
											?"腹部"
											:incubationEntry.getKey().getName(incubator, true)))
									+": "
									+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG),
								"[style.italicsSex('"+Util.capitaliseSentence(stage)+"')]"));
						noPregnancies=false;
					}
				}
			}
		}
		
		if(!Main.game.getPlayer().getLittersFathered().isEmpty()) {
			for (Litter litter : Main.game.getPlayer().getLittersFathered()) {
				String unknownName = "[style.colourDisabled(未知)]";
				try {
					String offspring0 = litter.getOffspring().iterator().next();
					if(offspring0.contains("NPCOffspring")) {
						GameCharacter c = Main.game.getNPCById(offspring0);
						if(!c.getMotherName().equals("???")) {
							unknownName = "<span style='color:"+c.getMotherFemininity().getColour().toWebHexString()+";'>"+c.getMotherName()+"</span>";
						}
					} else {
						OffspringSeed o = Main.game.getOffspringSeedById(offspring0);
						if(!o.getMotherName().equals("???")) {
							unknownName = "<span style='color:"+o.getMotherFemininity().getColour().toWebHexString()+";'>"+o.getMotherName()+"</span>";
						}
					}
				} catch(Exception ex) {
				}
				
				contentSB.append(pregnancyRow("[style.boldGood(已经结束的怀孕)]",
						(litter.getMother()!=null
							?(litter.getMother().isPlayer()
								?"母亲：[style.colourExcellent(你自己)]"
								:UtilText.parse(litter.getMother(), "母亲：<span style='color:"+litter.getMother().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"母亲："+unknownName),
						"受孕于："
							+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
							+" | 生产于："
							+Units.date(litter.getBirthDate(), Units.DateType.LONG),
						"生育："
							+litter.getBirthedDescription()));
			}
			noPregnancies=false;
		}
		if(!Main.game.getPlayer().getLittersImplanted().isEmpty()) {
			List<Litter> incubatorCompletedLitters = new ArrayList<>(Main.game.getPlayer().getLittersImplanted());
			incubatorCompletedLitters.removeAll(incubatorOngoingLitters);
			for (Litter litter : incubatorCompletedLitters) {
				String unknownName = "[style.colourDisabled(未知)]";
				try {
					String offspring0 = litter.getOffspring().iterator().next();
					if(offspring0.contains("NPCOffspring")) {
						GameCharacter c = Main.game.getNPCById(offspring0);
						if(!c.getIncubatorName().equals("???")) {
							unknownName = "<span style='color:"+c.getIncubatorFemininity().getColour().toWebHexString()+";'>"+c.getIncubatorName()+"</span>";
						}
					} else {
						OffspringSeed o = Main.game.getOffspringSeedById(offspring0);
						if(!o.getIncubatorName().equals("???")) {
							unknownName = "<span style='color:"+o.getIncubatorFemininity().getColour().toWebHexString()+";'>"+o.getIncubatorName()+"</span>";
						}
					}
				} catch(Exception ex) {
				}
				
				contentSB.append(pregnancyRow("[style.boldGood(已经结束的)][style.boldPurple(孵化)]",
						(litter.getIncubator()!=null
							?(litter.getIncubator().isPlayer()
								?"孵化者：[style.colourExcellent(你自己)]"
								:UtilText.parse(litter.getIncubator(), "孵化者:<span style='color:"+litter.getIncubator().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"孵化者:"+unknownName),
						"授卵于:"
							+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG)
							+" | 生产于："
							+Units.date(litter.getBirthDate(), Units.DateType.LONG),
						"生育："
							+litter.getBirthedDescription()));
			}
			noPregnancies=false;
		}
		
		if(noPregnancies){
			contentSB.append("<div class='subTitle'>"
					+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>你还没有令任何人怀孕过！</span>"
					+ "</div>");
		}
		

		return contentSB.toString();
	}
	
	private static String statHeader() {
		return "<div class='container-full-width' style='margin-bottom:0;'>"
					+ "<div style='width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "属性"
					+ "</div>"
					+ "<div style='width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "数据"
					+ "</div>"
					+ "<div style='float:left; width:30%; font-weight:bold; margin:0; padding:0;'>"
						+ "描述"
					+"</div>"
				+ "</div>";
	}

	private static String statRowHeader(Colour colour, String text) {
		return "<h6 style='color:"+colour.toWebHexString()+"; text-align:center; margin-bottom:0; padding-bottom:0;'>"+text+"</h6>";
	}
	
	private static int rowCount = 0;
	
	private static String statRow(String colourLeft, String left, Colour colourCentre, String centre, String colourRight, String right) {
		rowCount++;
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(rowCount%2==0?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
				+ "<div style='color:"+colourLeft+"; width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ left
				+ "</div>"
				+ "<div style='color:"+colourCentre.toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ centre
				+ "</div>"
				+ "<div style='color:"+colourRight+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ right
				+ "</div>"
			+ "</div>";
	}
	
	private static String statRow(Colour colourLeft, String left, Colour colourCentre, String centre, Colour colourRight, String right) {
		rowCount++;
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(rowCount%2==0?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
					+ "<div style='color:"+colourLeft.toWebHexString()+"; width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ left
					+ "</div>"
					+ "<div style='color:"+(centre.equalsIgnoreCase("未知") || centre.equalsIgnoreCase("N/A")?PresetColour.TEXT_GREY:colourCentre).toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ centre
					+ "</div>"
					+ "<div style='color:"+(right.equalsIgnoreCase("未知") || right.equalsIgnoreCase("N/A")?PresetColour.TEXT_GREY:colourRight).toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ right
					+ "</div>"
				+ "</div>";
	}

	private static String statRow(Colour colour, String text) {
		rowCount++;
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(rowCount%2==0?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
					+ "<div style='color:"+colour.toWebHexString()+"; width:100%; float:left; font-weight:bold; margin:0; padding:0; text-align:center;'>"
						+ text
					+ "</div>"
				+ "</div>";
	}

	private static String statAttributeHeader() {
		return "<div class='container-full-width' style='text-align:center; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'><b>"
				
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:26%;'>"
						+ "属性"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:16%;'>"
						+ "核心 | 加成"
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:20%;'>"
						+ "数值/最大值"
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:36%;'>"
						+ "描述"
					+ "</div>"
					
				+ "</b></div>";
	}
	
	private static String getAttributeBox(GameCharacter owner, AbstractAttribute att, String effect) {
		return getAttributeBox(owner, att, effect, false);
	}
	
	private static String getAttributeBox(GameCharacter owner, AbstractAttribute att, String effect, boolean half) {
		float value = owner.getAttributeValue(att);

		String valueForDisplay;
		if(((int)value)==value) {
			valueForDisplay = String.valueOf(((int)value));
		} else {
			valueForDisplay = String.valueOf(value);
		}
		if(att.isInfiniteAtUpperLimit() && value>=att.getUpperLimit()) {
			valueForDisplay = UtilText.getInfinitySymbol(true);
		}
		if(att.isPercentage()){
			valueForDisplay = (value>=0?"+":"")+valueForDisplay+"%";
		}
		
		float bonusAttributeValue = owner.getBonusAttributeValue(att) + (att==Attribute.RESISTANCE_PHYSICAL?owner.getPhysicalResistanceAttributeFromClothingAndWeapons():0);
		
		return "<div class='container-full-width' style='background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>"
				
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:30%;'>"
						+ "<b style='color:" + att.getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(att.getName())+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:6%;text-align:right;'>"
						+(owner.getBaseAttributeValue(att) > 0 
								? "<b style='color:" + PresetColour.GENERIC_MINOR_GOOD.getShades()[1] + ";"
								: (owner.getBaseAttributeValue(att) < 0
									? "<b style='color:" + PresetColour.GENERIC_MINOR_BAD.getShades()[1] + ";"
									: "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";"))+"'>"
							+Units.number(owner.getBaseAttributeValue(att), 0, 1)
						+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:6%;text-align:left;'>"
						+" | "	
						+ (bonusAttributeValue > 0
								? "<b style='color:" + PresetColour.GENERIC_MINOR_GOOD.getShades()[1] + ";"
								: (bonusAttributeValue < 0
									? "<b style='color:" + PresetColour.GENERIC_MINOR_BAD.getShades()[1] + ";"
									: "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";"))+"'>"
							+Units.number(bonusAttributeValue, 0, 1)
						+"</b>"
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:8%;text-align:right;'>"
						+ "<b style='color:"+att.getColour().toWebHexString()+";'>"
							+ valueForDisplay
						+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:8%;text-align:left;'>"
						+ "/"
						+ (value>=att.getUpperLimit() || (att==Attribute.MAJOR_CORRUPTION && value==0)
								?"[style.boldGood("+att.getUpperLimit()+")]"
								:"[style.boldDisabled("+att.getUpperLimit()+")]")
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:40%;'>"
						+ "[style.italicsDisabled("+effect.replaceAll("<br/>", " ")+")]"
					+ "</div>"
					
				+ "</div>";
	}

	public static String getContactEntry(GameCharacter contact) {
		boolean isOffspring = contact.getMotherId().equals(Main.game.getPlayer().getId()) || contact.getFatherId().equals(Main.game.getPlayer().getId());
		
		return UtilText.parse(contact, "<b style='color:"+contact.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"+(isOffspring
				?(contact.isFeminine()
						?"，你的[style.colourFeminine(女儿)]"
						:"，你的[style.colourMasculine(儿子)]")
				:"")
				+"，[npc.She]是"
				+ (contact.getRaceStage()==RaceStage.HUMAN || contact.isRaceConcealed()
					?"[npc.a_race(true)]"
					:"[npc.a_raceStage(true)][npc.race(true)]")
				+"，当前位置:"
				+ (contact.getWorldLocation()==WorldType.EMPTY
					?"[style.italicsDisabled(未知！)]"
					:"<i>"+contact.getWorldLocation().getName()+", "+contact.getLocationPlace().getPlaceType().getName()+"</i>。"));
	}
	
	public static final DialogueNode CONTACTS = new DialogueNode("联系人", "查看你的联系人", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			for (int i = 0; i < charactersEncountered.size(); i++) {
				GameCharacter npc = charactersEncountered.get(i);
				
				UtilText.nodeContentSB.append("<p>"
												+ getContactEntry(npc)
											+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回到手机主界面。", MENU);
			
			} else if (index <= charactersEncountered.size()) {
				GameCharacter npc = charactersEncountered.get(index-1);
				boolean isOffspring = npc.getMotherId().equals(Main.game.getPlayer().getId()) || npc.getFatherId().equals(Main.game.getPlayer().getId());
				return new Response(
						UtilText.parse(npc, isOffspring
								?(npc.isFeminine()?"[style.colourFeminine([npc.Name])]":"[style.colourMasculine([npc.Name])]")
								:"[npc.Name]"),
						getContactEntry(npc),
						CONTACTS_CHARACTER){
					@Override
					public void effects() {
						CharactersPresentDialogue.resetContent(npc);
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CONTACTS_CHARACTER = new DialogueNode("联系人", "查看你的联系人", true) {

		@Override
		public String getLabel() {
			return CharactersPresentDialogue.characterViewed.getName(true);
		}

		@Override
		public String getContent() {
			return CharactersPresentDialogue.menuContent;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回到手机主界面。", MENU);
			
			} else if (index <= Main.game.getPlayer().getCharactersEncountered().size()) {
				try {
					GameCharacter npc = Main.game.getNPCById(Main.game.getPlayer().getCharactersEncountered().get(index - 1));
					return new Response(Util.capitaliseSentence(npc.getName(true)),
							UtilText.parse(npc, "仔细查看[npc.Name]的外貌。"),
							CONTACTS_CHARACTER){
						@Override
						public void effects() {
							CharactersPresentDialogue.resetContent(npc);
							
						}
					};
				} catch (Exception e) {
					Util.logGetNpcByIdError("CONTACTS_CHARACTER.getResponse()", Main.game.getPlayer().getCharactersEncountered().get(index - 1));
					e.printStackTrace();
					return null;
				}
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	private static String getSubspeciesDiscoveredIndication() {
		return Main.getProperties().getSubspeciesDiscoveredCount()+"/"+Subspecies.getAllSubspecies().size();
	}

	private static String getWeaponsDiscoveredIndication() {
		int size = weaponsDiscoveredList.size();
		return Math.min(size, Main.getProperties().getWeaponsDiscoveredCount())+"/"+size;
	}
	
	private static String getClothingDiscoveredIndication() {
		int size = clothingDiscoveredList.size();
		return Math.min(size, Main.getProperties().getClothingDiscoveredCount())+"/"+size;
	}
	
	private static String getItemsDiscoveredIndication() {
		int size = itemsDiscoveredList.size();
		return Math.min(size, Main.getProperties().getItemsDiscoveredCount())+"/"+size;
	}
	
	private static int encyclopediaItemIndex = 0;
	
	public static final DialogueNode ENCYCLOPEDIA = new DialogueNode("百科全书", "", true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ "你在旅途中收集到的所有你遇到过的种族，武器，服饰和物品的信息。"
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>");
				sb.append("你已经收集到的：");
				sb.append(Main.getProperties().getSubspeciesDiscoveredCount()==Subspecies.getAllSubspecies().size()
								?"<br/>[style.colourGood(亚种:"+getSubspeciesDiscoveredIndication()+")]"
								:"<br/>亚种: "+getSubspeciesDiscoveredIndication());

				int size = weaponsDiscoveredList.size();
				sb.append(Math.min(size, Main.getProperties().getWeaponsDiscoveredCount())==weaponsDiscoveredList.size()
								?"<br/>[style.colourGood(武器:"+getWeaponsDiscoveredIndication()+")]"
								:"<br/>武器: "+getWeaponsDiscoveredIndication());
				
				size = clothingDiscoveredList.size();
				sb.append(Math.min(size, Main.getProperties().getClothingDiscoveredCount())==clothingDiscoveredList.size()
								?"<br/>[style.colourGood(衣物:"+getClothingDiscoveredIndication()+")]"
								:"<br/>衣物: "+getClothingDiscoveredIndication());
				
				size = itemsDiscoveredList.size();
				sb.append(Math.min(size, Main.getProperties().getItemsDiscoveredCount())==itemsDiscoveredList.size()
								?"<br/>[style.colourGood(物品: "+getItemsDiscoveredIndication()+")]"
								:"<br/>物品: "+getItemsDiscoveredIndication());
			sb.append("</p>");
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newRaceDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>种族</span>":"种族",
						"查看你在旅途中遇到的所有类型的种族。", RACES){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newRaceDiscovered, false);
					}
				};

			} else if (index == 2) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>武器</span>":"武器",
						"查看你在旅途中遇到的所有类型的武器。", WEAPON_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
					}
				};

			} else if (index == 3) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newClothingDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>衣物</span>":"衣物",
						"查看你在旅途中遇到的所有类型的衣物。", CLOTHING_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
						clothingSlotKey = clothingSlotCategories.keySet().iterator().next();
					}
				};

			} else if (index == 4) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newItemDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>物品</span>":"物品",
						"查看你在旅途中遇到的所有类型的物品。", ITEM_CATALOGUE){
					@Override
					public void effects() {
						encyclopediaItemIndex = 0;
						Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
					}
				};

			} else if (index == 5) {
				if(!Main.getProperties().hasValue(PropertyValue.newItemDiscovered)
						&& !Main.getProperties().hasValue(PropertyValue.newClothingDiscovered)
						&& !Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered)
						&& !Main.getProperties().hasValue(PropertyValue.newRaceDiscovered)) {
					return new Response("清除提醒", "清除所有百科全书的提醒。<br/><i>你当前没有任何提醒可以清除……</i>", null);
					
				} else {
					return new ResponseEffectsOnly("清除提醒",
							"清除百科全书的提醒。"){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_MINOR_GOOD;
						}
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
							Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
							Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
							Main.getProperties().setValue(PropertyValue.newRaceDiscovered, false);
						}
					};
				}
				
			} else if (index == 0) {
				return new Response("返回", "返回到手机主页面。", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static List<AbstractItemType> itemsDiscoveredList = new ArrayList<>();
	private static List<AbstractClothingType> clothingDiscoveredList = new ArrayList<>();
	private static List<AbstractWeaponType> weaponsDiscoveredList = new ArrayList<>();
	
	private static Map<String, List<InventorySlot>> clothingSlotCategories;
	private static String clothingSlotKey;
	
	/**
	 * @return A list of all clothing which is available to the player in a normal game. i.e. A list of all clothing excluding silly mode or cheat items.
	 */
	public static List<AbstractClothingType> getClothingDiscoveredList() {
		return clothingDiscoveredList;
	}

	/**
	 * @return A list of all weapons which are available to the player in a normal game. i.e. A list of all weapons excluding silly mode or cheat items.
	 */
	public static List<AbstractWeaponType> getWeaponsDiscoveredList() {
		return weaponsDiscoveredList;
	}
	
	static {
		itemsDiscoveredList.addAll(ItemType.getAllItems());
		itemsDiscoveredList.removeIf((it) -> it.getItemTags().contains(ItemTag.CHEAT_ITEM) || it.getItemTags().contains(ItemTag.SILLY_MODE));
		Collections.sort(itemsDiscoveredList, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		weaponsDiscoveredList.addAll(WeaponType.getAllWeapons());
		weaponsDiscoveredList.removeIf((wt) -> wt.getItemTags().contains(ItemTag.CHEAT_ITEM) || wt.getItemTags().contains(ItemTag.SILLY_MODE));
		Collections.sort(weaponsDiscoveredList, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		clothingDiscoveredList.addAll(ClothingType.getAllClothing());
		clothingDiscoveredList.removeIf((ct) -> ct.getDefaultItemTags().contains(ItemTag.CHEAT_ITEM) || ct.getDefaultItemTags().contains(ItemTag.SILLY_MODE));
		Collections.sort(clothingDiscoveredList, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		clothingSlotCategories = new LinkedHashMap<>();
		
		clothingSlotCategories.put("头部",
				Util.newArrayListOfValues(
				InventorySlot.HEAD,
				InventorySlot.EYES,
				InventorySlot.HAIR,
				InventorySlot.MOUTH,
				InventorySlot.NECK));
		
		clothingSlotCategories.put("躯干",
				Util.newArrayListOfValues(
				InventorySlot.TORSO_OVER,
				InventorySlot.TORSO_UNDER));
		
		clothingSlotCategories.put("胸部",
				Util.newArrayListOfValues(
				InventorySlot.CHEST,
				InventorySlot.NIPPLE));
		
		clothingSlotCategories.put("手臂",
				Util.newArrayListOfValues(
				InventorySlot.HAND,
				InventorySlot.WRIST,
				InventorySlot.FINGER));
		
		clothingSlotCategories.put("腰部",
				Util.newArrayListOfValues(
				InventorySlot.STOMACH,
				InventorySlot.HIPS));
		
		clothingSlotCategories.put("腹股沟",
				Util.newArrayListOfValues(
				InventorySlot.GROIN,
				InventorySlot.PENIS,
				InventorySlot.VAGINA,
				InventorySlot.ANUS));
		
		clothingSlotCategories.put("腿部",
				Util.newArrayListOfValues(
				InventorySlot.LEG,
				InventorySlot.SOCK));
		
		clothingSlotCategories.put("足部",
				Util.newArrayListOfValues(
				InventorySlot.FOOT,
				InventorySlot.ANKLE));
		
		clothingSlotCategories.put("其他",
				Util.newArrayListOfValues(
				InventorySlot.HORNS,
				InventorySlot.WINGS,
				InventorySlot.TAIL));
		
		clothingSlotCategories.put("穿孔",
				Util.newArrayListOfValues(
				InventorySlot.PIERCING_EAR,
				InventorySlot.PIERCING_LIP,
				InventorySlot.PIERCING_NOSE,
				InventorySlot.PIERCING_TONGUE,
				InventorySlot.PIERCING_NIPPLE,
				InventorySlot.PIERCING_STOMACH,
				InventorySlot.PIERCING_PENIS,
				InventorySlot.PIERCING_VAGINA));
	}
	
	public static final DialogueNode WEAPON_CATALOGUE = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return "已发现的武器("+getWeaponsDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			StringBuilder sbMelee = new StringBuilder();
			StringBuilder sbRanged = new StringBuilder();
			StringBuilder sbDamageTypes = new StringBuilder();

			int meleeCount = 0;
			int meleeKnownCount = 0;
			int rangedCount = 0;
			int rangedKnownCount = 0;
			
			for(AbstractWeaponType weaponType : weaponsDiscoveredList) {
				boolean discovered = Main.getProperties().isWeaponDiscovered(weaponType);
				String entry = "<div class='inventory-item-slot unequipped' style='background-color:"+weaponType.getRarity().getBackgroundColour().toWebHexString()+"; width:78%; margin:1%; padding:0; '>"
									+ "<div class='inventory-icon-content'>"+(discovered?weaponType.getSVGImage():"")+"</div>"
									+ "<div class='overlay"+(discovered?"' id='"+weaponType.getId()+"'":" disabled-dark'")+" style='cursor:default;'></div>"
								+ "</div>";
				sbDamageTypes.setLength(0);
				if(discovered) {
					float width = weaponType.getAvailableDamageTypes().size()>4?(72/weaponType.getAvailableDamageTypes().size()):18;
					for(DamageType dt : weaponType.getAvailableDamageTypes()) {
						sbDamageTypes.append("<div class='square-button' "+(discovered?"id='"+(weaponType.getId()+"_"+dt.toString())+"'":"")
												+ " style='cursor:default; width:"+width+"%; margin:1%; padding:0; background-color:"+dt.getMultiplierAttribute().getColour().toWebHexString()+";'>"
											+ "</div>");
					}
				}
				if(weaponType.isMelee()) {
					meleeCount++;
					if(discovered) {
						meleeKnownCount++;
					}
					sbMelee.append("<div class='container-full-width' style='width:11.5%; padding:0; margin:0.5%;'>");
						sbMelee.append(entry);
						sbMelee.append(sbDamageTypes.toString());
					sbMelee.append("</div>");
					
				} else {
					rangedCount++;
					if(discovered) {
						rangedKnownCount++;
					}
					sbRanged.append("<div class='container-full-width' style='width:11.5%; padding:0; margin:0.5%;'>");
						sbRanged.append(entry);
						sbRanged.append(sbDamageTypes.toString());
					sbRanged.append("</div>");
				}
			}
			
			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldBlue(近战武器("+meleeKnownCount+"/"+meleeCount+"))]");
				sb.append("</p>");
				sb.append(sbMelee.toString());
			sb.append("</div>");
			
			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldYellow(远程武器("+rangedKnownCount+"/"+rangedCount+"))]");
				sb.append("</p>");
				sb.append(sbRanged.toString());
			sb.append("</div>");
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回到百科主界面。", ENCYCLOPEDIA);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CLOTHING_CATALOGUE = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return "已发现的衣物("+getClothingDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			List<InventorySlot> slots = clothingSlotCategories.get(clothingSlotKey);
			Map<InventorySlot, StringBuilder> sbMap = new LinkedHashMap<>();
			Map<InventorySlot, Value<Integer, Integer>> discoveredMap = new HashMap<>();
			for(InventorySlot slot : slots) {
				sbMap.put(slot, new StringBuilder());
				discoveredMap.put(slot, new Value<>(0, 0));
			}
			
			for(AbstractClothingType clothingType : clothingDiscoveredList) {
				if(Collections.disjoint(clothingType.getEquipSlots(), slots)) {
					continue;
				}
				boolean discovered = Main.getProperties().isClothingDiscovered(clothingType);
				
				for(InventorySlot slot : clothingType.getEquipSlots()) {
					if(slots.contains(slot)) {
						String entry = "<div class='inventory-item-slot unequipped' style='background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+"; width:8%;'>"
								+ "<div class='inventory-icon-content'>"+(discovered?clothingType.getSVGImageRandomColour(slot, true, false, false):"")+"</div>"
								+ "<div class='overlay"+(discovered?"' id='"+clothingType.getId()+"_"+slot.toString()+"'":" disabled-dark'")+" style='cursor:default;'></div>"
							+ "</div>";
						sbMap.get(slot).append(entry);
						discoveredMap.put(slot, new Value<>(discoveredMap.get(slot).getKey()+(discovered?1:0), discoveredMap.get(slot).getValue()+1));
					}
				}
			}

			for(InventorySlot slot : slots) {
				sb.append("<div class='container-full-width'>");
					sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
						sb.append("[style.boldYellowLight("+Util.capitaliseSentence(slot.getName())+" ("+discoveredMap.get(slot).getKey()+"/"+discoveredMap.get(slot).getValue()+"))]");
					sb.append("</p>");
					sb.append(sbMap.get(slot).toString());
				sb.append("</div>");
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回到百科主界面。", ENCYCLOPEDIA);
			
			} else {
				List<Response> responses = new ArrayList<>();
				for(Entry<String, List<InventorySlot>> entry : clothingSlotCategories.entrySet()) {
					if(clothingSlotKey==entry.getKey()) {
						responses.add(new Response(entry.getKey(), "你正在查看该类别！", null));
						
					} else {
						responses.add(new Response(entry.getKey(), "查看适合以下栏位的所有服装:<br/><i>"+Util.capitaliseSentence(Util.inventorySlotsToStringList(entry.getValue()))+".</i>", CLOTHING_CATALOGUE) {
							@Override
							public void effects() {
								clothingSlotKey = entry.getKey();
							}
						});
					}
				}
				if(index-1<responses.size()) {
					return responses.get(index-1);
				}
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode ITEM_CATALOGUE = new DialogueNode("", "查看已经发现的物品", true) {
		@Override
		public String getLabel() {
			return "已经发现的物品 ("+getItemsDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			StringBuilder sbItems = new StringBuilder();
			StringBuilder sbBooks = new StringBuilder();
			StringBuilder sbEssences = new StringBuilder();
			StringBuilder sbSpells = new StringBuilder();
			
			int itemCount = 0;
			int itemKnownCount = 0;
			int bookCount = 0;
			int bookKnownCount = 0;
			int essenceCount = 0;
			int essenceKnownCount = 0;
			int spellCount = 0;
			int spellKnownCount = 0;
			
			for(AbstractItemType itemType : itemsDiscoveredList) {
				boolean discovered = Main.getProperties().isItemDiscovered(itemType);
				String entry = "<div class='inventory-item-slot unequipped' style='background-color:"+itemType.getRarity().getBackgroundColour().toWebHexString()+"; width:8%;'>"
									+ "<div class='inventory-icon-content'>"+(discovered?itemType.getSVGString():"")+"</div>"
									+ "<div class='overlay"+(discovered?"' id='"+itemType.getId()+"'":" disabled-dark'")+" style='cursor:default;'></div>"
								+ "</div>";
				
				if(itemType.getItemTags().contains(ItemTag.BOOK)) {
					sbBooks.append(entry);
					bookCount++;
					if(discovered) {
						bookKnownCount++;
					}
					
				} else if(itemType.getItemTags().contains(ItemTag.ESSENCE)) {
					sbEssences.append(entry);
					essenceCount++;
					if(discovered) {
						essenceKnownCount++;
					}
					
				} else if(itemType.getItemTags().contains(ItemTag.SPELL_BOOK) || itemType.getItemTags().contains(ItemTag.SPELL_SCROLL)) {
					sbSpells.append(entry);
					spellCount++;
					if(discovered) {
						spellKnownCount++;
					}
					
				} else {
					sbItems.append(entry);
					itemCount++;
					if(discovered) {
						itemKnownCount++;
					}
				}
			}
			
			if(encyclopediaItemIndex==0) {
				sb.append("<div class='container-full-width'>");
					sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
						sb.append("[style.boldBlueLight(物品 ("+itemKnownCount+"/"+itemCount+"))]");
					sb.append("</p>");
					sb.append(sbItems.toString());
				sb.append("</div>");
			}

			if(encyclopediaItemIndex==1) {
				sb.append("<div class='container-full-width'>");
					sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
						sb.append("[style.boldOrange(书籍 ("+bookKnownCount+"/"+bookCount+"))]");
					sb.append("</p>");
					sb.append(sbBooks.toString());
				sb.append("</div>");
				
				sb.append("<div class='container-full-width'>");
					sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
						sb.append("[style.boldArcane(精华 ("+essenceKnownCount+"/"+essenceCount+"))]");
					sb.append("</p>");
					sb.append(sbEssences.toString());
				sb.append("</div>");
			}

			if(encyclopediaItemIndex==2) {
				sb.append("<div class='container-full-width'>");
					sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
						sb.append("[style.boldSpells(法术 ("+spellKnownCount+"/"+spellCount+"))]");
					sb.append("</p>");
					sb.append(sbSpells.toString());
				sb.append("</div>");
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("道具",
						encyclopediaItemIndex==0
							?"你正在查看已发现的所有物品……"
							:"查看已发现的所有物品。",
						encyclopediaItemIndex==0
							?null
							:ITEM_CATALOGUE){
					@Override
					public void effects() {
						encyclopediaItemIndex = 0;
					}
				};
				
			} else if(index==2) {
				return new Response("书籍&精华",
						encyclopediaItemIndex==1
							?"你正在查看已发现的所有种族书籍和精华……"
							:"查看已发现的所有种族书籍和精华。",
						encyclopediaItemIndex==1
							?null
							:ITEM_CATALOGUE){
					@Override
					public void effects() {
						encyclopediaItemIndex = 1;
					}
				};
				
			} else if(index==3) {
				return new Response("法术书",
						encyclopediaItemIndex==2
							?"你正在查看已发现的所有法术书……"
							:"查看已发现的所有法术书。",
						encyclopediaItemIndex==2
							?null
							:ITEM_CATALOGUE){
					@Override
					public void effects() {
						encyclopediaItemIndex = 2;
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "返回到百科主界面。", ENCYCLOPEDIA);
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static List<AbstractRace> racesDiscovered = new ArrayList<>();
	private static List<AbstractSubspecies> subspeciesDiscovered = new ArrayList<>();
	private static AbstractRace raceSelected;
	private static AbstractSubspecies subspeciesSelected;
	private static Body bodyForSubspeciesSelected;
	private static StringBuilder subspeciesSB = new StringBuilder();
	
	public static void resetContentForRaces() {
		
		subspeciesDiscovered.clear();
		
		for (AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
			if(Main.getProperties().isRaceDiscovered(subspecies)) {
				AbstractRace race = subspecies.getRace();
				if(!racesDiscovered.contains(race)) {
					racesDiscovered.add(race);
				}
				subspeciesDiscovered.add(subspecies);
			}
		}
		
		racesDiscovered.sort((a, b) -> a.getName(false).compareTo(b.getName(false)));
		subspeciesDiscovered.sort((a, b) -> a.getName(null).compareTo(b.getName(null)));
		
	}

	public static final DialogueNode RACES = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return "已被发现的物种 ("+getSubspeciesDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			// Race (X/Y), book icon if advanced knowledge found
			
			UtilText.nodeContentSB.append(
					"<p style='text-align:center;'>"
						+ "你已经在旅途中遇到了以下种族："
						+ "<br/>"
						+ "括号内为每个种族已发现的亚种数量。"
						+ "<br/>"
						+ "如果你已经解锁了该种族的高阶知识，将会显示其图标。"
//						+ "Discovered races are [style.boldGood(highlighted)], while undiscovered races are [style.colourDisabled(greyed out)]."
					+ "</p>");
			List<AbstractRace> sortedRaces = new ArrayList<>();
			sortedRaces.addAll(Race.getAllRaces());
			sortedRaces.remove(Race.NONE);
			sortedRaces.sort((r1, r2) -> r1.getName(false).compareTo(r2.getName(false)));
			int unknownRaces=0;
			for(AbstractRace race : sortedRaces) {
				int discoveredSubspecies = 0;
				int totalSubspecies = 0;
				boolean fullKnowledge = true;
				for(AbstractSubspecies subspecies : Subspecies.getSubspeciesOfRace(race)) {
					if(subspeciesDiscovered.contains(subspecies)) {
						discoveredSubspecies++;
					}
					if(!Main.getProperties().isAdvancedRaceKnowledgeDiscovered(subspecies)) {
						fullKnowledge = false;
					}
					totalSubspecies++;
				}
				if(discoveredSubspecies==0) {
					unknownRaces++;
					continue;
				}
				String icon = 
						"<div class='inventory-item-slot' style='width:10%; margin:0; "+(!fullKnowledge?"opacity:0.25;":"")+" pointer-events:none;'>"
							+(fullKnowledge
								?AbstractSubspecies.getMainSubspeciesOfRace(race).getSVGString(null)
								:"")//"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceUnknown()+"</div>")
						+"</div>";
				
				String discoveredInfo =
						"<div style='float:left; width:20%; margin:0; text-align:right;'>"
							+(discoveredSubspecies==0
								?"[style.colourDisabled((?/?))]"
								:(discoveredSubspecies==totalSubspecies
									?"[style.colourGood("
									:"")
										+"("+discoveredSubspecies+"/"+totalSubspecies+")"
								+ (discoveredSubspecies==totalSubspecies?")]":""))
						+ "</div>";
				//position:relative; width:"+(Util.random.nextInt(23)+10)+"%; padding:2px; margin:0.5%; float:left;filter:blur(1px) grayscale(0.8); font-size:10px; transform: rotate("+(-20+Util.random.nextInt(41))+"deg);'>
				UtilText.nodeContentSB.append("<div class='container-full-width' style='position:relative; width:32%; padding:2px; margin:0.5%; float:left;'>");
					UtilText.nodeContentSB.append("<div class='overlay' id='ENCYCLOPEDIA_RACE_"+Race.getIdFromRace(race)+"'></div>");
					UtilText.nodeContentSB.append(icon);
					UtilText.nodeContentSB.append("<div style='float:left; text-align:center; width:70%; margin:0;'>");
							if(discoveredSubspecies==0) {
								UtilText.nodeContentSB.append("[style.colourDisabled(???)]");
							} else if(racesDiscovered.contains(race)) {
								UtilText.nodeContentSB.append("<span style='color:"+race.getColour().toWebHexString()+";'>" + Util.capitaliseSentence(race.getName(false)) + "</span>");
							} else {
								UtilText.nodeContentSB.append("[style.colourDisabled(" + Util.capitaliseSentence(race.getName(false))+")]");
							}
						UtilText.nodeContentSB.append("</div>");
					UtilText.nodeContentSB.append(discoveredInfo);
				UtilText.nodeContentSB.append("</div>");
			}
			for(int i=0; i<unknownRaces;i++) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='position:relative; width:32%; padding:2px; margin:0.5%; float:left;'>");
					UtilText.nodeContentSB.append("<div class='inventory-item-slot' style='width:10%; margin:0; opacity:0.2;'></div>");
					UtilText.nodeContentSB.append("<div style='float:left; text-align:center; width:70%; margin:0;'>");
								UtilText.nodeContentSB.append("[style.colourDisabled(???)]");
						UtilText.nodeContentSB.append("</div>");
					UtilText.nodeContentSB.append("<div style='float:left; width:20%; margin:0; text-align:right;'>[style.colourDisabled((?/?))]</div>");
				UtilText.nodeContentSB.append("</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回到百科主界面。", ENCYCLOPEDIA);
			
			} else if (index <= racesDiscovered.size()) {
				return new Response(Util.capitaliseSentence(racesDiscovered.get(index - 1).getName(false)),
						"查看这一种族的所有亚种:'" + racesDiscovered.get(index - 1).getName(false) + "'",
						SUBSPECIES){
					@Override
					public void effects() {
						applyRaceSelection(racesDiscovered.get(index - 1));
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static void applyRaceSelection(AbstractRace race) {
		raceSelected = race;
		subspeciesSelected = AbstractSubspecies.getMainSubspeciesOfRace(raceSelected);
		if(!subspeciesDiscovered.contains(subspeciesSelected)) {
			for(AbstractSubspecies sub : subspeciesDiscovered) {
				if(sub.getRace()==raceSelected) {
					subspeciesSelected = sub;
					break;
				}
			}
		}
		bodyForSubspeciesSelected = Main.game.getCharacterUtils().generateBody(null, Gender.M_P_MALE, subspeciesSelected, RaceStage.GREATER);
	}
	
	private static List<String> getSubspeciesModifiersAsStringList(AbstractSubspecies subspecies) {
		LinkedHashMap<AbstractAttribute, Float> attMods;

		attMods = new LinkedHashMap<>(subspecies.getStatusEffectAttributeModifiers(null));
		
		ArrayList<String> fullModList = new ArrayList<>(getSubspeciesAttributeModifiersToStringList(attMods));
		fullModList.addAll(subspecies.getExtraEffects(null));
		
		if(subspecies.isFeralConfigurationAvailable(null)) {
			fullModList.add("<br/><b>处于[style.boldFeral(兽态)]时额外的加成：</b>");
			
			for(String s : subspecies.getFeralEffects()) {
				fullModList.add(s);
			}
		}
		
		return fullModList;
	}

	private static List<String> getSubspeciesAttributeModifiersToStringList(Map<AbstractAttribute, Float> attributeMap) {
		List<String> attributeModifiersList = new ArrayList<>();
		if (attributeMap != null) {
			for (Entry<AbstractAttribute, Float> e : attributeMap.entrySet()) {
				attributeModifiersList.add(e.getKey().getFormattedValue(e.getValue()));
			}
		}
		return attributeModifiersList;
	}
	
	public static final DialogueNode SUBSPECIES = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return Util.capitaliseSentence(subspeciesSelected.getName(null));
		}
		@Override
		public String getContent() {
			subspeciesSB.setLength(0);
			
			Body femaleBody = Main.game.getCharacterUtils().generateBody(null, Gender.F_V_B_FEMALE, subspeciesSelected, RaceStage.GREATER);
			Body maleBody = Main.game.getCharacterUtils().generateBody(null, Gender.M_P_MALE, subspeciesSelected, RaceStage.GREATER);
			
			subspeciesSB.append(
				"<div class='container-full-width' style='width:40%; float:right;'>"
					+ "<p style='width:100%; text-align:center;'>"
						+ "<b style='color:"+subspeciesSelected.getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getName(null))+"</b>"
						+ "<br/>平均属性"
					+ "</p>"
					+ "<table align='center'>"
						+ "<tr>"
							+ "<td></td>"
							+ "<td>[style.colourFeminine("+Util.capitaliseSentence(subspeciesSelected.getSingularFemaleName(null))+")]</td>"
							+ "<td>[style.colourMasculine("+Util.capitaliseSentence(subspeciesSelected.getSingularMaleName(null))+")]</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>身高</td>"
							+ "<td>"+Units.size(femaleBody.getHeightValue())+"</td>"
							+ "<td>"+Units.size(maleBody.getHeightValue())+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>女性化程度</td>"
							+ "<td>"+femaleBody.getFemininity()+"</td>"
							+ "<td>"+maleBody.getFemininity()+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>乳房尺寸</td>"
							+ "<td>"+(femaleBody.getBreast().getRawSizeValue()==0
										?"平坦"
										:femaleBody.getBreast().getSize().getCupSizeName()+"罩杯")+"</td>"
							+ "<td>"+(maleBody.getBreast().getRawSizeValue()==0
										?"平坦"
										:maleBody.getBreast().getSize().getCupSizeName()+"罩杯")+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>阴茎长度</td>"
							+ "<td>-</td>"
							+ "<td>"+Units.size(maleBody.getPenis().getRawLengthValue())+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>阴茎直径</td>"
							+ "<td>-</td>"
							+ "<td>"+Units.size(maleBody.getPenis().getRawGirthValue())+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>阴道直径</td>"
							+ "<td>"+Util.capitaliseSentence(femaleBody.getVagina().getOrificeVagina().getCapacity().getDescriptor())+"</td>"
							+ "<td>-</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>产仔数量</td>"
							+ "<td>"+subspeciesSelected.getRace().getNumberOfOffspringLow()+"-"+subspeciesSelected.getRace().getNumberOfOffspringHigh()+"</td>"
							+ "<td>-</td>"
						+ "</tr>"
					+ "</table>"
					+ "<hr/>"
					+ "<p style='width:100%; text-align:center;'>"
						+ "生物钟:"+Util.capitaliseSentence(subspeciesSelected.getNocturnality().getName())
						+ "<br/>"
						+ "水生:"+(subspeciesSelected.isAquatic()?"是":"否")
						+ "<br/>"
						+ "腿部配置:"+Util.capitaliseSentence(bodyForSubspeciesSelected.getLegConfiguration().getName())
						+ "<br/>"
						+ "身材矮小:"+(subspeciesSelected.isShortStature()?"是":"否")
					+"</p>"
				+ "</div>");
					
			subspeciesSB.append("<p>"
					+ "<b style='color:"+subspeciesSelected.getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getName(null))+"</b>"
					+ (AbstractSubspecies.getMainSubspeciesOfRace(raceSelected)==subspeciesSelected
							?" (<span style='color:"+raceSelected.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(raceSelected.getName(false))+"</span>的[style.colourMinorGood(核心)]亚种)"
							:" (<span style='color:"+raceSelected.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(raceSelected.getName(false))+"</span>的亚种)")
					+ "<br/>"
					+ "男性名:<span style='color:"+Femininity.valueOf(maleBody.getFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getSingularMaleName(null))+"</span>"
					+ "<br/>"
					+ "女性名:<span style='color:"+Femininity.valueOf(femaleBody.getFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getSingularFemaleName(null))+"</span>");

			subspeciesSB.append("<br/><br/>"
					+"<b>种族加成:</b>");
			if(Main.getProperties().isAdvancedRaceKnowledgeDiscovered(subspeciesSelected)) {
				for (String s : getSubspeciesModifiersAsStringList(subspeciesSelected)) {
					subspeciesSB.append("<br/>");
					subspeciesSB.append(s);
				}
			} else {
				subspeciesSB.append("<br/>");
				subspeciesSB.append("<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>");
					subspeciesSB.append("种族加成信息可以从书籍中获取！");
				subspeciesSB.append("</span>");
			}
			
			subspeciesSB.append("<br/><br/>"
					+ "<i>"+subspeciesSelected.getDescription(null)+"</i>"
				+ "</p>");
					
			subspeciesSB.append("<h6>"+Util.capitaliseSentence(raceSelected.getName(false))+"小知识</h6>"
					+subspeciesSelected.getBasicDescription(null)
					+ (Main.getProperties().isAdvancedRaceKnowledgeDiscovered(subspeciesSelected)
						?subspeciesSelected.getAdvancedDescription(null)
						:"<p style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
							+ "更多的信息可以从书籍中收集！"
						+ "</p>"));
			
			return subspeciesSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<AbstractSubspecies> raceSubspecies = Subspecies.getSubspeciesOfRace(raceSelected);
			
			if (index == 0) {
				return new Response("返回", "返回到种族选择界面。", RACES);
			
			} else if (index <= raceSubspecies.size()) {
				AbstractSubspecies indexSubspecies = raceSubspecies.get(index - 1);
				if(!subspeciesDiscovered.contains(indexSubspecies)) {
					return new Response(Util.capitaliseSentence(indexSubspecies.getName(null)),
							"你还没有遇到过这种亚种！",
							null);
				}
				return new Response(Util.capitaliseSentence(indexSubspecies.getName(null)),
						"仔细查看" + indexSubspecies.getNamePlural(null) + "的细节。"
						+ (AbstractSubspecies.getMainSubspeciesOfRace(raceSelected)==indexSubspecies
							?"<br/>当前为"+raceSelected.getName(false)+"[style.colourMinorGood(核心)]亚种。"
							:""),
						SUBSPECIES){
					@Override
					public Colour getHighlightColour() {
						if(AbstractSubspecies.getMainSubspeciesOfRace(raceSelected)==indexSubspecies) {
							return PresetColour.GENERIC_MINOR_GOOD;
						}
						return super.getHighlightColour();
					}
					@Override
					public void effects() {
						subspeciesSelected = indexSubspecies;
						bodyForSubspeciesSelected = Main.game.getCharacterUtils().generateBody(null, Gender.M_P_MALE, subspeciesSelected, RaceStage.GREATER);
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CHARACTER_PERK_TREE = new DialogueNode("天赋树", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.getProperties().setValue(PropertyValue.levelUpHightlight, false);
		}
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details>"
							+ "<summary>[style.boldPerk(天赋和特性信息)]</summary>"
							+ "[style.colourPerk(天赋)](圆形图标)为你的属性提供永久增益。<br/>"
							+ "[style.colourPerk(特性)](方形图标)为你的角色提供独特的效果。"
								+ "与天赋不同，<b>特性在加入“生效特性”栏之前不会有任何效果</b>.<br/>"
							+ "天赋需要消耗天赋点数解锁。每当升级时便会获得一点天赋点数，并且每五级获得额外两点天赋点数。<br/><br/>"
							+ "除了这些通过天赋点解锁的天赋以外，还存在着一些通过特殊事件解锁的特殊隐藏天赋。"
					+ "</details>");
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(Main.game.getPlayer(), true));
			UtilText.nodeContentSB.append("</div>");
			
			if(!Main.game.getPlayer().isDoll()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='padding:8px; text-align:center;'>"
							+ "[style.italicsBad(请注意此天赋树仍在施工，并非最终版本，仅用于展示概念！)]"
						+ "</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isDoll()) {
					return new Response("重置", "身为玩偶时你无法重置天赋及特性！", null);
				}
				return new Response("重置", "重置所有的天赋和特性，返还所有消耗的天赋点。(临时可用，由于天赋树仍在开发中。)", CHARACTER_PERK_TREE) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetPerksMap(false, false);
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "返回到手机主界面。", MENU);
			}
			
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	
//	private static boolean confirmReset = false;
	public static final DialogueNode CHARACTER_FETISHES = new DialogueNode("渴望与性癖", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder(
					"<details>"
						+ "<summary>[style.boldFetish(性癖信息)]</summary>"
							+ "你可以[style.colourArcane(无消耗地)]选择针对每一种性癖的[style.colourLust(渴望)]，"
							+ "或者消耗[style.colourArcane(奥术精华)]直接选择相关的[style.colourFetish(性癖)]。<br/><br/>"
							+ "选择渴望值会影响性交中的额外性欲获取，而取得某性癖则会永久将渴望值设置为“热爱”，并给予你特殊增益。"
							+ "性癖只能通过附魔药水去除。<br/><br/>"
							+ "你当前选择的渴望值使你拥有"+PresetColour.FETISH.getName()+"的边框，但你的真实渴望值(由染色的渴望值图标表示)可以通过附魔衣物或其他物品调整。<br/><br/>"
							+ "每次在性交中执行与某性癖相关的动作时，就会获得相应经验。"
							+ "无论你是否拥有相关性癖，都会获得经验。"
							+ "更高的性癖等级会令你和你的对象从相关性动作中获得更高的快感，并且也会提升性癖的增益。<br/><br/>"
							+ "最后，派生性癖无法直接解锁，但在满足要求后会自动解锁。"
					+ "</details>");
			
			// Normal fetishes:

			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold;'><h6>性癖</h6></div>");
			ArrayList<AbstractFetish> derivedFetishList = new ArrayList<>();
			ArrayList<AbstractFetish> pairedFetishList = new ArrayList<>();
			ArrayList<AbstractFetish> soloFetishList = new ArrayList<>();
			for(AbstractFetish fetish : Fetish.getAllFetishes()) {
				if(fetish.isContentEnabled()) {
					if(!fetish.getFetishesForAutomaticUnlock().isEmpty()) {
						derivedFetishList.add(fetish);
					} else if(fetish.getOpposite()!=null) {
						pairedFetishList.add(fetish);
					} else {
						soloFetishList.add(fetish);
					}
				}
			}
			while (pairedFetishList.size() > 0) {
				AbstractFetish fetish = pairedFetishList.remove(0);
				if(fetish!=null) {
					pairedFetishList.remove(fetish.getOpposite());
					if (fetish.isTopFetish()) {
						journalSB.append(getFetishEntry(Main.game.getPlayer(), fetish, fetish.getOpposite()));
					} else {
						journalSB.append(getFetishEntry(Main.game.getPlayer(), fetish.getOpposite(), fetish));
					}
				}
			}
			while (soloFetishList.size() > 0) {
				AbstractFetish fetish = soloFetishList.remove(0);
				AbstractFetish fetish2 = null;
				if(soloFetishList.size() > 0) {
					fetish2 = soloFetishList.remove(0);
				}
				journalSB.append(getFetishEntry(Main.game.getPlayer(), fetish, fetish2));
			}
			
			// Derived fetishes:

			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold; margin-top:16px;'><h6>派生性癖</h6></div>");
			journalSB.append("<div class='fetish-container'>");
			
			for(AbstractFetish fetish : derivedFetishList) {
				journalSB.append(
						"<div id='FETISH_" + Fetish.getIdFromFetish(fetish) + "' class='fetish-icon" + (Main.game.getPlayer().hasFetish(fetish)
						? " owned' style='border:2px solid " + PresetColour.FETISH.getShades()[1] + ";'>"
						: (fetish.isAvailable(Main.game.getPlayer())
								? " unlocked' style='border:2px solid " +  PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>"
								: " locked' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";'>"))
						+ "<div class='fetish-icon-content'>"+fetish.getSVGString(Main.game.getPlayer())+"</div>"
						+ (Main.game.getPlayer().hasFetish(fetish) // Overlay to create disabled effect:
								? ""
								: (fetish.isAvailable(Main.game.getPlayer())
										? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.5); border-radius:5px;'></div>"
										: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.7); border-radius:5px;'></div>"))
						+ "</div>");
			}
			
			// Free Fetishes:
			
			journalSB.append("</div>");
			
			
			return journalSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回到手机主界面。", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	
	public static String getFetishEntry(GameCharacter targetedCharacter, AbstractFetish othersFetish, AbstractFetish selfFetish) {
		return "<div class='container-full-width' style='background:transparent; margin:2px 0; width:100%;'>"
				+getIndividualFetishEntry(targetedCharacter, othersFetish)
				+(selfFetish == null?"":getIndividualFetishEntry(targetedCharacter, selfFetish))
				+"</div>";
	}
	
	private static String getIndividualFetishEntry(GameCharacter targetedCharacter, AbstractFetish fetish) {
		FetishLevel level = FetishLevel.getFetishLevelFromValue(targetedCharacter.getFetishExperience(fetish));
		float experiencePercentage = ((targetedCharacter.getFetishExperience(fetish)) / (float)(level.getMaximumExperience()))*100;
		
		return "<div class='container-half-width' style='margin:0 8px;'>"
					+"<div class='container-full-width' style='text-align:center; font-weight:bold; margin:0 8px; width: calc(78% - 16px);'>"
						+ (targetedCharacter.hasFetish(fetish)
								?"[style.colourPink("+Util.capitaliseSentence(fetish.getName(targetedCharacter))+""+level.getNumeral()+")]"
								:Util.capitaliseSentence(fetish.getName(targetedCharacter))+""+level.getNumeral())
						+"<div class='container-full-width' style='margin:2px 0; padding:0; width:100%;'></div>" // Spacer
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.ZERO_HATE)
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.ONE_DISLIKE)
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.TWO_NEUTRAL)
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.THREE_LIKE)
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.FOUR_LOVE)
					+ "</div>"
					+"<div class='container-full-width' style='margin:0 8px; width: calc(22% - 16px);'>"
						+ "<div id='FETISH_" + Fetish.getIdFromFetish(fetish) + "' class='fetish-icon full" + (targetedCharacter.hasFetish(fetish)
							? " owned' style='border:2px solid " + PresetColour.FETISH.toWebHexString() + ";'>"
							: (fetish.isAvailable(targetedCharacter)
									? " unlocked' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>"
									: " locked' style='border:2px solid " + PresetColour.TEXT_GREY_DARK.toWebHexString() + ";'>"))
										+ "<div class='fetish-icon-content'>"+fetish.getSVGString(targetedCharacter)+"</div>"
										+ "<div style='width:40%;height:40%;position:absolute;top:0;right:4px;'>"+level.getSVGImageOverlay()+"</div>"
										+ (targetedCharacter.hasFetish(fetish) // Overlay to create disabled effect:
											? ""
											: (fetish.isAvailable(targetedCharacter)
													? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.5); border-radius:5px;'></div>"
													: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.7); border-radius:5px;'></div>"))
						+ "</div>"
					+ "</div>"
					+"<div class='container-full-width' style='margin:0; padding:0; width:100%;'>"
						+"<div class='container-full-width' style='text-align:center; font-weight:bold; margin:0 8px; width: calc(78% - 16px);'>"
							+"<div class='container-full-width' style='margin:4px 0; padding:2px; width:100%; background:#222;'>"
								+ "<div class='container-full-width' style='margin:0; padding:2px; width:" + experiencePercentage + "%; background:"+level.getColour().toWebHexString()+";'></div>"
							+ "</div>"
						+ "</div>"
						+"<div class='container-full-width' style='text-align:center; margin:0 8px; width: calc(22% - 16px);'>"
							+ "<span style='color:"+level.getColour().toWebHexString()+";'>"+targetedCharacter.getFetishExperience(fetish)+" 经验</span>"
						+ "</div>"
//						+ "<div class='overlay no-pointer' id='"+Fetish.getIdFromFetish(fetish)+"_EXPERIENCE'></div>"
					+ "</div>"
				+ "</div>";
	}
	
	private static String getFetishDesireEntry(GameCharacter targetedCharacter, AbstractFetish fetish, FetishDesire desire) {
		boolean disabled = desire!=FetishDesire.FOUR_LOVE && targetedCharacter.hasFetish(fetish);
		
		return "<div class='square-button"+(disabled?" disabled":"")+"' id='"+Fetish.getIdFromFetish(fetish)+"_"+desire+"'"
					+ " style='"+(targetedCharacter.getBaseFetishDesire(fetish)==desire
								?"border:2px solid "+PresetColour.FETISH.getShades()[1]+";"
								:"")+"width:10%; margin:0 5%; float:left; cursor:pointer;'>"
				+ "<div class='square-button-content'>"+(targetedCharacter.getFetishDesire(fetish)==desire?desire.getSVGImage():desire.getSVGImageDesaturated())+"</div>"
				+ (targetedCharacter.hasFetish(fetish) && targetedCharacter.getFetishDesire(fetish)!=desire
					?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.8); border-radius:5px;'></div>"
					:targetedCharacter.getFetishDesire(fetish)!=desire
						?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.6); border-radius:5px;'></div>"
						:"")
			+ "</div>";
	}
	
	public static AbstractWorldType worldTypeMap = WorldType.DOMINION;

	private static void setMapResponseTabToCurrentMap() {
		AbstractWorldType world = Main.game.getPlayer().getWorldLocation();
		if(world.getWorldRegion()==WorldRegion.SUBMISSION) {
			Main.game.setResponseTab(1);
		} else if(world.getWorldRegion()==WorldRegion.FIELD_CITY) {
			Main.game.setResponseTab(2);
		} else {
			Main.game.setResponseTab(0);
		}
	}
	
	public static final DialogueNode MAP = new DialogueNode("地图", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getCurrentDialogueNode()!=MAP) {
				setMapResponseTabToCurrentMap();
			}
		}
		@Override
		public String getLabel() {
			return "地图: "+Util.capitaliseSentence(worldTypeMap.getName());
		}
		@Override
		public String getContent() {
			if(worldTypeMap==WorldType.WORLD_MAP) {
				return RenderingEngine.ENGINE.getFullMap(worldTypeMap, true, false);
			} else {
				return RenderingEngine.ENGINE.getFullMap(worldTypeMap, true, true);
			}
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "御城区";
			} else if(index==1) {
				return "屈城区";
			} else if(index==2) {
				return "Foloi Fields";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			int i=2;
			List<AbstractWorldType> worldTypes = new ArrayList<>(Main.getProperties().hasValue(PropertyValue.mapReveal)?WorldType.getAllWorldTypes():Main.game.getPlayer().getWorldsVisited());
			
			worldTypes.sort((w1, w2) -> (w1.getName().compareTo(w2.getName())));
			
			worldTypes.sort((w1, w2) -> w1.getMajorAreaIndex()-w2.getMajorAreaIndex());
			
			for(AbstractWorldType world : worldTypes) {
				boolean correctRegion = false;
				if(world.getWorldRegion()==WorldRegion.SUBMISSION) {
					correctRegion = responseTab==1;
				} else if(world.getWorldRegion()==WorldRegion.FIELD_CITY || world.getWorldRegion()==WorldRegion.FIELDS || world.getWorldRegion()==WorldRegion.YOUKO_FOREST) {
					correctRegion = responseTab==2;
				} else {
					correctRegion = responseTab==0;
				}
				
				if(correctRegion
						&& world != WorldType.WORLD_MAP
						&& (world != WorldType.EMPTY || Main.game.isDebugMode())
						&& world != WorldType.MUSEUM
						&& world != WorldType.MUSEUM_LOST) {
					if(index==i) {
						boolean playerPresent = Main.game.getPlayer().getWorldLocation()==world;
						String responseTitle = (world.isMajorArea()?"<b>":"")+Util.capitaliseSentence(world.getName())+(world.isMajorArea()?"</b>":"");
//						String responseTitle = Util.capitaliseSentence(world.getName());
						
						if(worldTypeMap==world) {
							return new Response(responseTitle, "你已经在查看"+world.getName()+"的地图了"+(playerPresent?"<br/>[style.colourGood(你已经在这个区域里了！)]":""), null);
							
						} else if(Main.game.getPlayer().getWorldsVisited().contains(world) || Main.getProperties().hasValue(PropertyValue.mapReveal)) { 
							return new Response(responseTitle, "查看"+world.getName()+"的地图"+(playerPresent?"<br/>[style.colourGood(你已经在这个区域里了！)]":""), MAP) {
								@Override
								public Colour getHighlightColour() {
									if(playerPresent) {
										return PresetColour.GENERIC_GOOD;
									}
									if(world==WorldType.EMPTY) {
										return PresetColour.GENERIC_BAD;
									}
									return super.getHighlightColour();
								}
								@Override
								public void effects() {
									Pathing.initPathingVariables();
									worldTypeMap = world;
								}
							};
							
						} else {
							return new Response("???", "你还没有发现这个区域", null);
						}
					}
					i++;
				}
			}
			if (index == 1) {
				boolean playerPresent = Main.game.getPlayer().getWorldLocation()==WorldType.WORLD_MAP;
				if(worldTypeMap==WorldType.WORLD_MAP) {
					return new Response("世界地图", "你已经在查看世界地图了"+(playerPresent?"<br/>[style.colourGood(你已经在这个区域里了！)]":""), null);
					
				} else if(Main.game.getPlayer().isDiscoveredWorldMap()) {
					return new Response("世界地图", "查看世界地图"+(playerPresent?"<br/>[style.colourGood(你已经在这个区域里了！)]":""), MAP) {
						@Override
						public Colour getHighlightColour() {
							if(playerPresent) {
								return PresetColour.GENERIC_GOOD;
							}
							return super.getHighlightColour();
						}
						@Override
						public void effects() {
							Pathing.initPathingVariables();
							worldTypeMap = WorldType.WORLD_MAP;
						}
					};
				} else {
					return new Response("世界地图", "你还没有发现世界地图！", null);
				}
			
			} else if (index == 0) {
				return new Response("返回", "返回到手机主页面。", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode LOITER_SELECTION = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "你在想你要在这个区域闲逛多久……"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "不在这个区域闲逛", MENU);
			}
			
			if(index == 1) {
				return new ResponseEffectsOnly("5分钟", "接下来5分钟在这个区域闲逛") {
					@Override
					public void effects() {
						loiter(5);
					}
				};
				
			} else if(index == 2) {
				return new ResponseEffectsOnly("15分钟", "接下来15分钟在这个区域闲逛") {
					@Override
					public void effects() {
						loiter(15);
					}
				};
				
			} else if(index == 3) {
				return new ResponseEffectsOnly("30分钟", "接下来30分钟在这个区域闲逛") {
					@Override
					public void effects() {
						loiter(30);
					}
				};
				
			} else if(index == 4) {
				return new ResponseEffectsOnly("1小时", " 接下来1小时在这个区域闲逛") {
					@Override
					public void effects() {
						loiter(60);
					}
				};
				
			} else if(index == 5) {
				return new ResponseEffectsOnly("2小时", "接下来2小时在这个区域闲逛") {
					@Override
					public void effects() {
						loiter(60*2);
					}
				};
				
			} else if(index == 6) {
				return new ResponseEffectsOnly("4小时", "接下来4小时在这个区域闲逛") {
					@Override
					public void effects() {
						loiter(60*4);
					}
				};
				
			} else if(index == 7) {
				return new ResponseEffectsOnly("6小时", "接下来6小时在这个区域闲逛") {
					@Override
					public void effects() {
						loiter(60*6);
					}
				};
				
			} else if(index == 8) {
				return new ResponseEffectsOnly("8小时", "接下来8小时在这个区域闲逛") {
					@Override
					public void effects() {
						loiter(60*8);
					}
				};
				
			} else if(index == 9) {
				return new ResponseEffectsOnly("12小时", "接下来12小时在这个区域闲逛") {
					@Override
					public void effects() {
						loiter(60*12);
					}
				};
				
			} else if(index == 10) {
				return new ResponseEffectsOnly("24小时", "接下来24小时在这个区域闲逛") {
					@Override
					public void effects() {
						loiter(60*24);
					}
				};
				
			} else if(index==11) {
				int timeUntilChange = Main.game.getMinutesUntilNextSunrise() + 5; // Add 5 minutes so that if the days are drawing in, you don't get stuck in a loop of always loitering to sunset/sunrise
				LocalDateTime[] sunriseSunset = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
				return new ResponseEffectsOnly("下个日出",
						"接下来" + (timeUntilChange >= 60 ?timeUntilChange / 60 + "小时" : "")
							+ (timeUntilChange % 60 != 0 ? timeUntilChange % 60 + "分钟" : "")
							+ "都在这个区域闲逛，直到日出后5分钟("+Units.time(sunriseSunset[0].plusMinutes(5))+")。"){
					@Override
					public void effects() {
						loiter(timeUntilChange);
					}
				};
				
			} else if(index==12) {
				int timeUntilChange = Main.game.getMinutesUntilNextSunset() + 5; // Add 5 minutes so that if the days are drawing in, you don't get stuck in a loop of always loitering to sunset/sunrise
				LocalDateTime[] sunriseSunset = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
				return new ResponseEffectsOnly("下个日落",
						"接下来" + (timeUntilChange >= 60 ?timeUntilChange / 60 + "小时" : "")
							+ (timeUntilChange % 60 != 0 ? timeUntilChange % 60 + "分钟" : "")
							+ "都在这个区域闲逛，直到日落后5分钟("+Units.time(sunriseSunset[1].plusMinutes(5))+")。"){
					@Override
					public void effects() {
						loiter(timeUntilChange);
					}
				};
				
			}
			
			return null;
		}

		private void loiter(int minutes) {
			String period = "";
			int hours = minutes / 60;
			int partialMinutes = minutes % 60;
			if (hours != 0) {
				period += (hours==1?"":Util.intToString(hours)) + " hour"+(hours==1?" ":"s ");
			}
			if (partialMinutes != 0) {
				period += Util.intToString(partialMinutes) + " minutes ";
			}
			if (minutes == 0) {
				period = "period ";
			}
			Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" +
					"<i>你接下来").append(period).append("都在闲逛，没做什么特别的事情……</i>").append("</p>");
			Main.game.getPlayer().setActive(false);
			Main.game.endTurn(60*minutes);
			Main.game.endTurnTimeTakenAddition = Main.game.endTurnTimeTaken;
			Main.game.getPlayer().setActive(true);
			Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue()));
			Main.game.endTurnTimeTakenAddition = Main.game.endTurnTimeTaken;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
}
