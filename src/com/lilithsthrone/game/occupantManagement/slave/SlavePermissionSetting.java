package com.lilithsthrone.game.occupantManagement.slave;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.87
 * @version 0.3.9.2
 * @author Innoxia
 */
public enum SlavePermissionSetting {

	// General/Misc.:
	
	GENERAL_SILENCE(false, "沉默", "禁止该奴隶说话。[style.italics(许可激活时，该奴隶会被当做哑巴对待。)]"),
	GENERAL_CRAWLING(false, "爬行", "禁止奴隶行走，强制其四肢跪地爬行。"),
	GENERAL_HOUSE_FREEDOM(false, "屋内自由", "给予该奴隶空闲时在莉莱雅的家里随意行走的权利。"),
	GENERAL_OUTSIDE_FREEDOM(false, "自由外出", "给予该奴隶空闲时离开莉莱雅的家的权利。"),
	
	
	// Behaviour:
	
	BEHAVIOUR_WHOLESOME(false, "关照", "让该奴隶在与你交互时显出宠爱关照的姿态。[style.italics(只有在喜欢你或顺从的情况下有效。)]"),
	BEHAVIOUR_PROFESSIONAL(false, "专业", "让该奴隶在于你交互时显出专业的姿态。[style.italics(只有在喜欢你或顺从的情况下有效。)]"),
	BEHAVIOUR_STANDARD(true, "常规", "对于该奴隶在你身边的行事方式，没有任何指示。"),
	BEHAVIOUR_SEDUCTIVE(false, "勾引", "让该奴隶在与你交互时显出优雅而充满魅惑的姿态。[style.italics(只有在喜欢你或顺从的情况下有效。)]"),
	BEHAVIOUR_SLUTTY(false, "淫荡", "让该奴隶在与你交互时显出自贱、淫荡的姿态。[style.italics(只有在喜欢你或顺从的情况下有效。)]"),

	
	// Sex:
	
