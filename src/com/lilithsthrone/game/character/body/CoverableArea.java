package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.LolificationProject.ConfigStuff.Configurations;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public enum CoverableArea {
	// keldon move this
	TUSSY(false,
			"tussy",
			Util.newArrayListOfValues(
					InventorySlot.TAIL)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasTussy();
		}
	},
	
	// Utility value
	NONE(false,
			"无",
			new ArrayList<>()),

	HANDS(false,
			"手",
			Util.newArrayListOfValues(
					InventorySlot.FINGER,
					InventorySlot.HAND,
					InventorySlot.WRIST)),
	
	ASS(true,
			"屁股",
			Util.newArrayListOfValues(
					InventorySlot.GROIN,
					InventorySlot.ANUS,
					InventorySlot.LEG,
					InventorySlot.TAIL)),
	
	ANUS(true,
			"肛门",
			Util.newArrayListOfValues(
					InventorySlot.GROIN,
					InventorySlot.ANUS)),

	STOMACH(false,
			"腹",
			Util.newArrayListOfValues(
					InventorySlot.STOMACH,
					InventorySlot.TORSO_UNDER,
					InventorySlot.TORSO_OVER)),
	
	BACK(false,
			"背部",
			Util.newArrayListOfValues(
					InventorySlot.TORSO_UNDER,
					InventorySlot.TORSO_OVER,
					InventorySlot.WINGS,
					InventorySlot.TAIL)),
	
	LEGS(false,
			"腿",
			Util.newArrayListOfValues(
					InventorySlot.LEG,
					InventorySlot.SOCK)) {
//		public boolean isPhysicallyAvailable(GameCharacter owner) {
//			return owner.hasLegs();
//		}
	},
	
	FEET(false,
			"足",
			Util.newArrayListOfValues(
					InventorySlot.FOOT,
					InventorySlot.ANKLE,
					InventorySlot.SOCK)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasLegs();
		}
	},
	
	THIGHS(false,
			"臀部",
			Util.newArrayListOfValues(
					InventorySlot.LEG,
					InventorySlot.GROIN)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasLegs();
		}
	},
	
	ARMPITS(false,
			"腋窝",
			Util.newArrayListOfValues(
					InventorySlot.TORSO_UNDER,
					InventorySlot.TORSO_OVER)) {
//		public boolean isPhysicallyAvailable(GameCharacter owner) {
//			return owner.hasArms();
//		}
	},
	
	TAIL(false,
			"尾巴",
			Util.newArrayListOfValues(
					InventorySlot.TAIL)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasTail();
		}
	},
	
	VAGINA(true,
			"小穴",
			Util.newArrayListOfValues(
					InventorySlot.VAGINA,
					InventorySlot.GROIN,
					InventorySlot.LEG)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasVagina();
		}
	},
	
	MOUND(true,
			"拢胸",
			Util.newArrayListOfValues(
					InventorySlot.GROIN,
					InventorySlot.LEG)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return !owner.hasVagina() && !owner.hasPenis();
		}
	},
	
	PENIS(true,
			"阴茎",
			Util.newArrayListOfValues(
					InventorySlot.PENIS,
					InventorySlot.GROIN,
					InventorySlot.LEG)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasPenis();
		}
	},
	
	TESTICLES(true,
			"睾丸",
			Util.newArrayListOfValues(
					InventorySlot.GROIN,
					InventorySlot.LEG)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasPenis();
		}
	},
	
	BREASTS(true,
			"胸部",
			Util.newArrayListOfValues(
					InventorySlot.CHEST,
					InventorySlot.NIPPLE,
					InventorySlot.TORSO_UNDER,
					InventorySlot.TORSO_OVER)),
	
	NIPPLES(true,
			"乳头",
			Util.newArrayListOfValues(
					InventorySlot.CHEST,
					InventorySlot.NIPPLE,
					InventorySlot.TORSO_UNDER,
					InventorySlot.TORSO_OVER)),
	
	BREASTS_CROTCH(true,
			"胯乳",
			Util.newArrayListOfValues(
					InventorySlot.STOMACH,
					InventorySlot.GROIN,
					InventorySlot.TORSO_UNDER,
					InventorySlot.TORSO_OVER)) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasBreastsCrotch();
		}
		public List<InventorySlot> getAssociatedInventorySlots(GameCharacter owner) {
			if(owner.getLegConfiguration().isBipedalPositionedCrotchBoobs()) {
				return super.getAssociatedInventorySlots(owner);
			} else {
				return Util.newArrayListOfValues(
						InventorySlot.STOMACH,
						InventorySlot.GROIN,
						InventorySlot.LEG);
			}
		}
	},
	
	NIPPLES_CROTCH(true,
			"胯乳乳头",
			null) {
		public boolean isPhysicallyAvailable(GameCharacter owner) {
			return owner.hasBreastsCrotch();
		}
		public List<InventorySlot> getAssociatedInventorySlots(GameCharacter owner) {
			return BREASTS_CROTCH.getAssociatedInventorySlots(owner);
		}
	},

	HAIR(false,
			"头发",
			Util.newArrayListOfValues(
					InventorySlot.HAIR,
					InventorySlot.HORNS,
					InventorySlot.HEAD)),
	
	MOUTH(true,
			"嘴巴",
			Util.newArrayListOfValues(
					InventorySlot.HAIR,
					InventorySlot.HORNS,
					InventorySlot.HEAD,
					InventorySlot.EYES,
					InventorySlot.MOUTH,
					InventorySlot.NECK)),

	EYES(true,
			"眼睛",
			Util.newArrayListOfValues(
					InventorySlot.HAIR,
					InventorySlot.HORNS,
					InventorySlot.HEAD,
					InventorySlot.EYES,
					InventorySlot.MOUTH,
					InventorySlot.NECK));

	
	private boolean saveDiscoveredStatus;
	private String name;
	private List<InventorySlot> associatedInventorySlots;

	private CoverableArea(boolean saveDiscoveredStatus, String name, List<InventorySlot> associatedInventorySlots) {
		setSaveDiscoveredStatus(saveDiscoveredStatus);
		this.name = name;
		this.associatedInventorySlots = associatedInventorySlots;
	}

	public boolean isSaveDiscoveredStatus() {
		return saveDiscoveredStatus;
	}

	public void setSaveDiscoveredStatus(boolean saveDiscoveredStatus) {
		this.saveDiscoveredStatus = saveDiscoveredStatus;
	}

	public String getName() {
		return name;
	}
	
	public List<InventorySlot> getAssociatedInventorySlots(GameCharacter owner) {
		return new ArrayList<>(associatedInventorySlots);
	}

	/**
	 * @return true if the owner has the related orifice/penetration type.
	 */
	public boolean isPhysicallyAvailable(GameCharacter owner) {
		return true;
	}

	public static CoverableArea of(String name) {
		for (CoverableArea value : CoverableArea.values()) {
			if (value.toString().equalsIgnoreCase(name)) {
				return value;
			}
		}
		if (Configurations.REPAIR_MODS) {
			String log = "Repair Mods enabled, and triggered, Before Fix : " + name;
			name = name.replace("FINGERS", "HANDS");
			name = name.replace("FINGER", "HANDS");
			name = name.replace("HEAD", "HAIR");
			name = name.replace("FACE", "MOUTH");
			name = name.replace("CHEST", "BREASTS");
			name = name.replace("BREAST", "BREASTS");
			name = name.replace("NIPPLE", "NIPPLES");
			System.err.println(log + " After Fix : " + name);
			return valueOf(name);
		}

		return null;
	}
}
