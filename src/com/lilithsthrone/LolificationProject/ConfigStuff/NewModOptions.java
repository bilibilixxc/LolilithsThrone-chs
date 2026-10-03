package com.lilithsthrone.LolificationProject.ConfigStuff;

import com.lilithsthrone.LolificationProject.Library;

import com.lilithsthrone.LolificationProject.ModCommon.Initializers;
import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.settings.SliderOption;
import com.lilithsthrone.game.settings.ToggleOption;
import com.lilithsthrone.utils.colours.PresetColour;
import org.w3c.dom.Element;

public class NewModOptions {

    /* TOGGLE BUTTONS */
    public static final ToggleOption MIN_AGE_SPEECH = new ToggleOption(
            "MIN_AGE_SPEECH",
            PresetColour.BASE_GOLD,
            "Minimum Age Speech",
            "</br>When disabled, removes the 'due to everyone starting at age' part of age descriptions.",
            PropertyValue.minimumAgeSpeech
    );
    public static final ToggleOption FETISH_PEE = new ToggleOption(
            "FETISH_PEE",
            PresetColour.BASE_GOLD,
            "FETISH : Pee Content",
            "</br>When disabled, all pee content will be disabled.",
            PropertyValue.peeContent,
            () -> {
                if (ModProps.isPeeingEnabled())
                    Initializers.initPeeActions();
                else Initializers.removePeeActions();
            }
    );

    public static final ToggleOption FETISH_AGE = new ToggleOption(
            "FETISH_AGE",
            PresetColour.BASE_GOLD,
            "FETISH : Age Gap Content",
            "</br>When disabled, Age Difference Fetishes are disabled. Incomplete",
            PropertyValue.extremeAgeContent
    );

    public static final ToggleOption LEXI_MENU_OPTIONS = new ToggleOption(
            "LEXI_MENU_OPTIONS",
            PresetColour.BASE_GOLD,
            "Better Main Menu",
            "</br>When enabled, the objectively better Main Menu layout will be used.",
            PropertyValue.lexiMenuOptions
    );
    public static final ToggleOption COMBAT_HUG = new ToggleOption(
            "COMBAT_HUG",
            PresetColour.BASE_PINK,
            "Hug Combat Move",
            "</br>When disabled, you cannot hug your enemies to defeat. </br>Disabled Until I RTFM on CombatMove XML",
            PropertyValue.enableHug
    );

    public static final ToggleOption SHOW_AGE = new ToggleOption(
            "SHOW_AGE",
            PresetColour.BASE_GREEN_LIME,
            "Show Age",
            "</br>When enabled, NPCs upon inspection will show the age they 'appear' to be.",
            PropertyValue.showAge
    );

    public static final ToggleOption SHOW_TRUE_AGE = new ToggleOption(
            "SHOW_TRUE_AGE",
            PresetColour.BASE_GREEN_LIME,
            "Show True Age",
            "</br>When enabled, NPCs upon inspection will show their true age with Observant Perk Allocated.",
            PropertyValue.showTrueAge
    );

    public static final ToggleOption KIN_MODE = new ToggleOption(
            "KIN_MODE",
            PresetColour.BASE_GREEN_LIME,
            "Change -morph to kin",
            "</br>When enabled, races will be declared as _kin instead of _-morph.",
            PropertyValue.kinMode
    );

    public static final ToggleOption SHOW_ENHANCED_DESCRIPTION = new ToggleOption(
            "SHOW_ENHANCED_DESCRIPTION",
            PresetColour.BASE_GREEN_LIME,
            "Show Age In Encounter Name",
            "</br>When enabled, NPCs upon encounter will show their age in name. Example (A man -> A young man)",
            PropertyValue.showEnhancedDescription,
            () -> {
                String[] divs = new String[]{"SHOTA_AGE", "LOLI_AGE", "YOUNG_AGE", "OLD_AGE"};
                for (String div : divs) {
                    if (!ModProps.isShowEnhancedDescriptionEnabled())
                        ((Element) MainController.document.getElementById(div).getParentNode().getParentNode()).setAttribute("hidden",
                                String.valueOf(ModProps.isShowEnhancedDescriptionEnabled()));
                    else ((Element) MainController.document.getElementById(div).getParentNode().getParentNode()).removeAttribute("hidden");
                }
            }
    );

    public static final ToggleOption OFFSPRING_AGE_PREF = new ToggleOption(
            "OFFSPRING_AGE_PREF",
            PresetColour.BASE_GREEN_LIME,
            "Apply Preferences to Offspring Age",
            "</br>When enabled, Offspring will be mystically aged to somewhere within your age content preferences.",
            PropertyValue.applyPrefToOffspring
    );

    public static final ToggleOption OFFSPRING_INHERIT_LEVEL = new ToggleOption(
            "OFFSPRING_INHERIT_LEVEL",
            PresetColour.BASE_GREEN_LIME,
            "Offspring Inherit Level",
            "</br>When enabled, Offspring will randomly inherit levels up to the same level you are +1.",
            PropertyValue.offspringLevelParent
    );

