package com.lilithsthrone.game.character.effects;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.LolificationProject.Lexi.character.effects.LexiStatusEffect;
import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.AlcoholLevel;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.ReindeerOverseer;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Units.ValueType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.8.2
 * @author Innoxia
 */
public class StatusEffect {

	// Attribute-related status effects:
	// Strength:
	public static AbstractStatusEffect PHYSIQUE_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"孱弱",
			"attStrength0",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -15f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f)),
			Util.newArrayListOfValues(
					"基础[style.colourUnarmed(徒手伤害)]等于[style.colourMinorGood(体格的20%)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.ZERO_WEAK.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull]弱得令人难以置信。[npc.She]无法用[npc.her]弱小的[npc.arms]造成很大伤害，[npc.her]脆弱的身体特别容易受到物理伤害。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.ZERO_WEAK;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"如常",
			"attStrength1",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			null,
			Util.newArrayListOfValues(
					"基础[style.colourUnarmed(徒手伤害)]等于[style.colourMinorGood(体格的20%)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.ONE_AVERAGE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameHasFull]拥有与[npc.her]体型相当的平均体能水平。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.ONE_AVERAGE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"健壮",
			"attStrength2",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			Util.newArrayListOfValues(
					"基础[style.colourUnarmed(徒手伤害)]等于[style.colourMinorGood(体格的20%)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.TWO_STRONG.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull]比[npc.her]身体表现出来的更加强壮，因而也能够造成更多的物理伤害。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.TWO_STRONG;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"强健",
			"attStrength3",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 20f)),
			Util.newArrayListOfValues(
					"基础[style.colourUnarmed(徒手伤害)]等于[style.colourMinorGood(体格的20%)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.THREE_POWERFUL.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull]比[npc.her]身体表现出来的强壮得多，因而也能够造成大量的物理伤害。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.THREE_POWERFUL;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"威猛",
			"attStrength4",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 30f)),
			Util.newArrayListOfValues(
					"基础[style.colourUnarmed(徒手伤害)]等于[style.colourMinorGood(体格的20%)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.FOUR_MIGHTY.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name]的体格异于常人，几乎没有人在力量上能与[npc.her]匹敌。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.FOUR_MIGHTY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"神力",
			"attStrength5",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50f)),
			Util.newArrayListOfValues(
					"基础[style.colourUnarmed(徒手伤害)]等于[style.colourMinorGood(体格的20%)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.FIVE_HERCULEAN.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的身躯宛若神明；凡人只配俯身仰望！");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.FIVE_HERCULEAN;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};

	// Intelligence:
	public static AbstractStatusEffect INTELLIGENCE_PERK_0_OLD_WORLD = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"无奥术力量",
			"attIntelligence0",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "因为奥术在这个世界中并不存在，所以你的施法能力也不存在。";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.ZERO_AIRHEAD && !Main.game.isInNewWorld();
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"奥术才疏",
			"attIntelligence0",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, -75f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -75f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.GENERIC_TERRIBLE.toWebHexString() + "'>战斗中性欲积蓄满则会直接投降</b>",
					"[style.boldMana("+Attribute.MANA_MAXIMUM.getName()+"的最大值)][style.boldBad(被限制为5)]",
					"[style.boldBad(易受)][style.boldArcane(奥术风暴)]影响")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.ZERO_AIRHEAD.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "尽管你对奥术有着天生的亲和力，但你却莫名其妙地失去了大部分的力量……";
			} else {
				return UtilText.parse(owner, "[npc.NameIsFull]无法以任何主要方式驾驭奥术。这也是这个世界上所有普通种族的典型奥术亲和力水平。");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.ZERO_AIRHEAD && Main.game.isInNewWorld();
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"奥术潜能",
			"attIntelligence1",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 5f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(免疫)][style.boldArcane(奥术风暴)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.ONE_AVERAGE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "你拥有驾驭奥术的天赋，因此，你比御城区的绝大多数人都要强大得多。";
			} else {
				return UtilText.parse(target, "[npc.Name]对如何驾驭奥术有相当的了解；与接受过广泛训练的普通种族相当。");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.ONE_AVERAGE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"奥术精通",
			"attIntelligence2",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_SPELLS, 15f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(免疫)][style.boldArcane(奥术风暴)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.TWO_SMART.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull]精通驾驭奥术，[npc.her]的法术不仅更容易施放，而且伤害更高。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.TWO_SMART;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"超凡奥术",
			"attIntelligence3",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 15f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 15f),
					new Value<>(Attribute.DAMAGE_FIRE, 5f),
					new Value<>(Attribute.DAMAGE_ICE, 5f),
					new Value<>(Attribute.DAMAGE_POISON, 5f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(免疫)][style.boldArcane(奥术风暴)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.THREE_BRAINY.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "你非常精通奥术。你的法术更容易施放，伤害也更高。而且你还拥有少量的元素伤害亲和力。";
			} else {
				return UtilText.parse(target, "[npc.NameIsFull]高度精通奥术。[npc.Her]的法术更容易施放，伤害也更高。而且[npc.she]还有少量元素伤害亲和力。");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.THREE_BRAINY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"奥术大师",
			"attIntelligence4",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 20f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 20f),
					new Value<>(Attribute.DAMAGE_FIRE, 10f),
					new Value<>(Attribute.DAMAGE_ICE, 10f),
					new Value<>(Attribute.DAMAGE_POISON, 10f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(免疫)][style.boldArcane(奥术风暴)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.FOUR_GENIUS.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "你非常精通奥术。你的法术更容易施放，伤害也更高，而且你还拥有相当高的元素伤害亲和力。";
			} else {
				return UtilText.parse(target, "[npc.NameIsFull]非常精通奥术。"
						+ "[npc.her]的法术更容易施放，伤害也更高，而且[npc.she]还具有相当高的元素伤害亲和力。");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.FOUR_GENIUS;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"奥术之光",
			"attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 25f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 25f),
					new Value<>(Attribute.DAMAGE_FIRE, 15f),
					new Value<>(Attribute.DAMAGE_ICE, 15f),
					new Value<>(Attribute.DAMAGE_POISON, 15f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(免疫)][style.boldArcane(奥术风暴)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.FIVE_POLYMATH.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "你驾驭奥术的能力只有莉莉丝能与之匹敌。施法对你来说就像呼吸一样自然。";
			} else {
				return UtilText.parse(owner, "[npc.NamePos]的奥术天赋只有莉莉丝能与之匹敌。施法对[npc.herHim]来说就像呼吸一样自然。");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.FIVE_POLYMATH;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};

	// Corruption:
	public static AbstractStatusEffect CORRUPTION_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"纯洁",
			"attCorruption0",
			PresetColour.CORRUPTION_STAGE_ZERO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, 25f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.ZERO_PURE.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "你完全没有堕落，除了和你爱的人进行最保守的性行为外，你对性一点也不感兴趣。";
			} else {
				return UtilText.parse(owner, "[npc.NameIsFull]没有丝毫堕落，除了和[npc.she]爱的人进行最保守的性行为外，[npc.sheIs]对性一点也不感兴趣。");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.ZERO_PURE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect CORRUPTION_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"寻常",
			"attCorruption1",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, 15f),
					new Value<>(Attribute.DAMAGE_LUST, 5f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.ONE_VANILLA.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "你对随意发生性关系的想法持开放态度，但仍不愿意进行任何极端的性行为。";
			} else {
				return UtilText.parse(owner, "[npc.NameIsFull]对随意发生性关系的想法持开放态度，但仍不愿意进行任何极端的性行为。");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.ONE_VANILLA;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect CORRUPTION_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"肮脏",
			"attCorruption2",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.TWO_HORNY.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "曾经让你感觉不舒服的性行为，现在却成为你幻想的焦点，你迫不及待地想在愿意的伴侣身上尝试这些行为……";
			} else {
				return UtilText.parse(owner, "[npc.Name]总是露出下流的目光，你能感到[npc.her]的视线饥渴地在你全身游走。");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.TWO_HORNY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect CORRUPTION_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"放荡",
			"attCorruption3",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -5f),
					new Value<>(Attribute.DAMAGE_LUST, 30f),
					new Value<>(Attribute.FERTILITY, 25f),
					new Value<>(Attribute.VIRILITY, 25f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.THREE_DIRTY.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				if (owner.hasVagina()) {
					return "借助你脑海中萦绕的幻想的力量，奥术逐渐对你的身体有了实质上的影响，你感觉自己更容易怀孕了……";
				} else if (owner.hasPenis()) {
					return "借助你脑海中萦绕的幻想的力量，奥术逐渐对你的身体有了实质上的影响，"
							+ "你感觉自己更容易让他人受孕了……";
				} else {
					return "借助你脑海中萦绕的幻想的力量，奥术逐渐对你的身体有了实质上的影响，但由于你没有任何性器官，并没有发生什么特别的事情……";
				}
				
			} else {
				if (owner.hasVagina()) {
					return UtilText.parse(owner,
							"借助[npc.namePos]脑海中萦绕的幻想的力量，奥术逐渐对[npc.her]的身体有了实质上的影响，[npc.her]更容易怀孕了……");
				} else if (owner.hasPenis()) {
					return UtilText.parse(owner,
							"借助[npc.namePos]脑海中萦绕的幻想的力量，奥术逐渐对[npc.her]的身体有了实质上的影响，[npc.her]更容易让他人受孕了……");
				} else {
					return UtilText.parse(owner,
							"借助[npc.namePos]脑海中萦绕的幻想的力量，奥术逐渐对[npc.her]的身体有了实质上的影响，"
							+ "但由于[npc.she]没有任何性器官，并没有发生什么特别的事情。");
				}
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.THREE_DIRTY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect CORRUPTION_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"好色",
			"attCorruption4",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f),
					new Value<>(Attribute.DAMAGE_LUST, 40f),
					new Value<>(Attribute.FERTILITY, 50f),
					new Value<>(Attribute.VIRILITY, 50f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.FOUR_LUSTFUL.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				if (owner.hasVagina()) {
					return "脑海中萦绕的淫靡幻想提供了巨大的能量，奥术逐渐对你的身体有了实质上的影响，"
							+"你感觉自己更容易怀孕了……";
				} else if (owner.hasPenis()) {
					return "脑海中萦绕的淫靡幻想提供了巨大的能量，奥术逐渐对你的身体有了实质上的影响，"
							+ "你感觉自己更容易让他人受孕了……";
				} else {
					return "脑海中萦绕的淫靡幻想提供了巨大的能量，奥术逐渐对你的身体有了实质上的影响，"
							+"但由于你没有任何性器官，并没有发生什么特别的事情……";
				}
				
			} else {
				if (owner.hasVagina()) {
					return UtilText.parse(owner,
							"脑海中萦绕的淫靡幻想提供了巨大的能量，奥术逐渐对[npc.namePos]的身体有了实质上的影响，"
							+ "[npc.her]更容易怀孕了……");
				} else if (owner.hasPenis()) {
					return UtilText.parse(owner,
							"脑海中萦绕的淫靡幻想提供了巨大的能量，奥术逐渐对[npc.namePos]的身体有了实质上的影响，"
							+ "[npc.her]更容易让他人受孕了……");
				} else {
					return UtilText.parse(owner,
							"脑海中萦绕的淫靡幻想提供了巨大的能量，奥术逐渐对[npc.namePos]的身体有了实质上的影响，"
							+ "但由于[npc.she]没有性器官，并没有发生什么特别的事情。");
				}
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.FOUR_LUSTFUL;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
			
	public static AbstractStatusEffect CORRUPTION_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"堕落",
			"attCorruption5",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f),
					new Value<>(Attribute.DAMAGE_LUST, 50f),
					new Value<>(Attribute.FERTILITY, 75f),
					new Value<>(Attribute.VIRILITY, 75f)),
			Util.newArrayListOfValues("<b style='color: "+ PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString()+ "'>恶魔思维</b>")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.FIVE_CORRUPT.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]已经彻底堕落，"
						+ (owner.getSubspeciesOverrideRace()==Race.DEMON
							?"这正是恶魔需要的。"
							:"并且急切地想要成为恶魔。")
					+ "脑海中萦绕的淫靡幻想解锁了奥术的全部力量，使得[npc.her]的身体极其适合受孕和授孕。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.FIVE_CORRUPT;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	// Arousal:
	public static AbstractStatusEffect AROUSAL_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"无",
			"attArousal0",
			PresetColour.AROUSAL_STAGE_ZERO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.ZERO_NONE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "你完全没感到快感。";
			else
				return UtilText.parse(target, "[npc.NameIsFull]完全没感到快感。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.ZERO_NONE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> effects = new ArrayList<>();
			if(Main.game.isInSex()) {
				if(Main.sex.isInForeplay(target)) {
					effects.add("[style.colourPinkLight(前戏)]");
					effects.add("[style.colourMinorBad(-50％)]快感获取");
				} else {
					effects.add("[style.colourPink(正戏)]");
					effects.add("[style.colourMinorGood(正常)]快感获取");
					effects.add(UtilText.parse(target, "<i>[npc.nameIsFull]已经高潮过了，因此在这个快感等级下不再需要前戏</i>"));
				}
			}
			return effects;
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"性欲激起",
			"attArousal1",
			PresetColour.AROUSAL_STAGE_ONE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPinkLight(前戏)]",
					"[style.colourMinorBad(-50％)]快感获取")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.ONE_TURNED_ON.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "你的性欲被激发出来了。";
			else
				return UtilText.parse(target, "[npc.NameIsFull]的性欲被激发出来了。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.ONE_TURNED_ON;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return AROUSAL_PERK_0.getExtraEffects(target);
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"非常兴奋",
			"attArousal2",
			PresetColour.AROUSAL_STAGE_TWO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPink(正戏)]",
					"[style.colourMinorGood(正常)]快感获取")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.TWO_EXCITED.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "你感到十分性奋，满脑子都被欲望填满。";
			else
				return UtilText.parse(target, "[npc.NameIsFull]感到十分性奋。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.TWO_EXCITED;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"欲火焚身",
			"attArousal3",
			PresetColour.AROUSAL_STAGE_THREE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPink(正戏)]",
					"[style.colourMinorGood(正常)]快感获取")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.THREE_HEATED.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "燥热起来了。你全心全意投入到了性爱中。";
			else
				return UtilText.parse(target, "[npc.Name]全心全意投入到了性爱中。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.THREE_HEATED;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"激情澎拜",
			"attArousal4",
			PresetColour.AROUSAL_STAGE_FOUR,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPink(正戏)]",
					"[style.colourMinorGood(正常)]快感获取")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.FOUR_PASSIONATE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "你现在只想赶紧高潮。";
			else
				return UtilText.parse(target, "[npc.NameIsFull]现在只想赶紧高潮。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.FOUR_PASSIONATE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"临近高潮",
			"attArousal5",
			PresetColour.AROUSAL_STAGE_FIVE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPink(正戏)]",
					"[style.colourMinorGood(正常)]快感获取")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.FIVE_ORGASM_IMMINENT.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "你感觉到高潮正在来临。你明白再过几秒就要高潮了！";
			else
				return UtilText.parse(owner, "[npc.NameIsFull]感觉到高潮正在来临！");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.FIVE_ORGASM_IMMINENT;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	
	// Lust:
	public static AbstractStatusEffect LUST_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"无",
			"attLust0",
			PresetColour.LUST_STAGE_ZERO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.ZERO_COLD.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.ZERO_COLD.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.ZERO_COLD.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.ZERO_COLD;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"性欲激起",
			"attLust1",
			PresetColour.LUST_STAGE_ONE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.ONE_HORNY.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.ONE_HORNY.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.ONE_HORNY.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.ONE_HORNY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"非常兴奋",
			"attLust2",
			PresetColour.LUST_STAGE_TWO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.TWO_AMOROUS.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.TWO_AMOROUS.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.TWO_AMOROUS.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.TWO_AMOROUS;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"欲火焚身",
			"attLust3",
			PresetColour.LUST_STAGE_THREE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.THREE_LUSTFUL.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.THREE_LUSTFUL.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.THREE_LUSTFUL.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.THREE_LUSTFUL;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"激情澎拜",
			"attLust4",
			PresetColour.LUST_STAGE_FOUR,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.FOUR_IMPASSIONED.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.FOUR_IMPASSIONED.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.FOUR_IMPASSIONED.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.FOUR_IMPASSIONED;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"激情澎拜",
			"attLust5",
			PresetColour.LUST_STAGE_FIVE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.FIVE_BURNING.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.FIVE_BURNING.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.FIVE_BURNING.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.FIVE_BURNING;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	
	
	
	// WEATHER & LOCATION EFFECTS:
	
	public static AbstractStatusEffect WEATHER_PROLOGUE = new AbstractStatusEffect(100,
			"奇怪的天气",
			"weatherNightStormIncoming",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>增强性欲</b>")) {
		@Override
		public String getDescription(GameCharacter target) {
			return "今天晚上，博物馆里弥漫着一种奇怪的气氛，你莫名其妙地发现自己感到难以置信的兴奋……";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInNewWorld();
		}
	};
	
	public static AbstractStatusEffect WEATHER_CLEAR = new AbstractStatusEffect(100,
			"晴天",
			"weatherDayClear",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			if(Main.game.isDayTime()) {
				sb.append("阳光从湛蓝的天空中洒下。");
			} else {
				sb.append("月亮和星星在晴朗的夜空中闪耀");
			}
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append("虽然目前没有风暴的迹象，但[npc.nameIsFull]还是能够吸收背景中的奥术能量为自己充能。");
			} else {
				sb.append("虽然目前没有风暴的迹象，但[npc.name]仍然能感受到奥术的影响，表现为性欲增强。");
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.CLEAR && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayClear();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightClear();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(获得)]能量，来源为[style.colourArcane(奥术)]背景");
			} else {
				exEff.add("[style.colourArcane(增强性欲)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("距离下次[style.colourArcane(奥术风暴)]：");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_CLOUD = new AbstractStatusEffect(100,
			"阴天",
			"weatherDayCloudy",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("天气似乎说变就变，目前阴云密布，有可能会下雨。");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append("虽然目前没有风暴的迹象，但[npc.nameIsFull]还是能够吸收背景中的奥术能量为自己充能。");
			} else {
				sb.append("虽然目前没有风暴的迹象，但[npc.name]仍然能感受到奥术的影响，表现为性欲增强。");
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.CLOUD && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayCloud();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightCloud();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(获得)]能量，来源为[style.colourArcane(奥术)]背景");
			} else {
				exEff.add("[style.colourArcane(增强性欲)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("距离下次[style.colourArcane(奥术风暴)]：");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_RAIN = new AbstractStatusEffect(100,
			"雨天",
			"weatherDayRain",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("头顶的雨云终于破裂，骤然下起了倾盆大雨。");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append("虽然目前没有风暴的迹象，但[npc.nameIsFull]还是能够吸收背景中的奥术能量为自己充能。");
			} else {
				sb.append("虽然目前没有风暴的迹象，但[npc.name]仍然能感受到奥术的影响，表现为性欲增强。");
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.RAIN && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayRain();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightRain();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(获得)]能量，来源为[style.colourArcane(奥术)]背景");
			} else {
				exEff.add("[style.colourArcane(增强性欲)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("距离下次[style.colourArcane(奥术风暴)]：");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_SNOW = new AbstractStatusEffect(100,
			"雪天",
			"weatherDaySnow",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasSnowedThisWinter)) {
				Main.game.getDialogueFlags().values.add(DialogueFlagValue.hasSnowedThisWinter);
				
				if(Main.game.getReindeerOverseers().isEmpty()) {
					try {
						for(int i=0; i<2; i++) {
							Main.game.addNPC(new ReindeerOverseer(Gender.M_P_MALE), false);
						}
						for(int i=0; i<2; i++) {
							Main.game.addNPC(new ReindeerOverseer(Gender.F_V_B_FEMALE), false);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					for(NPC npc : Main.game.getReindeerOverseers()) {
						npc.setRandomLocation(WorldType.DOMINION, PlaceType.DOMINION_STREET, true);
					}
				}
				return "<p>"
							+ "深灰色云层极具压迫感，在御城区的上空盘踞了数小时后终于爆发了。"
							+ "松软的大片雪花缓缓从天而降，尽管最初的冰晶在接触地面后很快便融化，"
								+ "没过多久就在房顶和首都的道路上积起了一层薄薄的白雪。"
						+ "</p>"
						+ "<p>"
							+ "起初看上去不过是一阵小雪，但很快就变成了狂风暴雪，不到一个小时，厚重的白色积雪就逐渐把街道封锁起来。"
							+ "又过了一个小时，白雪已经堆得很高，想在城中穿行都要慢慢悠悠的。"
							+ "几只恶魔想要用奥术火焰融化积雪，清理出一条道路，但雪实在太大，即使是强力的法术也造不成太大影响。"
						+ "</p>"
						+ "<p>"
							+ "就在整个首都似乎即将陷入停顿的时候，微弱的钟声预示着城市救世主的到来。"
							+ "一群驯鹿化形从冻寒之地远道而来，驾临了御城区。"
							+ "分叉的巨大蹄子使得他们在积雪的道路上穿行轻而易举，他们很快就分成数个铲雪小队，"
								+ "前往了城市的不同区域。"
							+ "在几个虎背熊腰的领导带领下，这些驯鹿化形很快就在雪地上开辟出了一条道路。"
						+ "</p>"
						+ "<p>"
							+ "尽管天气没有转好，但在这些值得钦佩的驯鹿化形的努力下，只用了几个小时就清理干净了道路上的积雪。"
							+ "消息很快便传开了，这些访客会一直在御城区待到二月，保证城市能在整个冬日里正常运转后才会离开。"
						+ "</p>";
			} else {
				return "";
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("头顶的乌云终于破裂，骤然下起了鹅毛大雪。");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append("虽然目前没有风暴的迹象，但[npc.nameIsFull]还是能够吸收背景中的奥术能量为自己充能。");
			} else {
				sb.append("虽然目前没有风暴的迹象，但[npc.name]仍然能感受到奥术的影响，表现为性欲增强。");
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.SNOW && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDaySnow();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightSnow();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(获得)]能量，来源为[style.colourArcane(奥术)]背景");
			} else {
				exEff.add("[style.colourArcane(增强性欲)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("距离下次[style.colourArcane(奥术风暴)]：");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM_GATHERING = new AbstractStatusEffect(100,
			"风暴将至",
			"weatherDayStormIncoming",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"一大片厚重乌黑的风暴云在[npc.name]的头顶翻滚着。"
					+ "在其表面之下偶尔可见几道粉色或紫色的能量束，[npc.she]意识到奥术风暴随时可能爆发。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM_GATHERING && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStormIncoming();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStormIncoming();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(获得)]能量，来源为[style.colourArcane(奥术)]背景");
			} else {
				exEff.add("[style.colourArcane(增强性欲)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("距离下次[style.colourArcane(奥术风暴)]：");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM = new AbstractStatusEffect(100,
			"奥术风暴",
			"weatherDayStorm",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
				if(!Main.game.isWeatherInterruptionsEnabled() || (Main.game.getPlayer().getWorldLocation().getWorldRegion()!=WorldRegion.DOMINION && Main.game.getPlayer().getWorldLocation().getWorldRegion()!=WorldRegion.HARPY_NESTS)) {
					return "";
				}
				
				StringBuilder sb = new StringBuilder();
				
				sb.append("<p>"
							+ "一道亮粉色的光芒顿时照亮了整个御城区，让那些仍在街道上逗留的居民不禁抬头望向天。"
							+ "在头顶的高空，威胁的暴风云终于爆发，一团沸腾的奥术能量噼啪作响。"
						+ "</p>"
						+ "<p>"
							+ "很快，一连串无来由的淫荡呻吟和狂喜尖叫便开始在整个城市中回荡，奥术雷霆击穿了那些没有强大灵气保护的人的心灵，"
							+ "他们发现自己满脑子只剩下性爱了。"
						+ "</p>");
				
				WorldRegion region = target.getWorldLocation().getWorldRegion();
				
				if(region!=WorldRegion.DOMINION && region!=WorldRegion.HARPY_NESTS) {
					sb.append("<p>"
							+ "尽管风暴在御城区上方爆发，但并非只会影响这个城市，而是会席卷弗洛伊田野甚至周边的树林和荒野绿地。"
							+ "紫色的闪电在天际划向四面八方，奥术雷霆带来的色情呻吟也接踵而至。"
						+ "</p>");

					if(region==WorldRegion.FIELD_CITY
							|| region==WorldRegion.FIELDS
							|| region==WorldRegion.WOODLAND
							|| region==WorldRegion.RIVER) {
						
						if(target.getLocationPlace().isStormImmune()) {
							sb.append("<p>"
										+ "虽说你能够完全免疫其影响，但你仍能感觉到外面的风暴并不强烈，远远算不上风暴中心。"
										+ "这也意味着你在该区域遇到的人都会更加饥渴，但你认为他们能够控制住自己，抵抗风暴带来的影响。"
									+ "</p>");
						} else {
							sb.append("<p>"
										+ "虽说你会受到其影响，但也只是觉得比平常更性奋一点；这也证明这里的风暴比较弱，远远算不上风暴中心。"
										+ "你立刻意识到在该区域遇到的人肯定会更加饥渴，但你认为他们能够控制住自己，抵抗风暴带来的影响，就和你一样。"
									+ "</p>");
						}
						
					} else {
						sb.append("<p>"
									+ "由于风暴中心正位于御城区，而你的距离足够远，只能听见空气中细微的奥术的呻吟。"
									+ "这也意味着你在该区域遇到的人性欲会稍高一点，但你认为他们能够控制住自己，抵抗风暴带来的影响。"
								+ "</p>");
					}
				}
				
				return sb.toString();
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("奥术风暴在御城区上空肆虐，粉紫交加的巨型闪电撕破了苍穹。");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append("作为奥术驱动的性爱玩偶，[npc.nameIsFull]中充满了巨量能量！");
				
			} else {
				if(target.getWorldLocation().getWorldRegion()!=WorldRegion.DOMINION && target.getWorldLocation().getWorldRegion()!=WorldRegion.HARPY_NESTS) {
					sb.append("[npc.NameIsFull]距离风暴的中心足够遥远，对于其催情的影响完全免疫。");
				} else {
					sb.append("由于与奥术的亲和力，[npc.NamePos]几乎可以完全免疫奥术风暴催情的影响。");
				}
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			GenericPlace targetPlace =
					(target.isElemental() && ((Elemental)target).getSummoner()!=null)
						?((Elemental)target).getSummoner().getLocationPlace()
						:target.getLocationPlace();
			
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					&& Main.game.isInNewWorld()
					&& Main.game.isStarted()
					&& !targetPlace.isStormImmune()
					&& (!target.isVulnerableToArcaneStorm() || (target.getWorldLocation().getWorldRegion()!=WorldRegion.DOMINION && target.getWorldLocation().getWorldRegion()!=WorldRegion.HARPY_NESTS));
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStorm();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStorm();
			}
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				return Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 1000f));
			} else {
				return Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -5f));
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(获得)][style.colourExcellent(大量)]能量，来源为[style.colourArcane(奥术风暴)]");
			} else {
				exEff.add("[style.colourArcane(增强性欲)]");
				exEff.add("[style.boldExcellent(双倍)]性爱&战斗的[style.colourArcane(精华获取)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("持续到[style.colourArcane(风暴结束)]:");
				exEff.add(Main.game.getWeatherTimeRemainingAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM_VULNERABLE = new AbstractStatusEffect(100,
			"奥术风暴",
			"weatherDayStorm",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
				if(!Main.game.isWeatherInterruptionsEnabled() || (Main.game.getPlayer().getWorldLocation().getWorldRegion()!=WorldRegion.DOMINION && Main.game.getPlayer().getWorldLocation().getWorldRegion()!=WorldRegion.HARPY_NESTS)) {
					return "";
				}
				
				return "<p>"
							+ "一道亮粉色的光芒顿时照亮了整个御城区，让那些仍在街道上逗留的居民不禁抬头望向天。"
							+ "在头顶的高空，威胁的暴风云终于爆发，一团沸腾的奥术能量噼啪作响。"
						+ "</p>"
						+ "<p>"
							+ "很快，一连串无来由的淫荡呻吟和狂喜尖叫便开始在整个城市中回荡。"
							+ "尽管你来到这个世界时拥有很强的奥术亲和力，足以免疫风暴的影响，但你还是失去了不少力量，"
								+ "你感到自己欲火焚身，情不自禁地发出一声[pc.moan]。"
						+ "</p>"
						+ "<p>"
							+ "你继续赶路，忽然觉得自己想要一头撞进某个人怀里，让他好好给你来一发……"
						+ "</p>";
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("奥术风暴在御城区上空肆虐，粉紫交加的巨型闪电撕破了苍穹。");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append("作为奥术驱动的性爱玩偶，[npc.nameIsFull]中充满了巨量能量！");
				
			} else {
				sb.append("[npc.NameIsFull]受到正在肆虐的奥术风暴的严重影响，满脑子只有性爱了……");
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			GenericPlace targetPlace =
					(target.isElemental() && ((Elemental)target).getSummoner()!=null)
						?((Elemental)target).getSummoner().getLocationPlace()
						:target.getLocationPlace();
			
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					&& Main.game.isInNewWorld()
					&& Main.game.isStarted()
					&& target.isVulnerableToArcaneStorm()
					&& (!targetPlace.isStormImmune() && !target.isProtectedFromArcaneStorm())
					&& (target.getWorldLocation().getWorldRegion()==WorldRegion.DOMINION || target.getWorldLocation().getWorldRegion()==WorldRegion.HARPY_NESTS);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStorm();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStorm();
			}
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.HEALTH_MAXIMUM, 1000f));
			} else {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.RESISTANCE_LUST, -100f),
						new Value<>(Attribute.RESTING_LUST, 50f));
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(获得)][style.colourExcellent(大量)]能量，来源为[style.colourArcane(奥术风暴)]");
			} else {
				exEff.add("[style.colourArcane(增强性欲)]");
				exEff.add("[style.colourArcane(性欲超载)]");
				exEff.add("[style.boldExcellent(双倍)]性爱&战斗的[style.colourArcane(精华获取)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("持续到[style.colourArcane(风暴结束)]:");
				exEff.add(Main.game.getWeatherTimeRemainingAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM_PROTECTED = new AbstractStatusEffect(100,
			"奥术风暴(被防护)",
			"weatherDayStorm",
			PresetColour.GENERIC_GOOD,
			true,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
				if(!Main.game.isWeatherInterruptionsEnabled() || (Main.game.getPlayer().getWorldLocation().getWorldRegion()!=WorldRegion.DOMINION && Main.game.getPlayer().getWorldLocation().getWorldRegion()!=WorldRegion.HARPY_NESTS)) {
					return "";
				}
				
				return "<p>"
							+ "一道亮粉色的光芒顿时照亮了整个御城区，让那些仍在街道上逗留的居民不禁抬头望向天。"
							+ "在头顶的高空，威胁的暴风云终于爆发，一团沸腾的奥术能量噼啪作响。"
						+ "</p>"
						+ "<p>"
							+ "很快，一连串无来由的淫荡呻吟和狂喜尖叫便开始在整个城市中回荡，奥术雷霆击穿了那些没有强大灵气保护的人的心灵，"
							+ "他们发现自己满脑子只剩下性爱了……"
						+ "</p>";
			} else {
				return "";
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("奥术风暴在御城区上空肆虐，粉紫交加的巨型闪电撕破了苍穹。");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append("尽管[npc.nameIsFull]得到了保护，但仍能还是能够吸收背景中的奥术能量为自己充能。");
				
			} else {
				sb.append("尽管[npc.nameIsFull]仍能感受到风暴的影响，表现为性欲增强，但风暴的威力绝大部分都被防护住了。");
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			GenericPlace targetPlace =
					(target.isElemental() && ((Elemental)target).getSummoner()!=null)
						?((Elemental)target).getSummoner().getLocationPlace()
						:target.getLocationPlace();
			
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					&& Main.game.isInNewWorld()
					&& Main.game.isStarted()
					&& (targetPlace.isStormImmune() || target.isProtectedFromArcaneStorm());
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStormProtected();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStormProtected();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(获得)]能量，来源为[style.colourArcane(奥术)]背景");
			} else {
				exEff.add("[style.colourArcane(增强性欲)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("持续到[style.colourArcane(风暴结束)]:");
				exEff.add(Main.game.getWeatherTimeRemainingAsTimeString());
			}
			return exEff;
		}
	};

	public static AbstractStatusEffect BLINDED = new AbstractStatusEffect(90,
			"致盲",
			"blinded",
			PresetColour.BASE_BLACK,
			PresetColour.BASE_RED,
			PresetColour.BASE_GREY_LIGHT,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, -50f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, -50f),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -50f),
					new Value<>(Attribute.DAMAGE_SPELLS, -50f),
					new Value<>(Attribute.ACTION_POINTS, -1f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"[npc.NameHasFull]已经被致盲，[npc.she]努力在四周寻找正确的位置。该状态下[npc.she]将很难有效地战斗！");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSightHindered() && !target.hasEchoLocation();
		}
	};

	public static AbstractStatusEffect BLINDED_NEGATED = new AbstractStatusEffect(90,
			"致盲(回声定位)",
			"blinded_negated",
			PresetColour.BASE_BLACK,
			PresetColour.BASE_GREEN,
			PresetColour.BASE_GREY_LIGHT,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, -5f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, -5f),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -5f),
					new Value<>(Attribute.DAMAGE_SPELLS, -5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"虽然[npc.name]被致盲了，但[npc.her]依然能够通过回声定位能力有效的探知周边环境！");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSightHindered() && target.hasEchoLocation();
		}
	};

	public static AbstractStatusEffect DARKNESS = new AbstractStatusEffect(90,
			"黑暗",
			"darkness",
			PresetColour.BASE_BLACK,
			PresetColour.BASE_RED,
			PresetColour.BASE_GREY_LIGHT,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, -25f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, -25f),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -25f),
					new Value<>(Attribute.DAMAGE_SPELLS, -25f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"[npc.name]正在探索的区域非常黑，而且[npc.she]没有照明手段，[npc.she]正在努力寻找[npc.she]要去的地方！");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isInDarkness();
		}
	};

	public static AbstractStatusEffect DARKNESS_NEGATED = new AbstractStatusEffect(90,
			"黑暗(无效)",
			"darkness_negated",
			PresetColour.BASE_BLACK,
			PresetColour.BASE_GREEN,
			PresetColour.BASE_GREY_LIGHT,
			true,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues()) {
		@Override
		public EffectBenefit getBeneficialStatus() {
			return EffectBenefit.NEUTRAL;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"虽然[npc.Name]正在通过的区域十分昏暗，但[npc.she]却能够看清，[#npc.getDescriptionInDarkness()]。");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getCell().isDark() && !target.isInDarkness();
		}
	};


	public static AbstractStatusEffect SHORT_SIGHTED = new AbstractStatusEffect(90,
			"视力障碍",
			"short_sighted",
			PresetColour.BASE_BLACK,
			PresetColour.GENERIC_TERRIBLE,
			PresetColour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, -1f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -50f),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -50f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"[npc.Name]不佩戴矫正眼镜的话会看不清东西……");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasPerkAnywhereInTree(Perk.SPECIAL_SHORT_SIGHTED)
					&& !target.isDoll()
					&& !target.isPerfectVision()
					&& !target.hasClothingWithTag(ItemTag.PRESCRIPTION_GLASSES, true, false);
		}
	};
	public static AbstractStatusEffect ENCHANCED_VISION = new AbstractStatusEffect(90,
			"视力提高",
			"perfect_vision",
			PresetColour.BASE_BLACK,
			PresetColour.GENERIC_EXCELLENT,
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 5f),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.hasPerkAnywhereInTree(Perk.SPECIAL_SHORT_SIGHTED)) {
					return UtilText.parse(target,
							"多亏了[npc.namePos]眼镜上附魔的矫正镜片，不仅解决了[npc.her]的视力障碍问题，还有效提升了[npc.her]的视力！");
				}
				return UtilText.parse(target,
						"虽然[npc.name]没有任何视力障碍，但[npc.her]眼镜上附魔的矫正镜片仍有效增强了[npc.her]的视力！");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasClothingWithTag(ItemTag.PRESCRIPTION_GLASSES, true, false);
		}
	};
	
	// RACES:
	// HUMAN:
	public static AbstractStatusEffect PURE_HUMAN_PROLOGUE = new AbstractStatusEffect(1000,
			"人类",
			null,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "你是人类，就像世界上其他人一样。";
			else
				return "[npc.NameIsFull]是一个人类，就像这个世界上的其他人一样。";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.HUMAN
					&& target.getRaceStage() == RaceStage.HUMAN
					&& !Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return owner.getSubspecies().getSVGString(owner);
		}
	};
	
	public static AbstractStatusEffect SUBSPECIES_BONUS = new AbstractStatusEffect(1000,
			"",
			null,
			PresetColour.CLOTHING_WHITE,
			true,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			if(target.isRaceConcealed()) {
				return "亚种隐藏增益";
			}
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				return target.getSubspeciesOverride().getName(null)+"("+target.getSubspecies().getName(target.getBody())+")";
			}
			return (target.isFeral()?"[style.colourFeral(兽态)]":"")+target.getSubspecies().getName(target.getBody());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isRaceConcealed()) {
				return UtilText.parse(target, "虽然[npc.name]隐藏了种族，但仍能受到属性修正的增益……");
			}
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				return target.getSubspeciesOverride().getStatusEffectDescription(target);
			} else {
				return target.getSubspecies().getStatusEffectDescription(target);	
			}
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			List<Value<Integer, String>> additionalDescriptions = new ArrayList<>();
			
			// Add subspecies appearance change:
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				String subspeciesName = target.getSubspecies().getName(target.getBody());
				additionalDescriptions.add(
						new Value<>(1,
								UtilText.parse(target,
										"[npc.NameIsFull]"
										+"<span style='color:"+target.getSubspeciesOverride().getColour(target).toWebHexString()+";'>"+UtilText.addDeterminer(target.getSubspeciesOverride().getName(null))+"</span>"
										+ "但[npc.she]看上去"
										+"<span style='color:"+target.getSubspecies().getColour(target).toWebHexString()+";'>"+UtilText.addDeterminer(subspeciesName)+"</span>！")));
			}

			// Add body material modifiers:
			BodyMaterial material = target.getBodyMaterial();
			if(material.getAttributeModifiers(target)!=null
					|| material.getExtraEffects(target)!=null) {
				int lineHeight = 1;
				StringBuilder sb = new StringBuilder();
				sb.append(UtilText.parse(target, "<i style='color:"+material.getColour().toWebHexString()+";'>[npc.NamePos]的"+material.getName()+"身躯给予[npc.herHim]:</i>"));
				if(material.getAttributeModifiers(target)!=null) {
					for(String s : attributeModifiersToStringList(material.getAttributeModifiers(target))) {
						sb.append("<br/>"+s);
						lineHeight++;
					}
				}
				if(material.getExtraEffects(target)!=null) {
					for(String s : material.getExtraEffects(target)) {
						sb.append("<br/>"+s);
						lineHeight++;
					}
				}
				additionalDescriptions.add(new Value<>(lineHeight, sb.toString()));
			}
			
			return additionalDescriptions;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInNewWorld();
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			LinkedHashMap<AbstractAttribute, Float> attMods;
			
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				attMods = new LinkedHashMap<>(target.getSubspeciesOverride().getStatusEffectAttributeModifiers(target));
			} else {
				attMods = new LinkedHashMap<>(target.getSubspecies().getStatusEffectAttributeModifiers(target));
			}
			
			BodyMaterial material = target.getBodyMaterial();
			if(material.getAttributeModifiers(target)!=null) {
				for(Entry<AbstractAttribute, Float> entry : material.getAttributeModifiers(target).entrySet()) {
					attMods.putIfAbsent(entry.getKey(), 0f);
					attMods.put(entry.getKey(), attMods.get(entry.getKey())+entry.getValue());
				}
			}
			
			return attMods;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				return target.getSubspeciesOverride().getExtraEffects(target);
			}
			return target.getSubspecies().getExtraEffects(target);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			LinkedHashMap<AbstractAttribute, Float> attMods;

			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				attMods = new LinkedHashMap<>(target.getSubspeciesOverride().getStatusEffectAttributeModifiers(target));
			} else {
				attMods = new LinkedHashMap<>(target.getSubspecies().getStatusEffectAttributeModifiers(target));
			}
			
			ArrayList<String> fullModList = new ArrayList<>(attributeModifiersToStringList(attMods));
			fullModList.addAll(getExtraEffects(target));
			
			return fullModList;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isRaceConcealed()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getRaceUnknown();
			}
			return owner.getSubspecies().getSVGString(owner);
		}
	};

	public static AbstractStatusEffect AQUATIC_TAIL_POSITIVE = new AbstractStatusEffect(90,
			"如鱼得水",
			"aquatic_positive",
			PresetColour.GENERIC_GOOD,
			PresetColour.BASE_BLUE_LIGHT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, 1f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 15f),
					new Value<>(Attribute.ENERGY_SHIELDING, 5f)),
			Util.newArrayListOfValues(
					"[style.boldBlueLight(失去腿部)]")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			if(!target.isPlayer()) {
				return "";
			}
			return "随着你越来越接近附近的水体，你感到一股强烈的刺痛，沿着腿一路向上。"
					+ "你的肌肉没有任何征兆，便不由自主地痉挛起来，腿部逐渐并紧，令你不禁惊呼起来。"
					+ "还没等你反应过来，你的腿便融在一起，发生了转化，转瞬之间你的下半身就变成了鱼尾的样子！"
					+ "<p style='text-align:center;'>"
						+ "[style.italicsMinorBad(你不再能穿着腿和脚栏位的衣物了！)]"
					+ "</p>"
					+ target.postTransformationCalculation(); // To handle clothing removals
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"由于[npc.nameIsFull]是[npc.a_race]，并且能够接触到附近的水体，[npc.her]的下半身转化为了鱼尾的样子，让[npc.herHim]觉得非常舒适！");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getCell().getAquatic().isWater()
					&& target.getLegConfiguration()==LegConfiguration.TAIL
					&& target.getSubspecies().isAquatic(target);
		}
	};

	public static AbstractStatusEffect AQUATIC_TAIL_NEGATIVE = new AbstractStatusEffect(90,
			"离水之鱼",
			"aquatic_negative",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_TAN,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, -1f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -15f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues(
					"[style.boldTan(生出双腿)]")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			if(!target.isPlayer()) {
				return "";
			}
			return "当你发现自己身处一个附近没有大型水体的区域时，你感觉到自己的下半身开始刺痛。"
					+ "在对即将发生的事情没有任何预兆的情况下，你的肌肉不由自主地绷紧，导致你发出一声惊叫。"
					+ "还没等你反应过来，你的尾巴便迅速分开，转化为一双腿，这使你能在陆地上行走和奔跑，这种感觉对你来说很陌生。"
					+ "<p style='text-align:center;'>"
						+ "[style.italicsMinorGood(你现在可以穿着腿和脚栏位的衣物了！)]"
					+ "</p>"
					+ target.postTransformationCalculation(); // To handle clothing checks
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"由于[npc.nameIsFull]是[npc.a_race]，并且附近没有大型水体，[npc.her]的下半身转化为一双腿，这让[npc.herHim]觉得很难受！");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.getCell().getAquatic().isWater()
					&& target.getLegConfiguration()==LegConfiguration.TAIL
					&& target.getSubspecies().isAquatic(target);
		}
	};
	
	public static AbstractStatusEffect AQUATIC_POSITIVE = new AbstractStatusEffect(90,
			"如鱼得水",
			"aquatic_positive",
			PresetColour.GENERIC_GOOD,
			PresetColour.BASE_BLUE_LIGHT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 15f),
					new Value<>(Attribute.ENERGY_SHIELDING, 5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"由于[npc.nameIsFull]是[npc.a_race]，并且能够接触到附近的水体，这让[npc.herHim]觉得非常舒适！");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getCell().getAquatic().isWater()
					&& target.getLegConfiguration()!=LegConfiguration.TAIL
					&& target.getSubspecies().isAquatic(target);
		}
	};

	public static AbstractStatusEffect AQUATIC_NEGATIVE = new AbstractStatusEffect(90,
			"离水之鱼",
			"aquatic_negative",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_TAN,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -15f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"由于[npc.nameIsFull]是[npc.a_race]，并且附近没有大型水体，这让[npc.herHim]觉得很难受！");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.getCell().getAquatic().isWater()
					&& target.getLegConfiguration()!=LegConfiguration.TAIL
					&& target.getSubspecies().isAquatic(target);
		}
	};

