package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.82
 * @version 0.3.8.9
 * @author Innoxia
 */
public enum ObedienceLevel {
	
	
	NEGATIVE_FIVE_REBELLIOUS("反叛", -100, -90, PresetColour.AFFECTION_NEGATIVE_FIVE),
	
	NEGATIVE_FOUR_DEFIANT("忤逆", -90, -70, PresetColour.AFFECTION_NEGATIVE_FOUR),
	
	NEGATIVE_THREE_STRONG_INSUBORDINATE("违抗", -70, -50, PresetColour.AFFECTION_NEGATIVE_THREE),
	
	NEGATIVE_TWO_UNRULY("不服管教", -50, -30, PresetColour.AFFECTION_NEGATIVE_TWO),
	
	NEGATIVE_ONE_DISOBEDIENT("叛逆", -30, -10, PresetColour.AFFECTION_NEGATIVE_ONE),

	ZERO_FREE_WILLED("随心所欲", -10, 10, PresetColour.AFFECTION_NEUTRAL),

	POSITIVE_ONE_AGREEABLE("顺从", 10, 30, PresetColour.AFFECTION_POSITIVE_ONE),

	POSITIVE_TWO_OBEDIENT("服从", 30, 50, PresetColour.AFFECTION_POSITIVE_TWO),

	POSITIVE_THREE_DISCIPLINED("守纪", 50, 70, PresetColour.AFFECTION_POSITIVE_THREE),

	POSITIVE_FOUR_DUTIFUL("恭顺", 70, 90, PresetColour.AFFECTION_POSITIVE_FOUR),

	POSITIVE_FIVE_SUBSERVIENT("卑躬屈膝", 90, 100, PresetColour.AFFECTION_POSITIVE_FIVE);
	
	
	private String name;
	private int minimumValue;
	private int maximumValue;
	private Colour colour;

	private ObedienceLevel(String name, int minimumValue, int maximumValue, Colour colour) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
	}
	
	private static String getObedienceName(ObedienceLevel affectionLevel, boolean withColour) {
		if(withColour) {
			return "<span style='color:"+affectionLevel.getColour().toWebHexString()+";'>"+affectionLevel.getName()+"</span>";
		} else {
			return affectionLevel.getName();
		}
	}

	public String getDescription(GameCharacter character, boolean withColour, boolean longDescription) {
		return ObedienceLevel.getDescription(character, this, withColour, longDescription);
	}
	
	public static String getDescription(GameCharacter character, ObedienceLevel affectionLevel, boolean withColour, boolean longDescription) {
		StringBuilder sb = new StringBuilder();
		
		String obedienceName = getObedienceName(affectionLevel, withColour);
		
		switch(affectionLevel) {
			case NEGATIVE_FIVE_REBELLIOUS:
				sb.append(UtilText.parse(character, "[npc.Name]表现得"+obedienceName+(longDescription?"，断然拒绝遵从任何命令。":"。")));
				break;
			case NEGATIVE_FOUR_DEFIANT:
				sb.append(UtilText.parse(character, "[npc.Name]表现得"+obedienceName+(longDescription?"，只有在被强迫时才会遵从命令。":"。")));
				break;
			case NEGATIVE_THREE_STRONG_INSUBORDINATE:
				sb.append(UtilText.parse(character, "[npc.Name]表现得"+obedienceName+(longDescription?"，有时会拒绝遵从命令。":"。")));
				break;
			case NEGATIVE_TWO_UNRULY:
				sb.append(UtilText.parse(character, "[npc.Name]表现得"+obedienceName+(longDescription?"，只有在觉得"
						+"自己能够"
						+ "逃脱惩罚时才会拒绝遵从命令。":"。")));
				break;
			case NEGATIVE_ONE_DISOBEDIENT:
				sb.append(UtilText.parse(character, "[npc.Name]表现得"+obedienceName+(longDescription?"，总会埋怨自己必须要听从命令。":"。")));
				break;
			case ZERO_FREE_WILLED:
				sb.append(UtilText.parse(character, "[npc.Name]表现得"+obedienceName+(longDescription?"，而且想做什么就做什么。":"。")));
				break;
			case POSITIVE_ONE_AGREEABLE:
				sb.append(UtilText.parse(character, "[npc.Name]表现得"+obedienceName+(longDescription?"，并且会毫无怨言地执行大多数命令。":"。")));
				break;
			case POSITIVE_TWO_OBEDIENT:
				sb.append(UtilText.parse(character, "[npc.Name]表现得"+obedienceName+(longDescription?"，并且几乎会做所有被吩咐的事情。":"。")));
				break;
			case POSITIVE_THREE_DISCIPLINED:
				sb.append(UtilText.parse(character, "[npc.Name]表现得"+obedienceName+(longDescription?"，并且会毫无怨言地执行命令。":"。")));
				break;
			case POSITIVE_FOUR_DUTIFUL:
				sb.append(UtilText.parse(character, "[npc.Name]表现得"+obedienceName+(longDescription?"，并且总能超出命令的要求。":"。")));
				break;
			case POSITIVE_FIVE_SUBSERVIENT:
				sb.append(UtilText.parse(character, "[npc.Name]表现得"+obedienceName+(longDescription?"，并且无论被吩咐什么事情都会去完成。":"。")));
				break;
		}
		
		return sb.toString();
	}
	
	public String getName() {
		return name;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}
	
	public int getMedianValue() {
		return (minimumValue + maximumValue) / 2;
	}

	public Colour getColour() {
		return colour;
	}

	public static ObedienceLevel getObedienceLevelFromValue(float value){
		for(ObedienceLevel al : ObedienceLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return POSITIVE_FIVE_SUBSERVIENT;
	}
	
	public boolean isGreaterThan(ObedienceLevel levelComparison) {
		return this.getMaximumValue() > levelComparison.getMaximumValue();
	}
	
	public boolean isLessThan(ObedienceLevel levelComparison) {
		return this.getMaximumValue() < levelComparison.getMaximumValue();
	}
}