    public static final ToggleOption AGE_PROGRESSION = new ToggleOption(
            "AGE_PROGRESSION",
            PresetColour.BASE_RED_LIGHT,
            "Age Progression",
            "</br>When enabled, NPCs bodies will be checked for aging. AKA Growing Up Simulation",
            PropertyValue.ageProgression
    );

    public static final ToggleOption EQUIP_UNIQUES = new ToggleOption(
            "EQUIP_UNIQUES",
            PresetColour.BASE_RED_LIGHT,
            "Equip Unique NPCs",
            "</br>When enabled, You can modify Unique NPC Equipment<br>NOTE: Currently only works on certain shopkeepers! Example: Nyan, Finch",
            PropertyValue.equipUniques
    );

    public static final ToggleOption ENSLAVE_LUMI = new ToggleOption(
            "ENSLAVE_LUMI",
            PresetColour.BASE_RED_LIGHT,
            "Enslave-able Lumi",
            "</br>When enabled, You can enslave Lumi after defeating her<br>NOTE: Requires additional testing...",
            PropertyValue.enslaveLumi
    );

    public static final ToggleOption COMBAT_LEAKAGE = new ToggleOption(
            "COMBAT_LEAKAGE",
            PresetColour.BASE_YELLOW_LIGHT,
            "Combat Leakage",
            "</br>When enabled, critical hits have a chance to cause leakage. (when watersports is enabled)",
            PropertyValue.combatLeakage
    );

    public static final ToggleOption FORCE_CLEAN_SLOTS = new ToggleOption(
            "FORCE_CLEAN_SLOTS",
            PresetColour.BASE_YELLOW_LIGHT,
            "Force Clean Slots",
            "</br>When enabled, any 'wet' clothing will be forcibly cleaned of 'wet' when also cleaned of 'dirty'",
            PropertyValue.forceClean
    );

    public static final ToggleOption ENABLE_PENT_UP = new ToggleOption(
            "ENABLE_PENT_UP",
            PresetColour.BASE_RED,
            "Enable Pent Up",
            "</br>Slaves will get 'pent up' after a full day without sex<br/>Set to OFF to disable this status effect",
            PropertyValue.enablePentUp
    );

    public static final ToggleOption NERF_AFF_OBED = new ToggleOption(
            "NERF_AFF_OBED",
            PresetColour.BASE_GREY_DARK,
            "No Negative Room Modifiers",
            "</br>Prevents any negative modifiers for room upgrades and slave jobs, thereby preventing slaves from losing affection or obedience over time.",
            PropertyValue.nerfedAffObed
    );

    public static final ToggleOption ENABLE_SLOVENLY = new ToggleOption(
            "ENABLE_SLOVENLY",
            PresetColour.BASE_YELLOW_PALE,
            "Enable Sloven Speech",
            "</br>Allows randomly generated characters to spawn with slovenly speech trait<br/>Does not affect unique NPCs!",
            PropertyValue.enableSlovenly
    );

    public static final ToggleOption LOOT_THE_MUSEUM = new ToggleOption(
            "LOOT_THE_MUSEUM",
            PresetColour.BASE_BLUE,
            "Loot The Museum",
            "</br>Take all the clothing with you when you click \"To the stage\" during character creation<br>NOTE: You can change colours before leaving!",
            PropertyValue.lootTheMuseum
    );

    public static final ToggleOption WAKE_AFFECTION_LOSS = new ToggleOption(
            "WAKE_AFFECTION_LOSS",
            PresetColour.BASE_BLUE,
            "Affection Loss From Waking NPCs",
            "</br>When On, Waking up Occupants causes them to lose Affection when being woke up Slaves/Occupants",
            PropertyValue.wakeAffectionLoss
    );

    public static final ToggleOption VANILLA_JOB_CONDITIONS = new ToggleOption(
            "VANILLA_JOB_CONDITIONS",
            PresetColour.BASE_GOLD,
            "Vanilla Job Conditions",
            "</br>When disabled, Player History (Select Job in Character Creation) will use vanilla conditions instead of the new conditions",
            PropertyValue.vanillaJobConditions
    );

    public static final ToggleOption ORIGINAL_AGE = new ToggleOption(
            "ORIGINAL_AGE",
            PresetColour.BASE_BLUE_DARK,
            "Unique NPC Age",
            "</br>When off Unique NPCs will have their age lowered by (18 - Minimum Age)",
            PropertyValue.originalAge
    );

    public static final ToggleOption ENABLE_ENFORCERS = new ToggleOption(
            "ENABLE_ENFORCERS",
            PresetColour.BASE_BLUE_DARK,
            "Enable Enforcers",
            "</br>Allows enforcers to spawn and harass the player via random encounters in certain locations",
            PropertyValue.enableEnforcers
    );

    public static final ToggleOption FORCE_ORGASMIC_DRAIN = new ToggleOption(
            "FORCE_ORGASMIC_DRAIN",
            PresetColour.BASE_BLUE_DARK,
            "Force Orgasmic Drain Spawns",
            "</br>Forces NPC's to spawn with Orgasmic Drain perk, according to Orgasmic Level Drain Chance setting. Note: Probably not required after update 112",
            PropertyValue.forceOrgasmicDrain
    );

