package com.lilithsthrone.LolificationProject.Lexi;

import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import org.w3c.dom.events.EventTarget;

public class TussyListeners {
    // Tail Pussy
    public static void initTussyCapacityListeners() {
        for (com.lilithsthrone.game.character.body.valueEnums.Capacity capacity : com.lilithsthrone.game.character.body.valueEnums.Capacity.getCapacityListFromPreferences()) {
            String id = "TUSSY_CAPACITY_"+capacity;
            if (com.lilithsthrone.controller.MainController.document.getElementById(id) != null) {
                ((EventTarget) com.lilithsthrone.controller.MainController.document.getElementById(id)).addEventListener("click", e->{
                    BodyChanging.getTarget().setTussyCapacity(capacity.getMedianValue(), true);
                    com.lilithsthrone.main.Main.game.setContent(new com.lilithsthrone.game.dialogue.responses.Response("", "", com.lilithsthrone.main.Main.game.getCurrentDialogueNode()));
                }, false);
            }
        }
    }

    public static void initTussyDepthListeners() {
        for (com.lilithsthrone.game.character.body.valueEnums.OrificeDepth depth : com.lilithsthrone.game.character.body.valueEnums.OrificeDepth.values()) {
            String id = "TUSSY_DEPTH_"+depth;
            if (com.lilithsthrone.controller.MainController.document.getElementById(id) != null) {
                ((EventTarget) com.lilithsthrone.controller.MainController.document.getElementById(id)).addEventListener("click", e->{
                    BodyChanging.getTarget().setTussyDepth(depth.getValue());
                    com.lilithsthrone.main.Main.game.setContent(new com.lilithsthrone.game.dialogue.responses.Response("", "", com.lilithsthrone.main.Main.game.getCurrentDialogueNode()));
                }, false);
            }
        }
    }

    public static void initTussyElasticityListeners() {
        for (com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity elasticity : com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity.values()) {
            String id = "TUSSY_ELASTICITY_"+elasticity;
            if (com.lilithsthrone.controller.MainController.document.getElementById(id) != null) {
                ((EventTarget) com.lilithsthrone.controller.MainController.document.getElementById(id)).addEventListener("click", e->{
                    BodyChanging.getTarget().setTussyElasticity(elasticity.getValue());
                    com.lilithsthrone.main.Main.game.setContent(new com.lilithsthrone.game.dialogue.responses.Response("", "", com.lilithsthrone.main.Main.game.getCurrentDialogueNode()));
                }, false);
            }
        }
    }

    public static void initTussyModifierListeners() {
        for (com.lilithsthrone.game.character.body.valueEnums.OrificeModifier orificeMod : com.lilithsthrone.game.character.body.valueEnums.OrificeModifier.values()) {
            String id = "TUSSY_MOD_"+orificeMod;
            if (com.lilithsthrone.controller.MainController.document.getElementById(id) != null) {
                ((EventTarget) com.lilithsthrone.controller.MainController.document.getElementById(id)).addEventListener("click", e->{
                    if (BodyChanging.getTarget().hasTussyOrificeModifier(orificeMod)) {
                        BodyChanging.getTarget().removeTussyOrificeModifier(orificeMod);
                    } else {
                        BodyChanging.getTarget().addTussyOrificeModifier(orificeMod);
                    }
                    com.lilithsthrone.main.Main.game.setContent(new com.lilithsthrone.game.dialogue.responses.Response("", "", com.lilithsthrone.main.Main.game.getCurrentDialogueNode()));
                }, false);
                com.lilithsthrone.controller.MainController.addTooltipListeners(id, new com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener().setInformation(
                        com.lilithsthrone.utils.Util.capitaliseSentence(orificeMod.getName()),
                        (orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription()));
            }
        }
    }

    public static void initTussyPlasticityListeners() {
        for (com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity plasticity : com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity.values()) {
            String id = "TUSSY_PLASTICITY_"+plasticity;
            if (com.lilithsthrone.controller.MainController.document.getElementById(id) != null) {
                ((EventTarget) com.lilithsthrone.controller.MainController.document.getElementById(id)).addEventListener("click", e->{
                    BodyChanging.getTarget().setTussyPlasticity(plasticity.getValue());
                    com.lilithsthrone.main.Main.game.setContent(new com.lilithsthrone.game.dialogue.responses.Response("", "", com.lilithsthrone.main.Main.game.getCurrentDialogueNode()));
                }, false);
            }
        }
    }

    public static void initTussyWetnessListeners() {
        for (com.lilithsthrone.game.character.body.valueEnums.Wetness wetness : com.lilithsthrone.game.character.body.valueEnums.Wetness.values()) {
            String id = "TUSSY_WETNESS_"+wetness;
            if (com.lilithsthrone.controller.MainController.document.getElementById(id) != null) {
                ((EventTarget) com.lilithsthrone.controller.MainController.document.getElementById(id)).addEventListener("click", e->{
                    BodyChanging.getTarget().setTussyWetness(wetness.getValue());
                    com.lilithsthrone.main.Main.game.setContent(new com.lilithsthrone.game.dialogue.responses.Response("", "", com.lilithsthrone.main.Main.game.getCurrentDialogueNode()));
                }, false);
            }
        }
    }

}
