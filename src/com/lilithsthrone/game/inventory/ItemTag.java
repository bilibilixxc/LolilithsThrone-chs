package com.lilithsthrone.game.inventory;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.1
 * @version 0.4.11.4
 * @author Innoxia
 */
public enum ItemTag {

	CHEAT_ITEM, // Cheat items are hidden in the debug spawner, and are also not added to the Encyclopedia.
	SILLY_MODE, // Silly mode items only appear in shopkeepers inventories when silly mode is on.
	FILLY_NAMING(Util.newArrayListOfValues("将娜塔莉亚文本中所有提及“骡子”之处替换为“雌驹”"), false), // 骡子/雌驹项圈专用tag
	
	REMOVE_FROM_DRESSING_ROOM_OUTFITS,
	REMOVE_FROM_DEBUG_SPAWNER,
	NOT_FOR_SALE,
	
	REINDEER_GIFT, // Can be found in the presents that the reindeer sell (who appear in Dominion during winter months).
	SOLD_BY_RALPH, // Will also be used for any future consumable and miscellaneous item vendors.
	SOLD_BY_NYAN, // Clothing (is added to all clothing vendors)
	SOLD_BY_MONICA, // Clothing (is added to Elis only)
	SOLD_BY_KATE, // Jewellery
	SOLD_BY_FINCH, // BDSM and sex-related stuff
	SOLD_BY_VICKY, // Weapons
	SOLD_BY_EISEK, // Produce
	USED_BY_BONDAGE_APPLIERS, // When applied to clothing, then the clothing can spawn in bondage appliers' inventories

	// Items with these tags can randomly spawn in encounters in the commented area
	ALL_AREAS_SPAWN, // Every area in the game
	DOMINION_ALLEYWAY_SPAWN, // Dominion
	SUBMISSION_TUNNEL_SPAWN, // Submission (excluding Bat Caverns)
	BAT_CAVERNS_SPAWN, // Bat Caverns
	ELIS_ALLEYWAY_SPAWN, // Foloi Fields
	
	SPELL_BOOK,
	SPELL_SCROLL,
	ESSENCE,
	ATTRIBUTE_TF_ITEM,
	RACIAL_TF_ITEM,
	MISC_TF_ITEM, // Fetish or non-racial body part transformations
	BOOK,
	GIFT,

	// These three all remove 'thirst quenched', 'thirst quenched (junk food)', and 'thirst quenched (quality food)' and then apply their related status effect
	DRINK, // Drink items apply the 'thirst quenched' status effect upon use
	DRINK_POOR, // Basic drink items apply the 'thirst quenched (basic)' status effect upon use
	DRINK_QUALITY, // Quality drink items apply the 'thirst quenched (quality)' status effect upon use
	
	// These three all remove 'recently eaten', 'recently eaten (junk food)', and 'recently eaten (quality food)' and then apply their related status effect
	FOOD, // Food items apply the 'recently eaten' status effect upon use
	FOOD_POOR, // Junk food items apply the 'recently eaten (junk)' status effect upon use
	FOOD_QUALITY, // Quality food items apply the 'recently eaten (quality)' status effect upon use
	
	ALCOHOLIC, // For easy detection of alcoholic items in some scenes
	
