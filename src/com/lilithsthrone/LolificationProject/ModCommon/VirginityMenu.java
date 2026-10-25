package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.LolificationProject.ConfigStuff.ConfigurationManager;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.settings.SliderOption;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

import java.util.ArrayList;
import java.util.HashMap;

public class VirginityMenu {
    static final HashMap<Femininity, HashMap<AgeCategory, Integer>> virginityRates = new HashMap<>();
    static final HashMap<Femininity, HashMap<AgeCategory, SliderOption>> virginityRatesSliders = new HashMap<>();
    static int vaginaVirginRate = 100;
    static int penisVirginRate = 100;
    static int analVirginRate = 80;
    static int faceVirginRate = 75;
    static int assVirginRate = 70;
    static int spinneretVirginRate = 140;
    static int nippleVirginRate = 200;
    static int nippleCrotchVirginRate = 200;
    static int urethraVirginRate = 200;
    static int vaginaUrethraVirginRate = 200;
    static SliderOption vaginaVirginSlider = new SliderOption(
                "vaginaVirgin_slider",
                PresetColour.BASE_GREY,
                "阴道处女率",
                "",
                "%VALUE%",
                0,
                200,
                1,
                "$vaginaVirgin"
            );
    static SliderOption penisVirginSlider = new SliderOption(
                "penisVirgin_slider",
                PresetColour.BASE_GREY,
                "阴茎处女率",
                "",
                "%VALUE%",
                0,
                200,
                1,
                "$penisVirgin"
            );
    static SliderOption analVirginSlider = new SliderOption(
                "analVirgin_slider",
                PresetColour.BASE_GREY,
                "肛门处女率",
                "",
                "%VALUE%",
                0,
                200,
                1,
                "$analVirgin"
            );
    static SliderOption faceVirginSlider = new SliderOption(
                "faceVirgin_slider",
                PresetColour.BASE_GREY,
                "口部处女率",
                "",
                "%VALUE%",
                0,
                200,
                1,
                "$faceVirgin"
            );
    static SliderOption assVirginSlider = new SliderOption(
                "assVirgin_slider",
                PresetColour.BASE_GREY,
                "臀部处女率",
                "",
                "%VALUE%",
                0,
                200,
                1,
                "$assVirgin"
            );
    static SliderOption spinneretVirginSlider = new SliderOption(
                "spinneretVirgin_slider",
                PresetColour.BASE_GREY,
                "丝囊处女率",
                "",
                "%VALUE%",
                0,
                200,
                1,
                "$spinneretVirgin"
            );
    static SliderOption nippleVirginSlider = new SliderOption(
                "nippleVirgin_slider",
                PresetColour.BASE_GREY,
                "乳头处女率",
                "",
                "%VALUE%",
                0,
                200,
                1,
                "$nippleVirgin"
            );
    static SliderOption nippleCrotchVirginSlider = new SliderOption(
                "nippleCrotchVirgin_slider",
                PresetColour.BASE_GREY,
                "阴部乳头处女率",
                "",
                "%VALUE%",
                0,
                200,
                1,
                "$nippleCrotchVirgin"
            );
    static SliderOption urethraVirginSlider = new SliderOption(
                "urethraVirgin_slider",
                PresetColour.BASE_GREY,
                "阴茎尿道处女率",
                "",
                "%VALUE%",
                0,
                200,
                1,
                "$urethraVirgin"
            );
    static SliderOption vaginaUrethraVirginSlider = new SliderOption(
                "vaginaUrethraVirgin_slider",
                PresetColour.BASE_GREY,
                "阴道尿道处女率",
                "",
                "%VALUE%",
                0,
                200,
                1,
                "$vaginaUrethraVirgin"
            );
    public static final DialogueNode VIRGIN_RATES_MENU = new DialogueNode("处女率", "允许你更改处女生成率", true) {

        @Override
        public String getHeaderContent() {
            UtilText.nodeContentSB.setLength(0);

            UtilText.nodeContentSB.append("<h1>特定部位的基础概率</h1><h2>这些概率会乘以年龄阶段系数</h2>");
            UtilText.nodeContentSB.append("<p>例如：阴茎率70%和阴道率110%，年龄阶段为三十多岁中期28%<br>" +
                    "意味着阴茎率是 70% * 28% = 19.6%，而阴道率是 30.8%<br>" +
                    "这些设置保存在 %userprofile%/Lilith's Throne 中，因此更改版本时会被保留</p>");
            UtilText.nodeContentSB.append(vaginaVirginSlider.getDiv());
            UtilText.nodeContentSB.append(penisVirginSlider.getDiv());
            UtilText.nodeContentSB.append(analVirginSlider.getDiv());
            UtilText.nodeContentSB.append(faceVirginSlider.getDiv());
            UtilText.nodeContentSB.append(assVirginSlider.getDiv());
            UtilText.nodeContentSB.append(spinneretVirginSlider.getDiv());
            UtilText.nodeContentSB.append(nippleVirginSlider.getDiv());
            UtilText.nodeContentSB.append(nippleCrotchVirginSlider.getDiv());
            UtilText.nodeContentSB.append(urethraVirginSlider.getDiv());
            UtilText.nodeContentSB.append(vaginaUrethraVirginSlider.getDiv());
            for (Femininity femininity : Femininity.values()) {
                UtilText.nodeContentSB.append("<h style='color:" + femininity.getColour().toWebHexString() + "';>" + femininity.toString() + "</h>");
                HashMap<AgeCategory, SliderOption> ageVirginityRates = virginityRatesSliders.get(femininity);

                for (AgeCategory ageCategory : AgeCategory.values()) {
                    if (ageCategory.getMinimumValue() == ageCategory.getMaximumValue()) continue;
                    UtilText.nodeContentSB.append(ageVirginityRates.get(ageCategory).getDiv());
                }
                UtilText.nodeContentSB.append("<br><br>");
            }

            return UtilText.nodeContentSB.toString();
        }

        @Override
        public String getContent() {
            return "";
        }

        public Response getResponse(int responseTab, int index) {
            return ModOptions.getModOptionsResponses(index);
        }
    };

