package com.lilithsthrone.LolificationProject.Archer.sex;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
public class SelfTongueNipple {

	public static final SexAction SELF_TONGUE_NIPPLE_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition().isSelfOralAvailable(Main.sex.getCharacterPerformingAction())
                    && Main.game.isNipplePenEnabled()
                    && Main.sex.getCharacterPerformingAction().isBreastFuckableNipplePenetration()
                    && !this.isForbiddenArea(SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE)
                    && Main.sex.getCharacterPerformingAction().getTongueLengthValue()>5;
		}
		@Override
		public String getActionTitle() {
			return "开始自舔乳头";
		}
		@Override
		public String getActionDescription() {
			return "把[npc.tongue+]伸入[npc.nipple+]，开始舔弄[npc.her]自己[npc.breasts+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]向前倾斜，展现出十足的柔韧性，[npc.verb(bend)]下身体，",
					"[npc.Name]轻松地弯下身子，",
					"把脸凑到[npc.her][npc.breasts+][npc.name]，"));

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name][npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.nipples]."
							+ " 在[npc.her] [npc.nipple+]上落下一串轻柔的亲吻后，[npc.she]开始缓慢而坚定地把[npc.her] [npc.tongue+]滑入[npc.her] [npc.nipple+]中。",

							"[npc.Name][npc.verb(plant)] 一串轻柔的亲吻在[npc.her] [npc.breasts+]上，"
							+ " 然后[npc.She][npc.verb(give)] [npc.her] [npc.nipple+]一记长而湿润的舔舐，随后温柔地把[npc.her] [npc.tongue+]深深探入其中。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地[npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.nipples]."
							+ " 在[npc.her] [npc.nipple+]上落下一连串热情的亲吻后，[npc.she]不顾一切地把[npc.her] [npc.tongue+]滑入[npc.her] [npc.nipple+]中。",

							"[npc.Name][npc.verb(plant)] 一串热情的亲吻在[npc.her] [npc.breasts+]上，"
							+ " 然后[npc.She][npc.verb(give)] [npc.her] [npc.nipple+]一记饥渴的舔舐，随后贪婪地把[npc.her] [npc.tongue+]深深探入其中。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗暴地[npc.verb(grind)] [npc.her] [npc.lips+] against [npc.her] [npc.nipples]."
							+ " 在[npc.her] [npc.nipple+]上落下一连串用力的亲吻后，[npc.she]贪婪地把[npc.her] [npc.tongue+]滑入[npc.her] [npc.nipple+]中。",

							"[npc.Name][npc.verb(plant)] 一串用力的亲吻在[npc.her] [npc.breasts+]上，"
							+ " 然后[npc.She][npc.verb(give)] [npc.her] [npc.nipple+]一记粗暴的舔舐，随后贪婪地把[npc.her] [npc.tongue+]深深探入其中。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地[npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.nipples]."
							+ " 在[npc.her] [npc.nipple+]上落下一连串热情的亲吻后，[npc.she]满怀激情地把[npc.her] [npc.tongue+]滑入[npc.her] [npc.nipple+]中。",

							"[npc.Name][npc.verb(plant)] 一串热情的亲吻在[npc.her] [npc.breasts+]上，"
							+ " 然后[npc.She][npc.verb(give)] [npc.her] [npc.nipple+]一记饥渴的舔舐，随后贪婪地把[npc.her] [npc.tongue+]深深探入其中。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name][npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.nipples]."
							+ " 在[npc.her] [npc.nipple+]上落下一串亲吻后，[npc.she]把[npc.her] [npc.tongue+]滑入[npc.her] [npc.nipple+]中。",

							"[npc.Name][npc.verb(plant)] 一串亲吻在[npc.her] [npc.breasts+]上，"
							+ " 然后[npc.She][npc.verb(give)] [npc.her] [npc.nipple+]一记湿润的舔舐，随后把[npc.her] [npc.tongue+]深深探入其中。"));
					break;
				default:
					break;
			}
            switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
                case ONE_TRICKLE:
                    UtilText.nodeContentSB.append(" 一小股[npc.milk]顺着[npc.her] [npc.nipple+]与[npc.her] [npc.tongue+][npc.verb(leak)]渗出。");
                    break;
                case TWO_SMALL_AMOUNT:
                    UtilText.nodeContentSB.append(" 少量[npc.milk]顺着[npc.her] [npc.nipple+]与[npc.her] [npc.tongue+][npc.verb(leak)]渗出。");
                    break;
                case THREE_DECENT_AMOUNT:
                    UtilText.nodeContentSB.append(" 一股细流般的[npc.milk]从[npc.her] [npc.nipple+]与[npc.her] [npc.tongue+]之间[npc.verb(run)]出。");
                    break;
                case FOUR_LARGE_AMOUNT:
                    UtilText.nodeContentSB.append(" [npc.Her] [npc.milk]开始从[npc.her] [npc.nipple+]与[npc.her] [npc.tongue+]之间流出，顺着[npc.her] [npc.breasts+]淌下。");
                    break;
                case FIVE_VERY_LARGE_DROOLING:
                    UtilText.nodeContentSB.append(" [npc.Milk]开始以小股细流从[npc.her] [npc.nipple+]与[npc.her] [npc.tongue+]之间流淌而出。");
                    break;
                case SIX_EXTREME_AMOUNT_DRIPPING:
                    UtilText.nodeContentSB.append(" [npc.Milk]开始源源不断地从[npc.her] [npc.nipple+]涌出，顺着[npc.her] [npc.breasts+]流下。");
                    break;
                case SEVEN_MONSTROUS_AMOUNT_POURING:
                    UtilText.nodeContentSB.append(" [npc.Milk]开始大量地从[npc.her] [npc.nipple+]喷涌而出，迅速浸湿[npc.her] [npc.breasts+]。");
                    break;
                default:
                    break;
            }
			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_NIPPLE_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "温柔地自舔乳头";
		}
		@Override
		public String getActionDescription() {
			return "温柔地舔弄[npc.her]自己[npc.nipples+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]把[npc.her][npc.tongue+]尽可能深地温柔探入[npc.her][npc.nipple+]中，"
							+ "把[npc.her][npc.lips+]紧贴在[npc.her][npc.breasts+]上，然后发出了一声闷闷的[npc.moan]。",
					"[npc.Name]把[npc.her][npc.tongue+]从[npc.her][npc.nipple+]中抽出，开始温柔地亲吻舔弄[npc.her][npc.nipples+]，"
							+ "随后再次向前探身，把[npc.her][npc.tongue]缓缓滑入[npc.her][npc.nipple+]中。",
					"[npc.Name]抽出[npc.her][npc.tongue+]，开始慢慢地亲吻摩擦[npc.her][npc.breasts+]，"
							+ "随后再次温柔地把[npc.her][npc.tongue]深深探入[npc.her][npc.nipple+]中。"));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_NIPPLE_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "自舔乳头";
		}
		@Override
		public String getActionDescription() {
			return "继续把[npc.tongue]顶入[npc.her]自己的[npc.nipple+]中。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]急不可耐地把[npc.her][npc.tongue+]尽可能深地探入[npc.her][npc.nipple+]中，"
							+ "把[npc.her][npc.lips+]紧贴在[npc.her][npc.breasts+]上，然后发出了一声闷闷的[npc.moan]。",
					"[npc.Name]把[npc.her][npc.tongue+]从[npc.her][npc.nipple+]中抽出，开始急切地亲吻舔弄[npc.her][npc.nipples+]，"
							+ "随后再次向前探身，贪婪地把[npc.her][npc.tongue]滑入[npc.her][npc.nipple+]中。",
					"[npc.Name]抽出[npc.her][npc.tongue+]，开始开心地亲吻摩擦[npc.her][npc.breasts+]，"
							+ "随后再次热情地把[npc.her][npc.tongue]深深探入[npc.her][npc.nipple+]中。"));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_NIPPLE_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "粗暴地自舔乳头";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地把[npc.tongue]顶入[npc.her]自己的[npc.nipple+]中。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]粗暴地把[npc.her][npc.tongue+]尽可能深地探入[npc.her][npc.nipple+]中，"
							+ "用[npc.her][npc.lips+]用力碾压[npc.her][npc.breasts+]，然后发出了一声闷闷的[npc.moan]。",
					"[npc.Name]把[npc.her][npc.tongue+]从[npc.her][npc.nipple+]中抽出，开始粗暴地亲吻舔弄[npc.her][npc.nipples+]，"
							+ "随后再次向前探身，激烈地把[npc.her][npc.tongue]顶入[npc.her][npc.nipple+]中。",
					"[npc.Name]抽出[npc.her][npc.tongue+]，开始用力地亲吻舔弄[npc.her][npc.nipples+]，"
							+ "随后再次粗暴地把[npc.her][npc.tongue]深深探入[npc.her][npc.nipple+]中。"));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_NIPPLE_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "自舔乳头";
		}
		@Override
		public String getActionDescription() {
			return "继续把[npc.tongue]顶入[npc.her]自己的[npc.nipple+]中。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]把[npc.her][npc.tongue+]尽可能深地探入[npc.her][npc.nipple+]中，"
							+ "把[npc.her][npc.lips+]紧贴在[npc.her][npc.breasts+]上，然后发出了一声闷闷的[npc.moan]。",
					"[npc.Name]把[npc.her][npc.tongue+]从[npc.her][npc.nipple+]中抽出，开始亲吻舔弄[npc.her][npc.nipples+]，"
							+ "随后再次向前探身，把[npc.her][npc.tongue]滑入[npc.her][npc.nipple+]中。",
					"[npc.Name]抽出[npc.her][npc.tongue+]，开始亲吻摩擦[npc.her][npc.breasts+]，"
							+ "随后再次把[npc.her][npc.tongue]深深探入[npc.her][npc.nipple+]中。"));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_NIPPLE_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "渴望地自舔乳头";
		}
		@Override
		public String getActionDescription() {
			return "渴望地把[npc.tongue]顶入[npc.her]自己的[npc.nipple+]中。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]渴望地把[npc.her][npc.tongue+]尽可能深地探入[npc.her][npc.nipple+]中，"
							+ "把[npc.her][npc.lips+]紧贴在[npc.her][npc.breasts+]上，然后发出了一声闷闷的[npc.moan]。",
					"[npc.Name]把[npc.her][npc.tongue+]从[npc.her][npc.nipple+]中抽出，开始急切地亲吻舔弄[npc.her][npc.nipples+]，"
							+ "随后再次向前探身，贪婪地把[npc.her][npc.tongue]滑入[npc.her][npc.nipple+]中。",
					"[npc.Name]抽出[npc.her][npc.tongue+]，开始开心地亲吻摩擦[npc.her][npc.breasts+]，"
							+ "随后再次热情地把[npc.her][npc.tongue]深深探入[npc.her][npc.nipple+]中。"));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_NIPPLE_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "停止自舔乳头";
		}
		@Override
		public String getActionDescription() {
			return "把[npc.tongue]从[npc.her][npc.nipple+]中抽出，停止舔弄[npc.her]自己[npc.breasts+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]最后粗暴地舔了一下，便把[npc.her][npc.face]从[npc.her][npc.breasts+]旁移开。",

							"[npc.Name]给[npc.her][npc.nipple+]最后一记凶猛的亲吻，然后把[npc.her][npc.face]从[npc.her][npc.breasts+]旁移开。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]最后舔了一下，便把[npc.her][npc.face]从[npc.her][npc.breasts+]旁移开。",

							"[npc.Name]给[npc.her][npc.nipple+]最后一记湿润的亲吻，然后把[npc.her][npc.face]从[npc.her][npc.breasts+]旁移开。"));
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
	};

}