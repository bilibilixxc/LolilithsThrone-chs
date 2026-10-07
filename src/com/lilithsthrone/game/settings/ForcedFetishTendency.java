package com.lilithsthrone.game.settings;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.95
 * @version 0.2.3
 * @author FeiFongWong
 */
public enum ForcedFetishTendency {
	
	BOTTOM_HEAVY("低姿态+", "强加的性癖总会是低姿态的并移除高姿态的行为。", PresetColour.BASE_PINK_LIGHT),
	BOTTOM("低姿态", "当符合NPC喜好时，强加的性癖经常会是低姿态的并移除高姿态的行为。", PresetColour.BASE_PINK),
	NEUTRAL("中性", "强加的性癖单纯由NPC的喜好、一时的心血来潮和宇宙固有的随机性决定。", PresetColour.ANDROGYNOUS),
	TOP ("高姿态", "当符合NPC喜好时，强加的性癖经常会是高姿态的并移除低姿态的行为。", PresetColour.BASE_PURPLE_LIGHT),
	TOP_HEAVY("高姿态+", "强加的性癖总会是高姿态的并移除低姿态的行为。", PresetColour.BASE_PURPLE);

	private final String name;
	private final String description;
	private final Colour colour;

	ForcedFetishTendency(String name, String description, Colour colour) {
		this.name = name;
		this.description = description;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public Colour getColour() {
		return colour;
	}

}
