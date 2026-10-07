package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;

/**
 * All SexSlots that are used in the LYING_DOWN position.
 * 
 * @since 0.3.4
 * @version 0.4.11.2
 * @author Innoxia
 */
public class SexSlotLyingDown {

	public static final SexSlot LYING_DOWN = new SexSlot(
			"平躺",
			"平躺",
			"[npc.Name]一挺腰，发出[npc.a_moan+]，即将迎来高潮。",
			false,
			SexSlotTag.LYING_DOWN);
	public static final SexSlot LYING_DOWN_TWO = new SexSlot(LYING_DOWN) {
		@Override
		public String getDescription() {
			return "平躺(二)";
		}
	};
	public static final SexSlot LYING_DOWN_THREE = new SexSlot(LYING_DOWN) {
		@Override
		public String getDescription() {
			return "平躺(三)";
		}
	};
	public static final SexSlot LYING_DOWN_FOUR = new SexSlot(LYING_DOWN) {
		@Override
		public String getDescription() {
			return "平躺(四)";
		}
	};

	public static final SexSlot LYING_DOWN_FRONT = new SexSlot(
			"俯卧",
			"俯卧",
			"[npc.name]保持着俯卧姿势，发出一声[npc.a_moan+]，即将迎来高潮。",
			false,
			SexSlotTag.LYING_DOWN_ON_FRONT);
	public static final SexSlot LYING_DOWN_FRONT_TWO = new SexSlot(LYING_DOWN_FRONT) {
		@Override
		public String getDescription() {
			return "俯卧(二)";
		}
	};
	public static final SexSlot LYING_DOWN_FRONT_THREE = new SexSlot(LYING_DOWN_FRONT) {
		@Override
		public String getDescription() {
			return "俯卧(三)";
		}
	};
	public static final SexSlot LYING_DOWN_FRONT_FOUR = new SexSlot(LYING_DOWN_FRONT) {
		@Override
		public String getDescription() {
			return "俯卧(四)";
		}
	};
	
	
	public static final SexSlot COWGIRL = new SexSlot(
			"骑乘位",
			"骑乘位",
			"[npc.Name]的[npc.legs]颤抖着，深深坐进了[npc2.namePos]的胯下，发出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.COWGIRL);
	public static final SexSlot COWGIRL_TWO = new SexSlot(COWGIRL) {
		@Override
		public String getDescription() {
			return "骑乘位(二)";
		}
	};
	public static final SexSlot COWGIRL_THREE = new SexSlot(COWGIRL) {
		@Override
		public String getDescription() {
			return "骑乘位(三)";
		}
	};
	public static final SexSlot COWGIRL_FOUR = new SexSlot(COWGIRL) {
		@Override
		public String getDescription() {
			return "骑乘位(四)";
		}
	};

	
	
	public static final SexSlot COWGIRL_REVERSE = new SexSlot(
			"反骑乘",
			"反骑乘",
			"[npc.Name]的[npc.legs]颤抖着，深深坐进了[npc2.namePos]的胯下，发出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.COWGIRL_REVERSE);
	public static final SexSlot COWGIRL_REVERSE_TWO = new SexSlot(COWGIRL_REVERSE) {
		@Override
		public String getDescription() {
			return "反骑乘(二)";
		}
	};
	public static final SexSlot COWGIRL_REVERSE_THREE = new SexSlot(COWGIRL_REVERSE) {
		@Override
		public String getDescription() {
			return "反骑乘(三)";
		}
	};
	public static final SexSlot COWGIRL_REVERSE_FOUR = new SexSlot(COWGIRL_REVERSE) {
		@Override
		public String getDescription() {
			return "反骑乘(四)";
		}
	};

	
	/** Sitting looking down into the person's eyes. */
	public static final SexSlot FACE_SITTING = new SexSlot(
			"正向颜面骑乘",
			"颜面骑乘",
			"[npc.Name]的[npc.legs]颤抖着，深深地坐在[npc2.namePos]的脸上，发出[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.FACE_SITTING);

	public static final SexSlot FACE_SITTING_TWO = new SexSlot(
			"正向颜面骑乘",
			"颜面骑乘(二)",
			"[npc.Name]的[npc.legs]颤抖着，深深地坐在[npc2.namePos]的脸上，发出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.FACE_SITTING);

	public static final SexSlot FACE_SITTING_THREE = new SexSlot(
			"正向颜面骑乘",
			"颜面骑乘(三)",
			"[npc.Name]的[npc.legs]颤抖着，深深地坐在[npc2.namePos]的脸上，发出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.FACE_SITTING);

	public static final SexSlot FACE_SITTING_FOUR = new SexSlot(
			"正向颜面骑乘",
			"颜面骑乘(四)",
			"[npc.Name]的[npc.legs]颤抖着，深深地坐在[npc2.namePos]的脸上，发出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.FACE_SITTING);

	/** Sitting looking down at the person's lower body. */
	public static final SexSlot FACE_SITTING_REVERSE = new SexSlot(
			"反向颜面骑乘",
			"反向颜面骑乘",
			"[npc.Name]的[npc.legs]颤抖着，深深地坐在[npc2.namePos]的脸上，发出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.FACE_SITTING_REVERSE);

	public static final SexSlot FACE_SITTING_REVERSE_TWO = new SexSlot(
			"反向颜面骑乘",
			"反向颜面骑乘(二)",
			"[npc.Name]的[npc.legs]颤抖着，深深地坐在[npc2.namePos]的脸上，发出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.FACE_SITTING_REVERSE);

	public static final SexSlot FACE_SITTING_REVERSE_THREE = new SexSlot(
			"反向颜面骑乘",
			"反向颜面骑乘(三)",
			"[npc.Name]的[npc.legs]颤抖着，深深地坐在[npc2.namePos]的脸上，发出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.FACE_SITTING_REVERSE);

	public static final SexSlot FACE_SITTING_REVERSE_FOUR = new SexSlot(
			"反向颜面骑乘",
			"反向颜面骑乘(四)",
			"[npc.Name]的[npc.legs]颤抖着，深深地坐在[npc2.namePos]的脸上，发出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.FACE_SITTING_REVERSE);

	/** Oral slots for if the targeted character is lying down on their front. */
	public static final SexSlot RECEIVING_ORAL = new SexSlot(
			"身前",
			"身前",
			"[npc.Name]将下体顶在[npc2.namePos]的脸上，随着一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.RECEIVING_ORAL_FROM_PRONE_PARTNER);
	public static final SexSlot RECEIVING_ORAL_TWO = new SexSlot(
			"身前",
			"身前(二)",
			"[npc.Name]将下体顶在[npc2.namePos]的脸上，随着一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.RECEIVING_ORAL_FROM_PRONE_PARTNER);
	public static final SexSlot RECEIVING_ORAL_THREE = new SexSlot(
			"身前",
			"身前(三)",
			"[npc.Name]将下体顶在[npc2.namePos]的脸上，随着一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.RECEIVING_ORAL_FROM_PRONE_PARTNER);
	public static final SexSlot RECEIVING_ORAL_FOUR = new SexSlot(
			"身前",
			"身前(四)",
			"[npc.Name]将下体顶在[npc2.namePos]的脸上，随着一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.RECEIVING_ORAL_FROM_PRONE_PARTNER);
	
	
	public static final SexSlot LAP_PILLOW = new SexSlot(
			"膝枕",
			"膝枕",
			"[npc.Name]望向[npc2.namePos]的[npc2.eyes]，口中冒出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.LAP_PILLOW);

	public static final SexSlot LAP_PILLOW_TWO = new SexSlot(
			"膝枕",
			"膝枕(二)",
			"[npc.Name]望向[npc2.namePos]的[npc2.eyes]，口中冒出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.LAP_PILLOW);

	public static final SexSlot LAP_PILLOW_THREE = new SexSlot(
			"膝枕",
			"膝枕(三)",
			"[npc.Name]望向[npc2.namePos]的[npc2.eyes]，口中冒出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.LAP_PILLOW);

	public static final SexSlot LAP_PILLOW_FOUR = new SexSlot(
			"膝枕",
			"膝枕(四)",
			"[npc.Name]望向[npc2.namePos]的[npc2.eyes]，口中冒出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.LAP_PILLOW);
	
	
	

	public static final SexSlot SIXTY_NINE = new SexSlot(
			"六九式",
			"六九式",
			"[npc.Name]的[npc.legs]颤抖着，将下体紧紧地压在[npc2.namePos]的脸上，发出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.SIXTY_NINE);
	public static final SexSlot SIXTY_NINE_TWO = new SexSlot(SIXTY_NINE) {
		@Override
		public String getDescription() {
			return "六九式(二)";
		}
	};
	public static final SexSlot SIXTY_NINE_THREE = new SexSlot(SIXTY_NINE) {
		@Override
		public String getDescription() {
			return "六九式(三)";
		}
	};
	public static final SexSlot SIXTY_NINE_FOUR = new SexSlot(SIXTY_NINE) {
		@Override
		public String getDescription() {
			return "六九式(四)";
		}
	};
	
	
	
	public static final SexSlot MISSIONARY = new SexSlot(
			"传教士体位",
			"腿间",
			"[npc.Name]即将迎来高潮，口中冒出一声[npc.a_moan+]，重重地压在[npc2.name]身上，淫靡地盯着[npc2.her][npc2.eyes+]。",
			false,
			SexSlotTag.MISSIONARY) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(LYING_DOWN_FRONT);
			if(partner==null) {
				return super.getName(target);
			}
			return "俯卧位";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			if(orgasmingCharacter.equals(targetedCharacter) || !Main.game.isInSex()) {
				return super.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
			}
			if(Main.sex.getSexPositionSlot(targetedCharacter).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)) {
				return "[npc.Name]即将迎来高潮，口中冒出一声[npc.a_moan+]，重重地压在[npc2.name]身上，低头凝视着[npc2.her]的后脑勺。";
			}
			return super.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	public static final SexSlot MISSIONARY_TWO = new SexSlot(MISSIONARY) {
		@Override
		public String getDescription() {
			return "腿间(二)";
		}
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(LYING_DOWN_FRONT_TWO);
			if(partner==null) {
				return super.getName(target);
			}
			return "俯卧位";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return MISSIONARY.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	public static final SexSlot MISSIONARY_THREE = new SexSlot(MISSIONARY) {
		@Override
		public String getDescription() {
			return "腿间(三)";
		}
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(LYING_DOWN_FRONT_THREE);
			if(partner==null) {
				return super.getName(target);
			}
			return "俯卧位";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return MISSIONARY.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	public static final SexSlot MISSIONARY_FOUR = new SexSlot(MISSIONARY) {
		@Override
		public String getDescription() {
			return "腿间(四)";
		}
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(LYING_DOWN_FRONT_FOUR);
			if(partner==null) {
				return super.getName(target);
			}
			return "俯卧位";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return MISSIONARY.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	
	public static final SexSlot MISSIONARY_ORAL = new SexSlot(
			"传教士体位口交",
			"脸位于腿间",
			"[npc.Name]即将迎来高潮，口中冒出一声[npc.a_moan+]，将[npc.face]深深埋入[npc2.namePos]胯下。",
			false,
			SexSlotTag.MISSIONARY_ORAL);
	public static final SexSlot MISSIONARY_ORAL_TWO = new SexSlot(MISSIONARY_ORAL) {
		@Override
		public String getDescription() {
			return "脸位于腿间(二)";
		}
	};
	public static final SexSlot MISSIONARY_ORAL_THREE = new SexSlot(MISSIONARY_ORAL) {
		@Override
		public String getDescription() {
			return "脸位于腿间(三)";
		}
	};
	public static final SexSlot MISSIONARY_ORAL_FOUR = new SexSlot(MISSIONARY_ORAL) {
		@Override
		public String getDescription() {
			return "脸位于腿间(四)";
		}
	};

	
	
	public static final SexSlot MATING_PRESS = new SexSlot(
			"授种式",
			"授种式",
			"[npc.Name]低头看向[npc2.namePos]的[npc2.eyes]，随后就将全身的重量压在[npc2.herHim]身上，伴随着一声[npc1.a_moan+]，[npc1.she]即将迎来高潮。",
			false,
			SexSlotTag.MATING_PRESS);

	public static final SexSlot MATING_PRESS_TWO = new SexSlot(
			"授种式",
			"授种式(二)",
			"[npc.Name]低头看向[npc2.namePos]的[npc2.eyes]，随后就将全身的重量压在[npc2.herHim]身上，伴随着一声[npc1.a_moan+]，[npc1.she]即将迎来高潮。",
			false,
			SexSlotTag.MATING_PRESS);

	public static final SexSlot MATING_PRESS_THREE = new SexSlot(
			"授种式",
			"授种式(三)",
			"[npc.Name]低头看向[npc2.namePos]的[npc2.eyes]，随后就将全身的重量压在[npc2.herHim]身上，伴随着一声[npc1.a_moan+]，[npc1.she]即将迎来高潮。",
			false,
			SexSlotTag.MATING_PRESS);

	public static final SexSlot MATING_PRESS_FOUR = new SexSlot(
			"授种式",
			"授种式(四)",
			"[npc.Name]低头看向[npc2.namePos]的[npc2.eyes]，随后就将全身的重量压在[npc2.herHim]身上，伴随着一声[npc1.a_moan+]，[npc1.she]即将迎来高潮。",
			false,
			SexSlotTag.MATING_PRESS);
	
	

	public static final SexSlot SCISSORING = new SexSlot(
			"剪刀式体位",
			"剪刀式体位",
			"[npc.Name]迫不及待地挺腰送入[npc2.nameHers]的胯下，发出一声[npc1.a_moan+]，即将迎来了高潮。",
			false,
			SexSlotTag.SCISSORING);

	public static final SexSlot SCISSORING_TWO = new SexSlot(
			"剪刀式体位",
			"剪刀式体位(二)",
			"[npc.Name]迫不及待地挺腰送入[npc2.nameHers]的胯下，发出一声[npc1.a_moan+]，即将迎来了高潮。",
			false,
			SexSlotTag.SCISSORING);

	public static final SexSlot SCISSORING_THREE = new SexSlot(
			"剪刀式体位",
			"剪刀式体位(三)",
			"[npc.Name]迫不及待地挺腰送入[npc2.nameHers]的胯下，发出一声[npc1.a_moan+]，即将迎来了高潮。",
			false,
			SexSlotTag.SCISSORING);

	public static final SexSlot SCISSORING_FOUR = new SexSlot(
			"剪刀式体位",
			"剪刀式体位(四)",
			"[npc.Name]迫不及待地挺腰送入[npc2.nameHers]的胯下，发出一声[npc1.a_moan+]，即将迎来了高潮。",
			false,
			SexSlotTag.SCISSORING);
	
	

	public static final SexSlot BESIDE = new SexSlot(
			"侧躺在身边",
			"侧躺在身边",
			"[npc.Name]一挺腰，发出[npc.a_moan+]，即将迎来高潮。",
			false); // SexSlotTag.LYING_DOWN Removed in v0.4.11.2
	public static final SexSlot BESIDE_TWO = new SexSlot(BESIDE) {
		@Override
		public String getDescription() {
			return "侧躺在身边(二)";
		}
	};
	public static final SexSlot BESIDE_THREE = new SexSlot(BESIDE) {
		@Override
		public String getDescription() {
			return "侧躺在身边(三)";
		}
	};
	public static final SexSlot BESIDE_FOUR = new SexSlot(BESIDE) {
		@Override
		public String getDescription() {
			return "侧躺在身边(四)";
		}
	};
}
