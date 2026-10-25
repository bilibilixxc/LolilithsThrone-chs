package com.lilithsthrone.LolificationProject.ConfigStuff;

import com.lilithsthrone.game.settings.ToggleOption;
import com.lilithsthrone.utils.colours.PresetColour;

public class AdvancedModOptions {

    public static final ToggleOption EXPERIMENTAL = new ToggleOption(
            "EXPERIMENTAL",
            PresetColour.BASE_GOLD,
            "实验性内容",
            "</br>启用后，NPCModSystem、挑战以及其他内容将被开启。",
            "EXPERIMENTAL"
    );
    public static final ToggleOption REPAIR_MODS = new ToggleOption(
            "REPAIR_MODS",
            PresetColour.BASE_GOLD,
            "修复模组",
            "</br>启用后，此选项会尝试修复模组作者的错误，例如误用 InventorySlot.HANDS 而本该使用 InventorySlot.HAND 等情况。",
            "REPAIR_MODS"
    );
    public static final ToggleOption LOG_UNPARSED_LINES = new ToggleOption(
            "LOG_UNPARSED_LINES",
            PresetColour.BASE_GOLD,
            "记录未解析行",
            "</br>启用后，会将游戏中出现的所有尚未解析的行以其原始形式记录到日志，供您修改或调整。",
            "LOG_UNPARSED_LINES"
    );
    public static final ToggleOption LOG_HEIGHT_CALCS = new ToggleOption(
            "LOG_HEIGHT_CALCS",
            PresetColour.BASE_GOLD,
            "记录身高计算",
            "</br>启用后，会将身高计算的所有步骤记录到 ./data/height_calc.log",
            "LOG_HEIGHT_GEN"
    );
    public static final ToggleOption TIME_LOG_NPC = new ToggleOption(
            "TIME_LOG_NPC",
            PresetColour.BASE_GOLD,
            "NPC生成计时日志",
            "</br>启用后，会将 NPC 生成的所有步骤耗时记录到 ./data/time_log.log",
            "TIME_TEST_NPC_GEN"
    );
    public static final ToggleOption TIME_LOG_PRELOAD = new ToggleOption(
            "TIME_LOG_PRELOAD",
            PresetColour.BASE_GOLD,
            "预加载计时日志",
            "</br>启用后，会将预生成的所有步骤耗时记录到 ./data/time_log.log",
            "TIME_TEST_PRELOADING"
    );
    public static final ToggleOption LOG_VIRGINITY_RATES = new ToggleOption(
            "LOG_VIRGINITY_RATES",
            PresetColour.BASE_GOLD,
            "记录处子身计算",
            "</br>启用后，会将处子身计算的所有步骤记录到 ./data/virgin_calcs.log",
            "LOG_VIRGIN_GEN"
    );
    public static final ToggleOption CHECK_FOR_UPDATES = new ToggleOption(
            "CHECK_FOR_UPDATES",
            PresetColour.BASE_GOLD,
            "自动检查更新",
            "</br>启用后，会检查 GitHub 上的最新版本，若发现新版本将在主菜单中通知您。",
            "CHECK_FOR_UPDATES"
    );
    public static final ToggleOption DRAKE_MODE = new ToggleOption(
            "DRAKE_MODE",
            PresetColour.BASE_GOLD,
            "Drake默认设置",
            "</br>启用后，将应用 DrakeFaewylde 偏好的设置，基本上等效于 FaeLT-lite。",
            "DRAKE_MODE"
    );
    public static final ToggleOption ARCHER_CONTENT = new ToggleOption(
            "ARCHER_CONTENT",
            PresetColour.BASE_GOLD,
            "Archer的内容",
            "</br>开启时，将启用 Archer 添加的内容，例如额外的自慰插入动作。",
            "ARCHER_CONTENT"
    );
    public static final ToggleOption LEXI_CONTENT = new ToggleOption(
            "LEXI_CONTENT",
            PresetColour.BASE_GOLD,
            "Lexi的内容",
            "</br>启用后，将启用新人 Lexi 制作的功能，包括但不限于尾巴穴。",
            "LEXI_CONTENT"
    );
    public static final ToggleOption MULTITHREAD_NPC_GENERATION = new ToggleOption(
            "MULTITHREAD_NPC_GENERATION",
            PresetColour.BASE_GOLD,
            "NPC多线程",
            "</br>启用后，NPC 将在独立的线程上生成，以加快游戏加载和新开档速度。有时会导致界面锁定，若遇到此问题请关闭本选项。",
            "MULTITHREAD_NPC_GENERATION"
    );
    public static final ToggleOption MULTITHREADED_PRELOADING = new ToggleOption(
            "MULTITHREADED_PRELOADING",
            PresetColour.BASE_GOLD,
            "预加载数据",
            "</br>启用后，将预载身体部件、衣物遮盖等内容，以加快游戏加载和新开档速度。",
            "MULTITHREADED_PRELOADING"
    );

    public static final ToggleOption BACKUP_SAVES = new ToggleOption(
            "BACKUP_SAVES",
            PresetColour.BASE_GOLD,
            "备份存档",
            "</br>启用后，会在保存时创建存档备份，以防保存过程出现异常。",
            "BACKUP_SAVES"
    );
}