package com.lilithsthrone.LolificationProject.Experiments.NMS;

import com.lilithsthrone.LolificationProject.Experiments.NMS.NMSElements.NPCOptionSlider;
import com.lilithsthrone.LolificationProject.Experiments.NMS.NMSElements.NPCOptionToggle;
import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import org.w3c.dom.events.EventTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import static com.lilithsthrone.LolificationProject.Experiments.NMS.NMSUI.buildMiddle;
import static com.lilithsthrone.LolificationProject.Experiments.NMS.NPCModSys.allNPCs;
import static com.lilithsthrone.LolificationProject.Experiments.NMS.SettingType.*;
import static com.lilithsthrone.game.character.GameCharacter.MAXIMUM_AGE;
import static com.lilithsthrone.game.character.GameCharacter.MINIMUM_AGE;

/* Friendly Note : NMS is to be pronounced Nimis */
public class NMSController {

    private static final HashMap<Class<? extends NPC>, NMSOptions> NPC_MODIFICATIONS = new HashMap<>();
    static Class<? extends NPC> CURRENTLY_ACTIVE_NPC;
    static final LinkedHashMap<SettingType, LinkedList<NPCOptionSlider>> sliders = new LinkedHashMap<>();
    static final HashMap<SettingType, ArrayList<NPCOptionToggle>> toggles = new HashMap<>();
    static void resetSliders() {
        sliders.clear();
        for (SettingType value : values()) {
            sliders.put(value, new LinkedList<>());
        }
    }

    static void resetToggles() {
        toggles.clear();
        for (SettingType value : SettingType.values()) {
            toggles.put(value, new ArrayList<>());
        }
    }

    public static void createToggles() {
        resetToggles();
        toggles.get(GENERAL).add(new NPCOptionToggle(CURRENTLY_ACTIVE_NPC.getSimpleName() + "_oralVirginity", CURRENTLY_ACTIVE_NPC, PresetColour.GENERIC_ARCANE, "口部处女", "", "oralVirginity"));
        if (Main.getProperties().hasValue(PropertyValue.nipplePenContent))
            toggles.get(GENERAL).add(new NPCOptionToggle(CURRENTLY_ACTIVE_NPC.getSimpleName() + "_nippleVirginity", CURRENTLY_ACTIVE_NPC, PresetColour.GENERIC_ARCANE, "乳头处女", "", "nippleVirginity"));
        if (Main.getProperties().hasValue(PropertyValue.analContent)) {
            toggles.get(GENERAL).add(new NPCOptionToggle(CURRENTLY_ACTIVE_NPC.getSimpleName() + "_analVirginity", CURRENTLY_ACTIVE_NPC, PresetColour.GENERIC_ARCANE, "肛门处女", "", "analVirginity"));
            toggles.get(GENERAL).add(new NPCOptionToggle(CURRENTLY_ACTIVE_NPC.getSimpleName() + "_anusBleached", CURRENTLY_ACTIVE_NPC, PresetColour.GENERIC_ARCANE, "粉嫩肛门", "", "anusBleached"));
        }
        toggles.get(GENERAL).add(new NPCOptionToggle(CURRENTLY_ACTIVE_NPC.getSimpleName() + "_hasVagina", CURRENTLY_ACTIVE_NPC, PresetColour.GENERIC_ARCANE, "拥有阴道", "", "hasVagina"));
        toggles.get(GENERAL).add(new NPCOptionToggle(CURRENTLY_ACTIVE_NPC.getSimpleName() + "_hasPenis", CURRENTLY_ACTIVE_NPC, PresetColour.GENERIC_ARCANE, "拥有阴茎", "", "hasPenis"));
        toggles.get(VAGINA).add(new NPCOptionToggle(CURRENTLY_ACTIVE_NPC.getSimpleName() + "_vaginaSquirter", CURRENTLY_ACTIVE_NPC, PresetColour.GENERIC_ARCANE, "潮吹体质", "", "vaginaSquirter"));
        toggles.get(VAGINA).add(new NPCOptionToggle(CURRENTLY_ACTIVE_NPC.getSimpleName() + "_vaginalVirginity", CURRENTLY_ACTIVE_NPC, PresetColour.GENERIC_ARCANE, "阴道处女", "", "vaginalVirginity"));
        toggles.get(PENIS).add(new NPCOptionToggle(CURRENTLY_ACTIVE_NPC.getSimpleName() + "_penileVirginity", CURRENTLY_ACTIVE_NPC, PresetColour.GENERIC_ARCANE, "阴茎处女", "", "penileVirginity"));
        if (Main.getProperties().hasValue(PropertyValue.urethralContent)) {
            toggles.get(PENIS).add(new NPCOptionToggle(CURRENTLY_ACTIVE_NPC.getSimpleName() + "_penileUrethraVirginity", CURRENTLY_ACTIVE_NPC, PresetColour.GENERIC_ARCANE, "阴茎尿道处女", "", "penileUrethraVirginity"));
            toggles.get(VAGINA).add(new NPCOptionToggle(CURRENTLY_ACTIVE_NPC.getSimpleName() + "_vaginalUrethraVirginity", CURRENTLY_ACTIVE_NPC, PresetColour.GENERIC_ARCANE, "阴道尿道处女", "", "vaginalUrethraVirginity"));
        }
        toggles.get(SPINNERET).add(new NPCOptionToggle(CURRENTLY_ACTIVE_NPC.getSimpleName() + "_spinneretVirginity", CURRENTLY_ACTIVE_NPC, PresetColour.GENERIC_ARCANE, "丝囊处女", "", "spinneretVirginity"));
        if (Main.getProperties().hasValue(PropertyValue.udderContent))
            toggles.get(CROTCH_BREAST).add(new NPCOptionToggle(CURRENTLY_ACTIVE_NPC.getSimpleName() + "_crotchNippleVirginity", CURRENTLY_ACTIVE_NPC, PresetColour.GENERIC_ARCANE, "胯部乳头处女", "", "crotchNippleVirginity"));
    }

