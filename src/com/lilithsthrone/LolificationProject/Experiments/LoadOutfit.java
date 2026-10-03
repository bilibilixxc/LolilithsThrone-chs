package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.outfit.Outfit;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LoadOutfit {
    public static Outfit loadFromXML(XMLLoader loader) {
        long newID = loader.getCurrentElementAttributeAsLong("gameCreationID", -1);
        String newName = loader.getCurrentElementAttributeAsStr("name", "outfit");
        String iconSlotPrio = loader.getCurrentElementAttributeAsStr("iconSlotPriority");
        InventorySlot newIconSlotPriority = null;
        Map<InventorySlot, AbstractWeapon> newWeapons = new HashMap<>();
        Map<InventorySlot, AbstractClothing> newClothing = new HashMap<>();
        Set<InventorySlot> newIgnoredSlots = new HashSet<>();

        if(!iconSlotPrio.isEmpty()) {
            newIconSlotPriority = InventorySlot.valueOf(iconSlotPrio);
        } else System.err.println("Warning: An instance of iconSlotPriority failed to load");

        if (loader.moveToIfHasChildren("weapons")) {
            loader.forEachAttributeOfElement("weaponWrapper", "slot", weaponSlot -> {
                newWeapons.put(InventorySlot.valueOf(weaponSlot), LoadInventory.loadWeapon(loader));
            });
            loader.goToParent();
        }
        if (loader.moveToIfHasChildren("clothing")) {
            loader.forEachAttributeOfElement("clothingWrapper", "slot", clothingSlot -> {
                newClothing.put(InventorySlot.valueOf(clothingSlot), LoadInventory.loadClothing(loader));
            });
            loader.goToParent();
        }
        if (loader.moveToIfHasChildren("ignoredSlots")) {
            loader.forEachTextContent("slot", slot -> {
                newIgnoredSlots.add(InventorySlot.valueOf(slot));
            });
            loader.goToParent();
        }

        Outfit newOutfit = new Outfit(newName, newWeapons, newClothing);

        newOutfit.gameCreationID = newID;
        newOutfit.ignoredSlots = newIgnoredSlots;

        if(newIconSlotPriority != null) {
            newOutfit.iconSlotPriority = newIconSlotPriority;
        }

        return newOutfit;
    }
}
