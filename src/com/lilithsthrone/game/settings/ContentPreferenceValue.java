package com.lilithsthrone.game.settings;

/**
 * @since 0.1.78
 * @version 0.2.11
 * @author Innoxia
 */
public enum ContentPreferenceValue {

	ZERO_NONE("关闭", 0),
	
	ONE_MINIMAL("极少", 1),
	
	TWO_LOW("少量", 5),
	
	THREE_AVERAGE("平均", 10),
	
	FOUR_HIGH("大量", 20),
	
	FIVE_ABUNDANT("极多", 40);

	private String name;
	private int value;
	
	private ContentPreferenceValue(String name, int value) {
		this.name= name;
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