	// To mark consumables as containing caffeine, with the number representing the equivalent alcoholic level to be applied to spider-morphs
	// Only one of these should be applied to an item
	CAFFEINATED_005(Util.newArrayListOfValues("对于[style.boldSpider(蛛化形)][style.boldMinorBad(增加)]5%[style.boldAlcohol(醉酒度)]"), false),
	CAFFEINATED_010(Util.newArrayListOfValues("对于[style.boldSpider(蛛化形)][style.boldMinorBad(增加)]10%[style.boldAlcohol(醉酒度)]"), false),
	CAFFEINATED_015(Util.newArrayListOfValues("对于[style.boldSpider(蛛化形)][style.boldMinorBad(增加)]15%[style.boldAlcohol(醉酒度)]"), false),
	CAFFEINATED_020(Util.newArrayListOfValues("对于[style.boldSpider(蛛化形)][style.boldMinorBad(增加)]20%[style.boldAlcohol(醉酒度)]"), false),
	CAFFEINATED_025(Util.newArrayListOfValues("对于[style.boldSpider(蛛化形)][style.boldMinorBad(增加)]25%[style.boldAlcohol(醉酒度)]"), false),
	CAFFEINATED_030(Util.newArrayListOfValues("对于[style.boldSpider(蛛化形)][style.boldMinorBad(增加)]30%[style.boldAlcohol(醉酒度)]"), false),
	CAFFEINATED_040(Util.newArrayListOfValues("对于[style.boldSpider(蛛化形)][style.boldMinorBad(增加)]40%[style.boldAlcohol(醉酒度)]"), false),
	CAFFEINATED_050(Util.newArrayListOfValues("对于[style.boldSpider(蛛化形)][style.boldMinorBad(增加)]50%[style.boldAlcohol(醉酒度)]"), false),
	CAFFEINATED_075(Util.newArrayListOfValues("对于[style.boldSpider(蛛化形)][style.boldMinorBad(增加)]75%[style.boldAlcohol(醉酒度)]"), false),
	CAFFEINATED_100(Util.newArrayListOfValues("对于[style.boldSpider(蛛化形)][style.boldMinorBad(增加)]100%[style.boldAlcohol(醉酒度)]"), false),
	
	CONTRABAND_LIGHT(// 'Restricted' items will not be bought by honest shopkeepers
			Util.newArrayListOfValues(
					"[style.colourDarkBlue(受限)]",
					"[style.colourMinorBad(诚信商人不会买账)]"),
			false),
	CONTRABAND_MEDIUM(// 'Illegal' items will not be bought by honest shopkeepers and will be confiscated by Enforcers
			Util.newArrayListOfValues(
					"[style.colourDarkBlue(非法)]",
					"[style.colourMinorBad(诚信商人不会买账)]",
					"[style.colourBad(执法者会没收这件物品)]"),
			false),
	CONTRABAND_HEAVY(// 'Highly Illegal' items will not be bought by honest shopkeepers and Enforcers will arrest anyone possessing them
			Util.newArrayListOfValues(
					"[style.colourDarkBlue(严重违法)]",
					"[style.colourMinorBad(诚信商人不会买账)]",
					"[style.colourTerrible(执法者会逮捕携带这件物品的人)]"),
			false),
	
	//-------------- WEAPONS & CLOTHING --------------//
	
	// ----- Weapon-specific tags: ----- //
	WEAPON_FERAL_EQUIPPABLE( // Allows ferals to equip this weapon (as they cannot equip weapons by default)
			Util.newArrayListOfValues(
					"[style.colourFeral(可以被兽类装备)]"),
			false),
	
	WEAPON_BLADE, // Should be added to all weapons that use an arcane blade
	
	WEAPON_FIREARM, // Should be added to all weapons that should be considered to be a firearm
	
	WEAPON_UNARMED, // Should be added to all weapons that should use unarmed damage calculations instead of melee
	//   ------------------------------ //
	
	/** Excludes this clothing from being randomly chosen to equip on an NPC in automatic outfit generation.
	 *  Also excludes the clothing from randomly spawning as tile-exploration loot.
	 *  This only really affects common-rarity clothing, as all clothing of a rarity higher than common are typically only able to be added to characters directly. */
	NO_RANDOM_SPAWN,
	
	UNIQUE_NO_NPC_EQUIP, // Prevents the clothing/weapon from being equipped onto an NPC. Only works on items with a 'UNIQUE' rarity level

	UNLOCKS_ENCOUNTER( // Special tag which has no effect other than displaying text for clothing stickers. Used for the 'rental mommy' tshirt, so look there for an example.
			Util.newArrayListOfValues(
			"[style.colourBlueLight(可能引起遭遇战)]"),
			false),
	
