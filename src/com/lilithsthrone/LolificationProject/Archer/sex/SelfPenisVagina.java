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
public class SelfPenisVagina {

	public static final SexAction SELF_PENIS_VAGINA_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return super.isBaseRequirementsMet()
					&& Main.sex.getCharacterPerformingAction().hasPenis()
					&& Main.sex.getCharacterPerformingAction().hasVagina()
                    && !this.isForbiddenArea(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)
                    && Main.sex.getCharacterPerformingAction().getPenisRawSizeValue()>5
					&& Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.PREHENSILE);
		}

		@Override
		public String getActionTitle() {
			return "开始自我插入";
		}

		@Override
		public String getActionDescription() {
			return "弯折[npc.her] [npc.cock+]并开始操弄[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.name]展现出令人惊叹的柔韧性弯下腰身，把[npc.her] [npc.cock+]的[npc.cockHead+]对准[npc.her] [npc.pussy+]，随后缓慢地将它推入其中，开始操弄[npc.herself]。",
					"[npc.Name][npc.verb(bend)] 下腰身以展现令人惊叹的柔韧性，[npc.she]愉悦地[npc.moaning]，把[npc.her] [npc.cock+]的顶端[npc.verb(press)] 在[npc.her] [npc.pussy+]上，[npc.verb(slide)] 深入[npc.herself]，然后[npc.verb(start)] 操弄[npc.herself]。",
					"[npc.name]利用[npc.her]可缠绕的阳具来考验自己的柔韧性，把胀动的[npc.cock+]从双腿间[npc.verb(guide)] 引导到湿润的[npc.her] [npc.pussy+]，并[npc.verb(push)] 深深推入其中，当[npc.she][npc.verb(start)]操弄[npc.herself]时发出[npc.a_moan+]。");
		}
	};

	public static final SexAction DOM_SELF_PENIS_VAGINA_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {

		@Override
		public String getActionTitle() {
			return "温柔地自我插入";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.her] [npc.cock+]温柔地操弄[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos] [npc.lips+]间传出一声[npc.A_moan+]，因为[npc.she]正缓慢地[npc.verb(push)] 把[npc.her] [npc.cock+]深深插入[npc.her] [npc.pussy+]中。",
					"[npc.name]温柔地把[npc.her] [npc.cock+]在[npc.her] [npc.pussy+]里来回抽送，[npc.verb(start)] 发出接连不断的愉悦[npc.moans]，同时[npc.she]有节奏地[npc.verb(fuck)] 自己。",
					"[npc.name]缓慢地把[npc.her] [npc.cock+]深深插入[npc.her] [npc.pussy+]中，当[npc.she][npc.verb(start)]温柔地操弄[npc.herself]时[npc.verb(let)] 发出一声轻微的呜咽。",
					"[npc.name]专注于取悦[npc.herself]，[npc.verb(start)] 温柔地把[npc.her] [npc.cock+]在[npc.her] [npc.pussy+]中来回抽送。");
		}
	};

	public static final SexAction DOM_SELF_PENIS_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {

		@Override
		public String getActionTitle() {
			return "自我插入";
		}

		@Override
		public String getActionDescription() {
			return "专注于用[npc.her] [npc.cock+]操弄[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos] [npc.lips+]间传出一声[npc.A_moan+]，因为[npc.she]正贪婪地[npc.verb(push)] 把[npc.her] [npc.cock+]深深插入[npc.her] [npc.pussy+]中。",
					"[npc.name]把[npc.her] [npc.cock+]在[npc.her] [npc.pussy+]里来回抽送，[npc.verb(start)] 发出接连不断的愉悦[npc.moans]，同时[npc.she]有节奏地[npc.verb(fuck)] 自己。",
					"[npc.name]把[npc.her] [npc.cock+]深深插入[npc.her] [npc.pussy]中，发出一声[npc.a_moan]，然后开心地操弄[npc.herself]。",
					"[npc.name]专注于双腿间[npc.sheIs]带给[npc.herself]的欢愉，[npc.verb(start)] 把[npc.her] [npc.cock+]在[npc.her] [npc.pussy+]中来回抽送。");
		}
	};

	public static final SexAction DOM_SELF_PENIS_VAGINA_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "粗暴地自我插入";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.her] [npc.cock+]粗暴地操弄[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos] [npc.lips+]间传出一声[npc.A_moan+]，因为[npc.she]正粗暴地[npc.verb(slam)] 把[npc.her] [npc.cock+]深深插入[npc.her] [npc.pussy+]中，接着开始粗暴地操弄[npc.herself]。",
					"[npc.name]粗暴地把[npc.her] [npc.cock+]在[npc.her] [npc.pussy+]里来回抽送，[npc.verb(start)] 发出接连不断的愉悦[npc.moans]，同时[npc.she]毫不留情地[npc.verb(fuck)] 自己。",
					"[npc.name]把[npc.her] [npc.cock+]深深插入[npc.her] [npc.pussy]中，发出一声[npc.a_moan]，同时[npc.she]开始粗暴地在[npc.herself]体内研磨搅动。",
					"[npc.name]专注于双腿间[npc.sheIs]带给[npc.herself]的欢愉，[npc.verb(start)] 粗暴地把[npc.her] [npc.cock+]在[npc.her] [npc.pussy+]中猛烈抽送。");
		}
	};

	public static final SexAction SUB_SELF_PENIS_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return "自我插入";
		}

		@Override
		public String getActionDescription() {
			return "专注于用[npc.her] [npc.cock+]操弄[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos] [npc.lips+]间传出一声[npc.A_moan+]，因为[npc.she]正贪婪地[npc.verb(push)] 把[npc.her] [npc.cock+]深深插入[npc.her] [npc.pussy+]中。",
					"[npc.name]把[npc.her] [npc.cock+]在[npc.her] [npc.pussy+]里来回抽送，[npc.verb(start)] 发出接连不断的愉悦[npc.moans]，同时[npc.she]有节奏地[npc.verb(fuck)] 自己。",
					"[npc.name]把[npc.her] [npc.cock+]深深插入[npc.her] [npc.pussy]中，发出一声[npc.a_moan]，然后开心地操弄[npc.herself]。",
					"[npc.name]专注于双腿间[npc.sheIs]带给[npc.herself]的欢愉，[npc.verb(start)] 把[npc.her] [npc.cock+]在[npc.her] [npc.pussy+]中来回抽送。");
		}
	};

	public static final SexAction SUB_SELF_PENIS_VAGINA_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "渴望地自我插入";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.her] [npc.cock+]渴望地操弄[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos] [npc.lips+]间传出一声[npc.A_moan+]，因为[npc.she]正急切地[npc.verb(slam)] 把[npc.her] [npc.cock+]深深插入[npc.her] [npc.pussy+]中，接着开始不顾一切地操弄[npc.herself]。",
					"[npc.name]热情地把[npc.her] [npc.cock+]在[npc.her] [npc.pussy+]里来回抽送，[npc.verb(start)] 发出接连不断的愉悦[npc.moans]，同时[npc.she]疯狂地[npc.verb(fuck)] 自己。",
					"[npc.name]不顾一切地把[npc.her] [npc.cock+]深深插入[npc.her] [npc.pussy]中，发出一声[npc.a_moan]，同时[npc.she]急切地在[npc.herself]体内研磨搅动。",
					"[npc.name]专注于双腿间[npc.sheIs]带给[npc.herself]的欢愉，急切地[npc.verb(start)] 把[npc.her] [npc.cock+]在[npc.her] [npc.pussy+]中来回抽送。");
		}
	};

	public static final SexAction SELF_PENIS_VAGINA_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "停止自我插入";
		}

		@Override
		public String getActionDescription() {
			return "停止用[npc.her] [npc.cock+]操弄[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return "发出一声[npc.a_moan]，[npc.name][npc.verb(slide)] 把[npc.her] [npc.cock+]从[npc.her] [npc.pussy+]中抽出。";
		}
	};
}