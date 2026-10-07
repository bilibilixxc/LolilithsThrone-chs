package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.moves.CombatMove;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.BlockedParts;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.clothing.Sticker;
import com.lilithsthrone.game.inventory.clothing.StickerCategory;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.comparators.ClothingZLayerComparator;

/**
 * @since 0.1.0
 * @version 0.4.10.10
 * @author Innoxia
 */
public class InventoryDialogue {
	
	private static final int IDENTIFICATION_PRICE = 1000;
	private static final int IDENTIFICATION_ESSENCE_PRICE = 15;
	
	private static AbstractItem item;
	private static AbstractClothing clothing;
	private static AbstractWeapon weapon;
	private static InventorySlot weaponSlot;
	private static GameCharacter owner;
	
	private static NPC inventoryNPC;
	private static InventoryInteraction interactionType;

	private static StringBuilder inventorySB = new StringBuilder();

	private static boolean buyback;

	private static int buyBackPrice;
	private static int buyBackIndex;

	public static DamageType damageTypePreview;
	
	public static List<Colour> dyePreviews;
	public static String dyePreviewPattern;
	public static List<Colour> dyePreviewPatternColours;
	
	public static Map<StickerCategory, Sticker> dyePreviewStickers;

	public static Map<String, String> getDyePreviewStickersAsStrings() {
		Map<String, String> stickerIds = new HashMap<>();
		for(Entry<StickerCategory, Sticker> entry : dyePreviewStickers.entrySet()) {
			stickerIds.put(entry.getKey().getId(), entry.getValue().getId());
		}
		return stickerIds;
	}
	
	private static void resetClothingDyeColours() {
		dyePreviews = new ArrayList<>();
		dyePreviews.addAll(clothing.getColours());
		
		dyePreviewPattern = clothing.getPattern();

		dyePreviewPatternColours = new ArrayList<>();
		dyePreviewPatternColours.addAll(clothing.getPatternColours());
		
		dyePreviewStickers = new HashMap<>(clothing.getStickersAsObjects());
	}

	private static void resetWeaponDyeColours() {
		dyePreviews = new ArrayList<>();
		dyePreviews.addAll(weapon.getColours());
		
		damageTypePreview = weapon.getDamageType();
	}
	
	private static String inventoryView() {
		inventorySB = new StringBuilder();
		
		inventorySB.append(RenderingEngine.ENGINE.getInventoryPanel(inventoryNPC, buyback));
		
		return inventorySB.toString();
	}

	private static void equipAll(GameCharacter character) {
		List<AbstractClothing> zlayerClothing = new ArrayList<>(character.getAllClothingInInventory().keySet());
		zlayerClothing.removeIf((c) -> c.isEnchantmentKnown() && c.isSealed());
		zlayerClothing.sort(new ClothingZLayerComparator().reversed());
		Set<InventorySlot> slotsTaken = new HashSet<>();

		for(AbstractClothing c : character.getClothingCurrentlyEquipped()) {
			slotsTaken.add(c.getSlotEquippedTo());
		}

		for(AbstractClothing c : zlayerClothing) {
			if(!slotsTaken.contains(c.getClothingType().getEquipSlots().get(0))) {
				Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+character.equipClothingFromInventory(c, true, character, character)+"</p>");
				slotsTaken.add(c.getClothingType().getEquipSlots().get(0));
			}
		}
	}

	private static String unequipAll(GameCharacter character) {
		StringBuilder sb = new StringBuilder();
		
//		for(int i=0; i<character.getArmRows(); i++) {
//			sb.append(character.unequipMainWeapon(i, false, character.isPlayer()));
//			sb.append(character.unequipOffhandWeapon(i, false, character.isPlayer()));
//		}
		
		List<AbstractClothing> zlayerClothing = new ArrayList<>(character.getClothingCurrentlyEquipped());
		zlayerClothing.sort(new ClothingZLayerComparator());
		
		for(AbstractClothing c : zlayerClothing) { 
			if((!Main.game.isInSex()
					|| (!c.getSlotEquippedTo().isJewellery()
							&& !c.isCondom()
							&& (!character.hasPerkAnywhereInTree(Perk.SPECIAL_SHORT_SIGHTED) || !c.getItemTags().contains(ItemTag.PRESCRIPTION_GLASSES))))
					&& !c.isMilkingEquipment()) {
				if (c.isDiscardedOnUnequip(null)) {
					character.unequipClothingIntoVoid(c, true, Main.game.getPlayer());
				} else {
					if(Main.game.isInNewWorld()) {
						character.unequipClothingIntoInventory(c, true, Main.game.getPlayer());
					} else {
						character.unequipClothingOntoFloor(c, true, Main.game.getPlayer());
					}
				}
				sb.append("<p style='text-align:center;'>"+character.getUnequipDescription()+"</p>");
			}
		}
		
		return sb.toString();
	}
	
	private static String getEnchantmentNotDiscoveredText(String item) {
		StringBuilder sb = new StringBuilder();
		sb.append("你还不知道如何附魔");
		sb.append(item);
		sb.append("……");
		sb.append("<br/>");
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
			sb.append("[style.italicsArcane(你应该问一问莉莱雅精华的事情……)]");
		} else {
			sb.append("[style.italicsArcane(你需要在战斗或性爱后吸收精华，或者通过购买精华来发现更多关于附魔的知识……)]");
		}
		return sb.toString();
	}
	
	private static String getClothingBlockingRemovalText(GameCharacter equipTarget, String equipVerb) {
		StringBuilder sb = new StringBuilder();
		AbstractClothing blockingClothing = equipTarget.getBlockingClothing();

		sb.append("你不能");
		sb.append(equipVerb);
		sb.append("那件");
		sb.append(clothing.getName());
		sb.append("，这是因为");
		
		if(blockingClothing.equals(clothing) && clothing.isSealed()) {
			sb.append((blockingClothing.getClothingType().isPlural()?"它们被":"它被"));
			sb.append(" 封印了！");
			
		} else {
			sb.append(UtilText.parse(equipTarget, "[npc.namePos]的"));
			sb.append(blockingClothing.getName());
			sb.append((blockingClothing.getClothingType().isPlural()?"在":" 在"));
			sb.append("阻止你如此做！");
		}
		
		return sb.toString();
	}
	
	private static boolean isWeaponDyeReforgeActionAvailable() {
		return Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
				|| Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
				|| Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
				|| Main.game.isDebugMode();
	}
	
	private static boolean isClothingDyeActionAvailable() {
		return Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
				|| Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
				|| Main.game.isDebugMode();
	}
	
	
	/**
	 * The main DialogueNode. From here, the player can gain access to all parts
	 * of their inventory.
	 */
	public static final DialogueNode INVENTORY_MENU = new DialogueNode("物品栏", "返回物品栏菜单。", true) {
		@Override
		public String getLabel() {
			if(!Main.game.isInNewWorld()) {
				return "晚会着装";
			}
			
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "物品栏(快速管理当前<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>开启</b>)";
			} else {
				return "物品栏";
			}
		}

		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(inventoryNPC!=null && interactionType==InventoryInteraction.TRADING) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.removeTraderDescription)) {
					UtilText.nodeContentSB.append(inventoryNPC.getTraderDescription());
				}
				
			} else if(interactionType==InventoryInteraction.CHARACTER_CREATION) {
				return CharacterCreation.getCheckingClothingDescription();
			}
			
			return UtilText.nodeContentSB.toString();
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}
			
			if(Main.game.isBadEnd()) {
				if(index==1) {
					return new Response("不可用", "在坏结局时无法操作物品栏……", null);
				}
				return null;
			}
			
			if(responseTab==1) {
				if(item!=null) {
					return ITEM_INVENTORY.getResponse(responseTab, index);
					
				} else if(clothing!=null) {
					if(Main.game.getPlayer().getClothingCurrentlyEquipped().contains(clothing) || (inventoryNPC!=null && inventoryNPC.getClothingCurrentlyEquipped().contains(clothing))) {
						return CLOTHING_EQUIPPED.getResponse(responseTab, index);
					} else {
						return CLOTHING_INVENTORY.getResponse(responseTab, index);
					}
					
				} else if(weapon!=null) {
					if(Main.game.getPlayer().hasWeaponEquipped(weapon)
							|| (inventoryNPC!=null && inventoryNPC.hasWeaponEquipped(weapon))) {
						return WEAPON_EQUIPPED.getResponse(responseTab, index);
					} else {
						return WEAPON_INVENTORY.getResponse(responseTab, index);
					}
					
				} else {
					return null;
				}
			}

			StringBuilder responseSB = new StringBuilder();
			switch(interactionType) {
				case COMBAT:
					if(index == 1) {
						return new Response("拿取所有", "战斗中无法进行此操作！", null);

					} else if (index == 2) {
						return new Response("移开所有", "在战斗时无法进行此操作！", null);

					} else if (index == 3) {
						return new Response("整理所有衣物", "在战斗时无法进行此操作！", null);
						
					} else if (index == 4) {
						return new Response("脱下所有衣物", "在战斗时无法进行此操作！", null);

					} else if (index == 5) {
						return new Response("装备所有衣物", "在战斗时无法进行此操作！", null);

					} else if(index == 6) {
						if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
							return new Response("存储所有", "战斗中无法进行此操作！", null);
						}
						return new Response("丢弃所有", "战斗中无法进行此操作！", null);

					} else if(index==11) {
						if(Main.game.getPlayer().getUnlockKeyMap().isEmpty()) {
							return new Response("解锁用具", "你当前并没有能解锁特定衣物的解锁用具。", null);
							
						} else if(Main.game.getCurrentDialogueNode()==INVENTORY_MENU_KEYS) {
							return new Response("解锁用具", "你正在查看能够用来解锁特定衣物的解锁用具。", null);
							
						} else {
							return new Response("解锁用具", "查看所有能够用来解锁特定衣物的解锁用具。", INVENTORY_MENU_KEYS);
						}
						
					} else {
						return null;
					}
					
				case FULL_MANAGEMENT:
					if (index == 1) {
						if(inventoryNPC == null ) {
							if((Main.game.getPlayerCell().getInventory().getInventorySlotsTaken()==0 && !Main.game.getPlayerCell().getInventory().isAnyQuestItemPresent())
									|| Main.game.isInCombat()
									|| Main.game.isInSex()) {
								return new Response("拿取所有", "拿起所有在地上的物品。", null);

							} else {
								return new Response("拿取所有", "拿起所有在地上的物品。", INVENTORY_MENU){
									@Override
									public void effects(){
										for(Entry<AbstractItem, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllItemsInInventory()).entrySet()) {
											Main.game.getPlayer().addItem(entry.getKey(), entry.getValue(), true, true);
										}
										for(Entry<AbstractWeapon, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory()).entrySet()) {
											Main.game.getPlayer().addWeapon(entry.getKey(), entry.getValue(), true, true);
										}
										for(Entry<AbstractClothing, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllClothingInInventory()).entrySet()) {
											Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), true, true);
										}
									}
								};
							}

						} else {
							if(inventoryNPC.getInventorySlotsTaken()==0 || Main.game.isInCombat() || Main.game.isInSex()) {
								return new Response("拿取所有", UtilText.parse(inventoryNPC, "从[npc.namePos]的物品栏拿取所有物品。"), null);

							} else {
								return new Response("拿取所有", UtilText.parse(inventoryNPC, "从[npc.namePos]的物品栏拿取所有物品。"), INVENTORY_MENU){
									@Override
									public void effects(){
										for(Entry<AbstractItem, Integer> entry : new HashMap<>(inventoryNPC.getAllItemsInInventory()).entrySet()) {
											if(!Main.game.getPlayer().isInventoryFull()
													|| Main.game.getPlayer().hasItem(entry.getKey())
													|| entry.getKey().getRarity()==Rarity.QUEST) {
												inventoryNPC.removeItem(entry.getKey(), entry.getValue());
												Main.game.getPlayer().addItem(entry.getKey(), entry.getValue(), true, true);
											}
										}
										for(Entry<AbstractClothing, Integer> entry : new HashMap<>(inventoryNPC.getAllClothingInInventory()).entrySet()) {
											if(!Main.game.getPlayer().isInventoryFull()
													|| Main.game.getPlayer().hasClothing(entry.getKey())
													|| entry.getKey().getRarity()==Rarity.QUEST) {
												inventoryNPC.removeClothing(entry.getKey(), entry.getValue());
												Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), true, true);
											}
										}
										for(Entry<AbstractWeapon, Integer> entry : new HashMap<>(inventoryNPC.getAllWeaponsInInventory()).entrySet()) {
											if(!Main.game.getPlayer().isInventoryFull()
													|| Main.game.getPlayer().hasWeapon(entry.getKey())
													|| entry.getKey().getRarity()==Rarity.QUEST) {
												inventoryNPC.removeWeapon(entry.getKey(), entry.getValue());
												Main.game.getPlayer().addWeapon(entry.getKey(), entry.getValue(), true, true);
											}
										}
									}
								};
							}
						}

					} else if (index == 2) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("移开所有", "你没穿衣物，所以无法移开衣物！", null);

						} else {
							return new Response("移开所有", "尽可能地移开所有衣物。", INVENTORY_MENU){
								@Override
								public void effects(){
									for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getDisplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 3) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("整理所有衣物", "你没穿衣物，所以没有衣物可以整理！", null);

						} else {
							return new Response("整理所有衣物", "尽量整理好你的衣物！", INVENTORY_MENU){
								@Override
								public void effects(){

									List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator().reversed());

									for(AbstractClothing c : zlayerClothing) {
										for(BlockedParts bp : c.getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeReplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getReplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 4) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("脱下所有衣物", "你没有穿衣物，所以无法脱下衣物！", null);

						} else {
							return new Response("脱下所有衣物", "尽可能地脱下衣物。", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(unequipAll(Main.game.getPlayer()));
								}
							};
						}

					} else if (index == 5) {
						if(Main.game.getPlayer().getAllClothingInInventory().isEmpty()) {
							return new Response("装备所有衣物", "你没有衣物，所以无法装备！", null);

						} else {
							return new Response("装备所有衣物", "尽可能地装备物品栏中的衣物。", INVENTORY_MENU){
								@Override
								public void effects(){
									equipAll(Main.game.getPlayer());
								}
							};
						}

					} else if(index == 6) {
						if(Main.game.getPlayer().getInventorySlotsTaken()==0) {
							return new Response(
									!Main.game.getPlayer().getLocationPlace().isItemsDisappear()
										?"存储所有"
										:"丢弃所有",
									!Main.game.getPlayer().getLocationPlace().isItemsDisappear()
										?"你的物品栏里没有任何能存入的东西……"
										:"你的物品栏里没有任何能丢弃的东西……",
											null);
						}
						return new Response(
								!Main.game.getPlayer().getLocationPlace().isItemsDisappear()
									?"存储所有"
									:"丢弃所有",
								!Main.game.getPlayer().getLocationPlace().isItemsDisappear()
									?"将物品栏中的所有物品存储于该地点。"
									:"将物品栏中的所有物品丢在地上。",
										INVENTORY_MENU) {
							@Override
							public void effects() {
								for(Entry<AbstractItem, Integer> i : new HashSet<>(Main.game.getPlayer().getAllItemsInInventory().entrySet())) {
									if(i.getKey().getItemType().isAbleToBeDropped()) {
										dropItems(Main.game.getPlayer(), i.getKey(), i.getValue());
									}
								}
								for(Entry<AbstractWeapon, Integer> w : new HashSet<>(Main.game.getPlayer().getAllWeaponsInInventory().entrySet())) {
									if(w.getKey().getWeaponType().isAbleToBeDropped()) {
										dropWeapons(Main.game.getPlayer(), w.getKey(), w.getValue());
									}
								}
								for(Entry<AbstractClothing, Integer> c : new HashSet<>(Main.game.getPlayer().getAllClothingInInventory().entrySet())) {
									if(c.getKey().getClothingType().isAbleToBeDropped()) {
										dropClothing(Main.game.getPlayer(), c.getKey(), c.getValue());
									}
								}
							}
						};

					} else if (index == 7 && inventoryNPC != null) {
						if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response(UtilText.parse(inventoryNPC,"移开所有([npc.HerHim])"),
									UtilText.parse(inventoryNPC, "[npc.Name]没有穿衣物，所以无法移开衣物！"),
									null);

						} else {
							return new Response(UtilText.parse(inventoryNPC,"移开所有([npc.HerHim])"),
									UtilText.parse(inventoryNPC, "尽可能地解除[npc.namePos]的衣物。"),
									INVENTORY_MENU){
								@Override
								public void effects(){
									for(AbstractClothing c : inventoryNPC.getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getBlockedPartsMap(inventoryNPC, c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												inventoryNPC.isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+inventoryNPC.getDisplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 8 && inventoryNPC != null) {
						if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response(UtilText.parse(inventoryNPC,"整理所有衣物([npc.HerHim])"),
									UtilText.parse(inventoryNPC, "[npc.Name]没穿衣物，所以无法整理衣物！"),
									null);

						} else {
							return new Response(UtilText.parse(inventoryNPC,"整理所有衣物([npc.HerHim])"),
									UtilText.parse(inventoryNPC, "尽可能地整理[npc.namePos]的衣物。"),
									INVENTORY_MENU){
								@Override
								public void effects(){

									List<AbstractClothing> zlayerClothing = new ArrayList<>(inventoryNPC.getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator().reversed());

									for(AbstractClothing c : zlayerClothing) {
										for(BlockedParts bp : c.getBlockedPartsMap(inventoryNPC, c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												inventoryNPC.isAbleToBeReplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+inventoryNPC.getReplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 9 && inventoryNPC != null) {
						if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response(UtilText.parse(inventoryNPC, "脱下所有衣物([npc.HerHim])"),
									UtilText.parse(inventoryNPC, "[npc.Name]没穿衣物，所以无法解除衣物！"),
									null);

						} else {
							return new Response(UtilText.parse(inventoryNPC, "脱下所有衣物([npc.HerHim])"),
									UtilText.parse(inventoryNPC, "尽可能地解除[npc.namePos]的衣物。"),
									INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(unequipAll(inventoryNPC));
								}
							};
						}

					} else if (index == 10 && !Main.game.isInSex() && !Main.game.isInCombat()) {
						return getQuickTradeResponse();

					} else if(index==11) {
						if(Main.game.getPlayer().getUnlockKeyMap().isEmpty()) {
							return new Response("解锁用具", "你当前并没有能解锁特定衣物的解锁用具。", null);
							
						} else if(Main.game.getCurrentDialogueNode()==INVENTORY_MENU_KEYS) {
							return new Response("解锁用具", "你正在查看能够用来解锁特定衣物的解锁用具。", null);
							
						} else {
							return new Response("解锁用具", "查看所有能够用来解锁特定衣物的解锁用具。", INVENTORY_MENU_KEYS);
						}
						
					} else {
						return null;
					}
				case CHARACTER_CREATION:
					if (index == 1) {
						if(Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.NIPPLES)
								|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.ANUS)
								|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.PENIS)
								|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.VAGINA)
								|| (Main.game.getPlayer().getClothingInSlot(InventorySlot.FOOT)==null && Main.game.getPlayer().getLegType().equals(LegType.HUMAN))) {
							return new Response("前往舞台", "你需要先穿好能够遮盖身体的衣物，还需要一双鞋。", null);
							
						} else {
							return new Response("前往舞台", "你已经准备好前往舞台了。", CharacterCreation.CHOOSE_BACKGROUND) {
								@Override
								public int getSecondsPassed() {
									return CharacterCreation.TIME_TO_BACKGROUND;
								}
								@Override
								public void effects() {
									CharacterCreation.moveNPCIntoPlayerTile();
								}
							};
						}
						
					} else if(index == 2){
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()){
							return new Response("脱下所有衣物", "你现在已经光着了，没有衣物可以脱。", null);
						}
						else{
							return new Response("脱下所有衣物", "尽可能地脱下衣物。", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(unequipAll(Main.game.getPlayer()));
								}
							};
						}

					} else {
						return null;
					}
					
				case TRADING:
					if (index == 1) {
						if(inventoryNPC != null ||Main.game.getPlayerCell().getInventory().getInventorySlotsTaken()==0 || Main.game.isInCombat() || Main.game.isInSex()) {
							return new Response("拿取所有", "拿起所有在地上的物品。", null);

						} else {
							return new Response("拿取所有", "拿起所有在地上的物品。", INVENTORY_MENU){
								@Override
								public void effects(){
									for(Entry<AbstractItem, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllItemsInInventory()).entrySet()) {
										Main.game.getPlayer().addItem(entry.getKey(), entry.getValue(), true, true);
									}
									for(Entry<AbstractWeapon, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory()).entrySet()) {
										Main.game.getPlayer().addWeapon(entry.getKey(), entry.getValue(), true, true);
									}
									for(Entry<AbstractClothing, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllClothingInInventory()).entrySet()) {
										Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), true, true);
									}
								}
							};
						}

					} else if (index == 2) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("移开所有", "你没穿衣物，所以无法移开衣物！", null);

						} else {
							return new Response("移开所有", "尽可能地移开所有衣物。", INVENTORY_MENU){
								@Override
								public void effects(){
									for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getDisplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 3) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("整理所有衣物", "你没有穿衣物，所以没有衣物可以整理！", null);

						} else {
							return new Response("整理所有衣物", "尽量整理好你的衣物！", INVENTORY_MENU){
								@Override
								public void effects(){

									List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator().reversed());

									for(AbstractClothing c : zlayerClothing) {
										for(BlockedParts bp : c.getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeReplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getReplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 4) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("脱下所有衣物", "你没有穿衣物，所以无法脱下衣物！", null);

						} else {
							return new Response("脱下所有衣物", "尽可能地脱下衣物。", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(unequipAll(Main.game.getPlayer()));
								}
							};
						}

					} else if (index == 5) {
						if(Main.game.getPlayer().getAllClothingInInventory().isEmpty()) {
							return new Response("装备所有衣物", "你没有衣物，所以无法装备！", null);

						} else {
							return new Response("装备所有衣物", "尽可能地装备物品栏中的衣物。", INVENTORY_MENU){
								@Override
								public void effects(){
									equipAll(Main.game.getPlayer());
								}
							};
						}

					} else if(index == 6) {
						if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
							return new Response("存储所有", "你无法在交易时做这个……", null);
						}
						return new Response("丢弃所有", "你无法在交易时做这个……", null);

					} else if (index == 9 && inventoryNPC!=null) {
						return getBuybackResponse();

					} else if (index == 10 && !Main.game.isInSex() && !Main.game.isInCombat()) {
						return getQuickTradeResponse();

					} else if(index==11) {
						if(Main.game.getPlayer().getUnlockKeyMap().isEmpty()) {
							return new Response("解锁用具", "你当前并没有能解锁特定衣物的解锁用具。", null);
							
						} else if(Main.game.getCurrentDialogueNode()==INVENTORY_MENU_KEYS) {
							return new Response("解锁用具", "你正在查看能够用来解锁特定衣物的解锁用具。", null);
							
						} else {
							return new Response("解锁用具", "查看所有能够用来解锁特定衣物的解锁用具。", INVENTORY_MENU_KEYS);
						}
						
					} else {
						return null;
					}
				case SEX:
					if(index == 1) {
						return new Response("拿取所有", "拿起所有在地上的物品。", null);

					} else if (index == 2) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("移开所有", "你没穿衣物，所以无法移开衣物！", null);

						} else {
							return new Response("移开所有", "尽可能地移开所有衣物。", Main.sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									responseSB.setLength(0);

									for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												responseSB.append("<p style='text-align:center;'>"+Main.game.getPlayer().getDisplaceDescription()+"</p>");
											}
										}
									}

									Main.sex.setUnequipClothingText(null, responseSB.toString());
									Main.mainController.openInventory();
									Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Main.sex.setSexStarted(true);
								}
							};
						}

					} else if (index == 3) {
						return new Response("整理所有衣物", "你无法在性交场景中整理衣物！", null);

					} else if (index == 4) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("脱下所有衣物", "你没穿衣物，所以无法脱下衣物！", null);

						} else {
							return new Response("脱下所有衣物", "尽可能地脱下衣物。", Main.sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									responseSB.setLength(0);
									
									responseSB.append(unequipAll(Main.game.getPlayer()));
									
									Main.sex.setUnequipClothingText(null, responseSB.toString());
									Main.mainController.openInventory();
									Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Main.sex.setSexStarted(true);
								}
							};
						}

					} else if (index == 5) {
						return new Response("装备所有衣物", "你无法在性交场景中装备衣物！", null);

					} else if(index == 6) {
						if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
							return new Response("存储所有", "性交过程中不能这么做……", null);
						}
						return new Response("丢弃所有", "性交过程中不能这么做……", null);

					} else if (index == 7 && inventoryNPC != null) {
						if(Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
							return new Response(UtilText.parse(inventoryNPC, "移开所有([npc.HerHim])"), UtilText.parse(inventoryNPC, "你无法在躲藏时移开[npc.namePos]的衣服！"), null);
							
						} else if(!Main.sex.isCanRemoveOthersClothing(Main.game.getPlayer(), null)) {
							return new Response(UtilText.parse(inventoryNPC, "移开所有([npc.HerHim])"), UtilText.parse(inventoryNPC, "该性交场景中你无法移开[npc.namePos]的衣物！"), null);

						} else if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response(UtilText.parse(inventoryNPC, "移开所有([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.namePos]没穿衣物，所以无法移开衣物！"), null);

						} else {
							return new Response(UtilText.parse(inventoryNPC, "移开所有([npc.HerHim])"), UtilText.parse(inventoryNPC, "尽可能地移开[npc.namePos]的衣物。"), Main.sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									responseSB.setLength(0);

									for(AbstractClothing c : inventoryNPC.getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getBlockedPartsMap(inventoryNPC, c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												inventoryNPC.isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												responseSB.append("<p style='text-align:center;'>"+inventoryNPC.getDisplaceDescription()+"</p>");
											}
										}
									}

									Main.sex.setUnequipClothingText(null, responseSB.toString());
									Main.mainController.openInventory();
									Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Main.sex.setSexStarted(true);
								}
							};
						}

					} else if (index == 8 && inventoryNPC != null) {
						return new Response(UtilText.parse(inventoryNPC, "整理所有衣物([npc.HerHim])"), UtilText.parse(inventoryNPC, "你无法在性交场景中整理[npc.namePos]的衣物！"), null);

					} else if (index == 9 && inventoryNPC != null) {
						if(Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
							return new Response(UtilText.parse(inventoryNPC, "脱下所有衣物([npc.HerHim])"), UtilText.parse(inventoryNPC, "你无法在躲藏时脱下[npc.namePos]的衣服！"), null);
							
						} else if(!Main.sex.isCanRemoveOthersClothing(Main.game.getPlayer(), null)) {
							return new Response(UtilText.parse(inventoryNPC, "脱下所有衣物([npc.HerHim])"), UtilText.parse(inventoryNPC, "该性交场景中你无法脱下[npc.namePos]的衣物！"), null);

						} else if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response(UtilText.parse(inventoryNPC, "脱下所有衣物([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name]没穿衣物，所以无法脱下衣物！"), null);

						} else {
							return new Response(UtilText.parse(inventoryNPC, "脱下所有衣物([npc.HerHim])"), UtilText.parse(inventoryNPC, "尽可能地脱下[npc.namePos]的衣物。"), Main.sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									responseSB.setLength(0);

									responseSB.append(unequipAll(inventoryNPC));

									Main.sex.setUnequipClothingText(null, responseSB.toString());
									Main.mainController.openInventory();
									Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Main.sex.setSexStarted(true);
								}
							};
						}

					} else if(index==11) {
						if(Main.game.getPlayer().getUnlockKeyMap().isEmpty()) {
							return new Response("解锁用具", "你当前并没有能解锁特定衣物的解锁用具。", null);
							
						} else if(Main.game.getCurrentDialogueNode()==INVENTORY_MENU_KEYS) {
							return new Response("解锁用具", "你正在查看能够用来解锁特定衣物的解锁用具。", null);
							
						} else {
							return new Response("解锁用具", "查看所有能够用来解锁特定衣物的解锁用具。", INVENTORY_MENU_KEYS);
						}
						
					} else {
						return null;
					}
			}
			
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static final DialogueNode INVENTORY_MENU_KEYS = new DialogueNode("物品栏", "返回物品栏菜单。", true) {
		@Override
		public String getLabel() {
			if(!Main.game.isInNewWorld()) {
				return "晚会着装";
			}
			
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "物品栏(快速管理当前<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>开启</b>)";
			} else {
				return "物品栏";
			}
		}

		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
			UtilText.nodeContentSB.append("[style.boldMinorGood(拥有的解锁用具：)]");
			if(Main.game.getPlayer().getUnlockKeyMap().isEmpty()) {
				UtilText.nodeContentSB.append("<br/>[style.italicsDisabled(什么也没有……)]");
				
			} else {
				for(Entry<String, List<InventorySlot>> entry : Main.game.getPlayer().getUnlockKeyMap().entrySet()) {
					try {
						if (entry.getKey() == null || entry.getKey().isBlank())
							System.err.println("Trying to load inventory with a non-existant NPC ID");
						GameCharacter npc = Main.game.getNPCById(entry.getKey());
						List<String> slots = new ArrayList<>();
						for(InventorySlot slot : entry.getValue()) {
							AbstractClothing slotClothing = npc.getClothingInSlot(slot);
							if(slotClothing!=null) {
								slots.add(Util.capitaliseSentence(slotClothing.getName())+" ('"+slot.getName()+"'栏位)");
							}
						}
						if(!slots.isEmpty()) {
							UtilText.nodeContentSB.append(UtilText.parse(npc, "<br/><b style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>("+slots.size()+"): "));
							int i=0;
							for(String s : slots) {
								UtilText.nodeContentSB.append((i>0?"、":"")+s);
								i++;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return INVENTORY_MENU.getResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static final DialogueNode ITEM_INVENTORY = new DialogueNode("物品", "", true) {

		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "物品栏(快速管理当前<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>开启</b>)";
			} else {
				return "物品栏";
			}
		}

		@Override
		public String getHeaderContent() {
			return inventoryView();
		}
		
		@Override
		public String getContent() {
			return getItemDisplayPanel(item,
					item.getSVGString(),
					item.getDisplayName(true),
					item.getDescription(owner)
						+ item.getExtraDescription(owner, owner)
						+ (owner!=null && owner.isPlayer()
								? (inventoryNPC != null && interactionType == InventoryInteraction.TRADING
										? "<p>"
											+(inventoryNPC.willBuy(item) && item.getItemType().isAbleToBeSold()
												?inventoryNPC.getName("") + "会以" + UtilText.formatAsMoney(item.getPrice(inventoryNPC.getBuyModifier())) + "的价格买入。"
												:inventoryNPC.getName("") + "不想买这个。")
											+"</p>"
										: "")
								:(inventoryNPC != null && interactionType == InventoryInteraction.TRADING
									? "<p>"
											+ inventoryNPC.getName("") + "会以" + UtilText.formatAsMoney(item.getPrice(inventoryNPC.getSellModifier(item))) + "的价格出售。"
										+ "</p>" 
									: "")));
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}

			if(Main.game.isBadEnd()) {
				if(index==1) {
					return new Response("不可用", "在坏结局时无法操作物品栏……", null);
				}
				return null;
			}
			
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasItem(item);
					
					switch(interactionType) {
						case SEX:
							String dropTitle = owner.getLocationPlace().isItemsDisappear()?"丢弃":"存储";
							if(index == 1) {
								return new Response(dropTitle+"(1)", "自慰时无法丢弃物品。", null);
								
							} else if(index == 2) {
								return new Response(dropTitle+"(5)", "自慰时无法丢弃物品。", null);
								
							} else if(index == 3) {
								return new Response(dropTitle+"(所有)", "自慰时无法丢弃物品。", null);
								
							} else if(index == 5) {
								return new Response("附魔", "自慰时无法附魔物品。", null);
								
							} else if(index == 6) {
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "你当前处于[style.colourTerrible(无法行动)]状态，无法使用此物品！", null);
								}
								if(!Main.sex.isItemUseAvailable()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "该性交场景中无法使用物品！", null);
									
								} else if (!item.isAbleToBeUsedInSex()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "性交过程中不能使用这个！", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												resetPostAction();
											}
										};
									} else {
										return new Response(
												Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
												item.getItemType().getUseTooltipDescription(owner, owner),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												Main.sex.setUsingItemText(Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false));
												resetPostAction();
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
												Main.sex.setSexStarted(true);
											}
										};
									}
								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", "性交过程中一次只能使用一份物品！", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
						
						default:
							if(index == 1) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("丢弃(1)", "你无法丢弃" + item.getName() + "！", null);
									} else if(areaFull) {
										return new Response("丢弃(1)", "该区域已经满了，无法在此丢弃" + item.getName() + "！", null);
									} else {
										return new Response("丢弃(1)", "丢弃" + item.getName() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, 1);
											}
										};
									}
								} else {
									if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("存储(1)", "你无法存储" + item.getName() + "！", null);
									} else if(areaFull) {
										return new Response("存储(1)", "该区域已经满了，无法在此存储" + item.getName() + "！", null);
									} else {
										return new Response("存储(1)", "在该区域存储" + item.getName() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, 1);
											}
										};
									}
								}
								
							} else if(index == 2) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(owner.getItemCount(item) < 5) {
										return new Response("丢弃(5)", "你没有足够的五个" + item.getNamePlural() + "！", null);
										
									} else if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("丢弃(5)", "你无法丢弃" + item.getName() + "！", null);
										
									} else if(areaFull) {
										return new Response("丢弃(5)", "该区域已经满了，无法在此丢弃" + item.getName() + "！", null);
										
									} else {
										return new Response("丢弃(5)", "丢弃五个" + item.getName() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, 5);
											}
										};
									}
								} else {
									if(owner.getItemCount(item) < 5) {
										return new Response("存储(5)", "你没有足够的五个" + item.getNamePlural() + "！", null);
										
									} else if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("存储(5)", "你无法存储" + item.getName() + "！", null);
										
									} else if(areaFull) {
										return new Response("存储(5)", "该区域已经满了，无法在此存储" + item.getName() + "！", null);
										
									} else {
										return new Response("存储(5)", "在该区域存储五个" + item.getName() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, 5);
											}
										};
									}
								}
								
							} else if(index == 3) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("丢弃(所有)", "你无法丢弃" + item.getName() + "！", null);
									} else if(areaFull) {
										return new Response("丢弃(所有)", "该区域已经满了，无法在此丢弃" + item.getName() + "！", null);
									} else {
										return new Response("丢弃(所有)", "丢弃所有的" + item.getName() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, owner.getItemCount(item));
											}
										};
									}
								} else {
									if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("存储(所有)", "你无法存储" + item.getName() + "！", null);
									} else if(areaFull) {
										return new Response("存储(所有)", "该区域已经满了，无法在此存储" + item.getName() + "！", null);
									} else {
										return new Response("存储(所有)", "在该区域存储所有的" + item.getName() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, owner.getItemCount(item));
											}
										};
									}
								}
								
							} else if(index == 5) {
								if(item.getEnchantmentItemType(null)==null || item.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
									return new Response("附魔", "该物品无法附魔！", null);
									
								} else if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									return new Response("附魔", "附魔该物品。", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(item);
										}
									};
									
								} else {
									return new Response("附魔", getEnchantmentNotDiscoveredText("items"), null);
								}
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												resetPostAction();
											}
										};
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" 所有(自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", "由于该物品拥有特殊效果，你一次只能使用一个！", null);
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)",
											item.getItemType().getUseTooltipDescription(owner, owner)
												+"<br/>[style.italicsMinorGood(重复该动作直到用光物品栏中的所有" + item.getNamePlural() + "。)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("给予(1)", "在战斗时无法给予其物品！", null);
								
							} else if(index == 2) {
								return new Response("给予(5)", "在战斗时无法给予对方物品！", null);
								
							} else if(index == 3) {
								return new Response("给予(所有)", "在战斗时无法给予其物品！", null);
								
							} else if(index == 5) {
								return new Response("附魔", "在战斗时无法附魔物品！", null);
								
							} else if(index == 6) {
								if(Main.game.getPlayer().isStunned()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "眩晕时无法使用任何物品！", null);
									
								} else if(Main.combat.isCombatantDefeated(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "被击败时无法使用任何物品！", null);
									
								} else if(Main.game.getPlayer().getRemainingAP()<CombatMove.ITEM_USAGE.getAPcost(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "需要至少"+CombatMove.ITEM_USAGE.getAPcost(Main.game.getPlayer())+"点AP来使用该动作！", null);
									
								} else if (!item.isAbleToBeUsedInCombatAllies()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "无法在战斗中使用这个！", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											Main.combat.ENEMY_ATTACK){
										@Override
										public void effects(){
											Main.combat.addItemToBeUsed(owner, owner, item);
											resetPostAction();
											Main.mainController.openInventory();
										}
									};
								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", "战斗时一次只能使用一件物品！", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {//TODO on ally though???
								if(Main.game.getPlayer().isStunned()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(敌人)", "眩晕时无法使用任何物品！", null);
									
								} else if(Main.combat.isCombatantDefeated(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(敌人)", "被击败时无法使用任何物品！", null);
									
								} else if(Main.game.getPlayer().getRemainingAP()<CombatMove.ITEM_USAGE.getAPcost(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(敌人)", "至少需要"+CombatMove.ITEM_USAGE.getAPcost(Main.game.getPlayer())+"点AP来使用该动作！", null);
									
								} else if (!item.isAbleToBeUsedInCombatEnemies()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(敌人)", "无法在战斗中使用这个！", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(敌人)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (敌人)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);

								} else if(item.getItemType().isFetishGiving()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +"(敌人)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											Main.combat.ENEMY_ATTACK,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.combat.addItemToBeUsed(owner, inventoryNPC, item);
											resetPostAction();
											Main.mainController.openInventory();
										}
									};
								} else if(item.getItemType().isTransformative()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +"(敌人)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											Main.combat.ENEMY_ATTACK,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.combat.addItemToBeUsed(owner, inventoryNPC, item);
											resetPostAction();
											Main.mainController.openInventory();
										}
									};
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +"(敌人)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											Main.combat.ENEMY_ATTACK){
										@Override
										public void effects(){
											Main.combat.addItemToBeUsed(owner, inventoryNPC, item);
											resetPostAction();
											Main.mainController.openInventory();
										}
									};
								}
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(敌人)", "战斗时一次只能使用一件物品！", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:  case CHARACTER_CREATION:
							boolean inventoryFull = inventoryNPC.isInventoryFull() && !inventoryNPC.hasItem(item);
							
							if(index == 1) {
								if(!item.getItemType().isAbleToBeDropped()) {
									return new Response("给予(1)", "你无法给出" + item.getName() + "！", null);
								} else if(inventoryFull) {
									return new Response("给予(1)", UtilText.parse(inventoryNPC, "[npc.NamePos]的物品栏已经满了！"), null);
								}
								return new Response("给予(1)", UtilText.parse(inventoryNPC, "给予[npc.name]一个" + item.getName() + "。"), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(Main.game.getPlayer(), inventoryNPC, item, 1);
									}
								};
								
							} else if(index == 2) {
								if(!item.getItemType().isAbleToBeDropped()) {
									return new Response("给予(5)", "你无法给出" + item.getName() + "！", null);
								} else if(inventoryFull) {
									return new Response("给予(5)", UtilText.parse(inventoryNPC, "[npc.NamePos]的物品栏已经满了！"), null);
								}
								if(Main.game.getPlayer().getItemCount(item) >= 5) {
									return new Response("给予(5)", UtilText.parse(inventoryNPC, "给予[npc.name]五个" + item.getName() + "。"), INVENTORY_MENU){
										@Override
										public void effects(){
											transferItems(Main.game.getPlayer(), inventoryNPC, item, 5);
										}
									};
								} else {
									return new Response("给予(5)", "你没有足够的五个" + item.getNamePlural() + "！", null);
								}
								
							} else if(index == 3) {
								if(!item.getItemType().isAbleToBeDropped()) {
									return new Response("给予(所有)", "你无法给出" + item.getName() + "！", null);
								} else if(inventoryFull) {
									return new Response("给予(所有)", UtilText.parse(inventoryNPC, "[npc.NamePos]的物品栏已经满了！"), null);
								}
								return new Response("给予(所有)", UtilText.parse(inventoryNPC, "给予[npc.name]你所有的" + item.getName() + "。"), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(Main.game.getPlayer(), inventoryNPC, item, Main.game.getPlayer().getItemCount(item));
									}
								};
								
							} else if(index == 5) {
								if(item.getEnchantmentItemType(null)==null || item.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
									return new Response("附魔", "该物品无法附魔！", null);
									
								} else if(Main.game.isDebugMode()
											|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
										return new Response("附魔", "附魔该物品。", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public DialogueNode getNextDialogue() {
												return EnchantmentDialogue.getEnchantmentMenu(item);
											}
										};
								
								} else {
									return new Response("附魔", getEnchantmentNotDiscoveredText("items"), null);
								}
							
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												resetPostAction();
											}
										};
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" 所有(自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", "由于该物品拥有特殊效果，你一次只能使用一个！", null);
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)",
											item.getItemType().getUseTooltipDescription(owner, owner)
												+"<br/>[style.italicsMinorGood(重复该动作直到用光物品栏中的所有" + item.getNamePlural() + "。)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "([npc.HerHim])"), item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"), item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);
									
								} else if(item.isBreakOutOfInventory()) {
									return new ResponseEffectsOnly(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, owner)){
										@Override
										public void effects(){
											Main.game.getPlayer().useItem(item, inventoryNPC, false);
											resetPostAction();
										}
									};
									
								} else if(item.getItemType().isFetishGiving()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											resetPostAction();
										}
									};
								} else if(item.getItemType().isTransformative()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											resetPostAction();
										}
									};
									
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											resetPostAction();
										}
									};
								}
							} else if(index == 12) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"), item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"), item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);
									
								} else if(item.isBreakOutOfInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"), "由于该物品具有特殊效果，你一次只能使用一个！", null);
									
								} else if(item.getItemType().isFetishGiving()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC)
												+"<br/>[style.italicsMinorGood(重复该动作直到用光物品栏中的所有" + item.getNamePlural() + "。)]",
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											}
											resetPostAction();
										}
									};
								} else if(item.getItemType().isTransformative()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC)
												+"<br/>[style.italicsMinorGood(重复该动作直到用光物品栏中的所有" + item.getNamePlural() + "。)]",
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											}
											resetPostAction();
										}
									};
									
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC)
												+"<br/>[style.italicsMinorGood(重复该动作直到用光物品栏中的所有" + item.getNamePlural() + "。)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											}
											resetPostAction();
										}
									};
								}
								
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("给予(1)", "性交过程中无法给予对方物品！", null);
								
							} else if(index == 2) {
								return new Response("给予(5)", "在战斗时无法给予对方物品！", null);
								
							} else if(index == 3) {
								return new Response("给予(所有)", "性交过程中无法给予对方物品！", null);
								
							} else if(index == 5) {
								return new Response("附魔", "性交过程中无法附魔物品！", null);
								
							} else if(index == 6) {
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "你当前处于[style.colourTerrible(无法行动)]状态，无法使用此物品！", null);
								}
								if(!Main.sex.isItemUseAvailable()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "该性交场景中无法使用物品！", null);
									
								} else if (!item.isAbleToBeUsedInSex()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "性交过程中不能使用这个！", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												resetPostAction();
											}
										};
									} else {
										return new Response(
												Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
												item.getItemType().getUseTooltipDescription(owner, owner),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												Main.sex.setUsingItemText(((NPC)Main.sex.getTargetedPartner(Main.game.getPlayer())).getItemUseEffects(item, owner, Main.game.getPlayer(), Main.game.getPlayer()).getValue());
												resetPostAction();
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
												Main.sex.setSexStarted(true);
											}
										};
									}
								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", "性交过程中一次只能使用一份物品！", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(对象)", "你当前处于[style.colourTerrible(无法行动)]状态，无法使用此物品！", null);
								}
								if(Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(对象)", UtilText.parse(inventoryNPC, "你无法在躲藏时对[npc.Name]使用物品！"), null);
									
								} else if(!Main.sex.isItemUseAvailable()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (对象)", "该性交场景中无法使用物品！", null);
									
								} else if (!item.isAbleToBeUsedInSex()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (对象)", "性交过程中不能使用这个！", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (对象)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (对象)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);
									
								} else if(inventoryNPC.isAsleep()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(对象)", UtilText.parse(inventoryNPC, "当[npc.nameIsFull]睡眠时你不能使用该物品！"), null);
									
								} else if(item.getItemType().isFetishGiving()) {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (对象)",
												item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
												Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
												Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
												null,
												null,
												null){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, inventoryNPC, false);
												resetPostAction();
											}
										};
									} else {
										return new Response(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (对象)",
												item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
												Main.sex.SEX_DIALOGUE,
												Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
												Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
												null,
												null,
												null){
											@Override
											public void effects(){
												Main.sex.setUsingItemText(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
												resetPostAction();
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
												Main.sex.setSexStarted(true);
											}
										};
									}
									
								} else if(item.getItemType().isTransformative()) {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (对象)",
												item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
												Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
												Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
												null,
												null,
												null){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, inventoryNPC, false);
												resetPostAction();
											}
										};
									} else {
										return new Response(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (对象)",
												item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
												Main.sex.SEX_DIALOGUE,
												Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
												Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
												null,
												null,
												null){
											@Override
											public void effects(){
												Main.sex.setUsingItemText(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
												resetPostAction();
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
												Main.sex.setSexStarted(true);
											}
										};
									}
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (对象)",
												item.getItemType().getUseTooltipDescription(owner, inventoryNPC)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, inventoryNPC, false);
												resetPostAction();
											}
										};
									} else {
										return new Response(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (对象)",
												item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												Main.sex.setUsingItemText(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
												resetPostAction();
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
												Main.sex.setSexStarted(true);
											}
										};
									}
								}
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" 所有(对象)", "性交过程中一次只能使用一份物品！", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							if(index == 1) {
								if(!item.getItemType().isAbleToBeSold()) {
									return new Response("出售(1)", "你无法出售" + item.getName() + "！", null);
									
								} else if (inventoryNPC.willBuy(item)) {
									int sellPrice = item.getPrice(inventoryNPC.getBuyModifier());
									return new Response("出售(1) (" + UtilText.formatAsMoney(sellPrice, "span") + ")", "以" + UtilText.formatAsMoney(sellPrice) + "的价格出售" + item.getName() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											sellItems(Main.game.getPlayer(), inventoryNPC, item, 1, sellPrice);
										}
									};
								} else {
									return new Response("出售(1)", inventoryNPC.getName("") + "不想买这个。", null);
								}
								
							} else if(index == 2) {
								if(Main.game.getPlayer().getItemCount(item) >= 5) {
									if(!item.getItemType().isAbleToBeSold()) {
										return new Response("出售(5)", "你无法出售" + item.getName() + "！", null);
										
									} else if (inventoryNPC.willBuy(item)) {
										int sellPrice = item.getPrice(inventoryNPC.getBuyModifier());
										return new Response("出售(5) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "以" + UtilText.formatAsMoney(sellPrice) + "的价格出售五个" + item.getName() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												sellItems(Main.game.getPlayer(), inventoryNPC, item, 5, sellPrice);
											}
										};
									} else {
										return new Response("出售(5)", inventoryNPC.getName("") + "不想买这些。", null);
									}
									
								} else {
									return new Response("出售(5)", "你没有足够的五个" + item.getNamePlural() + "！", null);
								}
								
							} else if(index == 3) {
								if(!item.getItemType().isAbleToBeSold()) {
									return new Response("出售(所有)", "你无法出售" + item.getName() + "！", null);
									
								} else if (inventoryNPC.willBuy(item)) {
									int sellPrice = item.getPrice(inventoryNPC.getBuyModifier());
									return new Response("出售(所有) (" + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getItemCount(item), "span") + ")",
											"以" + UtilText.formatAsMoney(sellPrice) + "的价格出售所有" + item.getName() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											sellItems(Main.game.getPlayer(), inventoryNPC, item, Main.game.getPlayer().getItemCount(item), sellPrice);
										}
									};
								} else {
									return new Response("出售(所有)", inventoryNPC.getName("") + "不想买这些。", null);
								}
								
							} else if(index == 5) {
								if(item.getEnchantmentItemType(null)==null || item.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
									return new Response("附魔", "该物品无法附魔！", null);
									
								} else if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									return new Response("附魔", "附魔该物品。", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(item);
										}
									};
									
								} else {
									return new Response("附魔", getEnchantmentNotDiscoveredText("items"), null);
								}
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsedWhileTrading()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", item.getUnableToBeUsedWhileTradingDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												resetPostAction();
											}
										};
									}
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsedWhileTrading()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", item.getUnableToBeUsedWhileTradingDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" 所有(自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", "由于该物品具有特殊效果，你一次只能使用一个！", null);
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)",
											item.getItemType().getUseTooltipDescription(owner, owner)
												+"<br/>[style.italicsMinorGood(重复该动作直到用光物品栏中的所有" + item.getNamePlural() + "。)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name]不想使用你的物品。"), null);
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name]不想使用你的物品。"), null);
								
							} else {
								return null;
							}
					}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item) && item.getRarity()!=Rarity.QUEST;
					
					switch(interactionType) {
						case SEX:
							if(index == 1) {
								return new Response("拿取(1)", "自慰时无法拿起物品。", null);
								
							} else if(index == 2) {
								return new Response("拿取(5)", "自慰时无法拿起物品。", null);
								
							} else if(index == 3) {
								return new Response("拿取(所有)", "自慰时无法拿起物品。", null);
								
							} else if(index == 5) {
								return new Response("附魔", "自慰时无法附魔物品。", null);
								
							} else if(index == 6) {
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "你当前处于[style.colourTerrible(无法行动)]状态，无法使用此物品！", null);
								}
								if(!Main.sex.isItemUseAvailable()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "该性交场景中无法使用物品！", null);
									
								} else if (!item.isAbleToBeUsedInSex()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "做爱的时候不能用这个！", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
												item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer())){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true);
												resetPostAction();
											}
										};
									} else {
										return new Response(
												Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
												item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer()),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												Main.sex.setUsingItemText(Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true));
												resetPostAction();
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
												Main.sex.setSexStarted(true);
											}
										};
									}
								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", "你在做爱过程中只能用一个！", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					
						default:
							if(index == 1) {
								if(inventoryFull) {
									return new Response("拿取(1)", "你的物品栏已经满了！", null);
								}
								return new Response("拿取(1)", "从地上拿取一个" + item.getName() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpItems(Main.game.getPlayer(), item, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("拿取(5)", "你的物品栏已经满了！", null);
								}
								if(Main.game.getPlayerCell().getInventory().getItemCount(item) >= 5) {
									return new Response("拿取(5)", "从地上拿取五个" + item.getNamePlural() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											pickUpItems(Main.game.getPlayer(), item, 5);
										}
									};
								} else {
									return new Response("拿取(5)", "地上不足五个" + item.getNamePlural() + "！", null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("拿取(所有)", "你的物品栏已经满了！", null);
								}
								return new Response("拿取(所有)", "从地上拿取所有" + item.getNamePlural() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpItems(Main.game.getPlayer(), item, Main.game.getPlayerCell().getInventory().getItemCount(item));
									}
								};
								
							} else if(index == 5) {
								return new Response("附魔", "无法附魔在地上的物品！", null);
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
												item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer())){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true);
												resetPostAction();
											}
										};
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer()),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" 所有(自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +"所有(自己)", "由于该物品具有特殊效果，你一次只能使用一个！", null);
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer())
											+"<br/>[style.italicsMinorGood(重复该动作直到用光该区域中的所有" + item.getNamePlural() + "。)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayerCell().getInventory().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					boolean inventoryFull = false;
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("拿取(1)", "你不能在和人战斗的时候拿他们的东西！", null);
								
							} else if(index == 2) {
								return new Response("拿取(5)", "你不能在和人战斗的时候拿他们的东西！", null);
								
							} else if(index == 3) {
								return new Response("拿取(所有)", "你不能在和人战斗的时候拿他们的东西！", null);
								
							} else if(index == 5) {
								return new Response("附魔", "无法附魔其他人的物品，尤其是在战斗过程中！", null);
								
							} else if(index == 6) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)", "你不能在和人打架的同时用他们的东西！", null);
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +"所有(自己)", "你不能在和人打架的同时用他们的东西！", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +"(敌人)", "你不能在和人打架的同时让他们使用东西！", null);
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +"所有(敌人)", "你不能在和人打架的同时让他们使用东西！", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:  case CHARACTER_CREATION:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item) && item.getRarity()!=Rarity.QUEST;
						
							if(index == 1) {
								if(inventoryFull) {
									return new Response("拿取(1)", "你的物品栏已经满了！", null);
								}
								return new Response("拿取(1)", UtilText.parse(inventoryNPC, "从[npc.name]那里拿取一个" + item.getName() + "。"), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(inventoryNPC, Main.game.getPlayer(), item, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("拿取(5)", "你的物品栏已经满了！", null);
								}
								if(inventoryNPC.getItemCount(item) >= 5) {
									return new Response("拿取(5)", UtilText.parse(inventoryNPC, "从[npc.namePos]那儿拿走五个" + item.getNamePlural() + "。"), INVENTORY_MENU){
										@Override
										public void effects(){
											transferItems(inventoryNPC, Main.game.getPlayer(), item, 5);
										}
									};
								} else {
									return new Response("拿取(5)", UtilText.parse(inventoryNPC, "[npc.Name]没有5个" + item.getNamePlural() + "！"), null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("拿取(所有)", "你的物品栏已经满了！", null);
								}
								return new Response("拿取(所有)", UtilText.parse(inventoryNPC, "从[npc.namePos]那拿走所有的" + item.getNamePlural() + "(" + Util.intToString(inventoryNPC.getItemCount(item))+ "个)。"), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(inventoryNPC, Main.game.getPlayer(), item, inventoryNPC.getItemCount(item));
									}
								};
								
							} else if(index == 5) {
								return new Response("附魔", "无法附魔由其他人拥有的物品！", null);
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer()),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false, false, false) + "</p>");
											if (item.isConsumedOnUse()) {
												inventoryNPC.getInventory().removeItem(item);
											}
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" 所有(自己)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer())
												+"<br/>[style.italicsMinorGood(重复该动作直到用光[npc.namePos]物品栏中的所有" + item.getNamePlural() + "。)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = inventoryNPC.getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false, false, false) + "</p>");
											}
											if (item.isConsumedOnUse()) {
												inventoryNPC.getInventory().removeItem(item, itemCount);
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "([npc.HerHim])"), item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"), item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);
									
								} else if(item.isBreakOutOfInventory()) {
									return new ResponseEffectsOnly(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, owner)){
										@Override
										public void effects(){
											Main.game.getPlayer().useItem(item, inventoryNPC, false);
											resetPostAction();
										}
									};
									
								} else if(item.getItemType().isFetishGiving()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC),
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											resetPostAction();
										}
									};
								} else if(item.getItemType().isTransformative()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC),
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											resetPostAction();
										}
									};
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											resetPostAction();
										}
									};
								}
								
							} else if(index == 12) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"), item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"), item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);
									
								} else if(item.isBreakOutOfInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"), "由于该物品拥有特殊效果，你一次只能使用一个！", null);
									
								} else if(item.getItemType().isFetishGiving()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC)
												+"<br/>[style.italicsMinorGood(重复该动作直到用光[npc.namePos]物品栏中的所有" + item.getNamePlural() + "。)]",
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											int itemCount = inventoryNPC.getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											}
											resetPostAction();
										}
									};
									
								} else if(item.getItemType().isTransformative()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC)
												+"<br/>[style.italicsMinorGood(重复该动作直到用光[npc.namePos]物品栏中的所有" + item.getNamePlural() + "。)]",
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											int itemCount = inventoryNPC.getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											}
											resetPostAction();
										}
									};
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC)
												+"<br/>[style.italicsMinorGood(重复该动作直到用光[npc.namePos]物品栏中的所有" + item.getNamePlural() + "。)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = inventoryNPC.getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											}
											resetPostAction();
										}
									};
								}
								
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("拿取(1)", "性交过程中不能拿走他人物品！", null);
								
							} else if(index == 2) {
								return new Response("拿取(5)", "性交过程中不能拿走他人物品！", null);
								
							} else if(index == 3) {
								return new Response("拿取(所有)", "性交过程中不能拿走他人物品！", null);
								
							} else if(index == 5) {
								return new Response("附魔", "无法附魔其他人的物品，尤其是在性交过程中！", null);
								
							} else if(index == 6) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"(自己)", "性交过程中无法使用对象的物品！", null);
								//TODO
