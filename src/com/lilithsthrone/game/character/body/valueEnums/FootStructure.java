package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.2.8
 * @version 0.4
 * @author Innoxia
 */
public enum FootStructure {

	/**no feet*/
	NONE("无", "[npc.She]没有脚。"),
	
	/**walk with feet flat on the ground*/
	PLANTIGRADE("跖行", "[npc.She]使用脚掌在陆地上行走。"),

	/**walk on toes with the heel permanently raised*/
	DIGITIGRADE("趾行", "[npc.She]使用[npc.toes]行走，脚跟总是高高抬起。"),

	/**walk on hoof with the rest of the foot permanently raised*/
	UNGULIGRADE("蹄行", "[npc.She]使用[npc.toes]行走，而脚的其他部分则总是高高抬起。"),

	/**have segmented legs like a spider, so foot is the 'tarsus' segment.*/
	ARACHNOID("蛛形", "[npc.She]使用蛛化的节肢末端行走。"),
	
	/**use tentacle-legs to walk around on*/
	TENTACLED("触手", "[npc.She]使用触手的下半段行走。");
	
	private String name;
	private String description;

	private FootStructure(String name, String description) {
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
