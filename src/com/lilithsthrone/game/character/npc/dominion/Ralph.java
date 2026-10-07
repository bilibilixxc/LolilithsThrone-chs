package com.lilithsthrone.game.character.npc.dominion;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.*;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.*;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.Month;
import java.util.List;

/**
 * @since 0.1.0
 * @version 0.3.9
 * @author Innoxia
 */
public class Ralph extends NPC {
	
	public static final String RALPH_DISCOUNT_TIMER_ID = "ralph_discount_timer";
	
	public Ralph() {
		this(false);
	}
	
	public Ralph(boolean isImported) {
		super(isImported, new NameTriplet("拉尔夫"), "阿姆斯特朗",
				"拉尔夫是“拉尔夫小吃店”的店主。他的举止充满自信，而且始终保持着职业风范。",
				34, Month.MAY, 17,
				10, Gender.M_P_MALE, Subspecies.HORSE_MORPH, RaceStage.GREATER,
				new CharacterInventory(false, 10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RALPHS_SHOP, true);
		
		if(!isImported) {
			dailyUpdate();
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 35);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.5")) {
			this.setPenisSize(30);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.1")) {
			this.setPenisCumStorage(250);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.11.1")) { // For some reason my test save didn't have Ralph as having these fetishes, so I made sure to re-add them in
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_IMPREGNATION);
		}
	}
	
	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.5")) {
			this.setPenisSize(30);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.1")) {
			this.setPenisCumStorage(250);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.11.1")) { // For some reason my test save didn't have Ralph as having these fetishes, so I made sure to re-add them in
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_IMPREGNATION);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 3),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
		}
		
		if(setFetishes) {
			this.clearFetishDesires();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_IMPREGNATION);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_STORE_OWNER);
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
		this.setHeight(195);
		this.setFemininity(5);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, PresetColour.EYE_BROWN));
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_EBONY), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		this.setHairLength(HairLength.TWO_SHORT);
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.THREE_TRIMMED);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(true);
		// Leave as default:
