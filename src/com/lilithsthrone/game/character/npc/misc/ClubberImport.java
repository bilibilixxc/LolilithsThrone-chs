package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.util.List;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import org.w3c.dom.Document;
import com.lilithsthrone.threading.DocBuilders;import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.nightlife.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.5.7
 * @version 0.4.5.7
 * @author Innoxia
 */
public class ClubberImport extends NPC {

	public ClubberImport() {
		this(false);
	}
	
	public ClubberImport(boolean isImported) {
		super(isImported, new NameTriplet("夜总会会员"), "", "-",
				18, Month.JUNE, 10,
				1, Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(false, 0), WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		this.setLastTimeEncountered(DEFAULT_TIME_START_VALUE);
		
		if(!this.getId().endsWith("ClubberImport")) {
			this.setId(Main.game.getNextNPCId(ClubberImport.class));
		}
	}
	
	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);
		this.setLastTimeEncountered(DEFAULT_TIME_START_VALUE);

		if(!this.getId().endsWith("ClubberImport")) {
			this.setId(Main.game.getNextNPCId(ClubberImport.class));
		}
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
		}
		
		if(setFetishes) {
		}
		
		if(setOrientation) {
		}

		if(setHistory) {
		}
		
		if(setSpells) {
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			setStartingPersona();
		}
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		// Not needed
	}
	
	public void applyNewlyImportedClubberVariables() {
		this.endPregnancy(false);

		this.washAllOrifices(true);
		this.calculateStatusEffects(0);
		this.cleanAllDirtySlots(true);
		this.cleanAllClothing(true, false);
		
		this.clearAffectionMap();
		
		this.getSlavesOwned().clear();
		
		this.setPlayerKnowsName(true);
		
		if(NightlifeDistrict.isSearchingForASub()) {
			this.removePersonalityTrait(PersonalityTrait.CONFIDENT);
		} else {
			this.addPersonalityTrait(PersonalityTrait.CONFIDENT);
		}
		
		this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_CLUB_HOLDING_CELL, true);
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave() && this.isDoll()) {
			return super.getDescription();
		}
		return UtilText.parse(this, "[npc.Name]是御城区的居民,，你们曾经在夜生活区的一家夜总会遇见过。");
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}

//	@Override
//	public Vector2i getLocation() {
//		if(this.getWorldLocation()==WorldType.NIGHTLIFE_CLUB
//				&& Main.game.getPlayer().getWorldLocation()==WorldType.NIGHTLIFE_CLUB) {
//			return Main.game.getPlayer().getLocation();
//		}
//		return location;
//	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

}