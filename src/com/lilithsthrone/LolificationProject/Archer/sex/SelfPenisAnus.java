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
			return "Start anal self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Bend [npc.her] [npc.cock+] and start fucking [npc.herself] in the [npc.ass] with it.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Snaking [npc.her] [npc.cock+] around to [npc.her] [npc.ass], [npc.name] [npc.verb(tease)] the [npc.cockHead+] over [npc.her] [npc.asshole+], [npc.moaning] in delight before thrusting it deep inside.",
					"[npc.Name] [npc.verb(bend)] over in a display of impressive flexibility, [npc.moaning] in delight as [npc.she] [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] against [npc.her] [npc.asshole+], before [npc.verb(slide)] it deep inside [npc.her] tight [npc.ass].",
					"Using [npc.her] prehensile cock to put [npc.her] flexibility to the test, [npc.name] [npc.verb(guide)] [npc.her] throbbing [npc.cock+] between [npc.her] [npc.ass] cheeks and [npc.verb(thrust)] it deep inside [npc.her] [npc.asshole+], letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] fucking [npc.herself] in the [npc.ass].");
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
			return "Gentle anal self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc.her] own [npc.ass] with [npc.her] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] slowly [npc.verb(push)] [npc.her] [npc.cock+] deep inside [npc.her] [npc.asshole+].",
					"Gently pumping [npc.her] [npc.cock+] in and out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] slowly [npc.verb(fuck)] [npc.her] own [npc.ass].",
					"Slowly driving [npc.her] [npc.cock+] deep inside [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] out a little whimper as [npc.she] "
							+(!Main.sex.getCharacterPerformingAction().hasVagina()
									? "[npc.verb(use)] it to gently start massaging [npc.her] prostate."
									: "gently [npc.verb(fuck)] [npc.her] own [npc.ass+]."),
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] [npc.verb(start)] gently pumping [npc.her] [npc.cock+] in and out of [npc.her] [npc.asshole+].");
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
			return "Anal self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.her] own [npc.ass] with [npc.her] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.cock+] deep inside [npc.her] [npc.asshole+].",
					"Pumping [npc.her] [npc.cock+] in and out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.her] own [npc.ass].",
					"Driving [npc.her] [npc.cock+] deep inside [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] "
							+(!Main.sex.getCharacterPerformingAction().hasVagina()
									? "[npc.verb(use)] it to start massaging [npc.her] prostate."
									: "steadily [npc.verb(fuck)] [npc.her] own [npc.ass+]."),
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] [npc.verb(start)] pumping [npc.her] [npc.cock+] in and out of [npc.her] [npc.asshole+].");
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
			return "Rough anal self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.her] own [npc.ass] with [npc.her] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] roughly [npc.verb(slam)] [npc.her] [npc.cock+] deep inside [npc.her] [npc.asshole+], before starting to roughly fuck [npc.her] own [npc.ass].",
					"Roughly pumping [npc.her] [npc.cock+] in and out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] ruthlessly [npc.verb(fuck)] [npc.her] own [npc.ass].",
					"Forcefully driving [npc.her] [npc.cock+] deep inside [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] "
							+(!Main.sex.getCharacterPerformingAction().hasVagina()
									? "[npc.verb(start)] roughly grinding it up against [npc.her] prostate."
									: "roughly [npc.verb(fuck)] [npc.her] own [npc.ass+]."),
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.cock+] in and out of [npc.her] [npc.asshole+].");
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
			return "Anal self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.her] own [npc.ass] with [npc.her] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.cock+] deep inside [npc.her] [npc.asshole+].",
					"Pumping [npc.her] [npc.cock+] in and out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.her] own [npc.ass].",
					"Driving [npc.her] [npc.cock+] deep inside [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] "
							+(!Main.sex.getCharacterPerformingAction().hasVagina()
									? "[npc.verb(use)] it to start massaging [npc.her] prostate."
									: "steadily [npc.verb(fuck)] [npc.her] own [npc.ass+]."),
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] [npc.verb(start)] pumping [npc.her] [npc.cock+] in and out of [npc.her] [npc.asshole+].");
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
			return "Eager anal self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.her] own [npc.ass] with [npc.her] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] eagerly [npc.verb(slam)] [npc.her] [npc.cock+] deep inside [npc.her] [npc.asshole+], before starting to desperately fuck [npc.her] own [npc.ass].",
					"Enthusiastically pumping [npc.her] [npc.cock+] in and out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] frantically [npc.verb(fuck)] [npc.her] own [npc.ass].",
					"Desperately driving [npc.her] [npc.cock+] deep inside [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] "
							+(!Main.sex.getCharacterPerformingAction().hasVagina()
									? "[npc.verb(start)] eagerly grinding it up against [npc.her] prostate."
									: "eagerly [npc.verb(fuck)] [npc.her] own [npc.ass+]."),
					"Focusing on pleasuring [npc.her] [npc.ass+], [npc.name] eagerly [npc.verb(start)] slamming [npc.her] [npc.cock+] in and out of [npc.her] [npc.asshole+].");
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
			return "Stop anal self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking [npc.herself] with [npc.her] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			return "With [npc.a_groan+], [npc.name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc.her] [npc.asshole+].";
		}
	};
}