    public static final ToggleOption HEIGHT_MOD = new ToggleOption(
            "HEIGHT_MOD",
            PresetColour.BASE_GREEN_LIME,
            "Apparent Age Height",
            "</br>Calculate Height using Apparent Age or Real Age</br>On = Calculate Height with Age Appearance</br>Off = Calculate Height with Real Age",
            PropertyValue.scaleHeightBasedOnAgeAppearance
    );

    public static final ToggleOption CHANGE_APPARENT_AGE = new ToggleOption(
            "CHANGE_APPARENT_AGE",
            PresetColour.BASE_GREEN_LIME,
            "Change Apparent Age",
            "</br>This option will allow re-randomization of age appearance of encountered NPCs<br>It will be +/- your apparent age range offset",
            PropertyValue.scaleHeightBasedOnAgeAppearance
    );

    public static final SliderOption APPARENT_AGE_OFFSET = new SliderOption(
            "APPARENT_AGE_OFFSET",
            PresetColour.BASE_GREEN_LIME,
            "Apparent Age Range Offset",
            "</br>This adjusts apparent age to be age-this <-> age+this",
            "%VALUE",
            0,
            30,
            1,
            "ageAppearanceRange"
    );
    public static final SliderOption AMORPHOUS_APPARENT_AGE_OFFSET = new SliderOption(
            "AMORPHOUS_APPARENT_AGE_OFFSET",
            PresetColour.BASE_GREEN_LIME,
            "Demon/Slime Apparent Age Range Offset",
            "</br>This adjusts apparent age for demons & slimes to be added to the above Apparent Age Range Offset<br> if the above is 5, and this is 10, appearance will be for a 45 year old would look 30-60 years old",
            "%VALUE",
            0,
            30,
            1,
            "amorphousAgeRange"
    );

    public static final ToggleOption EXCLUDE_AMORPHOUS = new ToggleOption(
            "EXCLUDE_AMORPHOUS",
            PresetColour.BASE_GREEN_LIME,
            "Exclude Shapeshifters from Body Tweaking",
            "</br>When disabled, our body modifications based on age will be applied to shapeshiftable races (Slime/Demon)",
            PropertyValue.excludeAmorphous
    );

    public static final ToggleOption IMP_PREFERENCES = new ToggleOption(
            "IMP_PREFERENCES",
            PresetColour.BASE_GREEN_LIME,
            "Imp Spawn Rates Use Preferences",
            "</br>When enabled, the subspecies weight for imps is based on your preferences",
            PropertyValue.impPreferences
    );

    public static final SliderOption IMP_HEIGHT = new SliderOption("IMP_HEIGHT",
            PresetColour.BASE_CRIMSON,
            "Imp Height",
            "</br>This changes the height required to be classified as an Imp",
            "%UNIT_SIZE",
            1,
            150,
            1,
            "impHeight"
    );

    public static final SliderOption ALPHA_IMP_HEIGHT = new SliderOption("ALPHA_IMP_HEIGHT",
            PresetColour.BASE_CRIMSON,
            "Alpha Imp Height",
            "</br>This changes the height required to be classified as an Alpha Imp",
            "%UNIT_SIZE",
            50,
            200,
            1,
            "alphaImpHeight"
    );

