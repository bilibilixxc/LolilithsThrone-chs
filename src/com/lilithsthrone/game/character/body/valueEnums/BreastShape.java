package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.86
 * @version 0.3.1
 * @author Innoxia
 */
public enum BreastShape {

	UDDERS(true, "腹乳", "似兽"),
	
	ROUND(false, "圆润", "圆润"),
	POINTY(false, "尖挺", "尖挺"),
	PERKY(false, "上翘", "上翘"),
	SIDE_SET(false, "分向两侧", "分向两侧"),
	WIDE(false, "宽阔", "宽阔"),
	NARROW(false, "狭窄", "狭窄");
	
	private boolean restrictedToCrotchBoobs;
	private String transformName;
	private String descriptor;

	private BreastShape(boolean restrictedToCrotchBoobs, String transformName, String descriptor) {
		this.restrictedToCrotchBoobs = restrictedToCrotchBoobs;
		this.transformName = transformName;
		this.descriptor = descriptor;
	}

	public boolean isRestrictedToCrotchBoobs() {
		return restrictedToCrotchBoobs;
	}

	public String getTransformName() {
		return transformName;
	}
	
	public String getDescriptor() {
		return descriptor;
	}
	
	public static List<BreastShape> getUdderBreastShapes() {
		return getBreastShapes(true);
	}
	
	public static List<BreastShape> getNonUdderBreastShapes() {
		return getBreastShapes(false);
	}
	
	private static List<BreastShape> getBreastShapes(boolean udders) {
		List<BreastShape> shapes = new ArrayList<>();
		
		for(BreastShape shape : BreastShape.values()) {
			if(shape.isRestrictedToCrotchBoobs()==udders) {
				shapes.add(shape);
			}
		}
		
		return shapes;
	}
}