	NIGHT_VISION_SELF( // Makes this clothing or weapon provide immunity to the darkness debuff for just the wearer while equipped
			Util.newArrayListOfValues(
					"[style.colourGood(消除“黑暗”效果)]"),
			false),
	
	NIGHT_VISION_AREA( // Makes this clothing or weapon provide immunity to the darkness debuff for all characters in the area in which the wearer is located while equipped
			Util.newArrayListOfValues(
					"[style.colourExcellent(消除所有附近角色的“黑暗”效果)]"),
			false),
	
	REVEALS_CONCEALABLE_SLOT, // If a piece of clothing has this tag, it will always be visible, even if another item of clothing is concealing its slot. (Used for spreader bar.)

	TRANSPARENT( // This item of clothing does not conceal any areas. Used for chastity cages & condoms (so penis is still visible). Could also be used for sheer clothing material.
			Util.newArrayListOfValues(
					"[style.colourSex(不能隐藏任何身体部位)]"),
			false),
	
	IGNORE_HAIR_RESTRICTION, // If a piece of clothing has this tag, it can always be equipped into the HAIR slot, even if the equipping character has no hair.
	
	DRESS, // For helping to generate clothing in CharacterUtils
	SWIMWEAR, // For helping to generate clothing in CharacterUtils
	
	PRESCRIPTION_GLASSES(  // Negates the blurry vision status effect
			Util.newArrayListOfValues(
					"[style.colourGood(附魔的镜片可以增强佩戴者的视力)]"),
			false),

	PROVIDES_KEY( // The person who equips this clothing will get an unlock key, making the unsealing cost 0
			Util.newArrayListOfValues(
					"[style.colourGood(为装备者提供钥匙)]"),
			false),
	
	UNENCHANTABLE(  // Prevents the player from enchanting this in the inventory menu.
			Util.newArrayListOfValues(
					"[style.colourBad(无法附魔)]"),
			false),

	// If clothing has these tags, then it will act as though the associated bodyPart is added to the blockedBodyParts list
	// This is mainly useful for stickers, as normally you should be using the blockedBodyParts list to define blocked areas
	// Look at the innoxia_latex_hood clothing item to see these in use
	APPLIES_BLOCKED_BODY_PART_EYES(Util.newArrayListOfValues("[style.colourBad(遮挡眼部)]"), false),
	APPLIES_BLOCKED_BODY_PART_MOUTH(Util.newArrayListOfValues("[style.colourBad(遮挡口部)]"), false),
	
	SPREADS_FEET( // Prevents double foot actions, like wrap-around footjobs
			Util.newArrayListOfValues(
					"[style.colourBad(限制性爱动作)]"),
			false),

	MUFFLES_SPEECH( // Causes the wearer to not be able to talk. E.g. Ball gags
			Util.newArrayListOfValues(
					"[style.colourBad(说话含糊不清)]"),
			false),

	BLOCKS_SIGHT( // Causes the wearer to suffer from 'blinded' status effect
			Util.newArrayListOfValues(
					"[style.colourBad(遮挡视线)]"),
			false),
	
	HINDERS_ARM_MOVEMENT( // Hinders the ability of the wearer to use their arms. E.g. Hand cuffs
			Util.newArrayListOfValues(
					"[style.colourBad(妨碍手臂运动)]",
					"[style.colourTerrible(阻止翼手战斗)]"),
			false),

	HINDERS_LEG_MOVEMENT(  // Hinders the ability of the wearer to run or use their legs properly. E.g. Spreader bar
			Util.newArrayListOfValues(
					"[style.colourBad(妨碍腿部运动)]",
					"[style.colourTerrible(阻止非飞行战斗逃跑)]"),
			false),

	PREVENTS_COMBAT_ESCAPE(  // Prevents the character from escaping from combat
			Util.newArrayListOfValues(
					"[style.colourTerrible(阻止战斗逃跑)]"),
			false),
	