    public static final ToggleOption HEIGHT_GENDERED = new ToggleOption(
            "HEIGHT_GENDERED",
            PresetColour.BASE_GREEN_LIME,
            "Apparent Gender Height",
            "</br>Decide Height using Real Gender or Apparent Gender</br>On = Calculate Height with Apparent Gender</br>Off = Calculate Height with Real Gender",
            PropertyValue.scaleHeightBasedOnGenderOrAppearance
    );
    public static final ToggleOption HEIGHT_RELATIVE = new ToggleOption(
            "HEIGHT_RELATIVE",
            PresetColour.BASE_GREEN_LIME,
            "Relative Height",
            "</br>Multiply Age Adjusted Height based on Racial Height / Human Height<br>" +
                    " We use real world WHO data for heights, this allows fantasy races to be accounted for.",
            PropertyValue.relativeHeight
    );
    public static final ToggleOption BODY_AGE = new ToggleOption(
            "BODY_AGE",
            PresetColour.BASE_GREEN_LIME,
            "Apparent Age Body",
            "</br>Change Body Sizes using Real Age or Apparent Age</br>On = Adjust Body with Apparent Age</br>Off = Adjust Body with Real Age</br> I added this because the idea that they appear as an age, because their physical appearance",
            PropertyValue.scaleBodyBasedOnApparentAge
    );
    public static final ToggleOption BA_BODY = new ToggleOption(
            "BA_BODY",
            PresetColour.BASE_GREEN_LIME,
            "Change Body",
            "</br>Adjust Body Size Relative to Age, Muscle Mass and Size Only",
            PropertyValue.changeBody
    );
    public static final ToggleOption BA_BREASTS = new ToggleOption(
            "BA_BREASTS",
            PresetColour.BASE_GREEN_LIME,
            "Change Breasts",
            "</br>Adjust Breast Size Relative to Age",
            PropertyValue.changeBreasts
    );
    public static final ToggleOption BA_NIPPLE = new ToggleOption(
            "BA_NIPPLE",
            PresetColour.BASE_GREEN_LIME,
            "Change Nipples",
            "</br>Adjust Nipple Size Relative to Breast",
            PropertyValue.changeNipples
    );
    public static final ToggleOption BA_HIPS = new ToggleOption(
            "BA_HIPS",
            PresetColour.BASE_GREEN_LIME,
            "Change Hips",
            "</br>Adjust Hip Size Relative to Age",
            PropertyValue.changeHips
    );
    public static final ToggleOption BA_VAGINA = new ToggleOption(
            "BA_VAGINA",
            PresetColour.BASE_GREEN_LIME,
            "Change Vagina",
            "</br>Adjust Vagina Size Relative to Age",
            PropertyValue.changeVagina
    );
    public static final ToggleOption BA_PENIS = new ToggleOption(
            "BA_PENIS",
            PresetColour.BASE_GREEN_LIME,
            "Change Penis",
            "</br>Adjust Penis Size Relative to Age",
            PropertyValue.changePenis
    );
    public static final ToggleOption BA_TESTICLE = new ToggleOption(
            "BA_TESTICLE",
            PresetColour.BASE_GREEN_LIME,
            "Change Testicle",
            "</br>Adjust Testicle Size Relative to Age",
            PropertyValue.changeTesticle
    );
    public static final ToggleOption BA_THROAT = new ToggleOption(
            "BA_THROAT",
            PresetColour.BASE_GREEN_LIME,
            "Change Throat",
            "</br>Adjust Throat Size Relative to Age",
            PropertyValue.changeThroat
    );
    public static final ToggleOption BA_ASS = new ToggleOption(
            "BA_ASS",
            PresetColour.BASE_GREEN_LIME,
            "Change Ass",
            "</br>Adjust Ass Size Relative to Age",
            PropertyValue.changeAss
    );
    public static final ToggleOption BA_ANUS = new ToggleOption(
            "BA_ANUS",
            PresetColour.BASE_GREEN_LIME,
            "Change Anus",
            "</br>Adjust Anus Size Relative to Age",
            PropertyValue.changeAnus
    );
    public static final ToggleOption BA_URETHRA = new ToggleOption(
            "BA_URETHRA",
            PresetColour.BASE_GREEN_LIME,
            "Change Urethra",
            "</br>Adjust Urethra Size Relative to (Vagina/Penis) size",
            PropertyValue.changeUrethra
    );
    public static final ToggleOption ENABLE_PIERCINGS = new ToggleOption(
            "ENABLE_PIERCINGS",
            PresetColour.BASE_GREEN_LIME,
            "Enable Piercings",
            "</br>Allows randomly generated NPCs to have piercings upon generation",
            PropertyValue.enablePiercings
    );
    public static final ToggleOption ENABLE_TATTOOS = new ToggleOption(
            "ENABLE_TATTOOS",
            PresetColour.BASE_GREEN_LIME,
            "Enable Tattoos",
            "</br>Allows randomly generated NPCs to have tattoos upon generation",
            PropertyValue.enableTattoos
    );
    public static final ToggleOption ENABLE_MAKEUP = new ToggleOption(
            "ENABLE_MAKEUP",
            PresetColour.BASE_GREEN_LIME,
            "Enable Makeup",
            "</br>Allows randomly generated NPCs to have makeup upon generation",
            PropertyValue.enableMakeup
    );

    public static final ToggleOption ENABLE_FRENCH_KISS = new ToggleOption(
            "ENABLE_FRENCH_KISS",
            PresetColour.BASE_PERIWINKLE,
            "Enable French Kissing",
            "</br>Enable French Kissing during sex?",
            PropertyValue.enableFrenchKiss,
            () -> {
                if (ModProps.hasValue(PropertyValue.enableFrenchKiss))
                    Initializers.initFrenchKissActions();
                else Initializers.removeFrenchKissActions();
            }
    );

