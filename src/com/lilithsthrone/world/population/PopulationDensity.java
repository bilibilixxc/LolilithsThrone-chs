package com.lilithsthrone.world.population;

/**
 * @since 0.2.12
 * @version 0.4.4.5
 * @author Innoxia
 */
public enum PopulationDensity {

	ONE("一位"),
	
	OCCASIONAL("偶尔有一个"),
	
	COUPLE("几个"),
	
	FEW("没几个"),
	
	TRIO("三个"),
	
	SPARSE("稀少的"),
	
	SEVERAL("一些"),
	
	HALF_DOZEN("六个"),
	
	DOZEN("一打"),

	DOZENS("几十个"),
	
	MANY("许多"),
	
	NUMEROUS("大量"),
	
	DENSE("密集的"),
	
	SMALL("少批"),
	
	HUNDREDS("成百"),
	
	THOUSANDS("上千");

	String name;
	
	private PopulationDensity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
