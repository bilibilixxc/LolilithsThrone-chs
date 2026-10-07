package com.lilithsthrone.game.inventory.item;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectTimer;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.84
 * @version 0.4.0
 * @author Innoxia
 */
public class ItemType {
	
	public static String getGenericUseDescription(GameCharacter user, GameCharacter target, String playerUseSelf, String playerUsePartner, String partnerUseSelf, String partnerUsePlayer) {
		if (user!=null && user.isPlayer()) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "<p>"+playerUseSelf+"</p>";
					
				} else {
					return UtilText.parse(target, "<p>"+playerUsePartner+"</p>");
				}
			} else {
				return "";
			}
			
		} else {
			if(target!=null) {
				if(target.isPlayer()) {
					return UtilText.parse(user, "<p>"+partnerUsePlayer+"</p>");
					
				} else {
					return UtilText.parse(user, "<p>"+partnerUseSelf+"</p>");
				}
			} else {
				return "";
			}
		}
	}
	
	public static AbstractItemType FETISH_UNREFINED = new AbstractItemType(500,
			"一瓶",
			false,
			"魅魔之吻",
			"魅魔之吻",
			"一个心形的玻璃瓶，装满了涌动的，荧光粉色的液体"
					+ "瓶身上用华丽的字体刻着“魅魔之吻“的字样，而下面的“傻瓜式爱情药水”字样则清楚地表明了这种液体的作用。",
			"fetishDrink",
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_PINK,
					PresetColour.CLOTHING_PINK_LIGHT,
					PresetColour.CLOTHING_WHITE,
					PresetColour.CLOTHING_PINK_DARK),
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MYSTERY_KINK)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.MISC_TF_ITEM,
					ItemTag.SOLD_BY_RALPH,
					ItemTag.DRINK)) {
//		@Override
//		public boolean isFetishGiving() {
//			return true;
//		}
		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.FETISH_ENHANCEMENT;
		}
		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return FETISH_REFINED;
		}
		@Override
		public String getUseName() {
			return "喝";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你把瓶塞从“魅魔之吻”的瓶颈上拔掉，然后送到嘴唇边，吞下里面甘甜的粉红色液体。",
					"你把瓶塞从“魅魔之吻”的瓶颈上拔掉，然后把它送到[npc.namePos]唇边，给[npc.herHim]灌下了里面甘甜的粉红色液体。",
					"[npc.Name]拿出一瓶“魅魔之吻”，迅速拔掉了瓶塞，将整瓶一饮而尽。",
					"[npc.Name]拿出一瓶“魅魔之吻”，迅速拔掉了瓶塞，"
							+ "[npc.she]把瓶口送到你[pc.lips]边，稍微仰起你的脑袋，强行给你灌下了里面甘甜的粉红色液体。");
		}
	};
	
	public static AbstractItemType FETISH_REFINED = new AbstractItemType(750,
			"一小瓶",
			false,
			"天赐之癖",
			"天赐之癖",
			"一小瓶起泡的粉色液体，由“魅魔之吻”精炼而成。"
					+ "其附魔和之蒸馏前的液体有所不同，能够添加或移除特定性癖。",
			"fetishDrinkRefined",
			PresetColour.FETISH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(ItemTag.DRINK)) {
		@Override
		public boolean isFetishGiving() {
			return true;
		}
		@Override
		public String getUseName() {
			return "喝";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你把瓶塞从“天赐之癖”的瓶颈上拔掉，然后送到唇边，将里面甜腻的液体一饮而尽。",
					"你把瓶塞从“天赐之癖”的瓶颈上拔掉，然后送到[npc.namePos]唇边，将里面甜腻的液体灌入[npc.herHim]的口中。",
					"[npc.Name]拿出一瓶“天赐之癖”，迅速拔掉了瓶塞，将整瓶一饮而尽。",
					"[npc.Name]拿出一瓶“天赐之癖”，迅速拔掉了瓶塞，"
							+ "[npc.she]把瓶口送到你[pc.lips]边，稍微仰起你的脑袋，强行让你吞下了里面甜腻的液体。");
		}
	};
	
	public static AbstractItemType ADDICTION_REMOVAL = new AbstractItemType(750,
			"一瓶",
			false,
			"天使玉液",
			"天使玉液",
			"一个精致的玻璃瓶，里面装有清凉的蓝色液体。"
					+ "瓶身一侧阳刻着几个字“天使玉液”，但你不清楚这跟里面的液体到底有没有关系……",
			"addictionRemoval",
			PresetColour.RACE_HUMAN,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.ADDICTION_REMOVAL)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.SOLD_BY_RALPH,
					ItemTag.DRINK_QUALITY)) {
		@Override
		public String getUseName() {
			return "喝";
		}
		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ADDICTION_REMOVAL_REFINEMENT;
		}
		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ADDICTION_REMOVAL_REFINED;
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你把水晶瓶塞从“天使玉液”的瓶颈上拔掉，然后送到唇边，将里面没什么味道的液体一饮而尽。",
					"你把水晶瓶塞从“天使玉液”的瓶颈上拔掉，然后送到[npc.namePos]的唇边，将里面的液体灌入[npc.herHim]的口中。",
					"[npc.Name]拿出一瓶“天使玉液”，迅速拔掉了水晶瓶塞，将整瓶一饮而尽。",
					"[npc.Name]拿出一瓶“天使玉液”，迅速拔掉了水晶瓶塞，"
							+ "[npc.she]把瓶口送到你[pc.lips]边，稍微仰起你的脑袋，强行让你吞下了里面没什么味道的液体。");
		}
	};
	
	public static AbstractItemType ADDICTION_REMOVAL_REFINED = new AbstractItemType(1500,
			"一小瓶",
			false,
			"天使之纯洁",
			"天使之纯洁",
			"装有淡蓝色清凉液体的玻璃瓶，散发着微弱但持久的光芒。"
					+ "是精炼后的“天使玉液”，这种液体已经无法再移除成瘾效果，但无论谁喝下，都能够永久降低堕落……",
			"addictionRemovalRefined",
			PresetColour.RACE_HUMAN,
			null,
			null,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(ItemTag.DRINK_QUALITY)) {
		@Override
		public String getUseName() {
			return "喝";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你把瓶塞从“天使之纯洁”的瓶颈上拔掉，然后送到唇边，将里面清凉爽口的液体一饮而尽。",
					"你把瓶塞从“天使之纯洁”的瓶颈上拔掉，然后送到[npc.namePos]的唇边，将里面的液体灌入[npc.herHim]的口中。",
					"[npc.Name]拿出一瓶“天使之纯洁”，迅速拔掉了瓶塞，将整瓶一饮而尽。",
					"[npc.Name]拿出一瓶“天使之纯洁”，迅速拔掉了瓶塞，"
							+ "[npc.she]把瓶口送到你[pc.lips]边，稍微仰起你的脑袋，强行让你吞下了里面清凉爽口的液体。");
		}
	};
	
	public static AbstractItemType MUSHROOM = new AbstractItemType(500,
			"一把",
			true,
			"发光蘑菇",
			"发光蘑菇",
			"像这样的发光蘑菇通常生长在蝙蝠洞窟中。"
					+ "那些以蝙蝠洞穴为家的史莱姆通常以这些蘑菇为食，这也是它们的身体会发光的原因。",
			"mushrooms",
			PresetColour.BASE_BLUE_LIGHT,
			null,
			null,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MUSHROOMS)),
			Util.newArrayListOfValues(
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.CONTRABAND_LIGHT)) {
		@Override
		public boolean isTransformative() {
			return true;
		}
		@Override
		public String getUseName() {
			return "吃";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你拿出一小把发光蘑菇放进嘴里，咀嚼几下便咽了下去，忽然发现有一种清新的薄荷味。",
					"你拿出一小把发光蘑菇放进[npc.namePos]嘴里，等到[npc.she]咀嚼几下咽到肚子里才算完。",
					"[npc.Name]拿出一小把发光蘑菇放进嘴里，咀嚼几下便咽了下去。",
					"[npc.Name]拿出一小把发光蘑菇放进你的嘴里，等到你咀嚼几下咽到肚子里才算完，你忽然发现有一种清新的薄荷味。");
		}
	};
	
	private static String getEssenceAbsorptionText(Colour essenceColour, GameCharacter user, GameCharacter target) {
			if (user!=null && user.isPlayer()) {
				if(target!=null) {
					if(target.isPlayer()) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essenceBottledDiscovered)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.essenceBottledDiscovered);

							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
								return "<p>"
											+ "你把瓶口的软木塞拔了出来，只见一道旋转的光彩顿时从玻璃牢笼中冲出，让你不禁倒吸一口气。"
											+ "你还没来得及反应，精华便直直向你冲过来，随着一阵"+essenceColour.getName()+"的闪光，它便击中了你的胸膛，从视线中消失了。"
											+ "你觉得最好去问问莉莱雅刚才发生的事……"
										+ "</p>"
										+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"");
							} else {
								return "<p>"
										+ "你把瓶口的软木塞拔了出来，只见一道旋转的光彩顿时从玻璃牢笼中冲出，让你不禁倒吸一口气。"
										+ "你还没来得及反应，精华便直直向你冲过来，随着一阵"+essenceColour.getName()+"的闪光，它便击中了你的胸膛，从视线中消失了。"
										+ "你忽然记起莉莱雅告诉你有关吸收精华的事情，这才长长的吁了一口气……"
									+ "</p>";
							}
						}

						if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
							return "<p>"
										+ "你把瓶口的软木塞拔了出来，将奥术精华从玻璃牢笼中释放了出来。"
										+ "那精华向着你强大的奥术灵气，直直地冲了过来，随着一阵"+essenceColour.getName()+"的闪光，它便从视线中消失了。"
										+ "你觉得最好去问问莉莱雅刚才发生的事……"
									+ "</p>";
						} else {
							return "<p>"
									+ "你把瓶口的软木塞拔了出来，将奥术精华从玻璃牢笼中释放了出来。"
									+ "那精华向着你强大的奥术灵气，直直地冲了过来，随着一阵"+essenceColour.getName()+"的闪光，它便从视线中消失了。"
									+ "你感觉到灵气发生了一些微小的变化，也就说明你成功将精华吸收了。"
								+ "</p>";
						}
						
					} else {
						return UtilText.parse(target,
								"<p>"
									+ "你把瓶口的软木塞拔了出来，将奥术精华从玻璃牢笼中释放了出来。"
									+ "那精华向着[npc.namePos]强大的奥术灵气，直直地冲了过去，随着一阵"
										+essenceColour.getName()+"的闪光，它便从视线中消失，被吸收进入[npc.her]的灵气了。"
								+ "</p>");
					}
				} else {
					return "";
				}
				
			} else {
				if(target!=null) {
					if(target.isPlayer()) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essenceBottledDiscovered)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.essenceBottledDiscovered);

							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
								return UtilText.parse(user,
										"<p>"
											+ "[npc.Name]把瓶口的软木塞拔了出来，将奥术精华从玻璃牢笼中释放了出来。"
											+ "你还没来得及反应，精华便直直向你冲过来，随着一阵"+essenceColour.getName()+"的闪光，它便击中了你的胸膛，从视线中消失了。"
											+ "你觉得最好去问问莉莱雅刚才发生的事……"
										+ "</p>"
										+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):""));
							} else {
								return UtilText.parse(user,
										"<p>"
											+ "[npc.Name]把瓶口的软木塞拔了出来，将奥术精华从玻璃牢笼中释放了出来。"
											+ "你还没来得及反应，精华便直直向你冲过来，随着一阵"+essenceColour.getName()+"的闪光，它便击中了你的胸膛，从视线中消失了。"
											+ "你忽然记起莉莱雅告诉你有关吸收精华的事情，这才长长的吁了一口气……"
										+ "</p>");
							}
						}

						if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
							return UtilText.parse(user,
									"<p>"
										+ "[npc.Name]把瓶口的软木塞拔了出来，将奥术精华从玻璃牢笼中释放了出来。"
										+ "那精华向着你强大的奥术灵气，直直地冲了过来，随着一阵"+essenceColour.getName()+"的闪光，它便从视线中消失了。"
										+ "你觉得最好去问问莉莱雅刚才发生的事……"
									+ "</p>");
						} else {
							return UtilText.parse(user,
									"<p>"
										+ "[npc.Name]把瓶口的软木塞拔了出来，将奥术精华从玻璃牢笼中释放了出来。"
									+ "那精华向着你强大的奥术灵气，直直地冲了过来，随着一阵"+essenceColour.getName()+"的闪光，它便从视线中消失了。"
									+ "你感觉到灵气发生了一些微小的变化，也就说明你成功将精华吸收了。"
								+ "</p>");
						}
						
					} else {
						return UtilText.parse(user, 
								"<p>"
									+ "[npc.Name]把瓶口的软木塞拔了出来，将奥术精华从玻璃牢笼中释放了出来。"
									+ "那精华向着[npc.her]强大的奥术灵气，直直地冲了过去，随着一阵"
										+essenceColour.getName()+"的闪光，它便从视线中消失，被吸收进入[npc.her]的灵气了。"
								+"</p>");
					}
				} else {
					return "";
				}
			}
		
		
	}
	
	public static AbstractItemType BOTTLED_ESSENCE_ARCANE = new AbstractItemType(
			40,
			null,
			false,
			"瓶装奥术精华",
			"瓶装奥术精华",
			"一个小玻璃瓶，瓶口紧紧的塞着一个小软木塞。"
					+ "里面"+PresetColour.GENERIC_ARCANE.getName()+"光芒的奥术精华舞动闪烁着，不断飞旋，令人着迷。",
			null,
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_ARCANE)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {
		@Override
		public String getUseName() {
			return "吸收";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(PresetColour.GENERIC_ARCANE, user, target);
		}
		@Override
		public String getSVGString() {
			return getEssenceSvg(Subspecies.LILIN);
		}
	};
	

	// Specials:
	
	public static AbstractItemType HARPY_MATRIARCH_BIMBO_LOLLIPOP = new AbstractItemType(1250,
			null,
			false,
			"[bimboHarpy.namePos]的棒棒糖",
			"[bimboHarpy.namePos]的棒棒糖",
			"从哈比族长[bimboHarpy.name]那里拿到的风车型棒棒糖。"
				+ "尽管看上去平平无奇，但你确信一旦吃下去肯定会发生转化……",
			"bimboLollipop",
			PresetColour.RARITY_LEGENDARY,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BIMBO_LOLLIPOP)),
			Util.newArrayListOfValues(
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.NOT_FOR_SALE,
					ItemTag.FOOD)) {

		
		@Override
		public boolean isTransformative() {
			return true;
		}

		@Override
		public String getUseName() {
			return "舔";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你把棒棒糖放到[pc.lips+]边，伸出[pc.tongue]在上面留下了一道绵长的口水痕。"
						+ "一股强烈的甜味充斥了你的口腔，跟你之前尝过的味道完全不同。"
						+ "在你还没有反应过来的时候，你就把[pc.lips]吸在了这美味的糖果上，发出着小声的呜咽，你似乎阻止不了自己舔这根棒棒糖了……",
					"[npc.name]把棒棒糖放到[npc.lips+]边，伸出[npc.tongue]在上面留下了一道绵长的口水痕。"
						+ "那令人上瘾的味道迅速冲垮了[npc.her]的感官，[npc.she]急切地将[npc.lips]吸在这美味的糖果上，"
						+ "发出着小声的呜咽，[npc.she]似乎阻止不了自己舔这根棒棒糖了……",
					"[npc.Name]拿出了一根风车型的棒棒糖，麻利地打开包装，开始舔起来……",
					"[npc.Name]拿出了一根风车型的棒棒糖，麻利地打开包装，硬塞进了你的嘴里。"
						+ "一股强烈的甜味冲击着你的舌头，跟你之前尝过的味道完全不同。"
						+ "在你还没有反应过来的时候，你就把[pc.lips]吸在了这美味的糖果上，发出着小声的呜咽，你似乎阻止不了自己舔这根棒棒糖了……");

		}
	};
	
	public static AbstractItemType HARPY_MATRIARCH_NYMPHO_LOLLIPOP = new AbstractItemType(1250,
			null,
			false,
			"[nymphoHarpy.namePos]的棒棒糖",
			"[nymphoHarpy.namePos]的棒棒糖",
			"从哈比族长[nymphoHarpy.name]那里拿到的屌状棒棒糖。"
				+ "尽管看上去就是普通的糖果制成，但你确信一旦吃下去肯定会发生转化……",
			"nymphoLollipop",
			PresetColour.RARITY_LEGENDARY,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.NYMPHO_LOLLIPOP)),
			Util.newArrayListOfValues(
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.NOT_FOR_SALE,
					ItemTag.FOOD)) {

		
		@Override
		public boolean isTransformative() {
			return true;
		}

		@Override
		public String getUseName() {
			return "舔";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你把棒棒糖放到[pc.lips+]边，伸出[pc.tongue]在上面留下了一道绵长的口水痕。"
						+ "一股强烈的甜味充斥了你的口腔，跟你之前尝过的味道完全不同。"
						+ "在你还没有反应过来的时候，你就开始把这美味的屌状糖果在嘴里进进出出，发出着淫荡的呻吟，你似乎阻止不了自己舔这根棒棒糖了……",
					"[npc.name]把棒棒糖放到[npc.lips+]边，伸出[npc.tongue]在上面留下了一道绵长的口水痕。"
						+ "那令人上瘾的味道迅速冲垮了[npc.her]的感官，[npc.she]急切地用[npc.lips]包裹住了这美味的屌状糖果，"
							+ "发出着淫荡的呻吟，[npc.she]似乎阻止不了自己舔这根棒棒糖了……",
					"[npc.Name]拿出了一根屌状的棒棒糖，麻利地打开包装，开始舔起来……",
					"[npc.Name]拿出了一根屌状的棒棒糖，麻利地打开包装，硬塞进了你的嘴里。"
							+ "一股强烈的甜味充斥了你的口腔，跟你之前尝过的味道完全不同。"
							+ "在你还没有反应过来的时候，你就开始把这美味的屌状糖果在嘴里进进出出，发出着淫荡的呻吟，你似乎阻止不了自己舔这根棒棒糖了……");
		}
	};
	
	public static AbstractItemType HARPY_MATRIARCH_DOMINANT_PERFUME = new AbstractItemType(1250,
			null,
			false,
			"[dominantHarpy.namePos]的香水",
			"[dominantHarpy.namePos]的香水",
			"从哈比族长[dominantHarpy.name]那里拿到的一瓶香水。"
				+ "尽管看上去就是普通的香水，但你确信一旦喷在身上肯定会发生转化……",
			"dominantPerfume",
			PresetColour.RARITY_LEGENDARY,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.DOMINANT_PERFUME)),
			Util.newArrayListOfValues(
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.NOT_FOR_SALE)) {

		
		@Override
		public boolean isTransformative() {
			return true;
		}
		
		@Override
		public String getUseName() {
			return "喷";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你把香水瓶放在脖子旁，轻轻一喷。"
							+ "尽管只释放出极少量的液体，但却瞬间用光了一整瓶，只剩下一个空壳子。"
							+ "你惊讶地低头一看，一阵浓烈的女性香气却直接冲破了你的感官，"
							+ "香水中强力的附魔似乎开始发挥作用，你意识到自己下意识渴望地呻吟起来……",
					"你把香水瓶放在[npc.namePos]脖子旁，轻轻一喷。"
							+ "尽管只释放出极少量的液体，但却瞬间用光了一整瓶，只剩下一个空壳子。"
							+ "你惊讶地低头一看，一阵浓烈的女性香气却直接冲破了[npc.namePos]的感官，"
							+ "香水中强力的附魔似乎开始发挥作用，[npc.she]意识到自己下意识渴望地呻吟起来……",
					"[npc.Name]拿出了一瓶香水，麻利地掀开盖子，喷在了[npc.her]的脖子上……",
					"[npc.Name]拿出了一瓶香水，麻利地掀开盖子，喷在了你的脖子上……"
						+ "尽管只释放出极少量的液体，但却瞬间用光了一整瓶，只剩下一个空壳子。"
						+ "你惊讶地低头一看，一阵浓烈的女性香气却直接冲破了你的感官，"
							+ "香水中强力的附魔似乎开始发挥作用，你意识到自己下意识渴望地呻吟起来……");
			
		}
	};
	
	
	// Crafting outputs:
	
	public static AbstractItemType POTION = new AbstractItemType(500,
			"",
			false,
			"药水",
			"药水",
			"通过向消耗品中注入奥术精华而创造出的药水能够拥有强大的恢复效果或属性提升。"
					+ "由于药水的制作仅限于那些精通奥术的人，比如恶魔。所以它们相当罕见，价格也很高。",
			"refined_potion_container",
			PresetColour.CLOTHING_PINK,
			null,
			null,
			Rarity.RARE,
			null,
			null) {
		@Override
		public boolean isTransformative() {
			return false;
		}
		@Override
		public String getUseName() {
			return "喝";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你先拔出了瓶塞，然后迫不及待地送到了嘴边。"
						+ "一股香甜味从瓶口涌出，你连忙吞下了这美味的液体，随后便感到一阵刺痛传遍了你的身体，药水似乎开始起效了……",
					"你先拔出了瓶塞，然后送到了[npc.namePos]的嘴边，给[npc.herHim]全部灌了下去。",
					"[npc.Name]拿出了一瓶某种液体，迅速拔掉了瓶塞，将整瓶一饮而尽。",
					"[npc.Name]将药水送到你的嘴边，稍微仰起你的脑袋，强行让你吞下了里面的液体。"
						+ "你感到一股奇特的刺痛感传遍了全身，药水似乎开始起效了……");
		}
	};
	
	public static AbstractItemType ELIXIR = new AbstractItemType(750,
			"",
			false,
			"灵药",
			"灵药",
			"通过将奥术精华注入消耗品后获得，这样的灵药可以拥有大量的转化效果。"
					+ "只有奥术造诣极高的人——例如恶魔——才能酿造灵药，灵药十分稀有，价格也不俗。",
			"refined_elixir_container",
			PresetColour.CLOTHING_PINK,
			null,
			null,
			Rarity.EPIC,
			null,
			null) {
		@Override
		public boolean isTransformative() {
			return true;
		}
		@Override
		public String getUseName() {
			return "喝";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你先拔出了瓶塞，然后迫不及待地将灵药送到了嘴边。"
						+ "一股香甜味从瓶口涌出，你连忙吞下了这美味的液体，随后便感到一阵刺痛传遍了你的身体，灵药似乎开始起效了……",
					"你先拔出了瓶塞，然后将灵药送到了[npc.namePos]的嘴边，给[npc.herHim]全部灌了下去。",
					"[npc.Name]拿出了一瓶某种灵药，迅速拔掉了瓶塞，将整瓶一饮而尽。",
					"[npc.Name]将灵药送到你的嘴边，稍微仰起你的脑袋，强行让你吞下了里面的液体。"
						+ "你感到一股奇特的刺痛感传遍了全身，灵药似乎开始起效了……");
		}
	};
	
	
	// Non-TF:

	public static AbstractItemType DYE_BRUSH = new AbstractItemType(150,
			"一把",
			false,
			"染色刷",
			"染色刷",
			"一把看上去极其不同的小刷子，看样子就是在画布或雕塑上描画细节的。"
					+ "凑近仔细观察后，你注意到刷子的顶端散发出极其微弱的紫色光芒，表明其确实是一把奥术附魔的染色刷。",
			"dyeBrush",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.DYE_BRUSH)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN)) {
		@Override
		public String getUseName() {
			return "使用";
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>"
						+ "当你握住染色刷时，你看到刷头发出一阵紫色的强光。"
						+ "当其越靠近那件衣物，光芒就越明亮，最后一瞬间，五颜六色的画面开始在你的脑中闪烁。"
						+ "用毛刷触碰到衣物表面时，染色刷瞬间汽化了！"
						+ "你看得出奥术附魔已经将衣物染成你想要的颜色了。"
					+ "</p>";
		}

		@Override
		public boolean isAbleToBeUsedFromInventory() {
			return false;
		}
	};
	
	public static AbstractItemType REFORGE_HAMMER = new AbstractItemType(150,
			"一把",
			false,
			"重铸锤",
			"重铸锤",
			"一把小锤子，有坚固的金属头和木制的锤柄。"
					+ "它被注入了一种独特的奥术魔法，不仅轻如鸿毛，还能瞬间重铸任何武器。",
			"reforge_hammer",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.REFORGE_HAMMER)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN)) {
		@Override
		public String getUseName() {
			return "使用";
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>"
						+ "当你握住重铸锤时，你看到金属头发出一阵暗紫色的光芒。"
						+ "当其越靠近你的武器，光芒就越明亮，最后一瞬间，各类伤害类型的画面开始在你的脑中闪烁。"
						+ "用金属头触碰到武器表面时，重铸锤瞬间汽化了！"
						+ "你注意到奥术附魔已经重铸了这把武器，成为了你想要伤害类型。"
					+ "</p>";
		}

		@Override
		public boolean isAbleToBeUsedFromInventory() {
			return false;
		}
	};
	
	public static AbstractItemType CONDOM_USED_WEBBING = new AbstractItemType(1,
			"一个",
			false,
			"用过的避孕网",
			"用过的避孕网",
			"一个由蛛网制成的避孕套样的物体，已经使用过，里面装满了某人的精液，口部系了起来。虽然绝大多数人都只会单纯将其丢掉，但那些心灵肮脏的家伙总会另有他用……",
			"condomUsedWebbing",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.USED_CONDOM_DRINK)),
			Util.newArrayListOfValues(
					ItemTag.REMOVE_FROM_DEBUG_SPAWNER)) {
		@Override
		public String getUseName() {
			return CONDOM_USED.getUseName();
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return CONDOM_USED.getUseDescription(user, target);
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			return CONDOM_USED.getUnableToBeUsedDescription(user, target);
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}
//		@Override
//		public boolean isAbleToBeUsedWhileTrading() {
//			return false;
//		}
	};

	public static AbstractItemType CONDOM_USED = new AbstractItemType(1,
			"一个",
			false,
			"用过的避孕套",
			"用过的避孕套",
			"一个使用过的避孕套样的物体，里面装满了某人的精液，口部系了起来。虽然绝大多数人都只会单纯将其丢掉，但那些心灵肮脏的家伙总会另有他用……",
			"condomUsed",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.USED_CONDOM_DRINK)),
			Util.newArrayListOfValues(
					ItemTag.REMOVE_FROM_DEBUG_SPAWNER)) {
		@Override
		public String getUseName() {
			return "打开";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if(user==target) {
				return UtilText.parse(user, target, "解开用过的避孕套，[npc.name]想知道如何最好地使用里面的东西。");
			} else {
				return UtilText.parse(user, target, "解开用过的避孕套，[npc.name]想知道如何在[npc2.name]身上最好地使用里面的东西。");
			}
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			return "你想不到这东西还有什么用法。或许还是赶紧扔了吧……<br/>"
					+ "(你的堕落度至少需要达到<b style='color:"+CorruptionLevel.THREE_DIRTY.getColour().toWebHexString()+";'>"+CorruptionLevel.THREE_DIRTY.getName()+"</b>才能得知该物品的用法！)";
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}
//		@Override
//		public boolean isAbleToBeUsedWhileTrading() {
//			return false;
//		}
	};
	
	public static AbstractItemType ORIENTATION_HYPNO_WATCH = new AbstractItemType(50000,
			"一个",
			false,
			"催眠怀表",
			"催眠怀表",
			"一个十分特殊又极其强大的奥术装置。附魔之后，这块催眠怀表就能够修改他人的性取向，只需提升一点对象的堕落度……",
			"hypnoClockBase",
			PresetColour.ANDROGYNOUS,
			null,
			null,
			Rarity.QUEST,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.ORIENTATION_CHANGE)),
			null) {

		
		@Override
		public boolean isFetishGiving() {
			return true;
		}
		
		@Override
		public String getDeterminer() {
			return UtilText.generateSingularDeterminer(this.getName(false));
		}
		
		@Override
		public int getEnchantmentLimit() {
			return 1;
		}
		
		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ORIENTATION_CHANGE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ORIENTATION_HYPNO_WATCH;
		}
		
		@Override
		public String getUseName() {
			return "催眠";
		}
		
		@Override
		public String getUseTooltipDescription(GameCharacter user, GameCharacter target) {
			if(user.equals(target)) {
				return "使用" + getName(false) + "催眠自己。";
			} else {
				return UtilText.parse(target, "使用" + getName(false) + "催眠[npc.name]。");
			}
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你捏住精致的表链，轻轻地将催眠怀表左右晃动起来，同时将自己的目光集中在转动的表盘上，从而让物品中的奥术之力渗入思想中……",
					"你捏住精致的表链，轻轻地将催眠怀表在[npc.namePos]的面前左右晃动起来，"
							+ "如你所料，[npc.she]将目光集中在了转动的表盘上，物品中的奥术之力从而得以渗入[npc.her]的思想中…… ",
					"[npc.Name]捏住精致的表链，轻轻地将催眠怀表左右晃动起来，同时将自己的目光集中在转动的表盘上，从而让物品中的奥术之力渗入思想中……",
					"[npc.Name]捏住精致的表链，轻轻地将催眠怀表在你的面前左右晃动起来，"
							+ "你意识到自己难以自控，将目光集中在了旋涡状的表盘上，物品中的奥术之力从而得以渗入你的思想中…… ");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
