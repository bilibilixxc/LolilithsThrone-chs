package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @author Innoxia
 * @version 0.4.0.0
 * @since 0.1.79
 */
public class SelfFingerNipple {
    public static final SexAction PINCH_NIPPLES = new SexAction(
            SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
            ArousalIncrease.ONE_MINIMUM,
            ArousalIncrease.THREE_NORMAL,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF) {
        @Override
        public boolean isBaseRequirementsMet() {
            return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
        }

        @Override
        public String getActionTitle() {
            return "捏乳头 (自己)";
        }

        @Override
        public String getActionDescription() {
            return "玩弄你自己的乳头。";
        }

        @Override
        public String getDescription() {
            UtilText.nodeContentSB.setLength(0);

            UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
                    "[npc.Name]向上摸索并开始玩弄[npc.her]坚硬的[npc.nipples]，[npc.she]揉捏着摩擦它们并激动地[npc.moans]着。",
                    "[npc.NamePos]指尖在[npc.her][npc.breasts+]上挑逗，停下来并捏拉[npc.her][npc.nipples+]，[npc.she]发出愉悦的[npc.moan]与叹息。",
                    "[npc.Name]将手伸向[npc.her][npc.breasts+]，开始用热切的手指揉捏和摩擦[npc.her]暴露的[npc.nipples]。",
                    "[npc.name]发出[npc.a_moan+]，将手摸索向[npc.her][npc.nipples+]，或捏或弹，[npc.she]紧接着兴奋地叫喊。"));

            switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
                case ONE_TRICKLE:
                    UtilText.nodeContentSB.append("几滴[npc.milk]漏出并萦绕在[npc.her]的指尖上。");
                    break;
                case TWO_SMALL_AMOUNT:
                    UtilText.nodeContentSB.append("少许[npc.milk]从[npc.her]的指尖下漏出。");
                    break;
                case THREE_DECENT_AMOUNT:
                    UtilText.nodeContentSB.append("些许的[npc.milk]从[npc.her]的指尖下流了出来。");
                    break;
                case FOUR_LARGE_AMOUNT:
                    UtilText.nodeContentSB.append("[npc.Her][npc.milk]开始从[npc.her]指尖流出并从[npc.her][npc.breasts+]上缓缓流下。");
                    break;
                case FIVE_VERY_LARGE_DROOLING:
                    UtilText.nodeContentSB.append("[npc.Milk]开始在[npc.her]的指间一小股一小股地流下。");
                    break;
                case SIX_EXTREME_AMOUNT_DRIPPING:
                    UtilText.nodeContentSB.append("[npc.Milk]开始源源不断地流出，并成股地从[npc.her][npc.breasts+]上流下。");
                    break;
                case SEVEN_MONSTROUS_AMOUNT_POURING:
                    UtilText.nodeContentSB.append("[npc.Milk]开始源源不断地倾泻而下并将[npc.her][npc.breasts+]完全浸没了。");
                    break;
                default:
                    break;
            }

            return UtilText.nodeContentSB.toString();
        }

        @Override
        public String applyEffectsString() {
            return Main.sex.getCharacterPerformingAction().incrementBreastStoredMilk(-10);
        }

    };


    public static final SexAction SELF_FINGER_NIPPLE_PENETRATION = new SexAction(
            SexActionType.START_ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF) {
        @Override
        public boolean isBaseRequirementsMet() {
            return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
        }

        @Override
        public String getActionTitle() {
            return "乳头自慰(自己)";
        }

        @Override
        public String getActionDescription() {
            return "将[npc.her]的手指压入[npc.her][npc.nipples+]。";
        }

        @Override
        public String getDescription() {
            UtilText.nodeContentSB.setLength(0);

            UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
                    "[npc.Name]向上摸索，发出淫荡的[npc.moan]，将[npc.her]的手指急切地陷入那足以插入的[npc.nipples]内。",
                    "[npc.NamePos]的指尖挑逗着[npc.her]的胸脯，盘旋在[npc.her]的[npc.nipples]周围，然后贪婪地陷入其中。",
                    "当[npc.she]开始热烈地用手指探入[npc.her]的乳穴，[npc.Name][npc.moans]并且高亢地叫起来。",
                    "随着一声淫荡的叫喊，[npc.name]将[npc.her]的手指插入[npc.her]令人沉醉的乳穴中，[npc.she]沉重地喘息着并热烈地用手指取悦[npc.herself]。"));
            switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
                case ONE_TRICKLE:
                    UtilText.nodeContentSB.append("几滴[npc.milk]漏出并萦绕在[npc.her]的指尖上。");
                    break;
                case TWO_SMALL_AMOUNT:
                    UtilText.nodeContentSB.append("少许[npc.milk]从[npc.her]的指尖下漏出。");
                    break;
                case THREE_DECENT_AMOUNT:
                    UtilText.nodeContentSB.append("些许的[npc.milk]从[npc.her]的指尖下流了出来。");
                    break;
                case FOUR_LARGE_AMOUNT:
                    UtilText.nodeContentSB.append("[npc.Her][npc.milk]开始从[npc.her]指尖流出并从[npc.her][npc.breasts+]上缓缓流下。");
                    break;
                case FIVE_VERY_LARGE_DROOLING:
                    UtilText.nodeContentSB.append("[npc.Her]的[npc.milk]开始在[npc.her]的指间一小股一小股地流下。");
                    break;
                case SIX_EXTREME_AMOUNT_DRIPPING:
                    UtilText.nodeContentSB.append("[npc.Her]的[npc.milk]开始源源不断地流出，并成股地从[npc.her][npc.breasts+]上流下。");
                    break;
                case SEVEN_MONSTROUS_AMOUNT_POURING:
                    UtilText.nodeContentSB.append("[npc.Her]的[npc.milk]开始源源不断地倾泻而下并将[npc.her][npc.breasts+]完全浸没了。");
                    break;
                default:
                    break;
            }

            return UtilText.nodeContentSB.toString();
        }

        @Override
        public String applyEffectsString() {
            return Main.sex.getCharacterPerformingAction().incrementBreastStoredMilk(-10);
        }
    };

    public static final SexAction DOM_SELF_FINGER_NIPPLE_GENTLE = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.DOM_GENTLE) {

        @Override
        public String getActionTitle() {
            return "指交乳头(温柔)(自己)";
        }

        @Override
        public String getActionDescription() {
            return "温柔地用手指玩弄[npc.her][npc.nipples+]。";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "当[npc.she]慢慢地将[npc.her]的[npc.fingers]向[npc.her][npc.nipple+]更深处推入时，[npc.A_moan+]从[npc.namePos][npc.lips+]间漏出。",
                    "温柔地将[npc.her]的[npc.fingers]在[npc.her][npc.nipple+]中富有节奏地抽插着，[npc.she]用手指玩弄起[npc.her][npc.breast+]，[npc.name]开始发出一连串愉悦的[npc.moans]。",
                    "[npc.fingers]在[npc.nipple(true)]内蜷起，[npc.name]将[npc.fingers]在[npc.her][npc.breast+]中抽插，不自觉地发出呜咽声。",
                    "[npc.name]专心取悦起[npc.her]足以插入的[npc.breasts]，温柔地用[npc.fingers]在[npc.her]的其中一个[npc.nipples+]中进进出出。");
        }

    };

    public static final SexAction DOM_SELF_FINGER_NIPPLE_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.DOM_NORMAL) {

        @Override
        public String getActionTitle() {
            return "指交乳头(自己)";
        }

        @Override
        public String getActionDescription() {
            return "全身心地投入玩弄[npc.her][npc.nipples+]。";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "当[npc.she]贪婪地将[npc.her]的[npc.fingers]向[npc.her][npc.nipple+]更深处推入时，[npc.A_moan+]从[npc.namePos][npc.lips+]间漏出。",
                    "[npc.name]的[npc.fingers]在[npc.her][npc.nipple+]中富有节奏地抽插着，[npc.she]用手指玩弄起[npc.her][npc.breast+]，[npc.her]开始发出一连串愉悦的[npc.moans]。",
                    "[npc.fingers]在那足以插入的[npc.nipple(true)]内蜷起，[npc.name]将[npc.fingers]开始在自己[npc.nipple+]中抽插，不自觉地漏出[npc.a_moan]。",
                    "[npc.name]专心取悦起[npc.her]的[npc.breasts]，用[npc.fingers]在[npc.her][npc.nipples+]中进进出出。");
        }

    };

    public static final SexAction DOM_SELF_FINGER_NIPPLE_ROUGH = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.DOM_ROUGH) {

        @Override
        public String getActionTitle() {
            return "指交乳头(粗暴)(自己)";
        }

        @Override
        public String getActionDescription() {
            return "粗暴地用手指玩弄[npc.her][npc.nipples+]。";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.namePos]粗暴地将[npc.fingers]深深插进那[npc.nipple+]中，[npc.A_moan+]从[npc.lips+]间漏出，紧接着[npc.she]开始用手指快速地抽插自己的[npc.breast(true)]。",
                    "[npc.her]的[npc.fingers]在[npc.her][npc.nipple+]中粗暴地抽插着，当[npc.she]用手指富有节奏地玩弄起[npc.her][npc.breast+]，[npc.name]开始发出一连串愉悦的[npc.moans]。",
                    "[npc.Her]的[npc.fingers]在[npc.her]那足以插入的[npc.nipple(true)]内激烈地蜷起，当[npc.her]的[npc.fingers]开始在[npc.her][npc.nipple+]中粗暴地摩擦，[npc.name]不自觉地漏出[npc.a_moan]。",
                    "[npc.name]专心取悦起[npc.her]足以插入的[npc.breasts]，粗暴地用[npc.fingers]插入[npc.her]的其中一个[npc.nipples+]并激烈地抽插。");
        }

    };

    public static final SexAction SUB_SELF_FINGER_NIPPLE_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.SUB_NORMAL) {

        @Override
        public String getActionTitle() {
            return "指交乳头(自己)";
        }

        @Override
        public String getActionDescription() {
            return "全身心地投入玩弄[npc.her][npc.nipples+]。";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "当[npc.she]贪婪地将[npc.her]的[npc.fingers]向[npc.her][npc.nipple+]更深处推入时，[npc.A_moan+]从[npc.namePos][npc.lips+]间漏出。",
                    "[npc.name]的[npc.fingers]在[npc.her][npc.nipple+]中富有节奏地抽插着，[npc.she]用手指玩弄起[npc.her][npc.breast+]，[npc.her]开始发出一连串愉悦的[npc.moans]。",
                    "[npc.fingers]在那足以插入的[npc.nipple(true)]内蜷起，[npc.name]将[npc.fingers]开始在自己[npc.nipple+]中抽插，不自觉地漏出[npc.a_moan]。",
                    "[npc.name]专心取悦起[npc.her]的[npc.breasts]，用[npc.fingers]在[npc.her][npc.nipples+]中进进出出。");
        }

    };

    public static final SexAction SUB_SELF_FINGER_NIPPLE_EAGER = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.SUB_EAGER) {

        @Override
        public String getActionTitle() {
            return "指交乳头(渴求)(自己)";
        }

        @Override
        public String getActionDescription() {
            return "饥渴地用手指玩弄[npc.her][npc.nipples+]。";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "当[npc.she]饥渴地将[npc.her]的[npc.fingers]深深插进[npc.her][npc.nipple+]中，[npc.A_moan+]从[npc.namePos][npc.lips+]间漏出，紧接着[npc.she]开始用手指猛烈地抽插[npc.her]的[npc.breast(true)]。",
                    "狂热地将[npc.her]的[npc.fingers]在[npc.her][npc.nipple+]中富有节奏地抽插着，[npc.she]用手指疯狂地玩弄起[npc.her][npc.breast+]，[npc.name]开始发出一连串愉悦的[npc.moans]。",
                    "[npc.fingers]在那足以插入的[npc.nipple(true)]内猛然蜷起，[npc.name]将[npc.fingers]在自己[npc.nipple+]中饥渴地摩擦，不自觉地漏出[npc.a_moan]。",
                    "[npc.name]专心取悦起[npc.her]足以插入的[npc.breasts]，饥渴地用[npc.fingers]插入[npc.her]的其中一个[npc.nipples+]抽插起来。");
        }

    };

    public static final SexAction SELF_FINGER_NIPPLE_STOP_PENETRATION = new SexAction(
            SexActionType.STOP_ONGOING,
            ArousalIncrease.ONE_MINIMUM,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF) {

        @Override
        public String getActionTitle() {
            return "停止指交乳头(自己)";
        }

        @Override
        public String getActionDescription() {
            return "停止玩弄[npc.her][npc.nipples+]。";
        }

        @Override
        public String getDescription() {
            return "[npc.name]发出一声心满意足的[npc.moan]，将指尖滑出了[npc.nipples+]。";
        }
    };
}
