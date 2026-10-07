package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @since 0.1.83
 * @version 0.3.9
 * @author Innoxia
 */
public enum FluidFlavour {
// Keldon move out
	NECTAR("nectar", PresetColour.BASE_YELLOW,
			Util.newArrayListOfValues(
					"清甜",
					"nectar-flavoured")),
	
	CUM("精液", PresetColour.CUM,
			Util.newArrayListOfValues(
					"咸涩")),
	
	MILK("乳汁", PresetColour.MILK,
			Util.newArrayListOfValues(
					"香醇")),
	
	GIRL_CUM("爱液", PresetColour.GIRLCUM,
			Util.newArrayListOfValues(
					"微甜")),
	
	FLAVOURLESS("无味", PresetColour.BASE_GREY,
			Util.newArrayListOfValues(
					"无味",
					"无味")),

	BUBBLEGUM("泡泡糖", PresetColour.BASE_PINK_LIGHT,
			Util.newArrayListOfValues(
					"微甜")),
	
	BEER("啤酒", PresetColour.BASE_TAN,
			Util.newArrayListOfValues(
					"麦芽香",
					"啤酒风味")),
	
	VANILLA("香草", PresetColour.BASE_YELLOW_PALE,
			Util.newArrayListOfValues(
					"甜味",
					"香草风味")),
	
	STRAWBERRY("草莓", PresetColour.BASE_CRIMSON,
			Util.newArrayListOfValues(
					"甜味",
					"草莓风味")),
	
	CHOCOLATE("巧克力", PresetColour.BASE_BROWN,
			Util.newArrayListOfValues(
					"巧克力味",
					"巧克力风味")),
	
	PINEAPPLE("菠萝", PresetColour.BASE_YELLOW_LIGHT,
			Util.newArrayListOfValues(
					"酸甜",
					"酸味",
					"咸甜",
					"菠萝风味")),
	
	HONEY("蜂蜜", PresetColour.BASE_YELLOW,
			Util.newArrayListOfValues(
					"甜味",
					"蜂蜜风味")),
	
	MINT("薄荷", PresetColour.BASE_GREEN_LIME,
			Util.newArrayListOfValues(
					"薄荷味")),
	
	CHERRY("樱桃", PresetColour.BASE_RED_DARK,
			Util.newArrayListOfValues(
					"甜味",
					"樱桃风味")),
	
	// ------ Icons for these made by 'Charisma is my Dump Stat': ------ //
	
	COFFEE("咖啡", PresetColour.BASE_BROWN_DARK,
			Util.newArrayListOfValues(
					"苦涩",
					"咖啡风味")),
	
	TEA("茶叶", PresetColour.BASE_GREEN,
			Util.newArrayListOfValues(
					"茶叶风味")),
	
	MAPLE("枫浆", PresetColour.BASE_RED,
			Util.newArrayListOfValues(
					"sweet",
					"枫浆风味")),
	
	CINNAMON("肉桂", PresetColour.BASE_BROWN,
			Util.newArrayListOfValues(
					"肉桂风味")),

	LEMON("柠檬", PresetColour.BASE_YELLOW,
			Util.newArrayListOfValues(
					"酸味",
					"柠檬风味")),
	
	// ------------ //
	
	// ------ Icons for these made by 'DSG': ------ //
	
	ORANGE("橙子", PresetColour.BASE_ORANGE,
			Util.newArrayListOfValues(
					"橙子风味")),
	
	GRAPE("葡萄", PresetColour.BASE_PURPLE,
			Util.newArrayListOfValues(
					"葡萄风味")),
	
	MELON("蜜瓜", PresetColour.BASE_GREEN_LIGHT,
			Util.newArrayListOfValues(
					"蜜瓜风味")),
	
	COCONUT("椰子", PresetColour.BASE_BROWN_DARK,
			Util.newArrayListOfValues(
					"椰子风味")),
	
	BLUEBERRY("蓝莓", PresetColour.BASE_BLUE_DARK,
			Util.newArrayListOfValues(
					"蓝莓风味")),
	
	BANANA("香蕉", PresetColour.BASE_YELLOW_LIGHT,
			Util.newArrayListOfValues(
					"香蕉风味"))
	
	// ------------ //
	
	;
	
	private String name;
	private Colour colour;
	private List<String> flavourDescriptors;

	private FluidFlavour(String name, Colour colour, List<String> flavourDescriptors) {
		this.name = name;
		this.colour=colour;
		this.flavourDescriptors = flavourDescriptors;
	}
	
	/**
	 * To go into: "You can't get the rich strawberry taste out of your mouth."<br/>
	 * Or: "Strawberry-flavoured"
	 */
	public String getName() {
		return name;
	}
	
	public Colour getColour() {
		return colour;
	}

	public List<String> getFlavourDescriptors() {
		return flavourDescriptors;
	}
	
	public String getRandomFlavourDescriptor() {
		return flavourDescriptors.get(Util.random.nextInt(flavourDescriptors.size()));
	}
	
	public static List<FluidFlavour> getUnnaturalFlavourings() {
		List<FluidFlavour> list = new ArrayList<>(Arrays.asList(FluidFlavour.values()));
		list.remove(CUM);
		list.remove(MILK);
		list.remove(GIRL_CUM);
		return list;
	}
}
