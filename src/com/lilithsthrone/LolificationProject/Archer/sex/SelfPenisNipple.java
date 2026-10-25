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
public class SelfPenisNipple {

	public static final SexAction SELF_PENIS_NIPPLE_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return super.isBaseRequirementsMet()
					&& Main.sex.getCharacterPerformingAction().hasPenis()
                    && Main.game.isNipplePenEnabled()
                    && Main.sex.getCharacterPerformingAction().isBreastFuckableNipplePenetration()
                    && !this.isForbiddenArea(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)
                    && (Main.sex.getCharacterPerformingAction().getPenisRawSizeValue()>15
                    || Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.HYPERMOBILITY)
                    || Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_1));
		}

		@Override
		public String getActionTitle() {
			return "自我肏乳";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.her] [npc.cock+]插入[npc.her]自己的[npc.nipples+]进行操弄。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name][npc.verb(Guiding)] 把[npc.her] [npc.cock+]引向[npc.her] [npc.breasts+]，用[npc.cockHead+]在[npc.her] [npc.nipples]上[npc.verb(tease)] 挑弄，愉悦地[npc.moaning]，随后猛地深插进去。",
					"[npc.Name]以令人惊叹的柔韧性[npc.verb(bend)] 向前弯下身体，当[npc.she][npc.verb(force)] 把[npc.her] [npc.cock+]深深插入一个诱人的[npc.nipple(true)]时，愉悦地[npc.moaning]。",
					"[npc.name]把[npc.her] [npc.cock+]的[npc.cockHead+]滑到[npc.her] [npc.breasts+]前，[npc.verb(thrust)] 将它深深插入一个诱人的[npc.nipple(true)]，当[npc.she][npc.verb(start)]操弄[npc.her]自己的[npc.breasts]时发出一声[npc.a_moan+]。",
					"[npc.Name]急切地[npc.verb(thrust)] 把[npc.her] [npc.cock+]深深插入一个饥渴的[npc.nipple(true)]，当[npc.she][npc.verb(start)]操弄[npc.her]自己的[npc.breasts]时，发出一连串的[npc.moans+]。"));

			switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" 一小股[npc.milk]从[npc.her] [npc.nipple+]处沿着[npc.her] [npc.cock+]渗了出来。");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" 一小股[npc.milk]从[npc.her] [npc.nipple+]处沿着[npc.her] [npc.cock+]喷渗了出来。");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" 一股[npc.milk]从[npc.her] [npc.nipple+]处沿着[npc.her] [npc.cock+]流了出来。");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.milk]开始从[npc.her] [npc.nipple+]处沿着[npc.her] [npc.cock+]涌出，滑落到[npc.her] [npc.breasts]上。");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [npc.Milk]开始从[npc.her] [npc.nipple+]处沿着[npc.her] [npc.cock+]细细地滴淌出来。");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [npc.Milk]开始从[npc.her] [npc.nipple+]处源源不断地涌出，滑落到[npc.her] [npc.breasts]上。");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [npc.Milk]开始从[npc.her] [npc.nipple+]处汹汹涌出，迅速浸湿[npc.her] [npc.breasts]。");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastStoredMilk(-10);
		}

	};

	public static final SexAction DOM_SELF_PENIS_NIPPLE_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {

		@Override
		public String getActionTitle() {
			return "温柔地自我肏乳";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.her] [npc.cock+]温柔地操弄[npc.her] [npc.nipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos] [npc.lips+]间传出一声[npc.A_moan+]，因为[npc.she]正缓慢地[npc.verb(push)] 把[npc.her] [npc.cock+]深深插入[npc.her] [npc.nipple+]中。",
					"[npc.name]温柔地把[npc.her] [npc.cock+]在[npc.her] [npc.nipple+]里来回抽送，[npc.verb(start)] 发出接连不断的愉悦[npc.moans]，同时[npc.she]有节奏地[npc.verb(fuck)] [npc.her] [npc.breast+]。",
					"[npc.name]缓慢地把[npc.her] [npc.cock+]深深插入[npc.her] [npc.nipple(true)]中，当[npc.she][npc.verb(start)]温柔地在[npc.her] [npc.breast+]里来回抽送时[npc.verb(let)] 发出一声轻微的呜咽。",
					"[npc.name]专注于取悦[npc.her]可以插入的[npc.breasts]，[npc.verb(start)] 温柔地把[npc.her] [npc.cock+]在[npc.her]其中一个[npc.nipples+]里来回抽送。");
		}

	};

	public static final SexAction DOM_SELF_PENIS_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {

		@Override
		public String getActionTitle() {
			return "自我肏乳";
		}

		@Override
		public String getActionDescription() {
			return "专注于用[npc.her] [npc.cock+]操弄[npc.her] [npc.nipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos] [npc.lips+]间传出一声[npc.A_moan+]，因为[npc.she]正贪婪地[npc.verb(push)] 把[npc.her] [npc.cock+]深深插入[npc.her] [npc.nipple+]中。",
					"[npc.name]把[npc.her] [npc.cock+]在[npc.her] [npc.nipple+]里来回抽送，[npc.verb(start)] 发出接连不断的愉悦[npc.moans]，同时[npc.she]有节奏地[npc.verb(fuck)] [npc.her] [npc.breast+]。",
					"[npc.name]把[npc.her] [npc.cock+]深深插入[npc.her]可以插入的[npc.nipple(true)]中，[npc.verb(let)] 发出一声[npc.a_moan]，同时[npc.she][npc.verb(start)] 把它在[npc.her] [npc.breast+]里来回抽送。",
					"[npc.name]专注于取悦[npc.her] [npc.breasts+]，[npc.verb(start)] 把[npc.her] [npc.cock+]在[npc.her]其中一个[npc.nipples+]里来回抽送。");
		}

	};

	public static final SexAction DOM_SELF_PENIS_NIPPLE_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "粗暴地自我肏乳";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.her] [npc.cock+]粗暴地操弄[npc.her] [npc.nipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos] [npc.lips+]间传出一声[npc.A_moan+]，因为[npc.she]正粗暴地[npc.verb(slam)] 把[npc.her] [npc.cock+]深深插入[npc.her] [npc.nipple+]中，随后开始快速操弄[npc.her] [npc.breast(true)]。",
					"[npc.name]粗暴地把[npc.her] [npc.cock+]在[npc.her] [npc.nipple+]里来回抽送，[npc.verb(start)] 发出接连不断的愉悦[npc.moans]，同时[npc.she]有节奏地[npc.verb(fuck)] [npc.her] [npc.breast+]。",
					"[npc.name]用力地把[npc.her] [npc.cock+]深深插入[npc.her]可以插入的[npc.nipple(true)]中，当[npc.she][npc.verb(start)]粗暴地在[npc.her] [npc.breast+]里研磨搅动时[npc.verb(let)] 发出一声[npc.a_moan]。",
					"[npc.name]专注于取悦[npc.her] [npc.breasts+]，[npc.verb(start)] 粗暴地把[npc.her] [npc.cock+]在[npc.her]其中一个[npc.nipples+]里猛烈抽送。");
		}
	};

	public static final SexAction SUB_SELF_PENIS_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return "自我肏乳";
		}

		@Override
		public String getActionDescription() {
			return "专注于用[npc.her] [npc.cock+]操弄[npc.her] [npc.nipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos] [npc.lips+]间传出一声[npc.A_moan+]，因为[npc.she]正贪婪地[npc.verb(push)] 把[npc.her] [npc.cock+]深深插入[npc.her] [npc.nipple+]中。",
					"[npc.name]把[npc.her] [npc.cock+]在[npc.her] [npc.nipple+]里来回抽送，[npc.verb(start)] 发出接连不断的愉悦[npc.moans]，同时[npc.she]有节奏地[npc.verb(fuck)] [npc.her] [npc.breast+]。",
					"[npc.name]把[npc.her] [npc.cock+]深深插入[npc.her]可以插入的[npc.nipple(true)]中，[npc.verb(let)] 发出一声[npc.a_moan]，同时[npc.she][npc.verb(start)] 把它在[npc.her] [npc.breast+]里来回抽送。",
					"[npc.name]专注于取悦[npc.her] [npc.breasts+]，[npc.verb(start)] 把[npc.her] [npc.cock+]在[npc.her]其中一个[npc.nipples+]里来回抽送。");
		}
	};

	public static final SexAction SUB_SELF_PENIS_NIPPLE_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "渴望地自我肏乳";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.her] [npc.cock+]渴望地操弄[npc.her] [npc.nipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos] [npc.lips+]间传出一声[npc.A_moan+]，因为[npc.she]正急切地[npc.verb(slam)] 把[npc.her] [npc.cock+]深深插入[npc.her] [npc.nipple+]中，随后开始不顾一切地操弄[npc.her] [npc.breast(true)]。",
					"[npc.name]热情地把[npc.her] [npc.cock+]在[npc.her] [npc.nipple+]里来回抽送，[npc.verb(start)] 发出接连不断的愉悦[npc.moans]，同时[npc.she]疯狂地[npc.verb(fuck)] [npc.her] [npc.breast+]。",
					"[npc.name]不顾一切地把[npc.her] [npc.cock+]深深插入[npc.her]可以插入的[npc.nipple(true)]中，当[npc.she][npc.verb(start)]急切地在[npc.her] [npc.breast+]里研磨搅动时，[npc.verb(let)] 发出一声[npc.a_moan]。",
					"[npc.name]专注于取悦[npc.her] [npc.breasts+]，急切地[npc.verb(start)] 把[npc.her] [npc.cock+]在[npc.her]其中一个[npc.nipples+]里猛烈抽送。");
		}
	};

	public static final SexAction SELF_PENIS_NIPPLE_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "停止自我肏乳";
		}

		@Override
		public String getActionDescription() {
			return "停止用[npc.her] [npc.cock+]操弄[npc.her] [npc.nipple+]。";
		}

		@Override
		public String getDescription() {
			return "发出一声满足的[npc.moan]，[npc.name][npc.verb(slide)] 把[npc.her] [npc.cock+]从[npc.her] [npc.nipple+]中抽出。";
		}
	};
}