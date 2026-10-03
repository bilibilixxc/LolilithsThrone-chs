package com.lilithsthrone.LolificationProject.Lexi.sex;

import com.lilithsthrone.LolificationProject.ModCommon.TranslationUtils;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
 * @since 0.1.79
 * @version 0.4.8.5
 * @author Lexi
 */
public class SelfTongueTussy {

	public static final SexAction SELF_TAIL_CUNNILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.TUSSY)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition().isSelfOralAvailable(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getCharacterPerformingAction().getTailType().isTussy();
		}
		@Override
		public String getActionTitle() {
			return "Start Tussy Autocunnilingus";
		}
		@Override
		public String getActionDescription() {
			return "Slide your [npc.tongue] into your [npc.tail+] and start performing cunnilingus on yourself.";
		}
		@Override
		public String getDescription() {
			return TranslationUtils.getStringFor(
					"sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
					"selfTussyLickStart", // change to what the sex action will be called in short form, must be unique.
					true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
			);
		}
	};

	public static final SexAction SELF_TAIL_CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice. TUSSY)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle Tail Pussy Autocunnilingus";
		}
		@Override
		public String getActionDescription() {
			return "Gently lick your own [npc.tail+]'s pussy.";
		}
		@Override
		public String getDescription() {
			return TranslationUtils.getStringFor(
					"sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
					"selfTussyLickGentle", // change to what the sex action will be called in short form, must be unique.
					true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
			);
		}
	};

	public static final SexAction SELF_TAIL_CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice. TUSSY)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Tail Pussy Autocunnilingus";
		}
		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.tongue] into your [npc.tail+]'s pussy.";
		}
		@Override
		public String getDescription() {
			return TranslationUtils.getStringFor(
					"sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
					"selfTussyLickNormal", // change to what the sex action will be called in short form, must be unique.
					true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
			);
		}
	};

	public static final SexAction SELF_TAIL_CUNNILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice. TUSSY)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Stop Tail Pussy Autocunnilingus";
		}
		@Override
		public String getActionDescription() {
			return "Pull your [npc.tongue] out of your [npc.tail+]'s pussy and stop performing auto tail cunnilingus.";
		}
		@Override
		public String getDescription() {
			return TranslationUtils.getStringFor(
					"sex", // FILE_NAME, ensure it's real, looks in res/Translation/en_US/FILE_NAME.lang
					"selfTussyLickStop", // change to what the sex action will be called in short form, must be unique.
					true// reverses the perspective of the line, IE Swaps [npc.her] and [npc2.her] -> [npc2.her] and [npc.her]
			);
		}
	};
	
}