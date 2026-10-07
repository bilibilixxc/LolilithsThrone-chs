package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public class LegType {
	
	public static AbstractLegType HUMAN = new AbstractLegType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			FootStructure.PLANTIGRADE,
			FootType.HUMANOID,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚"),
			Util.newArrayListOfValues("阴柔"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"迅速转化为了正常的人类腿部，还带着人类的脚。<br/>"
				+ "[npc.She]现在有着[style.boldHuman(人类的腿和脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿和脚是人类形态，[npc.materialCompositionDescriptor][npc.legFullDescription(true)]。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
	};
	
	public static AbstractLegType ANGEL = new AbstractLegType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			FootStructure.PLANTIGRADE,
			FootType.HUMANOID,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "红润", "天使"),
			Util.newArrayListOfValues("阴柔", "红润", "天使"),
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues("天使"),
			"迅速转化为了一双光滑、纤细的腿，无暇的天使皮肤迅速覆盖其上，[npc.she]不禁惊叹一声。"
				+ "这部分转化结束后，[npc.her]脚上的骨头也开始变形重构，害得[npc.she]差点失去平衡。"
				+ "过了一段时间，便化成像人一样纤细的脚，脚趾柔软且细腻。<br/>"
				+ "[npc.Name]现在拥有[style.boldAngel(天使的腿和脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿和脚形状上与人类无异，但[npc.materialCompositionDescriptor][npc.legFullDescription(true)]。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
	};

	public static AbstractLegType DEMON_COMMON = new AbstractLegType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			FootStructure.PLANTIGRADE,
			FootType.HUMANOID,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚","恶魔"),
			Util.newArrayListOfValues("阴柔", "无暇", "恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			"-",
			"[npc.Her]的腿和脚形状上与人类无异，但[npc.materialCompositionDescriptor][npc.legFullDescription(true)]。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			if (!owner.isShortStature()) {
				return UtilText.parse(owner,
						"迅速转化为了一双光滑、纤细的腿，无暇的恶魔皮肤迅速覆盖其上，[npc.she]不禁惊叹一声。"
						+ "这部分转化结束后，[npc.her]脚上的骨头也开始变形重构，害得[npc.she]差点失去平衡。"
						+ "过了一段时间，便化成像人一样纤细的脚，脚趾柔软且细腻。<br/>"
						+ "[npc.Name]现在拥有[style.boldDemon(恶魔的腿和脚)]，[npc.materialDescriptor][npc.legFullDescription]。"
					+ "</p>");
			} else {
				return UtilText.parse(owner,
						"迅速转化为了一双光滑、纤细的腿，无暇的小恶魔皮肤迅速覆盖其上，[npc.she]不禁惊叹一声。"
						+ "这部分转化结束后，[npc.her]脚上的骨头也开始变形重构，害得[npc.she]差点失去平衡。"
						+ "过了一段时间，便化成像人一样纤细的脚，脚趾柔软且细腻。<br/>"
						+ "[npc.Name]现在拥有[style.boldImp(小恶魔的腿和脚)]，[npc.materialDescriptor][npc.legFullDescription]。"
					+ "</p>");
			}
		}
	};

	public static AbstractLegType DEMON_HOOFED = new AbstractLegType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚","恶魔"),
			Util.newArrayListOfValues("阴柔", "无暇", "恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			"-",
			"[npc.Her]恶魔的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了硬质的蹄子。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			if (!owner.isShortStature()) {
				return UtilText.parse(owner,
							"迅速转化为了一双光滑、纤细的腿，无暇的恶魔皮肤迅速覆盖其上，[npc.she]不禁惊叹一声。"
							+ "新生的皮肤一直延伸到脚趾，[npc.she]忽然大叫一声，原来是脚趾并在了一起，一块厚实的蹄子般的指甲在原处生长出来，"
								+ "迅速将[npc.her]的脚转化为硬质的恶魔蹄子。"
							+ "转化结束后，[npc.she]看到大腿处新生的皮肤自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
							+ "[npc.NameIsFull]有着[style.boldDemon(恶魔的腿和蹄状的脚)]，[npc.materialDescriptor][npc.legFullDescription]。"
						+ "</p>");
			} else {
				return UtilText.parse(owner,
						"迅速转化为了一双光滑、纤细的腿，无暇的小恶魔皮肤迅速覆盖其上，[npc.she]不禁惊叹一声。"
						+ "新生的皮肤一直延伸到脚趾，[npc.she]忽然大叫一声，原来是脚趾并在了一起，一块厚实的蹄子般的指甲在原处生长出来，"
							+ "迅速将[npc.her]的脚转化为硬质的恶魔蹄子。"
						+ "转化结束后，[npc.she]看到大腿处新生的皮肤自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
						+ "[npc.Name]现在拥有[style.boldImp(小恶魔的腿和蹄状的脚)]，[npc.materialDescriptor][npc.legFullDescription]。"
					+ "</p>");
			}
		}
		@Override
		public String getTransformName() {
			return "恶魔蹄";
		}
	};

	public static AbstractLegType DEMON_HORSE_HOOFED = new AbstractLegType(BodyCoveringType.HORSE_HAIR,
			Race.DEMON,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "覆盖毛发", "恶魔马般"),
			Util.newArrayListOfValues("阴柔", "覆盖毛发", "恶魔马般"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			"-",
			"[npc.Her]恶魔化马一般的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了硬质的蹄子。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"在[npc.her]的恶魔腿转变形态时，马一般的粗短毛发迅速生长于其上。"
					+ "毛发一直延伸到脚趾，[npc.her]忽然大叫一声，原来是脚趾并在了一起，一块厚实的蹄子般的指甲在原处生长出来，"
						+ "迅速将[npc.her]的脚转化为硬质的恶魔蹄子。"
					+ "转化结束后，[npc.she]看到大腿处新生的毛发自然过渡成了覆盖身体其他部位的[npc.skin]。"
					+ "<br/>[npc.Name]现在拥有"
						+ (!owner.isShortStature()
							?"[style.boldDemon(恶魔化动物般的腿和蹄状的脚)]，[npc.materialDescriptor][npc.legFullDescription]。"
							:"[style.boldImp(小恶魔化动物般的腿和蹄状的脚)]，[npc.materialDescriptor][npc.legFullDescription]。"));
		}
		@Override
		public String getTransformName() {
			return "恶魔马";
		}
	};

	public static AbstractLegType DEMON_SNAKE = new AbstractLegType(BodyCoveringType.SNAKE_SCALES,
			Race.DEMON,
			FootStructure.NONE,
			FootType.NONE,
			"一条",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("阳刚", "鳞片覆盖", "恶魔蛇"),
			Util.newArrayListOfValues("阴柔", "鳞片覆盖", "恶魔蛇"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			"-",
			"[npc.Her]恶魔化蛇一般的下肢[npc.materialCompositionDescriptor][npc.legFullDescription(true)]",
			Util.newArrayListOfValues(
					LegConfiguration.TAIL_LONG),
			false) {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return false;
		}
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"在[npc.her]的恶魔腿转变蛇的样貌时，一层光滑的鳞片迅速生长于其上。"
					+ "转化很快就步入尾声，[npc.Name]大腿处新生的鳞片自然过渡成了覆盖身体其他部位的[npc.skin]。"
					+ "<br/>[npc.Name]现在拥有"
						+ (!owner.isShortStature()
							?"[style.boldDemon(恶魔化蛇一般的腿)]，[npc.materialDescriptor][npc.legFullDescription]。"
							:"[style.boldImp(小恶魔化蛇一般的腿)]，[npc.materialDescriptor][npc.legFullDescription]。"));
		}
		@Override
		public String getTransformName() {
			return "恶魔蛇";
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LYXIAS_6); //TODO?
		}
	};

	public static AbstractLegType DEMON_SPIDER = new AbstractLegType(BodyCoveringType.SPIDER_CHITIN,
			Race.DEMON,
			FootStructure.DIGITIGRADE,
			FootType.ARACHNID,
			"一条",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "蛛形", "恶魔蛛"),
			Util.newArrayListOfValues("阴柔", "蛛形", "恶魔蛛"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			"-",
			"[npc.Her]恶魔化蜘蛛般的下肢[npc.materialCompositionDescriptor][npc.legFullDescription(true)]",
			Util.newArrayListOfValues(
					LegConfiguration.ARACHNID),
			true) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"在[npc.her]的恶魔腿转变为蜘蛛样貌时，一层光滑的几丁质外壳迅速生长于其上。"
					+ "转化很快就步入尾声，[npc.Name]大腿处新生的光滑几丁质外壳自然过渡成了覆盖身体其他部位的[npc.skin]。"
					+ "<br/>[npc.Name]现在拥有"
						+ (!owner.isShortStature()
							?"[style.boldDemon(恶魔化蜘蛛般的腿)]，[npc.materialDescriptor][npc.legFullDescription]。"
							:"[style.boldImp(小恶魔化蜘蛛般的腿)]，[npc.materialDescriptor][npc.legFullDescription]。"));
		}
		@Override
		public String getTransformName() {
			return "恶魔蛛";
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LUNETTE_5); //TODO?
		}
	};

	public static AbstractLegType DEMON_OCTOPUS = new AbstractLegType(BodyCoveringType.OCTOPUS_SKIN,
			Race.DEMON,
			FootStructure.TENTACLED,
			FootType.TENTACLE,
			"一根",
			"触手",
			"触手",
			Util.newArrayListOfValues("阳刚", "头足类", "恶魔章鱼"),
			Util.newArrayListOfValues("阴柔", "头足类", "恶魔章鱼"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			"-",
			"[npc.Her]恶魔化章鱼般的下肢[npc.materialCompositionDescriptor][npc.legFullDescription(true)]。",
			Util.newArrayListOfValues(
					LegConfiguration.CEPHALOPOD),
			false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"在[npc.her]的恶魔腿转变为触手状样貌时，一层光滑的皮肤迅速生长于其上。"
					+ "转化很快就步入尾声，[npc.Name]大腿处新生的皮肤自然过渡成了覆盖身体其他部位的[npc.skin]。"
					+ "<br/>[npc.Name]现在拥有"
						+ (!owner.isShortStature()
							?"[style.boldDemon(恶魔化章鱼般的腿)]，[npc.materialDescriptor][npc.legFullDescription]。"
							:"[style.boldImp(小恶魔化章鱼般的腿)]，[npc.materialDescriptor][npc.legFullDescription]。"));
		}
		@Override
		public String getTransformName() {
			return "恶魔章鱼";
		}
		@Override
		public AbstractTentacleType getTentacleType() {
			return TentacleType.LEG_DEMON_OCTOPUS;
		}
		public int getTentacleCount() {
			return 8;
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LIRECEA_1); //TODO?
		}
	};

	public static AbstractLegType DEMON_FISH = new AbstractLegType(BodyCoveringType.FISH_SCALES,
			Race.DEMON,
			FootStructure.NONE,
			FootType.NONE,
			"一条",
			"尾巴",
			"尾巴",
			Util.newArrayListOfValues("阳刚", "鳞片覆盖", "恶魔鱼"),
			Util.newArrayListOfValues("阴柔", "鳞片覆盖", "恶魔鱼"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			"-",
			"[npc.Her]恶魔化鱼一般的下肢[npc.materialCompositionDescriptor][npc.legFullDescription(true)]。",
			Util.newArrayListOfValues(
					LegConfiguration.TAIL),
			false) {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE);
		}
		@Override
		public String getNameSingular(GameCharacter gc) {
			if(gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
				return "腿";
			} else {
				return "尾巴";
			}
		}
		@Override
		public String getNamePlural(GameCharacter gc) {
			if(gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
				return "腿";
			} else {
				return "尾巴";
			}
		}
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"在[npc.her]的恶魔腿转变为鱼的样貌时，光滑的鳞片迅速生长于其上。"
					+ "转化很快就步入尾声，[npc.Name]大腿处新生的鳞片自然过渡成了覆盖身体其他部位的[npc.skin]。"
					+ "<br/>[npc.Name]现在拥有"
						+ (!owner.isShortStature()
							?"[style.boldDemon(恶魔化鱼一般的腿)]，[npc.materialDescriptor][npc.legFullDescription]。"
							:"[style.boldImp(小恶魔化鱼一般的腿)]，[npc.materialDescriptor][npc.legFullDescription]。"));
		}
		@Override
		public String getTransformName() {
			return "恶魔鱼";
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LIRECEA_1); //TODO?
		}
	};

	public static AbstractLegType DEMON_EAGLE = new AbstractLegType(BodyCoveringType.FEATHERS,
			Race.DEMON,
			FootStructure.DIGITIGRADE,
			FootType.TALONS,
			"一条",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "带爪", "鸟一般拟人"),
			Util.newArrayListOfValues("阴柔", "带爪", "鸟一般拟人"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			"-",
			"[npc.Her]恶魔化鸟一般的下肢[npc.materialCompositionDescriptor][npc.legFullDescription(true)]。",
			Util.newArrayListOfValues(
					LegConfiguration.AVIAN),
			false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"在[npc.her]的恶魔腿转变为鸟的样貌时，一层羽毛迅速生长于其上。"
					+ "转化很快就步入尾声，[npc.Name]大腿处新生的羽毛自然过渡成了覆盖身体其他部位的[npc.skin]。"
					+ "<br/>[npc.Name]现在拥有"
						+ (!owner.isShortStature()
							?"[style.boldDemon(恶魔化鸟一般的腿)]，[npc.materialDescriptor][npc.legFullDescription]。"
							:"[style.boldImp(小恶魔化鸟一般的腿)]，[npc.materialDescriptor][npc.legFullDescription]。"));
		}
		@Override
		public String getTransformName() {
			return "恶魔鹰";
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LISOPHIA_7); //TODO?
		}
	};
	
	public static AbstractLegType COW_MORPH = new AbstractLegType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "蹄状", "毛茸茸", "覆盖毛皮", "牛一般拟人"),
			Util.newArrayListOfValues("阴柔", "蹄状", "毛茸茸", "覆盖毛皮", "牛一般拟人"),
			Util.newArrayListOfValues("牛一般", "牛类"),
			Util.newArrayListOfValues("牛一般", "牛类"),
			Util.newArrayListOfValues("牛一般", "牛类"),
			Util.newArrayListOfValues("牛一般", "牛类"),
			"在[npc.her]的腿转变形态时，牛一般的粗短毛发迅速生长于其上。"
				+ "新生的毛皮一直延伸到脚趾，[npc.she]忽然大叫一声，原来是脚趾并在了一起，一块厚实的蹄子般的指甲在原处生长出来，"
					+ "迅速将[npc.her]的脚转化为牛一般的硬质蹄子。"
				+ "转化结束后，[npc.she]看到大腿处新生的皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]现在拥有人形的[style.boldCowMorph(牛一般的腿和蹄状的脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了拟人的牛蹄子。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType DOG_MORPH = new AbstractLegType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "毛茸茸", "覆盖毛皮", "犬一般拟人"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "覆盖毛皮", "犬一般拟人"),
			Util.newArrayListOfValues("犬一般", "犬类"),
			Util.newArrayListOfValues("犬一般", "犬类"),
			Util.newArrayListOfValues("犬一般", "犬类"),
			Util.newArrayListOfValues("犬一般", "犬类"),
			"在[npc.her]的腿转变形态时，一层犬一般的皮毛迅速生长于其上。"
				+ "新生的毛皮一直延伸到脚趾，指甲增厚变成了圆钝的小爪子，皮质的肉垫覆盖了脚掌，给[npc.herHim]留下了脚爪般的脚。"
				+ "转化结束后，[npc.she]看到大腿处新生的皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]现在拥有人形的[style.boldDogMorph(犬一般的腿和脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了拟人的犬脚爪，长着圆钝的小爪子和皮质的肉垫。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType WOLF_MORPH = new AbstractLegType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "毛茸茸", "覆盖毛皮", "狼一般拟人"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "覆盖皮毛", "狼一般拟人"),
			Util.newArrayListOfValues("狼一般", "狼类"),
			Util.newArrayListOfValues("狼一般", "狼类"),
			Util.newArrayListOfValues("狼一般", "狼类"),
			Util.newArrayListOfValues("狼一般", "狼类"),
			"在[npc.her]的腿转变形态时，一层狼一般的皮毛迅速生长于其上。"
				+ "新生的毛皮一直延伸到脚趾，指甲增厚变成了锋利的爪子，粗糙的皮质肉垫覆盖了脚掌，给[npc.herHim]留下了脚爪般的脚。"
				+ "转化结束后，[npc.she]看到大腿处新生的皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]现在拥有人形的[style.boldWolfMorph(狼一般的腿和脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了拟人的狼脚爪，长着锋利的爪子和皮质的肉垫。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType FOX_MORPH = new AbstractLegType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "毛茸茸", "覆盖毛皮", "狐狸般拟人"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "覆盖皮毛", "狐狸般拟人"),
			Util.newArrayListOfValues("狐狸般", "狐类"),
			Util.newArrayListOfValues("狐狸般", "狐类"),
			Util.newArrayListOfValues("狐狸般", "狐类"),
			Util.newArrayListOfValues("狐狸般", "狐类"),
			"在[npc.her]的腿转变形态时，一层狐狸般的皮毛迅速生长于其上。"
				+ "新生的毛皮一直延伸到脚趾，指甲增厚变成了锋利的爪子，小巧的肉垫覆盖了脚掌，给[npc.herHim]留下了脚爪般的脚。"
				+ "转化结束后，[npc.she]看到大腿处新生的皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。</br>"
				+ "[npc.Name]现在拥有人形的[style.boldFoxMorph(狐狸般的腿和脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了拟人的狐狸脚爪，长着锋利的爪子和小巧的肉垫。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType SQUIRREL_MORPH = new AbstractLegType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			FootStructure.PLANTIGRADE,
			FootType.PAWS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "毛茸茸", "覆盖毛皮", "松鼠般拟人"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "覆盖皮毛", "松鼠般拟人"),
			Util.newArrayListOfValues("松鼠般"),
			Util.newArrayListOfValues("松鼠般"),
			Util.newArrayListOfValues("松鼠般"),
			Util.newArrayListOfValues("松鼠般"),
			"在[npc.her]的腿转变形态时，一层松鼠般的皮毛迅速生长于其上。"
				+ "新生的毛皮一直延伸到脚趾，指甲增厚变成了锋利的爪子，小巧的粉色肉垫覆盖了脚掌，给[npc.herHim]留下了脚爪般的脚。"
				+ "转化结束后，[npc.she]看到大腿处新生的皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]现在拥有人形的[style.boldSquirrelMorph(松鼠般的腿和脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了拟人的松鼠脚爪，长着爪子和粉色的肉垫。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType RAT_MORPH = new AbstractLegType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			FootStructure.PLANTIGRADE,
			FootType.PAWS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "毛茸茸", "覆盖毛皮", "老鼠般拟人"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "覆盖皮毛", "老鼠般拟人"),
			Util.newArrayListOfValues("老鼠般"),
			Util.newArrayListOfValues("老鼠般"),
			Util.newArrayListOfValues("老鼠般"),
			Util.newArrayListOfValues("老鼠般"),
			"在[npc.her]的腿转变形态时，一层老鼠般的皮毛迅速生长于其上。"
				+ "新生的毛皮一直延伸到脚趾，指甲增厚变成了锋利的爪子，小巧的粉色肉垫覆盖了脚掌，给[npc.herHim]留下了脚爪般的脚。"
				+ "转化结束后，[npc.she]看到大腿处新生的皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]现在拥有人形的[style.boldRatMorph(老鼠般的腿和脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了拟人的老鼠脚爪，长着爪子和粉色的肉垫。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType RABBIT_MORPH = new AbstractLegType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			FootStructure.PLANTIGRADE,
			FootType.PAWS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "毛茸茸", "覆盖毛皮", "兔子般拟人"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "覆盖皮毛", "兔子般拟人"),
			Util.newArrayListOfValues("兔子般"),
			Util.newArrayListOfValues("兔子般"),
			Util.newArrayListOfValues("兔子般"),
			Util.newArrayListOfValues("兔子般"),
			"在[npc.her]的腿转变形态时，一层兔子般的皮毛迅速生长于其上。"
				+ "新生的毛皮一直延伸到脚趾，指甲增厚变成了圆钝的爪子，小巧柔软的肉垫覆盖了脚掌，给[npc.herHim]留下了脚爪般的脚。"
				+ "转化结束后，[npc.she]看到大腿处新生的皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]现在拥有人形的[style.boldRabbitMorph(兔般的腿和脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了拟人的兔子脚爪，长着圆钝的爪子和柔软的肉垫。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType BAT_MORPH = new AbstractLegType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "毛茸茸", "覆盖毛皮", "蝙蝠般拟人"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "覆盖皮毛", "蝙蝠般拟人"),
			Util.newArrayListOfValues("蝙蝠般"),
			Util.newArrayListOfValues("蝙蝠般"),
			Util.newArrayListOfValues("蝙蝠般"),
			Util.newArrayListOfValues("蝙蝠般"),
			"在[npc.her]的腿转变形态时，一层蝙蝠般的皮毛迅速生长于其上。"
				+ "新生的毛皮一直延伸到脚趾，指甲增厚变成了锋利的爪子，小巧的粉色肉垫覆盖了脚掌，给[npc.herHim]留下了脚爪般的脚。"
				+ "转化结束后，[npc.she]看到大腿处新生的皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]现在拥有人形的[style.boldBatMorph(蝙蝠般的腿和脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了拟人的蝙蝠脚爪，长着爪子和粉色的肉垫。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
	};
	
	public static AbstractLegType CAT_MORPH = new AbstractLegType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "毛茸茸", "覆盖毛皮", "猫一般拟人"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "覆盖皮毛", "猫一般拟人"),
			Util.newArrayListOfValues("猫一般", "猫类"),
			Util.newArrayListOfValues("猫一般", "猫类"),
			Util.newArrayListOfValues("猫一般", "猫类"),
			Util.newArrayListOfValues("猫一般", "猫类"),
			"在[npc.her]的腿转变形态时，一层猫一般的皮毛迅速生长于其上。"
				+ "新生的毛皮一直延伸到脚趾，指甲增厚变成了可收缩的锋利爪子，小巧的粉色肉垫覆盖了脚掌，给[npc.herHim]留下了脚爪般的脚。"
				+ "转化结束后，[npc.she]看到大腿处新生的皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]现在拥有人形的[style.boldCatMorph(猫一般的腿和脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了拟人的猫脚爪，长着可收缩的爪子和粉色的肉垫。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType ALLIGATOR_MORPH = new AbstractLegType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			FootStructure.PLANTIGRADE,
			FootType.HUMANOID,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "覆盖鳞片", "爬行类", "鳄鱼般拟人"),
			Util.newArrayListOfValues("阴柔", "覆盖鳞片", "爬行类", "鳄鱼般拟人"),
			Util.newArrayListOfValues("鳄鱼般", "覆盖鳞片", "爬行类"),
			Util.newArrayListOfValues("鳄鱼般", "覆盖鳞片", "爬行类"),
			Util.newArrayListOfValues("鳄鱼般", "覆盖鳞片", "爬行类"),
			Util.newArrayListOfValues("鳄鱼般", "覆盖鳞片", "爬行类"),
			"在[npc.her]的腿转变形态时，一层鳄鱼般的鳞片迅速生长于其上。"
				+ "新生的毛皮一直延伸到脚趾，指甲增厚变成了锋利的爪子，细小的鳞片覆盖了脚掌，给[npc.herHim]留下鳄鱼般的脚。"
				+ "转化结束后，[npc.she]看到大腿处新生的鳞片自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]现在拥有人形的[style.boldGatorMorph(鳄鱼般的腿和脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了鳄鱼般拟人的脚，长着锋利的爪子。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType HORSE_MORPH = new AbstractLegType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "覆盖毛发", "马一般拟人"),
			Util.newArrayListOfValues("阴柔", "覆盖毛发", "马一般拟人"),
			Util.newArrayListOfValues("马一般", "马类"),
			Util.newArrayListOfValues("马一般", "马类"),
			Util.newArrayListOfValues("马一般", "马类"),
			Util.newArrayListOfValues("马一般", "马类"),
			"在[npc.her]的腿转变形态时，马一般的粗短毛发迅速生长于其上。"
				+ "新生的毛皮一直延伸到脚趾，[npc.she]忽然大叫一声，原来是脚趾并在了一起，一块厚实的蹄子般的指甲在原处生长出来，"
					+ "迅速将[npc.her]的脚转化为马一般的硬质蹄子。"
				+ "转化结束后，[npc.she]看到大腿处新生的毛发自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]现在拥有人形的[style.boldHorseMorph(马一般的腿和蹄状的脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了拟人的马蹄子。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};

