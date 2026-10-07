package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.5
 * @version 0.3.5
 * @author Innoxia
 */
public enum PersonalityCategory {

	CORE("核心", PresetColour.GENERIC_EXCELLENT),
	
	COMBAT("战斗", PresetColour.GENERIC_COMBAT),
	
	SEX("性", PresetColour.GENERIC_SEX),
	
	SPEECH("对话", PresetColour.BASE_PURPLE_LIGHT);

	private String name;
	private Colour colour;
	
	private PersonalityCategory( String name, Colour colour) {
		this.colour = colour;
		this.name = name;
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}
}
