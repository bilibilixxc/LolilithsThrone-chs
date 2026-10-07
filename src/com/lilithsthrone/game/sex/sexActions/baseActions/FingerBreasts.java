package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
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
public class FingerBreasts {
	
	public static final SexAction FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "揉搓乳房";
		}

		@Override
		public String getActionDescription() {
			return "挤压一下[npc2.namePos][npc2.breasts+]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()
					&& Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.HANDS)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.NIPPLES)){
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]把手伸向[npc2.namePos]的胸部，开始爱抚和摸索[npc2.namePos]那[npc2.breastRows][npc2.breasts+]，发出轻柔的[npc.moan]，"
										+ "温柔地隔着[npc2.lowClothing(NIPPLES)]揉压下面[npc2.nipples+]。",

								"[npc.Name]发现自己无法抵抗[npc2.namePos][npc2.breasts+]的诱惑，于是[npc.she]伸出[npc.hands+]，轻柔地"
									+ "抵住[npc2.her]的[npc2.topClothing(NIPPLES)]，然后开始轻柔地摸索并挤压[npc2.her]的胸部。",

								"[npc.Name]用[npc.fingers]磨蹭着[npc2.namePos]的[npc2.topClothing(NIPPLES)]，温柔地摸索和爱抚起[npc2.her][npc2.breasts+]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]抚上[npc2.namePos]的胸部，发出[npc.a_moan+]，热切地摸索爱抚着[npc2.her]的[npc2.breastRows][npc2.breasts+]，"
										+ "开始隔着[npc2.lowClothing(NIPPLES)]揉压下面[npc2.nipples+]。",

								"[npc.Name]发现自己无法抵抗[npc2.namePos][npc2.breasts+]的诱惑，于是[npc.she]伸出[npc.hands+]，急切地"
									+ "抵住[npc2.her]的[npc2.topClothing(NIPPLES)]，开始摸索和挤压[npc2.her]的胸部。",

								"[npc.Name]用[npc.fingers]抚过[npc2.namePos]的[npc2.topClothing(NIPPLES)]，开始急切地揉捏挑拢着[npc2.her][npc2.breasts+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]抚上[npc2.namePos]的胸，发出[npc.a_moan+]，开始粗暴地挤弄揉捏那[npc2.breastRows][npc2.breasts+]，"
										+ "激烈地磨捻[npc2.her][npc2.lowClothing(NIPPLES)]，以及其下[npc2.nipples+]。",

								"[npc.Name]难以抵抗[npc2.namePos][npc2.breasts+]的诱惑，于是[npc.she]伸出[npc.hands+]，激烈地"
									+ "压住[npc2.her]的[npc2.topClothing(NIPPLES)]，然后粗暴地挤压榨弄[npc2.her]的胸部。",

								"[npc.Name]将[npc.fingers]伸入[npc2.namePos]的[npc2.topClothing(NIPPLES)]，开始粗暴地揉捏挑拢着那[npc2.breasts+]。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]抚上[npc2.namePos]的胸部，发出[npc.a_moan+]，热切地摸索爱抚着[npc2.her]的[npc2.breastRows][npc2.breasts+]，"
										+ "开始隔着[npc2.lowClothing(NIPPLES)]揉压下面[npc2.nipples+]。",

								"[npc.Name]发现自己无法抵抗[npc2.namePos][npc2.breasts+]的诱惑，于是[npc.she]伸出[npc.hands+]，急切地"
									+ "抵住[npc2.namePos]的[npc2.topClothing(NIPPLES)]，开始摸索和挤压[npc2.her]的胸部。",

								"[npc.Name]用[npc.fingers]抚过[npc2.namePos]的[npc2.topClothing(NIPPLES)]，开始急切地揉捏挑拢着[npc2.her][npc2.breasts+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]抚上[npc2.namePos]的胸部，发出[npc.a_moan+]，摸索爱抚着[npc2.her]的[npc2.breastRows][npc2.breasts+]，"
										+ "开始隔着[npc2.lowClothing(NIPPLES)]揉压下面[npc2.nipples+]。",

								"[npc.Name]发现自己无法抵抗[npc2.namePos][npc2.breasts+]的诱惑，于是[npc.she]伸出[npc.hands+]"
									+ "抵住[npc2.her]的[npc2.topClothing(NIPPLES)]，开始摸索和挤压[npc2.her]的胸部。",

								"[npc.Name]用[npc.fingers]磨蹭着[npc2.namePos]的[npc2.topClothing(NIPPLES)]，摸索和爱抚起[npc2.her][npc2.breasts+]。"));
						break;
					default:
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出一声柔和的[npc2.moan]以回应[npc.namePos]的触摸，然后温柔地鼓励[npc.name]继续全神贯注于自己[npc2.breasts+]。",
	
									"[npc2.name]发出一声轻柔的[npc2.moan]，缓慢地挤捏着自己的胸部，恳求[npc.name]继续。",
	
									"在[npc.namePos]的触碰下，[npc2.name]轻柔地[npc2.moaning]着，鼓励着[npc.name]继续玩弄[npc2.her][npc2.breasts+]。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出一声[npc2.a_moan+]以回应[npc.namePos]的触摸，然后急切地鼓励[npc.name]继续全神贯注于自己[npc2.breasts+]。",
	
									"[npc2.name]发出[npc2.a_moan+]，饥渴地挤捏着自己的胸部，恳求[npc.name]继续。",
	
									"在[npc.namePos]的触碰下，[npc2.name][npc2.moaning+]着，急切地鼓励着[npc.name]继续玩弄[npc2.her][npc2.breasts+]。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出一声[npc2.a_moan+]以回应[npc.namePos]的触摸，然后粗暴地咆哮着让[npc.name]继续全神贯注于自己[npc2.breasts+]。",
	
									"[npc2.name]发出[npc2.a_moan+]，挺起了自己的胸部，语气坚定地"
											+ "命令[npc.name]继续全神贯注于自己[npc2.breasts+]。",
	
									"[npc2.name]发出一声[npc2.a_moan+]以回应[npc.namePos]的触摸，然后命令[npc.name]继续玩弄自己[npc2.breasts+]。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出一声[npc2.a_moan+]以回应[npc.namePos]的触摸，然后急切地鼓励[npc.name]继续全神贯注于自己[npc2.breasts+]。",
	
									"[npc2.name]发出[npc2.a_moan+]，饥渴地挤捏着自己的胸部，恳求[npc.name]继续。",
	
									"在[npc.namePos]的触碰下，[npc2.name][npc2.moaning+]着，急切地鼓励着[npc.name]继续玩弄[npc2.her][npc2.breasts+]。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出一声[npc2.a_moan+]以回应[npc.namePos]的触摸，然后鼓励[npc.name]继续全神贯注于自己[npc2.breasts+]。",
	
									"[npc2.name]发出[npc2.a_moan+]，挤捏着自己的胸部，恳求[npc.name]继续。",
	
									"在[npc.namePos]的触碰下，[npc2.name][npc2.moaning+]着，鼓励[npc.name]继续玩弄[npc2.her][npc2.breasts+]。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name]本能地向后缩，"
											+ "[npc2.sobbing]并挣扎抵抗着[npc.namePos]的触碰，同时试图将[npc.namePos]的[npc.fingers]从自己[npc2.breasts+]上推开。",
	
									"[npc2.Name]发出[npc2.a_sob+]，不安地扭动着，"
											+ "[npc2.she]乞求[npc.name]放过自己，但[npc.name]继续玩弄着[npc2.her][npc2.breasts+]。",
	
									"作为对[npc.namePos]触摸的回应，[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc.name]不断玩弄[npc2.her][npc2.breasts+]，"
											+"[npc2.she]奋力反抗着[npc.name]。"));
							break;
						default:
							break;
					}
				}
				switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append("随着[npc.Name]挤榨[npc2.nipples+]的动作，几滴[npc2.milk]浸湿了[npc2.namePos]的[npc2.lowClothing(NIPPLES)]。");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append("随着[npc.name]挤榨[npc2.nipples+]的动作，少许[npc2.milk]浸湿了[npc2.namePos]的[npc2.lowClothing(NIPPLES)]。");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append("[npc.Name]揉捏着[npc2.namePos][npc2.nipples+]，一缕[npc2.milk]喷到了[npc2.her]的[npc2.lowClothing(NIPPLES)]上。");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append("[npc2.NamePos]的[npc2.milk]流到了[npc2.lowClothing(NIPPLES)]上，"
								+ "[npc2.name]感受到液体从[npc2.her][npc2.breasts+]上流下，不禁[npc2.moanVerb]着。");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append("[npc.Name]不断揉捏着[npc2.NamePos][npc2.nipples+]，一小股[npc2.milk]垂落到了[npc2.her]的[npc2.lowClothing(NIPPLES)]上"
								+ "。");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append("[npc2.namePos]的[npc2.milk]开始源源不断地涌出，迅速浸湿了[npc2.her]的[npc2.lowClothing(NIPPLES)]。");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append("[npc2.namePos]的[npc2.milk]开始源源不断地倾泄而下，迅速地浸湿了[npc2.her]的[npc2.lowClothing(NIPPLES)]。");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]抚上[npc2.namePos]的胸部，开始温柔地爱抚和摸索[npc2.namePos]那[npc2.breastRows][npc2.breasts+]，发出一声轻柔的[npc.moan]。",

								"[npc.Name]发现自己无法抵抗[npc2.namePos][npc2.breasts+]的诱惑，"
										+ "[npc.she]开始温柔地挤压揉捏[npc2.namePos]裸露的胸部。",

								"[npc.Name]用[npc.fingers]挑逗着[npc2.namePos]裸露的胸部，开始温柔地揉捏挑拢着[npc2.namePos][npc2.breasts+]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]抚上[npc2.namePos]的胸部，发出[npc.a_moan+]，开始热切地摸索和爱抚[npc2.her]的[npc2.breastRows][npc2.breasts+]。",

								"[npc.Name]发现自己无法抵抗[npc2.namePos][npc2.breasts+]的诱惑，"
										+ "[npc.she]开始急切地挤压揉捏[npc2.namePos]裸露的胸部。",

								"[npc.Name]用[npc.fingers]挑逗着[npc2.namePos]裸露的胸部，开始急切地揉捏挑拢着[npc2.namePos][npc2.breasts+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]抚上[npc2.namePos]的胸，发出[npc.a_moan+]，开始粗暴地挤弄揉捏那[npc2.breastRows][npc2.breasts+]。",

								"[npc.Name]发现自己无法抵抗[npc2.namePos][npc2.breasts+]的诱惑，"
										+ "[npc.she]开始粗暴地挤压揉捏[npc2.namePos]裸露的胸部。",

								"[npc.Name]将[npc.fingers]摸向[npc2.namePos]裸露的胸部，开始粗暴地揉捏挑拢着那[npc2.breasts+]。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]抚上[npc2.namePos]的胸部，发出[npc.a_moan+]，开始热切地摸索和爱抚[npc2.her]的[npc2.breastRows][npc2.breasts+]。",

								"[npc.Name]发现自己无法抵抗[npc2.namePos][npc2.breasts+]的诱惑，"
										+ "[npc.she]开始急切地挤压揉捏[npc2.namePos]裸露的胸部。",

								"[npc.Name]用[npc.fingers]挑逗着[npc2.namePos]裸露的胸部，开始急切地揉捏挑拢着[npc2.namePos][npc2.breasts+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]抚上[npc2.namePos]的胸部，发出[npc.a_moan+]，开始摸索和爱抚[npc2.her]的[npc2.breastRows][npc2.breasts+]。",

								"[npc.Name]发现自己无法抵抗[npc2.namePos][npc2.breasts+]的诱惑，"
										+ "[npc.she]开始挤压揉捏[npc2.namePos]裸露的胸部。",

								"[npc.Name]用[npc.fingers]挑逗着[npc2.namePos]裸露的胸部，开始揉捏挑拢着[npc2.namePos][npc2.breasts+]。"));
						break;
					default:
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出一声柔和的[npc2.moan]以回应[npc.namePos]的触摸，"
											+ "然后温柔地鼓励[npc.name]继续全神贯注于自己[npc2.breasts+]。",
	
									"[npc2.name]发出一声轻柔的[npc2.moan]，缓慢地挤捏着自己的胸部，恳求[npc.name]继续。",
	
									"在[npc.namePos]的触碰下，[npc2.name]轻柔地[npc2.moaning]着，鼓励着[npc.name]继续玩弄[npc2.her][npc2.breasts+]。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为对[npc.namePos]触摸的回应，[npc2.Name]的口中漏出了[npc2.a_moan+]，"
											+ "然后急切地鼓励[npc.name]继续全神贯注于自己[npc2.breasts+]。",
	
									"[npc2.name]发出[npc2.a_moan+]，饥渴地挤捏着自己的胸部，恳求[npc.herHim]继续。",
	
									"在[npc.namePos]的触碰下，[npc2.name][npc2.moaning+]着，急切地鼓励着[npc.name]继续玩弄[npc2.her][npc2.breasts+]。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为对[npc.namePos]触摸的回应，[npc2.Name]的口中漏出了[npc2.a_moan+]，"
											+ "然后粗暴地咆哮着让[npc.name]继续全神贯注于自己[npc2.breasts+]。",
	
									"[npc2.name]发出[npc2.a_moan+]，挺起了自己的胸部，语气坚定地命令[npc.name]继续弄出一些淫荡的声音。",
	
									"[npc2.name]发出一声[npc2.a_moan+]以回应[npc.namePos]的触摸，然后命令[npc.name]继续玩弄自己[npc2.breasts+]。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为对[npc.namePos]触摸的回应，[npc2.Name]的口中漏出了[npc2.a_moan+]，"
											+ "然后急切地鼓励[npc.name]继续全神贯注于自己[npc2.breasts+]。",
	
									"[npc2.name]发出[npc2.a_moan+]，饥渴地挤捏着自己的胸部，恳求[npc.herHim]继续。",
	
									"在[npc.namePos]的触碰下，[npc2.name][npc2.moaning+]着，急切地鼓励着[npc.name]继续玩弄[npc2.her][npc2.breasts+]。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为对[npc.namePos]触摸的回应，[npc2.Name]的口中漏出了[npc2.a_moan+]，"
											+ "然后鼓励[npc.name]继续全神贯注于自己[npc2.breasts+]。",
	
									"[npc2.name]发出[npc2.a_moan+]，挤捏着自己的胸部，恳求[npc.herHim]继续。",
	
									"在[npc.namePos]的触碰下，[npc2.name][npc2.moaning+]着，鼓励[npc.name]继续玩弄[npc2.her][npc2.breasts+]。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name]本能地向后缩，"
											+ "[npc2.sobbing]并挣扎抵抗着[npc.namePos]的触碰，同时试图将[npc.namePos]的[npc.fingers]从自己[npc2.breasts+]上推开。",
	
									"[npc2.Name]发出[npc2.a_sob+]，不安地扭动着，"
											+ "[npc2.she]乞求[npc.name]放过自己，但[npc.name]继续玩弄着[npc2.her][npc2.breasts+]。",
	
									"作为对[npc.namePos]触摸的回应，[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，"
											+ "[npc.name]不断玩弄着[npc2.her][npc2.breasts+]，[npc2.name]奋力反抗着[npc.name]。"));
							break;
						default:
							break;
					}
				}
				switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append("随着[npc.name]挤榨[npc2.namePos][npc2.nipples+]的动作，几滴[npc2.milk]从[npc.fingers]间滴出。");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append("随着[npc.name]挤榨[npc2.namePos][npc2.nipples+]的动作，一小股[npc2.milk]从[npc.fingers]间滴出。");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append("随着[npc.name]挤榨[npc2.namePos][npc2.nipples+]的动作，一缕[npc2.milk]从[npc.fingers]间溢出。");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append("[npc2.NamePos]的[npc2.milk]开始从[npc.namePos]的[npc.fingers]间流出，"
								+ "[npc2.name]感受到液体从[npc2.her][npc2.breasts+]上流下，不禁[npc2.moanVerb]着。");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append("随着[npc.name]挤榨[npc2.namePos][npc2.nipples+]的动作，[npc2.milk]顺着[npc.fingers]间一小股一小股地涌出。");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append("[npc2.her]的[npc2.milk]立刻开始源源不断地涌出，很快浸湿了[npc2.her][npc2.breasts+]，滴落在身下的地板上。");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append("[npc2.namePos]的[npc2.milk]立刻开始源源不断地倾泻而下，"
								+ "迅速浸湿了[npc2.her][npc2.breasts+]，在[npc2.herHim]身下聚成一大滩。");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects(){
			if(!Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.NIPPLES)
					&& Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk().getMinimumValue()>=Lactation.ONE_TRICKLE.getMinimumValue()
					&& Main.sex.getCharacterTargetedForSexAction(this).getLowestZLayerCoverableArea(CoverableArea.NIPPLES)!=null) {
				Main.sex.getCharacterTargetedForSexAction(this).getLowestZLayerCoverableArea(CoverableArea.NIPPLES).setDirty(Main.sex.getCharacterTargetedForSexAction(this), true);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).incrementBreastStoredMilk(-10);
		}
		
	};
	
	
	public static final SexAction FORCE_FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "被揉搓乳房";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]好好揉捏一下你[npc.breasts+]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreasts()
					&& Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.HANDS)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue();
		}

		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES)){

				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]拉住[npc2.namePos]的[npc2.hands]，将其引导到[npc.her]的胸部，"
										+ "[npc.she]发出了一声轻柔的[npc.moan]，然后将其压向自己[npc.breasts+]，"
										+ "使得[npc.her]的[npc.lowClothing(NIPPLES)]紧紧贴上[npc.her][npc.nipples+]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，然后将其温柔地引导压向[npc.her]的[npc.topClothing(NIPPLES)]，"
										+ "就这样把它们按在那里，以此鼓励[npc2.name]挤压揉捏[npc.she]的[npc.breasts]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，温柔地引导[npc2.her]那[npc2.fingers]压向[npc.her]的[npc.topClothing(NIPPLES)]，"
										+ "然后温柔地将它压向[npc.her][npc.breasts+]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]拉住[npc2.namePos]的[npc2.hands]，将其引导到[npc.her]的胸部，"
										+ "[npc.she]发出[npc.a_moan+]，然后急切地将其压向自己[npc.breasts+]，"
										+ "使得[npc.her]的[npc.lowClothing(NIPPLES)]紧紧贴上[npc.her][npc.nipples+]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，然后将其急切地引导压向[npc.her]的[npc.topClothing(NIPPLES)]，"
										+ "就这样把它们按在那里，以此鼓励[npc2.name]挤压揉捏[npc.she]的[npc.breasts]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，急切地引导[npc2.her]那[npc2.fingers]压向[npc.her]的[npc.topClothing(NIPPLES)]，"
										+ "然后亢奋地将它压向[npc.her][npc.breasts+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]拉住[npc2.namePos]的[npc2.hands]，将其拽向[npc.her]的胸部，"
										+ "[npc.she]发出[npc.a_moan+]，然后粗暴地将其压向自己[npc.breasts+]，"
										+ "使得[npc.her]的[npc.lowClothing(NIPPLES)]紧紧贴上[npc.her][npc.nipples+]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，然后粗暴地拉着它们抓向[npc.her]的[npc.topClothing(NIPPLES)]，"
										+ "就这样把它们按在那里，命令[npc2.name]挤压揉捏[npc.she]的[npc.breasts]。",

								"[npc.Name]拉住[npc2.namePos]的[npc2.hands]，将其粗暴地拉向自己的胸部，"
										+ "强迫[npc2.her]的[npc2.fingers]压向遮盖自己[npc.breasts+]的[npc.topClothing(NIPPLES)]。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]拉住[npc2.namePos]的[npc2.hands]，将其引导到[npc.her]的胸部，"
										+ "[npc.she]发出[npc.a_moan+]，然后急切地将其压向自己[npc.breasts+]，"
										+ "使得[npc.her]的[npc.lowClothing(NIPPLES)]紧紧贴上[npc.her][npc.nipples+]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，然后将其急切地引导压向[npc.her]的[npc.topClothing(NIPPLES)]，"
										+ "就这样把它们按在那里，以此鼓励[npc2.name]挤压揉捏[npc.she]的[npc.breasts]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，急切地引导[npc2.her]那[npc2.fingers]压向[npc.her]的[npc.topClothing(NIPPLES)]，"
										+ "然后亢奋地将它压向[npc.her][npc.breasts+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]拉住[npc2.namePos]的[npc2.hands]，将其引导到[npc.her]的胸部，"
										+ "[npc.she]发出[npc.a_moan+]，然后将其压向自己[npc.breasts+]，"
										+ "使得[npc.her]的[npc.lowClothing(NIPPLES)]紧紧贴上[npc.her][npc.nipples+]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，然后将其引导压向[npc.her]的[npc.topClothing(NIPPLES)]，"
										+ "就这样把它们按在那里，以此鼓励[npc2.name]挤压揉捏[npc.she]的[npc.breasts]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，引导[npc2.her]那[npc2.fingers]压向[npc.her]的[npc.topClothing(NIPPLES)]，"
									+ "然后将它压向[npc.her][npc.breasts+]。"));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出柔和的[npc2.moan]以回应[npc.namePos]的渴求，然后温柔地将[npc2.hands]按在[npc.her][npc.breasts+]上。",
	
									"[npc2.name]发出一阵绵软的[npc2.moan]，急切地回应着[npc.namePos]的动作，温柔地用[npc2.fingers]抓向[npc.her][npc.breasts]的软肉。",
	
									"[npc2.name]一边轻柔地[npc2.moaning]着，一边开始玩弄[npc.namePos][npc.breasts+]，"
											+ "[npc2.she]温柔地揉搓着[npc.namePos]的[npc.breasts]，使得[npc.name][npc.lips]间发出愉快的[npc.moans]。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name]发出一阵[npc2.a_moan+]以回应[npc.namePos]的渴求，"
									+ "然后急切地将[npc2.hands]按在[npc.her][npc.breasts+]上。",
	
									"[npc2.name]发出一阵[npc2.a_moan+]，急切地回应着[npc.namePos]的动作，亢奋地用[npc2.fingers]抓向[npc.her][npc.breasts]的软肉。",
	
									"[npc2.name][npc2.Moaning+]着，开始玩弄[npc.namePos][npc.breasts+]，"
											+ "[npc2.she]急切地揉搓着[npc.namePos]的[npc.breasts]，使得[npc.name][npc.lips]间发出愉快的[npc.moans]。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name]发出一阵[npc2.a_moan+]以回应[npc.namePos]的渴求，"
											+ "粗鲁地将[npc2.hands]压向[npc.namePos][npc.breasts+]的软肉，同时咆哮着表明[npc2.sheIs]才是占据主导的一方。",
	
									"[npc2.name]发出一阵[npc2.a_moan+]，急切地回应着[npc.namePos]的动作，粗暴地用[npc2.fingers]抓向[npc.her][npc.breasts]的软肉。",
	
									"[npc2.name][npc2.Moaning+]着，开始玩弄[npc.namePos][npc.breasts+]，"
											+ "[npc2.she]粗暴地揉搓着[npc.namePos]的[npc.breasts]，使得[npc.name][npc.lips]间发出愉快的[npc.moans]。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出一阵[npc2.a_moan+]以回应[npc.namePos]的渴求，然后急切地将[npc2.hands]按在[npc.her][npc.breasts+]上。",
	
									"[npc2.name]发出一阵[npc2.a_moan+]，急切地回应着[npc.namePos]的动作，亢奋地用[npc2.fingers]抓向[npc.her][npc.breasts]的软肉。",
	
									"[npc2.name][npc2.Moaning+]着，开始玩弄[npc.namePos][npc.breasts+]，"
											+ "[npc2.she]急切地揉搓着[npc.namePos]的[npc.breasts]，使得[npc.name][npc.lips]间发出愉快的[npc.moans]。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name]发出一阵[npc2.a_moan+]以回应[npc.namePos]的渴求，"
											+ "然后将[npc2.hands]压进[npc.her][npc.breasts+]肉中。",
	
									"[npc2.name]发出一阵[npc2.a_moan+]，回应着[npc.namePos]的动作，用[npc2.fingers]抓向[npc.her][npc.breasts]的软肉。",
	
									"[npc2.name][npc2.Moaning+]着，开始玩弄[npc.namePos][npc.breasts+]，"
											+ "[npc2.she]揉搓着[npc.namePos]的[npc.breasts]，使得[npc.name][npc.lips]间发出愉快的[npc.moans]。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name]向后缩，"
											+ "[npc2.sobbing]并挣扎抵抗着[npc.namePos]的触碰，因为[npc.she]正强迫[npc2.namePos]将[npc2.hands]按向[npc.her]那[npc.breasts+]。",
	
									"[npc2.Name]发出[npc2.a_sob+]，不安地扭动着，"
											+ "[npc2.she]乞求[npc.name]放过自己，但[npc.name]仍然强行将[npc2.her]的[npc2.hands]压向自己[npc.breasts+]。",
	
									"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，"
											+ "[npc2.she]不断反抗着[npc.name]，但[npc.name]仍然强行将[npc2.her]的[npc2.hands]压向自己[npc.breasts+]。"));
							break;
						default:
							break;
					}
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append("随着[npc2.name]挤榨[npc.namePos][npc.nipples+]的动作，几滴[npc.milk]浸湿了[npc.her]的[npc.lowClothing(NIPPLES)]。");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append("随着[npc2.name]挤榨[npc.namePos][npc.nipples+]的动作，少许[npc.milk]浸湿了[npc.her]的[npc.lowClothing(NIPPLES)]。");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append("随着[npc2.name]挤榨[npc.namePos][npc.nipples+]的动作，一股[npc.milk]浸湿了[npc.her]的[npc.lowClothing(NIPPLES)]。");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append("[npc.namePos]的[npc.milk]溢满了[npc.her]的[npc.lowClothing(NIPPLES)]，"
								+ "[npc.she]感到液体从自己[npc.breasts+]上滑下，不由得[npc.moansVerb]着。");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append("随着[npc2.name]挤榨[npc.namePos][npc.nipples+]的动作，一股股涌出的[npc.milk]浸湿了[npc.her]的[npc.lowClothing(NIPPLES)]。");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append("[npc.namePos]的[npc.milk]开始源源不断地流出，迅速地浸透了[npc.her]的[npc.lowClothing(NIPPLES)]。");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append("[npc.namePos]的[npc.milk]开始源源不断地倾泄而下，迅速地浸透了[npc.her]的[npc.lowClothing(NIPPLES)]。");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]拉着[npc2.namePos]的[npc2.hands]，"
								+ "把它们引导到自己的胸部，然后将它们压向自己[npc.breasts+]，发出了温柔的[npc.moan]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，将其温柔地引导压向[npc.her][npc.breasts]的软肉。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，温柔地引导[npc2.her]的[npc2.fingers]压向自己[npc.breasts+]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]拉着[npc2.namePos]的[npc2.hands]，"
								+ "把它们引导到自己的胸部，然后急切地将它们压向自己[npc.breasts+]，发出一阵[npc.a_moan+]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，将其急切地引导压向[npc.her][npc.breasts]的软肉。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，急切地引导[npc2.her]的[npc2.fingers]压向自己[npc.breasts+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]拉着[npc2.namePos]的[npc2.hands]，"
								+ "把它们拉向自己的胸部，然后粗暴地将它们压向自己[npc.breasts+]，发出一阵[npc.a_moan+]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，粗暴地拉着它们抓向[npc.her][npc.breasts]的软肉。",

								"[npc.name]抓住[npc2.namePos]的[npc2.hands]，"
								+ "粗暴地将其拉向自己的胸部，强迫[npc2.her]的[npc2.fingers]压向自己[npc.breasts+]的软肉。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]拉着[npc2.namePos]的[npc2.hands]，"
								+ "把它们引导到自己的胸部，然后急切地将它们压向自己[npc.breasts+]，发出一阵[npc.a_moan+]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，将其急切地引导压向[npc.her][npc.breasts]的软肉。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，急切地引导[npc2.her]的[npc2.fingers]压向自己[npc.breasts+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]拉着[npc2.namePos]的[npc2.hands]，"
								+ "把它们引导到自己的胸部，然后将它们压向自己[npc.breasts+]，发出一阵[npc.a_moan+]。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，将其引导压向[npc.her][npc.breasts]的软肉。",

								"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，引导[npc2.her]的[npc2.fingers]压向自己[npc.breasts+]。"));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出柔和的[npc2.moan]以回应[npc.namePos]的渴求，然后温柔地将[npc2.hands]按在[npc.her][npc.breasts+]上。",
	
									"[npc2.name]发出一阵绵软的[npc2.moan]，急切地回应着[npc.namePos]的动作，温柔地用[npc2.fingers]抓向[npc.her][npc.breasts]的软肉。",
	
									"[npc2.name]轻柔地[npc2.moaning]，开始玩弄[npc.namePos][npc.breasts+]，"
											+ "[npc2.she]温柔地揉搓着[npc.namePos]的[npc.breasts]，使得[npc.name][npc.lips]间发出愉快的[npc.moans]。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出一阵[npc2.a_moan+]以回应[npc.namePos]的渴求，然后急切地将[npc2.hands]按在[npc.her][npc.breasts+]上。",
	
									"[npc2.name]发出一阵[npc2.a_moan+]，急切地回应着[npc.namePos]的动作，亢奋地用[npc2.fingers]抓向[npc.her][npc.breasts]的软肉。",
	
									"[npc2.name][npc2.Moaning+]着，开始玩弄[npc.namePos][npc.breasts+]，"
											+ "[npc2.she]急切地揉搓着[npc.namePos]的[npc.breasts]，使得[npc.name][npc.lips]间发出愉快的[npc.moans]。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name]发出一阵[npc2.a_moan+]以回应[npc.namePos]的渴求，"
											+ "粗鲁地将[npc2.hands]压向[npc.namePos][npc.breasts+]的软肉，同时咆哮着表明[npc2.sheIs]才是占据主导的一方。",
	
									"[npc2.name]发出一阵[npc2.a_moan+]，急切地回应着[npc.namePos]的动作，粗暴地用[npc2.fingers]抓向[npc.her][npc.breasts]的软肉。",
	
									"[npc2.name][npc2.Moaning+]着，开始玩弄[npc.namePos][npc.breasts+]，"
											+ "[npc2.she]粗暴地揉搓着[npc.namePos]的[npc.breasts]，使得[npc.name][npc.lips]间发出愉快的[npc.moans]。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出一阵[npc2.a_moan+]以回应[npc.namePos]的渴求，然后急切地将[npc2.hands]按在[npc.her][npc.breasts+]上。",
	
									"[npc2.name]发出一阵[npc2.a_moan+]，急切地回应着[npc.namePos]的动作，亢奋地用[npc2.fingers]抓向[npc.her][npc.breasts]的软肉。",
	
									"[npc2.name][npc2.Moaning+]着，开始玩弄[npc.namePos][npc.breasts+]，"
											+ "[npc2.she]急切地揉搓着[npc.namePos]的[npc.breasts]，使得[npc.name][npc.lips]间发出愉快的[npc.moans]。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出一阵[npc2.a_moan+]以回应[npc.namePos]的渴求，然后将[npc2.hands]按在[npc.her][npc.breasts+]上。",
	
									"[npc2.name]发出一阵[npc2.a_moan+]，回应着[npc.namePos]的动作，用[npc2.fingers]抓向[npc.her][npc.breasts]的软肉。",
	
									"[npc2.name][npc2.Moaning+]着，开始玩弄[npc.namePos][npc.breasts+]，"
											+ "[npc2.she]揉搓着[npc.namePos]的[npc.breasts]，使得[npc.name][npc.lips]间发出愉快的[npc.moans]。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name]在[npc2.sobbing]着的同时试图抽身，挣扎反抗着[npc.namePos]的触碰，因为[npc.she]正强迫[npc2.name]将[npc2.hands]按向[npc.her][npc.breasts+]。",
	
									"[npc2.Name]发出[npc2.a_sob+]，不安地扭动着，"
											+ "[npc2.she]乞求[npc.name]放过自己，但[npc.name]仍然强行将[npc2.her]的[npc2.hands]压向自己[npc.breasts+]。",
	
									"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，"
											+ "[npc2.she]不断反抗着[npc.name]，但[npc.name]仍然强行将[npc2.her]的[npc2.hands]压向自己[npc.breasts+]。"));
							break;
						default:
							break;
					}
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append("随着[npc2.name]挤榨[npc.namePos][npc.nipples+]的动作，几滴[npc.milk]从[npc2.her]指间滴出。");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append("随着[npc2.name]挤榨[npc.namePos][npc.nipples+]的动作，少许[npc.milk]从[npc2.her]指间滴出。");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append("随着[npc2.name]挤榨[npc.namePos][npc.nipples+]的动作，一股[npc.milk]从[npc.her]指间溢出。");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append("[npc.NamePos]的[npc.milk]开始从[npc2.namePos]的[npc2.fingers]间流出，"
								+ "[npc.she]感到液体从自己[npc.breasts+]上滑下，不由得[npc.moansVerb]着。");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append("随着[npc2.she]挤榨[npc.nipples+]的动作，[npc.milk]顺着指间一小股一小股地流下。");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append("[npc.namePos]的[npc.milk]开始源源不断地流出，"
								+ "迅速浸湿了[npc.her][npc.breast+]，在[npc.herHim]身下聚成一滩。");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append("[npc.namePos]的[npc.milk]开始源源不断地倾泄而下，"
								+ "迅速浸湿了[npc.her][npc.breast+]，在[npc.herHim]身下聚成一大滩。");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects(){
			if(!Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES)
					&& Main.sex.getCharacterPerformingAction().getBreastStoredMilk().getMinimumValue()>=Lactation.ONE_TRICKLE.getMinimumValue()
					&& Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.NIPPLES)!=null) {
				Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.NIPPLES).setDirty(Main.sex.getCharacterPerformingAction(), true);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastStoredMilk(-10);
		}
		
	};
}