//								if (!item.isAbleToBeUsedInSex()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Self)", "This cannot be used during sex!", null);
//									
//								} else if (!item.isAbleToBeUsedFromInventory()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Self)", item.getUnableToBeUsedFromInventoryDescription(), null);
//									
//								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Self)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
//									
//								} else {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Self)",
//											Util.capitaliseSentence(item.getItemType().getUseName()) + " the " + item.getName() + ".", Main.sex.SEX_DIALOGUE){
//										@Override
//										public void effects(){
//											Main.sex.setUsingItemText(Main.sex.getPartner().getItemUseEffects(item, owner, inventoryNPC, Main.game.getPlayer()));
//											resetPostAction();
//											Main.mainController.openInventory();
//											Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
//											Main.sex.setSexStarted(true);
//										}
//									};
//								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+"所有(自己)", "性交过程中一次只能使用一份物品！", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (对象)", "性交过程中无法使用对象的物品！", null);
								//TODO
//								if (!item.isAbleToBeUsedInSex()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Partner)", "This cannot be used during sex!", null);
//									
//								} else if (!item.isAbleToBeUsedFromInventory()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Partner)", item.getUnableToBeUsedFromInventoryDescription(), null);
//									
//								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Partner)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);
//									
//								} else if(item.getItemType().isFetishGiving()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Partner)",
//											"Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".",
//											Main.sex.SEX_DIALOGUE,
//											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
//											Fetish.v.getAssociatedCorruptionLevel(),
//											null,
//											null,
//											null){
//										@Override
//										public void effects(){
//											Main.sex.setUsingItemText(Main.sex.getPartner().getItemUseEffects(item, owner, inventoryNPC, inventoryNPC));
//											resetPostAction();
//											Main.mainController.openInventory();
//											Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
//											Main.sex.setSexStarted(true);
//										}
//									};
//								} else if(item.getItemType().isTransformative()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Partner)",
//											"Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".",
//											Main.sex.SEX_DIALOGUE,
//											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
//											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
//											null,
//											null,
//											null){
//										@Override
//										public void effects(){
//											Main.sex.setUsingItemText(Main.sex.getPartner().getItemUseEffects(item, owner, inventoryNPC, inventoryNPC));
//											resetPostAction();
//											Main.mainController.openInventory();
//											Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
//											Main.sex.setSexStarted(true);
//										}
//									};
//									
//								} else {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Partner)",
//											"Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".", Main.sex.SEX_DIALOGUE){
//										@Override
//										public void effects(){
//											Main.sex.setUsingItemText(Main.sex.getPartner().getItemUseEffects(item, owner, inventoryNPC, inventoryNPC));
//											resetPostAction();
//											Main.mainController.openInventory();
//											Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
//											Main.sex.setSexStarted(true);
//										}
//									};
//								}
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" 所有(对象)", "性交过程中一次只能使用一份物品！", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item)  && item.getRarity()!=Rarity.QUEST;
							
							if(index == 1) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():item.getPrice(inventoryNPC.getSellModifier(item));
								if(inventoryFull) {
									return new Response("购买(1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "你的物品栏已经满了！", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
									return new Response("购买(1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "你买不起这个！", null);
								}
								return new Response("购买(1) (" + UtilText.formatAsMoney(sellPrice, "span") + ")", "以" + UtilText.formatAsMoney(sellPrice) + "的价格购买" + item.getName() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										sellItems(inventoryNPC, Main.game.getPlayer(), item, 1, sellPrice);
									}
								};
								
							} else if(index == 2) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():item.getPrice(inventoryNPC.getSellModifier(item));
								if((buyback && Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<5)
										|| (!buyback && inventoryNPC.getItemCount(item) < 5)) {
									return new Response("购买(5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", UtilText.parse(inventoryNPC, "[npc.Name]没有五个"+item.getNamePlural()+"。"), null);
								}
								if(inventoryFull) {
									return new Response("购买(5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "你的物品栏已经满了！", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*5) {
									return new Response("购买(5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "你买不起这个！", null);
								}
								return new Response("购买(5) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "以" + UtilText.formatAsMoney(sellPrice*5)+ "的价格买下"+ item.getName() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										sellItems(inventoryNPC, Main.game.getPlayer(), item, 5, sellPrice);
									}
								};
								
							} else if(index == 3) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():item.getPrice(inventoryNPC.getSellModifier(item));
								int count = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount():inventoryNPC.getItemCount(item);
								if(inventoryFull) {
									return new Response("购买(所有) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "span")+")", "你的物品栏已经满了！", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*count) {
									int affordableCount = (int)(Main.game.getPlayer().getMoney() / sellPrice);
									if(affordableCount > 0) {
										return new Response("购买(Max " + affordableCount + ") (" + UtilText.formatAsMoney(sellPrice * affordableCount, "span") + ")",
												"以" + UtilText.formatAsMoney(sellPrice * affordableCount) + "的价格购买" + item.getName() + "。", INVENTORY_MENU) {
											@Override
											public void effects() {
												sellItems(inventoryNPC, Main.game.getPlayer(), item, affordableCount, sellPrice);
											}
										};
									} else {
										return new Response("购买(所有) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "span")+")", "你买不起这个！", null);
									}
								}
								return new Response("购买(所有) (" + UtilText.formatAsMoney(sellPrice*count, "span") + ")",
										"以" + UtilText.formatAsMoney(sellPrice*count) + "的价格购买" + item.getName() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										sellItems(inventoryNPC, Main.game.getPlayer(), item, count, sellPrice);
									}
								};
								
							} else if(index == 5) {
								return new Response("附魔", "无法附魔由其他人拥有的物品！", null);
								
							} else if(index == 6) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +"(自己)", UtilText.parse(inventoryNPC, "[npc.Name]不允许你使用[npc.her]的物品，除非你先买下。"), null);
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +"所有(自己)", UtilText.parse(inventoryNPC, "[npc.Name]不允许你使用[npc.her]的物品，除非你先买下。"), null);
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "([npc.HerHim])"),
										UtilText.parse(inventoryNPC, "[npc.Name]不允许你使用[npc.sheIs]准备售卖的物品！"),
										null);
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, "所有([npc.HerHim])"),
										UtilText.parse(inventoryNPC, "[npc.Name]不允许你使用[npc.sheIs]准备售卖的物品！"),
										null);
								
							} else {
								return null;
							}
					}
				}
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static final DialogueNode WEAPON_INVENTORY = new DialogueNode("武器", "", true) {
		
		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "物品栏(快速管理当前<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>开启</b>)";
			} else {
				return "物品栏";
			}
		}
		
		@Override
		public String getHeaderContent() {
			return inventoryView();
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			List<String> extraDescriptions = weapon.getExtraDescriptions(owner);
			if(!extraDescriptions.isEmpty()) {
				sb.append("<p>");
					for(int i=0 ; i<extraDescriptions.size() ; i++) {
						sb.append(extraDescriptions.get(i));
						if(i<extraDescriptions.size()-1) {
							sb.append("<br/>");
						}
					}
				sb.append("</p>");
			}
			return getItemDisplayPanel(weapon,
					weapon.getSVGString(),
					Util.capitaliseSentence(weapon.getDisplayName(true)),
					weapon.getDescription(owner)
						+ sb.toString()
						+ (owner!=null && owner.isPlayer()
								? (inventoryNPC != null && interactionType == InventoryInteraction.TRADING
										? "<p>" 
											+(inventoryNPC.willBuy(weapon)
												?inventoryNPC.getName("") + "会以" + UtilText.formatAsMoney(weapon.getPrice(inventoryNPC.getBuyModifier())) + "的价格买入。"
												:inventoryNPC.getName("") + "并不想买这个。")
											+"</p>"
										: "")
								:(inventoryNPC != null && interactionType == InventoryInteraction.TRADING
									? "<p>"
											+ inventoryNPC.getName("") + "会以" + UtilText.formatAsMoney(weapon.getPrice(inventoryNPC.getSellModifier(weapon))) + "的价格卖出。"
										+ "</p>" 
									: "")));
		}


		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}

			if(Main.game.isBadEnd()) {
				if(index==1) {
					return new Response("不可用", "在坏结局时无法操作物品栏……", null);
				}
				return null;
			}
			
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasWeapon(weapon);
					
					switch(interactionType) {
						case SEX:
							String dropTitle = owner.getLocationPlace().isItemsDisappear()?"丢弃":"存储";
							if(index == 1) {
								return new Response(dropTitle+"(1)", "你无法在自慰时丢弃你的武器。", null);
								
							} else if(index == 2) {
								return new Response(dropTitle+"(5)", "你无法在自慰时丢弃你的武器。", null);
								
							} else if(index == 3) {
								return new Response(dropTitle+"(所有)", "你无法在自慰时丢弃你的武器。", null);
								
							} else if(index == 4) {
								return new Response("染色/重铸", "你无法在自慰时染色或重铸你的武器。", null);
								
							} else if(index == 5) {
								return new Response("附魔", "自慰时无法附魔武器。", null);
								
							} else if(index == 6) {
								return new Response("主手装备(自己)", "自慰时无法装备武器。", null);
								
							} else if(index == 7) {
								return new Response("副手装备(自己)", "自慰时无法装备武器。", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
							
						default:
							if(index == 1) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("丢弃(1)", "无法丢弃" + weapon.getName() + "！", null);
										
									} else if(areaFull) {
										return new Response("丢弃(1)", "该区域已经满了，所以无法丢弃" + weapon.getName() + "！", null);
										
									} else {
										return new Response("丢弃(1)", "丢弃" + weapon.getName() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, 1);
											}
										};
									}
								} else {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("Store (1)", "You cannot drop the " + weapon.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("存储(1)", "存储" + weapon.getName() + "！", null);
									} else {
										return new Response("存储(1)", "存储" + weapon.getName() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, 1);
											}
										};
									}
								}
								
							} else if(index == 2) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("丢弃(5)", "无法丢弃" + weapon.getName() + "！", null);
										
									} else if(owner.getWeaponCount(weapon) < 5) {
										return new Response("丢弃(5)", "你没有足够的五个" + weapon.getNamePlural() + "！", null);
										
									} else if(areaFull) {
										return new Response("丢弃(5)", "该区域已经满了，所以无法丢弃" + weapon.getNamePlural() + "！", null);
										
									} else {
										return new Response("丢弃(5)", "丢弃五个" + weapon.getNamePlural() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, 5);
											}
										};
									}
								} else {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("Store (5)", "You cannot drop the " + weapon.getName() + "!", null);
										
									} else if(owner.getWeaponCount(weapon) < 5) {
										return new Response("存储(5)", "你没有足够的五个" + weapon.getNamePlural() + "！", null);
										
									} else if(areaFull) {
										return new Response("存储(5)", "存储" + weapon.getNamePlural() + "！", null);
										
									} else {
										return new Response("存储(5)", "存储五个" + weapon.getNamePlural() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, 5);
											}
										};
									}
								}
								
							} else if(index == 3) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("丢弃(所有)", "无法丢弃" + weapon.getName() + "！", null);
									
								} else if(owner.getLocationPlace().isItemsDisappear()) {
									if(areaFull) {
										return new Response("丢弃(所有)", "该区域已经满了，所以无法丢弃" + weapon.getNamePlural() + "！", null);
									} else {
										return new Response("丢弃(所有)", "丢弃所有的" + weapon.getNamePlural() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, owner.getWeaponCount(weapon));
											}
										};
									}
								} else {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("Store (All)", "You cannot drop the " + weapon.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("存储(所有)", "存储" + weapon.getNamePlural() + "！", null);
									} else {
										return new Response("存储(所有)", "存储所有的" + weapon.getNamePlural() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, owner.getWeaponCount(weapon));
											}
										};
									}
								}
								
							} else if (index==4) {
								if (isWeaponDyeReforgeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("染色/重铸",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"你使用精通的[style.colourEarth(土系法术)]为物品染色或重铸。"
													:"使用染色刷或重铸锤来修改这件武器的属性。",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("染色/重铸", "你的物品栏满了，无法修改武器的属性。", null);
									}
								} else {
									return new Response("染色/重铸", "你需要找到染色刷或重铸锤才能修改这件武器的属性。", null);
								}
								
							} else if(index == 5) {
								if(weapon.getEnchantmentItemType(null)==null || weapon.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
									return new Response("附魔", "该武器无法附魔！", null);
									
								} else if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									return new Response("附魔", "附魔该武器。", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(weapon);
										}
									};
									
								} else {
									return new Response("附魔", getEnchantmentNotDiscoveredText("weapons"), null);
								}
								
							} else if(index == 6) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[Main.game.getPlayer().getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("主手装备(自己)", "装备" + weapon.getName() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("主手装备(自己)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if(index == 7) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("副手装备(自己)",
											(weapon.getWeaponType().isPlural()
												?"由于" + weapon.getName() + "需要双手握持，所以只能被装备在主手栏位！"
												:"由于" + weapon.getName() + "是双手武器，只能被装备在主手栏位！"),
											null);
								}

								InventorySlot slot = InventorySlot.offhandWeaponSlots[Main.game.getPlayer().getOffhandWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("副手装备(自己)",
											"装备" + weapon.getName() + "。"
												+(weapon.getWeaponType().isTwoHanded()
														?"<br/>[style.italicsGood(尽管"+(weapon.getWeaponType().isPlural()?"":"")+")]"
														:""),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("副手装备(自己)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("给予(1)", "你不能在和人战斗的时候给他们武器！", null);
								
							} else if(index == 2) {
								return new Response("给予(5)", "你不能在和人战斗的时候给他们武器！", null);
								
							} else if(index == 3) {
								return new Response("给予(所有)", "你不能在和人战斗的时候给他们武器！", null);
								
							} else if(index == 4) {
								return new Response("染色", "在战斗时无法染色你的武器！", null);
								
							} else if(index == 5) {
								return new Response("附魔", "在战斗时无法附魔武器！", null);
								
							} else if(index == 6) {
								return new Response("主手装备(自己)", "你不能在和人战斗的同时更换武器！", null);
									
							} else if(index == 7) {
								return new Response("副手装备(自己)", "你不能在和人战斗的同时更换武器！", null);
									
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("装备(敌人)", "你不能给对手装备武器！", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:  case CHARACTER_CREATION:
							boolean inventoryFull = inventoryNPC.isInventoryFull() && !inventoryNPC.hasWeapon(weapon);
							
							if(index == 1) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("给予(1)", "你无法给出" + weapon.getName() + "！", null);
									
								} else if(inventoryFull) {
									return new Response("给予(1)", UtilText.parse(inventoryNPC, "[npc.NamePos]的物品栏已经满了！"), null);
								}
								return new Response("给予(1)", UtilText.parse(inventoryNPC, "Give [npc.name] one " + weapon.getName() + "。"), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(Main.game.getPlayer(), inventoryNPC, weapon, 1);
									}
								};
								
							} else if(index == 2) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("给予(5)", "你无法给出" + weapon.getName() + "！", null);
									
								} else if(inventoryFull) {
									return new Response("给予(5)", UtilText.parse(inventoryNPC, "[npc.NamePos]的物品栏已经满了！"), null);
								}
								if(Main.game.getPlayer().getWeaponCount(weapon) >= 5) {
									return new Response("给予(5)", UtilText.parse(inventoryNPC, "给[npc.name]五把" + weapon.getNamePlural() + "。"), INVENTORY_MENU){
										@Override
										public void effects(){
											transferWeapons(Main.game.getPlayer(), inventoryNPC, weapon, 5);
										}
									};
								} else {
									return new Response("给予(5)", "你没有足够的五个" + weapon.getNamePlural() + "！", null);
								}
								
							} else if(index == 3) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("给予(所有)", "你无法给出" + weapon.getName() + "！", null);
									
								} else if(inventoryFull) {
									return new Response("给予(所有)", UtilText.parse(inventoryNPC, "[npc.NamePos]的物品栏已经满了！"), null);
								}
								return new Response("给予(所有)", UtilText.parse(inventoryNPC, "把你所有的" + weapon.getNamePlural() + "给[npc.name]。"), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(Main.game.getPlayer(), inventoryNPC, weapon, Main.game.getPlayer().getWeaponCount(weapon));
									}
								};
								
							} else if (index==4) {
								if (isWeaponDyeReforgeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("染色/重铸",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"你使用精通的[style.colourEarth(土系法术)]为物品染色或重铸。"
													:"使用染色刷或重铸锤来修改这件武器的属性。",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("染色/重铸", "你的物品栏满了，无法修改武器的属性。", null);
									}
								} else {
									return new Response("染色/重铸", "你需要找到染色刷或重铸锤才能修改这件武器的属性。", null);
								}
								
							} else if(index == 5) {
								if(weapon.getEnchantmentItemType(null)==null || weapon.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
									return new Response("附魔", "该武器无法附魔！", null);
									
								} else if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									return new Response("附魔", "附魔该武器。", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(weapon);
										}
									};
									
								} else {
									return new Response("附魔", getEnchantmentNotDiscoveredText("weapons"), null);
								}
								
							} else if(index == 6) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[Main.game.getPlayer().getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("主手装备(自己)", "装备" + weapon.getName() + "为主手武器。", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("主手装备(自己)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
									
							} else if(index == 7) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("副手装备(自己)",
											(weapon.getWeaponType().isPlural()
												?"由于" + weapon.getName() + "需要双手握持，所以只能被装备在主手栏位！"
												:"由于" + weapon.getName() + "是双手武器，只能被装备在主手栏位！"),
											null);
								}

								InventorySlot slot = InventorySlot.offhandWeaponSlots[Main.game.getPlayer().getOffhandWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("副手装备(自己)", "装备" + weapon.getName() + "为副手武器。", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("副手装备(自己)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
//								if(!weapon.getWeaponType().isAbleToBeDropped()) {
//									return new Response(UtilText.parse(inventoryNPC, "Equip Main ([npc.HerHim])"), "You cannot give away the " + weapon.getName() + "!", null);
//								}

								InventorySlot slot = InventorySlot.mainWeaponSlots[inventoryNPC.getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(inventoryNPC, slot)) {
									return new Response(UtilText.parse(inventoryNPC, "主手装备([npc.HerHim])"), UtilText.parse(inventoryNPC, "让[npc.name]装备"+weapon.getName()+"为主手武器。"), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ inventoryNPC.equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response(UtilText.parse(inventoryNPC, "主手装备([npc.HerHim])"), weapon.getCannotBeEquippedText(inventoryNPC, slot), null);
								}
								
							
							} else if(index == 12) {
//								if(!weapon.getWeaponType().isAbleToBeDropped()) {
//									return new Response(UtilText.parse(inventoryNPC, "Equip Main ([npc.HerHim])"), "You cannot give away the " + weapon.getName() + "!", null);
//								}
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response(UtilText.parse(inventoryNPC, "副手装备([npc.HerHim])"),
											(weapon.getWeaponType().isPlural()
												?"由于" + weapon.getName() + "需要双手握持，所以只能被装备在主手栏位！"
												:"由于" + weapon.getName() + "是双手武器，只能被装备在主手栏位！"),
											null);
								}
								InventorySlot slot = InventorySlot.offhandWeaponSlots[inventoryNPC.getOffhandWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(inventoryNPC, slot)) {
									return new Response(UtilText.parse(inventoryNPC, "副手装备([npc.HerHim])"), UtilText.parse(inventoryNPC, "让[npc.name]装备"+weapon.getName()+"为副手武器。"), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ inventoryNPC.equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response(UtilText.parse(inventoryNPC, "副手装备([npc.HerHim])"), weapon.getCannotBeEquippedText(inventoryNPC, slot), null);
								}
							
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("给予(1)", "性交过程中无法给予对方武器！", null);
								
							} else if(index == 2) {
								return new Response("给予(5)", "性交过程中无法给予对方武器！", null);
								
							} else if(index == 3) {
								return new Response("给予(所有)", "性交过程中无法给予对方武器！", null);
								
							} else if(index == 4) {
								return new Response("染色", "你和人做爱的时候不能给武器染色！", null);
								
							} else if(index == 5) {
								return new Response("附魔", "性交过程中无法附魔武器！", null);
								
							} else if(index == 6) {
								return new Response("主手装备(自己)", "性交过程中不能装备武器！", null);
								
							} else if(index == 7) {
								return new Response("副手装备(自己)", "性交过程中不能装备武器！", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "装备([npc.HerHim])"), "性交过程中不能装备武器！", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							if(index == 1) {
								if(!weapon.getWeaponType().isAbleToBeSold()) {
									return new Response("出售(1)", "你不能出售" + weapon.getName() + "！", null);
									
								} else if (inventoryNPC.willBuy(weapon)) {
									int sellPrice = weapon.getPrice(inventoryNPC.getBuyModifier());
									return new Response("出售(1) (" + UtilText.formatAsMoney(sellPrice, "span") + ")", "以" + UtilText.formatAsMoney(sellPrice) + "的价格出售" + weapon.getName() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											sellWeapons(Main.game.getPlayer(), inventoryNPC, weapon, 1, sellPrice);
										}
									};
								} else {
									return new Response("出售(1)", inventoryNPC.getName("") + "不想买这个。", null);
								}
								
							} else if(index == 2) {
								if(Main.game.getPlayer().getWeaponCount(weapon) >= 5) {
									if(!weapon.getWeaponType().isAbleToBeSold()) {
										return new Response("出售(5)", "你不能出售" + weapon.getName() + "！", null);
										
									} else if (inventoryNPC.willBuy(weapon)) {
										int sellPrice = weapon.getPrice(inventoryNPC.getBuyModifier());
										return new Response("出售(5) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "以" + UtilText.formatAsMoney(sellPrice*5) + "的价格出售五件" + weapon.getNamePlural() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												sellWeapons(Main.game.getPlayer(), inventoryNPC, weapon, 5, sellPrice);
											}
										};
									} else {
										return new Response("出售(5)", inventoryNPC.getName("") + "不想买这些。", null);
									}
									
								} else {
									return new Response("出售(5)", "你没有足够的五个" + weapon.getNamePlural() + "！", null);
								}
								
							} else if(index == 3) {
								if(!weapon.getWeaponType().isAbleToBeSold()) {
									return new Response("出售(所有)", "你不能出售" + weapon.getName() + "！", null);
									
								} else if (inventoryNPC.willBuy(weapon)) {
									int sellPrice = weapon.getPrice(inventoryNPC.getBuyModifier());
									return new Response("出售(所有) (" + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getWeaponCount(weapon), "span") + ")",
											"以" + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getWeaponCount(weapon)) + "的价格出售" + weapon.getName() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											sellWeapons(Main.game.getPlayer(), inventoryNPC, weapon, Main.game.getPlayer().getWeaponCount(weapon), sellPrice);
										}
									};
								} else {
									return new Response("出售(所有)", inventoryNPC.getName("") + "不想买这些。", null);
								}
								
							} else if (index==4) {
								if (isWeaponDyeReforgeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("染色/重铸",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"你使用精通的[style.colourEarth(土系法术)]为物品染色或重铸。"
													:"使用染色刷或重铸锤来修改这件武器的属性。",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("染色/重铸", "你的物品栏满了，无法修改武器的属性。", null);
									}
								} else {
									return new Response("染色/重铸", "你需要找到染色刷或重铸锤才能修改这件武器的属性。", null);
								}
								
							} else if(index == 5) {
								if(weapon.getEnchantmentItemType(null)==null || weapon.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
									return new Response("附魔", "该武器无法附魔！", null);
									
								} else if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									return new Response("附魔", "附魔该武器。", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(weapon);
										}
									};
									
								} else {
									return new Response("附魔", getEnchantmentNotDiscoveredText("weapons"), null);
								}
								
							} else if(index == 6) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[Main.game.getPlayer().getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("主手装备(自己)", "装备" + weapon.getName() + "为主手武器。", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("主手装备(自己)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
									
							} else if(index == 7) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("副手装备(自己)",
											(weapon.getWeaponType().isPlural()
												?"由于" + weapon.getName() + "需要双手握持，所以只能被装备在主手栏位！"
												:"由于" + weapon.getName() + "是双手武器，只能被装备在主手栏位！"),
											null);
								}
								
								InventorySlot slot = InventorySlot.offhandWeaponSlots[Main.game.getPlayer().getOffhandWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("副手装备(自己)", "装备" + weapon.getName() + "为副手武器。", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("副手装备(自己)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "主手装备([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name]不想使用你的武器。"), null);
								
							} else if(index == 12) {
								return new Response(UtilText.parse(inventoryNPC, "副手装备([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name]不想使用你的武器。"), null);
								
							} else {
								return null;
							}
					}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon) && weapon.getRarity()!=Rarity.QUEST;

					switch(interactionType) {
						case SEX:
							if(index == 1) {
								return new Response("拿取(1)", "自慰时无法拿起武器。", null);
								
							} else if(index == 2) {
								return new Response("拿取(5)", "自慰时无法拿起武器。", null);
								
							} else if(index == 3) {
								return new Response("拿取(所有)", "自慰时无法拿起武器。", null);
								
							} else if(index == 4) {
								return new Response("染色", "自慰时无法染色武器。", null);
								
							} else if(index == 5) {
								return new Response("附魔", "自慰时无法附魔武器。", null);
								
							} else if(index == 6) {
								return new Response("主手装备(自己)", "自慰时无法装备武器。", null);
								
							} else if(index == 7) {
								return new Response("副手装备(自己)", "自慰时无法装备武器。", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
							
						default:
							if(index == 1) {
								if(inventoryFull) {
									return new Response("拿取(1)", "你的物品栏已经满了！", null);
								}
								return new Response("拿取(1)", "从地上拿取一个" + weapon.getName() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpWeapons(Main.game.getPlayer(), weapon, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("拿取(5)", "你的物品栏已经满了！", null);
								}
								if(Main.game.getPlayerCell().getInventory().getWeaponCount(weapon) >= 5) {
									return new Response("拿取(5)", "从地上拿取五个" + weapon.getNamePlural() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											pickUpWeapons(Main.game.getPlayer(), weapon, 5);
										}
									};
								} else {
									return new Response("拿取(5)", "地上不足五个" + weapon.getNamePlural() + "！", null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("拿取(所有)", "你的物品栏已经满了！", null);
								}
								return new Response("拿取(所有)", "从地上拿取所有" + weapon.getNamePlural() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpWeapons(Main.game.getPlayer(), weapon, Main.game.getPlayerCell().getInventory().getWeaponCount(weapon));
									}
								};
								
							} else if (index==4) {
								if (isWeaponDyeReforgeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayerCell().getInventory().isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("染色/重铸",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"你使用精通的[style.colourEarth(土系法术)]为物品染色或重铸。"
													:"使用染色刷或重铸锤来修改这件武器的属性。",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("染色/重铸", "你的物品栏满了，无法修改武器的属性。", null);
									}
								} else {
									return new Response("染色/重铸", "你需要找到染色刷或重铸锤才能修改这件武器的属性。", null);
								}
								
							} else if(index == 5) {
								return new Response("附魔", "你无法附魔在地上的武器！", null);
								
							} else if(index == 6) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[Main.game.getPlayer().getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("主手装备(自己)", "装备" + weapon.getName() + "为主手武器。", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ Main.game.getPlayer().equipMainWeaponFromFloor(weapon)
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("主手装备(自己)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if(index == 7) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[Main.game.getPlayer().getMainWeaponIndexToEquipTo(weapon)];
								if(!weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("副手装备(自己)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("副手装备(自己)",
											(weapon.getWeaponType().isPlural()
												?"由于" + weapon.getName() + "需要双手握持，所以只能被装备在主手栏位！"
												:"由于" + weapon.getName() + "是双手武器，只能被装备在主手栏位！"),
											null);
								}
								return new Response("副手装备(自己)", "装备" + weapon.getName() + "为副手武器。", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
											+ Main.game.getPlayer().equipOffhandWeaponFromFloor(weapon)
											+ "</p>");
										resetPostAction();
									}
								};
									
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					boolean inventoryFull = false;
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("拿取(1)", "在战斗时无法拿取他人的武器！", null);
								
							} else if(index == 2) {
								return new Response("拿取(5)", "在战斗时无法拿取他人的武器！", null);
								
							} else if(index == 3) {
								return new Response("拿取(所有)", "在战斗时无法拿取他人的武器！", null);
								
							} else if(index == 4) {
								return new Response("染色", "在战斗时无法染色他人的武器！", null);
								
							} else if(index == 5) {
								return new Response("附魔", "你无法附魔其他人的武器，尤其是在战斗过程中！", null);
								
							} else if(index == 6) {
								return new Response("主手装备(自己)", "在战斗时无法使用他人的武器！", null);
								
							} else if(index == 7) {
								return new Response("副手装备(自己)", "在战斗时无法使用他人的武器！", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("主手装备(敌人)", "在战斗时无法令他人使用武器！", null);
								
							} else if(index == 12) {
								return new Response("副手装备(敌人)", "在战斗时无法令他人使用武器！", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:  case CHARACTER_CREATION:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon) && weapon.getRarity()!=Rarity.QUEST;
						
							if(index == 1) {
								if(inventoryFull) {
									return new Response("拿取(1)", "你的物品栏已经满了！", null);
								}
								return new Response("拿取(1)", UtilText.parse(inventoryNPC, "从[npc.Name]处拿取一个" + weapon.getName() + "。"), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(inventoryNPC, Main.game.getPlayer(), weapon, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("拿取(5)", "你的物品栏已经满了！", null);
								}
								if(inventoryNPC.getWeaponCount(weapon) >= 5) {
									return new Response("拿取(5)", UtilText.parse(inventoryNPC, "从[npc.namePos]那拿走五个" + weapon.getNamePlural() + "。"), INVENTORY_MENU){
										@Override
										public void effects(){
											transferWeapons(inventoryNPC, Main.game.getPlayer(), weapon, 5);
										}
									};
								} else {
									return new Response("拿取(5)", UtilText.parse(inventoryNPC, "[npc.Name]没有5个" + weapon.getNamePlural() + "！"), null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("拿取(所有)", "你的物品栏已经满了！", null);
								}
								return new Response("拿取(所有)", UtilText.parse(inventoryNPC, "将[npc.namePos]的"+ weapon.getNamePlural() +Util.intToString(inventoryNPC.getWeaponCount(weapon))+ "全部拿走。"), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(inventoryNPC, Main.game.getPlayer(), weapon, inventoryNPC.getWeaponCount(weapon));
									}
								};
								
							} else if (index==4) {
								if (isWeaponDyeReforgeActionAvailable()) {
									boolean hasFullInventory = inventoryNPC.isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = inventoryNPC.getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("染色/重铸",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"你使用精通的[style.colourEarth(土系法术)]为物品染色或重铸。"
													:"使用染色刷或重铸锤来修改这件武器的属性。",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("染色/重铸", UtilText.parse(inventoryNPC, "[npc.NamePos]的物品栏满了，无法修改武器的属性。"), null);
									}
								} else {
									return new Response("染色/重铸", UtilText.parse(inventoryNPC, "你需要找到染色刷或重铸锤才能修改[npc.namePos]的武器的属性。"), null);
								}
								
							} else if(index == 5) {
								return new Response("附魔", "无法附魔由其他人拥有的武器！", null);
								
							} else if(index == 6) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[Main.game.getPlayer().getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("主手装备(自己)", "装备" + weapon.getName() + "为主手武器。", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ Main.game.getPlayer().equipMainWeaponFromInventory(weapon, inventoryNPC)
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("主手装备(自己)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if(index == 7) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("副手装备(自己)",
											(weapon.getWeaponType().isPlural()
												?"由于" + weapon.getName() + "需要双手握持，所以只能被装备在主手栏位！"
												:"由于" + weapon.getName() + "是双手武器，只能被装备在主手栏位！"),
											null);
								}
								
								InventorySlot slot = InventorySlot.offhandWeaponSlots[Main.game.getPlayer().getOffhandWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("副手装备(自己)", "装备" + weapon.getName() + "为副手武器。", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, inventoryNPC)
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("副手装备(自己)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[inventoryNPC.getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(inventoryNPC, slot)) {
									return new Response(UtilText.parse(inventoryNPC, "主手装备([npc.HerHim])"), UtilText.parse(inventoryNPC, "让[npc.name]装备"+weapon.getName()+"为主手武器。"), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ inventoryNPC.equipMainWeaponFromInventory(weapon, inventoryNPC)
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response(UtilText.parse(inventoryNPC, "主手装备([npc.HerHim])"), weapon.getCannotBeEquippedText(inventoryNPC, slot), null);
								}
								
							} else if(index == 12) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response(UtilText.parse(inventoryNPC, "副手装备([npc.HerHim])"),
											(weapon.getWeaponType().isPlural()
												?"由于" + weapon.getName() + "需要双手握持，所以只能被装备在主手栏位！"
												:"由于" + weapon.getName() + "是双手武器，只能被装备在主手栏位！"),
											null);
								}
								
								InventorySlot slot = InventorySlot.offhandWeaponSlots[Main.game.getPlayer().getOffhandWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(inventoryNPC, slot)) {
									return new Response(UtilText.parse(inventoryNPC, "副手装备([npc.HerHim])"), UtilText.parse(inventoryNPC, "让[npc.name]装备" + weapon.getName() + "为副手武器。"), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ inventoryNPC.equipOffhandWeaponFromInventory(weapon, inventoryNPC)
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response(UtilText.parse(inventoryNPC, "副手装备([npc.HerHim])"), weapon.getCannotBeEquippedText(inventoryNPC, slot), null);
								}
								
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("拿取(1)", "性交过程中不能拿走他人武器！", null);
								
							} else if(index == 2) {
								return new Response("拿取(5)", "性交过程中不能拿走他人武器！", null);
								
							} else if(index == 3) {
								return new Response("拿取(所有)", "性交过程中不能拿走他人武器！", null);
								
							} else if(index == 4) {
								return new Response("染色", "性交过程中不能染色他人武器！", null);
								
							} else if(index == 5) {
								return new Response("附魔", "无法附魔其他人的武器，尤其是在性交过程中！", null);
								
							} else if(index == 6) {
								return new Response("主手装备(自己)", "性交过程中不能使用他人武器！", null);
								
							} else if(index == 7) {
								return new Response("副手装备(自己)", "性交过程中不能使用他人武器！", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("主手装备(敌人)", "性交过程中不能令他人使用武器！", null);
								
							} else if(index == 12) {
								return new Response("副手装备(敌人)", "性交过程中不能令他人使用武器！", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon) && weapon.getRarity()!=Rarity.QUEST;
							
							if(index == 1) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():weapon.getPrice(inventoryNPC.getSellModifier(weapon));
								if(inventoryFull) {
									return new Response("购买(1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "你的物品栏已经满了！", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
									return new Response("购买(1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "你买不起这个！", null);
								}
								return new Response("购买(1) (" + UtilText.formatAsMoney(sellPrice, "span") + ")", "以" + UtilText.formatAsMoney(sellPrice) + "的价格购买" + weapon.getName() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										sellWeapons(inventoryNPC, Main.game.getPlayer(), weapon, 1, sellPrice);
									}
								};
								
							} else if(index == 2) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():weapon.getPrice(inventoryNPC.getSellModifier(weapon));
								if((buyback && Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<5)
										|| (!buyback && inventoryNPC.getWeaponCount(weapon) < 5)) {
									return new Response("购买(5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", UtilText.parse(inventoryNPC, "[npc.Name]并不拥有五件"+weapon.getNamePlural()+"。"), null);
								}
								if(inventoryFull) {
									return new Response("购买(5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "你的物品栏已经满了！", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*5) {
									return new Response("购买(5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "你买不起这个！", null);
								}
								return new Response("购买(5) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "以"+ UtilText.formatAsMoney(sellPrice*5) + "的价格购买五件" + weapon.getName() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										sellWeapons(inventoryNPC, Main.game.getPlayer(), weapon, 5, sellPrice);
									}
								};
								
							} else if(index == 3) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():weapon.getPrice(inventoryNPC.getSellModifier(weapon));
								int count = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount():inventoryNPC.getWeaponCount(weapon);
								if(inventoryFull) {
									return new Response("购买(所有) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "span")+")", "你的物品栏已经满了！", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*count) {
									int affordableCount = (int)(Main.game.getPlayer().getMoney() / sellPrice);
									if(affordableCount > 0) {
										return new Response("购买(Max " + affordableCount + ") (" + UtilText.formatAsMoney(sellPrice * affordableCount, "span") + ")",
												"以" + UtilText.formatAsMoney(sellPrice * affordableCount) + "的价格购买" + weapon.getName() + "。", INVENTORY_MENU) {
											@Override
											public void effects() {
												sellWeapons(inventoryNPC, Main.game.getPlayer(), weapon, affordableCount, sellPrice);
											}
										};
									} else {
										return new Response("购买(所有) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "span")+")", "你买不起这个！", null);
									}
								}
								return new Response("购买(所有) (" + UtilText.formatAsMoney(sellPrice*count, "span") + ")",
										"以" + UtilText.formatAsMoney(sellPrice*count) + "的价格购买" + weapon.getName() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										sellWeapons(inventoryNPC, Main.game.getPlayer(), weapon, count, sellPrice);
									}
								};
								
							} else if(index == 4) {
								return new Response("染色", UtilText.parse(inventoryNPC, "[npc.Name]不允许你染色[npc.sheIs]要售卖的武器！"), null);
								
							} else if(index == 5) {
								return new Response("附魔", "无法附魔由其他人拥有的武器！", null);
								
							} else if(index == 6) {
								return new Response("主手装备(自己)", UtilText.parse(inventoryNPC, "[npc.Name]不允许你装备[npc.her]的武器，除非先买下。"), null);
								
							} else if(index == 7) {
								return new Response("副手装备(自己)", UtilText.parse(inventoryNPC, "[npc.Name]不允许你装备[npc.her]的武器，除非先买下。"), null);
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "主手装备([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name]不允许你装备[npc.sheIs]要售卖的武器！"), null);
								
							} else if(index == 12) {
								return new Response(UtilText.parse(inventoryNPC, "副手装备([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name]不允许你装备[npc.sheIs]要售卖的武器！"), null);
								
							} else {
								return null;
							}
					}
				}
			}
			return null;
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static final DialogueNode CLOTHING_INVENTORY = new DialogueNode("衣物", "", true) {

		@Override
		public String getLabel() {
			if(!Main.game.isInNewWorld()) {
				return "晚会着装";
			}
			
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "物品栏(快速管理当前<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>开启</b>)";
			} else {
				return "库存";
			}
		}
		
		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(clothing.getDescription(owner));
			sb.append("<p>");
				for(String s : clothing.getExtraDescriptions(null, null, true)) {
					sb.append(s+"<br/>");
				}
				for(InventorySlot is : clothing.getClothingType().getEquipSlots()) {
					List<String> descriptions = clothing.getExtraDescriptions(null, is, true);
					if(!descriptions.isEmpty()) {
						sb.append("<i>当装备在“"+is.getName()+"”栏位时:</i><br/>");
						for(String s : clothing.getExtraDescriptions(null, is, true)) {
							sb.append(s+"<br/>");
						}
					}
				}
			sb.append("</p>");
			sb.append((owner!=null && owner.isPlayer()
							? (inventoryNPC != null && interactionType == InventoryInteraction.TRADING
							? "<p>"
								+(inventoryNPC.willBuy(clothing)
									? inventoryNPC.getName("") + "将以" + UtilText.formatAsMoney(clothing.getPrice(inventoryNPC.getBuyModifier())) + "的价格买下。"
									: inventoryNPC.getName("") + "不想买这个。")
								+"</p>"
							: "")
					:(inventoryNPC != null && interactionType == InventoryInteraction.TRADING
						? "<p>"
								+ inventoryNPC.getName("") + "将以" + UtilText.formatAsMoney(clothing.getPrice(inventoryNPC.getSellModifier(clothing))) + "的价格售出。"
							+ "</p>" 
						: "")));
			
			
			return getItemDisplayPanel(clothing,
						clothing.getSVGString(),
						clothing.getDisplayName(true),
						sb.toString())
					+(interactionType==InventoryInteraction.CHARACTER_CREATION
						?CharacterCreation.getCheckingClothingDescription()
						:"");
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}

			if(Main.game.isBadEnd()) {
				if(index==1) {
					return new Response("不可用", "在坏结局时无法操作物品栏……", null);
				}
				return null;
			}
			
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);

					switch(interactionType) {
						case SEX:
							String dropTitle = owner.getLocationPlace().isItemsDisappear()?"丢弃":"存储";
							if(index == 1) {
								return new Response(dropTitle+"(1)", "自慰时无法丢弃衣物。", null);
								
							} else if(index == 2) {
								return new Response(dropTitle+"(5)", "自慰时无法丢弃衣物。", null);
								
							} else if(index == 3) {
								return new Response(dropTitle+"(所有)", "自慰时无法丢弃衣物。", null);
								
							} else if(index == 4) {
								return new Response("染色", "自慰时无法染色衣物。", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "自慰时无法修复避孕套。", null);
									}
									return new Response("破坏", "自慰时无法破坏避孕套。", null);
								}
								return new Response("附魔", "自慰时无法附魔衣物。", null);

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response("装备："+Util.capitaliseSentence(slot.getName()), "你当前处于[style.colourTerrible(无法行动)]状态，无法装备此物品！", null);
								}
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									if(clothing.isAbleToBeEquippedDuringSex(slot).getKey()) {
										if(!Main.sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer(), Main.game.getPlayer(), clothing)) {
											return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "由于处于特殊性交场景中，你无法装备衣物！", null);
										}
										if(!Main.sex.isClothingEquipAvailable(Main.game.getPlayer(), slot, clothing)) {
											return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "该栏位与当前进行的性动作有关，所以无法在其上装备衣物！", null);
										}
										if(Main.game.getPlayer().isAbleToEquip(clothing, slot, false, Main.game.getPlayer())) {
											return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "装备" + clothing.getName() + "。", Main.sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
													Main.sex.setEquipClothingText(c, Main.game.getPlayer().getUnequipDescription());
													Main.mainController.openInventory();
													Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Main.sex.setSexStarted(true);
												}
											};
										} else {
											return new Response("装备:"+Util.capitaliseSentence(slot.getName()), getClothingBlockingRemovalText(Main.game.getPlayer(), "装备"), null);
										}
										
									} else {
										return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.isAbleToBeEquippedDuringSex(slot).getValue(), null);
									}
									
								} else {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else {
								return null;
							}
					default:
							if(index == 1) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("丢弃(1)", "无法丢弃" + clothing.getName() + "！", null);
										
									} else if(areaFull) {
										return new Response("丢弃(1)", "该区域已经满了，所以无法丢弃" + clothing.getName() + "！", null);
									} else {
										return new Response("丢弃(1)", "丢弃" + clothing.getName() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, 1);
											}
										};
									}
								} else {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Store (1)", "You cannot drop the " + clothing.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("存储(1)", "存储" + clothing.getName() + "！", null);
									} else {
										return new Response("存储(1)", "存储" + clothing.getName() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, 1);
											}
										};
									}
								}
								
							} else if(index == 2) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("丢弃(5)", "无法丢弃" + clothing.getName() + "！", null);
										
									} else if(owner.getClothingCount(clothing) < 5) {
										return new Response("丢弃(5)", "你没有足够的五个" + clothing.getNamePlural() + "！", null);
										
									} else if(areaFull) {
										return new Response("丢弃(5)", "该区域已经满了，所以无法丢弃" + clothing.getNamePlural() + "！", null);
										
									} else {
										return new Response("丢弃(5)", "丢弃五个" + clothing.getNamePlural() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, 5);
											}
										};
									}
								} else {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Store (5)", "You cannot drop the " + clothing.getName() + "!", null);
										
									} else if(owner.getClothingCount(clothing) < 5) {
										return new Response("存储(5)", "你没有足够的五个" + clothing.getNamePlural() + "！", null);
										
									} else if(areaFull) {
										return new Response("存储(5)", "存储" + clothing.getNamePlural() + "！", null);
										
									} else {
										return new Response("存储(5)", "存储五个" + clothing.getNamePlural() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, 5);
											}
										};
									}
								}
								
							} else if(index == 3) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("丢弃(所有)", "无法丢弃" + clothing.getName() + "！", null);
										
									} else if(areaFull) {
										return new Response("丢弃(所有)", "该区域已经满了，所以无法丢弃" + clothing.getNamePlural() + "！", null);
									} else {
										return new Response("丢弃(所有)", "丢弃所有的" + clothing.getNamePlural() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, owner.getClothingCount(clothing));
											}
										};
									}
								} else {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Store (All)", "You cannot drop the " + clothing.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("存储(所有)", "存储" + clothing.getNamePlural() + "！", null);
									} else {
										return new Response("存储(所有)", "存储所有的" + clothing.getNamePlural() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, owner.getClothingCount(clothing));
											}
										};
									}
								}
								
							} else if (index==4) {
								if (isClothingDyeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && clothing.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllClothingInInventory().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("染色",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"你使用精通的[style.colourEarth(土系法术)]为该衣物染色。"
													:"使用染色刷来染色衣物。",
												DYE_CLOTHING) {
											@Override
											public void effects() {
												resetClothingDyeColours();
											}
										};
									} else {
										return new Response("染色", "你的物品栏满了，无法染色这件衣物。", null);
									}
								} else {
									return new Response("染色", "你需要找到染色刷才能染色衣物。", null);
								}
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									return getCondomSabotageResponse(clothing);
								}
								if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									if(clothing.getEnchantmentItemType(null)==null || clothing.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
										return new Response("附魔", "该衣物无法附魔！", null);
										
									} else if(!clothing.isEnchantmentKnown()) {
										if(Main.game.getPlayer().getEssenceCount() >= IDENTIFICATION_ESSENCE_PRICE) {
											return new Response("鉴定([style.italicsArcane("+IDENTIFICATION_ESSENCE_PRICE+"精华)])",
													"为了鉴定"+clothing.getName()+"，你需要付出"+IDENTIFICATION_ESSENCE_PRICE+"奥术精华自己完成，"
															+ "或前往商店付出"+IDENTIFICATION_PRICE+"火币让别人帮忙。",
													CLOTHING_INVENTORY) {
												@Override
												public void effects() {
													Main.game.getPlayer().incrementEssenceCount(-IDENTIFICATION_ESSENCE_PRICE, false);
													
													String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
													
													clothing = AbstractClothing.enchantmentRemovedClothing;
													
													Main.game.getTextEndStringBuilder().append(
															"<p>"
																+ "你引导"+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+"奥术精华的能量进入了"+clothing.getName()
																	+"，它发出一阵淡紫色光芒后，你发现自己已经能够检测到其上的附魔种类了！"
															+ "</p>"
															+ enchantmentRemovedString
															+ "<p style='text-align:center;'>"
																+ "鉴定"+clothing.getName()+"消耗了你[style.boldBad("+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+")][style.boldArcane(奥术精华)]！"
															+ "</p>");
													RenderingEngine.setPage(Main.game.getPlayer(), clothing);
												}
											};
										} else {
											return new Response("鉴定(<i>"+IDENTIFICATION_ESSENCE_PRICE+"精华</i>)",
													"你需要付出"+IDENTIFICATION_ESSENCE_PRICE+"奥术精华自己完成([style.italicsBad(但你的精华不足)])，"
															+ "或前往商店付出"+IDENTIFICATION_PRICE+"火币让别人帮忙。", null);
										}
									}
									return new Response("附魔", "附魔该衣物。", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(clothing);
										}
									};
									
								} else {
									return new Response("附魔", getEnchantmentNotDiscoveredText("clothing"), null);
								}
								
							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "装备" + clothing.getName() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								} else {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("给予(1)", "在战斗时无法给予其衣物！", null);
								
							} else if(index == 2) {
								return new Response("给予(5)", "在战斗时无法给予其衣物！", null);
								
							} else if(index == 3) {
								return new Response("给予(所有)", "在战斗时无法给予其衣物！", null);
								
							} else if(index == 4) {
								return new Response("染色", "在战斗时无法染色你的衣物！", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "在战斗时无法修复避孕套！", null);
									}
									return new Response("破坏", "在战斗时无法破坏避孕套！", null);
								}
								return new Response("附魔", "在战斗时无法附魔衣物！", null);
								
							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "在战斗时无法更换衣物！", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								return new Response("装备:"+Util.capitaliseSentence(slot.getName())+"(敌人)", "在战斗时无法令敌人装备衣物！", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT: case CHARACTER_CREATION:
							boolean inventoryFull = inventoryNPC.isInventoryFull() && !inventoryNPC.hasClothing(clothing);
							
							if(index == 1) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("给予(1)", "你无法给出" + clothing.getName() + "！", null);
									
								} else if(inventoryFull) {
									return new Response("给予(1)", UtilText.parse(inventoryNPC, "[npc.NamePos]的物品栏已经满了！"), null);
								}
								return new Response("给予(1)", UtilText.parse(inventoryNPC, "给予[npc.name]" + clothing.getName() + "。"), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(Main.game.getPlayer(), inventoryNPC, clothing, 1);
									}
								};
								
							} else if(index == 2) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("给予(5)", "你无法给出" + clothing.getName() + "！", null);
									
								} else if(inventoryFull) {
									return new Response("给予(5)", UtilText.parse(inventoryNPC, "[npc.NamePos]的物品栏已经满了！"), null);
								}
								if(Main.game.getPlayer().getClothingCount(clothing) >= 5) {
									return new Response("给予(5)", UtilText.parse(inventoryNPC, "给予[npc.name]五个" + clothing.getNamePlural() + "。"), INVENTORY_MENU){
										@Override
										public void effects(){
											transferClothing(Main.game.getPlayer(), inventoryNPC, clothing, 5);
										}
									};
								} else {
									return new Response("给予(5)", "你没有足够的五个" + clothing.getNamePlural() + "！", null);
								}
								
							} else if(index == 3) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("给予(所有)", "你无法给出" + clothing.getName() + "！", null);
									
								} else if(inventoryFull) {
									return new Response("给予(所有)", UtilText.parse(inventoryNPC, "[npc.NamePos]的物品栏已经满了！"), null);
								}
								return new Response("给予(所有)", UtilText.parse(inventoryNPC, "给予[npc.name]你所有的" + clothing.getNamePlural() + "。"), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(Main.game.getPlayer(), inventoryNPC, clothing, Main.game.getPlayer().getClothingCount(clothing));
									}
								};
								
							} else if (index==4) {
								if (isClothingDyeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull();
									boolean isDyeingStackItem = Main.game.getPlayer().getAllClothingInInventory().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("染色",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"你使用精通的[style.colourEarth(土系法术)]为该物品染色。"
													:"使用染色刷来染色衣物。",
												DYE_CLOTHING) {
											@Override
											public void effects() {
												resetClothingDyeColours();
											}
										};
									} else {
										return new Response("染色", "你的物品栏满了，无法染色这件衣物。", null);
									}
								} else {
									return new Response("染色", "你需要找到染色刷才能染色衣物。", null);
								}
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									return getCondomSabotageResponse(clothing);
								}
								if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									if(clothing.getEnchantmentItemType(null)==null || clothing.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
										return new Response("附魔", "该衣物无法附魔！", null);
										
									} else if(!clothing.isEnchantmentKnown()) {
										if(Main.game.getPlayer().getEssenceCount() >= IDENTIFICATION_ESSENCE_PRICE) {
											return new Response("鉴定([style.italicsArcane("+IDENTIFICATION_ESSENCE_PRICE+"精华)])",
													"为了鉴定"+clothing.getName()+"，你需要付出"+IDENTIFICATION_ESSENCE_PRICE+"奥术精华自己完成，"
															+ "或前往商店付出"+IDENTIFICATION_PRICE+"火币让别人帮忙。",
													CLOTHING_INVENTORY) {
												@Override
												public void effects() {
													Main.game.getPlayer().incrementEssenceCount(-IDENTIFICATION_ESSENCE_PRICE, false);

													String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
													
													clothing = AbstractClothing.enchantmentRemovedClothing;
													
													Main.game.getTextEndStringBuilder().append(
															"<p>"
																+ "你引导"+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+"奥术精华的能量进入了"+clothing.getName()
																	+"，它发出一阵淡紫色光芒后，你发现自己已经能够检测到其上的附魔种类了！"
															+ "</p>"
															+ enchantmentRemovedString
															+ "<p style='text-align:center;'>"
																+ "鉴定"+clothing.getName()+"消耗了你[style.boldBad("+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+")][style.boldArcane(奥术精华)]！"
															+ "</p>");
													RenderingEngine.setPage(Main.game.getPlayer(), clothing);
												}
											};
										} else {
											return new Response("鉴定(<i>"+IDENTIFICATION_ESSENCE_PRICE+"精华</i>)",
													"你需要付出"+IDENTIFICATION_ESSENCE_PRICE+"奥术精华自己完成([style.italicsBad(但你的精华不足)])，"
															+ "或前往商店付出"+IDENTIFICATION_PRICE+"火币让别人帮忙。", null);
										}
									}
									return new Response("附魔", "附魔该衣物。", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(clothing);
										}
									};
									
								} else {
									return new Response("附魔", getEnchantmentNotDiscoveredText("clothing"), null);
								}
								
							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "装备" + clothing.getName() + "。", INVENTORY_MENU) {
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								} else {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
									
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
//								if(!clothing.getClothingType().isAbleToBeDropped()) {
//									return new Response(
//											UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
//											"You cannot give away the " + clothing.getName() + "!",
//											null);
//								}
								Value<Boolean, String> equipAllowed = inventoryNPC.isInventoryEquipAllowed(clothing, slot);
								if(!equipAllowed.getKey()) {
									return new Response(
											UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
											UtilText.parse(inventoryNPC, equipAllowed.getValue()),
											null);
								}
								if(clothing.isCanBeEquipped(inventoryNPC, slot)) {
									if(inventoryNPC.isAbleToEquip(clothing, slot, true, Main.game.getPlayer()) && clothing.isEnslavementClothing() && (!inventoryNPC.isSlave() || !inventoryNPC.getOwner().isPlayer())) {
										boolean willEnslave = !inventoryNPC.isSlave() && inventoryNPC.isAbleToBeEnslaved() && Main.game.getPlayer().isHasSlaverLicense();
										return new Response(
												UtilText.parse(inventoryNPC,
														!willEnslave
															?"[style.colourMinorBad(装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim]))]"
															:"[style.colourArcane(装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim]))]"),
												UtilText.parse(inventoryNPC,
														"让[npc.name]装备"+clothing.getName()+"。"
														+(!willEnslave
															?"<br/><i>尽管"+clothing.getName()+""
																	+(clothing.getClothingType().isPlural()?"拥有奴役附魔，它":"拥有奴役附魔，它")
																	+"[style.colourMinorBad(无法奴役[npc.name])]，原因是"
																	+ (Main.game.getPlayer().isHasSlaverLicense()
																			?"[npc.sheIsFull]并非合适的奴役对象"
																			:"你没有贩奴许可")
																	+"！</i>"
															:"<br/><i>多亏了"+clothing.getName()+""
																	+(clothing.getClothingType().isPlural()?"拥有奴役附魔，它":"拥有奴役附魔，它")
																	+"[style.colourArcane(能够奴役[npc.name])]，令你成为[npc.her]的新主人！</i>")),
												INVENTORY_MENU){
											@Override
											public DialogueNode getNextDialogue() {
												if(inventoryNPC.getEnslavementDialogue(clothing)!=null) {
													return inventoryNPC.getEnslavementDialogue(clothing);
													
												} else {
													return INVENTORY_MENU;
												}
											}
											@Override
											public void effects() {
												List<NPC> enslavementTargets = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
//												enslavementTargets.removeIf((npc) -> Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()));
												enslavementTargets.removeIf((npc) -> !Main.combat.getEnemies(Main.game.getPlayer()).contains(npc));
												if(enslavementTargets.size()<=1) {
													SlaveDialogue.setFollowupEnslavementDialogue(Main.game.getDefaultDialogue(false));
												} else {
													SlaveDialogue.setFollowupEnslavementDialogue(Main.game.getSavedDialogueNode());
												}
												if(inventoryNPC.getEnslavementDialogue(clothing)==null) {
													Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing) + "</p>");
													
												} else {
													equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing);
												}
											}
										};
										
									} else {
										return new Response(
												UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
												UtilText.parse(inventoryNPC, "令[npc.name]装备"+clothing.getName()+"！"),
												INVENTORY_MENU){
											@Override
											public void effects(){
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing) + "</p>");
											}
										};
									}
									
								} else {
									return new Response(
											UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
											clothing.getCannotBeEquippedText(inventoryNPC, slot),
											null);
								}
							
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("给予(1)", "性交过程中无法给予对方衣物！", null);
								
							} else if(index == 2) {
								return new Response("给予(5)", "性交过程中无法给予对方衣物！", null);
								
							} else if(index == 3) {
								return new Response("给予(所有)", "性交过程中无法给予对方衣物！", null);
								
							} else if(index == 4) {
								return new Response("染色", "性交过程中无法染色你的衣服！", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "性交过程中无法修复避孕套！", null);
									}
									return new Response("破坏", "性交过程中无法破坏避孕套！", null);
								}
								return new Response("附魔", "性交过程中无法附魔衣物！", null);

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response("装备："+Util.capitaliseSentence(slot.getName()), "你当前处于[style.colourTerrible(无法行动)]状态，无法装备此物品！", null);
								}
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									if(clothing.isAbleToBeEquippedDuringSex(slot).getKey()) {
										if(!Main.sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer(), Main.game.getPlayer(), clothing)) {
											return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "由于处于特殊性交场景中，你无法装备衣物！", null);
										}
										if(!Main.sex.isClothingEquipAvailable(Main.game.getPlayer(), slot, clothing)) {
											return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "该栏位与当前进行的性动作有关，所以无法在其上装备衣物！", null);
										}
										if (Main.game.getPlayer().isAbleToEquip(clothing, slot, false, Main.game.getPlayer())) {
											return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "装备" + clothing.getName() + "。", Main.sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
													Main.sex.setEquipClothingText(c, Main.game.getPlayer().getUnequipDescription());
													Main.mainController.openInventory();
													Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Main.sex.setSexStarted(true);
												}
											};
										} else {
											return new Response("装备:"+Util.capitaliseSentence(slot.getName()), getClothingBlockingRemovalText(Main.game.getPlayer(), "装备"), null);
										}
										
									} else {
										return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.isAbleToBeEquippedDuringSex(slot).getValue(), null);
									}
									
								} else {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
//								if(!clothing.getClothingType().isAbleToBeDropped()) {
//									return new Response(
//											UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
//											"You cannot give away the " + clothing.getName() + "!",
//											null);
//								}
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(
											UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
											UtilText.parse(inventoryNPC, "你当前处于[style.colourTerrible(无法行动)]状态，无法让[npc.name]装备" + clothing.getName() + "！"),
											null);
								}
								Value<Boolean, String> equipAllowed = inventoryNPC.isInventoryEquipAllowed(clothing, slot);
								if(!equipAllowed.getKey()) {
									return new Response(
											UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
											UtilText.parse(inventoryNPC, equipAllowed.getValue()),
											null);
								}
								if(clothing.isCanBeEquipped(inventoryNPC, slot)) {
									if(clothing.isAbleToBeEquippedDuringSex(slot).getKey()) {
										if(!Main.sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer(), inventoryNPC, clothing)) {
											return new Response(
													UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
													"由于处于特殊性交场景中，你无法装备衣物！",
													null);
										}
										if(!Main.sex.isClothingEquipAvailable(inventoryNPC, slot, clothing)) {
											return new Response(
													UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
													"该栏位与当前进行的性动作有关，所以无法在其上装备衣物！",
													null);
										}
										if(inventoryNPC.isAbleToEquip(clothing, slot, false, Main.game.getPlayer())) {
											return new Response(
													UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
													UtilText.parse(inventoryNPC, "令[npc.name]装备" + clothing.getName() + "。"),
													Main.sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing);
													Main.sex.setEquipClothingText(c, inventoryNPC.getUnequipDescription());
													Main.mainController.openInventory();
													Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Main.sex.setSexStarted(true);
												}
											};
										} else {
											return new Response(UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
													UtilText.parse(inventoryNPC, "[npc.Name]无法装备" + clothing.getName() + "，原因是其他装备阻止了[npc.herHim]这么做！"), null);
										}
										
									} else {
										return new Response(UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"), clothing.isAbleToBeEquippedDuringSex(slot).getValue(), null);
									}
									
								} else {
									return new Response(UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"), clothing.getCannotBeEquippedText(inventoryNPC, slot), null);
								}
								
							} else {
								return null;
							}
							
						case TRADING:
							if(index == 1) {
								if(!clothing.getClothingType().isAbleToBeSold()) {
									return new Response("出售(1)", "你不能出售" + clothing.getName() + "！", null);
									
								} else if (inventoryNPC.willBuy(clothing)) {
									int sellPrice = clothing.getPrice(inventoryNPC.getBuyModifier());
									return new Response("出售(1) (" + UtilText.formatAsMoney(sellPrice, "span") + ")", "以" + UtilText.formatAsMoney(sellPrice) + "的价格出售" + clothing.getName() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											sellClothing(Main.game.getPlayer(), inventoryNPC, clothing, 1, sellPrice);
										}
									};
								} else {
									return new Response("出售(1)", inventoryNPC.getName("") + "不想买这个。", null);
								}
								
							} else if(index == 2) {
								if(!clothing.getClothingType().isAbleToBeSold()) {
									return new Response("出售(5)", "你不能出售" + clothing.getName() + "！", null);
									
								} else if(Main.game.getPlayer().getClothingCount(clothing) >= 5) {
									if (inventoryNPC.willBuy(clothing)) {
										int sellPrice = clothing.getPrice(inventoryNPC.getBuyModifier());
										return new Response("出售(5) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "以" + UtilText.formatAsMoney(sellPrice*5) + "的价格触手五个" + clothing.getNamePlural() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												sellClothing(Main.game.getPlayer(), inventoryNPC, clothing, 5, sellPrice);
											}
										};
									} else {
										return new Response("出售(5)", inventoryNPC.getName("") + "不想买这些。", null);
									}
									
								} else {
									return new Response("出售(5)", "你没有足够的五个" + clothing.getNamePlural() + "！", null);
								}
								
							} else if(index == 3) {
								if(!clothing.getClothingType().isAbleToBeSold()) {
									return new Response("出售(所有)", "你不能出售" + clothing.getName() + "！", null);
									
								} else if (inventoryNPC.willBuy(clothing)) {
									int sellPrice = clothing.getPrice(inventoryNPC.getBuyModifier());
									return new Response("出售(所有) (" + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getClothingCount(clothing), "span") + ")",
											"以" + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getClothingCount(clothing)) + "的价格出售" + clothing.getName() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											sellClothing(Main.game.getPlayer(), inventoryNPC, clothing, Main.game.getPlayer().getClothingCount(clothing), sellPrice);
										}
									};
								} else {
									return new Response("出售(所有)", inventoryNPC.getName("") + "不想买这些。", null);
								}
								
							} else if (index==4) {
								if (isClothingDyeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && clothing.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllClothingInInventory().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("染色", 
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"你使用精通的[style.colourEarth(土系法术)]为该物品染色。"
													:"使用染色刷来染色衣物。",
												DYE_CLOTHING) {
											@Override
											public void effects() {
												resetClothingDyeColours();
											}
										};
									} else {
										return new Response("染色", "你的物品栏满了，无法染色这件衣物。", null);
									}
								} else {
									return new Response("染色", "你需要找到染色刷才能染色衣物。", null);
								}
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									return getCondomSabotageResponse(clothing);
								}
								if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									if(clothing.getEnchantmentItemType(null)==null || clothing.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
										return new Response("附魔", "该衣物无法附魔！", null);
										
									} else if(!clothing.isEnchantmentKnown()) {
										if(Main.game.getPlayer().getEssenceCount() >= IDENTIFICATION_ESSENCE_PRICE) {
											return new Response("鉴定([style.italicsArcane("+IDENTIFICATION_ESSENCE_PRICE+"精华)])",
													"为了鉴定"+clothing.getName()+"，你需要付出"+IDENTIFICATION_ESSENCE_PRICE+"奥术精华自己完成，"
															+ "或前往商店付出"+IDENTIFICATION_PRICE+"火币让别人帮忙。",
													CLOTHING_INVENTORY) {
												@Override
												public void effects() {
													Main.game.getPlayer().incrementEssenceCount(-IDENTIFICATION_ESSENCE_PRICE, false);

													String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
													
													clothing = AbstractClothing.enchantmentRemovedClothing;
													
													Main.game.getTextEndStringBuilder().append(
															"<p>"
																+ "你引导"+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+"奥术精华的能量进入了"+clothing.getName()
																	+"，它发出一阵淡紫色光芒后，你发现自己已经能够检测到其上的附魔种类了！"
															+ "</p>"
															+ enchantmentRemovedString
															+ "<p style='text-align:center;'>"
																+ "鉴定"+clothing.getName()+"消耗了你[style.boldBad("+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+")][style.boldArcane(奥术精华)]！"
															+ "</p>");
													RenderingEngine.setPage(Main.game.getPlayer(), clothing);
												}
											};
										} else {
											return new Response("鉴定(<i>"+IDENTIFICATION_ESSENCE_PRICE+"精华</i>)",
													"你需要付出"+IDENTIFICATION_ESSENCE_PRICE+"奥术精华自己完成([style.italicsBad(但你的精华不足)])，"
															+ "或前往商店付出"+IDENTIFICATION_PRICE+"火币让别人帮忙。", null);
										}
									}
									return new Response("附魔", "附魔该衣物。", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(clothing);
										}
									};
									
								} else {
									return new Response("附魔", getEnchantmentNotDiscoveredText("clothing"), null);
								}

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "装备" + clothing.getName() + "。", INVENTORY_MENU){
											@Override
											public void effects(){
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
											}
										};
								} else {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							}
