package com.lilithsthrone.game.character.npc.dominion;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.*;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.Month;
import java.util.List;

/**
 * @since 0.3.9.4
 * @version 0.3.9.4
 * @author DSG (character creator), Innoxia
 */
public class Elle extends NPC {

	public Elle() {
		this(false);
	}
	
	public Elle(boolean isImported) {
		super(isImported, new NameTriplet("艾拉西斯"), "露内特马尔图",
				"",
				44, Month.FEBRUARY, 5,
				15,
				Gender.F_V_B_FEMALE, Subspecies.HORSE_MORPH_UNICORN, RaceStage.GREATER,
				new CharacterInventory(false, 10),
				WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER,
				true);
		
		if(!isImported) {
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.7")) {
			equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
	}
	
	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.7")) {
			equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(Perk.BARREN),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 3),
						new Value<>(PerkCategory.ARCANE, 1)));
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT, 
					PersonalityTrait.LEWD);
		}
		
		if(setFetishes) {
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);

			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_ENFORCER_SWORD_SUPER);
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
		this.setAgeAppearanceAbsolute(41);
		this.setWingType(WingType.DEMON_COMMON);
		this.setHornType(HornType.HORSE_STRAIGHT);
		this.setHornLength(22);
		this.setFootStructure(FootStructure.UNGULIGRADE);

		this.setAssType(AssType.DEMON_COMMON);
		this.setBreastType(BreastType.DEMON_COMMON);
		this.setVaginaType(VaginaType.DEMON_COMMON);
		this.setWingType(WingType.DEMON_COMMON);
		this.setWingSize(WingSize.THREE_LARGE.getValue());
		this.setEyeType(EyeType.DEMON_COMMON);
		this.setSubspeciesOverride(Subspecies.HALF_DEMON);
		
		// Core:
		this.setHeight(188);
		this.setFemininity(90);
		this.setMuscle(70);
		this.setBodySize(50);
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_PINK));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_LILAC), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BROWN), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_INDIGO), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_SANDY), true);
		this.setHairLength(22);
		this.setHairStyle(HairStyle.BUN);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.SIX_BUSHY);
		this.setAssHair(BodyHair.FIVE_UNKEMPT);
		this.setPubicHair(BodyHair.SEVEN_WILD);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.FIVE_ROOMY, true);
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.E.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		
		this.setNippleCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue(), true);
		
		// Crotch-boobs:
		this.setBreastCrotchType(BreastType.NONE);
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		this.setAssCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setAssWetness(Wetness.THREE_WET);
		this.setAssDepth(OrificeDepth.FOUR_DEEP.getValue());
		this.setAssPlasticity(0);
		this.setAssElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		
		// Penis:
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaDepth(OrificeDepth.FOUR_DEEP.getValue());
		this.setVaginaPlasticity(0);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		if(isSlave()) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BRASS, false), true, this);

			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);

			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.ENFORCER_SHIRT, PresetColour.CLOTHING_PINK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.ENFORCER_MINI_SKIRT, PresetColour.CLOTHING_PINK, false), true, this);
			
		} else {
			this.setEssenceCount(100);
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("dsg_eep_pbweap_pbpistol"));
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_pantyhose", PresetColour.CLOTHING_BLACK, false), true, this);

			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, null, false), true, this);
			
			AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_GOLD, false);
			jacket.setSticker("collar", "tab_su");
			jacket.setSticker("name", "name_elle");
			jacket.setSticker("ribbon", "ribbon_elle");
			jacket.setSticker("qual", "qual_flyer");
			this.equipClothingFromNowhere(jacket, true, this);

			AbstractClothing beret = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfberet", PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_BLACK, null, false);
			beret.setSticker("flash", "flash_sword");
			this.equipClothingFromNowhere(beret, true, this);
			
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, PresetColour.CLOTHING_DESATURATED_BROWN, PresetColour.CLOTHING_GOLD, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_DESATURATED_BROWN, false), true, this);
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getArtworkFolderName() {
		if(isSlave()) {
			return "ElleSlave";
		} else {
			return "Elle";
		}
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return "托你的福，埃勒已经不再是御城区中心执法者的高级军需官了。尽管她从前职位上摔下得很快，本人看起来却完全不在意。";
			
		} else if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES)) {
			return "在你的帮助下，埃勒仍然是御城区中心执法者的高级军需官。虽然她可以让任何一个下属来接替韦斯以前的位置，但她选择了亲力亲为。";
			
		} else {
			return "埃勒是御城区中心执法者的高级军需官并且还是韦斯的指挥官。尽管她的脾性共事有点难度，但她的下属除了韦斯之外，几乎没什么怨言。";
		}
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#754a86";
		}
		return "#d0a0e2";
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isWorkTime()) {
				this.setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
				
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
		}
		if(!this.hasStatusEffect(StatusEffect.PROMISCUITY_PILL)) {
			Main.game.getItemGen().generateItem("innoxia_pills_sterility").applyEffect(this, this);
		}
	}
	
	@Override
	public void dailyUpdate() {
		if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_WES, Quest.WES_3_ELLE)) {
			clearNonEquippedInventory(false);
			
			// Weapons:
			this.addWeapon(Main.game.getItemGen().generateWeapon("dsg_eep_enbaton_enbaton"), 5, false, false);
			this.addWeapon(Main.game.getItemGen().generateWeapon("dsg_eep_pbweap_pbpistol"), 5, false, false);
			this.addWeapon(Main.game.getItemGen().generateWeapon("dsg_eep_pbweap_pbrifle"), 5, false, false);
			this.addWeapon(Main.game.getItemGen().generateWeapon("dsg_eep_taser_taser"), 5, false, false);
			
			// Clothing:
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_bglasses", false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_milsweatervest_crew", PresetColour.CLOTHING_GREY, false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_milsweater_crew", PresetColour.CLOTHING_GREY, false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_milsweater_vee", PresetColour.CLOTHING_GREY, false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_cbtshirt", PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_BLACK, null, false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_sslcbtshirt", PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_BLACK, null, false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_utilbelt", false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_battlebelt", false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_bgoggles", false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_cboots", false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_chelmet", false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_gmask", false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_nvgoggles", false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_telbowpads", false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_tkneepads", false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_hndcuffs_hndcuffs", false), 5, false, false);

			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_stpvest", false), 5, false, false);
			this.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_pltcarrier", false), 5, false, false);
		}
	}
	
	@Override
	public String getTraderDescription() {
		return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_TRADE_DIALOGUE");
	}
	
	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return false;
	}
	
	@Override
	public float getSellModifier(AbstractCoreItem item) {
		return 1.5f;
	}
	
	@Override
	public Value<Boolean, String> getItemUseEffects(AbstractItem item,  GameCharacter itemOwner, GameCharacter user, GameCharacter target) {
		if(user.isPlayer() && !target.isPlayer()) {
			if(item.isTypeOneOf("innoxia_pills_fertility", "innoxia_pills_broodmother")) {
				return new Value<>(true,
						"<p>"
							+ "你从背包中拿出一个"+item.getName(false, false)+"准备递给埃勒，[elle.race]已经看破了你的意图并宣告，"
							+ "[elle.speechNoEffects(我不可能会接受这个！我绝对没有怀孕的打算，非常感谢！)]"
						+ "</p>"
						+ "<p>"
							+ "你觉得强迫埃勒服用这药是个坏主意，就把它收起来了……"
						+ "</p>");
			}
			return new Value<>(false,
					"<p>"
						+ "你开始从背包中拿出"+item.getItemType().getDeterminer()+""+item.getName()+"，但埃勒迅速看破了你的意图并告诉你她绝对不会让你在她身上使用这个。"
						+ "你觉得强迫埃勒改变主意是并不好，所以就把药收起来了……"
					+ "</p>");
		}
		return super.getItemUseEffects(item, itemOwner, user, target);
	}

	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character){
		return SexPace.SUB_EAGER;
	}

	@Override
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_NORMAL;
	}
}