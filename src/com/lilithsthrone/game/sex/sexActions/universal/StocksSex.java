package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.SMStocks;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.1
 * @author Innoxia
 */
public class StocksSex {

	public static final SexAction SWITCH_TO_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "移动到后面";
		}

		@Override
		public String getActionDescription() {
			return "移动到[npc2.name]身后，准备好插入[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			return "[npc.name]想趁着[npc2.name]被锁在颈手枷的时候干[npc2.her]，"
						+ "便[npc.step]向[npc2.herHim]的身后，将下体紧紧贴在了[npc2.her][npc2.ass+]。"
					+ "[npc.she]牢牢地抓住[npc2.her][npc2.hips+]，[npc.moanVerb]道，"
					+ "[npc.speech(乖乖的，在我草你的时候不要乱动！)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMStocks(
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.MOUTH),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotStocks.BEHIND_STOCKS)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStocks.LOCKED_IN_STOCKS))));
		}
	};

	public static final SexAction SWITCH_TO_BENEATH = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "四肢跪地";
		}

		@Override
		public String getActionDescription() {
			return "在[npc2.name]的身下四肢跪地，将你[npc.ass+]抵住[npc2.her]的下体，准备好被[npc2.herHim]操了。";
		}

		@Override
		public String getDescription() {
			return "[npc.Name]想被[npc2.name]插入，于是四肢跪地，趴在[npc2.her]的颈手枷之下。"
					+ "挪动身子摆到了个舒服的姿势，抬起[npc.hips+]，将[npc.ass+]抵在了[npc2.her]的下体上。"
					+ "[npc.Name]带着激动的[npc.moan]高喊道，"
					+ "[npc.speech(你赚到了哦！我正准备让你干我呢！)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMStocks(
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.MOUTH),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotStocks.BENEATH_STOCKS)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStocks.LOCKED_IN_STOCKS))));
		}
	};
	
	public static final SexAction SWITCH_TO_GIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.PERFORMING_ORAL_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "提供口交";
		}

		@Override
		public String getActionDescription() {
			return "跪在[npc2.name]后面给[npc2.herHim]口交。";
		}

		@Override
		public String getDescription() {
			return "[npc.name]决定给[npc2.name]口交，便跪在[npc2.herHim]后面。"
					+ "把嘴巴贴近[npc2.her]的下体，[npc.moanVerb]起来，"
					+ "[npc.speech(你会喜欢的！)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMStocks(
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.MOUTH),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotStocks.PERFORMING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStocks.LOCKED_IN_STOCKS))));
		}
	};
	
	public static final SexAction SWITCH_TO_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.RECEIVING_ORAL_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "挪到前面";
		}

		@Override
		public String getActionDescription() {
			return "决定使用[npc2.namePos]的嘴。";
		}

		@Override
		public String getDescription() {
			return "[npc.Name]决定要使用[npc2.namePos]的嘴巴，于是先退开，走到了[npc2.her][npc2.face]的正前方。"
					+ "把下体贴近[npc2.her]的嘴巴，[npc.moanVerb]起来，"
					+ "[npc.speech(你会喜欢的！)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMStocks(
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.MOUTH),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotStocks.RECEIVING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStocks.LOCKED_IN_STOCKS))));
		}
	};

}
