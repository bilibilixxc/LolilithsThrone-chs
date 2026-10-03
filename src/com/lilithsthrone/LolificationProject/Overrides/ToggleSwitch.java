package com.lilithsthrone.LolificationProject.Overrides;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import org.w3c.dom.events.EventTarget;

public class ToggleSwitch {
    final String OPTION_TITLE, OPTION_ID, OPTION_DESCRIPTION;
    final Colour OPTION_COLOUR;
    final Runnable OPTION_RUNNABLE;

    public ToggleSwitch(String optionTitle, String optionDescription, Runnable optionRunnable) {
        OPTION_TITLE = optionTitle;
        OPTION_ID = optionTitle.toUpperCase().replaceAll(" ", "_");
        OPTION_DESCRIPTION = optionDescription;
        OPTION_RUNNABLE = optionRunnable;
        OPTION_COLOUR = PresetColour.BASE_PINK;
    }

    public String getDiv(boolean enabled) {
        return getContentPreferenceDiv(OPTION_ID, OPTION_COLOUR, OPTION_TITLE, OPTION_DESCRIPTION, enabled);
    }

    public void addListener() {
        createToggleListener(OPTION_ID, OPTION_RUNNABLE);
    }

    private static void createToggleListener(String id, Runnable runnable) {
        if (MainController.document.getElementById(id + "_ON") != null) {
            ((EventTarget) MainController.document.getElementById(id + "_ON")).addEventListener("click", e -> {
                if (runnable != null) runnable.run();
                updateUIButton(id);
            }, false);
        }
        if (MainController.document.getElementById(id + "_OFF") != null) {
            ((EventTarget) MainController.document.getElementById(id + "_OFF")).addEventListener("click", e -> {
                if (runnable != null) runnable.run();
                updateUIButton(id);
            }, false);
        }
    }
    private static void updateUIButton(String id) {
//        MainController.document.getElementById(id + "_OFF").setAttribute("class", !Main.getProperties().hasValue(fieldName) ? "normal-button selected" : "normal-button");
//        MainController.document.getElementById(id + "_ON").setAttribute("class", Main.getProperties().hasValue(fieldName) ? "normal-button selected" : "normal-button");
        Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
    }


    private static String getContentPreferenceDiv(String id, Colour colour, String title, String description, boolean enabled) {
        StringBuilder contentSB = new StringBuilder();

        contentSB.append("<div class='container-full-width' style='padding:0; margin:2px 0;'><div class='container-half-width' style='width:calc(55% - 16px);'><b style='text-align:center; color:").append(colour.toWebHexString()).append(";'>").append(title).append("</b><b>:</b> ").append(description).append("</div>").append("<div class='container-half-width' style='width:calc(45% - 16px);'>");

        if (enabled)
            contentSB.append("<div id='").append(id).append("_ON' class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>").append("[style.boldGood(YES)]").append("</div>").append("<div id='").append(id).append("_OFF' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>").append("[style.colourDisabled(NO)]").append("</div>");
        else
            contentSB.append("<div id='").append(id).append("_ON' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>").append("[style.colourDisabled(YES)]").append("</div>").append("<div id='").append(id).append("_OFF' class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>").append("[style.boldBad(NO)]").append("</div>");

        contentSB.append("</div></div>");
        return contentSB.toString();
    }
}