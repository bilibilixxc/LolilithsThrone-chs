package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
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
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class TongueArmpit {
	
	public static final SexAction ARMPIT_LICKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "舔舐腋下";
		}
		@Override
		public String getActionDescription() {
			return "你在[npc2.namePos][npc2.armpit+]上游移你的[npc.tongue]并开始舔吮它。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]温柔地举起[npc2.namePos]的手臂，将[npc.lips+]贴在[npc2.her][npc2.armpit+]上，随后给予其一长串轻柔的吻，"
									+"[npc.she]开始用[npc.tongue+]舔弄[npc2.her]裸露的胸口。",

							"[npc.Name]举起[npc2.namePos]的手臂，给予[npc2.namePos]的腋下一长串轻柔的吻，"
									+"[npc.she]用[npc.tongue+]温柔地舔舐[npc2.her]裸露的胸口，发出[npc.moaning+]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]渴望地举起[npc2.namePos][npc2.arm+(true)]，疯狂地将[npc.her][npc.lips+]压在[npc2.her][npc2.armpit+]，并随之给予其一长串热情的吻，"
									+"[npc.she]开始贪婪地用[npc.tongue+]在[npc2.her]裸露的乳房上舔舐。",

							"[npc.Name]不耐烦地举起[npc2.namePos][npc2.arm+(true)]，给予[npc2.namePos][npc2.armpit+]一长串热情的吻，"
									+"[npc.she]贪婪地用[npc.tongue+]在[npc2.her]裸露的胸口上舔舐，发出了[npc.moaning+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗暴地拽起[npc2.name][npc2.arm+(true)]，强行将[npc.lips+]压在了[npc2.her][npc2.armpit+]上，接着就毫不顾忌地热吻起来，"
									+"[npc.she]贪婪地用[npc.tongue+]舔舐[npc2.her]裸露的胸口。",

							"[npc.Name]粗暴地拉起[npc2.namePos][npc2.arm+(true)]，给予[npc2.namePos][npc2.armpit+]一长串猛烈的吻，"
									+"[npc.she]用[npc.tongue+]强势地舔舐[npc2.her]裸露的胸口，发出[npc.moaning+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]举起[npc2.namePos][npc2.arm+(true)]，将[npc.her][npc.lips+]压在[npc2.her][npc2.armpit+]上，并随后给予其一长串的吻，"
									+"[npc.she]开始用[npc.tongue+]舔舐[npc2.her]裸露的胸口。",

							"[npc.Name]举起[npc2.namePos][npc2.arm+(true)]，给予[npc2.namePos][npc2.armpit+]一长串的吻，"
									+"[npc.she]用[npc.tongue+]在[npc2.her]裸露的胸口上舔舐，发出了[npc.moaning+]。"));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出轻柔的[npc2.moan]作为回应，温柔地将[npc2.underarm]向[npc.namePos]的[npc.face]按去，期待着对方继续做下去。",
	
								"[npc2.name]温柔地将[npc2.underarm]向着[npc.namePos][npc.face]上压过去，回应着[npc.her]的侍奉。同时大声[npc2.moanVerb]起来，祈求对方继续下去。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]作为回应，渴望地将[npc2.her][npc2.underarm]压向[npc.namePos][npc.face]以祈求[npc.herHim]继续做下去。",
	
								"[npc2.name]拼命地将[npc2.her][npc2.underarm]压向[npc.namePos][npc.face]以回应[npc.her]口交，并大声[npc2.moanVerb]，祈求[npc.herHim]继续做下去。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]作为回应，粗暴地将[npc2.her][npc2.underarm]推向[npc.namePos][npc.face]以命令[npc.herHim]继续做下去。",
	
								"[npc2.name]粗暴地将[npc2.underarm]推向[npc.namePos]的[npc.face]，饥渴地回应起口交，大声地[npc2.moanVerb]，要求[npc.herHim]继续做下去。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]作为回应，渴望地将[npc2.her][npc2.underarm]压向[npc.namePos][npc.face]以祈求[npc.herHim]继续做下去。",
	
								"[npc2.name]拼命地将[npc2.her][npc2.underarm]压向[npc.namePos][npc.face]以回应[npc.her]口交，并大声[npc2.moanVerb]，祈求[npc.herHim]继续做下去。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]回应着，发出[npc2.a_moan+]，把[npc2.her]的[npc2.underarm]压向[npc.namePos]的[npc.face]，祈求[npc.herHim]继续做下去。",
	
								"[npc2.name]将[npc2.underarm]抵在[npc.namePos][npc.face]上，回应起口交，大声地[npc2.moanVerb]，祈求[npc.herHim]继续做下去。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]拼命地尝试扭动远离[npc.namePos]的嘴巴接触，[npc2.she][npc2.sobbing]扭动，乞求放过自己。",
	
								"[npc2.namePos]嘴间爆发出一阵[npc2.A_sob+]，[npc2.she]一边反抗着[npc.Name]，一边乞求[npc.Name]将[npc.tongue]从[npc2.her]的腋窝处挪开。"));
						break;
					default:
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
							"作为回应，[npc2.Name]急切地向后压去，"
									+ "[npc2.she]用[npc2.armpit+]压住[npc.namePos][npc.lips+]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]温柔地压向[npc.namePos]的[npc.face]，乞求[npc.Name]继续侍奉[npc2.her]的[npc2.underarm]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地用[npc2.underarm+]磨蹭[npc.namePos]的[npc.face]，"
									+ "然后急切地乞求[npc.herHim]继续用[npc.tongue+]滑向[npc2.her][npc2.armpit+]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法从[npc.namePos]那讨厌的[npc.tongue]下缩身，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然亲吻舔弄[npc2.her][npc2.armpit+]。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地试图远离[npc.namePos]的[npc.tongue]，"
									+ "[npc.name]不断舔弄着[npc2.her][npc2.armpit+]，[npc2.she]奋力反抗着[npc.name]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]温柔地向后压去，"
									+ "[npc2.she]发出一声轻柔的[npc2.moan]，将[npc2.armpit+]压向[npc.namePos][npc.lips+]。",
		
							" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
									+ "[npc2.she]温柔地压向[npc.namePos]的[npc.face]，乞求[npc.Name]继续侍奉[npc2.her]的[npc2.underarm]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，轻柔地用[npc2.underarm+]磨蹭[npc.namePos]的[npc.face]，"
									+ "然后急切地乞求[npc.herHim]继续用[npc.tongue+]滑向[npc2.her][npc2.armpit+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]粗暴地向后推，"
									+ "[npc2.she]用[npc2.armpit+]蹭着[npc.namePos][npc.lips+]，同时发出[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]粗暴地压向[npc.namePos]的[npc.face]，命令[npc.Name]继续侍奉[npc2.her]的[npc2.underarm]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，激烈地用[npc2.underarm+]磨蹭[npc.namePos]的[npc.face]，"
									+ "然后积极地命令[npc.herHim]继续用[npc.tongue+]滑向[npc2.her][npc2.armpit+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]向后压去，"
									+ "[npc2.she]用[npc2.armpit+]压住[npc.namePos][npc.lips+]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间飘出一阵[npc2.A_moan+]，"
									+ "[npc2.she]压向[npc.namePos]的[npc.face]，乞求[npc.Name]继续侍奉[npc2.her]的[npc2.underarm]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.underarm+]磨蹭[npc.namePos]的[npc.face]，"
									+ "然后乞求[npc.herHim]继续用[npc.tongue+]滑向[npc2.her][npc2.armpit+]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction ARMPIT_LICKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "舔舐腋窝(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "温柔地舔弄[npc2.namePos][npc2.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]温柔地用[npc.tongue+]在[npc2.namePos][npc2.underarm+]上磨蹭着，"
							+ "[npc.name]将[npc.her][npc.lips+]压向[npc2.her][npc2.armpit+]，并发出了一声低沉的[npc.moan]。",

					"[npc.name]将[npc.tongue+]收回口中，开始温柔地亲吻[npc2.namePos][npc2.underarm+]，"
							+ "随后[npc.her][npc.tongue]再次缓慢地滑向[npc2.her][npc2.armpit+]。",

					"[npc.name]将[npc.tongue+]收回口中，开始慢慢地亲吻摩擦[npc2.namePos][npc2.underarm+]，"
							+ "[npc.she]倾身向前，继续温柔地舔舐[npc2.namePos]的腋窝。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "舔舐腋窝";
		}
		@Override
		public String getActionDescription() {
			return "用你的[npc.tongue]舔弄[npc2.namePos][npc2.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]饥渴地用[npc.tongue+]在[npc2.namePos][npc2.underarm+]上磨蹭着，"
							+ "[npc.name]将[npc.her][npc.lips+]压向[npc2.her][npc2.armpit+]，并发出了一声低沉的[npc.moan]。",

					"[npc.name]将[npc.tongue+]收回口中，开始急切地亲吻[npc2.namePos][npc2.underarm+]，"
							+ "随后[npc.her][npc.tongue]再次贪婪地滑向[npc2.her][npc2.armpit+]。",

					"[npc.name]将[npc.tongue+]收回口中，开始愉悦地亲吻摩擦[npc2.namePos][npc2.underarm+]，"
							+ "[npc.she]倾身向前，继续热切地舔舐[npc2.namePos]的腋窝。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "舔舐腋窝(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地用你的[npc.tongue]舔弄[npc2.namePos][npc2.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]粗暴地用[npc.tongue+]在[npc2.namePos][npc2.underarm+]上磨蹭着，"
							+ "[npc.name]将[npc.her][npc.lips+]蹭向[npc2.her][npc2.armpit+]，并发出了一声低沉的[npc.moan]。",

					"[npc.name]将[npc.tongue+]收回口中，开始粗暴地亲吻[npc2.namePos][npc2.underarm+]，"
							+ "随后[npc.her][npc.tongue]再次激烈地滑向[npc2.her][npc2.armpit+]。",

					"[npc.name]将[npc.tongue+]收回口中，开始激烈地亲吻摩擦[npc2.namePos][npc2.underarm+]，"
							+ "[npc.she]倾身向前，继续粗暴地舔舐[npc2.namePos]的腋窝。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "舔舐腋窝(抗拒)";
		}
		@Override
		public String getActionDescription() {
			return "努力让你的[npc.tongue]远离[npc2.namePos][npc2.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]试图把[npc.face]移开，但[npc2.name]继续将[npc2.armpit+]温柔地压在[npc.namePos][npc.lips+]上，"
									+ "[npc2.name]牢牢地将[npc.herHim]固定在原位，紧紧贴着[npc.herHim]。",

							"伴随着一声[npc.a_sob+]，[npc.Name]试着推开[npc2.name]，但[npc2.name]迅速地将[npc.her]的[npc.face]拉回了自己[npc2.underarm+]，"
									+ "完全无视了[npc.her]的挣扎，温柔地磨蹭着[npc.herHim]。",

							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把嘴巴从[npc2.namePos][npc2.underarm+]边挪开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边将[npc2.armpit+]压向[npc.her][npc.lips+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]试图把[npc.face]移开，但[npc2.name]继续将[npc2.armpit+]粗暴地压在[npc.namePos][npc.lips+]上，"
									+ "[npc2.name]牢牢地将[npc.herHim]固定在原位，粗暴地贴着[npc.herHim]。",

							"伴随着一声[npc.a_sob+]，[npc.Name]试着推开[npc2.name]，但[npc2.name]猛烈地将[npc.her]的[npc.face]拉回了自己[npc2.underarm+]，"
									+ "完全无视了[npc.her]的挣扎，粗暴地磨蹭着[npc.herHim]。",

							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把嘴巴从[npc2.namePos][npc2.underarm+]边挪开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边粗暴地将[npc2.armpit+]压向[npc.her][npc.lips+]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]试图把[npc.face]移开，但[npc2.name]继续将[npc2.armpit+]急切地压在[npc.namePos][npc.lips+]上，"
									+ "[npc2.name]牢牢地将[npc.herHim]固定在原位，紧紧贴着[npc.herHim]。",

							"伴随着一声[npc.a_sob+]，[npc.Name]试着推开[npc2.name]，但[npc2.name]迅速地将[npc.her]的[npc.face]拉回了自己[npc2.underarm+]，"
									+ "完全无视了[npc.her]的挣扎，急切地磨蹭着[npc.herHim]。",

							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把嘴巴从[npc2.namePos][npc2.underarm+]边挪开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边急切地将[npc2.armpit+]压向[npc.her][npc.lips+]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "舔舐腋窝";
		}
		@Override
		public String getActionDescription() {
			return "用你的[npc.tongue]舔弄[npc2.namePos][npc2.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.tongue+]伸向[npc2.namePos][npc2.underarm+]，"
							+ "[npc.name]将[npc.her][npc.lips+]压向[npc2.her][npc2.armpit+]，并发出了一声低沉的[npc.moan]。",

					"[npc.name]将[npc.tongue+]收回口中，开始亲吻[npc2.namePos][npc2.underarm+]，"
							+ "随后[npc.her][npc.tongue]再次滑向[npc2.her][npc2.armpit+]。",

					"[npc.name]将[npc.tongue+]收回口中，开始亲吻摩擦[npc2.namePos][npc2.underarm+]，"
							+ "[npc.she]倾身向前，继续舔舐[npc2.namePos]的腋窝。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "舔舐腋窝(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "饥渴地用你的[npc.tongue]舔弄[npc2.namePos][npc2.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]饥渴地用[npc.tongue+]在[npc2.namePos][npc2.underarm+]上磨蹭着，"
							+ "[npc.name]将[npc.her][npc.lips+]压向[npc2.her][npc2.armpit+]，并发出了一声低沉的[npc.moan]。",

					"[npc.name]将[npc.tongue+]收回口中，开始急切地亲吻[npc2.namePos][npc2.underarm+]，"
							+ "随后[npc.her][npc.tongue]再次贪婪地滑向[npc2.her][npc2.armpit+]。",

					"[npc.name]将[npc.tongue+]收回口中，开始愉悦地亲吻摩擦[npc2.namePos][npc2.underarm+]，"
							+ "[npc.she]倾身向前，继续热切地舔舐[npc2.namePos]的腋窝。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止舔舐腋窝";
		}
		@Override
		public String getActionDescription() {
			return "将你的[npc.tongue]抽离[npc2.namePos][npc2.armpit+]，停止舔弄它。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"以一个粗暴的舔舐作为结束，随后[npc.name]把[npc.her]的[npc.face]从[npc2.namePos][npc2.armpit+]旁移开了。",
	
							"给了[npc2.namePos][npc2.armpit+]一个粗暴的亲吻作为结束，[npc.name]将[npc.her]的[npc.face]从[npc2.her]胸口旁移开了。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"以一个粗暴的舔舐作为结束，随后[npc.name]把[npc.her]的[npc.face]从[npc2.namePos][npc2.armpit+]旁移开了。",
	
							"给了[npc2.namePos][npc2.armpit+]一个湿润的亲吻作为结束，[npc.name]将[npc.her]的[npc.face]从[npc2.her]胸口旁移开了。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]继续挣扎着，[npc2.sobbing]并不舒服地扭动，[npc2.she]意识到[npc.Name]还没有完全和[npc2.herHim]结束。",
		
								"[npc2.name]意识到[npc.she]还没有完全放过自己，继续挣扎[npc2.sobbing]，"
										+ "[npc2.she]恳求[npc.name]放过自己，泪水如小溪般从[npc2.her]的[npc2.face]上流下。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]将[npc.tongue+]从[npc2.namePos][npc2.underarm+]挪开，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
								
								"[npc.Name]不再舔弄[npc2.namePos][npc2.armpit+]，[npc2.namePos][npc2.lips+]间飘出一阵[npc2.A_moan+]，暴露了[npc2.she]渴望得到[npc.namePos]的更多关注。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "舔腋窝";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]开始舔你的[npc.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]温柔地抬起胳膊，把[npc.underarm+]按向[npc2.namePos][npc2.face]，"
									+"用[npc.armpit+]磨蹭着[npc2.name][npc2.lips+]，爽得发出一声[npc.a_moan]。",

							"[npc.Name]轻声[npc.moan]，温柔地将[npc.underarm+]压向[npc2.namePos][npc2.face+]，用[npc.armpit+]磨蹭[npc2.her][npc2.lips+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]饥渴地抬起胳膊，把[npc.underarm+]按向[npc2.namePos][npc2.face]，"
									+"用[npc.armpit+]忘我地磨蹭着[npc2.name][npc2.lips+]，爽得发出一声[npc.a_moan+]。",

							"[npc.Name]发出[npc.a_moan+]，将[npc.underarm+]压向[npc2.namePos][npc2.face+]，饥渴地用[npc.armpit+]磨蹭[npc2.her][npc2.lips+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地抬起胳膊，把[npc.underarm+]按向[npc2.namePos][npc2.face]，"
									+"用[npc.armpit+]用力地磨蹭着[npc2.name][npc2.lips+]，爽得发出一声[npc.a_moan+]。",
									
							"[npc.Name]发出[npc.a_moan+]，将[npc.underarm+]压向[npc2.namePos][npc2.face+]，粗暴地用[npc.armpit+]磨蹭[npc2.her][npc2.lips+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]饥渴地抬起胳膊，把[npc.underarm+]按向[npc2.namePos][npc2.face]，"
									+"用[npc.armpit+]忘我地磨蹭着[npc2.name][npc2.lips+]，爽得发出一声[npc.a_moan+]。",

							"[npc.Name]发出[npc.a_moan+]，将[npc.underarm+]压向[npc2.namePos][npc2.face+]，饥渴地用[npc.armpit+]磨蹭[npc2.her][npc2.lips+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]抬起胳膊，把[npc.underarm+]按向[npc2.namePos][npc2.face]，"
									+"用[npc.armpit+]磨蹭着[npc2.name][npc2.lips+]，爽得发出一声[npc.a_moan+]。",

							"[npc.Name][npc.moan]着，将[npc.underarm+]压向[npc2.namePos][npc2.face+]，用[npc.armpit+]磨蹭[npc2.her][npc2.lips+]。"));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]慢慢向[npc.namePos][npc.armpit+]伸出[npc2.tongue+]，伴随着着模糊不清的[npc2.moan]，温柔地舔吻着[npc.her][npc.underarm+]。",
	
								"[npc2.name]温柔地伸出舌头细致而缓慢地舔了一下[npc.name][npc.underarm+]，"
										+"含糊不清地发出一声[npc2.moan]，在[npc.namePos][npc.armpit+]上留下了一串温柔的吻。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]贪婪地向[npc.namePos][npc.armpit+]伸出[npc2.tongue+]，伴随着着模糊不清的[npc2.moan]，兴奋地舔吻着[npc.her][npc.underarm+]。",
	
								"[npc2.name]伸出舌头黏湿地舔了一下[npc.name][npc.underarm+]，"
										+"含糊不清地发出一声[npc2.moan]，在[npc.namePos][npc.armpit+]上留下了一串热情的吻。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]粗暴地伸出舌头舔向[npc.name][npc.underarm+]，"
										+"含糊不清地发出一声[npc2.moan]，在[npc.namePos][npc.armpit+]上留下了一串粗鲁的吻。",
	
								"[npc2.name]贪婪而细致地伸出舌头粗暴而黏湿地舔了一下[npc.name][npc.underarm+]，"
										+"含糊不清地发出一声[npc2.moan]，在[npc.namePos][npc.armpit+]上留下了一串用力的吻。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]贪婪地向[npc.namePos][npc.armpit+]伸出[npc2.tongue+]，伴随着着模糊不清的[npc2.moan]，兴奋地舔吻着[npc.her][npc.underarm+]。",
	
								"[npc2.name]伸出舌头黏湿地舔了一下[npc.name][npc.underarm+]，"
										+"含糊不清地发出一声[npc2.moan]，在[npc.namePos][npc.armpit+]上留下了一串热情的吻。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]向[npc.namePos][npc.armpit+]伸出[npc2.tongue+]，伴随着着模糊不清的[npc2.moan]，舔吻着[npc.her][npc.underarm+]。",
	
								"[npc2.name]伸出舌头缓慢而黏湿地舔了一下[npc.name][npc.underarm+]，"
										+"含糊不清地发出一声[npc2.moan]，在[npc.namePos][npc.armpit+]上留下了一串吻。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]痛哭出声，挣扎着想把嘴从[npc.Name][npc.armpit+]旁拿开。",
	
								"[npc2.name]痛哭出声，挣扎着想把脸从[npc.Name][npc.armpit+]旁拿开，却浑身无力。"));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]贪婪地用[npc2.tongue+]舔弄[npc.namePos][npc.armpit+]，"
									+"含糊不清地发出一声[npc2.moan]。",
		
							"[npc2.namePos]嘴里流出一声低沉的[npc2.moan]，"
									+"把[npc2.tongue+]伸向[npc.namePos][npc.underarm+]，贪求地一路舔向腋下。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地把[npc2.lips+]靠向[npc.namePos]的[npc.underarm]，"
									+"饥渴地对着[npc.her][npc.armpit+]伸出[npc.tongue+]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.face]从[npc.namePos]的[npc.underarm]间移开，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然将自己[npc.underarm+]压向[npc2.her][npc2.face+]。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地试图远离[npc.namePos]的[npc.underarm]，"
									+ "[npc.name]将[npc.armpit+]压向[npc2.her][npc2.lips+]，[npc2.she]奋力反抗着[npc.name]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地用[npc2.tongue+]舔弄[npc.namePos][npc.armpit+]，"
									+ "[npc2.she]发出一声含糊不清的柔和[npc2.moan]，轻柔地将[npc2.lips+]压向[npc.namePos][npc.underarm+]。",
		
							"[npc2.namePos]的口中飘出一声含糊不清的[npc2.moan]，"
									+ "[npc2.she]粗鲁地将[npc2.lips+]压向[npc.namePos][npc.underarm+]，开始粗暴地伸出[npc2.tongue+]舔舐腋窝。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，轻柔地用[npc2.lips+]压向[npc.namePos]的[npc.underarm]，"
									+ "随后温柔地将[npc2.her][npc2.tongue+]尽可能深地探入[npc.her][npc.armpit+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]用力地伸出伸头舔了舔[npc.namePos][npc.armpit+]，"
									+ "含糊不清地发出一声[npc2.moan]，接着把[npc2.lips+]靠向[npc.underarm+]。",
		
							"[npc2.namePos]的口中飘出一声含糊不清的[npc2.moan]，"
									+ "[npc2.she]粗鲁地将[npc2.lips+]压向[npc.namePos][npc.underarm+]，开始粗暴地用[npc2.tongue+]舔舐。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，激烈地用[npc2.lips+]压向[npc.namePos]的[npc.underarm]，"
									+ "接着积极地伸出舌头舔向[npc.armpit+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]用[npc2.tongue+]舔弄[npc.namePos][npc.armpit+]，"
									+ "含糊不清地发出一声[npc2.moan]，接着把[npc2.lips+]靠向[npc.underarm+]。",
		
							"[npc2.namePos]嘴里流出一声低沉的[npc2.moan]，"
									+"[npc2.she]粗鲁地把嘴靠向[npc.namePos][npc.underarm+]，伸出[npc2.tongue+]舔舐腋窝。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.lips+]压向[npc.namePos]的[npc.underarm]，"
									+"随后贪婪地伸出[npc2.tongue+]舔着[npc.armpit+]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "被舔腋窝(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "温柔地将你[npc.underarm+]按向[npc2.namePos]的脸，让[npc2.her][npc2.tongue+]舔弄你[npc.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]轻轻地叹了口气，温柔地抬起胳膊，让[npc2.namePos][npc2.lips+]紧紧贴向[npc.armpit+]。",

					"[npc.Name]轻声[npc.moan]，将[npc.underarm+]压向[npc2.namePos][npc2.face+]，温柔地用自己[npc.armpit+]磨蹭[npc2.her][npc2.lips+]。",

					"轻轻地叹了口气，[npc.name]温柔地用[npc.armpit+]磨蹭着[npc2.namePos]的[npc2.lips]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "被舔腋窝";
		}
		@Override
		public String getActionDescription() {
			return "饥渴地将你[npc.underarm+]按向[npc2.namePos]的脸，让[npc2.her][npc2.tongue+]舔弄你[npc.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]饥渴地呻吟着，让[npc.armpit+]紧靠着[npc2.namePos][npc2.lips+]。",

					"[npc.Name][npc.moan]出声，饥渴地将[npc.underarm+]压向[npc2.namePos][npc2.face+]，贪婪地用自己[npc.armpit+]磨蹭[npc2.her][npc2.lips+]。",

					"[npc.name]发出一阵[npc.a_moan+]，饥渴地用[npc.armpit+]磨蹭着[npc2.namePos]的[npc2.lips]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "被舔腋窝(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地用你[npc.armpit+]磨蹭[npc2.namePos][npc2.tongue+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]粗暴地在[npc2.namePos][npc2.lips+]蹭着自己的[npc.armpit+]，爽得发出一阵阵[npc.a_moan+]。",

					"伴随着[npc.a_moan+]，[npc.Name]粗暴地把自己的[npc.underarm+]压在[npc2.namePos][npc2.face+]，支配地用[npc.armpit+]蹭着[npc2.namePos][npc2.lips+]。",

					"伴随着一阵[npc.a_moan+]，[npc.name]粗暴地用[npc.armpit+]碾压者着[npc2.namePos]的[npc2.lips]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "被舔腋窝(抗拒)";
		}
		@Override
		public String getActionDescription() {
			return "努力让你[npc.armpit+]远离[npc2.namePos]noun.[npc2.tongue+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]绝望地看着"
									+"[npc2.name]温柔地伸出舌头舔着自己[npc.armpit+]，[npc.a_sob]着想要把[npc.underarm]拉离[npc2.name]。",

							"[npc.Name]绝望地痛哭着，想要把[npc.armpit+]拉离[npc2.namePos][npc2.lips+]，"
									+"却只能看着[npc2.name]一边抓住自己，一边无视自己的挣扎在[npc.underarm+]上留下一系列轻吻。"
									+"然后把[npc2.tongue+]温柔地伸向自己[npc.armpit+]。",

							"[npc.Name]痛哭着，拼命地想要把[npc.underarm+]拉离[npc2.namePos][npc2.lips+]，却只能看着[npc2.namePos]一边抓着自己，"
									+"一边把自己的抵抗当做情趣，继续温柔地把舌头伸向[npc.armpit+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]绝望地看着"
									+"[npc2.name]伸出舌头粗暴地舔着自己[npc.armpit+]，[npc.a_sob]着想要把[npc.underarm]拉离[npc2.name]。",

							"[npc.Name]绝望地痛哭着，想要把[npc.armpit+]拉离[npc2.namePos][npc2.lips+]，"
									+"却只能看着[npc2.name]一边抓住自己，一边无视自己的挣扎在[npc.underarm+]上留下一系列湿吻。"
									+"然后把[npc2.tongue+]粗暴地伸向自己[npc.armpit+]。",

							"[npc.Name]痛哭着，拼命地想要把[npc.underarm+]拉离[npc2.namePos][npc2.lips+]，却只能看着[npc2.namePos]一边狠狠抓着自己，"
									+"一边把自己的抵抗当做情趣，继续粗暴地把舌头伸向[npc.armpit+]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]绝望地看着"
									+"[npc2.name]伸出舌头贪婪地舔着自己[npc.armpit+]，[npc.a_sob]着想要把[npc.underarm]拉离[npc2.name]。",

							"[npc.Name]绝望地痛哭着，想要把[npc.armpit+]拉离[npc2.namePos][npc2.lips+]，"
									+"却只能看着[npc2.name]一边抓住自己，一边无视自己的挣扎在[npc.underarm+]上留下一系列热情的吻。"
									+"然后把[npc2.tongue+]贪婪地伸向自己[npc.armpit+]。",

							"[npc.Name]痛哭着，拼命地想要把[npc.underarm+]拉离[npc2.namePos][npc2.lips+]，却只能看着[npc2.namePos]一边抓着自己，"
									+"一边把自己的抵抗当做情趣，继续饥渴地把舌头伸向[npc.armpit+]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "被舔腋窝";
		}
		@Override
		public String getActionDescription() {
			return "将你[npc.armpit+]压向[npc2.namePos][npc2.face+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]带着[npc.a_moan+]，把[npc.armpit+]紧紧贴在[npc2.namePos][npc2.lips+]上。",

					"伴随着一连串[npc.a_moan+]，[npc.Name]把[npc.underarm+]压在[npc2.namePos][npc2.face+]上，用[npc.armpit+]磨蹭着[npc2.her][npc2.lips+]。",

					"伴随着[npc.a_moan+]，[npc.Name]在[npc2.namePos][npc2.lips]上磨蹭着自己[npc.armpit+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "被舔腋窝(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "饥渴地用你[npc.armpit+]磨蹭[npc2.namePos][npc2.tongue+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]饥渴地呻吟着，让[npc.armpit+]紧靠着[npc2.namePos][npc2.lips+]。",

					"[npc.Name][npc.moan]出声，饥渴地将[npc.underarm+]压向[npc2.namePos][npc2.face+]，贪婪地用自己[npc.armpit+]磨蹭[npc2.her][npc2.lips+]。",

					"[npc.name]发出一阵[npc.a_moan+]，饥渴地用[npc.armpit+]磨蹭着[npc2.namePos]的[npc2.lips]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止被舔腋窝";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.tongue+]从你[npc.armpit+]旁挪开。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]头部从自己[npc.armpit+]推开，命令[npc2.name]不准舔了。",

							"粗暴地再在[npc2.namePos][npc2.face]上蹭了下[npc.armpit+]，[npc.Name]将自己的[npc.underarm]拉开。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]把[npc2.namePos]的头推开，让[npc2.herHim]不要舔了。",

							"再用[npc.armpit+]蹭了下[npc2.namePos][npc2.face]，[npc.Name]收回了[npc.underarm]。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"眼泪滚下，[npc2.name]啜泣着，发现[npc.name]原未满足。",
	
								"[npc2.name]继续奋力反抗着[npc.Name]，[npc2.she]发出一阵[npc2.a_sob+]，乞求[npc.name]放过自己。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]不情愿地舔弄[npc.namePos][npc.armpit+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.name]意犹未尽地发出一声[npc2.a_moan+]，示意[npc.Name]移开点。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
