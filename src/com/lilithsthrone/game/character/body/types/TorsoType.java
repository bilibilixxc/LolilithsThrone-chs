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
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.8.8
 * @author Innoxia
 */
public class TorsoType {
	
	public static AbstractTorsoType HUMAN = new AbstractTorsoType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干现在被人类皮肤所覆盖。"
				+ "<br/>[npc.Name]现在拥有[style.boldHuman(人类)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};

	public static AbstractTorsoType DEMON_COMMON = new AbstractTorsoType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干现在被"
					+ "#IF(npc.isShortStature())"
						+ "小恶魔"
					+ "#ELSE"
						+ "恶魔"
					+ "#ENDIF"
					+ "皮肤所覆盖。"
				+ "比通常人类的皮肤要细腻许多，全身的色调也变得极其和谐，能够凸显出[npc.her]的身材。"
				+ "<br/>[npc.Name]现在拥有"
				+ "#IF(npc.isShortStature())"
					+ "[style.boldImp(小恶魔)]"
				+ "#ELSE"
					+ "[style.boldDemon(恶魔)]"
				+ "#ENDIF"
				+ "[npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};

	public static AbstractTorsoType ANGEL = new AbstractTorsoType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干现在被天使皮肤所覆盖。"
				+ "比通常人类的皮肤要细腻许多，全身的色调也变得极其和谐，能够凸显出[npc.her]的身材。"
				+ "<br/>[npc.Name]现在拥有[style.boldAngel(天使)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};

	public static AbstractTorsoType COW_MORPH = new AbstractTorsoType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干被牛一般稍短的毛发所覆盖。"
				+ "[npc.Her]新生的毛发油亮光滑，虽然摸上去稍显粗糙，但能够凸显出[npc.her]的身材。"
				+ "<br/>[npc.Name]现在拥有[style.boldCowMorph(牛一般)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};

	public static AbstractTorsoType DOG_MORPH = new AbstractTorsoType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干现在被犬一般的皮毛所覆盖。"
				+ "[npc.Her]新生的皮毛紧贴着[npc.her]身材的线条，光滑柔顺，摸起来很舒服。"
				+ "<br/>[npc.Name]现在拥有[style.boldDogMorph(犬一般)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};

	public static AbstractTorsoType WOLF_MORPH = new AbstractTorsoType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干现在被狼一般的皮毛所覆盖。"
				+ "[npc.Her]新生的皮毛在关节处有些乱蓬蓬的，而且相当密集。"
				+ "<br/>[npc.Name]现在拥有[style.boldWolfMorph(狼一般)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};
	
	public static AbstractTorsoType FOX_MORPH = new AbstractTorsoType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干现在被狐狸般的皮毛所覆盖。"
				+ "[npc.Her]新生的皮毛在关节处有些乱蓬蓬的，而且相当密集。"
				+ "<br/>[npc.Name]现在拥有[style.boldFoxMorph(狐狸般)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};

	public static AbstractTorsoType CAT_MORPH = new AbstractTorsoType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干现在被猫一般的皮毛所覆盖。"
				+ "[npc.Her]新生的皮毛紧贴着[npc.her]身材的线条，格外地光滑柔顺。"
				+ "<br/>[npc.Name]现在拥有[style.boldCatMorph(猫一般)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};

	public static AbstractTorsoType SQUIRREL_MORPH = new AbstractTorsoType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干现在被松鼠般的皮毛所覆盖。"
				+ "[npc.Her]新生的皮毛紧贴着[npc.her]身材的线条，格外地光滑柔顺。"
				+ "<br/>[npc.Name]现在拥有[style.boldSquirrelMorph(松鼠般)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};

	public static AbstractTorsoType RAT_MORPH = new AbstractTorsoType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干现在被老鼠般的皮毛所覆盖。"
				+ "[npc.Her]新生的皮毛紧贴着[npc.her]身材的线条，但摸起来有些粗糙。"
				+ "<br/>[npc.Name]现在拥有[style.boldRatMorph(老鼠般)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};

	public static AbstractTorsoType RABBIT_MORPH = new AbstractTorsoType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干现在被兔子般的皮毛所覆盖。"
				+ "[npc.Her]新生的皮毛紧贴着[npc.her]身材的线条，格外地光滑柔顺。"
				+ "<br/>[npc.Name]现在拥有[style.boldRabbitMorph(兔子般)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};

	public static AbstractTorsoType BAT_MORPH = new AbstractTorsoType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干现在被蝙蝠般的皮毛所覆盖。"
				+ "[npc.Her]新生的皮毛紧贴着[npc.her]身材的线条，光滑柔顺，摸起来很舒服。"
				+ "<br/>[npc.Name]现在拥有[style.boldBatMorph(蝙蝠般)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};

