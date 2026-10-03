package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.LolificationProject.ConfigStuff.AdvancedModOptions;
import com.lilithsthrone.LolificationProject.ConfigStuff.Configurations;
import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.settings.SliderOption;
import com.lilithsthrone.main.Main;
import javafx.scene.input.KeyEvent;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.DRAKE_MODE;
import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.EXPERIMENTAL;
import static com.lilithsthrone.LolificationProject.ConfigStuff.NewModOptions.*;
import static com.lilithsthrone.LolificationProject.ModCommon.VirginityMenu.virginityRatesSliders;

public class ModOptionsController {
    static List<OptionInputListener> inputListeners = new ArrayList<>();

    public static void addModdedListeners() {
        if (!DRAKE_MODE || Configurations.DEGEN_MODE)
            ORIGINAL_AGE.addListener();
        IMPORT_AGE.addListener();
//        MIN_AGE_SPEECH.addListener();
        LEXI_MENU_OPTIONS.addListener();
        FETISH_PEE.addListener();
        FETISH_AGE.addListener();
        SHOW_AGE.addListener();
        SHOW_TRUE_AGE.addListener();
        KIN_MODE.addListener();
        OFFSPRING_INHERIT_LEVEL.addListener();
        if (Configurations.DEGEN_MODE) {
            SHOW_ENHANCED_DESCRIPTION.addListener();
            OFFSPRING_AGE_PREF.addListener();
            SHOTA_AGE.addListener();
            LOLI_AGE.addListener();
            YOUNG_AGE.addListener();
            OLD_AGE.addListener();
        }
        COMBAT_HUG.addListener();
        EQUIP_UNIQUES.addListener();
        ENSLAVE_LUMI.addListener();
        COMBAT_LEAKAGE.addListener();
        FORCE_CLEAN_SLOTS.addListener();
        AGE_PROGRESSION.addListener();
        ENABLE_PENT_UP.addListener();
        NERF_AFF_OBED.addListener();
        ENABLE_SLOVENLY.addListener();
        LOOT_THE_MUSEUM.addListener();
        WAKE_AFFECTION_LOSS.addListener();
        VANILLA_JOB_CONDITIONS.addListener();
        ENABLE_PERSONALITY.addListener();
        ENABLE_FRENCH_KISS.addListener();
        ENABLE_TUSSY_CONTENT.addListener();
        ENABLE_EXTRA_SELF_CONTENT.addListener();
        ENABLE_SEX_IMAGES.addListener();
        FUCKABLE_CUP_SIZE.addListener();
    }

    public static void addBalanceListeners() {
        ENABLE_ENFORCERS.addListener();
        FORCE_ORGASMIC_DRAIN.addListener();

//        DEMON_CHANCE.addListener();
//        ENSLAVEMENT_COST.addListener();
        PLAYER_PREG_DUR.addListener();
        NPC_PREG_DUR.addListener();
        MONEY_MULT.addListener();
        ENABLE_MULTI_INCOME.addListener();
        INTEREST_MULT.addListener();
        ESS_MULT.addListener();
        XP_MULT.addListener();
        MAX_ENCHANTMENTS.addListener();
        ENC_COST_MULT.addListener();
        DROPS_MULT.addListener();
        MAX_LEVEL.addListener();
        MAX_PERKS.addListener();
        ESSENCE_STOCK.addListener();


        if (Main.getProperties().hasValue(PropertyValue.enchantmentLimits)) {
            ENCH_STAB_BASE.addListener();
            ENCH_STAB_LIMIT.addListener();
            ENCH_STAB_PER_LEVEL.addListener();
            ENCH_STAB_PER_PHYS.addListener();
            ENCH_STAB_PER_ARC.addListener();
            ENCH_STAB_PER_CORR.addListener();
        }

//        if (EXPERIMENTAL) {
            POTENCY_MINOR.addListener();
            POTENCY_MEDIUM.addListener();
            POTENCY_MAJOR.addListener();
            VALUE_MINOR.addListener();
            VALUE_MEDIUM.addListener();
            VALUE_MAJOR.addListener();
//        }
    }

    public static void addBodyModListeners() {
        EXCLUDE_AMORPHOUS.addListener();
        IMP_PREFERENCES.addListener();
        IMP_HEIGHT.addListener();
        ALPHA_IMP_HEIGHT.addListener();
        if (Configurations.DEGEN_MODE) {
            HEIGHT_MOD.addListener();
            HEIGHT_GENDERED.addListener();
            HEIGHT_RELATIVE.addListener();
            BODY_AGE.addListener();
            BA_BODY.addListener();
            BA_BREASTS.addListener();
            BA_NIPPLE.addListener();
            BA_HIPS.addListener();
            BA_VAGINA.addListener();
            BA_PENIS.addListener();
            BA_TESTICLE.addListener();
            BA_THROAT.addListener();
            BA_ASS.addListener();
            BA_ANUS.addListener();
            BA_URETHRA.addListener();
        }
        ENABLE_PIERCINGS.addListener();
        ENABLE_TATTOOS.addListener();
        ENABLE_MAKEUP.addListener();
        ALWAYS_MODDED.addListener();
        PREVENT_GIANTS.addListener();

        MINIMUM_AGE.addListener();
        MAXIMUM_AGE.addListener();
        CHANGE_APPARENT_AGE.addListener();
        APPARENT_AGE_OFFSET.addListener();
        AMORPHOUS_APPARENT_AGE_OFFSET.addListener();
//        OFFSPRING_AGE.addListener();
        if (Configurations.DEGEN_MODE) {
            SHOTA_ENDOWMENT.addListener();
            LOLI_FERTILITY.addListener();
            OPPAI_LOLI.addListener();
        }
//        VIRGIN_LOVER.addListener();
        HEIGHT_DEVIATION.addListener();
        HEIGHT_AGE_CAP.addListener();
        IMP_HMULT.addListener();
        A_IMP_HMULT.addListener();
        HETEROCHROMIA_CHANCE.addListener();
        ORGASMIC_LEVEL_DRAIN_CHANCE.addListener();
        CHUUNI_CHANCE.addListener();
        NPC_PREG_CHANCE.addListener();
    }

