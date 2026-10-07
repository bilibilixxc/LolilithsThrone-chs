package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.8.2
 * @author Innoxia
 */
public class FluidType {
// keldon move out
	public static final AbstractFluidType URINE = new AbstractFluidType(FluidTypeBase.URINE,
			FluidFlavour.NECTAR,
			Race.NONE,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()) {
	};
	
	// Cum:
	
	public static AbstractFluidType CUM_HUMAN = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.HUMAN,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};
	
	public static AbstractFluidType CUM_DOLL = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.DOLL,
			Util.newArrayListOfValues("玩偶"),
			Util.newArrayListOfValues("玩偶"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY,
					FluidModifier.MINERAL_OIL)) {
		@Override
		public float getValueModifier() {
			return 0.25f;
		}
	};

	public static AbstractFluidType CUM_ANGEL = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.ANGEL,
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_DEMON = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.DEMON,
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_DOG_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.DOG_MORPH,
			Util.newArrayListOfValues("犬", "犬类"),
			Util.newArrayListOfValues("犬", "犬类"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_WOLF_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.WOLF_MORPH,
			Util.newArrayListOfValues("狼"),
			Util.newArrayListOfValues("狼"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.MUSKY,
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_FOX_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.FOX_MORPH,
			Util.newArrayListOfValues("狐狸"),
			Util.newArrayListOfValues("狐狸"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_CAT_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.CAT_MORPH,
			Util.newArrayListOfValues("猫", "猫类"),
			Util.newArrayListOfValues("猫", "猫类"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_SQUIRREL_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.SQUIRREL_MORPH,
			Util.newArrayListOfValues("松鼠", "啮齿动物"),
			Util.newArrayListOfValues("松鼠", "啮齿动物"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_RAT_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.RAT_MORPH,
			Util.newArrayListOfValues("鼠", "啮齿动物"),
			Util.newArrayListOfValues("鼠", "啮齿动物"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_RABBIT_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.RABBIT_MORPH,
			Util.newArrayListOfValues("兔子"),
			Util.newArrayListOfValues("兔子"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_BAT_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.BAT_MORPH,
			Util.newArrayListOfValues("蝙蝠"),
			Util.newArrayListOfValues("蝙蝠"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_ALLIGATOR_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.ALLIGATOR_MORPH,
			Util.newArrayListOfValues("鳄鱼", "爬行动物"),
			Util.newArrayListOfValues("鳄鱼", "爬行动物"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_HORSE_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.HORSE_MORPH,
			Util.newArrayListOfValues("马", "马类"),
			Util.newArrayListOfValues("马", "马类"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.MUSKY,
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_REINDEER_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.REINDEER_MORPH,
			Util.newArrayListOfValues("驯鹿"),
			Util.newArrayListOfValues("驯鹿"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.MUSKY,
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_COW_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.COW_MORPH,
			Util.newArrayListOfValues("牛", "牛类"),
			Util.newArrayListOfValues("牛", "牛类"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.MUSKY,
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_HARPY = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.HARPY,
			Util.newArrayListOfValues("哈比", "鸟"),
			Util.newArrayListOfValues("哈比", "鸟"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	// Girl cum:
	
	public static AbstractFluidType GIRL_CUM_HUMAN = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.HUMAN,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};
	
	public static AbstractFluidType GIRL_CUM_DOLL = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.DOLL,
			Util.newArrayListOfValues("玩偶"),
			Util.newArrayListOfValues("玩偶"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY,
					FluidModifier.MINERAL_OIL)) {
		@Override
		public float getValueModifier() {
			return 0.25f;
		}
	};

	public static AbstractFluidType GIRL_CUM_ANGEL = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.ANGEL,
			null,
			null,
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_DEMON = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.DEMON,
			null,
			null,
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_DOG_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.DOG_MORPH,
			null,
			null,
			Util.newArrayListOfValues("犬类"),
			Util.newArrayListOfValues("犬类"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_WOLF_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.WOLF_MORPH,
			null,
			null,
			Util.newArrayListOfValues("狼类"),
			Util.newArrayListOfValues("狼类"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_FOX_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.FOX_MORPH,
			null,
			null,
			Util.newArrayListOfValues("狐狸"),
			Util.newArrayListOfValues("狐狸"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_CAT_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.CAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("猫类"),
			Util.newArrayListOfValues("猫类"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_SQUIRREL_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.SQUIRREL_MORPH,
			null,
			null,
			Util.newArrayListOfValues("松鼠"),
			Util.newArrayListOfValues("松鼠"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_RAT_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.RAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("老鼠"),
			Util.newArrayListOfValues("老鼠"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_RABBIT_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.RABBIT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("兔子"),
			Util.newArrayListOfValues("兔子"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_BAT_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.BAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("蝙蝠"),
			Util.newArrayListOfValues("蝙蝠"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_ALLIGATOR_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.ALLIGATOR_MORPH,
			null,
			null,
			Util.newArrayListOfValues("鳄鱼"),
			Util.newArrayListOfValues("鳄鱼"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_HORSE_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.HORSE_MORPH,
			null,
			null,
			Util.newArrayListOfValues("马类"),
			Util.newArrayListOfValues("马类"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_REINDEER_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.REINDEER_MORPH,
			null,
			null,
			Util.newArrayListOfValues("驯鹿"),
			Util.newArrayListOfValues("驯鹿"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_COW_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.COW_MORPH,
			null,
			null,
			Util.newArrayListOfValues("牛类"),
			Util.newArrayListOfValues("牛类"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_HARPY = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.HARPY,
			null,
			null,
			Util.newArrayListOfValues("鸟类"),
			Util.newArrayListOfValues("鸟类"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	// Milks:
	
	public static AbstractFluidType MILK_HUMAN = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.HUMAN,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractFluidType MILK_DOLL = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.DOLL,
			Util.newArrayListOfValues("玩偶"),
			Util.newArrayListOfValues("玩偶"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY,
					FluidModifier.MINERAL_OIL)) {
		@Override
		public float getValueModifier() {
			return 0.25f;
		}
	};

	public static AbstractFluidType MILK_ANGEL = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.ANGEL,
			null,
			null,
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_COW_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.COW_MORPH,
			null,
			null,
			Util.newArrayListOfValues("牛类"),
			Util.newArrayListOfValues("牛类"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_DEMON = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.DEMON,
			null,
			null,
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_DOG_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.DOG_MORPH,
			null,
			null,
			Util.newArrayListOfValues("犬类"),
			Util.newArrayListOfValues("犬类"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_WOLF_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.WOLF_MORPH,
			null,
			null,
			Util.newArrayListOfValues("狼类"),
			Util.newArrayListOfValues("狼类"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_FOX_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.FOX_MORPH,
			null,
			null,
			Util.newArrayListOfValues("狐狸"),
			Util.newArrayListOfValues("狐狸"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_CAT_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.CAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("猫类"),
			Util.newArrayListOfValues("猫类"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_SQUIRREL_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.SQUIRREL_MORPH,
			null,
			null,
			Util.newArrayListOfValues("松鼠"),
			Util.newArrayListOfValues("松鼠"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_RAT_MORPH = new AbstractFluidType(FluidTypeBase.MILK, // I don't get it. Everyone loves rats, but they don't wanna drink the rats' milk?
			FluidFlavour.MILK,
			Race.RAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("老鼠"),
			Util.newArrayListOfValues("老鼠"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_RABBIT_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.RABBIT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("兔子"),
			Util.newArrayListOfValues("兔子"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_BAT_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.BAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("蝙蝠"),
			Util.newArrayListOfValues("蝙蝠"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_ALLIGATOR_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.ALLIGATOR_MORPH,
			null,
			null,
			Util.newArrayListOfValues("鳄鱼"),
			Util.newArrayListOfValues("鳄鱼"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_HORSE_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.HORSE_MORPH,
			null,
			null,
			Util.newArrayListOfValues("马类"),
			Util.newArrayListOfValues("马类"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_REINDEER_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.REINDEER_MORPH,
			null,
			null,
			Util.newArrayListOfValues("驯鹿"),
			Util.newArrayListOfValues("驯鹿"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_HARPY = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.HARPY,
			null,
			null,
			Util.newArrayListOfValues("鸟类"),
			Util.newArrayListOfValues("鸟类"),
			Util.newArrayListOfValues()) {
	};
	
	
	private static List<AbstractFluidType> allFluidTypes;
	private static Map<AbstractFluidType, String> fluidToIdMap = new HashMap<>();
	private static Map<String, AbstractFluidType> idToFluidMap = new HashMap<>();
	
	static {
		allFluidTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("fluid")) {
					try {
						AbstractFluidType type = new AbstractFluidType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allFluidTypes.add(type);
						fluidToIdMap.put(type, id);
						idToFluidMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("fluid")) {
					try {
						AbstractFluidType type = new AbstractFluidType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allFluidTypes.add(type);
						fluidToIdMap.put(type, id);
						idToFluidMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded fluid types:
		
		Field[] fields = FluidType.class.getFields();
		
		for(Field f : fields){
			if (AbstractFluidType.class.isAssignableFrom(f.getType())) {
				
				AbstractFluidType ct;
				try {
					ct = ((AbstractFluidType) f.get(null));

					fluidToIdMap.put(ct, f.getName());
					idToFluidMap.put(f.getName(), ct);
					
					allFluidTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AbstractFluidType getFluidTypeFromId(String id) {
		if(id.equals("CUM_IMP")) {
			id = "CUM_DEMON";
			
		} else if(id.equals("GIRL_CUM_IMP")) {
			id = "GIRL_CUM_DEMON";
			
		} else if(id.equals("MILK_IMP")) {
			id = "MILK_DEMON";
			
		} else if(id.equals("MILK_DEMON_COMMON")) {
			id = "MILK_DEMON";
		}
		
		id = Util.getClosestStringMatch(id, idToFluidMap.keySet());
		return idToFluidMap.get(id);
	}
	
	public static String getIdFromFluidType(AbstractFluidType fluidType) {
		return fluidToIdMap.get(fluidType);
	}
	
	public static List<AbstractFluidType> getAllFluidTypes() {
		return allFluidTypes;
	}
	
	private static Map<AbstractRace, List<AbstractFluidType>> typesMap = new HashMap<>();
	public static List<AbstractFluidType> getFluidTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractFluidType> types = new ArrayList<>();
		for(AbstractFluidType type : FluidType.getAllFluidTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}