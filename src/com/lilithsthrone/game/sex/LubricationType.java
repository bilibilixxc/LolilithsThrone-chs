package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.6?
 * @version 0.4.11.1
 * @author Innoxia
 */
public enum LubricationType {
	
	SALIVA("口水", "口水", false, PresetColour.BASE_BLUE_LIGHT),
	
	MILK("[npc.milk]", "乳汁", false, PresetColour.MILK),
	
	PRECUM("先走液", "先走液", false, PresetColour.CUM),
	
	CUM("[npc.cum]", "精液", false, PresetColour.CUM),
	
	GIRLCUM("爱液", "爱液", false, PresetColour.GIRLCUM),
	
	ANAL_LUBE("肠液", "肠液", false, PresetColour.BASE_BLUE_LIGHT), // This is only present if the anus has been transformed to be 'wetter' than usual

	SLIME("粘液", "粘液", false, PresetColour.RACE_SLIME),
	
	WATER("水", "水", false, PresetColour.BASE_AQUA),
	
	OTHER("润滑剂", "润滑剂", false, PresetColour.BASE_BLUE_LIGHT);
	
	private String name;
	private String nullOwnerName;
	private boolean plural;
	private Colour colour;
	
	private LubricationType(String name, String nullOwnerName, boolean plural, Colour colour){
		this.name = name;
		this.nullOwnerName = nullOwnerName;
		this.plural = plural;
		this.colour = colour;
	}
	
	public boolean isPlural() {
		return plural;
	}
	
	public String getName(GameCharacter owner) {
		return getName(owner, false);
	}
	
	public String getName(GameCharacter owner, boolean coloured) {
		if(owner==null) {
			return nullOwnerName;
		}
		if(coloured) {
			return "<span style='color:"+getColour().toWebHexString()+";'>"+UtilText.parse(owner, name)+"</span>";
		} else {
			return UtilText.parse(owner, name);
		}
	}
	
	public Colour getColour() {
		return colour;
	}
}
