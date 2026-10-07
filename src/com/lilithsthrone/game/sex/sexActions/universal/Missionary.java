package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.8
 * @version 0.3.4
 * @author Innoxia
 */
public class Missionary {

	public static final SexAction SPREAD_LEGS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "张开双腿";
		}
		@Override
		public String getActionDescription() {
			return "将你的[npc.legs]对着[npc2.name]大幅度张开。";
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LYING_DOWN))
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.MISSIONARY))
					&& !Main.sex.isMasturbation()
					&& Main.sex.getCharacterPerformingAction().hasLegs()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getDescription() {
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterTargetedForSexAction(this)).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterPerformingAction()).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterTargetedForSexAction(this)).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterPerformingAction()).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 
			
			if(vaginalSex) {
				return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()),
						UtilText.returnStringAtRandom(
						"[npc.Name]咬住[npc.her]的[npc.lip]并且发出[npc.moan+]，"
								+ "准备[npc.spreadingHerLegs]来帮助[npc2.namePos]将[npc2.cock+]更深的插入[npc.her][npc.pussy+]。",
						"[npc.Name]向[npc2.name][npc.spreadsHerLegs]，帮助[npc2.herHim]将[npc2.her][npc2.cock+]深深地推入自己[npc.pussy+]。",
						"[npc.name]收回[npc.her]的[npc.feet]，向两侧张开，"
								+ "[npc.name]通过[npc.spreadingHerLegs]帮助[npc2.name]将[npc2.her][npc2.cock+]深深的插入自己[npc.pussy+]。",
						"[npc2.name]继续深入[npc.NamePos][npc.pussy+]时，[npc.Name][npc.spreadsHerLegs]，看向[npc2.she]并咬住了自己的[npc.lip]。"));
			}
			if(analSex) {
				return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()),
						UtilText.returnStringAtRandom(
						"[npc.Name]咬住[npc.her]的[npc.lip]并且发出[npc.moan+]，"
								+ "准备抬起[npc.her][npc.ass+]并[npc.spreadingHerLegs]来帮助[npc2.namePos]将[npc2.cock+]更深的插入[npc.her][npc.asshole+]。",
						"[npc.Name][npc.spreadsHerLegs]并且抬起[npc.ass+]对向[npc2.name]，帮助[npc2.herHim]将[npc2.her][npc2.cock+]推进自己[npc.asshole+]。",
						"[npc.name]收回[npc.her]的[npc.feet]，向两侧张开，"
								+ "[npc.Name][npc.spreadsHerLegs]并抬起[npc.ass+]对向[npc2.name]，帮助[npc2.herHim]将[npc2.her][npc2.cock+]推进自己[npc.asshole+]。",
						"[npc.Name][npc.spreadsHerLegs]并且抬高[npc.her][npc.ass+]，"
								+ "看向[npc2.name]并咬住[npc.her]的[npc.lip]，[npc2.name]继续操干[npc.her][npc.asshole+]。"));
			}
			
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()),
					UtilText.returnStringAtRandom(
					"[npc.name]抬头看向[npc2.name]，咬住[npc.her]的[npc.lip]并且发出[npc.moan+]，[npc.spreadingHerLegs]并且顺从的放低[npc.herself]，做好了被进入的准备。",
					"[npc.Name]对着[npc2.name]的[npc.spreadsHerLegs]，将[npc.herself]作为接受一方呈现。",
					"[npc.name]收回[npc.feet]，向两侧[npc.spreadsHerLegs]，诱使[npc2.name]插入其中。",
					"[npc.Name][npc.spreadsHerLegs]，看向[npc2.name]并且咬住[npc.her]的[npc.lip]，因为[npc.she]在诱惑[npc2.herHim]来进入[npc.herHim]。"));
		}
	};
	
}
