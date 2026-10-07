package com.lilithsthrone.game.character.npc.dominion;

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
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounter;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.3.1
 * @author Innoxia
 */
public class Kate extends NPC {

	public Kate() {
		this(false);
	}
	
	public Kate(boolean isImported) {
		super(isImported, new NameTriplet("凯特"), "拉谢尔马尔图",
				"凯特是个恶魔，经营着美容沙龙“魅魔的秘密”。"
						+ "她尽管非常擅长自己的工作，但非常懒惰，并喜欢让店铺看起来破旧不堪以吓退潜在的顾客。",
				361, Month.SEPTEMBER, 9,
				10, Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(false, 10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_KATES_SHOP, true);
		
		if(!isImported) {
			dailyUpdate();
		}
	}
	
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceAbsolute(28);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.setTailGirth(PenetrationGirth.TWO_NARROW);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.3")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.8")) {
			this.setAge(361);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.9")) {
			this.addTattoo(InventorySlot.GROIN, getKatesGroinTattoo());
		}
	}

	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceAbsolute(28);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.setTailGirth(PenetrationGirth.TWO_NARROW);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.3")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.8")) {
			this.setAge(361);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.9")) {
			this.addTattoo(InventorySlot.GROIN, getKatesGroinTattoo());
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_TATTOOIST);
		this.addSpecialPerk(Perk.SPECIAL_MEGA_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.HEAVY_SLEEPER),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 5),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
		}
		
		if(setFetishes) {
			this.clearFetishDesires();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_PREGNANCY);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_BEAUTICIAN);
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
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.CURLED);
		this.setWingType(WingType.DEMON_COMMON);
		this.setWingSize(WingSize.ONE_SMALL.getValue());
		this.setTailType(TailType.DEMON_COMMON);
		this.setTailGirth(PenetrationGirth.TWO_NARROW);

		if(this.getTattooInSlot(InventorySlot.GROIN)==null) {
			try {
				this.addTattoo(InventorySlot.GROIN, getKatesGroinTattoo());
				
				this.addTattoo(InventorySlot.TORSO_OVER,
						new Tattoo(
							"innoxia_animal_butterflies",
							PresetColour.CLOTHING_PURPLE,
							PresetColour.CLOTHING_PINK,
							PresetColour.CLOTHING_PINK_LIGHT,
							false,
							null,
							null));
				
				this.addTattoo(InventorySlot.TORSO_UNDER,
						new Tattoo(
							"innoxia_symbol_tribal",
							PresetColour.CLOTHING_BLACK,
							null,
							null,
							false,
							new TattooWriting(
									"不要拔出来！",
									PresetColour.CLOTHING_BLACK,
									false),
							null));
				
			} catch(Exception ex) {
			}
		}

		// Core:
		this.setAgeAppearanceAbsolute(28);
		this.setHeight(180);
		this.setFemininity(85);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_GREEN));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_PINK), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_RED), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.SIDECUT);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PINK));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PINK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_RED));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.FIVE_ROOMY, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.F.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setAssPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		// Anus modifiers
		
		// Penis:
		// (For when she grows one)
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setPenisSize(15);
//		this.setInternalTesticles(true); Use player preferences
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(150);
		this.fillCumToMaxStorage();
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setVaginaWetness(Wetness.SIX_SOPPING_WET);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {

		this.unequipAllClothingIntoVoid(true, true);

		this.setMoney(10);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_vstring", PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_micro_skirt_belted", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_cami_straps", PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_womens_leather_jacket", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_fishnets", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_BLACK, false), true, this);

		this.setPiercedEar(true);
		this.setPiercedNavel(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", PresetColour.CLOTHING_GOLD, false), InventorySlot.PIERCING_STOMACH, true, this);

	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);

		for(AbstractItemType item : ItemType.getAllItems()) {
			if(item.getItemTags().contains(ItemTag.SOLD_BY_KATE)
					&& (!item.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				this.addItem(Main.game.getItemGen().generateItem(item), !item.isConsumedOnUse()?1:(6+Util.random.nextInt(12)), false, false);
			}
		}
		
		List<AbstractClothing> clothingToSell = new ArrayList<>();
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_KATE)
					&& (!clothing.getDefaultItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				clothingToSell.add(Main.game.getItemGen().generateClothing(clothing, false));
			}
		}

		for(AbstractClothing c : clothingToSell) {
			this.addClothing(c, 2+Util.random.nextInt(5), false, false);
		}
		
		for(AbstractClothing c : Main.game.getCharacterUtils().generateEnchantedClothingForTrader(this, clothingToSell, 6, 2)) {
			this.addClothing(c, false);
		}
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isExtendedWorkTime()) {
				this.returnToHome();
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
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
	public String getTraderDescription() {
		return "";
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return (item instanceof AbstractClothing)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_LIGHT)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_MEDIUM)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_HEAVY);
	}

	@Override
	public void endSex() {
		setPenisType(PenisType.NONE);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public boolean isAffectedBySleepingStatusEffect() {
		return true;
	}
	
	@Override
	public boolean isSleepingAtHour(int hour) {
		return this.isAtHome(); // Always sleeping when on home tile
	}
	
	@Override
	public Value<Boolean, String> getItemUseEffects(AbstractItem item,  GameCharacter itemOwner, GameCharacter user, GameCharacter target) {
		if(user.isPlayer() && !target.isPlayer() && !target.isAsleep()) {
			if(item.isTypeOneOf("innoxia_pills_fertility", "innoxia_pills_broodmother")) {
				String useDesc = itemOwner.useItem(item, target, false, true);
				return new Value<>(true,
						"<p>"
							+ "你从物品栏中拿出一个"+item.getName(false, false)+"，打开塑料包装后塞到凯特嘴里。"
							+ "她咯咯笑着，欣然咽下了那小小的"+item.getColour(0).getName()+"药片，因为她知道这药片会让她的子宫更容易受孕。"
						+ "</p>"
						+ useDesc);
				
			} else if(item.getItemType()==ItemType.PREGNANCY_TEST) {
				String useDesc = itemOwner.useItem(item, target, false, true);
				if(this.isPregnant()) {
					this.setCharacterReactedToPregnancy(user, true);
					String litterCount = Util.intToString(this.getPregnantLitter().getTotalLitterCount());
					return new Value<>(true,
							"<p>"
								+ "你从物品栏中拿出"+item.getName(true, false)+"放到凯特的肚子上并观察结果……"
							+ "</p>"
							+ useDesc
							+"<p>"
								+ (this.isVisiblyPregnant()
									?"[kate.speechNoEffects(你在期待些什么？)]凯特笑着，揉了揉孕肚并冲你眨了眨眼睛。[kate.speech(你已经看出我怀孕了，对吧？)]"
									:"[kate.speechNoEffects(~哦呜！~我怀孕了！)]"
											+ (this.getPregnantLitter().getTotalLitterCount()>2
													?"凯特惊叫着，揉了揉肚子，咬着嘴唇说：[kate.speechNoEffects(我这里有"+litterCount+"个孩子？~唔姆！~我的肚子会变得好大好大……)]"
													:"凯特呻吟着，揉了揉肚子并冲你眨了眨眼睛，[kate.speech(我很快就会有个又大又圆的肚子！)]"))
							+ "</p>");
					
				} else {
					return new Value<>(true,
							"<p>"
									+ "你从物品栏中拿出"+item.getName(true, false)+"放到凯特的肚子上并观察结果……"
							+ "</p>"
							+ useDesc
							+"<p>"
								+ (this.hasStatusEffect(StatusEffect.PREGNANT_0)
									?"[kate.speechNoEffects(~噢！~我没有怀孕！)]凯特抱怨着，揉了揉肚子撅着嘴看你。[kate.speech(来吧，[pc.name]，你还有很多时间来改变！)]"
									:"[kate.speechNoEffects(你在期待些什么？我当然不会怀孕！)]凯特大笑着说，她咬着嘴唇，揉了揉肚子。"
											+ "[kate.speech(虽然你还有很多机会来改变我……)]")
							+ "</p>");
				}
				
			} else {
				return new Value<>(false,
						"<p>"
							+ "你开始从背包中拿出"+item.getItemType().getDeterminer()+" "+item.getName()+"但凯特皱了皱眉，飞快地踢开了你的手。"
						+ "</p>");
			}
		}
		return super.getItemUseEffects(item, itemOwner, user, target);
	}
	
	@Override
	public String getCondomEquipEffects(AbstractClothingType condomClothingType, GameCharacter equipper, GameCharacter target, boolean rough) {
		if(Main.game.isInSex() && !target.isAsleep()) {
			if(!target.equals(equipper) && !target.isPlayer()) {
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return null;
				}
				return "<p>"
							+ "掏出一个避孕套，强迫[kate.name]拿起并戴上。"
							+ "[kate.she]迅速把它从小小的铝箔包装纸中撕开，捋到[kate.her][kate.cock+]的根部并向你抱怨，"
							+ "[kate.speech(一定要这样吗？不戴着可舒服多了……)]"
						+ "</p>";
			}
			if(target.equals(equipper) && target.isPlayer() && !this.isAsleep()) {
				AbstractClothing clothing = target.getClothingInSlot(InventorySlot.PENIS);
				if(clothing!=null && clothing.isCondom()) {
					target.unequipClothingIntoVoid(clothing, true, equipper);
					target.getInventory().resetEquipDescription();
				}
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return UtilText.parse(equipper, target,
							"你将丝囊对准自己的[npc.cock]，想要织个丝网避孕套，但凯特见状一巴掌将其拍开，咯咯笑了起来，"
							+ " [kate.speech(别这样！没有精液射进来还有什么意思！)]");
				}
				return "<p>"
							+ "你拿出一个避孕套，凯特脸上掠过一丝担忧，"
							+ "[kate.speech(哦！呃，我帮你戴上吧！)]"
							+"<br/>"
							+ "还没等你反应过来，凯特就从你手中夺过避孕套，奸诈地一笑，用她锋利的小犬齿直接在中间[style.colourBad(咬了一个大洞)]。"
							+ "她被你震惊的反应逗笑了，"
							+ "[kate.speech(不被精液射到就没意思了！)]"
						+ "</p>";
			}
		}
		return null;
	}
	
	
	// Dirty talk:

	/**
	 * @return A <b>non-formatted</b> String of this NPCs speech related to no ongoing penetration.
	 */
	@Override
	public String getDirtyTalkNoPenetration(GameCharacter target, boolean isPlayerDom){
		List<String> speech = new ArrayList<>();

		speech.add("来吧！操我吧！");
		speech.add("求你了，快点开始吧！");
		speech.add("我的小骚穴太需要你了！");

		String returnedLine = speech.get(Util.random.nextInt(speech.size()));
		return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
	}
	
	private Tattoo getKatesGroinTattoo() {
		Tattoo tat = new Tattoo(
				TattooType.getTattooTypeFromId("innoxia_heartWomb_heart_womb"),
				PresetColour.CLOTHING_PINK,
				PresetColour.CLOTHING_PINK_LIGHT,
				PresetColour.CLOTHING_PURPLE,
				true,
				new TattooWriting(
						"让我怀孕！",
						PresetColour.CLOTHING_PINK_LIGHT,
						true,
						TattooWritingStyle.ITALICISED),
				new TattooCounter(
						TattooCounterType.CURRENT_PREGNANCY,
						TattooCountType.NUMBERS,
						PresetColour.CLOTHING_PINK_LIGHT,
						true,
						0));
		
		for(int i=0; i<10; i++) {
			tat.addEffect(new ItemEffect(ItemEffectType.TATTOO, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0));
		}
		return tat;
	}
}
