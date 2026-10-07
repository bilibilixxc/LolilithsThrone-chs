package com.lilithsthrone.game.character.race;

import com.lilithsthrone.LolificationProject.Lexi.character.race.LexiSubspecies;

import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidGirlCum;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.body.LegConfigurationAffinity;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.Map.Entry;

/**
 * @since 0.1.91
 * @version 0.4.0
 * @author tukaima, Innoxia
 */
public class Subspecies {
	
	// ---- TODO planned races ---- //
	
	//LIZARD_MORPH(Race.LIZARD_MORPH.getName(), Race.LIZARD_MORPH, RacialBody.LIZARD_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.LIZARD_MORPH.getName()),
	
	// AQUATIC:
	//TIGER_SHARK(Race.TIGER_SHARK.getName(), Race.TIGER_SHARK, RacialBody.TIGER_SHARK, SubspeciesPreference.FIVE_ABUNDANT,
	//		"An extremely aggressive variety of "+Race.SHARK_MORPH.getName()),
	
	// INSECTS:
	//BEE_MORPH(Race.BEE_MORPH.getName(), Race.BEE_MORPH, RacialBody.BEE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.BEE_MORPH.getName()),
	//ROYAL_BEE(Race.ROYAL_BEE.getName(), Race.BEE_MORPH, RacialBody.ROYAL_BEE, SubspeciesPreference.ZERO_NONE,
	//		"A bipedal "+Race.BEE_MORPH.getName()+" at the top of the bee-morph hierarchy"),
	//WASP_MORPH(Race.WASP_MORPH.getName(), Race.WASP_MORPH, RacialBody.WASP_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.WASP_MORPH.getName()),
	
//	FOX_TAILED("statusEffects/race/raceFoxMorph",
//			"pipefox",
//			"pipefoxes",
//			"pipefox-boy",
//			"pipefox-girl",
//			"pipefox-boys",
//			"pipefox-girls",
//			Race.FOX_MORPH,
//			PresetColour.RACE_FOX_MORPH,
//			SubspeciesPreference.FOUR_ABUNDANT,
//			"A fox-morph with a serpentine lower body, devoid of legs.",
//			Util.newHashMapOfValues(WorldType.DOMINION)) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			//apply fox coloring
//		}
//	};
	
//	FOX_TAUR("statusEffects/race/raceFoxMorph",
//			"yegan",
//			"yegans",
//			"yegan-boy",
//			"yegan-girl",
//			"yegan-boys",
//			"yegan-girls",
//			Race.FOX_MORPH,
//			PresetColour.RACE_FOX_MORPH,
//			SubspeciesPreference.FOUR_ABUNDANT,
//			"A fox-morph a bestial lower body that walks on four legs.",
//			Util.newHashMapOfValues(WorldType.DOMINION)) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			//apply fox coloring
//		}
//	};
	
	// ---- ---- //
	
