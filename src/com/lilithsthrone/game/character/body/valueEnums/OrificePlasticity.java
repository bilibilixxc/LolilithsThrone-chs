package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.83
 * @version 0.3.1
 * @author Innoxia
 */
public enum OrificePlasticity {

	/*-------MOULDABLE:---------*/

	/**Instantly returns to starting value after sex.*/
	ZERO_RUBBERY("原状难改",
			"[style.colourPlasticity(几乎立刻恢复到原来的容量)]",
			"[style.colourPlasticity(立刻恢复到原来的容量)]",
			0, 0, 100, PresetColour.GENERIC_SIZE_ONE),

	/**Quickly returns to starting value after sex, at a rate of 2 cm per 30 minutes.*/
	ONE_SPRINGY("恢复迅速",
			"[style.colourPlasticity(非常快速地恢复到原来的容量)]",
			"[style.colourPlasticity(非常快速地恢复到原来的容量)]",
			1, 0, 2/(60*30f), PresetColour.GENERIC_SIZE_TWO),

	/**Returns to starting value after sex, at a rate of 2 cm per hour.*/
	TWO_TENSILE("柔韧",
			"[style.colourPlasticity(以常规速度恢复到原来的容量)]",
			"[style.colourPlasticity(以常规速度恢复到原来的容量)]",
			2, 0, 2/(60*60f), PresetColour.GENERIC_SIZE_THREE),

	/*-------NORMAL:---------*/

	/**Normal value, along with FOUR_ACCOMMODATING. Will slowly return to starting value after sex, at a rate of 2 cm per 3 hours.*/
	THREE_RESILIENT("柔软",
			"[style.colourPlasticity(缓慢地恢复到原来的容量)]",
			"[style.colourPlasticity(缓慢地恢复到原来的容量)]",
			3, 0, 2/(60*60f*4), PresetColour.GENERIC_SIZE_FOUR),

	/**Normal value, along with THREE_RESILIENT. Will recover about 98% of its original size after sex, at a rate of 2 cm per 12 hours.*/
	FOUR_ACCOMMODATING("绵软",
			"[style.colourPlasticity(非常缓慢地恢复[style.italicsMinorBad(原来容量的绝大部分，但并非全部)])]",
			"[style.colourPlasticity(非常缓慢地恢复[style.italicsMinorBad(原来容量的绝大部分，但并非全部)])]",
			4, 0.2f, 2/(60*60f*12), PresetColour.GENERIC_SIZE_FIVE),

	/*-------STRETCHY:---------*/

	/**Will recover about 80% of its original size after sex, at a rate of 2 cm per 24 hours.*/
	FIVE_YIELDING("松软",
			"[style.colourPlasticity(非常缓慢地恢复[style.italicsMinorBad(原来容量的大部分，但并非全部)])]",
			"[style.colourPlasticity(非常缓慢地恢复[style.italicsMinorBad(原来容量的大部分，但并非全部)])]",
			5, 0.2f, 2/(60*60f*24), PresetColour.GENERIC_SIZE_SIX),

	/**Will only recover about 40% of its original size after sex, at a rate of 2 cm per 24 hours.*/
	SIX_MALLEABLE("延展自如",
			"[style.colourPlasticity(非常缓慢地恢复[style.italicsMinorBad(原来容量的部分，但并非全部)])]",
			"[style.colourPlasticity(非常缓慢地恢复[style.italicsMinorBad(原来容量的部分，但并非全部)])]",
			6, 0.6f, 2/(60*60f*24), PresetColour.GENERIC_SIZE_SEVEN),

	/**Will recover none of its original size after sex.*/
	SEVEN_MOULDABLE("随意塑形",
			"保持[style.italicsTerrible(永远扩张)]的状态，[style.colourPlasticity(完全不会恢复至原来的容量)]",
			"保持[style.italicsTerrible(永远扩张)]的状态，[style.colourPlasticity(完全不会恢复至原来的容量)]",
			7, 1, 2/(60*60f*24), PresetColour.GENERIC_SIZE_EIGHT);

	
	private String descriptor;
	private String description;
	private String descriptionPlural;
	private int value;
	private float capacityIncreaseModifier;
	private float recoveryModifier;
	private Colour colour;

	private OrificePlasticity(String descriptor, String description, String descriptionPlural, int value, float capacityIncreaseModifier, float recoveryModifier, Colour colour) {
		this.descriptor = descriptor;
		this.description = description;
		this.descriptionPlural = descriptionPlural;
		this.value = value;
		this.capacityIncreaseModifier = capacityIncreaseModifier;
		this.recoveryModifier = recoveryModifier;
		this.colour = colour;
	}

	public static OrificePlasticity getElasticityFromInt(int value) {
		for(OrificePlasticity oe : OrificePlasticity.values()) {
			if(value == oe.getValue()) {
				return oe;
			}
		}
		return SEVEN_MOULDABLE;
	}

	/**
	 * To fit into a sentence: "You have a "+getDescriptor()+" ass."
	 */
	public String getDescriptor() {
		return descriptor;
	}
	public String getDescription() {
		return description;
	}
	public String getDescriptionPlural() {
		return descriptionPlural;
	}

	public int getValue() {
		return value;
	}

	public float getRecoveryModifier() {
		return recoveryModifier;
	}
	
	public float getCapacityIncreaseModifier() {
		return capacityIncreaseModifier;
	}

	public Colour getColour() {
		return colour;
	}
}
