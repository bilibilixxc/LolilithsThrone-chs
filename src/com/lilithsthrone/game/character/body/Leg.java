package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Leg implements BodyPartInterface {

	public static final float LENGTH_PERCENTAGE_MIN_FERAL = 0.05f;
	public static final float LENGTH_PERCENTAGE_MIN = 2f;
	public static final float LENGTH_PERCENTAGE_MAX = 10f;
	
	protected AbstractLegType type;
	protected FootStructure footStructure;
	protected LegConfiguration legConfiguration;
	protected int girth;
	protected float lengthAsPercentageOfHeight;

	public Leg(AbstractLegType type, LegConfiguration legConfiguration) {
		this.type = type;
		this.legConfiguration = legConfiguration;
		this.footStructure = type.getDefaultFootStructure(legConfiguration);
		this.girth = PenetrationGirth.THREE_AVERAGE.getValue();
		this.lengthAsPercentageOfHeight = LegConfiguration.getDefaultSerpentTailLengthMultiplier();
	}

	public Leg(Leg legToCopy) {
		this.type = legToCopy.type;
		this.legConfiguration = legToCopy.legConfiguration;
		this.footStructure = legToCopy.footStructure;
		this.girth = legToCopy.girth;
		this.lengthAsPercentageOfHeight = legToCopy.lengthAsPercentageOfHeight;
	}
	
	@Override
	public AbstractLegType getType() {
		return type;
	}

	public FootStructure getFootStructure() {
		return footStructure;
	}

	//TODO check
	/**
	 * @return A description of the change. Returns an empty String if !isCharacterInitialised(owner) or if footStructure==FootStructure.TENTACLED
	 */
	public String setFootStructure(GameCharacter owner, FootStructure footStructure) {
		if(!isCharacterInitialised(owner) || footStructure==FootStructure.TENTACLED) {
			this.footStructure = footStructure;
			return "";
		}
		
		if(this.getFootStructure()==footStructure) {
			return UtilText.parse(owner,
					"<p>"
						+ "[style.colourDisabled(由于[npc.namePos]的[npc.feet]已经是[npc.footStructure]了，所以无事发生……)]"
					+ "</p>");
		}
		
		if(!type.getFootType().getPermittedFootStructures(owner.getLegConfiguration()).contains(footStructure)) {
			return UtilText.parse(owner,
					"<p>"
						+ "[style.colourDisabled(由于[npc.namePos]的[npc.feet]无法被转化为"+footStructure.getName()+"，所以无事发生……)]"
					+ "</p>");
		}

		
		this.footStructure = footStructure;
		
		StringBuilder sb = new StringBuilder();

		sb.append("<p>"
				+ "[npc.Name]忽然感到[npc.legs]和[npc.feet]处迸发出一阵强烈的热量，不禁震惊地倒吸一口凉气。"
				+ "随着脚部结构的变化，[npc.she]一个踉跄，差点失去了平衡。</br>");
		
		switch(footStructure) {
			case NONE:
				sb.append("片刻过后，[npc.sheIs]便[style.boldTfGeneric(没有[npc.feet])]了！");
				break;
			case DIGITIGRADE:
				sb.append("片刻过后，[npc.sheIs]得到了[style.boldTfGeneric(趾行的[npc.feet])]，这意味着[npc.she]现在用脚趾行走，脚跟总是高高抬起。");
				break;
			case PLANTIGRADE:
				sb.append("片刻过后，[npc.sheIs]得到了[style.boldTfGeneric(跖行的[npc.feet])]，这意味着[npc.she]现在用脚掌在地面上行走。");
				break;
			case UNGULIGRADE:
				sb.append("片刻过后，[npc.sheIs]得到了[style.boldTfGeneric(蹄行的[npc.feet])]，这意味着[npc.she]现在用[npc.toes]行走，而脚的其他部分则总是高高抬起。");
				break;
			case ARACHNOID:
				sb.append("片刻过后，[npc.sheIs]得到了[style.boldTfGeneric(蛛形的[npc.feet])]，这意味着[npc.she]现在用蛛形的节肢末端行走。");
				break;
			case TENTACLED:
				break;
		}
		
		return UtilText.parse(owner, sb.toString())
				+ "</p>"
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
		
	}

	public LegConfiguration getLegConfiguration() {
		return legConfiguration;
	}

	public void setLegConfigurationForced(AbstractLegType type, LegConfiguration legConfiguration) {
		this.type = type;
		this.footStructure = type.getDefaultFootStructure(legConfiguration);
		this.legConfiguration = legConfiguration;
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
		List<String> descriptorList = new ArrayList<>();
		
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(Util.randomItemFrom(gc.getBodyShape().getLimbDescriptors()));

		return Util.randomItemFrom(descriptorList);
	}

	public String setType(GameCharacter owner, AbstractLegType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			this.footStructure = type.getDefaultFootStructure(this.getLegConfiguration());
			if(owner!=null) {
				if(Main.game.isStarted() && !type.isLegConfigurationAvailable(this.getLegConfiguration())) {
					this.getType().applyLegConfigurationTransformation(owner, RacialBody.valueOfRace(type.getRace()).getLegConfiguration(), true, true);
				}
				owner.postTransformationCalculation();
				
			} else {
				if(Main.game.isStarted() && !type.isLegConfigurationAvailable(this.getLegConfiguration())) {
					System.err.println("Leg.setType() was passed a null owner, and the type '"+type.getTransformName()+"' requires conversion of LegConfiguration from "
							+ "'"+this.getLegConfiguration().getName()+"' to '"+RacialBody.valueOfRace(type.getRace()).getLegConfiguration().getName()+"'.");
					this.setLegConfigurationForced(type, RacialBody.valueOfRace(type.getRace()).getLegConfiguration());
				}
			}
			return "";
		}
		
		if(type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有[npc.a_legRace]的[npc.legs]了，所以无事发生……)]</p>");
		}
		
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(
				"<p>"
					+ "转化开始后，[npc.NamePos]的[npc.legs]晃动起来，变得越来越虚弱，[npc.she]差点没保持住平衡。");
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		this.footStructure = type.getDefaultFootStructure(this.getLegConfiguration());
		
		sb.append(type.getTransformationDescription(owner)+"</p>");
		
		// Set tentacle variables:
		owner.setTentacleType(type.getTentacleType());
		owner.setTentacleCount(type.getTentacleCount());
		
		if(!type.isLegsReplacedByTentacles() && type.getDefaultFootStructure(this.getLegConfiguration())!=FootStructure.NONE) {
			sb.append(
					"<p>"
						+ "转化让[npc.her][npc.feet]的结构变为了[style.boldTFGeneric("+this.footStructure.getName()+")]！"+this.footStructure.getDescription()
					+ "</p>");
		}
		
		if(!type.isLegConfigurationAvailable(this.getLegConfiguration())) {
			sb.append(this.getType().applyLegConfigurationTransformation(owner, type.getAllowedLegConfigurations().get(0), true, true));
		}
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Leg.class) && getType().getRace().isFeralPartsAvailable());
	}
	
	// These methods are just for if the Leg's LegConfiguration is of type TAIL_LONG:
	
	// Length:
	
	public float getLengthAsPercentageOfHeight() {
		return lengthAsPercentageOfHeight;
	}

	/**
	 * Sets the tails' length as a percentage of the owner's height. Value is bound to >=2f && <=10f
	 */
	public String setLengthAsPercentageOfHeight(GameCharacter owner, float lengthAsPercentageOfHeight) {
		if(!isCharacterInitialised(owner)) {
			// Allow for setting down to feral minimum, as this could be loading of a feral part:
			this.lengthAsPercentageOfHeight = Math.max(LENGTH_PERCENTAGE_MIN_FERAL, Math.min(lengthAsPercentageOfHeight, LENGTH_PERCENTAGE_MAX));
			return "";
		}
		
		float lengthChange = 0;
		
		float percentageMinimum = owner.isFeral()?LENGTH_PERCENTAGE_MIN_FERAL:LENGTH_PERCENTAGE_MIN;
		if (lengthAsPercentageOfHeight <= percentageMinimum) {
			if (this.lengthAsPercentageOfHeight != percentageMinimum) {
				lengthChange = percentageMinimum - this.lengthAsPercentageOfHeight;
				this.lengthAsPercentageOfHeight = percentageMinimum;
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
		
		if(owner.getLegConfiguration()!=LegConfiguration.TAIL_LONG) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(由于[npc.name]没有"+LegConfiguration.TAIL_LONG+"下肢，所以无事发生……)]</p>");
		}
		
		if(lengthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.tail]的长度没有变化……)]</p>");
		}
		
		String heightPercentageDescription = "(长度为身高的"+((int)(owner.getLegTailLengthAsPercentageOfHeight()*100))+"%)";
		if(lengthChange>0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.her][npc.tail+]里逐渐升起一股强烈的搏动感，不禁发出[npc.a_moan+]。"
						+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的[npc.tail]突然[style.boldGrow(伸长了)]。"
						+ "<br/>"
						+ "[npc.She]现在拥有[style.boldTfGeneric([npc.a_tailLength][npc.tail])]"+heightPercentageDescription+"！"
					+ "</p>");
				
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.her][npc.tail+]里逐渐升起一股强烈的搏动感，不禁发出[npc.a_moan+]。"
						+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的[npc.tail]突然[style.boldShrink(缩短了)]。"
						+ "<br/>"
						+ "[npc.She]现在拥有[style.boldTfGeneric([npc.a_tailLength][npc.tail])]"+heightPercentageDescription+"！"
					+ "</p>");
		}
	}
	
	public int getLength(GameCharacter owner) {
		return (int) (owner.getHeightValue() * getLengthAsPercentageOfHeight());
	}

	public int getLength(Body body) {
		if (body == null) return -1;
		return (int) (body.getHeightValue() * getLengthAsPercentageOfHeight());
	}
	
	
	// Diameter:
	
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
//		System.out.println(owner.getName());
		// Waist-to-height ratio is 0.5 for non-ferals, 0.25 for ferals
		// It is a measurement of waist circumference divided by height
		float waistToHeightRatio = owner.isFeral()?0.25f:0.5f;
		float waistCircumference = owner.getHeightValue()*waistToHeightRatio;
		float waistDiameter = (float) (waistCircumference / Math.PI);
//		System.out.println("WD: "+waistDiameter);
		
		// Modify base diameter (at waist) by hip size:
		float baseDiameter = waistDiameter * (1f + owner.getLegTailGirth().getDiameterPercentageModifier());
//		System.out.println("BD: "+baseDiameter);
		float lengthPercentage = Math.min(1, atLength / this.getLength(owner));
		
		 // Linear diameter tapering:
		// y = 1 - (0.95x)
		// At maximum length, diameter is 5% base diameter
		float diameter = (1 - (0.95f * lengthPercentage)) * baseDiameter;

//		System.out.println("FD: "+diameter);
		
		return diameter;
	}

}