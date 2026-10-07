package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public enum HairStyle {

//	- parted down the middle
//	- side parted
//	- shaved (different from bald)
//	- punk (hair draped over face)
	
	NONE("自然", Femininity.ANDROGYNOUS, HairLength.ZERO_BALD) {
		@Override
		public String getName(Body body) {
			if(body!=null && body.isFeral()) {
				if(body.getLegConfiguration()==LegConfiguration.AVIAN) {
					return "羽状";
				} else {
					return "鬃毛状";
				}
			}
			return super.getName(body);
		}
	},
	MESSY("凌乱", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	LOOSE("松散", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	CURLY("卷发", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	STRAIGHT("直发", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	SLICKED_BACK("大背头", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	SIDE_PARTED("侧剃", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	
	SIDECUT("侧剃", Femininity.ANDROGYNOUS, HairLength.TWO_SHORT),
	MOHAWK("莫霍克", Femininity.ANDROGYNOUS, HairLength.TWO_SHORT),
	DREADLOCKS("脏辫", Femininity.ANDROGYNOUS, HairLength.TWO_SHORT),
	
	AFRO("爆炸头", Femininity.MASCULINE, HairLength.ONE_VERY_SHORT),
	TOPKNOT("髻发", Femininity.MASCULINE, HairLength.THREE_SHOULDER_LENGTH),
	
	PIXIE("精灵短发", Femininity.FEMININE, HairLength.TWO_SHORT),
	BUN("丸子头", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	BOB_CUT("波波头", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	CHONMAGE("月代头", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	WAVY("波浪长发", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	PONYTAIL("马尾辫", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	LOW_PONYTAIL("低马尾", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	TWIN_TAILS("双马尾", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	SIDE_BRAIDS("侧麻花辫", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	CHIGNON("低髻", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	BRAIDED("麻花辫", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	TWIN_BRAIDS("双麻花辫", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	CROWN_BRAID("冠辫发", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	DRILLS("公主卷发", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	HIME_CUT("姬发", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	BIRD_CAGE("鸟笼发", Femininity.FEMININE, HairLength.SEVEN_TO_FLOOR);
	
	private String descriptor;
	private Femininity femininity;
	private int minimumLengthRequired;

	private HairStyle(String descriptor, Femininity femininity, HairLength minimumLengthRequired) {
		this.descriptor = descriptor;
		this.femininity = femininity;
		this.minimumLengthRequired = minimumLengthRequired.getMinimumValue();
	}

	public String getName(GameCharacter owner) {
		if(owner==null) {
			return descriptor;
		}
		return getName(owner.getBody());
	}

	public String getName(Body body) {
		return descriptor;
	}
	
	/** This should just be used for random character hair style generation. */
	public Femininity getFemininity() {
		return femininity;
	}

	public int getMinimumLengthRequired() {
		return minimumLengthRequired;
	}
	
	/**
	 * @return A random hair style, filtered by femininity and length limitations.
	 */
	public static HairStyle getRandomHairStyle(boolean feminine, int hairLength) {
		List<HairStyle> availableStyles = new ArrayList<>();
		
		for(HairStyle hs : HairStyle.values()) {
			if((hs.getFemininity()==Femininity.ANDROGYNOUS || hs.getFemininity().isFeminine()==feminine) && hs.getMinimumLengthRequired() <= hairLength) {
				availableStyles.add(hs);
			}
		}
		
		// Most likely to have a "normal" hair style:
		if(Math.random()>0.10f) {
			availableStyles.remove(HairStyle.AFRO);
			availableStyles.remove(HairStyle.SIDECUT);
			availableStyles.remove(HairStyle.MOHAWK);
			availableStyles.remove(HairStyle.HIME_CUT);
			availableStyles.remove(HairStyle.CHONMAGE);
			availableStyles.remove(HairStyle.DREADLOCKS);
			availableStyles.remove(HairStyle.BIRD_CAGE);
			availableStyles.remove(HairStyle.DRILLS);
		}
		
		return availableStyles.get(Util.random.nextInt(availableStyles.size()));
	}
}
