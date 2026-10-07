package com.lilithsthrone.game.occupantManagement.slave;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.87
 * @version 0.4.9.1
 * @author Innoxia
 */
public enum SlaveJobSetting {
	
	// Sex as a sub settings:
	SEX_ORAL(PresetColour.GENERIC_SEX, "提供口交", "Oral P", "允许该奴隶为别人提供口交。"),
	SEX_VAGINAL(PresetColour.GENERIC_SEX, "接受阴道性交", "Vaginal R", "允许奴隶接受阴道性交。"),
	SEX_ANAL(PresetColour.GENERIC_SEX, "接受肛交", "Anal R", "允许该奴隶接受肛交。") {
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},
	SEX_NIPPLES(PresetColour.GENERIC_SEX, "接受乳头性交", "Nipples R", "允许该奴隶接受插入乳头性交。") {
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled();
		}
	},

	// Sex as a dom settings:
	SEX_ORAL_DOM(PresetColour.GENERIC_SEX, "接受口交", "Oral R", "允许该奴隶接受他人的口交。"),
	SEX_VAGINAL_DOM(PresetColour.GENERIC_SEX, "进行阴道性交", "Vaginal P", "允许该奴隶对他人进行阴道性交。"),
	SEX_ANAL_DOM(PresetColour.GENERIC_SEX, "进行肛交", "Anal P", "允许该奴隶对他人进行肛交。") {
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},
	SEX_NIPPLES_DOM(PresetColour.GENERIC_SEX, "进行乳头性交", "Nipples P", "允许该奴隶对他人进行乳头性交。") {
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled();
		}
	},

	
	MILKING_NO_PREFERENCE(PresetColour.BASE_GREY, "无偏好", "NP", "安排该角色去任意可用的挤奶间。"),
	MILKING_INDUSTRIAL(PresetColour.GENERIC_MINOR_BAD, "工业榨乳", "IN", "安排该奴隶去安装了工业挤奶机的房间。"),
	MILKING_REGULAR(PresetColour.BASE_YELLOW_LIGHT, "常规榨乳", "RG", "安排该奴隶去安装了常规挤奶机的房间。"),
	MILKING_ARTISAN(PresetColour.GENERIC_MINOR_GOOD, "人工榨乳", "AR", "安排该奴隶去安装了人工挤奶机的房间。"),
	MILKING_MILK(PresetColour.BASE_YELLOW_LIGHT, "收集乳汁", "CM", "允许收集该奴隶的乳汁。"),
	MILKING_MILK_CROTCH(PresetColour.BASE_YELLOW_LIGHT, "收集腹乳乳汁", "CU", "允许收集该奴隶的腹乳乳汁。"),
	MILKING_CUM(PresetColour.BASE_BLUE_LIGHT, "收集精液", "CC", "允许收集该奴隶的精液。"),
	MILKING_GIRLCUM(PresetColour.BASE_PINK_LIGHT, "收集爱液", "CG", "允许收集该奴隶的爱液。"),
	MILKING_TEAR_HYMEN(PresetColour.BASE_PINK_DEEP, "撕裂处女膜", "TH", "如果该角色还有完整的处女膜，也允许使用“阴道泵”插入(会造成处女膜破裂)。"
			+ "若关闭该许可，拥有完整处女膜的奴隶不会插入“阴道泵”，也就无法收集爱液。"),
	
	MILKING_MILK_AUTO_SELL(PresetColour.CURRENCY_GOLD, "自动售卖乳汁", "SM", "设置该角色的乳汁会自动售卖而非储存。"),
	MILKING_MILK_CROTCH_AUTO_SELL(PresetColour.CURRENCY_GOLD, "自动售卖腹乳乳汁", "SCM", "设置该角色的腹乳乳汁会自动售卖而非储存。"),
	MILKING_CUM_AUTO_SELL(PresetColour.CURRENCY_GOLD, "自动售卖精液", "SC", "设置该角色的精液会自动售卖而非储存。"),
	MILKING_GIRLCUM_AUTO_SELL(PresetColour.CURRENCY_GOLD, "自动售卖爱液", "SG", "设置该角色的爱液会自动售卖而非储存。"),
	
	
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE(PresetColour.FEMININE, "女性转化", "TF (F)", "允许该角色接受女性转化。"),
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE(PresetColour.MASCULINE, "男性转化", "TF (M)", "允许该角色进行男性转化。"),

	DOLL_STATUE_ARTISTIC(PresetColour.BASE_TAN, "艺术", "DPAR", "命令玩偶在扮演雕像时摆出艺术性姿势。"),
	DOLL_STATUE_ATTENTION(PresetColour.BASE_TAN, "立正", "DPAT", "命令玩偶在扮演雕像时立正。"),
	DOLL_STATUE_STANDING_SPLIT(PresetColour.BASE_TAN, "站立一字马", "DPSS", "命令玩偶在扮演雕像时摆出站立一字马姿势。"),
	DOLL_STATUE_MISSIONARY(PresetColour.BASE_TAN, "传教士体位", "DPMI", "命令玩偶在扮演雕像时以传教士体位向后躺。"),
	DOLL_STATUE_ALL_FOURS(PresetColour.BASE_TAN, "四肢跪地", "DPAF", "命令玩偶在扮演雕像时四肢跪地。"),
	DOLL_STATUE_SQUATTING(PresetColour.BASE_TAN, "分腿下蹲", "DPSQ", "命令玩偶在扮演雕像时分腿下蹲，同时将双手放在头后面。"),
	DOLL_STATUE_BRIDGE(PresetColour.BASE_TAN, "拱桥下腰", "DPBR", "命令玩偶在扮演雕像时摆出拱桥下腰姿势。"),

	SECURITY_ENTRANCE_PRIORITY(PresetColour.BASE_GOLD, "入口优先", "EP", "该奴隶会比其他人更优先选择在入口安保。(若多个奴隶拥有该许可，则最先到达入口的留在那里。)"),
	SECURITY_ANSWER_DOOR(PresetColour.BASE_GREEN_LIGHT, "应门", "AD", "若该奴隶位于入口，则由他来代替萝丝应门。"),
	
	BEDROOM_GREETING(PresetColour.GENERIC_MINOR_GOOD, "问候", "BG", "指示该奴隶在你进入自己房间时对你问候。"),
	BEDROOM_CLEAN(PresetColour.BASE_BLUE_LIGHT, "清洁", "BC", "让该奴隶负责你房间的清洁。"),
	BEDROOM_WAKE_UP(PresetColour.BASE_YELLOW_LIGHT, "叫醒", "BK", "让该奴隶担任你的闹钟，在指定时段内可以将你从睡梦中叫醒。"),
	BEDROOM_HELP_WASH(PresetColour.BASE_BLUE, "洗净身体", "BW", "让该奴隶协助你在浴室中清洗身体。(将会覆盖角色“清洁”许可的设置，因为一定会跟你一起洗澡。)"),
	
	BEDROOM_SLEEP_FLOOR(PresetColour.GENERIC_MINOR_BAD, "席地而睡", "BSF", "让该奴隶必须躺在你房间的地上睡觉。"),
	BEDROOM_SLEEP_ON_BED(PresetColour.BASE_PURPLE_LIGHT, "上床睡觉", "BSO", "允许该奴隶在你的床上睡觉，但不允许进被子。"),
	BEDROOM_SLEEP_IN_BED(PresetColour.BASE_PINK, "进床睡觉", "BSI", "允许该奴隶在你的床上睡觉，并且盖上被子。"),
	
	
	SPA_SHOWERING(PresetColour.BASE_BLUE, "冲淋", "SSH", "让该奴隶协助你在换衣间清洗身体。(将会覆盖角色“清洁”许可的设置，因为一定会跟你一起洗澡。)"),
	SPA_BATHING(PresetColour.BASE_AQUA, "入浴", "SBA", "让该奴隶在你入浴时一起进入水疗池。(将会覆盖角色“清洁”许可的设置，因为一定会跟你一起洗澡。)"),
	SPA_STRIP_TO_BATHE(PresetColour.BASE_PERIWINKLE, "裸体入浴", "SBN", "让奴隶先脱光衣服，再跟你一起进入水疗池。"),
	SPA_MASSAGE(PresetColour.BASE_BROWN, "按摩", "SMA", "允许该奴隶给你按摩。"),
	SPA_SAUNA(PresetColour.BASE_ROSE, "桑拿", "SSA", "允许该奴隶一起跟你蒸桑拿(扩建后可用)。"),
	SPA_POOL(PresetColour.BASE_BLUE_LIGHT, "游泳", "SPA", "允许该奴隶在水池中跟你一起游泳(扩建后可用)。"),

//	SPA_RECEPTION(PresetColour.BASE_BROWN, "Receptionist", "SRA", "Assign this slave to work at the spa's reception."),
//	SPA_ASSISTANT(PresetColour.BASE_GREEN_LIME, "Assistant", "SPA", "Assign this slave to work as one of the assistants within the spa proper."),
	;
	
	private Colour colour;
	private String name;
	private String tag;
	private String description;
	
	private SlaveJobSetting(Colour colour, String name, String tag, String description) {
		this.colour = colour;
		this.name = name;
		this.tag = tag;
		this.description = description;
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}
	
	public String getTag() {
		return tag;
	}

	public String getDescription() {
		return description;
	}
	
	public String getDailyEffectsDescription() {
		return null;
	}
	
	public String applyDailyEffects() {
		return null;
	}
	
	/**
	 * @return true if this setting is displayed to the player. It will only be false if an associated content setting (e.g. anal, nipple pen) is turned off.
	 */
	public boolean isAvailable() {
		return true;
	}
	
}
