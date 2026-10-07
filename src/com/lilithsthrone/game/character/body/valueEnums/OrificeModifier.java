package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.3.7.2
 * @author Innoxia
 */
public enum OrificeModifier {
	
	PUFFY("肉感", ""),
	
	RIBBED("内部螺纹", ""),
	
	TENTACLED("长有触手", ""),
	
	MUSCLE_CONTROL("肌肉发达", "该腔穴永远不会被认为“过于松垮”，无论插入物的直径和腔穴的容量。");
	
	
	private String name;
	private String description;

	private OrificeModifier(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public boolean isSpecialEffects() {
		return !description.isEmpty();
	}
	
	public String getDescription() {
		if(description.isEmpty()) {
			return "没有游戏内实际效果。";
		}
		return description;
	}
}
