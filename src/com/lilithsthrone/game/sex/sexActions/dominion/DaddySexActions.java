package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.SMDaddyDinnerOral;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.3.10
 * @version 0.3.3.10
 * @author Innoxia
 */
public class DaddySexActions {

	public static final SexAction ORAL_STOP_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "服务生走近";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction() instanceof Daddy)
					&& Main.sex.getSexManager().isPartnerWantingToStopSex(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getSexManager() instanceof SMDaddyDinnerOral);
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			boolean lilayaPresent = Main.game.getCharactersPresent().contains(Main.game.getNpc(Lilaya.class));
			return "[daddy.she]已经心满意足，却忽然对周围的环境警觉起来，[npc.she]俯下身，对桌子下面的你"
					+ (lilayaPresent?"和莉莱雅":"")
					+"低声道，"
					+ "[daddy.speechNoEffects(我想有个服务员知道发生了什么！坐起来！)]"
					+ "<br/><br/>"
					+ "很遗憾[daddy.name]现在就想停下，"+(lilayaPresent?"你们俩":"你")+"闷闷不乐地从桌子下滑回了 "+(lilayaPresent?"座位":"座位")+"，"
							+ "你向[daddy.herHim]调皮一笑，舔了舔嘴唇。"
					+ "果然，你做完淫荡表演，看见一个服务员向你走来……";
		}

		@Override
		public void applyEffects() {
			Main.sex.addRemoveEndSexAffection(Main.game.getNpc(Lilaya.class));
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction GENERIC_ORGASM_BACK = new SexAction(GenericOrgasms.GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(Main.game.getNpc(Lilaya.class))
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LYING_DOWN);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在后背上";
				}
				return "外射(后背)";
			}
			return "射在背上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了[npc2.namePos]背上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BACK, false).getDescription();
		}

		@Override
		public void applyEffects() {
			GenericOrgasms.applyGenericPullOutEffects(this, OrgasmCumTarget.BACK);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.BACK);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BACK, true).isEndsSex();
		}
	};
	
	
	public static final SexAction GENERIC_ORGASM_SELF_STOMACH = new SexAction(GenericOrgasms.GENERIC_ORGASM_FLOOR) {

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(Main.game.getNpc(Lilaya.class))
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LYING_DOWN);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在肚子上";
				}
				return "外射(自己的肚子)";
			}
			return "射在你的肚子上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了自己肚子上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_STOMACH, false).getDescription();
		}

		@Override
		public void applyEffects() {
			GenericOrgasms.applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_STOMACH);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.STOMACH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return  Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_STOMACH, true).isEndsSex();
		}
	};
}
