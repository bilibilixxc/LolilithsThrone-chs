package com.lilithsthrone.game.settings;

import com.lilithsthrone.LolificationProject.ModCommon.ModOptionsController;
import com.lilithsthrone.LolificationProject.ModCommon.OptionInputListener;
import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.colours.Colour;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import org.w3c.dom.events.EventTarget;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SliderOption {
    public final String OPTION_ID;
    public final Colour OPTION_COLOUR;
    public final String OPTION_TITLE;
    public final String OPTION_DESCRIPTION;
    public final String OPTION_DISPLAY;
    public final int OPTION_MIN_VALUE;
    public final int OPTION_MIN_STEP;
    public final int OPTION_MAX_STEP;
    public final int OPTION_VALUE_PER_STEP;
    public final String OPTION_FIELD_NAME;

    public SliderOption(String id, Colour colour, String title, String description, String display, int minVal, int maxVal, int valPerStep, String field) {
        this(id, colour, title, description, display, minVal, 0, maxVal, valPerStep, field);
    }

    public SliderOption(String id, Colour colour, String title, String description, String display, int minVal, int minStep, int maxSteps, int valPerStep, String field) {
        OPTION_ID = id;
        OPTION_COLOUR = colour;
        OPTION_TITLE = title;
        OPTION_DESCRIPTION = description;
        OPTION_DISPLAY = display;
        OPTION_MIN_VALUE = minVal;
        OPTION_MIN_STEP = minStep;
        OPTION_MAX_STEP = maxSteps;
        OPTION_VALUE_PER_STEP = valPerStep;
        OPTION_FIELD_NAME = field;
    }

    public static String getSlider(String id, int minStep, int maxStep, int valPerStep, String fieldName) {
        return "<div class='container-full-width slider-style'>" +
                "<input style='float:right; width:55%;' type='range' min=" + minStep + " max=" + maxStep + " value=" + Math.round((Main.getProperties().getIntByRef(fieldName)) / (float) valPerStep) + " class='slider' id='" + id + "_SLIDER'>" +
                "</div>";
    }

    public String getDiv() {
        return getContentPreferenceSliderDiv(OPTION_ID, OPTION_COLOUR, OPTION_TITLE, OPTION_DESCRIPTION, OPTION_DISPLAY, OPTION_MIN_STEP, OPTION_MAX_STEP, OPTION_VALUE_PER_STEP, OPTION_FIELD_NAME);
    }

    public void addListener() {
        addSliderListeners(OPTION_ID, OPTION_FIELD_NAME, OPTION_VALUE_PER_STEP, OPTION_MIN_VALUE, OPTION_DISPLAY);
        /*addBlurListener();
        ModOptionsController.addInputListener(new OptionInputListener(OPTION_ID + "_input", () -> {
            handleValueChange();
        }, () -> {
            Platform.runLater(() -> {
                Main.mainController.getWebViewMain().requestFocus();
                String script = """
                    document.activeElement.blur();
                    document.getElementById('OPTION_ID').innerHTML = "</form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' value='VALUE_DISPLAY'>";
                    var slider = document.getElementById('OPTION_ID_SLIDER');
                    slider.value = VALUE_EXACT;
                """.replace("OPTION_ID", OPTION_ID).replace("VALUE_DISPLAY", getValueForDisplay(OPTION_DISPLAY, OPTION_FIELD_NAME)).replace("VALUE_EXACT",
                        Main.getProperties().getIntByRef(OPTION_FIELD_NAME) + "");
                Main.mainController.getWebEngine().executeScript(script);
            });
        }));*/
    }
/*
    private void handleValueChange() {
        String newVal = String.valueOf(Main.mainController.getWebEngine().executeScript("document.getElementById('" + OPTION_ID + "_input" + "').value"));
        System.out.println("Value Change Triggered : " + newVal);
        int intVal = convertValueToInt(newVal, OPTION_DISPLAY, Main.getProperties().getIntByRef(OPTION_FIELD_NAME));
        boolean overwrite = false;
        if (intVal > OPTION_MAX_STEP * OPTION_VALUE_PER_STEP) {
            overwrite = true;
            intVal = OPTION_MAX_STEP * OPTION_VALUE_PER_STEP;
        }
        if (intVal < OPTION_MIN_VALUE) {
            overwrite = true;
            intVal = OPTION_MIN_VALUE;
        }
        System.out.println("Value Is : " + intVal + " which shows as : " + getValueForDisplay(OPTION_DISPLAY, intVal));
        Main.getProperties().setIntByRef(OPTION_FIELD_NAME, intVal);
        String script = """
                    document.getElementById('OPTION_ID').innerHTML = "</form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' value='VALUE_DISPLAY'>";
                    var slider = document.getElementById('OPTION_ID_SLIDER');
                    slider.value = VALUE_EXACT;
                """.replace("OPTION_ID", OPTION_ID).replace("VALUE_DISPLAY", getValueForDisplay(OPTION_DISPLAY, OPTION_FIELD_NAME)).replace("VALUE_EXACT",
                Main.getProperties().getIntByRef(OPTION_FIELD_NAME) + "");
        if (overwrite) Platform.runLater(() -> Main.mainController.getWebEngine().executeScript(script));
        *//* Doing this allows us to change the value live, instead of redrawing the page every time *//*
        Main.saveProperties();
    }*/

    private static String getContentPreferenceSliderDiv(String id, Colour colour, String title, String description, String valueDisplay, int minStep, int maxSteps, int valPerStep, String field) {
        return "<div class='container-full-width' style='padding:0; margin:2px 0;'>" +
                    "<div class='container-half-width' style='width:calc(55% - 16px);'>" +
                        "<b style='text-align:center; margin-bottom: 3px; color:" + colour.toWebHexString() + ";'>" + title + "</b><b>:</b> " + description +
                    "</div>" +
                    "<div class='container-half-width' style='width:calc(45% - 16px);'>" +
                        "<div id='" + id + "' class='container-full-width slider-info'>" +
                            "<b>" + getValueForDisplay(valueDisplay, field) + "</b>" +
//                            "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"+
//                                "<input type='text' id='" + id + "_input' value='" + getValueForDisplay(valueDisplay, field) + "'>" +
                        "</div>" +
                        getSlider(id, minStep, maxSteps, valPerStep, field) +
                    "</div>" +
                "</div>";
    }


    static String getValueForDisplay(String display, String field) {
        return getValueForDisplay(display, Main.getProperties().getIntByRef(field));
    }
    /*public static final Pattern cupSizePattern = Pattern.compile("((?:flat)|(?:(?:training)(?:_|-)a+)|(?:(?:[a-z]+)(?:-|_)?(?:[a-z]+)?)) ?(?:cup)?");
    public static final Pattern unitLength = Pattern.compile("(\\d+)[^0-9]*(\\d+)?");

    private static int convertValueToInt(String userInputString, String displayStr, int defaultValue) {
        if (userInputString.contains("%")) {
            String v = userInputString.replaceAll("%", "");
            try {
                float temp = Float.valueOf(v);// This will handle 100% as 100, and 100.5% as 100.5
                return (int) temp;// this will discard the .5, making it 100, since we use integers for floating point
            } catch (NumberFormatException ignored) {System.err.println("User input invalid value into Slider : " + userInputString);} // Welp user error
        }
        if (userInputString.contains(".")) {
            try {
                float temp = Float.valueOf(userInputString);
                return (int) temp * 100;
            } catch (NumberFormatException ignored) {System.err.println("User input invalid value into Slider : " + userInputString);} // Don't care if not float, just lazy
        }
        if (userInputString.contains("cm") || userInputString.contains("ml")) {
            userInputString = userInputString.replaceAll("cm|ml|,|'", "");
            try {
                float temp = Float.valueOf(userInputString);
                return (int) temp;
            } catch (NumberFormatException ignored) {System.err.println("User input invalid value into Slider : " + userInputString);}
        }
        if (userInputString.contains("oz") || userInputString.contains("ounce")) {
            userInputString = userInputString.replaceAll("oz|ounces|ounce", "");
            try {
                float temp = Float.valueOf(userInputString);
                return (int) (temp * 29.5735f);// oz:ml = 1:29.5735
            } catch (NumberFormatException ignored) {System.err.println("User input invalid value into Slider : " + userInputString);}
        }
        switch (displayStr) {
            case "%CUP_SIZE" -> {
                Matcher matcher = cupSizePattern.matcher(userInputString.trim().toLowerCase());
                if (matcher.find()) {
                    try {
                        CupSize cs = CupSize.valueOf(matcher.group(1).toUpperCase());
                        return cs.getValue();
                    } catch (IllegalArgumentException ignored){System.err.println("User input invalid value into CUP SIZE Slider : " + userInputString);}// User Failure?
                }
                switch (userInputString.toLowerCase()) {
                    case "tiny" -> { return 5; }
                    case "small" -> { return 6; }
                    case "average" -> { return 7; }
                    case "big" -> { return 8; }
                    case "large" -> { return 9; }
                    case "huge" -> { return 13; }
                    case "massive" -> { return 16; }
                    default -> System.err.println("User input invalid value into CUP SIZE Slider : " + userInputString);
                }
            }
            case "%UNIT_SIZE" -> {
                Matcher matcher = unitLength.matcher(userInputString.trim().toLowerCase());
                if (matcher.find()) {
                    float unit = -1, unit2 = -1;
                    if (matcher.groupCount() == 3) {
                        unit = Integer.parseInt(matcher.group(1));
                        unit2 = Integer.parseInt(matcher.group(2));
                    }
                    if (matcher.groupCount() == 2) {
                        if (userInputString.contains("foot") || userInputString.contains("feet") || userInputString.contains("'")) {
                            unit = Integer.parseInt(matcher.group(1));
                        } else unit2 = Integer.parseInt(matcher.group(1));
                    }
                    if (unit > 0) {
                        unit2 = unit * 12;
                    }
                    if (unit2 > 0 && !Main.getProperties().hasValue(PropertyValue.metricSizes)) {
                        unit2 *= 2.54f;
                    }
                    return (int) unit2;
                }
            }
        }
        try {
            float temp = Float.parseFloat(userInputString);
            return (int) temp;
        } catch (NumberFormatException ignored)  {System.err.println("User input invalid value into Slider : " + userInputString);}
        return defaultValue;
    }*/

    static String getValueForDisplay(String display, int val) {
        String valDisplay = display.replace("%VALUE", String.valueOf(val));
        if (valDisplay.contains("$")) {
            valDisplay = valDisplay.replace("$", val > 1 ? "s" : "");
        }
        if (valDisplay.contains("$+")) {
            valDisplay = valDisplay.replace("$+", val > 0 ? "+" : "");
        }
        if (valDisplay.contains("%UNIT_SIZE")) {
            valDisplay = valDisplay.replace("%UNIT_SIZE", Units.size(val, Units.ValueType.PRECISE, Units.UnitType.SHORT));
        }
        if (valDisplay.contains("%UNIT_VOLUME")) {
            valDisplay = valDisplay.replace("%UNIT_VOLUME", Units.fluid(val, Units.ValueType.PRECISE, Units.UnitType.SHORT));
        }
        if (valDisplay.contains("%CUP_SIZE")) {
            valDisplay = valDisplay.replace("%CUP_SIZE", CupSize.getCupSizeFromInt(val).getCupSizeName());
        }
        return valDisplay;
    }

    /**
     * Adds the JS listener to the Doc for Relevant Option
     *
     * @param elementID         The HTML ID of the option
     * @param fieldName         The name of the field in Properties.java
     * @param settingValueMulti The # the slider's 'points' are stepped by
     * @param floor             The minimum value of the option (Allows setting the slider to other scaling sizes)
     */
    static void addSliderListeners(String elementID, String fieldName, int settingValueMulti, int floor, String display) {
        if (MainController.document.getElementById(elementID + "_SLIDER") == null) return;
        ((EventTarget) MainController.document.getElementById(elementID + "_SLIDER")).addEventListener("input", e -> {
            int newVal = Math.max(floor, Integer.parseInt(Main.mainController.getWebEngine().executeScript("document.getElementById('" + elementID + "_SLIDER" + "').value;").toString()) * settingValueMulti);
            Main.getProperties().setIntByRef(fieldName, newVal);
            /* Doing this allows us to change the value live, instead of redrawing the page every time */
            Main.mainController.getWebEngine().executeScript("document.getElementById('" + elementID + "').innerHTML = \"" +
                    "<b>" + getValueForDisplay(display, newVal) + "</b>"
//                    "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"+
//                    "<input type='text' id='" + elementID + "_input' value='" + getValueForDisplay(display, fieldName) + "'>"
                    +"\";");
            Main.saveProperties();
        }, false);
    }
/*
    private void addBlurListener() {
        if (MainController.document.getElementById(OPTION_ID + "_SLIDER") == null) return;
//        ((EventTarget) MainController.document.getElementById(OPTION_ID + "_SLIDER")).addEventListener("blur", e -> {
//            this.handleValueChange();
//        }, false);
        ((EventTarget) MainController.document.getElementById(OPTION_ID + "_input")).addEventListener("input", e -> {
            handleValueChange();
        }, false);
    }*/
}