package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class SelfFingerPenis {
	
	public static final SexAction STROKE_PENIS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "用阴茎挑逗(自己)";
		}
		@Override
		public String getActionDescription() {
			return "轻抚挑弄来刺激你的[npc.cock]寻求快感。";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)) {
				return UtilText.returnStringAtRandom(
						"将手探向[npc.her][npc.legs]之间，[npc.name][npc.fingers+]沿着[npc.her][npc.cock+]上下游走并发出[npc.a_moan+]。",
						"当[npc.she]用[npc.fingers]在[npc.cock+][npc.cockHead+]上挑逗着的时候，[npc.Name][npc.groansVerb+]。",
						"将[npc.her][npc.fingers+]沿着[npc.cock+]上下轻抚逗弄着，[npc.name]发出一连系列难以自抑的[npc.moans+]。",
						"[npc.name]的[npc.fingers]沿着[npc.cock+]上下滑动着，发出了一连串的[npc.groans+]。");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name]用[npc.her]的[npc.hand]抚摸过[npc.her]的腹股沟，然后将"+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()
							+"向着[npc.her][npc.cock+]压下并发出[npc.a_moan+]。",
						"[npc.Name][npc.fingers+]滑向[npc.her]的[npc.legs]之间，发出[npc.a_moan+]并按压起[npc.her]的"
							+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"抵住[npc.her][npc.cock+]。",
						"[npc.Name]将[npc.her]的[npc.fingers]滑过[npc.her]的"+Main.sex.getCharacterPerformingAction().getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()
							+"，并向下按压，试图隔着[npc.her]的衣服刺激[npc.her][npc.cock+]。",
						"[npc.name]用[npc.her]的手掌向下按压[npc.her]的[npc.legs]之间，用"+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()
							+"抵住下面[npc.cock+]揉搓着。");
			}
		}
		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)) {
				Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
			}
		}
	};
	
	public static final SexAction START_COCK_STROKING = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "开始撸管";
		}
		@Override
		public String getActionDescription() {
			return "握住你[npc.cock+]，开始打飞机。";
		}
		@Override
		public String getDescription() {
			return (UtilText.returnStringAtRandom(
					"[npc.name]将手伸到[npc.her]的[npc.legs]之间，抓住[npc.her][npc.cock+]并开始手淫。",
					"[npc.Name]握住并开始上下反复抚摸[npc.her][npc.cock+]。",
					"[npc.Name]伸手握住[npc.her][npc.cock+]，发出[npc.a_moan+]，并开始给自己打手冲。"));
		}
	};
	
	public static final SexAction DOM_GENTLE_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "温柔地抚摸鸡巴";
		}
		@Override
		public String getActionDescription() {
			return "集中精力轻轻抚摸你[npc.cock+]。";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.name]用[npc.fingers]慢慢地上下套弄自己[npc.cock+]，[npc.lips+]间流出[npc.A_moan+]",
					"[npc.name][npc.fingers+]在[npc.her][npc.cock+]周围上下温柔地滑动，[npc.she]开始发出一连串愉悦的[npc.moans]，有节奏地撸动自己。",
					"[npc.name]用[npc.fingers]包裹住自己[npc.cock+]，轻轻地上下抚摸并发出[npc.a_moan+]。",
					"[npc.Name]沉溺于自我满足的愉悦中，[npc.fingers]轻柔地上下抚弄着[npc.her][npc.cock+]。");
		}
	};
	
	public static final SexAction DOM_NORMAL_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "摸摸鸡巴";
		}
		@Override
		public String getActionDescription() {
			return "专注于抚摸你[npc.cock+]。";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.name]用[npc.fingers]急切地上下套弄自己[npc.cock+]，[npc.lips+]间流出[npc.A_moan+]",
					"[npc.name][npc.fingers+]在[npc.her][npc.cock+]周围上下迷乱地滑动，[npc.she]开始发出一连串愉悦的[npc.moans]，有节奏地撸动自己。",
					"[npc.name]用[npc.fingers]包裹住自己[npc.cock+]，疯狂地撸动并发出[npc.a_moan+]。",
					"[npc.Name]沉溺于自我满足的愉悦中，[npc.fingers]饥渴地上下抚弄着[npc.her][npc.cock+]。");
		}
	};
	
	public static final SexAction DOM_ROUGH_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "摸摸鸡巴(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "专心粗暴地撸动你[npc.cock+]。";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.name]用[npc.fingers]粗暴地上下套弄自己[npc.cock+]，[npc.lips+]间流出[npc.A_moan+]",
					"[npc.name][npc.fingers+]在[npc.her][npc.cock+]周围上下粗暴地滑动，[npc.she]开始发出一连串愉悦的[npc.moans]，有节奏地撸动自己。",
					"[npc.name]用[npc.fingers]包裹住自己[npc.cock+]，疯狂地撸动并发出[npc.a_moan+]。",
					"[npc.Name]沉溺于自我满足的愉悦中，[npc.fingers]疯狂打着环、上下抚弄着[npc.her][npc.cock+]。");
		}
	};
	
	public static final SexAction SUB_NORMAL_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "摸摸鸡巴";
		}
		@Override
		public String getActionDescription() {
			return "集中精力轻轻抚摸你[npc.cock+]。";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.name]用[npc.fingers]上下套弄自己[npc.cock+]，[npc.lips+]间流出[npc.A_moan+]",
					"[npc.name][npc.fingers+]在[npc.cock+]周围上下滑动，[npc.she]开始发出一连串愉悦的[npc.moans]，有节奏地撸动自己。",
					"[npc.name]用[npc.fingers]包裹住自己[npc.cock+]，撸动起来并发出[npc.a_moan+]。",
					"[npc.Name]沉溺于自我满足的愉悦中，[npc.fingers]打着环上下抚弄着[npc.her][npc.cock+]。");
		}
		
	};
	
	public static final SexAction SUB_EAGER_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "摸摸鸡巴(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "渴求地关照你[npc.cock+]。";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.name]用[npc.fingers]急切地上下套弄自己[npc.cock+]，[npc.lips+]间流出[npc.A_moan+]",
					"[npc.name][npc.fingers+]在[npc.her][npc.cock+]周围上下迷乱地滑动，[npc.she]开始发出一连串愉悦的[npc.moans]，有节奏地撸动自己。",
					"[npc.name]用[npc.fingers]包裹住自己[npc.cock+]，疯狂地撸动并发出[npc.a_moan+]。",
					"[npc.Name]沉溺于自我满足的愉悦中，[npc.fingers]饥渴地上下抚弄着[npc.her][npc.cock+]。");
		}
	};
	
	public static final SexAction STOP_COCK_STROKING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "停止摸鸡巴";
		}
		@Override
		public String getActionDescription() {
			return "停止抚摸你[npc.cock+]。";
		}
		@Override
		public String getDescription() {
			return "[npc.Name]发出[npc.a_moan+]，将[npc.hand]从[npc.her][npc.cock+]上移开，停止了手冲。";
		}
	};
}
