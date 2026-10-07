package com.lilithsthrone.game.sex.sexActions.submission;

import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.11
 * @version 0.3.4
 * @author Innoxia
 */
public class FortressAlphaLeaderSA {
	
	public static boolean isBothTargetsUsed() {
		try {
			return Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))>0
					&& Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_TWO), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))>0;
		} catch(Exception ex) {
			return true;
		}
	}
	
	public static GameCharacter getBlowjobTarget() {
		return Main.sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressAlphaLeader.class), SexAreaPenetration.PENIS).isEmpty()
				?null
				:Main.sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressAlphaLeader.class), SexAreaPenetration.PENIS).get(0);
	}
	
	private static GameCharacter getOtherTarget() {
		try {
			if(getBlowjobTarget()==null) {
				if(Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))>0) {
					return Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_TWO);
				} else {
					return Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL);
				}
			}
			return Main.sex.getSexPositionSlot(getBlowjobTarget())==SexSlotStanding.PERFORMING_ORAL
					?Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_TWO)
					:Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL);
		} catch(Exception ex) {
			return null;
		}
	}
	
	public static final SexAction PARTNER_ROUND_TWO_ONGOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "第二轮";
		}

		@Override
		public String getActionDescription() {
			return "告诉[npc2.name]你还没准备好！";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()==null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressAlphaLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "[npc.name]把[npc2.namePos]的头向后扳去，粗暴地将臀部向前猛撞，把[npc.cock+]完全没入[npc2.her]的喉咙里。"
					+ "[npc.she]低低地呻吟了一声，[npc2.namePos]嘴唇碰撞着[npc.her]的鸡巴根部，令[npc.she]的呻吟变成了低沉的嘶吼，"
					+ "[npc.speechNoEffects(我还没准备好，小婊子！希望你准备好应对第二轮了！)]";
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_ONGOING_SWITCH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "第二轮(选择)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getOtherTarget(), "选择操[npc.namePos]的脸！");
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()!=null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressAlphaLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					"[npc.name]抓住[npc2.namePos]的两颊，把[npc2.herHim]向后推去，[npc.her][npc.cock+]从[npc2.her]滑了出来，在[npc2.her][npc2.lips]边牵起丝缕的精液和唾液。<br/>"
					+ "[npc.her]的注意力转移到[npc3.name]上，[npc.name]将[npc.her]那覆满精液[npc.cock+]顶部怼向[npc3.her][npc3.lips+]，"
						+ "接着[npc.her]粗暴地坐在[npc3.her]脸上，直直地向下插入[npc3.her]的喉咙。"
					+ "[npc.she]低低地呻吟了一声，当[npc3.namePos][npc3.lips]碰撞起[npc.her]的鸡巴根部，[npc.she]的呻吟变成了低沉的嘶吼，"
					+ "[npc.speechNoEffects(轮到你了，小婊子！希望你准备好应对我的第二轮了！)]");
		}
		
		@Override
		public void applyEffects(){
			GameCharacter otherTarget = getOtherTarget();
			
			Map<AbstractClothing, DisplacementType> clothingTouched = otherTarget.displaceClothingForAccess(CoverableArea.MOUTH, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
			
			Main.sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.MOUTH, otherTarget, false);
			
			Main.sex.stopOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBlowjobTarget(),
					SexAreaOrifice.MOUTH);

			Main.sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Main.sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Main.sex.applyOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					otherTarget,
					SexAreaOrifice.MOUTH,
					true);
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "第二轮";
		}

		@Override
		public String getActionDescription() {
			return "告诉[npc2.name]你还没准备好！";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()==null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressAlphaLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "[npc.name]把[npc2.namePos]的头向后扳去，将自己那覆满精液[npc.cock+]顶部怼向[npc2.her]的嘴唇，"
						+ "然后粗暴地将臀部向前猛撞，直直地向下插入[npc2.her]的喉咙。"
					+ "[npc.she]低低地呻吟了一声，[npc2.namePos]嘴唇碰撞着[npc.her]的鸡巴根部，令[npc.she]的呻吟变成了低沉的嘶吼，"
					+ "[npc.speechNoEffects(我还没准备好，小婊子！希望你准备好应对第二轮了！)]";
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_START_SWITCH = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "第二轮(选择)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getOtherTarget(), "选择操[npc.namePos]的脸！");
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()!=null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.ORGASM
							|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressAlphaLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					"[npc.her]的注意力转移到[npc3.name]上，[npc.name]将[npc.her]那覆满精液[npc.cock+]顶部怼向[npc3.her][npc3.lips+]，"
						+ "接着[npc.her]粗暴地坐在[npc3.her]脸上，直直地向下插入[npc3.her]的喉咙。"
					+ "[npc.she]低低地呻吟了一声，当[npc3.namePos][npc3.lips]碰撞起[npc.her]的鸡巴根部，[npc.she]的呻吟变成了低沉的嘶吼，"
					+ "[npc.speechNoEffects(轮到你了，小婊子！希望你准备好应对我的第二轮了！)]");
		}
		
		@Override
		public void applyEffects(){
			GameCharacter otherTarget = getOtherTarget();

			Map<AbstractClothing, DisplacementType> clothingTouched = otherTarget.displaceClothingForAccess(CoverableArea.MOUTH, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
			
			Main.sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.MOUTH, otherTarget, false);
			
			Main.sex.stopOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBlowjobTarget(),
					SexAreaOrifice.MOUTH);

			Main.sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Main.sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Main.sex.applyOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					otherTarget,
					SexAreaOrifice.MOUTH,
					true);
		}
	};


	
}
