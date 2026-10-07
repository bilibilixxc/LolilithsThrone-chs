package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;

/**
 * Used in Pathing.
 * 
 * @since 0.3.1
 * @version 0.3.5
 * @author Innoxia
 */
public enum MapTravelType {

	WALK_SAFE("步行 (最安全)",
			"使用最安全的路径前往目的地。",
			"在地图上点击，确认终点，然后再次点击前往那里。你可以通过按住shift键点击来规划路径点。",
			PresetColour.GENERIC_MINOR_GOOD) {
				@Override
				public boolean isAvailable(Cell c, GameCharacter character) {
					return !character.isCaptive();
				}

				@Override
				public String getUnavailablilityDescription(Cell c, GameCharacter character) {
					if(character.isCaptive()) {
						return "当你是俘虏时你不能使用快速旅行！";
					}
					return "";
				}
			},
	
	WALK_DANGEROUS("步行 (最快)",
			"使用最快的路径前往目的地。",
			"在地图上点击，确认终点，然后再次点击前往那里。你可以通过按住shift键点击来规划路径点。",
			PresetColour.GENERIC_MINOR_BAD) {
				@Override
				public boolean isAvailable(Cell c, GameCharacter character) {
					return !character.isCaptive();
				}

				@Override
				public String getUnavailablilityDescription(Cell c, GameCharacter character) {
					if(character.isCaptive()) {
						return "当你是俘虏时你不能使用快速旅行！";
					}
					return "";
				}
			},
	
	FLYING("飞行",
			"飞往目的地。",
			"在地图上点击一次标记你的目的地，点击第二次则会前往那里。",
			PresetColour.SPELL_SCHOOL_AIR) {
				@Override
				public boolean isAvailable(Cell c, GameCharacter character) {
					return !character.isCaptive() && character.isPartyAbleToFly() && character.getWorldLocation().isFlightEnabled();
				}

				@Override
				public String getUnavailablilityDescription(Cell c, GameCharacter character) {
					if(character.isCaptive()) {
						return "当你是俘虏时你不能使用快速旅行！";
					}
					if(!character.getWorldLocation().isFlightEnabled()) {
						return "你不能在这个区域飞行！";
					}
					if(!character.isAbleToFly()) {
						return "你还不能飞行！";
					}
					return "不是所有你的同伴都能飞行！";
				}
			},
	
	TELEPORT("传送",
			"传送前往目的地。",
			"在地图上点击一次标记你的目的地，点击第二次则会前往那里。",
			PresetColour.SPELL_SCHOOL_ARCANE) {
				@Override
				public boolean isAvailable(Cell c, GameCharacter character) {
					if(character.isCaptive()) {
						return false;
					}
					if(Main.game.isDebugMode()) {
						return true;
					}
					if(!character.getWorldLocation().getTeleportPermissions().isOutgoing()
							|| (c!=null && !c.getType().getTeleportPermissions().isIncoming())
							|| (c!=null && !c.getPlace().getPlaceType().getTeleportPermissions().isIncoming())) {
						return false;
					}
					return (character.isAbleToTeleport() && character.getMana()>=Spell.TELEPORT.getModifiedCost(character));
				}

				@Override
				public String getUnavailablilityDescription(Cell c, GameCharacter character) {
					if(character.isCaptive()) {
						return "当你是俘虏时你不能使用快速旅行！";
					}
					if(!character.getWorldLocation().getTeleportPermissions().isOutgoing()) {
						return "你无法传送出区域'"+character.getWorldLocation().getName()+"'这个区域！";
					}
					if(c!=null && !c.getType().getTeleportPermissions().isIncoming()) {
						return "你无法传送进区域'"+c.getType().getName()+"'！";
					}
					if(c!=null && !c.getPlace().getPlaceType().getTeleportPermissions().isIncoming()) {
						return "你无法传送进地块“"+c.getPlace().getName()+"”！";
					}
					if(!character.isAbleToTeleport()) {
						return character.getUnableToTeleportDescription();
					}
					return "施法至少需要"+Spell.TELEPORT.getModifiedCost(character)+"灵气！";
				}
			};
	
	private String name;
	private String description;
	private String useInstructions;
	private Colour colour;
	
	private MapTravelType(String name, String description, String useInstructions, Colour colour) {
		this.name = name;
		this.description = description;
		this.useInstructions = useInstructions;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getUseInstructions() {
		return useInstructions;
	}

	public Colour getColour() {
		return colour;
	}
	
	public abstract boolean isAvailable(Cell c, GameCharacter character);

	public abstract String getUnavailablilityDescription(Cell c, GameCharacter character);
}
