package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
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
public class SelfTailMouth {
	public static final SexAction SELF_TAIL_MOUTH_LUBRICATION = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					|| (!Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL)
							&& Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())
							&& (Main.sex.getForeplayPreference(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))!=null
								&& Main.sex.getForeplayPreference(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)).getPerformingSexArea()==SexAreaPenetration.TAIL));
		}
		
		@Override
		public String getActionTitle() {
			return "润湿尾巴";
		}

		@Override
		public String getActionDescription() {
			return "用你的口水润湿尾巴。";
		}

		@Override
		public String getDescription() {
			return "[npc.name]将[npc.her][npc.tail+]举至嘴边，[npc.tail+]轻轻滑过[npc.lips+]，"
					+ "接着一脸淫荡地吮吸了起来，[npc.her]的唾液沾染其上，变得又湿又滑。";
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH);
		}
	};
	
	public static final SexAction PARTNER_SELF_TAIL_MOUTH_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "吮吸自己的尾巴";
		}

		@Override
		public String getActionDescription() {
			return "将你的[pc.tail]塞进嘴里，吮吸起来。";
		}

		@Override
		public String getDescription() {
			return "[npc.name]将[npc.her]的[npc.tail]举至嘴边，[npc.tail+]尖儿从[npc.her][npc.lips+]边扫过，而后便贪婪地吮吸起来。";
		}
	};
	
	public static final SexAction SELF_FINGER_MOUTH_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "停止吮吸尾巴(自己)";
		}

		@Override
		public String getActionDescription() {
			return "停止吮吸你的[pc.tail]。";
		}

		@Override
		public String getDescription() {
			return "伴随着一声声娇喘，[npc.name]的津液伴随着[npc.tail]的进出从口中滴落。";
		}
	};
}
