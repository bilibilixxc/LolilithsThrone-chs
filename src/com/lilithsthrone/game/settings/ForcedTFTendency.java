package com.lilithsthrone.game.settings;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.95
 * @version 0.3.5.5
 * @author FeiFongWong, Innoxia
 */
public enum ForcedTFTendency {
	
	FEMININE_HEAVY(true, false, "女性化+", "强制转化有很大概率使你更加女性化，与NPC的喜好无关。", PresetColour.FEMININE_PLUS),
	
	FEMININE(true, false, "女性化", "当符合NPC喜好时，强制转化通常会使你更加女性化。", PresetColour.FEMININE),
	
	NEUTRAL(false, false, "中性", "强制转化的性别效果单纯由NPC的喜好、一时的心血来潮和宇宙固有的随机性决定。", PresetColour.ANDROGYNOUS),
	
	MASCULINE(false, true, "男性化", "当符合NPC喜好时，强制转化通常会使你更加男性化。", PresetColour.MASCULINE),
	
	MASCULINE_HEAVY(false, true, "男性化+", "强制转化有很大概率使你更加男性化，与NPC的喜好无关。", PresetColour.MASCULINE_PLUS);
	
	
	private final String name;
	private final String description;
	private final boolean feminine;
	private final boolean masculine;
	private final Colour colour;
	
	ForcedTFTendency(boolean feminine, boolean masculine, String name, String description, Colour colour) {
		this.name = name;
		this.description = description;
		this.feminine = feminine;
		this.masculine = masculine;
		this.colour = colour;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean isFeminine() {
		return feminine;
	}
	
	public boolean isMasculine() {
		return masculine;
	}
	
	public Colour getColour() {
		return colour;
	}
}