//	public static AbstractStatusEffect OCCUPATION_PERK = new AbstractStatusEffect(1000,
//			"",
//			null,
//			PresetColour.CLOTHING_WHITE,
//			true,
//			null,
//			null) {
//		@Override
//		public String getName(GameCharacter target) {
//			return target.getOccupation().getAssociatedPerk().getName(target);
//		}
//		@Override
//		public String getDescription(GameCharacter target) {
//			return target.getOccupation().getAssociatedPerk().getDescription(target);
//		}
////		@Override
////		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
////			List<Value<Integer, String>> additionalDescriptions = new ArrayList<>();
////			
////			return additionalDescriptions;
////		}
//		@Override
//		public boolean isConditionsMet(GameCharacter target) {
//			return !target.isPlayer() || Main.game.isInNewWorld(); //TODO
//		}
//		@Override
//		public List<String> getModifiersAsStringList(GameCharacter target) {
//			List<String> extraModifiersList = new ArrayList<>();
//			return extraModifiersList;
//		}
//		@Override
//		public String getSVGString(GameCharacter owner) {
//			return owner.getOccupation().getAssociatedPerk().getSVGString(owner);
//		}
//	};
	
	// SEXUAL ORIENTATIONS:
	
	public static AbstractStatusEffect ORIENTATION_ANDROPHILIC = new AbstractStatusEffect(90,
			"男性恋",
			"orientation_androphilic",
			PresetColour.MASCULINE,
			true,
			null,
			Util.newArrayListOfValues(
					"从[style.colourFeminine(女性外貌对手)]受到的[style.colourDmgLust(性欲伤害)][style.colourGood(-50%)]",
					"对[style.colourFeminine(女性外貌对手)]造成的[style.colourDmgLust(性欲伤害)][style.colourBad(-50%)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull]在性取向上被男性化个体所吸引，对女性化个体完全没有性欲。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getSexualOrientation()==SexualOrientation.ANDROPHILIC;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ORIENTATION_GYNEPHILIC = new AbstractStatusEffect(90,
			"女性恋",
			"orientation_gynephilic",
			PresetColour.FEMININE,
			true,
			null,
			Util.newArrayListOfValues(
					"从[style.colourMasculine(男性外貌对手)]受到的[style.colourDmgLust(性欲伤害)][style.colourGood(-50%)]",
					"对[style.colourMasculine(男性外貌对手)]造成的[style.colourDmgLust(性欲伤害)][style.colourBad(-50%)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull]在性取向上被女性化个体所吸引，对男性化个体完全没有性欲。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getSexualOrientation()==SexualOrientation.GYNEPHILIC;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	public static AbstractStatusEffect ORIENTATION_AMBIPHILIC = new AbstractStatusEffect(90,
			"双性恋",
			"orientation_ambiphilic",
			PresetColour.ANDROGYNOUS,
			true,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull]在性取向上对男女都来者不拒，不会被对方的女性化程度影响性欲。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getSexualOrientation()==SexualOrientation.AMBIPHILIC;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	

	// CLOTHING:

	public static AbstractStatusEffect CLOTHING_FEMININITY = new AbstractStatusEffect(85,
			"衣着过于女性化",
			"clothingFemininity",
			PresetColour.CLOTHING_PINK_LIGHT,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.namePos]的部分衣着对于其男性的外表来说过于女性化了。"
						+ "[npc.She]觉得穿上这些衣物非常尴尬，头脑很不清醒。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.hasFetish(Fetish.FETISH_CROSS_DRESSER)
					|| target.hasPerkAnywhereInTree(Perk.SPECIAL_CLOTHING_FEMININITY_INDIFFERENCE)
					|| target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)) {
				return false;
			}
			for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if(c.getClothingType().getFemininityMinimum() > target.getFemininityValue()) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_MASCULINITY = new AbstractStatusEffect(85,
			"衣着过于男性化",
			"clothingMasculinity",
			PresetColour.CLOTHING_BLUE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.namePos]的部分衣着对于其女性的外表来说过于男性化了。"
						+ "[npc.She]觉得穿上这些衣物非常尴尬，头脑很不清醒。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.hasFetish(Fetish.FETISH_CROSS_DRESSER)
					|| target.hasPerkAnywhereInTree(Perk.SPECIAL_CLOTHING_MASCULINITY_INDIFFERENCE)
					|| target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)) {
				return false;
			}
			for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if(c.getClothingType().getFemininityMaximum() < target.getFemininityValue()) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_CUM = new AbstractStatusEffect(80,
			"肮脏衣物",
			"clothingCummedIn",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 5f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)) {
				return UtilText.parse(target,
						"[npc.namePos]的一些衣物已经被精液，乳汁或其他淫秽液体所覆盖。"
							+ "穿着如此下流的衣物只能说明[npc.sheIs]是一个不折不扣的性玩具。");
			}
			return UtilText.parse(target,
					"[npc.namePos]的一些衣物已经被精液，乳汁或其他淫秽液体所覆盖。"
						+ "[npc.SheIs]觉得穿着如此污秽的衣物走在路上非常尴尬。");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br/>你气味浓郁的衣服似乎会召来麻烦。");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!isCumEffectPositive(target)) {
				for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
					if (c.isDirty()
							&& Collections.disjoint(
									c.getItemTags(),
									Util.newArrayListOfValues(ItemTag.PLUGS_ANUS, ItemTag.SEALS_ANUS, ItemTag.PLUGS_VAGINA, ItemTag.SEALS_VAGINA, ItemTag.PLUGS_NIPPLES, ItemTag.SEALS_NIPPLES))) {
						return true;
					}
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_CUM_MASOCHIST = new AbstractStatusEffect(80,
			"肮脏衣物",
			"clothingCummedInMasochist",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 2f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "你的一些衣物已经被精液，乳汁或其他淫秽液体所覆盖。"
						+ "你穿着如此污秽的衣物走在路上，觉得非常性奋。";
			} else {
				return UtilText.parse(target, "[npc.namePos]的一些衣物已经被精液，乳汁或其他淫秽液体所覆盖。"
						+ "[npc.sheIs]穿着如此污秽的衣物走在路上，觉得非常性奋。");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br/>你气味浓郁的衣服似乎会召来麻烦。");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
					if (c.isDirty()
							&& Collections.disjoint(
									c.getItemTags(),
									Util.newArrayListOfValues(ItemTag.PLUGS_ANUS, ItemTag.SEALS_ANUS, ItemTag.PLUGS_VAGINA, ItemTag.SEALS_VAGINA, ItemTag.PLUGS_NIPPLES, ItemTag.SEALS_NIPPLES))) {
						return true;
					}
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect BODY_CUM = new AbstractStatusEffect(80,
			"肮脏的身体",
			"dirtyBody",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 5f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			List<InventorySlot> slotsToClean = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			for(AbstractClothing clothing : new ArrayList<>(target.getClothingCurrentlyEquipped())) {
				if(target.getDirtySlots().contains(clothing.getSlotEquippedTo())) {
					InventorySlot slotEquippedTo = clothing.getSlotEquippedTo();
					Set<ItemTag> tags = clothing.getItemTags();
					slotsToClean.add(slotEquippedTo);
					
					boolean seals = tags.contains(ItemTag.SEALS_ANUS)
										|| tags.contains(ItemTag.SEALS_VAGINA)
										|| tags.contains(ItemTag.SEALS_NIPPLES);
					
					if(clothing.getSlotEquippedTo()==InventorySlot.ANUS && (tags.contains(ItemTag.SEALS_ANUS) || tags.contains(ItemTag.PLUGS_ANUS))
							|| clothing.getSlotEquippedTo()==InventorySlot.VAGINA && (tags.contains(ItemTag.SEALS_VAGINA) || tags.contains(ItemTag.PLUGS_VAGINA))
							|| clothing.getSlotEquippedTo()==InventorySlot.NIPPLE && (tags.contains(ItemTag.SEALS_NIPPLES) || tags.contains(ItemTag.PLUGS_NIPPLES))) {
						if(sb.length()>0) {
							sb.append("<br/>");
						}
						sb.append("你使用了<b>"+clothing.getDisplayName(true)+"</b>来清理"+clothing.getSlotEquippedTo().getName()
								+(seals
										?"当你装备时"
										:"当你将其插入腔穴时")
								+"。");
						
					} else {
						if(!clothing.isDirty()) {
							clothing.setDirty(target, true);
							if(sb.length()>0) {
								sb.append("<br/>");
							}
							sb.append("你使用了<b>"+clothing.getDisplayName(true)+"</b>来清理"+clothing.getSlotEquippedTo().getName()
									+"，<b style='color:"+PresetColour.CUM.toWebHexString()+";'>在这过程中也弄脏了它</b>。");
						}
					}
					
				} else {
					for(InventorySlot blockedSlot : clothing.getIncompatibleSlots(target, clothing.getSlotEquippedTo())) {
						if(target.getDirtySlots().contains(blockedSlot)) {
							slotsToClean.add(blockedSlot);
							if(!clothing.isDirty()) {
								clothing.setDirty(target, true);
								if(sb.length()>0) {
									sb.append("<br/>");
								}
								sb.append("你使用了<b>"+clothing.getDisplayName(true)+"</b>来清理"+clothing.getSlotEquippedTo().getName()
										+"，<b style='color:"+PresetColour.CUM.toWebHexString()+";'>在这过程中也弄脏了它</b>。");
							}
						}
					}
				}
			}
			for(InventorySlot slotToClean : slotsToClean) {
				target.removeDirtySlot(slotToClean, false);
			}
			
			if(target.isPlayer()) {
				return sb.toString();
			}
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)) {
				return UtilText.parse(target,
						"[npc.namePos]身体的某些部位已经被精液、乳汁或其他淫秽液体所覆盖。"
							+ "处于如此淫秽的状态只能说明[npc.sheIs]是一个不折不扣的性玩具。");
			}
			return UtilText.parse(target, "[npc.namePos]身体的某些部位被精液、乳汁或其他淫秽液体所覆盖。"
					+ "身体如此污秽的情况下走在路上，让[npc.sheIs]觉得非常尴尬。");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br/>你气味浓郁的躯体似乎会召来麻烦。");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (!isCumEffectPositive(target)) && !target.getDirtySlots().isEmpty();
		}
	};
	
	public static AbstractStatusEffect BODY_CUM_MASOCHIST = new AbstractStatusEffect(80,
			"肮脏的身体",
			"dirtyBodyMasochist",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 2f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return StatusEffect.BODY_CUM.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "你身体的某些部位被精液、乳汁或其他淫秽液体所覆盖。"
						+ "身体如此污秽的情况下走在路上，让你觉得非常性奋。";
			} else {
				return UtilText.parse(target, "[npc.namePos]身体的某些部位被精液、乳汁或其他淫秽液体所覆盖。"
						+ "身体如此污秽的情况下走在路上，让[npc.sheIs]觉得非常性奋。");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br/>你气味浓郁的躯体似乎会召来麻烦。");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (isCumEffectPositive(target)) && !target.getDirtySlots().isEmpty();
		}
	};
	
	public static AbstractStatusEffect MARKED_BY_MUSK = new AbstractStatusEffect(80,
			"被淫味标记",
			"marked_by_musk",
			PresetColour.BASE_YELLOW_LIGHT,
			PresetColour.BASE_ORANGE_LIGHT,
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESTING_LUST, 5f),
					new Value<>(Attribute.RESISTANCE_LUST, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.getMuskMarkerCharacters().isEmpty()) {
				return UtilText.parse(target,
						"[npc.NameHasFull]身上有种令人陶醉的浓烈气味，令[npc.herself]也遭气息煽动着，不由自主地感到性奋。");
			} else {
				return UtilText.parse(target,
						"[npc.NameHasFull]身上有着"+Util.charactersToStringListOfNames(target.getMuskMarkerCharacters(), true)
							+"的浓烈气味，令[npc.herself]也遭气息煽动着，不由自主地感到性奋。");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			return new Value<>(1, "[style.italicsAqua(洗个澡，做个水疗，或者用奥术清洁都能移除气味。)]");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isMuskContentEnabled() && !target.getMuskMarkers().isEmpty();
		}
	};
	
	public static AbstractStatusEffect CLOTHING_ENCHANTMENT_OVER_LIMIT = new AbstractStatusEffect(80,
			"附魔不稳定",
			"unstable_enchantment_1",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 25f)),
			Util.newArrayListOfValues("[style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+"的最大值)][style.boldMinorBad(-10%)]",
					"[style.boldMana("+Attribute.MANA_MAXIMUM.getName()+"的最大值)][style.boldMinorBad(-10%)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull]无法驾驭被注入[npc.her]的武器、衣物、纹身上的属性附魔，正在承受部分副作用。"
							+ "如果[npc.she]继续装备附魔物品，情况只会变得更糟……");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float overBy = (target.getEnchantmentPointsUsedTotal()/ (float) target.getAttributeValue(Attribute.ENCHANTMENT_LIMIT));
			return Main.game.isEnchantmentCapacityEnabled() && overBy>1f && overBy<1.1f;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_ENCHANTMENT_OVER_LIMIT_2 = new AbstractStatusEffect(80,
			"附魔震荡",
			"unstable_enchantment_2",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 50f)),
			Util.newArrayListOfValues("[style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+"的最大值)][style.boldBad(-50%)]",
					"[style.boldMana("+Attribute.MANA_MAXIMUM.getName()+"的最大值)][style.boldBad(-50%)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull]无法驾驭被注入[npc.her]的武器、衣物、纹身上的属性附魔，正在承受比较严重的副作用。"
							+ "如果[npc.she]继续装备附魔物品，情况只会变得更糟……");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float overBy = (target.getEnchantmentPointsUsedTotal()/ (float) target.getAttributeValue(Attribute.ENCHANTMENT_LIMIT));
			return Main.game.isEnchantmentCapacityEnabled() && overBy>1.1f && overBy<1.2f;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_ENCHANTMENT_OVER_LIMIT_3 = new AbstractStatusEffect(80,
			"附魔破碎",
			"unstable_enchantment_3",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 100f)),
			Util.newArrayListOfValues("[style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+"的最大值)][style.boldTerrible(设为1)]",
					"[style.boldMana("+Attribute.MANA_MAXIMUM.getName()+"的最大值)][style.boldTerrible(设为1)]",
					"[style.boldTerrible(所有护盾数值归零)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull]无法驾驭被注入[npc.her]的武器、衣物、纹身上的属性附魔，正在承受极其严重的副作用。");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float overBy = (target.getEnchantmentPointsUsedTotal()/ (float) target.getAttributeValue(Attribute.ENCHANTMENT_LIMIT));
			return Main.game.isEnchantmentCapacityEnabled() && overBy>1.2f;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_JINXED = new AbstractStatusEffect(80,
			"封印衣物",
			"jinxed_clothing",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"在[npc.namePos]被封印的衣物上的附魔天然能够吸取能量，[npc.she]感觉得到自己的灵气流失了一部分……");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer()) {
				if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.jinxedClothingDiscovered)) {
					Main.game.getDialogueFlags().values.add(DialogueFlagValue.jinxedClothingDiscovered);
					AbstractClothing clothing = null;
					for(AbstractClothing c : new ArrayList<>(target.getClothingCurrentlyEquipped())) {
						if(c.isSealed()) {
							clothing = c;
							break;
						}
					}
					if(target.isPlayer() && !((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
						return "<p>"
									+ "在你将"+clothing.getName()+"穿戴到位后，忽然感到其表面辐射出一阵奇怪的暖流。"
									+ "你认定这是上面的奥术附魔搞的鬼，有些许不安，于是立刻尝试"
										+" 脱下来。"
								+ "</p>"
								+ "<p>"
									+ "抓住"+clothing.getName()+"的时候，似乎没有什么不对劲，但正要脱下来的时候，你才意识到自己犯了个错误。"
									+ "一串奥术能量顿时穿过你的身体，仿佛直接射入你的脑海，你不由自主地松开了手。"
								+ "</p>"
								+ "<p>"
									+ "你紧咬牙关，再次尝试除掉这件烦人的衣物，但在你想要脱下的那一刻，立刻就没了力气。"
									+ "无论你如何努力，最多也只能将"+clothing.getName()
										+"稍微移开一点，每当快要从身体上移除时，它都会自动返回贴身的位置。"
								+ "</p>"
								+ "<p>"
									+ "你最终放弃了，还是去问问莉莱雅这"
										+"<b style='color:"+PresetColour.RARITY_JINXED.toWebHexString()+";'>被封印的</b>"+clothing.getName()+"是怎么回事吧。"
									+ "或许她知道如何打破封印？"
								+ "</p>"
								+(!((PlayerCharacter) target).hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
										?((PlayerCharacter) target).startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
										:"");
					
					} else {
						return "<p>"
									+ "在你将"+clothing.getName()+"穿戴到位后，忽然感到其表面辐射出一阵奇怪的暖流。"
									+ "你认定这是上面的奥术附魔搞的鬼，有些许不安，于是立刻尝试"
										+" 脱下来。"
								+ "</p>"
								+ "<p>"
									+ "抓住"+clothing.getName()+"的时候，似乎没有什么不对劲，但正要脱下来的时候，你才意识到自己犯了个错误。"
									+ "一串奥术能量顿时穿过你的身体，仿佛直接射入你的脑海，你不由自主地松开了手。"
								+ "</p>"
								+ "<p>"
									+ "你紧咬牙关，再次尝试除掉这件烦人的衣物，但在你想要脱下的那一刻，立刻就没了力气。"
									+ "无论你如何努力，最多也只能将"+clothing.getName()
										+"稍微移开一点，每当快要从身体上移除时，它都会自动返回贴身的位置。"
								+ "</p>"
								+ "<p>"
									+ "你顿时想起了莉莱雅之前警告过封印衣物的危险，你意识到"
										+clothing.getName()
										+""
										+"<b style='color:"+PresetColour.RARITY_JINXED.toWebHexString()+";'>已经被封印了</b>，难受得呻吟起来。"
									+ "还记得莉莱雅这样说，想要解除封印就需要将吸收的精华集中在其上……"
								+ "</p>";
					}
					
				} else {
					return "";
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if(c.isSealed()) {
					return true;
				}
			}
			return false;
		}
	};
	
	// OTHER:

	public static AbstractStatusEffect SLEEPING = new AbstractStatusEffect(80,
			"睡眠",
			"睡眠",
			PresetColour.SLEEP,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIsFull]睡眠中……");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.isPlayer()) {
				return target.hasStatusEffect(SLEEPING);
			}
			return (Main.game.isStarted()
						&& target.isSleepingAtHour()
						&& target.isAtHome()
						&& target.isAffectedBySleepingStatusEffect()
						&& (target.hasStatusEffect(SLEEPING) || !Main.game.getCharactersPresent().contains(target))
						&& !target.hasTrait(Perk.HEAVY_SLEEPER, true));
		}
	};

	public static AbstractStatusEffect SLEEPING_HEAVY = new AbstractStatusEffect(80,
			"深度睡眠",
			"sleeping_heavy",
			PresetColour.SLEEP_HEAVY,
			PresetColour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -5f)),
			Util.newArrayListOfValues("在温柔的性爱时不会醒来")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIsFull]正处在深度睡眠中，即便大声呼喊乃至用力摇晃，[npc.herHim]都不会醒来。"
							+"[npc.SheIs]进入了深度睡眠的状态，以至于一个动作温和的伴侣可以与[npc.herHim]进行性行为而不至于让[npc.herHim]醒来……");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.isPlayer()) {
				return target.hasStatusEffect(SLEEPING_HEAVY);
			}
			return (Main.game.isStarted()
						&& target.isSleepingAtHour()
						&& target.isAtHome()
						&& target.isAffectedBySleepingStatusEffect()
						&& (target.hasStatusEffect(SLEEPING_HEAVY) || !Main.game.getCharactersPresent().contains(target))
						&& target.hasTrait(Perk.HEAVY_SLEEPER, true));
		}
		@Override
		public boolean isSexEffect() {
			return false;
		}
	};
	
	public static AbstractStatusEffect WELL_RESTED = new AbstractStatusEffect(80,
			"充分休息",
			"wellRested",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.ATTRIBUTE_MANA,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 10f),
					new Value<>(Attribute.MANA_MAXIMUM, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "在充分休息之后，[npc.name]感到充满活力。");
			} else {
				return "";
			}
		}
	};
	
	public static AbstractStatusEffect WELL_RESTED_BOOSTED = new AbstractStatusEffect(80,
			"充分休息(增强)",
			"wellRestedBoosted",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.ATTRIBUTE_MANA,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 30f),
					new Value<>(Attribute.MANA_MAXIMUM, 30f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.hasTrait(Perk.JOB_UNEMPLOYED, true)) {
					return UtilText.parse(target, "由于[npc.name]清楚地了解该如何高效利用休息时间，[npc.her]现在感觉精神饱满、神清气爽。");
				} else {
					return UtilText.parse(target, "由于[npc.Name]房间内的“帝皇尺寸床”，[npc.her]休息得非常舒服，现在觉得精神饱满、神清气爽。");
				}
			} else {
				return "";
			}
		}
	};
	
	public static AbstractStatusEffect WELL_RESTED_BOOSTED_EXTRA = new AbstractStatusEffect(80,
			"充分休息(额外增强)",
			"wellRestedBoostedExtra",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.ATTRIBUTE_MANA,
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 60f),
					new Value<>(Attribute.MANA_MAXIMUM, 60f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"由于[npc.Name]房间内的“帝皇尺寸床”，加之[npc.her]清楚地了解该如何高效利用休息时间，[npc.name]现在感觉精神焕发、神采奕奕。");
				
			} else {
				return "";
			}
		}
	};

//	public static AbstractStatusEffect SHOWER = new AbstractStatusEffect(80,
//			"recently showered",
//			"bath_minor",
//			PresetColour.ATTRIBUTE_HEALTH,
//			PresetColour.BASE_AQUA,
//			true,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
//					new Value<>(Attribute.MANA_MAXIMUM, 5f),
//					new Value<>(Attribute.DAMAGE_LUST, 5f)),
//			Util.newArrayListOfValues("[style.boldMinorGood(Doubles)] [style.colourHealth(health)] and [style.colourMana(aura)] regeneration rate")) {
//		@Override
//		public String getDescription(GameCharacter target) {
//			if(target!=null) {
//				return UtilText.parse(target, "Having recently taken the time to have a shower, [npc.name] [npc.verb(feel)] refreshed.");
//			} else {
//				return "";
//			}
//		}
//	};
	
//	public static AbstractStatusEffect BATH = new AbstractStatusEffect(80,
//			"recently bathed",
//			"bath",
//			PresetColour.ATTRIBUTE_HEALTH,
//			PresetColour.BASE_AQUA,
//			true,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.HEALTH_MAXIMUM, 10f),
//					new Value<>(Attribute.MANA_MAXIMUM, 10f),
//					new Value<>(Attribute.DAMAGE_LUST, 10f)),
//			Util.newArrayListOfValues("[style.boldGood(Triples)] [style.colourHealth(health)] and [style.colourMana(aura)] regeneration rate")) {
//		@Override
//		public String getDescription(GameCharacter target) {
//			if(target!=null) {
//				return UtilText.parse(target, "Having recently taken the time to relax in [npc.her] bath, [npc.name] [npc.verb(feel)] refreshed and rejuvenated.");
//			} else {
//				return "";
//			}
//		}
//	};

