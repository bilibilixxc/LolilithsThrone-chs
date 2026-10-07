package com.lilithsthrone.game.character.persona;

/**
 * @since 0.2.4
 * @version 0.2.4
 * @author Irbynx
 */
public enum MoralityValue {// Excuse me? This is a thing? Why no Incest, Underage, etc..? Can we utilize this @Drake ?
	
	SURRENDER("投降", "避免事情陷入糟糕结局的态度。"),
	
	VIOLENCE("暴力", "滥用暴力的态度。"),
	
	DISHONESTY("欺诈", "在提供的信息上欺骗、半真半假、语焉不详的态度。"),
	
	SLAVERY("奴役", "奴役他人的态度。");
	
	private String name;
	private String description;
	
	private MoralityValue(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
}
