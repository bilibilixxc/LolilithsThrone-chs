package com.lilithsthrone.game.character.gender;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.69
 * @version 0.1.69
 * @author Innoxia
 */
public enum AndrogynousIdentification {
	FEMININE("女性", PresetColour.FEMININE),
	CLOTHING_FEMININE("衣着女性", PresetColour.ANDROGYNOUS),
	CLOTHING_MASCULINE("衣着男性", PresetColour.ANDROGYNOUS),
	MASCULINE("男性", PresetColour.MASCULINE);
	
	private String name;
	private Colour colour;
	
	private AndrogynousIdentification(String name, Colour colour) {
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
