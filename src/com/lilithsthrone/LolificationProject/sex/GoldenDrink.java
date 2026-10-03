package com.lilithsthrone.LolificationProject.sex;

import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.LolificationProject.ModCommon.OtherRegistrars;
import com.lilithsthrone.LolificationProject.ModCommon.TranslationUtils;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.*;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

import java.util.HashMap;

public class GoldenDrink {
    public static final SexAction DRINK_PISS_START = new SexAction(
            SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.TWO_LOW,
            CorruptionLevel.THREE_DIRTY,
            new HashMap<>()/*Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.VAGINA, SexAreaOrifice.MOUTH))*/,
            SexParticipantType.NORMAL) {

        @Override
        public boolean isBaseRequirementsMet() {
            //Rewritten by Ybyx
            return ModProps.isPeeingEnabled()
                    && ((Main.sex.getOngoingSexAreas(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).contains(SexAreaOrifice.MOUTH)
                    || Main.sex.getOngoingSexAreas(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()), SexAreaOrifice.VAGINA, Main.sex.getCharacterPerformingAction()).contains(SexAreaOrifice.MOUTH)))
                    && Main.sex.getCharacterPerformingAction().getFetishDesire(OtherRegistrars.FETISH_PEE_RECEIVING).getValue() >= 3
                    && Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()).getFetishDesire(OtherRegistrars.FETISH_PEE_GIVING).getValue() >= 2;
        }

        @Override
        public String getActionTitle() {
            return "Get Refreshments";
        }

        @Override
        public String getActionDescription() {
            return "Quench your thirst from the source of relief.";
        }

        @Override
        public String getDescription() {
            UtilText.nodeContentSB.setLength(0);
            switch (Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
                case DOM_GENTLE:
                    UtilText.nodeContentSB.append(TranslationUtils.getStringFor("sex.dom.gentle.peeDrink"));
                    break;
                case DOM_NORMAL:
                case SUB_EAGER:
                    UtilText.nodeContentSB.append(TranslationUtils.getStringFor("sex.sub.eager.peeDrink"));
                    break;
                case DOM_ROUGH:
                    UtilText.nodeContentSB.append(TranslationUtils.getStringFor("sex.dom.rough.peeDrink"));
                    break;
                case SUB_NORMAL:
                    UtilText.nodeContentSB.append(TranslationUtils.getStringFor("sex.sub.normal.peeDrink"));
                    break;
                case SUB_RESISTING:
                    UtilText.nodeContentSB.append(TranslationUtils.getStringFor("sex.sub.resist.peeDrink"));
                    break;
                default:
                    break;
            }
            return UtilText.nodeContentSB.toString();
        }
    };

    public static final SexAction SERVE_PISS_START = new SexAction(
            SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
            ArousalIncrease.TWO_LOW,
            ArousalIncrease.THREE_NORMAL,
            CorruptionLevel.THREE_DIRTY,
            new HashMap<>()/*Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.VAGINA, SexAreaOrifice.MOUTH))*/,
            SexParticipantType.NORMAL) {

        @Override
        public boolean isBaseRequirementsMet() {
            //Rewritten by Ybyx
            return ModProps.isPeeingEnabled()
                    && ((Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction())).contains(SexAreaOrifice.MOUTH)
                    || Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction())).contains(SexAreaOrifice.MOUTH)))
                    && Main.sex.getCharacterPerformingAction().getFetishDesire(OtherRegistrars.FETISH_PEE_GIVING).getValue() >= 3;
        }

        @Override
        public String getActionTitle() {
            return "Give Refreshments";
        }

        @Override
        public String getActionDescription() {
            return Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)) == SexPace.SUB_RESISTING ? "Try to drown them in your piss." : "Relieve yourself to quench their thirst.";
        }

        @Override
        public String getDescription() {
            UtilText.nodeContentSB.setLength(0);
            switch (Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
                case DOM_GENTLE ->
                        UtilText.nodeContentSB.append(TranslationUtils.getStringFor("sex.dom.gentle.peeServe"));
                case DOM_NORMAL, SUB_EAGER ->
                        UtilText.nodeContentSB.append(TranslationUtils.getStringFor("sex.sub.eager.peeServe"));
                case DOM_ROUGH ->
                        UtilText.nodeContentSB.append(TranslationUtils.getStringFor("sex.dom.rough.peeServe"));
                case SUB_NORMAL ->
                        UtilText.nodeContentSB.append(TranslationUtils.getStringFor("sex.sub.normal.peeServe"));
                case SUB_RESISTING ->
                        UtilText.nodeContentSB.append(TranslationUtils.getStringFor("sex.sub.resist.peeServe"));
            }
            return UtilText.nodeContentSB.toString();
        }
    };
}