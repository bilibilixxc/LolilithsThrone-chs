package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.LolificationProject.Lexi.Lexicon;
import com.lilithsthrone.LolificationProject.ModCommon.MiscStuff;
import com.lilithsthrone.LolificationProject.ModCommon.TranslationUtils;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.attributes.AlcoholLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAnusType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFootType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractMouthType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractNippleType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTesticleType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTongueType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.AnusType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyPartType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.MouthType;
import com.lilithsthrone.game.character.body.types.NippleType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.types.TesticleType;
import com.lilithsthrone.game.character.body.types.TongueType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodyShape;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.OccupationTag;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.pregnancy.FertilisationType;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractRacialBody;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Nocturnality;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.DamageVariance;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.AbstractDialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.AbstractSetBonus;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.outfit.AbstractOutfit;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.settings.ForcedFetishTendency;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotManager;
import com.lilithsthrone.game.sex.sexActions.baseActions.ToyVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.threading.DocBuilders;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.BaseColour;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.time.DayPeriod;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;
import org.openjdk.nashorn.api.scripting.NashornScriptEngine;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @since 0.1.0
 * @version 0.4.10.4
 * @author Innoxia, Pimvgd, AlacoGit, Tad Unlikely, CognitiveMist, tarbh-uisge
 */
public class UtilText {

	private static String modifiedSentence;
	public static StringBuilder nodeContentSB = new StringBuilder(4096);
	private static StringBuilder descriptionSB = new StringBuilder();
	private static List<ParserTag> parserTags;
	private static List<String> parserVariableCalls = new ArrayList<>();
	
	private static AbstractClothingType clothingTypeForParsing;
	private static Body body;
	private static AbstractRace race;
	private static CharacterInventory inventory;

//	private static List<GameCharacter> specialNPCList = new ArrayList<>();
	public static boolean parseCapitalise;
	private static boolean parseAddPronoun;

	private static NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
	public static ScriptEngine engine;
	
	private static List<String> specialParsingStrings = new ArrayList<>();
	private static List<GameCharacter> parsingCharactersForSpeech = new ArrayList<>();
	
	private static Map<String, String> americanEnglishConversions = Util.newHashMapOfValues(
			// -our to -or:
			new Value<>("armour", "armor"),
			new Value<>("armoury", "armory"),
			new Value<>("behaviour", "behavior"),
			new Value<>("candour", "candor"),
			new Value<>("clamour", "clamor"),
			new Value<>("colour", "color"),
			new Value<>("demeanour", "demeanor"),
			new Value<>("endeavour", "endeavor"),
			new Value<>("favourite", "favorite"),
			new Value<>("flavour", "flavor"),
			new Value<>("glamour", "glamor"),
			new Value<>("harbour", "harbor"),
			new Value<>("honour", "honor"),
			new Value<>("humour", "humor"),
			new Value<>("labour", "labor"),
			new Value<>("neighbour", "neighbor"),
			new Value<>("odour", "odor"),
			new Value<>("parlour", "parlor"),
			new Value<>("rancour", "rancor"),
			new Value<>("rigour", "rigor"),
			new Value<>("rumour", "rumor"),
			new Value<>("saviour", "savior"),
			new Value<>("savour", "savor"),
			new Value<>("savoury", "savory"),
			new Value<>("splendour", "splendor"),
			new Value<>("valour", "valor"),
			new Value<>("vapour", "vapor"),
			new Value<>("vigour", "vigor"),
			
			// -re to -er:
			new Value<>("amphitheatre", "amphitheater"),
			new Value<>("calibre", "caliber"),
			new Value<>("centimetre", "centimeter"),
			new Value<>("centre", "center"),
			new Value<>("fibre", "fiber"),
			new Value<>("kilometre", "kilometer"),
			new Value<>("litre", "liter"),
			new Value<>("louvre", "louver"),
			new Value<>("lustre", "luster"),
			new Value<>("manoeuvre", "maneuver"),
			new Value<>("meagre", "meager"),
			new Value<>("metre", "meter"),
			new Value<>("millimetre", "millimeter"),
			new Value<>("sabre", "saber"),
			new Value<>("sceptre", "scepter"),
			new Value<>("sombre", "somber"),
			new Value<>("spectre", "specter"),
			new Value<>("theatre", "theater"),

			// -ogue to -og:
			new Value<>("analogue", "analog"),
			new Value<>("dialogue", "dialog"),
			new Value<>("catalogue", "catalog"),
//			new Value<>("epilogue", "epilog"),
//			new Value<>("monologue", "monolog"),
//			new Value<>("prologue", "prolog"),
//			new Value<>("travelogue", "travelog"),
			
			// -l endings are not doubled:
			new Value<>("cancelled", "canceled"),
			new Value<>("counsellor", "counselor"),
			new Value<>("equalled", "equaled"),
			new Value<>("fuelling", "fueling"),
			new Value<>("fuelled", "fueled"),
			new Value<>("grovelling", "groveling"),
			new Value<>("jeweller", "jeweler"),
			new Value<>("jewellery", "jewelery"),
			new Value<>("levelled", "leveled"),
			new Value<>("libelled", "libeled"),
			new Value<>("marvellous", "marvelous"),
			new Value<>("modelling", "modeling"),
			new Value<>("panelled", "paneled"),
			new Value<>("quarrelling", "quarreling"),
			new Value<>("revelled", "reveled"),
			new Value<>("woollen", "woolen"),

			// some -l words are doubled:
			new Value<>("appal(^l)", "appall$1"),
			new Value<>("distil(^l)", "distill$1"),
			new Value<>("enrol(^l)", "enroll$1"),
			new Value<>("enthral(^l)", "enthrall$1"),
			new Value<>("fulfil(^l)", "fulfill$1"),
			new Value<>("instil(^l)", "instill$1"),
			new Value<>("skilful(^l)", "skillful$1"),
			new Value<>("wilful(^l)", "willful$1"),

			// -ae and -oe words change to -e:
			new Value<>("diarrhoea", "diarrhea"),
			new Value<>("oestrogen", "estrogen"),
			new Value<>("foetus", "fetus"),
			//new Value<>("manoeuvre", "maneuver"),
			new Value<>("mementoes", "mementos"),
			new Value<>("anaemia", "anemia"),
			new Value<>("caesarean", "cesarean"),
			new Value<>("gynaecology", "gynecology"),
			new Value<>("haemorrhage", "hemorrhage"),
			new Value<>("leukaemia", "leukemia"),
			new Value<>("palaeontology", "paleontology"),
			new Value<>("paediatric", "pediatric"),

			// -ise words change to -ize:
			new Value<>("apologise", "apologize"),
			new Value<>("appetiser", "appetizer"),
			new Value<>("authorise", "authorize"),
			new Value<>("capitalise", "capitalize"),
			new Value<>("characterise", "characterize"),
			new Value<>("civilise", "civilize"),
			new Value<>("colonise", "colonize"),
			new Value<>("criticise", "criticize"),
			new Value<>("dramatise", "dramatize"),
			new Value<>("emphasise", "emphasize"),
			new Value<>("equalise", "equalize"),
			new Value<>("mobilise", "mobilize"),
			new Value<>("naturalise", "naturalize"),
			new Value<>("organise", "organize"),
			new Value<>("popularise", "popularize"),
			new Value<>("realise", "realize"),
			new Value<>("recognise", "recognize"),
			new Value<>("satirise", "satirize"),
			new Value<>("standardise", "standardize"),
			new Value<>("symbolise", "symbolize"),
			new Value<>("vaporise", "vaporize"),
			new Value<>("analyse", "analyze"),
			new Value<>("paralyse", "paralyze"),

			// -ce words change to -se:
			new Value<>("defence", "defense"),
			new Value<>("defencive", "defensive"),
			new Value<>("offence", "offense"),
			new Value<>("offencive", "offensive"),
			new Value<>("pretence", "pretense"),
			new Value<>("licence", "license"),
			new Value<>("practise", "practice"),

			// other:
			new Value<>("draught", "daft"),
			new Value<>("plough", "plow"),
			new Value<>("tyre", "tire"),
			new Value<>("mould", "mold"),
			new Value<>("moult", "molt"),
			new Value<>("smoulder", "smolder"),
			new Value<>("programme", "program"),
			new Value<>("cheque", "check"),
			new Value<>("chequer", "checker"),
			new Value<>("acknowledgement", "acknowledgment"),
			new Value<>("ageing", "aging"),
			new Value<>("judgement", "judgment"),
			new Value<>("aluminium", "aluminum"),
			new Value<>("axe", "ax"),
			new Value<>("cosy", "cozy"),
			new Value<>("kerb", "curb"),
			new Value<>("furore", "furor"),
			new Value<>("grey", "gray"),
			new Value<>("carat", "karat"),
			new Value<>("liquorice", "licorice"),
			new Value<>("moustache", "mustache"),
			new Value<>("nought", "naught"),
			new Value<>("pyjamas", "pajamas"),
			new Value<>("sceptic", "skeptic"),
			new Value<>("phial", "vial"),
			new Value<>("whisky", "whiskey"),
			new Value<>("queue", "line")
			);
	
	/**
	 * Converts the input into a format suitable for html display. i.e. converts things like '<' to "&lt;".
	 */
	public static String parseForHTMLDisplay(String input) {
		StringBuilder builder = new StringBuilder();
		
		for (char c : input.toCharArray()) {
			switch (c) {
				// I'm not sure why this was being changed to a non-breaking space... It was interfering with clothing name equality and such, so I removed it in v0.3.5.1
//				case ' ':
//					builder.append("&nbsp;");
//					break;
				case '<':
					builder.append("&lt;");
					break;
				case '>':
					builder.append("&gt;");
					break;
				case '&':
					builder.append("&amp;");
					break;
				case '"':
					builder.append("&quot;");
					break;
				case '\'':
					builder.append("&#39;");
					break;
				default:
					builder.append(c);
					break;
			}
		}
		
		return builder.toString();
	}
	
	public static String parsePlayerThought(String text) {
		return parseThought(text, Main.game.getPlayer());
	}

	public static String parsePlayerSpeech(String text) {
		return parseSpeech(text, Main.game.getPlayer());
	}

	private static String getGlowStyle(Colour colour) {
		return colour==null?"":"text-shadow: 0px 0px 4px "+colour.getShadesRgbaFormat(0.75f)[1]+";";
	}
	
	private static Boolean isPlayer(String target, GameCharacter character) {
		if (character == null) return false;
		return target.startsWith("npc") && character.isPlayer();
	}

	public static String parseSpeech(String text, GameCharacter target, boolean includePersonalityEffects, boolean includeExtraEffects) {
		return parseSpeech(text, target, includePersonalityEffects, includeExtraEffects, true);
	}
	public static String parseSpeech(String text, GameCharacter target, boolean includePersonalityEffects, boolean includeExtraEffects, boolean canBeMuted) {
		modifiedSentence = text.trim();
		
		String[] splitOnConditional = modifiedSentence.split("#THEN");
		
		modifiedSentence = UtilText.parse(parsingCharactersForSpeech, splitOnConditional[splitOnConditional.length-1]);
		
		if(target.isMute() && canBeMuted) {
			modifiedSentence = Util.replaceWithMute(modifiedSentence, Main.game.isInSex() && Main.sex.getAllParticipants().contains(target));
			
		} else if(includeExtraEffects
				&& !parserTags.contains(ParserTag.SEX_ALLOW_MUFFLED_SPEECH)
				&& Main.game.isInSex()
				&& Main.sex.getAllParticipants().contains(target)
				&& target.isSpeechMuffled()) {
			if(Main.sex.isOngoingActionsBlockingSpeech(target)) {
				modifiedSentence = Util.replaceWithMuffle(modifiedSentence, 5);
			}
			
		} else {
			if(includePersonalityEffects) {
				if(target.hasFetish(Fetish.FETISH_BIMBO)) {
					if(target.isFeminine()) {
						modifiedSentence = Util.addBimbo(modifiedSentence, 10);
					} else {
						modifiedSentence = Util.addBro(modifiedSentence, 6);
					}
				}
				
				if(target.hasPersonalityTrait(PersonalityTrait.SLOVENLY)) {
					modifiedSentence = Util.applySlovenlySpeech(modifiedSentence);
				}
			}
			
			if(includeExtraEffects) {
				if(target.getAlcoholLevel().getSlurredSpeechFrequency()>0) {
					modifiedSentence = Util.addDrunkSlur(modifiedSentence, target.getAlcoholLevel().getSlurredSpeechFrequency());
				}
				
				// Apply speech effects:
				if(target.isSpeechMuffled()) {
					modifiedSentence = Util.addMuffle(modifiedSentence, 8);
					
				} else if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(target)) {
					if(Main.sex.isCharacterEngagedInOngoingAction(target)) {
						modifiedSentence = Util.addSexSounds(modifiedSentence, 6, Main.sex.getSexPace(target)==SexPace.SUB_RESISTING);
					}
					
				}
			}

			if(includePersonalityEffects) {
				if((Main.game.isLipLispEnabled() && target.getLipSize().isImpedesSpeech())
						|| target.hasPersonalityTrait(PersonalityTrait.LISP)) {
					modifiedSentence = Util.applyLisp(modifiedSentence);
				}
	
				if(target.hasPersonalityTrait(PersonalityTrait.STUTTER)) {
					modifiedSentence = Util.addStutter(modifiedSentence, 4);
				}
			}
			
			if(splitOnConditional.length>1) {
				modifiedSentence = splitOnConditional[0]+"#THEN"+modifiedSentence;
			}
		}
		
