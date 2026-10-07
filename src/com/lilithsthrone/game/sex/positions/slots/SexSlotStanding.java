package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;

/**
 * All SexSlots that are used in the STANDING position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotStanding {

	public static final SexSlot STANDING_DOMINANT = new SexSlot(
			"站立",
			"站立",
			"[npc.Name]向前抵在[npc2.namePos]的[npc2.breasts]上，发出[npc.a_moan+]，[npc.she]即将迎来高潮。",
			true,
			SexSlotTag.STANDING) {
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			SexSlot targetedSlot = Main.sex.getSexPositionSlot(targetedCharacter);
			if(orgasmingCharacter.equals(targetedCharacter)) {
				return "[npc.Name]发出了[npc.a_moan+]，即将迎来高潮。";
			}
			if(targetedSlot.hasTag(SexSlotTag.PERFORMING_ORAL)) {
				return "[npc.Name]轻推臀部，将下体压在[npc2.namePos]的[npc2.face]上，随着[npc.a_moan+]，[npc.she]即将迎来高潮。";
			}
			if(targetedSlot.hasTag(SexSlotTag.PERFORMING_ORAL_BEHIND)) {
				return "[npc.Name]稍微一缩腰，之后便在[npc2.namePos]的[npc2.mouth]里抽插起来，随着[npc.a_moan+]，[npc.she]即将迎来高潮。";
			}
			if(targetedSlot.hasTag(SexSlotTag.STANDING_BEHIND)) {
				return "[npc.Name]后倚在[npc2.name]身上，发出[npc.a_moan+]，即将到达高潮。";
			}
			if(targetedCharacter.isTaur()) {
				return "[npc.Name]环抱住了[npc2.namePos]的背部，将[npc2.herHim]拉得更近，伴随着[npc.a_moan+]，[npc.she]马上要迎来高潮。";
			}
			return "[npc.Name]环抱住了[npc2.namePos][npc2.ass+]，将[npc2.herHim]拉的更近，伴随着[npc.a_moan+]，[npc.she]即将迎来高潮。";
		}
	};
	
	public static final SexSlot STANDING_DOMINANT_TWO = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "站立(二)";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return STANDING_DOMINANT.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	public static final SexSlot STANDING_DOMINANT_THREE = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "站立(三)";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return STANDING_DOMINANT.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	public static final SexSlot STANDING_DOMINANT_FOUR = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "站立(四)";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return STANDING_DOMINANT.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};

	public static final SexSlot STANDING_SUBMISSIVE = new SexSlot(
			"站立",
			"站立(在前)",
			"[npc.Name]向前抵在[npc2.namePos]的[npc2.breasts]上，发出[npc.a_moan+]，[npc.she]即将迎来高潮。",
			true);

	public static final SexSlot STANDING_SUBMISSIVE_TWO = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "站立(在前)(二)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_THREE = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "站立(在前)(三)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_FOUR = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "站立(在前)(四)";
		}
	};

	public static final SexSlot STANDING_SUBMISSIVE_BEHIND = new SexSlot(
			"站立",
			"站立(在后)",
			"[npc.Name]将[npc2.name]拉到怀里，紧紧贴着[npc.her]的[npc.breasts]，随后便发出一声[npc.a_moan+]，即将迎来了高潮。",
			true,
			SexSlotTag.STANDING_BEHIND);

	public static final SexSlot STANDING_SUBMISSIVE_BEHIND_TWO = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "站立(在后)(二)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_BEHIND_THREE = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "站立(在后)(三)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_BEHIND_FOUR = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "站立(在后)(四)";
		}
	};

	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"提供口交(在前)",
			"提供口交(在前)",
			"[npc.Name]伸出[npc.hand]放在[npc2.namePos]的[npc2.legs]上，接着就发出一声[npc.a_moan+]，[npc.she]已经准备要迎来高潮了。",
			false,
			SexSlotTag.PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	
	public static final SexSlot PERFORMING_ORAL_TWO = new SexSlot(
			"提供口交(在前)",
			"提供口交(在前)(二)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_THREE = new SexSlot(
			"提供口交(在前)",
			"提供口交(在前)(三)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_FOUR = new SexSlot(
			"提供口交(在前)",
			"提供口交(在前)(四)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND = new SexSlot(
			"提供口交(在后)",
			"提供口交(在后)",
			"[npc.Name]伸出[npc.hand]放在[npc2.namePos]的[npc2.legs]上，接着就发出一声[npc.a_moan+]，[npc.she]已经准备要迎来高潮了。",
			false,
			SexSlotTag.PERFORMING_ORAL_BEHIND) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_TWO = new SexSlot(
			"提供口交(在后)",
			"提供口交(在后)(二)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_BEHIND) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_THREE = new SexSlot(
			"提供口交(在后)",
			"提供口交(在后)(三)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_BEHIND) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_FOUR = new SexSlot(
			"提供口交(在后)",
			"提供口交(在后)(四)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_BEHIND) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
}
