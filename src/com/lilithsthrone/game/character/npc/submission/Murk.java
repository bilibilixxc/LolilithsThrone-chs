package com.lilithsthrone.game.character.npc.submission;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import org.w3c.dom.Document;
import com.lilithsthrone.threading.DocBuilders;import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
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
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Scar;
import com.lilithsthrone.game.character.markings.ScarType;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensCaptiveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.sexActions.submission.SAMurkSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Murk extends NPC {

	public Murk() {
		this(false);
	}
	
	public Murk(boolean isImported) {
		super(isImported, new NameTriplet("默克"), "特里奇",
				"",
				36, Month.JANUARY, 12,
				10, Gender.M_P_MALE, Subspecies.RAT_MORPH, RaceStage.GREATER,
				new CharacterInventory(false, 2500), WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE, true);
		
		if(!isImported) {
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 60);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.7")) {  // Reset character
			this.setName(new NameTriplet("默克", "米西", "米西"));
			setupPerks(true);
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			setStartingBody(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.9")) {
			this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, PresetColour.COVERING_BROWN_DARK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.RAT_SKIN, PresetColour.SKIN_PINK_PALE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_DARK), false);
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, PresetColour.COVERING_BROWN_DARK), false);
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.setBirthday(LocalDateTime.of(Main.game.getStartingDate().getYear()-36, Month.JANUARY, 12, 12, 0));
			this.setPenisCumStorage(350);
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.2")) {
			if(this.isFeminine()) {
				this.setVaginaType(VaginaType.RAT_MORPH);
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9")) {
			this.addCumModifier(FluidModifier.MUSKY);
			this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_DARK), false);
			this.setSkinCovering(new Covering(BodyCoveringType.EYE_RAT, PresetColour.EYE_GREY_GREEN), false);
			this.setPenisSize(38);
			this.setPenisGirth(PenetrationGirth.SEVEN_FAT);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.4")) {
			this.setName(new NameTriplet("默克"));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.17")) {
			this.setPenisGirth(PenetrationGirth.SEVEN_FAT);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1")) {
			this.addFetish(Fetish.FETISH_BONDAGE_APPLIER);
		}
	}
	
	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.7")) {  // Reset character
			this.setName(new NameTriplet("Murk", "Missy", "Missy"));
			setupPerks(true);
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			setStartingBody(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.9")) {
			this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, PresetColour.COVERING_BROWN_DARK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.RAT_SKIN, PresetColour.SKIN_PINK_PALE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_DARK), false);
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, PresetColour.COVERING_BROWN_DARK), false);
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.setBirthday(LocalDateTime.of(Main.game.getStartingDate().getYear()-36, Month.JANUARY, 12, 12, 0));
			this.setPenisCumStorage(350);
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.2")) {
			if(this.isFeminine()) {
				this.setVaginaType(VaginaType.RAT_MORPH);
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9")) {
			this.addCumModifier(FluidModifier.MUSKY);
			this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_DARK), false);
			this.setSkinCovering(new Covering(BodyCoveringType.EYE_RAT, PresetColour.EYE_GREY_GREEN), false);
			this.setPenisSize(38);
			this.setPenisGirth(PenetrationGirth.SEVEN_FAT);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.4")) {
			this.setName(new NameTriplet("Murk"));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.17")) {
			this.setPenisGirth(PenetrationGirth.SEVEN_FAT);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1")) {
			this.addFetish(Fetish.FETISH_BONDAGE_APPLIER);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void resetDefaultMoves() {
		this.clearEquippedMoves();
		equipMove("strike");
		equipMove("offhand-strike");
		equipMove("twin-strike");
		equipMove("block");
		this.equipAllSpecialMoves();
		this.equipAllSpellMoves();
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.COWARDLY,
					PersonalityTrait.LEWD,
					PersonalityTrait.SELFISH,
					PersonalityTrait.SLOVENLY);
		}
		
		if(setFetishes) {
			this.clearFetishDesires();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			this.addFetish(Fetish.FETISH_BONDAGE_APPLIER);
			
			this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_GANG_MEMBER);
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
		this.setHeight(146);
		this.setFemininity(30);
		this.setMuscle(Muscle.ZERO_SOFT.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setSkinCovering(new Covering(BodyCoveringType.EYE_RAT, PresetColour.EYE_GREY_GREEN), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, PresetColour.COVERING_BROWN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_SKIN, PresetColour.SKIN_PINK_PALE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_DARK), false);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, PresetColour.COVERING_BROWN_DARK), false);
		this.setHairLength(0);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_RAT_FUR, PresetColour.COVERING_BROWN_DARK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.FLAT.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.ZERO_TINY);
		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.TWO_NARROW);
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisSize(38);
		this.setPenisGirth(PenetrationGirth.SEVEN_FAT);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(350);
		this.fillCumToMaxStorage();
		this.addCumModifier(FluidModifier.MUSKY);
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		if(settings.contains(EquipClothingSetting.ADD_TATTOOS)) {
			this.addTattoo(InventorySlot.WRIST, new Tattoo(TattooType.getTattooTypeFromId("innoxia_gang_rat_skull"), PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_WHITE, false, null, null));
			this.setScar(InventorySlot.LEG, new Scar(ScarType.CLAW_MARKS, true));
		}
		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bat_metal", DamageType.PHYSICAL, Util.newArrayListOfValues(PresetColour.CLOTHING_GUNMETAL, PresetColour.CLOTHING_BLACK)));
		}
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_briefs", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_crotchless_chaps", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_wraps", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_GOLD, false), true, this);
	}
	
	@Override
	public String getDescription() {
		StringBuilder sb = new StringBuilder();
		
		if(this.getHomeLocationPlace().getPlaceType()==PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE) {
			if(this.isFeminine()) {
				sb.append("伊波娜被说服了，默克受到了与罪相抵的惩罚，过去的鼠“男”现在是鼠“女”咯。"
						+ "“米西”，是她现在的名字，依然负责清理怀孕轮盘赌的台子，不过伊波娜现在似乎对她宽容了一些。");
				
			} else {
				sb.append("默克由于其强奸和绑架的罪行，受到了奴役，最后被交给了[axel.name]处置，当作曾经收取保护费的补偿。"
						+ "[axel.name]对拥有这样一个堕落的生物没有丝毫兴趣，立刻就将他交给了伊波娜，她发现默克的所作所为后大为愤怒，便罚他清理怀孕轮盘赌的台子。");
			}
			
		} else {
			sb.append("这个鼠男称作默克，长得又矮又圆，最喜欢展示他那不成比例的巨大鸡巴，但最出名的还不是他的长相或者礼节。"
					+ "其实他在整个鼠窟的坏名声，却来源于公认的自私、懦弱、残忍到近乎虐待狂的性格。");

			sb.append("<br/>"
					+ "若是换做其他人，必然会因为不断表现出这些烦人的素质而受到无情的欺凌，"
						+ "但默克手下的帮派成员容忍了他，因为他经营着所谓的“挤奶室”。"
					+ "正如其名，那里是几个被绑来的人的住处，他们转化后既能提供稳定的奶水供应，又能为文加的帮派成员提供性释放的场所。");
		}
		
		return sb.toString();
	}
	
	public String applyFeminisation() {
		StringBuilder sb = new StringBuilder();
		
		this.clearFetishes();
		this.clearFetishDesires();
		
		sb.append(this.addFetish(Fetish.FETISH_SUBMISSIVE));
		sb.append(this.addFetish(Fetish.FETISH_MASOCHIST));
		sb.append(this.addFetish(Fetish.FETISH_EXHIBITIONIST));
		
		this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.ZERO_HATE);
		this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
		
		// Body:
		// Core:
		this.setHeight(146);
		sb.append(this.setFemininity(75));
		this.setMuscle(Muscle.ZERO_SOFT.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setSkinCovering(new Covering(BodyCoveringType.EYE_RAT, PresetColour.EYE_YELLOW), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, PresetColour.COVERING_BROWN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_SKIN, PresetColour.SKIN_PINK_LIGHT), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_PINK_PALE), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_PINK_PALE), false);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, PresetColour.COVERING_BROWN_DARK), false);
		sb.append(this.setHairLength(HairLength.THREE_SHOULDER_LENGTH));
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_RAT_FUR, PresetColour.COVERING_BROWN_DARK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		// Face:
		sb.append(this.setLipSize(LipSize.TWO_FULL));
		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		
		// Chest:
		sb.append(this.setBreastSize(CupSize.DD.getMeasurement()));
		this.setBreastShape(BreastShape.POINTY);
		sb.append(this.setNippleSize(NippleSize.THREE_LARGE));
		sb.append(this.setAreolaeSize(AreolaeSize.THREE_LARGE));
		
		// Ass:
		sb.append(this.setAssSize(AssSize.FOUR_LARGE));
		sb.append(this.setHipSize(HipSize.FOUR_WOMANLY));
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.ONE_SLIGHTLY_MOIST);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		// Anus modifiers
		
		// Penis:
		sb.append(this.setPenisType(PenisType.NONE));
		
		// Vagina:
		sb.append(this.setVaginaType(VaginaType.RAT_MORPH));
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		sb.append(this.setVaginaLabiaSize(LabiaSize.THREE_LARGE));
		sb.append(this.setVaginaSquirter(true));
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		sb.append(this.setVaginaWetness(Wetness.FOUR_SLIMY));
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		
		// Feet:
		// Foot shape
		
		return sb.toString();
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public String getSpeechColour() {
		if(this.isFeminine()) {
			return PresetColour.BASE_TAN.toWebHexString();
		}
		return PresetColour.BASE_BROWN.toWebHexString();
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public void turnUpdate() {
	}

	@Override
	public boolean isAbleToBeImpregnated(){
		return true;
	}
	
	// Combat:
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(victory) {
			return new Response("", "", RatWarrensCaptiveDialogue.CAPTIVE_ESCAPE_FIGHT_VICTORY);
			
		} else {
			return new Response("", "", RatWarrensCaptiveDialogue.CAPTIVE_ESCAPE_FIGHT_DEFEAT);
		}
	}
	
	// Sex:

	@Override
	public int calculateSexTypeWeighting(SexType type, GameCharacter target, List<SexType> request, boolean lustOrArousalCalculation) {
		if(type.getPerformingSexArea()==SexAreaPenetration.TAIL) {
			return -10000;
		}
		return super.calculateSexTypeWeighting(type, target, request, lustOrArousalCalculation);
	}
	
	@Override
	public int getOrgasmsBeforeSatisfied() {
		if(Main.game.getPlayer().isCaptive() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.badEnd)) {
			return RatWarrensCaptiveDialogue.murkOrgasmsRequired;
		}
		return super.getOrgasmsBeforeSatisfied();
	}
	
