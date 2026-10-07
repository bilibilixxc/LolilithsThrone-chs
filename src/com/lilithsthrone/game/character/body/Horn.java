package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Horn implements BodyPartInterface {

	public static final int MAXIMUM_ROWS = 3;
	public static final int MAXIMUM_HORNS_PER_ROW = 4;
	
	protected AbstractHornType type;
	public int rows;
	public int hornsPerRow;
	public int length;
	
	public Horn(AbstractHornType type, int length) {
		if(length<=0) {
			this.length = 0;
			this.type = HornType.NONE;
			
		} else {
			this.type = type;
			this.length = length;
		}
		
		rows = 1;
		hornsPerRow = type.getDefaultHornsPerRow();
	}

	public Horn(Horn hornToCopy) {
		this.type = hornToCopy.type;
		this.length = hornToCopy.length;
		this.rows = hornToCopy.rows;
		this.hornsPerRow = hornToCopy.hornsPerRow;
	}
	
	@Override
	public AbstractHornType getType() {
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

	public void setType(AbstractHornType type) {
		this.type = type;
	}

	public String setType(GameCharacter owner, AbstractHornType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type.equals(HornType.NONE)) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经没有horns了，所以无事发生……)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有[npc.a_hornRace]的[npc.horns]了，所以无事发生……)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (type == HornType.HORSE_STRAIGHT) {
			this.setHornsPerRow(owner, 1);
			this.setHornRows(owner, 1);
		}

		if(this.type.equals(HornType.NONE)) {
			sb.append(UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]倒吸一口凉气，感到前额变得滚烫又敏感，连忙摸了摸。"
						+ "过了一会儿，[npc.her]震惊地睁大了[npc.eyes]，有什么东西从[npc.her]前额的[npc.faceSkin]之下冲了出来。"));
		} else {
			sb.append(UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]倒吸一口凉气，感到[npc.horn]的根部有一种奇怪的刺痛感。"
						+ "还没等[npc.she]反应过来，[npc.horn]便分崩离析，转瞬消失了。"));
		}

		if(type!=HornType.NONE) {
			sb.append(UtilText.parse(owner, (owner.getTotalHorns()==1
					?"[npc.she]惊喘出声，一处坚硬的小凸起从[npc.her]前额上忽然冒出，并且迅速成长为了"
					:"[npc.she]惊喘出声，一处坚硬的小凸起从[npc.her]头部忽然冒出，并且迅速成长为了")));
		}
		
		this.type = type;
		
		sb.append(type.getTransformationDescription(owner));
		
		if(this.length==0) {
			length = HornLength.ONE_SMALL.getMinimumValue();
		}
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}
	
	public int getHornRows() {
		return rows;
	}

	public String setHornRows(GameCharacter owner, int rows) {
		rows = Math.max(1, Math.min(rows, MAXIMUM_ROWS));
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.rows = rows;
			return "";
		}
		
		if(owner.getHornRows() == rows) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		boolean removingHorns = owner.getHornRows() > rows;
		this.rows = rows;
		
		owner.postTransformationCalculation();
		
		if (owner.getHornType().equals(HornType.NONE)) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		if(removingHorns) {
			return UtilText.parse(owner,
					"<p>"
						+ "一股刺痛感传遍了[npc.namePos]的[npc.horns]，接着这种感觉继续向下，集中在了[npc.her]的前额上。"
						+ "[npc.She]不禁大叫一声，只感到有些[npc.horns]顿时分崩离析，消失在[npc.her]的[npc.faceSkin]之下。<br/>"
						+ "过了一会儿，[npc.sheIs]便得到了[style.boldTfGeneric([npc.a_horns])]。"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "一股刺痛感传遍了[npc.namePos]的[npc.horns]，接着这种感觉继续向下，集中在了[npc.her]的前额上。"
						+ "[npc.She]不禁大叫一声，只感到全新的[npc.horns][style.boldGrow(向上推挤)]着，从[npc.her]的[npc.faceSkin]上长了出来。<br/>"
						+ "过了一会儿，[npc.sheIs]便得到了[style.boldTfGeneric([npc.a_horns])]。"
					+ "</p>");
		}
	}
	
	public int getHornsPerRow() {
		return hornsPerRow;
	}

	public String setHornsPerRow(GameCharacter owner, int hornsPerRow) {
		hornsPerRow = Math.max(1, Math.min(hornsPerRow, MAXIMUM_HORNS_PER_ROW));
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.hornsPerRow = hornsPerRow;
			return "";
		}
		
		if(owner.getHornsPerRow() == hornsPerRow) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		boolean removingHorns = owner.getHornsPerRow() > hornsPerRow;
		this.hornsPerRow = hornsPerRow;
		
		owner.postTransformationCalculation();
		
		if (owner.getHornType().equals(HornType.NONE)) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		if(removingHorns) {
			return UtilText.parse(owner,
					"<p>"
						+ "一股奇怪的躁动感在[npc.namePos]的[npc.horns]基部愈发强烈。"
						+ "还没等[npc.she]反应过来，[npc.horns]便开始[style.boldShrink(解离重组)]。<br/>"
						+ "过了一会儿，[npc.sheIs]就变成了" + (rows==1?"":"每一组") + "[style.boldTfGeneric("+Util.intToString(hornsPerRow)+"根"+(hornsPerRow==1?"[npc.horn]":"[npc.horns]")+")]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "一股奇怪的躁动感在[npc.namePos]的前额愈发强烈。"
						+ "还没等[npc.she]反应过来，全新的[npc.horns]便突然冲出，从[npc.her]的[npc.faceSkin]上长了出来。<br/>"
						+ "过了一会儿，[npc.sheIs]就变成了" + (rows==1?"":"每一组") + "[style.boldTfGeneric("+Util.intToString(hornsPerRow)+"根"+(hornsPerRow==1?"[npc.horn]":"[npc.horns]")+")]！"
					+ "</p>");
		}
	}

	public int getTotalHorns() {
		return getHornRows() * getHornsPerRow();
	}
	
	public HornLength getHornLength() {
		return HornLength.getLengthFromInt(length);
	}
	
	public int getHornLengthValue() {
		return length;
	}

	public void setHornLength(int length) {
		this.length = Math.max(0, Math.min(length, HornLength.FOUR_MASSIVE.getMaximumValue()));
	}

	public void setTypeAndLength(AbstractHornType type, int length) {
		setType(type);
		setHornLength(length);
	}

	public String setHornLength(GameCharacter owner, int length) {
		int oldLength = this.length;
		this.length = Math.max(0, Math.min(length, HornLength.FOUR_MASSIVE.getMaximumValue()));
		int sizeChange = this.length - oldLength;
		
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			return "";
		}
		
		if(owner.getHornType().equals(HornType.NONE)) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]没有角，所以无事发生……)]</p>");
		}
		
		if(sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.horns]的长度没有变化……)]</p>");
		}
		
		if(sizeChange < 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]惊叫了一声，[npc.she]感到一股奇怪的刺痛感传遍[npc.face]，很快便传到了[npc.horns]上，"
							+ "然后突缩了下去，[style.boldShrink(显然更短了)]。<br/>"
						+ "[npc.Name]现在拥有[style.boldTfGeneric("+(getTotalHorns()==1?"[npc.a_hornSize]":"[npc.hornSize]")+"的[npc.horns])]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]惊喘了一声，感到一股温热的脉动感从[npc.face]传递到了[npc.horns]上，"
							+ "然后突长了一截，[style.boldShrink(显然更长了)]。<br/>"
						+ "[npc.Name]现在拥有[style.boldTfGeneric("+(getTotalHorns()==1?"[npc.a_hornSize]":"[npc.hornSize]")+"的[npc.horns])]！"
					+ "</p>");
		}
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Horn.class) && getType().getRace().isFeralPartsAvailable());
	}

}