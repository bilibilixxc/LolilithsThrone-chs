package com.lilithsthrone.game.inventory;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public enum InventorySlot {

	// HEAD:
	
	/** Clothing slot "head". Used for headgear.<br/>
	 *  Tattoo slot "head".*/
	HEAD(40, "头部", false, false, false, "头部") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "头部";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getFaceCovering());
		}
	},

	/** Clothing slot "eyes". Used for glasses.<br/>
	 *  Tattoo slot "upper face".*/
	EYES(50, "眼部", true, false, false, "上面部") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.eyes]";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getFaceCovering());
		}
	},

	/** Clothing slot "hair". Used for ribbons and hairbands.<br/>
	 *  Tattoo slot "ears".*/
	HAIR(20, "头发", false, false, false, "耳朵") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasHair()) {
				return UtilText.parse(character, "[npc.Name]没有头发！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.hair(true)]";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getEarCovering());
		}
	},

	/** Clothing slot "mouth". Used for ballgags.<br/>
	 *  Tattoo slot "lower face".*/
	MOUTH(10, "嘴部", false, false, false, "下面部") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.mouth]";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getFaceCovering());
		}
	},

	/** Clothing slot "neck". Used for necklaces and scarfs.<br/>
	 *  Tattoo slot "neck".*/
	NECK(30, "颈部", false, false, false, "颈部") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "颈部";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getFaceCovering());
		}
	},
	
	
	// TORSO:

	/** Clothing slot "over-torso". Used for coats.<br/>
	 *  Tattoo slot "upper back".*/
	TORSO_OVER(50, "外衣", false, false, false, "上背部") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "外衣";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getTorsoCovering());
		}
	},

	/** Clothing slot "torso". Used for shirts.<br/>
	 *  Tattoo slot "lower back".*/
	TORSO_UNDER(40, "躯干", false, true, false, "下背部") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "躯干";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getTorsoCovering());
		}
	},

	/** Clothing slot "chest". Used for bras.<br/>
	 *  Tattoo slot "chest".*/
	CHEST(10, "胸部", false, true, false, "胸部") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.breasts]";
		}
		@Override
		public boolean isPlural(GameCharacter character) {
			return character.hasBreasts();
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getBreastCovering());
		}
	},

	/** Clothing slot "nipples". Used for nipple shields, plugs.<br/>
	 * <br/>If character is a feral with no breasts, crotch-nipples are referenced.
	 *  Tattoo slot "nipples".*/
	NIPPLE(5, "乳头", false, false, false, "乳头") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(character.isFeral() && !character.getFeralAttributes().isBreastsPresent() && !character.hasBreastsCrotch()) {
				return UtilText.parse(character, "[npc.Name]没有胸部或副乳！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			if(character.isFeral() && !character.getFeralAttributes().isBreastsPresent() && character.hasBreastsCrotch()) {
				return "[npc.nipplesCrotch]";
			}
			return "[npc.nipples]";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getNippleCovering());
		}
	},

	/** Clothing slot "stomach". Used for corsets.<br/>
	 *  Tattoo slot "stomach".*/
	STOMACH(10, "腹部", false, false, false, "腹部") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "腹部";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getTorsoCovering());
		}
	},

	
	// HAND:

	/** Clothing slot "hands". Used for gloves.<br/>
	 *  Tattoo slot "forearms".*/
	HAND(20, "手部", true, false, false, "前臂") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(character.isFeral() && !character.getFeralAttributes().isArmsOrWingsPresent()) {
				return UtilText.parse(character, "[npc.Name]没有手！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.hands]";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getArmCovering());
		}
	},

	/** Clothing slot "wrists". Used for bracelets.<br/>
	 *  Tattoo slot "upper arms".*/
	WRIST(30, "手腕", true, false, false, "上臂") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(character.isFeral() && !character.getFeralAttributes().isArmsOrWingsPresent()) {
				return UtilText.parse(character, "[npc.Name]没有手臂！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "手腕";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getArmCovering());
		}
	},

	/** Clothing slot "fingers". Used for rings.<br/>
	 *  Tattoo slot "hands".*/
	FINGER(30, "手指", true, false, false, "手部") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(character.isFeral() && !character.getFeralAttributes().isFingerActionsAvailable()) {
				return UtilText.parse(character, "[npc.Name]没有手指！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.fingers]";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getArmCovering());
		}
	},

	/** Clothing slot "hips". Used for belts.<br/>
	 *  Tattoo slot "hips".*/
	HIPS(40, "臀部", true, false, false, "臀部") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.hips]";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getTorsoCovering());
		}
	},

