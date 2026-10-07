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
 * @since 0.1.90
 * @version 0.2.9
 * @author Innoxia
 */
public class PenisThighs {
	
	public static final SexAction THIGH_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "开始腿交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.cock+]在[npc2.namePos]大腿间滑动，开始操干。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]缓缓合拢[npc2.namePos][npc2.legs+]，将[npc.cock+][npc.cockHead+]压到[npc2.namePos]股间，"
									+ "然后开始在形成的肉缝中缓慢地抽送[npc.her][npc.cock+]。",

							"[npc.Name]将[npc.her][npc.cockHead+]柔缓地抵在[npc2.namePos][npc2.legs+]上，"
									+ "[npc.she]温柔地将[npc2.her]的股间挤到一起，然后开始操形成的肉缝。"));
					break;
				case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]饥渴地合拢[npc2.namePos][npc2.legs+]，将[npc.cock+][npc.cockHead+]压到[npc2.namePos]股间，"
									+ "然后开始贪婪地将[npc.her]的[npc.hips]推向自己的胯部，开始操臀缝。",

							"[npc.Name]将[npc.her][npc.cock][npc.cockHead+]抵在[npc2.namePos][npc2.legs+]上，"
									+ "然后迫不及待地把[npc2.her]的大腿压在一起，开始操弄形成的缝隙。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗暴地合拢[npc2.namePos][npc2.legs+]，激烈地将[npc.cock+][npc.cockHead+]压到[npc2.namePos]股间，"
									+ "然后暴力地[npc.her]的[npc.hips]推向自己，开始操臀缝。",

							"[npc.Name]将[npc.her][npc.cock][npc.cockHead+]抵在[npc2.namePos][npc2.legs+]上，"
									+ "然后开始粗鲁地挤压[npc2.her]的股间到一起，大力操弄形成的肉缝。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]合拢[npc2.namePos][npc2.legs+]，将[npc.cock+][npc.cockHead+]压到[npc2.namePos]股间，"
									+ "然后把[npc.her]的[npc.hips]推向自己，开始操臀缝。",

							"[npc.Name]将[npc.her][npc.cock][npc.cockHead+]抵在[npc2.namePos][npc2.legs+]上，"
									+ "然后把[npc2.her]的大腿压在一起，开始操弄形成的缝隙。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]开始用[npc2.name]的[npc2.legs]，[npc2.Name]发出轻柔的[npc2.moan]，"
										+ "[npc2.she]轻轻地扭动[npc2.her][npc2.hips+]，让[npc.herHim][npc.cock+]更加深入[npc2.her]的股间中。",
	
								"[npc2.name]轻柔地[npc2.moan]，开始温柔地压向[npc.namePos]的胯部，"
										+ "将[npc.her][npc.cock+]更深地插入自己的股间。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]开始使用[npc2.namePos]的[npc2.legs]，[npc2.Name]发出[npc2.a_moan+]，"
										+ "[npc2.she]饥渴地向后顶去，让[npc.namePos][npc.cock+]更加深入自己的股间。",
	
								"伴随着[npc2.a_moan+]，[npc2.name]开始急切地顶向[npc.namePos]的胯部，"
										+ "饥渴地让[npc.namePos][npc.cock+]更加深入[npc2.her]的股间。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]开始使用[npc2.namePos]的[npc2.legs]，[npc2.Name]发出[npc2.a_moan+]，"
										+ "[npc2.she]激烈地向后顶去，粗暴地强迫[npc.namePos][npc.cock+]更加深入自己的股间。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始猛烈地顶向[npc.namePos]的胯部，"
										+ "粗暴地强迫[npc.namePos][npc.cock+]更加深入[npc2.her]的股间。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]开始使用[npc2.namePos]的[npc2.legs]，[npc2.Name]发出[npc2.a_moan+]，"
										+ "[npc2.she]饥渴地向后顶去，让[npc.namePos][npc.cock+]更加深入自己的股间。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地顶向[npc.namePos]的胯部，"
										+ "饥渴地让[npc.namePos][npc.cock+]更加深入[npc2.her]的股间。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]开始使用[npc2.namePos]的[npc2.legs]，[npc2.Name]发出[npc2.a_moan+]，"
										+ "[npc2.she]向后顶去，帮助[npc.namePos][npc.cock+]更加深入自己的股间。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始顶向[npc.namePos]的胯部，"
										+ "让[npc.namePos][npc.cock+]更加深入[npc2.her]的股间。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]开始用[npc2.name]的[npc2.legs]窝，[npc2.Name]发出[npc2.a_sob+]，并且，"
										+ "泪水不停地从[npc2.her][npc2.face]上淌下，[npc2.she]撕心裂肺地哀求[npc.herHim]停下。",
	
								"伴随[npc2.a_sob+]，[npc2.name]试着将[npc.name]推开；"
										+ "但[npc.namePos]不请自来的[npc.cock]却在股间插得更深，泪水从[npc2.her]的[npc2.face]上流了下来。"));
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
							"作为回应，[npc2.Name]急切地将[npc2.hips]向后压，"
									+ "[npc2.she]控制着[npc.namePos][npc.cock+]在[npc2.her]股间进进出出，发出一阵[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]饥渴地地扭动[npc2.hips]，让[npc.namePos][npc.cock+]在[npc2.her][npc2.legs+]间进进出出。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，热切地前后摆动[npc2.her][npc2.hips+]，"
									+ "做出有助于[npc.cock+]在[npc2.her]股间来回磨蹭的动作，乞求[npc.Name]继续操[npc2.her]的[npc2.legs]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要逃离[npc.namePos]的[npc.cock]，但没能成功，"
									+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地乞求[npc.Name]从自己股间拔出来，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "[npc2.she]恳求[npc.name]从自己的股间拔出来，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
		
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]从[npc2.her]的股间拔出来。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]将[npc2.hips]向后压，"
									+ "[npc2.she]控制着[npc.namePos][npc.cock+]在[npc2.her]股间进进出出，发出一阵[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]扭动[npc2.hips]，让[npc.namePos][npc.cock+]在[npc2.her][npc2.legs+]间进进出出。",
		
							"[npc2.name]一边愉悦地[npc2.Moaning]，一边前后摆动[npc2.her][npc2.hips+]，"
									+ "做出有助于[npc.cock+]在[npc2.her]股间来回磨蹭的动作，乞求[npc.Name]继续操[npc2.her]的[npc2.legs]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]温柔地将[npc2.her][npc2.legs+]向后压，"
									+ "[npc2.she]发出一声轻柔的[npc2.moan]，控制着[npc.namePos][npc.cock+]在[npc2.her]股间进进出出。",
		
							"[npc2.namePos][npc2.lips+]间飘出一声轻柔的[npc2.moan]，"
									+ "[npc2.she]缓缓地扭动[npc2.hips]，让[npc.namePos][npc.cock+]在[npc2.her][npc2.legs+]间进进出出。",
		
							"[npc2.name]愉悦地[npc2.Moaning]着，温柔地前后摆动[npc2.hips+]，"
									+ "做出有助于[npc.cock+]在[npc2.her]股间来回磨蹭的动作，乞求[npc.Name]继续操[npc2.her]的[npc2.legs]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]粗暴地将[npc2.hips]向后压，"
									+ "[npc2.she]控制着[npc.namePos][npc.cock+]在[npc2.her]股间抽插进出，发出一阵[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]粗鲁地摇动[npc2.hips]，迫使[npc.namePos][npc.cock+]在[npc2.her][npc2.legs+]间进进出出。",
		
							"[npc2.name]高兴地[npc2.Moaning]，霸道地前后摆动[npc2.her][npc2.hips+]，"
									+ "做出有助于[npc.cock+]在[npc2.her]股间来回磨蹭的动作，命令[npc.Name]继续操[npc2.her]的[npc2.legs]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction THIGH_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "腿交(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地操[npc2.namePos]的大腿。";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"在[npc2.namePos]股间温柔地滑动[npc.her][npc.cock+]，"
							+ "[npc.name]开始平稳地前后挺动[npc.her]的[npc.hips]，缓缓地操[npc2.her]的[npc2.legs]，每次推入都会发出一阵轻微的[npc.moan]。",

					"在[npc2.namePos][npc2.legs+]之间温柔地滑动[npc.her][npc.cock+]，"
							+ "[npc.name]开始轻柔地将[npc.her]的[npc.hips]向前推，当[npc.she]温柔地操着[npc2.her]的股间时，发出[npc.moans+]。",

					"[npc.name]轻轻地将[npc2.namePos][npc2.legs+]挤在一起，发出一声轻微的[npc.moan]，开始温柔地前后摆动[npc.hips]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，缓缓地操[npc2.namePos]的股间。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "普通地腿交";
		}

		@Override
		public String getActionDescription() {
			return "继续操[npc2.namePos]的大腿。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]饥渴地将[npc.cock+]挤入[npc2.namePos]的股间，"
							+ "[npc.name]开始大力地前后挺动[npc.her]的[npc.hips]，贪婪地操[npc2.her]的[npc2.legs]，每次推入都会发出一阵[npc.a_moan+]。",

					"[npc.name]饥渴地用[npc.cock+]上下磨蹭[npc2.namePos][npc2.legs+]，"
							+ "疯狂地向前挺进[npc.hips]，贪婪地干着[npc2.her]的股间，不禁发出[npc.a_moan+]。",

					"[npc.name]贪婪地合拢[npc2.namePos][npc2.legs+]，并在饥渴地前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，竭力地操[npc2.namePos]的股间。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "腿交(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地操[npc2.namePos]的大腿。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"粗暴地用[npc.her][npc.cock+]冲撞[npc2.namePos]的股间，"
							+ "[npc.name]开始支配性地前后挺动[npc.her]的[npc.hips]，用力地操[npc2.her]的[npc2.legs]，每次推入都会发出一阵[npc.a_moan+]。",

					"[npc.name]激烈地将[npc.cock+]挤入[npc2.namePos][npc2.legs+]之间，"
							+ "开始向前挺动[npc.hips]，粗暴地操着[npc2.her]的股间，不禁发出[npc.a_moan+]。",

					"[npc.name]霸道地将[npc2.namePos][npc2.legs+]挤在一起，来回撞击[npc.her]的[npc.hips]，同时发出[npc.a_moan+]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，粗暴地操[npc2.namePos]的股间。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "普通地腿交";
		}

		@Override
		public String getActionDescription() {
			return "继续操[npc2.namePos]的大腿。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.cock+]挤入[npc2.namePos]的股间，"
							+ "[npc.name]开始前后挺动[npc.her]的[npc.hips]，贪婪地操[npc2.her]的[npc2.legs]，每次推入都会发出一阵[npc.a_moan+]。",

					"[npc.name]将[npc.cock+]挤入[npc2.namePos][npc2.legs+]之间，"
							+ "开始向前挺动[npc.hips]，愉悦地操着[npc2.her]的股间，不禁发出[npc.a_moan+]。",

					"[npc.name]合拢[npc2.namePos][npc2.legs+]，并在前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，操[npc2.namePos]的股间。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "腿交(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "操[npc2.namePos]的大腿(渴求)。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]饥渴地将[npc.cock+]挤入[npc2.namePos]的股间，"
							+ "[npc.name]开始大力地前后挺动[npc.her]的[npc.hips]，贪婪地操[npc2.her]的[npc2.legs]，每次推入都会发出一阵[npc.a_moan+]。",

					"[npc.name]饥渴地用[npc.cock+]上下磨蹭[npc2.namePos][npc2.legs+]，"
							+ "疯狂地向前挺进[npc.hips]，贪婪地干着[npc2.her]的股间，不禁发出[npc.a_moan+]。",

					"[npc.name]贪婪地合拢[npc2.namePos][npc2.legs+]，并在饥渴地前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，竭力地操[npc2.namePos]的股间。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗腿交";
		}

		@Override
		public String getActionDescription() {
			return "努力把你的[npc.cock]从[npc2.namePos][npc2.legs+]间抽出来。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]慢慢地将自己的[npc2.legs]撞向[npc.herHim]，继续温柔地强迫[npc.her][npc.cock+]在[npc2.her]的股间抽送。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "牢牢地将[npc.herHim]固定在原位，"
									+ "温柔地将[npc2.legs]压向[npc.namePos]的胯部，强行将[npc.her][npc.cock+]夹在股间。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，请求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.Moaning]着，完全无视了[npc.her]的反抗，"
									+ "将[npc.her][npc.cock+]深深陷入自己的股间，缓缓地顶着[npc.herHim]磨蹭身体。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]强蛮地将自己的[npc2.legs]撞向[npc.herHim]，继续粗鲁地强迫[npc.her][npc.cock+]在[npc2.her]的股间抽送。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "霸道地将[npc.herHim]固定在原位，"
									+ "[npc2.name]粗暴地将[npc2.legs]压向[npc.her]的腹股沟，粗暴地将[npc.her][npc.cock+]夹在股间。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，哀求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.Moaning]着，完全无视了[npc.her]的反抗，"
									+ "强迫[npc.her][npc.cock+]深深插入自己的股间，粗暴地顶着[npc.herHim]磨蹭身体。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]热切地将自己的[npc2.legs]快速撞向[npc.herHim]，强迫[npc.her][npc.cock+]在[npc2.her]的股间抽送。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "牢牢地将[npc.herHim]固定在原位，"
									+ "[npc2.name]急切地将[npc2.legs]压向[npc.her]的腹股沟，饥渴地将[npc.her][npc.cock+]夹在股间。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，哀求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.Moaning]着，完全无视了[npc.her]的反抗，"
									+ "愉悦地强迫[npc.her][npc.cock+]深深陷入自己的股间，急切地顶着[npc.herHim]磨蹭身体。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止腿交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.cock+]从[npc2.namePos]大腿间探出。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将[npc.cock+]从[npc2.namePos][npc2.legs+]间拉出，"
									+ "霸道地用[npc.cock]的[npc.cockHead]最后一次上下磨蹭[npc2.her]的股间，然后将[npc.hips]移开。",

							"作为腿交的结尾，[npc.name]最后一次深深插进[npc2.namePos][npc2.legs+]，之后才将对方推开。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.cock+]从[npc2.namePos][npc2.legs+]间滑出，"
									+ "[npc.she]最后一次用[npc.her][npc.cock]的[npc.cockHead]拍打[npc2.her]的股间，然后收回[npc.hips]。",

							"最后一次深深地插进[npc2.namePos][npc2.legs+]之间后，[npc.name]将家伙抽出，结束了这次腿交。"));
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
	
	
	public static final SexAction USING_COCK_BETWEEN_THIGHS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "被腿交";
		}

		@Override
		public String getActionDescription() {
			return "把[npc2.namePos][npc2.cock+]强制夹在你的大腿之间。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"发出一声轻柔的[npc.moan]，[npc.name]慢慢地将[npc2.namePos][npc2.cock+]滑进[npc.her][npc.legs+]间，"
									+ "然后轻轻地将[npc.her]的股大腿推到一起，强迫着把[npc2.her]的[npc2.cock]放进形成的沟槽中。",

							"将[npc.her][npc.legs+]对准[npc2.namePos][npc2.cock+]，[npc.name]慢慢地把[npc.her]的大腿挤到一起，"
									+ "[npc.she]将[npc2.her]的[npc2.cock]塞进形成的臀缝，忍不住轻柔地[npc.moaning]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"随着[npc.a_moan+]，[npc.name]饥渴地在[npc.her][npc.legs+]间抽动起[npc2.namePos][npc2.cock+]，"
									+ "然后急切地将[npc.her]的大腿压在一起，并将[npc2.her]的[npc2.cock]插入由此产生的沟中。",

							"把自己[npc.legs+]对准[npc2.namePos][npc2.cock+]，[npc.name]急切地将自己的股间推到一起，"
									+ "[npc.she]使劲将[npc2.her]的[npc2.cock]塞进臀缝中，忍不住[npc.moaning+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"随着[npc.a_moan+]，[npc.name]粗暴地在[npc.her][npc.legs+]间抽动起[npc2.namePos][npc2.cock+]，"
									+ "然后贪婪地将[npc.her]的大腿推到一起，并野蛮地强迫[npc2.her]的[npc2.cock]插进形成的沟槽中。",

							"把自己[npc.legs+]对准[npc2.namePos][npc2.cock+]，[npc.name]粗暴地将自己的大腿推到一起，"
									+ "[npc.she]使劲将[npc2.her]的[npc2.cock]塞进臀缝中，忍不住[npc.moaning+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"随着[npc.a_moan+]，[npc.name]在[npc.her][npc.legs+]间抽动起[npc2.namePos][npc2.cock+]，"
									+ "然后急切地将[npc.her]的大腿压在一起，并将[npc2.her]的[npc2.cock]插入由此产生的沟中。",

							"把自己[npc.legs+]对准[npc2.namePos][npc2.cock+]，[npc.name]急切地将自己的股间推到一起，"
									+ "当[npc.she]使劲将[npc2.her]的[npc2.cock]塞进臀缝中的时候，忍不住[npc.moaning+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一声柔和的[npc2.moan]，温柔地将[npc2.hips]前压，开始操干[npc.namePos]的股间。",
	
								"[npc2.name]轻柔地[npc2.moan]，温柔地挺进自己的[npc2.hips]，将[npc2.cock+]插进[npc.namePos][npc.legs+]间。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]忍不住发出[npc2.a_moan+]，非常急切地朝对方挺进自己的[npc2.hips]，使劲地操[npc.her]股间。",
	
								"[npc2.name]一边发出[npc2.a_moan+]，一边急切地将[npc2.hips]撞向对方，把[npc2.cock+]深深埋入[npc.namePos][npc.legs+]间。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]忘我地发出一阵[npc2.a_moan+]，为了提醒[npc.name]谁才是主导者，"
										+ "[npc2.she]粗暴地撞击[npc2.hips]，无情地暴插[npc.her]的股间。",
	
								"[npc2.name]发出[npc2.a_moan+]，粗暴地挺进自己的[npc2.hips]，"
										+ "[npc2.she]无情地暴操[npc.namePos]的股间，宣告着自己的支配权。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]忍不住发出[npc2.a_moan+]，朝对方挺进自己的[npc2.hips]，使劲地操[npc.her]股间。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.hips]撞向对方，把[npc2.cock+]深深埋入[npc.namePos][npc.legs+]间。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]强行用[npc.legs+]窝夹紧[npc2.her]的[npc2.cock]，[npc2.Name]忍不住发出[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着，拼命试着推开[npc.name]。",
	
								"[npc.Name]强行将[npc2.name]的[npc2.cock]深深插入自己的股间，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
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
							"[npc2.Name]贪婪地将自己[npc2.cock+]插到[npc.namePos][npc.legs+]间，"
									+ "[npc2.she]发出[npc2.a_moan+]，亢奋地操干[npc.namePos]的腿缝。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]深深插入[npc.namePos]的股间。",
									
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地将[npc2.cock+]插入[npc.namePos][npc.legs+]之间。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.cock]从[npc.namePos]的[npc.legs]间拔出，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.cock+]在自己[npc.legs+]间继续抽插。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.cock]从[npc.namePos][npc.legs+]之间抽离。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将自己[npc2.cock+]插到[npc.namePos][npc.legs+]间，"
									+ "[npc2.she]发出一声轻柔的[npc2.moan]，开始操干[npc.namePos]的股间。",
		
							"[npc2.name]温柔地将[npc2.cock+]挺入[npc.namePos]的股间，口中飘出一声轻柔的[npc2.moan]。",
									
							 "[npc2.name]愉悦地[npc2.moaning]着，温柔地将[npc2.cock+]插入[npc.namePos][npc.legs+]之间。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.cock+]插到[npc.namePos][npc.legs+]间，"
									+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地操干[npc.namePos]的股间。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地将[npc2.cock+]深深插入[npc.namePos]的股间。",
									
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地将[npc2.cock+]插入[npc.namePos][npc.legs+]之间。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.cock+]插到[npc.namePos][npc.legs+]间，"
									+ "[npc2.she]开始操干[npc.namePos]的股间，发出一阵[npc2.a_moan+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]深深插入[npc.namePos]的股间。",
									
							"[npc2.name]愉悦地[npc2.moaning]着，开始将[npc2.cock+]插入[npc.namePos][npc.legs+]之间。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "接受腿交(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地让[npc2.namePos][npc2.cock+]插你的股间。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声轻柔的[npc.moan]，温柔地将[npc.hips]向后拱起，让[npc2.namePos][npc2.cock+]在自己[npc.legs+]中插得更深。",

					"[npc.name]发出一声轻柔的[npc.moan]，温柔地向后拱起[npc.hips]，使得[npc2.namePos][npc2.cock+]在[npc.her]的股间前后抽插。",

					"一边慢慢地将自己的[npc.hips]往后送，"
							+ "[npc.lips+]间飘出一声轻柔的[npc.moan]，[npc.her]设法让[npc2.namePos][npc2.cock+]深深插入自己[npc.legs+]间。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "接受腿交";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.namePos][npc2.cock+]操你的股间。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一阵[npc.a_moan+]，热切地向后拱起[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己[npc.legs+]间插得更深。",

					"[npc.name]发出一阵[npc.a_moan+]，贪婪地将自己的[npc.hips]往后推，迫使[npc2.namePos][npc2.cock+]在[npc.her]的股间前后抽插。",

					"[npc.name]饥渴地向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.she]贪婪地设法让[npc2.namePos][npc2.cock+]插入自己[npc.legs+]之间。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "接受腿交(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "用你的大腿穴粗暴地吞进吐出[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一阵[npc.a_moan+]，暴力地撞击[npc.hips]，强迫[npc2.namePos][npc2.cock+]深深插入自己[npc.legs+]之间。",

					"[npc.name]发出一阵[npc.a_moan+]，粗暴地向后猛撞[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己股间前后抽插。",

					"[npc.name]粗鲁地向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.she]霸道地设法让[npc2.namePos][npc2.cock+]插入自己[npc.legs+]之间。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "接受腿交";
		}

		@Override
		public String getActionDescription() {
			return "用你[npc.legs+]压住[npc2.name]，把[npc2.her]的[npc2.cock]强制夹在你的大腿间。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]屈起[npc.her]的[npc.hips]，强迫[npc2.namePos][npc2.cock+]在[npc.her][npc.legs+]间插的更深，发出一阵[npc.a_moan+]。",

					"[npc.name]发出一阵[npc.a_moan+]，将自己的[npc.hips]往后压，迫使[npc2.namePos][npc2.cock+]在自己股间前后抽插。",

					"[npc.name]向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.she]设法让[npc2.namePos][npc2.cock+]插入自己[npc.legs+]之间。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "接受腿交(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地用你[npc.legs+]压住[npc2.name]，把[npc2.her]的[npc2.cock]夹在你的大腿间。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一阵[npc.a_moan+]，热切地向后拱起[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己[npc.legs+]间插得更深。",

					"[npc.name]发出一阵[npc.a_moan+]，贪婪地将自己的[npc.hips]往后推，迫使[npc2.namePos][npc2.cock+]在[npc.her]的股间前后抽插。",

					"[npc.name]饥渴地向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.she]贪婪地设法让[npc2.namePos][npc2.cock+]插入自己[npc.legs+]之间。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_BETWEEN_THIGHS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗被腿交";
		}

		@Override
		public String getActionDescription() {
			return "努力让你[npc.legs+]远离[npc2.namePos][npc2.cock+]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"眼泪慢慢涌上[npc.eyes]，再也无法忍住哭意，[npc.Name]一阵阵地啜泣起来，"
									+ "试着推开[npc2.namePos]的[npc2.cock]但徒劳无功，[npc2.name]继续温柔地操着[npc.her]的股间。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.legs]从[npc2.namePos]的[npc2.cock]旁抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然从容地在[npc.her]的股间继续抽插。",

							"[npc.name]拼命地尝试将[npc.legs]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然温柔地在[npc.her]股间深深抽插。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到泪水涌上自己的眼窝，下一瞬间，[npc.she]开始啜泣起来，"
									+ "试着推开[npc2.namePos]的[npc2.cock]但徒劳无功，[npc2.name]热切地依然操着[npc.her]的股间。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.legs]从[npc2.namePos]的[npc2.cock]旁抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然急切地在[npc.her]的股间继续抽插。",

							"[npc.name]拼命地尝试将[npc.legs]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然急切地在[npc.her]股间深深抽插。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"眼泪慢慢涌上[npc.eyes]，再也无法忍住哭意，[npc.Name]一阵阵地啜泣起来，"
									+ "试着推开[npc2.namePos]的[npc2.cock]但徒劳无功，[npc2.she]保持粗暴的节奏操着[npc.her]的股间。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.legs]从[npc2.namePos]的[npc2.cock]旁抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然粗暴地在[npc.her]的股间继续抽插。",

							"[npc.name]拼命地尝试将[npc.legs]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然粗暴地在[npc.her]股间深深抽插。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止腿交";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.her]的[npc2.cock]从你的腿穴拔出来。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]的[npc2.cock]从自己的股间抽出，[npc.she]愤怒地咆哮着，命令[npc2.name]停下。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]的[npc2.cock]从自己的股间抽出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]的[npc2.cock]从自己的股间抽出，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]停下。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc2.her]的[npc2.cock]从自己的股间抽出。"));
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
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续抽插[npc.namePos]股间的渴望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	
}
