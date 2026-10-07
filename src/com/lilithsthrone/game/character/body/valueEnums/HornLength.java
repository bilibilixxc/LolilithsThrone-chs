package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Sizes in cm.
 * 
 * @since 0.1.89
 * @version 0.4.4.6
 * @author Innoxia
 */
public enum HornLength {
	
	ZERO_TINY("微小", 0, 5, PresetColour.GENERIC_SIZE_ONE, false),

	ONE_SMALL("短小", 5, 15, PresetColour.GENERIC_SIZE_THREE, true),

	TWO_LONG("修长", 15, 30, PresetColour.GENERIC_SIZE_FIVE, true),

	THREE_HUGE("极大", 30, 50, PresetColour.GENERIC_SIZE_SEVEN, true),

	FOUR_MASSIVE("巨大", 50, 75, PresetColour.GENERIC_SIZE_NINE, true);

	private int minimumValue, maximumValue;
	private String descriptor;
	private Colour colour;
	private boolean suitableAsHandles;

	private HornLength(String descriptor, int minimumValue, int maximumValue, Colour colour, boolean suitableAsHandles) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
		this.suitableAsHandles = suitableAsHandles;
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

	public static HornLength getLengthFromInt(int cm) {
		for(HornLength ps : HornLength.values()) {
			if(cm>=ps.getMinimumValue() && cm<ps.getMaximumValue()) {
				return ps;
			}
		}
		return FOUR_MASSIVE;
	}
	
	public String getDescriptor() {
		return descriptor;
	}
	
	public Colour getColour() {
		return colour;
	}

	public boolean isSuitableAsHandles() {
		return suitableAsHandles;
	}
}
