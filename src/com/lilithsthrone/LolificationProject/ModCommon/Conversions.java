package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.LolificationProject.ConfigStuff.Configurations;
import com.lilithsthrone.LolificationProject.Library;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryAttributeChange;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.PlaceType;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static com.lilithsthrone.game.character.GameCharacter.MINIMUM_AGE;
import static com.lilithsthrone.utils.Util.random;

@SuppressWarnings("all")
public class Conversions {
    // Initial Height Deviations from Realistic, so if you want smaller people this is where it starts
    private static int playerDeviation = ModProps.heightDeviations;
    private static int heightGrowthCapInMonths = 180;

    private static void refreshValues() {
        playerDeviation = ModProps.heightDeviations;

        heightGrowthCapInMonths = (ModProps.heightAgeCap * 12);
    }

    public static void applyGrowth(NPC npc) {
        int realAge = npc.getAgeValue();
        int apparentAge = npc.getAppearsAsAgeValue();
        int calcAge = ModProps.isBodyAppearanceBased() ? apparentAge : realAge;
//        int oldHeight = npc.getHeightValue();
        modifyHeight(npc, apparentAge, realAge);
//        int newHeight = npc.getHeightValue(); Future update, work hours can make weight loss, or growth, as can feeding... survival mode?
//        if (newHeight != oldHeight) {
//            int sizeRange = (calcAge < 18 ? 22 - calcAge : 10) >> 1;
//            npc.setBodySize(npc.getBodySizeValue() + ((random.nextInt(-sizeRange, sizeRange)) - npc.averageWorkHours) >> 1);// >>1 does +50% or -150% of value
//            npc.setMuscle(npc.getMuscleValue() + ((random.nextInt(-sizeRange, sizeRange) + npc.averageWorkHours) / 4));
//        }
    }

    public static NPC applyAgeEffects(NPC npc) {
        return (NPC) applyAgeEffects(npc, false);
    }

    public static PlayerCharacter applyAgeEffects(PlayerCharacter npc) {
        return (PlayerCharacter) applyAgeEffects(npc, false, true);
    }

    public static GameCharacter applyAgeEffects(GameCharacter npc, boolean bypassModdedCheck) {
        return applyAgeEffects(npc, bypassModdedCheck, false);
    }