//	public static AbstractStatusEffect BATH_BOOSTED = new AbstractStatusEffect(80,
//			"recently bathed (spa)",
//			"bath_boosted",
//			PresetColour.ATTRIBUTE_HEALTH,
//			PresetColour.BASE_AQUA,
//			PresetColour.GENERIC_EXCELLENT,
//			true,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.HEALTH_MAXIMUM, 25f),
//					new Value<>(Attribute.MANA_MAXIMUM, 25f),
//					new Value<>(Attribute.DAMAGE_LUST, 15f)),
//			Util.newArrayListOfValues("[style.boldExcellent(Quadruples)] [style.colourHealth(health)] and [style.colourMana(aura)] regeneration rate")) {
//		@Override
//		public String getDescription(GameCharacter target) {
//			if(target!=null) {
//				return UtilText.parse(target, "Having recently taken the time to relax in [npc.her] spa, [npc.name] [npc.verb(feel)] like [npc.sheHas] been born anew!");
//			} else {
//				return "";
//			}
//		}
//	};
	
	public static AbstractStatusEffect OVERWORKED_1 = new AbstractStatusEffect(80,
			"略微疲劳",
			"overworked1",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, -10f),
					new Value<>(Attribute.MANA_MAXIMUM, -10f)),
			Util.newArrayListOfValues(
					"[style.colourMinorBad(低概率)]会使用奴隶休息室",
					"工作时:",
					"[style.boldBad(-50%)][style.colourAffection(好感获取)]",
					"[style.boldBad(-0.5)][style.colourAffection(好感/小时)]",
					"[style.boldBad(-25%)][style.colourExperience(经验)]获取几率")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"由于每天要做的工作有点多，[npc.name]有时会感到有点疲劳。<br/>"
						+ "<i>(每日体力值-1至-9。当前的每日体力值为[style.colourBad("+target.getDailySlaveJobStamina()+")])</i>");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSlave()
					&& target.getDailySlaveJobStamina()<0
					&& target.getDailySlaveJobStamina()>=-9;
		}
	};
	
	public static AbstractStatusEffect OVERWORKED_2 = new AbstractStatusEffect(80,
			"疲劳",
			"overworked2",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, -25f),
					new Value<>(Attribute.MANA_MAXIMUM, -25f)),
			Util.newArrayListOfValues(
					"[style.colourBad(不大可能)]会使用奴隶休息室",
					"工作时:",
					"[style.boldBad(-80%)][style.colourAffection(好感获取)]",
					"[style.boldBad(-1)][style.colourAffection(好感/小时)]",
					"[style.boldBad(-50%)][style.colourExperience(经验)]获取几率")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"由每天进行过多工作造成，[npc.name]逐渐觉得十分劳累。<br/>"
						+ "<i>(每天-10至-19体力时就会获得。当前每日体力变化为[style.colourBad("+target.getDailySlaveJobStamina()+")])</i>");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSlave()
					&& target.getDailySlaveJobStamina()<-9
					&& target.getDailySlaveJobStamina()>=-19;
		}
	};
	
	public static AbstractStatusEffect OVERWORKED_3 = new AbstractStatusEffect(80,
			"极度劳累",
			"overworked3",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -50f),
					new Value<>(Attribute.MANA_MAXIMUM, -50f)),
			Util.newArrayListOfValues(
					"将[style.colourTerrible(不再)]使用奴隶休息室",
					"工作时:",
					"[style.boldBad(-100%)][style.colourAffection(好感获取)]",
					"[style.boldBad(-2)][style.colourAffection(好感/小时)]",
					"[style.boldBad(-75%)][style.colourExperience(经验)]获取几率")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"由每天进行过多工作造成，[npc.name]感觉十分疲乏。<br/>"
						+ "<i>(每天-20以下体力时就会获得。当前每日体力变化为[style.colourBad("+target.getDailySlaveJobStamina()+")])</i>");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSlave()
					&& target.getDailySlaveJobStamina()<-19;
		}
	};
	
	public static AbstractStatusEffect GYM_FATIGUE = new AbstractStatusEffect(80,
			"锻炼后疲倦",
			"gym_fatigue",
			PresetColour.ATTRIBUTE_HEALTH,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, -15f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.nameIsFull]刚刚锻炼过，感到十分疲倦，在回复之前无法继续锻炼了。");
			} else {
				return "";
			}
		}
	};
	
	public static AbstractStatusEffect FATIGUED = new AbstractStatusEffect(80,
			"疲倦",
			"fatigued",
			PresetColour.ATTRIBUTE_HEALTH,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, -15f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "经过一段时间努力工作，[npc.nameIsFull]觉得十分疲倦，需要一段时间来恢复……");
			} else {
				return "";
			}
		}
	};
	
	// Utility status effect to display text of companions leaving:
	public static AbstractStatusEffect COMPANIONS_LEAVING = new AbstractStatusEffect(80,
			"同伴离开",
			"",
			PresetColour.BASE_MAGENTA,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return "";
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isPlayer();
		}
	};
	
	public static AbstractStatusEffect PSYCHOACTIVE = new AbstractStatusEffect(80,
			"迷幻之旅",
			"psychoactive",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("接受<b style='color: " + PresetColour.PSYCHOACTIVE.toWebHexString() + ";'>催眠暗示</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && Math.random()<=Util.getModifiedDropoffValue((secondsPassed/60)*0.0075f, 0.5f)) {
				
				if(target.getPsychoactiveFluidsIngested().isEmpty()) {
					return "<p>"
								+ "你意识到自己不知道现在身处何处，于是奋力与幻觉抗争着……"
							+ "</p>"
							+ "<p><i>"
							+ UtilText.returnStringAtRandom(
									"忽然，你发现自己回到了莉莉的博物馆。"
										+ "一名博物馆职员正用[pc.hand]抓着你，将你拖向一个空房间。"
										+ "进到房间后，他便跪下来，对你咧嘴笑了一下，附身向前在你暴露的下体上送去了一记湿吻……",
									"忽然，你发现自己和姨妈莉莉面对着面；站在她有些陈旧的公寓中。"
										+ "她咯咯地笑了两声，便抓住了你的[pc.hand]，带着你进了她的卧室。"
										+ "进到房间后，她便抖掉身上的睡袍，与你拥吻起来……",
									"忽然，你发现自己站在一位倾城绝世的恶魔面前。"
										+ "她向前走来，将赤裸的乳房紧紧贴住你的身体，尾巴则偷偷地潜入你的两腿之间，摩挲起来。"
										+ "她发出一声淫靡的呻吟，便与你热吻起来……")
							+"</i></p>"
							+ "<p>"
								+ "你急促地深呼吸后，忽然从幻觉中跳出。"
								+ "脸刷得红了一片，只感到一股热气在胯下涌动……"
							+ "</p>"
							+target.incrementLust(25, false);
				} else {
					List<AbstractFluidType> list = new ArrayList<>(target.getPsychoactiveFluidsIngested());
					AbstractFluidType fluid = list.get(Util.random.nextInt(list.size()));
					String npcName = UtilText.generateSingularDeterminer(fluid.getRace().getName(false))+""+fluid.getRace().getName(false);
					switch(fluid.getBaseType()) {
						case CUM:
							return "<p>"
										+ "你意识到自己不知道现在身处何处，于是奋力与幻觉抗争着……"
									+ "</p>"
									+ "<p><i>"
									+ UtilText.returnStringAtRandom(
											"忽然，你发现自己回到了莉莉的博物馆。"
												+ "你跪在一名博物馆职员——"+npcName+"——的面前，热切地含着屌。"
												+ "过了一会儿，肉棒便开始颤抖，一股浓厚的精液倾泻进你的口中，你发出一声欢愉的呻吟……",
											"忽然，你发现自己站在姨妈莉莉身边；在她有些陈旧的公寓的卧室中，你们两个跪在对方身前。"
												+ "她注视着你吮吸着面前"+fluid.getRace().getName(false)+"的大屌，露出了明快的笑容。"
												+ "粗壮的肉棒在你的口中颤抖起来，莉莉伸出手，摁住了你的脑袋，为了让每一滴精液都能深深地注入你的喉咙……",
											"忽然，你发现自己跪在一个浑身赤裸的"+fluid.getRace().getName(false)+"面前。"
												+ "对方快步向前，抓住你的脑袋，让你生生吞下了那硬挺的鸡巴。"
												+ "一阵难以抑制的呻吟过后，对方便突然射了出来，用滚烫的精液填满了你的嘴巴……")
									+"</i></p>"
									+ "<p>"
										+ "你急促地深呼吸后，忽然从幻觉中跳出。"
										+ "脸刷得红了一片，只感到一股热气在胯下涌动……"
									+ "</p>"
									+target.incrementLust(25, false);
						case GIRLCUM:
							return "<p>"
										+ "你意识到自己不知道现在身处何处，于是奋力与幻觉抗争着……"
									+ "</p>"
									+ "<p><i>"
									+ UtilText.returnStringAtRandom(
											"忽然，你发现自己回到了莉莉的博物馆。"
												+ "你跪在一名博物馆职员——"+npcName+"——的面前，热切地舔弄、亲吻着湿漉漉的小穴。"
												+ "过了一会儿，小穴开始抽搐、颤抖，你带着欢愉的呻吟，将每一滴可口的爱液都纳入口中……",
											"忽然，你发现自己站在姨妈莉莉身边；在她有些陈旧的公寓的卧室中，你们两个跪在对方身前。"
												+ "她注视着你舔弄着面前"+fluid.getRace().getName(false)+"的小穴，露出了明快的笑容。"
												+ "他的小穴开始抽搐、颤抖，莉莉伸出手摁住了你的脑袋，为了让你舔干净每一滴可口的爱液……",
											"忽然，你发现自己跪在一个浑身赤裸的"+fluid.getRace().getName(false)+"面前。"
												+ "对方快步向前，抓住你的脑袋，强行让你[pc.lips+]贴在那湿漉漉的小穴上。"
												+ "一阵难以抑制的呻吟过后，对方便步入了高潮，将你的脸紧紧压在胯下，让你舔干净了每一滴可口的爱液……")
									+"</i></p>"
									+ "<p>"
										+ "你急促地深呼吸后，忽然从幻觉中跳出。"
										+ "脸刷得红了一片，只感到一股热气在胯下涌动……"
									+ "</p>"
									+target.incrementLust(25, false);
						case MILK:
							return "<p>"
										+ "你意识到自己不知道现在身处何处，于是奋力与幻觉抗争着……"
									+ "</p>"
									+ "<p><i>"
									+ UtilText.returnStringAtRandom(
											"忽然，你发现自己回到了莉莉的博物馆。"
												+ "你坐在一名博物馆职员——"+npcName+"——的大腿上，热切地亲吻、吸吮着胀满奶的乳头。"
												+ "过了一会儿，乳汁开始不断地流入你的口中，你发出一声欢愉的呻吟，咕嘟咕嘟地品尝着这可口的液体……",
											"忽然，你发现自己躺在姨妈莉莉的腿上；你们俩正在她旧公寓的床上。"
												+ "你上前去，对着她微微发胀的乳头又吸又舔，惹得她合不拢嘴。"
												+ "过了一会儿，乳汁开始不断地流入你的口中，你发出一声欢愉的呻吟，咕嘟咕嘟地品尝着这可口的液体……",
											"忽然，你发现自己正坐在一个全身赤裸的"+fluid.getRace().getName(false)+"的大腿上。"
												+ "那人将你的脑袋深深地埋进巨乳之中，用胀满奶的乳头紧紧地贴着你[pc.lips+]。"
												+ "随着一声难以抑制的呻吟，你对着柔软的乳头吮吸起来，过了一会儿，乳汁开始不断地流入你的口中……")
									+"</i></p>"
									+ "<p>"
										+ "你急促地深呼吸后，忽然从幻觉中跳出。"
										+ "脸刷得红了一片，只感到一股热气在胯下涌动……"
									+ "</p>"
									+target.incrementLust(25, false);
					}
				}
				return "";
			} else {
				return "";
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "你此刻感到非常怪异……奇妙的景象在面前不断闪过，你不知道现在身处何处……";
			} else {
				return UtilText.parse(target, "[npc.name]此刻感到非常怪异……奇妙的景象在面前不断闪过，[npc.she]不知道现在身处何处……");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.removePsychoactiveEffects();
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CLEANED_MASSAGED = new AbstractStatusEffect(80,
			"刚按摩过",
			"cleaned_massage",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 25f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"刚刚接受过按摩，[npc.nameIsFull]感到身体十分放松！");
		}
	};
	
	public static AbstractStatusEffect CLEANED_SHOWER = new AbstractStatusEffect(80,
			"刚洗过澡",
			"cleaned_shower",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.BASE_AQUA,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.MANA_MAXIMUM, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 5f)),
			Util.newArrayListOfValues(
					"[style.colourHealth(生命)]和[style.colourMana(灵气)]恢复速度提高至[style.boldMinorGood(两倍)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"刚刚花时间淋浴过，[npc.name]感觉焕然一新！");
		}
	};
	
	public static AbstractStatusEffect CLEANED_BATH = new AbstractStatusEffect(80,
			"刚泡过澡",
			"cleaned_bath",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.BASE_AQUA,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 10f),
					new Value<>(Attribute.MANA_MAXIMUM, 10f),
					new Value<>(Attribute.DAMAGE_LUST, 10f)),
			Util.newArrayListOfValues(
					"[style.colourHealth(生命)]和[style.colourMana(灵气)]恢复速度提高至[style.boldGood(三倍)]")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.clearMuskMarkers();
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"刚刚花时间在浴缸中放松过，[npc.name]感觉焕然一新、活力充沛。");
		}
	};
	
	public static AbstractStatusEffect CLEANED_SPA = new AbstractStatusEffect(80,
			"水疗浴",
			"cleaned_spa",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.BASE_AQUA,
			PresetColour.ATTRIBUTE_MANA,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 25f),
					new Value<>(Attribute.MANA_MAXIMUM, 25f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			Util.newArrayListOfValues(
					"[style.colourHealth(生命)]和[style.colourMana(灵气)]恢复速度提高至[style.boldMinorGood(四倍)]")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.clearMuskMarkers();
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"刚刚花时间在水疗中心放松过，[npc.name]感觉仿佛重获新生！");
		}
	};
	
	public static AbstractStatusEffect LOLLIPOP_SUCKING = new AbstractStatusEffect(80,
			"舔棒棒糖",
			"lollipop",
			PresetColour.CLOTHING_PINK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull]玩味地舔着棒棒糖，每次有人看着的时候，[npc.she]都会把[npc.lips]贴上去，仿佛在上演一场激吻。");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SMOKING = new AbstractStatusEffect(80,
			"抽烟",
			"smoking",
			PresetColour.CLOTHING_ORANGE,
			PresetColour.CLOTHING_BRASS,
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 10f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull]正在抽烟。每当[npc.she]呼气时，都会在面前形成以团白色烟雾，顿时让[npc.her]被烟草燃烧的浓烈味道所包围。");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(RECENTLY_SMOKED);
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(RECENTLY_SMOKED, 4 * 60 * 60);
			return "";
		}
	};
	
	public static AbstractStatusEffect RECENTLY_SMOKED = new AbstractStatusEffect(80,
			"刚抽过烟",
			"recentlySmoked",
			PresetColour.CLOTHING_ORANGE,
			PresetColour.CLOTHING_BRASS,
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 10f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"只要靠近[npc.NameHasFull]就能很清楚地知道，[npc.herHim]刚刚抽过烟，散发着一股烟草燃烧的浓烈味道。");
		}
	};

	public static AbstractStatusEffect RECENTLY_EATEN = new AbstractStatusEffect(80,
			"刚刚进食",
			"recentlyEaten",
			PresetColour.BASE_GREEN,
			PresetColour.BASE_GREEN_LIGHT,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 5f)),
			null) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(RECENTLY_EATEN_POOR);
			target.removeStatusEffect(RECENTLY_EATEN_QUALITY);
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull]刚刚吃过东西。[npc.her]的饥饿感暂时得到了满足，感觉自己充满能量。");
		}
	};

	public static AbstractStatusEffect RECENTLY_EATEN_POOR = new AbstractStatusEffect(80,
			"刚刚进食(垃圾食品)",
			"recentlyEaten",
			PresetColour.BASE_GREEN_LIGHT,
			PresetColour.BASE_YELLOW_PALE,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 1f)),
			null) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(RECENTLY_EATEN);
			target.removeStatusEffect(RECENTLY_EATEN_QUALITY);
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull]刚刚吃过那种会被归类为垃圾食品的东西。[npc.her]的饥饿感暂时得到了满足，感觉自己更有活力了。");
		}
	};

	public static AbstractStatusEffect RECENTLY_EATEN_QUALITY = new AbstractStatusEffect(80,
			"刚刚进食(优质)",
			"recentlyEaten",
			PresetColour.BASE_GREEN_DARK,
			PresetColour.BASE_GREEN,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 10f)),
			null) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(RECENTLY_EATEN_POOR);
			target.removeStatusEffect(RECENTLY_EATEN);
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull]刚刚吃过十分优质的食物。"
					+ "[npc.her]的饥饿感暂时得到了满足，美妙的餐点仿佛仍在口中，[npc.sheIs]感觉自己充满能量，十分满意。");
		}
	};

	public static AbstractStatusEffect THIRST_QUENCHED = new AbstractStatusEffect(80,
			"解渴",
			"recentlyDrank",
			PresetColour.BASE_BLUE,
			PresetColour.BASE_BLUE_LIGHT,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 5f)),
			null) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(THIRST_QUENCHED_POOR);
			target.removeStatusEffect(THIRST_QUENCHED_QUALITY);
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull]刚刚喝过东西。[npc.her]的口渴感暂时得到了缓解，能够集中精神专注于当前事物了。");
		}
	};

	public static AbstractStatusEffect THIRST_QUENCHED_POOR = new AbstractStatusEffect(80,
			"解渴(平淡)",
			"recentlyDrank",
			PresetColour.BASE_BLUE_LIGHT,
			PresetColour.BASE_YELLOW_PALE,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 1f)),
			null) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(THIRST_QUENCHED);
			target.removeStatusEffect(THIRST_QUENCHED_QUALITY);
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull]刚刚用勉强能入嘴的东西解了渴。[npc.her]的口渴感暂时得到了缓解，感觉自己能够稍微集中精神专注于当前事物了。");
		}
	};

	public static AbstractStatusEffect THIRST_QUENCHED_QUALITY = new AbstractStatusEffect(80,
			"解渴(优质)",
			"recentlyDrank",
			PresetColour.BASE_BLUE_DARK,
			PresetColour.BASE_BLUE,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 10f)),
			null) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(THIRST_QUENCHED_POOR);
			target.removeStatusEffect(THIRST_QUENCHED);
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull]刚刚喝过十分优质的饮品。"
					+ "[npc.her]的口渴感暂时得到了缓解，可口的饮品仿佛仍在口中，能够集中精神专注于当前事物了。");
		}
	};
	
	public static AbstractStatusEffect DRUNK_1 = new AbstractStatusEffect(80,
			"醉酒I - 微醺",
			"drunk1",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 2f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<>(Attribute.MAJOR_ARCANE, -2f),
					new Value<>(Attribute.DAMAGE_LUST, 5f),
					new Value<>(Attribute.RESISTANCE_LUST, -1f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			target.incrementAlcoholLevel(-((secondsPassed/60)*(1f/(60f*6)))); // alcohol level will completely go after 6 hours
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.nameIsFull]刚刚喝过酒精饮品，现在觉得有些微醺……"
					+ "<br/>醉酒度：[style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.ONE_TIPSY;
		}
	};
	
	public static AbstractStatusEffect DRUNK_2 = new AbstractStatusEffect(80,
			"醉酒II - 浅醉",
			"drunk2",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.RESISTANCE_LUST, -5f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.nameIsFull]刚刚喝过酒精饮品，现在觉得稍有些醉……"
					+ "<br/>醉酒度：[style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.TWO_MERRY;
		}
	};
	
	public static AbstractStatusEffect DRUNK_3 = new AbstractStatusEffect(80,
			"醉酒III - 醉酒",
			"drunk3",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.DAMAGE_LUST, 5f),
					new Value<>(Attribute.RESISTANCE_LUST, -10f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.nameIsFull]刚刚喝过酒精饮品，现在觉得醉得不轻……"
					+ "<br/>沉醉：[style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(3, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br/>你看起来酩酊大醉，可能会有人计划“捡尸”。");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.THREE_DRUNK;
		}
	};
	
	public static AbstractStatusEffect DRUNK_4 = new AbstractStatusEffect(80,
			"醉酒IV - 沉醉",
			"drunk4",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 15f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<>(Attribute.MAJOR_ARCANE, -10f),
					new Value<>(Attribute.DAMAGE_LUST, -5f),
					new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.nameIsFull]刚刚喝过酒精饮品，现在觉得完全醉了……"
					+ "<br/>醉酒度：[style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(3, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br/>你看起来酩酊大醉，可能会有人计划“捡尸”。");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.FOUR_HAMMERED;
		}
	};
	
	public static AbstractStatusEffect DRUNK_5 = new AbstractStatusEffect(80,
			"醉酒V - 烂醉",
			"drunk5",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 20f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<>(Attribute.MAJOR_ARCANE, -15f),
					new Value<>(Attribute.DAMAGE_LUST, -10f),
					new Value<>(Attribute.RESISTANCE_LUST, -20f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "A[npc.nameIsFull]刚刚喝过酒精饮品，现在已经烂醉如泥……"
					+ "<br/>醉酒度：[style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(3, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br/>你看起来酩酊大醉，可能会有人计划“捡尸”。");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.FIVE_WASTED;
		}
	};
	
	public static AbstractStatusEffect ADDICTIONS = new AbstractStatusEffect(80,
			"成瘾",
			"addictions",
			PresetColour.BASE_CRIMSON,
			false,
			null,
			null) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			extraEffects.clear();
			
			for(Addiction addiction : target.getAddictions()) {
				long oneDayLater = addiction.getLastTimeSatisfied() + (24 * 60);
				long now = Main.game.getMinutesPassed();
				long timeLeft = oneDayLater - now;
				long hoursLeft = timeLeft / 60;
				long minutesLeft = timeLeft % 60;
				AbstractRace fluidRace = addiction.getFluid().getRace();
				extraEffects.add("<b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+addiction.getFluid().getBaseType().getNames().get(0)+"</b>:"
						+ (timeLeft > 0
								?"[style.colourGood("+hoursLeft+":"+String.format("%02d", minutesLeft)+")]"
								:"[style.boldArcane(戒断！)]"));
			}
			
			return extraEffects;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "你对"+Util.intToString(target.getAddictions().size())+"种液体上瘾了！"
							+ "如果超过24小时没有缓解，将会受到戒断症状的影响。"
							+ "<i>成瘾可以使用“"+ItemType.ADDICTION_REMOVAL.getName(false)+"”解除。</i>";
				} else {
					return UtilText.parse(target,
							"[npc.NameIsFull]对"+Util.intToString(target.getAddictions().size())+"种液体完全上瘾了！"
									+ "如果超过24小时没有缓解，将会受到戒断症状的影响。"
									+ "<i>成瘾可以使用“"+ItemType.ADDICTION_REMOVAL.getName(false)+"”解除。</i>");
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.getAddictions().isEmpty();
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_1 = new AbstractStatusEffect(80,
			"轻度戒断",
			"withdrawal1",
			PresetColour.CORRUPTION_STAGE_ONE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -2f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -2f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -2f),
					new Value<>(Attribute.MANA_MAXIMUM, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("你正在遭受轻度戒断:");
				} else {
					sb.append(UtilText.parse(target, "[npc.NameIsFull]正在遭受轻度戒断:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long oneDayLater = addiction.getLastTimeSatisfied() + (24 * 60);
					long twoDaysLater = oneDayLater + (24 * 60);
					long now = Main.game.getMinutesPassed();
					
					if(oneDayLater <= now && now < twoDaysLater) {
						long timeLeft = twoDaysLater - now;
						long hoursLeft = timeLeft / 60;
						long minutesLeft = timeLeft % 60;
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+"[style.boldArcane(具有成瘾性的)]"
								+ "<b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+addiction.getFluid().getBaseType().getNames().get(0)+"</b>:"
								+ hoursLeft+":"+String.format("%02d", minutesLeft) + "后[style.boldArcane(加剧)]");
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long oneDayLater = addiction.getLastTimeSatisfied() + (24 * 60);
				long twoDaysLater = oneDayLater + (24 * 60);
				long now = Main.game.getMinutesPassed();
				
				if (oneDayLater <= now && now < twoDaysLater) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_2 = new AbstractStatusEffect(80,
			"明显戒断",
			"withdrawal2",
			PresetColour.CORRUPTION_STAGE_TWO,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -5f),
					new Value<>(Attribute.MANA_MAXIMUM, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("你正在遭受明显戒断:");
				} else {
					sb.append(UtilText.parse(target, "[npc.NameIsFull]正在遭受明显戒断:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long twoDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 2);
					long threeDaysLater = twoDaysLater + (24 * 60);
					long now = Main.game.getMinutesPassed();
					
					if (twoDaysLater <= now && now < threeDaysLater) {
						long timeLeft = threeDaysLater - now;
						long hoursLeft = timeLeft / 60;
						long minutesLeft = timeLeft % 60;
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+"[style.boldArcane(具有成瘾性的)]"
								+ "<b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+addiction.getFluid().getBaseType().getNames().get(0)+"</b>:"
								+ hoursLeft+":"+String.format("%02d", minutesLeft) + "后[style.boldArcane(加剧)]");
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long twoDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 2);
				long threeDaysLater = twoDaysLater + (24 * 60);
				long now = Main.game.getMinutesPassed();
				
				if (twoDaysLater <= now && now < threeDaysLater) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_3 = new AbstractStatusEffect(80,
			"强力戒断",
			"withdrawal3",
			PresetColour.CORRUPTION_STAGE_THREE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -10f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -10f),
					new Value<>(Attribute.MANA_MAXIMUM, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("你正在遭受强力戒断:");
				} else {
					sb.append(UtilText.parse(target, "[npc.NameIsFull]正在遭受强力戒断:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long threeDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 3);
					long fourDaysLater = threeDaysLater + (24 * 60);
					long now = Main.game.getMinutesPassed();
					
					if (threeDaysLater <= now && now < fourDaysLater) {
						long timeLeft = fourDaysLater - now;
						long hoursLeft = timeLeft / 60;
						long minutesLeft = timeLeft % 60;
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+"[style.boldArcane(具有成瘾性的)]"
								+ "<b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+addiction.getFluid().getBaseType().getNames().get(0)+"</b>:"
								+ hoursLeft+":"+String.format("%02d", minutesLeft) + "后[style.boldArcane(加剧)]");
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long threeDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 3);
				long fourDaysLater = threeDaysLater + (24 * 60);
				long now = Main.game.getMinutesPassed();
				
				if (threeDaysLater <= now && now < fourDaysLater) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_4 = new AbstractStatusEffect(80,
			"严重戒断",
			"withdrawal4",
			PresetColour.CORRUPTION_STAGE_FOUR,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -25f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -25f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -25f),
					new Value<>(Attribute.MANA_MAXIMUM, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("你正在遭受严重戒断:");
				} else {
					sb.append(UtilText.parse(target, "[npc.NameIsFull]正在遭受严重戒断:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long fourDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 4);
					long fiveDaysLater = fourDaysLater + (24 * 60);
					long now = Main.game.getMinutesPassed();
					
					if (fourDaysLater <= now && now < fiveDaysLater) {
						long timeLeft = fiveDaysLater - now;
						long hoursLeft = timeLeft / 60;
						long minutesLeft = timeLeft % 60;
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+"[style.boldArcane(具有成瘾性的)]"
								+ "<b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+addiction.getFluid().getBaseType().getNames().get(0)+"</b>:"
								+ hoursLeft+":"+String.format("%02d", minutesLeft) + "后[style.boldArcane(加剧)]");
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long fourDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 4);
				long fiveDaysLater = fourDaysLater + (24 * 60);
				long now = Main.game.getMinutesPassed();
				
				if (fourDaysLater <= now && now < fiveDaysLater) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_5 = new AbstractStatusEffect(80,
			"剧烈戒断",
			"withdrawal5",
			PresetColour.CORRUPTION_STAGE_FIVE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -50f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -50f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -50f),
					new Value<>(Attribute.MANA_MAXIMUM, -50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("你正在遭受剧烈戒断:");
				} else {
					sb.append(UtilText.parse(target, "[npc.NameIsFull]正在遭受剧烈戒断:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long fiveDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 5);
					long now = Main.game.getMinutesPassed();
					
					if (fiveDaysLater <= now) {
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+"[style.boldArcane(具有成瘾性的)]"
								+ "<b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+addiction.getFluid().getBaseType().getNames().get(0)+"</b>。");
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long fiveDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 5);
				long now = Main.game.getMinutesPassed();
				
				if (fiveDaysLater <= now) {
					return true;
				}
			}
			return false;
		}
	};

	public static AbstractStatusEffect MENOPAUSE = new AbstractStatusEffect(80,
			"绝经",
			"menopause",
			PresetColour.BASE_CRIMSON,
			false,
			null,
			Util.newArrayListOfValues("[style.colourBad(完全不孕)]")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"由于年过五十二岁，[npc.nameHasFull]已经绝经，且[npc.sheIsFull]并非恶魔，完全无法再生育子女。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (Main.getProperties().hasValue(PropertyValue.ageContent) || target.isUnique())
					&& target.hasVagina()
					&& (target.isPlayer()
							?target.getAgeValue()>=52+Game.TIME_SKIP_YEARS
							:target.getAgeValue()>=52)
					&& (target.getSubspecies()!=Subspecies.ANGEL && target.getSubspeciesOverride()==null) // Angels and demons are immune
					&& !target.isElemental()
					&& !target.isDoll()
					&& !target.hasStatusEffect(StatusEffect.VIXENS_VIRILITY)
					&& !target.hasStatusEffect(StatusEffect.BROODMOTHER_PILL);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PREGNANT_0 = new AbstractStatusEffect(80,
			"怀孕风险",
			"pregnancy0",
			PresetColour.GENERIC_ARCANE,
			true,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(Main.game.isInNewWorld()) {
				return UtilText.parse(target,
						"[npc.name]最近经历过无防护性交，有可能怀孕！"
							+ "由于奥术能够加速孕期，[npc.she]用不了几个小时就能知道自己怀没怀孕……");
			} else {
				return UtilText.parse(target,
						"[npc.name]最近经历过无防护性交，有可能怀孕！");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			
			if (target.isPregnant()) {
				int maxHourLength = (int)((Main.getProperties().pregnancyDuration * 7 * 24) / 2f);
				target.addStatusEffect(PREGNANT_1, 60 * 60 * ((maxHourLength-12) + Util.random.nextInt(13)));
				target.loadImages(true); // Reload images for pregnant versions
				
				if (target.isPlayer() && !((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
					sb.append("<p>");
						if(Main.game.getPlayer().isTaur()) {
							sb.append("在过去的几个小时里，你始终感觉到你的[pc.legRace]身体有些不适，同时你的下腹部也逐渐隆起。"
								+ "你起初试图将其归咎于消化不良，但当你回过头并低头看向你的半兽身人躯体时，你再也无法否认自己凭借本能得知的真相。"
								+ "你怀孕了。");
						} else {
							sb.append("在过去几个小时里，你的小腹开始逐渐隆起。"
									+ "发展十分缓慢，你起初甚至没有意识到发生了什么，直到低头看了一眼腹部才确信无疑。"
									+ "你怀孕了。"
									+ "你试着摸了一下小腹，终于意识到木已成舟，发出了轻轻的喘息声。");
						}
					sb.append("</p>");
					if(target.hasFetish(Fetish.FETISH_PREGNANCY)) {
						sb.append("<p>"
								+ "[pc.thought(我，我怀孕了？"
									+ "<br/>"
									+ "……"
									+ "<br/>"
									+ "我的天！太好了！<b>我怀孕了！</b>)]"
							+ "</p>"
							+ "<p>"
								+ "当你意识到自己怀孕了，而且只过了几个小时就出现了身体上的反应，顿时感到无上的快乐，就像一阵阵纯粹的极乐感在冲刷着你。"
								+ (Main.game.getPlayer().isTaur()
									?"你回头凝视着[pc.legRace]身体那鼓起的孕肚，喜悦的泪水在你的眼眶中涌动。"
									:"你宠溺地怀抱着鼓起的小腹，喜悦的泪水从眼中汩汩而下。")
							+ "</p>"
							+ "<p>"
								+ "[pc.thought(我从没感觉这么棒！"
										+ "<br/>"
										+ (Main.game.isPlotDiscovered()
											?"我现在该怎么办？！"
											:"要是莉莉姨妈在这，她肯定会为我自豪的！")
										+ "<br/>"
										+ "等等！对啊！<b>莉莱雅！</b>她肯定也想知道！)]"
							+ "</p>");
						
						sb.append("<p>");
							if(target.getBodyMaterial()==BodyMaterial.SLIME) {
								sb.append("你仔细观察了一下自己微微隆起、粘液构成的小腹，才意识到竟然能看见"
											+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+"个微小的史莱姆核心正在"
											+ (target.hasVagina()
												?"你子宫所在的位置上。"
												:"你的孕肚内。")
										+ "你不禁开心地尖叫起来，那种看到自己的"
											+(target.getPregnantLitter().getTotalLitterCount()==1?"孩子":"孩子们")+"在体内成长的喜悦油然而生，"
													+ "接下来的几分钟，"
													+ (Main.game.getPlayer().isTaur()
														?"你伸手来回轻抚揉搓着"
														:"你磨蹭爱抚着")
													+"你鼓起的小腹，沉浸在身为人母的至福中。"
										+ "不过，最终你还是决定去找一下莉莱雅，或许她能帮你弄清楚分娩的所有细节。");
							} else {
								sb.append("你花了一些时间"
										+(Main.game.getPlayer().isTaur()
											?"伸手来回轻抚揉搓着"
											:"磨蹭爱抚着")
										+"你鼓起的小腹，你开始逐渐冷静了下来。"
										+ "你还是决定去找一下莉莱雅，让她帮你弄清楚分娩的所有细节。");
							}
						sb.append("</p>");
						
					} else {
						sb.append("<p>"
									+ "[pc.thought(我，我怀孕了？"
										+ "<br/>"
										+ "……"
										+ "<br/>"
										+ "天啊！<b>我怀孕了！</b>)]"
								+ "</p>"
								+ "<p>"
									+ "这股惊讶感不仅来自于发现自己怀孕，而且还来自于仅仅过了几个小时就在身体上有反应的事实，如同一记重锤让你振聋发聩。"
									+ "尽管你竭尽全力尝试冷静，但还是感觉到自己的呼吸急促起来，你原地打转，"
									+ (Main.game.getPlayer().isTaur()
											?"下意识地用[pc.hands]抱着头部。"
											:"一会儿抱着头，一会儿抱着肚子。")
								+ "</p>"
								+ "<p>"
									+ "[pc.thought(我该怎么办？我该怎么办？我该怎么办？"
											+ "<br/>"
											+ (Main.game.isPlotDiscovered()
												?"我现在该怎么办？！"
												:"要是莉莉姨妈在这，她肯定会为我自豪的！")
											+ "<br/>"
											+ "等等！对啊！<b>莉莱雅！</b>她肯定知道的！)]"
								+ "</p>");
						
						sb.append("<p>");
							if(target.getBodyMaterial()==BodyMaterial.SLIME) {
								sb.append("你最后瞧了一眼自己微微隆起、粘液构成的小腹，才意识到竟然能看见"
												+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+"个微小的史莱姆核心正在"
												+ (target.hasVagina()?"子宫的位置缓缓生长。":"你的小腹中缓缓生长。")
											+ "你不禁震惊地喊出声来，那种看到自己的"+(target.getPregnantLitter().getTotalLitterCount()==1?"孩子":"孩子们")
												+"在体内生长的景象让你颇为震撼，接下来的几分钟，"
													+ (Main.game.getPlayer().isTaur()
														?"你伸手来回轻抚揉搓着"
														:"你磨蹭抚摸着")
												+"你鼓起的小腹，茫然又惶恐不安。"
											+ "不过你最终还是慢慢冷静了下来，决定还是尽快去找莉莱雅比较好。");
							} else {
								sb.append("最初的震惊渐渐消去后，你开始冷静下来。"
											+ "要说谁会知道该怎么办，那一定是莉莱雅了。");
							}
						sb.append("</p>");
					}
					
				} else {
					sb.append("<p>");
						if(Main.game.getPlayer().isTaur()) {
							sb.append("在过去的几个小时里，你始终感觉你的[pc.legRace]身体有些熟悉的不适感，同时你的下腹部也逐渐隆起。"
								+ "你起初并不完全确定这种不适的来源，但当你回过头并低头看向你的半兽身人躯体时，你本能地知道了究竟是什么原因。"
								+ "你又怀孕了。");
						} else {
							sb.append("在过去几个小时里，你的小腹开始逐渐隆起。"
									+ "发展十分缓慢，你起初甚至没有意识到发生了什么，直到低头看了一眼腹部才确信无疑。"
									+ "你又怀孕了。"
									+ "你试着摸了一下小腹，终于意识到木已成舟，发出了轻轻的喘息声。");
						}
					sb.append("</p>");
					
					if(target.getBodyMaterial()==BodyMaterial.SLIME) {
						sb.append("<p>");
							sb.append("你仔细观察了一下自己微微隆起、粘液构成的小腹，只见有"+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+"个微小的史莱姆核心正在"
											+ (target.hasVagina()?"子宫的位置缓缓生长。":"你的小腹中缓缓生长。"));
							if(!target.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
								sb.append("你不禁发出一声开心的轻叹，那种看到自己的"+(target.getPregnantLitter().getTotalLitterCount()==1?"孩子":"孩子们")
											+"在体内生长的喜悦油然而生，接下来的几分钟，"
												+(Main.game.getPlayer().isTaur()
													?"你伸手来回轻抚揉搓着"
													:"你磨蹭抚摸着")
											+"你鼓起的小腹，沉浸在身为人母的至福中。");
							}
						sb.append("</p>");
					}
					
					if(target.hasFetish(Fetish.FETISH_PREGNANCY)) {
						sb.append("<p>"
								+ "[pc.thought(哈哈！太好了！又怀孕了！这感觉真是太棒了……)]"
							+ "</p>"
							+ "<p>"
								+ "你已经轻车熟路，于是发出了愉快的笑声，然后继续你的旅程。"
							+ "</p>");
					} else if(target.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
						sb.append("<p>"
								+ "[pc.thought(不！真不敢相信我又怀孕了……我<b>讨厌</b>这样……)]"
							+ "</p>"
							+ "<p>"
								+ "你已经知道将会发生什么，恼怒地嘟囔了一声，咒骂自己的怀孕，然后继续你的旅程。"
							+ "</p>");
					} else {
						sb.append("<p>"
								+ "[pc.thought(嗯嗯……看上去我又怀孕了……)]"
							+ "</p>"
							+ "<p>"
								+ "你已经轻车熟路，满足地叹了口气，然后继续你的旅程。"
							+ "</p>");
					}
					
				}
				
				sb.append( "<p style='text-align:center;'>"
						+ "<b style='color:"+ PresetColour.GENERIC_SEX.toWebHexString() + ";'>你怀孕了！</b>"
					+ "</p>");
				
				// Remove cum inflation:
				StringBuilder sbFluidDrain = new StringBuilder();
				float fluidAmount = target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
				boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.VAGINA);
				if(retention) {
					fluidAmount = (int)(fluidAmount-Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT);
					if(fluidAmount>0) {
						target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, fluidAmount);
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(The swelling of your pregnant bump forces your body to convert [style.fluid("+fluidAmount+")] of the cum in your pussy into more slime.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(The swelling of your pregnant bump forces your body to expel [style.fluid("+fluidAmount+")] of the cum in your pussy.)]");
						}
					}
				} else if(fluidAmount>0) {
					target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, -fluidAmount);
					if(target.getBodyMaterial()==BodyMaterial.SLIME) {
						sbFluidDrain.append("[style.italicsSex(The swelling of your pregnant bump forces your body to convert all of the cum in your pussy into more slime.)]");
					} else {
						sbFluidDrain.append("[style.italicsSex(The swelling of your pregnant bump forces your body to expel all of the cum in your pussy.)]");
					}
				}
				
				float fluidAmountUrethra = target.getTotalFluidInArea(SexAreaOrifice.URETHRA_VAGINA);
				boolean retentionUrethra = target.hasCreampieRetentionArea(SexAreaOrifice.VAGINA);
				if(retentionUrethra) {
					fluidAmountUrethra = (int)(fluidAmountUrethra-Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT);
					if(fluidAmountUrethra>0) {
						target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, fluidAmountUrethra);
					}
					if(sbFluidDrain.length()>0) {
						sbFluidDrain.append("<br/>");
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(Your body also converts [style.fluid("+fluidAmount+")] of the cum in your pussy's urethra into more slime.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(Your body also expels [style.fluid("+fluidAmount+")] of the cum in your pussy's urethra.)]");
						}
					} else {
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(The swelling of your pregnant bump forces your body to convert [style.fluid("+fluidAmount+")] of the cum in your pussy's urethra into more slime.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(The swelling of your pregnant bump forces your body to expel [style.fluid("+fluidAmount+")] of the cum in your pussy's urethra.)]");
						}
					}
					
				} else if(fluidAmountUrethra>0) {
					target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, -fluidAmountUrethra);
					if(sbFluidDrain.length()>0) {
						sbFluidDrain.append("<br/>");
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(Your body also converts all of the cum in your pussy's urethra into more slime.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(Your body also expels all of the cum in your pussy's urethra.)]");
						}
					} else {
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(The swelling of your pregnant bump forces your body to convert all of the cum in your pussy into more slime.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(The swelling of your pregnant bump forces your body to expel all of the cum in your pussy.)]");
						}
					}
				}
				if(sbFluidDrain.length()>0) {
					sb.append("<p style='text-align:center;'>");
						sb.append(sbFluidDrain.toString());
					sb.append("</p>");
				}
				sb.append("<p style='text-align:center;'>");
					sb.append("[style.italicsMinorBad(你的小穴在怀孕时"+(Main.game.isUrethraEnabled()?"和尿道":"")+"只能容纳[style.fluid("+Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT+")]的液体！)]");
				sb.append("</p>");
				
			} else {
				target.endPregnancy(false);
				sb.append("<p>"
							+ "现在已经过去了足够长的时间，你安全了。"
							+ "你的小腹没有任何变化的迹象，"+(target.getBodyMaterial()==BodyMaterial.SLIME?"体内也没有出现史莱姆核心，":"")
								+"你意识到自己虽然经历过无防护性交，但还是避免了怀孕。"
						+ "</p>"
						+ "<p>"
							+ (target.hasFetish(Fetish.FETISH_PREGNANCY)
								?"[pc.thought(操……)]"
								:"[pc.thought(这下放心了……)]")
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>你没有怀孕！</b>"
						+ "</p>");	
			}

			if(target.isPlayer()) {
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public String applyPostRemovalStatusEffect(GameCharacter target) {
			if(!target.isPregnant()) {
				target.performImpregnationCheck(false);
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PREGNANT_1 = new AbstractStatusEffect(80,
			"妊娠初期",
			"pregnancy1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues("[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+"的最大值)]-5%")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.namePos]在最近的某次性行为中受孕了！"
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?"透过组成[npc.her]身体的[npc.skinColour][npc.skin]，你能看到"+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+"个微小的史莱姆核心正在"
									+"[npc.herHim]的体内生长。"
								:"由于奥术能够加速孕期，[npc.she]很快就能进入下一阶段。"));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			int maxHourLength = (int)((Main.getProperties().pregnancyDuration * 7 * 24) / 2f);
			target.addStatusEffect(PREGNANT_2, 60 * 60 * ((maxHourLength-12) + Util.random.nextInt(13)));
			
			boolean breastGrowth = false;
			if(Main.getProperties().pregnancyBreastGrowth>0 && target.getBreastRawSizeValue()<Main.getProperties().pregnancyBreastGrowthLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyBreastGrowth - Main.getProperties().pregnancyBreastGrowthVariance + Util.random.nextInt(Main.getProperties().pregnancyBreastGrowthVariance*2 + 1));
				
				if(target.getBreastRawSizeValue() + valueIncrease > Main.getProperties().pregnancyBreastGrowthLimit) {
					breastGrowth = true;
					target.setBreastSize(Main.getProperties().pregnancyBreastGrowthLimit);
				} else {
					breastGrowth = true;
					target.incrementBreastSize(valueIncrease);
				}
			}
			
			boolean udderGrowth = false;
			if(target.hasBreastsCrotch() && Main.getProperties().pregnancyUdderGrowth>0 && target.getBreastCrotchRawSizeValue()<Main.getProperties().pregnancyUdderGrowthLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyUdderGrowth - Main.getProperties().pregnancyBreastGrowthVariance + Util.random.nextInt(Main.getProperties().pregnancyBreastGrowthVariance*2 + 1));
				
				if(target.getBreastCrotchRawSizeValue() + valueIncrease > Main.getProperties().pregnancyUdderGrowthLimit) {
					udderGrowth = true;
					target.setBreastCrotchSize(Main.getProperties().pregnancyUdderGrowthLimit);
				} else {
					udderGrowth = true;
					target.incrementBreastCrotchSize(valueIncrease);
				}
			}
			
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();
			if (!((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				sb.append("<p>"
							+ "尽管变化比较缓慢，但当你发现小腹忽然胀得极大，还是受到了强烈的冲击。"
							+ (target.isTaur()
								?"你回头凝视着自己[pc.legRace]腹部那巨大的隆起，心想它究竟会长多大。"
								:"你忍不住用手揉了揉鼓起的小腹，心想它到底会长多大。")
							+ "由于这还是你第一次怀孕，你不太清楚接下来会发生什么，但想到莉莱雅总能及时救你于水火之中，便放下心来。"
						+ "</p>");
			} else {
				sb.append("<p>"
							+ "尽管变化比较缓慢，但你发现小腹忽然胀大的时候，还是受到了似曾相识的冲击感。");
							if(target.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
								sb.append((target.isTaur()
										?"你回头瞥见自己[pc.legRace]腹部那巨大的隆起，厌恶地皱起眉头，咒骂着自己怎么会怀孕。"
										:"你厌恶地皱着眉头，轻轻戳了戳隆起的腹部，咒骂自己怎么就怀上了。"));
							} else {
								sb.append((target.isTaur()
										?"你回头凝视着自己[pc.legRace]腹部那巨大的隆起，脸上浮现出怀孕带来的幸福笑容。"
										:"你忍不住用手揉了揉鼓起的小腹，脸上挂着微笑，感受着舒服的触感。"));
							}
							sb.append("你已经经历过一次，所以很清楚距离分娩还有很长一段时间。"
						+ "</p>");
			}

			if(target.getBodyMaterial()==BodyMaterial.SLIME) {
				sb.append("<p>"
						+ "透过构成你身体的透明粘液，你看到"
						+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+"个史莱姆核心在你的体内越长越大了……"
				+ "</p>");
			}
			sb.append("<p style='text-align:center;'>"
							+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>你进入了妊娠晚期！</b>"
						+ "</p>");
			
			if(breastGrowth) {
				sb.append("<p><i>"
							+"你的乳房也开始胀大，身体已经准备好泌乳。"
							+ "你现在有着[style.boldSex([pc.breastSize]"  + (target.getBreastRawSizeValue()>CupSize.AA.getMeasurement()?"的"+target.getBreastSize().getCupSizeName()+"罩杯":"") + "乳房)]！"
						+ "</i></p>");
			}
			if(udderGrowth) {
				sb.append("<p><i>"
							+"你的[pc.udders]也开始胀大，身体已经准备好泌乳。"
							+ "你现在拥有了[style.boldSex([pc.udderSize]"  + (target.getBreastCrotchRawSizeValue()>CupSize.AA.getMeasurement()?"的"+target.getBreastCrotchSize().getCupSizeName()+"罩杯":"") + "[pc.udders])]！"
						+ "</i></p>");
			}
			
			return sb.toString();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PREGNANT_2 = new AbstractStatusEffect(80,
			"妊娠晚期",
			"pregnancy2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues("[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+"的最大值)]-10%")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NamePos]的肚子高高隆起，很明显[npc.sheIs]已经步入妊娠晚期。"
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?"透过组成[npc.her]身体的[npc.skinColour][npc.skin]，你能看到"+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+"个微小的史莱姆核心正在"
									+"[npc.herHim]的体内生长……"
								:"由于奥术能够加速孕期，[npc.she]很快就能进入最后阶段。"));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {

			target.setTimeProgressedToFinalPregnancyStage(Main.game.getSecondsPassed());

			boolean lactationIncrease = false;
			if(Main.getProperties().pregnancyLactationIncrease>0 && target.getBreastRawMilkStorageValue()<Main.getProperties().pregnancyLactationLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyLactationIncrease - Main.getProperties().pregnancyLactationIncreaseVariance + Util.random.nextInt(Main.getProperties().pregnancyLactationIncreaseVariance*2 + 1));
				
				if(target.getBreastRawMilkStorageValue() + valueIncrease > Main.getProperties().pregnancyLactationLimit) {
					lactationIncrease = true;
					target.setBreastMilkStorage(Main.getProperties().pregnancyLactationLimit);
				} else {
					lactationIncrease = true;
					target.incrementBreastMilkStorage(valueIncrease);
				}
			}

			boolean lactationUddersIncrease = false;
			if(target.hasBreastsCrotch() && Main.getProperties().pregnancyUdderLactationIncrease>0 && target.getBreastCrotchRawMilkStorageValue()<Main.getProperties().pregnancyUdderLactationLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyUdderLactationIncrease - Main.getProperties().pregnancyLactationIncreaseVariance + Util.random.nextInt(Main.getProperties().pregnancyLactationIncreaseVariance*2 + 1));
				
				if(target.getBreastCrotchRawMilkStorageValue() + valueIncrease > Main.getProperties().pregnancyUdderLactationLimit) {
					lactationUddersIncrease = true;
					target.setBreastCrotchMilkStorage(Main.getProperties().pregnancyUdderLactationLimit);
				} else {
					lactationUddersIncrease = true;
					target.incrementBreastCrotchMilkStorage(valueIncrease);
				}
			}
			
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();
			boolean firstTimePregnancy = !((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY);
			sb.append("<p>");
				if(target.isTaur()) {
					sb.append("现在，你的[pc.legRace]孕肚已经鼓胀到惊人的尺寸，走动时下半身的额外重量感变得异常明显。");
				} else {
					sb.append("如今你的肚子已经像个气球一般挺在你的身前，你必须要用一只手撑着腰才能[pc.walk]。");
				}
				
				if(target.getBodyMaterial()==BodyMaterial.SLIME) {
					sb.append("透过构成你身体的透明粘液，你看到"
								+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+"个史莱姆核心"
								+"在你的体内"
								+"大小已经和你自己的相当，你意识到要准备分娩了。");
					
				} else if(target.isVaginaEggLayer()) {
					sb.append("虽然你能感觉到硬质的卵壳正在挤压你的子宫，但并不觉得有任何不适感。"
							+ "如果说有什么影响的话，这种感觉似乎只会增强你的母性本能，你常常发现自己在幻想着产卵和孵卵。");
					
				} else {
					if(firstTimePregnancy) {
						sb.append("过去几个小时内，你感觉孕肚中有一阵奇怪的躁动感，慌乱了一会儿后，你反应过来是你的孩子在子宫中闹腾。"
								+ "时不时还会感觉到自己又被踢了一脚，你意识到要准备分娩了。");
					} else {
						sb.append("过去几小时里，你感到孕肚中传来熟悉的胎动，经验告诉你，这是孩子在子宫里踢腾。"
									+ "时不时还会感觉到自己又被踢了一脚，你意识到要准备分娩了。");
					}
				}
			sb.append("</p>");
			
			sb.append("<p>");
				if(target.hasFetish(Fetish.FETISH_PREGNANCY)) {
					sb.append("[pc.thought(我必须得去找莉莱雅了……或者再这样坚持一小会儿！)]");
				} else if(target.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					sb.append("[pc.thought(终于要结束了！我可以去找莉莱雅，把"+(target.getPregnantLitter().getTotalLitterCount()==1?"这个小崽子":"这些小崽子")+"从我身体里弄出来了！我<b>讨厌</b>怀孕！)]");
				} else {
					sb.append("[pc.thought(我确实该去找莉莱雅了……)]");
				}
			sb.append("</p>");
			
			if(lactationIncrease) {
				sb.append("<p><i>");
					if(target.isTaur()) {
						sb.append("你的乳房明显变得更沉了，当你试探性地揉弄胸口时，能感觉到[pc.milk]的液滴在你肿胀的乳头上凝结成珠。");
					} else {
						sb.append("你的乳房明显变得更沉了，当你温柔地抚摸腹部圆润的隆起时，能感觉到[pc.milk]的液滴在你肿胀的乳头上凝结成珠。");
					}
					sb.append(" 你现在能够分泌[style.boldSex(" + target.getBreastMilkStorage().getDescriptor() + " [pc.milk] ("+ Units.fluid(target.getBreastRawMilkStorageValue(), Units.UnitType.LONG)+"))]!");
				sb.append("</i></p>");
			}
			if(lactationUddersIncrease) {
				sb.append("<p><i>");
					sb.append("你的[pc.udders]变得格外沉重，在你走路的时候，都能感觉到几滴[pc.crotchMilk]从微微胀起的乳头中流淌出来。"
							+ "你现在能分泌[style.boldSex(" + target.getBreastMilkStorage().getDescriptor() + "的[pc.crotchMilk]("+ Units.fluid(target.getBreastRawMilkStorageValue(), Units.UnitType.LONG)+"))]！");
				sb.append("</i></p>");
			}
			
			sb.append("<p style='text-align:center;'>"
						+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>你已经准备好分娩了！</b>" 
					+ "</p>");
			
			return sb.toString();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	public static AbstractStatusEffect PREGNANT_3 = new AbstractStatusEffect(80,
			"临盆",
			"pregnancy3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues("[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+"的最大值)]-15%")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
							(target.isTaur()
								?"[npc.NamePos]的肚子已经胀得巨大，只需要朝这边看一眼就知道[npc.sheIs]准备分娩了。"
								:"[npc.NamePos]的肚子已经胀得巨大，[npc.sheIs]现在必须要用一只手撑着腰才能行走。")
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?"透过组成[npc.her]身体的[npc.skinColour][npc.skin]，你能看到"+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+"个成熟的史莱姆核心正在"
									+"[npc.herHim]的体内。"
								:"")
							+(target.isPlayer()
								?"最好还是去见一下莉莱雅……"
								:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isPregnant()
					 && !target.hasStatusEffect(StatusEffect.PREGNANT_0)
					 && !target.hasStatusEffect(StatusEffect.PREGNANT_1)
					 && !target.hasStatusEffect(StatusEffect.PREGNANT_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_STOMACH_1 = new AbstractStatusEffect(80,
			"孵化初期(肚子)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.ANUS).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"在某次性行为后，[npc.namePos]的腹中填满了卵！"
							+ "由于奥术效果，这些卵会迅速成熟，并在几天内进入下一孵化阶段。");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_STOMACH_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ "你感到腹内的重量感明显增长，不禁开始思考还要多久才能生下肚里正在孵化的卵……"
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(你腹里的卵长大了！)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.ASS, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_STOMACH_2 = new AbstractStatusEffect(80,
			"孵化晚期(肚子)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.ANUS).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"在某次性行为后，[npc.namePos]的腹中填满了卵！"
							+ "由于奥术效果，这些卵会迅速成熟，并在几天内进入孵化的最终阶段。");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.ANUS, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ "从明显的重量感来判断，你可以肯定你腹内的卵现在已经完全成熟，可以在你选定的任意时间产卵。"
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(你已经准备好产下腹中的卵了！)]"
						+ "<br/>(要产下卵，请打开手机界面，进入“卵”页面。)"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.ASS, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_STOMACH_3 = new AbstractStatusEffect(80,
			"完全孵化(肚子)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.ANUS).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "在某次性行为后，[npc.namePos]的腹内填满了卵。现在已经完全成熟，可以产下了。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.ANUS)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.ASS, 3);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_1 = new AbstractStatusEffect(80,
			"孵化初期(乳房)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"某次性行为后，[npc.namePos]的[npc.breasts]内填满了卵！"
							+ "由于奥术效果，这些卵会迅速成熟，并在几天内进入下一孵化阶段。");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_NIPPLES_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "你感到胸部里的重量感明显增长，不禁开始思考还要多久才能生下正在孵化的卵……"
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(你乳房里的卵长大了！)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_2 = new AbstractStatusEffect(80,
			"孵化晚期(乳房)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"某次性行为后，[npc.namePos]的[npc.breasts]内填满了卵！"
							+ "由于奥术效果，这些卵会迅速成熟，并在几天内进入孵化的最终阶段。");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.NIPPLE, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "从明显的重量感来判断，你可以肯定[npc.breasts]里的卵现在已经完全成熟，可以在你选定的任意时间产卵。"
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(你已经准备好产下[npc.breasts]里的卵了！)]"
						+ "<br/>(要产下卵，请打开手机界面，进入“卵”页面。)"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_3 = new AbstractStatusEffect(80,
			"完全孵化(乳房)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "在某次性行为后，[npc.namePos]的[npc.breasts]内填满了卵。现在已经完全成熟，可以产下了。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.NIPPLE)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST, 3);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_CROTCH_1 = new AbstractStatusEffect(80,
			"孵化初期(胯乳)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getName(GameCharacter target) {
			if(target!=null && target.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "孵化初期 (腹乳)";
			}
			return "孵化初期 (胯乳)";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"在某次性行为后，[npc.namePos]的[npc.crotchBoobs]内填满了卵！"
							+ "由于奥术效果，这些卵会迅速成熟，并在几天内进入下一孵化阶段。");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_NIPPLES_CROTCH_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "你感到[npc.crotchBoobs]里的重量感明显增长，不禁开始思考还要多久才能生下正在孵化的卵……"
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(你[npc.crotchBoobs]里的卵长大了！)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST_CROTCH, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_CROTCH_2 = new AbstractStatusEffect(80,
			"孵化晚期(胯乳)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getName(GameCharacter target) {
			if(target!=null && target.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "孵化晚期 (腹乳)";
			}
			return "孵化晚期 (胯乳)";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"在某次性行为后，[npc.namePos]的[npc.crotchBoobs]内填满了卵！"
							+ "由于奥术效果，这些卵会迅速成熟，并在几天内进入孵化的最终阶段。");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.NIPPLE_CROTCH, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "从明显的重量感来判断，你可以肯定[npc.crotchBoobs]里的卵现在已经完全成熟，可以在你选定的任意时间产卵。"
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(你已经准备好产下[npc.crotchBoobs]里的卵了！)]"
						+ "<br/>(要产下卵，请打开手机界面，进入“卵”页面。)"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST_CROTCH, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_CROTCH_3 = new AbstractStatusEffect(80,
			"完全孵化(胯乳)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getName(GameCharacter target) {
			if(target!=null && target.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "完全孵化 (腹乳)";
			}
			return "完全孵化 (胯乳)";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"在某次性行为后，[npc.namePos]的[npc.crotchBoobs]里填满了卵。现在已经完全成熟，可以产下了。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST_CROTCH, 3);
		}
	};

	public static AbstractStatusEffect INCUBATING_EGGS_SPINNERET_1 = new AbstractStatusEffect(80,
			"孵化初期(丝囊)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.SPINNERET).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"在某次性行为后，[npc.namePos]的[npc.spinneret]内填满了卵！"
							+ "由于奥术效果，这些卵会迅速成熟，并在几天内进入下一孵化阶段。");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_SPINNERET_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "你感到[npc.spinneret]里的重量感明显增长，不禁开始思考还要多久才能生下正在孵化的卵……"
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(你[npc.spinneret]里的卵长大了！)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.SPINNERET, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_SPINNERET_2 = new AbstractStatusEffect(80,
			"孵化晚期(丝囊)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.SPINNERET).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"在某次性行为后，[npc.namePos]的[npc.spinneret]内填满了卵！"
							+ "由于奥术效果，这些卵会迅速成熟，并在几天内进入孵化的最终阶段。");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.SPINNERET, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "从明显的重量感来判断，你可以肯定[npc.spinneret]里的卵现在已经完全成熟，可以在你选定的任意时间产卵。"
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(你已经准备好产下[npc.spinneret]里的卵了！)]"
						+ "<br/>(要产下卵，请打开手机界面，进入“卵”页面。)"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.SPINNERET, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_SPINNERET_3 = new AbstractStatusEffect(80,
			"完全孵化(丝囊)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.SPINNERET).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"在某次性行为后，[npc.namePos]的[npc.spinneret]里填满了卵。现在已经完全成熟，可以产下了。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.SPINNERET)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.SPINNERET, 3);
		}
	};

	public static AbstractStatusEffect INCUBATING_EGGS_WOMB_1 = new AbstractStatusEffect(80,
			"孵化初期(子宫)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.VAGINA).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"在某次性行为后，[npc.namePos]的子宫内填满了卵！"
							+ "由于奥术效果，这些卵会迅速成熟，并在几天内进入下一孵化阶段。");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_WOMB_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "你感到子宫里的重量感明显增长，不禁开始思考还要多久才能生下正在孵化的卵……"
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(你穴里的卵长大了！)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.VAGINA, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_WOMB_2 = new AbstractStatusEffect(80,
			"孵化晚期(子宫)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.VAGINA).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"在某次性行为后，[npc.namePos]的子宫内填满了卵！"
							+ "由于奥术效果，这些卵会迅速成熟，并在几天内进入孵化的最终阶段。");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.VAGINA, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "从明显的重量感来判断，你可以肯定子宫里的卵现在已经完全成熟，可以在你选定的任意时间产卵。"
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(你已经准备好产下子宫里的卵了！)]"
						+ "<br/>(要产下卵，请打开手机界面，进入“卵”页面。)"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.VAGINA, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_WOMB_3 = new AbstractStatusEffect(80,
			"完全孵化(子宫)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.VAGINA).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(正在孵化)][style.colourGood("+Util.intToString(count)+")]颗[style.colourYellowLight(卵)]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "在某次性行为后，[npc.namePos]的子宫里填满了卵。现在已经完全成熟，可以产下了。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.VAGINA)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.VAGINA, 3);
		}
	};
	
	public static AbstractStatusEffect VIXENS_VIRILITY = new AbstractStatusEffect(80,
			"助孕药物",
			"vixensVirility",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 50f),
					new Value<>(Attribute.VIRILITY, 50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "在吸收了“[#ITEM_innoxia_pills_fertility.getName(false)]”后，[npc.namePos]的生育力和生殖力被暂时强化了。");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect PROMISCUITY_PILL = new AbstractStatusEffect(80,
			"避孕药物",
			"promiscuityPill",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, -100f),
					new Value<>(Attribute.VIRILITY, -100f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "在吸收了“[#ITEM_innoxia_pills_sterility.getName(false)]”后，[npc.namePos]的生育力和生殖力被暂时大幅削弱了。"
							+ "这是一项<b>预防</b>措施，不会改变[npc.she]在服药前进行的任意无保护措施的性行为的结果！");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect PROMISCUITY_PILL_PROLOGUE = new AbstractStatusEffect(80,
			"避孕药起效",
			"promiscuityPill",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, -100f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull]正在服用复合口服避孕药，因此不会怀孕。");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect BROODMOTHER_PILL = new AbstractStatusEffect(80,
			"超级助孕药物",
			"broodmother_pill",
			PresetColour.CLOTHING_PINK,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, 100f),
					new Value<>(Attribute.VIRILITY, 100f)),
			Util.newArrayListOfValues(
					"孕育的后代数量[style.colourExcellent(翻倍)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"在吸收了“[#ITEM_innoxia_pills_broodmother.getName(false)]”后，[npc.namePos]的生育力和生殖力被暂时强化了，"
							+ "如果[npc.she]让别人怀孕或[npc.herself]怀孕，会怀上比平时多得多的后代！");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect LUBE_PILL = new AbstractStatusEffect(80,
			"润滑过的身体",
			"lube_pill",
			PresetColour.WETNESS,
			PresetColour.GENERIC_EXCELLENT,
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESTING_LUST, 5f)),
			Util.newArrayListOfValues(
					"[style.colourSex(在性爱中：)]",
					"[style.colourWetness(立刻润滑)]腔穴",
					"[style.colourWetness(立即产生)]先走液")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("[npc.NameHasFull]服下了一粒“[#ITEM_innoxia_pills_lubrication.getName(false)]”，现在在性爱过程中");
			if(target.hasPenisIgnoreDildo()) {
				sb.append("[npc.her]的肉棒会立刻分泌出先走液，而腔穴则会渗出天然润滑液。");
			} else {
				sb.append("[npc.her]的腔穴会渗出天然润滑液。");
			}
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CUM_PRODUCTION = new AbstractStatusEffect(80,
			"产生精液",
			"cumProduction",
			PresetColour.GENERIC_SEX,
			true,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			target.incrementPenisStoredCum(secondsPassed * target.getCumRegenerationPerSecond());
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumRegenRate = target.getCumRegenerationPerSecond()*60;
			
			return UtilText.parse(target, "[npc.NamePos]的[npc.balls]正分泌着更多的[npc.cum]，分泌速度为"+Units.fluid(cumRegenRate)+"/分钟。"
					+ "目前积蓄了"+Units.fluid(target.getPenisRawStoredCumValue())+"，上限为"+Units.fluid(target.getPenisRawCumStorageValue())+"。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)
					&& target.getPenisRawCumStorageValue()>0
					&& target.getPenisRawStoredCumValue()!=target.getPenisRawCumStorageValue()
					&& target.hasPenisIgnoreDildo()
					&& (target.isPlayer() || target.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect CUM_FULL = new AbstractStatusEffect(80,
			"满载",
			"cumFull",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumRegenRate = target.getCumRegenerationPerSecond()*60;
			
			return UtilText.parse(target, "[npc.NamePos]的[npc.balls]充满了[npc.cum]("+Units.fluid(target.getPenisRawCumStorageValue())+")，"
					+ "[npc.she]已经等不及要在下一次尽情射空了。"
					+ "[npc.She]会在高潮时射出"+Units.fluid(target.getPenisRawOrgasmCumQuantity())+"，并以"+Units.fluid(cumRegenRate)+"/分钟的速率重新分泌[npc.cum]。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)
					&& target.getPenisRawCumStorageValue()>0
					&& target.getPenisRawStoredCumValue()==target.getPenisRawCumStorageValue()
					&& target.hasPenisIgnoreDildo()
					&& (target.isPlayer() || target.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect MILK_PRODUCTION = new AbstractStatusEffect(80,
			"分泌乳汁",
			"milkProduction",
			PresetColour.GENERIC_SEX,
			true,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			target.incrementBreastStoredMilk(secondsPassed * target.getLactationRegenerationPerSecond(true));
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getLactationRegenerationPerSecond(false) * 60;

			return UtilText.parse(target,
					"[npc.NamePos]的乳房正在以"+Units.fluid(milkRegenRate)+"/分钟的速率分泌着[npc.milk]，"
							+ "总量为[style.colourGood("+Units.fluid(milkRegenRate * target.getBreastRows() * 2)+"/分钟)] ([npc.sheHasFull]的[npc.totalBreasts]乳房)。"
					+ "目前积蓄了"+Units.fluid(target.getBreastRawStoredMilkValue())+"，上限为"+Units.fluid(target.getBreastRawMilkStorageValue())+"。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getBreastRawMilkStorageValue()>0 && target.getBreastRawStoredMilkValue()<target.getBreastRawMilkStorageValue();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect MILK_FULL = new AbstractStatusEffect(80,
			"乳房胀满",
			"milkFull",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getLactationRegenerationPerSecond(false) * 60;
			//milkRegenRate+" |"+
			return UtilText.parse(target,
					"[npc.NamePos]的[npc.breasts]充满了"+Units.fluid(target.getBreastRawMilkStorageValue())+"的[npc.milk]。<br/>"
						+ "正在以"+Units.fluid(milkRegenRate)+"/分钟的速率分泌着[npc.milk]，"
						+ "总量为[style.colourGood("+Units.fluid(milkRegenRate * target.getBreastRows() * 2)+"/分钟)] ([npc.sheHasFull]的[npc.totalBreasts]乳房)。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getBreastRawMilkStorageValue()>0
					&& target.getBreastRawStoredMilkValue()==target.getBreastRawMilkStorageValue();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect MILK_CROTCH_PRODUCTION = new AbstractStatusEffect(80,
			"分泌胯乳乳汁",
			"milkCrotchProduction",
			PresetColour.GENERIC_SEX,
			true,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			if(target==null) {
				return super.getName(target);
			}
			return UtilText.parse(target, "[npc.CrotchBoob]乳汁分泌");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			target.incrementBreastCrotchStoredMilk((secondsPassed) * target.getCrotchLactationRegenerationPerSecond(true));
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getCrotchLactationRegenerationPerSecond(false) * 60;
			
			return UtilText.parse(target,
					"[npc.NamePos]的[npc.crotchBoobs]正在以"+Units.fluid(milkRegenRate)+"/分钟的速率分泌着[npc.crotchMilk]，"
							+ "总量为[style.colourGood("+Units.fluid(milkRegenRate * Math.max(1, target.getBreastCrotchRows()*2))+"/分钟)] ([npc.sheHasFull]的[npc.totalCrotchBoobs][npc.crotchBoobs])。"
					+ "目前积蓄了"+Units.fluid(target.getBreastCrotchRawStoredMilkValue())+"，上限为"+Units.fluid(target.getBreastCrotchRawMilkStorageValue())+"。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasBreastsCrotch()
					&& target.getBreastCrotchRawMilkStorageValue()>0
					&& target.getBreastCrotchRawStoredMilkValue()!=target.getBreastCrotchRawMilkStorageValue();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect MILK_CROTCH_FULL = new AbstractStatusEffect(80,
			"胯乳胀满",
			"milkCrotchFull",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String getName(GameCharacter target) {
			if(target==null) {
				return super.getName(target);
			}
			return UtilText.parse(target, "装满的[npc.CrotchBoobs]");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getCrotchLactationRegenerationPerSecond(false) * 60;
		
			return UtilText.parse(target,
					"[npc.NamePos]的[npc.crotchBoobs]充满了"+Units.fluid(target.getBreastCrotchRawMilkStorageValue())+"[npc.crotchMilk]。<br/>"
							+ "正在以"+Units.fluid(milkRegenRate)+"/分钟的速率分泌着[npc.crotchMilk]，"
							+ "总量为[style.colourGood("+Units.fluid(milkRegenRate * Math.max(1, target.getBreastCrotchRows()*2))+"/分钟)] ([npc.sheHasFull]的[npc.totalCrotchBoobs][npc.crotchBoobs])。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasBreastsCrotch()
					&& target.getBreastCrotchRawMilkStorageValue()>0
					&& target.getBreastCrotchRawStoredMilkValue()==target.getBreastCrotchRawMilkStorageValue();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect STRETCHING_ORIFICE = new AbstractStatusEffect(80,
			"玩具过大",
			"sexEffects/combinationStretching",
			PresetColour.BASE_MAGENTA,
			false,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			float minimumStretchPercentage = 0.00_05f;
			
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificeStretching().entrySet()) {
				AbstractClothing clothing = entry.getValue();
				int length = clothing.getClothingType().getPenetrationSelfLength();
				PenetrationGirth girth = PenetrationGirth.getGirthFromInt(clothing.getClothingType().getPenetrationSelfGirth());
				float diameter = Penis.getGenericDiameter(length, girth);
				
				// Stretch out the orifice by a factor of elasticity's modifier:
				float stretchModifier = 600f;
				float stretch = 0f;
				switch(entry.getKey()) {
					case ANUS:
						stretch = Math.max(diameter*minimumStretchPercentage, (diameter-target.getAssStretchedCapacity())*target.getAssElasticity().getStretchModifier());
						target.incrementAssStretchedCapacity((stretch/stretchModifier) * secondsPassed);
						if(target.getAssStretchedCapacity()>diameter) {
							target.setAssStretchedCapacity(diameter);
						}
						break;
					case MOUTH:
						stretch = Math.max(diameter*minimumStretchPercentage, (diameter-target.getFaceStretchedCapacity())*target.getFaceElasticity().getStretchModifier());
						target.incrementFaceStretchedCapacity((stretch/stretchModifier) * secondsPassed);
						if(target.getFaceStretchedCapacity()>diameter) {
							target.setFaceStretchedCapacity(diameter);
						}
						break;
					case NIPPLE:
						stretch = Math.max(diameter*minimumStretchPercentage, (diameter-target.getNippleStretchedCapacity())*target.getNippleElasticity().getStretchModifier());
						target.incrementNippleStretchedCapacity((stretch/stretchModifier) * secondsPassed);
						if(target.getNippleStretchedCapacity()>diameter) {
							target.setNippleStretchedCapacity(diameter);
						}
						break;
					case VAGINA:
						stretch = Math.max(diameter*minimumStretchPercentage, (diameter-target.getVaginaStretchedCapacity())*target.getVaginaElasticity().getStretchModifier());
						target.incrementVaginaStretchedCapacity((stretch/stretchModifier) * secondsPassed);
						if(target.getVaginaStretchedCapacity()>diameter) {
							target.setVaginaStretchedCapacity(diameter);
						}
						break;
					default:
						break;
				}
			}
			
			return "";
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> effects = new ArrayList<>();
			
			Map<SexAreaOrifice, String> orifices = Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, "[style.boldVagina(阴道)]:"),
					new Value<>(SexAreaOrifice.ANUS, "[style.boldAsshole(后穴)]:"),
					new Value<>(SexAreaOrifice.MOUTH, "[style.boldMouth(喉咙)]:"),
					new Value<>(SexAreaOrifice.NIPPLE, "[style.boldNipple(奶头)]:"));
			List<SexAreaOrifice> orificesFound = new ArrayList<>();
			for(Entry<SexAreaOrifice, String> orifice : orifices.entrySet()) {
				if(target.getSexToyOrificeStretching().containsKey(orifice.getKey())) {
					AbstractClothing clothing = target.getSexToyOrificeStretching().get(orifice.getKey());
					int length = clothing.getClothingType().getPenetrationSelfLength();
					PenetrationGirth girth = PenetrationGirth.getGirthFromInt(clothing.getClothingType().getPenetrationSelfGirth());
					float diameter = Penis.getGenericDiameter(length, girth);
					
					effects.add(orifice.getValue());
					effects.add("[style.boldBad(扩张)]至[style.boldPinkLight("+Units.size(diameter)+")]");
					orificesFound.add(orifice.getKey());
				}
				if(target.getSexToyOrificePreventingStretchRecovery().containsKey(orifice.getKey())) {
					if(!orificesFound.contains(orifice.getKey())) {
						effects.add(orifice.getValue());
					}
					effects.add("[style.boldMinorBad(阻止)][style.boldPinkLight(扩张恢复)]");
					orificesFound.add(orifice.getKey());
				}
				if(target.getSexToyOrificeTooDeep().containsKey(orifice.getKey())) {
					AbstractClothing clothing = target.getSexToyOrificeTooDeep().get(orifice.getKey());
					int length = clothing.getClothingType().getPenetrationSelfLength();
					if(!orificesFound.contains(orifice.getKey())) {
						effects.add(orifice.getValue());
					}
					effects.add("[style.boldPinkLight("+Units.size(length)+")][style.boldTerrible(太深)]");
				}
			}
			
			return effects;
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			
			List<String> stretching = new ArrayList<>();
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificeStretching().entrySet()) {
				switch(entry.getKey()) {
					case ANUS:
						stretching.add("[style.colourAsshole(asshole)]");
						break;
					case MOUTH:
						stretching.add("[style.colourMouth(throat)]");
						break;
					case NIPPLE:
						stretching.add("[style.colourNipple(nipples)]");
						break;
					case VAGINA:
						stretching.add("[style.colourVagina(vagina)]");
						break;
					default:
						break;
				}
			}
			List<String> preventingRecovery = new ArrayList<>();
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificePreventingStretchRecovery().entrySet()) {
				switch(entry.getKey()) {
					case ANUS:
						preventingRecovery.add("[style.colourAsshole(asshole)]");
						break;
					case MOUTH:
						preventingRecovery.add("[style.colourMouth(throat)]");
						break;
					case NIPPLE:
						preventingRecovery.add("[style.colourNipple(nipples)]");
						break;
					case VAGINA:
						preventingRecovery.add("[style.colourVagina(vagina)]");
						break;
					default:
						break;
				}
			}
			List<String> tooDeep = new ArrayList<>();
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificeTooDeep().entrySet()) {
				switch(entry.getKey()) {
					case ANUS:
						tooDeep.add("[style.colourAsshole(asshole)]");
						break;
					case MOUTH:
						tooDeep.add("[style.colourMouth(throat)]");
						break;
					case NIPPLE:
						tooDeep.add("[style.colourNipple(nipples)]");
						break;
					case VAGINA:
						tooDeep.add("[style.colourVagina(vagina)]");
						break;
					default:
						break;
				}
			}
			
			boolean needBreak = false;
			if(!target.getSexToyOrificeStretching().isEmpty()) {
				boolean singular = target.getSexToyOrificeStretching().size()==1;
				descriptionSB.append("[npc.NamePos]的性玩具太大了，因而导致[npc.her]的"
						+Util.stringsToStringList(stretching, false)+"正在被[style.colourBad(扩张)]！");
				needBreak = true;
			}
			
			if(!target.getSexToyOrificePreventingStretchRecovery().isEmpty()) {
				if(needBreak) {
					descriptionSB.append("");
				}
				boolean singular = target.getSexToyOrificePreventingStretchRecovery().size()==1;
				descriptionSB.append("[npc.NamePos]的性玩具正在阻止[npc.her][style.colourMinorBad(被扩张的)]" +Util.stringsToStringList(preventingRecovery, false)+"恢复！");
				needBreak = true;
			}
			
			if(Main.game.isPenetrationLimitationsEnabled()) {
				if(!target.getSexToyOrificeTooDeep().isEmpty()) {
					if(needBreak) {
						descriptionSB.append("");
					}
					boolean singular = target.getSexToyOrificeTooDeep().size()==1;
					descriptionSB.append("插入[npc.namePos]"
							+Util.stringsToStringList(tooDeep, false)+"的性玩具[style.colourTerrible(太深了，造成了不适)]！");
				}
			}
			
			return UtilText.parse(target, descriptionSB.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.getSexToyOrificeTooDeep().isEmpty() || !target.getSexToyOrificeStretching().isEmpty() || !target.getSexToyOrificePreventingStretchRecovery().isEmpty();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter target) {
			Set<SexAreaOrifice> orifices = new HashSet<>(target.getSexToyOrificeStretching().keySet());
			orifices.addAll(target.getSexToyOrificePreventingStretchRecovery().keySet());
			orifices.addAll(target.getSexToyOrificeTooDeep().keySet());
			return getStretchingOrificeStatus(target, !target.getSexToyOrificeStretching().isEmpty(), !target.getSexToyOrificePreventingStretchRecovery().isEmpty(), !target.getSexToyOrificeTooDeep().isEmpty(), orifices);
		}
	};
	
	public static AbstractStatusEffect RECOVERING_ORIFICE = new AbstractStatusEffect(80,
			"阴道恢复",
			"recoveringOrifice",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -2f), new Value<>(Attribute.HEALTH_MAXIMUM, -5f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			int i=0;
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.isInSex()) {
				sb.append("被扩张");
			} else {
				sb.append("恢复中");
			}
			
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				sb.append("阴部");
				i++;
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				sb.append("肛门");
				i++;
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				sb.append("乳头");
				i++;
			}
			if (target.hasBreastsCrotch()
					&& target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				sb.append("胯乳乳头");
				i++;
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				sb.append("阴茎尿道");
				i++;
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				sb.append("阴道尿道");
				i++;
			}
			if (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				sb.append("喉咙");
				i++;
			}
			
			if(i>1) {
				if(Main.game.isInSex()) {
					return "被扩张的腔穴";
				} else {
					return "恢复中的腔穴";
				}
			} else {
				return sb.toString();
			}
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			
			Set<SexAreaOrifice> stretchRecoveryPrevention = new HashSet<>(target.getSexToyOrificePreventingStretchRecovery().keySet());
			stretchRecoveryPrevention.addAll(target.getSexToyOrificeStretching().keySet());
			
			if(!stretchRecoveryPrevention.contains(SexAreaOrifice.VAGINA) && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				target.incrementVaginaStretchedCapacity(-target.getVaginaPlasticity().getRecoveryModifier()*secondsPassed);

				if(target.getVaginaStretchedCapacity()<target.getVaginaRawCapacityValue()) {
					target.setVaginaStretchedCapacity(target.getVaginaRawCapacityValue());
				}
			}
			if(!stretchRecoveryPrevention.contains(SexAreaOrifice.ANUS) && target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				target.incrementAssStretchedCapacity(-target.getAssPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getAssStretchedCapacity()<target.getAssRawCapacityValue()) {
					target.setAssStretchedCapacity(target.getAssRawCapacityValue());
				}
			}
			if(!stretchRecoveryPrevention.contains(SexAreaOrifice.NIPPLE) && target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				target.incrementNippleStretchedCapacity(-target.getNipplePlasticity().getRecoveryModifier()*secondsPassed);

				if(target.getNippleStretchedCapacity()<target.getNippleRawCapacityValue()) {
					target.setNippleStretchedCapacity(target.getNippleRawCapacityValue());
				}
			}
			if(!stretchRecoveryPrevention.contains(SexAreaOrifice.MOUTH) && target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				target.incrementFaceStretchedCapacity(-target.getFacePlasticity().getRecoveryModifier()*secondsPassed);

				if(target.getFaceStretchedCapacity()<target.getFaceRawCapacityValue()) {
					target.setFaceStretchedCapacity(target.getFaceRawCapacityValue());
				}
			}
			if(target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				target.incrementNippleCrotchStretchedCapacity(-target.getNippleCrotchPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getNippleCrotchStretchedCapacity()<target.getNippleCrotchRawCapacityValue()) {
					target.setNippleCrotchStretchedCapacity(target.getNippleCrotchRawCapacityValue());
				}
			}
			if(target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				target.incrementPenisStretchedCapacity(-target.getUrethraPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getPenisStretchedCapacity()<target.getPenisRawCapacityValue()) {
					target.setPenisStretchedCapacity(target.getPenisRawCapacityValue());
				}
			}
			if(target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				target.incrementVaginaUrethraStretchedCapacity(-target.getVaginaUrethraPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getVaginaUrethraStretchedCapacity()<target.getVaginaUrethraRawCapacityValue()) {
					target.setVaginaUrethraStretchedCapacity(target.getVaginaUrethraRawCapacityValue());
				}
			}
			
			return "";
		}
		
		private String getRecoveryText(float recoveryModifier) {
			int minutes = (int) (1/recoveryModifier)/60;
			int hours = minutes /60;
			
			return "回缩"+Units.size(1)+"每"+(hours>=1
						?(hours)+"时"+(minutes%60>0?"和"+(minutes%60)+"分":"")
						:(minutes )+"分");
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> recoveringEffects = new ArrayList<>();

			Set<SexAreaOrifice> stretchRecoveryPrevention = new HashSet<>(target.getSexToyOrificePreventingStretchRecovery().keySet());
			stretchRecoveryPrevention.addAll(target.getSexToyOrificeStretching().keySet());
			
			String recoveringText = "恢复";
			String from1 = "自";
			String from2 = "至";
			
			if(Main.game.isInSex()) {
				recoveringText = "被扩张";
				from1 = "自";
				from2 = "至";
			}
			
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				recoveringEffects.add("[style.boldVagina(阴道"+recoveringText+":)]");
				recoveringEffects.add(from1+"[style.boldBad("+Units.size(target.getVaginaStretchedCapacity())+")]"+from2+"[style.boldGood("+Units.size(target.getVaginaRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.VAGINA)) {
					recoveringEffects.add("[style.boldBad(性玩具阻止了恢复！)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getVaginaPlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				recoveringEffects.add("[style.boldAsshole(肛门"+recoveringText+":)]");
				recoveringEffects.add(from1+"[style.boldBad("+Units.size(target.getAssStretchedCapacity())+")]"+from2+"[style.boldGood("+Units.size(target.getAssRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.ANUS)) {
					recoveringEffects.add("[style.boldBad(性玩具阻止了恢复！)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getAssPlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				recoveringEffects.add("[style.boldNipples(乳头"+recoveringText+":)]");
				recoveringEffects.add(from1+"[style.boldBad("+Units.size(target.getNippleStretchedCapacity())+")]"+from2+"[style.boldGood("+Units.size(target.getNippleRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.NIPPLE)) {
					recoveringEffects.add("[style.boldBad(性玩具阻止了恢复！)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getNipplePlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				recoveringEffects.add("[style.boldMouth(喉咙"+recoveringText+":)]");
				recoveringEffects.add(from1+"[style.boldBad("+Units.size(target.getFaceStretchedCapacity())+")]"+from2+"[style.boldGood("+Units.size(target.getFaceRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.MOUTH)) {
					recoveringEffects.add("[style.boldBad(性玩具阻止了恢复！)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getFacePlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.hasBreastsCrotch() && target.hasBreastsCrotch() && target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				recoveringEffects.add("[style.boldNipplesCrotch(胯乳乳头"+recoveringText+"：)]");
				recoveringEffects.add(from1+"[style.boldBad("+Units.size(target.getNippleCrotchStretchedCapacity())+")]"+from2+"[style.boldGood("+Units.size(target.getNippleCrotchRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getNippleCrotchPlasticity().getRecoveryModifier())+")]");
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				recoveringEffects.add("[style.boldPenisUrethra(阴茎尿道"+recoveringText+"：)]");
				recoveringEffects.add(from1+"[style.boldBad("+Units.size(target.getPenisStretchedCapacity())+")] "+from2+"[style.boldGood("+Units.size(target.getPenisRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getUrethraPlasticity().getRecoveryModifier())+")]");
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				recoveringEffects.add("[style.boldVaginaUrethra(阴道尿道"+recoveringText+"：)]");
				recoveringEffects.add(from1+"[style.boldBad("+Units.size(target.getVaginaUrethraStretchedCapacity())+")] "+from2+"[style.boldGood("+Units.size(target.getVaginaUrethraRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getVaginaUrethraPlasticity().getRecoveryModifier())+")]");
			}
			
			
			return recoveringEffects;
		}
		@Override
		public String getDescription(GameCharacter target) {
			
			StringBuilder descriptionSB = new StringBuilder("在被迫适应");
			
			List<String> orificesRecovering = new ArrayList<>();
			boolean plural = false;
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				orificesRecovering.add("[style.boldVagina(阴道)]");
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				orificesRecovering.add("[style.boldAnus(肛门)]");
			}
			if (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				orificesRecovering.add("[style.boldMouth(喉咙)]");
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				orificesRecovering.add("[style.boldNipple(乳头)]");
				plural = true;
			}
			if (target.hasBreastsCrotch() && target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				orificesRecovering.add("[style.boldNipplesCrotch(胯乳乳头)]");
				plural = true;
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				orificesRecovering.add("[style.boldPenisUrethra(阴茎尿道)]");
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				orificesRecovering.add("[style.boldVaginaUrethra(阴道尿道)]");
			}
			if(orificesRecovering.size()==1) {
				descriptionSB.append("一件远大于其的物体后，[npc.namePos]"+orificesRecovering.get(0)+"已经"
						+"被扩张开来，需要时间来恢复至自然紧度。");
			} else {
				descriptionSB.append("插入物太过粗大，[npc.namePos]"+Util.stringsToStringList(orificesRecovering, false)
						+"被扩张开来，需要时间来恢复至自然紧度。");
			}

			if(Main.game.isInSex()) {
				descriptionSB.append("[style.italicsBad(腔穴在性交场景内不进行恢复！)]");
			}
			
			return UtilText.parse(target, descriptionSB.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ((target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity())
					|| (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity())
					|| (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity())
					|| (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity())
					|| (target.hasBreastsCrotch() && target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity())
					|| (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity())
					|| (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getRecoveringOrificeStatus(owner, super.getSVGString(owner));
		}
	};
	
	
	
	public static AbstractStatusEffect CREAMPIE_VAGINA = new AbstractStatusEffect(80,
			"小穴内射",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>脏衣服</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			AbstractClothing pump = target.getClothingInSlot(InventorySlot.VAGINA);
			if(pump!=null && pump.isMilkingEquipment()) {
				target.clearFluidsStored(SexAreaOrifice.VAGINA);
				if(!Main.game.isInSex()) {
					return "<p>"
								+ "你被内射过的小穴里的精液正在被"+pump.getName()+"迅速地吸走！"
							+ "</p>";
				}
			}
			
			if(Main.game.isInSex()) {
				return "";
			}
			
			float cumLost = SexAreaOrifice.VAGINA.getCharactersCumLossPerSecond(target)*secondsPassed;
			
			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.VAGINA);
			boolean dirtyArea = clothingBlocking==null;
			
			if(clothingBlocking!=null
					&& !clothingBlocking.getItemTags().contains(ItemTag.PLUGS_VAGINA)
					&& !clothingBlocking.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(内射)]", "[style.colourDirty(脏污了)]"+clothingBlocking.getName()), false);
					}
				}
				dirtyArea = true;
			}
			
			if(dirtyArea) {
				if(!target.getDirtySlots().contains(InventorySlot.VAGINA)) {
					target.addDirtySlot(InventorySlot.VAGINA);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.VAGINA);
			String pregnancyText = (target.isVisiblyPregnant()?"<br/>[style.italicsSex(由于已经怀孕，最大储量为"+Units.fluid(Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT)+".)]":"");
			
			if(target.isOrificePlugged(SexAreaOrifice.VAGINA)) {
				if(target.isPlayer()) {
					return "随着你的走动，你可以感觉到被操过的小穴正在往下流着精液。"
							+pregnancyText
							+ "<br/>[style.boldTerrible(阴道被堵塞:)]没有精液漏出"
							+ (retention?"！":"，但仍旧会被吸收！");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos]的[npc.pussy]最近被精液填满，而且堵住了。"
							+pregnancyText
							+ "<br/>[style.boldTerrible(阴道被堵塞:)]没有精液漏出"
							+ (retention?"！":"，但仍旧会被吸收！"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "随着你的走动，你可以感觉到被操过的小穴正在往外流着精液。"
							+pregnancyText;
				} else {
					return UtilText.parse(target,
							"[npc.NamePos]的[npc.pussy]已经被精液填满了。"
							+pregnancyText);
				}
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.VAGINA;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"当前灌入：[style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/分钟"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/分钟")
					+(retention
							?"<br/>由于附魔，[style.colourExcellent(维持)]了内射状态！"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.VAGINA)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.VAGINA);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_VAGINA_URETHRA = new AbstractStatusEffect(80,
			"阴道尿道内射",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>脏衣服</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.URETHRA_VAGINA.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			List<AbstractClothing> blockingList = target.getBlockingCoverableAreaClothingList(CoverableArea.VAGINA, false);
			boolean pluggedFound = blockingList.removeIf((c) -> c.getItemTags().contains(ItemTag.PLUGS_VAGINA));
			blockingList.sort((c1, c2) -> c1.getSlotEquippedTo().getZLayer()-c2.getSlotEquippedTo().getZLayer());
			AbstractClothing clothingBlocking = blockingList.isEmpty()?null:blockingList.get(0);
//			if(clothingBlocking!=null) {
//				System.out.println(clothingBlocking.getName());
//			}
			boolean dirtyArea = clothingBlocking==null;

			// 'PLUGS_VAGINA' check doesn't entirely make sense, but if not included, and the character has a urethral and normal creampie, then the game enters a loop of cleaning-dirtying if a dildo is inserted into the vagina
			if(clothingBlocking!=null
//					&& !clothingBlocking.getItemTags().contains(ItemTag.PLUGS_VAGINA)
					&& !clothingBlocking.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(内射)]", "[style.colourDirty(脏污了)]"+clothingBlocking.getName()), false);
					}
				}
				dirtyArea = true;
			}
			
			if(dirtyArea && !pluggedFound && (clothingBlocking==null || !clothingBlocking.getItemTags().contains(ItemTag.SEALS_VAGINA))) {
				if(!target.getDirtySlots().contains(InventorySlot.VAGINA)) {
					target.addDirtySlot(InventorySlot.VAGINA);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.URETHRA_VAGINA);
			String pregnancyText = (target.isVisiblyPregnant()?"<br/>[style.italicsSex(由于已经怀孕，最大储量为"+Units.fluid(Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT)+".)]":"");
			
			if(target.isOrificePlugged(SexAreaOrifice.URETHRA_VAGINA)) {
				if(target.isPlayer()) {
					return "随着你的走动，你可以感觉到被密封在阴部尿道中的精液。"
							+pregnancyText
							+ "<br/>[style.boldTerrible(尿道被堵塞:)]没有精液漏出"
							+ (retention?"！":"，但仍旧会被吸收！");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos]的阴部尿道最近被精液填满，而且堵住了。"
							+pregnancyText
							+ "<br/>[style.boldTerrible(尿道被堵塞:)]没有精液漏出"
							+ (retention?"！":"，但仍旧会被吸收！"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "随着你的走动，你可以感觉到阴部尿道正在往外流着精液。"
							+pregnancyText;
				} else {
					return UtilText.parse(target,
							"[npc.NamePos]的阴道尿道已经被精液填满了。"
							+pregnancyText);
				}
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.URETHRA_VAGINA;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"当前灌入：[style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/分钟"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/分钟")
					+(retention
							?"<br/>由于附魔，[style.colourExcellent(维持)]了内射状态！"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.URETHRA_VAGINA)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.URETHRA_VAGINA);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_PENIS_URETHRA = new AbstractStatusEffect(80,
			"阴茎尿道内射",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>脏衣服</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			AbstractClothing pump = target.getClothingInSlot(InventorySlot.PENIS);
			if(pump!=null && pump.isMilkingEquipment()) {
				target.clearFluidsStored(SexAreaOrifice.URETHRA_PENIS);
				if(!Main.game.isInSex()) {
					return "<p>"
								+ "你被内射过的阴茎尿道里的精液正在被"+pump.getName()+"迅速地吸走！"
							+ "</p>";
				}
			}
			
			if(Main.game.isInSex()) {
				return "";
			}
			
			float cumLost = SexAreaOrifice.URETHRA_PENIS.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.PENIS);
			
			if(clothingBlocking!=null){
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(内射)]", "[style.colourDirty(脏污了)]"+clothingBlocking.getName()), false);
					}
				}
			}
			
			if(!target.getDirtySlots().contains(InventorySlot.PENIS)) {
				target.addDirtySlot(InventorySlot.PENIS);
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_PENIS, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.URETHRA_PENIS);
			
			if(target.isOrificePlugged(SexAreaOrifice.URETHRA_PENIS)) {
				if(target.isPlayer()) {
					return "随着你的走动，你可以感觉到被密封在阴茎尿道中的精液。"
							+ "<br/>[style.boldTerrible(尿道被堵塞:)]没有精液漏出"
							+ (retention?"！":"，但仍旧会被吸收！");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos]的阴茎尿道最近被精液填满，而且堵住了。"
							+ "<br/>[style.boldTerrible(尿道被堵塞:)]没有精液漏出"
							+ (retention?"！":"，但仍旧会被吸收！"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "随着你的走动，你可以感觉到阴茎的尿道正在往外流着精液。";
				} else {
					return UtilText.parse(target,
							"[npc.NamePos]的阴茎尿道已经被精液填满了。");
				}
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.URETHRA_PENIS;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"当前灌入：[style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/分钟"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/分钟")
					+(retention
							?"<br/>由于附魔，[style.colourExcellent(维持)]了内射状态！"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.URETHRA_PENIS)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.URETHRA_PENIS);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_ANUS = new AbstractStatusEffect(80,
			"肛门内射",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>脏衣服</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.ANUS.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.ANUS);
			boolean dirtyArea = clothingBlocking==null;
			
			if(clothingBlocking!=null && !clothingBlocking.getItemTags().contains(ItemTag.PLUGS_ANUS) && !clothingBlocking.getItemTags().contains(ItemTag.SEALS_ANUS)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(内射)]", "[style.colourDirty(脏污了)]"+clothingBlocking.getName()), false);
					}
				}
				dirtyArea = true;
			}
			
			if(dirtyArea) {
				if(!target.getDirtySlots().contains(InventorySlot.ANUS)) {
					target.addDirtySlot(InventorySlot.ANUS);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.ANUS, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.ANUS);
			
			if(target.isOrificePlugged(SexAreaOrifice.ANUS)) {
				if(target.isPlayer()) {
					return "随着你的走动，你可以感觉到被操过的肛门正在往下流着精液。"
							+ "<br/>[style.boldTerrible(肛门被堵塞:)]没有精液漏出"
							+ (retention?"！":"，但仍旧会被吸收！");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos]的肛门最近被精液填满，而且堵住了。"
							+ "<br/>[style.boldTerrible(肛门被堵塞:)]没有精液漏出"
							+ (retention?"！":"，但仍旧会被吸收！"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "随着你的走动，你可以感觉到被操过的肛门正在往外流着精液。";
				} else {
					return UtilText.parse(target,
							"[npc.NamePos]的肛门已经被精液填满了。");
				}
			}
		
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.ANUS;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"当前灌入：[style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/分钟"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/分钟")
					+(retention
							?"<br/>由于附魔，[style.colourExcellent(维持)]了内射状态！"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.ANUS)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.ANUS);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_NIPPLES = new AbstractStatusEffect(80,
			"乳头内射",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>脏衣服</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			AbstractClothing pump = target.getClothingInSlot(InventorySlot.NIPPLE);
			if(pump!=null && pump.isMilkingEquipment()) {
				target.clearFluidsStored(SexAreaOrifice.NIPPLE);
				if(!Main.game.isInSex()) {
					return "<p>"
								+ "你被内射过的乳头里的精液正在被"+pump.getName()+"迅速地吸走！"
							+ "</p>";
				}
			}
			
			if(Main.game.isInSex()) {
				return "";
			}
			
			float cumLost = SexAreaOrifice.NIPPLE.getCharactersCumLossPerSecond(target) * secondsPassed;

			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES);
			boolean dirtyArea = clothingBlocking==null;
			
			if(clothingBlocking!=null && !clothingBlocking.getItemTags().contains(ItemTag.PLUGS_NIPPLES) && !clothingBlocking.getItemTags().contains(ItemTag.SEALS_NIPPLES)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(内射)]", "[style.colourDirty(脏污了)]"+clothingBlocking.getName()), false);
					}
				}
				dirtyArea = true;
			}
			
			if(dirtyArea) {
				if(!target.getDirtySlots().contains(InventorySlot.NIPPLE)) {
					target.addDirtySlot(InventorySlot.NIPPLE);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.NIPPLE, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.NIPPLE);
			
			if(target.isOrificePlugged(SexAreaOrifice.NIPPLE)) {
				if(target.isPlayer()) {
					return "随着你的走动，你可以感觉到被操过的[pc.nipples]正在往下流着精液。"
							+ "<br/>[style.boldTerrible(乳头被堵塞:)]没有精液漏出"
							+ (retention?"！":"，但仍旧会被吸收！");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos]的[npc.nipples]最近被精液填满，而且堵住了。"
							+ "<br/>[style.boldTerrible(乳头被堵塞:)]没有精液漏出"
							+ (retention?"！":"，但仍旧会被吸收！"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "随着你的走动，你可以感觉到被操过的[pc.nipples]正在往外流着精液。";
				} else {
					return UtilText.parse(target,
							"[npc.NamePos]的[npc.nipples]已经被精液填满了。");
				}
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.NIPPLE;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"当前灌入：[style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/分钟"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/分钟")
					+(retention
							?"<br/>由于附魔，[style.colourExcellent(维持)]了内射状态！"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.NIPPLE)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.NIPPLE);
		}
	};

	public static AbstractStatusEffect CREAMPIE_NIPPLES_CROTCH = new AbstractStatusEffect(80,
			"乳头内射",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>脏衣服</b>")) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "腹乳乳头内射";
			} else {
				return "胯乳乳头内射";
			}
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			AbstractClothing pump = target.getClothingInSlot(InventorySlot.STOMACH);
			if(pump!=null && pump.isMilkingEquipment()) {
				target.clearFluidsStored(SexAreaOrifice.NIPPLE_CROTCH);
				if(!Main.game.isInSex()) {
					return "<p>"
								+ "你被内射过的乳头里的精液正在被"+pump.getName()+"迅速地吸走！"
							+ "</p>";
				}
			}
			
			if(Main.game.isInSex()) {
				return "";
			}
			
			float cumLost = SexAreaOrifice.NIPPLE_CROTCH.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH);
			
			if(clothingBlocking!=null){
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(内射)]", "[style.colourDirty(脏污了)]"+clothingBlocking.getName()), false);
					}
				}
			}
			
			if(!target.getDirtySlots().contains(InventorySlot.STOMACH)) {
				target.addDirtySlot(InventorySlot.STOMACH);
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.NIPPLE_CROTCH, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.NIPPLE_CROTCH);
			
			if(target.isOrificePlugged(SexAreaOrifice.NIPPLE_CROTCH)) {
				if(target.isPlayer()) {
					return "随着你的走动，你可以感觉到被操过的[pc.crotchNipples]中密封的精液。"
							+ "<br/>[style.boldTerrible(胯乳乳头被堵塞:)]没有精液漏出"
							+ (retention?"！":"，但仍旧会被吸收！");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos]的[npc.crotchNipples]最近被精液填满，而且堵住了。"
							+ "<br/>[style.boldTerrible(胯乳乳头被堵塞:)]没有精液漏出"
							+ (retention?"！":"，但仍旧会被吸收！"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "随着你的走动，你可以感觉到被操过的[pc.crotchNipples]正在往外流着精液。";
				} else {
					return UtilText.parse(target,
							"[npc.NamePos]的[npc.crotchNipples]已经被精液填满了。");
				}
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.NIPPLE_CROTCH;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"当前灌入：[style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/分钟"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/分钟")
					+(retention
							?"<br/>由于附魔，[style.colourExcellent(维持)]了内射状态！"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.NIPPLE_CROTCH)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.NIPPLE_CROTCH);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_MOUTH = new AbstractStatusEffect(80,
			"精味大餐",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return target.isOnlyCumInArea(SexAreaOrifice.MOUTH)
					?"精味大餐"
					:"好味大餐";
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			if(this.getExtraEffects(target)!=null) {
				attributeModifiersList.addAll(this.getExtraEffects(target));
			}
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.MOUTH.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			target.drainTotalFluidsStored(SexAreaOrifice.MOUTH, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, 
					target.isOnlyCumInArea(SexAreaOrifice.MOUTH)
						?"[npc.NameHasFull]最近咽下了精液。"
						:"[npc.NameHasFull]最近咽下了某种性液体。");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.MOUTH;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"当前灌入：[style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/分钟"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/分钟")
					+(retention
							?"<br/>由于附魔，[style.colourExcellent(维持)]了内射状态！"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.MOUTH)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.MOUTH);
		}
	};

	public static AbstractStatusEffect CREAMPIE_SPINNERET = new AbstractStatusEffect(80,
			"丝囊内射",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>脏衣服</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.SPINNERET.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(SexAreaOrifice.SPINNERET.getRelatedCoverableArea(target));
			boolean dirtyArea = clothingBlocking==null;

			if(clothingBlocking!=null){
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(内射)]", "[style.colourDirty(脏污了)]"+clothingBlocking.getName()), false);
					}
				}
			}
			
			if(dirtyArea) {
				InventorySlot spinneretSlot = SexAreaOrifice.SPINNERET.getRelatedInventorySlot(target);
				if(!target.getDirtySlots().contains(spinneretSlot)) {
					target.addDirtySlot(spinneretSlot);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.SPINNERET, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "随着你的走动，你可以感觉到被操过的丝囊正在往外流着精液。";
			} else {
				return UtilText.parse(target, 
						"[npc.NamePos]的丝囊已经被精液填满了。");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.SPINNERET;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"当前灌入：[style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/分钟"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/分钟")
					+(retention
							?"<br/>由于附魔，[style.colourExcellent(维持)]了内射状态！"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.SPINNERET)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.SPINNERET);
		}
	};
	
	public static AbstractStatusEffect CUM_INFLATION_1 = new AbstractStatusEffect(80,
			"微肿的小腹",
			"inflation_stomach_1",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.ANUS).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.MOUTH).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("液体");
			}
			if(target.isPlayer()) {
				return "你的腹中充满着不少的"+Util.stringsToStringList(fluidNames, false)+"，看起来微微隆起。"
						+ "额外的重量感为你的移动造成了不便。";
			} else {
				return UtilText.parse(target,
							"你的腹中充满着不少的"+Util.stringsToStringList(fluidNames, false)+"，看起来微微隆起。");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS) + target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CUM_INFLATION_2 = new AbstractStatusEffect(80,
			"肿胀的小腹",
			"inflation_stomach_2",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.ANUS).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.MOUTH).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("液体");
			}
			if(target.isPlayer()) {
				return "你的腹中充满着大量的"+Util.stringsToStringList(fluidNames, false)+"，看起来十分明显。"
						+ "你腹中充实的重量感令你更难移动。";
			} else {
				return UtilText.parse(target,
							"你的腹中充满着大量的"+Util.stringsToStringList(fluidNames, false)+"，看起来十分明显。"
							+ "[npc.her]腹中充实的重量感令[npc.her]更难移动。");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS) + target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CUM_INFLATION_3 = new AbstractStatusEffect(80,
			"过于肿胀的小腹",
			"inflation_stomach_3",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.ANUS).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.MOUTH).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("液体");
			}
			if(target.isPlayer()) {
				return "你的腹中充满着巨量的"+Util.stringsToStringList(fluidNames, false)+"，看起来十分肿胀。"
						+ "你腹中沉重感令你几乎无法移动。";
			} else {
				return UtilText.parse(target,
							"你的腹中充满着巨量的"+Util.stringsToStringList(fluidNames, false)+"，看起来无比肿胀。"
									+ "[npc.her]腹中沉重感令[npc.herHim]几乎无法移动。");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS) + target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	
	public static AbstractStatusEffect BREAST_CUM_INFLATION_1 = new AbstractStatusEffect(80,
			"微肿的乳房",
			"inflation_breasts_1",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.NIPPLE).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("液体");
			}
			if(target.isPlayer()) {
				return "你的乳房里充满着不少的"+Util.stringsToStringList(fluidNames, false)+"，看起来微微隆起。" 
						+ "额外的重量感为你的移动造成了不便。";
			} else {
				return UtilText.parse(target,
							"[npc.namePos]的[npc.breasts]里充满着不少的"+Util.stringsToStringList(fluidNames, false)+"，看起来微微隆起。");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect BREAST_CUM_INFLATION_2 = new AbstractStatusEffect(80,
			"肿胀的乳房",
			"inflation_breasts_2",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.NIPPLE).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("液体");
			}
			if(target.isPlayer()) {
				return "你的[pc.breasts]充满着大量的"+Util.stringsToStringList(fluidNames, false)+"，看起来十分明显。"
						+ "你上半身附带的充实重量感令你更难移动。";
			} else {
				return UtilText.parse(target,
							"[npc.namePos]的[npc.breasts]里充满着大量的"+Util.stringsToStringList(fluidNames, false)+"，看起来十分明显。"
							+ "[npc.her]上半身附带的充实重量感令[npc.her]更难移动。");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect BREAST_CUM_INFLATION_3 = new AbstractStatusEffect(80,
			"过于肿胀的乳房",
			"inflation_breasts_3",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.NIPPLE).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("液体");
			}
			if(target.isPlayer()) {
				return "你的[pc.breasts]里充满着巨量的"+Util.stringsToStringList(fluidNames, false)+"，看起来十分肿胀。"
						+ "你上半身附带的沉重感令你几乎无法移动。";
			} else {
				return UtilText.parse(target,
							"[npc.namePos]的[npc.breasts]里充满着巨量的"+Util.stringsToStringList(fluidNames, false)+"，看起来无比肿胀。"
									+ "[npc.her]上半身附带的沉重感令[npc.her]几乎无法移动。");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SPINNERET_CUM_INFLATION_1 = new AbstractStatusEffect(80,
			"微隆的丝囊",
			"inflation_spinneret_1",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.SPINNERET).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("液体");
			}
			if(target!=null) {
				if(target.hasTailSpinneret()) {
					return UtilText.parse(target,
							"[npc.namePos]的[npc.tail]里充满着不少的"+Util.stringsToStringList(fluidNames, false)+"，看起来微微隆起。"
							+ "额外的重量感为[npc.herHim]的移动造成了不便。");
				} else {
					return UtilText.parse(target,
							"[npc.namePos]的腹中充满着不少的"+Util.stringsToStringList(fluidNames, false)+"，看起来微微隆起。"
							+ "额外的重量感为[npc.herHim]的移动造成了不便。");
				}
			}
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.SPINNERET);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SPINNERET_CUM_INFLATION_2 = new AbstractStatusEffect(80,
			"肿胀的丝囊",
			"inflation_spinneret_2",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.SPINNERET).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("液体");
			}
			if(target!=null) {
				if(target.hasTailSpinneret()) {
					return UtilText.parse(target,
							"[npc.namePos]的[npc.tail]里充满着大量的"+Util.stringsToStringList(fluidNames, false)+"，看起来十分明显。"
							+ "额外的重量感限制了[npc.her]移动的能力。");
				} else {
					return UtilText.parse(target,
							"[npc.namePos]的腹中充满着大量的"+Util.stringsToStringList(fluidNames, false)+"，看起来十分明显。"
							+ "额外的重量感限制了[npc.her]移动的能力。");
				}
			}
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.SPINNERET);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SPINNERET_CUM_INFLATION_3 = new AbstractStatusEffect(80,
			"过于肿胀的丝囊",
			"inflation_spinneret_3",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.SPINNERET).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("液体");
			}
			if(target!=null) {
				if(target.hasTailSpinneret()) {
					return UtilText.parse(target,
							"[npc.namePos]的[npc.tail]里充满着巨量的"+Util.stringsToStringList(fluidNames, false)+"，看起来无比肿胀。"
							+ "沉重感令[npc.herHim]几乎无法移动。");
				} else {
					return UtilText.parse(target,
							"[npc.namePos]的腹中充满着巨量的"+Util.stringsToStringList(fluidNames, false)+"，看起来无比肿胀。"
							+ "沉重感令[npc.herHim]几乎无法移动。");
				}
			}
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.SPINNERET);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FRUSTRATED_NO_ORGASM = new AbstractStatusEffect(80,
			"沮丧的",
			"frustrated",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name]在某次性行为中未能顺利高潮。"
					+ "[npc.sheIs]最终感到更加的饥渴与沮丧……");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
