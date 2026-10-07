package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericPositioning;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * Contains all positional changes for both sub and dom.
 * 
 * If sub, positional change is just a suggestion, which the NPC may refuse if they have other preferences.
 * 
 * @since 0.1.79
 * @version 0.4.9.5
 * @author Innoxia
 */
public class ToiletStall {
	
	private static boolean checkBaseRequirements(PositioningData data, boolean request) {
		return GenericPositioning.checkBaseRequirements(data, request);
//		return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
//				&& Main.sex.getInitialSexManager().getAllowedSexPositions().contains(data.getPosition())
//				&& !(Main.sex.getPosition() == data.getPosition()
//					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==data.getPerformerSlots().get(0)
//					&& Main.sex.getSexPositionSlot(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))==data.getPartnerSlots().get(0))
//				&& data.getPosition().getMaximumSlots()>=Main.sex.getTotalParticipantCount(false)
//				&& Main.sex.getTotalParticipantCount(false)==(data.getPerformerSlots().size()+data.getPartnerSlots().size())
//				&& (request
//						?Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())!=SexControl.FULL
//						:(Main.sex.getCharacterPerformingAction().isPlayer()
//							?Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
//							:!Main.sex.isCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterPerformingAction())))
//				&& (!request && !Main.sex.getCharacterPerformingAction().isPlayer()
//						?((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(data.getPosition(), data.getPerformerSlots().get(0), data.getPartnerSlots().get(0), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
//						:true);
	}

