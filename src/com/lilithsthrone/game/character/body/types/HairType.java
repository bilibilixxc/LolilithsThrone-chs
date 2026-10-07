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
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.9.1
 * @author Innoxia
 */
public class HairType {

	public static AbstractHairType HUMAN = new AbstractHairType(BodyCoveringType.HAIR_HUMAN,
			Race.HUMAN,
			"人类",
			"头发",
			"头发",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("柔软", "阴柔"),
			"转化只持续了很短的时间，便得到了人类样子的头发。<br/>"
					+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldHuman(人类头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer][npc.hairColour(true)]的人类[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType ANGEL = new AbstractHairType(BodyCoveringType.HAIR_ANGEL,
			Race.ANGEL,
			"天使",
			"头发",
			"头发",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("丝绸般", "柔软", "阴柔"),
			"转化只持续了很短的时间，便得到了天使样子的柔顺头发。<br/>"
					+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldAngel(天使头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer][npc.hairColour(true)]的天使[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};
	
	public static AbstractHairType DEMON = new AbstractHairType(BodyCoveringType.HAIR_DEMON,
			Race.DEMON,
			"恶魔",
			"头发",
			"头发",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("丝绸般", "柔软", "阴柔"),
			"#IF(npc.isShortStature())"
				+ "转化只持续了很短的时间，便得到了小恶魔样子的柔顺头发。<br/>"
					+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldImp(小恶魔头发)]。"
			+ "#ELSE"
				+ "转化只持续了很短的时间，便得到了恶魔样子的柔顺头发。<br/>"
					+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldDemon(恶魔头发)]。"
			+ "#ENDIF",
			"[npc.SheHasFull]拥有[npc.hairDeterminer][npc.hairColour(true)]的#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType DOG_MORPH = new AbstractHairType(BodyCoveringType.HAIR_CANINE_FUR,
			Race.DOG_MORPH,
			"犬",
			"头发",
			"头发",
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			"转化只持续了很短的时间，便得到了皮毛般的头发。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldDogMorph(犬一般的头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]犬一般[npc.hairColour(true)]的[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};
	
	public static AbstractHairType WOLF_MORPH = new AbstractHairType(BodyCoveringType.HAIR_LYCAN_FUR, //TODO rename
			Race.WOLF_MORPH,
			"狼",
			"头发",
			"头发",
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			"转化只持续了很短的时间，便得到了皮毛般的头发。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldWolfMorph(狼一般的头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]狼一般[npc.hairColour(true)]的[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
		@Override
		public boolean isNeckFluffRequiresGreater() {
			return true;
		}
		@Override
		public double getNeckFluffChance() {
			return 0.25f;
		}
	};

	public static AbstractHairType FOX_MORPH = new AbstractHairType(BodyCoveringType.HAIR_FOX_FUR,
			Race.FOX_MORPH,
			"狐狸",
			"头发",
			"头发",
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			"转化只持续了很短的时间，便得到了皮毛般的头发。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldFoxMorph(狐狸般的头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]狐狸般[npc.hairColour(true)]的[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
		@Override
		public boolean isNeckFluffRequiresGreater() {
			return true;
		}
		@Override
		public double getNeckFluffChance() {
			return 0.25f;
		}
	};

	public static AbstractHairType CAT_MORPH = new AbstractHairType(BodyCoveringType.HAIR_FELINE_FUR, //TODO change to cat
			Race.CAT_MORPH,
			"猫",
			"头发",
			"头发",
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			"转化只持续了很短的时间，便得到了皮毛般的头发。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldCatMorph(猫一般的头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]猫一般[npc.hairColour(true)]的[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	//TODO should be PANTHER
	public static AbstractHairType CAT_MORPH_SIDEFLUFF = new AbstractHairType(BodyCoveringType.HAIR_FELINE_FUR,
			Race.CAT_MORPH,
			"猫(脸颊绒)",
			"头发",
			"头发",
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			"转化只持续了很短的时间，便得到了皮毛般的头发。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldCatMorph(猫一般的头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]猫一般[npc.hairColour(true)]的[npc.hairLength]发，在面颊两边还有毛茸茸的柔软皮毛",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType COW_MORPH = new AbstractHairType(BodyCoveringType.HAIR_BOVINE_FUR, //TODO change to cow
			Race.COW_MORPH,
			"牛",
			"头发",
			"头发",
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			"转化只持续了很短的时间，便得到了皮毛般的头发。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldCowMorph(牛一般的头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]牛一般[npc.hairColour(true)]的[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType ALLIGATOR_MORPH = new AbstractHairType(BodyCoveringType.HAIR_SCALES_ALLIGATOR, //TODO change to hair
			Race.ALLIGATOR_MORPH,
			"鳄鱼",
			"头发",
			"头发",
			Util.newArrayListOfValues("粗糙"),
			Util.newArrayListOfValues("粗糙"),
			"转化只持续了很短的时间，便得到了鳄鱼样子的粗糙头发。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldAlligatorMorph(鳄鱼般的头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]鳄鱼般[npc.hairColour(true)]的[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType SQUIRREL_MORPH = new AbstractHairType(BodyCoveringType.HAIR_SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			"松鼠",
			"头发",
			"头发",
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			"转化只持续了很短的时间，便得到了皮毛般的头发。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldSquirrelMorph(松鼠般的头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]松鼠般[npc.hairColour(true)]的[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType RAT_MORPH = new AbstractHairType(BodyCoveringType.HAIR_RAT_FUR,
			Race.RAT_MORPH,
			"老鼠",
			"头发",
			"头发",
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			"转化只持续了很短的时间，便得到了皮毛般的头发。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldRatMorph(老鼠般的头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]老鼠般[npc.hairColour(true)]的[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType RABBIT_MORPH = new AbstractHairType(BodyCoveringType.HAIR_RABBIT_FUR,
			Race.RABBIT_MORPH,
			"兔子",
			"头发",
			"头发",
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			"转化只持续了很短的时间，便得到了皮毛般的头发。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldRabbitMorph(兔子般的头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]兔子般[npc.hairColour(true)]的[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType BAT_MORPH = new AbstractHairType(BodyCoveringType.HAIR_BAT_FUR,
			Race.BAT_MORPH,
			"蝙蝠",
			"头发",
			"头发",
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			"转化只持续了很短的时间，便得到了皮毛般的头发。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldBatMorph(蝙蝠般的头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]蝙蝠般[npc.hairColour(true)]的[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType HORSE_MORPH = new AbstractHairType(BodyCoveringType.HAIR_HORSE_HAIR,
			Race.HORSE_MORPH,
			"马",
			"头发",
			"头发",
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			"转化只持续了很短的时间，便得到了皮毛般的头发。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldHorseMorph(马一般的头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]马一般[npc.hairColour(true)]的[npc.hairLength]发",
			Util.newArrayListOfValues(
					BodyPartTag.HAIR_HANDLES_IN_SEX,
					BodyPartTag.HAIR_NATURAL_MANE)) {
	};

	public static AbstractHairType REINDEER_MORPH = new AbstractHairType(BodyCoveringType.HAIR_REINDEER_FUR,
			Race.REINDEER_MORPH,
			"驯鹿",
			"头发",
			"头发",
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			Util.newArrayListOfValues("毛茸茸", "皮毛般"),
			"转化只持续了很短的时间，便得到了皮毛般的头发。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldReindeerMorph(驯鹿般的头发)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]驯鹿般[npc.hairColour(true)]的[npc.hairLength]发",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType HARPY = new AbstractHairType(BodyCoveringType.HAIR_HARPY,
			Race.HARPY,
			"哈比",
			"冠羽",
			"冠羽",
			Util.newArrayListOfValues("优美", "鸟一般"),
			Util.newArrayListOfValues("优美", "鸟一般"),
			"转化只持续了很短的时间，头发原本的位置便得到了一簇冠羽。<br/>"
				+ "[npc.Name]现在拥有[npc.hairColour]的[style.boldHarpy(哈比羽毛)]。",
			"[npc.SheHasFull]拥有[npc.hairDeterminer]鸟一般[npc.hairColour(true)]的[npc.hairLength]冠羽",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return true;
		}
		@Override
		public String getDeterminer(GameCharacter gc) {
			return "一簇";
		}
	};

	private static List<AbstractHairType> allHairTypes;
	private static Map<AbstractHairType, String> hairToIdMap = new HashMap<>();
	private static Map<String, AbstractHairType> idToHairMap = new HashMap<>();
	
	static {
		allHairTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("hair")) {
					try {
						AbstractHairType type = new AbstractHairType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allHairTypes.add(type);
						hairToIdMap.put(type, id);
						idToHairMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("hair")) {
					try {
						AbstractHairType type = new AbstractHairType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allHairTypes.add(type);
						hairToIdMap.put(type, id);
						idToHairMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		// Add in hard-coded hair types:
		
		Field[] fields = HairType.class.getFields();
		
		for(Field f : fields){
			if (AbstractHairType.class.isAssignableFrom(f.getType())) {
				
				AbstractHairType ct;
				try {
					ct = ((AbstractHairType) f.get(null));

					hairToIdMap.put(ct, f.getName());
					idToHairMap.put(f.getName(), ct);
					
					allHairTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		Collections.sort(allHairTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractHairType getHairTypeFromId(String id) {
		if(id.equals("IMP") || id.equals("DEMON_COMMON")) {
			return HairType.DEMON;
		}
		if(id.equals("LYCAN")) {
			return HairType.WOLF_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToHairMap.keySet());
		return idToHairMap.get(id);
	}
	
	public static String getIdFromHairType(AbstractHairType hairType) {
		return hairToIdMap.get(hairType);
	}
	
	public static List<AbstractHairType> getAllHairTypes() {
		return allHairTypes;
	}
	
	private static Map<AbstractRace, List<AbstractHairType>> typesMap = new HashMap<>();
	
	public static List<AbstractHairType> getHairTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractHairType> types = new ArrayList<>();
		for(AbstractHairType type : HairType.getAllHairTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}