package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.effects.AppliedStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.main.Main;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.DRAKE_MODE;

public class ModProps {

    //	public int offspringAge = DRAKE_MODE ? 1 : 5;
//	public int offspringAgeDeviation = DRAKE_MODE ? 0 : 20;
    public static int xpMultiplier = DRAKE_MODE ? 200 : 100;
    public static int essenceMultiplier = DRAKE_MODE ? 200 : 100;
    public static int moneyMultiplier = DRAKE_MODE ? 200 : 100;
    public static int interestMultiplier = 12;
    public static int itemDropsIncrease = 1;
    public static int maxLevel = DRAKE_MODE ? 200 : 50;
    public static int maxPerks = DRAKE_MODE ? 20 : 6;
    public static int loliFertility = DRAKE_MODE ? 20 : 0;
    public static int shotaEndowment = DRAKE_MODE ? 100 : 10;
    public static int loliOppai = DRAKE_MODE ? 5 : 10;
    public static int virginsPercent = DRAKE_MODE ? 40 : 25;
    public static int heightDeviations = 0;
    public static int heightAgeCap = DRAKE_MODE ? 17 : 15;
    public static int impHMult = 65;
    public static int aimpHMult = 85;
    public static int minAge = DRAKE_MODE ? 5 : 21, maxAge = DRAKE_MODE ? 35 : 60;
    public static int playerPregDuration = 100, NPCPregDuration = 100;
    public static int minimumFuckableCupSize = 7;
    public static int NPCPregChance = DRAKE_MODE ? 0 : 5;
    public static int importAgeOffset = 0;
    public static int loliLabelAge = DRAKE_MODE ? 5 : 12,
            shotaLabelAge = DRAKE_MODE ? 5 : 12,
            youngLabelAge = DRAKE_MODE ? 19 : 17,
            oldLabelAge = DRAKE_MODE ? 30 : 40;
    public static int heterochromiaChance = DRAKE_MODE ? 10 : 5;
    public static int orgasmicLevelDrainChance = DRAKE_MODE ? 10 : 5;
    public static int chuuniChance = DRAKE_MODE ? 10 : 5;
    public static int essenceStock = DRAKE_MODE ? 10 : 350; //Keldon made the value more akin to vanilla
    public static int impHeight = 100;
    public static int alphaImpHeight = 150;
    public static int encMinorPotency = 1;
    public static int encMediumPotency = 2;
    public static int encMajorPotency = 3;
    public static int encMinorValue = 1;
    public static int encMediumValue = 2;
    public static int encMajorValue = 3;
    public static int encCostMultiplier = 100;
    public static int maxEnchantments = 100;
    public static int enchantmentInstabilityUpperLimit = 1000;
    public static int enchantmentInstabilityBonus = 0;
    public static int enchantmentInstabilityPerLevel = 10;
    public static int enchantmentInstabilityPerPhys = 1;
    public static int enchantmentInstabilityPerArcane = 1;
    public static int enchantmentInstabilityPerCorruption = 1;

    //	public int demonsEverywhereChance = 0; // FAETODO can't be fucked right now, I'll fix these later. Mod Options settings commented out for now
    // Challenge Mod Settings
    public static int inventoryTabs = 5;
    public static int inventoryRows = 5;
    public static int amorphousAgeRange = 10;
    public static int ageAppearanceRange = 5;


    public static void saveProps(Document doc, Element settings) throws IllegalAccessException {
        Field[] fields = ModProps.class.getFields();
        for (Field field : fields) {
            Main.getProperties().createXMLElementWithValue(doc, settings, field.getName(), String.valueOf(field.getInt(null)));
        }
    }