//	@Override
//	public SexPace getSexPaceDomPreference(){
//		return SexPace.DOM_NORMAL;
//	}
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(SAMurkSpecials.class);
	}

	@Override
	public boolean getSexBehaviourDeniesRequests(GameCharacter requestingCharacter, SexType sexTypeRequest) {
		if(Main.game.getPlayer().isCaptive() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.badEnd)) {
			return true; // Always deny requests before transformations are finished.
		}
		return super.getSexBehaviourDeniesRequests(requestingCharacter, sexTypeRequest);
	}
	
	// Dirty talk:

	@Override
	public String getDirtyTalkPenisPenetrating(GameCharacter target, boolean isPlayerDom){
		List<String> availableLines = new ArrayList<>();
		
		if(!Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.PENIS, target).isEmpty()) {
			for(SexAreaOrifice orifice : Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.PENIS, target)) {
				if(Main.game.isGapeContentEnabled()) {
					switch(orifice) {
						case ANUS:
							if(Capacity.isPenetrationDiameterTooBig(
									target.getAssElasticity(), target.getAssStretchedCapacity(), this.getPenisDiameter(), true)) {
								availableLines.add(UtilText.returnStringAtRandom(
										"恁有感觉了？俺的大屌是不是要把恁屁股撑裂了？！骚逼，就当了俺的鸡巴套吧！",
										"恁个好色的婊子！恁就拿了屁眼儿给俺用吧！",
										"尼感觉到没油，尼届各骚货肉便器，看到窝德大几巴了吗？它马上就要淦进你的骚菊花了，看看它是怎摸撑爆你的骚穴的！",
										"看窝怎么把你给操烂，待会尼那淫荡的小小屁穴就该报废了！",
										"莫有比把肥腚操烂更好嘞，瞧它变了合不得的大洞喽！",
										"恁那浪荡屁眼被撑大咯！恁很快得全糟嘞！"));
							} else {
								availableLines.add(UtilText.returnStringAtRandom(
										"恁感得俺滴肥屌插进恁操烂的屁眼么？！只得俺嘞肥屌能满足恁嘞！",
										"恁个浪婊子！吃俺滴肥屌吧！干恁屁股！",
										"恁感得了么，小肉便器？感到俺的粗肉棒捅进恁贪吃的屁股了么？！"));
							}
							break;
						case VAGINA:
							if(Capacity.isPenetrationDiameterTooBig(
									target.getVaginaElasticity(), target.getVaginaStretchedCapacity(), this.getPenisDiameter(), true)) {
								availableLines.add(UtilText.returnStringAtRandom(
										"恁有感觉了？俺的大屌是不是要把恁逼撑裂了？！骚逼，就当了俺的鸡巴套吧！",
										"恁个浪婊子！俺滴肥屌要把恁操烂！干恁！",
										"恁感得了么，小肉便器？感到俺的粗肉棒捅进你贪吃的小穴了么？！被撑大了塞？",
										"等俺操烂，恁那淫荡的逼就该报废了！",
										"莫有比把小逼操烂更好嘞，瞧它变了合不得的大洞喽！",
										"恁那浪逼被撑大咯！恁很快得全糟嘞！"));
							} else {
								availableLines.add(UtilText.returnStringAtRandom(
										"恁感得俺滴肥屌插进恁操开的小逼么？！只得俺嘞肥屌能满足恁嘞！",
										"恁个浪婊子！吃俺滴肥屌吧！干恁浪逼！",
										"恁感得了么，小肉便器？感到俺的粗肉棒捅进恁贪吃的小逼了么？！"));
							}
							break;
						default:
							break;
					}
				}
				if(availableLines.isEmpty()) {
					switch(orifice) {
						case ANUS:
							availableLines.add(UtilText.returnStringAtRandom(
									"恁有感觉了？俺的大屌是不是要把恁屁眼子捅穿了？！骚逼，就当了俺的鸡巴套吧！",
									"恁个好色的婊子！恁就拿了屁眼儿给俺用吧！",
									"恁感觉到没，这个骚货肉便器，感觉到俺嘞大鸡巴了吗？它马上就要淦进你的骚菊花深处了？！"));
							break;
						case VAGINA:
							availableLines.add(UtilText.returnStringAtRandom(
									"恁有感觉了？俺的大屌是不是要把恁逼捅穿了？！骚逼，就当了俺的鸡巴套吧！",
									"恁个浪婊子！小逼吃俺滴肥屌吧！",
									"恁感得了么，小肉便器？感到俺的粗肉棒捅进你贪吃的小穴了么？！"));
							break;
						default:
							break;
					}
				}
			}
		}

		if(!availableLines.isEmpty()) {
			String returnedLine = Util.randomItemFrom(availableLines);
			return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
		}
		return super.getDirtyTalkPenisPenetrating(target, isPlayerDom);
	}
	

	@Override
	public String getSpecialPlayerVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating, GameCharacter receivingCharacter, SexAreaOrifice penetrated) {
		if(receivingCharacter.isPlayer() && receivingCharacter.isCaptive()) {
			if(penetrated==SexAreaOrifice.VAGINA) {
				return "";
			} else if(penetrated==SexAreaOrifice.ANUS) {
				return "";
			}
		}
		
		return super.getSpecialPlayerVirginityLoss(penetratingCharacter, penetrating, receivingCharacter, penetrated);
	}

	@Override
	public String getSpecialPlayerPureVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating) {
		return "<p style='text-align:center;'>"
					+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>失格处女</b>"
				+ "</p>"
				+ "<p>"
					+ "默克粗大的肉棒不停顶弄着你下贱的小穴，突然意识到正在发生的现实一切如同重锤一般砸在你身上。"
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ "[pc.thought(我就这样失贞了？！"
						+ "<br/>只是……<b>这样就</b>？！)]"
				+ "</p>"
				+ "<p>"
					+ "你不知道哪个更糟糕；是失去了这样看重的第一次，还是自己反而乐在其中。"
					+ "你肥厚硕大的阴唇淫荡地张开，吃下了鼠男搏动着的粗壮肉棒，你开始自我怀疑自己要成为下一个乳畜了……"
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ "[pc.thought(因为我的第一次献给了默克，所以后来我就成了他胯下的荡妇……<br/>"
						+ "好的……<br/>"
						+ "我会做他最好的挤奶婊子……)]"
				+ "</p>"
				+ "<p>"
					+ "你完全沉沦于默克的攻势，发出浪荡的呻吟，狂乱地朝他扭拱屁股，承认自己只不过是个"
					+ "<b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>失格处女</b>……"
				+ "</p>";
	}
}
