package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericPositioning;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.4.9.5
 * @author Innoxia
 */
public class ChairSex {

	private static boolean checkBaseRequirements(PositioningData data, boolean request) {
		return GenericPositioning.checkBaseRequirements(data, request);
//		return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
//				&& Main.sex.getInitialSexManager().getAllowedSexPositions().contains(data.getPosition())
//				&& !(Main.sex.getPosition() == data.getPosition()
//					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==data.getPerformerSlots().get(0)
//					&& Main.sex.getSexPositionSlot(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))==data.getPartnerSlots().get(0))
//				&& data.getPosition().getMaximumSlots()>=Main.sex.getTotalParticipantCount(false)
//				&& Main.sex.getTotalParticipantCount(false)<=(data.getPerformerSlots().size()+data.getPartnerSlots().size())
//				&& (request
//						?Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())!=SexControl.FULL
//						:(Main.sex.getCharacterPerformingAction().isPlayer()
//							?Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
//							:!Main.sex.isCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterPerformingAction())))
//				&& (!request && !Main.sex.getCharacterPerformingAction().isPlayer()
//						?((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(data.getPosition(), data.getPerformerSlots().get(0), data.getPartnerSlots().get(0), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
//						:true);
	}
	
	private static boolean isSittingAvailable(GameCharacter gettingFucked) {
		return true;
//		return !gettingFucked.isTaur();
	}
	
	private static void applyChangeSlotEffects(GameCharacter mover, SexSlot moverSlot, GameCharacter partner, SexSlot partnerSlot) {
		if(Main.sex.getCharacterInPosition(moverSlot)!=null) {
			Main.sex.swapSexPositionSlots(mover, Main.sex.getCharacterInPosition(moverSlot));
		}
		if(Main.sex.getCharacterInPosition(partnerSlot)!=null && !Main.sex.getCharacterInPosition(partnerSlot).equals(partner)) {
			Main.sex.swapSexPositionSlots(partner, Main.sex.getCharacterInPosition(partnerSlot));
		}
		
		Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
		Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
		
		if(Main.sex.isDom(mover)) {
			dominants.put(mover, moverSlot);
		} else {
			submissives.put(mover, moverSlot);
		}
		if(Main.sex.isDom(partner)) {
			dominants.put(partner, partnerSlot);
		} else {
			submissives.put(partner, partnerSlot);
		}

		Main.sex.setSexManager(new SexManagerDefault(
				SexPosition.SITTING,
				dominants,
				submissives){
		});
	}
	
