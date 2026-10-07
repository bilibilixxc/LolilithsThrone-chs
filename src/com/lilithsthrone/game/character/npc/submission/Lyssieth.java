package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import org.w3c.dom.Document;
import com.lilithsthrone.threading.DocBuilders;import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMLilayaDemonTF;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethDemonTF;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionOrgasmOverride;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.submission.SALyssiethSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.12
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Lyssieth extends NPC {

	public Lyssieth() {
		this(false);
	}
	
	public Lyssieth(boolean isImported) {
		super(isImported,
				new NameTriplet("莉西丝"), "莉莉丝马尔图拉尼",
				"莉西丝是最为强大的七位莉琳长老之一。",
				7734, Month.OCTOBER, 13,
				1000,
				Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER,
				new CharacterInventory(false, 10),
				WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE,
				true);
		
		if(!isImported) {
			this.setPlayerKnowsName(true);
			//TODO spells
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3")) {
			this.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER, false);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.5")) {
			this.setStartingBody(true);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(1000);
			this.setHistory(Occupation.NPC_ELDER_LILIN);
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setStartingBody(false);
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.setTailGirth(PenetrationGirth.FOUR_GIRTHY);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.0.10")) {
			this.setStartingBody(false);
		}
	}
	
	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3")) {
			this.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER, false);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.5")) {
			this.setStartingBody(true);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(1000);
			this.setHistory(Occupation.NPC_ELDER_LILIN);
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setStartingBody(false);
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.setTailGirth(PenetrationGirth.FOUR_GIRTHY);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.0.10")) {
			this.setStartingBody(false);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 1)));
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.LEWD);
		}
		
		if(setFetishes) {
			this.clearFetishDesires();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_INCEST);
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);

			this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_KINK_GIVING, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.ZERO_HATE);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_ELDER_LILIN);
		}
		
		if(setSpells) {
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			setStartingPersona();
		}
		
		this.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER, false);
		
		this.setPiercedEar(true);
		AbstractClothing earrings = null;
		for(Entry<AbstractClothing, Integer> c : this.getAllClothingInInventory().entrySet()) {
			if(c.getKey().getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_piercing_ear_ring"))) {
				earrings = c.getKey();
			}
		}
		if(earrings!=null) {
			this.equipClothingFromInventory(earrings, true, this, this);
		}
		
		// Body:
		this.setSubspeciesOverride(Subspecies.ELDER_LILIN);
		this.setAgeAppearanceAbsolute(45);
//		this.setTailType(TailType.DEMON_COMMON);
//		this.setWingType(WingType.DEMON_COMMON);
//		this.setHornType(HornType.CURLED);
		this.setTailGirth(PenetrationGirth.FOUR_GIRTHY);

		// Core:
		this.setHeight(184);
		this.setFemininity(100);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_GREEN));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, PresetColour.EYE_YELLOW, true, PresetColour.EYE_YELLOW, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMaximumValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED_DARK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED_DARK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.THREE_PLUMP);
		this.setFaceCapacity(Capacity.SEVEN_GAPING, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.GG.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		this.clearAssOrificeModifier();
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisType(PenisType.NONE);
		this.clearPenisModifiers();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FOUR_LOOSE, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.clearVaginaOrificeModifiers();
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.resetInventory(true);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_half_rim_glasses", PresetColour.CLOTHING_BROWN_VERY_DARK, PresetColour.CLOTHING_BRASS, PresetColour.CLOTHING_GREY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_plunge_blouse", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_asymmetrical_skirt", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_strappy_sandals", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_thong", PresetColour.CLOTHING_RED_DARK, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_SILVER, false), true, this);
	}

	@Override
	public Colour getSpeechGlowColour() {
		if(this.getTorsoType().getRace()==Race.DEMON) {
			return PresetColour.BASE_PINK_LIGHT;
		}
		return null;
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#71009E";
		}
		if(this.getTorsoType().getRace()==Race.DEMON) {
			return "#FF99F8";
		}
		return "#E194FF";
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public int getLevel() {
		return 1000;
	}
	
