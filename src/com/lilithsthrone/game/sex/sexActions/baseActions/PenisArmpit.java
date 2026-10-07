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
public class PenisArmpit {

	public static final SexAction ARMPIT_SEX_RECEIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "操腋窝";
		}
		@Override
		public String getActionDescription() {
			return "用你的[npc.cock]在[npc2.namePos][npc2.armpit+]上下摩擦。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.namePos][npc.Eagerly(pulling)]将[npc2.namePos][npc2.arm(true)]抬起来，然后把[npc.cock+][npc.cockHead+]对着[npc2.her]露出的腋窝顶了上去，"
								+ "接着开始[npc.eagerly]上下摩擦起[npc.her][npc.cock+]。",

					"[npc.Name]拉起[npc2.namePos]的[npc2.arm(true)]，然后用[npc.cock][npc.cockHead+]对着[npc2.her][npc2.armpit+]摩擦起来。"));

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]开始[npc2.Name]的[npc2.armpit]，[npc2.name]发出[npc2.a_sob+]，"
										+ "眼泪滑下[npc2.her][npc2.face]，[npc2.she]绝望地乞求[npc.herHim]停下。",
								"伴随[npc2.a_sob+]，[npc2.name]试着将[npc.name]推开；"
										+ "讨厌的[npc.cock]在[npc2.armpit]上滑动，眼泪顺着[npc2.face]流了下来。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]开始[npc2.Name]的[npc2.armpit]，[npc2.name]发出[npc2.a_moan+]，"
										+ "[npc2.Name][npc2.eagerly]推回[npc.her]的[npc.cock+]的同时帮着摩擦起它来。",
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地在[npc.namePos][npc.cock+]上来回磨蹭自己的[npc2.armpit]。"));
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
							"作为回应，[npc2.Name]对着[npc.namePos][npc.cock+][npc.eagerly]上下运动[npc2.her]的[npc2.armpit+]，"
									+ "[npc2.she]发出了一阵[npc2.a_moan+]。",
		
							"[npc2.name]急切地用[npc2.armpit+]来回磨蹭[npc.namePos][npc.cock+]，一声[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地用[npc2.armpit+]上下磨蹭[npc.namePos][npc.cock+]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要把[npc2.arm(true)]远离[npc.namePos]的[npc.cock]，但没能成功，"
									+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地乞求[npc.Name]停止使用自己的[npc2.armpit]，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "[npc2.she]恳求[npc.name]放过自己的[npc2.armpit]，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
		
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]远离[npc2.her]的[npc2.armpit]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]向着[npc.namePos][npc.cock+]上下磨蹭[npc2.her]的[npc2.armpit+]，"
									+ "[npc2.she]发出了一阵[npc2.a_moan+]。",
		
							"[npc2.name]用[npc2.armpit+]来回磨蹭[npc.namePos][npc.cock+]，一声[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.armpit+]上下磨蹭[npc.namePos][npc.cock+]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]用[npc2.her][npc2.armpit+]贴着[npc.namePos][npc.cock+]上下磨蹭，"
									+ "[npc2.she]发出了一阵[npc2.a_moan+]。",
		
							"[npc2.name]温柔地用[npc2.armpit+]来回磨蹭[npc.namePos][npc.cock+]，一声[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.armpit+]轻轻地上下磨蹭[npc.namePos][npc.cock+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]向着[npc.namePos][npc.cock+]粗暴地上下磨蹭[npc2.her][npc2.armpit+]，"
									+ "[npc2.she]发出了一阵[npc2.a_moan+]。",
		
							"[npc2.name]激烈地用[npc2.armpit+]来回磨蹭[npc.namePos][npc.cock+]，一声[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.armpit+]激烈上下磨蹭起[npc.namePos][npc.cock+]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction ARMPIT_SEX_RECEIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "腋交(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "温柔地用你[npc.cock+]上下磨蹭[npc2.namePos][npc2.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"在[npc2.namePos][npc2.armpit]上温柔地来回磨蹭[npc.cock+]，"
							+ "[npc.name]开始前后挺动[npc.her]的[npc.hips]，缓缓地操[npc2.her][npc2.armpit+]，每次推入都会发出轻微的[npc.moan]。",

					"用[npc2.namePos][npc2.armpit]温柔地来回磨蹭[npc.cock+]，"
							+ "[npc.name]开始轻柔地将[npc.her]的[npc.hips]向前推，当[npc.she]温柔地操着[npc2.her]的[npc2.armpit+]时，漏出[npc.moans+]。",

					"[npc.name]轻轻地将[npc.cock+]顶向[npc2.namePos]的[npc2.armpit]，发出一声轻微的[npc.moan]，开始温柔地前后摆动[npc.hips]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，缓缓地操[npc2.namePos][npc2.armpit+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "操腋窝";
		}
		@Override
		public String getActionDescription() {
			return "用你[npc.cock+]上下磨蹭[npc2.namePos][npc2.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"饥渴地将[npc.her][npc.cock+]对着[npc2.namePos]的[npc2.armpit]上下摩擦，"
							+ "[npc.name]开始大力地前后挺动[npc.her]的[npc.hips]，缓缓地操[npc2.her][npc2.armpit+]，每次推入都会发出一阵[npc.a_moan+]。",

					"[npc.name]饥渴地用[npc.cock+]上下磨蹭[npc2.namePos]的[npc2.armpit]，"
							+ "疯狂地向前挺进[npc.hips]，贪婪地干着[npc2.her][npc2.armpit+]，不禁发出[npc.a_moan+]。",

					"[npc.Name]贪婪地将[npc.her][npc.cock+]顶在[npc2.namePos]的[npc2.armpit]上，发出一声[npc.a_moan+]并饥渴地前后摆动起[npc.her]的[npc.hips]，"
							+ "[npc.she]一边竭力地操着[npc2.her]的[npc2.armpit+]，一边吸入着[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "腋交(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地用你[npc.cock+]上下磨蹭[npc2.namePos][npc2.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"对着[npc2.namePos]的[npc2.armpit]粗暴地抽插[npc.her][npc.cock+]，"
							+ "[npc.name]开始支配性地前后挺动[npc.her]的[npc.hips]，用力地操[npc2.her][npc2.armpit+]，每次推入都会发出一阵[npc.a_moan+]。",

					"强硬地将自己[npc.cock+]在[npc2.namePos][npc2.armpit]上来回磨蹭，"
							+ "[npc.name]开始暴力地挺进[npc.her]的[npc.hips]，粗鲁地操着[npc2.her][npc2.armpit+]，并发出[npc.a_moan+]。",

					"支配性地将自己[npc.cock+]顶在[npc2.namePos][npc2.armpit]上，[npc.name]发出一声[npc.a_moan+]，开始猛烈地前后摆动[npc.hips]，"
							+ "[npc.she]一边粗暴地操着[npc2.her][npc2.armpit+]，一边吸入着[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "操腋窝";
		}
		@Override
		public String getActionDescription() {
			return "用你[npc.cock+]上下磨蹭[npc2.namePos][npc2.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]用[npc.cock+]来回磨蹭[npc2.namePos]的[npc2.armpit]，"
							+ "[npc.name]开始前后挺动[npc.her]的[npc.hips]，贪婪地操[npc2.her][npc2.armpit+]，每次推入都会发出一阵[npc.a_moan+]。",

					"[npc.name]用[npc.cock+]来回磨蹭[npc2.namePos]的[npc2.armpit]，"
							+ "[npc.Name]发出[npc.a_moan+]，开始向前挺动[npc.hips]，愉悦地操起[npc2.her][npc2.armpit+]。",

					"[npc.Name]将[npc.cock+]顶在[npc2.namePos][npc2.armpit]上，发出一声[npc.a_moan+]，开始前后摆动[npc.hips]，"
							+ "[npc.she]一边操着[npc2.her][npc2.armpit+]，一边吸入着[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "腋交(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "急切地用你[npc.cock+]上下磨蹭[npc2.namePos][npc2.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"饥渴地用[npc.her][npc.cock+]上下磨蹭[npc2.namePos][npc2.armpit]，"
							+ "[npc.name]开始大力地前后挺动[npc.her]的[npc.hips]，缓缓地操[npc2.her][npc2.armpit+]，每次推入都会发出一阵[npc.a_moan+]。",

					"[npc.name]饥渴地用[npc.cock+]上下磨蹭[npc2.namePos]的[npc2.armpit]，"
							+ "疯狂地向前挺进[npc.hips]，贪婪地干着[npc2.her][npc2.armpit+]，不禁发出[npc.a_moan+]。",

					"[npc.name]贪婪地将[npc.cock+]顶在[npc2.namePos][npc2.armpit]上，发出[npc.a_moan+]，开始饥渴地前后摆动[npc.her]的[npc.hips]，"
							+ "[npc.she]竭力地操着[npc2.her][npc2.armpit+]，同时吸入着[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "腋交(抗拒)";
		}
		@Override
		public String getActionDescription() {
			return "试着让你的[npc.cock]远离[npc2.namePos][npc2.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.name]用[npc2.armpit]夹紧[npc.her][npc.cock+]并缓慢地推送起来。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "牢牢地将[npc.herHim]固定在原位，温柔地将[npc2.armpit]压向[npc.her][npc.cock+]。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，请求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.moaning]着，完全无视[npc.her]的抗议，缓缓地将[npc2.armpit]压向[npc.her][npc.cock+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]粗暴地用[npc2.her][npc2.armpit]摩擦[npc.her][npc.cock+]。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "霸道地将[npc.herHim]固定在原位，"
									+ "粗暴地将[npc2.her][npc2.armpit]压向[npc.her][npc.cock+]。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，哀求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.moaning]着，完全无视[npc.her]的抗议，粗暴地将[npc2.armpit]压向[npc.her][npc.cock+]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]饥渴地将[npc2.her][npc2.armpit]压向[npc.her][npc.cock+]。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "牢牢地将[npc.herHim]固定在原位，急切地将[npc2.armpit]压向[npc.her][npc.cock+]。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，哀求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.moaning]着，完全无视[npc.her]的抗议，急切地将[npc2.armpit]压向[npc.her][npc.cock+]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止腋交";
		}
		@Override
		public String getActionDescription() {
			return "将你[npc.cock+]抽离[npc2.namePos][npc2.armpit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将[npc.cock+]从[npc2.namePos][npc2.armpit+]中拉出，"
									+ "霸道地用[npc.cock]的[npc.cockHead]最后一次上下磨蹭[npc2.her][npc2.arm+(true)]，然后将[npc.hips]移开。",

							"[npc.Name]最后一次顶着[npc2.namePos][npc2.armpit+]，之后后退，结束了腋窝性爱。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.cock+]滑离[npc2.namePos][npc2.armpit+]，"
									+ "[npc.she]最后一次用[npc.her][npc.cock]的[npc.cockHead]拍打[npc2.namePos][npc2.arm+(true)]，然后收回[npc.hips]。",

							"[npc.Name]最后一次向上推[npc2.namePos][npc2.armpit+]，之后后退，结束了腋窝性爱。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.name]拔出的时候，[npc2.Name]忍不住发出一声[npc2.sob+]，"
										+ "[npc2.she]仍然不停地哭泣并反抗着，恳求[npc.name]就这样放过自己。",
	
								"发出[npc2.a_sob+]，[npc2.name]仍然挣扎着试图摆脱[npc.name]，当[npc2.she]哀求对方放过自己时，眼泪忍不住像小溪一样从[npc2.her]的[npc2.face]上淌下。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.name]将[npc.cock+]拔出时，[npc2.Name]发出[npc2.a_moan+]，渴求[npc.name]的更多“照顾”。",
	
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]对[npc.namePos][npc.cock+]的渴望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	public static final SexAction ARMPIT_SEX_GIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "被操腋窝";
		}
		@Override
		public String getActionDescription() {
			return "将你的[npc.armpit]按向[npc2.namePos][npc2.cock+]，让[npc2.herHim]开始操它。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"随着[npc.a_moan+]，[npc.Name]提起[npc.her][npc.arm(true)]，急切地将[npc.her][npc.armpit+]压向[npc2.namePos][npc2.cock+]，"
							+ "急切地上下摩擦[npc2.her][npc2.cockHead+]。",

					"[npc.Name]提起[npc.arm(true)]，将[npc.her]下到[npc2.namePos]的腹股沟，急切地将[npc.her][npc.armpit+]压向[npc2.her][npc2.cock+]。"));

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]强迫[npc2.her][npc2.cock+]插进自己的[npc.armpit]，[npc2.Name]忍不住发出[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着，拼命试着推开[npc.name]。",
	
								"[npc.Name]强行将[npc.armpit]压向[npc2.name]的[npc2.cock]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵[npc2.a_moan+]，饥渴地向前挺动[npc2.hips]，急切地磨蹭[npc.namePos]的[npc.armpit]。",
	
								"随着一阵[npc2.a_moan+]，[npc2.name]饥渴地向前挺起[npc2.her]的[npc2.hips]，将[npc2.her][npc2.cock+]压向[npc.namePos][npc.armpit+]。"));
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
							"[npc2.name]贪婪地用[npc2.her][npc2.cock+]在[npc.namePos][npc.armpit+]上摩擦，"
									+ "[npc2.she]发出了一阵[npc2.a_moan+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]急切地将[npc2.cock+]顶向[npc.namePos]的[npc.armpit]。",
									
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地将[npc2.cock+]顶向[npc.namePos][npc.armpit+]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.cock]从[npc.namePos]的[npc.armpit]中拔出，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.cock+]在自己[npc.armpit+]中继续抽插。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.cock]从[npc.namePos][npc.armpit+]间抽离。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.cock+]推向[npc.namePos][npc.armpit+]，"
									+ "[npc2.she]发出了一阵[npc2.a_moan+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]顶向[npc.namePos]的[npc.armpit]。",
									
							"[npc2.name]愉悦地[npc2.moaning]着，开始将[npc2.cock+]顶向[npc.namePos][npc.armpit+]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将自己[npc2.cock+]推向[npc.namePos][npc.armpit+]，"
									+"搞得[npc2.she]发出轻柔的[npc2.moan]。",
		
							"[npc2.name]温柔地将[npc2.cock+]挺入[npc.namePos]的[npc.armpit]，口中飘出轻柔的[npc2.moan]。",
									
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地将[npc2.cock+]顶向[npc.namePos][npc.armpit+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.cock+]推向[npc.namePos][npc.armpit+]，"
									+ "[npc2.she]发出了一阵[npc2.a_moan+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地将[npc2.cock+]顶向[npc.namePos]的[npc.armpit]。",
									
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地将[npc2.cock+]顶向[npc.namePos][npc.armpit+]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction ARMPIT_SEX_GIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "被操腋窝(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "温柔地用你[npc.armpit+]上下磨蹭[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]温柔地在[npc2.namePos][npc2.cock+]上上下滑动[npc.her][npc.armpit]，发出一阵柔软的[npc.moan]。",

					"随着一阵柔软的[npc.moan]，[npc.Name]开始温柔地在[npc2.namePos][npc2.cock+]上上下滑动[npc.her][npc.armpit+]。",

					"[npc.Name]缓慢地将[npc.her][npc.armpit+]压向[npc2.namePos][npc2.cock+]，让[npc2.herHim]操[npc.she]，发出一阵[npc.a_moan+]"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "被操腋窝";
		}
		@Override
		public String getActionDescription() {
			return "继续用你[npc.armpit+]上下磨蹭[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]饥渴地用[npc.her][npc.armpit]在[npc2.namePos][npc2.cock+]上上下滑动，发出一阵[npc.a_moan+]。",

					"随着一阵[npc.a_moan+]，[npc.Name]开始开心地用[npc.her][npc.armpit+]在[npc2.namePos][npc2.cock+]上上下滑动。",

					"[npc.Name]饥渴地将[npc.her][npc.armpit+]压向[npc2.namePos][npc2.cock+]，让[npc2.herHim]操[npc.she]，发出一阵[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "被操腋窝(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地用你[npc.armpit+]上下磨蹭[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]粗暴地在[npc2.namePos][npc2.cock+]上上下摩擦[npc.her]的[npc.armpit]，发出一阵[npc.a_moan+]。",
					"[npc.Name]发出一阵[npc.a_moan+]，激烈地用[npc.armpit+]来回磨蹭[npc2.namePos][npc2.cock+]。",
					"[npc.Name]粗暴地将[npc.her][npc.armpit+]压向[npc2.namePos][npc2.cock+]，让[npc2.herHim]操[npc.she]，发出一阵[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "被操腋窝";
		}
		@Override
		public String getActionDescription() {
			return "继续用你[npc.armpit+]上下磨蹭[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]用[npc.her]的[npc.armpit]在[npc2.namePos][npc2.cock+]上上下滑动，发出一阵[npc.a_moan+]。",

					"随着一阵[npc.a_moan+]，[npc.Name]开始开心地用[npc.her][npc.armpit+]在[npc2.namePos][npc2.cock+]上上下滑动。",

					"[npc.Name]将[npc.her][npc.armpit+]压向[npc2.namePos][npc2.cock+]，让[npc2.herHim]操[npc.she]，发出一阵[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "被操腋窝(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "饥渴地用你[npc.armpit+]上下磨蹭[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]饥渴地用[npc.her][npc.armpit]在[npc2.namePos][npc2.cock+]上上下滑动，发出一阵[npc.a_moan+]。",

					"随着一阵[npc.a_moan+]，[npc.Name]开始开心地用[npc.her][npc.armpit+]在[npc2.namePos][npc2.cock+]上上下滑动。",

					"[npc.Name]饥渴地将[npc.her][npc.armpit+]压向[npc2.namePos][npc2.cock+]，让[npc2.herHim]操[npc.she]，发出一阵[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "被操腋窝(抗拒)";
		}
		@Override
		public String getActionDescription() {
			return "努力让你[npc.armpit+]远离[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]的眼泪在[npc.her][npc.eyes]中打转，无法继续忍受，突然发出[npc.a_sob+]，"
									+ "虚弱地尝试摆脱[npc2.namePos][npc2.cock]，[npc2.she]继续温柔地操[npc.her]的[npc.armpit]。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.armpit]从[npc2.namePos]的[npc2.cock]抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然从容地在[npc.her]的[npc.armpit]里继续抽插。",

							"[npc.name]拼命地尝试将[npc.armpit]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然温柔地磨蹭着[npc.her]的[npc.armpit]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到泪水涌上自己的眼窝，下一瞬间，[npc.she]开始啜泣起来，"
									+ "虚弱地试图摆脱[npc2.namePos][npc2.cock]，[npc2.she]继续饥渴地操[npc.her]的[npc.armpit]。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.armpit]从[npc2.namePos]的[npc2.cock]抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然急切地在[npc.her]的[npc.armpit]里继续抽插。",

							"[npc.name]拼命地尝试将[npc.armpit]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然急切地磨蹭着[npc.her]的[npc.armpit]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"眼泪慢慢涌上[npc.eyes]，再也无法忍住哭意，[npc.Name]一阵阵地啜泣起来，"
									+ "虚弱地试图摆脱[npc2.namePos][npc2.cock]，[npc2.she]继续粗暴地操[npc.her]的[npc.armpit]，",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.armpit]从[npc2.namePos]的[npc2.cock]抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然粗暴地在[npc.her]的[npc.armpit]里继续抽插。",

							"[npc.name]拼命地尝试将[npc.armpit]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然粗暴地磨蹭着[npc.her]的[npc.armpit]。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止被操腋窝";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.cock+]从你[npc.armpit+]旁挪开。";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]把[npc.armpit]从[npc2.namePos][npc2.cock+]处移开，凶狠地咆哮着命令[npc2.herHim]滚开。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后迅速地将[npc.armpit]远离[npc2.her][npc2.cock+]。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]将[npc.armpit]从[npc2.namePos][npc2.cock+]处移开，发出一阵[npc.a_moan+]，让[npc2.herHim]离开。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc.armpit]远离[npc2.her][npc2.cock+]。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]松了一口气，但当[npc2.she]意识到[npc.name]还没满足时，又发出了一阵[npc2.a_sob+]。",
	
								"[npc2.name]发出一阵[npc2.a_sob+]，继续反抗并挣扎着，但[npc.name]依然牢牢地将[npc2.she]固定在原位。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]流出，背叛[npc2.her]的欲望继续操[npc.her]的[npc.armpit]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	
}