    public static GameCharacter applyAgeEffects(GameCharacter npc, boolean bypassModdedCheck, boolean bypassUniqueCheck) {
        if ((npc.haveWeModded && !bypassModdedCheck)/* || !Configurations.DEGEN_MODE*/) return npc;
        npc.haveWeModded = true;
        if (npc.isUnique() && !npc.isPlayer() && !ModProps.hasValue(PropertyValue.originalAge)) {
            npc.setAge(Math.max(MINIMUM_AGE, npc.getAgeValue() - (18 + MINIMUM_AGE)));
        }

        if (npc == null || npc.getBody() == null || (!bypassUniqueCheck && npc.isUnique()))
            return npc;

        if (ModProps.hasValue(PropertyValue.changeApparentAge)) {
            int offset = ModProps.ageAppearanceRange + (npc.getRace() == Race.DEMON | npc.getRace() == Race.SLIME ? ModProps.amorphousAgeRange : 0);
            int newAge = random.nextInt(npc.getAgeValue() - offset, npc.getAgeValue() + offset);
            if (ModProps.hasValue(PropertyValue.excludeAmorphous) && (npc.getRace() == Race.SLIME || npc.getRace() == Race.DEMON)) {
                npc.setAgeAppearanceAbsolute(newAge);
            }
        }

        if (ModProps.hasValue(PropertyValue.forceOrgasmicDrain) && Util.random.nextInt(100) <= ModProps.orgasmicLevelDrainChance)
            npc.addSpecialPerk(Perk.ORGASMIC_LEVEL_DRAIN);


        int realAge = npc.getAgeValue();
        int apparentAge = npc.getAppearsAsAgeValue();
        int age = (ModProps.isBodyAppearanceBased() ? apparentAge : realAge);

        int ageInMonths = Math.max(1, ModProps.isHeightAppearanceBased() ? apparentAge * 12 : realAge * 12);
        handleVirginity(npc);

        // Removes body hair
        if (ageInMonths < 220 && random.nextInt(0, ageInMonths) <= 120) {// Age 10 and lower 0 hair, older less likely to lose hair, age 15 = 180 months,
            // random (0, 180) <= 120 = 66% chance to be hairless
            npc.setPubicHair(BodyHair.ZERO_NONE);
            npc.setUnderarmHair(BodyHair.ZERO_NONE);
            npc.setAssHair(BodyHair.ZERO_NONE);
            npc.setFacialHair(BodyHair.ZERO_NONE);
        }

        // Fertility and Virility
        if (age <= 12) {
            float v = npc.getAttributeValue(Attribute.VIRILITY);
            float f = npc.getAttributeValue(Attribute.FERTILITY);
            if (f > 0.15f && random.nextInt(100) >= ModProps.loliFertility)
                npc.setAttribute(Attribute.FERTILITY, age > 8 ? age - 7 : 0, false);

            if (v > 0.15f && random.nextInt(100) >= ModProps.shotaEndowment)
                npc.setAttribute(Attribute.VIRILITY, age > 6 ? age - 5 : 0, false);

            if (ModProps.shotaEndowment <= -1)
                npc.setAttribute(Attribute.VIRILITY, ModProps.shotaEndowment, false);

            if (ModProps.loliFertility <= -1)
                npc.setAttribute(Attribute.FERTILITY, ModProps.loliFertility, false);

            if(npc.isPlayer()) {
                EventLogEntry eve =
                        new EventLogEntry("<span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Core Attributes</span>",
                                "<span>Your Virility is now <span style='color:"+ PresetColour.GENERIC_NEUTRAL.toWebHexString()+";'>"+npc.getAttributeValue(Attribute.VIRILITY) +
                                        "</span> due to your age.</span>" + "<br>" +
                                        "<span>Your Fertility is now <span style='color:"+ PresetColour.GENERIC_NEUTRAL.toWebHexString()+";'>"+npc.getAttributeValue(Attribute.FERTILITY) +
                                        "</span> due to your age.</span>");
                Main.game.addEvent(eve, true);
            }
        }

        if (!npc.isPlayer() && npc.getAttributeValue(Attribute.FERTILITY) > 0.01f)
            randomPregnancy(npc);


        // Don't Modify Body Shape on Shape Amorphous Entities
        if (ModProps.hasValue(PropertyValue.excludeAmorphous) && (npc.getRace() == Race.SLIME || npc.getRace() == Race.DEMON)) {
            if (npc.getSubspecies() == Subspecies.IMP_ALPHA || npc.getSubspecies() == Subspecies.IMP)
                npc = modifyHeight(npc, apparentAge, realAge);
            return npc;
        }

        // Height
        npc = modifyHeight(npc, apparentAge, realAge);

        // Body Size
        if (age <= 16 && ModProps.changeBody()) {
            if (age > 13) { // 13 - 16
                npc.setBodySize(random.nextInt(75) + 8);// Between ZERO and FIVE see enum BodySize 20 points between sizes
                npc.setMuscle(random.nextInt(80));
            } else if (age > 10) { // 10 - 13
                npc.setBodySize(random.nextInt(50) + 6);// Between ZERO and TWO
                npc.setMuscle(random.nextInt(60));
            } else if (age > 7) { // 8 - 10
                npc.setBodySize(random.nextInt(42) + 4); // Between ZERO and TWO
                npc.setMuscle(random.nextInt(50));
            } else if (age > 4) { // 7 and under, lower the age, higher chance of size One, adjusted for chubby children
                npc.setBodySize(random.nextInt(40) + 10); // Between ZERO and THREE
                npc.setMuscle(random.nextInt(40));
            } else {
                npc.setBodySize(random.nextInt(30) + 15); // Between ONE and FOUR
                npc.setMuscle(random.nextInt(30));
            }
        }

        // Breasts
        if (npc.hasBreasts() && ModProps.changeBreasts()) {
            if (age < 16) {
                int loliOppaiShift = random.nextInt(100) <= ModProps.loliOppai ? random.nextInt(4) : 0;
                int virginityShift = npc.isNippleVirgin() ? 2 : 0;
                if (age > 12)
                    npc.setBreastSize(CupSize.FLAT.getMeasurement() + random.nextInt(virginityShift, 8 + virginityShift) + loliOppaiShift);
                else if (age > 8)
                    npc.setBreastSize(CupSize.FLAT.getMeasurement() + random.nextInt(virginityShift, 5 + virginityShift) + loliOppaiShift);
                else
                    npc.setBreastSize(CupSize.FLAT.getMeasurement() + random.nextInt(virginityShift, 3 + virginityShift) + loliOppaiShift);
            }
            if (ModProps.changeNipples()) {
                if (npc.getBreastSize().getMeasurement() <= CupSize.C.getMeasurement()) {
                    npc.setNippleCapacity(random.nextFloat(Capacity.ONE_EXTREMELY_TIGHT.getMinimumValue(),
                            npc.isNippleVirgin() ? Capacity.ONE_EXTREMELY_TIGHT.getMaximumValue(false) : Capacity.TWO_TIGHT.getMaximumValue(false)), false);
                    npc.setNippleElasticity(npc.isNippleVirgin() ? OrificeElasticity.ZERO_UNYIELDING.getValue() :
                            random.nextInt(OrificeElasticity.ONE_RIGID.getValue(), OrificeElasticity.FIVE_STRETCHY.getValue()));
                    npc.setNipplePlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
                    npc.setNippleSize(random.nextBoolean() || npc.isNippleVirgin() ? NippleSize.ONE_SMALL : NippleSize.ZERO_TINY);
                }

                if (npc.getBreastSize().getMeasurement() <= CupSize.A.getMeasurement()) {
                    npc.setNippleCapacity(npc.isNippleVirgin() ? Capacity.ZERO_IMPENETRABLE.getMinimumValue() :
                            random.nextFloat(Capacity.ONE_EXTREMELY_TIGHT.getMinimumValue(), Capacity.TWO_TIGHT.getMaximumValue(false)), false);
                    npc.setNippleElasticity(npc.isNippleVirgin() ? OrificeElasticity.ZERO_UNYIELDING.getValue() :
                            random.nextInt(OrificeElasticity.ONE_RIGID.getValue(), OrificeElasticity.FIVE_STRETCHY.getValue()));
                    npc.setNipplePlasticity(OrificePlasticity.TWO_TENSILE.getValue());
                    npc.setNippleSize(NippleSize.ZERO_TINY);
                }
            }
        }
        npc.setNippleStretchedCapacity(0);

        // Vagina
        if (npc.hasVagina() && ModProps.changeVagina()) {
            float minCap = npc.isVaginaVirgin() ? 0f : 2f;
            int elaOff = npc.isVaginaVirgin() ? 0 : 1;
            int ureElaOff = npc.isVaginaUrethraVirgin() ? 0 : 1;
            float minUreCap = npc.isVaginaUrethraVirgin() ? 0f : 2f;
            int depOff = npc.isVaginaVirgin() ? 0 : 1;
            if (age < 16 && age > 12) {
                float cap = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + random.nextFloat(6) + minCap;
                npc.setVaginaCapacity(cap, false);
                npc.setVaginaLabiaSize(random.nextBoolean() || npc.isVaginaVirgin() ? LabiaSize.ONE_SMALL : LabiaSize.TWO_AVERAGE);
                npc.setVaginaElasticity(elaOff + (random.nextBoolean() ? OrificeElasticity.TWO_FIRM.getValue() : OrificeElasticity.ONE_RIGID.getValue()));
                npc.setVaginaPlasticity(random.nextInt(100) > 60 ? OrificePlasticity.ZERO_RUBBERY.getValue() : random.nextBoolean() ? OrificePlasticity.ONE_SPRINGY.getValue() : OrificePlasticity.TWO_TENSILE.getValue());
                npc.setVaginaClitorisSize(random.nextInt(6));
                npc.setVaginaDepth(depOff + random.nextInt(3));
                npc.setClitorisGirth(random.nextInt(3));
                if (ModProps.changeUrethra()) {
                    npc.setVaginaUrethraElasticity(ureElaOff + (random.nextBoolean() ? OrificeElasticity.TWO_FIRM.getValue() : OrificeElasticity.ONE_RIGID.getValue()));
                    npc.setVaginaUrethraPlasticity(random.nextInt(100) > 60 ? OrificePlasticity.ZERO_RUBBERY.getValue() : random.nextBoolean() ? OrificePlasticity.ONE_SPRINGY.getValue() : OrificePlasticity.TWO_TENSILE.getValue());
                    npc.setVaginaUrethraDepth(npc.getVaginaDepth().getValue());
                    npc.setVaginaUrethraCapacity(minUreCap + random.nextInt(3), false);
                }
            } else if (age < 13 && age > 9) {
                float cap = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + random.nextFloat(4f) + minCap;
                npc.setVaginaCapacity(cap, false);
                npc.setVaginaLabiaSize(random.nextBoolean() || npc.isVaginaVirgin() ? LabiaSize.ZERO_TINY : LabiaSize.ONE_SMALL);
                npc.setVaginaElasticity((random.nextBoolean() ? OrificeElasticity.ONE_RIGID.getValue() : OrificeElasticity.ZERO_UNYIELDING.getValue()) + (npc.isVaginaVirgin() ? 0 : 1));
                npc.setVaginaPlasticity(random.nextInt(100) > 60 ? OrificePlasticity.ZERO_RUBBERY.getValue() : random.nextBoolean() ? OrificePlasticity.ONE_SPRINGY.getValue() : OrificePlasticity.TWO_TENSILE.getValue());
                npc.setVaginaClitorisSize(random.nextInt(4));
                npc.setVaginaDepth(depOff + random.nextInt(3));
                npc.setClitorisGirth(random.nextInt(2));
                if (ModProps.changeUrethra()) {
                    npc.setVaginaUrethraElasticity(ureElaOff + (random.nextBoolean() ? OrificeElasticity.ONE_RIGID.getValue() :
                            OrificeElasticity.ZERO_UNYIELDING.getValue()));
                    npc.setVaginaUrethraPlasticity(random.nextInt(100) > 60 ? OrificePlasticity.ZERO_RUBBERY.getValue() : random.nextBoolean() ? OrificePlasticity.ONE_SPRINGY.getValue() : OrificePlasticity.TWO_TENSILE.getValue());
                    npc.setVaginaUrethraDepth(npc.getVaginaDepth().getValue());
                    npc.setVaginaUrethraCapacity(minUreCap + random.nextInt(2), false);
                }
            } else if (age <= 9) {
                float cap = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + random.nextInt(4) + minCap;
                npc.setVaginaCapacity(cap, false);
                npc.setVaginaLabiaSize(random.nextBoolean() || npc.isVaginaVirgin() ? LabiaSize.ZERO_TINY : LabiaSize.ONE_SMALL);
                npc.setVaginaElasticity(elaOff + (random.nextBoolean() ? OrificeElasticity.ONE_RIGID.getValue() : OrificeElasticity.ZERO_UNYIELDING.getValue()));
                npc.setVaginaPlasticity(random.nextInt(100) > 60 ? OrificePlasticity.ZERO_RUBBERY.getValue() : random.nextBoolean() ? OrificePlasticity.ONE_SPRINGY.getValue() : OrificePlasticity.TWO_TENSILE.getValue());
                npc.setVaginaClitorisSize(random.nextInt(2));
                npc.setVaginaDepth(depOff + random.nextInt(2));
                npc.setClitorisGirth(random.nextInt(1));
                if (ModProps.changeUrethra()) {
                    npc.setVaginaUrethraElasticity(ureElaOff + (random.nextBoolean() ? OrificeElasticity.ONE_RIGID.getValue() :
                            OrificeElasticity.ZERO_UNYIELDING.getValue()));
                    npc.setVaginaUrethraPlasticity(random.nextInt(100) > 60 ? OrificePlasticity.ZERO_RUBBERY.getValue() : random.nextBoolean() ? OrificePlasticity.ONE_SPRINGY.getValue() : OrificePlasticity.TWO_TENSILE.getValue());
                    npc.setVaginaUrethraDepth(npc.getVaginaDepth().getValue());
                    npc.setVaginaUrethraCapacity(minUreCap + random.nextFloat(1), false);
                }
            }
        }
        npc.setVaginaStretchedCapacity(0);
        npc.setVaginaUrethraStretchedCapacity(0);

        // Penis
        if (npc.hasPenis() && ModProps.changePenis()) {
            int elaOff = npc.isUrethraVirgin() ? 0 : 1;
            if (age < 16) {
                if (age > 12) {
                    npc.setPenisSize(PenisLength.TWO_AVERAGE.getMinimumValue() + random.nextInt(3) + (shouldCockBeLarger(npc) ? random.nextInt(4) : 0));
                    if (ModProps.changeUrethra()) {
                        npc.setUrethraElasticity(random.nextBoolean() ? OrificeElasticity.TWO_FIRM.getValue() : OrificeElasticity.ONE_RIGID.getValue() + (shouldCockBeLarger(npc) ? random.nextInt(2) : 0));
                        npc.setUrethraPlasticity(random.nextInt(100) > 30 ? OrificePlasticity.ZERO_RUBBERY.getValue() : random.nextBoolean() ? OrificePlasticity.ONE_SPRINGY.getValue() : OrificePlasticity.TWO_TENSILE.getValue());
                        npc.setUrethraDepth(npc.getPenisSize().getMedianValue());
                    }
                } else if (age < 13 && age > 9) {
                    npc.setPenisSize(PenisLength.ONE_TINY.getMinimumValue() + random.nextInt(3) + (shouldCockBeLarger(npc) ? random.nextInt(5) : 0));
                    if (ModProps.changeUrethra()) {
                        npc.setUrethraElasticity(random.nextBoolean() ? OrificeElasticity.ONE_RIGID.getValue() : OrificeElasticity.ZERO_UNYIELDING.getValue() + (shouldCockBeLarger(npc) ? random.nextInt(2) : 0));
                        npc.setUrethraPlasticity(random.nextInt(100) > 50 ? OrificePlasticity.ZERO_RUBBERY.getValue() : random.nextBoolean() ? OrificePlasticity.ONE_SPRINGY.getValue() : OrificePlasticity.TWO_TENSILE.getValue());
                        npc.setUrethraDepth(npc.getPenisSize().getMedianValue());
                    }
                } else if (age <= 9) {
                    npc.setPenisSize(Math.max(1, PenisLength.ZERO_MICROSCOPIC.getMinimumValue() + random.nextInt(3) + (shouldCockBeLarger(npc) ? random.nextInt(6) : 0)));
                    if (ModProps.changeUrethra()) {
                        npc.setUrethraElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue() + (shouldCockBeLarger(npc) ? random.nextInt(2) : 0));
                        npc.setUrethraPlasticity(random.nextInt(100) > 70 ? OrificePlasticity.ZERO_RUBBERY.getValue() : random.nextBoolean() ? OrificePlasticity.ONE_SPRINGY.getValue() : OrificePlasticity.TWO_TENSILE.getValue());
                        npc.setUrethraDepth(npc.getPenisSize().getMedianValue());
                    }
                }
                if (ModProps.shotaEndowment > 0) {
                    float shotaChange = 1 + (ModProps.shotaEndowment / 100f);
                    npc.setPenisSize(Math.round(npc.getPenisRawSizeValue() * shotaChange));
                    if (ModProps.changeUrethra()) {
                        npc.setUrethraDepth(npc.getPenisSize().getMedianValue());
                    }
                }
            }
            npc.setUrethraElasticity(npc.getUrethraElasticity().getValue() + elaOff);
            switch (npc.getPenisSize()) { // Logically Replaced when Testicle code dealt with, but gives a base value for when no testicles are present, and/or multipliers by testicle size. This emulates a "testosterone" check
                case NEGATIVE_UTILITY_VALUE -> npc.setPenisCumStorage(CumProduction.ZERO_NONE.getMedianValue());
                case ZERO_MICROSCOPIC -> npc.setPenisCumStorage(random.nextInt(CumProduction.ZERO_NONE.getMinimumValue(), CumProduction.ONE_TRICKLE.getMedianValue()));
                case ONE_TINY ->
                        npc.setPenisCumStorage(random.nextInt(CumProduction.ZERO_NONE.getMinimumValue(), CumProduction.TWO_SMALL_AMOUNT.getMedianValue()));
                case TWO_AVERAGE ->
                        npc.setPenisCumStorage(random.nextInt(CumProduction.ONE_TRICKLE.getMinimumValue(), CumProduction.THREE_AVERAGE.getMedianValue()));
                case THREE_LARGE ->
                        npc.setPenisCumStorage(random.nextInt(CumProduction.TWO_SMALL_AMOUNT.getMinimumValue(), CumProduction.FOUR_LARGE.getMedianValue()));
                case FOUR_HUGE ->
                        npc.setPenisCumStorage(random.nextInt(CumProduction.THREE_AVERAGE.getMinimumValue(), CumProduction.FIVE_HUGE.getMedianValue()));
                case FIVE_ENORMOUS ->
                        npc.setPenisCumStorage(random.nextInt(CumProduction.FOUR_LARGE.getMinimumValue(), CumProduction.SIX_EXTREME.getMedianValue()));
                case SIX_GIGANTIC ->
                        npc.setPenisCumStorage(random.nextInt(CumProduction.FIVE_HUGE.getMinimumValue(), CumProduction.SEVEN_MONSTROUS.getMedianValue()));
                case SEVEN_STALLION ->
                        npc.setPenisCumStorage(random.nextInt(CumProduction.SIX_EXTREME.getMinimumValue(), CumProduction.SEVEN_MONSTROUS.getMaximumValue()));
            }
        }
        npc.setPenisStretchedCapacity(0);

