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

    public static final DialogueNode SUMMON_NPC = new DialogueNode("召唤NPC", "将指定NPC传送至你身边。", true) {

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
                        + ";'>召唤 " + npc.getNameIgnoresPlayerKnowledge() + "</span>" + "</div>"
                        + "<div id='RETURN_NPC_" + Main.game.getUniqueNPCId(npc.getClass()).replace("-1,", "") + "' class='cosmetics-button'><span style='color:"
                        + PresetColour.BASE_PURPLE_LIGHT.toWebHexString()
                        + ";'>送 " + npc.getNameIgnoresPlayerKnowledge() + " 回家</span>" + "</div>"

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
                return new Response("返回", "返回手机的主菜单。", PhoneDialogue.MENU);
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
                ? ", 你的[style.colourFeminine(女儿)]"
                : ", 你的[style.colourMasculine(儿子)]")
                : "")
                + "。[npc.She]是"
                + (contact.getRaceStage() == RaceStage.HUMAN || contact.isRaceConcealed()
                ? "[npc.a_race(true)]"
                : "[npc.a_raceStage(true)] [npc.race(true)]")
                + "，其当前所在位置为："
                + (contact.getWorldLocation() == WorldType.EMPTY
                ? "[style.italicsDisabled(虚空!)]"
                : "<i>" + contact.getWorldLocation().getName() + "，" + contact.getLocationPlace().getPlaceType().getName() + "</i>。"));
    }
}