	public static final SexAction PLAYER_POSITION_SWAP = new SexAction(
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
//					&& Main.sex.getInitialSexManager().isPositionChangingAllowed(Main.sex.getCharacterPerformingAction()) // Should be covered in the method above
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
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
			return "你控制住[npc2.name]，跟[npc2.herHim]换了个位置，便[npc.moaning]道，"
					+ "[npc.speech(这样会更爽哦！)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.swapSexPositionSlots(Main.game.getPlayer(), Main.sex.getCharacterTargetedForSexAction(this));
		}
	};

	public static final SexAction PLAYER_POSITION_FACE_TO_WALL = new SexAction(
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
			return "面对墙面";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]面朝附近的墙面。";
		}
		@Override
		public String getDescription() {
			return "[npc.Name]抓住[npc2.namePos]的肩膀，推着[npc2.herHim]面向厕所隔间的墙面。"
					+ "随后用整个身子压在[npc2.her]的背上，在[npc2.ear]边[npc.moansVerb]着，"
					+ "[npc.speech(真乖！操你的时候别乱动！)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction PLAYER_POSITION_FACE_TO_WALL_REQUEST = new SexAction(
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
			return "面对墙面";
		}
		@Override
		public String getActionDescription() {
			return "尝试调整姿势，让自己面对厕所隔间的墙面。";
		}
		@Override
		public String getDescription() {
			return "[npc2.name]还没来得及反应，你就面对着厕所隔间的墙面站着了。"
					+ "你按住面前涂满了涂鸦的墙面，把[npc.ass+]向后一探，对着[npc2.name]晃动起来，希望[npc2.herHim]能直接这样上了你。";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction PLAYER_POSITION_BACK_TO_WALL = new SexAction(
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
			return "背靠墙面";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]背靠厕所隔间的墙面。";
		}
		@Override
		public String getDescription() {
			return "[npc.Name]抓住[npc2.namePos]肩膀，把[npc2.herHim]推到厕所的墙壁上。"
					+ "随后用整个身子压了过去，在[npc2.ear]边[npc.moansVerb]着，"
					+ "[npc.speech(乖乖的，在我操你的时候不要乱动！)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction PLAYER_POSITION_BACK_TO_WALL_REQUEST = new SexAction(
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
			return "背靠墙面";
		}
		@Override
		public String getActionDescription() {
			return "试着换个姿势，把后背靠在厕所隔间墙上。";
		}
		@Override
		public String getDescription() {
			return "趁[npc2.name]没反应过来，你就靠在了厕所隔间的墙上。"
					+ "你背靠着涂满了涂鸦的表面，向[npc2.name]投出一阵格外撩人的眼神，希望[npc2.herHim]能直接这样上了你。";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction PLAYER_POSITION_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(SexSlotStanding.STANDING_DOMINANT),
				Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "跪下(接受口交)";
		}
		@Override
		public String getActionDescription() {
			return "强迫[npc2.name]下跪。";
		}
		@Override
		public String getDescription() {
			return "[npc.Name]抓住[npc2.namePos]的肩膀，迅速向下按，强迫[npc2.herHim]跪在[npc.herHim]身前。"
					+ "[npc2.name]抬起头，对方正对着自己屈服的样子喜笑颜开，[npc.name]轻笑一声，[npc.moansVerb]道，"
					+ "[npc.speech(该是时候动动嘴了！)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction PLAYER_POSITION_KNEELING_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotStanding.STANDING_DOMINANT));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "跪下";
		}
		@Override
		public String getActionDescription() {
			return "跪在地上，希望[npc2.name]会让你给[npc2.herHim]口交。";
		}
		@Override
		public String getDescription() {
			return "你迅速冲着[npc2.name]跪下，膝行向前，脸离着[npc2.her]的下体更近了。";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction PLAYER_FORCE_POSITION_SELF_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotStanding.STANDING_DOMINANT));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "跪下(给予口交)";
		}
		@Override
		public String getActionDescription() {
			return "跪在[npc2.name]身前，准备用你的嘴或者[npc.hands]服侍[npc2.herHim]。";
		}
		@Override
		public String getDescription() {
			return "[npc.name]用[npc.hands]在[npc2.namePos]的身体上游走着，随后便跪在[npc2.herHim]面前，抬头向[npc2.herHim]抛出了一抹魅惑的微笑。"
					+ "[npc.she]轻笑一声，[npc.moansVerb]道，"
					+ "[npc.speech(那就乖乖尝尝这个吧！)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};

	public static final SexAction PLAYER_POSITION_REQUEST_SELF_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(SexSlotStanding.STANDING_DOMINANT),
				Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "接受跪姿口交";
		}
		@Override
		public String getActionDescription() {
			return "尝试让[npc2.name]跪下来，这样[npc2.she]就能给你口交了。";
		}
		@Override
		public String getDescription() {
			return "你抬起[npc.arms]，抓住了[npc2.namePos]的肩膀，然后轻轻一按，示意[npc2.herHim]在你面前跪下。";
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
			
			if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAgainstWall.STANDING_WALL) {
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
					return "[npc.Name]抓住了你的肩膀，把你从墙边拉了回来，让你变回原来的姿势，怒斥道，"
							+ "[npc.speech(你以为你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAgainstWall.STANDING_WALL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name]看到你奋力引诱着[npc.herHim]抵着墙上了你的样子，不禁面露笑意。"
									+ "[npc.she]毫不在乎地把身子黏了上来，越过你的肩头在耳边低吼道，"
									+ "[npc.speech(好个小骚货！给我<i>站好了</i>，我可要狠狠地操你一顿！)]";
						default:
							return "[npc.Name]看到你奋力引诱着[npc.herHim]抵着墙上了你的样子，不禁面露笑意。"
									+ "[npc.she]把身子压了上来，越过你的肩头在耳边[npc.moans]道，"
									+ "[npc.speech(真是个乖[pc.girl]！会很舒服的！)]";
					}
					
				} else {
					return "[npc.Name]抓住了你的肩膀，把你从墙边拉了回来，让你变回原来的姿势，怒斥道，"
							+ "[npc.speech(你以为你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.STANDING_DOMINANT) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name]看到你屈服的跪姿，不禁嘴角上扬。"
									+ "[npc.she]轻笑一声，用一只[npc.hand]按住了你的脑袋，猛地把你拉到[npc.her]的胯前，低头对你低吼道，"
									+ "[npc.speech(你最好口活好一点，婊子！操你嘴的时候给我<i>安稳点</i>！)]";
						default:
							return "[npc.Name]看到你屈服的跪姿，不禁嘴角上扬。"
									+ "伴随着一声[npc.a_moan+]，[npc.she]用一只[npc.hand]按住了你的脑袋，把你拉到了[npc.her]的胯前，低头对你[npc.moansVerb]道，"
									+ "[npc.speech(真是个乖[pc.girl]！会很舒服的！)]";
					}
					
				} else {
					return "[npc.Name]攥住你的[pc.arm]，把你拉回到原来的姿势，怒斥道，"
							+ "[npc.speech(你以为你在干嘛？！你最好别<i>敢</i>有下一次！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name]一把拍开了你的[pc.arms]，怒吼了一声。"
									+ "没想到[npc.she]却突然跪了下来，你低头看向[npc.herHim]，[npc.she]还正抬头对着你笑，"
									+ "[npc.speech(你太走运了，我就是这么想的！现在别乱动，婊子，这是给你脸了！)]";
						default:
							return "[npc.name]伸出手抓住你的[pc.arms]，发出一声轻笑，[npc.she]等你压住[npc.herHim]，让[npc.her]跪下。"
									+ "你低头看向[npc.herHim]，[npc.she]正抬头对着你笑，"
									+ "[npc.speech(今天真是你的幸运日啊！我最喜欢用嘴了！给我好好珍惜！)]";
					}
					
				} else {
					return "[npc.name]把你的[pc.arms]从[npc.her]肩上打落，愤怒地斥责着你，"
							+ "[npc.speech(你以为你在干什么？！你真觉得我会给你跪下吗？！你<i>再</i>这样可就别怪我了！)]";
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
				GenericPositioning.setNewSexManager(Main.sex.getPositionRequest(), true);
			}
			
			Main.sex.setPositionRequest(null);
		}
	};
	
}
