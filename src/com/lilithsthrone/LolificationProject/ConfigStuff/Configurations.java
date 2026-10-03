package com.lilithsthrone.LolificationProject.ConfigStuff;

import java.io.File;

import static com.lilithsthrone.LolificationProject.ConfigStuff.ConfigurationManager.configFolder;

/**
 * Variables using @Setting will be saved to '%userprofile%/Lilith's Throne/' instead of the './data/` folder.
 */
public class Configurations {

    /**
     * Experimental settings that are not fully tested or not fully implemented will be locked behind
     * this boolean, to allow further development and bug fixes without adding potentially broken content
     * to the game before it's ready.
     */
    @Setting(comment = "When 'true' this enables some features that are currently uncertain of their reliability.")
    public static boolean EXPERIMENTAL = false;

    @Setting(comment = "When 'true' this enables features that are currently not finished and are certainly broken.")
    public static boolean DEVELOPER_MODE = false;

    @Setting(comment = "When 'true' this attempts to resolve mod author mistakes like using InventorySlot.HANDS instead of InventorySlot.HAND and the like.")
    public static boolean REPAIR_MODS = true;

    @Setting(comment = "When 'true' all the old settings will return and function exactly as before")
    public static boolean DEGEN_MODE = false;

    @Setting(comment = "When 'true' it will attempt to change the above setting if it detects you loading a save with underaged characters.")
    public static boolean AUTOMATIC_DEGEN_MODE = true;

    @Setting(comment = "When 'true' this logs all lines seen in game in their unparsed form to be used for changing/altering them")
    public static boolean LOG_UNPARSED_LINES = false;

    @Setting(comment = "Unfinished, when it is finished it will allow your saves to save to the folder this config is located in, instead of game folder.")
    public static boolean GLOBAL_SAVES = false;

    @Setting(comment = "When 'true' checks for updates by checking https://github.com/DegenDomain/LolilithsThroneIssueTracker/blob/main/LatestVersion.txt and comparing against current version")
    public static boolean CHECK_FOR_UPDATES = true;

    @Setting(comment = "Do not edit this, this is a sanity check for error log expected length")
    public static int ERR_LOG_INFO_LENGTH = 15;
    /*
        TODO
        ~ Use GLOBAL_SAVES flag for new global save system. Users can choose to use the old /data/ folder or the new one in user.home
        - Reference at - Game.java:315
        ~ Add StAX saving
        - Reference at = ???:??? (Spoke of this in DMs with @Keldon
     */

    /**
     * Makes numerous changes (mostly just changing default values) to suit one of the developer's preferences.
     * A few noteworthy examples can be found below the delcaration in a comment block.<br>
     * For a full list, search for "DRAKE_MODE ?" without the quotes.<br><br>
     * FAETODO: Add the following DRAKE_MODE features:  <br>
     * ~ NMS Mass Adjust Settings                       <br>
     * ~ ... more?                                      <br>
     */
    @Setting(comment = "When 'true' it changes some default settings to values that are approved by Drake Faewylde")
    public static boolean DRAKE_MODE = false; // Now enabled on using key code DRAKE similar to BUGGY
    /*
        FAETODO NewQuestTree.java test and deploy, set up the rest of the game to run NewQuestTree while DRAKE_MODE is true
    */
    /*
        Current features:
        ~ Changes A LOT of defaults - Only affects new game and/or fresh saves (meaning start the game, enable FaeLT Mode, then delete data folder)
            A few worth noting: fontSize 18 -> 22, zoomedIn true -> false, various content options, various mod options
            Also changes default furry preference to Minimum (no greater morphs)
        ~ Changes Nyan's outfits (Daily reset, does not require new game/fresh save)
            Normal outfit replaced with kitty lingerie set (as a placeholder and proof of concept)
            Lingerie color changed from black to white
        ~ Hides ORIGINAL_AGE from Mod Options (Only hides the option, does not disable or change it)
            Simply hides the option, as it defaults to false. This was done primarily as bug prevention, and to get rid of an eyesore lol.
    */

    @Setting(comment = "When on, this enables additions made by Archer, such as extra self-fuck actions.")
    public static boolean ARCHER_CONTENT = true;

    @Setting(comment = "When 'true' this enables custom content added by Lexi such as Tail Pussy")
    public static boolean LEXI_CONTENT = true;

    @Setting(comment = "When 'true' this speeds up game loading by decoupling NPC initialization from the main thread, 95% stable")
    public static boolean MULTITHREAD_NPC_GENERATION = true;

    @Setting(comment = "When 'true' this speed tests NPC initialization")
    public static boolean TIME_TEST_NPC_GEN = false;

    @Setting(comment = "Then 'true' this speeds up game loading by decoupling body part registration from the main thread, 80% stable")
    public static boolean MULTITHREADED_PRELOADING = true;

    @Setting(comment = "When 'true' this speed tests body part registration")
    public static boolean TIME_TEST_PRELOADING = false;

    @Setting(comment = "When 'true' this logs what happens in height calculation")
    public static boolean LOG_HEIGHT_GEN = false;

    @Setting(comment = "When 'true' this logs what happens in virginity calculation")
    public static boolean LOG_VIRGIN_GEN = false;

    @Setting(comment = "Then 'true' this makes a backup of saves incase something goes wrong with saving.")
    public static boolean BACKUP_SAVES = true;


    public static void setValue(String valueName, boolean value) {
        try {
            Configurations.class.getField(valueName).setBoolean(null, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } // #Mandatory
    }

    public static void setValue(String valueName, int value) {
        try {
            Configurations.class.getField(valueName).setInt(null, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } // #Mandatory
    }
    public static boolean getBoolValue(String valueName) {
        try {
            return Configurations.class.getField(valueName).getBoolean(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } // #Mandatory
        return false;
    }

    public static int getIntValue(String valueName) {
        try {
            return Configurations.class.getField(valueName).getInt(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } // #Mandatory
        return -1;
    }

    public static void saveConfiguration() {
        ConfigurationManager.saveConfig(Configurations.class, new File(configFolder, Configurations.class.getSimpleName() + ".cfg"));
    }
}