//	@Override
//	public void turnUpdate() {
//		if(!Main.game.getCharactersPresent().contains(this)) {
//			this.setStartingBody(false);
//		}
//	}
	
	@Override
	protected Set<GameCharacter> getChildren() {
		Set<GameCharacter> children = super.getChildren();
		
		children.add(Main.game.getNpc(Lilaya.class));
		children.add(Main.game.getNpc(DarkSiren.class));
		if(Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")) {
			children.add(Main.game.getPlayer());
		}
		
		return children;
	}

	@Override
	public String getArtworkFolderName() {
		if(this.getTorsoType().getRace()==Race.HUMAN) {
			return "LyssiethHuman";
			
		} else {
			return "LyssiethDemon";
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(SALyssiethSpecials.class);
	}

	private void setPlayerToPartialDemon() {
		Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);
		Main.game.getPlayer().setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, PresetColour.EYE_YELLOW, false, PresetColour.EYE_YELLOW, false));
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);
		
		Main.game.getPlayer().setTailType(TailType.DEMON_COMMON);
		Main.game.getPlayer().setTailGirth(PenetrationGirth.FOUR_GIRTHY);
		Main.game.getPlayer().setHornType(HornType.SWEPT_BACK);
		Main.game.getPlayer().setHornLength(HornLength.ONE_SMALL.getMedianValue());
		Main.game.getPlayer().setMinimumHornsPerRow(2);
		Main.game.getPlayer().getLegConfiguration().setWingsToDemon(Main.game.getPlayer());
		Main.game.getPlayer().setEarType(EarType.DEMON_COMMON);
		Main.game.getPlayer().setEyeType(EyeType.DEMON_COMMON);
		Main.game.getPlayer().setHairType(HairType.DEMON);
	}
	
	private void setPlayerToFullDemon() {
		Main.game.getPlayer().setTorsoType(TorsoType.DEMON_COMMON);
		Main.game.getPlayer().setFaceType(FaceType.DEMON_COMMON);
		Main.game.getPlayer().setSubspeciesOverride(Subspecies.DEMON);
		Main.game.getPlayer().setArousal(100, true);
		if(Main.game.getPlayer().hasPenis()) {
			Main.game.getPlayer().fillCumToMaxStorage();
		}
		
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
		
		// Change perk to demon version:
		Main.game.getPlayer().handleDemonicTransformationPerkEffects();
	}

	public void setDaughterToFullDemon(Class<? extends NPC> daughterClass) {
		setDaughterToFullDemon(daughterClass, true);
	}
	
	public void setDaughterToFullDemon(Class<? extends NPC> daughterClass, boolean includeArousalChanges) {
		setDaughterDemonicBodyParts(Main.game.getNpc(daughterClass));
		
		if(includeArousalChanges) {
			Main.game.getNpc(Lilaya.class).setArousal(100);
			Main.game.getPlayer().setArousal(100, true);
			if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(Main.game.getNpc(DarkSiren.class))) {
				Main.game.getNpc(DarkSiren.class).setArousal(100);
			}
		}
		
		Main.game.getNpc(daughterClass).loadImages(true);
	}
	
	public static void setDaughterDemonicBodyParts(GameCharacter daughter) {
		daughter.setAssType(AssType.DEMON_COMMON);
		daughter.setBreastType(BreastType.DEMON_COMMON);
		daughter.setArmType(ArmType.DEMON_COMMON);
		daughter.getLegConfiguration().setLegsToDemon(daughter);
		daughter.setTorsoType(TorsoType.DEMON_COMMON);
		daughter.setFaceType(FaceType.DEMON_COMMON);
		daughter.setSubspeciesOverride(Subspecies.DEMON);

		daughter.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		daughter.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		daughter.setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		daughter.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		daughter.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
		daughter.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
	}
	
	@Override
	public SexActionOrgasmOverride getSexActionOrgasmOverride(SexActionInterface sexAction, OrgasmCumTarget target, boolean applyExtraEffects, String description) {
		if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) { // Vision scene:
			StringBuilder sb = new StringBuilder();
			if(description!=null) {
				sb.append(description);
			} else {
				sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			}
			
			Main.sex.addRemoveEndSexAffection(Main.game.getNpc(Lyssieth.class));
			
			// Gaining Lyssieth's power:
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_D_MEETING_A_LILIN) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "SEX_ORGASM_GAINING_POWER"));
			}
			
			return new SexActionOrgasmOverride(true) {
				@Override
				public String getDescription() {
					return sb.toString();
				}
				@Override
				public void applyEffects() {
					if(applyExtraEffects) {
						Main.game.getPlayer().setArousal(50);
					}
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(250, false));
				}
			};
			
		} else if(Main.sex.getSexManager() instanceof SMLyssiethDemonTF) { // Demon TF scene:
			StringBuilder sb = new StringBuilder();
			if(description!=null) {
				sb.append(description);
			} else {
				sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			}
			
			Main.sex.addRemoveEndSexAffection(Main.game.getNpc(Lyssieth.class));
			
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==0) {
				// Stage 1) Player is sucking Lyssieth's cock:
				if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.MOUTH)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GIVING_LYSSIETH_BLOWJOB"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToPartialDemon();
								if(Main.game.getPlayer().isFeminine()) {
									Main.game.getPlayer().incrementFemininity(20);
								} else if(Main.game.getPlayer().getFemininityValue()<Femininity.MASCULINE.getMaximumFemininity()){
									Main.game.getPlayer().setFemininity(Femininity.MASCULINE.getMaximumFemininity());
								} else if(Main.game.getPlayer().getFemininityValue()<Femininity.ANDROGYNOUS.getMaximumFemininity()){
									Main.game.getPlayer().setFemininity(Femininity.ANDROGYNOUS.getMaximumFemininity());
								}
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
					
				// Stage 1) Player is eating Lyssieth out:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getPlayer()).contains(SexAreaPenetration.TONGUE)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GIVING_LYSSIETH_CUNNILINGUS"));
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToPartialDemon();
								if(Main.game.getPlayer().isFeminine()) {
									Main.game.getPlayer().incrementFemininity(20);
								} else if(Main.game.getPlayer().getFemininityValue()<Femininity.MASCULINE.getMaximumFemininity()){
									Main.game.getPlayer().setFemininity(Femininity.MASCULINE.getMaximumFemininity());
								} else if(Main.game.getPlayer().getFemininityValue()<Femininity.ANDROGYNOUS.getMaximumFemininity()){
									Main.game.getPlayer().setFemininity(Femininity.ANDROGYNOUS.getMaximumFemininity());
								}
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
						
				// Stage 1) Lyssieth is sucking player's cock:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.MOUTH, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GETTING_BLOWJOB_FROM_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToPartialDemon();
								Main.game.getPlayer().setHornType(HornType.CURVED);
								Main.game.getPlayer().setMinimumHornsPerRow(2);
								if(!Main.game.getPlayer().isFeminine()) {
									Main.game.getPlayer().incrementFemininity(-20);
								}
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
	
				// Stage 1) Lyssieth is eating the player out:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.TONGUE, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GETTING_CUNNILINGUS_FROM_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToPartialDemon();
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
				}
				
			} else if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==1) {
				// Stage 2) Lyssieth is fucking the player:
				if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_PUSSY_FUCKED_BY_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								Main.game.getPlayer().setArmType(ArmType.DEMON_COMMON);
								Main.game.getPlayer().getLegConfiguration().setLegsToDemon(Main.game.getPlayer());
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};

				// Stage 2) Lyssieth is fucking the player's ass:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.ANUS)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_ASS_FUCKED_BY_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								Main.game.getPlayer().setArmType(ArmType.DEMON_COMMON);
								Main.game.getPlayer().getLegConfiguration().setLegsToDemon(Main.game.getPlayer());
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
					
				// Stage 2) The player is fucking Lyssieth:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_FUCKING_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								Main.game.getPlayer().setArmType(ArmType.DEMON_COMMON);
								Main.game.getPlayer().getLegConfiguration().setLegsToDemon(Main.game.getPlayer());
								Main.game.getPlayer().setArousal(100, true);
							}
						}
						@Override
						public void applyEndEffects() {
							if(applyExtraEffects) {
								Main.sex.setCreampieLockedBy(Main.game.getPlayer(), new Value<>(Main.game.getNpc(Lyssieth.class), Leg.class));
							}
						}
					};

				// Stage 2) Scissoring:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.CLIT, Main.game.getPlayer()).contains(SexAreaPenetration.CLIT)) {
					if(Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.SCISSORING) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_SCISSOR_PC_BOTTOM"));
					} else {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_SCISSOR_PC_TOP"));
					}
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								Main.game.getPlayer().setArmType(ArmType.DEMON_COMMON);
								Main.game.getPlayer().getLegConfiguration().setLegsToDemon(Main.game.getPlayer());
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
				}
				
			} else {
				// Stage 3) The player is fucking/breeding Lyssieth:
				if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
					if(Main.sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotLyingDown.MATING_PRESS) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_BREEDING_LYSSIETH"));
					} else {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_FUCKING_LYSSIETH"));
					}
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
						@Override
						public void applyEndEffects() {
							if(applyExtraEffects) {
								if(Main.sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotLyingDown.MATING_PRESS) {
									Main.sex.setCreampieLockedBy(Main.game.getPlayer(), new Value<>(Main.game.getNpc(Lyssieth.class), Leg.class));
								} else {
									Main.sex.setCreampieLockedBy(Main.game.getPlayer(), new Value<>(Main.game.getNpc(Lyssieth.class), Tail.class));
								}
							}
						}
					};

				// Stage 3) Lyssieth is fucking the player:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_PUSSY_FUCKED_BY_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
					};

				// Stage 3) Lyssieth is fucking the player's ass:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.ANUS)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_ASS_FUCKED_BY_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
					};
					
				// Stage 3) oral:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.MOUTH, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
					if(Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.SIXTY_NINE) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_SIXTY_NINE_BLOWJOB_LYSSIETH_TOP"));
					} else if(Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.LYING_DOWN) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_SIXTY_NINE_BLOWJOB_LYSSIETH_BOTTOM"));
					} else {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_GETTING_BLOWJOB_FROM_LYSSIETH"));
					}
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
					};
	
				// Stage 3) Lyssieth is eating the player out:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.TONGUE, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
					if(Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.SIXTY_NINE) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_SIXTY_NINE_CUNNILINGUS_LYSSIETH_TOP"));
					} else if(Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.LYING_DOWN) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_SIXTY_NINE_CUNNILINGUS_LYSSIETH_BOTTOM"));
					} else {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_GETTING_CUNNILINGUS_FROM_LYSSIETH"));
					}
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
					};
				}
			}
			
		} else if(Main.sex.getSexManager() instanceof SMLilayaDemonTF) { // TF Lilaya or Meraxis into full demons
			StringBuilder sb = new StringBuilder();
			if(description!=null) {
				sb.append(description);
			} else {
				sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			}

			Main.sex.addRemoveEndSexAffection(Main.game.getNpc(Lyssieth.class));
			
			// Lyssieth is fucking Lilaya's pussy:
			if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lilaya.class)).contains(SexAreaOrifice.VAGINA) && Main.game.getNpc(Lilaya.class).getRaceStage()!=RaceStage.GREATER) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "LILAYA_DEMON_TF_VAGINA"));
				
				return new SexActionOrgasmOverride(false) {
					@Override
					public String getDescription() {
						return sb.toString();
					}
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							setDaughterToFullDemon(Lilaya.class);
						}
					}
				};
				
			// Lyssieth is fucking Lilaya's ass:
			} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lilaya.class)).contains(SexAreaOrifice.ANUS) && Main.game.getNpc(Lilaya.class).getRaceStage()!=RaceStage.GREATER) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "LILAYA_DEMON_TF_ANUS"));
				
				return new SexActionOrgasmOverride(false) {
					@Override
					public String getDescription() {
						return sb.toString();
					}
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							setDaughterToFullDemon(Lilaya.class);
						}
					}
				};
			
			// Lyssieth is fucking Meraxis's ass:
			} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(DarkSiren.class)).contains(SexAreaOrifice.VAGINA) && Main.game.getNpc(DarkSiren.class).getRaceStage()!=RaceStage.GREATER) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "MERAXIS_DEMON_TF_VAGINA"));
				
				return new SexActionOrgasmOverride(false) {
					@Override
					public String getDescription() {
						return sb.toString();
					}
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							setDaughterToFullDemon(DarkSiren.class);
						}
					}
				};
				
			} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(DarkSiren.class)).contains(SexAreaOrifice.ANUS) && Main.game.getNpc(DarkSiren.class).getRaceStage()!=RaceStage.GREATER) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "MERAXIS_DEMON_TF_ANUS"));
				
				return new SexActionOrgasmOverride(false) {
					@Override
					public String getDescription() {
						return sb.toString();
					}
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							setDaughterToFullDemon(DarkSiren.class);
						}
					}
				};
			}
		}

		return super.getSexActionOrgasmOverride(sexAction, target, applyExtraEffects, description); // Normal scene
	}
	
	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.sex.getSexManager() instanceof SMLyssiethDemonTF) {
			if(Main.sex.getNumberOfOrgasms(this)==0) { // Only need to override the start, as preferences are set in the class SALyssiethSpecials after this.
				if(Main.sex.getSexPositionSlot(this)==SexSlotStanding.STANDING_DOMINANT) {
					return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
				} else {
					if(target.hasPenis()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
					} else {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
					}
				}
				
			} else if((Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.SIXTY_NINE || Main.sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotLyingDown.SIXTY_NINE)
					&& !this.hasPenis()) {
				if(Main.sex.getOngoingCharactersUsingAreas(this, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE).contains(target)) {
					if(target.hasPenis()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
					} else {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
					}
				} else {
					return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
				}
			}
		}
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.sex.getSexManager() instanceof SMLyssiethDemonTF) {
			return getForeplayPreference(target);
		}
		return super.getMainSexPreference(target);
	}
	
	@Override
	public void endSex() {
//		this.setPenisType(PenisType.NONE);
	}

	@Override
	public int getOrgasmsBeforeSatisfied() {
		return 3;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public Set<Relationship> getRelationshipsTo(GameCharacter character, Relationship... excludedRelationships) {
		if(character.isPlayer() && Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")) {
			return Util.newHashSetOfValues(Relationship.Parent);
		}
		return super.getRelationshipsTo(character, excludedRelationships);
	}
	
	public void growCock(AbstractPenisType type) {
		this.setPenisType(type);
		this.setPenisVirgin(false);
		if(type.getRace()==Race.HUMAN) {
			this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
			this.setPenisSize(18);
			this.setTesticleSize(TesticleSize.THREE_LARGE);
			this.setPenisCumStorage(500);
		} else {
			this.setPenisGirth(PenetrationGirth.FIVE_THICK);
			this.setPenisSize(30);
			this.setTesticleSize(TesticleSize.FOUR_HUGE);
			this.setPenisCumStorage(2500);
		}
		this.fillCumToMaxStorage();
	}
	
	public void setLilinBody() {
		this.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER, false);

		this.setPiercedEar(true);
		AbstractClothing earrings = null;
		for(Entry<AbstractClothing, Integer> c : this.getAllClothingInInventory().entrySet()) {
			if(c.getKey().getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_piercing_ear_ring"))) {
				earrings = c.getKey();
			}
		}
		if(earrings!=null) {
			this.equipClothingFromInventory(earrings, true, this, this);
		}
		
		// Body:
		this.setSubspeciesOverride(Subspecies.ELDER_LILIN);
		this.setAgeAppearanceAbsolute(45);
		this.setTailType(TailType.DEMON_COMMON);
		this.setTailGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setWingType(WingType.DEMON_COMMON);
		this.setWingSize(WingSize.FOUR_HUGE.getValue());
		this.setHornType(HornType.SWEPT_BACK);
		this.setHornLength(HornLength.TWO_LONG.getMedianValue());
		this.setLegType(LegType.DEMON_COMMON);

		// Core:
		this.setHeight(196);
		this.setFemininity(100);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_GREEN));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, PresetColour.EYE_YELLOW, true, PresetColour.EYE_YELLOW, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMaximumValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED_DARK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED_DARK));
