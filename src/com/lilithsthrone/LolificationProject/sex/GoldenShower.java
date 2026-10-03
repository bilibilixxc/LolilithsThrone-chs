package com.lilithsthrone.LolificationProject.sex;

import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.LolificationProject.ModCommon.OtherRegistrars;
import com.lilithsthrone.LolificationProject.ModCommon.TranslationUtils;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

import java.util.HashMap;

public class GoldenShower {
    public static final SexAction GOLDEN_SHOWER_START = new SexAction(
            SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
            ArousalIncrease.TWO_LOW,
            ArousalIncrease.TWO_LOW,
            CorruptionLevel.THREE_DIRTY,
            new HashMap<>()/*Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaOrifice.MOUTH))*/,
            SexParticipantType.NORMAL) {

        @Override
        public boolean isBaseRequirementsMet() {
            //Rewritten by Ybyx
            return ModProps.isPeeingEnabled()
                    && (Main.sex.isOrificeFree(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)
                    || Main.sex.isOrificeFree(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA))
                    && Main.sex.getCharacterPerformingAction().getFetishDesire(OtherRegistrars.FETISH_PEE_GIVING).getValue() >= 3;
        }

        @Override
        public String getActionTitle() {
            return "Golden Shower";
        }

        @Override
        public String getActionDescription() {
            return Main.getProperties().hasValue(PropertyValue.sillyMode) ? "Make it rain!" : "Cover them in your stream.";
        }

        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
                    "peeOnOther", // change to what the sex action will be called in short form, must be unique.
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()), // probably double check this
                    true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
            );
        }

        @Override
        public void applyEffects() {
            Shower(Main.sex.getCharacterTargetedForSexAction(this));
        }
    };

    public static final SexAction REQUEST_GOLDEN_SHOWER_START = new SexAction(
            SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
            ArousalIncrease.TWO_LOW,
            ArousalIncrease.TWO_LOW,
            CorruptionLevel.THREE_DIRTY,
            new HashMap<>()/*Util.newHashMapOfValues(new Util.Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaOrifice.MOUTH))*/,
            SexParticipantType.NORMAL) {

        @Override
        public boolean isBaseRequirementsMet() {
            //Rewritten by Ybyx
            return ModProps.isPeeingEnabled()
                    && (Main.sex.isOrificeFree(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()), SexAreaOrifice.URETHRA_PENIS)
                    || Main.sex.isOrificeFree(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()), SexAreaOrifice.URETHRA_VAGINA))
                    && Main.sex.getCharacterPerformingAction().getFetishDesire(OtherRegistrars.FETISH_PEE_RECEIVING).getValue() >= 3
                    && Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()).getFetishDesire(OtherRegistrars.FETISH_PEE_GIVING).getValue() >= 2;
        }

        @Override
        public String getActionTitle() {
            return "Get a Shower";
        }

        @Override
        public String getActionDescription() {
            return Main.getProperties().hasValue(PropertyValue.sillyMode) ? "Request a warm stream of golden glory." : "Request them to cover you in urine.";
        }

        @Override
        public String getDescription() {
            return TranslationUtils.getStringFor(
                    "sex",
                    "peeOnOther",
                    Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()),
                    false
            );
        }

        @Override
        public void applyEffects() {
            Shower(Main.sex.getTargetedPartner(Main.sex.getCharacterTargetedForSexAction(this)));
        }
    };

    public static void Shower(GameCharacter c) {
        c.addWetSlot(InventorySlot.CHEST);
        c.addWetSlot(InventorySlot.HEAD);
        c.addWetSlot(InventorySlot.LEG);
        c.addWetSlot(InventorySlot.GROIN);
        c.addWetSlot(InventorySlot.FOOT);
        if (!c.getInventorySlotsConcealed(c).containsKey(InventorySlot.CHEST) && c.getClothingInSlot(InventorySlot.CHEST) != null)
            c.getClothingInSlot(InventorySlot.CHEST).setWet(c, true);
        if (!c.getInventorySlotsConcealed(c).containsKey(InventorySlot.HEAD) && c.getClothingInSlot(InventorySlot.HEAD) != null)
            c.getClothingInSlot(InventorySlot.HEAD).setWet(c, true);
        if (!c.getInventorySlotsConcealed(c).containsKey(InventorySlot.LEG) && c.getClothingInSlot(InventorySlot.LEG) != null)
            c.getClothingInSlot(InventorySlot.LEG).setWet(c, true);
        if (!c.getInventorySlotsConcealed(c).containsKey(InventorySlot.GROIN) && c.getClothingInSlot(InventorySlot.GROIN) != null)
            c.getClothingInSlot(InventorySlot.GROIN).setWet(c, true);
        if (!c.getInventorySlotsConcealed(c).containsKey(InventorySlot.FOOT) && c.getClothingInSlot(InventorySlot.FOOT) != null)
            c.getClothingInSlot(InventorySlot.FOOT).setWet(c, true);
    }
}