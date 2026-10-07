package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
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
 * @version 0.3.8.8
 * @author Innoxia
 */
public class PenisVagina {

	// -- Methods for multiple ongoing characters:
	
	static List<GameCharacter> getOngoingCharacters(GameCharacter characterReceivingDP) {
		return new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterReceivingDP, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
	}

	private static List<GameCharacter> getCharactersForParsing(GameCharacter characterReceivingDP) {
		List<GameCharacter> characters = Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
		for(GameCharacter c : getOngoingCharacters(characterReceivingDP)) {
			if(!characters.contains(c)) {
				characters.add(c);
			}
		}
		return characters;
	}
	
	public static GameCharacter getPrimaryDPPerformer(GameCharacter characterReceivingDP) {
		if(Main.sex.getOngoingActionsMap(characterReceivingDP).get(SexAreaOrifice.VAGINA).isEmpty()) {
			return null;
		}
		return Main.sex.getOngoingActionsMap(characterReceivingDP).get(SexAreaOrifice.VAGINA).keySet().iterator().next();
	}
	
	// ---
	
	public static final SexAction TEASE_PENIS_OVER_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "挑逗小穴";
		}

		@Override
		public String getActionDescription() {
			return "挑逗[npc2.name]，把你[npc.cock]的[npc.cockHead]在[npc2.her][npc2.pussy+]口蹭来蹭去。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).isEmpty()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"[npc.Name]无视了[npc3.namePos]的[npc3.cock]已经深深插入[npc2.namePos][npc2.pussy+]的事实，"
										+ "[npc.name]用[npc.cock+][npc.cockTip+]缓缓地磨蹭[npc2.her][npc2.labia+]，威胁着随时会双插[npc.herHim]。",
								"[npc.name]轻声[npc.moan]，温柔地把[npc.cock][npc.cockTip+]扶到[npc2.namePos]春光微露的[npc2.labia+]旁，"
										+ "表露出[npc.she]想加入[npc3.name]双插[npc2.pussy+]的念头。",
								"[npc.name]温柔地用[npc.cock][npc.cockTip+]上下挑逗[npc2.namePos][npc2.pussy+]裸露的部分，"
										+ "[npc.she]发出一阵[npc.a_moan+]，示意[npc.she]想要加入[npc3.name]双插[npc2.name]。")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"[npc.Name]无视了[npc3.namePos]的[npc3.cock]已经深深插入[npc2.namePos][npc2.pussy+]的事实，"
										+ "[npc.name]用[npc.cock+][npc.cockTip+]粗暴地磨蹭[npc2.her][npc2.labia+]，威胁着随时会双插[npc.herHim]。",
								"[npc.name]发出一阵[npc.a_moan+]，激烈地用[npc.cock][npc.cockTip+]抵住[npc2.namePos][npc2.labia+]裸露的部分磨蹭，"
										+ "表露出[npc.she]想加入[npc3.name]双插[npc2.pussy+]的念头。",
								"[npc.name]粗暴地用[npc.cock][npc.cockTip+]来回磨蹭[npc2.namePos][npc2.pussy+]裸露的部分，"
										+ "[npc.she]发出一阵[npc.a_moan+]，示意[npc.she]想要加入[npc3.name]双插[npc2.name]。")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"[npc.Name]无视了[npc3.namePos]的[npc3.cock]已经深深插入[npc2.namePos][npc2.pussy+]的事实，"
										+ "[npc.name]用[npc.cock+][npc.cockTip+]磨蹭[npc2.her][npc2.labia+]，威胁着随时会双插[npc.herHim]。",
								"[npc.name]发出一阵[npc.a_moan+]，把[npc.cock][npc.cockTip+]抵住[npc2.namePos][npc2.labia+]裸露的部分磨蹭，"
										+ "表露出[npc.she]想加入[npc3.name]双插[npc2.pussy+]的念头。",
								"[npc.name]用[npc.cock][npc.cockTip+]来回磨蹭[npc2.namePos][npc2.pussy+]裸露的部分，"
										+ "[npc.she]发出一阵[npc.a_moan+]，示意[npc.she]想要加入[npc3.name]双插[npc2.name]。")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"[npc.Name]无视了[npc3.namePos]的[npc3.cock]已经深深插入[npc2.namePos][npc2.pussy+]的事实，"
										+ "[npc.name]用[npc.cock+][npc.cockTip+]饥渴地磨蹭[npc2.her][npc2.labia+]，威胁着随时会双插[npc.herHim]。",
								"[npc.name]发出一阵[npc.a_moan+]，亢奋地用[npc.cock][npc.cockTip+]抵住[npc2.namePos][npc2.labia+]裸露的部分磨蹭，"
										+ "表露出[npc.she]想加入[npc3.name]双插[npc2.pussy+]的念头。",
								"[npc.name]饥渴地用[npc.cock][npc.cockTip+]来回磨蹭[npc2.namePos][npc2.pussy+]裸露的部分，"
										+ "[npc.she]发出一阵[npc.a_moan+]，示意[npc.she]想要加入[npc3.name]双插[npc2.name]。")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]将自己[npc.cock+]顶向[npc2.namePos][npc2.pussy+]，"
										+ "开始缓慢地用[npc.cockTip+]上下挑逗[npc2.her][npc2.labia+]，随时准备插入[npc2.herHim]。",
								"[npc.name]轻声[npc.moan]，把自己[npc.cock+]移向[npc2.namePos][npc2.pussy+]，温柔地用[npc.cockTip]来回磨蹭[npc2.her][npc2.labia+]。",
								"[npc.name]温柔地用[npc.cock][npc.cockTip+]上下挑逗[npc2.namePos][npc2.pussy+]，"
										+ "一想到只要[npc.she]乐意，就可以随时插入[npc2.herHim]的身体，[npc.name]发出了轻柔的[npc.moan]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]用[npc.cock+]磨蹭着[npc2.namePos][npc2.pussy+]，"
										+ "接着向后退了一点，开始用[npc.cockTip+]上下挑逗[npc2.her][npc2.labia+]，随时准备插入[npc2.herHim]。",
								"[npc.name]发出一阵[npc.a_moan+]，把[npc.cock+]挪向[npc2.namePos][npc2.pussy+]，"
										+ "然后开始粗暴地用[npc.cockTip]上下磨蹭[npc2.namePos][npc2.labia+]。",
								"[npc.name]用[npc.cock][npc.cockTip+]粗暴地来回磨蹭[npc2.namePos][npc2.pussy+]，"
										+ "一想到只要[npc.she]高兴随时可以操干[npc2.name]，[npc.name]就发出了一阵[npc.a_moan+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]将自己[npc.cock+]顶向[npc2.namePos][npc2.pussy+]，"
										+ "开始用[npc.cockTip+]上下挑逗[npc2.her][npc2.labia+]，随时准备插入[npc2.herHim]。",
								"[npc.name]发出一阵[npc.a_moan+]，把[npc.cock+]扶到[npc2.namePos][npc2.pussy+]旁，用[npc.cockTip]来回磨蹭[npc2.her][npc2.labia+]。",
								"用[npc.cock][npc.cockTip+]挑逗[npc2.namePos][npc2.pussy+]，"
										+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了一阵[npc.a_moan+]。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]将自己[npc.cock+]顶向[npc2.namePos][npc2.pussy+]，"
										+ "开始急切地用[npc.cockTip+]上下挑逗[npc2.her][npc2.labia+]，随时准备插入[npc2.herHim]。",
								"[npc.name]发出一阵[npc.a_moan+]，把[npc.cock]扶到[npc2.namePos][npc2.pussy+]旁，在[npc2.her][npc2.labia+]上饥渴地磨蹭着[npc.cockTip]。",
								"[npc.name]急切地用[npc.cock][npc.cockTip+]上下挑逗[npc2.namePos][npc2.pussy+]，"
										+ "一想到只要[npc.she]乐意随时可以插入[npc2.herHim]的身体，[npc.name]就发出了一阵[npc.a_moan+]。"));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				if((Main.sex.getCharacterTargetedForSexAction(this).isVaginaVirgin() || Main.sex.getCharacterTargetedForSexAction(this).hasHymen()) && Main.sex.getCharacterTargetedForSexAction(this).hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
						switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
							case SUB_RESISTING:
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"一想到自己即将要被夺走贞操，[npc2.namePos][npc2.lips+]间不禁爆发出一阵[npc2.A_sob+]。",
										"[npc2.Name]意识到自己可能将失去贞操，不禁发出一声绝望的[npc2.sob]。",
										"一想到自己马上要被[npc.namePos][npc.cock+]夺走贞操，[npc2.name]就不禁绝望地[npc2.sobVerb]着。"));
								break;
							default:
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.name]把贞洁看得比什么都重，[npc2.she]将[npc2.pussy]推离[npc.namePos][npc.cock+]。",
										"[npc2.name]因将要失去宝贵的贞洁而恐惧不已，[npc2.she]将[npc2.pussy]推离[npc.namePos][npc.cock+]。",
										"[npc2.name]躲开了[npc.namePos]的触摸，将[npc2.pussy]推离[npc.namePos][npc.cock+]，以此来明确[npc2.she]还不想失去贞洁。"));
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
									"[npc2.namePos]感觉到[npc2.pussy+]传来的阵阵快感，一声轻柔的[npc2.moan]从[npc2.her][npc2.lips+]间飘出，"
											+"[npc2.she]温柔地将[npc2.pussy+]推向[npc.namePos]的[npc.cock]。",
									"[npc2.name]发出一声柔和的[npc2.moan]，随后温柔地将[npc2.pussy+]贴向[npc.namePos]的[npc.cock]。",
									"[npc2.name]感受到[npc.namePos]的[npc.cock]正刺激着[npc2.her][npc2.pussy+]，[npc2.she]愉悦地[npc2.moanVerb]着，并温柔地扭动[npc2.her][npc2.hips+]作为回应。"));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos]感受到[npc2.pussy+]传来阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间漏出，"
											+"[npc2.she]饥渴地将[npc2.labia+]推向[npc.namePos]的[npc.cock]。",
									"[npc2.name]发出一声迷乱的[npc2.moan]，然后迫不及待地将[npc.namePos]的[npc.cock]塞入自己[npc2.pussy+]。",
									"[npc2.name]感受到[npc.namePos]的[npc.cock]正刺激着[npc2.her][npc2.pussy+]，[npc2.she]愉悦地[npc2.moanVerb]着，并饥渴地扭动[npc2.her][npc2.hips+]作为回应。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos]感受到[npc2.pussy+]传来阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间漏出，"
											+"[npc2.she]粗暴地用[npc2.labia+]抵住[npc.namePos]的[npc.cock]。",
									"[npc2.name]发出一声迷乱的[npc2.moan]，然后粗暴地将[npc.namePos]的[npc.cock]塞入自己[npc2.pussy+]。",
									"[npc2.name]感受到[npc.namePos]的[npc.cock]正刺激着[npc2.her][npc2.pussy+]，[npc2.she]愉悦地[npc2.moanVerb]着，并粗暴地扭动[npc2.her][npc2.hips+]作为回应。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos]感受到[npc2.pussy+]传来阵阵快感，一阵[npc2.A_moan+]从[npc2.her][npc2.lips+]间漏出，"
											+"[npc2.she]将[npc2.labia+]推向[npc.namePos]的[npc.cock]。",
									"[npc2.name]发出一声[npc2.moan]，然后将[npc2.her][npc2.pussy+]推向[npc.namePos]的[npc.cock]。",
									"[npc2.name]感受到[npc.namePos]的[npc.cock]正刺激着[npc2.her][npc2.pussy+]，[npc2.she]愉悦地[npc2.moanVerb]着，并扭动[npc2.her][npc2.hips+]作为回应。"));
							break;
						case SUB_RESISTING:
							if(Main.sex.getCharacterTargetedForSexAction(this).isVaginaVirgin()) {
								if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											"一想到自己即将要被夺走贞操，[npc2.namePos][npc2.lips+]间不禁爆发出一阵[npc2.A_sob+]。",
											"[npc2.Name]意识到自己可能将失去贞操，不禁发出一声绝望的[npc2.sob]。",
											"一想到自己马上要被[npc.namePos][npc.cock+]夺走贞操，[npc2.name]就不禁绝望地[npc2.sobVerb]着。"));
									
								} else {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											"[npc2.namePos]想到接下来要发生的事，[npc2.lips+]间不禁爆发出一阵[npc2.A_sob+]，[npc2.speech(不！别这么做！我还是处！)]",
											"[npc2.Name]发出一声绝望的[npc2.sob]，苦苦乞求，[npc2.speech(不要这样！求你了！我还是处！)]",
											"[npc2.Name]一想到接下来要发生的事情，不禁痛苦地[npc2.sobsVerb]，绝望地哀求着，[npc2.speech(不要！快停手！我不想被夺走第一次！)]"));
								}
								
							} else {
								if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.pussy]从[npc.namePos]的[npc.cock]抽离。",
											"[npc2.Name]不断反抗着[npc.Name]，不禁发出绝望的[npc2.sob]。",
											"[npc2.name]痛苦地[npc2.sobVerb]着，试图远离[npc.namePos][npc.cock+]。"));
									
								} else {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.pussy]从[npc.namePos]的[npc.cock]抽离，"
													+ "[npc2.speech(不！不可以！求求你了，放过我吧！)]",
											"[npc2.Name]发出一声绝望的[npc2.sob]，苦苦乞求，[npc2.speech(求求你！不要这样做！放过我！)]",
											"[npc2.Name]痛苦地[npc2.sobsVerb]，[npc2.she]哀求道，[npc2.speech(不要！快停下！求求你快走！)]"));
								}
							}
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
	};
	
	public static final SexAction FORCE_PENIS_OVER_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "挑逗阴茎";
		}

		@Override
		public String getActionDescription() {
			return "挑逗[npc2.name]，把[npc2.her][npc2.cock][npc2.cockTip]在你[npc.pussy+]口蹭来蹭去。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			boolean canReachPenis = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaPenetration.PENIS)) {
					canReachPenis = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaPenetration.PENIS).contains(SexAreaPenetration.FINGER)) {
					canReachPenis = true;
				}
			} catch(Exception ex) {
			}
			if(!canReachPenis) { // No available finger-penis actions, so can't reach penis
				return false;
			}
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			if(!getOngoingCharacters(Main.sex.getCharacterPerformingAction()).isEmpty()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(
								UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"[npc.Name]对只有[npc3.namePos]的[npc3.cock]插入[npc.herHim]这件事感到并不满足，又握住了[npc2.namePos][npc2.cock+]，"
										+"随后引导它到[npc.her][npc.pussy]的裸露部位并温柔地在[npc.her][npc.labia+]之间挑逗[npc.cockHead]。",
								"[npc.name]不满足于自己[npc.pussy+]只被[npc3.namePos][npc3.cock+]填满，"
										+ "[npc.she]温柔地握住[npc2.namePos][npc2.cock+]，然后开始用[npc2.cockTip]上下磨蹭着[npc.her][npc.labia+]外露的部分。",
								"[npc3.namePos][npc3.cock+]仍然在[npc.namePos][npc.pussy+]内进进出出，"
										+ "[npc.Name]抓住[npc2.namePos][npc2.cock+]，然后温柔地用[npc2.cockTip+]磨蹭[npc.her][npc.labia+]外露的部分。")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append
						(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"[npc.Name]对只有[npc3.namePos]的[npc3.cock]插入[npc.herHim]这件事感到并不满足，又粗暴地抓住了[npc2.namePos][npc2.cock+]，"
										+"随后引导它到[npc.her][npc.pussy]的裸露部位并激烈地在[npc.her][npc.labia+]之间摩擦[npc.cockHead]。",
								"[npc.name]不满足于自己[npc.pussy+]只被[npc3.namePos][npc3.cock+]填满，"
										+ "[npc.she]粗暴地逮住[npc2.namePos][npc2.cock+]，然后开始激烈地用[npc2.cockTip]上下磨蹭着[npc.her][npc.labia+]外露的部分。",
								"[npc3.namePos][npc3.cock+]仍然在[npc.namePos][npc.pussy+]内进进出出，"
										+ "[npc.Name]有力地抓住[npc2.namePos][npc2.cock+]，然后粗暴地用[npc2.cockTip+]磨蹭[npc.her][npc.labia+]外露的部分。")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(
								UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"[npc.Name]对只有[npc3.namePos]的[npc3.cock]插入[npc.herHim]这件事感到并不满足，又握住了[npc2.namePos][npc2.cock+]，"
										+"随后引导它到[npc.her][npc.pussy]的裸露部位并在[npc.her][npc.labia+]之间摩擦[npc.cockHead]。",
								"[npc.name]不满足于自己[npc.pussy+]只被[npc3.namePos][npc3.cock+]填满，"
										+ "[npc.she]握住[npc2.namePos][npc2.cock+]，然后开始用[npc2.cockTip]上下磨蹭着[npc.her][npc.labia+]外露的部分。",
								"[npc3.namePos][npc3.cock+]仍然在[npc.namePos][npc.pussy+]内进进出出，"
										+ "[npc.name]抓住[npc2.namePos][npc2.cock+]，然后用[npc2.cockTip+]磨蹭[npc.her][npc.labia+]外露的部分。")));
						break;
					default:
						UtilText.nodeContentSB.append(
								UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"[npc.name]对只有[npc3.namePos]的[npc3.cock]插入[npc.herHim]这件事感到并不满足，又急切地抓住了[npc2.namePos][npc2.cock+]，"
										+"随后引导它到[npc.her][npc.pussy]的裸露部位并急切地在[npc.her][npc.labia+]之间摩擦[npc.cockHead]。",
								"[npc.name]不满足于自己[npc.pussy+]只被[npc3.namePos][npc3.cock+]填满，"
										+ "[npc.Name]饥渴地逮住[npc2.namePos][npc2.cock+]，然后开始竭力地用[npc2.cockTip]上下磨蹭着[npc.her][npc.labia+]外露的部分。",
								"[npc3.namePos][npc3.cock+]仍然在[npc.namePos][npc.pussy+]内进进出出，"
										+ "[npc.name]急切地抓住[npc2.namePos][npc2.cock+]，然后愉悦地用[npc2.cockTip+]磨蹭[npc.her][npc.labia+]外露的部分。")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]抓住[npc2.namePos][npc2.cock+]，把它挪到自己[npc.pussy+]。"
										+ "[npc.she]缓慢地用[npc2.cockTip+]上下磨蹭[npc.her][npc.labia+]，挑逗[npc2.name]随时可以插入。",
								"[npc.name]轻声[npc.moan]，抓住[npc2.namePos][npc2.cock+]，将它移向自己[npc.pussy+]，"
										+ "然后开始温柔地用[npc2.cockTip]上下磨蹭[npc.her][npc.labia+]。",
								"[npc.Name]抓住[npc2.namePos][npc2.cock+]，温柔地用[npc2.cockTip+]磨蹭自己[npc.pussy+]，"
										+ "[npc.she]发出一声轻柔的[npc.moan]，挑逗[npc2.Name]随时可能插入。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]抓住[npc2.namePos][npc2.cock+]，把它猛拉到自己[npc.pussy+]。"
										+ "[npc.she]粗暴地强迫[npc2.cockTip+]上下磨蹭[npc.her][npc.labia+]，挑逗[npc2.name]随时可能插入。",
								"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.cock+]并把它拉扯到自己[npc.pussy+]，"
										+ "然后开始粗暴地强迫[npc2.cockTip]上下磨蹭[npc.her][npc.labia+]。",
								"[npc.Name]抓住[npc2.namePos][npc2.cock+]，粗暴地用[npc2.cockTip+]磨蹭自己[npc.pussy+]，"
										+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]抓住[npc2.namePos][npc2.cock+]，把它挪到自己[npc.pussy+]。"
										+ "[npc.she]用[npc2.cockTip+]上下磨蹭[npc.her][npc.labia+]，挑逗[npc2.name]随时可能插入。",
								"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.cock+]并引导它到自己[npc.pussy+]，"
										+ "然后开始用[npc2.cockTip]上下磨蹭[npc.her][npc.labia+]。",
								"[npc.Name]抓住[npc2.namePos][npc2.cock+]，用[npc2.clitTip+]磨蹭自己[npc.pussy+]，"
										+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]抓住[npc2.namePos][npc2.cock+]，把它挪到自己[npc.pussy+]。"
										+ "[npc.she]急切地用[npc2.cockTip+]上下磨蹭[npc.her][npc.labia+]，挑逗[npc2.name]随时可以插入。",
								"[npc.Name]发出一声[npc.a_moan+]，抓住[npc2.namePos][npc2.cock+]并引导它到自己[npc.pussy+]，"
										+ "然后开始急切地用[npc2.cockTip]上下磨蹭[npc.her][npc.labia+]。",
								"[npc.Name]抓住[npc2.namePos][npc2.cock+]，急切地用[npc2.cockTip+]磨蹭自己[npc.pussy+]，"
										+ "[npc.she]发出一声[npc.a_moan+]，挑逗[npc2.name]随时可能插入。"));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]温柔地用[npc2.cock]上下磨蹭[npc.namePos][npc.pussy+]，一声轻柔的[npc2.moan]从[npc2.her][npc2.lips+]间飘出。",
	
								"[npc2.name]发出一声柔和的[npc2.moan]，随后温柔地用[npc2.cock]前后磨蹭[npc.namePos][npc.pussy+]。",
	
								"[npc2.name]感受到[npc2.cock]传来阵阵快感，不禁发出兴奋的[npc2.moans]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.cock]温柔地上下磨蹭[npc.namePos][npc.pussy+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]粗暴地用[npc2.cock]来回磨蹭[npc.namePos][npc.pussy+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.Name]发出一阵[npc2.a_moan+]，激烈地用[npc2.cock]来回磨蹭[npc.namePos][npc.pussy+]。",
	
								"[npc2.Name]感觉到[npc2.cock]传来阵阵快感，忘我地发出愉悦的[npc2.moan]，为了提醒[npc.name]谁才是主导者，"
										+ "[npc2.she]开始用[npc2.cock]粗暴地上下磨蹭[npc.namePos][npc.pussy+]。"));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]热切地用[npc2.cock]来回磨蹭[npc.namePos][npc.pussy+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.name]发出一声迷乱的[npc2.moan]，然后迫不及待地用[npc2.cock]前后磨蹭[npc.namePos][npc.pussy+]。",
	
								"[npc2.name]感受到[npc2.cock]传来阵阵快感，不禁发出兴奋的[npc2.moans]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.cock]急切地上下磨蹭[npc.namePos][npc.pussy+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]用[npc2.cock]来回磨蹭[npc.namePos][npc.pussy+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.name]发出一声迷乱的[npc2.moan]，然后用[npc2.cock]前后磨蹭[npc.namePos][npc.pussy+]。",
	
								"[npc2.name]感受到[npc2.cock]传来阵阵快感，不禁发出兴奋的[npc2.moans]，看来不需要做进一步的前戏了，"
										+ "[npc2.she]开始用[npc2.cock]上下磨蹭[npc.namePos][npc.pussy+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图将[npc2.cock+]从[npc.namePos][npc.pussy+]中拔出来。",
	
								"[npc2.Name]发出一声绝望的[npc2.sob]，然后拼命地尝试将[npc2.cock+]从[npc.namePos][npc.labia+]中拔出来。",
	
								"[npc2.Name]痛苦地[npc2.sobsVerb]，并哀求[npc.name]放过自己的[npc2.cock]。"));
						break;
				}
				if((Main.sex.getCharacterPerformingAction().isVaginaVirgin() || Main.sex.getCharacterPerformingAction().hasHymen()) && Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					if(Main.sex.getCharacterPerformingAction().isMute()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]把贞洁看得比什么都重，[npc.she]迅速地将[npc.pussy]推离[npc2.namePos][npc2.cock+]。",
								"[npc.name]还没准备好失去宝贵的贞洁，[npc.she]迅速地将[npc.pussy]推离[npc2.namePos][npc2.cock+]。",
								"[npc.Name]把宝贵的贞洁看得比什么都重，"
										+ "[npc.she]将[npc.pussy]推离[npc2.namePos][npc2.cock+]，明确强调自己真的不想被插入。"));
							
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
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
	};
	
	public static final SexAction PUSSY_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "收紧小穴";
		}

		@Override
		public String getActionDescription() {
			return "用你肌肉发达的小穴挤弄包裹着[npc2.namePos]的[npc2.cock]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getVaginaOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				return UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"[npc.Name]发出一阵[npc.a_moan+]，继续专心用[npc.her]肌肉极致发达的[npc.pussy]挤弄包裹着#IF(npc3.isPlayer())[npc3.namePos]和[npc2.namePos]#ELSE[npc2.namePos]和[npc3.namePos]#ENDIF[npc2.cocks+(true)]。",
						(!isTargetedCharacterInanimate()
							?"[npc.Name]发出一阵[npc.a_moan+]，继续专心控制自己[npc.pussy]内部极致发达的肌肉。"
								+ "[npc.Name]挤弄包裹着#IF(npc3.isPlayer())[npc3.namePos]和[npc2.namePos]#ELSE[npc2.namePos]和[npc3.namePos]#ENDIF[npc2.cocks+(true)]，让"
								+ (getOngoingCharacters(Main.sex.getCharacterPerformingAction()).contains(Main.game.getPlayer())?"你们":"他们")
								+ "两人不禁发出愉悦的呻吟。"
							:""),
						"[npc.Name]以稳定的节奏[npc.moans]着，[npc.she]继续专心"
								+ "用[npc.her]肌肉极致发达且[npc.pussy+]挤弄包裹着双插进[npc.herHim]体内的两根[npc2.cocks(true)]。",
						"[npc.Name]发出一阵[npc.a_moan+]，专心控制[npc.pussy]内里极致发达的肌肉，"
								+ "[npc.she]挤弄按摩着#IF(npc3.isPlayer())[npc3.namePos]和[npc2.namePos]#ELSE[npc2.namePos]和[npc3.namePos]#ENDIF [npc2.cock+]时，不禁愉悦地尖叫一声。"));
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name]发出一阵[npc.a_moan+]，继续专心用[npc.her]肌肉极致发达的[npc.pussy]挤弄包裹着[npc2.namePos][npc2.cock+]。",
						(!isTargetedCharacterInanimate()
							?"[npc.Name]发出一阵[npc.a_moan+]，继续专心控制自己[npc.pussy]内部极致发达的肌肉。"
								+ "[npc.Name]挤弄包裹着[npc2.namePos][npc2.cock+]，让[npc2.herHim]不禁漏出一声愉悦的呻吟。"
							:""),
						"[npc.Name]以稳定的节奏[npc.moans]着，[npc.she]继续专心"
								+ "用[npc.her]肌肉极致发达且[npc.pussy+]挤弄包裹着[npc2.namePos][npc2.cock+]。",
						"[npc.Name]发出一阵[npc.a_moan+]，专心控制[npc.pussy]内里极致发达的肌肉，"
								+ "[npc.she]挤弄按摩着[npc2.namePos][npc2.cock+]时，不禁愉悦地尖叫一声。");
			}
		}
	};
	
	public static final SexAction PENIS_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "操[npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.cock+]插进[npc2.namePos][npc2.pussy+]并开始操[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]慢慢用[npc.cock][npc.cockTip+]挑逗着[npc2.namePos][npc2.labia+]，"
									+ "发出轻微的[npc.moan]，缓缓地向前推，将[npc.cock+]没入[npc2.namePos][npc2.pussy+]里。",

							"[npc.Name]将其[npc.cock][npc.cockTip+]抵住[npc2.namePos][npc2.labia+]，"
									+ "[npc.she]以缓慢而稳定的力度，轻柔地将它深深地插入[npc2.namePos][npc2.pussy+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.cock][npc.cockTip+]饥渴地挑逗着[npc2.namePos][npc2.labia+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.cock+]深深插入[npc2.her][npc2.pussy+]。",

							"[npc.Name]将其[npc.cock][npc.cockTip+]抵住[npc2.namePos][npc2.labia+]，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos][npc2.pussy+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.cock][npc.cockTip+]粗暴地磨蹭[npc2.namePos][npc2.labia+]，"
									+ "发出[npc.a_moan+]，然后粗暴地向前猛推，将[npc.cock+]深深插入[npc2.her][npc2.pussy+]。",

							"[npc.Name]将其[npc.cock][npc.cockTip+]抵住[npc2.namePos][npc2.labia+]，"
									+ "用力前推，粗暴地将它深深插入[npc2.her][npc2.pussy+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.cock][npc.cockTip+]饥渴地挑逗着[npc2.namePos][npc2.labia+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.cock+]深深插入[npc2.her][npc2.pussy+]。",

							"[npc.Name]将其[npc.cock][npc.cockTip+]抵住[npc2.namePos][npc2.labia+]，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos][npc2.pussy+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.cock][npc.cockTip+]挑逗着[npc2.namePos][npc2.labia+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，将[npc.cock+]深深插入[npc2.her][npc2.pussy+]。",

							"[npc.Name]将其[npc.cock][npc.cockTip+]抵住[npc2.namePos][npc2.labia+]，"
									+ "[npc.she]小幅度前推，将它深深插入[npc2.namePos][npc2.pussy+]。"));
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
						UtilText.nodeContentSB.append("因为[npc2.name]的处女膜还完好无损，当[npc2.she]感到[npc.namePos][npc.cock+]撕破了它时，[npc2.she]不禁发出狂乱的哀号。");
						
					} else {
						UtilText.nodeContentSB.append("因为[npc2.name]的处女膜还完好无损，当[npc2.she]感到[npc.namePos][npc.cock+]撕破了它时，[npc2.she]不禁发出惊讶的[npc2.moan]。");
					}
					
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.cock+]顶进[npc2.name]体内，[npc2.herHim]发出轻柔的[npc2.moan]，"
											+ "于是[npc2.she]温柔地挺起自己的[npc2.hips]，以便让它在[npc2.pussy+]里插得更深。",
		
									"[npc2.name]轻柔地[npc2.moan]，开始温柔地扭动[npc2.hips]，"
											+ "将[npc.namePos][npc.cock+]更深地插入自己[npc2.pussy+]。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]感受到[npc.cock+]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
											+ "于是[npc2.her]粗暴地挺起[npc2.hips]，强迫它在自己[npc2.pussy+]里插得更深。",
		
									"伴随着一声[npc2.a_moan+]，[npc2.name]开始猛烈地摇动[npc2.hips]，"
											+ "粗暴地强迫[npc.Name]把[npc.her][npc.cock+]在自己[npc2.pussy+]里插得更深。"));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]感受到[npc.cock+]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
											+ "[npc2.she]急切地扭动[npc2.hips]，让它在[npc2.pussy+]里插得更深。",
		
									"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地扭动[npc2.hips]，"
											+ "饥渴地让[npc.namePos][npc.cock+]更加深入[npc2.her][npc2.pussy+]。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]感受到[npc.cock+]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
											+ "[npc2.she]扭动[npc2.hips]，让它在[npc2.pussy+]里插得更深。",
		
									"伴随着一声[npc2.a_moan+]，[npc2.name]开始扭动[npc2.hips]，"
											+ "让[npc.namePos][npc.cock+]更加深入[npc2.her][npc2.pussy+]。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]感受到[npc.cock+]进入了自己的身体，不禁漏出一声[npc2.a_sob+]，"
											+ "泪水不停地从[npc2.her]的[npc2.face]上淌下，[npc2.she]哀求[npc.Name]从自己身体里拔出来。",
		
									"[npc2.name]发出[npc2.a_sob+]，徒劳地挣扎扭身，试图拔出讨厌的插入物，"
											+ "但[npc.namePos]不请自来的[npc.cock]却在[npc2.pussy+]中插得更深，泪水从[npc2.her]的[npc2.face]上流了下来。"));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	

	public static final SexAction PENIS_FUCKING_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();
			
			int size = PenisVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size();
			
			map.put("one other character fucking [npc2.namePos] pussy", size==1);
			map.put("[npc.namePos] penis to be exposed", Main.sex.getCharacterPerformingAction().isPenetrationTypeExposed(SexAreaPenetration.PENIS));
			map.put("[npc.namePos] penis to be free", SexAreaPenetration.PENIS.isFree(Main.sex.getCharacterPerformingAction()));
			
			return map;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()==1
					&& !getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).contains(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "双重插入";
		}
		
		@Override
		public String getActionDescription() {
			return UtilText.parse(getCharactersForParsing(),
					"与[npc3.name]一起操[npc2.namePos][npc2.pussy+]。");
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
							"[npc.name]想一起找些乐子，[npc.she]将[npc.cock][npc.cockHead+]抵在[npc2.namePos][npc2.pussy+]上，接着[npc3.name]稍稍后退，"
									+ "[npc.she]温柔地挺身向前，将[npc.cock+]插到[npc3.namePos]的旁边，开始与[npc3.name]一起双插[npc2.name]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
							"[npc.name]想一起找些乐子，[npc.she]将[npc.cock][npc.cockHead+]抵在[npc2.namePos][npc2.pussy+]上，接着[npc3.name]稍稍后退，"
									+ "[npc.she]粗暴地挺身向前，强行将[npc.cock+]插到[npc3.namePos]的旁边，开始与[npc3.name]一起双插[npc2.name]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
							"[npc.name]想一起找些乐子，[npc.she]将[npc.cock][npc.cockHead+]抵在[npc2.namePos][npc2.pussy+]上，接着[npc3.name]稍稍后退，"
									+ "[npc.she]挺身向前，将[npc.cock+]插到[npc3.namePos]的旁边，开始与[npc3.name]一起双插[npc2.name]。"));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
							"[npc.name]想一起找些乐子，[npc.she]将[npc.cock][npc.cockHead+]抵在[npc2.namePos][npc2.pussy+]上，接着[npc3.name]稍稍后退，"
									+ "[npc.she]饥渴地挺身向前，将[npc.cock+]撞到[npc3.namePos]的旁边，开始与[npc3.name]一起双插[npc2.name]。"));
					break;
			}
			

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
								"[npc2.name]感受到第二根[npc.cock]进入了自己[npc2.pussy+]，不禁发出轻柔的[npc2.moan]，"
										+ "[npc2.she]温柔地挺起[npc2.hips]，以便让它和[npc3.namePos][npc3.cock+]一起插得更深。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
								"[npc2.name]感受到第二根[npc.cock]进入了自己[npc2.pussy+]，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.she]粗暴地挺起[npc2.hips]，以便让它和[npc3.namePos][npc3.cock+]一起插得更深。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
								"[npc2.name]感受到第二根[npc.cock]进入了自己[npc2.pussy+]，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.she]挺起自己的[npc2.hips]，让它和[npc3.namePos][npc3.cock+]一起插得更深。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"[npc2.name]感受到第二根[npc.cock]进入了自己的身体，不禁漏出一声[npc2.a_sob+]，"
										+ "泪水不停地从[npc2.her]的[npc2.face]上淌下，[npc2.she]哀求[npc.Name]和[npc3.name]从自己身体里拔出来。",
								"[npc2.name]发出[npc2.a_sob+]，徒劳地挣扎扭身，试图拔出第二根讨厌的插入物，"
										+ "[npc2.she]感觉到[npc.namePos]不请自来的[npc.cock]却在[npc2.pussy+]中插得更深，泪水再次不由自主地从[npc2.her]的[npc2.face]上流了下来。")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
								"[npc2.name]感受到第二根[npc.cock]进入了自己[npc2.pussy+]，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.she]贪婪地挺起自己的[npc2.hips]，让它和[npc3.namePos][npc3.cock+]一起插得更深。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return (UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								"[npc2.name]拼命想要从插在[npc2.herHim]里面的两根[npc.cocks(true)]上逃开，但没能成功，"
										+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地乞求[npc.Name]和[npc3.name]从自己[npc2.pussy+]里拔出来，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
								"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]和[npc3.name]。"
										+ "泪水如小溪般从[npc2.her]的[npc2.face]上流下，[npc2.she]恳求"
										+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())?"你们":"他们")+"两人从自己[npc2.pussy+]里拔出去。",
								"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
										+ "[npc2.she]无力地尝试移出[npc2.pussy+]里插入的[npc.cocks(true)]，但徒劳无功，[npc2.she]哭着哀求[npc.name]和[npc3.name]放过[npc2.herHim]。")));
					case SUB_NORMAL:
						return (UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								"[npc2.Name]扭动[npc2.hips]作为回应，"
										+ "[npc2.she]帮着两根[npc.cocks(true)]深深插入[npc2.her][npc2.pussy+]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]挺起[npc2.hips]，乞求[npc.name]和[npc3.name]继续操[npc2.herHim]。",
								"[npc2.name]愉悦地[npc2.moaning]着，饥渴地挺起[npc2.hips+]，"
										+ "乞求"+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"你们两人继续操[npc2.herHim]，[npc2.she]做出了有助于你们的[npc.cocks(true)]更深地插入[npc2.her][npc2.pussy+]的动作。"
												:"他们两人继续操[npc2.herHim]，[npc2.she]做出了有助于他们的[npc.cocks(true)]更深地插入[npc2.her][npc2.pussy+]的动作。"))));
					case DOM_GENTLE:
						return (UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								"[npc2.Name]慢慢地扭动[npc2.hips]作为回应，"
										+ "[npc2.she]发出一阵[npc2.a_moan+]，温柔地让两根[npc.cocks(true)]更深地插入[npc2.her][npc2.pussy+]。",
								"[npc2.namePos][npc2.lips+]间飘出一阵[npc2.A_moan+]，"
										+ "[npc2.she]慢慢地向后回撞[npc2.hips]，恳求[npc.Name]和[npc3.name]继续干[npc2.herHim]。",
								"[npc2.name]愉悦地[npc2.moaning]着，温柔地挺起[npc2.hips+]，"
										+ "乞求"+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"你们两人继续操[npc2.herHim]，[npc2.she]有节奏地做出有助于你们的[npc.cocks(true)]更深地插入[npc2.her][npc2.pussy+]的动作。"
												:"他们两人继续操[npc2.herHim]，[npc2.she]有节奏地做出有助于他们的[npc.cocks(true)]更深地插入[npc2.her][npc2.pussy+]的动作。"))));
					case DOM_ROUGH:
						return (UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								"[npc2.Name]粗暴地扭动[npc2.hips]作为回应，"
										+ "[npc2.she]发出一阵[npc2.a_moan+]，粗鲁地将迫使两根[npc.cocks(true)]更深地插入[npc2.her][npc2.pussy+]。",
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]粗暴地推动[npc2.hips]，命令[npc.name]和[npc3.name]继续操[npc2.herHim]。",
								"[npc2.name]愉悦地[npc2.moaning]着，粗暴地推动[npc2.hips+]，"
										+ "命令"+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"你们两人继续操[npc2.herHim]，[npc2.she]粗暴地做出有助于你们的[npc.cocks(true)]更深地插入[npc2.her][npc2.pussy+]的动作。"
												:"他们两人继续操[npc2.herHim]，[npc2.she]粗暴地做出有助于他们的[npc.cocks(true)]更深地插入[npc2.her][npc2.pussy+]的动作。"))));
					default:
						return (UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								"[npc2.Name]迷乱地扭动[npc2.hips]作为回应，"
										+ "[npc2.she]发出一阵[npc2.a_moan+]，急切地迫使两根[npc.cocks(true)]更深地插入[npc2.her][npc2.pussy+]。",
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "竭力拱起[npc2.hips]，祈求[npc.name]和[npc3.name]继续操[npc2.herHim]。",
								"[npc2.name]一边愉悦地[npc2.Moaning]，一边高兴地前后摆动[npc2.her][npc2.hips+]，"
										+ "乞求"+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"你们两人继续操[npc2.herHim]，[npc2.she]积极地做出有助于你们的[npc.cocks(true)]更深地插入[npc2.her][npc2.pussy+]的动作。"
												:"他们两人继续操[npc2.herHim]，[npc2.she]积极地做出有助于他们的[npc.cocks(true)]更深地插入[npc2.her][npc2.pussy+]的动作。"))));
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return (UtilText.returnStringAtRandom(
								"[npc2.name]拼命想要逃离[npc.namePos]的[npc.cock]，但没能成功，"
										+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地乞求[npc.Name]从自己[npc2.pussy+]里拔出来，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
								"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
										+ "[npc2.she]恳求[npc.name]从自己[npc2.pussy+]拔出来，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
								"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
										+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]从[npc2.her][npc2.pussy+]里拔出来。"));
					case SUB_NORMAL:
						return (UtilText.returnStringAtRandom(
								"[npc2.Name]扭动[npc2.hips]作为回应，"
										+ "[npc2.she]帮着[npc.namePos][npc.cock+]深深插入[npc2.her][npc2.pussy+]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]挺起[npc2.hips]，乞求[npc.name]继续操[npc2.herHim]。",
								"[npc2.name]愉悦地[npc2.moaning]着，饥渴地挺起[npc2.hips+]，"
										+ "做出有助于[npc.cock+]更深地插入[npc2.her][npc2.pussy+]的动作，乞求[npc.Name]继续操[npc2.herHim]。"));
					case DOM_GENTLE:
						return (UtilText.returnStringAtRandom(
								"[npc2.Name]慢慢地扭动[npc2.hips]作为回应，"
										+ "[npc2.she]发出一声轻柔的[npc2.moan]，温柔地乞求着[npc.Name]继续干[npc2.her][npc2.pussy+]。",
								" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
										+ "[npc2.she]缓缓地晃动[npc2.hips]，恳求[npc.Name]继续操[npc2.herHim]。",
								"[npc2.name]愉悦地[npc2.moaning]着，轻柔地将[npc2.hips+]向后压去，"
										+ "做出有助于[npc.cock+]更深地插入[npc2.her][npc2.pussy+]的动作，乞求[npc.Name]继续操[npc2.herHim]。"));
					case DOM_ROUGH:
						return (UtilText.returnStringAtRandom(
								"[npc2.Name]粗暴地扭动[npc2.hips]作为回应，"
										+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地命令[npc.Name]继续干[npc2.herHim]。",
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]粗鲁地用[npc2.hips]压向[npc.namePos]的腹股沟，命令[npc.Name]继续操[npc2.herHim]。",
								"[npc2.name]愉悦地[npc2.moaning]着，粗暴地用[npc2.hips+]猛然撞击[npc.namePos]的胯下，"
										+ "强迫[npc.name]将[npc.cock+]深深插入[npc2.her][npc2.pussy+]，命令[npc.name]继续干[npc2.herHim]。"));
					default:
						return (UtilText.returnStringAtRandom(
								"[npc2.Name]热切地扭动[npc2.hips]作为回应，"
										+ "[npc2.she]热切地帮着[npc.namePos][npc.cock+]深深插入[npc2.her][npc2.pussy+]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]急切地挺起[npc2.hips]，乞求[npc.name]继续操[npc2.herHim]。",
								"[npc2.name]愉悦地[npc2.moaning]着，急切地挺起[npc2.hips+]，"
										+ "做出有助于[npc.cock+]更深地插入[npc2.her][npc2.pussy+]的动作，急切地乞求[npc.Name]继续操[npc2.herHim]。"));
				}
			}
		}
		return "";
	}
	
	public static final SexAction PENIS_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "操干(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地把你的[npc.cock]滑进[npc2.namePos][npc2.pussy+]，开始抽插。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"[npc.name]将自己[npc.cock+]和[npc3.nameHers]的抵在一起，温柔地深深插入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.she]缓缓地操干[npc2.name]，每次扭动[npc.hips]都会发出一声轻微的[npc.moan]。",
						"[npc.Name]加入[npc3.name]一起双插[npc2.namePos][npc2.pussy+]，[npc.she]柔和地向前推动[npc.hips]，温柔地操干[npc2.name]，发出一声轻微的[npc.moan]。",
						"[npc.namePos]与[npc3.namePos]的[npc.cocks(true)]都深深塞进了[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]温柔地前后抽动[npc.hips]，呼吸着[npc2.namePos]的[npc2.scent]，缓缓地操[npc2.herHim]。")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]温柔地将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]开始前后摇摆[npc.hips]，缓缓地操[npc2.name]，每次推入都会发出一阵轻微的[npc.moan]。",
						"[npc.name]缓缓地将[npc.cock+]插入[npc2.namePos][npc2.pussy+]，"
								+ "轻柔地将[npc.hips]向前推，[npc.she]温柔地干着[npc2.name]，不禁漏出一小声[npc.moan]。",
						"[npc.Name]将[npc.cock+]滑入[npc2.namePos][npc2.pussy+]，开始温柔地前后抽动[npc.hips]，不禁漏出一小声[npc.moan]，"
								+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，同时缓缓地操[npc2.name]。"));
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "操[npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "继续在[npc2.namePos][npc2.pussy+]里抽送你[npc.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"[npc.name]将自己[npc.cock+]和[npc3.nameHers]的抵在一起，急切地深深插入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.she]疯狂地操干[npc2.name]，每次竭力扭动[npc.hips]都会发出一阵[npc.a_moan+]。",
						"[npc.Name]加入[npc3.name]一起双插[npc2.namePos][npc2.pussy+]，[npc.she]热切地向前推动[npc.hips]，饥渴地操干[npc2.name]，发出一阵[npc.a_moan+]。",
						"[npc.namePos]与[npc3.namePos]的[npc.cocks(true)]都深深塞进了[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]兴奋地前后抽动[npc.hips]，呼吸着[npc2.namePos]的[npc2.scent]，拼命地操[npc2.herHim]。")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]急切地将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]开始热切地前后摇摆[npc.hips]，兴奋地操[npc2.name]，每次突入都会发出一阵[npc.a_moan+]。",
						"[npc.name]亢奋地将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]，"
								+ "开始疯狂地向前挺进[npc.hips]，贪婪地干着[npc2.name]，不禁发出[npc.a_moan+]。",
						"[npc.Name]将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]，开始热切地用[npc.hips]前后抽送打桩，不禁发出[npc.a_moan+]，"
								+ "[npc.she]兴奋地干着[npc2.namePos]，呼吸着[npc2.herHim]的[npc2.scent]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "操干(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地用你[npc.cock+]在[npc2.namePos][npc2.pussy+]里进进出出。";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"[npc.name]将自己[npc.cock+]和[npc3.nameHers]的抵在一起，粗暴地深深插入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.she]激烈地操干[npc2.name]，每次猛烈扭动[npc.hips]都会发出一阵[npc.a_moan+]。",
						"[npc.Name]加入[npc3.name]一起双插[npc2.namePos][npc2.pussy+]，[npc.she]粗暴地向前推动[npc.hips]，激烈地操干[npc2.name]，发出一阵[npc.a_moan+]。",
						"[npc.namePos]与[npc3.namePos]的[npc.cocks(true)]都深深塞进了[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]用力地前后抽动[npc.hips]，呼吸着[npc2.namePos]的[npc2.scent]，狠狠地操[npc2.herHim]。")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]野蛮地将[npc.cock+]撞入[npc2.namePos][npc2.pussy+]深处，"
								+ "[npc.she]开始粗暴地前后摇摆[npc.hips]，野蛮地操[npc2.name]，每次推入都会发出一阵[npc.a_moan+]。",
						"[npc.name]激烈地将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]，"
								+ "开始粗暴地向前挺进[npc.hips]，激烈地干着[npc2.name]，不禁发出[npc.a_moan+]。",
						"[npc.Name]激烈地将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]，[npc.hips]粗暴地来回撞击，不禁发出[npc.a_moan+]，"
								+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，同时激烈地操[npc2.name]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "操[npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "继续操[npc2.namePos][npc2.pussy+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"[npc.name]将自己[npc.cock+]和[npc3.nameHers]的抵在一起，深深插入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.she]愉悦地操干[npc2.name]，每次扭动[npc.hips]都会发出一阵[npc.a_moan+]。",
						"[npc.Name]加入[npc3.name]一起双插[npc2.namePos][npc2.pussy+]，[npc.she]向前推动[npc.hips]，开始操干[npc2.name]，发出一阵[npc.a_moan+]。",
						"[npc.namePos]与[npc3.namePos]的[npc.cocks(true)]都深深塞进了[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]兴奋地前后抽动[npc.hips]，呼吸着[npc2.namePos]的[npc2.scent]，操着[npc2.herHim]。")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]开始前后摇摆[npc.hips]，兴奋地操[npc2.name]，每次突入都会发出一阵[npc.a_moan+]。",
						"[npc.name]将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]，"
								+ "开始向前挺动[npc.hips]，急切地操着[npc2.herHim]，不禁发出[npc.a_moan+]。",
						"[npc.Name]将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]，开始用[npc.hips]前后抽送打桩，不禁发出[npc.a_moan+]，"
								+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，同时操着[npc2.name]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "操干(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "渴求地把你[npc.cock+]推入[npc2.namePos][npc2.pussy+]，开始抽插。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"[npc.name]将自己[npc.cock+]和[npc3.nameHers]的抵在一起，急切地深深插入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.she]疯狂地操干[npc2.name]，每次竭力扭动[npc.hips]都会发出一阵[npc.a_moan+]。",
						"[npc.Name]加入[npc3.name]一起双插[npc2.namePos][npc2.pussy+]，[npc.she]热切地向前推动[npc.hips]，饥渴地操干[npc2.name]，发出一阵[npc.a_moan+]。",
						"[npc.namePos]与[npc3.namePos]的[npc.cocks(true)]都深深塞进了[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]兴奋地前后抽动[npc.hips]，呼吸着[npc2.namePos]的[npc2.scent]，拼命地操[npc2.herHim]。")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]急切地将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]开始热切地前后摇摆[npc.hips]，兴奋地操[npc2.name]，每次突入都会发出一阵[npc.a_moan+]。",
						"[npc.name]亢奋地将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]，"
								+ "开始疯狂地向前挺进[npc.hips]，贪婪地干着[npc2.name]，不禁发出[npc.a_moan+]。",
						"[npc.Name]将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]，开始热切地用[npc.hips]前后抽送打桩，不禁发出[npc.a_moan+]，"
								+ "[npc.she]兴奋地干着[npc2.namePos]，呼吸着[npc2.herHim]的[npc2.scent]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抗拒操干";
		}

		@Override
		public String getActionDescription() {
			return "努力让你的[npc.cock]远离[npc2.namePos][npc2.pussy+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"[npc.Name]尝试把[npc.cock]从[npc2.namePos]的[npc2.pussy]里拔出来，"
										+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就低下去轻轻地抓住它，"
										+ "然后温柔地把它扭回[npc2.namePos][npc2.pussy+]，与[npc3.nameHers]的[npc3.cock]贴在一起。", 
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.cock]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，"
										+ "然后温柔地把它扭回[npc2.namePos][npc2.pussy+]，与[npc3.nameHers]的[npc3.cock]贴在一起。", 
								"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock]从[npc2.namePos][npc2.pussy+]里拔出来，"
										+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边温柔地将[npc2.pussy+]压向[npc.her][npc.cock+]。")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"[npc.Name]尝试把[npc.cock]从[npc2.namePos]的[npc2.pussy]里拔出来，"
										+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就向后粗暴地抓住它，"
										+ "然后蛮横地把它扭回[npc2.namePos][npc2.pussy+]，与[npc3.nameHers]的[npc3.cock]贴在一起。", 
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.cock]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，"
										+ "然后粗暴地把它扭回[npc2.namePos][npc2.pussy+]，与[npc3.nameHers]的[npc3.cock]贴在一起。", 
								"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock]从[npc2.namePos][npc2.pussy+]里拔出来，"
										+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边粗暴地将[npc2.pussy+]压向[npc.her][npc.cock+]。")));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"[npc.Name]尝试把[npc.cock]从[npc2.namePos]的[npc2.pussy]里拔出来，"
										+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就向后牢牢地抓住它，"
										+ "然后热切地把它扭回[npc2.namePos][npc2.pussy+]，与[npc3.nameHers]的[npc3.cock]贴在一起。", 
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.cock]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，"
										+ "然后热切地把它扭回[npc2.namePos][npc2.pussy+]，与[npc3.nameHers]的[npc3.cock]贴在一起。", 
								"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock]从[npc2.namePos][npc2.pussy+]里拔出来，"
										+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边将[npc2.pussy+]压向[npc.her][npc.cock+]。")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]尝试把[npc.cock]从[npc2.namePos]的[npc2.pussy]里拔出来，"
										+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就低下去轻轻地抓住它，然后温柔地把它扭回[npc2.her][npc2.pussy+]。",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.cock]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并温柔地把它扭回[npc2.her][npc2.pussy+]里。",
								"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock]从[npc2.namePos][npc2.pussy+]里拔出来，"
										+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边温柔地将[npc2.pussy+]压向[npc.her][npc.cock+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]尝试把[npc.cock]从[npc2.namePos]的[npc2.pussy]里拔出来，"
										+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就向后粗暴地抓住它，然后蛮横地把它扭回[npc2.her][npc2.pussy+]。",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.cock]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并粗暴地把它扭回[npc2.her][npc2.pussy+]里。",
								"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock]从[npc2.namePos][npc2.pussy+]里拔出来，"
										+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边粗暴地将[npc2.pussy+]压向[npc.her][npc.cock+]。"));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]尝试把[npc.cock]从[npc2.namePos]的[npc2.pussy]里拔出来，"
										+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就向后牢牢地抓住它，然后热切地把它扭回[npc2.her][npc2.pussy+]。",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.cock]从[npc2.name]的身体里拔出来，但[npc2.name]马上就抓住了它，并渴望地把它扭回[npc2.her][npc2.pussy+]里。",
								"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock]从[npc2.namePos][npc2.pussy+]里拔出来，"
										+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边将[npc2.pussy+]压向[npc.her][npc.cock+]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "不操了";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.cock+]从[npc2.namePos][npc2.pussy+]里拔出去，停止操[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"[npc.name]粗暴地将[npc.cock+]从[npc2.namePos][npc2.pussy+]中拉出，"
										+ "霸道地用[npc.cockTip+]最后一次上下磨蹭[npc2.her][npc2.labia+]，然后将它移开，让[npc3.name]继续操[npc2.herHim]。",
								"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.cock+]从[npc2.her][npc2.pussy+]中猛抽出来，结束了粗暴双重插入。")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"[npc.name]将[npc.cock]从[npc2.namePos][npc2.pussy+]中抽出，"
										+ "用[npc.cockTip+]最后一次上下磨蹭[npc2.her][npc2.labia+]，然后将它移开，让[npc3.name]继续操[npc2.herHim]。",
								"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.cock+]从[npc2.her][npc2.pussy+]中拔出来，结束了双重插入。")));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
									"当[npc.Name]从[npc2.her]的[npc2.pussy]里拔出来时，[npc2.name]忍不住漏出一声[npc2.sob+]，"
											+ "[npc2.she]仍然不停地哭泣，抵抗着[npc3.name]对自己的抽抽插插。",
									"[npc2.name]发出一阵[npc2.a_sob+]，拼命地挣扎反抗，但[npc3.name]仍然对[npc2.her][npc2.pussy+]猛烈打桩，泪水如小溪般从[npc2.her]的[npc2.face]上流了下来。"))); 
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
									"当[npc.name]将[npc.cock+]拔出[npc2.her][npc2.pussy+]时，[npc2.Name]发出[npc2.a_moan+]，渴求[npc.name]的更多“照顾”。",
									"[npc2.namePos][npc2.lips+]间漏出一声[npc2.A_moan+]，暴露了[npc2.she]无比渴望得到[npc.namePos]的更多关注。")));
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]粗暴地将[npc.cock+]从[npc2.namePos][npc2.pussy+]中拉出，"
										+ "霸道地用[npc.cockTip+]最后一次上下磨蹭[npc2.her][npc2.labia+]，然后将它移开。",
								"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.cock+]从[npc2.her][npc2.pussy+]中猛抽出来，结束了粗暴性交。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]将[npc.cock]滑出[npc2.namePos][npc2.pussy+]，在最后一次用[npc.cockTip]来回磨蹭[npc2.her][npc2.labia+]后，将[npc2.her]推开。",
								"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.cock+]从[npc2.her][npc2.pussy+]中拔出来，结束了性交。"));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"当[npc.Name]从[npc2.her]的[npc2.pussy]里拔出来时，[npc2.name]忍不住漏出一声[npc2.sob+]，"
											+ "[npc2.she]仍然不停地哭泣，无力地继续反抗着[npc.name]。",
									"[npc2.name]发出一阵[npc2.a_sob+]，拼命地挣扎着反抗[npc.Name]，将[npc2.pussy+]从[npc.Name]身上抽离，泪水如小溪般从[npc2.face]上流了下来。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"当[npc.name]将[npc.cock+]拔出[npc2.her][npc2.pussy+]时，[npc2.Name]发出[npc2.a_moan+]，渴求[npc.name]的更多“照顾”。",
									"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]无比渴望得到[npc.namePos]的更多关注。"));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	//TODO DP
	
	public static final SexAction USING_PENIS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "开始被操";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.namePos][npc2.cock+]滑进你[npc.pussy+]，你开始被操。";
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
			
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]抓住[npc2.namePos]的[npc2.cock]，慢慢地将它引导到自己[npc.labia+]，"
										+ "[npc.her]温柔地摇晃着[npc.hips]，不时漏出一小声[npc.moan]，强迫[npc2.herHim]插入[npc.her][npc.pussy+]。",
								"[npc.name]抓住[npc2.namePos]的[npc2.cock]，把它对准自己[npc.pussy+]，"
										+ "慢慢地将[npc.hips]向后顶，将[npc2.her][npc2.cock+]插入自己，不禁漏出一声轻柔的[npc.moan]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]抓住[npc2.namePos]的[npc2.cock]，粗暴地把它拉到自己[npc.labia+]，"
										+ "[npc.her]暴力地猛烈摇晃[npc.hips]，不时漏出一阵[npc.a_moan+]，强迫[npc2.herHim]操进[npc.her][npc.pussy+]。",
								"[npc.name]抓住[npc2.namePos]的[npc2.cock]，把它对准自己[npc.pussy+]，"
										+ "[npc.she]急切地向后猛撅[npc.hips]，裹住[npc2.her][npc2.cock+]，[npc.she]不禁漏出一声[npc.a_moan+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]抓住[npc2.namePos]的[npc2.cock]，将它引导到自己[npc.labia+]，"
										+ "[npc.her]摇晃着[npc.hips]，不时漏出一阵[npc.a_moan+]，强迫[npc2.herHim]插入[npc.her][npc.pussy+]。",
								"[npc.name]抓住[npc2.namePos]的[npc2.cock]，把它对准自己[npc.pussy+]，"
										+ "[npc.she]向后顶[npc.hips]，吞下[npc2.her][npc2.cock+]，[npc.she]不禁漏出一声[npc.a_moan+]。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]抓住[npc2.namePos]的[npc2.cock]，热切地把它引导到自己[npc.labia+]，"
										+ "[npc.her]亢奋地摇晃着[npc.hips]，不时漏出一阵[npc.a_moan+]，强迫[npc2.herHim]插入[npc.her][npc.pussy+]。",
								"[npc.name]抓住[npc2.namePos]的[npc2.cock]，把它对准自己[npc.pussy+]，"
										+ "[npc.she]热切地向后顶[npc.hips]，吞下[npc2.her][npc2.cock+]，[npc.she]不禁漏出一声[npc.a_moan+]。"));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]缓缓地移动[npc.hips]，直至[npc2.namePos][npc2.cock+]被挤入[npc.her][npc.labia+]，"
										+ "[npc.she]发出一小声[npc.moan]，温柔地向后压，强迫[npc2.name]插入[npc.her][npc.pussy+]。",
								"[npc.Name]将[npc.pussy+]向后压向[npc2.namePos]的[npc2.cock]，把它挪到[npc.labia+]之间，"
										+ "[npc.she]慢慢地向后移动，强迫[npc2.name]插入[npc.herHim]，不禁漏出一声轻柔的[npc.moan]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]激烈地将[npc.hips]向后压，直至[npc2.namePos][npc2.cock+]被挤入[npc.her][npc.labia+]，"
										+ "[npc.she]发出一阵[npc.a_moan+]，粗暴地向后撞，强迫[npc2.name]插入[npc.her][npc.pussy+]。",
								"[npc.Name]粗暴地将[npc.pussy+]向后压向[npc2.namePos]的[npc2.cock]，把它挪到[npc.labia+]之间，"
										+ "[npc.she]霸道地向后顶，强迫[npc2.name]插入[npc.herHim]，不禁漏出一阵[npc.a_moan+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]将[npc.hips]向后压，直至[npc2.namePos][npc2.cock+]被挤入[npc.her][npc.labia+]，"
										+ "[npc.she]发出一阵[npc.a_moan+]，向后压去，强迫[npc2.name]插入[npc.her][npc.pussy+]。",
								"[npc.Name]将[npc.pussy+]向后压向[npc2.namePos]的[npc2.cock]，把它挪到[npc.labia+]之间，"
										+ "[npc.she]向后移动，强迫[npc2.name]插入[npc.herHim]，不禁漏出一阵[npc.a_moan+]。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]急切地将[npc.hips]向后压，直至[npc2.namePos][npc2.cock+]被挤入[npc.her][npc.labia+]，"
										+ "[npc.she]发出一阵[npc.a_moan+]，贪婪地向后压去，强迫[npc2.name]插入[npc.her][npc.pussy+]。",
								"[npc.Name]饥渴地将[npc.pussy+]向后压向[npc2.namePos]的[npc2.cock]，把它挪到[npc.labia+]之间，"
										+ "[npc.she]急切地向后移动，强迫[npc2.name]插入[npc.herHim]，不禁漏出一阵[npc.a_moan+]。"));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.herHim]的身体，发出一声轻柔的[npc2.moan]，"
										+ "温柔地将[npc2.cock]向前顶，开始操[npc.namePos][npc.pussy+]。",
	
								"[npc2.name]轻柔地[npc2.moan]着，温柔地挺进自己的[npc2.cock]，"
										+ "将它深深插进[npc.namePos][npc.pussy+]，开始操[npc.herHim]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "[npc2.she]热切地将[npc2.cock]向前顶，开始亢奋地操[npc.namePos][npc.pussy+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，热切地顶进自己的[npc2.cock]，"
										+ "将它深深插进[npc.namePos][npc.pussy+]，开始竭力地操[npc.herHim]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "为了警告[npc.name]别得意忘形，[npc2.she]粗暴地向前猛撅[npc2.cock]，开始无情地暴操[npc.her][npc.pussy+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，粗暴地猛塞自己的[npc2.cock]，"
										+ "[npc2.she]无情地暴操[npc.namePos][npc.pussy+]，宣告着自己的支配权。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]挺进[npc.name]的身体，不禁漏出一声[npc2.a_moan+]，[npc2.she]将[npc2.cock]向前顶，开始操[npc.namePos][npc.pussy+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，向前挺进[npc2.cock]，将它深深插入[npc.namePos][npc.pussy+]，开始操[npc.herHim]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]紧紧裹住[npc2.name]的[npc2.cock]，[npc2.name]不禁发出一声[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着推开[npc.name]，拼命地想把自己[npc2.cock+]从对方[npc.pussy+]里拔出来。",
	
								"[npc.Name]强行将[npc2.name]的[npc2.cock]深深插入自己[npc.pussy+]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction USING_PENIS_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();
			
			int size = PenisVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size();
			
			map.put("one other character fucking [npc.namePos] pussy", size==1);
			map.put("[npc2.namePos] penis to be exposed", Main.sex.getCharacterTargetedForSexAction(this).isPenetrationTypeExposed(SexAreaPenetration.PENIS));
			map.put("[npc2.namePos] penis to be free", SexAreaPenetration.PENIS.isFree(Main.sex.getCharacterTargetedForSexAction(this)));
			
			return map;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()==1
					&& !getOngoingCharacters(Main.sex.getCharacterPerformingAction()).contains(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "开始被双插";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getCharactersForParsing(),
				"让[npc2.name]加入[npc3.name]，操你[npc.pussy+]。");
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			

			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
							"[npc.Name]对只有[npc3.namePos]操干[npc.her][npc.pussy+]这件事感到并不满足，又温柔地握住了[npc2.namePos]的[npc2.cock]，慢慢地把它挪到[npc.her][npc.labia+]。"
									+ "[npc.she]微微[npc.moan]，温柔地向后撅[npc.hips]，强迫[npc2.herHim]和[npc3.name]一起双插[npc.her][npc.pussy+]。",
							"[npc.name]不满足于自己的[npc.pussy]只被[npc3.namePos]的[npc3.cock]填满，[npc.she]握住[npc2.namePos]的[npc2.cock]，温柔地把它挪到[npc.her][npc.labia+]。"
									+ "[npc.she]慢慢撅起[npc.hips]，感觉[npc2.namePos][npc2.cock+]跟[npc3.namePos]的一起插进[npc.her][npc.pussy+]，柔声[npc.moan]。")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
							"[npc.Name]对只有[npc3.namePos]操干[npc.her][npc.pussy+]这件事感到并不满足，又粗暴地握住了[npc2.namePos]的[npc2.cock]，把它猛拉到[npc.her][npc.labia+]。"
									+ "[npc.she]发出一阵[npc.a_moan+]，粗暴地向后拱[npc.hips]，强迫[npc2.name]和[npc3.name]一起双插[npc.her][npc.pussy+]。",
							"[npc.name]不满足于自己的[npc.pussy]只被[npc3.namePos]的[npc3.cock]填满，[npc.she]粗暴地握住[npc2.namePos]的[npc2.cock]，迅速地把它挪到[npc.her][npc.labia+]。"
									+ "[npc.she]粗暴地拱撞[npc.hips]，感觉[npc2.namePos][npc2.cock+]和[npc3.namePos]的一起插进[npc.her][npc.pussy+]，发出了[npc.a_moan+]。")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
							"[npc.Name]对只有[npc3.namePos]操干[npc.her][npc.pussy+]这件事感到并不满足，又抓住了[npc2.namePos]的[npc2.cock]，把它拉到[npc.her][npc.labia+]。"
									+ "[npc.she]发出一阵[npc.a_moan+]，向后拱[npc.hips]，强迫[npc2.name]和[npc3.name]一起双插[npc.her][npc.pussy+]。",
							"[npc.name]不满足于自己的[npc.pussy]只被[npc3.namePos]的[npc3.cock]填满，[npc.she]抓住[npc2.namePos]的[npc2.cock]，把它挪到[npc.her][npc.labia+]。"
									+ "[npc.she]撅起[npc.hips]，感觉[npc2.namePos][npc2.cock+]跟[npc3.namePos]的一起插进[npc.her][npc.pussy+]，发出了[npc.a_moan+]。")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
							"[npc.Name]对只有[npc3.namePos]操干[npc.her][npc.pussy+]这件事感到并不满足，又不耐烦地抓住了[npc2.namePos]的[npc2.cock]，急切地把它拉到[npc.her][npc.labia+]。"
									+ "[npc.she]发出一阵[npc.a_moan+]，迷乱地向后拱[npc.hips]，强迫[npc2.name]和[npc3.name]一起双插[npc.her][npc.pussy+]。",
							"[npc.name]不满足于自己的[npc.pussy]只被[npc3.namePos]的[npc3.cock]填满，[npc.she]贪婪地握住[npc2.namePos]的[npc2.cock]，迅速地把它挪到[npc.her][npc.labia+]。"
									+ "[npc.she]不耐烦地撅起[npc.hips]，感觉[npc2.namePos][npc2.cock+]跟[npc3.namePos]的一起插进[npc.her][npc.pussy+]，发出了[npc.a_moan+]。")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"[npc.Name]对只有[npc3.namePos]操干[npc.her][npc.pussy+]这件事感到并不满足，[npc.she]慢慢地挪动[npc.hips]，直到将[npc2.namePos][npc2.cock+]也压入[npc.her][npc.labia+]，"
										+ "[npc.she]发出一小声[npc.moan]，温柔地向后压，强迫[npc2.name]和[npc3.name]一起双插[npc.her][npc.pussy+]。",
								"[npc.Name]不止想被[npc3.name]操，便迷乱地后压住[npc2.namePos][npc2.cock]，让[npc2.cockHead]钻在[npc.her][npc.labia+]间"
										+ "然后缓缓向后拱身，强迫[npc2.herHim]加入[npc3.name]，双插[npc.her][npc.pussy+]。")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"[npc.Name]对只有[npc3.namePos]操干[npc.her][npc.pussy+]这件事感到并不满足，[npc.she]激烈地挪动[npc.hips]，直到将[npc2.namePos][npc2.cock+]也压入[npc.her][npc.labia+]，"
										+ "[npc.she]发出一阵[npc.a_moan+]，粗暴地向后撞，强迫[npc2.name]和[npc3.name]一起双插[npc.her][npc.pussy+]。",
								"[npc.Name]不止想被[npc3.name]操，便粗暴地后压住[npc2.namePos][npc2.cock]，让[npc2.cockHead]钻在[npc.her][npc.labia+]间"
										+ "然后支配地向后拱身，强迫[npc2.herHim]加入[npc3.name]，双插[npc.her][npc.pussy+]。")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"[npc.Name]对只有[npc3.namePos]操干[npc.her][npc.pussy+]这件事感到并不满足，[npc.she]向后挪动[npc.hips]，直到将[npc2.namePos][npc2.cock+]也压入[npc.her][npc.labia+]，"
										+ "[npc.she]发出一阵[npc.a_moan+]，向后压去，强迫[npc2.name]和[npc3.name]一起双插[npc.her][npc.pussy+]。",
								"[npc.Name]不止想被[npc3.name]操，便后压住[npc2.namePos][npc2.cock]，让[npc2.cockHead]钻在[npc.her][npc.labia+]间"
										+ "然后向后拱身，强迫[npc2.herHim]加入[npc3.name]，双插[npc.her][npc.pussy+]。")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"[npc.Name]对只有[npc3.namePos]操干[npc.her][npc.pussy+]这件事感到并不满足，[npc.she]急切地挪动[npc.hips]，直到将[npc2.namePos][npc2.cock+]也压入[npc.her][npc.labia+]，"
										+ "[npc.she]发出一阵[npc.a_moan+]，贪婪地向后压去，强迫[npc2.name]和[npc3.name]一起双插[npc.her][npc.pussy+]。",
								"[npc.Name]不止想被[npc3.name]操，便迷乱地后压住[npc2.namePos][npc2.cock]，让[npc2.cockHead]钻在[npc.her][npc.labia+]间"
										+ "然后渴盼地向后拱身，强迫[npc2.herHim]加入[npc3.name]，一起双插[npc.her][npc.pussy+]。")));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.herHim]的身体，发出一声轻柔的[npc2.moan]，"
										+ "温柔地将[npc2.cock]向前顶到[npc3.namePos]的[npc3.cock]旁边，开始和[npc3.name]一起操[npc.namePos][npc.pussy+]。",
								"[npc2.name]轻柔地[npc2.moan]着，温柔地挺进自己的[npc2.cock]，"
										+ "将它深深插进[npc.namePos][npc.pussy+]，开始和[npc3.name]一起操[npc.herHim]。")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "[npc2.she]粗暴地将[npc2.cock]向前顶到[npc3.namePos]的[npc3.cock]旁边，开始和[npc3.name]一起操[npc.namePos][npc.pussy+]。",
								"[npc2.name]发出一阵[npc2.a_moan+]，粗暴地猛塞自己的[npc2.cock]，"
										+ "[npc2.she]与[npc3.name]一起无情地暴操[npc.name]，宣告着自己的支配权。")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "[npc2.she]将[npc2.cock]向前顶到[npc3.namePos]的[npc3.cock]旁边，开始和[npc3.name]一起操[npc.namePos][npc.pussy+]。",
								"[npc2.name]发出一阵[npc2.a_moan+]，向前挺进[npc2.cock]，将它深深插入[npc.namePos][npc.pussy+]，开始和[npc3.name]一起操[npc.herHim]。")));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"[npc.name]紧紧裹住[npc2.name]的[npc2.cock]，[npc2.name]不禁发出一声[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着推开[npc.name]，拼命地想把自己[npc2.cock+]从对方[npc.pussy+]里拔出来。",
								"[npc.Name]强行将[npc2.name]的[npc2.cock]深深插入自己[npc.pussy+]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.name]的身体，发出一阵[npc2.a_moan+]，"
										+ "[npc2.she]急切地将[npc2.cock]向前顶到[npc3.namePos]的[npc3.cock]旁边，开始亢奋地和[npc3.name]一起操[npc.namePos][npc.pussy+]。",
								"[npc2.name]发出一阵[npc2.a_moan+]，热切地顶进自己的[npc2.cock]，"
										+ "将它深深插进[npc.namePos][npc.pussy+]，开始和[npc3.name]一起竭力地操[npc.herHim]。")));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc2.name]无法将[npc2.cock]从[npc.namePos]的[npc.pussy]中拔出，"
										+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
								"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
										+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her]和[npc3.namePos][npc2.cock+]在自己[npc.pussy+]中继续抽插。",
								"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.cock]从[npc.namePos][npc.pussy+]中抽离。"));
					case DOM_GENTLE:
						return UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc2.Name]温柔地将[npc2.cock+]滑入[npc.namePos][npc.pussy+]深处，"
										+ "[npc2.she]发出柔和的[npc2.moan]，与[npc3.name]一起轻柔地抽插[npc.namePos][npc.pussy+]。",
								"[npc2.name]慢慢地挺动[npc2.cock+]，与[npc3.namePos]一起深深插入[npc.namePos][npc.pussy+]，口中飘出一声轻柔的[npc2.moan]。",
								"[npc2.name]愉悦地[npc2.moaning]着，温柔地挺动[npc2.cock+]，与[npc3.name]一起深深插入[npc.namePos][npc.pussy+]。"));
					case DOM_ROUGH:
						return UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc2.Name]粗暴地将[npc2.cock+]深深插入[npc.namePos][npc.pussy+]，"
										+ "[npc2.she]发出一阵[npc2.a_moan+]，与[npc3.name]一起粗暴地撞入[npc.namePos][npc.pussy+]。",
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地挺动[npc2.cock+]，与[npc3.namePos]一起深深插入[npc.namePos][npc.pussy+]。",
								"[npc2.name]愉悦地[npc2.moaning]着，粗暴地挺动[npc2.cock+]，与[npc3.name]一起深深插入[npc.namePos][npc.pussy+]。"));
					case SUB_NORMAL:
						return UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc2.Name]将[npc2.cock+]深深插入[npc.namePos][npc.pussy+]，"
										+ "[npc2.she]发出柔和的[npc2.moan]，与[npc3.name]一起粗暴地抽插[npc.namePos][npc.pussy+]。",
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]挺动[npc2.cock+]，与[npc3.namePos]一起深深插入[npc.namePos][npc.pussy+]。",
								"[npc2.name]愉悦地[npc2.moaning]着，开始挺动[npc2.cock+]，与[npc3.name]一起深深插入[npc.namePos][npc.pussy+]。"));
					default:
						return UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc2.Name]贪婪地将[npc2.cock+]插入[npc.namePos][npc.pussy+]，"
										+ "[npc2.she]发出一阵[npc2.a_moan+]，与[npc3.name]一起热切地撞入[npc.namePos][npc.pussy+]。",
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]急切地挺动[npc2.cock+]，与[npc3.namePos]一起深深插入[npc.namePos][npc.pussy+]。",
								"[npc2.name]愉悦地[npc2.moaning]着，急切地挺动[npc2.cock+]，与[npc3.name]一起深深插入[npc.namePos][npc.pussy+]。"));
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return (UtilText.returnStringAtRandom(
								"[npc2.name]无法将[npc2.cock]从[npc.namePos]的[npc.pussy]中拔出，"
										+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
								"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
										+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.cock+]在自己[npc.pussy+]中继续抽插。",
								"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.cock]从[npc.namePos][npc.pussy+]中抽离。"));
					case DOM_GENTLE:
						return (UtilText.returnStringAtRandom(
								"[npc2.Name]温柔地将[npc2.cock+]滑入[npc.namePos][npc.pussy+]深处，"
										+ "[npc2.she]发出柔和的[npc2.moan]，轻柔地抽插[npc.namePos][npc.pussy+]。",
								"[npc2.name]慢慢地将[npc2.cock+]挺入[npc.namePos][npc.pussy+]，口中飘出一声轻柔的[npc2.moan]。",
								"[npc2.name]愉悦地[npc2.moaning]着，温柔地用[npc2.cock+]深入[npc.namePos][npc.pussy+]。"));
					case DOM_ROUGH:
						return (UtilText.returnStringAtRandom(
								"[npc2.Name]粗暴地将[npc2.cock+]深深插入[npc.namePos][npc.pussy+]，"
										+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地插入[npc.namePos][npc.pussy+]。",
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地将[npc2.cock+]深深插入[npc.namePos][npc.pussy+]。",
								"[npc2.name]愉悦地[npc2.moaning]着，粗暴地顶着[npc2.her][npc2.cock+]，拼命要插入[npc.namePos][npc.pussy+]最深处。"));
					case SUB_NORMAL:
						return (UtilText.returnStringAtRandom(
								"[npc2.Name]将[npc2.cock+]深深插入[npc.namePos][npc.pussy+]，"
										+ "[npc2.she]发出一阵[npc2.a_moan+]，不停抽插着[npc.namePos][npc.pussy+]。",
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]深深插入[npc.namePos][npc.pussy+]。",
								"[npc2.name]愉悦地[npc2.moaning]着，开始将[npc2.cock+]深深插入[npc.namePos][npc.pussy+]。"));
					default:
						return (UtilText.returnStringAtRandom(
								"[npc2.Name]贪婪地将[npc2.cock+]插入[npc.namePos][npc.pussy+]，"
										+ "[npc2.she]发出[npc2.a_moan+]，亢奋地插入[npc.namePos][npc.pussy+]。",
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]深深插入[npc.namePos][npc.pussy+]。",
								"[npc2.name]愉悦地[npc2.moaning]着，热切地挺动[npc2.her][npc2.cock+]，拼命要插入[npc.namePos][npc.pussy+]最深处。"));
				}
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_PENIS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "被操(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地用你[npc.pussy+]吞吐着[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"[npc.name]发出一声轻柔的[npc.moan]，温柔地挺起[npc.hips]，以便两根[npc2.cocks(true)]可以在自己[npc.pussy+]中插得更深。",
						"[npc.name]发出一声轻柔的[npc.moan]，温柔地将[npc.hips]向后拱起，迫使#IF(npc3.isPlayer())[npc3.namePos]和[npc2.namePos]#ELSE[npc2.namePos]和[npc3.namePos]#ENDIF[npc2.cocks+(true)]在自己[npc.pussy+]中插得更深。",
						"[npc.namePos][npc.lips+]间飘出一声轻柔的[npc.moan]，[npc.her]慢慢地扭动[npc.hips]，设法让两根[npc2.cocks(true)]深深插入[npc.her][npc.pussy+]。")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]发出一声轻柔的[npc.moan]，温柔地挺起[npc.hips]，以便[npc2.namePos][npc2.cock+]可以在自己[npc.pussy+]中插得更深。",
						"[npc.name]发出一声轻柔的[npc.moan]，温柔地将[npc.hips]向后拱起，迫使[npc2.namePos][npc2.cock+]在自己[npc.pussy+]中插得更深。",
						"[npc.namePos][npc.lips+]间飘出一声轻柔的[npc.moan]，[npc.her]慢慢地扭动[npc.hips]，设法让[npc2.namePos][npc2.cock+]深深插入[npc.her][npc.pussy+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "被操";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地用你[npc.pussy+]吞吐[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"[npc.name]发出一阵[npc.a_moan+]，饥渴地拱起[npc.hips]，以便两根[npc2.cocks(true)]可以在自己[npc.pussy+]中插得更深。",
						"[npc.name]发出一阵[npc.a_moan+]，亢奋地将[npc.hips]向后拱起，迫使#IF(npc3.isPlayer())[npc3.namePos]和[npc2.namePos]#ELSE[npc2.namePos]和[npc3.namePos]#ENDIF[npc2.cocks+(true)]在自己[npc.pussy+]中插得更深。",
						"[npc.namePos]积极地扭动[npc.hips]，[npc.lips+]间飘出一声[npc.a_moan+]，[npc.her]设法让两根[npc2.cocks(true)]深深插入自己[npc.pussy+]。")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]发出一阵[npc.a_moan+]，饥渴地拱起[npc.hips]，以便[npc2.namePos][npc2.cock+]可以在自己[npc.pussy+]中插得更深。",
						"[npc.name]发出一阵[npc.a_moan+]，亢奋地拱起[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己[npc.pussy+]中插得更深。",
						"[npc.namePos]积极地扭动[npc.hips]，[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.cock+]深深插入自己[npc.pussy+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "被操(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地用你[npc.pussy+]吞吐[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"[npc.name]发出一阵[npc.a_moan+]，粗暴地冲撞[npc.hips]，以便两根[npc2.cocks(true)]可以在自己[npc.pussy+]中插得更深。",
						"[npc.name]发出一阵[npc.a_moan+]，粗暴地将[npc.hips]向后拱起，迫使#IF(npc3.isPlayer())[npc3.namePos]和[npc2.namePos]#ELSE[npc2.namePos]和[npc3.namePos]#ENDIF[npc2.cocks+(true)]在自己[npc.pussy+]中插得更深。",
						"[npc.namePos]激烈地扭动[npc.hips]，[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]粗暴地让两根[npc2.cocks(true)]深深插入自己[npc.pussy+]。")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]发出一阵[npc.a_moan+]，暴力地撞击[npc.hips]，强迫[npc2.namePos][npc2.cock+]深深插入自己[npc.pussy+]。",
						"[npc.name]发出一阵[npc.a_moan+]，粗暴地拱起[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己[npc.pussy+]中插得更深。",
						"[npc.namePos]激烈地扭动[npc.hips]，[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]粗暴地让[npc2.namePos][npc2.cock+]深深插入自己[npc.pussy+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_PENIS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "被操";
		}

		@Override
		public String getActionDescription() {
			return "用你[npc.pussy+]吞吐着[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"[npc.name]发出一阵[npc.a_moan+]，迅速拱起[npc.hips]，以便两根[npc2.cocks(true)]可以在自己[npc.pussy+]中插得更深。",
						"[npc.name]发出一阵[npc.a_moan+]，将[npc.hips]向后拱起，迫使#IF(npc3.isPlayer())[npc3.namePos]和[npc2.namePos]#ELSE[npc2.namePos]和[npc3.namePos]#ENDIF[npc2.cocks+(true)]在自己[npc.pussy+]中插得更深。",
						"[npc.name]扭动[npc.hips]，[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让两根[npc2.cocks(true)]深深插入自己[npc.pussy+]。")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]发出一阵[npc.a_moan+]，迅速拱起[npc.hips]，以便[npc2.namePos][npc2.cock+]可以在自己[npc.pussy+]中插得更深。",
						"[npc.name]发出一阵[npc.a_moan+]，开始拱起[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己[npc.pussy+]中插得更深。",
						"[npc.name]扭动[npc.hips]，[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.cock+]深深插入自己[npc.pussy+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "被操(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地用你[npc.pussy+]吞吐[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"[npc.name]发出一阵[npc.a_moan+]，饥渴地拱起[npc.hips]，以便两根[npc2.cocks(true)]可以在自己[npc.pussy+]中插得更深。",
						"[npc.name]发出一阵[npc.a_moan+]，亢奋地将[npc.hips]向后拱起，迫使#IF(npc3.isPlayer())[npc3.namePos]和[npc2.namePos]#ELSE[npc2.namePos]和[npc3.namePos]#ENDIF[npc2.cocks+(true)]在自己[npc.pussy+]中插得更深。",
						"[npc.namePos]积极地扭动[npc.hips]，[npc.lips+]间飘出一声[npc.a_moan+]，[npc.her]设法让两根[npc2.cocks(true)]深深插入自己[npc.pussy+]。")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]发出一阵[npc.a_moan+]，饥渴地拱起[npc.hips]，以便[npc2.namePos][npc2.cock+]可以在自己[npc.pussy+]中插得更深。",
						"[npc.name]发出一阵[npc.a_moan+]，亢奋地拱起[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己[npc.pussy+]中插得更深。",
						"[npc.namePos]积极地扭动[npc.hips]，[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.cock+]深深插入自己[npc.pussy+]。"));
			}

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抗拒被操";
		}

		@Override
		public String getActionDescription() {
			return "努力让你[npc.pussy+]远离[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
										+ "[npc.she]无力地试着将#IF(npc3.isPlayer())[npc3.namePos]和[npc2.namePos]#ELSE[npc2.namePos]和[npc3.namePos]#ENDIF的[npc2.cocks(true)]从自己[npc.pussy+]里一起拔出来。",
								"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.pussy]从令人憎恶的双重插入中抽离，"
										+ "[npc.she]绝望地挣扎着，但两根[npc2.cocks(true)]依然从容地在[npc.her][npc.pussy+]里滑进滑出。",
								"[npc.name]拼命地尝试把[npc.hips]挪开，"
										+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.name]依然与[npc3.name]一起，温柔地将[npc2.cock+]滑进[npc.her][npc.pussy+]深处。")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
										+ "[npc.she]无力地试着将#IF(npc3.isPlayer())[npc3.namePos]和[npc2.namePos]#ELSE[npc2.namePos]和[npc3.namePos]#ENDIF的[npc2.cocks(true)]从自己[npc.pussy+]里一起拔出来。",
								"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.pussy]从令人憎恶的双重插入中抽离，"
										+ "[npc.she]绝望地挣扎着，但两根[npc2.cocks(true)]依然粗暴地在[npc.her][npc.pussy+]里抽送爆操。",
								"[npc.name]拼命地尝试把[npc.hips]挪开，"
										+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.name]依然与[npc3.name]一起，激烈地将[npc2.cock+]插入[npc.her][npc.pussy+]深处。")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(PenisVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
										+ "[npc.she]无力地试着将#IF(npc3.isPlayer())[npc3.namePos]和[npc2.namePos]#ELSE[npc2.namePos]和[npc3.namePos]#ENDIF的[npc2.cocks(true)]从自己[npc.pussy+]里一起拔出来。",
								"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.pussy]从令人憎恶的双重插入中抽离，"
										+ "[npc.she]绝望地挣扎着，但两根[npc2.cocks(true)]依然疯狂地在[npc.her][npc.pussy+]里抽送爆操。",
								"[npc.name]拼命地尝试把[npc.hips]挪开，"
										+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.name]依然与[npc3.name]一起，贪婪地将[npc2.cock+]插入[npc.her][npc.pussy+]深处。")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
										+ "[npc.she]无力地试着将[npc2.namePos][npc2.cock]从自己[npc.pussy+]里拔出来。",
								"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.pussy]从[npc2.namePos]令人憎恶的性器抽离，"
										+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.cock+]依然从容地在[npc.her][npc.pussy+]里滑进滑出。",
								"[npc.name]拼命地尝试把[npc.hips]挪开，"
										+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然温柔地滑进[npc.her][npc.pussy+]深处。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
										+ "[npc.she]无力地试着将[npc2.namePos][npc2.cock]从自己[npc.pussy+]里拔出来。",
								"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.pussy]从[npc2.namePos]令人憎恶的性器抽离，"
										+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.cock+]依然粗暴地在[npc.her][npc.pussy+]里抽送爆操。",
								"[npc.name]拼命地尝试把[npc.hips]挪开，"
										+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然激烈地插入[npc.her][npc.pussy+]深处。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
										+ "[npc.she]无力地试着将[npc2.namePos][npc2.cock]从自己[npc.pussy+]里拔出来。",
								"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.pussy]从[npc2.namePos]令人憎恶的性器抽离，"
										+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.cock+]依然疯狂地在[npc.her][npc.pussy+]里抽送爆操。",
								"[npc.name]拼命地尝试把[npc.hips]挪开，"
										+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然贪婪地插入[npc.her][npc.pussy+]深处。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "停止被操";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.her]的[npc2.cock]从你[npc.pussy+]里拔出来。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]猛地将[npc2.namePos]的[npc2.cock]从自己[npc.pussy+]里抽出，[npc.she]愤怒地咆哮着，命令[npc2.name]不准再操了。",
								"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]的[npc2.cock]从自己[npc.pussy+]中抽出。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]将[npc2.namePos]的[npc2.cock]从自己[npc.pussy+]中抽出，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]不要再操了。",
								"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc2.her]的[npc2.cock]从自己[npc.pussy+]中抽出。"));
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
									"[npc.Name]不再让[npc2.name]操自己[npc.pussy+]，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
									"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续抽插[npc.namePos][npc.pussy+]的渴望。"));
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]猛地将[npc2.namePos]的[npc2.cock]从自己[npc.pussy+]里抽出，[npc.she]愤怒地咆哮着，命令[npc2.name]不准再操了。",
								"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]的[npc2.cock]从自己[npc.pussy+]中抽出。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]将[npc2.namePos]的[npc2.cock]从自己[npc.pussy+]中抽出，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]不要再操了。",
								"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc2.her]的[npc2.cock]从自己[npc.pussy+]中抽出。"));
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
									"[npc.Name]不再让[npc2.name]操自己[npc.pussy+]，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
									"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续抽插[npc.namePos][npc.pussy+]的渴望。"));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