//			if(target.getLastTimeOrgasmedSeconds()>=Main.game.getSecondsPassed()-(60*60*24)) {
//				target.removeStatusEffect(FRUSTRATED_NO_ORGASM); // Remove this effect if orgasmed within the last day
//			}
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PENT_UP_SLAVE = new AbstractStatusEffect(80,
			"长期积欲",
			"frustrated",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
				return UtilText.parse(target, "[npc.name]由于性瘾成狂，已经超过四小时没有性接触，此刻感到极度欲求不满……");
			}
			return UtilText.parse(target, "[npc.Name]已经超过一天没能得到性满足了，此刻非常的欲求不满……");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if (!ModProps.hasValue(PropertyValue.enablePentUp)) return false;
			int hoursToPentUp = 24;
			if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
				hoursToPentUp = 4;
			}
			
			return !target.isPlayer()
					&& target.isSlave()
					&& !target.isDoll()
					&& ((NPC)target).getLastTimeOrgasmedSeconds()+(60*60*hoursToPentUp)<Main.game.getSecondsPassed();
		}
	};

	/**
	 * This status effect is automatically removed from a character when orgasming in sex.
	 */
	public static AbstractStatusEffect DESPERATELY_HORNY = new AbstractStatusEffect(80,
			"极度饥渴",
			"desperately_horny",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESTING_LUST, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, -15f),
					new Value<>(Attribute.DAMAGE_LUST, 25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull]此刻感觉异常饥渴。"
					+ "[npc.sheIs]努力地控制着[npc.her]的性欲，因此更容易受到魅惑，同时也得以更容易地魅惑他人。");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_1 = new AbstractStatusEffect(80,
			"强制贞操带 (平静)",
			"chastity1",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.colourSex(强度增加……)]")) {
		@Override
		public String getDescription(GameCharacter target) {//TODO if one key-holder, mention them
			return UtilText.parse(target, "[npc.Name]被贞操带锁住，迟早会因为没法正常高潮而感到欲求不满的……");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(target.isWearingChastity()) {
				int denominator = 1;
				if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
					denominator = 2;
				}
					
				if(target.hasStatusEffect(CHASTITY_REMOVED_2)) {
					target.addStatusEffect(CHASTITY_2, (60*60*24*2)/denominator); // 2 instead of 5
					target.removeStatusEffect(CHASTITY_REMOVED_2);
					
				} else if(target.hasStatusEffect(CHASTITY_REMOVED_3)) {
					target.addStatusEffect(CHASTITY_3, (60*60*24*3)/denominator); // 3 instead of 7
					target.removeStatusEffect(CHASTITY_REMOVED_3);
					
				} else if(target.hasStatusEffect(CHASTITY_REMOVED_4)) {
					target.addStatusEffect(CHASTITY_4, -1);
					target.removeStatusEffect(CHASTITY_REMOVED_4);
					
				} else {
					target.addStatusEffect(CHASTITY_2, (60*60*24*5)/denominator); // 5 days
				}
			}
			return "";
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.hasStatusEffect(CHASTITY_REMOVED_2) || target.hasStatusEffect(CHASTITY_REMOVED_3) || target.hasStatusEffect(CHASTITY_REMOVED_4)) {
				target.removeStatusEffect(CHASTITY_1);
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isWearingChastity()
					&& !target.isDoll()
					&& !target.hasStatusEffect(CHASTITY_2)
					&& !target.hasStatusEffect(CHASTITY_3)
					&& !target.hasStatusEffect(CHASTITY_4);
		}
		@Override
		public int getApplicationLength(GameCharacter target) {
			int denominator = 1;
			if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
				denominator = 2;
			}
			return (60*60*24*2)/denominator; // 2 days
		}
		@Override
		public boolean isConstantRefresh() {
			return false;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_2 = new AbstractStatusEffect(80,
			"强制贞操带 (渴望)",
			"chastity2",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -5f)),
			Util.newArrayListOfValues("[style.colourSex(强度增加……)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name]已经被锁贞操带有一段时间，由于无法正常高潮而开始感到欲求不满……");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(target.isWearingChastity()) {
				int denominator = 1;
				if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
					denominator = 2;
				}
				target.addStatusEffect(CHASTITY_3, (60*60*24*7)/denominator); // 7 days
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_3 = new AbstractStatusEffect(80,
			"强制贞操带 (欲求不满)",
			"chastity3",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			Util.newArrayListOfValues("[style.colourSex(强度增加……)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name]已经被锁贞操带很长时间，由于无法正常高潮而感到十分燥热难耐……");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(target.isWearingChastity()) {
				target.addStatusEffect(CHASTITY_4, -1); // This should be enough to prevent the effect from looping back to CHASTITY_1, as once CHASTITY_4 is applied, CHASTITY_1's conditions are now false, and therefore CHASTITY_4's are true
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_4 = new AbstractStatusEffect(80,
			"强制贞操带 (难以自抑)",
			"chastity4",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -50f)),
			Util.newArrayListOfValues("[style.colourSex(最高强度)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name]已经被锁贞操带不知有多久了，由于无法正常高潮而感到异常燥热难耐……");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(!target.isWearingChastity()) {
				target.addStatusEffect(CHASTITY_REMOVED_4, 60*60*24*1); // 1 day (This effect needs this while the others don't due to the use of the isConditionsMet() method)
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isWearingChastity()
					&& !target.isDoll()
					&& !target.hasStatusEffect(CHASTITY_1)
					&& !target.hasStatusEffect(CHASTITY_2)
					&& !target.hasStatusEffect(CHASTITY_3);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean forceLoad() {
			return true;
		}
	};

	public static AbstractStatusEffect CHASTITY_REMOVED_2 = new AbstractStatusEffect(80,
			"贞操带解放 (渴望)",
			"chastityRemoved2",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -5f),
					new Value<>(Attribute.RESTING_LUST, 5f)),
			Util.newArrayListOfValues(
					"[style.colourSex(性高潮)]会移除该效果！")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("[npc.Name]刚从短暂的贞操锁囚期中解脱出来，现在非常的饥渴！");
			sb.append("<br/>");
			sb.append("[style.italicsSex(如果[npc.sheIsFull]被锁回贞操带内，仍会受到此效应的影响，且会保持在“渴望”阶段内！)]");
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(CHASTITY_2);
			return "";
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
//			if(totalSecondsPassed==0) {
//				target.removeStatusEffect(CHASTITY_2);
//			}
			if(target.getLastTimeOrgasmedSeconds()>=Main.game.getSecondsPassed()-(60*60*24)) {
				target.removeStatusEffect(CHASTITY_REMOVED_2); // Remove this effect if orgasmed within the last day (the duration of this effect)
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.isWearingChastity() && target.hasStatusEffect(CHASTITY_2);
		}
		@Override
		public int getApplicationLength(GameCharacter target) {
			return 60*60*24*1; // 1 day
		}
		@Override
		public boolean isConstantRefresh() {
			return false;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_REMOVED_3 = new AbstractStatusEffect(80,
			"贞操带解放 (欲求不满)",
			"chastityRemoved3",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -15f),
					new Value<>(Attribute.RESTING_LUST, 10f)),
			Util.newArrayListOfValues(
					"[style.colourSex(性高潮)]会移除该效果！")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("[npc.Name]刚从长时间的贞操锁囚期中解脱出来，现在极度饥渴！");
			sb.append("<br/>");
			sb.append("[style.italicsSex(如果[npc.sheIsFull]被锁回贞操带内，仍会受到此效应的影响，且会保持在“欲求不满”阶段内！)]");
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(CHASTITY_3);
			return "";
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
//			if(totalSecondsPassed==0) {
//				target.removeStatusEffect(CHASTITY_3);
//			}
			if(target.getLastTimeOrgasmedSeconds()>=Main.game.getSecondsPassed()-(60*60*24)) {
				target.removeStatusEffect(CHASTITY_REMOVED_3); // Remove this effect if orgasmed within the last day (the duration of this effect)
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.isWearingChastity() && target.hasStatusEffect(CHASTITY_3);
		}
		@Override
		public int getApplicationLength(GameCharacter target) {
			return 60*60*24*1; // 1 day
		}
		@Override
		public boolean isConstantRefresh() {
			return false;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_REMOVED_4 = new AbstractStatusEffect(80,
			"贞操带解放 (难以自抑)",
			"chastityRemoved4",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.RESTING_LUST, 15f)),
			Util.newArrayListOfValues(
					"[style.colourSex(性高潮)]会移除该效果！")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("[npc.Name]刚从几乎永无止境的贞操锁囚期中解脱出来，现在是感到疯魔一般的饥渴！");
			sb.append("<br/>");
			sb.append("[style.italicsSex(如果[npc.sheIsFull]被锁回贞操带内，仍会受到此效应的影响，且会保持在“难以自抑”阶段内！)]");
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.getLastTimeOrgasmedSeconds()>=Main.game.getSecondsPassed()-(60*60*24)) {
				target.removeStatusEffect(CHASTITY_REMOVED_4); // Remove this effect if orgasmed within the last day (the duration of this effect)
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
//		@Override
//		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
//			if(totalSecondsPassed==0) {
//				target.removeStatusEffect(CHASTITY_4);
//			}
//			return "";
//		}
//		@Override
//		public boolean isConditionsMet(GameCharacter target) {
//			return !target.isWearingChastity() && target.hasStatusEffect(CHASTITY_4);
//		}
//		@Override
//		public int getApplicationLength(GameCharacter target) {
//			return 60*60*24*1; // 1 day
//		}
//		@Override
//		public boolean isConstantRefresh() {
//			return false;
//		}
	};
	
	public static AbstractStatusEffect RECOVERING_AURA = new AbstractStatusEffect(80,
			"被强化的灵气",
			"recoveringAura",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "由于最近的高潮，你的奥术灵气被暂时强化了。"
						+ "处于这个状态时，即便你高潮，你也不能获得奥术精华！";
			} else {
				return UtilText.parse(target, "由于最近的高潮，[npc.namePos]的奥术灵气被暂时强化了。"
						+ "当[npc.she]处于这个状态时，即便[npc.she]在你面前高潮，你也不能从[npc.she]身上获得奥术精华！");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect EXPOSED = new AbstractStatusEffect(80,
			"裸露",
			"exposed",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.GENERIC_BAD,
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos]的衣服无法盖住[npc.her]的"+getExposedPartsNamesList(target)+"，穿着这么暴露令[npc.she]感到十分尴尬。");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br/>你裸露的身体部位会吸引各种猥亵的目光。");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& target.getLegConfiguration()==LegConfiguration.BIPEDAL
					&& !target.isFeral()
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& isExposedParts(target, false, true);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};

	public static AbstractStatusEffect EXPOSED_ANIMAL = new AbstractStatusEffect(80,
			"暴露 (兽躯)",
			"exposedFeral",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_TAN,
			PresetColour.BASE_TAN,
			false,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			if(target.getLegConfiguration().isGenitalsExposed(target)) {
				return UtilText.parse(target, "[npc.NamePos]的衣服无法盖住[npc.her]的"+getExposedPartsNamesList(target)+"，但由于是兽态的躯体，[npc.she]并不认为那是暴露的。");
			} else {
				return UtilText.parse(target, "[npc.NamePos]的衣服无法盖住[npc.her]的"+getExposedPartsNamesList(target)+"，但[npc.her]兽形身躯的构造使其不会展示出来。");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& (target.getLegConfiguration()!=LegConfiguration.BIPEDAL || target.isFeral())
					&& !((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaVisible(CoverableArea.NIPPLES))
					&& ((target.hasBreastsCrotch() && target.isCoverableAreaVisible(CoverableArea.NIPPLES_CROTCH))
						|| target.isCoverableAreaVisible(CoverableArea.ANUS)
						|| (target.isCoverableAreaVisible(CoverableArea.PENIS) && target.hasPenis())
						|| (target.isCoverableAreaVisible(CoverableArea.VAGINA) && target.hasVagina()));
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};

	public static AbstractStatusEffect EXPOSED_DOLL = new AbstractStatusEffect(80,
			"暴露 (玩偶)",
			"exposedDoll",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_GREY,
			PresetColour.BASE_GREY,
			false,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos]的衣服无法盖住[npc.her]的"+getExposedPartsNamesList(target)+"，但由于[npc.sheIs]是个玩偶，[npc.sheIs]并不具备感觉羞耻或尴尬的能力。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& ((target.hasBreastsCrotch() && target.isCoverableAreaVisible(CoverableArea.NIPPLES_CROTCH))
						|| (target.hasBreasts() && target.isFeminine() && target.isCoverableAreaVisible(CoverableArea.NIPPLES))
						|| target.isCoverableAreaVisible(CoverableArea.ANUS)
						|| (target.isCoverableAreaVisible(CoverableArea.PENIS) && target.hasPenis())
						|| (target.isCoverableAreaVisible(CoverableArea.VAGINA) && target.hasVagina()));
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect EXPOSED_BREASTS = new AbstractStatusEffect(80,
			"暴露的胸部",
			"exposed",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.GENERIC_BAD,
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -2f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			if (! target.hasBreasts()) {
				return "暴露的乳头";
			} else {
				return super.getName(target);
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos]的衣服无法盖住[npc.her]的"+getExposedPartsNamesList(target)+"，穿着这么暴露令[npc.she]感到十分尴尬。");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br/>你裸露的乳房吸引了各种猥亵的目光。");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& (target.getLegConfiguration()==LegConfiguration.BIPEDAL || ((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaVisible(CoverableArea.NIPPLES)))
					&& !target.isFeral()
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& isExposedParts(target, true, false);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect EXPOSED_PLUS_BREASTS = new AbstractStatusEffect(80,
			"裸露",
			"exposed",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.GENERIC_BAD,
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos]的衣服无法盖住[npc.her]的"+getExposedPartsNamesList(target)+"，穿着这么暴露令[npc.she]感到十分尴尬。");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br/>你裸露的身体部位会吸引各种猥亵的目光。");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& target.getLegConfiguration()==LegConfiguration.BIPEDAL
					&& !target.isFeral()
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& isExposedParts(target, true, true);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect FETISH_EXHIBITIONIST = new AbstractStatusEffect(80,
			"暴露狂",
			"exposedExhibitionist",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_PINK_DEEP,
			PresetColour.BASE_PINK_DEEP,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos]的衣服无法盖住[npc.her]的"+getExposedPartsNamesList(target)+"，穿着这么暴露令[npc.she]感到十分的性感与自信。");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br/>你裸露的身体部位会吸引各种猥亵的目光。");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& isExposedParts(target, false, true);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect FETISH_EXHIBITIONIST_BREASTS = new AbstractStatusEffect(80,
			"暴露狂",
			"exposedExhibitionist",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_PINK_DEEP,
			PresetColour.BASE_PINK_DEEP,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos]的衣服无法盖住[npc.her]的"+getExposedPartsNamesList(target)+"，穿着这么暴露令[npc.she]感到十分的性感与自信。");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br/>你裸露的身体部位会吸引各种猥亵的目光。");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& isExposedParts(target, true, false);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect FETISH_EXHIBITIONIST_PLUS_BREASTS = new AbstractStatusEffect(80,
			"暴露狂",
			"exposedExhibitionist",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_PINK_DEEP,
			PresetColour.BASE_PINK_DEEP,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 30f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos]的衣服无法盖住[npc.her]的"+getExposedPartsNamesList(target)+"，穿着这么暴露令[npc.she]感到十分的性感与自信。");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br/>你裸露的身体部位会吸引各种猥亵的目光。");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& isExposedParts(target, true, true);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};

	public static AbstractStatusEffect FETISH_PURE_VIRGIN = new AbstractStatusEffect(80,
			"贞洁处女",
			"virginPure",
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name]是纯洁与正义的完美代表。[npc.her]展现出的高贵气质与善良个性，正标志着[npc.herHim]是世间真善美的典范。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN)
					&& !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& target.isVaginaVirgin()
					&& target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_PURE_VIRGIN_NO_HYMEN = new AbstractStatusEffect(80,
			"“贞洁”处女",
			"virginPureNoHymen",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 10f),
					new Value<>(Attribute.MAJOR_CORRUPTION, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name]的举止可称为是高尚贤淑的纯洁典范，却又奇特地热衷于争辩一件事——就算处女膜破了，也不代表该被排除在贞洁处女的范围外……");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN)
					&& !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& target.isVaginaVirgin()
					&& !target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_PURE_VIRGIN_ONLY_HYMEN = new AbstractStatusEffect(80,
			"贞洁“处女”",
			"virginPureRepaired",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name]的举止可称为是高尚贤淑的纯洁典范，却又奇特地热衷于争辩一件事——只要处女膜没破，就可算作是处女……");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN)
					&& !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& !target.isVaginaVirgin()
					&& target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_BROKEN_VIRGIN = new AbstractStatusEffect(80,
			"失格处女",
			"virginBroken",
			PresetColour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"失去宝贵的贞操对[npc.Name]是一次巨大的打击，令[npc.she]自认为已经脏了。"
						+ "[npc.She]开始常常幻想着大屌操着自己下流的小穴，被肮脏的精液玷污子宫……");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN)
					&& !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& !target.isVaginaVirgin()
					&& !target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_LUSTY_MAIDEN = new AbstractStatusEffect(80,
			"色欲少女",
			"virginLustyMaidenPure",
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.RESISTANCE_LUST, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIs]喜欢用[npc.her]的屁股，嘴巴，胸部——甚至愿意承诺用[npc.her]的小穴来取悦[npc.her]的对象，"
							+ "但这承诺永远不会兑现，谁也无法进入[npc.she]的女性身体，夺走[npc.her]宝贵的贞操！");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& target.isVaginaVirgin()
					&& target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_LUSTY_MAIDEN_NO_HYMEN = new AbstractStatusEffect(80,
			"色欲“少女”",
			"virginLustyMaidenNoHymen",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name]表现得像是[npc.her]的小穴从未遭人玷污或进入过一样，"
							+ "但[npc.sheIs]又奇特地热衷于争辩一件事——就算处女膜破了，也不代表该被排除在贞洁处女的范围外……");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& target.isVaginaVirgin()
					&& !target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_LUSTY_MAIDEN_ONLY_HYMEN = new AbstractStatusEffect(80,
			"色欲“少女”",
			"virginLustyMaidenRepaired",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name]表现得像是[npc.her]的小穴从未遭人玷污或进入过一样，"
							+ "但[npc.sheIs]却又奇特地热衷于争辩一件事——只要处女膜没破，就可算作是处女……");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& !target.isVaginaVirgin()
					&& target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_LUSTY_MAIDEN_BROKEN = new AbstractStatusEffect(80,
			"破处少女",
			"virginLustyMaidenBroken",
			PresetColour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, -25f),
					new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"失去宝贵的贞操对[npc.Name]是一次巨大的打击，令[npc.she]自认为已经脏了。"
						+ "[npc.her]为保住小穴贞洁所付出的一切努力，譬如让人用别的地方，现在都已付诸东流。这令[npc.she]只能幻想着用下贱的小穴给人作肉便器……");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& !target.isVaginaVirgin()
					&& !target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	
	// JOB/OCCUPATION EFFECTS:
	
	public static AbstractStatusEffect COMBAT_JOB_SOLDIER = new AbstractStatusEffect(10,
			"受控侵略",
			"res/perks/jobs/soldier",
			PresetColour.BASE_GREEN,
			true,
			null,
			Util.newArrayListOfValues("你的伤害[style.boldExcellent(翻倍)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "得益于军事训练，[npc.Name]能在战斗开始时以极强的攻击性打击敌人。");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	
	// CLOTHING SETS:
	
	public static AbstractStatusEffect SET_MAID = new AbstractStatusEffect(70,
			"辛勤女仆",
			"clothingSets/maid",
			PresetColour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "穿上整套女仆装，[npc.nameIsFull]感到自己就该是个辛勤的性感女仆！");
					
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_maid").isCharacterWearingCompleteSet(target) && !target.hasTrait(Perk.JOB_MAID, true);
		}
	};
	
	public static AbstractStatusEffect SET_MAID_BOOSTED = new AbstractStatusEffect(70,
			"职业女仆",
			"clothingSets/maid_boosted",
			PresetColour.CLOTHING_BLACK,
			PresetColour.BASE_GOLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "穿上整套女仆装，[npc.nameIsFull]才会想起，[npc.her]生来便是天赋异禀的性感女仆！");
				
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_maid").isCharacterWearingCompleteSet(target) && target.hasTrait(Perk.JOB_MAID, true);
		}
	};
	
	public static AbstractStatusEffect SET_MILK_MAID = new AbstractStatusEffect(70,
			"泌乳女仆",
			"clothingSets/milk_maid",
			PresetColour.BASE_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "穿上整套泌乳女仆装，[npc.nameIsFull]感到自己就该全心全意地投入进泌乳女仆的职责中。");
				
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_milk_maid").isCharacterWearingCompleteSet(target) && !target.hasTrait(Perk.JOB_MAID, true);
		}
	};
	
	public static AbstractStatusEffect SET_MILK_MAID_BOOSTED = new AbstractStatusEffect(70,
			"职业泌乳女仆",
			"clothingSets/milk_maid_boosted",
			PresetColour.BASE_WHITE,
			PresetColour.BASE_GOLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "穿上整套泌乳女仆装，[npc.nameIsFull]才想起，[npc.her]生来便该是天赋异禀的性感泌乳女仆！");
				
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_milk_maid").isCharacterWearingCompleteSet(target) && target.hasTrait(Perk.JOB_MAID, true);
		}
	};
	
	public static AbstractStatusEffect SET_BUTLER = new AbstractStatusEffect(70,
			"管家",
			"clothingSets/butler",
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "穿上整套管家服，[npc.nameIsFull]感到自己就该全心全意地投入进管家的职责中。");
					
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_butler").isCharacterWearingCompleteSet(target) && !target.hasTrait(Perk.JOB_BUTLER, true);
		}
	};
	
	public static AbstractStatusEffect SET_BUTLER_BOOSTED = new AbstractStatusEffect(70,
			"职业管家",
			"clothingSets/butler_boosted",
			PresetColour.CLOTHING_WHITE,
			PresetColour.BASE_GOLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "穿上整套管家服，[npc.nameIsFull]这才想起，[npc.her]生来便该是天赋异禀的管家！");
					
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_butler").isCharacterWearingCompleteSet(target) && target.hasTrait(Perk.JOB_BUTLER, true);
		}
	};
	
	public static AbstractStatusEffect SET_WITCH = new AbstractStatusEffect(70,
			"奥术魔女",
			"clothingSets/witch",
			PresetColour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "穿齐魔女套装，你的奥术能量愈发强大。";
					
				} else {
					return UtilText.parse(target, "穿上整套魔女服，[npc.namePos]的力量愈发强大。");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_witch").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_SCIENTIST = new AbstractStatusEffect(70,
			"科学家",
			"clothingSets/scientist",
			PresetColour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.RESISTANCE_FIRE, 2f),
					new Value<>(Attribute.RESISTANCE_POISON, 2f),
					new Value<>(Attribute.RESISTANCE_ICE, 2f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "你相信，只要穿好防护性能最强的白大褂，戴上护目镜，就没有什么化学品能伤害你。";
					
				} else {
					return UtilText.parse(target, "穿上实验室外套，戴上安全护目镜，就能很好地防止化学品泄漏。");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_scientist").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_SLUTTY_ENFORCER = new AbstractStatusEffect(70,
			"风骚执法者",
			"clothingSets/slutty_enforcer",
			PresetColour.BASE_PINK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIsFull]穿着放荡奇装异服版的执法者制服，真叫人把持不住。");
					
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_slutty_enforcer").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_RAINBOW = new AbstractStatusEffect(70,
			"双倍彩虹",
			"clothingSets/rainbow",
			PresetColour.CLOTHING_RED,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return "双倍彩虹……到底什么意思啊？！";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_rainbow").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_DARK_SIREN = new AbstractStatusEffect(70,
			"暗夜塞壬",
			"clothingSets/dark_siren",
			PresetColour.CLOTHING_PURPLE_DARK,
			PresetColour.CLOTHING_BLACK_STEEL,
			PresetColour.CLOTHING_RED_DARK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return "<i>无光与混乱，打破永恒虚无的界限吧！无形显现的神意啊，释放出我那封存着的力量吧！</i>";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_dark_siren").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_LYSSIETH_GUARD = new AbstractStatusEffect(70,
			"莉西丝的护卫",
			"clothingSets/lyssieth_guard",
			PresetColour.CLOTHING_OLIVE,
			PresetColour.CLOTHING_BROWN_DARK,
			PresetColour.CLOTHING_OLIVE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {//British Auxiliary Territorial Service
			return UtilText.parse(target, "穿上莉西丝的护卫制服，[npc.Name]似乎更容易保持冷静了。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_lyssieth_guard").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_BDSM = new AbstractStatusEffect(70,
			"受缚婊子",
			"clothingSets/bdsm",
			PresetColour.CLOTHING_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -15f)),
			null) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(target!=null && target.hasFetish(Fetish.FETISH_BONDAGE_VICTIM)) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.HEALTH_MAXIMUM, 10f),
						new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f),
						new Value<>(Attribute.DAMAGE_LUST, 10f));
			}
			return super.getAttributeModifiers(target);
		}
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.hasFetish(Fetish.FETISH_BONDAGE_VICTIM)) {
					return UtilText.parse(target, "[npc.Name]被束缚装置捆绑着，对难以移动的现状感到无比热爱。");
				} else {
					return UtilText.parse(target, "[npc.Name]被束缚装置捆绑着，难以移动。");
				}
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_bdsm").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_CATTLE = new AbstractStatusEffect(70,
			"牛",
			"clothingSets/cattle",
			PresetColour.BASE_TAN,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "你穿着在牛身上常见的配饰。";
					
				} else {
					return UtilText.parse(target, "[npc.NameIsFull]穿着在牛身上常见的配饰。");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_cattle").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_SNOWFLAKE = new AbstractStatusEffect(70,
			"冰川",
			"clothingSets/snowflake",
			PresetColour.BASE_BLUE_LIGHT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.DAMAGE_ICE, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "佩戴上全套特别附魔的雪花首饰后，[npc.namePos]的奥术力量与寒冷攻击都将变得更加强大！");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_snowflake").isCharacterWearingCompleteSet(target);
		}
	};
		
	public static AbstractStatusEffect SET_SUN = new AbstractStatusEffect(70,
			"辉光",
			"clothingSets/sun",
			PresetColour.CLOTHING_COPPER,
			PresetColour.CLOTHING_YELLOW,
			PresetColour.CLOTHING_ORANGE,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.DAMAGE_FIRE, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "在戴上像太阳一样闪耀的珠宝后，[npc.nameIsFull]信心满满，准备好[npc.her]的法术与火焰将敌人烧成灰烬！");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_sun").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_GEISHA = new AbstractStatusEffect(70,
			"艺伎",
			"clothingSets/geisha",
			PresetColour.BASE_ROSE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIs]穿着一套妖狐的礼服，这身衣服与日本传统服装非常相似。");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_geisha").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_RONIN = new AbstractStatusEffect(70,
			"浪人",
			"clothingSets/ronin",
			PresetColour.BASE_ROSE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "<i>你明白在自身之外有什么必须被服务。当欲望满足后，信仰消亡后，你又会变成什么样子呢？一个没有主人的[pc.man]。</i>";
					
				} else {
					return UtilText.parse(target,
							"<i>[npc.Name]明白在[npc.herself]之外有什么必须被服务。当欲望满足后，信仰消亡后，[npc.she]又会变成什么样子呢？一个没有主人的[npc.man]。</i>");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_ronin").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_DAISHO = new AbstractStatusEffect(70,
			"成对武士刀",
			"clothingSets/daisho",
			PresetColour.BASE_ROSE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"日语中的“大小”意为“成对武士刀”，用来描述同时佩戴太刀和胁差的情况。"
						+ "同时佩戴这两把剑，标志着持剑者是武士阶层的一员。");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_daisho").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_JOLNIR = new AbstractStatusEffect(70,
			"圣诞",
			"clothingSets/jolnir",
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.DAMAGE_ICE, 15f),
					new Value<>(Attribute.RESISTANCE_ICE, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "你穿上了“圣诞套装”，大幅增强了自身的智力与作战能力！";
					
				} else {
					return UtilText.parse(target, "[npc.NameIsFull]穿上了“圣诞套装”，大幅增加了[npc.her]的智力与作战能力。");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_jolnir").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_KITTY = new AbstractStatusEffect(70,
			"爱玩的猫咪",
			"clothingSets/kitty",
			PresetColour.CLOTHING_PINK_LIGHT,
			PresetColour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.name]穿上了整套猫咪内衣，想要好好调戏下见到的每一个人！");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_kitty").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_STURDY_STEED = new AbstractStatusEffect(70,
			"统统套齐",
			"clothingSets/sturdy_steed",
			PresetColour.CLOTHING_STEEL,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 25f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "穿上了整套马具，[npc.name]感觉自己比往常更为强壮，精力满满！");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_sturdy_steed").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect CLOTHING_EFFECT = new AbstractStatusEffect(70,
			"衣物效果",
			"combatHidden",
			PresetColour.TRANSFORMATION_GENERIC,
			false,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "-";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return "-";
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect POTION_EFFECTS = new AbstractStatusEffect(80,
			"药剂效果",
			"potionEffects",
			PresetColour.GENERIC_ARCANE,
			PresetColour.BASE_PINK_LIGHT,
			null,
			false,
			null,
			Util.newArrayListOfValues("[style.italicsMinorGood(最大值为25)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.hasTrait(Perk.JOB_CHEF, true)) {
				return UtilText.parse(target, "借助[npc.namePos]厨师的背景，对于这个世界中被注入消耗品的，[npc.sheIs]都能够完全发挥其效果……");
			}
			return UtilText.parse(target, "注入奥术的消耗品在这个世界非常常见，[npc.nameHasFull]会发现，他们拥有各式各样相当奇妙的作用……");
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			return target.getPotionAttributes();
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modifiersList = new ArrayList<>();

			modifiersList.addAll(getExtraEffects(target));
			
			if (getAttributeModifiers(target) != null) {
				for (Entry<AbstractAttribute, Float> e : getAttributeModifiers(target).entrySet()) {
					modifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b>" + "<b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
				}
			}
					
			return modifiersList;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(target.hasTrait(Perk.JOB_CHEF, true)) {
				return Util.newArrayListOfValues("[style.italicsGood(最大值为50)]");
			}
			return super.getExtraEffects(target);
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.clearPotionAttributes();
			return "";
		}
	};
	
	public static AbstractStatusEffect HAPPINESS = new AbstractStatusEffect(70,
			"心情愉悦",
			"happinessFox",
			PresetColour.CLOTHING_SILVER,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.MANA_MAXIMUM, 5f)),
			Util.newArrayListOfValues("[style.italicsGood(开心！)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return "这个银白色皮毛的狐狸“<i>欢欣</i>”跟着你到处走。"
					+ "每当你感到疲劳或失落，这个可爱的小动物都会在你的[pc.legs]边蹭来蹭去，坐下来求你挠耳朵，能立刻让你开心起来。";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isPlayer() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.foundHappiness);
		}
	};
	

	// COMBAT EFFECTS:

	public static AbstractStatusEffect SPECIAL_SILENCE_TRANCE = new AbstractStatusEffect(70,
			"默之迷幻",
			"glowingEyes",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, 1f),
					new Value<>(Attribute.MANA_MAXIMUM, 100f),
					new Value<>(Attribute.DAMAGE_SPELLS, 50f),
					new Value<>(Attribute.ENERGY_SHIELDING, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return "目睹挚友“影”被击败后，默召唤出风元素，并陷入强烈的迷幻状态，战斗能力大幅提高！";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInCombat()
					&& target.equals(Main.game.getNpc(Silence.class))
					&& !Main.combat.getEnemies(Main.game.getPlayer()).contains(Main.game.getNpc(Shadow.class));
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect SPECIAL_SHADOW_BESERK = new AbstractStatusEffect(70,
			"影之狂怒",
			"glowingEyes",
			PresetColour.BASE_CRIMSON,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, 3f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 150f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 50f),
					new Value<>(Attribute.ENERGY_SHIELDING, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return "目睹挚友“默”被击败后，影陷入了野蛮狂怒，战斗能力大幅提高！";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInCombat()
					&& target.equals(Main.game.getNpc(Shadow.class))
					&& Main.combat.getEnemies(Main.game.getPlayer()).size()==1;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect SPECIAL_AMERICAN_FREEDOM = new AbstractStatusEffect(70,
			"被自由蒙蔽双眼",
			"american_freedom",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIsFull]与命运抗争！");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isStarted()
					&& Main.game.getPlayer().getOccupation()==Occupation.TOURIST
					&& !target.isPlayer()
					&& Main.game.isInCombat()
					&& Main.combat.getEnemies(Main.game.getPlayer()).contains(target);
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect COMBAT_HIDDEN = new AbstractStatusEffect(70,
			"隐藏",
			"combatHidden",
			PresetColour.GENERIC_BAD,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return "你不知道对手有什么天赋、效果、法术或特殊攻击。需要"+Perk.OBSERVANT.getName(target)+"天赋才能揭示这些信息。";
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect DESPERATE_FOR_SEX = new AbstractStatusEffect(70,
			"渴求性爱",
			"desperateForSex",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.ATTRIBUTE_MANA,
			PresetColour.ATTRIBUTE_LUST,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourTerrible(所受伤害无视所有护盾)]",
					"即将受到的[style.colourLust(性欲伤害)]将转化为：",
					"[style.colourHealth(2*"+Attribute.HEALTH_MAXIMUM.getName()+"伤害)]和[style.colourMana(1*"+Attribute.MANA_MAXIMUM.getName()+"伤害)])")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "你求性若渴，但借助奥术灵气的力量，你能够暂时避免直接放弃抵抗！";
			} else {
				return UtilText.parse(target,
						"[npc.NameIsFull]求性若渴，但借助奥术灵气的力量，[npc.sheIs]能够暂时避免直接放弃抵抗！");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getLust()>=100 && !target.isVulnerableToLustLoss();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	// From spells or combat moves (still in combat):
	
	public static AbstractStatusEffect ARCANE_WEAKNESS = new AbstractStatusEffect(10,
			"奥术弱点",
			"negativeCombatEffect",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -2f),
					new Value<>(Attribute.RESISTANCE_LUST, -2f),
					new Value<>(Attribute.RESISTANCE_FIRE, -2f),
					new Value<>(Attribute.RESISTANCE_ICE, -2f),
					new Value<>(Attribute.RESISTANCE_POISON, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NamePos]头昏脑花，只能奋力站直……");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect DAZED = new AbstractStatusEffect(10,
			"眩晕",
			"dazed",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "[npc.NamePos]头晕脑花，只能奋力站直。[npc.SheIs]感觉无论是命中对手还是躲避攻击都变得极其困难。");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CRIPPLE = new AbstractStatusEffect(10,
			"致残",
			"negativeCombatEffect",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameHasFull]被暂时致残了，造成的伤害不足平常。");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect VULNERABLE = new AbstractStatusEffect(10,
			"脆弱",
			"negativeCombatEffect",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull]感觉自己格外脆弱，防护能力不比平常。");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISONED = new AbstractStatusEffect(10,
			"中毒",
			"combat_poisoned",
			PresetColour.ATTRIBUTE_HEALTH,
			false,
			null,
			Util.newArrayListOfValues("每回合造成<b>15</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 15);

			return UtilText.parse(target, "[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"！")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.namePos]体内的毒素正在生效，[npc.sheIs]的体力渐渐流失！");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISONED_LUST = new AbstractStatusEffect(10,
			"欲毒",
			"combat_poisoned",
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues(
					"每回合造成<b>5</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"</b>",
					"<b>10</b>"+Attribute.DAMAGE_LUST.getColouredName("b")+"每回合</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 5);
			Value<String, Integer> lustDamageValue = DamageType.LUST.damageTarget(null, target, 10);

			return UtilText.parse(target, "[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"！")+damageValue.getKey()
					 + UtilText.parse(target, "<br/>[npc.Name]额外受到了<b>" + lustDamageValue.getValue() + "</b>点"+Attribute.DAMAGE_LUST.getColouredName("b")+"！")+lustDamageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.namePos]体内的欲毒正在生效，[npc.sheIs]不仅渐渐失去了体力，还不受控制地性奋起来！");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect INKY_ATTACK = new AbstractStatusEffect(10,
			"墨墨的最爱",
			"inky_summon",
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, -1f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues(
					"每回合造成<b>15</b>点"+Attribute.DAMAGE_LUST.getColouredName("b")+"</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> lustDamageValue = DamageType.LUST.damageTarget(null, target, 15);

			return UtilText.parse(target, "<br/>[npc.Name]受到了<b>" + lustDamageValue.getValue() + "</b>点"+Attribute.DAMAGE_LUST.getColouredName("b")+"！")+lustDamageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "墨墨正在用触手爱抚着[npc.namePos]的身体，享受着在其上的每一秒！");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect WITCH_SEAL = new AbstractStatusEffect(10,
			"魔女的封锁",
			"combat_witch_seal",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -3f)),
			Util.newArrayListOfValues("[style.colourTerrible(无法逃跑！)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "一道强而有力的奥术封锁将[npc.name]牢牢定在原地，使[npc.herHim]无法做出任何动作！");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT,
					ItemTag.PREVENTS_COMBAT_ESCAPE);
		}
	};
	
	public static AbstractStatusEffect WITCH_CHARM = new AbstractStatusEffect(10,
			"魔女的魅惑",
			"combat_witch_charm",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if (target.isPlayer()) {
				return "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>魔女的魅惑</b>让你极具吸引力，无人能挡！";
				
			} else {
				return UtilText.parse(target,
						"<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>魔女的魅惑</b>让[npc.name]极具吸引力，无人能挡！");
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				if(target.isFeminine()) {
					return "一种会魅惑任何一个看向你的人的奥术附魔，对方的眼中你将变成无出其右的美女。";
				} else {
					return "一种会魅惑任何一个看向你的人的奥术附魔，对方的眼中你将变成无出其右的俊男。";
				}
				
			} else {
				if(target.isFeminine()) {
					return UtilText.parse(target, "一种会魅惑任何一个看向[npc.Name]的人的奥术附魔，对方的眼中[npc.her]将变成无出其右的美女。");
				} else {
					return UtilText.parse(target, "一种会魅惑任何一个看向[npc.Name]的人的奥术附魔，对方的眼中[npc.her]将变成无出其右的俊男。");
				}
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect ROPE_BOUND_SEX = new AbstractStatusEffect(10,
			"绳缚",
			"immobilised_rope",
			PresetColour.GENERIC_BAD,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(无法移动！)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "结实的绳子缠绕住[npc.namePos]的身体，让[npc.herHim]只得待在原地，无法做出任何动作！");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.ROPE);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect CHAINS_BOUND_SEX = new AbstractStatusEffect(10,
			"链缚",
			"immobilised_chains",
			PresetColour.GENERIC_BAD,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(无法移动！)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "结实的锁链缠住了[npc.namePos]的身体，让[npc.herHim]只得待在原地，无法做出任何动作！");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.CHAINS);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect STOCKS_BOUND_SEX = new AbstractStatusEffect(10,
			"锁在颈手枷",
			"immobilised_stocks",
			PresetColour.CLOTHING_DESATURATED_BROWN,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(无法移动！)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name]被锁在颈手枷中，无法做出任何行动！");
		}
		@Override
		public String applyAdditionEffect(GameCharacter target) {
//			if(Main.game.isInSex()) {
//				Main.sex.addCharacterImmobilised(ImmobilisationType.STOCKS, Main.sex.getDominantParticipants(false).keySet().iterator().next(), target);
//			}
			return "";
		}
		@Override
		protected String extraRemovalEffects(GameCharacter target){
			if(Main.game.isInSex()) {
				Main.sex.removeCharacterImmobilised(target, ImmobilisationType.STOCKS);
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.STOCKS)
					&& Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.LOCKED_IN_STOCKS);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};
	
	public static AbstractStatusEffect WEBBED_1 = new AbstractStatusEffect(10,
			"网缚",
			"restrain_webbed_1",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "粘性的厚实蛛网缠在[npc.namePos]的身体上，让[npc.her]的行动受到了一些阻碍！"
						+ "<br/>[style.italicsMinorBad(如果[npc.she]再次被网住，该效果会更加严重！)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect WEBBED_2 = new AbstractStatusEffect(10,
			"厚重网缚",
			"restrain_webbed_2",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -50f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "大量粘性的厚实蛛网缠在[npc.namePos]的身体上，让[npc.her]的行动受到了严重阻碍！"
						+ "<br/>[style.italicsMinorBad(如果[npc.she]再次被网住，该效果会变得极其严重！)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT);
		}
	};

	public static AbstractStatusEffect WEBBED_3 = new AbstractStatusEffect(10,
			"茧缚",
			"restrain_webbed_3",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -75f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f),
					new Value<>(Attribute.ACTION_POINTS, -2f)),
			Util.newArrayListOfValues("[style.colourTerrible(无法逃跑！)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "大量粘性的厚实蛛网缠在[npc.namePos]的身体上，让[npc.her]的[npc.arms]和[npc.legs]都无法移动了！"
						+ "<br/>[style.italicsBad(如果[npc.she]再次被网住，[npc.she]便会直接被击败！)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT,
					ItemTag.PREVENTS_COMBAT_ESCAPE);
		}
	};

	public static AbstractStatusEffect WEBBED_SEX = new AbstractStatusEffect(10,
			"茧缚",
			"immobilised_cocoon",
			PresetColour.GENERIC_BAD,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(无法移动！)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "大量粘性的厚实蛛网缠在[npc.namePos]的身体上，让[npc.her]无法移动了！");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.COCOON);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect WITCH_SEAL_SEX = new AbstractStatusEffect(10,
			"魔女的封锁",
			"immobilised_seal",
			PresetColour.GENERIC_ARCANE,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(无法移动！)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "一道强而有力的奥术封锁将[npc.name]牢牢定在原地，使[npc.herHim]无法做出任何动作！");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.WITCH_SEAL);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect TENTACLE_RESTRAIN_1 = new AbstractStatusEffect(10,
			"被触手抓取",
			"restrain_tentacles_1",
			PresetColour.GENERIC_BAD,
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "几根粗壮的触手抓住了[npc.namePos]的身体，让[npc.her]的行动受到了一些阻碍！"
						+ "<br/>[style.italicsMinorBad(如果[npc.she]再次被触手限制，该效果会更加严重！)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect TENTACLE_RESTRAIN_2 = new AbstractStatusEffect(10,
			"被触手缠绕",
			"restrain_tentacles_2",
			PresetColour.GENERIC_BAD,
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -50f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "有力的触手紧紧缠绕住[npc.namePos]的[npc.arms]和[npc.legs]，让[npc.her]的行动受到了严重阻碍！"
						+ "<br/>[style.italicsMinorBad(如果[npc.she]再次被触手限制，该效果会变得极其严重！)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT);
		}
	};

	public static AbstractStatusEffect TENTACLE_RESTRAIN_3 = new AbstractStatusEffect(10,
			"被触手约束",
			"restrain_tentacles_3",
			PresetColour.GENERIC_BAD,
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -75f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f),
					new Value<>(Attribute.ACTION_POINTS, -2f)),
			Util.newArrayListOfValues("[style.colourTerrible(无法逃跑！)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "有力的触手如钳口一般紧紧束缚[npc.name]，让[npc.herHim]完全无法移动[npc.arms]和[npc.legs]了！"
						+ "<br/>[style.italicsBad(如果[npc.she]再次被触手限制，[npc.she]便会直接被击败！)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT,
					ItemTag.PREVENTS_COMBAT_ESCAPE);
		}
	};

	public static AbstractStatusEffect TENTACLE_RESTRICTION_SEX = new AbstractStatusEffect(10,
			"被触手束缚",
			"immobilised_tentacles",
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(无法移动！)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			GameCharacter applier = Main.sex.getImmobilisationTypes(target).get(ImmobilisationType.TENTACLE_RESTRICTION);
			if(applier==null) {
				return "";
			}
			return UtilText.parse(target, applier, "[npc2.NameIsFull]用四根[npc2.tentacles]钳制住了[npc.name]，让[npc.herHim]一动都不能动了！");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.TENTACLE_RESTRICTION);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect TAIL_RESTRAIN_1 = new AbstractStatusEffect(10,
			"被尾巴抓住",
			"restrain_tail_1",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_GREEN_DARK,
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "一条粗壮的蛇尾环住了[npc.namePos]的身体，让[npc.her]的行动受到了一些阻碍！"
						+ "<br/>[style.italicsMinorBad(如果[npc.she]再被锢住，该效果会更加严重！)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect TAIL_RESTRAIN_2 = new AbstractStatusEffect(10,
			"被尾巴紧抱",
			"restrain_tail_2",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_GREEN_DARK,
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -50f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "一条粗壮的蛇尾紧紧缠绕住[npc.namePos]的[npc.arms]和[npc.legs]，让[npc.her]的行动受到了严重阻碍！"
						+ "<br/>[style.italicsBad(如果[npc.she]再被锢住，该效果会变得极其严重！)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT);
		}
	};

	public static AbstractStatusEffect TAIL_RESTRAIN_3 = new AbstractStatusEffect(10,
			"被尾巴禁锢",
			"restrain_tail_3",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_GREEN_DARK,
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -75f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f),
					new Value<>(Attribute.ACTION_POINTS, -2f)),
			Util.newArrayListOfValues("[style.colourTerrible(无法逃跑！)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "一条粗壮的蛇尾像钳子一样紧紧夹住了[npc.namePos]，使[npc.herHim]几乎无法移动[npc.arms]和[npc.legs]！"
						+ "<br/>[style.italicsBad(如果[npc.she]再被锢住，[npc.she]便会直接被击败！)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT,
					ItemTag.PREVENTS_COMBAT_ESCAPE);
		}
	};
	
	public static AbstractStatusEffect TAIL_CONSTRICTION_SEX = new AbstractStatusEffect(10,
			"约束",
			"immobilised_tail",
			PresetColour.BASE_GREEN_DARK,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(无法移动！)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			GameCharacter applier = Main.sex.getImmobilisationTypes(target).get(ImmobilisationType.TAIL_CONSTRICTION);
			if(applier==null) {
				return "";
			}
			return UtilText.parse(target, applier, "[npc2.NameIsFull]用[npc2.her]长而强壮的尾巴禁锢住了[npc.name]，让[npc.herHim]一动都不能动了！");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.TAIL_CONSTRICTION);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};
	
	public static AbstractStatusEffect COMMAND_IMMOBILE_SEX = new AbstractStatusEffect(10,
			"无法移动",
			"immobilised_command",
			PresetColour.BASE_WHITE,
			PresetColour.BASE_RED,
			PresetColour.BASE_WHITE,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(无法移动！)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			GameCharacter applier = Main.sex.getImmobilisationTypes(target).get(ImmobilisationType.COMMAND);
			if(applier==null) {
				return "";
			}
			return UtilText.parse(target, applier, "[npc2.NameHasFull]命令[npc.name]完全保持静止，而[npc.sheIsFull]确实一动不动了！");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.COMMAND);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SLEEP_SEX = new AbstractStatusEffect(10,
			"熟睡",
			"immobilised_sleep",
			PresetColour.SLEEP_HEAVY,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourTerrible(无法移动！)]",
					"如果性爱状态不再处于温柔，或是口腔被插入，就会[style.colourBad(醒来)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull]正处于深度睡眠中，只要性爱状态仍处于温柔，那么[npc.she]就不会醒来！");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.SLEEP);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};
	
	public static AbstractStatusEffect BANEFUL_FISSURE = new AbstractStatusEffect(10,
			"裂隙毒烟",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			null,
			Util.newArrayListOfValues("每回合造成<b>25</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name]受到了<b>" + damageValue.getValue()+ "</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"！")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"裂隙不断喷出毒气，导致[npc.herHim]每次吸气时都会咳嗽与呛咳。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.DARK_SIREN_SIRENS_CALL.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect FIRE_MANA_BURN = new AbstractStatusEffect(10,
			"点燃灵气",
			"melee_fire",
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			null,
			Util.newArrayListOfValues("[style.boldFire(火焰法术)]可在灵气耗尽时，以"+Attribute.HEALTH_MAXIMUM.getName()+"消耗释放",
					"[style.boldGood(无法将"+Attribute.HEALTH_MAXIMUM.getName()+"降为1以下)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"由于[npc.her]与烈火学派的亲和力，[npc.nameIsFull]能通过牺牲部分"+Attribute.HEALTH_MAXIMUM.getName()+"，在灵气耗尽时继续释放火系法术。");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSpellSchoolSpecialAbilityUnlocked(SpellSchool.FIRE);
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect LINGERING_FLAMES = new AbstractStatusEffect(10,
			"不灭烈焰",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			null,
			Util.newArrayListOfValues("每回合<b>5</b>点[style.boldFire(火焰伤害)]</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.FIRE.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name]受到了<b>" + damageValue.getValue() + "</b>[style.boldFire(火焰伤害)]！")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "无形的奥术火焰舔舐着你的双脚，虽然它们不会给你带来真正的痛苦，但你还是会因不适而跳来跳去。";
			} else {
				return UtilText.parse(target,
						"无形的奥术火焰舔舐着[npc.namePos]的双脚，虽然它们不会给[npc.herHim]带来真正的痛苦，但[npc.she]还是会因不适而跳来跳去。");
			}
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.FIREBALL_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FLASH = new AbstractStatusEffect(10,
			"致盲",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"刺眼的闪光让[npc.Name]暂时眩晕，[npc.sheIsFull]正在努力恢复对感官的控制！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.FLASH.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect FLASH_1 = new AbstractStatusEffect(10,
			"致盲 (次生辉光)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"在第一道刺眼的闪光之后，还有一连串炫目的闪光在不断眩晕[npc.name]，使[npc.herHim]难以采取任何行动！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.FLASH_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	public static AbstractStatusEffect CLOAK_OF_FLAMES = new AbstractStatusEffect(10,
			"火焰斗篷",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull]被笼罩在奥术火焰制成的斗篷中，这增加了[npc.her]的火焰抗性和寒冷抗性。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CLOAK_OF_FLAMES_1 = new AbstractStatusEffect(10,
			"火焰斗篷 (烈焰)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			Util.newArrayListOfValues("施法者每有一级，徒手攻击伤害额外+1",
					"徒手攻击造成[style.boldFire(火焰伤害)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull]被笼罩在奥术火焰制成的斗篷中，这不仅增加了[npc.her]的抗性，还为[npc.her]的徒手攻击附加了火焰伤害！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CLOAK_OF_FLAMES_2 = new AbstractStatusEffect(10,
			"火焰斗篷 (地狱烈焰)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 25f),
					new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			Util.newArrayListOfValues("施法者每有一级，徒手攻击伤害额外+1",
					"徒手攻击造成[style.boldFire(火焰伤害)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target,
						"[npc.NameHasFull]被笼罩在奥术火焰制成的斗篷中，这不仅增加了[npc.her]的抗性和火系伤害，还为[npc.her]的徒手攻击附加了火焰伤害！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CLOAK_OF_FLAMES_3 = new AbstractStatusEffect(10,
			"火焰斗篷 (火焰之环)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 25f),
					new Value<>(Attribute.RESISTANCE_FIRE, 10f),
					new Value<>(Attribute.RESISTANCE_ICE, 20f)),
			Util.newArrayListOfValues("施法者每有一级，徒手攻击伤害额外+1",
					"徒手攻击造成[style.boldFire(火焰伤害)]",
					"攻击者受到<b>5</b>点[style.colourFire(火焰伤害)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull]被笼罩在奥术火焰制成的斗篷中，这不仅增加了[npc.her]的抗性和火系伤害，还为[npc.her]的徒手攻击附加了火焰伤害！"
						+ "任何蠢到用近战攻击[npc.herHim]的敌人都会受到反击的火焰伤害！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_WILDFIRE = new AbstractStatusEffect(10,
			"野火",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 20f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.namePos]队中的火元素正在向[npc.herHim]灌输知识，教导[npc.herHim]该如何将火系攻击伤害最大化！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_1)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.FIRE) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_BURNING_DESIRE = new AbstractStatusEffect(10,
			"点燃欲望",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.namePos]队中的火元素正在往[npc.Name]体内填入熊熊欲火！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInCombat()) {
				List<GameCharacter> enemies = Main.combat.getEnemies(target);
				
				for(GameCharacter combatant : enemies) {
					if(combatant.isElemental()
							&& ((Elemental)combatant).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_2)
							&& ((Elemental)combatant).getCurrentSchool()==SpellSchool.FIRE) {
						return true;
					}
				}
			}
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_SERVANT_OF_FIRE = new AbstractStatusEffect(10,
			"烈焰仆佣",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			null,
			Util.newArrayListOfValues("[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+"的最大值)]-50%")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
					"[npc.namePos]宣誓效忠烈火学派，[npc.her]的火元素，即[npc2.name]，正在随心所欲地汲取着[npc.namePos]的能量！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.FIRE;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_SERVANT_OF_FIRE_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"能量汲取",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(非魅惑伤害)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull]随心所欲地吸取着[npc2.namePos]的能量，使其能够造成大量伤害！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.FIRE;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_BINDING_OF_FIRE = new AbstractStatusEffect(10,
			"烈焰束缚",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 25f),
					new Value<>(Attribute.RESISTANCE_FIRE, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"[npc.namePos]令烈火学派随其心意而动，[npc.her]的火元素，即[npc2.name]，被迫向[npc.mistress]揭露出[npc2.her]所有的秘密。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.FIRE;
		}
	};
	
	public static AbstractStatusEffect FREEZING_FOG = new AbstractStatusEffect(10,
			"冻人寒雾",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -20f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"法术“冰刃术”留下的寒雾萦绕在[npc.name]周围。奥术寒气正渗入着[npc.her]的身体，减缓了[npc.her]的行动，使其思维变得迟钝。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ICE_SHARD_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FROZEN = new AbstractStatusEffect(10,
			"冻寒",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "萦绕在[npc.name]周围的寒雾炸开，用薄薄的冰层盖住了[npc.herHim]，减缓了[npc.her]的行动！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ICE_SHARD_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect RAIN_CLOUD = new AbstractStatusEffect(10,
			"雨云术",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"一小片充满奥术的雨云正在[npc.namePos]的头顶盘旋。冻雨侵蚀了[npc.her]的施法能力。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.RAIN_CLOUD.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect RAIN_CLOUD_DEEP_CHILL = new AbstractStatusEffect(10,
			"雨云术 (深度寒意)",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f),
					new Value<>(Attribute.RESISTANCE_ICE, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"一小片充满奥术的雨云正在[npc.namePos]的头顶盘旋。冻雨侵蚀了[npc.her]的施法能力，也降低了[npc.her]对寒冷的抵抗力。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.RAIN_CLOUD_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect RAIN_CLOUD_DOWNPOUR = new AbstractStatusEffect(10,
			"雨云术 (倾盆大雨)",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f),
					new Value<>(Attribute.RESISTANCE_ICE, -25f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"一片充满奥术的雨云正在[npc.namePos]的头顶盘旋。"
							+ "冰冷的暴雨侵蚀了[npc.her]的施法能力，也降低了[npc.her]对寒冷的抵抗力，使[npc.herself]更难保护自己。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.RAIN_CLOUD_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST = new AbstractStatusEffect(10,
			"雨云术 (倾盆大雨)",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f),
					new Value<>(Attribute.RESISTANCE_ICE, -25f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"一片充满奥术的雨云正在[npc.namePos]的头顶盘旋。"
							+ "冰冷的暴雨侵蚀了[npc.her]的施法能力，也降低了[npc.her]对寒冷的抵抗力，使[npc.herself]更难保护自己。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.RAIN_CLOUD_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect RAIN_CLOUD_CLOUDBURST = new AbstractStatusEffect(10,
			"雨云 (大暴雨)",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -50f),
					new Value<>(Attribute.RESISTANCE_ICE, -25f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"一片充满奥术的雨云正在[npc.namePos]的头顶盘旋。"
							+ "冰冷的倾盆大雨侵蚀了[npc.her]的施法能力，也降低了[npc.her]对寒冷的抵抗力，使[npc.herself]更难保护自己。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.RAIN_CLOUD_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_CRASHING_WAVES = new AbstractStatusEffect(10,
			"惊涛骇浪",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_ICE, 20f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.namePos]队中的水元素正在向[npc.herHim]灌输知识，教导[npc.herHim]该如何将冰系攻击伤害最大化！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_1)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.WATER) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_CALM_WATERS = new AbstractStatusEffect(10,
			"古井无波",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.namePos]队中的水元素正在帮助[npc.her]平息对性的渴望。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_2)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.WATER) {
					return true;
				}
			}
			
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_SERVANT_OF_WATER = new AbstractStatusEffect(10,
			"水波仆佣",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			null,
			Util.newArrayListOfValues("[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+"的最大值)]-50%")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"[npc.namePos]宣誓效忠激流学派，[npc.her]的水元素，即[npc2.name]，正在随心所欲地汲取着[npc.namePos]的能量！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.WATER;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_SERVANT_OF_WATER_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"能量汲取",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(非魅惑伤害)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull]随心所欲地吸取着[npc2.namePos]的能量，使其能够造成大量伤害！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.WATER;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_BINDING_OF_WATER = new AbstractStatusEffect(10,
			"水波束缚",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_ICE, 25f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"[npc.namePos]令激流学派随其心意而动，[npc.her]的水元素，即[npc2.name]，被迫向[npc.mistress]揭露出[npc2.her]所有的秘密。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.WATER;
		}
	};
	
	public static AbstractStatusEffect POISON_VAPOURS = new AbstractStatusEffect(10,
			"毒云术",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			null,
			Util.newArrayListOfValues("每回合造成<b>25</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"！")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "毒气继续萦绕在[npc.namePos]的身体四周，导致[npc.herHim]每次吸气时都会咳嗽与呛咳。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.POISON_VAPOURS.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISON_VAPOURS_CHOKING_HAZE = new AbstractStatusEffect(10,
			"毒云术 (窒息雾霾)",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues("每回合造成<b>25</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"</b>")) {
				@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"！")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "毒气继续萦绕在[npc.namePos]的身体四周，导致[npc.herHim]每次吸气时都会咳嗽与呛咳。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.POISON_VAPOURS_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISON_VAPOURS_ARCANE_SICKNESS = new AbstractStatusEffect(10,
			"毒云术 (奥术疾疫)",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues("每回合造成<b>25</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"</b>",
					"每回合<b>10</b>点"+Attribute.MANA_MAXIMUM.getColouredName("b")+"[style.boldTerrible(流失)]</b>")) {
				@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			int lustDamage = 10;
			target.incrementMana(-lustDamage);
			
			return UtilText.parse(target,
					"[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"，并失去了<b>" + lustDamage + "</b>"+Attribute.MANA_MAXIMUM.getColouredName("b")+"！")
					+ damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "毒云继续萦绕在[npc.namePos]的四周，导致[npc.herHim]每次吸气时都会咳嗽与呛咳。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.POISON_VAPOURS_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISON_VAPOURS_WEAKENING_CLOUD = new AbstractStatusEffect(10,
			"毒云术 (弱化之云)",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, -15f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues("每回合造成<b>25</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"</b>",
					"每回合<b>10</b>点"+Attribute.MANA_MAXIMUM.getColouredName("b")+"[style.boldTerrible(流失)]</b>")) {
				@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			int lustDamage = 10;
			target.incrementMana(-lustDamage);
			
			return UtilText.parse(target,
					"[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点"+Attribute.DAMAGE_POISON.getColouredName("b")+"，并失去了<b>" + lustDamage + "</b>"+Attribute.MANA_MAXIMUM.getColouredName("b")+"！")
					+ damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "毒云继续萦绕在[npc.namePos]的四周，导致[npc.herHim]每次吸气时都会咳嗽与呛咳。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.POISON_VAPOURS_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect VACUUM = new AbstractStatusEffect(10,
			"真空术",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "空气中持续存在的虚空不断游弋，靠近[npc.namePos]的身体，使[npc.her]失去平衡！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.VACUUM.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect VACUUM_SECONDARY_VOIDS = new AbstractStatusEffect(10,
			"真空术 (次生虚空)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"空气中持续存在的虚空不断游弋，靠近[npc.namePos]的身体，使[npc.her]失去平衡！"
						+ "在[npc.herHim]周围，还不断有更小的次生虚空在产生与坍塌！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.VACUUM_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect VACUUM_SUCTION = new AbstractStatusEffect(10,
			"真空术 (抽吸)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues("每回合有<b>10%</b>的概率[style.boldExcellent(脱掉)]衣物")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(!target.isUnique() && (Math.random()<(target.isPlayer()?0.1f:0.166f))) { // I purposefully boost the chance in secret to make the player feel better about the RNG
				List<AbstractClothing> suitableClothing = new ArrayList<>();
				for(AbstractClothing c : new ArrayList<>(target.getClothingCurrentlyEquipped())) {
					if(target.isAbleToUnequip(c, false, target)
							&& !c.getSlotEquippedTo().isJewellery()) {
						suitableClothing.add(c);
					}
				}
				if(!suitableClothing.isEmpty()) {
					AbstractClothing clothingBlownOff = suitableClothing.get(Util.random.nextInt(suitableClothing.size()));
					target.unequipClothingOntoFloor(clothingBlownOff, true, target);
					if(target.isPlayer()) {
						return "你的"+clothingBlownOff.getName()+"被吸走，并吹到了地上！";
					} else {
						return "[npc.NamePos]"+clothingBlownOff.getName()+"被吸走，吹到了地上！";
					}
				}
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"持续存在的强力虚空，持续靠近着[npc.namePos]的身体，使[npc.her]失去平衡！"
						+ "在[npc.herHim]周围，还不断有更小的次生虚空在产生与坍塌！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.VACUUM_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect VACUUM_TOTAL_VOID = new AbstractStatusEffect(10,
			"真空术 (完全真空)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues("每回合<b>25%</b>的概率[style.boldExcellent(脱掉)]衣物")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(!target.isUnique() && (Math.random()<(target.isPlayer()?0.25f:0.33f))) { // I purposefully boost the chance in secret to make the player feel better about the RNG
				List<AbstractClothing> suitableClothing = new ArrayList<>();
				for(AbstractClothing c : new ArrayList<>(target.getClothingCurrentlyEquipped())) {
					if(target.isAbleToUnequip(c, false, target)
							&& !c.getSlotEquippedTo().isJewellery()) {
						suitableClothing.add(c);
					}
				}
				if(!suitableClothing.isEmpty()) {
					AbstractClothing clothingBlownOff = suitableClothing.get(Util.random.nextInt(suitableClothing.size()));
					target.unequipClothingOntoFloor(clothingBlownOff, true, target);
					if(target.isPlayer()) {
						return "你的"+clothingBlownOff.getName()+"被吸走，吹到了地上！";
					} else {
						return "[npc.NamePos]"+clothingBlownOff.getName()+"被吸走，吹到了地上！";
					}
				}
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target,
						"持续存在且无比强劲的虚空，正在靠近着[npc.namePos]的身体，使[npc.her]失去平衡！"
						+ "在[npc.herHim]周围，还不断有更小的次生虚空在产生与坍塌！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.VACUUM_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PROTECTIVE_GUSTS = new AbstractStatusEffect(10,
			"护体之风",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_POISON, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"召唤了一股仁慈之风，保护[npc.name]免受有毒的蒸气，并帮助抵御针对[npc.herHim]的攻击。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.PROTECTIVE_GUSTS.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect PROTECTIVE_GUSTS_GUIDING_WIND = new AbstractStatusEffect(10,
			"护体之风 (指引之风)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_POISON, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"召唤了一股仁慈之风，保护[npc.name]免受有毒的蒸气，并帮助抵御针对[npc.herHim]的攻击。"
							+ "还有助于引导[npc.her]的攻击。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.PROTECTIVE_GUSTS_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect PROTECTIVE_GUSTS_FOCUSED_BLAST = new AbstractStatusEffect(10,
			"护体之风 (气浪聚合)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_POISON, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 3f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"召唤了一股仁慈之风，保护[npc.name]免受毒气之苦，并帮助抵御针对[npc.herHim]的攻击。"
							+ "还有助于引导和增强[npc.her]的攻击。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.PROTECTIVE_GUSTS_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_WHIRLWIND = new AbstractStatusEffect(10,
			"旋风",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"敌人队中的风元素化作狂风，围绕在[npc.name]的四周！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInCombat()) {
				List<GameCharacter> enemies = Main.combat.getEnemies(target);
				
				for(GameCharacter combatant : enemies) {
					if(combatant.isElemental()
							&& ((Elemental)combatant).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_1)
							&& ((Elemental)combatant).getCurrentSchool()==SpellSchool.AIR) {
						return true;
					}
				}
			}
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_VITALISING_SCENTS = new AbstractStatusEffect(10,
			"活力香氛",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.namePos]队中的风元素正在向[npc.herHim]灌输知识，教导该如何躲避攻击，并回以有力的冲击！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_2)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.AIR) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_SERVANT_OF_AIR = new AbstractStatusEffect(10,
			"气旋仆佣",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			null,
			Util.newArrayListOfValues("[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+"的最大值)]-50%")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"[npc.namePos]宣誓效忠大气学派，[npc.her]的风元素，即[npc2.name]，正在随心所欲地汲取着[npc.namePos]的能量！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.AIR;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_SERVANT_OF_AIR_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"能量汲取",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(非魅惑伤害)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull]随心所欲地吸取着[npc2.namePos]的能量，使其能够造成大量伤害！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.AIR;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_BINDING_OF_AIR = new AbstractStatusEffect(10,
			"气旋束缚",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_POISON, 25f),
					new Value<>(Attribute.RESISTANCE_POISON, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"[npc.namePos]令大气学派随其心意而动，[npc.her]的风元素，即[npc2.name]，被迫向[npc.mistress]揭露出[npc2.her]所有的秘密。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.AIR;
		}
	};
	
	public static AbstractStatusEffect SLAM_GROUND_SHAKE = new AbstractStatusEffect(10,
			"震地",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.namePos]脚下的地面正在摇晃，使[npc.herHim]很难躲避袭来的攻击。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.SLAM_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect SLAM_AFTER_SHOCK = new AbstractStatusEffect(10,
			"震地 (余波)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.namePos]脚下的地面正在摇晃，使[npc.herHim]很难躲避袭来的攻击。"
						+ "[npc.she]能感受到有余波即将到来，但却无法回避……");
		}
		@Override
		protected String extraRemovalEffects(GameCharacter target){
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点"+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"！")+damageValue.getKey();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.SLAM_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEKENETIC_SHOWER = new AbstractStatusEffect(10,
			"念力之雨",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			null,
			Util.newArrayListOfValues("每回合造成<b>25</b>点"+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点"+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"！")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"不断有石块和其他小物体砸中[npc.NameIsFull]。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.TELEKENETIC_SHOWER.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEKENETIC_SHOWER_PRECISION_STRIKES = new AbstractStatusEffect(10,
			"念力之雨 (精确打击)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -20f)),
			Util.newArrayListOfValues("每回合造成<b>25</b>点"+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点"+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"！")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"不断有石块和其他小物体精准地砸中[npc.NameIsFull]。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEKENETIC_SHOWER_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEKENETIC_SHOWER_UNSEEN_FORCE = new AbstractStatusEffect(10,
			"念力之雨 (无形之力)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -20f)),
			Util.newArrayListOfValues("每回合造成<b>50</b>点"+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 50);

			return UtilText.parse(target, "[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点"+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"！")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"不断有石块和其他小物体精准地砸中[npc.NameIsFull]。每次撞击后都会产生一波爆炸般的冲击力。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEKENETIC_SHOWER_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect STONE_SHELL = new AbstractStatusEffect(10,
			"磐石之壳",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.Name]与敌人之间竖起了一道坚固的石质屏障，使[npc.herHim]的物理防御能力显著提高。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.STONE_SHELL.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect STONE_SHELL_SHIFTING_SANDS = new AbstractStatusEffect(10,
			"磐石之壳 (流沙)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.Name]与敌人之间竖起了一道坚固的石质屏障，使[npc.herHim]的物理防御能力显著提高。"
								+ "每隔一段时间，石质屏障就会突然化作沙子，随后在其他地方重新形成。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.STONE_SHELL_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect STONE_SHELL_HARDENED_CARAPACE = new AbstractStatusEffect(10,
			"磐石之壳 (外壳硬化)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.Name]与敌人之间竖起了一道硬化的石质屏障，使[npc.herHim]的物理防御能力显著提高。"
								+ "每隔一段时间，石质屏障就会突然化作沙子，随后在其他地方重新形成。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.STONE_SHELL_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect STONE_SHELL_EXPLOSIVE_FINISH = new AbstractStatusEffect(10,
			"磐石之壳 (爆破终结)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues("磐石之壳效果结束时[style.colourExcellent(所有敌人)]受到<b>10</b>点"+Attribute.DAMAGE_PHYSICAL.getColouredName("b"))) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.Name]与敌人之间竖起了一道硬化的石质屏障，使[npc.herHim]的物理防御能力显著提高。"
								+ "每隔一段时间，石质屏障就会突然化作沙子，随后在其他地方重新形成。");
		}
		@Override
		protected String extraRemovalEffects(GameCharacter target){
			StringBuilder sb = new StringBuilder();
			
			boolean first=true;
			for(GameCharacter combatant : Main.combat.getEnemies(target)) {
				Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(target, combatant, 10);
				sb.append(UtilText.parse(combatant,
						target, (first?"":"<br/>")+"<br/>[npc2.namePos]的磐石之壳炸开，使[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点"+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"！")
						+damageValue.getKey());
				first=false;
			}
			
			return sb.toString();
			
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.STONE_SHELL_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_ROLLING_STONE = new AbstractStatusEffect(10,
			"滚石",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.namePos]队中的土元素正用着冲击波增强着[npc.her]的攻击！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_1)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.EARTH) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_HARDENING = new AbstractStatusEffect(10,
			"硬化",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.namePos]队中的土元素正在使用念力控制着岩石碎片保护[npc.herHim]！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_2)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.EARTH) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_SERVANT_OF_EARTH = new AbstractStatusEffect(10,
			"沙土仆佣",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			null,
			Util.newArrayListOfValues("[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+"的最大值)]-50%")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"[npc.namePos]宣誓效忠大地学派，[npc.her]的土元素，即[npc2.name]，正在随心所欲地汲取着[npc.namePos]的能量！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.EARTH;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_SERVANT_OF_EARTH_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"能量汲取",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(非魅惑伤害)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull]随心所欲地吸取着[npc2.namePos]的能量，使其能够造成大量伤害！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.EARTH;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_BINDING_OF_EARTH = new AbstractStatusEffect(10,
			"沙土束缚",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"[npc.namePos]令大地学派随其心意而动，[npc.her]的土元素，即[npc2.name]，被迫向[npc.mistress]揭露出[npc2.her]所有的秘密。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.EARTH;
		}
	};
	

	public static AbstractStatusEffect ARCANE_AROUSAL_LUSTFUL_DISTRACTION = new AbstractStatusEffect(10,
			"情迷欲乱",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"令人兴奋的图像与想法不断涌入[npc.namePos]的脑海，导致[npc.herHim]无法专注于命中。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_AROUSAL_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ARCANE_AROUSAL_DIRTY_PROMISES = new AbstractStatusEffect(10,
			"情迷欲乱 (淫誓欲言)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -15f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"令人兴奋的图像与想法不断冲入[npc.namePos]的脑海，导致[npc.herHim]无法专注于命中。"
								+ "[npc.she]偶尔会听到幻象般的耳语，向[npc.she]许诺只要顺从，就享受一段美好的时光。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_AROUSAL_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPATHIC_COMMUNICATION = new AbstractStatusEffect(10,
			"心灵低语",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull]能够将[npc.her]的下流逗弄与[npc.moans+]直接投射入他人的脑海中！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.TELEPATHIC_COMMUNICATION.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH = new AbstractStatusEffect(10,
			"触摸投射",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 30f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull]能够将[npc.her]的下流逗弄与[npc.moans+]直接投射入他人的脑海中！"
								+ "此外，[npc.she]可以为目标提供如梦般妙曼的亲吻与抚摸。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEPATHIC_COMMUNICATION_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION = new AbstractStatusEffect(10,
			"言语暗示",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 30f)),
			Util.newArrayListOfValues("[style.boldLust(挑逗)]对目标[style.boldExcellent(造成)]-25"+Attribute.RESISTANCE_PHYSICAL.getColouredName("b")+"，持续[style.boldGood(2回合)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull]能够将[npc.her]的下流逗弄与暗示直接投射入他人的脑海中！"
								+ "此外，[npc.she]可以为目标提供如梦般妙曼的亲吻与抚摸。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEPATHIC_COMMUNICATION_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED = new AbstractStatusEffect(10,
			"言语暗示",
			"telepathic_communication_power_of_suggestion_targeted",
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"投射入[npc.namePos]脑海中的呻吟与暗示让[npc.herHim]降低了警惕！");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	
	public static AbstractStatusEffect ARCANE_CLOUD = new AbstractStatusEffect(10,
			"奥术之云",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"一小团奥术云朵正在[npc.namePos]的头顶盘旋。在受其影响时，由奥术导致的欲望效应会渗入[npc.her]的脑中。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.ARCANE_CLOUD.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect ARCANE_CLOUD_ARCANE_LIGHTNING = new AbstractStatusEffect(10,
			"奥术之云 (奥术雷霆)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("每回合<b>5</b>点"+Attribute.DAMAGE_LUST.getColouredName("b")+"</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点[style.boldLust(性欲伤害)]！")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"一小团催情的奥术云朵正在[npc.namePos]的头顶盘旋。时不时会有一道奥术雷霆向[npc.herHim]降下，令[npc.her]脑内充斥着淫秽的想法。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_CLOUD_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect ARCANE_CLOUD_ARCANE_THUNDER = new AbstractStatusEffect(10,
			"奥术之云 (奥术雷鸣)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("每回合造成<b>15</b>点"+Attribute.DAMAGE_LUST.getColouredName("b")+"</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, target, 15);

			return UtilText.parse(target, "[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点[style.boldLust(性欲伤害)]！")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"一小团催情的奥术云朵正在[npc.namePos]的头顶盘旋。时不时会有一道伴随着轻声雷鸣的奥术雷霆出现，令[npc.her]脑内充斥着淫秽的想法。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_CLOUD_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ARCANE_CLOUD_LOCALISED_STORM = new AbstractStatusEffect(10,
			"奥术之云 (地域风暴)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("[style.boldTerrible(所有团队成员)]每回合承受<b>15</b>点"+Attribute.DAMAGE_LUST.getColouredName("b")+"</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			StringBuilder sb = new StringBuilder();
			
			List<GameCharacter> affectedCombatants = new ArrayList<>();
			affectedCombatants.add(target);
			affectedCombatants.addAll(Main.combat.getAllies(target));
			
			for(GameCharacter combatant : affectedCombatants) {
				Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, combatant, 15);
				
				sb.append(UtilText.parse(combatant, "<br/>[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点[style.boldLust(性欲伤害)]！")+damageValue.getKey());
			}
			
			return sb.toString();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"一场局部的奥术风暴正在[npc.namePos]的头顶盘旋。时不时会有一道伴随着轻声雷鸣的奥术雷霆出现，令[npc.her]脑内充斥着淫秽的想法。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_CLOUD_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_ARCANE_LEWD_ENCOURAGEMENTS = new AbstractStatusEffect(10,
			"淫乱激励",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.namePos]队中的奥术元素正在向[npc.her]的脑海中投射淫秽的鼓励话语！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_1)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.ARCANE) {
					return true;
				}
			}
			
			return false;
		}
	};

	public static AbstractStatusEffect ELEMENTAL_ARCANE_CARESSING_TOUCH = new AbstractStatusEffect(10,
			"爱抚之触",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"敌人队中的奥术元素正伸出幻象触手抚摸和摸索着[npc.namePos]的身体！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInCombat()) {
				List<GameCharacter> enemies = Main.combat.getEnemies(target);
				
				for(GameCharacter combatant : enemies) {
					if(combatant.isElemental()
							&& ((Elemental)combatant).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_2)
							&& ((Elemental)combatant).getCurrentSchool()==SpellSchool.ARCANE) {
						return true;
					}
				}
			}
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_ARCANE_SERVANT_OF_ARCANE = new AbstractStatusEffect(10,
			"奥术仆佣",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			null,
			Util.newArrayListOfValues("[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+"的最大值)]-50%")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"[npc.namePos]宣誓效忠奥术学派，[npc.her]的奥术元素，即[npc2.name]，正在随心所欲地汲取着[npc.namePos]的能量！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.ARCANE;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_ARCANE_SERVANT_OF_ARCANE_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"能量汲取",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(非魅惑伤害)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull]随心所欲地吸取着[npc2.namePos]的能量，使其能够造成大量伤害！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.ARCANE;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_ARCANE_BINDING_OF_ARCANE = new AbstractStatusEffect(10,
			"奥术束缚",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"[npc.namePos]令奥术学派随其心意而动，[npc.her]的奥术元素，即[npc2.name]，被迫向[npc.mistress]揭露出[npc2.her]所有的秘密。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.ARCANE;
		}
	};
	
	public static AbstractStatusEffect ARCANE_DUALITY_POSITIVE = new AbstractStatusEffect(10,
			"奥术二元 (保护)",
			"cleanse_positive",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f),
					new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 5f),
					new Value<>(Attribute.RESISTANCE_POISON, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"一道由奥术能量构筑的护罩环绕在[npc.name]的周围，为[npc.herHim]抵御着所有类型的伤害。");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ARCANE_DUALITY_NEGATIVE = new AbstractStatusEffect(10,
			"奥术二元 (弱化)",
			"cleanse_negative",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f),
					new Value<>(Attribute.RESISTANCE_LUST, -5f),
					new Value<>(Attribute.RESISTANCE_FIRE, -5f),
					new Value<>(Attribute.RESISTANCE_ICE, -5f),
					new Value<>(Attribute.RESISTANCE_POISON, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"一道由奥术能量构筑的弱化屏障环绕在[npc.name]的周围，使[npc.herHim]在遭遇各类伤害时更为脆弱。");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPORT = new AbstractStatusEffect(10,
			"传送",
			null,
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 100f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull]传送到敌人的身后，使其极有可能无法击中[npc.herHim]！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.TELEPORT.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPORT_ARCANE_ARRIVAL = new AbstractStatusEffect(10,
			"传送 (奥术抵达)",
			null,
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 100f)),
			Util.newArrayListOfValues("每回合选定一个随机敌人，造成<b>5</b>点"+Attribute.DAMAGE_LUST.getColouredName("b"))) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			GameCharacter randomEnemy = Main.combat.getEnemies(target).get(Util.random.nextInt(Main.combat.getEnemies(target).size()));
			
			Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, randomEnemy, 15);

			return UtilText.parse(randomEnemy, "[npc.Name]受到了<b>" + damageValue.getValue() + "</b>点[style.boldLust(性欲伤害)]！")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull]传送到敌人的身后，使其极有可能无法击中[npc.herHim]！伴随着[npc.her]的抵达，一阵催情的奥术能量也随后到来！");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEPORT_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	// SEX EFFECTS:
	
	public static AbstractStatusEffect CONDOM_WORN = new AbstractStatusEffect(80,
			"穿着避孕套",
			"condom",
			PresetColour.CLOTHING_PINK_LIGHT,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return -0.5f;
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modifiersList = new ArrayList<>();
			modifiersList.add("-0.5%<b style='color: " + PresetColour.GENERIC_SEX.toWebHexString() + "'>快感/回合</b>");
			return modifiersList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "因为你正带着避孕套，所以性爱的感觉可能不是那么好……";
			} else {
				return "因为[npc.sheIs]正带着避孕套，所以性爱的感觉对于[npc.Name]来说可能不是那么好……";
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isWearingCondom();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect FLOWING_WATER = new AbstractStatusEffect(80,
			"流动之水",
			"sexEffects/flowing_water",
			PresetColour.BASE_BLUE_LIGHT,
			PresetColour.BASE_AQUA,
			true,
			null,
			Util.newArrayListOfValues(
					"[style.colourDirty(被弄脏的身体部位)][style.colourAqua(很快就清洁干净了)]")) {
		public EffectBenefit getBeneficialStatus() {
			return EffectBenefit.NEUTRAL;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "清水流过[npc.namePos]的身体，[npc.herHim]身上不再有脏污了！");
			}
			return null;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex() && Main.sex.getInitialSexManager().isWashingScene();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect DESIRES = new AbstractStatusEffect(80,
			"欲望",
			"desires",
			PresetColour.GENERIC_ARCANE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "你的性癖和欲望会影响你从相关的性行为中获得的快感。选择一个与你拥有的性癖相关联的行动也不会增加你的堕落。";
				
//			} else if(Main.game.isInSex()) {
//				GameCharacter targetedCharacter = Main.sex.getTargetedPartner(target);
//				SexType foreplayPreference = Main.sex.getForeplayPreference((NPC) target, targetedCharacter);
//				SexType mainPreference = Main.sex.getMainSexPreference((NPC) target, targetedCharacter);
//				
//				return UtilText.parse(target, targetedCharacter,
//						(Main.game.isInNewWorld()
//								?"The power of your arcane aura allows you to sense [npc.namePos] sexual preferences:"
//								:"Somehow, you're able to instinctively sense what [npc.namePos] sexual preferences are:")
//						+ "<br/>[style.italics"+(Main.sex.isInForeplay(target)?"PinkLight(<b>Foreplay</b>: ":"Disabled(Foreplay: ")
//							+ (foreplayPreference!=null
//									?"[npc.Her] "+foreplayPreference.getPerformingSexArea().getName(target, true)+" and [npc2.namePos] "+foreplayPreference.getTargetedSexArea().getName(targetedCharacter, true)+"."
//									:"[npc.She] [npc.has] no preference...")
//							+ ")]"
//						+ "<br/>[style.italics"+(!Main.sex.isInForeplay(target)?"Pink(<b>Sex</b>: ":"Disabled(Sex: ")
//						+ (mainPreference!=null
//								?"[npc.Her] "+mainPreference.getPerformingSexArea().getName(target, true)+" and [npc2.namePos] "+mainPreference.getTargetedSexArea().getName(targetedCharacter, true)+"."
//								:"[npc.She] [npc.has] no preference...")
//						+ ")]")
//						+ (Main.sex.isCharacterObeyingTarget(target, Main.game.getPlayer())
//							?"<br/>[style.italicsMinorGood([npc.She] will listen to your requests.)]"
//							:"<br/>[style.italicsMinorBad([npc.She] will ignore all of your requests.)]");
//				
			} else {
				return UtilText.parse(target,
						(Main.game.isInNewWorld()
								?"由于你潜在的奥术灵气，你能感知到[npc.namePos]对性动作的偏好。"
								:"不知为什么，你天然能感知到[npc.namePos]的性偏好。")
						+ "<br/>"
						+ "[style.italicsSex(在性爱中，你可以探知[npc.name]想要使用什么区域)]");
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>();
			
			if(Main.game.isInSex()) {
				GameCharacter targetedCharacter = Main.sex.getTargetedPartner(target);
				SexType foreplayPreference = Main.sex.getForeplayPreference(target, targetedCharacter);
				SexType mainPreference = Main.sex.getMainSexPreference(target, targetedCharacter);
				
//				modList.add(UtilText.parse(target, "<b>[npc.NamePos] Preferences:</b>"));
				
				modList.add(UtilText.parse(target, targetedCharacter,
										"[style.italics"+(Main.sex.isInForeplay(target)?"PinkLight(<b>前戏</b>:":"Disabled(前戏：")
											+ (foreplayPreference!=null
													?"[npc.Her]的"+foreplayPreference.getPerformingSexArea().getName(target, true)+"和[npc2.namePos]的"+foreplayPreference.getTargetedSexArea().getName(targetedCharacter, true)+"。"
													:"[npc.She]没有偏好……")
											+ ")]"));
				modList.add(UtilText.parse(target, targetedCharacter,
										"[style.italics"+(!Main.sex.isInForeplay(target)?"Pink(<b>正戏</b>:":"Disabled(正戏：")
											+ (mainPreference!=null
													?"[npc.Her]的"+mainPreference.getPerformingSexArea().getName(target, true)+"和[npc2.namePos]的"+mainPreference.getTargetedSexArea().getName(targetedCharacter, true)+"。"
													:"[npc.She]没有偏好……")
											+ ")]"));
				modList.add(UtilText.parse(target, targetedCharacter,
										Main.sex.isCharacterObeyingTarget(target, Main.game.getPlayer())
										?"[style.italicsMinorGood([npc.She]会听从你的请求)]"
										:"[style.italicsMinorBad([npc.She]会忽视你所有的请求)]"));
			}
			
			return modList;
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			List<Value<Integer, String>> additionalDescriptions = new ArrayList<>();
			List<AbstractFetish> orderedFetishList = new ArrayList<>();
			
			for(AbstractFetish f : Fetish.getAllFetishes()) {
				FetishDesire desire = target.getFetishDesire(f);
				if(desire!=FetishDesire.TWO_NEUTRAL) {
					orderedFetishList.add(f);
				}
			}
			orderedFetishList.sort((e1, e2) -> target.getFetishDesire(e2).compareTo(target.getFetishDesire(e1)));

			for(AbstractFetish f : orderedFetishList) {
				FetishDesire desire = target.getFetishDesire(f);
				additionalDescriptions.add(new Value<>(1, "<b style='color:"+desire.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(desire.getNameAsVerb())+"</b>: "+Util.capitaliseSentence(f.getShortDescriptor(target))));
			}
			
			return additionalDescriptions;
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.isPlayer() || Main.game.isInSex();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ORGASM_COUNTER = new AbstractStatusEffect(80,
			"高潮",
			"sexEffects/orgasms",
			PresetColour.GENERIC_ARCANE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			if(target.isPlayer()) {
				sb.append("任何像你这样有强大奥术灵气的人，在性高潮后都不会有任何不应期……");
				
			} else {
				sb.append("任何像你这样处于强大奥术灵气之下的人，在性高潮后都不会有任何不应期……");
			}
			
			sb.append("<br/>在[npc.sheIs]满足之前，[npc.Name]需要达到[style.boldSex(" + Util.intToCount(target.getOrgasmsBeforeSatisfied()) + ")]高潮。");

			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			List<Value<Integer, String>> additionalDescriptions = new ArrayList<>();
			
			if(Main.sex.getNumberOfOrgasms(target)>=target.getOrgasmsBeforeSatisfied()) {
				additionalDescriptions.add(new Value<>(1, UtilText.parse(target, "[npc.NameIsFull]感到[style.colourExcellent(心满意足)]。")));
			} else {
				additionalDescriptions.add(new Value<>(1, UtilText.parse(target, "[npc.NameIsFull]还[style.colourTerrible(不够满足)]！")));
			}
			
			if(!target.isAbleToOrgasm()) {
				additionalDescriptions.add(new Value<>(1, UtilText.parse(target, "[npc.NameIsFull][style.colourTerrible(无法达到高潮)]！")));
			}
			
			int bonus = Main.sex.getNumberOfAdditionalOrgasms(target);
			if(!target.isPlayer() && bonus != 0) {
				if(bonus>0) {
					additionalDescriptions.add(
							new Value<>(2,
									UtilText.parse(target,
											"[npc.Her]的欲望得到了[style.boldExcellent(提升)]，因此高潮目标次数比正常情况多了[style.boldGood(" + Util.intToString(bonus) + "次)]！")));
				} else {
					additionalDescriptions.add(
							new Value<>(2,
									UtilText.parse(target,
											"[npc.Her]的欲望得到了[style.boldTerrible(抑制)]，因此高潮目标次数比正常情况少了[style.boldBad(" + Util.intToString(-bonus) + ")]次！")));
				}
			}

			return additionalDescriptions;
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>();

			Colour orgasmColour = PresetColour.GENERIC_ARCANE;
			int orgasms = Main.sex.getNumberOfOrgasms(target);
			if(orgasms<RenderingEngine.orgasmColours.length) {
				orgasmColour = RenderingEngine.orgasmColours[orgasms];
			}
			
			modList.add("<b style='color:"+orgasmColour.toWebHexString()+";'>"+orgasms+"</b>高潮");

			int essences = Main.sex.getEssenceGeneration(target);
			if(target.hasStatusEffect(StatusEffect.RECOVERING_AURA)) {
				modList.add("会产出[style.boldBad(0精华)]");
				modList.add("受[style.boldBad(“"+RECOVERING_AURA.getName(target)+"”)]效果影响");
				
			} else {
				if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
					modList.add("性爱后生成[style.boldArcane("+(essences)+"精华)]");
					modList.add("因[style.colourTrait("+Perk.NYMPHOMANIAC.getName(target)+"特性)]而[style.boldExcellent(双倍)]");
					
				} else {
					modList.add("会产出[style.boldArcane("+(essences)+"精华)]");
				}
				
				if(orgasms>=5) {
					modList.add("[style.boldBad(达到最大精华量)]");
				}
			}
			return modList;
		}
		@Override
		public String getPathName(GameCharacter owner) {
			if(Main.sex.getNumberOfOrgasms(owner)>=owner.getOrgasmsBeforeSatisfied()) {
				return "sexEffects/orgasmsSatisfied";
			}
			return super.getPathName(owner);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			StringBuilder SVGImageSB = new StringBuilder();

			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+super.getSVGString(owner)+"</div>");
			
			int orgasms = Main.sex.getNumberOfOrgasms(owner);
			
			SVGImageSB.append("<div style='width:40%;height:40%;position:absolute; top:0; right:4px;'>");
				if(orgasms == 0) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterZero());
				} else if(orgasms == 1) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterOne());
				} else if(orgasms == 2) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterTwo());
				} else if(orgasms == 3) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterThree());
				} else if(orgasms == 4) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterFour());
				} else if(orgasms == 5) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterFive());
				} else {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterFivePlus());
				}
			SVGImageSB.append("</div>");
			
			return SVGImageSB.toString();
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PENIS_STATUS = new AbstractStatusEffect(95,
			"阴茎状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getPenetrationArousalPerTurn(target, SexAreaPenetration.PENIS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return 0;
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getPenetrationModifiersAsStringList(target, SexAreaPenetration.PENIS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaPenetration type = SexAreaPenetration.PENIS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(抚摸自己的[npc.cock])]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在给[npc2.name][style.boldSex(手淫)]！"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]用[npc.her]的两根[npc.cocks(true)]在[style.boldSex(击剑)]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在跟[npc2.name][style.boldSex(击剑)]！"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给自己[style.boldSex(尾淫)]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在给[npc2.name][style.boldSex(尾淫)]！"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给自己[style.boldSex(触手淫)]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在给[npc2.name][style.boldSex(触手淫)]！"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给自己[style.boldSex(口交)]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), "[npc.NameIsFull]正在给[npc2.name][style.boldSex(口交)]！"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在用[npc.her]自己的[npc.cock]跟[style.boldSex(阴蒂击剑)]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在用[npc.her]的[npc.cock]跟[npc2.name][style.boldSex(阴蒂击剑)]！"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给自己[style.boldSex(足交)]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在给[npc2.name][style.boldSex(足交)]！"));
								}
								break;
						}
					}
				}
			}
			
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}

			appendPenetrationAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos]的[npc.penis]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.PENIS).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.PENIS).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							target,
