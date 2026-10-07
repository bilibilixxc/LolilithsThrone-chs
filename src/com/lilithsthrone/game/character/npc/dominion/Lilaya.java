package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import org.w3c.dom.Document;
import com.lilithsthrone.threading.DocBuilders;import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounter;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.sexActions.dominion.SALilayaSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4.4.1
 * @author Innoxia
 */
public class Lilaya extends NPC {
	
	public Lilaya() {
		this(false);
	}
	
	public Lilaya(boolean isImported) {
		super(isImported, new NameTriplet("莉莱雅"), "莉西丝马尔图萨莉",
				"除了你的双胞胎表亲，你的莉莉姨妈就是你唯一的亲人了。"
						+ "虽然她依然存在于这个世界上，但她已经不再是你的姨妈了。现在，她是一个叫“莉莱雅”的半恶魔。"
						+ "你曾经的姨妈是市立博物馆的研究者，而莉莱雅则是一位私人资助的奥术研究者。"
						+ "由于她的恶魔外表以及她是莉琳·莉西丝的女儿之一，人们通常对莉莱雅既害怕又尊敬。",
				48, Month.DECEMBER, 28, // Note that Lilaya's age is always set to 22 years older than the player in CharacterCreation.applyGameStart(), so the age of 48 here doesn't mean much.
				25,
				Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.PARTIAL_FULL,
				new CharacterInventory(false, 10),
				WorldType.LILAYAS_HOUSE_FIRST_FLOOR,
				PlaceType.LILAYA_HOME_ROOM_LILAYA,
				true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.12")) {
			this.setAgeAppearanceAbsolute(32);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			this.setStartingBody(true);
			this.setLegType(LegType.HUMAN);
		}

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.6")) {
			this.setWingSize(WingSize.THREE_LARGE.getValue());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3")) {
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(25);
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.10")) {
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.4")) {
			this.equipClothing();
			if(this.getSubspecies()!=Subspecies.DEMON) {
				setupCoverings(this.getCovering(BodyCoveringType.HUMAN).getPrimaryColour());
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.4.9")) {
			if(this.getSubspecies()==Subspecies.DEMON) {
				this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.SHY);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.7")) {
			this.setTailGirth(PenetrationGirth.FIVE_THICK);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.20")) {
			this.setHomeLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_LILAYA);
		}
	}

	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.12")) {
			this.setAgeAppearanceAbsolute(32);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			this.setStartingBody(true);
			this.setLegType(LegType.HUMAN);
		}

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.6")) {
			this.setWingSize(WingSize.THREE_LARGE.getValue());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3")) {
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(25);
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.10")) {
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.4")) {
			this.equipClothing();
			if(this.getSubspecies()!=Subspecies.DEMON) {
				setupCoverings(this.getCovering(BodyCoveringType.HUMAN).getPrimaryColour());
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.4.9")) {
			if(this.getSubspecies()==Subspecies.DEMON) {
				this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.SHY);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.7")) {
			this.setTailGirth(PenetrationGirth.FIVE_THICK);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.20")) {
			this.setHomeLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_LILAYA);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 5)));
	}
	
	private void setupCoverings(Colour humanSkinColour) {
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_YELLOW));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);

		this.setAssType(AssType.DEMON_COMMON);
		this.setBreastType(BreastType.DEMON_COMMON);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, humanSkinColour), true);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, humanSkinColour), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, humanSkinColour), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, humanSkinColour), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, humanSkinColour), false);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, humanSkinColour), false);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMaximumValue());
		this.setHairStyle(HairStyle.LOOSE);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.THREE_TRIMMED);
		this.setFacialHair(BodyHair.ZERO_NONE);
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.SHY);
		}
		
		if(setFetishes) {
			this.clearFetishDesires();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_MASOCHIST);
			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ZERO_HATE);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_ARCANE_RESEARCHER);
		}
		
		if(setSpells) {
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			setStartingPersona();
		}
		
		// Body:

		// Core:
		this.setSubspeciesOverride(Subspecies.HALF_DEMON);
		this.setAgeAppearanceAbsolute(32);
		this.setWingType(WingType.DEMON_COMMON);
		this.setWingSize(WingSize.THREE_LARGE.getValue());
		this.setHornType(HornType.SWEPT_BACK);
		this.setTailType(TailType.DEMON_COMMON);
		this.setTailGirth(PenetrationGirth.FIVE_THICK);

		this.setHeight(180);
		this.setFemininity(85);
		this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		setupCoverings(Main.game.getPlayer().getCovering(BodyCoveringType.HUMAN).getPrimaryColour());

