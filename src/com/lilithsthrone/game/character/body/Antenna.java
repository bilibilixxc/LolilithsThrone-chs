package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Antenna implements BodyPartInterface {

	public static final int MAXIMUM_ROWS = 3;
	public static final int MAXIMUM_ANTENNAE_PER_ROW = 4;
	
	protected AbstractAntennaType type;
	protected int rows;
	public int antennaePerRow;
	public int length;
	
	public Antenna(AbstractAntennaType type, int length) {
		this.type = type;
		this.length = length;
		rows = 1;
		antennaePerRow = type.getDefaultAntennaePerRow();
	}

	public Antenna(Antenna antennaToCopy) {
		this.type = antennaToCopy.type;
		this.length = antennaToCopy.length;
		this.rows = antennaToCopy.rows;
		this.antennaePerRow = antennaToCopy.antennaePerRow;
	}
	
	@Override
	public AbstractAntennaType getType() {
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

	public String setType(GameCharacter owner, AbstractAntennaType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type.equals(AntennaType.NONE)) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经没有)]触须了，所以无事发生……</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有[npc.a_antennaRace]的[npc.antenna]了，所以无事发生……)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(this.type.equals(AntennaType.NONE)) {
			sb.append(UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]倒吸一口凉气，感到前额变得滚烫又敏感，连忙摸了摸。"
						+ "过了一会儿，[npc.her]震惊地睁大了[npc.eyes]，有什么东西从[npc.her]前额的[npc.faceSkin]之下冲了出来。"));
		} else {
			sb.append(UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]倒吸一口凉气，感到[npc.antennae]的根部有一种奇怪的刺痛感。"
						+ "还没等[npc.she]反应过来，[npc.antennae]便分崩离析，转瞬消失了。"));
		}
		
		if(type!=AntennaType.NONE) {
			sb.append(UtilText.parse(owner, 
					"[npc.she]惊喘出声，坚硬的小凸起从[npc.her]脑袋上忽然冒出来，并且迅速成长为了"));
		}
		
		this.type = type;
		
		sb.append(type.getTransformationDescription(owner));
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}
	
	public int getAntennaRows() {
		return rows;
	}

	public String setAntennaRows(GameCharacter owner, int rows) {
		rows = Math.max(1, Math.min(rows, MAXIMUM_ROWS));
		if(!isCharacterInitialised(owner)) {
			this.rows = rows;
			return "";
		}
		
		if(owner.getAntennaRows() == rows) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		boolean removingAntennae = owner.getAntennaRows() > rows;
		this.rows = rows;
		
		owner.postTransformationCalculation();
		
		if (owner.getAntennaType().equals(AntennaType.NONE)) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		if(removingAntennae) {
			return UtilText.parse(owner,
					"<p>"
						+ "一股刺痛感传遍了[npc.namePos]的[npc.antennae]，接着这种感觉继续向下，集中在了[npc.her]的前额上。"
						+ "[npc.She]不禁大叫一声，只感到有些[npc.antennae]顿时分崩离析，消失在[npc.her]的[npc.faceSkin]之下。<br/>"
						+ "过了一会儿，[npc.sheIs]便得到了[style.boldTfGeneric([npc.a_antennae])]。"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "一股刺痛感传遍了[npc.namePos]的[npc.antennae]，接着这种感觉继续向下，集中在了[npc.her]的前额上。"
						+ "[npc.She]不禁大叫一声，只感到全新的[npc.antennae][style.boldGrow(向上推挤)]着，从[npc.her]的[npc.faceSkin]上长了出来。<br/>"
						+ "过了一会儿，[npc.sheIs]便得到了[style.boldTfGeneric([npc.a_antennae])]。"
					+ "</p>");
		}
	}
	
	public int getAntennaePerRow() {
		return antennaePerRow;
	}

	public String setAntennaePerRow(GameCharacter owner, int antennaePerRow) {
		antennaePerRow = Math.max(1, Math.min(antennaePerRow, MAXIMUM_ANTENNAE_PER_ROW));
		if(!isCharacterInitialised(owner)) {
			this.antennaePerRow = antennaePerRow;
			return "";
		}
		
		if(owner.getAntennaePerRow() == antennaePerRow) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		boolean removingAntennae = owner.getAntennaePerRow() > antennaePerRow;
		this.antennaePerRow = antennaePerRow;
		
		owner.postTransformationCalculation();
		
		if (owner.getAntennaType().equals(AntennaType.NONE)) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		if(removingAntennae) {
			return UtilText.parse(owner,
					"<p>"
						+ "一股奇怪的躁动感在[npc.namePos]的[npc.antennae]基部愈发强烈。"
						+ "还没等[npc.she]反应过来，[npc.antennae]便开始[style.boldShrink(解离重组)]。<br/>"
						+ "过了一会儿，[npc.sheIs]就变成了" + (rows==1?"":"每一组") + "[style.boldTfGeneric("+Util.intToString(antennaePerRow)+"根"+(antennaePerRow==1?"[npc.antenna]":"[npc.antennae]")+")]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "一股奇怪的躁动感在[npc.namePos]的前额愈发强烈。"
						+ "还没等[npc.she]反应过来，全新的[npc.antennae]便突然冲出，从[npc.her]的[npc.faceSkin]上长了出来。<br/>"
						+ "过了一会儿，[npc.sheIs]就变成了" + (rows==1?"":"每一组") + "[style.boldTfGeneric("+Util.intToString(antennaePerRow)+"根"+(antennaePerRow==1?"[npc.antenna]":"[npc.antennae]")+")]！"
					+ "</p>");
		}
	}

	public int getTotalAntennae() {
		return getAntennaRows() * getAntennaePerRow();
	}
	
	public HornLength getAntennaLength() {
		return HornLength.getLengthFromInt(length);
	}
	
	public int getAntennaLengthValue() {
		return length;
	}

	public String setAntennaLength(GameCharacter owner, int length) {
		int oldLength = this.length;
		this.length = Math.max(0, Math.min(length, HornLength.FOUR_MASSIVE.getMaximumValue()));
		int sizeChange = this.length - oldLength;
		
		if (!isCharacterInitialised(owner)) {
			return "";
		}
		
		if(owner.getAntennaType().equals(AntennaType.NONE)) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]没有触须，所以无事发生……)]</p>");
		}
		
		if(sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.antennae]的长度没有变化……)]</p>");
		}
		
		if(sizeChange < 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]惊叫了一声，[npc.she]感到一股奇怪的刺痛感传遍[npc.face]，很快便传到了[npc.antennae]上，"
							+ "随后[npc.antennae]便缩回去一截，[style.boldShrink(显然更短了)]。<br/>"
						+ "[npc.Name]目前拥有[style.boldTfGeneric([npc.antennaSize]的[npc.antennae])]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]发出一小声尖叫，[npc.she]感到一股温热的脉动感从[npc.face]传递到了[npc.antennae]，"
							+ "随后[npc.antennae]便长出来一截，[style.boldGrow(显然更长了)]。<br/>"
						+ "[npc.Name]目前拥有[style.boldTfGeneric([npc.antennaSize]的[npc.antennae])]！"
					+ "</p>");
		}
	}
	
	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Antenna.class) && getType().getRace().isFeralPartsAvailable());
	}
}
