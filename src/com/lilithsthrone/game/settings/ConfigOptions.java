package com.lilithsthrone.game.settings;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

public class ConfigOptions {
    /* Multi-Slider Options */
    public static final MultiSliderOption PREGNANCY_LACTATION = new MultiSliderOption(
            "PREGNANCY_LACTATION",
            PresetColour.BASE_YELLOW,
            "怀孕平均泌乳量",
            "乳房",
            "乳头乳区",
            "设置角色每次怀孕所能获得的<b>平均</b>泌乳量增加。实际增长值会在该值的±" + Units.fluid(Main.getProperties().pregnancyLactationIncreaseVariance) + "范围内波动。",
            "%UNIT_VOLUME",
            0,
            20,
            50,
            "pregnancyLactationIncrease",
            "pregnancyUdderLactationIncrease"
    );
    public static final MultiSliderOption PREGNANCY_LACTATION_LIMIT = new MultiSliderOption(
            "PREGNANCY_LACTATION_LIMIT",
            PresetColour.BASE_YELLOW_LIGHT,
            "怀孕泌乳上限",
            "乳房",
            "乳头乳区",
            "设置角色从怀孕中获得的泌乳量上限。",
            "%UNIT_VOLUME",
            0,
            Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue(),
            50,
            "pregnancyLactationLimit",
            "pregnancyUdderLactationLimit"
    );
    public static final MultiSliderOption BREAST_SIZE_PREFERENCE = new MultiSliderOption(
            "BREAST_SIZE_PREFERENCE",
            PresetColour.NIPPLES,
            "罩杯尺寸偏好",
            "乳房",
            "乳头乳区",
            "影响随机生成 NPC 的罩杯大小（不会减小到 AA 罩杯以下）。",
            "%VALUE",
            -20,
            -20,
            20,
            1,
            "breastSizePreference",
            "udderSizePreference"
    );
    public static final MultiSliderOption PREGNANCY_BREAST_GROWTH = new MultiSliderOption(
            "PREGNANCY_BREAST_GROWTH",
            PresetColour.BASE_PINK,
            "怀孕平均胸部增长",
            "乳房",
            "乳头乳区",
            "设置角色每次怀孕所能获得的<b>平均</b>罩杯增长。实际胸部增长会在该值的±"+Util.intToString(Main.getProperties().pregnancyBreastGrowthVariance)+"个尺寸范围内波动。",
            "%VALUE cup$",
            0,
            10,
            1,
            "pregnancyBreastGrowth",
            "pregnancyUdderGrowth"
    );

    public static final MultiSliderOption PREGNANCY_BREAST_GROWTH_LIMIT = new MultiSliderOption(
            "PREGNANCY_BREAST_GROWTH_LIMIT",
            PresetColour.BASE_PINK_LIGHT,
            "怀孕胸部增长上限",
            "乳房",
            "乳头乳区",
            "设置角色乳房从怀孕中增长到的罩杯上限。",
            "%CUP_SIZE",
            0,
            CupSize.getCupSizeFromInt(100).getMeasurement(),
            1,
            "pregnancyBreastGrowthLimit",
            "pregnancyUdderGrowthLimit"
    );

    /* SLIDER OPTIONS */
    public static final SliderOption PREGNANCY_DURATION = new SliderOption(
            "PREGNANCY_DURATION",
            PresetColour.BASE_PINK_DEEP,
            "怀孕时长",
            "设置怀孕从受孕到分娩所需的最长时间。",
            "%VALUE week$",
            1,
            40,
            1,
            "pregnancyDuration");

    public static final SliderOption FORCED_TF = new SliderOption(
            "FORCED_TF",
            PresetColour.TRANSFORMATION_GENERIC,
            "强制变身",
            "设置携带「'"+ Fetish.FETISH_TRANSFORMATION_GIVING.getName(null)+"'」癖好、在你于战斗中落败后强制改造你身体的 NPC 生成比例。",
            "%VALUE%",
            0,
            20,
            5,
            "forcedTFPercentage");

    public static final SliderOption FORCED_FETISH = new SliderOption(
            "FORCED_FETISH",
            PresetColour.FETISH,
            "强制癖好",
            "设置携带「'"+Fetish.FETISH_KINK_GIVING.getName(null)+"'」癖好、在你于战斗中落败后会尝试强行赋予你癖好的 NPC 生成比例。",
            "%VALUE%",
            0,
            100,
            5,
            "forcedFetishPercentage");

