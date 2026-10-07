package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.5.5
 * @author Innoxia
 */
public class PixShower {
	
	// Player:

	public static final SexAction PLAYER_KISS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "接吻";
		}

		@Override
		public String getActionDescription() {
			return "转头亲皮克斯。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isOrificeFree(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH) && Main.sex.isOrificeFree(Main.game.getPlayer(), SexAreaOrifice.MOUTH);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你把头转向一边，想要亲吻皮克斯。一瞬间你似乎已经跟她的[pix.lips]贴在了一起，但随着一声轻笑，她将身子一仰，你只亲到了一团空气。",
					"你回头看向皮克斯，迫不及待地呜咽着，身子后仰，想要亲上去。但她却只是将你躲开，让你[pc.lips]留在了半空中。你懊恼不已，脸颊上也微微泛起了红。",
					"你身子向后倚向皮克斯，转过头去想要亲她。她看到你的意图，只是轻笑几声轻松躲开了，你[pc.lips]没有碰到她分毫。",
					"你扭头看向皮克斯，想要亲上去，但她却也看出你的意图，笑着躲开了，让你觉得自己像个傻瓜一样。");
		}

		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_TRY_TO_FEEL_BREASTS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "捏乳头";
		}

		@Override
		public String getActionDescription() {
			return "向上摸索并捏你[npc.nipples+]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你感到欲求不满，便向着胸部摸去，期望能刺激一下自己[pc.nipples+]。"
							+ "而皮克斯一眼就看穿了你的行为，生气地把你的手从胸部拍开，惹得你急不可耐地呜咽起来。",
							
					"你摸索上去想要玩弄自己[pc.nipples+]，但你已经精疲力尽，动作十分缓慢，还没等你伸过一半的距离，皮克斯便把你的手从胸部拿开了。",
					
					"你迫切地呜咽起来，想要伸手揉搓自己的[pc.breasts]，但皮克斯却另有打算，扯着你的手远离了胸部。",
					
					"你迫不及待地伸出[pc.hand]来，想要玩弄自己[pc.nipples+]，可还没等伸到一半，皮克斯就紧紧抓住你的[pc.arms]，按回到身体两边。");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_TRY_TO_MASTURBATE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "自慰";
		}

		@Override
		public String getActionDescription() {
			return "摸索到股间并开始自慰。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你又急又羞，伸出[pc.hands]就向着胯下过去，但皮克斯却比现在的你快上太多，"
							+ "立马拉住你的手臂，塞到了背后，不允许你刺激自己。",
							
					"你试图把手放在腿间开始自慰，但皮克斯迅速抓住你的手腕并把你的手压在你背后。",
					
					"伴随着一声急不可耐的呜咽，你想伸手触碰股间。"
							+ "皮克斯看到你的动作，嬉笑着拧住了你的手腕，把你的手从胯下拽了回来，然后在你耳边威胁地低声道。",
					
					"你迫不及待地伸出[pc.hand]来，想要稍微自己刺激，可还没等伸到一半，皮克斯就将其拉开，在你耳边嬉笑起来。");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "说骚话";
		}

		@Override
		public String getActionDescription() {
			return "对皮克斯说骚话。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你刚一张嘴，正想对皮克斯说些什么，她直接立刻伸出[pix.hands+]捂住了你的嘴，在你的耳边响起了一阵银铃般的笑声，紧接着是让你保持安静的话语。",
							
					"你正想对皮克斯说些什么，但她却立马伸手捂住了你的嘴，到嘴边的话语立刻变成一串闷响。她在你肩头笑着告诉你，请保持安静，当个乖孩子。",
					
					"你刚一张嘴，一个字还没来得及说出，皮克斯就啪的一下把[pix.hands+]盖在了你[pc.lips+]上。",
					
					"你正准备回头对皮克斯说些什么，她就啪的一下把[pix.a_hand+]盖在了你的嘴上，在你耳边轻笑着，阻止了你说话。");
		}

		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_BREAK_FREE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "挣扎";
		}

		@Override
		public String getActionDescription() {
			return "尝试挣脱皮克斯的控制";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你已经受够了皮克斯的“乐趣”，不顾一切地扭动着身子，想要逃脱她的魔爪。"
							+ "可惜你的身体还没有从锻炼的疲惫中恢复过来，她轻轻松松就将你压倒在了淋浴间的墙上，"
							+ "玩味地在你耳边低哼着，你的逃脱计划戛然而止了。",
							
					"你调动起全身仅剩的力量，绝望地想要将皮克斯推开。"
							+ "她只是小小地嬉笑一声，就把你压在了淋浴间的墙上，微弱的低哼声在耳边回响。",
					
					"你扭来扭去，不顾一切地想要把皮克斯推开。"
							+ "她看到你虚弱的反抗，只是笑笑，便轻而易举地将你重新压倒在淋浴间的墙上，微弱的低哼声伴随着你的粗重的喘息在肩头回响。",
					
					"你努力把自己退离墙壁，想要将背后的皮克斯甩下来。"
							+ "可以你现在的状态，顶多只能她踉跄后退一点，随后她便威胁地咆哮一声，迅速把你撞回了墙上。");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
			}
		}
	};
	
	public static final SexAction PLAYER_SUBMIT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "顺从";
		}

		@Override
		public String getActionDescription() {
			return "让皮克斯对你为所欲为吧";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你意识到自己无法反抗皮克斯的“取乐”，索性向着那不饶人的犬女身上倒去，让她随心所欲地使用你。",
							
					"你决定不如让皮克斯随心所欲地使用你，便慢慢仰躺在她身上，引得她赞许地哼了一声。",
					
					"你将自己完全交给了皮克斯，向着面前淋浴间的墙上靠去，允许她为所欲为。",
					
					"你终于意识到皮克斯已经完全掌控了现状，于是向着面前淋浴间的墙上靠去，决定任她随心所欲。");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
			}
		}
	};
	
	public static final SexAction FINGERED_GRIND_DOWN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "磨蹭";
		}

		@Override
		public String getActionDescription() {
			return "用你的[npc.pussy]顶着皮克斯的手摩擦。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你放低了腰身，抵住皮克斯[pix.hand+]，那气势汹汹的犬女也热切地[pix.moan]了一声，用自己[pix.fingers+]深深探入你[pc.pussy+]，欣赏着你在淋雨的流水之下喘息、颤抖。",
					
					"你把身子靠向了皮克斯，用[pc.pussy+]对着她[pix.hand+]磨蹭起来，愉悦的呻吟声脱口而出，她也回应着你，将[pix.fingers+]深深送入你的[pc.pussy]。",
					
					"你慢慢地放低了身子，压住皮克斯的手，期待着她能更深一些，没想到的是她竟然还做出了回应，她轻轻弯曲[pix.fingers+]，急切地抚摸着阴道的肉壁。",
					
					"你将自己的[pc.pussy]压在皮克斯的手上，她则发出一声饥渴的咕哝声，将[pix.fingers]在你体内弯曲起来。"
							+ "她抚摸的手法仿佛在说：“来吧，来吧。”你仰倒在那盛气凌人的犬女身上，感受着温热的水流流淌过身体，不住地呻吟、颤抖着。");
		}
	};
	
	// Partner on player:
	
	public static final SexAction PARTNER_KISS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "亲吻";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"皮克斯轻笑一声，越过了你的肩膀，饶有兴致地轻咬着你的耳朵，但却依然将你死死地压在淋浴间的墙壁上。",
					
					"皮克斯玩味地一笑，忽然猛地把你按在墙上，越过肩头开始轻噬你的耳朵。",
					
					"温热的水流继续不断地打落在你们两个身上，皮克斯将[pix.face+]紧紧贴住你的脖子，随着一声[pix.moan]，她开始用[pix.tongue+]调皮地在你的肩膀上舔舐。",
					
					"皮克斯探过你的肩头，用她[pix.tongue+]饶有兴致地舔过你的脖子和耳朵。");
		}
	};
	
	public static final SexAction PARTNER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "说骚话";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
				
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"皮克斯把你按在墙上，在你耳边低声道，",
					"皮克斯死死地压住你的背部，在你的肩头咕哝道，"));
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.speech(乖[pc.girl]！真希望你喜欢这个奖励，就和我一样！)]",
					"[npc.speech(哦——你累坏了吧！这么缓解一下也蛮有意思的对吧？)]",
					"[npc.speech(我跟你说，你挣扎得比我之前奖励过的家伙可差远了！)]",
					"[npc.speech(你不觉得，超有意思吗？！好吧，至少我觉得！)]"));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_GROPE_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.game.getPlayer().hasBreasts();
		}
		
		@Override
		public String getActionTitle() {
			return "揉搓乳房";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB = new StringBuilder();
			
			UtilText.nodeContentSB.append(
					UtilText.returnStringAtRandom(
					"皮克斯举起[pix.a_hand+]摸索到你的胸部，对着你[pc.breasts+]其中一个揉捏起来，一串轻笑声从她的口中冒了出来。",
					
					"淋浴中的水流依旧不断地拍打在你赤裸的身体上，皮克斯举起[pix.a_hand+]摸索到你的胸部，伴随着愉悦的哼声，她对着你那[pc.breastRows][pc.breasts]狠狠地揉搓了起来。",
					
					"你[pc.breasts+]早已被淋浴的水流打湿，顿时成为了皮克斯的焦点。"
					+ "她伸出[pix.hands+]摸索到你的胸部，迫不及待地对着你胸前的肉团揉捏起来，你按捺不住发出[pc.a_moan+]，也引来了耳边一声轻哼。",
					
					"皮克斯对着你[pc.breasts+]，一个一个地揉搓过去，惹得你连连发出[pc.a_moan+]。"));
			
			switch (Main.game.getPlayer().getBreastStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append("她揉捏着你[pc.nipples+]，几滴[pc.milk]漏了出来，沾染了指尖。");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append("她揉捏着你[pc.nipples+]，一小股[pc.milk]沾染在了指尖上。");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append("她捏住你[pc.nipples+]，几滴[pc.milk]淌过了她的指尖。");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append("你的[pc.milk]沿着她[pix.fingers+]流了下来，与冲刷着[pc.breasts+]的淋浴水流混杂在一起。");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append("你的[pc.milk]逐渐顺着她[pix.fingers+]淌下来，与冲刷着[pc.breasts+]的淋浴水流混杂在一起。");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append("你的[pc.milk]汩汩地顺着她[pix.fingers+]流下，与冲刷着[pc.breasts+]的淋浴水流混杂在一起。");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append("你的[pc.milk]不断地涌出，顺着她[pix.fingers+]不断流下，与冲刷着[pc.breasts+]的淋浴水流混杂在一起。");
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Main.game.getPlayer().incrementBreastStoredMilk(-10);
		}
		
	};
	
	public static final SexAction PARTNER_MASTURBATE_PLAYER_COCK = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(null, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isPenetrationTypeFree(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER);
		}
		
		@Override
		public String getActionTitle() {
			return "撸动玩家的肉棒";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Main.sex.getCharacterPerformingAction(),
					UtilText.returnStringAtRandom(
					"皮克斯将[pix.hand]伸向你的股间，忽然捏住了[pc.cock+]，开始上下撸动起来，渐渐地你身子向后倒去，[pc.a_moan+]不断从口中发出。",
					
					"皮克斯伸出一只[pix.hands+]向你[pc.legs+]之间，一把抓住了[pc.cock+]，开始就着流水激烈地撸起管来。",
					
					"皮克斯伸出[pix.hand]握住了你[pc.cock+]，开始舒缓绵长地撸动起来，你按捺不住，一声[pc.a_moan+]脱口而出，身子也向皮克斯那边仰去。",
					
					"皮克斯握住了你[pc.cock+]，你禁不住连连发出[pc.moans+]。"
							+ "她在你的耳边轻声低语着，同时开始上下撸动起来，害得你[pc.moans]得越来越明显。"));
		}
	};
	
	public static final SexAction FINGERING_PLAYER_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "开始指交";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Main.sex.getCharacterPerformingAction(),
					"你感觉得到皮克斯把身子紧紧贴在了你的背上，稍带疯狂的语调在你耳边低语着，随后便伸出[pix.a_hand+]开始挑弄爱抚你的大阴唇。"
						+ "伴随着[pc.a_moan+]，你倚到了她的身上，飞落而下的水流的拍打声在狭窄的隔间中回响，她忽然将[pix.fingers+]一捅，轻盈的一下便插入了你[pc.pussy+]。");
		}
	};
	
	public static final SexAction FINGERING_PLAYER_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "指交(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"皮克斯将她[pix.fingers+]在你[pc.pussy+]中一勾，做出一个“来啊”的手势轻抚着内壁，引得你抵在墙上轻声喘息不断。",
					
					"皮克斯将[pix.fingers+]推入你[pc.pussy+]中，开始缓缓地抽插起来，惹得你口中不住轻声呻吟，身子也向后倒去，被她接了住。",
					
					"皮克斯缓缓地将[pix.fingers+]送入你[pc.pussy+]的深处，让你不仅发出一声[pc.a_moan+]。"
							+ "你感觉得到她的手掌已经跟你的阴唇紧密贴合在一起，她忽然勾起了[pix.fingers]，抚摸着阴道的肉壁，害得你又是一声[pc.moan+]。",
					
					"皮克斯逐渐沉浸于在你[pc.pussy+]中翻弄手指的感觉，你发出[pc.a_moan+]，仰躺到了她的身上。"
							+ "洒落的水声在四周回响，皮克斯在你的体内弯起了[pix.fingers+]，开始轻抚着你的阴道壁。");
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction FINGERING_PLAYER_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "指交(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"皮克斯粗暴地将[pix.fingers+]刺入了你[pc.pussy+]中，在里面摆出了“来啊”的手势，用力地抚摸着，惹得你连连发出[pc.moans+]。",
					
					"皮克斯硬生生地把[pix.fingers+]刺入了你[pc.pussy+]中，毫不顾忌地一抽一插起来，惹得你不免从[pc.lips]中连连漏出[pc.moans+]，向后瘫去，被她接了住。",
					
					"皮克斯猛地将[pix.fingers+]深深刺入你[pc.pussy+]，让你不仅发出[pc.a_moan+]。"
							+ "你感受到她的手掌不时就会跟你的阴唇来一场亲密接触，她忽然将[pix.fingers]弯了起来，急切地触摸着你的阴道肉壁，让你又是一声[pc.moan+]。",
					
					"皮克斯的[pix.fingers]深插入了你[pc.pussy+]，你发出[pc.a_moan+]，向她的身上瘫软了下去。"
							+ "溅落而下的水流声在周围回响着，皮克斯在你体内粗暴地勾起[pix.fingers+]，对着阴道内壁揉摸起来。");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
			}
		}
	};
	
	public static final SexAction FINGERING_PLAYER_CLIT_PLAY = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "玩弄阴蒂";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"皮克斯将[pix.finger]缓缓插入了你[pc.pussy+]，而伸出的拇指则正对着你的阴核。"
							+ "接着拇指便压了下来，对着你敏感的小豆豆揉弄起来，你发出[pc.a_moan+]，仰躺到了她的身上。",
					
					"皮克斯竖起拇指对准了你脆弱的阴核，开始揉弄起来。"
							+ "你忍不住叫了出来，仰躺到了她的身上，你[pc.moans+]声几乎完全被流水声所掩盖，她则继续玩弄着你那雌性的核。",
					
					"你连连喘息着，感觉到皮克斯的拇指压住阴蒂后，主动向着手的方向顶去。"
							+ "你躺倒在那高高在上的犬女怀里，发出了[pc.moans+]，用下体对着她的手磨蹭起来，她也不停对着那颗豆豆玩弄着。",
					
					"皮克斯把拇指压在了你敏感的阴蒂上，害得你你尖声衣角，向后倒去。"
							+ "她仍旧不停地用手指在你[pc.pussy+]里抽插，你感觉得到她正全神贯注地玩弄着你敏感的豆豆，没过多久，你便已经[pc.moans+]连连。");
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PARTNER_ASK_FOR_PROMISE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !SexFlags.pixDemandedPromise
					&& Main.game.getPlayer().getArousal()>=ArousalLevel.FOUR_PASSIONATE.getMinimumValue();
		}
		
		@Override
		public String getActionTitle() {
			return "要求保证";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}

		@Override
		public String getDescription() {
			return "皮克斯忽然把你按在墙上，越过你的肩头，在你耳边轻声低吼道，"
						+"[npc.speechNoEffects(听着，我想跟你来个交易，给我仔细听着好吗？"
						+ "是这样的，你觉得自己要去了的时候，必须向我保证，今后你还会再来锻炼，明白吗？"
						+ "我要的是<i>真正的</i>承诺！)]"
					+ "<br/><br/>"
					+ "你也毫无打断她的余地，只好继续让皮克斯把你按在墙上，聆听她所谓的“交易”，"
					+"[npc.speechNoEffects(我告诉你，如果我愿意，可以让这个持续一整天，所以如果你准备好高潮了，那就跟我保证，下次一定还会来锻炼，懂了？！"
					+ "你要是不愿意松口，那你这辈子也别想去！"
					+ "希望你都听明白了，因为，我可不是跟你开玩笑！)]"
					+ "<br/><br/>"
					+ "你正想回应，皮克斯却立刻用一只[pix.hands+]堵住了你的嘴，提醒着你在快要高潮的时候，是不允许说话的。";
		}

		@Override
		public void applyEffects() {
			SexFlags.pixDemandedPromise = true;
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>=1;
		}
		
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "皮克斯满足地长吁一声，终于把你放开，结果你却瘫倒在了她的怀里，一点力气也没有了。";
		}

		@Override
		public void applyEffects() {
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};


	// Player:
	
	public static final SexAction PLAYER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "准备";
		}

		@Override
		public String getActionDescription() {
			return "你能感觉到[npc2.name]马上就要高潮了。做好准备。";
		}
		
		@Override
		public String getDescription() {
			return "皮克斯用全身的力量压住了你的背，让你一动也动不了；她在高潮前夕将你钉在了淋浴间的墙上。";
		}
	};
	
	public static final SexAction PLAYER_ORGASM_PROMISE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "保证";
		}

		@Override
		public String getActionDescription() {
			return "向皮克斯保证，你还会再来的。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("你受不了皮克斯的调戏了，一股难以抑制的热量开始在下体聚集，你发出一声[pc.a_moan+]，听从了她的要求，"
										+ "[pc.speech(好吧！我保证下次还会来锻炼的！求你了，让我去吧！)]."
										+ "<br/><br/>"
										+ "皮克斯愉悦地叫了一声，还没等你反应过来，她就如箭一般将双手塞进了你的股间，在你耳边低吟起来，"
										+ "[pix.speech(真是乖孩子！听着，这么乖的小家伙应该得到些奖励才是！)]");
			
			UtilText.nodeContentSB.append("<br/><br/>");
			// PENIS ORGASM:
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()){
				
				UtilText.nodeContentSB.append("你感到皮克斯用一只[pix.hands+]握住了你[pc.cock+]，随后便用力地撸动起来，她一刻不停地在你耳边诉说着，你有多么的听话。");
				
				// Describe cum amount:
				UtilText.nodeContentSB.append("在她精妙的手法之下，你没有挺过几秒，便感觉[pc.balls+]一紧");
				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append("，你这才发现自己竟然连一滴都没有了。顿时发出[pc.a_moan+]。");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append("，你感到几滴[pc.cum+]挤了出来");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append("，你感到有一些[pc.cum+]挤了出来");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append("，你感到[pc.cum+]挤了出来");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append("，你感到[pc.cum+]射了出来");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append("，你感到[pc.cum+]溢了出来");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append("，你感到[pc.cum+]溢了出来");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append("，你感到[pc.cum+]溢了出来");
						break;
					default:
						break;
				}
				
				// Describe where cum is going:
				if(Main.game.getPlayer().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE){
					UtilText.nodeContentSB.append("，溅到了面前的墙上。");
				}
				
				UtilText.nodeContentSB.append("<br/><br/>");
			}
			
			// VAGINA ORGASM:
			
			if (Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				
				UtilText.nodeContentSB.append("皮克斯将自己[pix.breastRows][pix.breasts+]压在你的背上，你顿时感到一阵无可抵抗的热流冲向你[pc.pussy+]。"
						+ "皮克斯轻笑一声，将两根[pix.fingers+]深深地送入了你[pc.pussy+]，又用另一只手对着你敏感的阴核揉搓起来，你在狂喜的快感中浪叫着。"
						+ "伴随着最后一声刺破耳膜的尖叫，高潮向你袭来，你竟瘫倒在了皮克斯的怀里，双腿已经不听使唤了。");
			}
			
			// MOUND ORGASM:
			if (!Main.game.getPlayer().hasPenisIgnoreDildo() && Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				UtilText.nodeContentSB.append("随着一声刺破耳膜的尖叫，你的腿不住颤抖着，仿佛一阵阵纯粹的极乐感在冲刷着你。"
						+ "皮克斯对着你格外敏感的胯部轻浮揉搓起来，你无性征的下体里，肌肉开始收缩痉挛，口中不禁冒出一声无法抗拒的尖叫。"
						+ "伴随着最后一声刺破耳膜的尖叫，高潮向你袭来，你竟瘫倒在了皮克斯的怀里，双腿已经不听使唤了。");
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PLAYER_ORGASM_REFUSE = new SexAction(
			SexActionType.ORGASM_DENIAL,
			ArousalIncrease.NEGATIVE_MAJOR,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "拒绝";
		}

		@Override
		public String getActionDescription() {
			return "不发一语，希望皮克斯没注意到你就要高潮了。";
		}

		@Override
		public String getDescription() {
			return "你感到下体泛起一股难以抑制的热流，但你却又不想向皮克斯保证，下次还会来见她，于是决定保持沉默，不用她做什么你照样可以高潮。"
					+ "你正在逐步临近高潮，但忽然你发出了一小声[pc.moan]。"
					+ "<br/><br/>"
					+ "你暴露了，没能保持完全安静，皮克斯也瞬间明白过来，发出一声怒吼，揪住了你的[pc.arms]，反压在背上，把你按朝墙上按去，"
					+ "[pix.speech(我刚刚怎么说的？！你不发誓就<i>不准</i>高潮！我告诉过你了，我这样一整天都没问题！)]"
					+ "<br/><br/>"
					+"她把你钉在了墙上，一直持续了好几分钟，你无论如何挣扎也无法挣脱。"
					+ "你甚至绝望到用下体摩擦淋浴间的墙面，但终归是徒劳无功，高潮感渐渐褪去了，你变得格外燥热难耐。"
					+ "皮克斯确认你确实平静下来后，才终于将你放开，用[pix.hands]在你身上爱抚起来，将你带入另一场高潮的边缘……";
		}

		@Override
		public void applyEffects() {
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			}
		}
	};
	
	// Pix orgasm:
	
	public static final SexAction PARTNER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "准备";
		}

		@Override
		public String getActionDescription() {
			return "你能感觉到[npc2.name]马上就要高潮了。做好准备。";
		}
		
		@Override
		public String getDescription() {
			return "皮克斯忽然把你压在墙上，越过你的肩头，在你耳边低吼道，"
					+"[npc.speech(哦，要去了吗？赶紧发誓吧，你会再来的，不然就别想结束！)]";
		}
	};
	
	public static final SexAction PARTNER_ORGASM_PIX_REWARD = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "高潮";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "你听到皮克斯的嘴角漏出了小声的呜咽，立刻明白过来，她单单靠着阻止你高潮，就要去了。"
					+ "你意识到这是个重获自由的好机会，于是准备把她推开，但反而只是让她呻吟得更大声了，"
					+ "随着一声刺破耳膜的尖叫，她把你按在了墙上，用[pix.pussy]紧紧压住你的[pc.ass]，感受着高潮的到来。"
					+ "<br/><br/>"
					+ "过了一会儿，她才终于恢复，直起身子，在你耳边呼出一声愉悦的长叹，"
					+ "[pix.speech(妈的……太爽了！)]";
		}

		@Override
		public void applyEffects() {
		}
	};
	


}
