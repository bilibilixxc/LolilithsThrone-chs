package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
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
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
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
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.2
 * @version 0.4.2
 * @author Innoxia
 */
public class Dale extends NPC {

	public Dale() {
		this(false);
	}
	
	public Dale(boolean isImported) {
		super(isImported, new NameTriplet("戴尔"), "雷恩",
				"戴尔是艾弗利克斯乳业的接待员。",
				28, Month.JANUARY, 2,
				15,
				Gender.M_P_MALE, Subspecies.HORSE_MORPH_DONKEY, RaceStage.GREATER,
				new CharacterInventory(false, 10),
				WorldType.getWorldTypeFromId("innoxia_fields_dairyFarm"), PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_reception"),
				true);
		if(!isImported) {
			this.setPlayerKnowsName(false);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.8")) {
			this.setStartingBody(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.10.3")) {
			this.setupPerks(true);
		}
	}

	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.8")) {
			this.setStartingBody(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.10.3")) {
			this.setupPerks(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(Perk.HEAVY_SLEEPER),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 0)));
		
		if(!Main.game.getDialogueFlags().hasFlag("innoxia_dale_teddy_bear_given")) {
			this.removeTrait(Perk.HEAVY_SLEEPER);
		}
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.KIND);
		}
		
		if(setFetishes) {
			this.clearFetishDesires();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_DEFLOWERING);
			
			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
			