//							SexAreaPenetration.PENIS,
//							partner,
//							Main.sex.getOngoingActionsMap(target).get(SexAreaPenetration.PENIS).get(partner).iterator().next())));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& Main.sex.getOngoingActionsMap(target)!=null
					&& !Main.sex.getOngoingSexAreas(target, SexAreaPenetration.PENIS).isEmpty()
					&& !Collections.disjoint(
							Main.sex.getOngoingSexAreas(target, SexAreaPenetration.PENIS).get(Main.sex.getCharacterOngoingSexArea(target, SexAreaPenetration.PENIS).get(0)),
							Util.newArrayListOfValues(SexAreaPenetration.values()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaPenetration.PENIS, SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypePenis());
		}
	};
	
	public static AbstractStatusEffect CLIT_STATUS = new AbstractStatusEffect(95,
			"阴蒂状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getPenetrationArousalPerTurn(target, SexAreaPenetration.CLIT);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return 0;
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getPenetrationModifiersAsStringList(target, SexAreaPenetration.CLIT);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaPenetration type = SexAreaPenetration.CLIT;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(指交)]自己！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(指交)][npc2.name]！"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(挑逗)]着[npc.her]自己的[npc.cock]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(抚弄)][npc2.namePos]的[npc2.cock]！"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用尾巴挑逗着)]自己的小穴！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用尾巴挑逗着)][npc2.namePos]的小穴！"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用触手挑逗着)][npc.her]自己的小穴！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用触手挑逗着)][npc2.namePos]的小穴！"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给[npc.herself][style.boldSex(舔阴)] ！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在给[npc2.name][style.boldSex(舔阴)] ！"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴户磨蹭着)][npc.herself]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在跟[npc2.name][style.boldSex(磨镜着)]！"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用脚挑逗着)][npc.her]自己的小穴！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用脚挑逗着)][npc2.namePos]的小穴！"));
								}
								break;
						}
						
					}
				}
			}
			
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}

			appendPenetrationAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos][npc.clit]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.CLIT).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.CLIT).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							target,
