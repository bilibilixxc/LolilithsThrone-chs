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
public class SelfClitNipple {

	public static final SexAction SELF_CLIT_NIPPLE_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return super.isBaseRequirementsMet()
                    && Main.sex.getCharacterPerformingAction().hasVagina()
                    && Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis()
                    && Main.game.isNipplePenEnabled()
                    && Main.sex.getCharacterPerformingAction().isBreastFuckableNipplePenetration()
                    && !this.isForbiddenArea(SexAreaOrifice.NIPPLE, SexAreaPenetration.CLIT)
                    && Main.sex.getCharacterPerformingAction().getVaginaRawClitorisSizeValue()>15
                    || (Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.HYPERMOBILITY)
                    || Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_1));
		}

		@Override
		public String getActionTitle() {
			return "Start clit nipple self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Bend down and start fucking [npc.her] own [npc.nipples+] with [npc.her] [npc.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Bending over in an impressive display of flexibility, [npc.name] [npc.verb(line)] the [npc.clitTip+] of [npc.her] [npc.clit+] up against [npc.her] own [npc.nipples], before slowly pushing it inside and starting to fuck [npc.herself].",
					"[npc.Name] [npc.verb(bend)] over in a display of impressive flexibility, [npc.moaning] in delight as [npc.she] [npc.verb(press)] the tip of [npc.her] [npc.clit+] against an inviting [npc.nipple(true)], [npc.verb(slide)] it deep inside [npc.herself], and [npc.verb(start)] to fuck [npc.herself].",
					"Using [npc.her] enlarged clit to put [npc.her] flexibility to the test, [npc.name] [npc.verb(guide)] [npc.her] throbbing [npc.clit+] up to [npc.her] [npc.breasts+], and [npc.verb(push)] it deep inside an inviting [npc.nipple(true)], letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] fucking [npc.herself]."));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction DOM_SELF_CLIT_NIPPLE_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {

		@Override
		public String getActionTitle() {
			return "Gentle clit nipple self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc.her] own [npc.nipples+] with [npc.her] [npc.clit+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] slowly [npc.verb(push)] [npc.her] [npc.clit+] deep inside an inviting [npc.nipple(true)].",
					"Gently pumping [npc.her] [npc.clit+] in and out of [npc.her] own [npc.nipples+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.herself].",
					"Slowly driving [npc.her] [npc.clit+] deep inside an inviting [npc.nipple(true)], [npc.name] [npc.verb(let)] out a little whimper as [npc.she] [npc.verb(start)] gently fucking [npc.herself].");
		}
	};

	public static final SexAction DOM_SELF_CLIT_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {

		@Override
		public String getActionTitle() {
			return "Clit nipple self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.her] own [npc.nipples+] with [npc.her] [npc.clit+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.clit+] deep inside an inviting [npc.nipple(true)].",
					"Pumping [npc.her] [npc.clit+] in and out of [npc.her] own [npc.nipples+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.herself].",
					"Driving [npc.her] [npc.clit+] deep inside an inviting [npc.nipple(true)], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] happily fucking [npc.herself].");
		}
	};

	public static final SexAction DOM_SELF_CLIT_NIPPLE_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Rough clit nipple self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.her] own [npc.nipples+] with [npc.her] [npc.clit+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] roughly [npc.verb(slam)] [npc.her] [npc.clit+] deep inside an inviting [npc.nipple(true)], before starting to roughly fuck [npc.herself].",
					"Roughly pumping [npc.her] [npc.clit+] in and out of [npc.her] own [npc.nipples+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] ruthlessly [npc.verb(fuck)] [npc.herself].",
					"Driving [npc.her] [npc.clit+] deep inside an inviting [npc.nipple(true)], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] roughly grinding it up inside [npc.herself].");
		}
	};

	public static final SexAction SUB_SELF_CLIT_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return "Clit nipple self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.her] own [npc.nipples+] with [npc.her] [npc.clit+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(push)] [npc.her] [npc.clit+] deep inside an inviting [npc.nipple(true)].",
					"Pumping [npc.her] [npc.clit+] in and out of [npc.her] own [npc.nipples+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(fuck)] [npc.herself].",
					"Driving [npc.her] [npc.clit+] deep inside an inviting [npc.nipple(true)], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] fucking [npc.herself].");
		}
	};

	public static final SexAction SUB_SELF_CLIT_NIPPLE_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "Eager clit nipple self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.her] own [npc.nipples+] with [npc.her] [npc.clit+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] eagerly [npc.verb(slam)] [npc.her] [npc.clit+] deep inside an inviting [npc.nipple(true)], before starting to desperately fuck [npc.herself].",
					"Enthusiastically pumping [npc.her] [npc.clit+] in and out of [npc.her] own [npc.nipples+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] frantically [npc.verb(fuck)] [npc.herself].",
					"Desperately driving [npc.her] [npc.clit+] deep inside an inviting [npc.nipple(true)], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] eagerly grinding it up inside [npc.herself].");
		}
	};

	public static final SexAction SELF_CLIT_NIPPLE_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "Stop clit nipple self-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking [npc.her] own [npc.nipples+] with [npc.her] [npc.clit+].";
		}

		@Override
		public String getDescription() {
			return "Letting out [npc.a_moan], [npc.name] [npc.verb(slide)] [npc.her] [npc.clit+] out of an inviting [npc.nipple(true)].";
		}
	};
}
