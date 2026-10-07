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
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class FingerClit {

	public static final SexAction CLIT_PLAY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "玩弄阴蒂";
		}

		@Override
		public String getActionDescription() {
			return "向下探索[npc2.namePos][npc2.pussy+]并且开始戏耍[npc2.her]的[npc2.clit]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.her]缓缓地将[npc.fingers+]插入[npc2.namePos][npc2.pussy+]，[npc.name]将[npc.her]的拇指将[npc2.her]的[npc2.clit]顶住，"
										+ "[npc.she]轻轻地向下按压和摩擦它并继续将[npc.her]的手指在[npc2.herHim]的体内蜷起。",
								"[npc.name]将[npc.her][npc.fingers+]轻轻滑入[npc2.namePos][npc2.pussy+]中并将[npc.her]的拇指按在[npc2.her][npc2.clit+]上，"
										+ "[npc.she]轻柔地抚摸着它，发出一声低微[npc.moan]。",
								"[npc.name]温柔地将[npc.fingers]深深地插入[npc2.namePos][npc2.pussy+]，"
										+ "温柔地用拇指揉搓按压[npc2.her][npc2.clit+]，[npc.lips]间发出轻微的[npc.moan]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]将手指粗暴地深深插入[npc2.namePos][npc2.pussy+]，举起拇指，对准[npc2.her]的[npc2.clit]，"
										+ "[npc.she]强行按压并磨蹭它并继续霸道地将[npc.her]的手指在[npc2.herHim]的体内蜷起。",
								"[npc.name]强行将[npc2.her][npc.fingers+]深入[npc2.namePos][npc2.pussy+]，将[npc.her]拇指向下磨向[npc2.her][npc2.clit+]，"
										+ "[npc.she]开始粗暴地抚摸它，发出一阵[npc.a_moan+]。",
								"[npc.name]将[npc.fingers]猛烈插入[npc2.namePos][npc2.pussy+]深处，"
										+ "粗暴地用拇指揉弄磨蹭[npc2.her][npc2.clit+]，[npc.lips]间发出一阵[npc.a_moan+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]将[npc.fingers+]深深插入[npc2.namePos][npc2.pussy+]，用拇指顶住[npc2.her]的[npc2.clit]，"
										+ "然后向下按压摩擦，并将手指在[npc2.herHim]的体内蜷起。",
								"[npc.name]将[npc.fingers+]滑入[npc2.namePos][npc2.pussy+]，并将拇指按在[npc2.namePos][npc2.clit+]上，"
										+ "[npc.she]开始抚摸它，发出一阵[npc.a_moan+]。",
								"[npc.name]将[npc.fingers]深深插入[npc2.namePos][npc2.pussy+]，"
										+ "[npc.namePos]用拇指揉弄把玩着[npc2.her][npc2.clit+]，[npc.lips]间发出一阵[npc.a_moan+]。"));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]渴望地将手指向下深入[npc2.namePos][npc2.pussy+]，举起拇指贴向[npc2.her]的[npc2.clit]，"
										+ "然后贪婪地向下按压摩擦，并将手指在[npc2.herHim]的体内蜷起。",
								"[npc.name]将[npc.fingers+]贪婪地滑入[npc2.namePos][npc2.pussy+]，并将拇指按在[npc2.namePos][npc2.clit+]上，"
										+ "[npc.she]开始急切地抚摸它，发出一阵[npc.a_moan+]。",
								"[npc.name]将[npc.fingers]深深插入[npc2.namePos][npc2.pussy+]，"
										+ "[npc.namePos]用拇指轻轻揉搓按压[npc2.her][npc2.clit+]，[npc.lips]间溢出[npc.a_moan+]。"));
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
											+ "[npc2.she]柔和地晃动[npc2.hips]，温柔地鼓励[npc.Name]继续爱抚[npc2.her][npc2.clit+]。",
									"作为回应，[npc2.Name]开始温柔地摆动[npc2.her]的[npc2.hips]，"
											+ "温柔地将[npc2.her][npc2.clit+]压到[npc.namePos][npc.fingers]上，[npc2.she]恳求[npc.herHim]继续。",
									"[npc2.Name]温柔地拱着[npc2.hips] ，抵住[npc.namePos]的[npc.fingers]，"
											+ "温柔地[npc2.moaning]，专心于[npc2.her][npc2.clit+]的快乐中。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]粗暴地将[npc2.hips]向后压，让[npc.name]触摸，一声[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
									"[npc2.Name]粗暴地用[npc2.hips]顶着[npc.namePos]的[npc.hand]，"
											+ "[npc2.she]愉悦地[npc2.moaning]着，命令[npc.name]继续爱抚[npc2.her][npc2.clit+]。",
									"[npc2.Name]激烈地拱着[npc2.her]的[npc2.hips]，抵住[npc.namePos][npc.fingers]，"
											+ "[npc2.she][npc2.moaning]着拱起了背部，粗暴地磨蹭着，让[npc.name]触摸自己[npc2.clit+]。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]将[npc2.hips]向后压，让[npc.name]触摸，一声[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
									"[npc2.Name]用[npc2.hips]顶着[npc.namePos]的[npc.hand]，"
											+ "[npc2.she]愉悦地[npc2.moaning]着，鼓励[npc.name]继续爱抚[npc2.her][npc2.clit+]。",
									"[npc2.she]将[npc2.hips]推向[npc.namePos]的[npc.fingers]，"
											+ "[npc2.she][npc2.moaning]着拱起了背部，向下压去，让[npc.name]触摸自己[npc2.clit+]。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos]嘴间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.pussy]从[npc.namePos]的触摸下抽离，"
											+ "挣扎着恳求[npc.herHim]停止。",
									"[npc2.Name]拉回[npc2.her][npc2.hips]作为回应，"
											+ "发出[npc2.a_sob+]，[npc2.she]恳求[npc.name]停止触摸[npc2.herHim]。",
									"[npc2.Name]试图将[npc2.her]的[npc2.pussy]推离[npc.namePos]的[npc.fingers]，"
											+ "[npc2.sobbing+]，[npc2.she]恳求[npc.name]放开自己。"));
							break;
						default: // Dom normal and sub eager:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]急切地将[npc2.hips]向后压，让[npc.name]触摸，一阵[npc2.A_moan+]从[npc2.namePos]口中爆发而出。",
									"[npc2.Name]急切地用[npc2.hips]顶着[npc.namePos]的[npc.hand]，"
											+ "[npc2.she]愉悦地[npc2.moaning]着，热切地鼓励[npc.name]继续爱抚[npc2.her][npc2.clit+]。",
									"[npc2.she]饥渴地将[npc2.hips]推向[npc.namePos]的[npc.fingers]，"
											+ "[npc2.she][npc2.moaning]着拱起了背部，饥渴地向下压去，让[npc.name]触摸自己[npc2.clit+]。"));
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]缓慢地将[npc.fingers+]尽可能深地滑入[npc2.namePos][npc2.pussy+]，将注意力集中在[npc2.namePos][npc2.clit+]上，"
										+ "[npc.she]温柔地挤压揉捏着它，同时将身体压向[npc2.name]，发出一阵[npc.a_moan+]。",
								"[npc.name]温柔地用[npc.fingers+]抚上[npc2.namePos][npc2.pussy+]，"
										+ "发出轻微的[npc.moan]，摸向[npc2.namePos][npc2.clit+]，开始温柔地揉捏挤压它。",
								"[npc.name]用[npc.fingers]挑逗着[npc2.namePos][npc2.pussy+]，"
										+ "温柔地揉搓按压[npc2.her][npc2.clit+]，[npc.lips]间溢出小声[npc.moan]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]用[npc.fingers+]在[npc2.namePos][npc2.pussy+]上粗暴地磨蹭，将注意力集中在[npc2.namePos][npc2.clit+]上，"
										+ "[npc.she]狠狠地挤压揉捏着它，同时将身体压向[npc2.name]，发出一阵[npc.a_moan+]。",
								"[npc.name]贪婪地用[npc.fingers+]抚上[npc2.namePos][npc2.pussy+]，"
										+ "发出轻微的[npc.a_moan+]，摸向[npc2.namePos][npc2.clit+]，开始粗暴地揉捏挤压它。",
								"[npc.name]用[npc.fingers]磨蹭[npc2.namePos][npc2.pussy+]，"
										+ "粗暴地挤弄揉按[npc2.her][npc2.clit+]，[npc.lips]间溢出[npc.a_moan+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]将[npc.fingers+]尽可能深地滑入[npc2.namePos][npc2.pussy+]，将注意力集中在[npc2.namePos][npc2.clit+]上，"
										+ "[npc.she]挤压揉捏着它，同时将身体压向[npc2.name]，发出一阵[npc.a_moan+]。",
								"[npc.name]用[npc.fingers+]抚上[npc2.namePos][npc2.pussy+]，"
										+ "发出轻微的[npc.a_moan+]，摸向[npc2.namePos][npc2.clit+]，开始揉捏挤压它。",
								"[npc.name]用[npc.fingers]挑逗着[npc2.namePos][npc2.pussy+]，"
										+ "开始揉弄把玩[npc2.her][npc2.clit+]，[npc.lips]间溢出[npc.a_moan+]。"));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]贪婪地将[npc.fingers+]尽可能深地滑入[npc2.namePos][npc2.pussy+]，将注意力集中在[npc2.namePos][npc2.clit+]上，"
										+ "[npc.she]急切地挤压揉捏着它，同时将身体压向[npc2.name]，发出一阵[npc.a_moan+]。",
								"[npc.name]坚定地用[npc.fingers+]抚上[npc2.namePos][npc2.pussy+]，"
										+ "发出轻微的[npc.a_moan+]，摸向[npc2.namePos][npc2.clit+]，开始渴欲地揉捏挤压它。",
								"[npc.name]用[npc.fingers]挑逗着[npc2.namePos][npc2.pussy+]，"
										+ "渴望地揉弄按压[npc2.her][npc2.clit+]，[npc.lips]间溢出[npc.a_moan+]。"));
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"伴随着[npc2.namePos][npc2.hips]的温柔起伏，一声轻柔的[npc2.moan]从[npc2.her][npc2.lips+]间飘出，"
											+ "[npc2.she]温柔地鼓励[npc.Name]继续爱抚[npc2.her][npc2.clit+]。",
									"作为回应，[npc2.Name]开始温柔地摆动[npc2.her]的[npc2.hips]，"
											+ "温柔地将[npc2.her][npc2.clit+]压到[npc.namePos][npc.fingers]上，[npc2.she]恳求[npc.herHim]继续。",
									"[npc2.Name]温柔地拱着[npc2.hips] ，抵住[npc.namePos]的[npc.fingers]，"
											+ "温柔地[npc2.moaning]，专心于[npc2.her][npc2.clit+]的快乐中。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]粗暴地将[npc2.hips]向后压，让[npc.name]触摸，一阵[npc2.A_moan+]从[npc2.namePos]口中爆发而出。",
									"[npc2.Name]粗暴地用[npc2.hips]顶着[npc.namePos]的[npc.hand]，"
											+ "[npc2.she]愉悦地[npc2.moaning]着，命令[npc.name]继续爱抚[npc2.her][npc2.clit+]。",
									"[npc2.Name]激烈地拱着[npc2.her]的[npc2.hips]，抵住[npc.namePos][npc.fingers]，"
											+ "[npc2.she][npc2.moaning]着拱起了背部，粗暴地磨蹭着，让[npc.name]触摸自己[npc2.clit+]。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]将[npc2.hips]向后压，让[npc.name]触摸，一阵[npc2.A_moan+]从[npc2.namePos]口中爆发而出。",
									"[npc2.Name]用[npc2.hips]顶着[npc.namePos]的[npc.hand]，"
											+ "[npc2.she]愉悦地[npc2.moaning]着，鼓励[npc.name]继续爱抚[npc2.her][npc2.clit+]。",
									"[npc2.she]将[npc2.hips]推向[npc.namePos]的[npc.fingers]，"
											+ "[npc2.she][npc2.moaning]着拱起了背部，向下压去，让[npc.name]触摸自己[npc2.clit+]。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos]嘴间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.pussy]从[npc.namePos]的触摸下抽离，"
											+ "挣扎着恳求[npc.herHim]停止。",
									"[npc2.Name]拉回[npc2.her][npc2.hips]作为回应，"
											+ "发出[npc2.a_sob+]，[npc2.she]恳求[npc.name]停止触摸[npc2.herHim]。",
									"[npc2.Name]试图将[npc2.her]的[npc2.pussy]推离[npc.namePos]的[npc.fingers]，"
											+ "[npc2.sobbing+]，[npc2.she]恳求[npc.name]放开自己。"));
							break;
						default: // Dom normal and sub eager:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]急切地将[npc2.hips]向后压，让[npc.name]触摸，一阵[npc2.A_moan+]从[npc2.namePos]口中爆发而出。",
									"[npc2.Name]急切地用[npc2.hips]顶着[npc.namePos]的[npc.hand]，"
											+ "[npc2.she]愉悦地[npc2.moaning]着，热切地鼓励[npc.name]继续爱抚[npc2.her][npc2.clit+]。",
									"[npc2.she]饥渴地将[npc2.hips]推向[npc.namePos]的[npc.fingers]，"
											+ "[npc2.she][npc2.moaning]着拱起了背部，饥渴地向下压去，让[npc.name]触摸自己[npc2.clit+]。"));
							break;
					}
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
}
