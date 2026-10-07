package com.lilithsthrone.game.dialogue;

/**
 * @since 0.1.0
 * @version 0.3.7.3
 * @author Innoxia
 */
public enum DialogueNodeType {
	
	NORMAL("普通"),
	
	STATUS_EFFECT_MESSAGE("状态效果信息"),
	
	INVENTORY("物品栏"),
	
	PHONE("手机"),
	
	CHARACTERS_PRESENT("在场角色"),

	OCCUPANT_MANAGEMENT("角色管理"),
	
	OPTIONS("选项"),
	
	GIFT("送礼");

	private String name;

	private DialogueNodeType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
