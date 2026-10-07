package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.65
 * @version 0.3.8.2
 * @author Innoxia
 */
public enum ArousalLevel {

	ZERO_NONE("无", 0, 10, PresetColour.AROUSAL_STAGE_ZERO) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_0;
		}
	},

	ONE_TURNED_ON("性欲激起", 10, 25, PresetColour.AROUSAL_STAGE_ONE) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_1;
		}
	},

	TWO_EXCITED("非常性奋", 25, 50, PresetColour.AROUSAL_STAGE_TWO) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_2;
		}
	},

	THREE_HEATED("欲火焚身", 50, 80, PresetColour.AROUSAL_STAGE_THREE) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_3;
		}
	},

	FOUR_PASSIONATE("激情澎拜", 80, 95, PresetColour.AROUSAL_STAGE_FOUR) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_4;
		}
		@Override
		public boolean isMutualOrgasm() {
			return true;
		}
	},

	FIVE_ORGASM_IMMINENT("临近高潮", 95, 100, PresetColour.AROUSAL_STAGE_FIVE) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.AROUSAL_PERK_5;
		}
		@Override
		public boolean isMutualOrgasm() {
			return true;
		}
	};
	
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;

	private ArousalLevel(String name, int minimumValue, int maximumValue, Colour colour) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
	}
	
	public abstract AbstractStatusEffect getRelatedStatusEffect();

	public String getName() {
		return name;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}
	
	public int getMedianValue() {
		return (minimumValue + maximumValue) / 2;
	}

	public Colour getColour() {
		return colour;
	}

	public static ArousalLevel getArousalLevelFromValue(float value){
		for(ArousalLevel al : ArousalLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue()) {
				return al;
			}
		}
		return FIVE_ORGASM_IMMINENT;
	}

	public boolean isMutualOrgasm() {
		return false;
	}
}
