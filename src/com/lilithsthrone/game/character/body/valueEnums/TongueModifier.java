package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.4
 * @author Innoxia
 */
public enum TongueModifier {
	
	RIBBED("螺纹"),
	
	TENTACLED("长有触手"),
	
	BIFURCATED("分叉"),
	
	WIDE("宽大"),
	
	FLAT("平直"),
	
	STRONG("有力"),
	
	TAPERED("锥形");
	
	private String descriptor;

	private TongueModifier(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
