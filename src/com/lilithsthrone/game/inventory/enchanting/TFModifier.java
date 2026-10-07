package com.lilithsthrone.game.inventory.enchanting;

import com.lilithsthrone.LolificationProject.ModCommon.OtherRegistrars;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.7
 * @version 0.3.5.5
 * @author Innoxia
 */
public enum TFModifier {
	
	NONE("空的",
		"无修饰",
		"无效",
		"modifier_circle",
		PresetColour.TEXT_GREY,
		Rarity.COMMON),
	
	// Pos/Neg
	
	ARCANE_BOOST("奥术增幅",
			"应用一项更强大的原初精华效果。",
			"增幅",
			"modifier_circle_arcane",
			PresetColour.GENERIC_ARCANE,
			Rarity.COMMON),

	REMOVAL("移除",
			"应用一项移除主要身体部位的效果。",
			"消失",
			"modifier_circle_removal",
			PresetColour.GENERIC_TERRIBLE,
			Rarity.UNCOMMON),
	
	// Misc:
	
	ORIENTATION_GYNEPHILIC("女性恋",
			"应用与改变性取向为女性恋相关的效果。",
			"女性恋",
			"modifier_circle_orientation_gynephilic",
			PresetColour.FEMININE_PLUS,
			Rarity.LEGENDARY),
	
	ORIENTATION_AMBIPHILIC("双性恋",
			"应用与改变性取向为双性恋相关的效果。",
			"双性恋",
			"modifier_circle_orientation_ambiphilic",
			PresetColour.ANDROGYNOUS,
			Rarity.LEGENDARY),
	
	ORIENTATION_ANDROPHILIC("男性恋",
			"应用与改变性取向为男性恋相关的效果。",
			"男性恋",
			"modifier_circle_orientation_androphilic",
			PresetColour.MASCULINE_PLUS,
			Rarity.LEGENDARY),
	
	PERSONALITY_TRAIT_SPEECH_LISP("口齿不清",
			"应用与他人说话口齿不清相关的效果。",
			"口齿不清",
			"modifier_circle_speech",
			PresetColour.BASE_PURPLE_LIGHT,
			Rarity.LEGENDARY),

	PERSONALITY_TRAIT_SPEECH_STUTTER("口吃",
			"应用与他人说话结结巴巴相关的效果。",
			"口吃",
			"modifier_circle_speech",
			PresetColour.BASE_PINK_SALMON,
			Rarity.LEGENDARY),

	PERSONALITY_TRAIT_SPEECH_SLOVENLY("发音模糊",
			"应用与他人说话发音模糊相关的效果。",
			"发音模糊",
			"modifier_circle_speech",
			PresetColour.BASE_BROWN,
			Rarity.LEGENDARY),
	
	// Attributes:

	HEALTH_MAXIMUM(AttributeCategory.STRENGTH,
			Attribute.HEALTH_MAXIMUM,
			"应用与主属性“最大生命值”相关的效果。",
			"modifier_circle_strength",//"modifier_circle_health",
			Rarity.EPIC),

	MANA_MAXIMUM(AttributeCategory.INTELLIGENCE,
			Attribute.MANA_MAXIMUM,
			"应用与主属性“最大灵气值”相关的效果。",
			"modifier_circle_mana",
			Rarity.EPIC),
	
	STRENGTH(AttributeCategory.STRENGTH,
			Attribute.MAJOR_PHYSIQUE,
			"应用与主属性“体格”相关的效果。",
			"modifier_circle_strength",
			Rarity.LEGENDARY),
	
	INTELLIGENCE(AttributeCategory.INTELLIGENCE,
			Attribute.MAJOR_ARCANE,
			"应用与主属性“奥术”相关的效果。",
			"modifier_circle_intelligence",
			Rarity.LEGENDARY),
	
	CORRUPTION(AttributeCategory.CORRUPTION,
			Attribute.MAJOR_CORRUPTION,
			"应用与主属性“堕落”相关的效果。",
			"modifier_circle_corruption",
			Rarity.LEGENDARY),

	/** This TFModifier is a special case, as it is not added to the available clothing TF lists.
	 * It is simply defined so that modded clothing can add this as a secondary TFModifier (to the primary TFModifier 'CLOTHING_MAJOR_ATTRIBUTE') to increase enchantment capacity of the wearer. */
	ENCHANTMENT_LIMIT(AttributeCategory.CORRUPTION,
			Attribute.ENCHANTMENT_LIMIT,
			"应用与次要属性“附魔不稳定”相关的效果。",
			"modifier_circle_corruption",
			Rarity.LEGENDARY),
	
	FERTILITY(AttributeCategory.CORRUPTION,
			Attribute.FERTILITY,
			"应用与次要属性“生育力”相关的效果。",
			"modifier_circle_fertility",
			Rarity.RARE),

	VIRILITY(AttributeCategory.CORRUPTION,
			Attribute.VIRILITY,
			"应用与次要属性“生殖力”相关的效果。",
			"modifier_circle_virility",
			Rarity.RARE),
	
