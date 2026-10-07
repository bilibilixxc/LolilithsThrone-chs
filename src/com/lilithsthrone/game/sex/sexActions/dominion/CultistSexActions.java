package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionary;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionarySealed;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.88
 * @version 0.3.4
 * @author Innoxia
 */
public class CultistSexActions {
	
	public static final SexAction FORCE_POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()) != SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()) != SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "传教士体位";
		}

		@Override
		public String getActionDescription() {
			return "起身站到[npc2.namePos]的[npc2.legs]之间。";
		}

		@Override
		public String getDescription() {
			return "已经用嘴巴侍奉够了[npc2.name]，[npc.name]站起来并向前#IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF ，把下体贴到[npc2.hers]身上。"
					+ "紧紧抓着[npc2.her][npc2.legs+]，[npc.she]把它们推开，淫笑着[npc.moanVerb]。"
					+ "[npc.speech(是时候来点真正的快乐了！)]";
		}

		@Override
		public void applyEffects() {
			boolean performingSealed = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.WITCH_SEAL);
			boolean targetedSealed = Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.WITCH_SEAL);
			
			if((performingSealed) || (targetedSealed)) {
				Main.sex.setSexManager(new SMAltarMissionarySealed(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))));
				
			} else {
				Main.sex.setSexManager(new SMAltarMissionary(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))));
				
			}
		}
	};
	
	public static final SexAction FORCE_POSITION_MISSIONARY_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()) != SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()) != SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "传教士体位口交";
		}

		@Override
		public String getActionDescription() {
			return "跪下并把脸伸到[npc2.namePos]腿部的前方。";
		}

		@Override
		public String getDescription() {
			return "[npc.name]跪下并把头伸到[npc2.namePos]的[npc2.legs]之间，准备用嘴来侍奉[npc2.herHim]。"
					+ "[npc2.namePos][npc2.scent+]扑面而来，[npc.name][npc.moanVerb]。"
					+ "[npc.speech(哦，事情变得<i>更</i>有意思了！)]";
		}

		@Override
		public void applyEffects() {
			boolean performingSealed = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.WITCH_SEAL);
			boolean targetedSealed = Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.WITCH_SEAL);
			
			if((performingSealed) || (targetedSealed)) {
				Main.sex.setSexManager(new SMAltarMissionarySealed(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))));
				
			} else {
				Main.sex.setSexManager(new SMAltarMissionary(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))));
			}
		}
	};
	
}
