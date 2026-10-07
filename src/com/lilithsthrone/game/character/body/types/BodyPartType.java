package com.lilithsthrone.game.character.body.types;

/**
 * @since 0.1.69.9
 * @version 0.4
 * @author Innoxia
 */
public enum BodyPartType {
	
	GENERIC("一般"),
	
	// Limbs/body:
	ARM("手臂"),
	LEG("腿"),
	SKIN("皮肤"),
	TAIL("尾巴"),
	TENTACLE("触手"),
	WING("翅膀"),
	
	// Ass:
	ASS("屁股"),
	ANUS("肛门"),

	// Breasts:
	BREAST("乳房"),
	NIPPLES("乳头"),
	MILK("乳汁"),
	
	// Crotch-boobs:
	BREAST_CROTCH("胯乳"),
	NIPPLES_CROTCH("胯乳乳头"),
	MILK_CROTCH("胯乳乳汁"),
	
	// Head:
	ANTENNA("触须"),
	EAR("耳朵"),
	EYE("眼睛"),
	FACE("面部"),
	MOUTH("嘴巴"),
	TONGUE("舌头"),
	HAIR("毛发"),
	HORN("角"),
	
	// Penis:
	PENIS("阴茎"),
	TESTICLES("阴囊"),
	CUM("精液"),
	
	// Vagina:
	CLIT("阴蒂"),
	VAGINA("阴道"),
	GIRL_CUM("爱液"),
	
	// Spinneret:

	SPINNERET("丝囊");
	
	private String name;
	private BodyPartType(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
}
