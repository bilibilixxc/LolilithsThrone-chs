package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * 
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class HornType {
	
	// If any more horn types are added, check to see that the potion TFs still work. (5 types is currently the maximum.)
	
	public static final AbstractHornType NONE = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"无",
			"角",
			"角",
			new ArrayList<>(),
			new ArrayList<>(),
			"<br/>[npc.Name]现在[style.boldTfGeneric(没有角)]。",
			"") {
	};

//	// Cows:
//	
//	public static final AbstractHornType BOVINE_CURVED = new AbstractHornType(
//			BodyCoveringType.HORN,
//			Race.COW_MORPH,
//			2,
//			"curved",
//			"horn",
//			"horns",
//			Util.newArrayListOfValues("curved", "bovine"),
//			Util.newArrayListOfValues("curved", "bovine", "smooth"),
//			"slightly-curved #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
//					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(curved #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
//			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], curved #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
//	};
//
//	public static final AbstractHornType BOVINE_STRAIGHT = new AbstractHornType(
//			BodyCoveringType.HORN,
//			Race.COW_MORPH,
//			2,
//			"straight",
//			"horn",
//			"horns",
//			Util.newArrayListOfValues("straight", "bovine"),
//			Util.newArrayListOfValues("straight", "bovine", "smooth"),
//			"sleek, straight #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
//					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(straight #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
//			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], straight #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
//	};

	// Reindeer:
	
	public static final AbstractHornType REINDEER_RACK = new AbstractHornType(
			BodyCoveringType.ANTLER,
			Race.REINDEER_MORPH,
			2,
			"分杈",
			"鹿角",
			"鹿角",
			Util.newArrayListOfValues("分杈", "驯鹿"),
			Util.newArrayListOfValues("分杈", "驯鹿"),
			"多分杈的[npc.horn]。"
					+ "<br/>[npc.Name]现在拥有[npc.hornsDeterminer][style.boldTfGeneric(驯鹿般的#IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)]。",
			"[npc.HornsDeterminer]呈[npc.hornColour(true)]，多分杈且[npc.hornSize]的[npc.horn]从[npc.her]的前额#IFnpc.getHornsPerRow()==1#THEN中间#ELSE顶端#ENDIF生长出来。") {
	};
	
	// Horse:
	
	public static final AbstractHornType HORSE_STRAIGHT = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.HORSE_MORPH,
			1,
			"独角",
			"角",
			"角",
			Util.newArrayListOfValues("竖直", "螺旋", "独"),
			Util.newArrayListOfValues("竖直", "螺旋", "独"),
			"竖直向上，带螺旋纹的独[npc.horn]。"
					+ "<br/>[npc.Name]现在拥有[npc.hornsDeterminer][style.boldTfGeneric(独[npc.horn])]。",
			"[npc.HornsDeterminer]呈[npc.hornColour(true)]，[npc.hornSize]的独[npc.horns]从[npc.her]的前额#IFnpc.getHornsPerRow()==1#THEN中间#ELSE顶端#ENDIF生长出来。") {
	};

	// Generic:

	public static final AbstractHornType ANTLERS = new AbstractHornType(
			BodyCoveringType.ANTLER,
			Race.NONE,
			2,
			"分杈",
			"鹿角",
			"鹿角",
			Util.newArrayListOfValues("分杈"),
			Util.newArrayListOfValues("分杈"),
			"多分杈的[npc.horn]。"
					+ "<br/>[npc.Name]现在拥有[npc.hornsDeterminer][style.boldTfGeneric(分杈的[npc.horn])]。",
			"[npc.HornsDeterminer]呈[npc.hornColour(true)]，分杈且[npc.hornSize]的[npc.horn]从[npc.her]的前额#IFnpc.getHornsPerRow()==1#THEN中间#ELSE顶端#ENDIF生长出来。") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType CURLED = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"盘绕",
			"角",
			"角",
			Util.newArrayListOfValues("盘绕"),
			Util.newArrayListOfValues("盘绕", "光滑"),
			"盘绕的[npc.horn]。"
					+ "<br/>[npc.Name]现在拥有[npc.hornsDeterminer][style.boldTfGeneric(盘绕的[npc.horn])]。",
			"[npc.HornsDeterminer]呈[npc.hornColour(true)]，盘绕且[npc.hornSize]的[npc.horn]从[npc.her]的前额#IFnpc.getHornsPerRow()==1#THEN中间#ELSE顶端#ENDIF生长出来。") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType SPIRAL = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"螺旋状", //Rasen no Chikara
			"角",
			"角",
			Util.newArrayListOfValues("螺旋状"),
			Util.newArrayListOfValues("螺旋状", "光滑"),
			"曲折螺旋的[npc.horn]。"
					+ "<br/>[npc.Name]现在拥有[npc.hornsDeterminer][style.boldTfGeneric(螺旋状的#IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)]。",
			"[npc.HornsDeterminer]呈[npc.hornColour(true)]，螺旋状且[npc.hornSize]的[npc.horn]从[npc.her]的前额#IFnpc.getHornsPerRow()==1#THEN中间#ELSE顶端#ENDIF生长出来。") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType CURVED = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"弯曲",
			"角",
			"角",
			Util.newArrayListOfValues("弯曲"),
			Util.newArrayListOfValues("弯曲", "光滑"),
			"略微弯曲的[npc.horn]。"
					+ "<br/>[npc.Name]现在拥有[style.boldTfGeneric(弯曲的#IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)]。",
			"[npc.HornsDeterminer]呈[npc.hornColour(true)]，弯曲且[npc.hornSize]的[npc.horn]从[npc.her]的前额#IFnpc.getHornsPerRow()==1#THEN中间#ELSE顶端#ENDIF生长出来。") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType SWEPT_BACK = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"反折",
			"角",
			"角",
			Util.newArrayListOfValues("反折"),
			Util.newArrayListOfValues("反折", "光滑"),
			"光滑的[npc.horn]，上端向后弯折，掠过头顶。"
					+ "<br/>[npc.Name]现在拥有[npc.hornsDeterminer][style.boldTfGeneric(反折的#IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)]。",
			"[npc.HornsDeterminer]呈[npc.hornColour(true)]，反折的[npc.hornSize]的[npc.horn]从[npc.her]的前额#IFnpc.getHornsPerRow()==1#THEN中间#ELSE顶端#ENDIF生长出来。") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType STRAIGHT = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"竖直",
			"角",
			"角",
			Util.newArrayListOfValues("竖直"),
			Util.newArrayListOfValues("竖直", "光滑"),
			"十分光滑，竖直向上的[npc.horn]。"
					+ "<br/>[npc.Name]现在拥有[npc.hornsDeterminer][style.boldTfGeneric(竖直向上的#IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)]。",
			"[npc.HornsDeterminer]呈[npc.hornColour(true)]，竖直向上的[npc.hornSize]的[npc.horn]从[npc.her]的前额#IFnpc.getHornsPerRow()==1#THEN中间#ELSE顶端#ENDIF生长出来。") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	
	private static List<AbstractHornType> allHornTypes;
	private static Map<AbstractHornType, String> hornToIdMap = new HashMap<>();
	private static Map<String, AbstractHornType> idToHornMap = new HashMap<>();
	
	static {
		allHornTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("horn")) {
					try {
						AbstractHornType type = new AbstractHornType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allHornTypes.add(type);
						hornToIdMap.put(type, id);
						idToHornMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("horn")) {
					try {
						AbstractHornType type = new AbstractHornType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allHornTypes.add(type);
						hornToIdMap.put(type, id);
						idToHornMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded horn types:
		
		Field[] fields = HornType.class.getFields();
		
		for(Field f : fields){
			if (AbstractHornType.class.isAssignableFrom(f.getType())) {
				
				AbstractHornType ct;
				try {
					ct = ((AbstractHornType) f.get(null));

					hornToIdMap.put(ct, f.getName());
					idToHornMap.put(f.getName(), ct);
					
					allHornTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allHornTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractHornType getHornTypeFromId(String id) {
		if(id.equals("BOVINE_CURVED")) {
			return CURVED;
		} else if(id.equals("BOVINE_STRAIGHT")) {
			return STRAIGHT;
		}
		id = Util.getClosestStringMatch(id, idToHornMap.keySet());
		return idToHornMap.get(id);
	}
	
	public static String getIdFromHornType(AbstractHornType hornType) {
		return hornToIdMap.get(hornType);
	}
	
	public static List<AbstractHornType> getAllHornTypes() {
		return allHornTypes;
	}
	
	private static Map<AbstractRace, List<AbstractHornType>> typesMap = new HashMap<>();
	
	/**
	 * 
	 * @param race The race whose available horn types are to be returned.
	 * @param retainNone Whether to leave HornType.NONE in the list (true) or remove it if it's present (false).
	 * @return A list of HornTypes which are available for this race to have <b>via transformation, not by default</b>. If you want to find out what HornTypes a race has by default, use their RacialBody's getHornTypes() method.
	 */
	public static List<AbstractHornType> getHornTypes(AbstractRace race, boolean retainNone) {
		if(!typesMap.containsKey(race)) {
			List<AbstractHornType> allTypes = new ArrayList<>();
			
			for(AbstractHornType type : HornType.getAllHornTypes()) {
				if(type.getRace()==race) {
					allTypes.add(type);
				}
			}
			if(allTypes.isEmpty()) {
				allTypes.add(HornType.NONE);
				for(AbstractHornType type : HornType.getAllHornTypes()) {
					if(type.isGeneric()) {
						allTypes.add(type);
					}
				}
			}
			
			typesMap.put(race, allTypes);
		}
		
		List<AbstractHornType> types = new ArrayList<>(typesMap.get(race));
		if(!retainNone) {
			types.remove(HornType.NONE);
		}
		return types;
	}
}