    static {
        for (Femininity femininity : Femininity.values()) {
            HashMap<AgeCategory, Integer> ageVirginityRates = new HashMap<>();
            HashMap<AgeCategory, SliderOption> ageVirginityRateSliders = new HashMap<>();
            ageVirginityRates.put(AgeCategory.INFANT, 95);
            ageVirginityRates.put(AgeCategory.TODDLER, 95);
            ageVirginityRates.put(AgeCategory.CHILD, 90);
            ageVirginityRates.put(AgeCategory.PRETEENS, 80);
            ageVirginityRates.put(AgeCategory.TEENS_EARLY, 75);
            ageVirginityRates.put(AgeCategory.TEENS_MIDDLE, 70);
            ageVirginityRates.put(AgeCategory.TEENS_LATE, 65);
            ageVirginityRates.put(AgeCategory.TWENTIES_EARLY, 60);
            ageVirginityRates.put(AgeCategory.TWENTIES_MIDDLE, 50);
            ageVirginityRates.put(AgeCategory.TWENTIES_LATE, 40);
            ageVirginityRates.put(AgeCategory.THIRTIES_EARLY, 30);
            ageVirginityRates.put(AgeCategory.THIRTIES_MIDDLE, 28);
            ageVirginityRates.put(AgeCategory.THIRTIES_LATE, 26);
            ageVirginityRates.put(AgeCategory.FORTIES_EARLY, 24);
            ageVirginityRates.put(AgeCategory.FORTIES_MIDDLE, 22);
            ageVirginityRates.put(AgeCategory.FORTIES_LATE, 20);
            ageVirginityRates.put(AgeCategory.FIFTIES_EARLY, 20);
            ageVirginityRates.put(AgeCategory.FIFTIES_MIDDLE, 10);
            ageVirginityRates.put(AgeCategory.FIFTIES_LATE, 10);
            ageVirginityRates.put(AgeCategory.SIXTIES_EARLY, 10);
            ageVirginityRates.put(AgeCategory.SIXTIES_MIDDLE, 10);
            ageVirginityRates.put(AgeCategory.SIXTIES_LATE, 10);
            ageVirginityRates.put(AgeCategory.SIXTIES_PLUS, 10);
            virginityRates.put(femininity, ageVirginityRates);
            for (AgeCategory ageCategory : AgeCategory.values()) {
                SliderOption sliderOption = new SliderOption(
                        femininity.toString() + "_" + ageCategory.toString() + "_VR",
                        ageCategory.getColour(),
                        Util.capitaliseSentence(ageCategory.getName()) + "处率",
                        "",
                        "%VALUE%",
                        0,
                        100,
                        1,
                        "$" + femininity.toString() + "|" + ageCategory.toString()
                );
                ageVirginityRateSliders.put(ageCategory, sliderOption);
            }
            virginityRatesSliders.put(femininity, ageVirginityRateSliders);
        }
    }

