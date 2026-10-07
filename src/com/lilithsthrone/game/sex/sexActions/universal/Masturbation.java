package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.4
 * @author Innoxia
 */
public class Masturbation {
	
	public static final SexAction SWITCH_TO_STANDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return SexPosition.MASTURBATION.isSlotUnlocked(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.STANDING, Main.sex.getAllOccupiedSlots(true)).getKey()
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.KNEELING_PANTIES
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.STANDING;
		}
		@Override
		public String getActionTitle() {
			return "站立式自慰";
		}
		@Override
		public String getActionDescription() {
			return "决定站起来并以站姿继续自慰";
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "[npc.name]站了起来，认为以站姿继续自慰是更好的。"
						+ "然后将[npc.her]的[npc.hands]放到[npc.her]的[npc.legs]之间，准备继续[npc.she]之前的动作……";
			} else {
				return "[npc.name]站了起来，认为以站姿继续自慰是更好的。"
						+ "[npc.her]扭过头，看着[npc.legRace]兽态的身体并发出一声沮丧的呜咽……";
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.MASTURBATION,
					Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.STANDING))
						:null,
					!Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.STANDING))
						:null){
			});
		}
	};
	
	public static final SexAction SWITCH_TO_SITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return SexPosition.MASTURBATION.isSlotUnlocked(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.SITTING, Main.sex.getAllOccupiedSlots(true)).getKey()
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.KNEELING_PANTIES
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.SITTING;
		}
		@Override
		public String getActionTitle() {
			return "坐姿自慰";
		}
		@Override
		public String getActionDescription() {
			return "决定坐在附近的地面上并继续用这种姿势自慰。";
		}
		@Override
		public String getDescription() {
			return "[npc.name]决定还是以坐姿继续自慰，于是在附近找了个合适的地方，"
					+ "然后坐下并将[npc.her]的[npc.hands]移到了[npc.her]的[npc.legs]之间……";
		}
		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.MASTURBATION,
					Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.SITTING))
						:null,
					!Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.SITTING))
						:null){
			});
		}
	};

	public static final SexAction SWITCH_TO_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return SexPosition.MASTURBATION.isSlotUnlocked(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.KNEELING, Main.sex.getAllOccupiedSlots(true)).getKey()
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.KNEELING_PANTIES
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.KNEELING;
		}
		@Override
		public String getActionTitle() {
			return "跪姿自慰";
		}
		@Override
		public String getActionDescription() {
			return "决定跪下并以跪姿继续自慰。";
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "[npc.name]决定还是以跪姿继续自慰比较好，于是就以这样的姿势跪了下来，"
						+ "然后将[npc.her]的[npc.hands]滑入[npc.her]的[npc.legs]之间，准备继续[npc.she]之前的动作……";
			} else {
				return "[npc.name]决定还是以跪姿继续自慰比较好，于是就以这样的姿势跪了下来，"
						+ "[npc.her]扭过头，看着[npc.legRace]兽态的身体并发出一声沮丧的呜咽……";
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.MASTURBATION,
					Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.KNEELING))
						:null,
					!Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.KNEELING))
						:null){
			});
		}
	};
	
}
