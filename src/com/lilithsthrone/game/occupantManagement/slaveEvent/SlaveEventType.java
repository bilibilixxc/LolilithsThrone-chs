package com.lilithsthrone.game.occupantManagement.slaveEvent;

/**
 * @since 0.3.9.2
 * @version 0.3.9.2
 * @author Innoxia
 */
public enum SlaveEventType {
	
	BONDING("羁绊", "两名奴隶花了一些时间相互了解或者相互争执的事件。"),
	
	JOB("工作", "与奴隶工作有关的时间。工作内容包括性爱时也显示性爱事件。"),
	
	SEX("性爱", "奴隶进行与性有关或性行为导致的动作(如分娩)的事件。"),
	
	MISCELLANEOUS("杂项", "琐碎的杂项事件，例如奴隶清洗自己身体等。");
	
	private String name;
	private String description;
	
	private SlaveEventType(String name, String description) {
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
