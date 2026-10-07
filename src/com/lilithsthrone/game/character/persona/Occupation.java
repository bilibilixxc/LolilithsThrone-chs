package com.lilithsthrone.game.character.persona;


import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.0
 * @version 0.3.9.4
 * @author Innoxia
 */
public enum Occupation {
	
	// Unique:
	
	ELEMENTAL(Perk.ELEMENTAL_CORE_OCCUPATION, "元素", "[npc.NameIsFull]由纯粹的奥术能量构成，经由召唤者的灵气获得物理显现。", OccupationTag.HAS_PREREQUISITES),

	NPC_ENFORCER_PATROL_INSPECTOR(Perk.JOB_NPC_ENFORCER_PATROL_INSPECTOR, "执法者", "[npc.NameIs]是执法者中的一员。", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_PATROL),
	NPC_ENFORCER_PATROL_SERGEANT(Perk.JOB_NPC_ENFORCER_PATROL_SERGEANT, "执法者", "[npc.NameIs]是执法者中的一员。", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_PATROL),
	NPC_ENFORCER_PATROL_CONSTABLE(Perk.JOB_NPC_ENFORCER_PATROL_CONSTABLE, "执法者", "[npc.NameIs]是执法者中的一员。", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_PATROL),

	NPC_ENFORCER_SWORD_SUPER(Perk.JOB_NPC_ENFORCER_SWORD_SUPER, "SWORD执法者", "[npc.NameIs]是一名执法者，SWORD成员之一。", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_SWORD),
	NPC_ENFORCER_SWORD_CHIEF_INSPECTOR(Perk.JOB_NPC_ENFORCER_SWORD_CHIEF_INSPECTOR, "SWORD执法者", "[npc.NameIs]是一名执法者，SWORD成员之一。", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_SWORD),
	NPC_ENFORCER_SWORD_INSPECTOR(Perk.JOB_NPC_ENFORCER_SWORD_INSPECTOR, "SWORD执法者", "[npc.NameIs]是一名执法者，SWORD成员之一。", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_SWORD),
	NPC_ENFORCER_SWORD_SERGEANT(Perk.JOB_NPC_ENFORCER_SWORD_SERGEANT, "SWORD执法者", "[npc.NameIs]是一名执法者，SWORD成员之一。", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_SWORD),
	NPC_ENFORCER_SWORD_CONSTABLE(Perk.JOB_NPC_ENFORCER_SWORD_CONSTABLE, "SWORD执法者", "[npc.NameIs]是一名执法者，SWORD成员之一。", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_SWORD),

	NPC_ENFORCER_ORICL_INSPECTOR(Perk.JOB_NPC_ENFORCER_ORICL_INSPECTOR, "ORICL执法者", "[npc.NameIs]是一名执法者，ORICL成员之一。", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_ORICL),
	NPC_ENFORCER_ORICL_SERGEANT(Perk.JOB_NPC_ENFORCER_ORICL_SERGEANT, "ORICLORICL执法者", "[npc.NameIs]是一名执法者，ORICL成员之一。", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_ORICL),
	NPC_ENFORCER_ORICL_CONSTABLE(Perk.JOB_NPC_ENFORCER_ORICL_CONSTABLE, "ORICLORICL执法者", "[npc.NameIs]是一名执法者，ORICL成员之一。", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_ORICL),

	
	NPC_HARPY_MATRIARCH(Perk.JOB_NPC_HARPY_MATRIARCH, "哈比族长", "[npc.NameIsFull]是哈比部族的族长。", OccupationTag.HAS_PREREQUISITES),
	NPC_HARPY_FLOCK_MEMBER(Perk.JOB_NPC_HARPY_FLOCK_MEMBER, "哈比部族成员", "[npc.NameIsFull]是哈比部族的一员。", OccupationTag.HAS_PREREQUISITES),

	NPC_CULTIST(Perk.JOB_NPC_CULTIST, "狂信者", "[npc.NameIs]是“莉莉丝异教”的全职成员。", OccupationTag.HAS_PREREQUISITES),

