package com.lilithsthrone.game.settings;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public enum DifficultyLevel {
	
	NORMAL("人类", "符合常理的游戏难度。无等级乘数，无伤害修正。", PresetColour.RACE_HUMAN, false, 1, 1),

	LEVEL_SCALING("化形", "敌人等级随你的角色等级提升，无伤害修正。", PresetColour.RACE_CAT_MORPH, true, 1, 1),
	
	HARD("恶魔", "敌人等级随你的角色等级提升，造成200%伤害。", PresetColour.RACE_DEMON, true, 2, 1),
	
	NIGHTMARE("莉琳", "敌人等级随你的角色等级提升，造成200%伤害且只受到50%伤害。", PresetColour.BASE_PURPLE, true, 2, 0.5f),
	
	HELL("莉莉丝", "敌人等级永远是你的2倍，造成400%伤害且只受到25%伤害。准备好战败吧，无数次。", PresetColour.BASE_CRIMSON, true, 4, 0.25f);

	private String name;
	private String description;
	private Colour colour;
	private boolean isNPCLevelScaling;
	private float damageModifierNPC;
	private float damageModifierPlayer;
	
	private DifficultyLevel(String name, String description, Colour colour, boolean isNPCLevelScaling, float damageModifierNPC, float damageModifierPlayer) {
		this.name = name;
		this.description = description;
		this.colour = colour;
		this.isNPCLevelScaling = isNPCLevelScaling;
		this.damageModifierNPC = damageModifierNPC;
		this.damageModifierPlayer = damageModifierPlayer;
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

	public boolean isNPCLevelScaling() {
		return isNPCLevelScaling;
	}

	public float getDamageModifierNPC() {
		return damageModifierNPC;
	}

	public float getDamageModifierPlayer() {
		return damageModifierPlayer;
	}
}
