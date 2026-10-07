package com.lilithsthrone.game.dialogue.places.submission.dicePoker;

import com.lilithsthrone.rendering.SVGImages;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum DiceFace {
	
	ONE(1, "一", "⚀") {
		@Override
		public String getSVGString() {
			return SVGImages.SVG_IMAGE_PROVIDER.getDice1();
		}
	},
	TWO(2, "二", "⚁") {
		@Override
		public String getSVGString() {
			return SVGImages.SVG_IMAGE_PROVIDER.getDice2();
		}
	},
	THREE(3, "三", "⚂") {
		@Override
		public String getSVGString() {
			return SVGImages.SVG_IMAGE_PROVIDER.getDice3();
		}
	},
	FOUR(4, "四", "⚃") {
		@Override
		public String getSVGString() {
			return SVGImages.SVG_IMAGE_PROVIDER.getDice4();
		}
	},
	FIVE(5, "五", "⚄") {
		@Override
		public String getSVGString() {
			return SVGImages.SVG_IMAGE_PROVIDER.getDice5();
		}
	},
	SIX(6, "六", "⚅") {
		@Override
		public String getSVGString() {
			return SVGImages.SVG_IMAGE_PROVIDER.getDice6();
		}
	};

	private int value;
	private String name;
	private String htmlDisplay;
	
	private DiceFace(int value, String name, String htmlDisplay) {
		this.value = value;
		this.name = name;
		this.htmlDisplay = htmlDisplay;
	}
	
	public abstract String getSVGString();
	
	public int getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getHtmlDisplay() {
		return htmlDisplay;
	}
	
	public static DiceFace getFaceFromInt(int face) {
		for(DiceFace df : DiceFace.values()) {
			if(df.getValue()==face) {
				return df;
			}
		}
		System.err.println("There is no DiceFace with the value '"+face+"'...");
		return DiceFace.ONE;
	}
}
