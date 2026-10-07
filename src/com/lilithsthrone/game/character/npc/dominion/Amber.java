package com.lilithsthrone.game.character.npc.dominion;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.LoadGame;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
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
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.*;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMAmberDoggyFucked;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PartnerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.SadisticActions;
import com.lilithsthrone.game.sex.sexActions.dominion.AmberSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.95
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Amber extends NPC {

	public Amber() {
		this(false);
	}
	
	public Amber(boolean isImported) {
		super(isImported, new NameTriplet("安柏"), "莉瑞恰马尔图",
				"作为扎拉尼克斯的最高阶女仆，安柏显然因为你能在无人监督的情况下在她主人的房子旁闲逛而愤懑。",
				117, Month.OCTOBER, 17,
				15,
				null, null, null,
				new CharacterInventory(false, 10), WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, true);
		
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
		if(this.getBodyMaterial()!=BodyMaterial.FLESH) {
			this.setBodyMaterial(BodyMaterial.FLESH);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.8")) {
			this.setLevel(15);
			this.equipClothing();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.BRAVE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.9")) {
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.NONE, PresetColour.EYE_AMBER, true, PresetColour.EYE_AMBER, true));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, CoveringPattern.NONE, PresetColour.COVERING_AMBER, true, PresetColour.COVERING_AMBER, true), true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1")) {
			this.setFetishDesire(Fetish.FETISH_BONDAGE_APPLIER, FetishDesire.THREE_LIKE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.7.11")) {
			this.addSpecialPerk(Perk.MARTIAL_ARTIST);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.8")) {
			this.setAge(117);
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
		if(this.getBodyMaterial()!=BodyMaterial.FLESH) {
			this.setBodyMaterial(BodyMaterial.FLESH);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.8")) {
			this.setLevel(15);
			this.equipClothing();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.BRAVE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.9")) {
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.NONE, PresetColour.EYE_AMBER, true, PresetColour.EYE_AMBER, true));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, CoveringPattern.NONE, PresetColour.COVERING_AMBER, true, PresetColour.COVERING_AMBER, true), true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1")) {
			this.setFetishDesire(Fetish.FETISH_BONDAGE_APPLIER, FetishDesire.THREE_LIKE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.7.11")) {
			this.addSpecialPerk(Perk.MARTIAL_ARTIST);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.8")) {
			this.setAge(117);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		this.addSpecialPerk(Perk.MARTIAL_ARTIST);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.ORGASMIC_LEVEL_DRAIN,
						Perk.UNARMED_TRAINING),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 3),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 2)));
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.BRAVE);
		}
		
		if(setFetishes) {
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);
			this.addFetish(Fetish.FETISH_DEFLOWERING);
			this.addFetish(Fetish.FETISH_FOOT_GIVING);
			
			this.setFetishDesire(Fetish.FETISH_BONDAGE_APPLIER, FetishDesire.THREE_LIKE);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_MAID);
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
		// Add full body reset as this method is called after leaving Zaranix's house:
		this.setAgeAppearanceAbsolute(28);
		this.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER, false);
		this.setTailType(TailType.DEMON_HAIR_TIP);
		this.setWingType(WingType.NONE);
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.SWEPT_BACK);

		// Core:
		this.setHeight(180);
		this.setFemininity(85);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.NONE, PresetColour.EYE_AMBER, true, PresetColour.EYE_AMBER, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_EBONY), true);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.SKIN_EBONY, false, PresetColour.COVERING_AMBER, true), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, PresetColour.SKIN_EBONY, false, PresetColour.COVERING_AMBER, true), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, PresetColour.COVERING_AMBER, true, PresetColour.COVERING_AMBER, true), false);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_BLACK), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, CoveringPattern.NONE, PresetColour.COVERING_AMBER, true, PresetColour.COVERING_AMBER, true), true);
		this.setHairLength(HairLength.FIVE_ABOVE_ASS.getMedianValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, CoveringPattern.NONE, PresetColour.COVERING_AMBER, true, PresetColour.COVERING_AMBER, true), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_AMBER));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_AMBER));
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
		this.setBreastSize(CupSize.G.getMeasurement());
		this.setBreastShape(BreastShape.SIDE_SET);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(25);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(550);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		this.setVaginaSquirter(true);
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
		
		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_crystal_epic", DamageType.FIRE));
		this.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_crystal_epic", DamageType.FIRE));
		
		// Tattoos
		// Scars

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_RED_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_open_cup_bra", PresetColour.CLOTHING_RED_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_stomach_underbust_corset", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_dress", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_headpiece", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_stockings", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_sleeves", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_heels", PresetColour.CLOTHING_BLACK, false), true, this);

	}

	@Override
	public String getName(boolean applyNameAlteringEffects) {
		if(!playerKnowsName) {
			return "暴躁女仆";
			
		} else {
			return "安柏";
		}
	}
	
	@Override
	public String getDescription() {
		if(!playerKnowsName) {
			return "这个暴躁女仆显然因为你能在无人监督的情况下在她主人的房子旁闲逛而愤懑。";
			
		} else {
			return "作为扎拉尼克斯的最高女仆，安柏是你曾见过的最妩媚的魅魔之一。"
					+ "人如其名，她那琥珀色的头发与眼睛发出宝石般的光辉，与她的乌黑皮肤形成鲜明的对比。"
					+ "<br/>"
					+ "安柏是个残酷的施虐狂，喜欢把自己的支配强加于下属。";
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#FFB38A";
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
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_dress", PresetColour.CLOTHING_BLACK, false), true, this);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	

	// Combat:
	
	@Override
	public String getMainAttackDescription(int armRow, GameCharacter target, boolean isHit, boolean critical) {
		return "<p>"
					+ UtilText.parse(target,
							UtilText.returnStringAtRandom(
							"安柏的眼中燃起了恐怖的怒火，向着[npc.namePos]身侧踢了过去！",
							"安柏怒吼一声，一拳打在[npc.Name]胸口！",
							"安柏口中骂骂咧咧，愤怒地踢在了[npc.namePos]的小腿上！",
							"安柏的头发跟眼中一样，也燃起了怒火，她转身扭腰，嗖地一声，重重地拳打在[npc.namePos][npc.arm]上！"))
				+ "</p>";
	}
			
	@Override
	public String getSpellDescription() {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"安柏高声急呼，手臂高举到半空，释放了法术！",
						"安柏口吐咒言，目不转睛地盯着你的眼睛，释放了法术！",
						"安柏怒骂一声，冲上前来释放了法术！") 
			+ "</p>";
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
			if (victory) {
				return new Response("", "", ZaranixHomeGroundFloorRepeat.COMBAT_VICTORY);
			} else {
				return new Response("", "", ZaranixHomeGroundFloorRepeat.COMBAT_LOSS);
			}
			
		} else {
			if (victory) {
				return new Response("", "", AFTER_COMBAT_VICTORY) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixAmberSubdued, true);
					}
				};
			} else {
				return new Response("", "", AFTER_COMBAT_DEFEAT);
			}
			
		}
	}
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("胜利", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "安柏蹒跚着退下，最后靠着墙壁恨恨地骂道，"
						+ "[amber.speech(你这……"+(Main.game.getPlayer().isFeminine()?"婊子……":"混蛋……")+")]"
					+ "</p>"
					+ "<p>"
						+ "显然她已经无法继续战斗了，过了一会儿，你强大的奥术灵气的效果便展现出来。"
						+ "安柏的怒骂声渐渐转变成为格外下流的呻吟，头一次将那发光的眼睛从你身上移开，看向两腿之间，接着一只手伸进裙摆下。"
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(~啊！~操……)]"
						+ "她呻吟着，手臂的动作明显是在给自己扣。"
					+ "</p>"
					+ "<p>"
						+ "虽然战斗引发了不小的噪音，但是却没有听到其他女仆进到屋子里的声音。"
						+ "现在正是绝佳的时机，能够与这位总是怒气冲天但现在欲火焚身的女仆爽上一爽，你思索起来是否应该趁上这个机会，还是继续去找扎拉尼克斯。"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续探索扎拉尼克斯家。", PlaceType.ZARANIX_GF_ENTRANCE.getDialogue(false)) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getPlayerCell().getDialogue(false);
					}
				};
				
			} if(index==2) {
				return new ResponseSex("使用安柏",
						"跟这个火气很大的女仆爽一爽。",
						true,
						false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
						null,
						null),
						AFTER_SEX_VICTORY,
						"<p>"
							+ "看来同住一家的其他女仆并没有来帮她，你决定抓住机会，跟安柏玩一玩。"
							+ "你靠近她依靠的墙边，攥住了她的胳膊，接着把她的手从裙子下面扯了出来。"
							+ "安柏被剥夺了发泄的自由，可怜巴巴地抬起头，紧盯着你的眼睛，其中却没有丝毫愤怒，而是充满了燃烧的欲望。"
						+ "</p>"
						+ "<p>"
							+ "她推了一把离开了墙壁，上前抱住了你，立刻将她[amber.lips+]压在了你的嘴上。"
							+ "你也做出了回应，舌头在彼此的嘴里纠缠了片刻后，你们抽回舌头，淫笑起来……"
						+ "</p>");
				
			} else if(index==3) {
				return new ResponseSex("顺从",
						"安柏的暴脾气让你性欲高涨。你没法主导性爱，但又<i>非常</i>想跟她做爱。或许只要选择顺从，她也会愿意干你的？",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SEX_VICTORY,
						"<p>"
							+ "尽管她战败了，但你却发现自己被安柏霸道而暴躁的性格深深吸引了。"
							+ "你不愿主导性爱，但又渴望和这位欲火焚身的魅魔做爱，于是走到她依靠的墙边，叹道，"
							+ "[pc.speech(‘安柏小姐’，是吗？嗯……你如果想要的话，或许可以用我来发泄？我是说，我——)]"
						+ "</p>"
						+ "<p>"
							+ "尽管你是闯入她主人屋子里的陌生人，安柏却带着热烈的情感望向你。"
							+ "她从裙子底下抽出手来，把自己从墙边推离，控制住你的脑袋，直接来了一场热烈的吻，同时也打断了你的话。"
						+ "</p>"
						+ "<p>"
							+ "你做出了回应，但安柏只亲吻了片刻，就退出去呻吟道，"
							+ "[amber.speech(乖婊子！操……我太他妈想要了！我<i>想要</i>你！)]"
						+ "</p>");
				
			} else if (index == 4) {
				return new Response("转化",
						"让安柏使用[kelly.her]的恶魔能力来转化自己……",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getNpc(Amber.class));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("继续", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Amber.class)) >= Main.game.getNpc(Amber.class).getOrgasmsBeforeSatisfied()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "安柏深深地叹了一口气，然后筋疲力尽地倒在地上。"
							+ "尽管她很疲惫，但你看到她仍将其中一只手伸到两腿之间，很明显，她仍然欲火缠身，她开始在你面前自慰起来。"
							+ "她显然不会构成太大的威胁，所以你把注意力转向手头的任务；找到扎拉尼克斯并营救亚瑟。"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "安柏发出一声渴求的呜叫，双手抚上腹股沟，倒在地上。"
							+ "很明显，她由于没有得到满足而沉溺于欲望之中，瘫在地上疯狂地自慰。"
							+ "她显然不会构成太大的威胁，所以你把注意力转向手头的任务；找到扎拉尼克斯并营救亚瑟。"
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
							+ "你考虑到还有其他女仆需要担心，便准备向房子的更深处前进……"
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续探索扎拉尼克斯家。", Main.game.getPlayerCell().getDialogue(false)) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getPlayerCell().getDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("落败", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "事实证明，你应付不来这个红头发女仆。你失去了战斗能力，跌跌撞撞地靠在旁边的墙上。"
						+ "安柏看到你被打败，发出了嘲弄的笑声。她向你走来，咆哮着，"
						+ "[amber.speech(你这个愚蠢的婊子！现在你<i>真的</i>要付出代价了！)]"
					+ "</p>"
					+ "<p>"
						+ "你抬头一看，魅魔那燃烧的琥珀色眼睛怒视着你，在你能做出任何反应前，她伸出一只手，紧紧抓住你的脖子。"
						+ "她抓得更紧，使你咳嗽起来，只引得她又发出一阵嘲讽的笑声，"
						+ "[amber.speech(哈哈哈！真可悲！)]"
					+ "</p>"
					+ "<p>"
						+ "说完，她猛地将你摔到地上，你四肢着地，发出一声惨叫。"
						+ "安柏在你身后踱来踱去，当你试图爬走时，她狠狠地拍了你屁股一巴掌，"
						+ "[amber.speech(愚蠢的婊子！你把我惹急了！是时候教训教训你了！)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("被使用", "安柏开始操你。",
						false, false,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Amber.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_SEX_DEFEAT,
						"<p>"
							+ "安柏牢牢把住你的屁股，粗暴地让它再撅高点。"
							+ "她的手狠狠扇了你右脸一巴掌，你的呜咽被淹没在女仆气势汹汹的咆哮中，"
							+ "[amber.speech(婊子，你就吱吱叫吧，<i>你现在是我的了！</i>)]"
						+ "</p>");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("被使用", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "你瘫倒在地，安柏粗暴的对待使你筋疲力尽。"
						+ "最后，安柏挂着轻蔑的冷笑起身，"
						+ "[amber.speech(像你这样的贱人也就这样了！现在给我滚出去！)]"
					+ "</p>"
					+ "<p>"
						+ "安柏伸手抓住你的"+(Main.game.getPlayer().getHairRawLengthValue()>4?"一撮[pc.hair(true)]":"脖子")+"，毫不留情地把你拖到前门。"
						+ "她拉开门，对你吐出最后一句憎恨的话，然后一脚把你踹到街上，砰地一声甩上门。"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被扔出去", "安柏把你扔到了街上。", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	public int getLootMoney() {
		return 5000;
	}
	
	// Sex:

	@Override
	public boolean isLevelDrainAvailableToUse() {
		AbstractClothing neckClothing = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
		return Main.game.isLevelDrainContentEnabled()
				&& neckClothing!=null
				&& neckClothing.getClothingType().getId().equals("innoxia_neck_ambers_bitch_collar");
	}

	@Override
	public boolean isWantingToLevelDrain(GameCharacter target) {
		return target.isPlayer();
	}

	@Override
	public String getLevelDrainDescription(GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(UtilText.returnStringAtRandom(
				"安柏发出嘲弄的笑声，粗暴地抓住你，咆哮道，",
				"安柏炯炯有神的眼睛睁得大大的，带着残忍的笑意，说道，",
				"安柏发出残忍、嘲弄的笑声，贪婪地吸收着你的能量并嘲弄道，"
				));
		
		sb.append(UtilText.returnStringAtRandom(
				"[npc.speech(我要榨干你所有的能量！等我解决了你，你就只是我的一只宠物罢了！)]",
				"[npc.speech(你这个可悲的婊子！你所有的力量都将属于我！)]",
				"[npc.speech(你真是个可悲的贱人，你的力量就这样被耗尽了！)]"));
		
		return UtilText.parse(this, target, sb.toString());
	}
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(AmberSpecials.class);
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.sex.getSexManager().getPosition() == SexPosition.ALL_FOURS) {
			if(target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
			} else {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS);
			}
		}
		
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.sex.getSexManager().getPosition() == SexPosition.ALL_FOURS) {
			if(target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
			} else {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			}
		}

		return super.getMainSexPreference(target);
	}
	
	@Override
	public GameCharacter getPreferredSexTarget() {
		if(Main.sex.getSexManager().getDominants().containsKey(Main.game.getNpc(Zaranix.class))) { // Assisting the player to suck Zaranix's cock:
			return Main.game.getPlayer();
		}
		return super.getPreferredSexTarget();
	}
	
	@Override
	public List<SexActionInterface> getLimitedSexClasses() {
		if(Main.sex.getSexManager().getDominants().containsKey(Main.game.getNpc(Zaranix.class))) { // Assisting the player to suck Zaranix's cock:
			List<SexActionInterface> actionsAvailable = new ArrayList<>();
			
			actionsAvailable.add(FingerMouth.PARTNER_ASSIST_BLOWJOB);
			actionsAvailable.addAll(this.getSexActionInterfacesFromClass(SadisticActions.class));
			actionsAvailable.add(PartnerTalk.PARTNER_DIRTY_TALK);
			actionsAvailable.addAll(this.getSexActionInterfacesFromClass(GenericOrgasms.class));

//			actionsAvailable.addAll(this.getSexActionInterfacesFromClass(GenericActions.class));
//			actionsAvailable.addAll(this.getSexActionInterfacesFromClass(GenericTalk.class));
//			actionsAvailable.addAll(this.getSexActionInterfacesFromClass(PartnerTalk.class));
			
			return actionsAvailable;
		}
		return super.getLimitedSexClasses();
	}
	
	@Override
	public int getOrgasmsBeforeSatisfied() {
		if(Main.sex.getSexManager().getDominants().containsKey(Main.game.getNpc(Zaranix.class))) {
			return 0;
		}
		return super.getOrgasmsBeforeSatisfied();
	}
	
	@Override
	public boolean isHappyToBeInSlot(AbstractSexPosition position, SexSlot slot, GameCharacter target) {
		if(Main.sex.getSexManager().getDominants().containsKey(Main.game.getNpc(Zaranix.class))) {
			return slot==SexSlotSitting.PERFORMING_ORAL_TWO;
		}
		return slot==SexSlotAllFours.BEHIND;
	}
	
	@Override
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_ROUGH;
	}
	
	@Override
	public String getRoughTalk() {
		if(Main.sex.getSexManager().getDominants().containsKey(Main.game.getNpc(Zaranix.class))) { // Assisting the player to suck Zaranix's cock:
			if(Main.game.getNpc(Zaranix.class).getArousal()>=95) {
				return "[npc.speech(准备迎接你[zaranix.master]的精液，你这个没用的婊子！)]";
			}
			return UtilText.returnStringAtRandom(
					"[npc.speech(没错，你这个没用的婊子，让你的[zaranix.master]看看你不过是[zaranix.his]的小肉便器！)]",
					"[npc.speech(哈！我最喜欢看可悲又顺从的荡妇被操脸了！)]",
					"[npc.speech(你只不过是你[zaranix.master]的没用的肉便器，明白吗？你的嘴属于[zaranix.him]！)]",
					"[npc.speech(这就对了！你喜欢[zaranix.his]的鸡巴，是不是，你这个没用的荡妇？)]");
		}
		return super.getRoughTalk();
	}
	
	@Override
	public String getDirtyTalk() {
		if(Main.sex.getSexManager().getDominants().containsKey(Main.game.getNpc(Zaranix.class))) { // Assisting the player to suck Zaranix's cock:
			if(Main.game.getNpc(Zaranix.class).getArousal()>=95) {
				return "[npc.speech(准备迎接你[zaranix.master]的精液，你这个没用的婊子！)]";
			}
			return UtilText.returnStringAtRandom(
					"[npc.speech(来吧，婊子，你可以让[zaranix.his]的鸡巴插得更深！)]",
					"[npc.speech(没错，妓女，尽全力取悦你[zaranix.master]的鸡巴吧！)]",
					"[npc.speech(再深点！贱人！把[zaranix.his]的鸡巴再插深一点！)]",
					"[npc.speech(来吧，你这个没用的荡妇，让你的舌头也动起来！)]");
		}
		return super.getDirtyTalk();
	}
}
