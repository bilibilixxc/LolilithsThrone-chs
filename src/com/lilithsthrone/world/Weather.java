package com.lilithsthrone.world;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public enum Weather {
	
	CLOUD("阴天", PresetColour.BASE_GREY),
	
	CLEAR("晴天", PresetColour.BASE_YELLOW_PALE),
	
	RAIN("雨天", PresetColour.BASE_BLUE),

	SNOW("雪天", PresetColour.BASE_BLUE_LIGHT),
	
	MAGIC_STORM_GATHERING("风暴将至", PresetColour.GENERIC_ARCANE),
	
	MAGIC_STORM("奥术风暴", PresetColour.GENERIC_ARCANE);

	private String name;
	private Colour colour;

	private Weather(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}
}
