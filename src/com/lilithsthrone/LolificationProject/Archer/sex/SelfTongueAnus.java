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
public class SelfTongueAnus {

	public static final SexAction SELF_TONGUE_ANUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition().isSelfOralAvailable(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getCharacterPerformingAction().getTongueLengthValue()>30
                    && !this.isForbiddenArea(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)
                    || Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.HYPERMOBILITY)
					|| Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_1));
		}
		@Override
		public String getActionTitle() {
			return "Start auto-anilingus";
		}
		@Override
		public String getActionDescription() {
			return "Slide your [npc.tongue] into your [npc.ass+] and start performing anilingus on yourself.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"In a display of impressive flexibility, [npc.name] [npc.verb(bend)] down and ",
					"Showing off how flexible [npc.sheIsFull], [npc.name] [npc.verb(double)] over and ",
					"Putting [npc.her] flexibility to the test, [npc.name] [npc.verb(bend)] down and "));

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] own [npc.ass]."
							+ " Planting a series of soft kisses on [npc.her] [npc.asshole+], [npc.she] slowly, but firmly, [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.asshole+].",

							"[npc.verb(plant)] a series of soft kisses on [npc.her] [npc.asshole+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.ass+] a long, wet lick, before gently pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"eagerly [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] own [npc.ass]."
							+ " Planting a series of passionate kisses on [npc.her] [npc.asshole+], [npc.she] desperately [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.asshole+].",

							"[npc.verb(plant)] a series of passionate kisses on [npc.her] [npc.asshole+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.ass+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"roughly [npc.verb(grind)] [npc.her] [npc.lips+] against [npc.her] own [npc.ass]."
							+ " Planting a series of forceful kisses on [npc.her] [npc.asshole+], [npc.she] greedily [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.asshole+].",

							"[npc.verb(plant)] a series of forceful kisses on [npc.her] [npc.asshole+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.ass+] a rough lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"eagerly [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] own [npc.ass]."
							+ " Planting a series of passionate kisses on [npc.her] [npc.asshole+], [npc.she] passionately [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.asshole+].",

							"[npc.verb(plant)] a series of passionate kisses on [npc.her] [npc.asshole+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.ass+] a hungry lick, before greedily pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] own [npc.ass]."
							+ " Planting a series of kisses on [npc.her] [npc.asshole+], [npc.she] [npc.verb(slide)] [npc.her] [npc.tongue+] into [npc.her] [npc.asshole+].",

							"[npc.verb(plant)] a series of kisses on [npc.her] [npc.asshole+]."
							+ " [npc.She] then [npc.verb(give)] [npc.her] [npc.ass+] a wet lick, before pushing [npc.her] [npc.tongue+] deep inside."));
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_ANUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle auto-anilingus";
		}
		@Override
		public String getActionDescription() {
			return "Gently lick your own [npc.ass+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently driving [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.asshole+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.ass+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] to gently kiss and lick [npc.her] [npc.asshole+],"
							+ " before pressing forwards and slowly sliding [npc.her] [npc.tongue] into [npc.her] [npc.asshole+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] slowly kissing and nuzzling against [npc.her] [npc.asshole+],"
							+ " before once again gently thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_ANUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Auto-anilingus";
		}
		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into your [npc.ass+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly driving [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.asshole+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.ass+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] to eagerly kiss and lick [npc.her] [npc.asshole+],"
							+ " before pressing forwards and greedily sliding [npc.her] [npc.tongue] into [npc.her] [npc.asshole+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] happily kissing and nuzzling against [npc.her] [npc.asshole+],"
							+ " before once again enthusiastically thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_ANUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough auto-anilingus";
		}
		@Override
		public String getActionDescription() {
			return "Roughly thrust your tongue into your own [npc.ass+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly thrusting [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.asshole+],"
							+ " [npc.name] [npc.verb(grind)] [npc.her] [npc.lips+] against [npc.her] [npc.ass+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] to roughly kiss and lick [npc.her] [npc.asshole+],"
							+ " before pressing forwards and violently thrusting [npc.her] [npc.tongue] into [npc.her] [npc.asshole+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] forcefully kissing and licking [npc.her] [npc.asshole+],"
							+ " before once again roughly thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_ANUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Auto-anilingus";
		}
		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into your own [npc.ass+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Driving [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.asshole+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.ass+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] to kiss and lick [npc.her] [npc.asshole+],"
							+ " before pressing forwards and sliding [npc.her] [npc.tongue] into [npc.her] [npc.asshole+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] kissing and nuzzling against [npc.her] [npc.asshole+],"
							+ " before once again thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_ANUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager auto-anilingus";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly thrust your [npc.tongue] into your own [npc.ass+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly driving [npc.her] [npc.tongue+] as deep as possible into [npc.her] [npc.asshole+],"
							+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc.her] [npc.ass+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Withdrawing [npc.her] [npc.tongue+] from [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] to eagerly kiss and lick [npc.her] [npc.asshole+],"
							+ " before pressing forwards and greedily sliding [npc.her] [npc.tongue] into [npc.her] [npc.asshole+] once more.",
					"Drawing [npc.her] [npc.tongue+] out from [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] happily kissing and nuzzling against [npc.her] [npc.asshole+],"
							+ " before once again enthusiastically thrusting [npc.her] [npc.tongue] deep into [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_ANUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Stop auto-anilingus";
		}
		@Override
		public String getActionDescription() {
			return "Pull your [npc.tongue] out of your [npc.ass+] and stop performing auto-anilingus.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last rough lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.ass+].",

							"Giving [npc.her] [npc.asshole+] a final, rough kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.ass+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.ass+].",

							"Giving [npc.her] [npc.asshole+] a final, wet kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.ass+]."));
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
	};

}