//							SexAreaPenetration.CLIT,
//							partner,
//							Main.sex.getOngoingActionsMap(target).get(SexAreaPenetration.CLIT).get(partner).iterator().next())));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& Main.sex.getOngoingActionsMap(target)!=null
					&& !Main.sex.getOngoingSexAreas(target, SexAreaPenetration.CLIT).isEmpty()
					&& !Collections.disjoint(
							Main.sex.getOngoingSexAreas(target, SexAreaPenetration.CLIT).get(Main.sex.getCharacterOngoingSexArea(target, SexAreaPenetration.CLIT).get(0)),
							Util.newArrayListOfValues(SexAreaPenetration.values()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaPenetration.CLIT, SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeClit());
		}
	};
	
	public static AbstractStatusEffect ANUS_STATUS = new AbstractStatusEffect(96,
			"肛门状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.ANUS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.ANUS);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.ANUS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.ANUS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");

			boolean descriptionAdded = false;
			for(SexAreaPenetration pen : SexAreaPenetration.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, pen)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(pen) {
						case FINGER:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(指交)]着自己的[npc.asshole]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(指交)][npc.namePos]的[npc.asshole]！"));
							}
							break;
						case PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的[npc.asshole]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(操着)][npc.namePos]的[npc.asshole]！"));
							}
							break;
						case TAIL:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)]自己的[npc.asshole]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(用尾巴操着)][npc.namePos]的[npc.asshole]！"));
							}
							break;
						case TENTACLE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)]自己的[npc.asshole]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(触手操着)][npc.namePos]的[npc.asshole]！"));
							}
							break;
						case TONGUE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给自己[style.boldSex(吻肛)]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"正在给[npc.name][style.boldSex(吻肛)]！"));
							}
							break;
						case CLIT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc.her]自己的[npc.asshole]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(用阴蒂操着)][npc.namePos]的[npc.asshole]！"));
							}
							break;
						case FOOT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]将[style.boldSex([npc.toes]插入)][npc.her]自己的[npc.asshole]里！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
										+(names.size()==1?UtilText.parse(main, "[style.boldSex(将[npc.toes]插入)]"):"[style.boldSex(将多根脚趾插入)]")
										+"[npc.namePos]的[npc.asshole]！"));
							}
							break;
					}
				}
			}

			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, orifice)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					boolean selfAction = target.equals(main);
					descriptionAdded = true;
					switch(orifice) {
						case ARMPITS:
						case ANUS:
						case ASS:
						case BREAST:
						case NIPPLE:
						case BREAST_CROTCH:
						case NIPPLE_CROTCH:
							break;
						case MOUTH:
							if(selfAction) {
								descriptionSB.append("[npc.NameIsFull]正在给自己[style.boldSex(吻肛)]！");
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"正在给[npc2.name][style.boldSex(吻肛)]！"));
							}
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的[npc.asshole]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(操着)][npc.namePos]的[npc.asshole]！"));
							}
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							break;
						case SPINNERET:
							break;
						case TUSSY:
							break;
					}
				}
			}
			
			if(!descriptionAdded) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her]的[npc.asshole]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.ANUS);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& Main.game.isAnalContentEnabled();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.ANUS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus());
		}
	};

	public static AbstractStatusEffect ASS_STATUS = new AbstractStatusEffect(96,
			"屁股状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.ASS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.ASS);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.ASS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.ASS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull][style.boldSex(抚弄着)][npc.her]自己的[npc.ass]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(抚弄着)][npc2.namePos]的[npc2.ass]！"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(尻交)]着[npc.herself]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(尻交)]着[npc2.name]！"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用尾巴尻交着)][npc.herself]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用尾巴尻交着)][npc2.name]！"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用触手尻交着)][npc.herself]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用触手尻交着)][npc2.name]！"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(舔弄着)][npc2.her]自己的[npc.ass]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(舔弄着)][npc2.namePos]的[npc2.ass]！"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂尻交着)][npc.herself]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂尻交着)][npc2.name]！"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在用[npc.her]的[npc.feet][style.boldSex(抚弄着)][npc.her]自己的[npc.ass]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]用[npc.her]的[npc.feet][style.boldSex(抚弄着)][npc2.namePos]的[npc2.ass]！"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(舔弄着)][npc2.her]自己的[npc.ass]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(舔弄着)][npc2.namePos]的[npc2.ass]！"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(尻交)]着[npc.herself]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(尻交)]着[npc2.name]！"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
							case TUSSY:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her]的[npc.ass]"), descriptionSB);
			
			descriptionSB.append("</p>");

			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			SexAreaOrifice orifice = SexAreaOrifice.ASS;
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							partner,
//							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
//							target,
//							orifice)));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.ASS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAss());
		}
	};
	
	public static AbstractStatusEffect MOUTH_STATUS = new AbstractStatusEffect(99,
			"嘴巴状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.MOUTH);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.MOUTH);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.MOUTH);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.MOUTH;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(吮吸着)]自己的[npc.fingers]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(吮吸)][npc.namePos]的[npc.fingers]！"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给自己[style.boldSex(口交)]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.name][style.boldSex(口交)]！"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(吮吸着)]自己的[npc.tail]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(吮吸)][npc.namePos]的[npc.tail]！"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(吮吸着)]自己的触手！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(吮吸)][npc.namePos]的触手！"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.herself]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(亲吻着)][npc.name]！"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(吮吸着)]自己的[npc.clit]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(吮吸)][npc.namePos]的[npc.clit]！"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(舔弄着)][npc.her]自己的[npc.feet]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(舔着)][npc.namePos]的[npc.feet]！"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(舔弄着)][npc.her]自己的[npc.armpits]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(舔着)][npc.namePos]的[npc.armpits]！"));
								}
								break;
							case ANUS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给自己[style.boldSex(吻肛)]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.name][style.boldSex(舔肛)]！"));
								}
								break;
							case ASS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(舔弄着)][npc.her]自己的[npc.ass]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(舔舐)][npc.namePos]的[npc.ass]！"));
								}
								break;
							case BREAST:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.breasts]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(亲吻着)][npc.namePos]的[npc.breasts]！"));
								}
								break;
							case NIPPLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(吮吸着)]自己的[npc.nipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(吮吸着)][npc.namePos]的[npc.nipples]！"));
								}
								break;
							case BREAST_CROTCH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.crotchBoobs]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(亲吻着)][npc.namePos]的[npc.crotchBoobs]！"));
								}
								break;
							case NIPPLE_CROTCH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(吮吸着)]自己的[npc.crotchNipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(吮吸着)][npc.namePos]的[npc.crotchNipples]！"));
								}
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.herself]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(亲吻着)][npc.name]！"));
								}
								break;
							case THIGHS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.legs]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在[style.boldSex(亲吻着)][npc.namePos]的[npc.legs]！"));
								}
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给自己[style.boldSex(口交)]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.name][style.boldSex(口交)]！"));
								}
								break;
							case URETHRA_VAGINA:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(舔着[npc.herself]的阴部)] ！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.name][style.boldSex(舔阴)]！"));
								}
								break;
							case VAGINA:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(舔着[npc.herself]的阴部)] ！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.name][style.boldSex(舔阴)]！"));
								}
								break;
							case SPINNERET:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(口交着)][npc.her]自己的丝囊！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.namePos]的丝囊[style.boldSex(口交)]！"));
								}
								break;
							case TUSSY:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(oral)] on [npc.her] own tussy!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex(oral)] on [npc.namePos] tussy!"));
								}
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her]的嘴"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.MOUTH);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.MOUTH, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMouth());
		}
	};
	
	public static AbstractStatusEffect BREAST_STATUS = new AbstractStatusEffect(98,
			"乳房状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.hasBreasts()) {
				return "乳房状态";
			} else {
				return "胸部状态";
			}
		}
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.BREAST);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.BREAST);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.BREAST);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.BREAST;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(抚弄着)][npc.her]自己的[npc.breasts]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(抚弄着)][npc2.namePos]的[npc2.breasts]！"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给[npc.herself][style.boldSex("+(target.isBreastFuckablePaizuri()?"乳交":"贫乳乳交")+")]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.name][style.boldSex("+(target.isBreastFuckablePaizuri()?"乳交":"贫乳乳交")+")]！"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给[npc.herself][style.boldSex(尾巴"+(target.isBreastFuckablePaizuri()?"乳交":"贫乳乳交")+")]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.name][style.boldSex(尾巴"+(target.isBreastFuckablePaizuri()?"乳交":"贫乳乳交")+")]！"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给[npc.herself][style.boldSex(触手"+(target.isBreastFuckablePaizuri()?"乳交":"贫乳乳交")+")]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.name][style.boldSex(触手"+(target.isBreastFuckablePaizuri()?"乳交":"贫乳乳交")+")]！"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.breasts]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.breasts]！"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给自己的[npc.clit][style.boldSex("+(target.isBreastFuckablePaizuri()?"乳交":"贫乳乳交")+")]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.namePos]的[npc.clit][style.boldSex("+(target.isBreastFuckablePaizuri()?"乳交":"贫乳乳交")+")]！"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在用[npc.feet][style.boldSex(抚弄着)][npc.her]自己的[npc.breasts]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在用[npc.her]的[npc.feet][style.boldSex(抚弄)][npc2.namePos]的[npc2.breasts]！"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.breasts]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.breasts]！"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给[npc.herself][style.boldSex("+(target.isBreastFuckablePaizuri()?"乳交":"贫乳乳交")+")]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.Name]进行[style.boldSex("+(target.isBreastFuckablePaizuri()?"乳交":"贫乳乳交")+")]！"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
							case TUSSY:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her]的[npc.breasts]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			SexAreaOrifice orifice = SexAreaOrifice.BREAST;
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							partner,
//							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
//							target,
//							orifice)));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& (!target.isFeral() || target.getFeralAttributes().isBreastsPresent())
					&& Main.sex.getAllParticipants(true).contains(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.BREAST, owner.hasBreasts()?SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreasts():SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsFlat());
		}
	};
	
	public static AbstractStatusEffect NIPPLE_STATUS = new AbstractStatusEffect(97,
			"乳头状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.NIPPLE);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.NIPPLE);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.NIPPLE);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.NIPPLE;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(揉捏)]着自己的[npc.nipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(揉捏着)][npc2.namePos]的[npc2.nipples]！"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的[npc.nipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc2.namePos]的[npc2.nipples]！"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)]自己的[npc.nipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)][npc2.namePos]的[npc2.nipples]！"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)]自己的[npc.nipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)][npc2.namePos]的[npc2.nipples]！"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.nipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.nipples]！"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc.her]自己的[npc.nipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc2.namePos]的[npc2.nipples]！"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]将[style.boldSex([npc.toes]插入)][npc.her]自己的[npc.nipples]里！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]将[style.boldSex([npc.toes]插入)][npc2.namePos]的[npc2.nipples]里！"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.nipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.nipples]！"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的[npc.nipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc2.namePos]的[npc2.nipples]！"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
							case TUSSY:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her]的[npc.nipples]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.NIPPLE);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