        // Testicles
        if (npc.getTesticleCount() > 0 && ModProps.changeTesticle()) {
            if (age < 16) {
                if (age > 12) {
                    int testeSize = 2 + ((random.nextInt(100) <= ModProps.shotaEndowment) ? 1 : 0);
                    npc.setTesticleSize(testeSize);
                } else if (age > 9) {
                    int testeSize = 1 + ((random.nextInt(100) <= ModProps.shotaEndowment) ? 1 : 0);
                    npc.setTesticleSize(testeSize);
                } else {
                    int testeSize = 1 + ((random.nextInt(100) <= ModProps.shotaEndowment) ? 1 : 0);
                    npc.setTesticleSize(testeSize);
                }
            }
            switch (npc.getTesticleSize()) {
                case ZERO_VESTIGIAL -> npc.setPenisCumStorage(random.nextInt(CumProduction.ZERO_NONE.getMinimumValue(), CumProduction.ONE_TRICKLE.getMedianValue()));
                case ONE_TINY -> npc.setPenisCumStorage(random.nextInt(CumProduction.ZERO_NONE.getMinimumValue(), CumProduction.TWO_SMALL_AMOUNT.getMedianValue()));
                case TWO_AVERAGE -> npc.setPenisCumStorage(random.nextInt(CumProduction.ONE_TRICKLE.getMinimumValue(), CumProduction.THREE_AVERAGE.getMedianValue()));
                case THREE_LARGE -> npc.setPenisCumStorage(random.nextInt(CumProduction.TWO_SMALL_AMOUNT.getMinimumValue(), CumProduction.FOUR_LARGE.getMedianValue()));
                case FOUR_HUGE -> npc.setPenisCumStorage(random.nextInt(CumProduction.THREE_AVERAGE.getMinimumValue(), CumProduction.FIVE_HUGE.getMedianValue()));
                case FIVE_MASSIVE -> npc.setPenisCumStorage(random.nextInt(CumProduction.FOUR_LARGE.getMinimumValue(), CumProduction.SIX_EXTREME.getMedianValue()));
                case SIX_GIGANTIC -> npc.setPenisCumStorage(random.nextInt(CumProduction.FIVE_HUGE.getMinimumValue(), CumProduction.SEVEN_MONSTROUS.getMedianValue()));
                case SEVEN_ABSURD -> npc.setPenisCumStorage(random.nextInt(CumProduction.SIX_EXTREME.getMinimumValue(), CumProduction.SEVEN_MONSTROUS.getMaximumValue()));
            }
        }

