package com.lilithsthrone.game.inventory.clothing;

/**
 * @since 0.1.0
 * @version 0.1.96
 * @author Innoxia
 */
public enum DisplacementType {
	
	REMOVE_OR_EQUIP("卸下", "", "", "", ""),
	
	OPEN("敞开", "敞开", "敞开", "合上", "合上"),
	
	PULLS_UP("拉起", "拉起", "拉起", "放下", "放下"),
	
	PULLS_DOWN("拉下", "拉下", "拉下","提起", "提起"),
	
	PULLS_OUT("拔出", "拔出", "拔出", "塞入", "塞入"),
	
	PULLS_OFF("摘掉", "摘掉", "摘掉", "重新戴上", "重新戴上"),
	
	SHIFTS_ASIDE("挪开", "挪开", "挪开", "复位", "复位"),
	
	UNZIPS("解开", "解开", "解开", "拉上", "拉上"),
	
	UNTIE("解开", "解开", "解开", "系上", "系上"),
	
	UNBUCKLE("解开扣带", "解开扣带", "解开扣带", "扣好扣带", "扣好扣带"),
	
	UNBUTTONS("解开", "解开", "解开", "系上", "系上");

	private String description, descriptionThirdPerson, descriptionPast, oppositeDescription, oppositeDescriptionThirdPerson;

	private DisplacementType(String description, String descriptionThirdPerson, String descriptionPast, String oppositeDescription, String oppositeDescriptionThirdPerson) {
		this.description = description;
		this.descriptionThirdPerson = descriptionThirdPerson;
		this.descriptionPast = descriptionPast;
		this.oppositeDescription = oppositeDescription;
		this.oppositeDescriptionThirdPerson = oppositeDescriptionThirdPerson;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptionThirdPerson() {
		return descriptionThirdPerson;
	}

	public String getDescriptionPast() {
		return descriptionPast;
	}

	public String getOppositeDescription() {
		return oppositeDescription;
	}

	public String getOppositeDescriptionThirdPerson() {
		return oppositeDescriptionThirdPerson;
	}
}
