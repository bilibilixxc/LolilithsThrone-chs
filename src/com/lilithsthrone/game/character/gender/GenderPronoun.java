package com.lilithsthrone.game.character.gender;

/**
 * @since 0.1.67
 * @version 0.1.86
 * @author Innoxia
 */
public enum GenderPronoun {

	NOUN("名词", "女人", "男人", "人"),
	YOUNG_NOUN("年轻化名词", "女孩", "男孩", "孩子"),
	
	SECOND_PERSON("人称代词", "她", "他", "他"),
	THIRD_PERSON("人称宾格", "她", "他", "他"),
	POSSESSIVE_BEFORE_NOUN("名词前所有格", "她", "他", "他"),
	POSSESSIVE_ALONE("仅所有格", "她", "他", "他");
	
	private String name, feminine, masculine, neutral;
	
	private GenderPronoun(String name, String feminine, String masculine, String neutral){
		this.name = name;
		this.feminine = feminine;
		this.masculine = masculine;
	}

	public String getName() {
		return name;
	}
	
	public String getFeminine() {
		return feminine;
	}

	public String getMasculine() {
		return masculine;
	}

	public String getNeutral() {
		return neutral;
	}
}
