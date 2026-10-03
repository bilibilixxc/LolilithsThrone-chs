package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.LolificationProject.ModCommon.Logger;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.race.AbstractRacialBody;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.moves.CombatMove;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.enchanting.*;
import com.lilithsthrone.game.inventory.item.*;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadInventory {
    public static CharacterInventory loadInventoryFromXML(XMLLoader loader) {
        CharacterInventory inventory = new CharacterInventory(false, 0);
        if (loader.debugMode) Logger.logTest("Started Loading Inventory");
        loader.moveToNode("characterInventory");

//		if(parentElement.getElementsByTagName("maxInventorySpace").item(0)!=null) {
//			inventory.setMaximumInventorySpace(Integer.valueOf(((Element)parentElement.getElementsByTagName("maxInventorySpace").item(0)).getAttribute("value")));
//		}

        inventory.floorInventory = loader.getElementAttributeAsBoolean("floorInventory", CharacterInventory.loadingFromFloorBackupCheck);

        inventory.setMoney(loader.getElementAttributeAsLong("money"));
        inventory.setEssenceCount(loader.getElementAttributeAsInt("essences", loader.getElementAttributeAsInt("essenceCount")));

        if (loader.moveToIfHasChildren("extraBlockedParts")) {
            Logger.logTest("This function is completely blind, as I didn't have a save that had this data. Please report this with the save that triggered this.");
//            inventory.setExtraBlockedParts(LoadBlockedParts.loadBlocked(loader, "LoadInventory CharacterInventory extraBlockedParts"));
            loader.goToParent();
        }

        if (loader.moveToIfHasChildren("dirtySlots")) {
            loader.forEachAttributeOfElement("dirtySlot", "slot", slotID -> {
                inventory.addDirtySlot(InventorySlot.valueOf(slotID));
            });
            loader.goToParent();
        }
        if (loader.moveToIfHasChildren("wetSlots")) {
            loader.forEachAttributeOfElement("wetSlot", "slot", slotID -> {
                inventory.addWetSlot(InventorySlot.valueOf(slotID));
            });
            loader.goToParent();
        }

        if (loader.moveToIfHasChildren("unlockKeyMap")) {
            loader.forEachAttributeOfElement("character", "id", charId -> {
                List<InventorySlot> loadedSlots = new ArrayList<>();
                loader.forEachAttributeOfElement("slot", "id", slotId -> {
                    loadedSlots.add(InventorySlot.valueOf(slotId));
                });
                inventory.unlockKeyMap.put(charId, loadedSlots);
            });
            loader.goToParent();
        }

        if (loader.moveToIfHasChildren("mainWeapon")) {
            if (loader.moveToIfHasChildren("weapon")) {
                AbstractWeapon weapon = loadWeapon(loader);
                inventory.equipMainWeapon(0, weapon);
                loader.goToParent();
            }
            loader.goToParent();
        } else {
            for (int i = 0; i < inventory.mainWeapon.length; i++) {
                if (loader.moveToIfHasChildren("mainWeapon" + i)) {
                    if (loader.moveToIfHasChildren("weapon")) {
                        AbstractWeapon weapon = loadWeapon(loader);
                        inventory.equipMainWeapon(i, weapon);
                        loader.goToParent();
                    }
                    loader.goToParent();
                }
            }
        }
        if (loader.moveToIfHasChildren("offhandWeapon")) {
            if (loader.moveToIfHasChildren("weapon")) {
                AbstractWeapon weapon = loadWeapon(loader);
                inventory.equipOffhandWeapon(0, weapon);
                loader.goToParent();
            }
            loader.goToParent();
        } else {
            for (int i = 0; i < inventory.mainWeapon.length; i++) {
                if (loader.moveToIfHasChildren("offhandWeapon" + i)) {
                    if (loader.moveToIfHasChildren("weapon")) {
                        AbstractWeapon weapon = loadWeapon(loader);
                        inventory.equipOffhandWeapon(i, weapon);
                        loader.goToParent();
                    }
                    loader.goToParent();
                }
            }
        }

        if (loader.moveToIfHasChildren("clothingEquipped")) {
            loader.forEachAttributeOfElement("clothing", "id", clothingId -> {
                AbstractClothing clothing = loadClothing(loader);
                inventory.getClothingCurrentlyEquipped().add(clothing);
                if (clothing.getSlotEquippedTo() == null) {
                    clothing.setSlotEquippedTo(clothing.getClothingType().getEquipSlots().get(0));
                }
            });
            loader.goToParent();
        }

        if (loader.moveToIfHasChildren("itemsInInventory")) {
            Map<AbstractItem, Integer> itemMapToAdd = new HashMap<>();
            loader.forEachAttributeOfElement("item", "id", itemId -> {
                int count = loader.getCurrentElementAttributeAsInt("count");

                if(itemId.equals("GIFT_ROSE")) { // Changed the rose to a clothing item in v0.3.5.5
                    inventory.addClothing(Main.game.getItemGen().generateClothing("innoxia_hair_rose", PresetColour.CLOTHING_RED_DARK, PresetColour.CLOTHING_GREEN_DARK, null, false), count);
                } else if(itemId.equals(ItemType.getIdFromItemType(ItemType.CONDOM_USED)) || itemId.equals(ItemType.getIdFromItemType(ItemType.CONDOM_USED_WEBBING))) {
                    itemMapToAdd.put(loadFilledCondom(loader), count);
                } else if(itemId.equals(ItemType.getIdFromItemType(ItemType.MOO_MILKER_FULL))) {
                    itemMapToAdd.put(loadFilledBreastPump(loader), count);
                } else {
                    AbstractItem item = loadItem(loader);
                    if (item != null) itemMapToAdd.put(item, count);
                }
            });
            inventory.addItems(itemMapToAdd);
            loader.goToParent();
        }

        if (loader.moveToIfHasChildren("clothingInInventory")) {
            loader.forEachAttributeIntOfElement("clothing", "count", count -> {
                AbstractClothing clothing = loadClothing(loader);
                inventory.addClothing(clothing, count);
            });
            loader.goToParent();
        }

        if (loader.moveToIfHasChildren("weaponsInInventory")) {
            loader.forEachAttributeIntOfElement("weapon", "count", count -> {
                AbstractWeapon weapon = loadWeapon(loader);
                inventory.addWeapon(weapon, count);
            });
            loader.goToParent();
        }
        loader.goToParent();
        if (loader.debugMode) Logger.logTest("Finished Loading Inventory");
        return inventory;
    }

    private static AbstractItem loadFilledBreastPump(XMLLoader loader) {
        String provider = loader.getCurrentElementAttributeAsStr("milkProvider", loader.getCurrentElementAttributeAsStr("milkProvidor"));
        return new AbstractFilledBreastPump(
                ItemType.getIdToItemMap().get(loader.getCurrentElementAttributeAsStr("id")),
                PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("colour")),
                provider,
                FluidLoaders.loadMilk(loader, "milk", null, false),
                loader.getCurrentElementAttributeAsInt("millilitresStored", 25));

    }

    private static AbstractItem loadFilledCondom(XMLLoader loader) {
        String provider = loader.getCurrentElementAttributeAsStr("cumProvider", loader.getCurrentElementAttributeAsStr("cumProvidor"));
        FluidStored fs = null;
        if(loader.moveToIfHasChildren("fluidStored")) {
            fs = LoadFluidStored.loadFluidStored(loader, provider, null);
            loader.goToParent();
        } else { // Old version support to generate Body based on cum type:
            FluidCum fluidCum = FluidLoaders.loadCum(loader, "cum", null);
            AbstractRacialBody racialBody = fluidCum.getType().getRace().getRacialBody();
            fs = new FluidStored(provider, Main.game.getCharacterUtils().generateBody(null, Gender.M_P_MALE, racialBody, RaceStage.GREATER), fluidCum, loader.getCurrentElementAttributeAsInt("millilitresStored", 25));
        }

        return new AbstractFilledCondom(
                ItemType.getIdToItemMap().get(loader.getCurrentElementAttributeAsStr("id")),
                PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("colour")),
                fs);
    }

    public static AbstractItem loadItem(XMLLoader loader) {
        String itemID = loader.getCurrentElementAttributeAsStr("id");
        if (loader.debugMode) Logger.logTest("Started Loading Item " + itemID);
        String itemName = loader.getCurrentElementAttributeAsStr("name");
        AbstractItemType it = ItemType.getItemTypeFromId(itemID);
        if(it == null) {
            Logger.logTest("Warning: An instance of AbstractItem was unable to be imported, due to AbstractItemType not existing. (" + itemID + ")");
            return null;
        }
        AbstractItem item = Main.game.getItemGen().generateItem(it);

        if(!itemName.isEmpty()) item.setName(itemName);

        if (loader.moveToNode("overrideSpecialEffects")) {
            item.overrideSpecialEffects = loader.getCurrentElementText();
            loader.goToParent();
        }

        List<ItemEffect> effectsToBeAdded = new ArrayList<>();
        if (loader.moveToIfHasChildren("itemEffects")) {
            loader.forEachAttributeOfElement("effect", "type", effType -> {
                ItemEffect eff = LoadItemEffect.loadEffect(loader, effType);
                effectsToBeAdded.add(eff);
            });

            item.setItemEffects(effectsToBeAdded);
            loader.goToParent();
        }
        item.setColour(0, PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("colour", PresetColour.GENERIC_ARCANE.getId())));

        if(!effectsToBeAdded.isEmpty()
                && (item.getItemType().getId().equals(ItemType.ELIXIR.getId()) || item.getItemType().getId().equals(ItemType.POTION.getId()) || item.getItemType().getId().equals(ItemType.ORIENTATION_HYPNO_WATCH.getId()))) {
            item.setSVGString(EnchantingUtils.getImportedSVGString(item, item.getColour(0), effectsToBeAdded));
        }
        if (loader.debugMode) Logger.logTest("Finished Loading Item " + itemID);
        return item;
    }

    public static AbstractWeapon loadWeapon(XMLLoader loader) {
        AbstractWeapon weapon;
        String weaponId = loader.getCurrentElementAttributeAsStr("id");
        if (loader.debugMode) Logger.logTest("Started Loading Weapon " + weaponId);
        String weaponName = loader.getCurrentElementAttributeAsStr("name");
        String damageType = loader.getCurrentElementAttributeAsStr("damageType");
        String coreEnchant = loader.getCurrentElementAttributeAsStr("coreEnchantment");
        if (!weaponId.isEmpty()) {
            weapon = Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId(weaponId), DamageType.valueOf(damageType));
        } else {
            weapon = null;
            Logger.logTest("Warning: An instance of AbstractWeapon was unable to be imported. (" + weaponId + ")");
            return null;
        }

        if (!weaponName.isEmpty()) weapon.setName(weaponName);
        if (!coreEnchant.isEmpty() && !coreEnchant.equalsIgnoreCase("null"))
            weapon.coreEnchantment = Attribute.getAttributeFromId(coreEnchant);

        // Try to load colour:
        if(!Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.8")) {
            if (loader.moveToIfHasChildren("colours")) {
                loader.forEachAttributeIntOfElement("colour", "i", colorIndex -> {
                    Colour col = PresetColour.getColourFromId(loader.getCurrentElementText());
                    weapon.setColour(colorIndex, col);
                });
                loader.goToParent();
            }
        } else {
            if(!Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.4") || !weapon.getWeaponType().equals(WeaponType.getWeaponTypeFromId("innoxia_bow_shortbow"))){
                String colPrim = loader.getCurrentElementAttributeAsStr("colourPrimary");
                String colSec = loader.getCurrentElementAttributeAsStr("colourSecondary");
                String colTer = loader.getCurrentElementAttributeAsStr("colourTertiary");
                if(!colPrim.isEmpty())
                    weapon.setColour(0, PresetColour.getColourFromId(colPrim));
                if(!colSec.isEmpty())
                    weapon.setColour(1, PresetColour.getColourFromId(colSec));
                if(!colTer.isEmpty())
                    weapon.setColour(2, PresetColour.getColourFromId(colTer));
            }
        }

        if(!Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
            weapon.getEffects().clear();
            if (loader.moveToIfHasChildren("effects")) {
                loader.forEachAttributeOfElement("effect", "type", effectType -> {
                    ItemEffect eff = LoadItemEffect.loadEffect(loader, effectType);
                    weapon.addEffect(eff);
                });
                loader.goToParent();
            }
        }

        weapon.spells = new ArrayList<>();
        if (loader.moveToIfHasChildren("spells")) {
            loader.forEachAttributeOfElement("spell", "value", spellVal -> {
                if(spellVal.equals("DARK_SIREN_BANEFUL_FISSURE")) {
                    spellVal = "DARK_SIREN_SIRENS_CALL";
                }

                weapon.spells.add(Spell.valueOf(spellVal));
            });
            loader.goToParent();
        }

        weapon.combatMoves = new ArrayList<>();
        if (loader.moveToIfHasChildren("combatMoves")) {
            loader.forEachAttributeOfElement("move", "value", moveVal -> {
                weapon.combatMoves.add(CombatMove.getCombatMoveFromId(moveVal));
            });
            loader.goToParent();
        }

        if (loader.debugMode) Logger.logTest("Finished Loading Weapon " + weaponId);
        return weapon;
    }

    public static AbstractClothing loadClothing(XMLLoader loader) {
        AbstractClothing clothing = null;
        String slotHint = null;
        slotHint = loader.getCurrentElementAttributeAsStr("slotEquippedTo");
        String loadedId = loader.getCurrentElementAttributeAsStr("id");
        if (loader.debugMode) Logger.logTest("Loading Clothing " + loadedId);
        boolean sealed = loader.getCurrentElementAttributeAsBoolean("sealed");
        boolean isDirty = loader.getCurrentElementAttributeAsBoolean("isDirty");
        boolean isWet = loader.getCurrentElementAttributeAsBoolean("isWet");
        boolean enchantmentKnown = loader.getCurrentElementAttributeAsBoolean("enchantmentKnown");
        String hiddenName = loader.getCurrentElementAttributeAsStr("hiddenName");


        // Handle old Enforcer clothing ids (pre-sticker update):
        if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.6")) {
            // Berets:
            if(loadedId.equals("dsg_eep_servequipset_enfberet")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfberet", false);
                clothing.setSticker("flash", "flash_patrol_dominion");
                return clothing;
            } else if(loadedId.equals("dsg_eep_servequipset_enfberet_academy")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfberet", false);
                clothing.setSticker("flash", "flash_academy");
                return clothing;
            } else if(loadedId.equals("dsg_eep_servequipset_enfberet_oricl")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfberet", false);
                clothing.setSticker("flash", "flash_oricl");
                return clothing;
            } else if(loadedId.equals("dsg_eep_servequipset_enfberet_sword")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfberet", false);
                clothing.setSticker("flash", "flash_sword");
                return clothing;
            }

            // Hats:
            if(loadedId.equals("dsg_eep_ptrlequipset_bwhat")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_bwhat", false);
                clothing.setSticker("badge", "badge_dominion");
                return clothing;
            } else if(loadedId.equals("dsg_eep_ptrlequipset_pcap")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_pcap", false);
                clothing.setSticker("badge", "badge_dominion");
                return clothing;
            }

            // Jackets:
            if(loadedId.equals("dsg_eep_servequipset_enfdjacket_cs")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", false);
                clothing.setSticker("collar", "tab_cs");
                clothing.setSticker("name", "name_cs");
                return clothing;
            } else if(loadedId.equals("dsg_eep_servequipset_enfdjacket_ip")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", false);
                clothing.setSticker("collar", "tab_ip");
                clothing.setSticker("name", "name_ip");
                return clothing;
            } else if(loadedId.equals("dsg_eep_servequipset_enfdjacket_pc")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", false);
                clothing.setSticker("collar", "tab_pc");
                clothing.setSticker("name", "name_pc");
                return clothing;
            } else if(loadedId.equals("dsg_eep_servequipset_enfdjacket_sg")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", false);
                clothing.setSticker("collar", "tab_sg");
                clothing.setSticker("name", "name_sg");
                return clothing;
            } else if(loadedId.equals("dsg_eep_servequipset_enfdjacket_su")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", false);
                clothing.setSticker("collar", "tab_su");
                clothing.setSticker("name", "name_su");
                return clothing;
            }

            // Unique jackets:
            if(loadedId.equals("dsg_eep_uniques_enfdjacket_brax")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLUE, null, false);
                clothing.setSticker("collar", "tab_ip");
                clothing.setSticker("name", "name_brax");
                clothing.setSticker("ribbon", "ribbon_brax");
                return clothing;
            } else if(loadedId.equals("dsg_eep_uniques_enfdjacket_candi")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_PINK, null, false);
                clothing.setSticker("collar", "tab_pc");
                clothing.setSticker("name", "name_candi");
                clothing.setSticker("ribbon", "ribbon_candi");
                return clothing;
            } else if(loadedId.equals("dsg_eep_uniques_enfdjacket_claire")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_PINK, null, false);
                clothing.setSticker("collar", "tab_sg");
                clothing.setSticker("name", "name_claire");
                clothing.setSticker("ribbon", "ribbon_claire");
                return clothing;
            } else if(loadedId.equals("dsg_eep_uniques_enfdjacket_elle")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_PINK, null, false);
                clothing.setSticker("collar", "tab_su");
                clothing.setSticker("name", "name_elle");
                clothing.setSticker("ribbon", "ribbon_elle");
                clothing.setSticker("qual", "qual_flyer");
                return clothing;
            } else if(loadedId.equals("dsg_eep_uniques_enfdjacket_wesley")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLUE, null, false);
                clothing.setSticker("collar", "tab_ip");
                clothing.setSticker("name", "name_wesley");
                clothing.setSticker("ribbon", "ribbon_wes");
                return clothing;
            } else if(loadedId.equals("dsg_eep_uniques_enfdjacket_wesley_su")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLUE, null, false);
                clothing.setSticker("collar", "tab_su");
                clothing.setSticker("name", "name_wesley");
                clothing.setSticker("ribbon", "ribbon_wes");
                return clothing;
            } else if(loadedId.equals("dsg_eep_uniques_stpvest_claire")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_stpvest", false);
                clothing.setSticker("name_plate", "claire");
                return clothing;
            }

            // Waistcoats:
            if(loadedId.equals("dsg_eep_servequipset_enfdwaistcoat_cs")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdwaistcoat", false);
                clothing.setSticker("collar", "tab_cs");
                clothing.setSticker("name", "name_cs");
                return clothing;
            } else if(loadedId.equals("dsg_eep_servequipset_enfdwaistcoat_ip")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdwaistcoat", false);
                clothing.setSticker("collar", "tab_ip");
                clothing.setSticker("name", "name_ip");
                return clothing;
            } else if(loadedId.equals("dsg_eep_servequipset_enfdwaistcoat_pc")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdwaistcoat", false);
                clothing.setSticker("collar", "tab_pc");
                clothing.setSticker("name", "name_pc");
                return clothing;
            } else if(loadedId.equals("dsg_eep_servequipset_enfdwaistcoat_sg")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdwaistcoat", false);
                clothing.setSticker("collar", "tab_sg");
                clothing.setSticker("name", "name_sg");
                return clothing;
            } else if(loadedId.equals("dsg_eep_servequipset_enfdwaistcoat_su")) {
                clothing = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdwaistcoat", false);
                clothing.setSticker("collar", "tab_su");
                clothing.setSticker("name", "name_su");
                return clothing;
            }
        }

        clothing = Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId(loadedId, slotHint), false);

        if(clothing == null) {
            Logger.logTest("Warning: An instance of AbstractClothing was unable to be imported. (" + loadedId + ")");
            return null;
        }

        if(!Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.6") || clothing.getClothingType().getStickers().isEmpty()) { // Reset name at version 0.3.9.6 for clothing which has had sticker support added
            clothing.setName(loader.getCurrentElementAttributeAsStr("name"));
        }

        // Reset name if loading from prior to 0.4.10.10 for when the default 'filly' choker was changed to 'mule':
        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.10.10") && clothing.getClothingType()==ClothingType.getClothingTypeFromId("innoxia_neck_filly_choker")) {
            clothing.setName(clothing.getClothingType().getName());
        }
        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.10.10") && clothing.getClothingType()==ClothingType.getClothingTypeFromId("innoxia_latex_hood")) {
            clothing.setSticker("Mouth hole", "present");
            clothing.setSticker("Eye holes", "present");
        }

        if(!loader.getCurrentElementAttributeAsStr("slotEquippedTo").isEmpty()) {
            InventorySlot slot = InventorySlot.valueOf(loader.getCurrentElementAttributeAsStr("slotEquippedTo"));
            if(!clothing.getClothingType().getEquipSlots().contains(slot)) {
                return null; // If the clothing type doesn't support this slot, then something has gone wrong and the clothing should not be laoded.
            }
            clothing.setSlotEquippedTo(slot);
        }


        // Try to load colours:
        if(!Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.8")) {
            boolean applySecondaryLoad = true;
            boolean applyTertiaryLoad = true;
            if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.2.6")) {
                if(clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_torsoOver_womens_leather_jacket"))
                        || clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_stomach_overbust_corset"))
                        || clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_stomach_underbust_corset"))) {
                    applySecondaryLoad = false;
                    applyTertiaryLoad = false;
                }
            }

            boolean swapPrimaryAndSecondary;
            if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.13") && loadedId.equals("norin_piercings_heart_barbells")) {
                swapPrimaryAndSecondary = true;
            } else {
                swapPrimaryAndSecondary = false;
            }

            if(loader.moveToIfHasChildren("colours")) {
                AbstractClothing finalClothing = clothing;
                loader.forEachAttributeIntOfElement("colour", "i", index -> {
                    Colour pcol = PresetColour.getColourFromId(loader.getCurrentElementText());
                    int colourIndex = index;
                    if(swapPrimaryAndSecondary) {
                        if(colourIndex==0) {
                            colourIndex = 1;
                        } else if(colourIndex==1) {
                            colourIndex = 0;
                        }
                    }
                    finalClothing.setColour(colourIndex, pcol);
                });
                clothing = finalClothing;
                loader.goToParent();
            }
        } else if((!Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.4") || !clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_scientist_safety_goggles")))
                && !clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_rainbow_gloves"))
                && !clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_rainbow_stockings"))) {

            if((clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("BDSM_CHOKER")) && Main.isVersionOlderThan(Game.loadingVersion, "0.2.12.6"))
                    || (clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_ankle_shin_guards")) && Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.6"))
                    || (clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("FOOT_TRAINERS")) && Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.2"))
                    || (clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_sock_toeless_striped_stockings")) && Main.isVersionOlderThan(Game.loadingVersion, "0.3.2"))) {
                clothing.setColour(0, PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("colourSecondary", "CLOTHING_WHITE")));
                clothing.setColour(1, PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("colour", "CLOTHING_WHITE")));
            } else if(clothing.getClothingType().equals(ClothingType.getClothingTypeFromId("FOOT_LOW_TOP_SKATER_SHOES")) && Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.2")){
                clothing.setColour(0, PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("colour", "DEFAULT_COLOUR_VALUE")));
                clothing.setColour(1, PresetColour.CLOTHING_WHITE);
            } else {
                clothing.setColour(0, PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("colour", "DEFAULT_COLOUR_VALUE")));
                clothing.setColour(1, PresetColour.CLOTHING_WHITE);

                Colour secColour = PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("colourSecondary", AbstractClothingType.DEFAULT_COLOUR_VALUE.getId()));
                if(clothing.getClothingType().getPatternColourReplacement(1)!=null && clothing.getClothingType().getPatternColourReplacement(1).getAllColours().contains(secColour)) {
                    clothing.setColour(1, secColour);
                }
            }
            Colour terColour = PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("colourTertiary", AbstractClothingType.DEFAULT_COLOUR_VALUE.getId()));
            if(clothing.getClothingType().getPatternColourReplacement(2)!=null && clothing.getClothingType().getPatternColourReplacement(2).getAllColours().contains(terColour)) {
                clothing.setColour(2, terColour);
            }
        }

        // Try to load patterns:
        if(!Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.8")) {
            String patternId = loader.getNextElementAttributeAsStr("pattern", "id");
            if (!patternId.isEmpty()) {
                AbstractClothing finalClothing1 = clothing;
                if (Pattern.getPattern(patternId) == null) {
                    patternId = Pattern.getPatternIdByName(patternId);
                }
                clothing.setPattern(patternId);
                AtomicInteger lastIndex = new AtomicInteger(0);
                if (loader.moveToIfHasChildren("colours")) {
                    loader.forEachAttributeIntOfElement("colour", "i", index -> {
                        lastIndex.set(index);
                        Colour pcol = PresetColour.getColourFromId(loader.getCurrentElementText());
                        finalClothing1.setPatternColour(index, pcol);
                    });
                    loader.goToParent();
                }
                // If any pattern colours are missing (i.e. were not saved due to the current pattern not needing them), then fill the missing colours with the first of the defaults
                // This prevents the issue where multiple clothing with apparently the same colour patterning wouldn't stack due to a 'hidden' pattern colour being different in each item of clothing
                int defaultPatternReplacementSize = finalClothing1.getClothingType().getPatternColourReplacements().size();
                for (int i = lastIndex.get(); i < defaultPatternReplacementSize; i++) {
                    ColourReplacement cr = finalClothing1.getClothingType().getPatternColourReplacement(i);
                    if (cr.getAllColours().isEmpty() || !Pattern.getPattern(finalClothing1.getPattern()).isRecolourAvailable(cr)) {
                        finalClothing1.setPatternColour(i, cr.getFirstOfDefaultColours());
                    }
                }
                loader.goToParent();
                clothing = finalClothing1;
            } else {
                clothing.setPattern("none");
            }
        } else {
            String patternId = loader.getCurrentElementAttributeAsStr("pattern", "none");
            if(Pattern.getPattern(patternId) == null) {
                patternId = Pattern.getPatternIdByName(patternId);
            }
            clothing.setPattern(patternId);
            Colour colour = PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("patternColour", AbstractClothingType.DEFAULT_COLOUR_VALUE.getId()));
            Colour secColour = PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("patternColourSecondary", AbstractClothingType.DEFAULT_COLOUR_VALUE.getId()));
            Colour terColour = PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("patternColourTertiary",
                    AbstractClothingType.DEFAULT_COLOUR_VALUE.getId()));
            clothing.setPatternColour(0, colour);
            clothing.setPatternColour(1, secColour);
            clothing.setPatternColour(2, terColour);
        }

        boolean handleHoodStickers = Main.isVersionOlderThan(Game.loadingVersion, "0.4.10.12") && clothing.getClothingType()==ClothingType.getClothingTypeFromId("innoxia_latex_hood");
        // Load stickers:
        AbstractClothing finalClothing2 = clothing;
        if (loader.moveToIfHasChildren("stickers")){
            loader.forEachAttributeOfElement("sticker", "category", stickerCat -> {
                String categoryIDString = loader.getCurrentElementText();
                if (handleHoodStickers) {
                    stickerCat = stickerCat.replace("mouth hole", "mouth");
                    stickerCat = stickerCat.replace("eye holes", "eyes");
                }
                finalClothing2.setSticker(stickerCat, categoryIDString);
            });
            loader.goToParent();
        }
        clothing = finalClothing2;
        // Try to load core features:
        clothing.setSealed(sealed);
        clothing.setDirty(null, isDirty);
        clothing.setWet(null, isWet);
        clothing.setEnchantmentKnown(null, enchantmentKnown);
        clothing.setHiddenName(hiddenName);

        // Try to load attributes:
        if(!Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.5") || !clothing.isCondom(clothing.getClothingType().getEquipSlots().get(0))) { // Do not load condom effects from versions prior to 0.3.0.5
            AbstractClothing finalClothing5 = clothing;
            if (loader.moveToIfHasChildren("attributeModifiers")) {
                if(clothing.getClothingType().getClothingSet() == null) {
                    clothing.getEffects().clear();
                    loader.forEachAttributeOfElement("modifier", "attribute", attModId -> {
                        AbstractAttribute att = Attribute.getAttributeFromId(attModId);
                        int value = loader.getCurrentElementAttributeAsInt("value");

                        TFPotency pot = TFPotency.BOOST;
                        if(value <= -5) {// TODO Fix TFPOT
                            pot = TFPotency.MAJOR_DRAIN;
                        } else if(value <= -3) {
                            pot = TFPotency.DRAIN;
                        } else if(value <= -1) {
                            pot = TFPotency.MINOR_DRAIN;
                        } else if(value <= 1) {
                            pot = TFPotency.MINOR_BOOST;
                        } else if(value <= 3) {
                            pot = TFPotency.BOOST;
                        } else {
                            pot = TFPotency.MAJOR_BOOST;
                        }

                        for(TFModifier mod : TFModifier.getClothingAttributeList()) {
                            if(mod.getAssociatedAttribute()==att) {
                                finalClothing5.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, mod, pot, 0));
                                break;
                            }
                        }

                        for(TFModifier mod : TFModifier.getClothingMajorAttributeList()) {
                            if(mod.getAssociatedAttribute()==att) {
                                finalClothing5.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, mod, pot, 0));
                                break;
                            }
                        }
                    });
                }
                clothing = finalClothing5;
                loader.goToParent();
            } else {
                clothing.getEffects().clear();

                if (loader.moveToIfHasChildren("effects")) {
                    AbstractClothing finalClothing4 = clothing;
                    if (!(loader.getElementAttributeAsStr("effect", "type")).isEmpty())
                        loader.forEachAttributeOfElement("effect", "type", effectType -> {
                            ItemEffect eff = LoadItemEffect.loadEffect(loader, effectType);
                            finalClothing4.addEffect(eff);
                        });
                    if (!(loader.getElementAttributeAsStr("effect", "itemEffectType")).isEmpty())
                        loader.forEachAttributeOfElement("effect", "itemEffectType", effectType -> {
                            ItemEffect eff = LoadItemEffect.loadEffect(loader, effectType);
                            finalClothing4.addEffect(eff);
                        });
                    loader.goToParent();
                    clothing = finalClothing4;
                }
            }
        } else {
            clothing.setEnchantmentKnown(null, true);
        }

        // Try to load displacements:
        clothing.displacedList = new ArrayList<>();
        AbstractClothing finalClothing3 = clothing;
        if(loader.moveToIfHasChildren("displacedList")) {
            loader.forEachAttributeOfElement("displaymentType", "value", displaceVal -> {
//                boolean displacementTypeFound = false;
                DisplacementType dt = DisplacementType.valueOf(displaceVal);
//                for (LoadBlockedParts bp : clothing.getBlockedPartsMap(null, clothing.getSlotEquippedTo())) {
//                    if(bp.displacementType == dt) {
//                        displacementTypeFound = true;
//                    }
//                }
//                if(displacementTypeFound) {
                    finalClothing3.getDisplacedList().add(dt);
//                } else {
//                    Logger.logTest("Warning: Invalid displacement");
//                }
            });
            loader.goToParent();
        }
        if (loader.debugMode) Logger.logTest("Finished Loading Clothing " + loadedId);
        return finalClothing3;
    }
}
