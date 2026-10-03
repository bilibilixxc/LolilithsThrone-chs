package com.lilithsthrone.LolificationProject.Lexi;

import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

public class TussyDisplay {
    private static final StringBuilder contentSB = new StringBuilder();

    public static String getSelfTransformTussyModifiersDiv() {
        contentSB.setLength(0);

        for (OrificeModifier orificeMod : OrificeModifier.values()) {
            if (BodyChanging.getTarget().hasTussyOrificeModifier(orificeMod)) {
                contentSB.append(
                        "<div id='TUSSY_MOD_" + orificeMod + "' class='cosmetics-button active'>"
                                + "<span style='color:" + PresetColour.TRANSFORMATION_SEXUAL.toWebHexString() + ";'>" + Util.capitaliseSentence(orificeMod.getName()) + (orificeMod.isSpecialEffects() ? "*" : "") + "</span>"
                                + "</div>");

            } else {
                contentSB.append(
                        "<div id='TUSSY_MOD_" + orificeMod + "' class='cosmetics-button'>"
                                + "<span style='color:" + PresetColour.TRANSFORMATION_SEXUAL.getShades()[0] + ";'>" + Util.capitaliseSentence(orificeMod.getName()) + (orificeMod.isSpecialEffects() ? "*" : "") + "</span>"
                                + "</div>");
            }
        }

        return CharacterModificationUtils.applyWrapper("Tussy Modifiers",
                UtilText.parse(BodyChanging.getTarget(), "Change the modifiers for [npc.namePos] tussy."
                        + "<br/><i>Orifice modifiers affect descriptions and some actions in sex. The modifiers which have more than just descriptive effects are marked by an asterisk.</i>"),
                "TUSSY_MODS",
                contentSB.toString(),
                true);
    }

    public static String getSelfTransformTussyCapacityDiv() {
        contentSB.setLength(0);

        for (Capacity value : Capacity.getCapacityListFromPreferences()) {
            if (BodyChanging.getTarget().getTussyCapacity() == value) {
                contentSB.append(
                        "<div class='cosmetics-button active'>"
                                + "<span style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>"
                                + "</div>");

            } else {
                contentSB.append(
                        "<div id='TUSSY_CAPACITY_" + value + "' class='cosmetics-button'>"
                                + "<span style='color:" + value.getColour().getShades()[0] + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>"
                                + "</div>");
            }
        }

        return CharacterModificationUtils.applyWrapper("Tussy Capacity",
                UtilText.parse(BodyChanging.getTarget(), "Change the capacity of [npc.namePos] tussy."
                        + "<br/><i>This affects the size of penetrative objects that [npc.namePos] tussy can take. If capacity is too small or too large for a penetrative object, arousal gains are affected.</i>"),
                "TUSSY_CAPACITY",
                contentSB.toString(),
                true);
    }

    public static String getSelfTransformTussyWetnessDiv() {
        contentSB.setLength(0);

        for (Wetness value : Wetness.values()) {
            if (BodyChanging.getTarget().getTussyWetness() == value) {
                contentSB.append(
                        "<div class='cosmetics-button active'>"
                                + "<span style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>"
                                + "</div>");

            } else {
                if (BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
                    contentSB.append(
                            "<div class='cosmetics-button disabled'>"
                                    + "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>"
                                    + Util.capitaliseSentence(value.getDescriptor())
                                    + "</span>"
                                    + "</div>");

                } else {
                    contentSB.append(
                            "<div id='TUSSY_WETNESS_" + value + "' class='cosmetics-button'>"
                                    + "<span style='color:" + value.getColour().getShades()[0] + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>"
                                    + "</div>");
                }
            }
        }

        return CharacterModificationUtils.applyWrapper("Tussy Wetness",
                UtilText.parse(BodyChanging.getTarget(), "Change the wetness of [npc.namePos] tussy."
                        + "<br/><i>This affects pleasure in sex, as non-lubricated orifices negatively affect arousal gains.</i>"
                        + (BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()
                        ? "<br/>[style.italicsWetness8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '" + Util.capitaliseSentence(Wetness.SEVEN_DROOLING.getDescriptor()) + "'!)]"
                        : "")),
                "TUSSY_WETNESS",
                contentSB.toString(),
                true);
    }