//	public static AbstractItemType VIXENS_VIRILITY = new AbstractItemType(20,
//			"a",
//			false,
//			"breeder pill",
//			"breeder pills",
//			"A small, purple pill, individually packaged in a foil and plastic wrapper."
//				+ " While the text printed on the foil identifies this pill as an 'Orally-Administered Reproduction Enhancer', it's colloquially known as a 'breeder pill', and temporarily boosts both fertility and virility when ingested.",
//			"pill",
//			PresetColour.CLOTHING_PINK,
//			null,
//			null,
//			Rarity.COMMON,
//			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.VIXENS_VIRILITY)),
//			Util.newArrayListOfValues(
//					ItemTag.DOMINION_ALLEYWAY_SPAWN,
//					ItemTag.SUBMISSION_TUNNEL_SPAWN,
//					ItemTag.BAT_CAVERNS_SPAWN,
//					ItemTag.ATTRIBUTE_TF_ITEM,
//					ItemTag.SOLD_BY_RALPH)) {
//		@Override
//		public String getUseName() {
//			return "swallow";
//		}
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return getGenericUseDescription(user, target,
//					"Popping the little purple pill out of its foil wrapper, you quickly put it in your mouth and swallow it down.",
//					"Popping the little purple pill out of its foil wrapper, you bring it up to [npc.namePos] [npc.lips], before forcing it into [npc.her] mouth and making sure that [npc.she] swallows it down.",
//					"[npc.Name] pops a breeder pill out of its little foil wrapper, before quickly placing it in [npc.her] mouth and swallowing it down.",
//					"[npc.Name] pops a breeder pill out of its little foil wrapper, before bringing it up to your [pc.lips], forcing it into your mouth, and making sure that you swallow it down.");
//		}
//	};
//	
//	public static AbstractItemType PROMISCUITY_PILL = new AbstractItemType(20,
//			"a",
//			false,
//			"sterility pill",
//			"sterility pills",
//			"A small, blue pill, individually packaged in a foil and plastic wrapper."
//				+ " While the text printed on the foil identifies this pill as an 'Orally-Administered Reproduction Inhibitor',"
//					+ " it's colloquially known as either a 'sterility pill' or 'slut pill', and temporarily reduces both fertility and virility when ingested.",
//			"pill",
//			PresetColour.CLOTHING_BLUE,
//			null,
//			null,
//			Rarity.COMMON,
//			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.PROMISCUITY_PILL)),
//			Util.newArrayListOfValues(
//					ItemTag.DOMINION_ALLEYWAY_SPAWN,
//					ItemTag.SUBMISSION_TUNNEL_SPAWN,
//					ItemTag.BAT_CAVERNS_SPAWN,
//					ItemTag.ATTRIBUTE_TF_ITEM,
//					ItemTag.SOLD_BY_RALPH)) {
//		@Override
//		public String getUseName() {
//			return "swallow";
//		}
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return getGenericUseDescription(user, target,
//					"Popping the little blue pill out of its foil wrapper, you quickly put it in your mouth and swallow it down.",
//					"Popping the little blue pill out of its foil wrapper, you bring it up to [npc.namePos] [npc.lips], before forcing it into [npc.her] mouth and making sure that [npc.she] swallows it down.",
//					"[npc.Name] pops a sterility pill out of its little foil wrapper, before quickly placing it in [npc.her] mouth and swallowing it down.",
//					"[npc.Name] pops a sterility pill out of its little foil wrapper, before bringing it up to your [pc.lips], forcing it into your mouth, and making sure that you swallow it down.");
//		}
//	};
	
	public static AbstractItemType MOO_MILKER_EMPTY = new AbstractItemType(50,
			"一个",
			false,
			"哞哞挤奶器",
			"哞哞挤奶器",
			"一个手动的挤奶器，有奶牛风格的装饰，最多能够在相连接的塑料瓶中储存"+Units.fluid(1000)+"的液体。",
			"breastPump",
			PresetColour.BASE_PURPLE_LIGHT,
			null,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MOO_MILKER)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.SOLD_BY_RALPH)) {

		
		@Override
		public String getUseName() {
			return "挤奶";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你将哞哞挤奶器拿到自己的胸旁，把吸力罩安装在[pc.nipple(true)]上，然后开始按动上面的把手。"
							+ "你[pc.milk+]渐渐地被泵入连接的瓶中，给自己挤奶的感觉令你按捺不住，发出一声满足的长叹。",
					"你将哞哞挤奶器拿到[npc.namePos]的胸旁，把吸力罩安装在[pc.nipple(true)]上，然后开始按动上面的把手。"
							+ "[npc.her][npc.milk+]渐渐地被泵入连接的瓶中，被挤奶的感觉令[npc.she]按捺不住，发出一声满足的长叹。",
					"[npc.Name]将哞哞挤奶器拿到自己的胸旁，把吸力罩安装在[npc.nipple(true)]上，然后开始按动上面的把手。"
							+ "[npc.her][npc.milk+]渐渐地被泵入连接的瓶中，给自己挤奶的感觉令[npc.she]按捺不住，发出一声满足的长叹。",
					"[npc.Name]将哞哞挤奶器拿到你的胸旁，把吸力罩安装在[pc.nipple(true)]上，然后开始按动上面的把手。"
							+ "你[pc.milk+]渐渐地被泵入连接的瓶中，被挤奶的感觉令你按捺不住，发出一声满足的长叹。");
		}

		@Override
		public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
			return target.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true) && target.getBreastRawMilkStorageValue()>=5;
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			if(target.isPlayer()) {
				if(!target.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
					return "要使用该物品必须先能够接触到你的乳头！";
				} else {
					return "要使用该物品，你的乳房中至少需要储存着"+Units.fluid(5)+"乳汁！";
				}
				
			} else {
				if(!target.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
					return UtilText.parse(target, "要使用该物品必须先能够接触到[npc.namePos]头！");
				} else {
					return UtilText.parse(target, "要使用该物品，[npc.her]的乳房中至少需要储存着"+Units.fluid(5)+"乳汁！");
				}
			}
		}
	};
	
	public static AbstractItemType MOO_MILKER_FULL = new AbstractItemType(150,
			"一块",
			false,
			"装满的挤奶器",
			"装满的挤奶器",
			"一个手动的挤奶器，有奶牛风格的装饰。"
					+ "连接在其上的塑料瓶已经装满了乳汁，只要拧开顶端的抽取装置，就能够随时享用到其中的液体。",
			"breastPumpFilled",
			PresetColour.BASE_PURPLE_LIGHT,
			null,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.FILLED_MOO_MILKER_DRINK)),
			Util.newArrayListOfValues(
					ItemTag.REMOVE_FROM_DEBUG_SPAWNER)) {
		

		@Override
		public String getUseName() {
			return "喝";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你拧开了抽取头，将瓶口送到唇边，喝下了其中的液体。",
					"你拧开了抽取头，将瓶口送到[npc.namePos][npc.lips]边，强行让[npc.herHim]喝下了其中的液体。",
					"[npc.Name]拧开了抽取头，将瓶口送到[npc.lips]边，喝下了其中的液体。",
					"[npc.Name]拧开了抽取头，将瓶口送到你[pc.lips]边，强行让你喝下了其中的液体。");
		}
	};
	
	public static AbstractItemType PREGNANCY_TEST = new AbstractItemType(100,
			"一根",
			false,
			"奥术验孕棒",
			"奥术验孕棒",
			"一根小塑料棒，长不过"+Units.size(15)+"，中间嵌入着一块数字显示屏。"
					+ "附带的小说明书上写到：“将检验棒在对象腹部扫过，就能判定父亲的身份！”",
			"pregnancy_test",
			PresetColour.CLOTHING_WHITE,
			PresetColour.GENERIC_ARCANE,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.PREGNANCY_TEST)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public String getUseName() {
			return "使用";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你将验孕棒从肚子前扫过，听见滴的一声后便拿了起来检查读数。",
					"你将验孕棒从[npc.namePos]的肚子前扫过，听见滴的一声后便拿了起来检查读数。",
					"[npc.Name]将验孕棒从[npc.her]的肚子前扫过，听见滴的一声后便拿了起来检查读数。",
					"[npc.Name]验孕棒从你的肚子前扫过，听见滴的一声后便拿了起来检查读数。");
		}
	};
	
	public static AbstractItemType MOTHERS_MILK = new AbstractItemType(100,
			"一瓶",
			false,
			"母亲的奶水",
			"母亲的奶水",
			"装有醇厚液体的婴儿奶瓶。"
					+ "在瓶身一边贴着一个标签，称该饮品能够快速缩短孕期和孵卵期。",
			"mothers_milk",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MOTHERS_MILK)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH,
					ItemTag.DRINK)) {


		@Override
		public String getUseName() {
			return "喝";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你将瓶子送到[pc.lips]边，含住奶头形状的开口，贪婪地吮吸起其中浓郁的液体。",
					"你将瓶子送到[npc.namePos][npc.lips]边，把奶头形状的开口塞进了[npc.her]的嘴里，强行让[npc.herHim]吮吸起其中浓郁的液体。",
					"[npc.Name]将瓶子送到[npc.lips]边，含住奶头形状的开口，贪婪地吮吸起其中浓郁的液体。",
					"[npc.Name]将瓶子送到你[pc.lips]边，把奶头形状的开口塞进了嘴里，强行让你吮吸起其中浓郁的液体。");
		}
	};
	
	public static AbstractItemType REJUVENATION_POTION = new AbstractItemType(1_000,
			"一瓶",
			false,
			"活力药剂",
			"活力药剂",
			"一个花纹繁复的玻璃瓶，瓶塞也是青铜镶嵌的玻璃塞。"
					+ "在瓶底贴有有一张提示用的小标签，上面写着：'<i>活力药剂，能够重塑使用过度的腔穴以及恢复你所有的体液！</i>'",
			"rejuvenation_potion",
			PresetColour.CLOTHING_BLUE_LIGHT,
			PresetColour.CLOTHING_GOLD,
			PresetColour.CLOTHING_BLUE_GREY,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.REJUVENATION_POTION)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH,
					ItemTag.DRINK)) {
		@Override
		public String getUseName() {
			return "喝";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你将瓶子送到[pc.lips]边，含住奶头形状的开口，贪婪地吮吸起其中浓郁的液体。",
					"你将瓶子送到[npc.namePos][npc.lips]边，把奶头形状的开口塞进了[npc.her]的嘴里，强行让[npc.herHim]吮吸起其中浓郁的液体。",
					"[npc.Name]将瓶子送到[npc.lips]边，含住奶头形状的开口，贪婪地吮吸起其中浓郁的液体。",
					"[npc.Name]将瓶子送到你[pc.lips]边，把奶头形状的开口塞进了嘴里，强行让你吮吸起其中浓郁的液体。");
		}
	};
	
	public static AbstractItemType CIGARETTE_PACK = new AbstractItemType(350,
			"一包",
			true,
			"斯塔香烟",
			"斯塔香烟",
			"一包还没开过的纸壳包装的烟盒，紫白配色，内涵二十根“斯塔香烟”。"
					+ "根据盒子背面打印的信息，这些香烟不仅“添加了重振灵气的补剂”还“保证你回头率拉满”。",
			"cigaretteBox",
			PresetColour.CLOTHING_PURPLE_DARK,
			PresetColour.CLOTHING_GOLD,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.CIGARETTE_PACK)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH)) {

		@Override
		public String getUseName() {
			return "打开";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"打开上方的包装后，你便能够取出其中的二十根香烟了。",
					"打开上方的包装后，你便能够取出其中的二十根香烟给[npc.Name]了。",
					"打开上方的包装后，[npc.Name]便能够取出其中的二十根香烟了。",
					"打开上方的包装后，[npc.Name]便能够取出其中的二十根香烟给你了。");
		}
	};
	
	public static AbstractItemType CIGARETTE = new AbstractItemType(20,
			"一个",
			false,
			"斯塔香烟",
			"斯塔香烟",
			"一根卷起的纸筒，塞着海绵样的滤嘴，内含物由烘干的烟草和奥术增幅补剂混合而成。"
					+ "其上附魔有非常微弱的火系法术，放到嘴边时就能自动引火。",
			"cigarette",
			PresetColour.CLOTHING_ORANGE,
			PresetColour.CLOTHING_BRASS,
			PresetColour.CLOTHING_WHITE,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.CIGARETTE)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH)) {

		@Override
		public String getUseName() {
			return "抽";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你把香烟举到[pc.lips]边，将烟嘴塞到了嘴里，这时其上的火焰附魔便自动点燃了另一端。"
							+ "你深吸一口，吸入了点燃烟草产生的烟雾，吐出后在面前形成了一小团白色烟气。",
					"你把香烟举到[npc.namePos][npc.lips]边，将烟嘴塞到了嘴里，这时其上的火焰附魔便自动点燃了另一端。"
							+ "[npc.she]深吸一口，吸入了点燃烟草产生的烟雾，吐出后在面前形成了一小团白色烟气。",
					"[npc.namePos]把香烟举到[npc.lips]边，将烟嘴塞到了嘴里，这时其上的火焰附魔便自动点燃了另一端。"
							+ "[npc.she]深吸一口，吸入了点燃烟草产生的烟雾，吐出后在面前形成了一小团白色烟气。",
					"[npc.namePos]把香烟举到你[pc.lips]边，将烟嘴塞到了嘴里，这时其上的火焰附魔便自动点燃了另一端。"
							+ "你深吸一口，吸入了点燃烟草产生的烟雾，吐出后在面前形成了一小团白色烟气。");
		}
	};

	public static AbstractItemType MAKEUP_SET = new AbstractItemType(5000,
			"一套",
			false,
			"奥术化妆套装",
			"奥术化妆套装",
			"一套广受好评的便携化妆套装。"
				+ "其中的化妆品都拥有附魔，能够让使用者随意调整颜色。"
				+ "更神奇的是，无论如何使用，里面的化妆品都不会耗尽。",
			"makeupSet",
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_BLACK,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MAKEUP_SET)),
			Util.newArrayListOfValues(
					ItemTag.SOLD_BY_RALPH,
					ItemTag.SOLD_BY_KATE)) {
		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
		@Override
		public String getUseName() {
			return "使用";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "";
		}
		@Override
		public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
			return super.isAbleToBeUsed(user, target) && target.isAbleToWearMakeup();
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			if(!target.isAbleToWearMakeup()) {
				return UtilText.parse(target, "<i>由于[npc.namePos]的身体由"+BodyChanging.getTarget().getBodyMaterial().getName()+"构成，[npc.sheIsFull][style.colourBad(无法进行任何化妆)]！</i>");
			}
			return "该物品无法这样使用！";
		}
	};

	public static AbstractItemType DOLL_CONSOLE = new AbstractItemType(120000,
			"一个",
			false,
			"D.E.C.K.",
			"D.E.C.K.",
			"<i>“玩偶专用客制化工具(Doll's Expedient Customisation Kit)”</i>是唯一能够转化自动性爱玩偶的方式。"
				+ "将数据线插入玩偶后颈部的接口，使用者便能够调整玩偶大量的身体特性。",
			"dollConsole",
			PresetColour.CLOTHING_BLUE_LIGHT,
			PresetColour.CLOTHING_PINK_LIGHT,
			PresetColour.CLOTHING_PURPLE,
			Rarity.QUEST,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.DOLL_CONSOLE)),
			Util.newArrayListOfValues()) {
		@Override
		public boolean isAbleToBeSold() {
			return true;
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
		@Override
		public String getUseName() {
			return "使用";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "";
		}
		@Override
		public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
			return super.isAbleToBeUsed(user, target) && target.isDoll();
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			if(!target.isDoll()) {
				return UtilText.parse(target, "<i>[npc.nameIsFull]并非玩偶，D.E.C.K.没有效果！</i>");
			}
			return "该物品无法这样使用！";
		}
	};
	
	public static AbstractItemType PRESENT = new AbstractItemType(250,
			"一根",
			false,
			"圣诞节礼物",
			"圣诞节礼物",
			"一份包裹好的礼物，由御城区的某个驯鹿化形工头售卖。里面包含了他商店中的一件随机物品，同样也可以当作礼物送给后代、奴隶或者莉莱雅。",
			"present",
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			Rarity.RARE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.PRESENT)),
			null) {

		
		@Override
		public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
			return !(target.isInventoryFull() && Main.game.getPlayerCell().getInventory().isInventoryFull());
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			return "你的物品栏和地面都没有空间来容纳物品了！";
		}
		
		@Override
		public String getUseName() {
			return "打开";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你解下丝带，撕开了包装纸，打开盒子后发现里面是……",
					"你让[npc.name]解下丝带，撕开了包装纸，打开盒子后发现里面是……",
					"[npc.Name]拿出一份礼物，随后解下丝带，撕开了包装纸，打开盒子后发现里面是……",
					"[npc.Name]拿出一份礼物，随后让你解下丝带，撕开了包装纸，打开盒子后发现里面是……");
		}
	};
	
	public static AbstractItemType GIFT_ROSE_BOUQUET = new AbstractItemType(
			500,
			null,
			false,
			"玫瑰花束",
			"玫瑰花束",
			"一束各色玫瑰组成的花束，即使相隔很远也能闻到怡人的香味。"
				+ "[Ashley.speech(免得你都不知道心上人最喜欢的颜色是什么，所以里面每种颜色都有。)]",
			//				+ " If their favourite happens to be blue, tough luck; maybe you should try getting acquainted with another species of flower instead of going with what's safe.)] ",
			"giftRoseBouquet",
			PresetColour.BASE_RED,
			PresetColour.BASE_ORANGE,
			PresetColour.BASE_YELLOW,
			Rarity.UNCOMMON,
			null,
			Util.newArrayListOfValues(ItemTag.GIFT)) {


		@Override
		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP)) {
				return "一束各色玫瑰组成的花束，即使相隔很远也能闻到怡人的香味。"
						+ "[Ashley.speech(免得你都不知道心上人最喜欢的颜色是什么，所以里面每种颜色都有。)]";
			} else {
				return "一束各色玫瑰组成的花束，即使相隔很远也能闻到怡人的香味。";
			}
		}
		
		@Override
		public String getUseName() {
			return "闻";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你闻了闻玫瑰花束散发出的淡雅的香气。",
					"你让[npc.Name]闻了闻玫瑰花束散发出的淡雅的香气。",
					"[npc.Name]闻了闻玫瑰花束散发出的淡雅的香气。",
					"[npc.Name]让你闻了闻玫瑰花束散发出的淡雅的香气。");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	public static AbstractItemType GIFT_CHOCOLATES = new AbstractItemType(
			300,
			"一盒",
			true,
			"巧克力",
			"巧克力",
			"一盒各种口味的巧克力。[Ashley.speech(很普通但是很好吃。嗯，买吧，假装是给别人买的就好。)]",
			"giftChocolates",
			PresetColour.BASE_TAN,
			PresetColour.BASE_BROWN_DARK,
			PresetColour.BASE_YELLOW,
			Rarity.UNCOMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.GIFT_CHOCOLATES)),
			Util.newArrayListOfValues(
					ItemTag.GIFT,
					ItemTag.FOOD_QUALITY)) {


		@Override
		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP)) {
				return "一盒各种口味的巧克力。[Ashley.speech(很普通但是很好吃。嗯，买吧，假装是给别人买的就好。)]";
			} else {
				return "一盒各种口味的巧克力。";
			}
		}
		
		@Override
		public String getUseName() {
			return "吃";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你拿开盒盖，自己吃了起来。",
					"你拿开盒盖，喂给[npc.Name]吃了起来。",
					"[npc.Name]拿开盒盖，自己吃了起来。",
					"[npc.Name]拿开盒盖，喂给你吃了起来。");
		}
	};
	
	public static AbstractItemType GIFT_PERFUME = new AbstractItemType(
			300,
			"一瓶",
			false,
			"玫瑰香水",
			"玫瑰香水",
			"一小瓶香水。"
					+ "[Ashley.speech(大多人都很欣赏的平常味道。能让你吸引到更多人，谁喜欢浑身臭气呢！)]",
			"giftPerfume",
			PresetColour.BASE_ROSE,
			PresetColour.BASE_PURPLE_LIGHT,
			null,
			Rarity.UNCOMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.GIFT_PERFUME)),
			Util.newArrayListOfValues(ItemTag.GIFT)) {


		@Override
		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP)) {
				return "一小瓶香水。"
						+ "[Ashley.speech(大多人都很欣赏的平常味道。能让你吸引到更多人，谁喜欢浑身臭气呢！)]";
			} else {
				return "一小瓶香水。";
			}
		}
		
		@Override
		public String getUseName() {
			return "喷";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你深吸了一口玫瑰的芳香，便将“玫瑰香水”喷在了你的脖子上。",
					"你将“玫瑰香水”喷在了[npc.namePos]的脖子上。",
					"[npc.Name]拿出一瓶“玫瑰香水”，举到脖子边，立刻喷在了[npc.skin]上。",
					"[npc.Name]拿出一瓶“玫瑰香水”，举到你的脖子边，立刻喷在了[pc.skin]上。");
		}
	};
	
	public static AbstractItemType GIFT_TEDDY_BEAR = new AbstractItemType(
			600,
			null,
			false,
			"泰迪熊",
			"泰迪熊",
			"一只可爱的棕色泰迪熊，抱着一个粉色的心，上面绣着“抱抱我！”。"
				+ "[Ashley.speech(注意，这不是个活物；它其实并不渴望你的爱，也不能让你免受藏在床底下的怪物的伤害！)]",
			"giftTeddyBear",
			PresetColour.BASE_TAN,
			null,
			null,
			Rarity.UNCOMMON,
			null,
			Util.newArrayListOfValues(ItemTag.GIFT)) {


		@Override
		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP)) {
				return "一只可爱的棕色泰迪熊，抱着一个粉色的心，上面绣着“抱抱我！”。"
						+ "[Ashley.speech(注意，这不是个活物；它其实并不渴望你的爱，也不能让你免受藏在床底下的怪物的伤害！)]";
			} else {
				return "一只可爱的棕色泰迪熊，抱着一个粉色的心，上面绣着“抱抱我！”。";
			}
		}
		
		@Override
		public String getUseName() {
			return "拥抱";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你抱了抱泰迪熊，又松又软……",
					"你让[npc.name]抱了抱泰迪熊，[npc.She]很惊讶竟然能这么松软……",
					"[npc.Name]抱了抱泰迪熊，[npc.She]惊讶竟然能这么松软……",
					"[npc.Name]让你抱了抱泰迪熊，又松又软……");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	// Why did I make this?
	public static AbstractItemType EGGPLANT = new AbstractItemType(
			25,
			null,
			false,
			"茄子",
			"茄子",
			"一种漂亮的热带多年生植物，通常在温带气候下作为不耐寒或半耐寒的一年生植物栽培。眯着眼睛看有点像个屌。",
			"eggplant",
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.EGGPLANT)),
			Util.newArrayListOfValues(ItemTag.FOOD)) {

		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.EGGPLANT_POTION;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return EGGPLANT_POTION;
		}
		
		@Override
		public String getUseName() {
			return "吃";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你吃了茄子。苦涩的口感让你很失望。",
					"你让[npc.Name]吃下了茄子。苦涩的口感让你们很失望。",
					"[npc.Name]拿出一根茄子，吃了下去。苦涩的口感让你们很失望。",
					"[npc.Name]拿出一根茄子，让你吃了下去。苦涩的口感让你们很失望。");
		}
	};
	
	public static AbstractItemType EGGPLANT_POTION = new AbstractItemType(
			250,
			null,
			false,
			"茄子药剂",
			"茄子药剂",
			"一瓶由苦涩的茄子果肉制成的药剂。就跟作为其原料的果实一样，装有药水的瓶子眯着眼看也像个屌。",
			"eggplant_potion",
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(ItemTag.DRINK)) {
		@Override
		public boolean isTransformative() {
			return true;
		}
		@Override
		public String getUseName() {
			return "喝";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"你喝下了茄子药剂，没想到丰富而复杂的味道还挺好喝。",
					"你让[npc.name]喝下了茄子药剂，没想到丰富而复杂的味道还挺好喝。",
					"[npc.Name]拿出一瓶茄子药剂喝了下去，没想到丰富而复杂的味道还挺好喝。",
					"[npc.Name]拿出一瓶茄子药剂让你喝了下去，没想到丰富而复杂的味道还挺好喝。");
		}
	};

	public static AbstractItemType ARTHURS_PACKAGE = new AbstractItemType(0,
			"",
			false,
			"亚瑟的包裹",
			"亚瑟的包裹",
			"从“奥术艺术”拿回来的包裹，需要交给亚瑟。",
			"arthursPackage",
			PresetColour.ANDROGYNOUS,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {


		@Override
		public String getUseName() {
			return "检查";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"包裹很小，每边长大概"+Units.size(20)+"。外面包裹着棕色的纸板，用包装胶带密封着。",
					"包裹很小，每边长大概"+Units.size(20)+"。外面包裹着棕色的纸板，用包装胶带密封着。",
					"包裹很小，每边长大概"+Units.size(20)+"。外面包裹着棕色的纸板，用包装胶带密封着。",
					"包裹很小，每边长大概"+Units.size(20)+"。外面包裹着棕色的纸板，用包装胶带密封着。");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};


	public static AbstractItemType IMP_FORTRESS_ARCANE_KEY = new AbstractItemType(0,
			"",
			false,
			"菲尔西亚的钥匙",
			"菲尔西亚的钥匙",
			"一把奥术钥匙，从屈城区小恶魔要塞的首领菲尔西亚那里取得。与另两座要塞处获得的钥匙组合使用，就能进入中央城堡。",
			"impArcaneKey",
			PresetColour.CLOTHING_SILVER,
			PresetColour.GENERIC_ARCANE,
			null,
			Rarity.QUEST,
			null,
			null) {


		@Override
		public String getUseName() {
			return "检查";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(user, "你将钥匙在[npc.hands]中把玩，感受到了其散发出微弱的奥术涌动。");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	public static AbstractItemType IMP_FORTRESS_ARCANE_KEY_2 = new AbstractItemType(0,
			"",
			false,
			"卓特拉克斯的钥匙",
			"卓特拉克斯的钥匙",
			"一把奥术钥匙，从屈城区小恶魔要塞的首领卓特拉克斯那里取得。与另两座要塞处获得的钥匙组合使用，就能进入中央城堡。",
			"impArcaneKey2",
			PresetColour.CLOTHING_STEEL,
			PresetColour.GENERIC_ARCANE,
			null,
			Rarity.QUEST,
			null,
			null) {


		@Override
		public String getUseName() {
			return "检查";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(user, "你将钥匙在[npc.hands]中把玩，感受到了其散发出微弱的奥术涌动。");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	public static AbstractItemType IMP_FORTRESS_ARCANE_KEY_3 = new AbstractItemType(0,
			"",
			false,
			"希尔莉丝的钥匙",
			"希尔莉丝的钥匙",
			"一把奥术钥匙，从屈城区小恶魔要塞的首领希尔莉丝那里取得。与另两座要塞处获得的钥匙组合使用，就能进入中央城堡。",
			"impArcaneKey3",
			PresetColour.CLOTHING_GOLD,
			PresetColour.GENERIC_ARCANE,
			null,
			Rarity.QUEST,
			null,
			null) {


		@Override
		public String getUseName() {
			return "检查";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(user, "你将钥匙在[npc.hands]中把玩，感受到了其散发出微弱的奥术涌动。");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType LYSSIETHS_RING = new AbstractItemType(0,
			"",
			false,
			"莉西丝的陷阱图章戒指",
			"莉西丝的陷阱图章戒指",
			"戒指由玫瑰金精铸而成，镶嵌着珍贵的珠宝，且其上有附魔，只要戴上就会被奴役。"
					+ "如果你能骗“暗夜塞壬”戴上，兵不血刃便赢得莉西丝的赏识。",
			"lyssiethsRing",
			PresetColour.CLOTHING_ROSE_GOLD,
			PresetColour.CLOTHING_RED_DARK,
			PresetColour.CLOTHING_ROSE_GOLD,
			Rarity.QUEST,
			null,
			null) {


		@Override
		public String getUseName() {
			return "检查";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(user, "这枚戒指摸上去有一股暖流，意味着其带有强大的附魔。或许一切结束后，莉西丝会让你留下它作为奖励……");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	
	public static AbstractItemType OFFSPRING_MAP = new AbstractItemType(50_000,
			"一张",
			false,
			"奥术后代地图",
			"奥术后代地图",
			"一张从御城区市政厅获得的奥术附魔地图，可以追踪到你附近任何一个孩子的大致位置。",
			"offspring_map",
			PresetColour.BASE_BROWN,
			null,
			null,
			Rarity.QUEST,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.OFFSPRING_MAP)),
			null) {
		@Override
		public String getUseName() {
			return "查阅";
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
//			System.out.println("###");
//			System.out.println(target.isPlayer());
//			System.out.println(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).size()==0);
//			System.out.println(Util.newArrayListOfValues(
//					Encounter.DOMINION_ALLEY,
//					Encounter.DOMINION_CANAL,
//					Encounter.HARPY_NEST_WALKWAYS,
//					Encounter.SUBMISSION_TUNNELS,
//					Encounter.BAT_CAVERN,
//					Encounter.getEncounterFromId("innoxia_elis_alleyway")
//				).contains(target.getLocationPlace().getPlaceType().getCoreEncounterType()));
//			System.out.println(Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley"));
			
			return target.isPlayer()
					&& Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).size()==0
					&& ((Util.newArrayListOfValues(
							Encounter.DOMINION_ALLEY,
							Encounter.DOMINION_CANAL,
							Encounter.HARPY_NEST_WALKWAYS,
							Encounter.SUBMISSION_TUNNELS,
							Encounter.BAT_CAVERN,
							Encounter.getEncounterFromId("innoxia_elis_alleyway")
						).contains(target.getLocationPlace().getPlaceType().getCoreEncounterType()))
						|| Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley"));
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			return "要使用这张地图，你需要在以下几种类型的空地块上：御城区小巷、御城区运河、哈比巢穴通道、屈城区隧道、伊利斯小巷。";
		}
		@Override
		public String getUseTooltipDescription(GameCharacter user, GameCharacter target) {
			return "查阅地图以寻找该区域是否有你的后代。";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "你查阅了地图……";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	public static AbstractItemType CANDI_PERFUMES = new AbstractItemType(500,
			"",
			true,
			"坎迪的香水",
			"坎迪的香水",
			"从“魅魔的秘密”的凯特那里拿来的几瓶香水。你需要交给执法者总部的坎迪。",
			"candiPerfumes",
			PresetColour.BASE_ROSE,
			PresetColour.BASE_PURPLE_LIGHT,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "检查";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "这几瓶香水没什么特别的。还是赶紧交给坎迪吧。";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType CANDI_CONTRABAND = new AbstractItemType(1000,
			"",
			false,
			"违禁品棒棒糖",
			"违禁品棒棒糖",
			"装满了违禁品棒棒糖的箱子，从哈比之巢的执法者那里取来，坎迪对此垂涎欲滴。",
			"contrabandBox",
			PresetColour.BASE_PINK_DEEP,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "检查";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "箱子上标记了几道红叉，里面的东西显然某种程度上比较危险。"
					+ "将箱子交给你的执法者曾说里面的棒棒糖是一种永久春药，应当立即被封锁在安全存储中。";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType CANDI_HUNDRED_KISSES = new AbstractItemType(50000,
			"",
			false,
			"“百万之吻”",
			"“百万之吻”",
			"一盒限定款的口红，包含着上百种不同的颜色，由整个御城区最独特、最高档的化妆品公司生产。",
			"candiHundredKisses",
			PresetColour.BASE_PINK_DEEP,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "检查";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "随便看一眼就这华丽的盒子，就能知道里面装着的绝对是用钱能买到最上等的口红。";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType RESONANCE_STONE = new AbstractItemType(0,
			"",
			false,
			"克莱尔的回声石",
			"克莱尔的回声石",
			"一块光滑的小型球体，圆周上有一道浅浅的凹槽。"
					+ "通常禁止平民所有，但克莱尔给了你一块，方便你向鼠窟旁边的SWORD执法者发送信号。",
			"resonanceStone",
			PresetColour.CLOTHING_PURPLE_VERY_DARK,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "检查";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "这块回声石由某种精心抛光过的岩石制成，只要将上下两个半球朝不同方向扭动，就能将其启动，向连接的其他回声石发送信号。"
					+ "克莱尔告诉过你，这一块会向SWORD执法者发出通知，让他们对鼠窟发动突袭，你应当在确认文加在场时再使用。";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType PAINT_CAN_PREMIUM = new AbstractItemType(1_500,
			"一罐",
			false,
			"“紫星”金色油漆",
			"“紫星”金色油漆",
			"一罐金色油漆，带有高级的“紫星”标签，从“阿格斯的DIY仓库”买来的。"
					+ "希望海伦娜明白这可不是小价钱……",
			"paint_can",
			PresetColour.CLOTHING_GOLD,
			PresetColour.CLOTHING_PURPLE_DARK,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "检查";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "除非是真正的专业漆匠，不然没有人会相信你为了买这罐“紫星”油漆花了多少钱……";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
		@Override
		public List<AbstractCoreType> getAdditionalDiscoveryTypes() {
			return Util.newArrayListOfValues(PAINT_CAN);
		}
	};

	public static AbstractItemType PAINT_CAN = new AbstractItemType(250,
			"一罐",
			false,
			"“铜星”金色油漆",
			"“铜星”金色油漆",
			"一罐金色油漆，带有普通的“铜星”标签，从“阿格斯的DIY仓库”买来的。"
					+ "希望海伦娜不会太失望……",
			"paint_can",
			PresetColour.CLOTHING_GOLD,
			PresetColour.CLOTHING_BRONZE,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "检查";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "尽管不是海伦娜指定的高级“紫星”油漆，但你肯定这个已经足够应付了……";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
		@Override
		public List<AbstractCoreType> getAdditionalDiscoveryTypes() {
			return Util.newArrayListOfValues(PAINT_CAN_PREMIUM);
		}
	};

	public static AbstractItemType ROLLED_UP_POSTERS = new AbstractItemType(0,
			"半打",
			true,
			"卷起来的附魔海报",
			"卷起来的附魔海报",
			"半打卷起来的附魔海报，海伦娜让你张贴在奴隶巷入口边的墙上。"
					+ "每一张上都展示着附魔过的动态图像，一只风姿绰约的哈比身着暴露的比基尼，摆出诱惑的姿势。",
			"rolled_up_posters",
			PresetColour.CLOTHING_DESATURATED_BROWN,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "检查";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "尽管是用来给“海伦娜精品店”打广告用的，但在海报上却丝毫没有提及那位哈比的店铺。"
					+ "反而每一张上都展示着附魔过的动态图像，一只风姿绰约的哈比身着暴露的比基尼，摆出诱惑的姿势。"
					+ "唯一可见的文字在海报底部，只是单纯地用优美的连笔写着“海伦娜”几个字。";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

//	public static AbstractItemType BUSINESS_CARDS = new AbstractItemType(0,
//			"a",
//			false,
//			"pack of business cards",
//			"packs of business cards",
//			"A pack of Helena's business cards, given to you by Scarlett along with the instructions to hand them out at the auction block in Slaver Alley."
//					+ " The pack itself has a fancy heart-shaped cut-out in the middle of its protective sleeve.",
//			"business_card_box_1",
//			PresetColour.CLOTHING_GOLD,
//			null,
//			null,
//			Rarity.QUEST,
//			null,
//			null,
//			null) {
//		@Override
//		public String getUseName() {
//			return "inspect";
//		}
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "Carefully taking them out of their protective packaging, you see that Helena's business cards are very well designed, and clearly display both her name and the address of her store in Slaver Alley.";
//		}
//		@Override
//		public boolean isConsumedOnUse() {
//			return false;
//		}
//	};

	public static AbstractItemType NATALYA_BUSINESS_CARD = new AbstractItemType(0,
			"",
			false,
			"娜塔莉亚的名片",
			"娜塔莉亚的名片",
			"一张娜塔莉亚给你的名片，她是个征服欲很强的恶魔半人马，担任着快递公司“御城速递”的“马场主”。"
					+ "名片上印着的地址指引你前往御城区的仓库区。",
			"natalya_business_card",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "检查";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "娜塔莉亚的名片上写着她的名字和职位“马场主”。"
					+ "她从事的公司“御城速递”的地址也写得清清楚楚，正在御城区的仓库区。";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType NATALYA_BUSINESS_CARD_STAMPED = new AbstractItemType(0,
			"",
			false,
			"娜塔莉亚的名片(盖章)",
			"娜塔莉亚的名片(盖章)",
			"一张娜塔莉亚给你的名片，她是个征服欲很强的恶魔半人马，担任着快递公司“御城速递”的“马场主”。"
					+ "名片上印着的地址指引你前往御城区的仓库区。",
			"natalya_business_card_stamped",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "检查";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "娜塔莉亚的名片上写着她的名字和职位“马场主”。"
					+ "她从事的公司“御城速递”的地址也写得清清楚楚，正在御城区的仓库区。"
					+ "这张名片上已经由娜塔莉亚亲自盖章，准许你在御城速递的仓库中畅通无阻。";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType SLAVER_LICENSE = new AbstractItemType(5000,
			"",
			false,
			"贩奴许可",
			"贩奴许可",
			"一份官方文件，声明你在法律上有权拥有、购买、出售甚至捕获奴隶。"
					+ "虽然贩奴许可极难获得，但只有对其合法拥有者来说才真正有价值……",
			"slaver_license",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "检查";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "这张贩奴许可由一张高质量的厚纸印刷。"
					+ "上面很清楚地印着你的名字，还有芬奇的签名。";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	// Standard non-racial transformatives:
	
//	MASOCHISTS_HEAVEN("a bottle of", "it", "Masochist's Heaven",
//			"A clear plastic bottle of Masochist's Heaven. A girl, lying back in the missionary position, is prominently featured on the label, screaming in delight as a huge cock painfully stretches out her tight, dry pussy.",
//			"potion", PresetColour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Decreases orifice capacity, elasticity, and wetness.") {
//		
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Masochist's Heaven</i>. The drink is quite bland, but a slight"
//						+ " citrus aftertaste lingers in your mouth as you swallow the last few drops. As you lower the empty bottle, your mouth and throat suddenly feel incredibly"
//						+ " dry, as though you haven't drunk anything for hours. Before you can think about getting another drink, the feeling quickly fades away, spreading a dry warmth throughout your entire body.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Masochist's Heaven</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.MASOCHISTS_HEAVEN.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	GOING_BIG("a bottle of", "it", "Going Big",
//			"A clear plastic bottle of a drink branded as 'Going Big'. A girl, presenting herself doggy-style, is prominently featured on the label, crying out in delight as a gigantic cock easily stretches out her tight, wet pussy.",
//			"potion", PresetColour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Decreases orifice capacity. Increases elasticity and wetness.") {
//		
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Going Big</i>."
//						+ " Despite the fact that the liquid is clear, it has a very strong taste of apples, and after only a moment, you're licking the last few drops from your lips.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Going Big</i>, unscrews the cap, and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.GOING_BIG.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	WET_KISS("a bottle of", "it", "Wet Kiss",
//			"A clear plastic bottle of the branded drink <i>Wet Kiss</i>, filled with a rose-coloured liquid. The label on the front is devoid"
//					+ " of any images, and instead simply displays the name <i>Wet Kiss</i>, along with some incomprehensible technical details of the drink's manufacturing process.",
//			"potion", PresetColour.CLOTHING_PINK_LIGHT, true, 25, Rarity.RARE, "Increases orifice wetness and capacity.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Wet Kiss</i>. The drink is quite bland, but a slight"
//						+ " aftertaste of cranberries lingers in your mouth as you swallow the last few drops. Within seconds, you feel a slimy wetness squirming in your stomach,"
//						+ " but before you have any time to worry, it quickly dissipates throughout your body.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Wet Kiss</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.WET_KISS.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	
//	
//
//	BUBBLE_MILK("a bottle of", "it", "bubble-milk",
//			"A clear plastic bottle of bubble-milk. Despite its name, the milk doesn't physically bubble, but instead refers to the feeling"
//					+ " you get after drinking it. A busty greater cow-girl is prominently featured on the label, smiling as she milks her gigantic udder-tits into a metal bucket.",
//			"potion", PresetColour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Increases breast size and lactation.") {
//		
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of bubble-milk. It tastes just like regular milk, but as you"
//						+ " swallow the last few drops, a funny bubbling sensation starts to spread throughout your torso before settling in your chest.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of bubble-milk, unscrews the cap, and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.BUBBLE_MILK.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	BUBBLE_CREAM("a bottle of", "it", "bubble-cream",
//			"A clear plastic bottle of bubble-cream. Just like bubble-milk, the cream doesn't physically bubble, but instead refers"
//					+ " to the feeling you get after drinking it. A greater cow-girl with three pairs of gigantic breasts is prominently featured on the label, smiling as she" + " milks her gigantic udder-tits into a metal bucket.",
//			"potion", PresetColour.CLOTHING_WHITE, true, 100, Rarity.EPIC, "Increases breast size, count, and lactation.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of bubble-cream. Its rich taste is exactly like that of regular"
//						+ " cream, but as you swallow the last few drops, a strong bubbling sensation starts to spread throughout your torso before settling in your chest.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of bubble-cream, unscrews the cap, and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.BUBBLE_CREAM.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//
//	THROBBING_GLOW("a bottle of", "it", "Throbbing Glow",
//			"A clear plastic bottle of the energy drink <i>Throbbing Glow</i>, filled with a bright blue liquid. A"
//					+ " well-endowed greater horse-boy is prominently featured on the label, stroking his gigantic member with one hand, while bringing a bottle of <i>Throbbing Glow</i>" + " to his lips with the other.",
//			"potion", PresetColour.CLOTHING_BLUE_LIGHT, true, 25, Rarity.RARE, "Increases penis and testicle size. Increases cum production.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Throbbing Glow</i>. It tastes a little sour, sort of like"
//						+ " a cheap, sugary energy drink. As the last few drops slide down your throat, you feel a throbbing, deep-seated heat take root in your groin.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Throbbing Glow</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.THROBBING_GLOW.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//
//	FLOWERS_WARMTH("a bottle of", "it", "Flower's Warmth",
//			"A clear plastic bottle of the energy drink <i>Flower's Warmth</i>, filled with a pale pink liquid. A"
//					+ " greater cat-girl is featured prominently on the label, leaning back in a chair as another greater cat-girl laps hungrily at her exposed pussy.",
//			"potion", PresetColour.CLOTHING_PINK_LIGHT, true, 25, Rarity.RARE, "Increases the body's feminine characteristics.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Flower's Warmth</i>. It tastes a little sour, sort of like"
//						+ " a cheap, sugary energy drink. As the last few drops slide down your throat, you feel a deep-seated heat start to spread through in your groin.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Flower's Warmth</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.FLOWERS_WARMTH.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//
//	
//
//	SCARLET_WHISPER("a bottle of", "it", "Scarlet Whisper",
//			"A delicate glass bottle of <i>Scarlet Whisper</i>, filled with a bright pink liquid. The label on the front displays"
//					+ " the name <i>Scarlet Whisper</i> in a delicate, feminine font. The rest of the label is covered in simple images of pale pink flowers and looping linework.",
//			"potion", PresetColour.CLOTHING_PINK, true, 25, Rarity.RARE, "Increases all feminine aspects.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the metal cap and gulp down the bottle of <i>Scarlet Whisper</i>. The liquid has a delicate, sweet flavour,"
//						+ " which reminds you of strawberries and cream. As you finish the bottle, a wave of dizziness washes over you, filling your mind with a soft pink haze. Shaking"
//						+ " your head, the feeling somehow seems to sink down into your body, leaving you tingling all over.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Scarlet Whisper</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.SCARLET_WHISPER.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	FLAMING_THUNDER("a bottle of", "it", "Flaming Thunder",
//			"A thick glass bottle of <i>Flaming Thunder</i>, filled with a deep blue liquid. The label on the front displays"
//					+ " the name <i>Flaming Thunder</i> in a bold, striking font. The rest of the label is covered in simple images of lightning and bold linework.",
//			"potion", PresetColour.CLOTHING_BLUE, true, 25, Rarity.RARE, "Increases all masculine aspects.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the metal cap and gulp down the bottle of <i>Flaming Thunder</i>. The liquid has a strong flavour, and despite"
//						+ " its blue colouring, tastes very similar to lemonade. As you finish the bottle, a wave of dizziness washes over you, filling your mind with a strange blue haze."
//						+ " Shaking your head, the feeling somehow seems to sink down into your body, leaving you tingling all over.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Flaming Thunder</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.FLAMING_THUNDER.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//
	

	public static int getMooMilkerMaxMilk() {
		return 1000;
	}
	
	private static List<AbstractItemType> dominionAlleywayItems = new ArrayList<>();
	private static List<AbstractItemType> submissionTunnelItems = new ArrayList<>();
	private static List<AbstractItemType> batCavernItems = new ArrayList<>();
	private static List<AbstractItemType> elisAlleywayItems = new ArrayList<>();
	
	private static List<AbstractItemType> essences = new ArrayList<>();
	private static List<AbstractItemType> allItems = new ArrayList<>();
	private static List<AbstractItemType> moddedItems = new ArrayList<>();
	private static Map<AbstractSubspecies, String> subspeciesBookId = new HashMap<>();
	
	/**
	 * If you're looking for spell books, their id is:<br/>
	 * SPELL_BOOK_"+spell.toString()<br/>
	 * If you're looking for spell scrolls, their id is:<br/>
	 * "SPELL_SCROLL_"+spellSchool.toString()
	 */
	private static Map<AbstractItemType, String> itemToIdMap = new HashMap<>();

	/**
	 * If you're looking for spell books, their id is:<br/>
	 * SPELL_BOOK_"+spell.toString()<br/>
	 * If you're looking for spell scrolls, their id is:<br/>
	 * "SPELL_SCROLL_"+spellSchool.toString()
	 */
	private static Map<String, AbstractItemType> idToItemMap = new HashMap<>();
	

	public static AbstractItemType getItemTypeFromId(String id) {
		
		if(id.equalsIgnoreCase("PROMISCUITY_PILL")) {
			id = "innoxia_pills_sterility";
			
		} else if(id.equalsIgnoreCase("VIXENS_VIRILITY")) {
			id = "innoxia_pills_fertility";
		}
		
		// Attribute-related liquids were moved out into external res folder in v0.4:
		if(id.equalsIgnoreCase("STR_INGREDIENT_EQUINE_CIDER")) {
			id = "innoxia_race_horse_equine_cider";
		} else if(id.equalsIgnoreCase("STR_INGREDIENT_BUBBLE_MILK")) {
			id = "innoxia_race_cow_bubble_milk";
		} else if(id.equalsIgnoreCase("STR_INGREDIENT_WOLF_WHISKEY")) {
			id = "innoxia_race_wolf_wolf_whiskey";
		} else if(id.equalsIgnoreCase("STR_INGREDIENT_SWAMP_WATER")) {
			id = "innoxia_race_alligator_swamp_water";
		} else if(id.equalsIgnoreCase("STR_INGREDIENT_BLACK_RATS_RUM")) {
			id = "innoxia_race_rat_black_rats_rum";
		} else if(id.equalsIgnoreCase("INT_INGREDIENT_FELINE_FANCY")) {
			id = "innoxia_race_cat_felines_fancy";
		} else if(id.equalsIgnoreCase("INT_INGREDIENT_GRAPE_JUICE")) {
			id = "innoxia_race_fox_vulpines_vineyard";
		} else if(id.equalsIgnoreCase("INT_INGREDIENT_VANILLA_WATER")) {
			id = "innoxia_race_human_vanilla_water";
		} else if(id.equalsIgnoreCase("INT_INGREDIENT_FRUIT_BAT_SQUASH")) {
			id = "innoxia_race_bat_fruit_bats_juice_box";
		} else if(id.equalsIgnoreCase("FIT_INGREDIENT_CANINE_CRUSH")) {
			id = "innoxia_race_dog_canine_crush";
		} else if(id.equalsIgnoreCase("FIT_INGREDIENT_SQUIRREL_JAVA")) {
			id = "innoxia_race_squirrel_squirrel_java";
		} else if(id.equalsIgnoreCase("FIT_INGREDIENT_EGG_NOG")) {
			id = "innoxia_race_reindeer_rudolphs_egg_nog";
		} else if(id.equalsIgnoreCase("SEX_INGREDIENT_HARPY_PERFUME")) {
			id = "innoxia_race_harpy_harpy_perfume";
		} else if(id.equalsIgnoreCase("SEX_INGREDIENT_SLIME_QUENCHER")) {
			id = "innoxia_race_slime_slime_quencher";
		} else if(id.equalsIgnoreCase("SEX_INGREDIENT_BUNNY_JUICE")) {
			id = "innoxia_race_rabbit_bunny_juice";
		} else if(id.equalsIgnoreCase("SEX_INGREDIENT_MINCE_PIE")) {
			id = "innoxia_race_none_mince_pie";
		} else if(id.equalsIgnoreCase("COR_INGREDIENT_ANGELS_TEARS")) {
			id = "innoxia_race_angel_angels_tears";
		} else if(id.equalsIgnoreCase("COR_INGREDIENT_LILITHS_GIFT")) {
			id = "innoxia_race_demon_liliths_gift";
		} else if(id.equalsIgnoreCase("COR_INGREDIENT_IMPISH_BREW")) {
			id = "innoxia_race_imp_impish_brew";
		} else if(id.equalsIgnoreCase("DEBUG_YOUKO_POTION")) {
			id = "innoxia_cheat_inno_chans_gift";
		} else if(id.equalsIgnoreCase("FEMININE_BURGER")) {
			id = "innoxia_cheat_unlikely_whammer";
		}
		
		// Racial-transformative consumables were also moved into external res folder in v0.4:
		
		if(id.equalsIgnoreCase("DEBUG_DEMON_POTION")) {
			id = "innoxia_race_demon_innoxias_gift";
		} else if(id.equalsIgnoreCase("RACE_ANGELS_TEARS") || id.equalsIgnoreCase("RACE_INGREDIENT_HUMAN")) {
			id = "innoxia_race_human_bread_roll";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_CAT_MORPH")) {
			id = "innoxia_race_cat_kittys_reward";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_DOG_MORPH")) {
			id = "innoxia_race_dog_canine_crunch";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_COW_MORPH")) {
			id = "innoxia_race_cow_bubble_cream";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_SQUIRREL_MORPH")) {
			id = "innoxia_race_squirrel_round_nuts";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_BAT_MORPH")) {
			id = "innoxia_race_bat_fruit_bats_salad";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_RAT_MORPH")) {
			id = "innoxia_race_rat_brown_rats_burger";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_RABBIT_MORPH")) {
			id = "innoxia_race_rabbit_bunny_carrot_cake";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_FOX_MORPH")) {
			id = "innoxia_race_fox_chicken_pot_pie";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_HORSE_MORPH")) {
			id = "innoxia_race_horse_sugar_carrot_cube";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_REINDEER_MORPH")) {
			id = "innoxia_race_reindeer_sugar_cookie";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_ALLIGATOR_MORPH")) {
			id = "innoxia_race_alligator_gators_gumbo";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_WOLF_MORPH")) {
			id = "innoxia_race_wolf_meat_and_marrow";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_HARPY")) {
			id = "innoxia_race_harpy_bubblegum_lollipop";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_SLIME")) {
			id = "innoxia_race_slime_biojuice_canister";
		}
		
		id = Util.getClosestStringMatch(id, idToItemMap.keySet());
		return idToItemMap.get(id);
	}
	
	public static String getIdFromItemType(AbstractItemType itemType) {
		return itemToIdMap.get(itemType);
	}
	
	public static AbstractItemType getSpellBookType(Spell s) {
		return idToItemMap.get("SPELL_BOOK_"+s);
	}
	
	public static AbstractItemType getSpellScrollType(SpellSchool school) {
		return idToItemMap.get("SPELL_SCROLL_"+school);
	}
	
	public static AbstractItemType getLoreBook(AbstractSubspecies subspecies) {
		return idToItemMap.get(subspeciesBookId.get(subspecies));
	}
	
	static{
		
		// Modded item types:

		moddedItems = new ArrayList<>();

		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/items/items");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey();
					AbstractItemType ct = new AbstractItemType(innerEntry.getValue(), entry.getKey(), true) {};
					moddedItems.add(ct);
					itemToIdMap.put(ct, id);
					idToItemMap.put(id, ct);
				} catch(Exception ex) {
					System.err.println("Loading modded item failed at 'ItemType'. File path: "+innerEntry.getValue().getAbsolutePath());
										ex.printStackTrace(System.err);
				}
			}
		}
		allItems.addAll(moddedItems);
		
		// External res item types:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/items");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey();
					AbstractItemType ct = new AbstractItemType(innerEntry.getValue(), entry.getKey(), false) {};
					allItems.add(ct);
					itemToIdMap.put(ct, id);
					idToItemMap.put(id, ct);
//					System.out.println("IT: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading item failed at 'ItemType'. File path: "+innerEntry.getValue().getAbsolutePath());
										ex.printStackTrace(System.err);
				}
			}
		}
		
		for(AbstractItemType it : allItems) {
			it.getSVGString(); // Initialise all SVGStrings so that initialisation methods do not conflict with one another in other places in the code.
		}
		
		Field[] fields = ItemType.class.getFields();
		
		for(Field f : fields){
			
			if (AbstractItemType.class.isAssignableFrom(f.getType())) {
				
				AbstractItemType item;
				try {
					item = ((AbstractItemType) f.get(null));
					
					itemToIdMap.put(item, f.getName());
					idToItemMap.put(f.getName(), item);
					
					allItems.add(item);
					
					if(item.getItemTags().contains(ItemTag.ESSENCE)) {
						essences.add(item);
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		for(Spell s : Spell.values()) {
			if(!s.isSpellBook()) {
				continue;
			}
			
			List<String> effectsString = Util.newArrayListOfValues(
					"[style.boldExcellent(永久)]习得法术“<b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+"</b>”。");
			
			if(s == Spell.ELEMENTAL_EARTH) {
				effectsString.add("Adds "+Subspecies.ELEMENTAL_EARTH.getName(null)+" encyclopedia entry.");
				
			} else if(s == Spell.ELEMENTAL_WATER) {
				effectsString.add("Adds "+Subspecies.ELEMENTAL_WATER.getName(null)+" encyclopedia entry.");
				
			} else if(s == Spell.ELEMENTAL_AIR) {
				effectsString.add("Adds "+Subspecies.ELEMENTAL_AIR.getName(null)+" encyclopedia entry.");
				
			} else if(s == Spell.ELEMENTAL_FIRE) {
				effectsString.add("Adds "+Subspecies.ELEMENTAL_FIRE.getName(null)+" encyclopedia entry.");
				
			} else if(s == Spell.ELEMENTAL_ARCANE) {
				effectsString.add("Adds "+Subspecies.ELEMENTAL_ARCANE.getName(null)+" encyclopedia entry.");
			}
//			effectsString.add("[style.boldExcellent(+5 "+Attribute.DAMAGE_ELEMENTAL.getName()+")]");
			
			
			AbstractItemEffectType effectType = new AbstractItemEffectType(effectsString, s.getSpellSchool().getColour()) {
				
				@Override
				public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
					boolean hasSpell = target.hasSpell(s);
					target.addSpell(s);
					
					String raceKnowledgeGained = "";
					if(target.isPlayer()) {
						if(s == Spell.ELEMENTAL_EARTH) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_EARTH, null, true);
							
						} else if(s == Spell.ELEMENTAL_WATER) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_WATER, null, true);
							
						} else if(s == Spell.ELEMENTAL_AIR) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_AIR, null, true);
							
						} else if(s == Spell.ELEMENTAL_FIRE) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_FIRE, null, true);
							
						} else if(s == Spell.ELEMENTAL_ARCANE) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_ARCANE, null, true);
						}
					}
					
					if(hasSpell) {
						if(target.isPlayer()) {
							return "<p style='text-align:center'>"
										+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b>"+s.getDescription(target)+"</i>"
									+"</p>"
									+ "<p>"
										+"再次通读了这本法术书，你很快就意识到自己已经完全掌握了法术“"+s.getName()+"”相关的知识。"
										+ "除了几张恶魔释放该法术时的生动图像，这本书中已经没几页能引起你的注意了，"
											+ "没过几分钟你就合上了书，并没有学到什么新东西……"
									+ "</p>"
									+raceKnowledgeGained;
						} else {
							return "<p style='text-align:center'>"
										+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b>"+s.getDescription(target)+"</i>"
									+"</p>"
									+ "<p>"
										+ UtilText.parse(target,
												"再次通读了这本法术书，[npc.Name]很快就意识到自己已经完全掌握了法术“"+s.getName()+"”相关的知识。"
												+ "除了几张恶魔释放该法术时的生动图像，这本书中已经没几页能引起[npc.her]的注意了，"
													+ "没过几分钟[npc.she]就合上了书，并没有学到什么新东西……")
									+ "</p>";
						}
						
					} else {
						if(target.isPlayer()) {
							return "<p style='text-align:center'>"
										+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b>"+s.getDescription(target)+"</i>"
									+"</p>"
									+ "<p>"
										+ "你通读了一下这本书，发现大部分内容都在教读者训练奥术灵气，以达到释放该法术的最低门槛。"
										+ "鉴于你的灵气已经非常强大，这些段落对你来说毫无用处。你迅速地翻到最后几章，"
											+ "这些章节详细介绍了如何集中你的奥术灵气来施放法术“<i>"+s.getName()+"</i>”。"
										+ "你没花多长时间就大致了解了应该怎么做，在完成了书中的练习之后，你自信地认为自己可以随时施展这个法术。"
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ "你学习了<b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+"</b>法术！"
										+ "<br/><i>法术书完成了它的使命，在一道紫光中消失了！</i>"
										+ "<br/>[style.italicsExcellent(法术书已被添加到莉莱雅的图书馆中！)]"
									+ "</p>"
									+raceKnowledgeGained;
							
						} else {
							return "<p style='text-align:center'>"
									+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b>"+s.getDescription(target)+"</i>"
								+"</p>"
								+ "<p>"
									+ UtilText.parse(target,
											"[npc.Name]通读了一下这本书，发现大部分内容都在教读者训练奥术灵气，以达到释放该法术的最低门槛。"
											+ "鉴于[npc.her]的灵气已经足够强大，这些段落对于[npc.herHim]毫无用处，[npc.she]迅速地翻到最后几章。"
												+ "这些章节详细介绍了如何集中[npc.her]的奥术灵气来施放法术“<i>"+s.getName()+"</i>”。"
											+ "[npc.herHim]没花多长时间就大致了解了应该怎么做，在完成了书中的练习之后，"
												+ "[npc.herHim]自信地认为自己可以随时施展这个法术。")
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ UtilText.parse(target, "[npc.Name]习得了法术<b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+"</b>！")
									+ "<br/><i>法术书完成了它的使命，在一道紫光中消失了！</i>"
								+ "</p>";
						}
					}
				}
			};
			
			ItemEffectType.addAbstractItemEffectToIds("EFFECT_SPELL_"+s, effectType);
			
			int value = 2500;
			switch(s) {
				// Tier 1:
				case ARCANE_AROUSAL:
				case ICE_SHARD:
				case POISON_VAPOURS:
				case FIREBALL:
				case SLAM:
					break;
					
				// Tier 2:
				case ARCANE_CLOUD:
				case FLASH:
				case RAIN_CLOUD:
				case TELEKENETIC_SHOWER:
				case TELEPATHIC_COMMUNICATION:
				case VACUUM:
					value = 5000;
					break;

				// Tier 3:
				case STONE_SHELL:
				case SOOTHING_WATERS:
				case PROTECTIVE_GUSTS:
				case CLOAK_OF_FLAMES:
				case CLEANSE:
				case STEAL:
					value = 10000;
					break;
					
				// Tier 4:
				case ELEMENTAL_AIR:
				case ELEMENTAL_ARCANE:
				case ELEMENTAL_EARTH:
				case ELEMENTAL_FIRE:
				case ELEMENTAL_WATER:
					value = 25000;
					break;
					
				// Tier 5:
				case LILITHS_COMMAND:
				case TELEPORT:
					value = 1000000;
					break;
					
				case WITCH_CHARM:
				case WITCH_SEAL:
				case DARK_SIREN_SIRENS_CALL:
				case LIGHTNING_SPHERE_DISCHARGE:
				case LIGHTNING_SPHERE_OVERCHARGE:
				case ARCANE_CHAIN_LIGHTNING:
				case ARCANE_LIGHTNING_SUPERBOLT:
					break;
			}
			
			AbstractItemType spellBook = new AbstractItemType(value,
					null,
					false,
					"法术书："+s.getName(),
					"法术书："+s.getName(),
					"一本超厚的奥术宝典，内含手把手的“"+s.getName()+"”从入门到精通。"
							+ "阅读此书后，便能习得该法术。",
					"spell_book",
					s.getSpellSchool().getColour(),
					null,
					null,
					Rarity.LEGENDARY,
					Util.newArrayListOfValues(new ItemEffect(effectType)),
					Util.newArrayListOfValues(ItemTag.SPELL_BOOK)) {
		
				@Override
				public String getSVGString() {
					return super.getSVGString()
							+"<div style='width:60%;height:60%;position:absolute;left:0;top:0;'>"
								+ s.getSVGString()
							+ "</div>";
				}
				@Override
				public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
					return (target.isPlayer() || target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.ONE_AVERAGE.getMinimumValue())
							&& !(target.isElemental());
				}
				@Override
				public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
					if(target.isPlayer()) {
						return "你已经知道怎么使用这个法术了！";
						
					} else if(target.isElemental()) {
						return UtilText.parse(target, "就像其他元素体一样，[npc.Name]不能从书中学习法术。"
								+ "相反[npc.she]需要专注于提高对于奥术的理解，从而才能学习新法术。"
								+ "(元素体通过天赋树获得新法术。)");
						
					} else {
						return UtilText.parse(target, "[npc.Name]没有足够的奥术技巧来学习这个法术！(需要奥术技巧至少为"+IntelligenceLevel.ONE_AVERAGE.getMinimumValue()+")");
					}
				}
				@Override
				public String getUseName() {
					return "阅读";
				}
				@Override
				public String getUseDescription(GameCharacter user, GameCharacter target) {
					return getGenericUseDescription(user, target,
							"你打开法术书，阅读了书中的内容……",
							"你打开法术书，让[npc.Name]阅读书中的内容……",
							"[npc.Name]拿出一本法术书，阅读起书中的内容……",
							"[npc.Name]拿出一本法术书，让你阅读书中的内容……");
				}
				@Override
				public boolean isAbleToBeUsedInSex() {
					return false;
				}
				@Override
				public boolean isAbleToBeUsedInCombatAllies() {
					return false;
				}
			};
			
			itemToIdMap.put(spellBook, "SPELL_BOOK_"+s);
			idToItemMap.put("SPELL_BOOK_"+s, spellBook);
			
			allItems.add(spellBook);
		}
		
		for(SpellSchool school : SpellSchool.values()) {
			
			AbstractItemEffectType effectType = new AbstractItemEffectType(Util.newArrayListOfValues(
							"<span style='color:"+school.getColour().toWebHexString()+";'>"+school.getName()+"</span>升级点数[style.boldExcellent(+1)]。"),
							school.getColour()) {
						
						@Override
						public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
							target.incrementSpellUpgradePoints(school, 1);
							return "<p style='text-align:center;'>"
										+ (target.isPlayer()?"你获得了":UtilText.parse(target, "[npc.Name]获得了"))+"一点<b style='color:"+school.getColour().toWebHexString()+";'>"+school.getName()+"</b>学派的升级点数！<br/>"
										+ "<i>卷轴完成了它的使命，在一道紫光中消失了！</i>"
									+ "</p>";
						}
					};

			ItemEffectType.addAbstractItemEffectToIds("EFFECT_SCROLL_SCHOOL_"+school, effectType);
			
			AbstractItemType scroll = new AbstractItemType(1000,
					null,
					false,
					Util.capitaliseSentence(school.getName()) + "卷轴",
					Util.capitaliseSentence(school.getName()) + "卷轴",
					"一张奥术卷轴，阅读后会将"+Util.capitaliseSentence(school.getName())+"学派的力量注入到阅读者体内。",
					"spell_scroll",
					school.getColour(),
					null,
					null,
					Rarity.EPIC,
					Util.newArrayListOfValues(new ItemEffect(effectType)),
					Util.newArrayListOfValues(ItemTag.SPELL_SCROLL)) {
				@Override
				public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
					return (target.isPlayer() || target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.ONE_AVERAGE.getMinimumValue())
							&& !target.isElemental();
				}
				@Override
				public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
					if(target.isElemental()) {
						return "元素体无法使用卷轴，相反是通过天赋精进法术！";
					}
					return UtilText.parse(target, "[npc.Name]没有足够的奥术技巧来得知吸收卷轴力量的方法！(需要奥术技巧至少为"+IntelligenceLevel.ONE_AVERAGE.getMinimumValue()+"。)");
				}
				@Override
				public String getUseName() {
					return "阅读";
				}
				@Override
				public String getUseDescription(GameCharacter user, GameCharacter target) {
					return getGenericUseDescription(user, target,
							"你展开卷轴，阅读了其中的内容……",
							"你展开卷轴，让[npc.Name]阅读了其中的内容……",
							"[npc.Name]拿出一张卷轴，展开后阅读了其中的内容……",
							"[npc.Name]拿出一张卷轴，展开后让你阅读了其中的内容……");
				}
				@Override
				public boolean isAbleToBeUsedInSex() {
					return false;
				}
				@Override
				public boolean isAbleToBeUsedInCombatAllies() {
					return false;
				}
			};
			
			itemToIdMap.put(scroll, "SPELL_SCROLL_"+school);
			idToItemMap.put("SPELL_SCROLL_"+school, scroll);
			
			allItems.add(scroll);
		}
		
		// Race books:
		
		Map<String, List<AbstractSubspecies>> subspeciesLoreMap = new HashMap<>();
		for(AbstractSubspecies sub : Subspecies.getAllSubspecies()) {
			subspeciesLoreMap.putIfAbsent(sub.getAdvancedDescriptionId(), new ArrayList<>());
			subspeciesLoreMap.get(sub.getAdvancedDescriptionId()).add(sub);
		}
		
		// Add effects from here, as Subspecies and ItemEffectType are dependent on one another to be initialised.
		for(AbstractSubspecies sub : Subspecies.getAllSubspecies()) {
			subspeciesLoreMap.putIfAbsent(sub.getAdvancedDescriptionId(), new ArrayList<>());
			subspeciesLoreMap.get(sub.getAdvancedDescriptionId()).add(sub);
		}
		
		for(Entry<String, List<AbstractSubspecies>> entry : subspeciesLoreMap.entrySet()) {
			AbstractSubspecies mainSubspecies = entry.getValue().contains(AbstractSubspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace()))
											?AbstractSubspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace())
											:entry.getValue().get(0);
			
			AbstractItemType loreBook = new AbstractItemType(250,
							null,
							false,
							mainSubspecies.getBookName(),
							mainSubspecies.getBookNamePlural(),
							"包含着有关"+mainSubspecies.getNamePlural(null)+"更详细的知识。",
							"race_book",
							mainSubspecies.getColour(null),
							PresetColour.CLOTHING_GOLD,
							mainSubspecies.getColour(null),
							Rarity.RARE,
							null,
							Util.newArrayListOfValues(ItemTag.BOOK)) {
				@Override
				public String getDescription() {
					return super.getDescription()
							+(mainSubspecies.getBookAuthor().isEmpty()?"":"这本书的作者为“"+mainSubspecies.getBookAuthor()+"”。");
				}
				@Override
				public List<ItemEffect> getEffects() {
					AbstractSubspecies mainSubspecies = entry.getValue().contains(AbstractSubspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace()))
							?AbstractSubspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace())
							:entry.getValue().get(0);
					String id = "BOOK_READ_"+Subspecies.getIdFromSubspecies(mainSubspecies);
					
					if(!ItemEffectType.idToItemEffectTypeMap.containsKey(id)) {
						AbstractItemEffectType bookType = generateBookEffect(mainSubspecies, entry.getValue());
						ItemEffectType.allEffectTypes.add(bookType);
						ItemEffectType.itemEffectTypeToIdMap.put(bookType, id);
						ItemEffectType.idToItemEffectTypeMap.put(id, bookType);
					}
					
					return Util.newArrayListOfValues(new ItemEffect(ItemEffectType.getBookEffectFromSubspecies(mainSubspecies)));
				}
				@Override
				public String getSVGString() {
					int offset = 6;
					float left = (float) (30 + offset*Math.cos(Math.toRadians(60)));
					left = Math.round(left*100);
					left /=100;
					return super.getSVGString()
							+"<div style='width:40%;height:40%;position:absolute;left:"+left+"%;top:"+(30-offset)+"%; opacity:0.75; -webkit-transform: rotate(30deg);'>"
								+ mainSubspecies.getBookSVGString()
							+ "</div>";
				}
				@Override
				public boolean isConsumedOnUse() {
					return false;
				}
				@Override
				public String getUseName() {
					return "阅读";
				}
				@Override
				public String getUseDescription(GameCharacter user, GameCharacter target) {
					return getGenericUseDescription(user, target,
							"你翻开书，阅读了其中的内容……",
							"你翻开书，让[npc.name]阅读了其中的内容……",
							"[npc.Name]拿出一本名为“"+getName(false)+"”的书，阅读起其中的内容……",
							"[npc.Name]拿出一本名为“"+getName(false)+"”的书，让你阅读了其中的内容……");
				}
			};
			
			String id = "BOOK_"+Subspecies.getIdFromSubspecies(mainSubspecies);
					
			itemToIdMap.put(loreBook, id);
			idToItemMap.put(id, loreBook);
			
			for(AbstractSubspecies subspecies : entry.getValue()) {
				subspeciesBookId.put(subspecies, id);
			}
			
			allItems.add(loreBook);
			
			
			// Essences
			if(mainSubspecies!=Subspecies.CENTAUR) { // a CENTAUR essence is identical to a HORSE_MORPH essence

				int override = mainSubspecies.getSubspeciesOverridePriority();
				String raceName = (override>0?mainSubspecies.getFeralName(null):mainSubspecies.getRace().getName(true));
				String raceNamePlural = (override>0?mainSubspecies.getFeralNamePlural(null):mainSubspecies.getRace().getNamePlural(true));

				AbstractStatusEffect statusEffect = new AbstractStatusEffect(80,
						(mainSubspecies.getRace()==Race.ANGEL
							?"天使"
							:(mainSubspecies.getRace()==Race.DEMON && override <= 2
								?"小恶魔"
								:(mainSubspecies.getRace()==Race.DEMON && override >= 5 && override <= 20
									?"恶魔"
									:(mainSubspecies==Subspecies.LILIN
										?"莉琳"
										:(mainSubspecies==Subspecies.ELDER_LILIN
											?"莉琳长老"
											:raceName.toLowerCase())))))
							+ "直觉",
						null,
						mainSubspecies.getColour(null),
						true,
						Util.newHashMapOfValues(new Util.Value<>(Attribute.MAJOR_PHYSIQUE, 2f),
								new Util.Value<>(
									mainSubspecies.getRace()==Race.DEMON && override <= 2
										?Attribute.DAMAGE_IMP
										:(mainSubspecies==Subspecies.LILIN
											?Attribute.DAMAGE_LILIN
											:mainSubspecies==Subspecies.ELDER_LILIN
												?Attribute.DAMAGE_ELDER_LILIN
												:Attribute.getRacialDamageAttribute(mainSubspecies.getRace())),
									25f)),
						null) {
					@Override
					public String getDescription(GameCharacter target) {
						if(target == null) {
							return "";
						}
						return UtilText.parse(target, "[npc.nameIsFull]在吸收了特定附魔的奥术精华后，能够精准地预测"
								+ raceNamePlural.toLowerCase() +"的行动方式了。");
					}
					@Override
					public String getSVGString(GameCharacter owner) {
						return getEssenceEffectSvg(mainSubspecies);
					}
				};

				String effect_id = "COMBAT_BONUS_"+Subspecies.getIdFromSubspecies(mainSubspecies).toUpperCase();

				StatusEffect.statusEffectToIdMap.put(statusEffect, effect_id);
				StatusEffect.idToStatusEffectMap.put(effect_id, statusEffect);
				StatusEffect.allStatusEffects.add(statusEffect);

				AbstractItemEffectType effectType = new AbstractItemEffectType(Util.newArrayListOfValues(
						"[style.boldGood(+1)][style.boldArcane(奥术精华)]"),
						mainSubspecies.getColour(null)) {
					@Override
					public Map<AbstractStatusEffect, Integer> getAppliedStatusEffects() {
						return Util.newHashMapOfValues(new Value<>(statusEffect, 60*4*60));
					}
//					@Override
//					public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
//						List<String> list = super.getEffectsDescription(primaryModifier, secondaryModifier, potency, limit, user, target);
//						list.add("Applies <i style='color:"+statusEffect.getColour().toWebHexString()+";'>'"+Util.capitaliseSentence(statusEffect.getName(target))+"'</i>:");
//						for(Entry<AbstractAttribute, Float> entry : statusEffect.getAttributeModifiers(target).entrySet()) {
//							list.add("<i>"+entry.getKey().getFormattedValue(entry.getValue())+"</i>");
//						}
//						return list;
//					}
					@Override
					public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
						if(target.isDoll()) {
							return UtilText.parse(target,
										"<p style='text-align:center;'>"
											+ "[style.colourDisabled(由于[npc.sheIsFull]是个性爱玩偶，无法吸收精华……)]"
										+ "</p>");
						}
						return target.incrementEssenceCount(1, false)
								+ UtilText.parse(target,
										"<p style='text-align:center;'>"
											+"[npc.She]暂时能够更有效地对"
											+ "<b style='color:"+mainSubspecies.getColour(null).toWebHexString()+";'>" + raceNamePlural +"</b>发起攻击！"
										+ "</p>");
					}
				};

				ItemEffectType.addAbstractItemEffectToIds("BOTTLED_ESSENCE_"+Subspecies.getIdFromSubspecies(mainSubspecies).toUpperCase(), effectType);

				AbstractItemType essence = new AbstractItemType(
						Math.min(Math.max((mainSubspecies.getBaseSlaveValue(null) / 250), 40), 10000), // i.e. 48 flames for cat-morphs, minimum 40 flames, 10000 maximum
						null,
						false,
						"瓶装" + Util.capitaliseSentence(raceName) + "精华",
						"瓶装" + Util.capitaliseSentence(raceName) + "精华",
						"一个小玻璃瓶，顶部紧紧地塞着一颗小巧的软木塞。"
								+ "里面"+PresetColour.GENERIC_ARCANE.getName()+"光芒的奥术精华舞动闪烁着，不断飞旋，令人着迷，"
								+ "并且注入了"+UtilText.generateSingularDeterminer(raceName) + raceName
								+ "的能量。",
						null,
						mainSubspecies.getColour(null),
						null,
						null,
						Rarity.EPIC,
						Util.newArrayListOfValues(new ItemEffect(effectType)),
						(((mainSubspecies.getRace()==Race.DEMON && mainSubspecies.getSubspeciesOverridePriority()>5) || mainSubspecies.getRace()==Race.ANGEL) // Demon+ (and Angels) are contraband
								?Util.newArrayListOfValues(ItemTag.ESSENCE, ItemTag.CONTRABAND_HEAVY)
								:Util.newArrayListOfValues(ItemTag.ESSENCE))) {
						
					@Override
					public String getUseName() {
						return "吸收";
					}
					@Override
					public String getUseDescription(GameCharacter user, GameCharacter target) {
						return getEssenceAbsorptionText(mainSubspecies.getColour(null), user, target);
					}
					@Override
					public String getSVGString() {
						return getEssenceSvg(mainSubspecies);
					}

				};

				String essence_id = "BOTTLED_ESSENCE_"+Subspecies.getIdFromSubspecies(mainSubspecies).toUpperCase();

				itemToIdMap.put(essence, essence_id);
				idToItemMap.put(essence_id, essence);

				allItems.add(essence);
				essences.add(essence);
			}

		}
		
		// Add items to spawn lists:
		for(AbstractItemType item : allItems) {
			if(item.getItemTags().contains(ItemTag.DOMINION_ALLEYWAY_SPAWN) || item.getItemTags().contains(ItemTag.ALL_AREAS_SPAWN)) {
				dominionAlleywayItems.add(item);
			}
			if(item.getItemTags().contains(ItemTag.SUBMISSION_TUNNEL_SPAWN) || item.getItemTags().contains(ItemTag.ALL_AREAS_SPAWN)) {
				submissionTunnelItems.add(item);
			}
			if(item.getItemTags().contains(ItemTag.BAT_CAVERNS_SPAWN) || item.getItemTags().contains(ItemTag.ALL_AREAS_SPAWN)) {
				batCavernItems.add(item);
			}
			if(item.getItemTags().contains(ItemTag.ELIS_ALLEYWAY_SPAWN) || item.getItemTags().contains(ItemTag.ALL_AREAS_SPAWN)) {
				elisAlleywayItems.add(item);
			}
		}
	}
	
	private static AbstractItemEffectType generateBookEffect(AbstractSubspecies mainSubspecies, List<AbstractSubspecies> additionalUnlockSubspecies) {
		return new AbstractItemEffectType(Util.newArrayListOfValues(
				"添加"+mainSubspecies.getName(null)+"百科条目",
				"揭示该种族的种族状态效果具体属性",
				"[style.colourExcellent(获得独特天赋：)]",
				Perk.getSubspeciesRelatedPerk(mainSubspecies).getName(null)
				//"[style.boldExcellent(+10)] <b style='color:"+mainSubspecies.getColour(null).toWebHexString()+";'>"+mainSubspecies.getDamageMultiplier().getName()+"</b>"
				),
				mainSubspecies.getColour(null)) {
			@Override
			public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
				return getBookEffect(target, mainSubspecies, additionalUnlockSubspecies, true);
			}
		};
	}
	
	private static Map<AbstractSubspecies, String> essenceMap = new HashMap<>();
	
	private static String getEssenceSvg(AbstractSubspecies subspecies) {
		if(essenceMap.containsKey(subspecies)) {
			return essenceMap.get(subspecies);
		}
		String background = "";
		String bottle = "";
		Colour colour = subspecies.getColour(null);
		try {
			InputStream is = ItemType.class.getResourceAsStream("/com/lilithsthrone/res/items/essenceBackground.svg");
			String s = Util.inputStreamToString(is);
			
			background = SvgUtil.colourReplacement(subspecies.getName(null), colour, s);
			is.close();
			
			is = ItemType.class.getResourceAsStream("/com/lilithsthrone/res/items/essenceBottle.svg");
			s = Util.inputStreamToString(is);
			bottle = SvgUtil.colourReplacement(subspecies.getName(null), colour, s);
			is.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String subspeciesIcon = subspecies.getSVGStringNoBackground();
		subspeciesIcon = subspeciesIcon.replaceAll("fill=\"#(.*?)\"", "fill=\""+colour.getShades()[1]+"\"");
//		subspeciesIcon = subspeciesIcon.replaceAll(colour.getShades()[1], colour.getShades()[0]);
//		subspeciesIcon = subspeciesIcon.replaceAll(colour.getShades()[2], colour.getShades()[1]);
//		subspeciesIcon = subspeciesIcon.replaceAll(colour.getShades()[3], colour.getShades()[2]);
//		subspeciesIcon = subspeciesIcon.replaceAll(colour.getShades()[4], colour.getShades()[3]);
		subspeciesIcon = subspeciesIcon.replaceAll("stroke=\"#(.*?)\"", "stroke=\""+colour.getShades()[1]+"\"");
		
		String finalImage = "<div style='width:80%;height:80%;position:absolute;left:10%;top:10%;'>"
								+ background
							+ "</div>"
							+"<div style='width:70%;height:70%;position:absolute;left:15%;top:20%;'>"
								+ subspeciesIcon
							+ "</div>"
							+ "<div style='width:60%;height:60%;position:absolute;left:20%;top:25%;'>"
								+ bottle
							+ "</div>";
		
		essenceMap.put(subspecies, finalImage);
		
		return finalImage;
	}

	private static String getEssenceEffectSvg(AbstractSubspecies subspecies) {
		String background = "";
		Colour colour = subspecies.getColour(null);
		try {
			InputStream is = ItemType.class.getResourceAsStream("/com/lilithsthrone/res/items/essenceBackground.svg");
			String s = Util.inputStreamToString(is);

			background = SvgUtil.colourReplacement(subspecies.getName(null), colour, s);
			is.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String subspeciesIcon = subspecies.getSVGStringNoBackground();
		subspeciesIcon = subspeciesIcon.replaceAll("fill=\"#(.*?)\"", "fill=\""+colour.getShades()[1]+"\"");
		subspeciesIcon = subspeciesIcon.replaceAll("stroke=\"#(.*?)\"", "stroke=\""+colour.getShades()[1]+"\"");

		String finalImage = "<div style='width:80%;height:80%;position:absolute;left:10%;top:10%;'>"
				+ background
				+ "</div>"
				+"<div style='width:70%;height:70%;position:absolute;left:15%;top:20%;'>"
				+ subspeciesIcon
				+ "</div>";

		return finalImage;
	}

	public static List<AbstractItemType> getDominionAlleywayItems() {
		return dominionAlleywayItems;
	}
	
	public static List<AbstractItemType> getSubmissionTunnelItems() {
		return submissionTunnelItems;
	}
	
	public static List<AbstractItemType> getBatCavernItems() {
		return batCavernItems;
	}
	
	public static List<AbstractItemType> getElisAlleywayItems() {
		return elisAlleywayItems;
	}
	
	public static List<AbstractItemType> getEssences() {
		return essences;
	}
	
	public static List<AbstractItemType> getAllItems() {
		return allItems;
	}
	
	public static Map<AbstractItemType, String> getItemToIdMap() {
		return itemToIdMap;
	}
	
	public static Map<String, AbstractItemType> getIdToItemMap() {
		return idToItemMap;
	}

}
