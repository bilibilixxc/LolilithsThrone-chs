package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Arm implements BodyPartInterface {

	
	public static final int MAXIMUM_ROWS = 3;
	
	protected AbstractArmType type;
	protected int armRows;
	public BodyHair underarmHair;

	public Arm(AbstractArmType type, int armRows) {
		this.type = type;
		this.armRows = armRows;
		underarmHair = BodyHair.ZERO_NONE;
	}

	public Arm(Arm armToCopy) {
		this.type = armToCopy.type;
		this.armRows = armToCopy.armRows;
		this.underarmHair = armToCopy.underarmHair;
	}
	
	@Override
	public AbstractArmType getType() {
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
		List<String> descriptorList = new ArrayList<>();
		
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(Util.randomItemFrom(gc.getBodyShape().getLimbDescriptors()));

		return Util.randomItemFrom(descriptorList);
	}

	public String setType(GameCharacter owner, AbstractArmType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经拥有了[npc.a_armRace]的[npc.arms]，所以无事发生……)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (owner.isPlayer()) {
			sb.append(
					"<p>"
						+ "你忽然觉得[pc.arms+]又热又痒，你意识到转变开始了，于是不禁倒吸了一口凉气。"
						+ "你的大臂开始扭曲变形，你发出一声痛叫，却无能为力，只得眼睁睁地看着手臂转化成了新的形态。");
		} else {
			sb.append(
					"<p>"
						+ "[npc.Name]感到[npc.she][npc.arms+]变得又热又痒，顿时手足无措，[npc.she]意识到转变开始了，于是不禁倒吸了一口凉气。"
						+ "[npc.her]的大臂开始扭曲变形，[npc.she]发出一声痛叫，却无能为力，只得眼睁睁地看着手臂转化成了新的形态。");
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		
		sb.append(type.getTransformationDescription(owner)+"</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getArmRows() {
		return armRows;
	}

	public String setArmRows(GameCharacter owner, int armRows) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			armRows = Math.max(1, Math.min(armRows, MAXIMUM_ROWS));
			this.armRows = armRows;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		int currentArmRows = getArmRows();
		armRows = Math.max(1, Math.min(armRows, MAXIMUM_ROWS));
		if (armRows == currentArmRows) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (armRows < currentArmRows) {
			boolean losesTwoPairs = (armRows + 2) == currentArmRows;
			if (owner.isPlayer()) {
				sb.append(
						"<p>"
							+ "你感觉到一阵奇妙的压力，出现在"
							+ (losesTwoPairs
								? "你额外的两对"
								: (armRows == 2 
									? "最下方的一对" 
									: "你额外的那对"))
							+ "[pc.arms]的基部，还没等你反应过来，这些手臂便快速潜入了你躯干的[pc.skin]之下。<br/>" 
							+ "你现在拥有[style.boldTfLesser(" + Util.intToString(armRows) + "对[pc.arms])]，[pc.materialDescriptor][pc.armFullDescriptionColour]。"
						+ "</p>");
			} else {
				sb.append(UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]担心地低头瞄向"
							+ (losesTwoPairs
								? "[npc.her]额外的两对"
								: (armRows == 2 
									? "[npc.her]最下方的一对" 
									: "[npc.her]额外的那对"))
							+ "[npc.arms]，还没等[npc.she]反应过来，这些手臂便快速潜入了躯干的[npc.skin]之下。<br/>" 
							+ "[npc.She]现在拥有[style.boldTfLesser(" + Util.intToString(armRows) + "对[npc.arms])]，[npc.materialDescriptor][npc.armFullDescriptionColour]。"
						+ "</p>"));
			}
			
		} else {
			boolean gainsTwoPairs = (armRows - 2) == currentArmRows;
			if (owner.isPlayer()) {
				sb.append(
						"<p>"
							+ "你感觉到一阵奇妙的压力，出现在你的躯干两侧，还没等你反应过来，"
								+ (gainsTwoPairs
									? "额外的两对"
									: "额外的一对")
							+ "[pc.arms]便迅速从躯干偏下部分的[pc.skin]中生长了出来。<br/>"
							+ "你现在拥有[style.boldTfLesser(" + Util.intToString(armRows) + "对[pc.arms])]，[pc.materialDescriptor][pc.armFullDescriptionColour]。"
						+ "</p>");
			} else {
				sb.append(UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]担心地提都瞄向[npc.her]的躯干，还没等[npc.she]反应过来，"
								+ (gainsTwoPairs
									? "额外的两对"
									: "额外的一对")
							+ "[npc.arms]便迅速从躯干偏下部分的[npc.skin]中生长了出来。<br/>"
							+ "[npc.She]现在拥有[style.boldTfLesser(" + Util.intToString(armRows) + "对[npc.arms])]，[npc.materialDescriptor][npc.armFullDescriptionColour]。"
						+ "</p>"));
			}
		}
		
		this.armRows = armRows;
		
		sb.append(UtilText.parse(owner,
				"<p>"
					+ owner.postTransformationCalculation()
				+ "</p>"));
		
		return sb.toString();
	}

	public BodyHair getUnderarmHair() {
		return underarmHair;
	}

	public Covering getUnderarmHairType(GameCharacter owner) {
		return owner.getCovering(owner.getBodyHairCoveringType(owner.getArmType().getRace()));
	}

	public String setUnderarmHair(GameCharacter owner, BodyHair underarmHair) {
		if(!isCharacterInitialised(owner)) {
			this.underarmHair = underarmHair;
			return "";
		}

		if(!this.getType().isUnderarmHairAllowed()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(由于[npc.namePos]的手臂类型不允许[npc.herHim]生长任何腋下毛发，所以无事发生……)]</p>");
		}
		
		if(getUnderarmHair() == underarmHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		StringBuilder sb = new StringBuilder();
		
		switch(underarmHair) {
			case ZERO_NONE:
				sb.append(UtilText.parse(owner, "<p>[npc.her]的腋下已经没有了"+getUnderarmHairType(owner).getFullDescription(owner, true)+"的踪迹。</p>"));
				break;
			case ONE_STUBBLE:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的腋下现在有一小片短茬的"+getUnderarmHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
			case TWO_MANICURED:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的腋下现在有一小片齐整的"+getUnderarmHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
			case THREE_TRIMMED:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的腋下现在有一小片修剪过的"+getUnderarmHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
			case FOUR_NATURAL:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的腋下自然生长着一片"+getUnderarmHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
			case FIVE_UNKEMPT:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的腋下现在有一片凌乱的"+getUnderarmHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
			case SIX_BUSHY:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的腋下现在有一片厚实浓密的"+getUnderarmHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
			case SEVEN_WILD:
				sb.append(UtilText.parse(owner, "<p>[npc.Name]的腋下现在有一大片野蛮生长的"+getUnderarmHairType(owner).getFullDescription(owner, true)+"。</p>"));
				break;
		}
		
		this.underarmHair = underarmHair;

		return sb.toString();
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Arm.class) && getType().getRace().isFeralPartsAvailable());
	}
}
