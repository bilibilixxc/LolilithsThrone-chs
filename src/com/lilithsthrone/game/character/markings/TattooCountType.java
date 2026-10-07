package com.lilithsthrone.game.character.markings;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum TattooCountType {

	NUMBERS("数字") {
		@Override
		public String convertInt(int input) {
			return String.valueOf(input);
		}
	},
	TALLY("计数符号") {
		@Override
		public String convertInt(int input) {
			return Util.intToTally(input, 50);
		}
	},
	NUMERALS("罗马数字") {
		@Override
		public String convertInt(int input) {
			return Util.intToNumerals(input);
		}
	},
	WRITTEN("书写文字") {
		@Override
		public String convertInt(int input) {
			return Util.capitaliseSentence(Util.intToString(input));
		}
	},
	CHINESE("汉字计数") {
		public String convertInt(int input) {
			return Util.intToZheng(input, 50);
		}
	};
	
	private String name;

	private TattooCountType(String name) {
		this.name = name;
	}

	public abstract String convertInt(int input);
	
	public String getName() {
		return name;
	}
}
