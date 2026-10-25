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

        return CharacterModificationUtils.applyWrapper("尾穴修饰",
                UtilText.parse(BodyChanging.getTarget(), "改变 [npc.namePos] 尾穴的修饰。"
                        + "<br/><i>穴口修饰会影响性行为中的描述与某些动作；除描述性效果外还有额外效果的修饰会用星号标记。</i>"),
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

        return CharacterModificationUtils.applyWrapper("尾穴容量",
                UtilText.parse(BodyChanging.getTarget(), "改变 [npc.namePos] 尾穴的容量。"
                        + "<br/><i>这会影响 [npc.namePos] 尾穴所能容纳的插入物大小。若容量相对插入物过小或过大，都会影响兴奋度增长。</i>"),
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

        return CharacterModificationUtils.applyWrapper("尾穴湿润度",
                UtilText.parse(BodyChanging.getTarget(), "改变 [npc.namePos] 尾穴的湿润度。"
                        + "<br/><i>这会影响到性行为中的快感，因为缺乏润滑的穴口会对兴奋度增长产生负面影响。</i>"
                        + (BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()
                        ? "<br/>[style.italicsWetness8(由于 [npc.namePos] 的身体由 [npc.bodyMaterial] 构成，[npc.her] 的穴口绝不会低于 '" + Util.capitaliseSentence(Wetness.SEVEN_DROOLING.getDescriptor()) + "'！)]"
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

        return CharacterModificationUtils.applyWrapper("尾穴深度",
                UtilText.parse(BodyChanging.getTarget(), "改变 [npc.namePos] 尾穴的深度。"
                        + "<br/><i>穴口的深度决定了它能舒适容纳的插入物长度。</i>"
                        + (!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
                        ? "<br/>[style.italicsSize8(由于 [npc.namePos] 的身体由 [npc.bodyMaterial] 构成，[npc.her] 的穴口绝不会低于 '" + Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()) + "'！)]"
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

        return CharacterModificationUtils.applyWrapper("尾穴弹性",
                UtilText.parse(BodyChanging.getTarget(), "改变 [npc.namePos] 尾穴的弹性。"
                        + "<br/><i>这决定了当插入物对 [npc.namePos] 的尾穴过于粗壮时，它会以多快的速度被撑开。"
                        + (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled() ? " 标有星号的数值会增加 [npc.her] 尾穴的最大不适深度。" : ""))
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

        return CharacterModificationUtils.applyWrapper("尾穴可塑性",
                UtilText.parse(BodyChanging.getTarget(), "改变 [npc.namePos] 尾穴的可塑性。"
                        + "<br/><i>穴口的可塑性决定了它在被撑开后，能以多快的速度恢复、以及在多大程度上恢复到原本的紧致度。</i>"),
                "TUSSY_PLASTICITY",
                contentSB.toString(),
                true);
    }

}