    public static float getVirginityRate(Femininity fem, int AgeInMonths, String bodyPart) {
        AgeCategory aCat1 = AgeCategory.valueOf((int)(AgeInMonths / 12));
        /* The next age category after the one the NPC belongs to */
        AgeCategory aCat2 = aCat1 == AgeCategory.SIXTIES_PLUS ? aCat1 : AgeCategory.valueOf(aCat1.getMaximumValue() + 1);
        /* This is how close someone is to the next age category, a 22 year old is 50% of the way to "Twenties Middle" 24-26 years old */
        float relativeAge = AgeInMonths / ((aCat1 == AgeCategory.SIXTIES_PLUS ? aCat2.getMaximumValue() : aCat2.getMinimumValue()) * 12f);
        Logger.logVirginCalc("Relative Age is : " + String.format("%.1f", relativeAge));
        /* The virginity rate for the NPC's current age category */
        float baseRate1 = virginityRates.get(fem).get(aCat1) * 0.01f;
        Logger.logVirginCalc("Base rate for Age " + aCat1.getName() + " is : " + String.format("%.1f", baseRate1));
        /* The Virginity Rate for the NPC's next age category */
        float baseRate2 = virginityRates.get(fem).get(aCat2) * 0.01f;
        Logger.logVirginCalc("Base rate for Age " + aCat2.getName() + " is : " + String.format("%.1f", baseRate2));
        /* This is a Lerp operation of current age category virginity rate to next age category virginity rate, if 80% and 70%, and the above 50%, 75% will be used */
        float baseRate = baseRate1 + ((baseRate2 - baseRate1) * relativeAge);// lerp
        Logger.logVirginCalc("Base rate for npc is : " + String.format("%.1f", baseRate));
        return switch (bodyPart) {
            case "vaginaVirgin" -> {
                    float vaginaVRate = (vaginaVirginRate * 0.01f);
                    Logger.logVirginCalc("vaginaVirgin rate is : " + vaginaVRate);
                    yield Math.max(0f, Math.min(1.0f, vaginaVRate * baseRate));
            }
            case "penisVirgin" -> {
                    float penisVRate = (penisVirginRate * 0.01f);
                    Logger.logVirginCalc("penisVirgin rate is : " + penisVRate);
                    yield Math.max(0f, Math.min(1.0f, penisVRate * baseRate));
            }
            case "analVirgin" -> {
                    float analVRate = (analVirginRate * 0.01f);
                    Logger.logVirginCalc("analVirgin rate is : " + analVRate);
                    yield Math.max(0f, Math.min(1.0f, analVRate * baseRate));
            }
            case "faceVirgin" -> {
                    float faceVRate = (faceVirginRate * 0.01f);
                    Logger.logVirginCalc("faceVirgin rate is : " + faceVRate);
                    yield Math.max(0f, Math.min(1.0f, faceVRate * baseRate));
            }
            case "assVirgin" -> {
                    float assVRate = (assVirginRate * 0.01f);
                    Logger.logVirginCalc("assVirgin rate is : " + assVRate);
                    yield Math.max(0f, Math.min(1.0f, assVRate * baseRate));
            }
            case "spinneretVirgin" -> {
                    float spinneretVRate = (spinneretVirginRate * 0.01f);
                    Logger.logVirginCalc("spinneretVirgin rate is : " + spinneretVRate);
                    yield Math.max(0f, Math.min(1.0f, spinneretVRate * baseRate));
            }
            case "nippleVirgin" -> {
                    float nippleVRate = (nippleVirginRate * 0.01f);
                    Logger.logVirginCalc("nippleVirgin rate is : " + nippleVRate);
                    yield Math.max(0f, Math.min(1.0f, nippleVRate * baseRate));
            }
            case "nippleCrotchVirgin" -> {
                    float nippleCrotchVRate = (nippleCrotchVirginRate * 0.01f);
                    Logger.logVirginCalc("nippleCrotchVirgin rate is : " + nippleCrotchVRate);
                    yield Math.max(0f, Math.min(1.0f, nippleCrotchVRate * baseRate));
            }
            case "urethraVirgin" -> {
                    float urethraVRate = (urethraVirginRate * 0.01f);
                    Logger.logVirginCalc("urethraVirgin rate is : " + urethraVRate);
                    yield Math.max(0f, Math.min(1.0f, urethraVRate * baseRate));
            }
            case "vaginaUrethraVirgin" -> {
                float vaginaUrethraVRate = (vaginaUrethraVirginRate * 0.01f);
                Logger.logVirginCalc("vaginaUrethraVirgin rate is : " + vaginaUrethraVRate);
                yield Math.max(0f, Math.min(1.0f, vaginaUrethraVRate * baseRate));
            }
            default -> 0.5f;
        };
    }

