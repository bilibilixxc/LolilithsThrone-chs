package com.lilithsthrone.game.character.race;

import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.78
 * @version 0.1.99
 * @author Innoxia
 */
public enum FurryPreference {

	/**No furry parts at all. (NPCs will spawn as regular humans.)*/
	HUMAN("禁用", PresetColour.TRANSFORMATION_HUMAN, "X") {
		@Override
		public String getDescriptionFeminine(AbstractSubspecies subspecies) {
			return "在随机遭遇中，完全禁用“女性”"+subspecies.getNamePlural(null)+"。如果所有“女性”偏好都设置为“禁用”，随机遭遇将默认为“女性人类”。";
		}
		@Override
		public String getDescriptionMasculine(AbstractSubspecies subspecies) {
			return "在随机遭遇中，完全禁用“男性”"+subspecies.getNamePlural(null)+"。如果所有“男性”偏好都设置为“禁用”，随机遭遇将默认为“男性人类”。";
		}
		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleZeroDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleZero();
		}
	},
	
	/**NPCs will spawn with furry ears and eyes, and where applicable, furry tails, horns, antenna, and wings. They will <b>not</b> spawn with furry breasts or genitalia.*/
	MINIMUM("最低", PresetColour.TRANSFORMATION_PARTIAL, "--") {
		@Override
		public String getDescriptionFeminine(AbstractSubspecies subspecies) {
			return "拥有毛茸茸的耳朵和大眼睛的“女性”"+subspecies.getNamePlural(null)+"将出现，而且在适用的情况下，还会有毛茸茸的尾巴、角、触角和翅膀。它们<b>不会</b>带有毛茸茸的乳房或生殖器。";
		}

		@Override
		public String getDescriptionMasculine(AbstractSubspecies subspecies) {
			return "拥有毛茸茸的耳朵和眼睛的“男性”"+subspecies.getNamePlural(null)+"将出现，而且在适用的情况下，还会有毛茸茸的尾巴、角、触角和翅膀。它们<b>不会</b>带有毛茸茸的乳房或生殖器。";
		}

		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleOneDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleOne();
		}
	},
	
	/**NPCs will spawn with all of the furry parts that the 'Minimum' setting enables (ears, eyes, tails, horns, antenna, and wings). They also have the chance to spawn with furry breasts, genitalia, arms, and legs.*/
	REDUCED("较低", PresetColour.TRANSFORMATION_PARTIAL_FULL, "-") {
		@Override
		public String getDescriptionFeminine(AbstractSubspecies subspecies) {
			return "“女性”"+subspecies.getNamePlural(null)+"将会生成所有由“最低”设置启用的福瑞部分(耳朵、眼睛、尾巴、角、触角和翅膀)"
						+ "他们还可能会生成毛茸茸的乳房、生殖器、手臂和腿。";
		}

		@Override
		public String getDescriptionMasculine(AbstractSubspecies subspecies) {
			return "“男性”"+subspecies.getNamePlural(null)+"将会生成所有由“最低”设置启用的福瑞部分(耳朵、眼睛、尾巴、角、触角和翅膀)"
					+ "他们还可能会生成毛茸茸的乳房、生殖器、手臂和腿。";
		}

		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleTwoDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleTwo();
		}
	},
	
	/**NPCs will spawn with all of the furry parts that the 'Minimum' setting enables (ears, eyes, tails, horns, antenna, and wings). They also have the chance to spawn with furry breasts, genitalia, arms, legs, skin/fur, and faces.*/
	NORMAL("较高", PresetColour.TRANSFORMATION_LESSER, "+") {
		@Override
		public String getDescriptionFeminine(AbstractSubspecies subspecies) {
			return "“女性”"+subspecies.getNamePlural(null)+"将会生成所有由“最低”设置启用的福瑞部分(耳朵、眼睛、尾巴、角、触角和翅膀)"
					+ "他们还有机会生成毛茸茸的乳房、生殖器、手臂、腿、皮肤/毛发和面部。";
		}

		@Override
		public String getDescriptionMasculine(AbstractSubspecies subspecies) {
			return "“男性”"+subspecies.getNamePlural(null)+"将会生成所有由“最低”设置启用的福瑞部分(耳朵、眼睛、尾巴、角、触角和翅膀)"
					+ "他们还有机会生成毛茸茸的乳房、生殖器、手臂、腿、皮肤/毛发和面部。";
		}

		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleThreeDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleThree();
		}
	},
	
	/**NPCs will <b>always</b> spawn with as many furry parts as is possible (ears, eyes, tails, horns, antenna, wings, breasts, genitalia, arms, legs, skin/fur, and face).*/
	MAXIMUM("完全", PresetColour.TRANSFORMATION_GREATER, "++") {
		@Override
		public String getDescriptionFeminine(AbstractSubspecies subspecies) {
			return "“女性”"+subspecies.getNamePlural(null)+"将<b>始终</b>生成尽可能多的福瑞部分(耳朵、眼睛、尾巴、角、触角、翅膀、乳房、生殖器、手臂、腿、皮肤/毛发和面部)";
		}

		@Override
		public String getDescriptionMasculine(AbstractSubspecies subspecies) {
			return "“男性”"+subspecies.getNamePlural(null)+"将<b>始终</b>生成尽可能多的福瑞部分(耳朵、眼睛、尾巴、角、触角、翅膀、乳房、生殖器、手臂、腿、皮肤/毛发和面部)";
		}

		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleFourDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleFour();
		}
	};
	
	private String name;
	private Colour colour;
	private String buttonText;
	
	private FurryPreference(String name, Colour colour, String buttonText) {
		this.name = name;
		this.colour = colour;
		this.buttonText = buttonText;
	}

	public abstract String getSVGImage(boolean disabled);
	
	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}
	
	public String getButtonText() {
		return buttonText;
	}

	public abstract String getDescriptionFeminine(AbstractSubspecies subspecies);
	public abstract String getDescriptionMasculine(AbstractSubspecies subspecies);
}
