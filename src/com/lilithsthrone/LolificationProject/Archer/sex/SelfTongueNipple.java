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
			return "Start self nipple licking";
		}
		@Override
		public String getActionDescription() {
			return "Slide your [npc.tongue+] into your [npc.nipple+] and start licking your own [npc.breasts+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Leaning forward with a stretch of pure flexibility, [npc.name] [npc.verb(bend)] down and ",
					"Bending over with ease, [npc.name] ",
					"Bringing [npc.her] face down to [npc.her] [npc.breasts+], [npc.name] "));

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.nipples]."
							+ " Planting a series of soft kisses on [npc.her] [npc.nipple+], [npc.she] slowly, but firmly, [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.nipple+].",

							"[npc.verb(plant)] a series of soft kisses on [npc.her] [npc.breasts+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.nipple+] a long, wet lick, before gently pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"eagerly [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.nipples]."
							+ " Planting a series of passionate kisses on [npc.her] [npc.nipple+], [npc.she] desperately [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.nipple+].",

							"[npc.verb(plant)] a series of passionate kisses on [npc.her] [npc.breasts+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.nipple+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"roughly [npc.verb(grind)] [npc.her] [npc.lips+] against [npc.her] [npc.nipples]."
							+ " Planting a series of forceful kisses on [npc.her] [npc.nipple+], [npc.she] greedily [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.nipple+].",

							"[npc.verb(plant)] a series of forceful kisses on [npc.her] [npc.breasts+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.nipple+] a rough lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"eagerly [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.nipples]."
							+ " Planting a series of passionate kisses on [npc.her] [npc.nipple+], [npc.she] passionately [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.nipple+].",

							"[npc.verb(plant)] a series of passionate kisses on [npc.her] [npc.breasts+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.nipple+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.nipples]."
							+ " Planting a series of kisses on [npc.her] [npc.nipple+], [npc.she] [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.nipple+].",

							"[npc.verb(plant)] a series of kisses on [npc.her] [npc.breasts+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.nipple+] a wet lick, before pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				default:
					break;
			}
            switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
                case ONE_TRICKLE:
                    UtilText.nodeContentSB.append(" A small trickle of [npc.milk] leaks from [npc.her] [npc.nipple+] around [npc.her] [npc.tongue+].");
                    break;
                case TWO_SMALL_AMOUNT:
                    UtilText.nodeContentSB.append(" A small squirt of [npc.milk] leaks from [npc.her] [npc.nipple+] around [npc.her] [npc.tongue+].");
                    break;
                case THREE_DECENT_AMOUNT:
                    UtilText.nodeContentSB.append(" A trickle of [npc.milk] runs out from [npc.her] [npc.nipple+] around [npc.her] [npc.tongue+].");
                    break;
                case FOUR_LARGE_AMOUNT:
                    UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] starts to flow out from [npc.her] [npc.nipple+] around [npc.her] [npc.tongue+] and run down [npc.her] [npc.breasts+].");
                    break;
                case FIVE_VERY_LARGE_DROOLING:
                    UtilText.nodeContentSB.append(" [npc.Milk] starts drooling out from [npc.her] [npc.nipple+] in a little stream around [npc.her] [npc.tongue+].");
                    break;
                case SIX_EXTREME_AMOUNT_DRIPPING:
                    UtilText.nodeContentSB.append(" [npc.Milk] starts pouring out from [npc.her] [npc.nipple+] in a constant stream to run down [npc.her] [npc.breasts+].");
                    break;
                case SEVEN_MONSTROUS_AMOUNT_POURING:
                    UtilText.nodeContentSB.append(" [npc.Milk] starts pouring out from [npc.her] [npc.nipple+] in a heavy flow to quickly soak [npc.her] [npc.breasts+].");
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
			return "Gentle self nipple licking";
		}
		@Override
		public String getActionDescription() {
			return "Gently lick your own [npc.nipples+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently driving [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.nipple+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.breasts+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] to gently kiss and lick [npc.her] [npc.nipples+],"
							+ " before pressing forwards and slowly sliding [npc.her] [npc.tongue] into [npc.her] [npc.nipple+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] slowly kissing and nuzzling against [npc.her] [npc.breasts+],"
							+ " before once again gently thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.nipple+]."));

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
			return "Self nipple licking";
		}
		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into your [npc.nipple+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly driving [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.nipple+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.breasts+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] to eagerly kiss and lick [npc.her] [npc.nipples+],"
							+ " before pressing forwards and greedily sliding [npc.her] [npc.tongue] into [npc.her] [npc.nipple+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] happily kissing and nuzzling against [npc.her] [npc.breasts+],"
							+ " before once again enthusiastically thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.nipple+]."));

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
			return "Rough self nipple licking";
		}
		@Override
		public String getActionDescription() {
			return "Roughly thrust your tongue into your own [npc.nipple+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly thrusting [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.nipple+],"
							+ " [npc.name] [npc.verb(grind)] [npc.her] [npc.lips+] against [npc.her] [npc.breasts+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] to roughly kiss and lick [npc.her] [npc.nipples+],"
							+ " before pressing forwards and violently thrusting [npc.her] [npc.tongue] into [npc.her] [npc.nipple+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] forcefully kissing and licking [npc.her] [npc.nipples+],"
							+ " before once again roughly thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.nipple+]."));

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
			return "Self nipple licking";
		}
		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into your own [npc.nipple+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Driving [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.nipple+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.breasts+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] to kiss and lick [npc.her] [npc.nipples+],"
							+ " before pressing forwards and sliding [npc.her] [npc.tongue] into [npc.her] [npc.nipple+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] kissing and nuzzling against [npc.her] [npc.breasts+],"
							+ " before once again thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.nipple+]."));

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
			return "Eager self nipple licking";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly thrust your [npc.tongue] into your own [npc.nipple+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly driving [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.nipple+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.breasts+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] to eagerly kiss and lick [npc.her] [npc.nipples+],"
							+ " before pressing forwards and greedily sliding [npc.her] [npc.tongue] into [npc.her] [npc.nipple+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] happily kissing and nuzzling against [npc.her] [npc.breasts+],"
							+ " before once again enthusiastically thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.nipple+]."));

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
			return "Stop self nipple licking";
		}
		@Override
		public String getActionDescription() {
			return "Pull your [npc.tongue] out of your [npc.nipple+] and stop licking your own [npc.breasts+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last rough lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.breasts+].",

							"Giving [npc.her] [npc.nipple+] a final, rough kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.breasts+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.breasts+].",

							"Giving [npc.her] [npc.nipple+] a final, wet kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.breasts+]."));
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
	};

}
