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
            return "Offer tussy";
        }

        @Override
        public String getActionDescription() {
            if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
                return "Wave your tail around in an attempt to entice [npc2.name] into using your tussy.";
            }
            return "Tell [npc2.name] that you'd like [npc2.herHim] to use your tussy.";
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
                                "As [npc.namePos] mouth is blocked, [npc.she] [npc.verb(resort)] to waving [npc.her] [npc.tail] out at [npc2.name] in an attempt to get [npc2.herHim] to use [npc.her] [npc.tussy+].",
                                "With [npc.her] mouth being blocked, [npc.name] [npc.verb(fall)] back on simply making a suggestive move with [npc.her] [npc.tail] in order to entice [npc2.name] into using [npc.her] [npc.tussy+].")
                                +"<br/><br/>");
            } else {
                UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
                        +UtilText.returnStringAtRandom(
                        "[npc.speech(Please, use my tussy!)]",
                        "[npc.speech(Use my little tussy, please!)]")
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
            return "Request tussy";
        }

        @Override
        public String getActionDescription() {
            if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
                return "Move closer to [npc2.namePos] tussy in an attempt to show [npc2.herHim] that that's what you want.";
            }
            return "Tell [npc2.name] that you'd like to use [npc2.her] tussy.";
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
                                "As [npc.namePos] mouth is blocked, [npc.she] [npc.verb(resort)] to trying to move closer to [npc2.namePos] [npc2.tussy+] in an attempt to show [npc2.herHim] that that's what [npc.sheIs] interested in.",
                                "With [npc.her] mouth being blocked, [npc.name] [npc.verb(fall)] back on simply moving closer to [npc2.namePos] [npc2.tussy+] as [npc.she] [npc.verb(try)] to indicate that that's what [npc.she] [npc.verb(want)].")
                                +"<br/><br/>");
            } else {
                UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
                        +UtilText.returnStringAtRandom(
                        "[npc.speech(Please, I want to use your tussy!)]",
                        "[npc.speech(I want to use your tussy, please!)]")
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
