package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.8.2
 * @author Innoxia
 */
public class WingType {

	// If any more wing types are added, check to see that the potion TFs still work. (5 types is currently the maximum.)
	
	public static final AbstractWingType NONE = new AbstractWingType(
			null,
			Race.NONE,
			false,
			"无",
			"翅膀",
			"翅膀",
			new ArrayList<>(),
			new ArrayList<>(),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
					+ "伴随着强烈的拉扯感，[npc.her]的[npc.wings]逐渐缩小，消失在[npc.legConfiguration]身体的两侧。"
			+ "#ELSE"
				+ "伴随着强烈的拉扯感，[npc.her]的[npc.wings]逐渐缩小，从背上消失了。"
			+ "#ENDIF"
			+ "<br/>[npc.Name]现在[style.boldTfGeneric(没有翅膀)]。",
			"") {
	};

	// Angels:
	
	public static final AbstractWingType ANGEL = new AbstractWingType(
			BodyCoveringType.ANGEL_FEATHER,
			Race.ANGEL,
			true,
			"天使(覆羽)",
			"翅膀",
			"翅膀",
			Util.newArrayListOfValues("天使", "羽毛覆盖"),
			Util.newArrayListOfValues("天使", "羽毛覆盖"),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
				+ "[npc.She]紧咬下唇，希望能压抑住始料未及的愉悦呻吟，一对覆盖着羽毛的[npc.wingSize]天使翅膀随之从[npc.her][npc.legConfiguration]身体的两侧长了出来。"
			+ "#ELSE"
				+ "[npc.She]紧咬下唇，希望能压抑住始料未及的愉悦呻吟，一对覆盖着羽毛的[npc.wingSize]天使翅膀随之从[npc.her]的肩胛骨位置长了出来。"
			+ "#ENDIF"
			+ "<br/>"
			+ "[npc.Name]现在拥有[style.boldAngel(羽毛覆盖的天使翅膀)]。",
			"[npc.sheHasFull]拥有一对羽毛覆盖的[npc.wingSize]天使翅膀，[npc.materialDescriptor][npc.wingFullDescription(true)]。") {
	};

	// Demons:
	
	public static final AbstractWingType DEMON_COMMON = new AbstractWingType(
			BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			true,
			"恶魔(皮质)",
			"翅膀",
			"翅膀",
			Util.newArrayListOfValues("恶魔", "皮质"),
			Util.newArrayListOfValues("恶魔", "皮质"),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
				+ "[npc.She]紧咬下唇，希望能压抑住始料未及的愉悦呻吟，一对覆盖有皮膜的[npc.wingSize]恶魔翅膀随之从[npc.her][npc.legConfiguration]身体的两侧长了出来。"
			+ "#ELSE"
				+ "[npc.She]紧咬下唇，希望能压抑住始料未及的愉悦呻吟，一对覆盖有皮膜的[npc.wingSize]恶魔翅膀随之从[npc.her]的肩胛骨位置长了出来。"
			+ "#ENDIF"
			+ "<br/>"
			+ "#IF(npc.isShortStature())"
				+ "[npc.Name]现在拥有[style.boldImp(覆盖有皮膜的小恶魔翅膀)]。"
			+ "#ELSE"
				+ "[npc.Name]现在拥有[style.boldDemon(覆盖有皮膜的恶魔翅膀)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]拥有一对覆盖有皮膜的[npc.wingSize]恶魔翅膀，[npc.materialDescriptor][npc.wingFullDescription(true)]。") {
	};

	public static final AbstractWingType DEMON_FEATHERED = new AbstractWingType(
			BodyCoveringType.DEMON_FEATHER,
			Race.DEMON,
			true,
			"恶魔(覆羽)",
			"翅膀",
			"翅膀",
			Util.newArrayListOfValues("恶魔", "羽毛覆盖"),
			Util.newArrayListOfValues("恶魔", "羽毛覆盖"),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
				+ "[npc.She]紧咬下唇，希望能压抑住始料未及的愉悦呻吟，一对覆盖着羽毛的[npc.wingSize]恶魔翅膀随之从[npc.her]的肩胛骨位置长了出来。"
			+ "#ELSE"
				+ "[npc.She]紧咬下唇，希望能压抑住始料未及的愉悦呻吟，一对覆盖着羽毛的[npc.wingSize]恶魔翅膀随之从[npc.her]的肩胛骨位置长了出来。"
			+ "#ENDIF"
			+ "<br/>"
			+ "#IF(npc.isShortStature())"
				+ "[npc.Name]现在拥有[style.boldImp(羽毛覆盖的小恶魔翅膀)]。"
			+ "#ELSE"
				+ "[npc.Name]现在拥有[style.boldDemon(羽毛覆盖的恶魔翅膀)]。"
			+ "#ENDIF",
			"[npc.sheHasFull]拥有一对羽毛覆盖的[npc.wingSize]恶魔翅膀，[npc.materialDescriptor][npc.wingFullDescription(true)]。") {
	};
	
	// Generic:

	public static final AbstractWingType LEATHERY = new AbstractWingType(
			BodyCoveringType.WING_LEATHER,
			Race.NONE,
			true,
			"皮质",
			"翅膀",
			"翅膀",
			Util.newArrayListOfValues("皮质"),
			Util.newArrayListOfValues("皮质"),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
				+ "[npc.She]紧咬下唇，希望能压抑住始料未及的愉悦呻吟，一对覆盖有皮膜的[npc.wingSize]翅膀随之从[npc.her][npc.legConfiguration]身体的两侧长了出来。"
			+ "#ELSE"
				+ "[npc.She]紧咬下唇，希望能压抑住始料未及的愉悦呻吟，一对覆盖有皮膜的[npc.wingSize]翅膀随之从[npc.her]的肩胛骨位置长了出来。"
			+ "#ENDIF"
			+ "<br/>"
			+ "[npc.Name]现在拥有[style.boldTfGeneric(覆盖有皮膜的翅膀)]。",
			"[npc.sheHasFull]拥有一对覆盖有皮膜的[npc.wingSize]翅膀，[npc.materialDescriptor][npc.wingFullDescription(true)]。") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};

	public static final AbstractWingType FEATHERED = new AbstractWingType(
			BodyCoveringType.FEATHERS,
			Race.NONE,
			true,
			"羽毛",
			"翅膀",
			"翅膀",
			Util.newArrayListOfValues("羽毛覆盖"),
			Util.newArrayListOfValues("羽毛覆盖"),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
				+ "[npc.She]紧咬下唇，希望能压抑住始料未及的愉悦呻吟，一对覆盖着羽毛的[npc.wingSize]翅膀随之从[npc.her][npc.legConfiguration]身体的两侧长了出来。"
			+ "#ELSE"
				+ "[npc.She]紧咬下唇，希望能压抑住始料未及的愉悦呻吟，一对覆盖着羽毛的[npc.wingSize]翅膀随之从[npc.her]的肩胛骨位置长了出来。"
			+ "#ENDIF"
			+ "<br/>"
			+ "[npc.Name]现在拥有[style.boldTfGeneric(羽毛覆盖的翅膀)]。",
			"[npc.sheHasFull]拥有一对羽毛覆盖的[npc.wingSize]翅膀，[npc.materialDescriptor][npc.wingFullDescription(true)]。") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};

	public static final AbstractWingType INSECT = new AbstractWingType(
			BodyCoveringType.WING_CHITIN,
			Race.NONE,
			true,
			"昆虫",
			"翅膀",
			"翅膀",
			Util.newArrayListOfValues("几丁质"),
			Util.newArrayListOfValues("几丁质"),
			"#IF(npc.getLegConfiguration().isWingsOnLegConfiguration())"
				+ "[npc.She]紧咬下唇，希望能压抑住始料未及的愉悦呻吟，一对昆虫般的[npc.wingSize]翅膀随之从[npc.her][npc.legConfiguration]身体的两侧长了出来。"
			+ "#ELSE"
				+ "[npc.She]紧咬下唇，希望能压抑住始料未及的愉悦呻吟，一对昆虫般的[npc.wingSize]翅膀随之从[npc.her]的肩胛骨位置长了出来。"
			+ "#ENDIF"
			+ "<br/>"
			+ "[npc.Name]现在拥有[style.boldTfGeneric(昆虫般的翅膀)]。",
			"[npc.sheHasFull]拥有一对昆虫般的[npc.wingSize]翅膀，[npc.materialDescriptor][npc.wingFullDescription(true)]。") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};


	private static List<AbstractWingType> allWingTypes;
	private static Map<AbstractWingType, String> wingToIdMap = new HashMap<>();
	private static Map<String, AbstractWingType> idToWingMap = new HashMap<>();
	
	static {
		allWingTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("wing")) {
					try {
						AbstractWingType type = new AbstractWingType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allWingTypes.add(type);
						wingToIdMap.put(type, id);
						idToWingMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("wing")) {
					try {
						AbstractWingType type = new AbstractWingType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allWingTypes.add(type);
						wingToIdMap.put(type, id);
						idToWingMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded wing types:
		
		Field[] fields = WingType.class.getFields();
		
		for(Field f : fields){
			if (AbstractWingType.class.isAssignableFrom(f.getType())) {
				
				AbstractWingType ct;
				try {
					ct = ((AbstractWingType) f.get(null));

					wingToIdMap.put(ct, f.getName());
					idToWingMap.put(f.getName(), ct);
					
					allWingTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allWingTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractWingType getWingTypeFromId(String id) {
		if(id.equals("IMP")) {
			return WingType.DEMON_COMMON;
		}
		if(id.equals("PEGASUS")) {
			return WingType.FEATHERED;
		}
		id = Util.getClosestStringMatch(id, idToWingMap.keySet());
		return idToWingMap.get(id);
	}
	
	public static String getIdFromWingType(AbstractWingType wingType) {
		return wingToIdMap.get(wingType);
	}
	
	public static List<AbstractWingType> getAllWingTypes() {
		return allWingTypes;
	}
	
	private static Map<AbstractRace, List<AbstractWingType>> typesMap = new HashMap<>();
	
	public static List<AbstractWingType> getWingTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractWingType> types = new ArrayList<>();
		for(AbstractWingType type : WingType.getAllWingTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		if(types.isEmpty()) {
			for(AbstractWingType type : WingType.getAllWingTypes()) {
				if(type.isGeneric()) {
					types.add(type);
				}
			}
		}
		typesMap.put(r, types);
		return types;
	}
}
