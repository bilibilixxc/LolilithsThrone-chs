package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.1
 * @version 0.4
 * @author Innoxia
 */
public enum PenetrationGirth {

	ZERO_THIN(0, -0.8f, "细小", PresetColour.GENERIC_SIZE_ONE),
	
	ONE_SLENDER(1, -0.4f, "纤细", PresetColour.GENERIC_SIZE_TWO),
	
	TWO_NARROW(2, -0.2f, "较细", PresetColour.GENERIC_SIZE_THREE),
	
	THREE_AVERAGE(3, 0, "平均直径", PresetColour.GENERIC_SIZE_FOUR),
	
	FOUR_GIRTHY(4, 0.2f, "较粗", PresetColour.GENERIC_SIZE_FIVE),
	
	FIVE_THICK(5, 0.4f, "粗大", PresetColour.GENERIC_SIZE_SIX),
	
	SIX_CHUBBY(6, 0.6f, "粗壮", PresetColour.GENERIC_SIZE_SEVEN),
	
	SEVEN_FAT(7, 0.8f, "极粗", PresetColour.GENERIC_SIZE_EIGHT);
	
	
	private int value;
	private float diameterPercentageModifier;
	private String descriptor;
	private Colour colour;

	private PenetrationGirth(int value, float diameterPercentageModifier, String descriptor, Colour colour) {
		this.value = value;
		this.diameterPercentageModifier = diameterPercentageModifier;
		this.descriptor = descriptor;
		this.colour = colour;
	}

	public int getValue() {
		return value;
	}

	/**
	 * @return The percentage (as a float from 0->1) by which this girth increases the diameter of a penetration type.
	 */
	public float getDiameterPercentageModifier() {
		return diameterPercentageModifier;
	}
	
	public String getName() {
		return descriptor;
	}
	
	public static PenetrationGirth getGirthFromInt(int size) {
		for(PenetrationGirth ls : PenetrationGirth.values()) {
			if(size == ls.getValue()) {
				return ls;
			}
		}
		return ZERO_THIN;
	}
	
	public static int getMaximum() {
		return SEVEN_FAT.getValue();
	}
	
	public Colour getColour() {
		return colour;
	}
}