//							else if (index == 10) {
//								return getQuickTradeResponse();
//
//							}
							else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								return new Response(UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name]不想穿你的衣物。"), null);
								
							} else if (index == 10 && !clothing.isEnchantmentKnown()) {
								if(!inventoryNPC.willBuy(clothing)) {
									return new Response("鉴定", inventoryNPC.getName("") + "无法鉴定衣物！", null);
									
								} else if(Main.game.getPlayer().getMoney() < IDENTIFICATION_PRICE){
									// don't format as money because we don't want to highlight non-selectable choices
									return new Response("鉴定(" + UtilText.formatAsMoneyUncoloured(IDENTIFICATION_PRICE, "span") + ")", "你的钱不够！", null);
									
								}else {
									return new Response("鉴定(" + UtilText.formatAsMoney(IDENTIFICATION_PRICE, "span") + ")",
												"以" + UtilText.formatAsMoney(IDENTIFICATION_PRICE, "span") + "的价格让" + clothing.getName() + "鉴定。" , CLOTHING_INVENTORY){
										@Override
										public void effects(){
											Main.game.getPlayer().incrementMoney(-IDENTIFICATION_PRICE);

											String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
											
											clothing = AbstractClothing.enchantmentRemovedClothing;
											
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ UtilText.parse(inventoryNPC,
																"你将" + UtilText.formatAsMoney(IDENTIFICATION_PRICE) + "递给了[npc.name]，"
																		+ "对方先是在特殊的检定装置上撒了几瓶奥术精华，接着便用这个装置展示出了"+clothing.getName()+"上的附魔。")
													+ "</p>"
													+enchantmentRemovedString);
											
											RenderingEngine.setPage(Main.game.getPlayer(), clothing);
										}
									};
								}
							} else {
								return null;
							}
					}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {

					boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing) && clothing.getRarity()!=Rarity.QUEST;
					switch(interactionType) {
						case CHARACTER_CREATION:
							if (index == 1) {
								if(Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.NIPPLES)
										|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.ANUS)
										|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.PENIS)
										|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.VAGINA)
										|| (Main.game.getPlayer().getClothingInSlot(InventorySlot.FOOT)==null && Main.game.getPlayer().getLegType().equals(LegType.HUMAN))) {
									return new Response("前往舞台", "你需要先穿好能够遮盖身体的衣物，还需要一双鞋。", null);
									
								} else {
									return new Response("前往舞台", "你已经准备好前往舞台了。", CharacterCreation.CHOOSE_BACKGROUND) {
										@Override
										public void effects() {
											CharacterCreation.moveNPCIntoPlayerTile();
										}
									};
								}
								
							} else if(index == 4) {
								if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()){
									return new Response("脱下所有衣物", "你现在已经光着了，没有衣物可以脱。", null);
								}
								else{
									return new Response("脱下所有衣物", "尽可能地脱下衣物。", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(unequipAll(Main.game.getPlayer()));
										}
									};
								}
								
							} else if(index == 5) {
								return new Response("更改颜色", "更改这件衣物的颜色。", DYE_CLOTHING_CHARACTER_CREATION) {
									@Override
									public void effects() {
										resetClothingDyeColours();
									}
								};
								
							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "装备" + clothing.getName() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											equipClothingFromGround(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
										}
									};
									
								} else {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
							
							} else {
								return null;
							}
					
					case SEX:
						if(index == 1) {
							return new Response("拿取(1)", "自慰时无法拿起衣物。", null);
							
						} else if(index == 2) {
							return new Response("拿取(5)", "自慰时无法拿起衣物。", null);
							
						} else if(index == 3) {
							return new Response("拿取(所有)", "自慰时无法拿起衣物。", null);
							
						} else if(index == 4) {
							return new Response("染色", "自慰时无法染色衣物。", null);
							
						} else if(index == 5) {
							if(clothing.isCondom()) {
								if(clothing.getCondomEffect().getPotency().isNegative()) {
									return new Response("修复(<i>1精华</i>)", "无法修复地上的避孕套！", null);
								}
								return new Response("破坏", "无法破坏地上的避孕套！", null);
							}
							if(!clothing.isEnchantmentKnown()) {
								return new Response("鉴定", "性交过程中无法鉴定衣物！", null);
							}
							return new Response("附魔", "你无法附魔在地上的衣物！", null);

						} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
							InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
							if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
								return new Response("装备："+Util.capitaliseSentence(slot.getName()), "你当前处于[style.colourTerrible(无法行动)]状态，无法装备此物品！", null);
							}
							if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
								if(clothing.isAbleToBeEquippedDuringSex(slot).getKey()) {
									if(!Main.sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer(), Main.game.getPlayer(), clothing)) {
										return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "由于处于特殊性交场景中，你无法装备衣物！", null);
									}
									if(!Main.sex.isClothingEquipAvailable(Main.game.getPlayer(), slot, clothing)) {
										return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "该栏位与当前进行的性动作有关，所以无法在其上装备衣物！", null);
									}
									if (Main.game.getPlayer().isAbleToEquip(clothing, slot, false, Main.game.getPlayer())) {
										return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "装备" + clothing.getName() + "。", Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												AbstractClothing c = clothing;
												equipClothingFromGround(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
												Main.sex.setEquipClothingText(c, Main.game.getPlayer().getUnequipDescription());
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Main.sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("装备:"+Util.capitaliseSentence(slot.getName()), getClothingBlockingRemovalText(Main.game.getPlayer(), "装备"), null);
									}
								} else {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.isAbleToBeEquippedDuringSex(slot).getValue(), null);
								}
							} else {
								return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();

						} else {
							return null;
						}
					
					default:
						if(index == 1) {
							if(inventoryFull) {
								return new Response("拿取(1)", "你的物品栏已经满了！", null);
							}
							return new Response("拿取(1)", "从地上拿取一个" + clothing.getName() + "。", INVENTORY_MENU){
								@Override
								public void effects(){
									pickUpClothing(Main.game.getPlayer(), clothing, 1);
								}
							};
							
						} else if(index == 2) {
							if(inventoryFull) {
								return new Response("拿取(5)", "你的物品栏已经满了！", null);
							}
							if(Main.game.getPlayerCell().getInventory().getClothingCount(clothing) >= 5) {
								return new Response("拿取(5)", "从地上拿取五个" + clothing.getNamePlural() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpClothing(Main.game.getPlayer(), clothing, 5);
									}
								};
							} else {
								return new Response("拿取(5)", "地上不足五个" + clothing.getNamePlural() + "！", null);
							}
							
						} else if(index == 3) {
							if(inventoryFull) {
								return new Response("拿取(所有)", "你的物品栏已经满了！", null);
							}
							return new Response("拿取(所有)", "从地上拿取所有" + clothing.getNamePlural() + "。", INVENTORY_MENU){
								@Override
								public void effects(){
									pickUpClothing(Main.game.getPlayer(), clothing, Main.game.getPlayerCell().getInventory().getClothingCount(clothing));
								}
							};
							
						} else if (index==4) {
							if (isClothingDyeActionAvailable()) {
								boolean hasFullInventory = Main.game.getPlayerCell().getInventory().isInventoryFull();
								boolean isDyeingStackItem = Main.game.getPlayerCell().getInventory().getAllClothingInInventory().get(clothing) > 1;
								boolean canDye = !(isDyeingStackItem && hasFullInventory);
								if (canDye) {
									return new Response("染色", 
											Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"你使用精通的[style.colourEarth(土系法术)]为该物品染色。"
												:"使用染色刷来染色衣物。",
											DYE_CLOTHING) {
										@Override
										public void effects() {
											resetClothingDyeColours();
										}
									};
								} else {
									return new Response("染色", "你的物品栏满了，无法染色这件衣物。", null);
								}
							} else {
								return new Response("染色", "你需要找到染色刷才能染色衣物。", null);
							}
							
						} else if(index == 5) {
							if(clothing.isCondom()) {
								if(clothing.getCondomEffect().getPotency().isNegative()) {
									return new Response("修复(<i>1精华</i>)", "无法修复地上的避孕套！", null);
								}
								return new Response("破坏", "无法破坏地上的避孕套！", null);
							}
							if(!clothing.isEnchantmentKnown()) {
								if(Main.game.getPlayer().getEssenceCount() >= IDENTIFICATION_ESSENCE_PRICE) {
									return new Response("鉴定([style.italicsArcane("+IDENTIFICATION_ESSENCE_PRICE+"精华)])",
											"为了鉴定"+clothing.getName()+"，你需要付出"+IDENTIFICATION_ESSENCE_PRICE+"奥术精华自己完成，"
													+ "或前往商店付出"+IDENTIFICATION_PRICE+"火币让别人帮忙。",
											CLOTHING_INVENTORY) {
										@Override
										public void effects() {
											Main.game.getPlayer().incrementEssenceCount(-IDENTIFICATION_ESSENCE_PRICE, false);
											
											Main.game.getPlayerCell().getInventory().removeClothing(clothing);
											String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
											Main.game.getPlayerCell().getInventory().addClothing(clothing);
											
//											clothing = AbstractClothing.enchantmentRemovedClothing;
											
											Main.game.getTextEndStringBuilder().append(
													"<p>"
														+ "你引导"+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+"奥术精华的能量进入了"+clothing.getName()
															+"，它发出一阵淡紫色光芒后，你发现自己已经能够检测到其上的附魔种类了！"
													+ "</p>"
													+ enchantmentRemovedString
													+ "<p style='text-align:center;'>"
														+ "鉴定"+clothing.getName()+"消耗了你[style.boldBad("+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+")][style.boldArcane(奥术精华)]！"
													+ "</p>");
											RenderingEngine.setPage(Main.game.getPlayer(), clothing);
										}
									};
								} else {
									return new Response("鉴定(<i>"+IDENTIFICATION_ESSENCE_PRICE+"精华</i>)",
											"你需要付出"+IDENTIFICATION_ESSENCE_PRICE+"奥术精华自己完成([style.italicsBad(但你的精华不足)])，"
													+ "或前往商店付出"+IDENTIFICATION_PRICE+"火币让别人帮忙。", null);
								}
							}
							return new Response("附魔", "你无法附魔在地上的衣物！", null);
							
						} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
							InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
							if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
								return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "装备" + clothing.getName() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromGround(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
									}
								};
							} else {
								return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					boolean inventoryFull = false;
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("拿取(1)", "在战斗时无法拿取他人的衣物！", null);
								
							} else if(index == 2) {
								return new Response("拿取(5)", "在战斗时无法拿取他人的衣物！", null);
								
							} else if(index == 3) {
								return new Response("拿取(所有)", "在战斗时无法拿取他人的衣物！", null);
								
							} else if(index == 4) {
								return new Response("染色", "在战斗时无法染色他人的衣物！", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "无法修复他人的避孕套，尤其是在战斗过程中！", null);
									}
									return new Response("破坏", "无法破坏他人的避孕套，尤其是在战斗过程中！", null);
								}
								return new Response("附魔", "你法附魔其他人的衣物，尤其是在战斗过程中！", null);
								
							} else if(index == 6) {
								return new Response("装备(自己)", "在战斗时无法使用他人的衣物！", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "装备:([npc.HerHim])"), "在战斗时无法令他人穿上衣物！", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT: case CHARACTER_CREATION:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing) && clothing.getRarity()!=Rarity.QUEST;
						
							if(index == 1) {
								if(inventoryFull) {
									return new Response("拿取(1)", "你的物品栏已经满了！", null);
								}
								return new Response("拿取(1)", UtilText.parse(inventoryNPC, "从[npc.Name]处拿取" + clothing.getName() + "。"), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(inventoryNPC, Main.game.getPlayer(), clothing, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("拿取(5)", "你的物品栏已经满了！", null);
								}
								if(inventoryNPC.getClothingCount(clothing) >= 5) {
									return new Response("拿取(5)", UtilText.parse(inventoryNPC, "从[npc.Name]处拿取五个" + clothing.getNamePlural() + "。"), INVENTORY_MENU){
										@Override
										public void effects(){
											transferClothing(inventoryNPC, Main.game.getPlayer(), clothing, 5);
										}
									};
								} else {
									return new Response("拿取(5)", UtilText.parse(inventoryNPC, "[npc.Name]没有五个" + clothing.getNamePlural() + "！"), null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("拿取(所有)", "你的物品栏已经满了！", null);
								}
								return new Response("拿取(所有)", UtilText.parse(inventoryNPC, "从[npc.Name]处拿取所有"+Util.intToString(inventoryNPC.getClothingCount(clothing))+"个" + clothing.getNamePlural() + "。"), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(inventoryNPC, Main.game.getPlayer(), clothing, inventoryNPC.getClothingCount(clothing));
									}
								};
								
							} else if (index==4) {
								if (isClothingDyeActionAvailable()) {
									boolean hasFullInventory = inventoryNPC.isInventoryFull();
									boolean isDyeingStackItem = clothing!=null && inventoryNPC.getAllClothingInInventory().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("染色", 
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"你使用精通的[style.colourEarth(土系法术)]为该物品染色。"
													:"使用染色刷来染色衣物。",
												DYE_CLOTHING) {
											@Override
											public void effects() {
												resetClothingDyeColours();
											}
										};
									} else {
										return new Response("染色", UtilText.parse(inventoryNPC, "[npc.NamePos]的物品栏满了，无法染色这件衣物。"), null);
									}
								} else {
									return new Response("染色", UtilText.parse(inventoryNPC, "你需要找到另一个染色刷才能染色[npc.namePos]的衣物。"), null);
								}
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "无法修复由他人拥有的避孕套！", null);
									}
									return new Response("破坏", "无法破坏由他人拥有的避孕套！", null);
								}
								return new Response("附魔", "无法附魔由其他人拥有的衣物！", null);

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "装备" + clothing.getName() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								} else {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								Value<Boolean, String> equipAllowed = inventoryNPC.isInventoryEquipAllowed(clothing, slot);
								if(!equipAllowed.getKey()) {
									return new Response(
											UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
											UtilText.parse(inventoryNPC, equipAllowed.getValue()),
											null);
								}
								if(clothing.isCanBeEquipped(inventoryNPC, slot)) {
									if(inventoryNPC.isAbleToEquip(clothing, slot, true, Main.game.getPlayer()) && clothing.isEnslavementClothing() && (!inventoryNPC.isSlave() || !inventoryNPC.getOwner().isPlayer())) {
										boolean willEnslave = !inventoryNPC.isSlave() && inventoryNPC.isAbleToBeEnslaved() && Main.game.getPlayer().isHasSlaverLicense();
										return new Response(
												UtilText.parse(inventoryNPC,
														!willEnslave
															?"[style.colourMinorBad(装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim]))]"
															:"[style.colourArcane(装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim]))]"),
												UtilText.parse(inventoryNPC,
														"令[npc.name]装备"+clothing.getName()+"。"
														+(!willEnslave
															?"<br/><i>尽管"+clothing.getName()+""
																	+(clothing.getClothingType().isPlural()?"拥有奴役附魔，它":"拥有奴役附魔，它")
																	+"[style.colourMinorBad(无法奴役[npc.name])]，原因是"
																	+ (Main.game.getPlayer().isHasSlaverLicense()
																			?"[npc.sheIsFull]并非合适的奴役对象"
																			:"你没有贩奴许可")
																	+"！</i>"
															:"<br/><i>多亏了"+clothing.getName()+""
																	+(clothing.getClothingType().isPlural()?"拥有奴役附魔，它":"拥有奴役附魔，它")
																	+"[style.colourArcane(能够奴役[npc.name])]，令你成为[npc.her]的新主人！</i>")),
												INVENTORY_MENU){
											@Override
											public DialogueNode getNextDialogue() {
												if(inventoryNPC.getEnslavementDialogue(clothing)!=null) {//inventoryNPC.isAbleToBeEnslaved() && !inventoryNPC.isSlave()) {
													return inventoryNPC.getEnslavementDialogue(clothing);
													
												} else {
													return INVENTORY_MENU;
												}
											}
											@Override
											public void effects() {
												List<NPC> enslavementTargets = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
//												enslavementTargets.removeIf((npc) -> Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()));
												enslavementTargets.removeIf((npc) -> !Main.combat.getEnemies(Main.game.getPlayer()).contains(npc));
												if(enslavementTargets.size()<=1) {
													SlaveDialogue.setFollowupEnslavementDialogue(Main.game.getDefaultDialogue(false));
												} else {
													SlaveDialogue.setFollowupEnslavementDialogue(Main.game.getSavedDialogueNode());
												}
												if(inventoryNPC.getEnslavementDialogue(clothing)==null) {
													Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing) + "</p>");
													
												} else {
													equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing);
												}
											}
										};
										
									} else {
										return new Response(
												UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
												UtilText.parse(inventoryNPC, "令[npc.name]装备" + clothing.getName() + "。"),
												INVENTORY_MENU){
											@Override
											public void effects(){
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing) + "</p>");
											}
										};
									}
								} else {
									return new Response(
											UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
											clothing.getCannotBeEquippedText(inventoryNPC, slot),
											null);
								}
								
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("拿取(1)", "性交过程中不能拿走他人衣物！", null);
								
							} else if(index == 2) {
								return new Response("拿取(5)", "性交过程中不能拿走他人衣物！", null);
								
							} else if(index == 3) {
								return new Response("拿取(所有)", "性交过程中不能拿走他人衣物！", null);
								
							} else if(index == 4) {
								return new Response("染色", "性交过程中无法染色他人的衣物！", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "无法修复他人的避孕套，尤其是在性交过程中！", null);
									}
									return new Response("破坏", "无法破坏他人的避孕套，尤其是在性交过程中！", null);
								}
								return new Response("附魔", "无法附魔其他人的衣物，尤其是在性交过程中！", null);

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) { //TODO ???
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response("装备："+Util.capitaliseSentence(slot.getName()), "你当前处于[style.colourTerrible(无法行动)]状态，无法装备此物品！", null);
								}
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									if(clothing.isAbleToBeEquippedDuringSex(slot).getKey() && !inventoryNPC.isTrader()) {
										if(!Main.sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer(), Main.game.getPlayer(), clothing)) {
											return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "由于处于特殊性交场景中，你无法装备衣物！", null);
										}
										if(!Main.sex.isClothingEquipAvailable(Main.game.getPlayer(), slot, clothing)) {
											return new Response("装备:"+Util.capitaliseSentence(slot.getName()), "该栏位与当前进行的性动作有关，所以无法在其上装备衣物！", null);
										}
										if (Main.game.getPlayer().isAbleToEquip(clothing, slot, false, Main.game.getPlayer())) {
											return new Response("装备: "+Util.capitaliseSentence(slot.getName()), "装备" + clothing.getName() + "。", Main.sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
													Main.sex.setEquipClothingText(c, Main.game.getPlayer().getUnequipDescription());
													Main.mainController.openInventory();
													Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Main.sex.setSexStarted(true);
												}
											};
										} else {
											return new Response("装备:"+Util.capitaliseSentence(slot.getName()), getClothingBlockingRemovalText(Main.game.getPlayer(), "装备"), null);
										}
										
									} else {
										return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.isAbleToBeEquippedDuringSex(slot).getValue(), null);
									}
									
								} else {
									return new Response("装备:"+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(
											UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
											UtilText.parse(inventoryNPC, "你当前处于[style.colourTerrible(无法行动)]状态，无法让[npc.name]装备" + clothing.getName() + "！"),
											null);
								}
								Value<Boolean, String> equipAllowed = inventoryNPC.isInventoryEquipAllowed(clothing, slot);
								if(!equipAllowed.getKey()) {
									return new Response(
											UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
											UtilText.parse(inventoryNPC, equipAllowed.getValue()),
											null);
								}
								if(clothing.isCanBeEquipped(inventoryNPC, slot)) {
									if(clothing.isAbleToBeEquippedDuringSex(slot).getKey() && !inventoryNPC.isTrader()) {
										if(!Main.sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer(), inventoryNPC, clothing)) {
											return new Response(
													UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
													"由于处于特殊性交场景中，你无法装备衣物！",
													null);
										}
										if(!Main.sex.isClothingEquipAvailable(inventoryNPC, slot, clothing)) {
											return new Response(
													UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"), 
													"该栏位与当前进行的性动作有关，所以无法在其上装备衣物！",
													null);
										}
										if (inventoryNPC.isAbleToEquip(clothing, slot, false, Main.game.getPlayer())) {
											return new Response(
													UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
													UtilText.parse(inventoryNPC, "令[npc.name]装备" + clothing.getName() + "。"),
													Main.sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing);
													Main.sex.setEquipClothingText(c, inventoryNPC.getUnequipDescription());
													Main.mainController.openInventory();
													Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Main.sex.setSexStarted(true);
												}
											};
										} else {
											return new Response(
													UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
													UtilText.parse(inventoryNPC, "[npc.Name]无法装备" + clothing.getName() + "，原因是其他装备阻止了[npc.herHim]这么做！"),
													null);
										}
										
									} else {
										return new Response(
												UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
												clothing.isAbleToBeEquippedDuringSex(slot).getValue(),
												null);
									}
									
								} else {
									return new Response(
											UtilText.parse(inventoryNPC, "装备:"+Util.capitaliseSentence(slot.getName())+"([npc.HerHim])"),
											clothing.getCannotBeEquippedText(inventoryNPC, slot),
											null);
								}
								
							} else {
								return null;
							}
							
						case TRADING:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing) && clothing.getRarity()!=Rarity.QUEST;
							
							if(index == 1) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():clothing.getPrice(inventoryNPC.getSellModifier(clothing));
								if(inventoryFull) {
									return new Response("购买(1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "你的物品栏已经满了！", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
									return new Response("购买(1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "你买不起这个！", null);
								}
								return new Response("购买(1) (" + UtilText.formatAsMoney(sellPrice, "span") + ")", "以" + UtilText.formatAsMoney(sellPrice) + "的价格购买" + clothing.getName() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										sellClothing(inventoryNPC, Main.game.getPlayer(), clothing, 1, sellPrice);
									}
								};
								
							} else if(index == 2) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():clothing.getPrice(inventoryNPC.getSellModifier(clothing));
								if((buyback && Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<5)
										|| (!buyback && inventoryNPC.getClothingCount(clothing) < 5)) {
									return new Response("购买(5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", UtilText.parse(inventoryNPC, "[npc.Name]没有五个"+clothing.getNamePlural()+"。"), null);
								}
								if(inventoryFull) {
									return new Response("购买(5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "你的物品栏已经满了！", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*5) {
									return new Response("购买(5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "你买不起这个！", null);
								}
								return new Response("购买(5) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "以" + UtilText.formatAsMoney(sellPrice*5) + "的价格购买" + clothing.getName() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										sellClothing(inventoryNPC, Main.game.getPlayer(), clothing, 5, sellPrice);
									}
								};
								
							} else if(index == 3) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():clothing.getPrice(inventoryNPC.getSellModifier(clothing));
								int count = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount():inventoryNPC.getClothingCount(clothing);
								if(inventoryFull) {
									return new Response("购买(所有) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "span")+")", "你的物品栏已经满了！", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*count) {
									int affordableCount = (int)(Main.game.getPlayer().getMoney() / sellPrice);
									if(affordableCount > 0) {
										return new Response("购买(最大" + affordableCount + ") (" + UtilText.formatAsMoney(sellPrice * affordableCount, "span") + ")",
												"以" + UtilText.formatAsMoney(sellPrice * affordableCount) + "的价格购买" + clothing.getName() + "。", INVENTORY_MENU) {
											@Override
											public void effects() {
												sellClothing(inventoryNPC, Main.game.getPlayer(), clothing, affordableCount, sellPrice);
											}
										};
									} else {
										return new Response("购买(所有) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "span")+")", "你买不起这个！", null);
									}
								}
								return new Response("购买(所有) (" + UtilText.formatAsMoney(sellPrice*count, "span") + ")",
										"以" + UtilText.formatAsMoney(sellPrice*count) + "的价格购买" + clothing.getName() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										sellClothing(inventoryNPC, Main.game.getPlayer(), clothing, count, sellPrice);
									}
								};
								
							} else if(index == 4) {
								return new Response("染色", UtilText.parse(inventoryNPC, "[npc.Name]不允许你染色[npc.sheIs]准备出售的衣物！"), null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "无法修复他人的避孕套！", null);
									}
									return new Response("破坏", "无法破坏他人的避孕套！", null);
								}
								return new Response("附魔", "无法附魔由其他人拥有的衣物！", null);
								
							} else if(index == 6) {
								return new Response("装备(自己)", UtilText.parse(inventoryNPC, "[npc.Name]不允许你使用[npc.her]的衣物，除非你先买下。"), null);
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "装备([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name]不允许你使用[npc.sheIs]准备出售的衣物！"), null);
								
							} else {
								return null;
							}
					}
				}
			}
			return null;
			
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static final DialogueNode WEAPON_EQUIPPED = new DialogueNode("武器已装备", "", true) {
		
		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "物品栏(快速管理当前<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>开启</b>)";
			} else {
				return "物品栏";
			}
		}
		
		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			List<String> extraDescriptions = weapon.getExtraDescriptions(owner);
			if(!extraDescriptions.isEmpty()) {
				sb.append("<p>");
					for(int i=0 ; i<extraDescriptions.size() ; i++) {
						sb.append(extraDescriptions.get(i));
						if(i<extraDescriptions.size()-1) {
							sb.append("<br/>");
						}
					}
				sb.append("</p>");
			}
			return getItemDisplayPanel(weapon,
					weapon.getSVGEquippedString(owner),
					Util.capitaliseSentence(weapon.getDisplayName(true)),
					weapon.getDescription(owner)
					 	+sb.toString());
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}

			if(Main.game.isBadEnd()) {
				if(index==1) {
					return new Response("不可用", "在坏结局时无法操作物品栏……", null);
				}
				return null;
			}
			
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				switch(interactionType) {
					case COMBAT:
						if (index == 1) {
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								return new Response("丢弃", "在战斗时无法更换武器！", null);
								
							} else {
								return new Response("存储", "在战斗时无法更换武器！", null);
							}
							
						} else if (index==4) {
							return new Response("染色/重铸", "在战斗时无法修改"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"武器的属性！", null);
							
						} else if(index == 5) {
							return new Response("附魔", "你无法附魔已经装备的武器。", null);
							
						} if(index == 6) {
							return new Response("卸下", "在战斗时无法更换武器！", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case FULL_MANAGEMENT: case TRADING: case CHARACTER_CREATION:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("丢弃", "无法丢弃" + weapon.getName() + "！", null);
									
								} else if(areaFull) {
									return new Response("丢弃", "该区域已经满了，所以无法丢弃" + weapon.getName() + "！", null);
								} else {
									return new Response("丢弃", "丢弃" + weapon.getName() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, true, true))
													+ "</p>");
											resetPostAction();
										}
									};
								}
								
							} else {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Store", "You cannot drop the " + weapon.getName() + "!", null);
									
								} else if(areaFull) {
									return new Response("存储", "该区域已满，无法存储" + weapon.getName() + "！", null);
								} else {
									return new Response("存储", "在该区域存储" + weapon.getName() + "。", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, true, true))
													+ "</p>");
											resetPostAction();
										}
									};
								}
							}
							
						} else if (index==4) {
							if (isWeaponDyeReforgeActionAvailable()) {
								return new Response("染色/重铸", 
										Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
											?"你使用精通的[style.colourEarth(土系法术)]修改该武器的属性。"
											:"使用染色刷或重铸锤来修改这件武器的属性。",
										DYE_EQUIPPED_WEAPON) {
									@Override
									public void effects() {
										resetWeaponDyeColours();
									}
								};
							} else {
								return new Response("染色", "你需要找到染色刷或重铸锤才能修改这件武器的属性。", null);
							}
							
						} else if(index == 5) {
							return new Response("附魔", "你无法附魔已经装备的武器。", null);
							
						} else if(index == 6) {
							return new Response("卸下", "卸下" + weapon.getName() + "。", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
												+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, false, true))
											+ "</p>");
									resetPostAction();
								}
							};
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case SEX:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("丢弃", "无法丢弃" + weapon.getName() + "！", null);
									
								} else if(areaFull) {
									return new Response("丢弃", "该区域已经满了，所以无法丢弃" + weapon.getName() + "！", null);
								} else {
									return new Response("丢弃", "丢弃" + weapon.getName() + "。", Main.sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Main.sex.setUnequipWeaponText(weapon,
													"<p style='text-align:center;'>"
														+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, true, true))
													+ "</p>");
											resetPostAction();
											Main.mainController.openInventory();
											Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Main.sex.setSexStarted(true);
										}
									};
								}
								
							} else {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Store", "You cannot drop the " + weapon.getName() + "!", null);
									
								} else if(areaFull) {
									return new Response("存储", "该区域已满，无法存储" + weapon.getName() + "！", null);
								} else {
									return new Response("存储", "在该区域存储" + weapon.getName() + "。", Main.sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Main.sex.setUnequipWeaponText(weapon,
													"<p style='text-align:center;'>"
														+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, true, true))
													+ "</p>");
											resetPostAction();
											Main.mainController.openInventory();
											Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Main.sex.setSexStarted(true);
										}
									};
								}
							}
							
						} else if (index==4) {
							return new Response("染色/重铸", "性交过程中无法修改"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"武器的属性！", null);
							
						} else if(index == 5) {
							return new Response("附魔", "你无法附魔已经装备的武器。", null);
							
						} else if(index == 6) {
							return new Response("卸下", "卸下" + weapon.getName() + "。", Main.sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									Main.sex.setUnequipWeaponText(weapon,
											"<p style='text-align:center;'>"
												+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, false, true))
											+ "</p>");
									resetPostAction();
									Main.mainController.openInventory();
									Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Main.sex.setSexStarted(true);
								}
							};
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				switch(interactionType) {
					case COMBAT:
						if(index == 1) {
							return new Response("丢弃", "在战斗时无法令他人丢弃武器！", null);
							
						} else if(index == 2) {
							return new Response("拿取", "战斗过程中无法拿取他人的武器！", null);
							
						} else if (index==4) {
							return new Response("染色/重铸", "在战斗时无法修改由他人装备的武器的属性！", null);
							
						} else if(index == 5) {
							return new Response("附魔", "无法附魔已经装备的武器，尤其是在战斗过程中！", null);
							
						} else if (index == 6) {
							return new Response("卸下", "战斗过程中无法解除他人的武器！", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case FULL_MANAGEMENT:  case CHARACTER_CREATION:
						boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon) && weapon.getRarity()!=Rarity.QUEST;
						
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("丢弃", UtilText.parse(inventoryNPC, "[npc.name][npc.Name]无法丢弃" + weapon.getName() + "！"), null);
									
								} else if(areaFull) {
									return new Response("丢弃", UtilText.parse(inventoryNPC, "该区域已经满了，[npc.name]无法丢弃" + weapon.getName() + "！"), null);
								} else {
									return new Response("丢弃", UtilText.parse(inventoryNPC, "让[npc.name]丢弃" + weapon.getName() + "。"), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (inventoryNPC.unequipWeapon(weaponSlot, weapon, true, false))
													+ "</p>");
											resetPostAction();
										}
									};
								}
								
							} else {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("丢弃", UtilText.parse(inventoryNPC, "[npc.name][npc.Name]无法丢弃" + weapon.getName() + "！"), null);
									
								} else if(areaFull) {
									return new Response("存储", UtilText.parse(inventoryNPC, "该区域已满，[npc.name]无法存储" + weapon.getName() + "！"), null);
								} else {
									return new Response("存储", UtilText.parse(inventoryNPC, "让[npc.name]在此处存储" + weapon.getName() + "。"), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (inventoryNPC.unequipWeapon(weaponSlot, weapon, true, false))
													+ "</p>");
											resetPostAction();
										}
									};
								}
							}
							
						} else if (index == 2) {
							if(inventoryFull) {
								return new Response("拿取", "你的物品栏满了，无法拿取这个！", null);
								
							} else {
								return new Response("拿取",
										UtilText.parse(inventoryNPC, "拿取[npc.namePos]的" + weapon.getName() + "并加入你的物品栏中。"),
										INVENTORY_MENU){
									@Override
									public void effects(){
										inventoryNPC.unequipWeaponIntoVoid(weaponSlot, weapon, true);
										Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ (Main.game.getPlayer().addWeapon(weapon, false))
												+ "</p>");
										resetPostAction();
									}
								};
							}
							
						} else if (index==4) {
							if (isWeaponDyeReforgeActionAvailable()) {
								return new Response("染色/重铸", 
										Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
											?"你使用精通的[style.colourEarth(土系法术)]修改该武器的属性。"
											:"使用染色刷或重铸锤来修改这件武器的属性。",
										DYE_EQUIPPED_WEAPON) {
									@Override
									public void effects() {
										resetWeaponDyeColours();
									}
								};
							} else {
								return new Response("染色", UtilText.parse(inventoryNPC, "你需要找到染色刷或重铸锤才能修改[npc.namePos]的武器的属性。"), null);
							}
							
						} else if(index == 5) {
							return new Response("附魔", "你无法附魔已经装备的武器。", null);
							
						} else if(index == 6) {
							if(!weapon.getWeaponType().isAbleToBeDropped()) {
								return new Response("卸下", UtilText.parse(inventoryNPC, "由于这是一把特殊武器，" + weapon.getName() + "无法被卸下至[npc.namePos]的物品栏中。"), null);
							}
							return new Response("卸下", UtilText.parse(inventoryNPC, "令[npc.name]卸下[npc.her]的" + weapon.getName() + "。"), INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
												+ (inventoryNPC.unequipWeapon(weaponSlot, weapon, false, false))
											+ "</p>");
									resetPostAction();
								}
							};
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case SEX:
						if(index == 1) {
							return new Response("丢弃", "性交过程中无法令他人解除武器！", null);
							
						} else if (index == 2) {
							return new Response("拿取", "性交过程中无法拿取他人的武器！", null);
							
						} else if (index==4) {
							return new Response("染色/重铸", UtilText.parse(inventoryNPC, "性交过程中无法修改[npc.namePos]的武器的属性！"), null);
						
						} else if(index == 5) {
							return new Response("附魔", "你无法附魔已经装备的武器。", null);
							
						} else if(index == 6) {
							return new Response("卸下", "性交过程中无法解除他人的武器！", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case TRADING:
						if(index == 1) {
							return new Response("丢弃", "无法令他人丢弃武器！", null);
							
						} else if (index == 2) {
							return new Response("拿取", "无法拿取他人的武器！", null);
							
						} else if (index==4) {
							return new Response("染色/重铸", UtilText.parse(inventoryNPC, "无法修改[npc.namePos]的武器的属性！"), null);
							
						} else if(index == 5) {
							return new Response("附魔", "无法附魔由其他人装备的武器！", null);
							
						} else if(index == 6) {
							return new Response("卸下", "无法解除他人的武器！", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
					}
				
				}
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	public static final DialogueNode CLOTHING_EQUIPPED = new DialogueNode("衣物已装备", "", true) {

		@Override
		public String getLabel() {
			if(!Main.game.isInNewWorld()) {
				return "晚会着装";
			}
			
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "物品栏(快速管理当前<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>开启</b>)";
			} else {
				return "物品栏";
			}
		}
		
		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(clothing.getDescription(owner));
			sb.append("<p>");
				GameCharacter descriptionTarget = owner; //Main.game.isInSex()?owner:Main.game.getPlayer()
				for(String s : clothing.getExtraDescriptions(descriptionTarget, null, true)) {
					sb.append(s+"<br/>");
				}
				for(String s : clothing.getExtraDescriptions(descriptionTarget, clothing.getSlotEquippedTo(), true)) {
					sb.append(s+"<br/>");
				}
			sb.append("</p>");
			sb.append(Main.game.isInSex()||Main.game.isInCombat()?clothing.getDisplacementBlockingDescriptions(owner):"");
			
			return getItemDisplayPanel(clothing,
						clothing.getSVGEquippedString(owner),
						clothing.getDisplayName(true),
						sb.toString())
					+(interactionType==InventoryInteraction.CHARACTER_CREATION
						?CharacterCreation.getCheckingClothingDescription()
						:"");
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}

			if(Main.game.isBadEnd()) {
				if(index==1) {
					return new Response("不可用", "在坏结局时无法操作物品栏……", null);
				}
				return null;
			}
			
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				InventorySlot slotEquippedTo = clothing.getSlotEquippedTo();
				switch(interactionType) {
					case COMBAT:
						if (index == 1) {
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								return new Response("丢弃", "无法在战斗中丢弃" + clothing.getName() + "！", null);
								
							} else {
								return new Response("Store", "You cannot drop the " + clothing.getName() + " while fighting someone!", null);
							}
							
						} else if (index==4) {
							return new Response("染色", "在战斗时不能染色"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"衣物！", null);
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								return getJinxRemovalResponse(true);
									
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "无法修复已经装备的避孕套！", null);
									}
									return new Response("破坏", "无法破坏已经装备的避孕套！", null);
								}
								return new Response("附魔", "你无法附魔已经装备的衣物。", null);
							}
							
						} else if(index == 6 && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
							return new Response("卸下", "战斗中无法解除" + clothing.getName() + "！", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).size()){
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										"无法"+clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()
										+ clothing.getName() + "！", null);
								
							
						} else {
							
							return null;
						}
						
					case FULL_MANAGEMENT: case TRADING:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("丢弃", "无法丢弃" + clothing.getName() + "！", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("丢弃", "该区域已经满了，所以无法丢弃"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"" + clothing.getName() + "！", null);
								} else {
									return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"移除":"丢弃"),
											(clothing.isDiscardedOnUnequip(slotEquippedTo)
													?"拿走"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"" + clothing.getName() + "并丢到一旁。"
													:"丢弃"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"" + clothing.getName() + "。"),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								}
								
							} else {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Store", "You cannot drop the " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("存储", "该区域已满，无法存储"+(owner.isPlayer()?"你的":owner.getName("")+"")+"" + clothing.getName() + "！", null);
								} else {
									return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"移除":"存储"),
											(clothing.isDiscardedOnUnequip(slotEquippedTo)
													?"拿走"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"" + clothing.getName() + "并丢到一旁。"
													:"存储"+(owner.isPlayer()?"你的":owner.getName("")+"")+"" + clothing.getName() + "。"),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								}
							}
							
						} else if (index==4) {
							if (isClothingDyeActionAvailable()) {
								return new Response("染色", 
										Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
											?"你使用精通的[style.colourEarth(土系法术)]为该物品染色。"
											:"使用染色刷来染色衣物。",
										DYE_EQUIPPED_CLOTHING) {
									@Override
									public void effects() {
										resetClothingDyeColours();
									}
								};
							} else {
								return new Response("染色", "你需要找到染色刷才能染色衣物。", null);
							}
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								return getJinxRemovalResponse(true);
								
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "无法修复已经装备的避孕套！", null);
									}
									return new Response("破坏", "无法破坏已经装备的避孕套！", null);
								}
								return new Response("附魔", "你无法附魔已经装备的衣物。", null);
							}
							
						} else if(index == 6 && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
							if (owner.isAbleToUnequip(clothing, true, Main.game.getPlayer())) {
								return new Response("卸下", "卸下" + clothing.getName() + "。", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToInventory(Main.game.getPlayer(), clothing) + "</p>");
									}
								};
								
							} else {
								return new Response("卸下", getClothingBlockingRemovalText(owner, "卸下"), null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).size()){
							
							if (clothing.getDisplacedList().contains(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getOppositeDescription()),
										Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getOppositeDescription()) + "" + clothing.getName() + "。"
												+ clothing.getClothingBlockingDescription(
														clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11),
														Main.game.getPlayer(),
														clothing.getSlotEquippedTo(),
														"<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>这将会覆盖"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"",
														"。</span>"),
												CLOTHING_EQUIPPED){
									@Override
									public void effects(){
										Main.game.getPlayer().isAbleToBeReplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
									}
								};
							} else {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
										Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()) + "" + clothing.getName() + "。"
												+ clothing.getClothingBlockingDescription(
														clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11),
														Main.game.getPlayer(),
														clothing.getSlotEquippedTo(),
														"<span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>这将会暴露"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"",
														"。</span>"),
												CLOTHING_EQUIPPED){
									@Override
									public void effects(){
										Main.game.getPlayer().isAbleToBeDisplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
									}
								};
							}
							
						} else {
							return null;
						}
						
					case CHARACTER_CREATION:
						if (index == 1) {
							if(Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.NIPPLES)
									|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.ANUS)
									|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.PENIS)
									|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.VAGINA)
									|| (Main.game.getPlayer().getClothingInSlot(InventorySlot.FOOT)==null && Main.game.getPlayer().getLegType().equals(LegType.HUMAN))) {
								return new Response("前往舞台", "你需要先穿好能够遮盖身体的衣物，还需要一双鞋。", null);
								
							} else {
								return new Response("前往舞台", "你已经准备好前往舞台了。", CharacterCreation.CHOOSE_BACKGROUND) {
									@Override
									public void effects() {
										CharacterCreation.moveNPCIntoPlayerTile();
									}
								};
							}
							
						} else if(index == 4){
							if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()){
								return new Response("脱下所有衣物", "你现在已经光着了，没有衣物可以脱。", null);
							}
							else{
								return new Response("脱下所有衣物", "尽可能地脱下衣物。", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append(unequipAll(Main.game.getPlayer()));
									}
								};
							}
							
						} else if(index == 5) {
							return new Response("更改颜色", "更改这件衣物的颜色。", DYE_EQUIPPED_CLOTHING_CHARACTER_CREATION) {
								@Override
								public void effects() {
									resetClothingDyeColours();
								}
							};
							
						} else if(index == 6) {
							return new Response("卸下", "卸下" + clothing.getName() + "。", INVENTORY_MENU){
								@Override
								public void effects(){
									unequipClothingToFloor(Main.game.getPlayer(), clothing);
								}
							};
								
						} else {
							return null;
						}
						
					case SEX:
						if (index == 1) {
							String unequipTitle = "放下";
							if(clothing.isDiscardedOnUnequip(slotEquippedTo)) {
								unequipTitle = "丢弃";
							} else if(!Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								unequipTitle = "存储";
							}
							if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
								return new Response(unequipTitle, "你当前处于[style.colourTerrible(无法行动)]状态，无法解除" + clothing.getName() + "！", null);
							}
							if(!Main.sex.isCanRemoveSelfClothing(Main.game.getPlayer())) {
								return new Response(unequipTitle, "该性交场景中无法解除" + clothing.getName() + "！", null);
							}
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("丢弃", "无法丢弃" + clothing.getName() + "！", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("丢弃", "该区域已经满了，所以无法丢弃"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"" + clothing.getName() + "！", null);
									
								} else {
									if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
										return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"移除":"丢弃"),
												(clothing.isDiscardedOnUnequip(slotEquippedTo)
														?"拿走"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"" + clothing.getName() + "并丢到一旁。"
														:"丢弃"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"" + clothing.getName() + "。"),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												GameCharacter unequipOwner = owner;
												AbstractClothing c = clothing;
												unequipClothingToFloor(Main.game.getPlayer(), clothing);
												Main.sex.setUnequipClothingText(c, unequipOwner.getUnequipDescription());
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Main.sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("丢弃", getClothingBlockingRemovalText(owner, "unequip"), null);
									}
								}
								
							} else {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Store", "You cannot drop the " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("存储", "存储"+(owner.isPlayer()?"你的":owner.getName("")+"")+"" + clothing.getName() + "！", null);
									
								} else {
									if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
										return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"移除":"存储"),
												(clothing.isDiscardedOnUnequip(slotEquippedTo)
														?"拿走"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"" + clothing.getName() + "并丢到一旁。"
														:"丢弃"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"" + clothing.getName() + "。"),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												GameCharacter unequipOwner = owner;
												AbstractClothing c = clothing;
												unequipClothingToFloor(Main.game.getPlayer(), clothing);
												Main.sex.setUnequipClothingText(c, unequipOwner.getUnequipDescription());
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Main.sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("存储", getClothingBlockingRemovalText(owner, "卸下"), null);
									}
								}
							}
							
						} else if (index==4) {
							return new Response("染色", "性交过程中无法染色"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"衣物！", null);
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								return getJinxRemovalResponse(true);
								
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "无法修复已经装备的避孕套！", null);
									}
									return new Response("破坏", "无法破坏已经装备的避孕套！", null);
								}
								return new Response("附魔", "你无法附魔已经装备的衣物。", null);
							}
							
						} else if(index == 6 && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
							if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
								return new Response("卸下", "你当前处于[style.colourTerrible(无法行动)]状态，无法解除" + clothing.getName() + "！", null);
							}
							if(!Main.sex.isCanRemoveSelfClothing(Main.game.getPlayer())) {
								return new Response("卸下", "该性交场景中无法解除" + clothing.getName() + "！", null);
							}
							if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
								return new Response("卸下", "卸下" + clothing.getName() + "。", Main.sex.SEX_DIALOGUE){
									@Override
									public void effects(){
										AbstractClothing c = clothing;
										unequipClothingToInventory(Main.game.getPlayer(), clothing);
										Main.sex.setUnequipClothingText(c, owner.getUnequipDescription());
										Main.mainController.openInventory();
										Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
										Main.sex.setSexStarted(true);
									}
								};
							} else {
								return new Response("卸下", getClothingBlockingRemovalText(owner, "卸下"), null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).size()) {
							if (clothing.getDisplacedList().contains(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										""+ clothing.getName()+ ""
										+(clothing.getClothingType().isPlural()?"":"")+"已经被"
												+ clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescriptionPast() + "了！", null);
								
							} else {
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(
											Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
											"在战斗时无法"+clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()
												+ clothing.getName() + "，你当前处于[style.colourTerrible(无法行动)]状态！",
											null);
								}
								if(!Main.sex.isCanRemoveSelfClothing(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
											"无法"+clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()
											+ ""+(owner.isPlayer()?"你的":owner.getName("")+"的")+"" + clothing.getName() + "！", null);
								}
								
								if(owner.isAbleToBeDisplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11), false, false, Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
											Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()) + "" + clothing.getName() + "。"
													+ clothing.getClothingBlockingDescription(
															clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11),
															Main.game.getPlayer(),
															clothing.getSlotEquippedTo(),
															"<span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>这将会暴露"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"",
															".</span>"),
													Main.sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Main.game.getPlayer().isAbleToBeDisplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
											Main.sex.setDisplaceClothingText(clothing, owner.getDisplaceDescription());
											Main.mainController.openInventory();
											Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Main.sex.setSexStarted(true);
										}
									};
								
								} else {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
											"无法"+clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()
											+ "" + clothing.getName() + "，原因是"+UtilText.parse(owner, "[npc.namePos]的")
											+owner.getBlockingClothing().getName()+""+(owner.getBlockingClothing().getClothingType().isPlural()?"":"")+"阻挡在中间！", null);
								}
							}
							
						} else {
							return null;
						}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				InventorySlot slotEquippedTo = clothing.getSlotEquippedTo();
				switch(interactionType) {
					case COMBAT:
						if (index == 1) {
							return new Response("丢弃", "在战斗时无法令他人丢弃衣物！", null);
							
						} else if (index==4) {
							return new Response("染色", "在战斗时无法染色由他人装备的武器！", null);
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								return getJinxRemovalResponse(false);
								
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "无法修复已经装备的避孕套！", null);
									}
									return new Response("破坏", "无法破坏已经装备的避孕套！", null);
								}
								return new Response("附魔", "你无法附魔已经装备的衣物。", null);
							}
							
						} else if(index == 6) {
							return new Response("卸下", "在战斗时无法解除他人的衣物！", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).size()){
							if (clothing.getDisplacedList().contains(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										""+ clothing.getName()+ ""
										+(clothing.getClothingType().isPlural()?"":"")+"已经被"
												+ clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescriptionPast() + "了！", null);
								
							} else {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										"在战斗时无法"+clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + "" + clothing.getName() + "！", null);
							}
							
						} else {
							return null;
						}
						
					case FULL_MANAGEMENT: case CHARACTER_CREATION:
						boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing) && clothing.getRarity()!=Rarity.QUEST;
						
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("丢弃", "无法丢弃" + clothing.getName() + "！", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("丢弃", UtilText.parse(inventoryNPC, "该区域已经满了，所以无法丢弃[npc.namePos] " + clothing.getName() + "！"), null);
								} else {
									return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"移除":"丢弃"),
											(clothing.isDiscardedOnUnequip(slotEquippedTo)
													?UtilText.parse(inventoryNPC, "脱下[npc.namePos]的" + clothing.getName() + "并丢到一边。")
													:UtilText.parse(inventoryNPC, "丢弃[npc.namePos]的" + clothing.getName() + "。")),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								}
								
							} else {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Store", "You cannot drop the " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("存储", UtilText.parse(inventoryNPC, "存储[npc.namePos]的" + clothing.getName() + "！"), null);
								} else {
									return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"移除":"存储"),
											(clothing.isDiscardedOnUnequip(slotEquippedTo)
													?UtilText.parse(inventoryNPC, "脱下[npc.namePos]的" + clothing.getName() + "并丢到一边。")
													:UtilText.parse(inventoryNPC, "存储[npc.namePos]的" + clothing.getName() + "。")),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								}
							}
							
						} else if(index==2) {
							if(inventoryFull) {
								return new Response("拿取", "你的物品栏满了，无法拿取这个！", null);
								
							} else if(clothing.isDiscardedOnUnequip(slotEquippedTo)) {
								return new Response("拿取", "这件衣物在卸下时会被自动移除，所以无法拿取！", null);
								
							} else {
								return new Response("拿取",
										UtilText.parse(inventoryNPC, "拿取[npc.namePos]的" + clothing.getName() + "并加入你的物品栏中。"),
										INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ unequipClothingToUnequippersInventory(Main.game.getPlayer(), clothing)
												+ "</p>");
									}
								};
							}
							
						} else if (index==4) {
							if (isClothingDyeActionAvailable()) {
								return new Response("染色", 
										Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
											?"你使用精通的[style.colourEarth(土系法术)]为该物品染色。"
											:"使用染色刷来染色衣物。",
										DYE_EQUIPPED_CLOTHING) {
									@Override
									public void effects() {
										resetClothingDyeColours();
									}
								};
								
							} else {
								return new Response("染色", UtilText.parse(inventoryNPC, "需要找到另一个染色刷才能染色[npc.namePos]的衣物。"), null);
							}
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								return getJinxRemovalResponse(false);
								
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "无法修复已经装备的避孕套！", null);
									}
									return new Response("破坏", "无法破坏已经装备的避孕套！", null);
								}
								return new Response("附魔", "你无法附魔已经装备的衣物。", null);
							}
							
						} else if(index == 6 && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
							if(!clothing.getClothingType().isAbleToBeDropped()) {
								return new Response("卸下", UtilText.parse(inventoryNPC, "由于这是一件特殊衣物，" + clothing.getName() + "无法被卸下至[npc.namePos]的物品栏中。"), null);
							}
							return new Response("卸下", "卸下" + clothing.getName() + "。", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToInventory(Main.game.getPlayer(), clothing) + "</p>");
								}
							};
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).size()){
							
							if (clothing.getDisplacedList().contains(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getOppositeDescription()),
										Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getOppositeDescription()) + "" + clothing.getName() + "。"
												+ clothing.getClothingBlockingDescription(
														clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11),
														owner,
														clothing.getSlotEquippedTo(),
														"<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>这将会覆盖"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"",
														".</span>"),
												CLOTHING_EQUIPPED){
									@Override
									public void effects(){
										owner.isAbleToBeReplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
									}
								};
							} else {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
										Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()) + "" + clothing.getName() + "。"
												+ clothing.getClothingBlockingDescription(
														clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11),
														owner,
														clothing.getSlotEquippedTo(),
														"<span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>这将会暴露"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"",
														".</span>"),
												CLOTHING_EQUIPPED){
									@Override
									public void effects(){
										owner.isAbleToBeDisplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + owner.getDisplaceDescription() + "</p>");
									}
								};
							}
							
						} else {
							return null;
						}
						
					case SEX:
						if (index == 1) {
							String unequipTitle = "放下";
							if(clothing.isDiscardedOnUnequip(slotEquippedTo)) {
								unequipTitle = "丢弃";
							} else if(!Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								unequipTitle = "存储";
							}
							if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
								return new Response(unequipTitle,
									UtilText.parse(inventoryNPC, "无法脱下[npc.namePos]的衣物，因为你当前[style.colourTerrible(无法行动)]！"),
									null);
							}
							if(Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
								return new Response(unequipTitle,
										UtilText.parse(inventoryNPC, "你无法在躲藏时脱下[npc.namePos]的衣物！"),
										null);
								
							} else if(clothing.isDiscardedOnUnequip(slotEquippedTo) && !Main.sex.isCanRemoveOthersClothing(Main.game.getPlayer(), clothing)) {
								return new Response("移除", "该性交场景中无法解除" + clothing.getName() + "！", null);
							}

							if(!Main.sex.isCanRemoveOthersClothing(Main.game.getPlayer(), clothing)) {
								return new Response(unequipTitle, "该性交场景中无法解除" + clothing.getName() + "！", null);
							}
							
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("丢弃", "无法丢弃" + clothing.getName() + "！", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("丢弃", UtilText.parse(inventoryNPC, "该区域已经满了，所以无法丢弃[npc.namePos] " + clothing.getName() + "！"), null);
									
								} else {
									if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
										return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"移除":"丢弃"),
											(clothing.isDiscardedOnUnequip(slotEquippedTo)
													?UtilText.parse(inventoryNPC, "脱下[npc.namePos]的" + clothing.getName() + "并丢到一边。")
													:UtilText.parse(inventoryNPC, "丢弃[npc.namePos]的" + clothing.getName() + "。")),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												AbstractClothing c = clothing;
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
												Main.sex.setUnequipClothingText(c, inventoryNPC.getUnequipDescription());
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Main.sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("丢弃", getClothingBlockingRemovalText(owner, "unequip"), null);
									}
								}
								
							} else {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Store", "You cannot drop the " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("存储", UtilText.parse(inventoryNPC, "存储[npc.namePos]的" + clothing.getName() + "！"), null);
								} else {
									if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
										return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"移除":"存储"),
												(clothing.isDiscardedOnUnequip(slotEquippedTo)
														?UtilText.parse(inventoryNPC, "脱下[npc.namePos]的" + clothing.getName() + "并丢到一边。")
														:UtilText.parse(inventoryNPC, "存储[npc.namePos]的" + clothing.getName() + "。")),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												AbstractClothing c = clothing;
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
												Main.sex.setUnequipClothingText(c, inventoryNPC.getUnequipDescription());
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Main.sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("存储", getClothingBlockingRemovalText(owner, "卸下"), null);
									}
								}
							}
							
						} else if (index==4) {
							return new Response("染色", UtilText.parse(inventoryNPC, "性交过程中无法染色[npc.namePos]的衣物！"), null);
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								return getJinxRemovalResponse(false);
								
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "无法修复已经装备的避孕套！", null);
									}
									return new Response("破坏", "无法破坏已经装备的避孕套！", null);
								}
								return new Response("附魔", "你无法附魔已经装备的衣物。", null);
							}
							
						} else if(index == 6 && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
							if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
								return new Response("卸下",
									UtilText.parse(inventoryNPC, "无法脱下[npc.namePos]的衣物，因为你当前[style.colourTerrible(无法行动)]！"),
									null);
							}
							if(Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
								return new Response("脱下", UtilText.parse(inventoryNPC, "你无法在躲藏时脱下[npc.namePos]的衣服！"), null);
								
							} else if(!Main.sex.isCanRemoveOthersClothing(Main.game.getPlayer(), clothing)) {
								return new Response("卸下", "该性交场景中无法解除" + clothing.getName() + "！", null);
							}
							
							if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
								return new Response("卸下", "卸下" + clothing.getName() + "。", Main.sex.SEX_DIALOGUE){
									@Override
									public void effects(){
										AbstractClothing c = clothing;
										unequipClothingToInventory(Main.game.getPlayer(), clothing);
										Main.sex.setUnequipClothingText(c, inventoryNPC.getUnequipDescription());
										Main.mainController.openInventory();
										Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
										Main.sex.setSexStarted(true);
									}
								};
							} else {
								return new Response("卸下", getClothingBlockingRemovalText(owner, "卸下"), null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).size()){
							if (clothing.getDisplacedList().contains(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										""+ clothing.getName()+ ""
										+(clothing.getClothingType().isPlural()?"":"")+"已经被"
												+ clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescriptionPast() + "了！", null);
								
							} else {
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
										UtilText.parse(inventoryNPC, "你无法"+clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()
												+ "[npc.namePos]的衣物，因为你目前[style.colourTerrible(无法行动)]！"),
										null);
								}
								if(Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
											UtilText.parse(inventoryNPC,
													"你无法在躲藏时"+clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + "[npc.namePos]的" + clothing.getName() + "！"),
											null);
								}
								if(owner.isAbleToBeDisplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11), false, false, Main.game.getPlayer())){
									if(!Main.sex.isCanRemoveOthersClothing(Main.game.getPlayer(), clothing)) {
										return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
												"该性交场景中无法"+clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + "" + clothing.getName() + "！", null);
									}
									
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
											Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()) + "" + clothing.getName() + "。"
													+ clothing.getClothingBlockingDescription(
															clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11),
															owner,
															clothing.getSlotEquippedTo(),
															"<span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>这将会暴露"+(owner.isPlayer()?"你的":owner.getName("")+"的")+"",
															".</span>"),
													Main.sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											owner.isAbleToBeDisplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
											Main.sex.setDisplaceClothingText(clothing, owner.getDisplaceDescription());
											Main.mainController.openInventory();
											Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Main.sex.setSexStarted(true);
										}
									};
								
								} else {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
											"无法"+clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()
											+ "" + clothing.getName() + "，原因是"+UtilText.parse(owner, "[npc.namePos]的")
											+owner.getBlockingClothing().getName()+""+(owner.getBlockingClothing().getClothingType().isPlural()?"":"")+"阻挡在中间！", null);
								}
							}
							
						} else {
							return null;
						}
						
						case TRADING:
							if (index == 1) {
								return new Response("丢弃", UtilText.parse(inventoryNPC, "无法令[npc.name]丢弃[npc.her]的衣物！"), null);
								
							} else if (index==4) {
								return new Response("染色", UtilText.parse(inventoryNPC, "无法染色[npc.namePos]的衣物！"), null);
								
							}  else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("修复(<i>1精华</i>)", "无法修复[npc.namePos]的避孕套！", null);
									}
									return new Response("破坏", "无法破坏[npc.namePos]的避孕套！", null);
								}
								return new Response("附魔", UtilText.parse(inventoryNPC, "你无法附魔[npc.namePos]的衣物！"), null);
								
							} else if(index == 6) {
								return new Response("卸下", UtilText.parse(inventoryNPC, "无法解除[npc.namePos]的衣物！"), null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).size()){
								if (clothing.getDisplacedList().contains(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11))) {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
											""+ clothing.getName()+ ""
													+(clothing.getClothingType().isPlural()?"":"")+"已经被"
													+ clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescriptionPast() + "了！", null);
									
								} else {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
											"无法"+clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + "" + clothing.getName() + "！", null);
								}
								
							} else {
								return null;
							}
					}
				
				}
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	private static String getClothingDyeUI() {
		InventorySlot slotEquippedTo = clothing.getSlotEquippedTo();
		if(slotEquippedTo==null) {
			slotEquippedTo = clothing.getClothingType().getEquipSlots().get(0);
		}
		
		inventorySB = new StringBuilder(
				"<div class='container-full-width'>"
					+ "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ clothing.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<h3 style='text-align:center;'><b>"+clothing.getDisplayName(true)+"</b></h3>"
					+ "<p>"
						+ "从下方染色的按钮中选择你想要的颜色，并使用预览查看染色后的效果，点击屏幕底端的“染色”即可应用修改。"
					+ "</p>"
				+ "</div>"
					
				+ "<br/>"
				
				+ "<div class='container-full-width'>"
					+ "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ clothing.getClothingType().getSVGImage(slotEquippedTo,
									dyePreviews,
									dyePreviewPattern,
									dyePreviewPatternColours,
									getDyePreviewStickersAsStrings())
						+ "</div>"
					+ "</div>");
		
		inventorySB.append("<h3 style='text-align:center;'><b>染色并预览</b></h3>");
		
		if(!clothing.getClothingType().getStickers().isEmpty()) {
			StringBuilder stickerSB = new StringBuilder();
			boolean stickerFound = false;
			List<StickerCategory> orderedCategories = new ArrayList<>(clothing.getClothingType().getStickers().keySet());
			Collections.sort(orderedCategories, (s1, s2)->s1.getPriority()-s2.getPriority());
			
			for(StickerCategory cat : orderedCategories) {
				stickerSB.append("<div class='container-quarter-width' style='width:calc(75% - 16px); margin:0 8px; padding:0;'>");
					stickerSB.append("<div class='container-quarter-width' style='margin:0; padding-top:6px; width:20%;'>");
						stickerSB.append(Util.capitaliseSentence(cat.getName())+":"); // Category name
					stickerSB.append("</div>");
					
					stickerSB.append("<div class='container-quarter-width' style='margin:0; padding:0; width:80%;'>");
						List<Sticker> orderedStickers = new ArrayList<>(clothing.getClothingType().getStickers().get(cat));
						Collections.sort(orderedStickers, (s1, s2)->s1.getPriority()-s2.getPriority());
						for(Sticker sticker : orderedStickers) {
							String requirements = UtilText.parse(sticker.getUnavailabilityText()).trim();
							if(requirements.isEmpty() || sticker.isShowDisabledButton()) {
								boolean specialSticker = !sticker.getAvailabilityText().isEmpty() || !sticker.getTagsApplied().isEmpty() || !sticker.getTagsRemoved().isEmpty();
								stickerFound = true;
								String id = "ITEM_STICKER_"+cat.getId()+sticker.getId();
								if(!requirements.isEmpty()) {
									stickerSB.append(
											"<div id='"+id+"' class='cosmetics-button disabled'>"
													+ "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>" + Util.capitaliseSentence(sticker.getName()) + (specialSticker?"*":"") + "</b>"
											+ "</div>");
									
								} else if(dyePreviewStickers.get(cat)==sticker) {
									stickerSB.append(
											"<div id='"+id+"' class='cosmetics-button active'>"
													+ "<b style='color:" + sticker.getColourSelected().toWebHexString() + ";'>" + Util.capitaliseSentence(sticker.getName()) + (specialSticker?"*":"") + "</b>"
											+ "</div>");
								} else {
									stickerSB.append(
											"<div id='"+id+"' class='cosmetics-button'>"
													+ "<span style='color:"+sticker.getColourDisabled().toWebHexString()+";'>" + Util.capitaliseSentence(sticker.getName()) + (specialSticker?"*":"") + "</span>"
											+ "</div>");
								}
							}
						}
					stickerSB.append("</div>");
				stickerSB.append("</div>");
				
				if(stickerFound) {
					stickerFound = false;
					inventorySB.append(stickerSB.toString());
					stickerSB = new StringBuilder();
				}
			}
		}
		
		for(int i=0; i<clothing.getClothingType().getColourReplacements().size(); i++) {
			ColourReplacement cr = clothing.getClothingType().getColourReplacement(i);
			if(!cr.getAllColours().isEmpty()) {
				inventorySB.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
							+ Util.capitaliseSentence(Util.intToPrimarySequence(i+1))+"颜色"+(cr.isRecolouringAllowed()?"":" ([style.italicsBad(无法更改)])")+":<br/>");
				
				for(Colour c : cr.getAllColours()) {
//					if(!c.isDesaturated()) {
						inventorySB.append("<div class='normal-button"+(dyePreviews.size()>i && dyePreviews.get(i)==c?" selected":"")+"' id='DYE_CLOTHING_"+i+"_"+c.getId()+"'"
											+ " style='width:auto; margin-right:4px; border-width:1px;"
												+(cr.getDefaultColours().contains(c)
													?"border-color:"+PresetColour.TEXT_GREY.toWebHexString()+";"
													:"")
												+(dyePreviews.size()>i && dyePreviews.get(i)==c
													?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";"
													:"")+"'>"
										+ "<div class='phone-item-colour' style='"
											+ (c.isMetallic()
													?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
													:"background-color:" + c.toWebHexString() + ";")
											+ "'></div>"
							+ "</div>");
//					}
				}
				inventorySB.append("</div>");
			}
		}
		
		if(clothing.getClothingType().isPatternAvailable()){
			inventorySB.append(
					"<br/>"
					+ "<div class='container-full-width'>"
					+ "花纹:<br/>");
	 
			for (Pattern pattern : Pattern.getAllPatterns()) {
				if (dyePreviewPattern.equals(pattern.getId())) {
					inventorySB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(pattern.getNiceName()) + "</b>"
							+ "</div>");
				} else {
					inventorySB.append(
							"<div id='ITEM_PATTERN_"+pattern.getId()+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(pattern.getNiceName()) + "</span>"
							+ "</div>");
				}
			}
			inventorySB.append("</div>");

			for(int i=0; i<clothing.getClothingType().getPatternColourReplacements().size(); i++) {
				ColourReplacement cr = clothing.getClothingType().getPatternColourReplacement(i);
				if(!cr.getAllColours().isEmpty() && Pattern.getPattern(dyePreviewPattern).isRecolourAvailable(cr)) {
					inventorySB.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
								+ "Pattern "+Util.capitaliseSentence(Util.intToPrimarySequence(i+1))+" 颜色:<br/>");
					
					for (Colour c : cr.getAllColours()) {
						inventorySB.append("<div class='normal-button"+(dyePreviewPatternColours.size()>i && dyePreviewPatternColours.get(i)==c?" selected":"")+"' id='DYE_CLOTHING_PATTERN_"+i+"_"+c.getId()+"'"
											+ " style='width:auto; margin-right:4px;"+(dyePreviewPatternColours.size()>i && dyePreviewPatternColours.get(i)==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'>"
										+ "<div class='phone-item-colour' style='"
											+ (c.isMetallic()
													?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
													:"background-color:" + c.toWebHexString() + ";")
											+ "'></div>"
							+ "</div>");
					}
					inventorySB.append("</div>");
				}
			}
		}
		inventorySB.append("</div>");
		
		return inventorySB.toString();
	}
	
	private static String getWeaponDyeUI() {
		inventorySB = new StringBuilder(
				"<div class='container-full-width'>"
					+ "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ weapon.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<h3 style='text-align:center;'><b>"+weapon.getDisplayName(true)+"</b></h3>"
					+ "<p>"
						+ "从下方染色的按钮中选择你想要的颜色，并使用预览查看染色后的效果，点击屏幕底端的“染色”即可应用修改。"
					+ "</p>"
				+ "</div>"
				+ "<br/>"
				+ "<div class='container-full-width'>"
					+ "<div class='container-full-width' style='text-align:center; width:calc(25% - 16px); float:right;'>"
						+ "<div class='inventoryImage' style='width:100%;'>"
							+ (weapon.getWeaponType().isEquippedSVGImageDifferent()
								?"已卸下"
								:"")
							+ "<div class='inventoryImage-content'>"
								+ weapon.getWeaponType().getSVGImage(damageTypePreview, dyePreviews)
							+ "</div>"
						+ "</div>"
						+(weapon.getWeaponType().isEquippedSVGImageDifferent()
							?"<div class='inventoryImage' style='width:100%;'>"
								+ "已装备"
									+ "<div class='inventoryImage-content'>"
										+ weapon.getWeaponType().getSVGEquippedImage(Main.game.getPlayer(), damageTypePreview, dyePreviews)
									+ "</div>"
								+ "</div>"
							:"")
					+ "</div>"
					+ "<h3 style='text-align:center;'><b>染色并预览</b></h3>");
		
		
		inventorySB.append("<div class='container-quarter-width' style='text-align:center;width:calc(75% - 16px);'>"
				+ "<b>伤害类型:</b><br/>");
		for(DamageType dt : weapon.getWeaponType().getAvailableDamageTypes()) {
			inventorySB.append("<div class='normal-button"+(damageTypePreview==dt?" selected":"")+"' id='DAMAGE_TYPE_"+dt.toString()+"'"
							+ "style='width:20%; margin:0 2.5%; color:"+(damageTypePreview==dt?dt.getColour().toWebHexString():dt.getColour().getShades(8)[0])+";'>"
						+ Util.capitaliseSentence(dt.getName())
					+ "</div>");
		}
		inventorySB.append("</div>");

		boolean colourOptions = false;

		for(int i=0; i<weapon.getWeaponType().getColourReplacements(false).size(); i++) {
			colourOptions = true;
			ColourReplacement cr = weapon.getWeaponType().getColourReplacement(false, i);
			if(!cr.getAllColours().isEmpty()) {
				inventorySB.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
						+ Util.capitaliseSentence(Util.intToPrimarySequence(i+1))+"颜色"+(cr.isRecolouringAllowed()?"":" ([style.italicsBad(无法更改)])")+":<br/>");
				
				for(Colour c : cr.getAllColours()) {
					inventorySB.append("<div class='normal-button"+(dyePreviews.size()>i && dyePreviews.get(i)==c?" selected":"")+"' id='DYE_WEAPON_"+i+"_"+c.getId()+"'"
										+ " style='width:auto; margin-right:4px; border-width:1px;"
											+(cr.getDefaultColours().contains(c)
												?"border-color:"+PresetColour.TEXT_GREY.toWebHexString()+";"
												:"")
											+(dyePreviews.size()>i && dyePreviews.get(i)==c
												?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";"
												:"")
										+"'>"
									+ "<div class='phone-item-colour' style='"
										+ (c.isMetallic()
												?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
												:"background-color:" + c.toWebHexString() + ";")
										+ "'></div>"
						+ "</div>");
				}
				inventorySB.append("</div>");
			}
		}
		
		if(!colourOptions) {
			inventorySB.append("<div class='container-half-width' style='text-align:center;'>"
					+ "[style.colourDisabled(染色选项均不可用……)]"
					+ "</div>");
		}

		inventorySB.append("</div>");
		
		return inventorySB.toString();
	}
	
	public static final DialogueNode DYE_CLOTHING = new DialogueNode("染色衣物", "", true) {

		@Override
		public String getContent() {
			return getClothingDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回之前的菜单。", INVENTORY_MENU);

			} else if (index == 1) {
				if(dyePreviews.equals(clothing.getColours())
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternColours.equals(clothing.getPatternColours())
						&& dyePreviewStickers.equals(clothing.getStickersAsObjects())) {
					return new Response("染色",
							"需要选择与之前不同的颜色才能染色" + clothing.getName() + "！",
							null); 
				}
				
				return new Response("染色",
						"使用选中的颜色染色" + clothing.getName() + "。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其染成不同颜色。"
										:"该行为是永久的，如果想要再次更改颜色则需要另一个染色刷。"),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(clothing, dyePreviews.get(0))
									+ "</p>"
									+ "<p>"
										+ "<b>" + clothing.getName() + "" + (clothing.getClothingType().isPlural() ? "已经" : "已经") + "染色过了</b>！"
									+ "</p>"
									+ "<p>"
										+ (isClothingDyeActionAvailable()
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b>个染色刷" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>染色刷了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用染色刷就染色" + clothing.getName() + "！"
										+ "</p>");
						}
						
						if(owner!=null) {
							owner.removeClothing(clothing);
							AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
							dyedClothing.setColours(dyePreviews);
							dyedClothing.setPattern(dyePreviewPattern);
							dyedClothing.setPatternColours(dyePreviewPatternColours);
							dyedClothing.setStickersAsObjects(dyePreviewStickers);
							owner.addClothing(dyedClothing, false);
							Main.game.addEvent(new EventLogEntry("已染色", dyedClothing.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeClothing(clothing);
							AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
							dyedClothing.setColours(dyePreviews);
							dyedClothing.setPattern(dyePreviewPattern);
							dyedClothing.setPatternColours(dyePreviewPatternColours);
							dyedClothing.setStickersAsObjects(dyePreviewStickers);
							Main.game.getPlayerCell().getInventory().addClothing(dyedClothing);
							Main.game.addEvent(new EventLogEntry("已染色", dyedClothing.getDisplayName(true)), false);
						}
						
					}
				};

			} else if (index == 6) {
				if(dyePreviews.equals(clothing.getColours())
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternColours.equals(clothing.getPatternColours())
						&& dyePreviewStickers.equals(clothing.getStickersAsObjects())) {
					return new Response("染色所有(堆叠)",
							"需要选择与之前不同的颜色才能染色" + clothing.getName() + "！",
							null); 
				}
				
				int stackCount = 0;
				if(owner!=null) {
					stackCount = owner.getClothingCount(clothing);
				} else {
					stackCount = Main.game.getPlayerCell().getInventory().getClothingCount(clothing);
				}

				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("染色所有(堆叠)",
							"你只有一件"+clothing.getName()+"，所以应该使用单次动作染色……",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH));
					
					if(dyeBrushCount<stackCount) {
						return new Response("染色所有(堆叠)",
								"你没有足够的染色刷将所有" + clothing.getNamePlural() + "染色！你只有个"+dyeBrushCount+"染色刷，但堆叠中有"+stackCount+"件"+clothing.getNamePlural()+"……",
								null); 
					}
				}
				
				return new Response("染色所有(堆叠)",
						"将所有在该衣物堆叠中的" + clothing.getNamePlural() + "染色为所选颜色(共计"+stackCount+")。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其染成不同颜色。"
										:"该行为是永久的，如果想要再次更改颜色则需要另一个染色刷。"),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(clothing, dyePreviews.get(0))
									+ "</p>"
									+ "<p>"
										+ "<b>"+clothing.getName()+(clothing.getClothingType().isPlural()?"":"")+"已经被染色过了</b>！"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>接着你对剩余"+Util.intToString(finalCount-1)+"件"+clothing.getNamePlural()+"重复了此动作……</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (isClothingDyeActionAvailable()
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b>个染色刷" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>染色刷了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用染色刷就染色"
												+ (finalCount==2
													?"两"
													:"所有"+Util.intToString(finalCount))
												+"件" + clothing.getNamePlural() + "！"
										+ "</p>");
						}
						
						if(owner!=null) {
							owner.removeClothing(clothing, finalCount);
							AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
							dyedClothing.setColours(dyePreviews);
							dyedClothing.setPattern(dyePreviewPattern);
							dyedClothing.setPatternColours(dyePreviewPatternColours);
							dyedClothing.setStickersAsObjects(dyePreviewStickers);
							owner.addClothing(dyedClothing, finalCount, false, false);
							Main.game.addEvent(new EventLogEntry("已染色", dyedClothing.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeClothing(clothing, finalCount);
							AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
							dyedClothing.setColours(dyePreviews);
							dyedClothing.setPattern(dyePreviewPattern);
							dyedClothing.setPatternColours(dyePreviewPatternColours);
							dyedClothing.setStickersAsObjects(dyePreviewStickers);
							Main.game.getPlayerCell().getInventory().addClothing(dyedClothing, finalCount);
							Main.game.addEvent(new EventLogEntry("已染色", dyedClothing.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 11) {
				if(dyePreviews.equals(clothing.getColours())
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternColours.equals(clothing.getPatternColours())
						&& dyePreviewStickers.equals(clothing.getStickersAsObjects())) {
					return new Response("染色所有",
							"需要选择与之前不同的颜色才能染色" + clothing.getName() + "！",
							null); 
				}
				
				List<AbstractClothing> clothingMatches = new ArrayList<>();
				int stackCount = 0;
				if(owner!=null) {
					for(Entry<AbstractClothing, Integer> entry : owner.getAllClothingInInventory().entrySet()) {
						if(entry.getKey().getClothingType().equals(clothing.getClothingType())) {
							clothingMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				} else {
					for(Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getAllClothingInInventory().entrySet()) {
						if(entry.getKey().getClothingType().equals(clothing.getClothingType())) {
							clothingMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				}
				
				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("染色所有",
							"你只有一件"+clothing.getName()+"，所以应该使用单次动作染色……",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH));
					
					if(dyeBrushCount<stackCount) {
						return new Response("染色所有",
								"你没有足够的染色刷将所有" + clothing.getNamePlural() + "染色！你只有个"+dyeBrushCount+"染色刷，但总共有"+stackCount+"件"+clothing.getNamePlural()+"……",
								null); 
					}
				}
				
				return new Response("染色所有",
						"将所有在该衣物堆叠中的" + clothing.getNamePlural() + "染色为所选颜色(共计"+stackCount+")。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其染成不同颜色。"
										:"该行为是永久的，如果想要再次更改颜色则需要另一个染色刷。"),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(clothing, dyePreviews.get(0))
									+ "</p>"
									+ "<p>"
									+ "<b>"+clothing.getName()+(clothing.getClothingType().isPlural()?"":"")+"已经被染色过了</b>！"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>接着你对剩余"+Util.intToString(finalCount-1)+"件"+clothing.getNamePlural()+"重复了此动作……</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (isClothingDyeActionAvailable()
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b>个染色刷" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>染色刷了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用染色刷就染色"
												+ (finalCount==2
													?"两"
													:"所有"+Util.intToString(finalCount))
												+"件" + clothing.getNamePlural() + "！"
										+ "</p>");
						}
						
						if(owner!=null) {
							for(AbstractClothing c : clothingMatches) {
								int clothingCount = owner.getAllClothingInInventory().get(c);
								owner.removeClothing(c, clothingCount);
								AbstractClothing dyedClothing = new AbstractClothing(c) {};
								dyedClothing.setColours(dyePreviews);
								dyedClothing.setPattern(dyePreviewPattern);
								dyedClothing.setPatternColours(dyePreviewPatternColours);
								dyedClothing.setStickersAsObjects(dyePreviewStickers);
								owner.addClothing(dyedClothing, clothingCount, false, false);
								Main.game.addEvent(new EventLogEntry("已染色", dyedClothing.getDisplayName(true)), false);
							}
							
						} else {
							for(AbstractClothing c : clothingMatches) {
								int clothingCount = Main.game.getPlayerCell().getInventory().getAllClothingInInventory().get(c);
								Main.game.getPlayerCell().getInventory().removeClothing(c, clothingCount);
								AbstractClothing dyedClothing = new AbstractClothing(c) {};
								dyedClothing.setColours(dyePreviews);
								dyedClothing.setPattern(dyePreviewPattern);
								dyedClothing.setPatternColours(dyePreviewPatternColours);
								dyedClothing.setStickersAsObjects(dyePreviewStickers);
								Main.game.getPlayerCell().getInventory().addClothing(dyedClothing, clothingCount);
								Main.game.addEvent(new EventLogEntry("已染色", dyedClothing.getDisplayName(true)), false);
							}
						}
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static final DialogueNode DYE_EQUIPPED_CLOTHING = new DialogueNode("染色衣物", "", true) {

		@Override
		public String getContent() {
			return getClothingDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回之前的菜单。", INVENTORY_MENU);

			} else if (index == 1) {
				if(dyePreviews.equals(clothing.getColours())
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternColours.equals(clothing.getPatternColours())
						&& dyePreviewStickers.equals(clothing.getStickersAsObjects())) {
					return new Response("染色",
							"需要选择与之前不同的颜色才能染色" + clothing.getName() + "！",
							null); 
				}
				
				return new Response("染色",
								"使用选中的颜色染色" + clothing.getName() + "。"
										+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其染成不同颜色。"
												:"该行为是永久的，如果想要再次更改颜色则需要另一个染色刷。"),
								INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(clothing, dyePreviews.get(0))
									+ "</p>"
									+ "<p>"
										+ "<b>" + clothing.getName() + "" + (clothing.getClothingType().isPlural() ? "已经" : "已经") + "染色过了</b>！"
									+ "</p>"
									+ "<p>"
										+ (isClothingDyeActionAvailable()
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b>个染色刷" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>染色刷了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用染色刷就染色" + clothing.getName() + "！"
										+ "</p>");
						}
						
						clothing.setColours(dyePreviews);
						clothing.setPattern(dyePreviewPattern);
						clothing.setPatternColours(dyePreviewPatternColours);
						clothing.setStickersAsObjects(dyePreviewStickers);
						
						Main.game.addEvent(new EventLogEntry("已染色", clothing.getDisplayName(true)), false);
					}
				};

			} else
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	
	public static final DialogueNode DYE_CLOTHING_CHARACTER_CREATION = new DialogueNode("选择颜色", "", true) {

		@Override
		public String getContent() {
			return getClothingDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回之前的菜单。", CLOTHING_INVENTORY);

			} else if (index == 1) {
				if(dyePreviews.equals(clothing.getColours())
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternColours.equals(clothing.getPatternColours())
						&& dyePreviewStickers.equals(clothing.getStickersAsObjects())) {
					return new Response("染色",
							"需要选择与之前不同的颜色才能染色" + clothing.getName() + "！",
							null); 
				}
				
				return new Response("染色",
						"更改" + clothing.getName() + "的颜色为你选择的颜色。",
						INVENTORY_MENU){
					@Override
					public void effects(){
						Main.game.getPlayerCell().getInventory().removeClothing(clothing);
						AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
						dyedClothing.setColours(dyePreviews);
						dyedClothing.setPattern(dyePreviewPattern);
						dyedClothing.setPatternColours(dyePreviewPatternColours);
						dyedClothing.setStickersAsObjects(dyePreviewStickers);
						clothing = dyedClothing;
						Main.game.getPlayerCell().getInventory().addClothing(dyedClothing);
					}
				};

			} else
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static final DialogueNode DYE_EQUIPPED_CLOTHING_CHARACTER_CREATION = new DialogueNode("选择颜色", "", true) {

		@Override
		public String getContent() {
			return getClothingDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回之前的菜单。", CLOTHING_EQUIPPED);

			} else if (index  == 1) {
				if(dyePreviews.equals(clothing.getColours())
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternColours.equals(clothing.getPatternColours())
						&& dyePreviewStickers.equals(clothing.getStickersAsObjects())) {
					return new Response("染色",
							"需要选择与之前不同的颜色才能染色" + clothing.getName() + "！",
							null); 
				}
				
				return new Response("染色",
						"更改" + clothing.getName() + "的颜色为你选择的颜色。",
						CLOTHING_EQUIPPED){
					@Override
					public void effects(){
						clothing.setColours(dyePreviews);
						clothing.setPattern(dyePreviewPattern);
						clothing.setPatternColours(dyePreviewPatternColours);
						clothing.setStickersAsObjects(dyePreviewStickers);
					}
				};

			} else
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static final DialogueNode DYE_WEAPON = new DialogueNode("染色武器", "", true) {

		@Override
		public String getContent() {
			return getWeaponDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回之前的菜单。", INVENTORY_MENU);

			} else if (index == 1) {
				if (!Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
						&& !Main.game.isDebugMode()) {
					return new Response("染色",
							"你没有染色刷，所以无法更改" + weapon.getName() + "的颜色……",
							null); 
				}
				
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("染色",
							"需要选择与之前不同的颜色才能染色" + weapon.getName() + "！",
							null); 
				}
				
				return new Response("染色",
						"使用选中的颜色染色" + weapon.getName() + "。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其染成不同颜色。"
										:"该行为是永久的，如果想要再次更改颜色则需要另一个染色刷。"),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p>"
									+ "<p>"
										+ "<b>" + weapon.getName() + "" + (weapon.getWeaponType().isPlural() ? "" : "") + "已经染色过了</b>！"
									+ "</p>"
									+ "<p>"
										+ (isClothingDyeActionAvailable()
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b>个染色刷" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>染色刷了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
										+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用染色刷就染色" + weapon.getName() + "！"
									+ "</p>");
						}
						 
						if(owner!=null) {
							owner.removeWeapon(weapon);
							AbstractWeapon dyedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							dyedWeapon.setColours(dyePreviews);
							owner.addWeapon(dyedWeapon, false);
							Main.game.addEvent(new EventLogEntry("已染色", dyedWeapon.getDisplayName(true)), false);
							weapon = dyedWeapon;

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon);
							AbstractWeapon dyedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							dyedWeapon.setColours(dyePreviews);
							Main.game.getPlayerCell().getInventory().addWeapon(dyedWeapon);
							Main.game.addEvent(new EventLogEntry("已染色", dyedWeapon.getDisplayName(true)), false);
							weapon = dyedWeapon;
						}
					}
				};

			} else if (index == 2) {
				if (!Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
						&& !Main.game.isDebugMode()) {
					return new Response("重铸",
							"你没有重铸锤，所以无法更改" + weapon.getName() + "的伤害类型……",
							null); 
				}
				
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("重铸",
							"需要选择与之前不同的武器类型才能重铸" + weapon.getName() + "！",
							null); 
				}
				
				return new Response("重铸",
						"将" + weapon.getName() + "重铸为选中的伤害类型。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其重铸。"
										:"该行为是永久的，如果想要再次更改伤害类型则需要另一个重铸锤。"),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), owner, false);
							
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
										+ "<b>" + weapon.getName() + "" + (weapon.getWeaponType().isPlural() ? "" : "") + "已经重铸过了</b>！"
									+ "</p>"
									+ "<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "</b>个重铸锤" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>重铸锤了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用重铸锤就重铸" + weapon.getName() + "！"
										+ "</p>");
						}
						 
						if(owner!=null) {
							owner.removeWeapon(weapon);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), false);
							Main.game.addEvent(new EventLogEntry("已重铸", modifiedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon));
							Main.game.addEvent(new EventLogEntry("已重铸", modifiedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 3) {
				if ((!Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || !Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH))
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
						&& !Main.game.isDebugMode()) {
					return new Response("染色并重铸",
							"你没有染色刷和重铸锤，无法染色并重铸" + weapon.getName() + "……",
							null); 
				}
				
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("染色并重铸",
							"需要选择与之前不同的武器类型才能重铸" + weapon.getName() + "！",
							null); 
				}
				
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("染色并重铸",
							"需要选择与之前不同的颜色才能染色" + weapon.getName() + "！",
							null); 
				}
				
				return new Response("染色并重铸",
						"将" + weapon.getName() + "染色并重铸为你选择的颜色和伤害类型。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其染色与重铸。"
										:"该行为是永久的，如果想要再次更改颜色和伤害类型则需要另一个染色刷和重铸锤。"),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
										+ "<b>" + weapon.getName() + "" + (weapon.getWeaponType().isPlural() ? "" : "") + "已经染色和重铸过了</b>！"
									+ "</p>"
									+ "<p>"
										+ (isClothingDyeActionAvailable()
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b>个染色刷" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>染色刷了！")
										+"<br/>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "</b>个重铸锤" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>重铸锤了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用染色刷和重铸锤就染色和重铸" + weapon.getName() + "！"
										+ "</p>");
						}
						
						if(owner!=null) {
							owner.removeWeapon(weapon);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							modifiedWeapon.setColours(dyePreviews);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), false);
							Main.game.addEvent(new EventLogEntry("已染色并重铸", modifiedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							modifiedWeapon.setColours(dyePreviews);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon));
							Main.game.addEvent(new EventLogEntry("已染色并重铸", modifiedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 6) {
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("染色所有(堆叠)",
							"需要选择与之前不同的颜色才能染色" + weapon.getName() + "！",
							null); 
				}
				
				int stackCount = 0;
				if(owner!=null) {
					stackCount = owner.getWeaponCount(weapon);
				} else {
					stackCount = Main.game.getPlayerCell().getInventory().getWeaponCount(weapon);
				}

				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("染色所有(堆叠)",
							"你只有一把"+weapon.getName()+"，所以应该使用单次动作染色……",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH));
					
					if(dyeBrushCount<stackCount) {
						return new Response("染色所有(堆叠)",
								"你没有足够的染色刷将所有" + weapon.getNamePlural() + "染色！你只有个"+dyeBrushCount+"染色刷，但堆叠中有"+stackCount+"把"+weapon.getNamePlural()+"……",
								null); 
					}
				}
				
				return new Response("染色所有(堆叠)",
						"将所有在该武器堆叠中的" + weapon.getNamePlural() + "染色为所选颜色(共计"+stackCount+")。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其染成不同颜色。"
										:"该行为是永久的，如果想要再次更改颜色则需要另一个染色刷。"),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p>"
									+ "<p>"
									+ "<b>"+weapon.getName()+(weapon.getWeaponType().isPlural()?"":"")+"已经染色过了</b>！"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>接着你对剩余"+Util.intToString(finalCount-1)+"把"+weapon.getNamePlural()+"重复了此动作……</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (isClothingDyeActionAvailable()
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b>个染色刷" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>染色刷了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用染色刷就染色"
												+ (finalCount==2
													?"两"
													:"所有"+Util.intToString(finalCount))
												+"把" + weapon.getNamePlural() + "！"
										+ "</p>");
						}
						
						if(owner!=null) {
							owner.removeWeapon(weapon, finalCount);
							AbstractWeapon dyedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							dyedWeapon.setColours(dyePreviews);
							owner.addWeapon(dyedWeapon, finalCount, false, false);
							Main.game.addEvent(new EventLogEntry("已染色", dyedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon, finalCount);
							AbstractWeapon dyedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							dyedWeapon.setColours(dyePreviews);
							Main.game.getPlayerCell().getInventory().addWeapon(dyedWeapon, finalCount);
							Main.game.addEvent(new EventLogEntry("已染色", dyedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 7) {
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("重铸所有(堆叠)",
							"需要选择与之前不同的武器类型才能重铸" + weapon.getName() + "！",
							null); 
				}
				
				int stackCount = 0;
				if(owner!=null) {
					stackCount = owner.getWeaponCount(weapon);
				} else {
					stackCount = Main.game.getPlayerCell().getInventory().getWeaponCount(weapon);
				}

				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("重铸所有(堆叠)",
							"你只有一把"+weapon.getName()+"，所以应该使用单次动作重铸……",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int reforgeHammerCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER));
					
					if(reforgeHammerCount<stackCount) {
						return new Response("重铸所有(堆叠)",
								"你没有足够的重铸锤将所有" + weapon.getNamePlural() + "重铸！你只有"+reforgeHammerCount+"个重铸锤，但堆叠中有"+stackCount+"把"+weapon.getNamePlural()+"……",
								null); 
					}
				}
				
				return new Response("重铸所有(堆叠)",
						"将所有在该武器堆叠中的" + weapon.getNamePlural() + "重铸为选中的伤害类型("+stackCount+" in total)。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其重铸。"
										:"该行为是永久的，如果想要再次更改伤害类型则需要另一个重铸锤。"),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
									+ "<b>"+weapon.getName()+(weapon.getWeaponType().isPlural()?"":"")+"已经重铸过了</b>！"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>接着你对剩余"+Util.intToString(finalCount-1)+"把"+weapon.getNamePlural()+"重复了此动作……</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "</b>个重铸锤" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>重铸锤了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用重铸锤就重铸"
												+ (finalCount==2
													?"两"
													:"所有"+Util.intToString(finalCount))
												+"把" + weapon.getNamePlural() + "！"
										+ "</p>");
						}
						
						if(owner!=null) {
							owner.removeWeapon(weapon, finalCount);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), finalCount, false, false);
							Main.game.addEvent(new EventLogEntry("已重铸", modifiedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon, finalCount);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), finalCount);
							Main.game.addEvent(new EventLogEntry("已重铸", modifiedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 8) {
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("染色并重铸所有(堆叠)",
							"需要选择与之前不同的武器类型才能重铸" + weapon.getName() + "！",
							null); 
				}
				
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("染色并重铸所有(堆叠)",
							"需要选择与之前不同的颜色才能染色" + weapon.getName() + "！",
							null); 
				}
				
				
				int stackCount = 0;
				if(owner!=null) {
					stackCount = owner.getWeaponCount(weapon);
				} else {
					stackCount = Main.game.getPlayerCell().getInventory().getWeaponCount(weapon);
				}

				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("染色并重铸所有(堆叠)",
							"你只有一把"+weapon.getName()+"，所以应该使用单次动作染色和重铸……",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH));
					int reforgeHammerCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER));
					
					if(dyeBrushCount<stackCount || reforgeHammerCount<stackCount) {
						return new Response("染色并重铸所有(堆叠)",
								"你没有足够的染色刷和重铸锤将所有堆叠中的"+stackCount+"把"+weapon.getNamePlural()+"染色和重铸……",
								null); 
					}
				}
				
				return new Response("染色并重铸所有(堆叠)",
						"将所有在该武器堆叠中的" + weapon.getNamePlural() + "染色和重铸为所选颜色和伤害类型(共计"+stackCount+")。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时做这个。"
										:"该行为是永久的，如果想要再次做这个则需要另一个染色刷和重铸锤。"),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
									+ "<b>"+weapon.getName()+(weapon.getWeaponType().isPlural()?"":"")+"已经染色和重铸过了</b>！"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>接着你对剩余"+Util.intToString(finalCount-1)+"把"+weapon.getNamePlural()+"重复了此动作……</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b>个染色刷" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>染色刷了！")
									+ "</p>");

							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "</b>个重铸锤" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>重铸锤了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用染色刷就染色"
												+ (finalCount==2
													?"两"
													:"所有"+Util.intToString(finalCount))
												+"把" + weapon.getNamePlural() + "！"
										+ "</p>");
						}
						
						if(owner!=null) {
							owner.removeWeapon(weapon, finalCount);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							modifiedWeapon.setColours(dyePreviews);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), finalCount, false, false);
							Main.game.addEvent(new EventLogEntry("已染色并重铸", modifiedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon, finalCount);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							modifiedWeapon.setColours(dyePreviews);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), finalCount);
							Main.game.addEvent(new EventLogEntry("已染色并重铸", modifiedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 11) {
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("染色所有",
							"需要选择与之前不同的颜色才能染色" + weapon.getName() + "！",
							null); 
				}
				
				List<AbstractWeapon> weaponMatches = new ArrayList<>();
				int stackCount = 0;
				if(owner!=null) {
					for(Entry<AbstractWeapon, Integer> entry : owner.getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				} else {
					for(Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				}
				
				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("染色所有",
							"你只有一把"+weapon.getName()+"，所以应该使用单次动作染色……",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH));
					
					if(dyeBrushCount<stackCount) {
						return new Response("染色所有",
								"你没有足够的染色刷将所有" + weapon.getNamePlural() + "染色！你只有个"+dyeBrushCount+"染色刷，但总共有"+stackCount+"把"+weapon.getNamePlural()+"……",
								null); 
					}
				}
				
				return new Response("染色所有",
						"将所有在该武器堆叠中的" + weapon.getNamePlural() + "染色为所选颜色(共计"+stackCount+")。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其染成不同颜色。"
										:"该行为是永久的，如果想要再次更改颜色则需要另一个染色刷。"),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p>"
									+ "<p>"
									+ "<b>"+weapon.getName()+(weapon.getWeaponType().isPlural()?"":"")+"已经染色过了</b>！"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>接着你对剩余"+Util.intToString(finalCount-1)+"把"+weapon.getNamePlural()+"重复了此动作……</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (isClothingDyeActionAvailable()
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b>个染色刷" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>染色刷了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用染色刷就染色"
												+ (finalCount==2
													?"两"
													:"所有"+Util.intToString(finalCount))
												+"把" + weapon.getNamePlural() + "！"
										+ "</p>");
						}
						

						if(owner!=null) {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = owner.getAllWeaponsInInventory().get(w);
								owner.removeWeapon(w, weaponCount);
								AbstractWeapon dyedWeapon = Main.game.getItemGen().generateWeapon(w);
								dyedWeapon.setColours(dyePreviews);
								owner.addWeapon(dyedWeapon, weaponCount, false, false);
								Main.game.addEvent(new EventLogEntry("已染色", dyedWeapon.getDisplayName(true)), false);
							}
							
						} else {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().get(w);
								Main.game.getPlayerCell().getInventory().removeWeapon(w, weaponCount);
								AbstractWeapon dyedWeapon = Main.game.getItemGen().generateWeapon(w);
								dyedWeapon.setColours(dyePreviews);
								Main.game.getPlayerCell().getInventory().addWeapon(dyedWeapon, weaponCount);
								Main.game.addEvent(new EventLogEntry("已染色", dyedWeapon.getDisplayName(true)), false);
							}
						}
					}
				};

			} else if (index == 12) {
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("重铸所有",
							"需要选择与之前不同的武器类型才能重铸" + weapon.getName() + "！",
							null); 
				}
				
				List<AbstractWeapon> weaponMatches = new ArrayList<>();
				int stackCount = 0;
				if(owner!=null) {
					for(Entry<AbstractWeapon, Integer> entry : owner.getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				} else {
					for(Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				}
				
				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("重铸所有",
							"你只有一把"+weapon.getName()+"，所以应该使用单次动作重铸……",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int reforgeHammerCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER));
					
					if(reforgeHammerCount<stackCount) {
						return new Response("重铸所有",
								"你没有足够的重铸锤将所有" + weapon.getNamePlural() + "重铸！你只有"+reforgeHammerCount+"个重铸锤，但总共有"+stackCount+"把"+weapon.getNamePlural()+"……",
								null); 
					}
				}
				
				return new Response("重铸所有",
						"将所有在该武器堆叠中的" + weapon.getNamePlural() + "重铸为选中的伤害类型("+stackCount+" in total)。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其重铸。"
										:"该行为是永久的，如果想要再次更改伤害类型则需要另一个重铸锤。"),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
									+ "<b>"+weapon.getName()+(weapon.getWeaponType().isPlural()?"":"")+"已经重铸过了</b>！"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>接着你对剩余"+Util.intToString(finalCount-1)+"把"+weapon.getNamePlural()+"重复了此动作……</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "</b>个重铸锤" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>重铸锤了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用重铸锤就重铸"
												+ (finalCount==2
													?"两"
													:"所有"+Util.intToString(finalCount))
												+"把" + weapon.getNamePlural() + "！"
										+ "</p>");
						}

						if(owner!=null) {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = owner.getAllWeaponsInInventory().get(w);
								owner.removeWeapon(w, weaponCount);
								AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(w);
								modifiedWeapon.setDamageType(damageTypePreview);
								// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
								owner.addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), weaponCount, false, false);
								Main.game.addEvent(new EventLogEntry("已重铸", modifiedWeapon.getDisplayName(true)), false);
							}

						} else {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().get(w);
								Main.game.getPlayerCell().getInventory().removeWeapon(w, weaponCount);
								AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(w);
								modifiedWeapon.setDamageType(damageTypePreview);
								// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
								Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), weaponCount);
								Main.game.addEvent(new EventLogEntry("已重铸", modifiedWeapon.getDisplayName(true)), false);
							}
						}
					}
				};

			} else if (index == 13) {
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("染色并重铸所有",
							"需要选择与之前不同的武器类型才能重铸" + weapon.getName() + "！",
							null); 
				}
				
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("染色并重铸所有",
							"需要选择与之前不同的颜色才能染色" + weapon.getName() + "！",
							null); 
				}
				
				List<AbstractWeapon> weaponMatches = new ArrayList<>();
				int stackCount = 0;
				if(owner!=null) {
					for(Entry<AbstractWeapon, Integer> entry : owner.getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				} else {
					for(Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				}
				
				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("染色并重铸所有",
							"你只有一把"+weapon.getName()+"，所以应该使用单次动作染色和重铸……",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH));
					int reforgeHammerCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER));
					
					if(dyeBrushCount<stackCount || reforgeHammerCount<stackCount) {
						return new Response("染色并重铸所有",
								"你没有足够的染色刷和重铸锤将所有堆叠中的"+stackCount+"把"+weapon.getNamePlural()+"染色和重铸……",
								null); 
					}
				}
				
				return new Response("染色并重铸所有",
						"将所有在该武器堆叠中的" + weapon.getNamePlural() + "染色和重铸为所选颜色和伤害类型(共计"+stackCount+")。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时做这个。"
										:"该行为是永久的，如果想要再次做这个则需要另一个染色刷和重铸锤。"),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
									+ "<b>"+weapon.getName()+(weapon.getWeaponType().isPlural()?"":"")+"已经染色和重铸过了</b>！"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>接着你对剩余"+Util.intToString(finalCount-1)+"把"+weapon.getNamePlural()+"重复了此动作……</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b>个染色刷" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>染色刷了！")
									+ "</p>");

							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "</b>个重铸锤" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>重铸锤了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用染色刷和重铸锤就染色和重铸"
												+ (finalCount==2
													?"两"
													:"所有"+Util.intToString(finalCount))
												+"把" + weapon.getNamePlural() + "！"
										+ "</p>");
						}
						
						if(owner!=null) {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = owner.getAllWeaponsInInventory().get(w);
								owner.removeWeapon(w, weaponCount);
								AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(w);
								modifiedWeapon.setDamageType(damageTypePreview);
								modifiedWeapon.setColours(dyePreviews);
								// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
								owner.addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), weaponCount, false, false);
								Main.game.addEvent(new EventLogEntry("已染色并重铸", modifiedWeapon.getDisplayName(true)), false);
							}
							
						} else {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().get(w);
								Main.game.getPlayerCell().getInventory().removeWeapon(w, weaponCount);
								AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(w);
								modifiedWeapon.setDamageType(damageTypePreview);
								modifiedWeapon.setColours(dyePreviews);
								// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
								Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), weaponCount);
								Main.game.addEvent(new EventLogEntry("已染色并重铸", modifiedWeapon.getDisplayName(true)), false);
							}
						}
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static final DialogueNode DYE_EQUIPPED_WEAPON = new DialogueNode("染色武器", "", true) {

		@Override
		public String getContent() {
			return getWeaponDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回之前的菜单。", INVENTORY_MENU);

			} else if (index == 1) {
				if (!Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
						&& !Main.game.isDebugMode()) {
					return new Response("染色",
							"你没有染色刷，所以无法更改" + weapon.getName() + "的颜色……",
							null); 
				}
				
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("染色",
							"需要选择与之前不同的颜色才能染色" + weapon.getName() + "！",
							null); 
				}
				
				return new Response("染色",
						"使用选中的颜色染色" + weapon.getName() + "。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其染成不同颜色。"
										:"该行为是永久的，如果想要再次更改颜色则需要另一个染色刷。"),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p>"
									+ "<p>"
										+ "<b>" + weapon.getName() + "" + (weapon.getWeaponType().isPlural() ? "" : "") + "已经染色过了</b>！"
									+ "</p>"
									+ "<p>"
										+ (isClothingDyeActionAvailable()
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b>个染色刷" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>染色刷了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用染色刷就染色" + weapon.getName() + "！"
										+ "</p>");
						}
						
						

						owner.unequipWeaponIntoVoid(weaponSlot, weapon, true);
						AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
						modifiedWeapon.setColours(dyePreviews);
						
						if(weaponSlot==InventorySlot.WEAPON_MAIN_1
								|| weaponSlot==InventorySlot.WEAPON_MAIN_2
								|| weaponSlot==InventorySlot.WEAPON_MAIN_3) {
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(modifiedWeapon));
							
						} else {
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(modifiedWeapon));
						}

