package com.lilithsthrone.LolificationProject.Body;

import com.lilithsthrone.LolificationProject.Lexi.character.body.OrificeTussy;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;

public class ModBody {
    public OrificeTussy tussy;

    public OrificeTussy getTussy() {
        return tussy;
    }


    public String getTussyDescription(GameCharacter owner) {
        if(!owner.hasTussy()) {
            return "";
        }

        StringBuilder descriptionSB = new StringBuilder(1024);


        descriptionSB.append("At the end of [npc.her] [npc.tail+] the [npc.tailFullDescription(true)] has a [npc.tussyPrimaryColour(true)] slit that can open up to reveal a [npc.tussyWetness] fleshy [npc.tussySecondaryColour(true)] interior that can be used as a sexual orifice.");

        // Virgin/capacity:
        if(owner.isTussyVirgin()) {
            descriptionSB.append(" [npc.She] [npc.has] [style.colourExcellent(retained [npc.her] tussy virginity)].");
        } else {
            boolean virginityLossFound = false;
            for(SexAreaPenetration pt : SexAreaPenetration.values()) {
                if(pt.isTakesVirginity()) {
                    if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.TUSSY, pt))!=null) {
                        descriptionSB.append(" [style.colourArcane("+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.TUSSY, pt)) + ")]");
                        virginityLossFound = true;
                        break;
                    }
                }
            }
            if(!virginityLossFound) {
                descriptionSB.append(" [style.colourArcane([npc.Name] [npc.has] lost [npc.her] tussy virginity.)]");
                owner.setTussyVirgin(false);
            }
        }

        descriptionSB.append(" [npc.Her] tussy is " + Capacity.getCapacityFromValue(tussy.getStretchedCapacity()).getDescriptor(true)+" and when lubricated can comfortably accommodate objects of up to"
                + " [style.colourSex("+ Units.size(Capacity.getMaximumComfortableDiameter(tussy.getElasticity(), tussy.getRawCapacityValue(), true)) + ")] in diameter.");

        if(Main.game.isPenetrationLimitationsEnabled()) {
            switch(owner.getTussyDepth()) {
                default:
                    descriptionSB.append(" It is <span style='color:"+owner.getTussyDepth().getColour().toWebHexString()+";'>[npc.tussyDepth]</span>,");
                    break;
                case TWO_AVERAGE:
                    descriptionSB.append(" It is of an <span style='color:"+owner.getTussyDepth().getColour().toWebHexString()+";'>average depth</span>,");
                    break;
            }
            if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
                if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
                    descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of a maximum length of "
                            + " [style.colourSex("+Units.size(owner.getTussyMaximumPenetrationDepthUncomfortable())+")].");
                } else {
                    descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getTussyMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
                            + " and [style.colourMinorBad(uncomfortably)] accommodate [style.colourSex("+Units.size(owner.getTussyMaximumPenetrationDepthUncomfortable())+")].");
                }

            } else {
                if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
                    descriptionSB.append(" and as [npc.sheIsFull] a "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", combined with the fact that [npc.her] body is made out of [npc.bodyMaterial],"
                            + " [npc.she] can be pushed to [style.colourMinorGood(comfortably)] accommodate objects of [style.colourSex(any length)].");
                } else {
                    descriptionSB.append(" allowing [npc.herHim] to [style.colourMinorGood(comfortably)] accommodate [style.colourSex("+Units.size(owner.getTussyMaximumPenetrationDepthComfortable())+")] of a penetrative object,"
                            + " and as [npc.her] body is made out of [npc.bodyMaterial], [npc.she] can be pushed to [style.colourMinorBad(uncomfortably)] accommodate objects of [style.colourSex(any length)].");
                }
            }
        }

        // Wetness:
        switch (tussy.getWetness(owner)) {
            case ZERO_DRY:
                descriptionSB.append(" It's [style.colourWetness(completely dry and never gets wet)], no matter how aroused [npc.she] [npc.is].");
                break;
            case ONE_SLIGHTLY_MOIST:
                descriptionSB.append(" It's [style.colourWetness(slightly moist)], and [npc.she] [npc.verb(need)] a huge amount of stimulation before [npc.she] [npc.verb(get)] wet.");
                break;
            case TWO_MOIST:
                descriptionSB.append(" It's [style.colourWetness(moist)], but [npc.she] still [npc.verb(need)] a lot of stimulation before [npc.she] [npc.verb(get)] wet.");
                break;
            case THREE_WET:
                descriptionSB.append(" It's of an [style.colourWetness(average wetness)], and [npc.she] only [npc.verb(need)] a small amount of foreplay before [npc.sheIs] wet enough for a pleasurable penetration.");
                break;
            case FOUR_SLIMY:
                descriptionSB.append(" It's always [style.colourWetness(slimy and wet)], and [npc.sheIs] ready for penetration at a moment's notice.");
                break;
            case FIVE_SLOPPY:
                descriptionSB.append(" Its surface is always coated in [style.colourWetness(slimy moisture)], and within, [npc.her] tussy is permanently [style.colourWetness(sloppy)] and practically begging to be fucked.");
                break;
            case SIX_SOPPING_WET:
                descriptionSB.append(" [npc.Her] tussy is never anything less than [style.colourWetness(sopping wet)], and a trickle of [npc.her] natural lubricant constantly dribbles from it.");
                break;
            case SEVEN_DROOLING:
                descriptionSB.append(" [npc.Her] tussy is [style.colourWetness(so wet that it audibly squelches with every step [npc.she] [npc.verb(take)])], and a constant stream of juices flow from [npc.her] inviting orifice.");
                break;
        }

        // Elasticity & plasticity:
        switch (tussy.getElasticity()) {
            case ZERO_UNYIELDING:
                descriptionSB.append(" It is [style.colourElasticity(extremely unyielding)],");
                break;
            case ONE_RIGID:
                descriptionSB.append(" It [style.colourElasticity(takes a huge amount of effort to stretch it out)],");
                break;
            case TWO_FIRM:
                descriptionSB.append(" It [style.colourElasticity(does not stretch very easily)],");
                break;
            case THREE_FLEXIBLE:
                descriptionSB.append(" It [style.colourElasticity(reluctantly stretches out)] when penetrated,");
                break;
            case FOUR_LIMBER:
                descriptionSB.append(" It is [style.colourElasticity(somewhat resistant to being stretched out)],");
                break;
            case FIVE_STRETCHY:
                descriptionSB.append(" It [style.colourElasticity(stretches out fairly easily)],");
                break;
            case SIX_SUPPLE:
                descriptionSB.append(" It [style.colourElasticity(stretches out very easily)],");
                break;
            case SEVEN_ELASTIC:
                descriptionSB.append(" It is [style.colourElasticity(extremely elastic)],");
                break;
        }
        descriptionSB.append(" and after being used, it "+tussy.getPlasticity().getDescription()+".");

        for(OrificeModifier om : OrificeModifier.values()) {
            if(owner.hasTussyOrificeModifier(om)) {
                switch(om) {
                    case MUSCLE_CONTROL:
                        descriptionSB.append(" [npc.She] [npc.has] a series of internal muscles lining the inside of [npc.her] tussy, allowing [npc.herHim] to expertly squeeze and grip down on any intruding object.");
                        break;
                    case PUFFY:
                        descriptionSB.append(" The rim has swollen up into being particularly big and puffy.");
                        break;
                    case RIBBED:
                        descriptionSB.append(" The inside of [npc.her] tussy is lined with sensitive, fleshy ribs, which grant [npc.herHim] extra pleasure when stimulated.");
                        break;
                    case TENTACLED:
                        descriptionSB.append(" [npc.Her] tussy is filled with tiny little tentacles, which wriggle and squirm with a mind of their own.");
                        break;
                }
            }
        }

        return UtilText.parse(owner, descriptionSB.toString());
    }

}