	NPC_SLAVER_ADMIN(Perk.JOB_NPC_SLAVER_ADMIN, "奴隶管理局监督员", "[npc.NameIsFull]是奴隶管理局的一名监督员。", OccupationTag.HAS_PREREQUISITES),

	NPC_NIGHTCLUB_OWNER(Perk.JOB_NPC_NIGHTCLUB_OWNER, "夜店老板", "[npc.Name]拥有并管理一家受欢迎的夜店。", OccupationTag.HAS_PREREQUISITES),
	NPC_BAR_TENDER(Perk.JOB_NPC_BARMAID, "酒保", "[npc.Name]的工作是酒保。", OccupationTag.EVENING_SHIFT),
	NPC_BOUNCER(Perk.JOB_NPC_BOUNCER, "保安", "[npc.NameIsFull]是一名保安，工作内容是让流氓们远离夜店和酒吧。"),

	NPC_BEAUTICIAN(Perk.JOB_NPC_BEAUTICIAN, "美容师", "[npc.Name]的工作是美容师。"),
	
	NPC_ARCANE_RESEARCHER(Perk.JOB_NPC_ARCANE_RESEARCHER, "奥术研究者", "[npc.Name]将[npc.her]所有的时间花在研究奥术上。", OccupationTag.HAS_PREREQUISITES),

	NPC_CLOTHING_STORE_OWNER(Perk.JOB_NPC_SHOP_MANAGER, "服装店老板", "[npc.NameIs]拥有一家服装店。", OccupationTag.HAS_PREREQUISITES),
	NPC_GYM_OWNER(Perk.JOB_NPC_SHOP_MANAGER, "健身房老板", "[npc.NameIs]拥有一家健身房。", OccupationTag.HAS_PREREQUISITES),
	NPC_STORE_OWNER(Perk.JOB_NPC_SHOP_MANAGER, "商店老板", "[npc.NameIs]拥有一家商店。", OccupationTag.HAS_PREREQUISITES),
	NPC_CASINO_OWNER(Perk.JOB_NPC_SHOP_MANAGER, "赌场老板", "[npc.NameIs]拥有一家赌场。", OccupationTag.HAS_PREREQUISITES),
	NPC_BUSINESS_OWNER(Perk.JOB_NPC_SHOP_MANAGER, "公司老板", "[npc.NameIs]经营自己的事业。", OccupationTag.HAS_PREREQUISITES),
	NPC_TAVERN_OWNER(Perk.JOB_NPC_SHOP_MANAGER, "旅店老板", "[npc.NameIs]拥有一家旅店。", OccupationTag.HAS_PREREQUISITES),
	NPC_FARMER(Perk.JOB_NPC_FARMER, "农民", "[npc.NameIs]是一名农民。", OccupationTag.HAS_PREREQUISITES),
	
	NPC_JOURNALIST(Perk.JOB_NPC_JOURNALIST, "新闻记者", "[npc.NameIs]是一位新闻记者。", OccupationTag.HAS_PREREQUISITES),
	
	REINDEER_OVERSEER(Perk.JOB_NPC_REINDEER_OVERSEER, "监工", "[npc.NameIs]是驯鹿工团的监工，该工团会在冬季迁徙到御城区。", OccupationTag.HAS_PREREQUISITES),

	NPC_SLIME_QUEEN(Perk.JOB_NPC_SLIME_QUEEN, "史莱姆女王", "[npc.NameHas]称呼自己为“史莱姆女王”，并且对将大量屈城区市民转化成史莱姆负责。", OccupationTag.HAS_PREREQUISITES),
	NPC_SLIME_QUEEN_GUARD(Perk.JOB_NPC_SLIME_QUEEN_GUARD, "史莱姆女王的护卫", "[npc.NameIs]是保护[slimeQueen.namePos]之塔的三只史莱姆之一。", OccupationTag.HAS_PREREQUISITES),
	
