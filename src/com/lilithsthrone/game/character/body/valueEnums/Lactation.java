package com.lilithsthrone.game.character.body.valueEnums;

/**
 * Measured in millilitres.
 * 
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public enum Lactation {
	
	ZERO_NONE("无", "没有", 0, 1, Wetness.ZERO_DRY),
	/** This, and all before, require actual milking to produce milk. */
	ONE_TRICKLE("微量", "微量", 1, 30, Wetness.ONE_SLIGHTLY_MOIST),
	/** This, and all before, require actual milking to produce milk. */
	TWO_SMALL_AMOUNT("少量", "少量", 30, 100, Wetness.TWO_MOIST),
	/** This, and all before, require actual milking to produce milk. */
	THREE_DECENT_AMOUNT("适量", "适量", 100, 600, Wetness.THREE_WET),
	/** This, and all before, require actual milking to produce milk. */
	FOUR_LARGE_AMOUNT("大量", "大量", 600, 1000, Wetness.FOUR_SLIMY),
	/** They start drooling at the slightest touch. */
	FIVE_VERY_LARGE_DROOLING("巨量", "巨量", 1000, 2000, Wetness.FIVE_SLOPPY),
	/** They start dripping at the slightest touch. */
	SIX_EXTREME_AMOUNT_DRIPPING("极巨量", "极巨量", 2000, 10000, Wetness.SIX_SOPPING_WET),
	/** They start pouring at the slightest touch. */
	SEVEN_MONSTROUS_AMOUNT_POURING("恐怖量", "恐怖量", 10000, 100000, Wetness.SEVEN_DROOLING);

	private String name;
	private String descriptor;
	private int minimumValue;
	private int maximumValue;
	private Wetness associatedWetness;

	private Lactation(String name, String descriptor, int minimumValue, int maximumValue, Wetness associatedWetness) {
		this.name = name;
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.associatedWetness = associatedWetness;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * To fit into a sentence:<br/>
	 *  "Your breasts are producing "+getDescriptor()+" "+milkName+"."<br/>
	 *  "They are capable of producing "getDescriptor()+" "+milkName+", averaging at about "+breasts.getMilkProduction()+"mL each time they are milked."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public int getMedianValue() {
		return minimumValue + (maximumValue - minimumValue) / 2;
	}

	public static Lactation getLactationFromInt(int milkProduction) {
		for(Lactation l : Lactation.values()) {
			if(milkProduction>=l.getMinimumValue() && milkProduction<l.getMaximumValue()) {
				return l;
			}
		}
		return SEVEN_MONSTROUS_AMOUNT_POURING;
	}

	public Wetness getAssociatedWetness() {
		return associatedWetness;
	}
}
