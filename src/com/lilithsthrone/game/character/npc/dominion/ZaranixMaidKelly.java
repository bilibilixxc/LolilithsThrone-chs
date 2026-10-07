package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
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
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
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
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5.5
 * @author Innoxia
 */
public class ZaranixMaidKelly extends NPC {

	public ZaranixMaidKelly() {
		this(false);
	}
	
	public ZaranixMaidKelly(boolean isImported) {
		super(isImported, new NameTriplet("凯丽"), "拉谢尔马尔图",
				"凯丽是扎拉尼克斯的双胞胎魅魔女仆之一，负责打扫一楼的卫生。",
				26, Month.SEPTEMBER, 20,
				10,
				null, null, null,
				new CharacterInventory(false, 10), WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_MAID, true);

		this.setPlayerKnowsName(true);
		
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceAbsolute(18);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setDescription("凯丽是扎拉尼克斯的双胞胎魅魔女仆之一，负责打扫一楼的卫生。");
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
	}
	
	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceAbsolute(18);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setDescription("One of Zaranix's succubi maid twins, Kelly is assigned by her master to keep the first floor clean.");
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 5),
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
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_MASOCHIST);
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
		this.setAgeAppearanceAbsolute(18);
		this.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER, false);
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.NONE);
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.CURLED);

		// Core:
		this.setHeight(180);
		this.setFemininity(85);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_PURPLE));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_IVORY), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_WHITE), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.HIME_CUT);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PURPLE));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PURPLE));
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
		this.setBreastSize(CupSize.F.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
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
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
		this.setPenisSize(17);
		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
		this.setPenisCumStorage(100);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
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

		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_cleaning_feather_duster"));
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_dress", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_headpiece", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_stockings", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_sleeves", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_heels", PresetColour.CLOTHING_BLACK, false), true, this);

	}
	
	@Override
	public String getName(boolean applyNameAlteringEffects) {
		if(!playerKnowsName) {
			return "扎拉尼克斯的女仆";
			
		} else {
			return "凯丽";
		}
	}
	
	@Override
	public void hourlyUpdate(int hour) {
		if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
			this.moveToAdjacentMatchingCellType(true, PlaceType.ZARANIX_FF_CORRIDOR, PlaceType.ZARANIX_FF_STAIRS, PlaceType.ZARANIX_FF_MAID);
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#E48AFF";
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
		return true;
	}

	@Override
	public Set<Relationship> getRelationshipsTo(GameCharacter character, Relationship... excludedRelationships) {
		if(character instanceof ZaranixMaidKatherine) {
			Set<Relationship> result = new LinkedHashSet<>();
			result.add(Relationship.SiblingTwin);
			return result;
		}
		return super.getRelationshipsTo(character, excludedRelationships);
	}


	// Combat:
	
	@Override
	public String getMainAttackDescription(int armRow, GameCharacter target, boolean isHit, boolean critical) {
		return "<p>"
				+ UtilText.parse(target,
						UtilText.returnStringAtRandom(
						"凯丽发出一声急切的呻吟，朝[npc.Name]挥舞着她的小羽毛掸，羽毛拂过[npc.her]的脸！",
						"凯丽轻声呻吟，用羽毛掸轻轻地挠着[npc.namePos]的躯体！",
						"凯丽轻声叫嚷，用羽毛掸拂过[npc.namePos]的躯体！"))
			+ "</p>";
	}

	@Override
	public String getSpellDescription() {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"凯丽将羽毛掸在胸前一横，叫喊着施起咒来！",
						"凯丽一边施咒一边将她的羽毛掸子抛向空中！") 
			+ "</p>";
	}

	@Override
	public String getSeductionDescription(GameCharacter target) {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"凯丽把女仆裙的下摆拉起来一截，而后呻吟起来，[kelly.speech(如果你能击败我，羞辱也好、强奸也罢，我就听你任意摆布！)]",
						"凯丽发出淫荡的呻吟，把手探到裙子下面，呻吟道，[kelly.speech(~呃啊！~ 你真厉害！我已经湿透了！)]",
						"凯丽咬着嘴唇，抬眼对上你的眼睛。你听到淫荡的呻吟回荡在你的脑海里，[kelly.speech(求你毫不留情地蹂躏我！求求你！)]",
						"凯丽发出难以置信的浪叫声，双手靠近上身，握住她的[kelly.cupSize]杯乳房，[kelly.speech(~嗯姆！~ 如果我输了，你就可以无所顾忌地操我哦！)]",
						"凯丽做出一副蹒跚后退的样子，然后饥渴地将双手放在两腿之间， [kelly.speech(~啊~！我都可以想象得到，如果我在这里摔倒了，那你会对我做多少涩情的事情！)]",
						"凯丽喘着气，[npc.breasts+]随着呼吸上下起伏，[kelly.speech(哦，不！我已经感到累了！如果我输了，你，你绝对会占我便宜的！)]") 
			+ "</p>";
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AFTER_COMBAT_VICTORY) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKellySubdued, true);
				}
			};
		} else {
			return new Response("", "", AFTER_COMBAT_DEFEAT);
		}
	}
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("胜利", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "凯利摇摇晃晃地向后退去，她紧紧抓住身边柜子的边缘处，然后摊倒在墙上。"
						+ "她尽管没能保卫主人的家，但似乎并不失落。她抬头看向你，口中呻吟着，"
						+ "[kelly.speech(~啊哦！~ 哦不！我完全没防备呢！~唔姆！~ 现在没什么能阻止你控制我啦！)]"
					+ "</p>"
					+ "<p>"
						+ "她瘫软着倚靠在墙边，嘴里不断地发出充满欲望的呻吟和喘息——很明显，她想和你做爱。"
						+ "甚至无法多忍耐几秒钟等到你的回复，她的手指就滑到了衣服下面，开始无耻地在你面前手淫起来。"
					+ "</p>"
					+ "<p>"
						+ "凯丽满腔欲火，不会构成太大的威胁，所以你可以遂了她的愿——和她做爱，"
							+ "也可以干脆无视她，继续做自己的事。"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续探索扎拉尼克斯家。", PlaceType.ZARANIX_FF_MAID.getDialogue(false));
				
			} if(index==2) {
				return new ResponseSex("使用凯丽", "和诱人女仆做点有意思的事。",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
						null,
						null), AFTER_SEX_VICTORY, "<p>"
							+ "看起来在这里其他女仆不会打扰你，所以你决定趁这个机会和凯丽找点乐子。"
							+ "走向她瘫倒的地方，你伸手捉住她的手臂，把她的手从私处上拉开。"
							+ "饥渴的女仆双手被缚，她抬头与你对视，眼中充满了渴望，欲望的火焰在其中熊熊燃烧。"
						+ "</p>"
						+ "<p>"
							+ "她把自己从墙边推离，双臂环绕在你的背上，迫切地把她[kelly.lips+]贴在你的嘴上。"
							+ "你也做出了回应，舌头在彼此的嘴里纠缠了片刻后，你们抽回舌头，淫笑起来……"
						+ "</p>");
				
			} else if(index==3) {
				return new ResponseSex("顺从",
						"你无法让自己成为支配方，但你<i>确实</i>想和凯丽做爱。或许你可以顺从，她会愿意操你吗？",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null), AFTER_SEX_VICTORY, "<p>"
							+ "你不愿意当支配方的角色，但又很想和这个淫荡的魅魔做爱，你走到凯丽靠墙坐下的地方，小声说道，"
							+ "[pc.speech(凯丽……呃唔……如果你想要，你要不要用用我？我是说，我——)]"
						+ "</p>"
						+ "<p>"
							+ "尽管你是她主人屋子里的一个陌生人，但凯丽还是抬起头看着你，眼神中充满了强烈而炽热的欲望。"
							+ "凯丽打断了你的话，她将手从裙下抽出，抱住你的后脑，将你拉入一个漫长的吻。"
						+ "</p>"
						+ "<p>"
							+ "你做出了回应，但凯丽只亲吻了片刻，就退出去呻吟道，"
							+ "[kelly.speech(哦，好棒！操……我太他妈想要了！我<i>想要</i>你！)]"
						+ "</p>");
				
			} else if (index == 4) {
				return new Response("转化",
						"让凯丽使用[kelly.her]的恶魔能力来转化自己……",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getNpc(ZaranixMaidKelly.class));
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
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(ZaranixMaidKelly.class)) >= Main.game.getNpc(ZaranixMaidKelly.class).getOrgasmsBeforeSatisfied()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "凯丽满足地叹息着，向后靠在墙上，"
							+ "[kelly.speech(~哈啊！~ 好舒服……)]"
						+ "</p>"
						+ "<p>"
							+ "尽管你刚刚让她高潮了一次，她把手滑到两腿之间，口中不断逸出渴求的淫荡呻吟，开始指交自己。"
							+ "她的动作暴露了她仍被你的灵气唤起的力量所压倒。她留在这里不会造成威胁。你可以继续探索扎拉尼克斯的家。"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "凯丽急切地呻吟着，向后靠在墙上，"
							+ "[kelly.speech(~哈啊！~ 我还想要！)]"
						+ "</p>"
						+ "<p>"
							+ "她立刻把手滑到两腿之间，口中不断逸出渴求的淫荡呻吟，开始指交自己。"
							+ "她的动作暴露了她仍被你的灵气唤起的力量所压倒。她留在这里不会造成威胁。你可以继续探索扎拉尼克斯的家。"
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
				return new Response("继续", "继续探索扎拉尼克斯家。", PlaceType.ZARANIX_FF_MAID.getDialogue(false));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("被击败", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "凯丽的力量太大了，你完全无法反抗，只能发出一声泄气的哀嚎，你摇摇晃晃地穿过走廊，靠在墙上。"
						+ "你抬起头，看到象牙般白皙肌肤的女仆撅着嘴向你走来，"
						+ "[kelly.speech(哦吼吼……我还想你能更能打一点呢……根本没尽兴呢~！)]"
					+ "</p>"
					+ "<p>"
						+ "她移动到你身边，你无力反抗，只能任由她坏笑着环抱住你并把你摁向[kelly.breasts+]。"
						+ "[kelly.speech(看起来我得换个方式找点乐子……)]"
					+ "</p>"
					+ "<p>"
						+ "她突然把她[kelly.lips+]贴近你的嘴唇，与你热吻。她的恶魔蛇尾巴摩擦着你的腹股沟……"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("被使用", "凯丽使用你。",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						AFTER_SEX_DEFEAT,
						"");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("被使用", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "你完全脱力，垮在地上喘着粗气。"
						+ "高高在上的凯丽用一种居高临下的语气对你说，"
						+ "[kelly.speech(哦吼吼！对你来说是不是太刺激啦？既然这样，你是时候该离开了！)]"
					+ "</p>"
					+ "<p>"
						+ "你太累了，无法抗拒，任凭凯丽拖着你沿着走廊走到入口。"
						+ "她打开门，叹了口气，毫不客气地把你推到街上，"
						+ "[kelly.speech(我猜你选错了闯空门的对象，对吧？)]"
					+ "</p>"
					+ "<p>"
						+ "说完，她砰地关上了门，你自己站起来，继续你的路……"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被扔出去", "凯丽把你扔到了街上。", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
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

}