	NPC_EPONA(Perk.JOB_EPONA, "怀孕轮盘赌经理", "[npc.NameIs]在屈城区的赌场内管理“怀孕轮盘赌”项目。", OccupationTag.HAS_PREREQUISITES),

	NPC_GANG_LEADER(Perk.JOB_GANG_LEADER, "帮派头目", "[npc.NameIsFull]是臭名昭著的犯罪帮派头目。", OccupationTag.HAS_PREREQUISITES),
	NPC_GANG_BODY_GUARD(Perk.JOB_GANG_BODY_GUARD, "帮派保镖", "[npc.NameIsFull]是她的帮派头目的私人保镖。", OccupationTag.HAS_PREREQUISITES),
	NPC_GANG_MEMBER(Perk.JOB_GANG_MEMBER, "帮派成员", "[npc.NameIsFull]是臭名昭著的犯罪帮派的一员。", OccupationTag.HAS_PREREQUISITES),
	
	NPC_STABLE_MISTRESS(Perk.JOB_NPC_STABLE_MISTRESS, "马场主人", "[npc.NameIsFull]负责训练和照顾大量半人马奴隶。", OccupationTag.HAS_PREREQUISITES) {
		@Override
		public String getName(GameCharacter character) {
			if(character==null) {
				return "马场经理";
			} else if(character.isFeminine()) {
				return "马场主";
			}
			return "马场男主人";
		}
	},
	
	NPC_LYSSIETH_GUARD(Perk.JOB_LYSSIETH_GUARD, "莉西丝的护卫", "[npc.NameIsFull]是莉西丝不被承认的女儿其中一员，被指派与保护她母亲的住所。", OccupationTag.HAS_PREREQUISITES),
	
	NPC_ELDER_LILIN(Perk.JOB_ELDER_LILIN, "莉琳长老", "[npc.NameIsFull]是七位莉琳长老之一，被莉莉丝承认的女儿。", OccupationTag.HAS_PREREQUISITES),

	NPC_TAUR_TRANSPORT(Perk.JOB_TAUR_TRANSPORT, "半兽身人运输者", "[npc.Name]使用[npc.her]兽化身体的巨大潜力拉车和运送商品。", OccupationTag.HAS_PREREQUISITES),

	NPC_ELIS_MAYOR(Perk.JOB_NPC_MAYOR, "伊利斯镇长", "[npc.NameIs]是伊利斯的镇长，负责城镇内部与弗洛伊田野周边的幸福与安宁。", OccupationTag.HAS_PREREQUISITES),
	NPC_ASSISTANT(Perk.JOB_NPC_ASSISTANT, "个人助理", "[npc.NameIs]是一名协助[npc.her]的老板完成各类日程的助理。", OccupationTag.HAS_PREREQUISITES),
	
	NPC_LUNETTE_HERD(Perk.JOB_LUNETTE_HERD, "露内特的女儿", "[npc.NameIsFull]露内特的女儿之一，继承了[npc.her]母亲对骚乱与浩劫的钟爱。", OccupationTag.HAS_PREREQUISITES),

	NPC_MUSHROOM_FORAGER(Perk.JOB_NPC_MUSHROOM_FORAGER, "蘑菇寻觅者", "[npc.Name]在屈城区的蝙蝠洞穴内寻觅并贩卖致幻蘑菇。", OccupationTag.HAS_PREREQUISITES),

	NPC_LUNETTE_RECOGNISED_DAUGHTER(Perk.JOB_LUNETTE_RECOGNISED_DAUGHTER, "露内特被承认的女儿", "[npc.NameIsFull]是被露内特承认的女儿，因此她与寻常恶魔相比极为强大。", OccupationTag.HAS_PREREQUISITES),
	
	NPC_AMAZONIAN_QUEEN(Perk.JOB_AMAZONIAN_QUEEN, "亚马逊女王", "[npc.NameIsFull]是亚马逊一族的女王，因此她具有相当强大的力量与战斗能力。", OccupationTag.HAS_PREREQUISITES),
	NPC_AMAZONIAN(Perk.JOB_AMAZONIAN, "亚马逊人", "[npc.NameIsFull]一位亚马逊人，在战斗训练中投入了很多时间。", OccupationTag.HAS_PREREQUISITES),

