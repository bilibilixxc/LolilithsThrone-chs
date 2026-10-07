package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.8.5
 * @version 0.4.10.10
 * @author Sightglass
 */
public class PenisPenis {

	private static boolean hasSmallPenis(GameCharacter gc) {
		PenisLength pl = gc.getPenisSize();
		return pl == PenisLength.ZERO_MICROSCOPIC || pl == PenisLength.ONE_TINY;
	}
	
	private static String getHersWithDildoHandling(String parserTarget) {
		GameCharacter performer = Main.sex.getCharacterPerformingAction();
		GameCharacter target = Main.sex.getTargetedPartner(performer);
		
		if(performer.hasPenisIgnoreDildo()==target.hasPenisIgnoreDildo()) {
			return "["+parserTarget+".hers]的";
		} else {
			return "["+parserTarget+".her]的["+parserTarget+".cock]";
		}
	}
	
	public static final SexAction FROTTING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "开始磨枪";
		}

		@Override
		public String getActionDescription() {
			return "开始将你的[npc.cock]在[npc2.namePos]的[npc2.cock]上反复摩擦。";
		}

//		@Override
//		public boolean isBaseRequirementsMet() {
//			GameCharacter actor = Main.sex.getCharacterPerformingAction();
//			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
//			return actor.hasPenisIgnoreDildo() && target.hasPenisIgnoreDildo();
//		}
		
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
				case DOM_NORMAL:
					sb.append(UtilText.returnStringAtRandom(
							"[npc.name]伸手下探，调整[npc.her]的[npc.cock]来对准[npc2.nameHers]的，然后开始缓慢推进，用[npc.her]的阴茎摩擦"+getHersWithDildoHandling("npc2")+"。",
							"[npc.name]小心地调整[npc.her][npc.hips+]，将[npc.cock]抵在[npc2.namePos]的[npc2.cock]上，然后开始有节奏地来回磨蹭。",
							"[npc.name]发出[npc.a_moan+]，慢慢地将[npc.her]的下体抵在[npc2.nameHers]的上面，然后开始用[npc.her]的[npc.cock]来回磨蹭"+getHersWithDildoHandling("npc2")+"。"));
					break;
				case DOM_ROUGH:
					sb.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地顶起[npc.her][npc.hips+]，用[npc.cock]激烈地摩擦[npc2.namePos]的[npc2.cock]。",
							"[npc.name]咆哮一声，粗暴地抓住[npc2.name]，开始激烈地用[npc.cock+]顶撞[npc2.her][npc2.cock+]。",
							"[npc.name]发出[npc.a_moan+]，粗暴地将[npc.her]的下体顶在[npc2.nameHers]的上面，然后开始用[npc.her]的[npc.cock]激烈地来回磨蹭"+getHersWithDildoHandling("npc2")+"。"));
					break;
				case SUB_EAGER:
					sb.append(UtilText.returnStringAtRandom(
							(Main.sex.getCharacterPerformingAction().hasLegs()
									?"[npc.Name]将[npc2.name]拉近，张开双腿，将自己的下体与[npc2.hers]的对齐。"
									:"[npc.Name]将[npc2.name]拉近，调整姿势将自己的下体贴向[npc2.hers]的。")
								+"然后[npc.Name]饥渴地把[npc2.name]拉向自己，哄劝[npc2.name]用[npc2.her]的[npc2.cock]顶弄磨蹭"+getHersWithDildoHandling("npc")+"。",
							"[npc.name]发出[npc.a_moan+]，饥渴地将[npc.her]的下体抵在[npc2.nameHers]的上面，然后开始用[npc.her]的[npc.cock]拼命地磨擦"+getHersWithDildoHandling("npc2")+"。"));
					break;
				case SUB_NORMAL:
					sb.append(UtilText.returnStringAtRandom(
							(Main.sex.getCharacterPerformingAction().hasLegs()
									?"[npc.Name]将[npc2.name]拉近，张开双腿，将自己的下体与[npc2.hers]的对齐。"
									:"[npc.Name]将[npc2.name]拉近，调整姿势将自己的下体贴向[npc2.hers]的。")
								+"然后[npc.Name]娇羞地将[npc2.name]拉近，引诱[npc2.name]用[npc2.her]的[npc2.cock]顶弄摩擦"+getHersWithDildoHandling("npc")+"。",
							"[npc.name]发出[npc.a_moan+]，饥渴地将[npc.her]的下体抵在[npc2.nameHers]的上面，然后开始用[npc.her]的[npc.cock]挑逗"+getHersWithDildoHandling("npc2")+"。"));
					break;
				case SUB_RESISTING:
					break;
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					sb.append(UtilText.returnStringAtRandom(
							"[npc2.name]轻轻摇晃起[npc2.hips]作为回应，"
									+ "[npc2.name]模仿着[npc.name]的动作，用[npc2.cock]温柔地摩擦着"+getHersWithDildoHandling("npc")+"，[npc2.a_moan+]从口中溢出。",
							"作为回应，[npc2.name]开始模仿[npc.namePos]的动作，温柔地扭动着自己的[npc2.hips]，轻柔地用[npc2.cock]来回磨蹭"+getHersWithDildoHandling("npc")+"。"));
					break;
				case DOM_ROUGH:
					sb.append(UtilText.returnStringAtRandom(
							"[npc2.name]粗暴地扭动着自己的[npc2.hips]作为回应，"
									+ "[npc2.name]模仿着[npc.name]的动作，用[npc2.cock]激烈地摩擦着"+getHersWithDildoHandling("npc")+"，[npc2.a_moan+]从口中溢出。",
							"作为回应，[npc2.name]开始模仿[npc.namePos]的动作，粗暴地扭动着自己的[npc2.hips]，猛烈地用[npc2.cock]来回磨擦"+getHersWithDildoHandling("npc")+"。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					sb.append(UtilText.returnStringAtRandom(
							"[npc2.name]开心地摆动着自己的[npc2.hips]作为回应，"
									+ "[npc2.name]模仿着[npc.name]的动作，用[npc2.cock]轻快地磨蹭着"+getHersWithDildoHandling("npc")+"，[npc2.a_moan+]从口中溢出。",
							"作为回应，[npc2.name]开始模仿[npc.namePos]的动作，快乐地扭动着自己的[npc2.hips]，迷乱地用[npc2.cock]来回磨蹭"+getHersWithDildoHandling("npc")+"。"));
					break;
				case SUB_NORMAL:
					sb.append(UtilText.returnStringAtRandom(
							"[npc2.name]摆动着自己的[npc2.hips]作为回应，"
									+ "[npc2.name]模仿着[npc.name]的动作，用[npc2.cock]摩擦着"+getHersWithDildoHandling("npc")+"，[npc2.a_moan+]从口中溢出。",
							"作为回应，[npc2.name]开始模仿[npc.namePos]的动作，扭动着自己的[npc2.hips]，用[npc2.cock]来回磨蹭"+getHersWithDildoHandling("npc")+"。"));
					break;
				case SUB_RESISTING:
					sb.append(UtilText.returnStringAtRandom(
							"[npc2.name]绝望地尝试逃脱，"
									+ "[npc2.she]发出[npc2.a_moan+]，恳求[npc.name]放过[npc2.herHim]，别再碰[npc2.her]的[npc2.cock]了。",
							"[npc2.name]在[npc.namePos]的强行挑逗下不断退缩着，[npc2.she]一边祈求着对方放过自己，一边努力想让[npc2.cock]远离对方。"));
					break;
			}
			
			return sb.toString();
		}
		
	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		StringBuilder sb = new StringBuilder();
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				sb.append(UtilText.returnStringAtRandom(
						"[npc2.Name]热情地回礼，在[npc.nameHers]的[npc.cock]上来回磨蹭自己的[npc2.cock]，一声[npc2.a_moan+]不禁漏出。",
						"[npc2.name]急切地将[npc2.hips]前推，用自己的[npc2.cock]来回磨蹭着[npc.nameHers]的，一声[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
						"[npc2.name]愉悦地[npc2.moaning]着，饥渴地挺起[npc2.hips+]，用[npc.namePos]的动作辅助自己的[npc2.cock]磨蹭"+getHersWithDildoHandling("npc")+"。"));
				break;
			case SUB_RESISTING:
				sb.append(UtilText.returnStringAtRandom(
						"[npc2.name]拼命想要逃离[npc.namePos]的[npc.cock]，但没能成功，只得疯狂地恳求[npc.name]放过[npc2.him]。",
						"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试将[npc.name]推离自己的下体。",
						"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
								+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]远离[npc2.her]的下体。"));
				break;
			case SUB_NORMAL:
				sb.append(UtilText.returnStringAtRandom(
						"[npc2.Name]回礼，并用自己的[npc2.cock]来回磨蹭"+getHersWithDildoHandling("npc")+"，一声[npc2.a_moan+]不禁漏出。",
						"[npc2.name]将[npc2.hips]前推，用[npc2.cock]来回磨蹭"+getHersWithDildoHandling("npc")+"，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
						"[npc2.name]愉悦地[npc2.Moaning]着，挺起[npc2.hips+]，用[npc.namePos]的动作辅助自己[npc2.cock+]磨蹭"+getHersWithDildoHandling("npc")+"。"));
				break;
			case DOM_GENTLE:
				sb.append(UtilText.returnStringAtRandom(
						"[npc2.Name]快乐地回礼，温柔地在[npc.namePos]下体上来回磨蹭自己的[npc2.cock]，一声[npc2.a_moan+]不禁漏出。",
						"[npc2.name]将[npc2.hips]前推，温柔地用[npc2.cock]来回磨蹭[npc.nameHers]的[npc.cock]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间泄出。",
						"[npc2.name]愉悦地[npc2.Moaning]着，温柔地挺起[npc2.hips+]，用[npc.namePos]的动作辅助自己[npc2.cock+]磨蹭"+getHersWithDildoHandling("npc")+"。"));
				break;
			case DOM_ROUGH:
				sb.append(UtilText.returnStringAtRandom(
						"[npc2.Name]激烈地回礼，粗暴地用[npc2.cock]在[npc.namePos][npc.cock+]上来回摩擦，同时发出[npc2.a_moan+]。",
						"[npc2.name]粗暴地将[npc2.hips]前推，激烈地用[npc2.cock]来回摩擦"+getHersWithDildoHandling("npc")+"，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
						"[npc2.name]愉悦地[npc2.Moaning]着，粗暴地挺起[npc2.hips+]，用[npc.namePos]的动作辅助自己[npc2.cock+]抵住[npc.her]的下体。"));
				break;
		}
		return sb.toString();
	}
	
	public static final SexAction FROTTING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "磨枪(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "温柔地用你的[npc.cock]来回磨蹭[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.returnStringAtRandom(
					"[npc.name]每一次挺动[npc.hips]，都漏出一小声[npc.moan]，[npc.she]温柔地用[npc.cock+]在[npc2.namePos][npc2.cock+]上来回磨蹭着。",
					"[npc.Name]发出一连串轻柔的呻吟，温柔地用[npc.cock+]在[npc2.namePos][npc2.cock+]上来回磨蹭。",
					"[npc.name]用[npc.cock+]滑过[npc2.namePos][npc2.cock+]，每当[npc.she]扭动[npc.hips]，都会发出一声轻微的[npc.moan]。"));

			sb.append(getTargetedCharacterResponse(this));
					
			return sb.toString();
		}
	};
	
	public static final SexAction FROTTING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "磨枪";
		}
		@Override
		public String getActionDescription() {
			return "继续用你[npc.cock+]来回磨蹭[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"伴随着一阵[npc.a_moan+]，[npc.name]热切地推进着[npc.hips]，用[npc.cock+]热情地来回磨蹭[npc2.namePos][npc2.cock+]。",
					"[npc.Name]发出一连串淫荡的[npc.moans]，[npc.she]饥渴地在[npc2.namePos][npc2.cock+]上来回磨蹭[npc.her][npc.cock+]。",
					"[npc.name]饥渴地在[npc2.namePos][npc2.cock+]上摩擦着[npc.her][npc.cock+]，每当[npc.she]狂乱地扭动[npc.hips]，都会发出一声[npc.a_moan+]。"));

			sb.append(getTargetedCharacterResponse(this));
			
			return sb.toString();
		}
	};
	
	public static final SexAction FROTTING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "磨枪(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "继续用你[npc.cock+]粗暴地反复摩擦[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"伴随着一阵[npc.a_moan+]，[npc.name][有力地推进着[npc.hips]，用[npc.cock+]粗暴地来回磨蹭[npc2.namePos][npc2.cock+]。",
					"[npc.Name]发出一连串淫荡的[npc.moans]，[npc.she]粗暴地在[npc2.namePos][npc2.cock+]上来回磨蹭[npc.her][npc.cock+]。",
					"[npc.name]粗暴地用[npc.cock+]摩擦着[npc2.namePos][npc2.cock+]，每当[npc.she]有力地扭动[npc.hips]，都会发出一声[npc.a_moan+]。"));

			sb.append(getTargetedCharacterResponse(this));
			
			return sb.toString();
		}
	};
	
	public static final SexAction FROTTING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "磨枪";
		}
		@Override
		public String getActionDescription() {
			return "继续用你[npc.cock+]来回磨蹭[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"伴随着一阵[npc.a_moan+]，[npc.name]推进着[npc.hips]，继续用[npc.cock+]来回磨蹭[npc2.namePos][npc2.cock+]。",
					"[npc.Name]发出一连串淫荡的[npc.moans]，用[npc.cock+]来回磨蹭[npc2.namePos][npc2.cock+]。",
					"[npc.name]用[npc.cock+]磨蹭着[npc2.namePos][npc2.cock+]，每当[npc.she]扭动[npc.hips]，都会发出一声[npc.a_moan+]。"));

			sb.append(getTargetedCharacterResponse(this));
			
			return sb.toString();
		}
	};
	
	public static final SexAction FROTTING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "磨枪(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "用你[npc.cock+]饥渴地来回磨蹭[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"伴随着一阵[npc.a_moan+]，[npc.name]热切地推进着[npc.hips]，用[npc.cock+]热情地来回磨蹭[npc2.namePos][npc2.cock+]。",
					"[npc.Name]发出一连串淫荡的[npc.moans]，[npc.she]饥渴地在[npc2.namePos][npc2.cock+]上来回磨蹭[npc.her][npc.cock+]。",
					"[npc.name]饥渴地在[npc2.namePos][npc2.cock+]上摩擦着[npc.her][npc.cock+]，每当[npc.she]狂乱地扭动[npc.hips]，都会发出一声[npc.a_moan+]。"));

			sb.append(getTargetedCharacterResponse(this));
			
			return sb.toString();
		}
		
	};
	
	public static final SexAction FROTTING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "抵抗磨枪";
		}
		@Override
		public String getActionDescription() {
			return "努力远离[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					sb.append(UtilText.returnStringAtRandom(
							"[npc.Name]努力让自己的下体远离[npc2.nameHers]的，"
									+ "但[npc.her]的努力是徒劳的。[npc2.name]迅速拱上[npc2.hips]，[npc2.cock+]又温柔地压在了[npc.her][npc.cock+]上。",
							"[npc.name]发出[npc.a_sob+]，试着将[npc2.name]推开，"
									+ "但[npc2.name]迅速顶上[npc2.hips]，继续用[npc2.cock+]温柔地磨蹭着[npc.her][npc.cock+]。",
							"[npc.namePos]发出[npc.a_sob+]，[npc.eyes]里的泪水涌了出来，试图让自己的[npc.cock]逃离[npc2.nameHers]的，"
									+ "但[npc2.name]闪转位置，完全无视了[npc.her]的反抗，用自己[npc2.cock+]温柔地磨蹭着"+getHersWithDildoHandling("npc")+"。"));
					break;
				case DOM_ROUGH:
					sb.append(UtilText.returnStringAtRandom(
							"[npc.Name]努力让自己的下体远离[npc2.nameHers]的，"
									+ "但[npc.her]的努力是徒劳的。[npc2.name]迅速拱上[npc2.hips]，[npc2.cock+]又粗暴地压在[npc.her][npc.cock+]上。",
							"[npc.name]发出[npc.a_sob+]，试着将[npc2.name]推开，"
									+ "但[npc2.name]迅速顶上[npc2.hips]，继续用[npc2.cock+]粗暴地摩擦着[npc.her][npc.cock+]。",
							"[npc.namePos]发出[npc.a_sob+]，[npc.eyes]里的泪水涌了出来，试图让自己的[npc.cock]逃离[npc2.nameHers]的，"
									+ "但[npc2.name]闪转位置，完全无视了[npc.her]的反抗，用自己[npc2.cock+]粗暴地摩擦着"+getHersWithDildoHandling("npc")+"。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					sb.append(UtilText.returnStringAtRandom(
							"[npc.Name]努力让自己的下体远离[npc2.nameHers]的，"
									+ "但[npc.her]的努力是徒劳的。[npc2.name]迅速拱上[npc2.hips]，[npc2.cock+]又压在[npc.her][npc.cock+]上。",
							"[npc.name]发出[npc.a_sob+]，试着将[npc2.name]推开，"
									+ "但[npc2.name]迅速顶上[npc2.hips]，继续用[npc2.cock+]急切地磨蹭着[npc.her][npc.cock+]。",
							"[npc.namePos]发出[npc.a_sob+]，[npc.eyes]里的泪水涌了出来，试图让自己的[npc.cock]逃离[npc2.nameHers]的，"
									+ "但[npc2.name]闪转位置，完全无视了[npc.her]的反抗，用自己[npc2.cock+]热情地磨蹭着"+getHersWithDildoHandling("npc")+"。"));
					break;
			}
			
			return sb.toString();
		}
		
	};
	
	public static final SexAction FROTTING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止磨枪";
		}
		@Override
		public String getActionDescription() {
			return "将你[npc.cock+]抽离[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					sb.append(UtilText.returnStringAtRandom(
							"[npc.name]最后又粗暴地顶了一下，才把[npc.her]的下体从[npc2.namePos][npc2.cock+]上移开，结束了磨枪。",
							"[npc.name]最后又粗暴地将[npc.cock+]在[npc2.namePos]的[npc2.cock]上摩擦了一次，才挪开身体，结束了粗暴的磨枪。"));
					break;
				default:
					sb.append(UtilText.returnStringAtRandom(
							"在最后一次挺起[npc.hips]后，[npc.name]从[npc2.namePos][npc2.cock+]上移开了[npc.her]的下体，让磨枪到此结束。",
							"[npc.name]用[npc.cock+]在[npc2.name]的[npc2.cock]上最后磨蹭了一次，才挪开身体，让磨枪到此结束。"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					sb.append(UtilText.returnStringAtRandom(
							"[npc2.name]见到自己的[npc2.cock]自由了固然开心，但却还是不停地哭，无力地反抗着[npc.name]，恳求[npc.herHim]大发慈悲放过自己。",
							"[npc2.name]发出一阵[npc2.a_sob+]，拼命地挣扎着反抗[npc.name]，将[npc2.cock+]从[npc.name]身上抽离，泪水如小溪般从[npc2.face]上流了下来。"));
					break;
				default:
					sb.append(UtilText.returnStringAtRandom(
							"[npc.Name]抽身回去，[npc2.name]发出了[npc2.a_moan+]，似乎是在表示自己还想要更多“照顾”。",
							"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]无比渴望得到[npc.namePos]的更多关注。"));
					break;
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction PREHENSILE_FROTTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "缠绕磨枪";
		}
		@Override
		public String getActionDescription() {
			return "用你灵活可控的肉棒缠绕并按摩[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.PREHENSILE);
		}
		@Override
		public String getDescription() {
			if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.DOM_ROUGH) {
				return UtilText.returnStringAtRandom(
						"[npc.name]发出一阵[npc.a_moan+]，继续专心用[npc.her]灵活可控的[npc.cock]紧紧缠绕着[npc2.namePos][npc2.cock+]，"
								+ "然后将其卷起来，粗暴地按摩并挤压它。",
						"[npc.Name]发出[npc.a_moan+]，专注于控制自己灵活的[npc.cock]。"
								+ "[npc.name]将它紧紧地缠绕在[npc2.namePos][npc2.cock+]上，一边浪叫一边将其卷起，粗暴地按摩[npc2.her][npc2.cock+]，使得[npc2.herHim]发出[npc2.a_moan+]。",
						"[npc.Name]愉悦地咧嘴一笑，用[npc.her]灵活可控的阴茎粗暴地揉按抚弄着[npc2.namePos][npc2.cock+]，有节奏地激烈拉扯着它。",
						"[npc.name]发出一阵[npc.a_moan+]，专注于控制自己灵活可控的[npc.cock]。"
								+ "[npc.name]用力地将它缠绕在[npc2.namePos][npc2.cock+]上，用收紧的[npc.cock]将其卷住并开始榨取。");
			}
			return UtilText.returnStringAtRandom(
					"[npc.name]发出一阵[npc.a_moan+]，继续专心用[npc.her]灵活可控的[npc.cock]缠绕着[npc2.namePos][npc2.cock+]，然后盘绕起来挤弄按摩着它。",
					"[npc.Name]发出[npc.a_moan+]，专注于控制自己灵活的[npc.cock]。"
							+ "[npc.name]将它缠绕在[npc2.namePos][npc2.cock+]上，一边浪叫一边将其卷起，按摩着[npc2.her][npc2.cock+]，使得[npc2.herHim]发出[npc2.a_moan+]。",
					"[npc.Name]愉悦地咧嘴一笑，用[npc.her]灵活可控的阴茎揉按抚弄着[npc2.namePos][npc2.cock+]，有节奏地拉扯着它。",
					"[npc.name]发出一阵[npc.a_moan+]，专注于控制自己灵活可控的[npc.cock]。"
							+ "[npc.name]将它缠绕在[npc2.namePos][npc2.cock+]上，用收紧的[npc.cock]将其卷住并开始榨取。");
		}
	};

	public static final SexAction SHEATH_FROTTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "阴茎鞘对接";
		}
		@Override
		public String getActionDescription() {
			return "用你的鸡巴挑逗并插入包裹着[npc2.namePos][npc2.cock+]的鞘。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()).hasPenisModifier(PenetrationModifier.SHEATHED);
		}
		@Override
		public String getDescription() {
			if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.DOM_ROUGH) {
				String start = UtilText.returnStringAtRandom(
						"[npc.name]发出[npc.a_moan+]，将[npc.cock]的尖端猛地捅进[npc2.namePos][npc2.cock+]周围的鞘中，在鞘内打着圈，挑逗着里面敏感的皮肤。",
						"[npc.Name]发出[npc.a_moan+]，将手伸向[npc2.namePos][npc2.cock+]。"
								+ "[npc.name]将它引导向自己[npc.cock+]，咧嘴一笑，随后有力地将[npc.cock]推过[npc2.her][npc2.cock+]，塞入了[npc2.her]的鞘中，"
									+ "使得[npc2.name]发出惊诧的[npc2.moan]。",
						"[npc.Name]愉悦地咧嘴一笑，[npc.she]引导着阴茎来到[npc2.namePos]的鞘口，然后将其强行推入。"
								+ "[npc.NamePos]的[npc.cock]紧密地贴着"+getHersWithDildoHandling("npc2")+"，[npc.she]开始用力地按摩抚弄两根阴茎，有节奏地挤压着鞘。",
						"[npc.Name]发出一阵[npc.a_moan+]，将[npc.cock]猛地捅入[npc2.namePos]的鞘中，先是用力地短暂绕圈挑逗，然后便更深地插入进去。");
				String middle1 = UtilText.returnStringAtRandom(
					"[npc.Name]霸道地将[npc.cock]深深推入鞘中，在[npc2.namePos][npc2.cock+]根部周围滑动着。",
//					"Rather than thrust deeply into [npc2.her] sheath, [npc.name] instead circle [npc.her] cock around "+getHersWithDildoHandling("npc2")+".",
					"[npc.name]探下身，有力地抓住[npc2.namePos]的鞘，把它当做鸡巴套用来手淫。",
					"[npc.name]探下身，有力地抓住[npc2.namePos]的鞘，然后进一步将其套在自己的[npc.cock]上。"
				);
				String middle2 = UtilText.returnStringAtRandom(
					"[npc2.name]被来自敏感的阴茎鞘的刺激所淹没，只能颤抖着[npc2.moan]。",
					"[npc2.namePos]的鞘内表面极其敏感，[npc.name]感觉到[npc2.namePos]的[npc2.cock]每一次对这种刺激做出反应时，都会颤动着抽搐一下。",
					"[npc.namePos][npc.cock+]越来越深地塞入[npc2.her]的鞘中，[npc2.Name]不断颤抖着[npc2.moansVerb]。"
				);
				String end = UtilText.returnStringAtRandom(
					"[npc.name]最后又抽插了几次，然后漫不经心地从[npc2.namePos]的鞘中猛地拔出，粗暴地将自己的[npc.cock]拍打在"+getHersWithDildoHandling("npc2")+"上面，随后又开始对着它抽插摩擦起来。",
					"[npc.name]发出[npc.a_moan+]，最后又猛地顶了一下[npc.hips]，才从[npc2.namePos]的鞘中抽出，然后继续磨枪。",
					"过了一会儿，那种刺激变得过于强烈，以至于难以承受。"
							+ "[npc.name]发出一声[npc.moan+]，将[npc.cock]猛地从环绕着[npc2.namePos][npc.cock+]的鞘中抽出，"
							+ "在轻抚了几下之后，[npc.she]将[npc.cock+]对准了对方的[npc2.cock]，再次顶弄摩擦起来。",
					"[npc.name]最后又用[npc.cock]在[npc2.namePos]的鞘内磨蹭了几下，才随意地从紧致的鞘中抽离，然后[npc.she]重新调整了[npc.cock+]的位置，重新开始磨枪。"
				);
				return String.join("",start, middle1, middle2,end);
				
			} else {
				String start = UtilText.returnStringAtRandom(
						"[npc.name]发出[npc.a_moan+]，让[npc.cock]的尖端滑入[npc2.namePos][npc2.cock+]周围的鞘中，在鞘内打着圈，挑逗着里面敏感的皮肤。",
						"[npc.Name]发出[npc.a_moan+]，将手伸向[npc2.namePos][npc2.cock+]。"
								+ "[npc.name]将它引导向自己[npc.cock+]，一边浪叫，一边将[npc.cock]推过[npc2.her][npc2.cock+]，滑入了[npc2.her]的鞘中，使得[npc2.herHim]发出一阵[npc2.a_moan+]。",
						"[npc.Name]愉悦地咧嘴一笑，[npc.she]引导着阴茎来到[npc2.namePos]的鞘口，然后将其滑入。"
								+ "[npc.NamePos]的[npc.cock]紧密地贴着"+getHersWithDildoHandling("npc2")+"，[npc.name]开始按摩抚弄着两根阴茎，有节奏地挤压着鞘。",
						"[npc.Name]发出一阵[npc.a_moan+]，让[npc.cock]滑入[npc2.namePos]的鞘中，缓慢地绕圈挑逗着，然后便更深地滑入进去。");
				String middle1 = UtilText.returnStringAtRandom(
					"[npc.Name]缓慢地将[npc.cock]深深推入鞘中，在[npc2.namePos][npc2.cock+]根部周围滑动着。",
					"[npc.name]并没有深入[npc2.her]的鞘中，而是用自己的阴茎环绕着"+getHersWithDildoHandling("npc2")+"绕圈磨蹭。",
					"[npc.name]探下身，有力地抓住[npc2.namePos]的鞘，把它当做鸡巴套用来手淫。",
					"[npc.name]探下身，有力地抓住[npc2.namePos]的鞘，然后将其更深地套在自己的[npc.cock]上。[npc.name]并没有抽插，只是原地不动，缓慢地揉搓按摩着[npc2.namePos]的鸡巴。"
				);
				String middle2 = UtilText.returnStringAtRandom(
					"[npc2.name]被来自敏感的阴茎鞘的刺激所淹没，只能颤抖着[npc2.moan]。",
					"[npc2.namePos]的鞘内表面极其敏感，[npc.name]感觉到[npc2.namePos]的[npc2.cock]每一次对这种刺激做出反应时，都会颤动着抽搐一下。",
					(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING
						?"伴随着一声充满欲望的[npc2.moan]，[npc2.name]更加贴近了[npc.name]，温柔地顶弄着，试图让[npc.her][npc.cock+]更多地进入自己的鞘中。"
						:"")
				);
				String end = UtilText.returnStringAtRandom(
					"[npc.name]最后又抽插了几次，然后小心地从[npc2.namePos]的鞘中抽出，玩味地用自己的[npc.cock]拍打着"+getHersWithDildoHandling("npc2")+"，随后又开始对着它抽插摩擦起来。",
					"伴随着一声喘息，[npc.name]又在[npc2.namePos]的鞘内磨蹭了几下，才从[npc2.her]的鞘中抽出，重新开始磨枪。",
					"过了一会儿，那种刺激变得过于强烈，以至于难以承受。[npc.name]发出一声[npc.moan]，将[npc.cock]从环绕着[npc2.namePos][npc.cock+]的鞘中抽出，"
							+ "在轻抚了几下之后，[npc.she]将[npc.cock+]对准了对方的[npc2.cock]，再次顶弄摩擦起来。",
					"[npc.name]最后又用[npc.cock]在[npc2.namePos]的鞘内磨蹭了几下，才小心地从紧致的鞘中抽出，然后[npc.she]再次调整了[npc.cock+]的位置，重新开始磨枪。"
				);
				return String.join("",start, middle1, middle2,end);
			}
		}
	};
	
	public static final SexAction PREHENSILE_FROTTING_DOUBLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "双重缠绕磨枪";
		}
		@Override
		public String getActionDescription() {
			return "将你和[npc2.name]灵活可控的鸡巴互相缠绕。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()).hasPenisModifier(PenetrationModifier.PREHENSILE)
					&& Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.PREHENSILE)
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.name]发出一阵[npc.a_moan+]，继续专心用[npc.her]灵活可控的[npc.cock]缠绕着[npc2.namePos][npc2.cock+]。"
							+ "[npc2.Name]效仿[npc.name]，将[npc2.cock]与[npc.hers]的互相缠绕结合，然后[npc.name]开始缓慢地按摩抚弄着它。",
							
					"[npc.Name]发出[npc.a_moan+]，专注于控制自己灵活的[npc.cock]。"
							+ "[npc.name]将它缠绕在[npc2.namePos][npc2.cock+]上，一边浪叫着，一边用它抚弄并卷绕着对方的[npc2.cock]，不停地引诱着[npc2.namePos]。"
							+ "[npc2.Name]则饥渴地用自己灵活可控的[npc2.cock]环绕住[npc.hers]的，挑逗挤压着以回敬[npc.name]。",
							
					"[npc.Name]愉悦地咧嘴一笑，用[npc.her]灵活可控的阴茎揉按抚弄着[npc2.namePos][npc2.cock+]，"
							+ "而[npc2.name]也用自己灵活可控的阴茎模仿着[npc.her]的动作。"
							+ "[npc.name]用自己起伏扭动的[npc.cock]拉扯着它，与[npc2.name]一起找到了一个舒适的节奏。",
							
					"[npc.name]发出一阵[npc.a_moan+]，专注于控制自己灵活可控的[npc.cock]，用它缠绕住[npc2.namePos][npc2.cock+]。"
							+ "[npc.name]和[npc2.name]都有灵活可控的阴茎，两人试图缠绕并包裹住对方的肉棒，在收缩的盘绕中挤压榨取对方。");
		}
	};
	
	public static final SexAction FROTTING_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeCreampied() {
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				new Exception().printStackTrace();
				return null;
			}
			
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).contains(lockingCharacter)) {
					characterPenetrated = lockingCharacter;
				}
				
			} else { // If not locked, can choose who to cum inside:
				List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
				if(charactersPenetrated.contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					characterPenetrated = Main.sex.getCharacterTargetedForSexAction(this);
				}
			}
			
			return characterPenetrated;
		}
		private SexAreaInterface getAreaToBeCreampied() {
			return SexAreaPenetration.PENIS;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			if(Main.sex.getCharactersHavingOngoingActionWith(performer, SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			
			GameCharacter target = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			if(!performer.hasPenisIgnoreDildo()) {
				return false;
			}
			
			boolean dicksTouching = Main.sex.getOngoingSexAreas(performer, SexAreaPenetration.PENIS, target).contains(SexAreaPenetration.PENIS);
			if (!dicksTouching) {
				return false;
			}
			
			// Will not use if obeying pull out requests:
			if((Main.sex.getSexManager().getCharacterOrgasmBehaviour(performer)!=OrgasmBehaviour.CREAMPIE
					&& !performer.isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(performer) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(performer)>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(performer)==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			boolean knotRequestObeyed = false;
			for(GameCharacter knotRequester : Main.sex.getCharactersRequestingKnot()) {
				if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterPerformingAction(), knotRequester)) {
					knotRequestObeyed = true; // If there is a knot requester who they're listening to, give priority to knotting
					break;
				}
			}
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if((Math.random()<0.66f
					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0)
				&& !knotRequestObeyed) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return "强制磨枪！";
					
				} else if(bodypart == Arm.class) {
					return "拥抱禁锢磨枪！";
					
				} else if(bodypart == Leg.class) {
					return "叉腿禁锢磨枪！";
					
				} else if(bodypart == Tail.class) {
					return "尾巴禁锢磨枪！";
					
				} else if(bodypart == Wing.class) {
					return "翅膀禁锢磨枪！";
					
				} else if(bodypart == Tentacle.class) {
					return "触手禁锢磨枪！";
				}
			}
			return "磨枪高潮";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter character = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return UtilText.parse(character,
							"[npc.NameIsFull]占据有利位置，强迫你高潮时用阴茎抵着[npc.herHim]！你处在高潮边缘，没时间尝试推开[npc.herHim]！");
					
				} else if(bodypart == Arm.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.her][npc.arms+]抱着你的下背部，强迫你高潮时用阴茎抵着[npc.herHim]！"
							+ "你处在高潮边缘，没时间尝试从[npc.herHim]的掌握中解脱！");
					
				} else if(bodypart == Leg.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.her][npc.legs+]抱着你的下背部，强迫你高潮时用阴茎抵着[npc.herHim]！"
							+ "你处在高潮边缘，没时间尝试从[npc.herHim]的掌握中解脱！");
					
				} else if(bodypart == Tail.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.her]"+(character.getTailCount()>1?"[npc.tails+]":"[npc.tail]")+"缠着你的下背部，强迫你高潮时用阴茎抵着[npc.herHim]！"
							+ "你处在高潮边缘，没时间尝试从[npc.herHim]的掌握中解脱！");
					
				} else if(bodypart == Wing.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.wingSize]的[npc.wings]包裹着你的身体，强迫你高潮时用阴茎抵着[npc.herHim]！"
							+ "你处在高潮边缘，没时间尝试从[npc.herHim]的掌握中解脱！");
					
				} else if(bodypart == Tentacle.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.her][npc.tentacles+]缠着你的下背部，强迫你高潮时用阴茎抵着[npc.herHim]！"
							+ "你处在高潮边缘，没时间尝试从[npc.herHim]的掌握中解脱！");
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			String returnString = "你达到了极限，再也无法承受快感的冲击。你用自己的阴茎抵着[npc2.namePos]的阴茎射精了。";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEndEffects();
			return "";
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			if(cumTarget.equals(characterPenetrated)) {
				return Util.newArrayListOfValues(areaContacted);
				
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(getCharacterToBeCreampied())) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA || cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
					return Util.newArrayListOfValues(
							CoverableArea.ANUS,
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
					
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.NORMAL) {
					return Util.newArrayListOfValues(
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				}
			}
			return null; 
		}
