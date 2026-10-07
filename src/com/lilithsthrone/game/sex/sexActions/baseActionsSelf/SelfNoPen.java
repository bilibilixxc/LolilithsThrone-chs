package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfNoPen {
	
	public static final SexAction STROKE_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.HANDS);
		}

		@Override
		public String getActionTitle() {
			return "抚摸自己";
		}

		@Override
		public String getActionDescription() {
			return "抚摸自己来寻求快感。";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.VAGINA)) {
				return UtilText.returnStringAtRandom(
						"[npc.name]将手伸到两腿之间，手指抚弄着[npc.her][npc.pussy+]入口，又刺激起外部的褶皱，[npc.she]发出[npc.a_moan+]。",
						"[npc.Name]将[npc.fingers]伸到双腿之间摸索着，轻轻地[npc.moaning]并抚弄着[npc.her]诱人的[npc.pussy]口。",
						"[npc.name]的指尖滑过[npc.her]那被冷落[npc.pussy+]，刺激着[npc.her]的外阴唇并发出[npc.moan]。",
						"[npc.Name]急切地将[npc.fingers]滑过欲求不满的[npc.pussy]，[npc.moaning+]着并轻轻地按压[npc.her]的外阴唇。");
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name]将手伸向腹股沟，按住"+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+"向下用力顶住[npc.her]欲求不满的[npc.pussy]，发出轻微的呜咽声。",
						"[npc.Name]将手指伸入[npc.her]的双腿之间，[npc.moaning+]并揉搓[npc.her]那"+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+"抵住[npc.her]的阴唇。",
						"[npc.Name]将[npc.her]的指尖滑过[npc.her]那"+Main.sex.getCharacterPerformingAction().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+"，[npc.she]发出[npc.a_moan+]，并试图隔着衣服按压来刺激[npc.her][npc.pussy+]。",
						"[npc.name]将手向下，伸入自己腿间，边按压边将双股挤在一起。"
								+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName() +"紧贴[npc.her]那被冷落的[npc.pussy]。");
			}
		}
		
		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.VAGINA)) {
				Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
			}
		}
		
	};
	
	public static final SexAction STROKE_MOUND = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().hasPenis()
					&& !Main.sex.getCharacterPerformingAction().hasVagina()
					&& Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.HANDS);
		}
		
		@Override
		public String getActionTitle() {
			return "爱抚下体";
		}

		@Override
		public String getActionDescription() {
			return "抚摸你的无性下体来寻求快感。";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)) {
				return UtilText.returnStringAtRandom(
						"[npc.name]把手探向[npc.legs]间，[npc.her]的指尖在[npc.her]那玩偶般的下体上游移，[npc.moaning+]并挑逗着敏感部位。",
						"[npc.Name]用手指挑逗着[npc.her]两腿之间玩偶般的敏感下体，[npc.moaning+]并刺激着[npc.herself]。",
						"[npc.name]试探着将手指伸到下面，开始揉捏和摩擦[npc.her]那娇嫩的无性别胯部。",
						"尽管没有生殖器，[npc.namePos]的胯部仍然是一个高度敏感的性感带，[npc.she]迫不及待地开始用手指贪婪地摩擦和按压它。");
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name]用[npc.her]的[npc.hand]抚摸过[npc.her]的腹股沟，按压[npc.her]那。"+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+"向下顶住[npc.her]那玩偶般的下体时，[npc.she]发出一声轻叹。",
						"[npc.Name]将手指伸入[npc.her]的双腿之间，[npc.moaning+]并揉搓[npc.her]那"+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+"抵住[npc.her]无性别的胯部。",
						"[npc.Name]将指尖滑过[npc.her]那"+Main.sex.getCharacterPerformingAction().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+", 按压并试图隔着衣服刺激[npc.her]那玩偶般的下体。",
						"[npc.name]将手向下，伸入自己腿间，边按压边将双股挤在一起。"
								+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+"紧贴[npc.her]那无性别的下体。");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return null;
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_MASTURBATION);
			}
		}
	};
	
}
