package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.97
 * @version 0.4.1
 * @author Innoxia
 */
public enum OrgasmCumTarget {

	// Specials:
	LILAYA_PANTIES("瞄准莉莱雅的内裤", "panties", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
	},
	
	WALL("墙上", "wall", false) {
		@Override
		public String getName() {
			return UtilText.parse("[pc.wall]上");
		}

		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
	},
	FLOOR("地上", "地面", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
	},
	
	INSIDE("体内", "体内", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
	},
	INSIDE_SWITCH_DOUBLE("体内(双穴)", "体内", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
	},
	
	ARMPITS("腋窝上", "腋窝", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.ARMPITS;
		}
	},
	ASS("屁股上", "屁股", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.ASS;
		}
	},
	GROIN("股间", "股间", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			if(owner.hasVagina()) {
				return CoverableArea.VAGINA;
			} else if(owner.hasPenis()) {
				return CoverableArea.PENIS;
			}
			return CoverableArea.MOUND;
		}
	},
	BREASTS("胸上", "胸部", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.BREASTS;
		}
	},
	FACE("脸上", "面部", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.MOUTH;
		}
	},
	HAIR("头发上", "头发", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.HAIR;
		}
	},
	STOMACH("肚子上", "肚子", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.STOMACH;
		}
	},
	LEGS("腿上", "腿部", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.LEGS;
		}
	},
	FEET("脚上", "脚", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.FEET;
		}
	},
	BACK("背上", "背部", true) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.BACK;
		}
	},
	
	SELF_GROIN("在自己胯下", "自己的胯下", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			if(owner.hasVagina()) {
				return CoverableArea.VAGINA;
			} else if(owner.hasPenis()) {
				return CoverableArea.PENIS;
			}
			return CoverableArea.MOUND;
		}
	},
	SELF_STOMACH("在自己的肚子上", "自己的肚子", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.STOMACH;
		}
	},
	SELF_LEGS("在自己的腿上", "自己的腿", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.LEGS;
		}
	},
	SELF_FEET("在自己的脚上", "自己的脚", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.FEET;
		}
	},
	SELF_BREASTS("在自己的胸上", "自己的胸", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.BREASTS;
		}
	},
	SELF_HANDS("在自己的手上", "自己的手", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.HANDS;
		}
	},
	SELF_FACE("在自己的脸上", "自己的脸", false) {
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.MOUTH;
		}
	};
	
	private String name;
	private String simpleName;
	private boolean requiresPartner;

	private OrgasmCumTarget(String name, String simpleName, boolean requiresPartner) {
		this.name = name;
		this.simpleName = simpleName;
		this.requiresPartner = requiresPartner;
	}

	/**
	 * @return A name that's suitable for a brief action description, e.g. 'onto floor'
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return A one-word name that's suitable for use in scenes, e.g. 'floor'
	 */
	public String getSimpleName() {
		return simpleName;
	}
	
	public boolean isRequiresPartner() {
		return requiresPartner;
	}
	
	public abstract CoverableArea getRelatedCoverableArea(GameCharacter owner);
}
