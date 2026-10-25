package com.lilithsthrone.LolificationProject.Lexi.character.npc.fields;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import com.lilithsthrone.LolificationProject.Lexi.Lexicon;
import com.lilithsthrone.LolificationProject.Lexi.character.body.types.LexiTailType;
import com.lilithsthrone.LolificationProject.Lexi.character.effects.LexiPerk;
import com.lilithsthrone.LolificationProject.Lexi.character.race.LexiSubspecies;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.*;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.fields.Silvia;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.LolificationProject.Lexi.dialogue.npcDialogue.unique.CeciliaDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.unique.LumiDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.2.5
 * @version 0.0.1
 * @author Lexi
 */
public class Cecilia extends NPC {

	public Cecilia() {
		this(false);
	}

	public Cecilia(boolean isImported) {
		super(isImported, new NameTriplet("Cecilia"), null,
				"[npc.fullName] 是一头精力充沛的蝎狮女孩，你第一次见到她时，她正徘徊在旷野中。",
				21, Month.AUGUST, 28,
				38,
				Gender.F_V_B_FEMALE,
				LexiSubspecies.TRUE_MANTICORE,
				RaceStage.GREATER,
				new CharacterInventory(false, 0),
				WorldType.EMPTY,
				PlaceType.GENERIC_HOLDING_CELL,
				true);
		if(Main.game.getDialogueFlags().hasFlag(Lexicon.ceciliaMet)) {
			this.setPlayerKnowsName(true);
		} else {
		this.setPlayerKnowsName(false);
		}

		if(!isImported) {
			if(Main.game.getDialogueFlags().hasFlag(Lexicon.ceciliaMet)) {
				this.setPlayerKnowsName(true);
			} else {
				this.setPlayerKnowsName(false);
			}
			this.setGenericName("紫条纹蝎狮");
			initHealthAndManaToMax();
		}
	}


	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		setStartingCombatMoves();
	}
	
	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);
		setStartingCombatMoves();
	}

	@Override
	public void dailyUpdate() {

		//if(this.getWorldLocation()!=PlaceType.WORLD_MAP) {
		//	this.setRandomLocation(WorldType.WORLD_MAP, PlaceType.WORLD_MAP_GRASSLANDS, false);
	//	}
	}

	@Override
	public void setStartingCombatMoves() {
		this.clearEquippedMoves();
		this.equipMove("strike");
		this.equipMove("BASIC_OFFHAND_STRIKE");
		this.equipMove("BASIC_TWIN_STRIKE");
		this.equipMove("avert");
		this.equipMove("block");
		this.equipAllKnownMoves();
		this.equipAllSpellMoves();
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
	//	this.addSpecialPerk(LexiPerk.SPECIAL_CECILIA);
		this.addSpecialPerk(Perk.SPECIAL_CECILIA);
		this.addSpecialPerk(Perk.MARTIAL_ARTIST);
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		this.addSpecialPerk(Perk.FEROCIOUS_WARRIOR);
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.ORGASMIC_LEVEL_DRAIN,
						Perk.COMBAT_REGENERATION,
						Perk.BESERK,
						Perk.HYPERMOBILITY,
						Perk.CRITICAL_BOOST_ARCANE,
						Perk.CRITICAL_BOOST_LUST
				),
				Util.newHashMapOfValues(
						new Util.Value<>(PerkCategory.PHYSICAL, 100),
						new Util.Value<>(PerkCategory.LUST, 1),
						new Util.Value<>(PerkCategory.ARCANE, 1)));
	}

	@Override
	public CombatBehaviour getCombatBehaviour() {
		return CombatBehaviour.ATTACK;
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE);
		}

		if(setFetishes) {
			this.clearFetishDesires();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);

			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_MASTURBATION, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);

			//Post tussy penis tf, i think
			//	this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_DENIAL_SELF, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ONE_DISLIKE);


			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING, FetishDesire.ZERO_HATE);
		}

		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_AMAZONIAN);
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
//		this.setEarType(EarType.getEarTypeFromId("innoxia_panther_ear"));
//		this.setArmType(ArmType.getArmTypeFromId("innoxia_panther_arm"));
//		this.setLegType(LegType.getLegTypeFromId("innoxia_panther_leg"));
//		this.setTorsoType(TorsoType.getTorsoTypeFromId("innoxia_panther_torso"));
//		this.setTailType(LexiTailType.MANTICORE_TUSSY);
//		this.setWingType(WingType.LEATHERY);
//		this.setWingSize(260);

		// Core:
		this.setHeight(230); // About
		this.setFemininity(85); // Very feminine
		// Give body shape "ripped":
		this.setMuscle(Muscle.FOUR_RIPPED.getMaximumValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMaximumValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_eye"), CoveringPattern.NONE, PresetColour.EYE_PINK, true, PresetColour.EYE_YELLOW, true));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_SCLERA, PresetColour.EYE_BLACK));

		this.setSkinCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"), CoveringPattern.STRIPED, PresetColour.COVERING_PURPLE_DARK, false, PresetColour.COVERING_SILVER, false), true);



		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_TANNED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.SPIDER_CHITIN, PresetColour.CLOTHING_RED_BURGUNDY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_ROSY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_ROSY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_RED_DARK), false);

		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_hair"), PresetColour.COVERING_SCARLET), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_SCARLET), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.MESSY);