    public static void loadProps(Element element) throws IllegalAccessException {
        Field[] fields = ModProps.class.getFields();
        for (Field field : fields) {
            if (element.getElementsByTagName(field.getName()).item(0) != null) {
                field.setInt(null, Integer.parseInt(((Element)element.getElementsByTagName(field.getName()).item(0)).getAttribute("value")));
                /*field.set(null, switch (field.getType().getSimpleName().toLowerCase()) {
                    case "integer", "int" -> Integer.parseInt(element.getElementsByTagName(field.getName()).item(0).getTextContent());
                    case "long" -> Long.parseLong(element.getElementsByTagName(field.getName()).item(0).getTextContent());
                    case "double" -> Double.parseDouble(element.getElementsByTagName(field.getName()).item(0).getTextContent());
                    case "float" -> Float.parseFloat(element.getElementsByTagName(field.getName()).item(0).getTextContent());
                    case "byte", "short", "char", "boolean", "string" -> element.getElementsByTagName(field.getName()).item(0).getTextContent();
                    default ->
                            throw new IllegalStateException("Unexpected value: " + field.getType().getSimpleName().toLowerCase());
                });*/
            }
        }
    }

    public static void loadProps(XMLLoader loader) {
        try {
            Field[] fields = ModProps.class.getFields();
            for (Field field : fields) {
                field.setInt(null, loader.getElementAttributeAsInt(field.getName()));
            }
        } catch (Exception illegal) {
            System.err.println("Illegal Access from ModProps");
        }
    }

    public static boolean hasValue(PropertyValue propVal) {
        return Main.getProperties().hasValue(propVal);
    }


    public static void setIntByRef(String str, int val) {
        try {
            if (str.startsWith("$")) {
                VirginityMenu.setVirginityRate(str, val);
            }
            if (str.contains("PregDuration")) {
                int previous = getIntByRef(str);
                if (previous != val) {
                    if (str.equalsIgnoreCase("playerPregDuration") && Main.game != null && Main.game.getPlayer() != null) {
                        for (AppliedStatusEffect appliedStatusEffect : Main.game.getPlayer().getAppliedStatusEffects()) {
                            if (appliedStatusEffect.getEffect() == StatusEffect.PREGNANT_0 || appliedStatusEffect.getEffect() == StatusEffect.PREGNANT_1 ||
                                    appliedStatusEffect.getEffect() == StatusEffect.PREGNANT_2 || appliedStatusEffect.getEffect() == StatusEffect.PREGNANT_3)
                                appliedStatusEffect.setSecondsRemaining((int) (appliedStatusEffect.getSecondsRemaining() * (val / 100f)));
                            break;
                        }
                    } else if (str.equalsIgnoreCase("NPCPregDuration") && Main.game != null && Main.game.getNPCMap() != null && !Main.game.getNPCMap().isEmpty()) {
                        for (NPC npc : Main.game.getNPCMap().values())
                            for (AppliedStatusEffect appliedStatusEffect : npc.getAppliedStatusEffects()) {
                                if (appliedStatusEffect.getEffect() == StatusEffect.PREGNANT_0 || appliedStatusEffect.getEffect() == StatusEffect.PREGNANT_1 ||
                                        appliedStatusEffect.getEffect() == StatusEffect.PREGNANT_2 || appliedStatusEffect.getEffect() == StatusEffect.PREGNANT_3)
                                    appliedStatusEffect.setSecondsRemaining((int) (appliedStatusEffect.getSecondsRemaining() * (val / 100f)));
                                break;
                            }
                    }
                }
            }
            if (getFields().contains(str))
                ModProps.class.getField(str).setInt(null, val);
            else System.err.println("Invalid value for ModProps : " + str);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } // #Mandatory
    }