    public static String getSelfTransformTussyDepthDiv() {
        contentSB.setLength(0);

        for (OrificeDepth value : OrificeDepth.values()) {
            if (BodyChanging.getTarget().getTussyDepth() == value) {
                contentSB.append(
                        "<div class='cosmetics-button active'>"
                                + "<span style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>"
                                + "</div>");

            } else {
                if (!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
                    contentSB.append(
                            "<div class='cosmetics-button disabled'>"
                                    + "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>"
                                    + Util.capitaliseSentence(value.getDescriptor())
                                    + "</span>"
                                    + "</div>");

                } else {
                    contentSB.append(
                            "<div id='TUSSY_DEPTH_" + value + "' class='cosmetics-button'>"
                                    + "<span style='color:" + value.getColour().getShades()[0] + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>"
                                    + "</div>");
                }
            }
        }

        return CharacterModificationUtils.applyWrapper("Tussy Depth",
                UtilText.parse(BodyChanging.getTarget(), "Change the depth of [npc.namePos] tussy."
                        + "<br/><i>Depth of an orifice determines the length of a penetrating object that it can comfortably accommodate.</i>"
                        + (!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
                        ? "<br/>[style.italicsSize8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '" + Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()) + "'!)]"
                        : "")),
                "TUSSY_DEPTH",
                contentSB.toString(),
                true);
    }

    public static String getSelfTransformTussyElasticityDiv() {
        contentSB.setLength(0);

        for (OrificeElasticity value : OrificeElasticity.values()) {
            if (BodyChanging.getTarget().getTussyElasticity() == value) {
                contentSB.append(
                        "<div class='cosmetics-button active'>"
                                + "<span style='color:" + value.getColour().toWebHexString() + ";'>"
                                + Util.capitaliseSentence(value.getDescriptor())
                                + (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled() ? "*" : "")
                                + "</span>"
                                + "</div>");

            } else {
                contentSB.append(
                        "<div id='TUSSY_ELASTICITY_" + value + "' class='cosmetics-button'>"
                                + "<span style='color:" + value.getColour().getShades()[0] + ";'>"
                                + Util.capitaliseSentence(value.getDescriptor())
                                + (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled() ? "*" : "")
                                + "</span>"
                                + "</div>");
            }
        }

        return CharacterModificationUtils.applyWrapper("Tussy Elasticity",
                UtilText.parse(BodyChanging.getTarget(), "Change the elasticity of [npc.namePos] tussy."
                        + "<br/><i>This determines how quickly [npc.namePos] tussy will stretch out if a penetrating object is too thick for it."
                        + (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled() ? " Values marked with an asterisk increase the maximum uncomfortable depth of [npc.her] tussy." : ""))
                        + "</i>",
                "TUSSY_ELASTICITY",
                contentSB.toString(),
                true);
    }

    public static String getSelfTransformTussyPlasticityDiv() {
        contentSB.setLength(0);

        for (OrificePlasticity value : OrificePlasticity.values()) {
            if (BodyChanging.getTarget().getTussyPlasticity() == value) {
                contentSB.append(
                        "<div class='cosmetics-button active'>"
                                + "<span style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>"
                                + "</div>");

            } else {
                contentSB.append(
                        "<div id='TUSSY_PLASTICITY_" + value + "' class='cosmetics-button'>"
                                + "<span style='color:" + value.getColour().getShades()[0] + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>"
                                + "</div>");
            }
        }

        return CharacterModificationUtils.applyWrapper("Tussy Plasticity",
                UtilText.parse(BodyChanging.getTarget(), "Change the plasticity of [npc.namePos] tussy."
                        + "<br/><i>Plasticity of an orifice determines how quickly, and by how much, it regains its original tightness after being stretched out.</i>"),
                "TUSSY_PLASTICITY",
                contentSB.toString(),
                true);
    }

}
