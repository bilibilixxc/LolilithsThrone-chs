package com.lilithsthrone.LolificationProject.Lexi.sex;

import com.lilithsthrone.LolificationProject.ModCommon.TranslationUtils;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.*;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

public class PussyClean {
    public static final SexAction CLEAN_PUSSY = new SexAction(
            SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.THREE_NORMAL,
            CorruptionLevel.FOUR_LUSTFUL,
            Util.newHashMapOfValues(new Util.Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
            SexParticipantType.NORMAL) {
        @Override
        public boolean isBaseRequirementsMet() {
            return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!= SexPace.SUB_RESISTING;
        }
        @Override
        public String getActionTitle() {
            return "清理爱穴";
        }

        @Override
        public String getActionDescription() {
            return "用你的[npc.tongue]清理[npc2.name]的[npc2.pussy]。";
        }

        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "CleanPussy",// change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),
                    false// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }
    };
}