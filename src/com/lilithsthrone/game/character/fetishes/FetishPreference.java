package com.lilithsthrone.game.character.fetishes;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.4.2
 * @version 0.4.2
 * @author Maxis, Innoxia
 */
public enum FetishPreference {
	
	ZERO_DISABLED(PresetColour.TEXT_GREY, "禁用", 0, 0, 0, 0, 0, "除非特殊情况，该性癖不会被给予任何NPC。"),
	ONE_HATE(PresetColour.GENERIC_BAD, "厌恶", 1, 10, 5, 0, 0, "除非特殊情况，NPC会讨厌或厌恶该性癖。"),
	TWO_DISLIKE(PresetColour.GENERIC_MINOR_BAD, "讨厌", 2, 5, 10, 3, 1, "NPC更有可能讨厌/厌恶该性癖，而不是喜欢/热爱。"),
	THREE_NEUTRAL(PresetColour.TEXT, "中立", 3, 3, 3, 3, 3, "没有偏好。"),
	FOUR_LIKE(PresetColour.GENERIC_MINOR_GOOD, "喜欢", 4, 1, 3, 10, 5, "NPC更有可能喜欢/热爱该性癖，而不是讨厌/厌恶。"),
	FIVE_LOVE(PresetColour.GENERIC_GOOD, "热爱", 5, 0, 0, 3, 5, "NPC只会喜欢或热爱该性癖。"),
	SIX_ALWAYS(PresetColour.GENERIC_EXCELLENT, "总是", 6, 0, 0, 0, 1, "NPC总是会拥有该性癖。");
	
	private Colour colour;
	private String name;
	private int value;
	private int hate;
	private int dislike;
	private int like;
	private int love;
	private String tooltip;
	
	private FetishPreference(Colour colour, String name, int value, int hate, int dislike, int like, int love, String tooltip) {
		this.colour = colour;
		this.name=name;
		this.value=value;
		this.hate=hate;
		this.dislike=dislike;
		this.like=like;
		this.love=love;
		this.tooltip=tooltip;
	}
	
	public static FetishPreference valueOf(Integer i) {
		for(FetishPreference f: FetishPreference.values()) {
			if(f.getValue() == i) {
				return f;
			}
		}
		return null;
	}
	
	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getHate() {
		return hate;
	}
	
	public int getDislike() {
		return dislike;
	}
	
	public int getLike() {
		return like;
	}
	
	public int getLove() {
		return love;
	}

	public String getTooltip() {
		return tooltip;
	}
}
