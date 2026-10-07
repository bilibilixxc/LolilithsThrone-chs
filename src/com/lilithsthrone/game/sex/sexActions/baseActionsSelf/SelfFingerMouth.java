package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfFingerMouth {
	
	public static final SexAction SELF_FINGER_MOUTH_LUBRICATION = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					|| (!Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)
							&& Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())
							&& (Main.sex.getForeplayPreference(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))!=null
								&& Main.sex.getForeplayPreference(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)).getPerformingSexArea()==SexAreaPenetration.FINGER));
		}
		
		@Override
		public String getActionTitle() {
			return "润湿手指";
		}

		@Override
		public String getActionDescription() {
			return "你用唾液润滑手指。";
		}

		@Override
		public String getDescription() {
			return "将[npc.name]的[npc.hand]轻举至唇边，[npc.fingers]不经意间划过[npc.lips+]，"
					+ "然后又满面淫荡地吮吸了一会儿，让[npc.her]的唾液将他们充分浸润。";
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH);
		}
	};
	
	public static final SexAction SELF_FINGER_MOUTH_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!= SexPace.SUB_RESISTING;
		}

		@Override
		public String getActionTitle() {
			return "吮吸手指(自己)";
		}

		@Override
		public String getActionDescription() {
			return "将你的[npc.fingers]放入你[npc.mouth]然后开始吮吸。";
		}

		@Override
		public String getDescription() {
			return "将[npc.her]的[npc.hand]轻举至[npc.mouth]边，[npc.name]的[npc.fingers]不经意间划过[npc.her][npc.lips+]，[npc.her]开始淫荡地吮吸起[npc.her]侵入的手指。";
		}
	};
	
	public static final SexAction SELF_FINGER_MOUTH_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "停止吮吸手指(自己)";
		}

		@Override
		public String getActionDescription() {
			return "停止吮吸你的[npc.fingers]。";
		}

		@Override
		public String getDescription() {
			return "伴随着一阵喘息，[npc.name]将沾满唾液的[npc.fingers]从[npc.her][npc.mouth]中滑出。";
		}
	};
	
}
