package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.BlockedParts;
import com.lilithsthrone.game.inventory.clothing.ClothingAccess;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.clothing.PresetConcealmentLists;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class LoadBlockedParts {
    public static BlockedParts loadBlocked(XMLLoader loader, String contextForErrors) {
        Document doc = null;
        Element parentElement = null;

        DisplacementType displacementType = null;
        if (loader.moveToNode("displacementType")) {
            displacementType = DisplacementType.valueOf(loader.getCurrentElementText());
            loader.goToParent();
        }

        String errorCode = "Unknown Clothing";
        Element clothingElement = ((Element) doc.getElementsByTagName("clothing").item(0));
        try { // try, as when loaded when not embedded in clothing, the "clothing" element will not be found.
            if (clothingElement.getElementsByTagName("coreAttributes").item(0) != null &&
                    ((Element) clothingElement.getElementsByTagName("coreAttributes").item(0)).getElementsByTagName("name").getLength() > 0 &&
                    ((Element) clothingElement.getElementsByTagName("coreAttributes").item(0)).getElementsByTagName("name").item(0) != null)
                errorCode = ((Element) clothingElement.getElementsByTagName("coreAttributes").item(0)).getElementsByTagName("name").item(0).getTextContent(); // Fix typo
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        List<ClothingAccess> loadedClothingAccessRequired = new ArrayList<>();
        Element clothingAccessRequiredElement = (Element)parentElement.getElementsByTagName("clothingAccessRequired").item(0);
        try {
            for(int i=0; i<clothingAccessRequiredElement.getElementsByTagName("clothingAccess").getLength(); i++){
                Element e = ((Element)clothingAccessRequiredElement.getElementsByTagName("clothingAccess").item(i));
                loadedClothingAccessRequired.add(ClothingAccess.valueOf(e.getTextContent()));
            }
        } catch(Exception ex) {
            System.err.println("BlockedParts loading failed for "+errorCode+". Code 1 (clothingAccessRequired -> clothingAccess); Context: "+contextForErrors);
            ex.printStackTrace();
            BlockedParts.printHelpfulErrorForEnumValueMismatches(ex, BlockedParts.getPossibleEnumValues());
        }

        List<CoverableArea> loadedBlockedBodyParts = new ArrayList<>();
        Element blockedBodyPartsElement = (Element)parentElement.getElementsByTagName("blockedBodyParts").item(0);
        try {
            for(int i=0; i<blockedBodyPartsElement.getElementsByTagName("bodyPart").getLength(); i++){
                Element e = ((Element)blockedBodyPartsElement.getElementsByTagName("bodyPart").item(i));
                loadedBlockedBodyParts.add(CoverableArea.of(e.getTextContent()));
            }
        } catch(Exception ex) {
            System.err.println("BlockedParts loading failed for "+errorCode+". Code 2 (blockedBodyParts -> bodyPart); Context: "+contextForErrors);
            ex.printStackTrace();
            BlockedParts.printHelpfulErrorForEnumValueMismatches(ex, BlockedParts.getPossibleEnumValues());
        }

        List<ClothingAccess> loadedClothingAccessBlocked = new ArrayList<>();
        Element clothingAccessBlockedElement = (Element)parentElement.getElementsByTagName("clothingAccessBlocked").item(0);
        try {
            for(int i=0; i<clothingAccessBlockedElement.getElementsByTagName("clothingAccess").getLength(); i++){
                Element e = ((Element)clothingAccessBlockedElement.getElementsByTagName("clothingAccess").item(i));
                loadedClothingAccessBlocked.add(ClothingAccess.valueOf(e.getTextContent()));
            }
        } catch(Exception ex) {
            System.err.println("BlockedParts loading failed for "+errorCode+". Code 3 (clothingAccessBlocked -> clothingAccess); Context: "+contextForErrors);
            ex.printStackTrace();
            BlockedParts.printHelpfulErrorForEnumValueMismatches(ex, BlockedParts.getPossibleEnumValues());
        }

        List<InventorySlot> loadedConcealedSlots = new ArrayList<>();
        Element concealedSlotsElement = (Element)parentElement.getElementsByTagName("concealedSlots").item(0);
        if(!concealedSlotsElement.getAttribute("values").isEmpty()) {
            try {
                loadedConcealedSlots = PresetConcealmentLists.valueOf(concealedSlotsElement.getAttribute("values")).getPresetInventorySlotList();
            } catch(Exception ex) {
                System.err.println("BlockedParts loading failed for "+errorCode+". Code 4a (concealedSlots.values); Context: "+contextForErrors);
                ex.printStackTrace();
                BlockedParts.printHelpfulErrorForEnumValueMismatches(ex, BlockedParts.getPossibleEnumValues());
            }
        } else {
            try {
                for(int i=0; i<concealedSlotsElement.getElementsByTagName("slot").getLength(); i++){
                    Element e = ((Element)concealedSlotsElement.getElementsByTagName("slot").item(i));
                    loadedConcealedSlots.add(InventorySlot.valueOf(e.getTextContent()));
                }
            } catch(Exception ex) {
                System.err.println("BlockedParts loading failed for "+errorCode+". Code 4b (concealedSlots -> slot); Context: "+contextForErrors);
                ex.printStackTrace();
                BlockedParts.printHelpfulErrorForEnumValueMismatches(ex, BlockedParts.getPossibleEnumValues());
            }
        }

        return new BlockedParts(displacementType,
                loadedClothingAccessRequired,
                loadedBlockedBodyParts,
                loadedClothingAccessBlocked,
                loadedConcealedSlots);
    }
}
