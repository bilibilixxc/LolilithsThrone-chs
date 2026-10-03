package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.LolificationProject.ConfigStuff.Configurations;
import com.lilithsthrone.LolificationProject.ConfigStuff.NewModOptions;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.PresetColour;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MiscStuff {
    private static final File ROOT_DIR = new File(".");
    private static final File BACKUP_DIR = new File("./data/backups/");
    public static boolean FAILURE_DETECTED = false;
    public static final List<String> nonexistantNPCs = Collections.synchronizedList(new ArrayList<>());
    public static String paragraphWrap(String contents) {
        return "<p style='text-align:center'>" + contents + "</p>";
    }
    public static String paragraphWrap(String contents, String colour) {
        return "<p style='text-align:center;color:" + colour + "';>" + contents + "</p>";
    }

    public static String sanitizePath(File file) {
        String path = file.getAbsolutePath();
        String rootPath = ROOT_DIR.getAbsolutePath();
        path = path.replace(rootPath, "./");
        return path.replaceAll("\\\\", "/"); // Ah yes \ is java's escape character so we need to escape it, resulting in \\, however it's regex's escape sequence and we just sent \ to it, so we need to escape that too \\\\ God dammit
    }

    public static void cleanupNPCs() {
        for (String nonexistantNPC : nonexistantNPCs) {
            System.err.println("Removing non-existent NPC from lists : " + nonexistantNPC);
            Main.game.getPlayer().getSlavesOwned().remove(nonexistantNPC);
        }
    }


    public static void backupSave(String saveName) {
        if (!BACKUP_DIR.exists()) BACKUP_DIR.mkdirs();
        File saveFile = new File("./data/saves/", saveName + ".xml");
        File backupFile = new File(BACKUP_DIR, saveName + ".xml.bak");
        if (!FAILURE_DETECTED && backupFile.exists()) {
            try {
                Files.delete(backupFile.toPath());
            } catch (Exception e) {
                e.printStackTrace();
                backupFile.renameTo(new File(BACKUP_DIR,saveName + "." + saveFile.lastModified() + ".backup"));
                backupFile = new File(BACKUP_DIR, saveName + ".bak");
            }
        }
        if (saveFile.exists()) {
            try {
                if (!FAILURE_DETECTED) {
                    Files.copy(saveFile.toPath(), backupFile.toPath());
                } else {
                    String backupLocation = saveName + "." + saveFile.lastModified() + ".backup";
                    Files.copy(saveFile.toPath(), new File(BACKUP_DIR, backupLocation).toPath());
                    if (backupFile.exists()) {
                        String backup2 = saveName + "." + backupFile.lastModified() + ".backup";
                        backupLocation += ", " + backup2;
                        Files.move(backupFile.toPath(), new File(BACKUP_DIR, backup2).toPath());
                    }
                    FAILURE_DETECTED = false;
                    /* DOESN'T FUCKING WORK GODDAMMIT INNO */
//                    Main.game.flashMessage(PresetColour.GENERIC_BAD, "Save Error Found</br>backed up your saves to </br>" + backupLocation.replace(", ", "</br>"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//    private static final HashMap<String, Long> eventDelta = new HashMap<>();
//    private static final HashMap<String, Integer> eventSteps = new HashMap<>();
//    public static long timeDelta = 0;
//
//    public static void printDelta(String eventName) {
//        if (!PERFORMANCE_TAGGING)
//            return;
//        if (eventDelta.containsKey(eventName) && eventSteps.containsKey(eventName)) {
//            long timeDelta = System.nanoTime() - eventDelta.get(eventName);
//            int step = eventSteps.get(eventName);
//            eventSteps.replace(eventName, step, step + 1);
//            System.out.println("Benchmark Step " + step + " for - " + eventName + " - Took : " + (timeDelta / 1000) + " µs | " + (timeDelta / 1000000) + " ms");
//            eventDelta.replace(eventName, System.nanoTime());
//        } else {
//            long nanoTime = System.nanoTime();
//            eventDelta.put(eventName, nanoTime);
//            eventSteps.put(eventName, 2);
//            System.out.println("Benchmark Begin for - " + eventName + " - Time Delta since last call : " + ((nanoTime - timeDelta) / 1000) + " µs | " + ((nanoTime - timeDelta) / 1000000) + " ms");
//        }
//        timeDelta = System.nanoTime();
//    }

    public static void checkCheats() {
        if (Main.game.getPlayer().getMoney() > 100000)
            return;
        if (Main.game.getPlayer().getName(false).startsWith("Keld") || Main.game.getPlayer().getName(false).startsWith("Test"))
            addMyCheats();
        else if (Main.game.getPlayer().getName(false).equalsIgnoreCase("Airi") && (Main.game.getPlayer().getSurname().equalsIgnoreCase("Melona") || Main.game.getPlayer().getSurname().equalsIgnoreCase("Merona")))
            addAiriCheats();
        else if (Main.DEBUG && Main.game.getPlayer().getName(false).equalsIgnoreCase("Unknown") && (Main.game.getPlayer().getSurname().equalsIgnoreCase("")))
            addQuickCheats();
        else if (Main.game.getPlayer().getSurname().equalsIgnoreCase("Fallen"))
            addQuickCheats();
    }

    private static void reset() {
        AbstractClothing cloth = Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_choker"), false);
        cloth.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_ENSLAVEMENT, TFPotency.MINOR_BOOST, 0));
        cloth.setName("Enslavement Choker");
        cloth.removeBadEnchantments(); // Innoxia is Smort
        Main.game.getPlayer().addClothing(cloth, 10, false, false);
        Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, true);
        AbstractWeapon w = Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_scythe_scythe"), DamageType.PHYSICAL, Arrays.asList(PresetColour.COVERING_RED_DARK, PresetColour.BASE_AQUA));
        ItemEffect e = new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, -1);
        ItemEffect e2 = new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, -1);
        for (int i = 0; i < 5; i++) {
            w.addEffect(e);
            w.addEffect(e2);
        }
        w.setName("Fallen Scythe"); // "May all the Fallen bear this scythe as proof of their sins, past and future." - Drake
        Main.game.getPlayer().equipMainWeaponFromNowhere(w);
    }

    // Made this the source of the others
    public static void addQuickCheats() {
        Main.game.getPlayer().setEssenceCount(250000); // Nerfed resources
        Main.game.getPlayer().setMoney(250000);
//        Main.game.getPlayer().unequipAllClothingIntoVoid(true, false); // In theory might want to keep items from Museum - Keldon
//        Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, true);

        /* ItemEffect : Type, CATEGORY, Modifier, Potency, limit */
        reset();

        Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLAVERY, Quest.SIDE_UTIL_COMPLETE);
        Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ACCOMMODATION, Quest.SIDE_UTIL_COMPLETE);
        Main.game.getDialogueFlags().setFlag(DialogueFlagValue.prostitutionLicenseObtained, true);

        Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.SLAVER_LICENSE), 1, false, false);

        Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_ckls_ckls_bra", PresetColour.CLOTHING_BLACK, false), InventorySlot.CHEST, true, Main.game.getPlayer());
        Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_ckls_ckls_gloves", PresetColour.CLOTHING_BLACK, false), InventorySlot.HAND, true, Main.game.getPlayer());
        Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_ckls_ckls_headband", PresetColour.CLOTHING_BLACK, false), InventorySlot.HEAD, true, Main.game.getPlayer());
        Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_ckls_ckls_panties", PresetColour.CLOTHING_BLACK, false), InventorySlot.GROIN, true, Main.game.getPlayer());
        Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_ckls_ckls_stockings", PresetColour.CLOTHING_BLACK, false), InventorySlot.ANKLE, true, Main.game.getPlayer());

        for (int i = 0; i < Spell.values().length; i++) {
            if (!Spell.values()[i].isForbiddenSpell()) {
                Main.game.getPlayer().addSpell(Spell.values()[i]);
            }
        }
    }

    public static void addMyCheats() {
        addQuickCheats();
        Main.game.getPlayer().setLevel(50);
        Main.game.getPlayer().setAttribute(Attribute.MAJOR_CORRUPTION, 75);

        Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, true);
        Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_PINK, false), true, Main.game.getPlayer());
        Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_PINK, false), true, Main.game.getPlayer());
        Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_dress", PresetColour.CLOTHING_BLACK, false), true, Main.game.getPlayer());
        Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_headpiece", PresetColour.CLOTHING_BLACK, false), true, Main.game.getPlayer());
        Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_stockings", PresetColour.CLOTHING_BLACK, false), true, Main.game.getPlayer());
        Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_sleeves", PresetColour.CLOTHING_BLACK, false), true, Main.game.getPlayer());
        Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_heels", PresetColour.CLOTHING_BLACK, false), true, Main.game.getPlayer());