    public static final ToggleOption ENABLE_TUSSY_CONTENT = new ToggleOption(
            "ENABLE_TUSSY_CONTENT",
            PresetColour.BASE_RED_DARK,
            "Enable Tussy Content",
            "</br>Enables Tussy Actions during sex?",
            PropertyValue.enableTussyContent,
            () -> {
                if (ModProps.isTussyEnabled()) // Add && Configurations.LEXI_CONTENT ? + note in description
                    Initializers.initTussyActions();
                else Initializers.removeTussyActions();
            }
    );
    public static final ToggleOption ENABLE_EXTRA_SELF_CONTENT = new ToggleOption(
            "ENABLE_EXTRA_SELF_CONTENT",
            PresetColour.BASE_RED_DARK,
            "Enable Additional Self-fuck Content",
            "</br>Adds previously unimplemented self-fuck actions. Does nothing if Archer's content is disabled.",
            PropertyValue.enableExtraSelfContent,
            () -> {
                if (ModProps.isExtraSelfContentEnabled() && Configurations.ARCHER_CONTENT)
                    Initializers.initExtraSelfActions();
                else Initializers.removeExtraSelfActions();
            }
    );
    public static final ToggleOption ENABLE_SEX_IMAGES = new ToggleOption(
            "ENABLE_SEX_IMAGES",
            PresetColour.BASE_PERIWINKLE,
            "Enable Sex Images",
            "</br>Enable character images during sex?",
            PropertyValue.enableSexImages
    );
    public static final ToggleOption ALWAYS_MODDED = new ToggleOption(
            "ALWAYS_MODDED",
            PresetColour.BASE_PINK,
            "Flag Imported Characters",
            "</br>When enabled, any characters imported as slave will be flagged as already modified by us and not get tweaked.",
            PropertyValue.importedFlagAsModded
    );
    public static final ToggleOption PREVENT_GIANTS = new ToggleOption(
            "PREVENT_GIANTS",
            PresetColour.BASE_PINK,
            "Prevent Giants",
            "</br>When enabled, any characters will not have their modified heights be larger than Innoxia generated.",
            PropertyValue.preventGiants
    );
    public static final ToggleOption ENABLE_PERSONALITY = new ToggleOption(
            "ENABLE_PERSONALITY",
            PresetColour.BASE_GREY_LIGHT,
            "Enable Personality Selection",
            "</br>When enabled, the personality selection modal will appear during character creation.</br>Default: ON",
            PropertyValue.enablePersonality
    );

    /* SLIDERS */
    public static final SliderOption MAX_ENCHANTMENTS = new SliderOption("MAX_ENCHANTMENTS",
            PresetColour.BASE_BLUE,
            "Max Enchantments",
            "</br>This changes the max number of enchantments you can put on an item",
            "%VALUE",
            1,
            50,
            5,
            "maxEnchantments"
    );
    public static final SliderOption SHOTA_AGE = new SliderOption("SHOTA_AGE",
            PresetColour.BASE_BLUE_LIGHT,
            "Shota Age",
            "</br>This is the oldest anyone will be labeled \"Shota\" when showing age in encounter names",
            "%VALUE",
            1,
            1,
            16,
            1,
            "shotaLabelAge"
    );
    public static final SliderOption IMPORT_AGE = new SliderOption(
            "IMPORT_AGE",
            PresetColour.BASE_BLUE_DARK,
            "Imported Age Offset",
            "</br>Age offset for imported characters",
            "%VALUE",
            -18,
            -18,
            18,
            1,
            "importAgeOffset"
    );
    
    public static final SliderOption LOLI_AGE = new SliderOption("LOLI_AGE",
            PresetColour.BASE_PINK_LIGHT,
            "Loli Age",
            "</br>This is the oldest anyone will be labeled \"Loli\" when showing age in encounter names",
            "%VALUE",
            1,
            1,
            16,
            1,
            "loliLabelAge"
    );
    public static final SliderOption YOUNG_AGE = new SliderOption("YOUNG_AGE",
            PresetColour.BASE_GREEN_LIGHT,
            "Young Age",
            "</br>This is the oldest anyone will be labeled \"young\" when showing age in encounter names",
            "%VALUE",
            1,
            1,
            20,
            1,
            "youngLabelAge"
    );
    public static final SliderOption OLD_AGE = new SliderOption("OLD_AGE",
            PresetColour.BASE_GREEN_LIGHT,
            "Old Age",
            "</br>This is the youngest anyone will be labeled \"Old\" when showing age in encounter names",
            "%VALUE",
            18,
            18,
            80,
            1,
            "oldLabelAge"
    );
//    public static final SliderOption DEMON_CHANCE = new SliderOption(
//            "DEMON_CHANCE",
//            PresetColour.BASE_GREEN,
//            "Demons Everywhere Spawn Chance",
//            "</br>This adjusts the chance of demons spawning outside of Dark Alleyways",
//            "%VALUE%",
//            0,
//            20,
//            5,
//            "demonsEverywhereChance"
//    );
    public static final SliderOption XP_MULT = new SliderOption(
            "XP_MULT",
            PresetColour.BASE_GREEN,
            "XP Multiplier",
            "</br>This increases the amount of xp you gain from victories in combat",
            "%VALUE%",
            0,
            50,
            25,
            "xpMultiplier"
    );
    public static final SliderOption ESS_MULT = new SliderOption(
            "ESS_MULT",
            PresetColour.BASE_PURPLE,
            "Essences Multiplier",
            "</br>This increases the amount of Essences you get from sexual interactions",
            "%VALUE%",
            0,
            50,
            25,
            "essenceMultiplier"
    );
    public static final SliderOption MONEY_MULT = new SliderOption(
            "MONEY_MULT",
            PresetColour.BASE_GOLD,
            "Money Multiplier",
            "</br>This increases the amount of Money you get from victories in combat",
            "%VALUE%",
            0,
            50,
            25,
            "moneyMultiplier"
    );
    public static final ToggleOption ENABLE_MULTI_INCOME = new ToggleOption(
            "ENABLE_MULTI_INCOME",
            PresetColour.BASE_GREY_LIGHT,
            "Enable Money Multiplier for Income",
            "</br>When enabled, the money multiplier above will apply to income from self-prostitution and milk selling.",
            PropertyValue.applyMoneyMultToIncome
    );
    public static final SliderOption INTEREST_MULT = new SliderOption(
            "INTEREST_MULT",
            PresetColour.BASE_GOLD,
            "Interest Multiplier",
            "</br>This increases the amount of interest gained at the bank",
            "%VALUE%",
            0,
            50,
            4,
            "interestMultiplier"
    );
    public static final SliderOption DROPS_MULT = new SliderOption(
            "DROPS_MULT",
            PresetColour.BASE_TAN,
            "Drops Multiplier",
            "</br>This increases the amount of Items you get from victories in combat",
            "%VALUE",
            0,
            20,
            1,
            "itemDropsIncrease"
    );

