package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;

/**
 * All SexSlots that are used in the AGAINST_WALL position.
 * 
 * @since 0.3.4
 * @version 0.4
 * @author Innoxia
 */
public class SexSlotAgainstWall {
	
	public static final SexSlot FACE_TO_WALL = new SexSlot(
			"面向[pc.wall]",
			"面向[pc.wall]",
			"[npc1.Name]紧紧贴住面前的[pc.wall]，发出[npc1.a_moan+]，已经准备好迎接高潮。",
			true,
			SexSlotTag.FACE_TO_WALL);
	public static final SexSlot FACE_TO_WALL_TWO = new SexSlot(FACE_TO_WALL) {
		@Override
		public String getDescription() {
			return "面向[pc.wall](二)";
		}
	};
	public static final SexSlot FACE_TO_WALL_THREE = new SexSlot(FACE_TO_WALL) {
		@Override
		public String getDescription() {
			return "面向[pc.wall](三)";
		}
	};
	public static final SexSlot FACE_TO_WALL_FOUR = new SexSlot(FACE_TO_WALL) {
		@Override
		public String getDescription() {
			return "面向[pc.wall](四)";
		}
	};

	public static final SexSlot BACK_TO_WALL = new SexSlot(
			"背靠[pc.wall]",
			"背靠[pc.wall]",
			"[npc.name]紧紧背靠在[pc.wall]上，感觉自己即将要迎来高潮。",
			true,
			SexSlotTag.BACK_TO_WALL);
	public static final SexSlot BACK_TO_WALL_TWO = new SexSlot(BACK_TO_WALL) {
		@Override
		public String getDescription() {
			return "背靠[pc.wall](二)";
		}
	};
	public static final SexSlot BACK_TO_WALL_THREE = new SexSlot(BACK_TO_WALL) {
		@Override
		public String getDescription() {
			return "背靠[pc.wall](三)";
		}
	};
	public static final SexSlot BACK_TO_WALL_FOUR = new SexSlot(BACK_TO_WALL) {
		@Override
		public String getDescription() {
			return "背靠[pc.wall](四)";
		}
	};

	public static final SexSlot STANDING_WALL = new SexSlot(
			"站立",
			"站立",
			"[npc1.name]感受到自己即将迎来高潮，于是[npc1.step]上前，将[npc2.name]紧紧压在[pc.wall]上，开始发出[npc1.a_moan+]。",
			true);
	
	public static final SexSlot STANDING_WALL_TWO = new SexSlot(STANDING_WALL) {
		@Override
		public String getDescription() {
			return "站立(二)";
		}
	};
	public static final SexSlot STANDING_WALL_THREE = new SexSlot(STANDING_WALL) {
		@Override
		public String getDescription() {
			return "站立(三)";
		}
	};
	public static final SexSlot STANDING_WALL_FOUR = new SexSlot(STANDING_WALL) {
		@Override
		public String getDescription() {
			return "站立(四)";
		}
	};

	public static final SexSlot PERFORMING_ORAL_WALL = new SexSlot(
			"提供口交",
			"提供口交",
			"[npc.Name]伸出[npc.hand]放在[npc2.namePos]的[npc2.legs]上，接着就发出一声[npc.a_moan+]，[npc.she]已经准备要迎来高潮了。",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_WALL_TWO = new SexSlot(
			"提供口交",
			"提供口交(二)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_WALL_THREE = new SexSlot(
			"提供口交",
			"提供口交(三)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_WALL_FOUR = new SexSlot(
			"提供口交",
			"提供口交(四)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
}
