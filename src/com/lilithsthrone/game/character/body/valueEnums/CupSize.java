package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.LolificationProject.ModCommon.ModProps;

/**
 * Measurements are in inches. Measured in bust to underbust using the UK system.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum CupSize {
	
	FLAT("平坦", "平坦", 0),

	// Training bra sizes:
	
	TRAINING_AAA("难辨有无", "次AAA", 1) {
		@Override
		public boolean isTrainingBraSize() {
			return true;
		}
	},
	TRAINING_AA("难辨有无", "次AA", 2) {
		@Override
		public boolean isTrainingBraSize() {
			return true;
		}
	},
	TRAINING_A("难辨有无", "次A", 3) {
		@Override
		public boolean isTrainingBraSize() {
			return true;
		}
	},
	
	// Normal cup sizes:
	
	AA("极小", "AA", 4),
	A("很小", "A", 5),
	B("娇小", "B", 6),
	C("一般大小", "C", 7),
	D("丰满", "D", 8),
	DD("丰满", "DD", 9),
	E("可观", "E", 10),
	F("可观", "F", 11),
	FF("可观", "FF", 12),
	G("极大", "G", 13),
	GG("极大", "GG", 14),
	H("极大", "H", 15),
	HH("硕大", "HH", 16),
	J("硕大", "J", 17),
	JJ("硕大", "JJ", 18),
	K("巨大", "K", 19),
	KK("巨大", "KK", 20),
	L("巨大", "L", 21),
	LL("庞大", "LL", 22),
	M("庞大", "M", 23),
	MM("庞大", "MM", 24),
	N("庞大", "N", 25),
	
	// Hyper sizes:
	
	X_AA("大到异于常人", "X-AA", 26),
	X_A("大到异于常人", "X-A", 27),
	X_B("大到异于常人", "X-B", 28),
	X_C("大到异于常人", "X-C", 29),
	X_D("大到异于常人", "X-D", 30),
	X_DD("大到异于常人", "X-DD", 31),
	X_E("大到异于常人", "X-E", 32),
	X_F("大到异于常人", "X-F", 33),
	X_FF("大到异于常人", "X-FF", 34),
	X_G("大到异于常人", "X-G", 35),
	X_GG("大到异于常人", "X-GG", 36),
	X_H("大到异于常人", "X-H", 37),
	X_HH("大到异于常人", "X-HH", 38),
	X_J("大到异于常人", "X-J", 39),
	X_JJ("大到异于常人", "X-JJ", 40),
	X_K("大到异于常人", "X-K", 41),
	X_KK("大到异于常人", "X-KK", 42),
	X_L("大到异于常人", "X-L", 43),
	X_LL("大到异于常人", "X-LL", 44),
	X_M("大到异于常人", "X-M", 45),
	X_MM("大到异于常人", "X-MM", 46),
	X_N("大到异于常人", "X-N", 47),

	XX_AA("大到恐怖", "XX-AA", 48),
	XX_A("大到恐怖", "XX-A", 49),
	XX_B("大到恐怖", "XX-B", 50),
	XX_C("大到恐怖", "XX-C", 51),
	XX_D("大到恐怖", "XX-D", 52),
	XX_DD("大到恐怖", "XX-DD", 53),
	XX_E("大到恐怖", "XX-E", 54),
	XX_F("大到恐怖", "XX-F", 55),
	XX_FF("大到恐怖", "XX-FF", 56),
	XX_G("大到恐怖", "XX-G", 57),
	XX_GG("大到恐怖", "XX-GG", 58),
	XX_H("大到恐怖", "XX-H", 59),
	XX_HH("大到恐怖", "XX-HH", 60),
	XX_J("大到恐怖", "XX-J", 61),
	XX_JJ("大到恐怖", "XX-JJ", 62),
	XX_K("大到恐怖", "XX-K", 63),
	XX_KK("大到恐怖", "XX-KK", 64),
	XX_L("大到恐怖", "XX-L", 65),
	XX_LL("大到恐怖", "XX-LL", 66),
	XX_M("大到恐怖", "XX-M", 67),
	XX_MM("大到恐怖", "XX-MM", 68),
	XX_N("大到恐怖", "XX-N", 69),

	XXX_AA("超乎想象", "XXX-AA", 70),
	XXX_A("超乎想象", "XXX-A", 71),
	XXX_B("超乎想象", "XXX-B", 72),
	XXX_C("超乎想象", "XXX-C", 73),
	XXX_D("超乎想象", "XXX-D", 74),
	XXX_DD("超乎想象", "XXX-DD", 75),
	XXX_E("超乎想象", "XXX-E", 76),
	XXX_F("超乎想象", "XXX-F", 77),
	XXX_FF("超乎想象", "XXX-FF", 78),
	XXX_G("超乎想象", "XXX-G", 79),
	XXX_GG("超乎想象", "XXX-GG", 80),
	XXX_H("超乎想象", "XXX-H", 81),
	XXX_HH("超乎想象", "XXX-HH", 82),
	XXX_J("超乎想象", "XXX-J", 83),
	XXX_JJ("超乎想象", "XXX-JJ", 84),
	XXX_K("超乎想象", "XXX-K", 85),
	XXX_KK("超乎想象", "XXX-KK", 86),
	XXX_L("超乎想象", "XXX-L", 87),
	XXX_LL("超乎想象", "XXX-LL", 88),
	XXX_M("超乎想象", "XXX-M", 89),
	XXX_MM("超乎想象", "XXX-MM", 90),
	XXX_N("超乎想象", "XXX-N", 91);

	private String descriptor;
	private String cupSizeName;
	private int measurement;

	private CupSize(String descriptor, String cupSizeName, int measurement) {
		this.descriptor = descriptor;
		this.cupSizeName = cupSizeName;
		this.measurement = measurement;
	}
	
	public boolean isTrainingBraSize() {
		return false;
	}
	
	/**
	 * @return The minimum size which is regarded as a character 'having breasts' by the game.
	 */
	public static CupSize getMinimumCupSizeForBreasts() {
		return CupSize.AA;
	}
	
	public static CupSize getMinimumCupSizeForEggIncubation() {
		// return CupSize.C;
        return CupSize.getCupSizeFromInt(ModProps.minimumFuckableCupSize);
	}
	
	public static CupSize getMinimumCupSizeForPaizuri() {
		return CupSize.C;
	}
	
	public static CupSize getMinimumCupSizeForPenetration() {
		// return CupSize.C;
        return CupSize.getCupSizeFromInt(ModProps.minimumFuckableCupSize);
	}

	/**
	 * @param size Measurement in inches from bust to underbust.
	 */
	public static CupSize getCupSizeFromInt(int size) {
		for (CupSize cs : values()) {
			if (size == cs.measurement) {
				return cs;
			}
		}
		return XXX_N;
	}

	/**
	 * To fit into a sentence: "You have "+getDescriptor()+" breasts."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public String getCupSizeName() {
		return cupSizeName;
	}

	public int getMeasurement() {
		return measurement;
	}
	
	public int getValue() {
		return getMeasurement();
	}
	
	public static CupSize getMaximumCupSize() {
		return XXX_N;
	}
}