    public static final SliderOption PENIS_SIZE_PREFERENCE = new SliderOption(
            "PENIS_SIZE_PREFERENCE",
            PresetColour.PENIS,
            "阴茎尺寸偏好",
            "影响随机生成 NPC 的阴茎尺寸（不会减小到"+Units.size(8)+"以下）。",
            "%UNIT_SIZE",
            -20,
            -20,
            20,
            1,
            "penisSizePreference");

    public static final SliderOption TRAP_PENIS_SIZE_PREFERENCE = new SliderOption(
            "TRAP_PENIS_SIZE_PREFERENCE",
            PresetColour.BASE_PINK_LIGHT,
            Util.capitaliseSentence(Gender.N_P_TRAP.getName())+"的阴茎尺寸",
            "随机生成的"+Gender.N_P_TRAP.getName()+"的阴茎尺寸。100% 表示未经改动的尺寸。睾丸大小和精液产量也会随此设置按比例改变。",
            "%VALUE%",
            -90,
            -9,
            10,
            10,
            "trapPenisSizePreference");

    /* TOGGLE OPTIONS */
    public static final ToggleOption AUTO_LOCALE = new ToggleOption(
            "AUTO_LOCALE",
            PresetColour.BASE_BLUE_LIGHT,
            "自动",
            "启用后使用系统区域设置。否则将应用以下设置。",
            PropertyValue.autoLocale,
            () -> {
                Units.FORMATTER.updateSettings();
                Units.FORMATTER.updateFormats(!Main.getProperties().hasValue(PropertyValue.autoLocale));
            }
    );

    public static final ToggleOption METRIC_SIZES = new ToggleOption(
            "METRIC_SIZES",
            PresetColour.BASE_BLUE_STEEL,
            "公制尺寸",
            "游戏将使用米和厘米，而不是英尺和英寸。",
            PropertyValue.metricSizes,
            MainController::overrideAutoLocale
    );

    public static final ToggleOption METRIC_FLUIDS = new ToggleOption(
            "METRIC_FLUIDS",
            PresetColour.BASE_BLUE_STEEL,
            "公制液体",
            "游戏将使用升和毫升，而不是加仑和盎司。",
            PropertyValue.metricFluids,
            MainController::overrideAutoLocale
    );

    public static final ToggleOption METRIC_WEIGHTS = new ToggleOption(
            "METRIC_WEIGHTS",
            PresetColour.BASE_BLUE_STEEL,
            "公制重量",
            "游戏将使用千克和克，而不是磅和盎司。",
            PropertyValue.metricWeights,
            MainController::overrideAutoLocale
    );

    public static final ToggleOption TWENTYFOUR_HOUR_TIME = new ToggleOption(
            "TWENTYFOUR_HOUR_TIME",
            PresetColour.BASE_LILAC_LIGHT,
            "24 小时制",
            "时间将以 24 小时制显示，而不是上午/下午。",
            PropertyValue.twentyFourHourTime,
            () -> {
                MainController.overrideAutoLocale();
                Units.FORMATTER.updateTimeFormat(false);
            }
    );

    public static final ToggleOption INTERNATIONAL_DATE = new ToggleOption(
            "INTERNATIONAL_DATE",
            PresetColour.BASE_LILAC_LIGHT,
            "国际日期格式",
            "缩写日期将以日.月.年 的形式显示，而非 月/日/年。",
            PropertyValue.internationalDate,
            () -> {
                MainController.overrideAutoLocale();
                Units.FORMATTER.updateDateFormat(false);
            }
    );

    public static final ToggleOption ARTWORK = new ToggleOption(
            "ARTWORK",
            PresetColour.BASE_BLUE_LIGHT,
            "立绘",
            "在角色的信息界面中显示立绘。",
            PropertyValue.artwork
    );

    public static final ToggleOption THUMBNAIL = new ToggleOption(
            "THUMBNAIL",
            PresetColour.BASE_BLUE_STEEL,
            "缩略图",
            "启用包含角色缩略图图片的工具提示。",
            PropertyValue.thumbnail
    );

