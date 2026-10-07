package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfTailVagina {
	
	public static final SexAction SELF_TAIL_VAGINA_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "尾交(自己)";
		}

		@Override
		public String getActionDescription() {
			return "开始用[npc.tail+]操[npc.her][npc.pussy+]。";
		}

		@Override
		public String getDescription() {
			return (UtilText.returnStringAtRandom(
					"[npc.Name]让[npc.tail]环着[npc.legs]，尾巴尖挑弄着[npc.pussy]入口，愉悦地[npc.moaning]着，贯入自己的身体。",
					"[npc.Name]将[npc.tail]卷在腿间，愉悦地[npc.moaning]，强行突入[npc.her]那诱人的[npc.pussy]。",
					"[npc.tail+]尖尖蹭着被忽视的[npc.pussy]褶皱，突然深贯而入，[npc.name]溢出[npc.a_moan+]，开始自我尾交。",
					"[npc.Name]急切地将[npc.tail+]深深塞进自己欲求不满的[npc.pussy]，迭声[npc.moans+]，开始自我尾交。"));
		}
	};
	
	public static final SexAction DOM_SELF_TAIL_VAGINA_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {

		@Override
		public String getActionTitle() {
			return "尾交(温柔)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地用[npc.tail+]操自己[npc.pussy+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.tail]深入自己[npc.pussy+]，[npc.lips+]间发出[npc.moan]。",
					"[npc.name]用[npc.tail]在自己[npc.pussy+]里温柔地抽插，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己。",
					"[npc.Name]将[npc.tail]缓缓送入[npc.pussy+]深处，操起自己，不自觉地呜咽着。",
					"[npc.Name]沉溺于腿间自我满足的愉悦中，[npc.tail]温柔地在[npc.her][npc.pussy+]内插进抽出。");
		}
		
	};
	
	public static final SexAction DOM_SELF_TAIL_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "尾交(自己)";
		}

		@Override
		public String getActionDescription() {
			return "专心用[npc.tail+]操自己。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.tail]贪婪地深入自己[npc.pussy+]，[npc.lips+]间发出[npc.moan]。",
					"[npc.name]用[npc.tail]在自己[npc.pussy+]里抽插，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己。",
					"[npc.Name]将[npc.tail]送入[npc.pussy]深处，开心地操起自己，不自觉地发出[npc.a_moan]。",
					"[npc.Name]沉溺于腿间自我满足的愉悦中，[npc.tail]在[npc.her][npc.pussy+]内插进抽出。");
		}
	};
	
	public static final SexAction DOM_SELF_TAIL_VAGINA_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "尾交(粗暴)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地用[npc.her][npc.tail+]操自己。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.tail]深入那[npc.pussy+]，[npc.lips+]间发出[npc.moan]，然后粗暴地操起自己。",
					"[npc.name]用[npc.tail]在自己[npc.pussy+]里粗暴地抽插着，开始发出一连串愉悦的[npc.moans]，无情地操起自己来。",
					"[npc.name]将[npc.tail]送入[npc.pussy]深处，开始粗暴地磨蹭着身体内部，不自觉地发出[npc.a_moan]。",
					"[npc.Name]沉溺于腿间自我满足的愉悦中，[npc.tail]粗暴地操弄着[npc.her][npc.pussy+]。");
		}
	};
	
	public static final SexAction SUB_SELF_TAIL_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "尾交(自己)";
		}

		@Override
		public String getActionDescription() {
			return "专心用[npc.tail+]操自己。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.tail]贪婪地深入自己[npc.pussy+]，[npc.lips+]间发出[npc.moan]。",
					"[npc.name]用[npc.tail]在自己[npc.pussy+]里温柔地抽插，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己。",
					"[npc.Name]将[npc.tail]送入[npc.pussy]深处，开心地操起自己，不自觉地发出[npc.a_moan]。",
					"[npc.Name]沉溺于腿间自我满足的愉悦中，[npc.tail]在[npc.her][npc.pussy+]内插进抽出。");
		}
		
	};
	
	public static final SexAction SUB_SELF_TAIL_VAGINA_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "尾交(渴求)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "渴求地用[npc.her][npc.tail+]操自己。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.tail]深入那[npc.pussy+]，[npc.lips+]间发出[npc.moan]，然后狂乱地操起自己。",
					"[npc.name]用[npc.tail]在自己[npc.pussy+]里热情地抽插，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己。",
					"[npc.name]迷乱地将[npc.tail]送入[npc.pussy]深处，开始急切地磨蹭着身体内部，不自觉地发出[npc.a_moan]。",
					"[npc.Name]沉溺于腿间自我满足的愉悦中，[npc.tail]急切地操弄着[npc.her][npc.pussy+]。");
		}
	};
	
	public static final SexAction SELF_TAIL_VAGINA_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "停止尾交(自己)";
		}

		@Override
		public String getActionDescription() {
			return "停止用[npc.her]的[npc.tail]操自己。";
		}

		@Override
		public String getDescription() {
			return "[npc.Name]发出[npc.a_moan]，将[npc.tail+]从自己[npc.pussy+]里抽出来。";
		}
	};
}
