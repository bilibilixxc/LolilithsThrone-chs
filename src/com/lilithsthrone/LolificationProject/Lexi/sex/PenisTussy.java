package com.lilithsthrone.LolificationProject.Lexi.sex;

import com.lilithsthrone.LolificationProject.ModCommon.TranslationUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.*;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.game.sex.Sex;

public class PenisTussy {
    public static final SexAction TEASE_PENIS_OVER_TUSSY = new SexAction(
            SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.THREE_NORMAL,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY)),
            SexParticipantType.NORMAL) {
        @Override
        public boolean isBaseRequirementsMet() {
            return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
        }
        @Override
        public String getActionTitle() {
            return "挑逗尾穴";
        }

        @Override
        public String getActionDescription() {
            return "将你的[npc.cock]的[npc.cockHead]贴着[npc2.her]的[npc2.tussy+]滑动，挑逗[npc2.name]。";
        }

        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "PenisTussyTease",// change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),
                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }

        @Override
        public void applyEffects() {
            Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.TUSSY);

        }
    };

    public static final SexAction TEASE_TUSSY_OVER_PENIS = new SexAction(
            SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.THREE_NORMAL,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.TUSSY, SexAreaPenetration.PENIS)),
            SexParticipantType.NORMAL) {
        @Override
        public boolean isBaseRequirementsMet() {
            return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
        }
        @Override
        public String getActionTitle() {
            return "挑逗阴茎";
        }

        @Override
        public String getActionDescription() {
            return "将[npc.her]的[npc.tussy+]的入口贴着[npc2.her]的[npc2.cockHead+]滑动，挑逗[npc2.name]。";
        }

        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "PenisTussyTease",// change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),
                    false// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }

        @Override
        public void applyEffects() {
            Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY, Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
        }
    };

    public static final SexAction TUSSY_CONTROL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.FOUR_HIGH,
            ArousalIncrease.FIVE_EXTREME,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.TUSSY, SexAreaPenetration.PENIS)),
            SexParticipantType.NORMAL) {
        @Override
        public String getActionTitle() {
            return "尾穴控制";
        }
        @Override
        public String getActionDescription() {
            return "用你内部肌肉发达的尾穴勒紧[npc2.namePos]的[npc2.cock]。";
        }
        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "tussyControl",// change to what the sex action will be called in short form, must be unique.
                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction PENIS_FUCKING_TUSSY_START = new SexAction(
            SexActionType.START_ONGOING,
            ArousalIncrease.FOUR_HIGH,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY)),
            SexParticipantType.NORMAL) {
        @Override
        public String getActionTitle() {
            return "干[npc2.herHim]的尾穴";
        }

        @Override
        public String getActionDescription() {
            return "将你的[npc.cock+]沉入[npc2.namePos]的[npc2.tussy+]内，开始干[npc2.herHim]。";
        }
        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "FuckTussyStart",// change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),
                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction PENIS_FUCKING_TUSSY_DOM_GENTLE = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.FOUR_HIGH,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY)),
            SexParticipantType.NORMAL,
            SexPace.DOM_GENTLE) {

        @Override
        public String getActionTitle() {
            return "温柔干尾穴";
        }

        @Override
        public String getActionDescription() {
            return "温柔地让[npc.cock]在[npc2.namePos]的[npc2.tussy+]中进出。";
        }
        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "FuckTussy", // change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),
                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction PENIS_FUCKING_TUSSY_DOM_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.FOUR_HIGH,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY)),
            SexParticipantType.NORMAL,
            SexPace.DOM_NORMAL) {
        @Override
        public String getActionTitle() {
            return "干[npc2.herHim]的尾穴";
        }

        @Override
        public String getActionDescription() {
            return "继续让[npc.cock+]在[npc2.namePos]的[npc2.tussy+]中进出。";
        }
        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "FuckTussy", // change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),

                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction PENIS_FUCKING_TUSSY_DOM_ROUGH = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.FOUR_HIGH,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.TWO_HORNY,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY)),
            SexParticipantType.NORMAL,
            SexPace.DOM_ROUGH) {

        @Override
        public String getActionTitle() {
            return "粗暴干尾穴";
        }

        @Override
        public String getActionDescription() {
            return "粗暴地让[npc.cock+]在[npc2.namePos]的[npc2.tussy+]中进出。";
        }

        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "FuckTussy", // change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),

                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };


    public static final SexAction PENIS_FUCKING_TUSSY_SUB_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.FOUR_HIGH,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY)),
            SexParticipantType.NORMAL,
            SexPace.SUB_NORMAL) {

        @Override
        public String getActionTitle() {
            return "干[npc2.herHim]的尾穴";
        }

        @Override
        public String getActionDescription() {
            return "继续干[npc2.namePos]的[npc2.tussy+]。";
        }

        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "FuckTussy", // change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),

                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction PENIS_FUCKING_TUSSY_SUB_EAGER = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.FOUR_HIGH,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY)),
            SexParticipantType.NORMAL,
            SexPace.SUB_EAGER) {

        @Override
        public String getActionTitle() {
            return "热切干尾穴";
        }

        @Override
        public String getActionDescription() {
            return "热切地用[npc.cock+]抽插[npc2.namePos]的[npc2.tussy+]。";
        }

        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "FuckTussy", // change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),

                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction PENIS_FUCKING_TUSSY_SUB_RESIST = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.TWO_LOW,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY)),
            SexParticipantType.NORMAL,
            SexPace.SUB_RESISTING) {

        @Override
        public String getActionTitle() {
            return "抗拒干尾穴";
        }

        @Override
        public String getActionDescription() {
            return "试图将你的[npc.cock]从[npc2.namePos]的[npc2.tussy+]中抽出。";
        }

        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "FuckTussy", // change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),

                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };


    public static final SexAction PENIS_FUCKING_TUSSY_STOP = new SexAction(
            SexActionType.STOP_ONGOING,
            ArousalIncrease.TWO_LOW,
            ArousalIncrease.TWO_LOW,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY)),
            SexParticipantType.NORMAL) {

        @Override
        public String getActionTitle() {
            return "停止干尾穴";
        }

        @Override
        public String getActionDescription() {
            return "将你的[npc.cock+]从[npc2.namePos]的[npc2.tussy+]中抽出，停止干[npc2.herHim]。";
        }
        @Override
        public String getDescription() {
            if (Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) == SexPace.DOM_ROUGH) {
                return TranslationUtils.getStringFor("sex","FuckTussyStopRough",true);
            }

            return TranslationUtils.getStringFor("sex", "FuckTussyStop", true);
        }
    };

    public static final SexAction TUSSY_MILK_PENIS_START = new SexAction(
            SexActionType.START_ONGOING,
            ArousalIncrease.FOUR_HIGH,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.TUSSY, SexAreaPenetration.PENIS)),
            SexParticipantType.NORMAL) {

        GameCharacter actor = Main.sex.getCharacterPerformingAction();
        @Override
        public String getActionTitle() {

            if(Main.sex.getSexPace(actor)==SexPace.DOM_NORMAL || Main.sex.getSexPace(actor)==SexPace.DOM_ROUGH ||  Main.sex.getSexPace(actor)==SexPace.DOM_ROUGH ) {
                return "榨精阴茎";
            } else {
                return "被干尾穴";
            }
        }

        @Override
        public String getActionDescription() {
            if(Main.sex.getSexPace(actor)==SexPace.DOM_NORMAL || Main.sex.getSexPace(actor)==SexPace.DOM_ROUGH ||  Main.sex.getSexPace(actor)==SexPace.DOM_ROUGH ) {
                return "将[npc2.namePos]的[npc2.cock+]滑入你的[npc.tussy+]内，开始榨精。";
            } else {
                return "将[npc2.namePos]的[npc2.cock+]滑入你的[npc.tussy+]内，被干。";
            }
        }
        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "TussyMilkStart",// change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),
                    false// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction TUSSY_MILK_PENIS_DOM_GENTLE = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.FOUR_HIGH,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.TUSSY, SexAreaPenetration.PENIS)),
            SexParticipantType.NORMAL,
            SexPace.DOM_GENTLE) {

        @Override
        public String getActionTitle() {
            return "温柔尾穴榨精";
        }

        @Override
        public String getActionDescription() {
            return "用你的[npc.tussy+]温柔地榨取[npc2.her]的[npc2.cock+]。";
        }
        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "TussyMilk", // change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),
                    false// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction TUSSY_MILK_PENIS_DOM_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.FOUR_HIGH,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.TUSSY, SexAreaPenetration.PENIS)),
            SexParticipantType.NORMAL,
            SexPace.DOM_NORMAL) {
        @Override
        public String getActionTitle() {
            return "尾穴榨精";
        }

        @Override
        public String getActionDescription() {
            return "用你的[npc.tussy+]热切地榨取[npc2.her]的[npc2.cock+]。";
        }
        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "TussyMilk", // change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),

                    false// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction TUSSY_MILK_PENIS_DOM_ROUGH = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.FOUR_HIGH,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.TWO_HORNY,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.TUSSY, SexAreaPenetration.PENIS)),
            SexParticipantType.NORMAL,
            SexPace.DOM_ROUGH) {

        @Override
        public String getActionTitle() {
            return "粗暴尾穴榨精";
        }

        @Override
        public String getActionDescription() {
            return "用你的[npc.tussy+]粗暴地榨取[npc2.her]的[npc2.cock+]。";
        }

        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "TussyMilk", // change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),

                    false// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };


    public static final SexAction TUSSY_MILK_PENIS_SUB_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.FOUR_HIGH,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.TUSSY, SexAreaPenetration.PENIS)),
            SexParticipantType.NORMAL,
            SexPace.SUB_NORMAL) {

        @Override
        public String getActionTitle() {
            return "被干尾穴";
        }

        @Override
        public String getActionDescription() {
            return "用你的[npc.tussy+]套在[npc2.namePos]的[npc2.cock+]上套弄。";
        }

        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "TussyMilk", // change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),

                    false// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction TUSSY_MILK_PENIS_SUB_EAGER = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.FOUR_HIGH,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.TUSSY, SexAreaPenetration.PENIS)),
            SexParticipantType.NORMAL,
            SexPace.SUB_EAGER) {

        @Override
        public String getActionTitle() {
            return "热切被干尾穴";
        }

        @Override
        public String getActionDescription() {
            return "热切地用你的[npc.tussy+]套在[npc2.namePos]的[npc2.cock+]上套弄。";
        }


        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "TussyMilk", // change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),

                    false// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };

    public static final SexAction TUSSY_MILK_PENIS_SUB_RESIST = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.TWO_LOW,
            ArousalIncrease.FOUR_HIGH,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.TUSSY, SexAreaPenetration.PENIS)),
            SexParticipantType.NORMAL,
            SexPace.SUB_RESISTING) {

        @Override
        public String getActionTitle() {
            return "抗拒被干尾穴";
        }

        @Override
        public String getActionDescription() {
            return "试着将你的[npc.tussy+]从[npc2.namePos]的[npc2.cock+]移开。";
        }

        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "TussyMilk", // change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),

                    false// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };


    public static final SexAction TUSSY_MILK_PENIS_STOP = new SexAction(
            SexActionType.STOP_ONGOING,
            ArousalIncrease.TWO_LOW,
            ArousalIncrease.TWO_LOW,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.TUSSY, SexAreaPenetration.PENIS)),
            SexParticipantType.NORMAL) {

        @Override
        public String getActionTitle() {
            return "停止被干尾穴";
        }

        @Override
        public String getActionDescription() {
            return "让[npc2.name]把[npc2.her]的[npc2.cock]从你的[npc.tussy+]中抽出来。";
        }
        @Override
        public String getDescription() {
            if (Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) == SexPace.DOM_ROUGH) {
                return TranslationUtils.getStringFor("sex","TussyMilkStopRough",false);
            }

            return TranslationUtils.getStringFor("sex", "TussyMilkStop", false);
        }
    };
}