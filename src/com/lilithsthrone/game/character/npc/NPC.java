package com.lilithsthrone.game.character.npc;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import com.lilithsthrone.LolificationProject.ModCommon.Conversions;
import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.npc.dominion.ReindeerOverseer;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.npc.misc.GenericAndrogynousNPC;
import com.lilithsthrone.game.character.npc.misc.GenericFemaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.npc.misc.PrologueFemale;
import com.lilithsthrone.game.character.npc.misc.PrologueMale;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.pregnancy.Litter;
import com.lilithsthrone.game.character.pregnancy.PregnancyPossibility;
import com.lilithsthrone.game.character.race.AbstractRacialBody;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.AbstractPotion;
import com.lilithsthrone.game.inventory.item.FetishPotion;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

/**
 * @since 0.1.0
 * @version 0.4.4
 * @author Innoxia
 */
public abstract class NPC extends GameCharacter implements XMLSaving {
	
	protected long lastTimeEncountered = DEFAULT_TIME_START_VALUE;

	protected int playerSurrenderCount = 0; // Tracks how many times in a row the player has surrendered/offered body to this NPC. Only used for NPCs who are the attacker in random encounters.

	protected float buyModifier;
	protected float sellModifier;
	
	protected boolean addedToContacts;
	
	public Set<NPCFlagValue> NPCFlagValues;
	
	protected Set<SexSlot> sexPositionPreferences;
	
	protected Gender genderPreference = null;
	protected AbstractSubspecies subspeciesPreference = null;
	protected RaceStage raceStagePreference = null;

	// Tracks what items/clothing should be generated for this NPC:
	protected boolean generateExtraItems;
	protected boolean generateDisposableClothing;
	protected boolean generateExtraClothing;
	
	protected NPC(boolean isImported,
			NameTriplet nameTriplet,
			String surname,
			String description,
			int age,
			Month birthMonth,
			int birthDay,
			int level,
			Gender startingGender,
			AbstractSubspecies startingSubspecies,
			RaceStage stage,
			CharacterInventory inventory,
			AbstractWorldType worldLocation,
			AbstractPlaceType startingPlace,
			boolean addedToContacts,
			NPCGenerationFlag... generationFlags) {
		super(isImported,
				nameTriplet, surname, description, level,
					LocalDateTime.of(
							Main.game.getStartingDate().getYear() - (nameTriplet != null ? age : AgeCategory.getAgeFromPreferences(startingGender, age)),
							birthMonth,
							Math.min(birthMonth.maxLength(), birthDay), 12, 0),
				startingGender, startingSubspecies, stage, inventory, worldLocation, startingPlace);

		List<NPCGenerationFlag> flags = Arrays.asList(generationFlags);
		
		this.addedToContacts = addedToContacts;
		
		this.generateExtraItems = false;
		this.generateDisposableClothing = false;
		this.generateExtraClothing = false;
		
		sexPositionPreferences = new HashSet<>();
		
		buyModifier=0.75f;
		sellModifier=1.5f;
		
		NPCFlagValues = new HashSet<>();
		
		if(!isImported) {
			setStartingBody(true);
			if(!flags.contains(NPCGenerationFlag.NO_CLOTHING_EQUIP) && this.getBody()!=null) {
				equipClothing(EquipClothingSetting.getAllClothingSettings());
			}
//			if(!this.isUnique() && this.getBody()!=null && ((flags.contains(NPCGenerationFlag.DIRTY) || hasFetish(Fetish.FETISH_CUM_ADDICT)) && Math.random() < 0.1)) {
//				Main.game.getCharacterUtils().applyDirtiness(this);
//			}
		}
		
		if(this.getBody()!=null) {
			if(getLocation().equals(Main.game.getPlayer().getLocation()) && getWorldLocation()==Main.game.getPlayer().getWorldLocation()) {
				for(CoverableArea ca : CoverableArea.values()) {
					if(isCoverableAreaVisible(ca) && ca!=CoverableArea.MOUTH) {
						this.setAreaKnownByCharacter(ca, Main.game.getPlayer(), true);
					}
				}
			}
			
			if(!isImported || Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.5")) {
				this.setStartingCombatMoves();
			}
			
			loadImages();
		}
	}
	
	
	public void setStartingCombatMoves() {
		resetDefaultMoves();
	}

	/**
	 * Helper method that should be overridden and included in constructor. Sets custom body parts.<br/>
	 * <b><u>What to include</u></b><br/>
	 * <b><u>Persona</u></b><br/>
	 * <b>-</b> Starting attributes.<br/>
	 * <b>-</b> Personality.<br/>
	 * <b>-</b> Sexual orientation.<br/>
	 * <b>-</b> Occupation.<br/>
	 * <b>-</b> Fetishes.<br/>
	 * <br/><br/>
	 * 
	 * <b><u>Body</u></b><br/>
	 * <b>Core parts:</b><br/>
	 * <b>-</b> Any part type changes.<br/>
	 * <b>-</b> Height.<br/>
	 * <b>-</b> Femininity.<br/>
	 * <b>-</b> Muscle & body size.<br/>
	 * <br/>
	 * <b>Coverings:</b><br/>
	 * <b>-</b> Body coverings for eyes, skin & fur.<br/>
	 * <b>-</b> Hair coverings, length & style.<br/>
	 * <b>-</b> Body hair coverings & length. (Underarm, ass, pubic, facial.)<br/>
	 * <b>-</b> Makeup. (Nail polish, blusher, lipstick, eye liner, eye shadow.)<br/>
	 * <br/>
	 * <b>Face:</b><br/>
	 * <b>-</b> Oral virginity.<br/>
	 * <b>-</b> Lip size.<br/>
	 * <b>-</b> Eye count.<br/>
	 * <b>-</b> Throat capacity & modifiers.<br/>
	 * <b>-</b> Tongue length.<br/>
	 * <b>-</b> Tongue modifiers.<br/>
	 * <br/>
	 * <b>Chest:</b><br/>
	 * <b>-</b> Virginity.<br/>
	 * <b>-</b> Breast size.<br/>
	 * <b>-</b> Breast shape.<br/>
	 * <b>-</b> Nipple shape.<br/>
	 * <b>-</b> Nipple size.<br/>
	 * <b>-</b> Areolae shape.<br/>
	 * <b>-</b> Areolae size.<br/>
	 * <b>-</b> Nipple settings (capacity, wetness, plasticity, elasticity, modifiers).<br/>
	 * <b>-</b> Milk production.<br/>
	 * <b>-</b> Milk modifiers & flavour.<br/>
	 * <br/>
	 * <b>Arms:</b><br/>
	 * <b>-</b> Arm count.<br/>
	 * <br/>
	 * <b>Ass:</b><br/>
	 * <b>-</b> Virginity.<br/>
	 * <b>-</b> Ass size.<br/>
	 * <b>-</b> Hip size.<br/>
	 * <b>-</b> Anus bleaching.<br/>
	 * <b>-</b> Anus settings (capacity, wetness, plasticity, elasticity, modifiers).<br/>
	 * <br/>
	 * <b>Penis:</b><br/>
	 * <b>-</b> Virginity.<br/>
	 * <b>-</b> Penis size.<br/>
	 * <b>-</b> Testicle size.<br/>
	 * <b>-</b> Testicle count.<br/>
	 * <b>-</b> Cum production.<br/>
	 * <b>-</b> Cum modifiers & flavour.<br/>
	 * <b>-</b> Penis modifiers.<br/>
	 * <b>-</b> Penis urethra settings (capacity, wetness, plasticity, elasticity, modifiers).<br/>
	 * <br/>
	 * <b>Vagina:</b><br/>
	 * <b>-</b> Virginity.<br/>
	 * <b>-</b> Clit size.<br/>
	 * <b>-</b> Labia size.<br/>
	 * <b>-</b> Squirter.<br/>
	 * <b>-</b> Girlcum modifiers & flavour.<br/>
	 * <b>-</b> Vagina settings (capacity, wetness, plasticity, elasticity, modifiers).<br/>
	 * <b>-</b> Vagina urethra settings (capacity, wetness, plasticity, elasticity, modifiers).<br/>
	 * <br/>
	 * <b>Feet:</b><br/>
	 * <b>-</b> Foot structure.<br/>
	 */
	public abstract void setStartingBody(boolean setPersona);
	
