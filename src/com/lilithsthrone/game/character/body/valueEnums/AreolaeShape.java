package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum AreolaeShape {

	NORMAL("普通"),
	HEART("心形"),
	STAR("星形");
	
	private String descriptor;

	private AreolaeShape(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
