package com.lilithsthrone.game.settings;

import com.lilithsthrone.LolificationProject.ConfigStuff.Configurations;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.BaseColour;
import com.lilithsthrone.utils.colours.Colour;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

@SuppressWarnings("unused")
public class ToggleOption {
    public final String OPTION_ID, OPTION_TITLE, OPTION_DESCRIPTION, SETTING_NAME;
    public final Colour OPTION_COLOUR;
    public final PropertyValue OPTION_PROPERTY_VALUE;
    public final Runnable OPTION_RUNNABLE;

    public ToggleOption(String id, Colour colour, String title, String description, PropertyValue field) {
        OPTION_ID = id;
        OPTION_COLOUR = colour;
        OPTION_TITLE = title;
        OPTION_DESCRIPTION = description;
        OPTION_PROPERTY_VALUE = field;
        SETTING_NAME = "";
        OPTION_RUNNABLE = null;
    }

    public ToggleOption(String id, Colour colour, String title, String description, String field) {
        OPTION_ID = id;
        OPTION_COLOUR = colour;
        OPTION_TITLE = title;
        OPTION_DESCRIPTION = description;
        OPTION_PROPERTY_VALUE = null;
        SETTING_NAME = field;
        OPTION_RUNNABLE = null;
    }

    public ToggleOption(String id, Colour colour, String title, String description, PropertyValue property, Runnable runnable) {
        OPTION_ID = id;
        OPTION_COLOUR = colour;
        OPTION_TITLE = title;
        OPTION_DESCRIPTION = description;
        OPTION_PROPERTY_VALUE = property;
        SETTING_NAME = "";
        OPTION_RUNNABLE = runnable;
    }

    public String getDiv() {
        if (SETTING_NAME.isEmpty())
            return getContentPreferenceDiv(OPTION_ID, OPTION_COLOUR, OPTION_TITLE, OPTION_DESCRIPTION, Main.getProperties().hasValue(OPTION_PROPERTY_VALUE));
        else
            return getContentPreferenceDiv(OPTION_ID, OPTION_COLOUR, OPTION_TITLE, OPTION_DESCRIPTION, Configurations.getBoolValue(SETTING_NAME));
    }

    public void addListener() {
        if (SETTING_NAME.isEmpty()) {
            createToggleListener(OPTION_ID, OPTION_PROPERTY_VALUE, OPTION_RUNNABLE);
        } else createToggleListener(OPTION_ID, SETTING_NAME, OPTION_RUNNABLE);
    }

    private static void createToggleListener(String id, PropertyValue property, Runnable runnable) {
        if (MainController.document.getElementById(id + "_ON") != null) {
            ((EventTarget) MainController.document.getElementById(id + "_ON")).addEventListener("click", e -> {
                if (property != null)
                    Main.getProperties().setValue(property, true);
                if (runnable != null) runnable.run();
                updateUIButton(id, property);
            }, false);
        }
        if (MainController.document.getElementById(id + "_OFF") != null) {
            ((EventTarget) MainController.document.getElementById(id + "_OFF")).addEventListener("click", e -> {
                if (property != null)
                    Main.getProperties().setValue(property, false);
                if (runnable != null) runnable.run();
                updateUIButton(id, property);
            }, false);
        }
    }

    private static void createToggleListener(String id, String fieldName, Runnable runnable) {
        if (MainController.document.getElementById(id + "_ON") != null) {
            ((EventTarget) MainController.document.getElementById(id + "_ON")).addEventListener("click", e -> {
                Configurations.setValue(fieldName, true);
                if (runnable != null) runnable.run();
                updateUIButton(id, fieldName);
            }, false);
        }
        if (MainController.document.getElementById(id + "_OFF") != null) {
            ((EventTarget) MainController.document.getElementById(id + "_OFF")).addEventListener("click", e -> {
                Configurations.setValue(fieldName, false);
                if (runnable != null) runnable.run();
                updateUIButton(id, fieldName);
            }, false);
        }
    }

    private static void updateUIButton(String id, PropertyValue fieldName) {
        Main.saveProperties();
        boolean selected = Main.getProperties().hasValue(fieldName);
        MainController.document.getElementById(id + "_OFF").setAttribute("class", !Main.getProperties().hasValue(fieldName) ? "normal-button selected" : "normal-button");
        MainController.document.getElementById(id + "_ON").setAttribute("class", Main.getProperties().hasValue(fieldName) ? "normal-button selected" : "normal-button");
        setSelectedColours(id, selected);
    }

    private static void updateUIButton(String id, String fieldName) {
        Configurations.saveConfiguration();
        boolean selected = Configurations.getBoolValue(fieldName);
        MainController.document.getElementById(id + "_OFF").setAttribute("class", !selected ? "normal-button selected" : "normal-button");
        MainController.document.getElementById(id + "_ON").setAttribute("class", selected ? "normal-button selected" : "normal-button");
        setSelectedColours(id, selected);
    }

    private static void setSelectedColours(String id, boolean selected) {
        Element span_on = ((Element) MainController.document.getElementById(id + "_ON").getFirstChild());
        Element span_off = ((Element) MainController.document.getElementById(id + "_OFF").getFirstChild());
        span_on.setAttribute("style", "color:" + (selected ? BaseColour.GREEN : BaseColour.GREY).toWebHexString());
        span_off.setAttribute("style", "color:" + (!selected ? BaseColour.RED : BaseColour.GREY).toWebHexString());
    }

    private static String getContentPreferenceDiv(String id, Colour colour, String title, String description, boolean enabled) {
        StringBuilder contentSB = new StringBuilder();

        contentSB.append("<div class='container-full-width' style='padding:0; margin:2px 0;'><div class='container-half-width' style='width:calc(55% - 16px);'><b style='text-align:center; color:").append(colour.toWebHexString()).append(";'>").append(title).append("</b><b>:</b> ").append(description).append("</div>").append("<div class='container-half-width' style='width:calc(45% - 16px);'>");

        if (enabled)
            contentSB.append("<div id='").append(id).append("_ON' class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>").append("[style.boldGood(ON)]").append("</div>").append("<div id='").append(id).append("_OFF' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>").append("[style.colourDisabled(OFF)]").append("</div>");
        else
            contentSB.append("<div id='").append(id).append("_ON' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>").append("[style.colourDisabled(ON)]").append("</div>").append("<div id='").append(id).append("_OFF' class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>").append("[style.boldBad(OFF)]").append("</div>");

        contentSB.append("</div></div>");
        return contentSB.toString();
    }
}