	SEX_LUBE_PILL(false, "润滑药", "让奴隶服用润滑药，这会导致他们的整个身体都会在性爱场景中保持润滑。") {
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.useItem(Main.game.getItemGen().generateItem("innoxia_pills_lubrication"), character, false);
		}
		@Override
		public void applyEffectsOnRemoval(GameCharacter character) {
			character.removeStatusEffect(StatusEffect.LUBE_PILL);
		}
	},
	SEX_MASTURBATE(false, "自慰", "允许该奴隶自慰。"),
	SEX_INITIATE_SLAVES(false, "发起性爱", "") {
		@Override
		public String getDescription() {
			if(Main.game.isNonConEnabled()) {
				return "允许该奴隶向其他获得“性玩具”许可的奴隶发起性爱。"
						+ "只会与感兴趣的奴隶发起性爱，如果没有开启“强奸”许可，那么对方也必须对其感兴趣。"
						+ "只会与能找得到的奴隶发起性爱，即在同一个位置工作，或者是拥有“屋内自由”许可，亦或是两者均有“自由外出”许可。";
			}
			return "允许该奴隶向其他获得“性玩具”许可的奴隶发起性爱。"
					+ "只会与感兴趣的奴隶发起性爱，对方也必须对其感兴趣。"
					+ "只会与能找得到的奴隶发起性爱，即在同一个位置工作，或者是拥有“屋内自由”许可，亦或是两者均有“自由外出”许可。";
		}
		@Override
		public int getAdditionalDescriptionLines() {
			return 6;
		}
	},
	SEX_INITIATE_PLAYER(false, "用你解决", "") {
		@Override
		public String getDescription() {
			if(Main.game.isNonConEnabled()) {
				return "允许该奴隶使用你来释放性欲。"
						+ "这使其可以随时对你发起性爱，如果他们得到了强奸许可，那么你将无法拒绝他们的求爱。"
						+ "还需要“屋内自由”或“自由外出”许可，才能在宅邸中或御城区的小巷中找到你。";
			}
			return "允许该奴隶使用你来释放性欲。这使其可以随时对你发起性爱。"
					+ "还需要“屋内自由”或“自由外出”许可，才能在宅邸中或御城区的小巷中找到你。";
		}
		@Override
		public int getAdditionalDescriptionLines() {
			return 3;
		}
	},
	SEX_RAPIST(false, "强奸",
			"允许这个奴隶无视他人的性偏好，也包括你。"
				+ "如果该奴隶对于“"+Fetish.FETISH_NON_CON_DOM.getName(null)+"”性癖有负面情绪，"
						+ "或者自身喜欢对手，而对手对于“"+Fetish.FETISH_NON_CON_SUB.getName(null)+"”性癖也没有正面情绪，那么即使有这项许可也不会选择强奸对手。") {
		@Override
		public boolean isAvailableForCharacter(GameCharacter character) {
			return Main.game.isNonConEnabled();
		}
		@Override
		public int getAdditionalDescriptionLines() {
			return 3;
		}
	},
	SEX_RECEIVE_SLAVES(false, "性玩具", "允许该奴隶被任何拥有“发起性爱”许可的奴隶用以释放性欲。"),
	SEX_SAVE_VIRGINITY(true, "保留贞操", "不允许其他奴隶在性爱中夺去该奴隶的贞操。"),
	SEX_IMPREGNATED(false, "受种母狗", "允许该奴隶在性爱事件中被其他拥有“奴隶种马”许可的奴隶授孕。") {
		@Override
		public boolean isAvailableForCharacter(GameCharacter character) {
			return !character.isDoll();
		}	
	},
	SEX_IMPREGNATE(false, "奴隶种马", "允许该奴隶在性爱事件中使其他拥有“受种母狗”许可的奴隶授孕。") {
		@Override
		public boolean isAvailableForCharacter(GameCharacter character) {
			return !character.isDoll();
		}	
	},

	
	// Pills:

	PILLS_NO_PILLS(true, "不服药", "不给该奴隶影响生育力的药物，使其受孕概率保持正常。"),
	
	PILLS_PROMISCUITY_PILLS(false, "", "") {
		@Override
		public String getName() {
			return Util.capitaliseSentence(ItemType.getItemTypeFromId("innoxia_pills_sterility").getName(false));
		}
		@Override
		public String getDescription() {
			return UtilText.parse("让该奴隶持续服用[#ITEM_innoxia_pills_sterility.getNamePlural(false)]，极大降低其生育力和生殖力。");
		}
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.useItem(Main.game.getItemGen().generateItem("innoxia_pills_sterility"), character, false);
		}
		@Override
		public void applyEffectsOnRemoval(GameCharacter character) {
			character.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
		}
	},
	
	PILLS_VIXENS_VIRILITY(false, "", "") {
		@Override
		public String getName() {
			return Util.capitaliseSentence(ItemType.getItemTypeFromId("innoxia_pills_fertility").getName(false));
		}
		@Override
		public String getDescription() {
			return UtilText.parse("让该奴隶持续服用[#ITEM_innoxia_pills_fertility.getNamePlural(false)]，极大提高其生育力和生殖力。");
		}
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), character, false);
		}
		@Override
		public void applyEffectsOnRemoval(GameCharacter character) {
			character.removeStatusEffect(StatusEffect.VIXENS_VIRILITY);
		}
	},

	PILLS_BROODMOTHER(false, "", "") {
		@Override
		public String getName() {
			return Util.capitaliseSentence(ItemType.getItemTypeFromId("innoxia_pills_broodmother").getName(false));
		}
		@Override
		public String getDescription() {
			return UtilText.parse("让该奴隶持续服用[#ITEM_innoxia_pills_broodmother.getNamePlural(false)]，巨幅提高其生育力和生殖力，并且[style.colourExcellent(加倍)]怀胎数量。");
		}
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.useItem(Main.game.getItemGen().generateItem("innoxia_pills_broodmother"), character, false);
		}
		@Override
		public void applyEffectsOnRemoval(GameCharacter character) {
			character.removeStatusEffect(StatusEffect.BROODMOTHER_PILL);
		}
	},
	
	
	// Pregnancy:

	PREGNANCY_MOTHERS_MILK(false, "母亲的奶水", "命令该奴隶怀孕时定期服用“母亲的奶水”，使其在明显怀孕后数个小时即可结束孕期。"),
	PREGNANCY_ALLOW_BIRTHING(true, "允许生育", "允许该奴隶在需要时随时去往莉莱雅的分娩室完成分娩。"),
	PREGNANCY_ALLOW_EGG_LAYING(true, "允许产卵", "允许该奴隶需要时随时去往莉莱雅的分娩室，产下被注入体内的卵。"),
	
	
	// Diet/Body size:
	
	FOOD_DIET_EXTREME(false,
			Util.capitaliseSentence(BodySize.ZERO_SKINNY.getName(false)),
			"严格限制该奴隶的食物量，这将导致其体型每天都发生很大变化，"
					+ "最终会使其变得<b style='color:"+BodySize.ZERO_SKINNY.getColour().toWebHexString()+";'>"+BodySize.ZERO_SKINNY.getName(false)+"</b>。"),
	
	FOOD_DIET(false,
			Util.capitaliseSentence(BodySize.ONE_SLENDER.getName(false)),
			"控制该奴隶的食物量，这将导致其体型每天都会发生变化，"
					+ "最终会使其变得<b style='color:"+BodySize.ONE_SLENDER.getColour().toWebHexString()+";'>"+BodySize.ONE_SLENDER.getName(false)+"</b>.。"),
	
	FOOD_NORMAL(true,
			Util.capitaliseSentence(BodySize.TWO_AVERAGE.getName(false)),
			"给予该奴隶相对健康的食物量，这将导致其体型每天都会发生变化，"
					+ "最终会使其变得<b style='color:"+BodySize.TWO_AVERAGE.getColour().toWebHexString()+";'>"+BodySize.TWO_AVERAGE.getName(false)+"</b>。"),
	
	FOOD_PLUS(false,
			Util.capitaliseSentence(BodySize.THREE_LARGE.getName(false)),
			"每天给予该奴隶额外的食物，这将导致其体型每天都会发生变化，"
					+ "最终会使其变得<b style='color:"+BodySize.THREE_LARGE.getColour().toWebHexString()+";'>"+BodySize.THREE_LARGE.getName(false)+"</b>.。"),
	
	FOOD_LAVISH(false,
			Util.capitaliseSentence(BodySize.FOUR_HUGE.getName(false)),
			"让该奴隶想吃就吃，这将导致其体型每天都发生很大变化，"
					+ "最终会使其变得<b style='color:"+BodySize.FOUR_HUGE.getColour().toWebHexString()+";'>"+BodySize.FOUR_HUGE.getName(false)+"</b>。"),

	
	// Exercise/Muscle:
	
	EXERCISE_FORBIDDEN(false,
			Util.capitaliseSentence(Muscle.ZERO_SOFT.getName(false)),
			"禁止该奴隶体力活动，这将导致其肌肉量每天都大幅减少，"
					+ "最终会使其变得<b style='color:"+Muscle.ZERO_SOFT.getColour().toWebHexString()+";'>"+Muscle.ZERO_SOFT.getName(false)+"</b>。"),
	
	EXERCISE_REST(false,
			Util.capitaliseSentence(Muscle.ONE_LIGHTLY_MUSCLED.getName(false)),
			"不给奴隶安排日常锻炼，这将导致其肌肉量每天都会发生变化，"
					+ "最终会使其变得<b style='color:"+Muscle.ONE_LIGHTLY_MUSCLED.getColour().toWebHexString()+";'>"+Muscle.ONE_LIGHTLY_MUSCLED.getName(false)+"</b>。"),
	
	EXERCISE_NORMAL(true,
			Util.capitaliseSentence(Muscle.TWO_TONED.getName(false)),
			"奴隶安排健康的锻炼量，这将导致其肌肉量每天都会发生变化，"
					+ "最终会使其变得<b style='color:"+Muscle.TWO_TONED.getColour().toWebHexString()+";'>"+Muscle.TWO_TONED.getName(false)+"</b>。"),
	
	EXERCISE_TRAINING(false,
			Util.capitaliseSentence(Muscle.THREE_MUSCULAR.getName(false)),
			"给奴隶提供日常健身计划，这将导致其肌肉量每天都会发生变化，"
					+ "最终会使其变得<b style='color:"+Muscle.THREE_MUSCULAR.getColour().toWebHexString()+";'>"+Muscle.THREE_MUSCULAR.getName(false)+"</b>。"),
	
	EXERCISE_BODY_BUILDING(false,
			Util.capitaliseSentence(Muscle.FOUR_RIPPED.getName(false)),
			"给予该奴隶大量的体力锻炼，这将导致其肌肉量每天都大幅增加，"
					+ "最终会使其变得<b style='color:"+Muscle.FOUR_RIPPED.getColour().toWebHexString()+";'>"+Muscle.FOUR_RIPPED.getName(false)+"</b>。"),
	
	
	// Cleanliness:
	
	CLEANLINESS_WASH_CLOTHES(true, "清洗衣物", "让该奴隶时刻保持衣物清洁干净。"),
	CLEANLINESS_WASH_BODY(true, "清洗身体", "让该奴隶时刻保持身体清洁干净，同样也会清空腔穴的精液。"),
	CLEANLINESS_WASH_THOROUGH(false, "去除气味", "该奴隶清洗身体时，也会去除浓重的气味标记。"),
	
	
	// Sleeping:
	SLEEPING_DEFAULT(true, "随时睡眠", "该奴隶可以随时睡眠，日行性种族在夜晚睡眠，而夜行性种族在白天睡眠。") {
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.recalculateSleepHours();
		}
	},
	SLEEPING_NIGHT(false, "夜晚睡眠", "让奴隶在夜晚睡眠，对其并无正面或负面效果。") {
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.recalculateSleepHours();
		}
	},
	SLEEPING_DAY(false, "白天睡眠", "让该奴隶在白天睡眠，对其并无正面或负面效果。") {
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.recalculateSleepHours();
		}
	},
	
	;
	
	private String name;
	private String description;
	private boolean defaultValue;
	
	private SlavePermissionSetting(boolean defaultValue, String name, String description) {
		this.name = name;
		this.description = description;
		this.defaultValue = defaultValue;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getAdditionalDescriptionLines() {
		return 0;
	}
	
	public boolean isDefaultValue() {
		return defaultValue;
	}
	
	public boolean isAvailableForCharacter(GameCharacter character) {
		return true;
	}
	
	public void applyEffectsOnAddition(GameCharacter character) {
	}

	public void applyEffectsOnRemoval(GameCharacter character) {
	}
}
