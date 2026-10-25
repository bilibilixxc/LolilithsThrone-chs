package com.lilithsthrone.LolificationProject.Lexi.sex;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

import static com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PlayerTalk.getOfferResponse;
import static com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PlayerTalk.getRequestResponse;

public class LexiPlayerTalk {
    public static final SexAction PLAYER_OFFER_TUSSY = new SexAction(
            SexActionType.SPEECH_WITH_ALTERNATIVE,
            ArousalIncrease.ONE_MINIMUM,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            null,
            SexParticipantType.NORMAL) {

        @Override
        public String getActionTitle() {
            return "提供尾穴";
        }

        @Override
        public String getActionDescription() {
            if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
                return "摆动你的尾巴，试图引诱[npc2.name]使用你的尾穴。";
            }
            return "告诉[npc2.name]你希望[npc2.herHim]使用你的尾穴。";
        }

        @Override
        public boolean isBaseRequirementsMet() {
            return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
                    && Main.game.getPlayer().hasTussy()
                    && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.TUSSY, true)
                    && !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.TUSSY))
                    && Main.sex.getCharacterPerformingAction().isPlayer();
        }

        @Override
        public String getDescription() {
            UtilText.nodeContentSB.setLength(0);

            if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
                UtilText.nodeContentSB.append(
                        UtilText.returnStringAtRandom(
                                "由于[npc.namePos]嘴巴被堵住，[npc.she] [npc.verb(resort)]向[npc2.name]挥舞[npc.her] [npc.tail]，试图让[npc2.herHim]来使用[npc.her] [npc.tussy+]。",
                                "因为[npc.her]嘴巴被堵住，[npc.name] [npc.verb(fall)]只是简单地用[npc.her] [npc.tail]做出暗示性的动作，以引诱[npc2.name]来使用[npc.her] [npc.tussy+]。")
                                +"<br/><br/>");
            } else {
                UtilText.nodeContentSB.append("你摆出一副你能做出的最恳求的表情，向[npc2.name]喊道："
                        +UtilText.returnStringAtRandom(
                        "[npc.speech(求你了，用我的尾穴吧！)]",
                        "[npc.speech(用我的小尾穴吧，求你了！)]")
                        +"<br/><br/>");
            }

            UtilText.nodeContentSB.append(getOfferResponse(
                    ((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.TUSSY)),
                    Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
                    "tussy"));

            return UtilText.nodeContentSB.toString();
        }

        @Override
        public void applyEffects() {
            if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.TUSSY))) {
                ((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.TUSSY)));

            } else if(!Main.sex.isDom(Main.game.getPlayer())) {
                Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.TUSSY));
            }
        }
    };

    public static final SexAction PLAYER_REQUEST_TUSSY = new SexAction(
            SexActionType.SPEECH_WITH_ALTERNATIVE,
            ArousalIncrease.ONE_MINIMUM,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            null,
            SexParticipantType.NORMAL) {

        @Override
        public String getActionTitle() {
            return "请求使用尾穴";
        }

        @Override
        public String getActionDescription() {
            if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
                return "靠近[npc2.namePos]的尾穴，试图向[npc2.herHim]表明这正是你想要的。";
            }
            return "告诉[npc2.name]你想使用[npc2.her]的尾穴。";
        }

        @Override
        public boolean isBaseRequirementsMet() {
            return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
                    && Main.sex.getCharacterTargetedForSexAction(this).hasTussy()
                    && Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.TUSSY, Main.game.getPlayer())
                    && Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.TUSSY, true)
                    && !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.TUSSY, null))
                    && Main.sex.getCharacterPerformingAction().isPlayer()
                    && !Main.sex.isDom(Main.game.getPlayer());
        }

        @Override
        public String getDescription() {
            UtilText.nodeContentSB.setLength(0);

            if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
                UtilText.nodeContentSB.append(
                        UtilText.returnStringAtRandom(
                                "由于[npc.namePos]嘴巴被堵住，[npc.she] [npc.verb(resort)]试图靠近[npc2.namePos] [npc2.tussy+]，向[npc2.herHim]表明这正是[npc.sheIs]想要的。",
                                "因为[npc.her]嘴巴被堵住，[npc.name] [npc.verb(fall)]只是简单地靠近[npc2.namePos] [npc2.tussy+]，[npc.she] [npc.verb(try)]以此表示这正是[npc.she] [npc.verb(want)]的。")
                                +"<br/><br/>");
            } else {
                UtilText.nodeContentSB.append("你摆出一副你能做出的最恳求的表情，向[npc2.name]喊道："
                        +UtilText.returnStringAtRandom(
                        "[npc.speech(求你了，我想用你的尾穴！)]",
                        "[npc.speech(我想用你的尾穴，求你了！)]")
                        +"<br/><br/>");
            }

            UtilText.nodeContentSB.append(getRequestResponse(
                    ((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.TUSSY, null)),
                    Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
                    "tussy"));

            return UtilText.nodeContentSB.toString();
        }

        @Override
        public void applyEffects() {
            if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.TUSSY, null))) {
                ((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.TUSSY, null)));

            } else if(!Main.sex.isDom(Main.game.getPlayer())) {
                Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.TUSSY, null));
            }
        }
    };

}