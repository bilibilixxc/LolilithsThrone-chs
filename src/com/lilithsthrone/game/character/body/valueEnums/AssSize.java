package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.1.90
 * @author Innoxia
 */
public enum AssSize {
	
	ZERO_FLAT("平坦", 0, PresetColour.GENERIC_SIZE_ONE),
	
	ONE_TINY("娇小", 1, PresetColour.GENERIC_SIZE_TWO),
	
	TWO_SMALL("稍小", 2, PresetColour.GENERIC_SIZE_THREE),
	
	THREE_NORMAL("圆润", 3, PresetColour.GENERIC_SIZE_FOUR),
	
	FOUR_LARGE("较大", 4, PresetColour.GENERIC_SIZE_FIVE),
	
	FIVE_HUGE("极大", 5, PresetColour.GENERIC_SIZE_SIX),
	
	SIX_MASSIVE("硕大", 6, PresetColour.GENERIC_SIZE_SEVEN),
	
	SEVEN_GIGANTIC("巨大", 7, PresetColour.GENERIC_SIZE_EIGHT);

	private String descriptor;
	private int size;
	private Colour colour;

	private AssSize(String descriptor, int assSize, Colour colour) {
		this.descriptor = descriptor;
		this.size = assSize;
		this.colour = colour;
	}

	public static AssSize getAssSizeFromInt(int assSize) {
		for(AssSize as : AssSize.values()) {
			if(as.getValue() == assSize) {
				return as;
			}
		}
		return SEVEN_GIGANTIC;
	}

	/**
	 * To fit into a sentence: "You have a "+getDescriptor()+" ass."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public int getValue() {
		return size;
	}

	public Colour getColour() {
		return colour;
	}
}
