package com.lilithsthrone.world;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public enum Bearing {
	NORTH("北方"),
	NORTH_EAST("东北方"),
	EAST("东方"),
	SOUTH_EAST("东南方"),
	SOUTH("南方"),
	SOUTH_WEST("西南方"),
	WEST("西方"),
	NORTH_WEST("西北方"),
	RANDOM("随机方向");
	
	private String name;
	
	private Bearing(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	
}
