package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.*;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfFingerAnus {

    public static final SexAction SELF_FINGER_ANUS_SPREAD_ASS = new SexAction(
            SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
            ArousalIncrease.TWO_LOW,
            ArousalIncrease.TWO_LOW,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF) {

        @Override
        public boolean isBaseRequirementsMet() {
            return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING;
        }

        @Override
        public String getActionTitle() {
            return "掰开臀瓣";
        }

        @Override
        public String getActionDescription() {
            return "用你的[npc.hands]掰开臀瓣。";
        }

        @Override
        public String getDescription() {
            if (Main.sex.isMasturbation()) {
                if (Main.sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.ALL_FOURS)) {
                    return UtilText.returnStringAtRandom(
                            "你收回[pc.hand]，抓住[pc.ass+]，把它掰到一边，边露出你[pc.asshole+]边[pc.asshole+]。",
                            "你用一只[pc.hand]伸到后面， 一边轻声呻吟一边扒住你[pc.ass+]，然后诱人地拉向一边掰开，张开你[pc.asshole+]。",
                            "你指尖滑过[pc.asshole+]，发出[pc.a_moan+]，同时抓住你[pc.assSize]的臀肉掰向一边，以展示[pc.asshole+]。",
                            "你急切地用你[pc.fingers]滑过你饥渴的[pc.asshole]，[pc.moaning+]同时用你的[pc.hand]将臀肉掰向一侧，为肛门插入做好准备。");
                } else {
                    return UtilText.returnStringAtRandom(
                            "你[pc.hands]向后伸，抓住[pc.assSize]的臀肉并将它们分开，一边发出[pc.a_moan+]一边张开你[pc.asshole+]。",
                            "你用[pc.hands]向后伸，一边轻声呻吟，一边诱人地掰开[pc.assSize]的臀肉并张开你[pc.asshole+]。",
                            "你将指尖滑过[pc.asshole+]，发出[pc.a_moan+]，同时掰开[pc.assSize]的两瓣臀肉，展示你[pc.asshole+]。",
                            "你急切地用你[pc.fingers]滑过你饥渴的[pc.asshole]，[pc.moaning+]同时用你的[pc.hands]将两瓣臀肉掰开，为肛门插入做好准备。");
                }
            } else if (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)) {
                return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()),
                        UtilText.returnStringAtRandom(
                                "[npc.name]将一只[npc.hand]伸到后面，抓住[npc.her][npc.ass+]并掰到一边，[npc.she]发出[npc.a_moan+]的同时，将[npc.her][npc.asshole+]呈现给[npc2.name]。",
                                "[npc.Name]向后伸出一只[npc.hand]，轻声呻吟着抓住[npc.her][npc.ass+]，然后诱人地掰向一边，向[npc2.name]露出[npc.her][npc.asshole+]。",
                                "[npc.name]指尖滑过[npc.her][npc.asshole+]，发出[npc.a_moan+]，[npc.she]抓住[npc.her][npc.assSize]的一瓣臀肉，掰向一边，将[npc.her][npc.asshole+]呈现给[npc2.name]。",
                                "[npc.Name]急切地用[npc.fingers]磨蹭着欲求不满的[npc.asshole]，[npc.moaning+]着用[npc.hand]将掰开臀肉，为肛门插入做好准备。"));
            } else {
                return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()),
                        UtilText.returnStringAtRandom(
                                "[npc.name]双[npc.hand]伸到背后，抓住并掰开[npc.assSize]的臀肉，[npc.she]发出[npc.a_moan+]的同时，将[npc.her][npc.asshole+]呈现给[npc2.name]。",
                                "[npc.Name]双[npc.hand]伸到背后，轻声呻吟着，邀请般掰开[npc.assSize]的两瓣屁股，将[npc.her][npc.asshole+]呈现给[npc2.name]。",
                                "[npc.name]指尖滑过[npc.her][npc.asshole+]，发出[npc.a_moan+]，[npc.she]抓住[npc.her][npc.assSize]的臀肉，掰向一边，将[npc.her][npc.asshole+]呈现给[npc2.name]。",
                                "[npc.Name]急切地用[npc.fingers]磨蹭着欲求不满的[npc.asshole]，[npc.moaning+]着用[npc.hands]将掰开臀肉，为肛门插入做好准备。"));
            }
        }
    };

    public static final SexAction SELF_FINGER_ANUS_PENETRATION = new SexAction(
            SexActionType.START_ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.TWO_HORNY,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF) {
        @Override
        public String getActionTitle() {
            return "指交肛门(自己)";
        }

        @Override
        public String getActionDescription() {
            return "开始指交[npc.her]的屁股。";
        }

        @Override
        public boolean isBaseRequirementsMet() {
            return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING;
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.name]将手伸向臀肉，手指抚弄着[npc.her][npc.asshole+]的入口并深入内部，[npc.she]发出[npc.a_moan+]。",
                    "[npc.Name]把手指向下探入[npc.ass]间，轻轻地[npc.moaning]，并将两根手指插入[npc.her]那诱人[npc.asshole]。",
                    "[npc.name]的指尖滑过[npc.her]那被冷落的[npc.asshole]，[npc.she]发出一阵[npc.groan+]并把手指深入内部。",
                    "[npc.Name]急切地将手指插进自己欲求不满的[npc.asshole]，[npc.moaning+]着，用手指在[npc.ass]里进进出出。");
        }
    };

    public static final SexAction DOM_SELF_FINGER_ANUS_GENTLE = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.TWO_HORNY,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF,
            SexPace.DOM_GENTLE) {
        @Override
        public String getActionTitle() {
            return "指交肛门(温柔)(自己)";
        }

        @Override
        public String getActionDescription() {
            return "温柔地用手指玩弄[npc.her][npc.asshole]。";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.namePos]把[npc.fingers]深入自己[npc.asshole+]，[npc.lips+]间发出[npc.moan]。",
                    "[npc.name]用[npc.fingers]在自己[npc.asshole+]里温柔地抽插着，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己的[npc.ass]。",
                    "[npc.fingers]在[npc.asshole]里蜷起，[npc.name]不自觉地发出呜咽声，开始"
                            + (Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
                            ? "温柔地按摩着[npc.her]前列腺。"
                            : "温柔地玩弄[npc.her][npc.ass+]。"),
                    "[npc.name]专心取悦起[npc.her][npc.ass+]，温柔地用[npc.fingers]在[npc.her][npc.asshole+]中抽插。");
        }
    };

    public static final SexAction DOM_SELF_FINGER_ANUS_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.TWO_HORNY,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF,
            SexPace.DOM_NORMAL) {
        @Override
        public String getActionTitle() {
            return "指交肛门(自己)";
        }

        @Override
        public String getActionDescription() {
            return "全身心地投入玩弄[npc.her][npc.ass]。";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.namePos]把[npc.fingers]贪婪地深入自己[npc.asshole+]，[npc.lips+]间发出[npc.moan]。",
                    "[npc.name]用[npc.fingers]在自己[npc.asshole+]里抽插着，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己的[npc.ass]。",
                    "[npc.fingers]在[npc.asshole]里蜷起，[npc.name]不自觉地发出[npc.a_moan]，开始"
                            + (Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
                            ? "按摩[npc.her]前列腺。"
                            : "玩弄[npc.her][npc.ass+]。"),
                    "[npc.name]专心取悦起[npc.her][npc.ass+]，用[npc.fingers]在[npc.her][npc.asshole+]中抽插。");
        }
    };

    public static final SexAction DOM_SELF_FINGER_ANUS_ROUGH = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.THREE_DIRTY,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF,
            SexPace.DOM_ROUGH) {
        @Override
        public String getActionTitle() {
            return "指交肛门(粗暴)(自己)";
        }

        @Override
        public String getActionDescription() {
            return "粗暴地用手指玩弄[npc.her][npc.ass]。";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.namePos]把[npc.fingers]深入那[npc.asshole+]，[npc.lips+]间发出[npc.moan]，然后粗暴地操起自己的[npc.ass]。",
                    "[npc.name]用[npc.fingers]在自己[npc.asshole+]里粗暴地抽插着，开始发出一连串愉悦的[npc.moans]，无情地操起自己的[npc.ass]。",
                    "[npc.fingers]在[npc.asshole]里强硬地蜷起，[npc.name]不自觉地发出[npc.a_moan]，开始"
                            + (Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
                            ? "粗暴地用指尖揉弄[npc.her]前列腺。"
                            : "粗暴地用手指在[npc.her][npc.ass+]里抽插。"),
                    "[npc.name]专心取悦起[npc.her][npc.ass+]，粗暴地用[npc.fingers]贯穿抽插[npc.her][npc.asshole+]。");
        }
    };

    public static final SexAction SUB_SELF_FINGER_ANUS_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.TWO_HORNY,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF,
            SexPace.SUB_NORMAL) {
        @Override
        public String getActionTitle() {
            return "指交肛门(自己)";
        }

        @Override
        public String getActionDescription() {
            return "全身心地投入玩弄[npc.her][npc.ass]。";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.namePos]把[npc.fingers]贪婪地深入自己[npc.asshole+]，[npc.lips+]间发出[npc.moan]。",
                    "[npc.name]用[npc.fingers]在自己[npc.asshole+]里抽插着，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己的[npc.ass]。",
                    "[npc.fingers]在[npc.asshole]里蜷起，[npc.name]不自觉地发出[npc.a_moan]，开始"
                            + (Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
                            ? "按摩[npc.her]前列腺。"
                            : "玩弄[npc.her][npc.ass+]。"),
                    "[npc.name]专心取悦起[npc.her][npc.ass+]，用[npc.fingers]在[npc.her][npc.asshole+]中抽插。");
        }
    };

    public static final SexAction SUB_SELF_FINGER_ANUS_EAGER = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.THREE_DIRTY,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF,
            SexPace.SUB_EAGER) {
        @Override
        public String getActionTitle() {
            return "指交肛门(渴求)(自己)";
        }

        @Override
        public String getActionDescription() {
            return "急切地用手指玩弄[npc.her][npc.ass]。";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.namePos]把[npc.fingers]深入那[npc.asshole+]，[npc.lips+]间发出[npc.moan]，然后迷乱地指交起自己的[npc.ass]。",
                    "[npc.name]用[npc.fingers]在自己[npc.asshole+]里热情地抽插着，开始发出一连串愉悦的[npc.moans]，疯狂地操起自己的[npc.ass]。",
                    "[npc.fingers]在[npc.asshole]里狂乱地蜷起，[npc.name]不自觉地发出[npc.a_moan]，开始"
                            + (Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
                            ? "急切地用指尖揉弄[npc.her]前列腺。"
                            : "急切地用手指在[npc.her][npc.ass+]里抽插。"),
                    "[npc.name]专心取悦起[npc.her][npc.ass+]，急切地用[npc.fingers]贯穿抽插[npc.her][npc.asshole+]。");
        }
    };

    public static final SexAction SELF_FINGER_ANUS_STOP_PENETRATION = new SexAction(
            SexActionType.STOP_ONGOING,
            ArousalIncrease.ONE_MINIMUM,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF) {
        @Override
        public String getActionTitle() {
            return "停止指交肛门(自己)";
        }

        @Override
        public String getActionDescription() {
            return "停止指交[npc.her]地屁穴。";
        }

        @Override
        public String getDescription() {
            return "[npc.Name][npc.a_groan+]着，将手指顶进自己[npc.asshole+]。";
        }
    };
}
