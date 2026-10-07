package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.78
 * @version 0.4.4
 * @author Innoxia
 */
public enum AffectionLevel {
	
	/** -100 to -90*/
	NEGATIVE_FIVE_LOATHE("憎恶", "憎恶", -100, -90, PresetColour.AFFECTION_NEGATIVE_FIVE, true),

	/** -90 to -70*/
	NEGATIVE_FOUR_HATE("厌恶", "厌恶", -90, -70, PresetColour.AFFECTION_NEGATIVE_FOUR, true),

	/** -70 to -50*/
	NEGATIVE_THREE_STRONG_DISLIKE("极其讨厌", "极其讨厌", -70, -50, PresetColour.AFFECTION_NEGATIVE_THREE, true),

	/** -50 to -30*/
	NEGATIVE_TWO_DISLIKE("讨厌", "讨厌", -50, -30, PresetColour.AFFECTION_NEGATIVE_TWO, true),

	/** -30 to -10*/
	NEGATIVE_ONE_ANNOYED("烦恼", "有点烦", -30, -10, PresetColour.AFFECTION_NEGATIVE_ONE, true),

	/** -10 to 10*/
	ZERO_NEUTRAL("中立", "不喜欢也不讨厌", -10, 10, PresetColour.AFFECTION_NEUTRAL, true),

	/** 10 to 30*/
	POSITIVE_ONE_FRIENDLY("友善", "友善对待", 10, 30, PresetColour.AFFECTION_POSITIVE_ONE, false),

	/** 30 to 50*/
	POSITIVE_TWO_LIKE("喜欢", "喜欢", 30, 50, PresetColour.AFFECTION_POSITIVE_TWO, false),

	/** 50 to 70*/
	POSITIVE_THREE_CARING("关心", "关心", 50, 70, PresetColour.AFFECTION_POSITIVE_THREE, false),

	/** 70 to 90*/
	POSITIVE_FOUR_LOVE("喜爱", "喜爱", 70, 90, PresetColour.AFFECTION_POSITIVE_FOUR, false),

	/** 90 to 100*/
	POSITIVE_FIVE_WORSHIP("爱慕", "爱慕", 90, 100, PresetColour.AFFECTION_POSITIVE_FIVE, false);
	
	
	private String name;
	private String descriptor;
	private int minimumValue, maximumValue;
	private Colour colour;
	private boolean willFightPlayer;

	private AffectionLevel(String name, String descriptor, int minimumValue, int maximumValue, Colour colour, boolean willFightPlayer) {
		this.name = name;
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
		this.willFightPlayer = willFightPlayer;
	}
	
	/**
	 * In the format: "Rose loves Lilaya."
	 */
	public static String getDescription(GameCharacter character, GameCharacter target, boolean withColour) {
		StringBuilder sb = new StringBuilder();
		AffectionLevel affectionLevel = character.getAffectionLevel(target);
		
		switch(affectionLevel) {
			case NEGATIVE_FIVE_LOATHE:
				sb.append(UtilText.parse(character, target, "[npc.Name]"+applyColourWrapper("憎恶", affectionLevel, withColour)+"[npc2.name]。"));
				break;
			case NEGATIVE_FOUR_HATE:
				sb.append(UtilText.parse(character, target, "[npc.Name]"+applyColourWrapper("厌恶", affectionLevel, withColour)+"[npc2.name]。"));
				break;
			case NEGATIVE_THREE_STRONG_DISLIKE:
				sb.append(UtilText.parse(character, target, "[npc.Name] "+applyColourWrapper("非常讨厌", affectionLevel, withColour)+"[npc2.name]。"));
				break;
			case NEGATIVE_TWO_DISLIKE:
				sb.append(UtilText.parse(character, target, "[npc.Name]"+applyColourWrapper("讨厌", affectionLevel, withColour)+"[npc2.name]。"));
				break;
			case NEGATIVE_ONE_ANNOYED:
				sb.append(UtilText.parse(character, target, "[npc.Name]对[npc2.name]感到"+applyColourWrapper("恼火", affectionLevel, withColour)+"。"));
				break;
			case ZERO_NEUTRAL:
				sb.append(UtilText.parse(character, target, "[npc.Name]对[npc2.name]"+applyColourWrapper("满不在乎", affectionLevel, withColour)+"。"));
				break;
			case POSITIVE_ONE_FRIENDLY:
				sb.append(UtilText.parse(character, target, "[npc.Name]对[npc2.name]"+applyColourWrapper("友善", affectionLevel, withColour)+"。"));
				break;
			case POSITIVE_TWO_LIKE:
				sb.append(UtilText.parse(character, target, "[npc.Name]"+applyColourWrapper("喜欢", affectionLevel, withColour)+"[npc2.name]。"));
				break;
			case POSITIVE_THREE_CARING:
				sb.append(UtilText.parse(character, target, "[npc.Name]"+applyColourWrapper("关心", affectionLevel, withColour)+"[npc2.name]。"));
				break;
			case POSITIVE_FOUR_LOVE:
				sb.append(UtilText.parse(character, target, "[npc.Name]"+applyColourWrapper("喜爱", affectionLevel, withColour)+"[npc2.name]。"));
				break;
			case POSITIVE_FIVE_WORSHIP:
				sb.append(UtilText.parse(character, target, "[npc.Name]"+applyColourWrapper("爱慕", affectionLevel, withColour)+"[npc2.name]。"));
				break;
		}
		
		return sb.toString();
	}
	
