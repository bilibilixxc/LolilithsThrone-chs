package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.settings.ContentPreferenceValue;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

import static com.lilithsthrone.game.character.GameCharacter.MAXIMUM_AGE;
import static com.lilithsthrone.game.character.GameCharacter.MINIMUM_AGE;
import static com.lilithsthrone.utils.Util.checkWeightedMap;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Innoxia
 */
public enum AgeCategory {
	
	// Always at least 18, as returned by valueOf()
	INFANT("婴幼儿", 0, 1, PresetColour.AGE_INFANT, ContentPreferenceValue.ZERO_NONE),
	TODDLER("学步期", 1, 4, PresetColour.AGE_CHILD, ContentPreferenceValue.ZERO_NONE),
	CHILD("童年期", 4, 8, PresetColour.AGE_CHILD, ContentPreferenceValue.ONE_MINIMAL),
	PRETEENS("青春期前", 8, 12, PresetColour.AGE_PRETEENS, ContentPreferenceValue.THREE_AVERAGE),
	TEENS_EARLY("十四五岁", 12, 14, PresetColour.AGE_PRETEENS, ContentPreferenceValue.FOUR_HIGH),
	TEENS_MIDDLE("十五六七岁", 14, 17, PresetColour.AGE_TEENS, ContentPreferenceValue.FOUR_HIGH),
	TEENS_LATE("十七至二十岁", 17, 20, PresetColour.AGE_LATE_TEENS, ContentPreferenceValue.THREE_AVERAGE),

	TWENTIES_EARLY("二十出头", 20, 23, PresetColour.AGE_TWENTIES, ContentPreferenceValue.FIVE_ABUNDANT),
	
	TWENTIES_MIDDLE("二十五六", 23, 27, PresetColour.AGE_TWENTIES, ContentPreferenceValue.FIVE_ABUNDANT),
	
	TWENTIES_LATE("不到三十", 27, 30, PresetColour.AGE_TWENTIES, ContentPreferenceValue.FOUR_HIGH),
	
	THIRTIES_EARLY("三十出头", 30, 33, PresetColour.AGE_THIRTIES, ContentPreferenceValue.THREE_AVERAGE),
	
	THIRTIES_MIDDLE("三十五六", 33, 37, PresetColour.AGE_THIRTIES, ContentPreferenceValue.THREE_AVERAGE),
	
	THIRTIES_LATE("不到四十", 37, 40, PresetColour.AGE_THIRTIES, ContentPreferenceValue.TWO_LOW),
	
	FORTIES_EARLY("四十出头", 40, 43, PresetColour.AGE_FORTIES, ContentPreferenceValue.TWO_LOW),
	
	FORTIES_MIDDLE("四十五六", 43, 47, PresetColour.AGE_FORTIES, ContentPreferenceValue.ONE_MINIMAL),
	
	FORTIES_LATE("不到五十", 47, 50, PresetColour.AGE_FORTIES, ContentPreferenceValue.ONE_MINIMAL),
	
	FIFTIES_EARLY("五十出头", 50, 53, PresetColour.AGE_FIFTIES, ContentPreferenceValue.ONE_MINIMAL),
	
	FIFTIES_MIDDLE("五十五六", 53, 57, PresetColour.AGE_FIFTIES, ContentPreferenceValue.ZERO_NONE),
	
	FIFTIES_LATE("不到六十", 57, 60, PresetColour.AGE_FIFTIES, ContentPreferenceValue.ZERO_NONE),
	
	SIXTIES_EARLY("六十出头", 60, 63, PresetColour.AGE_SIXTIES, ContentPreferenceValue.ZERO_NONE),
	
	SIXTIES_MIDDLE("六十五六", 63, 67, PresetColour.AGE_SIXTIES, ContentPreferenceValue.ZERO_NONE),
	
	SIXTIES_LATE("不到七十", 67, 70, PresetColour.AGE_SIXTIES, ContentPreferenceValue.ZERO_NONE),

	SIXTIES_PLUS("past seventy", 70, 900, PresetColour.AGE_HUNDREDS, ContentPreferenceValue.ZERO_NONE);

	private String name;
	private int minimumAge;
	private int maximumAge;
	private Colour colour;
	private ContentPreferenceValue agePreferenceDefault;

	AgeCategory(String name, int minimumAge, int maximumAge, Colour colour, ContentPreferenceValue agePreferenceDefault) {
		this.name = name;
		this.minimumAge = minimumAge;
		this.maximumAge = maximumAge;
		this.colour = colour;
		this.agePreferenceDefault = agePreferenceDefault;
	}

	public int getMinimumValue() {
		return Math.min(Math.max(MINIMUM_AGE, minimumAge), MAXIMUM_AGE);
	}

	public int getMaximumValue() {
		return Math.min(Math.max(MINIMUM_AGE, maximumAge), MAXIMUM_AGE);
	}
	
	public int getMedianValue() {
		return getMinimumValue() + (getMaximumValue() - getMinimumValue()) / 2;
	}

	public static AgeCategory valueOf(int age) {
		age = Math.min(Math.max(MINIMUM_AGE, age), MAXIMUM_AGE);

		if (age < TODDLER.getMinimumValue())
			return TODDLER;
		for (AgeCategory ageCategory : AgeCategory.values()) {
			if (ageCategory.getMinimumValue() == ageCategory.getMaximumValue()) continue;
			if (age >= ageCategory.getMinimumValue() && age < ageCategory.getMaximumValue())
				return ageCategory;
		}
		return SIXTIES_PLUS;
	}
	
	public String getName() {
		return name;
	}
	
	public Colour getColour() {
		return colour;
	}

	public ContentPreferenceValue getAgePreferenceDefault() {
		return agePreferenceDefault;
	}


	public static int getAgeFromPreferences(Gender gender, int fallBackAge) {
		if (gender == null) return fallBackAge;
		return getAgeFromPreferences(gender);
	}
	public static int getAgeFromPreferences(Gender gender) {
		AgeCategory category = getCategoryWithFailover(gender);
		if (category == null) {
			category = AgeCategory.TWENTIES_MIDDLE;
			System.err.println("No Category found for : " + gender + " Returning Failover Age Category!");
		}
		if (category.getMinimumValue() == category.getMaximumValue()) // This avoids 'java.lang.IllegalArgumentException: bound must be greater than origin'
			category = AgeCategory.valueOf(category.getMinimumValue() - 2);
		if (category.getMinimumValue() == category.getMaximumValue()) // This avoids 'java.lang.IllegalArgumentException: bound must be greater than origin'
			category = AgeCategory.valueOf(category.getMinimumValue() + 2);
		int age = Util.random.nextInt(category.getMinimumValue(), category.getMaximumValue());
		return age;
	}

	private static AgeCategory getCategoryWithFailover(Gender gender) {
		AgeCategory category = Util.getRandomObjectFromWeightedMap(Main.getProperties().agePreferencesMap.get(gender.getType()));

		if (category != null) return category;

		if (!checkWeightedMap(Main.getProperties().agePreferencesMap.get(gender.getType()), false)) {
			Gender failover = Gender.getRandomGenderFromUserPreferences();
			category = getCategoryWithFailover(failover);
			System.err.println("Failover to : " + failover.getName() + " Gender Age Category Preferences!");
		}
		return category;
	}
}