	public static AbstractTorsoType ALLIGATOR_MORPH = new AbstractTorsoType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干现在被重叠的硬质鳞片所覆盖。"
				+ "[npc.Her]新生的鳞片紧贴着[npc.her]身材的线条，摸上去很坚硬，顺着方向抚摸也会很光滑。"
				+ "<br/>[npc.Name]现在拥有[style.boldGatorMorph(爬行动物般)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return true;
		}
	};

	public static AbstractTorsoType HORSE_MORPH = new AbstractTorsoType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干被马一般稍短的毛发所覆盖。"
				+ "[npc.Her]新生的毛发油亮光滑，虽然摸上去稍显粗糙，但能够凸显出[npc.her]的身材。"
				+ "<br/>[npc.Name]现在拥有[style.boldHorseMorph(马一般)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};

	public static AbstractTorsoType REINDEER_MORPH = new AbstractTorsoType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干被驯鹿般稍短的毛发所覆盖。"
				+ "[npc.Her]新生的毛发油亮光滑，虽然摸上去稍显粗糙，但能够凸显出[npc.her]的身材。"
				+ "<br/>[npc.Name]现在拥有[style.boldReindeerMorph(驯鹿般)][npc.skinFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
	};

	public static AbstractTorsoType HARPY = new AbstractTorsoType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"没过多久，转化就步入尾声，[npc.she]感觉瘙痒感终于褪去，长叹一声，[npc.her]的躯干现在被重叠的美丽羽毛所覆盖。"
				+ "[npc.Her]新生的羽毛紧贴着[npc.her]身材的线条，摸起来格外地光滑柔顺。"
				+ "<br/>[npc.She]现在拥有[style.boldHarpy(鸟一般)][npc.assholeFullDescription]。",
			"[npc.Her]的躯干看上去[npc.a_femininity(true)]，[npc.materialCompositionDescriptor][npc.skinFullDescription(true)]。") {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return true;
		}
	};
	
	
	private static List<AbstractTorsoType> allTorsoTypes;
	private static Map<AbstractTorsoType, String> torsoToIdMap = new HashMap<>();
	private static Map<String, AbstractTorsoType> idToTorsoMap = new HashMap<>();
	
	static {
		allTorsoTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("torso")) {
					try {
						AbstractTorsoType type = new AbstractTorsoType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTorsoTypes.add(type);
						torsoToIdMap.put(type, id);
						idToTorsoMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("torso")) {
					try {
						AbstractTorsoType type = new AbstractTorsoType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTorsoTypes.add(type);
						torsoToIdMap.put(type, id);
						idToTorsoMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded torso types:
		
		Field[] fields = TorsoType.class.getFields();
		
		for(Field f : fields){
			if (AbstractTorsoType.class.isAssignableFrom(f.getType())) {
				
				AbstractTorsoType ct;
				try {
					ct = ((AbstractTorsoType) f.get(null));

					torsoToIdMap.put(ct, f.getName());
					idToTorsoMap.put(f.getName(), ct);
					
					allTorsoTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allTorsoTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractTorsoType getTorsoTypeFromId(String id) {
		Map<String, String> torsoTypeConverterMap = new HashMap<>();
		torsoTypeConverterMap.put("IMP", "DEMON_COMMON");
		torsoTypeConverterMap.put("CANINE_FUR", "DOG_MORPH");
		torsoTypeConverterMap.put("LYCAN_FUR", "LYCAN");
		torsoTypeConverterMap.put("LYCAN", "WOLF_MORPH");
		torsoTypeConverterMap.put("FELINE_FUR", "CAT_MORPH");
		torsoTypeConverterMap.put("SQUIRREL_FUR", "SQUIRREL_MORPH");
		torsoTypeConverterMap.put("HORSE_HAIR", "HORSE_MORPH");
		torsoTypeConverterMap.put("SLIME", "SLIME");
		torsoTypeConverterMap.put("FEATHERS", "HARPY");
		if(torsoTypeConverterMap.containsKey(id)) {
			id = torsoTypeConverterMap.get(id);
		}
		
		id = Util.getClosestStringMatch(id, idToTorsoMap.keySet());
		return idToTorsoMap.get(id);
	}
	
	public static String getIdFromTorsoType(AbstractTorsoType torsoType) {
		return torsoToIdMap.get(torsoType);
	}
	
	public static List<AbstractTorsoType> getAllTorsoTypes() {
		return allTorsoTypes;
	}
	
	private static Map<AbstractRace, List<AbstractTorsoType>> typesMap = new HashMap<>();
	
	public static List<AbstractTorsoType> getTorsoTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractTorsoType> types = new ArrayList<>();
		for(AbstractTorsoType type : TorsoType.getAllTorsoTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}