package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Tentacle implements BodyPartInterface {

	public static final int MAXIMUM_COUNT = 100;
	public static final float LENGTH_PERCENTAGE_MIN = 1f;
	public static final float LENGTH_PERCENTAGE_MAX = 5f;
	
	protected AbstractTentacleType type;
	public int tentacleCount;
	public int girth;
	public float lengthAsPercentageOfHeight;

	public Tentacle(AbstractTentacleType type) {
		this.type = type;
		this.tentacleCount = 1;
		this.girth = type.getDefaultGirth();
		this.lengthAsPercentageOfHeight = type.getDefaultLengthAsPercentageOfHeight();
	}

	public Tentacle(Tentacle tentacleToCopy) {
		this.type = tentacleToCopy.type;
		this.tentacleCount = tentacleToCopy.tentacleCount;
		this.girth = tentacleToCopy.girth;
		this.lengthAsPercentageOfHeight = tentacleToCopy.lengthAsPercentageOfHeight;
	}
	
	@Override
	public AbstractTentacleType getType() {
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
		List<String> descriptors = new ArrayList<>();
		
		descriptors.add(type.getDescriptor(gc));
		
		if(gc.getTentacleGirth()!=PenetrationGirth.THREE_AVERAGE) {
			descriptors.add(gc.getTentacleGirth().getName());
		}
		
		return Util.randomItemFrom(descriptors);
	}
	
	/**
	 * @return A description as though the tentacle type is growing from the character's lower back.
	 */
	public String setType(GameCharacter owner, AbstractTentacleType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			if(this.getLengthAsPercentageOfHeight()==this.getType().getDefaultLengthAsPercentageOfHeight()) {
				this.setLengthAsPercentageOfHeight(owner, type.getDefaultLengthAsPercentageOfHeight());
			}
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type == TentacleType.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经没有触手了，所以无事发生……)]</p>");
				
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有[npc.a_tentacleRace]的[npc.tentacle]了，所以无事发生……)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(this.type == TentacleType.NONE) {
			sb.append(
					"<p>"
						+ "[npc.Name]感到腰背间变得滚烫又敏感，连忙摸了摸，这才发现有什么东西正从[npc.skin]之下生长出来。");
		} else {
			sb.append(
					"<p>"
						+ (owner.getTentacleCount()==1
							?"[npc.Name]感觉[npc.tentacle]渐渐燥热发痒起来，过了一会儿就开始了转化。"
							:"[npc.Name]感觉[npc.tentacles]渐渐燥热发痒起来，过了一会儿就开始了转化。"));
		}

		if(this.getLengthAsPercentageOfHeight()==this.getType().getDefaultLengthAsPercentageOfHeight()) {
			this.setLengthAsPercentageOfHeight(owner, type.getDefaultLengthAsPercentageOfHeight());
		}
		if(this.getRawGirthValue()==this.getType().getDefaultGirth()) {
			this.setTentacleGirth(owner, type.getDefaultGirth());
		}
		
		// If NONE, apply type change after. All else, before:
		if(type == TentacleType.NONE) {
			sb.append(type.getTransformationDescription(owner));
			this.type = type;
			
		} else {
			this.type = type;
			sb.append(type.getTransformationDescription(owner));
		}
		
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getTentacleCount() {
		return tentacleCount;
	}

	public String setTentacleCount(GameCharacter owner, int tentacleCount) {
		tentacleCount = Math.max(1, Math.min(tentacleCount, MAXIMUM_COUNT));
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.tentacleCount = tentacleCount;
			return "";
		}
		
		if(owner.getTentacleCount() == tentacleCount) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		owner.removeStatusEffect(StatusEffect.SUBSPECIES_BONUS);
		
		boolean removingTentacles = owner.getTentacleCount() > tentacleCount;
		this.tentacleCount = tentacleCount;

		owner.addStatusEffect(StatusEffect.SUBSPECIES_BONUS, -1);
		
		if (owner.getTentacleType() == TentacleType.NONE) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		if(removingTentacles) {
			return UtilText.parse(owner,
					"<p>"
						+ "一股刺痛感传遍了[npc.namePos]的[npc.tentacles]，接着这种感觉继续向上，集中在了[npc.her]的腰背间。"
						+ "[npc.She]不禁大叫一声，只觉得好像有一条[style.boldShrink(缩了下去)]，消失在[npc.her]的[npc.skin]之下。<br/>"
						+ "片刻过后，[npc.sheIs]便有了[style.boldTfGeneric("
								+(tentacleCount==1
									?"一条[npc.tentacle]"
									:Util.intToString(tentacleCount)+"条[npc.tentacles]")
						+ ")]。"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "一股刺痛感传遍了[npc.namePos]的[npc.tentacles]，接着这种感觉继续向上，集中在了[npc.her]的腰背间。"
						+ "[npc.She]不禁轻呼一声，只觉得好像有新的[npc.tentacles][style.boldShrink(正在生长)]，突破[npc.skin]而出。<br/>"
						+ "片刻过后，[npc.sheIs]便有了[style.boldTfGeneric("
								+(tentacleCount==1
									?"一条[npc.tentacle]"
									:Util.intToString(tentacleCount)+"条[npc.tentacles]")
						+ ")]。"
					+ "</p>");
		}
	}

	// Girth:

	public PenetrationGirth getGirth() {
		return PenetrationGirth.getGirthFromInt(girth);
	}

	public int getRawGirthValue() {
		return girth;
	}

	/**
	 * Sets the girth. Value is bound to >=0 && <=PenisGirth.FOUR_FAT.getValue()
	 */
	public String setTentacleGirth(GameCharacter owner, int girth) {
		if(!isCharacterInitialised(owner)) {
			this.girth = Math.max(0, Math.min(girth, PenetrationGirth.getMaximum()));
			return "";
		}
		
		if(!owner.hasTentacle()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		int girthChange = 0;
		
		if (girth <= 0) {
			if (this.girth != 0) {
				girthChange = 0 - this.girth;
				this.girth = 0;
			}
		} else if (girth >= PenetrationGirth.getMaximum()) {
			if (this.girth != PenetrationGirth.getMaximum()) {
				girthChange = PenetrationGirth.getMaximum() - this.girth;
				this.girth = PenetrationGirth.getMaximum();
			}
		} else {
			if (this.girth != girth) {
				girthChange = girth - this.girth;
				this.girth = girth;
			}
		}
		
		if(girthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.tentacle]的周长没有变化……)]</p>");
		}
		
		return owner.getTentacleType().getGirthTransformationDescription(owner, girthChange > 0);
	}

	// Length:

	public float getLengthAsPercentageOfHeight() {
		return lengthAsPercentageOfHeight;
	}

	/**
	 * Sets the tentacles' length as a percentage of the owner's height. Value is bound to >=1.0f && <=5.0f
	 */
	public String setLengthAsPercentageOfHeight(GameCharacter owner, float lengthAsPercentageOfHeight) {
		if(!isCharacterInitialised(owner)) {
			this.lengthAsPercentageOfHeight = Math.max(LENGTH_PERCENTAGE_MIN, Math.min(lengthAsPercentageOfHeight, LENGTH_PERCENTAGE_MAX));
			return "";
		}
		
		float lengthChange = 0;
		
		if (lengthAsPercentageOfHeight <= LENGTH_PERCENTAGE_MIN) {
			if (this.lengthAsPercentageOfHeight != LENGTH_PERCENTAGE_MIN) {
				lengthChange = LENGTH_PERCENTAGE_MIN - this.lengthAsPercentageOfHeight;
				this.lengthAsPercentageOfHeight = LENGTH_PERCENTAGE_MIN;
			}
			
		} else if (lengthAsPercentageOfHeight >= LENGTH_PERCENTAGE_MAX) {
			if (this.lengthAsPercentageOfHeight != LENGTH_PERCENTAGE_MAX) {
				lengthChange = LENGTH_PERCENTAGE_MAX - this.lengthAsPercentageOfHeight;
				this.lengthAsPercentageOfHeight = LENGTH_PERCENTAGE_MAX;
			}
			
		} else {
			if (this.lengthAsPercentageOfHeight != lengthAsPercentageOfHeight) {
				lengthChange = lengthAsPercentageOfHeight - this.lengthAsPercentageOfHeight;
				this.lengthAsPercentageOfHeight = lengthAsPercentageOfHeight;
			}
		}
		
		if(!owner.hasTentacle()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(由于[npc.name]没有触手，所以无事发生……)]</p>");
		}
		
		if(lengthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.tentacles]的长度没有变化……)]</p>");
		}
		
		return owner.getTentacleType().getLengthTransformationDescription(owner, lengthChange > 0);
	}
	
	public int getLength(GameCharacter owner) {
		return (int) (owner.getHeightValue() * getLengthAsPercentageOfHeight());
	}
	
	
	
	// Diameter:

	public static float getGenericDiameter(int height, PenetrationGirth girth) {
		return Units.round((height * 0.08f) * (1f + girth.getDiameterPercentageModifier()), 2);
	}
	
	public float getBaseDiameter(GameCharacter owner) {
		return getDiameter(owner, 0);
	}

	/**
	 * Uses basic equations to calculate a rough diameter dropoff, based on this tentacle type's TAPERING tag.
	 * 
	 * @param owner The character who this tentacle is attached to.
	 * @param atLength The length at which the diameter is to be found, measured from the base.
	 * @return The diameter.
	 */
	public float getDiameter(GameCharacter owner, float atLength) {
		float baseDiameter = (owner.getHeightValue() * 0.08f) * (1f + this.getGirth().getDiameterPercentageModifier());
		float lengthPercentage = Math.min(1, atLength / this.getLength(owner));
		
		if(this.getType().getTags().contains(BodyPartTag.TAIL_TAPERING_EXPONENTIAL)) { // Exponential diameter tapering:
			// y = 1/(4x+1)
			// At maximum length, diameter is 20% base length
			return (1 / (4*lengthPercentage + 1)) * baseDiameter;
			
		} else if(this.getType().getTags().contains(BodyPartTag.TAIL_TAPERING_LINEAR)) { // Linear diameter tapering:
			// y = 1 - (0.8x)
			// At maximum length, diameter is 20% base length
			return (1 - (0.8f * lengthPercentage)) * baseDiameter;
			
		}
		// No diameter tapering:
		return Units.round(baseDiameter, 2);
	}
	
	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Tentacle.class) && getType().getRace().isFeralPartsAvailable());
	}
}
