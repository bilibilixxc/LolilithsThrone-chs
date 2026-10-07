package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.3
 * @version 0.3.3
 * @author Innoxia
 */
public class ClitClit {
	
	public static final SexAction TRIBBING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "开始磨镜";
		}

		@Override
		public String getActionDescription() {
			return "开始将你[npc.clit+]在[npc2.namePos][npc2.vagina+]上反复摩擦。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"随着[npc.her]缓缓推进[npc.hips+]，[npc.name]把[npc.her][npc.clit+]压在[npc2.namePos][npc2.pussy+]上，然后开始在[npc2.her][npc2.labia+]上有节奏地上下摩擦。",
							"随着[npc.a_moan+]，[npc.name]慢慢的按压[npc.her]的[npc2.namePos][npc2.legs]间的位置，准备将[npc.her][npc.clit+]在[npc2.her][npc2.pussy+]上反复摩擦。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]撅起结实[npc.hips+]，将[npc.clit+]猛贴向[npc2.namePos][npc2.pussy+]，在[npc2.labia+]处上下摩擦。",
							"[npc.name]发出[npc.a_moan+]，粗暴地将下体撞向[npc2.legs]间，猛烈地用[npc.clit+]上下摩擦着[npc2.pussy+]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]饥渴地扭动着[npc.hips+]，将[npc.clit+]贴向[npc2.namePos][npc2.pussy+]，热情摩擦着[npc2.labia+]。",
							"[npc.name]发出[npc.a_moan+]，饥渴地将下体压向[npc2.namePos][npc2.legs]间，疯狂地用[npc.clit+]上下摩擦着[npc2.pussy+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]扭动着[npc.hips+]，将[npc.clit+]贴向[npc2.namePos][npc2.pussy+]，上下摩擦着[npc2.labia+]。",
							"[npc.name]发出[npc.a_moan+]，将下体压向[npc2.namePos][npc2.legs]间，用[npc.clit+]上下摩擦着[npc2.pussy+]。"));
					break;
				case SUB_RESISTING:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]轻轻摇晃起[npc2.hips]作为回应，"
										+ "[npc2.she]模仿着[npc.name]的动作，用[npc2.clit+]摩擦着对方[npc.pussy+]，[npc2.a_moan+]从口中溢出。",
								"作为回应，[npc2.name]开始模仿[npc.namePos]的动作，温柔地扭动着自己的[npc2.hips]，轻轻地用[npc2.her][npc2.clit+]来回磨蹭着[npc.her][npc.labia+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]粗暴地扭动着自己的[npc2.hips]作为回应，"
										+ "[npc2.she]模仿着[npc.name]的动作，用[npc2.clit+]激烈地摩擦着对方[npc.pussy+]，[npc2.a_moan+]从口中溢出。",
								"作为回应，[npc2.name]开始模仿[npc.namePos]的动作，猛烈地扭动着自己的[npc2.hips]，粗暴地用[npc2.her][npc2.clit+]来回磨蹭着[npc.her][npc.labia+]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]开心地摆动着自己的[npc2.hips]作为回应，"
										+ "[npc2.she]模仿着[npc.name]的动作，用[npc2.her][npc2.clit+]轻快地摩擦着对方[npc.pussy+]，[npc2.a_moan+]从口中溢出。",
								"作为回应，[npc2.name]开始模仿[npc.namePos]的动作，快乐地扭动着自己的[npc2.hips]，狂乱地用[npc2.her][npc2.clit+]来回磨蹭着[npc.her][npc.labia+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]摆动着自己的[npc2.hips]作为回应，"
										+ "[npc2.she]模仿着[npc.name]的动作，用[npc2.her][npc2.clit+]摩擦着对方[npc.pussy+]，[npc2.a_moan+]从口中溢出。",
								"作为回应，[npc2.name]开始模仿[npc.namePos]的动作，扭动着自己的[npc2.hips]，用[npc2.her][npc2.clit+]来回磨蹭着[npc.her][npc.labia+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]绝望地尝试逃脱，"
										+ "[npc2.she]发出[npc2.a_moan+]，请求[npc.Name]离开[npc2.herHim]，别再操[npc2.her]的[npc2.pussy]了。",
								"[npc2.name]在[npc.namePos]的强行挑逗下不断退缩着，[npc2.she]一面祈求着对方放过自己，一面努力想把[npc2.pussy]远离对方的[npc.pussy]。"));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]热情地回礼，并在[npc.namePos][npc.pussy+]来回磨蹭自己的[npc2.clit]，一声[npc2.a_moan+]不禁漏出。",
							"[npc2.name]急切地将[npc2.hips]前推，用[npc2.pussy]来回磨蹭[npc.nameHers]的[npc.pussy]，一声[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
							"[npc2.name]愉悦地[npc2.moaning]着，急切地挺起[npc2.hips+]，用动作辅助自己[npc2.clit+]磨蹭[npc.namePos][npc.vagina+]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要逃离[npc.namePos]的[npc.pussy]，但没能成功，只得无力地恳求[npc.herHim]放过自己[npc2.vagina+]。",
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试将[npc.name]推离自己的[npc2.pussy]。",
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]远离[npc2.her][npc2.vagina+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]回礼，并在[npc.namePos][npc.pussy+]来回磨蹭自己的[npc2.clit]，一声[npc2.a_moan+]不禁漏出。",
							"[npc2.name]将[npc2.hips]前推，用[npc2.pussy]来回磨蹭[npc.nameHers]的[npc.pussy]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
							"[npc2.name]愉悦地[npc2.Moaning]着，挺起[npc2.hips+]，用[npc.namePos]的动作辅助自己[npc2.clit+]磨蹭[npc.her][npc.vagina+]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]快乐地回礼，并温柔地在[npc.namePos][npc.pussy+]来回磨蹭自己的[npc2.clit]，一声[npc2.a_moan+]不禁漏出。",
							"[npc2.name]将[npc2.hips]前推，温柔地用[npc2.pussy]来回磨蹭[npc.nameHers]的[npc.pussy]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间泄出。",
							"满足地[npc2.moaning]，[npc2.name]温柔地把[npc2.her][npc2.hips+]伸出来，用[npc.namePos]动作帮助[npc2.her]把[npc2.clit+]磨到[npc.her][npc.vagina+]上。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]热烈地回礼，并粗鲁地在[npc.namePos][npc.pussy+]来回磨蹭自己的[npc2.clit]，一声[npc2.a_moan+]不禁漏出。",
							"[npc2.name]粗暴地将[npc2.hips]前推，激烈地用[npc2.pussy]来回磨蹭[npc.nameHers]的[npc.pussy]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地推动[npc2.hips+]，以便将[npc2.clit+]强行压在[npc.namePos][npc.vagina+]上。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction TRIBBING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "磨镜(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "轻柔地将你的[npc.clit]在[npc2.namePos][npc2.vagina+]上反复滑动。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]每一次挺动[npc.hips]，都漏出一小声[npc.moan]，[npc.she]温柔地用[npc.clit+]在[npc2.namePos][npc2.vagina+]上来回磨蹭着。",
					"[npc.Name]发出一连串轻柔的呻吟，温柔地用[npc.clit+]在[npc2.namePos][npc2.vagina+]上来回磨蹭。",
					"[npc.name]用[npc.clit+]滑过[npc2.namePos][npc2.vagina+]，每当[npc.she]扭动[npc.hips]，都会发出一声轻微的[npc.moan]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TRIBBING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "磨镜";
		}

		@Override
		public String getActionDescription() {
			return "继续将你[npc.clit+]在[npc2.namePos][npc2.vagina+]上反复摩擦。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"伴随着一阵[npc.a_moan+]，[npc.name]热切地推进着[npc.hips]，用[npc.clit+]热情地来回磨蹭[npc2.namePos][npc2.vagina+]。",
					"[npc.Name]发出一连串淫荡的呻吟，[npc.she]饥渴地在[npc2.namePos][npc2.vagina+]来回磨蹭[npc.her][npc.clit+]。",
					"[npc.name]饥渴地在[npc2.namePos][npc2.vagina+]上摩擦着[npc.her][npc.clit+]，每当[npc.she]狂乱地扭动[npc.hips]，都会发出一声[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TRIBBING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "磨镜(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "继续将你[npc.clit+]粗暴地在[npc2.namePos][npc2.vagina+]上反复摩擦。";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"伴随着一阵[npc.a_moan+]，[npc.name][有力地推进着[npc.hips]，用[npc.clit+]粗暴地来回磨蹭[npc2.namePos][npc2.vagina+]。",
					"[npc.Name]发出一连串淫荡的呻吟，[npc.she]粗暴地在[npc2.namePos][npc2.vagina+]来回磨蹭[npc.her][npc.clit+]。",
					"[npc.name]粗暴地用[npc.clit+]摩擦着[npc2.namePos][npc2.vagina+]，每当[npc.she]有力地扭动[npc.hips]，都会发出一声[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TRIBBING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "磨镜";
		}

		@Override
		public String getActionDescription() {
			return "继续将你[npc.clit+]在[npc2.namePos][npc2.vagina+]上上下摩擦。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"伴随着一阵[npc.a_moan+]，[npc.name]推进着[npc.hips]，继续用[npc.clit+]来回磨蹭[npc2.namePos][npc2.vagina+]。",
					"[npc.Name]发出一连串淫荡的[npc.moans]，用[npc.clit+]来回磨蹭[npc2.namePos][npc2.vagina+]。",
					"[npc.name]用[npc.clit+]磨蹭着[npc2.namePos][npc2.vagina+]，每当[npc.she]扭动[npc.hips]，都会发出一声[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TRIBBING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "磨镜(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "将你[npc.clit+]在[npc2.namePos][npc2.vagina+]上渴求地反复摩擦。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"伴随着一阵[npc.a_moan+]，[npc.name]热切地推进着[npc.hips]，用[npc.clit+]热情地来回磨蹭[npc2.namePos][npc2.vagina+]。",
					"[npc.Name]发出一连串淫荡的呻吟，[npc.she]饥渴地在[npc2.namePos][npc2.vagina+]来回磨蹭[npc.her][npc.clit+]。",
					"[npc.name]饥渴地在[npc2.namePos][npc2.vagina+]上摩擦着[npc.her][npc.clit+]，每当[npc.she]狂乱地扭动[npc.hips]，都会发出一声[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TRIBBING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抗拒磨镜";
		}

		@Override
		public String getActionDescription() {
			return "努力让你[npc.pussy+]远离[npc2.namePos][npc2.clit+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]努力让自己的下体远离[npc2.nameHers]的，"
										+ "但[npc.her]的努力是徒劳的。[npc2.name]迅速拱上[npc2.hips]，[npc2.clit+]又温柔地压在[npc.her][npc.pussy+]上。",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着将自己的[npc.pussy]远离[npc2.nameHers]的，"
										+ "但[npc2.she]迅速顶上[npc2.hips]，继续用[npc2.her][npc2.clit+]温柔地磨蹭着[npc.her][npc.vagina+]。",
								"[npc.namePos]发出[npc.a_sob+]，[npc.eyes]里的泪水涌了出来，试图让自己的[npc.pussy]逃离[npc2.nameHers]的，"
										+ "但[npc2.name]闪转位置，完全无视了[npc.her]的反抗，用自己[npc2.vagina+]温柔地磨蹭着[npc2.hers]的。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]努力让自己的下体远离[npc2.nameHers]的，"
										+ "但[npc.her]的努力是徒劳的。[npc2.name]迅速拱上[npc2.hips]，[npc2.clit+]又粗暴地压在[npc.her][npc.pussy+]上。",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着将自己的[npc.pussy]远离[npc2.nameHers]的，"
										+ "但[npc2.she]迅速顶上[npc2.hips]，继续用[npc2.her][npc2.clit+]粗暴地磨蹭着[npc.her][npc.vagina+]。",
								"[npc.namePos]发出[npc.a_sob+]，[npc.eyes]里的泪水涌了出来，试图让自己的[npc.pussy]逃离[npc2.nameHers]的，"
										+ "但[npc2.name]闪转位置，完全无视了[npc.her]的反抗，用自己[npc2.vagina+]粗暴地磨蹭着[npc2.hers]的。"));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]努力让自己的下体远离[npc2.nameHers]的，"
										+ "但[npc.her]的努力是徒劳的。[npc2.name]迅速拱上[npc2.hips]，[npc2.clit+]又压在[npc.her][npc.pussy+]上。",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着将自己的[npc.pussy]远离[npc2.nameHers]的，"
										+ "但[npc2.she]迅速顶上[npc2.hips]，继续用[npc2.her][npc2.clit+]急切地磨蹭着[npc.her][npc.vagina+]。",
								"[npc.namePos]发出[npc.a_sob+]，[npc.eyes]里的泪水涌了出来，试图让自己的[npc.pussy]逃离[npc2.nameHers]的，"
										+ "但[npc2.name]闪转位置，完全无视了[npc.her]的反抗，用自己[npc2.vagina+]热情地磨蹭着[npc2.hers]的。"));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]努力让自己的下体远离[npc2.nameHers]的，"
								+ "但[npc.her]的努力注定是徒劳的。[npc2.she]向前推进，[npc2.clit+]压在[npc.her][npc.pussy+]上。",
						"伴随着一声[npc.a_sob+]，[npc.Name]试着将自己的[npc.pussy]远离[npc2.nameHers]的，"
								+ "但[npc2.name]迅速顶上[npc2.hips]，继续用[npc2.clit+]磨蹭着[npc.her][npc.vagina+]。",
						"[npc.namePos]发出[npc.a_sob+]，[npc.eyes]里的泪水涌了出来，试图让自己的[npc.pussy]逃离[npc2.nameHers]的，"
								+ "但[npc2.she]闪转位置，完全无视了[npc.her]的抗议，用自己[npc2.vagina+]压在[npc2.hers]的上面。"));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TRIBBING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止磨镜";
		}

		@Override
		public String getActionDescription() {
			return "将你[npc.clit+]远离[npc2.namePos][npc2.vagina+]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]最后又用力顶了一下，才把[npc.her]的下体远离[npc2.namePos][npc2.pussy+]，磨镜到此结束。",
							"[npc.Name]用[npc.her][npc.clit+]在[npc2.namePos][npc2.pussy]上粗暴地磨了最后一会儿，才挪开身体，让这粗鲁的磨镜到此结束。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"在最后一次挺起[npc.hips]后，[npc.name]从[npc2.namePos][npc2.pussy+]上移开了[npc.her]的下体，让磨镜到此结束。",
							"[npc.name]用[npc.clit+]在[npc2.namePos]的[npc2.pussy]上最后磨蹭了一次，才挪开身体，让磨镜到此结束。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]见到自己的[npc2.pussy]自由了固然开心，但却还是不停地哭，无力地反抗着[npc.name]，恳求[npc.herHim]大发慈悲放过自己。",
								"[npc2.name]发出一阵[npc2.a_sob+]，仍然挣扎着试图将[npc2.vagina+]从[npc.name]身上抽出。[npc2.she]哀求对方放过自己，眼泪已经忍不住像小溪一样从[npc2.face]上淌下。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]抽身回去，[npc2.name]发出了[npc2.a_moan+]，似乎是在表示自己还想要更多“照顾”。",
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]无比渴望得到[npc.namePos]的更多关注。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PUSSY_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "磨镜按摩";
		}

		@Override
		public String getActionDescription() {
			return "用你灵活的小阴蒂按揉磨蹭着[npc2.namePos][npc2.pussy+]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.PREHENSILE) && Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.DOM_ROUGH;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]发出一阵[npc.a_moan+]，继续专心用[npc.her]灵活可控的[npc.clit]摩擦着[npc2.namePos][npc2.pussy+]，然后开始挤弄按摩[npc2.namePos][npc2.clit+]。",
					isTargetedCharacterInanimate()
						?null
						:"[npc.Name]发出一阵[npc.a_moan+]，继续专心控制自己灵活可控的[npc.clit]。"
							+ "[npc.Name]揉按抚摸着[npc2.namePos][npc2.labia+]，一边浪叫，一边挪过去贴弄[npc2.her][npc2.clit+]，让[npc2.herHim]发出[npc2.a_moan+]。",
					"[npc.Name]愉悦地呻吟，用自己灵活的阴蒂揉按抚摸着[npc2.namePos][npc2.labia+]，又挪过去贴弄[npc2.her][npc2.clit+]。",
					"[npc.Name]发出[npc.a_moan+]，专注于控制自己灵活的[npc.clit]。顶住揉按[npc2.namePos][npc2.labia+]，又挪过去贴弄[npc2.her][npc2.clit+]。");
		}
	};
}