//					&& target.isBreastFuckableNipplePenetration()
//					&& Main.getProperties().hasValue(PropertyValue.nipplePenContent)
					;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.NIPPLE, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple());
		}
	};
	
	public static AbstractStatusEffect BREAST_CROTCH_STATUS = new AbstractStatusEffect(98,
			"胯乳状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "腹乳状态";
			} else {
				return "胯乳状态";
			}
		}
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.BREAST_CROTCH);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.BREAST_CROTCH);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.BREAST_CROTCH);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.BREAST_CROTCH;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(抚弄着)][npc.her]自己的[npc.crotchBoobs]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(抚弄着)][npc2.namePos]的[npc2.crotchBoobs]！"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给[npc.herself][style.boldSex([npc.crotchBoob]"+(entry.getKey().hasBreasts()?"乳交":"贫乳乳交")+")]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.name][style.boldSex([npc2.crotchBoob]"+(entry.getKey().hasBreasts()?"乳交":"贫乳乳交")+")]！"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给[npc.herself]的[npc.tail][style.boldSex([npc.crotchBoob]"+(entry.getKey().hasBreasts()?"乳交":"贫乳乳交")+")]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在为[npc.namePos]的[npc.tail]进行[style.boldSex([npc2.crotchBoob]"+(entry.getKey().hasBreasts()?"乳交":"贫乳乳交")+")]！"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给[npc.herself]的[npc.tentacle][style.boldSex([npc.crotchBoob]"+(entry.getKey().hasBreasts()?"乳交":"贫乳乳交")+")]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.namePos]的[npc.tentacle][style.boldSex([npc2.crotchBoob]"+(entry.getKey().hasBreasts()?"乳交":"贫乳乳交")+")]！"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.crotchBoobs]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.crotchBoobs]！"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给[npc.herself]的[npc.clit][style.boldSex([npc.crotchBoob]"+(entry.getKey().hasBreasts()?"乳交":"贫乳乳交")+")]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.namePos]的[npc.clit][style.boldSex([npc.crotchBoob]"+(entry.getKey().hasBreasts()?"乳交":"贫乳乳交")+")]！"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在用[npc.feet][style.boldSex(抚弄着)][npc.her]自己的[npc.crotchBoobs]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在用[npc.feet][style.boldSex(抚弄着)][npc2.namePos]的[npc2.crotchBoobs]！"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.crotchBoobs]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.crotchBoobs]！"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给[npc.herself][style.boldSex([npc.crotchBoob]"+(entry.getKey().hasBreasts()?"乳交":"贫乳乳交")+")]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull]正在给[npc.name][style.boldSex([npc.crotchBoob]"+(entry.getKey().hasBreasts()?"乳交":"贫乳乳交")+")]！"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
							case TUSSY:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her]的[npc.crotchBoobs]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			SexAreaOrifice orifice = SexAreaOrifice.BREAST_CROTCH;
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							partner,
//							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
//							target,
//							orifice)));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasBreastsCrotch();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.BREAST_CROTCH,
					owner.getBreastCrotchShape()==BreastShape.UDDERS
						?SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUdders()
						:SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsCrotch());
		}
	};
	
	public static AbstractStatusEffect NIPPLE_CROTCH_STATUS = new AbstractStatusEffect(97,
			"乳头状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "腹乳乳头状态";
			} else {
				return "胯乳乳头状态";
			}
		}
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.NIPPLE_CROTCH);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.NIPPLE_CROTCH;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(揉捏)]着自己的[npc.crotchNipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(揉捏着)][npc2.namePos]的[npc2.crotchNipples]！"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的[npc.crotchNipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc2.namePos]的[npc2.crotchNipples]！"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)]自己的[npc.crotchNipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)][npc2.namePos]的[npc2.crotchNipples]！"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)]自己的[npc.crotchNipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)][npc2.namePos]的[npc2.crotchNipples]！"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.crotchNipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.crotchNipples]！"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc.her]自己的[npc.crotchNipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc2.namePos]的[npc2.crotchNipples]！"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]将[style.boldSex([npc.toes]插入)][npc.her]的[npc.crotchNipples]里！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]将[style.boldSex([npc.toes]插入)][npc2.namePos]的[npc2.crotchNipples]里！"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.crotchNipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.crotchNipples]！"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的[npc.crotchNipples]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc2.namePos]的[npc2.crotchNipples]！"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
							case TUSSY:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her]的[npc.crotchNipples]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasBreastsCrotch()
					&& target.isBreastCrotchFuckableNipplePenetration()
					&& Main.getProperties().hasValue(PropertyValue.nipplePenContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.NIPPLE_CROTCH, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple());
		}
	};
	
	public static AbstractStatusEffect URETHRA_PENIS_STATUS = new AbstractStatusEffect(97,
			"阴茎尿道状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.URETHRA_PENIS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.URETHRA_PENIS);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.URETHRA_PENIS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.URETHRA_PENIS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(手淫)]着自己的[npc.urethraPenis]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(指交着)][npc2.namePos]的[npc2.urethraPenis]！"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的[npc.urethraPenis]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc2.namePos]的[npc2.urethraPenis]！"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)]自己的[npc.urethraPenis]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)][npc2.namePos]的[npc2.urethraPenis]！"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)]自己的[npc.urethraPenis]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)][npc2.namePos]的[npc2.urethraPenis]！"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.urethraPenis]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.urethraPenis]！"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc.her]自己的[npc.urethraPenis]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc2.namePos]的[npc2.urethraPenis]！"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]将[style.boldSex([npc.toes]插入)][npc.her]的[npc.urethraPenis]里！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]将[style.boldSex([npc.toes]插入)][npc2.namePos]的[npc2.urethraPenis]里！"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.urethraPenis]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.urethraPenis]！"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的[npc.urethraPenis]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc2.namePos]的[npc2.urethraPenis]！"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
							case TUSSY:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her][npc.urethraPenis]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.URETHRA_PENIS);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasPenisIgnoreDildo()
					&& target.isUrethraFuckable()
					&& Main.getProperties().hasValue(PropertyValue.urethralContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.URETHRA_PENIS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraPenis());
		}
	};
	
	public static AbstractStatusEffect URETHRA_VAGINA_STATUS = new AbstractStatusEffect(97,
			"阴道尿道状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.URETHRA_VAGINA);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.URETHRA_VAGINA);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.URETHRA_VAGINA);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.URETHRA_VAGINA;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(指交)]着自己的[npc.urethraVagina]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(指交着)][npc2.namePos]的[npc2.urethraVagina]！"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的[npc.urethraVagina]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc2.namePos]的[npc2.urethraVagina]！"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)]自己的[npc.urethraVagina]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)][npc2.namePos]的[npc2.urethraVagina]！"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)]自己的[npc.urethraVagina]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)][npc2.namePos]的[npc2.urethraVagina]！"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.urethraVagina]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.urethraVagina]！"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc.her]自己的[npc.urethraVagina]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc2.namePos]的[npc2.urethraVagina]！"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]将[style.boldSex([npc.toes]插入)][npc.her]的[npc.urethraVagina]里！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]将[style.boldSex([npc.toes]插入)][npc2.namePos]的[npc2.urethraVagina]里！"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.urethraVagina]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.urethraVagina]！"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的[npc.urethraVagina]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc2.namePos]的[npc2.urethraVagina]！"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
							case TUSSY:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her]的[npc.urethraVagina]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.URETHRA_VAGINA);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasVagina()
					&& target.isVaginaUrethraFuckable()
					&& Main.getProperties().hasValue(PropertyValue.urethralContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.URETHRA_VAGINA, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraVagina());
		}
	};
	
	public static AbstractStatusEffect VAGINA_STATUS = new AbstractStatusEffect(95,
			"小穴状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.VAGINA);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.VAGINA);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.VAGINA);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.VAGINA;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			
			boolean descriptionAdded = false;
			for(SexAreaPenetration pen : SexAreaPenetration.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, pen)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(pen) {
						case FINGER:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(指交)]着自己的[npc.pussy]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(指交着)][npc.namePos]的[npc.pussy]！"));
							}
							break;
						case PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的[npc.pussy]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(操)][npc.namePos]的[npc.pussy]！"));
							}
							break;
						case TAIL:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)]自己的[npc.pussy]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(用尾巴操着)][npc.namePos]的[npc.pussy]！"));
							}
							break;
						case TENTACLE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给自己的[npc.pussy][style.boldSex(触手交)]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(触手交)][npc.namePos]的[npc.pussy]！"));
							}
							break;
						case TONGUE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给[npc.her]自己的[npc.pussy][style.boldSex(舔阴)]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"在[npc.Name]身上表演[style.boldSex(舔阴)]！"));
							}
							break;
						case CLIT:
							if(selfAction) {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给自己的[npc.pussy][style.boldSex(阴蒂交)]！"));
								} else {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴户磨蹭着)][npc.her]自己的[npc.pussy]！"));
								}
							} else {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
											+"[style.boldSex(用阴蒂操着)][npc.namePos]的[npc.pussy]！"));
								} else {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
											+"在跟[npc2.name][style.boldSex(磨镜)]！"));
								}
							}
							break;
						case FOOT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]将[style.boldSex([npc.toes]插入)][npc.her]的[npc.pussy]里！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
										+(names.size()==1?UtilText.parse(main, "[style.boldSex(将[npc.toes]插入)]"):"[style.boldSex(将多根脚趾插入)]")
										+"[npc.namePos]的[npc.pussy]里！"));
							}
							break;
					}
				}
			}

			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, orifice)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(orifice) {
						case ARMPITS:
						case ANUS:
						case ASS:
						case BREAST:
						case NIPPLE:
						case BREAST_CROTCH:
						case NIPPLE_CROTCH:
							break;
						case MOUTH:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(舔着[npc.herself]的阴部)] ！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"在给[npc2.name][style.boldSex(舔阴)]！"));
							}
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的[npc.pussy]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(操)][npc.namePos]的[npc.pussy]！"));
							}
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴户磨蹭着)][npc.her]自己的[npc.pussy]！")); //???
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"在跟[npc2.name][style.boldSex(磨镜)]！"));
							}
							break;
						case TUSSY:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] own [npc.tussy]!")); //???
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+" [npc.her] [npc.tussy] to [style.boldSex(trib)] with [npc2.name]!"));
							}
							break;
						case SPINNERET:
							break;
					}
				}
			}
			
			if(!descriptionAdded) {
				descriptionSB.append("[style.boldDisabled(没有正在进行的动作。)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her]的小穴"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.VAGINA);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasVagina();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.VAGINA, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina());
		}
	};
	
	public static AbstractStatusEffect SPINNERET_STATUS = new AbstractStatusEffect(95,
			"丝囊状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.SPINNERET);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.SPINNERET);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.SPINNERET);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.SPINNERET;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			
			boolean descriptionAdded = false;
			for(SexAreaPenetration pen : SexAreaPenetration.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, pen)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(pen) {
						case FINGER:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]在给[npc.her]自己的丝囊[style.boldSex(指交)]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(指交)][npc.namePos]的丝囊！"));
							}
							break;
						case PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的丝囊！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(操)][npc.namePos]的丝囊！"));
							}
							break;
						case TAIL:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)]自己的丝囊！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(尾交)][npc.namePos]的丝囊！"));
							}
							break;
						case TENTACLE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)]自己的丝囊！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(触手交)][npc.namePos]的丝囊！"));
							}
							break;
						case TONGUE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给自己的丝囊[style.boldSex(口交)]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"在给[npc.namePos]的丝囊[style.boldSex(口交)]！"));
							}
							break;
						case CLIT:
							if(selfAction) {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc.her]自己的丝囊！"));
								} else {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴户磨蹭着)][npc.her]自己的丝囊！"));
								}
							} else {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
											+"[style.boldSex(用阴蒂操着)][npc.namePos]的丝囊！"));
								} else {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
											+"在跟[npc2.namePos]的丝囊[style.boldSex(磨镜)]！"));
								}
							}
							break;
						case FOOT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]将[style.boldSex([npc.toes]插入)][npc.her]自己的丝囊里！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
										+(names.size()==1?UtilText.parse(main, "[style.boldSex(将[npc.toes]插入)]"):"[style.boldSex(将多根脚趾插入)]")
										+"[npc.namePos]的丝囊里！"));
							}
							break;
					}
				}
			}

			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, orifice)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(orifice) {
						case ARMPITS:
						case ANUS:
						case ASS:
						case BREAST:
						case NIPPLE:
						case BREAST_CROTCH:
						case NIPPLE_CROTCH:
							break;
						case MOUTH:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在给[npc.her]自己的丝囊[style.boldSex(口交)]！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"在给[npc2.namePos]的丝囊[style.boldSex(口交)]！"));
							}
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的丝囊！"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+"正"
										+"[style.boldSex(操)][npc.namePos]的丝囊！"));
							}
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴户磨蹭着)][npc.her]自己的丝囊！")); //???
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+"在跟[npc2.namePos]的丝囊[style.boldSex(磨镜)]！"));
							}
							break;
						case SPINNERET:
							break;
                        case TUSSY:
							break;
					}
				}
			}
			
			if(!descriptionAdded) {
				descriptionSB.append("[style.boldDisabled(没有正在进行的动作。)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her]的丝囊"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.SPINNERET);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasSpinneret();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.SPINNERET, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaSpinneret());
		}
	};
	
	public static AbstractStatusEffect THIGH_STATUS = new AbstractStatusEffect(95,
			"股间状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.THIGHS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.THIGHS);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.THIGHS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.THIGHS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(抚弄着)][npc.her]的[npc.legs]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(抚弄着)][npc2.namePos]的[npc2.legs]！"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的大腿！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc2.namePos]的大腿！"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)]自己的大腿！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)][npc2.namePos]的大腿！"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)]自己的大腿！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)][npc2.namePos]的大腿！"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.legs]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.legs]！"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc.her]自己的大腿！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc2.namePos]的大腿！"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在将[style.boldSex([npc.feet]插入)][npc.her]自己的大腿间！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在将[style.boldSex([npc.feet]插入)][npc2.namePos]的大腿间！"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.legs]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.legs]！"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的大腿！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc2.namePos]的大腿！"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
                            case TUSSY:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her]的双股"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			SexAreaOrifice orifice = SexAreaOrifice.THIGHS;
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							partner,
//							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
//							target,
//							orifice)));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.THIGHS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaThighs());
		}
	};
	
	public static AbstractStatusEffect ARMPIT_STATUS = new AbstractStatusEffect(95,
			"腋窝状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.ARMPITS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.ARMPITS);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.ARMPITS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.ARMPITS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(抚摸)][npc.her]自己的[npc.armpits]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(抚摸)][npc2.namePos]的[npc2.armpits]！"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的腋窝！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc2.namePos]的腋窝！"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)]自己的腋窝！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用尾巴操着)][npc2.namePos]的腋窝！"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)]自己的腋窝！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用触手操着)][npc2.namePos]的腋窝！"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.armpits]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.armpits]！"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc.her]自己的腋窝！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(用阴蒂操着)][npc2.namePos]的腋窝！"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(将脚插入)][npc.her]自己的腋窝中！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在将[style.boldSex([npc.feet]插入)][npc2.namePos]的腋窝间！"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc.her]的[npc.armpits]！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(亲吻着)][npc2.namePos]的[npc2.armpits]！"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc.her]自己的腋窝！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(操着)][npc2.namePos]的腋窝！"));
								}
								break;
							case URETHRA_VAGINA:
							case VAGINA:
							case SPINNERET:
							case TUSSY:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her]的腋窝"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			SexAreaOrifice orifice = SexAreaOrifice.ARMPITS;
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							partner,
//							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
//							target,
//							orifice)));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& Main.game.isArmpitContentEnabled();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.ARMPITS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaArmpits());
		}
	};

	public static AbstractStatusEffect HAND_STATUS = new AbstractStatusEffect(95,
			"手部状态",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getPenetrationArousalPerTurn(target, SexAreaPenetration.FINGER);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return 0;
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getPenetrationModifiersAsStringList(target, SexAreaPenetration.FINGER);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaPenetration type = SexAreaPenetration.FINGER;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case CLIT:
								break;
							case PENIS:
								break;
							case TAIL:
								break;
							case TENTACLE:
								break;
							case TONGUE:
								break;
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull]正在[style.boldSex(牵着)][npc.herself]的手！"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull]正在[style.boldSex(牵着)][npc2.name]的手！"));
								}
								break;
							case FOOT:
								break;
						}
						
					}
				}
			}
			
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(没有正在进行的动作……)]");
			}

			appendPenetrationAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos]的[npc.hands]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.FINGER).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.FINGER).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							target,
