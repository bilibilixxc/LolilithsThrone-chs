package com.lilithsthrone.game.sex.positions.slots;

/**
 * All SexSlots that are used in the BREEDING_STALL position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotBreedingStall {

	public static final SexSlot BREEDING_STALL_FRONT = new SexSlot(
			"俯身",
			"俯身",
			"[npc.Name][npc.spreadsHerLegs]，发出[npc.a_moan+]，已经准备迎来高潮。",
			false);

	public static final SexSlot BREEDING_STALL_BACK = new SexSlot(
			"仰身",
			"仰身",
			"[npc.Name][npc.spreadsHerLegs]，发出[npc.a_moan+]，已经准备迎来高潮。",
			false);
	
	public static final SexSlot BREEDING_STALL_FUCKING = new SexSlot(
			"站立",
			"配种",
			"[npc.Name]伸出[npc.hand]抓住[npc2.namePos]的手腕，向前靠近后立刻将[npc.cock]深埋入[npc2.namePos][npc2.pussy+]中。"
				+ "伴随着一声[npc.a_moan+]，[npc.she]已经准备好迎接高潮，在[npc2.namePos]的子宫中注满[npc.cum+]了。",
			true);
}
