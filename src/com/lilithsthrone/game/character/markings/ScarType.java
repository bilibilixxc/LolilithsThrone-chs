package com.lilithsthrone.game.character.markings;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum ScarType {

	CLAW_MARKS("爪痕", "爪痕", true),
	
	BURNS("烫痕", "烫痕", false),
	
	STRAIGHT_SCAR("笔直疤痕", "笔直疤痕", false),
	
	JAGGED_SCAR("曲折疤痕", "曲折疤痕", false),

	BRUISE("淤青", "淤青", false);
	
	private String name;
	private String namePlural;
	private boolean alwaysPlural;
	
	private ScarType(String name, String namePlural, boolean alwaysPlural) {
		this.name = name;
		this.namePlural = namePlural;
		this.alwaysPlural = alwaysPlural;
	}

	public String getName() {
		return name;
	}

	public String getNamePlural() {
		return namePlural;
	}
	
	public boolean isAlwaysPlural() {
		return alwaysPlural;
	}
	
	public static ScarType getScarTypeFromString(String value) {
		if(value=="BRUIS") {
			return BRUISE;
		}
		return ScarType.valueOf(value);
	}
}