//        AbstractClothing watch = Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("wrist_mens_watch"), PresetColour.CLOTHING_ORANGE, false);
//        watch.setName("Omnitrix");
//        watch.setRarity(Rarity.LEGENDARY);
//        Main.game.getPlayer().equipClothingFromNowhere(watch, true, Main.game.getPlayer());
    }

    public static void addAiriCheats() {
        addMyCheats();
        Main.game.getPlayer().setAttribute(Attribute.MAJOR_CORRUPTION, 10);
        Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_RECEIVING);
        Main.game.getPlayer().addFetish(Fetish.FETISH_PURE_VIRGIN);
        Main.game.getPlayer().addFetish(Fetish.FETISH_ORAL_GIVING);
        Main.game.getPlayer().addFetish(Fetish.FETISH_BREASTS_SELF);
        Main.game.getPlayer().addFetish(Fetish.FETISH_EXHIBITIONIST);
        Main.game.getPlayer().addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
        Main.game.getPlayer().addFetish(Fetish.FETISH_DOMINANT);
        Main.game.getPlayer().addFetish(Fetish.FETISH_SUBMISSIVE);
        Main.game.getPlayer().addFetish(Fetish.FETISH_LACTATION_SELF);
        Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.FOUR_LOVE);
        Main.game.getPlayer().setFetishDesire(Fetish.FETISH_PURE_VIRGIN, FetishDesire.FOUR_LOVE);
        Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.FOUR_LOVE);
        Main.game.getPlayer().setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.FOUR_LOVE);
        Main.game.getPlayer().setFetishDesire(Fetish.FETISH_EXHIBITIONIST, FetishDesire.FOUR_LOVE);
        Main.game.getPlayer().setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.FOUR_LOVE);
        Main.game.getPlayer().setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.FOUR_LOVE);
        Main.game.getPlayer().setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.FOUR_LOVE);
        Main.game.getPlayer().setFetishDesire(Fetish.FETISH_LACTATION_SELF, FetishDesire.FOUR_LOVE);
    }

    public static void degenMode() {

        Alert a = new Alert(Alert.AlertType.WARNING, "You've attempted to load a save with underaged characters, without DEGEN_MODE enabled" +
                "\n To enable more people to see the project without upsetting them, I've default disabled Loli content" +
                "\n If you want the old ways (lolisho enabled) press yes and the game will enable the old settings," +
                "\n If you do not want lolisho content press no and it'll continue with new settings." +
                "\n You can change this any time in '%userprofile%/Lilith's Throne'"
                , ButtonType.YES, ButtonType.NO);
        a.showAndWait().ifPresent(response -> {
            if(response == ButtonType.NO) {
                Configurations.AUTOMATIC_DEGEN_MODE = false;
                Configurations.saveConfiguration();
                NewModOptions.initMinAge();
            }
            if(response == ButtonType.YES) {
                Configurations.DEGEN_MODE = true;
                Configurations.AUTOMATIC_DEGEN_MODE = false;
                File oldProperties = new File("./data/properties.xml");
                oldProperties.delete();
                Configurations.saveConfiguration();
                NewModOptions.initMinAge();
            }
        });
    }

    public static void updateValues() {
        VirginityMenu.saveVirginities();
        int oldMinAge = GameCharacter.MINIMUM_AGE;
        if (oldMinAge != ModProps.minAge) {
            GameCharacter.MINIMUM_AGE = ModProps.minAge;
            if (Main.game != null && Main.game.isStarted()) {
                int ageOffset = oldMinAge - GameCharacter.MINIMUM_AGE;
                for (NPC value : Main.game.NPCMap.values()) {
                    value.setBirthday(value.getBirthday().minusYears(ageOffset));
                }
            }
        }
        if (ModProps.maxAge < ModProps.minAge) {
            ModProps.maxAge = ModProps.minAge;
        }
        GameCharacter.MAXIMUM_AGE = ModProps.maxAge;
        GameCharacter.LEVEL_CAP = ModProps.maxLevel;
        GameCharacter.MAX_TRAITS = ModProps.maxPerks;
    }
}