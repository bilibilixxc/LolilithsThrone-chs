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
    @Setting(comment = "设为 'true' 时，会启用一些目前可靠性尚未确定的特性。")
    public static boolean EXPERIMENTAL = false;

    @Setting(comment = "设为 'true' 时，会启用尚未完成且很可能损坏的特性。")
    public static boolean DEVELOPER_MODE = false;

    @Setting(comment = "设为 'true' 时，会尝试修复模组作者的错误，例如误用 InventorySlot.HANDS 而本该使用 InventorySlot.HAND 等情况。")
    public static boolean REPAIR_MODS = true;

    @Setting(comment = "设为 'true' 时，所有旧设置将恢复并像以前一样发挥功能。")
    public static boolean DEGEN_MODE = false;

    @Setting(comment = "设为 'true' 时，若检测到你加载了含未成年角色的存档，将尝试修改上面的设置。")
    public static boolean AUTOMATIC_DEGEN_MODE = true;

    @Setting(comment = "设为 'true' 时，会将游戏中出现的所有尚未解析的行以其原始形式记录到日志，供修改或调整。")
    public static boolean LOG_UNPARSED_LINES = false;

    @Setting(comment = "尚未完成；完成后将允许你的存档保存到本配置文件所在文件夹，而非游戏文件夹。")
    public static boolean GLOBAL_SAVES = false;

    @Setting(comment = "设为 'true' 时，通过检查 https://github.com/DegenDomain/LolilithsThroneIssueTracker/blob/main/LatestVersion.txt 并与当前版本比较来检查更新。")
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
    @Setting(comment = "设为 'true' 时，会将一些默认设置更改为 Drake Faewylde 认可的值。")
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

    @Setting(comment = "开启时，将启用 Archer 添加的内容，例如额外的自慰插入动作。")
    public static boolean ARCHER_CONTENT = true;

    @Setting(comment = "设为 'true' 时，将启用 Lexi 添加的自定义内容，例如尾巴穴。")
    public static boolean LEXI_CONTENT = true;

    @Setting(comment = "设为 'true' 时，通过将 NPC 初始化与主线程解耦来加快游戏加载，稳定度 95%。")
    public static boolean MULTITHREAD_NPC_GENERATION = true;

    @Setting(comment = "设为 'true' 时，对 NPC 初始化进行速度测试。")
    public static boolean TIME_TEST_NPC_GEN = false;

    @Setting(comment = "设为 'true' 时，通过将身体部件注册与主线程解耦来加快游戏加载，稳定度 80%。")
    public static boolean MULTITHREADED_PRELOADING = true;

    @Setting(comment = "设为 'true' 时，对身体部件注册进行速度测试。")
    public static boolean TIME_TEST_PRELOADING = false;

    @Setting(comment = "设为 'true' 时，记录身高计算中发生的事件。")
    public static boolean LOG_HEIGHT_GEN = false;

    @Setting(comment = "设为 'true' 时，记录处子身计算中发生的事件。")
    public static boolean LOG_VIRGIN_GEN = false;

    @Setting(comment = "设为 'true' 时，会在保存时创建存档备份，以防保存过程出现异常。")
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