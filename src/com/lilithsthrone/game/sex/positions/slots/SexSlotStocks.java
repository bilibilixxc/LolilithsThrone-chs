package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;

/**
 * All SexSlots that are used in the STOCKS position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotStocks {

	public static final SexSlot LOCKED_IN_STOCKS = new SexSlot(
			"锁在颈手枷",
			"锁在颈手枷",
			"[npc1.name]无法活动，只能在颈手枷中扭动身躯，发出[npc1.a_moan+]，准备好迎来高潮。",
			false,
			SexSlotTag.LOCKED_IN_STOCKS);
	public static final SexSlot LOCKED_IN_STOCKS_TWO = new SexSlot(LOCKED_IN_STOCKS) {
		@Override
		public String getDescription() {
			return "锁在颈手枷(二)";
		}
	};
	public static final SexSlot LOCKED_IN_STOCKS_THREE = new SexSlot(LOCKED_IN_STOCKS) {
		@Override
		public String getDescription() {
			return "锁在颈手枷(三)";
		}
	};
	public static final SexSlot LOCKED_IN_STOCKS_FOUR = new SexSlot(LOCKED_IN_STOCKS) {
		@Override
		public String getDescription() {
			return "锁在颈手枷(四)";
		}
	};

	
	public static final SexSlot BEHIND_STOCKS = new SexSlot(
			"颈手枷后",
			"颈手枷后",
			"[npc1.Name]猛地挺腰上前，顶进[npc2.namePos]的胯下，伴随着一声[npc1.a_moan+]，[npc1.she]即将迎来高潮。",
			false,
			SexSlotTag.BEHIND_STOCKS);
	public static final SexSlot BEHIND_STOCKS_TWO = new SexSlot(BEHIND_STOCKS) {
		@Override
		public String getDescription() {
			return "颈手枷后(二)";
		}
	};
	public static final SexSlot BEHIND_STOCKS_THREE = new SexSlot(BEHIND_STOCKS) {
		@Override
		public String getDescription() {
			return "颈手枷后(三)";
		}
	};
	public static final SexSlot BEHIND_STOCKS_FOUR = new SexSlot(BEHIND_STOCKS) {
		@Override
		public String getDescription() {
			return "颈手枷后(四)";
		}
	};

	
	public static final SexSlot HUMPING = new SexSlot(
			"趴背",
			"趴背",
			"[npc1.Name]猛地挺腰上前，顶进[npc2.namePos]的胯下，伴随着一声[npc1.a_moan+]，[npc1.she]即将迎来高潮。",
			false,
			SexSlotTag.HUMPING_STOCKS);
	public static final SexSlot HUMPING_TWO = new SexSlot(HUMPING) {
		@Override
		public String getDescription() {
			return "趴背(二)";
		}
	};
	public static final SexSlot HUMPING_THREE = new SexSlot(HUMPING) {
		@Override
		public String getDescription() {
			return "趴背(三)";
		}
	};
	public static final SexSlot HUMPING_FOUR = new SexSlot(HUMPING) {
		@Override
		public String getDescription() {
			return "趴背(四)";
		}
	};
	
	
	public static final SexSlot BENEATH_STOCKS = new SexSlot(
			"四肢跪地",
			"四肢跪地",
			"[npc1.Name]先是抽回腰身，接着又送进[npc2.namePos]的胯下，伴随着[npc1.a_moan+]，[npc1.she]即将迎来高潮。",
			false,
			SexSlotTag.ALL_FOURS);
	public static final SexSlot BENEATH_STOCKS_TWO = new SexSlot(BENEATH_STOCKS) {
		@Override
		public String getDescription() {
			return "四肢跪地(二)";
		}
	};
	public static final SexSlot BENEATH_STOCKS_THREE = new SexSlot(BENEATH_STOCKS) {
		@Override
		public String getDescription() {
			return "四肢跪地(三)";
		}
	};
	public static final SexSlot BENEATH_STOCKS_FOUR = new SexSlot(BENEATH_STOCKS) {
		@Override
		public String getDescription() {
			return "四肢跪地(四)";
		}
	};

	
	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"提供口交",
			"提供口交",
			"[npc1.Name]伸出[npc1.hand]放在[npc2.namePos]的[npc2.legs]上，接着就发出一声[npc1.a_moan+]，[npc1.she]已经准备要迎来高潮了。",
			false,
			SexSlotTag.PERFORMING_ORAL_STOCKS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot PERFORMING_ORAL_TWO = new SexSlot(
			"提供口交",
			"提供口交(二)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_STOCKS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot PERFORMING_ORAL_THREE = new SexSlot(
			"提供口交",
			"提供口交(三)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_STOCKS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot PERFORMING_ORAL_FOUR = new SexSlot(
			"提供口交",
			"提供口交(四)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_STOCKS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	

	public static final SexSlot RECEIVING_ORAL = new SexSlot(
			"接受口交",
			"接受口交",
			"[npc1.Name]伸出[npc1.hand]放在[npc2.namePos]的头上，接着就发出一声[npc1.a_moan+]，[npc1.she]已经准备要迎来高潮了。",
			true,
			SexSlotTag.RECEIVING_ORAL_STOCKS);
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