    public static int getVirginityRate(String id) {
        if (!id.contains("|")) {
            return switch (id) {
                case "$vaginaVirgin" -> vaginaVirginRate;
                case "$penisVirgin" -> penisVirginRate;
                case "$analVirgin" -> analVirginRate;
                case "$faceVirgin" -> faceVirginRate;
                case "$assVirgin" -> assVirginRate;
                case "$spinneretVirgin" -> spinneretVirginRate;
                case "$nippleVirgin" -> nippleVirginRate;
                case "$nippleCrotchVirgin" -> nippleCrotchVirginRate;
                case "$urethraVirgin" -> urethraVirginRate;
                case "$vaginaUrethraVirgin" -> vaginaUrethraVirginRate;
                default -> 0;
            };
        }
        String[] rateParts = id.replace("$", "").split("\\|");
        Femininity fem = Femininity.valueOf(rateParts[0]);
        AgeCategory ageCat = AgeCategory.valueOf(rateParts[1]);
        return virginityRates.get(fem).get(ageCat);
    }

    public static void setVirginityRate(String id, int num) {
        if (!id.contains("|")) {
            switch (id) {
                case "$vaginaVirgin" -> vaginaVirginRate = num;
                case "$penisVirgin" -> penisVirginRate = num;
                case "$analVirgin" -> analVirginRate = num;
                case "$faceVirgin" -> faceVirginRate = num;
                case "$assVirgin" -> assVirginRate = num;
                case "$spinneretVirgin" -> spinneretVirginRate = num;
                case "$nippleVirgin" -> nippleVirginRate = num;
                case "$nippleCrotchVirgin" -> nippleCrotchVirginRate = num;
                case "$urethraVirgin" -> urethraVirginRate = num;
                case "$vaginaUrethraVirgin" -> vaginaUrethraVirginRate = num;
            };
            return;
        }
        String[] rateParts = id.replace("$", "").split("\\|");
        Femininity fem = Femininity.valueOf(rateParts[0]);
        AgeCategory ageCat = AgeCategory.valueOf(rateParts[1]);
        virginityRates.get(fem).replace(ageCat, num);
    }

    public static void saveVirginities() {
        ArrayList<String> values = new ArrayList<>();
        for (Femininity femininity : Femininity.values()) {
            for (AgeCategory ageCategory : AgeCategory.values()) {
                String val = "$" + femininity.toString() + "|" + ageCategory.toString();
                values.add(val + " = " + getVirginityRate(val));
            }
        }
        values.add("$vaginaVirgin = " + vaginaVirginRate);
        values.add("$penisVirgin = " + penisVirginRate);
        values.add("$analVirgin = " + analVirginRate);
        values.add("$faceVirgin = " + faceVirginRate);
        values.add("$assVirgin = " + assVirginRate);
        values.add("$spinneretVirgin = " + spinneretVirginRate);
        values.add("$nippleVirgin = " + nippleVirginRate);
        values.add("$nippleCrotchVirgin = " + nippleCrotchVirginRate);
        values.add("$urethraVirgin = " + urethraVirginRate);
        values.add("$vaginaUrethraVirgin = " + vaginaUrethraVirginRate);
        ConfigurationManager.writeCustomConfig("virginityRates.cfg", values);
    }

    public static void loadVirginities() {
        ArrayList<String> values = ConfigurationManager.readCustomConfig("virginityRates.cfg");
        for (String value : values) {
            String[] temp = value.split(" = ");
            int val = Integer.parseInt(temp[1]);
            setVirginityRate(temp[0], val);
        }
    }
}