//		this.setLipSize(LipSize.ONE_AVERAGE);
//		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		// Throat settings and modifiers
//		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		// Leave as default:
//		this.setNippleVirgin(true);
//		this.setBreastSize(CupSize.FLAT.getMeasurement());
//		this.setBreastShape(BreastShape.ROUND);
//		this.setNippleSize(NippleSize.ZERO_TINY);
//		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		// Leave as default:
//		this.setAssSize(AssSize.TWO_SMALL);
//		this.setHipSize(HipSize.TWO_NARROW);
//		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
//		this.setAssWetness(Wetness.ZERO_DRY);
//		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
//		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(30);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(250);
		this.fillCumToMaxStorage();
		// Leave cum as normal value
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_jeans", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_GOLD, false), true, this);

	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	/**
	 * Discount is active for three days after earning it.
	 */
	public boolean isDiscountActive(){
		if(Main.game.getDialogueFlags().getSavedLong(RALPH_DISCOUNT_TIMER_ID) == -1 || Main.game.getDialogueFlags().ralphDiscount <= 0) {
			return false;
		} else {
			return (Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(RALPH_DISCOUNT_TIMER_ID)) < (60*24*3);
		}
	}

	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);
		
		this.addItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), 25, false, false);
		this.addItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), 10, false, false);
		
		for(AbstractItemType item : ItemType.getAllItems()) {
			if(item.getItemTags().contains(ItemTag.SOLD_BY_RALPH)
					&& (!item.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				this.addItem(Main.game.getItemGen().generateItem(item), !item.isConsumedOnUse()?1:(6+Util.random.nextInt(12)), false, false);
			}
		}

		for(AbstractWeaponType weapon : WeaponType.getAllWeapons()) {
			if(weapon.getItemTags().contains(ItemTag.SOLD_BY_RALPH)
					&& (!weapon.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				this.addWeapon(Main.game.getItemGen().generateWeapon(weapon), 1+Util.random.nextInt(5), false, false);
			}
		}
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_RALPH)
					&& (!clothing.getDefaultItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				if(clothing.isDefaultSlotCondom()) {
					Colour condomColour = clothing.getColourReplacement(0).getRandomOfDefaultColours();
					Colour condomColourSec = PresetColour.CLOTHING_BLACK;
					Colour condomColourTer = PresetColour.CLOTHING_BLACK;
					
					if(clothing.getColourReplacement(1)!=null) {
						condomColourSec = clothing.getColourReplacement(1).getRandomOfDefaultColours();
					}
					if(clothing.getColourReplacement(2)!=null) {
						condomColourTer = clothing.getColourReplacement(2).getRandomOfDefaultColours();
					}
					for (int i = 0; i < (3+(Util.random.nextInt(4)))*(clothing.getRarity()==Rarity.COMMON?3:(clothing.getRarity()==Rarity.UNCOMMON?2:1)); i++) {
						this.addClothing(Main.game.getItemGen().generateClothing(clothing, condomColour, condomColourSec, condomColourTer, false), false);
					}
					
				} else {
					this.addClothing(Main.game.getItemGen().generateClothing(clothing, false), false);
				}
			}
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
		if(Main.game.getDialogueFlags().getSavedLong(RALPH_DISCOUNT_TIMER_ID)>0){
			StringBuilder descriptionSB = new StringBuilder();
			
			descriptionSB.append("<p>"
						+ "你看了看柜台，发现拉尔夫正微笑着看着你。他感觉到你可能需要一些帮助，便轻快地走到你这里。"
					+ "</p>"
					+ "<p>"
						+ "虽然你们对顺从地取悦拉尔夫的巨大马鸡巴记忆犹新，但他对你们非常尊重，并礼貌地列出了展示的改造消耗品的价格。"
						+ (this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_sterility"))
								?"大多数商品标价似乎都很合理，但小号蓝色的‘"+ItemType.getItemTypeFromId("innoxia_pills_sterility").getNamePlural(false)+"’价格似乎不对劲，"
								+ "并且拉尔夫再次注意到，出售它们需要缴纳巨额税款。"
							+ "</p>"
							+ "<p>"
						:"")
						+ "他提醒你，他也有兴趣购买你愿意出售的任何改造消耗品。"
						+ "当你回头看货物时，拉尔夫告诉你，他每天都会更新存货。"
					+ "</p>");
			
			if(isDiscountActive()){
				descriptionSB.append("<p>"
						+ "<b>拉尔夫会给你</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+Main.game.getDialogueFlags().ralphDiscount+"%</b>的<b>折扣！</b>"
							+ "</p>");
			}else{
				descriptionSB.append("<p>"
						+ "在他转身走回柜台之前，俯身在你耳边低语了几句，"
						+ UtilText.parseSpeech("如果你有兴趣再赚点折扣，再来找我吧。", Main.game.getNpc(Ralph.class))
							+ "</p>");
			}
			
			return descriptionSB.toString();
			
		} else {
			return "<p>"
						+ "你看了看柜台，发现拉尔夫正微笑着看着你。他感觉到你可能需要一些帮助，便轻快地走到你这里。"
					+ "</p>"
					+ "<p>"
						+ "简短的问候之后，你向他打听展示的转化消耗品。他礼貌地告诉你，这些东西都在出售，并很快列出了它们的价格"
						+ (this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_sterility"))
							?"大多数商品标价似乎都很合理，但小号蓝色的‘"+ItemType.getItemTypeFromId("innoxia_pills_sterility").getNamePlural(false)+"’价格似乎不对劲，"
									+ "向拉尔夫询问了其高昂的价格后，他解释说，出售这些产品需要缴纳巨额税款。"
								+ "</p>"
								+ "<p>"
							:"")
						+ "他感觉到你可能不是一个寻常的客户，于是告诉你，他也有兴趣购买你愿意出售的任何转化消耗品。"
						+ "当你回头看货物时，拉尔夫告诉你，他每天都会更新存货。"
					+ "</p>";
		}
		
	}

	@Override
	public boolean isTrader() {
		return true;
	}
	
	@Override
	public float getSellModifier(AbstractCoreItem item) {
		float base = 1.5f;
		if(item instanceof AbstractItem) {
			if(((AbstractItem)item).getItemType()==ItemType.getItemTypeFromId("innoxia_pills_sterility")) {
				base*=10;
			}
		}
		return Math.max(getBuyModifier(), (base * ((100-Main.game.getDialogueFlags().ralphDiscount)/100f)) * (Main.game.getPlayer().hasTrait(Perk.JOB_STUDENT, true)?0.75f:1));
	}
	
	@Override
	public boolean willBuy(AbstractCoreItem item) {
		if(item.getItemTags().contains(ItemTag.CONTRABAND_LIGHT)
				|| item.getItemTags().contains(ItemTag.CONTRABAND_MEDIUM)
				|| item.getItemTags().contains(ItemTag.CONTRABAND_HEAVY)) {
			return false;
		}
		if(item instanceof AbstractItem) {
			return true;
		}
		if(item instanceof AbstractClothing) {
			AbstractClothingType type = ((AbstractClothing)item).getClothingType();
			return type.isDefaultSlotCondom();
		}
		
		return false;
	}

	@Override
	public void endSex() {
		for(AbstractClothing c : this.getClothingCurrentlyEquipped()) {
			c.getDisplacedList().clear();
		}
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.sex.getSexManager().getPosition() == SexPosition.OVER_DESK) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
		}
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.sex.getSexManager().getPosition() == SexPosition.OVER_DESK) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
		}
		return super.getMainSexPreference(target);
	}
	
	@Override
	public String getCondomEquipEffects(AbstractClothingType condomClothingType, GameCharacter equipper, GameCharacter target, boolean rough) {
		if(!target.equals(equipper) && Main.game.isInSex() && !target.isAsleep()) {
			if(Main.sex.getSexManager().getPosition() == SexPosition.OVER_DESK && target.equals(this)) {
				AbstractClothing clothing = target.getClothingInSlot(InventorySlot.PENIS);
				if(clothing!=null && clothing.isCondom()) {
					target.unequipClothingIntoVoid(clothing, true, equipper);
					target.getInventory().resetEquipDescription();
				}
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return UtilText.parse(equipper, target,
							"[npc.Name]将[npc.her]的丝囊对准了[npc2.namePos]的[npc2.cock]，但当意识到[npc.sheIs]的意图后，他一巴掌拍了上去，然后哼了一声，"
							+ "[npc2.speech(我可不这么想！你同意让我给你配种的，而我也正打算这么做！)]");
				}
				return UtilText.parse(target,
						"<p>"
							+ "你掏出一个避孕套递给拉尔夫，但当他看到你拿的是什么东西时，他一把抓住避孕套并撕烂，然后不屑地哼了一声，"
							+ "[npc.speech(我可不这么想！你同意让我给你配种的，而我也正打算这么做！)]"
						+ "</p>");
			}
			
			if(!target.isPlayer()) {
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return null;
				}
				if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return "<p>"
							+ "你从包里拿出一个避孕套，发出低沉的质问声，举到拉尔夫面前。"
								+ "他低头看了看你，然后点点头，退后一步，把他的巨大鸡巴从你嘴里抽出来。"
								+ "当拉尔夫撕开铝箔小包装，将避孕套顺着他巨大的阴茎捋到根部时，你有片刻的喘息时间。"
								+ "他再次向前一步，把避孕套包裹着的鸡巴塞回你的喉咙，你松了一口气，很高兴他按照你的要求做了。"
						+ "</p>";
				} else if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return "<p>"
								+ "拉尔夫继续用他巨大的肉棒剧烈抽插着你的小穴，你摸索物品栏，取出一个避孕套。"
									+ "你抑制住自己的呻吟，回头伸出手，让他给你戴上。"
									+ "他失望地叹了口气，但并没有表示拒绝，而是把鸡巴从你的阴道里抽了出来， 给你一点时间来喘气，"
										+ "而他扯开了铝箔小包装，然后将避孕套顺着他巨大的阴茎捋到了根部。"
									+ "随着避孕套被牢牢戴好，他将自己的鸡巴对准，然后向前一推，将避孕套包裹的鸡巴深深地插进你的小穴。"
							+ "</p>";
				} else if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return "<p>"
						+ "拉尔夫继续用他巨大的肉棒剧烈抽插着你的屁眼，你摸索物品栏，取出一个避孕套。"
							+ "你抑制住自己的呻吟，回头伸出手，让他给你戴上。"
							+ "他发出一声失望的叹息，但并没有表示拒绝，而是将他的鸡巴从你的后庭中抽出，让你有片刻的喘息时间，"
								+ "而他扯开了铝箔小包装，然后将避孕套顺着他巨大的阴茎捋到了根部。"
							+ "随着避孕套被牢牢戴好，他将自己的鸡巴对准，然后向前一推，将避孕套包裹的鸡巴深深地插进你的肛门。"
						+ "</p>";
				} else {
					return "<p>"
						+ "你从物品栏中取出一个避孕套，看向拉尔夫，把小铝箔包装举到他面前，让他戴上。"
								+ "他叹了口气，从你手中接过避孕套，撕开包装，迅速将避孕套捋到他巨大的阴茎上。"
								+ "你感谢他按照你的要求做了，他回答说他很乐意尊重你的要求。"
					+ "</p>";
				}
			}
		}
		return null;
	}
	
	
	@Override
	public String getPenetrationDescription(boolean initialPenetration, GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaInterface orifice) {
		if(Math.random()>0.3) {
			if(penetrationType == SexAreaPenetration.PENIS && orifice == SexAreaOrifice.VAGINA) {
				return UtilText.returnStringAtRandom(
						"拉尔夫一边操你[pc.pussy+]，一边把你推向台面。",
						"拉尔夫的[ralph.cock+]你[pc.pussy+]上猛烈撞击，你感觉到拉尔夫有力地抓住了你的臀部。",
						"拉尔夫的[ralph.cock+]继续在你贪婪的[pc.pussy]里进进出出。",
						"拉尔夫在台面上操你，你的阴唇紧紧包住他的[ralph.cock+]。");
			}
			
			if(penetrationType == SexAreaPenetration.PENIS && orifice == SexAreaOrifice.MOUTH) {
				return UtilText.returnStringAtRandom(
						"你继续吸吮拉尔夫的[ralph.cock+]，黏糊糊的唾液顺着你的下巴流了下来。",
						"你低头看着[ralph.cock+]在你嘴里抽插。",
						"你跪在他身下，拉尔夫的[ralph.cock+]从你的唇边挤了过去。",
						"你一边前后晃动脑袋，一边继续为拉尔夫的[ralph.cock+]提供服务。");
			}
		}

		return super.getPenetrationDescription(initialPenetration, characterPenetrating, penetrationType, characterPenetrated, orifice);
	}
	
	// Vagina:
	
	@Override
	public String getStretchingDescription(boolean initialPenetration, GameCharacter partner, SexAreaPenetration penetrationType, SexAreaOrifice orifice, boolean pastTense) {
		switch(orifice) {
			case MOUTH:
				if(Math.random()<0.3) {
					return super.getStretchingDescription(initialPenetration, partner, penetrationType, orifice, pastTense);
				} else {
					return UtilText.returnStringAtRandom(
							"你把[ralph.cock+]硬塞进喉咙，瞬间感到泪流满面。",
							"你的技术还不够娴熟，无法自如地驾驭拉尔夫的[ralph.cock+]。",
							"你一边扭动，一边干呕着，竭尽全力把[ralph.cock+]塞进喉咙。",
							"你努力把拉尔夫的[ralph.cock+]塞进喉咙。");
				}
			case VAGINA:
				if(Math.random()<0.3) {
					return super.getStretchingDescription(initialPenetration, partner, penetrationType, orifice, pastTense);
				} else {
					return UtilText.returnStringAtRandom(
							"拉尔夫的[ralph.cock+]奋力插入你的[pc.pussyCapacity][pc.pussy]，你不舒服地在台面上扭动。",
							"拉尔夫的[ralph.cock+]在你[pc.pussyCapacity]的[pc.pussy]里抽插让你痛不欲生。",
							"拉尔夫[ralph.cock+]对你的[pc.pussyCapacity][pc.pussy]来说太大了，当你伸展身体时，你发出难受的呜呜声。",
							"你的[pc.pussyCapacity][pc.pussy]努力适应拉尔夫巨大的[ralph.cock+]。");
				}
			default:
				return super.getStretchingDescription(initialPenetration, partner, penetrationType, orifice, pastTense);
		}
	}
	
	@Override
	public String getStretchingFinishedDescription(SexAreaOrifice orifice) {
		switch(orifice) {
			case MOUTH:
				return "拉尔夫温和向前推进，你感觉喉咙本能地张开以容纳他涨得像马一般的阴茎，"
						+ "你发出一声沉闷的呻吟，意识到自己现在能够舒适地吮吸拉尔夫巨大的黑色马屌！";
			case VAGINA:
				return "拉尔夫再次向前冲刺时，伴随着愉悦的呻吟声，你感觉到小穴被拉尔夫膨胀的马屌轻易撑开。"
						+ "你意识到自己[pc.pussy+]现在能够舒适地迎合拉尔夫巨大的黑色马屌！";
			default:
				return super.getStretchingFinishedDescription(orifice);
		}
	}
	
	@Override
	public Value<Boolean, String> getItemUseEffects(AbstractItem item, GameCharacter itemOwner, GameCharacter user, GameCharacter target) {
		if(user.isPlayer() && !target.isPlayer()) {
			if(item.isTypeOneOf("innoxia_pills_fertility", "innoxia_pills_broodmother")) {
				itemOwner.useItem(item, target, false);
				
				if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return new Value<>(true,
							"<p>"
								+ "你从包里拿出一个 "+item.getName(false, false)+" ，发出低沉的质问声，举到拉尔夫面前。"
								+ "他看到了你试图让他看到的东西后，低头看向你，耸了耸肩，发出了轻笑。"
								+ "他很快把药丸从塑料壳里取出吞下，你发出了尽管有点压抑但快乐的咯咯声，你知道他的精子变得更强大了。"
							+ "</p>");
				
				} else if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return new Value<>(true,
							"<p>"
								+ "拉尔夫继续用他巨大的肉棒剧烈抽插着你的小穴，你摸索物品栏，取出一个"+item.getName(false, false)+"。"
								+ "你抑制住自己的呻吟，回头伸出手，让他把它吞下去。"
								+ "当他看到你给他的东西时，他发出了一阵笑声，并迅速从包装里挤出药片吞了下去，"
								+ "他花了一点时间说了几句话然后继续操你，"
								+ (Main.game.getNpc(Ralph.class).isWearingCondom()
									?"[ralph.speech(你知道我戴着避孕套的，对吧？)]"
									:(Main.game.getPlayer().isVisiblyPregnant()
											?"[ralph.speech(呃--，你怀孕了，但当然了，为什么没有呢？)]"
											:"[ralph.speech(想要怀孕？嗯？我非常乐意帮忙！)]"))
							+ "</p>");
					
				} else if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return new Value<>(true,
								"<p>"
									+ "拉尔夫继续用他巨大的肉棒剧烈抽插着你的屁眼，你摸索物品栏，取出一个"+item.getName(false, false)+"。"
									+ "你抑制住自己的呻吟，回头伸出手，让他把它吞下去。"
									+ "当他看到你给他的东西时，他发出了一阵笑声，并迅速从包装里挤出药片吞了下去，"
									+ "他花了一点时间说了几句话然后继续操你，"
									+ "[ralph.speech(你知道这样做不会怀孕，对吧？)]"
								+ "</p>");
					
				} else {
					return new Value<>(true,
							"<p>"
								+ "拿出"+item.getName(false, false)+"你向前倾身，抬起头看着拉尔夫，一边举着一边让他吞下去。"
									+ "他看到了你试图让他看到的东西后，低头看向你，耸了耸肩，发出了轻笑。"
									+ "他迅速从包装中挤出药丸，吞了下去，你开心地笑了，因为你知道他的精子变得更有活力了。"
							+ "</p>");
				}
				
			} else {
				if(!Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH, Main.game.getNpc(Ralph.class)).isEmpty()) {
					return new Value<>(false,
							"<p>"
								+ "你从包里拿出一个"+item.getItemType().getDeterminer()+" "+item.getName()+"然后发出低沉的询问声，把它举到拉尔夫面前。"
								+ "当你把"+item.getName()+"放回包里时，他低头看了你一眼，然后轻蔑地哼了一声，微微向前走了一步，把他的鸡巴又往你喉咙里塞了一点。"
							+ "</p>");
					
				} else if(!Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA, Main.game.getNpc(Ralph.class)).isEmpty()) {
					return new Value<>(false,
							"<p>"
								+ "拉尔夫继续用他巨大的肉棒剧烈抽插着你的小穴，你摸索物品栏，取出一个"+item.getItemType().getDeterminer()+" "+item.getName()+"。"
								+ "你抑制住自己的呻吟，问他是否愿意"+item.getItemType().getUseName()+"它，但他只是轻蔑地哼了一声，继续操你。"
								+ "你把 "+item.getName()+"放回背包，又开始喘息，并愉悦地叫出来。"
							+ "</p>");
					
				} else if(!Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, Main.game.getNpc(Ralph.class)).isEmpty()) {
					return new Value<>(false,
							"<p>"
								+ "拉尔夫继续用他巨大的肉棒剧烈抽插着你的屁眼，你摸索物品栏，取出一个"+item.getItemType().getDeterminer()+" "+item.getName()+"。"
								+ "你强忍着呻吟，问他是否愿意"+item.getItemType().getUseName()+"，但他只是轻蔑地哼了一声，继续操你。"
								+ "你把 "+item.getName()+"放回背包，又开始喘息，并愉悦地叫出来。"
							+ "</p>");
					
				} else {
					return new Value<>(false,
							"<p>"
								+ "你问拉尔夫是否愿意使用你的 "+item.getItemType().getDeterminer()+""+item.getName()+"，但他只是轻蔑地哼了一声，告诉你他不感兴趣。"
								+ "你叹了口气，把"+item.getName()+"放回物品栏。"
							+ "</p>");
				}
			}
		}
		return super.getItemUseEffects(item, itemOwner, user, target);
	}
	
}
