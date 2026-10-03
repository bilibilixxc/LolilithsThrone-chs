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
public class SelfClitMouth {

	public static final SexAction SELF_CLIT_MOUTH_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return super.isBaseRequirementsMet()
                    && Main.sex.getPosition().isSelfOralAvailable(Main.sex.getCharacterPerformingAction())
                    && Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis()
                    && !this.isForbiddenArea(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)
                    && Main.sex.getCharacterPerformingAction().getVaginaRawClitorisSizeValue()>20
                    || (Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.HYPERMOBILITY)
                    || Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_1));
		}

		@Override
		public String getActionTitle() {
			return "Start sucking own clit";
		}

		@Override
		public String getActionDescription() {
			return "Bend down and start going down on [npc.herself], taking [npc.her] [npc.clit+] into [npc.her] own mouth.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Bending down in an impressive display of flexibility, [npc.name] [npc.verb(guide)] the [npc.clitTip+] of [npc.her] [npc.clit+] and [npc.verb(press)] [npc.her] [npc.lips+] against it, before slowly taking it into [npc.her] mouth.",
					"[npc.Name] [npc.verb(bend)] over in a display of impressive flexibility, [npc.moaning] in delight as [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] against the tip of [npc.her] [npc.clit+], [npc.verb(slide)] it deep inside [npc.her] mouth, and [npc.verb(start)] to go down on [npc.herself].",
					"Putting [npc.her] flexibility to the test, [npc.name] [npc.verb(bend)] and [npc.verb(guide)] [npc.her] throbbing [npc.clit+] between [npc.her] [npc.lips+], letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] sucking it."));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction DOM_SELF_CLIT_MOUTH_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {

		@Override
		public String getActionTitle() {
			return "Gentle clit autofellatio";
		}

		@Override
		public String getActionDescription() {
			return "Gently go down on [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Gently bobbing up and down on [npc.her] [npc.clit+], [npc.name] [npc.verb(suck)] at [npc.her] own [npc.clitTip+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Slowly taking [npc.her] [npc.clit+] in and out of [npc.her] mouth, [npc.name] [npc.verb(start)] letting out a series of delighted, muffled [npc.moans] as [npc.she] gently sucks on [npc.her] own [npc.clitTip+].",
					"Leaning down to softly worship [npc.herself], [npc.name] [npc.verb(swirl)] [npc.her] tongue around the [npc.clitTip+] before taking it deep into [npc.her] mouth once more.");
		}
	};

	public static final SexAction DOM_SELF_CLIT_MOUTH_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {

		@Override
		public String getActionTitle() {
			return "Clit autofellatio";
		}

		@Override
		public String getActionDescription() {
			return "Continue going down on [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Eagerly bobbing up and down on [npc.her] [npc.clit+], [npc.name] [npc.verb(suck)] at [npc.her] own [npc.clitTip+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Taking [npc.her] [npc.clit+] in and out of [npc.her] mouth, [npc.name] [npc.verb(start)] letting out a series of delighted, muffled [npc.moans] as [npc.she] sucks on [npc.her] own [npc.clitTip+].",
					"Leaning down to worship [npc.herself], [npc.name] [npc.verb(swirl)] [npc.her] tongue around the [npc.clitTip+] before taking it deep into [npc.her] mouth once more.");
		}
	};

	public static final SexAction DOM_SELF_CLIT_MOUTH_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Rough clit autofellatio";
		}

		@Override
		public String getActionDescription() {
			return "Roughly go down on [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Roughly bobbing up and down on [npc.her] [npc.clit+], [npc.name] [npc.verb(suck)] at [npc.her] own [npc.clitTip+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Violently taking [npc.her] [npc.clit+] in and out of [npc.her] mouth, [npc.name] [npc.verb(start)] letting out a series of desperate, muffled [npc.moans] as [npc.she] greedily sucks on [npc.her] own [npc.clitTip+].",
					"Slamming [npc.herself] down onto [npc.her] [npc.clit+], [npc.name] [npc.verb(swirl)] [npc.her] tongue around the [npc.clitTip+] before forcefully taking it deep into [npc.her] throat.");
		}
	};

	public static final SexAction SUB_SELF_CLIT_MOUTH_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return "Clit autofellatio";
		}

		@Override
		public String getActionDescription() {
			return "Continue going down on [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Bobbing up and down on [npc.her] [npc.clit+], [npc.name] [npc.verb(suck)] at [npc.her] own [npc.clitTip+] and [npc.verb(let)] out a muffled [npc.moan].",
					"Taking [npc.her] [npc.clit+] in and out of [npc.her] mouth, [npc.name] [npc.verb(start)] letting out a series of delighted, muffled [npc.moans] as [npc.she] sucks on [npc.her] own [npc.clitTip+].",
					"Leaning down to worship [npc.herself], [npc.name] [npc.verb(swirl)] [npc.her] tongue around the [npc.clitTip+] before taking it deep into [npc.her] mouth once more.");
		}
	};

	public static final SexAction SUB_SELF_CLIT_MOUTH_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "Eager clit autofellatio";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly go down on [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Eagerly bobbing up and down on [npc.her] [npc.clit+], [npc.name] [npc.verb(suck)] at [npc.her] own [npc.clitTip+] and [npc.verb(let)] out a desperate, muffled [npc.moan].",
					"Desperately taking [npc.her] [npc.clit+] in and out of [npc.her] mouth, [npc.name] [npc.verb(start)] letting out a series of delighted, muffled [npc.moans] as [npc.she] greedily sucks on [npc.her] own [npc.clitTip+].",
					"Thrusting [npc.herself] down onto [npc.her] [npc.clit+], [npc.name] [npc.verb(swirl)] [npc.her] tongue around the [npc.clitTip+] before desperately taking it deep into [npc.her] throat.");
		}
	};

	public static final SexAction SELF_CLIT_MOUTH_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "Stop clit autofellatio";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.her] [npc.clit+] out of [npc.her] mouth and stop going down on [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"With one last lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.clit+], letting out [npc.a_moan+] as [npc.she] [npc.verb(release)] [npc.her] [npc.clit+] from [npc.her] mouth.",
					"Giving [npc.her] [npc.clitTip+] a final, wet kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc.her] [npc.clit+].");
		}
	};
}
