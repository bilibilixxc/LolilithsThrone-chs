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
 * @version 0.3.4
 * @author Innoxia
 */
public class FingerAnus {
	
	public static final SexAction ANAL_FINGERING_PROSTATE_MASSAGE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasVagina();
		}
		
		@Override
		public String getActionTitle() {
			return "前列腺按摩";
		}

		@Override
		public String getActionDescription() {
			return "你将[npc.fingers]伸进[npc2.namePos][npc2.asshole+]，开始抚摸起前列腺。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.her]轻柔地将手推向[npc2.namePos][npc2.assCloaca+]，"
								+ "[npc.name]将[npc.fingers]滑入[npc2.her]的[npc2.asshole]深处，然后屈起手指，开始轻柔地爱抚和按摩前列腺。",
							"随着轻柔的[npc.moan]，[npc.name]温柔地将[npc.fingers+]尽可能滑入[npc2.namePos][npc2.asshole+]，"
								+ "然后将它们屈起，开始有节奏地爱抚和按摩前列腺。",
							"[npc.name]慢慢地将[npc.fingers+]尽可能深地插入[npc2.namePos][npc2.asshole+]，"
									+ "[npc.she]卷曲手指，开始温柔地按摩[npc2.namePos]的前列腺，轻轻地发出[npc.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.her]猛地把手插进[npc2.namePos][npc2.assCloaca+]，"
								+ "[npc.name]将[npc.fingers]滑入[npc2.her]的[npc2.asshole]深处，然后屈起手指，开始激烈地爱抚和按摩前列腺。",
							"伴随着[npc.a_moan+]，[npc.name]疯狂地用[npc.fingers+]深入[npc2.namePos][npc2.asshole+]，"
								+ "然后将它们屈起，开始积极地爱抚和按摩前列腺。",
							"[npc.name]强行把[npc.fingers+]深入[npc2.namePos][npc2.asshole+]，"
									+ "[npc.her]卷曲手指，开始粗暴地按摩[npc2.namePos]的前列腺，发出一阵[npc.a_moan+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.her]将手推向[npc2.namePos][npc2.assCloaca+]，"
								+ "[npc.name]将[npc.fingers]滑入[npc2.her]的[npc2.asshole]深处，然后屈起手指，开始爱抚和按摩前列腺。",
							"伴随着[npc.a_moan+]，[npc.name]用[npc.fingers+]尽可能深入[npc2.namePos][npc2.asshole+]，"
								+ "然后将它们屈起，开始爱抚和按摩前列腺。",
							"[npc.name]将[npc.fingers+]尽可能深地伸入[npc2.namePos][npc2.asshole+]，"
									+ "[npc.her]卷曲手指，开始按摩[npc2.namePos]的前列腺，发出一阵[npc.a_moan+]。"));
					break;
				default: // Normal dom and eager sub:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.her]急切地将手推向[npc2.namePos][npc2.assCloaca+]，"
								+ "[npc.name]将[npc.fingers]滑入[npc2.her]的[npc2.asshole]深处，然后屈起手指，开始迅速地爱抚和按摩前列腺。",
							"伴随着[npc.a_moan+]，[npc.name]急切地用[npc.fingers+]尽可能深入[npc2.namePos][npc2.asshole+]，"
								+ "然后将它们屈起，开始急切地爱抚和按摩前列腺。",
							"[npc.name]兴奋地将[npc.fingers+]尽可能深地伸入[npc2.namePos][npc2.asshole+]，"
									+ "[npc.name]卷曲手指，开始大力地按摩[npc2.her]的前列腺，发出[npc.a_moan+]。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]受到刺激，不由得发出[npc2.a_moan+]。寻求更多，[npc2.her]把[npc2.assCloaca+]凑向[npc.namePos]的[npc.hand]。",
								"瞬息之间，[npc2.name]不由自主发出[npc2.a_moan+]，想得到更多，[npc2.her]再次顶腰以迎合[npc.namePos]的触碰。",
								"[npc2.name]发出了[npc2.a_moan+]，温柔地将[npc2.assCloaca+]推向[npc.namePos]的[npc.hand]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]受到刺激，不由得发出[npc2.a_moan+]。寻求更多，[npc2.her]粗暴地把[npc2.assCloaca+]拱向[npc.namePos]的[npc.hand]。",
								"瞬息之间，[npc2.name]不由自主发出[npc2.a_moan+]，[npc2.her]用力地挺动[npc2.assCloaca+]，以迎合[npc.namePos]的触摸。",
								"[npc2.name]发出了[npc2.a_moan+]，使劲将[npc2.assCloaca+]冲向[npc.namePos]的[npc.hand]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]受到突如其来的刺激，不由得发出[npc2.a_moan+]，晃动着[npc2.assCloaca+]迎合[npc.namePos]的[npc.hand]。",
								"瞬息之间，[npc2.name]不由自主发出[npc2.a_moan+]，渴求更多，[npc2.her]扭腰以撞上了[npc.namePos]的触碰。",
								"[npc2.name]发出了[npc2.a_moan+]，将[npc2.assCloaca+]推向[npc.namePos]的[npc.hand]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]受到强加的刺激，不由得发出[npc2.a_moan+]，绝望地尝试将[npc2.assCloaca+]从[npc.namePos]的[npc.hand]边挪开。",
								"瞬息之间，[npc2.name]不情愿地发出[npc2.a_moan+]，尽管身体叫嚣着更多，[npc2.her]绝望地试图把腰从[npc.namePos]的触碰中拉开。",
								"[npc2.name]不情愿地发出了[npc2.a_moan+]，尽管身体叫嚣着更多，[npc2.her]拼命地企图将[npc2.assCloaca+]远离[npc.namePos]的[npc.hand]。"));
						break;
					default: // Normal dom and eager sub:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]受到突如其来的刺激，不由得发出[npc2.a_moan+]，晃动着[npc2.assCloaca+]迎合[npc.namePos]的[npc.hand]。",
								"瞬息之间，[npc2.name]不由自主发出[npc2.a_moan+]，渴求更多，[npc2.her]急切地扭腰，迎向[npc.namePos]的触碰。",
								"[npc2.name]发出了[npc2.a_moan+]，拼命地将[npc2.assCloaca+]推向[npc.namePos]的[npc.hand]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANAL_FINGERING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "指交[npc2.her]的屁股";
		}

		@Override
		public String getActionDescription() {
			return "把[npc.namePos]的[npc.fingers]插进[npc2.namePos][npc2.asshole+]并开始指交[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.fingers+]缓慢地挑逗着[npc2.namePos]的[npc2.assCloaca]，将手指没入[npc2.her][npc2.asshole+]，发出一阵小小地[npc.a_moan+]。",

							
							"[npc.Name]将[npc.her][npc.fingers+]压入[npc2.namePos]肉臀，"
									+ "[npc.she]以缓慢而稳定的力度，轻柔地将指部深深地插入[npc2.namePos][npc2.asshole+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.fingers+]急切地挑逗着[npc2.namePos]的[npc2.assCloaca]，贪婪地将手指没入[npc2.her][npc2.asshole+]，发出一阵[npc.a_moan+]。",

							
							"[npc.Name]将[npc.her][npc.fingers+]急切地压入[npc2.namePos]肉臀，"
									+ "[npc.she]以难以撼动的推力，贪婪地将指部插进[npc2.namePos][npc2.asshole+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]发出[npc.a_moan+]，用[npc.fingers+]粗暴地挤压着[npc2.namePos]的[npc2.assCloaca]，接着用力将手指捅入[npc2.her][npc2.asshole+]。",

							
							"[npc.name]，发出[npc.a_moan+]，用[npc.fingers+]粗暴地挤压着[npc2.namePos]的[npc2.assCloaca]，接着在强烈的饥渴中，贪婪地将手指探向[npc2.her][npc2.asshole+]，深入其中。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.fingers+]急切地挑逗着[npc2.namePos]的[npc2.assCloaca]，贪婪地将手指没入[npc2.her][npc2.asshole+]，发出一阵[npc.a_moan+]。",

							
							"[npc.Name]将[npc.her][npc.fingers+]急切地压入[npc2.namePos]肉臀，"
									+ "[npc.she]以难以撼动的推力，贪婪地将指部插进[npc2.namePos][npc2.asshole+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.fingers+]挑逗着[npc2.namePos]的[npc2.assCloaca]，将手指没入[npc2.her][npc2.asshole+]，发出一阵[npc.a_moan+]。",

							
							"[npc.Name]将[npc.her][npc.fingers+]压入[npc2.namePos]肉臀，然后轻轻一推，就把[npc.her]的手指插入了[npc2.her][npc2.asshole+]。"));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.namePos]的[npc.fingers]进入了自己的身体，不禁漏出轻柔的[npc2.a_moan+]，"
										+ "然后在[npc2.name]的帮助下，轻轻地把[npc2.her][npc2.assCloaca]引向[npc.namePos]的[npc.hand]"
										+ "[npc.her]的[npc.fingers]进一步深入[npc2.her][npc2.asshole+]。",
	
	
								"[npc2.name]轻柔地[npc2.moan]，开始温柔地将[npc2.assCloaca+]压向[npc.namePos]的[npc.hand]，"
										+ "鼓励[npc.name]将[npc.fingers]更深地插入[npc2.her][npc2.asshole+]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.namePos]的[npc.fingers]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+"在[npc2.her]热切地迎合下，急切地将[npc2.her][npc2.assCloaca]引向自己的[npc.hand]"
										+ "[npc.her]的[npc.fingers]进一步深入[npc2.her][npc2.asshole+]。",
	
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地将[npc2.assCloaca]压向[npc.namePos]的[npc.hand]，"
										+ "饥渴地鼓励[npc.Name]将[npc.fingers]更深地插入[npc2.her][npc2.asshole+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.namePos]的[npc.fingers]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+"然后激烈地将[npc2.assCloaca]压向[npc.her]的[npc.hand]，粗暴地让[npc.namePos]的[npc.fingers]更加深入自己[npc2.asshole+]。",
	
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始猛烈地将[npc2.assCloaca]撞向[npc.namePos]的[npc.hand]，"
										+ "粗暴地强迫[npc.name]将[npc.fingers]更深地插入[npc2.her][npc2.asshole+]。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.namePos]的[npc.fingers]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+"在[npc2.her]热切地迎合下，急切地将[npc2.her][npc2.assCloaca]引向自己的[npc.hand]"
										+ "[npc.her]的[npc.fingers]进一步深入[npc2.her][npc2.asshole+]。",
	
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地将[npc2.assCloaca]压向[npc.namePos]的[npc.hand]，"
										+ "饥渴地鼓励[npc.Name]将[npc.fingers]更深地插入[npc2.her][npc2.asshole+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.namePos]的[npc.fingers]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+"然后将[npc2.assCloaca]压向[npc.her]的[npc.hand]，让[npc.namePos]的[npc.fingers]更加深入自己[npc2.asshole+]。",
	
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始将[npc2.assCloaca]压向[npc.namePos]的[npc.hand]，"
										+ "鼓励[npc.name]将[npc.fingers]更深地插入[npc2.her][npc2.asshole+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.namePos]的[npc.fingers]进入了自己的身体，不禁漏出一声[npc2.a_sob+]，"
										+"[npc2.she]徒劳地挣扎扭身，试图拔出[npc2.assCloaca]中讨厌的插入物，不断[npc2.sobbing]着反抗。",
	
	
								"[npc2.name]发出[npc2.a_sob+]，徒劳地挣扎扭身，试图拔出讨厌的插入物，"
										+ "[npc2.sobbing]并挣扎抵抗着[npc.name]，因为[npc.her]正试图将[npc.fingers]深入自己[npc2.asshole+]。"));
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
							"[npc2.name]急切地将[npc2.assCloaca]撞向[npc.namePos]的[npc.hand]，"
									+"[npc2.she]发出一声愉悦的[npc2.moan]，热切地乞求着[npc.Name]继续指交[npc2.her][npc2.asshole+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一声愉悦的[npc2.moan]，"
									+ "[npc2.name]急切地将[npc2.assCloaca]向后推，让[npc.name]触摸，乞求[npc.Name]继续指交[npc2.her][npc2.asshole+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地用[npc2.assCloaca]磨蹭[npc.namePos]的[npc.hand]，"
									+ "做出有助于[npc.fingers]更深地插入[npc2.her][npc2.ass+]的动作，急切地乞求[npc.Name]继续。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.ass+]从[npc.namePos]那讨厌的触摸下缩回，"
									+ "[npc2.name]无力地反抗着[npc.namePos]插进来的[npc.fingers]，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然继续指交[npc2.her][npc2.asshole+]。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试不让[npc.name]触碰自己，"
									+ "但[npc.name]还是将[npc.fingers+]插入了[npc2.her][npc2.ass+]，[npc2.she]奋力反抗着[npc.name]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.name]缓慢地将[npc2.assCloaca]撞向[npc.namePos]的[npc.hand]，"
								+ "[npc2.she]发出一声轻柔的[npc2.moan]，开始温柔地乞求[npc.name]继续指交[npc2.her][npc2.asshole+]。",
	
						" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
								+ "[npc2.she]温柔地将[npc2.hips]压向[npc.NamePos]的[npc.hand]，乞求[npc.Name]继续指交[npc2.her][npc2.asshole+]。",
	
						"[npc2.name]愉悦地[npc2.moaning]着，缓缓地用[npc2.assCloaca]磨蹭[npc.namePos]的[npc.hand]，"
								+ "做出有助于[npc.fingers]更深地插入[npc2.her][npc2.ass+]的动作，柔和地[npc2.moaning]着让[npc.Name]继续。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]粗暴地将[npc2.assCloaca]撞向[npc.namePos]的[npc.hand]，"
									+ "[npc2.she]粗鲁地命令[npc.name]继续指交[npc2.her][npc2.asshole+]，发出一阵[npc2.a_moan+]。",
	
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]粗鲁地将[npc2.hips]压向[npc.NamePos]的[npc.hand]，专横地命令[npc.Name]继续指交[npc2.her][npc2.asshole+]。",
	
							"[npc2.Name]粗暴地用[npc2.assCloaca]顶着[npc.namePos]的[npc.hand]，"
									+ "强迫[npc.name]将[npc.fingers]深深插入[npc2.her][npc2.ass+]，命令[npc.name]继续。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]将[npc2.assCloaca]撞向[npc.namePos]的[npc.hand]，"
									+ "[npc2.she]乞求[npc.name]继续指交[npc2.her][npc2.asshole+]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+"接着，渴求更多的触碰，[npc2.name]扭动着腰，祈求[npc.name]继续用手指玩弄[npc2.asshole+]。",
		
							"[npc2.name][npc2.moaning+]着，用[npc2.assCloaca]磨蹭[npc.namePos]的[npc.hand]，"
									+ "做出有助于[npc.fingers]更深地插入[npc2.her][npc2.ass+]的动作，乞求[npc.Name]继续。"));
					break;
			}
		}
		return "";
	}
	
	
	public static final SexAction ANAL_FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "指交肛门(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地指交[npc2.namePos][npc2.asshole+]。";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]温柔地将[npc.fingers+]插入[npc2.namePos][npc2.asshole+]，[npc.she]弯起手指按摩内壁，缓缓地指交[npc2.namePos]的[npc2.ass]。",

					"[npc.name]缓缓地将[npc.fingers+]插入[npc2.namePos][npc2.asshole+]，"
							+ "[npc.she]温柔地进出抽插，柔和地指交[npc2.namePos]的[npc2.ass]，发出一阵[npc.a_moan+]。",

					"[npc.name]轻声[npc.moan]，把[npc.hand]贴紧[npc2.namePos]的[npc2.ass]，轻轻地把[npc.fingers+]不断探入[npc2.her][npc2.asshole+]内。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "指交肛门";
		}
		@Override
		public String getActionDescription() {
			return "继续指交[npc2.namePos][npc2.asshole+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.fingers+]深深插入[npc2.namePos][npc2.asshole+]，然后将指部弯起，"
							+ "亢奋地按摩内壁，[npc.she]饥渴地指交[npc2.namePos]的[npc2.ass]。",

					"[npc.name]坚定地将[npc.fingers+]插入[npc2.namePos][npc2.asshole+]，"
							+ "[npc.she]开始迅速地进出抽插，急切地指交[npc2.namePos]的[npc2.ass]，发出一阵[npc.a_moan+]。",

					"[npc.name]放出[npc.moan]，把[npc.hand]贴紧[npc2.namePos]的[npc2.ass]，亢奋地把[npc.fingers+]不断挤入[npc2.her][npc2.asshole+]内。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "指交肛门(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地指交[npc2.namePos][npc2.asshole+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]贪婪地将[npc.fingers+]深深插入[npc2.namePos][npc2.asshole+]，然后将指部弯起，"
							+ "粗暴地开始按摩内壁，[npc.she]激烈地指交着[npc2.namePos]的[npc2.ass]。",

					"[npc.name]粗暴地将[npc.fingers+]插入[npc2.namePos][npc2.asshole+]，"
							+ "[npc.she]开始猛烈地进出抽插，激烈地指交[npc2.namePos]的[npc2.ass]，发出一阵[npc.a_moan+]。",

					"[npc.name][npc.moan]出声，把[npc.hand]用力贴紧[npc2.namePos]的[npc2.ass]，粗暴地把[npc.fingers+]不断捅入[npc2.her][npc2.asshole+]内。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ANAL_FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "指交肛门";
		}

		@Override
		public String getActionDescription() {
			return "继续指交[npc2.namePos][npc2.asshole+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.fingers+]深深插入[npc2.namePos][npc2.asshole+]，然后将指部弯起，"
							+ "开始按摩内壁，[npc.she]指交着[npc2.namePos]的[npc2.ass]。",

					"[npc.name]将[npc.fingers+]插入[npc2.namePos][npc2.asshole+]，"
							+ "[npc.she]开始进出抽插，指交[npc2.namePos]的[npc2.ass]，发出一阵[npc.a_moan+]。",

					"[npc.name]发出[npc.moan]，把[npc.hand]贴紧[npc2.namePos]的[npc2.ass]，把[npc.fingers+]不断探入[npc2.her][npc2.asshole+]内。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ANAL_FINGERING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "指交肛门(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地指交[npc2.namePos][npc2.asshole+]。";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.fingers+]深深插入[npc2.namePos][npc2.asshole+]，然后将指部弯起，"
							+ "亢奋地按摩内壁，[npc.she]饥渴地指交[npc2.namePos]的[npc2.ass]。",

					"[npc.name]坚定地将[npc.fingers+]插入[npc2.namePos][npc2.asshole+]，"
							+ "[npc.she]开始迅速地进出抽插，急切地指交[npc2.namePos]的[npc2.ass]，发出一阵[npc.a_moan+]。",

					"[npc.name]放出[npc.moan]，急切地把[npc.hand]贴紧[npc2.namePos]的[npc2.ass]，亢奋地把[npc.fingers+]不断挤入[npc2.her][npc2.asshole+]内。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANAL_FINGERING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "抵抗指交肛门";
		}
		@Override
		public String getActionDescription() {
			return "尝试把你的[npc.fingers]从[npc2.namePos][npc2.asshole+]中拔出。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
							+ "[npc.she]发出一阵[npc.a_sob+]，拼命地尝试将自己[npc.fingers+]从[npc2.namePos][npc2.asshole+]中抽离。",

					"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将自己[npc.fingers+]从[npc2.namePos][npc2.asshole+]中抽离。",

					"[npc.name]拼命地尝试把自己[npc.fingers+]从[npc2.namePos][npc2.asshole+]中抽离，"
							+"[npc.name]痛哭出声，没能把手指抽出[npc2.her]的[npc2.assCloaca+]。"));
			
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_FINGERING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止指交肛门";
		}
		@Override
		public String getActionDescription() {
			return "把你的[npc.fingers]从[npc2.namePos][npc2.asshole+]拔出去，停止指交[npc2.herHim]。";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将[npc.fingers]从[npc2.namePos][npc2.asshole+]中拉出，迅速把[npc.hand]抽离[npc2.namePos]的[npc2.assCloaca]。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.fingers]从[npc2.her][npc2.asshole+]中猛抽出来，结束了粗暴指交。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.fingers]从[npc2.namePos][npc2.asshole+]中抽出，迅速把[npc.hand]抽离[npc2.namePos]的[npc2.assCloaca]。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.fingers]从[npc2.her][npc2.asshole+]中拔出来，结束了指交。"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]从[npc2.namePos]的[npc2.asshole]中抽出手指，而[npc2.name]喘气连连，一边发出[npc2.a_sob+]一边持续挣扎着。",


							"[npc.Name]强行从[npc.asshole+]抽出手指，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.fingers+]从[npc2.namePos][npc2.asshole+]里拔出，[npc2.Name]发出一阵[npc2.a_moan+]。",


							"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]渴望得到[npc.namePos]的更多关注。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_FINGERED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "开始被指交肛门";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]开始指交你[npc.assCloaca+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]温柔但牢固地抓住[npc2.namePos]的[npc2.hand]，将[npc2.namePos]那[npc2.fingers]引导到自己的[npc.assCloaca]，"
									+ "发出轻柔的[npc.a_moan+]，将[npc2.namePos]的指部插入[npc.her][npc.asshole+]。",
							
							"在抓住[npc2.namePos]的[npc2.hands]后, [npc.Name]引导着将[npc2.her]那[npc2.fingers]滑向[npc.her]的臀瓣之间，并缓慢地摩擦了起来，"
									+ "[npc.she]温柔地将[npc2.namePos]的指部推向[npc.her][npc.asshole+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢抓住[npc2.namePos]的[npc2.hand]，急切地将[npc2.namePos]那[npc2.fingers]引导到自己的[npc.assCloaca]，"
									+ "发出[npc.a_moan+]，贪婪地将[npc2.namePos]的指部插入[npc.her][npc.asshole+]。",

							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，急切地引导[npc2.her]的[npc2.fingers]滑向自己的臀瓣之间，并缓慢地摩擦了起来，"
									+ "[npc.she]贪婪地将[npc2.namePos]的指部推向[npc.her][npc.asshole+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢钳制住[npc2.namePos]的[npc2.hand]，用[npc2.namePos]那[npc2.fingers]磨蹭自己的[npc.assCloaca]，"
									+ "发出[npc.a_moan+]，粗暴地将[npc2.namePos]的指部插入[npc.her][npc.asshole+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.hand]，激烈地将[npc2.namePos]那[npc2.fingers]拉向[npc.her]肉臀间，然后霸道地猛推，"
									+ "[npc.she]粗暴地将[npc2.namePos]的指部插进[npc.her][npc.asshole+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢抓住[npc2.namePos]的[npc2.hand]，急切地将[npc2.namePos]那[npc2.fingers]引导到自己的[npc.assCloaca]，"
									+ "发出[npc.a_moan+]，贪婪地将[npc2.namePos]的指部插入[npc.her][npc.asshole+]。",

							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，急切地引导[npc2.namePos]的[npc2.fingers]滑向自己的臀瓣之间，并缓慢地摩擦了起来，"
									+ "[npc.she]贪婪地将[npc2.namePos]的指部推向[npc.her][npc.asshole+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢抓住[npc2.namePos]的[npc2.hand]，将[npc2.namePos]那[npc2.fingers]引导到自己的[npc.assCloaca]，"
									+ "发出[npc.a_moan+]，将[npc2.namePos]的指部插入[npc.her][npc.asshole+]。",

							"[npc.Name]抓住[npc2.namePos]的[npc2.hand]，引导[npc2.namePos]的[npc2.fingers]滑向自己的臀瓣之间，并缓慢地摩擦了起来，"
									+ "[npc.she]将[npc2.namePos]的指部推向[npc.her][npc.asshole+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.name]的身体，不禁发出轻柔的[npc2.moan]，然后弯起[npc2.fingers]，开始温柔地指交[npc.her][npc.asshole+]。",
	
								"[npc2.name]轻柔地[npc2.moan]着，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]温柔地将[npc2.hand]压进[npc.NamePos]的[npc.assCloaca]，开始指交[npc.NamePos][npc.asshole+]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.name]的身体，不禁发出一阵[npc2.a_moan+]，然后弯起[npc2.fingers]，开始急切地指交[npc.her][npc.asshole+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]急切地将[npc2.hand]压向[npc.NamePos]的[npc.assCloaca]，开始指交[npc.NamePos][npc.asshole+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.Name]的身体，忘我地发出一阵[npc2.a_moan+]，为了提醒[npc.Name]谁才是主导者，"
										+ "[npc2.she]粗暴地弯起[npc2.fingers]，开始无情地指交[npc.namePos][npc.asshole+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]无情地指交[npc.namePos][npc.asshole+]，宣告着自己的支配权。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.name]的身体，不禁发出一阵[npc2.a_moan+]，然后弯起[npc2.fingers]，开始急切地指交[npc.her][npc.asshole+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]急切地将[npc2.hand]压向[npc.NamePos]的[npc.assCloaca]，开始指交[npc.NamePos][npc.asshole+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.name]的身体，不禁发出一阵[npc2.a_moan+]，然后弯起[npc2.fingers]，开始指交[npc.her][npc.asshole+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]将[npc2.hand]压向[npc.NamePos]的[npc.assCloaca]，开始指交[npc.NamePos][npc.asshole+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]紧紧裹住[npc2.name]那[npc2.fingers]，[npc2.her]不禁发出一声[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着，想要从[npc.namePos]的紧握中抽出自己的[npc2.hand]。",
	
								"[npc2.name]发出[npc2.a_sob+]，挣扎着从[npc.namePos]的紧握中抽出手，"
										+ "[npc2.her]请求[npc.Name]不要再强迫自己把手指深入[npc.her][npc.asshole+]内。"));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "被指交肛门(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地享受[npc2.namePos][npc2.fingers+]指交你[npc.asshole+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]温柔地将[npc.assCloaca]向后压向[npc2.namePos]的[npc2.hand]，"
							+ "让[npc2.namePos][npc2.fingers+]深深插入自己[npc.asshole+]，发出一阵轻柔地[npc.moan]。",

					"伴随着一声轻柔的[npc.moan]，[npc.Name]温柔地将[npc.assCloaca]扭向[npc2.namePos]的[npc2.hand]，迫使[npc2.namePos][npc2.fingers+]更深地插入[npc.her][npc.asshole+]。",

					"[npc.name]缓慢地将[npc.assCloaca]向后顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间飘出一声轻柔的[npc.moan]，[npc.her]设法让[npc2.namePos][npc2.fingers+]深深插入[npc.her][npc.asshole+]。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "享受指交";
		}

		@Override
		public String getActionDescription() {
			return "享受[npc2.namePos][npc2.fingers+]指交你[npc.asshole+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.assCloaca]向后压向[npc2.namePos]的[npc2.hand]，"
							+ "让[npc2.namePos][npc2.fingers+]深深插入自己[npc.asshole+]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，竭力地将[npc.assCloaca]扭向[npc2.namePos]的[npc2.hand]，迫使[npc2.namePos][npc2.fingers+]更深地插入[npc.her][npc.asshole+]。",

					"[npc.name]亢奋地将[npc.assCloaca]向后顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.fingers+]插入自己[npc.asshole+]深处。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "被指交肛门(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地强迫[npc2.namePos][npc2.fingers+]深入你[npc.asshole+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]粗暴地把[npc.assCloaca]撞向[npc2.namePos]的[npc2.hand]，"
							+ "强迫[npc2.namePos][npc2.fingers+]粗暴地深深插入自己[npc.asshole+]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，强有力地将[npc.assCloaca]扭向[npc2.namePos]的[npc2.hand]，强迫[npc2.namePos][npc2.fingers+]在自己[npc.asshole+]中插得更深。",

					"[npc.name]将[npc.assCloaca]粗暴地向后顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.fingers+]有力地插入自己[npc.asshole+]深处。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "享受指交肛门";
		}

		@Override
		public String getActionDescription() {
			return "享受[npc2.namePos][npc2.fingers+]指交[npc.her][npc.asshole+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.assCloaca]向后压向[npc2.namePos]的[npc2.hand]，"
							+ "让[npc2.namePos][npc2.fingers+]深深插入自己[npc.asshole+]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，将[npc.assCloaca]扭向[npc2.namePos]的[npc2.hand]，强迫[npc2.namePos][npc2.fingers+]在自己[npc.asshole+]中插得更深。",

					"[npc.name]将[npc.assCloaca]向后顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.fingers+]插入自己[npc.asshole+]深处。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "被指交肛门(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地将你[npc.assCloaca+]压向[npc2.namePos]的[npc2.hand]，让[npc2.she]指交你[npc.asshole+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.assCloaca]向后压向[npc2.namePos]的[npc2.hand]，"
							+ "让[npc2.namePos][npc2.fingers+]深深插入自己[npc.asshole+]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，竭力地将[npc.assCloaca]扭向[npc2.namePos]的[npc2.hand]，迫使[npc2.namePos][npc2.fingers+]更深地插入[npc.her][npc.asshole+]。",

					"[npc.name]亢奋地将[npc.assCloaca]向后顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.fingers+]插入自己[npc.asshole+]深处。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANAL_FINGERED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗指交肛门";
		}

		@Override
		public String getActionDescription() {
			return "尝试把[npc2.namePos]的[npc.fingers]从你[npc.asshole+]中拔出。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
									+ "[npc.she]发出一阵[npc.a_sob+]，虚弱地尝试着，将[npc2.namePos]轻轻抽动的[npc2.fingers+]从自己[npc.asshole+]中抽出。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.assCloaca]从[npc2.namePos]令人憎恶的抚摸下抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.fingers+]依然温柔地在[npc.her][npc.asshole+]里滑进滑出。",

							"[npc.name]拼命地尝试把[npc.assCloaca]从[npc2.namePos]的[npc2.hand]边挪开，"
									+ "[npc2.namePos][npc2.fingers+]依然温柔地滑进[npc.Name][npc.asshole+]深处，使得[npc.Name]难以抑制地[npc.sob]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
									+ "[npc.she]发出一阵[npc.a_sob+]，虚弱地尝试着，将[npc2.namePos]贪婪饥渴的[npc2.fingers]从自己[npc.asshole+]中轻轻抽出。",


							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.assCloaca]从[npc2.namePos]令人憎恶的抚摸下抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.fingers+]依然急切地在[npc.her][npc.asshole+]里滑进滑出。",


							"[npc.name]拼命地尝试把[npc.assCloaca]从[npc2.namePos]的[npc2.hand]边挪开，"
									+ "[npc2.namePos][npc2.fingers+]依然饥渴地插入[npc.Name][npc.asshole+]深处，使得[npc.Name]难以抑制地[npc.sob]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
									+ "[npc.she]发出一阵[npc.a_sob+]，虚弱地尝试着，将[npc2.namePos]粗暴抽插的[npc2.fingers]从自己[npc.asshole+]中轻轻抽出。",


							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.assCloaca]从[npc2.namePos]令人憎恶的抚摸下抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.fingers+]依然粗暴地在[npc.her][npc.asshole+]里抽送爆操。",


							"[npc.name]拼命地尝试把[npc.assCloaca]从[npc2.namePos]的[npc2.hand]边挪开，"
									+ "[npc2.namePos][npc2.fingers+]依然粗暴撞进[npc.Name][npc.asshole+]深处，使得[npc.Name]难以抑制地[npc.sob]。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANAL_FINGERED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止被指交肛门";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.her]那[npc2.fingers]从你[npc.asshole+]里拔出来。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]那[npc2.fingers]从自己[npc.asshole+]里抽出，[npc.she]愤怒地咆哮着，命令[npc2.name]停止指交。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]那[npc2.fingers]从自己[npc.asshole+]中抽出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]那[npc2.fingers]从自己[npc.asshole+]里抽出，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]停止指交。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc2.her]那[npc2.fingers]从自己[npc.asshole+]中滑出。"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]松了一口气，但当[npc2.she]意识到[npc.name]还没完全满足时，又发出了一阵[npc2.a_sob+]。",

							"[npc2.name]发出一阵[npc2.a_sob+]，继续反抗并挣扎着，但[npc.name]依然牢牢地将[npc2.she]固定在原位。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]不再让[npc2.name]玩弄自己[npc.asshole+]，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",

							"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]想更多关注[npc.namePos][npc.asshole+]的渴望。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
