package com.lilithsthrone.LolificationProject.Lexi.character.race;

//
//import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.LolificationProject.Lexi.character.body.types.LexiTailType;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.*;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.*;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.race.*;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.91
 * @version 0.1.0
 * @author Lexi
 */
public class LexiSubspecies {


	public static final AbstractSubspecies TRUE_MANTICORE = new AbstractSubspecies(false,
			160000,
			"innoxia_race_panther_deep_roar",
			"innoxia_race_panther_panthers_delight",
			"race/innoxia/panther/subspecies/icon_lion",
			"race/innoxia/panther/subspecies/background_leopard",
			"真蝎狮",
			"真蝎狮",
			"真蝎狮雄性",
			"真蝎狮雌性",
			"真蝎狮雄性们",
			"真蝎狮雌性们",
			new FeralAttributes(
					"真蝎狮",
					"真蝎狮群",
					"真蝎狮",
					"真蝎狮",
					"真蝎狮群",
					"真蝎狮群",
					LegConfiguration.QUADRUPEDAL,
					80,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull] 极其凶猛，能用 [npc.her] 强壮的翅膀腾空而起。[npc.NameIsFull] 在力量与韧性的较量中几乎无人能敌。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 40f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 35f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null,
			"大猫",
			"大猫",
			"PANTHER_BASIC",
			"PANTHER_ADVANCED",
			Race.getRaceFromId("innoxia_panther"),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 4),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 3)),
			PresetColour.COVERING_AMBER,
			SubspeciesPreference.TWO_AVERAGE,
			"真蝎狮，以两足行走时被称为'蝎狮'，而当其下半身为一只通常体格庞大的野生豹时则被称为'蝎尾人马兽'。"
					+ " 要被认定为一头真蝎狮，角色必须是一只拥有蝎狮尾巴、皮革质翅膀且未被识别为其他豹类兽人的豹类兽化人。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.ONE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.NEGATIVE_THREE_ZERO)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			Colour primaryColor = PresetColour.COVERING_RED;
			double rand = Math.random();
			if(rand<0.35f) {
				primaryColor = PresetColour.COVERING_BRONZE;
			} else if(rand<0.65f) {
				primaryColor = PresetColour.COVERING_AMBER;
			}
			Colour secondaryColor = PresetColour.COVERING_BLACK;
			body.getCoverings().put(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"), new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"), CoveringPattern.NONE, CoveringModifier.FLUFFY, primaryColor, false, secondaryColor, false));
			body.getCoverings().put(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"), new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"), CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
			body.updateCoverings(true, true, true, true);
			body.getTail().setType(target, LexiTailType.MANTICORE_TUSSY);
			body.getWing().setType(target, WingType.LEATHERY);
			body.getWing().setSize(WingSize.THREE_LARGE.getValue());
			body.getTorso().setType(target, TorsoType.getTorsoTypeFromId("innoxia_panther_torso"));
			body.getArm().setType(target, ArmType.getArmTypeFromId("innoxia_panther_arm"));
			body.getLeg().setType(target, LegType.getLegTypeFromId("innoxia_panther_leg"));
			body.getEar().setType(target, EarType.getEarTypeFromId("innoxia_panther_ear"));
			if (Math.random() > 0.5f) {
				//human
				body.getFace().setType(target, FaceType.HUMAN);
			} else {
				//furry
				body.getFace().setType(target, FaceType.getFaceTypeFromId("innoxia_panther_face"));
			}


			// Body size adjustment
			if(body.getBreast().getRawSizeValue()>CupSize.C.getMeasurement()) {
				rand = Math.random();
				if(rand<0.35f) {
					body.getBreast().setSize(target, CupSize.B.getMeasurement());
				} else if(rand<0.70f) {
					body.getBreast().setSize(target, CupSize.A.getMeasurement());
				} else {
					body.getBreast().setSize(target, CupSize.C.getMeasurement());
				}
			}

			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			body.setHeight(Height.FOUR_VERY_TALL.getMedianValue());
			return "";
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.getRaceFromId("innoxia_panther")) {
				AbstractFaceType faceType = body.getFace().getType();
				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;

				if((faceType == FaceType.getFaceTypeFromId("innoxia_panther_face") || faceType == FaceType.HUMAN)
						&& body.getEarType() == EarType.getEarTypeFromId("innoxia_panther_ear")
						&& body.getTorsoType() == TorsoType.getTorsoTypeFromId("innoxia_panther_torso")
						&& body.getArmType() == ArmType.getArmTypeFromId("innoxia_panther_arm")
						&& body.getLegType() == LegType.getLegTypeFromId("innoxia_panther_leg")
						&& body.getWingType() == WingType.LEATHERY
						&& body.getTailType() == LexiTailType.MANTICORE_TUSSY) {
					return 250;
				}
			}
			return 0;
		}
	};

	public static final AbstractSubspecies FALSE_MANTICORE = new AbstractSubspecies(false,
			36000,
			"innoxia_race_cat_felines_fancy",
			"innoxia_race_cat_kittys_reward",
			"statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackgroundCheetah",
			"蝎狮兽化人",
			"蝎狮兽化人",
			"蝎狮雄性",
			"蝎狮雌性",
			"蝎狮雄性们",
			"蝎狮雌性们",
			new FeralAttributes(
					"蝎狮",
					"蝎狮群",
					"蝎狮",
					"蝎狮",
					"蝎狮群",
					"蝎狮群",
					LegConfiguration.QUADRUPEDAL,
					80,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull] 实力超群，在体格上比大多数其他种族都要强壮。",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 18f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 15f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 12f)),
			null,
			"好奇的小猫",
			"好奇的小猫",
			"CAT_MORPH_BASIC",
			"CAT_MORPH_ADVANCED",
			Race.CAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 4),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 3)),
			PresetColour.RACE_CAT_MORPH_LION,
			SubspeciesPreference.TWO_AVERAGE,
			"蝎狮，以两足行走时被称为'蝎狮'，而当其下半身为一只通常体格庞大的野生猫科动物时则被称为'蝎尾人马兽'。"
					+ " 要被认定为一头蝎狮，角色必须是一只拥有蝎狮尾巴且未被识别为其他猫科兽化人的猫科兽化人。",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.THREE)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			Colour primaryColor = PresetColour.COVERING_AUBURN;
			double rand = Math.random();
			if(rand<0.35f) {
				primaryColor = PresetColour.COVERING_COPPER;
			}
			Colour secondaryColor = PresetColour.COVERING_BLACK;
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, CoveringModifier.FLUFFY, primaryColor, false, secondaryColor, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
			body.updateCoverings(true, true, true, true);
			body.getTail().setType(target, LexiTailType.MANTICORE_TUSSY);
			body.getEar().setType(target, EarType.CAT_MORPH);
			body.getArm().setType(target, ArmType.CAT_MORPH);
			body.getLeg().setType(target, LegType.CAT_MORPH);


			// Body size adjustment
			if(body.getBreast().getRawSizeValue()>CupSize.C.getMeasurement()) {
				rand = Math.random();
				if(rand<0.35f) {
					body.getBreast().setSize(target, CupSize.B.getMeasurement());
				} else if(rand<0.70f) {
					body.getBreast().setSize(target, CupSize.A.getMeasurement());
				} else {
					body.getBreast().setSize(target, CupSize.C.getMeasurement());
				}
			}

			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			body.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			body.setHeight(Height.THREE_TALL.getMedianValue());
			return "";
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.CAT_MORPH) {
				AbstractFaceType faceType = body.getFace().getType();
				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;

				if((faceType == FaceType.CAT_MORPH || faceType == FaceType.HUMAN)
						&& body.getEarType() == EarType.CAT_MORPH
						&& body.getLegType() == LegType.CAT_MORPH
						&& body.getTailType() == LexiTailType.MANTICORE_TUSSY) {
					return 150;
				}
			}
			return 0;
		}
	};

}