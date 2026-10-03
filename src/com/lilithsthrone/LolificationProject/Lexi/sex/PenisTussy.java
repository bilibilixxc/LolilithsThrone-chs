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
            return "Tease tussy";
        }

        @Override
        public String getActionDescription() {
            return "Tease [npc2.name] by sliding the [npc.cockHead] of your [npc.cock] over [npc2.her] [npc2.tussy+].";
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
            return "Tease penis";
        }

        @Override
        public String getActionDescription() {
            return "Tease [npc2.name] by sliding the entrance of [npc.her] [npc.tussy+] over [npc2.her] [npc2.cockHead+].";
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
            return "Tussy Control";
        }
        @Override
        public String getActionDescription() {
            return "Squeeze your internally-muscled tussy down around [npc2.namePos] [npc2.cock].";
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
            return "Fuck [npc2.herHim] tussy";
        }

        @Override
        public String getActionDescription() {
            return "Sink your [npc.cock+] into [npc2.namePos] [npc2.tussy+] and start fucking [npc2.herHim].";
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
            return "Gentle tussy fucking";
        }

        @Override
        public String getActionDescription() {
            return "Gently slide your [npc.cock] in and out of [npc2.namePos] [npc2.tussy+].";
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
            return "Fuck [npc2.herHim] tussy";
        }

        @Override
        public String getActionDescription() {
            return "Continue thrusting your [npc.cock+] in and out of [npc2.namePos] [npc2.tussy+].";
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
            return "Rough tussy fucking";
        }

        @Override
        public String getActionDescription() {
            return "Roughly thrust your [npc.cock+] in and out of [npc2.namePos] [npc2.tussy+].";
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
            return "Fuck [npc2.herHim] tussy";
        }

        @Override
        public String getActionDescription() {
            return "Continue fucking [npc2.namePos] [npc2.tussy+].";
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
            return "Eager tussy fucking";
        }

        @Override
        public String getActionDescription() {
            return "Eagerly pump your [npc.cock+] in and out of [npc2.namePos] [npc2.tussy+].";
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
            return "Resist tussy fucking";
        }

        @Override
        public String getActionDescription() {
            return "Try to pull your [npc.cock] out of [npc2.namePos] [npc2.tussy+].";
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
            return "Stop tussy fucking";
        }

        @Override
        public String getActionDescription() {
            return "Pull your [npc.cock+] out of [npc2.namePos] [npc2.tussy+] and stop fucking [npc2.herHim].";
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
                return "Milk penis";
            } else {
                return "Get tussy fucked";
            }
        }

        @Override
        public String getActionDescription() {
            if(Main.sex.getSexPace(actor)==SexPace.DOM_NORMAL || Main.sex.getSexPace(actor)==SexPace.DOM_ROUGH ||  Main.sex.getSexPace(actor)==SexPace.DOM_ROUGH ) {
                return "Slide [npc2.namePos] [npc2.cock+] into your [npc.tussy+] and start milking.";
            } else {
                return "Slide [npc2.namePos] [npc2.cock+] into your [npc.tussy+] and get fucked.";
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
            return "Gently tussy milking";
        }

        @Override
        public String getActionDescription() {
            return "Gently milk [npc2.her] [npc2.cock+] with your [npc.tussy+].";
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
            return "Tussy milking";
        }

        @Override
        public String getActionDescription() {
            return "Eagerly milk [npc2.her] [npc2.cock+] with your [npc.tussy+].";
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
            return "Rough tussy milking";
        }

        @Override
        public String getActionDescription() {
            return "Roughly milk [npc2.her] [npc2.cock+] with your [npc.tussy+].";
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
            return "Tussy fucked";
        }

        @Override
        public String getActionDescription() {
            return "Fuck your [npc.tussy+] on [npc2.namePos] [npc2.cock+].";
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
            return "Eagerly tussy fucked";
        }

        @Override
        public String getActionDescription() {
            return "Eagerly fuck your [npc.tussy+] on [npc2.namePos] [npc2.cock+].";
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
            return "Resist being tussy fucked";
        }

        @Override
        public String getActionDescription() {
            return "Try and pull your [npc.tussy+] away from [npc2.namePos] [npc2.cock+].";
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
            return "Stop being tussy fucked";
        }

        @Override
        public String getActionDescription() {
            return "Get [npc2.name] to pull [npc2.her] [npc2.cock] out of your [npc.tussy+].";
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