    public static void addNPCModSysListeners() {
        for (LocationType value : LocationType.values()) {
            for (Class<? extends NPC> npc : allNPCs.get(value)) {
                String id = npc.getSimpleName() + "_NPCMS";
                if (MainController.documentAttributes.getElementById(id) != null) {
                    ((EventTarget) MainController.documentAttributes.getElementById(id)).addEventListener("click", e -> {
                        Main.mainController.getWebEngine().executeScript("document.getElementById('dialogue_title').innerHTML = \"NPC 修改系统 (" + NMSController.getName(npc) + ")\";");
                        save();
                        CURRENTLY_ACTIVE_NPC = npc;
                        buildMiddle();
                    }, false);
                }
            }
        }
    }

    static void addSliderListeners() {
        for (SettingType value : SettingType.values()) {
            for (NPCOptionSlider slider : sliders.get(value)) {
                slider.addListener();
            }
        }
    }
    static void addToggleListeners() {
        for (SettingType value : SettingType.values()) {
            for (NPCOptionToggle npcOptionToggle : toggles.get(value)) {
                npcOptionToggle.addListener();
            }
        }
    }

    static String getName(Class<? extends NPC> npc) {
        NMSOptions nmo = getNMO(npc);
        return nmo.name/* + " " + nmo.surname*/;
    }

    @SuppressWarnings("RedundantIfStatement")
    static boolean filterContent(SettingType value) {
        if (value == VAGINAL_URETHRA && (!Main.getProperties().hasValue(PropertyValue.urethralContent) || !NMSController.getBVariable(CURRENTLY_ACTIVE_NPC, "hasVagina")))
            return true;
        if (value == PENILE_URETHRA && (!Main.getProperties().hasValue(PropertyValue.urethralContent) || !NMSController.getBVariable(CURRENTLY_ACTIVE_NPC, "hasPenis")))
            return true;
        if (value == VAGINA && !NMSController.getBVariable(CURRENTLY_ACTIVE_NPC, "hasVagina")) return true;
        if (value == PENIS && !NMSController.getBVariable(CURRENTLY_ACTIVE_NPC, "hasPenis")) return true;
        if (value == CROTCH_BREAST && !(NMSController.getBVariable(CURRENTLY_ACTIVE_NPC, "hasCrotchNipple") || Main.getProperties().hasValue(PropertyValue.udderContent)))
            return true;
        if (value == SPINNERET && !NMSController.getBVariable(CURRENTLY_ACTIVE_NPC, "hasSpinneret"))
            return true;
        if (value == ASS && !Main.getProperties().hasValue(PropertyValue.analContent))
            return true;
        return false;
    }