	DUPLICATE_WHEN_EQUIP,  // Instead of removing the clothing from the inventory it's in, duplicate the clothing and then equip the duplicate instead. This should probably be paired with DISCARDED_WHEN_UNEQUIPPED, and is used for the electrical tape roll.
	
	DISCARDED_WHEN_UNEQUIPPED( // Makes the clothing be thrown away when unequipped. E.g. Condoms
			Util.newArrayListOfValues(
					"[style.colourMinorBad(卸下时将被丢弃)]"),
			false),

	ENABLE_SEX_EQUIP( // Allows this clothing to be equipped during sex. E.g. Condoms or strapons
			Util.newArrayListOfValues(
					"[style.colourSex(可在性交中装备)]"),
			false),
	
	// Self-explanatory requirements in order to equip this clothing:
	REQUIRES_PENIS(
			Util.newArrayListOfValues(
					"[style.colourSex(需要拥有阴茎)]"),
			false),
	REQUIRES_NO_PENIS(
			Util.newArrayListOfValues(
					"[style.colourSex(需要没有阴茎)]"),
			false),
	REQUIRES_VAGINA(
			Util.newArrayListOfValues(
					"[style.colourSex(需要拥有阴道)]"),
			false),
	REQUIRES_NO_VAGINA(
			Util.newArrayListOfValues(
					"[style.colourSex(需要没有阴道)]"),
			false),
	REQUIRES_FUCKABLE_NIPPLES(
			Util.newArrayListOfValues(
					"[style.colourSex(需要可操的乳头)]"),
			false),
	
