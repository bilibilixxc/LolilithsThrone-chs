package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.7
 * @version 0.2.7
 * @author Innoxia
 */
public enum FluidExpulsion {
	
	ZERO_NONE("微量", 0, 20, PresetColour.GENERIC_SIZE_ONE),

	ONE_SMALL("少量", 20, 40, PresetColour.GENERIC_SIZE_TWO),

	TWO_MODERATE("中量", 40, 60, PresetColour.GENERIC_SIZE_THREE),

	THREE_LARGE("大量", 60, 80, PresetColour.GENERIC_SIZE_FOUR),

	FOUR_HUGE("巨量", 80, 100, PresetColour.GENERIC_SIZE_FIVE);

	private int minimumValue, maximumValue;
	private String descriptor;
	private Colour colour;

	private FluidExpulsion(String descriptor, int minimumValue, int maximumValue, Colour colour) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour=colour;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public int getMedianValue() {
		return minimumValue + ((maximumValue - minimumValue) / 2);
	}

	public static FluidExpulsion getFluidExpulsionFromInt(int value) {
		for(FluidExpulsion expulsion : FluidExpulsion.values()) {
			if(value>=expulsion.getMinimumValue() && value<expulsion.getMaximumValue()) {
				return expulsion;
			}
		}
		return FOUR_HUGE;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}
}
