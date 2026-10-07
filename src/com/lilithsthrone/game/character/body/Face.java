package com.lilithsthrone.game.character.body;

import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Face implements BodyPartInterface {
	
	protected AbstractFaceType type;
	public boolean piercedNose;
	public BodyHair facialHair;
	
	public Mouth mouth;
	public Tongue tongue;

	public Face(AbstractFaceType type, int lipSize) {
		this.type = type;
		piercedNose = false;
		facialHair = BodyHair.ZERO_NONE;
		
		mouth = new Mouth(type.getMouthType(),
				lipSize,
				Wetness.THREE_WET.getValue(),
				Capacity.THREE_SLIGHTLY_LOOSE.getMedianValue(),
				OrificeDepth.TWO_AVERAGE.getValue(),
				OrificeElasticity.FOUR_LIMBER.getValue(),
				OrificePlasticity.THREE_RESILIENT.getValue(),
				true);
		
		tongue = new Tongue(type.getMouthType().getTongueType());
	}

	public Face(Face faceToCopy) {
		this.type = faceToCopy.type;
		this.piercedNose = faceToCopy.piercedNose;
		this.facialHair = faceToCopy.facialHair;
		
		this.mouth = new Mouth(faceToCopy.mouth);
		
		this.tongue = new Tongue(faceToCopy.tongue);
	}
	
	public Mouth getMouth() {
		return mouth;
	}

	public Tongue getTongue() {
		return tongue;
	}

	@Override
	public AbstractFaceType getType() {
		return type;
	}

	public List<BodyPartTag> getTypeTags() {
		return type.getTags();
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
	
	public String getDescriptor(GameCharacter owner) {
		return type.getDescriptor(owner);
	}
	
	public String getNoseNameSingular(GameCharacter gc) {
		return type.getNoseNameSingular(gc);
	}
	
	public String getNoseNamePlural(GameCharacter gc) {
		return type.getNoseNamePlural(gc);
	}

	public String getNoseDescriptor(GameCharacter gc) {
		return type.getNoseDescriptor(gc);
	}
	
	public String setType(GameCharacter owner, AbstractFaceType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			mouth.setType(type.getMouthType());
			tongue.setType(type.getMouthType().getTongueType());
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有[npc.a_faceRace]的[npc.face]了，所以无事发生……)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();

		sb.append("<p>");
		sb.append(
				 "一种强烈的刺痛感突然掠过[npc.namePos]的[npc.face]，在意识到转化的发生时，[npc.she]瞪大了双眼。"
					+ "随着一声清脆的响声，[npc.her]的面部骨骼开始重组，虽然这种感觉并不痛苦，但足以让[npc.herHim]不由自主地惊叫了一声");
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;

		mouth.setType(type.getMouthType());
		tongue.setType(type.getMouthType().getTongueType());
		
		sb.append(type.getTransformationDescription(owner));
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public boolean isPiercedNose() {
		return piercedNose;
	}

	public String setPiercedNose(GameCharacter owner, boolean piercedNose) {
		if(owner.isPiercedNose() == piercedNose) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		this.piercedNose = piercedNose;
		
		if(piercedNose) {
			return UtilText.parse(owner,
					"<p>[npc.NamePos]的[npc.nose]现在已[style.boldGrow(穿孔)]！</p>");
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_NOSE);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			return UtilText.parse(owner,
					"<p>"
							+ "[npc.NamePos]的[npc.nose][style.boldShrink(不再有穿孔了)]！"
					+ "</p>"
					+piercingUnequip);
		}
	}
	
	public BodyHair getFacialHair() {
		return facialHair;
	}

	public Covering getFacialHairType(GameCharacter owner) {
		return owner.getCovering(owner.getBodyHairCoveringType(owner.getFaceType().getRace()));
	}

	public String setFacialHair(GameCharacter owner, BodyHair facialHair) {
		if(!isCharacterInitialised(owner)) {
			this.facialHair = facialHair;
			return "";
		}

		if(!this.getType().isFacialHairAllowed()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]的脸型使[npc.herHim]无法长出胡须，所以无事发生……)]</p>");
		}
		
		if(owner.getFemininityValue()>=Femininity.ANDROGYNOUS.getMinimumFemininity() && facialHair!=BodyHair.ZERO_NONE && !Main.getProperties().hasValue(PropertyValue.feminineBeardsContent)) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(你太过女性化，无法长出胡子……)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.sheIs]太过女性化，无法长出胡子……)]</p>");
			}
		}
		
		if(getFacialHair() == facialHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		StringBuilder sb = new StringBuilder();
		
		switch(facialHair) {
			case ZERO_NONE:
				sb.append(UtilText.parse(owner, "<p>[npc.her]的面部上不再有"+getFacialHairType(owner).getFullDescription(owner, true)+"的痕迹。</p>"));
				break;
			case ONE_STUBBLE:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的脸上现在有了短而粗的"+getFacialHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
			case TWO_MANICURED:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的脸上现在有了少量的"+getFacialHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
			case THREE_TRIMMED:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的脸上现在有了修剪整齐的"+getFacialHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
			case FOUR_NATURAL:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的脸上现在有着胡须，外观似"+getFacialHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
			case FIVE_UNKEMPT:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的脸上现在有着蓬乱浓密的胡须，外观似"+getFacialHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
			case SIX_BUSHY:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的脸上现在有着大而浓密的胡须，外观似"+getFacialHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
			case SEVEN_WILD:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的脸上现在有着浓密且毫无修饰的大胡子，外观似"+getFacialHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
		}
		
		this.facialHair = facialHair;
		
		return sb.toString();
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Face.class) && getType().getRace().isFeralPartsAvailable());
	}

	public boolean isBaldnessNatural() {
		return this.getTypeTags().contains(BodyPartTag.FACE_NATURAL_BALDNESS_AVIAN)
				|| this.getTypeTags().contains(BodyPartTag.FACE_NATURAL_BALDNESS_FURRY)
				|| this.getTypeTags().contains(BodyPartTag.FACE_NATURAL_BALDNESS_SCALY)
				|| this.getTypeTags().contains(BodyPartTag.FACE_NATURAL_BALDNESS_SKIN);
	}
	
	public boolean isMuzzle() {
		return this.getTypeTags().contains(BodyPartTag.FACE_MUZZLE);
	}
	
	public boolean isHasFangs() {
		return this.getTypeTags().contains(BodyPartTag.FACE_FANGS);
	}
	
	public boolean isBeak() {
		return this.getTypeTags().contains(BodyPartTag.FACE_BEAK);
	}
	
	public boolean isSharkTeeth() {
		return this.getTypeTags().contains(BodyPartTag.FACE_SHARK_TEETH);
	}
}
