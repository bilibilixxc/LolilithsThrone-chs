package com.lilithsthrone.game.character.body.coverings;

/**
 * @since 0.1.0
 * @version 0.4.0
 * @author Innoxia
 */
public enum BodyCoveringCategory {

	// Main covering types
	MAIN_SKIN("皮肤"),
	MAIN_HAIR("毛发"),
	MAIN_FUR("皮毛"),
	MAIN_SCALES("鳞片"),
	MAIN_FEATHER("羽毛"),
	MAIN_CHITIN("几丁质外壳"),
	
	// Eyes
	EYE_IRIS("虹膜"),
	EYE_PUPIL("瞳孔"),
	EYE_SCLERA("巩膜"),

	// Head
	ANTENNAE("触须"),
	HORN("角"),
	ANTLER("鹿角"),
	HAIR("头发"),

	// Orifices
	ANUS("肛门"),
	MOUTH("嘴巴"),
	TONGUE("舌头"),
	NIPPLE("乳头"),
	NIPPLE_CROTCH("胯乳乳头"),
	VAGINA("阴道"),
	PENIS("阴茎"),
	SPINNERET("丝囊"),

	// Other
	BODY_HAIR("体毛"),
	
	// Specials
	ARTIFICIAL("假屌") { public boolean isInfluencedByMaterialType() { return false; } },
	FLUID("液体") { public boolean isInfluencedByMaterialType() { return false; } },
	MAKEUP("妆容") { public boolean isInfluencedByMaterialType() { return false; } };
	
	private String name;

	private BodyCoveringCategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * @return true if this BodyCoveringCategory changes based on the material which the character's body is made out of.
	 */
	public boolean isInfluencedByMaterialType() {
		return true;
	}
}
