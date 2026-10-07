package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.86
 * @version 0.4
 * @author Innoxia
 */
public enum Height {
	
	// Need to standardise to 1.5 each category

	/**6" to 2'*/
	NEGATIVE_THREE_MINIMUM("妖精大小", 15, 61, PresetColour.GENERIC_SIZE_ONE),
	
	/**2' to 3'6"*/
	NEGATIVE_TWO_MINIMUM("十分较小", 61, 106, PresetColour.GENERIC_SIZE_ONE),
	
	/**3'6" to 4'*/
	NEGATIVE_ONE_TINY("娇小", 106, 122, PresetColour.GENERIC_SIZE_ONE),
	
	/**4' to 5'*/
	ZERO_TINY("矮小", 122, 152, PresetColour.GENERIC_SIZE_ONE),
	
	/**5' to 5'6"*/
	ONE_SHORT("稍显矮小", 152, 166, PresetColour.GENERIC_SIZE_TWO),

	/**5'6" to 6'*/
	TWO_AVERAGE("一般身高", 166, 183, PresetColour.GENERIC_SIZE_THREE),

	/**6' to 6'6"*/
	THREE_TALL("高挑", 183, 198, PresetColour.GENERIC_SIZE_FOUR),

	/**6'6" to 7'*/
	FOUR_VERY_TALL("高大", 198, 214, PresetColour.GENERIC_SIZE_FIVE),

	/**7' to 7'6"*/
	FIVE_ENORMOUS("高耸", 214, 228, PresetColour.GENERIC_SIZE_SIX),

	/**7'6" to 9'"*/
	SIX_GIANT("巨大", 228, 274, PresetColour.GENERIC_SIZE_SEVEN),

	/**9' to 12'*/
	SEVEN_COLOSSAL("庞大", 274, 366, PresetColour.GENERIC_SIZE_EIGHT);
	
	private int minimumValue;
	private int maximumValue;
	private String descriptor;
	private Colour colour;

	private Height(String descriptor, int minimumValue, int maximumValue, Colour colour) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
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

	public int getRandomValue() {
		return minimumValue + Util.random.nextInt(maximumValue - minimumValue);
	}
	
	/**
	 * If height is less than this value, then that height is short stature.
	 */
	public static int getShortStatureCutOff() {
		return NEGATIVE_THREE_MINIMUM.getMinimumValue();
	}
	
	public boolean isShortStature() {
		return this.getMinimumValue()<getShortStatureCutOff();
	}

	/**
	 * If height is less than this value, then that height is short stature.
	 */
	public static int getFairySizeCutOff() {
		return NEGATIVE_TWO_MINIMUM.getMinimumValue();
	}
	
	public boolean isFairySized() {
		return this.getMinimumValue()<getFairySizeCutOff();
	}
	
	public static int getMaximumHeightForCharacterCreation() {
		return SEVEN_COLOSSAL.getMaximumValue();
	}
	
	public static Height getHeightFromInt(int centimeters) {
		for(Height cs : Height.values()) {
			if(centimeters >= cs.getMinimumValue() && centimeters < cs.getMaximumValue()) {
				return cs;
			}
		}
		return SEVEN_COLOSSAL;
	}
	
	public String getDescriptor() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}
}
