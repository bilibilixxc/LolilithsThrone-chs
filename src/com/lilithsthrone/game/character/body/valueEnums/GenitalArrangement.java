package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.84
 * @version 0.4
 * @author Innoxia
 */
public enum GenitalArrangement {
	
	NORMAL("普通", "[npc.NameHasFull]拥有外生殖器，并且与肛门分开。"),
	
	CLOACA("泄殖腔", "[npc.NamePos]的生殖器和肛门均为细缝状的泄殖腔，位于生殖器通常在的位置。"),
	
	CLOACA_BEHIND("后向泄殖腔", "[npc.NamePos]的生殖器和肛门均为细缝状的泄殖腔，位于肛门通常在的位置。");
	
	
	private String name;
	private String description;

	private GenitalArrangement(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
