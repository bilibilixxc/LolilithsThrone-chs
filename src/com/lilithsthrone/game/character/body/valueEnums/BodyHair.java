package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.83
 * @version 0.1.86
 * @author Innoxia
 */
public enum BodyHair {

	ZERO_NONE(0, "无", PresetColour.GENERIC_SIZE_ONE),
	ONE_STUBBLE(1, "短茬", PresetColour.GENERIC_SIZE_TWO),
	TWO_MANICURED(2, "齐整", PresetColour.GENERIC_SIZE_THREE),
	THREE_TRIMMED(3, "修剪过", PresetColour.GENERIC_SIZE_FOUR),
	FOUR_NATURAL(4, "普通", PresetColour.GENERIC_SIZE_FIVE),
	FIVE_UNKEMPT(5, "凌乱", PresetColour.GENERIC_SIZE_SIX),
	SIX_BUSHY(6, "浓密", PresetColour.GENERIC_SIZE_SEVEN),
	SEVEN_WILD(7, "野蛮生长", PresetColour.GENERIC_SIZE_EIGHT);
	
	private int value;
	private String descriptor;
	private Colour colour;

	private BodyHair(int value, String descriptor, Colour colour) {
		this.value = value;
		this.descriptor = descriptor;
		this.colour=colour;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}
	
	public static BodyHair getRandomBodyHair() {
		return BodyHair.values()[Util.random.nextInt(BodyHair.values().length)];
	}
	
	public static BodyHair getBodyHairFromValue(int value) {
		for(BodyHair bh : BodyHair.values()) {
			if(bh.getValue() == value) {
				return bh;
			}
		}
		return ZERO_NONE;
	}
}