	public static final SexAction SWITCH_TO_STANDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(
						SexSlotStanding.STANDING_DOMINANT,
						SexSlotStanding.STANDING_DOMINANT_TWO,
						SexSlotStanding.STANDING_DOMINANT_THREE,
						SexSlotStanding.STANDING_DOMINANT_FOUR),
				Util.newArrayListOfValues(
						SexSlotStanding.STANDING_SUBMISSIVE,
						SexSlotStanding.STANDING_SUBMISSIVE_TWO,
						SexSlotStanding.STANDING_SUBMISSIVE_THREE,
						SexSlotStanding.STANDING_SUBMISSIVE_FOUR));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "转成站姿";
		}
		@Override
		public String getActionDescription() {
			return "你让[npc2.name]与你一同站起，准备换成另一种性爱姿势。";
		}
		@Override
		public String getDescription() {
			return "[npc.Name]想要换到另一个位置，于是让[npc2.name]跟[npc.herHim]一起站起来。"
					+ "[npc.name]正跟对象面贴着面，[npc.her][npc.moansVerb]着，"
					+ "[npc.speech(让我们试试其他的……)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction SWITCH_TO_GIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isTaur()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "提供口交";
		}
		@Override
		public String getActionDescription() {
			return "跪在[npc2.name]面前给[npc2.herHim]口交。";
		}
		@Override
		public String getDescription() {
			return "[npc.name]决定给[npc2.name]口交，便跪在[npc2.herHim]面前。"
					+ "[npc.her]把嘴巴贴向[npc2.namePos]下体，[npc.moansVerb]道，"
					+ "[npc.speech(你只需要坐下来享受就行了！)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.PERFORMING_ORAL,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.SITTING);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_GIVING_ORAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isTaur()
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "提供口交(请求)";
		}
		@Override
		public String getActionDescription() {
			return "跪在[npc2.name]面前，以便给[npc2.herHim]口交，";
		}
		@Override
		public String getDescription() {
			return "你呻吟着跪在[npc2.name]面前。[npc.speech(求你了，我想要用我的嘴……)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_TO_GIVING_ORAL_TO_TAUR = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING),
				Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL));

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).isTaur()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "提供口交";
		}
		@Override
		public String getActionDescription() {
			return "坐下来之后，让[npc2.name]转过身背对着你，并且将动物般的生殖器展现在你的面前，你就可以用嘴巴侍奉[npc2.herHim]了。";
		}
		@Override
		public String getDescription() {
			return "[npc.Name]想要用嘴巴侍奉[npc2.name]，于是坐下来，然后让[npc2.name]转过身背对着你，这样[npc2.her]动物般的生殖器便在你面前一览无余。"
					+ "[npc.her]把嘴巴贴向[npc2.namePos]下体，[npc.moansVerb]道，"
					+ "[npc.speech(我已经等不及想要尝尝你的味道了！)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.SITTING,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_GIVING_ORAL_TO_TAUR_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING),
				Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).isTaur()
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "提供口交(请求)";
		}
		@Override
		public String getActionDescription() {
			return "试着坐下，让[npc2.name]转过身背对着你，并且将动物般的生殖器展现在你的面前，你就可以用嘴巴侍奉[npc2.herHim]了。";
		}
		@Override
		public String getDescription() {
			return "你尝试转变姿势，这样坐下来之后，再让[npc2.name]把动物般的生殖器对准你。"
					+ "同时你[npc.moan]着，[npc.speech(拜托了，我好想用嘴巴……)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_TO_RECEIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING),
				Util.newArrayListOfValues(SexSlotSitting.PERFORMING_ORAL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Main.sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "接受口交";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]跪在你面前，用嘴巴侍奉你。";
		}
		@Override
		public String getDescription() {
			return "[npc.Name]想要享受享受口穴侍奉，于是让[npc2.name]跪在[npc.herHim]的面前。"
					+ "[npc.Name]低头盯着[npc2.her]的[npc2.eyes]，[npc.moansVerb]道，"
					+ "[npc.speech(是时候动动嘴了！)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.SITTING,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.PERFORMING_ORAL);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_RECEIVING_ORAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING),
				Util.newArrayListOfValues(SexSlotSitting.PERFORMING_ORAL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Main.sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "接受口交(请求)";
		}
		@Override
		public String getActionDescription() {
			return "尝试让[npc2.name]跪在你的面前，这样[npc2.she]就能给你口交了。";
		}
		@Override
		public String getDescription() {
			return "你尝试转变姿势，让[npc2.name]跪在你的面前，同时[npc.moan]道，[npc.speech(拜托了，我想让你用你的嘴巴来……)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_TO_RECEIVING_ORAL_AS_TAUR = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isTaur()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "接受口交";
		}
		@Override
		public String getActionDescription() {
			return "转过身将动物般的生殖器展露在[npc2.name]面前，这样[npc2.she]就能给你口交了。";
		}
		@Override
		public String getDescription() {
			return "[npc.Name]想要享受享受口穴侍奉，转过身后退一步，将[npc.her]动物般的生殖器展露在[npc2.name]面前。"
					+ "[npc.Name]回头望去，[npc.moansVerb]道，"
					+ "[npc.speech(是时候动动嘴了！)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.SITTING);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_RECEIVING_ORAL_AS_TAUR_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isTaur()
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "接受口交(请求)";
		}
		@Override
		public String getActionDescription() {
			return "尝试转过身将动物般的生殖器展露在[npc2.name]面前，这样[npc2.she]就能给你口交了。";
		}
		@Override
		public String getDescription() {
			return "[npc.Name]尝试转过身，将动物般的生殖器对准[npc2.namePos]的嘴巴。"
					+ "同时[npc.moans]道，[npc.speech(拜托了，我想让你用嘴巴……)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_SITTING_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING),
				Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP));

		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Main.sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "坐式(在下)";
		}
		@Override
		public String getActionDescription() {
			return "调整姿势，变成坐在下面的那个，[npc2.name]则坐在你的大腿上。";
		}
		@Override
		public String getDescription() {
			return "[npc.Name]决定调整一下姿势，于是让[npc2.name]先站起来，自己坐了下去，然后把[npc2.herHim]拉坐到自己的大腿上。"
					+ "然后盯着对方的[npc2.eyes]，[npc.moansVerb]道，"
					+ "[npc.speech(真是个乖[npc2.girl]！)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.SITTING,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.SITTING_IN_LAP);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_SITTING_BOTTOM_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING),
				Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Main.sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "坐式(在下)(请求)";
		}
		@Override
		public String getActionDescription() {
			return "努力让[npc2.name]坐在你大腿上。";
		}
		@Override
		public String getDescription() {
			return "你尝试调整姿势，让[npc2.name]坐在你大腿上，同时[npc.moan]道，[npc.speech(求你了，我想当下面那个……)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_SITTING_TOP = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "坐式(在上)";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "交换位置，让[npc2.name]变成坐在下面那个，你坐在[npc2.her]大腿上。";
			} else {
				return "调整姿势，让[npc2.name]变成坐在下面的那个，而你则转过身，将自己动物般的胯部落在[npc2.her]的大腿上。";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "[npc.Name]决定换个姿势，于是让[npc2.name]坐下，自己则坐在了[npc2.her]的大腿上。"
						+ "随后便低头盯着[npc2.her]的[npc2.eyes]，[npc.moansVerb]道，"
						+ "[npc.speech(真是个乖[npc2.girl]！)]";
			} else {
				return "[npc.Name]决定调整姿势，让[npc2.name]坐下来，自己则转过身去，将动物般的胯部落在[npc2.namePos]的大腿上。"
						+ "[npc.Name]回头望过去，[npc.moansVerb]道，"
						+ "[npc.speech(该给你骑一下了！)]";
			}
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.SITTING_IN_LAP,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.SITTING);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_SITTING_TOP_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "坐式(在上)(请求)";
		}
		@Override
		public String getActionDescription() {
			return "试图坐在[npc2.namePos]腿上。";
		}
		@Override
		public String getDescription() {
			return "你尝试调整姿势，这样就能坐在[npc2.namePos]的大腿上，同时[npc.moan]道，[npc.speech(求你了，我想当上面那个……)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_STANDING_BETWEEN_LEGS = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING_BETWEEN_LEGS),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));

		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Main.sex.getCharacterTargetedForSexAction(this))
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "腿间";
		}
		@Override
		public String getActionDescription() {
			return "调整姿势，让[npc2.name]坐下来，而你"
					+(SexSlotSitting.SITTING_BETWEEN_LEGS.isStanding(Main.sex.getCharacterPerformingAction())?"站":"跪")+"在[npc2.her]的[npc2.legs]间，准备操[npc2.herHim]。";
		}
		@Override
		public String getDescription() {
			return "[npc.Name]决定换个姿势，于是让[npc2.name]坐下，自己则"
						+(SexSlotSitting.SITTING_BETWEEN_LEGS.isStanding(Main.sex.getCharacterPerformingAction())?"站":"跪")+"在[npc2.her][npc2.legs]间。"
					+ "随后便低头盯着[npc2.her]的[npc2.eyes]，[npc.moansVerb]道，"
					+ "[npc.speech(是时候好好插你一顿了！)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.SITTING_BETWEEN_LEGS,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.SITTING);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction SWITCH_STANDING_BETWEEN_LEGS_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING_BETWEEN_LEGS),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Main.sex.getCharacterTargetedForSexAction(this))
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "腿间(请求)";
		}
		@Override
		public String getActionDescription() {
			return "试图和[npc2.name]交换位置，让[npc2.name]变成坐在下面的，和你"
						+(SexSlotSitting.SITTING_BETWEEN_LEGS.isStanding(Main.sex.getCharacterPerformingAction())?"站":"跪")+"在[npc2.her]的[npc2.legs]间，准备操[npc2.herHim]。";
		}
		@Override
		public String getDescription() {
			return "你尝试转变姿势，这样[npc2.nameIsFull]就可以坐下来，而你则"
						+(SexSlotSitting.SITTING_BETWEEN_LEGS.isStanding(Main.sex.getCharacterPerformingAction())?"站":"跪")+"在[npc2.her]的[npc2.legs]间。"
						+ "同时你[npc.moan]着，[npc.speech(求你了，我想用这个姿势插进去……)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction PARTNER_POSITION_RESPONSE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPositionRequest()!=null
					&& !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			boolean isHappy = ((NPC)Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(
					Main.sex.getPositionRequest().getPosition(),
					Main.sex.getPositionRequest().getPartnerSlots().get(0),
					Main.sex.getPositionRequest().getPerformerSlots().get(0),
					Main.game.getPlayer());
			
			if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotSitting.SITTING_IN_LAP) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name]一把推倒了[npc2.name]，跨坐在[npc2.her]身上，倾着身子恶狠狠地盯住[npc2.her]的眼睛，[npc.moansVerb]道，"
									+ "[npc.speech(好了，你个骚货，该是坐你一下了！)]";
						default:
							return "[npc.Name]把[npc2.name]推倒，跨坐在了[npc2.her]身上，倾着身子紧盯住[npc2.her]的眼睛，[npc.moansVerb]道，"
									+ "[npc.speech(当然了，我会骑上来的……)]";
					}
					
				} else {
					return "[npc.Name]拽住了[npc2.name]的[npc2.arm]，把[npc2.herHim]扳回了原来的姿势，怒斥道，"
							+ "[npc.speech(你以为自己在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotSitting.SITTING) {
				if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotSitting.SITTING_IN_LAP) {
					if(isHappy) {
						switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
							case DOM_ROUGH:
								return "[npc.Name]坐下来，抓住了[npc2.namePos]的[npc2.arm]，狠狠地一扯，"
											+ "就把[npc2.herHim]拉倒，再次跨坐在了腿上。[npc.Name]紧紧靠上去恶狠狠地盯着[npc2.her]的眼睛，[npc.moansVerb]道，"
										+ "[npc.speech(那好，小贱货，最好让我舒服点！)]";
							default:
								return "[npc.Name]坐下来后，抓住了[npc2.namePos]的[npc2.arm]，用力地一扯，"
											+ "就把[npc2.herHim]拉倒，再次跨坐在了腿上。[npc.Name]紧紧靠上去盯着[npc2.her]的眼睛，[npc.moansVerb]道，"
										+ "[npc.speech(真乖……)]";
						}
						
					} else {
						return "[npc.Name]拽住了[npc2.name]的[npc2.arm]，把[npc2.herHim]扳回了原来的姿势，怒斥道，"
								+ "[npc.speech(你以为自己在干嘛？！你最好别<i>敢</i>有下一次！)]";
					}
					
				} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL) {
					if(isHappy) {
						switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
							case DOM_ROUGH:
								return "[npc.Name]让[npc2.name]调整好姿势，然后就一把拽住了[npc2.her]的后腿，给[npc2.her]动物般的下体拉到了自己的脸上。"
										+ "[npc.Name]对着[npc2.her]的屁股猛拍了一下，便[npc.moansVerb]道，"
										+ "[npc.speech(那好，小贱货，最好让我舒服点！)]";
							default:
								return "[npc.Name]让[npc2.name]调整好姿势，然后就一把拽住了[npc2.her]的后腿，给[npc2.her]动物般的下体拉到了自己的脸上。"
										+ "[npc.Name]对着[npc2.her]的屁股调情似的拍了一下，便[npc.moansVerb]道，"
										+ "[npc.speech(真乖……)]";
						}
						
					} else {
						return "[npc.Name]拽住了[npc2.name]的[npc2.arm]，把[npc2.herHim]扳回了原来的姿势，怒斥道，"
								+ "[npc.speech(你以为自己在干嘛？！你最好别<i>敢</i>有下一次！)]";
					}
					
				} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotSitting.SITTING_BETWEEN_LEGS) {
					if(isHappy) {
						switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
							case DOM_ROUGH:
								if(Main.sex.getCharacterPerformingAction().hasLegs()) {
									return "[npc.Name]让[npc2.name]调整好姿势，自己坐了下来，然后抬起[npc.legs]钳住了[npc2.namePos]的腰，强行把[npc2.herHim]拖得更近。"
											+ "[npc.Name]目露凶光，张开[npc.legs][npc.moansVerb]道，"
											+ "[npc.speech(那好，小贱货，最好让我舒服点！)]";
								} else {
									return "[npc.Name]让[npc2.name]调整好姿势，然后坐了下来，把自己的下体展露在[npc2.name]面前。"
											+ "[npc.she]抬起头目露凶光，[npc.moansVerb]道，"
											+ "[npc.speech(那好，小贱货，最好让我舒服点！)]";
								}
							default:
								if(Main.sex.getCharacterPerformingAction().hasLegs()) {
									return "[npc.Name]让[npc2.name]调整好姿势，自己坐了下来，然后抬起[npc.legs]钳住了[npc2.namePos]的腰，把[npc2.herHim]拖得更近。"
											+ "[npc.Name]面露微笑，张开[npc.legs][npc.moansVerb]道，"
											+ "[npc.speech(那就来吧，来干我吧！)]";
								} else {
									return "[npc.Name]让[npc2.name]调整好姿势，然后坐了下来，把自己的下体展露在[npc2.name]面前。"
											+ "[npc.Name]面露微笑，[npc.moansVerb]道，"
											+ "[npc.speech(那就来吧，来干我吧！)]";
								}
						}
						
					} else {
						return "[npc.Name]拽住了[npc2.name]的[npc2.arm]，把[npc2.herHim]扳回了原来的姿势，怒斥道，"
								+ "[npc.speech(你以为自己在干嘛？！你最好别<i>敢</i>有下一次！)]";
					}
					
				} else {
					if(isHappy) {
						switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
							case DOM_ROUGH:
								return "[npc.Name]坐下来，抓住了[npc2.namePos]的[npc2.arm]，狠狠地一扯，便把[npc2.herHim]拉倒在地，跪在了自己面前。"
										+ "[npc.Name]身子前倾，恶狠狠地盯住[npc2.her]的眼睛，[npc.moansVerb]道，"
										+ "[npc.speech(那好，小骚货，赶紧把嘴巴动起来！)]";
							default:
								return "[npc.Name]坐下来，抓住了[npc2.namePos]的[npc2.arm]，用力一扯，便让[npc2.herHim]跪在了自己面前。"
										+ "[npc.Name]身子前倾，紧盯住[npc2.her]的眼睛，[npc.moansVerb]道，"
										+ "[npc.speech(乖[npc2.girl]。赶紧把嘴巴动起来吧……)]";
						}
						
					} else {
						return "[npc.Name]拽住了[npc2.name]的[npc2.arm]，把[npc2.herHim]扳回了原来的姿势，怒斥道，"
								+ "[npc.speech(你以为自己在干嘛？！你最好别<i>敢</i>有下一次！)]";
					}
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotSitting.PERFORMING_ORAL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name]一把推倒[npc2.name]，跪在了[npc2.herHim]的面前，抬头恶狠狠地盯住[npc2.her]的眼睛，[npc.moansVerb]道，"
									+ "[npc.speech(好吧，你个小贱货，要不是我喜欢用嘴巴，你就偷着乐吧！)]";
						default:
							return "[npc.Name]把[npc2.name]推倒，跪在了[npc2.herHim]的面前，抬头紧盯住[npc2.her]的眼睛，[npc.moansVerb]道，"
									+ "[npc.speech(我很喜欢用嘴巴哦……)]";
					}
					
				} else {
					return "[npc.Name]拽住了[npc2.name]的[npc2.arm]，把[npc2.herHim]扳回了原来的姿势，怒斥道，"
							+ "[npc.speech(你以为自己在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
			}
			
			return "";
		}

		@Override
		public void applyEffects() {
			if(((NPC)Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(
					Main.sex.getPositionRequest().getPosition(),
					Main.sex.getPositionRequest().getPartnerSlots().get(0),
					Main.sex.getPositionRequest().getPerformerSlots().get(0),
					Main.game.getPlayer())) {

				applyChangeSlotEffects(
						Main.sex.getCharacterPerformingAction(),
						Main.sex.getPositionRequest().getPartnerSlots().get(0),
						Main.game.getPlayer(),
						Main.sex.getPositionRequest().getPerformerSlots().get(0));
				
//				GenericPositioningNew.setNewSexManager(Main.sex.getPositionRequest(), true);
			}
			
			Main.sex.setPositionRequest(null);
		}
	};
	
}