//	/** Clothing slot "hips-under". Used for suspender belts.<br/>
//	 *  Tattoo slot "".*/
//	HIPS_UNDER(20, "hips-under", false, false), // suspender belts

	/** Clothing slot "anus". Used for plugs.<br/>
	 *  Tattoo slot "ass".*/
	ANUS(0, "肛门", false, false, false, "屁股") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.asshole]";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getAssCovering());
		}
	},

	
	// LEG & FOOT:

	/** Clothing slot "legs". Used for trousers.<br/>
	 *  Tattoo slot "upper leg".*/
	LEG(30, "腿部", true, true, false, "大腿") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.legs]";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getLegCovering());
		}
	},

	/** Clothing slot "groin". Used for underwear.<br/>
	 *  Tattoo slot "lower abdomen".*/
	GROIN(10, "腹股沟", false, true, false, "下腹部") {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "腹股沟";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getTorsoCovering());
		}
	},

	/** Clothing slot "feet". Used for shoes.<br/>
	 *  Tattoo slot "feet".*/
	FOOT(40, "足部", true, true, false, "足部") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasLegs()) {
				return UtilText.parse(character, "[npc.Name]没有腿！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.feet]";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getLegCovering());
		}
	},

	/** Clothing slot "calves". Used for socks.<br/>
	 *  Tattoo slot "lower leg".*/
	SOCK(10, "小腿", true, true, false, "小腿") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasLegs()) {
				return UtilText.parse(character, "[npc.Name]没有腿！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "小腿";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getLegCovering());
		}
	},

	/** Clothing slot "ankles". Used for bracelets.<br/>
	 *  Tattoo slot "ankles".*/
	ANKLE(50, "脚踝", true, false, false, "脚踝") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasLegs()) {
				return UtilText.parse(character, "[npc.Name]没有腿！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "脚踝";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getLegCovering());
		}
	},
	
	
	// OPTIONAL EXTRAS:

	/** Clothing slot "horns". Used for horn decorations.<br/>
	 *  Tattoo slot "horns".*/
	HORNS(50, "角", true, false, false, "角") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasHorns()) {
				return UtilText.parse(character, "[npc.Name]没长角！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.horns]";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getHornCovering());
		}
	},

	/** Clothing slot "wings". Used for wing decorations.<br/>
	 *  Tattoo slot "wings".*/
	WINGS(50, "翅膀", true, false, false, "翅膀") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasWings()) {
				return UtilText.parse(character, "[npc.Name]没有翅膀！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.wings]";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getWingCovering());
		}
	},

	/** Clothing slot "tail". Used for tail decorations.<br/>
	 *  Tattoo slot "tail".*/
	TAIL(50, "尾巴", false, false, false, "尾巴") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasTail()) {
				return UtilText.parse(character, "[npc.Name]没有尾巴！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.tail]";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getTailCovering());
		}
	},

	/** Clothing slot "penis". Used for cock socks, cages, and plugs.<br/>
	 *  Tattoo slot "penis".*/
	PENIS(0, "阴茎", false, false, false, "阴茎") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasPenisIgnoreDildo()) {
				return UtilText.parse(character, "[npc.Name]没有阴茎！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		public BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character) {
			if (character == null) {
				return null;
			}
			if(!isPhysicallyAvailable(character)) {
				return new BodyPartClothingBlock(
					Util.newArrayListOfValues(this),
					null,
					this.getPhysicallyUnavailableReason(character),
					Util.newArrayListOfValues(ItemTag.REQUIRES_NO_PENIS));
			}
			// Leg configuration (takes into account feral):
			List<BodyPartClothingBlock> blockedList = character.getLegConfiguration().getBodyPartClothingBlock(character);
			if(blockedList!=null) {
				for(BodyPartClothingBlock block : blockedList) {
					if(block.getBlockedSlots().contains(this)) {
						return block;
					}
				}
			}
			for(BodyPartInterface bodypart : character.getBody().getAllBodyParts()) {
				BodyPartClothingBlock block = bodypart.getType().getBodyPartClothingBlock();
				if(block!=null) {
					if(block.getBlockedSlots().contains(this)) {
						return block;
					}
				}
			}
			
			return null;
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.cock]";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getPenisCovering());
		}
	},

	/** Clothing slot "vagina". Used for plugs.<br/>
	 *  Tattoo slot "vagina".*/
	VAGINA(0, "阴道", false, false, false, "阴部") {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasVagina()) {
				return UtilText.parse(character, "[npc.Name]没有阴道！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		public BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character) {
			if (character == null) {
				return null;
			}
			if(!isPhysicallyAvailable(character)) {
				return new BodyPartClothingBlock(
					Util.newArrayListOfValues(this),
					null,
					this.getPhysicallyUnavailableReason(character),
					Util.newArrayListOfValues(ItemTag.REQUIRES_NO_VAGINA));
			}
			// Leg configuration (takes into account feral):
			List<BodyPartClothingBlock> blockedList = character.getLegConfiguration().getBodyPartClothingBlock(character);
			if(blockedList!=null) {
				for(BodyPartClothingBlock block : blockedList) {
					if(block.getBlockedSlots().contains(this)) {
						return block;
					}
				}
			}
			for(BodyPartInterface bodypart : character.getBody().getAllBodyParts()) {
				BodyPartClothingBlock block = bodypart.getType().getBodyPartClothingBlock();
				if(block!=null) {
					if(block.getBlockedSlots().contains(this)) {
						return block;
					}
				}
			}
			
			return null;
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.pussy]";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return character.getCovering(character.getVaginaCovering());
		}
	},

	// PIERCING:
	PIERCING_EAR(0, "耳部穿孔", false, false, true, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.ears]";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	},
	PIERCING_NOSE(0, "鼻部穿孔", false, false, true, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.nose]";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	},
	PIERCING_TONGUE(0, "舌部穿孔", false, false, true, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.tongue]";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	},
	PIERCING_LIP(0, "唇部穿孔", false, false, true, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.lips]";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	},
	PIERCING_STOMACH(0, "肚脐穿孔", false, false, true, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "肚脐";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	},
	PIERCING_NIPPLE(0, "乳头穿孔", false, false, true, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.nipples]";
		}

		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	},
	PIERCING_VAGINA(0, "阴部穿孔", false, false, true, null) {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasVagina()) {
				return UtilText.parse(character, "[npc.Name]没有阴道！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.pussy]";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	},
	PIERCING_PENIS(0, "阴茎穿孔", false, false, true, null) {
		@Override
		public String getPhysicallyUnavailableReason(GameCharacter character) {
			if(!character.hasPenisIgnoreDildo()) {
				return UtilText.parse(character, "[npc.Name]没有阴茎！");
			}
			return super.getPhysicallyUnavailableReason(character);
		}
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return "[npc.cock]";
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	},

	// EQUIPPABLE:
	WEAPON_MAIN_1(0, "主手武器", false, false, false, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return null;
		}
		@Override
		public boolean isWeapon() {
			return true;
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	},
	WEAPON_MAIN_2(0, "主手武器(二)", false, false, false, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return null;
		}
		@Override
		public boolean isWeapon() {
			return true;
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	},
	WEAPON_MAIN_3(0, "主手武器(三)", false, false, false, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return null;
		}
		@Override
		public boolean isWeapon() {
			return true;
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	},
	
	WEAPON_OFFHAND_1(0, "副手武器", false, false, false, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return null;
		}
		@Override
		public boolean isWeapon() {
			return true;
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	},
	WEAPON_OFFHAND_2(0, "副手武器(二)", false, false, false, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return null;
		}
		@Override
		public boolean isWeapon() {
			return true;
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	},
	WEAPON_OFFHAND_3(0, "副手武器(三)", false, false, false, null) {
		@Override
		protected String getNameForParsing(GameCharacter character) {
			return null;
		}
		@Override
		public boolean isWeapon() {
			return true;
		}
		@Override
		protected Covering getAssociatedCoveringForTattoo(GameCharacter character) {
			return null;
		}
	};

	private int zLayer;
	private String name;
	private boolean plural;
	private String tattooSlotName;
	private boolean jewellery;
	private boolean coreClothing;

	private static List<InventorySlot> humanoidSlots;
	private static List<InventorySlot> clothingSlots;
	private static List<InventorySlot> mainClothingSlots;
	private static List<InventorySlot> extraClothingSlots;
	private static List<InventorySlot> piercingSlots;
	private static List<InventorySlot> commonTattooSlots;
	
	public static InventorySlot[] mainWeaponSlots;
	public static InventorySlot[] offhandWeaponSlots;
	public static InventorySlot[] allWeaponSlots;

	static {
		mainWeaponSlots = new InventorySlot[] {InventorySlot.WEAPON_MAIN_1, InventorySlot.WEAPON_MAIN_2, InventorySlot.WEAPON_MAIN_3};
		offhandWeaponSlots = new InventorySlot[] {InventorySlot.WEAPON_OFFHAND_1, InventorySlot.WEAPON_OFFHAND_2, InventorySlot.WEAPON_OFFHAND_3};
		allWeaponSlots = new InventorySlot[] {InventorySlot.WEAPON_MAIN_1, InventorySlot.WEAPON_MAIN_2, InventorySlot.WEAPON_MAIN_3, InventorySlot.WEAPON_OFFHAND_1, InventorySlot.WEAPON_OFFHAND_2, InventorySlot.WEAPON_OFFHAND_3};
		
		humanoidSlots = new ArrayList<>();
		clothingSlots = new ArrayList<>();
		piercingSlots = new ArrayList<>();
		extraClothingSlots = new ArrayList<>();
		
		clothingSlots.add(HEAD);
		clothingSlots.add(EYES);
		clothingSlots.add(HAIR);
		clothingSlots.add(MOUTH);
		clothingSlots.add(NECK);

		clothingSlots.add(TORSO_OVER);
		clothingSlots.add(TORSO_UNDER);
		clothingSlots.add(CHEST);
		clothingSlots.add(NIPPLE);
		clothingSlots.add(STOMACH);
		
		clothingSlots.add(HAND);
		clothingSlots.add(WRIST);
		clothingSlots.add(FINGER);
		clothingSlots.add(HIPS);
		clothingSlots.add(ANUS);
		
		clothingSlots.add(LEG);
		clothingSlots.add(GROIN);
		clothingSlots.add(FOOT);
		clothingSlots.add(SOCK);
		clothingSlots.add(ANKLE);
		
		clothingSlots.add(HORNS);
		clothingSlots.add(TAIL);
		clothingSlots.add(WINGS);
		clothingSlots.add(PENIS);
		clothingSlots.add(VAGINA);
		
		extraClothingSlots.add(HORNS);
		extraClothingSlots.add(TAIL);
		extraClothingSlots.add(WINGS);
		extraClothingSlots.add(PENIS);
		extraClothingSlots.add(VAGINA);
		
		mainClothingSlots = new ArrayList<>(clothingSlots);
		mainClothingSlots.removeAll(extraClothingSlots);
		
		piercingSlots.add(PIERCING_EAR);
		piercingSlots.add(PIERCING_NOSE);
		piercingSlots.add(PIERCING_TONGUE);
		piercingSlots.add(PIERCING_LIP);
		piercingSlots.add(PIERCING_STOMACH);
		piercingSlots.add(PIERCING_NIPPLE);
		piercingSlots.add(PIERCING_VAGINA);
		piercingSlots.add(PIERCING_PENIS);
		
		commonTattooSlots = Util.newArrayListOfValues(
				InventorySlot.NECK,
				InventorySlot.TORSO_OVER,
				InventorySlot.TORSO_UNDER,
				InventorySlot.CHEST,
				InventorySlot.STOMACH,
				InventorySlot.HAND,
				InventorySlot.WRIST,
				InventorySlot.HIPS,
				InventorySlot.ANUS,
				InventorySlot.LEG,
				InventorySlot.GROIN,
				InventorySlot.SOCK,
				InventorySlot.ANKLE);

		humanoidSlots.add(HEAD);
		humanoidSlots.add(EYES);
		humanoidSlots.add(HAIR);
		humanoidSlots.add(MOUTH);
		humanoidSlots.add(NECK);

		humanoidSlots.add(TORSO_OVER);
		humanoidSlots.add(TORSO_UNDER);
		humanoidSlots.add(CHEST);
		humanoidSlots.add(NIPPLE);
		humanoidSlots.add(STOMACH);
		
		humanoidSlots.add(HAND);
		humanoidSlots.add(WRIST);
		humanoidSlots.add(FINGER);
		humanoidSlots.add(HIPS);
//		humanoidSlots.add(ANUS);
		
//		humanoidSlots.add(LEG);
//		humanoidSlots.add(GROIN);
//		humanoidSlots.add(FOOT);
//		humanoidSlots.add(SOCK);
//		humanoidSlots.add(ANKLE);
		
		humanoidSlots.add(HORNS);
		humanoidSlots.add(TAIL);
//		humanoidSlots.add(WINGS); //TODO special case, as can be either
		humanoidSlots.add(PENIS);
		humanoidSlots.add(VAGINA);
		
		humanoidSlots.add(PIERCING_EAR);
		humanoidSlots.add(PIERCING_NOSE);
		humanoidSlots.add(PIERCING_TONGUE);
		humanoidSlots.add(PIERCING_LIP);
		humanoidSlots.add(PIERCING_STOMACH);
		humanoidSlots.add(PIERCING_NIPPLE);
//		humanoidSlots.add(PIERCING_VAGINA);
//		humanoidSlots.add(PIERCING_PENIS);
	}

	private InventorySlot(int zLayer, String name, boolean plural, boolean coreClothing, boolean jewellery, String tattooSlotName) {
		this.zLayer = zLayer;
		this.name = name;
		this.plural = plural;
		this.coreClothing = coreClothing;
		this.jewellery = jewellery;
		this.tattooSlotName = tattooSlotName;
	}

	public int getZLayer() {
		return zLayer;
	}

	public String getName() {
		return name;
	}
	
	public String getNameOfAssociatedPart(GameCharacter owner) {
		return UtilText.parse(owner, getNameForParsing(owner));
	}
	
	protected abstract String getNameForParsing(GameCharacter character);

	protected abstract Covering getAssociatedCoveringForTattoo(GameCharacter character);

	public Colour getAssociatedColourForTattoo(GameCharacter character) {
		Covering covering = getAssociatedCoveringForTattoo(character);
		if(covering==null || !isPhysicallyAvailable(character)) {
			return null;
		}
		return covering.getPrimaryColour();
	}
	
	public boolean isPlural(GameCharacter character) {
		return plural;
	}

	public String getTattooSlotName() {
		return tattooSlotName;
	}
	
	public boolean isCoreClothing() {
		return coreClothing;
	}

	public boolean isJewellery() {
		return jewellery;
	}

	public boolean isWeapon() {
		return false;
	}

	public String getPhysicallyUnavailableReason(GameCharacter character) {
		return "";
	}
	
	public boolean isPhysicallyAvailable(GameCharacter character) {
		return getPhysicallyUnavailableReason(character)==null || getPhysicallyUnavailableReason(character).isEmpty();
	}
	
	public static List<InventorySlot> getHumanoidSlots() {
		return new ArrayList<>(humanoidSlots);
	}

	public static List<InventorySlot> getClothingSlots() {
		return new ArrayList<>(clothingSlots);
	}

	public static List<InventorySlot> getPiercingSlots() {
		return new ArrayList<>(piercingSlots);
	}

	public static List<InventorySlot> getCommonTattooSlots() {
		return new ArrayList<>(commonTattooSlots);
	}
	
	public static List<InventorySlot> getMainClothingSlots() {
		return new ArrayList<>(mainClothingSlots);
	}

	public static List<InventorySlot> getExtraClothingSlots() {
		return new ArrayList<>(extraClothingSlots);
	}

	/**
	 * Returns the first applicable BodyPartClothingBlock found from the supplied character's body parts.
	 * 
	 * @param character The character to check.
	 * @return A BodyPartClothingBlock object which represents how this part is being blocked. Returns null if nothing is blocking the slot.
	 */
	public BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character) {
		if (character == null) {
			return null;
		}
//		if(character.getHairRawLengthValue()==0 && this == InventorySlot.HAIR) {
//			return new BodyPartClothingBlock(
//					Util.newArrayListOfValues(InventorySlot.HAIR),
//					character.getHairRace(),
//					UtilText.parse(character, "[npc.Name] [npc.do]n't have any hair, so [npc.she] can't wear any hair accessories!"),
//					Util.newArrayListOfValues());
//		}
		if(!isPhysicallyAvailable(character)) {
			return new BodyPartClothingBlock(
				Util.newArrayListOfValues(this),
				null,
				this.getPhysicallyUnavailableReason(character),
				Util.newArrayListOfValues(this == InventorySlot.HAIR?ItemTag.IGNORE_HAIR_RESTRICTION:null));
		}
		
		// Leg configuration (takes into account feral):
		List<BodyPartClothingBlock> blockedList = character.getLegConfiguration().getBodyPartClothingBlock(character);
		if(blockedList!=null) {
			for(BodyPartClothingBlock block : blockedList) {
				if(block.getBlockedSlots().contains(this)) {
					return block;
				}
			}
		}
		
		for(BodyPartInterface bodypart : character.getBody().getAllBodyParts()) {
			BodyPartClothingBlock block = bodypart.getType().getBodyPartClothingBlock();
			if(block!=null) {
				if(block.getBlockedSlots().contains(this)) {
					return block;
				}
			}
		}
		
		return null;
	}
}
