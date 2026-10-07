package com.lilithsthrone.game.character.npc.fields;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 0.4.4
 * @version 0.4.4
 * @author Innoxia
 */
public class ElisAlleywayAttacker extends NPC {

	public ElisAlleywayAttacker() {
		this(Gender.getGenderFromUserPreferences(false, false), false);
	}
	
	public ElisAlleywayAttacker(Gender gender) {
		this(gender, false);
	}
	
	public ElisAlleywayAttacker(boolean isImported) {
		this(Gender.getGenderFromUserPreferences(true, false), isImported);
	}
	
	/**
	 * You must manually place this NPC in a location after creation!
	 */
	public ElisAlleywayAttacker(Gender gender, boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3,
				Gender.getGenderFromUserPreferences(false, false), null, null,
				new CharacterInventory(false, 10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_town"),
				PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley"),
				false,
				generationFlags);

		if(!isImported) {
			// Set random level from 10 to 15:
			setLevel(Util.random.nextInt(6) + 10);
			
			// Race, name, personality/fetishes:
			
			Map<AbstractSubspecies, Integer> availableRaces = new HashMap<>();
			for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
				if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
					continue;
				}
				Map<AbstractSubspecies, SubspeciesSpawnRarity> subMap = Subspecies.getWorldSpecies(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley"), false);
				if(subMap.containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (10000 * subMap.get(s).getChanceMultiplier()), gender, s, availableRaces);
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true, true);
			
			if(Math.random()<Main.getProperties().halfDemonSpawnRate/100f && this.getSubspecies()!=Subspecies.SLIME) { // Don't convert slimes, as their getFleshSubspecies() can be of any subspecies
				this.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, gender, this.getBody().getFleshSubspecies(), true), true);
			}
			
			if(Math.random()<Main.getProperties().taurSpawnRate/100f
					&& this.getLegConfiguration()!=LegConfiguration.QUADRUPEDAL) { // Do not reset this character's taur body if they spawned as a taur (as otherwise subspecies-specific settings get overridden by global taur settings)
				// Check for race's leg type as taur, otherwise NPCs which spawn with human legs won't be affected by taur conversion rate:
				if(this.getSubspecies()==Subspecies.HALF_DEMON && this.getLegType().isLegConfigurationAvailable(LegConfiguration.QUADRUPEDAL)) {
					Main.game.getCharacterUtils().applyTaurConversion(this);
					
				} else if(this.getRace().getRacialBody().getLegType().isLegConfigurationAvailable(LegConfiguration.QUADRUPEDAL)) {
					this.setLegType(this.getRace().getRacialBody().getLegType());
					Main.game.getCharacterUtils().applyTaurConversion(this);
				}
			}
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
			
			setName(Name.getRandomTriplet(this.getSubspecies()));
			this.setPlayerKnowsName(false);
			
//			Main.game.getCharacterUtils().setHistoryAndPersonality(this, true);
			
			this.setHistory(Occupation.NPC_MUGGER);
			if(Math.random()<0.25f) {
				this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
			}
			
			Main.game.getCharacterUtils().addFetishes(this);
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			
			// Inventory:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
			
			if(!Arrays.asList(generationFlags).contains(NPCGenerationFlag.NO_CLOTHING_EQUIP)) {
				this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			}
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);

			if(hasFetish(Fetish.FETISH_CUM_ADDICT) && Math.random() < 0.1) {
				Main.game.getCharacterUtils().applyDirtiness(this);
			}
			
			// Set starting perks based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
		}

		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
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
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((long) (this.getInventory().getNonEquippedValue() * 1f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
		
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.PROSTITUTE, settings);
		} else {
			Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void turnUpdate() {
		if(!this.isSlave()
				&& !Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())
				&& this.getCell()!=Main.game.getPlayerCell()
				&& this.getHomeCell().getPlace().getPlaceType()!=PlaceType.ANGELS_KISS_BEDROOM) {
			if(Main.game.isDayTime()) {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
			} else {
				this.returnToHome();
			}
		}
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave() && this.isDoll()) {
			return super.getDescription();
		}
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos]在伊利斯背街巷子里卖淫的日子结束了。[npc.sheIs]因触犯法律成为了奴隶，不过是[npc.her]主人的财产而已。"));
			} else if(this.getLocationPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)){
				return (UtilText.parse(this,
						"你最初在伊利斯的小巷中遇见了非法卖淫的[npc.name]。你引荐[npc.herHim]去天使之吻工作，[npc.she]欣然接受。"));
			} else {
				return (UtilText.parse(this,
						"[npc.Name]是卖淫者，在伊利斯的下水道里出卖身体。"));
			}
			
		} else {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos]在伊利斯的后巷游荡和抢劫无辜过路人的日子已经结束。[npc.sheIs]因触犯法律成为了奴隶，不过是[npc.her]主人的财产而已。"));
			} else if(Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())){
				return (UtilText.parse(this,
						"[npc.name]在伊利斯后巷游荡、抢劫无辜过路人的日子已经结束。你与[npc.herHim]成为朋友，邀请[npc.name]搬到你家，帮助[npc.herHim]开始新生活。"));
			} else {
				return (UtilText.parse(this,
						"[npc.Name]是伊利斯居民，在后巷潜行，捕食无辜的过路人。"));
			}
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
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			this.setPlayerKnowsName(true);
			return DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_prostitute_start");
			
		} else {
			return DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_start");
		}
	}

	// Combat:

	@Override
	public void applyEscapeCombatEffects() {
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			if (victory) {
				return new Response("", "", DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_prostitute_after_combat_victory"));
			} else {
				return new Response ("", "", DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_prostitute_after_combat_defeat"));
			}
			
		} else {
			if (victory) {
				return new Response("", "", DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_after_combat_victory"));
			} else {
				return new Response ("", "", DialogueManager.getDialogueFromId("innoxia_encounters_fields_elis_alleyway_after_combat_defeat"));
			}
		}
	}
	
	// Misc.:
	
	public void setAsProstitute() {
		this.removePersonalityTrait(PersonalityTrait.MUTE);
		this.setHistory(Occupation.NPC_PROSTITUTE);
		CharacterUtils.initProstitute(this);
		this.equipClothing();
	}
}