	// These 'FITS' tags are used to check for whether clothing is suitable for certain body parts. They should be pretty self-explanatory.
	// Mouths (mouth clothing fits all mouth types by default, which is why there's no need for 'FITS_MUZZLES' or 'FITS_BEAKS'):
	FITS_MUZZLES_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourFeral(只适配口套)]"),
			false),
	FITS_BEAKS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourFeral(只适配喙)]"),
			false),
	
	// legs:
	FITS_HOOFS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourFeral(只适配有蹄类)]"),
			false),
	FITS_HOOFS(
			Util.newArrayListOfValues(
					"[style.colourFeral(适配有蹄类)]"),
			false),
	
	FITS_TALONS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourFeral(只适配鸟类的利爪)]"),
			false),
	FITS_TALONS(
			Util.newArrayListOfValues(
					"[style.colourFeral(适配鸟类的利爪)]"),
			false),
	
	// Arms:
	FITS_FEATHERED_ARM_WINGS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(只适配羽毛翼手)]"),
			false),
	FITS_FEATHERED_ARM_WINGS(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(适配羽毛翼手)]"),
			false),
	FITS_LEATHERY_ARM_WINGS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(只适配皮质翼手)]"),
			false),
	FITS_LEATHERY_ARM_WINGS(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(适配皮质翼手)]"),
			false),
	FITS_ARM_WINGS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(只适配翼手)]"),
			false),
	FITS_ARM_WINGS(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(适配翼手)]"),
			false),

	// Bodies:
	FITS_NON_BIPED_BODY_HUMANOID(
			Util.newArrayListOfValues(
					"[style.colourHuman(适配非两足动物身体的类人部分)]"),
			false),
	FITS_BIPEDS(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(只适配双足身体)]"),
			false),
	FITS_TAUR_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(只适配四足身体)]"),
			false),
	FITS_LONG_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(只适配拥有长尾巴的身体)]"),false), //lamia, eels
	FITS_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(只适配拥有尾巴的身体)]"),false), //mermaids
	FITS_ARACHNID_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(只适配蛛形身体)]"),false), //spiders and scorpions
	FITS_CEPHALOPOD_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(只适配头足类身体)]"),false), //octopuses and squids
	FITS_AVIAN_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(只适配鸟类身体)]"),false), //bird-taurs

	// Feral status:
	// These tags are non-exclusive, so clothing with them can additionally be equipped by non-ferals:
	FITS_FERAL_ALL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(适配所有兽态身体)]"),false), //All feral bodies can equip clothing marked with this tag.
	FITS_FERAL_QUADRUPED_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(适配四足兽态身体)]"),false), //Quadrupedal feral bodies can equip clothing marked with this tag
	FITS_FERAL_LONG_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(适配拥有长尾巴的兽态身体)]"),false), //Long-tailed feral bodies can equip clothing marked with this tag
	FITS_FERAL_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(适配拥有尾巴的兽态身体)]"),false), //Tailed feral bodies can equip clothing marked with this tag
	FITS_FERAL_ARACHNID_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(适配蛛形兽态身体)]"),false), //Arachnid feral bodies can equip clothing marked with this tag
	FITS_FERAL_CEPHALOPOD_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(适配头足类兽态身体)]"),false), //Cephalopod feral bodies can equip clothing marked with this tag
	FITS_FERAL_AVIAN_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(适配鸟类兽态身体)]"),false), //Avian feral bodies can equip clothing marked with this tag
	// These tags are exclusive, so clothing with them can ONLY be equipped by the corresponding body type (unless the clothing additionally has other permissive tags):
	ONLY_FITS_FERAL_ALL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(只适配兽态身体)]"),false),
	ONLY_FITS_FERAL_QUADRUPED_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(只适配四足兽态身体)]"),false),
	ONLY_FITS_FERAL_LONG_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(只适配拥有长尾巴的兽态身体)]"),false),
	ONLY_FITS_FERAL_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(只适配拥有尾巴的兽态身体)]"),false),
	ONLY_FITS_FERAL_ARACHNID_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(只适配蛛形兽态身体)]"),false),
	ONLY_FITS_FERAL_CEPHALOPOD_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(只适配头足类兽态身体)]"),false),
	ONLY_FITS_FERAL_AVIAN_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(只适配鸟类兽态身体)]"),false),
	
	
	RIGID_MATERIAL( // The clothing is made out of a rigid material, and as such, groping actions cannot be performed on it. Used for chastity cages/belts.
			Util.newArrayListOfValues(
					"[style.colourTerrible(阻止触摸动作)]"),
			false),
	
	PREVENTS_ERECTION_PHYSICAL( // Prevents the wearer from getting an erection during sex, by means of physically limiting the space into which the erection could take shape (i.e. chastity cages). As of 0.3.1, only affects descriptors.
			Util.newArrayListOfValues(
					"[style.colourTerrible(限制勃起)]"),
			false),
	PREVENTS_ERECTION_OTHER( // Prevents the wearer from getting an erection during sex, by means other than physical limitations. As of 0.3.1, only affects descriptors.
			Util.newArrayListOfValues(
					"[style.colourTerrible(限制勃起)]"),
			false),
	
	// Sex-related clothing:

	/**<b>IMPORTANT</b> This tag should only ever be given to clothing going into the PENIS InventorySlot, as otherwise it will throw errors.*/
	CONDOM(true), // Gives this clothing condom behaviour
	
	CHOKER_SNAP( // Snaps (into wearer's inventory) if throat stretches.
			Util.newArrayListOfValues(
					"[style.colourSex(性交时深喉过度会使其断裂)]"),
			true),
	
	CHASTITY( // Tags the clothing as being some form of chastity device, meaning that it will apply the 'CHASTITY_1' status effect when equipped
			Util.newArrayListOfValues(
					"[style.colourTerrible(贞操装置)]"),
			true),
	
	// To detect whether creampies should leak out or not:
	
	PLUGS_ANUS( // Counts as being inserted into the wearer's anus. E.g. butt plugs or anal beads
			Util.newArrayListOfValues(
					"[style.colourSex(填塞肛门(防止被内射时弄脏))]"),
			true),
	SEALS_ANUS( // Counts as sealing, but not inserted into, the wearer's anus. E.g. Tape
			Util.newArrayListOfValues(
					"[style.colourSex(遮盖肛门(防止被对方高潮时弄脏))]"),
			true),
	
	PLUGS_VAGINA( // Counts as being inserted into the wearer's vagina. E.g. insertable dildo
			Util.newArrayListOfValues(
					"[style.colourSex(填塞阴道(防止被内射时弄脏))]"),
			true),
	SEALS_VAGINA( // Counts as sealing, but not inserted into, the wearer's vagina. E.g. Tape
			Util.newArrayListOfValues(
					"[style.colourSex(遮盖阴道(防止被对方高潮时弄脏))]"),
			true) {
		@Override
		public List<String> getClothingTooltipAdditions() {
			if(Main.game.isUrethraEnabled()) {
				return Util.newArrayListOfValues("[style.colourSex(遮盖阴道(包括尿道))]");
			} else {
				return Util.newArrayListOfValues("[style.colourSex(遮盖阴道)]");
			}
		}
	},
	
	PLUGS_NIPPLES( // Counts as being inserted into the wearer's nipples. E.g. insertable nipple-dildos
			Util.newArrayListOfValues(
					"[style.colourSex(填塞乳头(防止被内射时弄脏))]"),
			true),
	SEALS_NIPPLES( // Counts as sealing, but not inserted into, the wearer's nipples. E.g. Pasties
			Util.newArrayListOfValues(
					"[style.colourSex(遮盖乳头(防止被对方高潮时弄脏))]"),
			true),
	
	MILKING_EQUIPMENT(
			Util.newArrayListOfValues(
					"[style.colourMilk(榨取装置(可榨取并吸收对应液体))]"),
			true),
	
	/** <b>This is automatically assigned to items, and should not be manually added to ItemTags!</b> */
	DILDO_SELF(
			Util.newArrayListOfValues(
					"[style.colourSub(可插入式)][style.colourSex(假阳具)]"),
			true),

	/** <b>This is automatically assigned to items, and should not be manually added to ItemTags!</b> */
	DILDO_OTHER(
			Util.newArrayListOfValues(
					"[style.colourDom(可穿戴式)][style.colourSex(假阳具)]"),
			true),

	/** <b>This is automatically assigned to items, and should not be manually added to ItemTags!</b> */
	ONAHOLE_SELF(
			Util.newArrayListOfValues(
					"[style.colourSex(可插入式飞机杯)]"),
			true),

	/** <b>This is automatically assigned to items, and should not be manually added to ItemTags!</b> */
	ONAHOLE_OTHER(
			Util.newArrayListOfValues(
					"[style.colourSex(可穿戴式飞机杯)]"),
			true),
	;

	private List<String> clothingTooltipAdditions;
	private boolean sexToy;

	/**
	 * @param clothingTooltipAdditions The descriptions that should be appended to clothing tooltips for when this ItemTag is present on clothing.
	 * @param sexToy If true, then this item tag marks the associating clothing as a sex toy, which, if worn by a dom, is unable to be removed by subs in sex.
	 */
	private ItemTag(List<String> clothingTooltipAdditions, boolean sexToy) {
		this.clothingTooltipAdditions = clothingTooltipAdditions;
		this.sexToy = sexToy;
	}
	/**
	 * @param sexToy If true, then this item tag marks the associating clothing as a sex toy, which, if worn by a dom, is unable to be removed by subs in sex.
	 */
	private ItemTag(boolean sexToy) {
		this.clothingTooltipAdditions = null;
		this.sexToy = sexToy;
	}
	
	private ItemTag() {
		this.clothingTooltipAdditions = null;
		this.sexToy = false;
	}

	public List<String> getClothingTooltipAdditions() {
		if(clothingTooltipAdditions==null) {
			return new ArrayList<>();
		}
		return clothingTooltipAdditions;
	}
	
	/**
	 * @return true if this tag makes the clothing to which it is applied a sex toy, meaning that NPCs will not remove it during sex unless it is blocking the part they wish to access.
	 */
	public boolean isSexToy() {
		return sexToy;
	}
	
}
