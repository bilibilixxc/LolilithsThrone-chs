package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.4.2
 * @author Innoxia
 */
public class KneelingOral {
	
	public static final SexAction ORGASM_THIGH_SQUEEZE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)
					&& Main.sex.getCharacterPerformingAction().hasLegs()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(!Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			return super.getPriority();
		}
		
		@Override
		public String getActionTitle() {
			return "大腿夹头";
		}

		@Override
		public String getActionDescription() {
			return "你的大腿紧紧夹住[npc2.namePos]头部，高潮带来的强烈性奋感让你彻底失去了对身体的控制，整个人瘫倒在了[npc2.her]的脸上。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"伴随着销魂的[npc.a_moan+]，[npc.Name]感到一股无法抗拒的刺激感从腹股沟处升起，"
							+ "[npc.she]紧紧地将大腿夹在[npc2.namePos]头上"
					+ "伴随着[npc.a_moan+]，[npc.Her]身下的[npc.legs]止不住地开始颤抖。[npc.she]双腿一软，无力地倒向前方。"
					+ "[npc2.NameIsFull]猛地摔到在地，而后突然发现[npc.name]正坐在[npc2.her]脸上，"
						+ "随着[npc2.tongue+]强行在[npc.her][npc.pussy+]柔软的褶皱内的不断深入，[npc.her]开始忍不住发出一阵陶醉的尖叫声，下体也无意识地开始痉挛，紧紧夹住了[npc2.tongue+]。");
			
			if(Main.sex.getCharacterPerformingAction().hasPenis()) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ GenericOrgasms.getGenericPenisOrgasmDescription(
							this,
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this),
							OrgasmCumTarget.HAIR,
							this.getCondomFailure(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)),
							false));
			}
			
			UtilText.nodeContentSB.append("<br/><br/>"
					+ "在[npc2.namePos]脸上又来回磨蹭了一会儿，[npc.namePos]才从绝顶的高潮中稍稍清醒过来， [npc.she]拖着软绵绵的[npc.legs]，摇摇晃晃地站立起来，"
							+ "[npc.she]感觉到唾液与[npc.girlcum]混合着从[npc.pussy+]内汩汩流出，向[npc2.name]咧嘴一笑。");
			
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), false, UtilText.nodeContentSB.toString()).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), true).applyEffects();
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), false).isEndsSex();
		}
	};
	
}