	SPELL_COST_MODIFIER(AttributeCategory.INTELLIGENCE,
			Attribute.SPELL_COST_MODIFIER,
			"应用与次要属性“"+Attribute.SPELL_COST_MODIFIER.getName()+"”相关的效果。",
			"modifier_circle_spell_efficiency",
			Rarity.RARE),
	
	
	CRITICAL_DAMAGE(AttributeCategory.STRENGTH,
			Attribute.CRITICAL_DAMAGE,
			"应用与次要属性“暴击伤害”相关的效果。",
			"modifier_circle_critical_damage",
			Rarity.RARE),
	
	
	DAMAGE_LUST(AttributeCategory.CORRUPTION,
			Attribute.DAMAGE_LUST,
			"应用与次要属性“魅惑伤害”相关的效果。",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_SPELLS(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_SPELLS,
			"应用与次要属性“法术伤害”相关的效果。",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_UNARMED(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_UNARMED,
			"应用与次要属性“徒手伤害”相关的效果。",
			"modifier_circle_damage_unarmed",
			Rarity.RARE),

	DAMAGE_MELEE_WEAPON(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_MELEE_WEAPON,
			"应用与次要属性“近战武器伤害”相关的效果。",
			"modifier_circle_damage_melee",
			Rarity.RARE),

	DAMAGE_RANGED_WEAPON(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_RANGED_WEAPON,
			"应用与次要属性“远程武器伤害”相关的效果。",
			"modifier_circle_damage_ranged",
			Rarity.RARE),
	
	DAMAGE_PHYSICAL(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_PHYSICAL,
			"应用与次要属性“物理伤害”相关的效果。",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_FIRE(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_FIRE,
			"应用与次要属性“火焰伤害”相关的效果。",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_ICE(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_ICE,
			"应用与次要属性“寒冷伤害”相关的效果。",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_POISON(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_POISON,
			"应用与次要属性“毒素伤害”相关的效果。",
			"modifier_circle_damage",
			Rarity.RARE),
	
	/**
	 * Utility value for initialising a weapon to have attribute bonuses related to its damage type.
	 */
	DAMAGE_WEAPON(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_PHYSICAL,
			"应用一项与武器伤害类型相关的伤害效果。",
			"modifier_circle_damage",
			Rarity.RARE),
	
	
	RESISTANCE_LUST(AttributeCategory.CORRUPTION,
			Attribute.RESISTANCE_LUST,
			"应用与次要属性“魅惑护盾”相关的效果。",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_PHYSICAL(AttributeCategory.STRENGTH,
			Attribute.RESISTANCE_PHYSICAL,
			"应用与次要属性“物理护盾”相关的效果。",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_FIRE(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_FIRE,
			"应用与次要属性“火焰护盾”相关的效果。",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_ICE(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_ICE,
			"应用与次要属性“寒冷护盾”相关的效果。",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_POISON(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_POISON,
			"应用与次要属性“毒素护盾”相关的效果。",
			"modifier_circle_resistance",
			Rarity.RARE),

	/**
	 * Utility value for initialising a weapon to have attribute bonuses related to its damage type.
	 */
	RESISTANCE_WEAPON(AttributeCategory.STRENGTH,
			Attribute.RESISTANCE_PHYSICAL,
			"应用一项与武器伤害类型相关的护盾效果。",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	
	// Clothing parts:
	
	CLOTHING_MAJOR_ATTRIBUTE("核心属性",
			"将一条修饰词应用到一项核心属性上。",
			"属性",
			"modifier_circle_attribute_major",
			PresetColour.GENERIC_ATTRIBUTE,
			Rarity.LEGENDARY),
	
	CLOTHING_ATTRIBUTE("属性",
			"将一条修饰词应用至一项属性上。",
			"属性",
			"modifier_circle_attribute",
			PresetColour.GENERIC_ATTRIBUTE,
			Rarity.UNCOMMON),

	CLOTHING_SPECIAL("特殊效果",
			"应用一项特殊效果。",
			"特殊",
			"modifier_circle_special",
			PresetColour.BASE_TEAL,
			Rarity.LEGENDARY),

	CLOTHING_SEXUAL("性相关效果",
			"施加一种性相关的效果。",
			"性",
			"modifier_circle_sexual",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.EPIC),
	
	CLOTHING_ENSLAVEMENT("奴役",
			"使这件衣物奴役其穿戴者。",
			"奴役",
			"modifier_circle_enslavement",
			PresetColour.BASE_PURPLE,
			Rarity.LEGENDARY),
	
	CLOTHING_SEALING("封印",
			"使这件衣物将它自己封印在穿戴者身上。",
			"封印",
			"modifier_circle_sealing",
			PresetColour.SEALED,
			Rarity.LEGENDARY),
	
	//CLOTHING_ANTI_SELF_TRANSFORMATION
	CLOTHING_SERVITUDE("仆役",
			"使这件衣物的穿戴者无法自我转化或解封衣物。",
			"仆役",
			"modifier_circle_servitude",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.LEGENDARY),

	CLOTHING_CONDOM("不破壁垒",
			"为避孕套提供潜在的强度。",
			"不破壁垒",
			"modifier_circle_resistance",
			PresetColour.BASE_GREEN,
			Rarity.COMMON),

	CLOTHING_CREAMPIE_RETENTION("维持内射",
			"防止体内的精液随时间流失。",
			"维持内射",
			"modifier_circle_creampie_retention",
			PresetColour.CUM,
			Rarity.EPIC),

	CLOTHING_VIBRATION("震动",
			"使这件衣物在穿戴时发出震动。",
			"震动",
			"modifier_circle_vibration",
			PresetColour.BASE_PINK,
			Rarity.EPIC),

	CLOTHING_ORGASM_PREVENTION("高潮阻碍",
			"使穿戴者无法达到高潮。",
			"高潮阻碍",
			"modifier_circle_orgasm_prevention",
			PresetColour.BASE_CRIMSON,
			Rarity.EPIC),
	
	// Racial parts:

	TF_MATERIAL_FLESH("血肉",
			"使某人的身体材质转化为血肉。",
			"血肉",
			"modifier_circle_tf_material_flesh",
			PresetColour.BASE_CRIMSON,
			Rarity.LEGENDARY),
	
	TF_ANTENNA("触须",
			"向你的触须应用一种转化效果。",
			"触须",
			"modifier_circle_tf_antenna",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_ARMS("手臂",
			"向你的手臂应用一种转化效果。",
			"手臂",
			"modifier_circle_tf_arm",
			PresetColour.TRANSFORMATION_LESSER,
			Rarity.RARE),
	
	TF_ASS("屁股",
			"向你的屁股应用一种转化效果。",
			"屁股",
			"modifier_circle_tf_ass",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_BREASTS("胸部",
			"向你的胸部应用一种转化效果。",
			"胸部",
			"modifier_circle_tf_breast",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_BREASTS_CROTCH("胯乳",
			"向你的胯乳/腹乳应用一种转化效果。",
			"胯乳",
			"modifier_circle_tf_breast_crotch",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_CORE("核心",
			"向你的身体应用一种转化效果。",
			"核心",
			"modifier_circle_tf_core",
			PresetColour.TRANSFORMATION_GREATER,
			Rarity.RARE),
	
	TF_EARS("耳朵",
			"向你的耳朵应用一种转化效果。",
			"耳朵",
			"modifier_circle_tf_ear",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_EYES("眼睛",
			"向你的眼部应用一种转化效果。",
			"眼睛",
			"modifier_circle_tf_eye",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_FACE("面部",
			"向你的脸部应用一种转化效果。",
			"面部",
			"modifier_circle_tf_face",
			PresetColour.TRANSFORMATION_GREATER,
			Rarity.EPIC),
	
	TF_HAIR("头发",
			"向你的头发应用一种转化效果。",
			"头发",
			"modifier_circle_tf_hair",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_HORNS("角",
			"向你的角应用一种转化效果。",
			"角",
			"modifier_circle_tf_horn",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_LEGS("腿",
			"向你的腿部应用一种转化效果。",
			"腿",
			"modifier_circle_tf_leg",
			PresetColour.TRANSFORMATION_LESSER,
			Rarity.RARE),
	
	TF_PENIS("阴茎",
			"向你的阴茎应用一种转化效果。",
			"肉棒",
			"modifier_circle_tf_penis",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),

	TF_PENIS_URETHRA("阴茎尿道",
			"向你的阴茎尿道应用一种转化效果。",
			"阴茎尿道",
			"modifier_circle_tf_penis",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.EPIC),
	
	TF_SKIN("躯干",
			"向你的躯干应用一种转化效果。",
			"躯干",
			"modifier_circle_tf_skin",
			PresetColour.TRANSFORMATION_GREATER,
			Rarity.EPIC),
	
	TF_SPINNERET("丝囊",
			"向你的丝囊应用一种转化效果。",
			"丝囊",
			"modifier_circle_tf_spinneret",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	TF_TUSSY("tussy",
			"Applies a transformative effect to your tussy.",
			"tussys",
			"modifier_circle_tf_spinneret",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_TAIL("尾巴",
			"向你的尾部应用一种转化效果。",
			"尾巴",
			"modifier_circle_tf_tail",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),

	TF_TENTACLE("触手",
			"向你的触手应用一种转化效果。",
			"触手",
			"modifier_circle_tf_tentacle",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_VAGINA("阴道",
			"向你的阴道应用一种转化效果。",
			"小穴",
			"modifier_circle_tf_vagina",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),

	TF_VAGINA_URETHRA("阴道尿道",
			"向你的阴道尿道应用一种转化效果。",
			"阴道尿道",
			"modifier_circle_tf_vagina",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.EPIC),
	
	TF_WINGS("翅膀",
			"向你的翅膀应用一种转化效果。",
			"翅膀",
			"modifier_circle_tf_wing",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_MILK("乳汁",
			"向你的乳汁应用一种转化效果。",
			"乳汁",
			"modifier_circle_tf_milk",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_MILK_CROTCH("腹乳乳汁",
			"向你的腹乳乳汁应用一种转化效果。",
			"腹乳乳汁",
			"modifier_circle_tf_milk_crotch",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_CUM("精液",
			"向你的精液应用一种转化效果。",
			"精液",
			"modifier_circle_tf_cum",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_GIRLCUM("爱液",
			"向你的爱液应用一种转化效果。",
			"爱液",
			"modifier_circle_tf_girlcum",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	
	// Body part modifiers:
	
	TF_TYPE_1("转化 I",
			"将身体的一部分转化为不同种族的对应部分。",
			"可转化",
			"modifier_circle_tf_1",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_2("转化 II",
			"将身体的一部分转化为不同种族的对应部分。",
			"可转化",
			"modifier_circle_tf_2",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_3("转化 III",
			"将身体的一部分转化为不同种族的对应部分。",
			"可转化",
			"modifier_circle_tf_3",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_4("转化 IV",
			"将身体的一部分转化为不同种族的对应部分。",
			"可转化",
			"modifier_circle_tf_4",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_5("转化 V",
			"将身体的一部分转化为不同种族的对应部分。",
			"可转化",
			"modifier_circle_tf_5",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),

	TF_TYPE_6("转化 VI",
			"将身体的一部分转化为不同种族的对应部分。",
			"可转化",
			"modifier_circle_tf_6",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_7("转化 VII",
			"将身体的一部分转化为不同种族的对应部分。",
			"可转化",
			"modifier_circle_tf_7",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_8("转化 VIII",
			"将身体的一部分转化为不同种族的对应部分。",
			"可转化",
			"modifier_circle_tf_8",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_9("转化 IX",
			"将身体的一部分转化为不同种族的对应部分。",
			"可转化",
			"modifier_circle_tf_9",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_10("转化 X",
			"将身体的一部分转化为不同种族的对应部分。",
			"可转化",
			"modifier_circle_tf_10",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_MOD_FEMININITY("女性化程度",
			"应用一项改变使用者女性化程度的效果。",
			"女性",
			"modifier_circle_femininity",
			PresetColour.ANDROGYNOUS,
			Rarity.COMMON),
	
	TF_MOD_COUNT("数量",
			"应用一项关于添加或移除身体额外部位的效果。",
			"计数",
			"modifier_circle_count",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_COUNT_SECONDARY("次要数量",
			"应用一项关于添加或移除身体额外部位的效果。",
			"计数",
			"modifier_circle_count_secondary",
			PresetColour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_BODY_HAIR("毛发量",
			"应用一项关于添加或移除身体毛发的效果。",
			"多毛",
			"modifier_circle_bodyHair",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_FOOT_STRUCTURE_PLANTIGRADE("跖行足",
			"应用一项给予使用者跖行足部结构相关的效果。",
			"跖行足",
			"modifier_circle_tf_footStructure_plantigrade",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.LEGENDARY),
	
	TF_MOD_FOOT_STRUCTURE_DIGITIGRADE("趾行足",
			"应用一项给予使用者趾行足部结构相关的效果。",
			"趾行足",
			"modifier_circle_tf_footStructure_digitigrade",
			PresetColour.BASE_BROWN_DARK,
			Rarity.LEGENDARY),
	
	TF_MOD_FOOT_STRUCTURE_UNGULIGRADE("蹄行足",
			"应用一项给予使用者蹄行足部结构相关的效果。",
			"蹄行足",
			"modifier_circle_tf_footStructure_unguligrade",
			PresetColour.BASE_TAN,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_BIPEDAL("二足身躯",
			"应用一项给予使用者二足行走的身体相关的效果。",
			"二足身躯",
			"modifier_circle_tf_legConfig_bipedal",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_TAUR("四足身躯",
			"应用一项给予使用者四足行走的身体相关的效果。",
			"四足身躯",
			"modifier_circle_tf_legConfig_taur",
			PresetColour.BASE_TAN,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_TAIL_LONG("长尾身躯",
			"应用一项给予使用者具有长尾的身体相关的效果。",
			"长尾身躯",
			"modifier_circle_tf_legConfig_tail_long",
			PresetColour.BASE_GREEN,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_TAIL("有尾身躯",
			"应用一项给予使用者带有尾巴的身体相关的效果。",
			"有尾身躯",
			"modifier_circle_tf_legConfig_tail",
			PresetColour.BASE_AQUA,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_ARACHNID("蛛形身躯",
			"应用一项给予使用者蛛形纲身体相关的效果。",
			"蛛形身躯",
			"modifier_circle_tf_legConfig_arachnid",
			PresetColour.BASE_BLACK,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_CEPHALOPOD("头足类身躯",
			"应用一项给予使用者带头足类身体相关的效果。",
			"头足类身躯",
			"modifier_circle_tf_legConfig_cephalopod",
			PresetColour.BASE_RED,
			Rarity.LEGENDARY),

	TF_MOD_LEG_CONFIG_AVIAN("鸟类身躯",
			"应用一项给予使用者鸟类身体相关的效果。",
			"鸟类身躯",
			"modifier_circle_tf_legConfig_avian",
			PresetColour.BASE_YELLOW,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_WINGED_BIPED("翼手双足身躯",
			"应用一项给予使用者翼手双足身躯相关的效果。",
			"翼手双足身躯",
			"modifier_circle_tf_legConfig_avian",
			PresetColour.BASE_YELLOW,
			Rarity.LEGENDARY),
	
	TF_MOD_INTERNAL("体内转化",
			"应用一项将某个身体部位隐藏到体内的效果。",
			"体内转化",
			"modifier_circle_internal",
			PresetColour.BASE_BLUE_STEEL,
			Rarity.COMMON),
	
	TF_MOD_SIZE("大小",
			"应用一项改变某个身体部位大小的效果。",
			"重塑大小",
			"modifier_circle_size",
			PresetColour.BASE_LILAC,
			Rarity.COMMON),
	
	TF_MOD_SIZE_SECONDARY("次要大小",
			"应用一项改变另一处身体部位大小的效果。",
			"重塑大小",
			"modifier_circle_size_secondary",
			PresetColour.BASE_LILAC_LIGHT,
			Rarity.COMMON),

	TF_MOD_SIZE_TERTIARY("三等大小",
			"应用一项改变第三处身体部位大小的效果。",
			"重塑大小",
			"modifier_circle_size_tertiary",
			PresetColour.BASE_ROSE,
			Rarity.COMMON),
	

	TF_MOD_REGENERATION("再生",
			"应用一项与液体再生速率相关的效果。",
			"重盈",
			"modifier_circle_regeneration",
			PresetColour.BASE_GREEN_LIGHT,
			Rarity.COMMON),
	
	// Orifices:
	
	TF_MOD_CAPACITY("容量",
			"应用一项给予使用者具有改变腔穴容量相关的效果。",
			"大容量的",
			"modifier_circle_capacity",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.COMMON),

	TF_MOD_CAPACITY_2("容量 II",
			"应用一项给予使用者具有改变腔穴容量相关的效果。",
			"大容量的",
			"modifier_circle_capacity",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_WETNESS("液体量",
			"应用一项改变腔穴湿润度或与身体部位分泌液体有关的效果。",
			"更加湿润",
			"modifier_circle_wetness",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_DEPTH("深度等级",
			"应用一项改变腔穴深度等级的效果。",
			"更加幽深",
			"modifier_circle_orifice_deep",
			PresetColour.BASE_GREY,
			Rarity.COMMON),
	
	TF_MOD_DEPTH_2("深度等级II",
			"应用一项改变腔穴深度等级的效果。",
			"更加幽深",
			"modifier_circle_orifice_deep",
			PresetColour.BASE_BLACK,
			Rarity.COMMON),
	
	TF_MOD_ELASTICITY("弹性等级",
			"应用一项改变腔穴弹性等级的效果。",
			"更具弹性",
			"modifier_circle_elasticity",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_ELASTICITY_2("弹性等级II",
			"应用一项改变腔穴弹性等级的效果。",
			"更具弹性",
			"modifier_circle_elasticity",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_PLASTICITY("可塑性等级",
			"应用一项改变腔穴可塑性等级的效果。",
			"更加可塑",
			"modifier_circle_plasticity",
			PresetColour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_PLASTICITY_2("可塑性等级II",
			"应用一项改变腔穴可塑性等级的效果。",
			"更加可塑",
			"modifier_circle_plasticity",
			PresetColour.BASE_BLUE,
			Rarity.COMMON),
	
	// modifiers:
	
	TF_MOD_ORIFICE_PUFFY("肉感",
			"应用一项让腔穴更具有肉感的效果。",
			"更具肉感",
			"modifier_circle_orifice_puffy",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_RIBBED("长有螺纹",
			"应用一项让腔穴内壁长出螺纹的效果。",
			"长有螺纹",
			"modifier_circle_orifice_ribbed",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_TENTACLED("长有触手",
			"应用一项让腔穴内壁长出触手的效果。",
			"长有触手",
			"modifier_circle_orifice_tentacled",
			PresetColour.BASE_CRIMSON,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_MUSCLED("长出肌肉",
			"应用一项让腔穴内壁长出肌肉的效果。",
			"长出肌肉",
			"modifier_circle_orifice_muscled",
			PresetColour.BASE_MAGENTA,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_PUFFY_2("丰满 II",
			"应用一项让腔穴更具有肉感的效果。",
			"更具肉感",
			"modifier_circle_orifice_puffy",
			PresetColour.BASE_BLUE,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_RIBBED_2("长有螺纹II",
			"应用一项让腔穴内壁长有螺纹的效果。",
			"长有螺纹",
			"modifier_circle_orifice_ribbed",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_TENTACLED_2("长出触手II",
			"应用一项让腔穴内壁长出触手的效果。",
			"长有触手",
			"modifier_circle_orifice_tentacled",
			PresetColour.BASE_RED,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_MUSCLED_2("长出肌肉II",
			"应用一项让腔穴内壁长出肌肉的效果。",
			"长出肌肉",
			"modifier_circle_orifice_muscled",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	// eye shapes:
	
	TF_MOD_EYE_IRIS_CIRCLE("圆形虹膜",
			"应用一种与使虹膜形状更像普通圆形相关的效果。",
			"圆形虹膜",
			"modifier_circle_eye_iris_normal",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_HORIZONTAL("横长条形虹膜",
			"应用一种与使虹膜呈横长条形相关的效果。",
			"横长条形虹膜",
			"modifier_circle_eye_iris_horizontal",
			PresetColour.BASE_LILAC_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_VERTICAL("竖长条形瞳孔",
			"应用一种与使虹膜呈竖长条形相关的效果。",
			"竖长条形虹膜",
			"modifier_circle_eye_iris_vertical",
			PresetColour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_HEART("心形虹膜",
			"应用一种与使虹膜形状像心形相关的效果。",
			"心形虹膜",
			"modifier_circle_eye_iris_heart",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_STAR("星形虹膜",
			"应用一种与使虹膜形状像星形相关的效果。",
			"星形虹膜",
			"modifier_circle_eye_iris_star",
			PresetColour.BASE_YELLOW,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_CIRCLE("圆形瞳孔",
			"应用一种与使虹膜形状更像普通圆形相关的效果。",
			"圆形瞳孔",
			"modifier_circle_eye_iris_normal",
			PresetColour.BASE_BLACK,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_HORIZONTAL("横长条形瞳孔",
			"应用一种与使瞳孔形状像横长条形相关的效果。",
			"横长条形瞳孔",
			"modifier_circle_eye_iris_horizontal",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_VERTICAL("竖长条形瞳孔",
			"应用一种与使瞳孔形状像竖长条形相关的效果。",
			"竖长条形瞳孔",
			"modifier_circle_eye_iris_vertical",
			PresetColour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_HEART("心形瞳孔",
			"应用一种与使瞳孔形状像心形相关的效果。",
			"心形瞳孔",
			"modifier_circle_eye_iris_heart",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_STAR("星形瞳孔",
			"应用一种与使瞳孔形状像星形相关的效果。",
			"星形瞳孔",
			"modifier_circle_eye_iris_star",
			PresetColour.BASE_BLUE,
			Rarity.COMMON),
	
	// breast shapes:
	
	TF_MOD_BREAST_SHAPE_UDDERS("腹乳",
			"应用一种关于将胯乳转化至腹乳的效果。",
			"腹乳",
			"modifier_circle_breastShape_udders",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_ROUND("圆形乳房",
			"应用一项让乳房看上去更圆润的效果。",
			"圆型乳房",
			"modifier_circle_breastShape_round",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_NARROW("窄型胸部",
			"应用一项让胸部看上去更窄的效果。",
			"窄型胸部",
			"modifier_circle_breastShape_narrow",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_WIDE("宽型胸部",
			"应用一项让胸部看上去更宽的效果。",
			"宽型胸部",
			"modifier_circle_breastShape_wide",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_POINTY("尖挺乳房",
			"应用一项让乳房看上去更尖挺的效果。",
			"尖挺乳房",
			"modifier_circle_breastShape_pointy",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_PERKY("上翘乳房",
			"应用一项让乳房看上去更上翘的效果。",
			"上翘乳房",
			"modifier_circle_breastShape_perky",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_SIDESET("八字胸部",
			"应用一项让两只乳房分向两侧的效果。",
			"八字胸部",
			"modifier_circle_breastShape_sideset",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	// nipple shapes:
	
	TF_MOD_NIPPLE_NORMAL("普通乳头",
			"应用一项让乳头看上去很普通的效果。",
			"普通乳头",
			"modifier_circle_nipple_normal",
			PresetColour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),

	TF_MOD_NIPPLE_INVERTED("内陷乳头",
			"应用一项让乳头看上去向内凹陷的效果。",
			"内陷乳头",
			"modifier_circle_nipple_normal",
			PresetColour.BASE_RED_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_NIPPLE_VAGINA("乳穴",
			"应用一种让乳头看起来就像小穴一样的效果。",
			"乳穴",
			"modifier_circle_nipple_vagina",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_NIPPLE_LIPS("乳唇",
			"应用一种让乳头看起来就像嘴唇一样饱满的效果。",
			"乳唇",
			"modifier_circle_nipple_lips",
			PresetColour.BASE_MAGENTA,
			Rarity.COMMON),
	
	// areolae shapes:
	
	TF_MOD_AREOLAE_CIRCLE("环形乳晕",
			"应用一项能够使乳晕变成常规圆形的效果。",
			"环形乳晕",
			"modifier_circle_areolae_normal",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	TF_MOD_AREOLAE_HEART("心形乳晕",
			"应用一项能够使乳晕变成心形的效果。",
			"心形乳晕",
			"modifier_circle_areolae_heart",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_AREOLAE_STAR("星形乳晕",
			"应用一项能够使乳晕变成星形的效果。",
			"星形乳晕",
			"modifier_circle_areolae_star",
			PresetColour.BASE_YELLOW,
			Rarity.COMMON),
	
	
	// tongue modifiers:
	
	TF_MOD_TONGUE_RIBBED("螺纹",
			"应用与为舌头添加螺纹相关的效果。",
			"长有螺纹",
			"modifier_circle_orifice_ribbed",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_TENTACLED("长有触手",
			"应用一项为舌头添加触手的效果。",
			"长有触手",
			"modifier_circle_orifice_tentacled",
			PresetColour.BASE_PINK_SALMON,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_BIFURCATED("分叉",
			"应用一项使舌头分叉的效果。",
			"分叉",
			"modifier_circle_tongue_bifurcated",
			PresetColour.BASE_CRIMSON,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_WIDE("宽大",
			"应用一项使舌头变得宽大的效果。",
			"宽大",
			"modifier_circle_tongue_wide",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_FLAT("平直",
			"应用一项使舌头变得平直的效果。",
			"平直",
			"modifier_circle_tongue_flat",
			PresetColour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_STRONG("有力",
			"应用一项使舌头变得强健有力的效果。",
			"强壮",
			"modifier_circle_orifice_muscled",
			PresetColour.BASE_MAGENTA,
			Rarity.COMMON),
	
	
	// penis & clit modifiers:
	
	TF_MOD_PENIS_SHEATHED("带鞘",
			"应用一项将某个身体部位被鞘包裹的效果。",
			"带鞘",
			"modifier_circle_penis_sheathed",
			PresetColour.BASE_ORANGE,
			Rarity.COMMON),
	
	TF_MOD_PENIS_RIBBED("螺纹",
			"应用一项将某个身体部位长有螺纹的效果。",
			"长有螺纹",
			"modifier_circle_penis_ribbed",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_PENIS_TENTACLED("长有触手",
			"应用一项将某个身体部位长有触手的效果。",
			"长有触手",
			"modifier_circle_orifice_tentacled",
			PresetColour.BASE_PINK_SALMON,
			Rarity.COMMON),
	
	TF_MOD_PENIS_KNOTTED("底端有结",
			"应用一项使某身体部位根部长结的效果。",
			"底端有节",
			"modifier_circle_penis_knotted",
			PresetColour.BASE_CRIMSON,
			Rarity.COMMON),
	
	TF_MOD_PENIS_TAPERED("尖头",
			"应用一项使某身体部位变为尖头的效果。",
			"尖头",
			"modifier_circle_penis_tapered",
			PresetColour.BASE_LILAC,
			Rarity.COMMON),
	
	TF_MOD_PENIS_FLARED("平头",
			"应用一项使某身体部位变为平头的效果。",
			"平头",
			"modifier_circle_penis_flared",
			PresetColour.BASE_BROWN,
			Rarity.COMMON),
	
	TF_MOD_PENIS_BLUNT("圆头",
			"应用一项使某身体部位变为圆头的效果。",
			"圆头",
			"modifier_circle_penis_blunt",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_PENIS_BARBED("倒刺",
			"应用一项使某身体部位带有倒刺的效果。",
			"带有倒刺",
			"modifier_circle_penis_barbed",
			PresetColour.BASE_RED,
			Rarity.COMMON),
	
	TF_MOD_PENIS_VEINY("青筋",
			"应用一项使某身体部位青筋暴起的效果。",
			"青筋暴起",
			"modifier_circle_penis_veiny",
			PresetColour.BASE_PINK_SALMON,
			Rarity.COMMON),
	
	TF_MOD_PENIS_PREHENSILE("灵活可控",
			"应用一项使某身体部位灵活可控的效果。",
			"灵活可控",
			"modifier_circle_penis_prehensile",
			PresetColour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_PENIS_OVIPOSITOR("产卵器",
			"应用一项使某身体部位具备产卵器功能的效果。",
			"可产卵",
			"modifier_circle_penis_ovipositor",
			PresetColour.BASE_WHITE,
			Rarity.COMMON),

	
	TF_MOD_CUM_EXPULSION("射精量",
			"应用一项改变高潮时一次性射精量的效果。",
			"精液",
			"modifier_circle_squirter",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	
	// Vagina:
	
	TF_MOD_VAGINA_SQUIRTER("潮吹体质",
			"应用一项让某人变为潮吹体质的效果。",
			"潮吹体质",
			"modifier_circle_squirter",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_VAGINA_EGG_LAYER("卵生",
			"应用与改变某人繁殖方式相关的效果。",
			"卵生",
			"modifier_circle_vagina_eggLayer",
			PresetColour.EGG,
			Rarity.COMMON),

	TF_MOD_HYMEN("处女膜",
			"应用一项关于修复或移除处女膜的效果。",
			"处女膜",
			"modifier_circle_tf_vagina",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.COMMON),
	
	
	// fluid modifiers:
	
	TF_MOD_FLUID_MUSKY("气味浓重",
			"应用一项改变液体的相关效果。",
			"淫味",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_BROWN,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.MUSKY.getColour();
		}
	},
	
	TF_MOD_FLUID_VISCOUS("粘稠",
			"应用一项改变液体的相关效果。",
			"粘稠",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_GREY,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.VISCOUS.getColour();
		}
	},
	
	TF_MOD_FLUID_STICKY("黏糊糊",
			"应用一项改变液体的相关效果。",
			"黏糊糊",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_YELLOW_LIGHT,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.STICKY.getColour();
		}
	},
	
	TF_MOD_FLUID_SLIMY("粘滑",
			"应用一项改变液体的相关效果。",
			"粘滑",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.SLIMY.getColour();
		}
	},
	
	TF_MOD_FLUID_BUBBLING("起泡",
			"应用一项改变液体的相关效果。",
			"起泡",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_AQUA,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.BUBBLING.getColour();
		}
	},
	
	TF_MOD_FLUID_ALCOHOLIC("高度酒精",
			"应用一项改变液体的相关效果。",
			"含酒精",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_ORANGE,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.ALCOHOLIC.getColour();
		}
	},
	
	TF_MOD_FLUID_ALCOHOLIC_WEAK("含酒精",
			"应用一项改变液体的相关效果。",
			"含酒精",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_ORANGE_LIGHT,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.ALCOHOLIC_WEAK.getColour();
		}
	},
	
	TF_MOD_FLUID_ADDICTIVE("成瘾性",
			"应用一项改变液体的相关效果。",
			"成瘾性",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_PINK_DEEP,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.ADDICTIVE.getColour();
		}
	},
	
	TF_MOD_FLUID_HALLUCINOGENIC("致幻性",
			"应用一项改变液体的相关效果。",
			"致幻性",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_MAGENTA,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.HALLUCINOGENIC.getColour();
		}
	},

	TF_MOD_FLUID_MINERAL_OIL("矿物油",
			"应用一项改变液体的相关效果。",
			"矿物油",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_BLACK,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.MINERAL_OIL.getColour();
		}
	},
	
	
	// Fluid flavours:
	
	TF_MOD_FLAVOUR_CUM(FluidFlavour.CUM, "flavours/cum"),
	
	TF_MOD_FLAVOUR_MILK(FluidFlavour.MILK, "flavours/cum"),
	
	TF_MOD_FLAVOUR_GIRLCUM(FluidFlavour.GIRL_CUM, "flavours/cum"),

	TF_MOD_FLAVOUR_FLAVOURLESS(FluidFlavour.FLAVOURLESS, "flavours/flavourless"),
	
	TF_MOD_FLAVOUR_BUBBLEGUM(FluidFlavour.BUBBLEGUM, "flavours/bubblegum"),
	
	TF_MOD_FLAVOUR_BEER(FluidFlavour.BEER, "flavours/beer"),
	
	TF_MOD_FLAVOUR_VANILLA(FluidFlavour.VANILLA, "flavours/vanilla"),
	
	TF_MOD_FLAVOUR_STRAWBERRY(FluidFlavour.STRAWBERRY, "flavours/strawberry"),
	
	TF_MOD_FLAVOUR_CHOCOLATE(FluidFlavour.CHOCOLATE, "flavours/chocolate"),
	
	TF_MOD_FLAVOUR_PINEAPPLE(FluidFlavour.PINEAPPLE, "flavours/pineapple"),
	
	TF_MOD_FLAVOUR_HONEY(FluidFlavour.HONEY, "flavours/honey"),
	
	TF_MOD_FLAVOUR_MINT(FluidFlavour.MINT, "flavours/mint"),

	TF_MOD_FLAVOUR_CHERRY(FluidFlavour.CHERRY, "flavours/cherry"),

	TF_MOD_FLAVOUR_COFFEE(FluidFlavour.COFFEE, "flavours/coffee"),

	TF_MOD_FLAVOUR_TEA(FluidFlavour.TEA, "flavours/tea"),

	TF_MOD_FLAVOUR_MAPLE(FluidFlavour.MAPLE, "flavours/maple"),

	TF_MOD_FLAVOUR_CINNAMON(FluidFlavour.CINNAMON, "flavours/cinnamon"),

	TF_MOD_FLAVOUR_LEMON(FluidFlavour.LEMON, "flavours/lemon"),

	TF_MOD_FLAVOUR_ORANGE(FluidFlavour.ORANGE, "flavours/orange"),
	
	TF_MOD_FLAVOUR_GRAPE(FluidFlavour.GRAPE, "flavours/grape"),
	
	TF_MOD_FLAVOUR_MELON(FluidFlavour.MELON, "flavours/melon"),
	
	TF_MOD_FLAVOUR_COCONUT(FluidFlavour.COCONUT, "flavours/coconut"),
	
	TF_MOD_FLAVOUR_BLUEBERRY(FluidFlavour.BLUEBERRY, "flavours/blueberry"),
	
	TF_MOD_FLAVOUR_BANANA(FluidFlavour.BANANA, "flavours/banana"),
	
	
	// Fetishes:
	
	TF_MOD_FETISH_BODY_PART("身体部位性癖",
			"应用一项关于身体部位性癖的效果。",
			"性癖",
			"modifier_circle_desires",
			PresetColour.BASE_PINK,
			Rarity.COMMON),

	TF_MOD_FETISH_BEHAVIOUR("行为性癖",
			"应用一项关于行为性癖的效果。",
			"性癖",
			"modifier_circle_desires",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	
	TF_MOD_FETISH_ANAL_GIVING(Fetish.FETISH_ANAL_GIVING),
	TF_MOD_FETISH_ANAL_RECEIVING(Fetish.FETISH_ANAL_RECEIVING),
	TF_MOD_FETISH_VAGINAL_GIVING(Fetish.FETISH_VAGINAL_GIVING),
	TF_MOD_FETISH_VAGINAL_RECEIVING(Fetish.FETISH_VAGINAL_RECEIVING),
	TF_MOD_FETISH_PENIS_GIVING(Fetish.FETISH_PENIS_GIVING),
	TF_MOD_FETISH_PENIS_RECEIVING(Fetish.FETISH_PENIS_RECEIVING),
	TF_MOD_FETISH_BREASTS_OTHERS(Fetish.FETISH_BREASTS_OTHERS),
	TF_MOD_FETISH_BREASTS_SELF(Fetish.FETISH_BREASTS_SELF),
	TF_MOD_FETISH_ORAL_RECEIVING(Fetish.FETISH_ORAL_RECEIVING),
	TF_MOD_FETISH_ORAL_GIVING(Fetish.FETISH_ORAL_GIVING),
	TF_MOD_FETISH_LEG_LOVER(Fetish.FETISH_LEG_LOVER),
	TF_MOD_FETISH_STRUTTER(Fetish.FETISH_STRUTTER),
	TF_MOD_FETISH_FOOT_GIVING(Fetish.FETISH_FOOT_GIVING),
	TF_MOD_FETISH_FOOT_RECEIVING(Fetish.FETISH_FOOT_RECEIVING),
	TF_MOD_FETISH_ARMPIT_GIVING(Fetish.FETISH_ARMPIT_GIVING),
	TF_MOD_FETISH_ARMPIT_RECEIVING(Fetish.FETISH_ARMPIT_RECEIVING),
	TF_MOD_FETISH_LACTATION_OTHERS(Fetish.FETISH_LACTATION_OTHERS),
	TF_MOD_FETISH_LACTATION_SELF(Fetish.FETISH_LACTATION_SELF),
	
	TF_MOD_FETISH_DOMINANT(Fetish.FETISH_DOMINANT),
	TF_MOD_FETISH_SUBMISSIVE(Fetish.FETISH_SUBMISSIVE),
	TF_MOD_FETISH_BONDAGE_VICTIM(Fetish.FETISH_BONDAGE_VICTIM),
	TF_MOD_FETISH_BONDAGE_APPLIER(Fetish.FETISH_BONDAGE_APPLIER),
	TF_MOD_FETISH_CROSS_DRESSER(Fetish.FETISH_CROSS_DRESSER),
	TF_MOD_FETISH_CUM_ADDICT(Fetish.FETISH_CUM_ADDICT),
	TF_MOD_FETISH_CUM_STUD(Fetish.FETISH_CUM_STUD),
	TF_MOD_FETISH_DEFLOWERING(Fetish.FETISH_DEFLOWERING),
	TF_MOD_FETISH_DENIAL(Fetish.FETISH_DENIAL),
	TF_MOD_FETISH_DENIAL_SELF(Fetish.FETISH_DENIAL_SELF),
	TF_MOD_FETISH_EXHIBITIONIST(Fetish.FETISH_EXHIBITIONIST),
	TF_MOD_FETISH_VOYEURIST(Fetish.FETISH_VOYEURIST),
	TF_MOD_FETISH_IMPREGNATION(Fetish.FETISH_IMPREGNATION),
	TF_MOD_FETISH_INCEST(Fetish.FETISH_INCEST),
	TF_MOD_FETISH_MASOCHIST(Fetish.FETISH_MASOCHIST),
	TF_MOD_FETISH_MASTURBATION(Fetish.FETISH_MASTURBATION),
	TF_MOD_FETISH_NON_CON_DOM(Fetish.FETISH_NON_CON_DOM),
	TF_MOD_FETISH_NON_CON_SUB(Fetish.FETISH_NON_CON_SUB),
	TF_MOD_FETISH_PREGNANCY(Fetish.FETISH_PREGNANCY),
	TF_MOD_FETISH_PURE_VIRGIN(Fetish.FETISH_PURE_VIRGIN),
	TF_MOD_FETISH_SADIST(Fetish.FETISH_SADIST),
	TF_MOD_FETISH_TRANSFORMATION_GIVING(Fetish.FETISH_TRANSFORMATION_GIVING),
	TF_MOD_FETISH_TRANSFORMATION_RECEIVING(Fetish.FETISH_TRANSFORMATION_RECEIVING),
	TF_MOD_FETISH_BIMBO(Fetish.FETISH_BIMBO),
	TF_MOD_FETISH_KINK_GIVING(Fetish.FETISH_KINK_GIVING),
	TF_MOD_FETISH_KINK_RECEIVING(Fetish.FETISH_KINK_RECEIVING),
	TF_MOD_FETISH_SIZE_QUEEN(Fetish.FETISH_SIZE_QUEEN),


	TF_MOD_FETISH_AGE_YOUNGER(OtherRegistrars.FETISH_AGE_YOUNGER),
	TF_MOD_FETISH_AGE_OLDER(OtherRegistrars.FETISH_AGE_OLDER),
	TF_MOD_FETISH_PEE_GIVING(OtherRegistrars.FETISH_PEE_GIVING),
	TF_MOD_FETISH_PEE_RECEIVING(OtherRegistrars.FETISH_PEE_RECEIVING),
	;

	private static List<TFModifier> TFModStrengthList = new ArrayList<>();
	private static List<TFModifier> TFModIntelligenceList = new ArrayList<>();
	private static List<TFModifier> TFModCorruptionList = new ArrayList<>();
	private static List<TFModifier> TFModSexualList = new ArrayList<>();
	private static List<TFModifier> TFAttributeList = new ArrayList<>();
	private static List<TFModifier> TFRacialBodyPartsList = new ArrayList<>();
	private static List<TFModifier> TFBodyPartFetishList = new ArrayList<>();
	private static List<TFModifier> TFBehaviouralFetishList = new ArrayList<>();

	private static List<TFModifier> clothingPrimaryList = new ArrayList<>();
	private static List<TFModifier> clothingAttributeList = new ArrayList<>();
	private static List<TFModifier> clothingMajorAttributeList = new ArrayList<>();
	private static List<TFModifier> clothingCreampieRetentionList = new ArrayList<>();
	
	private static List<TFModifier> tattooPrimaryList = new ArrayList<>();

	private static List<TFModifier> weaponPrimaryList = new ArrayList<>();
	private static List<TFModifier> weaponAttributeList = new ArrayList<>();
	private static List<TFModifier> weaponMajorAttributeList = new ArrayList<>();

	private static List<TFModifier> dollPrimaryList = new ArrayList<>();
	private static List<TFModifier> dollSecondaryList = new ArrayList<>();
	
	
	static {

		TFModStrengthList.add(NONE);
		TFModIntelligenceList.add(NONE);
		TFModCorruptionList.add(NONE);
		TFModSexualList.add(NONE);
		
		for(TFModifier tfMod : TFModifier.values()) {
			if(tfMod.getAttributeCategory()!=null && tfMod!=TFModifier.DAMAGE_WEAPON && tfMod!=TFModifier.RESISTANCE_WEAPON) {
				switch(tfMod.getAttributeCategory()) {
					case CORRUPTION:
						TFModCorruptionList.add(tfMod);
						break;
					case INTELLIGENCE:
						TFModIntelligenceList.add(tfMod);
						break;
					case STRENGTH:
						TFModStrengthList.add(tfMod);
						break;
				}
			}
		}
		
		TFModSexualList.add(FERTILITY);
		TFModSexualList.add(VIRILITY);
		TFModSexualList.add(DAMAGE_LUST);
		TFModSexualList.add(RESISTANCE_LUST);
		
		// Body parts:
		
		TFRacialBodyPartsList.add(NONE);
		TFRacialBodyPartsList.add(TF_FACE);
		TFRacialBodyPartsList.add(TF_SKIN);
		TFRacialBodyPartsList.add(TF_CORE);
		
		TFRacialBodyPartsList.add(TF_ARMS);
		TFRacialBodyPartsList.add(TF_LEGS);
		
		TFRacialBodyPartsList.add(TF_EARS);
		TFRacialBodyPartsList.add(TF_EYES);
		TFRacialBodyPartsList.add(TF_HAIR);
		TFRacialBodyPartsList.add(TF_ANTENNA);
		TFRacialBodyPartsList.add(TF_HORNS);
		TFRacialBodyPartsList.add(TF_TAIL);
		TFRacialBodyPartsList.add(TF_TENTACLE);
		TFRacialBodyPartsList.add(TF_WINGS);
		
		TFRacialBodyPartsList.add(TF_ASS);
		TFRacialBodyPartsList.add(TF_BREASTS);
		TFRacialBodyPartsList.add(TF_BREASTS_CROTCH);
		TFRacialBodyPartsList.add(TF_PENIS);
		TFRacialBodyPartsList.add(TF_VAGINA);
		
		TFRacialBodyPartsList.add(TF_MILK);
		TFRacialBodyPartsList.add(TF_MILK_CROTCH);
		TFRacialBodyPartsList.add(TF_CUM);
		TFRacialBodyPartsList.add(TF_GIRLCUM);
		
//		TFAttributeList.add(NONE);
		TFAttributeList.add(ARCANE_BOOST);
		
		
		clothingCreampieRetentionList.add(TF_FACE);
		clothingCreampieRetentionList.add(TF_ASS);
		clothingCreampieRetentionList.add(TF_VAGINA);
		clothingCreampieRetentionList.add(TF_VAGINA_URETHRA);
		clothingCreampieRetentionList.add(TF_BREASTS);
		clothingCreampieRetentionList.add(TF_BREASTS_CROTCH);
		clothingCreampieRetentionList.add(TF_PENIS_URETHRA);
		clothingCreampieRetentionList.add(TF_SPINNERET);

		TFBodyPartFetishList.add(TF_MOD_FETISH_ANAL_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ANAL_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_BREASTS_OTHERS);
		TFBodyPartFetishList.add(TF_MOD_FETISH_BREASTS_SELF);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ORAL_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ORAL_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_VAGINAL_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_VAGINAL_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_PENIS_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_PENIS_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_LEG_LOVER);
		TFBodyPartFetishList.add(TF_MOD_FETISH_STRUTTER);
		TFBodyPartFetishList.add(TF_MOD_FETISH_FOOT_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_FOOT_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ARMPIT_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ARMPIT_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_LACTATION_OTHERS);
		TFBodyPartFetishList.add(TF_MOD_FETISH_LACTATION_SELF);

		TFBehaviouralFetishList.add(TF_MOD_FETISH_DOMINANT);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_SUBMISSIVE);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_BONDAGE_APPLIER);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_BONDAGE_VICTIM);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_CUM_STUD);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_CUM_ADDICT);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_DEFLOWERING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_PURE_VIRGIN);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_IMPREGNATION);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_PREGNANCY);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_TRANSFORMATION_GIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_TRANSFORMATION_RECEIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_SADIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_MASOCHIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_NON_CON_DOM);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_NON_CON_SUB);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_DENIAL);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_DENIAL_SELF);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_VOYEURIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_EXHIBITIONIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_BIMBO);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_CROSS_DRESSER);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_MASTURBATION);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_INCEST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_KINK_GIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_KINK_RECEIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_SIZE_QUEEN);

		clothingPrimaryList.add(TFModifier.CLOTHING_MAJOR_ATTRIBUTE);
		clothingPrimaryList.add(TFModifier.CLOTHING_ATTRIBUTE);
		clothingPrimaryList.add(TFModifier.CLOTHING_SPECIAL);
		clothingPrimaryList.add(TFModifier.CLOTHING_CREAMPIE_RETENTION);
		clothingPrimaryList.add(TFModifier.TF_MOD_FETISH_BODY_PART);
		clothingPrimaryList.add(TFModifier.TF_MOD_FETISH_BEHAVIOUR);
		clothingPrimaryList.add(TF_FACE);
		clothingPrimaryList.add(TF_CORE);
		clothingPrimaryList.add(TF_ARMS);
		clothingPrimaryList.add(TF_HAIR);
		clothingPrimaryList.add(TF_ASS);
		clothingPrimaryList.add(TF_BREASTS);
		clothingPrimaryList.add(TF_BREASTS_CROTCH);
		clothingPrimaryList.add(TF_PENIS);
		clothingPrimaryList.add(TF_VAGINA);

		clothingMajorAttributeList.add(TFModifier.HEALTH_MAXIMUM);
		clothingMajorAttributeList.add(TFModifier.MANA_MAXIMUM);
		clothingMajorAttributeList.add(TFModifier.STRENGTH);
		clothingMajorAttributeList.add(TFModifier.INTELLIGENCE);
		clothingMajorAttributeList.add(TFModifier.CORRUPTION);
		
		clothingAttributeList.add(TFModifier.FERTILITY);
		clothingAttributeList.add(TFModifier.VIRILITY);
		clothingAttributeList.add(TFModifier.DAMAGE_UNARMED);
		clothingAttributeList.add(TFModifier.DAMAGE_MELEE_WEAPON);
		clothingAttributeList.add(TFModifier.DAMAGE_RANGED_WEAPON);
		clothingAttributeList.add(TFModifier.DAMAGE_PHYSICAL);
		clothingAttributeList.add(TFModifier.DAMAGE_LUST);
		clothingAttributeList.add(TFModifier.DAMAGE_FIRE);
		clothingAttributeList.add(TFModifier.DAMAGE_ICE);
		clothingAttributeList.add(TFModifier.DAMAGE_POISON);
		clothingAttributeList.add(TFModifier.DAMAGE_SPELLS);
		clothingAttributeList.add(TFModifier.RESISTANCE_FIRE);
		clothingAttributeList.add(TFModifier.RESISTANCE_ICE);
		clothingAttributeList.add(TFModifier.RESISTANCE_LUST);
		clothingAttributeList.add(TFModifier.RESISTANCE_PHYSICAL);
		clothingAttributeList.add(TFModifier.RESISTANCE_POISON);
		clothingAttributeList.add(TFModifier.SPELL_COST_MODIFIER);
		clothingAttributeList.add(TFModifier.CRITICAL_DAMAGE);
		

		tattooPrimaryList.add(TFModifier.CLOTHING_MAJOR_ATTRIBUTE);
		tattooPrimaryList.add(TFModifier.CLOTHING_ATTRIBUTE);
		tattooPrimaryList.add(TFModifier.CLOTHING_CREAMPIE_RETENTION);
		tattooPrimaryList.add(TFModifier.TF_MOD_FETISH_BODY_PART);
		tattooPrimaryList.add(TFModifier.TF_MOD_FETISH_BEHAVIOUR);
		tattooPrimaryList.add(TF_FACE);
		tattooPrimaryList.add(TF_CORE);
		tattooPrimaryList.add(TF_HAIR);
		tattooPrimaryList.add(TF_ASS);
		tattooPrimaryList.add(TF_BREASTS);
		tattooPrimaryList.add(TF_BREASTS_CROTCH);
		tattooPrimaryList.add(TF_PENIS);
		tattooPrimaryList.add(TF_VAGINA);
		

		weaponPrimaryList.add(TFModifier.CLOTHING_MAJOR_ATTRIBUTE);
		weaponPrimaryList.add(TFModifier.CLOTHING_ATTRIBUTE);
		
//		weaponAttributeList.add(TFModifier.RESISTANCE_WEAPON);
//		weaponAttributeList.add(TFModifier.DAMAGE_WEAPON);

		weaponMajorAttributeList.add(TFModifier.HEALTH_MAXIMUM);
		weaponMajorAttributeList.add(TFModifier.MANA_MAXIMUM);
		weaponMajorAttributeList.add(TFModifier.STRENGTH);
		weaponMajorAttributeList.add(TFModifier.INTELLIGENCE);
		weaponMajorAttributeList.add(TFModifier.CORRUPTION);
		
		weaponAttributeList.add(TFModifier.FERTILITY);
		weaponAttributeList.add(TFModifier.VIRILITY);
		weaponAttributeList.add(TFModifier.DAMAGE_UNARMED);
		weaponAttributeList.add(TFModifier.DAMAGE_MELEE_WEAPON);
		weaponAttributeList.add(TFModifier.DAMAGE_RANGED_WEAPON);
		weaponAttributeList.add(TFModifier.DAMAGE_PHYSICAL);
		weaponAttributeList.add(TFModifier.DAMAGE_LUST);
		weaponAttributeList.add(TFModifier.DAMAGE_FIRE);
		weaponAttributeList.add(TFModifier.DAMAGE_ICE);
		weaponAttributeList.add(TFModifier.DAMAGE_POISON);
		weaponAttributeList.add(TFModifier.DAMAGE_SPELLS);
		weaponAttributeList.add(TFModifier.RESISTANCE_FIRE);
		weaponAttributeList.add(TFModifier.RESISTANCE_ICE);
		weaponAttributeList.add(TFModifier.RESISTANCE_LUST);
		weaponAttributeList.add(TFModifier.RESISTANCE_PHYSICAL);
		weaponAttributeList.add(TFModifier.RESISTANCE_POISON);
		weaponAttributeList.add(TFModifier.SPELL_COST_MODIFIER);
		weaponAttributeList.add(TFModifier.CRITICAL_DAMAGE);
	}
	
	
	private enum AttributeCategory {
		STRENGTH,
		INTELLIGENCE,
		CORRUPTION;
	}
	
	private AttributeCategory attributeCategory;
	private AbstractAttribute associatedAttribute;
	
	private String name;
	private String description;
	private String descriptor;
	private String path;
	private String SVGString;
	
	private Colour colour;
	private Rarity rarity;
	private AbstractFetish fetish;
	
	private TFModifier(AttributeCategory attributeCategory, AbstractAttribute associatedAttribute, String description, String SVGString, Rarity rarity) {
		this.attributeCategory=attributeCategory;
		this.associatedAttribute=associatedAttribute;
		this.name = associatedAttribute.getName();
		this.description = description;
		this.descriptor = associatedAttribute.getPositiveEnchantment();
		this.colour = associatedAttribute.getColour();
		this.rarity=rarity;
		
		this.path = SVGString;
		this.SVGString = null;
	}
	
	private TFModifier(String name, String description, String descriptor, String SVGString, Colour colour, Rarity rarity) {
		this.name = name;
		this.description = description;
		this.descriptor = descriptor;
		this.rarity=rarity;
		
		if (colour == null) {
			this.colour = PresetColour.CLOTHING_BLACK;
		} else {
			this.colour = colour;
		}
		
		this.path = SVGString;
		this.SVGString = null;
	}
	
	private TFModifier(AbstractFetish f) {
		this.name = f.getName(null);
		this.description = "应用一项关于"+name+"性癖("+Util.capitaliseSentence(f.getShortDescriptor(null))+"相关的效果。)";
		this.descriptor = name;
		this.rarity = Rarity.EPIC;
		this.colour = PresetColour.FETISH;
		this.fetish = f;
		this.SVGString = f.getSVGString(null);
	}

	private TFModifier(FluidFlavour flavour, String pathName) {
		this(flavour, pathName, flavour.getColour());
	}
	
	private TFModifier(FluidFlavour flavour, String pathName, Colour colour) {
		this.name = flavour.getName()+(flavour==FluidFlavour.FLAVOURLESS?"":"味");
		this.description = "应用一项改变液体味道相关的效果。";
		this.descriptor = name;
		this.rarity = Rarity.COMMON;
		this.colour = colour;
		this.path = pathName;
		this.SVGString = null;
	}
	
	public int getValue() {
		switch(getRarity()) {
			case JINXED:
				return 1;
			case COMMON:
				return 1;
			case UNCOMMON:
				return 2;
			case RARE:
				return 4;
			case EPIC:
				return 8;
			case LEGENDARY:
				return 12;
			case QUEST:
				return 1;
		}
		return 1;
	}
	
	public AttributeCategory getAttributeCategory() {
		return attributeCategory;
	}

	public AbstractAttribute getAssociatedAttribute() {
		return associatedAttribute;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public String getSVGString() {
		if(SVGString==null) {
			// Set this item's file image:
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/crafting/" + path + ".svg");
				if(is==null) {
					System.err.println("Error! TFModifier icon file does not exist (Trying to read from '"+path+"')! (Code 2)");
				}
				String s = Util.inputStreamToString(is);

				is.close();
				
				if(path.contains("flavour")) {
					String SVGStringBackground = "";
					is = Subspecies.class.getClassLoader().getResourceAsStream("com/lilithsthrone/res/crafting/flavours/background.svg");
					if(is==null) {
						System.err.println("Error! Subspecies background icon file does not exist (Trying to read from 'flavours/background')!");
					}
					SVGStringBackground = "<div style='width:80%;height:80%;position:absolute;left:10%;bottom:10%;'>"+SvgUtil.colourReplacement(this.toString()+"_B", this.getColour(), Util.inputStreamToString(is))+"</div>";
					
					s = SVGStringBackground + "<div style='width:50%;height:50%;position:absolute;left:25%;bottom:25%;'>" + SvgUtil.colourReplacement(this.toString(), this.getColour(), s)+"</div>";
					
				} else {
					s = SvgUtil.colourReplacement(this.toString(), this.getColour(), s);
				}
				
				this.SVGString = s;

				is.close();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return SVGString;
	}

	public Colour getColour() {
		return colour;
	}
	
	public boolean isSoloDescriptor() {
		return false;
	}
	
	public Rarity getRarity() {
		return rarity;
	}

	public static List<TFModifier> getTFModStrengthList() {
		return TFModStrengthList;
	}

	public static List<TFModifier> getTFModIntelligenceList() {
		return TFModIntelligenceList;
	}

	public static List<TFModifier> getTFModCorruptionList() {
		return TFModCorruptionList;
	}
	
	public static List<TFModifier> getTFModSexualList() {
		return TFModSexualList;
	}

	public static List<TFModifier> getTFRacialBodyPartsList() {
		List<TFModifier> returnList = new ArrayList<>(TFRacialBodyPartsList);
		if(!Main.game.isUdderContentEnabled()) {
			returnList.remove(TFModifier.TF_BREASTS_CROTCH);
			returnList.remove(TFModifier.TF_MILK_CROTCH);
		}
		return returnList;
	}

	public static List<TFModifier> getTFAttributeList() {
		return TFAttributeList;
	}

	public static List<TFModifier> getTFBodyPartFetishList() {
		List<TFModifier> returnList = new ArrayList<>(TFBodyPartFetishList);
		returnList.removeIf(modifier->!modifier.fetish.isContentEnabled());
		return returnList;
	}
	
	public static List<TFModifier> getTFBehaviouralFetishList() {
		List<TFModifier> returnList = new ArrayList<>(TFBehaviouralFetishList);
		returnList.removeIf(modifier->!modifier.fetish.isContentEnabled());
		return returnList;
	}

	public AbstractFetish getFetish() {
		return fetish;
	}

	public static List<TFModifier> getClothingAttributeList() {
		return clothingAttributeList;
	}

	public static List<TFModifier> getClothingMajorAttributeList() {
		return clothingMajorAttributeList;
	}

	public static List<TFModifier> getClothingCreampieRetentionList() {
		List<TFModifier> returnList = new ArrayList<>(clothingCreampieRetentionList);
		if(!Main.game.isUdderContentEnabled()) {
			returnList.remove(TFModifier.TF_BREASTS_CROTCH);
		}
		if(!Main.game.isNipplePenEnabled()) {
			returnList.remove(TFModifier.TF_BREASTS);
			returnList.remove(TFModifier.TF_BREASTS_CROTCH);
		}
		if(!Main.game.isUrethraEnabled()) {
			returnList.remove(TFModifier.TF_PENIS_URETHRA);
			returnList.remove(TFModifier.TF_VAGINA_URETHRA);
		}
		return returnList;
	}
	
	public static List<TFModifier> getClothingPrimaryList() {
		List<TFModifier> returnList = new ArrayList<>(clothingPrimaryList);
		if(!Main.game.isBodyHairEnabled()) {
			returnList.remove(TF_ARMS);
		}
		if(!Main.game.isUdderContentEnabled()) {
			returnList.remove(TFModifier.TF_BREASTS_CROTCH);
			returnList.remove(TFModifier.TF_MILK_CROTCH);
		}
		return returnList;
	}

	public static List<TFModifier> getTattooPrimaryList() {
		return tattooPrimaryList;
	}

	public static List<TFModifier> getWeaponPrimaryList() {
		return weaponPrimaryList;
	}

	public static List<TFModifier> getWeaponMajorAttributeList() {
		return weaponMajorAttributeList;
	}
	
	public static List<TFModifier> getWeaponAttributeList() {
		return weaponAttributeList;
	}

	public static List<TFModifier> getDollPrimaryList() {
		return dollPrimaryList;
	}

	public static List<TFModifier> getDollSecondaryList() {
		return dollSecondaryList;
	}
}