	// HUMAN:
	public static AbstractSubspecies HUMAN = new AbstractSubspecies(true,
			4000,
			"innoxia_race_human_vanilla_water",
			"innoxia_race_human_bread_roll",
			"statusEffects/race/raceHuman",
			"statusEffects/race/raceBackground",
			"人类",
			"人类",
			"男人",
			"女人",
			"男人",
			"女人",
			null,
			Nocturnality.DIURNAL,
			"人类对于快感和奥术的抵抗能力远高于其他种族。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null,
			"心系人类",
			"心系人类",
			"HUMAN_BASIC",
			"HUMAN_ADVANCED",
			Race.HUMAN,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_HUMAN,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一个普通的人类。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HUMAN) {
				return 100;
			}
			return 0;
		}
	};

	// ANGEL:
	public static AbstractSubspecies ANGEL = new AbstractSubspecies(true,
			80000,
			"innoxia_race_angel_angels_tears",
			null,
			"statusEffects/race/raceAngel",
			"statusEffects/race/raceBackground",
			"天使",
			"天使",
			"天使",
			"天使",
			"天使",
			"天使",
			null,
			Nocturnality.DIURNAL,
			"作为一名天使，[npc.nameIsFull]对奥术造成的欲望效应具有高抵抗力，且善于与恶魔作战。"
					+ "[npc.her]拥有保护人类的本能，然而，这使[npc.her]难以抵御来自人类的伤害……",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, -100f),
					new Value<>(Attribute.RESISTANCE_LUST, 50f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.DEMON), 50f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HUMAN), -50f)),
			null,
			"守护者",
			"守护者",
			"ANGEL_BASIC",
			"ANGEL_ADVANCED",
			Race.ANGEL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 0),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 0),
					new Value<>(PerkCategory.ARCANE, 2)),
			PresetColour.RACE_ANGEL,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一个普通的天使。",
			null,
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 10;
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"堕天使",
					"堕天使",
					"堕天使",
					"堕天使",
					"堕天使",
					"堕天使"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "堕天使", false, false),
					applyNonBipedNameChange(body, "堕天使", false, true),
					applyNonBipedNameChange(body, "堕天使", false, false),
					applyNonBipedNameChange(body, "堕天使", true, false),
					applyNonBipedNameChange(body, "堕天使", false, true),
					applyNonBipedNameChange(body, "堕天使", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ANGEL) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	// DEMON:
	public static AbstractSubspecies ELDER_LILIN = new AbstractSubspecies(false,
			1000000000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElderLilin",
			"statusEffects/race/raceBackground",
			"莉琳长老",
			"莉琳长老",
			"莉琳长老",
			"莉琳长老",
			"莉琳长老",
			"莉琳长老",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.NameIsFull]是七位莉琳长老之一，也是现存者中最有力量的一位，[npc.She]可以把[npc.her]的身体转化成[npc.she]想要的任何形式，还能完全掌控奥术。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 1000f),
					new Value<>(Attribute.MAJOR_ARCANE, 1000f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 1000f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 1000f),
					new Value<>(Attribute.MANA_MAXIMUM, 1000f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(无限制)]<b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>自我转化</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>免疫种族转化</b>"),
			"莉莉丝的子嗣",
			"莉莉丝的子嗣",
			"ELDER_LILIN_BASIC",
			"ELDER_LILIN_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_LILIN,
			SubspeciesPreference.ONE_LOW,
			"七位莉琳长老之一。",
			null,
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 10_000;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setSubspeciesOverride(ELDER_LILIN);
			return "";
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			// As Elder Lilin will always have a SubspeciesOverride, there is no need to set any conditional weighting for it.
			return 0;
		}
		@Override
		public AbstractAttribute getDamageMultiplier() {
			return Attribute.DAMAGE_ELDER_LILIN;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	public static AbstractSubspecies LILIN = new AbstractSubspecies(false,
			500000000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceLilin",
			"statusEffects/race/raceBackground",
			"莉琳",
			"莉琳",
			"莉琳",
			"莉琳",
			"莉琳",
			"莉琳",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.Name]是位莉琳，远比寻常恶魔更为强大。[npc.She]可以将躯体随心所欲地转化成[npc.she]期望的姿态，且拥有着庞大的奥术之力。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 500f),
					new Value<>(Attribute.MAJOR_ARCANE, 500f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 500f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 500f),
					new Value<>(Attribute.MANA_MAXIMUM, 500f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(无限制)]<b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>自我转化</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>免疫种族转化</b>"),
			"莉莉丝的血脉",
			"莉莉丝的血脉",
			"LILIN_BASIC",
			"LILIN_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_LILIN,
			SubspeciesPreference.ONE_LOW,
			"一位莉琳。",
			null,
			null, null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 5_000;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setSubspeciesOverride(LILIN);
			return "";
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			// As Elder Lilin will always have a SubspeciesOverride, there is no need to set any conditional weighting for it.
			return 0;
		}
		@Override
		public AbstractAttribute getDamageMultiplier() {
			return Attribute.DAMAGE_LILIN;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	public static AbstractSubspecies DEMON = new AbstractSubspecies(true,
			120000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceDemon",
			"statusEffects/race/raceBackground",
			"恶魔",
			"恶魔",
			"淫梦魔",
			"魅魔",
			"淫梦魔",
			"魅魔",
			null,
			Nocturnality.CATHEMERAL,
			"由于恶魔能轻松地驾驭奥术之力，[npc.namePos]的施法能力可怕到无人能敌！",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, 30f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 100f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 25f),
					new Value<>(Attribute.DAMAGE_LUST, 25f),
					new Value<>(Attribute.DAMAGE_SPELLS, 75f)),
			Util.newArrayListOfValues(
					"[style.boldDemon(恶魔)]<b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>自体转变</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>免疫种族转化</b>"),
			"恶魔族的起源",
			"恶魔族的起源",
			"DEMON_BASIC",
			"DEMON_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.RACE_DEMON,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一个普通的恶魔。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB,  SubspeciesSpawnRarity.THREE)), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 20;
		}
		@Override
		public String getFeralName(Body body) {
			if(body!=null) {
				AbstractRace r = body.getLegType().getRace();
				LegConfiguration legConfiguration = body.getLegConfiguration();

				switch(legConfiguration) {
					case BIPEDAL:
						return "恶魔";
					case ARACHNID:
					case CEPHALOPOD:
					case QUADRUPEDAL:
					case TAIL:
					case TAIL_LONG:
					case AVIAN:
					case WINGED_BIPED:
						return r==Race.HUMAN || r==Race.DEMON
								?Race.DEMON.getFeralName(new LegConfigurationAffinity(legConfiguration, getAffinity()), false)
								:"恶魔"+r.getName(body, true);
				}
			}
			return "恶魔";
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			if(Math.random()<0.25f && body.getLeg().getType().equals(LegType.DEMON_COMMON)) {
				sb.append(body.getLeg().setType(target, LegType.DEMON_HOOFED));
			}
			if(Math.random()<0.2f && body.getPenis().getType()!=PenisType.NONE) {
				sb.append(body.getPenis().getTesticle().setTesticleCount(target, 4));
			}
			if(body.getLegConfiguration()==LegConfiguration.BIPEDAL && body.getTailType()==TailType.DEMON_HORSE) {
				sb.append(body.setTailType(target, TailType.DEMON_COMMON));
			}
			return sb.toString();
		}
		
		@Override
		public String getName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[0];
				}
				return super.getName(body);
			}
			return HALF_DEMON.getName(body);
		}
		
		@Override
		public String getNamePlural(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[1];
				}
				return super.getNamePlural(body);
			}
			return HALF_DEMON.getNamePlural(body);
		}

		@Override
		public String getSingularMaleName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[2];
				}
				return super.getSingularMaleName(body);
			}
			return HALF_DEMON.getSingularMaleName(body);
		}

		@Override
		public String getSingularFemaleName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[3];
				}
				return super.getSingularFemaleName(body);
			}
			return HALF_DEMON.getSingularFemaleName(body);
		}

		@Override
		public String getPluralMaleName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[4];
				}
				return super.getPluralMaleName(body);
			}
			return HALF_DEMON.getPluralMaleName(body);
		}

		@Override
		public String getPluralFemaleName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[5];
				}
				return super.getPluralFemaleName(body);
			}
			return HALF_DEMON.getPluralFemaleName(body);
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null || character.getRaceStage()==RaceStage.GREATER) {
				return super.getSVGString(character);
			}
			return HALF_DEMON.getSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null || character.getRaceStage()==RaceStage.GREATER) {
				return super.getSVGStringDesaturated(character);
			}
			return HALF_DEMON.getSVGStringDesaturated(character);
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DEMON) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	public static AbstractSubspecies HALF_DEMON = new AbstractSubspecies(false,
			50000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceDemon",
			"statusEffects/race/raceBackground",
			"半恶魔",
			"半恶魔",
			"半淫梦魔",
			"半魅魔",
			"半淫梦魔",
			"半魅魔",
			null,
			Nocturnality.CATHEMERAL,
			"半恶魔在驾驭奥术之力方面与普通恶魔无异，因而[npc.namePos]释放魔法的能力同样异乎寻常！",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 20f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 50f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 20f),
					new Value<>(Attribute.DAMAGE_LUST, 20f),
					new Value<>(Attribute.DAMAGE_SPELLS, 60f)),
			Util.newArrayListOfValues(
					"<b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>自我转化受限</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>免疫种族转化</b>"),
			"恶魔混血",
			"恶魔混血",
			"HALF_DEMON_BASIC",
			"HALF_DEMON_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 2)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 3),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 2)),
			PresetColour.RACE_HALF_DEMON,
			SubspeciesPreference.FOUR_ABUNDANT,
			"是恶魔与非恶魔配偶交配的结果",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TWO)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 5;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			if(body.getLegConfiguration()==LegConfiguration.BIPEDAL && body.getTailType()==TailType.DEMON_HORSE) {
				sb.append(body.setTailType(target, TailType.DEMON_COMMON));
			}
			return sb.toString();
		}

		@Override
		public String getFeralName(Body body) {
			if(body!=null) {
				if(body.getHalfDemonSubspecies()!=null) {
					return body.getHalfDemonSubspecies().getFeralName(body);
				}
			}
			return DEMON.getFeralName(body);
		}
		
		@Override
		public String getName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[0];
		}
		
		@Override
		public String getNamePlural(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getNamePlural(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[1];
		}

		@Override
		public String getSingularMaleName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getSingularMaleName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[2];
		}

		@Override
		public String getSingularFemaleName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getSingularFemaleName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[3];
		}

		@Override
		public String getPluralMaleName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getPluralMaleName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[4];
		}

		@Override
		public String getPluralFemaleName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getPluralFemaleName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[5];
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null || character.getHalfDemonSubspecies()==null) {
				return Subspecies.HUMAN.getHalfDemonSVGString(character);
			}
			AbstractSubspecies coreSubspecies = character.getHalfDemonSubspecies();
			if(coreSubspecies==Subspecies.HALF_DEMON) {
				coreSubspecies = Subspecies.HUMAN;
			}
			return coreSubspecies.getHalfDemonSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null || character.getHalfDemonSubspecies()==null) {
				return Subspecies.HUMAN.getSVGStringDesaturated(character);
			}
			AbstractSubspecies coreSubspecies = character.getHalfDemonSubspecies();
			if(coreSubspecies==Subspecies.HALF_DEMON) {
				coreSubspecies = Subspecies.HUMAN;
			}
			return coreSubspecies.getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DEMON) {
				if(body.getRaceWeightMap().size()>1) {
					return 200;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	public static final AbstractSubspecies IMP = new AbstractSubspecies(false,
			1000,
			"innoxia_race_imp_impish_brew",
			null,
			"statusEffects/race/raceImp",
			"statusEffects/race/raceBackground",
			"小恶魔",
			"小恶魔",
			"小恶魔",
			"小恶魔",
			"小恶魔",
			"小恶魔",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.NamePos]的小恶魔躯体对性有着深沉且永不满足的渴望。由于小恶魔举止粗鲁，又在社会的最底层，[npc.she]难以诱惑他人……",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 200f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f),
					new Value<>(Attribute.DAMAGE_LUST, -75f)),
			Util.newArrayListOfValues(
					"[style.boldDemon(Demonic)] <b style='color: " + PresetColour.TRANSFORMATION_GENERIC.toWebHexString() + ";'> self-transformations</b>",
					"<b style='color: " + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Immune to race transformations</b>"),
			"无法无天的小恶魔",
			"无法无天的小恶魔'",
			"IMP_BASIC",
			"IMP_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 4),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 8),
					new Value<>(PerkCategory.LUST, 4),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_IMP,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一个普通的小恶魔。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
			/*SubspeciesFlag.HIDDEN_FROM_PREFERENCES*/)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 1;
		}

		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
            int penisMaxLength = Math.max(Math.min((int) (body.getHeightValue() / 4f), 18), 8);
			if(target!=null && target.getBody()!=null) {
				sb.append(target.setHeight(Util.random.nextInt(Height.NEGATIVE_THREE_MINIMUM.getMinimumValue(), ModProps.impHeight)));
			} else {
				body.setHeight(Util.random.nextInt(Height.NEGATIVE_THREE_MINIMUM.getMinimumValue(), ModProps.impHeight));
			}
			sb.append(body.getPenis().setPenisLength(target, Util.random.nextInt(6, penisMaxLength))); // 3-7 inches
			sb.append(body.getWing().setSize(target,  WingSize.THREE_LARGE.getValue()));
			if (body.getLegConfiguration() == LegConfiguration.BIPEDAL && body.getTailType() == TailType.DEMON_HORSE) {
				sb.append(body.setTailType(target, TailType.DEMON_COMMON));
			}

			return sb.toString();
		}

		@Override
		public boolean isShortStature() {
			return true;
		}

		@Override
		public AbstractAttribute getDamageMultiplier() {
			return Attribute.DAMAGE_IMP;
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if (race == Race.DEMON) {
				if (body.getHeightValue() <= ModProps.impHeight) {
					if (Main.getProperties().hasValue(PropertyValue.impPreferences)) {
						if (body.isFeminine())
							return Main.getProperties().getSubspeciesFemininePreferencesMap().get(IMP).getValue();
						return Main.getProperties().getSubspeciesMasculinePreferencesMap().get(IMP).getValue();
					}
					return 150;
				}
			}
			return 0;
		}

		@Override
		public boolean isWinged() {
			return true;
		}

		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	public static final AbstractSubspecies IMP_ALPHA = new AbstractSubspecies(false,
			1000,
			"innoxia_race_imp_impish_brew",
			null,
			"statusEffects/race/raceImpAlpha",
			"statusEffects/race/raceBackground",
			"阿尔法小恶魔",
			"阿尔法小恶魔",
			"阿尔法小恶魔",
			"阿尔法小恶魔",
			"阿尔法小恶魔",
			"阿尔法小恶魔",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.NamePos]的小恶魔躯体对性有着深沉且永不满足的渴望。由于小恶魔举止粗鲁，又在社会的最底层，[npc.she]难以诱惑他人……",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 200f),
					new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.DAMAGE_LUST, -50f)),
			Util.newArrayListOfValues(
					"[style.boldDemon(Demonic)] <b style='color: " + PresetColour.TRANSFORMATION_GENERIC.toWebHexString() + ";'> self-transformations</b>",
					"<b style='color: " + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Immune to race transformations</b>"),
			"无法无天的小恶魔",
			"无法无天的小恶魔'",
			"IMP_BASIC",
			"IMP_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 4),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 8),
					new Value<>(PerkCategory.LUST, 4),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_IMP,
			SubspeciesPreference.ONE_LOW,
			"小恶魔一种更加强力的形态，站立约有[style.sizes(107)]高。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
			/*SubspeciesFlag.HIDDEN_FROM_PREFERENCES*/)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 2;
		}

		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			int minHeight = Height.NEGATIVE_THREE_MINIMUM.getMinimumValue();
            if (ModProps.impHeight < ModProps.alphaImpHeight) {
                minHeight = ModProps.impHeight;
            }
			StringBuilder sb = new StringBuilder();

			if(target!=null && target.getBody()!=null) {
				sb.append(target.setHeight(Util.random.nextInt(minHeight, ModProps.alphaImpHeight)));
			} else {
				body.setHeight(Util.random.nextInt(minHeight, ModProps.alphaImpHeight));
			}
            int penisMaxLength = Math.max(Math.min((int) (body.getHeightValue() / 4f), 24), 10);
            sb.append(body.getPenis().setPenisLength(target, Util.random.nextInt(6, penisMaxLength))); // 3-8 inches
			sb.append(body.getWing().setSize(target, WingSize.THREE_LARGE.getValue()));
			if (body.getLegConfiguration() == LegConfiguration.BIPEDAL && body.getTailType() == TailType.DEMON_HORSE) {
				sb.append(body.setTailType(target, TailType.DEMON_COMMON));
			}

			return sb.toString();
		}

		@Override
		public boolean isShortStature() {
			return true;
		}

		@Override
		public AbstractAttribute getDamageMultiplier() {
			return Attribute.DAMAGE_IMP;
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if (race == Race.DEMON) {
				if (body.getHeightValue() > ModProps.impHeight && body.getHeightValue() <= ModProps.alphaImpHeight) {
					if (Main.getProperties().hasValue(PropertyValue.impPreferences)) {
						if (body.isFeminine())
							return Main.getProperties().getSubspeciesFemininePreferencesMap().get(IMP_ALPHA).getValue();
						return Main.getProperties().getSubspeciesMasculinePreferencesMap().get(IMP_ALPHA).getValue();
					}
					return 150;
				}
			}
			return 0;
		}

		@Override
		public boolean isWinged() {
			return true;
		}

		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	// BOVINES:
	public static AbstractSubspecies COW_MORPH = new AbstractSubspecies(true,
			15000,
			"innoxia_race_cow_bubble_milk",
			"innoxia_race_cow_bubble_cream",
			"statusEffects/race/raceCowMorph",
			"statusEffects/race/raceBackground",
			"牛化形",
			"牛化形",
			"牛男",
			"牛女",
			"牛男",
			"牛女",
			new FeralAttributes(
					"牛",
					"牛",
					"公牛",
					"母牛",
					"公牛",
					"母牛",
					LegConfiguration.QUADRUPEDAL,
					160,
					0,
					1,
					1,
					4, false),
			Nocturnality.DIURNAL,
			"尽管[npc.namePos]的身躯力大无穷，坚韧无比，但头脑却并不是那么灵敏……",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 2f)),
			null,
			"挤牛奶",
			"挤牛奶'",
			"COW_MORPH_BASIC",
			"COW_MORPH_ADVANCED",
			Race.COW_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_COW_MORPH, SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的牛，当下肢为双足时称为“牛化形”，而下肢呈现出牛兽态时称为“半公牛人”或“半母牛人”。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.TWO)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.THREE)), null, null) {
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"米诺陶洛斯",
					"米诺陶洛斯",
					"米诺陶洛斯",
					"米诺陶洛斯",
					"米诺陶洛斯",
					"米诺陶洛斯"};
		}
		@Override
		protected String applyNonBipedNameChange(Body body, String baseName, boolean applyFeminineForm, boolean plural) {
			if(body.getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				return applyFeminineForm
						?("半母牛人"+(plural?"":""))
						:("半公牛人"+(plural?"":""));
			}
			return super.applyNonBipedNameChange(body, baseName, applyFeminineForm, plural);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.COW_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	// CANIDS:
	public static AbstractSubspecies DOG_MORPH = new AbstractSubspecies(true,
			12000,
			"innoxia_race_dog_canine_crush",
			"innoxia_race_dog_canine_crunch",
			"statusEffects/race/raceDogMorph",
			"statusEffects/race/raceBackground",
			"犬化形",
			"犬化形",
			"犬男",
			"犬女",
			"犬男",
			"犬女",
			new FeralAttributes(
					"犬",
					"犬",
					"犬",
					"婊子",
					"犬",
					"婊子",
					LegConfiguration.QUADRUPEDAL,
					70,
					0,
					1,
					5,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.Name]总是能量满满，且极易因新鲜事物而兴奋起来。"
					+ "[npc.she]还有着一种来源于本能的欲望，想对无辜的猫化形展示其支配地位……",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.CAT_MORPH), 5f)),
			null,
			"犬类文化",
			"犬类文化",
			"DOG_MORPH_BASIC",
			"DOG_MORPH_ADVANCED",
			Race.DOG_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 3),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_DOG_MORPH, SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的狗，当下肢似人时称为“犬化形”，而当下肢呈现出大型兽态犬的样子时称为“半犬人”。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "地狱猎犬", false, false),
					applyNonBipedNameChange(body, "地狱猎犬", false, true),
					applyNonBipedNameChange(body, "地狱猎犬", false, false),
					applyNonBipedNameChange(body, "地狱猎犬", true, false),
					applyNonBipedNameChange(body, "地狱猎犬", false, true),
					applyNonBipedNameChange(body, "地狱猎犬", true, true)
				};
			}
			
			return names;
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOG_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies DOG_MORPH_BORDER_COLLIE = new AbstractSubspecies(false,
			24000,
			"innoxia_race_dog_canine_crush",
			"innoxia_race_dog_canine_crunch",
			"statusEffects/race/raceDogMorph",
			"statusEffects/race/raceBackground",
			"边牧化形",
			"边牧化形",
			"边牧男",
			"边牧女",
			"边牧男",
			"边牧女",
			new FeralAttributes(
					"边牧",
					"边牧",
					"边牧犬",
					"边牧婊子",
					"边牧犬",
					"边牧婊子",
					LegConfiguration.QUADRUPEDAL,
					65,
					0,
					1,
					5,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.NameIsFull]相比一般的犬化形更加聪明，并且总有一种强烈冲动，想将周围人聚集在一起。"
					+ "[npc.She]还有着一种来源于本能的欲望，想对无辜的绵羊化形展示其支配地位……",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 5f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.getRaceFromId("innoxia_sheep")), 25f)),
			null,
			"犬类文化",
			"犬类文化",
			"DOG_MORPH_BASIC",
			"DOG_MORPH_ADVANCED",
			Race.DOG_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_DOG_MORPH, SubspeciesPreference.TWO_AVERAGE,
			"一种格外聪明也活力四射的犬化形，呈现出人形边境牧羊犬的外貌。"
							+ "当下肢似人时称为“边牧化形”，而当下肢呈现出大型兽态边牧的样子时则称为“半边牧人”。"
							+ "若要被判定为边牧化形，角色必须是犬化形，且拥有上竖耳或折耳，并且拥有松软的黑色皮毛，带有白色花纹。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			sb.append(body.setCovering(target, BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.FLUFFY, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false)));
			if(body.getEar().getType()==EarType.DOG_MORPH) {
				if(Math.random()<0.5f) {
					sb.append(body.getEar().setType(target, EarType.DOG_MORPH_POINTED));
				} else {
					sb.append(body.getEar().setType(target, EarType.DOG_MORPH_FOLDED));
				}
			}

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "地狱猎犬", false, false),
					applyNonBipedNameChange(body, "地狱猎犬", false, true),
					applyNonBipedNameChange(body, "地狱猎犬", false, false),
					applyNonBipedNameChange(body, "地狱猎犬", true, false),
					applyNonBipedNameChange(body, "地狱猎犬", false, true),
					applyNonBipedNameChange(body, "地狱猎犬", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOG_MORPH) {
				AbstractBodyCoveringType canineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.CANINE_FUR;
				if(body.getCoverings().get(canineFur).getPrimaryColour()==PresetColour.COVERING_BLACK
						&& body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_WHITE
						&& body.getCoverings().get(canineFur).getPattern() == CoveringPattern.MARKED
						&& body.getCoverings().get(canineFur).getModifier() == CoveringModifier.FLUFFY
						&& (body.getEar().getType()==EarType.DOG_MORPH_FOLDED || body.getEar().getType()==EarType.DOG_MORPH_POINTED)) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public String getPathName() {
			return "res/race/neverLucky/dog/border_collie";
		}
	};
	
	public static AbstractSubspecies DOG_MORPH_DOBERMANN = new AbstractSubspecies(false,
			18000,
			"innoxia_race_dog_canine_crush",
			"innoxia_race_dog_canine_crunch",
			"statusEffects/race/raceDogMorphDobermann",
			"statusEffects/race/raceBackground",
			"杜宾化形",
			"杜宾化形",
			"杜宾男",
			"杜宾女",
			"杜宾男",
			"杜宾女",
			new FeralAttributes(
					"杜宾化形",
					"杜宾化形",
					"杜宾犬",
					"杜宾婊子",
					"杜宾犬",
					"杜宾婊子",
					LegConfiguration.QUADRUPEDAL,
					70,
					0,
					1,
					5,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.NameIsFull]时刻准备着保卫[npc.she]的每一位朋友，借助着[npc.her]强健的体魄，通常都能够如愿。"
					+ "[npc.she]还有着一种来源于本能的欲望，想对无辜的猫化形展示其支配地位……",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.CAT_MORPH), 5f)),
			null,
			"犬类文化",
			"犬类文化",
			"DOG_MORPH_BASIC",
			"DOG_MORPH_ADVANCED",
			Race.DOG_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_DOG_MORPH,
			SubspeciesPreference.TWO_AVERAGE,
			"一种犬化形，呈现出人形杜宾犬的外貌。"
				+ "当下肢似人时称为“杜宾化形”，而当下肢呈现出大型兽态杜宾的样子时称为“半杜宾人”。"
				+ "若要被判定为杜宾化形，角色必须是犬化形，且拥有黑色的短皮毛，带棕色、深棕色、棕褐色的花纹。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			Colour secondaryColour = PresetColour.COVERING_BROWN;
			double rand = Math.random();

			if(rand<0.3f) {
				secondaryColour = PresetColour.COVERING_TAN;
			} else if(rand<0.6f) {
				secondaryColour = PresetColour.COVERING_BROWN_DARK;
			}
			sb.append(body.setCovering(target, BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.SHORT, PresetColour.COVERING_BLACK, false, secondaryColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_CANINE_FUR, new Covering(BodyCoveringType.HAIR_CANINE_FUR, CoveringPattern.NONE, PresetColour.COVERING_BLACK, false, secondaryColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_EBONY, false, PresetColour.SKIN_EBONY, false)));
			body.updateCoverings(true, true, true, true);
			if(body.getPenis().getType()==PenisType.DOG_MORPH) {
				sb.append(body.setCovering(target, BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED)));
			}
			if(body.getEar().getType()==EarType.DOG_MORPH) {
				sb.append(body.getEar().setType(target, EarType.DOG_MORPH_POINTED));
			}
			if(body.getTail().getType()==TailType.DOG_MORPH) {
				sb.append(body.getTail().setType(target, TailType.DOG_MORPH_STUBBY));
			}

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "地狱猎犬", false, false),
					applyNonBipedNameChange(body, "地狱猎犬", false, true),
					applyNonBipedNameChange(body, "地狱猎犬", false, false),
					applyNonBipedNameChange(body, "地狱猎犬", true, false),
					applyNonBipedNameChange(body, "地狱猎犬", false, true),
					applyNonBipedNameChange(body, "地狱猎犬", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOG_MORPH) {
				AbstractBodyCoveringType canineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.CANINE_FUR;
				
				if((body.getCoverings().get(canineFur).getPrimaryColour()==PresetColour.COVERING_BLACK
						|| body.getCoverings().get(canineFur).getPrimaryColour()==PresetColour.COVERING_JET_BLACK)
					&& (body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_BROWN
							|| body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_BROWN_DARK
							|| body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_TAN)
					&& body.getCoverings().get(canineFur).getPattern() == CoveringPattern.MARKED
					&& body.getCoverings().get(canineFur).getModifier() == CoveringModifier.SHORT) {
					return 150;
				}
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies DOG_MORPH_GERMAN_SHEPHERD = new AbstractSubspecies(false,
			18000,
			"innoxia_race_dog_canine_crush",
			"innoxia_race_dog_canine_crunch",
			"statusEffects/race/raceDogMorph",
			"statusEffects/race/raceBackground",
			"德牧化形",
			"德牧化形",
			"德牧男",
			"德牧女",
			"德牧男",
			"德牧女",
			new FeralAttributes(
					"德牧",
					"德牧",
					"德牧犬",
					"德牧婊子",
					"德牧犬",
					"德牧婊子",
					LegConfiguration.QUADRUPEDAL,
					70,
					0,
					1,
					5,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.NameHasFull]拥有相对原始的狼一般的外貌，相比于其他大多数犬化形都更有力量，更加聪慧。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 5f)),
			null,
			"犬类文化",
			"犬类文化",
			"DOG_MORPH_BASIC",
			"DOG_MORPH_ADVANCED",
			Race.DOG_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_DOG_MORPH,
			SubspeciesPreference.TWO_AVERAGE,
				"一种强壮、聪慧且忠诚的犬化形亚种，呈现出人形德牧犬的外貌。"
					+ "当下肢似人时称为“德牧化形”，而当下肢呈现出大型兽态德牧的样子时称为“半德牧人”。"
					+ "要被判定为德牧化形，角色必须是犬化形，且拥有上竖的耳朵，以及棕褐色花纹的黑色皮毛。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
			new Value<>(WorldType.DOMINION, SubspeciesSpawnRarity.TEN),
			new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			sb.append(body.setCovering(target, BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.FLUFFY, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_TAN, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_CANINE_FUR, new Covering(BodyCoveringType.HAIR_CANINE_FUR, CoveringPattern.NONE, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_TAN, false)));

			if(body.getEar().getType()==EarType.DOG_MORPH) {
				sb.append(body.getEar().setType(target, EarType.DOG_MORPH_POINTED));
			}

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬",
					"地狱猎犬"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "地狱猎犬", false, false),
					applyNonBipedNameChange(body, "地狱猎犬", false, true),
					applyNonBipedNameChange(body, "地狱猎犬", false, false),
					applyNonBipedNameChange(body, "地狱猎犬", true, false),
					applyNonBipedNameChange(body, "地狱猎犬", false, true),
					applyNonBipedNameChange(body, "地狱猎犬", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOG_MORPH) {
				AbstractBodyCoveringType canineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.CANINE_FUR;
				
				if(body.getCoverings().get(canineFur).getPrimaryColour()==PresetColour.COVERING_BLACK
						&& body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_TAN
						&& body.getCoverings().get(canineFur).getPattern() == CoveringPattern.MARKED
						&& body.getCoverings().get(canineFur).getModifier() == CoveringModifier.FLUFFY
						&& body.getEar().getType()==EarType.DOG_MORPH_POINTED) {
						return 150;
				}
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies WOLF_MORPH = new AbstractSubspecies(true,
			10000,
			"innoxia_race_wolf_wolf_whiskey",
			"innoxia_race_wolf_meat_and_marrow",
			"statusEffects/race/raceWolfMorph",
			"statusEffects/race/raceBackground",
			"狼化形",
			"狼化形",
			"狼男",
			"狼女",
			"狼男",
			"狼女",
			new FeralAttributes(
					"狼",
					"狼",
					"狼",
					"狼",
					"狼",
					"狼",
					LegConfiguration.QUADRUPEDAL,
					80,
					0,
					1,
					4,
					1, false),
			Nocturnality.NOCTURNAL,
			"[npc.NamePos]狼一般的身躯格外有力，经常拥有一种强烈的冲动，想要支配见到的任何人。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 20f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.DAMAGE_UNARMED, 25f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.getRaceFromId("innoxia_sheep")), 25f)),
			null,
			"潜行之狼",
			"潜行之狼",
			"WOLF_MORPH_BASIC",
			"WOLF_MORPH_ADVANCED",
			Race.WOLF_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_WOLF_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的狼，当下肢似人时成为“狼化形”，而下肢呈现出大型兽态狼的样子时称为“半狼人”。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String getName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "嗷呜化形";
			}
			return super.getName(body);
		}
		@Override
		public String getNamePlural(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "嗷呜化形";
			}
			return super.getNamePlural(body);
		}
		@Override
		public String getSingularMaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "嗷呜小子";
			}
			return super.getSingularMaleName(body);
		}
		@Override
		public String getSingularFemaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "嗷呜女";
			}
			return super.getSingularFemaleName(body);
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			List<Colour> naturalWolfFurColours = Util.newArrayListOfValues(
					PresetColour.COVERING_GREY,
					PresetColour.COVERING_BLACK,
					PresetColour.COVERING_JET_BLACK);
			
			Colour c = Util.randomItemFrom(naturalWolfFurColours);

			sb.append(body.setCovering(target, BodyCoveringType.LYCAN_FUR, new Covering(BodyCoveringType.LYCAN_FUR, c)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_LYCAN_FUR, new Covering(BodyCoveringType.HAIR_LYCAN_FUR, c)));
			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_LYCAN_FUR, new Covering(BodyCoveringType.BODY_HAIR_LYCAN_FUR, c)));

			return sb.toString();
		}
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"座狼",
					"座狼",
					"座狼",
					"座狼",
					"座狼",
					"座狼"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "座狼", false, false),
					applyNonBipedNameChange(body, "座狼", false, true),
					applyNonBipedNameChange(body, "座狼", false, false),
					applyNonBipedNameChange(body, "座狼", true, false),
					applyNonBipedNameChange(body, "座狼", false, true),
					applyNonBipedNameChange(body, "座狼", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.WOLF_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies FOX_MORPH = new AbstractSubspecies(true,
			16000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"狐化形",
			"狐化形",
			"狐男",
			"狐女",
			"狐男",
			"狐女",
			new FeralAttributes(
					"狐狸",
					"狐狸",
					"狐狸",
					"雌狐",
					"狐狸",
					"雌狐",
					LegConfiguration.QUADRUPEDAL,
					45,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull]敏捷又狡猾，[npc.sheIs]能够利用敏锐的感官检测到任何能够攻击的机会。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.ENERGY_SHIELDING, 1f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"隐匿之狐",
			"隐匿之狐",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的狐狸，当下肢似人时称为“狐化形”，而下肢呈现出大型兽态狐狸的样子时称为“半狐人”。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			AbstractSubspecies.applyFoxColoring(body);
			return "";
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				return 100;
			}
			return 0;
		}
	};

	public static AbstractSubspecies FOX_MORPH_ARCTIC = new AbstractSubspecies(false,
			20000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"北极狐化形",
			"北极狐化形",
			"北极狐男",
			"北极狐女",
			"北极狐男",
			"北极狐女",
			new FeralAttributes(
					"北极狐",
					"北极狐",
					"雄北极狐",
					"雌北极狐",
					"雄北极狐",
					"雌北极狐",
					LegConfiguration.QUADRUPEDAL,
					30,
					0,
					1,
					4,
					1, false),
			Nocturnality.NOCTURNAL,
			"[npc.NameIsFull]敏捷又狡猾，[npc.sheIs]能够利用敏锐的感官检测到任何能够攻击的机会。"
					+ "由于[npc.sheIsFull]适应了北极的环境，所以能够很好地抵抗袭来的寒冷伤害。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.ENERGY_SHIELDING, 1f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f),
					new Value<>(Attribute.RESISTANCE_ICE, 5f)),
			null,
			"隐匿之狐",
			"隐匿之狐",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_FOX_MORPH_ARCTIC,
			SubspeciesPreference.ONE_LOW,
			"一种人形的狐狸，带有白色皮毛，当下肢似人时称为“北极狐化形”，而下肢呈现出大型兽态狐狸的样子时称为“半北极狐人”。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SNOW, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.THREE)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_PALE, false, PresetColour.SKIN_PALE, true)));
			sb.append(body.setCovering(target, BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.HAIR_FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_FOX_FUR, new Covering(BodyCoveringType.BODY_HAIR_FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			body.updateCoverings(false, false, true, true);
			return sb.toString();
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				AbstractBodyCoveringType foxFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)
							?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR)
							:BodyCoveringType.FOX_FUR;
				Covering fox_fur = body.getCoverings().get(foxFur);
				
				if(fox_fur.getPrimaryColour()==PresetColour.COVERING_WHITE
						&& (body.getEar().getType()!=EarType.FOX_MORPH_BIG)
						&& body.getTail().getType()!=TailType.FOX_MORPH_MAGIC) {
					return 150;
				}
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies FOX_MORPH_FENNEC = new AbstractSubspecies(false,
			16000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"耳廓狐化形",
			"耳廓狐化形",
			"耳廓狐男",
			"耳廓狐女",
			"耳廓狐男",
			"耳廓狐女",
			new FeralAttributes(
					"耳廓狐",
					"耳廓狐",
					"耳廓狐",
					"耳廓雌狐",
					"耳廓狐",
					"耳廓雌狐",
					LegConfiguration.QUADRUPEDAL,
					20,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull]敏捷又狡猾，[npc.sheIs]能够利用敏锐的感官检测到任何能够攻击的机会。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.ENERGY_SHIELDING, 1f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"隐匿之狐",
			"隐匿之狐",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_FOX_MORPH_FENNEC,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的狐狸，耳朵格外大，带有棕褐色、灰金色或亮金色的皮毛。"
					+ "当下肢似人时称为“耳廓狐化形”，而下肢呈现出大型兽态耳廓狐的样子时称为“半耳廓狐人”。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.DESERT, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			Colour fennecColour = Util.randomItemFrom(Util.newArrayListOfValues(PresetColour.COVERING_DIRTY_BLONDE, PresetColour.COVERING_BLEACH_BLONDE, PresetColour.COVERING_TAN));
			
			sb.append(body.setCovering(target, BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_OLIVE, false, PresetColour.SKIN_OLIVE, false)));
			body.updateCoverings(true, true, true, true);
			if(body.getPenis().getType()==PenisType.FOX_MORPH) {
				sb.append(body.setCovering(target, BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED)));
			}
			if(body.getEar().getType()==EarType.FOX_MORPH) {
				sb.append(body.getEar().setType(target, EarType.FOX_MORPH_BIG));
			}

			return sb.toString();
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				if ((body.getEar().getType()==EarType.FOX_MORPH_BIG)
						&& body.getTail().getType() != TailType.FOX_MORPH_MAGIC) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public String getPathName() {
			return "res/race/neverLucky/fox/fennec";
		}
		@Override
		public int getIconSize() {
			return 70;
		}
	};
	
	public static AbstractSubspecies FOX_ASCENDANT = new AbstractSubspecies(false,
			15000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"妖狐",
			"妖狐",
			"妖狐男",
			"妖狐女",
			"妖狐男",
			"妖狐女",
			new FeralAttributes(
					"妖狐狐狸",
					"妖狐狐狸",
					"雄妖狐",
					"雌妖狐",
					"雄妖狐",
					"雌妖狐",
					LegConfiguration.QUADRUPEDAL,
					50,
					0,
					1,
					4,
					1, false),
			Nocturnality.CATHEMERAL,
			"",
			null,
			null,
			"九尾狐",
			"狐之九尾",
			"YOUKO_BASIC",
			"YOUKO_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 10)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形狐狸，由两位妖狐莉琳中的其中一位转化而成，拥有充盈着奥术的尾巴。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.YOUKO_FOREST, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.DISABLE_SPAWN_PREFERENCE)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 40;
		}
		@Override
		public float getChanceForMaleOffspring() {
			return 0.1f; // same as demons
		}
		@Override
		public int getBaseSlaveValue(GameCharacter character) {
			if(character==null) {
				return 15000;
			} else {
				return 15000 * character.getMaxTailCount();
			}
		}
		@Override
		public boolean isAbleToSelfTransform() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			AbstractSubspecies.applyFoxColoring(body);
			if(body.getTail().getType()==TailType.FOX_MORPH) {
				sb.append(body.getTail().setTailCount(target, 1, true)); // Youko should always spawn with 1 tail
				sb.append(body.getTail().setType(target, TailType.FOX_MORPH_MAGIC));
			}
			return sb.toString();
		}
		
		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if(character.getMaxTailCount()==1) {
				return UtilText.parse(character, "[npc.NameIsFull]是一只妖狐，虽然[npc.she]只有一条奥术尾巴，[npc.sheIsFull]却仍是个合格的施法者。");
			} else {
				return UtilText.parse(character, "[npc.NameFull]是一只妖狐，因侍奉一位妖狐莉琳，获得了[npc.tailMaxCount]条奥术尾巴。");
			}
		}

		@Override
		public Map<AbstractAttribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
			//TODO move the variable racial bonuses out of Subspecies and put them in the special youko perks
			if(character!=null && character.getMaxTailCount()<9) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, (float) (10*character.getMaxTailCount())),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, (float) (10 + 5*character.getMaxTailCount())),
						new Value<>(Attribute.CRITICAL_DAMAGE, (float) (20 + 5*character.getMaxTailCount())));
			} else {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, 100f),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, 60f),
						new Value<>(Attribute.CRITICAL_DAMAGE, 100f));
			}
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null) {
				return youkoIconMap.get(9);
			}
			return getBipedBackground(youkoIconMap.get(character.getMaxTailCount()), character, this.getColour(character));
		}

		@Override
		public String getBookSVGString() {
			return youkoIconMapNoBackground.get(9);
		}
		
		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null) {
				return youkoDesaturatedIconMap.get(9);
			}
			return getBipedBackground(youkoDesaturatedIconMap.get(character.getMaxTailCount()), character, PresetColour.BASE_GREY);
		}
		
		@Override
		public String getHalfDemonSVGString(GameCharacter character) {
			if(character!=null && character.getSubspeciesOverride()!=null && character.getSubspeciesOverride().equals(Subspecies.DEMON)) {
				return super.getHalfDemonSVGString(character);
			} else {
				return getBipedBackground(youkoHalfDemonIconMap.get(character.getMaxTailCount()), character, PresetColour.RACE_HALF_DEMON);
			}
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				if(body.getTail().getType()==TailType.FOX_MORPH_MAGIC) {
					body.setSubspeciesOverride(Subspecies.FOX_ASCENDANT);
					return 200;
				}
			}
			return 0;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	public static AbstractSubspecies FOX_ASCENDANT_ARCTIC = new AbstractSubspecies(false,
			15000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"北极妖狐",
			"北极妖狐",
			"北极妖狐男",
			"北极妖狐女",
			"北极妖狐男",
			"北极妖狐女",
			new FeralAttributes(
					"北极妖狐狐狸",
					"北极妖狐狐狸",
					"北极妖狐雄狐",
					"北极妖狐雌狐",
					"北极妖狐雄狐",
					"北极妖狐雌狐",
					LegConfiguration.QUADRUPEDAL,
					40,
					0,
					1,
					4,
					1, false),
			Nocturnality.CATHEMERAL,
			"",
			null,
			null, "狐之九尾",
			"狐之九尾",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 10)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形北极狐，由两位妖狐莉琳中的其中一位转化而成，拥有充盈着奥术的尾巴。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.YOUKO_FOREST, SubspeciesSpawnRarity.TWO)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.DISABLE_SPAWN_PREFERENCE)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 40;
		}
		@Override
		public float getChanceForMaleOffspring() {
			return 0.1f; // same as demons
		}
		@Override
		public int getBaseSlaveValue(GameCharacter character) {
			if(character==null) {
				return 15000;
			} else {
				return 15000 * character.getMaxTailCount();
			}
		}
		@Override
		public boolean isAbleToSelfTransform() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_PALE, false, PresetColour.SKIN_PALE, true)));
			sb.append(body.setCovering(target, BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.HAIR_FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_FOX_FUR, new Covering(BodyCoveringType.BODY_HAIR_FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			if(body.getTail().getType()==TailType.FOX_MORPH) {
				sb.append(body.getTail().setTailCount(target, 1, true)); // Youko should always spawn with 1 tail
				sb.append(body.getTail().setType(target, TailType.FOX_MORPH_MAGIC));
			}
			body.updateCoverings(false, false, true, true);
			return sb.toString();
		}
		
		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if(character.getMaxTailCount()<9) {
				return UtilText.parse(character, "[npc.NameIsFull]是一只北极狐化形，通过为某位莉琳效力从而得到了[npc.tailMaxCount]根奥术之尾。");
			} else {
				return UtilText.parse(character, "[npc.NameIsFull]是一只北极狐化形，其数条奥术之尾预示着[npc.her]已经向某位莉琳献出了无休无止的忠诚。");
			}
		}

		@Override
		public Map<AbstractAttribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
			if(character!=null && character.getMaxTailCount()<9) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, (float) (10*character.getMaxTailCount())),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, (float) (10 + 5*character.getMaxTailCount())),
						new Value<>(Attribute.CRITICAL_DAMAGE, (float) (20 + 5*character.getMaxTailCount())));
			} else {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, 100f),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, 60f),
						new Value<>(Attribute.CRITICAL_DAMAGE, 100f));
			}
		}
		
		@Override
		public String getSVGString(GameCharacter character) {
			return FOX_ASCENDANT.getSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			return FOX_ASCENDANT.getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				AbstractBodyCoveringType foxFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FOX_FUR;
				Covering fox_fur = body.getCoverings().get(foxFur);
				
				if(fox_fur.getPrimaryColour()==PresetColour.COVERING_WHITE
						&& (body.getEar().getType()!=EarType.FOX_MORPH_BIG)
						&& body.getTail().getType() == TailType.FOX_MORPH_MAGIC) {
					body.setSubspeciesOverride(Subspecies.FOX_ASCENDANT_ARCTIC);
					return 250;
				}
			}
			return 0;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	public static AbstractSubspecies FOX_ASCENDANT_FENNEC = new AbstractSubspecies(false,
			15000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"耳廓妖狐",
			"耳廓妖狐",
			"耳廓妖狐男",
			"耳廓妖狐女",
			"耳廓妖狐男",
			"耳廓妖狐女",
			new FeralAttributes(
					"耳廓妖狐狐狸",
					"耳廓妖狐狐狸",
					"耳廓妖狐雄狐",
					"耳廓妖狐雌狐",
					"耳廓妖狐雄狐",
					"耳廓妖狐雌狐",
					LegConfiguration.QUADRUPEDAL,
					30,
					0,
					1,
					4,
					1, false),
			Nocturnality.CATHEMERAL,
			"",
			null,
			null, "狐之九尾",
			"狐之九尾",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 10)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形耳廓狐，由两位妖狐莉琳中的其中一位转化而成，拥有充盈着奥术的尾巴。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.YOUKO_FOREST, SubspeciesSpawnRarity.ONE)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.DISABLE_SPAWN_PREFERENCE)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 40;
		}
		@Override
		public float getChanceForMaleOffspring() {
			return 0.1f; // same as demons
		}
		@Override
		public int getBaseSlaveValue(GameCharacter character) {
			if(character==null) {
				return 15000;
			} else {
				return 15000 * character.getMaxTailCount();
			}
		}
		@Override
		public boolean isAbleToSelfTransform() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			Colour fennecColour = PresetColour.COVERING_BLEACH_BLONDE;
			double rand = Math.random();
			if(rand<0.5f) {
				fennecColour = PresetColour.COVERING_DIRTY_BLONDE;
			}
			sb.append(body.setCovering(target, BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_OLIVE, false, PresetColour.SKIN_OLIVE, false)));
			body.updateCoverings(true, true, true, true);
			if(body.getPenis().getType()==PenisType.FOX_MORPH) {
				sb.append(body.setCovering(target, BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED)));
			}
			if(body.getEar().getType()==EarType.FOX_MORPH) {
				sb.append(body.getEar().setType(target, EarType.FOX_MORPH_BIG));
			}
			if(body.getTail().getType()==TailType.FOX_MORPH) {
				sb.append(body.getTail().setTailCount(target, 1, true)); // Youko should always spawn with 1 tail
				sb.append(body.getTail().setType(target, TailType.FOX_MORPH_MAGIC));
			}
			return sb.toString();
		}
		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if(character.getMaxTailCount()<9) {
				return UtilText.parse(character, "[npc.NameIsFull]是一只狐化形，通过为某位莉琳效力从而得到了[npc.tailMaxCount]根奥术之尾。");
			} else {
				return UtilText.parse(character, "[npc.NameIsFull]是一只狐化形，其数条奥术之尾预示着[npc.her]已经向某位莉琳献出了无休无止的忠诚。");
			}
		}
		@Override
		public Map<AbstractAttribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
			if(character!=null && character.getMaxTailCount()<9) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, (float) (10*character.getMaxTailCount())),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, (float) (10 + 5*character.getMaxTailCount())),
						new Value<>(Attribute.CRITICAL_DAMAGE, (float) (20 + 5*character.getMaxTailCount())));
			} else {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, 100f),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, 60f),
						new Value<>(Attribute.CRITICAL_DAMAGE, 100f));
			}
		}
		@Override
		public String getSVGString(GameCharacter character) {
			return FOX_ASCENDANT.getSVGString(character);
		}
		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			return FOX_ASCENDANT.getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				if ((body.getEar().getType()==EarType.FOX_MORPH_BIG)
						&& body.getTail().getType() == TailType.FOX_MORPH_MAGIC) {
					body.setSubspeciesOverride(Subspecies.FOX_ASCENDANT_FENNEC);
					return 250;
				}
			}
			return 0;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	// FELINES:
	public static AbstractSubspecies CAT_MORPH = new AbstractSubspecies(true,
			12000,
			"innoxia_race_cat_felines_fancy",
			"innoxia_race_cat_kittys_reward",
			"statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackground",
			"猫化形",
			"猫化形",
			"猫男",
			"猫女",
			"猫男",
			"猫女",
			new FeralAttributes(
					"猫",
					"猫",
					"公猫",
					"母猫",
					"公猫",
					"母猫",
					LegConfiguration.QUADRUPEDAL,
					25,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NamePos]的身体尤其敏捷，反应如闪电般迅速。"
					+ "[npc.She]还有着一种来源于本能的欲望，想对无辜的哈比和啮齿类化形展示其支配地位……",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HARPY), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.RAT_MORPH), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.SQUIRREL_MORPH), 5f)),
			null,
			"好奇小猫咪",
			"好奇小猫咪",
			"CAT_MORPH_BASIC",
			"CAT_MORPH_ADVANCED",
			Race.CAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CAT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的猫，当下肢拟人时被称为“猫化形”，而当下肢呈现出经典的大型兽态猫的样子时则称为“半猫人”。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String getName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "喵化形";
			}
			return super.getName(body);
		}
		@Override
		public String getNamePlural(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "喵化形";
			}
			return super.getNamePlural(body);
		}
		@Override
		public String getSingularMaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "喵喵小子";
			}
			return super.getSingularMaleName(body);
		}
		@Override
		public String getSingularFemaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "喵喵女";
			}
			return super.getSingularFemaleName(body);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.CAT_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies CAT_MORPH_LYNX = new AbstractSubspecies(false,
			12000,
			"innoxia_race_cat_felines_fancy",
			"innoxia_race_cat_kittys_reward",
			"statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackground",
			"猞猁化形",
			"猞猁化形",
			"猞猁男",
			"猞猁女",
			"猞猁男",
			"猞猁女",
			new FeralAttributes(
					"猞猁",
					"猞猁",
					LegConfiguration.QUADRUPEDAL,
					60,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NamePos]的身体尤其敏捷，反应如闪电般迅速。"
					+ "[npc.She]还有着一种来源于本能的欲望，想对无辜的哈比和啮齿类化形展示其支配地位……",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HARPY), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.RAT_MORPH), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.SQUIRREL_MORPH), 5f)),
			null,
			"好奇小猫咪",
			"好奇小猫咪",
			"CAT_MORPH_BASIC",
			"CAT_MORPH_ADVANCED",
			Race.CAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CAT_MORPH_LYNX,
			SubspeciesPreference.TWO_AVERAGE,
			"一种人形的猞猁，当下肢为双足时称为“猞猁化形”，而下肢呈现出大型兽态猞猁的样子时称为“半猞猁人”"
				+ "若要被判定为猞猁化形，角色必须是猫化形，并且拥有松软的皮毛，较短的尾巴和脸颊绒。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			Colour primaryColor = PresetColour.COVERING_BROWN;
			double rand = Math.random();
			if(rand<0.3f) {
				primaryColor = PresetColour.COVERING_TAN;
			} else if(rand<0.6f) {
				primaryColor = PresetColour.COVERING_BROWN_DARK;
			}
			sb.append(body.setCovering(target, BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.FLUFFY, primaryColor, false, PresetColour.COVERING_BLACK, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, PresetColour.COVERING_BLACK, false)));
			body.updateCoverings(true, true, true, true);
			if(body.getEar().getType().getRace()==Race.CAT_MORPH) {
				sb.append(body.getEar().setType(target, EarType.CAT_MORPH_TUFTED));
			}
			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
				sb.append(body.getTail().setType(target, TailType.CAT_MORPH_SHORT));
			}
			if(body.getHair().getType().getRace()==Race.CAT_MORPH) {
				sb.append(body.getHair().setType(target, HairType.CAT_MORPH_SIDEFLUFF));
			}
			return sb.toString();
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.CAT_MORPH) {
				AbstractFaceType faceType = body.getFace().getType();
				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
				
				if((faceType == FaceType.CAT_MORPH || faceType == FaceType.HUMAN)
						&& body.getHair().getType() == HairType.CAT_MORPH_SIDEFLUFF
						&& body.getEar().getType()==EarType.CAT_MORPH_TUFTED
						&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.FLUFFY
						&& body.getTail().getType()==TailType.CAT_MORPH_SHORT) {
					return 150;
				}
			}
			return 0;
		}
	};

	public static AbstractSubspecies CAT_MORPH_CHEETAH = new AbstractSubspecies(false,
			16000,
			"innoxia_race_cat_felines_fancy",
			"innoxia_race_cat_kittys_reward",
			"statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackgroundCheetah",
			"猎豹化形",
			"猎豹化形",
			"猎豹男",
			"猎豹女",
			"猎豹男",
			"猎豹女",
			new FeralAttributes(
					"猎豹",
					"猎豹",
					"猎豹",
					"猎豹",
					"猎豹",
					"猎豹",
					LegConfiguration.QUADRUPEDAL,
					80,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull]极其迅捷，只需短短数秒便能够加速到其他种族无法企及的速度。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f)),
			Util.newArrayListOfValues("从非猎豹化形处逃走的概率为[style.boldExcellent(100%)]"),
			"好奇小猫咪",
			"好奇小猫咪",
			"CAT_MORPH_BASIC",
			"CAT_MORPH_ADVANCED",
			Race.CAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CAT_MORPH_CHEETAH,
			SubspeciesPreference.TWO_AVERAGE,
				"一种人形的猎豹，当下肢为双足时称为“猎豹化形”，而下肢呈现出大型兽态雪貂的样子时称为“半猎豹人”"
					+ "若要被判定为猎豹化形，角色必须是猫化形，并且拥有较短的斑点皮毛，并且不能被认定为其他猫类化形。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			Colour primaryColor = PresetColour.COVERING_ORANGE;
			double rand = Math.random();
			if(rand<0.35f) {
				primaryColor = PresetColour.COVERING_TAN;
			}
			Colour secondaryColor = PresetColour.COVERING_BLACK;
			sb.append(body.setCovering(target, BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false)));
			body.updateCoverings(true, true, true, true);
			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
				sb.append(body.getTail().setType(target, TailType.CAT_MORPH));
			}
			
			// Body size adjustment
			if(body.getBreast().getRawSizeValue()>CupSize.B.getMeasurement()) {
				rand = Math.random();
				if(rand<0.35f) {
					sb.append(body.getBreast().setSize(target, CupSize.B.getMeasurement()));
				} else if(rand<0.70f) {
					sb.append(body.getBreast().setSize(target, CupSize.A.getMeasurement()));
				} else {
					sb.append(body.getBreast().setSize(target, CupSize.AA.getMeasurement()));
				}
			}
			
			if(target!=null && target.getBody()!=null) {
				sb.append(target.setBodySize(BodySize.ZERO_SKINNY.getMedianValue()));
				sb.append(target.setMuscle(Muscle.FOUR_RIPPED.getMedianValue()));

			} else {
				body.setBodySize(BodySize.ZERO_SKINNY.getMedianValue());
				body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			}

			return sb.toString();
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.CAT_MORPH) {
				AbstractFaceType faceType = body.getFace().getType();
				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
				
				if((faceType == FaceType.CAT_MORPH || faceType == FaceType.HUMAN)
						&& body.getCoverings().get(felineFur).getPattern() == CoveringPattern.SPOTTED
						&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.SHORT
						&& body.getTail().getType()==TailType.CAT_MORPH) {
					return 150;
				}
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies CAT_MORPH_CARACAL = new AbstractSubspecies(false,
			12000,
			"innoxia_race_cat_felines_fancy",
			"innoxia_race_cat_kittys_reward",
			"statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackground",
			"狞猫化形",
			"狞猫化形",
			"狞猫男",
			"狞猫女",
			"狞猫男",
			"狞猫女",
			new FeralAttributes(
					"狞猫",
					"狞猫",
					"狞猫",
					"狞猫",
					"狞猫",
					"狞猫",
					LegConfiguration.QUADRUPEDAL,
					50,
					0,
					1,
					4,
					1, false),
			Nocturnality.NOCTURNAL,
			"[npc.NamePos]的身体尤其敏捷，反应如闪电般迅速。"
					+ "[npc.She]还有着一种来源于本能的欲望，想对无辜的哈比和啮齿类化形展示其支配地位……",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HARPY), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.RAT_MORPH), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.SQUIRREL_MORPH), 5f)),
			null,
			"好奇小猫咪",
			"好奇小猫咪",
			"CAT_MORPH_BASIC",
			"CAT_MORPH_ADVANCED",
			Race.CAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CAT_MORPH_CARACAL,
			SubspeciesPreference.TWO_AVERAGE,
				"一种人形的狞猫，当下肢似人时成为“狞猫化形”，而下肢呈现出大型兽态狞猫的样子时称为“半狞猫人”。"
					+ "要被判定为狞猫化形，角色必须是猫化形，且耳朵上有簇生绒毛。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			if(body.getEar().getType().getRace()==Race.CAT_MORPH) {
				sb.append(body.getEar().setType(target, EarType.CAT_MORPH_TUFTED));
			}
			return sb.toString();
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.CAT_MORPH) {
				if(body.getEar().getType()==EarType.CAT_MORPH_TUFTED) {
					return 140;
				}
			}
			return 0;
		}
	};

