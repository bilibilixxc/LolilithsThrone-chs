package com.lilithsthrone.LolificationProject.Experiments.NMS;

import com.lilithsthrone.LolificationProject.Experiments.NMS.NMSElements.NPCOptionSlider;
import com.lilithsthrone.LolificationProject.Experiments.NMS.NMSElements.NPCOptionToggle;
import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

import static com.lilithsthrone.LolificationProject.Experiments.NMS.NMSController.*;
import static com.lilithsthrone.LolificationProject.Experiments.NMS.NMSController.createSliders;

/* Friendly Note : NMS is to be pronounced Nimis */
public class NMSUI {

    static void buildRightSide() {
        StringBuilder panel = new StringBuilder();

        for (SettingType value : SettingType.values()) {
            for (NPCOptionToggle npcOptionToggle : toggles.get(value)) {
                panel.append(npcOptionToggle.getDiv());
            }
        }
        Main.mainController.setRightPanelContent(panel.toString());
    }

    static void buildLeftSide() {
        StringBuilder panel = new StringBuilder(4096);
        for (LocationType value : LocationType.values()) {
            panel.append("<details style='user-select: none; background-color: #252525;'><summary style='display: flex;'>").append(Util.capitaliseSentence(value.name().toLowerCase())).append("</summary>");
            for (Class<? extends NPC> npcClazz : NPCModSys.allNPCs.get(value)) {
                panel.append("<button id='").append(npcClazz.getSimpleName()).append("_NPCMS' style='width: 100%; background-color: #333; border-color: transparent; color: #CCC;'>").append(getName(npcClazz)).append("</button>");
            }
            panel.append("</details>");
        }
        Main.mainController.setAttributePanelContent(panel.toString());
    }

    public static void buildMiddle() {
        createSliders();
        Main.mainController.getWebEngine().executeScript("document.getElementById('NPCModSheet').innerHTML = \"<b>" + buildNMSDiv() + "</b>\";");
        addSliderListeners();
        createToggles();
        buildRightSide();
        updateVisibility();
        addToggleListeners();
    }

    private static String buildNMSDiv() {
        StringBuilder npcModOptions = new StringBuilder(8192);
        for (SettingType value : SettingType.values()) {
            npcModOptions.append("<details id='").append(value.name()).append("_SLIDER_CONTAINER' style='user-select: none;'").append(filterContent(value) ? " hidden" : "").append(">").append("<summary style='display: flex;'>").append(value.getCategoryName()).append("</summary>");
            for (NPCOptionSlider npcOptionSlider : sliders.get(value)) {
                npcModOptions.append(npcOptionSlider.getDiv());
            }
            /* Loop Types and append*/
            npcModOptions.append("</details>");
        }
        return npcModOptions.toString();
    }

    public static void updateVisibility() {
        for (SettingType value : SettingType.values()) {
            String id = value.name() + "_SLIDER_CONTAINER";
            if (MainController.document.getElementById(id) != null) {
                Main.mainController.getWebEngine().executeScript("document.getElementById('" + id + "').hidden = " + filterContent(value) + ";");
            }
        }
        String id = CURRENTLY_ACTIVE_NPC.getSimpleName() + "_vaginaSquirter_TGB";
        if (MainController.documentRight.getElementById(id) != null) {
            Main.mainController.getWebEngineRight().executeScript("document.getElementById('" + id + "').hidden = " + filterContent(SettingType.VAGINA) + ";");
        }
        id = CURRENTLY_ACTIVE_NPC.getSimpleName() + "_vaginalVirginity_TGB";
        if (MainController.documentRight.getElementById(id) != null) {
            Main.mainController.getWebEngineRight().executeScript("document.getElementById('" + id + "').hidden = " + filterContent(SettingType.VAGINA) + ";");
        }
        id = CURRENTLY_ACTIVE_NPC.getSimpleName() + "_vaginalUrethraVirginity_TGB";
        if (MainController.documentRight.getElementById(id) != null) {
            Main.mainController.getWebEngineRight().executeScript("document.getElementById('" + id + "').hidden = " + filterContent(SettingType.VAGINA) + ";");
        }
        id = CURRENTLY_ACTIVE_NPC.getSimpleName() + "_penileVirginity_TGB";
        if (MainController.documentRight.getElementById(id) != null) {
            Main.mainController.getWebEngineRight().executeScript("document.getElementById('" + id + "').hidden = " + filterContent(SettingType.PENIS) + ";");
        }
        id = CURRENTLY_ACTIVE_NPC.getSimpleName() + "_penileUrethraVirginity_TGB";
        if (MainController.documentRight.getElementById(id) != null) {
            Main.mainController.getWebEngineRight().executeScript("document.getElementById('" + id + "').hidden = " + filterContent(SettingType.PENIS) + ";");
        }
        id = CURRENTLY_ACTIVE_NPC.getSimpleName() + "_spinneretVirginity_TGB";
        if (MainController.documentRight.getElementById(id) != null) {
            Main.mainController.getWebEngineRight().executeScript("document.getElementById('" + id + "').hidden = " + filterContent(SettingType.SPINNERET) + ";");
        }
        id = CURRENTLY_ACTIVE_NPC.getSimpleName() + "_crotchNippleVirginity_TGB";
        if (MainController.documentRight.getElementById(id) != null) {
            Main.mainController.getWebEngineRight().executeScript("document.getElementById('" + id + "').hidden = " + filterContent(SettingType.CROTCH_BREAST) + ";");
        }
    }
}