    public static final ToggleOption SHARED_ENCYCLOPEDIA = new ToggleOption(
            "SHARED_ENCYCLOPEDIA",
            PresetColour.GENERIC_EXCELLENT,
            "共享图鉴",
            "启用后，你的角色将使用共享图鉴（其条目可在任何一次游玩中被解锁）。若禁用，则只有在当前角色已发现图鉴条目时才会显示。",
            PropertyValue.sharedEncyclopedia
    );

    public static final ToggleOption WEATHER_INTERRUPTION = new ToggleOption(
            "WEATHER_INTERRUPTION",
            PresetColour.GENERIC_ARCANE,
            "暴风打断",
            "启用后，奥术风暴会打断对话，以告知你风暴已经来临。",
            PropertyValue.weatherInterruptions
    );

    public static final ToggleOption DIALOGUE_COPY = new ToggleOption(
            "DIALOGUE_COPY",
            PresetColour.BASE_BLUE_STEEL,
            "自动复制文本",
            "启用后，每次加载新场景时，当前场景的文本都会自动复制到你的系统剪贴板中。此选项是为了让你无需每次都手动选中并复制场景文本，便可轻松将游戏文本粘贴到文本阅读器中。",
            PropertyValue.automaticDialogueCopy
    );

    public static final ToggleOption SILLY = new ToggleOption(
            "SILLY",
            PresetColour.GENERIC_GOOD,
            "搞笑模式",
            "这会启用整个游戏中的趣味风味文本。",
            PropertyValue.sillyMode
    );

    public static final ToggleOption ENCHANTMENT_LIMITS = new ToggleOption(
            "ENCHANTMENT_LIMITS",
            PresetColour.GENERIC_ARCANE,
            "附魔容量",
            "切换「附魔容量」机制，该机制会限制你所能穿戴的附魔物品数量。此选项默认开启，若将其关闭，你可能会破坏游戏战斗的平衡。",
            PropertyValue.enchantmentLimits
    );

    public static final ToggleOption BAD_END = new ToggleOption(
            "BAD_END",
            PresetColour.GENERIC_TERRIBLE,
            "坏结局",
            "切换触发「坏结局」的能力，坏结局会在触发时为你的角色终结游戏。"
                    + "<br/>[style.italicsMinorBad(请注意，坏结局涉及非自愿内容，因此会忽略你的非自愿设置。)]",
//							+"<br/>[style.italicsTerrible(Please be aware that some bad ends are unaffected by this setting and are always present in the game.)]"
            PropertyValue.badEndContent
    );

    public static final ToggleOption LEVEL_DRAIN = new ToggleOption(
            "LEVEL_DRAIN",
            PresetColour.GENERIC_TERRIBLE,
            "等级吸取",
            "切换特殊 NPC（例如与琥珀的某些场景）使用的「高潮等级吸取」天赋。该天赋会在你与之发生性关系的每次高潮中吸取你的等级。",
            PropertyValue.levelDrain
    );

    public static final ToggleOption OPPORTUNISTIC_ATTACKERS = new ToggleOption(
            "OPPORTUNISTIC_ATTACKERS",
            PresetColour.BASE_CRIMSON,
            "机会主义袭击者",
            "这会使随机袭击在你纵欲值高、生命值低、沾满液体、暴露或醉酒时更有可能发生。",
            PropertyValue.opportunisticAttackers
    );

    public static final ToggleOption OFFSPRING_ENCOUNTERS = new ToggleOption(
            "OFFSPRING_ENCOUNTERS",
            PresetColour.BASE_INDIGO,
            "后代遭遇",
            "这使你能在世界的各个角落随机遇到你的后代。<br/><i>此设置对后代地图以及你已经遇到的后代没有影响。</i>",
            PropertyValue.offspringEncounters
    );

    public static final ToggleOption SPITTING_ENABLED = new ToggleOption(
            "SPITTING_ENABLED",
            PresetColour.BASE_BLUE,
            "抗拒变身药水",
            "若启用此选项，强制变身药水可能会被吐出。",
            PropertyValue.spittingEnabled
    );

