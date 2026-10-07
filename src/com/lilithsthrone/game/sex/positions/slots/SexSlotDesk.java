package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * All SexSlots that are used in the OVER_DESK position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotDesk {

	public static final SexSlot OVER_DESK_ON_BACK = new SexSlot(
			"平躺",
			"平躺",
			"[npc.Name]躺倒在[pc.desk]上，接着冒出[npc.a_moan+]，即将迎来高潮了。",
			false,
			SexSlotTag.OVER_DESK_BACK);
	public static final SexSlot OVER_DESK_ON_BACK_TWO = new SexSlot(OVER_DESK_ON_BACK) {
		@Override
		public String getDescription() {
			return "平躺(二)";
		}
	};
	public static final SexSlot OVER_DESK_ON_BACK_THREE = new SexSlot(OVER_DESK_ON_BACK) {
		@Override
		public String getDescription() {
			return "平躺(三)";
		}
	};
	public static final SexSlot OVER_DESK_ON_BACK_FOUR = new SexSlot(OVER_DESK_ON_BACK) {
		@Override
		public String getDescription() {
			return "平躺(四)";
		}
	};

	
	public static final SexSlot OVER_DESK_ON_FRONT = new SexSlot(
			"屈身",
			"屈身",
			"[npc.Name]趴倒在[pc.desk]上，接着冒出[npc.a_moan+]，即将迎来高潮了。",
			false,
			SexSlotTag.OVER_DESK_FRONT);
	public static final SexSlot OVER_DESK_ON_FRONT_TWO = new SexSlot(OVER_DESK_ON_FRONT) {
		@Override
		public String getDescription() {
			return "屈身(二)";
		}
	};
	public static final SexSlot OVER_DESK_ON_FRONT_THREE = new SexSlot(OVER_DESK_ON_FRONT) {
		@Override
		public String getDescription() {
			return "屈身(三)";
		}
	};
	public static final SexSlot OVER_DESK_ON_FRONT_FOUR = new SexSlot(OVER_DESK_ON_FRONT) {
		@Override
		public String getDescription() {
			return "屈身(四)";
		}
	};

	
	public static final SexSlot BETWEEN_LEGS = new SexSlot(
			"腿间",
			"腿间",
			null,
			true,
			SexSlotTag.OVER_DESK_BETWEEN_LEGS);
	public static final SexSlot BETWEEN_LEGS_TWO = new SexSlot(BETWEEN_LEGS) {
		@Override
		public String getDescription() {
			return "腿间(二)";
		}
	};
	public static final SexSlot BETWEEN_LEGS_THREE = new SexSlot(BETWEEN_LEGS) {
		@Override
		public String getDescription() {
			return "腿间(三)";
		}
	};
	public static final SexSlot BETWEEN_LEGS_FOUR = new SexSlot(BETWEEN_LEGS) {
		@Override
		public String getDescription() {
			return "腿间(四)";
		}
	};

	public static final SexSlot HUMPING = new SexSlot(
			"趴背",
			"趴背",
			"[npc.Name]粗暴地顶向[npc2.name]，接着冒出[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(OVER_DESK_ON_FRONT);
			if(partner==null) { partner = getCharacterInSlot(OVER_DESK_ON_BACK); }
			if(partner!=null) { return "趴在"+UtilText.parse(partner, "[npc.name]")+"的背上"; }
			return "趴背";
		}
	};
	public static final SexSlot HUMPING_TWO = new SexSlot(
			"趴背",
			"趴背(二)",
			"[npc.Name]粗暴地顶向[npc2.name]，接着冒出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(OVER_DESK_ON_FRONT_TWO);
			if(partner==null) { partner = getCharacterInSlot(OVER_DESK_ON_BACK_TWO); }
			if(partner!=null) { return "趴在"+UtilText.parse(partner, "[npc.name]")+"的背上"; }
			return "趴背";
		}
	};
	public static final SexSlot HUMPING_THREE = new SexSlot(
			"趴背",
			"趴背(三)",
			"[npc.Name]粗暴地顶向[npc2.name]，接着冒出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(OVER_DESK_ON_FRONT_THREE);
			if(partner==null) { partner = getCharacterInSlot(OVER_DESK_ON_BACK_THREE); }
			if(partner!=null) { return "趴在"+UtilText.parse(partner, "[npc.name]")+"的背上"; }
			return "趴背";
		}
	};
	public static final SexSlot HUMPING_FOUR = new SexSlot(
			"趴背",
			"趴背(四)",
			"[npc.Name]粗暴地顶向[npc2.name]，接着冒出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(OVER_DESK_ON_FRONT_FOUR);
			if(partner==null) { partner = getCharacterInSlot(OVER_DESK_ON_BACK_FOUR); }
			if(partner!=null) { return "趴在"+UtilText.parse(partner, "[npc.name]")+"的背上"; }
			return "趴背";
		}
	};
	
	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"提供口交",
			"提供口交",
			"[npc.Name]低头凑近[npc2.namePos]的股间，发出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return target.getHeightValue()<=80; // Assuming a desk is 70cm high, a character stands to perform oral if they're 80cm or shorter
		}
	};
	public static final SexSlot PERFORMING_ORAL_TWO = new SexSlot(
			"提供口交",
			"提供口交(二)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return target.getHeightValue()<=80; // Assuming a desk is 70cm high, a character stands to perform oral if they're 80cm or shorter
		}
	};
	public static final SexSlot PERFORMING_ORAL_THREE = new SexSlot(
			"提供口交",
			"提供口交(三)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return target.getHeightValue()<=80; // Assuming a desk is 70cm high, a character stands to perform oral if they're 80cm or shorter
		}
	};
	public static final SexSlot PERFORMING_ORAL_FOUR = new SexSlot(
			"提供口交",
			"提供口交(四)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return target.getHeightValue()<=80; // Assuming a desk is 70cm high, a character stands to perform oral if they're 80cm or shorter
		}
	};
	

	public static final SexSlot RECEIVING_ORAL = new SexSlot(
			"接受口交",
			"接受口交",
			"[npc.Name]紧紧地贴住[npc2.name]的[npc2.face]，接着冒出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			true);
	public static final SexSlot RECEIVING_ORAL_TWO = new SexSlot(RECEIVING_ORAL) {
		@Override
		public String getDescription() {
			return "接受口交(二)";
		}
	};
	public static final SexSlot RECEIVING_ORAL_THREE =  new SexSlot(RECEIVING_ORAL) {
		@Override
		public String getDescription() {
			return "接受口交(三)";
		}
	};
	public static final SexSlot RECEIVING_ORAL_FOUR =  new SexSlot(RECEIVING_ORAL) {
		@Override
		public String getDescription() {
			return "接受口交(四)";
		}
	};
}
