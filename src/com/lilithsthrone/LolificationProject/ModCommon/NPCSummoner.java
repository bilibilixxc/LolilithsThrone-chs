package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import org.w3c.dom.Element;
import org.w3c.dom.events.EventTarget;

import java.util.Comparator;
import java.util.List;

public class NPCSummoner {

    public static final DialogueNode SUMMON_NPC = new DialogueNode("Summon NPC", "Teleports an NPC to you.", true) {

        @Override
        public String getContent() {
            UtilText.nodeContentSB.setLength(0);
            List<NPC> uniqueNpcs = Main.game.getAllNPCs();
            uniqueNpcs.removeIf(n -> !n.isUnique());
            uniqueNpcs.sort(Comparator.comparing(GameCharacter::getNameIgnoresPlayerKnowledge));
            for (NPC npc : uniqueNpcs) {
                UtilText.nodeContentSB.append("<p>"
                        + PhoneDialogue.getContactEntry(npc)
                        + "<div id='SUMMON_NPC_" + Main.game.getUniqueNPCId(npc.getClass()).replace("-1,", "") + "' class='cosmetics-button'><span style='color:"
                        + PresetColour.BASE_PURPLE_LIGHT.toWebHexString()
                        + ";'>Summon " + npc.getNameIgnoresPlayerKnowledge() + "</span>" + "</div>"
                        + "<div id='RETURN_NPC_" + Main.game.getUniqueNPCId(npc.getClass()).replace("-1,", "") + "' class='cosmetics-button'><span style='color:"
                        + PresetColour.BASE_PURPLE_LIGHT.toWebHexString()
                        + ";'>Send " + npc.getNameIgnoresPlayerKnowledge() + " Home</span>" + "</div>"

                + "</p>");
            }
//            System.out.println("Content Finished.");

            return UtilText.nodeContentSB.toString();
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            List<NPC> uniqueNpcs = Main.game.getAllNPCs();
            uniqueNpcs.removeIf(n -> !n.isUnique());
            if (index == 0) {
                return new Response("Back", "Return to the phone's main menu.", PhoneDialogue.MENU);
            } else {
                return null;
            }
        }

        @Override
        public DialogueNodeType getDialogueNodeType() {
            return DialogueNodeType.PHONE;
        }
    };

    public static void initSummonListeners() {
        List<NPC> uniqueNpcs = Main.game.getAllNPCs();
        uniqueNpcs.removeIf(n -> !n.isUnique());
        for (NPC uniqueNpc : uniqueNpcs) {
            String id = "SUMMON_NPC_" + Main.game.getUniqueNPCId(uniqueNpc.getClass()).replace("-1,", "");
            Element element = MainController.document.getElementById(id);
            if (element != null) {
                ((EventTarget) element).addEventListener("click", e -> {
                    uniqueNpc.setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
            id = "RETURN_NPC_" + Main.game.getUniqueNPCId(uniqueNpc.getClass()).replace("-1,", "");
            element = MainController.document.getElementById(id);
            if (element != null) {
                ((EventTarget) element).addEventListener("click", e -> {
                    uniqueNpc.setLocation(uniqueNpc.getHomeWorldLocation(), uniqueNpc.getHomeLocation(), false);
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
                }, false);
            }
        }
    }


    public static String getContactEntry(GameCharacter contact) {
        boolean isOffspring = contact.getMotherId().equals(Main.game.getPlayer().getId()) || contact.getFatherId().equals(Main.game.getPlayer().getId());

        return UtilText.parse(contact, "<b style='color:" + contact.getFemininity().getColour().toWebHexString() + ";'>[npc.Name]</b>" + (isOffspring
                ? (contact.isFeminine()
                ? ", your [style.colourFeminine(daughter)]"
                : ", your [style.colourMasculine(son)]")
                : "")
                + ". [npc.She] is "
                + (contact.getRaceStage() == RaceStage.HUMAN || contact.isRaceConcealed()
                ? "[npc.a_race(true)]"
                : "[npc.a_raceStage(true)] [npc.race(true)]")
                + ", whose current location is: "
                + (contact.getWorldLocation() == WorldType.EMPTY
                ? "[style.italicsDisabled(The Void!)]"
                : "<i>" + contact.getWorldLocation().getName() + ", " + contact.getLocationPlace().getPlaceType().getName() + "</i>."));
    }
}