	/**
	 * In the format: "Rose worships Lilaya, and is head-over-heels in love with her."
	 */
	public static String getAttitudeDescription(GameCharacter character, GameCharacter target, boolean withColour) {
		StringBuilder sb = new StringBuilder();
		AffectionLevel affectionLevel = character.getAffectionLevel(target);
		
		sb.append("<p style='text-align:center;'><i>");
		switch(affectionLevel) {
			case NEGATIVE_FIVE_LOATHE:
				sb.append("从行为上来看，[npc.Name]很明显十分"+applyColourWrapper("憎恶[npc2.name]", affectionLevel, withColour)+"。");
				break;
			case NEGATIVE_FOUR_HATE:
				sb.append("从行为上来看，[npc.Name]很明显"+applyColourWrapper("厌恶[npc2.name]", affectionLevel, withColour)+"。");
				break;
			case NEGATIVE_THREE_STRONG_DISLIKE:
				sb.append("看[npc.Name]的态度，她明显"+applyColourWrapper("极其讨厌[npc2.name]", affectionLevel, withColour)+"。");
				break;
			case NEGATIVE_TWO_DISLIKE:
				sb.append("[npc.NameIsFull]很明显"+applyColourWrapper("讨厌[npc2.name]", affectionLevel, withColour)+"。");
				break;
			case NEGATIVE_ONE_ANNOYED:
				sb.append("[npc.NameIsFull]明显对[npc2.name]感到"+applyColourWrapper("恼火", affectionLevel, withColour)+"。");
				break;
			case ZERO_NEUTRAL:
				sb.append("对于[npc2.name]，[npc.NameIsFull]摆出一副"+applyColourWrapper("满不在乎", affectionLevel, withColour)+"的姿态。");
				break;
			case POSITIVE_ONE_FRIENDLY:
				if(character.isAttractedTo(target)) {
					sb.append("对于[npc2.name]，[npc.NameIsFull]摆出一副"+applyColourWrapper("友善、挑逗", affectionLevel, withColour)+"的姿态。");
				} else {
					sb.append("对于[npc2.name]，[npc.NameIsFull]摆出一副"+applyColourWrapper("友善", affectionLevel, withColour)+"的姿态。");
				}
				break;
			case POSITIVE_TWO_LIKE:
				if(character.isAttractedTo(target)) {
					sb.append("[npc.Name]很明显"+applyColourWrapper("喜欢[npc2.name]", affectionLevel, withColour)+"，而且认为[npc2.herHim]不只是朋友。");
				} else {
					sb.append("[npc.Name]很明显"+applyColourWrapper("喜欢[npc2.name]", affectionLevel, withColour)+"，并且认为[npc2.herHim]是位好朋友。");
				}
				break;
			case POSITIVE_THREE_CARING:
				if(character.isAttractedTo(target)) {
					sb.append("[npc.Name]明显"+applyColourWrapper("很在乎[npc2.name]", affectionLevel, withColour)+"，并且被[npc2.herHim]深深地吸引了。");
				} else {
					sb.append("[npc.Name]明显"+applyColourWrapper("很在乎[npc2.name]", affectionLevel, withColour)+"，并且认为[npc2.herHim]是[npc.her]最好的朋友。");
				}
				break;
			case POSITIVE_FOUR_LOVE:
				if(character.isAttractedTo(target)) {
					sb.append("从[npc.Name]看向[npc2.name]的眼神就能得知，[npc.she]很明显"+applyColourWrapper("爱上了[npc2.herHim]", affectionLevel, withColour)+"。");
				} else {
					sb.append("从[npc.Name]的行为上来看，[npc.she]很明显"+applyColourWrapper("喜爱着[npc2.name]", affectionLevel, withColour)+"，一种柏拉图式的友谊。");
				}
				break;
			case POSITIVE_FIVE_WORSHIP:
				if(character.isAttractedTo(target)) {
					sb.append("[npc.Name]十分"+applyColourWrapper("爱慕[npc2.name]", affectionLevel, withColour)+"，已经死心塌地地爱上了[npc2.herHim]。");
				} else {
					sb.append("[npc.Name]十分"+applyColourWrapper("仰慕[npc2.name]", affectionLevel, withColour)+"，无论[npc2.she]让自己做什么都愿意。");
				}
				break;
		}
		sb.append("</i></p>");
		
		return UtilText.parse(character, target, sb.toString());
	}
	
	private static String applyColourWrapper(String input, AffectionLevel affection, boolean withColour) {
		if(!withColour) {
			return input;
		}
		return "<span style='color:"+affection.getColour().toWebHexString()+";'>"+input+"</span>";
	}
	
	public String getName() {
		return name;
	}

	/**
	 * To fit into a sentence such as:<br/>
	 * "Due to the fact that Kate "+getDescriptor()+" you..."
	 * @return
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}
	
	public int getMedianValue() {
		return (minimumValue + maximumValue) / 2;
	}
	
	public Colour getColour() {
		return colour;
	}

	public boolean isWillFightPlayer() {
		return willFightPlayer;
	}

	public static AffectionLevel getAffectionLevelFromValue(float value){
		for(AffectionLevel al : AffectionLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return POSITIVE_FIVE_WORSHIP;
	}
	
	public boolean isLessThan(AffectionLevel levelToCompare) {
		return this.getMedianValue()<levelToCompare.getMedianValue();
	}
	
	public boolean isGreaterThan(AffectionLevel levelToCompare) {
		return this.getMedianValue()>levelToCompare.getMedianValue();
	}
}