    public static NMSOptions getNMO(Class<? extends NPC> npc) {
//        long timer = System.currentTimeMillis();
        try {
            if (!NPC_MODIFICATIONS.containsKey(npc))
                NPC_MODIFICATIONS.put(npc, NMSOptions.load(npc));
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
//        System.out.println("getNMO took " + (System.currentTimeMillis() - timer) + "ms to Load " + npc.getSimpleName());
        return NPC_MODIFICATIONS.get(npc);
    }

    public static NPCOptionSlider getSliderForNPC(Class<? extends NPC> npc, String value, String display, int minVal,
                                                  int minStep,
                                                  int maxStep,
                                                  int valPerStep, String fieldName) {
        return new NPCOptionSlider(
                npc.getSimpleName()+"_"+fieldName,
                npc,
                PresetColour.GENERIC_ARCANE,
                npc.getSimpleName() + "的" + value,
                "此滑块用于设置 " + npc.getSimpleName() + " 初始生成状态下的默认" + value + "。",
                display,
                minVal,
                minStep,
                maxStep,
                valPerStep, fieldName);
    }

    public static void setVariable(Class<? extends NPC> npc, String variableName, int variableValue) {
        NMSOptions nmo = getNMO(npc);
        try {
            nmo.setVariable(variableName, variableValue);
        } catch (Exception ignored) {
            System.err.println("Failed to setVariable " + npc.getSimpleName() + " - " + variableName + " = " + variableValue);
        }
    }

    public static void setVariable(Class<? extends NPC> npc, String variableName, boolean variableValue) {
        NMSOptions nmo = getNMO(npc);
        try {
            nmo.setVariable(variableName, variableValue);
        } catch (Exception ignored) {
            System.err.println("Failed to setVariable " + npc.getSimpleName() + " - " + variableName + " = " + variableValue);
        }
    }

    public static void massSet(String fieldName, int newValue) {
        for (LocationType location : LocationType.values())
            for (Class<? extends NPC> clazz : allNPCs.get(location))
                setVariable(clazz, fieldName, newValue);
    }

    public static void massSet(String fieldName, boolean newValue) {
        for (LocationType location : LocationType.values())
            for (Class<? extends NPC> clazz : allNPCs.get(location))
                setVariable(clazz, fieldName, newValue);
    }

    public static void adjustInteger(Class<? extends NPC> npc, String fieldName, float multiplier) {
        NMSOptions nmo = getNMO(npc);
        try {
            int oldValue = nmo.getIVariable(fieldName);
            int newValue = Math.round(oldValue * multiplier);
            if (fieldName.equals("height") && newValue <= 132) newValue = Util.random.nextInt(18, 132); /* height check to prevent demons from becoming imps, and add
            randomization */
            if (fieldName.equals("height") && newValue >= 250) newValue = Util.random.nextInt(50, 200); /* To add some variety */
            if (fieldName.equals("femininity") && newValue <= 10) newValue = Util.random.nextInt(0, 15);
            if (fieldName.equals("femininity") && newValue >= 90) newValue = Util.random.nextInt(15, 85);
//            System.out.println("NPC: " + npc + " | fieldName = " + fieldName + " | new Value = " + newValue);
            nmo.setVariable(fieldName, newValue);
            nmo.save(npc);
        } catch (Exception e) {
            System.out.println("adjustInteger failed for - " + npc.getSimpleName() + " variable : " + fieldName + " with " + multiplier);
            e.printStackTrace();
        }
    }
    public static void adjustBoolean(Class<? extends NPC> npc, String fieldName, boolean newValue) {
        NMSOptions nmo = getNMO(npc);
        try {
//            boolean oldValue = nmo.getBVariable(fieldName);
            nmo.setVariable(fieldName, newValue);
            nmo.save(npc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void massAdjustInteger(String fieldName, float newValue) {
        for (LocationType location : LocationType.values())
            for (Class<? extends NPC> clazz : allNPCs.get(location))
                adjustInteger(clazz, fieldName, newValue);
    }

    public static void massAdjustBoolean(String fieldName, boolean newValue) {
        for (LocationType location : LocationType.values())
            for (Class<? extends NPC> clazz : allNPCs.get(location))
                adjustBoolean(clazz, fieldName, newValue);
    }

    public static void massRandomInteger(String fieldName) {
        float multiplier = 0;
        if (fieldName.equals("height")) {
            multiplier = (float) (Util.random.nextInt(6, 7) / 10f);
        }
        if (fieldName.equals("femininity")) multiplier = (float) Math.random();
//        System.out.println("fieldName = " + fieldName + " | modifier = " + multiplier);
        for (LocationType location : LocationType.values())
            for (Class<? extends NPC> clazz : allNPCs.get(location))
                adjustInteger(clazz, fieldName, multiplier);
    }

    public static void massRandomIntegerWithBounds(String fieldName, int lowerLimit, int upperLimit) {
//        int hiddenVar = 0;
        float multiplier = 0;
        if (fieldName.equals("height") && lowerLimit < 132) lowerLimit = 132; // Some sanity checks
        if (fieldName.equals("height") && upperLimit > 250) upperLimit = 250;
        if (fieldName.equals("femiminity") && lowerLimit < 0) lowerLimit = 0;
        if (fieldName.equals("femininity") && upperLimit > 100) upperLimit = 100;
//        while (newValue < lowerLimit) {
            if (fieldName.equals("height")) multiplier = Util.random.nextInt(6, 7) / 10f;
            if (fieldName.equals("femininity")) multiplier = Util.random.nextFloat(0, 1f);
//            hiddenVar += 1;
//        }
//        while (newValue > upperLimit) {
//            if (fieldName == "height") newValue = (Util.random.nextInt(132, 118));
//            if (fieldName == "femininity") newValue = Util.random.nextInt(0, 100);
//            hiddenVar += 1;
//        }
//        System.out.println("fieldName = " + fieldName + " | modifier = " + newValue + " | total attempts = " + hiddenVar);
        for (LocationType location : LocationType.values())
            for (Class<? extends NPC> clazz : allNPCs.get(location))
                adjustInteger(clazz, fieldName, multiplier);
    }

    public static void massRandomBoolean(String fieldName) {
        boolean newValue = !(Math.random() < 0.5f);
        for (LocationType location : LocationType.values())
            for (Class<? extends NPC> clazz : allNPCs.get(location))
                adjustBoolean(clazz, fieldName, newValue);
    }

    public static int getIVariable(Class<? extends NPC> npc, String variableName) {
        NMSOptions nmo = getNMO(npc);
        try {
            return nmo.getIVariable(variableName);
        } catch (Exception ignored) {}
        return -1;
    }

    public static boolean getBVariable(Class<? extends NPC> npc, String variableName) {
        NMSOptions nmo = getNMO(npc);
        try {
            return nmo.getBVariable(variableName);
        } catch (Exception ignored) {}
        return false;
    }

    static void createSliders() {
        Class<? extends NPC> npc = CURRENTLY_ACTIVE_NPC;
        {
            resetSliders();
            sliders.get(GENERAL).add(getSliderForNPC(npc, "年龄", "%VALUE", MINIMUM_AGE, 1, MAXIMUM_AGE, 1, "age"));
            sliders.get(GENERAL).add(getSliderForNPC(npc, "外貌年龄", "%VALUE", MINIMUM_AGE, 1, MAXIMUM_AGE, 1, "apparentAge"));
            sliders.get(GENERAL).add(getSliderForNPC(npc, "身高", "%UNIT_SIZE", Height.NEGATIVE_THREE_MINIMUM.getMinimumValue(), 1, Height.SEVEN_COLOSSAL.getMaximumValue(), 1, "height"));
        }
        for (SettingType setting : SettingType.values()) {
            if (setting == GENERAL) continue;
            String orifice = setting.getSettingName(), valueName = setting.getDisplayName();
            NPCOptionSlider slider;
            if (setting == BREAST) {
                slider = getSliderForNPC(npc, "罩杯大小", "%CUP_SIZE", CupSize.FLAT.getMeasurement(), 0, CupSize.N.getMeasurement(), 1, "cupsize");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "乳头大小", "!NippleSize", NippleSize.ZERO_TINY.getValue(), 0, NippleSize.FOUR_MASSIVE.getValue(), 1, "nippleSize");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "乳晕大小", "!AreolaeSize", AreolaeSize.ZERO_TINY.getValue(), 0, AreolaeSize.FOUR_MASSIVE.getValue(), 1, "areolaSize");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "储乳量", "%UNIT_VOLUME", Lactation.ZERO_NONE.getMinimumValue(), 0, Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue(), 10, "nippleMilkStorage");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "泌乳再生", "%UNIT_VOLUME", FluidRegeneration.ZERO_SLOW.getMinimumRegenerationValuePerDay(), 0, FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay(), 100, "nippleMilkRegen");
                sliders.get(setting).add(slider);
            }
            if (setting == CROTCH_BREAST) {
                slider = getSliderForNPC(npc, "胯部乳房罩杯大小", "%CUP_SIZE", CupSize.FLAT.getMeasurement(), 0, CupSize.N.getMeasurement(), 1, "crotchNippleCupsize");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "胯部乳头大小", "!NippleSize", NippleSize.ZERO_TINY.getValue(), 0, NippleSize.FOUR_MASSIVE.getValue(), 1, "crotchNippleSize");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "胯部乳晕大小", "!AreolaeSize", AreolaeSize.ZERO_TINY.getValue(), 0, AreolaeSize.FOUR_MASSIVE.getValue(), 1, "crotchNippleAreolaSize");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "胯部储乳量", "%UNIT_VOLUME", Lactation.ZERO_NONE.getMinimumValue(), 0, Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue(), 10, "crotchNippleMilkStorage");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "胯部泌乳再生", "%UNIT_VOLUME", FluidRegeneration.ZERO_SLOW.getMinimumRegenerationValuePerDay(), 0, FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay(), 100, "crotchNippleMilkRegen");
                sliders.get(setting).add(slider);
            }
            if (setting == FACE) {
                slider = getSliderForNPC(npc, "嘴唇大小", "!LipSize", LipSize.ZERO_THIN.getValue(), 0, LipSize.SEVEN_ABSURD.getValue(), 1, "lipSize");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "舌头长度", "%UNIT_SIZE", TongueLength.ZERO_NORMAL.getMinimumValue(), 1, TongueLength.FOUR_ABSURDLY_LONG.getMaximumValue(), 1, "tongueLength");
                sliders.get(setting).add(slider);
            }
            if (setting == VAGINA) {
                slider = getSliderForNPC(npc, "阴蒂周长", "!PenetrationGirth(%UNIT_SIZE)", PenetrationGirth.ZERO_THIN.getValue(), 0, PenetrationGirth.SEVEN_FAT.getValue(), 1, "clitorisGirth");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "阴蒂大小", "!ClitorisSize(%UNIT_SIZE)", ClitorisSize.ZERO_AVERAGE.getMinimumValue(), 0, ClitorisSize.SEVEN_STALLION.getMaximumValue(), 1, "clitorisSize");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "阴唇大小", "!LabiaSize", LabiaSize.ZERO_TINY.getValue(), 0, LabiaSize.FOUR_MASSIVE.getValue(), 1, "labiaSize");
                sliders.get(setting).add(slider);
            }
            if (setting == ASS) {
                slider = getSliderForNPC(npc, "臀部大小", "!AssSize", AssSize.ZERO_FLAT.getValue(), 0, AssSize.SEVEN_GIGANTIC.getValue(), 1, "assSize");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "髋部大小", "!HipSize", HipSize.ZERO_NO_HIPS.getValue(), 0, HipSize.SEVEN_ABSURDLY_WIDE.getValue(), 1, "hipSize");
                sliders.get(setting).add(slider);
            }
            if (setting == PENIS) {
                slider = getSliderForNPC(npc, "阴茎周长", "!PenetrationGirth(%UNIT_SIZE)", PenetrationGirth.ZERO_THIN.getValue(), 0, PenetrationGirth.SEVEN_FAT.getValue(), 1, "penileGirth");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "精液存量", "%UNIT_VOLUME", CumProduction.ZERO_NONE.getMinimumValue(), 0, CumProduction.SEVEN_MONSTROUS.getMaximumValue(), 1, "cumStorage");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "排精量", "%UNIT_VOLUME", FluidExpulsion.ZERO_NONE.getMinimumValue(), 0, FluidExpulsion.FOUR_HUGE.getMaximumValue(), 1, "cumExpulsion");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, "睾丸大小", "!TesticleSize", TesticleSize.ZERO_VESTIGIAL.getValue(), 0, TesticleSize.SEVEN_ABSURD.getValue(), 1, "testicleSize");
                sliders.get(setting).add(slider);
            }
            if (setting != PENIS && (setting != BREAST || Main.getProperties().hasValue(PropertyValue.nipplePenContent))) {
                slider = getSliderForNPC(npc, valueName + "弹性", "!Elasticity", OrificeElasticity.ZERO_UNYIELDING.getValue(), 0, OrificeElasticity.SEVEN_ELASTIC.getValue(), 1, orifice + "Elasticity");
                sliders.get(setting).add(slider);
                if (setting != PENILE_URETHRA && setting != VAGINAL_URETHRA) {
                    slider = getSliderForNPC(npc, valueName + "湿润度", "!Wetness", Wetness.ZERO_DRY.getValue(), 0, Wetness.SEVEN_DROOLING.getValue(), 1, orifice + "Wetness");
                    sliders.get(setting).add(slider);
                }
                slider = getSliderForNPC(npc, valueName + "可塑性", "!Plasticity", OrificePlasticity.ZERO_RUBBERY.getValue(), 0, OrificePlasticity.SEVEN_MOULDABLE.getValue(), 1, orifice + "Plasticity");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, valueName + "深度", "!Depth(%UNIT_SIZE)", OrificeDepth.ZERO_EXTREMELY_SHALLOW.getValue(), 0, OrificeDepth.SEVEN_FATHOMLESS.getValue(), 1, orifice + "Depth");
                sliders.get(setting).add(slider);
                slider = getSliderForNPC(npc, valueName + "容量", "!Capacity÷(%UNIT_SIZE)", (int) Capacity.ZERO_IMPENETRABLE.getMinimumValue(), 0, (int) Capacity.SEVEN_GAPING.getMaximumValue(true) * 10, 1, orifice + "Capacity");
                sliders.get(setting).add(slider);
            }
            if (orifice.equals("penile")) {
                slider = getSliderForNPC(npc, valueName + "长度", "!PenisLength(%UNIT_SIZE)", PenisLength.ZERO_MICROSCOPIC.getMinimumValue(), 0, PenisLength.SEVEN_STALLION.getMaximumValue(), 1, orifice + "Length");
                sliders.get(setting).add(slider);
            }
            if (orifice.equals("oral")) {
                slider = getSliderForNPC(npc, "舌头长度", "!TongueLength(%UNIT_SIZE)", TongueLength.ZERO_NORMAL.getMinimumValue(), 0, TongueLength.FOUR_ABSURDLY_LONG.getMaximumValue(), 1, "tongueLength");
                sliders.get(setting).add(slider);
            }
        }
    }

    public static void save() {
        if (CURRENTLY_ACTIVE_NPC != null) {
            try {
                getNMO(CURRENTLY_ACTIVE_NPC).save(CURRENTLY_ACTIVE_NPC);
            } catch (Exception err) { err.printStackTrace(System.err); }
        }
    }
}