//	public static AbstractSubspecies CAT_MORPH_LEOPARD_SNOW = new AbstractSubspecies(false,
//			18000,
//			"innoxia_race_cat_felines_fancy",
//			"innoxia_race_cat_kittys_reward",
//			"statusEffects/race/raceCatMorph",
//			"statusEffects/race/raceBackgroundSnowLeopard",
//			"snow leopard-morph",
//			"snow leopard-morphs",
//			"snow leopard-boy",
//			"snow leopard-girl",
//			"snow leopard-boys",
//			"snow leopard-girls",
//			new FeralAttributes(
//					"snow leopard",
//					"snow leopards",
//					"snow leopard",
//					"snow leopardess",
//					"snow leopards",
//					"snow leopardesses",
//					LegConfiguration.QUADRUPEDAL,
//					60,
//					0,
//					1,
//					4,
//					1, false),
//			"[npc.NamePos] body is very strong and agile, and [npc.sheIsFull] capable of great feats of strength and stealth alike."
//					+ " [npc.She] also [npc.has] a very high resistance to both natural and arcane cold.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 20f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
//					new Value<>(Attribute.CRITICAL_DAMAGE, 15f),
//					new Value<>(Attribute.DAMAGE_UNARMED, 5f),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
//					new Value<>(Attribute.RESISTANCE_ICE, 5f)),
//			null,
//			"Curious Kitties",
//			"Curious Kitties",
//			"CAT_MORPH_BASIC",
//			"CAT_MORPH_ADVANCED",
//			Race.CAT_MORPH,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 10),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 10),
//					new Value<>(PerkCategory.LUST, 2),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.RACE_CAT_MORPH_LEOPARD_SNOW,
//			SubspeciesPreference.TWO_AVERAGE,
//			"An anthropomorphic snow leopard, known as a 'snow leopard-morph' when bipedal, and a 'snow leopardtaur' when the lower body is that of a typically-oversized feral snow leopard."
//				+ " To be identified as a snow leopard-morph, a character must be a cat-morph that has fluffy spotted fur, normal tail and panther face.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ZERO_EXTREMELY_RARE),
//					new Value<>(WorldRegion.MOUNTAINS, SubspeciesSpawnRarity.ZERO_EXTREMELY_RARE),
//					new Value<>(WorldRegion.SNOW, SubspeciesSpawnRarity.ZERO_EXTREMELY_RARE)),
//			Util.newHashMapOfValues(
//				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			Colour primaryColor = PresetColour.COVERING_WHITE;
//			Colour secondaryColor = PresetColour.COVERING_BLACK;
//			double rand = Math.random();
//			if(rand<0.3f) {
//				primaryColor = PresetColour.COVERING_WHITE;
//			} else if(rand<0.6f) {
//				primaryColor = PresetColour.COVERING_GREY;
//			} else if(rand<0.65f) {
//				primaryColor = PresetColour.COVERING_BLACK;
//			}
//			sb.append(body.setCovering(target, BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.FLUFFY, primaryColor, false, secondaryColor, false));
//			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
//			body.updateCoverings(true, true, true, true);
//			if(body.getFace().getType()==FaceType.CAT_MORPH) {
//				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
//			}
//			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
//				body.getTail().setType(null, TailType.CAT_MORPH);
//				body.getTail().setTailGirth(null, PenetrationGirth.FOUR_THICK.getValue());
//			}
//			
//			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
//			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
//		}
//
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.CAT_MORPH) {
//				AbstractFaceType faceType = body.getFace().getType();
//				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
//				
//				if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
//					&& body.getCoverings().get(felineFur).getPattern() == CoveringPattern.SPOTTED
//					&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.FLUFFY
//					&& body.getTail().getType()==TailType.CAT_MORPH) {
//					return 150;
//				}
//			}
//			return 0;
//		}
//	};
//	
//	public static AbstractSubspecies CAT_MORPH_LEOPARD = new AbstractSubspecies(false,
//			16000,
//			"innoxia_race_cat_felines_fancy",
//			"innoxia_race_cat_kittys_reward",
//			"statusEffects/race/raceCatMorph",
//			"statusEffects/race/raceBackgroundLeopard",
//			"leopard-morph",
//			"leopard-morphs",
//			"leopard-boy",
//			"leopard-girl",
//			"leopard-boys",
//			"leopard-girls",
//			new FeralAttributes(
//					"leopard",
//					"leopards",
//					"leopard",
//					"leopardess",
//					"leopard",
//					"leopardesses",
//					LegConfiguration.QUADRUPEDAL,
//					70,
//					0,
//					1,
//					4,
//					1, false),
//			"[npc.NamePos] body is very strong and agile, and [npc.sheIsFull] capable of great feats of strength and stealth alike. [npc.She] also [npc.has] a high resistance to both natural and arcane heat.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 20f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
//					new Value<>(Attribute.CRITICAL_DAMAGE, 15f),
//					new Value<>(Attribute.DAMAGE_UNARMED, 5f),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
//					new Value<>(Attribute.RESISTANCE_FIRE, 5f)),
//			null,
//			"Curious Kitties",
//			"Curious Kitties",
//			"CAT_MORPH_BASIC",
//			"CAT_MORPH_ADVANCED",
//			Race.CAT_MORPH,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 10),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 10),
//					new Value<>(PerkCategory.LUST, 2),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.RACE_CAT_MORPH_LEOPARD,
//			SubspeciesPreference.TWO_AVERAGE,
//			"An anthropomorphic leopard, known as a 'leopard-morph' when bipedal, and a 'leopardtaur' when the lower body is that of a typically-oversized feral leopard."
//			+ " To be identified as a leopard-morph, a character must be a cat-morph that has short spotted fur, normal tail and panther face.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE_VERY_RARE),
//					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TWO_RARE)),
//			Util.newHashMapOfValues(
//				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			Colour primaryColor = PresetColour.COVERING_SANDY;
//			Colour secondaryColor = PresetColour.COVERING_BLACK;
//			double rand = Math.random();
//			if(rand<0.05f) {
//				primaryColor = PresetColour.COVERING_BLACK;
//			}
//			sb.append(body.setCovering(target, BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
//			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
//			body.updateCoverings(true, true, true, true);
//			if(body.getFace().getType()==FaceType.CAT_MORPH) {
//				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
//			}
//			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
//				body.getTail().setType(null, TailType.CAT_MORPH);
//			}
//			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
//			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
//		}
//
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.CAT_MORPH) {
//				AbstractFaceType faceType = body.getFace().getType();
//				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
//				
//				if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
//					&& body.getCoverings().get(felineFur).getPattern() == CoveringPattern.SPOTTED
//					&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.SHORT
//					&& body.getTail().getType()==TailType.CAT_MORPH) {
//					return 150;
//				}
//			}
//			return 0;
//		}
//	};
//	
//	public static AbstractSubspecies CAT_MORPH_LION = new AbstractSubspecies(false,
//			24000,
//			"innoxia_race_cat_felines_fancy",
//			"innoxia_race_cat_kittys_reward",
//			"statusEffects/race/raceCatMorph",
//			"statusEffects/race/raceBackground",
//			"lion-morph",
//			"lion-morphs",
//			"lion-boy",
//			"lion-girl",
//			"lion-boys",
//			"lion-girls",
//			new FeralAttributes(
//					"lion",
//					"lions",
//					"lion",
//					"lioness",
//					"lions",
//					"lionesses",
//					LegConfiguration.QUADRUPEDAL,
//					100,
//					0,
//					1,
//					4,
//					1,
//					true),
//			"[npc.NamePos] body is extremely strong, and [npc.sheIsFull] capable of great feats of strength. [npc.She] also [npc.has] a high resistance to both natural and arcane heat.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
//					new Value<>(Attribute.DAMAGE_UNARMED, 15f),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 15f),
//					new Value<>(Attribute.RESISTANCE_FIRE, 5f)),
//			null,
//			"Curious Kitties",
//			"Curious Kitties",
//			"CAT_MORPH_BASIC",
//			"CAT_MORPH_ADVANCED",
//			Race.CAT_MORPH,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 20),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 20),
//					new Value<>(PerkCategory.LUST, 2),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.RACE_CAT_MORPH_LION,
//			SubspeciesPreference.TWO_AVERAGE,
//			"An anthropomorphic lion, known as a 'lion-morph' when bipedal, and a 'liontaur' when the lower body is that of a feral lion."
//				+ " To be identified as a lion-morph, a character must be a cat-morph that has short fur, tufted tail and panther face.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE_VERY_RARE),
//					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TWO_RARE)),
//			Util.newHashMapOfValues(
//				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			Colour primaryColor = PresetColour.COVERING_TAN;
//			Colour secondaryColor = PresetColour.COVERING_BLACK;
//			double rand = Math.random();
//			if(rand<0.05f) {
//				primaryColor = PresetColour.COVERING_BLACK;
//			}
//			else if(rand<0.1f) {
//				primaryColor = PresetColour.COVERING_WHITE;
//			}
//			sb.append(body.setCovering(target, BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
//			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
//			body.updateCoverings(true, true, true, true);
//			if(body.getFace().getType()==FaceType.CAT_MORPH) {
//				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
//			}
//			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
//				body.getTail().setType(null, TailType.CAT_MORPH_TUFTED);
//			}
//			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
//			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
//		}
//
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.CAT_MORPH) {
//				AbstractFaceType faceType = body.getFace().getType();
//				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
//				
//				if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
//					&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.SHORT
//					&& body.getTail().getType()==TailType.CAT_MORPH_TUFTED) {
//					return 150;
//				}
//			}
//			return 0;
//		}
//	};
//	
//	public static AbstractSubspecies CAT_MORPH_TIGER = new AbstractSubspecies(false,
//			30000,
//			"innoxia_race_cat_felines_fancy",
//			"innoxia_race_cat_kittys_reward",
//			"statusEffects/race/raceCatMorph",
//			"statusEffects/race/raceBackgroundTiger",
//			"tiger-morph",
//			"tiger-morphs",
//			"tiger-boy",
//			"tiger-girl",
//			"tiger-boys",
//			"tiger-girls",
//			new FeralAttributes(
//					"tiger",
//					"tigers",
//					"tiger",
//					"tigress",
//					"tigers",
//					"tigresses",
//					LegConfiguration.QUADRUPEDAL,
//					100,
//					0,
//					1,
//					4,
//					1, false),
//			"[npc.NamePos] body is extremely strong, and [npc.sheIsFull] capable of great feats of strength.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
//					new Value<>(Attribute.DAMAGE_UNARMED, 25f),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 25f)),
//			null,
//			"Curious Kitties",
//			"Curious Kitties",
//			"CAT_MORPH_BASIC",
//			"CAT_MORPH_ADVANCED",
//			Race.CAT_MORPH,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 20),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 20),
//					new Value<>(PerkCategory.LUST, 2),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.RACE_CAT_MORPH_TIGER,
//			SubspeciesPreference.TWO_AVERAGE,
//			"An anthropomorphic tiger, known as a 'tiger-morph' when bipedal, and a 'tigertaur' when the lower body is that of a feral tiger."
//				+ " To be identified as a tiger-morph, a character must be a cat-morph that has striped fur, normal tail and panther face.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE_VERY_RARE),
//					new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TWO_RARE)),
//			Util.newHashMapOfValues(
//				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			Colour primaryColor = PresetColour.COVERING_ORANGE;
//			Colour secondaryColor = PresetColour.COVERING_BLACK;
//			double rand = Math.random();
//			if(rand<0.6f) {
//				primaryColor = Util.randomItemFrom(Util.newArrayListOfValues(PresetColour.COVERING_TAN, PresetColour.COVERING_AUBURN, PresetColour.COVERING_AMBER));
//			} else if(rand<0.12f) {
//				primaryColor = PresetColour.COVERING_WHITE;
//			} else if(rand<0.16f) {
//				primaryColor = PresetColour.COVERING_BLACK;
//			}
//			sb.append(body.setCovering(target, BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.STRIPED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
//			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
//			body.updateCoverings(true, true, true, true);
//			if(body.getFace().getType()==FaceType.CAT_MORPH) {
//				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
//			}
//			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
//				body.getTail().setType(null, TailType.CAT_MORPH);
//			}
//			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
//			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
//		}
//
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.CAT_MORPH) {
//				AbstractFaceType faceType = body.getFace().getType();
//				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
//				
//				if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
//					&& body.getCoverings().get(felineFur).getPattern() == CoveringPattern.STRIPED
//					&& body.getTail().getType()==TailType.CAT_MORPH) {
//					return 150;
//					
//				}
//			}
//			return 0;
//		}
//	};
	
	// EQUINES:
	public static AbstractSubspecies HORSE_MORPH = new AbstractSubspecies(true,
			18000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"马化形",
			"马化形",
			"马男",
			"马女",
			"马男",
			"马女",
			new FeralAttributes(
					"马",
					"马",
					"公马",
					"母马",
					"公马",
					"母马",
					LegConfiguration.QUADRUPEDAL,
					175,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"尽管[npc.namePos]的身体力大无比，速度极快，但[npc.sheIs]却并不是世上最聪明的种族，并且在驾驭奥术时也通常十分艰难。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f)),
			null,
			"马类百科全书",
			"马类百科全书",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_HORSE_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的双足马类。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			sb.append(body.getHorn().setType(target, HornType.NONE));
			sb.append(body.getWing().setType(target, WingType.NONE));
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"梦魇",
					"梦魇",
					"梦魇",
					"梦魇",
					"梦魇",
					"梦魇"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "梦魇", false, false),
					applyNonBipedNameChange(body, "梦魇", false, true),
					applyNonBipedNameChange(body, "梦魇", false, false),
					applyNonBipedNameChange(body, "梦魇", true, false),
					applyNonBipedNameChange(body, "梦魇", false, true),
					applyNonBipedNameChange(body, "梦魇", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	public static AbstractSubspecies HORSE_MORPH_UNICORN = new AbstractSubspecies(false,
			30000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"独角兽化形",
			"独角兽化形",
			"独角兽男",
			"独角兽女",
			"独角兽男",
			"独角兽女",
			new FeralAttributes(
					"独角兽",
					"独角兽",
					"雄独角兽",
					"雌独角兽",
					"雄独角兽",
					"雌独角兽",
					LegConfiguration.QUADRUPEDAL,
					175,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"虽然体质略差于常规的马化形，但[npc.nameHasFull]拥有特殊的奥术纽带，能够在[npc.her]灵气衰竭前施放许多法术。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 20f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 50f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"马类百科全书",
			"马类百科全书",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 3),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 3)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 4),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 3)),
			PresetColour.RACE_UNICORN,
			SubspeciesPreference.ONE_LOW,
			"一种人形的双足马类，前额上长有一根充满魔法的独角。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.DOMINION, SubspeciesSpawnRarity.TWO),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			sb.append(body.getHorn().setType(target, HornType.HORSE_STRAIGHT));
			sb.append(body.getHorn().setHornRows(target, 1));
			sb.append(body.getHorn().setHornsPerRow(target, 1));
			sb.append(body.getHorn().setHornLength(target, HornLength.TWO_LONG.getMedianValue()));
			sb.append(body.getWing().setType(target, WingType.NONE));
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"独角兽梦魇",
					"独角兽梦魇",
					"独角兽梦魇",
					"独角兽梦魇",
					"独角兽梦魇",
					"独角兽梦魇"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "独角兽梦魇", false, false),
					applyNonBipedNameChange(body, "独角兽梦魇", false, true),
					applyNonBipedNameChange(body, "独角兽梦魇", false, false),
					applyNonBipedNameChange(body, "独角兽梦魇", true, false),
					applyNonBipedNameChange(body, "独角兽梦魇", false, true),
					applyNonBipedNameChange(body, "独角兽梦魇", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				if(!body.getHorn().getType().equals(HornType.NONE) && body.getHorn().getHornRows()==1 && body.getHorn().getHornsPerRow()==1) {
					return 150;
				}
			}
			return 0;
		}
	};
	public static AbstractSubspecies HORSE_MORPH_PEGASUS = new AbstractSubspecies(false,
			24000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"天马化形",
			"天马化形",
			"天马男",
			"天马女",
			"天马男",
			"天马女",
			new FeralAttributes(
					"天马",
					"天马",
					"公天马",
					"母天马",
					"公天马",
					"母天马",
					LegConfiguration.QUADRUPEDAL,
					175,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"虽然体质略差于常规的马化形，但[npc.nameHasFull]行动更加敏捷，总能够击敌于弱点。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 30f)),
			null,
			"马类百科全书",
			"马类百科全书",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_PEGASUS,
			SubspeciesPreference.ONE_LOW,
			"一种人形的双足马类，背上长有一双羽毛覆盖的翅膀。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			sb.append(body.getHorn().setType(target, HornType.NONE));
			sb.append(body.setWingType(target, WingType.FEATHERED));
			sb.append(body.setWingSize(target, WingSize.THREE_LARGE.getValue()));

			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"天马梦魇",
					"天马梦魇",
					"天马梦魇",
					"天马梦魇",
					"天马梦魇",
					"天马梦魇"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "天马梦魇", false, false),
					applyNonBipedNameChange(body, "天马梦魇", false, true),
					applyNonBipedNameChange(body, "天马梦魇", false, false),
					applyNonBipedNameChange(body, "天马梦魇", true, false),
					applyNonBipedNameChange(body, "天马梦魇", false, true),
					applyNonBipedNameChange(body, "天马梦魇", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				if(body.getWing().getType()==WingType.FEATHERED) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	public static AbstractSubspecies HORSE_MORPH_ALICORN = new AbstractSubspecies(false,
			60000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"天角兽化形",
			"天角兽化形",
			"天角兽男",
			"天角兽女",
			"天角兽男",
			"天角兽女",
			new FeralAttributes(
					"天角兽",
					"天角兽",
					"公天角兽",
					"母天角兽",
					"公天角兽",
					"母天角兽",
					LegConfiguration.QUADRUPEDAL,
					175,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"[npc.nameIsFull]拥有羽翼和独角，被归类为强大的天角兽，施法几乎不费吹灰之力。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 75f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50f)),
			null,
			"马类百科全书",
			"马类百科全书",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 3)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 3)),
			PresetColour.RACE_ALICORN,
			SubspeciesPreference.ONE_LOW,
			"一种人形的双足马类，背上长有一双羽毛覆盖的翅膀，而前额上长有一根充满魔法的独角。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.ONE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			sb.append(body.getHorn().setType(target, HornType.HORSE_STRAIGHT));
			sb.append(body.getHorn().setHornRows(target, 1));
			sb.append(body.getHorn().setHornsPerRow(target, 1));
			sb.append(body.getHorn().setHornLength(target, HornLength.TWO_LONG.getMedianValue()));
			sb.append(body.setWingType(target, WingType.FEATHERED));
			sb.append(body.setWingSize(target, WingSize.THREE_LARGE.getValue()));
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"天角兽梦魇",
					"天角兽梦魇",
					"天角兽梦魇",
					"天角兽梦魇",
					"天角兽梦魇",
					"天角兽梦魇"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "天角兽梦魇", false, false),
					applyNonBipedNameChange(body, "天角兽梦魇", false, true),
					applyNonBipedNameChange(body, "天角兽梦魇", false, false),
					applyNonBipedNameChange(body, "天角兽梦魇", true, false),
					applyNonBipedNameChange(body, "天角兽梦魇", false, true),
					applyNonBipedNameChange(body, "天角兽梦魇", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				if(body.getWing().getType()==WingType.FEATHERED
						&& !body.getHorn().getType().equals(HornType.NONE)
						&& body.getHorn().getHornRows()==1
						&& body.getHorn().getHornsPerRow()==1) {
					return 200;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies CENTAUR = new AbstractSubspecies(false,
			25000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"半人马",
			"半人马",
			"半人马",
			"半人马",
			"半人马",
			"半人马",
			null,
			Nocturnality.DIURNAL,
			"得益于拥有马的下半身，[npc.nameIsFull]能以极快的速度奔跑，还能借此造成巨大的物理伤害。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 35f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50f)),
			null,
			"不止半人马",
			"不止半人马",
			"CENTAUR_BASIC",
			"CENTAUR_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CENTAUR,
			SubspeciesPreference.FOUR_ABUNDANT,
			"任何拥有兽态四足下肢的马类都会被分类为半人马。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public boolean isNonBiped() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			sb.append(body.getHorn().setType(target, HornType.NONE));
			sb.append(body.getWing().setType(target, WingType.NONE));
			sb.append(body.getLeg().setType(target, LegType.HORSE_MORPH));
			if(target!=null && target.getBody()!=null) {
				sb.append(target.setLegConfiguration(LegType.HORSE_MORPH, LegConfiguration.QUADRUPEDAL, true));
			} else {
				LegType.HORSE_MORPH.applyLegConfigurationTransformation(body, LegConfiguration.QUADRUPEDAL, true);
			}
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			sb.append(body.getPenis().getTesticle().getCum().addFluidModifier(target, FluidModifier.MUSKY));
			sb.append(body.getVagina().getGirlcum().addFluidModifier(target, FluidModifier.MUSKY));

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"恶魔半人马",
					"恶魔半人马",
					"恶魔半人马",
					"恶魔半人马",
					"恶魔半人马",
					"恶魔半人马"};
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {	
			if(race==Race.HORSE_MORPH && body.getLeg().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				return 1000;
			}
			return 0;
		}
	};
	public static AbstractSubspecies PEGATAUR = new AbstractSubspecies(false,
			35000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"半天马人",
			"半天马人",
			"半天马人",
			"半天马人",
			"半天马人",
			"半天马人",
			null,
			Nocturnality.DIURNAL,
			"虽然体质略差于常规的半人马，但[npc.nameHasFull]行动更加敏捷，总能够击敌于弱点。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"不止半人马",
			"不止半人马",
			"CENTAUR_BASIC",
			"CENTAUR_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_PEGATAUR,
			SubspeciesPreference.ONE_LOW,
			"任何拥有马型兽态的四足下半身，且带有羽翼和独角的人，都被归类为半天马人。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public boolean isNonBiped() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			sb.append(body.getHorn().setType(target, HornType.NONE));
			sb.append(body.getLeg().setType(target, LegType.HORSE_MORPH));
			if(target!=null && target.getBody()!=null) {
				sb.append(target.setLegConfiguration(LegType.HORSE_MORPH, LegConfiguration.QUADRUPEDAL, true));
			} else {
				LegType.HORSE_MORPH.applyLegConfigurationTransformation(body, LegConfiguration.QUADRUPEDAL, true);
			}

			sb.append(body.setWingType(target, WingType.FEATHERED));
			sb.append(body.setWingSize(target, WingSize.FOUR_HUGE.getValue()));

			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			sb.append(body.getPenis().getTesticle().getCum().addFluidModifier(target, FluidModifier.MUSKY));
			sb.append(body.getVagina().getGirlcum().addFluidModifier(target, FluidModifier.MUSKY));

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"恶魔半天马人",
					"恶魔半天马人",
					"恶魔半天马人",
					"恶魔半天马人",
					"恶魔半天马人",
					"恶魔半天马人"};
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH && body.getLeg().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				if(body.getWing().getType()==WingType.FEATHERED) {
					return 1150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	public static AbstractSubspecies UNITAUR = new AbstractSubspecies(false,
			50000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"半独角兽人",
			"半独角兽人",
			"半独角兽人",
			"半独角兽人",
			"半独角兽人",
			"半独角兽人",
			null,
			Nocturnality.DIURNAL,
			"虽然体质略差于常规的半人马，但[npc.nameHasFull]拥有特殊的奥术纽带，能够在[npc.her]灵气衰竭前施放许多法术。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 50f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"不止半人马",
			"不止半人马",
			"CENTAUR_BASIC",
			"CENTAUR_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_UNICORN,
			SubspeciesPreference.ONE_LOW,
			"任何拥有兽态四足下肢和独角的马类，都被归类为半独角兽人。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public boolean isNonBiped() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			sb.append(body.getHorn().setType(target, HornType.HORSE_STRAIGHT));
			sb.append(body.getHorn().setHornRows(target, 1));
			sb.append(body.getHorn().setHornsPerRow(target, 1));
			sb.append(body.getHorn().setHornLength(target, HornLength.TWO_LONG.getMedianValue()));
			sb.append(body.getLeg().setType(target, LegType.HORSE_MORPH));
			if(target!=null && target.getBody()!=null) {
				sb.append(target.setLegConfiguration(LegType.HORSE_MORPH, LegConfiguration.QUADRUPEDAL, true));
			} else {
				LegType.HORSE_MORPH.applyLegConfigurationTransformation(body, LegConfiguration.QUADRUPEDAL, true);
			}

			sb.append(body.getWing().setType(target, WingType.NONE));
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			sb.append(body.getPenis().getTesticle().getCum().addFluidModifier(target, FluidModifier.MUSKY));
			sb.append(body.getVagina().getGirlcum().addFluidModifier(target, FluidModifier.MUSKY));

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"恶魔半独角兽人",
					"恶魔半独角兽人",
					"恶魔半独角兽人",
					"恶魔半独角兽人",
					"恶魔半独角兽人",
					"恶魔半独角兽人"};
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH && body.getLeg().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				if(!body.getHorn().getType().equals(HornType.NONE) && body.getHorn().getHornRows()==1 && body.getHorn().getHornsPerRow()==1) {
					return 1150;
				}
			}
			return 0;
		}
	};
	public static AbstractSubspecies ALITAUR = new AbstractSubspecies(false,
			100000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"半天角兽人",
			"半天角兽人",
			"半天角兽人",
			"半天角兽人",
			"半天角兽人",
			"半天角兽人",
			null,
			Nocturnality.DIURNAL,
			"[npc.nameIsFull]拥有羽翼和独角，被归类为强大的天角兽，施法几乎不费吹灰之力。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 75f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50f)),
			null,
			"不止半人马",
			"不止半人马",
			"CENTAUR_BASIC",
			"CENTAUR_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 2)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 2)),
			PresetColour.RACE_ALICORN,
			SubspeciesPreference.ONE_LOW,
			"任何拥有兽态四足下肢，且拥有翅膀和独角的马类，都被归类为半天角兽人。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.ONE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public boolean isNonBiped() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			sb.append(body.getHorn().setType(target, HornType.HORSE_STRAIGHT));
			sb.append(body.getHorn().setHornRows(target, 1));
			sb.append(body.getHorn().setHornsPerRow(target, 1));
			sb.append(body.getHorn().setHornLength(target, HornLength.TWO_LONG.getMedianValue()));
			sb.append(body.getLeg().setType(target, LegType.HORSE_MORPH));
			if(target!=null && target.getBody()!=null) {
				sb.append(target.setLegConfiguration(LegType.HORSE_MORPH, LegConfiguration.QUADRUPEDAL, true));
			} else {
				LegType.HORSE_MORPH.applyLegConfigurationTransformation(body, LegConfiguration.QUADRUPEDAL, true);
			}

			sb.append(body.setWingType(target, WingType.FEATHERED));
			sb.append(body.setWingSize(target, WingSize.FOUR_HUGE.getValue()));

			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			sb.append(body.getPenis().getTesticle().getCum().addFluidModifier(target, FluidModifier.MUSKY));
			sb.append(body.getVagina().getGirlcum().addFluidModifier(target, FluidModifier.MUSKY));

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"恶魔半天角兽人",
					"恶魔半天角兽人",
					"恶魔半天角兽人",
					"恶魔半天角兽人",
					"恶魔半天角兽人",
					"恶魔半天角兽人"};
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH && body.getLeg().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				if(body.getWing().getType()==WingType.FEATHERED
						&& !body.getHorn().getType().equals(HornType.NONE)
						&& body.getHorn().getHornRows()==1
						&& body.getHorn().getHornsPerRow()==1) {
					return 1200;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies HORSE_MORPH_ZEBRA = new AbstractSubspecies(false,
			18000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorphZebra",
			"statusEffects/race/raceBackgroundZebra",
			"斑马化形",
			"斑马化形",
			"斑马男",
			"斑马女",
			"斑马男",
			"斑马女",
			new FeralAttributes(
					"斑马",
					"斑马",
					"公斑马",
					"母斑马",
					"公斑马",
					"母斑马",
					LegConfiguration.QUADRUPEDAL,
					130,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"尽管[npc.namePos]的身体力大无比，速度极快，但[npc.sheIs]却并不是世上最聪明的种族，并且在驾驭奥术时也通常十分艰难。"
					+ "[npc.She]对于天然火焰和奥术火焰都有极高的抗性。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 20f),
					new Value<>(Attribute.RESISTANCE_FIRE, 5f)),
			null,
			"马类百科全书",
			"马类百科全书",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.BASE_BLACK,
			SubspeciesPreference.ONE_LOW,
			"一种人形的马，长着黑白色条纹的皮毛，当下肢似人时称为“斑马化形”，而下肢呈现出大型兽态斑马的样子时称为“半斑马人”。"
					+ "若要被判定为斑马化形，角色必须是马化形，并且拥有黑白条纹的皮毛，以及斑马化形的尾巴。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			sb.append(body.getHorn().setType(target, HornType.NONE));
			sb.append(body.getWing().setType(target, WingType.NONE));

			sb.append(body.setCovering(target, BodyCoveringType.HORSE_HAIR, new Covering(BodyCoveringType.HORSE_HAIR, CoveringPattern.STRIPED, CoveringModifier.SHORT, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_HORSE_HAIR, new Covering(BodyCoveringType.HAIR_HORSE_HAIR, CoveringPattern.NONE, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_EBONY, false, PresetColour.SKIN_EBONY, false)));
			body.updateCoverings(true, true, true, true);
			
			if(body.getTail().getType()==TailType.HORSE_MORPH) {
				sb.append(body.getTail().setType(target, TailType.HORSE_MORPH_ZEBRA));
			}
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"斑马梦魇",
					"斑马梦魇",
					"斑马梦魇",
					"斑马梦魇",
					"斑马梦魇",
					"斑马梦魇"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "斑马梦魇", false, false),
					applyNonBipedNameChange(body, "斑马梦魇", false, true),
					applyNonBipedNameChange(body, "斑马梦魇", false, false),
					applyNonBipedNameChange(body, "斑马梦魇", true, false),
					applyNonBipedNameChange(body, "斑马梦魇", false, true),
					applyNonBipedNameChange(body, "斑马梦魇", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				AbstractBodyCoveringType horseHair = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_HAIR):BodyCoveringType.HORSE_HAIR;
				Colour zebraPrimary = body.getCoverings().get(horseHair).getPrimaryColour();
				Colour zebraSecondary = body.getCoverings().get(horseHair).getSecondaryColour();
				if((((zebraPrimary==PresetColour.COVERING_BLACK || zebraPrimary==PresetColour.COVERING_JET_BLACK) && zebraSecondary==PresetColour.COVERING_WHITE)
						|| (zebraPrimary==PresetColour.COVERING_WHITE && (zebraSecondary==PresetColour.COVERING_BLACK || zebraSecondary==PresetColour.COVERING_JET_BLACK)))
					&& body.getTail().getType()==TailType.HORSE_MORPH_ZEBRA) {
//						return 125;
						return 1500;// Zebra-morphs should override centaur types
					}
			}
			return 0;
		}
	};

	public static AbstractSubspecies HORSE_MORPH_DONKEY = new AbstractSubspecies(false,
			12000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"驴化形",
			"驴化形",
			"驴男",
			"驴女",
			"驴男",
			"驴女",
			new FeralAttributes(
					"驴",
					"驴",
					"公驴",
					"母驴",
					"公驴",
					"母驴",
					LegConfiguration.QUADRUPEDAL,
					120,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"尽管[npc.namePos]的身体力大无比，耐力极佳，但[npc.sheIs]在驾驭奥术时通常十分艰难。"
					+ "[npc.She]还拥有天生的知识，能够有力地对抗犬类。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 20f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.DOG_MORPH), 25f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.WOLF_MORPH), 25f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.FOX_MORPH), 25f)),
			null,
			"马类百科全书",
			"马类百科全书",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 15),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.CLOTHING_DESATURATED_BROWN,
			SubspeciesPreference.ONE_LOW,
			"一种人形的驴，当下肢似人时称为“驴化形”，而当下肢呈现出大型兽态驴的样子时称为“半驴人”。"
					+ "若要被判定为驴化形，角色必须是马化形，并且拥有较高的上竖耳朵。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.THREE)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			sb.append(body.getHorn().setType(target, HornType.NONE));
			sb.append(body.getWing().setType(target, WingType.NONE));

			if(Math.random()<0.75f) { // 75% of donkey morphs are the classic brown with white markings:
				sb.append(body.setCovering(target, BodyCoveringType.HORSE_HAIR, new Covering(BodyCoveringType.HORSE_HAIR, CoveringPattern.MARKED, CoveringModifier.SHORT, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false)));
				sb.append(body.setCovering(target, BodyCoveringType.HAIR_HORSE_HAIR, new Covering(BodyCoveringType.HAIR_HORSE_HAIR, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false)));
				sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_DARK, false, PresetColour.SKIN_DARK, false)));
			}
			body.updateCoverings(true, true, true, true);
			
			if(body.getEar().getType()==EarType.HORSE_MORPH) {
				sb.append(body.getEar().setType(target, EarType.HORSE_MORPH_UPRIGHT));
			}
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"驴梦魇",
					"驴梦魇",
					"驴梦魇",
					"驴梦魇",
					"驴梦魇",
					"驴梦魇"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "驴梦魇", false, false),
					applyNonBipedNameChange(body, "驴梦魇", false, true),
					applyNonBipedNameChange(body, "驴梦魇", false, false),
					applyNonBipedNameChange(body, "驴梦魇", true, false),
					applyNonBipedNameChange(body, "驴梦魇", false, true),
					applyNonBipedNameChange(body, "驴梦魇", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				if(body.getEar().getType()==EarType.HORSE_MORPH_UPRIGHT) {
//					return 110; // Less than zebra
					return 1400;// Donkey-morphs should override centaur types
				}
			}
			return 0;
		}
		@Override
		public Map<PersonalityTrait, Float> getPersonalityTraitChances() {
			Map<PersonalityTrait, Float> map = super.getPersonalityTraitChances();
			map.put(PersonalityTrait.BRAVE, 0.5f);// Donkeys do not act like typical prey animals
			return map;
		}
	};
	
	public static AbstractSubspecies REINDEER_MORPH = new AbstractSubspecies(true,
			18000,
			"innoxia_race_reindeer_rudolphs_egg_nog",
			"innoxia_race_reindeer_sugar_cookie",
			"statusEffects/race/raceReindeerMorph",
			"statusEffects/race/raceBackground",
			"驯鹿化形",
			"驯鹿化形",
			"驯鹿男",
			"驯鹿女",
			"驯鹿男",
			"驯鹿女",
			new FeralAttributes(
					"驯鹿",
					"驯鹿",
					"公驯鹿",
					"母驯鹿",
					"公驯鹿",
					"母驯鹿",
					LegConfiguration.QUADRUPEDAL,
					130,
					0,
					1,
					1,
					4, false),
			Nocturnality.DIURNAL,
			"[npc.NamePos]的身体非常适合地狱自然和奥术寒冷，并且十分坚韧强壮。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 2f),
					new Value<>(Attribute.RESISTANCE_ICE, 5f)),
			null,
			"驯鹿的迁徙",
			"驯鹿的迁徙",
			"REINDEER_MORPH_BASIC",
			"REINDEER_MORPH_ADVANCED",
			Race.REINDEER_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_REINDEER_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的驯鹿，当下肢为双足时称为“驯鹿化形”，而下肢呈现出兽态驯鹿的样子时称为“半驯鹿人”。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE),
					new Value<>(WorldRegion.SNOW, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.THREE)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.REINDEER_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	// REPTILE:
	public static AbstractSubspecies ALLIGATOR_MORPH = new AbstractSubspecies(true,
			10000,
			"innoxia_race_alligator_swamp_water",
			"innoxia_race_alligator_gators_gumbo",
			"statusEffects/race/raceGatorMorph",
			"statusEffects/race/raceBackground",
			"鳄化形",
			"鳄化形",
			"鳄男",
			"鳄女",
			"鳄男",
			"鳄女",
			new FeralAttributes(
					"鳄鱼",
					"鳄鱼",
					LegConfiguration.QUADRUPEDAL,
					false,
					180,
					0,
					1,
					0,
					1, false),
			Nocturnality.NOCTURNAL,
			"[npc.NamePos]的身体极其坚韧，且反应迅速，其力量也足以在多数情况下发动突然袭击。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f)),
			null,
			"和鳄鱼搏斗",
			"和鳄鱼搏斗",
			"ALLIGATOR_MORPH_BASIC",
			"ALLIGATOR_MORPH_ADVANCED",
			Race.ALLIGATOR_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_ALLIGATOR_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的鳄鱼，当下肢似人时成为“鳄鱼化形”，而下肢呈现出大型兽态鳄鱼的样子时称为“半鳄鱼人”。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.RIVER, SubspeciesSpawnRarity.TWO)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ALLIGATOR_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	// RODENTS:
	public static AbstractSubspecies SQUIRREL_MORPH = new AbstractSubspecies(true,
			6000,
			"innoxia_race_squirrel_squirrel_java",
			"innoxia_race_squirrel_round_nuts",
			"statusEffects/race/raceSquirrelMorph",
			"statusEffects/race/raceBackground",
			"松鼠化形",
			"松鼠化形",
			"松鼠男",
			"松鼠女",
			"松鼠男",
			"松鼠女",
			new FeralAttributes(
					"松鼠",
					"松鼠",
					LegConfiguration.QUADRUPEDAL,
					15,
					0,
					1,
					4,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.NameIsFull]十分机敏灵巧，其强壮的[npc.legs]能够跳出很长一段距离。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"追逐松鼠",
			"追逐松鼠",
			"SQUIRREL_MORPH_BASIC",
			"SQUIRREL_MORPH_ADVANCED",
			Race.SQUIRREL_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_SQUIRREL_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的松鼠，当下肢似人时称为“松鼠化形”，而当下肢呈现出大型兽态松鼠的样子时称为“半松鼠人”。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.SQUIRREL_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	//MOUSE_MORPH(Race.MOUSE_MORPH.getName(), Race.MOUSE_MORPH, RacialBody.MOUSE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.MOUSE_MORPH.getName()),
	
	public static AbstractSubspecies RAT_MORPH = new AbstractSubspecies(true,
			6000,
			"innoxia_race_rat_black_rats_rum",
			"innoxia_race_rat_brown_rats_burger",
			"statusEffects/race/raceRatMorph",
			"statusEffects/race/raceBackground",
			"鼠化形",
			"鼠化形",
			"鼠男",
			"鼠女",
			"鼠男",
			"鼠女",
			new FeralAttributes(
					"老鼠",
					"老鼠",
					LegConfiguration.QUADRUPEDAL,
					15,
					0,
					1,
					6,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NamePos]的身体能够适应极端的恶劣环境，并且对于奥术毒素抗性极佳，亲和力也很强。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 10f),
					new Value<>(Attribute.DAMAGE_POISON, 15f),
					new Value<>(Attribute.RESISTANCE_POISON, 5f)),
			null,
			"纷乱之源",
			"纷乱之源",
			"RAT_MORPH_BASIC",
			"RAT_MORPH_ADVANCED",
			Race.RAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_RAT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的老鼠，当下肢拟人时被称为“鼠化形”，而当下肢呈现出大型兽态老鼠的样子时则称为“半鼠人”。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.FIVE)), Util.newHashMapOfValues(
							new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.RAT_MORPH) {
				return 100;
			}
			return 0;
		}
	};

	public static AbstractSubspecies RABBIT_MORPH = new AbstractSubspecies(true,
			12000,
			"innoxia_race_rabbit_bunny_juice",
			"innoxia_race_rabbit_bunny_carrot_cake",
			"statusEffects/race/raceRabbitMorph",
			"statusEffects/race/raceBackground",
			"兔化形",
			"兔化形",
			"兔男",
			"兔女",
			"兔男",
			"兔女",
			new FeralAttributes(
					"兔子",
					"兔子",
					LegConfiguration.QUADRUPEDAL,
					20,
					0,
					1,
					5,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull]十分机敏灵巧，短时间内能够爆发出极快的速度。"
					+ "[npc.Her]的身体会尽可能多地生育后代，无论[npc.she]是否愿意。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 5f),
					new Value<>(Attribute.FERTILITY, 50f),
					new Value<>(Attribute.VIRILITY, 50f)),
			null,
			"兔子产仔",
			"兔子产仔",
			"RABBIT_MORPH_BASIC",
			"RABBIT_MORPH_ADVANCED",
			Race.RABBIT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_RABBIT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的兔子，当下肢拟人时被称为“兔化形”，而当下肢呈现出大型兽态兔子的样子时则称为“半兔人”。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			if(body.isFeminine() && body.getRaceStage()==RaceStage.GREATER && Math.random()<0.1f) {
				sb.append(body.getHair().setNeckFluff(target, true));
			}
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"加卡洛普",
					"加卡洛普",
					"加卡洛普",
					"加卡洛普",
					"加卡洛普",
					"加卡洛普"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "加卡洛普", false, false),
					applyNonBipedNameChange(body, "加卡洛普", false, true),
					applyNonBipedNameChange(body, "加卡洛普", false, false),
					applyNonBipedNameChange(body, "加卡洛普", true, false),
					applyNonBipedNameChange(body, "加卡洛普", false, true),
					applyNonBipedNameChange(body, "加卡洛普", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.RABBIT_MORPH) {
				return 100;
			}
			return 0;
		}
	};

	public static AbstractSubspecies RABBIT_MORPH_LOP = new AbstractSubspecies(false,
			12000,
			"innoxia_race_rabbit_bunny_juice",
			"innoxia_race_rabbit_bunny_carrot_cake",
			"statusEffects/race/raceRabbitLopMorph",
			"statusEffects/race/raceBackground",
			"垂耳兔化形",
			"垂耳兔化形",
			"垂耳兔男",
			"垂耳兔女",
			"垂耳兔男",
			"垂耳兔女",
			new FeralAttributes(
					"垂耳兔",
					"垂耳兔",
					LegConfiguration.QUADRUPEDAL,
					20,
					0,
					1,
					5,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull]十分机敏灵巧，短时间内能够爆发出极快的速度。"
					+ "[npc.Her]的身体会尽可能多地生育后代，无论[npc.she]是否愿意。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 5f),
					new Value<>(Attribute.FERTILITY, 50f),
					new Value<>(Attribute.VIRILITY, 50f)),
			null,
			"兔子产仔",
			"兔子产仔",
			"RABBIT_MORPH_BASIC",
			"RABBIT_MORPH_ADVANCED",
			Race.RABBIT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_RABBIT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一只的人形兔，拥有下垂的耳朵而不是常见的竖立耳朵。"
					+ "当下肢拟人时称为“垂耳兔化形”，而当下肢呈现出大型兽态垂耳兔的样子时称为“半垂耳兔人”。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			if(body.getEar().getType()==EarType.RABBIT_MORPH) {
				sb.append(body.getEar().setType(target, EarType.RABBIT_MORPH_FLOPPY));
			}
			if(body.isFeminine() && body.getRaceStage()==RaceStage.GREATER && Math.random()<0.1f) {
				sb.append(body.getHair().setNeckFluff(target, true));
			}

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"加卡洛普",
					"加卡洛普",
					"加卡洛普",
					"加卡洛普",
					"加卡洛普",
					"加卡洛普"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "加卡洛普", false, false),
					applyNonBipedNameChange(body, "加卡洛普", false, true),
					applyNonBipedNameChange(body, "加卡洛普", false, false),
					applyNonBipedNameChange(body, "加卡洛普", true, false),
					applyNonBipedNameChange(body, "加卡洛普", false, true),
					applyNonBipedNameChange(body, "加卡洛普", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.RABBIT_MORPH && body.getEar().getType()==EarType.RABBIT_MORPH_FLOPPY) {
				return 150;
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies BAT_MORPH = new AbstractSubspecies(true,
			10000,
			"innoxia_race_bat_fruit_bats_juice_box",
			"innoxia_race_bat_fruit_bats_salad",
			"statusEffects/race/raceBatMorph",
			"statusEffects/race/raceBackground",
			"蝙蝠化形",
			"蝙蝠化形",
			"蝙蝠男",
			"蝙蝠女",
			"蝙蝠男",
			"蝙蝠女",
			new FeralAttributes(
					"蝙蝠",
					"蝙蝠",
					LegConfiguration.WINGED_BIPED,
					30,
					0,
					1,
					1,
					1, false) {
				@Override
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.NOCTURNAL,
			"由于独特的回声定位能力，所有的蝙蝠化形都有尽可能多说话的天性。"
					+ "正因如此，[npc.Name]会在脑海中不断预演对话，在与人见面前，就能想出新奇的方式吸引对方。", 
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"飞狐",
			"飞狐'",
			"BAT_MORPH_BASIC",
			"BAT_MORPH_ADVANCED",
			Race.BAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_BAT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的双足蝙蝠。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.SUBMISSION, SubspeciesSpawnRarity.TWO),
				new Value<>(WorldType.BAT_CAVERNS, SubspeciesSpawnRarity.TEN),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.BAT_MORPH) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	// AVIAN:
	public static AbstractSubspecies HARPY = new AbstractSubspecies(true,
			12000,
			"innoxia_race_harpy_harpy_perfume",
			"innoxia_race_harpy_bubblegum_lollipop",
			"statusEffects/race/raceHarpy",
			"statusEffects/race/raceBackground",
			"哈比",
			"哈比",
			"哈比",
			"哈比",
			"哈比",
			"哈比",
			new FeralAttributes(
					"天堂鸟",
					"天堂鸟",
					LegConfiguration.AVIAN,
					30,
					0,
					1,
					0,
					1,
					true) {
				@Override
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.DIURNAL,
			"[npc.NameIsFull]痴迷于自己的外表，为了尽可能地看起来有吸引力，[npc.she]认为花费一半醒着的时间来打扮自己也是很正常的。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"哈比全知道",
			"哈比全知道",
			"HARPY_BASIC",
			"HARPY_ADVANCED",
			Race.HARPY,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_HARPY,
			SubspeciesPreference.FOUR_ABUNDANT,
			"一种人形的双足鸟类。一般只有手臂、腿部、眼睛、耳朵和毛发为非人类样貌。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.TEN),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
				SubspeciesFlag.DISABLE_SPAWN_PREFERENCE,
				SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
		@Override
		public String getName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "啾";
			}
			return super.getName(body);
		}
		@Override
		public String getNamePlural(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "啾";
			}
			return super.getNamePlural(body);
		}
		@Override
		public String getSingularMaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "啾";
			}
			return super.getSingularMaleName(body);
		}
		@Override
		public String getSingularFemaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "啾";
			}
			return super.getSingularFemaleName(body);
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"怒兽",
					"怒兽",
					"怒兽",
					"怒兽",
					"怒兽",
					"怒兽"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "怒兽", false, false),
					applyNonBipedNameChange(body, "怒兽", false, true),
					applyNonBipedNameChange(body, "怒兽", false, false),
					applyNonBipedNameChange(body, "怒兽", true, false),
					applyNonBipedNameChange(body, "怒兽", false, true),
					applyNonBipedNameChange(body, "怒兽", true, true)
				};
			}
			
			return names;
		}
		@Override
		public String getNonBipedRaceName(Body body) {
			return "哈比";
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HARPY) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies HARPY_RAVEN = new AbstractSubspecies(false,
			14000,
			"innoxia_race_harpy_harpy_perfume",
			"innoxia_race_harpy_bubblegum_lollipop",
			"statusEffects/race/raceHarpy",
			"statusEffects/race/raceBackground",
			"渡鸦哈比",
			"渡鸦哈比",
			"渡鸦哈比",
			"渡鸦哈比",
			"渡鸦哈比",
			"渡鸦哈比",
			new FeralAttributes(
					"渡鸦",
					"渡鸦",
					LegConfiguration.AVIAN,
					60,
					0,
					1,
					0,
					1, false) {
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.DIURNAL,
			"[npc.NameIsFull]痴迷于自己的外表，为了尽可能地看起来有吸引力，[npc.she]认为花费一半醒着的时间来打扮自己也是很正常的。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"哈比全知道",
			"哈比全知道",
			"HARPY_BASIC",
			"HARPY_ADVANCED",
			Race.HARPY,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.BASE_BLACK,
			SubspeciesPreference.ONE_LOW,
			"一种人形的双足渡鸦，羽毛为漆黑色。一般只有手臂、腿部、眼睛、耳朵和毛发为非人类样貌。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.THREE),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
				SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			Colour ravenColour = PresetColour.COVERING_BLACK;
			if(Math.random()<0.5f) {
				ravenColour = PresetColour.COVERING_JET_BLACK;
			}
			sb.append(body.setCovering(target, BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, ravenColour, false, ravenColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, ravenColour, false, ravenColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_HARPY, new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, ravenColour, false, ravenColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HARPY_SKIN, new Covering(BodyCoveringType.HARPY_SKIN, CoveringPattern.NONE, PresetColour.SKIN_EBONY, false, PresetColour.SKIN_EBONY, false)));

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"怒兽",
					"怒兽",
					"怒兽",
					"怒兽",
					"怒兽",
					"怒兽"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "怒兽", false, false),
					applyNonBipedNameChange(body, "怒兽", false, true),
					applyNonBipedNameChange(body, "怒兽", false, false),
					applyNonBipedNameChange(body, "怒兽", true, false),
					applyNonBipedNameChange(body, "怒兽", false, true),
					applyNonBipedNameChange(body, "怒兽", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HARPY) {
				AbstractBodyCoveringType feathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
				
				if(body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_BLACK
						|| body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_JET_BLACK) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies HARPY_SWAN = new AbstractSubspecies(false,
			22000,
			"innoxia_race_harpy_harpy_perfume",
			"innoxia_race_harpy_bubblegum_lollipop",
			"statusEffects/race/raceHarpy",
			"statusEffects/race/raceBackground",
			"天鹅哈比",
			"天鹅哈比",
			"天鹅哈比",
			"天鹅哈比",
			"天鹅哈比",
			"天鹅哈比",
			new FeralAttributes(
					"天鹅",
					"天鹅",
					LegConfiguration.AVIAN,
					100,
					0,
					1,
					0,
					1, false) {
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.DIURNAL,
			"[npc.NameIsFull]痴迷于自己的外表，为了尽可能地看起来有吸引力，[npc.she]认为花费一半醒着的时间来打扮自己也是很正常的。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"哈比全知道",
			"哈比全知道",
			"HARPY_BASIC",
			"HARPY_ADVANCED",
			Race.HARPY,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.BASE_WHITE,
			SubspeciesPreference.ONE_LOW,
			"一种人形的双足天鹅，拥有白色的羽毛，以及黑檀色或灰色的腿部皮肤。一般只有手臂、腿部、眼睛、耳朵和毛发为非人类样貌。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.RIVER, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.THREE),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
				SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			sb.append(body.setCovering(target, BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_HARPY, new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HARPY_SKIN, new Covering(BodyCoveringType.HARPY_SKIN, CoveringPattern.NONE, PresetColour.SKIN_EBONY, false, PresetColour.SKIN_EBONY, false)));

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"怒兽",
					"怒兽",
					"怒兽",
					"怒兽",
					"怒兽",
					"怒兽"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "怒兽", false, false),
					applyNonBipedNameChange(body, "怒兽", false, true),
					applyNonBipedNameChange(body, "怒兽", false, false),
					applyNonBipedNameChange(body, "怒兽", true, false),
					applyNonBipedNameChange(body, "怒兽", false, true),
					applyNonBipedNameChange(body, "怒兽", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HARPY) {
				AbstractBodyCoveringType feathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
				AbstractBodyCoveringType legSkin = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_SKIN):BodyCoveringType.HARPY_SKIN;
				Colour legColour = body.getCoverings().get(legSkin).getPrimaryColour();
				
				if(body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_WHITE
						&& (legColour==PresetColour.SKIN_GREY || legColour==PresetColour.SKIN_EBONY)) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
//	public static AbstractSubspecies HARPY_BALD_EAGLE = new AbstractSubspecies(false,
//			16000,
//			"innoxia_race_harpy_harpy_perfume",
//			"innoxia_race_harpy_bubblegum_lollipop",
//			"statusEffects/race/raceHarpy",
//			"statusEffects/race/raceBackground",
//			"bald-eagle-harpy",
//			"bald-eagle-harpies",
//			"bald-eagle-harpy",
//			"bald-eagle-harpy",
//			"bald-eagle-harpies",
//			"bald-eagle-harpies",
//			new FeralAttributes(
//					"bald-eagle",
//					"bald-eagles",
//					LegConfiguration.AVIAN,
//					90,
//					0,
//					1,
//					0,
//					1,
//					true) {
//				public boolean isArmsOrWingsPresent() {
//					return true;
//				}
//			},
//			"[npc.NameIsFull] obsessed with [npc.her] appearance, and wouldn't think it unusual for someone to want to spend at least half of their waking hours preening themselves in order to look as attractive as possible.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
//					new Value<>(Attribute.DAMAGE_LUST, 15f)),
//			null,
//			"All About Harpies",
//			"All About Harpies'",
//			"HARPY_BASIC",
//			"HARPY_ADVANCED",
//			Race.HARPY,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 2),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 2),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.BASE_GREY_LIGHT,
//			SubspeciesPreference.ONE_LOW,
//			"An anthropomorphic, bipedal bald eagle, dark brown feathers covering their body and white feathers on their head. Typically only possessing non-human arms, legs, eyes, ears, and hair.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE_UNCOMMON),
//					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.THREE_UNCOMMON),
//					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TWO_RARE)),
//			Util.newHashMapOfValues(
//					new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.ONE_VERY_RARE),
//					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null, Util.newArrayListOfValues(
//					SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			sb.append(body.setCovering(target, BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_BROWN_DARK, false));
//			sb.append(body.setCovering(target, BodyCoveringType.HAIR_HARPY, new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false));
//			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_BROWN_DARK, false));
//			sb.append(body.setCovering(target, BodyCoveringType.HARPY_SKIN, new Covering(BodyCoveringType.HARPY_SKIN, CoveringPattern.NONE, PresetColour.SKIN_YELLOW, false, PresetColour.SKIN_YELLOW, false));
//		}
//		@Override
//		public String[] getHalfDemonName(GameCharacter character) {
//			String[] names = new String[] {
//					"fury",
//					"furies",
//					"fury",
//					"fury",
//					"furies",
//					"furies"};
//			
//			if(character!=null && !character.getHalfDemonSubspecies().isNonBiped()) {
//				names = new String[] {
//					applyNonBipedNameChange(character, "fury", false, false),
//					applyNonBipedNameChange(character, "fury", false, true),
//					applyNonBipedNameChange(character, "fury", false, false),
//					applyNonBipedNameChange(character, "fury", true, false),
//					applyNonBipedNameChange(character, "fury", false, true),
//					applyNonBipedNameChange(character, "fury", true, true)
//				};
//			}
//			
//			return names;
//		}
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.HARPY) {
//				AbstractBodyCoveringType feathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
//				AbstractBodyCoveringType headFeathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.HAIR):BodyCoveringType.HAIR_HARPY;
//				
//				if(body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_BROWN_DARK
//						&& body.getCoverings().get(headFeathers).getPrimaryColour()==PresetColour.COVERING_WHITE) {
//					return 150;
//				}
//			}
//			return 0;
//		}
//		@Override
//		public String getPathName() {
//			return "res/race/neverLucky/harpy/bald_eagle";
//		}
//		@Override
//		public Colour getSecondaryColour() {
//			return PresetColour.BASE_PITCH_BLACK;
//		}
//		@Override
//		public Colour getTertiaryColour() {
//			return PresetColour.BASE_YELLOW;
//		}
//	};
	
	public static AbstractSubspecies HARPY_PHOENIX = new AbstractSubspecies(false,
			50000,
			"innoxia_race_harpy_harpy_perfume",
			"innoxia_race_harpy_bubblegum_lollipop",
			"statusEffects/race/raceHarpy",
			"statusEffects/race/raceBackgroundPhoenix",
			"不死鸟哈比",
			"不死鸟哈比",
			"不死鸟哈比",
			"不死鸟哈比",
			"不死鸟哈比",
			"不死鸟哈比",
			new FeralAttributes(
					"不死鸟",
					"不死鸟",
					LegConfiguration.AVIAN,
					90,
					0,
					1,
					0,
					1,
					true) {
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.DIURNAL,
			"[npc.NameIsFull]与其他哈比一样痴迷于自己的外表，[npc.herHim]还拥有操控奥术的天赋，可以相对轻松地学习和施放法术。"
				+ "特别是[npc.she]对奥术火焰有着异常高的亲和力……",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 10f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 10f),
					new Value<>(Attribute.DAMAGE_FIRE, 75f),
					new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"哈比全知道",
			"哈比全知道",
			"HARPY_BASIC",
			"HARPY_ADVANCED",
			Race.HARPY,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.BASE_ORANGE,
			SubspeciesPreference.ONE_LOW,
			"一种存在于神话中的鸟类，人形双足，羽毛通常为散发着光芒的红色、橘色或黄色，或者就直接由奥术火焰构成。"
					+ "这个种族十分稀有，一般只有手臂、腿部、眼睛、耳朵和毛发为非人类样貌。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.VOLCANO, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.ONE)), null, Util.newArrayListOfValues(
					SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			CoveringPattern pattern = CoveringPattern.OMBRE;
			if(Math.random()<0.5f) {
				pattern = CoveringPattern.HIGHLIGHTS;
			}
			sb.append(body.setCovering(target, BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, pattern, PresetColour.COVERING_ORANGE, true, PresetColour.COVERING_YELLOW, true)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_HARPY, new Covering(BodyCoveringType.HAIR_HARPY, pattern, PresetColour.COVERING_RED, true, PresetColour.COVERING_ORANGE, true)));
			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_RED, true, PresetColour.COVERING_RED, true)));
			sb.append(body.setCovering(target, BodyCoveringType.HARPY_SKIN, new Covering(BodyCoveringType.HARPY_SKIN, CoveringPattern.NONE, PresetColour.SKIN_ORANGE, false, PresetColour.SKIN_ORANGE, false)));

			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"不死鸟怒兽",
					"不死鸟怒兽",
					"不死鸟怒兽",
					"不死鸟怒兽",
					"不死鸟怒兽",
					"不死鸟怒兽"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "不死鸟怒兽", false, false),
					applyNonBipedNameChange(body, "不死鸟怒兽", false, true),
					applyNonBipedNameChange(body, "不死鸟怒兽", false, false),
					applyNonBipedNameChange(body, "不死鸟怒兽", true, false),
					applyNonBipedNameChange(body, "不死鸟怒兽", false, true),
					applyNonBipedNameChange(body, "不死鸟怒兽", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HARPY) {
				AbstractBodyCoveringType feathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
				
				if((body.getCoverings().get(feathers).isPrimaryGlowing()
						&& (body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_RED
							|| body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_ORANGE
							|| body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_YELLOW))
					|| body.getBodyMaterial()==BodyMaterial.FIRE) {
					return 200;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};

	// ********** SPECIAL SUBSPECIES ********** //
	
	// SLIMES:
	public static AbstractSubspecies SLIME = new AbstractSubspecies(true,
			10000,
			"innoxia_race_slime_slime_quencher",
			"innoxia_race_slime_biojuice_canister",
			"statusEffects/race/raceSlime",
			"statusEffects/race/raceBackgroundSlime",
			"史莱姆",
			"史莱姆",
			"史莱姆男",
			"史莱姆女",
			"史莱姆男",
			"史莱姆女",
			null,
			Nocturnality.DIURNAL,
			"[npc.nameIsFull]借助其柔软粘滑的身体，能够完全免疫物理伤害，但从而在徒手时也无法造成太多伤害。"
					+ "[npc.She]也能随意变化形体，随心所欲地成为任何想成为的形态。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 25f)),
			Util.newArrayListOfValues(),
			"史莱姆趣闻",
			"史莱姆趣闻",
			"SLIME_BASIC",
			"SLIME_ADVANCED",
			Race.SLIME,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_SLIME,
			SubspeciesPreference.FOUR_ABUNDANT,
			"完全由粘液构成的生物，原本心脏的位置悬浮着一颗固态的核心。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.BAT_CAVERNS, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public AbstractItemType getTransformativeItem(GameCharacter owner) {
			if(getTransformativeItemId()==null || getTransformativeItemId().isEmpty()) {
				return null;	
			}
			if(owner!=null && !owner.hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				return ItemType.getItemTypeFromId("innoxia_race_slime_slime_quencher");
			}
			return ItemType.getItemTypeFromId(getTransformativeItemId());
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			// Slime subspecies are set in the Main.game.getCharacterUtils().generateBody() method
			body.setBodyMaterial(BodyMaterial.SLIME);
			return ""; // Don't append the slime change as that will surely be handled elsewhere...
		}

		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if(character!=null) {
				AbstractSubspecies coreSubspecies = character.getBody().getFleshSubspecies();
				if(character.getSubspeciesOverrideRace()==Race.DEMON) {
					return UtilText.parse(character,
							"[npc.nameIsFull]借助其柔软粘滑的身体，能够完全免疫物理伤害，但从而在徒手时也无法造成太严重的伤害。"
							+ "[npc.Her]的史莱姆核心在强大的力量下脉动着，这意味着[npc.sheIs]是一只真正的恶魔史莱姆。");
				} else if(coreSubspecies==Subspecies.DEMON) {
					return UtilText.parse(character,
							"[npc.nameIsFull]借助其柔软粘滑的身体，能够完全免疫物理伤害，但从而在徒手时也无法造成太严重的伤害。"
							+ "尽管[npc.she]看上去像个恶魔，但只不过是拟态了外形而已……");
				}
			}
			return super.getStatusEffectDescription(character);
		}
		
		@Override
		public String getName(Body body) {
			if(body == null) {
				return super.getName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getName(body)+"拟态史莱姆";
			}
			return coreSubspecies.getName(body)+"史莱姆";
		}
		
		@Override
		public String getNamePlural(Body body) {
			if(body ==null) {
				return super.getNamePlural(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getNamePlural(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getName(body)+"拟态史莱姆";
			}
			return coreSubspecies.getName(body)+"史莱姆";
		}

		@Override
		public String getSingularMaleName(Body body) {
			if(body ==null) {
				return super.getSingularMaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularMaleName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getSingularMaleName(body)+"拟态史莱姆";
			}
			return coreSubspecies.getSingularMaleName(body)+"史莱姆";
		}

		@Override
		public String getSingularFemaleName(Body body) {
			if(body ==null) {
				return super.getSingularFemaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularFemaleName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getSingularFemaleName(body)+"拟态史莱姆";
			}
			return coreSubspecies.getSingularFemaleName(body)+"史莱姆";
		}

		@Override
		public String getPluralMaleName(Body body) {
			if(body ==null) {
				return super.getPluralMaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralMaleName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getSingularMaleName(body)+"拟态史莱姆";
			}
			return coreSubspecies.getSingularMaleName(body)+"史莱姆";
		}

		@Override
		public String getPluralFemaleName(Body body) {
			if(body ==null) {
				return super.getPluralFemaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralFemaleName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getSingularFemaleName(body)+"拟态史莱姆";
			}
			return coreSubspecies.getSingularFemaleName(body)+"史莱姆";
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getSlimeSVGString(null);
			}
			return character.getBody().getFleshSubspecies().getSlimeSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getSVGStringDesaturated(null);
			}
			return character.getBody().getFleshSubspecies().getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.SLIME) {
				return 10_000; // Slimes should always be slime, no matter their underlying subspecies
			}
			return 0;
		}
		public FeralAttributes getFeralAttributes(Body body) {
			if(body==null) {
				return super.getFeralAttributes(body);
			}
			return body.getFleshSubspecies().getFeralAttributes(body);
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	// DOLLS:
	public static AbstractSubspecies DOLL = new AbstractSubspecies(true,
			10000,
			"innoxia_race_doll_silic_oil",
			null,
			"statusEffects/race/raceDoll",
			"statusEffects/race/raceBackgroundDoll",
			"玩偶",
			"玩偶",
			"玩偶",
			"玩偶",
			"玩偶",
			"玩偶",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.NameIsFull]是一个外形极其逼真的自动性爱玩偶，是在“洛维耶纳的奢侈品店”中由注入奥术的硅胶制成。"
					+ "[npc.Her]人工造就的身体和奥术驱动的自动原理使[npc.herHim]特性多变。",
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"[style.boldExcellent(玩偶独有天赋树)]",
					"[style.boldGood(天赋能够带来大量效果)]"),
			"终极玩具",
			"终极玩具",
			"DOLL_BASIC",
			"DOLL_ADVANCED",
			Race.DOLL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_DOLL,
			SubspeciesPreference.ZERO_NONE,
			"一个栩栩如生的硅胶玩偶，已经注入附魔，能够移动、说话、遵从指令。",
			null,
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public AbstractItemType getTransformativeItem(GameCharacter owner) {
			return null;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			// Doll subspecies are set in the Main.game.getCharacterUtils().generateBody() method
			body.setBodyMaterial(BodyMaterial.SILICONE);
			body.getBreast().setMilk(new FluidMilk(FluidType.MILK_DOLL, false));
			body.getBreastCrotch().setMilk(new FluidMilk(FluidType.MILK_DOLL, true));
			body.getPenis().getTesticle().setCum(new FluidCum(FluidType.CUM_DOLL));
			body.getVagina().setGirlcum(new FluidGirlCum(FluidType.GIRL_CUM_DOLL));

			return "";
		}
		@Override
		public String getName(Body body) {
			if(body == null) {
				return super.getName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getName(body);
			}
			return coreSubspecies.getName(body)+"玩偶";
		}
		
		@Override
		public String getNamePlural(Body body) {
			if(body ==null) {
				return super.getNamePlural(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getNamePlural(body);
			}
			return coreSubspecies.getName(body)+"玩偶";
		}

		@Override
		public String getSingularMaleName(Body body) {
			if(body ==null) {
				return super.getSingularMaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularMaleName(body);
			}
			return coreSubspecies.getSingularMaleName(body)+"玩偶";
		}

		@Override
		public String getSingularFemaleName(Body body) {
			if(body ==null) {
				return super.getSingularFemaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularFemaleName(body);
			}
			return coreSubspecies.getSingularFemaleName(body)+"玩偶";
		}

		@Override
		public String getPluralMaleName(Body body) {
			if(body ==null) {
				return super.getPluralMaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralMaleName(body);
			}
			return coreSubspecies.getSingularMaleName(body)+"玩偶";
		}

		@Override
		public String getPluralFemaleName(Body body) {
			if(body ==null) {
				return super.getPluralFemaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralFemaleName(body);
			}
			return coreSubspecies.getSingularFemaleName(body)+"玩偶";
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getDollSVGString(null);
			}
			return character.getBody().getFleshSubspecies().getDollSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getDollSVGStringDesaturated(null);
			}
			return character.getBody().getFleshSubspecies().getDollSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOLL) {
				return 20_000; // Dolls should always be dolls, no matter their underlying subspecies
			}
			return 0;
		}
		public FeralAttributes getFeralAttributes(Body body) {
			if(body==null) {
				return super.getFeralAttributes(body);
			}
			return body.getFleshSubspecies().getFeralAttributes(body);
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	// ELEMENTALS:
	
	public static AbstractSubspecies ELEMENTAL_FIRE = new AbstractSubspecies(false,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundFire",
			"火元素",
			"火元素",
			"火元素",
			"火元素",
			"火元素",
			"火元素",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull]是召唤而来的元素体，当前绑定于烈火学派。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.DAMAGE_FIRE, 50f),
					new Value<>(Attribute.RESISTANCE_FIRE, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(无限制)]<b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>自我转化</b>"),
			"火元素",
			"火元素",
			"ELEMENTAL_FIRE_BASIC",
			"ELEMENTAL_FIRE_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_FIRE,
			SubspeciesPreference.FOUR_ABUNDANT, "绑定于烈火学派的奥术元素体。", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 50_000;
		}
		public AbstractItemType getBook() {
			return ItemType.getLoreBook(this);
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setBodyMaterial(BodyMaterial.FIRE);
			return "";
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_FIRE),
									this.getColour(character),
									this.getColour(character),
									this.getColour(character),
									"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_FIRE),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL && body.getBodyMaterial()==BodyMaterial.FIRE) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	public static AbstractSubspecies ELEMENTAL_EARTH = new AbstractSubspecies(false,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundEarth",
			"土元素",
			"土元素",
			"土元素",
			"土元素",
			"土元素",
			"土元素",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull]是召唤而来的元素体，当前绑定于大地学派。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 50f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 50f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(无限制)]<b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>自我转化</b>"),
			"土元素",
			"土元素",
			"ELEMENTAL_EARTH_BASIC",
			"ELEMENTAL_EARTH_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_EARTH,
			SubspeciesPreference.FOUR_ABUNDANT, "绑定于大地学派的奥术元素体。", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return ELEMENTAL_FIRE.getSubspeciesOverridePriority();
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setBodyMaterial(BodyMaterial.STONE);
			return "";
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_EARTH),
							this.getColour(character),
							this.getColour(character),
							this.getColour(character),
							"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_EARTH),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL
					&& (body.getBodyMaterial()==BodyMaterial.STONE || body.getBodyMaterial()==BodyMaterial.RUBBER || body.getBodyMaterial()==BodyMaterial.FLESH || body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	public static AbstractSubspecies ELEMENTAL_WATER = new AbstractSubspecies(false,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundWater",
			"水元素",
			"水元素",
			"水元素",
			"水元素",
			"水元素",
			"水元素",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull]是召唤而来的元素体，当前绑定于激流学派。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.DAMAGE_ICE, 50f),
					new Value<>(Attribute.RESISTANCE_ICE, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(无限制)]<b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>自我转化</b>"),
			"水元素",
			"水元素",
			"ELEMENTAL_WATER_BASIC",
			"ELEMENTAL_WATER_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_WATER,
			SubspeciesPreference.FOUR_ABUNDANT, "绑定于激流学派的奥术元素体。", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return ELEMENTAL_FIRE.getSubspeciesOverridePriority();
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setBodyMaterial(BodyMaterial.WATER);
			return "";
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_WATER),
									this.getColour(character),
									this.getColour(character),
									this.getColour(character),
									"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_WATER),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL && (body.getBodyMaterial()==BodyMaterial.WATER || body.getBodyMaterial()==BodyMaterial.ICE)) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	public static AbstractSubspecies ELEMENTAL_AIR = new AbstractSubspecies(false,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundAir",
			"风元素",
			"风元素",
			"风元素",
			"风元素",
			"风元素",
			"风元素",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull]是召唤而来的元素体，当前绑定于大气学派。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.DAMAGE_POISON, 50f),
					new Value<>(Attribute.RESISTANCE_POISON, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(无限制)]<b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>自我转化</b>"),
			"风元素",
			"风元素",
			"ELEMENTAL_AIR_BASIC",
			"ELEMENTAL_AIR_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_AIR,
			SubspeciesPreference.FOUR_ABUNDANT, "绑定于大气学派的奥术元素体。", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return ELEMENTAL_FIRE.getSubspeciesOverridePriority();
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setBodyMaterial(BodyMaterial.AIR);
			return "";
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_AIR),
									this.getColour(character),
									this.getColour(character),
									this.getColour(character),
									"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_AIR),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL && body.getBodyMaterial()==BodyMaterial.AIR) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	public static AbstractSubspecies ELEMENTAL_ARCANE = new AbstractSubspecies(true,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundArcane",
			"奥术元素",
			"奥术元素",
			"奥术元素",
			"奥术元素",
			"奥术元素",
			"奥术元素",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull]是召唤而来的元素体，当前绑定于奥术学派。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.DAMAGE_LUST, 50f),
					new Value<>(Attribute.RESISTANCE_LUST, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(无限制)]<b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>自我转化</b>"),
			"奥术元素",
			"奥术元素",
			"ELEMENTAL_ARCANE_BASIC",
			"ELEMENTAL_ARCANE_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_ARCANE,
			SubspeciesPreference.FOUR_ABUNDANT, "绑定于奥术学派的奥术元素体。", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return ELEMENTAL_FIRE.getSubspeciesOverridePriority();
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setBodyMaterial(BodyMaterial.ARCANE);
			return "";
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_ARCANE),
									this.getColour(character),
									this.getColour(character),
									this.getColour(character),
									"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_ARCANE),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL && body.getBodyMaterial()==BodyMaterial.ARCANE) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};


	public static List<AbstractSubspecies> allSubspecies;
	
	public static Map<AbstractSubspecies, String> subspeciesToIdMap = new HashMap<>();
	public static Map<String, AbstractSubspecies> idToSubspeciesMap = new HashMap<>();

	private static Map<WorldRegion, Map<AbstractSubspecies, SubspeciesSpawnRarity>> regionSpecies;
	private static Map<AbstractWorldType, Map<AbstractSubspecies, SubspeciesSpawnRarity>> worldSpecies;
	private static Map<AbstractPlaceType, Map<AbstractSubspecies, SubspeciesSpawnRarity>> placeSpecies;
	
	protected static Map<AbstractSubspecies, SubspeciesSpawnRarity> dominionStormImmuneSpecies;
	protected static Map<AbstractRace, List<AbstractSubspecies>> subspeciesFromRace;
	

	public static AbstractSubspecies getSubspeciesFromId(String id) {
		if(id.equalsIgnoreCase("CAT_MORPH_LEOPARD_SNOW")) {
			id = "innoxia_panther_subspecies_snow_leopard";
		} else if(id.equalsIgnoreCase("CAT_MORPH_LEOPARD")) {
			id = "innoxia_panther_subspecies_leopard";
		} else if(id.equalsIgnoreCase("CAT_MORPH_LION")) {
			id = "innoxia_panther_subspecies_lion";
		} else if(id.equalsIgnoreCase("CAT_MORPH_TIGER")) {
			id = "innoxia_panther_subspecies_tiger";
		} else if(id.equalsIgnoreCase("HARPY_BALD_EAGLE")) {
			id = "innoxia_raptor_subspecies_bald_eagle";
		}
		id = Util.getClosestStringMatch(id, idToSubspeciesMap.keySet());
		return idToSubspeciesMap.get(id);
	}
	
	public static String getIdFromSubspecies(AbstractSubspecies subspecies) {
		return subspeciesToIdMap.get(subspecies);
	}

	static {
		allSubspecies = new ArrayList<>();

		// Modded subspecies:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "subspecies", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("subspecies")) {
					try {
						AbstractSubspecies subspecies = new AbstractSubspecies(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("_race", "");
						allSubspecies.add(subspecies);
						subspeciesToIdMap.put(subspecies, id);
						idToSubspeciesMap.put(id, subspecies);
//						System.out.println("subspecies: "+id);
					} catch(Exception ex) {
						System.err.println("Loading modded subspecies failed at 'Subspecies'. File path: "+innerEntry.getValue().getAbsolutePath());
												ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// External res subspecies:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "subspecies", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("subspecies")) {
					try {
						AbstractSubspecies subspecies = new AbstractSubspecies(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("_race", "");
						allSubspecies.add(subspecies);
						subspeciesToIdMap.put(subspecies, id);
						idToSubspeciesMap.put(id, subspecies);
					} catch(Exception ex) {
						System.err.println("Loading subspecies failed at 'Subspecies'. File path: "+innerEntry.getValue().getAbsolutePath());
												ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Hard-coded:
		
		Field[] fields = Subspecies.class.getFields();
		
		for(Field f : fields){
			if (AbstractSubspecies.class.isAssignableFrom(f.getType())) {
				
				AbstractSubspecies subspecies;
				
				try {
					subspecies = ((AbstractSubspecies) f.get(null));

					subspeciesToIdMap.put(subspecies, f.getName());
					idToSubspeciesMap.put(f.getName(), subspecies);
					allSubspecies.add(subspecies);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		fields = LexiSubspecies.class.getFields();

		for(Field f : fields){
			if (AbstractSubspecies.class.isAssignableFrom(f.getType())) {

				AbstractSubspecies subspecies;

				try {
					subspecies = ((AbstractSubspecies) f.get(null));

					subspeciesToIdMap.put(subspecies, f.getName());
					idToSubspeciesMap.put(f.getName(), subspecies);
					allSubspecies.add(subspecies);

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		worldSpecies = new HashMap<>();
		regionSpecies = new HashMap<>();
		placeSpecies = new HashMap<>();
		dominionStormImmuneSpecies = new HashMap<>();
		subspeciesFromRace = new HashMap<>();

		for(AbstractSubspecies species : Subspecies.getAllSubspecies()) {
			subspeciesFromRace.putIfAbsent(species.getRace(), new ArrayList<>());
			subspeciesFromRace.get(species.getRace()).add(species);
			
			for(Entry<WorldRegion, SubspeciesSpawnRarity> type : species.getRegionLocations().entrySet()) {
				regionSpecies.putIfAbsent(type.getKey(), new HashMap<>());
				regionSpecies.get(type.getKey()).put(species, type.getValue());
				try {
					if(type.getKey()==WorldRegion.DOMINION
							&& (species.getRace()==Race.DEMON || species.getStatusEffectAttributeModifiers(null).get(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue())) {
						dominionStormImmuneSpecies.put(species, type.getValue());
					}
				} catch(Exception ex) {	
				}
			}
			
			for(Entry<AbstractWorldType, SubspeciesSpawnRarity> type : species.getWorldLocations().entrySet()) {
				worldSpecies.putIfAbsent(type.getKey(), new HashMap<>());
				worldSpecies.get(type.getKey()).put(species, type.getValue());
				
				try {
					if(type.getKey()==WorldType.DOMINION
							&& (species.getRace()==Race.DEMON || species.getStatusEffectAttributeModifiers(null).get(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue())) {
						dominionStormImmuneSpecies.put(species, type.getValue());
					}
				} catch(Exception ex) {	
				}
			}
			
			for(Entry<AbstractPlaceType, SubspeciesSpawnRarity> type : species.getPlaceLocations().entrySet()) {
				placeSpecies.putIfAbsent(type.getKey(), new HashMap<>());
				placeSpecies.get(type.getKey()).put(species, type.getValue());
			}
		}
		
//		for(AbstractSubspecies s : dominionStormImmuneSpecies.keySet()) {
//			System.out.println(s.getName(null));
//		}
		
		for(List<AbstractSubspecies> e : subspeciesFromRace.values()) {
			e.sort((s1, s2) -> s1.getName(null).compareTo(s2.getName(null)));
		}

		allSubspecies.sort((s1, s2) -> s1.getRace().getName(false).compareTo(s2.getRace().getName(false)));
	}
	
	public static List<AbstractSubspecies> getAllSubspecies() {
		return allSubspecies;
	}

	public static Map<AbstractSubspecies, SubspeciesSpawnRarity> getWorldSpecies(AbstractWorldType worldType, AbstractPlaceType placeType, boolean onlyCoreRaceSpecies, AbstractSubspecies... subspeciesToExclude) {
		return getWorldSpecies(worldType, placeType, onlyCoreRaceSpecies, true, subspeciesToExclude);
	}

	public static Map<AbstractSubspecies, SubspeciesSpawnRarity> getWorldSpecies(AbstractWorldType worldType, AbstractPlaceType placeType, boolean onlyCoreRaceSpecies, boolean includeRegionSpecies, AbstractSubspecies... subspeciesToExclude) {
		return getWorldSpecies(worldType, placeType, onlyCoreRaceSpecies, includeRegionSpecies, Arrays.asList(subspeciesToExclude));
	}
	
	/**
	 * @param worldType The WorldType from which to fetch Subspecies present.
	 * @param placeType The PlaceType from which to fetch Subspecies present. Can be passed in as null to ignore.
	 * @param onlyCoreRaceSpecies true if only core Subspecies should be returned. (e.g. Cat-morph would be returned, but not Lion-morph, Tiger-morph, etc.)
	 * @param includeRegionSpecies true if the species of the WorldRegion should be included.
	 * @param subspeciesToExclude Any Subspecies that should be excluded from the returned map.
	 * @return A weighted map of subspecies that can spawn in that world, region and/or place.
	 */
	public static Map<AbstractSubspecies, SubspeciesSpawnRarity> getWorldSpecies(AbstractWorldType worldType, AbstractPlaceType placeType, boolean onlyCoreRaceSpecies, boolean includeRegionSpecies, List<AbstractSubspecies> subspeciesToExclude) {
		worldSpecies.putIfAbsent(worldType, new HashMap<>());
		regionSpecies.putIfAbsent(worldType.getWorldRegion(), new HashMap<>());
		
		Map<AbstractSubspecies, SubspeciesSpawnRarity> map = new HashMap<>(worldSpecies.get(worldType));
		if (includeRegionSpecies) {
			for(Entry<AbstractSubspecies, SubspeciesSpawnRarity> regionEntry : regionSpecies.get(worldType.getWorldRegion()).entrySet()) {
				if(!map.containsKey(regionEntry.getKey())) {
					map.put(regionEntry.getKey(), regionEntry.getValue());
				}
			}
		}
		if(placeType!=null) {
			placeSpecies.putIfAbsent(placeType, new HashMap<>());
			regionSpecies.putIfAbsent(placeType.getWorldRegion(), new HashMap<>());
		    for(Entry<AbstractSubspecies, SubspeciesSpawnRarity> placeEntry : placeSpecies.get(placeType).entrySet()) {
		        if(!map.containsKey(placeEntry.getKey())) {
		            map.put(placeEntry.getKey(), placeEntry.getValue());
		        }
		    }
			if (includeRegionSpecies && regionSpecies.get(placeType.getWorldRegion())!=null) {
			    for(Entry<AbstractSubspecies, SubspeciesSpawnRarity> regionEntry : regionSpecies.get(placeType.getWorldRegion()).entrySet()) {
			        if(!map.containsKey(regionEntry.getKey())) {
			            map.put(regionEntry.getKey(), regionEntry.getValue());
			        }
			    }
			}
		}
		
		Map<AbstractSubspecies, SubspeciesSpawnRarity> filteredMap = new HashMap<>(map);
		if(onlyCoreRaceSpecies) {
			for(AbstractSubspecies sub : map.keySet()) {
				if(AbstractSubspecies.getMainSubspeciesOfRace(sub.getRace())!=sub) {
					filteredMap.remove(sub);
				}
			}
		}
		
		for(AbstractSubspecies sub : subspeciesToExclude) {
			filteredMap.remove(sub);
		}
		
		return filteredMap;
	}

	/**
	 * @param onlyCoreRaceSpecies true if only core Subspecies should be returned. (e.g. Cat-morph would be returned, but not caracal-morph, lynx-morph, etc.)
	 * @param subspeciesToExclude Any Subspecies that should be excluded from the returned map.
	 */
	public static Map<AbstractSubspecies, SubspeciesSpawnRarity> getDominionStormImmuneSpecies(boolean onlyCoreRaceSpecies, AbstractSubspecies... subspeciesToExclude) {
		Map<AbstractSubspecies, SubspeciesSpawnRarity> map = new HashMap<>(dominionStormImmuneSpecies);
		
		if(onlyCoreRaceSpecies) {
			for(AbstractSubspecies sub : dominionStormImmuneSpecies.keySet()) {
				if(AbstractSubspecies.getMainSubspeciesOfRace(sub.getRace())!=sub) {
					map.remove(sub);
				}
			}
		}
		
		for(AbstractSubspecies sub : subspeciesToExclude) {
			map.remove(sub);
		}
		
		return map;
	}

	/**
	 * @return All Subspecies which do not have an override, cannot self transform, and which spawn in Dominion, Submission, or Elis.
	 * <br/><b>NOTE:</b> This should be updated in the future to include common races for all areas of the game.
	 */
	public static List<AbstractSubspecies> getCommonSubspecies(Collection<AbstractSubspecies> subspeciesToExclude) {
		List<AbstractSubspecies> commonSubspecies = new ArrayList<>();

		for(AbstractSubspecies subspecies : getAllSubspecies()) {
			if(!subspeciesToExclude.contains(subspecies)
				&& subspecies.getSubspeciesOverridePriority()==0
				&& !subspecies.isAbleToSelfTransform()
				&& (subspecies.isAbleToNaturallySpawnInLocation(WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS)
					|| subspecies.isAbleToNaturallySpawnInLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_TUNNELS)
					|| subspecies.isAbleToNaturallySpawnInLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley")))) {
				commonSubspecies.add(subspecies);
			}
		}

		return commonSubspecies;
	}

	public static List<AbstractSubspecies> getSubspeciesOfRace(AbstractRace race) {
		if (subspeciesFromRace.containsKey(race))
			return subspeciesFromRace.get(race);
		else {
//			System.err.println("There is no subspecies of race : " + race.getName(false));
			return new ArrayList<>();
		}
	}
}