	NPC_PUGILIST(Perk.JOB_PUGILIST, "拳击运动员", "[npc.NameIsFull]是一位职业拳击手，在战斗中将是一位可怕的对手。", OccupationTag.HAS_PREREQUISITES),

	NPC_LILIN_PAWN(Perk.JOB_ELDER_LILIN_PAWN, "莉琳长老的助手", "[[npc.NameIsFull]代表莉琳携带命令而来。", OccupationTag.HAS_PREREQUISITES),

	NPC_SEX_DOLL(Perk.JOB_SEX_DOLL, "性爱玩偶", "[npc.NameIsFull]是个外表十分真实的性爱玩偶。", OccupationTag.HAS_PREREQUISITES),
	
	NPC_YOUKO(Perk.JOB_YOUKO, "妖狐", "[npc.NameIsFull]是一名来自心林高地的妖狐，大半辈子都在钻研奥术技巧。", OccupationTag.HAS_PREREQUISITES),
	
	// NPC generic histories:

	NPC_UNEMPLOYED(Perk.JOB_UNEMPLOYED, "无业游民", "[npc.NameIsFull]没有工作。", OccupationTag.HAS_PREREQUISITES),

	NPC_SLAVE(Perk.JOB_SLAVE, "奴隶", "[npc.NameIsFull]是一名奴隶，必须承载着[npc.her]主人的意志。", OccupationTag.HAS_PREREQUISITES),
	
	NPC_CAPTIVE(Perk.JOB_CAPTIVE, "俘虏", "[npc.NameHasFull]被人绑架，并被非法监禁着。", OccupationTag.HAS_PREREQUISITES),
	
	NPC_REBEL_FIGHTER(Perk.JOB_NPC_REBEL_FIGHTER, "反抗军斗士", "[npc.NameIsFull]是一名效力于反抗军的战士。", OccupationTag.HAS_PREREQUISITES),
	
	NPC_PROSTITUTE(Perk.JOB_PROSTITUTE, "娼妓", "[npc.NameIsFull]是一名娼妓，靠出卖[npc.her]的肉体赚钱。", OccupationTag.LOWLIFE),
	
	NPC_STRIPPER(Perk.JOB_MISC, "脱衣舞娘", "[npc.Name]是一名脱衣舞娘。", OccupationTag.EVENING_SHIFT) {
		@Override
		public DayOfWeek getStartDay() {
			return DayOfWeek.TUESDAY;
		}
		@Override
		public DayOfWeek getEndDay() {
			return DayOfWeek.SATURDAY;
		}
	},
	
	NPC_MASSAGE_THERAPIST(Perk.JOB_MISC, "按摩理疗师", "[npc.Name]在一家水疗中心里当按摩理疗师。"),
	
