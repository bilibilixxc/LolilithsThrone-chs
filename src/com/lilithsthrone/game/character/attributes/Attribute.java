package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NOTE: Racial attributes are added at the bottom of the static block in Race.java!
 * 
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class Attribute {

	public static AbstractAttribute MAJOR_PHYSIQUE = new AbstractAttribute(false,
			0,
			0,
			100,
			"physique",
			"体格",
			"strengthIcon",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			"强壮",
			"虚弱",
			Util.newArrayListOfValues(
					"每1点体格<b>+2</b><b style='color: " + PresetColour.ATTRIBUTE_HEALTH.toWebHexString() + "'>生命</b>")) {
		@Override
		public boolean hasStatusEffect() {
			return true;
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"衡量[npc.name]身体健康的标准，体格会<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>被动提升</b>[npc.her]的"
							+ "<b style='color:" + PresetColour.ATTRIBUTE_HEALTH.toWebHexString() + ";'>最大生命值</b>。");
		}
		@Override
		public int getOrderPriority() {
			return 10;
		}
	};

	public static AbstractAttribute MAJOR_ARCANE = new AbstractAttribute(false,
			0,
			0,
			100,
			"arcane",
			"奥术",
			"intelligenceIcon",
			PresetColour.ATTRIBUTE_ARCANE,
			"奥术增幅",
			"奥术流失",
			Util.newArrayListOfValues(
					"每1点奥术<b>+2</b><b style='color: " + PresetColour.ATTRIBUTE_MANA.toWebHexString() + "'>灵气</b>")) {
		@Override
		public boolean hasStatusEffect() {
			return true;
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
						"衡量[npc.namePos]奥术亲和力的标准。奥术会<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>被动提升</b>[npc.her]的"
								+ "<b style='color:" + PresetColour.ATTRIBUTE_MANA.toWebHexString() + ";'>最大灵气</b>。");
		}
		@Override
		public int getOrderPriority() {
			return 20;
		}
	};

	public static AbstractAttribute MAJOR_CORRUPTION = new AbstractAttribute(false,
			0,
			0,
			100,
			"corruption",
			"堕落",
			"corruptionIcon",
			PresetColour.ATTRIBUTE_CORRUPTION,
			"堕落",
			"纯洁",
			Util.newArrayListOfValues(
					"每1点堕落<b>-0.5</b><b style='color: " + PresetColour.ATTRIBUTE_MANA.toWebHexString() + "'>快感抵抗</b>",
					"每1点堕落<b>+0.5</b><b style='color: " + PresetColour.DAMAGE_TYPE_MANA.toWebHexString() + "'>快感伤害</b>")) {
		@Override
		public boolean hasStatusEffect() {
			return true;
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner.isPlayer()) {
				return "堕落是衡量你有多变态的数值，会影响<b style='color:" + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>哪些性动作能随心所欲地使用</b>。";
			} else {
				return UtilText.parse(owner,
						"堕落是衡量[npc.namePos]有多变态的数值，并<i>不能</i>反映[npc.she]是善良还是邪恶。");
			}
		}
		@Override
		public int getOrderPriority() {
			return 30;
		}
	};

	public static AbstractAttribute HEALTH_MAXIMUM = new AbstractAttribute(false,
			1,
			1,
			999,
			"健康",
			"生命",
			"healthIcon",
			PresetColour.ATTRIBUTE_HEALTH,
			"健康",
			"患病",
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.Name]所拥有的体能与决心。在战斗中降为0后便会被击败。<br/>"
						+ "能够额外增加至“加成”数值的生命来源:<br/>"
						+"<b>"+ GameCharacter.HEALTH_CALCULATION + "</b>");
		}

		@Override
		public int getOrderPriority() {
			return 40;
		}
	};

	public static AbstractAttribute MANA_MAXIMUM = new AbstractAttribute(false,
			1,
			1,
			999,
			"aura",
			"灵气",
			"manaIcon",
			PresetColour.ATTRIBUTE_MANA,
			"灵气增强",
			"灵气流失",
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"衡量[npc.name]灵气中奥术能量多少的标准。<br/>"
						+ "能够额外增加至“加成”数值的灵气来源:<br/>"
						+ "<b>" + GameCharacter.MANA_CALCULATION + "</b>");
		}

		@Override
		public int getOrderPriority() {
			return 50;
		}
	};
	
	public static AbstractAttribute EXPERIENCE = new AbstractAttribute(false,
			0,
			0,
			1000000,
			"experience",
			"经验",
			"experienceIcon",
			PresetColour.GENERIC_EXPERIENCE,
			"好学",
			"健忘",
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.Name]提升至下一级过程中积累的进度。");
		}

		@Override
		public int getOrderPriority() {
			return 60;
		}
	};

	public static AbstractAttribute ACTION_POINTS = new AbstractAttribute(false,
			0,
			0,
			10,
			"action points",
			"行动点数",
			"action_points",
			PresetColour.GENERIC_ACTION_POINTS,
			"掌握先机",
			"无精打采",
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.nameHasFull]在战斗中能够用于动作的行动点数。");
		}

		@Override
		public int getOrderPriority() {
			return 70;
		}
	};

	public static AbstractAttribute ENCHANTMENT_LIMIT = new AbstractAttribute(false,
			0,
			0,
			1000,
			"enchantment instability",
			"附魔不稳定",
			"enchantmentLimitIcon",
			PresetColour.GENERIC_ENCHANTMENT,
			"掌控一切",
			"笨手笨脚",
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
						"在不造成严重惩罚的前提下，[npc.nameIsFull]能够驾驭的衣物、武器、纹身附魔的总量。");
		}
		@Override
		public boolean isAffectedByEnchantmentCost() {
			return false;
		}
		@Override
		public int getOrderPriority() {
			return 80;
		}

		@Override
		public int getUpperLimit() {
			return (Main.game != null && Main.getProperties() != null) ? ModProps.enchantmentInstabilityUpperLimit : 1000;
		}
	};

	// Sexual attributes:
	
	public static AbstractAttribute LUST = new AbstractAttribute(false,
			0,
			0,
			100,
			"lust",
			"性欲",
			"arousalIcon",
			PresetColour.ATTRIBUTE_LUST,
			"肉欲",
			"冷淡",
			null) {
		@Override
		public boolean hasStatusEffect() {
			return true;
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner.isPlayer()) {
				return "衡量你有多急切地想进行性接触的标准。你的性欲值会随时间逐渐向日常性欲值恢复。<br/>"
						+ "<b>日常性欲 = " + GameCharacter.RESTING_LUST_CALCULATION + "</b>";
			} else {
				return UtilText.parse(owner,
						"[npc.Name]有多急切地想进行性接触。");
			}
		}
		@Override
		public int getOrderPriority() {
			return 100;
		}
	};
	
	public static AbstractAttribute RESTING_LUST = new AbstractAttribute(false,
			0,
			-100,
			80,
			"resting lust",
			"日常性欲",
			"arousalIcon",
			PresetColour.ATTRIBUTE_LUST,
			"肉欲",
			"冷淡",
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]的性欲会随时间恢复至该数值。");
		}

		@Override
		public int getOrderPriority() {
			return 110;
		}
	};
	
	public static AbstractAttribute AROUSAL = new AbstractAttribute(false,
			0,
			0,
			100,
			"arousal",
			"快感",
			"arousalIcon",
			PresetColour.ATTRIBUTE_AROUSAL,
			"持久",
			"早泄",
			null) {
		@Override
		public boolean hasStatusEffect() {
			return true;
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner.isPlayer())
				return "你现在积累了多少快感，充满时则会高潮。";
			else
				return UtilText.parse(owner,
						"[npc.name]积累了多少快感。充满时[npc.She]则会高潮。");
		}
		@Override
		public int getOrderPriority() {
			return 120;
		}
	};
	
	public static AbstractAttribute VIRILITY = new AbstractAttribute(true, 10, -100, 100, "virility", "生殖力", "shieldIcon", PresetColour.GENERIC_SEX, "多育", "不育", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升使人受孕的概率。";
		}

		@Override
		public int getOrderPriority() {
			return 130;
		}
	};
	
	public static AbstractAttribute FERTILITY = new AbstractAttribute(true, 10, -100, 100, "fertility", "生育力", "shieldIcon", PresetColour.GENERIC_SEX, "多育", "不育", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升怀孕的概率。";
		}

		@Override
		public int getOrderPriority() {
			return 140;
		}
	};

	// Combat attributes:

	public static AbstractAttribute SPELL_COST_MODIFIER = new AbstractAttribute(true, 0, 0, 80, "spell efficiency", "法术效率", "shieldIcon", PresetColour.ATTRIBUTE_MANA, "精通", "无能", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "降低释放法术的消耗。";
		}

		@Override
		public int getOrderPriority() {
			return 200;
		}
	};
	
	public static AbstractAttribute CRITICAL_DAMAGE = new AbstractAttribute(true, 150, 100, 500, "critical power", "暴击伤害", "shieldIcon", PresetColour.ATTRIBUTE_HEALTH, "强大", "无力", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "每一点给于1%额外的暴击伤害。";
		}

		@Override
		public int getOrderPriority() {
			return 210;
		}
	};
	
	// Damages:

	public static AbstractAttribute DAMAGE_UNARMED = new AbstractAttribute(true, 0, -80, 100, "unarmed damage", "徒手伤害", "swordIcon", PresetColour.DAMAGE_TYPE_UNARMED, "精通武术", "武术不精", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升徒手攻击造成的伤害，包括又非人类身体部件发出的特殊攻击。";
		}

		@Override
		public int getOrderPriority() {
			return 220;
		}
	};
	
	public static AbstractAttribute DAMAGE_MELEE_WEAPON = new AbstractAttribute(true, 0, -80, 100, "melee weapon damage", "近战武器伤害", "swordIcon", PresetColour.DAMAGE_TYPE_MELEE, "近战大师", "不善近战", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升近战武器攻击造成的伤害。";
		}

		@Override
		public int getOrderPriority() {
			return 230;
		}
	};
	
	public static AbstractAttribute DAMAGE_RANGED_WEAPON = new AbstractAttribute(true, 0, -80, 100, "ranged weapon damage", "远程武器伤害", "swordIcon", PresetColour.DAMAGE_TYPE_RANGED, "远程大师", "不善远程", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升远程武器攻击造成的伤害。";
		}

		@Override
		public int getOrderPriority() {
			return 240;
		}
	};
	
	public static AbstractAttribute DAMAGE_SPELLS = new AbstractAttribute(true, 0, -80, 100, "spell damage", "法术伤害", "swordIcon", PresetColour.ATTRIBUTE_MANA, "奥术威能", "奥术弱能", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升法术伤害";
		}

		@Override
		public int getOrderPriority() {
			return 250;
		}
	};

	public static AbstractAttribute DAMAGE_PHYSICAL = new AbstractAttribute(true, 0, -80, 100, "physical damage", "物理伤害", "swordIcon", PresetColour.DAMAGE_TYPE_PHYSICAL, "强击", "轻击", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升物理伤害";
		}

		@Override
		public int getOrderPriority() {
			return 260;
		}
	};
	
	public static AbstractAttribute DAMAGE_LUST = new AbstractAttribute(true, 0, -80, 100, "lust damage", "性欲伤害", "swordIcon", PresetColour.GENERIC_SEX, "勾引", "排斥", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升性欲伤害。";
		}

		@Override
		public int getOrderPriority() {
			return 270;
		}
	};
	
	public static AbstractAttribute DAMAGE_FIRE = new AbstractAttribute(true, 0, -80, 100, "fire damage", "火焰伤害", "swordIcon", PresetColour.DAMAGE_TYPE_FIRE, "炼狱", "余烬", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升火焰伤害。";
		}

		@Override
		public int getOrderPriority() {
			return 280;
		}
	};
	
	public static AbstractAttribute DAMAGE_ICE = new AbstractAttribute(true, 0, -80, 100, "cold damage", "寒冷伤害", "swordIcon", PresetColour.DAMAGE_TYPE_COLD, "暴风雪", "融雪", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升寒冷伤害。";
		}

		@Override
		public int getOrderPriority() {
			return 290;
		}
	};
	
	public static AbstractAttribute DAMAGE_POISON = new AbstractAttribute(true, 0, -80, 100, "poison damage", "毒素伤害", "swordIcon", PresetColour.DAMAGE_TYPE_POISON, "剧毒", "稀释", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升毒素伤害。";
		}

		@Override
		public int getOrderPriority() {
			return 300;
		}
	};
	

	public static AbstractAttribute ENERGY_SHIELDING = new AbstractAttribute(false, 0, -100, 500, "health shielding", "生命护盾", "shieldIcon", PresetColour.ATTRIBUTE_HEALTH, "坚韧", "脆弱", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "每战斗回合开始时，该数值会被施加到生命护盾上。";
		}
		@Override
		public boolean isInfiniteAtUpperLimit() {
			return true;
		}
		@Override
		public String getInfiniteDescription() {
			return "[style.colourExcellent(免疫)][style.colourHealth(所有类型伤害)]";
		}
		@Override
		public int getOrderPriority() {
			return 400;
		}
	};

	
	// Resistances:

	public static AbstractAttribute RESISTANCE_PHYSICAL = new AbstractAttribute(false, 0, -100, 500, "physical shielding", "物理护盾", "shieldIcon", PresetColour.DAMAGE_TYPE_PHYSICAL, "强韧", "柔弱", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "降低受到的物理伤害。";
		}
		@Override
		public boolean isInfiniteAtUpperLimit() {
			return true;
		}
		@Override
		public String getInfiniteDescription() {
			return "[style.colourExcellent(免疫)][style.colourPhysical(物理伤害)]";
		}
		@Override
		public int getOrderPriority() {
			return 410;
		}
	};
	
	public static AbstractAttribute RESISTANCE_LUST = new AbstractAttribute(false, 0, -100, 500, "lust shielding", "性欲护盾", "shieldIcon", PresetColour.GENERIC_SEX, "贞操", "诱惑", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "降低受到的性欲伤害。";
		}
		@Override
		public boolean isInfiniteAtUpperLimit() {
			return true;
		}
		@Override
		public String getInfiniteDescription() {
			return "[style.colourExcellent(免疫)][style.colourLust(性欲伤害)]";
		}
		@Override
		public int getOrderPriority() {
			return 420;
		}
	};
	
	public static AbstractAttribute RESISTANCE_FIRE = new AbstractAttribute(false, 0, -100, 500, "fire shielding", "火焰护盾", "shieldIcon", PresetColour.DAMAGE_TYPE_FIRE, "阻燃", "易燃", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "降低受到的火焰伤害。";
		}
		@Override
		public boolean isInfiniteAtUpperLimit() {
			return true;
		}
		@Override
		public String getInfiniteDescription() {
			return "[style.colourExcellent(免疫)][style.colourFire(火焰伤害)]";
		}
		@Override
		public int getOrderPriority() {
			return 430;
		}
	};
	
	public static AbstractAttribute RESISTANCE_ICE = new AbstractAttribute(false, 0, -100, 500, "cold shielding", "寒冷护盾", "shieldIcon", PresetColour.DAMAGE_TYPE_COLD, "温暖", "冻伤", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "降低受到的寒冷伤害。";
		}
		@Override
		public boolean isInfiniteAtUpperLimit() {
			return true;
		}
		@Override
		public String getInfiniteDescription() {
			return "[style.colourExcellent(免疫)][style.colourIce(寒冷伤害)]";
		}
		@Override
		public int getOrderPriority() {
			return 440;
		}
	};
	
	public static AbstractAttribute RESISTANCE_POISON = new AbstractAttribute(false, 0, -100, 500, "poison shielding", "毒素护盾", "shieldIcon", PresetColour.DAMAGE_TYPE_POISON, "抗毒", "易感", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "降低受到的毒素伤害。";
		}
		@Override
		public boolean isInfiniteAtUpperLimit() {
			return true;
		}
		@Override
		public String getInfiniteDescription() {
			return "[style.colourExcellent(免疫)][style.colourPoison(毒素伤害)]";
		}
		@Override
		public int getOrderPriority() {
			return 450;
		}
	};
	
	// From v0.4, these are automatically generated in the static block at the end of the Race.java class!
