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
			return "Start anal clit self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Bend [npc.her] [npc.clit+] and start fucking [npc.herself] in the [npc.ass] with it.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Bending [npc.her] [npc.clit+] in an impressive display of flexibility, [npc.name] [npc.verb(line)] the [npc.clitTip+] of [npc.her] [npc.clit+] up against [npc.her] [npc.asshole+], before slowly pushing it inside and starting to fuck [npc.herself].",
					"[npc.Name] [npc.verb(bend)] [npc.her] [npc.clit+] in a display of impressive flexibility, [npc.moaning] in delight as [npc.she] [npc.verb(press)] the tip of [npc.her] [npc.clit+] against [npc.her] [npc.asshole+], [npc.verb(slide)] it deep inside [npc.herself], and [npc.verb(start)] to fuck [npc.herself].",
					"Using [npc.her] enlarged clit to put [npc.her] flexibility to the test, [npc.name] [npc.verb(guide)] [npc.her] throbbing [npc.clit+] between [npc.her] [npc.ass] cheeks and [npc.verb(push)] it deep inside [npc.her] [npc.asshole+], letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] fucking [npc.herself]."));

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
			return "Gentle anal clit self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc.herself] in the [npc.ass] with [npc.her] [npc.clit+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] slowly [npc.verb(push)] [npc.her] [npc.clit+] deep inside [npc.her] [npc.asshole+].",
					"Gently pumping [npc.her] [npc.clit+] in and out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.herself].",
					"Slowly driving [npc.her] [npc.clit+] deep inside [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] out a little whimper as [npc.she] [npc.verb(start)] gently fucking [npc.herself].");
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
			return "Anal clit self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.herself] in the [npc.ass] with [npc.her] [npc.clit+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.clit+] deep inside [npc.her] [npc.asshole+].",
					"Pumping [npc.her] [npc.clit+] in and out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.herself].",
					"Driving [npc.her] [npc.clit+] deep inside [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] happily fucking [npc.herself].");
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
			return "Rough anal clit self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.herself] in the [npc.ass] with [npc.her] [npc.clit+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] roughly [npc.verb(slam)] [npc.her] [npc.clit+] deep inside [npc.her] [npc.asshole+], before starting to roughly fuck [npc.herself].",
					"Roughly pumping [npc.her] [npc.clit+] in and out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] ruthlessly [npc.verb(fuck)] [npc.herself].",
					"Driving [npc.her] [npc.clit+] deep inside [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] roughly grinding it up inside [npc.herself].");
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
			return "Anal clit self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.herself] in the [npc.ass] with [npc.her] [npc.clit+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(push)] [npc.her] [npc.clit+] deep inside [npc.her] [npc.asshole+].",
					"Pumping [npc.her] [npc.clit+] in and out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.herself].",
					"Driving [npc.her] [npc.clit+] deep inside [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] fucking [npc.herself].");
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
			return "Eager anal clit self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.herself] in the [npc.ass] with [npc.her] [npc.clit+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] eagerly [npc.verb(slam)] [npc.her] [npc.clit+] deep inside [npc.her] [npc.asshole+], before starting to desperately fuck [npc.herself].",
					"Enthusiastically pumping [npc.her] [npc.clit+] in and out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] frantically [npc.verb(fuck)] [npc.herself].",
					"Desperately driving [npc.her] [npc.clit+] deep inside [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] eagerly grinding it up inside [npc.herself].");
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
			return "Stop anal clit self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking [npc.herself] in the [npc.ass] with [npc.her] [npc.clit+].";
		}

		@Override
		public String getDescription() {
			return "Letting out [npc.a_moan], [npc.name] [npc.verb(slide)] [npc.her] [npc.clit+] out of [npc.her] [npc.asshole+].";
		}
	};
}
