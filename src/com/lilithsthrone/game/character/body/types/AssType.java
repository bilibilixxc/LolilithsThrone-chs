package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3
 * @author Innoxia
 */
public class AssType {
	
	public static AbstractAssType HUMAN = new AbstractAssType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			AnusType.HUMAN,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldHuman(人类)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有人类[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType ANGEL = new AbstractAssType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			AnusType.ANGEL,
			null,
			null,
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues("天使", "完美"),
			"[npc.She]现在拥有[style.boldAngel(天使)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有天使[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType DEMON_COMMON = new AbstractAssType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			AnusType.DEMON_COMMON,
			null,
			null,
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔", "完美"),
			"[npc.She]现在拥有[style.boldDemon(恶魔)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有恶魔[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType DOG_MORPH = new AbstractAssType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			AnusType.DOG_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldDogMorph(犬一般)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有犬一般[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType COW_MORPH = new AbstractAssType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			AnusType.COW_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldCowMorph(牛一般)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有牛一般[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType SQUIRREL_MORPH = new AbstractAssType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			AnusType.SQUIRREL_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldSquirrelMorph(松鼠般)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有松鼠般[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType RAT_MORPH = new AbstractAssType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			AnusType.RAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldRatMorph(老鼠般)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有老鼠般[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType RABBIT_MORPH = new AbstractAssType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			AnusType.RABBIT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldRabbitMorph(兔子般)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有兔子般[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType BAT_MORPH = new AbstractAssType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			AnusType.BAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldBatMorph(蝙蝠般)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有蝙蝠般[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType ALLIGATOR_MORPH = new AbstractAssType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			AnusType.ALLIGATOR_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldGatorMorph(鳄鱼般)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有鳄鱼般[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType WOLF_MORPH = new AbstractAssType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			AnusType.WOLF_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldWolfMorph(狼一般)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥狼一般[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType FOX_MORPH = new AbstractAssType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			AnusType.FOX_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldFoxMorph(狐狸般)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有狐狸般[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType CAT_MORPH = new AbstractAssType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			AnusType.CAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldCatMorph(猫一般)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有猫一般[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType HORSE_MORPH = new AbstractAssType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			AnusType.HORSE_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldHorseMorph(马一般)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有马一般[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType REINDEER_MORPH = new AbstractAssType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			AnusType.REINDEER_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldReindeerMorph(驯鹿般)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有驯鹿般[npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType HARPY = new AbstractAssType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			AnusType.HARPY,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldHarpy(鸟一般)][npc.assholeFullDescription]。",
			"[npc.SheHasFull]拥有鸟一般[npc.anusFullDescription(true)]"){
	};
	
	
	private static List<AbstractAssType> allAssTypes;
	private static Map<AbstractAssType, String> assToIdMap = new HashMap<>();
	private static Map<String, AbstractAssType> idToAssMap = new HashMap<>();
	
	static {
		allAssTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("ass")) {
					try {
						AbstractAssType type = new AbstractAssType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allAssTypes.add(type);
						assToIdMap.put(type, id);
						idToAssMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("ass")) {
					try {
						AbstractAssType type = new AbstractAssType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allAssTypes.add(type);
						assToIdMap.put(type, id);
						idToAssMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded ass types:
		
		Field[] fields = AssType.class.getFields();
		
		for(Field f : fields){
			if (AbstractAssType.class.isAssignableFrom(f.getType())) {
				
				AbstractAssType ct;
				try {
					ct = ((AbstractAssType) f.get(null));

					assToIdMap.put(ct, f.getName());
					idToAssMap.put(f.getName(), ct);
					
					allAssTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		Collections.sort(allAssTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractAssType getAssTypeFromId(String id) {
		if(id.equals("IMP")) {
			return AssType.DEMON_COMMON;
		}
		
		id = Util.getClosestStringMatch(id, idToAssMap.keySet());
		return idToAssMap.get(id);
	}
	
	public static String getIdFromAssType(AbstractAssType assType) {
		return assToIdMap.get(assType);
	}
	
	public static List<AbstractAssType> getAllAssTypes() {
		return allAssTypes;
	}
	
	private static Map<AbstractRace, List<AbstractAssType>> typesMap = new HashMap<>();
	public static List<AbstractAssType> getAssTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractAssType> types = new ArrayList<>();
		for(AbstractAssType type : AssType.getAllAssTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}

}