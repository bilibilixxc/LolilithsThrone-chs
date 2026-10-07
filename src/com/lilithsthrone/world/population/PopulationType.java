package com.lilithsthrone.world.population;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.12
 * @version 0.3.9
 * @author Innoxia
 */
public class PopulationType {

	public static AbstractPopulationType PERSON = new AbstractPopulationType("人", "人") {};
	
	public static AbstractPopulationType FAN = new AbstractPopulationType("粉丝", "粉丝") {};
	
	public static AbstractPopulationType HARPY = new AbstractPopulationType("哈比", "哈比") {
		@Override
		public String getName() {
			if(Main.game.isSillyModeEnabled()) {
				return "啾";
			}
			return "哈比";
		}
		@Override
		public String getNamePlural() {
			if(Main.game.isSillyModeEnabled()) {
				return "啾";
			}
			return "哈比";
		}
	};
	
	public static AbstractPopulationType CROWD = new AbstractPopulationType("人群", "人群") {};

	public static AbstractPopulationType PRIVATE_SECURITY_GUARD = new AbstractPopulationType("私人护卫", "私人护卫") {};
	
	public static AbstractPopulationType ENFORCER = new AbstractPopulationType("执法者", "执法者") {};
	
	public static AbstractPopulationType SWORD = new AbstractPopulationType("SWORD执法者", "SWORD执法者") {};

	public static AbstractPopulationType CENTAUR_CARTS = new AbstractPopulationType("半人马拉车", "半人马拉车") {};
	
	public static AbstractPopulationType SHOPPER = new AbstractPopulationType("顾客", "顾客") {};
	
	public static AbstractPopulationType DINER = new AbstractPopulationType("用餐者", "用餐者") {};

	public static AbstractPopulationType VIP = new AbstractPopulationType("VIP", "VIP") {};
	
	public static AbstractPopulationType GUARD = new AbstractPopulationType("保安", "保安") {};
	
	public static AbstractPopulationType SECURITY_GUARD = new AbstractPopulationType("护卫", "护卫") {};

	public static AbstractPopulationType MAID = new AbstractPopulationType("女仆", "女仆") {};

	public static AbstractPopulationType CHEF = new AbstractPopulationType("厨师", "厨师") {};

	public static AbstractPopulationType SLAVE = new AbstractPopulationType("奴隶", "奴隶") {};
	
	public static AbstractPopulationType OFFICE_WORKER = new AbstractPopulationType("办公室职员", "办公室职员") {};
	
	public static AbstractPopulationType TEXTILE_WORKER = new AbstractPopulationType("纺织厂工人", "纺织厂工人") {};
	
	public static AbstractPopulationType CONSTRUCTION_WORKER = new AbstractPopulationType("建筑工人", "建筑工人") {};
	
	public static AbstractPopulationType RECEPTIONIST = new AbstractPopulationType("接待员", "接待员") {};

	public static AbstractPopulationType GANG_MEMBER = new AbstractPopulationType("帮派成员", "帮派成员") {};

	public static AbstractPopulationType STALL_HOLDER = new AbstractPopulationType("摊主", "摊主") {};

	public static AbstractPopulationType MILKER = new AbstractPopulationType("挤奶人", "挤奶人") {};
	
	public static AbstractPopulationType CASHIER = new AbstractPopulationType("收银员", "收银员") {};
	
	public static AbstractPopulationType CLERK = new AbstractPopulationType("文员", "文员") {};
	
	public static AbstractPopulationType MASSEUSE = new AbstractPopulationType("按摩师", "按摩师") {};
	
	public static AbstractPopulationType AMAZON = new AbstractPopulationType("亚马逊人", "亚马逊人") {};
	
	public static AbstractPopulationType AMAZON_GUARD = new AbstractPopulationType("亚马逊护卫", "亚马逊护卫") {};
	
	public static AbstractPopulationType LUNETTE_DAUGTHER = new AbstractPopulationType("露内特的女儿", "露内特的女儿") {};
	
	public static AbstractPopulationType COCK_SLEEVE = new AbstractPopulationType("阴茎套", "阴茎套") {};

	public static AbstractPopulationType DOLL = new AbstractPopulationType("玩偶", "玩偶") {};

	public static AbstractPopulationType OVERSEER = new AbstractPopulationType("监督员", "监督员") {};
	
	
	private static List<AbstractPopulationType> allPopulationTypes = new ArrayList<>();
	private static Map<AbstractPopulationType, String> populationToIdMap = new HashMap<>();
	private static Map<String, AbstractPopulationType> idToPlaceMap = new HashMap<>();

	public static List<AbstractPopulationType> getAllPopulationTypes() {
		return allPopulationTypes;
	}
	
	public static boolean hasId(String id) {
		return idToPlaceMap.keySet().contains(id);
	}
	
	public static AbstractPopulationType getPopulationTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToPlaceMap.keySet());
		return idToPlaceMap.get(id);
	}

	public static String getIdFromPopulationType(AbstractPopulationType populationType) {
		return populationToIdMap.get(populationType);
	}
	
	static {
		// Hard-coded population types (all those up above):
		
		Field[] fields = PopulationType.class.getFields();
		
		for(Field f : fields) {
			if(AbstractPopulationType.class.isAssignableFrom(f.getType())) {
				AbstractPopulationType populationType;
				try {
					populationType = ((AbstractPopulationType) f.get(null));

					populationToIdMap.put(populationType, f.getName());
					idToPlaceMap.put(f.getName(), populationType);
					allPopulationTypes.add(populationType);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
