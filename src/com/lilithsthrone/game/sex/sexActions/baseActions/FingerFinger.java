package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
 * @since 0.4.3.2
 * @version 0.4.4.1
 * @author Innoxia
 */
public class FingerFinger {
	
	public static final SexAction HAND_HOLDING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isLovingAction() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "开始牵手";
		}
		@Override
		public String getActionDescription() {
			return "握住[npc2.namePos]的[npc2.hands]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]想跟[npc2.name]多来一些身体接触，于是轻轻地牵住了[npc2.her]的[npc2.hands]。",
							"[npc.Name]发出[npc.a_moan+]，温柔地握住了[npc2.namePos]的[npc2.hands]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]希望能进一步控制住[npc2.namePos]的行动，于是用力抓住了[npc2.her]的[npc2.hands]。",
							"[npc.Name]发出[npc.a_moan+]声，一把抓住了[npc2.namePos]的[npc2.hands]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]想跟[npc2.name]多来一些身体接触，于是迫切地握住了[npc2.her]的[npc2.hands]。",
							"[npc.Name]发出一声[npc.a_moan+]，急切地握住了[npc2.namePos]的[npc2.hands]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]想跟[npc2.name]多来一些身体接触，于是牵住了[npc2.her]的[npc2.hands]。",
							"[npc.Name]发出一声[npc.a_moan+]，握住了[npc2.namePos]的[npc2.hands]。"));
					break;
				case SUB_RESISTING:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]则欣然用[npc2.her][npc2.fingers+]跟[npc.namePos]的[npc.hand]紧紧相扣，轻轻地叹了口气。",
								"作为回应，[npc2.name]也用[npc2.her][npc2.fingers+]包裹住[npc.namePos]的[npc.hand]，两人开心地牵起手来。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]则反过来用[npc2.her][npc2.fingers+]用力捏住[npc.namePos]的[npc.hand]，一声强硬的低哼声从口中传出。",
								"作为回应，[npc2.name]用[npc2.fingers+]紧紧攥住[npc.namePos]的[npc.hand]，两人用力地握起手来。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]欣然用[npc2.fingers+]包裹住[npc.namePos]的[npc.hand]，开心地[npc2.moan]起来。",
								"作为回应，[npc2.name]也热切地用[npc2.fingers+]握住[npc.namePos]的[npc.hand]，两人开心地牵起手来。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]反过来也用[npc2.fingers+]握住[npc.namePos]的[npc.hand]，发出一声[npc2.a_moan+]。",
								"作为回应，[npc2.name]也用[npc2.fingers+]包裹住[npc.namePos]的[npc.hand]，两人牵起手来。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]惊慌地想把[npc2.hand]从[npc.nameHers]的[npc.hand]里抽出来，一边央求对方松开，一边连连发出[npc2.a_moan+]。",
								"[npc2.name]在[npc.namePos]的强行挑逗下不断退缩着，[npc2.she]一面祈求着对方放过自己，一面努力想把[npc2.hand]从[npc.namePos]的[npc.hand]里抽出来。"));
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
							"[npc2.Name]也热情地回礼，紧紧攥住了[npc.namePos][npc.hand+]，同时一声[npc2.a_moan+]不禁漏出。",
							"[npc2.name]急切地攥住了[npc.namePos][npc.hand+]，一声[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地攥紧[npc.namePos][npc.hand+]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]惊慌地想把[npc2.hand]从[npc.nameHers]的[npc.hand]里抽出来，但却没能成功，只得绝望地恳求对方放过[npc2.herHim]。",
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试将[npc2.hand]从[npc.name]那里抽出来。",
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地挣扎着，哭着哀求[npc.Name]能松开[npc2.her]的[npc2.hand]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]也同样回礼，攥住了[npc.namePos][npc.hand+]，同时一声[npc2.a_moan+]不禁漏出。",
							"[npc2.name]握住了[npc.namePos][npc.hand+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
							"[npc2.name]反过来也攥住了[npc.namePos][npc.hand+]，口中发出一声[npc2.a_moan]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]欣然回礼，轻柔地握住了[npc.namePos][npc.hand+]，同时一声[npc2.a_moan+]不禁漏出。",
							"[npc2.name]轻柔的握住[npc.namePos][npc.hand+]，一声[npc2.A_moan+]从[npc2.lips+]间泄出。",
							"[npc2.name]反过来也温柔地握住[npc.namePos][npc.hand+]，口中发出一声[npc2.a_moan]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]也强硬地回礼，狠狠攥住了[npc.namePos][npc.hand+]，同时发出一声[npc2.a_moan+]。",
							"[npc2.name]狠力捏住了[npc.namePos][npc.hand+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
							"[npc2.name]反过来也用力攥住[npc.namePos][npc.hand+]，口中发出一声[npc2.a_moan]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction HAND_HOLDING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "牵手(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地握住[npc2.namePos][npc2.hand+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]轻声[npc.moan]着，温柔地握住了[npc2.namePos][npc2.hand+]。",
					"[npc.Name]用[npc.fingers+]包裹住了[npc2.namePos][npc2.hand+]，连连发出轻声的喘息。",
					"[npc.Name]用[npc.fingers+]轻柔地握住[npc2.namePos][npc2.hand+]，发出了轻声的[npc.moan]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction HAND_HOLDING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "牵手";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地握住[npc2.namePos][npc2.hand+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]欣然握着[npc2.namePos][npc2.hand+]，[npc.a_moan+]从口中溢出。",
					"[npc.Name]连连发出[npc.moans+]，急切地用[npc.fingers+]包裹住[npc2.namePos][npc2.hand+]。",
					"[npc.Name]饥渴地用[npc.fingers+]包裹住[npc2.namePos][npc2.hand+]，口中发出了一声[npc.moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction HAND_HOLDING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "牵手(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地攥住[npc2.namePos][npc2.hand+]。";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]用力握住[npc2.namePos][npc2.hand+]，[npc.a_moan+]从口中溢出。",
					"[npc.Name]连连发出[npc.moans+]，粗暴的用[npc.fingers+]包裹住[npc2.namePos][npc2.hand+]。",
					"[npc.Name]粗暴地用[npc.fingers+]包裹住[npc2.namePos][npc2.hand+]，口中发出了一声[npc.moan+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction HAND_HOLDING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "牵手";
		}

		@Override
		public String getActionDescription() {
			return "握住[npc2.namePos][npc2.hand+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]握住[npc2.namePos][npc2.hand+]，[npc.a_moan+]从口中溢出。",
					"[npc.Name]连连发出[npc.moans+]，用[npc.fingers+]包裹住[npc2.namePos][npc2.hand+]。",
					"[npc.Name]用[npc.fingers+]包裹住[npc2.namePos][npc2.hand+]，口中发出了一声[npc.moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction HAND_HOLDING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "牵手(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地握住[npc2.namePos][npc2.hand+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]欣然握着[npc2.namePos][npc2.hand+]，[npc.a_moan+]从口中溢出。",
					"[npc.Name]连连发出[npc.moans+]，急切地用[npc.fingers+]包裹住[npc2.namePos][npc2.hand+]。",
					"[npc.Name]饥渴地用[npc.fingers+]包裹住[npc2.namePos][npc2.hand+]，口中发出了一声[npc.moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction HAND_HOLDING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗牵手";
		}

		@Override
		public String getActionDescription() {
			return "把[npc.hand]从[npc2.nameHers]的[npc2.hand]里抽出来。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]想把[npc.hand]从[npc2.nameHers]的[npc2.hand]里抽出来，但[npc2.her]抓得十分用力，根本挣脱不开。",
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.hand]从[npc2.name]的抓握下抽出来，但[npc2.her]抓得十分用力，始终挣脱不开。",
							"泪水从[npc.namePos]的[npc.eyes]中滚落，[npc.she]无论怎样尝试也不能把[npc.hand]从[npc2.nameHers]的紧握下抽出来。"));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]想把[npc.hand]从[npc2.nameHers]的[npc2.hand]里抽出来，但始终都没能成功挣脱。",
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.hand]从[npc2.name]的抓握下抽出来，但[npc2.her]抓得很紧，始终挣脱不开。",
							"泪水从[npc.namePos]的[npc.eyes]中滚落，[npc.she]无论怎样尝试也不能把[npc.hand]从[npc2.nameHers]的紧握下抽出来。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction HAND_HOLDING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止牵手";
		}

		@Override
		public String getActionDescription() {
			return "将你[npc.hand+]从[npc2.nameHers]那里抽出来。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]最后又用力捏了一下，这才松开了[npc2.namePos][npc2.hand+]，牵手到此结束。",
							"[npc.Name]最后又用[npc.fingers+]狠狠攥了一下[npc2.namePos]的[npc2.hand]，这才松开，结束了这场粗暴的牵手。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]最后又捏了一下，这才松开了[npc2.namePos][npc2.hand+]，牵手到此结束。",
							"[npc.Name]最后又用[npc.fingers+]捏了一下[npc2.namePos]的[npc2.hand]，这才松开，结束了这场牵手。"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]见到自己的[npc2.hand]自由了固然开心，但却还是不停地哭，无力地反抗着[npc.name]，恳求[npc.herHim]大发慈悲放过自己。",
							"[npc2.name]发出一阵[npc2.a_sob+]，仍然挣扎着试图摆脱[npc.name]，[npc2.she]哀求对方放过自己，眼泪已经忍不住像小溪一样从[npc2.face]上淌下。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]把[npc.hand]抽了回去，[npc2.name]开始发出[npc2.a_moan+]，似乎是在表示自己还想要更多“照顾”。",
							"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]无比渴望得到[npc.namePos]的更多关注。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
