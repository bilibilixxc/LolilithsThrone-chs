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
            "最低年龄语音",
            "</br>禁用时，会移除年龄描述中'由于所有人都从该年龄开始'的部分。",
            PropertyValue.minimumAgeSpeech
    );
    public static final ToggleOption FETISH_PEE = new ToggleOption(
            "FETISH_PEE",
            PresetColour.BASE_GOLD,
            "嗜好：排尿内容",
            "</br>禁用时，所有排尿内容都将被禁用。",
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
            "嗜好：年龄差内容",
            "</br>禁用时，年龄差嗜好将被禁用。尚未完成。",
            PropertyValue.extremeAgeContent
    );

    public static final ToggleOption LEXI_MENU_OPTIONS = new ToggleOption(
            "LEXI_MENU_OPTIONS",
            PresetColour.BASE_GOLD,
            "更优主菜单",
            "</br>启用时，将使用客观上更优秀的主菜单布局。",
            PropertyValue.lexiMenuOptions
    );
    public static final ToggleOption COMBAT_HUG = new ToggleOption(
            "COMBAT_HUG",
            PresetColour.BASE_PINK,
            "拥抱战斗招式",
            "</br>禁用时，你将无法通过拥抱敌人来击败他们。</br>在我读完 CombatMove XML 文档前暂不可用。",
            PropertyValue.enableHug
    );

    public static final ToggleOption SHOW_AGE = new ToggleOption(
            "SHOW_AGE",
            PresetColour.BASE_GREEN_LIME,
            "显示年龄",
            "</br>启用时，检查 NPC 时将显示其所'呈现'的年龄。",
            PropertyValue.showAge
    );

    public static final ToggleOption SHOW_TRUE_AGE = new ToggleOption(
            "SHOW_TRUE_AGE",
            PresetColour.BASE_GREEN_LIME,
            "显示真实年龄",
            "</br>启用时，在分配了'观察敏锐'特长的情况下，检查 NPC 时将显示其真实年龄。",
            PropertyValue.showTrueAge
    );

    public static final ToggleOption KIN_MODE = new ToggleOption(
            "KIN_MODE",
            PresetColour.BASE_GREEN_LIME,
            "将 -morph 改为 kin",
            "</br>启用时，种族将命名为 _kin 而非 _-morph。",
            PropertyValue.kinMode
    );

    public static final ToggleOption SHOW_ENHANCED_DESCRIPTION = new ToggleOption(
            "SHOW_ENHANCED_DESCRIPTION",
            PresetColour.BASE_GREEN_LIME,
            "在遭遇名中显示年龄",
            "</br>启用时，遭遇 NPC 时会在地名中显示其年龄。例如（一位男性 -> 一位年轻男性）",
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
            "将偏好应用于后代年龄",
            "</br>启用时，后代会被神秘地老化到你的年龄内容偏好范围内的某个年龄。",
            PropertyValue.applyPrefToOffspring
    );

    public static final ToggleOption OFFSPRING_INHERIT_LEVEL = new ToggleOption(
            "OFFSPRING_INHERIT_LEVEL",
            PresetColour.BASE_GREEN_LIME,
            "后代继承等级",
            "</br>启用时，后代将随机继承等级，最高可达你的等级加1。",
            PropertyValue.offspringLevelParent
    );

    public static final ToggleOption AGE_PROGRESSION = new ToggleOption(
            "AGE_PROGRESSION",
            PresetColour.BASE_RED_LIGHT,
            "年龄增长",
            "</br>启用时，将检查 NPC 身体的老化情况。也就是'成长模拟'。",
            PropertyValue.ageProgression
    );

    public static final ToggleOption EQUIP_UNIQUES = new ToggleOption(
            "EQUIP_UNIQUES",
            PresetColour.BASE_RED_LIGHT,
            "装备独特NPC",
            "</br>启用后，你可以修改独特 NPC 的装备。<br>注意：目前仅对某些商店老板生效！例如：Nyan、Finch",
            PropertyValue.equipUniques
    );

    public static final ToggleOption ENSLAVE_LUMI = new ToggleOption(
            "ENSLAVE_LUMI",
            PresetColour.BASE_RED_LIGHT,
            "可奴役的Lumi",
            "</br>启用后，你可以在击败 Lumi 后奴役她。<br>注意：需要进一步测试...",
            PropertyValue.enslaveLumi
    );

    public static final ToggleOption COMBAT_LEAKAGE = new ToggleOption(
            "COMBAT_LEAKAGE",
            PresetColour.BASE_YELLOW_LIGHT,
            "战斗渗漏",
            "</br>启用后，暴击有机会导致漏出（当水域游戏启用时）。",
            PropertyValue.combatLeakage
    );

    public static final ToggleOption FORCE_CLEAN_SLOTS = new ToggleOption(
            "FORCE_CLEAN_SLOTS",
            PresetColour.BASE_YELLOW_LIGHT,
            "强制清洁孔位",
            "</br>启用后，任何'N湿'服装在被'清洁污渍'的同时也会强制清除'N湿'",
            PropertyValue.forceClean
    );

    public static final ToggleOption ENABLE_PENT_UP = new ToggleOption(
            "ENABLE_PENT_UP",
            PresetColour.BASE_RED,
            "启用积压模式",
            "</br>奴隶在整天没有性行为后会变得'积压'<br/>设为关闭可禁用此状态效果",
            PropertyValue.enablePentUp
    );

    public static final ToggleOption NERF_AFF_OBED = new ToggleOption(
            "NERF_AFF_OBED",
            PresetColour.BASE_GREY_DARK,
            "禁用负面房间修正",
            "</br>防止房间升级和奴隶工作中产生任何负面修正，从而防止奴隶随时间流失好感度或服从度。",
            PropertyValue.nerfedAffObed
    );

    public static final ToggleOption ENABLE_SLOVENLY = new ToggleOption(
            "ENABLE_SLOVENLY",
            PresetColour.BASE_YELLOW_PALE,
            "启用邋遢口音",
            "</br>允许随机生成的角色带有邋遢口音特质<br/>不影响独特NPC！",
            PropertyValue.enableSlovenly
    );

    public static final ToggleOption LOOT_THE_MUSEUM = new ToggleOption(
            "LOOT_THE_MUSEUM",
            PresetColour.BASE_BLUE,
            "搜刮博物馆",
            "</br>在角色创建时点击\"登台\"可将所有服装带走<br>注意：离开前可以更改颜色！",
            PropertyValue.lootTheMuseum
    );

    public static final ToggleOption WAKE_AFFECTION_LOSS = new ToggleOption(
            "WAKE_AFFECTION_LOSS",
            PresetColour.BASE_BLUE,
            "唤醒NPC导致好感度下降",
            "</br>开启后，唤醒房屋住户会导致其失去好感度（被唤醒的奴隶/住户）",
            PropertyValue.wakeAffectionLoss
    );

    public static final ToggleOption VANILLA_JOB_CONDITIONS = new ToggleOption(
            "VANILLA_JOB_CONDITIONS",
            PresetColour.BASE_GOLD,
            "原版职业条件",
            "</br>关闭后，玩家背景（角色创建的职业选择）将使用原版条件而非新条件",
            PropertyValue.vanillaJobConditions
    );

    public static final ToggleOption ORIGINAL_AGE = new ToggleOption(
            "ORIGINAL_AGE",
            PresetColour.BASE_BLUE_DARK,
            "独特NPC年龄",
            "</br>关闭后，独特NPC的年龄将降低（18 - 最低年龄）",
            PropertyValue.originalAge
    );

    public static final ToggleOption ENABLE_ENFORCERS = new ToggleOption(
            "ENABLE_ENFORCERS",
            PresetColour.BASE_BLUE_DARK,
            "启用执法者",
            "</br>允许执法者在特定地点的随机遭遇中生成并骚扰玩家",
            PropertyValue.enableEnforcers
    );

    public static final ToggleOption FORCE_ORGASMIC_DRAIN = new ToggleOption(
            "FORCE_ORGASMIC_DRAIN",
            PresetColour.BASE_BLUE_DARK,
            "强制高潮吸取生成",
            "</br>强制NPC按照高潮吸取等级流失几率设置携带高潮吸取特长。注意：更新112后可能已不需要",
            PropertyValue.forceOrgasmicDrain
    );

    public static final ToggleOption HEIGHT_MOD = new ToggleOption(
            "HEIGHT_MOD",
            PresetColour.BASE_GREEN_LIME,
            "外表年龄身高",
            "</br>使用表观年龄还是真实年龄来计算身高</br>开启 = 使用表观年龄</br>关闭 = 使用真实年龄",
            PropertyValue.scaleHeightBasedOnAgeAppearance
    );

    public static final ToggleOption CHANGE_APPARENT_AGE = new ToggleOption(
            "CHANGE_APPARENT_AGE",
            PresetColour.BASE_GREEN_LIME,
            "改变外表年龄",
            "</br>此选项将允许对遭遇NPC的年龄外观重新随机化<br>它将是你的表观年龄范围偏移的 +/-",
            PropertyValue.scaleHeightBasedOnAgeAppearance
    );

    public static final SliderOption APPARENT_AGE_OFFSET = new SliderOption(
            "APPARENT_AGE_OFFSET",
            PresetColour.BASE_GREEN_LIME,
            "外表年龄范围偏移",
            "</br>将表观年龄调整为 年龄-此值 <-> 年龄+此值",
            "%VALUE",
            0,
            30,
            1,
            "ageAppearanceRange"
    );
    public static final SliderOption AMORPHOUS_APPARENT_AGE_OFFSET = new SliderOption(
            "AMORPHOUS_APPARENT_AGE_OFFSET",
            PresetColour.BASE_GREEN_LIME,
            "恶魔/史莱姆外表年龄范围偏移",
            "</br>此设置调整恶魔和史莱姆的表观年龄，并加到上述的表观年龄范围偏移之上<br>如果上述为5，此值为10，那么45岁的角色看起来会是30-60岁",
            "%VALUE",
            0,
            30,
            1,
            "amorphousAgeRange"
    );

    public static final ToggleOption EXCLUDE_AMORPHOUS = new ToggleOption(
            "EXCLUDE_AMORPHOUS",
            PresetColour.BASE_GREEN_LIME,
            "从身体调整中排除变形者们",
            "</br>关闭后，我们基于年龄的身体改造将应用于可变形种族（史莱姆/恶魔）",
            PropertyValue.excludeAmorphous
    );

    public static final ToggleOption IMP_PREFERENCES = new ToggleOption(
            "IMP_PREFERENCES",
            PresetColour.BASE_GREEN_LIME,
            "小恶魔出现率使用偏好",
            "</br>启用后，小恶魔的亚种权重将基于你的偏好",
            PropertyValue.impPreferences
    );

    public static final SliderOption IMP_HEIGHT = new SliderOption("IMP_HEIGHT",
            PresetColour.BASE_CRIMSON,
            "小恶魔身高",
            "</br>此设置更改被归类为小恶魔所需的身高",
            "%UNIT_SIZE",
            1,
            150,
            1,
            "impHeight"
    );

    public static final SliderOption ALPHA_IMP_HEIGHT = new SliderOption("ALPHA_IMP_HEIGHT",
            PresetColour.BASE_CRIMSON,
            "阿尔法小恶魔身高",
            "</br>此设置更改被归类为阿尔法小恶魔所需的身高",
            "%UNIT_SIZE",
            50,
            200,
            1,
            "alphaImpHeight"
    );

    public static final ToggleOption HEIGHT_GENDERED = new ToggleOption(
            "HEIGHT_GENDERED",
            PresetColour.BASE_GREEN_LIME,
            "外表性别身高",
            "</br>使用真实性别还是表观性别来决定身高</br>开启 = 使用表观性别</br>关闭 = 使用真实性别",
            PropertyValue.scaleHeightBasedOnGenderOrAppearance
    );
    public static final ToggleOption HEIGHT_RELATIVE = new ToggleOption(
            "HEIGHT_RELATIVE",
            PresetColour.BASE_GREEN_LIME,
            "相对身高",
            "</br>按种族身高/人类身高来乘算经年龄调整后的身高<br>" +
                    " 我们使用世界卫生组织（WHO）的真实身高数据，这能让奇幻种族也得到考虑。",
            PropertyValue.relativeHeight
    );
    public static final ToggleOption BODY_AGE = new ToggleOption(
            "BODY_AGE",
            PresetColour.BASE_GREEN_LIME,
            "外表年龄身体",
            "</br>使用真实年龄还是表观年龄来改变身体大小</br>开启 = 依据表观年龄调整身体</br>关闭 = 依据真实年龄调整身体</br> 我添加此选项是因为他们会以某个年龄出现，即依据其身体外观",
            PropertyValue.scaleBodyBasedOnApparentAge
    );
    public static final ToggleOption BA_BODY = new ToggleOption(
            "BA_BODY",
            PresetColour.BASE_GREEN_LIME,
            "改变身体",
            "</br>仅根据年龄、肌肉量和体型调整身体大小",
            PropertyValue.changeBody
    );
    public static final ToggleOption BA_BREASTS = new ToggleOption(
            "BA_BREASTS",
            PresetColour.BASE_GREEN_LIME,
            "改变胸部",
            "</br>根据年龄调整胸部大小",
            PropertyValue.changeBreasts
    );
    public static final ToggleOption BA_NIPPLE = new ToggleOption(
            "BA_NIPPLE",
            PresetColour.BASE_GREEN_LIME,
            "改变乳头",
            "</br>根据胸部调整乳头大小",
            PropertyValue.changeNipples
    );
    public static final ToggleOption BA_HIPS = new ToggleOption(
            "BA_HIPS",
            PresetColour.BASE_GREEN_LIME,
            "改变臀部",
            "</br>根据年龄调整臀部大小",
            PropertyValue.changeHips
    );
    public static final ToggleOption BA_VAGINA = new ToggleOption(
            "BA_VAGINA",
            PresetColour.BASE_GREEN_LIME,
            "改变阴道",
            "</br>根据年龄调整阴道大小",
            PropertyValue.changeVagina
    );
    public static final ToggleOption BA_PENIS = new ToggleOption(
            "BA_PENIS",
            PresetColour.BASE_GREEN_LIME,
            "改变阴茎",
            "</br>根据年龄调整阴茎大小",
            PropertyValue.changePenis
    );
    public static final ToggleOption BA_TESTICLE = new ToggleOption(
            "BA_TESTICLE",
            PresetColour.BASE_GREEN_LIME,
            "改变睾丸",
            "</br>根据年龄调整睾丸大小",
            PropertyValue.changeTesticle
    );
    public static final ToggleOption BA_THROAT = new ToggleOption(
            "BA_THROAT",
            PresetColour.BASE_GREEN_LIME,
            "改变咽喉",
            "</br>根据年龄调整喉咙大小",
            PropertyValue.changeThroat
    );
    public static final ToggleOption BA_ASS = new ToggleOption(
            "BA_ASS",
            PresetColour.BASE_GREEN_LIME,
            "改变屁股",
            "</br>根据年龄调整屁股大小",
            PropertyValue.changeAss
    );
    public static final ToggleOption BA_ANUS = new ToggleOption(
            "BA_ANUS",
            PresetColour.BASE_GREEN_LIME,
            "改变肛门",
            "</br>根据年龄调整肛门大小",
            PropertyValue.changeAnus
    );
    public static final ToggleOption BA_URETHRA = new ToggleOption(
            "BA_URETHRA",
            PresetColour.BASE_GREEN_LIME,
            "改变尿道",
            "</br>根据（阴道/阴茎）大小调整尿道大小",
            PropertyValue.changeUrethra
    );
    public static final ToggleOption ENABLE_PIERCINGS = new ToggleOption(
            "ENABLE_PIERCINGS",
            PresetColour.BASE_GREEN_LIME,
            "启用打洞",
            "</br>允许随机生成的NPC在生成时带有穿孔",
            PropertyValue.enablePiercings
    );
    public static final ToggleOption ENABLE_TATTOOS = new ToggleOption(
            "ENABLE_TATTOOS",
            PresetColour.BASE_GREEN_LIME,
            "启用纹身",
            "</br>允许随机生成的NPC在生成时带有纹身",
            PropertyValue.enableTattoos
    );
    public static final ToggleOption ENABLE_MAKEUP = new ToggleOption(
            "ENABLE_MAKEUP",
            PresetColour.BASE_GREEN_LIME,
            "启用化妆",
            "</br>允许随机生成的NPC在生成时带有化妆",
            PropertyValue.enableMakeup
    );

    public static final ToggleOption ENABLE_FRENCH_KISS = new ToggleOption(
            "ENABLE_FRENCH_KISS",
            PresetColour.BASE_PERIWINKLE,
            "启用法式接吻",
            "</br>在性行为中启用开放式接吻？",
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
            "启用尾巴穴内容",
            "</br>在性行为中启用尾巴穴行为？",
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
            "启用额外的自慰插入内容",
            "</br>添加之前未实现的自慰插入行为。若Archer的内容被禁用则无效。",
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
            "启用性爱图像",
            "</br>在性行为中启用角色图像？",
            PropertyValue.enableSexImages
    );
    public static final ToggleOption ALWAYS_MODDED = new ToggleOption(
            "ALWAYS_MODDED",
            PresetColour.BASE_PINK,
            "标记已导入的角色",
            "</br>启用后，任何作为奴隶导入的角色都会被标记为我们已修改过，而不再被调整。",
            PropertyValue.importedFlagAsModded
    );
    public static final ToggleOption PREVENT_GIANTS = new ToggleOption(
            "PREVENT_GIANTS",
            PresetColour.BASE_PINK,
            "防止巨人",
            "</br>启用后，任何角色的修改后身高都不会超过Innoxia生成的最大值。",
            PropertyValue.preventGiants
    );
    public static final ToggleOption ENABLE_PERSONALITY = new ToggleOption(
            "ENABLE_PERSONALITY",
            PresetColour.BASE_GREY_LIGHT,
            "启用个性选择",
            "</br>启用后，角色创建期间将出现个性选择弹窗。</br>默认：开启",
            PropertyValue.enablePersonality
    );

    /* SLIDERS */
    public static final SliderOption MAX_ENCHANTMENTS = new SliderOption("MAX_ENCHANTMENTS",
            PresetColour.BASE_BLUE,
            "最大附魔数量",
            "</br>此设置改变你可在物品上施加的最大附魔数量",
            "%VALUE",
            1,
            50,
            5,
            "maxEnchantments"
    );
    public static final SliderOption SHOTA_AGE = new SliderOption("SHOTA_AGE",
            PresetColour.BASE_BLUE_LIGHT,
            "正太年龄",
            "</br>这是在遭遇名称中显示年龄时，被标记为\"正太\"的最大年龄",
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
            "导入年龄偏移",
            "</br>角色导入的年龄偏移",
            "%VALUE",
            -18,
            -18,
            18,
            1,
            "importAgeOffset"
    );
    
    public static final SliderOption LOLI_AGE = new SliderOption("LOLI_AGE",
            PresetColour.BASE_PINK_LIGHT,
            "萝莉年龄",
            "</br>这是在遭遇名称中显示年龄时，被标记为\"萝莉\"的最大年龄",
            "%VALUE",
            1,
            1,
            16,
            1,
            "loliLabelAge"
    );
    public static final SliderOption YOUNG_AGE = new SliderOption("YOUNG_AGE",
            PresetColour.BASE_GREEN_LIGHT,
            "年轻年龄",
            "</br>这是在遭遇名称中显示年龄时，被标记为\"年轻\"的最大年龄",
            "%VALUE",
            1,
            1,
            20,
            1,
            "youngLabelAge"
    );
    public static final SliderOption OLD_AGE = new SliderOption("OLD_AGE",
            PresetColour.BASE_GREEN_LIGHT,
            "年老年龄",
            "</br>这是在遭遇名称中显示年龄时，被标记为\"年老\"的最小年龄",
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
            "经验倍率",
            "</br>这会增加你在战斗中获胜所获得的经验值",
            "%VALUE%",
            0,
            50,
            25,
            "xpMultiplier"
    );
    public static final SliderOption ESS_MULT = new SliderOption(
            "ESS_MULT",
            PresetColour.BASE_PURPLE,
            "精魄倍率",
            "</br>这会增加你在性互动中获得精魄的数量",
            "%VALUE%",
            0,
            50,
            25,
            "essenceMultiplier"
    );
    public static final SliderOption MONEY_MULT = new SliderOption(
            "MONEY_MULT",
            PresetColour.BASE_GOLD,
            "金钱倍率",
            "</br>这会增加你在战斗中获胜所获得的金钱",
            "%VALUE%",
            0,
            50,
            25,
            "moneyMultiplier"
    );
    public static final ToggleOption ENABLE_MULTI_INCOME = new ToggleOption(
            "ENABLE_MULTI_INCOME",
            PresetColour.BASE_GREY_LIGHT,
            "为收入启用金钱倍率",
            "</br>启用后，上述金钱倍率将适用于自行卖淫和卖奶所得的收入。",
            PropertyValue.applyMoneyMultToIncome
    );
    public static final SliderOption INTEREST_MULT = new SliderOption(
            "INTEREST_MULT",
            PresetColour.BASE_GOLD,
            "利息倍率",
            "</br>这会增加在银行获得的利息",
            "%VALUE%",
            0,
            50,
            4,
            "interestMultiplier"
    );
    public static final SliderOption DROPS_MULT = new SliderOption(
            "DROPS_MULT",
            PresetColour.BASE_TAN,
            "掉落倍率",
            "</br>这会增加你在战斗中获胜所获得的物品数量",
            "%VALUE",
            0,
            20,
            1,
            "itemDropsIncrease"
    );

    public static final SliderOption MAX_LEVEL = new SliderOption(
            "MAX_LEVEL",
            PresetColour.BASE_BLUE,
            "角色最大等级",
            "</br>此设置改变角色的最大等级",
            "%VALUE",
            1,
            100,
            5,
            "maxLevel"
    );
    public static final SliderOption FUCKABLE_CUP_SIZE = new SliderOption(
            "FUCKABLE_CUP_SIZE",
            PresetColour.BASE_GREEN_LIME,
            "可插入的罩杯尺寸",
            "</br>此设置设定乳头被视为可插入所需的最小胸部罩杯尺寸（仍需满足其他要求）。" +
                    "</br>平坦 = 0，A = 5，C（原版） = 7，E = 10，H = 15" +
                    "</br>若禁用乳头插入，则此选项无效。",
            "%VALUE",
            0,
            15,
            1,
            "minimumFuckableCupSize"
    );
    public static final SliderOption PLAYER_PREG_DUR = new SliderOption(
            "PLAYER_PREG_DUR",
            PresetColour.BASE_PINK_LIGHT,
            "玩家妊娠时长",
            "</br>这是对玩家妊娠时长的百分比倍率。",
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
            "NPC妊娠时长",
            "</br>这是对NPC妊娠时长的百分比倍率。",
            "%VALUE%",
            5,
            50,
            10,
            "NPCPregDuration"
    );
    public static final SliderOption NPC_PREG_CHANCE = new SliderOption(
            "NPC_PREG_CHANCE",
            PresetColour.BASE_PINK_LIGHT,
            "NPC妊娠几率",
            "</br>这是随机生成的NPC中以处于怀孕状态生成的百分比。",
            "%VALUE%",
            0,
            20,
            5,
            "NPCPregChance"
    );
    public static final SliderOption MAX_PERKS = new SliderOption(
            "MAX_PERKS",
            PresetColour.BASE_GREY_DARK,
            "最大特长数",
            "</br>你可同时拥有的主要特长最大数量。<br/>注意：目前游戏中只有20种特长。",
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
            "稳定性下限基线",
            "</br>提升你的附魔不稳定性上限<br/>这只是平直的基线提升",
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
            "稳定性绝对上限",
            "</br>提升附魔不稳定性的绝对上限<br> 这是你能拥有的不稳定性上限，超过该上限仍会削弱你。",
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
            "每级稳定性上限",
            "</br>提升你的附魔不稳定性上限<br/>此值会乘以你的等级",
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
            "每体格稳定性上限",
            "</br>提升你的附魔不稳定性上限<br/>此值会乘以你的体格",
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
            "每神秘力稳定性上限",
            "</br>提升你的附魔不稳定性上限<br/>此值会乘以你的神秘力",
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
            "每堕落度稳定性上限",
            "</br>提升你的附魔不稳定性上限<br/>此值会乘以你的堕落度",
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
            "精魄库存",
            "</br>Vicky的商店每天每种精魄的库存量</br>实际数值将介于该值与此值2倍之间",
            "%VALUE",
            0,
            300,
            10,
            "essenceStock"
    );
    public static final SliderOption ENC_COST_MULT = new SliderOption(
            "ENC_COST_MULT",
            PresetColour.BASE_TAN,
            "附魔费用倍率",
            "</br>此设置增加/减少附魔的花费",
            "%VALUE%",
            0,
            50,
            25,
            "encCostMultiplier"
    );
    public static final SliderOption POTENCY_MINOR = new SliderOption(
            "POTENCY_MINOR",
            PresetColour.BASE_PURPLE_LIGHT,
            "次要强度",
            "</br>次要附魔的加成效果",
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
            "中等强度",
            "</br>中等附魔的加成效果",
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
            "主要强度",
            "</br>主要附魔的加成效果",
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
            "次要强度价值",
            "</br>次要附魔的价值加成",
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
            "中等强度价值",
            "</br>中等附魔的价值加成",
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
            "主要强度价值",
            "</br>主要附魔的价值加成",
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
                "最低年龄",
                "</br>这是任何人（包含玩家）可以达到的最低年龄",
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
            "最高年龄",
            "</br>这是任何人（包含玩家）可以达到的最高年龄",
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
            "</br>这是后代可以出现的最低年龄",
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
            "正太身体条件",
            "</br>这是对带阴茎的未成年人尺寸、精液储存/产生及雄性的%调整</br>若想绝不生育请设为-100。",
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
            "巨乳萝莉",
            "</br>这是所有生成时带胸部的主要未成年人获得额外罩杯尺寸的%几率",
            "%VALUE%",
            0,
            20,
            5,
            "loliOppai"
    );
    public static final SliderOption LOLI_FERTILITY = new SliderOption(
            "LOLI_FERTILITY",
            PresetColour.BASE_PINK,
            "萝莉生育能力",
            "</br>此百分比是自然携带生育能力的萝莉所占的百分比</br>若想绝不生育请设为-100。",
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
            "</br>此百分比是作为处女出现的人所占的百分比",
            "%VALUE%",
            0,
            10,
            5,
            "virginsPercent"
    );*/
    public static final SliderOption IMP_HMULT = new SliderOption(
            "IMP_HMULT",
            PresetColour.BASE_BROWN,
            "小恶魔身高倍率",
            "</br>此值会乘以小恶魔通常的身高计算",
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
            "阿尔法小恶魔身高倍率",
            "</br>此值会乘以阿尔法小恶魔通常的身高计算",
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
            "身高偏差",
            "</br>此设置改变基础身高的厘米数",
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
            "身高计算的年龄上限",
            "</br>此设置改变NPC被认为达到最高身高的年龄。 </br>现实中这介于15到16岁之间",
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
            "异色瞳几率",
            "</br>这是随机生成的NPC中以异色瞳出现的百分比。",
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
            "高潮吸取等级流失几率",
            "</br>这是随机生成的NPC中携带高潮吸取等级流失特长的百分比。",
            "%VALUE%",
            0,
            20,
            5,
            "orgasmicLevelDrainChance"
    );
    public static final SliderOption CHUUNI_CHANCE = new SliderOption(
            "CHUUNI_CHANCE",
            PresetColour.BASE_BLUE,
            "中二病几率",
            "</br>这是随机生成的NPC中携带中二病特长的百分比。",
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
            "背包页数*",
            "</br>限制你可访问的背包页数。</br>默认：5",
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
            "背包行数*",
            "</br>限制每页背包的行数。</br>默认：5",
            "%VALUE",
            1,
            1,
            5,
            1,
            "inventoryRows"
    );
}