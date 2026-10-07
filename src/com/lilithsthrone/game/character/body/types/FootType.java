package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFootType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.10
 * @version 0.4
 * @author Innoxia
 */
public class FootType {

	public static AbstractFootType NONE = new AbstractFootType("无",
			"无",
			"无",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"无",
			"无",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"足交",
			"[npc.SheHasFull]没有脚。",
			Util.newArrayListOfValues(FootStructure.NONE)) {
				@Override
				public String getFootNailPolishDescription(GameCharacter owner) {
					return "";
				}
	};
	
	public static AbstractFootType HUMANOID = new AbstractFootType("人类",
			"脚",
			"脚",
			Util.newArrayListOfValues("阳刚"),
			Util.newArrayListOfValues("阴柔", "柔软", "精致", "修长"),
			"脚趾",
			"脚趾",
			Util.newArrayListOfValues("阳刚"),
			Util.newArrayListOfValues("阴柔", "柔软", "精致", "修长"),
			"足交",
			"[npc.SheHasFull]拥有像人一样的脚。",
			Util.newArrayListOfValues(FootStructure.PLANTIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return "[npc.Her]的脚趾甲上涂着"+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+"。";
		}
	};

	public static AbstractFootType PAWS = new AbstractFootType("爪一般",
			"脚爪",
			"脚爪",
			Util.newArrayListOfValues("男性化","带肉垫"),
			Util.newArrayListOfValues("阴柔", "柔软", "带肉垫", "精致", "纤细"),
			"脚趾",
			"脚趾",
			Util.newArrayListOfValues("阳刚", "肉垫"),
			Util.newArrayListOfValues("阴柔", "柔软", "带肉垫", "精致", "纤细"),
			"足交",
			"[npc.SheHasFull]拥有爪一般的脚。",
			Util.newArrayListOfValues(
					FootStructure.PLANTIGRADE,
					FootStructure.DIGITIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return "[npc.Her]的脚趾甲上涂着"+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+"。";
		}
	};

	public static AbstractFootType HOOFS = new AbstractFootType("蹄子般",
			"蹄子",
			"蹄子",
			Util.newArrayListOfValues("阳刚","坚硬"),
			Util.newArrayListOfValues("阴柔", "精致", "坚硬"),
			"蹄",
			"蹄子",
			Util.newArrayListOfValues("阳刚", "坚硬"),
			Util.newArrayListOfValues("阴柔", "坚硬", "精致"),
			"蹄交",
			"[npc.SheHasFull]拥有蹄子而非脚。",
			Util.newArrayListOfValues(
					FootStructure.UNGULIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return "[npc.Her]的蹄子涂上了"+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+"。";
		}
	};

	public static AbstractFootType REPTILIAN = new AbstractFootType("爬行类",
			"脚",
			"脚",
			Util.newArrayListOfValues("阳刚","带爪"),
			Util.newArrayListOfValues("阴柔", "带爪", "修长"),
			"脚趾",
			"脚趾",
			Util.newArrayListOfValues("阳刚", "带爪"),
			Util.newArrayListOfValues("阴柔", "带爪", "修长"),
			"足交",
			"[npc.SheHasFull]拥有爬行类的脚。",
			Util.newArrayListOfValues(
					FootStructure.PLANTIGRADE,
					FootStructure.DIGITIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return "在[npc.her]粗短脚趾顶端的爪子上涂着"+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+"。";
		}
	};

	public static AbstractFootType AMPHIBIAN = new AbstractFootType("两栖类",
			"脚",
			"脚",
			Util.newArrayListOfValues("阳刚", "带蹼"),
			Util.newArrayListOfValues("阴柔", "带蹼", "修长"),
			"脚趾",
			"脚趾",
			Util.newArrayListOfValues("阳刚", "带蹼"),
			Util.newArrayListOfValues("阴柔", "带蹼", "修长"),
			"足交",
			"[npc.SheHasFull]拥有两栖类的脚。",
			Util.newArrayListOfValues(
					FootStructure.PLANTIGRADE,
					FootStructure.DIGITIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return "[npc.her]脚趾的顶端涂着"+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+"。";
		}
	};

	public static AbstractFootType TALONS = new AbstractFootType("鸟一般",
			"利爪",
			"利爪",
			Util.newArrayListOfValues("阳刚","带爪"),
			Util.newArrayListOfValues("阴柔", "带爪", "修长"),
			"爪子",
			"爪子",
			Util.newArrayListOfValues("阳刚", "尖锐"),
			Util.newArrayListOfValues("阴柔", "尖锐", "修长"),
			"爪交",
			"[npc.SheHasFull]拥有鸟一样的利爪而非脚。",
			Util.newArrayListOfValues(
					FootStructure.DIGITIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return "[npc.her]鸟爪的爪子上涂着"+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+"。";
		}
	};

	public static AbstractFootType ARACHNID = new AbstractFootType("蛛形",
			"脚",
			"脚",
			Util.newArrayListOfValues("阳刚","节节分明"),
			Util.newArrayListOfValues("阴柔", "节节分明", "纤细"),
			"爪子",
			"爪子",
			Util.newArrayListOfValues("阳刚", "尖锐"),
			Util.newArrayListOfValues("阴柔", "尖锐", "修长"),
			"爪交",
			"[npc.SheHasFull]拥有蛛形爪而非脚。",
			Util.newArrayListOfValues(
					FootStructure.PLANTIGRADE,
					FootStructure.DIGITIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return "[npc.her]蛛形的爪上涂了"+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+"。";
		}
	};

	public static AbstractFootType TENTACLE = new AbstractFootType("触手",
			"触手",
			"触手",
			Util.newArrayListOfValues("阳刚","有力"),
			Util.newArrayListOfValues("阴柔", "有力", "修长"),
			"吸盘",
			"吸盘",
			Util.newArrayListOfValues("有力"),
			Util.newArrayListOfValues("有力"),
			"触手淫",
			"[npc.her]的触手顶端可以当做脚来用。",
			Util.newArrayListOfValues(
					FootStructure.TENTACLED)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return "[npc.her]触手的顶端涂着"+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+"。";
		}
	};
	
	

	private static List<AbstractFootType> allFootTypes;
	private static Map<AbstractFootType, String> footToIdMap = new HashMap<>();
	private static Map<String, AbstractFootType> idToFootMap = new HashMap<>();
	
	static {
		allFootTypes = new ArrayList<>();
		
		// Add in hard-coded foot types:
		Field[] fields = FootType.class.getFields();
		
		for(Field f : fields){
			if (AbstractFootType.class.isAssignableFrom(f.getType())) {
				
				AbstractFootType ct;
				try {
					ct = ((AbstractFootType) f.get(null));

					footToIdMap.put(ct, f.getName());
					idToFootMap.put(f.getName(), ct);
					
					allFootTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AbstractFootType getFootTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToFootMap.keySet());
		return idToFootMap.get(id);
	}
	
	public static String getIdFromFootType(AbstractFootType footType) {
		return footToIdMap.get(footType);
	}
	
	public static List<AbstractFootType> getAllFootTypes() {
		return allFootTypes;
	}
}