    public static int getIntByRef(String str) {
        try {
            if (str.startsWith("$")) {// yes this is tech debt
                return VirginityMenu.getVirginityRate(str);
            }
            return ModProps.class.getField(str).getInt(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } // #Mandatory
        return -1;
    }

    private static List<String> getFields() {
        ArrayList<String> list = new ArrayList<>();
        for (Field field : ModProps.class.getFields()) {
            list.add(field.getName());
        }
        return list;
    }

    public static boolean isPeeingEnabled() {
        return Main.getProperties().hasValue(PropertyValue.peeContent);
    }
    public static boolean isExtraSelfContentEnabled() {
        return Main.getProperties().hasValue(PropertyValue.enableExtraSelfContent);
    }
    public static boolean isTussyEnabled() {
        return Main.getProperties().hasValue(PropertyValue.enableTussyContent);
    }
    public static boolean isLeakageEnabled() {
        return Main.getProperties().hasValue(PropertyValue.combatLeakage);
    }
    public static boolean forceCleanSlots() {
        return Main.getProperties().hasValue(PropertyValue.forceClean);
    }

    public static boolean isExtremeAgeEnabled() {
        return Main.getProperties().hasValue(PropertyValue.extremeAgeContent);
    }

    public static boolean isHeightAppearanceBased() {
        return Main.getProperties().hasValue(PropertyValue.scaleHeightBasedOnAgeAppearance);
    }

    public static boolean isBodyAppearanceBased() {
        return Main.getProperties().hasValue(PropertyValue.scaleBodyBasedOnApparentAge);
    }

    public static boolean isHeightApparentGender() {
        return Main.getProperties().hasValue(PropertyValue.scaleHeightBasedOnGenderOrAppearance);
    }

    public static boolean isHugsEnabled() {
        return Main.getProperties().hasValue(PropertyValue.enableHug);
    }

    //    public static boolean isHeightExplicitlyCalculated() { return Main.getProperties().hasValue(PropertyValue.extremeCaseCalculations); }
    public static boolean isShowAgeEnabled() {
        return Main.getProperties().hasValue(PropertyValue.showAge);
    }

    public static boolean isShowTrueAgeEnabled() {
        return Main.getProperties().hasValue(PropertyValue.showTrueAge);
    }

    public static boolean isShowEnhancedDescriptionEnabled() {
        return Main.getProperties().hasValue(PropertyValue.showEnhancedDescription);
    }

    public static boolean alwaysFlagModded() {
        return Main.getProperties().hasValue(PropertyValue.importedFlagAsModded);
    }

    public static boolean changeBody() {
        return Main.getProperties().hasValue(PropertyValue.changeBody);
    }

    public static boolean changeBreasts() {
        return Main.getProperties().hasValue(PropertyValue.changeBreasts);
    }

    public static boolean changeNipples() {
        return Main.getProperties().hasValue(PropertyValue.changeNipples);
    }

    public static boolean changeHips() {
        return Main.getProperties().hasValue(PropertyValue.changeHips);
    }

    public static boolean changeVagina() {
        return Main.getProperties().hasValue(PropertyValue.changeVagina);
    }

    public static boolean changePenis() {
        return Main.getProperties().hasValue(PropertyValue.changePenis);
    }

    public static boolean changeTesticle() {
        return Main.getProperties().hasValue(PropertyValue.changeTesticle);
    }

    public static boolean changeThroat() {
        return Main.getProperties().hasValue(PropertyValue.changeThroat);
    }

    public static boolean changeAss() {
        return Main.getProperties().hasValue(PropertyValue.changeAss);
    }

    public static boolean changeAnus() {
        return Main.getProperties().hasValue(PropertyValue.changeAnus);
    }

    public static boolean changeUrethra() {
        return Main.getProperties().hasValue(PropertyValue.changeUrethra);
    }

    public static boolean characterAgeProgression() {
        return Main.getProperties().hasValue(PropertyValue.ageProgression);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean equipUniques() {
        return Main.getProperties().hasValue(PropertyValue.equipUniques);
    }


    public static boolean isHeightRelative() {
        return Main.getProperties().hasValue(PropertyValue.relativeHeight);
    }

    public static boolean lexiMenu() {
        return Main.getProperties().hasValue(PropertyValue.lexiMenuOptions);
    }

    public static boolean enslaveLumi() {
        return Main.getProperties().hasValue(PropertyValue.enslaveLumi);
    }
}
