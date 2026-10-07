package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * Contains all positional changes for both sub and dom.
 * 
 * If sub, positional change is just a suggestion, which the NPC may refuse if they have other preferences.
 * 
 * @since 0.3.1
 * @version 0.3.8.1
 * @author Innoxia
 */
public class GenericPositioning {

	private static String getRequestTooltipText() {
		return "[style.italicsSex(这是一个要求，[npc2.name]可能会拒绝！)]";
	}
	
	public static final SexAction POSITION_SWAP = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isPositionSwap() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().equals(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getInitialSexManager().isSwapPositionAllowed(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().getLegConfiguration()==Main.sex.getCharacterTargetedForSexAction(this).getLegConfiguration() // Can only swap if have same body type
					&& (Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "和[npc2.name]交换";
		}

		@Override
		public String getActionDescription() {
			return "和[npc2.name]交换位置。";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return "你温柔地控制住[npc2.name]，小心翼翼地移动[npc2.herHim]，与之交换了位置，生怕惊醒[npc2.herHim]。";
			}
			return "你控制住[npc2.name]，跟[npc2.herHim]换了个位置，便[npc.moaning]道，"
					+ "[npc.speech(这样会更爽哦！)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.swapSexPositionSlots(Main.game.getPlayer(), Main.sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	public static boolean checkBaseRequirements(PositioningData data, boolean request) {
		for(SexSlot slot : data.getPartnerSlots()) {
			if(!Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()), slot)) {
				return false;
			}
		}
		for(SexSlot slot : data.getPerformerSlots()) {
			if(!Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getCharacterPerformingAction(), slot)) {
				return false;
			}
		}
		return Main.sex.getInitialSexManager().getAllowedSexPositions().contains(data.getPosition())
				&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
				&& !(Main.sex.getPosition() == data.getPosition()
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==data.getPerformerSlots().get(0)
					&& Main.sex.getSexPositionSlot(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))==data.getPartnerSlots().get(0))
				&& data.getPosition().getMaximumSlots()>=Main.sex.getTotalParticipantCount(false)
				&& Main.sex.getTotalParticipantCount(false)<=(data.getPerformerSlots().size()+data.getPartnerSlots().size())
				&& (request
					?(Main.sex.getCharacterPerformingAction().isPlayer()
							&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())!=SexControl.FULL
							&& !Main.sex.isPositioningRequestBlocked(Main.sex.getCharacterPerformingAction(), data.getPosition()))
					:(Main.sex.getCharacterPerformingAction().isPlayer()
						?Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS)
						:!Main.sex.isCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterPerformingAction())
							&& ((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(data.getPosition(), data.getPerformerSlots().get(0), data.getPartnerSlots().get(0), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))));
	}

	public static void setNewSexManager(PositioningData data, boolean requestAccepted) {
		Map<GameCharacter, SexSlot> dominants = new HashMap<>();
		Map<GameCharacter, SexSlot> submissives = new HashMap<>();
		List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		List<GameCharacter> dominantSpectators = new ArrayList<>(Main.sex.getDominantSpectators());
		List<GameCharacter> submissiveSpectators = new ArrayList<>(Main.sex.getSubmissiveSpectators());
		
		GameCharacter performer = Main.sex.getCharacterPerformingAction();
		GameCharacter target = Main.sex.getTargetedPartner(performer);
		if(requestAccepted) {
			target = Main.sex.getCharacterPerformingAction();
			performer = Main.sex.getTargetedPartner(target);
		}
		
		if(Main.sex.isDom(performer)) {
			doms.remove(performer);
			dominants.put(performer, data.getPerformerSlots().get(0));
			for(int i=0; i<doms.size(); i++) {
				if(i+1<data.getPerformerSlots().size()) {
					dominants.put(doms.get(i), data.getPerformerSlots().get(i+1));
				} else {
					dominantSpectators.add(doms.get(i));
				}
			}
			subs.remove(target);
			submissives.put(target, data.getPartnerSlots().get(0));
			for(int i=0; i<subs.size(); i++) {
				if(i+1<data.getPartnerSlots().size()) {
					submissives.put(subs.get(i), data.getPartnerSlots().get(i+1));
				} else {
					submissiveSpectators.add(subs.get(i));
				}
			}
			
		} else {
			doms.remove(target);
			dominants.put(target, data.getPartnerSlots().get(0));
			for(int i=0; i<doms.size(); i++) {
				if(i+1<data.getPartnerSlots().size()) {
					dominants.put(doms.get(i), data.getPartnerSlots().get(i+1));
				} else {
					dominantSpectators.add(doms.get(i));
				}
			}
			subs.remove(performer);
			submissives.put(performer, data.getPerformerSlots().get(0));
			for(int i=0; i<subs.size(); i++) {
				if(i+1<data.getPerformerSlots().size()) {
					submissives.put(subs.get(i), data.getPerformerSlots().get(i+1));
				} else {
					submissiveSpectators.add(subs.get(i));
				}
			}
		}
		Main.sex.setSexManager(new SexManagerDefault(
						data.getPosition(),
						dominants,
						submissives){
				},
				dominantSpectators,
				submissiveSpectators);
		Main.sex.setPositionRequest(null);
	}

	
	
	
	//--------------- ORAL ---------------//
	
	private static List<SexSlot> generatePerformerOralData(GameCharacter performer, GameCharacter receiver) {
		List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		doms.remove(receiver);
		List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		subs.remove(receiver);

		boolean bipedalOral1 = !receiver.isTaur();
		boolean doubleReceiving = false;
		GameCharacter receiver2 = null;
		if(Main.sex.isDom(receiver)) {
			doubleReceiving = doms.size()>=1;
			if(doubleReceiving) {
				receiver2 = doms.get(0);
			}
		} else {
			doubleReceiving = subs.size()>=1;
			if(doubleReceiving) {
				receiver2 = subs.get(0);
			}
		}
		boolean bipedalOral2 = receiver2!=null?!receiver2.isTaur():false;
		
		List<SexSlot> performerSlots = new ArrayList<>();
		if(bipedalOral1) {
			performerSlots.add(SexSlotStanding.PERFORMING_ORAL);
		} else {
			if(receiver.hasPenis()
					&& (!Main.game.isInSex() || ((performer instanceof NPC) && ((NPC)performer).getCurrentSexPreference(receiver)!=null && ((NPC)performer).getCurrentSexPreference(receiver).getTargetedSexArea()==SexAreaPenetration.PENIS))) {
				performerSlots.add(SexSlotStanding.PERFORMING_ORAL);
			} else {
				performerSlots.add(SexSlotStanding.PERFORMING_ORAL_BEHIND);
			}
		}
		if(doubleReceiving) {
			if(bipedalOral2) {
				performerSlots.add(SexSlotStanding.PERFORMING_ORAL_TWO);
			} else {
				if(receiver2.hasPenis()) {
					performerSlots.add(SexSlotStanding.PERFORMING_ORAL_TWO);
				} else {
					performerSlots.add(SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO);
				}
			}
		}
		SexSlot[] slots = new SexSlot[] {
				SexSlotStanding.PERFORMING_ORAL, SexSlotStanding.PERFORMING_ORAL_BEHIND, SexSlotStanding.PERFORMING_ORAL_TWO,
				SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO, SexSlotStanding.STANDING_SUBMISSIVE, SexSlotStanding.STANDING_SUBMISSIVE_TWO};
		for(SexSlot slot : slots) {
			if(!performerSlots.contains(slot)) {
				performerSlots.add(slot);
			}
		}
		
		return performerSlots;
	}
	
	private static PositioningData generateReceivingOralData(GameCharacter performer, GameCharacter receiver) {
		return new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(
						SexSlotStanding.STANDING_DOMINANT,
						SexSlotStanding.STANDING_DOMINANT_TWO),
				generatePerformerOralData(performer, receiver));
	}

	private static PositioningData generatePerformingOralData(GameCharacter performer, GameCharacter receiver) {
		return new PositioningData(
				SexPosition.STANDING,
				generatePerformerOralData(performer, receiver),
				Util.newArrayListOfValues(
						SexSlotStanding.STANDING_DOMINANT,
						SexSlotStanding.STANDING_DOMINANT_TWO));
	}
	
	public static final SexAction POSITION_ORAL_RECEIVING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()), false);
		}
		@Override
		public String getActionTitle() {
			return "站着被口交";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "让[npc2.name]给你口交。一旦[npc2.sheHasFull]开始，你就可以让[npc2.herHim]在你身体前后切换。";
			} else {
				return "让[npc2.name]给你口交。一旦[npc2.sheHasFull]开始，你就可以让[npc2.herHim]跪在下面或者在你动物化的后股前。";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) { // Biped body:
				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
						return "[npc.Name]想让[npc2.name]给[npc.herHim]口交，让[npc2.herHim]站到[npc.herHim]身前。"
								+ "[npc.Name]向下朝[npc2.herHim]咧嘴一笑，命令道，"
								+ "[npc.speech(不要停，用用你的嘴！)]";
					} else {
						return "[npc.Name]想让[npc2.name]给自己口交，便推倒了[npc2.herHim]，让[npc2.herHim]跪在身前。"
								+ "[npc.Name]向下朝[npc2.herHim]咧嘴一笑，命令道，"
								+ "[npc.speech(不要停，用用你的嘴！)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
						return "[npc.Name]想要[npc2.name]给自己吻肛，便调整姿势，让[npc2.herHim]站在身后。"
								+ "[npc.she]回头咧嘴一笑，命令道，"
								+ "[npc.speech(不要停，用用你的嘴！)]";
					} else {
						return "[npc.Name]想要[npc2.name]给自己吻肛，便推倒了[npc2.herHim]，让[npc2.herHim]跪在身前。"
								+ "[npc.she]回头咧嘴一笑，命令道，"
								+ "[npc.speech(不要停，用用你的嘴！)]";
					}
				}
				
			} else { // Taur body:
				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
						return "[npc.Name]想要[npc2.name]给自己口交，便推倒[npc2.herHim]，让[npc2.herHim]站在[npc.legRace]身体下。"
								+ "[npc.name]迈步向前，将[npc.herself]推向[npc2.herHim]，呼喊"
								+ "[npc.speech(不要停，用用你的嘴！)]";
					} else {
						return "[npc.Name]想要[npc2.name]给自己口交，于是推倒[npc2.herHim]，让[npc2.herHim]跪到[npc.legRace]身体下面。"
								+ "[npc.name]迈步向前，将[npc.herself]推向[npc2.herHim]，呼喊"
								+ "[npc.speech(不要停，用用你的嘴！)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
						return "[npc.Name]想要[npc2.name]给自己下半身口交，让便调整姿势，让[npc2.sheIs]站在身后。"
								+ "[npc.she]回头咧嘴一笑，命令道，"
								+ "[npc.speech(不要停，用用你的嘴！)]";
					} else {
						return "[npc.Name]推倒[npc2.herHim]，使之跪到[npc.her]的身下，意图让[npc2.name]给[npc.herHim]下半身口交。"
								+ "[npc.she]回头咧嘴一笑，命令道，"
								+ "[npc.speech(不要停，用用你的嘴！)]";
					}
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generateReceivingOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()), false);
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
			}
			return new ArrayList<>(fetishes);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).isFeral()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_ORAL_RECEIVING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()), true)
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT_TWO;
		}
		@Override
		public String getActionTitle() {
			return "站立接受口交(请求)";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "尝试让[npc2.name]给你口交。一旦[npc2.sheHasFull]接受，你可以要求[npc2.herHim]在你身体的前后切换。<br/>"
						+ getRequestTooltipText();
			} else {
				return "尝试让[npc2.name]给你口交。一旦[npc2.sheHasFull]接受，你就可以要求[npc2.herHim]跪在下面或者在你动物化的后股前。<br/>"
						+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) { // Biped body:
				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					return "[npc.Name]想让[npc2.name]给[npc.herHim]口交，站到[npc2.herHim]身前。"
							+ "[npc.she]凝视着[npc2.her][npc2.eyes+]，恳求道，"
							+ "[npc.speech(求你了，我想用用你的嘴！)]";
				} else {
					return "[npc.Name]想要[npc2.name]给[npc.herHim]吻肛，过到[npc2.herHim]身前。"
							+ "[npc.her]回头恳求着，"
							+ "[npc.speech(求你了，我想用用你的嘴！)]";
				}
				
			} else { // Taur body:
				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					return "[npc.Name]想要[npc2.name]给[npc.herHim]口交，来到[npc2.herHim]下半[npc.legRace]的身体附近。"
							+ "[npc.she]凝视着[npc2.her][npc2.eyes+]，恳求道，"
							+ "[npc.speech(求你了，我想用用你的嘴！)]";
				} else {
					return "[npc.name]想让[npc2.name]在[npc.legRace]的身下口交后股，[npc.name]挪了挪身子，朝[npc2.herHim]露出[npc.her]的臀腿。"
							+ "[npc.she]回头露出渴望的眼神，哀求道，"
							+ "[npc.speech(求你了，我想用用你的嘴！)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(generateReceivingOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()));
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			return POSITION_ORAL_RECEIVING.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes);
		}
	};
	
	public static final SexAction POSITION_ORAL_MOVE_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition()==SexPosition.STANDING
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.STANDING_DOMINANT_TWO
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.PERFORMING_ORAL_BEHIND
					&& (Main.sex.getCharacterInPosition(SexSlotStanding.STANDING_DOMINANT_TWO)!=null
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO)
					
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStanding.PERFORMING_ORAL_BEHIND);
		}
		@Override
		public String getActionTitle() {
			return "移动[npc2.herHim]到身后";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "让[npc2.name]动动身子到你背后，方便口交你的[npc.asshole]。";
			} else {
				return "让[npc2.name]动动身子到你背后，方便口交你的[npc.asshole]"+(Main.sex.getCharacterPerformingAction().hasVagina()?"和[npc.pussy]":"")+"。";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) { // Biped body:
				if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
					return "[npc.name]想要[npc2.name]给自己吻肛，便调整姿势，让[npc2.herHim]站在身后。"
							+ "[npc.she]把自己[npc.ass+]压在[npc2.her][npc2.face]脸上，命令道，"
							+ "[npc.speech(不要停，用用你的嘴！)]";
				} else {
					return "[npc.Name]想要[npc2.name]吻肛，于是调整了姿势，让[npc2.herHim]跪倒在自己身后。"
							+ "[npc.she]把自己[npc.ass+]压在[npc2.her][npc2.face]脸上，命令道，"
							+ "[npc.speech(不要停，用用你的嘴！)]";
				}
				
			} else { // Taur body:
				if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
					return "[npc.Name]想要[npc2.name]在[npc.legRace]身后给自己口交，便调整姿势，让[npc2.herHim]站在下面。"
							+ "[npc.she]把自己[npc.ass+]压在[npc2.her][npc2.face]脸上，命令道，"
							+ "[npc.speech(不要停，用用你的嘴！)]";
				} else {
					return "[npc.Name]想要让[npc2.name]给[npc.herHim]下半身口交，便调整姿势并将[npc2.herHim]推倒，让[npc2.herHim]跪在[npc.herHim]身下。"
							+ "[npc.she]把自己[npc.ass+]压在[npc2.her][npc2.face]脸上，命令道，"
							+ "[npc.speech(不要停，用用你的嘴！)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			if(Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_BEHIND)!=null) {
				Main.sex.swapSexPositionSlots(target, Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_BEHIND));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
			
			if(Main.sex.isDom(target)) {
				dominants.put(target, SexSlotStanding.PERFORMING_ORAL_BEHIND);
			} else {
				submissives.put(target, SexSlotStanding.PERFORMING_ORAL_BEHIND);
			}

			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.STANDING,
					dominants,
					submissives){
			});
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
				fetishes.add(Fetish.FETISH_ANAL_GIVING);
			}
			return new ArrayList<>(fetishes);
		}
	};
	
	public static final SexAction POSITION_ORAL_MOVE_IN_FRONT = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition()==SexPosition.STANDING
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.PERFORMING_ORAL
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.STANDING_DOMINANT_TWO
					&& (Main.sex.getCharacterInPosition(SexSlotStanding.STANDING_DOMINANT_TWO)!=null
							|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.PERFORMING_ORAL_TWO)
					
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStanding.PERFORMING_ORAL);
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "移动[npc2.herHim]到前面";
			} else {
				return "移动[npc2.herHim]到身下";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "让[npc2.name]动动身子到你前面，方便口交你的阴部。";
			} else {
				if(Main.sex.getCharacterPerformingAction().hasPenis()) {
					return "让[npc2.name]调整姿势，使之完全跪在你身下，好方便口你的[npc.cock]"+(Main.sex.getCharacterPerformingAction().hasBreastsCrotch()?"和[npc.crotchBoobs]":"")+"。";
				} else {
					return "让[npc2.name]调整姿势，使之完全跪在你身下"+(Main.sex.getCharacterPerformingAction().hasBreastsCrotch()?"，好方便口你的[npc.crotchBoobs]":"")+"。";
				}
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) { // Biped body:
				if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
					return "[npc.Name]想要[npc2.name]给自己口交，便调整[npc2.name]的位置，让[npc2.herHim]站到自己身前。"
							+ "[npc.Name]向下朝[npc2.herHim]咧嘴一笑，命令道，"
							+ "[npc.speech(不要停，用用你的嘴！)]";
				} else {
					return "[npc.Name]想要[npc2.name]口交，于是调整了姿势，让[npc2.herHim]跪倒在自己身前。"
							+ "[npc.Name]向下朝[npc2.herHim]咧嘴一笑，命令道，"
							+ "[npc.speech(不要停，用用你的嘴！)]";
				}
				
			} else { // Taur body:
				if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
					return "[npc.Name]想要[npc2.name]在[npc.legRace]身下给自己口交，便调整姿势，让[npc2.herHim]站在下面。"
							+ "[npc.name]迈步向前，将[npc.herself]推向[npc2.herHim]，呼喊道，"
							+ "[npc.speech(不要停，用用你的嘴！)]";
				} else {
					return "[npc.Name]想要[npc2.name]在[npc.legRace]身下给自己口交，便推倒[npc2.herHim]，让[npc2.herHim]跪在下面。"
							+ "[npc.name]迈步向前，将[npc.herself]推向[npc2.herHim]，呼喊道，"
							+ "[npc.speech(不要停，用用你的嘴！)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			if(Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL)!=null) {
				Main.sex.swapSexPositionSlots(target, Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
			
			if(Main.sex.isDom(target)) {
				dominants.put(target, SexSlotStanding.PERFORMING_ORAL);
			} else {
				submissives.put(target, SexSlotStanding.PERFORMING_ORAL);
			}

			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.STANDING,
					dominants,
					submissives){
			});
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
			}
			return new ArrayList<>(fetishes);
		}
	};
	
	public static final SexAction POSITION_ORAL_PERFORMING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)), false);
		}
		@Override
		public String getActionTitle() {
			return "提供口交";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "跪在[npc2.name]面前给[npc2.herHim]口交。一旦你开始，你就可以在[npc2.her]的前后之间切换。";
			} else {
				return "跪在[npc2.name]面前给[npc2.herHim]口交。一旦你开始，你就可以选择跪在下面或去到[npc2.her]动物化的后股前。";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
				if(generatePerformerOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "[npc.Name]想给[npc2.name]口交，便过来站到[npc2.herHim]身前。"
								+ "[npc.she]抬头对上[npc2.her][npc2.eyes+]，[npc.moansVerb]，"
								+ "[npc.speech(很棒哦，看看我的口技！)]";
					} else {
						return "[npc.Name]想给[npc2.name]口交，过来跪在了[npc2.herHim]身前。"
								+ "[npc.she]抬头对上[npc2.her][npc2.eyes+]，[npc.moansVerb]，"
								+ "[npc.speech(很棒哦，看看我的口技！)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "[npc.Name]想舔[npc2.name]的肛门，过来站在了[npc2.herHim]身后。"
								+ "[npc.she]抓住[npc2.her][npc2.hips+]，[npc.moansVerb]，"
								+ "[npc.speech(真好，我来尝尝你的小屁股！)]";
					} else {
						return "[npc.Name]想舔吻[npc2.name]的肛门，过来跪在了[npc2.herHim]背后。"
								+ "[npc.she]抓住[npc2.her][npc2.hips+]，[npc.moansVerb]，"
								+ "[npc.speech(真好，我来尝尝你的小屁股！)]";
					}
				}
				
			} else { // Taur body:
				if(generatePerformerOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "[npc.name]想给[npc2.name]口交，过来站在了[npc2.legRace]后半身体的下面。"
								+ "[npc.she]举起后[npc2.legs]的其中一只[npc.a_hand]，[npc.moansVerb]，"
								+ "[npc.speech(啊~太棒了，我是时候动动嘴了！)]";
					} else {
						return "[npc.name]想给[npc2.name]口交，过来跪在了[npc2.legRace]后半身体的下面。"
								+ "[npc.she]举起后[npc2.legs]的其中一只[npc.a_hand]，[npc.moansVerb]，"
								+ "[npc.speech(啊~太棒了，我是时候动动嘴了！)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "[npc.Name]想要给[npc2.namePos]那[npc2.legRace]的后半身下部口交，[npc.sheIs]转到了[npc2.herHim]的身后并站好。"
								+ "把[npc.a_hand]举过[npc2.her][npc2.ass+]，[npc.she][npc.moansVerb]，"
								+ "[npc.speech(啊~太棒了，我是时候动动嘴了！)]";
					} else {
						return "[npc.Name]想要给[npc2.namePos]那[npc2.legRace]的后半身下部口交，[npc.sheIs]转到了[npc2.herHim]的身后跪了下来。"
								+ "把[npc.a_hand]举过[npc2.her][npc2.ass+]，[npc.she][npc.moansVerb]，"
								+ "[npc.speech(啊~太棒了，我是时候动动嘴了！)]";
					}
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generatePerformingOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)), false);
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
			}
			return new ArrayList<>(fetishes);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).isFeral()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_ORAL_PERFORMING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)), true)
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_TWO
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_BEHIND
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO;
		}
		@Override
		public String getActionTitle() {
			return "提供口交(请求)";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "冷静下来并试着说服[npc2.name]同意你为[npc2.herHim]提供口交。如果[npc2.she]同意，你就能进一步要求在[npc2.her]的身前与身后切换。<br/>"
						+ getRequestTooltipText();
			} else {
				return "跪下努力说服[npc2.name]，让你给[npc2.herHim]口交。如果[npc2.she]接受了，你就可以更进一步，跪在下面或者去到[npc2.her]动物化的后股前。<br/>"
						+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
				if(generatePerformerOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "[npc.Name]想给[npc2.name]口交，便站到[npc2.herHim]身前。"
								+ "[npc.she]仰视着[npc2.her][npc2.eyes+]，恳求道，"
								+ "[npc.speech(来嘛，试试我的口技！)]";
					} else {
						return "[npc.Name]想给[npc2.name]口交，跪在了[npc2.herHim]身前。"
								+ "[npc.she]仰视着[npc2.her][npc2.eyes+]，恳求道，"
								+ "[npc.speech(来嘛，试试我的口技！)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "[npc.Name]想要给[npc2.name]吻肛，过到[npc2.herHim]身后。"
								+ "把住[npc2.her][npc2.hips+]，恳求道，"
								+ "[npc.speech(来嘛，试试我的口技！)]";
					} else {
						return "[npc.Name]想给[npc2.name]口交，便跪在了[npc2.herHim]身前。"
								+ "把住[npc2.her][npc2.hips+]，恳求道，"
								+ "[npc.speech(来嘛，试试我的口技！)]";
					}
				}
				
			} else { // Taur body:
				if(generatePerformerOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "[npc.name]想给[npc2.name]口交，便站在了[npc2.legRace]后半身体的下面。"
								+ "[npc.she]举起后[npc2.legs]的其中一只[npc.a_hand]，恳求道，"
								+ "[npc.speech(来嘛，试试我的口技！)]";
					} else {
						return "想要为[npc2.name]口交，于是[npc.name]跪在了[npc2.her]的下方。"
								+ "[npc.she]举起后[npc2.legs]的其中一只[npc.a_hand]，恳求道，"
								+ "[npc.speech(来嘛，试试我的口技！)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "想要为[npc2.namePos][npc2.legRace]下半身的后面进行口交，于是[npc.name]让自己站在了[npc2.herHim]的后面。"
								+ "把住[npc2.her][npc2.hips+]，恳求道，"
								+ "[npc.speech(来嘛，试试我的口技！)]";
					} else {
						return "想要为[npc2.namePos]的[npc2.legRace]下半身进行口交，于是[npc.name]跪在[npc2.herHim]的后面。"
								+ "在把住[npc2.her]那[npc2.hips+]后，[npc.she]恳求道，"
								+ "[npc.speech(来嘛，试试我的口技！)]";
					}
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(generatePerformingOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)));
//			System.out.println(Main.sex.getPositionRequest().getPosition().getName()+", "+Main.sex.getPositionRequest().getPerformerSlots().get(0).getName(null)+", "+Main.sex.getPositionRequest().getPartnerSlots().get(0).getName(null));
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			return POSITION_ORAL_PERFORMING.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes);
		}
	};
	
