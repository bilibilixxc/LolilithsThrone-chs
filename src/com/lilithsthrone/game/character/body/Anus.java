package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAnusType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Anus implements BodyPartInterface {
	
	// Asshole variables:
	protected AbstractAnusType type;
	public OrificeAnus orificeAnus;
	public boolean bleached;
	public BodyHair assHair;

	public Anus(AbstractAnusType type, int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		
		orificeAnus = new OrificeAnus(wetness, capacity, depth, elasticity, plasticity, virgin, type.getDefaultRacialOrificeModifiers());
		
		bleached = false;
		assHair = BodyHair.ZERO_NONE;
	}

	public Anus(Anus anusToCopy) {
		this.type = anusToCopy.type;
		
		this.orificeAnus = new OrificeAnus(anusToCopy.orificeAnus);
		
		this.bleached = anusToCopy.bleached;
		this.assHair = anusToCopy.assHair;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}
	
	@Override
	public String getName(GameCharacter gc) {
		return type.getName(gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return type.getNamePlural(gc);
	}

	@Override
	public String getDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<String>();
		
		for(OrificeModifier om : orificeAnus.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		String wetnessDescriptor = orificeAnus.getWetness(owner).getDescriptor();
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
			if(Main.sex.hasLubricationTypeFromAnyone(owner, SexAreaOrifice.ANUS)) {
				wetnessDescriptor = "湿润";
			}
		}
		descriptorList.add(wetnessDescriptor);
		if(owner.getAssHair().getValue()>=BodyHair.FOUR_NATURAL.getValue() && Main.game.isAssHairEnabled()) {
			descriptorList.add("多毛");
		}
		
		if(owner.isAnusFeral()) {
			descriptorList.add(Util.randomItemFrom(Util.newArrayListOfValues(
					"兽态",
					owner.getAssRace().getName(owner.getBody(), true)+"-",
					"兽性",
					"似兽")));
		} else {
			descriptorList.add(type.getDescriptor(owner));
		}
		
		if(owner.getBodyMaterial().getPartDescriptors()!=null && !owner.getBodyMaterial().getPartDescriptors().isEmpty()) {
			descriptorList.add(Util.randomItemFrom(owner.getBodyMaterial().getPartDescriptors()));
		}
		
		descriptorList.add(Capacity.getCapacityFromValue(orificeAnus.getStretchedCapacity()).getDescriptor().replaceAll(" ", "-"));

		return Util.randomItemFrom(descriptorList);
	}

	@Override
	public AbstractAnusType getType() {
		return type;
	}
	
	public void setType(AbstractAnusType type) {
		this.type = type;
	}
	
	public OrificeAnus getOrificeAnus() {
		return orificeAnus;
	}


	public boolean isBleached() {
		return bleached;
	}
	
	public String setAssBleached(GameCharacter owner, boolean bleached) {
		if(this.bleached == bleached) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		this.bleached = bleached;
		
		if(bleached) {
			return UtilText.parse(owner,
					"<p>[style.boldTfSex([npc.NamePos]的肛门已经被漂白了！)]</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[style.boldTfSex([npc.NamePos]的肛门已经不再被漂白了！)]</p>");
		}
	}

	public BodyHair getAssHair() {
		return assHair;
	}
	
	public Covering getAssHairType(GameCharacter owner) {
		return owner.getCovering(owner.getBodyHairCoveringType(owner.getAssType().getRace()));
	}
	
	public String setAssHair(GameCharacter owner, BodyHair assHair) {
		if(!isCharacterInitialised(owner)) {
			this.assHair=assHair;
			return "";
		}
		String transformation = "";

		if(!this.getType().isAssHairAllowed()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(由于[npc.namePos]的肛门类型不允许在[npc.her]的肛门附近生长毛发，所以无事发生……)]</p>");
		}
		
		if(getAssHair() == assHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
			
		} else {
			switch(assHair) {
				case ZERO_NONE:
					transformation = UtilText.parse(owner, "<p>[npc.namePos]肛穴的周边已经不再有"+getAssHairType(owner).getFullDescription(owner, true)+"的痕迹了。</p>");
					break;
				case ONE_STUBBLE:
					transformation = UtilText.parse(owner, "<p>[npc.Name]的肛穴周边有一小块"+getAssHairType(owner).getFullDescription(owner, true)+"。</p>");
					break;
				case TWO_MANICURED:
					transformation = UtilText.parse(owner, "<p>[npc.Name]的肛穴周边有一小片打理整齐的"+getAssHairType(owner).getFullDescription(owner, true)+"。</p>");
					break;
				case THREE_TRIMMED:
					transformation = UtilText.parse(owner, "<p>[npc.Name]的肛穴周边有一小片打理过的"+getAssHairType(owner).getFullDescription(owner, true)+"。</p>");
					break;
				case FOUR_NATURAL:
					transformation = UtilText.parse(owner, "<p>[npc.Name]的肛穴周边有一小片自然生长的"+getAssHairType(owner).getFullDescription(owner, true)+"。</p>");
					break;
				case FIVE_UNKEMPT:
					transformation = UtilText.parse(owner, "<p>[npc.Name]的肛穴周边有一小片蓬乱的"+getAssHairType(owner).getFullDescription(owner, true)+"。</p>");
					break;
				case SIX_BUSHY:
					transformation = UtilText.parse(owner, "<p>[npc.Name]的肛穴周边有一小片茂密的"+getAssHairType(owner).getFullDescription(owner, true)+"。</p>");
					break;
				case SEVEN_WILD:
					transformation = UtilText.parse(owner, "<p>[npc.Name]的肛穴周边有一小片繁密茂盛的"+getAssHairType(owner).getFullDescription(owner, true)+"。</p>");
					break;
			}
		}
		
		this.assHair = assHair;
		
		return transformation;
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Anus.class) && getType().getRace().isFeralPartsAvailable());
	}

}