//	// Racial:
//	
//	public static AbstractAttribute DAMAGE_ANGEL = new AbstractAttribute(true, 0, -100, 100, "angelic damage", "Angelic damage", "swordIcon", PresetColour.RACE_ANGEL, "angelic-obliteration", "angelic-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs angels.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_CAT_MORPH = new AbstractAttribute(true, 0, -100, 100, "cat-morph damage", "Cat-morph damage", "swordIcon", PresetColour.RACE_CAT_MORPH, "cat-morph-obliteration", "cat-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs cat-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_COW_MORPH = new AbstractAttribute(true, 0, -100, 100, "cow-morph damage", "Cow-morph damage", "swordIcon", PresetColour.RACE_COW_MORPH, "cow-morph-obliteration", "cow-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs cow-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_DEMON = new AbstractAttribute(true, 0, -100, 100, "demonic damage", "Demonic damage", "swordIcon", PresetColour.RACE_DEMON, "demonic-obliteration", "demonic-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs demons.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_DOG_MORPH = new AbstractAttribute(true, 0, -100, 100, "dog-morph damage", "Dog-morph damage", "swordIcon", PresetColour.RACE_DOG_MORPH, "dog-morph-obliteration", "dog-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs dog-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_HARPY = new AbstractAttribute(true, 0, -100, 100, "harpy damage", "Harpy damage", "swordIcon", PresetColour.RACE_HARPY, "harpy-obliteration", "harpy-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs harpies.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_HORSE_MORPH = new AbstractAttribute(true, 0, -100, 100, "horse-morph damage", "Horse-morph damage", "swordIcon", PresetColour.RACE_HORSE_MORPH, "horse-morph-obliteration", "horse-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs horse-morphs.";
//		}
//	};
	
	public static AbstractAttribute DAMAGE_ELDER_LILIN = new AbstractAttribute(true, 0, -100, 100, "elder lilin damage", "莉琳长老伤害", "swordIcon", PresetColour.RACE_LILIN, "肃清莉琳长老", "怜悯莉琳长老", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升对抗莉琳长老的伤害。";
		}

		@Override
		public int getOrderPriority() {
			return 1000;
		}
	};

	public static AbstractAttribute DAMAGE_LILIN = new AbstractAttribute(true, 0, -100, 100, "lilin damage", "莉琳伤害", "swordIcon", PresetColour.RACE_LILIN, "肃清莉琳", "怜悯莉琳", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升对抗莉琳的伤害。";
		}

		@Override
		public int getOrderPriority() {
			return 1100;
		}
	};
	
	public static AbstractAttribute DAMAGE_IMP = new AbstractAttribute(true, 0, -100, 100, "imp damage", "小恶魔伤害", "swordIcon", PresetColour.RACE_IMP, "肃清小恶魔", "怜悯小恶魔", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "提升对抗小恶魔的伤害。";
		}

		@Override
		public int getOrderPriority() {
			return 1200;
		}
	};

