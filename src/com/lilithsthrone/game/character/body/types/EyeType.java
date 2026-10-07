package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public class EyeType {

	public static AbstractEyeType HUMAN = new AbstractEyeType(BodyCoveringType.EYE_HUMAN,
			Race.HUMAN,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"人类",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了人类的眼睛，虹膜和瞳孔的比例适中。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldHuman(人类的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]普通的人类眼睛，[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
	};

	public static AbstractEyeType ANGEL = new AbstractEyeType(BodyCoveringType.EYE_ANGEL,
			Race.ANGEL,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"天使",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了天使的眼睛，虹膜和瞳孔的比例适中。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldAngel(天使的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]天使的眼睛，[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.EYE_PERFECT_VISION);
		}
	};

	public static AbstractEyeType DEMON_COMMON = new AbstractEyeType(BodyCoveringType.EYE_DEMON_COMMON,
			Race.DEMON,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"恶魔",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"#IF(npc.isShortStature())"
				+ "[npc.she]战战兢兢地睁开眼时，发现已经变成了小恶魔的眼睛，虹膜偏大，纵向的瞳孔居于其中。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldImp(小恶魔的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.she]战战兢兢地睁开眼时，发现已经变成了恶魔的眼睛，虹膜偏大，纵向的瞳孔居于其中。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldDemon(恶魔的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.SheHasFull]拥有[npc.eyePairs]#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF的眼睛，[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(
					BodyPartTag.NIGHT_VISION,
					BodyPartTag.EYE_PERFECT_VISION);
		}
	};

	public static AbstractEyeType DEMON_OWL = new AbstractEyeType(BodyCoveringType.EYE_DEMON_COMMON,
			Race.DEMON,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"恶魔鸮",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]犹豫着再度睁开眼睛，那双眼已经化作了恶魔似的鸮眼睛，为[npc.herHim]提供了出色的夜视能力。"
				+ "<br/>[npc.Name]拥有了[style.boldDemon(恶魔似的鸮眼睛)]，还带有[style.boldGenericTF([npc.irisShape])]，[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]，[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]恶魔似的鸮眼睛，[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]巩膜。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(
					BodyPartTag.NIGHT_VISION,
					BodyPartTag.EYE_PERFECT_VISION);
		}
	};

	public static AbstractEyeType CAT_MORPH = new AbstractEyeType(BodyCoveringType.EYE_FELINE,
			Race.CAT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"猫",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了猫一般的眼睛，虹膜偏大，纵向的瞳孔居于其中。"
					+ "<br/>"
					+ "[npc.Name]现在拥有[style.boldCatMorph(猫一般的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]猫一般的眼睛，虹膜和瞳孔相比一般人类的都要大一些。"
				+ "[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType COW_MORPH = new AbstractEyeType(BodyCoveringType.EYE_COW_MORPH,
			Race.COW_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.HORIZONTAL,
			"牛",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了牛一般的眼睛，虹膜偏大，横向的瞳孔居于其中。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldCowMorph(牛一般的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]牛一般的眼睛，虹膜和瞳孔相比一般人类的都要大一些。"
			+ "[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType DOG_MORPH = new AbstractEyeType(BodyCoveringType.EYE_DOG_MORPH,
			Race.DOG_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"犬",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了犬一般的眼睛，虹膜和瞳孔都显得稍大。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldDogMorph(犬一般的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]犬一般的眼睛，虹膜和瞳孔相比一般人类的都要大一些。"
				+ "[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType FOX_MORPH = new AbstractEyeType(BodyCoveringType.EYE_FOX_MORPH,
			Race.FOX_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"狐狸",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了狐狸般的眼睛，虹膜偏大，纵向的瞳孔居于其中。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldFoxMorph(狐狸般的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]狐狸般的眼睛，虹膜和瞳孔相比一般人类的都要大一些。"
				+ "[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType WOLF_MORPH = new AbstractEyeType(BodyCoveringType.EYE_LYCAN,
			Race.WOLF_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"狼",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了狼一般的眼睛，虹膜和瞳孔都显得稍大。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldWolfMorph(狼一般的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]狼一般的眼睛，虹膜和瞳孔相比一般人类的都要大一些。"
				+ "[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType SQUIRREL_MORPH = new AbstractEyeType(BodyCoveringType.EYE_SQUIRREL,
			Race.SQUIRREL_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"松鼠",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了松鼠般的眼睛，虹膜和瞳孔都显得稍大。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldSquirrelMorph(松鼠般的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]松鼠般的眼睛，虹膜和瞳孔相比一般人类的都要大一些。"
				+ "[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
	};

	public static AbstractEyeType RAT_MORPH = new AbstractEyeType(BodyCoveringType.EYE_RAT,
			Race.RAT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"老鼠",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了老鼠般的眼睛，虹膜和瞳孔都显得稍大。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldRatMorph(老鼠般的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]老鼠般的眼睛，虹膜和瞳孔相比一般人类的都要大一些。"
				+ "[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
	};

	public static AbstractEyeType RABBIT_MORPH = new AbstractEyeType(BodyCoveringType.EYE_RABBIT,
			Race.RABBIT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"兔子",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了兔子般的眼睛，虹膜和瞳孔都显得稍大。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldRabbitMorph(兔子般的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]兔子般的眼睛，虹膜和瞳孔相比一般人类的都要大一些。"
				+ "[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
	};

	public static AbstractEyeType BAT_MORPH = new AbstractEyeType(BodyCoveringType.EYE_BAT,
			Race.BAT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"蝙蝠",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，那双眼已经变成了蝙蝠般的眼睛，虹膜和瞳孔都显得稍大。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldBatMorph(蝙蝠般的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]蝙蝠般的眼睛，虹膜和瞳孔相比一般人类的都要大一些。"
				+ "[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType ALLIGATOR_MORPH = new AbstractEyeType(BodyCoveringType.EYE_ALLIGATOR_MORPH,
			Race.ALLIGATOR_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"鳄鱼",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了鳄鱼般的眼睛，虹膜偏大，纵向的瞳孔居于其中。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldAlligatorMorph(鳄鱼般的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]鳄鱼般的眼睛，虹膜和瞳孔相比一般人类的都要大一些。"
				+ "[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType HORSE_MORPH = new AbstractEyeType(BodyCoveringType.EYE_HORSE_MORPH,
			Race.HORSE_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.HORIZONTAL,
			"马",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了马一般的眼睛，虹膜偏大，横向的瞳孔居于其中。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldHorseMorph(马一般的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]马一般的眼睛，虹膜和瞳孔相比一般人类的都要大一些。"
				+ "[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType REINDEER_MORPH = new AbstractEyeType(BodyCoveringType.EYE_REINDEER_MORPH,
			Race.REINDEER_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.HORIZONTAL,
			"驯鹿",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了驯鹿般的眼睛，虹膜偏大，横向的瞳孔居于其中。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldReindeerMorph(驯鹿般的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]驯鹿般的眼睛，虹膜和瞳孔相比一般人类的都要大一些。"
				+ "[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType HARPY = new AbstractEyeType(BodyCoveringType.EYE_HARPY,
			Race.HARPY,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"哈比",
			"眼睛",
			"眼睛",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.she]战战兢兢地睁开眼时，发现已经变成了鸟一般的眼睛，虹膜和瞳孔都显得稍大。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[style.boldHarpy(鸟一般的眼睛)]，有着[style.boldGenericTF([npc.irisShape])]的[npc.irisFullDescription(true)]和[style.boldGenericTF([npc.pupilShape])]的[npc.pupilFullDescription(true)]。",
			"[npc.SheHasFull]拥有[npc.eyePairs]鸟一般的眼睛，虹膜和瞳孔相比一般人类的都要大一些。"
				+ "[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，还有着[npc.scleraColour(true)]的巩膜。") {
	};
	

	private static List<AbstractEyeType> allEyeTypes;
	private static Map<AbstractEyeType, String> eyeToIdMap = new HashMap<>();
	private static Map<String, AbstractEyeType> idToEyeMap = new HashMap<>();
	
	static {
		allEyeTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("eye")) {
					try {
						AbstractEyeType type = new AbstractEyeType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allEyeTypes.add(type);
						eyeToIdMap.put(type, id);
						idToEyeMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("eye")) {
					try {
						AbstractEyeType type = new AbstractEyeType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allEyeTypes.add(type);
						eyeToIdMap.put(type, id);
						idToEyeMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded eye types:
		
		Field[] fields = EyeType.class.getFields();
		
		for(Field f : fields){
			if (AbstractEyeType.class.isAssignableFrom(f.getType())) {
				
				AbstractEyeType ct;
				try {
					ct = ((AbstractEyeType) f.get(null));

					eyeToIdMap.put(ct, f.getName());
					idToEyeMap.put(f.getName(), ct);
					
					allEyeTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allEyeTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractEyeType getEyeTypeFromId(String id) {
		if(id.equals("IMP")) {
			return EyeType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return EyeType.WOLF_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToEyeMap.keySet());
		return idToEyeMap.get(id);
	}
	
	public static String getIdFromEyeType(AbstractEyeType eyeType) {
		return eyeToIdMap.get(eyeType);
	}
	
	public static List<AbstractEyeType> getAllEyeTypes() {
		return allEyeTypes;
	}
	
	private static Map<AbstractRace, List<AbstractEyeType>> typesMap = new HashMap<>();
	
	public static List<AbstractEyeType> getEyeTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractEyeType> types = new ArrayList<>();
		for(AbstractEyeType type : EyeType.getAllEyeTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
	
}