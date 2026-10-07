package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4
 * @author Innoxia
 */
public enum PenetrationModifier {

	SHEATHED("带鞘", "在未勃起时可以完全隐藏在衣物中。"),
	
	RIBBED("螺纹", ""),
	
	TENTACLED("长有触手", ""),
	
	KNOTTED("底端有结", "允许在高潮时将整个插入腔穴中，使有效直径翻倍并与伴侣连锁在一起。(要求腔穴有足够的深度支持基部插入)"),
	
	BLUNT("圆头", ""),

	TAPERED("尖头", "降低5%有效直径。(与“平头”互斥)") {
		@Override
		public List<PenetrationModifier> getMutuallyExclusivePenetrationModifiers() {
			return Util.newArrayListOfValues(FLARED);
		}
	},
	
	FLARED("平头", "增加5%有效直径(与“尖头”互斥)") {
		@Override
		public List<PenetrationModifier> getMutuallyExclusivePenetrationModifiers() {
			return Util.newArrayListOfValues(TAPERED);
		}
	},
	
	BARBED("带有倒刺", ""),
	
	VEINY("青筋暴起", ""),
	
	PREHENSILE("灵活可控", ""),
	
	OVIPOSITOR("可产卵",
			"允许在当前插入的腔穴中产卵。(需要卵已经受精。卵无法产在已怀孕角色的阴道内。)",
			Util.newArrayListOfValues(
					SexAreaPenetration.PENIS,
					SexAreaPenetration.CLIT));
	
	private String name;
	private String description;
	private List<SexAreaPenetration> restrictedPenetrationTypes;

	private PenetrationModifier(String name, String description) {
		this(name, description, null);
	}
	
	private PenetrationModifier(String name, String description, List<SexAreaPenetration> restrictedPenetrationTypes) {
		this.name = name;
		this.description = description;
		this.restrictedPenetrationTypes = restrictedPenetrationTypes;
	}
	
	public static List<PenetrationModifier> getPenetrationModifiers() {
		return getPenetrationModifiers(null);
	}
	
	public static List<PenetrationModifier> getPenetrationModifiers(SexAreaPenetration penetrationType) {
		List<PenetrationModifier> penetrationModifiers = new ArrayList<>(Arrays.asList(PenetrationModifier.values()));
		penetrationModifiers.removeIf(pm->pm.getRestrictedPenetrationTypes()!=null && penetrationType!=null && !pm.getRestrictedPenetrationTypes().contains(penetrationType));
		return penetrationModifiers;
	}

	public List<PenetrationModifier> getMutuallyExclusivePenetrationModifiers() {
		return new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isSpecialEffects() {
		return !description.isEmpty();
	}
	
	public String getDescription() {
		if(description.isEmpty()) {
			return "没有游戏内实际效果。";
		}
		return description;
	}

	public List<SexAreaPenetration> getRestrictedPenetrationTypes() {
		return restrictedPenetrationTypes;
	}
}