    public static void addChallengeModListeners() {
        INVENTORY_TABS.addListener();
        INVENTORY_ROWS.addListener();
    }

    public static void addAdvancedSettingsListeners() {
        AdvancedModOptions.EXPERIMENTAL.addListener();
        AdvancedModOptions.REPAIR_MODS.addListener();
        AdvancedModOptions.LOG_UNPARSED_LINES.addListener();
        AdvancedModOptions.CHECK_FOR_UPDATES.addListener();
        AdvancedModOptions.DRAKE_MODE.addListener();
        AdvancedModOptions.LOG_HEIGHT_CALCS.addListener();
        AdvancedModOptions.TIME_LOG_NPC.addListener();
        AdvancedModOptions.TIME_LOG_PRELOAD.addListener();
        AdvancedModOptions.LOG_VIRGINITY_RATES.addListener();
        AdvancedModOptions.ARCHER_CONTENT.addListener();
        AdvancedModOptions.LEXI_CONTENT.addListener();
        AdvancedModOptions.MULTITHREAD_NPC_GENERATION.addListener();
        AdvancedModOptions.MULTITHREADED_PRELOADING.addListener();
        AdvancedModOptions.BACKUP_SAVES.addListener();
    }

    public static void addVirginityListeners() {
        for (Femininity value : Femininity.values()) {
            HashMap<AgeCategory, SliderOption> ageVirginityRates = virginityRatesSliders.get(value);

            for (AgeCategory ageCategory : ageVirginityRates.keySet()) {
                if (ageCategory.getMinimumValue() == ageCategory.getMaximumValue()) continue;
                ageVirginityRates.get(ageCategory).addListener();
            }
        }
        VirginityMenu.vaginaVirginSlider.addListener();
        VirginityMenu.penisVirginSlider.addListener();
        VirginityMenu.analVirginSlider.addListener();
        VirginityMenu.faceVirginSlider.addListener();
        VirginityMenu.assVirginSlider.addListener();
        VirginityMenu.spinneretVirginSlider.addListener();
        VirginityMenu.nippleVirginSlider.addListener();
        VirginityMenu.nippleCrotchVirginSlider.addListener();
        VirginityMenu.urethraVirginSlider.addListener();
        VirginityMenu.vaginaUrethraVirginSlider.addListener();
    }

    @SuppressWarnings("unused")
    private static void refreshRecord(String elementID, String fieldName) {
        MainController.document.getElementById(elementID).setTextContent(Main.getProperties().getIntByRef(fieldName) + (MainController.document.getElementById(elementID).getTextContent().contains("%") ? "%" : ""));
        MainController.document.getElementById(elementID).setAttribute("style", "text-align:center; width:calc(30%); float:right; margin:0;font-weight: bold;");
    }

    @SuppressWarnings("unused")
    private static void setButtonStyles(String elementID, int val, int minVal, int maxVal) {
        Element buttonElement = MainController.document.getElementById(elementID + "_ON");
        if (val >= maxVal) {
            buttonElement.setAttribute("class", "normal-button disabled");
            buttonElement.setTextContent("+");
            buttonElement.setAttribute("style", "width:10%; margin:0 2.5%; text-align:center; float:right; font-weight: bold; color: #777;");
        } else {
            buttonElement.setAttribute("class", "normal-button");
            buttonElement.setTextContent("+");
            buttonElement.setAttribute("style", "width:10%; margin:0 2.5%; text-align:center; float:right; font-weight: bold; color: #57DB7E;");
        }

        buttonElement = MainController.document.getElementById(elementID + "_OFF");
        if (val <= minVal) {
            buttonElement.setAttribute("class", "normal-button disabled");
            buttonElement.setTextContent("-");
            buttonElement.setAttribute("style", "width:10%; margin:0 2.5%; text-align:center; float:right; font-weight: bold; color: #777;");
        } else {
            buttonElement.setAttribute("class", "normal-button");
            buttonElement.setTextContent("-");
            buttonElement.setAttribute("style", "width:10%; margin:0 2.5%; text-align:center; float:right; font-weight: bold; color: #EA5D76;");
        }
    }

    public static byte handleInput(KeyEvent event) {
        for (OptionInputListener inputListener : inputListeners) {
            byte v = inputListener.handleInput(event);
            if (v != 0) {
                return v;
            }
        }
        return 0;
    }

    public static void clearInputListeners() {
        inputListeners.clear();
    }

    public static void addInputListener(OptionInputListener optionInputListener) {
        inputListeners.add(optionInputListener);
    }
}