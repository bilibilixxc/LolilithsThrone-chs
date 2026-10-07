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
 * @since 0.1.82
 * @version 0.2.9
 * @author Innoxia
 */
public class PenisUrethraPenis {
	
	public static final SexAction PENILE_URETHRA_SEX_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "操阴茎尿道";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.cock+]插进[npc2.namePos][npc2.penisUrethra+]并开始操[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]慢慢用[npc.cock][npc.cockTip+]挑逗着[npc2.namePos][npc2.penis+][npc2.cockHead+]，"
									+ "发出轻微的[npc.moan]，缓缓地向前推，将[npc.cock+]没入[npc2.namePos][npc2.penisUrethra+]里。",

							"[npc.Name]将其[npc.cock][npc.cockTip+]抵住[npc2.namePos][npc2.penis+][npc2.cockHead+]，"
									+ "[npc.she]以缓慢而稳定的力度，轻柔地将它深深地插入[npc2.namePos][npc2.penisUrethra+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.cock][npc.cockTip+]饥渴地挑逗着[npc2.namePos][npc2.penis+][npc2.cockHead+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.cock+]深深插入[npc2.her][npc2.penisUrethra+]。",

							"[npc.Name]将其[npc.cock][npc.cockTip+]抵住[npc2.namePos][npc2.penis+][npc2.cockHead+]，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos][npc2.penisUrethra+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.cock][npc.cockTip+]粗暴地磨蹭[npc2.namePos][npc2.penis+][npc2.cockHead+]，"
									+ "发出[npc.a_moan+]，然后粗暴地向前猛推，将[npc.cock+]深深插入[npc2.her][npc2.penisUrethra+]。",

							"[npc.Name]将其[npc.cock][npc.cockTip+]抵住[npc2.namePos][npc2.penis+][npc2.cockHead+]，"
									+ "用力前推，粗暴地将它深深插入[npc2.her][npc2.penisUrethra+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.cock][npc.cockTip+]饥渴地挑逗着[npc2.namePos][npc2.penis+][npc2.cockHead+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.cock+]深深插入[npc2.her][npc2.penisUrethra+]。",

							"[npc.Name]将其[npc.cock][npc.cockTip+]抵住[npc2.namePos][npc2.penis+][npc2.cockHead+]，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos][npc2.penisUrethra+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.cock][npc.cockTip+]挑逗着[npc2.namePos][npc2.penis+][npc2.cockHead+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，将[npc.cock+]深深插入[npc2.her][npc2.penisUrethra+]。",

							"[npc.Name]将其[npc.cock][npc.cockTip+]抵住[npc2.namePos][npc2.penis+][npc2.cockHead+]，"
									+ "[npc.she]小幅度前推，将它深深插入[npc2.namePos][npc2.penisUrethra+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.cock+]顶进[npc2.name]体内，[npc2.herHim]发出轻柔的[npc2.moan]，"
										+ "于是[npc2.she]温柔地挺起自己的[npc2.hips]，以便让它在[npc2.penisUrethra+]里插得更深。",
	
								"[npc2.name]轻柔地[npc2.moan]，开始温柔地扭动[npc2.hips]，"
										+ "将[npc.namePos][npc.cock+]更深地插入自己[npc2.penisUrethra+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.cock+]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "于是[npc2.her]粗暴地挺起[npc2.hips]，强迫它在自己[npc2.penisUrethra+]里插得更深。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始猛烈地摇动[npc2.hips]，"
										+ "粗暴地强迫[npc.Name]把[npc.her][npc.cock+]在自己[npc2.penisUrethra+]里插得更深。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.cock+]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.she]急切地扭动[npc2.hips]，让它在[npc2.penisUrethra+]里插得更深。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地扭动[npc2.hips]，"
										+ "饥渴地让[npc.namePos][npc.cock+]更加深入[npc2.her][npc2.penisUrethra+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.cock+]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.she]扭动[npc2.hips]，让它在[npc2.penisUrethra+]里插得更深。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始扭动[npc2.hips]，"
										+ "让[npc.namePos][npc.cock+]更加深入[npc2.her][npc2.penisUrethra+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.cock+]进入了自己的身体，不禁漏出一声[npc2.a_sob+]，"
										+ "泪水不停地从[npc2.her]的[npc2.face]上淌下，[npc2.she]哀求[npc.Name]从自己身体里拔出来。",
	
								"[npc2.name]发出[npc2.a_sob+]，徒劳地挣扎扭身，试图拔出讨厌的插入物，"
										+ "但[npc.namePos]不请自来的[npc.cock]却在[npc2.penisUrethra+]中插得更深，泪水从[npc2.her]的[npc2.face]上流了下来。"));
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
							"[npc2.Name]热切地扭动[npc2.hips]作为回应，"
									+ "[npc2.she]热切地帮着[npc.namePos][npc.cock+]深深插入[npc2.her][npc2.penisUrethra+]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]急切地挺起[npc2.hips]，乞求[npc.name]继续操[npc2.herHim]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，急切地挺起[npc2.hips+]，"
									+ "做出有助于[npc.cock+]更深地插入[npc2.her][npc2.penisUrethra+]的动作，急切地乞求[npc.Name]继续操[npc2.herHim]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要逃离[npc.namePos]的[npc.cock]，但没能成功，"
									+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地乞求[npc.Name]从自己[npc2.penisUrethra+]里拔出来，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "[npc2.she]恳求[npc.name]从自己[npc2.penisUrethra+]拔出来，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
		
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]从[npc2.her][npc2.penisUrethra+]里拔出来。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]扭动[npc2.hips]作为回应，"
									+ "[npc2.she]帮着[npc.namePos][npc.cock+]深深插入[npc2.her][npc2.penisUrethra+]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
	
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]挺起[npc2.hips]，乞求[npc.name]继续操[npc2.herHim]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，饥渴地挺起[npc2.hips+]，"
									+ "做出有助于[npc.cock+]更深地插入[npc2.her][npc2.penisUrethra+]的动作，乞求[npc.Name]继续操[npc2.herHim]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]慢慢地扭动[npc2.hips]作为回应，"
									+ "[npc2.she]发出一声轻柔的[npc2.moan]，温柔地乞求着[npc.Name]继续操干[npc2.her][npc2.penisUrethra+]。",
	
							" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
									+ "[npc2.she]缓缓地晃动[npc2.hips]，恳求[npc.Name]继续操[npc2.herHim]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，轻柔地将[npc2.hips+]向后压去，"
									+ "做出有助于[npc.cock+]更深地插入[npc2.her][npc2.penisUrethra+]的动作，乞求[npc.Name]继续操[npc2.herHim]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地扭动[npc2.hips]作为回应，"
									+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地命令[npc.Name]继续干[npc2.herHim]。",
	
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]粗鲁地用[npc2.hips]压向[npc.namePos]的腹股沟，命令[npc.Name]继续操[npc2.herHim]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地用[npc2.hips+]猛然撞击[npc.namePos]的胯下，"
									+ "强迫[npc.name]将[npc.cock+]深深插入[npc2.her][npc2.penisUrethra+]，命令[npc.name]继续操[npc2.herHim]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction PENILE_URETHRA_SEX_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "操尿道(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地把你的[npc.cock]滑进[npc2.namePos][npc2.penisUrethra+]，开始抽插。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]温柔地将[npc.cock+]深深插入[npc2.namePos][npc2.penisUrethra+]，"
							+ "[npc.name]开始前后摇摆[npc.hips]，缓缓地操[npc2.name]，每次推入都会发出一阵轻微的[npc.moan]。",

					"[npc.name]缓缓地将[npc.cock+]插入[npc2.namePos][npc2.penisUrethra+]，"
							+ "轻柔地将[npc.hips]向前推，[npc.she]温柔地干着[npc2.name]，不禁漏出一小声[npc.moan]。",

					"[npc.Name]将[npc.cock+]滑入[npc2.namePos][npc2.penisUrethra+]，开始温柔地前后抽动[npc.hips]，不禁漏出一小声[npc.moan]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，同时缓缓地操[npc2.name]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENILE_URETHRA_SEX_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "操尿道";
		}

		@Override
		public String getActionDescription() {
			return "继续在[npc2.namePos][npc2.penisUrethra+]里抽送你[npc.cock+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.cock+]深深插入[npc2.namePos][npc2.penisUrethra+]，"
							+ "[npc.name]开始热切地前后摇摆[npc.hips]，兴奋地操[npc2.name]，每次突入都会发出一阵[npc.a_moan+]。",

					"[npc.name]亢奋地将[npc.cock+]深深插入[npc2.namePos][npc2.penisUrethra+]，"
							+ "开始疯狂地向前挺进[npc.hips]，贪婪地干着[npc2.name]，不禁发出[npc.a_moan+]。",

					"[npc.Name]将[npc.cock+]深深插入[npc2.namePos][npc2.penisUrethra+]，开始热切地用[npc.hips]前后抽送打桩，不禁发出[npc.a_moan+]，"
							+ "[npc.she]兴奋地干着[npc2.namePos]，呼吸着[npc2.herHim]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENILE_URETHRA_SEX_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "操尿道(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地用你[npc.cock+]在[npc2.namePos][npc2.penisUrethra+]里进进出出。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]粗暴地将[npc.cock+]深深插入[npc2.namePos][npc2.penisUrethra+]，"
							+ "[npc.name]开始粗暴地前后摇摆[npc.hips]，野蛮地操[npc2.name]，每次推入都会发出一阵[npc.a_moan+]。",

					"[npc.name]激烈地将[npc.cock+]深深插入[npc2.namePos][npc2.penisUrethra+]，"
							+ "开始粗暴地向前挺进[npc.hips]，激烈地干着[npc2.name]，不禁发出[npc.a_moan+]。",

					"[npc.Name]激烈地将[npc.cock+]深深插入[npc2.namePos][npc2.penisUrethra+]，[npc.hips]粗暴地来回撞击，不禁发出[npc.a_moan+]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，同时激烈地操[npc2.name]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENILE_URETHRA_SEX_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "操尿道";
		}

		@Override
		public String getActionDescription() {
			return "继续操[npc2.namePos][npc2.penisUrethra+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.cock+]深深插入[npc2.namePos][npc2.penisUrethra+]，"
							+ "[npc.name]开始前后摇摆[npc.hips]，兴奋地操[npc2.name]，每次突入都会发出一阵[npc.a_moan+]。",

					"[npc.name]将[npc.cock+]深深插入[npc2.namePos][npc2.penisUrethra+]，"
							+ "开始向前挺动[npc.hips]，急切地操着[npc2.herHim]，不禁发出[npc.a_moan+]。",

					"[npc.Name]将[npc.cock+]深深插入[npc2.namePos][npc2.penisUrethra+]，开始用[npc.hips]前后抽送打桩，不禁发出[npc.a_moan+]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，同时操着[npc2.name]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENILE_URETHRA_SEX_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "操尿道(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "渴求地把你[npc.cock+]推入[npc2.namePos][npc2.penisUrethra+]，开始抽插。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.cock+]深深插入[npc2.namePos][npc2.penisUrethra+]，"
							+ "[npc.name]开始热切地前后摇摆[npc.hips]，兴奋地操[npc2.name]，每次突入都会发出一阵[npc.a_moan+]。",

					"[npc.name]亢奋地将[npc.cock+]深深插入[npc2.namePos][npc2.penisUrethra+]，"
							+ "开始疯狂地向前挺进[npc.hips]，贪婪地干着[npc2.name]，不禁发出[npc.a_moan+]。",

					"[npc.Name]将[npc.cock+]深深插入[npc2.namePos][npc2.penisUrethra+]，开始热切地用[npc.hips]前后抽送打桩，不禁发出[npc.a_moan+]，"
							+ "[npc.she]兴奋地干着[npc2.namePos]，呼吸着[npc2.herHim]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENILE_URETHRA_SEX_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抗拒操尿道";
		}

		@Override
		public String getActionDescription() {
			return "努力把你的[npc.cock]从[npc2.namePos][npc2.penisUrethra+]里拔出去。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]尝试将[npc.cock]从[npc2.namePos]的[npc2.penisUrethra]里拔出来，"
									+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就低下去轻轻地抓住它，然后温柔地把它扭回[npc2.her][npc2.penisUrethra+]。",
	
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.cock]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并温柔地把它扭回[npc2.her][npc2.penisUrethra+]里。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock]从[npc2.namePos][npc2.penisUrethra+]里拔出来，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边温柔地将[npc2.penisUrethra+]压向[npc.her][npc.cock+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]尝试将[npc.cock]从[npc2.namePos]的[npc2.penisUrethra]里拔出来，"
									+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就向后粗暴地抓住它，然后蛮横地把它扭回[npc2.her][npc2.penisUrethra+]。",
	
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.cock]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并粗暴地把它扭回[npc2.her][npc2.penisUrethra+]里。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock]从[npc2.namePos][npc2.penisUrethra+]里拔出来，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边粗暴地将[npc2.penisUrethra+]压向[npc.her][npc.cock+]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]尝试将[npc.cock]从[npc2.namePos]的[npc2.penisUrethra]里拔出来，"
									+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就向后牢牢地抓住它，然后热切地把它扭回[npc2.her][npc2.penisUrethra+]。",
	
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.cock]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并渴望地把它扭回[npc2.her][npc2.penisUrethra+]里。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock]从[npc2.namePos][npc2.penisUrethra+]里拔出来，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边将[npc2.penisUrethra+]压向[npc.her][npc.cock+]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENILE_URETHRA_SEX_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止操尿道";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.cock+]从[npc2.namePos][npc2.penisUrethra+]里拔出去，停止操[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将[npc.cock+]从[npc2.namePos][npc2.penisUrethra+]中拉出，"
									+ "霸道地用[npc.cockTip+]最后一次上下磨蹭[npc2.her][npc2.penis+][npc2.cockHead+]，然后将它移开。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.cock+]从[npc2.her][npc2.penisUrethra+]中猛抽出来，结束了粗暴性交。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.cock]从[npc2.namePos][npc2.penisUrethra+]中抽出，"
									+ "用[npc.cockTip]最后一次上下磨蹭[npc2.her][npc2.penis+][npc2.cockHead+]，然后将它移开。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.cock+]从[npc2.her][npc2.penisUrethra+]中拔出来，结束了性交。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.Name]从[npc2.her]的[npc2.penisUrethra]里拔出来时，[npc2.name]忍不住漏出一声[npc2.sob+]，"
										+ "[npc2.she]仍然不停地哭泣，无力地继续反抗着[npc.name]。",
	
								"[npc2.name]发出一阵[npc2.a_sob+]，拼命地挣扎着反抗[npc.Name]，将[npc2.penisUrethra+]从[npc.Name]身上抽离，泪水如小溪般从[npc2.face]上流了下来。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.name]将[npc.cock+]拔出[npc2.her][npc2.penisUrethra+]时，[npc2.Name]发出[npc2.a_moan+]，渴求[npc.name]的更多“照顾”。",
	
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]无比渴望得到[npc.namePos]的更多关注。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "被操阴茎尿道";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.namePos][npc2.cock+]滑进你[npc.penisUrethra+]，你开始被操。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.cock]，慢慢地将[npc.cockHead+]引导至自己[npc.penis+]，"
									+ "[npc.her]温柔地摇晃着[npc.hips]，不时漏出一小声[npc.moan]，强迫[npc2.herHim]插入[npc.her][npc.penisUrethra+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.cock]，把它对准自己[npc.penisUrethra+]，"
									+ "慢慢地将[npc.hips]向后顶，将[npc2.her][npc2.cock+]插入自己，不禁漏出一声轻柔的[npc.moan]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.cock]，急切地将它引导到自己[npc.penis+][npc.cockHead+]，"
									+ "[npc.her]亢奋地摇晃着[npc.hips]，不时漏出一阵[npc.a_moan+]，强迫[npc2.herHim]插入[npc.her][npc.penisUrethra+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.cock]，把它对准自己[npc.penisUrethra+]，"
									+ "[npc.she]热切地向后顶[npc.hips]，吞下[npc2.her][npc2.cock+]，[npc.she]不禁漏出一声[npc.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]抓住[npc2.namePos]的[npc2.cock]，粗暴地把拉到自己[npc.penis+][npc.cockHead+]，"
									+ "[npc.her]暴力地猛烈摇晃[npc.hips]，不时漏出一阵[npc.a_moan+]，强迫[npc2.herHim]操进[npc.her][npc.penisUrethra+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.cock]，把它对准自己[npc.penisUrethra+]，"
									+ "[npc.she]急切地向后猛撅[npc.hips]，裹住[npc2.her][npc2.cock+]，[npc.she]不禁漏出一声[npc.a_moan+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.cock]，将它引导到自己[npc.penis+][npc.cockHead+]，"
									+ "[npc.her]摇晃着[npc.hips]，不时漏出一阵[npc.a_moan+]，强迫[npc2.herHim]插入[npc.her][npc.penisUrethra+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.cock]，把它对准自己[npc.penisUrethra+]，"
									+ "[npc.she]向后顶[npc.hips]，吞下[npc2.her][npc2.cock+]，[npc.she]不禁漏出一声[npc.a_moan+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.herHim]的身体，发出一声轻柔的[npc2.moan]，"
										+ "温柔地将[npc2.cock]向前顶，开始操[npc.namePos][npc.penisUrethra+]。",
	
								"[npc2.name]轻柔地[npc2.moan]着，温柔地挺进自己的[npc2.cock]，"
										+ "将它深深插进[npc.namePos][npc.penisUrethra+]，开始操[npc.herHim]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "[npc2.she]热切地将[npc2.cock]向前顶，开始亢奋地操[npc.namePos][npc.penisUrethra+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，热切地顶进自己的[npc2.cock]，"
										+ "将它深深插进[npc.namePos][npc.penisUrethra+]，开始竭力地操[npc.herHim]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "为了警告[npc.name]别得意忘形，[npc2.she]粗暴地向前猛撅[npc2.cock]，开始无情地暴操[npc.her][npc.penisUrethra+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，粗暴地猛塞自己的[npc2.cock]，"
										+ "[npc2.she]无情地暴操[npc.namePos][npc.penisUrethra+]，宣告着自己的支配权。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]挺进[npc.name]的身体，不禁漏出一声[npc2.a_moan+]，[npc2.she]将[npc2.cock]向前顶，开始操[npc.namePos][npc.penisUrethra+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，向前挺进[npc2.cock]，将它深深插入[npc.namePos][npc.penisUrethra+]，开始操[npc.herHim]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]紧紧裹住[npc2.name]的[npc2.cock]，[npc2.name]不禁发出一声[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着推开[npc.name]，拼命地想把自己[npc2.cock+]从对方[npc.penisUrethra+]里拔出来。",
	
								"[npc.Name]强行将[npc2.name]的[npc2.cock]深深插入自己[npc.penisUrethra+]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
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
							"[npc2.Name]贪婪地将自己[npc2.cock+]插入[npc.namePos][npc.penisUrethra+]，"
									+ "[npc2.she]发出[npc2.a_moan+]，亢奋地插入[npc.namePos][npc.penisUrethra+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]深深插入[npc.namePos][npc.penisUrethra+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，热切地顶着[npc2.her][npc2.cock+]，拼命要插入[npc.namePos][npc.penisUrethra+]最深处。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.cock]从[npc.namePos]的[npc.penisUrethra]中拔出，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.cock+]在自己[npc.penisUrethra+]中继续抽插。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.cock]从[npc.namePos][npc.penisUrethra+]中抽离。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将[npc2.cock+]滑入[npc.namePos][npc.penisUrethra+]深处，"
									+ "[npc2.she]发出柔和的[npc2.moan]，轻柔地抽插[npc.namePos][npc.penisUrethra+]。",
		
							"[npc2.name]慢慢地将[npc2.cock+]挺入[npc.namePos][npc.penisUrethra+]，口中飘出一声轻柔的[npc2.moan]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地将[npc2.cock+]滑入[npc.namePos][npc.penisUrethra+]深处。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.cock+]深深插入[npc.namePos][npc.penisUrethra+]，"
									+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地插入[npc.namePos][npc.penisUrethra+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地将[npc2.cock+]深深插入[npc.namePos][npc.penisUrethra+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地顶着[npc2.her][npc2.cock+]，拼命要插入[npc.namePos][npc.penisUrethra+]最深处。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.cock+]深深插入[npc.namePos][npc.penisUrethra+]，"
									+ "[npc2.she]发出一阵[npc2.a_moan+]，不停抽插着[npc.namePos][npc.penisUrethra+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]深深插入[npc.namePos][npc.penisUrethra+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，开始将[npc2.cock+]深深插入[npc.namePos][npc.penisUrethra+]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "被操尿道(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "用你[npc.penisUrethra+]温柔地吞吐[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声轻柔的[npc.moan]，温柔地挺起[npc.hips]，以便[npc2.namePos][npc2.cock+]可以在自己[npc.penisUrethra+]中插得更深。",

					"[npc.name]发出一声轻柔的[npc.moan]，温柔地将[npc.hips]向后拱起，迫使[npc2.namePos][npc2.cock+]在自己[npc.penisUrethra+]中插得更深。",

					"[npc.name]慢慢地扭动[npc.hips]，"
							+ "[npc.lips+]间飘出一声轻柔的[npc.moan]，[npc.her]设法让[npc2.namePos][npc2.cock+]深深插入自己[npc.penisUrethra+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "被操尿道";
		}

		@Override
		public String getActionDescription() {
			return "用你[npc.penisUrethra+]饥渴地吞吐[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一阵[npc.a_moan+]，饥渴地拱起[npc.hips]，以便[npc2.namePos][npc2.cock+]可以在自己[npc.penisUrethra+]中插得更深。",

					"[npc.name]发出一阵[npc.a_moan+]，亢奋地拱起[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己[npc.penisUrethra+]中插得更深。",

					"[npc.name]积极地扭动[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.cock+]插入自己[npc.penisUrethra+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "被操尿道(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地用你[npc.penisUrethra+]吞下[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一阵[npc.a_moan+]，暴力地撞击[npc.hips]，强迫[npc2.namePos][npc2.cock+]深深插入自己[npc.penisUrethra+]。",

					"[npc.name]发出一阵[npc.a_moan+]，粗暴地拱起[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己[npc.penisUrethra+]中插得更深。",

					"[npc.name]激烈地扭动[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]粗暴地让[npc2.namePos][npc2.cock+]插入自己[npc.penisUrethra+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "被操尿道";
		}

		@Override
		public String getActionDescription() {
			return "用你[npc.penisUrethra+]吞下[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一阵[npc.a_moan+]，迅速拱起[npc.hips]，以便[npc2.namePos][npc2.cock+]可以在自己[npc.penisUrethra+]中插得更深。",

					"[npc.name]发出一阵[npc.a_moan+]，开始拱起[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己[npc.penisUrethra+]中插得更深。",

					"[npc.name]扭动[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.cock+]插入自己[npc.penisUrethra+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "被操尿道(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "用你[npc.penisUrethra+]饥渴地吞吐[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一阵[npc.a_moan+]，饥渴地拱起[npc.hips]，以便[npc2.namePos][npc2.cock+]可以在自己[npc.penisUrethra+]中插得更深。",

					"[npc.name]发出一阵[npc.a_moan+]，亢奋地拱起[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己[npc.penisUrethra+]中插得更深。",

					"[npc.name]积极地扭动[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.cock+]插入自己[npc.penisUrethra+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抗拒被操尿道";
		}

		@Override
		public String getActionDescription() {
			return "努力让你[npc.penisUrethra+]远离[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
									+ "[npc.she]无力地试着将[npc2.namePos][npc2.cock]从自己[npc.penisUrethra+]里拔出来。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.penisUrethra]从[npc2.namePos]令人憎恶的性器抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.cock+]依然从容地在[npc.her][npc.penisUrethra+]里滑进滑出。",

							"[npc.name]拼命地尝试把[npc.hips]挪开，"
									+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然温柔地滑进[npc.her][npc.penisUrethra+]深处。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
									+ "[npc.she]无力地试着将[npc2.namePos][npc2.cock]从自己[npc.penisUrethra+]里拔出来。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.penisUrethra]从[npc2.namePos]令人憎恶的性器抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.cock+]依然疯狂地在[npc.her][npc.penisUrethra+]里抽送爆操。",

							"[npc.name]拼命地尝试把[npc.hips]挪开，"
									+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然贪婪地插入[npc.her][npc.penisUrethra+]深处。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
									+ "[npc.she]无力地试着将[npc2.namePos][npc2.cock]从自己[npc.penisUrethra+]里拔出来。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.penisUrethra]从[npc2.namePos]令人憎恶的性器抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.cock+]依然粗暴地在[npc.her][npc.penisUrethra+]里抽送爆操。",

							"[npc.name]拼命地尝试把[npc.hips]挪开，"
									+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然激烈地插入[npc.her][npc.penisUrethra+]深处。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "停止操尿道";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.her]的[npc2.cock]从你[npc.penisUrethra+]里拔出来。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]的[npc2.cock]从自己[npc.penisUrethra+]里抽出，[npc.she]愤怒地咆哮着，命令[npc2.name]不准再操了。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]的[npc2.cock]从自己[npc.penisUrethra+]中抽出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]的[npc2.cock]从自己[npc.penisUrethra+]中抽出，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]不要再操了。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc2.her]的[npc2.cock]从自己[npc.penisUrethra+]中抽出。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]松了一口气，但当[npc2.she]意识到[npc.name]还没完全满足时，又发出了一阵[npc2.a_sob+]。",
	
								"[npc2.name]发出一阵[npc2.a_sob+]，继续反抗并挣扎着，但[npc.name]依然牢牢地将[npc2.she]固定在原位。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]不再让[npc2.name]操自己[npc.penisUrethra+]，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
	
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续抽插[npc.namePos][npc.penisUrethra+]的渴望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
