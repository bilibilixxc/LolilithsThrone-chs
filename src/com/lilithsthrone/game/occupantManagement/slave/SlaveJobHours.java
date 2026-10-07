package com.lilithsthrone.game.occupantManagement.slave;

/**
 * @since 0.1.87
 * @version 0.1.87
 * @author Innoxia
 */
public enum SlaveJobHours {

	NONE("无", "不给角色分配时间。", 0, 0),
	
	DAY_NORMAL("白班", "让该角色在白天工作八小时。", 9, 8),
	DAY_LONG("白班+", "让该角色在白天工作十六小时。", 6, 16),
	
	NIGHT_NORMAL("夜班", "让该角色在晚上工作八小时。", 20, 8),
	NIGHT_LONG("夜班+", "让该角色在晚上工作十六小时。", 16, 16),

	TWENTY_FOUR_HOURS("全天", "让该角色全天无休工作。", 0, 24);
	

	private String name;
	private String description;
	private int startHour;
	private int length;
	
	private SlaveJobHours(String name, String description, int startHour, int length) {
		this.name = name;
		this.description = description;
		this.startHour = startHour;
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getStartHour() {
		return startHour;
	}

	public int getLength() {
		return length;
	}
}
