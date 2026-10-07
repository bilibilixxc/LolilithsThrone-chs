package com.lilithsthrone.game.combat.spells;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.1
 * @version 0.2.4
 * @author Innoxia
 */
public enum SpellSchool {

	EARTH("大地",
			"<p>"
				+ "大地学派的法术专注于操纵固态物体和纯粹的力的波动。"
			+ "</p>"
			+ "<p>"
				+ "和其它学派一样，大地学派施法者大都是恶魔。他们可以使用念力来搬运重物、建造建筑，从而赚取可观的薪水。"
				+ "或许是由于其报酬丰厚的实用性，大地学派是学习最为广泛、最受欢迎的一种奥术学派。"
			+ "</p>"
			+ "<p>"
				+ "驾驭土系法术的先决条件是能够自由操纵固体、非有机物质。"
				+ "在所有土系法术书的导论中都会提到一种能力，拥有恶魔水平灵气的生物都可以轻松掌握，那便是允许施法者改变任意物体的颜色和材质。"
			+ "</p>",
			"随意染色衣物、重铸武器。",
			PresetColour.BASE_BROWN),

	WATER("激流",
			"<p>"
				+ "激流学派的法术专注于向液体中注入奥术能量，从而操纵其温度和运动。"
			+ "</p>"
			+ "<p>"
				+ "和其他学派一样，激流学派施法者大都是恶魔。他们可以用法术协助维护航路、维修或安装管道。"
				+ "尽管不太优雅，但激流学派的学徒只需一小会儿就能完成这些任务，比普通人靠人力快得多，从而也能从工作中赚取不少钱财。"
			+ "</p>"
			+ "<p>"
				+ "激流学派的学徒能够轻而易举地控制所有流体，这使得他们不需要消耗奥术精华就可以附魔液体相关的药水。"
			+ "</p>",
			"所有液体相关的附魔均免费。",
			PresetColour.BASE_AQUA),
	
	AIR("大气",
			"<p>"
				+ "大气学派的法术专注于操纵气体的温度和运动。"
			+ "</p>"
			+ "<p>"
				+ "和其他学派一样，大气学派施法者大都是恶魔。但他们却没有多少机会将法术用于日常事务，"
					+ "其人数相比大地学派或激流学派可以说是少之又少。"
				+ "风系法术唯一的常规用途就是调控房间的温度，这使得住户能够躲避冬日的严寒或者夏日的酷暑。"
			+ "</p>"
			+ "<p>"
				+ "大气学派的学徒能够轻而易举地控制周身空气的温度，从来不会觉得太冷或太热。"
			+ "</p>",
			Attribute.HEALTH_MAXIMUM.getName()+"和"+Attribute.MANA_MAXIMUM.getName()+"的被动恢复速度加倍。",
			PresetColour.BASE_BLUE_LIGHT),
	
	FIRE("烈火",
			"<p>"
				+ "烈火学派，正如其名，纯粹专注于召唤奥术火焰。"
			+ "</p>"
			+ "<p>"
				+ "虽然奥术火焰在熔炼和其他与热量相关的行业中均有使用，但是其战斗方面的应用却让烈火学派在恶魔社会中声名狼藉，经常被认为是粗野且令人反感的。"
				+ "因此选择研习烈火学派法术的也只有那些真正对奥术研究感兴趣的恶魔，在热量相关产业工作的恶魔，或者经常战斗的恶魔。"
			+ "</p>"
			+ "<p>"
				+ "通过牺牲部分生命，烈火学派的学徒能够在灵气耗尽时继续释放火系法术。"
			+ "</p>",
			"灵气耗尽时，火系法术将会以1/4的消耗，消耗"+Attribute.HEALTH_MAXIMUM.getName()+"释放。"
				+ "“黑暗”状态效果也会被无效化。",
			PresetColour.BASE_ORANGE),

	ARCANE("奥术",
			"<p>"
				+ "奥术系的法术专注于驾驭奥术能量最纯粹的形式，既可以影响他人的性欲，还可以施展各色强大的能力。"
			+ "</p>"
			+ "<p>"
				+ "由于为大众所熟知的法术基本都与影响性欲有关，奥术学派并不被多数恶魔重视，因为他们身体的魅力在这方面就已经绰绰有余。"
				+ "绝大多数奥术学派的学徒都身居莉莉丝异教，他们认为奥术学派是唯一被莉莉丝本人所青睐的学派。"
			+ "</p>"
			+ "<p>"
				+ "一旦有哪个前途无量的学生掌握了基本的奥术系法术，他就会意识到自己能够感受到交织在整个世界中的奥术之潮的起伏，"
					+ "并且能够精确地预知下一次奥术风暴将会在何时爆发。"
			+ "</p>",
			"知晓下次奥术风暴爆发的准确时间。",
			PresetColour.GENERIC_ARCANE);
	
	
	private String name;
	private String description;
	private String passiveBuff;
	private Colour colour;
	
	private SpellSchool(String name, String description, String passiveBuff, Colour colour) {
		this.name = name;
		this.description = description;
		this.passiveBuff = passiveBuff;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getPassiveBuff() {
		return passiveBuff;
	}

	public Colour getColour() {
		return colour;
	}
	
}