    public static final SliderOption MAX_LEVEL = new SliderOption(
            "MAX_LEVEL",
            PresetColour.BASE_BLUE,
            "Character Max Level",
            "</br>This changes the max level of characters",
            "%VALUE",
            1,
            100,
            5,
            "maxLevel"
    );
    public static final SliderOption FUCKABLE_CUP_SIZE = new SliderOption(
            "FUCKABLE_CUP_SIZE",
            PresetColour.BASE_GREEN_LIME,
            "Fuckable cup size",
            "</br>This sets the minimum breast cup size requirement for nipples to be considered fuckable (other requirements still have to be met.)" +
                    "</br>Flat = 0, A = 5, C (vanilla) = 7, E = 10, H = 15" +
                    "</br>This option does nothing if nipple penetration is disabled.",
            "%VALUE",
            0,
            15,
            1,
            "minimumFuckableCupSize"
    );
    public static final SliderOption PLAYER_PREG_DUR = new SliderOption(
            "PLAYER_PREG_DUR",
            PresetColour.BASE_PINK_LIGHT,
            "Player Pregnancy Duration",
            "</br>This is a Percent multiplier to player pregnancy duration.",
            "%VALUE%",
            10,
            1,
            50,
            10,
            "playerPregDuration"
    );
    public static final SliderOption NPC_PREG_DUR = new SliderOption(
            "NPC_PREG_DUR",
            PresetColour.BASE_PINK_LIGHT,
            "NPC Pregnancy Duration",
            "</br>This is a Percent multiplier to NPC pregnancy duration.",
            "%VALUE%",
            5,
            50,
            10,
            "NPCPregDuration"
    );
    public static final SliderOption NPC_PREG_CHANCE = new SliderOption(
            "NPC_PREG_CHANCE",
            PresetColour.BASE_PINK_LIGHT,
            "NPC Pregnancy Chance",
            "</br>This is the percent of randomly generated NPCs that are generated with an active pregnancy.",
            "%VALUE%",
            0,
            20,
            5,
            "NPCPregChance"
    );
    public static final SliderOption MAX_PERKS = new SliderOption(
            "MAX_PERKS",
            PresetColour.BASE_GREY_DARK,
            "Max Perks",
            "</br>Maximum number of major perks you can have at once.<br/>Note: There are only 20 perks in game right now.",
            "%VALUE",
            1,
            1,
            25,
            1,
            "maxPerks"
    );
    public static final SliderOption ENCH_STAB_BASE = new SliderOption(
            "ENCH_STAB_BASE",
            PresetColour.BASE_PURPLE_LIGHT,
            "Stability Limit Base",
            "</br>Increases your Enchantment Instability Limit<br/>This is just a flat baseline bump",
            "%VALUE",
            10,
            1,
            250,
            10,
            "enchantmentInstabilityBonus"
    );
    public static final SliderOption ENCH_STAB_LIMIT = new SliderOption(
            "ENCH_STAB_LIMIT",
            PresetColour.BASE_PURPLE_LIGHT,
            "Stability Absolute Limit",
            "</br>Increases the Enchantment Instability Absolute upper Limit<br> This is how much instability cap you can have, going beyond the cap still debuffs you.",
            "%VALUE",
            1000,
            1,
            2000,
            1000,
            "enchantmentInstabilityUpperLimit"
    );
    public static final SliderOption ENCH_STAB_PER_LEVEL = new SliderOption(
            "ENCH_STAB_PER_LEVEL",
            PresetColour.BASE_PURPLE_LIGHT,
            "Stability Limit Per Level",
            "</br>Increases your Enchantment Instability Limit<br/>This is multiplied by your Level",
            "%VALUE",
            1,
            1,
            25,
            1,
            "enchantmentInstabilityPerLevel"
    );
    public static final SliderOption ENCH_STAB_PER_PHYS = new SliderOption(
            "ENCH_STAB_PER_PHYS",
            PresetColour.BASE_PURPLE_LIGHT,
            "Stability Limit Per Physique",
            "</br>Increases your Enchantment Instability Limit<br/>This is multiplied by your Physique",
            "%VALUE",
            1,
            1,
            25,
            1,
            "enchantmentInstabilityPerPhys"
    );
    public static final SliderOption ENCH_STAB_PER_ARC = new SliderOption(
            "ENCH_STAB_PER_ARC",
            PresetColour.BASE_PURPLE_LIGHT,
            "Stability Limit Per Arcane",
            "</br>Increases your Enchantment Instability Limit<br/>This is multiplied by your Arcane",
            "%VALUE",
            1,
            1,
            25,
            1,
            "enchantmentInstabilityPerArcane"
    );
    public static final SliderOption ENCH_STAB_PER_CORR = new SliderOption(
            "ENCH_STAB_PER_CORR",
            PresetColour.BASE_PURPLE_LIGHT,
            "Stability Limit Per Corruption",
            "</br>Increases your Enchantment Instability Limit<br/>This is multiplied by your Corruption",
            "%VALUE",
            1,
            1,
            25,
            1,
            "enchantmentInstabilityPerCorruption"
    );
    public static final SliderOption ESSENCE_STOCK = new SliderOption(
            "ESSENCE_STOCK",
            PresetColour.BASE_PINK,
            "Essence Stock",
            "</br>How many of each essence Vicky has in her shop per day</br>Actual value will be between this value and 2x this value",
            "%VALUE",
            0,
            300,
            10,
            "essenceStock"
    );
    public static final SliderOption ENC_COST_MULT = new SliderOption(
            "ENC_COST_MULT",
            PresetColour.BASE_TAN,
            "Enchanting Cost Multiplier",
            "</br>This increases/decreases the cost of enchanting",
            "%VALUE%",
            0,
            50,
            25,
            "encCostMultiplier"
    );
    public static final SliderOption POTENCY_MINOR = new SliderOption(
            "POTENCY_MINOR",
            PresetColour.BASE_PURPLE_LIGHT,
            "Minor Potency",
            "</br>How much the bonus is for Minor Enchantments",
            "%VALUE",
            1,
            1,
            25,
            1,
            "encMinorPotency"
    );
    public static final SliderOption POTENCY_MEDIUM = new SliderOption(
            "POTENCY_MEDIUM",
            PresetColour.BASE_PURPLE_LIGHT,
            "Medium Potency",
            "</br>How much the bonus is for Medium Enchantments",
            "%VALUE",
            2,
            2,
            50,
            1,
            "encMediumPotency"
    );
    public static final SliderOption POTENCY_MAJOR = new SliderOption(
            "POTENCY_MAJOR",
            PresetColour.BASE_PURPLE_LIGHT,
            "Major Potency",
            "</br>How much the bonus is for Major Enchantments",
            "%VALUE",
            3,
            3,
            75,
            1,
            "encMajorPotency"
    );
    public static final SliderOption VALUE_MINOR = new SliderOption(
            "VALUE_MINOR",
            PresetColour.BASE_PURPLE_LIGHT,
            "Minor Potency Value",
            "</br>How much the value is for Minor Enchantments",
            "%VALUE",
            1,
            1,
            25,
            1,
            "encMinorValue"
    );
    public static final SliderOption VALUE_MEDIUM = new SliderOption(
            "VALUE_MEDIUM",
            PresetColour.BASE_PURPLE_LIGHT,
            "Medium Potency Value",
            "</br>How much the value is for Medium Enchantments",
            "%VALUE",
            2,
            2,
            50,
            1,
            "encMediumValue"
    );
    public static final SliderOption VALUE_MAJOR = new SliderOption(
            "VALUE_MAJOR",
            PresetColour.BASE_PURPLE_LIGHT,
            "Major Potency Value",
            "</br>How much the value is for Major Enchantments",
            "%VALUE",
            3,
            3,
            75,
            1,
            "encMajorValue"
    );
    public static SliderOption MINIMUM_AGE;