    public static final ToggleOption COMPANION = new ToggleOption(
            "COMPANION",
            PresetColour.BASE_GREEN_LIGHT,
            "同伴",
            "启用将奴隶或友好居民添加为同伴的能力。<br/>[style.boldBad(警告：)] 这是一个实验性功能，而且在 v0.3.9 中已停止对同伴提供支持，因此除 Dominion 之外，不会有任何涉及你同伴的特殊对话或动作。",
            PropertyValue.companionContent
    );

    public static final ToggleOption NON_CON = new ToggleOption(
            "NON_CON",
            PresetColour.BASE_CRIMSON,
            "非自愿",
            "这会在性场面中启用「抵抗」节奏，其中包含一些更极端的非自愿描述，以及与这些内容相关的对话引用和动作。<br/>[style.italicsMinorBad(请注意，无论此选项是否启用，坏结局都会涉及非自愿内容。)]",
            PropertyValue.nonConContent
    );

    public static final ToggleOption SADISTIC_SEX = new ToggleOption(
            "SADISTIC_SEX",
            PresetColour.BASE_RED,
            "施虐性行为",
            "这会解锁「施虐」性行为动作，例如在性生活中掐住伙伴、扇巴掌以及朝伙伴吐口水。",
            PropertyValue.sadisticSexContent
    );

    public static final ToggleOption LIPSTICK_MARKING = new ToggleOption(
            "LIPSTICK_MARKING",
            PresetColour.BASE_RED_DARK,
            "唇印标记",
            "这会在性爱过程中通过亲吻在身体部位上留下口红印记。",
            PropertyValue.lipstickMarkingContent
    );

    public static final ToggleOption VOLUNTARY_NTR = new ToggleOption(
            "VOLUNTARY_NTR",
            PresetColour.GENERIC_MINOR_BAD,
            "自愿 NTR",
            "启用后，你将有机会提议让某些敌人与你的同伴发生性关系，以作为避免战斗的一种方式。",
            PropertyValue.voluntaryNTR
    );

    public static final ToggleOption INVOLUNTARY_NTR = new ToggleOption(
            "INVOLUNTARY_NTR",
            PresetColour.GENERIC_BAD,
            "非自愿 NTR",
            "启用后，敌人在战斗中击败你的队伍后，可能会选择只与你的同伴发生性关系。禁用时，所有战败后的性场面都会涉及到你。",
            PropertyValue.involuntaryNTR
    );

    public static final ToggleOption INCEST = new ToggleOption(
            "INCEST",
            PresetColour.BASE_ROSE,
            "乱伦",
            "这将启用彼此有亲属关系的角色之间的性行为动作。",
            PropertyValue.incestContent
    );

    public static final ToggleOption LACTATION = new ToggleOption(
            "LACTATION",
            PresetColour.BASE_YELLOW_LIGHT,
            "泌乳",
            "这会启用泌乳内容。",
            PropertyValue.lactationContent
    );

    public static final ToggleOption SEXUAL_UDDERS = new ToggleOption(
            "SEXUAL_UDDERS",
            PresetColour.BASE_ORANGE_LIGHT,
            "耻部乳房与乳区内容",
            "这会启用与耻部乳房及乳区相关的性行为动作，并允许对玩家施加耻部乳房与乳区变身。",
            PropertyValue.udderContent
    );

    public static final ToggleOption URETHRAL = new ToggleOption(
            "URETHRAL",
            PresetColour.BASE_PINK_DEEP,
            "尿道内容",
            "这会启用尿道变身与插入。",
            PropertyValue.urethralContent
    );

    public static final ToggleOption NIPPLE_PEN = new ToggleOption(
            "NIPPLE_PEN",
            PresetColour.BASE_PINK_DEEP,
            "乳头插入",
            "这会启用乳头插入变身与性行为动作。",
            PropertyValue.nipplePenContent
    );

    public static final ToggleOption ANAL = new ToggleOption(
            "ANAL",
            PresetColour.BASE_ORANGE,
            "肛交内容",
            "禁用后，会移除性生活中所有与肛交相关的动作。",
            PropertyValue.analContent
    );

    public static final ToggleOption GAPE = new ToggleOption(
            "GAPE",
            PresetColour.BASE_PINK_DEEP,
            "扩张内容",
            "禁用后，会将张开孔洞的描述改为简单的「松弛」，并隐藏任何特殊的扩张相关内容。",
            PropertyValue.gapeContent
    );

