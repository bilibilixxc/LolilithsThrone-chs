package com.lilithsthrone.game.character.npc.dominion;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.*;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.HarpyAttackerDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @since 0.1.75
 * @version 0.4.2.1
 * @author Innoxia
 */
public class HarpyNestsAttacker extends NPC {

	public HarpyNestsAttacker() {
		this(Gender.getGenderFromUserPreferences(Femininity.FEMININE), false);
	}
	
	public HarpyNestsAttacker(Gender gender) {
		this(gender, false);
	}
	
	public HarpyNestsAttacker(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public HarpyNestsAttacker(Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				4,
				null, null, null,
				new CharacterInventory(false, 10), WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_WALKWAYS, false);

		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
			// Set random level from 2 to 5:
			setLevel(Util.random.nextInt(4) + 2);
			
			// RACE & NAME:
			
			Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
//			for(Entry<AbstractSubspecies, SubspeciesPreference> entry : gender.getGenderName().isHasPenis()?Main.getProperties().getSubspeciesMasculinePreferencesMap().entrySet():Main.getProperties().getSubspeciesFemininePreferencesMap().entrySet()) {
//				if(entry.getKey().getRace()==Race.HARPY && Subspecies.getWorldSpecies(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_WALKWAYS, false).containsKey(entry.getKey())) {
//					AbstractSubspecies.addToSubspeciesMap((int) (1000*Subspecies.getWorldSpecies(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_WALKWAYS, false).get(entry.getKey()).getChanceMultiplier()), gender, entry.getKey(), subspeciesMap);
//				}
//			}
			
			for(Entry<AbstractSubspecies, SubspeciesSpawnRarity> entry : Subspecies.getWorldSpecies(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_WALKWAYS, false).entrySet()) {
				if(entry.getKey().getRace()==Race.HARPY) {
					AbstractSubspecies.addToSubspeciesMap((int) (1000*entry.getValue().getChanceMultiplier()), gender, entry.getKey(), subspeciesMap);
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, subspeciesMap, true, false);

			if(Math.random()<Main.getProperties().halfDemonSpawnRate/100f) { // Half-demon spawn rate
				this.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, this.getGender(), this.getBody().getFleshSubspecies(), true), true);
			}
			
			setName(Name.getRandomTriplet(Subspecies.HARPY));
			this.setPlayerKnowsName(false);

			Main.game.getCharacterUtils().setHistoryAndPersonality(this, true);
			this.setHistory(Occupation.NPC_HARPY_FLOCK_MEMBER);
			
			// Add fetishes:
			Main.game.getCharacterUtils().addFetishes(this);
			
			// BODY RANDOMISATION:
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			// INVENTORY:
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
			
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);
			
			if(hasFetish(Fetish.FETISH_CUM_ADDICT) && Math.random() < 0.1) {
				Main.game.getCharacterUtils().applyDirtiness(this);
			}
			
			initHealthAndManaToMax();
		}

		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
		
	}
	
	@Override
	public void setBodyFromSubspeciesPreference(Gender gender, Map<AbstractSubspecies, Integer> subspeciesMap, boolean additionalSetups, boolean includeHumanChance) {
		if(gender.isFeminine()) {
			for(Entry<AbstractSubspecies, FurryPreference> entry : Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					subspeciesMap.remove(entry.getKey());
				}
			}
		} else {
			for(Entry<AbstractSubspecies, FurryPreference> entry : Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					subspeciesMap.remove(entry.getKey());
				}
			}
		}
		
		int total = 0;
		for(Integer i : subspeciesMap.values()) {
			total += i;
		}

		AbstractSubspecies species = Subspecies.HARPY;
		if(!subspeciesMap.isEmpty() && total>0) {
			species = Util.getRandomObjectFromWeightedMap(subspeciesMap);
		}
		setBody(gender, species, RaceStage.LESSER, true);
//		if(gender.getGenderName().isHasPenis()) {
//			if(gender.getGenderName().isHasBreasts()) {
//				setBody(Gender.F_P_B_SHEMALE, species, RaceStage.LESSER, true);
//			} else {
//				setBody(Gender.F_P_TRAP, species, RaceStage.LESSER, true);
//			}
//		} else {
//			if(gender.getGenderName().isHasBreasts()) {
//				setBody(Gender.F_V_B_FEMALE, species, RaceStage.LESSER, true);
//			} else {
//				setBody(Gender.F_V_FEMALE, species, RaceStage.LESSER, true);
//			}
//		}
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
	public String getDescription() {
		if(this.isSlave()) {
			return UtilText.parse(this, "[npc.nameIsFull]因触犯法律成为了奴隶，不过是[npc.her]主人的财产而已。");
			
		} else if(this.getAffectionLevel(Main.game.getPlayer()).isLessThan(AffectionLevel.POSITIVE_ONE_FRIENDLY)) {
			return UtilText.parse(this, "[npc.Name]对你进入'[npc.her]的'领地感到愤怒。看起来她已经准备好与你战斗，好好给你一个教训……");
			
		} else {
			return UtilText.parse(this, "虽然你与[npc.name]的第一次相遇时彼此针锋相对，但后来你设法与这位好斗的[npc.race]成为朋友，你可以肯定[npc.herHim]总会热烈欢迎你。");
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
		this.incrementMoney((long) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
		
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.CASUAL, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}

	@Override
	public void endSex() {
		if(!isSlave()) {
			setPendingClothingDressing(true);
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
		return HarpyAttackerDialogue.HARPY_ATTACK;
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", HarpyAttackerDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", HarpyAttackerDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
}
