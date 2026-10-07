package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.99
 * @version 0.4
 * @author Innoxia
 */
public enum CoveringModifier {

	EYE("眼睛", false),
	FLUID("液体", false),
	MAKEUP("妆容", false),
	GLOSSY("光亮", false),
	MATTE("哑光", false),
	SPARKLY("闪闪发光", false),
	METALLIC("金属光泽", false),

	BLAZING("耀眼", false),
	SHIMMERING("闪烁", false),
	GLITTERING("晶莹剔透", false),
	SWIRLING("旋动", false),
	
	GOOEY("软黏", false) {
		@Override
		public String getName() {
			return UtilText.returnStringAtRandom(
					"软黏",
					"潮湿",
					"黏糊");
		}
	},
	
	// Generic:
	SMOOTH("光滑", false),
	ROUGH("粗糙", false),
	
	//Skin:
	LEATHERY("革质", false),
	
	// Fur/hair:
	SHORT("较短", true),
	SILKEN("柔顺", true),
	FLUFFY("松软", true),
	SHAGGY("蓬乱", true),
	FURRY("皮毛般", true), // FURRY is only used for head hair, not body-covering fur.
	COARSE("凌乱", true);
	
	private String descriptor;
	private boolean furryModifier;

	private CoveringModifier(String descriptor, boolean furryModifier) {
		this.descriptor = descriptor;
		this.furryModifier = furryModifier;
	}

	public String getName() {
		return descriptor;
	}
	
	/**
	 * @return true if this is a modifier which is typically assigned to furry or hairy coverings.
	 */
	public boolean isFurryModifier() {
		return furryModifier;
	}
}