    static {
        initMinAge();
    }

    public static void initMinAge() {
        MINIMUM_AGE = new SliderOption(
                "MINIMUM_AGE",
                PresetColour.BASE_BLUE_LIGHT,
                "Minimum Age",
                "</br>This is the Lowest Age anyone can be (player included)",
                "%VALUE",
                Configurations.DEGEN_MODE ? 1 : 18,
                Configurations.DEGEN_MODE ? 1 : 18,
                25,
                1,
                "minAge"
        );
    }

    public static final SliderOption MAXIMUM_AGE = new SliderOption(
            "MAXIMUM_AGE",
            PresetColour.BASE_ORANGE_LIGHT,
            "Maximum Age",
            "</br>This is the Highest Age anyone can be (player included)",
            "%VALUE",
            Configurations.DEGEN_MODE ? 2 : 18,
            Configurations.DEGEN_MODE ? 2 : 18,
            100,
            1,
            "maxAge"
    );
   /* public static final SliderOption OFFSPRING_AGE = new SliderOption(
            "OFFSPRING_AGE",
            PresetColour.BASE_BLUE_LIGHT,
            "Minimum Age For Offspring",
            "</br>This is the lowest age an offspring can spawn at",
            "%VALUE",
            1,
            1,
            20,
            1,
            "offspringAge"
    );*/
    public static final SliderOption SHOTA_ENDOWMENT = new SliderOption(
            "SHOTA_ENDOWMENT",
            PresetColour.BASE_BLUE,
            "Shota Endowment",
            "</br>This is a % adjustment to size and cum storage/production and virility for underage characters with a penis</br>set to -100 if you want never virile.",
            "%VALUE%",
            -100,
            -10,
            10,
            10,
            "shotaEndowment"
    );
    public static final SliderOption OPPAI_LOLI = new SliderOption(
            "OPPAI_LOLI",
            PresetColour.BASE_PINK_DEEP,
            "Big Breasted Lolis",
            "</br>This is a % chance to add additional cupsizes for all underage characters that spawn with breasts.",
            "%VALUE%",
            0,
            20,
            5,
            "loliOppai"
    );
    public static final SliderOption LOLI_FERTILITY = new SliderOption(
            "LOLI_FERTILITY",
            PresetColour.BASE_PINK,
            "Loli Fertility",
            "</br>This percent is the percent of lolis that will spawn with natural fertility</br>set to -100 if you want never fertile.",
            "%VALUE%",
            -100,
            -10,
            10,
            10,
            "loliFertility"
    );
    /*public static final SliderOption VIRGIN_LOVER = new SliderOption(
            "VIRGIN_LOVER",
            PresetColour.BASE_PINK_LIGHT,
            "Virgin Probability",
            "</br>This percent is the percent of people who spawn as a virgin",
            "%VALUE%",
            0,
            10,
            5,
            "virginsPercent"
    );*/
    public static final SliderOption IMP_HMULT = new SliderOption(
            "IMP_HMULT",
            PresetColour.BASE_BROWN,
            "Imp Height Multiplier",
            "</br>This is multiplied by the usual height calculations for imps",
            "%VALUE%",
            15,
            3,
            20,
            5,
            "impHMult"
    );
    public static final SliderOption A_IMP_HMULT = new SliderOption(
            "A_IMP_HMULT",
            PresetColour.BASE_ORANGE,
            "Alpha Imp Height Multiplier",
            "</br>This is multiplied by the usual height calculations for alpha imps",
            "%VALUE%",
            25,
            5,
            25,
            5,
            "aimpHMult"
    );
    public static final SliderOption HEIGHT_DEVIATION = new SliderOption(
            "HEIGHT_DEVIATION",
            PresetColour.BASE_BLUE_LIGHT,
            "Height Deviation",
            "</br>This changes the base heights in cm",
            "%UNIT_SIZE",
            -50,
            -10,
            10,
            5,
            "heightDeviations"
    );
    public static final SliderOption HEIGHT_AGE_CAP = new SliderOption(
            "HEIGHT_AGE_CAP",
            PresetColour.BASE_BLUE_STEEL,
            "Height Calculated Age Cap",
            "</br>This changes what age npcs are considered to be as tall as they can get. </br>In real life this is between 15 and 16",
            "%VALUE",
            5,
            5,
            20,
            1,
            "heightAgeCap"
    );
    public static final SliderOption HETEROCHROMIA_CHANCE = new SliderOption(
            "HETEROCHROMIA_CHANCE",
            PresetColour.BASE_GREY,
            "Heterochromia Chance",
            "</br>This is the percentage of randomized NPCs that will spawn with heterochromic eyes.",
            "%VALUE%",
            4,
            1,
            24,
            4,
            "heterochromiaChance"
    );
    public static final SliderOption ORGASMIC_LEVEL_DRAIN_CHANCE = new SliderOption(
            "ORGASMIC_LEVEL_DRAIN_CHANCE",
            PresetColour.LUST_STAGE_FOUR,
            "Orgasmic Level Drain Chance",
            "</br>This is the percentage of randomized NPCs that will spawn with the Orgasmic Level Drain perk.",
            "%VALUE%",
            0,
            20,
            5,
            "orgasmicLevelDrainChance"
    );
    public static final SliderOption CHUUNI_CHANCE = new SliderOption(
            "CHUUNI_CHANCE",
            PresetColour.BASE_BLUE,
            "Chuuni Chance",
            "</br>This is the percentage of randomized NPCs that will spawn with the Chuuni perk.",
            "%VALUE%",
            0,
            20,
            5,
            "chuuniChance"
    );

    /* CHALLENGE OPTIONS */
    public static final SliderOption INVENTORY_TABS = new SliderOption(
            "INVENTORY_TABS",
            PresetColour.BASE_GREY,
            "Inventory Pages*",
            "</br>Limit the number of inventory pages you have access to.</br>Default: 5",
            "%VALUE",
            1,
            1,
            5,
            1,
            "inventoryTabs"
    );
    public static final SliderOption INVENTORY_ROWS = new SliderOption(
            "INVENTORY_ROWS",
            PresetColour.BASE_GREY,
            "Inventory Rows*",
            "</br>Limit the number of inventory rows per page.</br>Default: 5",
            "%VALUE",
            1,
            1,
            5,
            1,
            "inventoryRows"
    );
}