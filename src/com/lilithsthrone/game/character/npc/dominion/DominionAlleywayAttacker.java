package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import org.w3c.dom.Document;
import com.lilithsthrone.threading.DocBuilders;import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayAttackerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayProstituteDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.StormStreetAttackerDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.3.5.5
 * @author Innoxia
 */
public class DominionAlleywayAttacker extends NPC {

	public DominionAlleywayAttacker() {
		this(Gender.getGenderFromUserPreferences(false, false), false);
	}
	
	public DominionAlleywayAttacker(Gender gender) {
		this(gender, false);
	}
	
	public DominionAlleywayAttacker(boolean isImported) {
		this(Gender.getGenderFromUserPreferences(true, false), isImported);
	}
	
	/**
	 * You must manually place this NPC in a location after creation!
	 */
	public DominionAlleywayAttacker(Gender gender, boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3,
				Gender.getGenderFromUserPreferences(false, false), null, null,
				new CharacterInventory(false, 10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false,
				generationFlags);

		if(!isImported) {
			boolean canalSpecies = false;
			AbstractPlaceType pt = Main.game.getPlayerCell().getPlace().getPlaceType();
			if(pt.equals(PlaceType.DOMINION_ALLEYS_CANAL_CROSSING)
					|| pt.equals(PlaceType.DOMINION_CANAL)
					|| pt.equals(PlaceType.DOMINION_CANAL_END)) {
				canalSpecies = true;
			}
			
			// Set random level from 1 to 3:
			setLevel(Util.random.nextInt(3) + 1);
			
			// RACE & NAME:
			
			Map<AbstractSubspecies, Integer> availableRaces = new HashMap<>();
			for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
				if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
					continue;
				}
				if(s==Subspecies.REINDEER_MORPH) {
					if(Main.game.getSeason()==Season.WINTER && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
						AbstractSubspecies.addToSubspeciesMap((int) ((canalSpecies?500:10000)* SubspeciesSpawnRarity.FIVE.getChanceMultiplier()), gender, s, availableRaces);
					}
					
				} else {
					if(Subspecies.getWorldSpecies(WorldType.DOMINION, pt, false).containsKey(s)) {
						AbstractSubspecies.addToSubspeciesMap((int) ((canalSpecies?2500:10000) * Subspecies.getWorldSpecies(WorldType.DOMINION, pt, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
					}
					if(canalSpecies && Subspecies.getWorldSpecies(WorldType.SUBMISSION, pt, false).containsKey(s)) {
//						System.out.println(s.getName(null));
						AbstractSubspecies.addToSubspeciesMap((int) (10000 * Subspecies.getWorldSpecies(WorldType.SUBMISSION, pt, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
					}
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true, true);
			
			if(Main.game.getCurrentWeather()!=Weather.MAGIC_STORM || canalSpecies || pt==PlaceType.DOMINION_BACK_ALLEYS) {
				if(Math.random()<Main.getProperties().halfDemonSpawnRate/100f && this.getSubspecies()!=Subspecies.SLIME) { // Don't convert slimes, as their getFleshSubspecies() can be of any non-Dominion subspecies
					this.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, gender, this.getBody().getFleshSubspecies(), true), true);
				}
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
			setDescription(UtilText.parse(this,
					"[npc.Name]是个御城区居民，出于私人原因，[npc.her]潜行在背街小巷中寻找猎物。"));
			
			// PERSONALITY & BACKGROUND:
			
			Main.game.getCharacterUtils().setHistoryAndPersonality(this, true);
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				this.setHistory(Occupation.NPC_MUGGER);
				setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
			}
			
			// ADDING FETISHES:
			
			Main.game.getCharacterUtils().addFetishes(this);
			
			// BODY RANDOMISATION:
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
			
			if(!Arrays.asList(generationFlags).contains(NPCGenerationFlag.NO_CLOTHING_EQUIP)) {
				this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			}
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);
			if((Arrays.asList(generationFlags).contains(NPCGenerationFlag.DIRTY) || hasFetish(Fetish.FETISH_CUM_ADDICT)) && Math.random() < 0.1) {
				Main.game.getCharacterUtils().applyDirtiness(this);
			}
			
			// Set starting perks based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
			
			if(this.isStormAttacker() && !this.isVulnerableToArcaneStorm()) { // NPCs spawned during a storm should be vulnerable to it.
				this.addSpecialPerk(Perk.SPECIAL_ARCANE_ALLERGY);
			}
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
		this.incrementMoney((long) (this.getInventory().getNonEquippedValue() * 0.5f));
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
	public String getDescription() {
		if(this.isSlave() && this.isDoll()) {
			return super.getDescription();
		}
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos]在御城区背街巷子里卖淫的日子结束了。[npc.sheIs]因触犯法律成为了奴隶，不过是[npc.her]主人的财产而已。"));
			} else if(this.getLocationPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)){
				return (UtilText.parse(this,
						"你最初在御城区的小巷中遇见了非法卖淫的[npc.name]。你引荐[npc.herHim]去天使之吻工作，[npc.she]欣然接受。"));
			} else {
				return (UtilText.parse(this,
						"[npc.name]是卖淫者，在御城区后巷出卖身体。"));
			}
			
		} else {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos]在御城区的后巷游荡和抢劫无辜过路人的日子已经结束。[npc.sheIs]因触犯法律成为了奴隶，不过是[npc.her]主人的财产而已。"));
			} else if(Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())){
				return (UtilText.parse(this,
						"[npc.name]在御城区后巷游荡、抢劫无辜过路人的日子已经结束。你与[npc.herHim]成为朋友，邀请[npc.name]搬到你家，帮助[npc.herHim]开始新生活。"));
			} else {
				return (UtilText.parse(this,
						"[npc.Name]是御城区居民，在后巷潜行，捕食无辜的过路人。"));
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
		if(!isStormAttacker()) {
			if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
				this.setPlayerKnowsName(true);
				return AlleywayProstituteDialogue.ALLEY_PROSTITUTE;
				
			} else {
				return AlleywayAttackerDialogue.ALLEY_ATTACK;
			}
			
		} else {
			return StormStreetAttackerDialogue.STORM_ATTACK;
		}
	}

	// Combat:

	@Override
	public void applyEscapeCombatEffects() {
		if(isStormAttacker()) {
			Main.game.banishNPC(this);
		}
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			if (victory) {
				return new Response("", "", AlleywayProstituteDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", AlleywayProstituteDialogue.AFTER_COMBAT_DEFEAT);
			}
			
		} else if(isStormAttacker()) {
			if (victory) {
				return new Response("", "", StormStreetAttackerDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", StormStreetAttackerDialogue.AFTER_COMBAT_DEFEAT);
			}
			
		} else {
			if (victory) {
				return new Response("", "", AlleywayAttackerDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", AlleywayAttackerDialogue.AFTER_COMBAT_DEFEAT);
			}
		}
	}
	
	public boolean isStormAttacker() {
		AbstractPlaceType pt = this.getLocationPlace().getPlaceType();
		return this.getWorldLocation().equals(WorldType.DOMINION)
				&& !pt.equals(PlaceType.DOMINION_BACK_ALLEYS)
				&& !pt.equals(PlaceType.DOMINION_ALLEYS_CANAL_CROSSING)
				&& !pt.equals(PlaceType.DOMINION_CANAL)
				&& !pt.equals(PlaceType.DOMINION_CANAL_END)
				&& !Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())
				&& (!this.isSlave() || !this.getOwner().isPlayer());
	}
}