//						weapon.setPrimaryColour(dyePreviewPrimary);
//						weapon.setSecondaryColour(dyePreviewSecondary);
						Main.game.addEvent(new EventLogEntry("已染色", weapon.getDisplayName(true)), false);
					}
				};

			} else if (index == 2) {
				if (!Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
						&& !Main.game.isDebugMode()) {
					return new Response("重铸",
							"你没有重铸锤，所以无法更改" + weapon.getName() + "的伤害类型……",
							null); 
				}
				
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("重铸",
							"需要选择与之前不同的武器类型才能重铸" + weapon.getName() + "！",
							null); 
				}
				
				return new Response("重铸",
						"将" + weapon.getName() + "重铸为选中的伤害类型。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其重铸。"
										:"该行为是永久的，如果想要再次更改伤害类型则需要另一个重铸锤。"),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
										+ "<b>" + weapon.getName() + "" + (weapon.getWeaponType().isPlural() ? "" : "") + "已经重铸过了</b>！"
									+ "</p>"
									+ "<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "</b>个重铸锤" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>重铸锤了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用重铸锤就重铸" + weapon.getName() + "！"
										+ "</p>");
						}

						owner.unequipWeaponIntoVoid(weaponSlot, weapon, true);
						AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
						modifiedWeapon.setDamageType(damageTypePreview);
						
						if(weaponSlot==InventorySlot.WEAPON_MAIN_1
								|| weaponSlot==InventorySlot.WEAPON_MAIN_2
								|| weaponSlot==InventorySlot.WEAPON_MAIN_3) {
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(modifiedWeapon));
							
						} else {
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(modifiedWeapon));
						}
						
						Main.game.addEvent(new EventLogEntry("已重铸", weapon.getDisplayName(true)), false);
					}
				};

			} else if (index == 3) {
				if ((!Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || !Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH))
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
						&& !Main.game.isDebugMode()) {
					return new Response("染色并重铸",
							"你没有染色刷和重铸锤，无法染色并重铸" + weapon.getName() + "……",
							null); 
				}
				
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("染色并重铸",
							"需要选择与之前不同的武器类型才能重铸" + weapon.getName() + "！",
							null); 
				}
				
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("染色并重铸",
							"需要选择与之前不同的颜色才能染色" + weapon.getName() + "！",
							null); 
				}
				
				return new Response("染色并重铸",
						"将" + weapon.getName() + "染色并重铸为你选择的颜色和伤害类型。"
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?"该行为是永久的，但由于你精通的[style.colourEarth(土系法术)]，你可以随时将其染色与重铸。"
										:"该行为是永久的，如果想要再次更改颜色和伤害类型则需要另一个染色刷和重铸锤。"),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
										+ "<b>" + weapon.getName() + "" + (weapon.getWeaponType().isPlural() ? "" : "") + "已经重铸过了</b>！"
									+ "</p>"
									+ "<p>"
										+ (isClothingDyeActionAvailable()
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b>个染色刷" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>染色刷了！")
										+"<br/>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"你还剩下<b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "</b>个重铸锤" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "") + "！"
												:"你<b>没有</b>重铸锤了！")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "由于你精通的[style.boldEarth(土系法术)]，你可以不使用染色刷和重铸锤就染色和重铸" + weapon.getName() + "！"
										+ "</p>");
						}

						owner.unequipWeaponIntoVoid(weaponSlot, weapon, true);
						AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
						modifiedWeapon.setColours(dyePreviews);
						modifiedWeapon.setDamageType(damageTypePreview);
						
						if(weaponSlot==InventorySlot.WEAPON_MAIN_1
								|| weaponSlot==InventorySlot.WEAPON_MAIN_2
								|| weaponSlot==InventorySlot.WEAPON_MAIN_3) {
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(modifiedWeapon));
							
						} else {
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(modifiedWeapon));
						}
						
