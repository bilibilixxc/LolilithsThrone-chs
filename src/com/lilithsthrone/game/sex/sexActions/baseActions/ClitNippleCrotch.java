package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
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
 * @since 0.4.7.11
 * @version 0.4.7.11
 * @author Innoxia
 */
public class ClitNippleCrotch {

	private static String getNippleTitle(GameCharacter character) {
		if(character.getBreastCrotchShape()==BreastShape.UDDERS) {
			return "腹乳乳头";
		} else {
			return "胯乳乳头";
		}
	}
	
	public static final SexAction TEASE_CLIT_OVER_NIPPLE_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this)))+"阴蒂操";
		}

		@Override
		public String getActionDescription() {
			return "你用[npc.clit+]挑逗着[npc2.name]的[npc2.crotchNipple+]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()
					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)
					&& Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将自己[npc.clit+]顶向[npc2.namePos][npc2.crotchBoobs+]，"
									+ "开始缓慢地用[npc.clitTip+]上下挑逗[npc2.her]的其中一个[npc2.crotchNipples+]，随时准备插入[npc2.herHim]。",

							"[npc.name]轻声[npc.moan]着，将自己[npc.clit+]移向[npc2.namePos][[npc2.crotchBoobs+]，"
									+ "然后开始温柔地用[npc.clitTip]上下磨蹭其中一个[npc2.crotchNipples+]。",

							"[npc.name]温柔地用[npc.clit][npc.clitTip+]上下挑逗[npc2.namePos][npc2.crotchNipple+]，"
									+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了轻柔的[npc.moan]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将自己[npc.clit+]顶向[npc2.namePos][npc2.crotchBoobs+]，"
									+ "开始急切地用[npc.clitTip+]上下挑逗[npc2.her]的其中一个[npc2.crotchNipples+]，随时准备插入[npc2.herHim]。",

							"[npc.Name]发出一阵[npc.a_moan+]，把[npc.clit+]扶到[npc2.namePos][npc2.crotchBoobs+]旁，"
									+ "然后开始急切地用[npc.clitTip]上下磨蹭其中一个[npc2.crotchNipples+]。",

							"[npc.name]急切地用[npc.clit][npc.clitTip+]上下挑逗[npc2.namePos][npc2.crotchNipple+]，"
									+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了一阵[npc.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.clit+]磨蹭着[npc2.namePos][npc2.crotchBoobs+]，"
									+ "接着向后退了一点，开始用[npc.clitTip+]上下挑逗[npc2.her]其中一个[npc2.crotchNipples+]，随时准备插入[npc2.herHim]。",

							"[npc.name]发出一阵[npc.a_moan+]，把[npc.clit+]挪向[npc2.namePos][npc2.crotchBoobs+]，"
									+ "然后开始粗暴地用[npc.clitTip]上下磨蹭其中一个[npc2.crotchNipples+]。",

							"[npc.name]用[npc.clit][npc.clitTip+]粗暴地来回磨蹭[npc2.namePos][npc2.crotchNipple+]，"
									+ "一想到只要[npc.she]高兴随时可以操干[npc2.name]，[npc.name]就发出了一阵[npc.a_moan+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将自己[npc.clit+]顶向[npc2.namePos][npc2.crotchBoobs+]，"
									+ "开始用[npc.clitTip+]上下挑逗[npc2.her]的其中一个[npc2.crotchNipples+]，随时准备插入[npc2.herHim]。",

							"[npc.name]发出一阵[npc.a_moan+]，把[npc.clit+]扶到[npc2.namePos][npc2.crotchBoobs+]旁，用[npc.clitTip]来回磨蹭[npc2.her]其中一个[npc2.crotchNipples+]。",

							"[npc2.namePos]在[npc.her][npc2.crotchNipple+]上面上下抚动着[npc.clit]的[npc.clitTip+]，"
									+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了一阵[npc.a_moan+]。"));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]感觉到[npc2.crotchNipple+]传来的阵阵快感，一声轻柔的[npc2.moan]从[npc2.her][npc2.lips+]间飘出，"
										+"[npc2.she]温柔地将腹部推向[npc.namePos]的[npc.clit]。",
	
								"[npc2.name]发出一声柔和的[npc2.moan]，随后温柔地将[npc2.crotchNipple+]贴向[npc.namePos]的[npc.clit]。",
	
								"[npc2.name]感受到[npc.namePos]的[npc.clit]正刺激着[npc2.her][npc2.crotchNipple+]，[npc2.she]愉悦地[npc2.moanVerb]着，并温柔地挺起[npc2.her]的腹部作为回应。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]感受到[npc2.crotchNipple+]传来阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间爆发而出，"
										+ "[npc2.she]饥渴地将腹部推向[npc.namePos]的[npc.clit]。",
	
								"[npc2.name]发出一声迷乱的[npc2.moan]，然后迫不及待地将[npc.namePos]的[npc.clit]塞入自己的腹部。",
	
								"[npc2.name]感受到[npc.namePos]的[npc.clit]正刺激着[npc2.her][npc2.crotchNipple+]，[npc2.she]愉悦地[npc2.moanVerb]着，并饥渴地挺起[npc2.her]的腹部作为回应。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]感受到[npc2.crotchNipple+]传来阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间爆发而出，"
										+ "[npc2.she]粗暴地用腹部抵住[npc.namePos]的[npc.clit]。",
	
								"[npc2.name]发出一声迷乱的[npc2.moan]，然后粗暴地将[npc.namePos]的[npc.clit]塞入自己的腹部。",
	
								"[npc2.name]感受到[npc.namePos]的[npc.clit]正刺激着[npc2.her][npc2.crotchNipple+]，[npc2.she]愉悦地[npc2.moanVerb]着，并粗暴地挺起[npc2.her]的腹部作为回应。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]感受到[npc2.crotchNipple+]传来阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间爆发而出，"
										+"[npc2.she]将腹部推向[npc.namePos]的[npc.clit]。",
	
								"[npc2.name]发出一声[npc2.moan]，然后将[npc2.her]的腹部推向[npc.namePos]的[npc.clit]。",
	
								"[npc2.name]感受到[npc.namePos]的[npc.clit]正刺激着[npc2.her][npc2.crotchNipple+]，[npc2.she]愉悦地[npc2.moanVerb]着，并挺起[npc2.her]的腹部作为回应。"));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isNippleCrotchVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"一想到自己即将要被夺走乳头贞操，[npc2.namePos][npc2.lips+]间不禁爆发出一阵[npc2.A_sob+]。",
										"[npc2.Name]意识到自己可能将失去乳头贞操，不禁发出一声绝望的[npc2.sob]。",
										"一想到自己马上要被[npc.namePos][npc.clit+]夺走乳头贞操，[npc2.name]就不禁绝望地[npc2.sobVerb]着。"));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.namePos]想到接下来要发生的事，[npc2.lips+]间不禁爆发出一阵[npc2.A_sob+]，[npc2.speech(不！快停下！我的乳头还没被操过呢！)]",
										"[npc2.Name]发出一声绝望的[npc2.sob]，苦苦乞求，[npc2.speech(求求你！不要这样做！我的乳头还没被操过呢！)]",
										"[npc2.Name]一想到接下来要发生的事情，不禁痛苦地[npc2.sobsVerb]，绝望地哀求着，[npc2.speech(不要！快停下！我不想失去我的乳头童贞！)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.crotchNipple(true)]从[npc.namePos]的[npc.clit]抽离。",
										"[npc2.Name]不断反抗着[npc.Name]，不禁发出绝望的[npc2.sob]。",
										"[npc2.name]痛苦地[npc2.sobVerb]着，试图远离[npc.Name]。"));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.crotchNipple(true)]从[npc.namePos]的[npc.clit]抽离，"
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE_CROTCH);
		}
	};
	
	public static final SexAction FORCE_CLIT_OVER_NIPPLE_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "用阴蒂挑逗("+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+")";
		}

		@Override
		public String getActionDescription() {
			return "把[npc2.name][npc2.clit]的[npc2.clitTip]在你[npc.crotchNipple+]上磨蹭，挑逗[npc2.her]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterPerformingAction().hasBreastsCrotch()
					&& Main.sex.getCharacterPerformingAction().isBreastCrotchFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)
					&& Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.clit+]，把它挪到自己[npc.crotchBoobs+]。"
									+ "[npc.she]缓慢地用[npc2.clitTip+]上下磨蹭[npc.her][npc.crotchNipple+]，挑逗[npc2.name]随时可能插入。",

							"[npc.name]轻声[npc.moan]，抓住[npc2.namePos][npc2.clit+]，将它移向自己[npc.crotchBoobs+]，"
									+ "然后开始温柔地用[npc2.clitTip]上下磨蹭[npc.her][npc.crotchNipple+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.clit+]，温柔地用[npc2.clitTip+]磨蹭自己[npc.crotchNipple+]，"
									+ "[npc.she]发出一声轻柔的[npc.moan]，挑逗[npc2.Name]随时可能插入。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.clit+]，把它挪到自己[npc.crotchBoobs+]。"
									+ "[npc.she]急切地用[npc2.clitTip+]上下磨蹭[npc.her][npc.crotchNipple+]，挑逗[npc2.name]随时可能插入。",

							"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.clit+]并引导它到自己[npc.crotchBoobs+]，"
									+ "然后开始急切地用[npc2.clitTip]上下磨蹭[npc.her][npc.crotchNipple+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.clit+]，急切地用[npc2.clitTip+]磨蹭自己[npc.crotchNipple+]，"
									+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.clit+]，把它猛拉到自己[npc.crotchBoobs+]。"
									+ "[npc.she]粗暴地强迫[npc2.clitTip+]上下磨蹭[npc.her][npc.crotchNipple+]，挑逗[npc2.name]随时可能插入。",

							"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.clit+]并把它猛拉到自己[npc.crotchBoobs+]，"
									+ "然后开始粗暴地强迫[npc2.clitTip]上下磨蹭[npc.her][npc.crotchNipple+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.clit+]，粗暴地用[npc2.clitTip+]磨蹭自己[npc.crotchNipple+]，"
									+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.clit+]，把它挪到自己[npc.crotchBoobs+]。"
									+ "[npc.she]用[npc2.clitTip+]上下磨蹭[npc.her][npc.crotchNipple+]，挑逗[npc2.name]随时可能插入。",

							"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.clit+]并引导它到自己[npc.crotchBoobs+]，"
									+ "然后开始用[npc2.clitTip]上下磨蹭[npc.her][npc.crotchNipple+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.clit+]，用[npc2.clitTip+]磨蹭自己[npc.crotchNipple+]，"
									+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]温柔地用[npc2.clit]上下磨蹭[npc.namePos][npc.crotchNipple+]，一声轻柔的[npc2.moan]从[npc2.her][npc2.lips+]间飘出。",
	
								"[npc2.name]发出一声柔和的[npc2.moan]，随后温柔地用[npc2.clit]前后磨蹭[npc.namePos][npc.crotchNipple+]。",
	
								"[npc2.name]感受到[npc2.clit]传来阵阵快感，不禁发出兴奋的[npc2.moan]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.clit]温柔地上下磨蹭[npc.namePos][npc.crotchNipple+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]粗暴地用[npc2.clit]来回磨蹭[npc.namePos][npc.crotchNipple+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.Name]发出一阵[npc2.a_moan+]，激烈地用[npc2.clit]来回磨蹭[npc.namePos][npc.crotchNipple+]。",
	
								"[npc2.Name]感觉到[npc2.clit]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，为了提醒[npc.name]谁才是主导者，"
										+ "[npc2.she]开始用[npc2.clit]粗暴地上下磨蹭[npc.namePos][npc.crotchNipple+]。"));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]热切地用[npc2.clit]来回磨蹭[npc.namePos][npc.crotchNipple+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.Name]发出一声迷乱的[npc2.moan]，然后迫不及待地用[npc2.clit]来回磨蹭[npc.namePos][npc.crotchNipple+]。",
	
								"[npc2.name]感受到[npc2.clit]传来阵阵快感，不禁发出兴奋的[npc2.moan]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.clit]急切地上下磨蹭[npc.namePos][npc.crotchNipple+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]用[npc2.clit]来回磨蹭[npc.namePos][npc.crotchNipple+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.Name]发出一声迷乱的[npc2.moan]，然后用[npc2.clit]来回磨蹭[npc.namePos][npc.crotchNipple+]。",
	
								"[npc2.name]感受到[npc2.clit]传来阵阵快感，不禁发出兴奋的[npc2.moan]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.clit]上下磨蹭[npc.namePos][npc.crotchNipple+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.clit+]从[npc.namePos][npc.crotchNipple+]中拔出来。",
	
								"[npc2.Name]发出一声绝望的[npc2.sob]，然后拼命地尝试将[npc2.clit+]从[npc.namePos][npc.labia+]中拔出来。",
	
								"[npc2.Name]痛苦地[npc2.sobsVerb]，并哀求[npc.name]放过自己的[npc2.clit]。"));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.CLIT, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH);
		}
	};
	
	
	public static final SexAction CLIT_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()
					&& Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis();
		}
		
		@Override
		public String getActionTitle() {
			return "阴蒂操"+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.clit+]插进[npc2.namePos][npc2.crotchNipple+]，开始操[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]慢慢用[npc.clit][npc.clitTip+]挑逗着[npc2.namePos][npc2.crotchBoobs+]，"
									+ "发出轻微的[npc.moan]，缓缓地向前推，将[npc.clit+]没入[npc2.namePos][npc2.crotchNipple+]里。",

							"[npc.Name]将其[npc.clit][npc.clitTip+]靠在[npc2.namePos][npc2.crotchBoobs+]上，"
									+ "[npc.she]以缓慢而稳定的力度，轻柔地将它深深地插入[npc2.namePos][npc2.crotchNipple+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.clit][npc.clitTip+]饥渴地挑逗着[npc2.namePos][npc2.crotchBoobs+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.clit+]深深插入[npc2.her][npc2.crotchNipple+]。",

							"[npc.Name]将其[npc.clit][npc.clitTip+]靠在[npc2.namePos][npc2.crotchBoobs+]上，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos][npc2.crotchNipple+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.clit][npc.clitTip+]粗暴地磨蹭[npc2.namePos][npc2.crotchBoobs+]，"
									+ "发出[npc.a_moan+]，然后粗暴地向前猛推，将[npc.clit+]深深插入[npc2.her][npc2.crotchNipple+]。",

							"[npc.Name]将其[npc.clit][npc.clitTip+]靠在[npc2.namePos][npc2.crotchBoobs+]上，"
									+ "用力前推，粗暴地将它深深插入[npc2.her][npc2.crotchNipple+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.clit][npc.clitTip+]饥渴地挑逗着[npc2.namePos][npc2.crotchBoobs+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.clit+]深深插入[npc2.her][npc2.crotchNipple+]。",

							"[npc.Name]将其[npc.clit][npc.clitTip+]靠在[npc2.namePos][npc2.crotchBoobs+]上，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos][npc2.crotchNipple+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.clit][npc.clitTip+]挑逗着[npc2.namePos][npc2.crotchBoobs+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，将[npc.clit+]深深插入[npc2.her][npc2.crotchNipple+]。",

							"[npc.Name]将其[npc.clit][npc.clitTip+]靠在[npc2.namePos][npc2.crotchBoobs+]上，"
									+ "[npc.she]小幅度前推，将它深深插入[npc2.namePos][npc2.crotchNipple+]。"));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.clit+]顶进[npc2.name]体内，[npc2.herHim]发出轻柔的[npc2.moan]，"
										+ "于是[npc2.she]温柔地挺起自己的腹部，以便让它在[npc2.crotchBoob+]里插得更深。",
	
								"[npc2.name]轻柔地[npc2.moan]着，温柔地将腹部挺起来，"
										+ "将[npc.namePos][npc.clit+]更深地插入自己[npc2.crotchNipple+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.clit+]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "于是[npc2.her]粗暴地挺起腹部，强迫它在自己[npc2.crotchBoob+]里插得更深。",
	
								"[npc2.name]发出[npc2.a_moan+]，粗暴地挺动着自己的腹部，"
										+ "粗暴地强迫[npc.Name]把[npc.her][npc.clit+]在自己[npc2.crotchNipple+]里插得更深。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.clit+]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "于是[npc2.she]急切地挺起腹部，以便让它在[npc2.crotchBoob+]里插得更深。",
	
								"[npc2.name]发出[npc2.a_moan+]，饥渴地挺起了自己的腹部，"
										+ "饥渴地让[npc.namePos][npc.clit+]更加深入[npc2.her][npc2.crotchNipple+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.clit+]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "于是[npc2.she]挺起腹部，以便让它在[npc2.crotchBoob+]里插得更深。",
	
								"[npc2.name]发出[npc2.a_moan+]，挺起了自己的腹部，"
										+ "让[npc.namePos][npc.clit+]更加深入[npc2.her][npc2.crotchNipple+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.clit+]进入了自己的身体，不禁漏出一声[npc2.a_sob+]，"
										+ "泪水不停地从[npc2.her]的[npc2.face]上淌下，[npc2.she]哀求[npc.Name]从自己身体里拔出来。",
	
								"[npc2.name]发出[npc2.a_sob+]，徒劳地挣扎扭身，试图拔出讨厌的插入物，"
										+ "但[npc.namePos]不请自来的[npc.clit]却在[npc2.crotchNipple+]中插得更深，泪水从[npc2.her]的[npc2.face]上流了下来。"));
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
							"作为回应，[npc2.Name]饥渴地挺起[npc2.her]的腹部，"
									+ "[npc2.she]热切地帮着[npc.namePos][npc.clit+]深深插入[npc2.her][npc2.crotchNipple+]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]急切地挺起腹部，乞求[npc.name]继续操[npc2.her]的[npc2.crotchBoobs]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，渴望地挺起自己的腹部，"
									+ "做出有助于[npc.clit+]更深地插入[npc2.her][npc2.crotchNipple+]的动作，急切地乞求[npc.Name]继续操[npc2.her]的[npc2.crotchBoobs]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要逃离[npc.namePos]的[npc.clit]，但没能成功，"
									+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地乞求[npc.Name]从自己[npc2.crotchNipple+]里拔出来，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "[npc2.she]恳求[npc.name]从自己[npc2.crotchNipple+]里拔出来，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
		
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]从[npc2.her][npc2.crotchNipple+]里拔出来。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]挺起了[npc2.her]的腹部，"
									+ "[npc2.she]帮着[npc.namePos][npc.clit+]深深插入[npc2.her][npc2.crotchNipple+]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
	
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]挺起自己的腹部，乞求[npc.name]继续操[npc2.her]的[npc2.crotchBoobs]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，渴望地挺起自己的腹部，"
									+ "做出有助于[npc.clit+]更深地插入[npc2.her][npc2.crotchNipple+]的动作，乞求[npc.Name]继续操[npc2.her]的[npc2.crotchBoobs]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]缓缓地挺起自己的腹部，"
									+ "[npc2.she]发出一声轻柔的[npc2.moan]，温柔地乞求着[npc.Name]继续操干[npc2.her][npc2.crotchBoobs+]。",
	
							" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
									+ "[npc2.she]缓缓地挺起腹部，乞求[npc.name]继续操[npc2.her]的[npc2.crotchBoobs]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地挺起自己的腹部，"
									+ "做出有助于[npc.clit+]更深地插入[npc2.her][npc2.crotchNipple+]的动作，乞求[npc.Name]继续操[npc2.her]的[npc2.crotchBoobs]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]暴力地推起自己的腹部，"
									+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地命令[npc.Name]继续干[npc2.her]的[npc2.crotchBoobs]。",
	
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]粗鲁地用腹部压向[npc.namePos]的腹股沟，命令[npc.Name]继续操[npc2.her]的[npc2.crotchBoobs]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地用腹部撞击[npc.namePos]的胯下，"
									+ "强迫[npc.name]将[npc.clit+]深深插入[npc2.her][npc2.crotchNipple+]，命令[npc.name]继续操[npc2.her]的[npc2.crotchBoobs]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction CLIT_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "阴蒂操"+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+"(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地用你的[npc.clit]在[npc2.namePos][npc2.crotchNipple+]里滑进滑出。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]温柔地将[npc.clit+]深深插入[npc2.namePos][npc2.crotchNipple+]，"
							+ "[npc.name]开始前后摇摆[npc.hips]，缓缓地操[npc2.her]的[npc2.crotchBoobs]，每次推入都会发出一阵轻微的[npc.moan]。",

					"[npc.name]缓缓地将[npc.clit+]插入[npc2.namePos][npc2.crotchNipple+]，"
							+ "轻柔地将[npc.hips]向前推，[npc.she]温柔地干着[npc2.name]的[npc2.crotchBoobs]，不禁漏出一小声[npc.moan]。",

					"[npc.Name]将[npc.clit+]滑入[npc2.namePos][npc2.crotchNipple+]，开始温柔地前后抽动[npc.hips]，不禁漏出一小声[npc.moan]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，同时缓缓地操[npc2.namePos]的[npc2.crotchBoobs]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "阴蒂操"+Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this)));
		}

		@Override
		public String getActionDescription() {
			return "继续在[npc2.namePos][npc2.crotchNipple+]里不断抽送着你[npc.clit+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.clit+]深深插入[npc2.namePos][npc2.crotchNipple+]，"
							+ "[npc.name]开始热切地前后摇摆[npc.hips]，兴奋地操[npc2.her]的[npc2.crotchBoobs]，每次突入都会发出一阵[npc.a_moan+]。",

					"[npc.name]亢奋地将[npc.clit+]深深插入[npc2.namePos][npc2.crotchNipple+]，"
							+ "疯狂地向前挺进[npc.hips]，贪婪地干着[npc2.namePos]的[npc2.crotchBoobs]，不禁发出[npc.a_moan+]。",

					"[npc.Name]将[npc.clit+]深深插入[npc2.namePos][npc2.crotchNipple+]，开始热切地用[npc.hips]前后抽送打桩，不禁发出[npc.a_moan+]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，同时饥渴地操着[npc2.namePos]的[npc2.crotchBoobs]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "阴蒂操"+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+"(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地用你[npc.clit+]在[npc2.namePos][npc2.crotchNipple+]里不断抽插。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]粗暴地将[npc.clit+]深深插入[npc2.namePos][npc2.crotchNipple+]，"
							+ "[npc.name]开始粗暴地前后摇摆[npc.hips]，野蛮地操[npc2.her]的[npc2.crotchBoobs]，每次推入都会发出一阵[npc.a_moan+]。",

					"[npc.name]激烈地将[npc.clit+]深深插入[npc2.namePos][npc2.crotchNipple+]，"
							+ "[npc.name]开始粗暴地向前猛撞[npc.hips]，大力地干着[npc2.her]的[npc2.crotchBoobs]，不禁发出[npc.moans+]。",

					"[npc.Name]激烈地将[npc.cock+]深深插入[npc2.namePos][npc2.crotchNipple+]，[npc.hips]粗暴地来回撞击，不禁发出[npc.a_moan+]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，同时激烈地操着[npc2.namePos]的[npc2.crotchBoobs]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "阴蒂操"+Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this)));
		}

		@Override
		public String getActionDescription() {
			return "继续操[npc2.namePos][npc2.crotchNipple+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.clit+]深深插入[npc2.namePos][npc2.crotchNipple+]，"
							+ "[npc.name]开始前后摇摆[npc.hips]，兴奋地操[npc2.her]的[npc2.crotchBoobs]，每次突入都会发出一阵[npc.a_moan+]。",

					"[npc.name]将[npc.clit+]深深插入[npc2.namePos][npc2.crotchNipple+]，"
							+ "开始向前挺动[npc.hips]，急切地操着[npc2.her]的[npc2.crotchBoobs]，不禁发出[npc.a_moan+]。",

					"[npc.Name]将[npc.clit+]深深插入[npc2.namePos][npc2.crotchNipple+]，开始用[npc.hips]前后抽送打桩，不禁发出[npc.a_moan+]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，同时操着[npc2.namePos]的[npc2.crotchBoobs]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "阴蒂操"+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+ "(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地把你[npc.clit+]塞进[npc2.namePos][npc2.crotchNipple+]里不停抽送。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.clit+]深深插入[npc2.namePos][npc2.crotchNipple+]，"
							+ "[npc.name]开始热切地前后摇摆[npc.hips]，兴奋地操[npc2.her]的[npc2.crotchBoobs]，每次突入都会发出一阵[npc.a_moan+]。",

					"[npc.name]亢奋地将[npc.clit+]深深插入[npc2.namePos][npc2.crotchNipple+]，"
							+ "疯狂地向前挺进[npc.crotchBoobs]，贪婪地干着[npc2.name]，不禁发出[npc.a_moan+]。",

					"[npc.Name]将[npc.clit+]深深插入[npc2.namePos][npc2.crotchNipple+]，开始热切地前后抽插[npc2.crotchBoobs]，不禁发出[npc.a_moan+]，"
							+ "[npc.she]兴奋地干着[npc2.namePos]，呼吸着[npc2.herHim]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "阴蒂操"+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+"(抵抗)";
		}

		@Override
		public String getActionDescription() {
			return "试图把你[npc.clit]从[npc2.namePos][npc2.crotchNipple+]里拔出来。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]尝试将[npc.clit]从[npc2.namePos]的[npc2.crotchNipple]里拔出来，"
										+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就温柔地抓住它，然后轻柔地把它扭回[npc2.namePos][npc2.crotchNipple+]里。",
		
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.clit]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并温柔地把它扭回[npc2.her][npc2.crotchNipple+]里。",
		
								"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.clit]从[npc2.namePos][npc2.crotchNipple+]里拔出来，"
										+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边温柔地将[npc2.crotchNipple+]压向[npc.her][npc.clit+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]尝试将[npc.clit]从[npc2.namePos]的[npc2.crotchNipple]里拔出来，"
										+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就粗暴地抓住它，并蛮横地将它扭回[npc2.namePos][npc2.crotchNipple+]里。",
		
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.clit]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并粗暴地把它扭回[npc2.her][npc2.crotchNipple+]里。",
		
								"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.clit]从[npc2.namePos][npc2.crotchNipple+]里拔出来，"
										+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边粗暴地将[npc2.crotchNipple+]压向[npc.her][npc.clit+]。"));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]尝试将[npc.clit]从[npc2.namePos]的[npc2.crotchNipple]里拔出来，"
										+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就牢牢地抓住它，然后热切地把它扭回[npc2.namePos][npc2.crotchNipple+]里。",
		
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.clit]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并渴望地把它扭回[npc2.her][npc2.crotchNipple+]里。",
		
								"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.clit]从[npc2.namePos][npc2.crotchNipple+]里拔出来，"
										+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边将[npc2.crotchNipple+]压向[npc.her][npc.clit+]。"));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]尝试将[npc.clit]从[npc2.namePos]的[npc2.crotchNipple]里拔出来，"
								+ "但事实证明，[npc.her]的努力是徒劳的，[npc2.name]迅速向前挺进，使其依旧深深插在[npc2.her][npc2.crotchNipple+]里。",

						"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.clit]从[npc2.name]的身体里拔出来，但[npc2.name]马上向前移动，让它继续插入[npc2.her][npc2.crotchNipple+]。",

						"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.clit]从[npc2.namePos][npc2.crotchNipple+]里拔出来，"
								+ "但[npc2.name]一边快速地改变体位，一边让[npc2.crotchNipple+]继续压在[npc.her][npc.clit+]上。"));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止阴蒂操"+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.clit+]从[npc2.namePos][npc2.crotchNipple+]里拔出来，停止操[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将[npc.clit+]从[npc2.namePos][npc2.crotchNipple+]中拉出，"
									+ "霸道地用[npc.clitTip+]最后一次上下磨蹭[npc2.her][npc2.crotchBoobs+]，然后将它移开。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.clit+]从[npc2.her][npc2.crotchNipple+]中猛抽出来，结束了粗暴性交。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]将[npc.clit]滑出[npc2.namePos][npc2.crotchNipple+]，在最后一次用[npc.clitTip]来回磨蹭[npc2.her][npc2.crotchBoobs+]后，将[npc2.her]推开。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.clit+]从[npc2.her][npc2.crotchNipple+]中拔出来，结束了性交。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.Name]从[npc2.her]的[npc2.crotchNipple]里拔出来时，[npc2.name]忍不住漏出一声[npc2.sob+]，"
										+ "[npc2.she]仍然不停地哭泣，无力地继续反抗着[npc.name]。",
	
								"[npc2.name]发出一阵[npc2.a_sob+]，拼命地挣扎着反抗[npc.Name]，将[npc2.crotchNipple+]从[npc.Name]身上抽离，泪水如小溪般从[npc2.face]上流了下来。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.name]将[npc.clit+]拔出[npc2.her][npc2.crotchNipple+]时，[npc2.Name]发出[npc2.a_moan+]，渴求[npc.name]的更多“照顾”。",
	
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]无比渴望得到[npc.namePos]的更多关注。"));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction USING_CLIT_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreastsCrotch()
					&& Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}
		
		@Override
		public String getActionTitle() {
			return "阴蒂操"+Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()));
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.namePos][npc2.clit+]滑进你[npc.crotchNipple+]，开始被操。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.clit]，慢慢地将它引导到自己[npc.crotchBoobs+]，"
									+ "[npc.her]漏出一声小小的[npc.moan]，然后温柔地挺起腹部，强迫[npc2.herHim]插入[npc.her][npc.crotchNipple+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.clit]，把它对准自己[npc.crotchNipple+]，"
									+ "[npc.she]慢慢地将[npc.crotchBoobs]向前推，让[npc2.clit+]插入其中，并不时漏出一声轻柔的[npc.moan]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]抓住[npc2.namePos]的[npc2.clit]，热切地把它拉到自己[npc.crotchBoobs+]，"
									+ "[npc.her]发出一阵[npc.a_moan+]，亢奋地挺起腹部，强迫[npc2.herHim]插入自己[npc.crotchNipple+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.clit]，把它对准自己[npc.crotchNipple+]，"
									+ "热切地将[npc.crotchBoobs]往前推，让[npc2.her][npc2.clit+]插入自己，不禁漏出一声[npc.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.clit]，猛地将其拉至自己[npc.crotchBoobs+]，"
									+ "[npc.her]发出一阵[npc.a_moan+]，暴力地将腹部向前顶，强迫[npc2.herHim]插入自己[npc.crotchNipple+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.clit]，把它对准自己[npc.crotchNipple+]，"
									+ "霸道地将[npc.crotchBoobs]往前推，让[npc2.her][npc2.clit+]插入自己，不禁漏出一阵[npc.a_moan+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.clit]，将它引导到自己[npc.crotchBoobs+]，"
									+ "[npc.her]发出一阵[npc.a_moan+]，然后挺起腹部，强迫[npc2.herHim]插入自己[npc.crotchNipple+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.clit]，把它对准自己[npc.crotchNipple+]，"
									+ "将[npc.crotchBoobs]往前推，让[npc2.her][npc2.clit+]插入自己，不禁漏出一阵[npc.a_moan+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.herHim]的身体，发出一声轻柔的[npc2.moan]，"
										+ "[npc2.she]温柔地将[npc2.clit]向前顶，开始操[npc.namePos][npc.crotchNipple+]。",
	
								"[npc2.name]轻柔地[npc2.moan]，温柔地挺进自己的[npc2.clit]，"
										+ "将它深深插进[npc.namePos][npc.crotchNipple+]，开始操[npc.herHim]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "[npc2.she]热切地将[npc2.clit]向前顶，开始亢奋地操[npc.namePos][npc.crotchNipple+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，热切地插入自己的[npc2.clit]，"
										+ "将它深深插进[npc.namePos][npc.crotchNipple+]，开始竭力地操[npc.herHim]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "为了警告[npc.name]别得意忘形，[npc2.she]粗暴地向前猛撅[npc2.clit]，开始无情地暴操[npc.her][npc.crotchNipple+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，粗暴地猛塞自己的[npc2.clit]，"
										+ "[npc2.she]无情地暴操[npc.namePos][npc.crotchNipple+]，宣告着自己的支配权。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]挺进[npc.name]的身体，不禁漏出一声[npc2.a_moan+]，[npc2.she]将[npc2.clit]向前顶，开始操[npc.namePos][npc.crotchNipple+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，向前挺进[npc2.clit]，将它深深插入[npc.namePos][npc.crotchNipple+]，开始操[npc.herHim]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]紧紧裹住[npc2.name]的[npc2.clit]，[npc2.her]不禁发出一声[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着推开[npc.name]，拼命地想把自己[npc2.clit+]从对方[npc.crotchNipple+]里拔出来。",
	
								"[npc.Name]强行将[npc2.name]的[npc2.clit]深深插入自己[npc.crotchNipple+]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
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
							"[npc2.Name]贪婪地将[npc2.clit+]插入[npc.namePos][npc.crotchBoob+]，"
									+ "[npc2.she]发出[npc2.a_moan+]，亢奋地插入[npc.namePos][npc.crotchNipple+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.clit+]深深插入[npc.namePos][npc.crotchNipple+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，热切地挺动[npc2.her][npc2.clit+]，拼命要插入[npc.namePos][npc.crotchNipple+]最深处。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.clit]从[npc.namePos]的[npc.crotchNipple]中拔出，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.clit+]在自己[npc.crotchNipple+]中继续抽插。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.clit]从[npc.namePos][npc.crotchNipple+]中抽离。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将[npc2.clit+]滑入[npc.namePos][npc.crotchBoob+]深处，"
									+ "[npc2.she]发出柔和的[npc2.moan]，轻柔地抽插[npc.namePos][npc.crotchNipple+]。",
		
							"[npc2.name]慢慢地将[npc2.clit+]深入[npc.namePos][npc.crotchNipple+]，口中飘出一声轻柔的[npc2.moan]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地用[npc2.clit+]深入[npc.namePos][npc.crotchNipple+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.clit+]深深插入[npc.namePos][npc.crotchBoob+]，"
									+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地插入[npc.namePos][npc2.crotchNipple+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地将[npc2.clit+]深深插入[npc.namePos][npc.crotchNipple+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地挺动[npc2.her][npc2.clit+]，拼命要插入[npc.namePos][npc.crotchNipple+]最深处。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.clit+]深深插入[npc.namePos][npc.crotchBoob+]，"
									+ "[npc2.she]发出一阵[npc2.a_moan+]，不停抽插着[npc.namePos][npc.crotchNipple+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.clit+]深深插入[npc.namePos][npc.crotchNipple+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.clit+]深深插入[npc.namePos][npc.crotchNipple+]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_CLIT_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "阴蒂操"+getNippleTitle(Main.sex.getCharacterPerformingAction())+"(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "用你[npc.crotchNipple+]温柔地吞吐[npc2.namePos][npc2.clit+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声轻柔的[npc.moan]，温柔地将自己的腹部向前挺，以便[npc2.namePos][npc2.clit+]可以在自己[npc.crotchNipple+]中插得更深。",

					"[npc.name]发出轻柔的[npc.a_moan+]，温柔地向前挺腹部，强迫[npc2.namePos][npc2.clit+]在自己[npc.crotchNipple+]中插得更深。",

					"[npc.name]慢慢地挺起腹部，"
							+ "[npc.lips+]间飘出一声轻柔的[npc.moan]，[npc.her]设法让[npc2.namePos][npc2.clit+]深深插入[npc.her][npc.crotchNipple+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "阴蒂操"+Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()));
		}

		@Override
		public String getActionDescription() {
			return "用[npc.crotchNipple+]饥渴地吞吐[npc2.namePos][npc2.clit+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，热切地将自己的腹部向前挺，帮着[npc2.namePos][npc2.clit+]可以在自己[npc.crotchNipple+]中插得更深。",

					"[npc.name]发出[npc.a_moan+]，亢奋地向前挺腹，强迫[npc2.namePos][npc2.clit+]在自己[npc.crotchNipple+]中插得更深。",

					"[npc.name]竭力地挺起腹部，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.clit+]插入自己[npc.crotchNipple+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "阴蒂操"+getNippleTitle(Main.sex.getCharacterPerformingAction())+"(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.crotchNipple+]粗暴地吞吐[npc2.namePos][npc2.clit+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，猛烈地将自己的腹部向前挺，以便[npc2.namePos][npc2.clit+]可以在自己[npc.crotchNipple+]中插得更深。",

					"[npc.name]发出[npc.a_moan+]，粗暴地向前拱腹部，强迫[npc2.namePos][npc2.clit+]在自己[npc.crotchNipple+]中插得更深。",

					"[npc.name]激烈地挺起腹部，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]粗暴地设法让[npc2.namePos][npc2.clit+]插入自己[npc.crotchNipple+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_CLIT_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "阴蒂操"+Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()));
		}

		@Override
		public String getActionDescription() {
			return "用[npc.crotchNipple+]吞吐[npc2.namePos][npc2.clit+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，迅速向前挺腹，帮着[npc2.namePos][npc2.clit+]在自己[npc.crotchNipple+]中插得更深。",

					"[npc.name]发出[npc.a_moan+]，向前挺腹部，强迫[npc2.namePos][npc2.clit+]在自己[npc.crotchNipple+]中插得更深。",

					"[npc.name]挺起腹部，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.clit+]插入自己[npc.crotchNipple+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "阴蒂操"+getNippleTitle(Main.sex.getCharacterPerformingAction())+"(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.crotchNipple+]饥渴地吞吐[npc2.namePos][npc2.clit+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，热切地将自己的腹部向前挺，帮着[npc2.namePos][npc2.clit+]可以在自己[npc.crotchNipple+]中插得更深。",

					"[npc.name]发出[npc.a_moan+]，亢奋地向前挺腹，强迫[npc2.namePos][npc2.clit+]在自己[npc.crotchNipple+]中插得更深。",

					"[npc.name]竭力地挺起腹部，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.clit+]插入自己[npc.crotchNipple+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗阴蒂操"+getNippleTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			return "努力从[npc2.namePos][npc2.clit+]拔出[npc.crotchNipple+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
										+ "[npc.she]无力地试着将[npc2.namePos][npc2.clit]从自己[npc.crotchNipple+]里拔出来。",
	
								"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.crotchNipple]从[npc2.namePos]令人憎恶的性器抽离，"
										+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.clit+]依然从容地在[npc.her][npc.crotchBoob+]里滑进滑出。",
	
								"[npc.name]拼命地尝试把[npc.crotchBoobs]挪开，"
										+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.clit+]依然温柔地滑进[npc.her][npc.crotchNipple+]深处。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
										+ "[npc.she]无力地试着将[npc2.namePos][npc2.clit]从自己[npc.crotchNipple+]里拔出来。",
	
								"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.crotchNipple]从[npc2.namePos]令人憎恶的性器抽离，"
										+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.clit+]依然疯狂地在[npc.her][npc.crotchBoob+]里抽送爆操。",
	
								"[npc.name]拼命地尝试把[npc.crotchBoobs]挪开，"
										+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.clit+]依然贪婪地插入[npc.her][npc.crotchNipple+]深处。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
										+ "[npc.she]无力地试着将[npc2.namePos][npc2.clit]从自己[npc.crotchNipple+]里拔出来。",
	
								"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.crotchNipple]从[npc2.namePos]令人憎恶的性器抽离，"
										+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.clit+]依然粗暴地在[npc.her][npc.crotchBoob+]里抽送爆操。",
	
								"[npc.name]拼命地尝试把[npc.crotchBoobs]挪开，"
										+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.clit+]依然激烈地插入[npc.her][npc.crotchNipple+]深处。"));
						break;
					default:
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
								+ "[npc.she]无力地试着将[npc2.namePos][npc2.clit]从自己[npc.crotchNipple+]里拔出来。",

						"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.crotchNipple]从[npc2.namePos]令人憎恶的性器抽离，"
								+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.clit+]依然深深插在[npc.her][npc.crotchBoob+]里。",

						"[npc.name]拼命地尝试把[npc.crotchBoobs]挪开，"
								+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.clit+]依然深深插在[npc.her][npc.crotchNipple+]里。"));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "停止阴蒂操"+getNippleTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.her]的[npc2.clit]从你[npc.crotchNipple+]里拔出来。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]的[npc2.clit]从自己[npc.crotchNipple+]里抽出，[npc.she]愤怒地咆哮着，命令[npc2.name]不准再操了。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]的[npc2.clit]从自己[npc.crotchNipple+]中抽出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]的[npc2.clit]从自己[npc.crotchNipple+]中抽出，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]不要再操了。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc2.her]的[npc2.clit]从自己[npc.crotchNipple+]中抽出。"));
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
								"[npc.Name]不再让[npc2.name]操自己[npc.crotchNipple+]，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
	
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续抽插[npc.namePos][npc.crotchNipple+]的渴望。"));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction NIPPLE_CROTCH_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "收紧" + Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()));
		}

		@Override
		public String getActionDescription() {
			return "用你肌肉发达的[npc.crotchNipple]挤弄包裹着[npc2.namePos]的[npc2.clit]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getNippleCrotchOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]发出一阵[npc.a_moan+]，继续专心用[npc.her]肌肉极致发达的[npc.crotchNipple]挤弄包裹着[npc2.namePos][npc2.clit+]。",

					isTargetedCharacterInanimate()
						?null
						:"[npc.Name]发出一阵[npc.a_moan+]，继续专心控制自己[npc.crotchNipple]内部极致发达的肌肉。"
							+ "[npc.Name]挤弄包裹着[npc2.namePos][npc2.clit+]，让[npc2.herHim]不禁漏出一声愉悦的呻吟。",

					"[npc.Name]以稳定的节奏[npc.moans]着，[npc.she]继续专心"
							+ "用[npc.her]肌肉极致发达且[npc.crotchNipple+]挤弄包裹着[npc2.namePos][npc2.clit+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，专心控制[npc.her]的[npc.crotchNipple]内里极致发达的肌肉，"
							+ "[npc.she]挤弄按摩着[npc2.namePos][npc2.clit+]时，不禁愉悦地尖叫一声。");
		}
	};
}
