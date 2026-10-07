package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import org.w3c.dom.Document;
import com.lilithsthrone.threading.DocBuilders;import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
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
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Saellatrix;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityCategory;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Innoxia
 */
public class Angelixx extends NPC {

	public Angelixx() {
		this(false);
	}
	
	public Angelixx(boolean isImported) {
		super(isImported,
				new NameTriplet("安吉莉克丝"), "洛维耶纳马尔图拉尼",
				"安吉莉克丝虽然表面上是个年轻无邪的魅魔，但实际上是她母亲莉琳长老洛维耶纳的一枚极其强大又危险的棋子。",
				52, Month.MAY, 17,
				30, Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(false, 10),
				WorldType.getWorldTypeFromId("innoxia_dominion_angelixx_apartment"), PlaceType.getPlaceTypeFromId("innoxia_dominion_angelixx_apartment_bedroom_angelixx"),
				true);
		
		if(!isImported) {
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.12")) {
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.10.2") && !this.isDoll()) {
			this.setStartingBody(true);
			this.setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_angelixx_apartment"), PlaceType.getPlaceTypeFromId("innoxia_dominion_angelixx_apartment_bedroom_angelixx"), true);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.THREE_LIKE);
			this.setupPerks(true);
		}
	}

	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.12")) {
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.10.2") && !this.isDoll()) {
			this.setStartingBody(true);
			this.setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_angelixx_apartment"), PlaceType.getPlaceTypeFromId("innoxia_dominion_angelixx_apartment_bedroom_angelixx"), true);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.THREE_LIKE);
			this.setupPerks(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_TRAINING);
		this.addSpecialPerk(Perk.SPECIAL_MEGA_SLUT);
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.BARREN,
						Perk.FIRING_BLANKS,
						Perk.CLOTHING_ENCHANTER,
						Perk.WEAPON_ENCHANTER),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.LEWD,
					PersonalityTrait.INNOCENT,
					PersonalityTrait.COWARDLY);
		}
		
		if(setFetishes) {
			this.clearFetishDesires();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			this.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);

			this.setFetishDesire(Fetish.FETISH_INCEST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_KINK_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_FOOT_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ARMPIT_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DENIAL, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ONE_DISLIKE);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_LILIN_PAWN);
		}
		
		if(setSpells) {
			this.addSpell(Spell.CLOAK_OF_FLAMES);
			this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_1);
			this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_2);
			this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_3);
			
			this.addSpell(Spell.PROTECTIVE_GUSTS);
			this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_1);
			this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_2);
			this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_3);

			this.addSpell(Spell.STONE_SHELL);
			this.addSpellUpgrade(SpellUpgrade.STONE_SHELL_1);
			this.addSpellUpgrade(SpellUpgrade.STONE_SHELL_2);
			this.addSpellUpgrade(SpellUpgrade.STONE_SHELL_3);
			
			this.addSpell(Spell.ARCANE_AROUSAL);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_1);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_3);

			this.addSpell(Spell.FLASH);
			this.addSpellUpgrade(SpellUpgrade.FLASH_1);
			this.addSpellUpgrade(SpellUpgrade.FLASH_2);
			this.addSpellUpgrade(SpellUpgrade.FLASH_3);

			this.addSpell(Spell.TELEPATHIC_COMMUNICATION);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3);
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			setStartingPersona();
		}
		
		// Body:
		this.setAgeAppearanceAbsolute(18);
		this.setTailType(TailType.NONE);
		this.setWingType(WingType.DEMON_FEATHERED);
		this.setWingSize(WingSize.ZERO_TINY.getValue());
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.STRAIGHT);
		this.setHornLength(HornLength.ZERO_TINY.getMedianValue());
		this.setHornRows(1);
		
		// Core:
		this.setHeight(152);
		this.setFemininity(100);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_BLUE_LIGHT));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_LIGHT), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, CoveringPattern.OMBRE, CoveringModifier.SMOOTH, PresetColour.COVERING_WHITE, false, PresetColour.SKIN_LIGHT, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.SKIN_ROSY, false, PresetColour.ORIFICE_INTERIOR, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, PresetColour.SKIN_ROSY, false, PresetColour.ORIFICE_INTERIOR, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, PresetColour.SKIN_ROSY, false, PresetColour.ORIFICE_INTERIOR, false), false);

		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_FEATHER, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false), false);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLONDE), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.TWIN_TAILS);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_DIRTY_BLONDE), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);
		
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PINK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PINK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_PINK_LIGHT));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PINK));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLUE_DARK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.A.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		this.addNippleOrificeModifier(OrificeModifier.PUFFY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.FOUR_SLIMY);
		this.setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setAssPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		// Anus settings and modifiers
		
		// Penis:
		// n/a
		this.setTesticleCount(2); // For if she grows one
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.addVaginaOrificeModifier(OrificeModifier.PUFFY);
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.addTattoo(InventorySlot.VAGINA,
				new Tattoo("innoxia_animal_hoof", PresetColour.COVERING_BLACK, PresetColour.COVERING_BLACK, PresetColour.COVERING_BLACK, false,
						null, null));

		AbstractClothing scrunchie = Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_scrunchie", PresetColour.CLOTHING_BLUE_LIGHT, false);
		scrunchie.setPattern("none");
		this.equipClothingFromNowhere(scrunchie, true, this);
		
		if(this.isTaur()) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_headband_bow", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_PINK, false), InventorySlot.TAIL, true, this);
		}
		
		AbstractClothing ring = Main.game.getItemGen().generateClothing("innoxia_finger_wrap_ring", PresetColour.CLOTHING_SILVER, false);
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		this.equipClothingFromNowhere(ring, true, this);
		
		if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_dominion_angelixx_apartment_bedroom_angelixx")) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_chemise", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_thighhigh_socks_striped", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_WHITE, false), true, this);
			
		} else {
			if(!this.isTaur()) {
				AbstractClothing vstring = Main.game.getItemGen().generateClothing("innoxia_groin_vstring", PresetColour.CLOTHING_WHITE, false);
				vstring.setPattern("irbynx_polka_dots_big");
				vstring.setPatternColours(Util.newArrayListOfValues(PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_WHITE));
				this.equipClothingFromNowhere(vstring, true, this);
	
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_leg_warmers", PresetColour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("nerdlinger_street_hitop_canvas_sneakers_hi_top_canvas_sneakers", PresetColour.CLOTHING_BLUE_LIGHT, false), true, this);
			}
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_croptop_bra", PresetColour.CLOTHING_WHITE, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_hoodie", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
	
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_micro_skirt_pleated", PresetColour.CLOTHING_BLUE_LIGHT, false), true, this);
	
		}
		
		this.setPiercedEar(true);
		this.setPiercedNavel(true);
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", PresetColour.CLOTHING_SILVER, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public String getSpeechColour() {
		if(Main.game.isLightTheme()) {
			return PresetColour.BASE_YELLOW_LIGHT.toWebHexString();
		}
		return "#fafad8";
	}

	@Override
	protected Set<GameCharacter> getChildren() {
		Set<GameCharacter> children = super.getChildren();
		
		children.add(Main.game.getNpc(Sleip.class));
		children.add(Main.game.getNpc(Nir.class));
		
		return children;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}
	
	// External methods:
	
	private List<String> adjectivesUsed = new ArrayList<>();
	public void initGangMember(NPC gangMember, boolean resetAdjectives) {
		if(resetAdjectives) {
			adjectivesUsed = new ArrayList<>();
		}
		gangMember.setHistory(Occupation.NPC_MUGGER);
		gangMember.equipClothing();
		gangMember.setLocation(Main.game.getPlayerCell(), true);
		gangMember.removePersonalityTrait(PersonalityTrait.MUTE);
		adjectivesUsed.add(Main.game.getCharacterUtils().setGenericName(gangMember, "帮派成员", adjectivesUsed));
		
		gangMember.unequipAllWeaponsIntoVoid(true);
	}
	
	public void setDefeatedName(NPC gangMember, boolean resetAdjectives) {
		if(resetAdjectives) {
			adjectivesUsed = Util.newArrayListOfValues("knocked-out", "unconscious", "defeated");
			Collections.shuffle(adjectivesUsed);
		}
		String adjective = "落败";
		if(adjectivesUsed.size()>0) {
			adjective = adjectivesUsed.get(0);
			adjectivesUsed.remove(0);
		}
		
		gangMember.setGenericName(adjective+"的"+(gangMember.isFeminine()?gangMember.getSubspecies().getSingularFemaleName(gangMember.getBody()):gangMember.getSubspecies().getSingularMaleName(gangMember.getBody())));
	}
	
	public void setDefeatedNamesPostCombat() {
		List<NPC> enemies = Main.game.getCharactersPresent().stream().filter(npc->npc instanceof ElisAlleywayAttacker && npc.hasFoughtPlayer()).collect(Collectors.toList());
		for(int i=0;i<enemies.size();i++) {
			setDefeatedName(enemies.get(i), i==0);
		}
	}
	
	public void initVictim(NPC victim, NPC gangMember) {
		victim.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN, true);
		victim.setVaginaCapacity(Capacity.FIVE_ROOMY, true);
		victim.setAnalVirgin(false);
		victim.setFaceVirgin(false);
		victim.setVaginaVirgin(false);
		
		victim.setHistory(Occupation.NPC_CAPTIVE);
		victim.unequipAllClothingIntoVoid(true, true);

		AbstractClothing choker = Main.game.getItemGen().generateClothing("innoxia_bdsm_choker", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, PresetColour.CLOTHING_STEEL, null, false);
		choker.setSticker("top_txt", "worthless");
		choker.setSticker("btm_txt", "whore");
		victim.equipClothingFromNowhere(choker, true, gangMember);
		victim.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_bracelets", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, PresetColour.CLOTHING_STEEL, null, false), InventorySlot.WRIST, true, gangMember);
		victim.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_bracelets", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, PresetColour.CLOTHING_STEEL, null, false), InventorySlot.ANKLE, true, gangMember);
		victim.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_DESATURATED_BROWN_DARK, PresetColour.CLOTHING_STEEL, false), true, gangMember);
		
		victim.addFluidStored(SexAreaOrifice.VAGINA, new FluidStored(gangMember, gangMember.getCum(), 250));
		victim.addFluidStored(SexAreaOrifice.MOUTH, new FluidStored(gangMember, gangMember.getCum(), 100));
		victim.addDirtySlot(InventorySlot.ANUS);
		victim.addDirtySlot(InventorySlot.CHEST);
		victim.addDirtySlot(InventorySlot.GROIN);
		victim.addDirtySlot(InventorySlot.LEG);
		victim.addDirtySlot(InventorySlot.HIPS);
		victim.addDirtySlot(InventorySlot.STOMACH);
		victim.addDirtySlot(InventorySlot.TORSO_UNDER);
		victim.addDirtySlot(InventorySlot.EYES);
		victim.addDirtySlot(InventorySlot.MOUTH);
		victim.addDirtySlot(InventorySlot.HEAD);
		
		victim.setLocation(Main.game.getPlayerCell(), true);
		victim.removePersonalityTrait(PersonalityTrait.MUTE);
		victim.setGenericName("俘虏");
	}

	public void initGangMemberGuards() {
		Main.game.getNpc(Sleip.class).setLocation(this);
		Main.game.getNpc(Nir.class).setLocation(this);
	}
	
	/**
	 * Banishes all gang members from ground floor (pass in 0 as argument) or first floor (pass in 1 as argument)
	 */
	public void banishGangMembers(int floor) {
		String worldId = "innoxia_fields_elis_abandoned_bakery_f0";
		if(floor==1) {
			worldId = "innoxia_fields_elis_abandoned_bakery_f1";
		}
		List<NPC> present = new ArrayList<>();
		Cell[][] cellGrid = Main.game.getWorlds().get(WorldType.getWorldTypeFromId(worldId)).getCellGrid();
		for(int i=0; i<cellGrid.length; i++) {
			for(int j=0; j<cellGrid[i].length; j++) {
				present.addAll(Main.game.getCharactersPresent(cellGrid[i][j]).stream().filter(
						npc->(npc instanceof ElisAlleywayAttacker || npc instanceof GenericSexualPartner)
							&& !npc.isSlave()
							&& !Main.game.getPlayer().getParty().contains(this)
						).collect(Collectors.toList()));
				Cell c = cellGrid[i][j];
				c.resetInventory();
			}
		}
		
		for(NPC npc : present) {
			Main.game.banishNPC(npc);
		}

		if(floor==1) {
			Main.game.getNpc(Sleip.class).returnToHome();
			Main.game.getNpc(Nir.class).returnToHome();
		}
	}
	
	private static List<String> diaryEntries = Util.newArrayListOfValues(
		"今天一个猫女把我推开了，还骂我小傻女。"
			+ "幸运的是我的孩子们在附近，所以我让他们把她拖到一个小巷里并把她围了起来。"
			+ "我命令他们射满她的身上并问她，现在谁看起来更傻呢？"
			+"<br/>(01/14)",
		"今晚我又在酒吧被搭讪了。搭讪者是个帅气又大只的斑马男。所有的马化形都是我的弱点，所以我被他带回了邋遢的小公寓然后按在床上狠狠地操死了。"
			+ "他妈的他那巨根把我撑得好爽，在中出我之后让我口交清洁干净。"
			+ "操，光是回想也让我欲火焚身……或许我得叫个奴隶来爆干我……"
			+"<br/>(02/14)",
		"赛拉特里克斯想要我增加绑架的数目，但仍然坚持失踪者必须是无亲无故不会被报告的目标。"
			+ "受不了一点，我的帮派已经很辛苦了，她怎么完全不懂呢？！按现在的速率我就又需要去小巷子里去诱惑那些独行者了，那会严重占用我与孩子们度过的时间……"
			+"<br/>(03/14)",
		"我的好孩子，斯雷普和尼尔，今天抓获了三个难民！作为奖励，我叫他们半夜来我的房间，我穿着他俩最喜欢的内衣裤并让一个奴隶把我绑在床上。"
			+ "操，那晚他们把我干得好猛啊，今早我几乎走不动路了……"
			+"<br/>(04/14)",
		"我正把难民们传送到母亲的店中时，其中一人骂我是愚蠢的小贱人。"
			+ "操，我这暴脾气。我当场就让孩子们给她的小脏逼来个双重插入，并继续传送其他人。"
			+ "传送好后，这个小傻逼的小脏逼敞开着，精液满溢。我跟她保证这只是她新生活的开始，之后也把她传送走了……"
			+"<br/>(05/14)",
		"今天我去了银行，结果大厅里坐着的那个傻吊居然叫我去队伍后面排队，我告诉他老娘是他妈的谁，他才终于知道错了。"
			+ "可惜太晚了！我给那个傻屌换了个工作。“她”现在被药水转化成了一个巨乳马女，斯雷普和尼尔扒光了那个傻屌，灌满了她刚长出来的子宫。相信全身泡过精液以后这个傻屌能学会做一个称职的荡妇……"
			+"<br/>(06/14)",
		"昨天把我当肉便器的半马人居然让我怀上了三胞胎……呃……"
			+ "马化形是一回事，但挤出恶魔混血的半人马真他妈的累。"
			+ "迟点我要拿些母亲的奶水然后快点把这逼事搞定。怀孕都让我走形了……"
			+"<br/>(07/14)",
		"母亲今天派赛拉特里克斯来见我了。那婊子继续催我增加绑架的数目。"
			+ "她的支配与直接的行为让我忍不住起了性欲，之后她长出巨屌的时候，我根本抵挡不住！"
			+ "她把我按在地上狠狠地操，而孩子们只是站在一旁看着……"
			+"<br/>(08/14)",
		"我的一些帮派成员消失了……肯定是执法者抓住了他们，又或者可能是敌对帮派？"
			+ "操了，我可不是打帮派战争的料！今晚我会诱惑一位执法者看看能知道些什么。"
			+ "就选巡逻中见过的那个大只性感又迷人的马男吧？"
			+"<br/>(09/14)",
		"晚我见到了巡逻中见过的那个大只帅气的马男。他比我想象的要难诱惑，我不得不使用心灵感应让他燃起性欲。"
			+ "也行吧，反正最后我得到了我想要的。他完全没有关于袭击我帮派的人的任何信息，不过我好歹也让他那又粗又壮的鸡巴深深插入我的小热穴里了……"
			+"<br/>(10/14)",
		"我今天在巷子里被抢劫了！一对犬男要抢我的火币，其中一个还拿着执法者的枪！"
			+ "我知道我完全可以打败他们，但我就是动不了……拿走火币后，他们说要用狗鞭回报我。"
			+ "操，那可把我弄湿了，所以我也让他们就在地上当场狂暴后入我……"
			+"<br/>(11/14)",
		"今天在农贸市场看到了有人袭击了米诺塔莉丝的店。天角兽真的为了我做了这个，所以我在亲吻亭埋伏并诱惑了他。"
			+ "当然，我得用奥术快感*和*心灵感应，好在我最后成功了。我们在附近建筑的后面找了个安静地，而他坚持先舔鲍鱼。"
			+ "他确实成功地只用舌头就把我舔高潮了，之后他的粗壮的马鞭又深插我的小穴把我干高潮了十几二次吧……"
			+"<br/>(12/14)",
		"母亲今天把我叫回御城区去见她，我决定坐马车替代传送过去。"
			+ "拉着马车的大只性感结实的半人马叫“弗洛狄”，而我为了让他停车用巨马鞭操我用尽了一切办法。"
			+ "但我甚至没法让他从嘴里撬出一个字。操！刚刚我让一个半人马大操特操我的时候还一直想着弗洛狄呢……"
			+"<br/>(13/14)",
		"尼尔今天在帮派里招收了一个新人。通常我不会太关心新成员，但这位可是个粗野的大笨驴男。"
			+ "我太想要他了，就让他把我拉进总部的脏厕所然后把我按在墙上狂操。"
			+ "他的粗屌就跟我想得那么好，但这个傻逼好色驴子在着急插入我的小穴的时候把我最喜欢的内裤撕成了两半……"
			+"<br/>(14/14)"
		);

	private static Set<Integer> viewedDiaryIndexes = new HashSet<>();
	private static Colour[] diaryPageColour = new Colour[] {PresetColour.BASE_BLUE_LIGHT, PresetColour.BASE_ORANGE_LIGHT, PresetColour.BASE_RED_LIGHT, PresetColour.BASE_GREEN_LIME, PresetColour.BASE_PINK_LIGHT};
	
	/**
	 * Does not repeat entries until all are seen, then restarts.
	 */
	public String getDiaryEntry() {
		List<Integer> availableIndexes = new ArrayList<>();
		for(int i=0; i<diaryEntries.size(); i++) {
			availableIndexes.add(i);
		}
		for(Integer i : viewedDiaryIndexes) {
			availableIndexes.remove(i);
		}
		if(availableIndexes.isEmpty()) {
			viewedDiaryIndexes = new HashSet<>();
			return getDiaryEntry();
		}
		
		int index = Util.randomItemFrom(availableIndexes);
		if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_1 && viewedDiaryIndexes.isEmpty()) {
			index = 4; // Make sure that the 'evidence' entry is the first one to be seen when the player likely checks the diary out for the first time
		}
		viewedDiaryIndexes.add(index);
		
		Colour colour = diaryPageColour[index%diaryPageColour.length];
		
		return "<span style='color:"+colour.toWebHexString()+";'>"+diaryEntries.get(index)+"</spn>";
	}
	
	public void initAsSlave() {
		this.unequipAllClothingIntoVoid(true, true);
		
		AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_GOLD, false);
		for(int i=0; i<33; i++) {
			collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_DRAIN, 0));
		}
		collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MINOR_DRAIN, 0));
		
		this.equipClothingFromNowhere(collar, true, Main.game.getNpc(Saellatrix.class));
		

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_RED_DARK, false), true, Main.game.getNpc(Saellatrix.class));
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_blindfold", PresetColour.CLOTHING_BLACK, false), true, Main.game.getNpc(Saellatrix.class));

		this.setAffection(Main.game.getPlayer(), -100);
		this.setAffection(Main.game.getNpc(Saellatrix.class), -100);
		this.setObedience(100);
	}
	
	public void growPenis() {
		if(this.isTaur()) {
			this.setPenisType(PenisType.DEMON_COMMON);
			this.clearPenisModifiers();
			this.addPenisModifier(PenetrationModifier.FLARED);
			this.setPenisSize(8);
			this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
			this.setTesticleSize(1);
			this.setPenisCumStorage(25);
			
		} else {
			this.setPenisType(PenisType.DEMON_COMMON);
			this.clearPenisModifiers();
			this.setPenisSize(4);
			this.setPenisGirth(PenetrationGirth.TWO_NARROW);
			this.setTesticleSize(0);
			this.setPenisCumStorage(5);
		}
	}
	
	public void applyPostTaurChanges() {
		if(this.isTaur()) {
			if(this.hasPenis()) {
				growPenis();
			}
			this.clearAssOrificeModifiers();
			this.addAssOrificeModifier(OrificeModifier.PUFFY);
			this.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
			
			this.clearVaginaOrificeModifiers();
			this.addVaginaOrificeModifier(OrificeModifier.PUFFY);
			this.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL);

			this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_WHITE), true);
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_BLONDE), true);

			this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.SKIN_ROSY, false, PresetColour.ORIFICE_INTERIOR, false), false);
			this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, PresetColour.SKIN_ROSY, false, PresetColour.ORIFICE_INTERIOR, false), false);
			
		} else {
			boolean hadPenis = this.hasPenis();
			setStartingBody(false);
			if(hadPenis) {
				growPenis();
			}
		}
		this.equipClothing();
	}
	
	public void equipClubbingClothing() {
		this.unequipAllClothingIntoVoid(true, true);

		AbstractClothing ring = Main.game.getItemGen().generateClothing("innoxia_finger_wrap_ring", PresetColour.CLOTHING_SILVER, false);
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		this.equipClothingFromNowhere(ring, true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_virgin_killer_sweater", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		

		AbstractClothing scrunchie = Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_scrunchie", PresetColour.CLOTHING_WHITE, false);
		scrunchie.setPattern("none");
		this.equipClothingFromNowhere(scrunchie, true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_WHITE, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_thighhigh_socks", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_low_top_skater_shoes", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
	}
	
	public void spawnClubStrangers() {
		try {
			String id = Main.game.addNPC("misc.GenericSexualPartner", "stranger1");
			GameCharacter npc1 = Main.game.getNPCById(id);
			
			npc1.resetBodyToGenderPreferences(false, true);
			npc1.setName(Name.getRandomName(npc1));
			npc1.removePersonalityTraits(PersonalityCategory.SPEECH);
			npc1.setPlayerKnowsName(true);
			if(Main.game.getPlayer().isFeminine()) {
				npc1.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				npc1.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			}
			Main.game.getCharacterUtils().equipClothingFromOutfitType(npc1, OutfitType.CLUBBING);
			npc1.setAlcoholLevel(0.25f);
			npc1.setLocation(Main.game.getPlayer());
			npc1.setPenisVirgin(false);
			
			
			id = Main.game.addNPC("misc.GenericSexualPartner", "stranger2");
			GameCharacter npc2 = Main.game.getNPCById(id);
			
			npc2.resetBodyToGenderPreferences(false, true);
			npc2.setName(Name.getRandomName(npc2));
			npc2.removePersonalityTraits(PersonalityCategory.SPEECH);
			npc2.setPlayerKnowsName(true);
			if(Main.game.getPlayer().isFeminine()) {
				npc2.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				npc2.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			}
			Main.game.getCharacterUtils().equipClothingFromOutfitType(npc2, OutfitType.CLUBBING);
			npc2.setAlcoholLevel(0.25f);
			npc2.setLocation(Main.game.getPlayer());
			npc2.setPenisVirgin(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
