package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum EyeShape {

	ROUND("圆形"),
	HORIZONTAL("横向"),
	VERTICAL("纵向"),
	HEART("心形"),
	STAR("星形");
	
	private String descriptor;

	private EyeShape(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