//	public static AbstractLegType HORSE_FISH = new AbstractLegType(BodyCoveringType.FISH_SCALES,
//			Race.HORSE_MORPH,
//			FootStructure.PLANTIGRADE, // FootStructure and Type is for when legs are grown on land.
//			FootType.HUMANOID,
//			"a",
//			"tail",
//			"tails",
//			Util.newArrayListOfValues("masculine", "scaly", "fish"),
//			Util.newArrayListOfValues("feminine", "scaly", "fish"),
//			Util.newArrayListOfValues("horse"),
//			Util.newArrayListOfValues("horse"),
//			Util.newArrayListOfValues("horse"),
//			Util.newArrayListOfValues("horse"),
//			"-",
//			"[npc.Her] fish-like lower body is [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
//			Util.newArrayListOfValues(
//					LegConfiguration.TAIL),
//			false) {
//		@Override
//		public boolean isDefaultPlural(GameCharacter gc) {
//			return gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE);
//		}
//		@Override
//		public String getNameSingular(GameCharacter gc) {
//			if(gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
//				return "leg";
//			} else {
//				return "tail";
//			}
//		}
//		@Override
//		public String getNamePlural(GameCharacter gc) {
//			if(gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
//				return "legs";
//			} else {
//				return "tails";
//			}
//		}
//		@Override
//		public String getTransformationDescription(GameCharacter owner) {
//			return UtilText.parse(owner,
//					"A layer of smooth scales quickly grow over [npc.her] legs as they take on a fish-like appearance."
//					+ " Quickly coming to an end, the transformation leaves [npc.name] with [npc.her] new scales smoothly transitioning into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh."
//					+ "<br/>[npc.Name] now [npc.has] [style.boldHorseMorph(fish-like legs)], which are [npc.materialDescriptor] [npc.legFullDescription].");
//		}
//		@Override
//		public String getTransformName() {
//			return "hippocampus";
//		}
//	};
	
	public static AbstractLegType REINDEER_MORPH = new AbstractLegType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "覆盖毛皮", "驯鹿般拟人"),
			Util.newArrayListOfValues("阴柔", "覆盖毛皮", "驯鹿般拟人"),
			Util.newArrayListOfValues("驯鹿般"),
			Util.newArrayListOfValues("驯鹿般"),
			Util.newArrayListOfValues("驯鹿般"),
			Util.newArrayListOfValues("驯鹿般"),
			"在[npc.her]的腿转变形态时，驯鹿般毛茸茸的毛发迅速生长于其上。"
				+ "新生的毛皮一直延伸到脚趾，[npc.she]忽然大叫一声，原来是脚趾并在了一起，一块新月形分成两瓣的蹄子在原处生长出来，"
					+ "迅速将[npc.her]的脚转化为驯鹿般的蹄子。"
				+ "转化结束后，[npc.she]看到大腿处新生的皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]现在拥有人形的[style.boldReindeerMorph(驯鹿般的腿和蹄状的脚)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，脚却变成了拟人的驯鹿蹄子。",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType HARPY = new AbstractLegType(BodyCoveringType.HARPY_SKIN,
			Race.HARPY,
			FootStructure.DIGITIGRADE,
			FootType.TALONS,
			"一双",
			"腿",
			"腿",
			Util.newArrayListOfValues("阳刚", "带爪", "鸟一般拟人"),
			Util.newArrayListOfValues("阴柔", "带爪", "鸟一般拟人"),
			Util.newArrayListOfValues("鸟一般"),
			Util.newArrayListOfValues("鸟一般"),
			Util.newArrayListOfValues("鸟一般"),
			Util.newArrayListOfValues("鸟一般"),
			"在[npc.her]的腿转变形态时，鸟一般的粗糙皮肤迅速生长于其上。"
				+ "新生的皮质皮肤延伸到脚趾，而[npc.her]的脚则正在经历巨变。"
				+ "[npc.Her]的脚趾聚合在一起，重新化成了三根向前的鸟爪，而第四根拇指样的利爪则位于后方。"
				+ "转化结束后，[npc.she]看到大腿处新生的皮质皮肤迅速过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]现在拥有人形的[style.boldHarpy(鸟一般的腿和替代了脚的利爪)]，[npc.materialDescriptor][npc.legFullDescription]。",
			"[npc.Her]鸟一般的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，自大腿处迅速过渡成了覆盖身体其他部位的[npc.skin]。"
				+ "在每条[npc.legs]的末端，都有着鸟一般锋利的利爪。",
				Util.newArrayListOfValues(
						LegConfiguration.BIPEDAL,
						LegConfiguration.AVIAN), false) {
	};
	
	private static List<AbstractLegType> allLegTypes;
	private static Map<AbstractLegType, String> legToIdMap = new HashMap<>();
	private static Map<String, AbstractLegType> idToLegMap = new HashMap<>();
	
	static {
		allLegTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("leg")) {
					try {
						AbstractLegType type = new AbstractLegType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allLegTypes.add(type);
						legToIdMap.put(type, id);
						idToLegMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("leg")) {
					try {
						AbstractLegType type = new AbstractLegType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allLegTypes.add(type);
						legToIdMap.put(type, id);
						idToLegMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded leg types:
		
		Field[] fields = LegType.class.getFields();
		
		for(Field f : fields){
			if (AbstractLegType.class.isAssignableFrom(f.getType())) {
				
				AbstractLegType ct;
				try {
					ct = ((AbstractLegType) f.get(null));

					legToIdMap.put(ct, f.getName());
					idToLegMap.put(f.getName(), ct);
					
					allLegTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allLegTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractLegType getLegTypeFromId(String id) {
		if(id.equals("IMP")) {
			return LegType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return LegType.WOLF_MORPH;
		}

		id = Util.getClosestStringMatch(id, idToLegMap.keySet());
		return idToLegMap.get(id);
	}
	
	public static String getIdFromLegType(AbstractLegType legType) {
		return legToIdMap.get(legType);
	}
	
	public static List<AbstractLegType> getAllLegTypes() {
		return allLegTypes;
	}
	
	private static Map<AbstractRace, List<AbstractLegType>> typesMap = new HashMap<>();
	public static List<AbstractLegType> getLegTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractLegType> types = new ArrayList<>();
		for(AbstractLegType type : LegType.getAllLegTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}

}