//		@Override
//		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
//			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
//					&& ((cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumTarget).isEmpty())
//						|| (cumTarget.equals(cumProvider) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumProvider).isEmpty()))) {
//
//				return Util.newArrayListOfValues(
//						CoverableArea.PENIS,
//						CoverableArea.VAGINA);
//			}
//			return null; 
//		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).isEndsSex();
		}
	};
	
	public static final SexAction FROTTING_SPH_SMALL = new SexAction(
			SexActionType.SPEECH,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "羞辱小阴茎";
		}
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public String getActionDescription() {
			return "嘲笑[npc2.name]那可怜尺寸的小鸡巴，同时用你更大的鸡巴抵住[npc2.her]的。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			boolean dicksTouching = Main.sex.getOngoingSexAreas(actor, SexAreaPenetration.PENIS, target).contains(SexAreaPenetration.PENIS);
			return hasSmallPenis(target) && target.hasPenisIgnoreDildo() && !hasSmallPenis(actor) && dicksTouching;
		}
		@Override
		public String getDescription() {
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			String intro = UtilText.returnStringAtRandom(
					"[npc.name]将[npc.cock+]与[npc2.namePos]的对齐，[npc2.hers][npc2.cock+]明显短了一截。",
					"[npc.Name]停顿了一下，将自己[npc.cock+]靠在[npc2.namePos]小得多的[npc2.cock]旁。",
					"[npc.Name]进行了一次超长的挺进，用[npc.cock+]的全部长度摩擦[npc2.namePos][npc2.cock+]。"
			);
			String mid = UtilText.returnStringAtRandom(
					"[npc.name]嘲讽地看着[npc2.her]那可怜的[npc2.cock]，咆哮道，",
					"[npc.name]继续用[npc.cock]摩擦着[npc2.name]那可怜的[npc2.cock]，决定要好好地羞辱[npc2.she]一番，说道，",
					"[npc.Name]对[npc2.name]嘲讽地轻笑了一声，说道，"
			);
			String quote;
			switch (target.getPenisSize()) {
				case ZERO_MICROSCOPIC:
					quote =  UtilText.returnStringAtRandom(
						"那个东西简直就是个小阴蒂！",
						"虽然我本就没抱什么期望，但你这还是太可怜了！",
						"小小的阴蒂被真正的鸡巴碾压的感觉怎么样？",
						"我几乎感觉不到你那个可悲的小突起。",
						"你应该感到幸运，总算能感受到一次<i>真正的</i>肉棒了。"
					);
					break;
				case ONE_TINY:
				default:
					quote =  UtilText.returnStringAtRandom(
						"你很努力了，小"+ (target.isFeminine()?"姑娘":"伙子") + "，但你那玩意儿根本不够看。",
						"<i>就这？</i>太让人失望了。",
						"你应该感到幸运，总算能感受到一次<i>真正的</i>肉棒了。",
						"你就这？",
						"虽然我本就没抱什么期望，但你这还是太可怜了！",
						"‘小不点儿’，是吧？", 
						"我敢打赌，你喜欢小鸡鸡被这样支配的感觉！"
					);
					break;
			}
			quote = "[npc.speech(" + quote + ")]";
			return String.join("", intro, mid, quote);
		}
	};
	
	public static final SexAction FROTTING_SPH_NORMAL = new SexAction(
			SexActionType.SPEECH,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "尺寸羞辱";
		}

		@Override
		public boolean isSadisticAction() {
			return true;
		}

		@Override
		public String getActionDescription() {
			return "用你更大的鸡巴羞辱[npc2.name]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			boolean dicksTouching = Main.sex.getOngoingSexAreas(actor, SexAreaPenetration.PENIS, target).contains(SexAreaPenetration.PENIS);
			
			float penisSizeRatio = (float)actor.getPenisRawSizeValue() / (float)target.getPenisRawSizeValue();
			boolean actorPenisSizeLarger = actor.getPenisSize().getMinimumValue() > target.getPenisSize().getMinimumValue();
			return (actorPenisSizeLarger || penisSizeRatio > 1.25)
				&& penisSizeRatio > 1.15
				&& !hasSmallPenis(target) && !hasSmallPenis(actor) 
				&& target.hasPenisIgnoreDildo() && dicksTouching;
		}

		@Override
		public String getDescription() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			String intro = UtilText.returnStringAtRandom(
					"[npc.name]将[npc.cock+]与[npc2.namePos]的对齐，[npc2.hers][npc2.cock+]相对短了一截。",
					"[npc.Name]停顿了一下，将自己[npc.cock+]靠在[npc2.namePos]的[npc2.cock]旁，使尺寸差异显而易见。",
					"[npc.Name]进行了一次超长的挺进，用[npc.cock+]的全部长度摩擦[npc2.namePos][npc2.cock+]。"
			);
			String mid = UtilText.returnStringAtRandom(
					"[npc.name]看到自己[npc.cock+]和[npc2.name]的[npc2.cock]之间的尺寸差异，笑着说，",
					"[npc.name]继续用[npc.cock]摩擦着[npc2.name]的[npc2.cock]，决定羞辱[npc2.she]一番，说道，",
					"[npc.Name]对[npc2.name]轻笑了一声，说道，"
			);
			String quote;
			switch (target.getPenisSize()) {
				case ZERO_MICROSCOPIC:
					quote =  UtilText.returnStringAtRandom(
						"那个东西简直就是个小阴蒂！",
						"虽然我本就没抱什么期望，但你这还是太可怜了！",
						"小小的阴蒂被真正的鸡巴碾压的感觉怎么样？",
						"我几乎感觉不到你那个可悲的小突起。",
						"你应该感到幸运，总算能感受到一次<i>真正的</i>肉棒了。"
					);
					break;
				case ONE_TINY:
					quote =  UtilText.returnStringAtRandom(
						"你很努力了，小"+ (target.isFeminine()?"姑娘":"伙子") + "，但你那玩意儿根本不够看。",
						"<i>就这？</i>太让人失望了。",
						"你应该感到幸运，总算能感受到一次<i>真正的</i>肉棒了。",
						"你就这？",
						"虽然我本就没抱什么期望，但你这还是太可怜了！",
						"‘小不点儿’，是吧？", 
						"我敢打赌，你喜欢小鸡鸡被这样支配的感觉！"
					);
					break;
				case TWO_AVERAGE:
					quote =  UtilText.returnStringAtRandom(
						"也不坏，但你现在可是在跟大"+(actor.isFeminine()?"姐姐":"哥哥")+"一起玩呢。",
						"平平无奇，好好看看<i>这个</i>！",
						"好好看看真正"+(actor.isFeminine()?"女人的":"男人的")+"鸡巴！",
						"你应该感到幸运，总算能感受到一次<i>真正的</i>肉棒了。",
						"‘平庸’可不是什么值得炫耀的事情呢。",
						"你就这？",
						"‘平均尺寸’？我在小恶魔身上都见过更大的鸡巴。"
					);
					break;
				case FIVE_ENORMOUS:
				case SIX_GIGANTIC:
				case SEVEN_STALLION:
					quote = UtilText.returnStringAtRandom(
						"也不坏，但你现在可是在跟大"+(actor.isFeminine()?"姐姐":"哥哥")+"一起玩呢。",
						"一屌更比一屌长！",
						"人外有人，屌外有屌！",
						"不管你有多大，还是我的更大！",
						"不错，但我见过更大的。",
						"你没怎么见过比你还大的人，对吧？"
					);
					break;
				case THREE_LARGE:
				case FOUR_HUGE:
				default:
					quote =  UtilText.returnStringAtRandom(
						"也不坏，但你现在可是在跟大"+(actor.isFeminine()?"姐姐":"哥哥")+"一起玩呢。",
						"你挺屌的，但总有比你更屌的！",
						"你挺屌的，但屌外有屌！",
						"你挺屌的，但我更屌！",
						"不错，但我见过更大的。",
						"鸡巴不错，但还差不少呢。"
					);
					break;
			}
			quote = "[npc.speech(" + quote + ")].";
			return String.join("", intro, mid, quote);
		}
	};
	
	public static final SexAction FROTTING_COMPLIMENT = new SexAction(
			SexActionType.SPEECH,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "欣赏鸡巴";
		}
		@Override
		public String getActionDescription() {
			return "称赞[npc2.namePos]的[npc2.cock]，同时用你自己的摩擦它。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			boolean dicksTouching = Main.sex.getOngoingSexAreas(actor, SexAreaPenetration.PENIS, target).contains(SexAreaPenetration.PENIS);
			return !hasSmallPenis(target) && !hasSmallPenis(actor) 
				&& target.hasPenisIgnoreDildo() && dicksTouching;
		}
		@Override
		public String getDescription() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			String intro = UtilText.returnStringAtRandom(
					"[npc.name]将[npc.cock+]与[npc2.namePos]的对齐，花了一点时间欣赏这两根鸡巴。",
					"[npc.Name]停顿了一下，将自己[npc.cock+]靠在[npc2.namePos]的[npc2.cock]旁，赞赏地轻抚了几下。",
					"[npc.Name]进行了一次超长的挺进，用[npc.cock+]的全部长度摩擦[npc2.namePos][npc2.cock+]，享受着那湿滑的快感。"
			);
			String mid = UtilText.returnStringAtRandom(
					"[npc.Name]带着玩味的笑声赞美[npc2.name]，说道，",
					"[npc.name]继续用[npc.cock]摩擦着[npc2.name]的[npc2.cock]，决定讨好[npc2.him]，说道，",
					"[npc.Name]对[npc2.name]轻笑了一声，说道，",
					"[npc.name]带着促狭的笑容，用自己的[npc.cock]轻拍着[npc2.her][npc2.cock+]，赞许地说道，"
			);
			String quote;
			switch (target.getPenisSize()) {
				case FIVE_ENORMOUS:
				case SIX_GIGANTIC:
				case SEVEN_STALLION:
					quote = UtilText.returnStringAtRandom(
						"不错的家伙，"+(actor.isFeminine()?"姐妹":"哥们")+"！",
						"哇！你可真大！",
						"我想把我的全身都贴在这只怪物般的鸡巴上！",
						"我就喜欢蹭这种巨型鸡巴！",
						"我要摸遍这只怪物的每一寸皮肤！"
					);
					break;
				case THREE_LARGE:
				case FOUR_HUGE:
					quote =  UtilText.returnStringAtRandom(
						"不错的家伙，"+(actor.isFeminine()?"姐妹":"哥们")+"！",
						"挺大的！不错！",
						"一根肥硕的鸡巴！",
						"你的鸡巴真大！",
						"漂亮！",
						"我就喜欢看大鸡巴！",
						"我就喜欢蹭这种大鸡巴！"
					);
					break;
				case TWO_AVERAGE:
				default:
					quote =  UtilText.returnStringAtRandom(
						"不错的家伙，"+(target.isFeminine()?"姐妹":"哥们")+"！",
						"这尺寸真不错！",
						"刚刚好！",
						"终于！一根大小适中的鸡巴！",
						"一根完美的鸡巴！",
						"这一根就<i>正正正正正</i>好！",
						"蹭这种好鸡巴真爽！"
					);
					break;
			}
			quote = "[npc.speech(" + quote + ")]";
			String response;
			if(Main.sex.getSexPace(target)==SexPace.SUB_RESISTING) {
				response = UtilText.returnStringAtRandom(
						"<br/>[npc2.name]拼命想要逃离[npc.namePos]的[npc.cock]，但没能成功，只得疯狂地恳求[npc.name]放过[npc2.him]。",
						"<br/>[npc2.namePos]的[npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试将[npc.name]推离自己的下体。",
						"<br/>悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上滴落而下，"
								+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]远离[npc2.her]的下体。");
				
			} else {
				response = UtilText.returnStringAtRandom(
						"<br/>[npc2.name]继续顶弄磨蹭着[npc.name]，因为[npc.her]的鼓励而更添了几分活力。",
						"<br/>[npc2.name]在[npc.namePos]的鼓励下，更加亢奋地顶撞着[npc.her][npc.cock+]",
						"<br/>[npc2.name]被[npc.name]的撩人的赞美所鼓舞，给了[npc.him]几下格外用力的抽送。",
						"<br/>[npc2.Name]听到[npc.namePos]的赞美后红了脸，更加贴近上去。",
						"<br/>[npc2.Name]用一声[npc2.moan]回应了[npc.her]的赞美，更加紧密地贴了上去，并且更起劲地用[npc2.cock]磨蹭着"+getHersWithDildoHandling("npc")+"。");
			}
			return String.join("", intro, mid, quote, response);
		}
	};
}