		Colour glow = target.getSpeechGlowColour();
		String classAddition = "";
		if (target.getSpeechColour() != null) {
			if(target.isDoll()) {
				classAddition = " doll";
			}
			return "<span class='speech"+classAddition+"' style='color:" + target.getSpeechColour() + ";"+getGlowStyle(glow)+"'>"
						+ modifiedSentence
					+ "</span>";

		} else {
			if(target.isDoll()) {
				classAddition = " doll";
			}
			if (Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE || Femininity.valueOf(target.getFemininityValue()) == Femininity.MASCULINE_STRONG) {
				return "<span class='speech"+classAddition+"' style='color:" + PresetColour.MASCULINE_NPC.toWebHexString() + ";"+getGlowStyle(glow)+"'>" + modifiedSentence + "</span>";
			} else if (Femininity.valueOf(target.getFemininityValue()) == Femininity.ANDROGYNOUS) {
				return "<span class='speech"+classAddition+"' style='color:" + PresetColour.ANDROGYNOUS_NPC.toWebHexString() + ";"+getGlowStyle(glow)+"'>" + modifiedSentence + "</span>";
			} else {
				return "<span class='speech"+classAddition+"' style='color:" + PresetColour.FEMININE_NPC.toWebHexString() + ";"+getGlowStyle(glow)+"'>" + modifiedSentence + "</span>";
			}
		}
	
	}
	
	public static String parseSpeech(String text, GameCharacter target) {
		return parseSpeech(text, target, true, true);
	}
	
	public static String parseSpeechNoEffects(String text, GameCharacter target) {
		return parseSpeech(text, target, false, false);
	}
	
	public static String parseSpeechNoExtraEffects(String text, GameCharacter target) {
		return parseSpeech(text, target, true, false);
	}
	
	public static String parseThought(String text, GameCharacter target) {
		return "<i>"+parseSpeech(text, target, true, false, false).replaceAll("class='speech'", "class='thoughts'")+"</i>";
	}

	public static String parseNPCThought(String text, Femininity femininity) {
		return "<i>"+parseNPCSpeech(text, femininity, false, false).replaceAll("class='speech'", "class='thoughts'")+"</i>";
	}
	
	public static String parseNPCSpeech(String text, Femininity femininity) {
		return parseNPCSpeech(text, femininity, false, false);
	}

	public static String parseNPCSpeechDoll(String text, Femininity femininity) {
		return parseNPCSpeech(text, femininity, false, false, true);
	}
	
	public static String parseNPCSpeech(String text, Femininity femininity, boolean bimbo, boolean stutter) {
		return parseNPCSpeech(text, femininity, bimbo, stutter, false);
	}
	
	public static String parseNPCSpeech(String text, Femininity femininity, boolean bimbo, boolean stutter, boolean doll) {
		modifiedSentence = text;
		if (bimbo) {
			modifiedSentence = Util.addBimbo(modifiedSentence, 10);
		}
		if (stutter) {
			modifiedSentence = Util.addStutter(modifiedSentence, 4);
		}
		return "<span class='speech"+(doll?" doll":"")+"' style='color:" + femininity.getSpeechColour().toWebHexString() + ";'>" + modifiedSentence + "</span>";
	}
	
	public static String getDisabledResponse(String label) {
		return "<span class='option-disabled'>"+label+"</span>";
	}
	
	// "Temporary" methods until I refine the way DialogueNodes work:
	public static String getRequirementsDescription(AbstractPerk perkRequired) {
		return ("你需要天赋“<b style='color:"+perkRequired.getPerkCategory().getColour().toWebHexString()+";'>"+perkRequired.getName(Main.game.getPlayer())+"</b>”。");
	}
	
	public static String getRequirementsDescription(AbstractPerk perkRequired, Gender... gendersRequired) {
		descriptionSB.setLength(0);
		
		descriptionSB.append("你需要天赋“<b style='color:"+perkRequired.getPerkCategory().getColour().toWebHexString()+";'>"+perkRequired.getName(Main.game.getPlayer())+"</b>”");
		
		if(gendersRequired.length==0) {
			descriptionSB.append("。");
		} else {
			descriptionSB.append("，且需要成为");
			
			for(int i=0 ;i<gendersRequired.length; i++) {
				if(i!=0) {
					if(i==gendersRequired.length-1)
						descriptionSB.append("，或");
					else
						descriptionSB.append("，");
				}
				descriptionSB.append("<b style='color:"+gendersRequired[i].getColour().toWebHexString()+";'>"+gendersRequired[i].getName()+"</b>");
			}
			
			descriptionSB.append("。");
		}
		
		return descriptionSB.toString();
	}
	
	public static String getRequirementsDescription(CorruptionLevel corruptionNeeded, AbstractPerk... perkRequired) {
		descriptionSB.setLength(0);
		
		descriptionSB.append("你需要堕落等级：<b style='color:"+corruptionNeeded.getColour().toWebHexString()+";'>"+corruptionNeeded.getName()+"</b>");
		
		if(perkRequired.length==0) {
			descriptionSB.append("。");
		} else {
			descriptionSB.append("，或者天赋"+(perkRequired.length>1?"":""));
			
			for(int i=0 ;i<perkRequired.length; i++) {
				if(i!=0) {
					if(i==perkRequired.length-1)
						descriptionSB.append("，或");
					else
						descriptionSB.append("，");
				}
				descriptionSB.append("<b style='color:"+perkRequired[i].getPerkCategory().getColour().toWebHexString()+";'>"+perkRequired[i].getName(Main.game.getPlayer())+"</b>");
			}
			
			descriptionSB.append("。");
		}
		
		return descriptionSB.toString();
	}

	public static String getCurrencySymbol() {
//		return "&#9679;"; // Circle
		return "&#164;"; // 'Generic' currency symbol
	}
	
	public static String getPentagramSymbol() {
		return "&#9956;";//"&#9737;"; // Java doesn't support unicode 6 ;_;   No pentagram for me... ;_;  "&#9956";
	}

	public static String getEssenceSymbol(Colour colour) {
		return "<b style='color:"+colour.toWebHexString()+"; -webkit-text-stroke: 1px "+colour.toWebHexString()+";'>"+getPentagramSymbol()+"</b>";
	}
	
	public static String getShieldSymbol() {
		return "&#9930;";
	}

	public static String getBasicInfinitySymbol() {
		return "<span style='font-weight:normal; font-family:sans-serif; font-size:1.5em;'>&#8734;</span>";
	}

	/**
	 * The infinity symbol really, really sucks in the default LT font.
	 */
	public static String getInfinitySymbol(boolean largerFont) {
		//"&#9854;";
//		return "<span style='font-family:serif; font-weight:normal; font-size:1.25em;'>&#8734;</span>";
		return "<span style='font-weight:normal; font-family:sans-serif; color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+"; "+(largerFont?"font-size:22px;":"")+"'>&#8734;</span>";
	}

	public static String applyGlow(String input) {
		return "<span style='text-shadow: 0px 0px 4px;'>"+input+"</span>";
	}
	
	public static String applyGlow(String input, Colour colour) {
		return "<span style='color:"+colour.toWebHexString()+"; text-shadow: 0px 0px 4px "+colour.getShades()[4]+";'>"+input+"</span>";
	}
	
	public static String applyVibration(String input, Colour colour) {
		return "<span style='text-shadow: 2px 2px "+colour.getShades()[0]+";'>"+input+"</span>";
	}
	
	public static String formatAsEssencesUncoloured(int amount, String tag, boolean withOverlay) {
		String disabledColour = BaseColour.GREY.toWebHexString();
		return
//				"<div class='item-inline'>"
//					+ SVGImages.SVG_IMAGE_PROVIDER.getEssenceUncoloured() + (withOverlay?"<div class='overlay no-pointer' id='ESSENCE_ICON'></div>":"")
//				+"</div>"
				getEssenceSymbol(PresetColour.TEXT_GREY)//+"<b style='color:"+disabledColour+"; -webkit-text-stroke: 1px "+disabledColour+";'>"+getPentagramSymbol()+"</b>"
				+ "<"+tag+" style='color:"+disabledColour+";'>"+Units.number(amount)+"</"+tag+">";
	}
	
	public static String formatAsEssences(String essences, String tag) {
		try {
			int essenceInt = Integer.parseInt(UtilText.parse(essences));
			return formatAsEssences(essenceInt, tag, false);
		} catch(Exception ex) {
		}
		return formatAsMoney(essences, tag, PresetColour.TEXT);
	}
	
	public static String formatAsEssences(int amount, String tag, boolean withOverlay) {
		String arcaneColour = PresetColour.GENERIC_ARCANE.toWebHexString();
		return
//				"<div class='item-inline'>"
//					+ SVGImages.SVG_IMAGE_PROVIDER.getEssence() + (withOverlay?"<div class='overlay no-pointer' id='ESSENCE_ICON'></div>":"")
//				+"</div>"
//				 "<b style='color:"+arcaneColour+"; text-shadow: "+PresetColour.BASE_PINK_LIGHT.toWebHexString()+" 0 0 16px;'>&#9956;</b>"

				getEssenceSymbol(PresetColour.GENERIC_ARCANE)// "<b style='color:"+arcaneColour+"; -webkit-text-stroke: 1px "+arcaneColour+";'>"+getPentagramSymbol()+"</b>"
				+ "<"+tag+" style='color:"+arcaneColour+";'>"+Units.number(amount)+"</"+tag+">";
	}

	public static String getEnchantmentCapacitySymbolUncoloured() {
		return "<b style='-webkit-text-stroke: 1px;'>&#9959;</b>";
	}
	
	public static String getEnchantmentCapacitySymbol() {
		return "<b style='-webkit-text-stroke: 1px; color:#EA5D76;'>&#9959;</b>";// text-shadow: #FF385Dbb 0 2px 5px;
	}
	
	public static String formatAsEnchantmentCapacityUncoloured(int amount, String tag) {
		return getEnchantmentCapacitySymbolUncoloured() + "<"+tag+">"+Units.number(amount)+"</"+tag+">";
	}
	
	public static String formatAsEnchantmentCapacity(int amount, String tag) {
		String colour = PresetColour.GENERIC_BAD.toWebHexString();
		return getEnchantmentCapacitySymbol() + "<"+tag+" style='color:"+colour+";'>"+Units.number(amount)+"</"+tag+">";
	}
	
	// Money formatting:
	
	public static String formatAsItemPrice(int money) {
		String moneyString = Units.number(money);
		
		if(money > 1_000_000) {
			float moneyAbbreviated = money/1_000_000f;
			moneyString = Units.number(moneyAbbreviated, 1, 1)+"M";
			return formatAsMoney(moneyString, "b", PresetColour.CURRENCY_GOLD);
			
		} else if(money > 1_000) {
			float moneyAbbreviated = money/1_000f;
			int precision = money < 10_000?1:0;
			moneyString = Units.number(moneyAbbreviated, precision, precision)+"k";
			return formatAsMoney(moneyString, "b", PresetColour.CURRENCY_SILVER);
		}
		
		return formatAsMoney(moneyString, "b", PresetColour.CURRENCY_COPPER);
	}
	
	public static String formatAsMoney(long money) {
		return formatAsMoney(money, "b");
	}
	
	public static String formatAsMoneyUncoloured(long money, String tag) {
		return formatAsMoney(money, tag, null);
	}

	public static String formatAsMoneyUncoloured(String money, String tag) {
		return formatAsMoney(money, tag, null);
	}
	
	public static String formatAsMoney(long money, String tag) {
		return formatAsMoney(money, tag, PresetColour.TEXT);
	}
	
	public static String formatAsMoney(String money, String tag) {
		if(!money.contains("[npc.")) { // Do not parse it out if this is a generic NPC's money
			try {
				// If 'thisItem' is not null, pass it through into the money parsing, so that [style.moneyFormat([#thisItem.getValue()], span)] will work without throwing an error
				Object item = engine.get("thisItem");
				int moneyInt;
				if(item!=null) {
					moneyInt = Integer.parseInt(UtilText.parse((AbstractCoreItem) item, money));
				} else {
					moneyInt = Integer.parseInt(UtilText.parse(money));
				}
				return formatAsMoney(moneyInt, tag, PresetColour.TEXT);
			} catch(Exception ex) {
			}
		}
		return formatAsMoney(money, tag, PresetColour.TEXT);
	}
	
	public static String formatAsMoney(long money, String tag, Colour amountColour) {
		return formatAsMoney(Units.number(money), tag, amountColour);
	}
	
	public static String formatAsMoney(String money, String tag, Colour amountColour) {
		return "<" + tag + " style='" + (amountColour==null?"":"color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";") + " padding-right:2px;'>" + getCurrencySymbol() + "</" + tag + ">"
				+ "<" + tag + (amountColour==null?"":" style='color:"+amountColour.toWebHexString()+";'") + ">" + money + "</" + tag + ">";
	}
	
	
	public static String formatVirginityLoss(String s) {
		return "<p style='text-align:center; color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'><i>"+s+"</i></p>";
	}
	
	public static String formatTooLoose(String s) {
		return "<p style='text-align:center; color:"+PresetColour.GENERIC_MINOR_BAD.toWebHexString()+";'><i>"+s+"</i></p>";
	}
	
	public static String formatStretching(String s) {
		return "<p style='text-align:center; color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'><i>"+s+"</i></p>";
	}

	public static boolean isVowel(char c) {
		return "AEIOUaeiou".indexOf(c) != -1;
	}

	/**
	 * @return The word, with an appropriate determiner (either 'a' or 'an' ) added in front of it.
	 */
	public static String addDeterminer(String word) {
		return generateSingularDeterminer(word)+word;
	}
	
	/**
	 * @return 'a' or 'an'
	 */
	public static String generateSingularDeterminer(String word) {
		if(word.isEmpty()) {
			return "";
		}
		if ((isVowel(word.charAt(0)) || word.charAt(0)=='x' || word.charAt(0)=='X')
				 && !word.startsWith("Uni") && !word.startsWith("uni")
				 && !word.startsWith("Used") && !word.startsWith("used")) {
			return "";
			
		} else {
			return "";
		}
	}

	/**
	 * @return Returns one of the supplied Strings, randomly chosen by using Random's nextInt() method. <b>Automatically removes empty Strings and null entries.</b>
	 */
	public static String returnStringAtRandom(String... strings) {
		List<String> randomStrings = new ArrayList<>();
		
		for(String s : strings) {
			if(s!=null && !s.isEmpty()) {
				randomStrings.add(s);
			}
		}
		
		if(!randomStrings.isEmpty()) {
			return randomStrings.get(Util.random.nextInt(randomStrings.size()));
		} else {
			return "";
		}
	}

	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(String pathName, String tag) {
		return parseFromXMLFile(new ArrayList<>(), "res/txt/", pathName, tag, new ArrayList<>());
	}

	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(List<ParserTag> parserTags, String pathName, String tag) {
		return parseFromXMLFile(parserTags, "res/txt/", pathName, tag, new ArrayList<>());
	}
	
	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(String pathName, String tag, GameCharacter... specialNPCs) {
		return parseFromXMLFile(new ArrayList<>(), "res/txt/", pathName, tag, Util.newArrayListOfValues(specialNPCs));
	}
	
	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(List<ParserTag> parserTags, String pathName, String tag, GameCharacter... specialNPCs) {
		return parseFromXMLFile(parserTags, "res/txt/", pathName, tag, Util.newArrayListOfValues(specialNPCs));
	}
	
	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(String pathName, String tag, List<GameCharacter> specialNPC) {
		return parseFromXMLFile(new ArrayList<>(), "res/txt/", pathName, tag, specialNPC);
	}
	
	/**
	 * Parses the tagged htmlContent from an xml file. If there is more than one htmlContent entry, it returns a random one.
	 */
	public static String parseFromXMLFile(List<ParserTag> parserTags, String folderPath, String pathName, String tag, List<GameCharacter> specialNPC) {
		File file = new File(folderPath+System.getProperty("file.separator")+pathName+".xml");

		List<String> strings = new ArrayList<>();
		
		if(file.exists()) {
			try {
				Document doc = DocBuilders.parseDoc(file);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
//				String rootElelemnt = doc.getDocumentElement().getTagName();
				
				for(int i=0; i<(doc.getDocumentElement()).getElementsByTagName("htmlContent").getLength(); i++){
					Element e = (Element) (doc.getDocumentElement()).getElementsByTagName("htmlContent").item(i);
					
					if(e.getAttribute("tag").equals(tag)) {
						strings.add(e.getTextContent().replaceFirst("<!\\[CDATA\\[", "").replaceAll("\\]\\]>", ""));
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Error in UtilText.parseFromXMLFile(): File '"+(folderPath+System.getProperty("file.separator")+pathName+".xml")+"' does not exist!");
		}
		
		if(strings.isEmpty()) {
			return "<p><span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>“"+tag+"”的对话未找到！(请保证“res”文件夹与.jar或.exe文件处于同一目录下)</span></p>";

		} else {
			return parse(new ArrayList<>(specialNPC), strings.get(Util.random.nextInt(strings.size())), true, parserTags);
		}
	}
	
	public static String runXmlTest(String pathName) {
		return runXmlTest(pathName, Util.newArrayListOfValues(
				Main.game.getNpc(Lilaya.class),
				Main.game.getNpc(Brax.class),
				Main.game.getNpc(Rose.class),
				Main.game.getNpc(Ralph.class),
				Main.game.getNpc(Nyan.class),
				Main.game.getNpc(Zaranix.class)));
	}
	
	public static String runXmlTest(String pathName, List<GameCharacter> specialNPC) {
		File file = new File(pathName);

		Map<String, List<String>> strings = new HashMap<>();
		
		if (file.exists()) {
			try {
				Document doc = DocBuilders.parseDoc(file);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				for(int i=0; i<((Element) doc.getElementsByTagName("dialogue").item(0)).getElementsByTagName("htmlContent").getLength(); i++){
					Element e = (Element) ((Element) doc.getElementsByTagName("dialogue").item(0)).getElementsByTagName("htmlContent").item(i);
					
					strings.putIfAbsent(e.getAttribute("tag"), new ArrayList<>());
					strings.get(e.getAttribute("tag")).add(e.getTextContent().replaceFirst("<!\\[CDATA\\[", "").replaceAll("\\]\\]>", ""));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(strings.isEmpty()) {
			return "<p><span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>未找到任何对话！(请保证“res”文件夹与.jar或.exe文件处于同一目录下)</span></p>";

		} else {
			StringBuilder sb = new StringBuilder();
			StringBuilder duplicationSB = new StringBuilder();
			for(Entry<String, List<String>> s : strings.entrySet()) {
				if(s.getValue().size()>1) {
					duplicationSB.append("[style.italicsMinorBad(XML测试重复：标签“"+s.getKey()+"”重复"+s.getValue().size()+"次！)]<br/>");
				}
				for(String savedString : s.getValue()) {
					sb.append("<p>"
								+ "<b>对话标签："+s.getKey()+"</b>"
							+ "</p>"
							+ parse(specialNPC, savedString, true)
							+"<br/><br/>");
				}
			}
			return duplicationSB.toString() + sb.toString();
		}
	}

	private static String speechTarget = "";
	private static boolean suppressOutput = false;
	
	public static boolean isInSpeech() {
		return speechTarget!=null && !speechTarget.isEmpty();
	}

	public static String parse(String input, ParserTag... tags) {
		return parse(new ArrayList<>(), input, tags);
	}

	// v0.4.10.10: allowed null values in lists so that parsed content can check for null characters.
	// It shouldn't have affected anything, but if text throughout the game starts throwing parsing errors just revert this to 'Util.newArrayListOfValues'...
	
	public static String parse(GameCharacter specialNPC, String input, ParserTag... tags) {
		return parse(Util.newArrayListOfValuesKeepNulls(specialNPC), input, tags);
	}
	public static String parseNoExceptionHandling(GameCharacter specialNPC, String input, ParserTag... tags) throws Exception {
		return parseNoExceptionHandling(Util.newArrayListOfValuesKeepNulls(specialNPC), null, input, false, Arrays.asList(tags));
	}

	public static String parse(GameCharacter specialNPC, AbstractCoreItem specialItem, String input, ParserTag... tags) {
		return parse(Util.newArrayListOfValuesKeepNulls(specialNPC), specialItem, input, tags);
	}
	
	public static String parse(GameCharacter specialNPC1, GameCharacter specialNPC2, String input, ParserTag... tags) {
		return parse(Util.newArrayListOfValuesKeepNulls(specialNPC1, specialNPC2), input, tags);
	}

	public static String parse(AbstractCoreItem specialItem, String input, ParserTag... tags) {
		return parse(specialItem, input, false, tags);
	}

	public static String parse(List<GameCharacter> specialNPC, String input, ParserTag... tags) {
		return parse(specialNPC, input, false, tags);
	}

	public static String parse(List<GameCharacter> specialNPC, AbstractCoreItem specialItem, String input, ParserTag... tags) {
		return parse(specialNPC, specialItem, input, false, Arrays.asList(tags));
	}
	
	private static String parse(AbstractCoreItem specialItem, String input, boolean xmlParsing, ParserTag... tags) {
		return parse(specialItem, input, xmlParsing, Arrays.asList(tags));
	}
	
	private static String parse(List<GameCharacter> specialNPC, String input, boolean xmlParsing, ParserTag... tags) {
		return parse(specialNPC, input, xmlParsing, Arrays.asList(tags));
	}

	public static String parse(AbstractCoreItem specialItem, String input, boolean xmlParsing, List<ParserTag> tags) {
		return parse(new ArrayList<>(), specialItem, input, xmlParsing, tags);
	}

	public static String parse(List<GameCharacter> specialNPC, String input, boolean xmlParsing, List<ParserTag> tags) {
		return parse(specialNPC, null, input, xmlParsing, tags);
	}
	/**
	 * Parses supplied text.
	 */
	public static String parse(List<GameCharacter> specialNPC, AbstractCoreItem specialItem, String input, boolean xmlParsing, List<ParserTag> tags) {
		if (input.trim().isEmpty()) return input;
		if (!(input.contains("#") || input.contains("(") || input.contains("[") || input.contains("<svg") || input.contains("\\u"))) {
			return input; // Performance'nt
		}

		input = TranslationUtils.prehandleParse(specialNPC, input, xmlParsing, tags);
		List<GameCharacter> parsingCharactersForSpeechSaved;
		parserTags = (tags);
		parsingCharactersForSpeechSaved = parsingCharactersForSpeech;
		parsingCharactersForSpeech = specialNPC;
		
		if(Main.game!=null && Main.game.getCurrentDialogueNode()==DebugDialogue.PARSER) {
			input = input.replaceAll("\u200b", "");
		}
//		input = input.replaceAll("", ""); //???
		for(int i=0; i<specialParsingStrings.size(); i++) {
			input = input.replaceAll("\\[#SPECIAL_PARSE_"+i+"\\]", specialParsingStrings.get(i));
		}
		
		if(xmlParsing) {
			if(input.contains("#VAR")) { // Set variables to be parsed on each conditional:
				speechTarget = "";
				parserVariableCalls = new ArrayList<>();
				Matcher matcherVAR = Pattern.compile("(?s)#VAR(.*?)#ENDVAR").matcher(input);
				while(matcherVAR.find()) {
					String s = matcherVAR.group().replaceAll("#VAR", "").replaceAll("#ENDVAR", "");
					parserVariableCalls.add(s);
				}
				input = input.replaceAll("(?s)#VAR(.*?)#ENDVAR", "");
			} else {
				speechTarget = "";
				parserVariableCalls = new ArrayList<>();
			}
		}
		
		try {
			return applyParsing(specialNPC, specialItem, input, xmlParsing, tags, parsingCharactersForSpeechSaved, true);
		} catch(Exception ex) {
			System.err.println("Failed to parse: "+input);
			ex.printStackTrace();
			parsingCharactersForSpeech = parsingCharactersForSpeechSaved;
			return "";
		}
	}
	/**
	 * Parses supplied text, but does not handle parsing errors and instead throws an Exception if something goes wrong.
	 */
	public static String parseNoExceptionHandling(List<GameCharacter> specialNPC, AbstractCoreItem specialItem, String input, boolean xmlParsing, List<ParserTag> tags) throws Exception {
		List<GameCharacter> parsingCharactersForSpeechSaved;
		parserTags = (tags);
		parsingCharactersForSpeechSaved = parsingCharactersForSpeech;
		parsingCharactersForSpeech = specialNPC;

		if(Main.game!=null && Main.game.getCurrentDialogueNode()==DebugDialogue.PARSER) {
			input = input.replaceAll("\u200b", "");
		}
//		input = input.replaceAll("", ""); //???
		for(int i=0; i<specialParsingStrings.size(); i++) {
			input = input.replaceAll("\\[#SPECIAL_PARSE_"+i+"\\]", specialParsingStrings.get(i));
		}

		if(xmlParsing) {
			if(input.contains("#VAR")) { // Set variables to be parsed on each conditional:
				speechTarget = "";
				parserVariableCalls = new ArrayList<>();
				Matcher matcherVAR = Pattern.compile("(?s)#VAR(.*?)#ENDVAR").matcher(input);
				while(matcherVAR.find()) {
					String s = matcherVAR.group().replaceAll("#VAR", "").replaceAll("#ENDVAR", "");
					parserVariableCalls.add(s);
				}
				input = input.replaceAll("(?s)#VAR(.*?)#ENDVAR", "");
			} else {
				speechTarget = "";
				parserVariableCalls = new ArrayList<>();
			}
		}

		return applyParsing(specialNPC, specialItem, input, xmlParsing, tags, parsingCharactersForSpeechSaved, false);
	}

	private static String applyParsing(List<GameCharacter> specialNPC, AbstractCoreItem specialItem, String input, boolean xmlParsing, List<ParserTag> tags, List<GameCharacter> parsingCharactersForSpeechSaved, boolean handleExceptions) throws Exception {
		StringBuilder resultBuilder = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		int openBrackets = 0;
		int closeBrackets = 0;
		int openArg = 0;
		int closeArg = 0;
		int startIndex = 0;
		int endIndex = 0;

		String target = null;
		String command = null;
		String arguments = null;
		String conditionalStatement = null;
		boolean usingConditionalBrackets = false;
		boolean lastConditionalUsedBrackets = false;
		int conditionalOpenBrackets = 0;
		int conditionalCloseBrackets = 0;

		Map<String, String> conditionals = null;

		boolean conditionalElseFound = false;
		ParseMode currentParseMode = ParseMode.UNKNOWN;

		int startedParsingSegmentAt = 0;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			// Advance the parser index to the final `>` if we encounter an SVG
			if(c == 'g' && substringMatchesInReverseAtIndex(input, "<svg", i)) {
				i = input.indexOf("</svg>", i) + 5; // 5 == "</svg>".length() - 1
				continue;
			}

			if(usingConditionalBrackets) {
				if(input.charAt(i)=='(') {
					conditionalOpenBrackets++;

				} else if(input.charAt(i)==')') {
					conditionalCloseBrackets++;
				}
			}

			if (currentParseMode != ParseMode.REGULAR && currentParseMode != ParseMode.REGULAR_SCRIPT) {
				suppressOutput = false;
				if (c == 'F' && substringMatchesInReverseAtIndex(input, "#IF", i)) {
					if (openBrackets == 0) {
						conditionals = new LinkedHashMap<>();
						currentParseMode = ParseMode.CONDITIONAL;
						startIndex = i-2;

						for(int j=i+1;j<input.length();j++) {
							if(!Character.isWhitespace(input.charAt(j))) {
								usingConditionalBrackets = input.charAt(j)=='(';
								lastConditionalUsedBrackets = usingConditionalBrackets;
								break;
							}
						}
					} else {
						lastConditionalUsedBrackets = false;
					}

					openBrackets++;

				} else if (currentParseMode == ParseMode.CONDITIONAL) {
					if(usingConditionalBrackets) {
						if(conditionalOpenBrackets>0 && conditionalOpenBrackets==conditionalCloseBrackets && openBrackets-1==closeBrackets) {
							conditionalStatement = sb.toString().substring(1, sb.length())+")";
							conditionalStatement = conditionalStatement.replaceAll("\n", "").replaceAll("\t", "");
							conditionalStatement = conditionalStatement.trim();

							usingConditionalBrackets = false;
							conditionalOpenBrackets = 0;
							conditionalCloseBrackets = 0;

							sb.setLength(0);

						} else if(c == 'F' && substringMatchesInReverseAtIndex(input, "#ELSE IF", i) && openBrackets-1==closeBrackets && conditionalStatement!=null) {
							conditionals.putIfAbsent(conditionalStatement, sb.toString().substring(1, sb.length()-7)); // Cut off the '#ELSE IF' at the end of this section.
							for(int j=i+1;j<input.length();j++) {
								if(!Character.isWhitespace(input.charAt(j))) {
									usingConditionalBrackets = input.charAt(j)=='(';
									break;
								}
							}

							sb.setLength(0);

						} else if(c == 'F' && substringMatchesInReverseAtIndex(input, "#ELSEIF", i) && openBrackets-1==closeBrackets && conditionalStatement!=null) {
							conditionals.putIfAbsent(conditionalStatement, sb.toString().substring(1, sb.length()-6)); // Cut off the '#ELSEIF' at the end of this section.

							for(int j=i+1;j<input.length();j++) {
								if(!Character.isWhitespace(input.charAt(j))) {
									usingConditionalBrackets = input.charAt(j)=='(';
									break;
								}
							}

							sb.setLength(0);

						} else if(c == 'E' && substringMatchesInReverseAtIndex(input, "#ELSE", i)
								&& (i+1==input.length()||i+2==input.length()||input.charAt(i+1)!='I'||input.charAt(i+2)!='F')
								&& (i+1==input.length()||i+2==input.length()||i+3==input.length()||input.charAt(i+1)!=' '||input.charAt(i+2)!='I'||input.charAt(i+3)!='F')
								&& openBrackets-1==closeBrackets
								&& conditionalStatement!=null) {
							conditionalElseFound = true;
							conditionals.putIfAbsent(conditionalStatement, sb.toString().substring(1, sb.length()-4)); // Cut off the '#ELSE' at the end of this section.
							sb.setLength(0);

						} else if(c == 'F' && substringMatchesInReverseAtIndex(input, "#ENDIF", i)) {
							closeBrackets++;

							if (openBrackets == closeBrackets) {
								if (conditionalElseFound) {
									conditionals.putIfAbsent("true", sb.toString().substring(1, sb.length()-5)); // Cut off the '#ENDIF' at the end.
								} else {
									conditionals.putIfAbsent(conditionalStatement, sb.toString().substring(1, sb.length()-5)); // Cut off the '#ENDIF' at the end of this section.
								}

								endIndex = i;
							}
						}

					} else {
//						System.out.println("noConditionalBrackets");
						if(c == 'N' && substringMatchesInReverseAtIndex(input, "#THEN", i)) {
							// If last conditional was brackets, remove the THEN
							if(lastConditionalUsedBrackets) {
								sb.replace(sb.length()-4, sb.length(), ""); // Reset StringBuilder to exclude #THEN
								i++;
								c = input.charAt(i);

							} else if (openBrackets-1==closeBrackets) {
								conditionalStatement = sb.toString().substring(1, sb.length()-4); // Cut off the '#THEN' at the end of the conditional statement.
								conditionalStatement = conditionalStatement.replaceAll("\n", "").replaceAll("\t", "");
								conditionalStatement = conditionalStatement.trim();
								sb.setLength(0);
							}

						} else if(c == 'F' && substringMatchesInReverseAtIndex(input, "#ELSE IF", i) && openBrackets-1==closeBrackets) {
							conditionals.putIfAbsent(conditionalStatement, sb.toString().substring(1, sb.length()-7)); // Cut off the '#ELSE IF' at the end of this section.

							for(int j=i+1;j<input.length();j++) {
								if(!Character.isWhitespace(input.charAt(j))) {
									usingConditionalBrackets = input.charAt(j)=='(';
									break;
								}
							}

							sb.setLength(0);

						} else if(c == 'F' && substringMatchesInReverseAtIndex(input, "#ELSEIF", i) && openBrackets-1==closeBrackets) {
							conditionals.putIfAbsent(conditionalStatement, sb.toString().substring(1, sb.length()-6)); // Cut off the '#ELSEIF' at the end of this section.

							for(int j=i+1;j<input.length();j++) {
								if(!Character.isWhitespace(input.charAt(j))) {
									usingConditionalBrackets = input.charAt(j)=='(';
									break;
								}
							}

							sb.setLength(0);

						} else if(c == 'E' && substringMatchesInReverseAtIndex(input, "#ELSE", i)
								&& (i+1==input.length()||i+2==input.length()||input.charAt(i+1)!='I'||input.charAt(i+2)!='F')
								&& (i+1==input.length()||i+2==input.length()||i+3==input.length()||input.charAt(i+1)!=' '||input.charAt(i+2)!='I'||input.charAt(i+3)!='F')
								&& openBrackets-1==closeBrackets) {
							conditionalElseFound = true;
//							conditionalTrue = sb.toString().substring(1, sb.length()-4); // Cut off the '#ELSE' at the end of this section.
							conditionals.putIfAbsent(conditionalStatement, sb.toString().substring(1, sb.length()-4)); // Cut off the '#ELSE' at the end of this section.
							sb.setLength(0);

						} else if(c == 'F' && substringMatchesInReverseAtIndex(input, "#ENDIF", i)) {
							closeBrackets++;

							if (openBrackets == closeBrackets) {

								if (conditionalElseFound) {
									// conditionalTrue has already been set in the #ELSE catch
//									conditionalFalse = sb.toString().substring(1, sb.length()-5); // Cut off the '#ENDIF' at the end.
									conditionals.putIfAbsent("true", sb.toString().substring(1, sb.length()-5)); // Cut off the '#ENDIF' at the end.
								} else {
//									conditionalTrue = sb.toString().substring(1, sb.length()-5); // Cut off the '#ENDIF' at the end.
//									conditionalFalse = "";
									conditionals.putIfAbsent(conditionalStatement, sb.toString().substring(1, sb.length()-5)); // Cut off the '#ENDIF' at the end of this section.
								}

								endIndex = i;
							}
						}
					}
				}
			}

			if (currentParseMode != ParseMode.CONDITIONAL) {
				suppressOutput = false;
				if (c == '[') {
					if(openBrackets==0) {
						if(input.charAt(i+1) == '#') {
							currentParseMode = ParseMode.REGULAR_SCRIPT;
						} else {
							currentParseMode = ParseMode.REGULAR;
						}
						startIndex = i;
					}

					openBrackets++;

				} else if (currentParseMode == ParseMode.REGULAR) {
					if (c =='.' && target == null) {
						target = sb.toString().substring(1); // Cut off the '[' at the start.
						sb.setLength(0);

					} else if (c == '(') {
						if(command == null) {
							command = sb.toString().substring(1); // Cut off the '.' at the start.
							if(command.equals("speech") || command.equals("speechNoEffects") || command.equals("speechNoExtraEffects")) {
								speechTarget = target;
							}
							sb.setLength(0);
						}

						openArg++;

					} else if (c == ')') {
						closeArg++;

						if (openArg == closeArg){
							arguments = sb.toString().substring(1);
						}

					} else if (c == ']') {
						closeBrackets++;

						if (openBrackets == closeBrackets) {
							if (command == null) {
								command = sb.toString().substring(1); // Cut off the '.' at the start.
								sb.setLength(0);
							}

							endIndex = i;
						}
					}

				} else if (currentParseMode == ParseMode.REGULAR_SCRIPT) {
					if (c == ']') {
						closeBrackets++;

						if (openBrackets == closeBrackets) {
							if(command == null) {
								if(sb.charAt(2)=='#') {
									suppressOutput = true;
									command = sb.toString().substring(3); // Cut off the '[##' at the start.
								} else {
									suppressOutput = false;
									command = sb.toString().substring(2); // Cut off the '[#' at the start.
								}
								sb.setLength(0);
							}

							endIndex = i;
						}
					}
				}
			}

			if (openBrackets>0 && ((target!=null && command!=null) || (!Character.isWhitespace(c) || c==' '))) {
				sb.append(c);
			}

			if (endIndex != 0) {
				resultBuilder.append(input.substring(startedParsingSegmentAt, startIndex));
				String subResult;
				if(currentParseMode == ParseMode.CONDITIONAL) {
					subResult = parseConditionalSyntaxNew(specialNPC, specialItem, conditionals, xmlParsing, handleExceptions);
				} else {
					subResult = parseSyntaxNew(specialNPC, specialItem, target, command, arguments, currentParseMode);
				}
				if (openBrackets > 1) {
					subResult = parse(specialNPC, specialItem, subResult, false, tags);
				}
				if(command!=null && (command.equals("speech") || command.equals("speechNoEffects") || command.equals("speechNoExtraEffects"))) {
					speechTarget = "";
				}
				resultBuilder.append(subResult);
				startedParsingSegmentAt = endIndex + 1;
				//This is the lamest version of recursion unrolling there is: just reset all your variables by hand.
				sb = new StringBuilder();

				openBrackets = 0;
				closeBrackets = 0;
				openArg = 0;
				closeArg = 0;
				startIndex = 0;
				endIndex = 0;

				target = null;
				command = null;
				arguments = null;
				conditionalStatement = null;
				conditionals = null;
				conditionalOpenBrackets = 0;
				conditionalCloseBrackets = 0;

				conditionalElseFound = false;
				currentParseMode = ParseMode.UNKNOWN;
			}
		}

		if (startIndex != 0) {
			StringBuilder errMsg = new StringBuilder("Error in parsing: ");
			switch(input.charAt(startIndex)) {
				case '#':
					errMsg.append("Missing #ENDIF for #IF at ");
					break;
				case '[':
					errMsg.append("Missing ] for [ at ");
					break;
				default:
					errMsg.append("Non-fatal error at ");
					break;
			}
			errMsg.append(startIndex);
			if(target != null) {
				errMsg.append(" Target: '" + target + "'");
			}
			if(command != null) {
				errMsg.append(" Command: '" + command + "'");
			}
			{
				int errContext = 30;
				errMsg.append("\nContext:  " + input.substring(Math.max(0, startIndex - errContext), Math.min(input.length(), startIndex + errContext)));
				errMsg.append("\nLocation: ");// + "-".repeat(Math.min(errContext, startIndex)) + "^"); // .repeat was introduced in Java 11 and I use an older version
				for(int i=0;i<Math.min(errContext, startIndex);i++) {
					errMsg.append("-");
				}
				errMsg.append("^");
			}
			System.err.println("UtilText err : " + errMsg);
			parsingCharactersForSpeech = parsingCharactersForSpeechSaved;
			switch(input.charAt(startIndex)) {
				// Replace the problematic character with its html entity, so that the error does
				// not propagate further.
				case '#':
					return input.substring(0, startIndex) + "&#35;" + input.substring(startIndex+1);
				case '[':
					return input.substring(0, startIndex) + "&#91;" + input.substring(startIndex+1);
			}
			return input;
		}
		if (startedParsingSegmentAt < input.length()) {
			resultBuilder.append(input.substring(startedParsingSegmentAt, input.length()));
		}

		String result = resultBuilder.toString();

		//TODO This really should be somewhere else or handled differently...
		result = result.replaceAll("german", "German"); // This is needed as the subspecies 'german-shepherd-morph' needs to use a lowercase 'g' for generic name determiner detection.

		parsingCharactersForSpeech = parsingCharactersForSpeechSaved;
        return TranslationUtils.clean(result, specialNPC);

    }
	
	private static boolean substringMatchesInReverseAtIndex(String input, String stringToMatch, int index) {
		index++;//this fixes my off by one error and I'm too tired to figure out why
		int startingLocation = index - stringToMatch.length();
		if (startingLocation < 0 || index > input.length()) {
			return false;
		}
		return input.substring(startingLocation, index).equals(stringToMatch);
	}

	public static String convertToAmericanEnglish(String input) {
		for(Entry<String, String> entry : americanEnglishConversions.entrySet()) {
			//input = input.replaceAll(entry.getKey()+"(\\s|\\.|,|s|e|i)", entry.getValue()+"$1");
			//input = input.replaceAll(Util.capitaliseSentence(entry.getKey())+"(\\s|\\.|,|s|e|i)", Util.capitaliseSentence(entry.getValue())+"$1");
		}
		
		return input;
	}

	
	public static List<ParserCommand> commandsList = new ArrayList<>();
	public static Map<BodyPartType, List<ParserCommand>> commandsMap = new EnumMap<>(BodyPartType.class);

	private static String[] lastDescriptors = new String[2];

	static{
		Lexicon.addCommands();
		// Parsing:

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("wall"),
				true,
				true,
				"",
				"返回角色所在区域墙体的名字(用于性交时)。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Cell cell = character.getCell();
				return cell.getWallName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("desk"),
				true,
				true,
				"",
				"防御角色锁在区域桌子的种类名(用于性交时)。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Cell cell = character.getCell();
				return cell.getDeskName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("moneyFormat"),
				true,
				false,
				"(数量, 标签)",
				"将提供的数字转化为金钱格式，使用tag作为html的标签。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				String secondArgument = "span";
				try {
					secondArgument = arguments.split(", ")[1];
				} catch(Exception ex) {
					System.err.println("Formatting 'moneyFormat' missing second argument, so 'span' used instead.");
					ex.printStackTrace();
				}
				return UtilText.formatAsMoney(arguments.split(", ")[0], secondArgument);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("essenceFormat"),
				true,
				false,
				"(数量, 标签)",
				"将提供的数字转化为精华点数的格式，使用tag作为html的标签。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				String secondArgument = "span";
				try {
					secondArgument = arguments.split(", ")[1];
				} catch(Exception ex) {
					System.err.println("Formatting 'essenceFormat' missing second argument, so 'span' used instead.");
					ex.printStackTrace();
				}
				return UtilText.formatAsEssences(arguments.split(", ")[0], secondArgument);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("enchantmentCapacityFormat"),
				true,
				false,
				"(数量, 标签)",
				"将提供的数字转化为附魔上限，使用tag作为html的标签。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				String secondArgument = "span";
				try {
					secondArgument = arguments.split(", ")[1];
				} catch(Exception ex) {
					System.err.println("Formatting 'enchantmentCapacityFormat' missing second argument, so 'span' used instead.");
					ex.printStackTrace();
				}
				return UtilText.formatAsEnchantmentCapacity(Integer.valueOf(arguments.split(", ")[0]), secondArgument);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"intFormat",
						"intToString",
						"parseInt"),
				false,
				false,
				"(整数)",
				"将传入的整数转化为字符串格式。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(Long.valueOf(UtilText.parse(arguments)));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moneyFormatUncoloured",
						"moneyFormatNoColour",
						"moneyFormatUncolored",
						"moneyFormatNoColor"),
				true,
				false,
				"(数量, 标签)",
				"将提供的数字转化为金钱格式，使用tag作为html的标签。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return UtilText.formatAsMoneyUncoloured(arguments.split(", ")[0], arguments.split(", ")[1]);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("money"),
				true,
				false,
				"(是否汉字输出)",
				"返回角色拥有的金钱。传入true则使用汉字显示数字(例如100 -> 一百)。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && (arguments.equalsIgnoreCase("true"))) {
					return Util.intToString(character.getMoney());
				}
				return String.valueOf(character.getMoney());
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("random"),
				true,
				false,
				"(文本1| 文本2| 文本3)",
				"从提供的参数中随机选择一个字符串。嵌套使用'random'命令目前无效。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				List<String> strings = new ArrayList<>();
				for(String s : arguments.split("\\|")) {
					strings.add(UtilText.parse(s.trim()));
				}
				strings.removeIf(s->s.trim().isEmpty());
				if(strings.isEmpty()) {
					return "";
				}
				return Util.randomItemFrom(strings);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("name"),
				true,
				false,
				"(前缀/真实名称)",
				"返回对象的名称，对于非大写字母开头的名字，<b>自动添加“the”(汉化版中不会)</b>。"
				+ "如果提供了前缀，那前缀将会被添加在名称前。"
				+ "如果设置了空格或“true”作为参数，则会返回角色的真实姓名，而忽略玩家是否知道。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge();
					}
					if(!character.isPlayer()) {
						return character.getName(arguments);
					}
				}
				
				if(!speechTarget.isEmpty()) {
					return parseSyntaxNew(specialNPCs, speechTarget, parseCapitalise?"PetName":"petName", target, ParseMode.REGULAR);
					
				} else {
					if(isPlayer(target, character)) {
						if(command.startsWith("N")) {
							return "你";
						} else {
							return "你";
						}
					}
					if(character.isPlayerKnowsName() || character.isPlayer()) {
						return character.getName(true);
					}
					return character.getName();
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("namePos"),
				true,
				false,
				"(前缀/真实名称)",
				"返回对象名称的所有格(汉化版中仅为名称)，对于非大写字母开头的名字，<b>自动添加“the”(汉化版中不会)</b>。"
				+ "如果需要实际的名字(从玩家的第三人称视角，或无视玩家目前知识)，请将“ ”或者“true”作为参数传入。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge();
					}
					return character.getName(arguments);
					
				} else if(!speechTarget.isEmpty()) {
					return parseSyntaxNew(specialNPCs, speechTarget, parseCapitalise?"PetName":"petName", target, ParseMode.REGULAR);
					
				} else {
					if(isPlayer(target, character)) {
						if(command.startsWith("N")) {
							return "你";
						} else {						 
							return "你";
						}
					}
					if(character.isPlayerKnowsName()) {
						return character.getName(true);
					}
					return character.getName();
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("nameIs"),
				true,
				false,
				"(前缀/真实名称)",
				"返回对象名称带缩写系动词(汉化版中仅为名称)，对于非大写字母开头的名字，<b>自动添加“the”(汉化版中不会)</b>。"
				+ "如果需要以第三人称视角获得玩家的实际姓名，请将“true”作为参数传入。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge();
					}
					return character.getName(arguments);
					
				} else if(!speechTarget.isEmpty()) {
					return parseSyntaxNew(specialNPCs, speechTarget, parseCapitalise?"PetName":"petName", target, ParseMode.REGULAR);
					
				} else {
					if(isPlayer(target, character)) {
						return "你";
					}
					if(character.isPlayerKnowsName()) {
						return character.getName(true);
					}
					return character.getName();
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("nameIsFull"),
				true,
				false,
				"(前缀/真实名称)",
				"返回对象名称带系动词(汉化版中仅为名称)，对于非大写字母开头的名字，<b>自动添加“the”(汉化版中不会)</b>。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge();
					}
					return character.getName(arguments);
					
				} else if(!speechTarget.isEmpty()) {
					return parseSyntaxNew(specialNPCs, speechTarget, parseCapitalise?"PetName":"petName", target, ParseMode.REGULAR);
					
				} else {
					if(isPlayer(target, character)) {
						return "你";
					}
					if(character.isPlayerKnowsName()) {
						return character.getName(true);
					}
					return character.getName();
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("nameHas"),
				true,
				false,
				"(前缀/真实名称)",
				"返回对象名称带缩写“have”或“has”(汉化版中仅为名称)，对于非大写字母开头的名字，<b>自动添加“the”(汉化版中不会)</b>。"
				+ "如果需要以第三人称视角获得玩家的实际姓名，请将“true”作为参数传入。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge();
					}
					return character.getName(arguments);
					
				} else if(!speechTarget.isEmpty()) {
					return parseSyntaxNew(specialNPCs, speechTarget, parseCapitalise?"PetName":"petName", target, ParseMode.REGULAR);
					
				} else {
					if(isPlayer(target, character)) {
						return "你";
					}
					if(character.isPlayerKnowsName()) {
						return character.getName(true);
					}
					return character.getName();
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("nameHasFull"),
				true,
				false,
				"(前缀/真实名称)",
				"返回对象名称带完整“have”或“has”(汉化版中仅为名称)，对于非大写字母开头的名字，<b>自动添加“the”(汉化版中不会)</b>。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge();
					}
					return character.getName(arguments);
					
				} else if(!speechTarget.isEmpty()) {
					return parseSyntaxNew(specialNPCs, speechTarget, parseCapitalise?"PetName":"petName", target, ParseMode.REGULAR);
					
				} else {
					if(isPlayer(target, character)) {
						return "你";
					}
					if(character.isPlayerKnowsName()) {
						return character.getName(true);
					}
					return character.getName();
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"verb",
						"verbPerson"),
				true,
				false,
				"(动词)",
				"返回动词的正确形式(大概)。汉化版无意义。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (character.isPlayer()) {
					return arguments;
				} else if (arguments.endsWith("s")
						||arguments.endsWith("x")
						||arguments.endsWith("sh")
						||arguments.endsWith("ch")
						||arguments.endsWith("o")){
					return arguments;
				} else if (arguments.endsWith("y")
						&&!arguments.endsWith("ay")
						&&!arguments.endsWith("ey")
						&&!arguments.endsWith("iy")
						&&!arguments.endsWith("oy")
						&&!arguments.endsWith("uy")) {
					return arguments;
				}else {
					return arguments;
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"walk",
						"slither"),
				true,
				false,
				"",
				"返回该角色移动时的合适动词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getMovementVerbPresentFirstPersonSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"walks",
						"slithers"),
				true,
				false,
				"",
				"返回该角色移动时的合适动词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getMovementVerbPresentThirdPersonSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"walking",
						"slithering"),
				true,
				false,
				"",
				"返回该角色移动时的合适动词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getMovementVerbPresentParticiple();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"walked",
						"slithered"),
				true,
				false,
				"",
				"返回该角色移动时的合适动词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getMovementVerbPastParticiple();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"step",
						"slide"),
				true,
				false,
				"",
				"返回该角色进行一步移动时的合适动词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getIndividualMovementVerbPresentFirstPersonSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"steps",
						"slides"),
				true,
				false,
				"",
				"返回该角色进行一步移动时的合适动词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getIndividualMovementVerbPresentThirdPersonSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"stepping",
						"sliding"),
				true,
				false,
				"",
				"返回该角色进行一步移动时的合适动词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getIndividualMovementVerbPresentParticiple();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"stepped",
						"slid"),
				true,
				false,
				"",
				"返回该角色进行一步移动时的合适动词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getIndividualMovementVerbPastParticiple();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("surname"),
				true,
				false,
				"",
				"返回对象的姓氏。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getSurname();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"fullName",
						"nameFull"),
				true,
				false,
				"(前缀)",
				"返回对象的名字，对于非大写字母开头的名字，<b>自动添加“the”(汉化版中不会)</b>。如果你想使用名字的基础形式，请在参数中传入一个空格。"
				+ "如果提供了前缀，那前缀将会被添加在名称前。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return character.getNameIgnoresPlayerKnowledge()+(character.getSurname().isEmpty()?"":"·"+character.getSurname());
					}
					return character.getName(arguments)+(character.getSurname().isEmpty()?"":"·"+character.getSurname());
				} else {
					return character.getName(false)+(character.getSurname().isEmpty()?"":"·"+character.getSurname());
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pcName",
						"pcPetName"),
				true,
				false,
				"",
				"返回该角色希望被玩家称呼的名字。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getPetName(Main.game.getPlayer());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"petName"),
				true,
				false,
				"(对象)",
				"返回该角色希望被对象称呼的爱称。对象的参数应当为一个解析对象的标签，如“pc”、“npc2”、“lilaya”等等。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractParserTarget parserTarget = findParserTargetWithTag(arguments.replaceAll("\u200b", ""));
