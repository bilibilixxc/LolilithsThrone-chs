package com.lilithsthrone.game.character.body.valueEnums;

import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public enum CoveringPattern {
	NONE(false, "单色"),
	
	FLUID(false, "液态"),
	
	ORIFICE_ANUS(true, "肛门"),
	ORIFICE_VAGINA(true, "阴道"),
	ORIFICE_MOUTH(true, "嘴"),
	ORIFICE_SPINNERET(true, "丝囊"),
	ORIFICE_NIPPLE(true, "乳头") {
		@Override
		public boolean isNaturalSecondColour(GameCharacter owner) {
			return owner!=null && owner.getNippleCapacity()!=Capacity.ZERO_IMPENETRABLE;
		}
	},
	ORIFICE_NIPPLE_CROTCH(true, "乳头") {
		@Override
		public boolean isNaturalSecondColour(GameCharacter owner) {
			return  owner!=null && owner.getNippleCrotchCapacity()!=Capacity.ZERO_IMPENETRABLE;
		}
	},

	EYE_IRISES(false, "标准"),
	EYE_IRISES_HETEROCHROMATIC(true, "异色"),
	EYE_PUPILS(false, "标准"),
	EYE_PUPILS_HETEROCHROMATIC(true, "异色"),
	EYE_SCLERA(false, "标准"),
	EYE_SCLERA_HETEROCHROMATIC(true, "异色"),

	HIGHLIGHTS(true, "高光"),
	STRIPED(true, "条纹"),
	SPOTTED(true, "斑点"),
	MOTTLED(true, "斑驳"),
	MARKED(true, "花纹"),
	FRECKLED(true, "雀斑"),
	FRECKLED_FACE(false, "有雀斑的脸"),
	
	OMBRE(true, "混色");
	
	
	public static Map<CoveringPattern, Integer> allStandardCoveringPatterns = new HashMap<>();
	public static Map<CoveringPattern, Integer> allHairCoveringPatterns = new HashMap<>();
	public static Map<CoveringPattern, Integer> allScalesCoveringPatterns = new HashMap<>();
	public static Map<CoveringPattern, Integer> allSlimeCoveringPatterns = new HashMap<>();
	public static Map<CoveringPattern, Integer> allSiliconeCoveringPatterns = new HashMap<>();
	
	
	static {
		allStandardCoveringPatterns.put(NONE, 1);
		allStandardCoveringPatterns.put(HIGHLIGHTS, 1);
		allStandardCoveringPatterns.put(STRIPED, 1);
		allStandardCoveringPatterns.put(SPOTTED, 1);
		allStandardCoveringPatterns.put(MOTTLED, 1);
		allStandardCoveringPatterns.put(MARKED, 1);
		allStandardCoveringPatterns.put(FRECKLED, 1);
		allStandardCoveringPatterns.put(FRECKLED_FACE, 1);
		
		allHairCoveringPatterns.put(NONE, 1);
		allHairCoveringPatterns.put(HIGHLIGHTS, 1);
		allHairCoveringPatterns.put(STRIPED, 1);
		allHairCoveringPatterns.put(SPOTTED, 1);
		allHairCoveringPatterns.put(MOTTLED, 1);
		allHairCoveringPatterns.put(MARKED, 1);
		allHairCoveringPatterns.put(OMBRE, 1);

		allScalesCoveringPatterns.put(NONE, 1);
		allScalesCoveringPatterns.put(HIGHLIGHTS, 1);
		allScalesCoveringPatterns.put(STRIPED, 1);
		allScalesCoveringPatterns.put(SPOTTED, 1);
		allScalesCoveringPatterns.put(MOTTLED, 1);
		allScalesCoveringPatterns.put(MARKED, 1);
		
		allSlimeCoveringPatterns.put(NONE, 1);
		allSlimeCoveringPatterns.put(HIGHLIGHTS, 1);
		allSlimeCoveringPatterns.put(STRIPED, 1);
		allSlimeCoveringPatterns.put(SPOTTED, 1);
		allSlimeCoveringPatterns.put(MOTTLED, 1);
		allSlimeCoveringPatterns.put(MARKED, 1);
		allSlimeCoveringPatterns.put(FRECKLED, 1);
		allSlimeCoveringPatterns.put(FRECKLED_FACE, 1);
		allSlimeCoveringPatterns.put(OMBRE, 1);

		allSiliconeCoveringPatterns.put(NONE, 1);
		allSiliconeCoveringPatterns.put(HIGHLIGHTS, 1);
		allSiliconeCoveringPatterns.put(STRIPED, 1);
		allSiliconeCoveringPatterns.put(SPOTTED, 1);
		allSiliconeCoveringPatterns.put(MOTTLED, 1);
		allSiliconeCoveringPatterns.put(MARKED, 1);
		allSiliconeCoveringPatterns.put(FRECKLED, 1);
		allSiliconeCoveringPatterns.put(FRECKLED_FACE, 1);
		allSiliconeCoveringPatterns.put(OMBRE, 1);
	}
	
	
	private String name;
	private boolean naturalSecondColour;
	
	private CoveringPattern(boolean naturalSecondColour, String name) {
		this.naturalSecondColour = naturalSecondColour;
		this.name = name;
	}

	/**
	 * @return true if this CoveringPattern has a secondary colour by default.
	 */
	public boolean isNaturalSecondColour(GameCharacter owner) {
		return naturalSecondColour;
	}

	public String getName() {
		return name;
	}
}