//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_AMBER));
//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_AMBER));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.E.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		// Anus settings and modifiers
		
		// Penis:
		// For when she grows one:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
		this.setPenisSize(15);
		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
		this.setPenisCumStorage(65);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.FOUR_SLIMY);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		this.setHairStyle(HairStyle.LOOSE);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_fullcup_bra", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_pencil_skirt", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_torso_feminine_short_sleeve_shirt"), PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_GREY, false), true, this);
		
		AbstractClothing labCoat = Main.game.getItemGen().generateClothing("innoxia_scientist_lab_coat", PresetColour.CLOTHING_WHITE, false);
		this.equipClothingFromNowhere(labCoat, true, this);
		this.isAbleToBeDisplaced(this.getClothingInSlot(InventorySlot.TORSO_OVER), DisplacementType.UNBUTTONS, true, true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_stockings", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.setPiercedEar(true);
	}
	
	public void applyGeishaChange() {
		Main.game.getNpc(Lilaya.class).resetInventory(false);
		this.setHairStyle(HairStyle.LOOSE);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_japanese_kanzashi", PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_PURPLE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_japanese_kimono", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_PURPLE, PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_japanese_geta", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_PINK, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
	}
	
	public void applyDinnerDateChange() {
		Main.game.getNpc(Lilaya.class).resetInventory(false);
		this.setHairStyle(HairStyle.PONYTAIL);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_plunge_dress", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_stiletto_heels", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_pearl_studs", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK_STEEL, null, false), true, this);
		AbstractClothing scrunchie = Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_scrunchie", PresetColour.CLOTHING_RED_VERY_DARK, false);
		scrunchie.setPattern("none");
		this.equipClothingFromNowhere(scrunchie, true, this);
		
	}

	public boolean isCondomBroke() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaCondomBroke);
	}

	public boolean isAmazonsSecretImpregnation() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaAmazonsSecretImpregnation);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	// Prevent issues with Geisha Lilaya immediately backing out of submissive sex:
	// I think it might also be due to demonic player characters being related to Lilaya and so as she doesn't have the incest fetish she hates it (same for Meraxis)
	@Override
	public boolean isAttractedTo(GameCharacter character) {
		return true;
	}

	@Override
	public String getArtworkFolderName() {
		if(this.getRaceStage()==RaceStage.GREATER) {
			return "LilayaDemon";
		}
		
		Colour skinColour = this.getCovering(BodyCoveringType.HUMAN).getPrimaryColour();
		if(skinColour==PresetColour.SKIN_PORCELAIN
				|| skinColour==PresetColour.SKIN_PALE) {
			return "LilayaPale";
			
		} else if(skinColour==PresetColour.SKIN_TANNED
				|| skinColour==PresetColour.SKIN_OLIVE) {
			return "LilayaOlive";
			
		} else if(skinColour==PresetColour.SKIN_CHOCOLATE
				|| skinColour==PresetColour.SKIN_DARK) {
			return "LilayaDark";
			
		} else if(skinColour==PresetColour.SKIN_EBONY) {
			return "LilayaEbony";
		}
		return "LilayaLight";
	}

	@Override
	public String setSkinCovering(Covering covering, boolean updateAllSkinColours) {
		String returnValue = super.setSkinCovering(covering, updateAllSkinColours);
		if (covering.getType() == BodyCoveringType.HUMAN) {
			// Reload images when the skin changes
			loadImages();
		}
		return returnValue;
	}
	
	@Override
	public String getSpeechColour() {
		return "#ff66a3";
	}
	
	@Override
	public void changeFurryLevel(){
	}

	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this) && !Main.game.getCurrentDialogueNode().isTravelDisabled()) {
			if(Main.game.isExtendedWorkTime()) {
				this.setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
			} else {
				this.setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE);
			}
		}
	}
	
	@Override
	public Set<Relationship> getRelationshipsTo(GameCharacter character, Relationship... excludedRelationships) {
		if(character.isPlayer()) {
			Set<Relationship> rSet = new LinkedHashSet<>();
			rSet.add(Relationship.Pibling);
			if(Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")) {
				rSet.add(Relationship.HalfSibling);
				return rSet;
			}
			return rSet;
		}
		return super.getRelationshipsTo(character, excludedRelationships);
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void endSex() {
		this.setPenisType(PenisType.NONE);
		this.setVaginaType(VaginaType.DEMON_COMMON);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	public void growCock() {
		this.setPenisType(PenisType.DEMON_COMMON);
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setPenisSize(28);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setInternalTesticles(false);
		this.setPenisCumStorage(2000);
		this.fillCumToMaxStorage();
	}
	
	@Override
	public Value<Boolean, String> getItemUseEffects(AbstractItem item, GameCharacter itemOwner, GameCharacter user, GameCharacter target) {
		if(user.isPlayer() && !target.isPlayer() && item.isTypeOneOf("innoxia_pills_fertility", "innoxia_pills_broodmother")) {
			if(this.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
				itemOwner.removeItem(item);
				return new Value<>(false,
						"<p>"
							+ "你从物品栏中拿出一个"+item.getColour(0).getName()+item.getName(false, false)+"，从塑料包装中取出，然后递到了莉莱雅嘴边。"
							+ "她看到你要为她吃下的东西，顿时皱起了眉头，把药片从你[pc.hand]中拍掉了，落在了某张实验桌下。"
							+ "她用尖锐的语气告诫你，"
							+ (this.hasPenis()
									?"[lilaya.speech(我不管你是不是为了我的精液的活力！我是<i>绝对不可能</i>吃下那种会让我更容易怀孕的东西的！)]"
									:"[lilaya.speech(我<i>绝对不可能</i>吃这种更容易让我怀孕的东西！你不是<i>不会</i>射在里面的吗，为什么还要这么做？！)]")
						+ "</p>");
				
			} else {
				itemOwner.useItem(item, this, false);
				return new Value<>(true,
						"<p>"
							+ "你从物品栏中拿出一个"+item.getName(false, false)+"，打开塑料包装后塞到莉莱雅嘴里。"
							+ "她愉悦地呻吟了一声，欣然咽下了那"+ item.getColour(0).getName() +"的小药片，"
								+ "[lilaya.speechNoEffects(~姆嗯！~对了对了，要让我的恶魔子宫健康又强大！怀孕已经不再需要憎恶了……)]"
						+ "</p>");
			}
		}
		return super.getItemUseEffects(item, itemOwner, user, target);
	}
	
	@Override
	public String getAttributeChangeText(AbstractAttribute att, float value) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(super.getAttributeChangeText(att, value));
		
		if(this.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
			if(att==Attribute.FERTILITY && value>0) {
				sb.append(UtilText.parse(this,
						"<p>"
							+ "[lilaya.speech(等等……我这下更容易怀孕了？！你怎么想的？！)]"
							+ "莉莱雅生气地大叫起来，过了一会儿又长叹一声，嘟哝道，"
							+ "[lilaya.speech(罢了……反正也没区别……)]"
						+ "</p>"));
				
			} else if(att==Attribute.FERTILITY && value<0) {
				sb.append(UtilText.parse(this,
						"<p>"
							+ "[lilaya.speech(等等……我更不容易怀孕了？！太感谢你了[pc.name]！)]"
							+ "莉莱雅高兴地大叫起来，随后却又叹道，"
							+ "[lilaya.speech(不过也没有什么区别，但你的想法是好的……)]"
						+ "</p>"));
			}
		}
		
		return sb.toString();
	}
	
	@Override
	public String getPotionAttributeChangeText(AbstractAttribute att, float value) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(super.getPotionAttributeChangeText(att, value));
		
		if(this.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
			if(att==Attribute.FERTILITY && value>0) {
				sb.append(UtilText.parse(this,
						"<p>"
							+ "[lilaya.speech(等等……我这下更容易怀孕了？！你怎么想的？！)]"
							+ "莉莱雅生气地大叫起来，过了一会儿又长叹一声，嘟哝道，"
							+ "[lilaya.speech(罢了……反正也没区别……)]"
						+ "</p>"));
				
			} else if(att==Attribute.FERTILITY && value<0) {
				sb.append(UtilText.parse(this,
						"<p>"
							+ "[lilaya.speech(等等……我更不容易怀孕了？！太感谢你了[pc.name]！)]"
							+ "莉莱雅高兴地大叫起来，随后却又叹道，"
							+ "[lilaya.speech(不过也没有什么区别，但你的想法是好的……)]"
						+ "</p>"));
			}
		}
		
		return sb.toString();
	}
	
	// Sex:
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(SALilayaSpecials.class);
	}

	/**
	 * @return A <b>non-formatted</b> String of this NPCs speech related to no ongoing penetration.
	 */
	@Override
	public String getDirtyTalkNoPenetration(GameCharacter target, boolean isPlayerDom){
		List<String> speech = new ArrayList<>();

		speech.add("操，为什么恶魔总是这么饥渴？！我满脑子想的都是和你或者萝丝做爱！");
		speech.add("我肯定能从中收集到一些有价值的数据……");
		
		if(Main.game.isIncestEnabled()) {
			if(Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")) {
				speech.add("对你新的半亲姐妹饥渴难耐吗，嗯？");
				speech.add("兄弟姐妹之间的性交对恶魔来说并没有什么不对……");
			} else {
				speech.add("不知道你有没有和你的真姨妈这样做过？");
				speech.add("等等，你还是把我看作你的姨妈，对吧？我想我可以同意……");
			}
		}
		
		String returnedLine = speech.get(Util.random.nextInt(speech.size()));
		return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
	}

	
	// Bad end content:
	
	public void applyDollificationBadEndTattoos() {
		this.addTattoo(InventorySlot.TORSO_OVER,
				new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_BLACK,
						false,
						new TattooWriting("拉住我的头发！", PresetColour.CLOTHING_BLACK, false),
						null));
		this.addTattoo(InventorySlot.TORSO_UNDER,
				new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_BLACK,
						false,
						new TattooWriting("扇我的屁股！", PresetColour.CLOTHING_BLACK, false),
						null));
		this.addTattoo(InventorySlot.ANUS,
				new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_BLACK,
						false,
						new TattooWriting("屁穴妓女", PresetColour.CLOTHING_BLACK, false),
						new TattooCounter(TattooCounterType.ANUS_FUCKED, TattooCountType.TALLY, PresetColour.CLOTHING_BLACK, false, this)));
		this.addTattoo(InventorySlot.CHEST,
				new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_BLACK,
						false,
						new TattooWriting("操我的大奶头", PresetColour.CLOTHING_BLACK, false),
						new TattooCounter(TattooCounterType.NIPPLES_FUCKED, TattooCountType.TALLY, PresetColour.CLOTHING_BLACK, false, this)));
		this.addTattoo(InventorySlot.GROIN,
				new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_BLACK,
						false,
						new TattooWriting("免费操干", PresetColour.CLOTHING_BLACK, false),
						new TattooCounter(TattooCounterType.PUSSY_FUCKED, TattooCountType.TALLY, PresetColour.CLOTHING_BLACK, false, this)));
		this.addTattoo(InventorySlot.STOMACH,
				new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_BLACK,
						false,
						new TattooWriting("奇亚里莱克斯的专属子宫", PresetColour.CLOTHING_BLACK, false),
						null));
		this.addTattoo(InventorySlot.LEG,
				new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_BLACK,
						false,
						new TattooWriting("性奴", PresetColour.CLOTHING_BLACK, false),
						null));
		this.addTattoo(InventorySlot.FOOT,
				new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_BLACK,
						false,
						new TattooWriting("射在我的脚上", PresetColour.CLOTHING_BLACK, false),
						new TattooCounter(TattooCounterType.CUM_TAKEN_FEET, TattooCountType.TALLY, PresetColour.CLOTHING_BLACK, false, this)));
		this.addTattoo(InventorySlot.NECK,
				new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_BLACK,
						false,
						new TattooWriting("属于奇亚里莱克斯", PresetColour.CLOTHING_BLACK, false),
						null));
		this.addTattoo(InventorySlot.VAGINA,
				new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_BLACK,
						false,
						new TattooWriting("储精垃圾场", PresetColour.CLOTHING_BLACK, false),
						new TattooCounter(TattooCounterType.CUM_TAKEN_PUSSY, TattooCountType.TALLY, PresetColour.CLOTHING_BLACK, false, this)));
		
		// Increment stats for tattoo counters:
		applyDemonSexCounts(new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), 1);
		applyDemonSexCounts(new SexType(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), 0.75f);
		applyDemonSexCounts(new SexType(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), 0.8f);
		applyDemonSexCounts(new SexType(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS), 0.25f);
		applyDemonSexCounts(new SexType(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS), 0.25f);
	}

	private void applyDemonSexCounts(SexType sexType, float frequency) {
		// Fucked by up to 30-50 different centauresses in this SexType:
		int demonPartnerCount = (int) ((30 + Util.random.nextInt(50)) * frequency);
		
		for(int i=0; i<demonPartnerCount; i++) {
			String demonId = "partyDemon"+i;
			int sexCount = 1 + Util.random.nextInt(3);
			this.setSexAsSubCountById(demonId, sexCount);
			this.setSexCountById(demonId, sexType, sexCount);
			this.setCumCountById(demonId, sexType, (Math.random()<0.75f?1:0)+Util.random.nextInt(sexCount));
		}
	}

}