//				System.out.println(target+" | "+arguments);
				if (parserTarget == null) {
					return "petName INVALID_TARGET_NAME("+arguments+")";
				}
				try {
					GameCharacter characterTarget = parserTarget.getCharacter(arguments, specialNPCs);
					if(parseCapitalise) {
						return Util.capitaliseSentence(character.getPetName(characterTarget));
					} else {
						return character.getPetName(characterTarget);
					}
				} catch(Exception ex) {
					System.err.println("PetName parsing failed on "+arguments);
					ex.printStackTrace();
				}
				return "";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"description",
						"desc"),
				true,
				false,
				"",
				"返回该角色简短的整体描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getDescription();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"age"),
				true,
				false,
				"",
				"返回该角色的年龄。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getAgeValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"job",
						"jobName"),
				true,
				true,
				"",
				"返回该角色的职位。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isSlave()) {
					return character.getSlaveJob(Main.game.getHourOfDay()).getName(character);
				}
				return character.getHistory().getName(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"desiredJob"),
				true,
				true,
				"",
				"返回该角色期望的职位。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getDesiredJobs().iterator().next().getName(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"jobHourStart",
						"jobStartHour"),
				true,
				true,
				"",
				"返回该角色职位开始工作的时间(24小时制)；奴隶的工作无法查询。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return String.valueOf(character.getHistory().getWorkHourStart());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"jobHourEnd",
						"jobEndHour",
						"jobHourFinish",
						"jobFinishHour"),
				true,
				true,
				"",
				"返回该角色职位结束工作的时间(24小时制)；奴隶的工作无法查询。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return String.valueOf(character.getHistory().getWorkHourEnd());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"jobTimeStart",
						"jobStartTime"),
				true,
				true,
				"",
				"返回该角色职位开始工作的时间(本地时)；奴隶的工作无法查询。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.time(LocalTime.of(character.getHistory().getWorkHourStart(), 0, 0));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"jobTimeEnd",
						"jobEndTime",
						"jobTimeFinish",
						"jobFinishTime"),
				true,
				true,
				"",
				"返回该角色职位结束工作的时间(本地时)；奴隶的工作无法查询。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.time(LocalTime.of(character.getHistory().getWorkHourEnd(), 0, 0));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"jobDayStart",
						"jobStartDay"),
				true,
				true,
				"",
				"返回该角色职位开始工作的星期；奴隶的工作无法查询。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHistory().getStartDay().getDisplayName(TextStyle.FULL, Locale.CHINESE);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"jobDayEnd",
						"jobEndDay",
						"jobDayFinish",
						"jobFinishDay"),
				true,
				true,
				"",
				"返回该角色职位结束工作的星期；奴隶的工作无法查询。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHistory().getEndDay().getDisplayName(TextStyle.FULL, Locale.CHINESE);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"gender"),
				true,
				true,
				"(是否上色)",
				"返回该角色的性别(参数为true时带颜色)。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Gender gender = character.getGender();
				
				boolean pronoun = parseAddPronoun;
				parseAddPronoun = false;
				if(arguments!=null && Boolean.valueOf(arguments)) {
					return "<span style='color:"+gender.getColour().toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(gender.getName())+"":"")+gender.getName())
											:(pronoun?UtilText.generateSingularDeterminer(gender.getName())+"":"")+gender.getName())
							+"</span>";
				}
				return parseCapitalise
						?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(gender.getName())+"":"")+gender.getName())
						:(pronoun?UtilText.generateSingularDeterminer(gender.getName())+"":"")+gender.getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"genderAppears",
						"genderAppearsAs",
						"appearsAsGender"),
				true,
				true,
				"(coloured)",
				"返回该角色外表展示出来的性别(可能与真实性别不同)，如果参数为“true”则进行染色。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Gender gender = character.getAppearsAsGender();
				
				boolean pronoun = parseAddPronoun;
				parseAddPronoun = false;
				if(arguments!=null && Boolean.valueOf(arguments)) {
					return "<span style='color:"+gender.getColour().toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(gender.getName())+"":"")+gender.getName())
											:(pronoun?UtilText.generateSingularDeterminer(gender.getName())+"":"")+gender.getName())
							+"</span>";
				}
				return parseCapitalise
						?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(gender.getName())+"":"")+gender.getName())
						:(pronoun?UtilText.generateSingularDeterminer(gender.getName())+"":"")+gender.getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"orientation"),
				true,
				true,
				"",
				"返回该角色的性取向。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getSexualOrientation().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"affection"),
				true,
				true,
				"(对象)",
				"输出该角色对于对象的好感程度的名称。例如，lilaya.affection(pc)将会默认输出“喜爱”"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractParserTarget parserTarget = findParserTargetWithTag(arguments.replaceAll("\u200b", ""));
				try {
					GameCharacter targetedCharacter = parserTarget.getCharacter(arguments.toLowerCase(), null);
					return character.getAffectionLevel(targetedCharacter).getDescriptor();
				} catch(Exception ex) {
					ex.printStackTrace();
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>错误：未找到“affection”指令的角色参数！("+arguments+")</i>";
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"companion"),
				true,
				true,
				"",
				"一半概率显示这个角色与他们的队伍领袖关系中最重要的关系名(多重关系将会被所见)。另一半概率只返回“同伴”或“奴隶”(如果适用)。"
				+ "例如，如果玩家的同伴是他们的女儿，那么“npc.companion”将显示为“女儿”，否则是“同伴”或“奴隶”(如果适用)。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				try {
					GameCharacter targetedCharacter = character.getPartyLeader();
					if(targetedCharacter==null) {
						return "同伴";
					}
					Set<Relationship> set = character.getRelationshipsTo(targetedCharacter);
					if(set.size()>=1 && Math.random()<0.5f) {
						return set.iterator().next().getName(character);
					} else {
						if(character.isSlave() && character.getOwner().equals(targetedCharacter) && Math.random()<0.5f) {
							return "奴隶";
						}
						return "同伴";
					}
					
				} catch(Exception ex) {
					ex.printStackTrace();
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>错误：未找到“relation”指令的角色参数！("+arguments+")</i>";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"relation",
						"relationship"),
				true,
				true,
				"(对象)",
				"输出该角色对于对象最重要的亲属关系的名称，(不会显示多个亲属关系名称)。例如blaze.relation(crystal)将会输出“兄弟”"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractParserTarget parserTarget = findParserTargetWithTag(arguments.replaceAll("\u200b", ""));
				try {
					GameCharacter targetedCharacter = parserTarget.getCharacter(arguments.toLowerCase(), specialNPCs);
					Set<Relationship> set = character.getRelationshipsTo(targetedCharacter);
					if(set.size()>=1) {
						return set.iterator().next().getName(character);
					} else {
						return "无亲缘关系";
					}
//					return character.getRelationshipStrTo(targetedCharacter);
					
				} catch(Exception ex) {
					ex.printStackTrace();
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>错误：未找到“relation”指令的角色参数！("+arguments+")</i>";
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"relationFull",
						"relationshipFull"),
				true,
				true,
				"(对象)",
				"输出该橘色对于对象的所有亲属关系的名称。例如lilaya.relation(pc)可能会输出“半亲姐妹和姨妈”"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractParserTarget parserTarget = findParserTargetWithTag(arguments.replaceAll("\u200b", ""));
				try {
					GameCharacter targetedCharacter = parserTarget.getCharacter(arguments.toLowerCase(), specialNPCs);
					return character.getRelationshipStrTo(targetedCharacter);
					
				} catch(Exception ex) {
					ex.printStackTrace();
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>错误：未找到“relation”指令的角色参数！("+arguments+")</i>";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"daughter",
						"son"),
				true,
				true,
				"",
				"根据角色的女性化程度，可能返回“儿子”或“女儿”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Relationship.Child.toString(character.getGender().getType());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mother",
						"father"),
				true,
				true,
				"",
				"根据角色的女性化程度，可能返回“父亲”或“母亲”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Relationship.Parent.toString(character.getGender().getType());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mommy",
						"daddy"),
				true,
				true,
				"",
				"根据角色的女性化程度，可能返回“爹地”或“妈咪”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "妈咪";
				} else {
					return "爹地";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mom",
						"mum",
						"dad"),
				true,
				true,
				"",
				"根据角色的女性化程度，可能返回“爸爸”或“妈妈”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "妈妈";
				} else {
					return "爸爸";
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sister",
						"brother"),
				true,
				true,
				"",
				"根据角色的女性化程度，可能返回“兄弟”或“姐妹”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Relationship.Sibling.toString(character.getGender().getType());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sis",
						"bro"),
				true,
				true,
				"",
				"根据角色的女性化程度，可能返回“兄弟”或“姐妹”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "姐妹";
				} else {
					return "兄弟";
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"niece",
						"nephew"),
				true,
				true,
				"",
				"根据角色的女性化程度，可能返回“侄子”或“侄女”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Relationship.Nibling.toString(character.getGender().getType());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mistress",
						"master"),
				true,
				true,
				"",
				"返回“主人”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "主人";
				} else {
					return "主人";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"maam",
						"ma'am",
						"sir"),
				true,
				true,
				"",
				"根据角色的女性化程度，可能返回“先生”或“女士”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "女士";
				} else {
					return "先生";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lord",
						"lady"),
				true,
				true,
				"",
				"根据角色的女性化程度，可能返回“大人”或“小姐”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "小姐";
				} else {
					return "大人";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"heroine",
						"hero"),
				true,
				true,
				"",
				"返回“英雄”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "英雄";
				} else {
					return "英雄";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"miss",
						"ms",
						"mister",
						"mr"),
				true,
				true,
				"",
				"根据角色的女性化程度，可能返回“先生”或“女士”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "女士";
				} else {
					return "先生";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"boyfriend",
						"girlfriend"),
				true,
				true,
				"",
				"根据角色的女性化程度，可能返回“男朋友”或“女朋友”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "女朋友";
				} else {
					return "男朋友";
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"filly",
						"mule"),
				true,
				true,
				"",
				"返回“骡子”，因为0.4.10.10版本娜塔莉亚的奴隶名称从小雌驹改为了骡子。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				try {
					if(Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK).getStickers().get("txt").equals("filly")) {
						return "小雌驹";
					}
				} catch(Exception ex) {
				}
				return "骡子";
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"fillies",
						"mules"),
				true,
				true,
				"",
				"返回“骡子”，因为0.4.10.10版本娜塔莉亚的奴隶名称从小雌驹改为了骡子。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				try {
					if(Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK).getStickers().get("txt").equals("filly")) {
						return "小雌驹";
					}
				} catch(Exception ex) {
				}
				return "骡子";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bitch",
						"slut",
						"insult"),
				true,
				true,
				"",
				"返回随机形容该角色的脏话，基于其女性化程度。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return UtilText.returnStringAtRandom("婊子", "荡妇", "淫妇", "妓女", "滥交女");
				} else {
					return UtilText.returnStringAtRandom("蠢货", "混蛋", "混球", "丑逼");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bitch+",
						"slut+",
						"insult+",
						"bitchD",
						"slutD",
						"insultD"),
				true,
				true,
				"",
				"随机返回形容该角色的脏话，基于其女性化程度，并在前面加上一个描述词。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				String naughtyDescriptor = Util.randomItemFromValues("一无是处", "愚蠢", "肮脏", "下贱");
				if(character.isFeminine()) {
					return naughtyDescriptor+"的"+UtilText.returnStringAtRandom("婊子", "荡妇", "淫妇", "妓女", "滥交女");
				} else {
					return naughtyDescriptor+"的"+UtilText.returnStringAtRandom("蠢货", "混蛋", "混球", "丑逼");
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bitches",
						"sluts",
						"insultPlural"),
				true,
				true,
				"",
				"返回随机形容该角色的脏话(复数)，基于其女性化程度。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return UtilText.returnStringAtRandom("婊子", "荡妇", "淫妇", "妓女", "滥交女");
				} else {
					return UtilText.returnStringAtRandom("蠢货", "混蛋", "混球", "操蛋的");
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bitches+",
						"sluts+",
						"insultPlural+",
						"bitchesD",
						"slutsD",
						"insultPluralD"),
				true,
				true,
				"",
				"随机返回形容该角色的脏话(复数)，基于其女性化程度，并在前面加上一个描述词。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				String naughtyDescriptor = Util.randomItemFromValues("一文不值", "愚蠢", "肮脏", "下贱");
				if(character.isFeminine()) {
					return naughtyDescriptor+"的"+UtilText.returnStringAtRandom("婊子", "荡妇", "淫妇", "妓女", "滥交女");
				} else {
					return naughtyDescriptor+"的"+UtilText.returnStringAtRandom("蠢货", "混蛋", "混球", "操蛋的");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hun",
						"babe"),
				true,
				true,
				"",
				"返回随机形容该角色的脏话，基于其女性化程度。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "宝贝儿";
				} else {
					return "臭男人";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"fullRace",
						"raceFull",
						"femininityRace"),
				true,
				true,
				"(是否上色)",
				"返回该角色种族(包括女性化程度)的完整描述。传入“true”以染色文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				boolean pronoun = parseAddPronoun;
				parseAddPronoun = false;
				if(arguments!=null && Boolean.valueOf(arguments)) {
					return "<span style='color:"+character.getFemininity().getColour().toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence(Femininity.getFemininityName(character.getFemininityValue(), pronoun))
									:Femininity.getFemininityName(character.getFemininityValue(), pronoun))+"</span>的"
							+ "<span style='color:"+character.getRaceStage().getColour().toWebHexString()+";'>" +character.getRaceStage().getName()+"</span>"
							+ "<span style='color:"+character.getSubspecies().getColour(character).toWebHexString()+";'>" + getSubspeciesName(character.getSubspecies(),character) + "</span>";
				}
				return (parseCapitalise
						?Util.capitaliseSentence(Femininity.getFemininityName(character.getFemininityValue(), pronoun))
						:Femininity.getFemininityName(character.getFemininityValue(), pronoun))+"的"+character.getRaceStage().getName()+getSubspeciesName(character.getSubspecies(),character);
			}
			@Override
			protected String applyDeterminer(String descriptor, String input) {
				return input;
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"fullRaces",
						"racesFull",
						"femininityRaces"),
				true,
				true,
				"(是否上色)",
				"返回该角色种族(复数)(包括女性化程度)的完整描述。传入“true”以染色文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				boolean pronoun = parseAddPronoun;
				parseAddPronoun = false;
				if(arguments!=null && Boolean.valueOf(arguments)) {
					return "<span style='color:"+character.getFemininity().getColour().toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence(Femininity.getFemininityName(character.getFemininityValue(), pronoun))
									:Femininity.getFemininityName(character.getFemininityValue(), pronoun))+"</span>的"
							+ "<span style='color:"+character.getRaceStage().getColour().toWebHexString()+";'>" +character.getRaceStage().getName()+"</span>"
							+ "<span style='color:"+character.getSubspecies().getColour(character).toWebHexString()+";'>" +  getSubspeciesNamePlural(character.getSubspecies(),character) + "</span>";
				}
				return (parseCapitalise
						?Util.capitaliseSentence(Femininity.getFemininityName(character.getFemininityValue(), pronoun))
						:Femininity.getFemininityName(character.getFemininityValue(), pronoun))+"的"+character.getRaceStage().getName()+""+getSubspeciesNamePlural(character.getSubspecies(),character);
			}
			@Override
			protected String applyDeterminer(String descriptor, String input) {
				return input;
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"race"),
				true,
				true,
				"(是否上色)",
				"返回该角色的种族名称。传入“true”以染色文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && Boolean.valueOf(arguments)) {
					boolean pronoun = parseAddPronoun;
					parseAddPronoun = false;
					String name = character.isRaceConcealed()?"未知种族":getSubspeciesName(character.getSubspecies(), character);
					return "<span style='color:"+(character.isRaceConcealed()?PresetColour.TEXT_GREY:character.getSubspecies().getColour(character)).toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(name)+"":"")+name)
									:(pronoun?UtilText.generateSingularDeterminer(name)+"":"")+name)
							+"</span>";
				}
				return getSubspeciesName(character.getSubspecies(), character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"races",
						"racePlural"),
				true,
				true,
				"(是否上色)",
				"返回该角色的种族名称(复数)。传入“true”以染色文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && Boolean.valueOf(arguments)) {
					boolean pronoun = parseAddPronoun;
					parseAddPronoun = false;
					String name = character.isRaceConcealed()?"未知种族":getSubspeciesNamePlural(character.getSubspecies(), character);
					return "<span style='color:"+(character.isRaceConcealed()?PresetColour.TEXT_GREY:character.getSubspecies().getColour(character)).toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(name)+"":"")+name)
									:(pronoun?UtilText.generateSingularDeterminer(name)+"":"")+name)
							+"</span>";
				}
				return getSubspeciesNamePlural(character.getSubspecies(), character);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"raceFeral",
						"feralRace"),
				true,
				true,
				"(是否上色)",
				"返回该角色的兽态名称。传入“true”以染色文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && Boolean.valueOf(arguments)) {
					boolean pronoun = parseAddPronoun;
					parseAddPronoun = false;
					String name = character.isRaceConcealed()?"未知种族":character.getSubspecies().getFeralName(character.getBody());
					return "<span style='color:"+(character.isRaceConcealed()?PresetColour.TEXT_GREY:character.getSubspecies().getColour(character)).toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(name)+"":"")+name)
									:(pronoun?UtilText.generateSingularDeterminer(name)+"":"")+name)
							+"</span>";
				}
				return character.getSubspecies().getFeralName(character.getBody());
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"passiveForm",
						"passiveRace",
						"racePassive"),
				true,
				true,
				"(是否上色)",
				"返回元素被动状态的名称。传入'true'以染色文本。(如果对象不是元素则返回一个错误代码。)"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(!character.isElemental()) {
					return "[style.italicsBad(“passiveRace”指令对于非元素生物不生效！)]";
				}
				Elemental elemental = ((Elemental)character);
				String name = elemental.getPassiveForm()==null
						?"wisp"
						:elemental.getPassiveForm().getFeralName(elemental.getBody());
				if(arguments!=null && Boolean.valueOf(arguments)) {
					boolean pronoun = parseAddPronoun;
					parseAddPronoun = false;
					return "<span style='color:"+(elemental.getPassiveForm()==null?elemental.getCurrentSchool().getColour():elemental.getPassiveForm().getColour(character)).toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(name)+"":"")+name)
									:(pronoun?UtilText.generateSingularDeterminer(name)+"":"")+name)
							+"</span>";
				}
				return name;
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"school",
						"elementalSchool",
						"boundSchool"),
				true,
				true,
				"(是否上色)",
				"返回该元素对应的奥术流派名。输入“true”可为文本着色。(如果目标不是元素，则返回错误代码。)"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(!character.isElemental()) {
					return "[style.italicsBad(“boundSchool”指令对于非元素生物不生效！)]";
				}
				Elemental elemental = ((Elemental)character);
				String name = elemental.getCurrentSchool().getName();
				if(arguments!=null && Boolean.valueOf(arguments)) {
					boolean pronoun = parseAddPronoun;
					parseAddPronoun = false;
					return "<span style='color:"+elemental.getCurrentSchool().getColour().toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(name)+"":"")+name)
									:(pronoun?UtilText.generateSingularDeterminer(name)+"":"")+name)
							+"</span>";
				}
				return name;
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"raceStage"),
				true,
				true,
				"(是否上色)",
				"返回角色种族“阶段”的名字(似、亚、纯)。传入“true”以染色文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && Boolean.valueOf(arguments)) {
					boolean pronoun = parseAddPronoun;
					parseAddPronoun = false;
					String name = character.getRaceStage().getName();
					return "<span style='color:"+character.getRaceStage().getColour().toWebHexString()+";'>"
							+ (parseCapitalise
									?Util.capitaliseSentence((pronoun?UtilText.generateSingularDeterminer(name)+"":"")+name)
									:(pronoun?UtilText.generateSingularDeterminer(name)+"":"")+name)
							+"</span>";
				}
				return character.getRaceStage().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"preferredBody"),
				false,
				false,
				"(tag)",
				"返回该角色体态的对应描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(!(character instanceof NPC)) {
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>preferredBody_not_npc</i>";
				}
				if(arguments!=null) {
					return ((NPC) character).getPreferredBodyDescription(arguments);
				}
				return ((NPC) character).getPreferredBodyDescription("b");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialDescriptor",
						"materialCompositionDescriptor",
						"compositionDescriptor"),
				true,
				false,
				"",
				"返回角色的身体材质(BodyMaterial)名"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getBodyMaterial()==BodyMaterial.FLESH) {
					return "覆盖着";
				} else {
					return UtilText.returnStringAtRandom(
							"成分为",
							"完全组成自",
							"形成自",
							"成分完全为");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bodyMaterial",
						"materialName"),
				true,
				true,
				"",
				"返回角色的身体材质(BodyMaterial)名"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialSkin"),
				true,
				true,
				"",
				"返回角色的皮肤描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getSkinNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialSkinAdjective"),
				true,
				true,
				"",
				"返回人物皮肤的形容描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getSkinAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialSkinAlt"),
				true,
				true,
				"",
				"返回角色非人类部位的皮肤的描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getSkinAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialSkinAltAdjective"),
				true,
				true,
				"",
				"返回描述角色非人类身体部位皮肤的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getSkinAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialOrifice"),
				true,
				true,
				"",
				"返回角色腔穴内壁的质地描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getOrificeNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialOrificeAdjective"),
				true,
				true,
				"",
				"返回覆盖在角色腔穴上材质的形容性描述词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getOrificeAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialOrificeAlt"),
				true,
				true,
				"",
				"返回角色非人类部位腔穴内壁的描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getOrificeAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialOrificeAltAdjective"),
				true,
				true,
				"",
				"返回描述角色非人类身体部位腔穴内壁的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getOrificeAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialHair"),
				true,
				true,
				"",
				"返回角色的头发描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getHairNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialHairAdjective"),
				true,
				true,
				"",
				"返回描述角色头发的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getHairAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialHairBody",
						"materialBodyHair"),
				true,
				true,
				"",
				"返回角色体毛的描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getHairBodyNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialHairBodyAdjective",
						"materialBodyHairAdjective"),
				true,
				true,
				"",
				"返回描述角色体毛的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getHairBodyAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialHairAlt"),
				true,
				true,
				"",
				"返回角色非人类部位毛发的描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getHairAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialHairAltAdjective"),
				true,
				true,
				"",
				"返回描述角色非人类身体部位毛发的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getHairAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFur"),
				true,
				true,
				"",
				"返回角色的皮毛描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFurNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFurAdjective"),
				true,
				true,
				"",
				"返回描述角色皮毛的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFurAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFurAlt"),
				true,
				true,
				"",
				"返回角色非人类部位皮毛的描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFurAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFurAltAdjective"),
				true,
				true,
				"",
				"返回描述角色非人类部位毛皮的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFurAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFeather",
						"materialFeathers"),
				true,
				true,
				"",
				"返回角色羽毛的描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFeatherNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFeatherAdjective",
						"materialFeathersAdjective"),
				true,
				true,
				"",
				"返回描述角色羽毛的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFeatherAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFeatherAlt",
						"materialFeathersAlt"),
				true,
				true,
				"",
				"返回角色非人类部位羽毛的描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFeatherAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialFeatherAltAdjective",
						"materialFeathersAltAdjective"),
				true,
				true,
				"",
				"返回描述角色非人类部位毛皮的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getFeatherAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialScale",
						"materialScales"),
				true,
				true,
				"",
				"返回角色的鳞片描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getScaleNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialScaleAdjective",
						"materialScalesAdjective"),
				true,
				true,
				"",
				"返回描述角色鳞片的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getScaleAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialScaleAlt",
						"materialScalesAlt"),
				true,
				true,
				"",
				"返回角色非人类部位鳞片的描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getScaleAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialScaleAltAdjective",
						"materialScalesAltAdjective"),
				true,
				true,
				"",
				"返回描述角色非人类身体部位鳞片的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getScaleAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialShell"),
				true,
				true,
				"",
				"返回角色的外壳描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getShellNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialShellAdjective"),
				true,
				true,
				"",
				"返回描述角色外壳的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getShellAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialShellAlt"),
				true,
				true,
				"",
				"返回角色非人类部位外壳的描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getShellAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialShellAltAdjective"),
				true,
				true,
				"",
				"返回描述角色非人类身体部位外壳的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getShellAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialKeratin"),
				true,
				true,
				"",
				"返回角色的角质描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getKeratinNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialKeratinAdjective"),
				true,
				true,
				"",
				"返回描述角色角质的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getKeratinAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialKeratinAlt"),
				true,
				true,
				"",
				"返回角色非人类部位角质的描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getKeratinAltNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"materialKeratinAltAdjective"),
				true,
				true,
				"",
				"返回描述角色非人类身体部位角质的形容词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyMaterial().getKeratinAltAdj();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"femininity",
						"fem",
						"masculinity",
						"mas"),
				true,
				true,
				"(是否上色)",
				"返回该角色的女性化程度的名称。传入“true”作为参数，使返回的文本呈现女性化程度的颜色。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Femininity fem =  Femininity.valueOf(character.getFemininityValue());
				boolean pronoun = parseAddPronoun;
				parseAddPronoun = false;
				if(arguments!=null && Boolean.valueOf(arguments)) {
					return "<span style='color:"+fem.getColour().toWebHexString()+";'>"+fem.getName(pronoun)+"</span>";
				}
				return fem.getName(pronoun);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bodySize"),
				true,
				true,
				"",
				"返回角色体型的描述词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return BodySize.valueOf(character.getBodySizeValue()).getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"muscle"),
				true,
				true,
				"",
				"返回角色肌肉量的描述词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Muscle.valueOf(character.getMuscleValue()).getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bodyShape"),
				true,
				true,
				"",
				"返回角色身体形态的描述词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBodyShape().getName(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"height"),
				true,
				true,
				"",
				"返回角色身高的描述词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHeight().getDescriptor();
			}
		});
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues("heightValue"),
				false,
				false,
				"",
				"以本地化的完整格式返回角色的身高。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.size(character.getHeightValue(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"heightDown",
						"heightUp"),
				true,
				true,
				"对象",
				"如果这个角色比目标更高，返回“down”；如果他们比目标更矮，返回“up”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractParserTarget parserTarget = findParserTargetWithTag(arguments.replaceAll("\u200b", ""));
				try {
					GameCharacter targetedCharacter = parserTarget.getCharacter(arguments.toLowerCase(), null);
					if(targetedCharacter.getHeightValue()<character.getHeightValue()) {
						return "低头";
					} else {
						return "抬头";
					}
				} catch(Exception ex) {
					ex.printStackTrace();
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>错误：未找到“heightDown”指令的角色参数！("+arguments+")</i>";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"weight"),
				false,
				false,
				"",
				"以本地化的完整格式返回角色的体重。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.weight(character.getWeight() / 1000.0, Units.ValueType.NUMERIC, Units.UnitType.LONG);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speech"),
				false,
				false,
				"(发言内容)",
				"以目标角色说话的语气，解析其中包含的对话文本。自动添加所有影响说话的额外效果，例如醉话、口齿不清、浪叫声等。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseSpeech(arguments, character);
				} else {
					return parseSpeech("……", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechMasculine",
						"masculineSpeech"),
				false,
				false,
				"(发言内容)",
				"以常规男性化角色说话的语气解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.MASCULINE);
				} else {
					return parseNPCSpeech("……", Femininity.MASCULINE);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechMasculineStrong",
						"speechMasculineHeavy",
						"speechMasculinePlus",
						"masculineStrongSpeech",
						"masculineHeavySpeech",
						"masculinePlusSpeech"),
				false,
				false,
				"(发言内容)",
				"以常规的极其男性化的角色说话的语气，解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.MASCULINE_STRONG);
				} else {
					return parseNPCSpeech("……", Femininity.MASCULINE_STRONG);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechAndrogynous",
						"androgynousSpeech"),
				false,
				false,
				"(发言内容)",
				"以常规的中性化角色说话的语气解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.ANDROGYNOUS);
				} else {
					return parseNPCSpeech("……", Femininity.ANDROGYNOUS);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechFeminine",
						"feminineSpeech"),
				false,
				false,
				"(发言内容)",
				"以常规女性化角色说话的语气解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.FEMININE);
				} else {
					return parseNPCSpeech("……", Femininity.FEMININE);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechFeminineStrong",
						"speechFeminineHeavy",
						"speechFemininePlus",
						"feminineStrongSpeech",
						"feminineHeavySpeech",
						"femininePlusSpeech"),
				false,
				false,
				"(发言内容)",
				"以常规极其女性化角色说话的语气解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeech(arguments, Femininity.FEMININE_STRONG);
				} else {
					return parseNPCSpeech("……", Femininity.FEMININE_STRONG);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechMasculineDoll",
						"masculineSpeechDoll"),
				false,
				false,
				"(发言内容)",
				"以常规男性化角色说话的语气解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeechDoll(arguments, Femininity.MASCULINE);
				} else {
					return parseNPCSpeechDoll("……", Femininity.MASCULINE);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechMasculineStrongDoll",
						"speechMasculineHeavyDoll",
						"speechMasculinePlusDoll",
						"masculineStrongSpeechDoll",
						"masculineHeavySpeechDoll",
						"masculinePlusSpeechDoll"),
				false,
				false,
				"(发言内容)",
				"以常规的极其男性化的角色说话的语气，解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeechDoll(arguments, Femininity.MASCULINE_STRONG);
				} else {
					return parseNPCSpeechDoll("……", Femininity.MASCULINE_STRONG);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechAndrogynousDoll",
						"androgynousSpeechDoll"),
				false,
				false,
				"(发言内容)",
				"以常规的中性化角色说话的语气解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeechDoll(arguments, Femininity.ANDROGYNOUS);
				} else {
					return parseNPCSpeechDoll("……", Femininity.ANDROGYNOUS);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechFeminineDoll",
						"feminineSpeechDoll"),
				false,
				false,
				"(发言内容)",
				"以常规女性化角色说话的语气解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeechDoll(arguments, Femininity.FEMININE);
				} else {
					return parseNPCSpeechDoll("……", Femininity.FEMININE);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechFeminineStrongDoll",
						"speechFeminineHeavyDoll",
						"speechFemininePlusDoll",
						"feminineStrongSpeechDoll",
						"feminineHeavySpeechDoll",
						"femininePlusSpeechDoll"),
				false,
				false,
				"(发言内容)",
				"以常规极其女性化角色说话的语气解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCSpeechDoll(arguments, Femininity.FEMININE_STRONG);
				} else {
					return parseNPCSpeechDoll("……", Femininity.FEMININE_STRONG);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechNoEffects"),
				false,
				false,
				"(发言内容)",
				"以目标角色说话的语气，解析其中包含的对话文本。但移除 *所有* 影响说话的额外效果，例如醉话、口齿不清、浪叫声等。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseSpeechNoEffects(arguments, character);
				} else {
					return parseSpeechNoEffects("……", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"speechNoExtraEffects"),
				false,
				false,
				"(发言内容)",
				"以目标角色说话的语气，解析其中包含的对话文本。但移除与角色性格不符的影响说话的额外效果，例如醉话、口齿不清、浪叫声等。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseSpeechNoExtraEffects(arguments, character);
				} else {
					return parseSpeechNoExtraEffects("……", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"thought"),
				false,
				false,
				"(思考内容)",
				"以角色内心思考的形式解析作为参数传入的文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseThought(arguments, character);
				} else {
					return parseThought("……", character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"thoughtMasculine",
						"masculineThought"),
				false,
				false,
				"(思考内容)",
				"以常规男性化角色说话的语气解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCThought(arguments, Femininity.MASCULINE);
				} else {
					return parseNPCThought("……", Femininity.MASCULINE);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"thoughtMasculineStrong",
						"thoughtMasculineHeavy",
						"thoughtMasculinePlus",
						"masculineStrongThought",
						"masculineHeavyThought",
						"masculinePlusThought"),
				false,
				false,
				"(思考内容)",
				"以常规的极其男性化的角色说话的语气，解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCThought(arguments, Femininity.MASCULINE_STRONG);
				} else {
					return parseNPCThought("……", Femininity.MASCULINE_STRONG);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"thoughtAndrogynous",
						"androgynousThought"),
				false,
				false,
				"(思考内容)",
				"以常规的中性化角色说话的语气解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCThought(arguments, Femininity.ANDROGYNOUS);
				} else {
					return parseNPCThought("……", Femininity.ANDROGYNOUS);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"thoughtFeminine",
						"feminineThought"),
				false,
				false,
				"(思考内容)",
				"以常规女性化角色说话的语气解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCThought(arguments, Femininity.FEMININE);
				} else {
					return parseNPCThought("……", Femininity.FEMININE);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"thoughtFeminineStrong",
						"thoughtFeminineHeavy",
						"thoughtFemininePlus",
						"feminineStrongThought",
						"feminineHeavyThought",
						"femininePlusThought"),
				false,
				false,
				"(思考内容)",
				"以常规极其女性化角色说话的语气解析其中包含的对话文本。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					return parseNPCThought(arguments, Femininity.FEMININE_STRONG);
				} else {
					return parseNPCThought("……", Femininity.FEMININE_STRONG);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moan",
						"groan",
						"sob",
						"cry"),
				true,
				true,
				"",
				"返回目标最可能发出的“淫叫声”变体。例如某女性化角色，则会呻吟(moan)，而男性化角色则是呻吟/哼唧(groan)。"
				+ "该方法会判断目标是否抵抗，如果目标抵抗，将返回类似于“呜咽”或“哭泣”的声音。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("呜咽", "尖叫", "哭喊");
						} else {
							return returnStringAtRandom("叫喊", "哭喊");
						}
					}
				}
				
				if(character.isFeminine()) {
					return returnStringAtRandom("呻吟", "浪叫", "喘息", "娇喘");
				} else {
					return returnStringAtRandom("呻吟", "咕哝");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moan+",
						"moanD",
						"groan+",
						"groanD",
						"sob+",
						"sobD",
						"cry+",
						"cryD"),
				true,
				true,
				"",
				"返回目标最可能发出的“淫叫声”变体。例如某女性化角色，则会呻吟(moan)，而男性化角色则是呻吟/哼唧(groan)。"
				+ "该方法会判断目标是否抵抗，如果目标抵抗，将返回类似于“呜咽”或“哭泣”的声音。"
				+ "<b>扩展“呻吟”命令：</b>该命令将在“呻吟”之前附加一个合适的描述符。如“淫荡的尖叫”或“急切的喘息”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("可悲的", "可怜的", "痛苦的") + "" + returnStringAtRandom("呜咽", "尖叫", "哭喊");
						} else {
							return returnStringAtRandom("可悲的", "可怜的", "痛苦的") + "" + returnStringAtRandom("叫喊", "哭喊");
						}
						
					} else if(Main.sex.getSexPace(character)==SexPace.DOM_GENTLE) {
						if(character.isFeminine()) {
							return returnStringAtRandom("轻柔的", "和缓的", "轻声") + "" + returnStringAtRandom("呻吟", "叹息", "喘息");
						} else {
							return returnStringAtRandom("轻柔的", "和缓的", "轻声") + "" + returnStringAtRandom("呻吟", "咕哝");
						}
					}
				}
				
				if(character.isFeminine()) {
					return returnStringAtRandom("淫荡的", "高声", "无法抑制的") + "" + returnStringAtRandom("呻吟", "浪叫", "叫喊", "喘息");
				} else {
					return returnStringAtRandom("深沉的", "低声", "无法抑制的") + "" + returnStringAtRandom("呻吟", "咕哝");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moanVerb",
						"groanVerb",
						"sobVerb",
						"cryVerb",
						"moansVerb",
						"groansVerb",
						"sobsVerb",
						"criesVerb"),
				true,
				true,
				"",
				"返回目标最可能发出的“淫叫声”变体。例如某女性化角色，则会呻吟(moan)，而男性化角色则是呻吟/哼唧(groan)。"
				+ "该方法会判断目标是否抵抗，如果目标抵抗，将返回类似于“呜咽”或“哭泣”的声音。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isPlayer()) {
					if(Main.game.isInSex()) {
						if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
							if(character.isFeminine()) {
								return returnStringAtRandom("呜咽", "尖叫", "哭喊");
							} else {
								return returnStringAtRandom("叫喊", "哭喊");
							}
						}
					}
					
					if(character.isFeminine()) {
						return returnStringAtRandom("呻吟", "浪叫", "喘息", "娇喘");
					} else {
						return returnStringAtRandom("呻吟", "咕哝");
					}
				} else {
					if(Main.game.isInSex()) {
						if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
							if(character.isFeminine()) {
								return returnStringAtRandom("呜咽", "尖叫", "哭喊");
							} else {
								return returnStringAtRandom("叫喊", "哭喊");
							}
						}
					}
					
					if(character.isFeminine()) {
						return returnStringAtRandom("呻吟", "浪叫", "喘息", "娇喘");
					} else {
						return returnStringAtRandom("呻吟", "咕哝");
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moanVerb+",
						"moanVerbD",
						"groanVerb+",
						"groanVerbD",
						"sobVerb+",
						"sobVerbD",
						"cryVerb+",
						"cryVerbD",
						"moansVerb+",
						"moansVerbD",
						"groansVerb+",
						"groansVerbD",
						"sobsVerb+",
						"sobsVerbD",
						"criesVerb+",
						"criesVerbD"),
				true,
				true,
				"",
				"返回目标最可能发出的“淫叫声”变体。例如某女性化角色，则会呻吟(moan)，而男性化角色则是呻吟/哼唧(groan)。"
				+ "该方法会判断目标是否抵抗，如果目标抵抗，将返回类似于“呜咽”或“哭泣”的声音。"
				+ "<b>扩展“呻吟”命令：</b>该命令将在“呻吟”之前附加一个合适的描述符。如“淫荡的尖叫”或“急切的喘息”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isPlayer()) {
					if(Main.game.isInSex()) {
						if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
							if(character.isFeminine()) {
								return returnStringAtRandom("可悲地", "可怜地") + "" + returnStringAtRandom("呜咽", "尖叫", "哭喊");
							} else {
								return returnStringAtRandom("可悲地", "可怜地") + "" + returnStringAtRandom("叫喊", "哭喊");
							}
							
						} else if(Main.sex.getSexPace(character)==SexPace.DOM_GENTLE) {
							if(character.isFeminine()) {
								return returnStringAtRandom("轻柔地", "和缓地", "轻声") + "" + returnStringAtRandom("呻吟", "叹息", "喊叫", "喘息");
							} else {
								return returnStringAtRandom("轻柔地", "和缓地", "轻声") + "" + returnStringAtRandom("呻吟", "咕哝");
							}
						}
					}
					
					if(character.isFeminine()) {
						return returnStringAtRandom("淫荡地", "饥渴地") + "" + returnStringAtRandom("呻吟", "浪叫", "哭喊", "喘息");
					} else {
						return returnStringAtRandom("淫荡地", "饥渴地") + "" + returnStringAtRandom("呻吟", "咕哝");
					}
				} else {
					if(Main.game.isInSex()) {
						if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
							if(character.isFeminine()) {
								return returnStringAtRandom("可悲地", "可怜地", "绝望地") + "" + returnStringAtRandom("呜咽", "哭喊");
							} else {
								return returnStringAtRandom("可悲地", "可怜地", "绝望地") + "" + returnStringAtRandom("叫喊", "哭喊");
							}
							
						} else if(Main.sex.getSexPace(character)==SexPace.DOM_GENTLE) {
							if(character.isFeminine()) {
								return returnStringAtRandom("轻柔地", "和缓地", "轻声") + "" + returnStringAtRandom("呻吟", "叹息", "喘息");
							} else {
								return returnStringAtRandom("轻柔地", "和缓地", "轻声") + "" + returnStringAtRandom("呻吟", "咕哝");
							}
						}
					}
					
					if(character.isFeminine()) {
						return returnStringAtRandom("淫荡地", "无法抑制地") + "" + returnStringAtRandom("呻吟", "浪叫", "喊叫");
					} else {
						return returnStringAtRandom("急切地", "无法抑制地") + "" + returnStringAtRandom("呻吟", "咕哝", "喊叫");
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moans",
						"groans",
						"sobs",
						"cries"),
				true,
				false,
				"",
				"返回目标最可能发出的“淫叫声”变体。例如某女性化角色，则会发出呻吟(moan)，而男性化角色则是发出呻吟/哼唧(groan)。"
				+ "该方法会判断目标是否抵抗，如果目标抵抗，将返回类似于“呜咽”或“哭泣”的声音。"
				+"<b>提供“呻吟”合适的<i>名词</i>形式。</b>(使用'moansVerb'作为动词形式。)"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("呜咽", "哭喊");
						} else {
							return returnStringAtRandom("叫喊", "哭喊");
						}
					}
				}
				
				if(character.isFeminine()) {
					return returnStringAtRandom("呻吟", "浪叫", "喘息", "娇喘");
				} else {
					return returnStringAtRandom("呻吟", "咕哝");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moans+",
						"moansD",
						"groans+",
						"groansD",
						"sobs+",
						"sobsD",
						"cries+",
						"criesD"),
				true,
				false,
				"",
				"返回目标最可能发出的“淫叫声”变体。例如某女性化角色，则会发出呻吟(moan)，而男性化角色则是发出呻吟/哼唧(groan)。"
				+ "该方法会判断目标是否抵抗，如果目标抵抗，将返回类似于“呜咽”或“哭泣”的声音。"
				+ "<b>扩展“呻吟”命令：</b>该命令将在“呻吟”之前附加一个合适的描述符。如“淫荡的尖叫”或“急切的喘息”。"
				+ "<b>提供“呻吟”合适的<i>名词</i>形式。</b>(使用'moansVerb+'作为动词形式。)"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("可悲的", "可怜的", "痛苦的") + "" + returnStringAtRandom("呜咽", "哭喊");
						} else {
							return returnStringAtRandom("可悲地", "可怜地", "痛苦地") + "" + returnStringAtRandom("叫喊", "哭喊");
						}
						
					} else if(Main.sex.getSexPace(character)==SexPace.DOM_GENTLE) {
						if(character.isFeminine()) {
							return returnStringAtRandom("轻柔的", "和缓的", "轻声") + "" + returnStringAtRandom("呻吟", "叹息", "喘息");
						} else {
							return returnStringAtRandom("轻柔的", "和缓的", "轻声") + "" + returnStringAtRandom("呻吟", "咕哝");
						}
					}
				}
				
				if(character.isFeminine()) {
					return returnStringAtRandom("淫荡的", "高声", "无法抑制的") + "" + returnStringAtRandom("呻吟", "浪叫", "喘息");
				} else {
					return returnStringAtRandom("深沉的", "急切的", "无法抑制的") + "" + returnStringAtRandom("呻吟", "咕哝");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moaning",
						"groaning",
						"sobbing",
						"crying"),
				true,
				false,
				"",
				"方法描述"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("呜咽", "哭喊");
						} else {
							return returnStringAtRandom("叫喊", "抗议");
						}
					}
				}
					
				if(character.isFeminine()) {
					return returnStringAtRandom("呻吟", "浪叫");
				} else {
					return returnStringAtRandom("呻吟", "咕哝");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"moaning+",
						"moaningD",
						"groaning+",
						"groaningD",
						"sobbing+",
						"sobbingD",
						"crying+",
						"cryingD"),
				true,
				false,
				"",
				"方法描述"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					if(Main.sex.getSexPace(character)==SexPace.SUB_RESISTING) {
						if(character.isFeminine()) {
							return returnStringAtRandom("可悲地", "可怜地", "绝望地") + "" + returnStringAtRandom("呜咽", "哭喊");
						} else {
							return returnStringAtRandom("可悲地", "可怜地", "绝望地") + "" + returnStringAtRandom("叫喊", "哭喊");
						}
						
					} else if(Main.sex.getSexPace(character)==SexPace.DOM_GENTLE) {
						if(character.isFeminine()) {
							return returnStringAtRandom("轻柔地", "和缓地", "轻声") + "" + returnStringAtRandom("呻吟", "叹息");
						} else {
							return returnStringAtRandom("轻柔地", "和缓地", "轻声") + "" + returnStringAtRandom("呻吟", "咕哝");
						}
					}
				}
				
				if(character.isFeminine()) {
					return returnStringAtRandom("淫荡地", "急切地", "饥渴地") + "" + returnStringAtRandom("呻吟", "浪叫");
				} else {
					return returnStringAtRandom("淫荡地", "急切地", "饥渴地") + "" + returnStringAtRandom("呻吟", "咕哝");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"eagerly",
						"gently",
						"roughly",
						"sexPaceVerb"),
				true,
				false,
				"(可选起始字符)",
				"基于角色的状态返回适当的描述词。输入的参数将会作为SUB_NORMAL状态下的替代描述词。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(Main.game.isInSex()) {
					List<String> descriptors = new ArrayList<>();
					switch(Main.sex.getSexPace(character)) {
						case DOM_GENTLE:
							descriptors = Util.newArrayListOfValues("温柔地", "轻轻地", "含情脉脉地");
							break;
						case DOM_NORMAL:
							descriptors = Util.newArrayListOfValues("开心地", "热切地", "热情地", "迫切地");
							break;
						case DOM_ROUGH:
							descriptors = Util.newArrayListOfValues("粗鲁地", "粗暴地", "猛烈地", "支配地");
							break;
						case SUB_EAGER:
							descriptors = Util.newArrayListOfValues("开心地", "热切地", "热情地", "迫切地");
							break;
						case SUB_NORMAL:
							if(arguments!=null && !arguments.isEmpty()) {
								return Util.capitaliseSentence(arguments); // Assume start of sentence, so capitalise.
							} else if(Character.isUpperCase(command.charAt(0))) {
								descriptors = Util.newArrayListOfValues("愉快地", "急切地", "欣然"); // If start of sentence, need descriptor.
								break;
							} else {
								return "";
							}
						case SUB_RESISTING:
							descriptors = Util.newArrayListOfValues("狂乱地", "不顾一切地", "狂躁地");
							break;
					}
					
					
					for(int i=lastDescriptors.length-1; i>=0; i--) {
						descriptors.remove(lastDescriptors[i]);
						if(i>0) {
							lastDescriptors[i] = lastDescriptors[i-1];
						}
					}
					String returnString = Util.randomItemFrom(descriptors);
					
					lastDescriptors[0] = returnString;

					if(arguments!=null && !arguments.isEmpty()) {
						return returnString+arguments;
					}
					return returnString;
				}
					
				return "急切地";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"scent",
						"smell"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.GENERIC){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return returnStringAtRandom("气味", "气息", "清香");
				} else {
					return returnStringAtRandom("麝香味", "气味", "芳香");
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"scent+",
						"scentD",
						"smell+",
						"smellD"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.GENERIC){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return returnStringAtRandom("雌性气味", "雌性清香", "微妙的气息");
				} else {
					return returnStringAtRandom("雄性气味", "雄性芳香");
				}
			}
		});
		
		// Gender parsing:

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"guy"),
				true,
				true,
				"",
				"返回该角色的正确性别版本“girl”或“guy”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "女人";
				} else {
					return "男人";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"girl",
						"boy"),
				true,
				true,
				"",
				"返回该角色的正确性别版本“girl”或“boy”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return Gender.F_V_B_FEMALE.getNounYoung();
				} else {
					return Gender.M_P_MALE.getNounYoung();
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"girls",
						"boys"),
				true,
				true,
				"",
				"返回该角色的正确性别版本“girls”或“boys”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return Gender.F_V_B_FEMALE.getNounYoung();
				} else {
					return Gender.M_P_MALE.getNounYoung();
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"woman",
						"man"),
				true,
				true,
				"",
				"返回该角色的正确性别版本“woman”或“man”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine())
					return Gender.F_V_B_FEMALE.getNoun();
				else
					return Gender.M_P_MALE.getNoun();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"female",
						"male"),
				true,
				true,
				"",
				"返回该角色的正确性别版本“female”或“male”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine())
					return Gender.F_V_B_FEMALE.getName();
				else
					return Gender.M_P_MALE.getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"feminine",
						"masculine"),
				true,
				true,
				"",
				"返回该角色的正确性别版本“女性化”或“男性化”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine())
					return "充满女人味";
				else
					return "充满男人味";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"filly",
						"colt"),
				true,
				true,
				"",
				"返回该角色的正确性别版本“fillly”或“colt”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "小雌驹";
				} else {
					return "小雄驹";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mare",
						"stallion"),
				true,
				true,
				"",
				"返回该角色的正确性别版本“mare”或“stallion”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "母马";
				} else {
					return "公马";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"feminineDescriptor",
						"masculineDescriptor"),
				true,
				true,
				"(是否上色)",
				"返回描述角色女性化程度的一两个描述词。如果希望输出彩色，请在参数中传入“true”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				
				String descriptor = "";
				
				switch(character.getFemininity()) {
					case FEMININE_STRONG:
						descriptor = UtilText.returnStringAtRandom("充满女人味", "闭月羞花", "沉鱼落雁");
						break;
					case FEMININE:
						descriptor = UtilText.returnStringAtRandom("漂亮", "有女人味", "可爱");
						break;
					case ANDROGYNOUS:
						descriptor = UtilText.returnStringAtRandom("中性");
						break;
					case MASCULINE:
						descriptor = UtilText.returnStringAtRandom("有男人味", "帅气");
						break;
					case MASCULINE_STRONG:
						descriptor = UtilText.returnStringAtRandom("充满男人味", "英俊潇洒");
						break;
				}
				
				String determiner = "";
				if(parseAddPronoun) {
					parseAddPronoun = false;
					determiner = UtilText.generateSingularDeterminer(descriptor);
				}
				
				if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
					switch(character.getFemininity()) {
						case FEMININE_STRONG:
							descriptor = "[style.colourFeminineStrong("+descriptor+")]";
							break;
						case FEMININE:
							descriptor = "[style.colourFeminine("+descriptor+")]";
							break;
						case ANDROGYNOUS:
							descriptor = "[style.colourAndrogynous("+descriptor+")]";
							break;
						case MASCULINE:
							descriptor = "[style.colourMasculine("+descriptor+")]";
							break;
						case MASCULINE_STRONG:
							descriptor = "[style.colourMasculineStrong("+descriptor+")]";
							break;
					}
				}
				
				return determiner + descriptor;
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"wife",
						"husband"),
				true,
				true,
				"",
				"返回与角色性别相应的“妻子”或“丈夫”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.isFeminine()) {
					return "妻子";
				} else {
					return "丈夫";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"have",
						"has"),
				true,
				true,
				"",
				"返回与角色人称相应的“has”形式。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(isPlayer(target, character)) {
					return "";
				} else {
					return "";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"is",
						"are"),
				true,
				true,
				"",
				"返回与角色人称相应的“is”形式。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(isPlayer(target, character)) {
					return "";
				} else {
					return "";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"does",
						"do"),
				true,
				true,
				"",
				"返回与角色人称相应的“does”形式。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(isPlayer(target, character)) {
					return "";
				} else {
					return "";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"were",
						"was"),
				true,
				true,
				"",
				"返回与角色人称相应的“was”形式。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(isPlayer(target, character)) {
					return "";
				} else {
					return "";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"her",
						"his",
						"herPos",
						"herHis",
						"hisPos",
						"hisHer"),
				true,
				true,
				"(实际代词)",
				"返回此字符(your、her、his)的正确的特定于性别的所有格代词。默认情况下，为玩家角色返回“your”。"
				+ "如果需要使用实际的第三人称玩家角色代词，请将“true”作为参数传入。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments==null && isPlayer(target, character)) {
					return "你";
					
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getPossessiveBeforeNoun();
						} else {
							return GenderPronoun.POSSESSIVE_BEFORE_NOUN.getFeminine();
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getPossessiveBeforeNoun();
						} else {
							return GenderPronoun.POSSESSIVE_BEFORE_NOUN.getMasculine();
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hers",
						"hersHis",
						"hisHers"),
				true,
				true,
				"",
				"方法描述"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments==null && isPlayer(target, character)) {
					return "你";
				} else {
					if(character.isFeminine()) {
						return Gender.F_V_B_FEMALE.getPossessiveAlone();
					} else {
						return Gender.M_P_MALE.getPossessiveAlone();
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"nameHers",
						"nameHersHis",
						"nameHisHers"),
				true,
				true,
				"",
				"方法说明"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments==null && isPlayer(target, character)) {
					return "你";
				} else {
					if(character.isPlayerKnowsName()) {
						return character.getName(true);
					}
					return character.getName();
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"him",
						"herPro",
						"herHim",
						"himHer"),
				true,
				true,
				"(实际代词)",
				"返回此角色(your、her、his)的人称代词。默认情况下，为玩家角色返回“you”。"
				+ "如果需要使用常规的第三人称玩家角色代词，请将 'true' 作为参数传入。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments==null && isPlayer(target, character)) {
					return "你";
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getThirdPerson();
						} else {
							return GenderPronoun.THIRD_PERSON.getFeminine();
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getThirdPerson();
						} else {
							return GenderPronoun.THIRD_PERSON.getMasculine();
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"she",
						"sheHe",
						"he",
						"heShe"),
				true,
				true,
				"(实际代词)",
				"返回此角色(your、her、his)的人称代词。默认情况下，为玩家角色返回“you”。"
				+ "如果需要使用常规的第三人称玩家角色代词，请将 'true' 作为参数传入。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments==null && isPlayer(target, character)) {
					return "你";
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getSecondPerson();
						} else {
							return GenderPronoun.SECOND_PERSON.getFeminine();
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getSecondPerson();
						} else {
							return GenderPronoun.SECOND_PERSON.getMasculine();
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sheIs",
						"sheHeIs",
						"heIs",
						"heSheIs"),
				true,
				true,
				"(实际代词)",
				"返回此角色(your、her、his)的正确的特定于性别的所有格代词。默认情况下，为玩家角色返回“your”。"
				+ "如果需要使用常规的第三人称玩家角色代词缩略语，请将 'true' 作为参数传入。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments==null && isPlayer(target, character)) {
					return "你";
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getSecondPerson();
						} else {
							return GenderPronoun.SECOND_PERSON.getFeminine();
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getSecondPerson();
						} else {
							return GenderPronoun.SECOND_PERSON.getMasculine();
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sheIsFull",
						"sheHeIsFull",
						"heIsFull",
						"heSheIsFull"),
				true,
				true,
				"(实际代词)",
				"返回此角色正确的特定于性别的(you are, she is, he is)的人称代词缩写。默认情况下，为玩家角色返回“you are”。"
				+ "如果需要使用常规的第三人称玩家角色代词缩略语，请将 'true' 作为参数传入。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments==null && isPlayer(target, character)) {
					return "你";
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getSecondPerson();
						} else {
							return GenderPronoun.SECOND_PERSON.getFeminine();
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getSecondPerson();
						} else {
							return GenderPronoun.SECOND_PERSON.getMasculine();
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sheHas",
						"sheHeHas",
						"heHas",
						"heSheHas"),
				true,
				true,
				"(实际代词)",
				"返回此角色正确的特定于性别的(you've, she's, he's)的人称代词缩写。默认情况下，为玩家角色返回“you've”。"
				+ "如果需要使用常规的第三人称玩家角色代词缩略语，请将 'true' 作为参数传入。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments==null && isPlayer(target, character)) {
					return "你";
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getSecondPerson();
						} else {
							return GenderPronoun.SECOND_PERSON.getFeminine();
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getSecondPerson();
						} else {
							return GenderPronoun.SECOND_PERSON.getMasculine();
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sheHasFull",
						"sheHeHasFull",
						"heHasFull",
						"heSheHasFull"),
				true,
				true,
				"",
				"返回此角色正确的特定于性别的(you have, she has, he has)的人称代词缩写。默认情况下，为玩家角色返回“you have”。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(isPlayer(target, character)) {
					return "你";
				} else {
					if(character.isFeminine()) {
						if(character.isPlayer()) {
							return Gender.F_V_B_FEMALE.getSecondPerson();
						} else {
							return GenderPronoun.SECOND_PERSON.getFeminine();
						}
					} else {
						if(character.isPlayer()) {
							return Gender.M_P_MALE.getSecondPerson();
						} else {
							return GenderPronoun.SECOND_PERSON.getMasculine();
						}
					}
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"herself",
						"himself"),
				true,
				true,
				"",
				"返回此角色(yourself, herself, himself)的正确的特定于性别的反身代词。默认情况下，为玩家角色返回“yourself”。"
						+ "如果需要使用常规的玩家反身代词，请将 'true' 作为参数传入。"){
					@Override
					public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
						if(arguments==null && isPlayer(target, character)) {
							return "你自己";
						} else {
							if(character.isFeminine()) {
								if(character.isPlayer()) {
									return Gender.F_V_B_FEMALE.getThirdPerson()+"自己";
								} else {
									return GenderPronoun.THIRD_PERSON.getFeminine()+"自己";
								}
							} else {
								if(character.isPlayer()) {
									return Gender.M_P_MALE.getThirdPerson()+"自己";
								} else {
									return GenderPronoun.THIRD_PERSON.getMasculine()+"自己";
								}
							}
						}
					}
				});
		
		// Clothing:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"slotClothing"),
				true,
				true,
				"(inventorySlot, coloured)",
				"返回作为第一个参数传入的物品栏位上衣物的名称。"
						+ "可能的参数应为InventorySlot枚举值。"
						+ "第二个参数为布尔值，表示你是否希望衣物名称包含颜色描述。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					InventorySlot slot;
					String argument1 = arguments.split(",")[0].trim();
					String argument2 = arguments.split(",")[1].trim();
					try {
						slot = InventorySlot.valueOf(argument1);
					} catch(Exception ex) {
						return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Clothingslot_not_found:"+argument1+"</i>";
					}
					AbstractClothing clothingInSlot = character.getClothingInSlot(slot);
					if(clothingInSlot==null) {
						return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>no_clothing_in_slot_"+slot+"</i>";
					} else {
						try {
							boolean pronoun = parseAddPronoun;
							parseAddPronoun = false;
							if(Boolean.valueOf(argument2)) {
								return clothingInSlot.getName(pronoun);
							} else {
								return (pronoun?UtilText.generateSingularDeterminer(clothingInSlot.getName())+"":"")+clothingInSlot.getName();
							}
						} catch(Exception ex) {
							return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Clothingslot_not_found</i>";
						}
					}
					
				} else {
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Clothingslot_not_found</i>";
				}
			}
			
			@Override
			public String getArgumentExample() {
				return "GROIN, true";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"topClothing",
						"highestClothing",
						"highClothing"),
				true,
				true,
				"(bodyPart)",
				"返回遮挡了作为第一个参数传入的物品栏位的最上层衣物的名称。可能的参数应为CoverableArea枚举值。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					CoverableArea area;
					try {
						area = CoverableArea.of(arguments);
					} catch(Exception ex) {
						return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found:"+arguments+"</i>";
					}
					if(character.getHighestZLayerCoverableArea(area)==null) {
						return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_"+area+"</i>";
					} else {
						try {
							return character.getHighestZLayerCoverableArea(area).getName();
						} catch(Exception ex) {
							return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
						}
					}
					
				} else {
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
				}
			}
			
			@Override
			public String getArgumentExample() {
				return "VAGINA";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bottomClothing",
						"lowestClothing",
						"lowClothing"),
				true,
				true,
				"(bodyPart)",
				"返回遮挡了作为第一个参数传入的物品栏位的最下层衣物的名称。可能的参数应为CoverableArea枚举值。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					CoverableArea area;
					try {
						area = CoverableArea.of(arguments);
					} catch(Exception ex) {
						return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found:"+arguments+"</i>";
					}
					if(character.getLowestZLayerCoverableArea(area)==null) {
						return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>no_clothing_covering_"+area+"</i>";
					} else {
						try {
							return character.getLowestZLayerCoverableArea(area).getName();
						} catch(Exception ex) {
							return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
						}
					}
					
				} else {
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Clothing_area_not_found</i>";
				}
			}
			
			@Override
			public String getArgumentExample() {
				return "VAGINA";
			}
		});
		
		
		// Styles & non-character parsing:
		
		for(Gender gender : Gender.values()) {
			commandsList.add(new ParserCommand(
					Util.newArrayListOfValues(
							gender.getType()==PronounType.FEMININE
								?gender.getGenderName().getFeminineId()
								:(gender.getType()==PronounType.MASCULINE
									?gender.getGenderName().getMasculineId()
									:gender.getGenderName().getNeutralId())),
					true,
					true,
					"",
					"返回性别名称(基于用户设置)。"){
				@Override
				public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
					return gender.getName();
				}
			});
		}
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"evening",
						"afternoon",
						"morning"),
				true,
				true,
				"",
				"返回分别在“早晨”、“下午”、“夜晚”返回相应的结果。"){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				int hour = Main.game.getHourOfDay();
				if(hour<4) {
					return "夜晚";
				} else if(hour<12) {
					return "早晨";
				}else if(hour<17) {
					return "下午";
				}
				return "夜晚";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"bold",
						"b"),
				false,
				false,
				"(加粗文字)",
				"方法描述"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null)
					return "<b>"+arguments+"</b>";
				else
					return "<b>……</b>";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"italic",
						"italics",
						"i"),
				false,
				false,
				"(倾斜文字)",
				"方法描述"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null)
					return "<i>"+arguments+"</i>";
				else
					return "<i>……</i>";
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"glow",
						"glowing",
						"g"),
				false,
				false,
				"(使文字发光)",
				"方法描述"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null)
					return applyGlow(arguments);
				else
					return applyGlow("……");
			}
		});
		

		List<String> commandNames = new ArrayList<>();
		for(Colour c : PresetColour.getAllPresetColours()) {
			if(c.getFormattingNames()!=null) {
				
				commandNames = new ArrayList<>();
				for(String s : c.getFormattingNames()) {
					commandNames.add("color"+Util.capitaliseSentence(s));
					commandNames.add("c"+Util.capitaliseSentence(s));
					commandNames.add("colour"+Util.capitaliseSentence(s));
				}
				
				commandsList.add(new ParserCommand(
						commandNames,
						false,
						false,
						"(需要染色的文本)",
						"将文本参数格式化为彩色文本。"){
					@Override
					public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
						if(arguments!=null) {
							if(c.getRainbowColours()!=null) {
								StringBuilder sb = new StringBuilder();
								
								int i=0;
								int openBrackets = 0;
								char[] characters = arguments.toCharArray();
								for(char ch : characters) {
									if(ch=='<') {
										openBrackets++;
									}
									if(openBrackets==0) {
										sb.append("<span style='color:"+c.getRainbowColours().get(i%c.getRainbowColours().size())+";'>");
											sb.append(ch);
										sb.append("</span>");
										i++;
									} else {
										sb.append(ch);
									}
									if(ch=='>') {
										openBrackets--;
									}
								}
								
								return sb.toString();
								
							} else {
								return "<span style='color:"+c.toWebHexString()+";'>"+arguments+"</span>";
							}
						} else {
							return "<span style='color:"+c.toWebHexString()+";'>……</span>";
						}
					}
				});
				
				commandNames = new ArrayList<>();
				for(String s : c.getFormattingNames()) {
					commandNames.add("bold"+Util.capitaliseSentence(s));
					commandNames.add("b"+Util.capitaliseSentence(s));
				}
				
				commandsList.add(new ParserCommand(
						commandNames,
						false,
						false,
						"(加粗文字)",
						"将文本参数格式化为彩色加粗文本。"){
					@Override
					public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
						if(arguments!=null) {
							if(c.getRainbowColours()!=null) {
								StringBuilder sb = new StringBuilder();
								
								int i=0;
								int openBrackets = 0;
								char[] characters = arguments.toCharArray();
								for(char ch : characters) {
									if(ch=='<') {
										openBrackets++;
									}
									if(openBrackets==0) {
										sb.append("<b style='color:"+c.getRainbowColours().get(i%c.getRainbowColours().size())+";'>");
											sb.append(ch);
										sb.append("</b>");
										i++;
									} else {
										sb.append(ch);
									}
									if(ch=='>') {
										openBrackets--;
									}
								}
								
								return sb.toString();
								
							} else {
								return "<b style='color:"+c.toWebHexString()+";'>"+arguments+"</b>";
							}
						} else {
							return "<b style='color:"+c.toWebHexString()+";'>……</b>";
						}
					}
				});
				
				commandNames = new ArrayList<>();
				for(String s : c.getFormattingNames()) {
					commandNames.add("italic"+Util.capitaliseSentence(s));
					commandNames.add("italics"+Util.capitaliseSentence(s));
					commandNames.add("i"+Util.capitaliseSentence(s));
				}
				
				commandsList.add(new ParserCommand(
						commandNames,
						false,
						false,
						"(倾斜文字)",
						"将文本参数格式化为彩色斜体文本。"){
					@Override
					public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
						if(arguments!=null) {
							if(c.getRainbowColours()!=null) {
								StringBuilder sb = new StringBuilder();
								
								int i=0;
								int openBrackets = 0;
								char[] characters = arguments.toCharArray();
								for(char ch : characters) {
									if(ch=='<') {
										openBrackets++;
									}
									if(openBrackets==0) {
										sb.append("<i style='color:"+c.getRainbowColours().get(i%c.getRainbowColours().size())+";'>");
											sb.append(ch);
										sb.append("</i>");
										i++;
									} else {
										sb.append(ch);
									}
									if(ch=='>') {
										openBrackets--;
									}
								}
								
								return sb.toString();
								
							} else {
								return "<i style='color:"+c.toWebHexString()+";'>"+arguments+"</i>";
							}
						} else {
							return "<i style='color:"+c.toWebHexString()+";'>……</i>";
						}
					}
				});
				
				commandNames = new ArrayList<>();
				for(String s : c.getFormattingNames()) {
					commandNames.add("glow"+Util.capitaliseSentence(s));
					commandNames.add("glowing"+Util.capitaliseSentence(s));
					commandNames.add("g"+Util.capitaliseSentence(s));
				}
				
				commandsList.add(new ParserCommand(
						commandNames,
						false,
						false,
						"(使文字发光)",
						"方法描述"){//TODO
					@Override
					public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
						if(arguments!=null) {
							if(c.getRainbowColours()!=null) {
								StringBuilder sb = new StringBuilder();

								int i=0;
								int openBrackets = 0;
								char[] characters = arguments.toCharArray();
								for(char ch : characters) {
									if(ch=='<') {
										openBrackets++;
									}
									if(openBrackets==0) {
										Colour col = new Colour(Util.newColour(c.getRainbowColours().get(i%c.getRainbowColours().size())));
										sb.append(applyGlow(String.valueOf(ch), col));
										i++;
									} else {
										sb.append(ch);
									}
									if(ch=='>') {
										openBrackets--;
									}
								}

								return sb.toString();

							} else {
								return applyGlow(arguments, c);
							}
						} else {
							return applyGlow("……");
						}
					}
				});
			}
		}


		// Units
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"currentTime"),
				true,
				false,
				"",
				"返回时间，以小时和分钟为单位。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.time(Main.game.getDateNow());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"time"),
				true,
				false,
				"(小时换算)",
				"将换算后的小时数转为具体时间，小数位对应于小时的分钟数(即 6.5 会被解析为 06:30)。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (arguments == null || arguments.isEmpty()) {
					return "NaN";
				}
				double time = Double.valueOf(arguments);
				LocalDateTime now = Main.game.getDateNow();
				return Units.time(LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), (int) time, Math.min(59, (int)((time%1)*60))));
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"fluid"),
				true,
				false,
				"(毫升体积换算)",
				"返回转换后的体积，以较小的单位，单数形式。如果没有给出参数，返回较小的单位。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (arguments == null || arguments.isEmpty()) {
					return Main.getProperties().hasValue(PropertyValue.metricSizes) ? "mL" : "oz";
				}
				return Units.fluid(Double.valueOf(arguments), Units.ValueType.NUMERIC, Units.UnitType.SHORT);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"size"),
				true,
				false,
				"(厘米长度单位换算)",
				"返回转换后的长度，以本地化的单数形式。" +
						"如果没有给出参数，则返回较短的长度单位。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (arguments == null || arguments.isEmpty()) {
					return Main.getProperties().hasValue(PropertyValue.metricSizes) ? "厘米" : "英寸";
				}
				return Units.size(Double.valueOf(arguments), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sizes",
						"sizePlural"),
				true,
				false,
				"(厘米长度单位换算)",
				"返回转换后的长度，以本地化的形式。" +
						"如果没有给出参数，返回较短的复数长度单位。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (arguments == null || arguments.isEmpty()) {
					return Main.getProperties().hasValue(PropertyValue.metricSizes) ? "厘米" : "英寸";
				}
				return Units.size(Double.valueOf(arguments), Units.ValueType.NUMERIC, Units.UnitType.LONG);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"sizeShort"),
				true,
				false,
				"(厘米长度单位换算)",
				"返回转换后的长度，以本地化的单数形式。" +
						"如果没有给出参数，则返回较短的长度单位。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (arguments == null || arguments.isEmpty()) {
					return Main.getProperties().hasValue(PropertyValue.metricSizes) ? "cm" : Units.INCH_SYMBOL;
				}
				return Units.size(Double.valueOf(arguments), Units.ValueType.NUMERIC, Units.UnitType.SHORT);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lSize",
						"largeSize"),
				true,
				false,
				"(厘米长度单位换算)",
				"返回转换后的长度，以本地化的单数形式。" +
						"如果没有给出参数，返回较长的长度单位。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (arguments == null || arguments.isEmpty()) {
					return Main.getProperties().hasValue(PropertyValue.metricSizes) ? "米" : "英尺";
				}
				return Units.size(Double.valueOf(arguments), Units.ValueType.TEXT, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lSizes",
						"largeSizePlural"),
				true,
				false,
				"(厘米长度单位换算)",
				"返回转化后的长度，以本地化的文本形式。" +
						"如果没有给出参数，返回较长的复数长度单位。") {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if (arguments == null || arguments.isEmpty()) {
					return Main.getProperties().hasValue(PropertyValue.metricSizes) ? "米" : "英尺";
				}
				return Units.size(Double.valueOf(arguments), Units.ValueType.TEXT, Units.UnitType.LONG);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mainWeapon",
						"primaryWeapon",
						"mainWeapon1",
						"primaryWeapon1"),
				true,
				true,
				"",
				"返回该角色第一对手臂上装备的主手武器的名称。如果没有武器则返回“拳头”。"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getMainWeapon(0)==null) {
					return "拳头";
				} else {
					return character.getMainWeapon(0).getName();
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mainWeapon2",
						"primaryWeapon2"),
				true,
				true,
				"",
				"返回该角色第二对手臂上装备的主手武器的名称(仅限拥有超过一对手臂的角色)。如果没有武器则返回“拳头”。"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getMainWeapon(1)==null) {
					return "拳头";
				} else {
					return character.getMainWeapon(1).getName();
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"mainWeapon3",
						"primaryWeapon3"),
				true,
				true,
				"",
				"返回该角色第三对手臂上装备的主手武器的名称(仅限拥有超过两对手臂的角色)。如果没有武器则返回“拳头”。"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getMainWeapon(2)==null) {
					return "拳头";
				} else {
					return character.getMainWeapon(2).getName();
				}
			}
		});

		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"offhandWeapon",
						"secondaryWeapon",
						"offhandWeapon0",
						"secondaryWeapon0"),
				true,
				true,
				"",
				"返回该角色第一对手臂上装备的副手武器的名称。如果没有武器则返回“拳头”。"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getOffhandWeapon(0)==null) {
					return "拳头";
				} else {
					return character.getOffhandWeapon(0).getName();
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"offhandWeapon2",
						"secondaryWeapon2"),
				true,
				true,
				"",
				"返回该角色第二对手臂上装备的副手武器的名称(仅限拥有超过一对手臂的角色)。如果没有武器则返回“拳头”。"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getOffhandWeapon(1)==null) {
					return "拳头";
				} else {
					return character.getOffhandWeapon(1).getName();
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"offhandWeapon3",
						"secondaryWeapon3"),
				true,
				true,
				"",
				"返回该角色第三对手臂上装备的副手武器的名称(仅限拥有超过两对手臂的角色)。如果没有武器则返回“拳头”。"){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getOffhandWeapon(2)==null) {
					return "拳头";
				} else {
					return character.getOffhandWeapon(2).getName();
				}
			}
		});
		
		
		
		
		
		// Body parts:
		
		// Add standard parsing for all types:

		addStandardParsingCommands(
				Util.newArrayListOfValues("antenna"),
				Util.newArrayListOfValues("antennae"),
				BodyPartType.ANTENNA);
				
		addStandardParsingCommands(
				Util.newArrayListOfValues("arm"),
				Util.newArrayListOfValues("arms"),
				BodyPartType.ARM);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("ass", "butt"),
				Util.newArrayListOfValues("asses", "butts"),
				BodyPartType.ASS);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("anus", "asshole"),
				Util.newArrayListOfValues("anuses", "assholes"),
				BodyPartType.ANUS);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("breast", "tit", "boob", "chest"),
				Util.newArrayListOfValues("breasts", "tits", "boobs"),
				BodyPartType.BREAST);

		addStandardParsingCommands(
				Util.newArrayListOfValues("nipple", "teat"),
				Util.newArrayListOfValues("nipples", "teats"),
				BodyPartType.NIPPLES);

		addStandardParsingCommands(
				Util.newArrayListOfValues("milk"),
				Util.newArrayListOfValues("milks"), // milks? Really?
				BodyPartType.MILK);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("breastCrotch", "titCrotch", "boobCrotch", "crotchBreast", "crotchTit", "crotchBoob", "udder"),
				Util.newArrayListOfValues("breastsCrotch", "titsCrotch", "boobsCrotch", "crotchBreasts", "crotchTits", "crotchBoobs", "udders"),
				BodyPartType.BREAST_CROTCH);

		addStandardParsingCommands(
				Util.newArrayListOfValues("nippleCrotch", "teatCrotch", "crotchNipple", "crotchTeat", "udderTeat", "uddersTeat"),
				Util.newArrayListOfValues("nipplesCrotch", "teatsCrotch", "crotchNipples", "crotchTeats", "udderTeats", "uddersTeats"),
				BodyPartType.NIPPLES_CROTCH);

		addStandardParsingCommands(
				Util.newArrayListOfValues("milkCrotch", "crotchMilk", "udderMilk", "uddersMilk"),
				Util.newArrayListOfValues("milksCrotch", "crotchMilks", "udderMilks", "uddersMilks"), // milks? Really?
				BodyPartType.MILK_CROTCH);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("ear"),
				Util.newArrayListOfValues("ears"),
				BodyPartType.EAR);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("eye"),
				Util.newArrayListOfValues("eyes"),
				BodyPartType.EYE);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("face"),
				Util.newArrayListOfValues("faces"),
				BodyPartType.FACE);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("mouth"),
				Util.newArrayListOfValues("mouths"),
				BodyPartType.MOUTH);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("hair", "feather"),
				Util.newArrayListOfValues("hairs", "feathers"),
				BodyPartType.HAIR);

		addStandardParsingCommands(
				Util.newArrayListOfValues("horn"),
				Util.newArrayListOfValues("horns"),
				BodyPartType.HORN);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("leg"),
				Util.newArrayListOfValues("legs"),
				BodyPartType.LEG);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("penis", "cock", "dick"),
				Util.newArrayListOfValues("penises", "cocks", "dicks"),
				BodyPartType.PENIS);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("testicle", "ball"),
				Util.newArrayListOfValues("testicles", "balls"),
				BodyPartType.TESTICLES);

		addStandardParsingCommands(
				Util.newArrayListOfValues("cum"),
				Util.newArrayListOfValues("cums"), // :s
				BodyPartType.CUM);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("skin"), // Will usually return the plural anyway...
				Util.newArrayListOfValues("skinPlural"),
				BodyPartType.SKIN);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("tail"),
				Util.newArrayListOfValues("tails"),
				BodyPartType.TAIL);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("tentacle"),
				Util.newArrayListOfValues("tentacles"),
				BodyPartType.TENTACLE);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("tongue"),
				Util.newArrayListOfValues("tongues"),
				BodyPartType.TONGUE);

		addStandardParsingCommands(
				Util.newArrayListOfValues("clit", "clitoris"),
				Util.newArrayListOfValues("clits", "clitorises"),
				BodyPartType.CLIT);
		
		addStandardParsingCommands(
				Util.newArrayListOfValues("vagina", "pussy", "cunt"),
				Util.newArrayListOfValues("vaginas", "pussies", "cunts"),
				BodyPartType.VAGINA);

		addStandardParsingCommands(
				Util.newArrayListOfValues("girlcum", "gcum"),
				Util.newArrayListOfValues("girlcums", "gcums"),
				BodyPartType.GIRL_CUM);

		addStandardParsingCommands(
				Util.newArrayListOfValues("wing"),
				Util.newArrayListOfValues("wings"),
				BodyPartType.WING);
		
		
		// Special body parts:
		
		// Antennae:

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"antennaSize",
						"antennaLength",
						"antennaeSize",
						"antennaeLength"),
				true,
				true,
				"",
				"返回该角色触须长度的描述词。",
				BodyPartType.ANTENNA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return HornLength.getLengthFromInt(character.getAntennaLength()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"antennaRows",
						"antennaeRows"),
				true,
				true,
				"",
				"返回该角色拥有的触须行数，以汉字的形式(即“一”、“二”而不是“1”、“2”。)",
				BodyPartType.ANTENNA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getAntennaRows());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"antennaPerRow",
						"antennaePerRow"),
				true,
				true,
				"",
				"返回该角色每行触须的数量，以汉字的形式(即“一”、“二”而不是“1”、“2”。)",
				BodyPartType.ANTENNA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getAntennaePerRow());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"antennaDeterminer",
						"antennaeDeterminer"),
				true,
				true,
				"",
				"返回该角色拥有触须所用的限定词。形式为“一对”等。",
				BodyPartType.ANTENNA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAntennaDeterminer();
			}
		});
		
		
		// Arm:

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"armpit",
						"underarm"),
				true,
				true,
				"",
				"返回该角色腋窝的名称，基本总是“腋窝”或“腋下”。",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return UtilText.returnStringAtRandom("腋窝", "腋下");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"armpit+",
						"armpitD",
						"underarm+",
						"underarmD"),
				true,
				true,
				"",
				"返回该角色腋窝的名称，前缀有描述词，基于腋窝的毛发量、皮毛量和女性化程度。",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				List<String> descriptors = new ArrayList<>();
				if(character.getCovering(character.getArmCovering()).getModifier().isFurryModifier()) {
					descriptors.add("毛茸茸");
				} else {
					if(Main.game.isBodyHairEnabled() && character.getUnderarmHair()!=BodyHair.ZERO_NONE) {
						descriptors.add("多毛");
					} else {
						descriptors.add("光滑");
					}
				}
				if(character.isFeminine()) {
					descriptors.add("女性化");
					descriptors.add("性感");
					descriptors.add("色情");
				} else {
					descriptors.add("男性化");
				}
				if(character.getMuscleValue()>=Muscle.THREE_MUSCULAR.getMinimumValue()) {
					descriptors.add("肌肉发达");
				} else if(character.getMuscleValue()<Muscle.ONE_LIGHTLY_MUSCLED.getMinimumValue() && character.getBodySizeValue()>=BodySize.THREE_LARGE.getMinimumValue()){
					descriptors.add("丰满");
				}
				return applyDescriptor(
						Util.randomItemFrom(descriptors),
						UtilText.returnStringAtRandom("腋窝", "腋下"));
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"armpits",
						"underarms"),
				true,
				true,
				"",
				"返回该角色腋窝的名称，基本总是“腋窝”或“腋下”。",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return UtilText.returnStringAtRandom("腋窝", "腋下");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"armpits+",
						"armpitsD",
						"underarms+",
						"underarmsD"),
				true,
				true,
				"",
				"返回该角色腋窝的名称，前缀有描述词，基于腋窝的毛发量、皮毛量和女性化程度。",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				List<String> descriptors = new ArrayList<>();
				if(character.getCovering(character.getArmCovering()).getModifier().isFurryModifier()) {
					descriptors.add("毛茸茸");
				} else {
					if(character.getUnderarmHair()!=BodyHair.ZERO_NONE) {
						descriptors.add("多毛");
					} else {
						descriptors.add("光滑");
					}
				}
				if(character.isFeminine()) {
					descriptors.add("女性化");
					descriptors.add("性感");
				} else {
					descriptors.add("男性化");
				}
				return applyDescriptor(
						Util.randomItemFrom(descriptors),
						UtilText.returnStringAtRandom("腋窝", "腋下"));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"armRows"),
				true,
				false,
				"",
				"返回对应的角色手臂数目的描述词。也就是说：如果该角色拥有一对手臂，则返回“一对”，两对则返回“两对”，三对则返回“三对”。",
				BodyPartType.ARM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getArmRows()==1) {
					return "一对";
				} else if(character.getArmRows()==2) {
					return "两对";
				} else {
					return "三对";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"handCount"),
				true,
				false,
				"",
				"返回该角色手掌的数量，通常为“二”，“四”或“六”。(有些兽态身体会是“无”！)",
				BodyPartType.ARM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getArmRows() * 2);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hand"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getArmType().getHandsNameSingular(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hand+",
						"handD"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getArmType().getHandsDescriptor(character), character.getArmType().getHandsNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hands"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getArmType().getHandsNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hands+",
						"handsD"),
				true,
				false,
				"",
				"方法描述",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getArmType().getHandsDescriptor(character), character.getArmType().getHandsNamePlural(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"finger"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getArmType().getFingersNameSingular(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"finger+",
						"fingerD"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getArmType().getFingersDescriptor(character), character.getArmType().getFingersNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"fingers"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getArmType().getFingersNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"fingers+",
						"fingersD"),
				true,
				false,
				"",
				"方法描述",
				BodyPartType.ARM){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getArmType().getFingersDescriptor(character), character.getArmType().getFingersNamePlural(character));
			}
		});
		
	
		// Ass:
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assSize"),
				true,
				true,
				"",
				"返回角色屁股尺寸的描述词。",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAssSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assCapacity",
						"assholeCapacity"),
				true,
				true,
				"",
				"返回角色肛门容量的描述词。",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getAssStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assDepth",
						"assholeDepth"),
				true,
				true,
				"",
				"返回角色肛门深度的描述词。",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAssDepth().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assElasticity",
						"assholeElasticity"),
				true,
				true,
				"",
				"返回角色肛门弹性的描述词。",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAssElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assPlasticity",
						"assholePlasticity"),
				true,
				true,
				"",
				"返回角色肛门可塑性的描述词。",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAssPlasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assWetness",
						"assholeWetness"),
				true,
				true,
				"",
				"返回角色肛门湿润度的描述词。",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAssWetness().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assCloaca"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getGenitalArrangement()==GenitalArrangement.CLOACA
						|| character.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
					return "泄殖腔";
				} else {
					return character.getBody().getAss().getNameSingular(character);
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"assCloaca+"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.ASS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getGenitalArrangement()==GenitalArrangement.CLOACA
						|| character.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
					return "狭缝状泄殖腔";
				} else {
					return applyDescriptor(character.getBody().getAss().getDescriptor(character), character.getBody().getAss().getNameSingular(character));
				}
			}
		});


		
		// Hips:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hipSkin",
						"hipsSkin"),
				true,
				true,
				"",
				"返回该角色臀部表层的名称(基于躯干类型)。",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return getSkinName(character.getAssType(), character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hipSkin+",
						"hipsSkin+"),
				true,
				true,
				"",
				"返回该角色臀部表层的名称(基于躯干类型)，前缀描述词。",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return getSkinNameWithDescriptor(character.getAssType(), character.getCovering(character.getAssType().getBodyCoveringType(character)), character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hip",
						"hips"),
				true,
				true,
				"",
				"返回该角色臀部的名称。",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "臀部";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hip+",
						"hipD",
						"hips+",
						"hipsD"),
				true,
				true,
				"",
				"返回该角色臀部的名称，前缀尺寸描述词。",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getHipSize().getDescriptor(), "臀部");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hipSize",
						"hipsSize"),
				true,
				true,
				"",
				"返回该角色臀部尺寸的描述词。",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHipSize().getDescriptor();
			}
		});
		
		// Breasts:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"paizuri",
						"naizuri"),
				true,
				true,
				"",
				"根据该角色的乳房尺寸，返回“乳交”或者“乳头摩擦”。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.isBreastFuckablePaizuri()?"乳交":"乳头摩擦";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastSize",
						"breastsSize",
						"titSize",
						"titsSize",
						"boobSize",
						"boobsSize"),
				true,
				true,
				"",
				"返回该角色乳房尺寸的描述词。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastShape",
						"breastsShape",
						"titShape",
						"titsShape",
						"boobShape",
						"boobsShape"),
				true,
				true,
				"",
				"返回该角色乳房形状的名称。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastShape().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"nippleSize",
						"nipplesSize"),
				true,
				true,
				"",
				"返回该角色乳头尺寸的名称。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleSize().getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"areolaSize",
						"areolaeSize"),
				true,
				true,
				"",
				"返回该角色乳晕尺寸的名称。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAreolaeSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"cupSize",
						"cups",
						"breastCups",
						"breastsCups",
						"titCups",
						"titsCups",
						"boobCups",
						"boobsCups"),
				true,
				true,
				"",
				"返回该角色罩杯的名称。(如“AA”、“B”、“DD”)",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastSize().getCupSizeName();
			}
		});
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastCapacity",
						"breastsCapacity",
						"titCapacity",
						"titsCapacity",
						"boobCapacity",
						"boobsCapacity"),
				true,
				true,
				"",
				"返回该角色乳头容量的描述词。",
				BodyPartType.ASS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getNippleStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastDepth",
						"breastsDepth",
						"titDepth",
						"titsDepth",
						"boobDepth",
						"boobsDepth"),
				true,
				true,
				"",
				"返回该角色乳头深度的描述词。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleDepth().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastElasticity",
						"breastsElasticity",
						"titElasticity",
						"titsElasticity",
						"boobElasticity",
						"boobsElasticity"),
				true,
				true,
				"",
				"返回该角色乳头弹性的描述词。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastPlasticity",
						"breastsPlasticity",
						"titPlasticity",
						"titsPlasticity",
						"boobPlasticity",
						"boobsPlasticity"),
				true,
				true,
				"",
				"返回该角色乳头可塑性的描述词。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNipplePlasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastRows",
						"nippleRows"),
				true,
				true,
				"",
				"返回该角色乳房的行数，格式化为汉字+“对”。(如“一对”、“三对”)",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getBreastRows()==1) {
					return "一对";
				} else {
					return Util.intToString(character.getBreastRows())+"对";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"totalBreasts",
						"totalBreastCount"),
				true,
				false,
				"",
				"返回该角色拥有的乳房总数。即乳房对数*2",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getBreastRows()*2);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"nipplesPerBreast"),
				true,
				false,
				"",
				"返回该角色每个乳房上的乳头数量。通常为一。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getNippleCountPerBreast());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"totalNipples",
						"totalNippleCount"),
				true,
				false,
				"",
				"返回该角色拥有的乳头总数。即乳房对数*2*每个乳房的乳头数",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getBreastRows()*2*character.getNippleCountPerBreast());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lactation"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastMilkStorage().getDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"milkRegen",
						"milkRegeneration"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastLactationRegeneration().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"milkFlavour",
						"flavourMilk"),
				false,
				false,
				"",
				"返回该角色乳汁口味的名称。",
				BodyPartType.MILK){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getMilk().getFlavour().getName();
			}
		});
		
		
		// Crotch Breasts:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"breastCrotchPair",
						"crotchBoobPair",
						"crotchBoobsPair",
						"udderSet"),
				true,
				true,
				"",
				"返回“对”或者“套”，基于该角色是胯乳还是腹乳",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastCrotchShape()==BreastShape.UDDERS?"套":"对";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderSize",
						"uddersSize",
						"crotchBreastSize",
						"crotchBreastsSize",
						"crotchTitSize",
						"crotchTitsSize",
						"crotchBoobSize",
						"crotchBoobsSize"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastCrotchSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderShape",
						"uddersShape",
						"crotchBreastShape",
						"crotchBreastsShape",
						"crotchTitShape",
						"crotchTitsShape",
						"crotchBoobShape",
						"crotchBoobsShape"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastCrotchShape().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderNippleSize",
						"udderNipplesSize",
						"uddersNippleSize",
						"uddersNipplesSize",
						"crotchNippleSize",
						"crotchNipplesSize"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleCrotchSize().getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderAreolaSize",
						"udderAreolaeSize",
						"uddersAreolaSize",
						"uddersAreolaeSize",
						"crotchBoobsAreolaSize",
						"crotchAreolaSize",
						"crotchAreolaeSize"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getAreolaeCrotchSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderCups",
						"udderCups",
						"uddersCups",
						"uddersCups",
						"crotchBreastCups",
						"crotchBreastsCups",
						"crotchTitCups",
						"crotchTitsCups",
						"crotchBoobCups",
						"crotchBoobsCups",
						"crotchBoobCupSize",
						"crotchBoobsCupSize"),
				true,
				true,
				"",
				"返回该角色胯乳罩杯的名称。(如“AA”、“B”、“DD”)",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastCrotchSize().getCupSizeName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderCapacity",
						"uddersCapacity",
						"crotchBreastCapacity",
						"crotchBreastsCapacity",
						"crotchTitCapacity",
						"crotchTitsCapacity",
						"crotchBoobCapacity",
						"crotchBoobsCapacity"),
				true,
				true,
				"",
				"返回该角色胯乳乳头容量的描述词。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getNippleCrotchStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderDepth",
						"uddersDepth",
						"crotchBreastDepth",
						"crotchBreastsDepth",
						"crotchTitDepth",
						"crotchTitsDepth",
						"crotchBoobDepth",
						"crotchBoobsDepth"),
				true,
				true,
				"",
				"返回该角色胯乳乳头深度的描述词。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleCrotchDepth().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderElasticity",
						"uddersElasticity",
						"crotchBreastElasticity",
						"crotchBreastsElasticity",
						"crotchTitElasticity",
						"crotchTitsElasticity",
						"crotchBoobElasticity",
						"crotchBoobsElasticity"),
				true,
				true,
				"",
				"返回该角色胯乳乳头弹性的描述词。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleCrotchElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderPlasticity",
						"uddersPlasticity",
						"crotchBreastPlasticity",
						"crotchBreastsPlasticity",
						"crotchTitPlasticity",
						"crotchTitsPlasticity",
						"crotchBoobPlasticity",
						"crotchBoobsPlasticity"),
				true,
				true,
				"",
				"返回该角色胯乳乳头可塑性的描述词。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNippleCrotchPlasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderRows",
						"uddersRows",
						"crotchBoobRows",
						"crotchBoobsRows",
						"crotchBreastRows",
						"crotchNippleRows"),
				true,
				true,
				"",
				"返回该角色胯乳的行数，格式化为汉字+“对”。(如“一对”、“三对”)",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getBreastCrotchRows()==0) {
					return "单个";
				} else if(character.getBreastCrotchRows()==1) {
					return "一对";
				} else {
					return Util.intToString(character.getBreastCrotchRows())+"对";
				}
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"totalUdders",
						"totalUdderCount",
						"totalCrotchBoobs",
						"totalCrotchBoobCount"),
				true,
				false,
				"",
				"返回该角色拥有的乳房总数。即乳房对数*2",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getBreastCrotchRows()*2);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderNipplesPerBreast",
						"uddersNipplesPerBreast",
						"crotchBoobNipplesPerBreast",
						"crotchBoobsNipplesPerBreast",
						"crotchNipplesPerBreast"),
				true,
				false,
				"",
				"返回该角色每个乳房上的乳头数量。通常为一。",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getNippleCrotchCountPerBreast());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"totalUdderNipples",
						"totalUddersNipples",
						"totalCrotchNipples",
						"totalCrotchNippleCount"),
				true,
				false,
				"",
				"返回该角色拥有的乳头总数。即乳房对数*2*每个乳房的乳头数",
				BodyPartType.BREAST){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getBreastCrotchRows()*2*character.getNippleCrotchCountPerBreast());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderLactation",
						"uddersLactation",
						"crotchLactation"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastCrotchMilkStorage().getDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderMilkRegen",
						"uddersMilkRegen",
						"crotchMilkRegen",
						"crotchMilkRegeneration"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.BREAST){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBreastCrotchLactationRegeneration().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"udderMilkFlavour",
						"flavourUdderMilk",
						"crotchMilkFlavour",
						"flavourCrotchMilk"),
				false,
				false,
				"",
				"返回该角色乳汁口味的名称。",
				BodyPartType.MILK){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getMilkCrotch().getFlavour().getName();
			}
		});
		
		
		// Eyes:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"eyePairs",
						"eyesPairs",
						"eyeRows",
						"eyesRows"),
				true,
				false,
				"",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getEyeDeterminer();
			}
		});
		
		// Face:

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"faceCapacity",
						"throatCapacity"),
				true,
				true,
				"",
				"返回角色喉咙容量的描述词。",
				BodyPartType.FACE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getFaceStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"faceDepth",
						"throatDepth"),
				true,
				true,
				"",
				"返回该角色喉咙深度的描述词。",
				BodyPartType.FACE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getFaceDepth().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"faceElasticity",
						"throatElasticity"),
				true,
				true,
				"",
				"返回角色喉咙弹性的描述词。",
				BodyPartType.FACE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getFaceElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"facePlasticity",
						"throatPlasticity"),
				true,
				true,
				"",
				"返回角色喉咙可塑性的描述词。",
				BodyPartType.FACE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getFacePlasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"faceWetness",
						"throatWetness"),
				true,
				true,
				"",
				"返回角色喉咙湿润度的描述词。",
				BodyPartType.FACE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getFaceWetness().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tongueLength",
						"tongueSize"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getTongueLength().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"nose"),
				true,
				false,
				"",
				"方法描述",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNoseNameSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"noses"),
				true,
				false,
				"",
				"方法描述",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getNoseNamePlural();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lipSize",
						"lipsSize"),
				true,
				false,
				"",
				"方法描述",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLipSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lip"),
				true,
				false,
				"",
				"方法描述",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLipsNameSingular();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lip+",
						"lipD"),
				true,
				false,
				"",
				"方法描述",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getLipsDescriptor(), character.getLipsNameSingular());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lips"),
				true,
				false,
				"",
				"方法描述",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLipsNamePlural();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"lips+",
						"lipsD"),
				true,
				false,
				"",
				"方法描述",
				BodyPartType.FACE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getLipsDescriptor(), character.getLipsNamePlural());
			}
		});
		
		// Hair:

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hairDeterminer"),
				true,
				false,
				"",
				"角色头发类型的限定词，通常是“一头”",
				BodyPartType.HAIR){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHairType().getDeterminer(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hairLength"),
				true,
				true,
				"",
				"返回该角色头发长度的描述词",
				BodyPartType.HAIR){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHairLength().getDescriptor();
			}
		});
		
		// Horns:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hornSize",
						"hornsSize",
						"hornLength",
						"hornsLength"),
				true,
				true,
				"",
				"返回该角色角长度的描述词",
				BodyPartType.HORN){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return HornLength.getLengthFromInt(character.getHornLengthValue()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hornRows"),
				true,
				true,
				"",
				"返回该角色拥有的角行数，以汉字的形式(即“一”、“二”而不是“1”、“2”。)",
				BodyPartType.HORN){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getHornRows());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hornsPerRow"),
				true,
				true,
				"",
				"返回该角色每行角的数量，以汉字的形式(即“一”、“二”而不是“1”、“2”。)",
				BodyPartType.HORN){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getHornsPerRow());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"hornsDeterminer"),
				true,
				true,
				"",
				"返回该角色拥有角所用的限定词。形式为“一对”等。",
				BodyPartType.HORN){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getHornDeterminer();
			}
		});
		
		
		// Leg:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"thighs"),
				true,
				true,
				"",
				"返回该角色股间的名称，基本总是“股间”。",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "大腿";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"thighs+",
						"thighsD"),
				true,
				true,
				"",
				"返回该角色股间的名称，前缀有描述词，基于臀部尺寸。",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getHipSize().getDescriptor(), "大腿");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"foot"),
				true,
				true,
				"(忽略衣物)",
				"该角色脚部的单数名称。"
					+ "如果角色正在装备严格限制到脚部的服装，并且在性爱过程中该指令已完成，将返回角色脚部装备的名称。"
					+ "传入值为真时忽略此性交行为，以确保该指令将永远返回单数的角色脚部名称",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				boolean trueName = arguments!=null && arguments.equalsIgnoreCase("true");
				if(parserTags.contains(ParserTag.SEX_DESCRIPTION) && !trueName) {
					if(!character.isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
						try {
							AbstractClothing clothing = character.getHighestZLayerCoverableArea(CoverableArea.FEET);
							return UtilText.returnStringAtRandom(
									clothing.getSlotEquippedTo()==InventorySlot.FOOT
										?"鞋子"
										:"穿着"+clothing.getName()+"的"+character.getLegType().getFootNameSingular(character),
									clothing.getName(),
									clothing.getName());
						} catch(Exception ex) {
						}
					}
				}
				return character.getLegType().getFootNameSingular(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"foot+",
						"footD"),
				true,
				true,
				"(忽略衣物)",
				"该角色脚部的单数名称，前缀描述词。"
					+ "如果角色正在装备严格限制到脚部的服装，并且在性爱过程中该指令已完成，将返回角色脚部装备的名称。"
					+ "传入值为真时忽略此性交行为，以确保该指令将永远返回单数的角色脚部名称",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				boolean trueName = arguments!=null && arguments.equalsIgnoreCase("true");
				if(parserTags.contains(ParserTag.SEX_DESCRIPTION) && !trueName) {
					if(!character.isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
						try {
							AbstractClothing clothing = character.getHighestZLayerCoverableArea(CoverableArea.FEET);
							return UtilText.returnStringAtRandom(
									clothing.getSlotEquippedTo()==InventorySlot.FOOT
									?"鞋子"
									:"穿着"+clothing.getName()+"的"+character.getLegType().getFootNameSingular(character),
									clothing.getName(),
									clothing.getName());
						} catch(Exception ex) {
						}
					}
				}
				return applyDescriptor(character.getLegType().getFootDescriptor(character), character.getLegType().getFootNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"feet"),
				true,
				true,
				"(忽略衣物)",
				"角色脚部的复数名称。"
					+ "如果角色正在装备严格限制到双脚的服装，并且在性爱过程中该指令已完成，将返回角色脚部装备的名称。"
					+ "传入值为真时忽略此性交行为，以确保该指令将永远返回复数的角色脚部名称",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				boolean trueName = arguments!=null && arguments.equalsIgnoreCase("true");
				if(parserTags.contains(ParserTag.SEX_DESCRIPTION) && !trueName) {
					if(!character.isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
						try {
							AbstractClothing clothing = character.getHighestZLayerCoverableArea(CoverableArea.FEET);
							return UtilText.returnStringAtRandom(
									clothing.getSlotEquippedTo()==InventorySlot.FOOT
										?"鞋子"
										:"穿着"+clothing.getName()+"的"+character.getLegType().getFootNamePlural(character),
									clothing.getNamePlural(),
									clothing.getNamePlural());
						} catch(Exception ex) {
						}
					}
				}
				return character.getLegType().getFootNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"feet+",
						"feetD"),
				true,
				false,
				"(忽略衣物)",
				"该角色脚部的复数名称，前缀描述词。"
					+ "如果角色正在装备严格限制到双脚的服装，并且在性爱过程中该指令已完成，将返回角色脚部装备的名称。"
					+ "传入值为真时忽略此性交行为，以确保该指令将永远返回复数的角色脚部名称",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				boolean trueName = arguments!=null && arguments.equalsIgnoreCase("true");
				if(parserTags.contains(ParserTag.SEX_DESCRIPTION) && !trueName) {
					if(!character.isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
						try {
							AbstractClothing clothing = character.getHighestZLayerCoverableArea(CoverableArea.FEET);
							return UtilText.returnStringAtRandom(
									clothing.getSlotEquippedTo()==InventorySlot.FOOT
										?"鞋子"
										:"穿着"+clothing.getName()+"的"+character.getLegType().getFootNamePlural(character),
									clothing.getNamePlural(),
									clothing.getNamePlural());
						} catch(Exception ex) {
						}
					}
				}
				return applyDescriptor(character.getLegType().getFootDescriptor(character), character.getLegType().getFootNamePlural(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toes"),
				true,
				true,
				"(忽略衣物)",
				"该角色脚趾的复数名称。"
					+ "如果角色正在装备严格限制到双脚的服装，并且在性爱过程中该指令已完成，将返回角色脚部装备的名称。"
					+ "传入值为真时忽略此性交行为，以确保该指令将永远返回复数的角色指头名称",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				boolean trueName = arguments!=null && arguments.equalsIgnoreCase("true");
				if(parserTags.contains(ParserTag.SEX_DESCRIPTION) && !trueName) {
					if(!character.isAbleToAccessCoverableArea(CoverableArea.FEET, false)) { // If not able to access feet, return foot-related clothing description, not toes:
						try {
							AbstractClothing clothing = character.getHighestZLayerCoverableArea(CoverableArea.FEET);
							return UtilText.returnStringAtRandom(
									clothing.getSlotEquippedTo()==InventorySlot.FOOT
										?"鞋子"
										:"穿着"+clothing.getName()+"的"+character.getLegType().getFootNamePlural(character),
									clothing.getNamePlural(),
									clothing.getNamePlural());
						} catch(Exception ex) {
						}
					}
				}
				return character.getLegType().getToeNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toes+",
						"toesD"),
				true,
				false,
				"(忽略衣物)",
				"该角色脚趾的复数名称，加上前面的描述词。"
					+ "如果角色正在装备严格限制到双脚的服装，并且在性爱过程中该指令已完成，将返回角色脚部装备的名称。"
					+ "传入值为真时忽略此性交行为，以确保该指令将永远返回复数的角色指头名称",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				boolean trueName = arguments!=null && arguments.equalsIgnoreCase("true");
				if(parserTags.contains(ParserTag.SEX_DESCRIPTION) && !trueName) {
					if(!character.isAbleToAccessCoverableArea(CoverableArea.FEET, false)) { // If not able to access feet, return foot-related clothing description, not toes:
						try {
							AbstractClothing clothing = character.getHighestZLayerCoverableArea(CoverableArea.FEET);
							return UtilText.returnStringAtRandom(
									clothing.getSlotEquippedTo()==InventorySlot.FOOT
										?"鞋子"
										:"穿着"+clothing.getName()+"的"+character.getLegType().getFootNamePlural(character),
									clothing.getNamePlural(),
									clothing.getNamePlural());
						} catch(Exception ex) {
						}
					}
				}
				return applyDescriptor(character.getLegType().getToeDescriptor(character), character.getLegType().getToeNamePlural(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"footjob"),
				true,
				true,
				"",
				"返回该角色“足交”动作的名称，包括足交、爪交、蹄交等。",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegType().getFootType().getFootjobName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"footStructure"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.LEG){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getFootStructure().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"spreadsHerLegs",
						"spreadsHisLegs",
						"spreadYourLegs"),
				true,
				true,
				"",
				"如果角色有腿，则返回“张开她/他的双腿”；否则返回“翘起她/他的臀部”。",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.hasLegs()) {
					return UtilText.parse(character, "张开[npc.her]的[npc.legs]");
				} else {
					return UtilText.parse(character, "抬起[npc.her]的[npc.hips]");
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"spreadingHerLegs",
						"spreadingHisLegs"),
				true,
				true,
				"",
				"如果角色有腿，则返回“张开她/他的双腿”；否则返回“翘起她/他的臀部”。",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.hasLegs()) {
					return UtilText.parse(character, "张开[npc.her]的[npc.legs]");
				} else {
					return UtilText.parse(character, "抬起[npc.her]的[npc.hips]");
				}
			}
		});
		
		
		// Penis:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"urethra",
						"penisUrethra",
						"cockUrethra",
						"urethraPenis",
						"urethraCock"),
				true,
				true,
				"",
				"返回角色尿道的名称。",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "尿道";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisUrethra+",
						"cockUrethra+",
						"urethraPenis+",
						"urethraCock+",
						"penisUrethraD",
						"cockUrethraD",
						"urethraPenisD",
						"urethraCockD"),
				true,
				true,
				"",
				"返回角色尿道前缀描述的名称。",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getPenisUrethraDescriptor(), "尿道");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisUrethraCapacity",
						"cockUrethraCapacity",
						"urethraPenisCapacity",
						"urethraCockCapacity"),
				true,
				true,
				"",
				"返回角色阴茎尿道容量的描述词。",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getPenisStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisUrethraDepth",
						"cockUrethraDepth",
						"urethraPenisDepth",
						"urethraCockDepth"),
				true,
				true,
				"",
				"返回该角色阴茎尿道深度的描述词。",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getUrethraDepth().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisUrethraElasticity",
						"cockUrethraElasticity",
						"urethraPenisElasticity",
						"urethraCockElasticity"),
				true,
				true,
				"",
				"返回角色阴茎尿道弹性的描述词。",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getUrethraElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisUrethraPlasticity",
						"cockUrethraPlasticity",
						"urethraPenisPlasticity",
						"urethraCockPlasticity"),
				true,
				true,
				"",
				"返回角色阴茎尿道可塑性的描述词。",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getUrethraPlasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"cumAmount",
						"cumProduction",
						"jizzAmount",
						"jizzProduction",
						"cumStorage"),
				true,
				true,
				"",
				"返回角色蛋蛋满溢时精液量的描述词。",
				BodyPartType.CUM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getPenisCumStorage().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"cumMl",
						"cumMeasurement"),
				false,
				false,
				"",
				"返回角色蛋蛋满溢时精液量的数值。",
				BodyPartType.CUM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.fluid(character.getPenisRawCumStorageValue());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"cumFlavour",
						"flavourCum"),
				false,
				false,
				"",
				"返回该角色精液口味的名称。",
				BodyPartType.CUM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCum().getFlavour().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"precum"),
				false,
				false,
				"",
				"返回角色先走液的名称。",
				BodyPartType.CUM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "先走液";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"precum+",
						"precumD"),
				false,
				false,
				"",
				"返回角色先走液前缀描述的名称。",
				BodyPartType.CUM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(!character.getCumModifiers().isEmpty()) {
					return character.getCumModifiers().get(Util.random.nextInt(character.getCumModifiers().size())).getName() + "先走液";
				}
				
				return "先走液";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"ballsCount",
						"ballCount",
						"testiclesCount"),
				true,
				false,
				"",
				"方法描述",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return (parseCapitalise
						?Util.capitaliseSentence(Util.intToString(character.getPenisNumberOfTesticles()))
						:Util.intToString(character.getPenisNumberOfTesticles()));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"ballSize",
						"ballsSize",
						"testicleSize",
						"testiclesSize"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.TESTICLES){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getTesticleSize().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisHead",
						"cockHead",
						"dickHead",
						"cockTip"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBody().getPenis().getPenisHeadName(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisHead+",
						"penisHeadD",
						"cockHead+",
						"cockHeadD",
						"dickHead+",
						"dickHeadD",
						"cockTip+",
						"cockTipD"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getBody().getPenis().getPenisHeadDescriptor(character), character.getBody().getPenis().getPenisHeadName(character));
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"cockDescriptive",
						"penisDescriptive",
						"dickDescriptive"),
				true,
				true,
				"(是否上色)",
				"返回角色阴茎的描述词，格式：‘size’，‘girth’，‘colour’ ‘feralRace’的肉棒。"
				+ "<br/>如果‘size’或‘girth’是平均尺寸，则省略描述词。"
				+ "<br/>如果希望输出彩色，请输入“true”。",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				StringBuilder sb = new StringBuilder();
				boolean coloured = arguments!=null && arguments.equalsIgnoreCase("true");
				
				// Length:
				if(character.getPenisSize()!=PenisLength.TWO_AVERAGE) {
					if(coloured) {
						sb.append("<span style='color:"+character.getPenisSize().getColour().toWebHexString()+";'>");
					}
					sb.append(character.getPenisSize().getDescriptor());
					if(coloured) {
						sb.append("</span>");
					}
				}

				// Girth:
				if(character.getPenisGirth()!=PenetrationGirth.THREE_AVERAGE) {
					if(sb.length()>0) {
						sb.append("，");	
					}
					if(coloured) {
						sb.append("<span style='color:"+character.getPenisGirth().getColour().toWebHexString()+";'>");
					}
					sb.append(character.getPenisGirth().getName());
					if(coloured) {
						sb.append("</span>");
					}
				}

				// Colour:
				if(sb.length()>0) {
					sb.append("，");
				}
				sb.append(character.getCovering(character.getPenisCovering()).getColourDescriptor(character, coloured, false));
				
				// Race:
				sb.append("");
				if(coloured) {
					sb.append("<span style='color:"+character.getPenisRace().getColour().toWebHexString()+";'>");
				}
				if(character.getPenisRace()!=Race.HUMAN) {
					sb.append(character.getPenisRace().getName(true)+"肉棒");
					
				} else {
					sb.append("人类肉棒");
				}
				if(coloured) {
					sb.append("</span>");
				}
				return sb.toString();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisSize",
						"cockSize",
						"dickSize",
						"penisLength",
						"cockLength",
						"dickLength"),
				true,
				true,
				"",
				"返回该角色阴茎长度的描述词。",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getPenisSize().getDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisCircumference",
						"cockCircumference",
						"dickCircumference"),
				true,
				true,
				"(复数单位)",
				"返回角色阴茎的周长，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getPenisCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getPenisCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisDiameter",
						"cockDiameter",
						"dickDiameter",
						"penisDiametre",
						"cockDiametre",
						"dickDiametre"),
				true,
				true,
				"(复数单位)",
				"返回角色阴茎的直径，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。",
				BodyPartType.PENIS){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getPenisDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getPenisDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"penisGirth",
						"cockGirth",
						"dickGirth"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.PENIS){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getPenisGirth().getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"cockValue",
						"cockLengthValue",
						"penisValue",
						"penisLengthValue"),
				false,
				false,
				"(short)",
				"返回本地对象，格式化阴茎长度，当长度为单数时(“厘米”)。传入值为真以返回一个单位缩写('cm')。",
				BodyPartType.PENIS) {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && arguments.equalsIgnoreCase("true")) {
					return Units.size(character.getPenisRawSizeValue(), Units.ValueType.NUMERIC, Units.UnitType.SHORT);
				}
				return Units.size(character.getPenisRawSizeValue(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"cockValues",
						"cockLengthValues",
						"penisValues",
						"penisLengthValues"),
				false,
				false,
				"(short)",
				"返回本地对象，格式化阴茎长度，当长度为复数时(“厘米”)。传入值为真以返回一个单位缩写('cm')。",
				BodyPartType.PENIS) {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && arguments.equalsIgnoreCase("true")) {
					return Units.size(character.getPenisRawSizeValue(), Units.ValueType.NUMERIC, Units.UnitType.SHORT);
				}
				return Units.size(character.getPenisRawSizeValue(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
			}
		});
		
		// Tentacle:

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tentacleHead",
						"tentacleTip"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getTentacleType().getTentacleTipNameSingular(character);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tentacleHeads",
						"tentacleTips"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getTentacleType().getTentacleTipNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tentacleHead+",
						"tentacleHeadD",
						"tentacleTip+",
						"tentacleTipD"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getTentacleType().getTentacleTipDescriptor(character), character.getTentacleType().getTentacleTipNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tentacleHeads+",
						"tentacleHeadsD",
						"tentacleTips+",
						"tentacleTipsD"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.TAIL){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getTentacleType().getTentacleTipDescriptor(character), character.getTentacleType().getTentacleTipNamePlural(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tentacleCount",
						"tentaclesCount"),
				true,
				false,
				"",
				"方法描述",
				BodyPartType.TENTACLE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getTentacleDeterminer();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tentacleGirth",
						"tentaclesGirth"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.TENTACLE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getTentacleGirthDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tentacleBaseCircumference"),
				true,
				true,
				"(复数单位)",
				"返回返回从底部测量的角色触手的周长，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。",
				BodyPartType.TENTACLE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getTentacleBaseCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getTentacleBaseCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tentacleTipCircumference"),
				true,
				true,
				"(复数单位)",
				"返回返回从顶端测量的角色触手的周长，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。",
				BodyPartType.TENTACLE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getTentacleCircumference(character.getTentacleLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getTentacleCircumference(character.getTentacleLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tentacleBaseDiameter",
						"tentacleBaseDiametre"),
				true,
				true,
				"(复数单位)",
				"返回返回从底部测量的角色触手的直径，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。",
				BodyPartType.TENTACLE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getTentacleBaseDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getTentacleBaseDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tentacleTipDiameter",
						"tentacleTipDiametre"),
				true,
				true,
				"(复数单位)",
				"返回返回从顶端测量的角色触手的直径，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。",
				BodyPartType.TENTACLE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getTentacleDiameter(character.getTentacleLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getTentacleDiameter(character.getTentacleLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tentacleLength",
						"tentacleSize"),
				true,
				true,
				"(复数单位)",
				"返回角色触手的长度，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。",
				BodyPartType.TENTACLE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getTentacleLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getTentacleLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tentaclePenetrationLength",
						"tentaclePenetrationSize"),
				true,
				true,
				"(复数单位)",
				"返回角色触手在用于穿透的长度(总长的80%)，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。",
				BodyPartType.TENTACLE){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getTentacleLength(true), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getTentacleLength(true), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});
		
		// Tail:

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailHead",
						"tailTip"),
				true,
				true,
				"",
				"返回角色尾巴尖的名称。"
					+ "<br/><b>提示：</b>如果角色拥有'"+LegConfiguration.TAIL_LONG.getName()+"'腿部配置，将返回与其尾巴相关联的值。",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
					return "尾巴尖";
				}
				return character.getTailType().getTailTipNameSingular(character);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailHeads",
						"tailTips"),
				true,
				true,
				"",
				"返回角色尾巴尖的复数名称。"
					+ "<br/><b>提示：</b>如果角色拥有'"+LegConfiguration.TAIL_LONG.getName()+"'腿部配置，将返回与其尾巴相关联的值。",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
					return "尾巴尖";
				}
				return character.getTailType().getTailTipNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailHead+",
						"tailHeadD",
						"tailTip+",
						"tailTipD"),
				true,
				true,
				"",
				"返回角色尾巴尖含前缀描述词的名称。"
					+ "<br/><b>提示：</b>如果角色拥有'"+LegConfiguration.TAIL_LONG.getName()+"'腿部配置，将返回与其尾巴相关联的值。",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
					return "尖尖的尾巴尖";
				}
				return applyDescriptor(character.getTailType().getTailTipDescriptor(character), character.getTailType().getTailTipNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailHeads+",
						"tailHeadsD",
						"tailTips+",
						"tailTipsD"),
				true,
				true,
				"",
				"返回角色尾巴尖复数的含前缀修饰词的名称。"
					+ "<br/><b>提示：</b>如果角色拥有'"+LegConfiguration.TAIL_LONG.getName()+"'腿部配置，将返回与其尾巴相关联的值。",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
					return "尖尖的尾巴尖";
				}
				return applyDescriptor(character.getTailType().getTailTipDescriptor(character), character.getTailType().getTailTipNamePlural(character));
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailCount",
						"tailsCount"),
				true,
				false,
				"",
				"返回角色拥有的尾巴数量。",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getTailCount());
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailMaxCount",
						"tailsMaxCount"),
				true,
				false,
				"",
				"返回角色可拥有的最大 (妖狐) 尾巴数量。",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getMaxTailCount());
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailGirth",
						"tailsGirth"),
				true,
				true,
				"",
				"返回角色尾巴周长的描述词。"
					+ "<br/><b>提示：</b>如果角色拥有'"+LegConfiguration.TAIL_LONG.getName()+"'腿部配置，将返回与其尾巴相关联的值。",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
					return character.getLegTailGirthDescriptor();
				}
				return character.getTailGirthDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailBaseCircumference"),
				true,
				true,
				"(复数单位)",
				"返回返回从底部测量的角色尾巴的周长，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。"
						+ "<br/><b>提示：</b>如果角色拥有'"+LegConfiguration.TAIL_LONG.getName()+"'腿部配置，将返回与其尾巴相关联的值。",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
					if(arguments!=null) {
						if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
							return Units.size(character.getLegTailBaseCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
						}
					}
					return Units.size(character.getLegTailBaseCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
					
				} else {
					if(arguments!=null) {
						if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
							return Units.size(character.getTailBaseCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
						}
					}
					return Units.size(character.getTailBaseCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailTipCircumference"),
				true,
				true,
				"(复数单位)",
				"返回返回从顶端测量的角色尾巴的周长，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。"
						+ "<br/><b>提示：</b>如果角色拥有'"+LegConfiguration.TAIL_LONG.getName()+"'腿部配置，将返回与其尾巴相关联的值。",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
					if(arguments!=null) {
						if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
							return Units.size(character.getLegTailCircumference(character.getLegTailLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG);
						}
					}
					return Units.size(character.getLegTailCircumference(character.getLegTailLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
					
				} else {
					if(arguments!=null) {
						if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
							return Units.size(character.getTailCircumference(character.getTailLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG);
						}
					}
					return Units.size(character.getTailCircumference(character.getTailLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailBaseDiameter",
						"tailBaseDiametre"),
				true,
				true,
				"(复数单位)",
				"返回返回从底部测量的角色尾巴的直径，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。"
						+ "<br/><b>提示：</b>如果角色拥有'"+LegConfiguration.TAIL_LONG.getName()+"'腿部配置，将返回与其尾巴相关联的值。",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
					if(arguments!=null) {
						if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
							return Units.size(character.getLegTailBaseDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
						}
					}
					return Units.size(character.getLegTailBaseDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
					
				} else {
					if(arguments!=null) {
						if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
							return Units.size(character.getTailBaseDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
						}
					}
					return Units.size(character.getTailBaseDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailTipDiameter",
						"tailTipDiametre"),
				true,
				true,
				"(复数单位)",
				"返回返回从顶端测量的角色尾巴的周长，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。"
						+ "<br/><b>提示：</b>如果角色拥有'"+LegConfiguration.TAIL_LONG.getName()+"'腿部配置，将返回与其尾巴相关联的值。",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
					if(arguments!=null) {
						if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
							return Units.size(character.getLegTailDiameter(character.getLegTailLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG);
						}
					}
					return Units.size(character.getLegTailDiameter(character.getLegTailLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
					
				} else {
					if(arguments!=null) {
						if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
							return Units.size(character.getTailDiameter(character.getTailLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG);
						}
					}
					return Units.size(character.getTailDiameter(character.getTailLength(false)), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailLength",
						"tailSize"),
				true,
				true,
				"(复数单位)",
				"返回角色尾巴的长度，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。"
						+ "<br/><b>提示：</b>如果角色拥有'"+LegConfiguration.TAIL_LONG.getName()+"'腿部配置，将返回与其尾巴相关联的值。",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
					if(arguments!=null) {
						if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
							return Units.size(character.getLegTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG);
						}
					}
					return Units.size(character.getLegTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
					
				} else {
					if(arguments!=null) {
						if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
							return Units.size(character.getTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG);
						}
					}
					return Units.size(character.getTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
				}
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"tailPenetrationLength",
						"tailPenetrationSize"),
				true,
				true,
				"(复数单位)",
				"返回角色尾巴在用于穿透的长度(总长的80%)，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。"
						+ "<br/><b>提示：</b>如果角色拥有'"+LegConfiguration.TAIL_LONG.getName()+"'腿部配置，将返回与其尾巴相关联的值。",
				BodyPartType.TAIL){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
					if(arguments!=null) {
						if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
							return Units.size(character.getLegTailLength(true), Units.ValueType.NUMERIC, Units.UnitType.LONG);
						}
					}
					return Units.size(character.getLegTailLength(true), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
							
				} else {
					if(arguments!=null) {
						if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
							return Units.size(character.getTailLength(true), Units.ValueType.NUMERIC, Units.UnitType.LONG);
						}
					}
					return Units.size(character.getTailLength(true), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
				}
			}
		});
		
		// Vagina:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pussyUrethra",
						"vaginaUrethra",
						"vaginalUrethra",
						"urethraVagina",
						"urethraVaginal"),
				true,
				true,
				"",
				"返回角色阴道尿道的名称。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "尿道";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pussyUrethra+",
						"vaginaUrethra+",
						"vaginalUrethra+",
						"urethraVagina+",
						"urethraVaginal+",
						"vaginaUrethraD",
						"pussyUrethraD",
						"vaginalUrethraD",
						"urethraVaginaD",
						"urethraVaginalD"),
				true,
				true,
				"",
				"返回角色阴道尿道前缀描述的名称。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getVaginaUrethraDescriptor(), "尿道");
			}
		});
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pussyUrethraCapacity",
						"vaginaUrethraCapacity",
						"urethraPussyCapacity",
						"urethraVaginaCapacity"),
				true,
				true,
				"",
				"返回角色阴道尿道容量的描述词。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getVaginaUrethraStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pussyUrethraDepth",
						"vaginaUrethraDepth",
						"urethraPussyDepth",
						"urethraVaginaDepth"),
				true,
				true,
				"",
				"返回该角色阴道尿道深度的描述词。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaUrethraDepth().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pussyUrethraElasticity",
						"vaginaUrethraElasticity",
						"urethraPussyElasticity",
						"urethraVaginaElasticity"),
				true,
				true,
				"",
				"返回角色阴道尿道弹性的描述词。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaUrethraElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pussyUrethraPlasticity",
						"vaginaUrethraPlasticity",
						"urethraPussyPlasticity",
						"urethraVaginaPlasticity"),
				true,
				true,
				"",
				"返回角色阴道尿道可塑性的描述词。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaUrethraPlasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"vaginaCapacity",
						"pussyCapacity",
						"cuntCapacity"),
				true,
				true,
				"",
				"返回角色阴道容量的描述词。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getVaginaStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"vaginaDepth",
						"pussyDepth",
						"cuntDepth"),
				true,
				true,
				"",
				"返回该角色阴道深度的描述词。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaDepth().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"vaginaElasticity",
						"pussyElasticity",
						"cuntElasticity"),
				true,
				true,
				"",
				"返回角色阴道弹性的描述词。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"vaginaPlasticity",
						"pussyPlasticity",
						"cuntPlasticity"),
				true,
				true,
				"",
				"返回角色阴道可塑性的描述词。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaPlasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"vaginaWetness",
						"pussyWetness",
						"cuntWetness"),
				true,
				true,
				"",
				"返回角色阴道湿润度的描述词。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaWetness().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"girlcumFlavour",
						"flavourGirlcum"),
				false,
				false,
				"",
				"返回该角色爱液口味的名称。",
				BodyPartType.GIRL_CUM){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getGirlcum().getFlavour().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"labiaSize"),
				true,
				true,
				"",
				"返回该角色阴唇尺寸的描述词。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaLabiaSize().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"labia"),
				true,
				true,
				"",
				"返回角色阴唇的名称。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "阴唇";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"labia+",
						"labiaD"),
				true,
				true,
				"",
				"返回角色阴唇前缀描述的名称。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				List<String> descriptors = Util.newArrayListOfValues(character.getVaginaLabiaSize().getName());
				
				if(character.getVaginaCovering()!=null) {
					descriptors.add(character.getVaginaLabiaSize().getName());
					descriptors.add(character.getCovering(character.getVaginaCovering()).getPrimaryColour().getName());
				}
				
				return Util.randomItemFrom(descriptors)+"的阴唇";
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"clitCircumference",
						"clitorisCircumference"),
				true,
				true,
				"(复数单位)",
				"返回角色阴蒂的周长，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。",
				BodyPartType.CLIT){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getClitorisCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getClitorisCircumference(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"clitDiameter",
						"clitorisDiameter",
						"clitDiametre",
						"clitorisDiametre"),
				true,
				true,
				"(复数单位)",
				"返回角色阴蒂的直径，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。",
				BodyPartType.CLIT){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null) {
					if(arguments.equals(" ") || arguments.equalsIgnoreCase("true")) {
						return Units.size(character.getClitorisDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
					}
				}
				return Units.size(character.getClitorisDiameter(), Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"clitSize",
						"clitorisSize"),
				true,
				true,
				"",
				"返回该角色阴蒂尺寸的描述词。",
				BodyPartType.CLIT){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getVaginaClitorisSize().getDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"clitSizeValue",
						"clitorisSizeValue"),
				true,
				true,
				"",
				"返回本地对象，格式化阴蒂具有长单位的尺寸。",
				BodyPartType.CLIT){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Units.size(character.getVaginaRawClitorisSizeValue(), Units.ValueType.NUMERIC, Units.UnitType.LONG);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"clitGirth",
						"clitorisGirth"),
				true,
				true,
				"",
				"返回角色的阴蒂周长的描述词。",
				BodyPartType.CLIT){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getClitorisGirth().getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"clitHead",
						"clitTip"),
				true,
				true,
				"",
				"返回角色阴蒂尖的名称。",
				BodyPartType.CLIT){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBody().getVagina().getClitoris().getClitTipNameSingular(character);
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"clitHeads",
						"clitTips"),
				true,
				true,
				"",
				"返回角色阴蒂尖的复数名称。",
				BodyPartType.CLIT){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getBody().getVagina().getClitoris().getClitTipNamePlural(character);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"clitHead+",
						"clitHeadD",
						"clitTip+",
						"clitTipD"),
				true,
				true,
				"",
				"返回角色阴蒂尖端前缀描述的名称。",
				BodyPartType.CLIT){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getBody().getVagina().getClitoris().getClitTipDescriptor(character), character.getBody().getVagina().getClitoris().getClitTipNameSingular(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"clitHeads+",
						"clitHeadsD",
						"clitTips+",
						"clitTipsD"),
				true,
				true,
				"",
				"返回角色阴蒂尖端前缀描述的复数名称。",
				BodyPartType.CLIT){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return applyDescriptor(character.getBody().getVagina().getClitoris().getClitTipDescriptor(character), character.getBody().getVagina().getClitoris().getClitTipNamePlural(character));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVagina",
						"toyPussy"),
				true,
				true,
				"",
				"返回角色插入阴道的玩具的名字。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return "玩具";
				}
				return toy.getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVagina+",
						"toyVaginaD",
						"toyPussy+",
						"toyPussyD"),
				true,
				true,
				"",
				"返回插入该角色阴道玩具的名称，带有描述词(描述玩具的颜色、厚度或长度)。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return "玩具";
				}
				AbstractClothingType toyType = toy.getClothingType();
				PenisLength length = PenisLength.getPenisLengthFromInt(toyType.getPenetrationSelfLength());
				PenetrationGirth girth = PenetrationGirth.getGirthFromInt(toyType.getPenetrationSelfGirth());
				return applyDescriptor(UtilText.returnStringAtRandom(length.getDescriptor(), girth.getName(), toy.getColourName()), toy.getName());
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaHead",
						"toyVaginaTip",
						"toyPussyHead",
						"toyPussyTip"),
				true,
				true,
				"",
				"返回角色插入阴道的玩具尖端的名字。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return UtilText.returnStringAtRandom("头部", "尖端");
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaHead+",
						"toyVaginaTip+",
						"toyPussyHead+",
						"toyPussyTip+"),
				true,
				true,
				"",
				"返回插入该角色阴道玩具尖端的名称，带有描述词(描述玩具的颜色)。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("头部", "尖端");
				}
				String descriptor = toy.getColour(0).getName();
				return applyDescriptor(descriptor, UtilText.returnStringAtRandom("头部", "尖端"));
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaDescriptive",
						"toyPussyDescriptive"),
				true,
				true,
				"(是否上色)",
				"返回角色插入阴道玩具的描述词，格式：‘size’，‘girth’，‘name’。"
				+ "<br/>如果‘size’或‘girth’是平均尺寸，则省略描述词。"
				+ "<br/>如果希望输出彩色，请输入“true”。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				StringBuilder sb = new StringBuilder();
				boolean coloured = arguments!=null && arguments.equalsIgnoreCase("true");
				
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("玩具");
				}
				AbstractClothingType toyType = toy.getClothingType();
				
				// Length:
				PenisLength length = PenisLength.getPenisLengthFromInt(toyType.getPenetrationSelfLength());
				if(character.getPenisSize()!=PenisLength.TWO_AVERAGE) {
					if(coloured) {
						sb.append("<span style='color:"+length.getColour().toWebHexString()+";'>");
					}
					sb.append(length.getDescriptor());
					if(coloured) {
						sb.append("</span>");
					}
				}

				// Girth:
				PenetrationGirth girth = PenetrationGirth.getGirthFromInt(toyType.getPenetrationSelfGirth());
				if(character.getPenisGirth()!=PenetrationGirth.THREE_AVERAGE) {
					if(sb.length()>0) {
						sb.append("，");	
					}
					if(coloured) {
						sb.append("<span style='color:"+girth.getColour().toWebHexString()+";'>");
					}
					sb.append(girth.getName());
					if(coloured) {
						sb.append("</span>");
					}
				}

				// Colour:
				if(sb.length()>0) {
					sb.append("，");
				}
				sb.append(toy.getDisplayName(coloured, false));
				
				return sb.toString();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaSize",
						"toyPussySize",
						"toyVaginaLength",
						"toyPussyLength"),
				true,
				true,
				"",
				"返回角色插入阴道玩具的长度，以用户设置中定义的公制或英制单位表示。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("不存在");
				}
				PenisLength length = PenisLength.getPenisLengthFromInt(toy.getClothingType().getPenetrationSelfLength());
				return length.getDescriptor();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaCircumference",
						"toyPussyCircumference"),
				true,
				true,
				"(复数单位)",
				"返回角色插入阴道玩具的周长，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("N/A");
				}
				float circumference = (float) (Penis.getGenericDiameter(toy.getClothingType().getPenetrationSelfLength(), PenetrationGirth.getGirthFromInt(toy.getClothingType().getPenetrationSelfGirth())) * Math.PI);
				if(arguments!=null && arguments.equalsIgnoreCase("true")) {
					return Units.size(circumference, Units.ValueType.NUMERIC, Units.UnitType.LONG);
				}
				return Units.size(circumference, Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaDiameter",
						"toyPussyDiameter",
						"toyVaginaDiametre",
						"toyPussyDiametre"),
				true,
				true,
				"(复数单位)",
				"返回角色插入阴道玩具的直径，以用户设置中定义的公制或英制单位表示。"
						+ "如果希望输出复数，请在参数传入“true”。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("N/A");
				}
				float diameter = Penis.getGenericDiameter(toy.getClothingType().getPenetrationSelfLength(), PenetrationGirth.getGirthFromInt(toy.getClothingType().getPenetrationSelfGirth()));
				if(arguments!=null &&  arguments.equalsIgnoreCase("true")) {
					return Units.size(diameter, Units.ValueType.NUMERIC, Units.UnitType.LONG);
				}
				return Units.size(diameter, Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaGirth",
						"toyPussyGirth"),
				true,
				true,
				"",
				"返回角色插入阴道的玩具周长的描述词。",
				BodyPartType.VAGINA){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("N/A");
				}
				return PenetrationGirth.getGirthFromInt(toy.getClothingType().getPenetrationSelfGirth()).getName();
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"toyVaginaValue",
						"toyVaginaLengthValue",
						"toyPussyValue",
						"toyPussyLengthValue"),
				false,
				false,
				"(short)",
				"返回本地对象，格式化自慰棒插入该角色阴道的长度，当长度为长单数时(“厘米”)。传入值为真以返回一个单位缩写('cm')。",
				BodyPartType.VAGINA) {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				AbstractClothing toy = ToyVagina.getToyInVagina(character);
				if(toy==null) {
					return UtilText.returnStringAtRandom("N/A");
				}
				int length = toy.getClothingType().getPenetrationSelfLength();
				if(arguments!=null && arguments.equalsIgnoreCase("true")) {
					return Units.size(length, Units.ValueType.NUMERIC, Units.UnitType.SHORT);
				}
				return Units.size(length, Units.ValueType.NUMERIC, Units.UnitType.LONG_SINGULAR);
			}
		});
		
		
		// Spinneret:

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"spinneret"),
				true,
				true,
				"",
				"返回角色丝囊的名称(总是返回“丝囊”……)",
				BodyPartType.SPINNERET){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return "丝囊";
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"spinneret+",
						"spinneretD"),
				true,
				true,
				"",
				"返回角色丝囊的名称+描述词。(返回描述词+‘丝囊’)",
				BodyPartType.SPINNERET){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getSpinneretDescriptor() + "丝囊";
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"spinneretCapacity"),
				true,
				true,
				"",
				"返回角色丝囊容量的描述词。",
				BodyPartType.SPINNERET){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Capacity.getCapacityFromValue(character.getSpinneretStretchedCapacity()).getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"spinneretDepth"),
				true,
				true,
				"",
				"返回该角色丝囊深度的描述词。",
				BodyPartType.SPINNERET){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getSpinneretDepth().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"spinneretElasticity"),
				true,
				true,
				"",
				"返回角色丝囊弹性的描述词。",
				BodyPartType.SPINNERET){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getSpinneretElasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"spinneretPlasticity"),
				true,
				true,
				"",
				"返回角色丝囊可塑性的描述词。",
				BodyPartType.SPINNERET){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getSpinneretPlasticity().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"spinneretWetness"),
				true,
				true,
				"",
				"返回角色丝囊湿润度的描述词。",
				BodyPartType.SPINNERET){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getSpinneretWetness().getDescriptor();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"spinneretFullDescription"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"返回BodyCoveringType.SPINNERET的Covering.getFullDescription()方法。",
				BodyPartType.SPINNERET) {
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.SPINNERET)
						.getFullDescription(character, arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"spinneretColor",
						"spinneretColour"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"返回BodyCoveringType.SPINNERET的Covering.getColourDescriptor()方法。",
				BodyPartType.SPINNERET){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.SPINNERET)
						.getColourDescriptor(character, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"spinneretColourPrimary",
						"spinneretColorPrimary",
						"spinneretPrimaryColour",
						"spinneretPrimaryColor"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"返回BodyCoveringType.SPINNERET的Covering.getPrimaryColourDescriptor()方法。",
				BodyPartType.SPINNERET){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.SPINNERET)
						.getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"spinneretColourSecondary",
						"spinneretColorSecondary",
						"spinneretSecondaryColour",
						"spinneretSecondaryColor"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"返回BodyCoveringType.SPINNERET的Covering.getSecondaryColourDescriptor()方法。",
				BodyPartType.SPINNERET){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.SPINNERET)
						.getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		
		// Wings:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"wingSize",
						"wingsSize"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.WING){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getWingSize().getName();
			}
		});
		
		// Legs:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"legConfiguration",
						"legShape"),
				true,
				true,
				"",
				"返回角色腿部配置的名称。",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getLegConfiguration().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"legCount"),
				true,
				false,
				"",
				"返回角色拥有的腿的数量。",
				BodyPartType.LEG){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return Util.intToString(character.getLegCount());
			}
		});
		
		// Eyes:
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"irisShape",
						"irisesShape"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getIrisShape().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"irisFullDescription",
						"irisesFullDescription"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(character.getEyeCovering())
						.getFullDescription(character, arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"irisColour",
						"irisColor",
						"irisesColour",
						"irisesColor"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(character.getEyeCovering())
						.getColourDescriptor(character, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"irisColourPrimary",
						"irisColorPrimary",
						"irisesColourPrimary",
						"irisesColorPrimary",
						"irisPrimaryColour",
						"irisPrimaryColor",
						"irisesPrimaryColour",
						"irisesPrimaryColor"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(character.getEyeCovering())
						.getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"irisColourSecondary",
						"irisColorSecondary",
						"irisesColourSecondary",
						"irisesColorSecondary",
						"irisSecondaryColour",
						"irisSecondaryColor",
						"irisesSecondaryColour",
						"irisesSecondaryColor"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(character.getEyeCovering())
						.getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pupilShape",
						"pupilsShape"),
				true,
				true,
				"",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getPupilShape().getName();
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pupilFullDescription",
						"pupilsFullDescription"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_PUPILS)
						.getFullDescription(character, arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pupilColour",
						"pupilColor",
						"pupilsColour",
						"pupilsColor"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_PUPILS)
						.getColourDescriptor(character, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pupilColourPrimary",
						"pupilColorPrimary",
						"pupilsColourPrimary",
						"pupilsColorPrimary",
						"pupilPrimaryColour",
						"pupilPrimaryColor",
						"pupilsPrimaryColour",
						"pupilsPrimaryColor"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_PUPILS)
						.getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"pupilColourSecondary",
						"pupilColorSecondary",
						"pupilsColourSecondary",
						"pupilsColorSecondary",
						"pupilSecondaryColour",
						"pupilSecondaryColor",
						"pupilsSecondaryColour",
						"pupilsSecondaryColor"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_PUPILS)
						.getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"scleraFullDescription",
						"scleraeFullDescription"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_SCLERA)
						.getFullDescription(character, arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"scleraColour",
						"scleraColor",
						"scleraeColour",
						"scleraeColor"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_SCLERA)
						.getColourDescriptor(character, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"scleraColourPrimary",
						"scleraColorPrimary",
						"scleraeColourPrimary",
						"scleraeColorPrimary",
						"scleraPrimaryColour",
						"scleraPrimaryColor",
						"scleraePrimaryColour",
						"scleraePrimaryColor"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_SCLERA)
						.getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});

		commandsList.add(new ParserCommand(
				Util.newArrayListOfValues(
						"scleraColourSecondary",
						"scleraColorSecondary",
						"scleraeColourSecondary",
						"scleraeColorSecondary",
						"scleraSecondaryColour",
						"scleraSecondaryColor",
						"scleraeSecondaryColour",
						"scleraeSecondaryColor"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"方法描述",
				BodyPartType.EYE){//TODO
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return character.getCovering(BodyCoveringType.EYE_SCLERA)
						.getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
			}
		});
		
		
		commandsList.sort(Comparator.nullsLast(Comparator.comparing(ParserCommand::getRelatedBodyPart)));
		
		for(BodyPartType bpt : BodyPartType.values()) {
			commandsMap.put(bpt, new ArrayList<>());
		}
		for(ParserCommand cmd : commandsList) {
			commandsMap.get(cmd.getRelatedBodyPart()).add(cmd);
		}
	}

	private static String parseSyntaxNew(List<GameCharacter> specialNPCs, String target, String command, String arguments, ParseMode currentParseMode) {
		return parseSyntaxNew(specialNPCs, null, target, command, arguments, currentParseMode);
	}
	
	private static String parseSyntaxNew(List<GameCharacter> specialNPCs, AbstractCoreItem specialItem, String target, String command, String arguments, ParseMode currentParseMode) {
		GameCharacter character;
		
		if(currentParseMode == ParseMode.REGULAR_SCRIPT) {
			if(engine==null) {
				initScriptEngine();
			}
			if(!specialNPCs.isEmpty()) {
				for(int i = 0; i<specialNPCs.size(); i++) {
					if(i==0) {
						engine.put("npc", specialNPCs.get(i));
					}
					engine.put("npc"+(i+1), specialNPCs.get(i));
				}
			} else {
				try { // Getting the target NPC can throw a NullPointerException, so if it does (i.e., there's no NPC suitable for parsing), just catch it and carry on.
					engine.put("npc", ParserTarget.NPC.getCharacter("npc", specialNPCs));
				} catch(Exception ex) {
//					System.err.println("Parsing error: Could not initialise npc");
				}
			}
			
			if(specialItem != null) {
				engine.put("thisItem", specialItem);
			} else {
				engine.put("thisItem", null);
			}
			
			// Companion parsing tags:
			if(Main.game.isStarted() && Main.game.getPlayer().hasCompanions()) {
				for(int i = 0; i<Main.game.getPlayer().getCompanions().size(); i++) {
					if(i==0) {
						engine.put("com", Main.game.getPlayer().getCompanions().get(i));
					}
					engine.put("com"+(i+1), Main.game.getPlayer().getCompanions().get(i));
				}
			}

			// Non-companion parsing tags:
			if(Main.game.isStarted()) {
				List<NPC> npcs = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
				npcs.removeIf(npc->npc.isUnique());
				Collections.sort(npcs, (n1, n2)->n1.getId().compareTo(n2.getId()));
				for(int i = 0; i<npcs.size(); i++) {
					if(i==0) {
						engine.put("ncom", npcs.get(i));
					}
					engine.put("ncom"+(i+1), npcs.get(i));
				}
			}
			
			try {
				StringBuilder commandWithVariableCalls = new StringBuilder();
				
				for(String s : parserVariableCalls) {
					commandWithVariableCalls.append(s+";");
				}
				commandWithVariableCalls.append(command);
				
				if(suppressOutput) {
					evaluate(commandWithVariableCalls.toString());
					return "";
				}
				return String.valueOf(evaluate(commandWithVariableCalls.toString()));
				
			} catch (ScriptException e) {
				System.err.println("Scripting parsing error: "+command);
				e.printStackTrace();
				return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>(脚本解析出错！)</i>";
			}
			
		} else if(Main.game!=null && Main.game.isStarted()) {
			if(engine==null) {
				initScriptEngine();
			}
			if(!specialNPCs.isEmpty()) {
				for(int i = 0; i<specialNPCs.size(); i++) {
					if(i==0) {
						engine.put("npc", specialNPCs.get(i));
					}
					engine.put("npc"+(i+1), specialNPCs.get(i));
				}
			} else {
				try { // Getting the target NPC can throw a NullPointerException, so if it does (i.e., there's no NPC suitable for parsing), just catch it and carry on.
					engine.put("npc", ParserTarget.NPC.getCharacter("npc", specialNPCs));
				} catch(Exception ex) {
//					System.err.println("Parsing error: Could not initialise npc 2");
				}
			}

			if(specialItem != null) {
				engine.put("thisItem", specialItem);
			} else {
				engine.put("thisItem", null);
			}

			// Companion parsing tags:
			if(Main.game.getPlayer().hasCompanions()) {
				for(int i = 0; i<Main.game.getPlayer().getCompanions().size(); i++) {
					if(i==0) {
						engine.put("com", Main.game.getPlayer().getCompanions().get(i));
					}
					engine.put("com"+(i+1), Main.game.getPlayer().getCompanions().get(i));
				}
			}
			
			// Non-companion parsing tags:
			List<NPC> npcs = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
			npcs.removeIf(npc->npc.isUnique());
			Collections.sort(npcs, (n1, n2)->n1.getId().compareTo(n2.getId()));
			for(int i = 0; i<npcs.size(); i++) {
				if(i==0) {
					engine.put("ncom", npcs.get(i));
				}
				engine.put("ncom"+(i+1), npcs.get(i));
			}
		}
		
		// Non-script parsing:
		
		parseCapitalise = false;
		parseAddPronoun = false;
		
		if(command.split("_").length==2) {
			if(Character.isUpperCase(command.split("_")[0].charAt(0)))
				parseCapitalise = true;
			command = command.split("_")[1];
			parseAddPronoun = true;
		} 
			
		if(Character.isUpperCase(command.charAt(0))) {
			parseCapitalise = true;
		}
		
		AbstractParserTarget parserTarget = findParserTargetWithTag(target.replaceAll("\u200b", ""));
		if (parserTarget == null) {
			return "INVALID_TARGET_NAME("+target+")";
		}
		
		try {
			character = parserTarget.getCharacter(target.toLowerCase(), specialNPCs);
		} catch(Exception ex) {
			ex.printStackTrace();
			return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>错误：未找到parserTarget.getCharacter()！("+target+")</i>";
		}
		
		// Commands with arguments:
		ParserCommand cmd = findCommandWithTag(command.replaceAll("\u200b", ""));
		if (cmd == null) {
			return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>command_unknown</i>";
		}


		String output = cmd.parse(specialNPCs, command, arguments, target, character);
		if(suppressOutput) {
			return "";
		}
		parseCapitalise = parseCapitalise && cmd.isAllowsCapitalisation();
		parseAddPronoun = parseAddPronoun && cmd.isAllowsPronoun();
		
		if(parseAddPronoun) {
			output = generateSingularDeterminer(output)+output;
		}
		if (parseCapitalise) {
			return Util.capitaliseSentence(output);
		}
		return output;
	}

	private static AbstractParserTarget findParserTargetWithTag(String target) {
		for(AbstractParserTarget parserTarget : ParserTarget.getAllParserTargets()) {
			for(String s : parserTarget.getTags()) {
				if(s.toLowerCase().equals(target.toLowerCase())) {
					return parserTarget;
				}
			}
		}
		System.err.println("UtilText error: findParserTargetWithTag() could not find a character with the parser tag '"+target+"'");
		return null;
	}

	public static GameCharacter findFirstCharacterFromParserTarget(String target) {
		AbstractParserTarget parserTarget = findParserTargetWithTag(target);
		
		List<GameCharacter> specialNPCs = new ArrayList<>();
		if(Main.game.getActiveNPC()!=null && Main.game.getCharactersPresent().contains(Main.game.getActiveNPC())) {
			specialNPCs.add(Main.game.getActiveNPC()); // Make sure active NPC is in index 0 if they're also present
		}
		for(NPC gc : Main.game.getCharactersPresent()) {
			if(!specialNPCs.contains(gc)) {
				specialNPCs.add(gc);
			}
		}
		
		return parserTarget.getCharacter(target, specialNPCs);
	}

	private static ParserCommand findCommandWithTag(String command) {
		for(ParserCommand cmd : commandsList) {
			for(String s : cmd.getTags()) {
				if(command.equalsIgnoreCase(s)) {
					return cmd;
				}
			}
		}
		return null;
	}
	
	public static void resetParsingEngine() {
		engine = null;
		memo.clear();
		specialParsingStrings = new ArrayList<>();
	}
	
	/**
	 * @param string The String to add.
	 * @return Size of specialParsingStrings list.
	 */
	public static int addSpecialParsingString(String string, boolean clearListBeforeAdding) {
		if(clearListBeforeAdding) {
			clearSpecialParsingStrings();
		}
		specialParsingStrings.add(string);
//		initScriptEngine();
		return specialParsingStrings.size();
	}
	
	public static void clearSpecialParsingStrings() {
		specialParsingStrings = new ArrayList<>();
	}
	
	/**
	 * Adds the key/value pair tag/target to the engine.
	 */
	public static void addNewParserTarget(String tag, GameCharacter target) {
		engine.put(tag, target);
	}

	/**
	 * Sets the key/value pair tag/null to the engine.
	 */
	public static void removeParserTarget(String tag) {
		engine.put(tag, null);
	}
	
	public static void initScriptEngine() {
		// http://hg.openjdk.java.net/jdk8/jdk8/nashorn/rev/eb7b8340ce3a
		engine = factory.getScriptEngine("-strict", "--no-java", "--no-syntax-extensions");//, "-scripting");
		memo.clear(); // cached scripts may reference the previous engine; drop them
		try {
			engine.getBindings(ScriptContext.ENGINE_SCOPE).remove("exit");
			engine.getBindings(ScriptContext.ENGINE_SCOPE).remove("quit");
			engine.getBindings(ScriptContext.ENGINE_SCOPE).remove("load");
			engine.getBindings(ScriptContext.ENGINE_SCOPE).remove("loadWithNewGlobal");
			engine.getBindings(ScriptContext.ENGINE_SCOPE).remove("bindProperties");
			engine.getBindings(ScriptContext.ENGINE_SCOPE).remove("Object.bindProperties");
		} catch(Exception ex) {
			System.err.println("ENGINE_SCOPE binding removal error.");
		}
		
//		ScriptEngineManager manager = new ScriptEngineManager();
//		engine = manager.getEngineByName("javascript");

		engine.put("RND", Util.random);
		
		// Parser targets:
		if(Main.game.isStarted()) {
			for(AbstractParserTarget target : ParserTarget.getAllParserTargets()) {
				if(target!=ParserTarget.STYLE && target!=ParserTarget.UNIT && target!=ParserTarget.ITEM && target!=ParserTarget.NPC && target!=ParserTarget.COMPANION && target!=ParserTarget.NON_COMPANION) {
					for(String tag : target.getTags()) {
						engine.put(tag, target.getCharacter(tag, null));
					}
				}
			}
		}
//		for(int i=0; i<specialParsingStrings.size(); i++) {
//			engine.put("SPECIAL_PARSE_"+i, specialParsingStrings.get(i));
//		}
		
		// Core classes:
		engine.put("game", Main.game);
		engine.put("sex", Main.sex);
		engine.put("combat", Main.combat);
		engine.put("properties", Main.getProperties());
		engine.put("itemGen", Main.game.getItemGen());
		engine.put("flags", Main.game.getDialogueFlags());
		engine.put("dialogueManager", Main.game.getDialogueManager());
		
		 // When accessing static methods, use .static before any method calls, e.g. [#util.static.capitaliseSentence('apple')]
		engine.put("util", Util.class);
		engine.put("utilText", UtilText.class);
		engine.put("subspecies", Subspecies.class);

		// Java classes:
		for(Month month : Month.values()) {
			engine.put("MONTH_"+month, month);
		}
		for(DayOfWeek dayOfWeek : DayOfWeek.values()) {
			engine.put("DOW_"+dayOfWeek, dayOfWeek);
		}
		
		// Items:
		for(AbstractWeaponType weaponType : WeaponType.getAllWeapons()) {
			engine.put("WEAPON_"+WeaponType.getIdFromWeaponType(weaponType), weaponType);
		}
		for(AbstractClothingType clothingType : ClothingType.getAllClothing()) {
			engine.put("CLOTHING_"+ClothingType.getIdFromClothingType(clothingType), clothingType);
			engine.put("CT_"+ClothingType.getIdFromClothingType(clothingType), clothingType);
		}
		for(AbstractItemType itemType : ItemType.getAllItems()) {
			engine.put("ITEM_"+ItemType.getIdFromItemType(itemType), itemType);
		}
		for(AbstractSetBonus setBonus : SetBonus.getAllSetBonuses()) {
			engine.put("SET_BONUS_"+SetBonus.getIdFromSetBonus(setBonus), setBonus);
		}
		for(AbstractOutfit outfit : OutfitType.getAllOutfits()) {
			engine.put("OUTFIT_"+OutfitType.getIdFromOutfitType(outfit), outfit);
		}
		for(OutfitType outfitType : OutfitType.values()) {
			engine.put("OUTFIT_TYPE_"+outfitType.toString(), outfitType);
		}
		for(ItemTag it : ItemTag.values()) {
			engine.put("ITEM_TAG_"+it.toString(), it);
		}
		for(CoverableArea ca : CoverableArea.values()) {
			engine.put("COVERABLE_AREA_"+ca.toString(), ca);
			engine.put("CA_"+ca.toString(), ca);
		}
		for(InventorySlot is : InventorySlot.values()) {
			engine.put("INV_SLOT_"+is.toString(), is);
			engine.put("IS_"+is.toString(), is);
		}
		for(EquipClothingSetting ecs : EquipClothingSetting.values()) {
			engine.put("EQUIP_CLOTHING_SETTING_"+ecs.toString(), ecs);
			engine.put("ECS_"+ecs.toString(), ecs);
		}
		for(AbstractItemEffectType aiet : ItemEffectType.getAllEffectTypes()) {
			engine.put("ITEM_EFFECT_TYPE_"+ItemEffectType.getIdFromItemEffectType(aiet), aiet);
		}
		for(TFModifier modifier : TFModifier.values()) {
			engine.put("ENCHANTMENT_MODIFIER_"+modifier.toString(), modifier);
		}
		for(TFPotency potency : TFPotency.values()) {
			engine.put("ENCHANTMENT_POTENCY_"+potency.toString(), potency);
		}
		
		// Tattoos:
		for(AbstractTattooType tattooType : TattooType.getAllTattooTypes()) {
			engine.put("TATTOO_"+TattooType.getIdFromTattooType(tattooType), tattooType);
		}
		for(TattooCounterType tattooCounterType : TattooCounterType.values()) {
			engine.put("TATTOO_COUNTER_"+tattooCounterType.toString(), tattooCounterType);
		}
		for(TattooCountType tattooCountType : TattooCountType.values()) {
			engine.put("TATTOO_COUNT_"+tattooCountType.toString(), tattooCountType);
		}
		for(TattooWritingStyle tattooWritingStyle : TattooWritingStyle.values()) {
			engine.put("TATTOO_WRITING_STYLE_"+tattooWritingStyle.toString(), tattooWritingStyle);
		}
		
		// Misc.:
		for(Colour colour : PresetColour.getAllPresetColours()) {
			engine.put("COLOUR_"+PresetColour.getIdFromColour(colour), colour);
		}
		for(Entry<DialogueNode, String> dn: DialogueManager.getDialogueToIdMap().entrySet()) {
			engine.put("DN_"+dn.getValue(), dn.getKey());
		}
		
		// Bodies:
		for(AbstractRace race : Race.getAllRaces()) {
			engine.put("RACE_"+Race.getIdFromRace(race), race);
		}
		for(AbstractRacialBody racialBody : RacialBody.getAllRacialBodies()) {
			engine.put("RACIAL_BODY_"+RacialBody.getIdFromRacialBody(racialBody), racialBody);
		}
		for(RaceStage raceStage : RaceStage.values()) {
			engine.put("RACE_STAGE_"+raceStage.toString(), raceStage);
		}
		for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
			engine.put("SUBSPECIES_"+Subspecies.getIdFromSubspecies(subspecies), subspecies);
		}
		for(Gender gender : Gender.values()) {
			engine.put("GENDER_"+gender.toString(), gender);
		}
		for(FertilisationType ft : FertilisationType.values()) {
			engine.put("FERTILISATION_"+ft.toString(), ft);
		}
		for(Nocturnality noc : Nocturnality.values()) {
			engine.put("NOCTURNALITY_"+noc.toString(), noc);
		}
		for(LegConfiguration legConf : LegConfiguration.values()) {
			engine.put("LEG_CONFIGURATION_"+legConf.toString(), legConf);
		}
		for(CupSize cupSize : CupSize.values()) {
			engine.put("CUP_SIZE_"+cupSize.toString(), cupSize);
		}
		for(NippleSize nippleSize : NippleSize.values()) {
			engine.put("NIPPLE_SIZE_"+nippleSize.toString(), nippleSize);
		}
		for(AreolaeSize areolaeSize : AreolaeSize.values()) {
			engine.put("AREOLAE_SIZE_"+areolaeSize.toString(), areolaeSize);
		}
		for(HairLength hairLength : HairLength.values()) {
			engine.put("HAIR_LENGTH_"+hairLength.toString(), hairLength);
		}
		for(BodyHair bodyHair : BodyHair.values()) {
			engine.put("BODY_HAIR_"+bodyHair.toString(), bodyHair);
		}
		for(LipSize lipSize : LipSize.values()) {
			engine.put("LIP_SIZE_"+lipSize.toString(), lipSize);
		}
		for(TongueLength tongueLength : TongueLength.values()) {
			engine.put("TONGUE_LENGTH_"+tongueLength.toString(), tongueLength);
		}
		for(FootStructure footStructure : FootStructure.values()) {
			engine.put("FOOT_STRUCTURE_"+footStructure.toString(), footStructure);
		}
		for(AssSize assSize : AssSize.values()) {
			engine.put("ASS_SIZE_"+assSize.toString(), assSize);
		}
		for(HipSize hipSize : HipSize.values()) {
			engine.put("HIP_SIZE_"+hipSize.toString(), hipSize);
		}
		for(GenitalArrangement genArrangement : GenitalArrangement.values()) {
			engine.put("GENITALS_"+genArrangement.toString(), genArrangement);
		}
		for(PenisLength penisLength : PenisLength.values()) {
			engine.put("PENIS_LENGTH_"+penisLength.toString(), penisLength);
		}
		for(TesticleSize testicleSize : TesticleSize.values()) {
			engine.put("TESTICLE_SIZE_"+testicleSize.toString(), testicleSize);
		}
		for(LabiaSize labiaSize : LabiaSize.values()) {
			engine.put("LABIA_SIZE_"+labiaSize.toString(), labiaSize);
		}
		for(ClitorisSize clitSize : ClitorisSize.values()) {
			engine.put("CLITORIS_SIZE_"+clitSize.toString(), clitSize);
		}


		for(BodyMaterial material : BodyMaterial.values()) {
			engine.put("BODY_MATERIAL_"+material.toString(), material);
		}
		for(AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
			engine.put("BODY_COVERING_TYPE_"+BodyCoveringType.getIdFromBodyCoveringType(bct), bct);
		}
		for(BodyCoveringCategory coveringCategory : BodyCoveringCategory.values()) {
			engine.put("BODY_COVERING_CATEGORY_"+coveringCategory, coveringCategory);
		}
		for(CoveringPattern pattern : CoveringPattern.values()) {
			engine.put("COVERING_PATTERN_"+pattern.toString(), pattern);
		}
		for(CoveringModifier modifier : CoveringModifier.values()) {
			engine.put("COVERING_MODIFIER_"+modifier.toString(), modifier);
		}
		for(NippleShape nippleShape : NippleShape.values()) {
			engine.put("NIPPLE_SHAPE_"+nippleShape.toString(), nippleShape);
		}
		for(CumProduction cumProduction : CumProduction.values()) {
			engine.put("CUM_PRODUCTION_"+cumProduction.toString(), cumProduction);
		}
		for(FluidModifier fluidModifier : FluidModifier.values()) {
			engine.put("FLUID_MODIFIER_"+fluidModifier.toString(), fluidModifier);
		}
		for(FluidFlavour fluidFlavour : FluidFlavour.values()) {
			engine.put("FLUID_FLAVOUR_"+fluidFlavour.toString(), fluidFlavour);
		}
		for(BodyPartTag bpt : BodyPartTag.values()) {
			engine.put("BODY_PART_TAG_"+bpt.toString(), bpt);
		}
		// Spelling errors which were corrected in PR#1603 but which now need correct parser references for old mod version support:
		engine.put("BODY_PART_TAG_TAIL_SUTABLE_FOR_PENETRATION", BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION);
		engine.put("BODY_PART_TAG_TAIL_NEVER_SUTABLE_FOR_PENETRATION", BodyPartTag.TAIL_NEVER_SUITABLE_FOR_PENETRATION);

		for(PenetrationGirth girth : PenetrationGirth.values()) {
			engine.put("PENETRATION_GIRTH_"+girth.toString(), girth);
		}
		for(PenetrationModifier penMod : PenetrationModifier.values()) {
			engine.put("PENETRATION_MODIFIER_"+penMod.toString(), penMod);
		}
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			engine.put("ORIFICE_MODIFIER_"+orificeMod.toString(), orificeMod);
		}
		for(TongueModifier tongueMod : TongueModifier.values()) {
			engine.put("TONGUE_MODIFIER_"+tongueMod.toString(), tongueMod);
		}
		for(Height height : Height.values()) {
			engine.put("HEIGHT_"+height.toString(), height);
		}
		for(Muscle muscle : Muscle.values()) {
			engine.put("MUSCLE_"+muscle.toString(), muscle);
		}
		for(BodySize bodySize : BodySize.values()) {
			engine.put("BODY_SIZE_"+bodySize.toString(), bodySize);
		}
		for(BodyShape bodyShape : BodyShape.values()) {
			engine.put("BODY_SHAPE_"+bodyShape.toString(), bodyShape);
		}
		for(Capacity capacity : Capacity.values()) {
			engine.put("CAPACITY_"+capacity.toString(), capacity);
		}
		for(OrificeDepth depth : OrificeDepth.values()) {
			engine.put("DEPTH_"+depth.toString(), depth);
		}
		for(OrificeElasticity elasticity : OrificeElasticity.values()) {
			engine.put("ELASTICITY_"+elasticity.toString(), elasticity);
		}
		for(OrificePlasticity plasticity : OrificePlasticity.values()) {
			engine.put("PLASTICITY_"+plasticity.toString(), plasticity);
		}
		for(Wetness wetness : Wetness.values()) {
			engine.put("WETNESS_"+wetness.toString(), wetness);
		}
		for(EyeShape eyeShape : EyeShape.values()) {
			engine.put("EYE_SHAPE_"+eyeShape.toString(), eyeShape);
		}
		for(OrificeDepth orificeDepth : OrificeDepth.values()) {
			engine.put("ORIFICE_DEPTH_"+orificeDepth.toString(), orificeDepth);
		}
		// Types:
		for(AbstractFluidType fluidType : FluidType.getAllFluidTypes()) {
			engine.put("FLUID_TYPE_"+FluidType.getIdFromFluidType(fluidType), fluidType);
		}
		for(AbstractAntennaType type : AntennaType.getAllAntennaTypes()) {
			engine.put("ANTENNA_TYPE_"+AntennaType.getIdFromAntennaType(type), type);
		}
		for(AbstractAnusType type : AnusType.getAllAnusTypes()) {
			engine.put("ANUS_TYPE_"+AnusType.getIdFromAnusType(type), type);
		}
		for(AbstractArmType type : ArmType.getAllArmTypes()) {
			engine.put("ARM_TYPE_"+ArmType.getIdFromArmType(type), type);
		}
		for(AbstractAssType type : AssType.getAllAssTypes()) {
			engine.put("ASS_TYPE_"+AssType.getIdFromAssType(type), type);
		}
		for(AbstractBreastType type : BreastType.getAllBreastTypes()) {
			engine.put("BREAST_TYPE_"+BreastType.getIdFromBreastType(type), type);
		}
		for(AbstractEarType type : EarType.getAllEarTypes()) {
			engine.put("EAR_TYPE_"+EarType.getIdFromEarType(type), type);
		}
		for(AbstractEyeType type : EyeType.getAllEyeTypes()) {
			engine.put("EYE_TYPE_"+EyeType.getIdFromEyeType(type), type);
		}
		for(AbstractFaceType type : FaceType.getAllFaceTypes()) {
			engine.put("FACE_TYPE_"+FaceType.getIdFromFaceType(type), type);
		}
		for(AbstractFootType type : FootType.getAllFootTypes()) {
			engine.put("FOOT_TYPE_"+FootType.getIdFromFootType(type), type);
		}
		for(AbstractHairType type : HairType.getAllHairTypes()) {
			engine.put("HAIR_TYPE_"+HairType.getIdFromHairType(type), type);
		}
		for(AbstractHornType type : HornType.getAllHornTypes()) {
			engine.put("HORN_TYPE_"+HornType.getIdFromHornType(type), type);
		}
		for(AbstractLegType type : LegType.getAllLegTypes()) {
			engine.put("LEG_TYPE_"+LegType.getIdFromLegType(type), type);
		}
		for(AbstractMouthType type : MouthType.getAllMouthTypes()) {
			engine.put("MOUTH_TYPE_"+MouthType.getIdFromMouthType(type), type);
		}
		for(AbstractNippleType type : NippleType.getAllNippleTypes()) {
			engine.put("NIPPLE_TYPE_"+NippleType.getIdFromNippleType(type), type);
		}
		for(AbstractPenisType type : PenisType.getAllPenisTypes()) {
			engine.put("PENIS_TYPE_"+PenisType.getIdFromPenisType(type), type);
		}
		for(AbstractTorsoType type : TorsoType.getAllTorsoTypes()) {
			engine.put("TORSO_TYPE_"+TorsoType.getIdFromTorsoType(type), type);
		}
		for(AbstractTailType type : TailType.getAllTailTypes()) {
			engine.put("TAIL_TYPE_"+TailType.getIdFromTailType(type), type);
		}
		for(AbstractTentacleType type : TentacleType.getAllTentacleTypes()) {
			engine.put("TENTACLE_TYPE_"+TentacleType.getIdFromTentacleType(type), type);
		}
		for(AbstractTesticleType type : TesticleType.getAllTesticleTypes()) {
			engine.put("TESTICLE_TYPE_"+TesticleType.getIdFromTesticleType(type), type);
		}
		for(AbstractTongueType type : TongueType.getAllTongueTypes()) {
			engine.put("TONGUE_TYPE_"+TongueType.getIdFromTongueType(type), type);
		}
		for(AbstractVaginaType type : VaginaType.getAllVaginaTypes()) {
			engine.put("VAGINA_TYPE_"+VaginaType.getIdFromVaginaType(type), type);
		}
		for(AbstractWingType type : WingType.getAllWingTypes()) {
			engine.put("WING_TYPE_"+WingType.getIdFromWingType(type), type);
		}
		for(WingSize size : WingSize.values()) {
			engine.put("WING_SIZE_"+size.toString(), size.getValue());
		}
		
		
		// Effects & persona:
		for(AbstractFetish f : Fetish.getAllFetishes()) {
			engine.put(Fetish.getIdFromFetish(f), f);
		}
		for(FetishDesire fetishDesire : FetishDesire.values()) {
			engine.put("FETISH_DESIRE_"+fetishDesire.toString(), fetishDesire);
		}
		for(PersonalityTrait personalityTrait : PersonalityTrait.values()) {
			engine.put("PERSONALITY_TRAIT_"+personalityTrait.toString(), personalityTrait);
		}
		for(Occupation occ : Occupation.values()) {
			engine.put("OCCUPATION_"+occ.toString(), occ);
		}
		for (OccupationTag occupationTag : OccupationTag.values()) {
			engine.put("OCCUPATION_TAG_" + occupationTag.toString(), occupationTag);
		}
		for(AbstractPerk p : Perk.getAllPerks()) {
			engine.put("PERK_"+Perk.getIdFromPerk(p), p);
		}
		for(PerkCategory pk : PerkCategory.values()) {
			engine.put("PERK_CATEGORY_"+pk.toString(), pk);
		}
		for(AbstractStatusEffect sa : StatusEffect.getAllStatusEffects()) {
			engine.put("STATUS_EFFECT_"+StatusEffect.getIdFromStatusEffect(sa), sa);
			engine.put("SE_"+StatusEffect.getIdFromStatusEffect(sa), sa);
		}
		for(AbstractAttribute att : Attribute.getAllAttributes()) {
			engine.put("ATTRIBUTE_"+Attribute.getIdFromAttribute(att), att);
		}
		for(CorruptionLevel corruption : CorruptionLevel.values()) {
			engine.put("CORRUPTION_LEVEL_"+corruption.toString(), corruption);
		}
		for(AlcoholLevel alcoholLevel : AlcoholLevel.values()) {
			engine.put("ALCOHOL_LEVEL_"+alcoholLevel.toString(), alcoholLevel);
		}
		
		
		// Combat:
		for(DamageType damageType : DamageType.values()) {
			engine.put("DAMAGE_TYPE_"+damageType.toString(), damageType);
		}
		for(DamageVariance damageVariance : DamageVariance.values()) {
			engine.put("DAMAGE_VARIANCE_"+damageVariance.toString(), damageVariance);
		}
		for(SpellSchool spellSchool : SpellSchool.values()) {
			engine.put("SPELL_SCHOOL_"+spellSchool.toString(), spellSchool);
		}
		for(Spell spell: Spell.values()) {
			engine.put("SPELL_"+spell.toString(), spell);
		}
		for(SpellUpgrade spellUpgrade: SpellUpgrade.values()) {
			engine.put("SPELL_UPGRADE_"+spellUpgrade.toString(), spellUpgrade);
		}
		for(CombatBehaviour behaviour: CombatBehaviour.values()) {
			engine.put("COMBAT_BEHAVIOUR_"+behaviour.toString(), behaviour);
		}
		
		// Sex:
		for(SexParticipantType particiantType : SexParticipantType.values()) {
			engine.put("SEX_PT_"+particiantType.toString(), particiantType);
		}
		for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
			engine.put("ORIFICE_"+orifice.toString(), orifice);
		}
		for(SexAreaPenetration penetration : SexAreaPenetration.values()) {
			engine.put("PENETRATION_"+penetration.toString(), penetration);
		}
		for(GenericSexFlag flag : GenericSexFlag.values()) {
			engine.put("SEX_FLAG_"+flag.toString(), flag);
		}
		for(SexPace pace : SexPace.values()) {
			engine.put("SEX_PACE_"+pace.toString(), pace);
		}
		for(OrgasmCumTarget oct : OrgasmCumTarget.values()) {
			engine.put("OCT_"+oct.toString(), oct);
		}
		for(Entry<String, AbstractSexPosition> position : SexPosition.idToSexPositionMap.entrySet()) {
			engine.put("SEX_POSITION_"+position.getKey(), position.getValue());
		}
		for(Entry<String, SexSlot> slot : SexSlotManager.getIdToSexSlotMap().entrySet()) {
			engine.put("SEX_SLOT_"+slot.getKey(), slot.getValue());
		}
		for(LubricationType lube : LubricationType.values()) {
			engine.put("LUBRICATION_"+lube.toString(), lube);
		}
		
		
		// Other:
		for(Season season : Season.values()) {
			engine.put("SEASON_"+season.toString(), season);
		}
		for(Weather w : Weather.values()) {
			engine.put("WEATHER_"+w.toString(), w);
		}
		for(DayPeriod dayPeriod : DayPeriod.values()) {
			engine.put("DAY_PERIOD_"+dayPeriod.toString(), dayPeriod);
		}
		for(AbstractDialogueFlagValue flag : DialogueFlagValue.getAllDialogueFlagValues()) {
			engine.put("FLAG_"+flag.getId(), flag);
		}
		for(NPCFlagValue flag : NPCFlagValue.values()) {
			engine.put("NPC_FLAG_"+flag.toString(), flag);
		}
		for(NPCGenerationFlag flag : NPCGenerationFlag.values()) {
			engine.put("NPC_GEN_FLAG_"+flag.toString(), flag);
		}
		for(SlavePermission permission : SlavePermission.values()) {
			engine.put("SLAVE_PERMISSION_"+permission.toString(), permission);
		}
		for(SlavePermissionSetting permission : SlavePermissionSetting.values()) {
			engine.put("SLAVE_PERMISSION_SETTING_"+permission.toString(), permission);
		}
		for(QuestLine questLine : QuestLine.values()) {
			engine.put("QUEST_LINE_"+questLine.toString(), questLine);
		}
		for(Quest quest : Quest.values()) {
			engine.put("QUEST_"+quest.toString(), quest);
		}
		for(SexualOrientation orientation : SexualOrientation.values()) {
			engine.put("ORIENTATION_"+orientation.toString(), orientation);
		}
		for(Femininity femininity : Femininity.values()) {
			engine.put("FEMININITY_"+femininity.toString(), femininity);
		}
		for(AffectionLevel affectionLevel : AffectionLevel.values()) {
			engine.put("AFFECTION_"+affectionLevel.toString(), affectionLevel);
		}
		for(AffectionLevelBasic affectionLevelBasic : AffectionLevelBasic.values()) {
			engine.put("AFFECTION_BASIC_"+affectionLevelBasic.toString(), affectionLevelBasic);
		}
		for(ObedienceLevel obedienceLevel : ObedienceLevel.values()) {
			engine.put("OBEDIENCE_"+obedienceLevel.toString(), obedienceLevel);
		}
		for(ObedienceLevelBasic obedienceLevelBasic : ObedienceLevelBasic.values()) {
			engine.put("OBEDIENCE_BASIC_"+obedienceLevelBasic.toString(), obedienceLevelBasic);
		}
		for(Relationship relationship : Relationship.values()) {
			engine.put("RELATIONSHIP_"+relationship.toString(), relationship);
		}
		for(FurryPreference furryPreference : FurryPreference.values()) {
			engine.put("FURRY_PREF_"+furryPreference.toString(), furryPreference);
		}
		for(ForcedTFTendency tfTendency : ForcedTFTendency.values()) {
			engine.put("FORCED_TF_"+tfTendency.toString(), tfTendency);
		}
		for(ForcedFetishTendency fetishTendency : ForcedFetishTendency.values()) {
			engine.put("FORCED_FETISH_"+fetishTendency.toString(), fetishTendency);
		}
		for(AbstractWorldType worldType : WorldType.getAllWorldTypes()) {
			engine.put("WORLD_TYPE_"+WorldType.getIdFromWorldType(worldType), worldType);
		}
		for(AbstractPlaceType placeType : PlaceType.getAllPlaceTypes()) {
			engine.put("PLACE_TYPE_"+PlaceType.getIdFromPlaceType(placeType), placeType);
		}
		for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
			engine.put("PLACE_UPGRADE_"+PlaceUpgrade.getIdFromPlaceUpgrade(upgrade), upgrade);
		}
		for(AbstractEncounter encounter : Encounter.getAllEncounters()) {
			engine.put("ENCOUNTER_"+Encounter.getIdFromEncounter(encounter), encounter);
		}
		for(InventoryInteraction interaction : InventoryInteraction.values()) {
			engine.put("INVENTORY_INTERACTION_"+interaction.toString(), interaction);
		}
		for(SlaveJob job : SlaveJob.values()) {
			engine.put("SLAVE_JOB_"+job.toString(), job);
		}
		for(SlaveJobSetting jobSetting : SlaveJobSetting.values()) {
			engine.put("SLAVE_JOB_SETTING_"+jobSetting.toString(), jobSetting);
		}
		
		
		
		// static methods don't work unless initialised like so:
//		try {
//			evaluate("var sex = Java.type('com.lilithsthrone.game.sex.Sex');");
//		} catch (ScriptException e) {
//			e.printStackTrace();
//		}
		// This requires the flag "--no-java" to be removed from the engine init line up above, and I'm not sure if that's a good idea or not...

//		engine.put("Packages.com.lilithsthrone.utils.Util");
		
//		StringBuilder sb = new StringBuilder();
//		for(Entry<String, Object> entry : engine.getBindings(ScriptContext.ENGINE_SCOPE).entrySet()) {
//			sb.append(entry.getKey()+", "+entry.getValue().toString()+"\n");
//		}
//		System.out.println(sb.toString());
	}
	
	private static String parseConditionalSyntaxNew(List<GameCharacter> specialNPCs, AbstractCoreItem specialItem, Map<String, String> conditionals, boolean hasXmlVariables, boolean handleExceptions) throws Exception {
		for(Entry<String, String> entry : conditionals.entrySet()) {
			if(handleExceptions) {
				try {
					if(evaluateConditional(specialNPCs, specialItem, entry.getKey(), hasXmlVariables)){
						return UtilText.parse(specialNPCs, specialItem, entry.getValue(), false, new ArrayList<>()); //TODO tags lost
					}

				} catch (ScriptException e) {
					System.err.println("Conditional parsing (from Map) error: "+entry.getKey()+" | Size of variableCalls: "+parserVariableCalls.size());
					e.printStackTrace();
					return "<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>(条件解析出错！)</i>";
				}

			} else {
				if(evaluateConditional(specialNPCs, specialItem, entry.getKey(), hasXmlVariables)){
					return UtilText.parseNoExceptionHandling(specialNPCs, specialItem, entry.getValue(), false, new ArrayList<>()); //TODO tags lost
				}
			}
		}
		
		return "";
	}
	
	public static boolean evaluateConditional(List<GameCharacter> specialNPCs, AbstractCoreItem specialItem, String conditionalStatement, boolean hasXmlVariables) throws ScriptException {
		if(engine==null) {
			initScriptEngine();
		}

		if(Main.game.isStarted()) {
			if(!specialNPCs.isEmpty()) {
	//			System.out.println("List size: "+specialNPCList.size());
				for(int i = 0; i<specialNPCs.size(); i++) {
					if(i==0) {
						engine.put("npc", specialNPCs.get(i));
					}
					engine.put("npc"+(i+1), specialNPCs.get(i));
	//				System.out.println("Added: npc"+(i+1));
				}
				
			} else {
				try { // Getting the target NPC can throw a NullPointerException, so if it does (i.e., there's no NPC suitable for parsing), just catch it and carry on.
					engine.put("npc", ParserTarget.NPC.getCharacter("npc", specialNPCs));
	//				System.out.println("specialNPCList is empty");
				} catch(Exception ex) {
	//				System.err.println("Parsing error 2: Could not initialise npc");
				}
			}
			
			if(specialItem != null) {
				engine.put("thisItem", specialItem);
			} else {
				engine.put("thisItem", null);
			}
		

			// Companion parsing tags:
			if(Main.game.getPlayer().hasCompanions()) {
				for (int i = 0; i < Main.game.getPlayer().getCompanions().size(); i++) {
					if (i == 0) {
						engine.put("com", Main.game.getPlayer().getCompanions().get(i));
					}
					engine.put("com" + (i + 1), Main.game.getPlayer().getCompanions().get(i));
				}
			}
			
			// Non-companion parsing tags:
			List<NPC> npcs = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
			npcs.removeIf(npc->npc.isUnique());
			Collections.sort(npcs, (n1, n2)->n1.getId().compareTo(n2.getId()));
			for(int i = 0; i<npcs.size(); i++) {
				if(i==0) {
					engine.put("ncom", npcs.get(i));
				}
				engine.put("ncom"+(i+1), npcs.get(i));
			}
		}
		
		StringBuilder conditionalStatementWithVariables = new StringBuilder();
		
		if(hasXmlVariables) {
			for(String s : parserVariableCalls) {
				conditionalStatementWithVariables.append(s+";");
			}
		}
		conditionalStatementWithVariables.append(conditionalStatement);
		
		return (boolean)evaluate(conditionalStatementWithVariables.toString());
	}
	
	
	/**
	 * Adds standard commands related to the baseCommand.<br/>
	 * Commands include:<br/>
	 * Race<br/>
	 * Skin<br/>
	 * Skin+<br/>
	 * Colour<br/>
	 * name<br/>
	 * name+<br/>
	 * names<br/>
	 * names+<br/>
	 */
	private static void addStandardParsingCommands(List<String> tags, List<String> tagsPlural, BodyPartType bodyPart) {
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "Race"),
				true,
				true,
				"",
				"返回与此身体部件关联的种族名称。种族不带有性别特征(例如：会返回“狼化形”，而非“狼女”)。",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				try {
					return getBodyPartFromType(bodyPart,character).getType().getRace().getName(character.getBody(), getBodyPartFromType(bodyPart, character).isFeral(character));
				} catch(Exception ex) {
					return "null_body_part";
				}
			}
		});

		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural,
						"raceFeral",
						"feralRace"),
				true,
				true,
				"",
				"返回与该身体部件有关的野生种族名称。种族不带有性别特征 (例如：会返回“狗”，而非“婊子”)。",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				try {
					return getBodyPartFromType(bodyPart,character).getType().getRace().getName(true);
				} catch(Exception ex) {
					return "null_body_part";
				}
			}
		});
		
