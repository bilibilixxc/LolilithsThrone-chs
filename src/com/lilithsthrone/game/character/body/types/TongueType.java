package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractTongueType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class TongueType {
	
	public static AbstractTongueType HUMAN = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.HUMAN,
			3,
			"舌头",
			"舌头",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的嘴里有一根[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType ANGEL = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.ANGEL,
			3,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues("天使"),
			"[npc.Her]的嘴里有一根[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType DEMON_COMMON = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.DEMON,
			6,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			"[npc.Her]的嘴里有一根[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType DOG_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.DOG_MORPH,
			8,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("犬一般"),
			Util.newArrayListOfValues("犬一般"),
			"[npc.Her]的嘴里有一根犬一般[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues(
					TongueModifier.WIDE,
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType WOLF_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.WOLF_MORPH,
			8,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("狼一般"),
			Util.newArrayListOfValues("狼一般"),
			"[npc.Her]的嘴里有一根狼一般[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues(
					TongueModifier.WIDE,
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType FOX_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.FOX_MORPH,
			6,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("狐狸般"),
			Util.newArrayListOfValues("狐狸般"),
			"[npc.Her]的嘴里有一根狐狸般[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues(
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType CAT_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.CAT_MORPH,
			6,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("猫一般"),
			Util.newArrayListOfValues("猫一般"),
			"[npc.Her]的嘴里有一根猫一般[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues(
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType COW_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.COW_MORPH,
			12,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("牛一般"),
			Util.newArrayListOfValues("牛一般"),
			"[npc.Her]的嘴里有一根牛一般[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues(
					TongueModifier.STRONG)) {
	};
	
	public static AbstractTongueType ALLIGATOR_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.ALLIGATOR_MORPH,
			6,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("鳄鱼般"),
			Util.newArrayListOfValues("鳄鱼般"),
			"[npc.Her]的嘴里有一根鳄鱼般[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues(
					TongueModifier.STRONG)) {
	};
	
	public static AbstractTongueType HORSE_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.HORSE_MORPH,
			8,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("马一般"),
			Util.newArrayListOfValues("马一般"),
			"[npc.Her]的嘴里有一根马一般[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues(
					TongueModifier.STRONG)) {
	};
	
	public static AbstractTongueType REINDEER_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.REINDEER_MORPH,
			8,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("驯鹿般"),
			Util.newArrayListOfValues("驯鹿般"),
			"[npc.Her]的嘴里有一根驯鹿般[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues(
					TongueModifier.STRONG)) {
	};
	
	public static AbstractTongueType HARPY = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.HARPY,
			6,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("鸟一般"),
			Util.newArrayListOfValues("鸟一般"),
			"[npc.Her]的嘴里有一根鸟一般[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues(
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType SQUIRREL_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.SQUIRREL_MORPH,
			6,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("松鼠般"),
			Util.newArrayListOfValues("松鼠般"),
			"[npc.Her]的嘴里有一根松鼠般[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType RAT_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.RAT_MORPH,
			6,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("老鼠般"),
			Util.newArrayListOfValues("老鼠般"),
			"[npc.Her]的嘴里有一根老鼠般[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType RABBIT_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.RABBIT_MORPH,
			6,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("兔子般"),
			Util.newArrayListOfValues("兔子般"),
			"[npc.Her]的嘴里有一根兔子般[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType BAT_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.BAT_MORPH,
			6,
			"舌头",
			"舌头",
			Util.newArrayListOfValues("蝙蝠般"),
			Util.newArrayListOfValues("蝙蝠般"),
			"[npc.Her]的嘴里有一根蝙蝠般[npc.a_tongueLength]的[npc.tongue]，呈现[npc.tongueColour(true)]#IF(npc.isPiercedTongue())，且已经穿孔#ENDIF。",
			Util.newArrayListOfValues()) {
	};
	
	private static List<AbstractTongueType> allTongueTypes;
	private static Map<AbstractTongueType, String> tongueToIdMap = new HashMap<>();
	private static Map<String, AbstractTongueType> idToTongueMap = new HashMap<>();
	
	static {
		allTongueTypes = new ArrayList<>();
		
		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("tongue")) {
					try {
						AbstractTongueType type = new AbstractTongueType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTongueTypes.add(type);
						tongueToIdMap.put(type, id);
						idToTongueMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("tongue")) {
					try {
						AbstractTongueType type = new AbstractTongueType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTongueTypes.add(type);
						tongueToIdMap.put(type, id);
						idToTongueMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded tongue types:
		
		Field[] fields = TongueType.class.getFields();
		
		for(Field f : fields){
			if (AbstractTongueType.class.isAssignableFrom(f.getType())) {
				
				AbstractTongueType ct;
				try {
					ct = ((AbstractTongueType) f.get(null));

					tongueToIdMap.put(ct, f.getName());
					idToTongueMap.put(f.getName(), ct);
					
					allTongueTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allTongueTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractTongueType getTongueTypeFromId(String id) {
		if(id.equals("IMP")) {
			return TongueType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return TongueType.WOLF_MORPH;
		}
		if(id.equals("TENGU")) {
			return TongueType.HARPY;
		}
		
		id = Util.getClosestStringMatch(id, idToTongueMap.keySet());
		return idToTongueMap.get(id);
	}
	
	public static String getIdFromTongueType(AbstractTongueType tongueType) {
		return tongueToIdMap.get(tongueType);
	}
	
	public static List<AbstractTongueType> getAllTongueTypes() {
		return allTongueTypes;
	}
	
	private static Map<AbstractRace, List<AbstractTongueType>> typesMap = new HashMap<>();
	
	public static List<AbstractTongueType> getTongueTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractTongueType> types = new ArrayList<>();
		for(AbstractTongueType type : TongueType.getAllTongueTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}

}