//	public static final SexAction POSITION_ORAL_PERFORMING_BEHIND = new SexAction(
//			SexActionType.POSITIONING,
//			ArousalIncrease.ONE_MINIMUM,
//			ArousalIncrease.ONE_MINIMUM,
//			CorruptionLevel.ONE_VANILLA,
//			null,
//			SexParticipantType.NORMAL) {
//		
//		@Override
//		public boolean isBaseRequirementsMet() {
//			return checkBaseRequirements(generatePerformingOralData(Main.sex.getCharacterTargetedForSexAction(this)), false);
//		}
//		@Override
//		public String getActionTitle() {
//			return "Perform oral (behind)";
//		}
//		@Override
//		public String getActionDescription() {
//			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
//				return "Get down behind [npc2.name], so that you're able to perform oral on [npc2.her] [npc2.asshole].";
//			} else {
//				return "Get down behind [npc2.name], so that you're able to perform oral on [npc2.her] [npc2.asshole]"+(Main.sex.getCharacterTargetedForSexAction(this).hasVagina()?" and [npc2.pussy]":"")+".";
//			}
//		}
//		@Override
//		public String getDescription() {
//			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
//				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
//					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
//						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing before [npc2.herHim]."
//								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(That's right, let me put my mouth to use!)]";
//					} else {
//						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down before [npc2.herHim]."
//								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(That's right, let me put my mouth to use!)]";
//					}
//				} else {
//					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
//						return "Wanting to perform anilingus on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing behind [npc2.herHim]."
//								+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, let me eat your ass!)]";
//					} else {
//						return "Wanting [npc2.name] to perform anilingus, [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down behind [npc2.herHim]."
//								+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, let me eat your ass!)]";
//					}
//				}
//				
//			} else { // Taur body:
//				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
//					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
//						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing beneath [npc2.her] lower [npc2.legRace]'s body."
//								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
//					} else {
//						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down beneath [npc2.her] lower [npc2.legRace]'s body."
//								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
//					}
//				} else {
//					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
//						return "Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing behind [npc2.herHim]."
//								+ " Running [npc.a_hand] up and over [npc2.her] [npc2.ass+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
//					} else {
//						return "Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down behind [npc2.herHim]."
//								+ " Running [npc.a_hand] up and over [npc2.her] [npc2.ass+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
//					}
//				}
//			}
//		}
//		@Override
//		public void applyEffects() {
//			setNewSexManager(generatePerformingOralData(Main.sex.getCharacterTargetedForSexAction(this)), false);
//		}
//		@Override
//		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
//			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
//			if(characterPerformingActionFetishes) {
//				fetishes.add(Fetish.FETISH_ORAL_GIVING);
//			} else {
//				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
//			}
//			return new ArrayList<>(fetishes);
//		}
//	};
	
	public static final SexAction POSITION_PERFORMING_ORAL_MOVE_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition()==SexPosition.STANDING
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT_TWO
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_BEHIND
					&& (Main.sex.getCharacterInPosition(SexSlotStanding.STANDING_DOMINANT_TWO)!=null
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO)
					
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getCharacterPerformingAction(), SexSlotStanding.PERFORMING_ORAL_BEHIND);
		}
		@Override
		public String getActionTitle() {
			return "移动到[npc2.herHim]身后";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				return "调整姿势，到[npc2.name]身后，方便你口交[npc2.her]的[npc2.asshole]。";
			} else {
				return "调整姿势，到[npc2.name]身后，方便你口交[npc2.her]的[npc2.asshole]"+(Main.sex.getCharacterTargetedForSexAction(this).hasVagina()?"和[npc2.pussy]":"")+"。";
			}
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
				if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
					sb.append("[npc.Name]想要给[npc2.name]吻肛，过到[npc2.herHim]身后。"
							+ "在把住[npc2.her]那[npc2.hips+]后，[npc.she]恳求道，");
				} else {
					sb.append("(想要肛交[npc2.name]，于是[npc.name]调整他自己的位置并跪在[npc2.herHim]的后面。"
							+ "在把住[npc2.her]那[npc2.hips+]后，[npc.she]恳求道，");
				}
				
			} else { // Taur body:
				if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
					sb.append("想要为[npc2.namePos][npc2.legRace]下半身进行口交，于是[npc.name]调整自己的位置并让自己重新站在了[npc2.herHim]的后面。"
							+ "[npc.name]用[npc.a_hand]摸向[npc2.her][npc2.ass+]，恳求道，");
				} else {
					sb.append("想要为[npc2.namePos][npc2.legRace]下半身进行口交，于是[npc.name]调整自己的位置并重新跪在了[npc2.herHim]的后面。"
							+ "[npc.name]用[npc.a_hand]摸向[npc2.her][npc2.ass+]，恳求道，");
				}
			}
			if(Main.sex.isDom(Main.sex.getCharacterPerformingAction())) {
				sb.append("[npc.speech(现在来尝尝你的屁股！)]");
			} else {
				sb.append("[npc.speech(来嘛，试试我的口技！)]");
			}
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterPerformingAction();
			if(Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_BEHIND)!=null) {
				Main.sex.swapSexPositionSlots(target, Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_BEHIND));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
			
			if(Main.sex.isDom(target)) {
				dominants.put(target, SexSlotStanding.PERFORMING_ORAL_BEHIND);
			} else {
				submissives.put(target, SexSlotStanding.PERFORMING_ORAL_BEHIND);
			}

			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.STANDING,
					dominants,
					submissives){
			});
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
				fetishes.add(Fetish.FETISH_ANAL_GIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
			}
			return new ArrayList<>(fetishes);
		}
	};
	
	public static final SexAction POSITION_PERFORMING_ORAL_MOVE_IN_FRONT = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition()==SexPosition.STANDING
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT_TWO
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL
					&& (Main.sex.getCharacterInPosition(SexSlotStanding.STANDING_DOMINANT_TWO)!=null
							|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_TWO)
					
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getCharacterPerformingAction(), SexSlotStanding.PERFORMING_ORAL);
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				return "挪到前面";
			} else {
				return "移动到[npc2.herHim]身下";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				return "移动到[npc2.name]前面，方便口交[npc2.her]的阴部。";
			} else {
				if(Main.sex.getCharacterTargetedForSexAction(this).hasPenis()) {
					return "跪在[npc2.namePos][npc2.legRaceFeral]躯干前，为[npc2.her]口交"
								+(Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()?"和[npc2.crotchBoobs]":"")+"。";
				} else {
					return "跪在[npc2.namePos][npc2.legRaceFeral]躯干前"
								+(Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()?"这样你就可以给[npc2.her]的[npc2.crotchBoobs]口交了":"")+"。";
				}
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
				if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
					return "[npc.Name]想给[npc2.name]口交，便重新站到[npc2.herHim]身前。"
							+ "[npc.she]向上凝视着[npc2.her][npc2.eyes+]，恳求道，"
							+ "[npc.speech(来嘛，试试我的口技！)]";
				} else {
					return "[npc.Name]想要给[npc2.name]口交，于是调整位置并跪在[npc2.herHim]的前面。"
							+ "[npc.she]向上凝视着[npc2.her][npc2.eyes+]，恳求道，"
							+ "[npc.speech(来嘛，试试我的口技！)]";
				}
				
			} else { // Taur body:
				if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
					return "[npc.name]想在[npc2.namePos][npc2.legRace]的后半身下方进行口交，于是调整体位到[npc2.herHim]的身下。"
							+ "[npc.she]将[npc.a_hand]的其中一只后[npc2.legs]上，恳求道，"
							+ "[npc.speech(来嘛，试试我的口技！)]";
				} else {
					return "[npc.name]想为[npc2.namePos]口交，于是调整体位俯身跪在[npc2.herHim][npc2.legRace]躯干前。"
							+ "[npc.she]将[npc.a_hand]的其中一只后[npc2.legs]上，恳求道，"
							+ "[npc.speech(来嘛，试试我的口技！)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterPerformingAction();
			if(Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL)!=null) {
				Main.sex.swapSexPositionSlots(target, Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
			
			if(Main.sex.isDom(target)) {
				dominants.put(target, SexSlotStanding.PERFORMING_ORAL);
			} else {
				submissives.put(target, SexSlotStanding.PERFORMING_ORAL);
			}

			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.STANDING,
					dominants,
					submissives){
			});
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
			}
			return new ArrayList<>(fetishes);
		}
	};
	

	
	
	//--------------- AGAINST WALL ---------------//

	public static final SexAction POSITION_FACE_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "面向[pc.wall]";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]面朝附近的[pc.wall]。";
		}
		@Override
		public String getDescription() {
			return "[npc.name]抓住[npc2.namePos]肩膀，把[npc2.herHim]推到附近的[pc.wall]上。"
					+ "随后用整个身子压在[npc2.her]的背上，在[npc2.ear]边[npc.moansVerb]着，"
					+ "[npc.speech(真乖！操你的时候别乱动！)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_FACE_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "面对[pc.wall](请求)";
		}
		@Override
		public String getActionDescription() {
			return "尝试调整姿势，让自己面对附近的[pc.wall]。<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "[npc2.name]还没来得及反应，你就面对旁边的[pc.wall]站着了。"
					+ "你按住面前的墙面，把[npc.ass+]向后一探，对着[npc2.name]晃动起来，希望[npc2.herHim]能直接这样上了你。";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_BACK_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.BACK_TO_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "摁在[pc.wall]上";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]背对附近的[pc.wall]。";
		}
		@Override
		public String getDescription() {
			return "[npc.name]抓住[npc2.namePos]肩膀，把[npc2.herHim]推到附近的[pc.wall]上。"
					+ "随后用整个身子压了过去，在[npc2.ear]边[npc.moansVerb]着，"
					+ "[npc.speech(乖乖的，在我操你的时候不要乱动！)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_BACK_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.BACK_TO_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "摁在[pc.wall]上(请求)";
		}
		@Override
		public String getActionDescription() {
			return "尝试调整姿势，让自己背对附近的[pc.wall]。<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "[npc2.name]还没来得及反应，你就面对旁边的[pc.wall]站着了。"
					+ "你背靠着墙面，向[npc2.name]投出一阵格外撩人的眼神，希望[npc2.herHim]能直接这样上了你。";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	
	
	
	
	//--------------- LYING DOWN ---------------//
	
	public static final SexAction POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "传教士体位";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]仰面躺下，跪在[npc2.her]的[npc2.legs]之间，准备以传教士体位开始性爱。";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return "[npc.name]小心翼翼地移动着[npc2.name]，以免弄醒[npc.her]，"
						+ (Main.sex.getCharacterTargetedForSexAction(this).hasLegs()
								?"将自己夹在了[npc2.her]的[npc2.legs]之间。"
								:"跪在[npc2.her]下体的上方。")
						+ "[npc.she]脸上挂着淫荡的笑容，轻声耳语道，"
						+ "<i>[npc.speech(没错，继续沉睡吧，我要操你了……)]</i>";
			}
			
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "[npc.name]抓住[npc2.namePos]的肩膀，将[npc2.herHim]推倒。"
						+"倾身于[npc2.her][npc2.legs]间，[npc.she]低头对上[npc2.her][npc2.eyes+]，[npc.moansVerb]着，"
						+ "[npc.speech(这就对啦，给我张开你的腿……)]";
			} else {
				return "[npc.name]抓住[npc2.namePos]的肩膀，将[npc2.herHim]推倒。"
						+"跪在于[npc2.her]腹股沟上，[npc.she]低头对上[npc2.her][npc2.eyes+]，[npc.moansVerb]着，"
						+ "[npc.speech(这就对啦，让我好好看看你……)]";
			}
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "传教士体位(请求)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "让[npc2.name]仰面躺下，伸开[npc2.legs]，用传教士体位和[npc2.herHim]做爱。<br/>"
							+ getRequestTooltipText();
			} else {
				return "让[npc2.name]仰面躺下，露出下体，用传教士体位和[npc2.herHim]做爱。<br/>"
							+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			return "你抓住[npc2.namePos]的肩膀，将[npc2.herHim]推倒，试着让[npc2.herHim]仰面躺下。";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_PRONE_BONE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_FRONT));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "俯卧位";
		}
		@Override
		public String getActionDescription() {
			return "将[npc2.name]面朝下按倒，跪在[npc2.her]的[npc2.legs]之间，准备以俯卧位跟[npc2.herHim]做爱。";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				boolean rollsOver = !Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT);
				return "[npc.name]小心翼翼地移动着[npc2.name]，以免弄醒[npc.her]，"
						+(rollsOver
								?"慢慢将[npc2.herHim]翻过身去，让[npc2.her]肚子朝下"
								:"")
						+ (Main.sex.getCharacterTargetedForSexAction(this).hasLegs()
								?"将自己夹在了[npc2.her]的[npc2.legs]之间。"
								:"抬起[npc2.her]的[npc2.legs]，接触到了[npc2.her]的下体。")
						+ "[npc.she]脸上挂着淫荡的笑容，轻声耳语道，"
						+ "<i>[npc.speech(别醒过来……)]</i>";
			}
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "[npc.name]抓住[npc2.namePos]的肩膀，将[npc2.herHim]推倒使其俯卧在地。"
						+"[npc.she]跪伏在[npc2.her]的[npc2.legs]之间，一边[npc.moansVerb]着一边准备进入[npc2.herHim]，"
						+ "[npc.speech(乖乖躺着享受吧……)]";
			} else {
				return "[npc.name]抓住[npc2.namePos]的肩膀，将[npc2.herHim]推倒使其俯卧在地。"
						+ "抬起[npc2.her]的[npc2.legs]，以便能接触到[npc2.her]的下体，同时[npc.moansVerb]道，"
						+ "[npc.speech(乖乖躺着享受吧……)]";
			}
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_PRONE_BONE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_FRONT));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "俯卧位(请求)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "尝试让[npc2.name]俯卧，分开[npc2.legs]，以便你能以俯卧位与[npc2.herHim]做爱。<br/>"
							+ getRequestTooltipText();
			} else {
				return "让[npc2.name]俯卧躺下，露出下体，这样你就可以用俯卧位和[npc2.herHim]做爱了。<br/>"
							+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			return "你伸手抓住[npc2.namePos]的肩膀，用力下压，试图让[npc2.herHim]俯卧下来。";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_MISSIONARY_ON_BACK = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "传教士体位(被动)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "你仰面躺下，张开[npc.legs]，准备以传教士体位与[npc2.name]开始性爱。";
			} else {
				return "你仰面躺下，露出下体，准备以传教士体位与[npc2.name]开始性爱。";
			}
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "[npc.name]抓住[npc2.namePos]的肩膀，让[npc2.herHim]跪倒在地。"
						+"[npc.name]跪在[npc2.herHim]身前，然后仰身躺下，伸开腿看着[npc2.namePos][npc2.eyes+]眼睛，[npc.moanVerb]着，"
						+ "[npc.speech(来上我啊！)]";
			} else {
				return "[npc.name]抓住[npc2.namePos]的肩膀，让[npc2.herHim]跪倒在地。"
						+"[npc.name]跪在[npc2.herHim]身前，然后仰身躺下，露出下体看着[npc2.namePos][npc2.eyes+]眼睛，[npc.moanVerb]着，"
						+ "[npc.speech(来上我啊！)]";
			}
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isBeingPenetrated()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_MISSIONARY_ON_BACK = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "传教士体位(被动)(请求)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "你仰面躺下，张开[npc.legs]，鼓励[npc2.name]以传教士体位与你开始性爱。<br/>"
							+ getRequestTooltipText();
			} else {
				return "你仰面躺下，露出下体，鼓励[npc2.name]以传教士体位与你开始性爱。<br/>"
							+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "[npc.Name]在[npc2.name]身前仰面躺下，"
						+ "伸开腿，轻声用[npc.moan]鼓励[npc2.name]用传教士体位做爱。";
			} else {
				return "[npc.Name]在[npc2.name]身前仰面躺下，"
						+ "露出下体，轻声用[npc.moan]鼓励[npc2.name]用传教士体位做爱。";
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_PRONE_BONE_ON_FRONT = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_FRONT),
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY));
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "俯卧位(在前)";
		}
		@Override
		public String getActionDescription() {
			return "俯卧在地，把你[npc.ass+]和下体对着[npc2.name]，让[npc2.she]能以俯卧姿态从背后插入。";
		}
		@Override
		public String getDescription() {
			return "[npc.name]抓住[npc2.namePos]肩膀，将[npc2.herHim]按着跪倒在地。"
					+"[npc.name]俯身趴在[npc2.name]面前，翘起[npc.ass+]露出下体，[npc.moanVerb]着，"
					+ "[npc.speech(来上我啊！)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isBeingPenetrated()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_PRONE_BONE_ON_FRONT = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_FRONT),
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY));
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "俯卧位(在前)(请求)";
		}
		@Override
		public String getActionDescription() {
			return "俯卧在地，翘起你[npc.ass+]，露出下体，引诱[npc2.name]用俯卧后入位和你做爱。<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "[npc.Name]在[npc2.name]身前俯身趴下，"
					+ "翘起[npc.ass+]，露出下体，轻声用[npc.moan]激励着[npc2.name]用俯卧位做爱。";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SIXTY_NINE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.SIXTY_NINE),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "六九式(在上)";
		}
		@Override
		public String getActionDescription() {
			return "推倒[npc2.name]并骑在[npc2.her]的脸上，呈六九式。";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				boolean rollsOver = Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT);
				return "[npc.name]小心翼翼地移动着[npc2.name]，以免弄醒[npc.her]，"
						+(rollsOver
								?"慢慢将[npc2.herHim]翻过身去，让[npc2.her]背部朝上，随后"
								:"")
						+ "俯身贴近形成69体位。"
						+ "[npc.she]饥渴地舔了舔[npc.lips+]，轻声地耳语道，"
						+ "<i>[npc.speech(让我给你一些带来一场美梦吧……)]</i>";
			}
			return "[npc.name]抓住[npc2.namePos]的肩膀，将[npc2.herHim]推倒。"
					+ "[npc.her]快速放倒[npc.herself]，四肢着地在[npc2.herHim]上面，"
						+ "[npc.she]将胯部躺到[npc2.her]的脸上，并同样伏到[npc2.her]的腹股沟处。"
					+ "回头看看下面的[npc.herHim]，[npc.name][npc.moansVerb]，"
					+ "[npc.speech(好[npc2.girl]！来爽一爽吧！)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_SIXTY_NINE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.SIXTY_NINE),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "六九式(在上)(请求)";
		}
		@Override
		public String getActionDescription() {
			return "尝试推倒[npc2.name]并骑在[npc2.her]的脸上，以便你能和[npc2.herHim]六九。<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "你抓住[npc2.namePos]的肩膀，用极具魅惑力的眼神请求[npc2.herHim]仰面躺下，"
					+ "[npc.speech(我想和你做六九式……求你了！)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SIXTY_NINE_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.SIXTY_NINE));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false)
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
						|| !Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive());
		}
		@Override
		public String getActionTitle() {
			return "六九式(在下)";
		}
		@Override
		public String getActionDescription() {
			return "仰面躺下，让[npc2.name]骑在你的脸上，呈六九式。";
		}
		@Override
		public String getDescription() {
			return "在[npc.name]仰面躺下时，[npc.herHim]抓住[npc2.namePos]的肩膀，将[npc2.herHim]拽倒并使其趴在[npc.herHim]的身上。"
					+"[npc.Name]抓着[npc2.hips]把[npc2.name][npc2.hips]拉倒，让[npc2.sheIs]四肢着地地趴在你身上。看着近在脸前的胯部，[npc.Name][npc.moansVerb]着，"
					+ "[npc.speech(好[npc2.girl]！来享受口交吧！)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_SIXTY_NINE_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.SIXTY_NINE));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "六九式(在下)(请求)";
		}
		@Override
		public String getActionDescription() {
			return "仰面躺下，让[npc2.name]与你呈六九式。<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "你低身仰躺，尽可能展现出你的魅力请求，"
					+ "[npc.speech(我想和你玩六九式……求你了！)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_COW_GIRL_RIDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "骑乘位(在上)";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]仰面躺下，骑在[npc2.her]的下体上，呈骑乘位。";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				boolean rollsOver = Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT);
				return "[npc.name]小心翼翼地移动着[npc2.name]，以免弄醒[npc.her]，"
						+(rollsOver
								?"慢慢将[npc2.herHim]翻过身去，让[npc2.her]背部朝上，随后"
								:"")
						+ "以骑乘位姿势跨了上去。"
						+"[npc.name]调整好姿势，俯视着[npc2.name]咧嘴一笑，轻声耳语道，"
						+ "<i>[npc.speech(乖乖睡着让我骑上来……)]</i>";
			}
			return "[npc.name]抓住[npc2.namePos]的肩膀，将[npc2.herHim]推倒。"
					+"接着，[npc.name]保持上位俯身，用骑乘式跨坐在[npc2.name]身上，把胯部向下顶。"
					+"调整好位置，[npc.name]咧嘴一笑，看着[npc2.name]，[npc.moansVerb]着，"
					+ "[npc.speech(好[npc2.girl]！该给你骑一下了！)]"; 
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isBeingPenetrated()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};

	public static final SexAction REQUEST_POSITION_COWGIRL_RIDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "骑乘位(在上)(请求)";
		}
		@Override
		public String getActionDescription() {
			return "尝试推倒[npc2.name]，以便你能以骑乘位坐在[npc2.herHim]身上。<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "你抓住[npc2.namePos]肩膀，稍稍用力，请求[npc2.herHim]仰面躺下，"
					+ "[npc.speech(让我骑骑你……好不好嘛！)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};

	public static final SexAction POSITION_COW_GIRL_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "骑乘位(在下)";
		}
		@Override
		public String getActionDescription() {
			return "仰面躺下，让[npc2.name]骑在你的下体上，呈骑乘位。";
		}
		@Override
		public String getDescription() {
			return "[npc.Name]抓住[npc2.namePos]肩膀，仰面向上，拉着[npc2.herHim]一起躺下。"
					+"[npc.name]紧紧抓住[npc2.namePos]的[npc2.hips]，让[npc2.sheIs]用骑乘位跨坐在自己身上。"
					+"调整好位置，[npc.name]咧嘴一笑，看着[npc2.name]，[npc.moansVerb]着，"
					+"[npc.speech(好[npc2.girl]！该骑上来了！)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};

	public static final SexAction REQUEST_POSITION_COWGIRL_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "骑乘位(在下)(请求)";
		}
		@Override
		public String getActionDescription() {
			return "仰面躺下，让[npc2.name]以骑乘位骑在你身上。<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "你仰面躺下，尽可能展现你的魅力，恳求着，"
					+ "[npc.speech(来骑我吧……求你了！)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SITTING_ON_FACE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "颜面骑乘";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]仰面躺下，坐在[npc2.her]的脸上。";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				boolean rollsOver = Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT);
				return "[npc.name]小心翼翼地移动着[npc2.name]，以免弄醒[npc.her]，"
						+(rollsOver
								?"慢慢将[npc2.herHim]翻过身去，让[npc2.her]背部朝上，随后"
								:"")
						+ "将身体缓缓沉下，压在了[npc2.her]的面庞上。"
						+ "[npc.name]缓缓分开[npc.legs]，将下体紧紧压在[npc2.namePos]的嘴上。"
						+ "[npc.she]脸上挂着灿烂的笑容，轻声低语道，"
						+ "<i>[npc.speech(你睡得可真沉……现在我要骑到你脸上咯……)]</i>";
			}
			return "[npc.name]抓住[npc2.namePos]的肩膀，将[npc2.herHim]推倒。"
					+"[npc.name]保持上位俯身，把小腹靠近[npc2.her]的脸。"
					+"[npc.name]调整好位置，配合着分开[npc.legs]，把下体对准[npc2.namePos]的嘴。";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};

	public static final SexAction REQUEST_POSITION_SITTING_ON_FACE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "颜面骑乘(请求)";
		}
		@Override
		public String getActionDescription() {
			return "你把[npc2.name]仰面推倒，顺势骑在[npc2.her]的脸上。<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "你近身抓住[npc2.namePos]的肩膀，然后把他仰面推倒，请求道，"
					+ "[npc.speech(求你了，让我坐在你脸上吧！)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_FACESITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "被颜面骑乘";
		}
		@Override
		public String getActionDescription() {
			return "仰面躺下，让[npc2.name]坐在你的脸上。";
		}
		@Override
		public String getDescription() {
			return "[npc.Name]抓住[npc2.namePos]的[npc2.arms]，拉着他对着自己躺下。"
					+"[npc.name]贴身抓住[npc2.name]，把[npc2.her]的小腹拉到自己脸前。"
					+"[npc2.namePos]接着张开腿，使自己的小腹正对[npc.namePos]的嘴。";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};

	public static final SexAction REQUEST_POSITION_FACESITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "被颜面骑乘(请求)";
		}
		@Override
		public String getActionDescription() {
			return "试着仰面躺下，让[npc2.name]能坐在你的脸上。<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "你抓住[npc2.namePos]的[npc2.hands]，躺下请求道，"
					+ "[npc.speech(求你了，坐在我脸上吧！)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};

	
	public static final SexAction POSITION_MATING_PRESS = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.MATING_PRESS),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasPenis()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant()
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isPositive()) {
				return SexActionPriority.HIGH;
			} else {
				return SexActionPriority.NORMAL;
			}
		}
		@Override
		public String getActionTitle() {
			return "授种式";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "让[npc2.name]仰面躺下，把[npc2.her]的[npc2.legs]分开，压向脑袋的方向，随后俯下身体压在[npc2.herHim]身上，呈“授种式”体位。";
			} else {
				return "让[npc2.name]仰面躺下，随后俯下身体压在[npc2.herHim]身上，呈“授种式”体位。";
			}
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "[npc.Name]抓住[npc2.namePos]肩膀，顺势正面把他推倒在地。"
						+ "[npc.Name]分开[npc2.her]的[npc2.legs]，靠了过去，然后俯身拉进彼此的小腹的距离。"
						+"[npc.Name]抓住[npc2.namePos]的手腕，把他们固定在[npc2.her]头部两侧，小口喘气，"
						+ "[npc.speech(是时候"
						+ UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this),
								"#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN狠狠地操你一顿了！#ELSE给你配种了！#ENDIF")
						+ ")]";
			} else {
				return "[npc.Name]抓住[npc2.namePos]肩膀，顺势正面把他推倒在地。"
						+ "[npc.name]迅速保持上位压在[npc2.namePos]身上，把[npc2.her]双手压在头部两侧，把自己的下身逼向[npc2.her]的，[npc.moaning]着，"
						+ "[npc.speech(是时候"
						+ UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this),
								"#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN狠狠地操你一顿了！#ELSE给你配种了！#ENDIF")
						+ ")]";
			}
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_MATING_PRESS = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.MATING_PRESS));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "授种式(请求)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "你正面躺下，把两腿分开掰到头部两侧，以授种式展示自己的私处，等待对方骑上来操你，<br/>"
							+ getRequestTooltipText();
			} else {
				return "你正面躺下，以授种式展示自己的私处，等待对方骑上来操你，<br/>"
							+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "[npc2.name]还没来得及反应，你就仰面躺下，然后把[npc.legs]分开，压向脑袋的方向。"
						+"你晃动下体，[npc.moansVerb]道，"
						+ "[npc.speech(压上来"
						+ UtilText.parse(Main.sex.getCharacterPerformingAction(), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN操我！#ELSE给我配种！#ENDIF")
						+ ")]";
			} else {
				return "你不等[npc2.name]有所反应，直接躺在[npc2.her]面前，露出腹部[npc.moaning]道，"
						+ "[npc.speech(压上来"
						+ UtilText.parse(Main.sex.getCharacterPerformingAction(), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN操我！#ELSE给我配种！#ENDIF")
						+ ")]";
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	
	
	//--------------- ALL FOURS ---------------//
	
	private static List<SexSlot> generatePerformerAllFoursData(GameCharacter receiver) {
		List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		doms.remove(receiver);
		List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		subs.remove(receiver);

		boolean doubleReceiving = false;
		if(Main.sex.isDom(receiver)) {
			doubleReceiving = doms.size()>=1;
		} else {
			doubleReceiving = subs.size()>=1;
		}
		List<SexSlot> performerSlots = new ArrayList<>();
		performerSlots.add(SexSlotAllFours.BEHIND);
		if(doubleReceiving) {
			performerSlots.add(SexSlotAllFours.BEHIND_TWO);
		}
		SexSlot[] slots = new SexSlot[] {
				SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
				SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.IN_FRONT_TWO};
		for(SexSlot slot : slots) {
			if(!performerSlots.contains(slot)) {
				performerSlots.add(slot);
			}
		}
		
		return performerSlots;
	}
	
	private static PositioningData generateReceivingAllFoursData(GameCharacter receiver) {
		return new PositioningData(
				SexPosition.ALL_FOURS,
				Util.newArrayListOfValues(
						SexSlotAllFours.ALL_FOURS,
						SexSlotAllFours.ALL_FOURS_TWO),
				generatePerformerAllFoursData(receiver));
	}

	private static PositioningData generatePerformingAllFoursData(GameCharacter receiver) {
		return new PositioningData(
				SexPosition.ALL_FOURS,
				generatePerformerAllFoursData(receiver),
				Util.newArrayListOfValues(
						SexSlotAllFours.ALL_FOURS,
						SexSlotAllFours.ALL_FOURS_TWO));
	}
	
	private static boolean isAllFoursAvailable(GameCharacter gettingFucked) {
		switch(gettingFucked.getLegConfiguration()) {
			case ARACHNID:
			case TAIL:
			case TAIL_LONG:
				return false;
			case BIPEDAL:
			case CEPHALOPOD:
			case QUADRUPEDAL:
			case AVIAN:
			case WINGED_BIPED:
				return true;
		}
		return true;
	}
	
	public static final SexAction POSITION_ALL_FOURS_GETTING_FUCKED = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingAllFoursData(Main.sex.getCharacterPerformingAction()), false)
					&& isAllFoursAvailable(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "被后入";
				} else {
					return "开始被操";
				}
			} else {
				return "开始被骑乘";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "四肢跪地，以便[npc2.name]用后入式体位操你。";
				} else {
					return "四肢跪地，以便[npc2.name]骑上来跟你交配。";
				}
			} else {
				return "把你的臀部和后腿朝向[npc2.name]，以便[npc2.herHim]骑上你动物化的后股。";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "[npc.name]在[npc2.name]前四肢着地，想被[npc2.herHim]以后入式操干。"
							+"[npc.name]回头，把自己[npc.ass+]凑向[npc2.namePos]小腹，[npc.moansVerb]着，"
							+ "[npc.speech(来吧，像动物一样操我！)]";
				} else {
					return "[npc.name]四肢着地趴在[npc2.name]面前，挑动着[npc.herHim]的情欲，勾引着[npc2.herHim]骑上来像动物发情一样操[npc.her]。"
							+ "[npc.she]像动物一样四肢着地后退，抬起[npc.hips+]凑向[npc2.namePos]野性的躯体，[npc.moansVerb]着，"
							+ "[npc.speech(来吧，骑上我吧！)]";
				}
				
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "[npc.Name]转身，露出自己[npc.legRace]态的后半身，示意[npc2.name]狠狠地操进来。"
							+"[npc.namePos]转头退步，把[npc2.namePos]的小腹拉到自己[npc.ass+]前，[npc.moansVerb]道，"
							+ "[npc.speech(来吧，像动物一样操我！)]";
				} else {
					return "[npc.Name]露出自己[npc.legRace]态的后半身，挑动着[npc.Name]的兽性，暗示[npc.her]骑在上来像发情的动物一样操[npc.her]。"
							+"[npc.name]往后退抬起屁股，强行让[npc2.name]骑上来。"
							+"[npc.she]回头[npc.moansVerb]道，"
							+"[npc.speech(就是这样！像动物一样操我！)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generateReceivingAllFoursData(Main.sex.getCharacterPerformingAction()), false);
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				if(characterPerformingAction.hasVagina()) {
					fetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
				}
				fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				fetishes.add(Fetish.FETISH_SUBMISSIVE);
				if(Main.sex.getTargetedPartner(characterPerformingAction).hasPenis()) {
					fetishes.add(Fetish.FETISH_PENIS_RECEIVING);
				}
			} else {
				if(Main.sex.getTargetedPartner(characterPerformingAction).hasVagina()) {
					fetishes.add(Fetish.FETISH_VAGINAL_GIVING);
				}
				fetishes.add(Fetish.FETISH_ANAL_GIVING);
				fetishes.add(Fetish.FETISH_DOMINANT);
				if(characterPerformingAction.hasPenis()) {
					fetishes.add(Fetish.FETISH_PENIS_GIVING);
				}
			}
			return new ArrayList<>(fetishes);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isBeingPenetrated()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_ALL_FOURS_GETTING_FUCKED = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingAllFoursData(Main.sex.getCharacterPerformingAction()), true)
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotAllFours.ALL_FOURS
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotAllFours.ALL_FOURS_TWO
					&& isAllFoursAvailable(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "被后入(请求)";
				} else {
					return "开始被操(请求)";
				}
			} else {
				return "开始被骑乘(请求)";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "你趴下身，让[npc2.name]后入你。<br/>"
						+ getRequestTooltipText();
				} else {
					return "你趴着露出自己私处，暗示[npc2.name]骑上来像发情的动物一样操你。<br/>"
						+ getRequestTooltipText();
				}
			} else {
				return "你露出后臀，暗示[npc2.name]骑上来像发情的动物一样操你。<br/>"
						+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "[npc.name]在[npc2.name]前四肢着地，想被[npc2.herHim]以后入式操干。"
							+ "[npc.name]倒退着爬行，用[npc.ass+]顶向[npc2.namePos]的小腹，回头恳求道，"
							+ "[npc.speech(求你了，像动物一样操我吧！)]";
				} else {
					return "[npc.name]四肢着地趴在[npc2.name]面前，挑动着[npc.herHim]的情欲，勾引着[npc2.herHim]骑上来像动物发情一样操[npc.her]。"
							+"[npc.name]倒退着爬到[npc2.namePos]兽态身躯下，抬起[npc.hips+]，恳求道，"
							+ "[npc.speech(求你了，像动物一样操我吧！)]";
				}
				
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "[npc.Name]转身，露出自己[npc.legRace]态的后半身，示意[npc2.name]狠狠地操进来。"
							+"[npc.name]转头往后退对准[npc2.namePos]小腹抬起[npc.ass+]，请求道，"
							+ "[npc.speech(求你了，像动物一样操我吧！)]";
				} else {
					return "[npc.Name]露出自己[npc.legRace]态的身躯的后部，挑动着[npc.Name]的兽性，暗示[npc.her]骑在上来像发情的动物一样操[npc.her]。"
							+"[npc.name]转头往后退抬起[npc.ass+]，请求道，"
							+ "[npc.speech(求你了，像动物一样操我吧！)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(generateReceivingAllFoursData(Main.sex.getCharacterPerformingAction()));
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			return POSITION_ALL_FOURS_GETTING_FUCKED.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes);
		}
	};
	
	public static final SexAction POSITION_ALL_FOURS_FUCKING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingAllFoursData(Main.sex.getCharacterTargetedForSexAction(this)), false)
					&& isAllFoursAvailable(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "后入[npc2.herHim]";
				} else {
					return "操[npc2.herHim]";
				}
			} else {
				return "骑乘[npc2.herHim]";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "让[npc2.name]四肢跪地，以便你用后入式体位操[npc2.herHim]。";
				} else {
					return "让[npc2.name]把[npc2.herself]自己朝向你，以便你可以操[npc2.herHim]。";
				}
			} else {
				return "让[npc2.name]把臀部和后腿朝向你，以便你骑上[npc2.herHim]动物化的后股。";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "[npc.Name]把[npc2.herHim]摁倒在身前，让[npc2.herHim]四肢着地，准备后入[npc.herHim]。"
							+ "跪在[npc2.herHim]后面，[npc.she]抓住[npc2.her][npc2.hips+]并将[npc2.her][npc2.ass+]对准[npc.her]的腹股沟，[npc.moaning]着，"
							+ "[npc.speech(是时候像动物一样操你了！)]";
				} else {
					return "[npc.Name]把[npc2.name]摁倒在身前，让[npc2.herHim]四肢着地，准备骑上去像发情的动物一样操[npc2.herHim]。"
							+ "[npc.name]往前走，让[npc2.name]躺在自己[npc.legRace]态的身躯下，微微曲腿，[npc.moansVerb]道，"
							+ "[npc.speech(没错，现在让我像动物一样操你！)]";
				}
				
			} else {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "[npc.Name]移到[npc2.name][npc2.legRace]态的身躯后，准备操[npc2.her]。"
							+"[npc.name]抓住[npc2.name]的[npc2.hips]把下体推到[npc2.her]身后，[npc.moaning]着，"
							+ "[npc.speech(是时候像动物一样操你了！)]";
				} else {
					return "[npc.Name]移到[npc2.name][npc2.legRace]态的身躯后，准备骑上去像动物一样狠狠地操[npc2.herHim]。"
							+"[npc.name]靠过去，迅速骑了上去。"
							+ "[npc.speech(没错，现在让我像动物一样操你！)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generatePerformingAllFoursData(Main.sex.getCharacterTargetedForSexAction(this)), false);
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				if(Main.sex.getTargetedPartner(characterPerformingAction).hasVagina()) {
					fetishes.add(Fetish.FETISH_VAGINAL_GIVING);
				}
				fetishes.add(Fetish.FETISH_ANAL_GIVING);
				fetishes.add(Fetish.FETISH_DOMINANT);
				if(characterPerformingAction.hasPenis()) {
					fetishes.add(Fetish.FETISH_PENIS_GIVING);
				}
			} else {
				if(characterPerformingAction.hasVagina()) {
					fetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
				}
				fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				fetishes.add(Fetish.FETISH_SUBMISSIVE);
				if(Main.sex.getTargetedPartner(characterPerformingAction).hasPenis()) {
					fetishes.add(Fetish.FETISH_PENIS_RECEIVING);
				}
			}
			return new ArrayList<>(fetishes);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_ALL_FOURS_FUCKING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingAllFoursData(Main.sex.getCharacterTargetedForSexAction(this)), true)
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotAllFours.BEHIND
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotAllFours.BEHIND_TWO
					&& isAllFoursAvailable(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "后入[npc2.herHim](请求)";
				} else {
					return "操[npc2.herHim](请求)";
				}
			} else {
				return "骑乘[npc2.herHim](请求)";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "你让[npc2.name]四肢着地准备后入。<br/>"
						+ getRequestTooltipText();
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "你让[npc2.name]四肢着地准备骑上去。<br/>"
						+ getRequestTooltipText();
				} else {
					return "你让[npc2.name]露出后臀准备骑上去<br/>。"
						+ getRequestTooltipText();
				}
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "[npc.Name]让[npc2.name]四肢着地准备后入。"
							+ "[npc.name]抓住[npc2.namePos]的肩膀向下压，恳求道，"
							+ "[npc.speech(求你了，趴下让我像动物一样操你！)]";
				} else {
					return "[npc.Name]让[npc2.name]四肢着地趴在身前准备骑上去。"
							+ "[npc.name]抓住[npc2.namePos]的肩膀向下压，恳求道，"
							+ "[npc.speech(求你了，趴下让我像动物一样操你！)]";
				}
				
			} else {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "[npc.Name]移到[npc2.name][npc2.legRace]态的身躯后，准备操[npc2.her]。"
							+ "[npc2.name]的臀部展露在[npc.name]眼前，[npc.name]抓住[npc2.her]的[npc2.hips]，恳求道，"
							+ "[npc.speech(求你了，让我像动物一样操你吧！)]";
				} else {
					return "[npc.Name]移到[npc2.name][npc2.legRace]态的身躯后，准备骑上去像动物一样狠狠地操[npc2.herHim]。"
							+ "[npc2.name]的臀部展露在[npc.name]眼前，[npc.she]恳求道，"
							+ "[npc.speech(求你了，让我骑着你吧！)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(generatePerformingAllFoursData(Main.sex.getCharacterTargetedForSexAction(this)));
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			return POSITION_ALL_FOURS_FUCKING.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes);
		}
	};

	
	public static final SexAction SWITCH_TO_SITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(
						SexSlotSitting.SITTING),
				Util.newArrayListOfValues(
						SexSlotSitting.PERFORMING_ORAL,
						SexSlotSitting.PERFORMING_ORAL_TWO,
						SexSlotSitting.PERFORMING_ORAL_THREE));

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isTaur()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "转成坐姿";
		}
		@Override
		public String getActionDescription() {
			return "坐在旁边的台子上，让[npc2.name]跪在你面前，准备进行口交。";
		}
		@Override
		public String getDescription() {
			return "[npc.name]想换个姿势，于是让[npc2.name]跪在旁边的台子前面。"
					+ "[npc.Name]坐在[npc2.herHim]面前，[npc.moansVerb]着，"
					+ "[npc.speech(对……这下更好了……)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction SWITCH_TO_SITTING_TAUR = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(
						SexSlotSitting.SITTING_BETWEEN_LEGS),
				Util.newArrayListOfValues(
						SexSlotSitting.SITTING,
						SexSlotSitting.PERFORMING_ORAL_TWO,
						SexSlotSitting.PERFORMING_ORAL_THREE));

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isTaur()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isTaur()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "转成坐姿";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]坐在旁边的台子上，用你动物化的下体靠向[npc2.herHim]，准备操[npc2.herHim]。";
		}
		@Override
		public String getDescription() {
			return "[npc.name]想换个姿势，于是让[npc2.name]坐在了旁边的台子上。"
					+ "[npc.name]靠向[npc.her]对象的上身，[npc.moansVerb]着，"
					+ "[npc.speech(对……这样操你会更爽哦……)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};

//	public static final SexAction SWITCH_DESK_ORAL_TO_SEX = new SexAction(
//			SexActionType.POSITIONING,
//			ArousalIncrease.ONE_MINIMUM,
//			ArousalIncrease.ONE_MINIMUM,
//			CorruptionLevel.ZERO_PURE,
//			null,
//			SexParticipantType.NORMAL) {
//		
//		private PositioningData data = new PositioningData(
//				SexPosition.OVER_DESK,
//				Util.newArrayListOfValues(
//						SexSlotDesk.BETWEEN_LEGS),
//				Util.newArrayListOfValues(
//						SexSlotDesk.OVER_DESK_ON_FRONT));
//
//		@Override
//		public boolean isBaseRequirementsMet() {
//			return checkBaseRequirements(data, false);
//		}
//		@Override
//		public String getActionTitle() {
//			return "Switch to sex";
//		}
//		@Override
//		public String getActionDescription() {
//			return "Get [npc2.name] to sit down on a nearby surface, before stepping over [npc2.herHim] with your lower animalistic body, ready to start fucking [npc2.herHim].";
//		}
//		@Override
//		public String getDescription() {
//			return "Switch test!";
//		}
//		@Override
//		public void applyEffects() {
//			GenericPositioning.setNewSexManager(data, false);
//		}
//	};
	
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
			return "回应姿势切换";
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
			
			if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL) {
				if(isHappy) {
					boolean standing = SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction());
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.speech(不要以为这就意味着你说了算！)]"
									+"[npc.name]龇牙咆哮，"+(standing?"向前":"跪下")+"让脸凑近你的小腹。"
									+ "[npc.she]瞪着你，凑过来，粗暴地抓住你的[pc.hips]，嘶声道，"
									+"[npc.speech(保持安静，然后感激我这么做！)]";
						default:
							return "[npc.speech(啊，太棒啦，那肯定很舒服呢！)]"
									+ "[npc.name]愉悦地回答道，然后[npc.she]"+(standing?"走上前":"跪下来")+"，将[npc.face]靠近你的下体。"
									+ "[npc.she]凝视着你，凑过来，抓住你的[pc.hips]，[npc.moansVerb]道，"
									+ "[npc.speech(现在乖乖尝尝这个吧！)]";
					}
					
				} else {
					return "[npc.Name]公然拒绝了你的要求，把你拉回到原来的姿势，怒斥道，"
							+ "[npc.speech(我<i>才不会</i>那样给你口！你<i>敢不敢</i>再试试！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL_BEHIND) {
				if(isHappy) {
					boolean standing = SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction());
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.speech(不要以为这就意味着你说了算！)]"
									+ "[npc.name]龇牙咆哮，"+(standing?"向前":"跪下")+"让脸凑近你[pc.ass+]。"
									+ "[npc.she]凑过来，粗暴地抓住你的[pc.hips]，嘶声道，"
									+"[npc.speech(保持安静，然后感激我这么做！)]";
						default:
							return "[npc.speech(啊，太棒啦，那肯定很舒服呢！)]"
									+ "[npc.name]愉悦地回答道，然后[npc.she]"+(standing?"走上前":"跪下来")+"，将[npc.face]靠近你[pc.ass+]。"
									+ "[npc.she]凑过来，抓住你的[pc.hips]，[npc.moansVerb]道，"
									+ "[npc.speech(现在乖乖尝尝这个吧！)]";
					}
					
				} else {
					return "[npc.Name]公然拒绝了你的要求，把你拉回到原来的姿势，怒斥道，"
							+ "[npc.speech(我<i>才不会</i>那样给你口！你<i>敢不敢</i>再试试！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.STANDING_DOMINANT
					&& (Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL || Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL_BEHIND)) {
				if(isHappy) {
					boolean biped = !Main.sex.getCharacterPerformingAction().isTaur();
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.speech(不要以为这就意味着你说了算！)]"
									+ "[npc.name]咆哮道，然后将"+(!biped?"[npc.her]兽态的[npc.legRace]身体":"[npc.herself]")+"压向你的[pc.face]。"
									+"[npc.she]晃着[pc.hips]，嘶声道，"
									+ "[npc.speech(做好准备，然后感谢我让你这么做！)]";
						default:
							return "[npc.speech(啊，太棒啦，那肯定很舒服呢！)]"
									+ "[npc.name]愉悦地回答道，然后"+(!biped?"将[npc.her]兽态的[npc.legRace]身体":"")+"压向你的[pc.face]。"
									+ "[npc.she]扭动着[npc.her][pc.hips]，[npc.moansVerb]道，"
									+ "[npc.speech(继续嘛，就这样干！)]";
					}
					
				} else {
					return "[npc.Name]公然拒绝了你的要求，把你拉回到原来的姿势，怒斥道，"
							+ "[npc.speech(我<i>才不会</i>对你给我口感兴趣！你<i>敢不敢</i>再试试！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAllFours.ALL_FOURS) {
				if(isHappy) {
					boolean biped = !Main.sex.getCharacterPerformingAction().isTaur();
					boolean bipedPlayer = !Main.game.getPlayer().isTaur();
					boolean standingPlayer = SexSlotAllFours.BEHIND.isStanding(Main.game.getPlayer());
					StringBuilder sb = new StringBuilder();
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							sb.append("[npc.speech(不要以为这就意味着你说了算！)]"
									+ "[npc.name]咆哮道，然后"+(biped?"粗暴地将[npc.her]兽态的[npc.legRace]身体":"四肢跪地并将身体")+"压向你。"
									+"[npc.she]晃着[pc.hips]，嘶声道，"
									+"[npc.speech(过来准备"+(!bipedPlayer?"骑在我身上":"上来操我")+"，感谢我让你这么做！)]");
							break;
						default:
							sb.append("[npc.speech(啊，太棒啦，那肯定很舒服呢！)]"
									+ "[npc.name]愉悦地回答道，然后"+(biped?"饥渴地将[npc.her]兽态的[npc.legRace]身体":"四肢跪地并将身体")+"压向你。"
									+"[npc.she]晃着[pc.hips]，[npc.moansVerb]道，"
									+ "[npc.speech(来吧，"+(!bipedPlayer?"骑上我":"操我")+"吧！)]");
							break;
					}
					sb.append((!bipedPlayer
										?(!biped
											?"<br/>"
												+"听到要求，你抬起前肢靠过去，骑了上去。"
												+"看着你饥渴的样子，[npc.She]发出迷乱的[npc.moan]，"
													+ "[npc.her]支撑着你，准备被你像发情的动物一样狠操。"
											:"<br/>"
												+"听到要求，你走过去，低身骑了上去。"
												+ "[npc.she]看到你急切的样子，狂乱/迷乱地[npc.moansVerb]着，等待你像发情的动物一样狠狠操[npc.she]。")
										:"<br/>"
											+ "听到要求，你"+(standingPlayer?"走过去":"屈膝爬过去")+"用腹部压着[npc.her][npc.ass+]。"
											+ "[npc.she]享受你的爱抚，迷乱地[npc.moanverb]出声，等待你像动物一样操[npc.she]。"));
					return sb.toString();
					
				} else {
					return "[npc.Name]驳回了你的请求，把你拉回原来的位置，愤怒地斥责你，"
							+ "[npc.speech(我<i>可不会</i>被你操！你<i>敢不敢</i>再试试！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAllFours.BEHIND) {
				if(isHappy) {
					boolean biped = !Main.sex.getCharacterPerformingAction().isTaur();
					boolean bipedPlayer = !Main.game.getPlayer().isTaur();
					boolean standing = SexSlotAllFours.BEHIND.isStanding(Main.sex.getCharacterPerformingAction());
					StringBuilder sb = new StringBuilder();
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							sb.append("[npc.speech(不要以为这就意味着你说了算！)]"
									+ "[npc.name]咆哮着，然后"
										+(!bipedPlayer
											?(!biped
												?"[npc.her]提起前腿靠过来，骑在你身上。"
												:(standing?"走过来":"爬过来")+"把腹部压在你的[pc.legRace]态的身体上。")
											:(!biped
												?"靠过来骑在你身上。"
												:(standing?"走过来":"爬过来")+"把腹部压在你[pc.ass+]上。"))
									+"[npc.she]晃着[pc.hips]，嘶声道，"
									+ "[npc.speech(感谢我像操卑贱的动物一样操你！)]");
							break;
						default:
							sb.append("[npc.speech(哦，太棒啦，听起来很舒服呢！)]"
									+ "[npc.name]愉悦地回答道，然后"
										+(!bipedPlayer
											?(!biped
												?"[npc.her]提起前腿靠过来，骑在你身上。"
												:(standing?"走过来":"爬过来")+"把腹部压在你的[pc.legRace]态的身体上。")
											:(!biped
												?"靠过来骑在你身上。"
												:(standing?"走过来":"爬过来")+"把腹部压在你[pc.ass+]上。"))
									+"[npc.she]晃着[pc.hips]，[npc.moansVerb]道，"
									+"[npc.speech(现在做个好孩子过来乖乖挨操！)]");
							break;
					}
					return sb.toString();
					
				} else {
					return "[npc.Name]驳回了你的请求，把你拉回原来的位置，愤怒地斥责你，"
							+ "[npc.speech(我<i>才不会</i>那样操你！你<i>敢不敢</i>再试试！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.SIXTY_NINE) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name]跳到你身上，四肢着地，把脸移到你[pc.legs]间，用腹部对着你的脸。"
									+ "转头看着你咆哮着，"
									+ "[npc.speech(好主意，骚货！现在<i>别动</i>，这样才能好好地使用你！)]";
						default:
							return "[npc.Name]跳到你身上，四肢着地，把脸移到你[pc.legs]间，用腹部对着你的脸。"
									+"转过头对着你咧嘴一笑，"
									+ "[npc.speech(真是个乖[pc.girl]！会很舒服的！)]";
					}
					
				} else {
					return "[npc.Name]攥住你的[pc.arm]，把你拉回到原来的姿势，怒斥道，"
							+ "[npc.speech(你想想你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.SIXTY_NINE) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name]粗暴地抓住你的[pc.hips]，拽着你一起，让你四肢着地靠在[npc.herHim]身上。"
									+"她把你的小腹拉到脸前，呲牙咆哮，"
									+ "[npc.speech(好想法，荡妇！现在把嘴巴动起来吧！)]";
						default:
							return "[npc.Name]抓住你的[pc.hips]，拽着你一起，让你四肢着地压在[npc.herHim]身上。"
									+ "[npc.she]把你的小腹拉到脸前，[npc.moansVerb]着，"
									+ "[npc.speech(真是个乖[pc.girl]！会很舒服的！)]";
					}
					
				} else {
					return "[npc.Name]把你的手从肩膀上拿开，把你拉回到之前的位置，愤怒地责备着，"
							+ "[npc.speech(你想想你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.COWGIRL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name]跳到你身上形成骑乘位，用[npc.she]的小腹靠着你的。"
									+"[npc.she]稍稍前倾，咆哮，"
									+ "[npc.speech(好主意，骚货！现在<i>别动</i>，这样才能好好地使用你！)]";
						default:
							return "[npc.Name]跳到你身上形成骑乘位，用[npc.she]的小腹靠着你的。"
									+ "[npc.she]稍稍前倾，咧嘴一笑，"
									+ "[npc.speech(真是个乖[pc.girl]！会很舒服的！)]";
					}
					
				} else {
					return "[npc.Name]攥住你的[pc.arm]，把你拉回到原来的姿势，怒斥道，"
							+ "[npc.speech(你想想你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.COWGIRL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name]抓住你的[pc.hips]，粗暴地拽着你一起倒下，让你以骑乘位骑在[npc.herHim]身上。"
									+ "[npc.she]抬头对上你的[pc.eyes]，咆哮着，"
									+ "[npc.speech(好想法，荡妇！再用点力！)]";
						default:
							return "[npc.Name]抓住你的[pc.hips]，拽着你一起倒下，让你以骑乘位骑在[npc.herHim]身上。"
									+ "[npc.she]抬头对上你的[pc.eyes]，[npc.moansVerb]，"
									+ "[npc.speech(真是个乖[pc.girl]！会很舒服的！)]";
					}
					
				} else {
					return "[npc.Name]把你的手从肩膀上拿开，把你拉回到之前的位置，愤怒地责备着，"
							+ "[npc.speech(你想想你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.FACE_SITTING) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name]拍开你的[pc.hands]，抓住你的腕部拽着你往前，咆哮着，"
									+ "[npc.speech(你怎么<i>敢</i>自己乱动的！我恩准你骑在我脸上，只是因为<i>我</i>想这么做，听懂了吗？！)]"
									+ "<br/>"
									+"就这样，[npc.she]迅速拉着你躺下。"
									+"你无视[npc.namePos]的话语，兴奋地把小腹靠向[npc.her]的脸。";
						default:
							return "[npc.name]朝你微笑，回复道："
									+ "[npc.speech(真是个好主意！哦，会很舒服的！)]"
									+ "<br/>"
									+"就这样，[npc.she]迅速拉着你躺下。"
									+ "你发现自己正好得到了想要的，便愉悦[pc.moan]，急切地把下体压到[npc.her]脸上。";
					}
					
				} else {
					return "[npc.Name]拍开你的手，把你拉回到原来的位置，生气地说，"
							+ "[npc.speech(你想想你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.FACE_SITTING) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name]趴到你身上，把脸对着你的胯部，而小腹对着你的嘴。"
									+"[npc.she]粗暴地蹭着你的脸咆哮。"
									+ "[npc.speech(好主意，骚货！现在<i>别动</i>，这样才能好好地使用你！)]";
						default:
							return "[npc.Name]趴到你身上，把脸对着你的胯部，而小腹对着你的嘴。"
									+"[npc.she]蹭着你的脸[npc.moanverb]。"
									+ "[npc.speech(真是个乖[pc.girl]！会很舒服的！)]";
					}
					
				} else {
					return "[npc.Name]攥住你的[pc.arm]，把你拉回到原来的姿势，怒斥道，"
							+ "[npc.speech(你想想你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.MATING_PRESS) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name]听到你说什么后，饥渴难耐，迫不及待，蹭着你的脸压在你身上。"
									+"[npc.she]把你的手腕分别压在头的两边，粗暴地用小腹摩擦着你的小腹，咆哮着，"
									+ "[npc.speech(你这下贱淫妇！"
									+ UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN我要狠狠操你！#ELSE我要给你狠狠播种！#ENDIF")
									+ ")]";
						default:
							return "[npc.Name]听到你说什么后，饥渴难耐，迫不及待，蹭着你的脸躺在你身上。"
									+"[npc.she]急切地把你的手腕分别压在头的两边，用小腹摩擦着你的小腹，[npc.moansVerb]着，"
									+ "[npc.speech(真是个乖[pc.girl]！"
									+ UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN我要狠狠操你！#ELSE我要给你狠狠播种！#ENDIF")
									+ ")]";
					}
					
				} else {
					return "[npc.Name]攥住你的[pc.arm]，把你拉回到原来的姿势，怒斥道，"
							+ "[npc.speech(你想想你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.MISSIONARY) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							if(Main.sex.getCharacterPerformingAction().hasLegs()) {
								return "你十分满意。[npc.Name]丝毫不抵抗，任你推到，还朝你张开[npc.legs]，暴露自己的私处，咆哮着威胁你，"
										+ "[npc.speech(别得意忘形，贱人！这可是我说了算！)]";
							} else {
								return "你十分满意。[npc.Name]丝毫不抵抗，任你推到，还朝你露出小腹的私处，咆哮着威胁你，"
										+ "[npc.speech(别得意忘形，贱人！这可是我说了算！)]";
							}
						default:
							if(Main.sex.getCharacterPerformingAction().hasLegs()) {
								return "你十分满意。[npc.Name]丝毫不抵抗，任你推到，还朝你张开[npc.legs]，暴露自己的私处，[npc.moansVerb]道，"
										+ "[npc.speech(我喜欢稍微主动点的对象呢！来引导我嘛！)]";
							} else {
								return "你十分满意。[npc.Name]丝毫不抵抗，任你推到，还朝你露出小腹的私处，[npc.moansVerb]道，"
										+ "[npc.speech(我喜欢稍微主动点的对象呢！来引导我嘛！)]";
							}
					}
				} else {
					return "[npc.Name]拍开了你的[pc.hands]，让你变回原来的姿势，怒斥道，"
							+ "[npc.speech(你想想你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.MISSIONARY) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "让你喜出望外的是，[npc.name]跪到了你的[pc.legs]间，抓住并分开你的[npc.legs]，然后咆哮道，"
									+ "[npc.speech(这就对了，婊子！像个骚货一样叉开腿吧！)]";
						default:
							return "你十分满意。[npc.Name]跪在你[pc.legs]间，拉开你的[npc.legs]，[npc.moansVerb]道，"
									+ "[npc.speech(真是好想法！腿好好张大点！现在就张开！)]";
					}
				} else {
					return "[npc.Name]抓住了你的一只[pc.arms]，让你变回原来的姿势，怒斥道，"
							+ "[npc.speech(你想想你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN_FRONT
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.MISSIONARY) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "让你喜出望外的是，[npc.name]很顺从地便被推倒趴下，还在你面前翘起了[npc.ass+]，露出下体，[npc.she]用威胁的语调低吼道，"
									+ "[npc.speech(别得意忘形了，贱人！这里可是我说了算！)]";
						default:
							return "让你喜出望外的是，[npc.name]很顺从地便被推倒趴下，翘起[npc.ass+]，露出下体，[npc.moansVerb]道，"
									+ "[npc.speech(我喜欢稍微主动点的同伴呢！来占有我吧！)]";
					}
				} else {
					return "[npc.Name]拍开了你的[pc.hands]，让你变回原来的姿势，怒斥道，"
							+ "[npc.speech(你想想你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAgainstWall.STANDING_WALL
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotAgainstWall.FACE_TO_WALL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "让你喜出望外的是，[npc.name]一把抓住了你的腰，整个身子就对着你的背贴了上来，在你耳边低哼道，"
									+ "[npc.speech(我就喜欢从后面干你这种婊子！够骚就<i>站好了别动</i>！)]";
						default:
							return "让你喜出望外的是，[npc.name]直接揽住了你的屁股，靠了上来，越过你的肩头在耳边[npc.moans]道，"
									+ "[npc.speech(真是个乖[pc.girl]！会很舒服的！)]";
					}
				} else {
					return "[npc.Name]抓住了你的肩膀，把你从[pc.wall]边拉了回来，让你变回原来的姿势，怒斥道，"
							+ "[npc.speech(你想想你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAgainstWall.STANDING_WALL
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotAgainstWall.BACK_TO_WALL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name]看到你奋力引诱着[npc.herHim]抵着[pc.wall]上了你的样子，不禁面露笑意。"
									+ "[npc.she]毫不在乎地把身子黏了上来，越过你的肩头在耳边低吼道，"
									+ "[npc.speech(好个小骚货！给我<i>站好了</i>，我可要狠狠地操你一顿！)]";
						default:
							return "[npc.Name]看到你奋力引诱着[npc.herHim]抵着[pc.wall]上了你的样子，不禁面露笑意。"
									+ "[npc.she]把身子压了上来，越过你的肩头在耳边[npc.moans]道，"
									+ "[npc.speech(真是个乖[pc.girl]！会很舒服的！)]";
					}
					
				} else {
					return "[npc.Name]抓住了你的肩膀，把你从[pc.wall]边拉了回来，让你变回原来的姿势，怒斥道，"
							+ "[npc.speech(你想想你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			}
			
			return "";
		}

		@Override
		public void applyEffects() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(
						Main.sex.getPositionRequest().getPosition(),
						Main.sex.getPositionRequest().getPartnerSlots().get(0),
						Main.sex.getPositionRequest().getPerformerSlots().get(0),
						Main.game.getPlayer())) {
				GenericPositioning.setNewSexManager(Main.sex.getPositionRequest(), true);
			} else {
				Main.sex.addPositioningRequestsBlocked(Main.game.getPlayer(), Main.sex.getPositionRequest().getPosition());
			}
			
			Main.sex.setPositionRequest(null);
		}
	};
	
}
