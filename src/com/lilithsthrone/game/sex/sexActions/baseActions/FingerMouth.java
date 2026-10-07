package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
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

/**
 * @since 0.3.2
 * @version 0.3.4
 * @author Innoxia
 */
public class FingerMouth {

	// The world isn't ready for finger sucking just yet
	
//	public static final SexAction FINGER_MOUTH_PENETRATION = new SexAction(
//			SexActionType.START_ONGOING,
//			ArousalIncrease.ZERO_NONE,
//			ArousalIncrease.ONE_MINIMUM,
//			CorruptionLevel.ZERO_PURE,
//			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
//			SexParticipantType.NORMAL) {
//		@Override
//		public String getActionTitle() {
//			return "Finger sucking";
//		}
//
//		@Override
//		public String getActionDescription() {
//			return "Push your [npc.fingers] into [npc2.namePos] mouth.";
//		}
//
//		@Override
//		public String getDescription() {
//			return "Lifting [npc.her] [npc.hand] up to [npc2.namePos] mouth, [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] past [npc2.her] [npc2.lips+], forcing [npc2.herHim] to start lewdly sucking on [npc.her] intruding digits.";
//		}
//	};
//	
//	public static final SexAction FINGER_MOUTH_STOP_PENETRATION = new SexAction(
//			SexActionType.STOP_ONGOING,
//			ArousalIncrease.ONE_MINIMUM,
//			ArousalIncrease.ONE_MINIMUM,
//			CorruptionLevel.ZERO_PURE,
//			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
//			SexParticipantType.NORMAL) {
//		@Override
//		public String getActionTitle() {
//			return "Stop finger sucking";
//		}
//
//		@Override
//		public String getActionDescription() {
//			return "Pull your [npc.fingers] out of [npc2.namePos] mouth.";
//		}
//
//		@Override
//		public String getDescription() {
//			return "With a little sigh, [npc.name] [npc.verb(slide)] [npc.her] saliva-coated [npc.fingers] out of [npc2.namePos] mouth.";
//		}
//	};
	

	public static final SexAction PARTNER_ASSIST_BLOWJOB = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return Util.newArrayListOfValues(
					Main.sex.getCharacterPerformingAction(),
					Main.sex.getCharacterTargetedForSexAction(this),
					getBlowjobReceiver());
		}
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), getBlowjobReceiver(), SexAreaPenetration.PENIS);
		}
		
		private GameCharacter getBlowjobReceiver() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH);
			if(characters.isEmpty()) {
				return null;
			}
			return Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH).get(0);
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
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
			
			boolean available = getBlowjobReceiver()!=null
					&& getBlowjobReceiver()!=Main.sex.getCharacterPerformingAction()
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getFirstOngoingSexAreaPenetration(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH) == SexAreaPenetration.PENIS
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
			
//			System.out.println(Main.sex.getCharacterPerformingAction().getName()+" "+Main.sex.getCharacterTargetedForSexAction(this).getName()+" "+available);
			
			return available;
		}
		
		@Override
		public String getActionTitle() {
			return "辅助口交";
		}

		@Override
		public String getActionDescription() {
			return "帮[npc2.namePos]给[npc3.name]口交。";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.DOM_ROUGH) {
				return UtilText.returnStringAtRandom(
								(Main.sex.getCharacterTargetedForSexAction(this).hasHair() && Main.sex.getCharacterTargetedForSexAction(this).getHairRawLengthValue()>HairLength.THREE_SHOULDER_LENGTH.getMaximumValue()
									?"[npc.Name]向上探身，粗暴地攥住一把[npc2.namePos][npc2.hair+]，强迫[npc2.her]低头凑近[npc3.namePos]腹股沟并命令[npc2.herHim]吞没[npc3.cock+]。"
									:""),
								"[npc.Name]粗暴地抓住[npc2.namePos]头部的一边，用猛力上下狂扇[npc2.namePos]的头，强迫[npc2.herHim]继续吮吸[npc3.namePos][npc3.cock+]。",
								"[npc.Name]把[npc2.namePos]头部撞向[npc3.namePos]胯部，淫荡的咆哮与侮辱人的话语进入了[npc2.her][npc2.ear]，"
										+"从而迫使[npc2.herHim]将[npc3.her][npc3.cock+]完全含入[npc2.her]口中。");
				
			} else {
				return UtilText.returnStringAtRandom(
								(Main.sex.getCharacterTargetedForSexAction(this).hasHair() && Main.sex.getCharacterTargetedForSexAction(this).getHairRawLengthValue()>HairLength.THREE_SHOULDER_LENGTH.getMaximumValue()
									?"[npc.name]向上探身，用[npc.fingers+]抚过[npc2.namePos][npc2.hair+]，"
											+ "将"+(Main.sex.getCharacterTargetedForSexAction(this).getHairType().isDefaultPlural(Main.sex.getCharacterTargetedForSexAction(this))?"它们":"它")+ "整理到一起并握住"
											+"，留出位置让[npc2.name]继续给[npc3.name]口交。"
									:""),
								"[npc.Name]帮忙抓住[npc2.namePos]头部的一边，抬起并上下推动，以助[npc2.herHim]继续吮吸[npc3.namePos][npc3.cock+]。",
								"[npc.Name]对[npc2.herHim]说了一些鼓励的下流话，帮助把[npc2.namePos]头部推向[npc3.namePos]胯部，"
										+ "从而确保[npc2.she]将[npc3.her][npc3.cock+]完全含入[npc2.her]口中。");
			}
		}
	};
	
	//TODO assist cunnilingus
	
}
