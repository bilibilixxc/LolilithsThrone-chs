package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;

/**
 * All SexSlots that are used in the SITTING position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotSitting {

	public static final SexSlot SITTING = new SexSlot(
			"坐下",
			"坐下",
			"[npc.Name]微微一挺腰，发出[npc.a_moan+]，即将迎来高潮。",
			false,
			SexSlotTag.SITTING);
	public static final SexSlot SITTING_TWO = new SexSlot(SITTING){
		@Override
		public String getDescription() {
			return "坐下(二)";
		}
	};
	public static final SexSlot SITTING_THREE = new SexSlot(SITTING){
		@Override
		public String getDescription() {
			return "坐下(三)";
		}
	};
	public static final SexSlot SITTING_FOUR = new SexSlot(SITTING){
		@Override
		public String getDescription() {
			return "坐下(四)";
		}
	};
	
	
	public static final SexSlot SITTING_IN_LAP = new SexSlot(
			"坐在腿上",
			"坐在腿上",
			"[npc.Name]的[npc.legs]颤抖着，深深地坐进[npc2.namePos]的大腿之间，随着一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.SITTING_IN_LAP);
	public static final SexSlot SITTING_IN_LAP_TWO = new SexSlot(SITTING_IN_LAP){
		@Override
		public String getDescription() {
			return "坐在腿上(二)";
		}
	};
	public static final SexSlot SITTING_IN_LAP_THREE = new SexSlot(SITTING_IN_LAP){
		@Override
		public String getDescription() {
			return "坐在腿上(三)";
		}
	};
	public static final SexSlot SITTING_IN_LAP_FOUR = new SexSlot(SITTING_IN_LAP){
		@Override
		public String getDescription() {
			return "坐在腿上(四)";
		}
	};
	
	
	public static final SexSlot SITTING_BETWEEN_LEGS = new SexSlot(
			"腿间",
			"腿间",
			"[npc.Name]前倾身子靠在[npc2.name]身上，发出一声[npc.a_moan+]，即将迎来高潮。",
			false,
			SexSlotTag.SITTING_BETWEEN_LEGS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(SITTING);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot SITTING_BETWEEN_LEGS_TWO = new SexSlot(
			"腿间",
			"腿间(二)",
			"[npc.Name]前倾身子靠在[npc2.name]身上，发出一声[npc.a_moan+]，即将迎来高潮。",
			false,
			SexSlotTag.SITTING_BETWEEN_LEGS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(SITTING_TWO);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot SITTING_BETWEEN_LEGS_THREE = new SexSlot(
			"腿间",
			"腿间(三)",
			"[npc.Name]前倾身子靠在[npc2.name]身上，发出一声[npc.a_moan+]，即将迎来高潮。",
			false,
			SexSlotTag.SITTING_BETWEEN_LEGS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(SITTING_THREE);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot SITTING_BETWEEN_LEGS_FOUR = new SexSlot(
			"腿间",
			"腿间(四)",
			"[npc.Name]前倾身子靠在[npc2.name]身上，发出一声[npc.a_moan+]，即将迎来高潮。",
			false,
			SexSlotTag.SITTING_BETWEEN_LEGS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(SITTING_FOUR);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"提供口交",
			"提供口交",
			"[npc.Name]伸出[npc.hand]放在[npc2.namePos]的[npc2.legs]上，接着发出一声[npc.a_moan+]，[npc.she]已经准备要迎来高潮了。",
			false,SexSlotTag.SITTING_PERFORMING_ORAL) {
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
			SexSlotTag.SITTING_PERFORMING_ORAL) {
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
			SexSlotTag.SITTING_PERFORMING_ORAL) {
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
			SexSlotTag.SITTING_PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL = new SexSlot(
			"接受口交",
			"接受口交",
			"[npc.Name]的[npc.legs]颤抖着，将下体紧紧地顶在[npc2.namePos]脸上，随着一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.SITTING_TAUR_PRESENTING_ORAL);
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL_TWO = new SexSlot(SITTING_TAUR_PRESENTING_ORAL){
		@Override
		public String getDescription() {
			return "接受口交(二)";
		}
	};
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL_THREE = new SexSlot(SITTING_TAUR_PRESENTING_ORAL){
		@Override
		public String getDescription() {
			return "接受口交(三)";
		}
	};
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL_FOUR = new SexSlot(SITTING_TAUR_PRESENTING_ORAL){
		@Override
		public String getDescription() {
			return "接受口交(四)";
		}
	};
	
}
