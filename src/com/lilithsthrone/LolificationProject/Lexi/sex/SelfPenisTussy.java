package com.lilithsthrone.LolificationProject.Lexi.sex;

import com.lilithsthrone.LolificationProject.ModCommon.TranslationUtils;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.*;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

public class SelfPenisTussy {

    public static final SexAction TEASE_PENIS_OVER_OWN_TUSSY = new SexAction(
            SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.THREE_NORMAL,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY)),
            SexParticipantType.SELF) {
        @Override
        public boolean isBaseRequirementsMet() {
            return super.isBaseRequirementsMet() && Main.sex.getCharacterPerformingAction().hasTussy();
        }

        @Override
        public String getActionTitle() {
            return "挑逗自己的尾穴";
        }

        @Override
        public String getActionDescription() {
            return "将你的[npc.cock]的[npc.cockHead]贴着你的[npc.tussy+]滑动，挑逗[npc.herself]。";
        }

        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "PenisSelfTussyTease",// change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),
                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction SELF_TAIL_PENETRATION_START = new SexAction(
            SexActionType.START_ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.TWO_HORNY,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY)),
            SexParticipantType.SELF) {
        @Override
        public boolean isBaseRequirementsMet() {
            return super.isBaseRequirementsMet() && Main.sex.getCharacterPerformingAction().hasTussy();
        }

        @Override
        public String getActionTitle() {
            return "开始尾穴自交";
        }

        @Override
        public String getActionDescription() {
            return "将你的[npc.penis]滑入你的[npc.tail+]内，开始用自己的[npc.tussy]自慰。";
        }
        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "selfTussyFuckStart", // change to what the sex action will be called in short form, must be unique.
                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction SELF_TAIL_PENETRATION_DOM_GENTLE = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.TWO_LOW,
            ArousalIncrease.THREE_NORMAL,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice. TUSSY)),
            SexParticipantType.SELF,
            SexPace.DOM_GENTLE) {
        @Override
        public String getActionTitle() {
            return "温柔尾穴自交";
        }
        @Override
        public String getActionDescription() {
            return "用你自己的[npc.tussy+]温柔地自慰。";
        }
        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "selfTussyFuckGentle", // change to what the sex action will be called in short form, must be unique.
                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction SELF_TAIL_PENETRATION_DOM_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.TWO_LOW,
            ArousalIncrease.THREE_NORMAL,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY)),
            SexParticipantType.SELF,
            SexPace.DOM_NORMAL) {
        @Override
        public String getActionTitle() {
            return "尾穴自交";
        }
        @Override
        public String getActionDescription() {
            return "继续将你的[npc.penis]插入你自己的[npc.tussy+]。";
        }
        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "selfTussyFuckNormal", // change to what the sex action will be called in short form, must be unique.
                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction SELF_TAIL_PENETRATION_STOP = new SexAction(
            SexActionType.STOP_ONGOING,
            ArousalIncrease.TWO_LOW,
            ArousalIncrease.TWO_LOW,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice. TUSSY)),
            SexParticipantType.SELF) {
        @Override
        public String getActionTitle() {
            return "停止尾穴自交";
        }
        @Override
        public String getActionDescription() {
            return "将你的[npc.penis]从你的[npc.tail+]的尾穴中抽出，停止自我尾穴插入。";
        }
        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "selfTussyFuckStop", // change to what the sex action will be called in short form, must be unique.
                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };
};