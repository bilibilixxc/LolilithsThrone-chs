package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.*;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.4.5
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfFingerBreasts {
	
	public static final SexAction FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.BREAST)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "揉搓乳房(自己)";
		}

		@Override
		public String getActionDescription() {
			return "挤弄揉搓着你自己[npc.breasts+]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreasts()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES)){
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]把手伸向[npc2.namePos]的胸部，开始爱抚和摸索[npc2.namePos]那[npc2.breastRows][npc2.breasts+]，发出轻柔的[npc.moan]，"
										+ "轻轻揉压着[npc.her]的[npc.lowClothing(NIPPLES)]，同时挤压着下面[npc.nipples+]。",
								"[npc.Name]欲火焚身，忍不住想要自己[npc.breasts+]被玩弄，于是伸出[npc.hands+]轻轻按压着"
									+ "[npc.her]的[npc.topClothing(NIPPLES)]，然后开始轻柔地摸索并挤压[npc.her]的胸部。",
								"[npc.name]用[npc.fingers]挑逗自己的[npc.topClothing(NIPPLES)]，"
										+ "[npc.she]轻柔地爱抚并摸索自己[npc.breastRows][npc.breasts+]，轻缓地[npc.moaning]、叹息起来。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]将手伸向胸前，开始粗暴地抚弄和摸索自己[npc.breastRows][npc.breasts+]，逐渐发出了[npc.a_moan+]，"
										+ "[npc.her][npc.lowClothing(NIPPLES)]激烈地与[npc.nipples+]摩擦。",
								"[npc.Name]欲火焚身，渴望自己[npc.breasts+]被玩弄，便用[npc.hands+]激烈摩擦着"
									+ "[npc.her]的[npc.topClothing(NIPPLES)]，然后开始粗暴地摸索和挤压[npc.her]的胸部。",
								"用[npc.fingers]抚摸着自己的[npc.topClothing(NIPPLES)]，"
										+"[npc.name]粗暴地抚弄和摸索自己[npc.breastRows][npc.breasts+]，逐渐大声地喘息、[npc.moaning]起来。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]把手伸到胸前，开始爱抚和摸索[npc.her]那[npc.breastRows][npc.breasts+]，渐渐发出了[npc.a_moan+]，"
										+ "同时[npc.her]的[npc.lowClothing(NIPPLES)]紧贴着[npc.her][npc.nipples+]。",
								"[npc.Name]欲火焚身，忍不住想要自己[npc.breasts+]被玩弄，于是伸出[npc.hands+]按压着"
									+ "[npc.her]的[npc.topClothing(NIPPLES)]，然后便轻柔地对着胸部揉捏起来。",
								"用[npc.fingers]抚摸着自己的[npc.topClothing(NIPPLES)]，"
										+ "[npc.name]对着自己[npc.breastRows][npc.breasts+]揉来搓去，渐渐发出急促的呼吸和[npc.moaning]。"));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]把[npc.hand]伸到胸前，开始爱抚和摸索[npc.her]那[npc.breastRows][npc.breasts+]，渐渐发出了[npc.a_moan+]，"
										+ "急切地隔着[npc.lowClothing(NIPPLES)]，向着[npc.nipples+]揉压起来。",
								"[npc.Name]欲火焚身，忍不住想要自己[npc.breasts+]被玩弄，于是伸出[npc.hands+]饥渴地按压着"
									+ "[npc.her]的[npc.topClothing(NIPPLES)]，然后便疯狂地对着胸部揉捏起来。",
								"用[npc.fingers]抚摸着自己的[npc.topClothing(NIPPLES)]，"
										+ "[npc.name]饥渴地对着自己[npc.breastRows][npc.breasts+]揉来搓去，很快便不禁发出喘息和[npc.moaning]。"));
						break;
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append("随着[npc.she]挤榨[npc.nipples+]的动作，几滴[npc.milk]浸湿了[npc.her]的[npc.lowClothing(NIPPLES)]。");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append("随着[npc.she]挤榨[npc.nipples+]的动作，少许[npc.milk]浸湿了[npc.her]的[npc.lowClothing(NIPPLES)]。");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append("[npc.she]揉捏着[npc.nipples+]，一缕[npc.milk]喷到了[npc.lowClothing(NIPPLES)]上。");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append("眨眼间，[npc.milk]从[npc.her][npc.nipples+]里涌了出来，沁入[npc.lowClothing(NIPPLES)]。");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append("随着[npc.she]挤榨[npc.nipples+]的动作，不断流出的[npc.milk]迅速浸湿了[npc.her]的[npc.lowClothing(NIPPLES)]。");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append("[npc.Her]的[npc.milk]立刻开始源源不断地涌出，迅速地浸透了[npc.her]的[npc.lowClothing(NIPPLES)]。");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append("[npc.her]的[npc.milk]立刻开始源源不断地倾泄而下，迅速地浸透了[npc.her]的[npc.lowClothing(NIPPLES)]。");
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
								"[npc.Name]轻轻地抚摸玩弄[npc.she]那[npc.breastRows][npc.breasts+]，发出一声轻柔的[npc.moan]，"
										+ "[npc2.she]也轻轻地将[npc.fingers+]按在了自己[npc.nipples+]上。",
								"[npc.Name]欲火焚身，忍不住想要自己[npc.breasts+]被玩弄，于是伸出[npc.hands+]轻轻按压着"
									+ "[npc.her]毫不在意暴露着的胸部，而是开始按揉摸弄[npc.her]那[npc.breastRows][npc.breasts]。",
								"[npc.name]用[npc.fingers]拨弄起自己[npc.breastRows][npc.breasts+]，"
										+ "[npc.she]温柔地抚摸揉搓着它们，不自主地[npc.moaning]起来。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]将手伸向裸露在外的胸部，然后开始粗暴地爱抚着[npc.her]那[npc.breastRows][npc.breasts+]，发出了[npc.a_moan+]，"
										+ "在此期间，[npc.her]将自己那[npc.fingers+]按压碾转在了[npc.nipples+]上。",
								"[npc.Name]欲火焚身，渴望自己[npc.breasts+]被玩弄，便用[npc.hands+]激烈摩擦着"
									+ "[npc.her]用手抵住裸露的胸部，便开始粗暴地摸索和挤压那[npc.breastRows][npc.breasts]。",
								"手指轻轻抚过那暴露在外的[npc.breastRows][npc.breasts+]，"
										+ "[npc.name]大力揉搓起它们，抑制不住地[npc.moaning]喘息起来。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]将手伸向裸露在外的胸部，然后开始爱抚着[npc.her]那[npc.breastRows][npc.breasts+]，发出了[npc.a_moan+]，"
										+ "[npc2.she]也将[npc.fingers+]按在了自己[npc.nipples+]上。",
								"[npc.Name]欲火焚身，忍不住想要自己[npc.breasts+]被玩弄，于是伸出[npc.hands+]按压着"
									+ "[npc.her]裸露的胸部，然后开始揉捏[npc.her]那[npc.breastRows][npc.breasts]。",
								"手指轻轻抚过那暴露在外的[npc.breastRows][npc.breasts+]，"
										+ "[npc.name]抚弄着它们，抑制不住地[npc.moaning]并喘息着。"));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]将手伸向裸露在外的胸部，然后开始急切地爱抚着[npc.her]那[npc.breastRows][npc.breasts+]，发出了[npc.a_moan+]，"
										+ "[npc2.she]也饥渴地将[npc.fingers+]按在了自己[npc.nipples+]上。",
								"[npc.Name]欲火焚身，忍不住想要自己[npc.breasts+]被玩弄，于是伸出[npc.hands+]饥渴地按压着"
									+ "[npc.her]裸露的胸部，然后开始疯狂地揉捏[npc.her]那[npc.breastRows][npc.breasts]。",
								"手指轻轻抚过[npc.her]那暴露在外的[npc.breastRows][npc.breasts+],"
										+ "[npc.name]急切地抚弄着它们，抑制不住地[npc.moaning]并急切地喘息着。"));
						break;
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append("随着[npc.she]挤榨[npc.nipples+]的动作，几滴[npc.milk]从[npc.fingers]间滴出。");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append("随着[npc.she]挤榨[npc.nipples+]的动作，少许[npc.milk]从[npc.fingers]间滴出。");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append("随着[npc.namePos]挤榨[npc.nipples+]的动作，几滴[npc.milk]从[npc.fingers]间滴出。");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append("眨眼间，[npc.milk]就从[npc.her][npc.fingers]间滴下来，"
								+ "[npc.she]感到液体从自己[npc.breasts+]上滑下，不由得[npc.moansVerb]着。");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append("随着[npc.she]挤榨[npc.nipples+]的动作，[npc.milk]很快流了出来，如小溪般淌过[npc.her][npc.nipples+]。");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append("[npc.Her]的[npc.milk]立刻开始源源不断地涌出，很快浸湿了[npc.her][npc.breasts+]，滴落在身下的地板上。");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append("[npc.Her]的[npc.milk]开始大量涌出，很快浸湿了[npc.her][npc.breasts+]，在地板上形成了一汪水洼。");
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
