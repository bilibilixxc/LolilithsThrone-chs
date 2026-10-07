package com.lilithsthrone.game.character.markings;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum TattooWritingStyle {

	ITALICISED("斜体"),
	
	BOLD("粗体");
	
	private String name;

	private TattooWritingStyle(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