//	public static AbstractAttribute DAMAGE_REINDEER_MORPH = new AbstractAttribute(true, 0, -100, 100, "reindeer-morph damage", "Reindeer-morph damage", "swordIcon", PresetColour.RACE_REINDEER_MORPH, "reindeer-morph-obliteration", "reindeer-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs reindeer-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_HUMAN = new AbstractAttribute(true, 0, -100, 100, "human damage", "Human damage", "swordIcon", PresetColour.RACE_HUMAN, "human-obliteration", "human-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs humans.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_SQUIRREL_MORPH = new AbstractAttribute(true, 0, -100, 100, "squirrel-morph damage", "Squirrel-morph damage", "swordIcon", PresetColour.RACE_SQUIRREL_MORPH, "squirrel-morph-obliteration", "squirrel-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs squirrel-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_RAT_MORPH = new AbstractAttribute(true, 0, -100, 100, "rat-morph damage", "Rat-morph damage", "swordIcon", PresetColour.RACE_RAT_MORPH, "rat-morph-obliteration", "rat-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs rat-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_RABBIT_MORPH = new AbstractAttribute(true, 0, -100, 100, "rabbit-morph damage", "Rabbit-morph damage", "swordIcon", PresetColour.RACE_RABBIT_MORPH, "rabbit-morph-obliteration", "rabbit-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs rabbit-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_BAT_MORPH = new AbstractAttribute(true, 0, -100, 100, "bat-morph damage", "Bat-morph damage", "swordIcon", PresetColour.RACE_BAT_MORPH, "bat-morph-obliteration", "bat-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs bat-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_ALLIGATOR_MORPH = new AbstractAttribute(true, 0, -100, 100, "alligator-morph damage", "Alligator-morph damage", "swordIcon", PresetColour.RACE_ALLIGATOR_MORPH, "alligator-morph-obliteration", "alligator-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs alligator-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_WOLF_MORPH = new AbstractAttribute(true, 0, -100, 100, "wolf-morph damage", "Wolf-morph damage", "swordIcon", PresetColour.RACE_WOLF_MORPH, "wolf-morph-obliteration", "wolf-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs wolf-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_FOX_MORPH = new AbstractAttribute(true, 0, -100, 100, "fox-morph damage", "Fox-morph damage", "swordIcon", PresetColour.RACE_FOX_MORPH, "fox-morph-obliteration", "fox-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs fox-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_SLIME = new AbstractAttribute(true, 0, -100, 100, "slime damage", "Slime damage", "swordIcon", PresetColour.RACE_SLIME, "slime-obliteration", "slime-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs slimes.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_ELEMENTAL = new AbstractAttribute(true, 0, -100, 100, "elemental damage", "Elemental damage", "swordIcon", PresetColour.SPELL_SCHOOL_ARCANE, "elemental-obliteration", "elemental-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs elementals.";
//		}
//	};
	
	
	public static Map<AbstractAttribute, String> attributeToIdMap = new HashMap<>();
	public static Map<String, AbstractAttribute> idToAttributeMap = new HashMap<>();
	public static List<AbstractAttribute> allAttributes;
	
	public static Map<AbstractRace, AbstractAttribute> racialAttributes = new HashMap<>();

	private static Map<String, AbstractAttribute> oldConversionMapping = new HashMap<>();
	static {
		oldConversionMapping.put("CORRUPTION", Attribute.MAJOR_CORRUPTION);
		oldConversionMapping.put("STRENGTH", Attribute.MAJOR_PHYSIQUE);
		oldConversionMapping.put("MAJOR_STRENGTH", Attribute.MAJOR_PHYSIQUE);
		oldConversionMapping.put("INTELLIGENCE", Attribute.MAJOR_ARCANE);
		oldConversionMapping.put("RESISTANCE_ATTACK", Attribute.RESISTANCE_PHYSICAL);
		oldConversionMapping.put("RESISTANCE_MANA", Attribute.RESISTANCE_LUST);
		oldConversionMapping.put("RESISTANCE_PURE", Attribute.ENERGY_SHIELDING);
	}

	/**
	 * @return The Attribute that has an id closest to the supplied attributeId.
	 *  <b>Will return null</b> if the matching distance is greater than 3 (which typically will be more than enough to catch spelling errors, indicating that the flag has been removed).
	 */
	public static AbstractAttribute getAttributeFromId(String attributeId) {
		if(attributeId.startsWith("RESISTANCE_ELEMENTAL")) {
			attributeId = "RESISTANCE_ELEMENTAL";
		} else if(attributeId.startsWith("DAMAGE_ELEMENTAL")) {
			attributeId = "DAMAGE_ELEMENTAL";
		} else if(attributeId.startsWith("CRITICAL_CHANCE")) { // Critical chance was removed, so return damage instead as a replacement for old saves
			attributeId = "CRITICAL_DAMAGE";
		}
		
		if(oldConversionMapping.containsKey(attributeId)) {
			return oldConversionMapping.get(attributeId);
		}

		attributeId = Util.getClosestStringMatch(attributeId, idToAttributeMap.keySet(), 3);
		
		return idToAttributeMap.get(attributeId);
	}

	public static String getIdFromAttribute(AbstractAttribute attribute) {
		return attributeToIdMap.get(attribute);
	}

	public static List<AbstractAttribute> getAllAttributes() {
		return allAttributes;
	}
	
	public static AbstractAttribute getRacialDamageAttribute(AbstractRace race) {
		return racialAttributes.get(race);
	}
	
	static {
		allAttributes = new ArrayList<>();
		
		// Hard-coded attributes (all those up above):
		
		Field[] fields = Attribute.class.getFields();
		
		for(Field f : fields) {
			if (AbstractAttribute.class.isAssignableFrom(f.getType())) {
				AbstractAttribute attribute;
				try {
					attribute = ((AbstractAttribute) f.get(null));

					attributeToIdMap.put(attribute, f.getName());
					idToAttributeMap.put(f.getName(), attribute);
					allAttributes.add(attribute);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		// NOTE: Racial attributes are added at the bottom of the static block in Race.java!
	}
	
}
