package com.lilithsthrone.LolificationProject.Archer.sex;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
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
public class SelfClitVagina {

	public static final SexAction SELF_CLIT_VAGINA_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return super.isBaseRequirementsMet()
                    && Main.sex.getCharacterPerformingAction().hasVagina()
                    && !this.isForbiddenArea(SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT)
                    && Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis()
                    || Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.PREHENSILE);
		}

		@Override
		public String getActionTitle() {
			return "开始用阴蒂自慰";
		}

		@Override
		public String getActionDescription() {
			return "弯曲[npc.her][npc.clit+]，开始用它在[npc.her]自己的[npc.pussy+]里自慰。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]展现出惊人的柔韧性弯下腰，把[npc.her][npc.clit+]的[npc.clitTip+]抵在[npc.her][npc.pussy+]上，然后慢慢将其推入，开始自慰。",
					"[npc.Name][npc.verb(bend)]下身体，展现出惊人的柔韧性，带着愉悦发出一声[npc.moaning]，把[npc.her][npc.clit+]顶端抵住[npc.her][npc.pussy+]，[npc.verb(slide)]入深处，然后[npc.verb(start)]操[npc.herself]。",
					"[npc.Name]借助增大的阴蒂来考验自己的柔韧性，将[npc.her]悸动的[npc.clit+]穿入[npc.her][npc.labia+]之间，[npc.verb(push)]入[npc.her][npc.pussy+]深处，随着[npc.she]开始自慰而发出一声[npc.a_moan+]。"));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction DOM_SELF_CLIT_VAGINA_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {

		@Override
		public String getActionTitle() {
			return "温柔地用阴蒂自慰";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.her][npc.clit+]温柔地操[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]慢慢地把[npc.her][npc.clit+]推入[npc.her][npc.pussy+]深处。",
					"[npc.Name]温柔地把[npc.her][npc.clit+]在[npc.her][npc.pussy+]里抽送进出，随着[npc.she]有节奏地自慰而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]慢慢地将[npc.her][npc.clit+]深入[npc.her][npc.pussy+]，在[npc.she]开始温柔地自慰时发出一声轻微的呜咽。");
		}
	};

	public static final SexAction DOM_SELF_CLIT_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {

		@Override
		public String getActionTitle() {
			return "用阴蒂自慰";
		}

		@Override
		public String getActionDescription() {
			return "专注于用[npc.her][npc.clit+]操[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]贪婪地把[npc.her][npc.clit+]推入[npc.her][npc.pussy+]深处。",
					"[npc.Name]把[npc.her][npc.clit+]在[npc.her][npc.pussy+]里抽送进出，随着[npc.she]有节奏地自慰而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]将[npc.her][npc.clit+]深入[npc.her][npc.pussy]，在[npc.she]开心地自慰时发出一声[npc.a_moan]。");
		}
	};

	public static final SexAction DOM_SELF_CLIT_VAGINA_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "粗暴地用阴蒂自慰";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.her][npc.clit+]粗暴地操[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]粗暴地把[npc.her][npc.clit+]深插进[npc.her][npc.pussy+]，然后开始猛操[npc.herself]。",
					"[npc.Name]粗暴地把[npc.her][npc.clit+]在[npc.her][npc.pussy+]里抽送进出，随着[npc.she]无情地自慰而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]将[npc.her][npc.clit+]深入[npc.her][npc.pussy]，在[npc.she]粗暴地研磨它时发出一声[npc.a_moan]。");
		}
	};

	public static final SexAction SUB_SELF_CLIT_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return "用阴蒂自慰";
		}

		@Override
		public String getActionDescription() {
			return "专注于用[npc.her][npc.clit+]操[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]贪婪地把[npc.her][npc.clit+]推入[npc.her][npc.pussy+]深处。",
					"[npc.Name]把[npc.her][npc.clit+]在[npc.her][npc.pussy+]里抽送进出，随着[npc.she]有节奏地自慰而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]将[npc.her][npc.clit+]深入[npc.her][npc.pussy]，在[npc.she]开心地自慰时发出一声[npc.a_moan]。");
		}
	};

	public static final SexAction SUB_SELF_CLIT_VAGINA_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "渴望地用阴蒂自慰";
		}

		@Override
		public String getActionDescription() {
			return "渴望地用[npc.her][npc.clit+]操[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]急切地把[npc.her][npc.clit+]深插进[npc.her][npc.pussy+]，然后开始拼命地自慰。",
					"[npc.Name]热情地把[npc.her][npc.clit+]在[npc.her][npc.pussy+]里抽送进出，随着[npc.she]疯狂地自慰而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]拼命地将[npc.her][npc.clit+]深入[npc.her][npc.pussy]，在[npc.she]渴望地研磨它时发出一声[npc.a_moan]。");
		}
	};

	public static final SexAction SELF_CLIT_VAGINA_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "停止用阴蒂自慰";
		}

		@Override
		public String getActionDescription() {
			return "停止用[npc.her][npc.clit+]操[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return "[npc.Name]发出一声[npc.a_moan]，把[npc.her][npc.clit+]从[npc.her][npc.pussy+]中抽出。";
		}
	};
}