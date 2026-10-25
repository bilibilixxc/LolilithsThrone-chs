package com.lilithsthrone.LolificationProject.Archer.sex;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.effects.Perk;
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
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.8.5
 * @version 0.4.8.5
 */
public class SelfClitAnus {

	public static final SexAction SELF_CLIT_ANUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return super.isBaseRequirementsMet()
                    && Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis()
                    && !this.isForbiddenArea(SexAreaOrifice.ANUS, SexAreaPenetration.CLIT)
                    && Main.sex.getCharacterPerformingAction().getVaginaRawClitorisSizeValue()>10
                    || Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.PREHENSILE);
		}

		@Override
		public String getActionTitle() {
			return "开始阴蒂肛交自慰";
		}

		@Override
		public String getActionDescription() {
			return "[npc.her]把[npc.clit+]弯曲，然后开始在[npc.ass]里自慰。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]以一种令人惊叹的柔韧姿态弯下[npc.her][npc.clit+]，把[npc.her][npc.clitTip+]抵在[npc.her][npc.asshole+]上，然后慢慢将其推入，开始了对自己的抽送。",
					"[npc.Name]展现出惊人的柔韧性，弯曲[npc.her][npc.clit+]，[npc.moaning]着将[npc.her][npc.clit+]的顶端抵住[npc.her][npc.asshole+]，[npc.verb(slide)]至深处，然后[npc.verb(start)]抽插[npc.herself]。",
					"[npc.Name]借助增大的阴蒂来考验自己的柔韧性，将[npc.her]悸动的[npc.clit+]引导至[npc.her][npc.ass]股缝之间，并[npc.verb(push)]进[npc.her][npc.asshole+]深处，随着[npc.she]开始自慰而发出一声[npc.a_moan+]。"));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction DOM_SELF_CLIT_ANUS_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {

		@Override
		public String getActionTitle() {
			return "温柔地用阴蒂自慰后穴";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.her][npc.clit+]在[npc.her][npc.ass]里温柔地自慰。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]缓慢地[npc.verb(push)]着[npc.her][npc.clit+]深入[npc.her][npc.asshole+]。",
					"[npc.Name]轻轻地把[npc.her][npc.clit+]在[npc.her][npc.asshole+]里抽送进出，随着[npc.she]有节奏地自慰而发出一连串愉悦的[npc.moans]。",
					"当[npc.name]缓慢地将[npc.her][npc.clit+]深入[npc.her][npc.asshole+]时，[npc.she][npc.verb(let)]出一声轻微的呜咽，然后开始温柔地自慰。");
		}
	};

	public static final SexAction DOM_SELF_CLIT_ANUS_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {

		@Override
		public String getActionTitle() {
			return "用阴蒂自慰后穴";
		}

		@Override
		public String getActionDescription() {
			return "专注于用[npc.her][npc.clit+]在[npc.her][npc.ass]里自慰。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]贪婪地[npc.verb(push)]着[npc.her][npc.clit+]深入[npc.her][npc.asshole+]。",
					"[npc.Name]把[npc.her][npc.clit+]在[npc.her][npc.asshole+]里抽送进出，随着[npc.she]有节奏地自慰而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]将[npc.her][npc.clit+]深入[npc.her][npc.asshole+]，在[npc.she]开心地自慰时[npc.verb(let)]出一声[npc.a_moan]。");
		}
	};

	public static final SexAction DOM_SELF_CLIT_ANUS_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "粗暴地用阴蒂自慰后穴";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.her][npc.clit+]在[npc.her][npc.ass]里粗暴地自慰。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]粗暴地[npc.verb(slam)]着[npc.her][npc.clit+]深入[npc.her][npc.asshole+]，然后开始猛烈地自慰。",
					"[npc.Name]粗暴地把[npc.her][npc.clit+]在[npc.her][npc.asshole+]里抽送进出，随着[npc.she]无情地自慰而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]将[npc.her][npc.clit+]深入[npc.her][npc.asshole+]，在[npc.she]粗暴地研磨它时[npc.verb(let)]出一声[npc.a_moan]。");
		}
	};

	public static final SexAction SUB_SELF_CLIT_ANUS_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return "用阴蒂自慰后穴";
		}

		@Override
		public String getActionDescription() {
			return "专注于用[npc.her][npc.clit+]在[npc.her][npc.ass]里自慰。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she][npc.verb(push)]着[npc.her][npc.clit+]深入[npc.her][npc.asshole+]。",
					"[npc.Name]把[npc.her][npc.clit+]在[npc.her][npc.asshole+]里抽送进出，随着[npc.she]有节奏地自慰而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]将[npc.her][npc.clit+]深入[npc.her][npc.asshole+]，在[npc.she]自慰时[npc.verb(let)]出一声[npc.a_moan]。");
		}
	};

	public static final SexAction SUB_SELF_CLIT_ANUS_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "渴望地用阴蒂自慰后穴";
		}

		@Override
		public String getActionDescription() {
			return "渴望地用[npc.her][npc.clit+]在[npc.her][npc.ass]里自慰。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]急切地[npc.verb(slam)]着[npc.her][npc.clit+]深入[npc.her][npc.asshole+]，然后开始拼命地自慰。",
					"[npc.Name]热切地把[npc.her][npc.clit+]在[npc.her][npc.asshole+]里抽送进出，随着[npc.she]疯狂地自慰而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]拼命地将[npc.her][npc.clit+]深入[npc.her][npc.asshole+]，在[npc.she]急切地研磨它时[npc.verb(let)]出一声[npc.a_moan]。");
		}
	};

	public static final SexAction SELF_CLIT_ANUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "停止用阴蒂自慰后穴";
		}

		@Override
		public String getActionDescription() {
			return "停止用[npc.her][npc.clit+]在[npc.her][npc.ass]里自慰。";
		}

		@Override
		public String getDescription() {
			return "[npc.Name]发出一声[npc.a_moan]，从[npc.her][npc.asshole+]中抽出[npc.her][npc.clit+]。";
		}
	};
}