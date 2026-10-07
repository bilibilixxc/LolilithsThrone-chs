package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.3.2
 * @version 0.4.3.2
 * @author Innoxia
 */
public class LovingActions {
	
	public static final SexAction CARESS_CHEEK = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public boolean isLovingAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			Map<InventorySlot, List<AbstractClothing>> concealedMap = Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction());
			if(concealedMap.containsKey(InventorySlot.MOUTH) && concealedMap.containsKey(InventorySlot.EYES)) {
				return false; // If mouth and eyes are concealed, treat face as being concealed and so unavailable
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
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (mouthFinger || mouthFingerReversed);
		}
		
		@Override
		public String getActionTitle() {
			return "爱抚脸颊";
		}

		@Override
		public String getActionDescription() {
			return "轻柔地抚摸[npc2.namePos]的面庞，向[npc2.herHim]展示爱意。";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"[npc.Name]希望[npc2.name]感受到爱意，缓缓抬起[npc.hand]，用[npc.fingers+]轻柔地抚摸过[npc2.her]的面庞。",
					"[npc.Name]缓缓抬起[npc.hand]，用[npc.fingers+]轻柔地滑过[npc2.namePos]的脸颊，向[npc2.herHim]展示着浓浓爱意。",
					"[npc.Name]希望[npc2.name]能够平静下来，于是抬起[npc.hand]，用[npc.fingers+]宠溺地爱抚着[npc2.her]的脸庞。"));
			
			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							"一声愉快的[npc2.moan]从[npc2.namePos]口中冒出，回应着这次接触，[npc.name]见状也开心地微笑起来。",
							"一声愉快的喘息声从[npc2.namePos]的口中冒出，这足以让[npc.name]知道[npc.her]轻柔的爱抚已经达到了预想的效果。",
							"[npc2.name]发出了愉快的[npc2.moan]，作为对此的回应，让[npc.name]知道[npc.her]的举动得到了回报。"));
				} else {
					sb.append(UtilText.returnStringAtRandom(
							"[npc2.namePos]的口中发出一声抗拒的尖叫，[npc2.she]依然对[npc.namePos]的进一步动作表示抵抗。",
							"[npc2.namePos]的口中发出一声恼怒的大叫，这可不是[npc.name]想要的，见到[npc2.name]依然在抵抗，[npc.she]不禁长叹。",
							"[npc2.name]发出了一声震惊的尖叫，泪水顿时从[npc2.eyes]中溢出，也让[npc.name]知道了[npc.her]的举动并没有达到期望的效果。"));
				}
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction KISS_CHEEK = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public boolean isLovingAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			Map<InventorySlot, List<AbstractClothing>> concealedMap = Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction());
			if(concealedMap.containsKey(InventorySlot.MOUTH) && concealedMap.containsKey(InventorySlot.EYES)) {
				return false; // If mouth and eyes are concealed, treat face as being concealed and so unavailable
			}
			
			boolean mouthTongue = false;
			boolean mouthTongueReversed = false;
			try {
				mouthTongue = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.TONGUE).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthTongueReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.TONGUE);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (mouthTongue || mouthTongueReversed);
		}
		
		@Override
		public String getActionTitle() {
			return "亲吻脸颊";
		}

		@Override
		public String getActionDescription() {
			return "在[npc2.namePos]的脸颊上送上一吻。";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"[npc.Name]希望[npc2.name]感受到爱意，身体微微前倾，在[npc2.her]的脸颊上送上了轻柔的一吻。",
					"[npc.Name]向[npc2.name]微微前倾，嘟起[npc.lips+]，在[npc2.her]的脸颊上留下了宠爱的一吻。",
					"[npc.Name]希望[npc2.name]感受到爱意，身体微微前倾，嘟起[npc.lips+]在[npc2.her]的脸颊上吻了一下。"));

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							"一声愉快的[npc2.moan]从[npc2.namePos]口中冒出，回应着这次接触，[npc.name]见状也开心地微笑起来。",
							"一声愉快的喘息声从[npc2.namePos]的口中冒出，这足以让[npc.name]知道[npc.her]的吻已经达到了预想的效果。",
							"[npc2.name]发出了一声愉快的[npc2.moan]，作为对此的回应，让[npc.name]知道[npc.her]的举动得到了回报。"));
				} else {
					sb.append(UtilText.returnStringAtRandom(
							"[npc2.namePos]的口中发出一声抗拒的尖叫，[npc2.she]依然对[npc.namePos]的进一步动作表示抵抗。",
							"[npc2.namePos]的口中发出一声恼怒的大叫，这可不是[npc.name]想要的，见到[npc2.name]依然在抵抗，[npc.she]不禁长叹。",
							"[npc2.name]发出了一声震惊的尖叫，泪水顿时从[npc2.eyes]中溢出，也让[npc.name]知道了[npc.her]的举动并没有达到期望的效果。"));
				}
			}
			
			return sb.toString();
		}
	};

	public static final SexAction STROKE_BELLY = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)), // Do not directly associate the sex action with Breasts, as otherwise it seems confusing to the player (and there is no SexAreaOrifice for stomach)
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public boolean isLovingAction() {
			return true;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// The reasoning is that if the performer can reach the target's breasts or pussy, they can also reach their stomach:
			boolean fingerBreasts = false;
			boolean fingerVagina = false;
			try {
				fingerBreasts = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.BREAST);
				fingerVagina = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
				// No available finger-breast actions, so can't reach breasts (which implies that they can't reach the stomach)
			}
			
			return (fingerBreasts || fingerVagina) && Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant();
		}
		@Override
		public String getActionTitle() {
			return "轻抚孕肚";
		}
		@Override
		public String getActionDescription() {
			return "轻抚[npc2.namePos]已经怀孕的肚子。";
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			boolean performerPossiblyFather = Main.sex.getCharacterTargetedForSexAction(this).isCharacterPossiblyFather(Main.sex.getCharacterPerformingAction());
			boolean performerIsFather = performerPossiblyFather && Main.sex.getCharacterTargetedForSexAction(this).getPotentialPartnersAsMother().size()==1;
			
			String pregnantBellyDesc = Util.randomItemFromValues("怀孕的小腹", "隆起的小腹", "圆滚的肚子", "怀孕的肚子");
			String fatherText = Main.sex.getCharacterPerformingAction().isFeminine()?"“父亲”":"父亲";
			
			sb.append(UtilText.returnStringAtRandom(
					"[npc.Name]希望[npc2.name]感受到爱意，将[npc.hands]搭在[npc2.her]"+pregnantBellyDesc+"上，轻轻抚摸着。",
					"[npc.Name]将[npc.hands]放在[npc2.namePos]"+pregnantBellyDesc+"上，宠爱地抚摸、轻拍着。",
					"[npc.Name]希望[npc2.name]感受到爱意，将[npc.hands]轻轻地放在[npc2.her]的"+pregnantBellyDesc+"上，宠爱地摸了几下。"));
			
			if(performerIsFather) {
				sb.append(UtilText.returnStringAtRandom(
						"[npc.name]知道[npc.she]就是[npc2.namePos]肚子里孩子的"+fatherText+"，"
								+ "把手放在对方"+pregnantBellyDesc+"上抚摸时，显得格外轻柔且充满爱意。",
						"[npc.name]确信自己就是[npc2.namePos]肚子里孩子的"+fatherText+"，又在对方的小腹上多抚摸了一会儿。",
						"[npc.name]肯定是[npc2.namePos]肚子里孩子的"+fatherText+"，于是便在对方"+pregnantBellyDesc+"上多抚摸了一会儿。"));
				
			} else if(performerPossiblyFather) {
				sb.append(UtilText.returnStringAtRandom(
						"[npc.name]知道[npc.she]可能是[npc2.namePos]肚子里孩子的"+fatherText+"，"
								+ "把手放在对方"+pregnantBellyDesc+"上抚摸时，显得格外轻柔且充满爱意。",
						"[npc.name]认为自己或许是[npc2.namePos]肚子里孩子的"+fatherText+"，又在对方的小腹上多抚摸了一会儿。",
						"[npc.name]或许是[npc2.namePos]肚子里孩子的"+fatherText+"，于是便在对方"+pregnantBellyDesc+"上多抚摸了一会儿。"));
			}

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							"一声愉快的[npc2.moan]从[npc2.namePos]口中冒出，回应着这次接触，[npc.name]见状也开心地微笑起来。",
							"一声愉快的喘息声从[npc2.namePos]的口中冒出，这足以让[npc.name]知道[npc.her]轻柔的爱抚已经达到了预想的效果。",
							"[npc2.name]发出了一声愉快的[npc2.moan]，作为对此的回应，让[npc.name]知道[npc.her]的举动得到了回报。"));
				} else {
					sb.append(UtilText.returnStringAtRandom(
							"[npc2.namePos]的口中发出一声抗拒的尖叫，[npc2.she]依然对[npc.namePos]的进一步动作表示抵抗。",
							"[npc2.namePos]的口中发出一声恼怒的大叫，这可不是[npc.name]想要的，见到[npc2.name]依然在抵抗，[npc.she]不禁长叹。",
							"[npc2.name]发出了一声震惊的尖叫，泪水顿时从[npc2.eyes]中溢出，也让[npc.name]知道了[npc.her]的举动并没有达到期望的效果。"));
				}
			}
			
			return sb.toString();
		}
	};
}
