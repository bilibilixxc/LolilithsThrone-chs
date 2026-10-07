package com.lilithsthrone.game.inventory.clothing;

/**
 * The types of possible access you'd need in order to equip a piece of clothing.
 * 
 * @since 0.1.64
 * @version 0.2.10
 * @author Innoxia
 */
public enum ClothingAccess {

	/** Placing something onto your eyes, like glasses. */
	EYES("放在眼睛上"),

	/** Placing something into your mouth, like a ball-gag. */
	MOUTH("戴在嘴上"),

	/** Pulling something down over your head, like a t-shirt. */
	HEAD("盖在头上"),

	/**
	 * Sliding something up your arms to rest on your shoulders, like the loops in a bra.
	 */
	ARMS_UP_TO_SHOULDER("穿过手臂"),

	/** Sliding something up your wrists, like elbow-length gloves. */
	WRISTS("穿过手腕"),

	/** Sliding something down your fingers, like a ring. */
	FINGERS("穿过手指"),

	/** Sliding something over your chest, like the cups of a bra. */
	CHEST("穿过胸部"),

	/** Sliding something over your waist, like a corset or swimsuit. */
	WAIST("穿过腰部"),

	/** Pulling something up your legs, like a pair of panties. */
	LEGS_UP_TO_GROIN_LOW_LEVEL("拉到腿上"),

	/** Sliding something up your legs, like the holes in a pair of trousers. */
	LEGS_UP_TO_GROIN("穿过腿"),

	/** Sliding something over your groin, like a pair of panties.*/
	GROIN("滑上腹股沟"),

	/** Sliding something onto your penis, like a condom. */
	PENIS("套到阴茎上"), // This gets automatically added to 'clothingAccessBlocked' values if clothing has 'PENIS' in 'blockedBodyParts'.

	/** Inserting something into your vagina, like a dildo. */
	VAGINA("插进阴道里"), // This gets automatically added to 'clothingAccessBlocked' values if clothing has 'VAGINA' in 'blockedBodyParts'.

	/** Inserting something into your anus, like a butt-plug. */
	ANUS("插进肛门里"),

	/** Sliding something over your calves, like a pair of knee-high socks. */
	CALVES("穿过小腿"),

	/** Sliding something over your feet, like a pair of socks. */
	FEET("穿过脚");

	String descriptor;

	private ClothingAccess(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}
}