    public static final ToggleOption PENETRATION_LIMITATION = new ToggleOption(
            "PENETRATION_LIMITATION",
            PresetColour.BASE_PINK_DEEP,
            "插入尺寸差",
            "启用后，孔洞将具有有限的深度，这意味着插入物（阴茎和尾巴）可能过长，无法完全进入内部。",
            PropertyValue.penetrationLimitations
    );

    public static final ToggleOption PENETRATION_LIMITATION_DYNAMIC = new ToggleOption(
            "PENETRATION_LIMITATION_DYNAMIC",
            PresetColour.BASE_PINK_DEEP,
            "弹性深度效果",
            "启用后，若孔洞的弹性达到「柔软」或更高，最大「不舒适深度」值将会增加，弹性值越高，增加的幅度越大。（注意：仅在「插入尺寸差」同时开启时生效。）",
            PropertyValue.elasticityAffectDepth
    );

    public static final ToggleOption FOOT = new ToggleOption(
            "FOOT",
            PresetColour.BASE_TAN,
            "足部内容",
            "禁用后，会移除性生活中所有与足部相关的动作。",
            PropertyValue.footContent
    );

    public static final ToggleOption ARMPIT = new ToggleOption(
            "ARMPIT",
            PresetColour.BASE_PINK_LIGHT,
            "腋下内容",
            "禁用后，会移除性生活中所有与腋下相关的动作。",
            PropertyValue.armpitContent
    );

    public static final ToggleOption MUSK = new ToggleOption(
            "MUSK",
            PresetColour.BASE_YELLOW_LIGHT,
            "体味内容",
            "禁用后，某些场景将会减少体味内容或被完全省略，同时「体味标记」状态效果也会被禁用。",
            PropertyValue.muskContent
    );

    public static final ToggleOption FURRY_TAIL_PENETRATION = new ToggleOption(
            "FURRY_TAIL_PENETRATION",
            PresetColour.BASE_MAGENTA,
            "兽尾插入",
            "这会将所有尾巴类型标记为适合插入，从而让兽尾能够在性生活中进行插入类动作。",
            PropertyValue.furryTailPenetrationContent
    );

    public static final ToggleOption INFLATION_CONTENT = new ToggleOption(
            "INFLATION_CONTENT",
            PresetColour.CUM,
            "膨胀",
            "这会启用膨胀机制。",
            PropertyValue.inflationContent
    );

    public static final ToggleOption AUTO_SEX_CLOTHING_MANAGEMENT = new ToggleOption(
            "AUTO_SEX_CLOTHING_MANAGEMENT",
            PresetColour.BASE_BLUE_STEEL,
            "性行为后衣物复原",
            "启用后，在性场面结束后，已穿着的衣物将被自动拉回性行为前的状态。",
            PropertyValue.autoSexClothingManagement
    );

    public static final ToggleOption AUTO_SEX_CLOTHING_STRIP = new ToggleOption(
            "AUTO_SEX_CLOTHING_STRIP",
            PresetColour.BASE_PINK_LIGHT,
            "自动脱衣",
            "启用后，性生活中所有你被允许脱下衣物的非旁观角色（包括你自己）都将以裸体开始。",
            PropertyValue.autoSexStrip
    );

    public static final ToggleOption RAPE_PLAY_BY_DEFAULT = new ToggleOption(
            "RAPE_PLAY_BY_DEFAULT",
            PresetColour.BASE_CRIMSON,
            "默认允许强暴扮演",
            "启用后，在性生活中具有「不情愿的性玩具」癖好的顺从角色，将无需事先获得许可即可进行强暴扮演。",
            PropertyValue.rapePlayAtSexStart
    );

    public static final ToggleOption AGE = new ToggleOption(
            "AGE",
            PresetColour.AGE_TWENTIES,
            "年龄",
            "这会启用对角色看起来年龄的描述。",
            PropertyValue.ageContent
    );

    public static final ToggleOption FERAL = new ToggleOption(
            "FERAL",
            PresetColour.BASE_TAN,
            "野兽",
            "这会启用野兽内容，其中包含与拥有完全动物身体且有智能的角色之间的性与非性互动。",
            PropertyValue.feralContent
    );

