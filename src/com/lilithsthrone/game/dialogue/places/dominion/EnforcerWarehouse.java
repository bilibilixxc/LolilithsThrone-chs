package com.lilithsthrone.game.dialogue.places.dominion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.EnforcerWarehouseGuard;
import com.lilithsthrone.game.character.npc.dominion.Sean;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.dominion.SMClaireWarehouse;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5
 * @version 0.3.5
 * @author Innoxia
 */
public class EnforcerWarehouse {
	
	private static EnforcerWarehouseGuard arrestingGuard = null;
	private static List<GameCharacter> randomSexPartners = null;
	
	public static List<GameCharacter> getEnforcersPresent() {
		List<GameCharacter> list = new ArrayList<>();
		for(GameCharacter character : Main.game.getCharactersPresent()) {
			if(character instanceof EnforcerWarehouseGuard) {
				if(character.getHistory()==Occupation.NPC_ENFORCER_SWORD_INSPECTOR) {
					list.add(0, character);
				} else {
					list.add(character);
				}
			}
		}
		if(arrestingGuard!=null) {
			list.add(0, arrestingGuard);
		}
		return list;
	}
	
	private static EnforcerWarehouseGuard generateGuard(Occupation occupation) {
		Gender gender = Gender.getGenderFromUserPreferences(false, false);
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		
		// Make SWORD guards a predator subspecies:
		List <AbstractSubspecies> subspeciesAvailable = Util.newArrayListOfValues(
				Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_tiger"),
				Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_lion"),
				Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_leopard"),
				Subspecies.DOG_MORPH_DOBERMANN,
				Subspecies.DOG_MORPH_GERMAN_SHEPHERD,
				Subspecies.FOX_MORPH,
				Subspecies.WOLF_MORPH);
		
		for(AbstractSubspecies subspecies : subspeciesAvailable) {
			if(gender.isFeminine()) {
				if(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(subspecies)!=FurryPreference.HUMAN
						&& Main.getProperties().getSubspeciesFemininePreferencesMap().get(subspecies).getValue()>0) {
					subspeciesMap.put(subspecies, Main.getProperties().getSubspeciesFemininePreferencesMap().get(subspecies).getValue());
				}
			} else {
				if(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(subspecies)!=FurryPreference.HUMAN
						&& Main.getProperties().getSubspeciesMasculinePreferencesMap().get(subspecies).getValue()>0) {
					subspeciesMap.put(subspecies, Main.getProperties().getSubspeciesMasculinePreferencesMap().get(subspecies).getValue());
				}
			}
		}
		if(gender.isFeminine()) {
			for(Entry<AbstractSubspecies, FurryPreference> entry : Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					subspeciesMap.remove(entry.getKey());
				}
			}
		} else {
			for(Entry<AbstractSubspecies, FurryPreference> entry : Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					subspeciesMap.remove(entry.getKey());
				}
			}
		}
		
		int total = 0;
		for(Integer i : subspeciesMap.values()) {
			total += i;
		}
		
		if(subspeciesMap.isEmpty() || total==0) {
			try {
				// If there is no suitable subspecies, use one at random and make them partial (as humans cannot be in SWORD):
				EnforcerWarehouseGuard guard = new EnforcerWarehouseGuard(occupation, Util.randomItemFrom(subspeciesAvailable), RaceStage.PARTIAL, gender, false);
				Main.game.addNPC(guard, false);
				return guard;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			AbstractSubspecies species = Util.getRandomObjectFromWeightedMap(subspeciesMap);
			RaceStage stage = RaceStage.GREATER;
			if(gender.isFeminine()) {
				stage = Main.game.getCharacterUtils().getRaceStageFromPreferences(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(species), gender, species);
			} else {
				stage = Main.game.getCharacterUtils().getRaceStageFromPreferences(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(species), gender, species);
			}
			
			try {
				EnforcerWarehouseGuard guard = new EnforcerWarehouseGuard(occupation, species, stage, gender, false);
				Main.game.addNPC(guard, false);
				return guard;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		return null;
	}
	
	public static void initWarehouse() {
		List<String> usedAdjectives = Util.newArrayListOfValues("焦虑", "懦弱");
		
		// Add an Enforcer onto each of the Enforcer post tiles:
		for(Cell c : Main.game.getWorlds().get(WorldType.ENFORCER_WAREHOUSE).getCells(PlaceType.ENFORCER_WAREHOUSE_ENFORCER_GUARD_POST)) {
			EnforcerWarehouseGuard guard = generateGuard(Occupation.NPC_ENFORCER_SWORD_CONSTABLE);
			guard.setLocation(c.getType(), c.getLocation(), true);
			usedAdjectives.add(Main.game.getCharacterUtils().setGenericName(guard, "SWORD守卫", usedAdjectives));
		}
		
		// Add four Enforcers to the entrance:
		EnforcerWarehouseGuard guard = generateGuard(Occupation.NPC_ENFORCER_SWORD_INSPECTOR);
		guard.setLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_ENTRANCE, true);
		guard.setGenericName("SWORD督察");
		
		guard = generateGuard(Occupation.NPC_ENFORCER_SWORD_SERGEANT);
		guard.setLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_ENTRANCE, true);
		usedAdjectives.add(Main.game.getCharacterUtils().setGenericName(guard, "SWORD守卫", usedAdjectives));
		
		for(int i=0; i<2; i++) {
			guard = generateGuard(Occupation.NPC_ENFORCER_SWORD_CONSTABLE);
			guard.setLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_ENTRANCE, true);
			usedAdjectives.add(Main.game.getCharacterUtils().setGenericName(guard, "SWORD守卫", usedAdjectives));
		}
	}
	
	private static Response getClaireCratesSexResponse() {
		if(Main.game.getNpc(Claire.class).getLust()>75) {
			return new ResponseSex(
					"提供帮助",
					"附近有几个堆起的板条箱，正是个合适的位置，可以让你帮克莱尔缓解一下……",
					true,
					true,
					new SMClaireWarehouse(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.STANDING_WALL)),
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Claire.class), SexSlotAgainstWall.BACK_TO_WALL))),
					null,
					null,
					AFTER_CLAIRE_SEX,
					UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "START_CLAIRE_SEX"));
			
		} else if(Main.game.getNpc(Claire.class).getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>0) {
			return new Response(
					"再来一次",
					"看看克莱尔还不想不想在板条箱后面再做一次……",
					ASK_CLAIRE_REPEAT_SEX);
			
		} else {
			return null;
		}
	}
	
	//---- ENCLOSURE DIALOGUE ----//
	
	public static final DialogueNode ENCLOSURE_TELEPORT_PADS = new DialogueNode("传送板", "", false) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.clairePadsInvestigated);
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_TELEPORT_PADS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.clairePadsInvestigated)) {
					return new Response("调查传送板", "仔细看一下传送板，找找有没有克莱尔要找的“精华接入口”。", ENCLOSURE_TELEPORT_PADS_INVESTIGATE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.clairePadsInvestigated, true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode ENCLOSURE_TELEPORT_PADS_INVESTIGATE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_TELEPORT_PADS_INVESTIGATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("严肃认真",
						"这么早就打退堂鼓可不是克莱尔的风格。告诉她得振作起来。<br/>[style.italicsGood(你觉得直截了当一些对克莱尔最有帮助。)]",
						ENCLOSURE_TELEPORT_PADS_INVESTIGATE_RESPOND_TO_CLAIRE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_TELEPORT_PADS_PULL_TOGETHER"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Claire.class).incrementAffection(Main.game.getPlayer(), 15));
					}
				};
				
			} else if(index==2) {
				return new Response("安慰",
						"告诉克莱尔先不要绝望，船到桥头自然直。<br/>[style.italicsMinorGood(克莱尔会喜欢这种方式的。)]",
						ENCLOSURE_TELEPORT_PADS_INVESTIGATE_RESPOND_TO_CLAIRE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_TELEPORT_PADS_REASSURE"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Claire.class).incrementAffection(Main.game.getPlayer(), 10));
					}
				};
				
			} else if(index==3) {
				return new Response("拥抱",
						"或许克莱尔只是需要一个拥抱，让她冷静下来？<br/>[style.italicsMinorGood(克莱尔或许会觉得你做得有些过，但她会明白你的心意的。)]",
						ENCLOSURE_TELEPORT_PADS_INVESTIGATE_RESPOND_TO_CLAIRE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_TELEPORT_PADS_HUG"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Claire.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode ENCLOSURE_TELEPORT_PADS_INVESTIGATE_RESPOND_TO_CLAIRE = new DialogueNode("", "", false) {
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
			return ENCLOSURE_TELEPORT_PADS.getResponse(responseTab, index);
		}
	};

	
	public static final DialogueNode ENCLOSURE = new DialogueNode("仓库", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	
	public static final DialogueNode ENCLOSURE_SHELVING = new DialogueNode("货架", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_SHELVING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireEnclosureEscaped)) {
				if(index==1) {
					return new Response("移动货架", "移动货架，尝试打开一条路", ENCLOSURE_SHELVING_MOVE) {
						@Override
						public void effects() {
							Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
							Cell c = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX()+1, Main.game.getPlayer().getLocation().getY());
							c.getPlace().setPlaceType(PlaceType.ENFORCER_WAREHOUSE_CORRIDOR);
							c.getPlace().setName(PlaceType.ENFORCER_WAREHOUSE_CORRIDOR.getName());
							Main.game.getPlayerCell().getInventory().addItem(Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.TELEPORT)));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode ENCLOSURE_SHELVING_MOVE = new DialogueNode("仓库", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_SHELVING_MOVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("学习法术", "浏览一遍传送术法术书，尽快学会传送术。", ENCLOSURE_SHELVING_DRAG_TELEPORT_LEARNED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_SHELVING_DRAG_TELEPORT_LEARNED_START"));
						Main.game.getTextStartStringBuilder().append(
								"<span style='border:0; padding:0; text-align:center;'><i>"
									+Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.TELEPORT)), Main.game.getPlayer(), true)
								+"</i></span>");
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_SHELVING_DRAG_TELEPORT_LEARNED_END"));
						// Removed as it was just annoying later on when the telepathy spell is meant to be available:
//						int manaCost = (int)Spell.TELEPORT.getModifiedCost(Main.game.getPlayer());
//						Main.game.getPlayer().incrementMana(-manaCost);
//						Main.game.getTextStartStringBuilder().append(
//								UtilText.parse(Main.game.getPlayer(),
//										"<p style='text-align:center;'><b>[npc.Name] [style.colourBad([npc.verb(lose)])] "+(manaCost)+" "+Attribute.MANA_MAXIMUM.getName()+"!</b></p>"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ENCLOSURE_SHELVING_DRAG_TELEPORT_LEARNED = new DialogueNode("", "", true) {
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
				return new Response("继续向前", "进入仓库，寻找出口。", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.claireEnclosureEscaped, true);
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX()+1, Main.game.getPlayer().getLocation().getY()));
					}
				};
			}
			return null;
		}
	};

	

	//---- CORRIDOR DIALOGUE ----//
	
	public static final DialogueNode CORRIDOR = new DialogueNode("走廊", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			List<String> dangerousDirections = new ArrayList<>();
			Vector2i location = Main.game.getPlayer().getLocation();
			boolean isMainEntrance = false;
			Cell c = Main.game.getActiveWorld().getCell(location.getX(), location.getY()+1);
			if(c!=null && c.getPlace().getPlaceType().isDangerous()) {
				dangerousDirections.add("北边");
				if(c.getPlace().getPlaceType().equals(PlaceType.ENFORCER_WAREHOUSE_ENTRANCE)) {
					isMainEntrance = true;
				}
			}
			c = Main.game.getActiveWorld().getCell(location.getX(), location.getY()-1);
			if(c!=null && c.getPlace().getPlaceType().isDangerous()) {
				dangerousDirections.add("南边");
				if(c.getPlace().getPlaceType().equals(PlaceType.ENFORCER_WAREHOUSE_ENTRANCE)) {
					isMainEntrance = true;
				}
			}
			c = Main.game.getActiveWorld().getCell(location.getX()+1, location.getY());
			if(c!=null && c.getPlace().getPlaceType().isDangerous()) {
				dangerousDirections.add("东边");
				if(c.getPlace().getPlaceType().equals(PlaceType.ENFORCER_WAREHOUSE_ENTRANCE)) {
					isMainEntrance = true;
				}
			}
			c = Main.game.getActiveWorld().getCell(location.getX()-1, location.getY());
			if(c!=null && c.getPlace().getPlaceType().isDangerous()) {
				dangerousDirections.add("西边");
				if(c.getPlace().getPlaceType().equals(PlaceType.ENFORCER_WAREHOUSE_ENTRANCE)) {
					isMainEntrance = true;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CORRIDOR"));
			if(isMainEntrance) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CORRIDOR_ENTRANCE_WARNING"));
				
			} else {
				if(!dangerousDirections.isEmpty()) {
					sb.append("<p style='test-align:center; color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'><i>");
						if(dangerousDirections.size()>1) {
							sb.append(Util.stringsToStringList(dangerousDirections, false)+"设有岗哨！进入这些区域必定会引发战斗！");
						} else {
							sb.append(Util.stringsToStringList(dangerousDirections, false)+"设有岗哨！进入这个区域必定会引发战斗！");
						}
					sb.append("</i></p>");
				}
			}
			
			return sb.toString();
			
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode CLAIRE_WARNING = new DialogueNode("走廊", "", true) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireWarning);
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireWarning)) {
				NPC guard = Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.ENFORCER_WAREHOUSE).getClosestCell(Main.game.getPlayer().getLocation(), PlaceType.ENFORCER_WAREHOUSE_ENFORCER_GUARD_POST)).get(0);
				return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CLAIRE_WARNING", guard);
			} else {
				return CORRIDOR.getContent();
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireWarning)) {
				if(index==1) {
					return new Response("继续", "继续穿越仓库……", CLAIRE_WARNING) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.claireWarning, true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_CLAIRE_SEX = new DialogueNode("解脱", "你帮克莱尔处理了她的性欲……", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_CLAIRE_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续穿越仓库……", Main.game.getDefaultDialogue());
			}
			return null;
		}
	};

	public static final DialogueNode ASK_CLAIRE_REPEAT_SEX = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ASK_CLAIRE_REPEAT_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue().getResponse(responseTab, index);
		}
	};
	
	
	
	
	
	//---- CRATES DIALOGUE ----//
	
	public static final DialogueNode CRATES = new DialogueNode("板条箱", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_SEARCHED) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATE_SEARCHED"));
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES"));

			if(Main.game.getNpc(Claire.class).getLust()>75 || Main.game.getNpc(Claire.class).getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>0) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_RECESS"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_SEARCHED
						|| Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_ARK_SEARCHED) {
					return new Response("翻找板条箱", "翻过该区域所有开封的板条箱了！", null);
					
				} else {
					return new Response("翻找板条箱", "翻过这些板条箱，看看能否找到些有用的东西。", CRATES_SEARCH) {
						@Override
						public void effects() {
							
							double rnd = Math.random();
							if(rnd<0.25) {
								List<AbstractClothingType> clothingToGenerate = new ArrayList<>(ClothingType.getAllClothing());
								clothingToGenerate.removeIf((clothing) -> !clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN));
								
								AbstractClothing clothing = Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), false);
								for(int i=0; i<Util.random.nextInt(4); i++) {
									TFModifier rndMod = TFModifier.getClothingAttributeList().get(Util.random.nextInt(TFModifier.getClothingAttributeList().size()));
									clothing.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod, TFPotency.getRandomWeightedPositivePotency(), 0));
								}
								
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(clothing, 1, false, true));
								
							} else if(rnd < 0.5) {
								List<AbstractWeaponType> weaponToGenerate = new ArrayList<>(WeaponType.getAllWeapons());
								weaponToGenerate.removeIf((weapon) -> (weapon.getRarity()!=Rarity.RARE && weapon.getRarity()!=Rarity.EPIC) || !weapon.getItemTags().contains(ItemTag.SOLD_BY_VICKY));
								
								AbstractWeapon weapon = Main.game.getItemGen().generateWeapon(Util.randomItemFrom(weaponToGenerate));
								
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(weapon, 1, false, true));
								
							} else {
								List<AbstractItemType> itemTypes = Util.newArrayListOfValues(ItemType.getItemTypeFromId("BOTTLED_ESSENCE_DEMON"), ItemType.getItemTypeFromId("innoxia_race_demon_liliths_gift"), ItemType.FETISH_UNREFINED);
								AbstractItem item = Main.game.getItemGen().generateItem(Util.randomItemFrom(itemTypes));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(item, 3+Util.random.nextInt(6), false, true));
							}

							if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_ARK) {
								Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.ENFORCER_WAREHOUSE_CRATES_ARK_SEARCHED);
								Main.game.getPlayerCell().getPlace().setName(PlaceType.ENFORCER_WAREHOUSE_CRATES_ARK_SEARCHED.getName());
								
							} else {
								Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.ENFORCER_WAREHOUSE_CRATES_SEARCHED);
								Main.game.getPlayerCell().getPlace().setName(PlaceType.ENFORCER_WAREHOUSE_CRATES_SEARCHED.getName());
							}
						}
					};
				}
				
			} else if(index==2) {
				return getClaireCratesSexResponse();
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CRATES_SEARCH = new DialogueNode("板条箱", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_SEARCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CRATES.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode CRATES_ARK = new DialogueNode("板条箱", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(Main.game.isSillyModeEnabled()) {
				if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_ARK_SEARCHED) {
					return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_ARK_SEARCHED");
				}
				return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_ARK");
			}
			return CRATES.getContent();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CRATES.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode CRATES_LUST_WEAPON = new DialogueNode("“绝密”板条箱", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireObtainedLightningGlobe)) {
				return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_LUST_WEAPON_SEARCHED");
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_LUST_WEAPON"));

			if(Main.game.getNpc(Claire.class).getLust()>75 || Main.game.getNpc(Claire.class).getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>0) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_RECESS"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireObtainedLightningGlobe)) {
					return new Response("翻找板条箱", "翻过该区域所有开封的板条箱了！", null);
				} else {
					return new Response("翻找板条箱", "在“绝密”板条箱中翻找，看看有没有什么能帮助你们逃离仓库的东西。", CRATES_LUST_WEAPON_SEARCH) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.claireObtainedLightningGlobe, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Claire.class).setLust(80));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementLust(20, false));

							Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.ENFORCER_WAREHOUSE_CRATES_SEARCHED);
							Main.game.getPlayerCell().getPlace().setName(PlaceType.ENFORCER_WAREHOUSE_CRATES_SEARCHED.getName());
						}
					};
				}
				
			} else if(index==2) {
				return getClaireCratesSexResponse();
			}
			
			return null;
		}
	};

	public static final DialogueNode CRATES_LUST_WEAPON_SEARCH = new DialogueNode("“绝密”板条箱", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_LUST_WEAPON_SEARCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {	
				return new Response("帮忙", "询问克莱尔她是不是还好。", CRATES_LUST_WEAPON_OBTAINED) {
					@Override
					public void effects() {
						AbstractItem item = Main.game.getItemGen().generateItem(ItemType.getItemTypeFromId("BOTTLED_ESSENCE_DEMON"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_lightningGlobe_lightning_globe", DamageType.LUST), 1, false, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(item, 3, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CRATES_LUST_WEAPON_OBTAINED = new DialogueNode("“绝密”板条箱", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_LUST_WEAPON_OBTAINED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "相信克莱尔足以自控，继续前进。", CRATES_LUST_WEAPON_OBTAINED_CONTINUE) {
					@Override
					public void effects() {
//						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_CORRIDOR, false);
					}
				};
				
			} else if(index==2) {
				return getClaireCratesSexResponse();
			}
			
			return null;
		}
	};

	public static final DialogueNode CRATES_LUST_WEAPON_OBTAINED_CONTINUE = new DialogueNode("“绝密”板条箱", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_LUST_WEAPON_OBTAINED_CONTINUE"));
			sb.append(CRATES_LUST_WEAPON.getContent());
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CRATES_LUST_WEAPON.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHELVES_SPELL_BOOK = new DialogueNode("货架", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK_SEARCHED) {
				return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "SHELVES_SPELL_BOOK_SEARCHED");
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "SHELVES_SPELL_BOOK"));

			if(Main.game.getNpc(Claire.class).getLust()>75 || Main.game.getNpc(Claire.class).getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>0) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_RECESS"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK_SEARCHED) {
					return new Response("搜索货架", "你已经搜索过该区域的所有货架了！", null);
					
				} else {
					return new Response("搜索货架", "搜索这些货架，看看能否找到些有用的东西。", SHELVES_SPELL_BOOK_SEARCH) {
						@Override
						public void effects() {
							Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK_SEARCHED);
							Main.game.getPlayerCell().getPlace().setName(PlaceType.ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK_SEARCHED.getName());
							
							AbstractItem item = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.TELEPATHIC_COMMUNICATION));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(item, 1, false, true));
						}
					};
				}
				
			} else if(index==2) {
				return getClaireCratesSexResponse();
			}
			
			return null;
		}
	};
	
	public static final DialogueNode SHELVES_SPELL_BOOK_SEARCH = new DialogueNode("板条箱", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_SPELL_BOOK_SEARCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHELVES_SPELL_BOOK.getResponse(responseTab, index);
		}
	};

	
	
	//---- ENFORCER POST DIALOGUE ----//
	
	public static final DialogueNode ENFORCER_GUARD_POST = new DialogueNode("执法者岗哨", "", true) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().warehouseDefeatedIDs.contains(getEnforcersPresent().get(0).getId());
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().warehouseDefeatedIDs.contains(getEnforcersPresent().get(0).getId())) {
				return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENFORCER_GUARD_POST_CLEARED", getEnforcersPresent());
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENFORCER_GUARD_POST", getEnforcersPresent());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			GameCharacter guard = getEnforcersPresent().get(0);
			if(!Main.game.getDialogueFlags().warehouseDefeatedIDs.contains(guard.getId())) {
				if(index==1) {
//					return new Response("Back off",
//							"Step back into the warehouse and re-think how you're going to get out of here.",
//							Main.game.getNpc(Claire.class).getCell().getPlace().getPlaceType().getDialogue(false)) {
//						@Override
//						public void effects() {
//							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENFORCER_GUARD_POST_BACK_OFF", getEnforcersPresent()));
//							Main.game.getPlayer().setLocation(Main.game.getNpc(Claire.class), false);
//						}
//					};
//					
//				} else if(index==2) {
					return new ResponseCombat("自卫",
							UtilText.parse(guard, "与这个好战的[npc.race]SWORD守卫战斗。"),
							(NPC) guard,
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENFORCER_GUARD_POST_PLAYER_FIGHT_START", getEnforcersPresent())),
									new Value<>(guard, UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENFORCER_GUARD_POST_GUARD_FIGHT_START", getEnforcersPresent()))));
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_GUARD_COMBAT_VICTORY = new DialogueNode("胜利", "你成功击败了SWORD守卫！", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_GUARD_COMBAT_VICTORY", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			GameCharacter guard = getEnforcersPresent().get(0);
			if(index==1) {
				return new Response("继续",
						UtilText.parse(guard, "把这个被击败的[npc.race]丢在身后，继续穿过仓库。"),
						Main.game.getDefaultDialogue());
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_GUARD_COMBAT_DEFEAT = new DialogueNode("落败", "SWORD守卫证明你还是不行！", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_GUARD_COMBAT_DEFEAT", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			GameCharacter guard = getEnforcersPresent().get(0);
			if(index==1) {
				return new Response("被拘留……",
						UtilText.parse(guard, "被击败后，你无可抵抗，只能被[npc.name(the)]拖走，迎接你的命运……"),
						AFTER_GUARD_COMBAT_DEFEAT_ENTRANCE) {
					@Override
					public void effects() {
						arrestingGuard = (EnforcerWarehouseGuard) guard;
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_ENTRANCE, false);
						arrestingGuard.setLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_ENTRANCE, false);
						Main.game.getNpc(Claire.class).returnToHome();
						Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_GUARD_COMBAT_DEFEAT_ENTRANCE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_GUARD_COMBAT_DEFEAT_ENTRANCE", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isNonConEnabled()) {
					return new Response("颈手枷",
							UtilText.parse(arrestingGuard, "[npc.Name(the)]把你拖到了颈手枷旁……"),
							AFTER_COMBAT_DEFEAT_SENT_TO_STOCKS) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS, false);
							arrestingGuard.setLocation(Main.game.getPlayer(), false);
							Main.game.getNpc(Claire.class).returnToHome();
							Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.TELEPORTING_CAUGHT));
						}
					};
					
				} else {
					return new Response("被锁住",
							UtilText.parse(arrestingGuard, "[npc.Name(the)]把你拖进了执法者总部，丢入了监狱……"),
							AFTER_COMBAT_DEFEAT_SENT_TO_CELLS) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELL, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_COMBAT_DEFEAT_SENT_TO_CELLS", arrestingGuard));
							arrestingGuard.returnToHome();
							Main.game.getNpc(Claire.class).returnToHome();
							Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.TELEPORTING_CAUGHT));
						}
					};
				}
			}
			return null;
		}
	};
	
	
	
	//---- ENTRANCE DIALOGUE ----//
	
	public static final DialogueNode ENTRANCE = new DialogueNode("仓库入口", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {

			if(index==0) {
				return new Response("放弃",
						"回到仓库里，重新思考该如何离开这里。",
						CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_BACK_OFF"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CORRIDOR"));
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_CORRIDOR, false);
					}
				};
				
			} else if(index==1) {
				return new ResponseCombat("战斗",
						"要摆脱这个困境，唯一的方法就是在战斗中击败这些SWORD执法者！",
						(NPC) getEnforcersPresent().get(0),
						getEnforcersPresent(),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_PLAYER_CHALLENGE", getEnforcersPresent())),
								new Value<>(getEnforcersPresent().get(0), UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_GUARD_RESPONSE_1", getEnforcersPresent().get(0))),
								new Value<>(getEnforcersPresent().get(1), UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_GUARD_RESPONSE_2", getEnforcersPresent().get(1))),
								new Value<>(getEnforcersPresent().get(2), UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_GUARD_RESPONSE_3", getEnforcersPresent().get(2))),
								new Value<>(getEnforcersPresent().get(3), UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_GUARD_RESPONSE_4", getEnforcersPresent().get(3)))));
				
			} else if(index==2) {
				if(!Main.game.getPlayer().hasWeaponType(WeaponType.getWeaponTypeFromId("innoxia_lightningGlobe_lightning_globe"), true)) {
					return new Response("闪电球", "你没有在仓库中找到闪电球……", null);
				}
				return new Response("闪电球",
						"让闪电球过载，从地上滚到执法者身旁。从中释放的催情能量理应会让他们欲火中烧，无视站岗的任务，开始相互做爱。",
						ENTRANCE_LIGHTNING_GLOBE) {
					@Override
					public void effects() {
						for(GameCharacter c : getEnforcersPresent()) {
							c.setLust(100);
						}
						Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'><i>你[style.colourBad(失去了)]<b>"+Main.game.getPlayer().getMana()+"</b>[style.colourAura(灵气)]！</i></p>");
						Main.game.getPlayer().setMana(0);
					}
				};
				
			} else if(index==3) {
				if(Main.game.getPlayer().hasSpell(Spell.TELEPATHIC_COMMUNICATION)) {
					if(Main.game.getPlayer().getMana()>=Spell.TELEPATHIC_COMMUNICATION.getModifiedCost(Main.game.getPlayer())) {
						return new Response("传音伎俩",
								UtilText.parse(getEnforcersPresent(),
										"对[npc.name(the)]释放法术“"+Spell.TELEPATHIC_COMMUNICATION.getName()+"”，并借助链接传递一条求救信息，假装有人在仓库深处需要救助……"),
								ENTRANCE_TELEPATHIC_TRICKERY);
						
					} else {
						return new Response("传音伎俩",
								"你没有足够的灵气释放“"+Spell.TELEPATHIC_COMMUNICATION.getName()+"”……",
								null);
					}
					
				} else {
					return new Response("传音伎俩",
							"你还没有学会“"+Spell.TELEPATHIC_COMMUNICATION.getName()+"”……",
							null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_ENTRANCE_VICTORY = new DialogueNode("胜利", "你成功击败了SWORD守卫！", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_ENTRANCE_VICTORY", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("逃脱",
						"你和克莱尔最终成功逃出了仓库！",
						AFTER_ENTRANCE_VICTORY_ESCAPE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ);
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.SIDE_UTIL_COMPLETE));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_ENTRANCE_VICTORY_ESCAPE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_ENTRANCE_VICTORY_ESCAPE", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("道别",
						"跟克莱尔道别，继续你的旅程……",
						Main.game.getDefaultDialogue()) {
					@Override
					public void effects() {
						Main.game.getNpc(Claire.class).returnToHome();
						Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_ENTRANCE_VICTORY_ESCAPE_END"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_ENTRANCE_DEFEAT = new DialogueNode("落败", "你还是难以同时抵挡四个SWORD执法者。你瘫倒在地，一败涂地。", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_ENTRANCE_DEFEAT", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("颈手枷",
						UtilText.parse(getEnforcersPresent().get(0), "[npc.Name(the)]把你拖到了颈手枷旁……"),
						AFTER_COMBAT_DEFEAT_SENT_TO_STOCKS) {
					@Override
					public void effects() {
						arrestingGuard = (EnforcerWarehouseGuard) getEnforcersPresent().get(0);
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS, false);
						arrestingGuard.setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Claire.class).returnToHome();
						Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.TELEPORTING_CAUGHT));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ENTRANCE_LIGHTNING_GLOBE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_LIGHTNING_GLOBE", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("逃脱",
						"你和克莱尔最终成功逃出了仓库！",
						AFTER_ENTRANCE_VICTORY_ESCAPE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ);
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.SIDE_UTIL_COMPLETE));
					}
				};
			}
			return null;
		}
	};
	
	
	public static final DialogueNode ENTRANCE_TELEPATHIC_TRICKERY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_TELEPATHIC_TRICKERY", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("逃脱",
						"你和克莱尔最终成功逃出了仓库！",
						AFTER_ENTRANCE_VICTORY_ESCAPE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ);
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.SIDE_UTIL_COMPLETE));
					}
				};
				
			}
			return null;
		}
	};
	
	
	
	//---- DEFEATED ----//
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT_SENT_TO_STOCKS = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setCaptive(true);
			Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getNpc(Sean.class), false, false);
		}
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_COMBAT_DEFEAT_SENT_TO_STOCKS", arrestingGuard);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("锁在颈手枷上",
						UtilText.parse(arrestingGuard, "你无能为力，只能眼睁睁地看着[npc.name]对你为所欲为。"),
						false,
						false,
						new SexManagerDefault(
								SexPosition.STOCKS,
								Util.newHashMapOfValues(new Value<>(arrestingGuard,
										arrestingGuard.hasPenis()||!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
											?SexSlotStocks.BEHIND_STOCKS
											:SexSlotStocks.RECEIVING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
							@Override
							public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
								return Util.newHashMapOfValues(
										new Value<>(ImmobilisationType.STOCKS,
												Util.newHashMapOfValues(
														new Value<>(arrestingGuard,
																Util.newHashSetOfValues(Main.game.getPlayer())))));
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.isPlayer();
							}
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.NONE;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								if(character.isPlayer()) {
									return false;
								}
								return super.isAbleToRemoveOthersClothing(character, clothing);
							}
							@Override
							public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
								return !equippingCharacter.isPlayer();
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
						},
						null,
						null,
						AFTER_STOCKS_ENFORCER_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "START_STOCKS_ENFORCER_SEX"));
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_STOCKS_ENFORCER_SEX = new DialogueNode("结束", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public void applyPreParsingEffects() {
			arrestingGuard.returnToHome();
		}
		@Override
		public String getDescription() {
			return UtilText.parse(arrestingGuard, "[npc.Name]和你做够了……");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_STOCKS_ENFORCER_SEX", arrestingGuard);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("陌生人接近",
						"几个陌生人上前来，准备和你爽一爽……",
						STOCKS_RANDOMS) {
					@Override
					public void effects() {
						randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(Main.game.getPlayer(), true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode STOCKS_RANDOMS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "STOCKS_RANDOMS", randomSexPartners);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("再次被使用",
						"有两个陌生人站好位置，开始使用你解决性欲……",
						false,
						false,
						new SexManagerDefault(
								SexPosition.STOCKS,
								Util.newHashMapOfValues(
										new Value<>(randomSexPartners.get(0), SexSlotStocks.BEHIND_STOCKS),
										new Value<>(randomSexPartners.get(1), SexSlotStocks.RECEIVING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
							@Override
							public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
								return Util.newHashMapOfValues(
										new Value<>(ImmobilisationType.STOCKS,
												Util.newHashMapOfValues(
														new Value<>(randomSexPartners.get(0),
																Util.newHashSetOfValues(Main.game.getPlayer())))));
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.isPlayer();
							}
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.NONE;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								if(character.isPlayer()) {
									return false;
								}
								return super.isAbleToRemoveOthersClothing(character, clothing);
							}
							@Override
							public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
								return !equippingCharacter.isPlayer();
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
						},
						null,
						null,
						AFTER_STOCKS_RANDOM_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "START_STOCKS_RANDOM_SEX", randomSexPartners)) {
					@Override
					public void effects() {
						arrestingGuard.returnToHome();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_STOCKS_RANDOM_SEX = new DialogueNode("结束", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getDescription() {
			return UtilText.parse(randomSexPartners, "[npc.Name]和[npc2.name]跟你做完了……");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_STOCKS_RANDOM_SEX", randomSexPartners);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被救下",
						"监管监狱的执法者解开了锁，将你放归自由。",
						STOCKS_SET_FREE) {
					@Override
					public void effects() {
						for(GameCharacter character : randomSexPartners) {
							Main.game.banishNPC((NPC) character);
						}
						Main.game.getNpc(Claire.class).returnToHome();
						Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.SIDE_UTIL_COMPLETE));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode STOCKS_SET_FREE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "STOCKS_SET_FREE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续你的旅程……", Main.game.getDefaultDialogue()) {
					@Override
					public void effects() {
						Main.game.getPlayer().setCaptive(false);
						Main.game.getPlayer().equipAllClothingFromHoldingInventory();
					}
				};
			}
			return null;
		}
	};


	public static final DialogueNode AFTER_COMBAT_DEFEAT_SENT_TO_CELLS = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setCaptive(true);
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
				return new Response("等待",
						"你无事可做，只得等人将你解救下来……",
						AFTER_COMBAT_DEFEAT_CELLS_WAITING) {
					@Override
					public void effects() {
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_COMBAT_DEFEAT_CELLS_WAITING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 120*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_COMBAT_DEFEAT_CELLS_WAITING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被救下",
						"克莱尔命令看管监狱的执法者给颈手枷解锁，将你放归自由。",
						CELLS_SET_FREE) {
					@Override
					public void effects() {
						Main.game.getNpc(Claire.class).returnToHome();
						Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.SIDE_UTIL_COMPLETE));
						Main.game.getPlayer().setCaptive(false);
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ, false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CELLS_SET_FREE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CELLS_SET_FREE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续你的旅程……", Main.game.getDefaultDialogue());
			}
			return null;
		}
	};

}
