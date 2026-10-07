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
 * @since 0.1.82
 * @version 0.4
 * @author Innoxia
 */
public class TentacleAnus {
	
	public static final SexAction TEASE_TENTACLE_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "用触手挑逗肛门";
		}

		@Override
		public String getActionDescription() {
			return "将你的[npc.tentacle(true)]顶在[npc2.namePos][npc2.asshole+]上滑动。";
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
							"将[npc.her]的[npc.tentacle+(true)]蜿蜒伸向[npc2.namePos][npc2.assCloaca+]，"
									+ "开始缓慢地用[npc.tentacleTip+]上下挑逗[npc2.her][npc2.asshole+]，随时准备插入[npc2.herHim]。",

							"[npc.Name]轻声[npc.moan]，把自己[npc.tentacle+(true)]伸向[npc2.namePos][npc2.assCloaca+]，温柔地用[npc.tentacleTip]来回磨蹭[npc2.her][npc2.asshole+]。",

							"[npc.name]温柔地用[npc.tentacle(true)][npc.tentacleTip+]上下挑逗[npc2.namePos][npc2.asshole+]，"
									+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了轻柔的[npc.moan]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"将[npc.her]的[npc.tentacle+(true)]蜿蜒伸向[npc2.namePos][npc2.assCloaca+]，"
									+ "[npc.name]开始渴望地顶着[npc2.her][npc2.asshole+]来回磨蹭[npc.tentacleTip+]，随时准备插入[npc2.herHim]。",

							"伴随着一阵[npc.a_moan+]，[npc.name]把[npc.tail+(true)]动到[npc2.namePos][npc2.assCloaca+]上，然后开始急切地将[npc.tentacleTip]顶在[npc2.her][npc2.asshole+]上来回磨蹭。",

							"[npc.name]急切地用[npc.tentacle(true)][npc.tentacleTip+]上下挑逗[npc2.namePos][npc2.asshole+]，"
									+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了一阵[npc.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tentacle+(true)]磨蹭着[npc2.namePos][npc2.assCloaca+]，"
									+ "接着向后退了一点，开始用[npc.tentacleTip+]上下挑逗[npc2.her][npc2.asshole+]，随时准备插入[npc2.herHim]。",

							"[npc.name]发出一阵[npc.a_moan+]，把[npc.tentacle+(true)]挪向[npc2.namePos][npc2.assCloaca+]，"
									+ "然后开始粗暴地用[npc.tentacleTip]上下磨蹭[npc2.namePos][npc2.asshole+]。",

							"[npc.name]用[npc.tentacle(true)][npc.tentacleTip+]粗暴地来回磨蹭[npc2.namePos][npc2.asshole+]，"
									+ "一想到只要[npc.she]高兴随时可以操干[npc2.name]，[npc.name]就发出了一阵[npc.a_moan+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"将[npc.her]的[npc.tentacle+(true)]蜿蜒伸向[npc2.namePos][npc2.assCloaca+]，"
									+ "[npc.name]开始顶着[npc2.her][npc2.asshole+]来回磨蹭[npc.tentacleTip+]，随时准备插入[npc2.herHim]。",

							"伴随着一阵[npc.a_moan+]，[npc.name]把[npc.tail+(true)]动到[npc2.namePos][npc2.assCloaca+]上，然后开始将[npc.tentacleTip]顶在[npc2.her][npc2.asshole+]上来回磨蹭。",

							"[npc.name]用[npc.tentacle(true)][npc.tentacleTip+]上下挑逗[npc2.namePos][npc2.asshole+]，"
									+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了一阵[npc.a_moan+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]感觉到[npc2.asshole+]传来的阵阵快感，一声轻柔的[npc2.moan]从[npc2.her][npc2.lips+]间飘出，"
										+ "然后[npc2.she]温柔地往回推动[npc2.assCloaca+]，顶向[npc.namePos]的[npc.tentacle(true)]。",
	
								"[npc2.name]发出一声柔和的[npc2.moan]，随后温柔地将[npc2.assCloaca+]向后压在[npc.namePos]的[npc.tentacle(true)]上。",
	
								"[npc2.name]感受到[npc.namePos]的[npc.tentacle(true)]刺激着[npc2.she][npc2.asshole+]，[npc2.she]愉悦地[npc2.moanVerb]着，"
										+ "并轻柔地向后压[npc2.her][npc2.hips+]作为回应。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受着[npc2.asshole+]传来的阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间爆发而出，"
										+ "而[npc2.she]急切地推着[npc2.her][npc2.assCloaca+]回撞[npc.namePos]的[npc.tentacle(true)]。",
	
								"[npc2.name]发出一声迷乱的[npc2.moan]，然后迫不及待地用[npc2.her][npc2.assCloaca+]回撞[npc.namePos]的[npc.tentacle(true)]。",
	
								"[npc2.name]感受到[npc.namePos]的[npc.tentacle(true)]刺激着[npc2.she][npc2.asshole+]，[npc2.she]愉悦地[npc2.moanVerb]着，"
										+ "并饥渴地向后压[npc2.her][npc2.hips+]作为回应。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受着[npc2.asshole+]传来的阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间爆发而出，"
										+ "然后[npc2.she]暴力地用[npc2.her][npc2.assCloaca+]挤压[npc.namePos]的[npc.tentacle(true)]。",
	
								"[npc2.name]发出一声迷乱的[npc2.moan]，然后粗暴地用[npc2.her][npc2.assCloaca+]回撞[npc.namePos]的[npc.tentacle(true)]。",
	
								"[npc2.name]感受到[npc.namePos]的[npc.tentacle(true)]刺激着[npc2.she][npc2.asshole+]，[npc2.she]愉悦地[npc2.moanVerb]着，"
										+ "并粗暴地向后压[npc2.her][npc2.hips+]作为回应。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受着[npc2.asshole+]传来的阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间爆发而出，"
										+ "然后[npc2.she]往回推[npc2.her][npc2.assCloaca+]，顶着[npc.namePos]的[npc.tentacle(true)]。",
	
								"[npc2.name]发出一声[npc2.moan]，然后将[npc2.her][npc2.assCloaca+]推向[npc.namePos]的[npc.tentacle(true)]。",
	
								"[npc2.name]感受到[npc.namePos]的[npc.tentacle(true)]正刺激着[npc2.her][npc2.asshole+]，[npc2.she]愉悦地[npc2.moanVerb]着，并扭动[npc2.her][npc2.hips+]作为回应。"));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isAssVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"一想到自己即将要被夺走肛门贞操，[npc2.namePos][npc2.lips+]间不禁爆发出一阵[npc2.A_sob+]。",
										"[npc2.Name]意识到自己可能将失去肛门贞操，不禁发出一声绝望的[npc2.sob]。",
										"一想到自己马上要被[npc.namePos][npc.tentacle+]夺走肛门贞操，[npc2.name]就不禁绝望地[npc2.sobVerb]着。"));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.namePos]想到接下来要发生的事，[npc2.lips+]间不禁爆发出一阵[npc2.A_sob+]，[npc2.speech(不！快停下！我还没肛交过呢！)]",
										"[npc2.Name]发出一声绝望的[npc2.sob]，苦苦乞求，[npc2.speech(求求你！不要做这种事！我还没肛交过，我害怕！)]",
										"[npc2.Name]绝望地哭诉，脑袋止不住地想接下来会发生什么，哀求道，[npc2.speech(不！快停手！我不想被夺走菊穴童贞！)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.assCloaca]从[npc.namePos]的[npc.tentacle]抽离。",
										"[npc2.Name]不断反抗着[npc.Name]，不禁发出绝望的[npc2.sob]。",
										"[npc2.name]痛苦地[npc2.sobVerb]着，试图远离[npc.namePos]的[npc.tentacle]。"));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.assCloaca]从[npc.namePos]的[npc.tentacle(true)]抽离，"
												+ "[npc2.speech(不！不可以！求求你了，放过我吧！)]",
										"[npc2.Name]发出一声绝望的[npc2.sob]，苦苦乞求，[npc2.speech(求求你！不要这样做！放过我！)]",
										"[npc2.Name]痛苦地[npc2.sobVerb]着，[npc2.she]哀求道，[npc2.speech(不！停下！求求你快走吧！)]"));
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TENTACLE, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS);
		}
	};
	
	public static final SexAction FORCE_TENTACLE_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "用触手挑逗(肛门)";
		}

		@Override
		public String getActionDescription() {
			return "用[npc2.namePos][npc2.tentacle(true)]的[npc2.tentacleTip]滑过你[npc.asshole+]。";
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
							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+(true)]，把它挪到自己[npc.assCloaca+]。"
									+ "[npc.she]缓慢地用[npc2.tentacleTip+]上下磨蹭[npc.her][npc.asshole+]，挑逗[npc2.name]随时可能插入。",

							"[npc.name]轻声[npc.moan]，抓住[npc2.namePos][npc2.tentacle+(true)]，将它移向自己[npc.assCloaca+]，"
									+ "然后开始温柔地用[npc2.tentacleTip]上下磨蹭[npc.her][npc.asshole+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+(true)]，温柔地用[npc2.tentacleTip+]磨蹭自己[npc.asshole+]，"
									+ "[npc.she]发出一声轻柔的[npc.moan]，挑逗[npc2.Name]随时可能插入。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+(true)]，把它挪到自己[npc.assCloaca+]。"
									+ "[npc.she]急切地用[npc2.tentacleTip+]上下磨蹭[npc.her][npc.asshole+]，挑逗[npc2.name]随时可以插入。",

							"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.tentacle+(true)]并引导它到自己[npc.assCloaca+]，"
									+ "然后开始急切地用[npc2.tentacleTip]上下磨蹭[npc.her][npc.asshole+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+(true)]，饥渴地用[npc2.tentacleTip+]磨蹭自己[npc.asshole+]，"
									+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+(true)]，把它猛拉到自己[npc.assCloaca+]。"
									+ "[npc.she]粗暴地强迫[npc2.tentacleTip+]上下磨蹭[npc.her][npc.asshole+]，挑逗[npc2.name]随时可能插入。",

							"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.tentacle+(true)]并把它拉扯到自己[npc.assCloaca+]，"
									+ "然后开始粗暴地强迫[npc2.tentacleTip]上下磨蹭[npc.her][npc.asshole+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+(true)]，粗暴地用[npc2.tentacleTip+]磨蹭自己[npc.asshole+]，"
									+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+(true)]，把它挪到自己[npc.assCloaca+]。"
									+ "[npc.she]用[npc2.tentacleTip+]上下磨蹭[npc.her][npc.asshole+]，挑逗[npc2.name]随时可能插入。",

							"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.tentacle+(true)]并引导它到自己[npc.assCloaca+]，"
									+ "然后开始用[npc2.tentacleTip]上下磨蹭[npc.her][npc.asshole+]。",

							"[npc.Name]抓住[npc2.namePos][npc2.tentacle+(true)]，用[npc2.tentacleTip+]磨蹭自己[npc.asshole+]，"
									+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]温柔地用[npc2.tentacle(true)]上下磨蹭[npc.namePos][npc.asshole+]，一声轻柔的[npc2.moan]从[npc2.her][npc2.lips+]间飘出。",
	
								"[npc2.name]发出一声柔和的[npc2.moan]，随后温柔地用[npc2.tentacle(true)]前后磨蹭[npc.namePos][npc.asshole+]。",
	
								"[npc2.Name]感觉到[npc2.tentacle(true)]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.tentacle(true)]温柔地上下磨蹭[npc.namePos][npc.asshole+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]粗暴地用[npc2.tentacle(true)]来回磨蹭[npc.namePos][npc.asshole+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.Name]发出一阵[npc2.a_moan+]，激烈地用[npc2.tentacle(true)]来回磨蹭[npc.namePos][npc.asshole+]。",
	
								"[npc2.Name]感觉到[npc2.tentacle(true)]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，为了提醒[npc.name]谁才是主导者，"
										+ "[npc2.she]开始用[npc2.tentacle(true)]粗暴地上下磨蹭[npc.namePos][npc.asshole+]。"));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]热切地用[npc2.tentacle(true)]来回磨蹭[npc.namePos][npc.asshole+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.Name]发出一声迷乱的[npc2.moan]，然后迫不及待地用[npc2.tentacle(true)]来回磨蹭[npc.namePos][npc.asshole+]。",
	
								"[npc2.Name]感觉到[npc2.tentacle(true)]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.tentacle(true)]急切地上下磨蹭[npc.namePos][npc.asshole+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]用[npc2.tentacle(true)]来回磨蹭[npc.namePos][npc.asshole+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.Name]发出一声迷乱的[npc2.moan]，然后用[npc2.tentacle(true)]来回磨蹭[npc.namePos][npc.asshole+]。",
	
								"[npc2.Name]感觉到[npc2.tentacle(true)]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.tentacle(true)]上下磨蹭[npc.namePos][npc.asshole+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.tentacle+(true)]从[npc.namePos][npc.asshole+]中拔出来。",
	
								"[npc2.Name]发出一声绝望的[npc2.sob]，然后拼命地尝试将[npc2.tentacle+(true)]从[npc.namePos][npc.assCloaca+]中拔出来。",
	
								"[npc2.Name]痛苦地[npc2.sobsVerb]，并哀求[npc.name]放过自己的[npc2.tentacle(true)]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TENTACLE, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS);
		}
	};
	
	
	public static final SexAction TENTACLE_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "开始触手肛交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.tentacle+(true)]插进[npc2.namePos][npc2.asshole+]，开始和[npc2.herHim]触手肛交。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]慢慢用[npc.tentacle(true)][npc.tentacleTip+]挑逗着[npc2.namePos][npc2.assCloaca+]，"
									+ "发出轻微的[npc.moan]，缓缓地向前推，将[npc.tentacle+(true)]没入[npc2.namePos][npc2.asshole+]里。",

							"[npc.Name]将其[npc.tentacle(true)][npc.tentacleTip+]抵住[npc2.namePos]的屁股蛋，"
									+ "[npc.she]以缓慢而稳定的力度，轻柔地将它深深地插入[npc2.namePos][npc2.asshole+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tentacle(true)][npc.tentacleTip+]饥渴地挑逗着[npc2.namePos][npc2.assCloaca+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.tentacle+(true)]深深插入[npc2.her][npc2.asshole+]。",

							"[npc.Name]将其[npc.tentacle(true)][npc.tentacleTip+]抵住[npc2.namePos]的屁股蛋，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos][npc2.asshole+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tentacle(true)][npc.tentacleTip+]粗暴地磨蹭[npc2.namePos][npc2.assCloaca+]，"
									+ "发出[npc.a_moan+]，然后粗暴地向前猛推，将[npc.tentacle+(true)]深深插入[npc2.her][npc2.asshole+]。",

							"[npc.Name]将其[npc.tentacle(true)][npc.tentacleTip+]抵住[npc2.namePos]的屁股蛋，"
									+ "用力前推，粗暴地将它深深插入[npc2.her][npc2.asshole+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tentacle(true)][npc.tentacleTip+]饥渴地挑逗着[npc2.namePos][npc2.assCloaca+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.tentacle+(true)]深深插入[npc2.her][npc2.asshole+]。",

							"[npc.Name]将其[npc.tentacle(true)][npc.tentacleTip+]抵住[npc2.namePos]的屁股蛋，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos][npc2.asshole+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tentacle(true)][npc.tentacleTip+]挑逗着[npc2.namePos][npc2.assCloaca+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，将[npc.tentacle+(true)]深深插入[npc2.her][npc2.asshole+]。",

							"[npc.Name]将其[npc.tentacle(true)][npc.tentacleTip+]抵住[npc2.namePos]的屁股蛋，"
									+ "[npc.she]小幅度前推，将它深深插入[npc2.namePos][npc2.asshole+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.tentacle+(true)]顶进[npc2.name]体内，[npc2.herHim]发出轻柔的[npc2.moan]，"
										+ "于是[npc2.she]温柔地将自己的[npc2.hips]向后压，以便让它在[npc2.asshole+]里插得更深。",
	
								"[npc2.name]轻柔地[npc2.moan]，开始温柔地扭动[npc2.hips]，"
										+ "将[npc.namePos][npc.tentacle+(true)]更深地插入自己[npc2.asshole+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.tentacle+(true)]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.her]于是粗暴地挺起[npc2.hips]，强迫它在[npc2.her][npc2.asshole+]里插得更深。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始猛烈地将[npc2.hips]向后顶，"
										+ "粗暴地强迫[npc.Name]把[npc.her][npc.tentacle+(true)]在自己[npc2.asshole+]里插得更深。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.tentacle+(true)]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.she]急切地扭动[npc2.hips]，让它在[npc2.asshole+]里插得更深。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地将[npc2.hips]向后顶，"
										+ "饥渴地让[npc.namePos][npc.tentacle+(true)]更加深入[npc2.her][npc2.asshole+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.tentacle+(true)]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.she]将[npc2.hips]向后压，让它在[npc2.asshole+]里插得更深。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始将[npc2.hips]向后压，"
										+ "让[npc.namePos][npc.tentacle+(true)]更加深入[npc2.her][npc2.asshole+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.tentacle+(true)]进入了自己的身体，不禁漏出一声[npc2.a_sob+]，"
										+ "泪水不停地从[npc2.her]的[npc2.face]上淌下，[npc2.she]哀求[npc.Name]从自己身体里拔出来。",
	
								"[npc2.name]发出[npc2.a_sob+]，徒劳地挣扎扭身，试图拔出讨厌的插入物，"
										+ "但[npc.namePos]不请自来的[npc.tentacle(true)]却在[npc2.asshole+]中插得更深，泪水从[npc2.her]的[npc2.face]上流了下来。"));
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
							"[npc2.name]急切地将[npc2.her][npc2.hips]向后推，作为回应，"
									+ "[npc2.she]热切地帮着[npc.namePos][npc.tentacle+(true)]深深插入[npc2.her][npc2.asshole+]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "，急切地把[npc2.her][npc2.hips]往后翘起，[npc2.she]乞求[npc.Name]继续用触手肛交[npc2.herHim]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地将[npc2.her][npc2.hips+]向后顶去，"
									+ "做出有助于[npc.tentacle+(true)]更深地插入[npc2.her][npc2.asshole+]的动作，急切地乞求[npc.Name]继续触手肛交[npc2.herHim]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要逃离[npc.namePos]的[npc.tentacle(true)]，但没能成功，"
									+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地乞求[npc.Name]从自己[npc2.asshole+]里拔出来，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "[npc2.she]恳求[npc.name]从自己[npc2.asshole+]拔出来，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
		
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]从[npc2.her][npc2.asshole+]拔出来。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]把[npc2.hips]往后一推作以回应，"
									+ "[npc2.she]帮着[npc.namePos][npc.tentacle+(true)]深深插入[npc2.her][npc2.asshole+]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
	
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "翘起[npc2.her]的[npc2.hips]，祈求[npc.name]继续用触手肛交[npc2.herHim]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地用[npc2.her][npc2.hips+]向后推去，"
									+ "做出有助于[npc.tentacle+(true)]更深地插入[npc2.her][npc2.asshole+]的动作，乞求[npc.Name]继续触手肛交[npc2.herHim]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]慢慢地将[npc2.hips]向后压，"
									+ "[npc2.she]发出一声轻柔的[npc2.moan]，温柔地乞求着[npc.Name]继续用触手肛交[npc2.her][npc2.asshole+]。",
	
							" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
									+ "[npc2.she]慢慢地向后回撞[npc2.hips]，恳求[npc.Name]继续用触手肛交[npc2.herHim]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，轻柔地将[npc2.hips+]向后压去，"
									+ "做出有助于[npc.tentacle+(true)]更深地插入[npc2.her][npc2.asshole+]的动作，乞求[npc.Name]继续触手肛交[npc2.herHim]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]粗暴地将[npc2.hips]向后压，"
									+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地命令[npc.Name]继续干[npc2.herHim]。",
	
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]粗暴地用[npc2.hips]撞击[npc.Name]，命令[npc.Name]继续用触手肛交[npc2.herHim]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地将[npc2.her][npc2.hips+]猛然向后撞去，"
									+ "强迫[npc.name]将[npc.tentacle+(true)]深深插入[npc2.her][npc2.asshole+]，命令[npc.name]继续触手肛交[npc2.herHim]。"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "触手肛交(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "把你的[npc.tentacle(true)]温柔地在[npc2.namePos][npc2.asshole+]里抽插。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]温柔地将[npc.tentacle+(true)]深深插入[npc2.namePos][npc2.asshole+]，"
							+ "[npc.she]开始不断地抽插，慢慢地触手肛交[npc2.name]，每次推入都会响起一小声[npc.moan]。",

					"[npc.name]慢慢地将[npc.tentacle+(true)]插入[npc2.namePos][npc2.asshole+]，"
							+ "开始温柔地抽插，[npc.she]轻柔地用触手肛交[npc2.name]，不禁漏出一小声[npc.moan]。",

					"[npc.name]将[npc.tentacle+(true)]滑入[npc2.namePos][npc2.asshole+]，"
							+ "发出轻微的[npc.moan]，开始温柔地抽送打桩，一边呼吸着[npc2.namePos]的[npc2.scent]，一边慢慢地对[npc2.herHim]进行触手肛交。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "触手肛交";
		}

		@Override
		public String getActionDescription() {
			return "继续在[npc2.namePos][npc2.asshole+]里抽送你[npc.tentacle+(true)]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.tentacle+(true)]深深插入[npc2.namePos][npc2.asshole+]，"
							+ "[npc.name]开始亢奋地抽送打桩，快乐地用触手肛交[npc2.name]，每次推入都会发出[npc.a_moan+]。",

					"[npc.name]亢奋地将[npc.tentacle+(true)]深深插入[npc2.namePos][npc2.asshole+]，"
							+ "开始疯狂地抽插，贪婪地用触手肛交[npc2.name]，漏出[npc.a_moan+]。",

					"[npc.name]将[npc.tentacle+(true)]深深插入[npc2.namePos][npc2.asshole+]，"
							+ "[npc.Name]开始急切地抽送打桩，不时漏出一阵[npc.a_moan+]，[npc.she]一边嗅着[npc2.namePos]的[npc2.scent]，一边亢奋地用触手肛交[npc2.herHim]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "触手肛交(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "用你[npc.tentacle+(true)]粗暴地抽插[npc2.namePos][npc2.asshole+]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]粗暴地将[npc.tentacle+(true)]深深插入[npc2.namePos][npc2.asshole+]，"
							+ "[npc.name]开始粗暴地抽送打桩，野蛮地用触手肛交[npc2.name]，每次猛推都会发出[npc.a_moan+]。",

					"[npc.name]激烈地将[npc.tentacle+(true)]深深插入[npc2.namePos][npc2.asshole+]，"
							+ "开始粗暴地抽送打桩，激烈地用触手肛交[npc2.name]，漏出[npc.a_moan+]。",

					"[npc.name]激烈地将[npc.tentacle+(true)]深深插入[npc2.namePos][npc2.asshole+]，"
							+ "开始粗鲁地抽送打桩，[npc.she]嗅着[npc2.namePos]的[npc2.scent]，暴力地用触手肛交[npc2.name]，不时漏出[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "触手肛交";
		}

		@Override
		public String getActionDescription() {
			return "继续操[npc2.namePos][npc2.asshole+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.tentacle+(true)]深深插入[npc2.namePos][npc2.asshole+]，"
							+ "开始抽送打桩，快乐地用触手肛交[npc2.name]，每次推入都会发出[npc.a_moan+]。",

					"[npc.name]将[npc.tentacle+(true)]深深插入[npc2.namePos][npc2.asshole+]，"
							+ "开始抽插打桩，热情地用触手肛交[npc2.name]，漏出[npc.a_moan+]。",

					"[npc.name]将[npc.tentacle+(true)]深深插入[npc2.namePos][npc2.asshole+]，"
							+ "[npc.Name]开始抽送打桩，不时漏出一阵[npc.a_moan+]，[npc.she]一边嗅着[npc2.namePos]的[npc2.scent]，一边用触手肛交[npc2.herHim]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "触手肛交(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "让你[npc.tentacle+(true)]在[npc2.namePos][npc2.asshole+]里渴求地塞进拔出。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.tentacle+(true)]深深插入[npc2.namePos][npc2.asshole+]，"
							+ "[npc.name]开始亢奋地抽送打桩，快乐地用触手肛交[npc2.name]，每次推入都会发出[npc.a_moan+]。",

					"[npc.name]亢奋地将[npc.tentacle+(true)]深深插入[npc2.namePos][npc2.asshole+]，"
							+ "开始疯狂地抽插，贪婪地用触手肛交[npc2.name]，漏出[npc.a_moan+]。",

					"[npc.name]将[npc.tentacle+(true)]深深插入[npc2.namePos][npc2.asshole+]，"
							+ "[npc.Name]开始急切地抽送打桩，不时漏出一阵[npc.a_moan+]，[npc.she]一边嗅着[npc2.namePos]的[npc2.scent]，一边亢奋地用触手肛交[npc2.herHim]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抗拒触手肛交";
		}

		@Override
		public String getActionDescription() {
			return "努力把你的[npc.tentacle(true)]从[npc2.namePos][npc2.asshole+]里拔出来。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]尝试将[npc.tentacle(true)]从[npc2.namePos]的[npc2.assCloaca]里拔出来，"
									+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就回手轻轻地抓住它，然后温柔地把它扭回[npc2.her][npc2.asshole+]。",
	
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.tentacle(true)]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，"
									+ "[npc2.her]温柔地摩擦着[npc2.her][npc2.assCloaca]，并强硬地将它塞进[npc2.her][npc2.asshole+]。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.tentacle(true)]从[npc2.namePos][npc2.assCloaca+]上拉开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边温柔地将[npc2.asshole+]压向[npc.her][npc.tentacle+(true)]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]尝试将[npc.tentacle(true)]从[npc2.namePos]的[npc2.assCloaca]里拔出来，"
									+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就向后粗暴地抓住它，然后蛮横地把它扭回[npc2.her][npc2.asshole+]。",
	
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.tentacle(true)]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，"
									+ "[npc2.her]粗鲁地拍打着[npc2.her][npc2.assCloaca]，并强硬地将它塞进[npc2.her][npc2.asshole+]。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.tentacle(true)]从[npc2.namePos][npc2.assCloaca+]上拉开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边粗暴地将[npc2.asshole+]压向[npc.her][npc.tentacle+(true)]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]尝试将[npc.tentacle(true)]从[npc2.namePos]的[npc2.assCloaca]里拔出来，"
									+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就向后牢牢地抓住它，然后热切地把它扭回[npc2.her][npc2.asshole+]。",
	
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.tentacle(true)]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，"
									+ "[npc2.her]急切地摩擦着[npc2.her][npc2.assCloaca]，并强硬地将它塞进[npc2.her][npc2.asshole+]。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.tentacle(true)]从[npc2.namePos][npc2.assCloaca+]上拉开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边将[npc2.asshole+]压向[npc.her][npc.tentacle+(true)]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止触手肛交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.tentacle+(true)]从[npc2.namePos][npc2.asshole+]里拔出来，停止和[npc2.herHim]触手肛交。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将[npc.tentacle+(true)]从[npc2.namePos][npc2.assCloaca+]中拉出，"
									+ "霸道地用[npc.tentacleTip+]最后一次上下磨蹭[npc2.her][npc2.asshole+]，然后将它移开。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.tentacle+(true)]从[npc2.her][npc2.asshole+]中猛抽出来，结束了粗暴触手肛交。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]将[npc.tentacle(true)]滑出[npc2.namePos][npc2.assCloaca+]，在最后一次用[npc.tentacleTip]来回磨蹭[npc2.her][npc2.asshole+]后，将[npc2.her]推开。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.tentacle+(true)]从[npc2.her][npc2.asshole+]中拔出来，结束了触手肛交。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.Name]从[npc2.her]的[npc2.asshole]里拔出来时，[npc2.name]忍不住漏出一声[npc2.sob+]，"
										+ "[npc2.she]仍然不停地哭泣，无力地继续反抗着[npc.name]。",
	
								"[npc2.name]发出一阵[npc2.a_sob+]，拼命地嘶哑着反抗[npc.Name]，将[npc2.asshole+]从[npc.Name]身上移开。泪水已经忍不住像小溪一样从[npc2.face]上流了下来。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.name]将[npc.tentacle+(true)]拔出[npc2.her][npc2.asshole+]时，[npc2.Name]发出[npc2.a_moan+]，渴求[npc.name]的更多“照顾”。",
	
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "开始被触手肛交";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.namePos][npc2.tentacle+(true)]插入你[npc.asshole+]，接受触手肛交。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tentacle(true)]，慢慢地将它引导到自己[npc.assCloaca+]，"
									+ "[npc.her]轻声[npc.moan]，温柔地翘起[npc.hips]，迫使[npc2.herHim]插入[npc.her][npc.asshole+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.tentacle(true)]，把它对准自己[npc.asshole+]，"
									+ "慢慢地将[npc.hips]向后顶，将[npc2.her][npc2.tentacle+(true)]插入自己，不禁漏出一声轻柔的[npc.moan]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tentacle(true)]，急切地将它引导到自己[npc.assCloaca+]，"
									+ "[npc.her]饥渴地猛烈摇晃并且向后撅[npc.hips]，不时漏出一阵[npc.a_moan+]，强迫[npc2.herHim]插入[npc.her][npc.asshole+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.tentacle(true)]，把它对准自己[npc.asshole+]，"
									+ "[npc.she]热切地向后顶[npc.hips]，吞下[npc2.her][npc2.tentacle+(true)]，[npc.she]不禁漏出一声[npc.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tentacle(true)]，粗暴地将它抓到自己[npc.assCloaca+]，"
									+ "[npc.her]暴力地猛烈摇晃并且向后撅[npc.hips]，不时漏出一阵[npc.a_moan+]，强迫[npc2.herHim]插入[npc.her][npc.asshole+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.tentacle(true)]，把它对准自己[npc.asshole+]，"
									+ "[npc.she]急切地向后猛撅[npc.hips]，裹住[npc2.her][npc2.tentacle+(true)]，[npc.she]不禁漏出一声[npc.a_moan+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tentacle(true)]，将它引导到自己[npc.assCloaca+]，"
									+ "[npc.her]向后摇晃着[npc.hips]，不时漏出一阵[npc.a_moan+]，强迫[npc2.herHim]插入[npc.her][npc.asshole+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.tentacle(true)]，把它对准自己[npc.asshole+]，"
									+ "[npc.she]向后顶[npc.hips]，吞下[npc2.her][npc2.tentacle+(true)]，[npc.she]不禁漏出一声[npc.a_moan+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.herHim]的身体，发出一声轻柔的[npc2.moan]，"
										+ "[npc2.she]温柔地将[npc2.tentacle(true)]向前顶，开始触手交[npc.namePos][npc.asshole+]。",
	
								"[npc2.name]轻柔地[npc2.moan]，温柔地挺进自己的[npc2.tentacle(true)]，"
										+ "将它深深插进[npc.namePos][npc.asshole+]，开始用触手肛交[npc.herHim]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "[npc2.she]热切地将[npc2.tentacle(true)]向前顶，开始亢奋地触手交[npc.namePos][npc.asshole+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，热切地挺进自己的[npc2.tentacle(true)]，"
										+ "将它深深插进[npc.namePos][npc.asshole+]，开始竭力地用触手肛交[npc.herHim]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "为了警告[npc.name]别得意忘形，[npc2.she]粗暴地向前猛撅[npc2.tentacle(true)]，开始无情地用触手暴操[npc.her][npc.asshole+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，粗暴地猛插自己的[npc2.tentacle(true)]，"
										+ "[npc2.she]无情地用触手暴操[npc.namePos][npc.asshole+]，宣告着自己的支配权。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]挺进[npc.name]的身体，不禁漏出一声[npc2.a_moan+]，[npc2.she]将[npc2.tentacle(true)]向前顶，开始触手交[npc.namePos][npc.asshole+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，向前挺进[npc2.tentacle(true)]，将它深深插入[npc.namePos][npc.asshole+]，开始触手肛交[npc.herHim]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]紧紧裹住[npc2.name]的[npc2.tentacle(true)]，[npc2.name]不禁发出一声[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着推开[npc.name]，拼命地想把自己[npc2.tentacle+(true)]从对方[npc.asshole+]里拔出来。",
	
								"[npc.Name]强行将[npc2.name]的[npc2.tentacle(true)]深深插入自己[npc.asshole+]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
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
							"[npc2.Name]贪婪地将自己[npc2.tentacle+(true)]深深插入[npc.namePos][npc.asshole+]，"
									+ "[npc2.she]发出[npc2.a_moan+]，亢奋地插入[npc.namePos][npc.assCloaca+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.tentacle+(true)]深深插入[npc.namePos][npc.asshole+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，热切地挺动[npc2.her][npc2.tentacle+(true)]，拼命要插入[npc.namePos][npc.asshole+]最深处。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.tentacle(true)]从[npc.namePos]的[npc.assCloaca]中抽出，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.tentacle+(true)]在自己[npc.asshole+]中继续抽插。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.tentacle(true)]从[npc.namePos][npc.asshole+]中抽离。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将[npc2.tentacle+(true)]滑入[npc.namePos][npc.asshole+]深处，"
									+ "[npc2.she]发出柔和的[npc2.moan]，轻柔地抽插[npc.namePos][npc.assCloaca+]。",
		
							"[npc2.namePos]的唇间飘出一阵[npc2.A_moan+]，[npc2.she]慢慢地将[npc2.tentacle+(true)]深深插入[npc.namePos][npc.asshole+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地用[npc2.tentacle+(true)]深入[npc.namePos][npc.asshole+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.tentacle+(true)]深深插入[npc.namePos][npc.asshole+]，"
									+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地插入[npc.namePos][npc.assCloaca+]。",
		
							"[npc2.namePos]的唇间飘出一阵[npc2.A_moan+]，[npc2.she]猛烈地将[npc2.tentacle+(true)]深深插入[npc.namePos][npc.asshole+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地挺动[npc2.her][npc2.tentacle+(true)]，拼命要插入[npc.namePos][npc.asshole+]最深处。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.tentacle+(true)]深深插入[npc.namePos][npc.asshole+]，"
									+ "[npc2.she]发出一阵[npc2.a_moan+]，不停抽插着[npc.namePos][npc.assCloaca+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.tentacle+(true)]深深插入[npc.namePos][npc.asshole+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.tentacle+(true)]深深插入[npc.namePos][npc.asshole+]。"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "接受触手肛交(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地用你[npc.asshole+]吞吐着[npc2.namePos][npc2.tentacle+(true)]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声轻柔的[npc.moan]，温柔地将自己的[npc.hips]向后拱起，以便[npc2.namePos][npc2.tentacle+(true)]可以在自己[npc.asshole+]中插得更深。",

					"[npc.Name]轻柔地[npc.moan]，温柔地向后拱着[npc.hips]，使得[npc2.namePos][npc2.tentacle+(true)]在自己[npc.asshole+]中插得更深。",

					"一边慢慢地将自己的[npc.hips]往后送，"
							+ "[npc.lips+]间飘出一声轻柔的[npc.moan]，[npc.her]设法让[npc2.namePos][npc2.tentacle+(true)]深深插入[npc.her][npc.asshole+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "触手肛交";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地用你[npc.asshole+]吞吐[npc2.namePos][npc2.tentacle+(true)]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，热切地向后拱[npc.hips]，以便[npc2.namePos][npc2.tentacle+(true)]可以在自己[npc.asshole+]中插得更深。",

					"[npc.Name]发出一阵[npc.a_moan+]，亢奋地向后拱起[npc.hips]，强迫[npc2.namePos][npc2.tentacle+(true)]在自己[npc.asshole+]中插得更深。",

					"[npc.name]积极地向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.tentacle+(true)]插入自己[npc.asshole+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "接受触手肛交(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地用你[npc.asshole+]吞下[npc2.namePos][npc2.tentacle+(true)]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，暴力地将自己的[npc.hips]向后猛撞，强迫[npc2.namePos][npc2.tentacle+(true)]在自己[npc.asshole+]中插得更深。",

					"[npc.Name]发出一阵[npc.a_moan+]，粗暴地向后猛撞[npc.hips]，强迫[npc2.namePos][npc2.tentacle+(true)]在自己[npc.asshole+]中插得更深。",

					"[npc.name]激烈地向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]粗暴地强迫[npc2.namePos][npc2.tentacle+(true)]插入自己[npc.asshole+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_TENTACLE_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "触手肛交";
		}

		@Override
		public String getActionDescription() {
			return "用你[npc.asshole+]吞吐着[npc2.namePos][npc2.tentacle+(true)]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，向后拱[npc.hips]，以便[npc2.namePos][npc2.tentacle+(true)]更深地没入自己[npc.asshole+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，向后拱起[npc.hips]，强迫[npc2.namePos][npc2.tentacle+(true)]在自己[npc.asshole+]中插得更深。",

					"[npc.name]向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.tentacle+(true)]插入自己[npc.asshole+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "触手肛交(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地用你[npc.asshole+]吞吐[npc2.namePos][npc2.tentacle+(true)]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，热切地向后拱[npc.hips]，以便[npc2.namePos][npc2.tentacle+(true)]可以在自己[npc.asshole+]中插得更深。",

					"[npc.Name]发出一阵[npc.a_moan+]，亢奋地向后拱起[npc.hips]，强迫[npc2.namePos][npc2.tentacle+(true)]在自己[npc.asshole+]中插得更深。",

					"[npc.name]积极地向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.tentacle+(true)]插入自己[npc.asshole+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗被触手肛交";
		}

		@Override
		public String getActionDescription() {
			return "努力让你[npc.asshole+]远离[npc2.namePos][npc2.tentacle+(true)]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
									+ "[npc.she]无力地试着将[npc2.namePos][npc2.tentacle(true)]从自己[npc.asshole+]里拔出来。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.assCloaca]从[npc2.namePos]令人憎恶的性器抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.tentacle+(true)]依然从容地在[npc.her][npc.asshole+]里滑进滑出。",

							"[npc.name]拼命地尝试把[npc.hips]挪开，"
									+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.tentacle+(true)]依然温柔地滑进[npc.her][npc.asshole+]深处。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
									+ "[npc.she]无力地试着将[npc2.namePos][npc2.tentacle(true)]从自己[npc.asshole+]里拔出来。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.assCloaca]从[npc2.namePos]令人憎恶的性器抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.tentacle+(true)]依然疯狂地在[npc.her][npc.asshole+]里抽送爆操。",

							"[npc.name]拼命地尝试把[npc.hips]挪开，"
									+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.tentacle+(true)]依然贪婪地插入[npc.her][npc.asshole+]深处。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
									+ "[npc.she]无力地试着将[npc2.namePos][npc2.tentacle(true)]从自己[npc.asshole+]里拔出来。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.assCloaca]从[npc2.namePos]令人憎恶的性器抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.tentacle+(true)]依然粗暴地在[npc.her][npc.asshole+]里抽送爆操。",

							"[npc.name]拼命地尝试把[npc.hips]挪开，"
									+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.tentacle+(true)]依然激烈地插入[npc.her][npc.asshole+]深处。"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "停止触手肛交";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.her]的[npc2.tentacle(true)]从你[npc.asshole+]里拔出来。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]的[npc2.tentacle(true)]从自己[npc.asshole+]里抽出，[npc.she]愤怒地咆哮着，命令[npc2.name]不准再操了。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]的[npc2.tentacle(true)]从自己[npc.asshole+]中抽出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]的[npc2.tentacle(true)]从自己[npc.asshole+]中抽出，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]不要再操了。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc2.her]的[npc2.tentacle(true)]从自己[npc.asshole+]中抽出。"));
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
								"[npc.Name]不再让[npc2.name]操自己[npc.asshole+]，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
	
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续抽插[npc.namePos][npc.asshole+]的渴望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "收紧肛门";
		}

		@Override
		public String getActionDescription() {
			return "用你肌肉发达的肛门挤弄包裹着[npc2.namePos]的[npc2.tentacle(true)]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getAssOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]发出一阵[npc.a_moan+]，继续专心用[npc.her]肌肉极致发达的[npc.asshole]挤弄包裹着[npc2.namePos][npc2.tentacle+(true)]。",

					(!isTargetedCharacterInanimate()
						?"[npc.Name]发出一阵[npc.a_moan+]，继续专心控制自己[npc.asshole]内部极致发达的肌肉。"
							+ "[npc.Name]挤弄包裹着[npc2.namePos][npc2.tentacle+(true)]，让[npc2.herHim]不禁漏出一声愉悦的呻吟。"
						:""),

					"[npc.Name]以稳定的节奏[npc.moans]着，[npc.she]继续专心"
							+ "用[npc.her]肌肉极致发达且[npc.asshole+]挤弄包裹着[npc2.namePos][npc2.tentacle+(true)]。",

					"[npc.Name]发出一阵[npc.a_moan+]，专心控制[npc.asshole]内里极致发达的肌肉，"
							+ "[npc.she]挤弄按摩着[npc2.namePos][npc2.tentacle+(true)]时，不禁愉悦地尖叫一声。");
		}
	};
}