	public abstract void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells);

	public void setStartingPersona() {
		setStartingPersona(true, true, true, true, true);
	}
	
	public final void equipClothing() {
		equipClothing(new ArrayList<>());
	}
	
	/**
	 * Helper method that should be overridden and included in constructor. Should set starting clothing and piercings.<br/>
	 * <b><u>What to include</u></b><br/>
	 * <b>-</b> Weapons.<br/>
	 * <b>-</b> Tattoos.<br/>
	 * <b>-</b> Scars.<br/>
	 * <b>-</b> Piercings.<br/>
	 * <b>-</b> Clothing (remember underwear and accessories).<br/>
	 */
	public void equipClothing(List<EquipClothingSetting> settings) {
		Main.game.getCharacterUtils().equipClothingFromOutfit(this, null, settings);
	}
	
	protected void resetBodyAfterVersion_2_10_5() {
		// Need to save and restore breast size/lactation from pregnancy changes.
		CupSize size = this.getBreastSize();
		float milkStorage = this.getBreastRawMilkStorageValue();
		float milkStored = this.getBreastRawStoredMilkValue();
		
		setStartingBody(true);
		equipClothing(EquipClothingSetting.getAllClothingSettings());
		
		this.setBreastSize(size.getMeasurement());
		this.setBreastMilkStorage((int) milkStorage);
		this.setBreastStoredMilk(milkStored);
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		Element npcSpecific = doc.createElement("npcSpecific");
		properties.appendChild(npcSpecific);

		XMLUtil.createXMLElementWithValue(doc, npcSpecific, "lastTimeEncountered", String.valueOf(lastTimeEncountered));
		XMLUtil.createXMLElementWithValue(doc, npcSpecific, "buyModifier", String.valueOf(buyModifier));
		XMLUtil.createXMLElementWithValue(doc, npcSpecific, "sellModifier", String.valueOf(sellModifier));
		XMLUtil.createXMLElementWithValue(doc, npcSpecific, "playerSurrenderCount", String.valueOf(playerSurrenderCount));
		XMLUtil.createXMLElementWithValue(doc, npcSpecific, "addedToContacts", String.valueOf(addedToContacts));

		XMLUtil.createXMLElementWithValue(doc, npcSpecific, "generateExtraItems", String.valueOf(generateExtraItems));
		XMLUtil.createXMLElementWithValue(doc, npcSpecific, "generateDisposableClothing", String.valueOf(generateDisposableClothing));
		XMLUtil.createXMLElementWithValue(doc, npcSpecific, "generateExtraClothing", String.valueOf(generateExtraClothing));
		
		Element valuesElement = doc.createElement("NPCValues");
		npcSpecific.appendChild(valuesElement);
		for(NPCFlagValue value : NPCFlagValues) {
			XMLUtil.createXMLElementWithValue(doc, valuesElement, "NPCValue", value.toString());
		}

		if(genderPreference!=null) {
			XMLUtil.createXMLElementWithValue(doc, npcSpecific, "genderPreference", String.valueOf(genderPreference));
			XMLUtil.createXMLElementWithValue(doc, npcSpecific, "subspeciesPreference", Subspecies.getIdFromSubspecies(subspeciesPreference));
			XMLUtil.createXMLElementWithValue(doc, npcSpecific, "raceStagePreference", String.valueOf(raceStagePreference));
		}
		
		return properties;
	}
	
	public abstract void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings);
	
	public static void loadNPCVariablesFromXML(NPC npc, StringBuilder log, Element parentElement, Document doc, CharacterImportSetting... settings) {
		GameCharacter.loadGameCharacterVariablesFromXML(npc, log, parentElement, doc, settings);
		
		Element npcSpecificElement = (Element) parentElement.getElementsByTagName("npcSpecific").item(0);
		
		if(npcSpecificElement!=null) {
			npc.setLastTimeEncountered(Long.valueOf(((Element)npcSpecificElement.getElementsByTagName("lastTimeEncountered").item(0)).getAttribute("value")));
			
			npc.setBuyModifier(Float.valueOf(((Element)npcSpecificElement.getElementsByTagName("buyModifier").item(0)).getAttribute("value")));
			npc.setSellModifier(Float.valueOf(((Element)npcSpecificElement.getElementsByTagName("sellModifier").item(0)).getAttribute("value")));
			if(((Element)npcSpecificElement.getElementsByTagName("playerSurrenderCount").item(0))!=null) {
				npc.playerSurrenderCount = Integer.valueOf(((Element)npcSpecificElement.getElementsByTagName("playerSurrenderCount").item(0)).getAttribute("value"));
			}
			npc.addedToContacts = (Boolean.valueOf(((Element)npcSpecificElement.getElementsByTagName("addedToContacts").item(0)).getAttribute("value")));
		

			if(((Element)npcSpecificElement.getElementsByTagName("generateExtraItems").item(0))!=null) {
				npc.generateExtraItems = (Boolean.valueOf(((Element)npcSpecificElement.getElementsByTagName("generateExtraItems").item(0)).getAttribute("value")));
			}
			if(((Element)npcSpecificElement.getElementsByTagName("generateDisposableClothing").item(0))!=null) {
				npc.generateDisposableClothing = (Boolean.valueOf(((Element)npcSpecificElement.getElementsByTagName("generateDisposableClothing").item(0)).getAttribute("value")));
			}
			if(((Element)npcSpecificElement.getElementsByTagName("generateExtraClothing").item(0))!=null) {
				npc.generateExtraClothing = (Boolean.valueOf(((Element)npcSpecificElement.getElementsByTagName("generateExtraClothing").item(0)).getAttribute("value")));
			}
	
			NodeList npcValues = ((Element) npcSpecificElement.getElementsByTagName("NPCValues").item(0)).getElementsByTagName("NPCValue");
			for(int i = 0; i < npcValues.getLength(); i++){
				Element e = (Element) npcValues.item(i);
				try {
					npc.NPCFlagValues.add(NPCFlagValue.valueOf(e.getAttribute("value")));
				} catch(Exception ex) {
				}
			}
			
			try {
				npc.genderPreference = Gender.valueOf(((Element)npcSpecificElement.getElementsByTagName("genderPreference").item(0)).getAttribute("value"));
				npc.subspeciesPreference = Subspecies.getSubspeciesFromId(((Element)npcSpecificElement.getElementsByTagName("subspeciesPreference").item(0)).getAttribute("value"));
				npc.raceStagePreference = RaceStage.valueOf(((Element)npcSpecificElement.getElementsByTagName("raceStagePreference").item(0)).getAttribute("value"));
			} catch(Exception ex) {}
		}
		// It seems, that upon loading an NPC, the fleshSubspecies unintentionally gets set to Subspecies.HUMAN, even if it's clearly not a human. 
		// This clears the cached value, so it's being recalculated just-in-time. ~Stadler76
		npc.getBody().setFleshSubspecies(null);
	}

	public static void loadNPCVariablesFromXML(NPC npc, XMLLoader loader, CharacterImportSetting... settings) {
		CharacterLoader.loadGameCharacterVariables(loader, npc, settings);

		if(loader.moveToIfHasChildren("npcSpecific")) {
			npc.setLastTimeEncountered(loader.getElementAttributeAsLong("lastTimeEncountered"));

			npc.setBuyModifier(loader.getElementAttributeAsFloat("buyModifier"));
			npc.setSellModifier(loader.getElementAttributeAsFloat("sellModifier"));
			if(!loader.getElementAttributeAsStr("playerSurrenderCount").isEmpty()) {
				npc.playerSurrenderCount = loader.getElementAttributeAsInt("playerSurrenderCount");
			}
			npc.addedToContacts = loader.getElementAttributeAsBoolean("addedToContacts");


			if(!loader.getElementAttributeAsStr("generateExtraItems").isEmpty()) {
				npc.generateExtraItems = loader.getElementAttributeAsBoolean("generateExtraItems");
			}
			if(!loader.getElementAttributeAsStr("generateDisposableClothing").isEmpty()) {
				npc.generateDisposableClothing = loader.getElementAttributeAsBoolean("generateDisposableClothing");
			}
			if(!loader.getElementAttributeAsStr("generateExtraClothing").isEmpty()) {
				npc.generateExtraClothing = loader.getElementAttributeAsBoolean("generateExtraClothing");
			}

			if (loader.moveToIfHasChildren("NPCValues")) {
				loader.forEachAttributeOfElement("NPCValue", "value", npcFlag -> {
					npc.NPCFlagValues.add(NPCFlagValue.valueOf(npcFlag));
				});
				loader.goToParent();
			}

			if (!loader.getElementAttributeAsStr("genderPreference").isEmpty())
				npc.genderPreference = Gender.valueOf(loader.getElementAttributeAsStr("genderPreference"));
			if (!loader.getElementAttributeAsStr("subspeciesPreference").isEmpty())
				npc.subspeciesPreference = Subspecies.getSubspeciesFromId(loader.getElementAttributeAsStr("subspeciesPreference"));
			if (!loader.getElementAttributeAsStr("raceStagePreference").isEmpty())
				npc.raceStagePreference = RaceStage.valueOf(loader.getElementAttributeAsStr("raceStagePreference"));
			loader.goToParent();
		}
		// It seems, that upon loading an NPC, the fleshSubspecies unintentionally gets set to Subspecies.HUMAN, even if it's clearly not a human.
		// This clears the cached value, so it's being recalculated just-in-time. ~Stadler76
		npc.getBody().setFleshSubspecies(null);
	}
	
	public void resetSlaveFlags() {
		for(NPCFlagValue flag : NPCFlagValue.getSlaveFlags()) {
			NPCFlagValues.remove(flag);
		}
	}
	
	public void resetOccupantFlags() {
		for(NPCFlagValue flag : NPCFlagValue.getOccupantFlags()) {
			NPCFlagValues.remove(flag);
		}
	}
	
	/**
	 * Applies a daily update to this NPC, called at midnight. Usually used for traders resetting their inventories.
	 */
	public void dailyUpdate() {
		if (ModProps.characterAgeProgression()) {
//			int worked = 0; future system
//			for (SlaveJob workHour : workHours) {
//				if (workHour != SlaveJob.IDLE || workHour != SlaveJob.BEDROOM)
//					worked++;
//			}
//			averageWorkHours = ((averageWorkHours + worked) >> 1);
			Conversions.applyGrowth(this);
		}
	}


	/**
	 * Calls hourlyUpdate(int hour) using the current hourOfDay as the hour argument.
	 */
	public void hourlyUpdate() {
		this.cleanAllWetSlots();
		hourlyUpdate(Main.game.getHourOfDay());
	}
	
	/**
	 * Applies an hourly update to this NPC.
	 * @param hour The hour (0-23) which is the currently active hour during the game's update loop.
	 */
	public void hourlyUpdate(int hour) {
	}
	
	/**
	 * Applies an update to this NPC every time the game makes a turn.
	 */
	public void turnUpdate() {
	}
	
	public abstract void changeFurryLevel();
	
	public abstract DialogueNode getEncounterDialogue();

	public int getPlayerSurrenderCount() {
		return playerSurrenderCount;
	}

	public void setPlayerSurrenderCount(int playerSurrenderCount) {
		this.playerSurrenderCount = playerSurrenderCount;
	}

	public void incrementPlayerSurrenderCount(int increment) {
		setPlayerSurrenderCount(getPlayerSurrenderCount() + increment);
	}
	
	public boolean isClothingStealable() {
		return false;
	}

	/**
	 * Check for this NPC's willingness to have clothing equipped on them.
	 */
	public Value<Boolean, String> isInventoryEquipAllowed(AbstractClothing clothing, InventorySlot slotToEquipTo) {
		if((this.isSlave() && this.getOwner().isPlayer()) || clothing.isCondom(slotToEquipTo)) {
			// Can always equip condoms onto anyone (as they have a getCondomEquipEffects() method to handle it)
			// Can also always equip anything onto owned slaves
			return new Value<>(true, ""); 
		}
		if(this.isUnique() && !clothing.isCondom(slotToEquipTo)) {
			if (ModProps.equipUniques()) return new Value<>(true, "");
			return new Value<>(false, "由于[npc.name]是特殊角色，不能成为你的奴隶，你无法强制[npc.herHim]装备"+clothing.getName()+"！");
		}
		if(Main.game.isInSex() && (Main.sex.isDom(this) || Main.sex.isSubHasEqualControl())) {
			return new Value<>(false, "[npc.Name]对于装备"+clothing.getName()+"并不感兴趣！");
		}
		return new Value<>(true, "");
	}

	public String getPresentInTileDescription(boolean inHiding) {
		StringBuilder tileSB = new StringBuilder();

		tileSB.append("<p style='text-align:center;'><i>");
		
		if(!this.isRaceConcealed()) {
			tileSB.append((this.isPlayerKnowsName()
								?"[npc.Name]，[npc.a_femininity(true)]的[npc.raceStage(true)][npc.race(true)]，"
								:"[npc.A_femininity(true)]的[npc.raceStage(true)][npc.race(true)]")
							+ "正"+(inHiding?"[style.boldBad(潜伏)]":"游荡")+"于该区域！");
		} else {
			tileSB.append("某人或某物正"+(inHiding?"[style.boldBad(隐藏)]":"游荡")+"于该区域！");
		}
		
		// Combat:
		if(this.getFoughtPlayerCount()>0) {
			tileSB.append("<br/>");
			tileSB.append("你已经[style.colourCombat(与[npc.herHim]战斗过"+Util.intToCount(this.getFoughtPlayerCount())+")]");
			if(this.getFoughtPlayerCount()==this.getLostCombatCount()) {
				if(this.getLostCombatCount()==1) {
					tileSB.append("，并且[style.colourGood(胜利了)]。");
				} else if(this.getLostCombatCount()==2) {
					tileSB.append("，你[style.colourMinorGood(两次)][style.colourGood(都胜利了)]。");
				} else {
					tileSB.append("，你[style.colourMinorGood(每次)][style.colourGood(都胜利了)]。");
				}
			} else if(this.getFoughtPlayerCount()==this.getWonCombatCount()) {
				if(this.getWonCombatCount()==1) {
					tileSB.append("并且[style.colourBad(战败了)]。");
				} else if(this.getWonCombatCount()==2) {
					tileSB.append("[style.colourMinorBad(两次)][style.colourBad(都战败了)]。");
				}  else {
					tileSB.append("[style.colourMinorBad(每次)][style.colourBad(都战败了)]。");
				}
			} else {
				tileSB.append("，你[style.colourGood(战胜了)][style.colourMinorGood("+Util.intToCount(this.getLostCombatCount())+")]");
				tileSB.append("且[style.colourBad(战败了)][style.colourMinorBad("+Util.intToCount(this.getWonCombatCount())+")]。");
			}
		}
		
		// Sex:
		if(this.getTotalTimesHadSex(Main.game.getPlayer()) > 0) {
			if(this.getSexAsDomCount(Main.game.getPlayer())>0) {
				tileSB.append("<br/>");
				tileSB.append("你已经跟[npc.herHim]进行过<span style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>服从型性爱</span>");
				tileSB.append(Util.intToCount(this.getSexAsDomCount(Main.game.getPlayer()))+"。");
			}

			if(this.getSexAsSubCount(Main.game.getPlayer())>0) {
				tileSB.append("<br/>");
				tileSB.append("你已经跟[npc.herHim]进行过<span style='color:"+PresetColour.GENERIC_SEX_AS_DOM.toWebHexString()+";'>支配型性爱</span>");
				tileSB.append(Util.intToCount(this.getSexAsSubCount(Main.game.getPlayer()))+"。");
			}
		}
		
		// Bitch:
		if(this.getPlayerSurrenderCount()==3) {
			tileSB.append("<br/>");
			tileSB.append("[npc.She]下次再见到你时，会要求你<span style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>向[npc.her]臣服并且做[npc.her]的母狗</span>！");
			
		} else if(this.getPlayerSurrenderCount()>3) {
			tileSB.append("<br/>");
			tileSB.append("你已经<span style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>向[npc.her]臣服，并且做了[npc.her]的母狗</span>！");
		}

		tileSB.append("</i></p>");
		
		return UtilText.parse(this, tileSB.toString());
	}
	
	public String getPresentInTileDescription() {
		return getPresentInTileDescription(false);
	}
	

	public String getPlayerRelationStatusDescription() {
		StringBuilder sb = new StringBuilder();
		
		if(this.isRelatedTo(Main.game.getPlayer())) {
			sb.append("<p style='text-align:center;'><i>");
			AffectionLevel al = this.getAffectionLevel(Main.game.getPlayer());
			switch(al) {
				case NEGATIVE_FIVE_LOATHE:
				case NEGATIVE_FOUR_HATE:
				case NEGATIVE_THREE_STRONG_DISLIKE:
				case NEGATIVE_TWO_DISLIKE:
				case NEGATIVE_ONE_ANNOYED:
				case ZERO_NEUTRAL:
					break;
				case POSITIVE_ONE_FRIENDLY:
					if(this.isAttractedTo(Main.game.getPlayer()) && Main.game.isIncestEnabled()) {
						sb.append("对于你，[npc.Name]摆出一副<i style='color:"+al.getColour().toWebHexString()+";'>友善</i>、[style.italicsSex(挑逗)]的姿态。");
					} else {
						sb.append("对于你，[npc.Name]摆出一副<i style='color:"+al.getColour().toWebHexString()+";'>友善</i>的姿态。");
					}
					break;
				case POSITIVE_TWO_LIKE:
					if(this.isAttractedTo(Main.game.getPlayer()) && Main.game.isIncestEnabled()) {
						sb.append("[npc.Name]很明显<i style='color:"+al.getColour().toWebHexString()+";'>喜欢你</i>，而且[style.italicsSex(不仅仅将你视作[npc.her]的[pc.relation(npc)])]。");
					} else {
						sb.append("[npc.Name]很明显<i style='color:"+al.getColour().toWebHexString()+";'>喜欢你</i>，而且很高兴有你这样一个[pc.relation(npc)]。");
					}
					break;
				case POSITIVE_THREE_CARING:
					if(this.isAttractedTo(Main.game.getPlayer()) && Main.game.isIncestEnabled()) {
						sb.append("[npc.Name]明显<i style='color:"+al.getColour().toWebHexString()+";'>很在乎你</i>，被你[style.italicsSex(深深吸引)]。");
					} else {
						sb.append("[npc.Name]明显<i style='color:"+al.getColour().toWebHexString()+";'>很在乎你</i>，并且不敢奢求比你更好的[pc.relation(npc)]了。");
					}
					break;
				case POSITIVE_FOUR_LOVE:
					if(this.isAttractedTo(Main.game.getPlayer()) && Main.game.isIncestEnabled()) {
						sb.append("从[npc.Name]的行为上来看，[npc.she]很明显<i style='color:"+al.getColour().toWebHexString()+";'>爱上了你</i>，产生了[style.italicsSex(爱情)]。");
					} else {
						sb.append("从[npc.Name]的行为上来看，[npc.she]很明显<i style='color:"+al.getColour().toWebHexString()+";'>喜爱着你</i>，而且只可能是身为[npc.a_relation(pc)]才能拥有的情感。");
					}
					break;
				case POSITIVE_FIVE_WORSHIP:
					if(this.isAttractedTo(Main.game.getPlayer()) && Main.game.isIncestEnabled()) {
						sb.append("[npc.Name]<i style='color:"+al.getColour().toWebHexString()+";'>爱慕你</i>，已经[style.italicsSex(死心塌地地)]爱上了你。");
					} else {
						sb.append("[npc.Name]<i style='color:"+al.getColour().toWebHexString()+";'>仰慕你</i>，无论你请求什么事情[npc.herHim]都会尽力完成。");
					}
					break;
			}

			if(Main.game.isIncestEnabled()) {
				sb.append("<br/>");
				if(this.isAttractedTo(Main.game.getPlayer())) {
					sb.append("你注意到[npc.namePos]的视线躲闪着，似乎是在偷瞄你的身体。"
								+ "看到[npc.her][npc.eyes]中饥渴的神情，[style.italicsSex(你敢说[npc.sheIs]已经迷上了你)]……");
				} else {
					sb.append("[npc.Name][style.italicsMinorBad(没有任何受你吸引的迹象)]，[npc.she]表现出来的喜爱单纯是由于你们[pc.mother][npc.daughter]的关系。");
				}
			}
			sb.append("</i></p>");
			
		} else {
			sb.append("<p style='text-align:center;'><i>");
			AffectionLevel al = this.getAffectionLevel(Main.game.getPlayer());
			switch(al) {
				case NEGATIVE_FIVE_LOATHE:
					if(this.isAttractedTo(Main.game.getPlayer())) {
						sb.append("尽管[npc.name]看上去<i style='color:"+al.getColour().toWebHexString()+";'>极其憎恶</i>你，但你看得出[npc.sheIs]还是很对你着迷。");
					} else {
						sb.append("[npc.Name]看上去<i style='color:"+al.getColour().toWebHexString()+";'>极其憎恶</i>你。");
					}
					break;
				case NEGATIVE_FOUR_HATE:
					if(this.isAttractedTo(Main.game.getPlayer())) {
						sb.append("尽管[npc.name]看上去<i style='color:"+al.getColour().toWebHexString()+";'>很厌恶</i>你，但你看得出[npc.sheIs]还是很对你着迷。");
					} else {
						sb.append("[npc.Name]看上去<i style='color:"+al.getColour().toWebHexString()+";'>很厌恶</i>你。");
					}
					break;
				case NEGATIVE_THREE_STRONG_DISLIKE:
					if(this.isAttractedTo(Main.game.getPlayer())) {
						sb.append("尽管[npc.name]看上去<i style='color:"+al.getColour().toWebHexString()+";'>非常讨厌</i>你，但你看得出[npc.sheIs]还是很对你着迷。");
					} else {
						sb.append("[npc.Name]看上去<i style='color:"+al.getColour().toWebHexString()+";'>非常讨厌</i>你。");
					}
					break;
				case NEGATIVE_TWO_DISLIKE:
					if(this.isAttractedTo(Main.game.getPlayer())) {
						sb.append("尽管[npc.name]看上去<i style='color:"+al.getColour().toWebHexString()+";'>讨厌</i>你，但你看得出[npc.sheIs]还是很对你着迷。");
					} else {
						sb.append("[npc.Name]看上去<i style='color:"+al.getColour().toWebHexString()+";'>讨厌</i>你。");
					}
					break;
				case NEGATIVE_ONE_ANNOYED:
					if(this.isAttractedTo(Main.game.getPlayer())) {
						sb.append("尽管[npc.name]看上去<i style='color:"+al.getColour().toWebHexString()+";'>对你感到恼火</i>，但你看得出[npc.sheIs]还是很对你着迷。");
					} else {
						sb.append("[npc.Name]看上去<i style='color:"+al.getColour().toWebHexString()+";'>对你感到恼火</i>。");
					}
					break;
				case ZERO_NEUTRAL:
					if(this.isAttractedTo(Main.game.getPlayer())) {
						sb.append("对于你，[npc.Name]摆出一副<i style='color:"+al.getColour().toWebHexString()+";'>友好、挑逗</i>的姿态。");
					} else {
						sb.append("对于你，[npc.Name]摆出一副<i style='color:"+al.getColour().toWebHexString()+";'>友好</i>的姿态。");
					}
					break;
				case POSITIVE_ONE_FRIENDLY:
					if(this.isAttractedTo(Main.game.getPlayer())) {
						sb.append("对于你，[npc.Name]摆出一副<i style='color:"+al.getColour().toWebHexString()+";'>友善、挑逗</i>的姿态。");
					} else {
						sb.append("对于你，[npc.Name]摆出一副<i style='color:"+al.getColour().toWebHexString()+";'>友善</i>的姿态。");
					}
					break;
				case POSITIVE_TWO_LIKE:
					if(this.isAttractedTo(Main.game.getPlayer())) {
						sb.append("[npc.Name]很明显<i style='color:"+al.getColour().toWebHexString()+";'>喜欢你</i>，而且不仅仅将你视作朋友。");
					} else {
						sb.append("[npc.Name]很明显<i style='color:"+al.getColour().toWebHexString()+";'>喜欢你</i>，而且将你视作一位好朋友。");
					}
					break;
				case POSITIVE_THREE_CARING:
					if(this.isAttractedTo(Main.game.getPlayer())) {
						sb.append("[npc.Name]明显<i style='color:"+al.getColour().toWebHexString()+";'>很在乎你</i>，而且被你深深吸引。");
					} else {
						sb.append("[npc.Name]明显<i style='color:"+al.getColour().toWebHexString()+";'>很在乎你</i>，而且认为你是[npc.her]最好的朋友。");
					}
					break;
				case POSITIVE_FOUR_LOVE:
					if(this.isAttractedTo(Main.game.getPlayer())) {
						sb.append("从[npc.Name]的行为上来看，[npc.she]很明显<i style='color:"+al.getColour().toWebHexString()+";'>爱上了你</i>。");
					} else {
						sb.append("从[npc.Name]的行为上来看，[npc.she]很明显<i style='color:"+al.getColour().toWebHexString()+";'>喜爱着你</i>，一种柏拉图式的友谊。");
					}
					break;
				case POSITIVE_FIVE_WORSHIP:
					if(this.isAttractedTo(Main.game.getPlayer())) {
						sb.append("[npc.Name]<i style='color:"+al.getColour().toWebHexString()+";'>爱慕你</i>，已经死心塌地地爱上了你。");
					} else {
						sb.append("[npc.Name]<i style='color:"+al.getColour().toWebHexString()+";'>仰慕你</i>，无论你请求什么事情[npc.herHim]都会尽力完成。");
					}
					break;
			}
			sb.append("</i></p>");
		}
		
		return UtilText.parse(this, sb.toString());
	}
	
	// Trader:

	public String getTraderDescription() {
		return UtilText.parse(this,
				"<p>"
					+ "你看了看[npc.name]有什么卖。"
				+ "</p>");
	}

	public boolean isTrader() {
		return false;
	}

	public boolean willBuy(AbstractCoreItem item) {
		return false;
	}

	public float getBuyModifier() {
		return buyModifier;
	}

	public void setBuyModifier(float buyModifier) {
		this.buyModifier = buyModifier;
	}

	/**
	 * @param item The item which this NPC is selling. If selling a slave, pass in null.
	 * @return The sell price modifier of the passed in item as a percentage.
	 */
	public float getSellModifier(AbstractCoreItem item) {
		float base = sellModifier;
		if(item instanceof AbstractItem) {
			if(((AbstractItem)item).getItemType()==ItemType.getItemTypeFromId("innoxia_pills_sterility")) {
				base*=10;
			}
		}
		return Math.max(getBuyModifier(), (base * (Main.game.getPlayer().hasTrait(Perk.JOB_STUDENT, true)?0.75f:1)));
	}

	public void setSellModifier(float sellModifier) {
		this.sellModifier = sellModifier;
	}

	/**
	 * This method is called every time this NPC is involved in a transaction with the player (in InventoryDialogue.java).
	 * By default it does nothing, but it can be overridden in individual NPC classes to define special behaviour.
	 * <br/><br/>
	 * You can safely set the DialogueFlagValue.removeTraderDescription to true here to prevent this NPC's getTraderDescription() text from being displayed.
	 * This flag is reset to false at the start of every transaction (before this method is called), so don't worry about manually resetting it to false.
	 * 
	 * @param itemSold the item that was the subject of this transaction
	 * @param quantity how many of these items were sold
	 * @param individualPrice the price of each item
	 * @param soldToPlayer true if the item was sold to the player, false if the item was sold by the player to this NPC
	 */
	public void applyItemTransactionEffects(AbstractCoreItem itemSold, int quantity, int individualPrice, boolean soldToPlayer) {
//		if(soldToPlayer) {
//			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.removeTraderDescription, true);
//			Main.game.appendToTextStartStringBuilder("<p>You bought something!</p>");
//		}
	}
	
	
	// Combat:
	
	private List<Spell> getSpellsAbleToCast() {
		List<Spell> spellsAbleToCast = new ArrayList<>();
		
		for(Spell spell : this.getAllSpells()) {
			if(this.getMana()>spell.getModifiedCost(this)) {
				if(this.isElemental()) {
					if(spell!=Spell.ELEMENTAL_AIR
							&& spell!=Spell.ELEMENTAL_ARCANE
							&& spell!=Spell.ELEMENTAL_EARTH
							&& spell!=Spell.ELEMENTAL_FIRE
							&& spell!=Spell.ELEMENTAL_WATER) {
						spellsAbleToCast.add(spell);
					}
					
				} else {
					spellsAbleToCast.add(spell);
				}
			}
		}
		
		return spellsAbleToCast;
	}
	
	/**
	 * @param target The character that his character is targeting in combat.
	 * @return A weighted map of spell -> weight.
	 */
	public Map<Spell, Integer> getWeightedSpellsAvailable(GameCharacter target) {
		Map<Spell, Integer> weightedSpellMap = new HashMap<>();
		
//		System.out.println(this.getName()+" "+target.getName()+": "+Main.combat.isOpponent(this, target));
		
		for(Spell spell : getSpellsAbleToCast()) {
			switch(spell) {
				// Basic offensive spells:
				case ARCANE_AROUSAL:
				case FIREBALL:
				case ICE_SHARD:
				case POISON_VAPOURS:
				case SLAM:
				case VACUUM:
				case ARCANE_LIGHTNING_SUPERBOLT:
					if(Main.combat.isOpponent(this, target)) {
						weightedSpellMap.put(spell, 1);
					}
					break;
				case ARCANE_CHAIN_LIGHTNING:
					if(Main.combat.getEnemies(this).size()>1) {
						weightedSpellMap.put(spell, 1);
					}
					break;
					
				// Spells that are based on applying status-effects:
				case ARCANE_CLOUD:
					if(Main.combat.isOpponent(this, target)
							&& !target.hasStatusEffect(StatusEffect.ARCANE_CLOUD)
							&& !target.hasStatusEffect(StatusEffect.ARCANE_CLOUD_ARCANE_LIGHTNING)
							&& !target.hasStatusEffect(StatusEffect.ARCANE_CLOUD_ARCANE_THUNDER)
							&& !target.hasStatusEffect(StatusEffect.ARCANE_CLOUD_LOCALISED_STORM)) {
						weightedSpellMap.put(spell, 1);
					}
					break;
				case CLOAK_OF_FLAMES:
					if(!Main.combat.isOpponent(this, target)
							&& !target.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES)
							&& !target.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES_1)
							&& !target.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES_2)
							&& !target.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES_3)) {
						weightedSpellMap.put(spell, 1);
					}
					break;
				case PROTECTIVE_GUSTS:
					if(!Main.combat.isOpponent(this, target)
							&& !target.hasStatusEffect(StatusEffect.PROTECTIVE_GUSTS)
							&& !target.hasStatusEffect(StatusEffect.PROTECTIVE_GUSTS_FOCUSED_BLAST)
							&& !target.hasStatusEffect(StatusEffect.PROTECTIVE_GUSTS_GUIDING_WIND)) {
						weightedSpellMap.put(spell, 1);
					}
					break;
				case RAIN_CLOUD:
					if(Main.combat.isOpponent(this, target)
							&& !target.hasStatusEffect(StatusEffect.RAIN_CLOUD)
							&& !target.hasStatusEffect(StatusEffect.RAIN_CLOUD_CLOUDBURST)
							&& !target.hasStatusEffect(StatusEffect.RAIN_CLOUD_DEEP_CHILL)
							&& !target.hasStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR)
							&& !target.hasStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST)) {
						weightedSpellMap.put(spell, 1);
					}
					break;
				case SOOTHING_WATERS:
					if(!Main.combat.isOpponent(this, target) && target.getHealthPercentage()<0.8f) {
						weightedSpellMap.put(spell, (int) (1-(target.getHealthPercentage()*10))/2);
					}
					break;
				case STONE_SHELL:
					if(!Main.combat.isOpponent(this, target)
							&& !target.hasStatusEffect(StatusEffect.STONE_SHELL)
							&& !target.hasStatusEffect(StatusEffect.STONE_SHELL_EXPLOSIVE_FINISH)
							&& !target.hasStatusEffect(StatusEffect.STONE_SHELL_HARDENED_CARAPACE)
							&& !target.hasStatusEffect(StatusEffect.STONE_SHELL_SHIFTING_SANDS)) {
						weightedSpellMap.put(spell, 1);
					}
					break;
				case TELEKENETIC_SHOWER:
					if(Main.combat.isOpponent(this, target)
							&& !target.hasStatusEffect(StatusEffect.TELEKENETIC_SHOWER)
							&& !target.hasStatusEffect(StatusEffect.TELEKENETIC_SHOWER_PRECISION_STRIKES)
							&& !target.hasStatusEffect(StatusEffect.TELEKENETIC_SHOWER_UNSEEN_FORCE)) {
						weightedSpellMap.put(spell, 1);
					}
					break;
				case TELEPATHIC_COMMUNICATION:
					if(!Main.combat.isOpponent(this, target)
							&& !target.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION)
							&& !target.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION)
							&& !target.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED)
							&& !target.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH)) {
						weightedSpellMap.put(spell, 1);
					}
					break;
				case TELEPORT:
					if(!Main.combat.isOpponent(this, target)
							&& !target.hasStatusEffect(StatusEffect.TELEPORT)
							&& !target.hasStatusEffect(StatusEffect.TELEPORT_ARCANE_ARRIVAL)) {
						weightedSpellMap.put(spell, 1);
					}
					break;
				case WITCH_CHARM:
					if(!Main.combat.isOpponent(this, target)
							&& !target.hasStatusEffect(StatusEffect.WITCH_CHARM)) {
						weightedSpellMap.put(spell, 1);
					}
					break;
					
				// Stuns:
				case FLASH:
				case WITCH_SEAL:
					if(Main.combat.isOpponent(this, target) && target.getAttributeValue(Attribute.ACTION_POINTS)>=3) {
						weightedSpellMap.put(spell, 1);
					}
					break;
					
				// Special condition spells:
				case CLEANSE:
					//TODO need to check for enemy & positive SEs, or ally & negative SEs
