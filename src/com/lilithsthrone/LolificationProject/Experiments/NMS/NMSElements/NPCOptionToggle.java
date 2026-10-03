package com.lilithsthrone.LolificationProject.Experiments.NMS.NMSElements;

import com.lilithsthrone.LolificationProject.Experiments.NMS.NMSController;
import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import org.w3c.dom.events.EventTarget;

import static com.lilithsthrone.LolificationProject.Experiments.NMS.NMSUI.updateVisibility;

public class NPCOptionToggle {
    public final String OPTION_ID;
    public final Class<? extends NPC> OPTION_NPC;
    public final Colour OPTION_COLOUR;
    public final String OPTION_TITLE;
    public final String OPTION_DESCRIPTION;
    public final String OPTION_FIELD;

    public NPCOptionToggle(String id, Class<? extends NPC> npc, Colour colour, String title, String description, String field) {
        OPTION_ID = id;
        OPTION_NPC = npc;
        OPTION_COLOUR = colour;
        OPTION_TITLE = title;
        OPTION_DESCRIPTION = description;
        OPTION_FIELD = field;
    }

    public String getDiv() {
        return getContentPreferenceDiv(OPTION_ID, OPTION_COLOUR, OPTION_TITLE, OPTION_DESCRIPTION, NMSController.getBVariable(OPTION_NPC, OPTION_FIELD));
    }

    public void addListener() {
        createToggleListener(OPTION_ID, OPTION_NPC, OPTION_FIELD);
    }

    private static void createToggleListener(String id, Class<? extends NPC> npc, String fieldName) {
        if (MainController.documentRight.getElementById(id) != null) {
            ((EventTarget) MainController.documentRight.getElementById(id)).addEventListener("click", e -> {
                boolean val = (Boolean) Main.mainController.getWebEngineRight().executeScript("document.getElementById('" + id + "').checked;");
                NMSController.setVariable(npc, fieldName, val);
                updateVisibility();
            }, false);
        }
        if (MainController.documentAttributes.getElementById(id) != null) {
            ((EventTarget) MainController.documentAttributes.getElementById(id)).addEventListener("click", e -> {
                boolean val = (Boolean) Main.mainController.getWebEngineAttributes().executeScript("document.getElementById('" + id + "').checked;");
                NMSController.setVariable(npc, fieldName, val);
//                buildMiddle();
            }, false);
        }
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                boolean val = (Boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('" + id + "').checked;");
                NMSController.setVariable(npc, fieldName, val);
//                buildMiddle();
            }, false);
        }
    }

    private static String getContentPreferenceDiv(String id, Colour colour, String title, String description, boolean enabled) {

        String a = "<label class='switch'>" +
                "<input id='" + id + "' type='checkbox'" + (enabled ? " checked" : "") + ">" +
                "<span class='slider round'></span>" +
                "</label>";

        String contentSB = "<div id='" + id + "_TGB' class='container-full-width' style='padding:0; margin:2px 0;'><div class='container-half-width' style='width:calc(68% - 16px);'><b style='text-align:center; color:" + colour.toWebHexString() + ";'>" + title + "</b><b>:</b> " + description + "</div>" + "<div class='container-half-width' style='width:calc(32% - 16px);'>" +
                a +
                "</div></div>";
        return contentSB;
    }
}