//			this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//			this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.THREE_PLUMP);
		this.setFaceCapacity(Capacity.SEVEN_GAPING, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.GG.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		this.clearAssOrificeModifier();
		this.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		this.addAssOrificeModifier(OrificeModifier.RIBBED);
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.growCock(PenisType.DEMON_COMMON);
		this.clearPenisModifiers();
		this.addPenisModifier(PenetrationModifier.FLARED);
		this.addPenisModifier(PenetrationModifier.RIBBED);
		this.addPenisModifier(PenetrationModifier.PREHENSILE);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.clearVaginaOrificeModifiers();
		this.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		this.addVaginaOrificeModifier(OrificeModifier.RIBBED);
		
		// Feet:
		// Foot shape
	}
	
	public static boolean isPlayersMommy() {
		return Main.game.getPlayer().hasFetish(Fetish.FETISH_INCEST)
				&& (Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth") || (Main.game.isInSex() && Main.sex.getSexManager() instanceof SMLyssiethDemonTF));
	}
	
	@Override
	public String getDirtyTalkVaginaPenetrated(GameCharacter target, boolean isPlayerDom){
		if(!isPlayersMommy()) {
			return super.getDirtyTalkVaginaPenetrated(target, isPlayerDom);
		}
		String returnedLine = "";
		
		if(getVaginaType()!=VaginaType.NONE) {
			if(Main.sex.getFirstOngoingSexAreaPenetration(this, SexAreaOrifice.VAGINA) != null) {
				switch(Main.sex.getFirstOngoingSexAreaPenetration(this, SexAreaOrifice.VAGINA)) {
					case FINGER:
						returnedLine = UtilText.returnStringAtRandom(
								"真棒！乖[npc2.girl]！指交妈咪吧！",
								"继续！妈咪的小穴很中意你的照顾哦！",
								"真棒！妈咪喜欢被指交~");
						break;
					case PENIS:
						returnedLine = UtilText.returnStringAtRandom(
								"太棒了，操我吧。用你的大肉棒填满妈咪！继续~",
								"继续！大力操我！操妈咪吧！啊……啊~太棒了！",
								"好厉害！干我吧！妈咪喜欢你的[npc2.cock]！");
						break;
					case TAIL:
						returnedLine = UtilText.returnStringAtRandom(
								"太棒了~干我！用你的尾巴插进妈咪吧！继续~",
								"继续！大力操我！操妈咪吧！啊……啊~太棒了！",
								"好厉害！干我吧！妈咪喜欢你的[npc2.tail]！");
						break;
					case TONGUE:
						returnedLine = UtilText.returnStringAtRandom(
								"好棒哦！妈咪爱你的[npc2.tongue]！加油哦~",
								"继续，舌头舔进妈咪小穴深处吧！哦……呃，啊~",
								"真棒！尝尝妈咪的小穴哦~再深点，对，乖[npc2.girl]！");
						break;
					default:
						returnedLine =  UtilText.returnStringAtRandom(
								"妈的！",
								"好耶！",
								"哦，爽死了！");
						break;
				}
			}
		}
		
		if(returnedLine.isEmpty()) {
			return null;
		}

		return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
	}

	@Override
	public String getDirtyTalkMouthPenetrated(GameCharacter target, boolean isPlayerDom){
		if(!isPlayersMommy()) {
			return super.getDirtyTalkMouthPenetrated(target, isPlayerDom);
		}
		String returnedLine = "";

		if(Main.sex.getFirstOngoingSexAreaPenetration(this, SexAreaOrifice.MOUTH) != null) {
			switch(Main.sex.getFirstOngoingSexAreaPenetration(this, SexAreaOrifice.MOUTH)) {
				case FINGER:
					returnedLine = UtilText.returnStringAtRandom(
							"妈咪喜欢你[npc2.fingers]的味道！继续哦~",
							"接着来~妈咪喜欢吸你那[npc2.fingers]！",
							"太棒了！妈咪喜欢你[npc2.fingers]的味道！");
					break;
				case PENIS:
					returnedLine = UtilText.returnStringAtRandom(
							"真棒！妈咪喜欢你的[npc2.cock]！乖乖[npc2.girl]，操她的脸吧~",
							"继续……啊啊！用力！操妈咪的喉咙~啊……呃，啊！",
							"太棒了！妈咪爱上你的[npc2.cock]了！味道很好哦~");
					break;
				case TAIL:
					returnedLine = UtilText.returnStringAtRandom(
							"真棒！妈咪喜欢你的[npc2.tail]！乖乖[npc2.girl]，操她的脸吧~",
							"继续……啊啊！用力！操妈咪的喉咙~啊……呃，啊！",
							"太棒了！妈咪爱上你的[npc2.tail]了！味道很好哦~");
					break;
				case TONGUE:
					returnedLine = UtilText.returnStringAtRandom(
							"乖乖[npc2.girl]，妈咪看看你有多爱她呀？",
							"真棒！好[npc2.girl]！",
							"乖[npc2.girl]~妈咪喜欢你的[npc2.lips]！");
					break;
				default:
					returnedLine = UtilText.returnStringAtRandom(
							"妈的！",
							"好耶！",
							"哦，爽死了！");
					break;
			}
		}

		if(returnedLine.isEmpty()) {
			return null;
		}
		
		return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
	}

	@Override
	public String getDirtyTalkPenisPenetrating(GameCharacter target, boolean isPlayerDom){
		if(!isPlayersMommy()) {
			return super.getDirtyTalkPenisPenetrating(target, isPlayerDom);
		}
		
		List<String> availableLines = new ArrayList<>();
		
		if(!Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.PENIS, target).isEmpty()) {
			for(SexAreaOrifice orifice : Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.PENIS, target)) {
				switch(orifice) {
					case ANUS:
						availableLines.add(UtilText.returnStringAtRandom(
								"真不错，妈咪喜欢你的屁股，乖[npc2.girl]，好好含进她的肉棒吧！",
								"真棒，让妈咪操你可爱的小屁股！哦，哦，哦！",
								"真棒！好[npc2.girl]！妈咪喜欢你的屁股！"));
						break;
					case ARMPITS:
					case ASS:
						break;
					case BREAST_CROTCH:
						break;
					case BREAST:
						availableLines.add(UtilText.returnStringAtRandom(
								"好！妈咪喜欢你的[npc2.breasts]！做个好[npc2.girl]，侍奉她的鸡巴！",
								"真棒，让妈咪操你的奶子！哦，哦，哦！",
								"哦对！好[npc2.girl]！妈咪喜欢你的奶子！"));
						break;
					case MOUTH:
						availableLines.add(UtilText.returnStringAtRandom(
								"乖！做个好[npc2.girl]，继续吃妈咪的鸡巴！就是这样！",
								"哦就是这样！用你的嘴唇包裹住妈咪的肉棒！继续！",
								"就这样一直吸着妈咪的肉棒！对！乖[npc2.girl]！"));
						break;
					case NIPPLE:
						availableLines.add(UtilText.returnStringAtRandom(
								"好！含住妈咪的肉棒！乖[npc2.girl]！",
								"妈咪太爱操你的奶子了！做一个乖[npc2.girl]，好好对待我的肉棒吧~",
								"对！包住妈咪的肉棒！你的奶子操起来太棒了！"));
						break;
					case NIPPLE_CROTCH:
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						availableLines.add(UtilText.returnStringAtRandom(
								"对！含住妈咪的肉棒！你的小穴是属于我的！",
								"真棒[npc2.girl]！用你的小穴把妈咪的肉棒吞得再深点！",
								"真棒！让妈咪的鸡巴填满你的小穴吧！好[npc2.girl]！"));
						break;
					case SPINNERET:
						availableLines.add(UtilText.returnStringAtRandom(
								"真不错。含住妈咪的肉棒！你的丝囊要变成肉棒的形状了哦。",
								"乖[npc2.girl]。用你的小丝囊把妈咪的肉棒吞得再深点~",
								"真棒！让妈咪的大肉棒撑满你的丝囊吧。乖哦。"));
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		
		String returnedLine = Util.randomItemFrom(availableLines);
		return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
	}

	@Override
	public String getDirtyTalkTonguePenetrating(GameCharacter target, boolean isPlayerDom){
		if(!isPlayersMommy()) {
			return super.getDirtyTalkTonguePenetrating(target, isPlayerDom);
		}
		List<String> availableLines = new ArrayList<>();
		
		if(!Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.TONGUE, target).isEmpty()) {
			for(SexAreaOrifice orifice : Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.TONGUE, target)) {
				switch(orifice) {
					case ANUS:
						availableLines.add(UtilText.returnStringAtRandom(
								"好棒哦！妈咪喜欢你的屁股！好好体会我的[npc1.tongue]顶进深处吧！",
								"真棒！让妈咪舔你的屁股！哦，哦，哦！",
								"真棒！我喜欢舔屁股！让我把[npc1.tongue]好好深入进去！"));
						break;
					case ARMPITS:
					case ASS:
						break;
					case BREAST_CROTCH:
						break;
					case BREAST:
						availableLines.add(UtilText.returnStringAtRandom(
								"真棒哦，妈咪喜欢你的奶子！让她吸吸你的奶子吧！",
								"哦~好舒服！妈咪吸吸你的奶子！哦，哦，哦！",
								"真棒！妈咪喜欢你的奶子！"));
						break;
					case MOUTH:
						availableLines.add(UtilText.returnStringAtRandom(
								"真棒！你的嘴尝起来真甜！妈咪还想要更多！",
								"妈咪真喜欢亲你。嗯……唔唔……",
								"真棒！你的嘴尝起来真甜！"));
						break;
					case NIPPLE:
						availableLines.add(UtilText.returnStringAtRandom(
								"真棒哦，妈咪喜欢你的奶子！让她吸吸你的奶子吧！",
								"哦~好舒服！妈咪吸吸你的奶子！哦，哦，哦！",
								"太棒了！妈咪喜欢你的奶子哦。让她把[npc1.tongue]好好地插进深处吧！"));
						break;
					case NIPPLE_CROTCH:
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						availableLines.add(UtilText.returnStringAtRandom(
								"很好哦，妈咪喜欢侍弄你的小穴。乖[npc2.girl]~",
								"真好，妈咪舔舔你的下面！啊……呃，好哦……",
								"真乖。妈咪喜欢你小穴的味道哦！好好体会她的[npc1.tongue]顶进深处吧！"));
						break;
					case SPINNERET:
						availableLines.add(UtilText.returnStringAtRandom(
								"好耶！妈咪喜欢侍弄你的丝穴！乖[npc2.girl]！",
								"真棒！妈咪舔舔你的丝穴！啊……呃，好哦……",
								"真乖。妈咪喜欢你丝穴的味道哦！好好体会她的[npc1.tongue]顶进深处吧！"));
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		
		String returnedLine = Util.randomItemFrom(availableLines);
		return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
	}

	@Override
	public String getSpecialPlayerVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating, GameCharacter receivingCharacter, SexAreaOrifice penetrated) {
		if(!receivingCharacter.isPlayer() || penetrating != SexAreaPenetration.PENIS || (penetrated != SexAreaOrifice.VAGINA && penetrated != SexAreaOrifice.ANUS)) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(penetrated == SexAreaOrifice.VAGINA) {
			if(Main.game.getPlayer().hasHymen()) {
				sb.append("<p>");
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("[npc.name]把[npc.cock+]塞进你[pc.pussy+]里，你忍不住地发出渴望，颤抖的哀号。"
									+ "你一直迷恋保持成一个贞洁处女，但你几乎不敢相信你已经默许一个莉琳长老来夺走它，"
										+ "[npc.namePos][npc.cock+]夺走了你宝贵的童贞，你没时间反思自己的选择。"
									+ "你的脑海中只剩下[npc.cockGirth]恶魔般大的屌撕裂你的处女膜，攻占你的阴户时带来的痛苦。");
					} else if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
						sb.append("[npc.name]将[npc.cock+]捅进你[pc.pussy+]，撕裂你的处女膜并夺走你的贞洁，你不由自主地发出受虐狂般的淫叫。"
								+ "你被[npc.namePos]的[npc.cockGirth]驴一般的巨屌撕裂处女膜带来的剧痛彻底压垮，但你仍止不住地在狂喜之中呻吟尖叫。");
					} else {
						sb.append("[npc.name]将[npc.her][npc.cock+]塞入你[pc.pussy+]并夺走你的第一次，你忍不住地发出渴望，颤抖的哀号。"
								+ "[npc.namePos]用[npc.cockGirth]的恶魔大屌将你的处女膜撕裂，那股剧痛彻底压垮了你，你难受地扭动着，试图忍耐这种痛苦。"); 
					}
				sb.append("</p>");
				
			} else {
				sb.append("<p>");
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("[npc.name]把[npc.her][npc.cock+]塞进你[pc.pussy+]里，你忍不住发出渴望，颤抖的哀号。"
								+ "你一直迷恋保持成一个贞洁处女，但你几乎不敢相信你已经默许一个莉琳长老来夺走它，"
									+ "[npc.namePos][npc.cock+]夺走了你宝贵的童贞，你没时间反思自己的选择。"
								+ "你脑海中唯一剩下的只有你被[npc.cockGirth]的恶魔屌破处，小穴被糟蹋的事实。");
					} else {
						sb.append("[npc.namePos][npc.cock+]插入了你[pc.pussy+]，将贞操夺去，但你却禁不住发出了一声极度淫荡的[pc.moan]。"
								+ "[npc.namePos]的[npc.cockGirth]恶魔一般大的屌给予你一种前所未有的强烈快感，你不由自主地在狂喜之中不停尖叫和[pc.moan]。");
					}
				sb.append("</p>");
			}
			
			if(Main.sex.getInitialSexManager() instanceof SMLyssiethDemonTF) {
				sb.append("<p>"
						+ "[npc.name]听到你震耳欲聋的哀号，停止前顶，但你湿漉漉的小穴仍被她火热跳动的肉棒填满，她揶揄道，"
						+"[npc.speechNoExtraEffects(幸运的女孩，[pc.name]……~唔！~不是所有人都有幸被莉琳长老夺取他的贞洁，尤其对非恶魔而言……"
							+ "~哦哦！~现在像个淫荡魅魔一样跪求我的肉棒，我才会继续做下去。)]"
					+ "</p>");
			
				sb.append("<p>");
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("尽管你在意识到你失去了自己宝贵，纯洁的贞操时不禁潸然落泪，与此同时你也十分性奋。");
					} else {
						sb.append("尽管从你那被肉棒塞满的小穴升起的痛感让你萌生退意， 你也彻底性奋起来。");
					}
					sb.append("按[npc.name]说的做，喘着粗气，[pc.speechNoExtraEffects(求你了，[npc.name]…… ~啊！~给我你的肉棒，让我变成魅魔！)]");
				sb.append("</p>");
				
			} else {
				sb.append("<p>"
							+ "[npc.name]听到你震耳欲聋的哀号，停止前顶，但你湿漉漉的小穴仍被她火热跳动的肉棒填满，她揶揄道，"
							+"[npc.speechNoExtraEffects(幸运的[pc.girl]，[pc.name]……~唔！~你该知道不是所有人都有幸被莉琳长老夺取贞洁……现在，跪求我的肉棒，我才会继续做下去。)]"
						+ "</p>");
				
				sb.append("<p>");
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("尽管你在意识到你失去了自己宝贵，纯洁的贞操时不禁潸然落泪，与此同时你也十分性奋。");
					} else {
						sb.append("尽管从你那被肉棒塞满的小穴升起的痛感让你萌生退意， 你也彻底性奋起来。");
					}
					sb.append("按[npc.name]说的做，喘着粗气，[pc.speechNoExtraEffects(求你了，[npc.name]…… ~啊！~给我你的肉棒！)]");
				sb.append("</p>");
			}
			
			sb.append("<p>"
						+ "[npc.speechNoExtraEffects(真是个好女孩，)][npc.name]回道，"
							+ "[npc.she]从刚才的位置继续，缓缓将[npc.her]硕大恶魔屌的剩余部分顶入你的小穴，你尽力忍住了呜咽。"
						+ "[npc.Name]用搏动的肉棒塞满你刚破处的小穴，轻声呻吟，"
						+ "[npc.speechNoExtraEffects(就这样……~唔！~让我顶进深处……你也要开始舒服起来了……)]"
					+ "</p>"
					+ "<p>"
						+ "理所当然地，[npc.her]把肉棒拔出你的小穴，疼痛就开始褪去，"
							+ "[npc.her][npc.cockHead+]一次又一次地突入你已然失守的秘缝中，你意识到自己已经沉浸在这快感里，还发出了淫荡的呻吟……"
					+ "</p>");
			
		} else if(penetrated == SexAreaOrifice.ANUS) {
			sb.append("<p>");
				sb.append("[npc.Name]缓缓将[npc.cock+]顶入你[pc.asshole+]，夺取你的童贞，你不由自主地发出一声绝望，颤抖的哀号。"
						+ "[npc.namePos][npc.cockGirth]的恶魔屌在你的身体内缓缓打开通道的不适感使你不舒服地扭动身体。");
			sb.append("</p>");
			

			if(Main.sex.getInitialSexManager() instanceof SMLyssiethDemonTF) {
				sb.append("<p>"
						+ "[npc.name]听到你震耳欲聋的哀号，停止前顶，但你的肛门仍被她火热跳动的肉棒填满，她揶揄道，"
						+ "[npc.speechNoExtraEffects(~唔！~你该知道，不管旁人说所有魅魔都是屁穴荡妇……你也和我们一样……"
							+ "~哦哦！~现在像个肛门荡妇一样跪求我的肉棒，我才会继续做下去。)]"
					+ "</p>");
			
				sb.append("<p>");
					sb.append("发现你和[npc.name]说的一样色情，你竭尽全力去忍受这让人厌恶的饥渴呻吟，"
							+ "[pc.speechNoExtraEffects(求你了，[npc.name]…… ~啊！~我会是好的屁穴魅魔荡妇！给我你的肉棒！)]");
				sb.append("</p>");
				
			} else {
				sb.append("<p>"
							+ "[npc.name]听到你震耳欲聋的哀号，停止前顶，但你的肛门仍被她火热跳动的肉棒填满，她揶揄道，"
							+ "[npc.speechNoExtraEffects(唔！~真开心是我的鸡巴夺取了你的屁穴纯洁……我敢说你终会成为一个色情屁穴小荡妇……"
							+ "~哦哦！~现在像个肛门荡妇一样跪求我的肉棒，我才会继续做下去。)]"
						+ "</p>");

				sb.append("<p>");
					sb.append("发现你和[npc.name]说的一样色情，你竭尽全力去忍受这让人厌恶的饥渴呻吟，"
							+ "[pc.speechNoExtraEffects(求你了，[npc.name]……~啊！~给我你的肉棒！)]");
				sb.append("</p>");
			}

			sb.append("<p>"
						+ "[npc.speechNoExtraEffects(真是个好女孩，)][npc.name]回道，"
							+ "[npc.she]从刚才的位置继续，缓缓将[npc.her]硕大恶魔屌的剩余部分顶入你的肛门，你尽力忍住了呜咽。"
						+ "[npc.Name]用她搏动的肉棒填满你，轻声呻吟，"
						+ "[npc.speechNoExtraEffects(就这样……~唔！~让我顶进深处……你也要开始舒服起来了……)]"
					+ "</p>"
					+ "<p>"
						+ "理所当然地，[npc.her]把肉棒拔出你的肛门，疼痛就开始褪去，"
							+ "[npc.her][npc.cockHead+]一遍又一遍地突入你已然失守的屁股中，你发出淫乱的呻吟，意识到自己沉沦在这快感里……"
					+ "</p>");
		}
		
		return UtilText.parse(this,  sb.toString());
	}
	
	@Override
	public String getSpecialPlayerPureVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating) {
		return "<p style='text-align:center;'>"
					+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>失格处女</b>"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.name]再度开始用[npc.her]灼热的恶魔屌捅插你的小穴时，突然意识到正在发生的现实一切如同重锤一般砸在你身上。"
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ "[pc.thought(我，我就这样失贞了？！"
					+ "<br/>给<b>一位莉琳长老</b>？！)]"
				+ "</p>"
				+ "<p>"
					+ "你不知道哪个更糟糕；是失去了这样看重的第一次，还是自己反而乐在其中。"
					+ "你[pc.labia+]淫靡地包裹着那又热又粗，粗暴抽插着你的[npc.cock]，你宽慰自己，这就是你所擅长的。"
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ "[pc.thought(如果我不再是处女，那我就是个骚货了……"
					+ "<br/>只是个活该被操，被注满精液的婊子罢了……"
					+ "<br/>我好奇是不是所有的鸡巴都像[npc.namePos]的那么给力……)]"
				+ "</p>"
				+ "<p>"
					+ "你逐渐意识到了这个事实，[npc.name]一直告诉你，你的小穴感觉起来有多棒，当[npc.she]开始把[npc.her]注意力集中在用她跳动的恶魔肉棒填满它。"
					+ "随着一声渴求的呻吟，"
					+ (Main.game.getPlayer().hasLegs()
						?"你张开双腿并"
						:"你")
					+ "只能接受现实，听之任之。现在的你什么都不是，只是个"
					+ "<b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>失格处女</b>……"
				+ "</p>";
	}
}