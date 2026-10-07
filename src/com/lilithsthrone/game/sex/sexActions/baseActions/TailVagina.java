package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.fetishes.Fetish;
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
 * @version 0.4
 * @author Innoxia
 */
public class TailVagina {
	
	public static final SexAction TEASE_TAIL_OVER_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "用尾巴挑逗(小穴)";
		}

		@Override
		public String getActionDescription() {
			return "将你的[npc.tail(true)]顶在[npc2.namePos][npc2.vagina+]上滑动。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.tail+(true)]蜿蜒到[npc2.namePos][npc2.pussy+]，"
									+ "开始缓慢地用[npc.tailTip+]上下挑逗[npc2.her][npc2.labia+]，随时准备插入[npc2.herHim]。",

							"[npc.name]轻声[npc.moan]，把自己[npc.tail+(true)]伸向[npc2.namePos][npc2.pussy+]，温柔地用[npc.tailTip]来回磨蹭[npc2.her][npc2.labia+]。",

							"[npc.name]温柔地用[npc.tail(true)][npc.tailTip+]上下挑逗[npc2.namePos][npc2.vagina+]，"
									+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了轻柔的[npc.moan]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.tail+(true)]蜿蜒到[npc2.namePos][npc2.pussy+]，"
									+ "开始急切地用[npc.tailTip+]上下挑逗[npc2.her][npc2.labia+]，随时准备插入[npc2.herHim]。",

							"[npc.name]发出[npc.a_moan+]，把自己[npc.tail+(true)]移向[npc2.namePos][npc2.pussy+]，急切地用[npc.tentacleTip]来回磨蹭[npc2.her][npc2.labia+]。",

							"[npc.name]急切地用[npc.tail(true)][npc.tailTip+]上下挑逗[npc2.namePos][npc2.vagina+]，"
									+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了一阵[npc.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tail+(true)]磨蹭着[npc2.namePos][npc2.pussy+]，"
									+ "接着向后退了一点，开始用[npc.tailTip+]上下挑逗[npc2.her][npc2.labia+]，随时准备插入[npc2.herHim]。",

							"[npc.name]发出一阵[npc.a_moan+]，把[npc.tail+(true)]挪向[npc2.namePos][npc2.pussy+]，"
									+ "然后开始粗暴地用[npc.tailTip]上下磨蹭[npc2.namePos][npc2.labia+]。",

							"[npc.name]用[npc.tail(true)][npc.tailTip+]粗暴地来回磨蹭[npc2.namePos][npc2.vagina+]，"
									+ "一想到只要[npc.she]高兴随时可以操干[npc2.name]，[npc.name]就发出了一阵[npc.a_moan+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.tail+(true)]蜿蜒到[npc2.namePos][npc2.pussy+]，"
									+ "开始用[npc.tailTip+]上下挑逗[npc2.her][npc2.labia+]，随时准备插入[npc2.herHim]。",

							"[npc.name]发出[npc.a_moan+]，把自己[npc.tail+(true)]移向[npc2.namePos][npc2.pussy+]，急切地用[npc.tailTip]来回磨蹭[npc2.her][npc2.labia+]。",

							"[npc.name]用[npc.tail(true)][npc.tailTip+]磨蹭着[npc2.namePos][npc2.vagina+]，"
									+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了一阵[npc.a_moan+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				if((Main.sex.getCharacterTargetedForSexAction(this).isVaginaVirgin() || Main.sex.getCharacterTargetedForSexAction(this).hasHymen()) && Main.sex.getCharacterTargetedForSexAction(this).hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
						switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
							case SUB_RESISTING:
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"一想到自己即将要被夺走贞操，[npc2.namePos][npc2.lips+]间不禁爆发出一阵[npc2.A_sob+]。",
										"[npc2.Name]意识到自己可能将失去贞操，不禁发出一声绝望的[npc2.sob]。",
										"一想到自己马上要被[npc.namePos][npc.tail+]夺走贞操，[npc2.name]就不禁绝望地[npc2.sobVerb]着。"));
								break;
							default:
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.name]把贞洁看得比什么都重，[npc2.she]将[npc2.pussy]推离[npc.namePos][npc.tail+]。",
										"[npc2.name]因将要失去宝贵的贞洁而恐惧不已，[npc2.she]将[npc2.pussy]推离[npc.namePos][npc.tail+]。",
										"[npc2.name]躲开了[npc.namePos]的触摸，将[npc2.pussy]推离[npc.namePos][npc.tail+]，以此来明确[npc2.she]还不想失去贞洁。"));
								break;
						}
						
					} else {
						switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
							case SUB_RESISTING:
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.namePos]想到接下来要发生的事，[npc2.lips+]间不禁爆发出一阵[npc2.A_sob+]，[npc2.speech(不！快停下！我还是处！)]",
										"[npc2.Name]发出一声绝望的[npc2.sob]，苦苦乞求，[npc2.speech(不要这样！求你了！我还是处！)]",
										"[npc2.Name]一想到接下来要发生的事情，不禁痛苦地[npc2.sobsVerb]，绝望地哀求着，[npc2.speech(不要！快停手！我不想被夺走第一次！)]"));
								break;
							default:
								if(Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))) {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											"[npc2.name]把贞洁看得比什么都重，命令道，[npc2.speech(别继续了！我还不想失去第一次！)]",
											"[npc2.name]因将要失去宝贵的贞洁而恐惧不已，声嘶力竭道，[npc2.speech(不要这样！我不要像这样失去第一次！)]",
											"[npc2.name]躲开了[npc.namePos]的触摸，警告道，[npc2.speech(不准继续了！我还不想失去第一次！)]"));
								} else {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											"[npc2.name]把贞洁看得比什么都重，疯狂地哀求道，[npc2.speech(别继续了！求求你，我还不想失去第一次！)]",
											"[npc2.name]因将要失去宝贵的贞洁而恐惧不已，绝望地哭了出来，[npc2.speech(快停手！别再做了！我不想被夺走第一次！)]",
											"[npc2.name]躲开了[npc.namePos]的触摸，绝望地哀求道，[npc2.speech(不要！快住手！我不想被夺走第一次！)]"));
								}
								break;
						}
					}
					
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos]感觉到[npc2.vagina+]传来的阵阵快感，一声轻柔的[npc2.moan]从[npc2.her][npc2.lips+]间飘出，"
											+ "然后[npc2.she]轻轻地挺起[npc2.her][npc2.vagina+]顶着[npc.namePos]的[npc.tail(true)]。",
		
									"[npc2.name]发出一声柔和的[npc2.moan]，随后温柔地将[npc2.vagina+]贴向[npc.namePos]的[npc.tail(true)]。",
		
									"[npc2.name]感受到[npc.namePos]的[npc.tail(true)]正刺激着[npc2.her][npc.vagina+]，[npc2.she]愉悦地[npc2.moanVerb]着，并温柔地扭动[npc2.her][npc2.hips+]作为回应。"));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]感受着[npc2.vagina+]传来的阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间爆发而出，"
											+"[npc2.she]饥渴地将[npc2.labia+]推向[npc.namePos]的[npc.tail(true)]。",
		
									"[npc2.name]发出一声迷乱的[npc2.moan]，然后迫不及待地将[npc.namePos]的[npc.tail(true)]塞入自己[npc2.vagina+]。",
		
									"[npc2.name]感受到[npc.namePos]的[npc.tail(true)]正刺激着[npc2.her][npc.vagina+]，[npc2.she]愉悦地[npc2.moanVerb]着，并饥渴地扭动[npc2.her][npc2.hips+]作为回应。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]感受着[npc2.vagina+]传来的阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间爆发而出，"
											+"[npc2.she]粗暴地用[npc2.labia+]抵住[npc.namePos]的[npc.tail(true)]。",
		
									"[npc2.name]发出一声迷乱的[npc2.moan]，然后粗暴地将[npc.namePos]的[npc.tail(true)]塞入自己[npc2.vagina+]。",
		
									"[npc2.name]感受到[npc.namePos]的[npc.tail(true)]正刺激着[npc2.her][npc.vagina+]，[npc2.she]愉悦地[npc2.moanVerb]着，并粗暴地扭动[npc2.her][npc2.hips+]作为回应。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]感受着[npc2.vagina+]传来的阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间爆发而出，"
											+"[npc2.she]将[npc2.labia+]推向[npc.namePos]的[npc.tail(true)]。",
		
									"[npc2.name]发出一声[npc2.moan]，然后将[npc2.her][npc2.vagina+]推向[npc.namePos]的[npc.tail(true)]。",
		
									"[npc2.name]感受到[npc.namePos]的[npc.tail(true)]正刺激着[npc2.her][npc.vagina+]，[npc2.she]愉悦地[npc2.moanVerb]着，并扭动[npc2.her][npc2.hips+]作为回应。"));
							break;
						case SUB_RESISTING:
							if(Main.sex.getCharacterTargetedForSexAction(this).isVaginaVirgin()) {
								if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											"一想到自己即将要被夺走贞操，[npc2.namePos][npc2.lips+]间不禁爆发出一阵[npc2.A_sob+]。",
											"[npc2.Name]意识到自己可能将失去贞操，不禁发出一声绝望的[npc2.sob]。",
											"一想到自己马上要被[npc.namePos][npc.tail+]夺走贞操，[npc2.name]就不禁绝望地[npc2.sobVerb]着。"));
									
								} else {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											"[npc2.namePos]想到接下来要发生的事，[npc2.lips+]间不禁爆发出一阵[npc2.A_sob+]，[npc2.speech(不！别这么做！我还是处！)]",
											"[npc2.Name]发出一声绝望的[npc2.sob]，苦苦乞求，[npc2.speech(不要这样！求你了！我还是处！)]",
											"[npc2.Name]一想到接下来要发生的事情，不禁痛苦地[npc2.sobsVerb]，绝望地哀求着，[npc2.speech(不要！快停手！我不想被夺走第一次！)]"));
								}
								
							} else {
								if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.pussy]从[npc.namePos]的[npc.tail(true)]抽离。",
											"[npc2.Name]不断反抗着[npc.Name]，不禁发出绝望的[npc2.sob]。",
											"[npc2.name]痛苦地[npc2.sobVerb]着，试图远离[npc.namePos][npc.tail+]。"));
									
								} else {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.pussy]从[npc.namePos]的[npc.tail(true)]抽离，"
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
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
	};
	
	public static final SexAction FORCE_TAIL_OVER_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "挑逗[npc2.her]的尾巴";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.namePos][npc2.tail+(true)]在你[npc.vagina+]口滑动。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.tail+(true)]，把它挪到自己[npc.pussy+]。"
									+ "[npc.she]缓慢地用[npc2.tailTip+]上下磨蹭[npc.her][npc.labia+]，挑逗[npc2.name]随时可能插入。",

							"[npc.name]轻声[npc.moan]，抓住[npc2.namePos][npc2.tail+(true)]，将它移向自己[npc.pussy+]，"
									+ "然后开始温柔地用[npc2.tailTip]上下磨蹭[npc.her][npc.labia+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.tail+(true)]，温柔地用[npc2.tailTip+]磨蹭自己[npc.vagina+]，"
									+ "[npc.she]发出一声轻柔的[npc.moan]，挑逗[npc2.Name]随时可能插入。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.tail+(true)]，把它挪到自己[npc.pussy+]。"
									+ "[npc.she]急切地用[npc2.tailTip+]上下磨蹭[npc.her][npc.labia+]，挑逗[npc2.name]随时可能插入。",

							"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.tail+(true)]并引导它到自己[npc.pussy+]，"
									+ "然后开始急切地用[npc2.tailTip]上下磨蹭[npc.her][npc.labia+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.tail+(true)]，急切地用[npc2.tailTip+]磨蹭自己[npc.vagina+]，"
									+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.tail+(true)]，把它猛拉到自己[npc.pussy+]。"
									+ "[npc.she]粗暴地强迫[npc2.tailTip+]上下磨蹭[npc.her][npc.labia+]，挑逗[npc2.name]随时可能插入。",

							"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.tail+(true)]并把它猛拉到自己[npc.pussy+]，"
									+ "然后开始粗暴地强迫[npc2.tailTip]上下磨蹭[npc.her][npc.labia+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.tail+(true)]，粗暴地用[npc2.tailTip+]磨蹭自己[npc.vagina+]，"
									+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.tail+(true)]，把它挪到自己[npc.pussy+]。"
									+ "[npc.she]用[npc2.tailTip+]上下磨蹭[npc.her][npc.labia+]，挑逗[npc2.name]随时可能插入。",

							"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.tail+(true)]并引导它到自己[npc.pussy+]，"
									+ "然后开始用[npc2.tailTip]上下磨蹭[npc.her][npc.labia+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.tail+(true)]，用[npc2.tailTip+]磨蹭自己[npc.vagina+]，"
									+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]温柔地用[npc2.tail(true)]上下磨蹭[npc.namePos][npc.vagina+]，一声轻柔的[npc2.moan]从[npc2.her][npc2.lips+]间飘出。",
	
								"[npc2.name]发出一声柔和的[npc2.moan]，随后温柔地用[npc2.tail(true)]前后磨蹭[npc.namePos][npc.vagina+]。",
	
								"[npc2.Name]感受到[npc2.tail(true)]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.tail(true)]温柔地上下磨蹭[npc.namePos][npc.vagina+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]粗暴地用[npc2.tail(true)]来回磨蹭[npc.namePos][npc.vagina+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.Name]发出一阵[npc2.a_moan+]，激烈地用[npc2.tail(true)]来回磨蹭[npc.namePos][npc.vagina+]。",
	
								"[npc2.Name]感觉到[npc2.tail(true)]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，为了提醒[npc.name]谁才是主导者，"
										+ "[npc2.she]开始用[npc2.tail(true)]粗暴地上下磨蹭[npc.namePos][npc.vagina+]。"));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]热切地用[npc2.tail(true)]来回磨蹭[npc.namePos][npc.vagina+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.Name]发出一声迷乱的[npc2.moan]，然后迫不及待地用[npc2.tail(true)]来回磨蹭[npc.namePos][npc.vagina+]。",
	
								"[npc2.Name]感受到[npc2.tail(true)]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.tail(true)]急切地上下磨蹭[npc.namePos][npc.vagina+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]用[npc2.tail(true)]来回磨蹭[npc.namePos][npc.vagina+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.Name]发出一声迷乱的[npc2.moan]，然后用[npc2.tail(true)]来回磨蹭[npc.namePos][npc.vagina+]。",
	
								"[npc2.Name]感受到[npc2.tail(true)]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.tail(true)]上下磨蹭[npc.namePos][npc.vagina+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.tail+(true)]从[npc.namePos][npc.vagina+]中拔出来。",
	
								"[npc2.Name]发出一声绝望的[npc2.sob]，然后拼命地尝试将[npc2.tail+(true)]从[npc.namePos][npc.labia+]中拔出来。",
	
								"[npc2.Name]痛苦地[npc2.sobsVerb]，并哀求[npc.name]放过自己的[npc2.tail(true)]。"));
						break;
				}
				if((Main.sex.getCharacterPerformingAction().isVaginaVirgin() || Main.sex.getCharacterPerformingAction().hasHymen()) && Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					if(Main.sex.getCharacterPerformingAction().isMute()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]把贞洁看得比什么都重，[npc.she]迅速地将[npc.pussy]推离[npc2.namePos][npc2.tail+]。",
								"[npc.name]还没准备好失去宝贵的贞洁，[npc.she]迅速地将[npc.pussy]推离[npc2.namePos][npc2.tail+]。",
								"[npc.Name]把宝贵的贞洁看得比什么都重，"
										+ "[npc.she]将[npc.pussy]推离[npc2.namePos][npc2.tail+]，明确强调自己真的不想被插入。"));
							
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]把宝贵的贞洁看得比什么都重，反复强调并[npc.moaning]着不想要[npc2.name]真的插入[npc.herHim]，",
								"[npc.name]还没准备好失去宝贵的贞洁，[npc.she][npc.moaning]着，明确强调自己并不想被插入，"));
						if(Main.sex.isDom(Main.sex.getCharacterPerformingAction())) {
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.speech(你喜欢这样吗？那么，你就好好享受去吧。我可不想就这样失去我的贞洁！)]",
									"[npc.speech(就到此为止吧！我不会让你夺走我的贞洁！)]",
									"[npc.speech(你也只能这样了！我是不会让你夺走我的贞操的！)]"));
						} else {
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.speech(你也只是对我的小穴满意，不是吗？我不想真的失去贞操！)]",
									"[npc.speech(对你而言，这就够了，对吧？请不要夺走我的童贞……)]",
									"[npc.speech(你现在只能用这个，懂吗？我可不想真的失去贞洁！)]"));
						}
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
	};
	
	
	public static final SexAction TAIL_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "开始尾交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.tail+(true)]插进[npc2.namePos][npc2.vagina+]，开始和[npc2.herHim]尾交。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]慢慢用[npc.tail(true)][npc.tailTip+]挑逗着[npc2.namePos][npc2.labia+]，"
									+ "发出轻微的[npc.moan]，缓缓地向前推，将[npc.clit+]没入[npc2.namePos][npc2.vagina+]里。",

							"[npc.Name]将其[npc.tail(true)][npc.tailTip+]抵住[npc2.namePos][npc2.labia+]，"
									+ "[npc.she]以缓慢而稳定的力度，轻柔地将它深深地插入[npc2.namePos][npc2.vagina+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tail(true)][npc.tailTip+]饥渴地挑逗着[npc2.namePos][npc2.labia+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.tail+(true)]深深插入[npc2.her][npc2.vagina+]。",

							"[npc.Name]将其[npc.tail(true)][npc.tailTip+]抵住[npc2.namePos][npc2.labia+]，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos][npc2.vagina+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tail(true)][npc.tailTip+]粗暴地磨蹭[npc2.namePos][npc2.labia+]，"
									+ "发出[npc.a_moan+]，然后粗暴地向前猛推，将[npc.tail+(true)]深深插入[npc2.her][npc2.vagina+]。",

							"[npc.Name]将其[npc.tail(true)][npc.tailTip+]抵住[npc2.namePos][npc2.labia+]，"
									+ "用力前推，粗暴地将它深深插入[npc2.her][npc2.vagina+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tail(true)][npc.tailTip+]饥渴地挑逗着[npc2.namePos][npc2.labia+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.tail+(true)]深深插入[npc2.her][npc2.vagina+]。",

							"[npc.Name]将其[npc.tail(true)][npc.tailTip+]抵住[npc2.namePos][npc2.labia+]，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos][npc2.vagina+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tail(true)][npc.tailTip+]挑逗着[npc2.namePos][npc2.labia+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，将[npc.tail+(true)]深深插入[npc2.her][npc2.vagina+]。",

							"[npc.Name]将其[npc.tail(true)][npc.tailTip+]抵住[npc2.namePos][npc2.labia+]，"
									+ "[npc.she]小幅度前推，将它深深插入[npc2.namePos][npc2.vagina+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isVaginaVirgin()) {
					if(Main.sex.getCharacterTargetedForSexAction(this).hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						UtilText.nodeContentSB.append("因为[npc2.name]把贞洁看得比什么都重，"
								+ "当[npc2.she]第一次体验到被插入的感觉时，[npc2.she]忍不住发出了一声狂乱的、震撼的尖叫。");
						
					} else if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING) {
						UtilText.nodeContentSB.append("因为[npc2.name]是个处女，当[npc2.she]第一次体验到被插入的感觉时，[npc2.she]不禁发出狂乱的哀号。");
						
					} else {
						UtilText.nodeContentSB.append("由于[npc2.name]是个处女，当[npc2.she]第一次体验到被插入的感觉时，[npc2.she]不禁发出惊讶的[npc2.moan]。");
					}
					
				} else if(Main.sex.getCharacterTargetedForSexAction(this).hasHymen()) {
					if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING) {
						UtilText.nodeContentSB.append("因为[npc2.name]的处女膜还完好无损，当[npc2.she]感到[npc.namePos][npc.tail+(true)]撕破了它时，[npc2.name]不禁发出狂乱的哀号。");
						
					} else {
						UtilText.nodeContentSB.append("因为[npc2.name]的处女膜还完好无损，当[npc2.she]感到[npc.namePos][npc.tail+(true)]撕破了它时，[npc2.she]不禁发出惊讶的[npc2.moan]。");
					}
					
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.tail+(true)]顶进[npc2.name]体内，[npc2.herHim]发出轻柔的[npc2.moan]，"
											+ "于是[npc2.she]温柔地将自己的[npc2.hips]向后压，以便让它在[npc2.vagina+]里插得更深。",
		
									"[npc2.name]轻柔地[npc2.moan]，开始温柔地扭动[npc2.hips]，"
											+ "将[npc.namePos][npc.tail+(true)]更深地插入自己[npc2.vagina+]。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]感受到[npc.tail+(true)]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
											+ "[npc2.her]于是粗暴地挺起[npc2.hips]，强迫它在[npc2.her][npc2.vagina+]里插得更深。",
		
									"伴随着一声[npc2.a_moan+]，[npc2.name]开始猛烈地将[npc2.hips]向后顶，"
											+ "粗暴地强迫[npc.Name]把[npc.her][npc.tail+(true)]在自己[npc2.vagina+]里插得更深。"));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]感受到[npc.tail+(true)]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
											+ "[npc2.she]急切地扭动[npc2.hips]，让它在[npc2.vagina+]里插得更深。",
		
									"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地将[npc2.hips]向后顶，"
											+ "饥渴地让[npc.namePos][npc.tail+(true)]更加深入[npc2.her][npc2.vagina+]。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]感受到[npc.tail+(true)]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
											+ "[npc2.she]将[npc2.hips]向后压，让它在[npc2.vagina+]里插得更深。",
		
									"伴随着一声[npc2.a_moan+]，[npc2.name]开始将[npc2.hips]向后压，"
											+ "让[npc.namePos][npc.tail+(true)]更加深入[npc2.her][npc2.vagina+]。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]感受到[npc.tail+(true)]进入了自己的身体，不禁漏出一声[npc2.a_sob+]，"
											+ "泪水不停地从[npc2.her]的[npc2.face]上淌下，[npc2.she]哀求[npc.Name]从自己身体里拔出来。",
		
									"[npc2.name]发出[npc2.a_sob+]，徒劳地挣扎扭身，试图拔出讨厌的插入物，"
											+ "但[npc.namePos]不请自来的[npc.tail(true)]却在[npc2.vagina+]中插得更深，泪水从[npc2.her]的[npc2.face]上流了下来。"));
							break;
					}
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
									+ "[npc2.she]热切地帮着[npc.namePos][npc.tail+(true)]深深插入[npc2.her][npc2.vagina+]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "，急切地翘起[npc2.her]的[npc2.hips]，乞求[npc.Name]继续尾交[npc2.herHim]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地将[npc2.her][npc2.hips+]向后顶去，"
									+ "做出有助于[npc.tail+(true)]更深地插入[npc2.her][npc2.vagina+]的动作，急切地乞求[npc.Name]继续尾交[npc2.herHim]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要逃离[npc.namePos]的[npc.tail(true)]，但没能成功，"
									+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地乞求[npc.Name]从自己[npc2.vagina+]里拔出来，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "[npc2.she]恳求[npc.name]从自己[npc2.vagina+]里拔出来，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
		
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]从[npc2.her][npc2.vagina+]里拔出来。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]扭动[npc2.hips]作为回应，"
									+ "[npc2.she]帮着[npc.namePos][npc.tail+(true)]深深插入[npc2.her][npc2.vagina+]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
	
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "翘起[npc2.her]的[npc2.hips]，祈求[npc.name]继续尾交[npc2.herHim]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地用[npc2.her][npc2.hips+]向后推去，"
									+ "做出有助于[npc.tail+(true)]更深地插入[npc2.her][npc2.vagina+]的动作，乞求[npc.Name]继续尾交[npc2.herHim]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]慢慢地扭动[npc2.hips]作为回应，"
									+ "[npc2.she]发出一声轻柔的[npc2.moan]，温柔地乞求着[npc.Name]继续尾交[npc2.her][npc2.vagina+]。",
	
							" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
									+ "[npc2.she]慢慢地向后回撞[npc2.hips]，恳求[npc.Name]继续尾交[npc2.herHim]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，轻柔地将[npc2.hips+]向后压去，"
									+ "做出有助于[npc.tail+(true)]更深地插入[npc2.her][npc2.vagina+]的动作，乞求[npc.Name]继续尾交[npc2.herHim]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地扭动[npc2.hips]作为回应，"
									+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地命令[npc.Name]继续干[npc2.herHim]。",
	
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]粗暴地用[npc2.hips]撞击[npc.Name]，命令[npc.Name]继续尾交[npc2.herHim]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地将[npc2.her][npc2.hips+]猛然向后撞去，"
									+ "强迫[npc.name]将[npc.tail+(true)]深深插入[npc2.her][npc2.vagina+]，命令[npc.name]继续尾交[npc2.herHim]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction TAIL_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "尾交(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "把你的[npc.tail(true)]温柔地在[npc2.namePos][npc2.vagina+]里抽插。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]温柔地将[npc.tail+(true)]深深插入[npc2.namePos][npc2.vagina+]，"
							+ "[npc.she]开始不断地抽插，慢慢地尾交着[npc2.name]，每次推入都会响起一小声[npc.moan]。",

					"[npc.name]慢慢地将[npc.tail+(true)]插入[npc2.namePos][npc2.vagina+]，"
							+ "开始温柔地抽插，[npc.she]轻柔地用尾巴操干[npc2.name]，不禁漏出一小声[npc.moan]。",

					"[npc.name]将[npc.tail+(true)]滑入[npc2.namePos][npc2.vagina+]，"
							+ "发出轻微的[npc.moan]，开始温柔地抽送打桩，一边呼吸着[npc2.namePos]的[npc2.scent]，一边慢慢地用尾巴操[npc2.herHim]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "尾交";
		}

		@Override
		public String getActionDescription() {
			return "继续在[npc2.namePos][npc2.vagina+]里抽送你[npc.tail+(true)]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.tail+(true)]深深插入[npc2.namePos][npc2.vagina+]，"
							+ "[npc.name]开始亢奋地抽送打桩，快乐地用尾巴操着[npc2.name]，每次猛推都会发出[npc.a_moan+]。",

					"[npc.name]亢奋地将[npc.tail+(true)]插入[npc2.namePos][npc2.vagina+]，"
							+ "开始疯狂地抽插，贪婪地尾交[npc2.name]，漏出[npc.a_moan+]。",

					"[npc.name]将[npc.tail+(true)]深深插入[npc2.namePos][npc2.vagina+]，"
							+ "[npc.Name]开始急切地抽送打桩，不时漏出一阵[npc.a_moan+]，[npc.she]一边嗅着[npc2.namePos]的[npc2.scent]，一边亢奋地用尾巴操着[npc2.herHim]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "尾交(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "用你[npc.tail+(true)]粗暴地抽插[npc2.namePos][npc2.vagina+]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]粗暴地将[npc.tail+(true)]深深插入[npc2.namePos][npc2.vagina+]，"
							+ "[npc.name]开始粗暴地抽送打桩，野蛮地尾交[npc2.name]，每次猛推都会发出[npc.a_moan+]。",

					"[npc.her]激烈地将[npc.tail+(true)]深深插入[npc2.namePos][npc2.vagina+]，"
							+ "开始粗暴地抽送打桩，激烈地尾交[npc2.name]，漏出[npc.a_moan+]。",

					"[npc.name]激烈地将[npc.tail+(true)]深深插入[npc2.namePos][npc2.vagina+]，"
							+ "开始粗鲁地抽送打桩，[npc.she]嗅着[npc2.namePos]的[npc2.scent]，暴力地尾交[npc2.name]，不时漏出[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "尾交";
		}

		@Override
		public String getActionDescription() {
			return "继续在[npc2.namePos][npc2.vagina+]里抽送你[npc.tail+(true)]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.tail+(true)]深深插入[npc2.namePos][npc2.vagina+]，"
							+ "开始抽送打桩，快乐地用尾巴操着[npc2.name]，每次猛推都会发出[npc.a_moan+]。",

					"[npc.name]将[npc.tail+(true)]插入[npc2.namePos][npc2.vagina+]，"
							+ "开始抽插打桩，热情地用尾巴操着[npc2.herHim]，漏出[npc.a_moan+]。",

					"[npc.name]将[npc.tail+(true)]深深插入[npc2.namePos][npc2.vagina+]，"
							+ "[npc.Name]开始抽送打桩，不时漏出一阵[npc.a_moan+]，[npc.she]一边嗅着[npc2.namePos]的[npc2.scent]，一边用尾巴操着[npc2.herHim]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "尾交(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "让你[npc.tail+(true)]在[npc2.namePos][npc2.vagina+]里渴求地塞进拔出。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.tail+(true)]深深插入[npc2.namePos][npc2.vagina+]，"
							+ "[npc.name]开始亢奋地抽送打桩，快乐地用尾巴操着[npc2.name]，每次猛推都会发出[npc.a_moan+]。",

					"[npc.name]亢奋地将[npc.tail+(true)]插入[npc2.namePos][npc2.vagina+]，"
							+ "开始疯狂地抽插，贪婪地尾交[npc2.name]，漏出[npc.a_moan+]。",

					"[npc.name]将[npc.tail+(true)]深深插入[npc2.namePos][npc2.vagina+]，"
							+ "[npc.Name]开始急切地抽送打桩，不时漏出一阵[npc.a_moan+]，[npc.she]一边嗅着[npc2.namePos]的[npc2.scent]，一边亢奋地用尾巴操着[npc2.herHim]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抗拒尾交";
		}

		@Override
		public String getActionDescription() {
			return "努力把你的[npc.tail(true)]从[npc2.namePos][npc2.vagina+]里拔出来。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]尝试将[npc.tail(true)]从[npc2.namePos]的[npc2.pussy]里拔出来，"
									+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就低下去轻轻地抓住它，然后温柔地把它扭回[npc2.her][npc2.vagina+]。",
	
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.tail(true)]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并温柔地把它扭回[npc2.her][npc2.vagina+]里。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.tail(true)]从[npc2.namePos][npc2.pussy+]里拔出来，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边温柔地将[npc2.vagina+]压向[npc.her][npc.tail+(true)]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]尝试将[npc.tail(true)]从[npc2.namePos]的[npc2.pussy]里拔出来，"
									+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就向后粗暴地抓住它，然后蛮横地把它扭回[npc2.her][npc2.vagina+]。",
	
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.tail(true)]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并粗暴地把它扭回[npc2.her][npc2.vagina+]里。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.tail(true)]从[npc2.namePos][npc2.pussy+]里拔出来，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边粗暴地将[npc2.vagina+]压向[npc.her][npc.tail+(true)]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]尝试将[npc.tail(true)]从[npc2.namePos]的[npc2.pussy]里拔出来，"
									+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就向后牢牢地抓住它，然后热切地把它扭回[npc2.her][npc2.vagina+]。",
	
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.tail(true)]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并渴望地把它扭回[npc2.her][npc2.vagina+]里。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.tail(true)]从[npc2.namePos][npc2.pussy+]里拔出来，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边将[npc2.vagina+]压向[npc.her][npc.tail+(true)]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止尾交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.tail+(true)]从[npc2.namePos][npc2.vagina+]里拔出来，停止和[npc2.herHim]尾交。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将[npc.tail+(true)]从[npc2.namePos][npc2.pussy+]中拉出，"
									+ "霸道地用[npc.tailTip+]最后一次上下磨蹭[npc2.her][npc2.labia+]，然后将它移开。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.tail+(true)]从[npc2.her][npc2.vagina+]中猛抽出来，结束了粗暴尾交。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]将[npc.tail(true)]滑出[npc2.namePos][npc2.pussy+]，在最后一次用[npc.tailTip]来回磨蹭[npc2.her][npc2.labia+]后，将[npc2.her]推开。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.tail+(true)]从[npc2.her][npc2.vagina+]中拔出来，结束了尾交。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.Name]从[npc2.her]的[npc2.vagina]里拔出来时，[npc2.name]忍不住漏出一声[npc2.sob+]，"
										+ "[npc2.she]仍然不停地哭泣，无力地继续反抗着[npc.name]。",
	
								"[npc2.name]发出一阵[npc2.a_sob+]，仍然挣扎着试图将[npc2.vagina+]从[npc.name]身上抽出。[npc2.she]哀求对方放过自己，眼泪已经忍不住像小溪一样从[npc2.face]上淌下。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.name]将[npc.tail+(true)]拔出[npc2.her][npc2.vagina+]时，[npc2.Name]发出[npc2.a_moan+]，渴求[npc.name]的更多“照顾”。",
	
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]无比渴望得到[npc.namePos]的更多关注。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction USING_TAIL_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "开始被尾交";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.namePos][npc2.tail+(true)]插入你[npc.vagina+]，接受尾交。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isVaginaVirgin()) {
				if(Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					UtilText.nodeContentSB.append("尽管[npc.Name]珍视[npc.her]的处子之身胜过这世上的任何一切，[npc.she]觉得现在是时候献出自己的贞洁了。");
				} else {
					UtilText.nodeContentSB.append("尽管[npc.Name]刚刚都还想着保留[npc.her]的处子之身，[npc.her]觉得现在是时候献出自己的贞洁了。");
				}
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tail(true)]，慢慢地将它引导到自己[npc.labia+]，"
									+ "[npc.her]温柔地摇晃着[npc.hips]，不时漏出一小声[npc.moan]，强迫[npc2.herHim]插入[npc.her][npc.vagina+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.tail(true)]，把它对准自己[npc.vagina+]，"
									+ "慢慢地将[npc.hips]向后顶，将[npc2.her][npc2.tail+(true)]插入自己，不禁漏出一声轻柔的[npc.moan]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tail(true)]，急切地将它引导到自己[npc.labia+]，"
									+ "[npc.her]亢奋地摇晃着[npc.hips]，不时漏出一阵[npc.a_moan+]，强迫[npc2.herHim]插入[npc.her][npc.vagina+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.tail(true)]，把它对准自己[npc.vagina+]，"
									+ "[npc.she]热切地向后顶[npc.hips]，吞下[npc2.her][npc2.tail+(true)]，[npc.she]不禁漏出一声[npc.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]抓住[npc2.namePos]的[npc2.tail(true)]，粗暴地把它拉到自己[npc.labia+]，"
									+ "[npc.her]暴力地猛烈摇晃[npc.hips]，不时漏出一阵[npc.a_moan+]，强迫[npc2.herHim]操进[npc.her][npc.vagina+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.tail(true)]，把它对准自己[npc.vagina+]，"
									+ "[npc.she]急切地向后猛撅[npc.hips]，裹住[npc2.her][npc2.tail+(true)]，[npc.she]不禁漏出一声[npc.a_moan+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tail(true)]，将它引导到自己[npc.labia+]，"
									+ "[npc.her]摇晃着[npc.hips]，不时漏出一阵[npc.a_moan+]，强迫[npc2.herHim]插入[npc.her][npc.vagina+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.tail(true)]，把它对准自己[npc.vagina+]，"
									+ "[npc.she]向后顶[npc.hips]，吞下[npc2.her][npc2.tail+(true)]，[npc.she]不禁漏出一声[npc.a_moan+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.herHim]的身体，发出一声轻柔的[npc2.moan]，"
										+ "[npc2.she]温柔地将[npc2.tail(true)]向前顶，开始尾交[npc.namePos][npc.vagina+]。",
	
								"[npc2.name]轻柔地[npc2.moan]，温柔地挺进自己的[npc2.tail(true)]，"
										+ "将它深深插进[npc.namePos][npc.vagina+]，开始尾交[npc.herHim]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "[npc2.she]热切地将[npc2.tail(true)]向前顶，开始亢奋地尾交[npc.namePos][npc.vagina+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，热切地挺进自己的[npc2.tail(true)]，"
										+ "将它深深插进[npc.namePos][npc.vagina+]，开始竭力地尾交[npc.herHim]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "为了警告[npc.name]别得意忘形，[npc2.she]粗暴地向前猛撅[npc2.tail(true)]，开始无情地用尾巴暴操[npc.her][npc.vagina+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，粗暴地猛插自己的[npc2.tail(true)]，"
										+ "[npc2.she]无情地用尾巴暴操[npc.namePos][npc.vagina+]，宣告着自己的支配权。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]挺进[npc.name]的身体，不禁漏出一声[npc2.a_moan+]，[npc2.she]将[npc2.tail(true)]向前顶，开始尾交[npc.namePos][npc.vagina+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，向前挺进[npc2.tail(true)]，将它深深插入[npc.namePos][npc.vagina+]，开始尾交[npc.herHim]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]紧紧裹住[npc2.name]的[npc2.tail(true)]，[npc2.name]不禁发出一声[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着推开[npc.name]，拼命地想把自己[npc2.tail+(true)]从对方[npc.vagina+]里拔出来。",
	
								"[npc.Name]强行将[npc2.name]的[npc2.tail(true)]深深插入自己[npc.vagina+]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
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
							"[npc2.Name]贪婪地将自己[npc2.tail+(true)]深入[npc.namePos][npc.vagina+]，"
									+ "[npc2.she]发出[npc2.a_moan+]，亢奋地插入[npc.namePos][npc.pussy+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.tail+(true)]深深插入[npc.namePos][npc.vagina+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，热切地挺动[npc2.her][npc2.tail+(true)]，拼命要插入[npc.namePos][npc.vagina+]最深处。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.tail(true)]从[npc.namePos]的[npc.pussy]中拔出，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.tail+(true)]在自己[npc.vagina+]中继续抽插。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.tail(true)]从[npc.namePos][npc.vagina+]中抽离。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将[npc2.tail+(true)]滑入[npc.namePos][npc.vagina+]深处，"
									+ "[npc2.she]发出柔和的[npc2.moan]，轻柔地抽插[npc.namePos][npc.pussy+]。",
		
							"[npc2.namePos]的唇间飘出一阵[npc2.A_moan+]，[npc2.she]慢慢地将[npc2.tail+(true)]深深插入[npc.namePos][npc.vagina+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地用[npc2.tail+(true)]深入[npc.namePos][npc.vagina+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.tail+(true)]深深插入[npc.namePos][npc.vagina+]，"
									+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地插入[npc.namePos][npc.pussy+]。",
		
							"[npc2.namePos]的唇间飘出一阵[npc2.A_moan+]，[npc2.she]猛烈地将[npc2.tail+(true)]深深插入[npc.namePos][npc.vagina+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地挺动[npc2.her][npc2.tail+(true)]，拼命要插入[npc.namePos][npc.vagina+]最深处。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.tail+(true)]深深插入[npc.namePos][npc.vagina+]，"
									+ "[npc2.she]发出一阵[npc2.a_moan+]，不停抽插着[npc.namePos][npc.pussy+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.tail+(true)]深深插入[npc.namePos][npc.vagina+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.tail+(true)]深深插入[npc.namePos][npc.vagina+]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_TAIL_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "被尾交(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地用你[npc.vagina+]吞吐着[npc2.namePos][npc2.tail+(true)]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声轻柔的[npc.moan]，温柔地将自己的[npc.hips]向后拱起，以便[npc2.namePos][npc2.tail+(true)]可以在自己[npc.vagina+]中插得更深。",

					"[npc.Name]轻柔地[npc.moan]，温柔地向后拱着[npc.hips]，使得[npc2.namePos][npc2.tail+(true)]在自己[npc.vagina+]中插得更深。",

					"一边慢慢地将自己的[npc.hips]往后送，"
							+ "[npc.lips+]间飘出一声轻柔的[npc.moan]，[npc.her]设法让[npc2.namePos][npc2.tail+(true)]深深插入[npc.her][npc.vagina+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TAIL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "被尾交";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地用你[npc.vagina+]吞吐[npc2.namePos][npc2.tail+(true)]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，热切地向后拱[npc.hips]，以便[npc2.namePos][npc2.tail+(true)]可以在自己[npc.vagina+]中插得更深。",

					"[npc.name]发出一阵[npc.a_moan+]，亢奋地向后拱起[npc.hips]，强迫[npc2.namePos][npc2.tail+(true)]在自己[npc.vagina+]中插得更深。",

					"[npc.name]积极地向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.tail+(true)]插入自己[npc.vagina+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TAIL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "被尾交(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地用你[npc.vagina+]吞下[npc2.namePos][npc2.tail+(true)]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，暴力地将自己的[npc.hips]向后猛撞，强迫[npc2.namePos][npc2.tail+(true)]在自己[npc.vagina+]中插得更深。",

					"[npc.Name]发出一阵[npc.a_moan+]，粗暴地向后猛撞[npc.hips]，强迫[npc2.namePos][npc2.tail+(true)]在自己[npc.vagina+]中插得更深。",

					"[npc.name]激烈地向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]粗暴地强迫[npc2.namePos][npc2.tail+(true)]插入自己[npc.vagina+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_TAIL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "被尾交";
		}

		@Override
		public String getActionDescription() {
			return "用你[npc.vagina+]吞吐[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，向后拱[npc.hips]，以便[npc2.namePos][npc2.tail+(true)]更深地没入自己[npc.vagina+]。",

					"[npc.name]发出一阵[npc.a_moan+]，向后拱起[npc.hips]，强迫[npc2.namePos][npc2.tail+(true)]在自己[npc.vagina+]中插得更深。",

					"[npc.name]向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.tail+(true)]插入自己[npc.vagina+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TAIL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "被尾交(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地用你[npc.vagina+]吞吐[npc2.namePos][npc2.tail+(true)]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，热切地向后拱[npc.hips]，以便[npc2.namePos][npc2.tail+(true)]可以在自己[npc.vagina+]中插得更深。",

					"[npc.name]发出一阵[npc.a_moan+]，亢奋地向后拱起[npc.hips]，强迫[npc2.namePos][npc2.tail+(true)]在自己[npc.vagina+]中插得更深。",

					"[npc.name]积极地向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.tail+(true)]插入自己[npc.vagina+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抗拒被尾交";
		}

		@Override
		public String getActionDescription() {
			return "努力让你[npc.vagina+]远离[npc2.namePos][npc2.tail+(true)]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
									+ "[npc.she]无力地试着将[npc2.namePos][npc2.tail(true)]从自己[npc.vagina+]里拔出来。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.pussy]从[npc2.namePos]令人憎恶的性器抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.tail+(true)]依然从容地在[npc.her][npc.vagina+]里滑进滑出。",

							"[npc.name]拼命地尝试把[npc.hips]挪开，"
									+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.tail+(true)]依然温柔地滑进[npc.her][npc.vagina+]深处。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
									+ "[npc.she]无力地试着将[npc2.namePos][npc2.tail(true)]从自己[npc.vagina+]里拔出来。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.pussy]从[npc2.namePos]令人憎恶的性器抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.tail+(true)]依然疯狂地在[npc.her][npc.vagina+]里抽送爆操。",

							"[npc.name]拼命地尝试把[npc.hips]挪开，"
									+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.tail+(true)]依然贪婪地插入[npc.her][npc.vagina+]深处。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
									+ "[npc.she]无力地试着将[npc2.namePos][npc2.tail(true)]从自己[npc.vagina+]里拔出来。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.pussy]从[npc2.namePos]令人憎恶的性器抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.tail+(true)]依然粗暴地在[npc.her][npc.vagina+]里抽送爆操。",

							"[npc.name]拼命地尝试把[npc.hips]挪开，"
									+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.tail+(true)]依然继续激烈地插入[npc.her][npc.vagina+]深处。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "停止被尾交";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.her][npc2.tail(true)]从你[npc.vagina+]里拔出来。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]的[npc2.tail(true)]从自己[npc.vagina+]里抽出，[npc.she]愤怒地咆哮着，命令[npc2.name]不准再操了。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]的[npc2.tail(true)]从自己[npc.vagina+]中抽出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]的[npc2.tail(true)]从自己[npc.vagina+]中抽出，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]不要再操了。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc2.her]的[npc2.tail(true)]从自己[npc.vagina+]中抽出。"));
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
								"[npc.Name]不再让[npc2.name]操自己[npc.vagina+]，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
	
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续抽插[npc.namePos][npc.vagina+]的渴望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PUSSY_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "收紧小穴";
		}

		@Override
		public String getActionDescription() {
			return "用你肌肉发达的小穴挤弄包裹着[npc2.namePos]的[npc2.tail(true)]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getVaginaOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]发出一阵[npc.a_moan+]，继续专心用[npc.her]肌肉极致发达的[npc.pussy]挤弄包裹着[npc2.namePos][npc2.tail+(true)]。",

					(!isTargetedCharacterInanimate()
						?"[npc.Name]发出一阵[npc.a_moan+]，继续专心控制自己[npc.pussy]内部极致发达的肌肉。"
							+ "[npc.Name]挤弄包裹着[npc2.namePos][npc2.tail+(true)]，让[npc2.herHim]不禁漏出一声愉悦的呻吟。"
						:""),

					"[npc.Name]以稳定的节奏[npc.moans]着，[npc.she]继续专心"
							+ "用[npc.her]肌肉极致发达且[npc.pussy+]挤弄包裹着[npc2.namePos][npc2.tail+(true)]。",

					"[npc.Name]发出一阵[npc.a_moan+]，专心控制[npc.pussy]内里极致发达的肌肉，"
							+ "[npc.she]挤弄按摩着[npc2.namePos][npc2.tail+(true)]时，不禁愉悦地尖叫一声。");
		}
	};
}
