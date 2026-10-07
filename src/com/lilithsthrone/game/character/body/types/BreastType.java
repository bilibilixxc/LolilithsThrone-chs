package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.8.2
 * @author Innoxia
 */
public class BreastType {

	// Only used for when lacking crotch breasts:
	public static AbstractBreastType NONE = new AbstractBreastType(BodyCoveringType.HUMAN,
			Race.NONE,
			NippleType.HUMAN,
			FluidType.MILK_HUMAN,
			"乳房",
			"乳房",
			"[npc.She]不再拥有[style.boldShrink([npc.crotchBoobs])]！",
			""){
	};
	
	public static AbstractBreastType HUMAN = new AbstractBreastType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			NippleType.HUMAN,
			FluidType.MILK_HUMAN,
			"[npc.She]现在拥有[style.boldHuman(人类)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldHuman(人类乳汁)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗人类的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldHuman(人类)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldHuman(人类乳汁)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗人类的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType ANGEL = new AbstractBreastType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			NippleType.ANGEL,
			FluidType.MILK_ANGEL,
			"[npc.She]现在拥有[style.boldAngel(天使)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldAngel(天使乳汁)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗天使的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldAngel(天使)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldAngel(天使乳汁)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗天使的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType DEMON_COMMON = new AbstractBreastType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			NippleType.DEMON,
			FluidType.MILK_DEMON,
			"[npc.She]现在拥有[style.boldDemon(恶魔)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldDemon(恶魔乳汁)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗恶魔的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldDemon(恶魔)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldDemon(恶魔乳汁)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗恶魔的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType DOG_MORPH = new AbstractBreastType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			NippleType.DOG_MORPH,
			FluidType.MILK_DOG_MORPH, // Emergency backup supply. We're on the dog's milk.
			"[npc.She]现在拥有[style.boldDogMorph(犬类)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldDogMorph(犬奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗犬类的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldDogMorph(犬类)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldDogMorph(犬奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗犬类的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType WOLF_MORPH = new AbstractBreastType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			NippleType.WOLF_MORPH,
			FluidType.MILK_WOLF_MORPH,
			"[npc.She]现在拥有[style.boldWolfMorph(狼类)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldWolfMorph(狼奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗狼类的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldWolfMorph(狼类)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldWolfMorph(狼奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗狼类的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType FOX_MORPH = new AbstractBreastType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			NippleType.FOX_MORPH,
			FluidType.MILK_FOX_MORPH,
			"[npc.She]现在拥有[style.boldFoxMorph(狐狸般)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldFoxMorph(狐狸奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗狐狸般的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldFoxMorph(狐狸般)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldFoxMorph(狐狸奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗狐狸般的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType COW_MORPH = new AbstractBreastType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			NippleType.COW_MORPH,
			FluidType.MILK_COW_MORPH,
			"[npc.She]现在拥有[style.boldCowMorph(牛类)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldCowMorph(牛奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗牛类的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldCowMorph(人类)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldCowMorph(牛奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗牛类的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType CAT_MORPH = new AbstractBreastType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			NippleType.CAT_MORPH,
			FluidType.MILK_CAT_MORPH,
			"[npc.She]现在拥有[style.boldCatMorph(猫类)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldCatMorph(猫奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗猫类的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldCatMorph(猫类)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldCatMorph(猫奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗猫类的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType SQUIRREL_MORPH = new AbstractBreastType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			NippleType.SQUIRREL_MORPH,
			FluidType.MILK_SQUIRREL_MORPH,
			"[npc.She]现在拥有[style.boldSquirrelMorph(松鼠般)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldSquirrelMorph(松鼠奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗松鼠般的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldSquirrelMorph(松鼠般)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldSquirrelMorph(松鼠奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗松鼠般的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType RAT_MORPH = new AbstractBreastType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			NippleType.RAT_MORPH,
			FluidType.MILK_RAT_MORPH, // Rats?! I'm outraged! You promised me dog or higher!
			"[npc.She]现在拥有[style.boldRatMorph(老鼠般)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldRatMorph(老鼠奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗老鼠般的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldRatMorph(老鼠般)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldRatMorph(老鼠奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗老鼠般的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType RABBIT_MORPH = new AbstractBreastType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			NippleType.RABBIT_MORPH,
			FluidType.MILK_RABBIT_MORPH,
			"[npc.She]现在拥有[style.boldRabbitMorph(兔子般)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldRabbitMorph(兔奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗兔子般的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldRabbitMorph(兔子般)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldRabbitMorph(兔奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗兔子般的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType BAT_MORPH = new AbstractBreastType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			NippleType.BAT_MORPH,
			FluidType.MILK_BAT_MORPH,
			"[npc.She]现在拥有[style.boldBatMorph(蝙蝠般)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldBatMorph(蝙蝠奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗蝙蝠般的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldBatMorph(蝙蝠般)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldBatMorph(蝙蝠奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗蝙蝠般的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType ALLIGATOR_MORPH = new AbstractBreastType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			NippleType.ALLIGATOR_MORPH,
			FluidType.MILK_ALLIGATOR_MORPH, // Hmm
			"[npc.She]现在拥有[style.boldGatorMorph(爬行类)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldGatorMorph(鳄鱼奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗爬行类的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldGatorMorph(爬行类)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldGatorMorph(鳄鱼奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗爬行类的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType HORSE_MORPH = new AbstractBreastType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			NippleType.HORSE_MORPH,
			FluidType.MILK_HORSE_MORPH,
			"[npc.She]现在拥有[style.boldHorseMorph(马类)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldHorseMorph(马奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗马类的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldHorseMorph(马类)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldHorseMorph(马奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗马类的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType REINDEER_MORPH = new AbstractBreastType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			NippleType.REINDEER_MORPH,
			FluidType.MILK_REINDEER_MORPH,
			"[npc.She]现在拥有[style.boldReindeerMorph(驯鹿般)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldReindeerMorph(驯鹿奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗驯鹿般的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldReindeerMorph(驯鹿般)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldReindeerMorph(驯鹿奶)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗驯鹿般的[npc.crotchNipplesFullDescription]。"){
	};
	
	public static AbstractBreastType HARPY = new AbstractBreastType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			NippleType.HARPY,
			FluidType.MILK_HARPY, // hmm
			"[npc.She]现在拥有[style.boldHarpy(鸟类)]的[npc.nipplesFullDescription]，泌乳时会分泌[style.boldHarpy(哈比乳汁)]。",
			"[npc.her]每个[npc.breastSize]的[npc.breasts]上，都有着[npc.nipplesPerBreast]颗鸟类的[npc.nipplesFullDescription]。",
			"[npc.She]现在拥有[style.boldHarpy(鸟类)]的[npc.crotchNipplesFullDescription]，泌乳时会分泌[style.boldHarpy(哈比乳汁)]。",
			"[npc.her]每个[npc.breastSize]的[npc.crotchBoobs]上，都有着[npc.crotchNipplesPerBreast]颗鸟类的[npc.crotchNipplesFullDescription]。"){
	};
	
	
	private static List<AbstractBreastType> allBreastTypes;
	private static Map<AbstractBreastType, String> breastToIdMap = new HashMap<>();
	private static Map<String, AbstractBreastType> idToBreastMap = new HashMap<>();
	
	static {
		allBreastTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("breast")) {
					try {
						AbstractBreastType type = new AbstractBreastType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allBreastTypes.add(type);
						breastToIdMap.put(type, id);
						idToBreastMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("breast")) {
					try {
						AbstractBreastType type = new AbstractBreastType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allBreastTypes.add(type);
						breastToIdMap.put(type, id);
						idToBreastMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded breast types:
		
		Field[] fields = BreastType.class.getFields();
		
		for(Field f : fields){
			if (AbstractBreastType.class.isAssignableFrom(f.getType())) {
				
				AbstractBreastType ct;
				try {
					ct = ((AbstractBreastType) f.get(null));

					breastToIdMap.put(ct, f.getName());
					idToBreastMap.put(f.getName(), ct);
					
					allBreastTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allBreastTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractBreastType getBreastTypeFromId(String id) {
		if(id.equals("IMP")) {
			return BreastType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return BreastType.WOLF_MORPH;
		}

		id = Util.getClosestStringMatch(id, idToBreastMap.keySet());
		return idToBreastMap.get(id);
	}
	
	public static String getIdFromBreastType(AbstractBreastType breastType) {
		return breastToIdMap.get(breastType);
	}
	
	public static List<AbstractBreastType> getAllBreastTypes() {
		return allBreastTypes;
	}
	
	private static Map<AbstractRace, List<AbstractBreastType>> typesMap = new HashMap<>();
	public static List<AbstractBreastType> getBreastTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractBreastType> types = new ArrayList<>();
		for(AbstractBreastType type : BreastType.getAllBreastTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
	
}