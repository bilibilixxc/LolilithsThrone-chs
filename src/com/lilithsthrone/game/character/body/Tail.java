package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Tail implements BodyPartInterface {

	public static final int MAXIMUM_COUNT = 9;
	public static final float LENGTH_PERCENTAGE_MIN = 0.05f;
	public static final float LENGTH_PERCENTAGE_MAX = 2.5f;
	
	protected AbstractTailType type;
	public int tailCount;
	public int girth;
	public float lengthAsPercentageOfHeight;

	public Tail(AbstractTailType type) {
		this.type = type;
		this.tailCount = 1;
		this.girth = type.getDefaultGirth();
		this.lengthAsPercentageOfHeight = type.getDefaultLengthAsPercentageOfHeight();
	}

	public Tail(Tail tailToCopy) {
		this.type = tailToCopy.type;
		this.tailCount = tailToCopy.tailCount;
		this.girth = tailToCopy.girth;
		this.lengthAsPercentageOfHeight = tailToCopy.lengthAsPercentageOfHeight;
	}
	
	@Override
	public AbstractTailType getType() {
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
		
		if(gc.getTailGirth()!=PenetrationGirth.THREE_AVERAGE) {
			descriptors.add(gc.getTailGirth().getName());
		}
		
		return Util.randomItemFrom(descriptors);
	}

	public String setType(GameCharacter owner, AbstractTailType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			if(owner!=null && owner.getBody()!=null && !owner.getLegConfiguration().isAbleToGrowTail()) {
				type = TailType.NONE;
			}
			if(this.getLengthAsPercentageOfHeight()==this.getType().getDefaultLengthAsPercentageOfHeight()) {
				this.setLengthAsPercentageOfHeight(owner, type.getDefaultLengthAsPercentageOfHeight());
			}
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}

		StringBuilder sb = new StringBuilder();
		
		if(!owner.getLegConfiguration().isAbleToGrowTail() && type!=TailType.NONE) {
			type = TailType.NONE;
			sb.append(UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(由于[npc.name]拥有“"+owner.getLegConfiguration().getName()+"”腿部配置，所以无法长尾巴！)]</p>"));
		}
		
		if(type == getType()) {
			if(type == TailType.NONE) {
				sb.append(UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经没有尾巴了，所以无事发生……)]</p>"));
				
			} else {
				sb.append(UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有[npc.a_tailRace]的[npc.tail]了，所以无事发生……)]</p>"));
			}
			return UtilText.parse(owner, sb.toString());
		}
		
		if(this.type == TailType.NONE) {
			sb.append(
					"<p>"
						+ "[npc.Name]感到腰背间变得滚烫又敏感，连忙摸了摸，这才发现有什么东西正从[npc.skin]之下生长出来。");
		} else {
			sb.append(
					"<p>"
						+ (owner.getTailCount()==1
							?"[npc.Name]感觉[npc.tail]渐渐燥热发痒起来，过了一会儿就开始了转化。"
							:"[npc.Name]感觉[npc.tails]渐渐燥热发痒起来，过了一会儿就开始了转化。"));
		}

		if(this.getLengthAsPercentageOfHeight()==this.getType().getDefaultLengthAsPercentageOfHeight()) {
			this.setLengthAsPercentageOfHeight(owner, type.getDefaultLengthAsPercentageOfHeight());
		}
		if(this.getRawGirthValue()==this.getType().getDefaultGirth()) {
			this.setTailGirth(owner, type.getDefaultGirth());
		}
		
		// If NONE, apply type change after. All else, before:
		if(type == TailType.NONE) {
			sb.append(type.getTransformationDescription(owner));
			this.type = type;
			
		} else {
			this.type = type;
//			System.out.println("1 "+type.getTransformName() + " | "+CharacterUtils.isGeneratingOwnerlessBody());
//			System.out.println("2 "+owner.getTailCovering().getName(owner));
			sb.append(type.getTransformationDescription(owner));
		}
		
		sb.append("</p>");
		
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getTailCount() {
		return tailCount;
	}

	public int getMaxTailCount(GameCharacter owner) {
		if(owner.hasPerkAnywhereInTree(Perk.SINGLE_TAILED_YOUKO)) {
			return 1;
		}
		if(owner.hasPerkAnywhereInTree(Perk.TWO_TAILED_YOUKO)) {
			return 2;
		}
		if(owner.hasPerkAnywhereInTree(Perk.THREE_TAILED_YOUKO)) {
			return 3;
		}
		if(owner.hasPerkAnywhereInTree(Perk.FOUR_TAILED_YOUKO)) {
			return 4;
		}
		if(owner.hasPerkAnywhereInTree(Perk.FIVE_TAILED_YOUKO)) {
			return 5;
		}
		if(owner.hasPerkAnywhereInTree(Perk.SIX_TAILED_YOUKO)) {
			return 6;
		}
		if(owner.hasPerkAnywhereInTree(Perk.SEVEN_TAILED_YOUKO)) {
			return 7;
		}
		if(owner.hasPerkAnywhereInTree(Perk.EIGHT_TAILED_YOUKO)) {
			return 8;
		}
		if(owner.hasPerkAnywhereInTree(Perk.NINE_TAILED_YOUKO)) {
			return 9;
		}
		return tailCount;
	}

	public String setTailCount(GameCharacter owner, int tailCount, boolean overrideYoukoLimitations) {
		tailCount = Math.max(1, Math.min(tailCount, MAXIMUM_COUNT));
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.tailCount = tailCount;
			return "";
		}
		
		if(owner.getTailCount() == tailCount && !overrideYoukoLimitations) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		if(owner.getTailType().equals(TailType.FOX_MORPH_MAGIC)
				&& tailCount > getMaxTailCount(owner)
				&& !overrideYoukoLimitations) {
				return "<p style='text-align:center;'>"
							+ "[style.colourMinorBad([npc.NamePos]注入奥术的"
							+ (this.tailCount == 1
								? "尾巴吸收并且无效化了"
								: "尾巴吸收并且无效化了")
							+ "转化效果，尾巴数量没能发生变化！)]"
						+ "</p>";
		}

		owner.removeStatusEffect(StatusEffect.SUBSPECIES_BONUS);
		
		boolean removingTails = owner.getTailCount() > tailCount;
		this.tailCount = tailCount;

		owner.addStatusEffect(StatusEffect.SUBSPECIES_BONUS, -1);

		if(overrideYoukoLimitations) {
			if(owner.hasPerkAnywhereInTree(Perk.SINGLE_TAILED_YOUKO)) {
				owner.removeSpecialPerk(Perk.SINGLE_TAILED_YOUKO);
			}
			if(owner.hasPerkAnywhereInTree(Perk.TWO_TAILED_YOUKO)) {
				owner.removeSpecialPerk(Perk.TWO_TAILED_YOUKO);
			}
			if(owner.hasPerkAnywhereInTree(Perk.THREE_TAILED_YOUKO)) {
				owner.removeSpecialPerk(Perk.THREE_TAILED_YOUKO);
			}
			if(owner.hasPerkAnywhereInTree(Perk.FOUR_TAILED_YOUKO)) {
				owner.removeSpecialPerk(Perk.FOUR_TAILED_YOUKO);
			}
			if(owner.hasPerkAnywhereInTree(Perk.FIVE_TAILED_YOUKO)) {
				owner.removeSpecialPerk(Perk.FIVE_TAILED_YOUKO);
			}
			if(owner.hasPerkAnywhereInTree(Perk.SIX_TAILED_YOUKO)) {
				owner.removeSpecialPerk(Perk.SIX_TAILED_YOUKO);
			}
			if(owner.hasPerkAnywhereInTree(Perk.SEVEN_TAILED_YOUKO)) {
				owner.removeSpecialPerk(Perk.SEVEN_TAILED_YOUKO);
			}
			if(owner.hasPerkAnywhereInTree(Perk.EIGHT_TAILED_YOUKO)) {
				owner.removeSpecialPerk(Perk.EIGHT_TAILED_YOUKO);
			}
			if(owner.hasPerkAnywhereInTree(Perk.NINE_TAILED_YOUKO)) {
				owner.removeSpecialPerk(Perk.NINE_TAILED_YOUKO);
			}
			if(owner.getTailType().equals(TailType.FOX_MORPH_MAGIC)) {
				switch (owner.getTailCount()) {
					case 1:
						owner.addSpecialPerk(Perk.SINGLE_TAILED_YOUKO);
						break;
					case 2:
						owner.addSpecialPerk(Perk.TWO_TAILED_YOUKO);
						break;
					case 3:
						owner.addSpecialPerk(Perk.THREE_TAILED_YOUKO);
						break;
					case 4:
						owner.addSpecialPerk(Perk.FOUR_TAILED_YOUKO);
						break;
					case 5:
						owner.addSpecialPerk(Perk.FIVE_TAILED_YOUKO);
						break;
					case 6:
						owner.addSpecialPerk(Perk.SIX_TAILED_YOUKO);
						break;
					case 7:
						owner.addSpecialPerk(Perk.SEVEN_TAILED_YOUKO);
						break;
					case 8:
						owner.addSpecialPerk(Perk.EIGHT_TAILED_YOUKO);
						break;
					case 9:
						owner.addSpecialPerk(Perk.NINE_TAILED_YOUKO);
						break;
				}
			}
		}

		if (owner.getTailType() == TailType.NONE) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		if(removingTails) {
			return UtilText.parse(owner,
					"<p>"
						+ "一股刺痛感传遍了[npc.namePos]的[npc.tails]，接着这种感觉继续向上，集中在了[npc.her]的腰背间。"
						+ "[npc.She]不禁大叫一声，只觉得好像有一条[style.boldShrink(缩了下去)]，消失在[npc.her]的[npc.skin]之下。<br/>"
						+ "片刻过后，[npc.sheIs]便有了[style.boldTfGeneric("
								+(tailCount==1
									?"一条[npc.tail]"
									:Util.intToString(tailCount)+"条[npc.tails]")
						+ ")]。"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "一股刺痛感传遍了[npc.namePos]的[npc.tails]，接着这种感觉继续向上，集中在了[npc.her]的腰背间。"
						+ "[npc.She]不禁轻呼一声，只觉得好像有新的[npc.tails][style.boldShrink(正在生长)]，突破[npc.skin]而出。<br/>"
						+ "片刻过后，[npc.sheIs]便有了[style.boldTfGeneric("
								+(tailCount==1
									?"一条[npc.tail]"
									:Util.intToString(tailCount)+"条[npc.tails]")
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
	public String setTailGirth(GameCharacter owner, int girth) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner) || owner.getBody()==null) {
			this.girth = Math.max(0, Math.min(girth, PenetrationGirth.getMaximum()));
			return "";
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

		if(!owner.hasTail()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(由于[npc.name]没有尾巴，所以无事发生……)]</p>");
		}
		
		if(girthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.tail]的周长没有变化……)]</p>");
		}
		
		return owner.getTailType().getGirthTransformationDescription(owner, girthChange > 0);
	}

	// Length:
	
	public float getLengthAsPercentageOfHeight() {
		return lengthAsPercentageOfHeight;
	}

	/**
	 * Sets the tails' length as a percentage of the owner's height. Value is bound to >=0.05f && <=2.5f
	 */
	public String setLengthAsPercentageOfHeight(GameCharacter owner, float lengthAsPercentageOfHeight) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner) || owner.getBody()==null) {
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
		
		if(!owner.hasTail()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(由于[npc.name]没有尾巴，所以无事发生……)]</p>");
		}
		
		if(lengthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.tail]的长度没有变化……)]</p>");
		}
		
		return owner.getTailType().getLengthTransformationDescription(owner, lengthChange > 0);
	}
	
	public int getLength(GameCharacter owner) {
		return (int) (owner.getHeightValue() * getLengthAsPercentageOfHeight());
	}

	/**
	 * Takes into account whether player has 'Allow furry tail penetrations' turned on or off.
	 */
	public boolean isSuitableForPenetration() {
		if(this.getType().getTags().contains(BodyPartTag.TAIL_NEVER_SUITABLE_FOR_PENETRATION)) {
			return false;
		}
		if(this.getType().getTags().contains(BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION) || Main.game.isFurryTailPenetrationContentEnabled()) {
			return this.getType().isPrehensile() || this.getLengthAsPercentageOfHeight()>=0.5f;
		}
		return false;
	}
	
	// Diameter:

	public static float getGenericDiameter(int height, PenetrationGirth girth) {
		return Units.round((height * 0.08f) * (1f + girth.getDiameterPercentageModifier()), 2);
	}
	
	public float getBaseDiameter(GameCharacter owner) {
		return getDiameter(owner, 0);
	}

	/**
	 * Uses basic equations to calculate a rough diameter dropoff, based on this tail type's TAPERING tag.
	 * 
	 * @param owner The character who this tail is attached to.
	 * @param atLength The length at which the diameter is to be found, measured from the base.
	 * @return The diameter.
	 */
	public float getDiameter(GameCharacter owner, float atLength) {
		float baseDiameter = (owner.getHeightValue() * 0.08f) * (1f + this.getGirth().getDiameterPercentageModifier()); // Default linear tapering
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
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Tail.class) && getType().getRace().isFeralPartsAvailable());
	}
}
