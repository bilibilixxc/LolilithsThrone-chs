package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
 * @since 0.3.1
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfFingerCrotchNipple {
	
	public static final SexAction PINCH_NIPPLE_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedCrotchBoobs();
		}
		
		@Override
		public String getActionTitle() {
			return "捏捏胯乳乳头";
		}

		@Override
		public String getActionDescription() {
			return "玩弄[npc.her]的[npc.crotchNipples]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]向下摸索并开始玩弄[npc.her]坚硬的[npc.nipples]，[npc.she]揉捏着摩擦它们并激动地[npc.moans]着。",
					"[npc.NamePos]指尖在[npc.her]的[npc.crotchBoobs]上挑逗，停下来并捏拉[npc.her]的[npc.crotchNipples]，[npc.she]发出愉悦的呻吟与叹息。",
					"[npc.Name]将手伸向[npc.her]的[npc.crotchBoobs]，急切地开始用手指揉捏挤压[npc.her]暴露的[npc.crotchNipples]。",
					"[npc.NamePos]的[npc.crotchNipples]渴求得到刺激，[npc.she]伸手揉捏着它们，哀鸣中带着愉悦。"));
			
			switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append("几滴[npc.crotchMilk]漏出并萦绕在[npc.her]的指尖上。");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append("少许[npc.crotchMilk]漏出并萦绕在[npc.her]的指尖上。");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append("些许[npc.crotchMilk]从指间流出。");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append("[npc.crotchMilk]开始从指间流出并从[npc.her][npc.crotchBoobs+]上缓缓流下。");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append("[npc.crotchMilk]顺着指间一小股一小股地流下。");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append("[npc.Her]的[npc.crotchMilk]开始源源不断地流出，并成股地从[npc.her][npc.crotchBoobs+]流下。");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append("[npc.crotchMilk]开始源源不断地倾泄而下并将[npc.her][npc.crotchBoobs+]完全浸没了。");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastCrotchStoredMilk(-10);
		}
		
	};
	
	
	public static final SexAction SELF_FINGER_NIPPLE_CROTCH_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedCrotchBoobs();
		}

		@Override
		public String getActionTitle() {
			return "指交胯乳乳头";
		}

		@Override
		public String getActionDescription() {
			return "将[npc.her]的手指陷入[npc.her][npc.crotchNipples+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]向下摸索，发出淫荡的[npc.moan]，将[npc.her]的手指饥渴地陷入那足以插入的[npc.crotchNipples]内。",
					"[npc.NamePos]的指尖挑逗着[npc.her]的[npc.crotchBoobs]，在[npc.her]的[npc.crotchNipples]上转圈，然后贪婪地陷入其中。",
					"[npc.she]用手指急切地探入[npc.her]的乳穴，这让[npc.Name][npc.moan]并尖叫出声。",
					"随着一声淫荡的叫喊，[npc.name]将[npc.her]的手指插入[npc.her]令人沉醉的乳穴中，[npc.she]沉重地喘息着，急切地用手指取悦[npc.herself]。"));
		
			switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append("几滴[npc.crotchMilk]漏出并萦绕在[npc.her]的指尖上。");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append("少许[npc.crotchMilk]漏出并萦绕在[npc.her]的指尖上。");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append("些许[npc.crotchMilk]从[npc.her]指间流出。");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append("[npc.Her]的[npc.crotchMilk]开始从[npc.her]的指间流出并从[npc.her][npc.crotchBoobs+]上缓缓流下。");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append("[npc.crotchMilk]顺着指间一小股一小股地流下。");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append("[npc.crotchMilk]开始源源不断地流出，并成股地从[npc.her][npc.crotchBoobs+]流下。");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append("[npc.Her]的[npc.crotchMilk]开始源源不断地倾泄而下并将[npc.her][npc.crotchBoobs+]完全浸没了。");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastCrotchStoredMilk(-10);
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_NIPPLE_CROTCH_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "指交乳头(温柔)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地指交[npc.her][npc.crotchNipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.she]慢慢地用[npc.fingers]深入自己[npc.crotchNipple+]，[npc.lips+]间溢出[npc.A_moan+]。",
					"[npc.name]用[npc.fingers]在[npc.her][npc.crotchNipple+]里温柔地抽插，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己[npc.crotchBoob+]。",
					"[npc.fingers]在[npc.crotchNipple]内蜷起，[npc.name]将[npc.fingers]在[npc.her][npc.crotchBoob+]中抽插，不自觉地发出呜咽声。",
					"[npc.she]专心取悦起自己足以插入的[npc.crotchBoobs]，[npc.fingers]温柔地在一个[npc.crotchNipples+]中进进出出。");
		}
		
	};
	
	public static final SexAction DOM_SELF_FINGER_NIPPLE_CROTCH_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "指交乳头(自己)";
		}

		@Override
		public String getActionDescription() {
			return "全神贯注地指交[npc.her][npc.crotchNipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.she]贪婪地用[npc.fingers]深入自己[npc.crotchNipple+]，[npc.lips+]间溢出了[npc.A_moan+]。",
					"[npc.name]用[npc.fingers]在[npc.her][npc.crotchNipple+]里抽插着，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己[npc.crotchBoob+]。",
					"[npc.fingers]在可插入的[npc.crotchNipple]内蜷起，[npc.name]将[npc.fingers]在[npc.her][npc.crotchBoob+]中抽插，不自觉地发出呜咽声。",
					"[npc.she]专心取悦起自己[npc.crotchBoobs+]，[npc.fingers]在一个[npc.crotchNipples+]中进进出出。");
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_NIPPLE_CROTCH_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "指交乳头(粗暴)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地指交[npc.her][npc.crotchNipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.she]粗暴地把[npc.fingers]深入自己[npc.crotchNipple+]，[npc.lips+]间漏了[npc.A_moan+]，然后迅速指交着[npc.crotchBoob]。",
					"[npc.name]用[npc.fingers]在[npc.her][npc.crotchNipple+]里粗暴地抽插着，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己[npc.crotchBoob+]。",
					"[npc.fingers]在可插入的[npc.crotchNipple]内强硬地蜷起，[npc.name]将[npc.fingers]在[npc.her][npc.crotchBoob+]中粗暴地抽插，不自觉地发出呜咽声。",
					"[npc.she]专心取悦起[npc.her]足以插入的[npc.crotchBoobs+]，[npc.fingers]粗暴地在一个[npc.crotchNipples+]中猛烈地进进出出。");
		}
	};
	
	public static final SexAction SUB_SELF_FINGER_NIPPLE_CROTCH_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "指交乳头(自己)";
		}

		@Override
		public String getActionDescription() {
			return "全神贯注地指交[npc.her][npc.crotchNipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.she]贪婪地用[npc.fingers]深入自己[npc.crotchNipple+]，[npc.lips+]间溢出了[npc.A_moan+]。",
					"[npc.name]用[npc.fingers]在[npc.her][npc.crotchNipple+]里抽插着，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己[npc.crotchBoob+]。",
					"[npc.fingers]在可插入的[npc.crotchNipple]内蜷起，[npc.name]将[npc.fingers]在[npc.her][npc.crotchBoob+]中抽插，不自觉地发出呜咽声。",
					"[npc.she]专心取悦起[npc.her][npc.crotchBoobs+]，用[npc.fingers]在[npc.her]的其中一个[npc.crotchNipples+]中进进出出。");
		}
		
	};
	
	public static final SexAction SUB_SELF_FINGER_NIPPLE_CROTCH_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "指交乳头(渴求)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "急切地指交[npc.her][npc.crotchNipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.she]急切地把[npc.fingers]深入自己[npc.crotchNipple+]，[npc.lips+]间漏出[npc.A_moan+]，然后饥渴地指交着[npc.crotchBoob]。",
					"[npc.name]用[npc.fingers]在[npc.her][npc.crotchNipple+]里热情地抽插着，开始发出一连串愉悦的[npc.moans]，疯狂地操起自己[npc.crotchBoob+]。",
					"[npc.fingers]在可插入的[npc.crotchNipple]内狂乱地蜷起，[npc.name]将[npc.fingers]在[npc.her][npc.crotchBoob+]中急切地抽插，不自觉地发出呜咽声。",
					"[npc.she]专心取悦起[npc.her]足以插入的[npc.crotchBoobs+]，用npc.fingers]急切地在一个[npc.crotchNipples+]中猛烈地进进出出。");
		}

	};
	
	public static final SexAction SELF_FINGER_NIPPLE_CROTCH_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "停止指交乳头(自己)";
		}

		@Override
		public String getActionDescription() {
			return "停止指交[npc.her][npc.crotchNipples+]。";
		}

		@Override
		public String getDescription() {
			return "[npc.she]发出一声心满意足的[npc.moan]，将指尖滑出了[npc.crotchNipples+]。";
		}
	};
}