//		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
//		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"), PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);



//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_BLACK));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_BLACK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_BLACK));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.THREE_EXTREMELY_LONG.getMedianValue());
		// Tongue modifiers
		this.addTongueModifier(TongueModifier.STRONG);
		this.removeTongueModifier(TongueModifier.WIDE);
		this.removeTongueModifier(TongueModifier.FLAT);

		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.B.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.ONE_SMALL);
		this.setAreolaeSize(AreolaeSize.ONE_SMALL);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL.getValue());
		this.setHipSize(HipSize.THREE_GIRLY.getValue());
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		// No penis
		
		// Vagina:
		this.setVaginaVirgin(true);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue(), true);
		this.setVaginaWetness(Wetness.TWO_MOIST);
		this.setVaginaElasticity(OrificeElasticity.TWO_FIRM.getValue());
		this.setVaginaPlasticity(OrificePlasticity.TWO_TENSILE.getValue());
		this.removeVaginaOrificeModifier(OrificeModifier.PUFFY);
		this.removeVaginaOrificeModifier(OrificeModifier.TENTACLED);
		this.removeVaginaOrificeModifier(OrificeModifier.RIBBED);
		this.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL);

		// Tail:
		this.setTussyVirgin(false);
		this.setTussyCapacity(Capacity.THREE_SLIGHTLY_LOOSE, false);
		this.setTussyWetness(Wetness.FOUR_SLIMY);
		this.setTussyElasticity(OrificeElasticity.TWO_FIRM.getValue());
		this.setTussyPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());


	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		this.setMoney(8888);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boyshorts", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("TonyJC_daisy_dukes", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_sarashi", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_wraps", PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("phlarx_sunglasses_cat_eye_sunglasses", PresetColour.CLOTHING_RED, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("irbynx_spikedCollar_punk_neck_metal_collar_spiked", PresetColour.CLOTHING_BLACK, false), true, this);

	}

	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}

	@Override
	public void hourlyUpdate(int hour) {
		if(!Main.game.isInSex()) {
			this.useItem(Main.game.getItemGen().generateItem("innoxia_pills_sterility"), this, false);
		}
	}

	@Override
	public String getSpeechColour() {
		return PresetColour.CLOTHING_PURPLE_ROYAL.toWebHexString();
	}


	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return CeciliaDialogue.CECILIA_APPEARS;
	}
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character){
		return SexPace.SUB_EAGER;
	}
	public SexPace getSexPaceDomPreference(GameCharacter character){
		return SexPace.DOM_NORMAL;
	}

	@Override
	public void endSex() {
	}
	
	// Combat:
	
//	@Override
////	public boolean isImmuneToDamageType(DamageType type) {
////		return type==DamageType.PHYSICAL;
////	}
//
//	public boolean isImmuneToDamageType(DamageType PHYSICAL) {
//		return true;
//	}

	@Override
	public int getEscapeChance() {
		return 0;
	}

	@Override
	public int getLootMoney() {
		return 0;
	}
	
	@Override
	public List<AbstractCoreItem> getLootItems() {
		return new ArrayList<>();
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean playerVictory) {
		if(playerVictory) {
			if(Main.game.getDialogueFlags().hasFlag(Lexicon.ceciliaMet)) {
				return new Response("胜利", "塞西莉亚认可了你的实力！", CeciliaDialogue.COMBAT_PLAYER_WIN_REPEAT);
			} else {
				return new Response("胜利", "你打败了这只蝎狮女孩！", CeciliaDialogue.COMBAT_PLAYER_WIN);
			}
		} else {
			if(Main.game.getDialogueFlags().hasFlag(Lexicon.ceciliaMet)) {
				return new Response("败北", "塞西莉亚鼓励你多加训练！", CeciliaDialogue.COMBAT_PLAYER_LOSS_REPEAT);
			} else {
				return new Response("败北", "这只蝎狮女孩彻底打败了你！", CeciliaDialogue.COMBAT_PLAYER_LOSS);
			}
		}
	};
}