    public static final ToggleOption CUM_REGENERATION = new ToggleOption(
            "CUM_REGENERATION",
            PresetColour.CUM,
            "精液再生",
            "这会启用与精液再生相关的内容，例如同一次性爱中多次高潮会减少精液量，以及「精囊充盈」状态效果。<br>禁用时，睾丸将始终被视为充满状态，但不会产生任何负面效果。",
            PropertyValue.cumRegenerationContent
    );

    public static final ToggleOption FUTA_BALLS = new ToggleOption(
            "FUTA_BALLS",
            PresetColour.BASE_PINK,
            "扶她睾丸",
            "启用后，扶她 NPC 将能够拥有体外睾丸。禁用时，它们将始终被锁定为体内状态。",
            PropertyValue.futanariTesticles
    );

    public static final ToggleOption CLOACA = new ToggleOption(
            "CLOACA",
            PresetColour.BASE_PINK_LIGHT,
            "双足泄殖腔",
            "启用后，某些双足种族（例如鹰身女妖和鳄鱼形态）将拥有泄殖腔。禁用时，所有拥有泄殖腔的双足种族都将被视为具有常规生殖器结构。某些特殊种族（如拉米亚蛇女）始终拥有泄殖腔，不受此设置影响。",
            PropertyValue.bipedalCloaca
    );

    public static final ToggleOption VESTIGIAL_MULTI_BREAST = new ToggleOption(
            "VESTIGIAL_MULTI_BREAST",
            PresetColour.BASE_PURPLE_LIGHT,
            "退化性多乳房",
            "启用后，拥有多排乳房的角色，其乳房位于顶端一排以下的排数将被描述为在尺寸上呈退化状态。禁用时，乳房排数将被描述为比上一排小一个罩杯。",
            PropertyValue.vestigialMultiBreasts
    );

    public static final ToggleOption HAIR_FACIAL = new ToggleOption(
            "HAIR_FACIAL",
            PresetColour.BASE_LILAC_LIGHT,
            "面部毛发",
            "这会启用面部毛发的描述与内容。",
            PropertyValue.facialHairContent
    );

    public static final ToggleOption HAIR_PUBIC = new ToggleOption(
            "HAIR_PUBIC",
            PresetColour.BASE_LILAC,
            "阴毛",
            "这会启用阴毛的描述与内容。",
            PropertyValue.pubicHairContent
    );

    public static final ToggleOption HAIR_BODY = new ToggleOption(
            "HAIR_BODY",
            PresetColour.BASE_PURPLE,
            "腋毛",
            "这会启用腋毛的描述与内容。",
            PropertyValue.bodyHairContent
    );

    public static final ToggleOption HAIR_ASS = new ToggleOption(
            "HAIR_ASS",
            PresetColour.BASE_PURPLE_DARK,
            "肛毛",
            "这会启用肛毛的描述与内容。",
            PropertyValue.assHairContent
    );

    public static final ToggleOption FEMININE_BEARD = new ToggleOption(
            "FEMININE_BEARD",
            PresetColour.BASE_BLUE_STEEL,
            "女性胡须",
            "这会启用女性角色长出胡须的能力。",
            PropertyValue.feminineBeardsContent
    );

    public static final ToggleOption FURRY_HAIR = new ToggleOption(
            "FURRY_HAIR",
            PresetColour.CLOTHING_DESATURATED_BROWN,
            "毛茸茸头发",
            "切换拥有毛茸茸头部类型（fur）的角色是否会在头上生成类似人类的头发。",
            PropertyValue.furryHairContent
    );

    public static final ToggleOption SCALY_HAIR = new ToggleOption(
            "SCALY_HAIR",
            PresetColour.BASE_GREEN_DARK,
            "鳞状头发",
            "切换拥有爬行类或两栖类头部类型的角色是否会在头上生成类似人类的头发。",
            PropertyValue.scalyHairContent
    );

    public static final ToggleOption LIP_LISP = new ToggleOption(
            "LIP_LISP",
            PresetColour.BASE_PINK_SALMON,
            "厚唇口齿不清",
            "切换嘴唇非常巨大的角色说话时是否会夹杂口齿不清。",
            PropertyValue.lipLispContent
    );
}