//					if(Main.combat.isOpponent(this, target)) {
//						
//					}
					break;
				case LILITHS_COMMAND:
					// TODO
					weightedSpellMap.put(spell, 1);
					break;
				case STEAL:
					// TODO
					weightedSpellMap.put(spell, 1);
					break;
					
				// Elementals:
				case ELEMENTAL_AIR:
				case ELEMENTAL_ARCANE:
				case ELEMENTAL_EARTH:
				case ELEMENTAL_FIRE:
				case ELEMENTAL_WATER:
					if(!(this.isElemental()) && !this.isElementalSummoned()) {
						weightedSpellMap.put(spell, 1);
					}
					break;
				// Spells that should not be used:
				// TODO
				case DARK_SIREN_SIRENS_CALL:
				case LIGHTNING_SPHERE_DISCHARGE:
				case LIGHTNING_SPHERE_OVERCHARGE:
					break;
			}
		}
		
		return weightedSpellMap;
	}
	
	/**
	 * Handles the behaviour when the player escapes from this enemy in combat.
	 */
	public void applyEscapeCombatEffects() {
	}
	
	public Response endCombat(boolean applyEffects, boolean playerVictory) {
		return null;
	}

	/**
	 * If this character has special scenes which interrupt combat at a certain point, then use this method to add them.
	 * (It is called at the end of every combat turn, and if it returns non-null values, it returns them as interrupting responses.)
	 */
	public Response interruptCombatSpecialCase() {
		return null;
	}
	
	/**
	 * @return The chance of enemies managing to escape from this NPC. Defined as an int from 0-100, representing percentage.
	 */
	public int getEscapeChance() {
		return (int) (30 * (this.hasTrait(Perk.JOB_BOUNTY_HUNTER, true)?0.5f:1));
	}

	public boolean isSurrendersAtZeroMana() {
		return true;
	}

	public int getProstitutePrice() {
		float prostitutePrice = 1f;

		if(this.isFeminine()) {
			prostitutePrice += 0.5f;
		}
		prostitutePrice += (this.getBody().getBreast().getRawSizeValue() - 7) * 0.02f; // Breast size.
		if(this.hasVagina()) {
			prostitutePrice += 0.15f; // More expensive if prostitute has a vagina.
		}
		if(this.hasPenis()) {
			prostitutePrice += Math.min((this.getBody().getPenis().getRawLengthValue() - 5) * 0.01f, 0.10f); // Penalises small penises, but adds price if penis is large.
		}
		if(this.isBreastFuckableNipplePenetration()) {
			prostitutePrice += 0.15f;  // Fuckable nipples add to price.
		}
		if(this.isVisiblyPregnant()) {
			prostitutePrice = prostitutePrice * 0.5f; // Pregnant prostitutes charge 50% of their usual price.
		}
		
		prostitutePrice += this.getTrueLevel()*0.05f; // Increase price for higher level prostitutes
		
		return Math.max(150, ((int) (prostitutePrice*50))*10); // Minimum value is 150 flames.
	}
	
	// Post-combat:

	public int getExperienceFromVictory() {
		return (int)(getLevel() * 2 * (ModProps.xpMultiplier / 100f));
	}

	public int getLootMoney() {
		return (int) (((getLevel() * 25) * (1 + Math.random() - 0.5f)) * (ModProps.moneyMultiplier / 100f));
	}
	
	public boolean isLootingPlayerAfterCombat() {
		return true;
	}
	
	public List<AbstractCoreItem> getLootItems() {
		double rnd = Math.random();

		List<AbstractCoreItem> loot = new ArrayList<>();
		for (int lr = 0; lr < ModProps.itemDropsIncrease; lr++) {
			if (rnd <= 0.05) {
				loot.add(Main.game.getItemGen().generateItem(ItemType.FETISH_UNREFINED));

			} else if (rnd <= 0.1) {
				loot.add(Main.game.getItemGen().generateItem(ItemType.ADDICTION_REMOVAL));

			} else {
				AbstractItemType raceIngredient = getSubspecies().getAttributeItem(this);
				AbstractItemType raceTFIngredient = getSubspecies().getTransformativeItem(this);
				AbstractItemType book = getSubspecies().getBook();


				if (rnd < 0.6 && raceTFIngredient != null) {
					loot.add(Main.game.getItemGen().generateItem(raceTFIngredient));

				} else if (rnd <= 0.8 && !Main.game.getPlayer().getRacesDiscoveredFromBook().contains(getSubspecies())) {
					loot.add(Main.game.getItemGen().generateItem(book));

				} else if (raceIngredient != null) {
					loot.add(Main.game.getItemGen().generateItem(raceIngredient));

				} else {
					loot.add(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH));
				}
			}
		}
		return loot;
	}
	
	public int getLootEssenceDrops() {
		return Util.random.nextInt(this.getLevel())+1;
	}

	// Item generation:

	public boolean isGenerateExtraItems() {
		return generateExtraItems;
	}

	public void setGenerateExtraItems(boolean generateExtraItems) {
		this.generateExtraItems = generateExtraItems;
	}

	public boolean isGenerateDisposableClothing() {
		return generateDisposableClothing;
	}

	public void setGenerateDisposableClothing(boolean generateDisposableClothing) {
		this.generateDisposableClothing = generateDisposableClothing;
	}

	public boolean isGenerateExtraClothing() {
		return generateExtraClothing;
	}

	public void setGenerateExtraClothing(boolean generateExtraClothing) {
		this.generateExtraClothing = generateExtraClothing;
	}

	
	// Relationships:
	
	public float getHourlyAffectionChange(int hour) {
		SlaveJob job = this.getSlaveJob(hour);
		
		// Rounding is to get rid of floating point ridiculousness (e.g. 2.3999999999999999999999):
		if(this.getSlaveJob(hour)==SlaveJob.IDLE) {
			return Math.round(this.getHomeLocationPlace().getHourlyAffectionChange()*100)/100f;
		} else {
			float overworkedPenalty = 1f;
			// Instead of checking for status effect, check if conditions met as this fixes a UI bug where the affection would not immediately account for the change in overworked status effects
			if(StatusEffect.OVERWORKED_1.isConditionsMet(this)) {
				overworkedPenalty = 0.5f;
			} else if(StatusEffect.OVERWORKED_2.isConditionsMet(this)) {
				overworkedPenalty = 0.2f;
			} else if(StatusEffect.OVERWORKED_3.isConditionsMet(this)) {
				overworkedPenalty = 0f;
			}
			float affectionGain = Math.round(job.getAffectionGain(this)*100)/100f;
			return Math.min(affectionGain, affectionGain*overworkedPenalty);
		}
	}
	
	public float getDailyAffectionChange() {
		float totalAffectionChange = 0;
		
//		for (int hour = 0; hour < 24; hour++) {
//			SlaveJob job = this.getSlaveJob(hour);
//			if(this.getSlaveJob(hour)==SlaveJob.IDLE) {
//				totalAffectionChange += this.getHomeLocationPlace().getHourlyAffectionChange();
//			} else {
//				totalAffectionChange += job.getAffectionGain(hour, this);
//			}
//		}
	    for (int hour = 0; hour < 24; hour++) {
	        totalAffectionChange += getHourlyAffectionChange(hour);
	    }
	    // Check conditions met as the status effects are updated AFTER the menu - UI bugfix
	    if(StatusEffect.OVERWORKED_1.isConditionsMet(this)) {
	        totalAffectionChange -= (0.5f*24);
	    } else if(StatusEffect.OVERWORKED_2.isConditionsMet(this)) {
	        totalAffectionChange -= (1f*24);
	    } else if(StatusEffect.OVERWORKED_3.isConditionsMet(this)) {
	        totalAffectionChange -= (2f*24);
	    }

		// Rounding is to get rid of floating point ridiculousness (e.g. 2.3999999999999999999999):
		return Math.round(totalAffectionChange*100)/100f;
	}
	
	
	// Misc:

	/**
	 * By default, NPCs can't be impregnated.
	 */
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}
	
	/**
	 * By default, NPCs can be implanted with eggs if they are a non-unique character, or if the player owns them (as a slave).
	 */
	@Override
	public boolean isAbleToBeEgged() {
		return (!this.isUnique() || (this.isSlave() && this.getOwner().isPlayer()))
				&& !this.hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_2)
				&& !this.isElemental();
	}

	public boolean isReadyToBeDeleted() {
		if (this.isUnique()
		 	|| this instanceof Elemental
			|| this instanceof ReindeerOverseer
			|| this instanceof GenericFemaleNPC
			|| this instanceof GenericMaleNPC
			|| this instanceof GenericAndrogynousNPC
			|| this instanceof PrologueFemale
			|| this instanceof PrologueMale
			|| this	instanceof NPCOffspring) {
			return false;
		}

		for(Litter litter : this.getIncubatingLitters().values()) {
			if((litter.getMother()!=null && litter.getMother().isPlayer()) || (litter.getFather()!=null && litter.getFather().isPlayer())) {
				return false;
			}
		}

		for(Litter litter : Main.game.getPlayer().getIncubatingLitters().values()) {
			if((litter.getMother()!=null && litter.getMother().equals(this)) || (litter.getFather()!=null && litter.getFather().equals(this))) {
				return false;
			}
		}

		if((this.getPregnantLitter()!=null && this.getPregnantLitter().getFather()!=null && this.getPregnantLitter().getFather().isPlayer()) // NPC needs to birth litter where player is father
			|| (Main.game.getPlayer().getPregnantLitter()!=null && Main.game.getPlayer().getPregnantLitter().getFather()!=null && Main.game.getPlayer().getPregnantLitter().isFather(this))) { // player needs to birth litter where NPC is father
			return false;
		}

		for (PregnancyPossibility possibility : this.getPotentialPartnersAsMother()) {
			if(possibility.getMother()!=null && possibility.getMother().isPlayer()
				|| possibility.getFather()!=null && possibility.getFather().isPlayer()) {
				return false;
			}
		}

		for (PregnancyPossibility possibility : this.getPotentialPartnersAsFather()) {
			if(possibility.getMother()!=null && possibility.getMother().isPlayer()
				|| possibility.getFather()!=null && possibility.getFather().isPlayer()) {
				return false;
			}
		}

		for (PregnancyPossibility possibility : Main.game.getPlayer().getPotentialPartnersAsMother()) {
			if(possibility.getMother()!=null && possibility.getMother().equals(this)
				|| possibility.getFather()!=null && possibility.getFather().equals(this)) {
				return false;
			}
		}

		for (PregnancyPossibility possibility : Main.game.getPlayer().getPotentialPartnersAsFather()) {
			if(possibility.getMother()!=null && possibility.getMother().equals(this)
				|| possibility.getFather()!=null && possibility.getFather().equals(this)) {
				return false;
			}
		}

		return true;
	}

	public boolean hasFlag(NPCFlagValue flag) {
		return NPCFlagValues.contains(flag);
	}
	
	public boolean addFlag(NPCFlagValue flag) {
		return NPCFlagValues.add(flag);
	}
	
	public boolean removeFlag(NPCFlagValue flag) {
		return NPCFlagValues.remove(flag);
	}
	
	public boolean setFlag(NPCFlagValue flag, boolean value) {
		if(value) {
			return addFlag(flag);
		} else {
			return removeFlag(flag);
		}
	}
	
	// Utility methods for determining sex behaviour in encounter post-defeat scenes:

	public boolean isPostCombatNoSex() {
		return (!this.isAttractedTo(Main.game.getPlayer()) || this.hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) && !Main.game.isNonConEnabled();
	}

	public boolean isPostCombatWantsSex() {
		return (this.isAttractedTo(Main.game.getPlayer()) && !this.hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) || !Main.game.isNonConEnabled();
	}

	public boolean isPostCombatRapePlay() {
		return (this.isAttractedTo(Main.game.getPlayer()) && !this.hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer))
				&& this.hasFetish(Fetish.FETISH_NON_CON_SUB)
				&& Main.game.isNonConEnabled()
				&& Main.getProperties().hasValue(PropertyValue.rapePlayAtSexStart);
	}
	
	
	public boolean isKnowsPlayerGender() {
		return NPCFlagValues.contains(NPCFlagValue.knowsPlayerGender);
	}

	public void setKnowsPlayerGender(boolean knowsPlayerGender) {
		if(knowsPlayerGender) {
			NPCFlagValues.add(NPCFlagValue.knowsPlayerGender);
		} else {
			NPCFlagValues.remove(NPCFlagValue.knowsPlayerGender);
		}
	}
	
	public boolean isIntroducedToPlayer() {
		return NPCFlagValues.contains(NPCFlagValue.introducedToPlayer);
	}

	public void setIntroducedToPlayer(boolean introducedToPlayer) {
		if(introducedToPlayer) {
			NPCFlagValues.add(NPCFlagValue.introducedToPlayer);
		} else {
			NPCFlagValues.remove(NPCFlagValue.introducedToPlayer);
		}
	}
	
	public boolean isPendingClothingDressing() {
		return NPCFlagValues.contains(NPCFlagValue.pendingClothingDressing);
	}
	public void setPendingClothingDressing(boolean pendingClothingDressing) {
		if(pendingClothingDressing) {
			NPCFlagValues.add(NPCFlagValue.pendingClothingDressing);
		} else {
			NPCFlagValues.remove(NPCFlagValue.pendingClothingDressing);
		}
	}
	
	public boolean isPendingTransformationToGenderIdentity() {
		return this.getGender()!=this.getGenderIdentity()
				&& !(this.isElemental())
				&& !this.isPregnant()
				&& this.getIncubatingLitters().isEmpty()
				&& !this.isUnique()
				&& !this.isSlave()
				&& !Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())
				&& this.isAbleToSelfTransform();
	}
	
	/**
	 * Resets this character's body to align with their gender identity.
	 * @param completeReset True if you want them to completely regenerate a new body. False if you just want femininity, breasts, and genitals altered.
	 */
	public void setBodyToGenderIdentity(boolean completeReset) {
		if(completeReset) {
			boolean assVirgin = this.isAssVirgin();
			boolean faceVirgin = this.isFaceVirgin();
			boolean nippleVirgin = this.isNippleVirgin();
			boolean penisVirgin = this.isPenisVirgin();
			boolean urethraVirgin = this.isUrethraVirgin();
			boolean vaginaVirgin = this.isVaginaVirgin();
			boolean vaginaUrethraVirgin = this.isVaginaUrethraVirgin();
			
			BodyMaterial material = this.getBodyMaterial();
			this.setBody(this.getGenderIdentity(), this.getBody().getFleshSubspecies(), this.getBody().getRaceStageFromPartWeighting(), false);
			this.setBodyMaterial(material);
			Main.game.getCharacterUtils().randomiseBody(this, false);
			
			this.setAssVirgin(assVirgin);
			this.setFaceVirgin(faceVirgin);
			this.setNippleVirgin(nippleVirgin);
			this.setPenisVirgin(penisVirgin);
			this.setUrethraVirgin(urethraVirgin);
			this.setVaginaVirgin(vaginaVirgin);
			this.setVaginaUrethraVirgin(vaginaUrethraVirgin);
			
		} else {
			AbstractRacialBody racialBody = RacialBody.valueOfRace(this.getBody().getFleshSubspecies().getRace());
			if(this.getGenderIdentity().getType()==PronounType.FEMININE) {
				this.setFemininity(racialBody.getFemaleFemininity());
				
			} else if(this.getGenderIdentity().getType()==PronounType.NEUTRAL) {
				this.setFemininity(50);
				
			} else {
				this.setFemininity(racialBody.getMaleFemininity());
			}
			
			if(this.getGenderIdentity().getGenderName().isHasBreasts()) {
				this.setBreastSize(racialBody.getBreastSize());
			} else {
				this.setBreastSize(racialBody.getNoBreastSize());
			}
			
			boolean largeGenitals = this.isTaur();
			if(this.getGenderIdentity().getGenderName().isHasPenis()) {
				this.setPenisType(racialBody.getPenisType());
				this.setPenisSize((int) (racialBody.getPenisSize()*(largeGenitals?2.5f:1)));
				this.setPenisGirth(racialBody.getPenisGirth()+(largeGenitals?1:0));
				this.setPenisCumStorage(racialBody.getCumProduction()*(largeGenitals?10:1));
				this.setTesticleSize(racialBody.getTesticleSize()+(largeGenitals?1:0));
				this.setTesticleCount(racialBody.getTesticleQuantity());
			} else {
				this.setPenisType(PenisType.NONE);
			}
			
			if(this.getGenderIdentity().getGenderName().isHasVagina()) {
				this.setVaginaType(racialBody.getVaginaType());
				this.setVaginaWetness(racialBody.getVaginaWetness());
			} else {
				this.setVaginaType(VaginaType.NONE);
			}
		}
	}

	public boolean hasEncounteredBefore() {
		return lastTimeEncountered!=-1;
	}
	
	public long getLastTimeEncountered() {
		return lastTimeEncountered;
	}

	public void setLastTimeEncountered(long minutesPassed) {
		this.lastTimeEncountered = minutesPassed;
	}

	@Override
	public void setPlayerKnowsName(boolean playerKnowsName) {
		super.setPlayerKnowsName(playerKnowsName);
		if(isAddedToContacts()
	            && playerKnowsName
	            && Main.game.isStarted()
	            && Main.game.getPlayer()!=null
	            && !Main.game.getPlayer().getCharactersEncountered().contains(this.getId())) {
	            Main.game.getPlayer().addCharacterEncountered(this);
	        }
	}
	
	public boolean isAddedToContacts() {
		return addedToContacts;
	}
	
	public void applyPlayerPregnancyReactions() {
		if(this.isVisiblyPregnant()){
			this.setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(this, true);
		}
	}
	
	public boolean isUsingForcedTransform(GameCharacter target) {
		return hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)
				&& target.getRace()!=Race.ELEMENTAL // Do not try to transform elementals
				&& target.getSubspeciesOverride()==null; // Do not try to transform demons
	}
	
	public boolean isUsingForcedFetish(GameCharacter target) {
		return hasFetish(Fetish.FETISH_KINK_GIVING);
	}

	//--- Post-combat transformation methods ---//
	
	private TransformativePotion potion = null;
	private FetishPotion fetishPotion = null;
	
	public boolean isApplyingPostCombatTransformations() {
		return isApplyingPostCombatTransformations(true, true);
	}
	
	public boolean isApplyingPostCombatTransformations(boolean checkForBodyTransform, boolean checkForFetishTransform) {
		return (checkForBodyTransform && this.isUsingForcedTransform(Main.game.getPlayer()) && this.getPostCombatPotion()!=null)
				|| (checkForFetishTransform && this.isUsingForcedFetish(Main.game.getPlayer()) && this.getPostCombatFetishPotion()!=null);
	}
	
	public void generatePostCombatPotions() {
		if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
			potion = this.generateTransformativePotion(Main.game.getPlayer());
			fetishPotion = this.generateFetishPotion(Main.game.getPlayer(), true);
		} else {
			potion = null;
			fetishPotion = null;
		}
	}

	public TransformativePotion getPostCombatPotion() {
		return potion;
	}

	public FetishPotion getPostCombatFetishPotion() {
		return fetishPotion;
	}

	public String applyPostCombatTransformation() {
		GameCharacter target = Main.game.getPlayer();
		boolean forcedTF = this.isUsingForcedTransform(target);
		TransformativePotion potion = getPostCombatPotion();
		FetishPotion fetishPotion = getPostCombatFetishPotion();
		boolean forcedFetish = this.isUsingForcedFetish(target);
		
		StringBuilder sb = new StringBuilder();
		
		if(potion!=null && forcedTF) {
			sb.append(this.applyPotion(potion, target));
		}
		
		if(fetishPotion!=null && forcedFetish) {
			sb.append(this.applyPotion(fetishPotion, target));
		}
		return sb.toString();
	}
	
	/// --- ///
	
	public String getPreferredBodyDescription(String tag) {
		// If preference is demon, just do gender
		boolean cannotTransformPreference = getSubspeciesPreference().getRace()==Race.DEMON;
		
		return "<"+tag+" style='color:"+getGenderPreference().getColour().toWebHexString()+";'>"+getGenderPreference().getName()+"</"+tag+">"
				+ (cannotTransformPreference
						?""
						:"<"+tag+" style='color:"+getSubspeciesPreference().getColour(null).toWebHexString()+";'>"+getSubspeciesPreference().getName(null)+"</"+tag+">");
	}
	
	public Gender getGenderPreference() {
		if(genderPreference == null) {
			generatePartnerPreferences();
		}
		return genderPreference;
	}
	
	public AbstractSubspecies getSubspeciesPreference() {
		if(subspeciesPreference == null) {
			generatePartnerPreferences();
		}
		return subspeciesPreference;
	}
	
	public RaceStage getRaceStagePreference() {
		if(raceStagePreference == null) {
			generatePartnerPreferences();
		}
		return raceStagePreference;
	}
	
	public boolean isAffectionHighEnoughToInviteHome() {
		if(this.isRelatedTo(Main.game.getPlayer())) {
			return this.getAffection(Main.game.getPlayer())>=AffectionLevel.NEGATIVE_TWO_DISLIKE.getMinimumValue();
		} else {
			return this.getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue();
		}
	}

	public boolean isAllowingPlayerToManageInventory() {
		return Main.game.getPlayer().getFriendlyOccupants().contains(this.getId()) || (this.isSlave() && this.getOwner().isPlayer());
	}
	
	public AbstractItemEffectType getItemEnchantmentEffect(AbstractItemType itemType, BodyPartInterface bodyPart) {
		if (Util.newArrayListOfValues(Race.HUMAN, Race.NONE).contains(bodyPart.getType().getRace())) {
			return ItemEffectType.getRacialEffectType(Race.HUMAN);
		}
		return itemType.getEnchantmentEffect();
	}

	public TransformativePotion generateTransformativePotion(GameCharacter target) {
		List<PossibleItemEffect> possibleEffects = new ArrayList<>();
		AbstractItemType itemType = ItemType.getItemTypeFromId("innoxia_race_human_bread_roll");
		int numberOfTransformations = (2+Util.random.nextInt(4)) * (target.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)?2:1);
		boolean cannotTransformPreference = getSubspeciesPreference().getRace()==Race.DEMON || getSubspeciesPreference().getRace()==Race.ANGEL;
		
		if(this.getSubspeciesPreference()==Subspecies.SLIME && target.getBodyMaterial()!=BodyMaterial.SLIME) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(ItemEffectType.RACE_SLIME_TF_UTIL_EFFECT, TFModifier.NONE, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
				"你会爱上变成史莱姆的感觉的！"));
			return new TransformativePotion(itemType, possibleEffects);
		}
		
		if(Main.getProperties().getForcedTFPreference() != FurryPreference.HUMAN) {
			AbstractSubspecies transformationItemSubspecies = cannotTransformPreference
																	?target.getSubspecies()
																	:getSubspeciesPreference();
			
			itemType = transformationItemSubspecies.getTransformativeItem(this);
			if(itemType==null || transformationItemSubspecies==Subspecies.SLIME) {
				itemType = ItemType.getItemTypeFromId("innoxia_race_human_bread_roll");
			}
		}
		
		AbstractItemType genitalsItemType = itemType;
		boolean skipGenitalsTF = false;
		
		Body body;
		Util.random = new Random((this.getId()).hashCode()); // Set random with seed of this character's id hash so that it's consistent across multiple calls (as some methods inside generateBody() use random).
		if(cannotTransformPreference) { // As demons and angels cannot be created via transformation, use the target's current body as Subspecies preference (so that gender changes use that Subspecies' body parts) 
			body = Main.game.getCharacterUtils().generateBody(null, this.getGenderPreference(), target.getSubspecies(), target.getRaceStage());
			
		} else {
			RaceStage targetedRaceStage = this.getRaceStagePreference();
			// Make sure that the generated body preference is respecting the player's content settings.
			// Otherwise, NPC's potion will attempt to apply changes as though the end body would be a greater stage than allowed.
				// e.g. Removing hair thinking that the player will end up greater, while preferences were set to less than greater.
			switch(Main.getProperties().getForcedTFPreference()) {
				case HUMAN:
					targetedRaceStage = RaceStage.HUMAN;
					break;
				case MINIMUM:
					if(targetedRaceStage!=RaceStage.HUMAN) {
						targetedRaceStage = RaceStage.PARTIAL_FULL;
					}
					break;
				case REDUCED:
					if(targetedRaceStage!=RaceStage.HUMAN && targetedRaceStage!=RaceStage.PARTIAL_FULL) {
						targetedRaceStage = RaceStage.LESSER;
					}
					break;
				case NORMAL:
				case MAXIMUM:
					break;
			}
			
			body = Main.game.getCharacterUtils().generateBody(null, this.getGenderPreference(), this.getSubspeciesPreference(), targetedRaceStage);
		}
		Util.random = new Random();
		
		if(body.getBodyMaterial()==BodyMaterial.SLIME) { // For slime body preferences, allow resetting of item type to the slime's underlying race
			itemType = body.getFleshSubspecies().getTransformativeItem(this);
			genitalsItemType = itemType;
		}
		
		boolean vaginaSet = target.getVaginaType()==body.getVagina().getType();
		boolean penisSet = target.getPenisType()==body.getPenis().getType();
		boolean humanGenitals = false;
		boolean applyingCrotchBoobTF = Main.game.isUdderContentEnabled();
		
		if(Main.getProperties().getForcedTFPreference()==FurryPreference.HUMAN || Main.getProperties().getForcedTFPreference()==FurryPreference.MINIMUM) {
			humanGenitals = true;
			genitalsItemType = ItemType.getItemTypeFromId("innoxia_race_human_bread_roll");
			
			vaginaSet = body.getVagina().getType()!=VaginaType.NONE == target.hasVagina();
			penisSet = body.getPenis().getType()!=PenisType.NONE == target.hasPenisIgnoreDildo();
			
			skipGenitalsTF = vaginaSet && penisSet;
		}
		
		// Order of transformation preferences are: Sexual organs -> minor parts -> Legs & arms -> Face & skin 
		
		if(!skipGenitalsTF) {
			// Sexual transformations:
			if(!vaginaSet) {
				if(body.getVagina().getType()==VaginaType.NONE) {
					if(!target.isHasAnyPregnancyEffects()) { // Vagina cannot be transformed if pregnant, so skip this
						possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(genitalsItemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1),
							"向你的小缝说拜拜；你不再需要这东西了！"));
					}
					
				} else {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(getItemEnchantmentEffect(genitalsItemType, body.getVagina()), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
						"来给你个漂亮的"+(humanGenitals?"人类":body.getVagina().getType().getTransformName())+"小穴吧！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				}
			}
			
			if(!penisSet) {
				if(body.getPenis().getType()==PenisType.NONE) {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(genitalsItemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1),
						"是时候跟你的鸡巴说再见了！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
					
				} else {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(getItemEnchantmentEffect(genitalsItemType, body.getPenis()), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
						"来给你个漂亮的"+(humanGenitals?"人类":body.getPenis().getType().getTransformName())+"鸡巴吧！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				}
			}
		}
		
		// All minor part transformations:
		if(Main.getProperties().getForcedTFPreference()!=FurryPreference.HUMAN && !cannotTransformPreference) {
			if(possibleEffects.isEmpty() || Math.random()>0.33f) {
				if(target.getAntennaType() != body.getAntenna().getType()) {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(getItemEnchantmentEffect(itemType, body.getAntenna()), TFModifier.TF_ANTENNA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
						body.getAntenna().getType()==AntennaType.NONE
							?UtilText.parse(target, "我不想再让你长着那些[npc.antennae]了！")
							:"该长点触须玩玩了！"));//TODO
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				}
				if(Main.getProperties().getForcedTFPreference() != FurryPreference.MINIMUM) {
					if(target.getAssType() != body.getAss().getType()) {
						possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(getItemEnchantmentEffect(itemType, body.getAss()), TFModifier.TF_ASS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
							"看我变了你的屁股！"));
						if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
					}
					if(target.getBreastType() != body.getBreast().getType()) {
						possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(getItemEnchantmentEffect(itemType, body.getBreast()), TFModifier.TF_BREASTS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
							"你的胸也逃不了！"));
						if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
					}
					if(applyingCrotchBoobTF && target.getBreastCrotchType() != body.getBreastCrotch().getType() && body.getBreastCrotch().getType()!=BreastType.NONE) {
						possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(getItemEnchantmentEffect(itemType, body.getBreastCrotch()), TFModifier.TF_BREASTS_CROTCH, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
							"你还得多长点胯乳！"));
						if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
					}
				}
				if(target.getEarType() != body.getEar().getType()) {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(getItemEnchantmentEffect(itemType, body.getEar()), TFModifier.TF_EARS, body.getEar().getType().getTFModifier(), TFPotency.MINOR_BOOST, 1),
						"你的耳朵还得改造一下！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				}
				if(target.getEyeType() != body.getEye().getType()) {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(getItemEnchantmentEffect(itemType, body.getEye()), TFModifier.TF_EYES, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
						"是时候转化一下眼睛了！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				}
				if(target.getHairType() != body.getHair().getType()) {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(getItemEnchantmentEffect(itemType, body.getHair()), TFModifier.TF_HAIR, body.getHair().getType().getTFModifier(), TFPotency.MINOR_BOOST, 1),
						"可能有点疼哦！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				}
				if(target.getHornType() != body.getHorn().getType()) {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(getItemEnchantmentEffect(itemType, body.getHorn()), TFModifier.TF_HORNS, body.getHorn().getType().getTFModifier(), TFPotency.MINOR_BOOST, 1),
						body.getHorn().getType()==HornType.NONE
							?"来把这些角除去吧……"
							:"准备好长角了吗？"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				} else if (target.getHornType() != HornType.NONE) {
					if(target.getHornLengthValue() + 3 < body.getHorn().getHornLengthValue()) {
						possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HORNS, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1),
							"让你的角再长点吧！"));
						if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
					} else if(target.getHornLengthValue() - 3 > body.getHorn().getHornLengthValue()) {
						possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HORNS, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1),
							"让你的角再短点吧！"));
						if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
					}
				}
				if(target.getTailType() != body.getTail().getType()
						&& target.getTailType()!=TailType.FOX_MORPH_MAGIC
						&& body.getTail().getType()!=TailType.FOX_MORPH_MAGIC) {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(getItemEnchantmentEffect(itemType, body.getTail()), TFModifier.TF_TAIL, body.getTail().getType().getTFModifier(), TFPotency.MINOR_BOOST, 1), 
						body.getTail().getType()==TailType.NONE
							?"你那尾巴太碍事了！"
							:"是时候长条新尾巴了！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				}
				if(target.getWingType() != body.getWing().getType()) {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(getItemEnchantmentEffect(itemType, body.getWing()), TFModifier.TF_WINGS, body.getWing().getType().getTFModifier(), TFPotency.MINOR_BOOST, 1),
						body.getWing().getType()==WingType.NONE
							?"来把你的翅膀除去吧……"
							:"准备好长翅膀了吗？"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				}
			}
			
			// Leg & Arm transformations:
			if(Main.getProperties().getForcedTFPreference() != FurryPreference.MINIMUM) {
				if(possibleEffects.isEmpty()) {
					if(target.getArmType() != body.getArm().getType()) {
						possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(getItemEnchantmentEffect(itemType, body.getArm()), TFModifier.TF_ARMS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
							"你的胳膊得变变样！"));
					}
					// TODO: Add separate chunks for LegConfiguration and LegType if more races have multiple leg types
					if(target.getLegType() != body.getLeg().getType()) {
						possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(getItemEnchantmentEffect(itemType, body.getLeg()), TFModifier.TF_LEGS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
							"你的腿也要变一下！"));
					}
					if(target.getLegConfiguration() != body.getLeg().getLegConfiguration()) {
						possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(getItemEnchantmentEffect(itemType, body.getLeg()), TFModifier.TF_LEGS, body.getLeg().getLegConfiguration().getTFModifier(), TFPotency.MINOR_BOOST, 1),
							"我要让你长出"+UtilText.generateSingularDeterminer(body.getLeg().getLegConfiguration().getName())+" "+body.getLeg().getLegConfiguration().getName()+"的身体！"));
					}
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); } // Apply arms & legs at the same time
				}
			}
			// Face & Skin transformations:
			if(Main.getProperties().getForcedTFPreference() == FurryPreference.NORMAL || Main.getProperties().getForcedTFPreference() == FurryPreference.MAXIMUM) {
				if(possibleEffects.isEmpty()) {
					if(target.getTorsoType() != body.getTorso().getType()) {
						possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(getItemEnchantmentEffect(itemType, body.getTorso()), TFModifier.TF_SKIN, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
							"这样一定不错！"));
					}
					if(target.getFaceType() != body.getFace().getType()) {
						possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(getItemEnchantmentEffect(itemType, body.getFace()), TFModifier.TF_FACE, body.getFace().getType().getTFModifier(), TFPotency.MINOR_BOOST, 1),
							"我等不及想看看你的样子了！"));
					}
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); } // Apply face & skin at the same time
				}
			}
		}
		
		
		// Other transformations:
		
		//----- Minor body part variation based on fetishes ------
		
		//Ass:
		if(hasFetish(Fetish.FETISH_ANAL_GIVING)) {
			if(this.getAttributeValue(Attribute.MAJOR_CORRUPTION) >= CorruptionLevel.THREE_DIRTY.getMinimumValue()) {
				body.getAss().getAnus().getOrificeAnus().addOrificeModifier(null, OrificeModifier.RIBBED);
				body.getAss().getAnus().getOrificeAnus().addOrificeModifier(null, OrificeModifier.MUSCLE_CONTROL);
				body.getAss().getAnus().getOrificeAnus().addOrificeModifier(null, OrificeModifier.PUFFY);
			}
			if(this.getAttributeValue(Attribute.MAJOR_CORRUPTION) >= CorruptionLevel.FOUR_LUSTFUL.getMinimumValue()) {
				body.getAss().getAnus().getOrificeAnus().addOrificeModifier(null, OrificeModifier.TENTACLED);
			}
			
			body.getAss().setAssSize(null, AssSize.FIVE_HUGE.getValue());
			body.getAss().setHipSize(null, HipSize.FIVE_VERY_WIDE.getValue());
		}
		
		//Breasts:
		if(hasFetish(Fetish.FETISH_BREASTS_OTHERS) && this.getGenderPreference().getGenderName().isHasBreasts()) {
			body.getBreast().setSize(null, (int) (body.getBreast().getRawSizeValue()*1.5f));
		}
		
		// Removing crotch-boobs:
		if(applyingCrotchBoobTF && target.getBreastCrotchType()!=body.getBreastCrotch().getType() && body.getBreastCrotch().getType()==BreastType.NONE) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(getItemEnchantmentEffect(itemType, body.getBreastCrotch()), TFModifier.TF_BREASTS_CROTCH, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1),
				"这些恶心的胯乳赶紧消失吧！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
		}
		
		// Face:
		if(hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
			body.getFace().getMouth().getOrificeMouth().addOrificeModifier(null, OrificeModifier.PUFFY);
			body.getFace().getMouth().setLipSize(null, LipSize.FOUR_HUGE.getValue());
			
			if(this.getAttributeValue(Attribute.MAJOR_CORRUPTION) >= CorruptionLevel.THREE_DIRTY.getMinimumValue()) {
				body.getFace().getMouth().getOrificeMouth().addOrificeModifier(null, OrificeModifier.RIBBED);
				body.getFace().getMouth().getOrificeMouth().addOrificeModifier(null, OrificeModifier.MUSCLE_CONTROL);
			}
			if(this.getAttributeValue(Attribute.MAJOR_CORRUPTION) >= CorruptionLevel.FOUR_LUSTFUL.getMinimumValue()) {
				body.getFace().getMouth().getOrificeMouth().addOrificeModifier(null, OrificeModifier.TENTACLED);
			}
		}
		
		// Hair:
//		if(this.getGenderPreference().isFeminine()) {
//			body.getHair().setLength(null, body.getHair().getRawLengthValue());
//			
//		} else {
//			body.getHair().setLength(null, body.getHair().getRawLengthValue());
//		}
		
		// Penis:
		if(body.getPenis().getType()!=PenisType.NONE) {
			if(this.getGenderPreference()==Gender.F_P_TRAP) {
				body.getPenis().setPenisLength(null, PenisLength.ONE_TINY.getMedianValue());
				body.getPenis().getTesticle().setTesticleSize(null, TesticleSize.ONE_TINY.getValue());
				body.getPenis().getTesticle().setCumStorage(null, CumProduction.ONE_TRICKLE.getMedianValue());
			}
		}
		
		// Vagina:
		if(body.getVagina().getType()!=VaginaType.NONE) {
			if(this.getAttributeValue(Attribute.MAJOR_CORRUPTION) >= CorruptionLevel.THREE_DIRTY.getMinimumValue()) {
				body.getVagina().getOrificeVagina().addOrificeModifier(null, OrificeModifier.RIBBED);
				body.getVagina().getOrificeVagina().addOrificeModifier(null, OrificeModifier.MUSCLE_CONTROL);
			}
			if(this.getAttributeValue(Attribute.MAJOR_CORRUPTION) >= CorruptionLevel.FOUR_LUSTFUL.getMinimumValue()) {
				body.getVagina().getOrificeVagina().addOrificeModifier(null, OrificeModifier.TENTACLED);
			}
		}
		
		//-----------
		
		
		//--- CORE ---//
		
		// Height:
		if(target.getHeightValue() + 10 < body.getHeightValue()) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
				"来让你长高点！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			
		} else if(target.getHeightValue() - 10 > body.getHeightValue()) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
				"来让你变矮点！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
		}
		
		// Muscle:
		if(target.getMuscleValue() > body.getMuscle()
				&& target.getMuscle() != Muscle.valueOf(body.getMuscle())) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_DRAIN, 1),
				"你肌肉太多了！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			
		} else if(target.getMuscleValue() < body.getMuscle()
				&& target.getMuscle() != Muscle.valueOf(body.getMuscle())) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1),
				"你还得多长点肌肉！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
		}

		// Body size:
		if(target.getBodySizeValue() > body.getBodySize()
				&& target.getBodySize() != BodySize.valueOf(body.getBodySize())) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_DRAIN, 1),
				"你得变瘦点！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			
		} else if(target.getBodySizeValue() < body.getBodySize()
				&& target.getBodySize() != BodySize.valueOf(body.getBodySize())) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_BOOST, 1),
				"你太瘦了，我不喜欢！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
		}
		
		// Femininity:
		if(target.getFemininityValue() < body.getFemininity()
				&& Femininity.valueOf(target.getFemininityValue()) != Femininity.valueOf(body.getFemininity())) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 1),
				"我得让你更有女人味！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			
		} else if(target.getFemininityValue() > body.getFemininity()
				&& Femininity.valueOf(target.getFemininityValue()) != Femininity.valueOf(body.getFemininity())
				&& !Femininity.valueOf(body.getFemininity()).isFeminine()) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_DRAIN, 1),
				"我得让你更像个男人！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
		}
		

		//--- BREASTS ---//
		// Breast size:
		if(target.getBreastSize().getMeasurement() + 3 <= body.getBreast().getSize().getMeasurement()) {
			possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"你的胸还太小了！"));
				if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			
		} else if(target.getBreastSize().getMeasurement() + 2 <= body.getBreast().getSize().getMeasurement()) {
			possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1),
					"你的胸还不够大！"));
				if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				
		} else if(target.getBreastSize().getMeasurement() + 1 <= body.getBreast().getSize().getMeasurement()) {
			possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_BOOST, 1),
					"你的胸还得稍大一点！"));
				if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				
		} else if(target.getBreastSize().getMeasurement()>0) {
			if(target.getBreastSize().getMeasurement() - 3 >= body.getBreast().getSize().getMeasurement()) {
				possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
						"你的胸太大了！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
					
			} else if(target.getBreastSize().getMeasurement() - 2 >= body.getBreast().getSize().getMeasurement()) {
				possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1),
						"你的胸太大了！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
					
			} else if(target.getBreastSize().getMeasurement() - 1 >= body.getBreast().getSize().getMeasurement()) {
				possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_DRAIN, 1),
						"你的胸部有点太大了！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			}
		}
		
		//--- CROTCH-BOOBS---//
		
		if(applyingCrotchBoobTF && body.getBreastCrotch().getType()!=BreastType.NONE) {
			if(target.getBreastCrotchSize().getMeasurement() + 3 <= body.getBreastCrotch().getSize().getMeasurement()) {
				possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS_CROTCH, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
						"你的胯乳还太小了！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				
			} else if(target.getBreastCrotchSize().getMeasurement() + 2 <= body.getBreastCrotch().getSize().getMeasurement()) {
				possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS_CROTCH, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1),
						"你的胯乳不够大！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
					
			} else if(target.getBreastCrotchSize().getMeasurement() + 1 <= body.getBreastCrotch().getSize().getMeasurement()) {
				possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS_CROTCH, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_BOOST, 1),
						"你的胯乳还得稍大一点！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
					
			} else if(target.getBreastCrotchSize().getMeasurement()>0) {
				if(target.getBreastCrotchSize().getMeasurement() - 3 >= body.getBreastCrotch().getSize().getMeasurement()) {
					possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS_CROTCH, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
							"你的胯乳太大了！"));
						if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
						
				} else if(target.getBreastCrotchSize().getMeasurement() - 2 >= body.getBreastCrotch().getSize().getMeasurement()) {
					possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS_CROTCH, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1),
							"你的胯乳太大了！"));
						if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
						
				} else if(target.getBreastCrotchSize().getMeasurement() - 1 >= body.getBreastCrotch().getSize().getMeasurement()) {
					possibleEffects.add(new PossibleItemEffect(
							new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS_CROTCH, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_DRAIN, 1),
							"你的胯乳有点太大了！"));
						if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				}
			}
		}

		//--- ASS ---//
		
		// Ass size:
		if(target.getAssSize().getValue() + 1 < body.getAss().getAssSize().getValue()) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1),
				"你的屁股还得再大点！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			
		} else if(target.getAssSize().getValue() - 1 > body.getAss().getAssSize().getValue()) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1),
				"你的屁股太大了！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
		}
		
		// Capacity:
		if(target.getAssRawCapacityValue()+10 < body.getAss().getAnus().getOrificeAnus().getRawCapacityValue()) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_CAPACITY, TFPotency.BOOST, 1),
				"你的屁股太紧了，我不喜欢！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			
		} else if(target.getAssRawCapacityValue()-20 > body.getAss().getAnus().getOrificeAnus().getRawCapacityValue()) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_CAPACITY, TFPotency.MAJOR_DRAIN, 1),
				"你后面也太松了！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
		}
		
		// Wetness:
		if(target.getAssWetness().getValue() < body.getAss().getAnus().getOrificeAnus().getWetness(null).getValue()) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_WETNESS, TFPotency.MINOR_BOOST, 1),
				"你的屁股太干了！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
		}
		
		// Hip size:
		if(target.getHipSize().getValue() + 1 < body.getAss().getHipSize().getValue()) {
			possibleEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.BOOST, 1),
				"臀部应该再宽一点！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			
		} else if(target.getHipSize().getValue() - 1 > body.getAss().getHipSize().getValue()) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.DRAIN, 1),
				"你的臀部太宽了！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
		}


		//--- HAIR ---//
		
		// Hair length:
		// Same as with breast size, since target hair size might be 0cm (= no hair) and steps reduced to 5cm from 15cm.
		boolean doubleApplication = Math.abs(target.getHairRawLengthValue() - body.getHair().getRawLengthValue()) > 20;
		for(int i=0; i<(doubleApplication?2:1); i++) {
			boolean majorChange = Math.abs(target.getHairRawLengthValue() - body.getHair().getRawLengthValue()) > (i==0&&doubleApplication?30:15);
			if(target.getHairRawLengthValue() + 6 < body.getHair().getRawLengthValue()) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, majorChange?TFPotency.MAJOR_BOOST:TFPotency.BOOST, 1),
					i==0
						?("你的[pc.hair(true)]怎么这么短！")
						:"让你的[pc.hair(true)]更长些吧！"));
				
			} else if(target.getHairRawLengthValue() > body.getHair().getRawLengthValue()) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, majorChange?TFPotency.MAJOR_DRAIN:TFPotency.DRAIN, 1),
					i==0
						?("你的[pc.hair(true)]也太长了！")
						:"让你的[pc.hair(true)]更短些吧！"));
			}
		}
		if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
		
		//--- FACE ---//
		
		// Lip size:
		if(target.getLipSize().getValue() + 1 < body.getFace().getMouth().getLipSize().getValue()) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1),
				"你的舌头[pc.lips]太小了！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			
		} else if(target.getLipSize().getValue() - 1 > body.getFace().getMouth().getLipSize().getValue()) {
			possibleEffects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1),
				"你[pc.lips]太大了！"));
			if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
		}
		
		
		//--- PENIS ---//
		
		if(target.getPenisType()!=PenisType.NONE && body.getPenis().getType()!=PenisType.NONE) {
			// Cum production:
			if(target.getPenisRawCumStorageValue() < body.getPenis().getTesticle().getRawCumStorageValue()) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CUM, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"嗯嗯嗯！你会给我制造很多精液的！"));
				if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			}
			// Size:
			if(target.getPenisRawSizeValue() < body.getPenis().getRawLengthValue()) {
				if(body.getPenis().getRawLengthValue() - target.getPenisRawSizeValue() > 5) {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1),
						"你的鸡巴还得大不少才行！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				} else {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_BOOST, 1),
						"你的下面还得再大点！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				}
				
			} else if(target.getPenisRawSizeValue() > body.getPenis().getRawLengthValue()) {
				if(target.getPenisRawSizeValue() - body.getPenis().getRawLengthValue() > 5) {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1),
						"你的鸡巴得多缩小些！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				} else {
					possibleEffects.add(new PossibleItemEffect(
						new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_DRAIN, 1),
						"你的下面小点更好！"));
					if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				}
			}
			// Penis girth:
			if(target.getPenisRawGirthValue() < body.getPenis().getRawGirthValue()) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MINOR_BOOST, 1),
					"我想让你的鸡巴再多粗壮些！"));
				if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				
			} else if(target.getPenisRawGirthValue() > body.getPenis().getRawGirthValue()) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MINOR_DRAIN, 1),
					"你的屌也太粗了！"));
				if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			}
			// Ball size:
			if(target.getTesticleSize().getValue() < body.getPenis().getTesticle().getTesticleSize().getValue()) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MINOR_BOOST, 1),
					"你的蛋蛋还不够大！"));
				if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				
			} else if(target.getTesticleSize().getValue() > body.getPenis().getTesticle().getTesticleSize().getValue()) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MINOR_DRAIN, 1), "你的蛋蛋可不能这么大！"));
				if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			}
		}

		
		//--- VAGINA ---//
		
		if(target.getVaginaType()!=VaginaType.NONE && body.getVagina().getType()!=VaginaType.NONE) {
			// Capacity:
			if(target.getVaginaRawCapacityValue()+10 < body.getVagina().getOrificeVagina().getRawCapacityValue()) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_CAPACITY, TFPotency.BOOST, 1),
					"你的小穴太紧了，我不喜欢！"));
				if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
				
			} else if(target.getVaginaRawCapacityValue()-20 > body.getVagina().getOrificeVagina().getRawCapacityValue()) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_CAPACITY, TFPotency.MAJOR_DRAIN, 1),
					"你小穴也太松了！"));
				if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			}
			// Wetness:
			if(target.getVaginaWetness().getValue() < body.getVagina().getOrificeVagina().getWetness(null).getValue()) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MINOR_BOOST, 1),
					"你的小穴还不够湿！"));
				if(possibleEffects.size()>=numberOfTransformations) { return new TransformativePotion(itemType, possibleEffects, body); }
			}
		}
		
		if(possibleEffects.isEmpty()) {
			return null;
		}
		
		return new TransformativePotion(itemType, possibleEffects, body);
	}
	
	public String applyPotion(AbstractPotion potion, GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		potion.getEffects().forEach((e) -> {
			sb.append(UtilText.parse(this,
				(!this.isMute() && e.getMessage()!=null && !e.getMessage().isEmpty()
					?"<p>[npc.speech("+e.getMessage()+")]</p>"
					:"")
				+ e.getEffect().applyEffect(this, target, 1)));
		});
		return sb.toString();
	}
	
	public void generatePartnerPreferences() {
		
		// Preferred gender:
		
		Gender preferredGender = Gender.N_P_V_B_HERMAPHRODITE;
		Map<Gender, Integer> desiredGenders = new HashMap<>();
		
		switch(this.getSexualOrientation()) {
			case AMBIPHILIC:
				if(this.isFeminine() && 
						// ambiphilic characters respect .getForcedTFTendency() setting by not entering this case if the
						// player has requested a feminine tendency; admittedly, this specific logic does slightly skew 
						// towards pushing the player feminine in neutral scenarios, but only to a small degree, so more
						// complex but fair logic doesn't feel too required
						Main.getProperties().getForcedTFTendency() != ForcedTFTendency.FEMININE &&
						Main.getProperties().getForcedTFTendency() != ForcedTFTendency.FEMININE_HEAVY) {
					desiredGenders.put(Gender.M_P_MALE, 14);
					// maybe it would be appropriate to raise these chances for impregnators?
					desiredGenders.put(Gender.M_P_V_HERMAPHRODITE, 2);
					desiredGenders.put(Gender.M_V_CUNTBOY, 2);
					desiredGenders.put(Gender.F_P_TRAP, 2);
				} else {
					// basic chances of cis-female preference
					desiredGenders.put(Gender.F_V_B_FEMALE, 14);
					
					// increase chances of growing a penis if fetishes increase desirability 
					if(this.hasVagina() && (this.hasFetish(Fetish.FETISH_PREGNANCY))) {
						desiredGenders.put(Gender.F_P_V_B_FUTANARI, 4);
						desiredGenders.put(Gender.F_P_B_SHEMALE, 4);
						desiredGenders.put(Gender.F_P_TRAP, 4);
						
					} else {
						desiredGenders.put(Gender.F_P_V_B_FUTANARI, 2);
						desiredGenders.put(Gender.F_P_B_SHEMALE, 2);
						desiredGenders.put(Gender.F_P_TRAP, 2);
					};
					
					// heavy masculine .getForcedTFTendency() option adds a bit of a chance for masculine preferences here
					if (Main.getProperties().getForcedTFTendency() == ForcedTFTendency.MASCULINE_HEAVY) {
						desiredGenders.put(Gender.M_P_V_HERMAPHRODITE, 4);
						desiredGenders.put(Gender.M_V_CUNTBOY, 4);
						desiredGenders.put(Gender.F_P_TRAP, 4);
						desiredGenders.put(Gender.M_V_B_BUTCH, 4);
					}
				}
				break;
			case ANDROPHILIC:
				// Heavy feminine .getForcedTFTendency() causes androphiles to lose the majority of masculine options
				if (Main.getProperties().getForcedTFTendency() != ForcedTFTendency.FEMININE_HEAVY) {
					desiredGenders.put(Gender.M_P_MALE, 14);
				}
				
				// base chance options regardless of .getForcedTFTendency() option
				desiredGenders.put(Gender.M_P_V_HERMAPHRODITE, 2);
				desiredGenders.put(Gender.M_V_CUNTBOY, 2);
				
				// both feminine .getForcedTFTendency() options add decent chances to get some feminine options despite tastes
				if(Main.getProperties().getForcedTFTendency() == ForcedTFTendency.FEMININE || 
				   Main.getProperties().getForcedTFTendency() == ForcedTFTendency.FEMININE_HEAVY) {
					desiredGenders.put(Gender.F_P_V_B_FUTANARI, 2);
					desiredGenders.put(Gender.F_P_B_SHEMALE, 2);
					desiredGenders.put(Gender.F_P_TRAP, 2);
					desiredGenders.put(Gender.M_V_B_BUTCH, 2);
				}
				break;
			case GYNEPHILIC:
				// increase chances of growing a penis if fetishes increase desirability; also, this is a reasonable
				// base level of feminine options even if .getForcedTFTendency() is heavy male
				if(this.hasVagina() && (this.hasFetish(Fetish.FETISH_PREGNANCY))) {
					desiredGenders.put(Gender.F_P_V_B_FUTANARI, 2);
					desiredGenders.put(Gender.F_P_B_SHEMALE, 2);
					desiredGenders.put(Gender.F_P_TRAP, 2);
				// much lower base chance of pure female preference for heavy masculine .getForcedTFTendency()
				} else if (Main.getProperties().getForcedTFTendency() == ForcedTFTendency.MASCULINE_HEAVY) {
					desiredGenders.put(Gender.F_V_B_FEMALE, 4);
				}
				else {
					desiredGenders.put(Gender.F_V_B_FEMALE, 14);
				}
				
				// both masculine .getForcedTFTendency() options add decent chances to get some masculine options despite tastes
				if(Main.getProperties().getForcedTFTendency() == ForcedTFTendency.MASCULINE || 
				   Main.getProperties().getForcedTFTendency() == ForcedTFTendency.MASCULINE_HEAVY) {
					desiredGenders.put(Gender.M_P_V_HERMAPHRODITE, 2);
					desiredGenders.put(Gender.M_V_CUNTBOY, 2);
					desiredGenders.put(Gender.M_V_B_BUTCH, 2);
					desiredGenders.put(Gender.F_P_TRAP, 2);
				}
				break;
		}
		
		int total = 0;
		for(Entry<Gender, Integer> entry : desiredGenders.entrySet()) {
			total+=entry.getValue();
		}
		int count = Util.random.nextInt(total)+1;
		total = 0;
		for(Entry<Gender, Integer> entry : desiredGenders.entrySet()) {
			if(total < count && total+entry.getValue()>= count) {
				preferredGender = entry.getKey();
				break;
			}
			total+=entry.getValue();
		}
		
		this.genderPreference = preferredGender;
		
		// Leaving this present but commented out so it can be easily re-enabled by anyone wanting to tweak or check
		// the results of gender selection and the .getForcedTFTendency() setting
//		System.out.println("PREFERRED GENDER");
//		System.out.println(preferredGender);
//		System.out.println(desiredGenders);
		
		// Preferred race:
		
		AbstractSubspecies species = getSubspecies();
		RaceStage stage = getRaceStage();
		
		if(Main.getProperties().getForcedTFPreference()==FurryPreference.HUMAN) {
			species = Subspecies.HUMAN;
			stage = RaceStage.HUMAN;
			
		} else {
			// Chance for predator races to prefer prey races:
			if(getRace()==Race.CAT_MORPH && Math.random()>0.8f) {
				species = Subspecies.HARPY;
			}
			if((getRace()==Race.WOLF_MORPH || getRace()==Race.DOG_MORPH) && Math.random()>0.8f) {
				List<AbstractSubspecies> availableRaces = new ArrayList<>();
				availableRaces.add(Subspecies.CAT_MORPH);
				availableRaces.add(Subspecies.HARPY);
				availableRaces.add(Subspecies.COW_MORPH);
				availableRaces.add(Subspecies.SQUIRREL_MORPH);
				species = availableRaces.get(Util.random.nextInt(availableRaces.size()));
			}
			
			// Chance for race to be random:
			if(Math.random() <= Main.getProperties().getRandomRacePercentage()) {
				List<AbstractSubspecies> availableRaces = new ArrayList<>();
				availableRaces.add(Subspecies.CAT_MORPH);
				availableRaces.add(Subspecies.DOG_MORPH);
				availableRaces.add(Subspecies.HARPY);
				availableRaces.add(Subspecies.HORSE_MORPH);
				availableRaces.add(Subspecies.HUMAN);
				availableRaces.add(Subspecies.SQUIRREL_MORPH);
				availableRaces.add(Subspecies.COW_MORPH);
				availableRaces.add(Subspecies.WOLF_MORPH);
				species = availableRaces.get(Util.random.nextInt(availableRaces.size()));
			}
			
			// Preferred race stage:
			if(preferredGender.isFeminine()) {
				switch(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(species)) {
					case HUMAN:
						stage = RaceStage.HUMAN;
						break;
					case MAXIMUM:
						stage = RaceStage.GREATER;
						break;
					case MINIMUM:
						stage = RaceStage.PARTIAL_FULL;
						break;
					case NORMAL:
						stage = RaceStage.GREATER;
						break;
					case REDUCED:
						stage = RaceStage.LESSER;
						break;
				}
			} else {
				switch(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(species)) {
					case HUMAN:
						stage = RaceStage.HUMAN;
						break;
					case MAXIMUM:
						stage = RaceStage.GREATER;
						break;
					case MINIMUM:
						stage = RaceStage.PARTIAL_FULL;
						break;
					case NORMAL:
						stage = RaceStage.GREATER;
						break;
					case REDUCED:
						stage = RaceStage.LESSER;
						break;
				}
			}
		}
		
		this.subspeciesPreference = species;
		this.raceStagePreference = stage;
	}
	
	public FetishPotion generateFetishPotion(GameCharacter target, Boolean pairedFetishesOnly) {
		ItemEffect selectedEffect = null; // this will be the ultimately selected effect, or null if none available
		String selectedEffectString; // this will be a flavour text string paired with the effect
		
		List<PossibleItemEffect> possibleEffects = new ArrayList<>();
		
		AbstractItemType itemType = ItemType.FETISH_UNREFINED;
		
		AbstractFetish currentTopFetish = null;
		AbstractFetish currentBottomFetish = null;
		
		TFModifier currentTopModifier = null;
		TFModifier currentBottomModifier = null;
		
		TFPotency currentTopPotency = null;
		TFPotency currentBottomPotency = null;
		TFPotency currentTopRemovePotency = null;
		TFPotency currentBottomRemovePotency = null;
		
		int baseTopChance = 5;
		int baseBottomChance = 5;
		int baseTopRemoveChance = 0;
		int baseBottomRemoveChance = 0; 
		
		int currentTopChance = 0;
		int currentBottomChance = 0;
		int currentTopRemoveChance = 0;
		int currentBottomRemoveChance = 0;
		
		int pairedFetishMultiplier = 5;  
		int matchedFetishDecrement = 8;  // heavy tendency can still allow small chance giving a matched fetish, otherwise no chance at all
		int matchedFetishRemoveIncrement = 1;  // only a modest increase in chances to matched fetish
		
		int desiredFetishIncrement = 2;  // for now, keeping it simple, only modifying add chances based on desires, one increment (or decrement) per level
		int expFetishIncrement = 1;  // for now, keeping it simple, only modifying add chances based on exp, one increment per level
		
		switch(Main.getProperties().getForcedFetishTendency()) {
			case NEUTRAL:
				baseTopChance = 5;
				baseBottomChance = 5;
				baseTopRemoveChance = 2;
				baseBottomRemoveChance = 2;
				break;
			case BOTTOM:
				baseTopChance = 1;
				baseBottomChance = 8;
				baseTopRemoveChance = 3;
				baseBottomRemoveChance = 0;
				break;
			case BOTTOM_HEAVY:
				baseTopChance = -2;
				baseBottomChance = 10;
				baseTopRemoveChance = 4;
				baseBottomRemoveChance = -1;
				break;
			case TOP:
				baseTopChance = 8;
				baseBottomChance = 1;
				baseTopRemoveChance = 0;
				baseBottomRemoveChance = 3;
				break;
			case TOP_HEAVY:
				baseTopChance = 10;
				baseBottomChance = -2;
				baseTopRemoveChance = -1;
				baseBottomRemoveChance = 4;
				break;
		}
		
		for(AbstractFetish fetish : Fetish.getAllFetishes()) {
			// Skip bottom & solo fetishes, as well as the TF & Kink giving fetishes
			// NPCs will otherwise always end up forcing them on the player
			// v0.4.10.8: Changed "!fetish.isTopFetish()" to "fetish.getOpposite()==null" to allow for more fetish applications (I think, in practice it seems to make no difference...)
			if(fetish.getOpposite()==null
					|| !fetish.getFetishesForAutomaticUnlock().isEmpty()
					|| fetish.equals(Fetish.FETISH_TRANSFORMATION_GIVING)
					|| fetish.equals(Fetish.FETISH_KINK_GIVING)) {
				continue;
			}
			currentTopFetish = fetish;
			currentBottomFetish = fetish.getOpposite();
			
			currentTopModifier = TFModifier.valueOf("TF_MOD_" + Fetish.getIdFromFetish(currentTopFetish));
			currentBottomModifier = TFModifier.valueOf("TF_MOD_" + Fetish.getIdFromFetish(currentBottomFetish));
			
			currentTopPotency = TFPotency.MINOR_BOOST;
			currentBottomPotency = TFPotency.MINOR_BOOST;
			currentTopRemovePotency = TFPotency.MINOR_DRAIN;
			currentBottomRemovePotency = TFPotency.MINOR_DRAIN;
			
			currentTopChance = baseTopChance;
			currentBottomChance = baseBottomChance;
			currentTopRemoveChance = baseTopRemoveChance;
			currentBottomRemoveChance = baseBottomRemoveChance;
			
			// Increase base add chances based on NPC's desire levels for these fetishes
			switch(this.getFetishDesire(currentBottomFetish)) {
				case THREE_LIKE:
					currentTopChance += desiredFetishIncrement;
					break;
				case FOUR_LOVE:
					currentTopChance += desiredFetishIncrement * 2;
					break;
				case ONE_DISLIKE:
					currentTopChance -= desiredFetishIncrement;
					break;
				case ZERO_HATE:
					currentTopChance = 0;
					break;
				default:
			}
			
			switch(this.getFetishDesire(currentTopFetish)) {
				case THREE_LIKE:
					currentBottomChance += desiredFetishIncrement;
					break;
				case FOUR_LOVE:
					currentBottomChance += desiredFetishIncrement * 2;
					break;
				case ONE_DISLIKE:
					currentBottomChance -= desiredFetishIncrement;
					break;
				case ZERO_HATE:
					currentBottomChance = 0;
					break;
				default:
			}
			
			// Increase base add chances based on NPC's experience levels for these fetishes
			switch(this.getFetishLevel(currentBottomFetish)) {
				case ONE_AMATEUR:
					currentTopChance += expFetishIncrement;
					break;
				case TWO_EXPERIENCED:
					currentTopChance += expFetishIncrement * 2;
					break;
					
				case THREE_EXPERT:
					currentTopChance += expFetishIncrement * 3;
					break;
					
				case FOUR_MASTERFUL:
					currentTopChance += expFetishIncrement * 4;
					break;
					
				default:
			}
			
			switch(this.getFetishLevel(currentTopFetish)) {
				case ONE_AMATEUR:
					currentBottomChance += expFetishIncrement;
					break;
				case TWO_EXPERIENCED:
					currentBottomChance += expFetishIncrement * 2;
					break;
				
				case THREE_EXPERT:
					currentBottomChance += expFetishIncrement * 3;
					break;
				
				case FOUR_MASTERFUL:
					currentBottomChance += expFetishIncrement * 4;
					break;
					
				default:
			}
			
			// set chances if NPC has top fetish
			if(this.hasFetish(currentTopFetish)) {
				currentBottomChance *= pairedFetishMultiplier;
				currentTopChance -= matchedFetishDecrement;
				currentBottomRemoveChance = 0;
				if(!pairedFetishesOnly) {
					currentTopRemoveChance += matchedFetishRemoveIncrement;
				}
				
			} else if(pairedFetishesOnly) {
				currentBottomChance = 0;
				// in paired only mode, we're only adding fetishes
				currentTopRemoveChance = 0;
				currentBottomRemoveChance = 0;
			}
			
			// set chances if NPC has bottom fetish
			if(this.hasFetish(currentBottomFetish)) {
				currentTopChance *= pairedFetishMultiplier;
				currentBottomChance -= matchedFetishDecrement;
				currentTopRemoveChance = 0;
				if(!pairedFetishesOnly) {
					currentBottomRemoveChance += matchedFetishRemoveIncrement;
				}
				
			} else if(pairedFetishesOnly) {
				currentTopChance = 0;
				// in paired only mode, we're only adding fetishes
				currentTopRemoveChance = 0;
				currentBottomRemoveChance = 0;
			}
			
			// if player has positive bottom fetish desire, adjust potency level to fully add fetish, not just desire
			if(target.getFetishDesire(currentBottomFetish) == FetishDesire.THREE_LIKE ||
					target.getFetishDesire(currentBottomFetish) == FetishDesire.FOUR_LOVE) {
				currentBottomPotency = TFPotency.BOOST;
			} 
			else if(target.getFetishDesire(currentBottomFetish) == FetishDesire.TWO_NEUTRAL) {
				int rand = Util.random.nextInt(100);
				
				// if the player is neutral, but the NPC has fetish,small chance to fully add rather than just boost desire
				if(this.hasFetish(currentTopFetish) && rand < 30) {
					currentBottomPotency = TFPotency.BOOST;
				}
			} else {
				// if they are already less than neutral, don't remove any more
				currentBottomRemoveChance = 0;
			}
			
			// prevent extraneous effects if player has bottom fetish, plus alter remove potency to drop fetish, not just desire
			if(target.hasFetish(currentBottomFetish)) {
				currentBottomChance = 0;
				currentBottomRemovePotency = TFPotency.DRAIN;
			}
			
			// if player has positive top fetish desire, adjust potency level to fully add fetish, not just desire
			if(target.getFetishDesire(currentTopFetish) == FetishDesire.THREE_LIKE ||
				target.getFetishDesire(currentTopFetish) == FetishDesire.FOUR_LOVE) {
				currentTopPotency = TFPotency.BOOST;
			} else if(target.getFetishDesire(currentTopFetish) == FetishDesire.TWO_NEUTRAL) {
				int rand = Util.random.nextInt(100);
				
				// if the player is neutral, but the NPC has paired fetish,small chance to fully add rather than just boost desire
				if(this.hasFetish(currentBottomFetish) && rand < 30) {
					currentTopPotency = TFPotency.BOOST;
				}
			} else {
				// if they are already less than neutral, don't remove any more
				currentTopRemoveChance = 0;
			}
			
			// prevent extraneous effects if player has top fetish, plus alter remove potency to drop fetish, not just desire
			if(target.hasFetish(currentTopFetish)) {
				currentTopChance = 0;
				currentTopRemovePotency = TFPotency.DRAIN;
			}
			
			// some settings and status combinations can create negative values, so let's zero those out
			if(currentTopChance < 0) { currentTopChance = 0 ;}
			if(currentBottomChance < 0) { currentBottomChance = 0 ;}
			if(currentTopRemoveChance < 0) { currentTopRemoveChance = 0 ;}
			if(currentBottomRemoveChance < 0) { currentBottomRemoveChance = 0 ;}
			
			if(currentTopChance > 0) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(),
						currentTopFetish.getAssociatedTFModifier(),
						currentTopModifier,
						currentTopPotency,
						1),
						currentTopChance));
			}
			
			if(currentTopRemoveChance > 0) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(
						itemType.getEnchantmentEffect(),
						currentTopFetish.getAssociatedTFModifier(),
						currentTopModifier,
						currentTopRemovePotency,
						1),
					currentTopRemoveChance));
			}
			
			if(currentBottomChance > 0) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(
						itemType.getEnchantmentEffect(),
						currentBottomFetish.getAssociatedTFModifier(),
						currentBottomModifier,
						currentBottomPotency,
						1),
					currentBottomChance));
			}
			
			if(currentBottomRemoveChance > 0) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(
						itemType.getEnchantmentEffect(),
						currentBottomFetish.getAssociatedTFModifier(),
						currentBottomModifier,
						currentBottomRemovePotency,
						1),
					currentBottomRemoveChance));
			}
		}
		
		// map of unpaired fetish -> boolean stating whether it wants to be shared, or hoarded
		// currently, all unpaired fetishes seem like they are something the owner would want to share,
		// but setting the second argument to false will cause the NPC to instead have an aversion to 
		// giving the player the same fetish
		Map<AbstractFetish, Boolean> unpairedFetishMap = new HashMap<>();

		unpairedFetishMap.put(Fetish.FETISH_BIMBO, true);
		unpairedFetishMap.put(Fetish.FETISH_CROSS_DRESSER, true);
		unpairedFetishMap.put(Fetish.FETISH_INCEST, true);
		unpairedFetishMap.put(Fetish.FETISH_MASTURBATION, true);
		
		for(Entry<AbstractFetish, Boolean> entry : unpairedFetishMap.entrySet()) {
			currentTopFetish = entry.getKey();
			Boolean wantsToShare = entry.getValue();
			
			currentTopModifier = TFModifier.valueOf( "TF_MOD_" + Fetish.getIdFromFetish(currentTopFetish));
			
			currentTopPotency = TFPotency.MINOR_BOOST;
			currentTopRemovePotency = TFPotency.MINOR_DRAIN;
			
			currentTopChance = baseTopChance;
			currentTopRemoveChance = baseTopRemoveChance;
			
			if(wantsToShare) {
				// Increase base add chances based on NPC's experience levels for this fetishes
				switch(this.getFetishDesire(currentTopFetish)) {
					case THREE_LIKE:
						currentTopChance += desiredFetishIncrement;
						break;
					case FOUR_LOVE:
						currentTopChance += desiredFetishIncrement * 2;
						break;
					case ONE_DISLIKE:
						currentTopChance -= desiredFetishIncrement;
						break;
					case ZERO_HATE:
						currentTopChance = 0;
						break;
					default:
				}
				
				// Increase base add chances based on NPC's experience levels for this fetishes
				switch(this.getFetishLevel(currentTopFetish)) {
					case ONE_AMATEUR:
						currentTopChance += expFetishIncrement;
						break;
					case TWO_EXPERIENCED:
						currentTopChance += expFetishIncrement * 2;
						break;
					case THREE_EXPERT:
						currentTopChance += expFetishIncrement * 3;
						break;
					case FOUR_MASTERFUL:
						currentTopChance += expFetishIncrement * 4;
						break;
					default:
				}
			}
			
			// set chances if NPC has top fetish
			if(this.hasFetish(currentTopFetish)) {
				if(wantsToShare) {
					currentTopChance *= pairedFetishMultiplier;
					currentTopRemoveChance = 0;
				}
				else if(pairedFetishesOnly) {
					currentTopChance = 0;
				}
				else {
					currentTopChance -= matchedFetishDecrement;
					currentTopRemoveChance += matchedFetishRemoveIncrement;
				}
			}
			else if(pairedFetishesOnly && wantsToShare) {
				currentTopChance = 0;
				currentTopRemoveChance = 0;
			}
			
			// if player has positive top fetish desire, adjust potency level to fully add fetish, not just desire
			if(target.getFetishDesire(currentTopFetish) == FetishDesire.THREE_LIKE ||
				target.getFetishDesire(currentTopFetish) == FetishDesire.FOUR_LOVE) {
				currentTopPotency = TFPotency.BOOST;
			}
			else if(target.getFetishDesire(currentTopFetish) == FetishDesire.TWO_NEUTRAL) {
				int rand = Util.random.nextInt(100);
				
				// if the player is neutral, but the NPC has paired fetish,small chance to fully add rather than just boost desire
				if(wantsToShare && this.hasFetish(currentBottomFetish) && rand < 30) {
					currentTopPotency = TFPotency.BOOST;
				}
				
			} else {
				// if they are already less than neutral, don't remove any more
				currentTopRemoveChance = 0;
			}
			
			// prevent extraneous effects if player has top fetish, plus alter remove potency to drop fetish, not just desire
			if(target.hasFetish(currentTopFetish)) {
				currentTopChance = 0;
				currentTopRemovePotency = TFPotency.DRAIN;
			} 
			
			// some setting and status combos can result in negative values, so let's zero those out
			if(currentTopChance < 0) { currentTopChance = 0 ;}
			if(currentTopRemoveChance < 0) { currentTopRemoveChance = 0 ;}
			
			if(currentTopChance > 0) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(
						itemType.getEnchantmentEffect(),
						currentTopFetish.getAssociatedTFModifier(),
						currentTopModifier,
						currentTopPotency,
						1),
					currentTopChance));
			}
			if(currentTopRemoveChance > 0) {
				possibleEffects.add(new PossibleItemEffect(
					new ItemEffect(
						itemType.getEnchantmentEffect(),
						currentTopFetish.getAssociatedTFModifier(),
						currentTopModifier,
						currentTopRemovePotency,
						1),
					currentTopRemoveChance));
			}
		}
		
		// randomly select from possible effects 
		int total = 0;
		for(PossibleItemEffect entry : possibleEffects) {
			total+=entry.getChance();
		}
		
		// no valid options found
		if (total == 0) {
			return null;
		}
		
		int count = Util.random.nextInt(total)+1;
		total = 0;
		for(PossibleItemEffect entry : possibleEffects) {
			if(total < count && total+entry.getChance()>= count) {
				selectedEffect = entry.getEffect();
				break;
			}
			total+=entry.getChance();
		}
		
		// Leaving this present but commented out so it can be easily re-enabled by anyone wanting to tweak or check
		// the results of fetish selection for potion generation
