package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.3.8.9
 * @author Innoxia
 */
public class TentacleType {
	
	public static final AbstractTentacleType NONE = new AbstractTentacleType(
			null,
			Race.NONE,
			PenetrationGirth.THREE_AVERAGE,
			0f,
			"无",
			"",
			"",
			"触手",
			"触手",
			Util.newArrayListOfValues(),
			Util.newArrayListOfValues(),
			"",
			"",
			Util.newArrayListOfValues(),
			Util.newArrayListOfValues(),
			"#IF(npc.getTentacleCount()==1)"
					+ "当[npc.She]感到[npc.tentacle]逐渐缩小，并消失在[npc.her]体内时，[npc.She]倒吸了一口凉气。"
				+ "#ELSE"
					+ "当[npc.She]感到[npc.tentacle]逐渐缩小，并消失在[npc.her]体内时，[npc.She]倒吸了一口凉气。"
				+ "#ENDIF"
				+ "<br/>"
				+ "[npc.Name]现在[style.boldTfGeneric(没有触手)]。",
			"[style.colourDisabled([npc.She]没有触手。)]",
			Util.newArrayListOfValues()) {
	};
	
	public static final AbstractTentacleType DEMON_COMMON = new AbstractTentacleType(
			BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			PenetrationGirth.ONE_SLENDER,
			1f,
			"恶魔",
			"",
			"",
			"触手",
			"触手",
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			"尖端",
			"尾尖",
			Util.newArrayListOfValues("圆润"),
			Util.newArrayListOfValues("圆润"),
			"#IF(npc.getTentacleCount()==1)"
				+ "一条恶魔触手从背部延伸而出，迅速生长至大概有[npc.tentacleLength]长。"
				+ "[npc.She]很快意识到自己能够随心所欲地控制其运动，仿佛拥有了额外的肢体。"
				+ "<br/>"
				+ "[npc.Name]现在拥有"
				+ "#IF(npc.isShortStature())"
					+ "一条[style.boldImp(小恶魔触手)]"
				+ "#ELSE"
					+ "一条[style.boldDemon(恶魔触手)]"
				+ "#ENDIF"
				+ "，[npc.materialDescriptor][npc.tentacleFullDescription(true)]。"
			+ "#ELSE"
				+ "[npc.TentacleCount]条恶魔触手从背部延伸而出，迅速生长至大概有[npc.tentacleLength]长。"
				+ "[npc.She]很快意识到[npc.she]能够随心所欲地控制它们，就像拥有了额外的肢体一样。"
				+ "<br/>"
				+ "[npc.Name]现在拥有[npc.tentacleCount]条"
				+ "#IF(npc.isShortStature())"
					+ "[style.boldImp(小恶魔触手)]"
				+ "#ELSE"
					+ "[style.boldDemon(恶魔触手)]"
				+ "#ENDIF"
				+ "，[npc.materialDescriptor][npc.tentacleFullDescription(true)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]从背部长出"
				+ "#IF(npc.getTentacleCount()==1)"
					+ "一条桃心状的[npc.tentacleColour(true)]#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF触手。[npc.sheHasFull]能自如地掌控其运动，帮助[npc.herHim]抓握物体。"
				+ "#ELSE"
					+ "[npc.sheHasFull]拥有[npc.tentacleCount]条桃心状的，[npc.tentacleColour(true)]#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF触手。它们活动自如，能够帮助[npc.herHim]抓取并握住物体。"
				+ "#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TAPERING_NONE)) {
	};
	
	public static final AbstractTentacleType LEG_DEMON_OCTOPUS = new AbstractTentacleType(
			BodyCoveringType.OCTOPUS_SKIN,
			Race.DEMON,
			PenetrationGirth.FOUR_GIRTHY,
			2.5f,
			"恶魔章鱼",
			"",
			"",
			"触手",
			"触手",
			Util.newArrayListOfValues("恶魔"),
			Util.newArrayListOfValues("恶魔"),
			"尾尖",
			"尾尖",
			Util.newArrayListOfValues("圆润"),
			Util.newArrayListOfValues("圆润"),
			"",
			"在腿的位置上，[npc.sheHasFull]长出了[npc.tentacleCount]条[npc.tentacleColour(true)]，章鱼一样的触手。它们活动自如，能够帮助[npc.herHim]抓取并握住物体。",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TAPERING_NONE)) {
	};
	

	private static List<AbstractTentacleType> allTentacleTypes;
	private static Map<AbstractTentacleType, String> tentacleToIdMap = new HashMap<>();
	private static Map<String, AbstractTentacleType> idToTentacleMap = new HashMap<>();
	
	static {
		allTentacleTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("tentacle")) {
					try {
						AbstractTentacleType type = new AbstractTentacleType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTentacleTypes.add(type);
						tentacleToIdMap.put(type, id);
						idToTentacleMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("tentacle")) {
					try {
						AbstractTentacleType type = new AbstractTentacleType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTentacleTypes.add(type);
						tentacleToIdMap.put(type, id);
						idToTentacleMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded tentacle types:
		
		Field[] fields = TentacleType.class.getFields();
		
		for(Field f : fields){
			if (AbstractTentacleType.class.isAssignableFrom(f.getType())) {
				
				AbstractTentacleType ct;
				try {
					ct = ((AbstractTentacleType) f.get(null));

					tentacleToIdMap.put(ct, f.getName());
					idToTentacleMap.put(f.getName(), ct);
					
					allTentacleTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allTentacleTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractTentacleType getTentacleTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToTentacleMap.keySet());
		return idToTentacleMap.get(id);
	}
	
	public static String getIdFromTentacleType(AbstractTentacleType tentacleType) {
		return tentacleToIdMap.get(tentacleType);
	}
	
	public static List<AbstractTentacleType> getAllTentacleTypes() {
		return allTentacleTypes;
	}
	
	private static Map<AbstractRace, List<AbstractTentacleType>> typesMap = new HashMap<>();
	
	public static List<AbstractTentacleType> getTentacleTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractTentacleType> types = new ArrayList<>();
		for(AbstractTentacleType type : TentacleType.getAllTentacleTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		if(types.isEmpty()) {
			types.add(TentacleType.NONE);
		}
		typesMap.put(r, types);
		return types;
	}
	
	public static List<AbstractTentacleType> getTentacleTypesSuitableForTransformation(List<AbstractTentacleType> options) {
		if (!options.contains(TentacleType.NONE)) {
			return options;
		}
		
		List<AbstractTentacleType> duplicatedOptions = new ArrayList<>(options);
		duplicatedOptions.remove(TentacleType.NONE);
		return duplicatedOptions;
	}
}