//		commandsList.add(new ParserCommand(
//				getModifiedTags(tags, tagsPlural, "Races"),
//				true,
//				true,
//				"",
//				"Returns the plural name of the race that's associated with this body part. Race is *not* gender-specific (i.e. will return 'wolf-morph', not 'wolf-girl').",
//				bodyPart){
//			@Override
//			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
//				return getBodyPartFromType(bodyPart).getType().getRace().getName();
//			}
//		});

		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "Skin"),
				true,
				true,
				"",
				"返回覆盖这个部位的“皮肤”名字。实际上，可能会出现诸多差分，比如：皮毛、角质、鳞片、粘液等等。",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				return getSkinName(getBodyPartFromType(bodyPart,character).getType(), character);
			}
		});
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "Skin+", "SkinD"),
				true,
				true,
				"",
				"返回覆盖此身体部位的‘皮肤’，和‘Skin’指令相同，但这会在前缀添加一个描述词(如果有的话)。",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Covering coveringHandledFreckles = character.getCovering(getBodyPartFromType(bodyPart, character).getBodyCoveringType(character));
				
				if(tags.contains("face")) {
					coveringHandledFreckles = getCoveringHandledFreckles(character, getBodyPartFromType(bodyPart, character), coveringHandledFreckles);
				}
				
				return getSkinNameWithDescriptor(getBodyPartFromType(bodyPart,character).getType(), coveringHandledFreckles, character);
			}
		});

		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "FullDescription"),
				true,
				true,
				"如果你希望此描述的颜色名称呈现色彩，为真。",
				"返回这个部位的完整描述。",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
