package com.lilithsthrone.game.inventory;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.8.2
 * @author Innoxia
 */
public enum Rarity {

	COMMON("普通", PresetColour.RARITY_COMMON, PresetColour.RARITY_COMMON_BACKGROUND),
	
	UNCOMMON("非普通", PresetColour.RARITY_UNCOMMON, PresetColour.RARITY_UNCOMMON_BACKGROUND),
	
	RARE("稀有", PresetColour.RARITY_RARE, PresetColour.RARITY_RARE_BACKGROUND),
	
	EPIC("史诗", PresetColour.RARITY_EPIC, PresetColour.RARITY_EPIC_BACKGROUND),
	
	LEGENDARY("传说", PresetColour.RARITY_LEGENDARY, PresetColour.RARITY_LEGENDARY_BACKGROUND),
	
	QUEST("独特", PresetColour.RARITY_QUEST, PresetColour.RARITY_QUEST_BACKGROUND),
	
	JINXED("厄运", PresetColour.RARITY_JINXED, PresetColour.RARITY_JINXED_BACKGROUND);

	private String name;
	private Colour colour;
	private Colour backgroundColour;
	
	private Rarity(String name, Colour colour, Colour backgroundColour) {
		this.name = name;
		this.colour = colour;
		this.backgroundColour = backgroundColour;
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}

	public Colour getBackgroundColour() {
		return backgroundColour;
	}
}
