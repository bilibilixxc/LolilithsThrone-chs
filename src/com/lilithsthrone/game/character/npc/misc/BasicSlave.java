package com.lilithsthrone.game.character.npc.misc;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.Month;
import java.util.List;

/**
 * This class is used for the customisable slave available from Helena's slave shop.
 * 
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class BasicSlave extends NPC {

	public BasicSlave() {
		this(Gender.getGenderFromUserPreferences(false, false), false);
	}
	
	public BasicSlave(Gender gender) {
		this(gender, false);
	}
	
	public BasicSlave(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public BasicSlave(Gender gender, boolean isImported) {
		super(isImported,
				new NameTriplet("奴隶"), "",
				"",
				21, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(27),
				3,
				null, null, null,
				new CharacterInventory(false, 0),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				false);

		if(!isImported) {
			this.setBody(gender, Subspecies.HUMAN, RaceStage.HUMAN, false);
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);

			this.setPlayerKnowsName(true);
			this.setSurname("");
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 0);
			
			// PERSONALITY & BACKGROUND:
			
			this.setHistory(Occupation.NPC_SLAVE);

			this.clearFetishDesires();
			this.clearFetishes();
			this.clearPersonalityTraits();
			this.clearTattoosAndScars();
			
			this.setObedience(100);
			this.setAffection(Main.game.getPlayer(), 100);
			
			
			// BODY RANDOMISATION:
			
			this.setStartingBody(true);
			
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(0);
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			
			// MISC.:
			
			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);
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
		if(this.body!=null) {
			this.setAssVirgin(true);
			this.setFaceVirgin(true);
			this.setNippleCrotchVirgin(true);
			this.setNippleVirgin(true);
			this.setPenisVirgin(true);
			this.setUrethraVirgin(true);
			this.setVaginaVirgin(true);
			this.setVaginaUrethraVirgin(true);
		}
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_STEEL, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave() && this.isDoll()) {
			return super.getDescription();
		}
		if(this.isSlave()) {
			return UtilText.parse(this, "由于触犯了法律，[npc.she]现在是一名奴隶，只不过是其主人的财产。");
			
		} else {
			return UtilText.parse(this, "[npc.nameIsFull]曾有一段时间是你的奴隶，但现在是你很信任的朋友了。");
		}
	}

	@Override
	public boolean isClothingStealable() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
}
