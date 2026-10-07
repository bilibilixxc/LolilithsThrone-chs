package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Eye implements BodyPartInterface {

	public static final int MAXIMUM_PAIRS = 4;
	
	protected AbstractEyeType type;
	public int eyePairs;
	public EyeShape irisShape;
	public EyeShape pupilShape;
	
	public Eye(AbstractEyeType type) {
		this.type = type;
		eyePairs = type.getDefaultPairCount();
		irisShape = type.getDefaultIrisShape();
		pupilShape = type.getDefaultPupilShape();
	}

	public Eye(Eye eyeToCopy) {
		this.type = eyeToCopy.type;
		this.eyePairs = eyeToCopy.eyePairs;
		this.irisShape = eyeToCopy.irisShape;
		this.pupilShape = eyeToCopy.pupilShape;
	}
	
	@Override
	public AbstractEyeType getType() {
		return type;
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
	public String getDescriptor(GameCharacter gc) {
		return type.getDescriptor(gc);
	}
	
	public String setType(GameCharacter owner, AbstractEyeType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			irisShape = type.getDefaultIrisShape();
			pupilShape = type.getDefaultPupilShape();
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.EYES);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有[npc.a_eyeRace]的[npc.eyes]了，所以无事发生……)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();

		sb.append("<p>");
		if(owner.isArmMovementHindered()) {
			sb.append("[npc.NamePos]的[npc.eyes]突然变得又热又痒，使[npc.herHim]本能地闭紧双眼。");
		} else {
			sb.append("[npc.NamePos]的[npc.eyes]突然变得又热又痒，使[npc.she]本能地闭紧双眼，伸手去揉。");
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		
		this.type = type;
		irisShape = type.getDefaultIrisShape();
		pupilShape = type.getDefaultPupilShape();
		owner.resetAreaKnownByCharacters(CoverableArea.EYES);

		sb.append(type.getTransformationDescription(owner));
		
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getEyePairs() {
		return eyePairs;
	}

	public String setEyePairs(GameCharacter owner, int eyePairs) {
		eyePairs = Math.max(1, Math.min(eyePairs, MAXIMUM_PAIRS));
		
		if(owner.getEyePairs() == eyePairs) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		boolean removingExtraEyes = owner.getEyePairs() > eyePairs;
		this.eyePairs = eyePairs;
		
		if(removingExtraEyes) {
			return UtilText.parse(owner,
					"<p>"
						+ "刺痛感遍布[npc.namePos]的[npc.face]，随后转移集中在[npc.her]的[npc.eyes]上。"
						+ "[npc.She]猛地合眼，下意识喊叫出声，同时感觉到有些[style.boldShrink(消失)]进了[npc.her]主眼下的[npc.faceSkin]皮肤里。<br/>"
						+ "片刻过后，[npc.sheIs]的脸上得到了[style.boldTfGeneric([npc.a_eyes])]。"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "刺痛感遍布[npc.namePos]的[npc.face]，随后转移集中在[npc.her]的[npc.eyes]上。"
						+ "[npc.She]猛地合眼，下意识喊叫出声，一股令人不安的感觉蔓延在"
							+ "[npc.her]主眼下的[npc.faceSkin]处，也就是新[npc.eyes][style.boldGrow(长出)]的地方。<br/>"
						+ "片刻过后，[npc.sheIs]的脸上得到了[style.boldTfGeneric([npc.a_eyes])]。"
					+ "</p>");
		}
	}

	public EyeShape getIrisShape() {
		return irisShape;
	}

	public String setIrisShape(GameCharacter owner, EyeShape irisShape) {
		if(!isCharacterInitialised(owner)) {
			this.irisShape = irisShape;
			return "";
		}
		if(owner.getIrisShape() == irisShape) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}

		this.irisShape = irisShape;
		
		return UtilText.parse(owner,
				"<p>"
					+ "[npc.namePos]的[npc.eyes]周围突然瘙痒难耐，但感觉很快就过去了，[npc.she]甚至来不及伸手揉揉。<br/>"
					+ "[npc.Name]现在已有[style.boldTfGeneric([npc.irisShape]的瞳孔)]！"
				+ "</p>");
	}

	public EyeShape getPupilShape() {
		return pupilShape;
	}

	public String setPupilShape(GameCharacter owner, EyeShape pupilShape) {
		if(!isCharacterInitialised(owner)) {
			this.pupilShape = pupilShape;
			return "";
		}
		if(owner.getPupilShape() == pupilShape) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}

		this.pupilShape = pupilShape;
		
		return UtilText.parse(owner,
				"<p>"
					+ "[npc.namePos]的[npc.eyes]周围突然瘙痒难耐，但感觉很快就过去了，[npc.she]甚至来不及伸手揉揉。<br/>"
					+ "[npc.Name]现在已有[style.boldTfGeneric([npc.pupilShape]的瞳孔)]！"
				+ "</p>");
	}
	
	public String setEyeCovering(GameCharacter owner, Covering covering) {
		if(owner.getCovering(owner.getEyeCovering()).equals(covering)
				|| owner.getCovering(BodyCoveringType.EYE_PUPILS).equals(covering)
				|| owner.getCovering(BodyCoveringType.EYE_SCLERA).equals(covering)) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		StringBuilder sb = new StringBuilder();
		
		owner.getBody().getCoverings().put(covering.getType(), covering);
		
		sb.append(
				"<p>"
					+ "[npc.NamePos]的视线瞬间模糊，眨了好几下眼后，[npc.her]的[npc.eyes]突然发现转变，连颜色都改变了。<br/>"
					+ "[npc.She]现已拥有");
		
		if(covering.getPattern() == CoveringPattern.EYE_IRISES_HETEROCHROMATIC) {
			sb.append("异色的[npc.irisPrimaryColour(true)]和[npc.irisSecondaryColour(true)][npc.irisShape]的虹膜，");
		} else {
			sb.append("[npc.irisPrimaryColour(true)][npc.irisShape]的虹膜");
		}
		if(covering.getPattern() == CoveringPattern.EYE_PUPILS_HETEROCHROMATIC) {
			sb.append("有着[npc.pupilPrimaryColour(true)]和[npc.pupilSecondaryColour(true)][npc.pupilShape]异色瞳孔");
		} else {
			sb.append("有着[npc.pupilPrimaryColour(true)][npc.pupilShape]的瞳孔");
		}
		if(covering.getPattern() == CoveringPattern.EYE_SCLERA_HETEROCHROMATIC) {
			sb.append("和异色[npc.scleraPrimaryColour(true)]和[npc.scleraSecondaryColour(true)]巩膜。</p>");
		} else {
			sb.append("以及[npc.scleraPrimaryColour(true)]巩膜。</p>");
		}
		
		return UtilText.parse(owner, sb.toString());
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Eye.class) && getType().getRace().isFeralPartsAvailable());
	}
}
