package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.LolificationProject.Lexi.character.body.types.LexiTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class TailType {
	
	public static final AbstractTailType NONE = new AbstractTailType(
			null,
			Race.NONE,
			PenetrationGirth.THREE_AVERAGE,
			0f,
			"无",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues(),
			Util.newArrayListOfValues(),
			"",
			"",
			Util.newArrayListOfValues(),
			Util.newArrayListOfValues(),
			"#IF(npc.getTailCount()==1)"
				+ "当[npc.She]感到[npc.tail]逐渐缩小，并消失在[npc.her]背后时，[npc.She]倒吸了一口凉气。"
			+ "#ELSE"
				+ "当[npc.She]感到[npc.tails]逐渐缩小，并消失在[npc.her]背后时，[npc.She]倒吸了一口凉气。"
			+ "#ENDIF"
			+ "<br/>"
			+ "[npc.Name]现在[style.boldTfGeneric(没有尾巴)]。",
			"[style.colourDisabled([npc.She]没有尾巴。)]",
			Util.newArrayListOfValues(), false) {
	};
	
	public static final AbstractTailType DEMON_COMMON = new AbstractTailType(
			BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			PenetrationGirth.ONE_SLENDER,
			1f,
			"恶魔(桃心形)",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("桃心形", "恶魔"),
			Util.newArrayListOfValues("桃心形", "恶魔"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("桃心形"),
			Util.newArrayListOfValues("桃心形"),
			"#IF(npc.getTailCount()==1)"
				+ "一条桃心状的恶魔尾巴，尖端有一簇绒毛，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够随心所欲地控制其运动，仿佛拥有了额外的肢体。"
				+ "<br/>"
				+ "[npc.Name]现在拥有"
				+ "#IF(npc.isShortStature())"
					+ "一条[style.boldImp(小恶魔尾巴)]"
				+ "#ELSE"
					+ "一条[style.boldDemon(恶魔尾巴)]"
				+ "#ENDIF"
				+ "，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.tailCount]条桃心状的恶魔尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够随心所欲地控制其运动，仿佛拥有了额外的肢体。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条"
				+ "#IF(npc.isShortStature())"
					+ "[style.boldImp(小恶魔尾巴)]"
				+ "#ELSE"
					+ "[style.boldDemon(恶魔尾巴)]"
				+ "#ENDIF"
				+ "，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条桃心状的[npc.tailColour(true)]#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF尾巴。[npc.sheHasFull]能自如地掌控其运动，帮助[npc.herHim]抓握物体。"
				+ "#ELSE"
					+ "[npc.tailCount]条桃心状的，[npc.tailColour(true)]#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF尾巴。它活动自如，能够帮助[npc.herHim]抓取并握住物体。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_SKIN,
					BodyPartTag.TAIL_TAPERING_EXPONENTIAL), false) {
	};
	
	public static final AbstractTailType DEMON_HAIR_TIP = new AbstractTailType(
			BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			PenetrationGirth.ONE_SLENDER,
			0.5f,
			"恶魔(绒毛尖)",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("绒毛尖端", "恶魔"),
			Util.newArrayListOfValues("绒毛尖端", "恶魔"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("簇状毛发"),
			Util.newArrayListOfValues("簇状毛发"),
			"#IF(npc.getTailCount()==1)"
				+ "[npc.her]屁股上方长出了一条恶魔般的尾巴，顶部有着绒毛，且迅速变长，直到大约[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够随心所欲地控制其运动，仿佛拥有了额外的肢体。"
				+ "<br/>"
				+ "[npc.Name]现在拥有"
				+ "#IF(npc.isShortStature())"
					+ "一条[style.boldImp(小恶魔尾巴)]"
				+ "#ELSE"
					+ "一条[style.boldDemon(恶魔尾巴)]"
				+ "#ENDIF"
				+ "，[npc.materialDescriptor][npc.tailFullDescription(true)]，尖端有着[#npc.getCovering(BODY_COVERING_TYPE_HAIR_DEMON).getFullDescription(npc, true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条恶魔尾巴，尖端有一簇绒毛，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够随心所欲地控制其运动，仿佛拥有了额外的肢体。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条"
				+ "#IF(npc.isShortStature())"
					+ "[style.boldImp(小恶魔尾巴)]"
				+ "#ELSE"
					+ "[style.boldDemon(恶魔尾巴)]"
				+ "#ENDIF"
				+ "，[npc.materialDescriptor][npc.tailFullDescription(true)]，尖端有着[#npc.getCovering(BODY_COVERING_TYPE_HAIR_DEMON).getFullDescription(npc, true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条[npc.tailColour(true)]的#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF尾巴，尖端带有[#npc.getCovering(BODY_COVERING_TYPE_HAIR_DEMON).getFullDescription(npc, true)]，"
						+ "[npc.sheHasFull]能自如地掌控其运动，帮助[npc.herHim]抓握物体。"
				+ "#ELSE"
					+ "[npc.tailCount]条[npc.tailColour(true)]的#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF尾巴，尖端带有[#npc.getCovering(BODY_COVERING_TYPE_HAIR_DEMON).getFullDescription(npc, true)]，"
						+ "[npc.sheHasFull]能自如地掌控其运动，帮助[npc.herHim]抓握物体。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_SKIN,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};

	public static final AbstractTailType DEMON_TAPERED = new AbstractTailType(
			BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			PenetrationGirth.THREE_AVERAGE,
			0.75f,
			"恶魔(尖头)",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("尖头", "恶魔"),
			Util.newArrayListOfValues("尖头", "恶魔"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("尖头"),
			Util.newArrayListOfValues("尖头"),
			"#IF(npc.getTailCount()==1)"
				+ "一条尖头的恶魔尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够随心所欲地控制其运动，仿佛拥有了额外的肢体。"
				+ "<br/>"
				+ "[npc.Name]现在拥有"
				+ "#IF(npc.isShortStature())"
					+ "一条[style.boldImp(尖头的小恶魔尾巴)]"
				+ "#ELSE"
					+ "一条[style.boldDemon(尖头的恶魔尾巴)]"
				+ "#ENDIF"
				+ "，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条尖头的恶魔尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够随心所欲地控制其运动，仿佛拥有了额外的肢体。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条"
				+ "#IF(npc.isShortStature())"
					+ "[style.boldImp(尖头的小恶魔尾巴)]"
				+ "#ELSE"
					+ "[style.boldDemon(尖头的恶魔尾巴)]"
				+ "#ENDIF"
				+ "，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条尖头的[npc.tailColour(true)]#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF尾巴。[npc.sheHasFull]能自如地掌控其运动，帮助[npc.herHim]抓握物体。"
				+ "#ELSE"
					+ "[npc.tailCount]条尖头的[npc.tailColour(true)]#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF尾巴。[npc.sheHasFull]能自如地掌控其运动，帮助[npc.herHim]抓握物体。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_SKIN,
					BodyPartTag.TAIL_TAPERING_LINEAR), false) {
	};
	
	public static final AbstractTailType DEMON_HORSE = new AbstractTailType(
			BodyCoveringType.HORSE_HAIR,
			Race.DEMON,
			PenetrationGirth.THREE_AVERAGE,
			0.3f,
			"恶魔(马)",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("马一般"),
			Util.newArrayListOfValues("马一般"),
			"末端",
			"末端",
			Util.newArrayListOfValues("蓬松"),
			Util.newArrayListOfValues("蓬松"),
			"#IF(npc.getTailCount()==1)"
				+ "一条马一般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长，垂荡在身后。"
				+ "[npc.She]很快意识到自己的控制有限，只能令其左右摇摆。"
				+ "<br/>"
				+ "[npc.Name]现在拥有"
				+ "#IF(npc.isShortStature())"
					+ "一条[style.boldImp(马一般的小恶魔尾巴)]"
				+ "#ELSE"
					+ "一条[style.boldDemon(马一般的恶魔尾巴)]"
				+ "#ENDIF"
				+ "，由[npc.tailFullDescription(true)]制成。"
			+ "#ELSE"
				+ "[npc.TailCount]条马一般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长，垂荡在身后。"
				+ "[npc.She]很快意识到自己的控制有限，只能令其左右摇摆。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条"
				+ "#IF(npc.isShortStature())"
					+ "[style.boldImp(马一般的小恶魔尾巴)]"
				+ "#ELSE"
					+ "[style.boldDemon(马一般的恶魔尾巴)]"
				+ "#ENDIF"
				+ "，由[npc.tailFullDescription(true)]制成。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF马尾，"
						+ "[npc.she]能够四处摆荡，但此外不能进行更精密的控制。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF马尾，"
						+ "[npc.she]能够四处摆荡，但此外不能进行更精密的控制。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_HAIR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};

	public static final AbstractTailType DEMON_OVIPOSITOR = new AbstractTailType(
			BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			PenetrationGirth.THREE_AVERAGE,
			0.75f,
			"恶魔(产卵器)",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("可产卵", "恶魔"),
			Util.newArrayListOfValues("可产卵", "恶魔"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("可产卵"),
			Util.newArrayListOfValues("可产卵"),
			"#IF(npc.getTailCount()==1)"
				+ "一条尖头的恶魔尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "末端微微膨胀成一个尖状鼓包，而尖端有着紧绷的十字形狭缝。"
				+ "[npc.Name]发出一声[npc.a_moan]，仿佛本能般得知这条尾巴可以用做产卵器。"
				+ "[npc.she]将[npc.tail]四处摆动，很快意识到自己能够随心所欲地控制其运动，仿佛拥有了额外的肢体。"
				+ "<br/>"
				+ "[npc.Name]现在拥有"
				+ "#IF(npc.isShortStature())"
					+ "一条[style.boldImp(可产卵的小恶魔尾巴)]"
				+ "#ELSE"
					+ "一条[style.boldDemon(可产卵的恶魔尾巴)]"
				+ "#ENDIF"
				+ "，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条尖头的恶魔尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "每一条的末端都微微膨胀成一个尖状鼓包，而尖端有着紧绷的十字形狭缝。"
				+ "[npc.Name]发出一声[npc.a_moan]，仿佛本能般得知这些尾巴可以用做产卵器。"
				+ "[npc.she]将[npc.tails]四处摆动，很快意识到自己能够随心所欲地控制其运动，仿佛拥有了额外的肢体。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条"
				+ "#IF(npc.isShortStature())"
					+ "[style.boldImp(可产卵的小恶魔尾巴)]"
				+ "#ELSE"
					+ "[style.boldDemon(可产卵的恶魔尾巴)]"
				+ "#ENDIF"
				+ "，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条尖头的[npc.tailColour(true)]#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF尾巴。[npc.sheHasFull]能自如地掌控其运动，帮助[npc.herHim]抓握物体。"
					+ "末端微微膨胀成一个尖状鼓包，而尖端有着紧绷的十字形狭缝，能够用作产卵器。"
				+ "#ELSE"
					+ "[npc.tailCount]条尖头的[npc.tailColour(true)]#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF尾巴。[npc.sheHasFull]能自如地掌控其运动，帮助[npc.herHim]抓握物体。"
					+ "末端微微膨胀成一个尖状鼓包，而尖端有着紧绷的十字形狭缝，能够用作产卵器。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_SKIN,
					BodyPartTag.TAIL_TAPERING_LINEAR,
					BodyPartTag.TAIL_OVIPOSITOR),
			false) {
	};
	
	public static final AbstractTailType ALLIGATOR_MORPH = new AbstractTailType(
			BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			PenetrationGirth.FIVE_THICK,
			0.6f,
			"鳄鱼",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("鳄鱼般"),
			Util.newArrayListOfValues("鳄鱼般"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"#IF(npc.getTailCount()==1)"
				+ "一条鳞片覆盖的鳄鱼般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够使其带着巨大的力量摆动起来。"
				+ "<br/>"
				+ "[npc.Name]现在拥有一条[style.boldAlligatorMorph(鳄鱼般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条鳞片覆盖的鳄鱼般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够使其带着巨大的力量摆动起来。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldAlligatorMorph(鳄鱼般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]鳄鱼尾巴，[npc.she]能够使其带着巨大的力量摆动起来。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]鳄鱼尾巴，[npc.she]能够使其带着巨大的力量摆动起来。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_SCALES,
					BodyPartTag.TAIL_TAPERING_LINEAR,
					BodyPartTag.TAIL_ATTACK), false) {
	};
	
	public static final AbstractTailType BAT_MORPH = new AbstractTailType(
			BodyCoveringType.BAT_SKIN,
			Race.BAT_MORPH,
			PenetrationGirth.ONE_SLENDER,
			0.2f,
			"蝙蝠",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("蝙蝠般"),
			Util.newArrayListOfValues("蝙蝠般"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("毛茸茸"),
			Util.newArrayListOfValues("毛茸茸"),
			"#IF(npc.getTailCount()==1)"
				+ "一条小巧的蝙蝠般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够适当地控制其运动，随意转向各处。"
				+ "<br/>"
				+ "[npc.Name]现在拥有一条[style.boldBatMorph(蝙蝠般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]小巧的蝙蝠般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够适当地控制其运动，随意转向各处。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldBatMorph(蝙蝠般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]蝙蝠尾巴，"
						+ "[npc.she]能够迅速地将其上下移动，有利于保持平衡，并在战斗中协助行动。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]蝙蝠尾巴，"
						+ "[npc.she]能够迅速地将其上下移动，有利于保持平衡，并在战斗中协助行动。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_GENERIC,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType CAT_MORPH = new AbstractTailType(
			BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			PenetrationGirth.TWO_NARROW,
			0.8f,
			"猫类",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("猫一般", "毛茸茸"),
			Util.newArrayListOfValues("猫一般", "毛茸茸"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("毛茸茸"),
			Util.newArrayListOfValues("毛茸茸"),
			"#IF(npc.getTailCount()==1)"
				+ "一条毛茸茸的猫一般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够适当地控制其运动，随意转向各处。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldCatMorph(猫一般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条毛茸茸的猫一般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够适当地控制其运动，随意转向各处。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldCatMorph(猫一般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]猫尾巴，能够有效地帮助[npc.herHim]保持平衡。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]猫尾巴，能够有效地帮助[npc.herHim]保持平衡。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType CAT_MORPH_SHORT = new AbstractTailType(
			BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			PenetrationGirth.THREE_AVERAGE,
			0.2f,
			"猫类(短)",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("猫一般", "短小", "毛茸茸"),
			Util.newArrayListOfValues("猫一般", "短小", "毛茸茸"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("毛茸茸"),
			Util.newArrayListOfValues("毛茸茸"),
			"#IF(npc.getTailCount()==1)"
				+ "一条毛茸茸的猫一般的短尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "虽然[npc.She]能够适当地控制其运动，但由于太短了，做不了什么事情。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldCatMorph(猫一般的短尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条毛茸茸的猫一般的短尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "虽然[npc.She]能够适当地控制其运动，但由于太短了，做不了什么事情。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldCatMorph(猫一般的短尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]短猫尾，"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]短猫尾，"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType CAT_MORPH_TUFTED = new AbstractTailType(
			BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			PenetrationGirth.THREE_AVERAGE,
			0.4f,
			"猫类(簇状)",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("猫般", "簇状", "毛茸茸"),
			Util.newArrayListOfValues("猫般", "簇状", "毛茸茸"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("簇状"),
			Util.newArrayListOfValues("簇状"),
			"#IF(npc.getTailCount()==1)"
				+ "一条毛茸茸的猫一般的短尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "末端有一簇松软的皮毛，[npc.she]能够迅速地将其上下左右移动，有利于保持平衡，并在战斗中协助行动。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldCatMorph(猫一般的尾巴)]，末端长着一簇皮毛，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条毛茸茸的猫一般的短尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "末端有一簇松软的皮毛，[npc.she]能够迅速地将其上下左右移动，有利于保持平衡，并在战斗中协助行动。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldCatMorph(猫一般的尾巴)]，末端长着一簇皮毛，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]猫尾巴，末端长着一簇皮毛。"
					+ "能够有效地帮助[npc.herHim]保持平衡"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]猫尾巴，末端长着一簇皮毛。"
					+ "能够有效地帮助[npc.herHim]保持平衡"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType COW_MORPH = new AbstractTailType(
			BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			PenetrationGirth.TWO_NARROW,
			0.35f,
			"牛",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("牛一般"),
			Util.newArrayListOfValues("牛一般"),
			"末端",
			"末端",
			Util.newArrayListOfValues("簇状毛发"),
			Util.newArrayListOfValues("簇状毛发"),
			"#IF(npc.getTailCount()==1)"
				+ "一条牛一般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长，垂荡在身后。"
				+ "[npc.She]很快意识到自己的控制有限，只能令其左右摇摆。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldCowMorph(牛一般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条牛一般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长，垂荡在身后。"
				+ "[npc.She]很快意识到自己的控制有限，只能令其左右摇摆。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldCowMorph(牛一般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]牛尾巴，[npc.she]能够四处摆荡，但此外不能进行更精密的控制。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]牛尾巴，[npc.she]能够四处摆荡，但此外不能进行更精密的控制。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType DOG_MORPH = new AbstractTailType(
			BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			PenetrationGirth.THREE_AVERAGE,
			0.4f,
			"犬",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("犬一般"),
			Util.newArrayListOfValues("犬一般"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("毛茸茸"),
			Util.newArrayListOfValues("毛茸茸"),
			"#IF(npc.getTailCount()==1)"
				+ "一条毛茸茸的犬一般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己很难控制其运动，只要一激动就会不受控制地摇摆起来。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldDogMorph(犬一般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条毛茸茸的犬一般的短尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己很难控制其运动，只要一激动就会不受控制地摇摆起来。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldDogMorph(犬一般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]狗尾巴，一激动就会不受控制地摇摆起来。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]狗尾巴，一激动就会不受控制地摇摆起来。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType DOG_MORPH_STUBBY = new AbstractTailType(
			BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			PenetrationGirth.THREE_AVERAGE,
			0.1f,
			"犬(粗短)",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("粗短", "犬一般"),
			Util.newArrayListOfValues("粗短", "犬一般"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("粗短"),
			Util.newArrayListOfValues("粗短"),
			"#IF(npc.getTailCount()==1)"
				+ "一条毛茸茸的犬一般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己很难控制其运动，只要一激动就会不受控制地摇摆起来。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldDogMorph(犬一般的粗短尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条毛茸茸的犬一般的短尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己很难控制其运动，只要一激动就会不受控制地摇摆起来。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldDogMorph(犬一般的粗短尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF粗短[npc.tailColour(true)]狗尾巴，一激动就会不受控制地摇摆起来。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF粗短[npc.tailColour(true)]狗尾巴，一激动就会不受控制地摇摆起来。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType FOX_MORPH = new AbstractTailType(
			BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			PenetrationGirth.FOUR_GIRTHY,
			0.6f,
			"狐狸",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("狐狸般", "松软", "浓密"),
			Util.newArrayListOfValues("狐狸般", "松软", "浓密"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("毛茸茸"),
			Util.newArrayListOfValues("毛茸茸"),
			"#IF(npc.getTailCount()==1)"
				+ "一条浓密的狐狸般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够适当地控制其运动，将这条松软的东西蜷缩在下半身。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldFoxMorph(狐狸般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.tailCount]条浓密的狐狸般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够适当地控制其运动，将这条松软的东西蜷缩在下半身。"
				+ "<br/>"
				+ "[npc.Name]拥有[npc.tailCount]条[style.boldFoxMorph(狐狸般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]狐狸尾巴，能够随意甩动。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]狐狸尾巴，能够随意甩动。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType FOX_MORPH_MAGIC = new AbstractTailType(
			BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			PenetrationGirth.FOUR_GIRTHY,
			1f,
			"狐狸(奥术)",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("奥术", "狐狸般", "松软"),
			Util.newArrayListOfValues("奥术", "狐狸般", "松软"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("毛茸茸"),
			Util.newArrayListOfValues("毛茸茸"),
			"#IF(npc.getTailCount()==1)"
				+ "一条浓密的狐狸般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够适当地控制其运动，将这条松软的东西蜷缩在下半身。"
				+ "[npc.SheIsFull]清楚地感受到尾巴给予了[npc.herHim][style.italicsArcane(奥术之力)]，而得到的尾巴越多，力量也就越强大！"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldFoxMorph(狐狸般的尾巴)]，充满着[style.boldArcane(奥术)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.tailCount]条浓密的狐狸般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够适当地控制其运动，将这条松软的东西蜷缩在下半身。"
				+ "#IF(npc.getTailCount()==9)"
					+ "[npc.SheIsFull]清楚地感受到尾巴给予了[npc.herHim][style.italicsExcellent(无穷的)][style.italicsArcane(奥术之力)]！"
				+ "#ELSE"
					+ "[npc.SheIsFull]清楚地感受到尾巴给予了[npc.herHim][style.italicsArcane(奥术之力)]，而得到的尾巴越多，力量也就越强大！"
				+ "#ENDIF"
				+ "<br/>"
				+ "[npc.Name]拥有[npc.tailCount]条[style.boldFoxMorph(狐狸般的尾巴)]，充满着[style.boldArcane(奥术)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)][style.boldArcane(奥术)]狐狸尾巴，赐予了[npc.herHim][style.italicsArcane(奥术之力)]！"
				+ "#ELSE"
					+ "#IF(npc.getTailCount()==9)"
						+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]蝙[style.boldArcane(奥术)]狐狸尾巴，"
							+ "赐予了[npc.herHim][style.italicsExcellent(无穷的)][style.italicsArcane(奥术之力)]！"
					+ "#ELSE"
						+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)][style.boldArcane(奥术)]狐狸尾巴，赐予了[npc.herHim][style.italicsArcane(奥术之力)]！"
					+ "#ENDIF"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType HARPY = new AbstractTailType(
			BodyCoveringType.FEATHERS,
			Race.HARPY,
			PenetrationGirth.FOUR_GIRTHY,
			0.5f,
			"哈比(尾羽)",
			"簇",
			"簇",
			"尾羽",
			"尾羽",
			Util.newArrayListOfValues("华丽", "鸟一般"),
			Util.newArrayListOfValues("华丽", "鸟一般"),
			"末端",
			"末端",
			Util.newArrayListOfValues("羽毛"),
			Util.newArrayListOfValues("羽毛"),
			"#IF(npc.getTailCount()==1)"
				+ "一簇漂亮的尾羽，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]发现自己能快速地抬放新生的鸟一般的尾巴，有利于保持平衡。"
				+ "<br/>"
				+ "[npc.Name]拥有一簇[style.boldHarpy(鸟一般的哈比尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]簇漂亮的尾羽，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]发现自己能快速地抬放新生的鸟一般的尾巴，有利于保持平衡。"
				+ "<br/>"
				+ "[npc.Name]拥有[npc.tailCount]簇[style.boldHarpy(鸟一般的哈比尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一簇#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF漂亮[npc.tailColour(true)]尾羽，"
						+ "[npc.she]能够迅速地将其上下移动，有利于保持平衡，并在战斗中协助行动。"
				+ "#ELSE"
					+ "[npc.tailCount]簇#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF漂亮[npc.tailColour(true)]尾羽，"
						+ "[npc.she]能够迅速地将其上下移动，有利于保持平衡，并在战斗中协助行动。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_FEATHER,
					BodyPartTag.TAIL_TAPERING_NONE,
					BodyPartTag.TAIL_NEVER_SUITABLE_FOR_PENETRATION), false) {
	};
	
	public static final AbstractTailType HORSE_MORPH = new AbstractTailType(
			BodyCoveringType.HAIR_HORSE_HAIR,
			Race.HORSE_MORPH,
			PenetrationGirth.THREE_AVERAGE,
			0.3f,
			"马",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("马一般"),
			Util.newArrayListOfValues("马一般"),
			"末端",
			"末端",
			Util.newArrayListOfValues("蓬松"),
			Util.newArrayListOfValues("蓬松"),
			"#IF(npc.getTailCount()==1)"
				+ "一条马一般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长，垂荡在身后。"
				+ "[npc.She]很快意识到自己的控制有限，只能令其左右摇摆。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldHorseMorph(马一般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条马一般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长，垂荡在身后。"
				+ "[npc.She]很快意识到自己的控制有限，只能令其左右摇摆。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldHorseMorph(马一般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]马尾巴，[npc.she]能够四处摆荡，但此外不能进行更精密的控制。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]马尾巴，[npc.she]能够四处摆荡，但此外不能进行更精密的控制。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_HAIR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType HORSE_MORPH_ZEBRA = new AbstractTailType(
			BodyCoveringType.HAIR_HORSE_HAIR,
			Race.HORSE_MORPH,
			PenetrationGirth.TWO_NARROW,
			0.3f,
			"斑马",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("斑马般"),
			Util.newArrayListOfValues("斑马般"),
			"末端",
			"末端",
			Util.newArrayListOfValues("绒毛尖端"),
			Util.newArrayListOfValues("绒毛尖端"),
			"#IF(npc.getTailCount()==1)"
				+ "一条斑马般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长，垂荡在身后。"
				+ "[npc.She]很快意识到自己的控制有限，只能令其左右摇摆。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldHorseMorph(斑马般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条斑马般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长，垂荡在身后。"
				+ "[npc.She]很快意识到自己的控制有限，只能令其左右摇摆。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldHorseMorph(斑马般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]斑马尾巴，[npc.she]能够四处摆荡，但此外不能进行更精密的控制。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]斑马尾巴，[npc.she]能够四处摆荡，但此外不能进行更精密的控制。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_HAIR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType RAT_MORPH = new AbstractTailType(
			BodyCoveringType.RAT_SKIN,
			Race.RAT_MORPH,
			PenetrationGirth.THREE_AVERAGE,
			0.75f,
			"老鼠",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("老鼠般"),
			Util.newArrayListOfValues("老鼠般"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"#IF(npc.getTailCount()==1)"
				+ "一条粗壮的老鼠般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够适当地控制其运动，随意转向各处。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldRatMorph(老鼠般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条粗壮的老鼠般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够适当地控制其运动，随意转向各处。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldRatMorph(老鼠般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]老鼠尾巴。[npc.sheHasFull]能自如地掌控其运动，帮助[npc.herHim]抓握物体。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]老鼠尾巴。[npc.sheHasFull]能自如地掌控其运动，帮助[npc.herHim]抓握物体。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_SKIN,
					BodyPartTag.TAIL_TAPERING_LINEAR), false) {
	};
	
	public static final AbstractTailType RABBIT_MORPH = new AbstractTailType(
			BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			PenetrationGirth.FIVE_THICK,
			0.075f,
			"兔子",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("兔子般", "松软"),
			Util.newArrayListOfValues("兔子般", "松软"),
			"毛团",
			"毛团",
			Util.newArrayListOfValues("松软"),
			Util.newArrayListOfValues("松软"),
			"#IF(npc.getTailCount()==1)"
				+ "一条毛茸茸的兔子般的圆尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己根本无法控制分毫，但说白了也不过只是一团毛球而已。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldRabbitMorph(兔子般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条毛茸茸的兔子般的圆尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己根本无法控制分毫，但说白了也不过只是几团毛球而已。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldRabbitMorph(兔子般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]兔子尾巴，只不过是一大团毛球。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]兔子尾巴，只不过是几大团毛球。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE,
					BodyPartTag.TAIL_NEVER_SUITABLE_FOR_PENETRATION), false) {
	};
	
	public static final AbstractTailType REINDEER_MORPH = new AbstractTailType(
			BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			PenetrationGirth.FOUR_GIRTHY,
			0.05f,
			"驯鹿",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("驯鹿般"),
			Util.newArrayListOfValues("驯鹿般"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("毛茸茸"),
			Util.newArrayListOfValues("毛茸茸"),
			"#IF(npc.getTailCount()==1)"
				+ "一条驯鹿般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己的控制有限，只能令其上下颤动。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldReindeerMorph(驯鹿般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条驯鹿般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己的控制有限，只能令其上下颤动。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldReindeerMorph(驯鹿般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]驯鹿尾巴，只能上下颤动。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]驯鹿尾巴，只能上下颤动。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType SQUIRREL_MORPH = new AbstractTailType(
			BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			PenetrationGirth.FIVE_THICK,
			1f,
			"松鼠",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("松鼠般", "松软", "浓密"),
			Util.newArrayListOfValues("松鼠般", "松软", "浓密"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("毛茸茸"),
			Util.newArrayListOfValues("毛茸茸"),
			"#IF(npc.getTailCount()==1)"
				+ "一条毛茸茸的松鼠般的尾巴，从尾椎处延伸而出，迅速生长至大概有惊人的[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够适当地控制其运动，快速移动时可以用来保持平衡。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldSquirrelMorph(松鼠般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条毛茸茸的松鼠般的尾巴，从尾椎处延伸而出，迅速生长至大概有惊人的[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己能够适当地控制其运动，快速移动时可以用来保持平衡。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldSquirrelMorph(松鼠般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]搜索那个书尾巴，能够有效地帮助[npc.herHim]保持平衡。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]搜索那个书尾巴，能够有效地帮助[npc.herHim]保持平衡。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType WOLF_MORPH = new AbstractTailType(
			BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			PenetrationGirth.FOUR_GIRTHY,
			0.4f,
			"狼",
			"",
			"",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("狼一般", "松软"),
			Util.newArrayListOfValues("狼一般", "松软"),
			"尖端",
			"尖端",
			Util.newArrayListOfValues("毛茸茸"),
			Util.newArrayListOfValues("毛茸茸"),
			"#IF(npc.getTailCount()==1)"
				+ "一条毛茸茸的狼一般的尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己对其控制有限，需要大费周章才能抗拒情感的表露。"
				+ "<br/>"
				+ "[npc.Name]拥有一条[style.boldWolfMorph(狼一般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TailCount]条毛茸茸的狼一般的短尾巴，从尾椎处延伸而出，迅速生长至大概有[npc.tailLength]长。"
				+ "[npc.She]很快意识到自己对其控制有限，需要大费周章才能抗拒情感的表露。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tailCount]条[style.boldWolfMorph(狼一般的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从尾椎处延伸而出"
				+ "#IF(npc.getTailCount()==1)"
					+ "一条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]狼尾巴，一激动就会摇摆起来。"
				+ "#ELSE"
					+ "[npc.tailCount]条#IF(npc.isTailFeral())[style.colourFeral(兽态的)]#ENDIF[npc.tailColour(true)]狼尾巴，一激动就会摇摆起来。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	
	private static List<AbstractTailType> allTailTypes;
	private static Map<AbstractTailType, String> tailToIdMap = new HashMap<>();
	private static Map<String, AbstractTailType> idToTailMap = new HashMap<>();
	
	static {
		allTailTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("tail")) {
					try {
						AbstractTailType type = new AbstractTailType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTailTypes.add(type);
						tailToIdMap.put(type, id);
						idToTailMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// External res types:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("tail")) {
					try {
						AbstractTailType type = new AbstractTailType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTailTypes.add(type);
						tailToIdMap.put(type, id);
						idToTailMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded tail types:
		
		Field[] fields = TailType.class.getFields();
		
		for(Field f : fields){
			if (AbstractTailType.class.isAssignableFrom(f.getType())) {
				
				AbstractTailType ct;
				try {
					ct = ((AbstractTailType) f.get(null));

					tailToIdMap.put(ct, f.getName());
					idToTailMap.put(f.getName(), ct);
					
					allTailTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		AbstractTailType ct = LexiTailType.DEMON_TUSSY;

		tailToIdMap.put(ct, "DEMON_TUSSY");
		idToTailMap.put("DEMON_TUSSY", ct);
		allTailTypes.add(ct);

		ct = LexiTailType.MANTICORE_TUSSY;

		tailToIdMap.put(ct, "MANTICORE_TUSSY");
		idToTailMap.put("MANTICORE_TUSSY", ct);
		allTailTypes.add(ct);

		Collections.sort(allTailTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractTailType getTailTypeFromId(String id) {
		if(id.equals("IMP")) {
			return TailType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return TailType.WOLF_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToTailMap.keySet());
		return idToTailMap.get(id);
	}
	
	public static String getIdFromTailType(AbstractTailType tailType) {
		return tailToIdMap.get(tailType);
	}
	
	public static List<AbstractTailType> getAllTailTypes() {
		return allTailTypes;
	}
	
	private static Map<AbstractRace, List<AbstractTailType>> typesMap = new HashMap<>();
	
	public static List<AbstractTailType> getTailTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractTailType> types = new ArrayList<>();
		for(AbstractTailType type : TailType.getAllTailTypes()) {
			if(type.getRace()==r && type!=TailType.FOX_MORPH_MAGIC) {
				types.add(type);
			}
		}
		if(types.isEmpty()) {
			types.add(TailType.NONE);
		}
		typesMap.put(r, types);
		return types;
	}
	
	public static List<AbstractTailType> getTailTypesSuitableForTransformation(List<AbstractTailType> options) {
		if (!options.contains(TailType.NONE)) {
			return options;
		}
		
		List<AbstractTailType> duplicatedOptions = new ArrayList<>(options);
		duplicatedOptions.remove(TailType.NONE);
		return duplicatedOptions;
	}
}