//						weapon.setDamageType(damageTypePreview);
//						weapon.setPrimaryColour(dyePreviewPrimary);
//						weapon.setSecondaryColour(dyePreviewSecondary);
						Main.game.addEvent(new EventLogEntry("已染色并重铸", weapon.getDisplayName(true)), false);
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	
	
	// Utility methods:
	
	private static String getDyeBrushEffects(AbstractClothing clothing, Colour colour) {
		return "<p>"
					+ "当你握住染色刷时，你看到刷头发出一阵紫色的强光。"
					+ "当其越靠近" + clothing.getName() + "，光芒就越明亮，最后一瞬间，五颜六色的画面开始在你的脑中闪烁。"
					+ "用毛刷触碰到" + clothing.getName() + "的表面时，染色刷瞬间汽化了！"
					+ "你看得出奥术附魔已经将" + clothing.getName() + "染成" + colour.getName() + "。"
				+ "</p>";
	}
	
	private static String getDyeBrushEffects(AbstractWeapon weapon, Colour colour) {
		return "<p>"
					+ "当你握住染色刷时，你看到刷头发出一阵紫色的强光。"
					+ "当其越靠近" + weapon.getName() + "，光芒就越明亮，最后一瞬间，五颜六色的画面开始在你的脑中闪烁。"
					+ "用毛刷触碰到" + weapon.getName() + "的表面时，染色刷瞬间汽化了！"
					+ "你看得出奥术附魔已经将" + weapon.getName() + "染成" + colour.getName() + "。"
				+ "</p>";
	}
	
	private static String getReforgeHammerEffects(AbstractWeapon weapon, DamageType damageType) {
		return "<p>"
					+ "当你握住重铸锤时，你看到金属头发出一阵暗紫色的光芒。"
					+ "当其越靠近" + weapon.getName() + "，光芒就越明亮，最后一瞬间，各类伤害类型的画面开始在你的脑中闪烁。"
					+ "用金属头触碰到" + weapon.getName() + "的表面时，重铸锤瞬间汽化了！"
					+ "你注意到奥术附魔已经重铸了" + weapon.getName() + "，现在这把武器能造成" + damageType.getName() + "伤害。"
				+ "</p>";
	}
	
	private static String getItemDisplayPanel(AbstractCoreItem item, String SVGString, String title, String description) {
		return "<div class='inventoryImage'>" // style='width: calc(50% - 4px);'
					+ "<div class='inventoryImage-content'>"
						+ SVGString
					+ "</div>"
				+ "</div>"
				+ "<h5 style='margin-bottom:0; padding-bottom:0;'><b>"+title+"</b></h5>"
				+ "<p style='margin-top:0; padding-top:0;'>"
					+ UtilText.parse(item, description)
				+ "</p>";
	}
	
	private static String getGeneralResponseTabTitle(int index) {
		if(index==0) {
			return "总览";
		} else if(index==1) {
			return "选择的物品";
		} else {
			return null;
		}
	}
	
	private static Response getCloseInventoryResponse() {
		if(interactionType == InventoryInteraction.CHARACTER_CREATION) {
			return new Response("返回", "对着镜子检查你的外观。", CharacterCreation.CHOOSE_ADVANCED_APPEARANCE){
				@Override
				public int getSecondsPassed() {
					return -CharacterCreation.TIME_TO_CLOTHING;
				}
				@Override
				public void effects(){
					item = null;
					clothing = null;
					weapon = null;
				}
			};
			
		} else {
			return new ResponseEffectsOnly("关闭物品栏", "关闭物品栏菜单。"){
				@Override
				public void effects(){
					item = null;
					clothing = null;
					weapon = null;
					Main.mainController.openInventory();
				}
			};
		}
	}
	
	private static Response getBuybackResponse() {
		if (buyback) {
			return new Response("正常交易", "转换至正常交易菜单。", INVENTORY_MENU){
				@Override
				public void effects(){
					buyback = !buyback;
				}
			};
		} else {
			if(Main.game.getPlayer().getBuybackStack()==null || Main.game.getPlayer().getBuybackStack().isEmpty()) {
				return new Response("回购", "你还没有出手任何物品，所以无法回购……", null);
			} else {
				return new Response("回购", "转换至回购菜单。", INVENTORY_MENU){
					@Override
					public void effects(){
						buyback = !buyback;
					}
				};
			}
		}
	}
	
	private static Response getQuickTradeResponse() {
		//TODO move this into options
		// Also, instead of being a response, this needs to be a button within the inventory UI
		return null;
		
//		if (Main.game.getDialogueFlags().quickTrade) {
//			return new Response("Quick-Manage: <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>",
//					"Quick-Manage is turned <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>!<br/>"
//							+ "That means you can buy and sell items with a single click when trading, and pick-up and drop items with a single click when in normal inventory mode.", INVENTORY_MENU){
//				
//				@Override
//				public DialogueNodeOld getNextDialogue() {
//					return Main.game.getCurrentDialogueNode();
//				}
//				
//				@Override
//				public void effects(){
//					Main.game.getDialogueFlags().quickTrade = !Main.game.getDialogueFlags().quickTrade;
//				}
//			};
//			
//		} else {
//			return new Response("Quick-Manage: <b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>OFF</b>",
//					"Quick-Manage is turned <b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>OFF</b>.<br/>"
//							+ "That means when you click on an item, you get a detailed view of the item before deciding whether to buy/sell or pick-up/drop it.", INVENTORY_MENU){
//
//				@Override
//				public DialogueNodeOld getNextDialogue() {
//					return Main.game.getCurrentDialogueNode();
//				}
//				
//				@Override
//				public void effects(){
//					Main.game.getDialogueFlags().quickTrade = !Main.game.getDialogueFlags().quickTrade;
//				}
//			};
//		}
	}
	
	private static Response getJinxRemovalResponse(boolean selfUnseal) {
		boolean ownsKey = Main.game.getPlayer().getUnlockKeyMap().containsKey(owner.getId()) && Main.game.getPlayer().getUnlockKeyMap().get(owner.getId()).contains(clothing.getSlotEquippedTo());
		int removalCost = clothing.getJinxRemovalCost(Main.game.getPlayer(), selfUnseal);
		
		if(clothing.getEffects().stream().filter(ie->ie.getSecondaryModifier()==TFModifier.CLOTHING_SEALING).findFirst().get().getPotency()==TFPotency.SPECIAL) {
			return new Response("解封([style.colourTerrible(不可能)])",
					"这个"+clothing.getName()+"拥有特殊的封印附魔，无法通过常规方式解除！",
					null);
		}
		
		if(interactionType==InventoryInteraction.COMBAT) {
			return new Response("解封"+(ownsKey?"(使用钥匙)":"(<i>"+removalCost+"精华</i>)"),
					"在战斗时无法解封衣物！",
					null);
		}

		if(interactionType==InventoryInteraction.SEX) {
			if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
				return new Response("解封"+(ownsKey?"(使用钥匙)":"(<i>"+removalCost+"精华</i>)"),
						UtilText.parse(owner, "你无法解封[npc.namePos]的衣物，因为你当前[style.colourTerrible(无法行动)]！"),
						null);
			}
			if(!selfUnseal && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				return new Response("解封"+(ownsKey?"(使用钥匙)":"(<i>"+removalCost+"精华</i>)"),
						UtilText.parse(owner, "你无法在躲藏时解封[npc.namePos]的衣物！"),
						null);
			}
			if(!Main.sex.getInitialSexManager().isAbleToRemoveClothingSeals(Main.game.getPlayer())) {
				return new Response("解封"+(ownsKey?"(使用钥匙)":"(<i>"+removalCost+"精华</i>)"),
						"无法在该性交场景解封衣物！",
						null);
			}
		}
		
		if(!ownsKey) {
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
				return new Response("解封", "你不知道如何解封衣物！或许你可以拜访莉莱雅然后问问她……", null);
			}
			if(Main.game.getPlayer().getClothingCurrentlyEquipped().stream().anyMatch(c -> c.isSelfTransformationInhibiting())) {
				return new Response("解封",
						"尽管你平时可以解封衣物，但由于你装备的衣物中有一到多个存在附魔，所以无法这么做！"
						+ "<br/>[style.italicsArcane(去找莉莱雅帮你去除封印的衣物！)]",
						null);
			}
			if(Main.game.getPlayer().getTattoos().values().stream().anyMatch(c -> c.isSelfTransformationInhibiting())) {
				return new Response("解封",
						"尽管你平时可以解封衣物，但由于你的纹身中有一到多个存在附魔，所以无法这么做！"
								+ "<br/>[style.italicsArcane(去找凯特帮你去除纹身！)]",
						null);
			}
		}
		
		if(ownsKey || Main.game.getPlayer().getEssenceCount()>=removalCost) {
			return new Response("解封"+(ownsKey?"([style.italicsGood(使用钥匙)])":"([style.italicsArcane("+removalCost+"精华)])"),
						ownsKey
							?"由于你拥有能够解锁衣服的钥匙，你能够不消耗任何奥术精华去除它！"
							:("花费"+removalCost+"奥术精华解封这件衣物。"
								+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_BONDAGE_VICTIM) && selfUnseal
									?"<br/>[style.italicsMinorBad(由于你的“"+Fetish.FETISH_BONDAGE_VICTIM.getName(Main.game.getPlayer())+"”性癖，你的解封花费变为标准情况的五倍！)]"
									:"")),
						interactionType==InventoryInteraction.SEX
							?Main.sex.SEX_DIALOGUE
							:INVENTORY_MENU) {
				@Override
				public void effects() {
					String s = "";
					if(ownsKey) {
						if(!Main.game.isInSex()) {
							Main.game.getPlayer().removeFromUnlockKeyMap(owner.getId(), clothing.getSlotEquippedTo());
						}
						s = "<p>"
								+ "Using the key which is in your possession, you unlock the "+clothing.getName()+"!"
							+ "</p>";
						
					} else {
						Main.game.getPlayer().incrementEssenceCount(-removalCost, false);
						s = UtilText.parse(owner,
								"<p>"
									+ "你引导奥术精华的能量进入了[npc.namePos]的"+clothing.getName()+"，一阵亮紫色的闪光后，你解除了封印！"
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ "移除封印消耗了你[style.boldBad("+removalCost+")][style.boldArcane(奥术精华)]！"
								+ "</p>");
					}
					
					// Have to remove and then re-add the clothing as setting the sealed status affects the clothing's hashCode
					List<DisplacementType> clothingDisplacementTypes = new ArrayList<>();
					if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
						if(Main.sex.getClothingPreSexMap().get(owner).get(clothing.getSlotEquippedTo()).get(clothing)!=null) {
							clothingDisplacementTypes.addAll(Main.sex.getClothingPreSexMap().get(owner).get(clothing.getSlotEquippedTo()).get(clothing));
						}
						Main.sex.getClothingPreSexMap().get(owner).get(clothing.getSlotEquippedTo()).remove(clothing);
					}
					clothing.setSealed(false);
					if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
						Main.sex.getClothingPreSexMap().get(owner).get(clothing.getSlotEquippedTo()).put(clothing, clothingDisplacementTypes);
					}
					
					if(interactionType==InventoryInteraction.SEX) {
						Main.sex.setUnequipClothingText(clothing, s);
						Main.mainController.openInventory();
						Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
						Main.sex.setSexStarted(true);
						
					} else {
						Main.game.getTextEndStringBuilder().append(s);
					}
				}
			};
			
		} else {
			return new Response("解封(<i>"+removalCost+"精华</i>)",
					"你至少需要"+removalCost+"奥术精华才能解封这件衣物！"
							+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_BONDAGE_VICTIM)
									?"<br/>[style.italicsMinorBad(由于你的“"+Fetish.FETISH_BONDAGE_VICTIM.getName(Main.game.getPlayer())+"”性癖，解封花费变为了标准情况的)][style.italicsBad(5倍)][style.italicsMinorBad(！)]"
									:""),
					null);
		}
	}
	
	
	
	// Items:
	
	private static void transferItems(GameCharacter from, GameCharacter to, AbstractItem item, int count) {
		if (!to.isInventoryFull() || to.hasItem(item) || item.getRarity()==Rarity.QUEST) {
			from.removeItem(item, count);
			to.addItem(item, count, false, to.isPlayer());
		}
		resetPostAction();
	}
	
	private static void dropItems(GameCharacter from, AbstractItem item, int count) {
		if (!Main.game.getPlayerCell().getInventory().isInventoryFull() || Main.game.getPlayerCell().getInventory().hasItem(item)) {
			from.dropItem(item, count, from.isPlayer());
		}
		resetPostAction();
	}
	
	private static void pickUpItems(GameCharacter to, AbstractItem item, int count) {
		if (!to.isInventoryFull() || to.hasItem(item) || item.getRarity()==Rarity.QUEST) {
			to.addItem(item, count, true, to.isPlayer());
		}
		resetPostAction();
	}
	
	private static void sellItems(GameCharacter from, GameCharacter to, AbstractItem item, int count, int itemPrice) {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.removeTraderDescription, false);
		
		if (!to.isPlayer() || !to.isInventoryFull() || to.hasItem(item) || item.getRarity()==Rarity.QUEST) {
			from.incrementMoney(itemPrice*count);
			to.incrementMoney(-itemPrice*count);
			
			if(buyback && to.isPlayer()) {
				Main.game.getPlayer().addItem(item, count, false, true);
				Main.game.getPlayer().getBuybackStack().get(buyBackIndex).incrementCount(-count);
				if(Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<=0) {	
					Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
				}
				
			} else {
				if(from.isPlayer()) {
					Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(item, itemPrice, count));
				} else {
					to.addItem(item, count, false, true);
				}
				from.removeItem(item, count);
			}
			
			if(from.isPlayer()) {
				Main.game.addEvent(new EventLogEntry("已出售",
								count+"x <span style='color:"+item.getRarity().getColour().toWebHexString()+";'>"+(count==1?item.getName():item.getNamePlural())+"</span>价格："+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(to.isPlayer()) {
				//((NPC) from).handleSellingEffects(item, count, itemPrice); // Delete: This was replaced by applyItemTransactionEffects
				Main.game.addEvent(new EventLogEntry("已买入",
								count+"x <span style='color:"+item.getRarity().getColour().toWebHexString()+";'>"+(count==1?item.getName():item.getNamePlural())+"</span>价格："+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(!from.isPlayer()) {
				((NPC)from).applyItemTransactionEffects(item, count, itemPrice, true);
				
			} else {
				((NPC)to).applyItemTransactionEffects(item, count, itemPrice, false);
			}
		}
		
		resetPostAction();
	}
	
	
	// Weapons:
	
	private static void transferWeapons(GameCharacter from, GameCharacter to, AbstractWeapon weapon, int count) {
		if (!to.isInventoryFull() || to.hasWeapon(weapon) || weapon.getRarity()==Rarity.QUEST) {
			from.removeWeapon(weapon, count);
			to.addWeapon(weapon, count, false, to.isPlayer());
		}
		resetPostAction();
	}
	
	private static void dropWeapons(GameCharacter from, AbstractWeapon weapon, int count) {
		if (!Main.game.getPlayerCell().getInventory().isInventoryFull() || Main.game.getPlayerCell().getInventory().hasWeapon(weapon)) {
			from.dropWeapon(weapon, count, from.isPlayer());
		}
		resetPostAction();
	}
	
	private static void pickUpWeapons(GameCharacter to, AbstractWeapon weapon, int count) {
		if (!to.isInventoryFull() || to.hasWeapon(weapon) || weapon.getRarity()==Rarity.QUEST) {
			to.addWeapon(weapon, count, true, to.isPlayer());
		}
		resetPostAction();
	}
	
	private static void sellWeapons(GameCharacter from, GameCharacter to, AbstractWeapon weapon, int count, int itemPrice) {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.removeTraderDescription, false);
		if (!to.isPlayer() || !to.isInventoryFull() || to.hasWeapon(weapon) || weapon.getRarity()==Rarity.QUEST) {

			from.incrementMoney(itemPrice*count);
			to.incrementMoney(-itemPrice*count);
			
			if(buyback && to.isPlayer()) {
				Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon(weapon), count, false, true);
				Main.game.getPlayer().getBuybackStack().get(buyBackIndex).incrementCount(-count);
				if(Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<=0) {	
					Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
				}
				
			} else {
				if(from.isPlayer()) {
					Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(weapon, itemPrice, count));
				} else {
					to.addWeapon(Main.game.getItemGen().generateWeapon(weapon), count, false, true);
				}
				from.removeWeapon(weapon, count);
			}
			
			if(from.isPlayer()) {
				Main.game.addEvent(new EventLogEntry("已出售",
								count+"x <span style='color:"+weapon.getRarity().getColour().toWebHexString()+";'>"+(count==1?weapon.getName():weapon.getNamePlural())+"</span>价格："+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(to.isPlayer()) {
				//((NPC) from).handleSellingEffects(weapon, count, itemPrice); // Delete: This was replaced by applyItemTransactionEffects
				Main.game.addEvent(new EventLogEntry("已买入",
								count+"x <span style='color:"+weapon.getRarity().getColour().toWebHexString()+";'>"+(count==1?weapon.getName():weapon.getNamePlural())+"</span>价格："+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(!from.isPlayer()) {
				((NPC)from).applyItemTransactionEffects(weapon, count, itemPrice, true);
				
			} else {
				((NPC)to).applyItemTransactionEffects(weapon, count, itemPrice, false);
			}
		}
		
		
		resetPostAction();
	}
	
	
	// Clothing:
	
	private static void transferClothing(GameCharacter from, GameCharacter to, AbstractClothing clothing, int count) {
		if (!to.isInventoryFull() || to.hasClothing(clothing) || clothing.getRarity()==Rarity.QUEST) {
			from.removeClothing(clothing, count);
			to.addClothing(clothing, count, false, to.isPlayer());
			owner = to;
		}
		resetPostAction();
	}
	
	
	private static void dropClothing(GameCharacter from, AbstractClothing clothing, int count) {
		if (!Main.game.getPlayerCell().getInventory().isInventoryFull() || Main.game.getPlayerCell().getInventory().hasClothing(clothing)) {
			from.dropClothing(clothing, count, from.isPlayer());
			
			if(from.getClothingCount(clothing) == 0) {
				owner = null;
			}
		}
		resetPostAction();
	}
	
	private static void pickUpClothing(GameCharacter to, AbstractClothing clothing, int count) {
		if (!to.isInventoryFull() || to.hasClothing(clothing) || clothing.getRarity()==Rarity.QUEST) {
			to.addClothing(clothing, count, true, to.isPlayer());
			
			owner = to;
		}
		resetPostAction();
	}
	
	private static void sellClothing(GameCharacter from, GameCharacter to, AbstractClothing clothing, int count, int itemPrice) {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.removeTraderDescription, false);
		if (!to.isPlayer() || !to.isInventoryFull() || to.hasClothing(clothing) || clothing.getRarity()==Rarity.QUEST) {
			from.incrementMoney(itemPrice*count);
			to.incrementMoney(-itemPrice*count);
			
			if(buyback && to.isPlayer()) {
				Main.game.getPlayer().addClothing(new AbstractClothing(clothing) {}, count, false, true);
				Main.game.getPlayer().getBuybackStack().get(buyBackIndex).incrementCount(-count);
				if(Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<=0) {	
					Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
				}
				
			} else {
				if(from.isPlayer()) {
					Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(clothing, itemPrice, count));
				} else {
					to.addClothing(new AbstractClothing(clothing) {}, count, false, true);
				}
				from.removeClothing(clothing, count);
			}
			
			if(from.isPlayer()) {
				Main.game.addEvent(new EventLogEntry("已出售",
								count+"x <span style='color:"+clothing.getRarity().getColour().toWebHexString()+";'>"+(count==1?clothing.getName():clothing.getNamePlural())+"</span>价格："+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(to.isPlayer()) {
				//((NPC) from).handleSellingEffects(clothing, count, itemPrice); // Delete: This was replaced by applyItemTransactionEffects
				Main.game.addEvent(new EventLogEntry("已买入",
								count+"x <span style='color:"+clothing.getRarity().getColour().toWebHexString()+";'>"+(count==1?clothing.getName():clothing.getNamePlural())+"</span>价格："+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(!from.isPlayer()) {
				((NPC)from).applyItemTransactionEffects(clothing, count, itemPrice, true);
				
			} else {
				((NPC)to).applyItemTransactionEffects(clothing, count, itemPrice, false);
			}
		}
		
		resetPostAction();
	}
	
	private static String unequipClothingToFloor(GameCharacter unequipper, AbstractClothing clothing) {
		String unequipDescription = "";
		if(clothing.isDiscardedOnUnequip(clothing.getSlotEquippedTo())) {
			unequipDescription = owner.unequipClothingIntoVoid(clothing, true, unequipper);
		} else {
			unequipDescription = owner.unequipClothingOntoFloor(clothing, true, unequipper);
		}
		owner = null;
		resetPostAction();
		
		return unequipDescription;
	}
	
	private static String unequipClothingToUnequippersInventory(GameCharacter unequipper, AbstractClothing clothing) {
		String unequipDescription = "";
		if(clothing.isDiscardedOnUnequip(clothing.getSlotEquippedTo())) {
			unequipDescription = owner.unequipClothingIntoVoid(clothing, true, unequipper);
		} else {
			unequipDescription = owner.unequipClothingIntoUnequippersInventory(clothing, true, unequipper);
		}
		resetPostAction();
		
		return unequipDescription;
	}
	
	private static String unequipClothingToInventory(GameCharacter unequipper, AbstractClothing clothing) {
		String unequipDescription = "";
		if(clothing.isDiscardedOnUnequip(clothing.getSlotEquippedTo())) {
			unequipDescription = owner.unequipClothingIntoVoid(clothing, true, unequipper);
		} else {
			unequipDescription = owner.unequipClothingIntoInventory(clothing, true, unequipper);
		}
		resetPostAction();
		
		return unequipDescription;
	}
	
	private static String equipClothingFromInventory(GameCharacter to, InventorySlot slot, GameCharacter equipper, AbstractClothing clothing) {
		String equipDescription = to.equipClothingFromInventory(clothing, slot, true, equipper, owner);
		owner = to;
		resetPostAction();
		return equipDescription;
	}
	
	private static String equipClothingFromGround(GameCharacter to, InventorySlot slot, GameCharacter equipper, AbstractClothing clothing) {
		owner = to;
		resetPostAction();
		return to.equipClothingFromGround(clothing, slot, true, equipper);
	}
	
	private static Response getCondomSabotageResponse(AbstractClothing clothing) {
		if(clothing.getCondomEffect().getPotency().isNegative()) {
			if(Main.game.getPlayer().getEssenceCount() >= 1) {
				return new Response("修复([style.italicsArcane(1精华)])",
						"花费1奥术精华来修复避孕套。", CLOTHING_INVENTORY) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementEssenceCount(-1, false);
						Main.game.getTextEndStringBuilder().append(
								"<p>"
									+ "你引导奥术精华的能量进入了避孕套，一阵淡紫色的光芒后，它被修复了！"
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ "修复避孕套消耗了你[style.boldBad(1)][style.boldArcane(奥术精华)]！"
								+ "</p>");
						AbstractClothing c = (AbstractClothing) EnchantmentDialogue.craftAndApplyFullInventoryEffects(clothing, clothing.getClothingType().getEffects());

						Main.game.getPlayer().removeClothing(c);
						c.setName(c.getClothingType().getName());
						setClothing(c);
						Main.game.getPlayer().addClothing(c, false);
						
						RenderingEngine.setPage(Main.game.getPlayer(), c);
					}
				};
			} else {
				return new Response("修复(<i>1精华</i>)", "你需要至少1奥术精华来修复避孕套！", null);
			}
			
		} else {
			return new Response("破坏", "通过在避孕套顶端制造一点破损，你能够保证高潮时避孕套裂开！", CLOTHING_INVENTORY) {
				@Override
				public void effects(){
					EnchantmentDialogue.setOutputName(clothing.getClothingType().getName());
					AbstractClothing c = (AbstractClothing) EnchantmentDialogue.craftAndApplyFullInventoryEffects(clothing,
							Util.newArrayListOfValues(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_CONDOM, TFModifier.ARCANE_BOOST, TFPotency.MAJOR_DRAIN, 0)),
							false);

					Main.game.getPlayer().removeClothing(c);
					setClothing(c);
					Main.game.getPlayer().addClothing(c, false);

					RenderingEngine.setPage(Main.game.getPlayer(), c);
					Main.game.getTextEndStringBuilder().append(
							"<p>"
								+ "通过在避孕套顶端制造一个几乎不可见的微笑破损，你确信充满精液时避孕套会裂开……"
							+ "</p>"
							+ "<p style='text-align:center;'>"
								+ "[style.italicsBad(这个避孕套一定会在高潮时破裂)]！"
							+ "</p>");
				}
			};
		}
	}
	
	private static void resetPostAction() {
		Main.game.setResponseTab(0);
		resetItems();
	}
	
	public static void resetItems() {
		item = null;
		clothing = null;
		weapon = null;
	}

	public static AbstractItem getItem() {
		return item;
	}

	public static void setItem(AbstractItem item) {
		resetItems();
		InventoryDialogue.item = item;
		if(Main.getProperties().addItemDiscovered(item.getItemType())) {
			Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(item.getItemType().getName(false), item.getRarity().getColour()), true);
		}
	}

	public static AbstractWeapon getWeapon() {
		return weapon;
	}

	public static void setWeapon(InventorySlot slot, AbstractWeapon weapon) {
		resetItems();
		InventoryDialogue.weaponSlot = slot;
		InventoryDialogue.weapon = weapon;
		if (Main.getProperties().addWeaponDiscovered(weapon.getWeaponType())) {
			Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(weapon.getWeaponType().getName(), weapon.getWeaponType().getRarity().getColour()), true);
		}
	}

	public static AbstractClothing getClothing() {
		return clothing;
	}

	public static void setClothing(AbstractClothing clothing) {
		resetItems();
		InventoryDialogue.clothing = clothing;
		if(Main.getProperties().addClothingDiscovered(clothing.getClothingType())) {
			Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(clothing.getClothingType().getName(), clothing.getClothingType().getRarity().getColour()), true);
		}
	}

	public static boolean isBuyback() {
		return buyback;
	}

	public static void setBuyback(boolean buyback) {
		InventoryDialogue.buyback = buyback;
	}

	public static int getBuyBackPrice() {
		return buyBackPrice;
	}

	public static void setBuyBackPrice(int buyBackPrice) {
		InventoryDialogue.buyBackPrice = buyBackPrice;
	}

	public static int getBuyBackIndex() {
		return buyBackIndex;
	}

	public static void setBuyBackIndex(int buyBackIndex) {
		InventoryDialogue.buyBackIndex = buyBackIndex;
	}

	public static GameCharacter getOwner() {
		return owner;
	}

	public static void setOwner(GameCharacter owner) {
		InventoryDialogue.owner = owner;
	}

	public static NPC getInventoryNPC() {
		return inventoryNPC;
	}

	public static void setInventoryNPC(NPC inventoryNPC) {
		InventoryDialogue.inventoryNPC = inventoryNPC;
	}

	public static InventoryInteraction getNPCInventoryInteraction() {
		return interactionType;
	}

	public static void setNPCInventoryInteraction(InventoryInteraction npcInventoryInteraction) {
		interactionType = npcInventoryInteraction;
	}

}