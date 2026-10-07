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
 * @since 0.1.79
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfTailNipple {
	
	public static final SexAction SELF_TAIL_NIPPLE_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "尾交乳头(自己)";
		}

		@Override
		public String getActionDescription() {
			return "用[npc.tail+]操自己[npc.nipples+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]将[npc.tail]伸向[npc.breasts+]，尾巴尖挑逗着[npc.nipples]，愉悦地[npc.moaning]着贯入其中。",
					"[npc.Name]将[npc.tail]卷在[npc.breasts+]间，愉悦地[npc.moaning]，强行突入[npc.her]那诱人的[npc.nipple(true)]。",
					"[npc.tail+]尖尖蹭着[npc.breasts+]，突然深贯进[npc.nipple(true)]，[npc.name]溢出[npc.a_moan+]，开始自我尾交。",
					"[npc.Name]急切地将[npc.tail+]深深塞进自己欲求不满的[npc.nipple(true)]，迭声[npc.moans+]，开始自我尾交。"));
		
			switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append("几滴[npc.milk]漏出并萦绕在[npc.her]的[npc.tail]上。");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append("少许[npc.milk]漏出并萦绕在[npc.her]的[npc.tail]上。");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append("一股[npc.milk]喷出并挂在[npc.her]的[npc.tail]上。");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append("[npc.Her][npc.milk]开始从[npc.her][npc.tail]尖流出并从[npc.her][npc.breasts]上缓缓流下。");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append("[npc.milk]顺着[npc.her][npc.tail]一小股一小股地流下。");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append("[npc.Milk]开始源源不断地流出，并成股地从[npc.her][npc.breasts]上流下。");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append("[npc.Milk]开始源源不断地倾泻而下并将[npc.her]的[npc.breasts]完全浸没了。");
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
	
	public static final SexAction DOM_SELF_TAIL_NIPPLE_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "尾交乳头(温柔)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地用[npc.tail]操自己[npc.nipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.tail]深入自己[npc.nipple+]，[npc.lips+]间发出[npc.moan]。",
					"[npc.name]用[npc.tail]在自己[npc.nipple+]里温柔地抽插着，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己[npc.breast+]。",
					"[npc.Name]将[npc.tail]缓缓送入[npc.nipple(true)]，轻柔地在[npc.breast+]间进进出出，不自觉地呜咽着。",
					"[npc.name]专心取悦起[npc.her]足以插入的[npc.breasts]，温柔地用[npc.tail]在[npc.her]其中一个[npc.nipples+]中进进出出。");
		}
		
	};
	
	public static final SexAction DOM_SELF_TAIL_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "尾交乳头(自己)";
		}

		@Override
		public String getActionDescription() {
			return "专心用[npc.tail+]操[npc.nipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.tail]贪婪地深入自己[npc.nipple+]，[npc.lips+]间发出[npc.moan]。",
					"[npc.name]用[npc.tail]在自己[npc.nipple+]里抽插着，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己[npc.breast+]。",
					"[npc.Name]将[npc.tail]深深插入自己足以插入的[npc.nipple(true)]，开始抽插自己[npc.breast+]，不自觉地漏出[npc.a_moan]。",
					"[npc.name]专心取悦起[npc.her][npc.breasts+]，温柔地用[npc.tail]在[npc.her]其中一个[npc.nipples+]中来回抽动。");
		}
		
	};
	
	public static final SexAction DOM_SELF_TAIL_NIPPLE_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "尾交乳头(粗暴)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地用[npc.tail]操自己[npc.nipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]粗暴地将[npc.tail]深深插进那[npc.nipple+]中，[npc.A_moan+]从[npc.lips+]间漏出，紧接着[npc.she]开始快速操起自己的[npc.breast(true)]。",
					"[npc.name]用[npc.tail]在自己[npc.nipple+]里粗暴地抽插着，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己[npc.breast+]。",
					"[npc.Name]激烈地将[npc.tail]深深插入自己可供操干的[npc.nipple(true)]，开始粗暴地抽插自己[npc.breast+]，不自觉地漏出[npc.a_moan]。",
					"[npc.name]专心取悦起[npc.her][npc.breasts]，粗暴地用[npc.tail]在其中一个[npc.nipples+]中激烈抽插。");
		}
	};
	
	public static final SexAction SUB_SELF_TAIL_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "尾交乳头(自己)";
		}

		@Override
		public String getActionDescription() {
			return "专心用[npc.tail+]操[npc.nipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.tail]贪婪地深入自己[npc.nipple+]，[npc.lips+]间发出[npc.moan]。",
					"[npc.name]用[npc.tail]在自己[npc.nipple+]里抽插着，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己[npc.breast+]。",
					"[npc.Name]将[npc.tail]深深插入自己足以插入的[npc.nipple(true)]，开始抽插自己[npc.breast+]，不自觉地漏出[npc.a_moan]。",
					"[npc.name]专心取悦起[npc.her][npc.breasts+]，温柔地用[npc.tail]在[npc.her]其中一个[npc.nipples+]中来回抽动。");
		}
	};
	
	public static final SexAction SUB_SELF_TAIL_NIPPLE_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "尾交乳头(渴求)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "急切地用[npc.tail+]操自己[npc.nipples+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]急切地将[npc.tail]深深插进那[npc.nipple+]中，[npc.A_moan+]从[npc.lips+]间漏出，紧接着[npc.she]开始狂乱地操起自己的[npc.breast(true)]。",
					"[npc.name]用[npc.tail]在自己[npc.nipple+]里热情地抽插着，开始发出一连串愉悦的[npc.moans]，有节奏地操起自己[npc.breast+]。",
					"[npc.Name]急切地将[npc.tail]深深插入自己足以插入的[npc.nipple(true)]，饥渴地抽插自己[npc.breast+]，不自觉地漏出[npc.a_moan]。",
					"[npc.name]专心取悦起[npc.her][npc.breasts+]，急切地用[npc.tail]在其中一个[npc.nipples+]中激烈抽插。");
		}
	};
	
	public static final SexAction SELF_TAIL_NIPPLE_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "停止尾交乳头(自己)";
		}

		@Override
		public String getActionDescription() {
			return "停止用[npc.tail]操[npc.nipple+]。";
		}

		@Override
		public String getDescription() {
			return "[npc.name]发出心满意足的[npc.moan]，将[npc.tail+]滑出[npc.nipples+]。";
		}
	};
}
