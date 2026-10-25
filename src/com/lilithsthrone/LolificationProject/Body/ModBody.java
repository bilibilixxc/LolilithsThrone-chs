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


        descriptionSB.append("在[npc.her] [npc.tail+]的末端，[npc.tailFullDescription(true)]上有一道[npc.tussyPrimaryColour(true)]的裂缝，可以张开，露出一条可以充当性器官使用的、[npc.tussyWetness]的肉质[npc.tussySecondaryColour(true)]内壁。");

        // Virgin/capacity:
        if(owner.isTussyVirgin()) {
            descriptionSB.append(" [npc.She][npc.has][style.colourExcellent(保留着[npc.her]尾穴的处女之身)]。");
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
                descriptionSB.append(" [style.colourArcane([npc.Name][npc.has]已经失去了[npc.her]尾穴的处女之身。)]");
                owner.setTussyVirgin(false);
            }
        }

        descriptionSB.append(" [npc.Her]尾穴"+ Capacity.getCapacityFromValue(tussy.getStretchedCapacity()).getDescriptor(true)+"在湿润的情况下可以舒适地容纳直径最大"
                + " [style.colourSex("+ Units.size(Capacity.getMaximumComfortableDiameter(tussy.getElasticity(), tussy.getRawCapacityValue(), true)) + ")]的物体。");

        if(Main.game.isPenetrationLimitationsEnabled()) {
            switch(owner.getTussyDepth()) {
                default:
                    descriptionSB.append(" 它是<span style='color:"+owner.getTussyDepth().getColour().toWebHexString()+";'>[npc.tussyDepth]</span>，");
                    break;
                case TWO_AVERAGE:
                    descriptionSB.append(" 它的深度处于<span style='color:"+owner.getTussyDepth().getColour().toWebHexString()+";'>平均水平</span>，");
                    break;
            }
            if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
                if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
                    descriptionSB.append(" 而且作为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，[npc.she]可以被压到[style.colourMinorGood(舒适地)]容纳最长"
                            + " [style.colourSex("+Units.size(owner.getTussyMaximumPenetrationDepthUncomfortable())+")]的物体。");
                } else {
                    descriptionSB.append(" 使[npc.herHim]能够[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getTussyMaximumPenetrationDepthComfortable())+")]的插入物，"
                            + " 并能够[style.colourMinorBad(不太舒适地)]容纳[style.colourSex("+Units.size(owner.getTussyMaximumPenetrationDepthUncomfortable())+")]的插入物。");
                }

            } else {
                if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
                    descriptionSB.append(" 而且作为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，加之[npc.her]身体由[npc.bodyMaterial]构成，"
                            + " [npc.she]可以被压到[style.colourMinorGood(舒适地)]容纳[style.colourSex(任意长度)]的物体。");
                } else {
                    descriptionSB.append(" 使[npc.herHim]能够[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getTussyMaximumPenetrationDepthComfortable())+")]的插入物，"
                            + " 而且由于[npc.her]身体由[npc.bodyMaterial]构成，[npc.she]可以被压到[style.colourMinorBad(不太舒适地)]容纳[style.colourSex(任意长度)]的物体。");
                }
            }
        }

        // Wetness:
        switch (tussy.getWetness(owner)) {
            case ZERO_DRY:
                descriptionSB.append(" 它[style.colourWetness(完全干燥，永远不会变湿)]，无论[npc.she]有多兴奋[npc.is]。");
                break;
            case ONE_SLIGHTLY_MOIST:
                descriptionSB.append(" 它[style.colourWetness(微微湿润)]，而[npc.she]需要巨大的刺激才能[npc.verb(get)]变湿。");
                break;
            case TWO_MOIST:
                descriptionSB.append(" 它[style.colourWetness(湿润)]，但[npc.she]仍然需要大量刺激才能[npc.verb(get)]变湿。");
                break;
            case THREE_WET:
                descriptionSB.append(" 它[style.colourWetness(湿润度适中)]，而且[npc.she]只需要少量的前戏就能让[npc.sheIs]足够湿润，以进行愉悦的插入。");
                break;
            case FOUR_SLIMY:
                descriptionSB.append(" 它总是[style.colourWetness(黏滑湿润)]，而且[npc.sheIs]随时都可以立刻进行插入。");
                break;
            case FIVE_SLOPPY:
                descriptionSB.append(" 它的表面总是覆盖着一层[style.colourWetness(黏液)]，而且在内部，[npc.her]尾穴永远[style.colourWetness(湿滑泥泞)]，几乎在恳求被肏。");
                break;
            case SIX_SOPPING_WET:
                descriptionSB.append(" [npc.Her]尾穴从来都不低于[style.colourWetness(湿透)]的程度，而且总有一小股[npc.her]的天然润滑液从里面不断滴落。");
                break;
            case SEVEN_DROOLING:
                descriptionSB.append(" [npc.Her]尾穴[style.colourWetness(湿到每一步[npc.she][npc.verb(take)]都会发出咕叽声响)]，而且不断有汁液从[npc.her]诱人的孔穴中流出。");
                break;
        }

        // Elasticity & plasticity:
        switch (tussy.getElasticity()) {
            case ZERO_UNYIELDING:
                descriptionSB.append(" 它[style.colourElasticity(极其坚韧，不易拉伸)]，");
                break;
            case ONE_RIGID:
                descriptionSB.append(" 它[style.colourElasticity(需要花费巨大的力气才能拉伸开)]，");
                break;
            case TWO_FIRM:
                descriptionSB.append(" 它[style.colourElasticity(不太容易拉伸)]，");
                break;
            case THREE_FLEXIBLE:
                descriptionSB.append(" 被插入时，它[style.colourElasticity(会不情愿地伸展开)]，");
                break;
            case FOUR_LIMBER:
                descriptionSB.append(" 它[style.colourElasticity(对拉伸有些抵抗力)]，");
                break;
            case FIVE_STRETCHY:
                descriptionSB.append(" 它[style.colourElasticity(相当容易拉伸)]，");
                break;
            case SIX_SUPPLE:
                descriptionSB.append(" 它[style.colourElasticity(非常容易拉伸)]，");
                break;
            case SEVEN_ELASTIC:
                descriptionSB.append(" 它[style.colourElasticity(极富弹性)]，");
                break;
        }
        descriptionSB.append(" 而在使用过后，它会"+tussy.getPlasticity().getDescription()+"。");

        for(OrificeModifier om : OrificeModifier.values()) {
            if(owner.hasTussyOrificeModifier(om)) {
                switch(om) {
                    case MUSCLE_CONTROL:
                        descriptionSB.append(" [npc.She][npc.has]一系列内部肌肉排列在[npc.her]尾穴的内壁上，让[npc.herHim]能够熟练地夹紧并握住任何侵入的物体。");
                        break;
                    case PUFFY:
                        descriptionSB.append(" 边缘已经肿胀起来，变得特别又大又肿。");
                        break;
                    case RIBBED:
                        descriptionSB.append(" [npc.her]尾穴内部排列着敏感的肉质褶皱，受到刺激时会给[npc.herHim]带来额外的快感。");
                        break;
                    case TENTACLED:
                        descriptionSB.append(" [npc.Her]尾穴里充满了细小的触须，它们仿佛自有意志般扭动蠕动。");
                        break;
                }
            }
        }

        return UtilText.parse(owner, descriptionSB.toString());
    }

}