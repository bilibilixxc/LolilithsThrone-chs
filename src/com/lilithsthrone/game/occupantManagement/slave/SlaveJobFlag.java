package com.lilithsthrone.game.occupantManagement.slave;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.8.6
 * @version 0.4.10.7
 * @author Innoxia
 */
public enum SlaveJobFlag {

	EXPERIENCE_GAINS(PresetColour.GENERIC_EXPERIENCE, "经验获取", "奴隶每小时有25%的几率获得5经验。"),
	
	INTERACTION_SEX(PresetColour.GENERIC_SEX, "奴隶性爱", "从事这份工作的奴隶会和其他奴隶做爱。"),
	
	INTERACTION_BONDING(PresetColour.AFFECTION, "奴隶社交", "从事这份工作的奴隶会和其他奴隶社交。"),
	
	GUEST_CAN_WORK(PresetColour.GENERIC_NEUTRAL, "住客工作", "住客可以从事这项工作。"),

	CLEANING_UNAVAILABLE(PresetColour.DIRTY, "无法清洁", "奴隶在从事这份工作时无法自我清洁。"),

	SPECIAL_UNIFORM(PresetColour.GENERIC_MINOR_BAD, "特殊制服", "奴隶在从事这份工作时无法在更衣室进行更衣。");
	
	private Colour colour;
	private String name;
	private String description;
	
	private SlaveJobFlag(Colour colour, String name, String description) {
		this.colour = colour;
		this.name = name;
		this.description = description;
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