	NPC_WAITRESS(Perk.JOB_MISC, "女服务员", "[npc.Name]在一家饭店里当女服务员。") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
	},
	
	NPC_MUSICIAN(Perk.JOB_MISC, "音乐家", "[npc.Name]是一名音乐家。", OccupationTag.HAS_PREREQUISITES),
	
	NPC_FITNESS_INSTRUCTOR(Perk.JOB_MISC, "健身教练", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_MUGGER(Perk.JOB_MUGGER, "抢劫犯", "[npc.NameIsFull]是一名抢劫犯，靠偷其他人的财物为生。", OccupationTag.LOWLIFE),

	NPC_BOUNTY_HUNTER(Perk.JOB_BOUNTY_HUNTER, "赏金猎人", "[npc.NameIsFull]是一名赏金猎人，靠追踪并抓捕通缉犯获得工资。", OccupationTag.HAS_PREREQUISITES),
	
	NPC_CONSTRUCTION_WORKER(Perk.JOB_CONSTRUCTION_WORKER, "建筑工人", "-"),
	NPC_CONSTRUCTION_WORKER_ARCANE(Perk.JOB_CONSTRUCTION_WORKER_ARCANE, "奥术建筑工人", "-", OccupationTag.HAS_PREREQUISITES),

	NPC_MECHANIC(Perk.JOB_MISC, "机械师", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_TEACHER(Perk.JOB_MISC, "教师", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_LIBRARIAN(Perk.JOB_MISC, "图书管理员", "-"),
	
	NPC_UNIVERSITY_STUDENT(Perk.JOB_MISC, "大学生", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_WRITER(Perk.JOB_MISC, "作家", "-"),
	
	NPC_ENGINEER(Perk.JOB_MISC, "工程师", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_ARCHITECT(Perk.JOB_MISC, "建筑师", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_DOCTOR(Perk.JOB_MISC, "医生", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_MAID(Perk.JOB_MISC, "女仆", "-") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
		@Override
		public DayOfWeek getStartDay() {
			return DayOfWeek.MONDAY;
		}
		@Override
		public DayOfWeek getEndDay() {
			return DayOfWeek.SATURDAY;
		}
	},

	NPC_BUTLER(Perk.JOB_MISC, "管家", "-") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return !character.isFeminine();
		}
		@Override
		public DayOfWeek getStartDay() {
			return DayOfWeek.MONDAY;
		}
		@Override
		public DayOfWeek getEndDay() {
			return DayOfWeek.SATURDAY;
		}
	},

	NPC_OFFICE_WORKER(Perk.JOB_NPC_OFFICE_WORKER, "办公室职员", "[npc.Name]为大公司工作，每天都在与[npc.her]的下属、公司财政以及难搞的客户打交道。") {
		@Override
		public String getName(GameCharacter character) {
			if(character==null) {
				return "办公室职员";
			} else if(character.isFeminine()) {
				return "女企业家";
			}
			return "男企业家";
		}
	},
	
	NPC_RECEPTIONIST(Perk.JOB_MISC, "接待员", "[npc.Name]是一名接待员，每天要与许多访客和顾客打交道。"),
	
	NPC_SHOP_ASSISTANT(Perk.JOB_MISC, "商店店员", "-"),
	
	NPC_ARTIST(Perk.JOB_MISC, "艺术家", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_NURSE(Perk.JOB_MISC, "护士", "-") {
//		@Override
//		public boolean isAvailable(GameCharacter character) {
//			return character.isFeminine();
//		}
//		@Override
//		public DayOfWeek getStartDay() {
//			return DayOfWeek.MONDAY;
//		}
		@Override
		public DayOfWeek getEndDay() {
			return DayOfWeek.SATURDAY;
		}
	},
	
	NPC_CHEF(Perk.JOB_MISC, "厨师", "-"),
	
	NPC_ATHLETE(Perk.JOB_MISC, "运动员", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_MODEL(Perk.JOB_MISC, "模特", "-"),

	NPC_TRADER(Perk.JOB_NPC_SHOP_MANAGER, "商人", "[npc.Name]是一名商人，以买卖各种货物为生。", OccupationTag.HAS_PREREQUISITES),
	
	
	
	// Player histories:

    UNEMPLOYED(Perk.JOB_UNEMPLOYED,
            "无业游民",
            "你已经失业有段时间了。",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) || character.getAgeValue() >= 18;
        }
    },

    NO_JOB_YET(Perk.JOB_NO_JOB_YET,
            "unemployed",
            "You've never had a job.",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return character.getAgeValue() < 18;
        }
    },

    AVERAGE_JOE(Perk.JOB_AVERAGE_JOE,
            "Average Joe",
            "You're just an average Joe, working various dead-end jobs and are more or less satisfied with that.",
            OccupationTag.PLAYER_ONLY),

//	PROFESSIONAL_GAMER(Perk.JOB_PROFESSIONAL_GAMER,
//			"professional gamer",
//			"You are a professional gamer that has honed your skills over many years.",
//			OccupationTag.PLAYER_ONLY),
//
//	PORN_STAR(Perk.JOB_PORN_STAR,
//			"porn star",
//			"You work in the adult film industry, and are featured in many pornographic films.",
//			OccupationTag.PLAYER_ONLY) {
//		@Override
//		public boolean isAvailable(GameCharacter character) {
//			return character.getAgeValue() >= 16;
//		}
//	},
//
//	CHILD_PORN_STAR(Perk.JOB_CHILD_PORN_STAR,
//			"child porn star",
//			"You work in the adult film industry, and are featured in many pornographic films featuring children.",
//			OccupationTag.PLAYER_ONLY) {
//		@Override
//		public boolean isAvailable(GameCharacter character) {
//			return character.getAgeValue() < 16;
//		}
//	},
//
//	DAYCARE_DELINQUENT(Perk.JOB_DAYCARE_DELINQUENT,
//			"daycare delinquent",
//			"You are the boss at your local daycare and everyone there has learned to listen when you speak.",
//			OccupationTag.PLAYER_ONLY) {
//		@Override
//		public boolean isAvailable(GameCharacter character) {
//			return character.getAgeValue() < 10;
//		}
//	},
//
//	DELINQUENT(Perk.JOB_DELINQUENT,
//			"delinquent",
//			"You're a delinquent with a penchant for causing trouble.",
//			OccupationTag.PLAYER_ONLY) {
//		@Override
//		public boolean isAvailable(GameCharacter character) {
//			return character.getAgeValue() >= 10 && character.getAgeValue() < 18;
//		}
//	},
//
//	HOMESCHOOLED(Perk.JOB_HOMESCHOOLED,
//			"homeschooled",
//			"You were homeschooled, and have had limited exposure to the outside world due to overprotective parents.",
//			OccupationTag.PLAYER_ONLY) {
//		@Override
//		public boolean isAvailable(GameCharacter character) {
//			return character.getAgeValue() < 18;
//		}
//	},
//
//	SCHOOLYARD_BULLY(Perk.JOB_SCHOOLYARD_BULLY,
//			"schoolyard bully",
//			"You command the schoolyard, and everyone has learned to fall in line.",
//			OccupationTag.PLAYER_ONLY) {
//		@Override
//		public boolean isAvailable(GameCharacter character) {
//			return character.getAgeValue() < 18;
//		}
//	},
//
//	LITTLE_PRINCESS(Perk.JOB_LITTLE_PRINCESS,
//			"Little Princess",
//			"Your parents spoiled you quite a bit, and as a result you are naive to the ways of the world and always expect things to go your way."
//				+ " You're prone to throwing tantrums when you don't get what you want.",
//			OccupationTag.PLAYER_ONLY) {
//		@Override
//		public boolean isAvailable(GameCharacter character) {
//			return character.getAgeValue() < 18;
//		}
//	},
//
//	PREP_SCHOOL(Perk.JOB_PREP_SCHOOL,
//			"prep school student",
//			"You have been attending a college preparation school for some time now, and are preparing yourself for the future.",
//			OccupationTag.PLAYER_ONLY) {
//		@Override
//		public boolean isAvailable(GameCharacter character) {
//			return character.getAgeValue() > 5 && character.getAgeValue() < 18;
//		}
//	},
//
//	SEGREGATED_SCHOOLING(Perk.JOB_SEGREGATED_SCHOOLING,
//			"segregated school student",
//			"You've attended segregated schools your entire life and have rarely had any chances to interact with those of the opposite gender.",
//			OccupationTag.PLAYER_ONLY) {
//		@Override
//		public boolean isAvailable(GameCharacter character) {
//			return character.getAgeValue() > 5 && character.getAgeValue() < 18;
//		}
//	},

    OFFICE_WORKER(Perk.JOB_OFFICE_WORKER,
            "办公室职员",
            "你在当地的一栋办公楼上班，处理文书工作、应答电话与邮件，什么事都要干一点。",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) || character.getAgeValue() >= 18;
        }
    },

    OJISAN(Perk.JOB_OJISAN,
            "ojisan",
            "You work in a local office, handling paperwork, answering phonecalls and emails, and generally doing a little bit of everything. You also have secretly been planning on kidnapping a little girl or boy to have your way with.",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) || character.getAgeValue() >= 40;
        }
    },

    STUDENT_COUNCIL(Perk.JOB_STUDENT_COUNCIL,
            "student council",
            "You are in your school's student council, handling paperwork, emails, and passing on concerns of the student body.",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) || character.getAgeValue() >= 6 && character.getAgeValue() <= 18;
        }
    },

    STUDENT(Perk.JOB_STUDENT,
            "university student",
            "你是市内的一所大学的学生，但是你还没决定好专业方向。",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) || character.getAgeValue() >= 18;
        }
    },

    GSTUDENT(Perk.JOB_GSTUDENT,
            "grade school student",
            "You're a student at the city's grade school.",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) || character.getAgeValue() >= 6 && character.getAgeValue() <= 10;
        }
    },

    MSTUDENT(Perk.JOB_MSTUDENT,
            "middle school student",
            "You're a student at the city's middle school, getting to that weird awkward age.",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) || character.getAgeValue() >= 11 && character.getAgeValue() <= 14;
        }
    },

    HSTUDENT(Perk.JOB_HSTUDENT,
            "high school student",
            "You're a student at the city's high school, preparing for your adult life.",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) || character.getAgeValue() >= 15 && character.getAgeValue() <= 18;
        }
    },

    MUSICIAN(Perk.JOB_MUSICIAN,
            "音乐家",
            "你是一名音乐家，也能弹奏许多种类的乐器，你也是一名好歌手。",
            OccupationTag.PLAYER_ONLY),

    BAND(Perk.JOB_BAND,
            "Band member",
            "You're a band member, and as well as being able to play a wide variety of instruments, you are also a very good singer.",
            OccupationTag.PLAYER_ONLY),

    TEACHER(Perk.JOB_TEACHER,
            "教师",
            "你是一名教师，在当地的学校教了几年书。",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) || character.getAgeValue() >= 21;
        }
    },

    WRITER(Perk.JOB_WRITER,
            "作家",
            "你是一名作家，近几个月一直在写你的新小说。",
            OccupationTag.PLAYER_ONLY),

    CHEF(Perk.JOB_CHEF,
            "厨师长",
            "你是当地一家饭店的厨师长。",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) || character.getAgeValue() >= 16;
        }
    },

    CONSTRUCTION_WORKER(Perk.JOB_PLAYER_CONSTRUCTION_WORKER,
            "建筑工人",
            "你是经验深厚且技术熟练的建筑工人。",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) || character.getAgeValue() >= 14;
        }
    },

    SOLDIER(Perk.JOB_SOLDIER,
            "士兵",
            "你是一名士兵，目前正充分享受你的假期。",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) || character.getAgeValue() >= 18;
        }
    },

    JROTC(Perk.JOB_JROTC,
            "Junior Reserves Officers Training Corps",
            "You're enrolled in JROTC, and are currently making the most of your school vacation.",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) || character.getAgeValue() >= 10 && character.getAgeValue() <= 18;
        }
    },

    ATHLETE(Perk.JOB_ATHLETE,
            "运动员",
            "你是一名运动员，正为下次赛事而训练。",
            OccupationTag.PLAYER_ONLY),

    ARISTOCRAT(Perk.JOB_ARISTOCRAT,
            "贵族",
            "你出身于钟鸣鼎食的世家大族，从出生到现在也没工作过一天。",
            OccupationTag.PLAYER_ONLY),

    BUTLER(Perk.JOB_BUTLER,
            "管家",
            "你是一名管家，被富有的家庭雇佣来监督女仆和应对访客。",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return !Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) ? character.getAgeValue() >= 13: !character.isFeminine();
        }
    },

    MAID(Perk.JOB_MAID,
            "女仆",
            "你是一名女仆，被富有的家庭雇佣来保持他们豪宅的整洁。",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return !Main.getProperties().hasValue(PropertyValue.vanillaJobConditions) ? character.getAgeValue() >= 13 : character.isFeminine();
        }
    },

    TOURIST(Perk.JOB_TOURIST,
            "美国游客",
            "今年的假期里你准备去英国玩玩。",
            OccupationTag.PLAYER_ONLY) {
        @Override
        public boolean isAvailable(GameCharacter character) {
            return Main.game.isSillyModeEnabled();
        }
    };


    private static List<Occupation> historiesList;
	
	public static List<Occupation> getAvailableHistories(GameCharacter character) {
		historiesList = new ArrayList<>();

		for(Occupation history : Occupation.values()) {
			if(history.isAvailable(character) && (character.isPlayer()?history.isAvailableToPlayer():!history.isAvailableToPlayer())) {
				historiesList.add(history);
			}
		}
		
		return historiesList;
	}


	protected static boolean[] noWorkHours = new boolean[24];
	protected static boolean[] daylightWorkHours = new boolean[24];
	protected static boolean[] eveningWorkHours = new boolean[24];
	protected static boolean[] nightWorkHours = new boolean[24];
	
	static {
		for(int i=0; i<8; i++) {
			daylightWorkHours[9+i] = true;
			
			int hour = (18+i)%24;
			eveningWorkHours[hour] = true;
			
			hour = (21+i)%24;
			nightWorkHours[hour] = true;
		}
	}

	private String name;
	private String description;
	private AbstractPerk associatedPerk;
	private List<OccupationTag> occupationTags;

	private Occupation(AbstractPerk associatedPerk,
			String name,
			String description,
			OccupationTag... occupationTags) {
		
		this.associatedPerk = associatedPerk;
		this.name = name;
		this.description = description;
		
		this.occupationTags = new ArrayList<>();
		for(OccupationTag tag : occupationTags) {
			this.occupationTags.add(tag);
		}
	}
	
	public boolean isAvailable(GameCharacter character) {
		return !occupationTags.contains(OccupationTag.HAS_PREREQUISITES);
	}

	public void applyExtraEffects(GameCharacter character) {
	}

	public void revertExtraEffects(GameCharacter character) {
	}

	public boolean isAvailableToPlayer() {
		return occupationTags.contains(OccupationTag.PLAYER_ONLY);
	}

	public AbstractPerk getAssociatedPerk() {
		return associatedPerk;
	}
	
	public String getName(GameCharacter character) {
		return name;
	}

	public String getDescription(GameCharacter character) {
		return UtilText.parse(character, description);
	}

	public boolean isLowlife() {
		return occupationTags.contains(OccupationTag.LOWLIFE);
	}

	public List<OccupationTag> getOccupationTags() {
		return occupationTags;
	}
	
	public boolean isAtWork(int hour) {
		return Main.game.getDateNow().getDayOfWeek().getValue()>=getStartDay().getValue()
				&& Main.game.getDateNow().getDayOfWeek().getValue()<=getEndDay().getValue()
				&& getWorkHours()[hour];
	}
	
	public boolean[] getWorkHours() {
		if(this.getOccupationTags().contains(OccupationTag.LOWLIFE) || this==NPC_UNEMPLOYED || this==UNEMPLOYED) {
			return noWorkHours;
		}
		if(this.getOccupationTags().contains(OccupationTag.EVENING_SHIFT)) {
			return eveningWorkHours;
		}
		if(this.getOccupationTags().contains(OccupationTag.NIGHT_SHIFT)) {
			return nightWorkHours;
		}
		return daylightWorkHours;
	}
	
	public int getWorkHourStart() {
		for(int i=0; i<24; i++) {
			int hour = (6+i)%24;
			if(getWorkHours()[hour]) {
				return 6+i;
			}
		}
		return 0;
	}
	
	public int getWorkHourEnd() {
		return (getWorkHourStart()+8)%24;
	}
	
	public DayOfWeek getStartDay() {
		return DayOfWeek.MONDAY;
	}

	public DayOfWeek getEndDay() {
		return DayOfWeek.FRIDAY;
	}
}