        // Throat
        if (age < 16 && ModProps.changeThroat()) {
            boolean bornForDeepThroating = random.nextBoolean();
            if (age > 12)
                npc.setFaceCapacity(npc.isFaceVirgin() ? bornForDeepThroating ? Capacity.TWO_TIGHT.getMinimumValue() : Capacity.ONE_EXTREMELY_TIGHT.getMinimumValue() : Capacity.THREE_SLIGHTLY_LOOSE.getMinimumValue(), false);
            else if (age > 8)
                npc.setFaceCapacity(npc.isFaceVirgin() ? bornForDeepThroating ? Capacity.TWO_TIGHT.getMinimumValue() : Capacity.ONE_EXTREMELY_TIGHT.getMinimumValue() : Capacity.TWO_TIGHT.getMinimumValue(), false);
            else
                npc.setFaceCapacity(npc.isFaceVirgin() ? bornForDeepThroating ? Capacity.TWO_TIGHT.getMinimumValue() : Capacity.ZERO_IMPENETRABLE.getMinimumValue() : Capacity.TWO_TIGHT.getMinimumValue(), false);
        }
        npc.setFaceStretchedCapacity(0);

        // Hips
        if (age <= 16 && ModProps.changeHips()) {
            if (age > 13) // 14 - 16
                npc.setHipSize(random.nextInt(10) > 6 ? HipSize.THREE_GIRLY : random.nextBoolean() ? HipSize.FOUR_WOMANLY : HipSize.TWO_NARROW);
            else if (age > 10) // 11 - 13
                npc.setHipSize(random.nextInt(10) > 6 ? HipSize.TWO_NARROW : random.nextBoolean() ? HipSize.THREE_GIRLY : HipSize.ONE_VERY_NARROW);
            else if (age > 7) // 8 - 10
                npc.setHipSize(random.nextInt(10) > 6 ? HipSize.ONE_VERY_NARROW : random.nextBoolean() ? HipSize.TWO_NARROW : HipSize.ZERO_NO_HIPS);
            else // 7 and under, lower the age, higher chance of size Zero
                npc.setHipSize(random.nextInt(10) > age ? HipSize.ZERO_NO_HIPS : random.nextBoolean() ? HipSize.TWO_NARROW : HipSize.ONE_VERY_NARROW);
        }