//							SexAreaPenetration.FINGER,
//							partner,
//							Main.sex.getOngoingActionsMap(target).get(SexAreaPenetration.FINGER).get(partner).iterator().next())));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex() || !Main.sex.getAllParticipants(true).contains(target) || Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.FINGER).isEmpty()) {
				return false;
			}
			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.FINGER).get(0);
			return Main.sex.getOngoingSexAreas(target, SexAreaPenetration.FINGER).get(partner).contains(SexAreaPenetration.FINGER);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaPenetration.FINGER, SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeFinger(), Util.newArrayListOfValues(SexAreaPenetration.FINGER));
		}
	};
	
	
	public static List<AbstractStatusEffect> allStatusEffects;
	public static List<AbstractStatusEffect> allStatusEffectsRequiringApplicationCheck;
	public static List<AbstractStatusEffect> allStatusEffectsRequiringApplicationCheckNonCombat;
	public static List<AbstractStatusEffect> preGameNonCombatStatusEffectsAsDiscussedInDevelopersChannelAfterFindingTheBugWhichWasCausingManaBurnStacksToThrowErrorsBecauseCombatStatusEffectsWereAddedBeforeTheGameWasStarted;
	
	public static Map<AbstractStatusEffect, String> statusEffectToIdMap = new HashMap<>();
	public static Map<String, AbstractStatusEffect> idToStatusEffectMap = new HashMap<>();
	
	/**
	 * @param id Will be in the format of: 'innoxia_maid'.
	 */
	public static AbstractStatusEffect getStatusEffectFromId(String id) {
		if(id.equals("innoxia_massaged")) {
			return CLEANED_MASSAGED;
		} else if(id.equals("BATH_BOOSTED") || id.equals("innoxia_cleaned_spa")) {
			return CLEANED_SPA;
		} else if(id.equals("BATH") || id.equals("innoxia_cleaned_bath")) {
			return CLEANED_BATH;
		} else if(id.equals("SHOWER") || id.equals("innoxia_cleaned_shower")) {
			return CLEANED_SHOWER;
		}
		
		if(id.equals("innoxia_item_broodmother_pill")) {
			return BROODMOTHER_PILL;
		}
		
		id = Util.getClosestStringMatch(id, idToStatusEffectMap.keySet());
		
		return idToStatusEffectMap.get(id);
	}
	
	public static String getIdFromStatusEffect(AbstractStatusEffect perk) {
		return statusEffectToIdMap.get(perk);
	}

	static {
		allStatusEffects = new ArrayList<>();
		allStatusEffectsRequiringApplicationCheck = new ArrayList<>();
		allStatusEffectsRequiringApplicationCheckNonCombat = new ArrayList<>();
		// Modded status effects:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/statusEffects");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractStatusEffect statusEffect = new AbstractStatusEffect(innerEntry.getValue(), entry.getKey(), true) {};
					allStatusEffects.add(statusEffect);
					statusEffectToIdMap.put(statusEffect, innerEntry.getKey());
					idToStatusEffectMap.put(innerEntry.getKey(), statusEffect);
//					System.out.println("modded SE: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading modded status effect failed at 'StatusEffect'. File path: "+innerEntry.getValue().getAbsolutePath());
										ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res status effects:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/statusEffects");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractStatusEffect statusEffect = new AbstractStatusEffect(innerEntry.getValue(), entry.getKey(), false) {};
					allStatusEffects.add(statusEffect);
					statusEffectToIdMap.put(statusEffect, innerEntry.getKey());
					idToStatusEffectMap.put(innerEntry.getKey(), statusEffect);
//					System.out.println("res SE: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading status effect failed at 'StatusEffect'. File path: "+innerEntry.getValue().getAbsolutePath());
										ex.printStackTrace(System.err);
				}
			}
		}
		
		// Hard-coded status effects (all those up above):
		
		Field[] fields = StatusEffect.class.getFields();
		
		for(Field f : fields){
			if (AbstractStatusEffect.class.isAssignableFrom(f.getType())) {
				
				AbstractStatusEffect statusEffect;
				
				try {
					statusEffect = ((AbstractStatusEffect) f.get(null));

					statusEffectToIdMap.put(statusEffect, f.getName());
					idToStatusEffectMap.put(f.getName(), statusEffect);
					allStatusEffects.add(statusEffect);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		fields = LexiStatusEffect.class.getFields();

		for(Field f : fields){
			if (AbstractStatusEffect.class.isAssignableFrom(f.getType())) {

				AbstractStatusEffect statusEffect;

				try {
					statusEffect = ((AbstractStatusEffect) f.get(null));

					statusEffectToIdMap.put(statusEffect, f.getName());
					idToStatusEffectMap.put(f.getName(), statusEffect);
					allStatusEffects.add(statusEffect);

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static List<AbstractStatusEffect> getAllStatusEffects() {
		return allStatusEffects;
	}
	
	public static List<AbstractStatusEffect> getAllStatusEffectsRequiringApplicationCheck() {
		if(!Main.game.isStarted()) {
			if(preGameNonCombatStatusEffectsAsDiscussedInDevelopersChannelAfterFindingTheBugWhichWasCausingManaBurnStacksToThrowErrorsBecauseCombatStatusEffectsWereAddedBeforeTheGameWasStarted==null) {
				preGameNonCombatStatusEffectsAsDiscussedInDevelopersChannelAfterFindingTheBugWhichWasCausingManaBurnStacksToThrowErrorsBecauseCombatStatusEffectsWereAddedBeforeTheGameWasStarted = new ArrayList<>(allStatusEffects);
				preGameNonCombatStatusEffectsAsDiscussedInDevelopersChannelAfterFindingTheBugWhichWasCausingManaBurnStacksToThrowErrorsBecauseCombatStatusEffectsWereAddedBeforeTheGameWasStarted.removeIf(se->se.isCombatEffect());
			}
			return preGameNonCombatStatusEffectsAsDiscussedInDevelopersChannelAfterFindingTheBugWhichWasCausingManaBurnStacksToThrowErrorsBecauseCombatStatusEffectsWereAddedBeforeTheGameWasStarted;
		}
		if(allStatusEffectsRequiringApplicationCheck.isEmpty()) { // Initialise on first call
			for(AbstractStatusEffect se : allStatusEffects) {
				se.isConditionsMet(Main.game.getPlayer()); // To initialise the variable
				if(se.isRequiresApplicationCheck()) {
					allStatusEffectsRequiringApplicationCheck.add(se);
					if(!se.isCombatEffect()) {
						allStatusEffectsRequiringApplicationCheckNonCombat.add(se);
					}
				}
			}
//			System.out.println("ASE/SE: "+allStatusEffectsRequiringApplicationCheck.size()+"/"+allStatusEffects.size());
		}
		if(Main.game.isInCombat()) {
			return allStatusEffectsRequiringApplicationCheck;
		} else {
			return allStatusEffectsRequiringApplicationCheckNonCombat;
		}
	}
}
