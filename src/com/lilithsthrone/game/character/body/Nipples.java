package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractNippleType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Nipples implements BodyPartInterface {
	
	protected AbstractNippleType type;
	public OrificeNipples orificeNipples;
	protected NippleShape nippleShape;
	protected AreolaeShape areolaeShape;
	protected int areolaeSize;
	protected int nippleSize;
	public boolean pierced;
	public boolean crotchNipples;

	public Nipples(AbstractNippleType type, int nippleSize, NippleShape nippleShape, int areolaeSize, AreolaeShape areolaeShape, int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin, boolean crotchNipples) {
		this.type = type;
		this.nippleSize = nippleSize;
		this.nippleShape = nippleShape;
		this.areolaeShape = areolaeShape;
		this.areolaeSize = areolaeSize;
		orificeNipples = new OrificeNipples(wetness, capacity, depth, elasticity, plasticity, virgin, crotchNipples, type.getDefaultRacialOrificeModifiers());
		this.crotchNipples = crotchNipples;
	}

	public Nipples(Nipples nipplesToCopy) {
		this.type = nipplesToCopy.type;
		this.nippleSize = nipplesToCopy.nippleSize;
		this.nippleShape = nipplesToCopy.nippleShape;
		this.areolaeShape = nipplesToCopy.areolaeShape;
		this.areolaeSize = nipplesToCopy.areolaeSize;
		this.orificeNipples = new OrificeNipples(nipplesToCopy.orificeNipples);
		this.pierced = nipplesToCopy.pierced;
		this.crotchNipples = nipplesToCopy.crotchNipples;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}

	@Override
	public String getName(GameCharacter owner) {
		return getNamePlural(owner);
	}

	@Override
	public String getNameSingular(GameCharacter owner) {
		if(crotchNipples) {
			return type.getNameCrotchSingular(owner);
		} else {
			return type.getNameSingular(owner);
		}
	}

	@Override
	public String getNamePlural(GameCharacter owner) {
		if(crotchNipples) {
			return type.getNameCrotchPlural(owner);
		} else {
			return type.getNamePlural(owner);
		}
	}

	@Override
	public String getDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : orificeNipples.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		if(isCrotchNipples()) {
			if(owner.getNippleCrotchCovering()!=null) {
				descriptorList.add(owner.getCovering(owner.getNippleCrotchCovering()).getColourDescriptor(owner, false, false));
			}
			
			if(owner.isBreastCrotchFuckableNipplePenetration()) {
				switch(owner.getBreastCrotchMilkStorage().getAssociatedWetness()) {
					case ONE_SLIGHTLY_MOIST:
					case TWO_MOIST:
					case THREE_WET:
					case FOUR_SLIMY:
					case FIVE_SLOPPY:
					case SIX_SOPPING_WET:
					case SEVEN_DROOLING:
//						descriptorList.add(owner.getBreastCrotchMilkStorage().getAssociatedWetness().getDescriptor());
						descriptorList.add("乳汁状");
						break;
					default:
						break;
				}
			}
			
		} else {
			if(owner.getNippleCovering()!=null) {
				descriptorList.add(owner.getCovering(owner.getNippleCovering()).getColourDescriptor(owner, false, false));
			}
			
			if(owner.isBreastFuckableNipplePenetration()) {
				switch(owner.getBreastMilkStorage().getAssociatedWetness()) {
					case ONE_SLIGHTLY_MOIST:
					case TWO_MOIST:
					case THREE_WET:
					case FOUR_SLIMY:
					case FIVE_SLOPPY:
					case SIX_SOPPING_WET:
					case SEVEN_DROOLING:
//						descriptorList.add(owner.getBreastMilkStorage().getAssociatedWetness().getDescriptor());
						descriptorList.add("乳汁状");
						break;
					default:
						break;
				}
			}
		}
		
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
			if(Main.sex.hasLubricationTypeFromAnyone(owner, SexAreaOrifice.NIPPLE)) {
				descriptorList.add("湿漉漉");
			}
		}

		switch(this.getNippleShape()) {
			case INVERTED:
				descriptorList.add("内陷");
				break;
			case LIPS:
			case NORMAL:
			case VAGINA:
				break;
		}
		
		descriptorList.add(this.getNippleSize().getName());
		
		descriptorList.add(type.getDescriptor(owner));
		
		if(orificeNipples.getCapacity()!= Capacity.ZERO_IMPENETRABLE) {
			descriptorList.add(Capacity.getCapacityFromValue(orificeNipples.getStretchedCapacity()).getDescriptor().replaceAll(" ", "-"));
		}
		
		if(owner.getBodyMaterial().getPartDescriptors()!=null && !owner.getBodyMaterial().getPartDescriptors().isEmpty()) {
			descriptorList.add(Util.randomItemFrom(owner.getBodyMaterial().getPartDescriptors()));
		}
		
		descriptorList.removeIf(d->d==null || d.isEmpty());
		
		return Util.randomItemFrom(descriptorList);
	}

	@Override
	public AbstractNippleType getType() {
		return type;
	}

	public void setType(GameCharacter owner, AbstractNippleType type) {
		this.type = type;
	}

	public NippleSize getNippleSize() {
		return NippleSize.getNippleSizeFromInt(nippleSize);
	}
	
	public int getNippleSizeValue() {
		return nippleSize;
	}

	public String setNippleSize(GameCharacter owner, int nippleSize) {
		int boundNippleSize = Math.max(0, Math.min(nippleSize, NippleSize.FOUR_MASSIVE.getValue()));
		if(!isCharacterInitialised(owner)) {
			this.nippleSize = boundNippleSize;
			return "";
		}
		if(this.nippleSize == boundNippleSize) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.nipples]的大小并没有改变……)]</p>");
		}
		
		String transformation = "";
		
		if(this.nippleSize > boundNippleSize) {
			transformation = UtilText.parse(owner, "<p>当[npc.Name]感觉到[npc.nipples]升起一股舒缓的凉意时，[npc.she]忍不住惊呼一声，感觉到[npc.nipples]紧接着[style.boldShrink(缩小)]了。<br/>");
			
		} else {
			transformation = UtilText.parse(owner, "<p>当[npc.Name]感觉到[npc.nipples]升起一股脉动似的暖意时，[npc.she]忍不住惊呼一声，感觉到[npc.nipples]紧接着[style.boldGrow(变大)]了。<br/>");
		}
		
		this.nippleSize = boundNippleSize;

		return transformation + UtilText.parse(owner, "[npc.Name]现在有了[style.boldSex([npc.nippleSize]的[npc.nipples])]！</p>");
		
	}

	public NippleShape getNippleShape() {
		return nippleShape;
	}
	
	public String setNippleShape(GameCharacter owner, NippleShape nippleShape) {
		if(!isCharacterInitialised(owner)) {
			this.nippleShape = nippleShape;
			return "";
		}
		
		if(this.nippleShape == nippleShape) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.nipples]的形状并无改变……)]</p>");
		}
		
		String transformation = "";
		
		switch(nippleShape) {
			case INVERTED:
				transformation = "<p>"
									+ "[npc.Name] [npc.verb(let)] out an uncomfortable whine as [npc.her] [npc.nipples] start to grow sore and sensitive."
									+ " Before [npc.she] [npc.has] a chance to do anything else, [npc.her] nipples suddenly transform into normal-looking ones, before pulling inwards and inverting!<br/>"
									+ "[npc.NamePos] [npc.nipplesFullDescriptionColour] [npc.nipples] have transformed into [style.boldSex(inverted nipples)]!"
								+ "</p>";
				break;
			case NORMAL:
				transformation = "<p>"
									+ "[npc.Name] [npc.verb(let)] out an uncomfortable whine as [npc.her] [npc.nipples] start to grow sore and sensitive, before suddenly transforming into normal-looking nipples.<br/>"
									+ "[npc.NamePos] [npc.nipplesFullDescriptionColour] [npc.nipples] have transformed into [style.boldSex(normal nipples)]!"
								+ "</p>";
				break;
			case LIPS:
				transformation = "<p>"
									+ "[npc.Name] [npc.verb(let)] out an uncomfortable whine as [npc.her] [npc.nipples] start to grow sore and sensitive, before suddenly swelling up and transforming into juicy pairs of lips!<br/>"
									+ "[npc.NamePos] [npc.nipplesFullDescriptionColour] [npc.nipples] have transformed into [style.boldSex(lip-like lipples)], which [npc.she] can control just like regular lips!"
								+ "</p>";
				break;
			case VAGINA:
				transformation = "<p>"
									+ "[npc.Name] [npc.verb(let)] out an uncomfortable whine as [npc.her] [npc.nipples] start to grow sore and sensitive, before suddenly shifting and transforming into pseudo-pussies!<br/>"
									+ "[npc.NamePos] [npc.nipplesFullDescriptionColour] [npc.nipples] have transformed into [style.boldSex(vagina-like nipple-cunts)]!"
								+ "</p>";
				break;
		}
		
		// Parse TF before changing nipple type:
		transformation = UtilText.parse(owner, transformation);
		
		this.nippleShape = nippleShape;
		
		return transformation;
	}
	
	public AreolaeShape getAreolaeShape() {
		return areolaeShape;
	}
	
	public String setAreolaeShape(GameCharacter owner, AreolaeShape areolaeShape) {
		if(!isCharacterInitialised(owner)) {
			this.areolaeShape = areolaeShape;
			return "";
		}
		
		if(this.areolaeShape == areolaeShape) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]乳晕的形状并无改变……)]</p>");
		}

		this.areolaeShape = areolaeShape;
		
		String transformation = "";
		switch(areolaeShape) {
			case NORMAL:
				transformation = "<p>[npc.NamePos] [npc.nipples] suddenly start to tingle, and [npc.she] [npc.verb(gasp)] as [npc.she] [npc.verb(feel)] [npc.her] areolae shift and transform into regular-looking circles.<br/>"
							+ "[npc.NamePos] areolae are now shaped like [style.boldSex(normal circles)]!";
				break;
			case HEART:
				transformation = "<p>[npc.NamePos] [npc.nipples] suddenly start to tingle, and [npc.she] [npc.verb(gasp)] as [npc.she] [npc.verb(feel)] [npc.her] areolae shift and transform into the shape of hearts.<br/>"
							+ "[npc.NamePos] areolae are now shaped like [style.boldSex(hearts)]!";
				break;
			case STAR:
				transformation = "<p>[npc.NamePos] [npc.nipples] suddenly start to tingle, and [npc.she] [npc.verb(gasp)] as [npc.she] [npc.verb(feel)] [npc.her] areolae shift and transform into the shape of stars.<br/>"
							+ "[npc.NamePos] areolae are now shaped like [style.boldSex(stars)]!";
				break;
		}
		
		return UtilText.parse(owner, transformation);
	}

	public OrificeNipples getOrificeNipples() {
		return orificeNipples;
	}

	public AreolaeSize getAreolaeSize() {
		return AreolaeSize.getAreolaeSizeFromInt(areolaeSize);
	}
	
	public int getAreolaeSizeValue() {
		return areolaeSize;
	}

	public String setAreolaeSize(GameCharacter owner, int areolaeSize) {
		int boundAreolaeSize = Math.max(0, Math.min(areolaeSize, AreolaeSize.FOUR_MASSIVE.getValue()));
		if(!isCharacterInitialised(owner)) {
			this.areolaeSize = boundAreolaeSize;
			return "";
		}
		if (this.areolaeSize == boundAreolaeSize) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]乳晕的大小并无改变……)]</p>");
		}
		
		String transformation = "";
		
		if (this.areolaeSize > boundAreolaeSize) {
			transformation = UtilText.parse(owner,
					"<p>[npc.Name]的乳晕突然[style.boldShrink(缩小)]了，[npc.her]还感觉到[npc.nipples]周围突然产生一种奇怪的刺痛感，这令[npc.her]发出一声震惊的喘息声。<br/>");
			
		} else {
			transformation = UtilText.parse(owner,
					"<p>[npc.Name]的乳晕突然[style.boldGrow(变大)]了，[npc.her]还感觉到[npc.nipples]周围突然产生一种奇怪的刺痛感，这令[npc.her]发出一声震惊的喘息声。<br/>");
		}
		
		this.areolaeSize = boundAreolaeSize;

		return transformation + UtilText.parse(owner, "[npc.Name]现在有了[style.boldSex([npc.areolaeSize]的乳晕)]！</p>");
	}

	public boolean isPierced() {
		return pierced;
	}
	
	public String setPierced(GameCharacter owner, boolean pierced) {
		if(this.pierced == pierced) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		this.pierced = pierced;
		
		if(pierced) {
			return UtilText.parse(owner, "<p>[npc.NamePos]的[npc.nipples]现在已[style.boldGrow(穿孔)]！</p>");
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_NIPPLE);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.NamePos]的[npc.nipples]已[style.boldShrink(不再有穿孔了)]！"
					+ "</p>"
					+piercingUnequip);
		}
		
	}

	public boolean isCrotchNipples() {
		return crotchNipples;
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(GameCharacter gc) {
		if(this.isCrotchNipples()) {
			return BodyCoveringType.NIPPLES_CROTCH;
		}
		return BodyCoveringType.NIPPLES;
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(Body body) {
		if(this.isCrotchNipples()) {
			return BodyCoveringType.NIPPLES_CROTCH;
		}
		return BodyCoveringType.NIPPLES;
	}
	
	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		if(this.isCrotchNipples()) {
			return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(BreastCrotch.class) && getType().getRace().isFeralPartsAvailable());
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Breast.class) && getType().getRace().isFeralPartsAvailable());
	}
}