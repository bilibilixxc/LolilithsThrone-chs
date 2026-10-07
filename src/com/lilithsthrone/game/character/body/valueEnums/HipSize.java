package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum HipSize {
	
	ZERO_NO_HIPS("干瘪", 0, PresetColour.GENERIC_SIZE_ONE),
	
	ONE_VERY_NARROW("非常平坦", 1, PresetColour.GENERIC_SIZE_TWO),
	
	TWO_NARROW("平坦", 2, PresetColour.GENERIC_SIZE_THREE),
	
	THREE_GIRLY("翘挺", 3, PresetColour.GENERIC_SIZE_FOUR),
	
	FOUR_WOMANLY("丰满", 4, PresetColour.GENERIC_SIZE_FIVE),
	
	FIVE_VERY_WIDE("宽大", 5, PresetColour.GENERIC_SIZE_SIX),
	
	SIX_EXTREMELY_WIDE("非常宽大", 6, PresetColour.GENERIC_SIZE_SEVEN),
	
	SEVEN_ABSURDLY_WIDE("宽大得离谱", 7, PresetColour.GENERIC_SIZE_EIGHT);

	
	private String descriptor;
	private int size;
	private Colour colour;

	private HipSize(String descriptor, int hipSize, Colour colour) {
		this.descriptor = descriptor;
		this.size = hipSize;
		this.colour=colour;
	}

	public static HipSize getHipSizeFromInt(int hipSize) {
		for(HipSize hs : HipSize.values()) {
			if(hipSize == hs.getValue()) {
				return hs;
			}
		}
		return SEVEN_ABSURDLY_WIDE;
	}

	/**
	 * To fit into a sentence: "You have "+getDescriptor()+" hips."
	 */
	public String getDescriptor() {
		if(Main.game.isSillyModeEnabled()) {
			switch(this) {
				case FIVE_VERY_WIDE:
					return "肥肥";
				case SIX_EXTREMELY_WIDE:
					return "超级肥肥";
				case SEVEN_ABSURDLY_WIDE:
					return "荒唐肥肥";
				case ZERO_NO_HIPS:
				case ONE_VERY_NARROW:
				case TWO_NARROW:
				case THREE_GIRLY:
				case FOUR_WOMANLY:
					break;
			}
		}
		return descriptor;
	}

	public int getValue() {
		return size;
	}

	public Colour getColour() {
		return colour;
	}
}
