package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
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
 * @since 0.4.9.13
 * @version 0.4.9.13
 * @author Innoxia
 */
public class TentacleNipple {
	
	public static final SexAction TEASE_TENTACLE_OVER_NIPPLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "用触手挑弄乳头";
		}
		@Override
		public String getActionDescription() {
			return "用[npc.tentacle+]挑逗[npc2.name][npc2.nipple+]。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE);
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将自己[npc.tentacle+]顶向[npc2.namePos][npc2.breasts+]，"
									+ "[npc.Name]开始缓慢地用[npc.tentacleTip+]上下挑逗[npc2.her][npc2.nipples+]，随时准备插入[npc2.herHim]。",

							"[npc.name]轻声[npc.moan]着，将自己[npc.tentacle+]移向[npc2.namePos][npc2.breasts+]，"
									+ "然后开始温柔地用[npc.tentacleTip]上下磨蹭其中一个[npc2.nipples+]。",

							"[npc.name]温柔地用[npc.tentacle][npc.tentacleTip+]上下挑逗[npc2.namePos][npc2.nipple+]，"
									+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了轻柔的[npc.moan]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将自己[npc.tentacle+]顶向[npc2.namePos][npc2.breasts+]，"
									+ "[npc.name]开始渴望地顶着[npc2.her][npc2.nipples+]来回磨蹭[npc.tentacleTip+]，随时准备插入[npc2.herHim]。",

							"[npc.Name]发出一阵[npc.a_moan+]，把[npc.tentacle+]引到[npc2.namePos][npc2.breasts+]旁，"
									+ "然后开始饥渴地用[npc.tentacleTip]上下磨蹭其中一个[npc2.nipples+]。",

							"[npc.name]急切地用[npc.tentacle][npc.tentacleTip+]上下挑逗[npc2.namePos][npc2.nipple+]，"
									+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了一阵[npc.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tentacle+]磨蹭着[npc2.namePos][npc2.breasts+]，"
									+ "接着向后退了一点，开始用[npc.tentacleTip+]上下挑逗[npc2.her][npc2.nipples+]，随时准备插入[npc2.herHim]。",

							"[npc.name]发出一阵[npc.a_moan+]，把[npc.tentacle+]挪向[npc2.namePos][npc2.breasts+]，"
									+ "然后开始粗暴地用[npc.tentacleTip]上下磨蹭[npc2.namePos][npc2.nipples+]。",

							"[npc.name]用[npc.tentacle][npc.tentacleTip+]粗暴地来回磨蹭[npc2.namePos][npc2.nipple+]，"
									+ "一想到只要[npc.she]高兴随时可以操干[npc2.name]，[npc.name]就发出了一阵[npc.a_moan+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将自己[npc.tentacle+]顶向[npc2.namePos][npc2.breasts+]，"
									+ "开始用[npc.tentacleTip+]来回磨蹭[npc2.her][npc2.nipples+]，随时准备插入[npc2.herHim]。",

							"[npc.name]发出一阵[npc.a_moan+]，把[npc.tentacle+]移向[npc2.namePos][npc2.breasts+]，用[npc.tentacleTip]来回磨蹭[npc2.her]其中一个[npc2.nipples+]。",

							"[npc.name]用[npc.tentacle][npc.tentacleTip+]上下挑逗[npc2.namePos][npc2.nipple+]，"
									+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了一阵[npc.a_moan+]。"));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]感受着[npc2.nipple+]传来的阵阵快感，一声轻柔的[npc2.moan]从[npc2.she][npc2.lips+]间飘出，"
										+ "然后[npc2.she]温柔地挺起胸部，顶向[npc.namePos]的[npc.tentacle]。",
	
								"[npc2.name]发出一声柔和的[npc2.moan]，随后温柔地将[npc2.nipple+]贴向[npc.namePos]的[npc.tentacle]。",
	
								"[npc2.name]感受到[npc.namePos]的[npc.tentacle]正刺激着[npc2.her][npc2.nipple+]，[npc2.she]愉悦地[npc2.moanVerb]着，并温柔地挺起[npc2.her]的胸部作为回应。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]感受到[npc2.nipple+]传来阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间爆发而出，"
										+ "然后[npc2.she]饥渴地挺起胸部，顶向[npc.namePos]的[npc.tentacle]。",
	
								"[npc2.name]发出一声迷乱的[npc2.moan]，然后饥渴地将[npc.namePos]的[npc.tentacle]塞入自己的胸部。",
	
								"[npc2.name]感受到[npc.namePos]的[npc.tentacle]正刺激着[npc2.her][npc2.nipple+]，[npc2.she]愉悦地[npc2.moanVerb]着，并饥渴地挺起[npc2.her]的胸部作为回应。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]感受到[npc2.nipple+]传来阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间爆发而出，"
										+"[npc2.she]粗暴地用胸部抵住[npc.namePos]的[npc.tentacle]。",
	
								"[npc2.name]发出一声迷乱的[npc2.moan]，然后粗暴地将[npc.namePos]的[npc.tentacle]塞入自己的胸部。",
	
								"[npc2.name]感受到[npc.namePos]的[npc.tentacle]正刺激着[npc2.her][npc2.nipple+]，[npc2.she]愉悦地[npc2.moanVerb]着，并粗暴地挺起[npc2.her]的胸部作为回应。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]感受到[npc2.nipple+]传来阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间爆发而出，"
										+ "然后[npc2.she]挺起胸部，顶向[npc.namePos]的[npc.tentacle]。",
	
								"[npc2.name]发出一声[npc2.moan]，然后将[npc2.her]的胸部推向[npc.namePos]的[npc.tentacle]。",
	
								"[npc2.name]感受到[npc.namePos]的[npc.tentacle]正刺激着[npc2.her][npc2.nipple+]，[npc2.she]愉悦地[npc2.moanVerb]着，挺起[npc2.her]的胸部作为回应。"));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isNippleVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"一想到自己即将要被夺走乳头贞操，[npc2.namePos][npc2.lips+]间不禁爆发出一阵[npc2.A_sob+]。",
										"[npc2.Name]意识到自己可能将失去乳头贞操，不禁发出一声绝望的[npc2.sob]。",
										"一想到自己马上要被[npc.namePos][npc.tentacle+]夺走乳头贞操，[npc2.name]就不禁绝望地[npc2.sobVerb]着。"));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.namePos]想到接下来要发生的事，[npc2.lips+]间不禁爆发出一阵[npc2.A_sob+]，[npc2.speech(不！快停下！我的乳头还没被操过呢！)]",
										"[npc2.Name]发出一声绝望的[npc2.sob]，苦苦乞求，[npc2.speech(求求你！不要这样做！我的乳头还没被操过呢！)]",
										"[npc2.Name]一想到接下来要发生的事情，不禁痛苦地[npc2.sobsVerb]，绝望地哀求着，[npc2.speech(不要！快停下！我不想失去我的乳头童贞！)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.nipple(true)]从[npc.namePos]的[npc.tentacle]抽离。",
										"[npc2.Name]不断反抗着[npc.Name]，不禁发出绝望的[npc2.sob]。",
										"[npc2.name]痛苦地[npc2.sobVerb]着，试图远离[npc.Name]。"));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.nipple(true)]从[npc.namePos]的[npc.tentacle]抽离，"
												+ "[npc2.speech(不！不可以！求求你了，放过我吧！)]",
										"[npc2.Name]发出一声绝望的[npc2.sob]，苦苦乞求，[npc2.speech(求求你！不要这样做！放过我！)]",
										"[npc2.Name]痛苦地[npc2.sobsVerb]，[npc2.she]哀求道，[npc2.speech(不要！快停下！求求你快走！)]"));
							}
						}
						break;
					default:
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TENTACLE, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE);
		}
	};
	
	public static final SexAction FORCE_TENTACLE_OVER_NIPPLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "触手挑逗乳头";
		}
		@Override
		public String getActionDescription() {
			return "用[npc.nipple+]挑逗[npc2.name]的[npc2.tentacleTip]。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterPerformingAction().isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE);
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+]，然后将它引导至自己[npc.breasts+]。"
									+ "[npc.she]缓慢地用[npc2.tentacleTip+]上下磨蹭[npc.her][npc.nipple+]，挑逗[npc2.name]随时可能插入。",

							"[npc.name]轻声[npc.moan]，抓住[npc2.namePos][npc2.tentacle+]，将它移向自己[npc.breasts+]，"
									+ "然后开始温柔地用[npc2.tentacleTip]上下磨蹭[npc.her][npc.nipple+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+]，温柔地用[npc2.tentacleTip+]磨蹭自己[npc.nipple+]，"
									+ "[npc.she]发出一声轻柔的[npc.moan]，挑逗[npc2.Name]随时可能插入。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+]，然后将它引导至自己[npc.breasts+]。"
									+ "[npc.she]饥渴地用[npc2.tentacleTip+]上下磨蹭[npc.her][npc.nipple+]，挑逗[npc2.name]随时可能插入。",

							"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.tentacle+]并引导它到自己[npc.breasts+]，"
									+ "然后开始饥渴地用[npc2.tentacleTip]上下磨蹭[npc.her][npc.nipple+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+]，饥渴地用[npc2.tentacleTip+]磨蹭自己[npc.nipple+]，"
									+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.cock+]，把它猛拉向自己[npc.breasts+]。"
									+ "[npc.she]粗暴地强迫[npc2.tentacleTip+]上下磨蹭[npc.her][npc.nipple+]，挑逗[npc2.name]随时可能插入。",

							"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.tentacle+]并把它拉扯向自己[npc.breasts+]，"
									+ "然后开始粗暴地强迫[npc2.tentacleTip]上下磨蹭[npc.her][npc.nipple+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+(true)]，粗暴地用[npc2.tentacleTip+]磨蹭自己[npc.nipple+]，"
									+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+]，然后将它引导至自己[npc.breasts+]。"
									+ "[npc.she]用[npc2.tentacleTip+]上下磨蹭[npc.her][npc.nipple+]，挑逗[npc2.name]随时可能插入。",

							"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.tentacle+]并引导它到自己[npc.breasts+]，"
									+ "然后开始用[npc2.tentacleTip]上下磨蹭[npc.her][npc.nipple+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+]，用[npc2.tentacleTip+]磨蹭自己[npc.nipple+]，"
									+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos][npc2.lips+]间飘出一声轻柔的[npc2.moan]，[npc2.she]开始用[npc2.tentacle]温柔地上下磨蹭[npc.namePos][npc.nipple+]。",
	
								"[npc2.name]发出一声柔和的[npc2.moan]，随后温柔地用[npc2.tentacle]前后磨蹭[npc.namePos][npc.nipple+]。",
	
								"[npc2.Name]感觉到[npc2.tentacle]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.tentacle]温柔地上下磨蹭[npc.namePos][npc.nipple+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]爆出一声[npc2.a_moan+]，粗暴地用[npc2.tentacle]来回磨蹭[npc.namePos][npc.nipple+]。",
	
								"[npc2.Name]发出一阵[npc2.a_moan+]，激烈地用[npc2.tentacle]来回磨蹭[npc.namePos][npc.nipple+]。",
	
								"[npc2.Name]感觉到[npc2.tentacle]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，为了提醒[npc.name]谁才是主导者，"
										+ "[npc2.she]开始用[npc2.tentacle]粗暴地上下磨蹭[npc.namePos][npc.nipple+]。"));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]爆出一声[npc2.a_moan+]，热切地用[npc2.tentacle]来回磨蹭[npc.namePos][npc.nipple+]。",
	
								"[npc2.Name]发出一声迷乱的[npc2.moan]，然后迫不及待地用[npc2.tentacle]来回磨蹭[npc.namePos][npc.nipple+]。",
	
								"[npc2.Name]感觉到[npc2.tentacle]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.tentacle]饥渴地上下磨蹭[npc.namePos][npc.nipple+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]爆出一声[npc2.a_moan+]，用[npc2.tentacle]来回磨蹭[npc.namePos][npc.nipple+]。",
	
								"[npc2.Name]发出一声迷乱的[npc2.moan]，然后用[npc2.tentacle]来回磨蹭[npc.namePos][npc.nipple+]。",
	
								"[npc2.Name]感觉到[npc2.tentacle]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.tentacle]上下磨蹭[npc.namePos][npc.nipple+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.tentacle+]从[npc.namePos][npc.nipple+]中拔出来。",
	
								"[npc2.Name]发出一声绝望的[npc2.sob]，然后拼命地尝试将[npc2.tentacle+]从[npc.namePos][npc.labia+]中拔出来。",
	
								"[npc2.Name]痛苦地[npc2.sobsVerb]，并哀求[npc.name]放过自己的[npc2.tentacle]。"));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TENTACLE, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE);
		}
	};
	
	
	public static final SexAction TENTACLE_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "触手交乳头";
		}
		@Override
		public String getActionDescription() {
			return "用[npc.tentacle+]抽插[npc2.namePos][npc2.nipple+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]慢慢用[npc.tentacleTip+]挑逗着[npc2.namePos][npc2.breasts+]，"
									+ "发出轻微的[npc.moan]，缓缓地向前推，将[npc.tentacle+]没入[npc2.namePos][npc2.nipple+]里。",

							"[npc.Name]将[npc.tentacleTip+]抵住[npc2.namePos][npc2.breasts+]，"
									+ "[npc.she]以缓慢而稳定的力度，轻柔地将它深深地插入[npc2.namePos][npc2.nipple+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]热切地用[npc.tentacleTip+]挑逗着[npc2.namePos][npc2.breasts+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.tentacle+]深深插入[npc2.her][npc2.nipple+]。",

							"[npc.Name]将[npc.tentacleTip+]抵住[npc2.namePos][npc2.breasts+]，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos][npc2.nipple+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tentacleTip+]粗暴地磨蹭[npc2.namePos][npc2.breasts+]，"
									+ "发出[npc.a_moan+]，然后粗暴地向前猛推，将[npc.tentacle+]深深插入[npc2.her][npc2.nipple+]。",

							"[npc.Name]将[npc.tentacleTip+]抵住[npc2.namePos][npc2.breasts+]，"
									+ "用力前推，粗暴地将它深深插入[npc2.her][npc2.nipple+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]热切地用[npc.tentacleTip+]挑逗着[npc2.namePos][npc2.breasts+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.tentacle+]深深插入[npc2.her][npc2.nipple+]。",

							"[npc.Name]将[npc.tentacleTip+]抵住[npc2.namePos][npc2.breasts+]，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos][npc2.nipple+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tentacleTip+]挑逗着[npc2.namePos][npc2.breasts+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.tentacle+]插入[npc2.her][npc2.nipple+]。",

							"[npc.Name]将[npc.tentacleTip+]抵住[npc2.namePos][npc2.breasts+]，"
									+ "[npc.she]小幅度前推，将它深深插入[npc2.namePos][npc2.nipple+]。"));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.tentacle+]顶进[npc2.name]体内，[npc2.herHim]发出轻柔的[npc2.moan]，"
										+ "于是[npc2.her]温柔地挺起自己的胸部，以便让它在[npc2.breast+(true)]里插得更深。",
	
								"[npc2.name]轻柔地[npc2.moan]着，温柔地将胸部挺起来，"
										+ "将[npc.namePos][npc.tentacle+]更深地插入自己[npc2.nipple+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.tentacle+]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "于是[npc2.her]粗暴地挺起胸部，强迫它在自己[npc2.breast+(true)]里插得更深。",
	
								"[npc2.name]发出[npc2.a_moan+]，激烈地挺起了自己的胸部，"
										+ "粗暴地强迫[npc.Name]把[npc.her][npc.tentacle+]在自己[npc2.nipple+]里插得更深。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.tentacle+]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.her]于是渴望地挺起自己的胸部，以便让它在[npc2.her][npc2.breast+(true)]里插得更深。",
	
								"[npc2.name]发出[npc2.a_moan+]，渴望地挺起了自己的胸部，"
										+ "急切地让[npc.namePos][npc.tentacle+]更加深入[npc2.her][npc2.nipple+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.tentacle+]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.her]于是挺起自己的胸部，以便让它在[npc2.her][npc2.breast+(true)]里插得更深。",
	
								"[npc2.name]发出[npc2.a_moan+]，挺起了自己的胸部，"
										+ "让[npc.namePos][npc.tentacle+]更加深入[npc2.her][npc2.nipple+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.tentacle+]进入了自己的身体，不禁漏出一声[npc2.a_sob+]，"
										+ "泪水不停地从[npc2.her]的[npc2.face]上淌下，[npc2.she]哀求[npc.Name]从自己身体里拔出来。",
	
								"[npc2.name]发出[npc2.a_sob+]，徒劳地挣扎扭身，试图拔出讨厌的插入物，"
										+ "但[npc.namePos]不请自来的[npc.tentacle]却在[npc2.nipple+]中插得更深，泪水从[npc2.her]的[npc2.face]上流了下来。"));
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
							"作为回应，[npc2.Name]热切地挺起[npc2.her]的胸部，"
									+ "[npc2.she]热切地配合着[npc.namePos][npc.tentacle+]深深插入[npc2.her][npc2.nipple+]中，感受着潮水般的快感，不住发出[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]急切地挺起胸部，乞求[npc.name]继续操[npc2.her]的[npc2.breasts]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，渴望地挺起自己的胸部，"
									+ "配合着[npc.tentacle+]更深地插入自己的[npc2.nipple+]，急切地乞求[npc.Name]继续操[npc2.her]的[npc2.breasts]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要逃离[npc.namePos]的[npc.tentacle]，但没能成功，"
									+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地乞求[npc.Name]从自己[npc2.nipple+]里拔出来，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "[npc2.she]恳求[npc.name]从自己[npc2.nipple+]里拔出来。泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
		
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]从[npc2.her][npc2.nipple+]里拔出来。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]挺起[npc2.her]的胸部，"
									+ "[npc2.she]配合着[npc.namePos][npc.tentacle+]深深插入[npc2.her][npc2.nipple+]中，感受着潮水般的快感，不住发出[npc2.a_moan+]。",
	
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.her]挺起自己的胸部，恳求[npc.name]继续操[npc2.her]的[npc2.breasts]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，渴望地将自己的胸部挺起，"
									+ "配合着[npc.tentacle+]更深地插入自己的[npc2.nipple+]，乞求[npc.Name]继续操[npc2.her]的[npc2.breasts]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]缓缓地挺起[npc2.her]的胸部，"
									+ "[npc2.she]发出一声轻柔的[npc2.moan]，温柔地乞求着[npc.Name]继续干自己[npc2.breasts+]。",
	
							" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
									+ "[npc2.her]缓缓地挺起自己的胸部，乞求[npc.name]继续操[npc2.her]的[npc2.breasts]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地挺起自己的胸部，"
									+ "配合着[npc.tentacle+]更深地插入自己的[npc2.nipple+]，乞求[npc.Name]继续操[npc2.her]的[npc2.breasts]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]暴力地推起自己的胸部，"
									+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地命令[npc.Name]继续干[npc2.her]的[npc2.breasts]。",
	
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]粗暴地用胸部撞击[npc.Name]的胯下，命令[npc.Name]继续干[npc2.her]的[npc2.breasts]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地用胸部猛然撞击[npc.namePos]的胯下，"
									+ "强迫[npc.name]将[npc.tentacle+]深深插入[npc2.her][npc2.nipple+]，命令[npc.name]继续操[npc2.her]的[npc2.breasts]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction TENTACLE_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "触手交乳头(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "用[npc.tentacle]温柔地抽插[npc2.namePos][npc2.nipple+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]温柔地将[npc.tentacle+]深深插入[npc2.namePos][npc2.nipple+]，"
							+ "[npc.name]开始前后摇摆[npc.hips]，缓缓地操[npc2.her]的[npc2.breasts]，每次推入都会发出一阵轻微的[npc.moan]。",

					"[npc.name]缓缓地将[npc.tentacle+]插入[npc2.namePos][npc2.nipple+]，"
							+ "轻柔地向前推，[npc.she]温柔地干着[npc2.name]的[npc2.breasts]，不禁漏出一小声[npc.moan]。",

					"[npc.Name]将[npc.tentacle+]滑入[npc2.namePos][npc2.nipple+]，开始温柔地前后抽动[npc.hips]，不禁漏出一小声[npc.moan]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，同时缓缓地操[npc2.namePos]的[npc2.breasts]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "触手交乳头";
		}

		@Override
		public String getActionDescription() {
			return "继续用[npc.tentacle+]抽插[npc2.namePos][npc2.nipple+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]饥渴地将[npc.tentacle+]深深插入[npc2.namePos][npc2.nipple+]，"
							+ "[npc.Name]开始亢奋地抽插打桩，开心地操着[npc2.herHim]的[npc2.breasts]，漏出[npc.a_moan+]。",

					"[npc.name]亢奋地将[npc.tentacle+]深深插入[npc2.namePos][npc2.nipple+]，"
							+ "疯狂地向前挺进[npc.hips]，贪婪地干着[npc2.namePos]的[npc2.breasts]，不禁发出[npc.a_moan+]。",

					"[npc.Name]将[npc.tentacle+]深深插入[npc2.namePos][npc2.nipple+]，开始热切地前后抽插，发出[npc.a_moan+]，"
							+ "[npc.she]亢奋地干着[npc2.her]的[npc2.breasts]时，将鼻子贴近吸入[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "触手交乳头(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.tentacle+]粗暴地抽插[npc2.namePos][npc2.nipple+]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]粗暴地将[npc.tentacle+]深深插入[npc2.namePos][npc2.nipple+]，"
							+ "[npc.name]开始粗暴地抽送打桩，野蛮地操着[npc2.name]的[npc2.breasts]，每次猛推都会发出[npc.a_moan+]。",

					"[npc.name]激烈地将[npc.tentacle+]深深插入[npc2.namePos][npc2.nipple+]，"
							+"[npc.name]开始粗暴地向前猛撞[npc.hips]，大力地干着[npc2.her]的[npc2.breasts]，不禁发出[npc.moans+]。",

					"[npc.Name]激烈地将[npc.tentacle+]深深插入[npc2.namePos][npc2.nipple+]，粗暴地来回撞击，不禁发出[npc.a_moan+]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，暴力地操干[npc2.namePos]的[npc2.breasts]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "触手交乳头";
		}

		@Override
		public String getActionDescription() {
			return "继续干[npc2.namePos][npc2.nipple+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.tentacle+]深深插入[npc2.namePos][npc2.nipple+]，"
							+ "[npc.Name]开始抽插打桩，开心地操着[npc2.herHim]的[npc2.breasts]，漏出[npc.a_moan+]。",

					"[npc.name]将[npc.tentacle+]深深插入[npc2.namePos][npc2.nipple+]，"
							+ "开始向前挺动[npc.hips]，急切地操着[npc2.her]的[npc2.breasts]，不禁发出[npc.a_moan+]。",

					"[npc.Name]将[npc.tentacle+]深深插入[npc2.namePos][npc2.nipple+]，开始热切地前后抽插，发出[npc.a_moan+]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，操干着[npc2.namePos]的[npc2.breasts]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "触手交乳头(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.tentacle+]饥渴地抽插[npc2.namePos][npc2.nipple+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]饥渴地将[npc.tentacle+]深深插入[npc2.namePos][npc2.nipple+]，"
							+ "[npc.Name]开始亢奋地抽插打桩，开心地操着[npc2.herHim]的[npc2.crotchBoobs]，漏出[npc.a_moan+]。",

					"[npc.name]亢奋地将[npc.tentacle+]深深插入[npc2.namePos][npc2.nipple+]，"
							+ "[npc.Name]开始疯狂地前后抽插，贪婪地干着[npc2.name]，发出[npc.a_moan+]。",

					"[npc.Name]将[npc.tentacle+]深深插入[npc2.namePos][npc2.nipple+]，开始热切地前后抽插，发出[npc.a_moan+]，"
							+ "[npc.she]兴奋地干着[npc2.namePos]，呼吸着[npc2.herHim]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "触手交乳头(抵抗)";
		}

		@Override
		public String getActionDescription() {
			return "努力从[npc2.namePos][npc2.nipple+]拔出[npc.tentacle]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]尝试将[npc.tentacle]从[npc2.namePos]的[npc2.nipple(true)]里拔出来，"
										+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就温柔地抓住它，然后轻柔地把它扭回[npc2.her][npc2.nipple+]里。",
		
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.tentacle]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并温柔地把它扭回[npc2.her][npc2.nipple+]里。",
		
								"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.tentacle]从[npc2.namePos][npc2.nipple+]里拔出来，"
										+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边温柔地将[npc2.nipple+]压向[npc.her][npc.tentacle+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]尝试将[npc.tentacle]从[npc2.namePos]的[npc2.nipple(true)]里拔出来，"
										+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就粗暴地抓住它，并凶猛地将它扭回[npc2.her][npc2.nipple+]里。",
		
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.tentacle]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并粗暴地把它扭回[npc2.her][npc2.nipple+]里。",
		
								"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.tentacle]从[npc2.namePos][npc2.nipple+]里拔出来，"
										+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边粗暴地将[npc2.nipple+]压向[npc.her][npc.tentacle+]。"));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]尝试将[npc.tentacle]从[npc2.namePos]的[npc2.nipple(true)]里拔出来，"
										+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就牢牢地抓住它，然后热切地把它扭回[npc2.her][npc2.nipple+]里。",
		
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.tentacle]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并饥渴地把它扭回[npc2.her][npc2.nipple+]里。",
		
								"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.tentacle]从[npc2.namePos][npc2.nipple+]里拔出来，"
										+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边温柔地将[npc2.nipple+]压向[npc.her][npc.tentacle+]。"));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]尝试将[npc.tentacle]从[npc2.namePos]的[npc2.nipple(true)]里拔出来，"
								+ "但事实证明，[npc.her]的努力是徒劳的，[npc2.name]向前移动，将其插入[npc2.her][npc2.nipple+]。",

						"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.tentacle]从[npc2.name]的身体里拔出来，但[npc2.name]马上向前移动，让它继续插入[npc2.her][npc2.nipple+]。",

						"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.tentacle]从[npc2.namePos][npc2.nipple+]里拔出来，"
								+ "但[npc2.name]一边快速地改变体位，一边让[npc2.nipple+]继续压在[npc.her][npc.tentacle+]上。"));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止触手交乳头";
		}

		@Override
		public String getActionDescription() {
			return "把[npc.tentacle+]抽出[npc2.namePos][npc2.nipple+]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将[npc.tentacle+]从[npc2.namePos][npc2.pussy+]中拉出，"
									+ "霸道地用[npc.tentacleTip+]最后一次上下磨蹭[npc2.her][npc2.breasts+]，然后将它移开。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.tentacle+]从[npc2.her][npc2.nipple+]中猛抽出来，结束了粗暴性交。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]将[npc.tentacle]滑出[npc2.namePos][npc2.nipple+]，在最后一次用[npc.tentacleTip]来回磨蹭[npc2.her][npc2.breasts+]后，将[npc2.her]推开。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.tentacle+]从[npc2.her][npc2.nipple+]中拔出来，结束了性交。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.Name]从[npc2.her]的[npc2.nipple(true)]里拔出来时，[npc2.name]忍不住漏出一声[npc2.sob+]，"
										+ "[npc2.she]仍然不停地哭泣，无力地继续反抗着[npc.name]。",
	
								"[npc2.name]发出一阵[npc2.a_sob+]，拼命地嘶哑着反抗[npc.Name]，将[npc2.nipple+]从[npc.Name]身上抽离，泪水如小溪般从[npc2.face]上流了下来。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]将[npc.tentacle+]拔出[npc2.Name][npc2.nipple+]，[npc2.name]发出了[npc2.a_moan+]，渴求着[npc.name]的更多“照顾”。",
	
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]无比渴望得到[npc.namePos]的更多关注。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction USING_TENTACLE_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "触手交乳头";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.namePos][npc2.tentacle+]抽插[npc.nipple+]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tentacle]，慢慢地将它引导至自己[npc.breasts+]，"
									+ "[npc.her]漏出一声小小的[npc.moan]，然后温柔地挺起胸部，强迫[npc2.herHim]插入[npc.her][npc.nipple+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.tentacle]，把它对准自己[npc.nipple+]，"
									+ "[npc.she]慢慢地将自己的[npc.breasts]向前推，塞入[npc2.her][npc2.tentacle+]，并不时漏出一声轻柔的[npc.moan]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tentacle]，饥渴地将它引导至自己[npc.breasts+]，"
									+ "[npc.her]发出一阵[npc.a_moan+]，亢奋地挺起胸部，强迫[npc2.herHim]插入自己[npc.nipple+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.tentacle]，把它对准自己[npc.nipple+]，"
									+ "[npc.she]饥渴地将自己的[npc.breasts]往前推，把[npc2.tentacle+]插入其中，并不时漏出一声[npc.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tentacle]，猛地将其拉至自己[npc.breasts+]，"
									+ "[npc.her]发出一阵[npc.a_moan+]，暴力地将胸部向前顶，强迫[npc2.herHim]插入自己[npc.nipple+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.tentacle]，把它对准自己[npc.nipple+]，"
									+ "[npc.her]强势地将自己的[npc.breasts]往前推，让[npc2.tentacle+]插入其中，并不时漏出一声[npc.a_moan+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tentacle]，慢慢地将它引导至自己[npc.breasts+]，"
									+ "[npc.her]发出一阵[npc.a_moan+]，挺起胸部，强迫[npc2.herHim]插入自己[npc.nipple+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.tentacle]，把它对准自己[npc.nipple+]，"
									+ "[npc.she]将自己的[npc.breasts]往前推，把[npc2.tentacle+]插入其中，并不时漏出一声[npc.a_moan+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.herHim]的身体，发出一声轻柔的[npc2.moan]，"
										+ "[npc2.she]温柔地将[npc2.tentacle]向前顶，开始操[npc.namePos][npc.nipple+]。",
	
								"[npc2.name]轻柔地[npc2.moan]，温柔地挺进自己的[npc2.tentacle]，"
										+ "将它深深插进[npc.namePos][npc.nipple+]，开始操[npc.herHim]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "[npc2.she]热切地将[npc2.tentacle]向前顶，开始亢奋地操[npc.namePos][npc.nipple+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，热切地挺进自己的[npc2.tentacle]，"
										+ "将它深深插进[npc.namePos][npc.nipple+]，开始竭力地操[npc.herHim]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "为了警告[npc.name]别得意忘形，[npc2.she]粗暴地向前猛撅[npc2.tentacle]，开始无情地暴操[npc.her][npc.nipple+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，粗暴地猛插自己的[npc2.tentacle]，"
										+ "[npc2.she]无情地暴操[npc.namePos][npc.nipple+]，宣告着自己的支配权。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]挺进[npc.name]的身体，不禁漏出一声[npc2.a_moan+]，[npc2.she]将[npc2.tentacle]向前顶，开始操[npc.namePos][npc.nipple+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，向前挺进[npc2.tentacle]，将它深深插入[npc.namePos][npc.nipple+]，开始操[npc.herHim]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]紧紧裹住[npc2.name]的[npc2.tentacle]，[npc2.name]不禁发出一声[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着推开[npc.name]，拼命地想把自己[npc2.tentacle+]从对方[npc.nipple+]里拔出来。",
	
								"[npc.Name]强行将[npc2.name]的[npc2.tentacle]深深插入自己[npc.nipple+]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
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
							"[npc2.Name]贪婪地将[npc2.tentacle+]撞入[npc.namePos][npc.breast+]深处，"
									+ "[npc2.she]发出[npc2.a_moan+]，热切地插入[npc.namePos][npc.nipple+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.tentacle+]深深插入[npc.namePos][npc.nipple+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，热切地顶着[npc2.tentacle+]，拼命要插入[npc.namePos][npc.nipple+]最深处。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.tentacle]从[npc.namePos]的[npc.nipple(true)]中拔出，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.tentacle+]在自己[npc.nipple+]中继续抽插。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.tentacle]从[npc.namePos][npc.nipple+]中抽离。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将[npc2.tentacle+]滑入[npc.namePos][npc.breast+]深处，"
									+ "[npc2.she]发出柔和的[npc2.moan]，轻柔地抽插[npc.namePos][npc.nipple+]。",
		
							"[npc2.name]慢慢地将[npc2.tentacle+]深入[npc.namePos][npc.nipple+]，口中飘出一声轻柔的[npc2.moan]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地用[npc2.tentacle+]深入[npc.namePos][npc.nipple+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.tentacle+]撞入[npc.namePos][npc.breast+]深处，"
									+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地插入[npc.namePos][npc.nipple+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地将[npc2.tentacle+]深深插入[npc.namePos][npc.nipple+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地控制着[npc2.her][npc2.tentacle+]，拼命要插入[npc.namePos][npc.nipple+]最深处。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.tentacle+]撞入[npc.namePos][npc.breast+]深处，"
									+ "[npc2.she]发出一阵[npc2.a_moan+]，不停抽插着[npc.namePos][npc.nipple+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.tentacle+]深深插入[npc.namePos][npc.nipple+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，用[npc2.tentacle+]深入[npc.namePos][npc.nipple+]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_TENTACLE_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "触手交乳头(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地用[npc.nipple+]吞吐[npc2.namePos][npc2.tentacle+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声轻柔的[npc.moan]，温柔地将自己的胸部向前挺，以便[npc2.namePos][npc2.tentacle+]可以在自己[npc.nipple+]中插得更深。",

					"[npc.Name]轻柔地[npc.moan]，温柔地向前挺胸，强迫[npc2.namePos][npc2.tentacle+]在自己[npc.nipple+]中插得更深。",

					"[npc.name]慢慢地挺起胸部，"
							+ "[npc.lips+]间飘出一声轻柔的[npc.moan]，[npc.her]设法让[npc2.namePos][npc2.tentacle+]深深插入[npc.her][npc.nipple+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "触手交乳头";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.nipple+]饥渴地吞吐[npc2.namePos][npc2.tentacle+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，热切地将自己的胸部向前挺，帮着[npc2.namePos][npc2.clit+]可以在自己[npc.nipple+]中插得更深。",

					"[npc.Name]发出[npc.a_moan+]，亢奋地向前挺胸，强迫[npc2.namePos][npc2.tentacle+]在自己[npc.nipple+]中插得更深。",

					"[npc.name]积极地挺起胸部，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.tentacle+]插入自己[npc.nipple+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "触手交乳头(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.nipple+]粗暴地吞吐[npc2.namePos][npc2.tentacle+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，猛烈地将自己的胸部向前挺，强迫[npc2.namePos][npc2.tentacle+]可以在自己[npc.nipple+]中插得更深。",

					"[npc.name]发出[npc.a_moan+]，粗暴地将自己的胸部向前挺，强迫[npc2.namePos[npc2.tentacle+]在自己[npc.nipple+]中插得更深。",

					"[npc.name]激烈地挺起胸部，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]粗暴地强迫[npc2.namePos][npc2.tentacle+]插入自己[npc.nipple+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_TENTACLE_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "触手交乳头";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.nipple+]吞吐[npc2.namePos][npc2.tentacle+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，快速地将自己的胸部向前挺，帮着[npc2.namePos][npc2.tentacle+]在自己[npc.nipple+]中插得更深。",

					"[npc.Name]发出[npc.a_moan+]，向前挺胸，强迫[npc2.namePos][npc2.tentacle+]在自己[npc.nipple+]中插得更深。",

					"[npc.name]挺起胸部，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.tentacle+]插入自己[npc.nipple+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "被触手操乳头(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.nipple+]饥渴地吞吐[npc2.namePos][npc2.tentacle+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，热切地将自己的胸部向前挺，帮着[npc2.namePos][npc2.clit+]可以在自己[npc.nipple+]中插得更深。",

					"[npc.Name]发出[npc.a_moan+]，亢奋地向前挺胸，强迫[npc2.namePos][npc2.tentacle+]在自己[npc.nipple+]中插得更深。",

					"[npc.name]积极地挺起胸部，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.tentacle+]插入自己[npc.nipple+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗被触手操乳头";
		}

		@Override
		public String getActionDescription() {
			return "把[npc.nipple+]抽出[npc2.namePos][npc2.tentacle+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
										+ "[npc.she]无力地试着将[npc2.namePos][npc2.tentacle]从自己[npc.nipple+]里拔出来。",
	
								"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.nipple(true)]从[npc2.namePos]令人憎恶的性器抽离，"
										+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.tentacle+]依然从容地在[npc.her][npc.breast+]里滑进滑出。",
	
								"[npc.name]拼命地尝试把[npc.breasts]挪开，"
										+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.tentacle+]依然温柔地滑进[npc.her][npc.nipple+]深处。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
										+ "[npc.she]无力地试着将[npc2.namePos][npc2.tentacle]从自己[npc.nipple+]里拔出来。",
	
								"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.nipple(true)]从[npc2.namePos]令人憎恶的性器抽离，"
										+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.tentacle+(true)]依然从容地在[npc.her][npc.breast+(true)]里滑进滑出。",
	
								"[npc.name]拼命地尝试把[npc.breasts]挪开，"
										+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.tentacle+]依然贪婪地插入[npc.her][npc.nipple+]深处。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
										+ "[npc.she]无力地试着将[npc2.namePos][npc2.tentacle]从自己[npc.nipple+]里拔出来。",
	
								"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.nipple(true)]从[npc2.namePos]令人憎恶的性器抽离，"
										+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.tentacle+(true)]依然疯狂地在[npc.her][npc.breast+(true)]里抽送爆操。",
	
								"[npc.name]拼命地尝试把[npc.breasts]挪开，"
										+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.tentacle+(true)]依然激烈地插入[npc.nipple+]深处。"));
						break;
					default:
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
								+ "[npc.she]无力地试着将[npc2.namePos][npc2.tentacle]从自己[npc.nipple+]里拔出来。",

						"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.nipple(true)]从[npc2.namePos]令人憎恶的性器抽离，"
								+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.tentacle+]依然深深插在[npc.her][npc.breast+]里。",

						"[npc.name]拼命地尝试把[npc.breasts]挪开，"
								+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.tentacle+]依然深深插在[npc.her][npc.nipple+]里。"));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "停止被触手操乳头";
		}

		@Override
		public String getActionDescription() {
			return "把[npc2.name]的[npc2.tentacle]抽出[npc.nipple+]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]的[npc2.tentacle]从自己[npc.nipple+]里抽出，[npc.she]愤怒地咆哮着，命令[npc2.name]不准再操了。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]的[npc2.tentacle(true)]从自己[npc.nipple+]中抽出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]的[npc2.tentacle]从自己[npc.nipple+]中抽出，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]不要再操了。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc2.her]的[npc2.tentacle]从自己[npc.nipple+]中抽出。"));
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
								"[npc.Name]不再让[npc2.name]操自己[npc.nipple+]，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
	
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续抽插[npc.namePos][npc.nipple+]的渴望。"));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction NIPPLE_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "收紧乳头穴";
		}

		@Override
		public String getActionDescription() {
			return "用肌肉发达的乳穴挤弄包裹[npc2.namePos]的[npc2.tentacle]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getNippleOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]发出一阵[npc.a_moan+]，继续专心用[npc.her]肌肉极致发达的[npc.nipple(true)]挤弄包裹着[npc2.namePos][npc2.tentacle+]。",

					isTargetedCharacterInanimate()
						?null
						:"[npc.Name]发出一阵[npc.a_moan+]，继续专心控制自己[npc.nipple(true)]内部极致发达的肌肉。"
							+ "[npc.Name]挤弄包裹着[npc2.namePos][npc2.tentacle+]，让[npc2.herHim]不禁漏出一声愉悦的呻吟。",

					"[npc.Name]以稳定的节奏[npc.moans]着，[npc.she]继续专心"
							+ "用[npc.her]肌肉极致发达且[npc.nipple+]挤弄包裹着[npc2.namePos][npc2.tentacle+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，专心控制[npc.nipple(true)]内里极致发达的肌肉，"
							+ "[npc.she]挤弄按摩着[npc2.namePos][npc2.tentacle+]时，不禁愉悦地尖叫一声。");
		}
	};
}
