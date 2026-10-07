package com.lilithsthrone.game.sex.sexActions.baseActions;

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
 * @version 0.2.8
 * @author Innoxia
 */
public class FingerNipple {
	
	public static final SexAction PINCH_NIPPLES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "捏乳头";
		}

		@Override
		public String getActionDescription() {
			return "捏[npc2.namePos]的[npc2.nipples+]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]把手伸向[npc2.namePos][npc2.breasts+]，开始温柔地掐揉和摩擦[npc2.her][npc2.nipples+]，发出一声轻柔的[npc.moan]。",

							"[npc2.namePos][npc2.breasts+]完全地展示出来，对[npc.Name]来说是无法忽视的诱人目标，"
									+"而随着微弱轻柔的[npc.moan]，[npc.she]开始温柔地揪拽和挤捏[npc2.her][npc2.nipples+]。",

							"[npc.Name]用[npc.fingers]挑逗着[npc2.namePos][npc2.breasts+] ，开始温柔地拉扯捏弄[npc2.her][npc2.nipples+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]把手伸向[npc2.namePos][npc2.breasts+]，发出[npc.a_moan+]，急切地揉弄搓玩[npc2.her][npc2.nipples+]。",

							"[npc2.namePos][npc2.breasts+]完全地展示出来，对[npc.Name]来说是无法忽视的诱人目标，"
									+"而随着一阵[npc.a_moan+]，[npc.she]开始急切地揪拽挤捏[npc2.her][npc2.nipples+]。",

							"[npc.Name]用[npc.fingers]磨蹭着[npc2.namePos]的[npc2.breastRows][npc2.breasts+]，温柔地扯捏起[npc2.her][npc2.nipples+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]把手伸向[npc2.namePos][npc2.breasts+]，"
									+"开始粗暴地抚弄[npc2.namePos]的胸部，发出一声[npc.a_moan+]，随后将手伸向[npc2.her][npc2.nipples+]并对其激烈地掐揉挤压起来。",

							"[npc2.namePos][npc2.breasts+]完全地展示出来，对[npc.Name]来说是无法忽视的诱人目标，"
									+ "伴着[npc.a_moan+]，[npc.she]开始粗暴地捏弄[npc2.her]那[npc2.nipples+]。",

							"[npc.name]将[npc.fingers]插入[npc2.namePos][npc2.breastRows][npc2.breasts+]，"
									+ "[npc.Name]发出一声[npc.a_moan+]，随后开始粗暴地揉捏挤压[npc2.her][npc2.nipples+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]把手伸向[npc2.namePos][npc2.breasts+]，发出[npc.a_moan+]，急切地揉弄搓玩[npc2.her][npc2.nipples+]。",

							"[npc2.namePos][npc2.breasts+]完全地展示出来，对[npc.Name]来说是无法忽视的诱人目标，"
									+"而随着一阵[npc.a_moan+]，[npc.she]开始急切地揪拽挤捏[npc2.her][npc2.nipples+]。",

							"[npc.Name]用[npc.fingers]磨蹭着[npc2.namePos]的[npc2.breastRows][npc2.breasts+]，温柔地扯捏起[npc2.her][npc2.nipples+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]把手伸向[npc2.namePos][npc2.breasts+]，发出[npc.a_moan+]，急切地揉弄搓玩[npc2.her][npc2.nipples+]。",

							"[npc2.namePos][npc2.breasts+]完全地展示出来，对[npc.Name]来说是无法忽视的诱人目标，"
									+"而随着一阵[npc.a_moan+]，[npc.she]开始揪拽挤捏[npc2.her][npc2.nipples+]。",

							"[npc.Name]用[npc.fingers]磨蹭着[npc2.namePos]那[npc2.breastRows][npc2.breasts+]，随后扯捏起[npc2.her]那[npc2.nipples+]。"));
					break;
				default:
					break;
			}
			
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一声柔和的[npc2.moan]以回应[npc.namePos]的触摸，然后温柔地鼓励[npc.name]继续全神贯注于自己[npc2.nipples+]。",
	
								"[npc2.name]发出一声轻柔的[npc2.moan]，缓缓地挤捏着自己的胸部，弄出一阵淫荡的声音，恳求[npc.name]继续。",
	
								"在[npc.namePos]的触碰下，[npc2.name]轻柔地[npc2.moaning]着，温柔地鼓励[npc.Name]继续玩弄[npc2.her][npc2.nipples+]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一声[npc2.a_moan+]以回应[npc.namePos]的触摸，然后急切地鼓励[npc.name]继续全神贯注于自己[npc2.nipples+]。",
	
								"[npc2.name]发出[npc2.a_moan+]，饥渴地挤捏着自己的胸部，弄出一阵淫荡的声音，恳求[npc.name]继续。",
	
								"在[npc.namePos]的触碰下，[npc2.name][npc2.moaning+]着，急切地鼓励[npc.name]继续玩弄[npc2.her][npc2.nipples+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一声[npc2.a_moan+]以回应[npc.namePos]的触摸，然后粗暴地咆哮着让[npc.name]继续全神贯注于自己[npc2.nipples+]。",
	
								"[npc2.name]发出[npc2.a_moan+]，挺起了自己的胸部，语气坚定地"
										+ "命令[npc.name]继续全神贯注于自己[npc2.nipples+]。",
	
								"[npc2.name]发出一声[npc2.a_moan+]以回应[npc.namePos]的触摸，然后命令[npc.she]继续玩弄自己[npc2.nipples+]。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一声[npc2.a_moan+]以回应[npc.namePos]的触摸，然后急切地鼓励[npc.name]继续全神贯注于自己[npc2.nipples+]。",
	
								"[npc2.name]发出[npc2.a_moan+]，饥渴地挤捏着自己的胸部，弄出一阵淫荡的声音，恳求[npc.name]继续。",
	
								"在[npc.namePos]的触碰下，[npc2.name][npc2.moaning+]着，急切地鼓励[npc.name]继续玩弄[npc2.her][npc2.nipples+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一声[npc2.a_moan+]以回应[npc.namePos]的触摸，然后鼓励[npc.name]继续全神贯注于自己[npc2.nipples+]。",
	
								"[npc2.name]发出[npc2.a_moan+]，挤捏着自己的胸部，弄出一阵淫荡的声音，恳求[npc.name]继续。",
	
								"在[npc.namePos]的触碰下，[npc2.name][npc2.moaning+]着，鼓励[npc.name]继续玩弄[npc2.her][npc2.nipples+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]本能地向后缩，"
										+ "[npc2.sobbing]并挣扎抵抗着[npc.namePos]的触碰，同时试图将[npc.namePos]的[npc.fingers]从自己[npc2.nipples+]上推开。",
	
								"[npc2.Name]发出[npc2.a_sob+]，不安地扭动着，"
										+ "[npc2.she]乞求[npc.name]放过自己，但[npc.name]继续玩弄着[npc2.her][npc2.nipples+]。",
	
								"作为对[npc.namePos]触摸的回应，[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，"
										+ "[npc.name]不断刺激着[npc2.her][npc2.nipples+]，[npc2.name]奋力反抗着[npc.name]。"));
						break;
					default:
						break;
				}
			}
			
			switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append("[npc.Name]开始挤榨[npc2.namePos]的[npc2.nipples]，几滴[npc2.milk]从[npc2.namePos][npc2.breasts+]上滴落下来。");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append("[npc.Name]开始挤榨[npc2.namePos]的[npc2.nipples]，一小股[npc2.milk]从[npc2.namePos][npc2.breasts+]上滴落下来。");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append("[npc.Name]开始挤榨[npc2.namePos]的[npc2.nipples]，一缕[npc2.milk]从[npc2.namePos][npc2.breasts+]上滴落下来。");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append("[npc.name]贪婪地挤榨着[npc2.namePos][npc2.breasts+]，[npc2.milk]越过了[npc.namePos]的指尖不断溢出。");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append("[npc.name]贪婪地挤榨着[npc2.namePos][npc2.breasts+]，[npc2.milk]越过了[npc.namePos]的指尖不断垂落。");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append("[npc.name]贪婪地挤榨着[npc2.namePos][npc2.breasts+]，[npc2.milk]越过了[npc.namePos]的指尖不断涌出。");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append("[npc.name]贪婪地挤榨着[npc2.namePos][npc2.breasts+]，[npc2.milk]越过了[npc.namePos]的指尖不断涌出。");
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).incrementBreastStoredMilk(-10);
		}
		
	};
	
	
	public static final SexAction MILK_TARGET = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "给[npc2.name]挤奶";
		}

		@Override
		public String getActionDescription() {
			return "捏拽[npc2.namePos]那[npc2.nipples+]来挤出[npc2.milk+]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抬起[npc.fingers+]摸向[npc2.namePos][npc2.breasts+]，然后将注意力集中于[npc2.her][npc2.nipples+]。"
								+ "[npc.Name]温柔地拉扯挤压着[npc2.namePos][npc2.nipples+]，发出一小阵愉悦的叫声，",

							"[npc.name]发出一声轻柔的[npc.moan]，温柔地用[npc.fingers]按向[npc2.namePos][npc2.breasts+]，然后又转去挑逗[npc2.her][npc2.nipples+]。"
								+ "[npc.Name]灵巧地挤压揉捏着[npc2.namePos][npc2.nipples+]，发出一阵愉悦的叫声，"));
					break;
				case SUB_NORMAL: case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地抬起[npc.fingers+]摸向[npc2.namePos][npc2.breasts+]，然后迅速地将注意力集中于[npc2.her][npc2.nipples+]。"
								+ "[npc.Name]贪婪地拉扯揉捏着[npc2.namePos][npc2.nipples+]，发出一阵愉悦的叫声，",

							"[npc.name]发出[npc.a_moan+]，迫不及待地用[npc.fingers]按压[npc2.namePos]那[npc2.breasts+]，然后迅速转去挑逗[npc2.her]那[npc2.nipples+]。"
									+ "[npc.Name]贪婪地挤压揉捏着[npc2.namePos][npc2.nipples+]，发出一阵愉悦的叫声，"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]用[npc.fingers+]握住[npc2.namePos][npc2.breasts+]，随后让[npc.fingers]摸向[npc2.her][npc2.nipples+]。"
								+ "[npc.Name]用力地捏住并挤压[npc2.namePos][npc2.nipples+]，得意的笑了笑，",

							"[npc.Name]发出[npc.a_moan+]，粗暴地用[npc.fingers]捏向[npc2.namePos][npc2.breasts+]，然后迅速地用力弹弄[npc2.her][npc2.nipples+]。"
									+ "[npc.Name]霸道地挤压揉捏着[npc2.namePos][npc2.nipples+]，发出[npc.a_moan+]，"));
					break;
				case SUB_RESISTING:
					break;
			}
			
			switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk()) {
				case ZERO_NONE://Shouldn't be able to be reached
					UtilText.nodeContentSB.append("但没能从[npc2.namePos][npc2.breasts+]中挤出[npc2.milk]。"); 
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"从[npc2.her][npc2.breasts+]中挤出一点[npc2.milk]。",

							"从[npc2.her][npc2.breasts+]中挤出几滴[npc2.milk]。"));
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"从[npc2.her][npc2.breasts+]中挤出一小股[npc2.milk]。",

							"从[npc2.her][npc2.breasts+]中挤出一小缕[npc2.milk]。"));
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"从[npc2.her][npc2.breasts+]中挤出一股[npc2.milk]。",

							"从[npc2.her][npc2.breasts+]中挤出一缕[npc2.milk]。"));
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"从[npc2.her][npc2.breasts+]中源源不断地挤出[npc2.milk]。",

							"从[npc2.her][npc2.breasts+]中挤出一连串的[npc2.milk]。"));
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append("从[npc2.her][npc2.breasts+]中挤出大量的[npc2.milk]。");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append("从[npc2.her][npc2.breasts+]中挤出相当多的[npc2.milk]。");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append("从[npc2.her][npc2.breasts+]中挤出巨量的[npc2.milk]。");
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]开始挤[npc2.name]的奶，[npc2.name]发出和缓的[npc2.moan]，"
										+ "[npc2.she]将[npc2.breasts]压向[npc.NamePos]的[npc.hands]，温柔地乞求[npc.name]继续指交[npc2.herHim]。",
	
								"[npc2.Name]享受着[npc2.breasts]被挤奶带来的愉悦感觉，不由得[npc2.moanVerb]起来，"
										+ "[npc2.she]咬着[npc2.lip]，温柔地恳求[npc.name]继续做下去。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]开始挤[npc2.name]的奶，[npc2.name]发出愉悦的[npc2.moan]，"
										+ "[npc2.she]将[npc2.breasts]拼命地压向[npc.NamePos]的[npc.hands]，开心地乞求[npc.name]继续指交[npc2.herHim]。",
	
								"[npc2.Name]享受着[npc2.breasts]被挤奶带来的愉悦感觉，不由得[npc2.moanVerb]起来，"
										+ "[npc2.she]咬着[npc2.lip]，急切地恳求[npc.name]继续做下去。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]开始挤[npc2.name]的奶，[npc2.name]发出心满意足的咆哮，"
										+ "[npc2.she]将[npc2.breasts]压向[npc.NamePos]的[npc.hands]，粗暴地要求[npc.name]继续指交[npc2.herHim]。",
	
								"[npc2.Name]享受着[npc2.breasts]被挤奶带来的愉悦感觉，不由得[npc2.moanVerb]起来，"
										+ "[npc2.she]咬着[npc2.lip]，粗暴地命令[npc.name]继续做下去。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]开始挤[npc2.name]的奶，[npc2.name]发出[npc2.a_moan+]，"
										+ "[npc2.she]试图让[npc2.breasts]远离[npc.namePos]的[npc.hands]，绝望地恳求[npc.Name]停下。",
	
								"[npc2.name]绝望地尝试让自己[npc2.breasts+]逃离强制挤奶的命运，[npc2.she]不断恳求着[npc.Name]放过自己。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).incrementBreastStoredMilk(-Main.sex.getCharacterTargetedForSexAction(this).getBreastRawMilkStorageValue()/5);
		}
		
	};
	
	public static final SexAction GET_MILKED = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "开始被挤奶";
		}

		@Override
		public String getActionDescription() {
			return "将[npc2.namePos]的[npc2.hands]拉向你[npc.breasts+]，让[npc2.herHim]给你挤奶。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.hand]，鼓励[npc2.herHim]用[npc2.her][npc2.fingers+]轻轻抚摸自己[npc.breasts+]，"
									+ "然后温柔地引导[npc2.herHim]摸向自己[npc.nipples+]。"
									+ "[npc.Name]指导[npc2.herHim]拉扯揉捏着自己[npc.nipples+]，发出一阵轻柔的叫声，[npc2.name]",

							"[npc.name]发出一声轻柔的[npc.moan]，温柔地将[npc2.namePos]的[npc2.fingers]引导向自己[npc.breasts+]，然后又让[npc2.she]转去挑逗[npc.nipples+]。"
									+ "[npc.Name]指导[npc2.herHim]挤压揉捏着自己[npc.nipples+]，发出一阵愉悦的叫声，[npc2.name]"));
					break;
				case SUB_NORMAL: case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.hand]，鼓励[npc2.herHim]用[npc2.her][npc2.fingers+]轻轻抚摸自己[npc.breasts+]，随后又贪婪地引导[npc2.herHim]去触碰自己[npc.nipples+]。"
									+ "[npc.Name]让[npc2.herHim]拉扯揉捏着自己[npc.nipples+]，发出[npc.a_moan+]，[npc2.name]",

							"[npc.name]发出[npc.a_moan+]，迫不及待地将[npc2.namePos]的[npc2.fingers]引导向自己[npc.breasts+]，然后又让[npc2.she]转去挑逗[npc.nipples+]。"
									+ "[npc.Name]指导[npc2.herHim]挤压揉捏着自己[npc.nipples+]，发出一阵愉悦的叫声，[npc2.name]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.hand]，粗暴地强迫[npc2.her][npc2.fingers+]来回抚摸自己[npc.breasts+]，"
									+ "然后霸道地引导[npc2.herHim]摸向自己[npc.nipples+]。"
									+ "[npc.Name]命令[npc2.herHim]拉扯揉捏着自己[npc.nipples+]，发出[npc.a_moan+]，[npc2.name]",

							"[npc.name]发出[npc.a_moan+]，激烈地将[npc2.namePos]的[npc2.fingers]引导向自己[npc.breasts+]，然后又让[npc2.she]转去挑逗[npc.nipples+]。"
									+ "[npc.Name]命令[npc2.herHim]挤压揉捏着自己[npc.nipples+]，发出一阵愉悦的叫声，[npc2.name]"));
					break;
				case SUB_RESISTING:
					break;
			}
			
			switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append("没能从[npc.namePos][npc.breasts+]中挤出[npc.milk]。"); //Shouldn't be able to be reached
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"从[npc.namePos][npc.breasts+]中挤出了一点[npc.milk]。",

							"从[npc.namePos][npc.breasts+]中挤出了几滴[npc.milk]。"));
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"从[npc.namePos][npc.breasts+]中挤出了一小股[npc.milk]。",

							"从[npc.namePos][npc.breasts+]中挤出了一小缕[npc.milk]。"));
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"从[npc.namePos][npc.breasts+]中挤出了一股[npc.milk]。",

							"从[npc.namePos][npc.breasts+]中挤出了一缕[npc.milk]。"));
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"从[npc.namePos][npc.breasts+]中源源不断地挤出[npc.milk]。",

							"从[npc.namePos][npc.breasts+]中挤出了一连串的[npc.milk]。"));
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append("从[npc.namepos][npc.breasts+]中挤出了大量的[npc.milk]。");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append("从[npc.namePos][npc.breasts+]中挤出了相当多的[npc.milk]。");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append("从[npc.namepos][npc.breasts+]中挤出了巨量的[npc.milk]。");
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]开始挤[npc.Name]的奶，发出和缓的[npc2.moan]，"
										+ "[npc.Name]将[npc.breasts]压向[npc2.her]的[npc.hands]，[npc2.she]继续温柔地从[npc.namePos]的[npc.nipples]中挤出[npc.milk+]。",
	
								"[npc2.Name]享受着给[npc.namePos]的[npc.breasts]挤奶带来的愉悦感觉，不由得发出[npc2.a_moan+]，"
										+ "而[npc.name]也发出了同样的声音，这让[npc2.she]备受鼓舞，[npc2.she]继续温柔地从[npc.namePos]的[npc.nipples]中挤出[npc.milk+]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]开始挤[npc.Name]的奶，发出[npc2.a_moan+]，"
										+ "[npc.Name]将[npc.breasts]压向[npc2.her]的[npc.hands]，[npc2.she]继续饥渴地从[npc.namePos]的[npc.nipples]中挤出[npc.milk+]。",
	
								"[npc2.Name]享受着给[npc.namePos]的[npc.breasts]挤奶带来的愉悦感觉，不由得发出[npc2.a_moan+]，"
										+ "而[npc.name]也发出了同样的声音，这让[npc2.she]备受鼓舞，[npc2.she]继续贪婪地从[npc.namePos]的[npc.nipples]中挤出[npc.milk+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]开始挤[npc.Name]的奶，发出一阵低沉的咆哮，"
										+ "[npc.Name]将[npc.breasts]压向[npc2.her]的[npc.hands]，[npc2.she]继续粗暴地从[npc.namePos]的[npc.nipples]中挤出[npc.milk+]。",
	
								"[npc2.Name]享受着给[npc.namePos]的[npc.breasts]挤奶带来的感觉，不由得发出[npc2.a_moan+]，"
										+ "而[npc.name]也发出了同样的声音，这让[npc2.she]备受鼓舞，[npc2.she]继续粗暴地从[npc.namePos]的[npc.nipples]中挤出[npc.milk+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出[npc2.a_moan+]，试图逃脱，但[npc.Name]用力一拉，便将[npc2.her]的[npc2.hands]拽回了自己的[npc.breasts]上，"
										+ "很显然[npc2.she]别无选择，只能继续从[npc.namePos]的[npc.nipples]挤出[npc.milk+]。",
	
								"[npc2.Name]拼命地试图远离[npc.namePos]的[npc.breasts]，"
										+ "但[npc.Name]紧紧抓住[npc2.her]的[npc2.hand]，强迫[npc2.herHim]继续从[npc.namePos]的[npc.nipples]中挤出[npc.milk+]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastStoredMilk(-Main.sex.getCharacterPerformingAction().getBreastRawMilkStorageValue()/5);
		}
	};
	
	
	
	public static final SexAction NIPPLE_FINGERING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "指交乳头";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.fingers]插进[npc2.namePos]其中一个可操干的[npc2.nipples]，开始指交[npc2.her]的乳房。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.fingers]挑逗着[npc2.namePos][npc2.breasts+]，"
									+ "绕着[npc2.her]其中一个[npc2.nipples+]不停打转，然后缓缓地将指部推入[npc2.her]诱人的乳穴，使得[npc2.name]不禁深吸了一口气。",

							"[npc.Name]用[npc.fingers]压上[npc2.namePos]其中一个[npc2.nipples+]，"
									+ "[npc.she]以缓慢而稳定的力度，轻柔地将指部深深按在[npc2.namePos]的乳房上。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.fingers]挑逗着[npc2.namePos][npc2.breasts+]，"
									+ "绕着[npc2.her]其中一个[npc2.nipples+]不停打转，然后急切地将指部推入[npc2.her]诱人的乳穴，使得[npc2.name]不禁深吸了一口气。",

							"[npc.Name]用[npc.fingers]压上[npc2.namePos]其中一个[npc2.nipples+]，"
									+ "[npc.she]以稳定的力度，贪婪地将指部深深按在[npc2.namePos]的乳房上。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]揉弄挤捏着[npc2.namePos][npc2.breasts+]，"
									+ "[npc.she]用[npc.fingers]绕着[npc2.namePos]其中一个[npc2.nipples+]不停打转，然后粗暴地将指部强行推入[npc2.namePos]诱人的乳穴。",

							"[npc.name]贪婪地用[npc.fingers]压上[npc2.namePos]其中一个[npc2.nipples+]，"
									+ "发出轻声的咆哮，粗暴地将指部深深按在[npc2.namePos]的乳房上。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.fingers]挑逗着[npc2.namePos][npc2.breasts+]，"
									+ "绕着[npc2.her]其中一个[npc2.nipples+]急切地打转，然后饥渴地将指部推入[npc2.her]诱人的乳穴，使得[npc2.name]不禁深吸了一口气。",

							"[npc.Name]用[npc.fingers]压上[npc2.namePos]其中一个[npc2.nipples+]，"
									+ "[npc.she]以稳定的力度，饥渴地将指部深深按在[npc2.namePos]的乳房上。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.fingers]挑逗着[npc2.namePos][npc2.breasts+]，"
									+ "绕着[npc2.her]其中一个[npc2.nipples+]不停打转，然后急切地将指部推入[npc2.her]诱人的乳穴，使得[npc2.name]不禁深吸了一口气。",

							"[npc.Name]用[npc.fingers]压上[npc2.namePos]其中一个[npc2.nipples+]，"
									+ "[npc.she]以稳定的力度，贪婪地将指部深深按在[npc2.namePos]的乳房上。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]开始指交[npc2.namePos]的[npc2.breasts]，[npc2.name]发出一声轻柔的[npc2.moan]，"
										+ "[npc2.she]温柔地挺起胸部，让[npc.NamePos]的[npc.fingers]深深插入自己[npc2.nipple+]。",
	
								"[npc2.name]发出一声轻柔的[npc2.moan]，缓慢地挺起自己的胸部，"
										+ "恳求[npc.Name]将[npc.fingers+]更深地插入[npc2.her]的乳房。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]开始指交[npc2.namePos]的[npc2.breast(true)]，[npc2.name]发出[npc2.a_moan+]，"
										+ "[npc2.she]急切地挺起胸部，让[npc.NamePos]的[npc.fingers]深深插入自己[npc2.nipple+]。",
	
								"[npc2.name]发出[npc2.a_moan+]，饥渴地挺起了自己的胸部，"
										+ "恳求[npc.Name]将[npc.fingers]更深地插入[npc2.her]的乳房。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]开始指交[npc2.namePos]的[npc2.breasts]，[npc2.name]发出[npc2.a_moan+]，"
										+ "[npc2.she]激烈地挺起胸部，让[npc.name]触摸，命令[npc.Name]将[npc.fingers]更深地插入[npc2.her][npc2.nipple+]。",
	
								"[npc2.name]发出[npc2.a_moan+]，粗暴地挺起了胸部作为回应，"
										+ "命令[npc.Name]将[npc.fingers]更深地插入[npc2.her]的乳房。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]开始指交[npc2.namePos]的[npc2.breasts]，[npc2.name]发出[npc2.a_moan+]，"
										+ "[npc2.she]急切地挺起胸部，让[npc.NamePos]的[npc.fingers]更深地插入自己[npc2.nipple+]。",
	
								"[npc2.name]发出[npc2.a_moan+]，饥渴地挺起了自己的胸部，"
										+ "恳求[npc.Name]将[npc.fingers]更深地插入[npc2.her]的乳房。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]开始指交[npc2.namePos]的[npc2.breast(true)]，[npc2.name]发出[npc2.a_moan+]，"
										+ "[npc2.she]急切地挺起胸部，让[npc.NamePos]的[npc.fingers]更深地插入自己[npc2.nipple+]。",
	
								"[npc2.name]发出[npc2.a_moan+]，饥渴地挺起了自己的胸部，"
										+ "恳求[npc.Name]将[npc.fingers]更深地插入[npc2.her]的乳房。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]向后缩，"
										+ "[npc2.she]不舒服地扭动[npc2.sobbing]着，而[npc.name]则开始用[npc.fingers]在[npc2.her][npc2.nipple+]进进出出。",
	
								"[npc2.name]发出[npc2.a_sob+]，徒劳地挣扎扭身，试图拔出讨厌的插入物，"
										+ "挣扎抵抗着[npc.namePos]的触碰，因为[npc.she]正在把[npc.her][npc.fingers+]插入自己[npc2.nipple+]当中。"));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					return (UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]挺起[npc2.her]的胸部，"
									+ "[npc2.she]发出一声愉悦的[npc2.moan]，热切地乞求着[npc.Name]继续指交[npc2.her]的[npc2.breasts]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一声愉悦的[npc2.moan]，"
									+ "[npc2.she]饥渴地挺起胸部，让[npc.name]触摸，乞求[npc.Name]继续指交[npc2.her][npc2.nipples+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始挺起自己的胸部，"
									+ "做出有助于[npc.fingers]更深地插入[npc2.her][npc2.nipple+]的动作，急切地乞求[npc.Name]继续指交[npc2.her]的[npc2.breasts]。"));
				case SUB_RESISTING:
					return (UtilText.returnStringAtRandom(
							"[npc2.name]无法将胸部从[npc.namePos]那触摸下缩回，"
									+ "[npc2.name]发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，"
									+ "在[npc2.she]开始虚弱地试图推开[npc.name]之前，[npc.name]继续轻轻地触碰[npc2.her][npc2.nepp+]，[npc2.she]扭动着抗议。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试"
									+ "不让[npc.name]触碰自己的胸部，但[npc.name]还是将[npc.fingers]温柔地插入了[npc2.her][npc2.nipple+]，[npc2.she]奋力反抗着[npc.name]。"));
				case DOM_GENTLE:
					return (UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]挺起[npc2.her]的胸部，"
									+ "[npc2.she]发出一声愉悦的[npc2.moan]，热切地乞求着[npc.Name]继续指交[npc2.her]的[npc2.breasts]。",
	
							"[npc2.namePos][npc2.lips]间爆发出一声愉悦的[npc2.moan]，"
									+ "[npc2.she]温柔地挺起胸部，让[npc.name]触摸，乞求[npc.Name]继续指交[npc2.her][npc2.nipples+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地挺起自己的胸部，"
									+ "做出有助于[npc.fingers]更深地插入[npc2.her][npc2.nipple+]的动作，乞求[npc.Name]继续指交[npc2.her]的[npc2.breasts]。"));
				case DOM_ROUGH:
					return (UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]挺起了胸部，[npc2.she]发出一声愉悦的[npc2.moan]，命令[npc.Name]继续指交[npc2.her]的[npc2.breasts]。",
	
							"[npc2.namePos][npc2.lips]间爆发出一声愉悦的[npc2.moan]，"
									+ "[npc2.she]粗暴地挺起胸部，让[npc.name]触摸，命令[npc.Name]继续指交[npc2.her][npc2.nipples+]。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，开始挺起自己的胸部，"
									+ "命令[npc.name]继续指交[npc2.her][npc2.breasts]。她的突然移动让[npc.name]将[npc.fingers]深入[npc2.her][npc2.nipple+]。"));
				case SUB_NORMAL:
					return (UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]挺起自己的胸部，"
									+ "[npc2.she]恳求[npc.name]继续指交[npc2.her][npc2.breasts]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.namePos]的[npc2.lips]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]挺起胸部，让[npc.name]触摸，恳求[npc.Name]继续指交[npc2.her][npc2.nipples+]。",
		
							"[npc2.name][npc2.moaning+]着，开始挺起自己的胸部，"
									+ "做出有助于[npc.fingers+]更深地插入[npc2.her][npc2.nipple+]的动作，乞求[npc.Name]继续指交[npc2.her]的[npc2.breasts]。"));
			}
		}
		return "";
	}
	
	public static final SexAction NIPPLE_FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "指交乳头(温柔)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "指交[npc2.namePos]的乳头(温柔)";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]温柔地将[npc.fingers+]深深插入[npc2.namePos][npc2.nipple+]，开始缓慢地用指部在[npc2.her]的[npc2.breast(true)]进进出出。",

					"[npc.Name]温柔地靠着[npc2.Name]，使[npc2.herHim]吸入[npc.her][npc.scent+]，温柔地将[npc.fingers+]在[npc2.her][npc2.nipple+]中抽插。",

					"[npc.Name]温柔地贴在[npc2.name]身上，轻柔地用[npc.she][npc.hand+]在[npc2.namePos][npc2.nipple+]里进进出出，发出[npc.a_moan+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "指交乳头";
		}

		@Override
		public String getActionDescription() {
			return "继续指交[npc2.namePos][npc2.nipple+]。";
		}
		
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.fingers+]深深插入[npc2.namePos][npc2.nipple+]，开始急切地指交[npc2.her]的[npc2.breasts]，"
							+ "[npc.name]紧紧地贴住[npc2.name]，发出一阵[npc.a_moan+]。",

					"[npc.Name]靠着[npc2.herHim]，"
							+ "[npc.she]开始急切地将[npc.her][npc.fingers+]抽插[npc2.namePos][npc2.nipple+]，[npc.her][npc.scent+]击溃了[npc2.namePos]的理智。",

					"[npc.Name]紧紧贴在[npc2.name]身上，发出一声[npc.a_moan+]，[npc.she]开始急切地用[npc.fingers+]抽插[npc2.namePos][npc2.nipple+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "指交乳头(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "指交[npc2.namePos]的乳头(粗暴)";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]贪婪地将[npc.fingers+]深深地插入[npc2.namePos][npc2.nipple+]，"
							+ "[npc.she]贴向[npc2.name]，开始粗暴地用指部撞击，迅速地指交[npc2.namePos]的[npc2.breast(true)]。",

					"[npc.Name]紧紧贴向[npc2.name]，"
							+ "[npc.she]强迫[npc2.name]吸入[npc.her][npc.scent+]，开始粗暴地用[npc.fingers+]撞击抽插[npc2.namePos][npc2.nipple+]。",

					"[npc.Name]紧紧贴向[npc2.name]，发出[npc.a_moan+]，开始用[npc.her][npc.fingers+]粗暴地撞击抽插[npc2.namePos][npc2.nipple+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "指交乳头";
		}

		@Override
		public String getActionDescription() {
			return "继续指交[npc2.namePos]的乳头。";
		}
		
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.fingers+]深深插入[npc2.namePos][npc2.nipple+]，"
							+ "[npc.she]紧紧贴向[npc2.name]，开始用指部进进出出，指交[npc2.namePos]的[npc2.breast(true)]。",

					"[npc.Name]带着[npc.scent+]贴到[npc2.name]身边，用[npc.fingers+]抽插[npc2.her][npc2.nipple+]。",

					"[npc.Name]贴在[npc2.name]身上，发出[npc.a_moan+]，用[npc.fingers+]不断玩弄着[npc2.namePos][npc2.nipple+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_NIPPLE_FINGERING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "指交乳头(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "指交[npc2.namePos]的乳头(渴求)";
		}
		
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.fingers+]深深插入[npc2.namePos][npc2.nipple+]，"
							+ "[npc.she]紧紧贴向[npc2.name]，开始用指部进进出出，急切地指交[npc2.namePos]的[npc2.breast(true)]。",

					"[npc.Name]倚靠在[npc2.name]身上"
							+"[npc.she]散发着[npc.scent+]，不断用[npc.fingers+]抽插[npc2.her][npc2.nipple+]小穴。",

					"[npc.Name]紧紧贴在[npc2.name]身上，发出一声[npc.a_moan+]，[npc.she]开始急切地用[npc.fingers+]抽插[npc2.namePos][npc2.nipple+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗指交乳头";
		}

		@Override
		public String getActionDescription() {
			return "尝试把你的[npc.fingers]从[npc2.namePos][npc2.nipple+]中拔出。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
									+ "[npc.she]发出[npc.a_sob+]，不断挣扎着试图将[npc.fingers+]从[npc2.namePos][npc2.asshole+]中抽离。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将自己[npc.fingers+]从[npc2.namePos][npc2.nipple+]中抽离。",

							"[npc.name]拼命地尝试把自己[npc.fingers+]从[npc2.namePos][npc2.nipple+]中抽离，"
									+ "[npc2.name]牢牢控制住[npc.namePos]的[npc.hand]，[npc.Name]绝望地[npc.sob]着，"
									+ "于是[npc2.she]温柔地挺起自己的胸部，强迫[npc.namePos]的[npc.fingers]深深插入[npc2.her]的[npc2.breast(true)]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
									+ "[npc.she]发出[npc.a_sob+]，不断挣扎着试图将[npc.fingers+]从[npc2.namePos][npc2.asshole+]中抽离。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将自己[npc.fingers+]从[npc2.namePos][npc2.nipple+]中抽离。",

							"[npc.name]拼命地尝试把自己[npc.fingers+]从[npc2.namePos][npc2.nipple+]中抽离，"
									+ "[npc2.name]牢牢控制住[npc.namePos]的[npc.hand]，[npc.Name]绝望地[npc.sob]着，"
									+"接着[npc2.her]饥渴地晃动胸部，迎合着[npc.namePos]在[npc2.breast(true)]间抽插[npc.fingers]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
									+ "[npc.she]发出[npc.a_sob+]，不断挣扎着试图将[npc.fingers+]从[npc2.namePos][npc2.asshole+]中抽离。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将自己[npc.fingers+]从[npc2.namePos][npc2.nipple+]中抽离。",

							"[npc.name]拼命地尝试把自己[npc.fingers+]从[npc2.namePos][npc2.nipple+]中抽离，"
									+ "[npc2.name]牢牢控制住[npc.namePos]的[npc.hand]，[npc.Name]绝望地[npc.sob]着，"
									+"接着[npc2.her]粗暴地晃动胸部，强迫[npc.namePos]在[npc2.breast(true)]间抽插[npc.fingers]。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction NIPPLE_FINGERING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止指交乳头";
		}

		@Override
		public String getActionDescription() {
			return "把你的[npc.fingers]从[npc2.namePos]的乳头拔出去，停止指交[npc2.her]的乳房。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗暴地抽出[npc.fingers]，狠狠地捏了捏[npc2.namePos][npc2.breast(true)]，不再便不再指交。",

							"[npc.name]嗅着[npc2.namePos][npc2.scent+]深呼吸，粗暴地从[npc2.namePos][npc2.nipple(true)]中拔出[npc.fingers]。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.fingers]从[npc2.namePos]的乳头中抽出，最后一次捏了捏[npc2.her]的[npc2.breast(true)]，然后便不再指交[npc2.her]的胸部。",

							"[npc.name]嗅着[npc2.namePos][npc2.scent+]深呼吸，从[npc2.namePos][npc2.nipple(true)]中抽出[npc.fingers]。"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]松了一口气，然后继续反抗着[npc.name]，又发出了一阵[npc2.a_sob+]。",

							"[npc2.name]发出[npc2.a_sob+]，继续反抗并挣扎着，但[npc.name]依然将[npc2.she]固定在原位。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]停止玩弄[npc2.namePos][npc2.breasts+]，[npc2.Name]发出一阵[npc2.a_moan+]。",

							"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]渴望得到[npc.namePos]的更多关注。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction NIPPLE_FINGERED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "开始被指交乳头";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]开始指交你[npc.nipple+]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]温和地抓住[npc2.namePos]的[npc2.hand]，把[npc2.her]的[npc2.fingers]引向到自己的[npc.breasts]，"
									+ "[npc.her]发出[npc.a_moan+]，抓着[npc2.namePos]的手指深入[npc.her][npc.nipple+]。",
							
							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，引导着[npc2.her]的[npc2.fingers]摸向自己的[npc.breasts]并缓慢地揉了起来，"
									+ "[npc.her]温柔地抓着[npc2.namePos]的手指探入[npc.her][npc.nipple+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢抓住[npc2.namePos]的[npc2.hand]，急切地将[npc2.namePos]那[npc2.fingers]引导到自己的[npc.breasts]，"
									+ "发出[npc.a_moan+]，贪婪地将[npc2.namePos]的指部插入[npc.her][npc.nipple+]。",

							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，急切地引导[npc2.her]的[npc2.fingers]摸向自己的[npc.breasts]并缓慢地揉了起来，"
									+ "[npc.her]贪婪地抓着[npc2.namePos]的手指深入[npc.her][npc.nipple+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢钳制住[npc2.namePos]的[npc2.hand]，用[npc2.namePos]那[npc2.fingers]磨蹭自己的[npc.breasts]，"
									+ "发出[npc.a_moan+]，粗暴地将[npc2.namePos]的指部插入[npc.her][npc.nipple+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.hand]，激烈地将[npc2.namePos]那[npc2.fingers]拉向自己的[npc.breasts]，然后霸道地猛推，"
									+ "[npc.she]粗暴地将[npc2.namePos]的指部插进[npc.her][npc.nipple+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢抓住[npc2.namePos]的[npc2.hand]，急切地将[npc2.namePos]那[npc2.fingers]引导到自己的[npc.breasts]，"
									+ "发出[npc.a_moan+]，贪婪地将[npc2.namePos]的指部插入[npc.her][npc.nipple+]。",

							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，急切地引导[npc2.namePos]的[npc2.fingers]摸向自己的[npc.breasts]并缓慢地揉了起来，"
									+ "[npc.her]贪婪地抓着[npc2.namePos]的手指深入[npc.her][npc.nipple+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢抓住[npc2.namePos]的[npc2.hand]，将[npc2.namePos]那[npc2.fingers]引导到自己的[npc.breasts]，"
									+ "发出[npc.a_moan+]，将[npc2.namePos]的指部插入[npc.her][npc.nipple+]。",

							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，引导着[npc2.namePos]的[npc2.fingers]摸向自己的[npc.breasts]并缓慢地揉了起来，"
									+ "[npc.she]将[npc2.namePos]的指部推向[npc.her][npc.nipple+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.name]的身体，不禁发出轻柔的[npc2.moan]，然后弯起[npc2.fingers]，开始温柔地指交[npc.her][npc.nipple+]。",
	
								"[npc2.name]轻柔地[npc2.moan]着，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]温柔地将[npc2.hand]压入[npc.NamePos][npc.breast(true)]的软肉，开始指交[npc.NamePos][npc.nipple+]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.name]的身体，不禁发出一阵[npc2.a_moan+]，然后弯起[npc2.fingers]，开始急切地指交[npc.her][npc.nipple+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]急切地将[npc2.hand]压入[npc.NamePos][npc.breast(true)]的软肉，开始指交[npc.NamePos][npc.nipple+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]顶进[npc.Name]的身体，忘我地发出一阵[npc2.a_moan+]，为了提醒[npc.Name]谁才是主导者，"
										+ "[npc2.she]粗暴地弯起[npc2.fingers]，开始无情地指交[npc.namePos][npc.nipple+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]无情地指交[npc.namePos][npc.nipple+]，宣告着自己的支配权。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.name]的身体，不禁发出一阵[npc2.a_moan+]，然后弯起[npc2.fingers]，开始急切地指交[npc.her][npc.nipple+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]急切地将[npc2.hand]压入[npc.NamePos][npc.breast(true)]的软肉，开始指交[npc.NamePos][npc.nipple+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.name]的身体，不禁发出一阵[npc2.a_moan+]，然后弯起[npc2.fingers]，开始指交[npc.her][npc.nipple+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]将[npc2.hand]压入[npc.NamePos][npc.breast(true)]的软肉，开始指交[npc.NamePos][npc.nipple+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]紧紧裹住[npc2.name]那[npc2.fingers]，[npc2.her]不禁发出一声[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着，想要从[npc.namePos]的紧握中抽出自己的[npc2.hand]。",
	
								"[npc2.name]发出[npc2.a_sob+]，挣扎着从[npc.namePos]的紧握中抽出手，"
										+ "[npc2.her]乞求[npc.Name]不要再强迫自己的手指深入[npc.Name][npc.asshole+]内。"));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "被指交乳头(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地享受[npc2.namePos][npc2.fingers+]指交你[npc.nipple+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.breast(true)]温柔地压向[npc2.namePos]的[npc2.hand]，"
							+ "让[npc2.namePos][npc2.fingers+]深深插入自己[npc.nipple+]，发出一阵轻柔地[npc.moan]。",

					"[npc.Name]发出一阵轻柔地[npc.moan]，将[npc.breast(true)]温柔地压向[npc2.namePos]的[npc2.hand]，强迫[npc2.namePos][npc2.fingers+]在自己[npc.nipple+]中插得更深。",

					"[npc.name]缓慢地将[npc.breast(true)]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间飘出一声轻柔的[npc.moan]，[npc.her]设法让[npc2.namePos][npc2.fingers+]深深插入[npc.her][npc.nipple+]。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "享受指交";
		}

		@Override
		public String getActionDescription() {
			return "享受[npc2.namePos][npc2.fingers+]指交你[npc.nipple+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.breast(true)]压向[npc2.namePos]的[npc2.hand]，"
							+ "让[npc2.namePos][npc2.fingers+]深深插入自己[npc.nipple+]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，将[npc.breast(true)]竭力地压向[npc2.namePos]的[npc2.hand]，强迫[npc2.namePos][npc2.fingers+]在自己[npc.nipple+]中插得更深。",

					"[npc.name]亢奋地将[npc.breast(true)]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.fingers+]插入自己[npc.nipple+]深处。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "被指交乳头(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地强迫[npc2.namePos][npc2.fingers+]深入你[npc.nipple+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]粗鲁地将[npc.breast(true)]撞向[npc2.namePos]的[npc2.hand]，"
							+ "强迫[npc2.namePos][npc2.fingers+]粗暴地深深插入自己[npc.nipple+]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，强有力地将[npc.breast(true)]压向[npc2.namePos]的[npc2.hand]，强迫[npc2.namePos][npc2.fingers+]在自己[npc.nipple+]中插得更深。",

					"[npc.name]粗暴地将[npc.breast(true)]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.fingers+]有力地插入自己[npc.crotchNipple+]深处。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "享受指交乳头";
		}

		@Override
		public String getActionDescription() {
			return "享受[npc2.namePos][npc2.fingers+]指交[npc.her][npc.nipple+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.breast(true)]压向[npc2.namePos]的[npc2.hand]，"
							+ "让[npc2.namePos][npc2.fingers+]深深插入自己[npc.nipple+]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，将[npc.breast(true)]压向[npc2.namePos]的[npc2.hand]，强迫[npc2.namePos][npc2.fingers+]在自己[npc.nipple+]中插得更深。",

					"[npc.name]将[npc.breast(true)]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.fingers+]插入自己[npc.nipple+]深处。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "被指交乳头(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地将你[npc.breast+(true)]压向[npc2.namePos]的[npc2.hand]，让[npc2.she]指交你[npc.nipple+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.breast(true)]压向[npc2.namePos]的[npc2.hand]，"
							+ "让[npc2.namePos][npc2.fingers+]深深插入自己[npc.nipple+]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，将[npc.breast(true)]竭力地压向[npc2.namePos]的[npc2.hand]，强迫[npc2.namePos][npc2.fingers+]在自己[npc.nipple+]中插得更深。",

					"[npc.name]亢奋地将[npc.breast(true)]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.fingers+]插入自己[npc.nipple+]深处。"));

			return UtilText.nodeContentSB.toString();
		}

	};

	public static final SexAction NIPPLE_FINGERED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗指交乳头";
		}

		@Override
		public String getActionDescription() {
			return "尝试把[npc2.namePos]的[npc.fingers]从你[npc.nipple+]中拔出。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
									+"[npc.she]呜咽出声，挣扎着想把[npc2.namePos]的手指从自己[npc.nipple+]小穴中抽离。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.breast(true)]从[npc2.namePos]令人憎恶的抚摸下抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.fingers+]依然温柔地在[npc.her][npc.nipple+]里滑进滑出。",

							"[npc.name]拼命地尝试把[npc.breast(true)]从[npc2.namePos]的[npc2.hand]边挪开，"
									+"[npc2.namePos][npc2.fingers+]的更加深入弄得[npc.Name]痛哭出声。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
									+"[npc.her]发出[npc.a_sob+]，想把[npc2.namePos]贪婪的[npc2.fingers]从自己[npc.nipple+]抽离却使不上劲。",


							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.breast(true)]从[npc2.namePos]令人憎恶的抚摸下抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.fingers+]依然急切地在[npc.her][npc.nipple+]里滑进滑出。",


							"[npc.name]拼命地尝试把[npc.breast(true)]从[npc2.namePos]的[npc2.hand]边挪开，"
									+"[npc2.namePos]不断饥渴地抽插[npc2.fingers+]，弄得[npc.Name]痛哭出声。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
									+"[npc.her]发出[npc.a_sob+]，想把[npc2.namePos]粗鲁的[npc2.fingers]从自己[npc.nipple+]抽离却使不上劲。",


							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.breast(true)]从[npc2.namePos]令人憎恶的抚摸下抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.fingers+]依然粗暴地在[npc.her][npc.nipple+]里抽送爆操。",


							"[npc.name]拼命地尝试把[npc.breast(true)]从[npc2.namePos]的[npc2.hand]边挪开，"
									+"[npc2.namePos]不挺用力地戳入[npc2.fingers+]，弄得[npc.Name]痛哭出声。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止指交乳头";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.fingers]从你的乳头拔出去，停止指交你的乳房。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]那[npc2.fingers]从自己[npc.nipple+]里抽出，[npc.she]愤怒地咆哮着，命令[npc2.name]停止指交。",
	
							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]那[npc2.fingers]从自己[npc.nipple+]中抽出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]那[npc2.fingers]从自己[npc.nipple+]里抽出，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]停止指交。",
	
							"[npc.Name]倚靠在[npc2.name]身上，吸了一口[npc2.her]的[npc2.scent]，然后吧[npc2.her]的[npc2.fingers]从自己[npc.nipple+]中抽出。"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]松了一口气，但当[npc2.she]意识到[npc.name]还没完全满足时，又发出了一阵[npc2.a_sob+]。",
	
							"[npc2.name]发出一阵[npc2.a_sob+]，继续反抗并挣扎着，但[npc.name]依然牢牢地将[npc2.she]固定在原位。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]不再让[npc2.name]玩弄自己[npc.nipple+]，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
	
							"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]想更多关注[npc.namePos][npc.nipple+]的渴望。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
