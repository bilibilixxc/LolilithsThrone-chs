package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.4.11.2
 * @author Innoxia
 */
public class DoggyStyleAndProneBone {
	
	private static boolean suitablePositionsForPullingAndGrabbing(GameCharacter performer, GameCharacter target) {
		boolean suitableSlot = false;
		
		// Doggy-style check:
		if(Main.sex.getSexPositionSlot(target)==SexSlotAllFours.ALL_FOURS) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.BEHIND
					|| Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.HUMPING;
		}
		if(Main.sex.getSexPositionSlot(target)==SexSlotAllFours.ALL_FOURS_TWO) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.BEHIND_TWO
					|| Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.HUMPING_TWO;
		}
		if(Main.sex.getSexPositionSlot(target)==SexSlotAllFours.ALL_FOURS_THREE) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.BEHIND_THREE
					|| Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.HUMPING_THREE;
		}
		if(Main.sex.getSexPositionSlot(target)==SexSlotAllFours.ALL_FOURS_FOUR) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.BEHIND_FOUR
					|| Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.HUMPING_FOUR;
		}
		
		// Prone bone check:
		if(Main.sex.getSexPositionSlot(target)==SexSlotLyingDown.LYING_DOWN_FRONT) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotLyingDown.MISSIONARY;
		}
		if(Main.sex.getSexPositionSlot(target)==SexSlotLyingDown.LYING_DOWN_FRONT_TWO) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotLyingDown.MISSIONARY_TWO;
		}
		if(Main.sex.getSexPositionSlot(target)==SexSlotLyingDown.LYING_DOWN_FRONT_THREE) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotLyingDown.MISSIONARY_THREE;
		}
		if(Main.sex.getSexPositionSlot(target)==SexSlotLyingDown.LYING_DOWN_FRONT_FOUR) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotLyingDown.MISSIONARY_FOUR;
		}
		
		return suitableSlot;
	}
	
	public static final SexAction PULL_HAIR = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "扯头发";
		}

		@Override
		public String getActionDescription() {
			return "抓住[npc2.namePos]的头发，把头向后拉。";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;
			
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			
			suitableSlot = suitablePositionsForPullingAndGrabbing(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
			
			if(!suitableSlot) {
				return false;
			}
			
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return SexAreaPenetration.FINGER.isFree(Main.sex.getCharacterPerformingAction())
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairLength().isSuitableForPulling()
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairType().isAbleToBeGrabbedInSex()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸手温柔地握住[npc2.namePos]的[npc2.hair(true)]，继续在[npc2.her][npc2."+tag+"+]间有节奏地抽送着[npc.cock]，"
										+ "[npc.she]缓缓抽出，引得[npc2.name]昂起头，发出[npc2.a_moan+]。",
								"[npc.name]一只[npc.hand]温柔地握住[npc2.namePos]的[npc2.hair(true)]，边向后拉[npc2.her]的头，边把[npc.cock+]尽根深埋进[npc2."+tag+"]，"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?"轻声地咯咯笑着":"得意地坏笑着")+"，[npc2.name]发出[npc2.moan+]。",
								"[npc.namePos][npc.cock+]继续缓缓探进[npc2.namePos][npc2."+tag+"+]，插进拔出。[npc.she]温柔地抓住[npc2.her]的[npc2.hair(true)]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
						
					} else {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸手温柔地握住[npc2.namePos]的[npc2.hair(true)]。[npc2.namePos]缓慢后仰，发出[npc2.a_moan+]。",
								"[npc.name]一只[npc.hand]温柔地握住[npc2.namePos]的[npc2.hair(true)]，[npc.name]"+(Main.sex.getCharacterPerformingAction().isFeminine()?"轻笑着":"坏笑着")
									+"，[npc.she]向后拉[npc2.her]的头，引得[npc2.herHim]发出[npc2.moan+]。",
								"[npc.namePos][npc.fingers+]沿着[npc2.namePos]的背轻轻滑动，又温柔地拉住[npc2.her]的[npc2.hair(true)]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"[npc.name]粗暴地一把薅住[npc2.namePos]的头发，继续强力地把[npc.her]的[npc.cock]操进[npc2.her][npc2."+tag+"+]，塞进拔出，"
										+ "[npc.she]突然向后猛拉[npc2.her]的头，[npc2.herHim]发出[npc2.a_moan+]。",
								"[npc.name]紧紧抓住一把[npc2.namePos]的[npc2.hair(true)]，一边向后猛拉[npc2.her]的头，一边用[npc.cock+]又深又重地顶撞[npc2.her][npc2."+tag+"]，"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?"愉悦地狂笑不止":"愉悦地低声哼叫")+"，[npc2.name]发出[npc2.moan+]。",
								"[npc.name]继续狂野地让[npc.cock+]没入[npc2.namePos][npc2."+tag+"+]，一把薅住[npc2.her]的[npc2.hair(true)]，"
										+ "肆意猛力地向后拽[npc2.her]的头，让[npc2.herHim]发出[npc2.a_moan+]。");
						
					} else {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸手狂乱地拽住一把[npc2.namePos]的[npc2.hair(true)]。[npc2.her]猛向后仰，发出[npc2.a_moan+]。",
								"[npc.name]抓着一把[npc2.namePos]的[npc2.hair(true)]，"+(Main.sex.getCharacterPerformingAction().isFeminine()?"疯子般地咯咯笑":"深沉地低吼")
									+ "[npc.she]粗鲁地向后猛拉[npc2.her]的头，使得[npc2.herHim]发出[npc2.moan+]。",
								"[npc.name]轻抚并拍了拍[npc2.namePos]的背，伸手拽住[npc2.her]的[npc2.hair(true)]，"
										+ "然后粗暴地将[npc2.her]的头向后拽，使得[npc2.herHim]发出[npc2.a_moan+]。");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸手牢牢地握住[npc2.namePos]的[npc2.hair(true)]，继续在[npc2.her][npc2."+tag+"+]里有节奏地抽送着[npc.cock]，"
										+ "[npc.she]缓缓抽出，引得[npc2.name]昂起头，发出[npc2.a_moan+]。",
								"[npc.name]一只[npc.hand]牢牢攥住[npc2.namePos]的[npc2.hair(true)]，边向后拉[npc2.her]的头，边把[npc.cock+]尽根深埋进[npc2."+tag+"]，"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?"快乐地狂笑着":"低声哼叫着")+"，[npc2.name]发出[npc2.moan+]。",
								"[npc.name]继续将[npc.cock+]大力操进[npc2.namePos][npc2."+tag+"+]，插进拔出。[npc.her]伸手牢牢攥住[npc2.her]的[npc2.hair(true)]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
						
					} else {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸手牢牢攥住[npc2.namePos]的[npc2.hair(true)]。[npc2.namePos]逐渐后仰，发出[npc2.a_moan+]。",
								"[npc.name]一只[npc.hand]牢牢攥住[npc2.namePos]的[npc2.hair(true)]，[npc.name]"+(Main.sex.getCharacterPerformingAction().isFeminine()?"咯咯笑着":"轻笑着")
									+"，[npc.she]愉悦地向后拉[npc2.her]的头，引得[npc2.herHim]发出[npc2.moan+]。",
								"[npc.namePos][npc.fingers+]沿着[npc2.namePos]的背轻轻滑动，[npc.she]伸手牢牢拽住[npc2.her]的[npc2.hair(true)]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
					}
			}
		}
		
	};
	
	public static final SexAction PULL_EARS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "扯耳朵";
		}

		@Override
		public String getActionDescription() {
			return "抓住[npc2.namePos][npc2.ears+]，把头向后拉。";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;

			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			suitableSlot = suitablePositionsForPullingAndGrabbing(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
			
			if(!suitableSlot) {
				return false;
			}
			
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).getEarType().isAbleToBeUsedAsHandlesInSex()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]温柔地揪住[npc2.namePos][npc2.ears+]，继续在[npc2.her][npc2."+tag+"+]间有节奏地抽送着[npc.her]的[npc.cock]，"
										+ "[npc.she]缓缓抽出，引得[npc2.name]昂起头，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hands]温柔地握住[npc2.namePos][npc2.ears+]，边向后拉[npc2.her]的头，边把[npc.cock+]尽根深埋进[npc2."+tag+"]，"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?"轻声地咯咯笑着":"得意地坏笑着")+"，[npc2.name]发出[npc2.moan+]。",
								"[npc.namePos][npc.cock+]继续缓缓探进[npc2.namePos][npc2."+tag+"+]，插进拔出。[npc.she]双[npc.hands]温柔地抓住[npc2.her][npc2.ears+]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
						
					} else {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]温柔地拽住[npc2.namePos][npc2.ears+]。[npc2.namePos]缓慢后仰，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hands]温柔地拽住[npc2.namePos][npc2.ears+]，[npc.name]"+(Main.sex.getCharacterPerformingAction().isFeminine()?"轻笑着":"坏笑着")
									+"，[npc.she]向后拉[npc2.her]的头，引得[npc2.herHim]发出[npc2.moan+]。",
								"[npc.namePos][npc.fingers+]沿着[npc2.namePos]的背轻抚，双[npc.hands]温柔地拉住[npc2.her][npc2.ears+]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"[npc.name]粗暴地一把薅住[npc2.namePos][npc2.ears+]，继续强力地把[npc.her]的[npc.cock]操进[npc2.her][npc2."+tag+"+]，塞进拔出，"
										+ "[npc.she]突然向后猛拉[npc2.her]的头，[npc2.herHim]发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hands]抓住[npc2.namePos][npc2.ears+]，一边向后猛拉[npc2.her]的头，一边用[npc.cock+]又深又重地顶撞[npc2.her][npc2."+tag+"]，"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?"愉悦地狂笑不止":"愉悦地低声哼叫")+"，[npc2.name]发出[npc2.moan+]。",
								"[npc.name]继续粗暴地让[npc.cock+]没入[npc2.namePos][npc2."+tag+"+]，双[npc.hands]揪住[npc2.her][npc2.ears+]，"
										+ "肆意猛力地向后拽[npc2.her]的头，让[npc2.herHim]发出[npc2.a_moan+]。");
						
					} else {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]粗暴地拽住[npc2.namePos][npc2.ears+]。[npc2.her]猛向后仰，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hands]揪住[npc2.namePos][npc2.ears+]，"+(Main.sex.getCharacterPerformingAction().isFeminine()?"疯子般地咯咯笑":"深沉地低吼")
									+ "[npc.she]粗鲁地向后猛拉[npc2.her]的头，使得[npc2.herHim]发出[npc2.moan+]。",
								"[npc.name]轻抚并拍了拍[npc2.namePos]的背，双[npc.hands]揪住[npc2.her][npc2.ears+]，"
										+ "然后粗暴地将[npc2.her]的头向后拽，使得[npc2.herHim]发出[npc2.a_moan+]。");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]牢牢地揪住[npc2.namePos][npc2.ears+]，继续在[npc2.her][npc2."+tag+"+]间有节奏地抽送着[npc.her]的[npc.cock]，"
										+ "[npc.she]缓缓抽出，引得[npc2.name]昂起头，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hand]牢牢地握住[npc2.namePos][npc2.ears+]，边向后拉[npc2.her]的头，边把[npc.cock+]尽根深埋进[npc2."+tag+"]，"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?"快乐地狂笑着":"低声哼叫着")+"，[npc2.name]发出[npc2.moan+]。",
								"[npc.name]继续将[npc.cock+]大力操进[npc2.namePos][npc2."+tag+"+]，插进拔出。[npc.her]双[npc.hands]牢牢攥住[npc2.her][npc2.ears+]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
						
					} else {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]牢牢攥住[npc2.namePos][npc2.ears+]。[npc2.namePos]逐渐后仰，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hand]牢牢攥住[npc2.namePos][npc2.ears+]，[npc.name]"+(Main.sex.getCharacterPerformingAction().isFeminine()?"咯咯笑着":"轻笑着")
									+"，[npc.she]愉悦地向后拉[npc2.her]的头，引得[npc2.herHim]发出[npc2.moan+]。",
								"[npc.namePos][npc.fingers+]沿着[npc2.namePos]的背轻抚，双[npc.hands]牢牢地拉住[npc2.her][npc2.ears+]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
					}
			}
		}
	};
	
	public static final SexAction GRAB_HORNS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "抓住[npc2.horns]";
		}

		@Override
		public String getActionDescription() {
			return "抓住[npc2.namePos][npc2.horns+]，把头向后拉。";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;

			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			suitableSlot = suitablePositionsForPullingAndGrabbing(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));

			if(!suitableSlot) {
				return false;
			}
			
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).isHornsAbleToBeUsedAsHandlesInSex()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]温柔地揪住[npc2.namePos][npc2.horns+]，继续在[npc2.her][npc2."+tag+"+]间有节奏地抽送着[npc.her]的[npc.cock]，"
										+ "[npc.she]缓缓抽出，引得[npc2.name]昂起头，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hand]温柔地拽住[npc2.namePos][npc2.horns+]，边向后拉[npc2.her]的头，边把[npc.cock+]尽根深埋进[npc2."+tag+"]，"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?"轻声地咯咯笑着":"得意地坏笑着")+"，[npc2.name]发出[npc2.moan+]。",
								"[npc.namePos][npc.cock+]继续缓缓探进[npc2.namePos][npc2."+tag+"+]，插进拔出。[npc.she]双[npc.hands]温柔地抓住[npc2.her][npc2.horns+]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
						
					} else {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]温柔地拽住[npc2.namePos][npc2.horns+]。[npc2.namePos]缓慢后仰，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hands]温柔地拽住[npc2.namePos][npc2.horns+]，[npc.name]"+(Main.sex.getCharacterPerformingAction().isFeminine()?"轻笑着":"坏笑着")
									+"，[npc.she]向后拉[npc2.her]的头，引得[npc2.herHim]发出[npc2.moan+]。",
								"[npc.namePos][npc.fingers+]沿着[npc2.namePos]的背轻抚，双[npc.hands]温柔地把住[npc2.her][npc2.horns+]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"[npc.name]粗暴地把住[npc2.namePos][npc2.horns+]，继续强力地把[npc.her]的[npc.cock]操进[npc2.her][npc2."+tag+"+]，塞进拔出，"
										+ "[npc.she]突然向后猛拉[npc2.her]的头，[npc2.herHim]发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hands]把住[npc2.namePos][npc2.horns+]，一边向后猛拉[npc2.her]的头，一边用[npc.cock+]又深又重地顶撞[npc2.her][npc2."+tag+"]，"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?"愉悦地狂笑不止":"愉悦地低声哼叫")+"，[npc2.name]发出[npc2.moan+]。",
								"[npc.name]继续粗暴地让[npc.cock+]没入[npc2.namePos][npc2."+tag+"+]，双[npc.hands]攥住[npc2.her][npc2.horns+]，"
										+ "肆意猛力地向后拽[npc2.her]的头，让[npc2.herHim]发出[npc2.a_moan+]。");
						
					} else {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]粗暴地把住[npc2.namePos][npc2.horns+]。[npc2.her]猛向后仰，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hands]把住[npc2.namePos][npc2.horns+]，"+(Main.sex.getCharacterPerformingAction().isFeminine()?"疯子般地咯咯笑":"深沉地低吼")
									+ "[npc.she]粗鲁地向后猛拉[npc2.her]的头，使得[npc2.herHim]发出[npc2.moan+]。",
								"[npc.name]轻抚并拍了拍[npc2.namePos]的背，双[npc.hands]把住[npc2.her][npc2.horns+]，"
										+ "然后粗暴地将[npc2.her]的头向后拽，使得[npc2.herHim]发出[npc2.a_moan+]。");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]牢牢地把住[npc2.namePos][npc2.horns+]，继续在[npc2.her][npc2."+tag+"+]间有节奏地抽送着[npc.her]的[npc.cock]，"
										+ "[npc.she]缓缓抽出，引得[npc2.name]昂起头，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hand]牢牢把住[npc2.namePos][npc2.horns+]，边向后拉[npc2.her]的头，边把[npc.cock+]尽根深埋进[npc2."+tag+"]，"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?"快乐地狂笑着":"低声哼叫着")+"，[npc2.name]发出[npc2.moan+]。",
								"[npc.name]继续将[npc.cock+]大力操进[npc2.namePos][npc2."+tag+"+]，插进拔出。[npc.her]双[npc.hands]牢牢把住[npc2.her][npc2.horns+]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
						
					} else {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]牢牢攥住[npc2.namePos][npc2.horns+]。[npc2.namePos]逐渐后仰，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hands]牢牢攥住[npc2.namePos][npc2.horns+]，[npc.name]"+(Main.sex.getCharacterPerformingAction().isFeminine()?"咯咯笑着":"轻笑着")
									+"，[npc.she]愉悦地向后拉[npc2.her]的头，引得[npc2.herHim]发出[npc2.moan+]。",
								"[npc.namePos][npc.fingers+]沿着[npc2.namePos]的背轻抚，双[npc.hands]牢牢把住[npc2.her][npc2.horns+]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
					}
			}
		}
		
	};
	

	public static final SexAction GRAB_ANTENNAE = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "抓住[npc2.antennae]";
		}

		@Override
		public String getActionDescription() {
			return "抓住[npc2.namePos][npc2.antennae+]，把头向后拉。";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;

			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			suitableSlot = suitablePositionsForPullingAndGrabbing(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));

			if(!suitableSlot) {
				return false;
			}
			
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).isAntennaeAbleToBeUsedAsHandlesInSex()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]温柔地揪住[npc2.namePos][npc2.antennae+]，继续在[npc2.her][npc2."+tag+"+]间有节奏地抽送着[npc.her]的[npc.cock]，"
										+ "[npc.she]缓缓抽出，引得[npc2.name]昂起头，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hands]温柔地拽住[npc2.namePos][npc2.antennae+]，边向后拉[npc2.her]的头，边把[npc.cock+]尽根深埋进[npc2."+tag+"]，"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?"轻声地咯咯笑着":"得意地坏笑着")+"，[npc2.name]发出[npc2.moan+]。",
								"[npc.namePos][npc.cock+]继续缓缓探进[npc2.namePos][npc2."+tag+"+]，插进拔出。[npc.she]双[npc.hands]温柔地抓住[npc2.her][npc2.antennae+]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
						
					} else {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]温柔地拽住[npc2.namePos][npc2.antennae+]。[npc2.namePos]缓慢后仰，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hands]温柔地拽住[npc2.namePos][npc2.antennae+]，[npc.name]"+(Main.sex.getCharacterPerformingAction().isFeminine()?"轻笑着":"笑了笑")
									+"，[npc.she]向后拉[npc2.her]的头，引得[npc2.herHim]发出[npc2.moan+]。",
								"[npc.namePos][npc.fingers+]沿着[npc2.namePos]的背轻抚，双[npc.hands]温柔地拽住[npc2.her][npc2.antennae+]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"[npc.name]粗暴地一把薅住[npc2.namePos][npc2.antennae+]，继续强力地把[npc.her]的[npc.cock]操进[npc2.her][npc2."+tag+"+]，塞进拔出，"
										+ "[npc.she]突然向后猛拉[npc2.her]的头，[npc2.herHim]发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hands]拽住[npc2.namePos][npc2.antennae+]，一边向后猛拉[npc2.her]的头，一边用[npc.cock+]又深又重地顶撞[npc2.her][npc2."+tag+"]，"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?"愉悦地狂笑不止":"愉悦地低声哼叫")+"，[npc2.name]发出[npc2.moan+]。",
								"[npc.name]继续粗暴地让[npc.cock+]没入[npc2.namePos][npc2."+tag+"+]，双[npc.hands]薅住[npc2.her][npc2.antennae+]，"
										+ "肆意猛力地向后拽[npc2.her]的头，让[npc2.herHim]发出[npc2.a_moan+]。");
						
					} else {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]粗暴地拽住[npc2.namePos][npc2.antennae+]。[npc2.her]猛向后仰，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hands]揪着[npc2.namePos][npc2.antennae+]，"+(Main.sex.getCharacterPerformingAction().isFeminine()?"疯子般地咯咯笑":"深沉地低吼")
									+ "[npc.she]粗鲁地向后猛拉[npc2.her]的头，使得[npc2.herHim]发出[npc2.moan+]。",
								"[npc.name]轻抚并拍了拍[npc2.namePos]的背，双[npc.hands]拽住[npc2.her][npc2.antennae+]，"
										+ "然后粗暴地将[npc2.her]的头向后拽，使得[npc2.herHim]发出[npc2.a_moan+]。");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]牢牢揪住[npc2.namePos][npc2.antennae+]，继续在[npc2.her][npc2."+tag+"+]间有节奏地抽送着[npc.her]的[npc.cock]，"
										+ "[npc.she]缓缓抽出，引得[npc2.name]昂起头，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hands]牢牢拽住[npc2.namePos][npc2.antennae+]，边向后拉[npc2.her]的头，边把[npc.cock+]尽根深埋进[npc2."+tag+"]，"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?"快乐地狂笑着":"低声哼叫着")+"，[npc2.name]发出[npc2.moan+]。",
								"[npc.name]继续将[npc.cock+]大力操进[npc2.namePos][npc2."+tag+"+]，插进拔出。[npc.her]双[npc.hands]牢牢攥住[npc2.her][npc2.antennae+]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
						
					} else {
						return UtilText.returnStringAtRandom(
								"[npc.name]伸[npc.hand]牢牢攥住[npc2.namePos][npc2.antennae+]。[npc2.namePos]逐渐后仰，发出[npc2.a_moan+]。",
								"[npc.name]双[npc.hand]牢牢攥住[npc2.namePos][npc2.antennae+]，[npc.name]"+(Main.sex.getCharacterPerformingAction().isFeminine()?"愉悦地笑着":"轻笑着")
									+"，[npc.she]愉悦地向后拉[npc2.her]的头，引得[npc2.herHim]发出[npc2.moan+]。",
								"[npc.namePos][npc.fingers+]抚上[npc2.namePos]的背，双[npc.hands]牢牢地拉住[npc2.her][npc2.antennae+]，"
										+ "然后将[npc2.her]的头向回拉，使[npc2.herHim]发出[npc2.a_moan+]。");
					}
			}
		}
		
	};

	public static final SexAction LOOK_BACK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			SexSlot slot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction());
			SexSlot targetedSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this));
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& ((slot.hasTag(SexSlotTag.ALL_FOURS) && targetedSlot.hasTag(SexSlotTag.BEHIND_ALL_FOURS))
							|| (slot.hasTag(SexSlotTag.LYING_DOWN_ON_FRONT) && targetedSlot.hasTag(SexSlotTag.MISSIONARY)))
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "抛媚眼";
		}
		@Override
		public String getActionDescription() {
			return "你转过头，摄人心魄地看向[npc2.name]。";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().getSubspecies()==Subspecies.getSubspeciesFromId("innoxia_raptor_subspecies_owl")
					&& Main.sex.getCharacterPerformingAction().getFaceType().getRace()==Race.getRaceFromId("innoxia_raptor")) {
				return UtilText.returnStringAtRandom(
						"[npc.Name]利用[npc.her]鸮一般的脖子的柔韧性，以一种令人毛骨悚然又惊恐万分的方式，把头完整地向后转了180度，"
								+ "[npc.she]抬头看着[npc2.name]，摆出最诱人的样子，引诱[npc2.herHim]使用自己。",
						"[npc.name]扭了一整圈[npc.her]鸮般的脖子，最终抬头直直地看向[npc2.name]。后者正压住[npc.her][npc.ass+]。"
								+ "[npc.Name]希望这个令人毛骨悚然又惊恐万分的举动不会让对方偃旗息鼓，又投射出摄人心魄的眼神，"
								+ "[npc.she]愉悦地[npc.moaning]，引诱[npc2.name]使用[npc.her]的身体。",
						"[npc.Name]利用鸮一般的脖子赋予[npc.herHim]的大范围移动能力，将头完全转过来，不动身体就可以与[npc2.name]面对面，"
								+ "[npc.her]尽量使自己看起来迷人。");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.name]转过头看向[npc2.name]，咬了咬[npc.lip]，投射出最摄人心魄的眼神，引诱[npc2.herHim]来使用[npc.herHim]。",
						"[npc2.name]俯视着[npc.her][npc.ass+]，[npc.name]回过头，露出诱人的表情，"
								+ "[npc.she]愉悦地[npc.moaning]，引诱[npc2.name]使用[npc.her]的身体。",
						"[npc.name]转过头，对着[npc2.name]咬了咬[npc.lip]，尽力投射出摄人心魄的眼神。",
						"[npc.name]转过头，对[npc2.name]投射出摄人心魄的眼神。[npc.she]看到[npc2.herHim]饥渴地回望着自己，心中极为满意。");
			}
		}
	};
	
	// Orgasms:
	
	public static final SexAction DOGGY_DOMINANT_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isBaseRequirementsMet() {
			SexSlot slot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction());
			SexSlot targetedSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this));
			
			return Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.MOUTH)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getDominantParticipants(false).size()==1
					&& Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					&& (slot.hasTag(SexSlotTag.BEHIND_ALL_FOURS)
							|| (slot.hasTag(SexSlotTag.MISSIONARY) && targetedSlot.hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<=0)
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
//					&& (Main.sex.getCharacterPerformingAction().isPlayer() || !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SADIST).isNegative());
		}
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Math.random()<0.75f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "先肛后嘴";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
				return "你粗暴地操到[npc2.name]伏在地上，在避孕套里射满精液，摘下它，让[npc2.her]给你舔干净。";
			}
			return "你粗暴地操到[npc2.name]伏在地上，射在[npc2.ass]里，抽出肉棒，让[npc2.her]给你舔干净。";
		}
		@Override
		public String getDescription() {
			boolean immobile = Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.COMMAND);
			boolean immobileSleep = Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.SLEEP);

			StringBuilder sb = new StringBuilder();
			
			sb.append("[npc.name]感觉[npc2.namePos][npc2.asshole+]箍弄着[npc.her][npc.cock+]，便决定让[npc2.herHim]看看"
						+ (Main.sex.getCharacterPerformingAction().getRace()==Race.WOLF_MORPH || Main.sex.getCharacterPerformingAction().getRace()==Race.DOG_MORPH
							?"阿尔法对待他们驯服的小贝塔。"
							:"真正的支配者对待他们顺从的婊子。"));
			
			if(immobileSleep) {
				sb.append("[npc.Name]发出[npc.a_moan+]，将[npc.cock+]尽根没入[npc2.namePos][npc2.ass+]，邪魅地咧嘴笑着，看着[npc2.she]醒来后发出[npc2.a_moan+]。");
			} else if(immobile) {
				sb.append("[npc.name]发出[npc.a_moan+]，将[npc.cock+]尽根没入[npc2.namePos][npc2.ass+]，邪魅地咧嘴笑着，而[npc2.she]继续像无生命的性爱玩偶一样一动不动。");
			} else {
				sb.append("[npc.name]发出[npc.a_moan+]，将[npc.cock+]尽根没入[npc2.namePos][npc2.ass+]，邪魅地咧嘴笑着，[npc2.name]发出[npc2.a_moan+]。");
			}
			
			sb.append("<br/><br/>");

			boolean isDoggyStyle = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_ALL_FOURS);
			if(immobile) {
				sb.append("[npc.she]向下探身，抓住[npc2.namePos]的肩膀，整个身子的重量压在[npc2.her]背上。[npc.she]粗暴地伏在[npc2.herHim]身上。"
						+ "随着[npc.namePos]的重量压在[npc2.herHim]身上，[npc2.name]"
						+ (isDoggyStyle
								?"被迫俯卧在地，"
								:"被压在[npc.herHim]下面，")
						+ "保持着沉默和静止，处于完全顺从的姿态。"
						+ "[npc.name]弯下身，悸动的[npc.cock]仍操在[npc2.namePos][npc2.asshole+]里，[npc.she]冲着[npc2.her]的[npc2.ear]压制性地咆哮，"
								+ "[npc.speech(你这个蠢玩偶！你只配做我的性玩具鸡巴套子！)]");
			} else {
				sb.append("[npc.she]向下探身，抓住[npc2.namePos]的肩膀，整个身子的重量压在[npc2.her]背上。[npc.she]粗暴地伏在[npc2.herHim]身上。"
						+ "随着[npc.namePos]的重量压在[npc2.herHim]身上，[npc2.name]"
						+ (isDoggyStyle
								?"发出[npc2.a_moan+]，被迫俯卧在地。"
								:"发出[npc2.a_moan+]，被压在[npc.herHim]下面。")
						+ "[npc.name]弯下身，悸动的[npc.cock]仍操在[npc2.namePos][npc2.asshole+]里，[npc.she]冲着[npc2.her]的[npc2.ear]压制性地咆哮，"
								+ "[npc.speech(你这个小婊子！你只能乖乖做我的鸡巴套哦！)]");
			}
			
			sb.append("<br/><br/>");

			if(immobile) {
				sb.append("即使是这些有辱人格的话语也不能打断[npc2.namePos]，[npc2.she]仍然完全一动不动，[npc.namePos]承受着高潮的冲刷。");
			} else {
				sb.append("[npc2.name]听到这些侮辱之语，发出[npc2.moan+]，却更使[npc.Name]快要去了。" );
			}
			sb.append("[npc.name]把[npc2.namePos][npc2.face+]按在地上蹭，达到了高潮，[npc.balls+]骤然缩紧");
			
			switch (Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					sb.append("，[npc.she]发现自己甚至连一滴都射不出来，[npc.her]的支配性减弱了。");
					break;
				case ONE_TRICKLE:
					sb.append("，射出几滴[npc.cum+]");
					break;
				case TWO_SMALL_AMOUNT:
					sb.append("，喷出少量[npc.cum+]");
					break;
				case THREE_AVERAGE:
					sb.append("，射出[npc.cum+]");
					break;
				case FOUR_LARGE:
					sb.append("，射出[npc.cum+]");
					break;
				case FIVE_HUGE:
					sb.append("，大量[npc.cum+]尽数射出");
					break;
				case SIX_EXTREME:
					sb.append("，巨量[npc.cum+]尽数射出");
					break;
				case SEVEN_MONSTROUS:
					sb.append("，极巨量[npc.cum+]尽数射出");
					break;
			}
			
			if(Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE) {
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					sb.append("，落在了避孕套里。");
				} else {
					sb.append("，灌进[npc2.namePos][npc2.asshole+]。");
				}
			}

			if (Main.sex.getCharacterPerformingAction().getVaginaType() != VaginaType.NONE) {
				sb.append("<br/><br/>");
				sb.append("[npc.Name]将仍在悸动的肉棒从[npc2.namePos]被操透的[npc2.ass]里抽出，感到第二次高潮的预兆在下体翻涌。"
						+ "[npc.Name]抓住[npc2.namePos]的屁股，撑起自己，[npc.her]夹紧大腿，[npc.pussy+]一缩一缩。"
						+ "经久不绝的高潮冲刷着[npc.Name]，[npc.herHim]愉悦地[npc.moanVerb]，完全沉浸在雌性器的欢愉中。");
				
			} else {
				sb.append("<br/><br/>");
				sb.append("[npc.Name]将仍在悸动的肉棒从[npc2.namePos]被操透的[npc2.ass]里抽出，[npc.she]向下看，发现[npc2.herHim]被操得乱七八糟。");
			}

			sb.append("<br/><br/>");
			
			sb.append("[npc.Name]呼哧呼哧喘着气，突然想起自己的计划，便又绕到与地相亲着的[npc2.namePos]身旁。" 
					+ (Main.sex.getCharacterTargetedForSexAction(this).hasHair()
							?"[npc.she]向下探身，粗暴地攥住一把[npc2.namePos][npc2.hair+]，"
							:"[npc.she]向下探身，粗暴地抓住[npc2.namePos]的脖子，")
					+(immobile?"":"[npc2.name]还没来得及反应，[npc.she]就")
					+"将[npc2.her][npc2.face+]压到[npc.her][npc.cock+]上。");
			
			if(immobile) {
				sb.append("[npc2.Name]毫无反应，"+(Main.sex.getCharacterPerformingAction().isPlayer()?"你":"[npc2.her]的支配伴侣")+"让[npc2.herHim]尝了尝自己[npc2.ass]的味道，"
							+ "[npc.she]牢牢将[npc2.herHim]按在原处，[npc.moansVerb+]着，让[npc2.namePos]用嘴给[npc.herHim]清理干净。"
						+ "<br/><br/>"
						+ "[npc.Name]这样用了一会[npc2.name]，然后放开了[npc2.herHim]，对[npc2.herHim]笑了笑，想着接下来做什么……");
			} else {
				sb.append("[npc2.Name]边[npc2.moansVerb]边来回扭动，"+(Main.sex.getCharacterPerformingAction().isPlayer()?"你":"[npc2.her]的支配伴侣")+"让[npc2.her]尝了尝自己[npc2.ass]的味道，" 
							+ "[npc.she]牢牢将[npc2.herHim]按在原处，[npc.moansVerb+]着，让[npc2.name]用灵巧的[npc2.tongue]给[npc.herHim]清理干净。"
						+ "<br/><br/>"
						+ "[npc.Name]这样用了一会[npc2.name]，最终放开了[npc2.herHim]。[npc2.she]长出了一口气，瘫倒在地，完全被强硬的对待玩坏了。");
			}

			return sb.toString();
		}
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(SexAreaOrifice.ANUS);
			} else {
				return null;
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS);
		}
		
	};
	
	public static final SexAction DOGGY_DOMINANT_ORGASM_PUSSY = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isBaseRequirementsMet() {
			SexSlot slot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction());
			SexSlot targetedSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this));
			
			return Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.MOUTH)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getDominantParticipants(false).size()==1
					&& Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					&& (slot.hasTag(SexSlotTag.BEHIND_ALL_FOURS)
							|| (slot.hasTag(SexSlotTag.MISSIONARY) && targetedSlot.hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<=0)
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
//					&& (Main.sex.getCharacterPerformingAction().isPlayer() || !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SADIST).isNegative());
		}
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Math.random()<0.75f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "先穴后嘴";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
				return "你粗暴地操到[npc2.name]伏在地上，在避孕套里射满精液，摘下它，让[npc2.her]给你舔干净。";
			}
			return "你粗暴地操到[npc2.name]伏在地上，射在[npc2.pussy]里，抽出肉棒，让[npc2.her]给你舔干净。";
		}
		@Override
		public String getDescription() {
			boolean immobile = Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.COMMAND);
			boolean immobileSleep = Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.SLEEP);

			StringBuilder sb = new StringBuilder();
			
			sb.append("[npc.Name]感觉[npc2.namePos][npc2.pussy+]箍住[npc.her][npc.cock+]，便决定让[npc2.herHim]看看" 
						+ (Main.sex.getCharacterPerformingAction().getRace()==Race.WOLF_MORPH || Main.sex.getCharacterPerformingAction().getRace()==Race.DOG_MORPH
							?"阿尔法对待他们驯服的小贝塔。"
							:"真正的支配者对待他们顺从的婊子。"));
			
			if(immobileSleep) {
				sb.append("[npc.Name]发出[npc.a_moan+]，将[npc.cock+]尽根没入[npc2.namePos][npc2.pussy+]，邪魅地咧嘴笑着，看着[npc2.she]醒来后发出[npc2.a_moan+]。");
			} else if(immobile) {
				sb.append("[npc.name]发出[npc.a_moan+]，将[npc.cock+]尽根没入[npc2.namePos][npc2.pussy+]，邪魅地咧嘴笑着，而[npc2.she]继续像无生命的性爱玩偶一样一动不动。");
			} else {
				sb.append("[npc.Name]发出[npc.a_moan+]，将[npc.cock+]尽根没入[npc2.namePos][npc2.pussy+]，邪魅地咧嘴笑着，[npc2.name]发出[npc2.a_moan+]。" );
			}
			
			sb.append("<br/><br/>");

			boolean isDoggyStyle = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_ALL_FOURS);
			if(immobile) {
				sb.append("[npc.she]向下探身，抓住[npc2.namePos]的肩膀，整个身子的重量压在[npc2.her]背上。[npc.she]粗暴地伏在[npc2.herHim]身上。"
						+ "随着[npc.namePos]的重量压在[npc2.herHim]身上，[npc2.name]"
						+ (isDoggyStyle
								?"被迫俯卧在地，"
								:"被压在[npc.herHim]下面，")
						+"保持着沉默和静止，处于完全顺从的姿态。"
						+ "[npc.Name]俯下身，悸动的[npc.cock]仍然被[npc2.namePos][npc2.pussy+]含住，[npc.her]在[npc2.her][npc2.ear]边耀武扬威，"
								+ "[npc.speech(你这个蠢玩偶！你只配做我的性玩具鸡巴套子！)]");
			} else {
				sb.append("[npc.she]向下探身，抓住[npc2.namePos]的肩膀，整个身子的重量压在[npc2.her]背上。[npc.she]粗暴地伏在[npc2.herHim]身上。"
						+ "随着[npc.namePos]的重量压在[npc2.herHim]身上，[npc2.name]"
						+ (isDoggyStyle
								?"发出[npc2.a_moan+]，被迫俯卧在地。"
								:"发出[npc2.a_moan+]，被压在[npc.herHim]下面。")
						+ "[npc.Name]俯下身，悸动的[npc.cock]仍然被[npc2.namePos][npc2.pussy+]含住，[npc.her]在[npc2.her][npc2.ear]边耀武扬威，"
								+ "[npc.speech(你这个小婊子！你只能乖乖做我的鸡巴套哦！)]");
			}
			
			sb.append("<br/><br/>");

			if(immobile) {
				sb.append("即使是这些有辱人格的话语也不能打断[npc2.namePos]，[npc2.she]仍然完全一动不动，[npc.namePos]承受着高潮的冲刷。");
			} else {
				sb.append("[npc2.name]听到这些侮辱之语，发出[npc2.moan+]，却更使[npc.Name]快要去了。" );
			}
			sb.append("[npc.name]把[npc2.namePos][npc2.face+]按在地上蹭，达到了高潮，[npc.balls+]骤然缩紧");
			
			switch (Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					sb.append("，[npc.she]发现自己甚至连一滴都射不出来，[npc.her]的支配性减弱了。");
					break;
				case ONE_TRICKLE:
					sb.append("，射出几滴[npc.cum+]");
					break;
				case TWO_SMALL_AMOUNT:
					sb.append("，喷出少量[npc.cum+]");
					break;
				case THREE_AVERAGE:
					sb.append("，射出[npc.cum+]");
					break;
				case FOUR_LARGE:
					sb.append("，射出[npc.cum+]");
					break;
				case FIVE_HUGE:
					sb.append("，大量[npc.cum+]尽数射出");
					break;
				case SIX_EXTREME:
					sb.append("，巨量[npc.cum+]尽数射出");
					break;
				case SEVEN_MONSTROUS:
					sb.append("，极巨量[npc.cum+]尽数射出");
					break;
			}
			
			if(Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE) {
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					sb.append("，落在了避孕套里。");
				} else {
					sb.append("，灌进[npc2.namePos][npc2.pussy+]。");
				}
			}

			if (Main.sex.getCharacterPerformingAction().getVaginaType() != VaginaType.NONE) {
				sb.append("<br/><br/>");
				sb.append( "[npc.Name]将仍在悸动的肉棒从[npc2.namePos]被操透的[npc2.pussy]中抽出，[npc.she]感到第二波高潮涌上下体。"
						+ "[npc.Name]抓住[npc2.namePos]的屁股，撑起自己，[npc.her]夹紧大腿，[npc.pussy+]一缩一缩。"
						+ "经久不绝的高潮冲刷着[npc.Name]，[npc.herHim]愉悦地[npc.moanVerb]，完全沉浸在雌性器的欢愉中。");
				
			} else {
				sb.append("<br/><br/>");
				sb.append("[npc.Name]将仍在悸动的肉棒从[npc2.namePos]被操透的[npc2.pussy]里抽出，[npc.she]向下看，发现[npc2.herHim]被操得乱七八糟。");
			}

			sb.append("<br/><br/>");
			
			sb.append("[npc.Name]呼哧呼哧喘着气，突然想起自己的计划，便又绕到与地相亲着的[npc2.namePos]身旁。" 
					+ (Main.sex.getCharacterTargetedForSexAction(this).hasHair()
							?"[npc.she]向下探身，粗暴地攥住一把[npc2.namePos][npc2.hair+]，"
							:"[npc.she]向下探身，粗暴地抓住[npc2.namePos]的脖子，")
					+(immobile?"":"[npc2.name]还没来得及反应，[npc.she]就")
					+"将[npc2.her][npc2.face+]压到[npc.her][npc.cock+]上。");
			
			if(immobile) {
				sb.append("[npc2.Name]毫无反应，"+(Main.sex.getCharacterPerformingAction().isPlayer()?"你":"[npc2.her]的支配伴侣")+"让[npc2.herHim]尝了尝自己[npc2.pussy]的味道，"
							+ "[npc.she]牢牢将[npc2.herHim]按在原处，[npc.moansVerb+]着，让[npc2.namePos]用嘴给[npc.herHim]清理干净。"
						+ "<br/><br/>"
						+ "[npc.Name]这样用了一会[npc2.name]，然后放开了[npc2.herHim]，对[npc2.herHim]笑了笑，想着接下来做什么……");
			} else {
				sb.append("[npc2.Name]边[npc2.moansVerb]边来回扭动，"+(Main.sex.getCharacterPerformingAction().isPlayer()?"你":"[npc2.her]的支配伴侣")+"让[npc2.herHim]尝了尝自己[npc2.pussy]的味道，"
							+ "[npc.she]牢牢将[npc2.herHim]按在原处，[npc.moansVerb+]着，让[npc2.name]用灵巧的[npc2.tongue]给[npc.herHim]清理干净。"
						+ "<br/><br/>"
						+ "[npc.Name]这样用了一会[npc2.name]，最终放开了[npc2.herHim]。[npc2.she]长出了一口气，瘫倒在地，完全被强硬的对待玩坏了。");
			}
			
			return sb.toString();
		}
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(SexAreaOrifice.VAGINA);
			} else {
				return null;
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
		
	};

}
