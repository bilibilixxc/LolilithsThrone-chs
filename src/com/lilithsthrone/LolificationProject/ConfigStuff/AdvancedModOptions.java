package com.lilithsthrone.LolificationProject.ConfigStuff;

import com.lilithsthrone.game.settings.ToggleOption;
import com.lilithsthrone.utils.colours.PresetColour;

public class AdvancedModOptions {

    public static final ToggleOption EXPERIMENTAL = new ToggleOption(
            "EXPERIMENTAL",
            PresetColour.BASE_GOLD,
            "Experimental Content",
            "</br>When enabled, NPCModSystem, Challenges, and other things will be enabled.",
            "EXPERIMENTAL"
    );
    public static final ToggleOption REPAIR_MODS = new ToggleOption(
            "REPAIR_MODS",
            PresetColour.BASE_GOLD,
            "Repair Mods",
            "</br>When enabled, this attempts to resolve mod author mistakes like using InventorySlot.HANDS instead of InventorySlot.HAND and the like.",
            "REPAIR_MODS"
    );
    public static final ToggleOption LOG_UNPARSED_LINES = new ToggleOption(
            "LOG_UNPARSED_LINES",
            PresetColour.BASE_GOLD,
            "Log Unparsed Lines",
            "</br>When enabled, this logs all lines seen in game in their unparsed form to be used for changing/altering them",
            "LOG_UNPARSED_LINES"
    );
    public static final ToggleOption LOG_HEIGHT_CALCS = new ToggleOption(
            "LOG_HEIGHT_CALCS",
            PresetColour.BASE_GOLD,
            "Log Height Calculations",
            "</br>When enabled, this logs all the steps of Height Calculations to ./data/height_calc.log",
            "LOG_HEIGHT_GEN"
    );
    public static final ToggleOption TIME_LOG_NPC = new ToggleOption(
            "TIME_LOG_NPC",
            PresetColour.BASE_GOLD,
            "Time Log NPC Gen",
            "</br>When enabled, this logs the time of  all the steps of NPC generation to ./data/time_log.log",
            "TIME_TEST_NPC_GEN"
    );
    public static final ToggleOption TIME_LOG_PRELOAD = new ToggleOption(
            "TIME_LOG_PRELOAD",
            PresetColour.BASE_GOLD,
            "Time Log Preloading",
            "</br>When enabled, this logs the time of all the steps of pre-generation to ./data/time_log.log",
            "TIME_TEST_PRELOADING"
    );
    public static final ToggleOption LOG_VIRGINITY_RATES = new ToggleOption(
            "LOG_VIRGINITY_RATES",
            PresetColour.BASE_GOLD,
            "Log Virginity Rate Calculations",
            "</br>When enabled, this logs all the steps of Virginity Calculations to ./data/virgin_calcs.log",
            "LOG_VIRGIN_GEN"
    );
    public static final ToggleOption CHECK_FOR_UPDATES = new ToggleOption(
            "CHECK_FOR_UPDATES",
            PresetColour.BASE_GOLD,
            "Automatic Update Check",
            "</br>When enabled, this checks github for what the latest version is, if found it notifies you on the main menu.",
            "CHECK_FOR_UPDATES"
    );
    public static final ToggleOption DRAKE_MODE = new ToggleOption(
            "DRAKE_MODE",
            PresetColour.BASE_GOLD,
            "Drake Default Settings",
            "</br>When enabled, this enables settings DrakeFaewylde prefers, basically FaeLT-lite.",
            "DRAKE_MODE"
    );
    public static final ToggleOption ARCHER_CONTENT = new ToggleOption(
            "ARCHER_CONTENT",
            PresetColour.BASE_GOLD,
            "Archer's Content",
            "</br>When on, this enables additions made by Archer, such as extra self-fuck actions.",
            "ARCHER_CONTENT"
    );
    public static final ToggleOption LEXI_CONTENT = new ToggleOption(
            "LEXI_CONTENT",
            PresetColour.BASE_GOLD,
            "Lexi Content",
            "</br>When enabled, this enables features made by our newbie Lexi, including but not limited to Tail-Pussy.",
            "LEXI_CONTENT"
    );
    public static final ToggleOption MULTITHREAD_NPC_GENERATION = new ToggleOption(
            "MULTITHREAD_NPC_GENERATION",
            PresetColour.BASE_GOLD,
            "NPC Multithreading",
            "</br>When enabled, this causes NPC's to be generated on separate threads to speed up game loading and new game. Sometimes causes UI Locks, if you experience them, disable this",
            "MULTITHREAD_NPC_GENERATION"
    );
    public static final ToggleOption MULTITHREADED_PRELOADING = new ToggleOption(
            "MULTITHREADED_PRELOADING",
            PresetColour.BASE_GOLD,
            "Preload Data",
            "</br>When enabled, this preloads Body Parts, covering and stuff, speeds up game loading and new game.",
            "MULTITHREADED_PRELOADING"
    );

    public static final ToggleOption BACKUP_SAVES = new ToggleOption(
            "BACKUP_SAVES",
            PresetColour.BASE_GOLD,
            "Backup Saves",
            "</br>When enabled, this makes a backup of saves incase something goes wrong with saving.",
            "BACKUP_SAVES"
    );
}