//		System.out.println("POSSIBLE"); 
//		for(Entry<ItemEffect, Integer> entry : possibleEffects.entrySet()) {
//			System.out.println(entry.getChance()+ " " + entry.getKey().getSecondaryModifier()+ " " + entry.getKey().getPotency()); 
//		}
//		System.out.println("SELECTED"); 
//		System.out.println(selectedEffect.getSecondaryModifier() + " " + selectedEffect.getPotency()); 
//		System.out.println(count); 
		
		
		// no fetish to add, so we have nothing to return
		if(selectedEffect == null) {
			return null;
		}
		
		
		Map<TFModifier, String> fetishAddFlavorText = new HashMap<>(), fetishRemoveFlavorText = new HashMap<>();
		
		String defaultFetishAddFlavorText = "为什么不眼界宽阔点，嗯？";
		String defaultFetishRemoveFlavorText = "这些不得了的玩意，你还是冷静一下吧，嗯？";
		
		// Body part
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_PENIS_GIVING, "你会喜欢上用胯下那根肉棒的！");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_PENIS_GIVING, "是时候别这么沉迷于你那根肉棒了。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_PENIS_RECEIVING, "给我喜欢上又粗又大的鸡巴吧！");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_PENIS_RECEIVING, "别再那么喜欢肉棒了。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_ANAL_GIVING, "从此之后你会爱上菊花的。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_ANAL_GIVING, "冷静一下，别满脑子想操别人屁股了。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_ANAL_RECEIVING, "你会喜欢把它吃进菊花的。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_ANAL_RECEIVING, "也许你应该对被操菊花的事冷静点。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_BREASTS_OTHERS, "你难道不喜欢漂亮的奶子吗？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_BREASTS_OTHERS, "你太喜欢胸部了。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_BREASTS_SELF, "你不想好好利用一下你的胸部吗？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_BREASTS_SELF, "你太喜欢你的胸部了。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_ORAL_GIVING, "你那张漂亮的嘴要派上用场了。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_ORAL_GIVING, "你没必要见到鸡巴就舔，不是吗？");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_ORAL_RECEIVING, "你不是很喜欢接受口交吗？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_ORAL_RECEIVING, "没人喜欢被操进嘴里，你知道的。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_VAGINAL_GIVING, "没什么比得上去操个湿乎乎的小穴，是吧？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_VAGINAL_GIVING, "性爱不仅限于小穴。拓展一下你的视野。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_VAGINAL_RECEIVING, "说白了你就是想被操逼，对吧？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_VAGINAL_RECEIVING, "性爱不仅限于小穴。拓展一下你的视野。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_LEG_LOVER, "一双美腿真的能让一切变得不同，对吧？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_LEG_LOVER, "也许应该多关注一下腰以上的身体——或者至少是臀部周围的身体？");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_STRUTTER, "你有着让人欲罢不能的美腿——你真该好好利用。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_STRUTTER, "也许应该多关注一下腰以上的身体——或者至少是臀部周围的身体？");

		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_FOOT_GIVING, "你梦寐以求着使用你的双脚！");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_FOOT_GIVING, "我觉得你已经受够用脚踩人了。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_FOOT_RECEIVING, "之后你会喜欢为别人的脚服务的。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_FOOT_RECEIVING, "是时候停止纠结别人的脚了。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_ARMPIT_RECEIVING, "你时时刻刻想让你的腋窝被玩弄。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_ARMPIT_RECEIVING, "忘记使用你的腋窝。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_ARMPIT_GIVING, "腋窝比屁股、小穴或鸡巴好多了。至少，你会这么想。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_ARMPIT_GIVING, "你没必要这么纠结于腋窝。");
		
		// Behavioral
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_DOMINANT, "你不觉得你应该是支配者吗？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_DOMINANT, "你真的没有你想的那么吓人。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_SUBMISSIVE, "屈服吧，承认你只想做我的玩物。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_SUBMISSIVE, "有时候得到你想要的也很好，对吧？");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_CUM_STUD, "没有什么比用你的子种汁灌出一个泡芙更好了，对吧？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_CUM_STUD, "性爱应该享受过程，别看重目的。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_CUM_ADDICT, "我一眼就能看出来这是个肮脏的精液垃圾箱。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_CUM_ADDICT, "如果你愿意的话，你可以不仅是别人的子种汁容器，你知道的。");

		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_LACTATION_SELF, "你会喜欢被榨乳的。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_LACTATION_SELF, "你知道，生活中有比被榨乳更重要的事。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_LACTATION_OTHERS, "你渴望乳汁的味道。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_LACTATION_OTHERS, "总想喝奶奶是不对的。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_DEFLOWERING, "拔得头筹的感觉很特别，对吧？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_DEFLOWERING, "相信我，找那些经验丰富的会更有趣。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_PURE_VIRGIN, "你应该珍惜你守住的贞洁，趁它还在的时候。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_PURE_VIRGIN, "去他妈的贞操。没了活得更逍遥。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_IMPREGNATION, "你这种种马就该狠狠地给那群婊子配种。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_IMPREGNATION, "管好你自己。没人想要你的孩子。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_PREGNANCY, "被干固然棒，但被授种要更好，是不是？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_PREGNANCY, "怀上孩子很累的，你不觉得吗？");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_SADIST, "看着对方被你折磨却反而求之不得的感觉，难道不是很美妙吗？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_SADIST, "不是谁都想当你的沙包。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_MASOCHIST, "是时候拥抱苦痛了。你会感恩我的。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_MASOCHIST, "多关心一下自己的身体。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_NON_CON_DOM, "他们恳求你停下来的声音让你近乎疯狂，是不是？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_NON_CON_DOM, "大多数情况下，说不就是拒绝。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_NON_CON_SUB, "每当你说“不要”，眼中却满是“继续用力操”，我看得到。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_NON_CON_SUB, "不管你信不信，其实不被强迫也可以很舒服。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_DENIAL, "比高潮更美妙的就是，告诉对方不能高潮，对吗？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_DENIAL, "如果有人想跟你搞，至少还是让他们高潮一次比较好。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_DENIAL_SELF, "高潮到底有意思在哪呢？你不想好好品味过程吗？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_DENIAL_SELF, "如果你不去一次，做这些的意义在哪？");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_VOYEURIST, "有时候只是看着也很有趣，不是吗？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_VOYEURIST, "请尊重他人隐私。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_EXHIBITIONIST, "既然明白，那就好好展现出来。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_EXHIBITIONIST, "不是谁都想看透你的一切。");

		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_BONDAGE_APPLIER, "看到别人被绑起来祈求你的原谅，很棒不是吗？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_BONDAGE_APPLIER, "不是谁都喜欢被绑起来的，你明白吧。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_BONDAGE_VICTIM, "你肯定会喜欢被束缚起来，然后把钥匙丢掉吧。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_BONDAGE_VICTIM, "如果你能动肯定更有意思。");
		
		// Behavioral unpaired
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_BIMBO, "是时候拥抱你心中那个无脑的骚货了。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_BIMBO, "或许还是给自己留点自尊？");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_CROSS_DRESSER, "你想穿什么就穿什么，尽情享受吧。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_CROSS_DRESSER, "稍微保守一点死不了人。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_MASTURBATION, "谁都不可能比你更了解自己，对不对？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_MASTURBATION, "也许你应该考虑一下，偶尔也拿别人的垃圾开开眼界？");

		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_INCEST, "要知道，如果没有一点乐趣，那怎么成为禁忌呢。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_INCEST, "你干啥？恶心。");
		
		// Behavioral transformative
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_TRANSFORMATION_GIVING, "在我看来，你应该成为变革的推动者。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_TRANSFORMATION_GIVING, "让人们保持原样吧。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_TRANSFORMATION_RECEIVING, "你很喜欢变成别的样子，对不对？");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_TRANSFORMATION_RECEIVING, "我觉得你现在的样子就很不错了。");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_KINK_GIVING, "你真是见多识广，应该多多分享才是。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_KINK_GIVING, "别人喜欢什么你就让他们喜欢就好，可以吗？");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_KINK_RECEIVING, "在我看来，你确实应该多接受些新鲜事物。");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_KINK_RECEIVING, "我觉得你已经足够有激情了。");
		
		if(selectedEffect.getPotency() == TFPotency.MINOR_BOOST || selectedEffect.getPotency() == TFPotency.BOOST) {
			// default for adding a fetish, just in case a fetish is somehow selected without a string defined in the lookup
			selectedEffectString = defaultFetishAddFlavorText;
			
			if(fetishAddFlavorText.get(selectedEffect.getSecondaryModifier()) != null ) {
				selectedEffectString = fetishAddFlavorText.get(selectedEffect.getSecondaryModifier());
			}
			
		} else {
			// default for removing a fetish, just in case a fetish is somehow selected without a string defined in the lookup
			selectedEffectString = defaultFetishRemoveFlavorText;
			
			if(fetishRemoveFlavorText.get(selectedEffect.getSecondaryModifier()) != null ) {
				selectedEffectString = fetishRemoveFlavorText.get(selectedEffect.getSecondaryModifier());
			}
		}
		
		// finally, build and return our fetish potion
		return new FetishPotion(itemType,
				Util.newArrayListOfValues(new PossibleItemEffect(selectedEffect, selectedEffectString)));
	}
	
	
	// Sex:
	
	/**
	 * Override this method to set a special virginity loss scene for the player.
	 */
	public String getSpecialPlayerVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating, GameCharacter receivingCharacter, SexAreaOrifice penetrated) {
		return null;
	}
	
	public String getSpecialPlayerPureVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating) {
		return null;
	}
	
	public void endSex() {
	}
	
	public boolean isWantingToEquipCondom(GameCharacter partner) {
		boolean wantingToEquip = this.getFetishDesire(Fetish.FETISH_CUM_STUD).isNegative() || (partner.hasVagina() && !partner.isVisiblyPregnant() && this.getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative());
//		System.out.println("isWantingToEquipCondom("+partner.getName()+"): "+wantingToEquip);
		return wantingToEquip;
	}

	public boolean isWantingToEquipCondomOnPartner(GameCharacter partner) {
		if(!partner.hasPenisIgnoreDildo()) {
			return false;
		}
		return this.getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative() || (this.hasVagina() && !this.isVisiblyPregnant() && this.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative());
	}

	/**
	 *  Finds an item of clothing from this character's inventory that this character wants to equip.
	 *  <br/>Handles condom equipping.
	 */
	public Value<AbstractClothing, String> getSexClothingToSelfEquip(GameCharacter partner, boolean inQuickSex) {
		if(Main.game.isInSex() && (inQuickSex || !Main.sex.getInitialSexManager().isPartnerWantingToStopSex(this))) {
			if(this.hasPenisIgnoreDildo()
					&& this.getClothingInSlot(InventorySlot.PENIS)==null
					&& isWantingToEquipCondom(partner)) {
				AbstractClothing condom = null;
				for(AbstractClothing clothing : this.getAllClothingInInventory().keySet()) {
					if(clothing.isCondom()) {
						condom = clothing;
						break;
					}
				}
				if(condom!=null && this.isAbleToEquip(condom, inQuickSex, this)) {
//					System.out.println("Condom");
					return new Value<>(condom, UtilText.parse(this, "[npc.Name]从物品栏汇总拿出一个"+condom.getName()+"……"));
				}
			}
		}
		return null;
	}

	/**
	 *  Finds an item of clothing from this character's inventory that this character wants to equip on the targeted partner.
	 *  <br/>Handles condom equipping.
	 */
	public Value<AbstractClothing, String> getSexClothingToEquip(GameCharacter partner, boolean inQuickSex) {
		if(Main.game.isInSex() && (inQuickSex || !Main.sex.getInitialSexManager().isPartnerWantingToStopSex(this))) {
			if(Main.sex.getSexPositionSlot(partner)==SexSlotGeneric.MISC_WATCHING) {
				return null; // Do not equip anything on spectators
			}
			// Condoms:
			if(partner.hasPenisIgnoreDildo()
					&& partner.getClothingInSlot(InventorySlot.PENIS)==null
					&& isWantingToEquipCondomOnPartner(partner)) {
				AbstractClothing condom = null;
				for(AbstractClothing clothing : this.getAllClothingInInventory().keySet()) {
					if(clothing.isCondom()) {
						condom = clothing;
						break;
					}
				}
				if(condom!=null && partner.isAbleToEquip(condom, inQuickSex || !Main.sex.isInForeplay(this), this)) { // Auto management in quick sex and if this NPC is past foreplay, as as otherwise clothing removals would take forever
					return new Value<>(condom, UtilText.parse(this, "[npc.Name]从物品栏汇总拿出一个"+condom.getName()+"……"));
				}
			}
			
			// Other clothing (only doms equip clothing on sub partners during sex):
			if(Main.sex.isDom(this) && !Main.sex.isDom(partner)) {
				Map<AbstractClothing, Integer> availableClothingInInventory = new HashMap<>(this.getAllClothingInInventory());
				// Remove clothing from available map if this clothing has been unequipped from the NPC (to prevent the NPC from equipping their own clothing onto their partner)
				for(Entry<InventorySlot, Map<AbstractClothing, List<DisplacementType>>> entry : Main.sex.getClothingPreSexMap().get(this).entrySet()) {
					for(AbstractClothing clothing : entry.getValue().keySet()) {
						for(AbstractClothing c : new HashSet<>(availableClothingInInventory.keySet())) {
							if(c.equalsWithoutEquippedSlot(clothing) && availableClothingInInventory.get(c)==1) {
								availableClothingInInventory.remove(c);
							}
						}
					}
				}
				
				for(AbstractClothing clothing : availableClothingInInventory.keySet()) {
					boolean wantsToEquip = false;
					// BDSM:
					if((clothing.getClothingType().getClothingSet()==SetBonus.getSetBonusFromId("innoxia_bdsm") || clothing.getClothingType().getClothingSet()==SetBonus.getSetBonusFromId("sage_ltxset"))) {
						wantsToEquip = this.getFetishDesire(Fetish.FETISH_BONDAGE_APPLIER).isPositive();
					}
					// Chastity cages are only equipped if NPC has like or love attitude towards denier fetish:
					if(clothing.getItemTags().contains(ItemTag.CHASTITY)) {
						wantsToEquip = this.getFetishDesire(Fetish.FETISH_DENIAL).isPositive() || this.getFetishDesire(Fetish.FETISH_BONDAGE_APPLIER).isPositive();
					}
					
					// Sex toys (NPC will not equip sex toys that block the areas they're interested in using):
					if(clothing.getClothingType().getDefaultItemTags().contains(ItemTag.ENABLE_SEX_EQUIP)) {
						if(clothing.getBlockedPartsMap(partner, clothing.getClothingType().getEquipSlots().get(0)).stream().anyMatch(bp->bp.blockedBodyParts.contains(CoverableArea.PENIS)) && partner.hasPenisIgnoreDildo()) {
							if(wantsToUseAreaDuringSex(SexAreaPenetration.PENIS, partner)) {
								wantsToEquip = false;
								continue;
							}
							wantsToEquip = true;
						} else if(clothing.getBlockedPartsMap(partner, clothing.getClothingType().getEquipSlots().get(0)).stream().anyMatch(bp->bp.blockedBodyParts.contains(CoverableArea.VAGINA)) && partner.hasVagina()) {
							if(wantsToUseAreaDuringSex(SexAreaOrifice.VAGINA, partner)) {
								wantsToEquip = false;
								continue;
							}
							wantsToEquip = true;
						} else if(clothing.getBlockedPartsMap(partner, clothing.getClothingType().getEquipSlots().get(0)).stream().anyMatch(bp->bp.blockedBodyParts.contains(CoverableArea.ANUS)) && Main.game.isAnalContentEnabled()) {
							if(wantsToUseAreaDuringSex(SexAreaOrifice.ANUS, partner)) {
								wantsToEquip = false;
								continue;
							}
							wantsToEquip = true;
						} else if(clothing.getBlockedPartsMap(partner, clothing.getClothingType().getEquipSlots().get(0)).stream().anyMatch(bp->bp.blockedBodyParts.contains(CoverableArea.NIPPLES))) {
							if(wantsToUseAreaDuringSex(SexAreaOrifice.NIPPLE, partner)) {
								wantsToEquip = false;
								continue;
							}
							wantsToEquip = true;
						} else if(clothing.getBlockedPartsMap(partner, clothing.getClothingType().getEquipSlots().get(0)).stream().anyMatch(bp->bp.blockedBodyParts.contains(CoverableArea.MOUTH))) {
							if(wantsToUseAreaDuringSex(SexAreaOrifice.MOUTH, partner)) {
								wantsToEquip = false;
								continue;
							}
							wantsToEquip = true;
						}
					}
					// Always auto manage clothing, as NPCs use clothing removal methods in SexManagerDefault, so clothing additions should take place after removals.
					// If auto management was disabled, then the NPC would equip clothing onto their partner as soon as that slot became free, which makes sex feel quite disjointed
						// e.g. An NPC deciding to equip latex stockings on their parter only when their partner has removed their shoes.
					InventorySlot defaultSlot = clothing.getClothingType().getEquipSlots().get(0);
					if(wantsToEquip
							&& Main.sex.isClothingEquipAvailable(partner, defaultSlot, clothing)
							&& clothing.isAbleToBeEquippedDuringSex(defaultSlot).getKey()
							&& partner.getClothingInSlot(defaultSlot)==null
							&& partner.isAbleToEquip(clothing, inQuickSex, this)) {
						return new Value<>(clothing, UtilText.parse(this, "[npc.Name]从物品栏中拿出"+clothing.getName(true, true)+"……"));
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * @return true if any character in the current sex scene wants to use the area during sex.
	 */
	private boolean wantsToUseAreaDuringSex(SexAreaInterface area, GameCharacter partner) {
		List<GameCharacter> sexParticipants = Main.sex.getAllParticipants();
		sexParticipants.remove(partner);
		// Check both current desire and main sex desire, to make sure that foreplay is accounted for:
		for(GameCharacter participant : sexParticipants) {
			SexType currentPreference = Main.sex.isInForeplay(participant)?participant.getForeplayPreference(partner):participant.getMainSexPreference(partner);
			SexType mainPreference = participant.getMainSexPreference(partner);
			if((currentPreference!=null && currentPreference.getTargetedSexArea()==area) || (mainPreference!=null && mainPreference.getTargetedSexArea()==area)) {
				return true;
			}
		}
		return false;
	}
	
	public Value<AbstractItem, String> getSexItemToUse(GameCharacter partner) {
		if(Main.game.isInSex() && !Main.sex.isCharacterInanimateFromImmobilisation(this)) {
			List<GameCharacter> charactersPenetratingThisNpc = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(this, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
			List<GameCharacter> charactersThisNpcIsPenetrating = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(this, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
			
			if(this.equals(partner)) { // Self-using items:
				if(!charactersPenetratingThisNpc.isEmpty() && charactersPenetratingThisNpc.stream().anyMatch((c) -> c.hasPenisIgnoreDildo())) { // Pills for when this NPC is being penetrated:
					if(this.isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)) {
						if((this.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() || (this.getHistory()==Occupation.NPC_PROSTITUTE && !this.isSlave()))
								&& !this.isPregnant()
								&& !this.hasIncubationLitter(SexAreaOrifice.VAGINA)
								&& !this.hasStatusEffect(StatusEffect.PROMISCUITY_PILL)
								&& this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_sterility"))
								&& !Main.sex.getItemUseDenials(this, partner).contains(ItemType.getItemTypeFromId("innoxia_pills_sterility"))) {
							return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_sterility"),
										UtilText.parse(this, charactersPenetratingThisNpc.get(0),
												"[npc.name]从物品栏里拿出一小片蓝色的“[#ITEM_innoxia_pills_sterility.getName(false)]”，解开包装后便放进嘴里，咽了下去。"
												+ (this.isMute()
														?"确认自己生育能力大幅降低，不太可能再怀孕后，[npc.Name]长舒一口气……"
														:"确认自己生育能力大幅降低，不太可能再怀孕后，[npc.name][npc.moansVerb]起来，[npc.speech(我一点也不想怀孕……)]")));
						}
						if((this.getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && (this.getHistory()!=Occupation.NPC_PROSTITUTE || this.isSlave()))
								&& this.hasVagina()
								&& partner.hasPenisIgnoreDildo()
								&& !this.isPregnant()
								&& !this.hasIncubationLitter(SexAreaOrifice.VAGINA)
								&& (Main.sex.getSexPace(this)!=SexPace.SUB_RESISTING || this.hasFetish(Fetish.FETISH_NON_CON_SUB)) // Do not want to get pregnant from rape unless they have the fetish
								&& !this.hasStatusEffect(StatusEffect.VIXENS_VIRILITY)
								&& this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_fertility"))
								&& !Main.sex.getItemUseDenials(this, partner).contains(ItemType.getItemTypeFromId("innoxia_pills_fertility"))) {
							return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_fertility"),
									UtilText.parse(this, charactersPenetratingThisNpc.get(0),
											"[npc.name]从物品栏里拿出一小片粉色的“[#ITEM_innoxia_pills_fertility.getName(false)]”，解开包装后便放进嘴里，咽了下去。"
											+ (this.isMute()
													?"当[npc.sheIs]想象[npc2.name]在[npc.herHim]体内射精并让[npc.herHim]怀孕时，[npc.name]知道自己的生育力强化了，发出了[npc.a_moan+]……"
													:"[npc.name]知道自己生育力强化了，发出[npc.a_moan+]并恳求道，[npc.speech(在我体内中出！我要你把我弄怀孕！)]")));
						}
					}
				}
				if(!charactersThisNpcIsPenetrating.isEmpty() && this.hasPenisIgnoreDildo()) { // Pills for when this NPC is penetrating someone else:
					if(this.isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)) {
						if(this.getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
								&& !partner.isPregnant()
								&& !partner.hasIncubationLitter(SexAreaOrifice.VAGINA)
								&& !this.hasStatusEffect(StatusEffect.PROMISCUITY_PILL)
								&& this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_sterility"))
								&& !Main.sex.getItemUseDenials(this, partner).contains(ItemType.getItemTypeFromId("innoxia_pills_sterility"))) {
							return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_sterility"),
										UtilText.parse(this, charactersThisNpcIsPenetrating.get(0),
												"[npc.name]从物品栏里拿出一小片蓝色的“[#ITEM_innoxia_pills_sterility.getName(false)]”，解开包装后便放进嘴里，咽了下去。"
												+ (this.isMute()
														?"[npc.sheIs]知道[npc.sheIs]现在阳气大减，几乎不可能把[npc2.name]搞大肚子，于是发出了一声如释重负的[npc.moan]……"
														:"了解到[npc.sheIs]现在的阳刚之气已经大不如前，极不可能把[npc2.name]搞大肚子，[npc.name][npc.moansVerb]道，[npc.speech(这样就好多了！我现在不会让你怀孕了！)]")));
						}
						if(this.getFetishDesire(Fetish.FETISH_IMPREGNATION).isPositive()
								&& partner.hasVagina()
								&& this.hasPenisIgnoreDildo()
								&& !partner.isPregnant()
								&& !partner.hasIncubationLitter(SexAreaOrifice.VAGINA)
								&& (Main.sex.getSexPace(this)!=SexPace.SUB_RESISTING || this.hasFetish(Fetish.FETISH_NON_CON_SUB)) // Do not want to impregnate during rape unless they have the fetish
								&& !this.hasStatusEffect(StatusEffect.VIXENS_VIRILITY)
								&& this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_fertility"))
								&& !Main.sex.getItemUseDenials(this, partner).contains(ItemType.getItemTypeFromId("innoxia_pills_fertility"))) {
							return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_fertility"),
									UtilText.parse(this, charactersThisNpcIsPenetrating.get(0),
											"[npc.name]从物品栏里拿出一小片粉色的“[#ITEM_innoxia_pills_fertility.getName(false)]”，解开包装后便放进嘴里，咽了下去。"
											+ (this.isMute()
													?"[npc.Name]知道自己的生殖力更强了，脑内不禁出现了灌满[npc2.herHim]体内，让[npc2.herHim]怀上孩子的景象，一声[npc.a_moan+]从口中漏出……"
													:"[npc.Name]知道自己生殖力更强了，于是便发出一声[npc.a_moan+]，调戏道，[npc.speech(我一定会给你灌成泡芙，让你怀上的！)]")));
						}
					}
				}
				
			} else { // Non-self use:
				if(charactersPenetratingThisNpc.contains(partner) && charactersPenetratingThisNpc.stream().anyMatch((c) -> c.hasPenisIgnoreDildo())) { // Pills for when this NPC is being penetrated:
					if(partner.isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)) {
						if(!Main.sex.getItemUseDenials(this, partner).contains(ItemType.getItemTypeFromId("innoxia_pills_sterility"))) {
							if((this.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() || (this.getHistory()==Occupation.NPC_PROSTITUTE && !this.isSlave()))
									&& !partner.isPregnant()
									&& !partner.hasIncubationLitter(SexAreaOrifice.VAGINA)
									&& (Main.sex.getSexPace(this)!=SexPace.SUB_RESISTING || this.hasFetish(Fetish.FETISH_NON_CON_SUB))
									&& !partner.hasStatusEffect(StatusEffect.PROMISCUITY_PILL)
									&& this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_sterility"))
									&& !Main.sex.getItemUseDenials(this, partner).contains(ItemType.getItemTypeFromId("innoxia_pills_sterility"))) {
								if(partner.isPlayer()) {
									if(Main.sex.isForcingItemUse(this, partner)) {
										return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_sterility"),
												"[npc.name]从物品栏里拿出一小片蓝色的“[#ITEM_innoxia_pills_sterility.getName(false)]”，打开包装后便喂到了你嘴里。"
												+ "随后捏住了你的[pc.lips]，防止你偷偷吐出来，"
													+ (this.isMute()
															?"[npc.name]发出一声毋庸置疑的低吼，一直等到你咽了下去……"
															:"[npc.name]强迫你咽了下去，随后低吼道，[npc.speech(我可不想让你给我弄怀上了！)]"));
										
									} else {
										return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_sterility"),
												"[npc.Name]从物品栏里拿出一小片蓝色的“[#ITEM_innoxia_pills_sterility.getName(false)]”，递给了你"
													+ (this.isMute()
															?"，快要哭出来似的央求着，想让你吃下去……"
															:"，请求道，[npc.speech(求你吃下这个吧；我不想怀上你的孩子！)]"));
									}
									
								} else {
									return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_sterility"), ""); // Description is appended in the SexAction
								}
							}
						}
						if((this.getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && (this.getHistory()!=Occupation.NPC_PROSTITUTE || this.isSlave()))
								&& !partner.isPregnant()
								&& !partner.hasIncubationLitter(SexAreaOrifice.VAGINA)
								&& (Main.sex.getSexPace(this)!=SexPace.SUB_RESISTING || this.hasFetish(Fetish.FETISH_NON_CON_SUB))
								&& !partner.hasStatusEffect(StatusEffect.VIXENS_VIRILITY)
								&& this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_fertility"))
								&& !Main.sex.getItemUseDenials(this, partner).contains(ItemType.getItemTypeFromId("innoxia_pills_fertility"))) {
							if(partner.isPlayer()) {
								if(Main.sex.isForcingItemUse(this, partner)) {
									return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_fertility"),
											"[npc.name]从物品栏里拿出一小片粉色的“[#ITEM_innoxia_pills_fertility.getName(false)]”，打开包装后便喂到了你嘴里。"
											+ "随后捏住了你的[pc.lips]，防止你偷偷吐出来，"
												+ (this.isMute()
														?"[npc.name]发出一声毋庸置疑的低吼，一直等到你咽了下去……"
														:"[npc.name]强迫你咽了下去，随后低吼道，[npc.speech(我正想让你搞大我的肚子呢！)]"));
									
								} else {
									return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_fertility"),
											"[npc.Name]从物品栏里拿出一小片粉色的“[#ITEM_innoxia_pills_fertility.getName(false)]”，递给了你"
												+ (this.isMute()
														?"，快要哭出来似的央求着，想让你吃下去……"
														:"，请求道，[npc.speech(求你吃下这个吧；我想怀上你的孩子！)]"));
								}
								
							} else {
								return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), ""); // Description is appended in the SexAction
							}
						}
					}
				}
				if(charactersThisNpcIsPenetrating.contains(partner) && this.hasPenisIgnoreDildo()) { // Pills for when this NPC is penetrating the partner:
					if(partner.isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)) {
						if(this.getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
								&& !partner.isPregnant()
								&& !partner.hasIncubationLitter(SexAreaOrifice.VAGINA)
								&& (Main.sex.getSexPace(this)!=SexPace.SUB_RESISTING || this.hasFetish(Fetish.FETISH_NON_CON_SUB))
								&& !partner.hasStatusEffect(StatusEffect.PROMISCUITY_PILL)
								&& this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_sterility"))
								&& !Main.sex.getItemUseDenials(this, partner).contains(ItemType.getItemTypeFromId("innoxia_pills_sterility"))) {
							if(partner.isPlayer()) {
								if(Main.sex.isForcingItemUse(this, partner)) {
									return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_sterility"),
											"[npc.name]从物品栏里拿出一小片蓝色的“[#ITEM_innoxia_pills_sterility.getName(false)]”，打开包装后便喂到了你嘴里。"
											+ "随后捏住了你的[pc.lips]，防止你偷偷吐出来，"
												+ (this.isMute()
														?"[npc.name]发出一声毋庸置疑的低吼，一直等到你咽了下去……"
														:"[npc.name]强迫你咽了下去，随后低吼道，[npc.speech(我可不想搞大你的肚子！)]"));
									
								} else {
									return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_sterility"),
											"[npc.Name]从物品栏里拿出一小片蓝色的“[#ITEM_innoxia_pills_sterility.getName(false)]”，递给了你"
												+ (this.isMute()
														?"，快要哭出来似的央求着，想让你吃下去……"
														:"，请求道，[npc.speech(求你吃下这个吧；我不想让你怀上！)]"));
								}
								
							} else {
								return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_sterility"), ""); // Description is appended in the SexAction
							}
						}
						if(this.getFetishDesire(Fetish.FETISH_IMPREGNATION).isPositive()
								&& !partner.isPregnant()
								&& !partner.hasIncubationLitter(SexAreaOrifice.VAGINA)
								&& (Main.sex.getSexPace(this)!=SexPace.SUB_RESISTING || this.hasFetish(Fetish.FETISH_NON_CON_SUB))
								&& !partner.hasStatusEffect(StatusEffect.VIXENS_VIRILITY)
								&& this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_fertility"))
								&& !Main.sex.getItemUseDenials(this, partner).contains(ItemType.getItemTypeFromId("innoxia_pills_fertility"))) {
							if(partner.isPlayer()) {
								if(Main.sex.isForcingItemUse(this, partner)) {
									return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_fertility"),
											"[npc.name]从物品栏里拿出一小片粉色的“[#ITEM_innoxia_pills_fertility.getName(false)]”，打开包装后便喂到了你嘴里。"
											+ "随后捏住了你的[pc.lips]，防止你偷偷吐出来，"
												+ (this.isMute()
														?"[npc.name]发出一声毋庸置疑的低吼，一直等到你咽了下去……"
														:"[npc.name]强迫你咽了下去，随后低吼道，[npc.speech(我正想让你怀上我的孩子呢！)]"));
									
								} else {
									return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_fertility"),
											"[npc.Name]从物品栏里拿出一小片粉色的“[#ITEM_innoxia_pills_fertility.getName(false)]”，递给了你"
												+ (this.isMute()
														?"，快要哭出来似的央求着，想让你吃下去……"
														:"，请求道，[npc.speech(求你吃下这个吧；我想让你怀上！)]"));
								}
								
							} else {
								return new Value<>(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), ""); // Description is appended in the SexAction
							}
						}
					}
				}
			}
		}
		
		return null;
	}

	/**
	 * Generic version of getSexBehaviourDeniesRequests(GameCharacter requestingCharacter, SexType sexTypeRequest)
	 */
	public boolean getSexBehaviourDeniesRequests(GameCharacter requestingCharacter) {
		return getSexBehaviourDeniesRequests(requestingCharacter, null);
	}
	
	public boolean getSexBehaviourDeniesRequests(GameCharacter requestingCharacter, SexType sexTypeRequest) {
		if(requestingCharacter.hasTraitActivated(Perk.CONVINCING_REQUESTS)) {
			return false;
		}
		
		if(Main.game.isInSex()
				&& Main.sex.getSexControl(requestingCharacter).getValue()<=SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue()
				&& Main.sex.getSexPace(this)==SexPace.DOM_ROUGH) {
			return true;
		}
		
		int weight = 0;
		if(sexTypeRequest!=null) {
			weight = calculateSexTypeWeighting(sexTypeRequest, requestingCharacter, null);
		}
		
		return weight<0 || this.hasFetish(Fetish.FETISH_SADIST);
	}
	

	/**
	 * @param position The position to check.
	 * @param slot The slot to check.
	 * @param slot The target's slot to check.
	 * @param target The person who is being interacted with in this slot.
	 * @return Whether this NPC is happy to be in this SexSlot
	 */
	public boolean isHappyToBeInSlot(AbstractSexPosition position, SexSlot slot, SexSlot targetSlot, GameCharacter target) {
		SexType targetSexPreference = Main.sex.getForeplayPreference(this, target);
		
		if(!Main.sex.isInForeplay(this)) {
			targetSexPreference = Main.sex.getMainSexPreference(this, target);
		}
		if(targetSexPreference==null) {
			return true;
		}
		return slot.isMeetsPreferenceCriteria(this, position, targetSlot, targetSexPreference);
	}
	
	public boolean isHappyToBeInSlot(AbstractSexPosition position, SexSlot slot, GameCharacter target) {
		return isHappyToBeInSlot(position, slot, null, target);
	}

	/**
	 * Override to force this character to use a certain SexPace in sex. Return null to use default Pace calculations.
	 */
	public SexPace getSexPaceSubPreference(GameCharacter character){
		return null;
	}

	// The methods of theoretical sex paces should be applicable to all those branches of thought in which the essential features are expressible with fetishes, arousal, and lust.
	public SexPace getTheoreticalSexPaceSubPreference(GameCharacter character) {
		if(!isAttractedTo(character) || this.hasFetish(Fetish.FETISH_NON_CON_SUB)) {
			if(Main.game.isNonConEnabled()) {
				if(isSlave()) {
					if(this.getObedienceValue()>=ObedienceLevel.POSITIVE_FIVE_SUBSERVIENT.getMinimumValue()) {
						return SexPace.SUB_EAGER;
						
					} else if(this.getObedienceValue()>=ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMinimumValue()) {
						return SexPace.SUB_NORMAL;
					}
				}
				
				if(getHistory() == Occupation.NPC_PROSTITUTE) {
					if(Main.sex.isConsensual()) {
						return SexPace.SUB_NORMAL;
					}
				}
				
				return SexPace.SUB_RESISTING;
				
			} else {
				return SexPace.SUB_NORMAL;
				
			}
		}
		
		if(hasStatusEffect(StatusEffect.WEATHER_STORM_VULNERABLE)) { // If they're vulnerable to arcane storms, they will always be eager during a storm:
			return SexPace.SUB_EAGER;
		}
		
		if (hasFetish(Fetish.FETISH_SUBMISSIVE) // Subs like being sub I guess ^^
				|| (hasFetish(Fetish.FETISH_PREGNANCY) && character.hasPenisIgnoreDildo() && hasVagina()) // Want to get pregnant
				|| (hasFetish(Fetish.FETISH_IMPREGNATION) && character.hasVagina() && hasPenisIgnoreDildo()) // Want to impregnate player
				) {
			return SexPace.SUB_EAGER;
		}
		
		return SexPace.SUB_NORMAL;
	}
	
	/**
	 * Override to force this character to use a certain SexPace in sex. Return null to use default Pace calculations.
	 */
	public SexPace getSexPaceDomPreference(){
		return null;
	}
	
	// Most people don't have time to master the very lewd details of theoretical sex paces.
	public SexPace getTheoreticalSexPaceDomPreference() {
		if(hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN) || (hasFetish(Fetish.FETISH_SUBMISSIVE) && !hasFetish(Fetish.FETISH_DOMINANT))) {
			return SexPace.DOM_GENTLE;
		}
		
		if(hasFetish(Fetish.FETISH_SADIST) || hasFetish(Fetish.FETISH_DOMINANT)) {
			return SexPace.DOM_ROUGH;
		}
		
		return SexPace.DOM_NORMAL;
	}
	
	public List<Class<?>> getUniqueSexClasses() {
		return new ArrayList<>();
	}
	
	/**
	 * Override this method and return a non-null list of SexActionInterfaces in order to limit what actions are available to this character during sex. For an example, see the Amber class.
	 * Use the <b>getSexActionInterfacesFromClass()</b> helper method to add all SexActionInterfaces from a containing class.
	 */
	public List<SexActionInterface> getLimitedSexClasses() {
		return null;
	}
	
	/**
	 * Helper method for the getLimitedSexClasses() method. Extracts all SexActionInterfaces from a class, and returns them in a list.
	 */
	protected List<SexActionInterface> getSexActionInterfacesFromClass(Class<?> classToAddSexActionsFrom) {
		List<SexActionInterface> actions = new ArrayList<>();
		Field[] fields = classToAddSexActionsFrom.getFields();
		
		for(Field f : fields){
			if (SexAction.class.isAssignableFrom(f.getType())) {
				try {
					SexAction action = ((SexAction) f.get(null));
					actions.add(action);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return actions;
	}

	/**
	 * This method determines how this NPC reacts to items being used on them.
	 * 
	 * @param item The item being used.
	 * @param itemOwner The owner of the item (so the game knows whose inventory to take it out of).
	 * @param user The character using the item on the target.
	 * @param target The character who is going to be experiencing the effects of the item's use.
	 * @return A Value whose Key is a Boolean of whether this use was successful or not, and whose Value is a description of what happened.
	 */
	public Value<Boolean, String> getItemUseEffects(AbstractItem item, GameCharacter itemOwner, GameCharacter user, GameCharacter target) {
		if(!user.equals(target)) { // Item is not being self-used:
			boolean isItemOrdinary = !item.getItemType().isTransformative() && !item.getItemType().isFetishGiving();
			
			if(target.isElemental()) {
				if(item.getItemType().isTransformative()) {
					return new Value<>(true,
							UtilText.parse(user, target,
									"<p>"
										+ "[npc2.speech(我随时都可以自由转化，给我"+item.getItemType().getUseName()+"那个恐怕浪费了，)]"
												+ "[npc2.name]叹了口气，但却还是照着对方的指示，"+item.getItemType().getUseName()+"了"+item.getName()+"。"
									+ "</p>")
							+ itemOwner.useItem(item, target, false));
					
				} else {
					return new Value<>(true, itemOwner.useItem(item, target, false));
				}
				
			} else if(isItemOrdinary
					|| (!target.isUnique() && !Main.game.isInCombat() && Main.combat.getAllCombatants(true).contains(user) && Main.combat.isCharacterVictory(user) && Main.combat.getEnemies(user).contains(target))
					|| (target.isSlave() && target.getOwner()!=null && target.getOwner().equals(user))) {
				return new Value<>(true, this.getItemUseEffectsAllowingUse(item, itemOwner, user, target));
				
			} else if(!target.isUnique()
						&& ((target.hasStatusEffect(StatusEffect.DRUNK_5)
								|| target.hasStatusEffect(StatusEffect.DRUNK_4)
								|| target.hasStatusEffect(StatusEffect.PSYCHOACTIVE))
							|| target.getAffectionLevel(user)==AffectionLevel.POSITIVE_FIVE_WORSHIP
							|| (target.getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive() && item.getItemType().isTransformative())
							|| (target.getFetishDesire(Fetish.FETISH_KINK_RECEIVING).isPositive() && item.getItemType().isFetishGiving())
							|| (Main.game.isInSex() && !Main.sex.isConsensual() && Main.sex.isDom(user) && !Main.sex.isDom(target)))) {
				return new Value<>(true, this.getItemUseEffectsAllowingUse(item, itemOwner, user, target));
				
			} else {
				if(item.getItemType().isTransformative()) {
					return new Value<>(false,
							UtilText.parse(user, target,
							"<p>"
								+ "[npc.Name]准备将自己的"+item.getName()+"给[npc2.name]，但[npc2.she]却只是瞧了一眼，便笑道，"
								+ "[npc2.speech(哈！有想法！但你不会真觉得这种随便什么药水，我就会喝吧？！)]<br/>"
								+ "[npc.Name]不情愿地把"+item.getName()+"放回物品栏中，没想到[npc2.nameIsFull]竟对此毫无兴趣。"
							+ "</p>"));
				} else {
					return new Value<>(false,
							UtilText.parse(user, target,
							"<p>"
								+ "[npc.Name]准备将自己的"+item.getName()+"交给[npc2.name]，但[npc2.she]却拒绝了。"
								+ "[npc.Name]只好不情愿地把"+item.getName()+"放回物品栏中。"
							+ "</p>"));
				}
			}
		
		} else { // Self-use always succeeds:
			return new Value<>(true, itemOwner.useItem(item, target, false));
		}
	}
	
	protected String getItemUseEffectsAllowingUse(AbstractItem item, GameCharacter itemOwner, GameCharacter user, GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		boolean isObedientSlave = target.isSlave() && target.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT;

		if(!user.equals(target)) { // Item is not being self-used:
			if(item.getItemType().equals(ItemType.getItemTypeFromId("innoxia_pills_sterility"))) {
				sb.append(UtilText.parse(user, target,
						"<p>"
							+ "[npc.Name]拿出一片“[#ITEM_innoxia_pills_sterility.getName(false)]”给了[npc2.name]，告诉[npc2.herHim]吃下去之后就不用担心意外怀孕了。"));
				
				if(isObedientSlave) {
					sb.append(UtilText.parse(user, target, 
							"[npc2.she]顺从地听从了要求，欣然接下了药片，立马拆开包装，咽了下去。"));
					
				} else if((target.hasFetish(Fetish.FETISH_IMPREGNATION) && target.hasPenis())
						|| (target.hasFetish(Fetish.FETISH_PREGNANCY) && target.hasVagina())) {
					sb.append(UtilText.parse(user, target,
							"[npc2.she]懊恼地叹了一声，但还是接下了药片，拆开包装咽了下去，随后呜咽道，"
							+ "[npc2.speech(没人会怀上的话，搞这个还有什么意义呢？)]"));
					
				} else {
					sb.append(UtilText.parse(user, target, 
							"[npc2.she]听后松了口气，欣然接下了药片，立马拆开包装咽了下去。"));
				}
				
				sb.append("</p>");
				
				sb.append(itemOwner.useItem(item, target, false, true));
				
				return sb.toString();
				
			} else if(item.getItemType().equals(ItemType.getItemTypeFromId("innoxia_pills_fertility"))) {
				sb.append(UtilText.parse(user, target,
						"<p>"
							+ "[npc.Name]拿出一片“[#ITEM_innoxia_pills_fertility.getName(false)]”给了[npc2.name]，告诉[npc2.herHim]吃下去之后就更容易怀孕了。"));
				
				if(isObedientSlave) {
					sb.append(UtilText.parse(user, target, 
							"[npc2.she]顺从地听从了要求，欣然接下了药片，立马拆开包装，咽了下去。"));
					
				} else if((target.hasFetish(Fetish.FETISH_IMPREGNATION) && target.hasPenis())
						|| (target.hasFetish(Fetish.FETISH_PREGNANCY) && target.hasVagina())) {
					sb.append(UtilText.parse(user, target, 
							"[npc2.she]乐得叫了起来，迫不及待地一把抓过药片，拆开了包装，"
							+ "连忙咽了下去，高喊道，"
							+ "[npc2.speech(一起来造孩子吧！)]"));
					
				} else {
					sb.append(UtilText.parse(user, target, 
							"[npc2.she]犹豫地叹了一声，，但还是接下了药片，拆开包装咽了下去。"));
				}
				
				sb.append("</p>");
				
				sb.append(itemOwner.useItem(item, target, false, true));
				
				return sb.toString();
					
			} else if(item.getItemType().equals(ItemType.getItemTypeFromId("innoxia_pills_broodmother"))) {
				sb.append(UtilText.parse(user, target,
						"<p>"
							+ "[npc.Name]拿出一片“[#ITEM_innoxia_pills_broodmother.getName(false)]”给了[npc2.name]，告诉[npc2.herHim]吃下去之后就更容易怀孕了。"));
				
				if(isObedientSlave) {
					sb.append(UtilText.parse(user, target, 
							"[npc2.she]顺从地听从了要求，欣然接下了药片，立马拆开包装，咽了下去。"));
					
				} else if((target.hasFetish(Fetish.FETISH_IMPREGNATION) && target.hasPenis())
						|| (target.hasFetish(Fetish.FETISH_PREGNANCY) && target.hasVagina())) {
					sb.append(UtilText.parse(user, target, 
							"[npc2.she]乐得叫了起来，迫不及待地一把抓过药片，拆开了包装，"
							+ "连忙咽了下去，高喊道，"
							+ "[npc2.speech(一起整几个孩子出来吧！)]"));
					
				} else {
					sb.append(UtilText.parse(user, target, 
							"[npc2.she]犹豫地叹了一声，，但还是接下了药片，拆开包装咽了下去。"));
				}
				
				sb.append("</p>");
				
				sb.append(itemOwner.useItem(item, target, false, true));
				
				return sb.toString();
					
			} else if(item.getItemType().equals(ItemType.getItemTypeFromId("innoxia_pills_lubrication"))) {
				sb.append(UtilText.parse(user, target,
						"<p>"
							+ "[npc.name]拿出一片“[#ITEM_innoxia_pills_lubrication.getName(false)]”给了[npc2.name]，告诉[npc2.herHim]吃下去能让[npc2.herHim]变得更润滑。"));
				
				if(isObedientSlave) {
					sb.append(UtilText.parse(user, target, 
							"[npc2.she]顺从地听从了要求，欣然接下了药片，立马拆开包装，咽了下去。"));
					
				} else {
					sb.append(UtilText.parse(user, target, 
							"[npc2.she]见这[#ITEM_innoxia_pills_lubrication.getName(false)]吃了也没什么坏处，便从[npc.namePos]的[npc.hand]里接了过来，"
									+ "然后迅速剥开包装一口吞下。"));
				}
				
				sb.append("</p>");
				
				sb.append(itemOwner.useItem(item, target, false, true));
				
				return sb.toString();
					
			} else if(item.getItemType().equals(ItemType.ELIXIR)) {
				sb.append(UtilText.parse(user, target,
						"<p>"
							+ "[npc.Name]从物品栏取出"+item.getName()+"，递给了[npc2.name]。"));
				
				if(isObedientSlave) {
					sb.append(UtilText.parse(user, target, 
								"[npc2.she]顺从地听从了要求，欣然从[npc.Name]那里接下了装有转化液体的瓶子，说道，"
								+ "[npc2.speech(我自然会忠于职守，按您的喜好进行转化……)]"
							+ "</p>"
							+ "<p>"
								+ "[npc2.she]为了取悦主人，连忙起开了瓶塞，便迫不及待地送到[npc2.lips]边，大口大口地豪饮起来。"
								+ "喝完后[npc2.She]咳嗽了一阵子，便感到液体的效果正在其身体深处起效，不禁倒吸了一口凉气……"
							+ "</p>"));
					
				} else if(target.getSubspeciesOverrideRace()==Race.DEMON) {
					sb.append(UtilText.parse(user, target,
						"<p>"
							+ "[npc.name]从物品栏取出"+item.getName()+"，递给了[npc2.name]。"
							+ "[npc2.she]看到递过来的是什么东西后，便嘲笑起来，"
							+ "[npc2.speech(哈！你不知道恶魔没法——~唔嗯！~)]"
						+ "</p>"
						+ "<p>"
							+ "[npc2.namePos]一开口[npc.Name]就恼火起来，立马起开了瓶塞，粗鲁地给对方灌了下去。"
							+ "[npc.Name]捏住[npc2.her]的鼻子，紧紧控制住[npc2.herHim]，直到[npc2.name]咽下最后一滴液体，才放[npc2.herHim]离开。"
							+ "喝完后[npc2.She]咳嗽了一阵子，把嘴角残留的液体抹干净后，气恼地嘟哝起来，"
							+ "[npc2.speech(不算太难喝……)]"
						+ "</p>"));
					
							
				} else if(target.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
					sb.append(UtilText.parse(user, target, 
							"[npc2.she]看到递过来的是什么东西后，便兴高采烈地大叫起来，问道，"
							+ "[npc2.speech(这是转化灵药吧？！求你了，让我喝吧！想把我变成什么样都可以！)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.Name]听到[npc2.namePos]回应如此热情，不由得露出了一抹微笑，随后起开了瓶塞，将药剂送到那急切的[npc2.race]嘴旁。"
							+ "[npc2.name]开心地含住了瓶口，大口大口地畅饮起来。"
							+ "喝完后[npc2.She]咳嗽了一阵子，便感到液体的效果正在其身体深处起效，不禁喜极而泣……"
						+ "</p>"));
					
				} else {
					if(target.getAffection(user) < AffectionLevel.POSITIVE_FIVE_WORSHIP.getMinimumValue()) {
						sb.append(UtilText.parse(user, target, 
								"[npc2.she]看到递过来的是什么东西后，不安地晃了晃身子，问道，"
								+ "[npc2.speech(你该不是认真的吧，随便拿——~唔嗯！~)]"
							+ "</p>"
							+ "<p>"
								+ "[npc2.namePos]一开口[npc.Name]就恼火起来，立马起开了瓶塞，粗鲁地给对方灌了下去。"
								+ "[npc.Name]捏住[npc2.her]的鼻子，紧紧控制住[npc2.herHim]，直到[npc2.name]咽下最后一滴液体，才放[npc2.herHim]离开。"
								+ "喝完后[npc2.She]咳嗽了一阵子，便感到液体的效果正在其身体深处起效，不禁惊呼起来……"
							+ "</p>"));
						
					} else {
						sb.append(UtilText.parse(user, target,
								" 由于[npc2.she]非常喜欢[npc.name]，于是暂时放下了对于转化的担忧，说道，"
								+ "[npc2.speech(我当然会喝了！无论什么我都会为你做的……)]"
							+ "</p>"
							+ "<p>"
								+ "听到[npc2.namePos]心甘情愿的回复，[npc.Name]便起开了瓶塞，将药剂递给了[npc2.herHim]。"
								+ "[npc2.she]为了取悦对方，便也欣然含住瓶口，大口大口地畅饮起来。"
								+ "喝完后[npc2.She]咳嗽了一阵子，便感到液体的效果正在其身体深处起效，不免惊呼一声……"
							+ "</p>"));
					}
				}
				
				sb.append(itemOwner.useItem(item, target, false, true));
				
				return sb.toString();
					
			} else if(item.getItemType().equals(ItemType.FETISH_REFINED)) {
				sb.append(UtilText.parse(user, target,
						"<p>"
							+ "[npc.Name]从物品栏取出"+item.getName()+"，递给了[npc2.name]。"));
				
				if(isObedientSlave) {
					sb.append(UtilText.parse(user, target, 
								"[npc2.she]顺从地听从了要求，从[npc.Name]那里接下了装有改变性癖液体的瓶子，说道，"
								+ "[npc2.speech(我自然会忠于职守，变成您喜欢的那种奴隶……)]"
							+ "</p>"
							+ "<p>"
								+ "[npc2.she]为了取悦主人，连忙起开了瓶塞，便迫不及待地送到[npc2.lips]边，大口大口地豪饮起来。"
								+ "喝完后[npc2.She]咳嗽了一阵子，便感到液体的效果正在其脑海深处起效，不禁倒吸了一口凉气……"
							+ "</p>"));
					
				} else if(target.hasFetish(Fetish.FETISH_KINK_RECEIVING)) {
					sb.append(UtilText.parse(user, target, 
							"[npc2.she]看到递过来的是什么东西后，便兴高采烈地大叫起来，问道，"
							+ "[npc2.speech(能让我得到新的性癖吗？！求你了，让我喝吧！)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.Name]听到[npc2.namePos]回应如此热情，不由得露出了一抹微笑，随后起开了瓶塞，将药剂送到那急切的[npc2.race]嘴旁。"
							+ "[npc2.name]开心地含住了瓶口，大口大口地畅饮起来。"
							+ "喝完后[npc2.She]咳嗽了一阵子，便感到液体的效果正在其脑海深处起效，不禁喜极而泣……"
						+ "</p>"));
					
				} else {
					if(target.getSubspeciesOverrideRace()==Race.DEMON) {
						sb.append(UtilText.parse(user, target, 
								"[npc2.she]看到递过来的是什么东西后，便嘲笑起来，"
								+ "[npc2.speech(哈！你不知道恶魔没法——~唔嗯！~)]"
							+ "</p>"
							+ "<p>"
								+ "[npc2.namePos]一开口[npc.Name]就恼火起来，立马起开了瓶塞，粗鲁地给对方灌了下去。"
								+ "[npc.Name]捏住[npc2.her]的鼻子，紧紧控制住[npc2.herHim]，直到[npc2.name]咽下最后一滴液体，才放[npc2.herHim]离开。"
								+ "喝完后[npc2.She]咳嗽了一阵子，把嘴角残留的液体抹干净后，发出一声淫荡的[npc2.moan]，"
								+ "[npc2.speech(~啊啊！~对了，这是性癖转化药吧？！~额啊！~身体好热……)]"
							+ "</p>"));
						
					} else {
						if(target.getAffection(user) < AffectionLevel.POSITIVE_FIVE_WORSHIP.getMinimumValue()) {
							sb.append(UtilText.parse(user, target, 
									"[npc2.she]看到递过来的是什么东西后，不安地晃了晃身子，问道，"
									+ "[npc2.speech(你该不是认真的吧，随便拿——~唔嗯！~)]"
								+ "</p>"
								+ "<p>"
									+ "[npc2.namePos]一开口[npc.Name]就恼火起来，立马起开了瓶塞，粗鲁地给对方灌了下去。"
									+ "[npc.Name]捏住[npc2.her]的鼻子，紧紧控制住[npc2.herHim]，直到[npc2.name]咽下最后一滴液体，才放[npc2.herHim]离开。"
									+ "喝完后[npc2.She]咳嗽了一阵子，便感到液体的效果正在其脑海深处起效，不禁惊呼起来……"
								+ "</p>"));
							
						} else {
							sb.append(UtilText.parse(user, target,
									" 由于[npc2.she]非常喜欢[npc.name]，于是暂时放下了对于转化的担忧，说道，"
										+ "[npc2.speech(我当然会喝了！无论什么我都会为你做的……)]"
								+ "</p>"
								+ "<p>"
									+ "听到[npc2.namePos]心甘情愿的回复，[npc.Name]便起开了瓶塞，将药剂递给了[npc2.herHim]。"
									+ "[npc2.she]为了取悦对方，便也欣然含住瓶口，大口大口地畅饮起来。"
									+ "喝完后[npc2.She]咳嗽了一阵子，便感到液体的效果正在其脑海深处起效，不免惊呼一声……"
								+ "</p>"));
						}
					}
				}
				sb.append(itemOwner.useItem(item, target, false, true));
				
				return sb.toString();
				
			} else if(item.getItemType().equals(ItemType.POTION) || item.getItemType().equals(ItemType.EGGPLANT_POTION) || item.getItemType().equals(ItemType.MOTHERS_MILK) || item.getItemType().equals(ItemType.FETISH_UNREFINED)) {
				if(isObedientSlave) {
					sb.append(UtilText.parse(user, target,
							"<p>"
								+ "[npc.name]从物品栏取出"+item.getName()+"，递给了[npc2.name]。"
								+ "[npc2.she]顺从地听从了要求，欣然从[npc.Name]那里接下了装有液体的瓶子，说道，"
								+ "[npc2.speech(您无论给我什么，我都会喝的……)]"
							+ "</p>"
							+ "<p>"
								+ "[npc2.she]为了取悦主人，连忙起开了瓶塞，便迫不及待地送到[npc2.lips]边，大口大口地豪饮起来。"
								+ "喝完后[npc2.She]咳嗽了一阵子，便感到液体正在起效了，不禁倒吸了一口凉气……"
							+ "</p>"));
					
				} else {
					sb.append(UtilText.parse(user, target,
							"<p>"
								+ "[npc.name]从物品栏取出"+item.getName()+"，递给了[npc2.name]。"
								+ "[npc2.she]看到递过来的是什么东西后，不安地晃了晃身子，问道，"
								+ "[npc2.speech(你该不是认真的吧，随便拿——~唔嗯！~)]"
							+ "</p>"
							+ "<p>"
								+ "[npc2.namePos]一开口[npc.Name]就恼火起来，立马起开了瓶塞，粗鲁地给对方灌了下去。"
								+ "[npc.Name]捏住[npc2.her]的鼻子，紧紧控制住[npc2.herHim]，直到[npc2.name]咽下最后一滴液体，才放[npc2.herHim]离开。"
								+ "喝完后[npc2.She]咳嗽了一阵子，便感到液体正在起效了，不禁惊呼起来……"
							+ "</p>"));
				}
				sb.append(itemOwner.useItem(item, target, false, true));

				return sb.toString();
				
			} else if(item.getItemType().equals(ItemType.EGGPLANT)) {
				if(isObedientSlave) {
					sb.append(UtilText.parse(user, target,
							"<p>"
								+ "[npc.Name]从物品栏中取出茄子，递给了[npc2.name]。"
								+ "[npc2.she]看到递过来的是什么东西后，顺从地接了下来，说道，"
								+ "[npc2.speech(您无论给我什么，我都会吃的……)]"
							+ "</p>"
							+ "<p>"
								+ "[npc2.she]为了取悦主人，连忙把这紫色的水果送到[npc2.lips]边，吃得干干净净……"
							+ "</p>"));
					
				} else {
					sb.append(UtilText.parse(target,
							"<p>"
								+ "[npc.Name]从物品栏中取出茄子，递给了[npc2.name]。"
								+ "[npc2.she]看到递过来的是什么东西后，不安地晃了晃身子，问道，"
								+ "[npc2.speech(你拿那个是要——~唔嗯！~)]"
							+ "</p>"
							+ "<p>"
								+ "[npc2.namePos]一开口[npc.Name]就恼火起来，立马把茄子塞进了[npc2.her]的嘴巴，强迫[npc2.herHim]把这紫色的水果吃得干干净净……"
							+ "</p>"));
				}
				sb.append(itemOwner.useItem(item, target, false, true));

				return sb.toString();
				
			} else {
				return itemOwner.useItem(item, target, false);
			}
			
		} else { // Self-using:
			return itemOwner.useItem(item, target, false);
		}
	}
}
