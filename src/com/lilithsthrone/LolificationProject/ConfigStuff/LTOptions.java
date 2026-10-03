package com.lilithsthrone.LolificationProject.ConfigStuff;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.SubspeciesPreference;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.settings.ContentPreferenceValue;
import com.lilithsthrone.game.settings.DifficultyLevel;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import org.w3c.dom.events.EventTarget;

import static com.lilithsthrone.game.character.GameCharacter.MAXIMUM_AGE;
import static com.lilithsthrone.game.character.GameCharacter.MINIMUM_AGE;

public class LTOptions {


    public static void initAgeListeners() {
        for (AgeCategory ageCat : AgeCategory.values()) {
            for (PronounType pronoun : PronounType.values()) {
                if (ageCat.getMaximumValue() <= MINIMUM_AGE || ageCat.getMinimumValue() >= MAXIMUM_AGE) {
                    Main.getProperties().agePreferencesMap.get(pronoun).put(ageCat, 0);
                    Main.saveProperties();
                    continue;
                }
                for (ContentPreferenceValue preference : ContentPreferenceValue.values()) {
                    if (MainController.document.getElementById(pronoun + "_" + preference + "_" + ageCat) != null) {
                        ((EventTarget) MainController.document.getElementById(pronoun + "_" + preference + "_" + ageCat)).addEventListener("click", e -> {
                            Main.getProperties().agePreferencesMap.get(pronoun).put(ageCat, preference.getValue());
                            Main.saveProperties();
                            Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                        }, false);
                    }
                }
            }
        }
    }

    public static String getAgePreferencesPanel(PronounType type) {

        StringBuilder sb = new StringBuilder();

        sb.append("<div class='container-full-width' style='text-align:center;'><p><b style='color:").append(type.getColour().toWebHexString()).append(";'>").append(Util.capitaliseSentence(type.getName())).append("</b></p>");

        int i = AgeCategory.values().length - 1;
        for (AgeCategory ageCat : AgeCategory.values()) {
            if (ageCat.getMaximumValue() <= MINIMUM_AGE || ageCat.getMinimumValue() >= MAXIMUM_AGE) continue;
            sb.append("<div style='display:inline-block; margin:4px auto;width:100%;'><div style='display:inline-block; margin:0 auto;'><div style='width:140px; float:left;'><b style='color:").append(type.getColour().getShades(AgeCategory.values().length)[i]).append(";'>").append(Util.capitaliseSentence(ageCat.getName())).append("</b></div>");

            for (ContentPreferenceValue preference : ContentPreferenceValue.values()) {
                sb.append("<div id='").append(type).append("_").append(preference).append("_").append(ageCat).append("' class='preference-button").append(Main.getProperties().agePreferencesMap.get(type).get(ageCat) == preference.getValue() ? " selected" : "").append("'>").append(Util.capitaliseSentence(preference.getName())).append("</div>");
            }

            sb.append("</div></div><hr/>");
            i--;
        }

        sb.append(OptionsDialogue.getAgeRepresentation(type)).append("</div>");
        return sb.toString();
    }

    public static String getPrefDiv(String id, boolean disabled, boolean selected, String baseStyle, Colour colour, String svg) {
        return "<div id='" + id + "' class='square-button small" + (disabled ? " disabled" : "") + (selected ? "selected'" : "'") + " style='" + baseStyle + (selected ? " border-color:" + colour.toWebHexString() + ";" : "")
                + "'>" + getPrefSVGDiv(svg) + "</div>";
    }

    public static String getPrefSVGDiv(String svg) {
        return "<div class='square-button-content'>" + svg + "</div>";
    }

    public static void refreshPreferenceDiv(FurryPreference pref, String finalId, boolean disabled, boolean selected) {
        String baseStyle = "max-width:30px; width:14%; margin:0 1%; padding:0;";
        MainController.document.getElementById(finalId).setAttribute("class", "square-button small" + (disabled ? " disabled" : "") + (selected ? "selected" : ""));
        MainController.document.getElementById(finalId).setAttribute("style", baseStyle + (selected ? " border-color:" + pref.getColour().toWebHexString() + ";" : ""));
        MainController.document.getElementById(finalId).setNodeValue(getPrefSVGDiv(pref.getSVGImage(!selected)));
    }

    public static void refreshPreferenceDiv(SubspeciesPreference pref, String finalId, boolean disabled, boolean selected, boolean female) {
        String baseStyle = "max-width:30px; width:14%; margin:0 1%; padding:0;";
        MainController.document.getElementById(finalId).setAttribute("class", "square-button small" + (disabled ? " disabled" : "") + (selected ? "selected" : ""));
        Colour col = female ? PresetColour.FEMININE_PLUS : PresetColour.MASCULINE_PLUS;
        MainController.document.getElementById(finalId).setAttribute("style", baseStyle + (selected ? " border-color:" + col.toWebHexString() + ";" : ""));
        MainController.document.getElementById(finalId).setNodeValue(getPrefSVGDiv(pref.getSVGImage(!selected)));
    }

    public static void initOptionsListeners() { // FAETODO Add other options menu entries here and convert them to buttons as well
        for (DifficultyLevel dl : DifficultyLevel.values()) {
            String id = "DIFFICULTY_LEVEL_" + dl.toString();
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
                    if (Main.getProperties().difficultyLevel != dl) {
                        Main.getProperties().difficultyLevel = dl;
                    }
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
        }
    }
}