        // Ass
        if (age <= 16 && ModProps.changeAss()) {
            if (age > 13)
                npc.setAssSize(random.nextInt(10) > 6 ? AssSize.THREE_NORMAL : random.nextBoolean() ? AssSize.FOUR_LARGE : AssSize.TWO_SMALL);
            else if (age > 10)
                npc.setAssSize(random.nextInt(10) > 6 ? AssSize.TWO_SMALL : random.nextBoolean() ? AssSize.THREE_NORMAL : AssSize.ONE_TINY);
            else if (age > 7)
                npc.setAssSize(random.nextInt(10) > 6 ? AssSize.ONE_TINY : random.nextBoolean() ? AssSize.TWO_SMALL : AssSize.ZERO_FLAT);
            else
                npc.setAssSize(random.nextInt(10) > age ? AssSize.ZERO_FLAT : random.nextBoolean() ? AssSize.TWO_SMALL : AssSize.ONE_TINY);
        }

        // Anus
        if (age <= 16 && ModProps.changeAnus()) {
            float offset = npc.isAnalVirgin() ? 0f : 2f;
            offset += random.nextFloat(0, 2);//if lucky they're born for anal
            if (age > 12)
                npc.setAssCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue() + offset, false);
            else if (age > 8)
                npc.setAssCapacity(Capacity.ZERO_IMPENETRABLE.getMedianValue() + offset, false);
            else
                npc.setAssCapacity(Capacity.ZERO_IMPENETRABLE.getMinimumValue() + offset, false);
        }
        npc.setAssStretchedCapacity(0);
        return npc;
    }

    private static boolean shouldVirgin(Femininity fem, int ageInMonths, String virginityType) {
        float virginRate = VirginityMenu.getVirginityRate(fem, ageInMonths, virginityType);
        float rngFlt = random.nextFloat();
        boolean virgin = rngFlt < virginRate;
        if (Configurations.LOG_VIRGIN_GEN)
            Logger.logVirginCalc(virginityType + "Rate is : " + virginRate + " rng : " + random.nextFloat() + " = " + (virgin ? "Virgin" : "Not Virgin"));
        return virgin;
    }

    private static void handleVirginity(GameCharacter npc) {
        if (npc.isPlayer()) return;
        int ageInMonthsForVirginity = Math.toIntExact(ChronoUnit.MONTHS.between(npc.getBirthday(), Main.game.getDateNow()));
        if (Configurations.LOG_VIRGIN_GEN)
            Logger.logVirginCalc("Doing Virginity Checks for : " + npc.getNameIgnoresPlayerKnowledge() + " at age " + ageInMonthsForVirginity + " months");

        // Virginities
        npc.setVaginaVirgin(shouldVirgin(npc.getFemininity(), ageInMonthsForVirginity, "vaginaVirgin"));
        npc.setPenisVirgin(shouldVirgin(npc.getFemininity(), ageInMonthsForVirginity, "penisVirgin"));
        npc.setAnalVirgin(shouldVirgin(npc.getFemininity(), ageInMonthsForVirginity, "analVirgin"));
        npc.setFaceVirgin(shouldVirgin(npc.getFemininity(), ageInMonthsForVirginity, "faceVirgin"));
        npc.setAssVirgin(shouldVirgin(npc.getFemininity(), ageInMonthsForVirginity, "assVirgin"));
        npc.setSpinneretVirgin(shouldVirgin(npc.getFemininity(), ageInMonthsForVirginity, "spinneretVirgin"));
        npc.setNippleVirgin(shouldVirgin(npc.getFemininity(), ageInMonthsForVirginity, "nippleVirgin"));
        npc.setNippleCrotchVirgin(shouldVirgin(npc.getFemininity(), ageInMonthsForVirginity, "nippleCrotchVirgin"));
        npc.setUrethraVirgin(shouldVirgin(npc.getFemininity(), ageInMonthsForVirginity, "urethraVirgin"));
        npc.setVaginaUrethraVirgin(shouldVirgin(npc.getFemininity(), ageInMonthsForVirginity, "vaginaUrethraVirgin"));
    }

    private static GameCharacter modifyHeight(GameCharacter npc, int apparentAge, int realAge) {
        if (!Configurations.DEGEN_MODE) return npc;
        refreshValues();
        Logger.logHeightCalc(System.lineSeparator());
        Logger.logHeightCalc("Player Setting Height Deviation is = " + playerDeviation + " cm");
        Logger.logHeightCalc("Player Setting Growth Cap is = " + heightGrowthCapInMonths + " months");
        Logger.logHeightCalc("NPC Birthday = " + npc.getBirthday().format(DateTimeFormatter.ISO_DATE_TIME));
        Logger.logHeightCalc("Game Startday = " + Main.game.startingDate.format(DateTimeFormatter.ISO_DATE_TIME));
        Logger.logHeightCalc("Adjusting NPC : " + npc.getName() + " - " + npc.getNameIgnoresPlayerKnowledge() + " - ID: " + npc.getId());
        Logger.logHeightCalc("NPC Race : " + npc.getRace().getName(false));

        int monthOffset = 12 * (apparentAge - realAge);
        Logger.logHeightCalc("Apparent Age offset = " + monthOffset);
        int minOffset = (MINIMUM_AGE * 12);
        Logger.logHeightCalc("Minimum Age offset = " + minOffset);
        int ageInMonths = minOffset + (int) ChronoUnit.MONTHS.between(npc.getBirthday(), Main.game.getDateNow()) + (ModProps.isHeightAppearanceBased() ? monthOffset : 0);
        Logger.logHeightCalc("NPC Age : " + ageInMonths + " months");
        ageInMonths = Math.max(1, Math.min(ageInMonths, heightGrowthCapInMonths)); // Capping max height by "age" in months
        Logger.logHeightCalc("NPC Age For Calculation : " + ageInMonths + " months");
        int origHeight = npc.getHeightValue();
        float heightDev = npc.heightDeviation == -1.0f ? origHeight / 178f : npc.heightDeviation;
        npc.heightDeviation = heightDev;
        Logger.logHeightCalc("NPC Original Height : " + origHeight + " cm");
        Logger.logHeightCalc("NPC Height Deviation : [" + heightDev + "] Calculated from " + origHeight + "cm / 178cm");

        byte gender = 0; // Fight me SJWs

        // 0 = neutral, 1 = female, -1 = male
        if (ModProps.isHeightApparentGender()) {
            if (npc.hasVagina())
                gender++;
            if (npc.hasPenis())
                gender--;
        } else {
            if (npc.getGender().getType() == PronounType.FEMININE)
                gender = 1;
            else if (npc.getGender().getType() == PronounType.MASCULINE)
                gender = -1;
            else gender = 0;
        }
        Logger.logHeightCalc("NPC Gender for Height : " + (gender == 0 ? "Androgynous" : gender == 1 ? "Feminine" : "Masculine") + " and is using : " + (ModProps.isHeightApparentGender() ? "Apparent Gender" : "Actual Gender"));

        float newHeight = calculateHeightByMonths(gender, ageInMonths);
        Logger.logHeightCalc("Height Chosen from WHO Data : " + newHeight + " cm");
        if (npc.monthsHeightCalculatedAt != 0 && npc.monthsHeightCalculatedAt - ageInMonths > 0) {
            Logger.logHeightCalc("NPC has aged : " + (npc.monthsHeightCalculatedAt - ageInMonths) + " months");
            newHeight = npc.getHeightValue();
            for (int i = 0; i < npc.monthsHeightCalculatedAt - ageInMonths; i++) {
                float growth = Math.min(0.01f, Math.max(0.5f, calculateHeightByMonths(gender, ageInMonths) - calculateHeightByMonths(gender, npc.monthsHeightCalculatedAt)));
                Logger.logHeightCalc("NPC has grown : " + growth + " on " + (npc.monthsHeightCalculatedAt + i) + " month of their life.");
                newHeight += growth;
            }
        }
        if (ModProps.isHeightRelative()) {
            Logger.logHeightCalc("Relative Height Setting is on Changing Height : Original Height = "
                    + origHeight + " | Generated Height = " + newHeight + " | Adjusted Height = " + (newHeight * heightDev));
            newHeight *= heightDev;
            if (ModProps.hasValue(PropertyValue.preventGiants) && origHeight < newHeight) {
                Logger.logHeightCalc("Prevent Giants is enabled, rolling back height to original height : " + origHeight);
                newHeight = origHeight; // To prevent stupid heights - Original Height = 264 | Generated Height = 193.9007 | Adjusted Height = 289.20782
            }
        }

        Logger.logHeightCalc("Applying Player Deviation : " + newHeight + " + " + playerDeviation + " = " + (newHeight + playerDeviation));
        newHeight += playerDeviation;

        if (newHeight != 0 || newHeight != origHeight) {
            if (npc.getSubspecies() == Subspecies.IMP_ALPHA) {
                Logger.logHeightCalc("Due to species being Alpha Imp applying user Alpha Imp setting : " + newHeight + " * " + (ModProps.aimpHMult / 100f) + " = " + Math.round(newHeight * (ModProps.aimpHMult / 100f)));
                npc.getBody().setHeight(Math.round(newHeight * ((float) ModProps.aimpHMult / 100f)));
            } else if (npc.getSubspecies() == Subspecies.IMP) {
                Logger.logHeightCalc("Due to species being Imp applying user Imp setting : " + newHeight + " * " + (ModProps.impHMult / 100f) + " = " + Math.round(newHeight * (ModProps.impHMult / 100f)));
                npc.getBody().setHeight(Math.round(newHeight * ((float) ModProps.impHMult / 100f)));
            } else {
                Logger.logHeightCalc("Finally applying Height to NPC : " + Math.round(newHeight) + "cm");
                npc.getBody().setHeight(Math.round(newHeight));
            }
        }

        npc.monthsHeightCalculatedAt = ageInMonths;
        return npc;
    }

    public static float calculateHeightByMonths(byte gender, int months) {
        if (months >= 220)
            months = 219;
        Logger.logHeightCalc("Age used for WHO base height : " + months + " months");
        return switch (gender) {
            case -1 -> {
                float min = Library.SD3MN[months], max = Library.SD3MM[months];
                Logger.logHeightCalc("Chosen Height Range : " + min + "-" + max + "cm");
                yield random.nextFloat(min, max);
            }
            case 1 -> {
                float min = Library.SD3MN[months], max = Library.SD3MM[months];
                Logger.logHeightCalc("Chosen Height Range : " + min + "-" + max + "cm");
                yield random.nextFloat(min, max);
            }
            default -> {
                float min = Library.SD3FN[months], max = Library.SD3MM[months];
                Logger.logHeightCalc("Chosen Height Range : " + min + "-" + max + "cm");
                yield random.nextFloat(min, max);
            }
        };
    }

    public static String getAgeDescriptor(GameCharacter npc) {
        AgeCategory apparentAge = npc.getAppearsAsAge();
        int apparentAgeValue = npc.getAppearsAsAgeValue();
        AgeCategory realAge = npc.getAge();
        int realAgeValue = npc.getAgeValue();
        return " [npc.She] [npc.verb(appear)] to be <span style='color:" + apparentAge.getColour().toWebHexString() + ";'>" //Color from age appearance
                + (!ModProps.isShowAgeEnabled() ? apparentAge.getName() + "</span>" : apparentAgeValue + " years old</span>" //Show age category if show age disabled
                + (Main.game.getPlayer().hasTrait(Perk.OBSERVANT, true) && ModProps.isShowTrueAgeEnabled() ? realAgeValue == apparentAgeValue //If show true age and observant with will pass the check to is equal
                ? " and, thanks to your observant perk, you can confirm that [npc.she] looks true to [npc.her] age."
                : " but, thanks to your observant perk, you can tell that [npc.sheIs] really <span style='color:"
                + realAge.getColour().toWebHexString() + ";'>" + (ModProps.isShowAgeEnabled() ? realAgeValue : realAge.getName()) + "</span>." : "."));
    }

    private static boolean shouldCockBeLarger(GameCharacter character) {
        return (character.getPenisRace() != Race.HORSE_MORPH || character.getPenisRace() != Race.DEMON || character.getPenisRace() != Race.REINDEER_MORPH || character.getPenisRace() != Race.COW_MORPH);
    }

    public static String getAdditionalDeterminer(GameCharacter gameCharacter, String nameText) {
        if (Main.game.isSillyMode() && gameCharacter.getHeightValue() >= 213 && (gameCharacter.getBodyShape().getRelatedMuscle() == Muscle.THREE_MUSCULAR || gameCharacter.getBodyShape().getRelatedMuscle() == Muscle.FOUR_RIPPED))
            return "7ft Wall of Muscle " + nameText;
        if (!ModProps.isShowEnhancedDescriptionEnabled()) return nameText;
        if (gameCharacter.getAppearsAsAgeValue() >= ModProps.oldLabelAge) {
            return "old " + nameText;
        } else if (gameCharacter.getAppearsAsAgeValue() <= ModProps.youngLabelAge) {
            if ((gameCharacter.getGender().isFeminine() && gameCharacter.getFemininity().isFeminine()) && gameCharacter.getAppearsAsAgeValue() <= ModProps.loliLabelAge)
                return nameText.trim().equalsIgnoreCase("woman") ? "Loli" : "loli " + nameText;
            if ((!gameCharacter.getGender().isFeminine() && gameCharacter.getFemininity() != Femininity.ANDROGYNOUS && !gameCharacter.getFemininity().isFeminine()) && gameCharacter.getAppearsAsAgeValue() <= ModProps.shotaLabelAge)
                return nameText.trim().equalsIgnoreCase("man") ? "Shota" : "shota " + nameText;
            return "young " + nameText;
        }
        return "adult " + nameText;
    }


    public static void randomPregnancy(GameCharacter npc) { // TODO Add other orifices
        int pregChance = npc.hasFetish(Fetish.FETISH_PREGNANCY) ? (ModProps.NPCPregChance * 2) : ModProps.NPCPregChance;
        // NPC is in GENERIC_EMPTY_TILE (holding location) or is player, elemental, slave, unique, or already pregnant or cannot become pregnant vaginally; skip
        if (npc.getLocationPlace().getPlaceType().equals(PlaceType.GENERIC_EMPTY_TILE)
                || npc.isPlayer() || npc.isSlave() || npc.isUnique() || npc.isElemental()
                || npc.isPregnant()
                || !npc.hasVagina() || npc.isVaginaVirgin())
            return;

        if (pregChance > Util.random.nextInt(100)) { // TODO Fix the duration issue - Done?
            npc.implantPregnantLitter(npc, SexAreaOrifice.VAGINA);
            int maxHourLength = (int)((Main.getProperties().pregnancyDuration * 7 * 24) / 2f);
            //this.addStatusEffect(StatusEffect.PREGNANT_0, (60 * 60) * (4 + Util.random.nextInt(5)));
            //target.addStatusEffect(PREGNANT_1, 60 * 60 * ((maxHourLength-12) + Util.random.nextInt(13)));
            npc.addStatusEffect((Math.random() > 0.5f ? StatusEffect.PREGNANT_1 : StatusEffect.PREGNANT_2), 60 * 60 * ((maxHourLength-12) + Util.random.nextInt(13)));
        }
    }
}