//			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ONE_DISLIKE);
			
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_RECEPTIONIST);
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
		this.setHeight(178);
		this.setFemininity(25);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, PresetColour.EYE_BROWN));
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);
		body.getCoverings().put(BodyCoveringType.HORSE_HAIR, new Covering(BodyCoveringType.HORSE_HAIR, CoveringPattern.MARKED, CoveringModifier.SHORT, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false));

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false), false);
		body.getCoverings().put(BodyCoveringType.HAIR_HORSE_HAIR, new Covering(BodyCoveringType.HAIR_HORSE_HAIR, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false));
		this.setHairLength(10);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.FLAT.getMeasurement());
		this.setBreastShape(BreastShape.SIDE_SET);
		this.setNippleSize(NippleSize.ZERO_TINY);
		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.TWO_NARROW);
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setPenisSize(26);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(120);
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
		
		if(this.getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_dormitory")) { // Sleeping
			//this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_DESATURATED_BROWN, false), true, this);
			
		} else {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_DESATURATED_BROWN, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_IRON, PresetColour.CLOTHING_IRON, PresetColour.CLOTHING_IRON, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_trousers", PresetColour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hips_leather_belt", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_long_sleeved_shirt", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), true, this);
		}
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	private boolean needsMoving = false;
	@Override
	public void hourlyUpdate(int hour) {
		// Sleeps between 01:00-05:00
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(((hour>=1 && hour<5) && this.getLocationPlaceType()!=PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_dormitory"))
					 || (!(hour>=1 && hour<5) && this.getLocationPlaceType()!=PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_reception"))) {
				needsMoving = true;
			}
		}
	}
	
	@Override
	public void turnUpdate() {
		// Sleeps between 01:00-05:00
		if(needsMoving && !Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isHourBetween(1, 5)) {
				Main.game.getDialogueFlags().setFlag("innoxia_evelyx_reception_sex", false);
				this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_dairyFarm"), PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_dormitory"), true);
				this.equipClothing();
				Main.game.updateResponses();
				
			} else {
				Main.game.getDialogueFlags().setFlag("innoxia_evelyx_dorm_sex", false);
				this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_dairyFarm"), PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_reception"), false);
				this.equipClothing();
			}
		}
	}
	
	@Override
	public void endSex() {
		this.cleanAllDirtySlots(true);
		this.cleanAllClothing(true, false);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
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
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public SexPace getSexPaceDomPreference() {
		return SexPace.DOM_GENTLE;
	}
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character) {
		return SexPace.SUB_EAGER;
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
						sb.append("当[npc.Name]缓缓将[npc.her]的[npc.cock+]顶入你[pc.pussy+]时，你不由自主地发出一声绝望，颤抖的哀号。"
									+ "你如此迷恋成为一个贞洁处女的想法，你不知道到底是什么东西在这样的地方拿走了你的童贞，"
										+ "[npc.namePos][npc.cock+]夺走了你宝贵的童贞，你没时间反思自己的选择。"
									+ "你的脑海中只剩下[npc.cockGirth]驴一样大的屌撕裂你的处女膜，攻占你的阴户时带来的痛苦。");
					} else if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
						sb.append("[npc.name]缓缓将[npc.cock+]顶入你[pc.pussy+]，撕裂处女膜并夺取你的童贞，你不由自主地发出一声受虐狂般的淫叫。"
								+ "[npc.namePos]的[npc.cockGirth]驴一般大的屌撕裂你处女膜时的剧痛彻底压垮了你，你不由自主地在狂喜之中尖叫呻吟着。");
					} else {
						sb.append("[npc.Name]缓缓将[npc.cock+]顶入你[pc.pussy+]，夺取你的童贞，你不由自主地发出一声狂乱，颤抖的哀号。" 
								+ "[npc.namePos][npc.cockGirth]的驴屌将你的处女膜撕裂，那股剧痛彻底压垮了你，你难受地扭动着，试图忍过这种痛苦。"); 
					}
				sb.append("</p>");
				
			} else {
				sb.append("<p>");
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("[npc.Name]缓缓将[npc.her][npc.cock+]顶入你[pc.pussy+]，你不由自主地发出一声狂乱，颤抖的哀号。"
								+ "你如此迷恋成为一个贞洁处女的想法，不知道到底是什么东西占据了你的思维，让你同意这样做，"
									+ "[npc.namePos][npc.cock+]夺走了你宝贵的童贞，你没时间反思自己的选择。"
								+ "你脑海中唯一剩下的只有你被[npc.cockGirth]的驴屌破处，小穴被糟蹋的事实。");
					} else {
						sb.append("[npc.Name]缓缓将[npc.her][npc.cock+]顶入你[pc.pussy+]，夺取你的童贞，你不由自主地发出一声狂乱而淫荡的[pc.moan]。"
								+ "[npc.namePos]的[npc.cockGirth]驴一般大的屌给予你一种前所未有的强烈快感，你不由自主地在狂喜之中不停尖叫和[pc.moan]。");
					}
				sb.append("</p>");
			}
			
			sb.append("<p>"
						+ "[npc.name]听到你震耳欲聋的哀号，立刻停止前顶，担忧地问，"
						+ "[npc.speechNoExtraEffects(你还好吗，[pc.name]？如果你承受不住，我们就先停一停……)]"
					+ "</p>");
			
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("尽管你在意识到你失去了自己宝贵，纯洁的贞操时不禁潸然落泪，[npc.name]似乎真的很关心却让你感到很欣慰。");
				} else {
					sb.append("尽管从你那被肉棒塞满的小穴升起的痛感让你萌生退意， [npc.name]似乎真诚的关心却让你感到很欣慰。");
				}
				sb.append(" 在休息一会，调整好呼吸之后，你明白此刻木已成舟，所以你喘息着说，[pc.speechNoExtraEffects(我没事……你可以继续了……)]");
			sb.append("</p>");
			
			sb.append("<p>"
						+ "[npc.speechNoExtraEffects(好吧，我会慢慢来，)][npc.name]回答，[npc.she]从上次中断的地方继续，缓缓将[npc.her]硕大的驴屌顶入你的小穴时，你尽力忍住了呜咽。"
						+ "[npc.Name]用搏动的肉棒塞满你刚破处的小穴，又用柔和的咕哝宽慰着你，"
						+ "[npc.speechNoExtraEffects(你的第一次已经表现得很不错了……用不了多久你就会适应……)]"
					+ "</p>"
					+ "<p>"
						+ "理所当然地，[npc.her]把肉棒拔出你的小穴，疼痛就开始褪去，"
							+ "你专注于[npc.her]扁平，外展的柱头一路顶入被撕裂的小穴，[npc.she]又一次开始打桩的感觉，你发出一声淫荡的呻吟……"
					+ "</p>");
			
		} else if(penetrated == SexAreaOrifice.ANUS) {
			sb.append("<p>");
				sb.append("[npc.Name]缓缓将[npc.cock+]顶入你[pc.asshole+]，夺取你的童贞，你不由自主地发出一声绝望，颤抖的哀号。"
						+ "[npc.namePos][npc.cockGirth]的驴屌在你的身体内缓缓打开通道的不适感使你不舒服地扭动身体。");
			sb.append("</p>");
			
			sb.append("<p>"
						+ "[npc.name]听见你的痛叫，马上停下了前顶的动作，用担忧的语气问道，"
						+ "[npc.speechNoExtraEffects(你还好吗，[pc.name]？如果你承受不住，我们就先停一停……)]"
					+ "</p>");
			
			sb.append("<p>");
				sb.append("尽管从你那被肉棒塞满的屁穴升起的痛感让你萌生退意， [npc.name]似乎真诚的关心却让你感到很欣慰。");
				sb.append(" 在休息一会，调整好呼吸之后，你明白此刻木已成舟，所以你喘息着说，[pc.speechNoExtraEffects(我没事……你可以继续了……)]");
			sb.append("</p>");
			
			sb.append("<p>"
						+ "[npc.speechNoExtraEffects(好吧，我会慢慢来，)][npc.name]回答，[npc.she]从上次中断的地方继续，缓缓将[npc.her]硕大的驴屌顶入你的后门时，你尽力忍住了呜咽。"
						+ "[npc.name]用搏动的肉棒塞满你的屁穴，又用柔和的咕哝宽慰着你，"
						+ "[npc.speechNoExtraEffects(你的第一次已经表现得很不错了……用不了多久你就会适应……)]"
					+ "</p>"
					+ "<p>"
						+ "理所当然地，[npc.her]把肉棒拔出你的屁穴，疼痛就开始褪去，"
							+ "你专注于[npc.her]扁平，外展的柱头一路顶入你已遭攻入的屁穴。[npc.she]又一次开始打桩，你发出一声淫荡的呻吟……"
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
					+ "正当[npc.name]再度开始用[npc.her]灼热的驴屌捅插你的小穴时，突然意识到正在发生的现实一切如同重锤一般砸在你身上。"
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ "[pc.thought(我，我就这样失贞了？！"
					+ "<br/>就……就像<b>这样</b>？！)]"
				+ "</p>"
				+ "<p>"
					+ "你不知道哪个更糟糕；是失去了这样看重的第一次，还是自己反而乐在其中。"
					+ "你[pc.labia+]淫靡地包裹着那又热又粗，缓缓抽插着你的[npc.cock]，你开始劝说自己，这是你唯一的长处。"
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ "[pc.thought(如果我不再是处女，那我就是个骚货了……"
					+ "<br/>只是个活该被操，被注满精液的婊子罢了……"
					+ "<br/>我好奇是不是所有的鸡巴都像[npc.namePos]的那么给力……)]"
				+ "</p>"
				+ "<p>"
					+ "你隐约感觉到[npc.namePos]似乎不再说让你安心的话，而是将[npc.she]的精神逐渐集中到了操你上面。"
					+ "随着一声渴求的呻吟，"
					+ (Main.game.getPlayer().hasLegs()
						?"你张开双腿并"
						:"你")
					+ "只能接受现实，听之任之。现在的你什么都不是，只是个"
					+ "<b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>失格处女</b>……"
				+ "</p>";
	}
}
