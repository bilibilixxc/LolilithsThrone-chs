package com.lilithsthrone.LolificationProject.combat;

import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.LolificationProject.ModCommon.OtherRegistrars;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

@SuppressWarnings("all")
public class CombatExtras {

    public static String applyExtraAttackEffects(GameCharacter attacker, GameCharacter target, Attack attackType, boolean isHit, boolean isCritical) {
        StringBuilder extraAttackEffectsSB = new StringBuilder();

        //Urination fetish attack effects
        if (ModProps.isPeeingEnabled() && ModProps.isLeakageEnabled() && isCritical && Util.random.nextFloat() > 0.75f) {
            if (target.isPlayer()) {
                GameCharacter player = target;
                if (player.hasFetish(OtherRegistrars.FETISH_PEE_RECEIVING)) {
                    extraAttackEffectsSB.append(
                            "<div class='container-full-width' style='text-align:center;'>"
                                    + "You feel a little aroused from the warmth running down your legs.<br/>"
                                    + Main.game.getPlayer().incrementLust(Util.random.nextInt(7), false)
                                    + "</div>");
                } else {
                    extraAttackEffectsSB.append(
                            "<div class='container-full-width' style='text-align:center;'>"
                                    + "You have leaked a little under the force of that blow!<br/></div>");
                }
                if (attacker.hasFetish(OtherRegistrars.FETISH_PEE_GIVING))
                    extraAttackEffectsSB.append(
                            "<div class='container-full-width' style='text-align:center;'>"
                                    + UtilText.parse(attacker, "[npc.Name] feels a little aroused from causing you to wet yourself.<br/>")
                                    + attacker.incrementLust(Util.random.nextInt(7), false)
                                    + "</div>");
                player.addWetSlot(InventorySlot.GROIN);
                if (player.getClothingInSlot(InventorySlot.GROIN) != null)
                    player.getClothingInSlot(InventorySlot.GROIN).setWet(player, true);
                player.addWetSlot(InventorySlot.LEG);
                if (player.getClothingInSlot(InventorySlot.LEG) != null)
                    player.getClothingInSlot(InventorySlot.LEG).setWet(player, true);
            }

            if (attacker.isPlayer()) {
                extraAttackEffectsSB.append(
                        "<div class='container-full-width' style='text-align:center;'>"
                                + UtilText.parse(target, "You made [npc.Name] urinate a little under the force of that blow!<br/></div>"));
                if (target.hasFetish(OtherRegistrars.FETISH_PEE_RECEIVING))
                    extraAttackEffectsSB.append(
                            "<div class='container-full-width' style='text-align:center;'>"
                                    + UtilText.parse(target, "[npc.Name] feels a little aroused from the warmth running down their legs.<br/>")
                                    + attacker.incrementLust(Util.random.nextInt(7), false)
                                    + "</div>");
                if (attacker.hasFetish(OtherRegistrars.FETISH_PEE_GIVING))
                    extraAttackEffectsSB.append(
                            "<div class='container-full-width' style='text-align:center;'>"
                                    + UtilText.parse(target, "You feel a little aroused from causing [npc.Name] to wet their self.<br/>")
                                    + Main.game.getPlayer().incrementLust(Util.random.nextInt(7), false)
                                    + "</div>");
                target.addWetSlot(InventorySlot.GROIN);
                if (target.getClothingInSlot(InventorySlot.GROIN) != null)
                    target.getClothingInSlot(InventorySlot.GROIN).setWet(target, true);
                target.addWetSlot(InventorySlot.LEG);
                if (target.getClothingInSlot(InventorySlot.LEG) != null)
                    target.getClothingInSlot(InventorySlot.LEG).setWet(target, true);
            }
        }

        //Age Fetish extra attack effects
        if (ModProps.isExtremeAgeEnabled() && attackType == Attack.SEDUCTION) {
            //If target is older than the attacker
            if (target.getAppearsAsAgeValue() - attacker.getAgeValue() >= 10) {
                //Attacker has older fetish
                if (attacker.hasFetish(OtherRegistrars.FETISH_AGE_OLDER)) {
                    //Text for player VS NPC
                    if (target.isPlayer())
                        extraAttackEffectsSB.append(
                                "<div class='container-full-width' style='text-align:center;'>"
                                        + UtilText.parse(target, "You feel more aroused due to their enhanced seduction.<br/>")
                                        + target.incrementLust(Util.random.nextInt(7), false)
                                        + "</div>");
                    else
                        extraAttackEffectsSB.append(
                                "<div class='container-full-width' style='text-align:center;'>"
                                        + UtilText.parse(target, "[npc.Name] feels more aroused due to your enhanced seduction.<br/>")
                                        + target.incrementLust(Util.random.nextInt(7), false)
                                        + "</div>");
                }

                //Target has younger fetish
                if (target.hasFetish(OtherRegistrars.FETISH_AGE_YOUNGER)) {
                    //Text for player VS NPC
                    if (target.isPlayer())
                        extraAttackEffectsSB.append(
                                "<div class='container-full-width' style='text-align:center;'>"
                                        + UtilText.parse(target, "You feel more aroused due to their age.<br/>")
                                        + target.incrementLust(Util.random.nextInt(7), false)
                                        + "</div>");
                    else
                        extraAttackEffectsSB.append(
                                "<div class='container-full-width' style='text-align:center;'>"
                                        + UtilText.parse(target, "[npc.Name] feels more aroused due to your age.<br/>")
                                        + target.incrementLust(Util.random.nextInt(7), false)
                                        + "</div>");
                }
                //If target is younger than attacker
            } else if (target.getAppearsAsAgeValue() - attacker.getAgeValue() <= -10) {
                //Attacker has younger fetish
                if (attacker.hasFetish(OtherRegistrars.FETISH_AGE_YOUNGER)) {
                    //Text for player VS NPC
                    if (target.isPlayer())
                        extraAttackEffectsSB.append(
                                "<div class='container-full-width' style='text-align:center;'>"
                                        + UtilText.parse(target, "You feel more aroused due to their enhanced seduction.<br/>")
                                        + target.incrementLust(Util.random.nextInt(7), false)
                                        + "</div>");
                    else
                        extraAttackEffectsSB.append(
                                "<div class='container-full-width' style='text-align:center;'>"
                                        + UtilText.parse(target, "[npc.Name] feels more aroused due to your enhanced seduction.<br/>")
                                        + target.incrementLust(Util.random.nextInt(7), false)
                                        + "</div>");
                }

                //Target has older fetish
                if (target.hasFetish(OtherRegistrars.FETISH_AGE_OLDER)) {
                    //Text for player VS NPC
                    if (target.isPlayer())
                        extraAttackEffectsSB.append(
                                "<div class='container-full-width' style='text-align:center;'>"
                                        + UtilText.parse(target, "You feel more aroused due to their age.<br/>")
                                        + target.incrementLust(Util.random.nextInt(7), false)
                                        + "</div>");
                    else
                        extraAttackEffectsSB.append(
                                "<div class='container-full-width' style='text-align:center;'>"
                                        + UtilText.parse(target, "[npc.Name] feels more aroused due to your age.<br/>")
                                        + target.incrementLust(Util.random.nextInt(7), false)
                                        + "</div>");
                }
            }
        }

        return extraAttackEffectsSB.toString();
    }
}