package com.lilithsthrone.game.character.body.valueEnums;

import java.util.List;

import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

import javafx.scene.paint.Color;

/**
 * @since 0.1.83
 * @version 0.2.8
 * @author Innoxia
 */
public enum BodyShape {
	
	/*
	 * Ectomorph: Lean and long, no muscle
	 * Endomorph: Big, high body fat
	 * Mesomorph: Muscular and well-built
	 */
	
	// BodySize == ZERO_SKINNY
	SKINNY_SOFT("瘦削", BodySize.ZERO_SKINNY, Muscle.ZERO_SOFT, Util.newArrayListOfValues("纤细", "纤瘦")),
	SKINNY_LIGHTLY_MUSCLED("纤瘦", BodySize.ZERO_SKINNY, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("纤细", "纤瘦")),
	SKINNY_TONED("轻盈", BodySize.ZERO_SKINNY, Muscle.TWO_TONED, Util.newArrayListOfValues("纤细")),
	SKINNY_MUSCULAR("精瘦", BodySize.ZERO_SKINNY, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("纤瘦", "肌肉分明")),
	SKINNY_RIPPED("健美", BodySize.ZERO_SKINNY, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("纤细", "肌肉分明")),

	// BodySize == ONE_SLENDER
	SLENDER_SOFT("苗条", BodySize.ONE_SLENDER, Muscle.ZERO_SOFT, Util.newArrayListOfValues("苗条", "纤瘦")),
	SLENDER_LIGHTLY_MUSCLED("修美", BodySize.ONE_SLENDER, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("苗条", "纤瘦")),
	SLENDER_TONED("精干", BodySize.ONE_SLENDER, Muscle.TWO_TONED, Util.newArrayListOfValues("苗条", "纤瘦", "肌肉分明")),
	SLENDER_MUSCULAR("精壮", BodySize.ONE_SLENDER, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("苗条", "肌肉分明")),
	SLENDER_RIPPED("矫健", BodySize.ONE_SLENDER, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("苗条", "肌肉分明")),
	
	// BodySize == TWO_AVERAGE
	AVERAGE_SOFT("圆润", BodySize.TWO_AVERAGE, Muscle.ZERO_SOFT, Util.newArrayListOfValues("圆润", "肌肉松弛")),
	AVERAGE_LIGHTLY_MUSCLED("匀称", BodySize.TWO_AVERAGE, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("稍有肌肉")),
	AVERAGE_TONED("协调", BodySize.TWO_AVERAGE, Muscle.TWO_TONED, Util.newArrayListOfValues("肌肉分明")),
	AVERAGE_MUSCULAR("健壮", BodySize.TWO_AVERAGE, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("肌肉分明", "线条优美")),
	AVERAGE_RIPPED("强健", BodySize.TWO_AVERAGE, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("肌肉分明", "线条优美", "强壮")),
	
	// BodySize == THREE_LARGE
	LARGE_SOFT("肥胖", BodySize.THREE_LARGE, Muscle.ZERO_SOFT, Util.newArrayListOfValues("圆润", "肌肉松弛", "肥胖")),
	LARGE_LIGHTLY_MUSCLED("丰满", BodySize.THREE_LARGE, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("圆润", "肌肉松弛", "丰满")),
	LARGE_TONED("结实", BodySize.THREE_LARGE, Muscle.TWO_TONED, Util.newArrayListOfValues("硕大", "强壮")),
	LARGE_MUSCULAR("壮实", BodySize.THREE_LARGE, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("硕大", "线条优美", "强壮")),
	LARGE_RIPPED("魁梧", BodySize.THREE_LARGE, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("庞大", "线条优美", "强壮")),
	
	// BodySize == FOUR_HUGE
	HUGE_SOFT("臃肿", BodySize.FOUR_HUGE, Muscle.ZERO_SOFT, Util.newArrayListOfValues("圆润", "肌肉松弛", "肥胖")),
	HUGE_LIGHTLY_MUSCLED("肥硕", BodySize.FOUR_HUGE, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("肥硕", "肌肉松弛", "丰满")),
	HUGE_TONED("粗壮", BodySize.FOUR_HUGE, Muscle.TWO_TONED, Util.newArrayListOfValues("硕大", "强壮")),
	HUGE_MUSCULAR("膀大腰圆", BodySize.FOUR_HUGE, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("硕大", "线条优美", "强壮")),
	HUGE_RIPPED("虎背熊腰", BodySize.FOUR_HUGE, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("庞大", "线条优美", "强壮"));
	
	private String name;
	private List<String> limbDescriptors;
	private BodySize relatedBodySize;
	private Muscle relatedMuscle;
	
	private BodyShape(String name, BodySize relatedBodySize, Muscle relatedMuscle, List<String> limbDescriptors) {
		this.name = name;
		this.relatedBodySize = relatedBodySize;
		this.relatedMuscle = relatedMuscle;
		this.limbDescriptors = limbDescriptors;
	}

	public String getName(boolean withDeterminer) {
		if(withDeterminer) {
			return UtilText.generateSingularDeterminer(name) + "" + name;
		} else {
			return name;
		}
	}

	public BodySize getRelatedBodySize() {
		return relatedBodySize;
	}

	public Muscle getRelatedMuscle() {
		return relatedMuscle;
	}
	
	public static BodyShape valueOf(Muscle muscle, BodySize bodySize) {
		for(BodyShape bs : BodyShape.values()) {
			if(muscle == bs.getRelatedMuscle() && bodySize == bs.getRelatedBodySize()) {
				return bs;
			}
		}
		return AVERAGE_LIGHTLY_MUSCLED;
	}
	
	public Color getDerivedColor() {
		return Util.midpointColor(relatedBodySize.getColour().getColor(), relatedMuscle.getColour().getColor());
	}
	
	public String toWebHexStringColour() {
		return Util.toWebHexString(getDerivedColor());
	}

	public List<String> getLimbDescriptors() {
		return limbDescriptors;
	}
}