//				System.out.println(getBodyPartFromType(bodyPart, character).getName(character)+", "+getBodyPartFromType(bodyPart, character).getBodyCoveringType(character)+", "+getBodyPartFromType(bodyPart, character).getType().getTransformName());
				Covering coveringHandledFreckles = character.getCovering(getBodyPartFromType(bodyPart, character).getBodyCoveringType(character));

				if(tags.contains("face")) {
					coveringHandledFreckles = getCoveringHandledFreckles(character, getBodyPartFromType(bodyPart, character), coveringHandledFreckles);
				}
				
				if(coveringHandledFreckles==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return coveringHandledFreckles.getFullDescription(character, true);
					}
				}
				return coveringHandledFreckles.getFullDescription(character, false);
			}
		});
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "FullDescriptionColour", "FullDescriptionColor", "FullDescriptionColoured", "FullDescriptionColored"),
				true,
				true,
				"",
				"返回这个部位的完整描述。",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Covering coveringHandledFreckles = character.getCovering(getBodyPartFromType(bodyPart, character).getBodyCoveringType(character));

				if(tags.contains("face")) {
					coveringHandledFreckles = getCoveringHandledFreckles(character, getBodyPartFromType(bodyPart, character), coveringHandledFreckles);
				}
				
				if(coveringHandledFreckles==null) {
					return "";
				}
				return coveringHandledFreckles.getFullDescription(character, true);
			}
		});
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "Colour", "Color"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"返回覆盖此身体部位的任何‘皮肤’的颜色。",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Covering coveringHandledFreckles = character.getCovering(getBodyPartFromType(bodyPart, character).getBodyCoveringType(character));

				if(tags.contains("face")) {
					coveringHandledFreckles = getCoveringHandledFreckles(character, getBodyPartFromType(bodyPart, character), coveringHandledFreckles);
				}
				
				if(coveringHandledFreckles==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return coveringHandledFreckles.getColourDescriptor(character, true, parseCapitalise);
					}
				}
				return coveringHandledFreckles.getColourDescriptor(character, false, parseCapitalise);
			}
		});
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "ColourPrimary", "ColorPrimary", "PrimaryColour", "PrimaryColor"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"返回覆盖此身体部位的任何‘皮肤’的主要颜色。",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Covering coveringHandledFreckles = character.getCovering(getBodyPartFromType(bodyPart, character).getBodyCoveringType(character));

				if(tags.contains("face")) {
					coveringHandledFreckles = getCoveringHandledFreckles(character, getBodyPartFromType(bodyPart, character), coveringHandledFreckles);
				}
				
				if(coveringHandledFreckles==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return coveringHandledFreckles.getPrimaryColourDescriptor(true);
					}
				}
				return coveringHandledFreckles.getPrimaryColourDescriptor(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "ColourSecondary", "ColorSecondary", "SecondaryColour", "SecondaryColor"),
				true,
				true,
				"若你希望此颜色名称呈现色彩，为真。",
				"返回覆盖此身体部位的任何‘皮肤’的次要颜色。",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				Covering coveringHandledFreckles = character.getCovering(getBodyPartFromType(bodyPart, character).getBodyCoveringType(character));

				if(tags.contains("face")) {
					coveringHandledFreckles = getCoveringHandledFreckles(character, getBodyPartFromType(bodyPart, character), coveringHandledFreckles);
				}
				
				if(coveringHandledFreckles==null) {
					return "";
				}
				if(arguments!=null) {
					if(arguments.equalsIgnoreCase("true")) {
						return coveringHandledFreckles.getSecondaryColourDescriptor(true);
					}
				}
				return coveringHandledFreckles.getSecondaryColourDescriptor(false);
			}
		});
		
		commandsList.add(new ParserCommand(
				getModifiedTags(tags, tagsPlural, "ColourHex", "ColorHex"),
				true,
				true,
				"",
				"返回覆盖此身体部位的任何‘皮肤’的颜色的Hex码。",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(character.getCovering(getBodyPartFromType(bodyPart,character).getBodyCoveringType(character))==null) {
					return "";
				}
				return character.getCovering(getBodyPartFromType(bodyPart,character).getBodyCoveringType(character)).getPrimaryColour().toWebHexString();
			}
		});
		
		commandsList.add(new ParserCommand(
				tags,
				true,
				true,
				"(强制单数)",
				"返回此身体部位的基本单数名称。可将true作为参数传入可防止此名称自动调整为其复数形式。"
						+ " (例：若一个角色有两个角，horn(true)命令就会输出‘角’)",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if((arguments!=null && Boolean.valueOf(arguments)) && bodyPart!=BodyPartType.SKIN) {  // Skin replacements (such as scales, feathers, fur), should always use the default plurality.
					return getBodyPartFromType(bodyPart,character).getNameSingular(character);
				}
				if(parseAddPronoun) {
					parseAddPronoun = false;
					return applyDeterminer(getBodyPartFromType(bodyPart,character).getDeterminer(character), getBodyPartFromType(bodyPart,character).getName(character));
					
				} else {
					return getBodyPartFromType(bodyPart,character).getName(character);
				}
			}
		});

		commandsList.add(new ParserCommand(
				tagsPlural,
				true,
				true,
				"(强制复数)",
				"返回此身体部位的基本复数名称。可将true作为参数传入可防止此名称自动调整为其单数形式。"
						+ " (例：若一个角色只有一个腹乳，crotchBoobs(true)命令就会输出“腹乳”)",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				if(arguments!=null && Boolean.valueOf(arguments)) {
					return getBodyPartFromType(bodyPart,character).getNamePlural(character);
				}
				if(parseAddPronoun) {
					parseAddPronoun = false;
					return applyDeterminer(getBodyPartFromType(bodyPart,character).getDeterminer(character), getBodyPartFromType(bodyPart,character).getName(character));
					
				} else {
					return getBodyPartFromType(bodyPart,character).getName(character);
				}
			}
		});

		commandsList.add(new ParserCommand(
				getModifiedTags(tags, null, "+", "D"),
				true,
				true,
				"(强制单数)",
				"返回此身体部位的单数名称，并在开头附加一个描述词(如果有的话)。"
						+ "如果适用可将true作为参数传入可防止此名称自动调整为其复数形式。"
						+ " (例：若一个角色有两个角，horn+(true)命令就会输出‘弯曲的角’)",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				String name;
				if((arguments!=null && Boolean.valueOf(arguments)) && bodyPart!=BodyPartType.SKIN) {  // Skin replacements (such as scales, feathers, fur), should always use the default plurality.
					name = getBodyPartFromType(bodyPart,character).getNameSingular(character);
				} else {
					name = getBodyPartFromType(bodyPart,character).getName(character);
				}
				if(parseAddPronoun) {
					parseAddPronoun = false;
					return applyDeterminer(getBodyPartFromType(bodyPart,character).getDeterminer(character), applyDescriptor(getBodyPartFromType(bodyPart,character).getDescriptor(character), name));
					
				} else {
					return applyDescriptor(getBodyPartFromType(bodyPart,character).getDescriptor(character), name);
				}
			}
		});

		commandsList.add(new ParserCommand(
				getModifiedTags(null, tagsPlural, "+", "D"),
				true,
				true,
				"(强制复数)",
				"返回此身体部位的复数名称，并在开头附加一个描述词(如果有的话)。"
						+ "如果适用可将true作为参数传入可防止此名称自动调整为其单数形式。"
						+ " (例：若一个角色只有一个乳房，crotchBoobs+(true)命令就会输出“大腹乳”)",
				bodyPart){
			@Override
			public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
				String name;
				if((arguments!=null && Boolean.valueOf(arguments))) {
					name = getBodyPartFromType(bodyPart,character).getNamePlural(character);
				} else {
					name = getBodyPartFromType(bodyPart,character).getName(character);
				}
				if(parseAddPronoun) {
					parseAddPronoun = false;
					return applyDeterminer(getBodyPartFromType(bodyPart,character).getDeterminer(character), applyDescriptor(getBodyPartFromType(bodyPart,character).getDescriptor(character), name));
					
				} else {
					return applyDescriptor(getBodyPartFromType(bodyPart,character).getDescriptor(character), name);
				}
			}
		});
	}
	
	/**
	 * Helper method for generating tags with specified endings.
	 */
	private static List<String> getModifiedTags(List<String> tags, List<String> tagsPlural, String... ending) {
		List<String> modifiedTags = new ArrayList<>();
		
		if(tags!=null)
			for(String s : tags) {
				for(String e : ending)
					modifiedTags.add(s+e);
			}
		
		if(tagsPlural!=null)
			for(String s : tagsPlural) {
				for(String e : ending)
					modifiedTags.add(s+e);
			}
		
		return modifiedTags;
	}
	
	
	private static BodyPartInterface getBodyPartFromType(BodyPartType type, GameCharacter character) {
		switch(type){
			case ANTENNA:
				return character.getBody().getAntenna();
			case ARM:
				return character.getBody().getArm();
			case ASS:
				return character.getBody().getAss();
			case ANUS:
				return character.getBody().getAss().getAnus();
			case BREAST:
				return character.getBody().getBreast();
			case MILK:
				return character.getBody().getBreast().getMilk();
			case NIPPLES:
				return character.getBody().getBreast().getNipples();
			case BREAST_CROTCH:
				return character.getBody().getBreastCrotch();
			case NIPPLES_CROTCH:
				return character.getBody().getBreastCrotch().getNipples();
			case MILK_CROTCH:
				return character.getBody().getBreastCrotch().getMilk();
			case EAR:
				return character.getBody().getEar();
			case EYE:
				return character.getBody().getEye();
			case FACE:
				return character.getBody().getFace();
			case MOUTH:
				return character.getBody().getFace().getMouth();
			case HAIR:
				return character.getBody().getHair();
			case HORN:
				return character.getBody().getHorn();
			case LEG:
				return character.getBody().getLeg();
			case PENIS:
				return character.getCurrentPenis();
			case TESTICLES:
				return character.getCurrentPenis().getTesticle();
			case CUM:
				return character.getCurrentPenis().getTesticle().getCum();
			case SKIN:
				return character.getBody().getTorso();
			case TAIL:
				if(character.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
					return character.getBody().getLeg();
				}
				return character.getBody().getTail();
			case TENTACLE:
				return character.getBody().getTentacle();
			case TONGUE:
				return character.getBody().getFace().getTongue();
			case CLIT:
				return character.getBody().getVagina().getClitoris();
			case VAGINA:
				return character.getBody().getVagina();
			case GIRL_CUM:
				return character.getBody().getVagina().getGirlcum();
			case WING:
				return character.getBody().getWing();
			case GENERIC:
			case SPINNERET: //TODO?
				return null;
		}
		return null;
	}
	
	
	/**
	 * Some methods might return a null or empty string for a descriptor. This method accounts for that, applying a descriptor if one is available and then returning the descriptor + name combination.
	 */
	private static String applyDescriptor(String descriptor, String name) {
		if(descriptor==null)
			return name;
		
		return (descriptor.length() > 0 ? descriptor + "" : UtilText.returnStringAtRandom("平平无奇的", "普通的", "平常的")) + name;
	}
	
	/**
	 * Some methods might return a null or empty string for a determiner. This method accounts for that, applying a special determiner if one is available and then returning the descriptor + name combination.
	 */
	private static String applyDeterminer(String descriptor, String input) {
		if(descriptor==null) {
			return input;
		}
		
		return descriptor.length()>0
				? descriptor + ""
				: UtilText.generateSingularDeterminer(input) + input;
	}

	private static String getSubspeciesName(AbstractSubspecies subspecies, GameCharacter character) {
		if(subspecies==null) {
			return "";
		}
		
		if (character.isFeminine()) {
			return subspecies.getSingularFemaleName(character.getBody());
		} else {
			return subspecies.getSingularMaleName(character.getBody());
		}
	}
	
	private static String getSubspeciesNamePlural(AbstractSubspecies race, GameCharacter character) {
		if(race==null)
			return "";
		if (character.isFeminine()) {
			return race.getPluralFemaleName(character.getBody());
		} else {
			return race.getPluralMaleName(character.getBody());
		}
	}
	
	private static String getSkinName(BodyPartTypeInterface bodyPart, GameCharacter character) {
		if(bodyPart.getBodyCoveringType(character)==null)
			return "";
		
		if(parseAddPronoun) {
			parseAddPronoun = false;
			return applyDeterminer(bodyPart.getBodyCoveringType(character).getDeterminer(character), bodyPart.getBodyCoveringType(character).getName(character));
		} else {
			return bodyPart.getBodyCoveringType(character).getName(character);
		}
	}
	
	private static String getSkinNameWithDescriptor(BodyPartTypeInterface bodyPart, Covering asCovering, GameCharacter character) {
		if(bodyPart.getBodyCoveringType(character)==null)
			return "";
		
		if(parseAddPronoun) {
			parseAddPronoun = false;
			return applyDeterminer(
					asCovering.getType().getDeterminer(character),
					applyDescriptor(asCovering.getModifier().getName(),
					asCovering.getType().getName(character)));
		} else {
			return applyDescriptor(asCovering.getModifier().getName(), asCovering.getType().getName(character));
		}
	}
	
	private static Covering getCoveringHandledFreckles(GameCharacter character, BodyPartInterface bodyPart, Covering covering) {
		if(covering.getPattern()==CoveringPattern.FRECKLED_FACE) {
			return new Covering(
					bodyPart.getBodyCoveringType(character),
					CoveringPattern.FRECKLED,
					covering.getModifier(),
					covering.getPrimaryColour(),
					covering.isPrimaryGlowing(),
					covering.getSecondaryColour(),
					covering.isSecondaryGlowing());
		}
		return covering;
	}
	
	public static String getNaturalEnumeration(List<String> elements) {
		if(elements.isEmpty()) {
			return "";
		}
		
		if(elements.size() == 1) {
			return elements.get(0);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<elements.size(); ++i) {
			sb.append(elements.get(i));

			if(i == elements.size()-2) {
				sb.append("和");
			} else if(i != elements.size() - 1) {
				sb.append("，");
			}
		}
		return sb.toString();
	}

	public static AbstractClothingType getClothingTypeForParsing() {
		return clothingTypeForParsing;
	}

	public static void setClothingTypeForParsing(AbstractClothingType clothingTypeForParsing) {
		UtilText.clothingTypeForParsing = clothingTypeForParsing;
		if(engine==null) {
			initScriptEngine();
		}
		engine.put("clothing", getClothingTypeForParsing());
	}

	public static Body getBodyForParsing() {
		return body;
	}
	
	public static void setBodyForParsing(String tag, Body body) {
		UtilText.body = body;
		if(engine==null) {
			initScriptEngine();
		}
		engine.put(tag, getBodyForParsing());
	}

	public static AbstractRace getRaceForParsing() {
		return race;
	}
	
	public static void setRaceForParsing(String tag, AbstractRace race) {
		UtilText.race = race;
		if(engine==null) {
			initScriptEngine();
		}
		engine.put(tag, getRaceForParsing());
	}

	public static CharacterInventory getInventoryForParsing() {
		return inventory;
	}
	
	public static void setInventoryForParsing(String tag, CharacterInventory inventory) {
		UtilText.inventory = inventory;
		if(engine==null) {
			initScriptEngine();
		}
		engine.put(tag, getInventoryForParsing());
	}
	
	
	private static final Map<String, CompiledScript> memo = new HashMap<>();
	private static final int memo_limit = 500;
	/**
	 * Added in PR#1442 to increase performance by adding a memoization cache to compile scripting engine scripts.
	 * <br/>- Adds a cache intended to hold compiled forms of script engine scripts.
	 * <br/>- Cache capacity set to 500, and will stop adding new entries at that limit (tests did not exceed 100, but mods affect this).
	 * <br/>- Tests showed scripting engine calls take 50% less time on average.
	 * <br/>- WARNING: adds one more nashorn warning.
	 * @param command
	 * @return
	 * @throws ScriptException
	 */
	private static Object evaluate(String command) throws ScriptException {
		CompiledScript script;
		if (!memo.containsKey(command)) {
			script = ((NashornScriptEngine)engine).compile(command);
			if (memo.size() < memo_limit) {
				memo.put(command, script);
				if (memo.size() == memo_limit) {
					System.err.println("Memo has reached capacity! Additional script commands will not be memoized.");
				}
			}
		} else {
			script = memo.get(command);
		}
		return script.eval();
	}
}
