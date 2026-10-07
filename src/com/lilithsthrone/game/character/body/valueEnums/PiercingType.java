package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.84
 * @version 0.3.7
 * @author Innoxia
 */
public enum PiercingType {
	
	EAR("耳朵",
			"耳朵是最常见的穿孔部位，打耳洞以佩戴耳环和其他耳朵相关的首饰。"),
	
	NOSE("鼻子",
			"给鼻子穿孔以佩戴鼻环或鼻钉。"),
	
	LIP("嘴唇",
			"给嘴唇穿孔以佩戴唇环。"),
	
	TONGUE("舌头",
			"给舌头穿孔以佩戴舌钉。"),
	
	NAVEL("肚脐",
			"给肚脐穿孔以佩戴肚脐相关的首饰。"),
	
	NIPPLE("乳头",
			"给乳头穿孔以佩戴乳环。"),
	
	VAGINA("阴部",
			"给阴蒂穿孔以佩戴阴部相关的首饰。"),
	
	PENIS("阴茎",
			"给阴茎穿孔以佩戴阴茎相关的首饰。");
	
	private String name;
	private String description;

	private PiercingType(String name, String description) {
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
