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
public class SelfPenisAnus {

	public static final SexAction SELF_PENIS_ANUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return super.isBaseRequirementsMet()
					&& Main.sex.getCharacterPerformingAction().hasPenis()
                    && !this.isForbiddenArea(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)
                    && Main.sex.getCharacterPerformingAction().getPenisRawSizeValue()>5
					&& Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.PREHENSILE);
		}

		@Override
		public String getActionTitle() {
			return "开始肛交式自慰";
		}

		@Override
		public String getActionDescription() {
			return "弯曲[npc.her][npc.cock+]，开始用它自慰[npc.her]自己的[npc.ass]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]把[npc.her][npc.cock+]盘绕到[npc.her][npc.ass]，将[npc.cockHead+]在[npc.her][npc.asshole+]上逗弄，[npc.moaning]着，然后用力将其深深插入。",
					"[npc.Name][npc.verb(bend)]下身体，展现出惊人的柔韧性，带着愉悦发出一声[npc.moaning]，把[npc.her][npc.cock+]的[npc.cockHead+]抵住[npc.her][npc.asshole+]，然后[npc.verb(slide)]入[npc.her]紧致的[npc.ass]深处。",
					"[npc.Name]借助可卷曲的阴茎来考验自己的柔韧性，将[npc.her]悸动的[npc.cock+]穿入[npc.her][npc.ass]股缝之间，[npc.verb(thrust)]入[npc.her][npc.asshole+]深处，随着[npc.she]开始在[npc.ass]里自慰而发出一声[npc.a_moan+]。");
		}
	};

	public static final SexAction DOM_SELF_PENIS_ANUS_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {

		@Override
		public String getActionTitle() {
			return "温柔地肛交式自慰";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.her][npc.cock+]温柔地操[npc.her]自己的[npc.ass]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]慢慢地把[npc.her][npc.cock+]推入[npc.her][npc.asshole+]深处。",
					"[npc.Name]温柔地把[npc.her][npc.cock+]在[npc.her][npc.asshole+]里抽送进出，随着[npc.she]缓缓操[npc.her]自己的[npc.ass]而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]慢慢地将[npc.her][npc.cock+]深入[npc.her][npc.asshole]，在[npc.she] "
							+(!Main.sex.getCharacterPerformingAction().hasVagina()
									? "[npc.verb(use)]它开始温柔地按揉[npc.her]前列腺。"
									: "温柔地[npc.verb(fuck)]操[npc.her]自己的[npc.ass+]。"),
					"[npc.Name]专注于取悦[npc.her][npc.ass+]，开始温柔地把[npc.her][npc.cock+]在[npc.her][npc.asshole+]里抽送进出。");
		}

	};

	public static final SexAction DOM_SELF_PENIS_ANUS_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {

		@Override
		public String getActionTitle() {
			return "肛交式自慰";
		}

		@Override
		public String getActionDescription() {
			return "专注于用[npc.her][npc.cock+]操[npc.her]自己的[npc.ass]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]贪婪地把[npc.her][npc.cock+]推入[npc.her][npc.asshole+]深处。",
					"[npc.Name]把[npc.her][npc.cock+]在[npc.her][npc.asshole+]里抽送进出，随着[npc.she]有节奏地操[npc.her]自己的[npc.ass]而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]将[npc.her][npc.cock+]深入[npc.her][npc.asshole]，在[npc.she] "
							+(!Main.sex.getCharacterPerformingAction().hasVagina()
									? "[npc.verb(use)]它开始按揉[npc.her]前列腺。"
									: "稳步地[npc.verb(fuck)]操[npc.her]自己的[npc.ass+]。"),
					"[npc.Name]专注于取悦[npc.her][npc.ass+]，开始把[npc.her][npc.cock+]在[npc.her][npc.asshole+]里抽送进出。");
		}
	};

	public static final SexAction DOM_SELF_PENIS_ANUS_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "粗暴地肛交式自慰";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.her][npc.cock+]粗暴地操[npc.her]自己的[npc.ass]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]粗暴地把[npc.her][npc.cock+]深插进[npc.her][npc.asshole+]，然后开始猛操[npc.her]自己的[npc.ass]。",
					"[npc.Name]粗暴地把[npc.her][npc.cock+]在[npc.her][npc.asshole+]里抽送进出，随着[npc.she]无情地操[npc.her]自己的[npc.ass]而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]用力将[npc.her][npc.cock+]深入[npc.her][npc.asshole]，在[npc.she] "
							+(!Main.sex.getCharacterPerformingAction().hasVagina()
									? "[npc.verb(start)]粗暴地用它在[npc.her]前列腺上研磨。"
									: "粗暴地[npc.verb(fuck)]操[npc.her]自己的[npc.ass+]。"),
					"[npc.Name]专注于取悦[npc.her][npc.ass+]，开始粗暴地把[npc.her][npc.cock+]在[npc.her][npc.asshole+]里砸进砸出。");
		}

	};

	public static final SexAction SUB_SELF_PENIS_ANUS_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return "肛交式自慰";
		}

		@Override
		public String getActionDescription() {
			return "专注于用[npc.her][npc.cock+]操[npc.her]自己的[npc.ass]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]贪婪地把[npc.her][npc.cock+]推入[npc.her][npc.asshole+]深处。",
					"[npc.Name]把[npc.her][npc.cock+]在[npc.her][npc.asshole+]里抽送进出，随着[npc.she]有节奏地操[npc.her]自己的[npc.ass]而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]将[npc.her][npc.cock+]深入[npc.her][npc.asshole]，在[npc.she] "
							+(!Main.sex.getCharacterPerformingAction().hasVagina()
									? "[npc.verb(use)]它开始按揉[npc.her]前列腺。"
									: "稳步地[npc.verb(fuck)]操[npc.her]自己的[npc.ass+]。"),
					"[npc.Name]专注于取悦[npc.her][npc.ass+]，开始把[npc.her][npc.cock+]在[npc.her][npc.asshole+]里抽送进出。");
		}
	};

	public static final SexAction SUB_SELF_PENIS_ANUS_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "渴望地肛交式自慰";
		}

		@Override
		public String getActionDescription() {
			return "渴望地用[npc.her][npc.cock+]操[npc.her]自己的[npc.ass]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"一声[npc.A_moan+]从[npc.namePos][npc.lips+]间溢出，[npc.she]急切地把[npc.her][npc.cock+]深插进[npc.her][npc.asshole+]，然后开始拼命地操[npc.her]自己的[npc.ass]。",
					"[npc.Name]热情地把[npc.her][npc.cock+]在[npc.her][npc.asshole+]里抽送进出，随着[npc.she]疯狂地操[npc.her]自己的[npc.ass]而发出一连串愉悦的[npc.moans]。",
					"[npc.Name]拼命地将[npc.her][npc.cock+]深入[npc.her][npc.asshole]，在[npc.she] "
							+(!Main.sex.getCharacterPerformingAction().hasVagina()
									? "[npc.verb(start)]渴望地用它在[npc.her]前列腺上研磨。"
									: "渴望地[npc.verb(fuck)]操[npc.her]自己的[npc.ass+]。"),
					"[npc.Name]专注于取悦[npc.her][npc.ass+]，渴望地开始把[npc.her][npc.cock+]在[npc.her][npc.asshole+]里砸进砸出。");
		}
	};

	public static final SexAction SELF_PENIS_ANUS_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "停止肛交式自慰";
		}

		@Override
		public String getActionDescription() {
			return "停止用[npc.her][npc.cock+]操[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return "随着一声[npc.a_groan+]，[npc.name]把[npc.her][npc.cock+]从[npc.her][npc.asshole+]中抽出。";
		}
	};
}