package com.lilithsthrone.game.character.effects;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public class Perk {
	
	// NPC Histories:
	
	public static AbstractPerk JOB_MISC = new AbstractPerk(20,
			true,
			"秘术",
			PerkCategory.JOB,
			"perks/jobs/prostitute",
			PresetColour.BASE_PINK,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "。";//TODO
		}
	};

	public static AbstractPerk JOB_NPC_HARPY_MATRIARCH = new AbstractPerk(20,
			true,
			"统治天空的女王",
			PerkCategory.JOB,
			"perks/jobs/npc_harpy_matriarch",
			PresetColour.RACE_HARPY,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 25),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]凭借[npc.her]的天生丽质，以及为统治而不惜一切代价的精神，跃升为哈比族群的族长。");
		}
	};

	public static AbstractPerk JOB_NPC_HARPY_FLOCK_MEMBER = new AbstractPerk(20,
			true,
			"啄食顺序",
			PerkCategory.JOB,
			"perks/jobs/npc_harpy_flock_member",
			PresetColour.RACE_HARPY,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 1)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]是哈比部族的成员，因此在奉承和背后捅人刀子的方面经验丰富。");
		}
	};

	
	// Enforcers:
	
	public static AbstractPerk JOB_NPC_ENFORCER_PATROL_INSPECTOR = new AbstractPerk(20,
			true,
			"执法者：巡逻督察",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_inspector",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.MAJOR_ARCANE, 5),
					new Value<>(Attribute.DAMAGE_UNARMED, 10),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 10),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 10)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]在执法者前线巡逻部门中担任“督察”一职，接受过匹配的战斗训练。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_PATROL_SERGEANT = new AbstractPerk(20,
			true,
			"执法者：巡逻警长",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_sergeant",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.DAMAGE_UNARMED, 5),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]在执法者的前线巡逻部门中担任“巡佐”一职，接受过有限的战斗训练。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_PATROL_CONSTABLE = new AbstractPerk(20,
			true,
			"执法者：巡逻警员",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_constable",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 1),
					new Value<>(Attribute.DAMAGE_UNARMED, 5),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]在执法者的前线巡逻部门中担任“警员”一职，接受过的战斗训练相当有限。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_SWORD_SUPER = new AbstractPerk(20,
			true,
			"执法者：SWORD警司",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_superintendent",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25),
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.DAMAGE_UNARMED, 35),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 35),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 35)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]在执法者的“特种武器行动反应部”中担任“警司”一职，接受过大量的实战训练。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_SWORD_CHIEF_INSPECTOR = new AbstractPerk(20,
			true,
			"执法者：SWORD总督察",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_chief_inspector",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 20),
					new Value<>(Attribute.MAJOR_ARCANE, 20),
					new Value<>(Attribute.DAMAGE_UNARMED, 30),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 30),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 30)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]在执法者的“特种武器行动反应部”担任“总督察”一职，接受过大量的实战训练。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_SWORD_INSPECTOR = new AbstractPerk(20,
			true,
			"执法者：SWORD督察",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_inspector",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15),
					new Value<>(Attribute.MAJOR_ARCANE, 15),
					new Value<>(Attribute.DAMAGE_UNARMED, 25),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 25),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 25)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]在执法者的“特殊武器和行动反应部”中担任“督察”一职，并接受过大量的实战训练。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_SWORD_SERGEANT = new AbstractPerk(20,
			true,
			"执法者：SWORD警长",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_sergeant",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.DAMAGE_UNARMED, 20),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 20),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 20)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]在执法者的“特种武器行动反应部”中担任“警长”一职，接受过大量的战斗训练。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_SWORD_CONSTABLE = new AbstractPerk(20,
			true,
			"执法者：SWORD警员",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_constable",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.MAJOR_ARCANE, 5),
					new Value<>(Attribute.DAMAGE_UNARMED, 15),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 15),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 15)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]在执法者的“特种武器行动反应部”担任“警员”一职，接受过大量的战斗训练。");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_ORICL_INSPECTOR = new AbstractPerk(20,
			true,
			"执法者：ORICL督察",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_inspector",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15),
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.DAMAGE_POISON, 25),
					new Value<>(Attribute.DAMAGE_LUST, 25),
					new Value<>(Attribute.DAMAGE_UNARMED, 10),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 10),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 10)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]在执法者的“王国情报局”中担当“督察”一职，知道很多让人开口的方法……");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_ORICL_SERGEANT = new AbstractPerk(20,
			true,
			"执法者：ORICL警长",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_sergeant",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_ARCANE, 20),
					new Value<>(Attribute.DAMAGE_POISON, 20),
					new Value<>(Attribute.DAMAGE_LUST, 20),
					new Value<>(Attribute.DAMAGE_UNARMED, 5),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]在执法者的“王国情报局”中担任“警长”一职，知道很多让人开口的方法……");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_ORICL_CONSTABLE = new AbstractPerk(20,
			true,
			"执法者：ORICL警员",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_constable",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.MAJOR_ARCANE, 15),
					new Value<>(Attribute.DAMAGE_POISON, 15),
					new Value<>(Attribute.DAMAGE_LUST, 15),
					new Value<>(Attribute.DAMAGE_UNARMED, 5),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]在执法者的“王国情报局”中担任“警员”一职，知道很多让人开口的方法……");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_CULTIST = new AbstractPerk(20,
			true,
			"莉莉丝的崇拜者",
			PerkCategory.JOB,
			"perks/jobs/npc_cultist",
			PresetColour.CLOTHING_BLACK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 15),
					new Value<>(Attribute.DAMAGE_SPELLS, 25),
					new Value<>(Attribute.DAMAGE_LUST, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是“莉莉丝异教”的正式成员，为了更好地拜谒[npc.her]女神，[npc.sheHasFull]花费了不少时间来训练[npc.her]施法和诱惑的能力。");
		}
	};

	public static AbstractPerk JOB_NPC_SLAVER_ADMIN = new AbstractPerk(20,
			true,
			"阴影中的监视者",
			PerkCategory.JOB,
			"perks/jobs/npc_slave_admin",
			PresetColour.CLOTHING_BLACK_STEEL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 100),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25),
					new Value<>(Attribute.RESISTANCE_LUST, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"奴隶巷中“奴隶行政管理”大楼的经理[npc.NameIsFull]，虽然[npc.she]表现得足够友好，但能坐上这个位置，肯定不只是对人好那么简单……");
		}
	};

	public static AbstractPerk JOB_NPC_BOUNCER = new AbstractPerk(20,
			true,
			"保安",
			PerkCategory.JOB,
			"perks/jobs/npc_bouncer",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, 15),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 15),
					new Value<>(Attribute.HEALTH_MAXIMUM, 25)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是一名保安，在夜总会和酒吧工作时积累了大量的格斗经验。");
		}
	};

	public static AbstractPerk JOB_NPC_BARMAID = new AbstractPerk(20,
			true,
			"酒保",
			PerkCategory.JOB,
			"perks/jobs/npc_barmaid",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 15),
					new Value<>(Attribute.RESISTANCE_LUST, 2)),
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner==null) {
				return "酒保";
			}
			if(owner.isFeminine()) {
				return super.getName(owner);
			}
			return "酒保";
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.name]得益于担任酒保的工作经验，[npc.her]知晓调情的所有技巧，也知道该如何抵制好色酒鬼的挑逗。");
		}
	};

	public static AbstractPerk JOB_NPC_BEAUTICIAN = new AbstractPerk(20,
			true,
			"美容师",
			PerkCategory.JOB,
			"perks/jobs/npc_beautician",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 15),
					new Value<>(Attribute.RESISTANCE_LUST, 2)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.Name]知道该如何让人们看起来处在最美的状态。");
		}
	};
	
	public static AbstractPerk JOB_NPC_NIGHTCLUB_OWNER = new AbstractPerk(20,
			true,
			"大老板",
			PerkCategory.JOB,
			"perks/jobs/npc_nightclub_owner",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是御城区最受欢迎夜总会之一的老板，非常富有，美人俊郎招手即来。");
		}
	};

	public static AbstractPerk JOB_NPC_ARCANE_RESEARCHER = new AbstractPerk(20,
			true,
			"隐世奥术研究者",
			PerkCategory.JOB,
			"perks/jobs/npc_arcane_researcher",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.DAMAGE_SPELLS, 50),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameHasFull]一生中的大部分时间都致力于研究奥术，试图揭开奥术蕴含的秘密。");
		}
	};

	public static AbstractPerk JOB_NPC_SHOP_MANAGER = new AbstractPerk(20,
			true,
			"经理",
			PerkCategory.JOB,
			"perks/jobs/npc_shop_manager",
			PresetColour.CURRENCY_GOLD,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.ENERGY_SHIELDING, 1)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"确保事业成功的压力让[npc.Name]不得不投入许多精力，在这个过程中，[npc.her]产生了抵触情绪。");
		}
	};
        
	public static AbstractPerk JOB_NPC_REBEL_FIGHTER = new AbstractPerk(20,
			true,
			"反抗军斗士",
			PerkCategory.JOB,
			"perks/jobs/npc_rebel_fighter",
			PresetColour.CLOTHING_RED_DARK,
			Util.newHashMapOfValues(
				new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
				new Value<>(Attribute.RESISTANCE_LUST, 20),
				new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]经受过的训练让[npc.herHim]对莉莉丝的部队有一定的防御能力。");
		}
	};
	
	public static AbstractPerk JOB_NPC_FARMER = new AbstractPerk(20,
			true,
			"世界粮仓",
			PerkCategory.JOB,
			"perks/jobs/npc_farmer",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5),
					new Value<>(Attribute.RESISTANCE_FIRE, 1),
					new Value<>(Attribute.RESISTANCE_ICE, 1)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"#IF(!game.isSillyMode())"
						+ "为了让田野焕发生机，[npc.name]艰辛劳作，变得强壮而坚韧。对于严寒和烈日，[npc.she]毫不陌生。"
					+ "#ELSE"
						+ "[npc.NameHasFull]一台全新的联合收割机，[npc.she]会给你钥匙，但前提是[npc.she]能开着拖拉机穿过你的干草堆，呜——呜！"
					+ "#ENDIF");
		}
	};
	
	public static AbstractPerk JOB_NPC_JOURNALIST = new AbstractPerk(20,
			true,
			"刀尖起舞",
			PerkCategory.JOB,
			"perks/jobs/npc_journalist",
			Util.newArrayListOfValues(PresetColour.CLOTHING_DESATURATED_BROWN, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_WHITE),
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 5),
					new Value<>(Attribute.CRITICAL_DAMAGE, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "在一个政府严格控制新闻报道，只允许官方版本的新闻发布的社会中，记者们必须小心地在刀尖上跳舞……");
		}
	};
	
	public static AbstractPerk JOB_NPC_OFFICE_WORKER = new AbstractPerk(20,
			true,
			"一门好生意",
			PerkCategory.JOB,
			"perks/jobs/npc_office_worker",
			PresetColour.CURRENCY_GOLD,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "要完成目标、管理难缠的同事和客户，这些持续不断的压力增强了[npc.namePos]的耐力。");
		}
	};

	public static AbstractPerk JOB_NPC_REINDEER_OVERSEER = new AbstractPerk(20,
			true,
			"抗寒",
			PerkCategory.JOB,
			"perks/jobs/npc_reindeer_overseer",
			PresetColour.RACE_REINDEER_MORPH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25),
					new Value<>(Attribute.RESISTANCE_ICE, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是驯鹿劳动队的监督员，不仅拥有强壮的躯体，还具有惊人的抗寒能力。");
		}
	};

	public static AbstractPerk JOB_ELDER_LILIN = new AbstractPerk(20,
			true,
			"不可捉摸",
			PerkCategory.JOB,
			"perks/jobs/elder_lilin",
			PresetColour.RACE_LILIN,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, 100),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 100),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 100),
					new Value<>(Attribute.DAMAGE_SPELLS, 100),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 100),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 100),
					new Value<>(Attribute.DAMAGE_FIRE, 100),
					new Value<>(Attribute.DAMAGE_ICE, 100),
					new Value<>(Attribute.DAMAGE_POISON, 100),
					new Value<>(Attribute.DAMAGE_LUST, 100),
					new Value<>(Attribute.ENERGY_SHIELDING, 250),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 250),
					new Value<>(Attribute.RESISTANCE_FIRE, 250),
					new Value<>(Attribute.RESISTANCE_ICE, 250),
					new Value<>(Attribute.RESISTANCE_POISON, 250),
					new Value<>(Attribute.RESISTANCE_LUST, 250)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是七位莉琳长老之一，掌握着凡人只能幻想的力量。");
		}
	};

	public static AbstractPerk JOB_NPC_SLIME_QUEEN = new AbstractPerk(20,
			true,
			"蜂王浆",
			PerkCategory.JOB,
			"perks/jobs/npc_slime_queen",
			Util.newArrayListOfValues(PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_RED_VERY_DARK, PresetColour.CLOTHING_GOLD),
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 50)),
			null,
			null,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是统治着所有史莱姆的女王，能使用变形能力令[npc.her]化作美丽的真正代表。");
		}
	};

	public static AbstractPerk JOB_NPC_SLIME_QUEEN_GUARD = new AbstractPerk(20,
			true,
			"史莱姆仆役",
			PerkCategory.JOB,
			"perks/jobs/npc_slime_queen_guard",
			PresetColour.RACE_REINDEER_MORPH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 25),
					new Value<>(Attribute.HEALTH_MAXIMUM, 25)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是史莱姆女王的贴身护卫之一，受过良好的剑术训练。");
		}
	};

	public static AbstractPerk JOB_EPONA = new AbstractPerk(20,
			true,
			"生殖皇后",
			PerkCategory.JOB,
			"perks/jobs/npc_epona",
			PresetColour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, 100),
					new Value<>(Attribute.VIRILITY, 100)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameHasFull]将一生都献给了帮助他人成为父母的愿望之中，目前正在屈城区的“赌场”中经营“怀孕轮盘赌”游戏，以助崇高目标的实现。");
		}
	};
	
	public static AbstractPerk JOB_GANG_LEADER = new AbstractPerk(20,
			true,
			"残酷领袖",
			PerkCategory.JOB,
			"perks/jobs/npc_rat_gang",
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_GOLD),
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 50),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 50),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 25),
					new Value<>(Attribute.RESISTANCE_POISON, 10)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.nameHasFull]拥有着成为犯罪首领应有的力量与狡诈，且从无数次挑战[npc.her]权势的尝试中幸存下来了。");
		}
	};
	
	public static AbstractPerk JOB_GANG_BODY_GUARD = new AbstractPerk(20,
			true,
			"尖牙",
			PerkCategory.JOB,
			"perks/jobs/npc_rat_gang",
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_SILVER),
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 25),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 10),
					new Value<>(Attribute.RESISTANCE_POISON, 10)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.Name]公认为[npc.her]帮派中最有权势的成员之一，因此赢得了首领护卫一职。");
		}
	};
	
	public static AbstractPerk JOB_GANG_MEMBER = new AbstractPerk(20,
			true,
			"我们中的一员",
			PerkCategory.JOB,
			"perks/jobs/npc_rat_gang",
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_COPPER),
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 10),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5),
					new Value<>(Attribute.RESISTANCE_POISON, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是犯罪团伙的成员，涉及多项打架斗殴行为。");
		}
	};

	public static AbstractPerk JOB_NPC_STABLE_MISTRESS = new AbstractPerk(20,
			true,
			"训马师",
			PerkCategory.JOB,
			"perks/jobs/npc_stable_mistress",
			Util.newArrayListOfValues(
					PresetColour.RACE_HORSE_MORPH),
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 25),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.nameIsFull]努力训练与照顾着大量的半人马奴隶，因此身体非常健康，而且非常清楚该如何对付任何不守规矩的人。");
		}
	};
	
	public static AbstractPerk JOB_LYSSIETH_GUARD = new AbstractPerk(20,
			true,
			"孝顺女儿",
			PerkCategory.JOB,
			"perks/jobs/npc_lyssieth_guard",
			Util.newArrayListOfValues(
					PresetColour.DAMAGE_TYPE_PHYSICAL,
					PresetColour.CLOTHING_STEEL,
					PresetColour.CLOTHING_BLACK),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25),
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 50)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是一名莉琳长老，莉西丝的女儿，她的任务是保护母亲的宫殿不受不速之客的侵扰。");
		}
	};
	
	public static AbstractPerk JOB_TAUR_TRANSPORT = new AbstractPerk(20,
			true,
			"继续拉！",
			PerkCategory.JOB,
			"perks/jobs/taur_transport",
			PresetColour.BASE_GOLD,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.nameHasFull]花了大量时间用似马的身躯拉车和搬运货物，因此积累了相当不错的体能。");
		}
	};
	
	public static AbstractPerk JOB_NPC_MAYOR = new AbstractPerk(20,
			true,
			"责任的重担",
			PerkCategory.JOB,
			"perks/jobs/mayor",
			Util.newArrayListOfValues(
					PresetColour.BASE_GOLD,
					PresetColour.BASE_AQUA,
					PresetColour.BASE_GOLD),
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 25),
					new Value<>(Attribute.RESISTANCE_LUST, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.nameHasFull]不得不做出无数影响成千上万人生活的艰难决定，因此[npc.her]的信念更加坚定，能够满怀信心地行事。");
		}
	};
	
	public static AbstractPerk JOB_NPC_ASSISTANT = new AbstractPerk(20,
			true,
			"援助之手",
			PerkCategory.JOB,
			"perks/jobs/assistant",
			PresetColour.BASE_GREEN,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"作为上级的私人助理，[npc.nameHasFull]始终保持着身体的最佳状态，以便随时准备提供帮助。");
		}
	};
	
	public static AbstractPerk JOB_LUNETTE_HERD = new AbstractPerk(20,
			true,
			"无情劫掠者",
			PerkCategory.JOB,
			"perks/jobs/lunette_raider",
			Util.newArrayListOfValues(
					PresetColour.BASE_PURPLE,
					PresetColour.BASE_CRIMSON),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 25),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 25),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"这只恶魔半人马曾伴随在露内特身侧接受训练，努力变得像[npc.her]母亲一样无情与破坏力十足！");
		}
	};

	public static AbstractPerk JOB_NPC_MUSHROOM_FORAGER = new AbstractPerk(20,
			true,
			"高超的菌类学家",
			PerkCategory.JOB,
			"perks/jobs/mushroom_forager",//TODO
			Util.newArrayListOfValues(
					PresetColour.BASE_BLUE_LIGHT,
					PresetColour.BASE_PINK,
					PresetColour.BASE_GREEN),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.RESISTANCE_POISON, 25)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.namePos]的身体具有很强的抗毒能力，通过艰苦的、有时甚至是令人作呕的过程来发现哪些蘑菇是可食用的、迷幻的或有毒的。");
		}
	};

	public static AbstractPerk JOB_LUNETTE_RECOGNISED_DAUGHTER = new AbstractPerk(20,
			true,
			"露内特的最爱",
			PerkCategory.JOB,
			"perks/jobs/lunette_raider",
			Util.newArrayListOfValues(
					PresetColour.BASE_GOLD,
					PresetColour.BASE_CRIMSON),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25),
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.MAJOR_CORRUPTION, 100),
					new Value<>(Attribute.DAMAGE_LUST, 25),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是露内特承认的女儿，比普通恶魔要强大得多。");
		}
	};

	public static AbstractPerk JOB_ELDER_LILIN_PAWN = new AbstractPerk(20,
			true,
			"忠心的小卒",
			PerkCategory.JOB,
			"perks/jobs/elder_lilin_pawn",
			Util.newArrayListOfValues(PresetColour.RACE_LILIN),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15),
					new Value<>(Attribute.MAJOR_ARCANE, 15),
					new Value<>(Attribute.MAJOR_CORRUPTION, 25),
					new Value<>(Attribute.DAMAGE_LUST, 15)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是一位莉琳长老重要的忠诚棋子，因此主人赋予了其一小部分的力量。");
		}
	};

	public static AbstractPerk JOB_SEX_DOLL = new AbstractPerk(20,
			true,
			"顺服的玩偶",
			PerkCategory.JOB,
			"perks/jobs/sex_doll",
			Util.newArrayListOfValues(PresetColour.RACE_DOLL),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"[style.boldGood(遵循)]任何命令",
					"[style.boldExcellent(优先遵循)]拥有者的命令",
					"[style.boldBad(无视)]奴隶与其他玩偶"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是一个外形极其逼真的性爱玩偶，是在“洛维耶纳的奢侈品店”制作的。"
					+ "[npc.her]将服从任何命令，而主人的指示为优先项，但会无视来自奴隶或其他玩偶的命令。");
		}
	};
	
	public static AbstractPerk JOB_AMAZONIAN_QUEEN = new AbstractPerk(20,
			true,
			"亚马逊女王",
			PerkCategory.JOB,
			"perks/jobs/npc_amazonian_queen",
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_SILVER),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 10),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 25),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 25)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是亚马逊人的领导者，一生都在进行着严苛的体育锻炼和格斗训练。");
		}
	};
	
	public static AbstractPerk JOB_AMAZONIAN = new AbstractPerk(20,
			true,
			"女性力量",
			PerkCategory.JOB,
			"perks/jobs/npc_amazonian",
			PresetColour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 10),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 10)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是一名亚马逊人，经常参加体育活动和战斗训练。");
		}
	};

	public static AbstractPerk JOB_YOUKO = new AbstractPerk(20,
			true,
			"魅惑妖狐",
			PerkCategory.JOB,
			"perks/jobs/npc_youko",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 15),
					new Value<>(Attribute.DAMAGE_SPELLS, 25),
					new Value<>(Attribute.CRITICAL_DAMAGE, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]一只来自心林高地的妖狐。[npc.SheHasFull]将[npc.her]一生中的大部分时间都花在了钻研奥术上，因此[npc.her]法术尤为强大。");
		}
	};
	
	public static AbstractPerk JOB_PUGILIST = new AbstractPerk(20,
			true,
			"拳击手",
			PerkCategory.JOB,
			"perks/jobs/npc_pugilist",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, 50),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25),
					new Value<>(Attribute.HEALTH_MAXIMUM, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]是一名职业拳击手，在比赛中是一名可怕的敌手。");
		}
	};
	
	
	public static AbstractPerk JOB_SLAVE = new AbstractPerk(20,
			true,
			"仆役生活",
			PerkCategory.JOB,
			"perks/jobs/slave",
			PresetColour.BASE_CRIMSON,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 2),
					new Value<>(Attribute.RESISTANCE_LUST, 2)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]有着强健的身心，这帮助[npc.herHim]消化了[npc.sheIs]只是别人财产的事实。");
		}
	};

	public static AbstractPerk JOB_CAPTIVE = new AbstractPerk(20,
			true,
			"人质",
			PerkCategory.JOB,
			"perks/jobs/npc_captive",
			PresetColour.BASE_RED,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5),
					new Value<>(Attribute.HEALTH_MAXIMUM, -25)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]遭到了绑架，成为了受他人意愿控制的囚徒。");
		}
	};
	
	public static AbstractPerk JOB_PROSTITUTE = new AbstractPerk(20,
			true,
			"传统职业",
			PerkCategory.JOB,
			"perks/jobs/prostitute",
			PresetColour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 50),
					new Value<>(Attribute.DAMAGE_LUST, 25),
					new Value<>(Attribute.RESISTANCE_LUST, 2)),
			Util.newArrayListOfValues("[style.boldExcellent(双倍)]自身和奴隶的卖淫收入")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]为谋生向陌生人出卖身体，经验丰富。在经历了无数次性爱后，[npc.herHim]需要卖力投入才能真正兴奋起来。");
		}
	};
	
	public static AbstractPerk JOB_MUGGER = new AbstractPerk(20,
			true,
			"法外之徒",
			PerkCategory.JOB,
			"perks/jobs/mugger",
			PresetColour.BASE_CRIMSON,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10),
					new Value<>(Attribute.HEALTH_MAXIMUM, 15)),
			Util.newArrayListOfValues("[style.boldExcellent(三倍)]抢劫收入")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]以犯罪为生，不论贫富，全都偷抢。");
		}
	};
	
	public static AbstractPerk JOB_BOUNTY_HUNTER = new AbstractPerk(20,
			true,
			"无路可逃",
			PerkCategory.JOB,
			"perks/jobs/bounty_hunter",
			PresetColour.BASE_CRIMSON,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 25),
					new Value<>(Attribute.HEALTH_MAXIMUM, 15)),
			Util.newArrayListOfValues("[style.boldExcellent(-50%)]敌人逃脱概率")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]是追踪和抓捕逃犯的专家，敌人很难在战斗中从[npc.herHim]手中逃脱。");
		}
	};
	
	public static AbstractPerk JOB_CONSTRUCTION_WORKER = new AbstractPerk(20,
			true,
			"建造者",
			PerkCategory.JOB,
			"perks/jobs/npc_construction",
			Util.newArrayListOfValues(PresetColour.CLOTHING_YELLOW, PresetColour.CLOTHING_BLACK),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.HEALTH_MAXIMUM, 15)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]曾经从事过建筑行业，帮助建造、修复基建与财产。");
		}
	};
	
	public static AbstractPerk JOB_CONSTRUCTION_WORKER_ARCANE = new AbstractPerk(20,
			true,
			"关键操纵",
			PerkCategory.JOB,
			"perks/jobs/npc_construction",
			Util.newArrayListOfValues(PresetColour.CLOTHING_PURPLE, PresetColour.CLOTHING_BLACK),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MANA_MAXIMUM, 25)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.nameIsFull]能够驾驭奥术，以心灵感应的方式操纵物体，对建筑公司来说是宝贵的资产。");
		}
	};
	
	
	
	
	// Player Histories:

    public static AbstractPerk JOB_OJISAN = new AbstractPerk(20,
            true,
            "Ojisan",
            PerkCategory.JOB,
            "perks/jobs/ojisan",
            PresetColour.BASE_PINK,
            Util.newHashMapOfValues(
                    new Value<>(Attribute.HEALTH_MAXIMUM, -10),
                    new Value<>(Attribute.VIRILITY, 10),
                    new Value<>(Attribute.FERTILITY, 10),
                    new Value<>(Attribute.LUST, 10),
                    new Value<>(Attribute.DAMAGE_LUST, 5)),
            Util.newArrayListOfValues()) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "[npc.Name] are one of those disgusting middle aged people who enjoy praying on others, [npc.Name] have managed to increase their fertility and virility for obvious reasons.");
        }
    };

    public static AbstractPerk JOB_UNEMPLOYED = new AbstractPerk(20,
			true,
			"家里蹲",
			PerkCategory.JOB,
			"perks/jobs/unemployed",
			PresetColour.BASE_RED,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 2),
					new Value<>(Attribute.DAMAGE_UNARMED, 5),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues("[style.boldExcellent(增强)]“充分休息”加成")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.herPos]有着充足的空闲时间，从许多的方面提升着[npc.herself]。"
					+ "[npc.Name]也因熟知放松的最佳办法而受益，增加睡眠受益。");
		}
	};

    public static final AbstractPerk JOB_NO_JOB_YET = new AbstractPerk(20,
            true,
            "Typical Student",
            PerkCategory.JOB,
            "perks/jobs/unemployed",
            PresetColour.BASE_RED,
            Util.newHashMapOfValues(
                    new Value<>(Attribute.MAJOR_PHYSIQUE, 10)),
            Util.newArrayListOfValues("[style.boldExcellent(Boosts)] 'Well Rested' bonus")) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "Being a student means not having struggled with a crappy part time job yet."
                    + " As such, [npc.Name] still retains their youthful vigor. [npc.She] also wakes more rested in the mornings.");
        }
    };

    public static final AbstractPerk JOB_AVERAGE_JOE = new AbstractPerk(20,
            true,
            "Average Joe",
            PerkCategory.JOB,
            "perks/jobs/unemployed", // TODO Ask an artist to make some new icons
            PresetColour.BASE_GREY,
            Util.newHashMapOfValues(),
            Util.newArrayListOfValues("[style.italics(No bonuses...)]")) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "An average joe in every way, you have never made a significant effort to break from the norm or be great in any way.");
        }
    };

    public static AbstractPerk JOB_PROFESSIONAL_GAMER = new AbstractPerk(20,
            true,
            "Professional Gamer",
            PerkCategory.JOB,
            "perks/jobs/unemployed",
            PresetColour.BASE_BLUE,
            Util.newHashMapOfValues(
                    new Value<>(Attribute.HEALTH_MAXIMUM, -10)),
            Util.newArrayListOfValues("Deals 25% more [style.colourBad(damage)]",
                    "All [style.colourExperience(experience)] gains are [style.colourBad(doubled)]")) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "You are a professional gamer with a great deal of skill.");
        }
    };

    public static AbstractPerk JOB_OFFICE_WORKER = new AbstractPerk(20,
			true,
			"白领",
			PerkCategory.JOB,
			"perks/jobs/officeWorker",
			PresetColour.BASE_BROWN,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 50)),
			Util.newArrayListOfValues("奴隶收入[style.boldExcellent(+25%)]")) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner!=null && owner.isFeminine()) {
				return "白领";
			} else {
				return "白领";
			}
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "受益于[npc.herPos]丰富的办公室经验，[npc.name]很清楚要如何激励底层员工。"
					+ "紧张的工作环境使[npc.herPro]积压了大量的压力，增强了暴击伤害。");
		}
	};

    public static final AbstractPerk JOB_STUDENT_COUNCIL = new AbstractPerk(20,
            true,
            "Student Council",
            PerkCategory.JOB,
            "perks/jobs/officeWorker",
            PresetColour.BASE_BROWN,
            Util.newHashMapOfValues(
                    new Value<>(Attribute.CRITICAL_DAMAGE, 50)),
            Util.newArrayListOfValues("[style.boldExcellent(+25%)] all slave income")) {
        @Override
        public String getName(GameCharacter owner) {
            if(owner!=null && owner.isFeminine()) {
                return "Student Councilwoman";
            } else {
                return "Student Councilman";
            }
        }
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "From [npc.herPos] considerable student council experience, [npc.name] [npc.verb(know)] exactly how to motivate those working beneath [npc.herPro]."
                    + " The stressful work environment has caused [npc.herPro] to bottle up a lot of frustration, which manifests in increased critical power.");
        }
    };
	public static AbstractPerk JOB_STUDENT = new AbstractPerk(20,
			true,
			"学生优惠",
			PerkCategory.JOB,
			"perks/jobs/student",
			PresetColour.BASE_YELLOW,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10)),
			Util.newArrayListOfValues("[style.boldExcellent(获得25%)]商店折扣")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的学生折扣从未让[npc.herPro]失望过！"
					+ "除了在所有商店都能享受25%折扣外，[npc.she]还对[npc.herPos]快速学习新知识的能力充满信心。");
		}
	};
	
	public static AbstractPerk JOB_MUSICIAN = new AbstractPerk(20,
			true,
			"奥术曲谱",
			PerkCategory.JOB,
			"perks/jobs/musician",
			PresetColour.BASE_GREY,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 25)),
			Util.newArrayListOfValues("[style.boldExcellent(双倍)]奥术效应的影响时长")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]发现[npc.herPos]作为音乐家的能力为魅惑之术增添了不少优势。"
					+ "[npc.She]能感受到施法过程中的音乐节律，导致所有[npc.herPos]法术效果的持续时间是常人的两倍。");
		}
	};

    public static AbstractPerk JOB_JROTC = new AbstractPerk(20,
            true,
            "受控侵略",
            PerkCategory.JOB,
            "perks/jobs/soldier",
            PresetColour.BASE_GREEN,
            Util.newHashMapOfValues(
                    new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
                    new Value<>(Attribute.HEALTH_MAXIMUM, 25),
                    new Value<>(Attribute.RESISTANCE_PHYSICAL, 5),
                    new Value<>(Attribute.DAMAGE_PHYSICAL, 10)),
            Util.newArrayListOfValues("在战斗的第一回合，你的伤害[style.boldExcellent(翻倍)]")) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "[npc.NameHas]花了大量时间进行战斗训练，远比普通人更强壮、更健康。"
                    + "得益于训练，[npc.she]也能将[npc.herPos]的攻击性转化为攻击的威力。");
        }
    };

	public static AbstractPerk JOB_TEACHER = new AbstractPerk(20,
			true,
			"尽在掌握",
			PerkCategory.JOB,
			"perks/jobs/teacher",
			PresetColour.BASE_BLUE_LIGHT,
			Util.newHashMapOfValues(
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10)),
			Util.newArrayListOfValues("[style.boldExcellent(三倍)]奴隶的服从获得")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]明白该如何处理不守规矩的学生。"
					+ "[npc.HerPos]能够清楚地理解与解释难题，这使得施法成本得以降低。");
		}
	};
	
	public static AbstractPerk JOB_WRITER = new AbstractPerk(20,
			true,
			"冥想",
			PerkCategory.JOB,
			"perks/jobs/writer",
			PresetColour.BASE_PURPLE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_SPELLS, 25)),
			Util.newArrayListOfValues("[style.boldExcellent(+25%)]经验获得")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]随身携带一本记录了[npc.herPos]个人思考和境遇的日记，让[npc.herPro]反思并从[npc.herPos]经历中学习。"
					+ "[npc.HerPos]对阅读很感兴趣，这使[npc.herPro]能快速施放最有效的应对法术。");
		}
	};

	public static AbstractPerk JOB_CHEF = new AbstractPerk(20,
			true,
			"高雅品味",
			PerkCategory.JOB,
			"perks/jobs/chef",
			PresetColour.BASE_ORANGE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_POISON, 25)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(双倍)]药剂效果的持续时间",
					"[style.boldExcellent(双倍)]药效上限")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "由于花费大量时间品尝食物，[npc.Name]有着很强的抗毒能力，以及用基础食材做出美味佳肴的手艺。");
		}
	};

    public static AbstractPerk JOB_GSTUDENT = new AbstractPerk(20,
            true,
            "Student Discount",
            PerkCategory.JOB,
            "perks/jobs/gstudent",
            PresetColour.BASE_YELLOW,
            Util.newHashMapOfValues(
                    new Value<>(Attribute.MAJOR_ARCANE, 10)),
            Util.newArrayListOfValues("[style.boldExcellent(25%)] discount in all stores")) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "[npc.NamePos] student discount has never failed [npc.herPro] before!"
                    + " Along with a guaranteed 25% discount in all stores, [npc.she] can be confident in [npc.herPos] ability to quickly learn new things.");
        }
    };

    public static AbstractPerk JOB_MSTUDENT = new AbstractPerk(20,
            true,
            "Student Discount",
            PerkCategory.JOB,
            "perks/jobs/mstudent",
            PresetColour.BASE_YELLOW,
            Util.newHashMapOfValues(
                    new Value<>(Attribute.MAJOR_ARCANE, 10)),
            Util.newArrayListOfValues("[style.boldExcellent(25%)] discount in all stores")) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "[npc.NamePos] student discount has never failed [npc.herPro] before!"
                    + " Along with a guaranteed 25% discount in all stores, [npc.she] can be confident in [npc.herPos] ability to quickly learn new things.");
        }
    };

    public static AbstractPerk JOB_HSTUDENT = new AbstractPerk(20,
            true,
            "Student Discount",
            PerkCategory.JOB,
            "perks/jobs/hstudent",
            PresetColour.BASE_YELLOW,
            Util.newHashMapOfValues(
                    new Value<>(Attribute.MAJOR_ARCANE, 10)),
            Util.newArrayListOfValues("[style.boldExcellent(25%)] discount in all stores")) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "[npc.NamePos] student discount has never failed [npc.herPro] before!"
                    + " Along with a guaranteed 25% discount in all stores, [npc.she] can be confident in [npc.herPos] ability to quickly learn new things.");
        }
    };
    public static AbstractPerk JOB_BAND = new AbstractPerk(20,
            true,
            "Arcane Composition",
            PerkCategory.JOB,
            "perks/jobs/musician",
            PresetColour.BASE_GREY,
            Util.newHashMapOfValues(
                    new Value<>(Attribute.DAMAGE_LUST, 25)),
            Util.newArrayListOfValues("[style.boldExcellent(Double)] length of all spell effects")) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "[npc.Name] [npc.verb(find)] that [npc.herPos] abilities as a band member translate quite well into the art of seduction."
                    + " [npc.She] [npc.verb(feel)] the same sort of rhythm in casting spells as [npc.she] [npc.do] with music, resulting in all of [npc.herPos] spell effects lasting twice as long as usual.");
        }
    };
    public static AbstractPerk JOB_HOMESCHOOLED = new AbstractPerk(20,
            true,
            "Homeschooled",
            PerkCategory.JOB,
            "perks/jobs/student",
            PresetColour.BASE_YELLOW,
            Util.newHashMapOfValues(
                    new Value<>(Attribute.MAJOR_ARCANE, 10),
                    new Value<>(Attribute.MANA_MAXIMUM, 25)),
            Util.newArrayListOfValues()) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "[npc.NamePos] was homeschooled until very recently and has gained a hieghtened sense for the arcane in this new world.");
        }
    };

    public static AbstractPerk JOB_PREP_SCHOOL = new AbstractPerk(20,
            true,
            "Prep School Student",
            PerkCategory.JOB,
            "perks/jobs/student",
            PresetColour.BASE_BLUE,
            Util.newHashMapOfValues(
                    new Value<>(Attribute.MAJOR_PHYSIQUE, -10),
                    new Value<>(Attribute.MAJOR_ARCANE, 30)),
            Util.newArrayListOfValues("All [style.colourExperience(experience)] gains are [style.colourBad(doubled)]")) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "[npc.NamePos] was attending a college preparation school (read as: cram school) "
                    + "which has had an inverse effect on [npc.her] physique, but greatly boosts [npc.her] arcane abilities.");
        }
    };

    public static AbstractPerk JOB_SEGREGATED_SCHOOL = new AbstractPerk(20,
            true,
            "Segregated School Student",
            PerkCategory.JOB,
            "perks/jobs/student",
            PresetColour.SPELL_SCHOOL_ARCANE,
            Util.newHashMapOfValues(),
            Util.newArrayListOfValues("All [style.colourLust(lust)] gains are [style.colourBad(doubled)]",
                    "All [style.colourCorruption(corruption)] gains are [style.colourBad(doubled)]")) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "[npc.NamePos] was attending a segrated school and had little exposure to the opposite sex in [npc.her] youth.");
        }
    };

	public static AbstractPerk JOB_PLAYER_CONSTRUCTION_WORKER = new AbstractPerk(20,
			true,
			"工头",
			PerkCategory.JOB,
			"perks/jobs/construction",
			Util.newArrayListOfValues(PresetColour.CLOTHING_YELLOW, PresetColour.CLOTHING_BLACK),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.HEALTH_MAXIMUM, 10),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues("房间升级费用[style.boldExcellent(减半)]"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "由于[npc.her]拥有丰富的行业经验，[npc.Name]知道该如何效率地管理建筑项目。"
					+ "[npc.Her]投入进体力劳动的时间，同时赐予了[npc.herHim]一具健康的身体。");
		}
	};
	

	public static AbstractPerk JOB_SOLDIER = new AbstractPerk(20,
			true,
			"Controlled Aggression",
			PerkCategory.JOB,
			"perks/jobs/soldier",
			PresetColour.BASE_GREEN,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.HEALTH_MAXIMUM, 25),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10)),
			Util.newArrayListOfValues("Your damage is [style.boldExcellent(doubled)] in the first turn of combat")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHas] spent a considerable amount of time training to fight, and as a result, [npc.she] [npc.is] far stronger and healthier than a normal person."
					+ " Thanks to this training, [npc.she] [npc.is] also able to channel [npc.herPos] aggression into [npc.herPos] attacks.");
		}
	};

	public static AbstractPerk JOB_ATHLETE = new AbstractPerk(20,
			true,
			"10秒极限",
			PerkCategory.JOB,
			"perks/jobs/athlete",
			PresetColour.BASE_TEAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10)),
			Util.newArrayListOfValues("战斗中只要逃脱率不为零，都会提升至[style.boldExcellent(100%)] ")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]是世界一流的短跑运动员，有100%的保证逃脱任何可逃跑的战斗。");
		}
	};

    public static AbstractPerk JOB_PORN_STAR = new AbstractPerk(20,
            true,
            "Porn Star",
            PerkCategory.JOB,
            "perks/jobs/prostitute",
            PresetColour.BASE_PINK,
            Util.newHashMapOfValues(
                    new Value<>(Attribute.MANA_MAXIMUM, 20)),
            Util.newArrayListOfValues("Increases [style.colourLust(lust)] damage dealt by 25%]"/*, // TODO uncomment and add the missing functionality
					"Increases [style.colourElasticity(elasticity)] for all orifices by three ranks",
					"Decreases [style.colourPlasticity(plasticity)] for all orifices by three ranks"*/)) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "[npc.Name] has done considerable work in the adult film industry as an actor in pornographic films."/*
			+ " As a result, [npc.her] body is much more experienced with sexual activities and is not only able to accommodate more, but is also more resistant to stretching."*/);
        }
    };

    public static AbstractPerk JOB_CHILD_PORN_STAR = new AbstractPerk(20,
            true,
            "Child Porn Star",
            PerkCategory.JOB,
            "perks/jobs/prostitute",
            PresetColour.BASE_PINK,
            Util.newHashMapOfValues(
                    new Value<>(Attribute.MANA_MAXIMUM, 20)),
            Util.newArrayListOfValues("Increases [style.colourLust(lust)] damage dealt by 25%]"/*, // TODO uncomment and add the missing functionality
					"Increases [style.colourElasticity(elasticity)] for all orifices by three ranks",
					"Decreases [style.colourPlasticity(plasticity)] for all orifices by three ranks"*/)) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "[npc.Name] has done considerable work in the adult film industry as a child actor in pornographic films."/*
			+ " As a result, [npc.her] body is much more experienced with sexual activities and is not only able to accommodate more, but is also more resistant to stretching."*/);
        }
    };
	public static AbstractPerk JOB_ARISTOCRAT = new AbstractPerk(20,
			true,
			"贵族血统",
			PerkCategory.JOB,
			"perks/jobs/aristocrat",
			PresetColour.BASE_GOLD,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 10),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 25),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 25)),
			Util.newArrayListOfValues(
					"[style.colourCorruption(堕落)]将[style.colourBad(双倍)]增加",
					"每有1堕落，[style.colourExcellent(全抗性)][style.colourGood(+0.25)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]出身簪缨世族，得到了最优渥的精英教育。"
					+ "颐指气使是[npc.name]的做派。[npc.name]确信自己超群绝伦，如鹤在鸡群。凡夫俗子如何同[npc.herHim]相提并论！");
		}
	};

    public static AbstractPerk JOB_LITTLE_PRINCESS = new AbstractPerk(20,
            true,
            "little princess",
            PerkCategory.JOB,
            "perks/jobs/aristocrat",
            PresetColour.BASE_GOLD,
            Util.newHashMapOfValues(),
            Util.newArrayListOfValues(
                    "All [style.colourCorruption(corruption)] gains are [style.colourBad(doubled)]",
                    "All [style.colourExperience(experience)] gains are [style.colourBad(doubled)]"/*, // FAETODO - Figure out how to make character take more damage based on history
                    "Increased damage taken from all sources"*/)) {
        @Override
        public String getDescription(GameCharacter owner) {
            return UtilText.parse(owner, "[npc.NameIsFull] from an ancient aristocratic family, and [npc.has] been given the best education money can buy."
                    + " Insufferably arrogant, [npc.name] [npc.verb(know)] that [npc.sheIsFull] better than everyone else, and won't let any filthy peasant get the better of [npc.herHim]!");
        }
    };



    public static AbstractPerk JOB_MAID = new AbstractPerk(20,
			true,
			"管家",
			PerkCategory.JOB,
			"perks/jobs/maid",
			PresetColour.BASE_PINK_LIGHT,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 5),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(增强)]女仆套增益",
					"从女佣与管家处获得[style.boldExcellent(双倍)]奴隶收益")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]是辛勤女仆的完美典范，穿着整套女佣服时，获得的增益也将大大提高。"
					+ "[npc.She]同时知晓该如何培训管家与其他女仆，使其在工作的表现上出类拔萃。");
		}
	};

	public static AbstractPerk JOB_BUTLER = new AbstractPerk(20,
			true,
			"吉夫斯笔下的人物",
			PerkCategory.JOB,
			"perks/jobs/butler",
			PresetColour.BASE_BLUE_STEEL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.RESISTANCE_LUST, 25)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(提升)]管家套装的奖励",
					"[style.boldExcellent(双倍)]从事仆人/管家奴隶的受益")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]是辛勤管家的完美典范，穿着整套管家服时，获得的增益也将大大提高。"
					+ "[npc.She]还知晓该如何培训女仆与管家，使其在工作的表现上出类拔萃。");
		}
	};

	public static AbstractPerk JOB_TOURIST = new AbstractPerk(20,
			true,
			"爷是美国人！",
			PerkCategory.JOB,
			"perks/jobs/tourist",
			PresetColour.BASE_BLUE_DARK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 25)),
			Util.newArrayListOfValues(
					"所有错误的英语拼写都要通通[style.boldExcellent(纠正)]",
					"敌人被你的[style.colourFreedom(自由民主)]闪瞎了，[style.colourExcellent(-1)]<span style='color:"+Attribute.ACTION_POINTS.getColour().toWebHexString()+";'>"+Attribute.ACTION_POINTS.getName()+"</span>",
					"汉堡的效果[style.colourGood(翻倍)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.speech(打扰一下！我是美·国·人！你会说英语吗？！我的签证四天后到期，大使馆在哪里？！)]");
		}
	};
	
	// Physical:
	
	public static AbstractPerk PHYSICAL_BASE = new AbstractPerk(20,
			false,
			"天生体魄",
			PerkCategory.PHYSICAL,
			"perks/attStrength5",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]拥有与生俱来的强健体魄。");
		}
	};

	public static AbstractPerk CRITICAL_BOOST = new AbstractPerk(20,
			false,
			"关键一击",
			PerkCategory.PHYSICAL,
			"perks/critical_power",
			PresetColour.BASE_ORANGE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 5),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]能够真正充分发挥[npc.she]的关键力量。");
		}
	};

	public static AbstractPerk CRITICAL_BOOST_LUST = new AbstractPerk(20,
			false,
			"关键一击",
			PerkCategory.LUST,
			"perks/critical_power",
			PresetColour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 5),
					new Value<>(Attribute.DAMAGE_LUST, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]能够真正充分发挥[npc.she]的关键力量。");
		}
	};

	public static AbstractPerk CRITICAL_BOOST_ARCANE = new AbstractPerk(20,
			false,
			"关键一击",
			PerkCategory.ARCANE,
			"perks/critical_power",
			PresetColour.BASE_PURPLE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 5),
					new Value<>(Attribute.DAMAGE_SPELLS, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]能够真正充分发挥[npc.she]的关键力量。");
		}
	};
	
	public static AbstractPerk PHYSIQUE_BOOST = new AbstractPerk(20,
			false,
			"身体健康",
			PerkCategory.PHYSICAL,
			"perks/attStrength1",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]花了不少工夫来锤炼[npc.her]的身体。");
		}
	};
	
	public static AbstractPerk PHYSIQUE_BOOST_ALT = new AbstractPerk(20,
			false,
			"身体健康",
			PerkCategory.PHYSICAL,
			"perks/attStrength1",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]花了不少工夫来锤炼[npc.her]的身体。");
		}
	};

	public static AbstractPerk PHYSIQUE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"身体健康",
			PerkCategory.PHYSICAL,
			"perks/attStrength5",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "千锤百炼！[npc.nameHasFull]的身体素质大幅强化了！");
		}
	};
	
	public static AbstractPerk PHYSICAL_DAMAGE = new AbstractPerk(20,
			false,
			"打击者",
			PerkCategory.PHYSICAL,
			"perks/strike",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "通过不涉及奥术的训练，[npc.nameHasFull]成功提升了[npc.her]物理攻击的伤害。");
		}
	};

	public static AbstractPerk UNARMED_DAMAGE = new AbstractPerk(20,
			false,
			"拳拳到肉",
			PerkCategory.PHYSICAL,
			"perks/unarmed_damage",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_UNARMED, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]训练徒手格斗已久，不凭仗武器亦可制敌。");
		}
	};

	public static AbstractPerk MELEE_DAMAGE = new AbstractPerk(20,
			false,
			"近战武器专家",
			PerkCategory.PHYSICAL,
			"perks/melee_damage",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.nameIsFull]投入大量的时间来训练各种不同的近战武器，在近身作战中已然是一名相当可怕的敌手。");
		}
	};

	public static AbstractPerk RANGED_DAMAGE = new AbstractPerk(20,
			false,
			"神射手",
			PerkCategory.PHYSICAL,
			"perks/ranged_damage",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.nameIsFull]精通各种远程武器，在远距离交战中是能令人真切感到恐惧的敌手。");
		}
	};

	public static AbstractPerk BESERK = new AbstractPerk(20,
			true,
			"狂战士",
			PerkCategory.PHYSICAL,
			"perks/beserk",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 20),
					new Value<>(Attribute.CRITICAL_DAMAGE, 20),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -2),
					new Value<>(Attribute.DAMAGE_SPELLS, -15),
					new Value<>(Attribute.DAMAGE_LUST, -15)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]完全不顾自身安危，几乎无暇顾及那些不能全面展示武力的策略。");
		}
	};
	
	public static AbstractPerk PHYSICAL_DEFENCE = new AbstractPerk(20,
			false,
			"卫士",
			PerkCategory.PHYSICAL,
			"perks/shield",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]花了很多时间来提升[npc.her]抵抗物理攻击的能力。");
		}
	};

	public static AbstractPerk HYPERMOBILITY = new AbstractPerk(20,
			false,
			"超级柔韧",
			PerkCategory.PHYSICAL,
			"perks/hypermobility",
			PresetColour.BASE_WHITE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 15)),
			Util.newArrayListOfValues(
					"[style.colourExcellent(解锁)]“自我口交”和“自我舔阴”[style.colourSex(性行为)]。")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]异常柔韧，所以能从刁钻的角度奇袭敌人。"
					+ "[npc.sheIs]弯起腰来能把自己折叠，所以可以为自己口交。");
		}
	};
	
	public static AbstractPerk SPELL_DAMAGE = new AbstractPerk(20,
			false,
			"施法伤害",
			PerkCategory.ARCANE,
			"perks/arcane_power_1",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "通过下功夫练习施法，[npc.nameHasFull]发现了提高法术效果的新方法。");
		}
	};
	
	public static AbstractPerk SPELL_DAMAGE_MAJOR = new AbstractPerk(20,
			false,
			"施法熟练",
			PerkCategory.ARCANE,
			"perks/arcane_power_3",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.nameIsFull]花费大量时间专注于提高法术的威力，现在能够更有效地施放法术。");
		}
	};
	
	public static AbstractPerk SPELL_EFFICIENCY = new AbstractPerk(20,
			false,
			"施法效率",
			PerkCategory.ARCANE,
			"perks/spell_efficiency",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]提高了施法效率！");
		}
	};
	
	public static AbstractPerk AURA_BOOST = new AbstractPerk(20,
			false,
			"灵气储备",
			PerkCategory.ARCANE,
			"perks/resource_boost",
			PresetColour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.nameHasFull]花了不少时间冥想，成功增加了灵气储备。");
		}
	};
	
	public static AbstractPerk ENERGY_BOOST = new AbstractPerk(20,
			false,
			"能量储备",
			PerkCategory.PHYSICAL,
			"perks/resource_boost",
			PresetColour.ATTRIBUTE_HEALTH,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]增加了能量储备，使[npc.herHim]能够抵御曾经会使其战败的攻击。");
		}
	};

	public static AbstractPerk ENERGY_BOOST_DRAIN_DAMAGE = new AbstractPerk(20,
			true,
			"灵气护盾",
			PerkCategory.PHYSICAL,
			"perks/resource_boost_drain_aura",
			PresetColour.ATTRIBUTE_HEALTH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 20),
					new Value<>(Attribute.MANA_MAXIMUM, -25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "通过集中[npc.her]的奥术灵气制造一道护盾，[npc.nameIsFull]现在可以承受比以往更多的伤害。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_BOOST = new AbstractPerk(20,
			false,
			"元素打击者",
			PerkCategory.LUST,
			"perks/elemental_damage",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 25),
					new Value<>(Attribute.DAMAGE_ICE, 25),
					new Value<>(Attribute.DAMAGE_POISON, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]致力于学习该如何更有效地利用奥术元素。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_BOOST_ALT = new AbstractPerk(20,
			false,
			"元素打击者",
			PerkCategory.LUST,
			"perks/elemental_damage",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 25),
					new Value<>(Attribute.DAMAGE_ICE, 25),
					new Value<>(Attribute.DAMAGE_POISON, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]致力于学习该如何更有效地利用奥术元素。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_BOOST_ALT_2 = new AbstractPerk(20,
			false,
			"元素打击者",
			PerkCategory.LUST,
			"perks/elemental_damage",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 25),
					new Value<>(Attribute.DAMAGE_ICE, 25),
					new Value<>(Attribute.DAMAGE_POISON, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]致力于学习该如何更有效地利用奥术元素。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_DEFENCE_BOOST = new AbstractPerk(20,
			false,
			"元素防御者",
			PerkCategory.LUST,
			"perks/elemental_defence",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_FIRE, 10),
					new Value<>(Attribute.RESISTANCE_ICE, 10),
					new Value<>(Attribute.RESISTANCE_POISON, 10)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]花时间学习如何最好地在奥术元素中保护[npc.herself]。");
		}
	};
	
	
	public static AbstractPerk ARCANE_BASE = new AbstractPerk(20,
			false,
			"天然奥术力量",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 2)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "在这个世界里，无论多弱，每个人都会有奥术灵气。因此他们至少都能有一丝奥术力量可用。";
		}
	};
	
	public static AbstractPerk ARCANE_BOOST = new AbstractPerk(20,
			false,
			"奥术训练",
			PerkCategory.ARCANE,
			"perks/attIntelligence1",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]专注于提高驾驭奥术的能力。");
		}
	};

	public static AbstractPerk ARCANE_BOOST_ALT = new AbstractPerk(20,
			false,
			"奥术训练",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]专注于提升[npc.her]驾驭奥术的能力");
		}
	};

	public static AbstractPerk ARCANE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"奥术亲和力",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]解锁了几乎不为人所知的奥术秘闻。");
		}
	};
	
	
	public static AbstractPerk LEWD_KNOWLEDGE = new AbstractPerk(20,
			false,
			"放荡之识",
			PerkCategory.LUST,
			"perks/lewd_knowledge",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, 1),
					new Value<>(Attribute.DAMAGE_LUST, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]对御城区居民参加的淫荡活动了如指掌，并能利用这些知识增强[npc.her]的优势……");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST = new AbstractPerk(20,
			false,
			"摄人心魄",
			PerkCategory.LUST,
			"perks/attSeduction1",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]花大量时间习得了魅惑技术，[npc.herHim]与他人调情时可造成额外的伤害。");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST_ALT = new AbstractPerk(20,
			false,
			"摄人心魄",
			PerkCategory.LUST,
			"perks/attSeduction1",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]花大量时间习得了魅惑技术，[npc.herHim]与他人调情时可造成额外的伤害。");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST_ALT_2 = new AbstractPerk(20,
			false,
			"摄人心魄",
			PerkCategory.LUST,
			"perks/attSeduction1",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]花大量时间习得了魅惑技术，[npc.herHim]与他人调情时可造成额外的伤害。");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"摄人心魄",
			PerkCategory.LUST,
			"perks/attSeduction5",
			PresetColour.BASE_PINK_DEEP,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]是个行走的性爱炸弹。[npc.Her]的一举一动都包含着性暗示。人们只需看[npc.herHim]一眼，就会不由自主地被挑起情欲。");
		}
	};
	
	public static AbstractPerk SEDUCTION_DEFENCE_BOOST = new AbstractPerk(20,
			false,
			"抗性",
			PerkCategory.LUST,
			"perks/shield",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]使[npc.herself]的意志坚如铁石，以此来抗拒任何诱惑，[npc.she]在受到挑逗时更难被挑起情欲。");
		}
	};
	
	public static AbstractPerk VIRILITY_BOOST = new AbstractPerk(20,
			false,
			"生殖力",
			PerkCategory.LUST,
			"perks/virile",
			PresetColour.BASE_BLUE_LIGHT,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, 15)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的身体可以制造出非常强壮的精子，使得[npc.hers]的性对象会更容易怀孕。");
		}
	};
	
	public static AbstractPerk VIRILITY_MAJOR_BOOST = new AbstractPerk(20,
			false,
			"生殖力",
			PerkCategory.LUST,
			"perks/virile",
			PresetColour.GENERIC_EXCELLENT,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, 25)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的身体可以产生无比强壮的精子，大大增加了[npc.hers]的性对象怀孕的几率。");
		}
	};

	public static AbstractPerk FERTILITY_BOOST = new AbstractPerk(20,
			false,
			"生育力",
			PerkCategory.LUST,
			"perks/fertile",
			PresetColour.BASE_PINK_LIGHT,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 15)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的身体非常易孕，增加了性对象令[npc.herHim]怀孕的概率。");
		}
	};

	public static AbstractPerk FERTILITY_MAJOR_BOOST = new AbstractPerk(20,
			false,
			"生育力",
			PerkCategory.LUST,
			"perks/fertile",
			PresetColour.GENERIC_EXCELLENT,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 25)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的身体颇具生育力，增强了性对象让[npc.herHim]怀孕的机会。");
		}
	};
	
	
	public static AbstractPerk ARCANE_COMBATANT = new AbstractPerk(20,
			false,
			"奥术战士",
			PerkCategory.ARCANE,
			"perks/physical_brawler",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_SPELLS, 10),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]是利用奥术战斗的专家，获得法术伤害和效能的加成。");
		}
	};
	
	public static AbstractPerk SACRIFICIAL_SHIELDING = new AbstractPerk(20,
			true,
			"牺牲护盾",
			PerkCategory.ARCANE,
			"perks/sacrificial_shielding",
			PresetColour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, -10),
					new Value<>(Attribute.ENERGY_SHIELDING, 2)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.nameIsFull]通过牺牲部分体力，得以召唤出一个全抗性的护盾。");
		}
	};

	public static AbstractPerk ARCANE_VAMPYRISM = new AbstractPerk(20,
			true,
			"奥术吸血",
			PerkCategory.ARCANE,
			"perks/arcane_vampyrism",
			PresetColour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, -25)),
			Util.newArrayListOfValues(
					"任何参战者被击败时，[style.colourExcellent(吸收他们50%)]剩余的[style.colourMana("+Attribute.MANA_MAXIMUM.getName()+")]",
					 "")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]将训练出吸取本性的奥术灵气，减少了对自身储备的依赖，更多地依靠从附近战败人员(朋友或敌人)身上吸取灵气。");
		}
	};
	
	
	public static AbstractPerk FEROCIOUS_WARRIOR = new AbstractPerk(20,
			false,
			"悍将",
			PerkCategory.PHYSICAL,
			"perks/physical_brawler",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, 5),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 2)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]拥有丰富的战斗经验，将使任何蠢到敢于挑战[npc.herHim]的人落入危险的境地。");
		}
	};
	
	
	public static AbstractPerk OBSERVANT = new AbstractPerk(60,
			true,
			"敏锐",
			PerkCategory.PHYSICAL,
			"perks/misc_observant",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>性别看破</span>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner!=null && owner.isPlayer()) {
				return "你的洞察力非常敏锐，能够注意到周围环境最细微的变化。"
						+ "即使看不到对方的腹股沟，也能准确判别性别。";
			} else {
				return UtilText.parse(owner, "[npc.Name]非常敏锐，[npc.she]常常扫视着周围的环境，寻找危险的迹象。");
			}
		}
	};
	

	public static AbstractPerk HEAVY_SLEEPER = new AbstractPerk(60,
			true,
			"重度沉睡者",
			PerkCategory.PHYSICAL,
			"statusEffects/sleeping",
			PresetColour.SLEEP_HEAVY,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"温柔的[style.colourSex(性行为)]不会让其醒来",
					"[style.boldExcellent(10x)][style.colourHealth(生命)]和[style.colourMana(灵气)]再生速率")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}
		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner!=null) {
				return UtilText.parse(owner,
						"[npc.Name]处在深度睡眠中，即便大声呼喊乃至用力摇晃，[npc.herHim]都不会醒来。"
							+"[npc.SheIs]在睡眠时的反应极为迟钝，以至于一个动作温和的伴侣可以与[npc.herHim]进行性行为而不至于让[npc.herHim]醒来……");
			}
			return "";
		}
	};
	
	// Arcane:
	
	public static AbstractPerk ARCANE_CRITICALS = new AbstractPerk(60,
			false,
			"奥术精度",
			PerkCategory.ARCANE,
			"perks/physical_accurate",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 5)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_COMBAT.toWebHexString()+ ";'>法术暴击</span>添加<b style='color:"+PresetColour.ATTRIBUTE_LUST.toWebHexString()+";'>奥术弱点</b>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术在攻击目标弱点时特别有效。"
					+ "[npc.her]法术的任何暴击命中都会添加“奥术弱点”状态，持续一回合(所有护盾效果-10)。");
		}
	};
	
//	
//	TELEPATHY(60,
//			true,
//			"arcane telepathy",
//			PerkCategory.ARCANE,
//			"perks/misc_observant",
//			PresetColour.GENERIC_ARCANE,
//			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5)),
//			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Telepathic seduction</span>")) {
//		@Override
//		public String applyPerkGained(GameCharacter character) {
//			return UtilText.parsePlayerThought("");
//		}
//
//		@Override
//		public String applyPerkLost(GameCharacter character) {
//			return UtilText.parsePlayerThought("");
//		}
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer()) {
//				return "By concentrating your arcane power into the mind of others, you're able to deliver a .";
//			} else {
//				return UtilText.parse(owner, "[npc.Name] is very perceptive, and [npc.she] continuously scans [npc.her] surroundings for signs of danger.");
//			}
//		}
//	};
	
//	SPELL_POWER_1(20,
//			true,
//			"arcane power",
//			PerkCategory.ARCANE,
//			"perks/arcane_power_1",
//			PresetColour.ATTRIBUTE_ARCANE,
//			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 5)), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer())
//				return "You have focused on improving your ability to harness the arcane and cast spells.";
//			else
//				return UtilText.parse(owner, "[npc.Name] seems reasonably competent at casting spells.");
//		}
//	};
//	SPELL_POWER_2(20,
//			true,
//			"arcane conduit",
//			PerkCategory.ARCANE,
//			"perks/arcane_power_2",
//			PresetColour.ATTRIBUTE_ARCANE,
//			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 10)), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer())
//				return "You have focused your ability to harness the arcane to the point where you can greatly enhance the effects of any spell.";
//			else
//				return UtilText.parse(owner, "[npc.Name] is highly competent at harnessing the arcane and improving [npc.her] spells.");
//		}
//	};
//	SPELL_POWER_3(20,
//			true,
//			"arcane mastery",
//			PerkCategory.ARCANE,
//			"perks/arcane_power_3",
//			PresetColour.ATTRIBUTE_ARCANE,
//			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 15)), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer()) {
//				return "You are a master at harnessing the arcane. Even if you didn't have an aura as strong as a demon's, you'd still be one of the greatest arcane users in Dominion.";
//			} else
//				return UtilText.parse(owner, "[npc.Name] is a master of harnessing the arcane and improving [npc.her] spells.");
//		}
//	};

	public static AbstractPerk FIRE_ENHANCEMENT = new AbstractPerk(20,
			false,
			"煽火者",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			PresetColour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "你对奥术火焰有高度的亲和力，知道如何使用它来最大限度地造成伤害。";
			else
				return UtilText.parse(owner, "[npc.Name]对奥术火焰有高度的亲和力，知道如何使用它来最大限度地造成伤害。");
		}
	};

	public static AbstractPerk FIRE_ENHANCEMENT_2 = new AbstractPerk(20,
			false,
			"纵火者",
			PerkCategory.ARCANE,
			"perks/arcane_fire_1",
			PresetColour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 10),
					new Value<>(Attribute.RESISTANCE_FIRE, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "你是操纵奥术火焰的专家。你不仅能最大限度地提高伤害，还拥有对其影响的高度抵抗力。";
			else
				return UtilText.parse(owner, "[npc.Name]是操纵奥术火焰的专家。[npc.she]不仅能最大限度地提高伤害，还拥有对其影响的高度抵抗力。");
		}
	};

	public static AbstractPerk COLD_ENHANCEMENT = new AbstractPerk(20,
			false,
			"一日之寒",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			PresetColour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_ICE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "你对奥术寒冷有高度的亲和力，知道如何使用它来最大限度地造成伤害。";
			else
				return UtilText.parse(owner, "[npc.Name]对奥术寒冷有高度的亲和力，知道如何使用它来最大限度地造成伤害。");
		}
	};

	public static AbstractPerk COLD_ENHANCEMENT_2 = new AbstractPerk(20,
			false,
			"冰冻十尺",
			PerkCategory.ARCANE,
			"perks/arcane_ice_1",
			PresetColour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_ICE, 10),
					new Value<>(Attribute.RESISTANCE_ICE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "你是操纵奥术寒冷的专家。你不仅能最大限度地提高伤害，还拥有对其影响的高度抵抗力。";
			else
				return UtilText.parse(owner, "[npc.Name]是操纵奥术寒冷的专家。[npc.she]不仅能最大限度地提高伤害，还拥有对其影响的高度抵抗力。");
		}
	};

	public static AbstractPerk POISON_ENHANCEMENT = new AbstractPerk(20,
			false,
			"毒液",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			PresetColour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_POISON, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "你对奥术毒素有高度的亲和力，知道如何使用它来最大限度地造成伤害。";
			else
				return UtilText.parse(owner, "[npc.Name]对奥术毒素有高度的亲和力，知道如何使用它来最大限度地造成伤害。");
		}
	};

	public static AbstractPerk POISON_ENHANCEMENT_2 = new AbstractPerk(20,
			false,
			"恶毒",
			PerkCategory.ARCANE,
			"perks/arcane_poison_1",
			PresetColour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_POISON, 10),
					new Value<>(Attribute.RESISTANCE_POISON, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "你是操纵奥术毒素的专家。你不仅能最大限度地提高伤害，还拥有对其影响的高度抵抗力。";
			else
				return UtilText.parse(owner, "[npc.Name]是操纵奥术毒素的专家。[npc.she]不仅能最大限度地提高伤害，还拥有对其影响的高度抵抗力。");
		}
	};

	// Fitness:
	public static AbstractPerk RUNNER = new AbstractPerk(20,
			true,
			"跑者",
			PerkCategory.PHYSICAL,
			"perks/fitness_runner",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 3)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.ATTRIBUTE_PHYSIQUE.toWebHexString()+ ";'>提高逃脱概率</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "你天生善于跑步，拥有良好的耐力。";
			else
				return UtilText.parse(owner, "[npc.Name]天生善于跑步，拥有良好的耐力。");
		}
	};

	public static AbstractPerk RUNNER_2 = new AbstractPerk(20,
			true,
			"有氧运动冠军",
			PerkCategory.PHYSICAL,
			"perks/fitness_runner_2",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 5)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.ATTRIBUTE_PHYSIQUE.toWebHexString()+ ";'>提高逃脱概率</span>")) {
		@Override
		public String getName(GameCharacter character) {
			if (character!=null && character.isFeminine()) {
				return "有氧女王";
			} else {
				return "有氧帝王";
			}
		}

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]就是" + (owner.isFeminine() ? "女王" : "帝王") + "，有氧放光芒。能量储备用之不竭。");
		}
	};

	public static AbstractPerk COMBAT_REGENERATION = new AbstractPerk(20,
			true,
			"战斗续行",
			PerkCategory.PHYSICAL,
			"perks/regeneration",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, -25)),
			Util.newArrayListOfValues("每回合[style.boldExcellent(恢复5%)]最大[style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]。")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "以牺牲部分"+Attribute.MANA_MAXIMUM.getName()+"为代价，奥术的自然治疗属性将在肾上腺素的作用下放大。");
		}
	};
	
	public static AbstractPerk UNARMED_TRAINING = new AbstractPerk(20,
			false,
			"斗士",
			PerkCategory.PHYSICAL,
			"perks/natural_fighter",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5),
					new Value<>(Attribute.DAMAGE_UNARMED, 10)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]热衷于斗殴，是徒手格斗中的可怕敌手。");
		}
	};

	public static AbstractPerk FEMALE_ATTRACTION = new AbstractPerk(60,
			false,
			"御女高手",
			PerkCategory.LUST,
			"perks/fitness_female_attraction",
			PresetColour.FEMININE,
			null, Util.newArrayListOfValues(
					"对<span style='color:" + PresetColour.FEMININE.toWebHexString()+ ";'>女性敌人</span>" + "+10% <span style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>性欲伤害</span>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]非常善于调情，虽然[npc.her]的魅力男女通吃，但[npc.she]发现[npc.her]的挑逗对女性尤其起效。");
		}

	};

	public static AbstractPerk MALE_ATTRACTION = new AbstractPerk(60,
			false,
			"御男高手",
			PerkCategory.LUST,
			"perks/fitness_male_attraction",
			PresetColour.MASCULINE,
			null, Util.newArrayListOfValues(
					"对<span style='color:" + PresetColour.MASCULINE.toWebHexString()+ ";'>男性敌人</span>" + "+10% <span style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>性欲伤害</span>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]很会挑逗，虽然[npc.her]的魅力男女通吃，但[npc.she]发现[npc.her]的挑逗对男性尤其起效。");
		}
	};
	
	public static AbstractPerk CONVINCING_REQUESTS = new AbstractPerk(20,
			true,
			"摄人心魄",
			PerkCategory.LUST,
			"perks/convincing_requests",
			Util.newArrayListOfValues(PresetColour.GENERIC_SEX, PresetColour.BASE_GOLD),
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 1)),
			Util.newArrayListOfValues(
					"在正常的性爱场景中提出的要求总是会被答应。",
					"解锁普通性爱场景的所有姿势"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]掌握了情话的艺术，再铁石心肠的支配对象都能听得进[npc.she]的话。");
		}
	};
	
	public static AbstractPerk OBJECT_OF_DESIRE = new AbstractPerk(20,
			true,
			"欲望之源",
			PerkCategory.LUST,
			"perks/object_of_desire",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 1)),
			Util.newArrayListOfValues("任何对象在性交中需要[style.colourSex(+1高潮次数)]才能被满足")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]惊人地"+(owner.getFemininity()==Femininity.ANDROGYNOUS?"attractive":(owner.isFeminine()?"美丽":"英俊"))
					+"以至于[npc.her]的性对象在第一次高潮之后仍会情难自抑地继续做下去。");
		}
	};

	public static AbstractPerk ORGASMIC_LEVEL_DRAIN = new AbstractPerk(20,
			true,
			"高潮等级流失",
			PerkCategory.LUST,
			"perks/orgasmic_level_drain",
			Util.newArrayListOfValues(PresetColour.GENERIC_SEX, PresetColour.GENERIC_EXPERIENCE),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"[style.boldTerrible(-95%所有)][style.boldExperience(经验获得)]",
					"在所有的[style.boldSex(性爱场景)]：",
					"可以选择让高潮的伴侣[style.boldTerrible(等级流失1级)]",
					"你将获得流失等级[style.boldExcellent(5)]倍的[style.boldExperience(经验)]"),
			null, null, null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"通过对[npc.her]奥术灵气的一系列复杂操作，[npc.name]如今从新的经历中学习的速度变得非常缓慢，"
					+ "但与之相对，现在能从[npc.her]在场时高潮的性爱对象那里汲取经验。");
		}
	};
	
	public static AbstractPerk NYMPHOMANIAC = new AbstractPerk(20,
			true,
			"性瘾成狂",
			PerkCategory.LUST,
			"perks/fitness_nymphomaniac",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 5),
					new Value<>(Attribute.RESISTANCE_LUST, -2)),
			Util.newArrayListOfValues(
					"[style.colourExcellent(x2)]高潮的[style.colourArcane(精华获取)]",
					"[style.colourTerrible(2x速度)]获得[style.colourSex(欲求不满)]和[style.colourSex(贞操带)]状态效果")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]对性爱无可救药地全身心上瘾。");
		}
	};
	
	public static AbstractPerk AHEGAO = new AbstractPerk(20,
			true,
			"阿嘿颜",
			PerkCategory.LUST,
			"perks/ahegao",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 15),
					new Value<>(Attribute.RESISTANCE_LUST, -5)),
			Util.newArrayListOfValues("性高潮时会[style.colourSex(阿嘿颜)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]高潮特别强烈，每次高潮[npc.she]都控制不出地半翻白眼，舌头外伸，几乎失去意识。");
		}
	};

	public static AbstractPerk LUSTPYRE = new AbstractPerk(20,
			false,
			"葬身欲火",
			PerkCategory.LUST,
			"perks/lustful_leech",
			PresetColour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 2)),
			Util.newArrayListOfValues("每当你造成[style.boldLust("+Attribute.DAMAGE_LUST.getName()+")]时[style.boldExcellent(吸收2%)]目标的最大"+Attribute.MANA_MAXIMUM.getName())) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "奥术能量将对[npc.namePos]的淫荡行为产生反应，每当[npc.her]挑逗敌人时，会从目标身上吸收一小股"+Attribute.MANA_MAXIMUM.getName()+"。");
		}
	};

	public static AbstractPerk PURE_MIND = new AbstractPerk(20,
			false,
			"纯洁思想",
			PerkCategory.LUST,
			"perks/pure_mind",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 2)),
			Util.newArrayListOfValues("每当你受到[style.boldLust("+Attribute.DAMAGE_LUST.getName()+")]时[style.boldExcellent(回复2%)]的最大"+Attribute.MANA_MAXIMUM.getName())) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.her]坚定意志，对抗任何的情欲干扰，[npc.nameIsFull]得以进入冥想状态，"
					+ "对[npc.she]所受到的任何性欲伤害做出反应，回复[npc.her]的一些"+Attribute.MANA_MAXIMUM.getName()+"。");
		}
	};
	
	
	public static AbstractPerk CLOTHING_ENCHANTER = new AbstractPerk(20,
			false,
			"奥术编织者",
			PerkCategory.ARCANE,
			"perks/arcaneWeaver",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.ENCHANTMENT_LIMIT, 15)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>为衣物附魔的成本减半</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]天生擅长将奥术附魔编织到衣物中，所以[npc.herHim]在给衣物附魔时只需消耗通常奥术精华的一半。");
		}
	};
	
	public static AbstractPerk WEAPON_ENCHANTER = new AbstractPerk(20,
			false,
			"奥术锻造者",
			PerkCategory.PHYSICAL,
			"perks/arcaneSmith",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.ENCHANTMENT_LIMIT, 15)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>为武器附魔的成本减半</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]天生擅长将奥术灌注于武器内，所以[npc.herHim]在给武器附魔时只需消耗通常奥术精华的一半。");
		}
	};
	
	public static AbstractPerk ENCHANTMENT_STABILITY = new AbstractPerk(20,
			false,
			"稳固附魔",
			PerkCategory.PHYSICAL,
			"perks/enchantment_stability",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.ENCHANTMENT_LIMIT, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]花了很长时间协调身心，来处理更多的"+Attribute.ENCHANTMENT_LIMIT.getName()+"，藉此驾驭更多的附魔武器、服装和纹身。");
		}
	};

	public static AbstractPerk ENCHANTMENT_STABILITY_ALT = new AbstractPerk(20,
			false,
			"稳固附魔",
			PerkCategory.ARCANE,
			"perks/enchantment_stability",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.ENCHANTMENT_LIMIT, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]花了很长时间协调身心，来处理更多的"+Attribute.ENCHANTMENT_LIMIT.getName()+"，藉此驾驭更多的附魔武器、服装和纹身。");
		}
	};
	
	public static AbstractPerk BARREN = new AbstractPerk(20,
			true,
			"不孕不育",
			PerkCategory.LUST,
			"perks/barren",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, -200)),
			Util.newArrayListOfValues("当"+Attribute.FERTILITY.getName()+"数值为0或更低时，[style.colourTerrible(不可能)]怀孕")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]生育力低下，因此怀孕概率很低。");
		}
	};
	
	public static AbstractPerk FIRING_BLANKS = new AbstractPerk(20,
			true,
			"精无力",
			PerkCategory.LUST,
			"perks/firing_blanks",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, -200)),
			Util.newArrayListOfValues("当"+Attribute.VIRILITY.getName()+"数值为0或更低时，[style.colourTerrible(不可能)]令人怀孕")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的精子不可思议的孱弱，使得[npc.sheIs]极不可能使任何人怀孕。");
		}
	};
	

	
	public static AbstractPerk FETISH_BROODMOTHER = new AbstractPerk(20,
			true,
			"孕母",
			PerkCategory.LUST,
			"fetishes/fetish_broodmother",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 20)),
			Util.newArrayListOfValues("2x<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>单次受孕数量上限</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "你的身体只为一件事而生，那就是尽可能的孕育后代。"
							+ "不论是由你奥术灵气的影响，还是仅仅因为你身体自然的生育能力，你似乎总是能同时生下大量的后代。";
			} else {
				return UtilText.parse(owner, "[npc.NamePos]身体的构造只有一个目的，那就是尽可能的孕育后代。"
						+ "[npc.she]似乎总是同时生下大量的后代。");
			}
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	};
	
	public static AbstractPerk FETISH_SEEDER = new AbstractPerk(20,
			true,
			"种马",
			PerkCategory.LUST,
			"fetishes/fetish_seeder",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, 20)),
			Util.newArrayListOfValues("2x<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>单次授孕数量上限</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "你的种子具有强大的功效，能让受孕者生下大量的后代。";
			} else {
				return UtilText.parse(owner, "[npc.namePos]的种子具有一种强效作用，使任何受其授孕的人都能生下大量的后代。");
			}
		}

		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	};
	
	public static AbstractPerk CHUUNI = new AbstractPerk(20,
			true,
			"中二",
			PerkCategory.ARCANE,
			"perks/misc_chuuni",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_SPELLS, 20),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 20)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>令人脚趾抠地的施法咒语</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "原指初中二年级青少年的某些病态自我意识。“中二病”患者相信并表现得好像自己拥有特殊的力量。"
					+ "虽然中二行径只是那些少年少女们的妄想，但现在奥术能让幻想变为现实……");
		}
	};

	public static AbstractPerk SPECIAL_PLAYER = new AbstractPerk(20,
			false,
			"不寻常的灵气",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 18)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "汝之存在，已超越了凡人的界限。奥能在汝体内中澎湃激荡，是凡人无法理解的神秘与奥妙！";
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_CECILIA = new AbstractPerk(20,
			false,
			"The Apex",
			PerkCategory.PHYSICAL,
			"perks/beserk",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Util.Value<>(Attribute.MAJOR_PHYSIQUE, 25),
					new Util.Value<>(Attribute.RESISTANCE_FIRE, 50),
					new Util.Value<>(Attribute.RESISTANCE_ICE, 50),
					new Util.Value<>(Attribute.RESISTANCE_POISON, 100),
					new Util.Value<>(Attribute.HEALTH_MAXIMUM, 250)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Cecilia is what one would consider to be at the very apex of her already considerably powerful species. Manticores have no official ruler, but her sheer might places her at the hypothetical and very literal pecking order.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_MERAXIS = new AbstractPerk(20,
			false,
			"暗夜塞壬",
			PerkCategory.ARCANE,
			"perks/dark_siren",
			PresetColour.ATTRIBUTE_LUST,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.HEALTH_MAXIMUM, 100),
					new Value<>(Attribute.MANA_MAXIMUM, 100),
					new Value<>(Attribute.ENCHANTMENT_LIMIT, 100)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "梅拉克西丝正是暗夜塞壬本尊！她是莉琳长老莉西丝承认的女儿，拥有高超的奥术技巧。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_ARCANE_TATTOOIST = new AbstractPerk(20,
			false,
			"奥术纹身师",
			PerkCategory.ARCANE,
			"perks/tattoo",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 15),
					new Value<>(Attribute.ENCHANTMENT_LIMIT, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]接受了广泛的训练，为学习如何在纹身上注入奥术魔法作铺垫。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_CLOTHING_MASCULINITY_INDIFFERENCE = new AbstractPerk(20,
			false,
			"男性化衣物漠视",
			PerkCategory.PHYSICAL,
			"perks/clothingIndifferenceMasculinity",
			Util.newArrayListOfValues(PresetColour.MASCULINE, PresetColour.CLOTHING_GREY),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>不受“服装过于男性化”身份效应的影响</span>"),
			null, null, null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]无论身体多么女性化，都不认为穿着男性化服装有不适感。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_CLOTHING_FEMININITY_INDIFFERENCE = new AbstractPerk(20,
			false,
			"女性化衣物漠视",
			PerkCategory.PHYSICAL,
			"perks/clothingIndifferenceFemininity",
			Util.newArrayListOfValues(PresetColour.FEMININE, PresetColour.CLOTHING_GREY),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>不受“服装过于女性化”身份效应的影响</span>"),
			null, null, null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]无论身体多么男性化，都不认为穿着女性化服装有不适感。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	// HIDDEN PERKS:

	public static AbstractPerk SPECIAL_DIRTY_MINDED = new AbstractPerk(20,
			false,
			"下流胚子",
			PerkCategory.ARCANE,
			"statusEffects/attCorruption5",
			PresetColour.ATTRIBUTE_CORRUPTION,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]的思想非常下流，常常沉浸于性爱的思绪当中。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_SLUT = new AbstractPerk(20,
			false,
			"骚货",
			PerkCategory.ARCANE,
			"perks/attSeduction3",
			PresetColour.ATTRIBUTE_LUST,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 15),
					new Value<>(Attribute.MAJOR_CORRUPTION, 40)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]有数不清的性对象，与他们进行了各式下流行为。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_MEGA_SLUT = new AbstractPerk(20,
			false,
			"恣意放荡",
			PerkCategory.ARCANE,
			"perks/attSeduction3",
			PresetColour.ATTRIBUTE_LUST,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 25),
					new Value<>(Attribute.MAJOR_CORRUPTION, 75)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "和完全陌生的人在一起却什么也没发生时，[npc.nameIsFull]几乎总是幻想着性爱，而且满脑子都是他们做爱时该用什么样的淫荡姿势，诸如此类。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_ARCANE_TRAINING = new AbstractPerk(20,
			false,
			"奥术训练",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.DAMAGE_SPELLS, 20)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]为学习如何驾驭奥术，接受了广泛的训练。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_ARCANE_LIGHTNING = new AbstractPerk(20,
			false,
			"天赐奥术师",
			PerkCategory.ARCANE,
			"perks/special_arcane_lightning",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.MANA_MAXIMUM, 100),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 25),
					new Value<>(Attribute.DAMAGE_SPELLS, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "由于[npc.nameHasFull]经历的一件特殊事件，[npc.her]不仅获得了惊人的天生奥术之力，还对如何更好地利用奥术拥有着本性上的了解。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_ARCANE_ALLERGY = new AbstractPerk(20,
			false,
			"奥术过敏",
			PerkCategory.ARCANE,
			"perks/arcane_allergy",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, -50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name]对奥术有罕见的过敏反应，因此几乎完全无法利用奥术的力量。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_HEALTH_FANATIC = new AbstractPerk(20,
			false,
			"健身狂人",
			PerkCategory.PHYSICAL,
			"perks/attStrength3",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]痴迷于个人的健康状态，每天都要花几个小时锻炼身体和规划饮食。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_MARTIAL_BACKGROUND = new AbstractPerk(20,
			false,
			"武术底子",
			PerkCategory.PHYSICAL,
			"perks/attStrength3",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]花了很多时间进行战斗训练，因此比一般人强壮得多。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_SHORT_SIGHTED = new AbstractPerk(60,
			false,
			"视力障碍",
			PerkCategory.PHYSICAL,
			"statusEffects/short_sighted",
			Util.newArrayListOfValues(
					PresetColour.BASE_BLACK,
					PresetColour.GENERIC_BAD,
					PresetColour.GENERIC_BAD),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"当[style.colourMinorBad(未佩戴矫正眼镜时)]，受到[style.colourTerrible(视力模糊)]的影响"),
			null, null, null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}
		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner.isPerfectVision()) {
				return UtilText.parse(owner,
						"[npc.Name]患有视力障碍，但多亏了[npc.her]的[npc.eyeRace][npc.eyes]，[npc.she]无需佩戴矫正眼镜也能看得清楚。");
			}
			return UtilText.parse(owner,
					"[npc.Name]患有视力障碍，不戴矫正眼镜时很难看清楚。"
					+ "当[npc.she]佩戴着矫正眼镜时，[npc.her]的视力就和正常人没什么区别了。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_MELEE_EXPERT = new AbstractPerk(20,
			false,
			"近战专家",
			PerkCategory.PHYSICAL,
			"perks/melee_damage",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]在使用近战武器方面拥有相当丰富的经验，这使得[npc.herHim]在使用特定武器时将会成为一名可怕的敌手。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_RANGED_EXPERT = new AbstractPerk(20,
			false,
			"远程专家",
			PerkCategory.PHYSICAL,
			"perks/ranged_damage",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]在使用远程武器方面拥有丰富的经验，这使得[npc.her]在使用特定武器时将会成为一名可怕的敌手。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_CHILD_OF_THE_CRAG = new AbstractPerk(20,
			true,
			"峭壁之子",
			PerkCategory.PHYSICAL_WATER,
			"perks/cragchild",
			PresetColour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_ICE, 30),
					new Value<>(Attribute.RESISTANCE_ICE, 50),
					new Value<>(Attribute.RESISTANCE_FIRE, -15)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.namePos]的血管里流淌着辛姆瑞斯的血液，[npc.she]早已适应了高山生活的寒意。不过，由于跟[npc.her]的沙漠亲戚血缘过于遥远，[npc.her]对火的天生抵抗力有所下降。");
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_ENFORCER_FIREARMS_TRAINING = new AbstractPerk(20,
			true,
			"枪械掌握",
			PerkCategory.PHYSICAL,
			"perks/enforcer_firearms",
			Util.newArrayListOfValues(
					PresetColour.DAMAGE_TYPE_FIRE,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_GUNMETAL),
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 75)),
			Util.newArrayListOfValues("[style.colourExcellent(双倍)]枪械伤害"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameHasFull]不仅完成了SWORD执法者都须参加的艰苦的“枪械训程”，还获得了大师的成绩。"
					+ "这毫无疑问地留下了些挥之不去的阴暗记忆，但结果是，[npc.sheIs]成为了这个领域中的标杆人物之一。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	//**** Special perks which can be gained from in-game events: ****//

	public static AbstractPerk PIX_TRAINING = new AbstractPerk(20,
			false,
			"皮克斯的训练",
			PerkCategory.PHYSICAL,
			"perks/pix_trained",
			Util.newArrayListOfValues(
				PresetColour.ATTRIBUTE_PHYSIQUE),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 2),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 1),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 1)),
			null,
			null,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "在与御城区最兴奋、最积极的体能训练师进行多次高强度训练后，你明显感觉到自己变得更强壮了。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk MARTIAL_ARTIST = new AbstractPerk(20,
			false,
			"武术家",
			PerkCategory.PHYSICAL,
			"perks/unarmed_training",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.CRITICAL_DAMAGE, 25)),
			Util.newArrayListOfValues("[style.colourUnarmed(徒手基础伤害)][style.colourExcellent(翻倍)]"),
			null,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull]接受过正规的武术训练，使[npc.herHim]即使手无寸铁也极度危险。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk IMP_SLAYER = new AbstractPerk(20,
			false,
			"毁灭战士",
			PerkCategory.ARCANE,
			"perks/imp_slayer",
			PresetColour.RACE_IMP,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_IMP, 100)),
			null) {

		@Override
		public String getName(GameCharacter owner) {
			if(owner!=null && owner.isFeminine()) {
				return "毁灭战士";
			} else {
				return super.getName(owner);
			}
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "<i>又是老一套。要有人以为能跟我胡闹，那我就得证明他是错的！</i>");
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk HERO_OF_THEMISCYRA = new AbstractPerk(20,
			false,
			"特弥斯库拉的英雄",
			PerkCategory.ARCANE,
			"perks/hero_of_themiscyra",
			PresetColour.BASE_GOLD,
			Util.newHashMapOfValues(
					new Value<>(Attribute.getRacialDamageAttribute(Race.HORSE_MORPH), 25),
					new Value<>(Attribute.getRacialDamageAttribute(Race.DEMON), 25)),
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner!=null && owner.isFeminine()) {
				return "特弥斯库拉的英雄";
			} else {
				return super.getName(owner);
			}
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return "<i>天空、喷泉、附近的每个区域"
					+ "<br/>似乎混成了一片交互的呐喊"
					+ "<br/>我从来不曾听见过那样谐美的喧声，那样悦耳的雷鸣。</i>";
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk AMAZONIAN_TRAINING = new AbstractPerk(20,
			false,
			"亚马逊特训",
			PerkCategory.ARCANE,
			"perks/amazonian_training",
			Util.newArrayListOfValues(
				PresetColour.DAMAGE_TYPE_PHYSICAL,
				PresetColour.CLOTHING_DESATURATED_BROWN,
				PresetColour.CLOTHING_KHAKI),
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, 1),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 1),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 1)),
			null,
			null,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "在与奥罗卡利斯进行了几次格斗训练后，你觉得自己在格斗方面又学到了一两样新东西。");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	
	//**** Elder lilin perks: ****//
	
	public static AbstractPerk POWER_OF_LIRECEA_1 = new AbstractPerk(20,
			false,
			"莉瑞恰的力量",
			PerkCategory.ARCANE,
			"perks/lilin1",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"如果是[style.boldDemon(恶魔)]则[style.boldExcellent(解锁)][style.boldLightBlue(鱼转换)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"莉瑞恰力量的精华已注入你的奥术灵气。"
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?"她的力量还令你能将恶魔之躯转换成任何水生物种的身体！"
							:"如果你是一名恶魔，这种力量能让你把自己的身体部位转换成任何水生物种的身体部位！"));
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LIRECEA_1_DEMON = new AbstractPerk(20,
			false,
			"莉瑞恰真实的力量",
			PerkCategory.ARCANE,
			"perks/lilin1",
			PresetColour.BASE_BLUE_LIGHT,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(解锁)][style.boldLightBlue(鱼转化)]",
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"莉瑞恰力量的精华已注入你的奥术灵气。"
					+ "她的力量被你的恶魔形态增强了，现在你可以将你的恶魔之躯转化为任何水生物种的身体！");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LOVIENNE_2 = new AbstractPerk(21,
			false,
			"洛维耶纳的力量",
			PerkCategory.ARCANE,
			"perks/lilin2",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"如果是一名[style.boldDemon(恶魔)]则[style.boldExcellent(解锁)][style.boldFeral(兽态转化)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"洛维耶纳力量的精华已注入你的奥术灵气。"
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?"她的力量还能够使你的恶魔之躯转化为野兽之躯！"
							:"如果你是一个恶魔，这力量还可以让你把身体的任意部分转化成任何野兽的身体部位！"));
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LOVIENNE_2_DEMON = new AbstractPerk(21,
			false,
			"洛维耶纳的真正力量",
			PerkCategory.ARCANE,
			"perks/lilin2",
			PresetColour.RACE_BESTIAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(解锁)][style.boldFeral(兽态转化)]",
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"洛维耶纳力量的精华已注入你的奥术灵气。"
					+ "你的恶魔形态增强了她的力量，这也使你能够将恶魔之躯转化为野兽之躯！");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LASIELLE_3 = new AbstractPerk(22,
			false,
			"拉谢尔的力量",
			PerkCategory.ARCANE,
			"perks/lilin3",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"如果是[style.boldDemon(恶魔)]则[style.boldExcellent(解锁)]将他人转化为[style.boldDemon(恶魔)]的能力", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"拉谢尔力量的精华已注入你的奥术灵气。"
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" 她的力量让你能将其他人变成恶魔！"
							:" 如果你是恶魔，那这种能力就让你能将其他人也变成恶魔！"));
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LASIELLE_3_DEMON = new AbstractPerk(22,
			false,
			"拉谢尔真正的力量",
			PerkCategory.ARCANE,
			"perks/lilin3",
			PresetColour.RACE_DEMON,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(解锁)]将他人转化成[style.boldDemon(恶魔)]的能力",
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"拉谢尔力量的精华已注入你的奥术灵气。"
					+ "你的恶魔形态增强了她的力量，这也使你能够将他人转化为恶魔！");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LYSSIETH_4 = new AbstractPerk(23,
			false,
			"莉西丝的力量",
			PerkCategory.ARCANE,
			"perks/lilin4",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 25)),
			Util.newArrayListOfValues(
					"如果是[style.boldDemon(恶魔)]则[style.boldExcellent(解锁)][style.boldHuman(人类转化)]",
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"莉西丝力量的精华已注入你的奥术灵气。"
					+ "如果你是一个恶魔，这力量还可以让你把身体的任意部分转化成普通人类的身体部位！");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LYSSIETH_4_DEMON = new AbstractPerk(23,
			false,
			"莉西丝的真正力量",
			PerkCategory.ARCANE,
			"perks/lilin4",
			PresetColour.RACE_HUMAN,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HUMAN), 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(解锁)][style.boldHuman(人类转化)]",
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"莉索菲亚力量的精华已注入你的奥术灵气。"
					+ "她的力量被你的恶魔形态增强了，现在你还可以把恶魔身躯的任意部分转化成普通人类的身体部位！");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LUNETTE_5 = new AbstractPerk(24,
			false,
			"露内特的力量",
			PerkCategory.ARCANE,
			"perks/lilin5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"如果是[style.boldDemon(恶魔)]则[style.boldExcellent(解锁)][style.boldHorse(哺乳动物转化)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"露内特力量的精华已注入你的奥术灵气。"
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?"她的力量还能够使你的恶魔之躯转化为普通人之躯！"
							:"如果你是一个恶魔，这力量能让你将身体的任意部分转化成普通人类的身体部位！"));
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LUNETTE_5_DEMON = new AbstractPerk(24,
			false,
			"露内特真正的力量",
			PerkCategory.ARCANE,
			"perks/lilin5",
			PresetColour.RACE_HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(解锁)][style.boldHorse(哺乳动物转化)]",
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"露内特力量的精华已注入你的奥术灵气。"
					+ "她的力量被你的恶魔形态增强了，现在你还可以把恶魔身躯的任意部分转化成哺乳动物的身体部位！");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LYXIAS_6 = new AbstractPerk(25,
			false,
			"莉西亚斯之力",
			PerkCategory.ARCANE,
			"perks/lilin6",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"如果是[style.boldDemon(恶魔)]则[style.boldExcellent(解锁)][style.boldHarpy(鸟类转化)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"莉西亚斯力量的精华已注入你的奥术灵气。"
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?"她的力量还令你能将恶魔之躯转换成任何鸟类的身体！"
							:"如果你是个恶魔，这种力量能让你把自己的身体部位转换成任何鸟类的身体部位！"));
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LYXIAS_6_DEMON = new AbstractPerk(25,
			false,
			"莉西亚斯真正的力量",
			PerkCategory.ARCANE,
			"perks/lilin6",
			PresetColour.RACE_HARPY,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(解锁)][style.boldHarpy(鸟类转化)]",
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"莉西亚斯力量的精华已注入你的奥术灵气。"
					+ "她的力量被你的恶魔形态增强了，现在你还可以把恶魔身躯的任意部分转化成鸟类的身体部位！");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LISOPHIA_7 = new AbstractPerk(26,
			false,
			"莉索菲亚之力",
			PerkCategory.ARCANE,
			"perks/lilin7",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"如果是[style.boldDemon(恶魔)]则[style.boldExcellent(解锁)][style.boldGreenLight(爬虫类转化和两栖类转化)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"莉西丝力量的精华已注入你的奥术灵气。"
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?"她的力量还令你能将恶魔之躯转换成任何爬虫类和两栖类的身体！"
							:"如果你是个恶魔，这种力量能让你把自己的身体部位转换成任何爬虫类以及两栖类的身体部位！"));
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LISOPHIA_7_DEMON = new AbstractPerk(26,
			false,
			"莉索菲亚真正的力量",
			PerkCategory.ARCANE,
			"perks/lilin7",
			PresetColour.BASE_GREEN_LIGHT,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(解锁)][style.boldGreenLight(爬虫类及两栖类转化)]",
					"[style.boldExcellent(免疫)][style.boldArcane(莉莉丝之律令)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"莉索菲亚力量的精华已注入你的奥术灵气。"
					+ "她的力量被你的恶魔形态增强了，现在你可以把恶魔身躯的任意部分转化成爬虫类和两栖类动物的身体部位！");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SINGLE_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"单尾妖狐",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail1",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(解锁)][style.boldFox(妖狐转化)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]是一只妖狐，一种能够操纵奥术的狐化形，只在心林高地出没。[npc.Her]为等级更高的妖狐服务。[npc.Her]得到了一条奥术尾巴作为回报。");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk TWO_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"双尾妖狐",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail2",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(解锁)][style.boldFox(妖狐转化)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]是一只妖狐，一种能够操纵奥术的狐化形，只在心林高地出没。[npc.Her]为等级更高的妖狐服务。[npc.Her]得到了两条奥术尾巴作为回报。");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk THREE_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"三尾妖狐",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail3",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(解锁)][style.boldFox(妖狐转化)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]是一只妖狐，一种能够操纵奥术的狐化形，只在心林高地出没。[npc.Her]为等级更高的妖狐服务。[npc.Her]得到了三条奥术尾巴作为回报。");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk FOUR_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"四尾妖狐",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail4",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(解锁)][style.boldFox(妖狐转化)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]是一只妖狐，一种能够操纵奥术的狐化形，只在心林高地出没。[npc.Her]为等级更高的妖狐服务。[npc.Her]得到了四条奥术尾巴作为回报。");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk FIVE_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"五尾妖狐",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail5",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(解锁)][style.boldFox(妖狐转化)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]是一只妖狐，一种能够操纵奥术的狐化形，只在心林高地出没。[npc.Her]为等级更高的妖狐服务。[npc.Her]得到了五条奥术尾巴作为回报。");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk SIX_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"六尾妖狐",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail6",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(解锁)][style.boldFox(妖狐转化)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]是一只妖狐，一种能够操纵奥术的狐化形，只在心林高地出没。[npc.Her]为等级更高的妖狐服务。[npc.Her]得到了六条奥术尾巴作为回报。");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk SEVEN_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"七尾妖狐",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail7",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(解锁)][style.boldFox(妖狐转化)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]是一只妖狐，一种能够操纵奥术的狐化形，只在心林高地出没。[npc.Her]为等级更高的妖狐服务。[npc.Her]得到了七条奥术尾巴作为回报。");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk EIGHT_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"八尾妖狐",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail8",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(解锁)][style.boldFox(妖狐转化)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]是一只妖狐，一种能够操纵奥术的狐化形，只在心林高地出没。[npc.Her]为等级更高的妖狐服务。[npc.Her]得到了八条奥术尾巴作为回报。");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk NINE_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"九尾妖狐",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail9",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(解锁)][style.boldFox(妖狐转化)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull]是一只妖狐，一种能够操纵奥术的狐化形，只在心林高地出没。[npc.Her]为等级更高的妖狐服务。[npc.Her]得到了九条奥术尾巴作为回报。");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	// SPECIFIC TO ELEMENTAL PERK TREE:
	
//	public static AbstractPerk ELEMENTAL_BOUND_EARTH = new AbstractPerk(20,
//			true,
//			"Bound to Earth",
//			PerkCategory.JOB,
//			"combat/spell/elemental_earth",
//			PresetColour.SPELL_SCHOOL_EARTH,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 50),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 50),
//					new Value<>(Attribute.RESISTANCE_PHYSICAL, 50)),
//			null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return UtilText.parse(owner, "By being bound to the school of Earth, [npc.name] has gained a strong, tough body that is extremely resilient to physical damage."
//					+ " As well as this, [npc.sheIs] now capable of inflicting great damage by using physical attacks.");
//		}
//	};
//
//	public static AbstractPerk ELEMENTAL_BOUND_FIRE = new AbstractPerk(20,
//			true,
//			"Bound to Fire",
//			PerkCategory.JOB,
//			"combat/spell/elemental_fire",
//			PresetColour.SPELL_SCHOOL_FIRE,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
//					new Value<>(Attribute.DAMAGE_FIRE, 50),
//					new Value<>(Attribute.RESISTANCE_FIRE, 50)),
//			null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return UtilText.parse(owner, "By being bound to the school of Fire, [npc.name] has gained an ethereal body that is extremely resilient to fire damage."
//					+ " As well as this, [npc.sheIs] now capable of inflicting great damage by using fire-based attacks.");
//		}
//	};
//
//	public static AbstractPerk ELEMENTAL_BOUND_WATER = new AbstractPerk(20,
//			true,
//			"Bound to Water",
//			PerkCategory.JOB,
//			"combat/spell/elemental_water",
//			PresetColour.SPELL_SCHOOL_WATER,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 20),
//					new Value<>(Attribute.DAMAGE_ICE, 50),
//					new Value<>(Attribute.RESISTANCE_ICE, 50)),
//			null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return UtilText.parse(owner, "By being bound to the school of Water, [npc.name] has gained "+(owner.getBodyMaterial()==BodyMaterial.WATER?"a liquid-like ":"an ice-like ")+"body that is extremely resilient to ice damage."
//					+ " As well as this, [npc.sheIs] now capable of inflicting great damage by using ice-based attacks.");
//		}
//	};
//
//	public static AbstractPerk ELEMENTAL_BOUND_AIR = new AbstractPerk(20,
//			true,
//			"Bound to Air",
//			PerkCategory.JOB,
//			"combat/spell/elemental_air",
//			PresetColour.SPELL_SCHOOL_AIR,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
//					new Value<>(Attribute.DAMAGE_POISON, 50),
//					new Value<>(Attribute.RESISTANCE_POISON, 50)),
//			null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return UtilText.parse(owner, "By being bound to the school of Air, [npc.name] has gained an ethereal body that is extremely resilient to poison damage."
//					+ " As well as this, [npc.sheIs] now capable of inflicting great damage by using poison-based attacks.");
//		}
//	};
//
//	public static AbstractPerk ELEMENTAL_BOUND_ARCANE = new AbstractPerk(20,
//			true,
//			"Bound to Arcane",
//			PerkCategory.JOB,
//			"combat/spell/elemental_arcane",
//			PresetColour.SPELL_SCHOOL_AIR,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
//					new Value<>(Attribute.DAMAGE_LUST, 50),
//					new Value<>(Attribute.DAMAGE_SPELLS, 25),
//					new Value<>(Attribute.SPELL_COST_MODIFIER, 25),
//					new Value<>(Attribute.RESISTANCE_LUST, -50)),
//			null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return UtilText.parse(owner, "By being bound to the school of Arcane, [npc.name] has gained an ethereal body that capable of inflicting great damage by using lust-based attacks."
//					+ " [npc.She] has also become more adept at casting spells, but the arcane's arousing power has left [npc.herHim] more susceptible to lust-based attacks.");
//		}
//	};

	public static AbstractPerk ELEMENTAL_CORE_OCCUPATION = new AbstractPerk(20,
			true,
			"元素",
			PerkCategory.JOB,
			"perks/elemental/coreOccupation",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 50),
					new Value<>(Attribute.DAMAGE_SPELLS, 25),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 100),
					new Value<>(Attribute.MAJOR_CORRUPTION, 100)
					), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"作为一种纯粹的奥术能量，元素可以轻而易举地驾驭用于施法的奥术。"
					+ "由于奥术的淫荡本质，它们通常都十分的变态，无时不刻地准备着热烈的性交……");
		}
	};

//	public static AbstractPerk ELEMENTAL_CORRUPTION = new AbstractPerk(20,
//			false,
//			"elemental",
//			PerkCategory.LUST,
//			"perks/elemental/coreCorruption",
//			PresetColour.GENERIC_ARCANE,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_CORRUPTION, 100)
//					), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return UtilText.parse(owner, "Even if their summoner is completely pure and innocent, the lustful nature of the arcane causes all elementals to be incredibly perverted."
//					+ " If nothing else, they can always be relied upon to be willing and ready to have sex with anyone or anything...");
//		}
//	};
	
	// ELEMENTAL FIRE

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FIREBALL,
			null,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]即可使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FIREBALL,
			SpellUpgrade.FIREBALL_1,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FIREBALL,
			SpellUpgrade.FIREBALL_2,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FIREBALL,
			SpellUpgrade.FIREBALL_3,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FLASH,
			null,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]即可使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FLASH,
			SpellUpgrade.FLASH_1,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FLASH,
			SpellUpgrade.FLASH_2,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FLASH,
			SpellUpgrade.FLASH_3,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.CLOAK_OF_FLAMES,
			null,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]即可使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.CLOAK_OF_FLAMES,
			SpellUpgrade.CLOAK_OF_FLAMES_1,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.CLOAK_OF_FLAMES,
			SpellUpgrade.CLOAK_OF_FLAMES_2,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.CLOAK_OF_FLAMES,
			SpellUpgrade.CLOAK_OF_FLAMES_3,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};
	
	public static AbstractPerk ELEMENTAL_FIRE_BOOST_MINOR = new AbstractPerk(20,
			false,
			"点燃",
			PerkCategory.ARCANE_FIRE,
			"perks/elemental/fire1",
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 1),
					new Value<>(Attribute.RESISTANCE_FIRE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "倘若为[npc.name]指点迷津，令其踏上控制与应用奥术火焰的道路，那么[npc.name]便迈出了解开烈火奥术学派秘密的第一步。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_FIRE_BOOST = new AbstractPerk(20,
			false,
			"燃烧",
			PerkCategory.ARCANE_FIRE,
			"perks/elemental/fire2",
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 3),
					new Value<>(Attribute.RESISTANCE_FIRE, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.nameIsFull]继续着追寻火元素之路，"
					+ "虽然[npc.she]在达到新的里程碑之前还有一段路要走，但[npc.her]与奥术火焰的亲和力正在稳步提升。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_FIRE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"爆燃",
			PerkCategory.ARCANE_FIRE,
			"perks/elemental/fire3",
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 6),
					new Value<>(Attribute.RESISTANCE_FIRE, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]对奥术火焰的亲和力大幅提升，[npc.sheIs]即将发掘出某种无比强大的存在！");
		}
	};
	
	public static AbstractPerk ELEMENTAL_FIRE_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"燃尽",
			PerkCategory.ARCANE_FIRE,
			"perks/elemental/fire4",
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 20),
					new Value<>(Attribute.RESISTANCE_FIRE, 20),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "燃烧着太阳那美丽而又可怕的力量，谁敢招致[npc.name]的怒火，只此一击，足以将之击溃。");
		}
	};
	
	// ELEMENTAL EARTH

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SLAM,
			null,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]就能使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SLAM,
			SpellUpgrade.SLAM_1,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SLAM,
			SpellUpgrade.SLAM_2,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SLAM,
			SpellUpgrade.SLAM_3,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEKENETIC_SHOWER,
			null,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]就能使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEKENETIC_SHOWER,
			SpellUpgrade.TELEKENETIC_SHOWER_1,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEKENETIC_SHOWER,
			SpellUpgrade.TELEKENETIC_SHOWER_2,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEKENETIC_SHOWER,
			SpellUpgrade.TELEKENETIC_SHOWER_3,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.STONE_SHELL,
			null,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]就能使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.STONE_SHELL,
			SpellUpgrade.STONE_SHELL_1,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.STONE_SHELL,
			SpellUpgrade.STONE_SHELL_2,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.STONE_SHELL,
			SpellUpgrade.STONE_SHELL_3,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_BOOST_MINOR = new AbstractPerk(20,
			false,
			"影响力",
			PerkCategory.PHYSICAL_EARTH,
			"perks/elemental/earth1",
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 1),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "倘若为[npc.name]指点迷津，令其踏上捏造强大物质形态的道路，那么[npc.name]便迈出了解开大地奥术学派秘密的第一步。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_EARTH_BOOST = new AbstractPerk(20,
			false,
			"增压",
			PerkCategory.PHYSICAL_EARTH,
			"perks/elemental/earth2",
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 3),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.nameIsFull]继续着追寻土元素之路，"
					+ "虽然[npc.she]在达到新的里程碑之前还有一段路要走，但[npc.her]与奥术原力的亲和力正在稳步提升。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_EARTH_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"地震活动",
			PerkCategory.PHYSICAL_EARTH,
			"perks/elemental/earth3",
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 6),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]对奥术原力的亲和力大幅提升，[npc.sheIs]即将发掘出某种无比强大的存在！");
		}
	};
	
	public static AbstractPerk ELEMENTAL_EARTH_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"地震中心",
			PerkCategory.PHYSICAL_EARTH,
			"perks/elemental/earth4",
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 20),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 20),
					new Value<>(Attribute.DAMAGE_UNARMED, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.name]现在能够无视几乎任何攻击，以一种不可战胜的姿态接近[npc.her]的敌人，给予其终结的一击。");
		}
	};
	
	// ELEMENTAL WATER

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ICE_SHARD,
			null,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]就能使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ICE_SHARD,
			SpellUpgrade.ICE_SHARD_1,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ICE_SHARD,
			SpellUpgrade.ICE_SHARD_2,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ICE_SHARD,
			SpellUpgrade.ICE_SHARD_3,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};
	
	public static AbstractPerk ELEMENTAL_WATER_SPELL_2 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.RAIN_CLOUD,
			null,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]就能使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_2_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.RAIN_CLOUD,
			SpellUpgrade.RAIN_CLOUD_1,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_2_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.RAIN_CLOUD,
			SpellUpgrade.RAIN_CLOUD_2,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_2_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.RAIN_CLOUD,
			SpellUpgrade.RAIN_CLOUD_3,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SOOTHING_WATERS,
			null,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]就能使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SOOTHING_WATERS,
			SpellUpgrade.SOOTHING_WATERS_1,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SOOTHING_WATERS,
			SpellUpgrade.SOOTHING_WATERS_2,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SOOTHING_WATERS,
			SpellUpgrade.SOOTHING_WATERS_3,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_BOOST_MINOR = new AbstractPerk(20,
			false,
			"寒意",
			PerkCategory.PHYSICAL_WATER,
			"perks/elemental/water1",
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_ICE, 1),
					new Value<>(Attribute.RESISTANCE_ICE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "倘若为[npc.name]指点迷津，令其踏上控制与应用奥术寒冷的道路，那么[npc.name]便迈出了解开激流奥术学派秘密的第一步。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_WATER_BOOST = new AbstractPerk(20,
			false,
			"结霜",
			PerkCategory.PHYSICAL_WATER,
			"perks/elemental/water2",
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_ICE, 3),
					new Value<>(Attribute.RESISTANCE_ICE, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.nameIsFull]继续着追寻水元素之路，"
					+ "虽然[npc.she]在达到新的里程碑之前还有一段路要走，但[npc.her]与奥术寒冷的亲和力正在稳步提升。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_WATER_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"极寒",
			PerkCategory.PHYSICAL_WATER,
			"perks/elemental/water3",
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_ICE, 6),
					new Value<>(Attribute.RESISTANCE_ICE, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]对寒冷奥术的亲和力大幅提升，[npc.sheIs]即将发掘出某种无比强大的存在！");
		}
	};
	
	public static AbstractPerk ELEMENTAL_WATER_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"冰河时代",
			PerkCategory.PHYSICAL_WATER,
			"perks/elemental/water4",
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_ICE, 20),
					new Value<>(Attribute.RESISTANCE_ICE, 20),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "优雅地从一处滑向另一处，[npc.name]能够在一瞬间改变[npc.her]的身体，使其足以完成物理意义上几乎不可能完成的壮举。");
		}
	};
	
	// ELEMENTAL AIR

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.POISON_VAPOURS,
			null,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]就能使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.POISON_VAPOURS,
			SpellUpgrade.POISON_VAPOURS_1,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.POISON_VAPOURS,
			SpellUpgrade.POISON_VAPOURS_2,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.POISON_VAPOURS,
			SpellUpgrade.POISON_VAPOURS_3,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.VACUUM,
			null,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]就能使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.VACUUM,
			SpellUpgrade.VACUUM_1,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.VACUUM,
			SpellUpgrade.VACUUM_2,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.VACUUM,
			SpellUpgrade.VACUUM_3,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.PROTECTIVE_GUSTS,
			null,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]就能使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.PROTECTIVE_GUSTS,
			SpellUpgrade.PROTECTIVE_GUSTS_1,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.PROTECTIVE_GUSTS,
			SpellUpgrade.PROTECTIVE_GUSTS_2,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.PROTECTIVE_GUSTS,
			SpellUpgrade.PROTECTIVE_GUSTS_3,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_BOOST_MINOR = new AbstractPerk(20,
			false,
			"微风",
			PerkCategory.ARCANE_AIR,
			"perks/elemental/air1",
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_POISON, 1),
					new Value<>(Attribute.RESISTANCE_POISON, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "倘若为[npc.name]指点迷津，令其踏上控制与应用奥术毒素的道路，那么[npc.name]便迈出了解开大气奥术学派秘密的第一步。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_AIR_BOOST = new AbstractPerk(20,
			false,
			"大风",
			PerkCategory.ARCANE_AIR,
			"perks/elemental/air2",
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_POISON, 3),
					new Value<>(Attribute.RESISTANCE_POISON, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.nameIsFull]继续着追寻风元素之路，"
					+ "虽然[npc.she]在达到新的里程碑之前还有一段路要走，但[npc.her]与奥术毒素的亲和力正在稳步提升。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_AIR_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"风暴",
			PerkCategory.ARCANE_AIR,
			"perks/elemental/air3",
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_POISON, 6),
					new Value<>(Attribute.RESISTANCE_POISON, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]对毒素奥术的亲和力大幅提升，[npc.sheIs]即将发掘出某种无比强大的存在！");
		}
	};
	
	public static AbstractPerk ELEMENTAL_AIR_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"强风暴系统",
			PerkCategory.ARCANE_AIR,
			"perks/elemental/air4",
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_POISON, 20),
					new Value<>(Attribute.RESISTANCE_POISON, 20),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "任何愚蠢到招致[npc.namePos]怒火的敌人，很快就会发现他们正在自食其果，"
					+ "[npc.her]引导的阵风使[npc.her]的每一发毁灭性的投射物，都将完美且精准地命中目标。");
		}
	};
	
	// ELEMENTAL ARCANE

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_AROUSAL,
			null,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]就能使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_AROUSAL,
			SpellUpgrade.ARCANE_AROUSAL_1,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_AROUSAL,
			SpellUpgrade.ARCANE_AROUSAL_2,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_AROUSAL,
			SpellUpgrade.ARCANE_AROUSAL_3,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEPATHIC_COMMUNICATION,
			null,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]就能使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEPATHIC_COMMUNICATION,
			SpellUpgrade.TELEPATHIC_COMMUNICATION_1,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEPATHIC_COMMUNICATION,
			SpellUpgrade.TELEPATHIC_COMMUNICATION_2,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEPATHIC_COMMUNICATION,
			SpellUpgrade.TELEPATHIC_COMMUNICATION_3,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3 = new AbstractPerk(20,
			false,
			"法术",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_CLOUD,
			null,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "法术："+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("获得法术“<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，前提是绑定至<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"学派</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "当绑定至"+getSchool().getName()+"学派时，[npc.name]就能使用'"+getSpell().getName()+"'。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3_1 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_CLOUD,
			SpellUpgrade.ARCANE_CLOUD_1,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3_2 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_CLOUD,
			SpellUpgrade.ARCANE_CLOUD_2,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3_3 = new AbstractPerk(20,
			false,
			"升级",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_CLOUD,
			SpellUpgrade.ARCANE_CLOUD_3,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "升级："+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("升级法术“<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>”"
					+ "，获得强化(<span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>)");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]的法术，"+getSpell().getName()+"，将会升级成更强力的“"+getSpellUpgrade().getName()+"”。");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_BOOST_MINOR = new AbstractPerk(20,
			false,
			"觉醒",
			PerkCategory.LUST,
			"perks/elemental/arcane1",
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 1),
					new Value<>(Attribute.RESISTANCE_LUST, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "倘若为[npc.name]指点迷津，令其踏上控制纯粹奥术力量的道路，那么[npc.name]便迈出了解开奥术学派秘密的第一步。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_ARCANE_BOOST = new AbstractPerk(20,
			false,
			"激情",
			PerkCategory.LUST,
			"perks/elemental/arcane2",
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 3),
					new Value<>(Attribute.RESISTANCE_LUST, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.nameIsFull]继续着追寻奥术元素之路，"
					+ "虽然[npc.she]在达到新的里程碑之前还有一段路要走，但[npc.her]与奥术性欲的亲和力正在稳步提升。");
		}
	};
	
	public static AbstractPerk ELEMENTAL_ARCANE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"迷恋",
			PerkCategory.LUST,
			"perks/elemental/arcane3",
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 6),
					new Value<>(Attribute.RESISTANCE_LUST, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos]对奥术的亲和力大幅提升，[npc.sheIs]即将发掘出某种无比强大的存在！");
		}
	};
	
	public static AbstractPerk ELEMENTAL_ARCANE_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"痴情",
			PerkCategory.LUST,
			"perks/elemental/arcane4",
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 20),
					new Value<>(Attribute.RESISTANCE_LUST, 20),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "那些注视[npc.namePos]"+(owner.isFeminine()?"动人躯体与迷人":"如刀凿般躯体与英俊")
					+"脸庞的人如中了魔咒，顷刻间就被狂野的兽欲完全吞噬。");
		}
	};
	
	// Doll perks:
	
	public static AbstractPerk DOLL_PHYSICAL_CORE = new AbstractPerk(20,
			false,
			"奥术附魔硅胶",
			PerkCategory.PHYSICAL,
			"perks/doll/physical0",
			Util.newArrayListOfValues(PresetColour.BASE_WHITE, PresetColour.GENERIC_ARCANE),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 10),
					new Value<>(Attribute.RESISTANCE_FIRE, 10),
					new Value<>(Attribute.RESISTANCE_ICE, 10),
					new Value<>(Attribute.RESISTANCE_POISON, 10)),
			Util.newArrayListOfValues(
					"腔穴缓慢吸收液体"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NamePos]的身体全由一种充斥着奥术的硅胶状聚合物制成的，其成分是“洛维耶纳奢侈品店”严守的秘密。"
						+ "这种材料不仅能缓慢吸收储存的液体，将其转化为能量，还特别耐用。");
		}
	};
	
	public static AbstractPerk DOLL_PHYSICAL_1 = new AbstractPerk(20,
			false,
			"关节过度活动症",
			PerkCategory.PHYSICAL,
			"perks/doll/physical1",
			PresetColour.BASE_PINK_LIGHT,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"[style.colourExcellent(解锁)]自我口交和自我舔阴")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NamePos]的身体异常灵活，[npc.herHim]可以随意摆出连体操运动员都会嫉妒的姿势。");
		}
	};
	
	public static AbstractPerk DOLL_PHYSICAL_2 = new AbstractPerk(20,
			false,
			"不孕不育",
			PerkCategory.PHYSICAL,
			"perks/doll/physical2",
			PresetColour.RACE_DOLL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.VIRILITY, -1000),
					new Value<>(Attribute.FERTILITY, -1000)),
			Util.newArrayListOfValues(
					"受精概率几乎为[style.colourTerrible(零)]",
					"怀孕概率几乎为[style.colourTerrible(零)]",
					"[style.colourTerrible(无法)]孵化蛋")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NamePos]的人造躯体无法生育[npc.her]自己的后代。"
					+ "[npc.Her]原状难改的腔穴也不适合产卵。");
		}
	};
	
	public static AbstractPerk DOLL_PHYSICAL_3 = new AbstractPerk(20,
			false,
			"无器官",
			PerkCategory.PHYSICAL,
			"perks/doll/physical3",
			PresetColour.BASE_RED_LIGHT,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_POISON, 50)),
			Util.newArrayListOfValues(
					"[style.colourExcellent(免疫)]液体成瘾，精神活性物质和酒精的影响",
					"腔穴拥有着[style.colourExcellent(无限)]深度")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.namePos]的内部结构仅由通向[npc.her]腔穴深处的通道构成。"
					+ "由于没有任何生物系统，[npc.name]能免疫绝大多数物质的效果。");
		}
	};

	public static AbstractPerk DOLL_LUST_CORE = new AbstractPerk(20,
			false,
			"性爱玩具",
			PerkCategory.LUST,
			"perks/doll/lust0",
			PresetColour.RACE_DOLL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 100),
					new Value<>(Attribute.RESISTANCE_LUST, -100)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameHasFull]是以性爱玩偶的目的被设计和制造的，虽然[npc.she]能胜任绝大多数的其他任务，[npc.her]最根本的目的还是被操。");
		}
	};

	public static AbstractPerk DOLL_LUST_1 = new AbstractPerk(20,
			false,
			"为操而生",
			PerkCategory.LUST,
			"perks/doll/lust1",
			PresetColour.RACE_DOLL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"永远[style.colourTerrible(不能)]在[style.colourSex(性爱场景)]中<span style='color:"+SexPace.SUB_RESISTING.getColour().toWebHexString()+";'>抵抗</span>",
					"不反对<span style='color:"+SexPace.SUB_RESISTING.getColour().toWebHexString()+";'>施虐</span>性行动",
					"性取向始终为[style.colourAndrogynous(双性恋)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.Name]只渴求一件事——挨操，不管是谁来操，也无论他们有多粗暴。");
		}
	};

	public static AbstractPerk DOLL_LUST_2 = new AbstractPerk(20,
			false,
			"所有物",
			PerkCategory.LUST,
			"perks/doll/lust2",
			Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"将会总是[style.colourSex(服从)]所有者",
					"[style.colourObedience(服从)]将总是[style.colourExcellent(维持在100)]"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.Name]知道[npc.sheIs]仅仅是个物品，并且[npc.her]将毫无保留地去做任何[npc.her]的所有者要求[npc.her]做的事情。");
		}
	};

	public static AbstractPerk DOLL_LUST_3 = new AbstractPerk(20,
			false,
			"万事皆允",
			PerkCategory.LUST,
			"perks/doll/lust3",
			Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE_GREY, PresetColour.BASE_GREY_LIGHT),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"[style.colourTerrible(无法)]获得[style.colourFetish(性癖)]",
					"[style.colourFetish(性癖欲望)]将总是[style.colourHuman(无倾向)]",
					"对暴露程度，衣物女性化程度，贞操和污浊都漠不关心"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"作为一个没有自我意志的机器人，[npc.name]对不同的性癖没有任何想法，同时，不论[npc.her]得到什么样的要求，[npc.her]都会从附和其性对象的一切想法中获得满足感。");
		}
	};
	
	public static AbstractPerk DOLL_ARCANE_CORE = new AbstractPerk(20,
			false,
			"自动人偶",
			PerkCategory.ARCANE,
			"perks/doll/arcane0",
			Util.newArrayListOfValues(PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_PINK_LIGHT),
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, -1),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, -25),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -25)),
			Util.newArrayListOfValues(
					"可以无期限地保持静止不动"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull]，一个自动人偶，虽然[npc.sheIs]可以以一种栩栩如生的方式运动，但[npc.she]永远也不会像真人一样迅捷灵活。"
					+ "如果指令需要，[npc.she]可以在近乎无限的时间内保持静止不动。");
		}
	};
	
	public static AbstractPerk DOLL_ARCANE_1 = new AbstractPerk(20,
			false,
			"无灵气",
			PerkCategory.ARCANE,
			"perks/doll/arcane1",
			Util.newArrayListOfValues(PresetColour.BASE_BLACK, PresetColour.GENERIC_ARCANE),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, -1000)),
			Util.newArrayListOfValues(
					"[style.colourSex(高潮时)][style.colourTerrible(永远无法)]产生精华",
					"[style.colourTerrible(永远无法)]吸收精华",
					"[style.colourExcellent(免疫)][style.colourTfGeneric(常规转化)]"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.name]只是个玩偶，并不是真正的人，[npc.sheIs]没有奥术灵气，因此既不能吸收也不能生产精华。"
						+ "常规的转化方法也无法对[npc.herHim]产生影响，只有玩偶专用的特殊转化才能生效。");
		}
	};

	public static AbstractPerk DOLL_ARCANE_2 = new AbstractPerk(20,
			false,
			"物件的局限性",
			PerkCategory.ARCANE,
			"perks/doll/arcane2",
			PresetColour.GENERIC_EXPERIENCE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"[style.colourTerrible(永远无法)]获得[style.colourExperience(经验值)]",
					"[style.colourTerrible(永久锁定)]为[style.colourExperience(1 级)]",
					"[style.colourTerrible(永远无法)]获得特性",
					"对他人的[style.colourAffection(好感)]<span style='color:"+PresetColour.AFFECTION_NEUTRAL.toWebHexString()+";'>总是中立的</span>")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.nameIsFull]没有任何的自由意志和良知，[npc.her]无法从经历中学到任何事物，而且也不会像真人一样成长发育。");
		}
	};

	public static AbstractPerk DOLL_ARCANE_3 = new AbstractPerk(20,
			false,
			"奥术能源",
			PerkCategory.ARCANE,
			"perks/doll/arcane3",
			Util.newArrayListOfValues(PresetColour.BASE_PINK, PresetColour.GENERIC_ARCANE),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"无需进食、饮水和睡眠",
					"每当暴露在奥术风暴下，获得[style.colourExcellent(1000)]"+Attribute.HEALTH_MAXIMUM.getColouredName("span")),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.Name]无需从食物或饮料中获取热量，而是被动吸收着周边的奥术能量来实现自我供能。"
						+ "正因如此，奥术风暴能赋予其大量的生命力。");
		}
	};
	
	public static List<AbstractPerk> hiddenPerks;
	public static List<AbstractPerk> allPerks;
	public static List<AbstractPerk> subspeciesKnowledgePerks;
	
	public static Map<AbstractPerk, String> perkToIdMap = new HashMap<>();
	public static Map<String, AbstractPerk> idToPerkMap = new HashMap<>();
	
	private static boolean subspeciesPerksGenerated = false;
	
	public static AbstractPerk getPerkFromId(String id) {
		if(!subspeciesPerksGenerated) {
			generateSubspeciesPerks();
		}
//		System.out.print("ID: "+id);
		if(id.equalsIgnoreCase("MERAXIS")
				|| id.equalsIgnoreCase("ARCANE_TATTOOIST")
				|| id.equalsIgnoreCase("SLUT")
				|| id.equalsIgnoreCase("ARCANE_TRAINING")
				|| id.equalsIgnoreCase("ARCANE_ALLERGY")
				|| id.equalsIgnoreCase("HEALTH_FANATIC")
				|| id.equalsIgnoreCase("MARTIAL_BACKGROUND")) {
			id = "SPECIAL_"+id;
		} else if(id.equalsIgnoreCase("BRAWLER")) {
			id = "FEROCIOUS_WARRIOR";
		} else if(id.equalsIgnoreCase("ARCANE_BASE_NPC")) {
			id = "ARCANE_BASE";
		} else if(id.equalsIgnoreCase("PHYSIQUE_5")) {
			id = "PHYSIQUE_BOOST_MAJOR";
		} else if(id.equalsIgnoreCase("ARCANE_5")) {
			id = "ARCANE_BOOST_MAJOR";
		} else if(id.equalsIgnoreCase("SPELL_DAMAGE_5")) {
			id = "SPELL_DAMAGE_MAJOR";
		} else if(id.equalsIgnoreCase("ELEMENTALIST_5")) {
			id = "ELEMENTAL_BOOST";
		} else if(id.equalsIgnoreCase("CRITICAL_BOOST_ALT")) {
			id = "CRITICAL_BOOST_LUST";
		} else if(id.equalsIgnoreCase("CRITICAL_BOOST_ALT_2")) {
			id = "CRITICAL_BOOST_ARCANE";
		}
		
		
		id = Util.getClosestStringMatch(id, idToPerkMap.keySet());
//		System.out.println("  set to: "+id);
		return idToPerkMap.get(id);
	}
	
	public static String getIdFromPerk(AbstractPerk perk) {
		if(!subspeciesPerksGenerated) {
			generateSubspeciesPerks();
		}
		return perkToIdMap.get(perk);
	}

	static {
		hiddenPerks = new ArrayList<>();
		allPerks = new ArrayList<>();
		subspeciesKnowledgePerks = new ArrayList<>();
		
		Field[] fields = Perk.class.getFields();
		
		for(Field f : fields){
			if (AbstractPerk.class.isAssignableFrom(f.getType())) {
				
				AbstractPerk perk;
				
				try {
					perk = ((AbstractPerk) f.get(null));

					// I feel like this is stupid :thinking:
					perkToIdMap.put(perk, f.getName());
					idToPerkMap.put(f.getName(), perk);
					
					allPerks.add(perk);
					if(perk.isHiddenPerk()) {
						hiddenPerks.add(perk);
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		hiddenPerks.sort((p1, p2) -> p1.getRenderingPriority()-p2.getRenderingPriority());
	}
	
	public static void generateSubspeciesPerks() {
		List<AbstractAttribute> resistancesAdded = new ArrayList<>();
		for(AbstractSubspecies sub : Subspecies.getAllSubspecies()) {
			if(!resistancesAdded.contains(sub.getDamageMultiplier())) {
				resistancesAdded.add(sub.getDamageMultiplier());
				boolean mainSubspecies = sub.getDamageMultiplier()==AbstractSubspecies.getMainSubspeciesOfRace(sub.getRace()).getDamageMultiplier();
				AbstractSubspecies subToUse = mainSubspecies
												?AbstractSubspecies.getMainSubspeciesOfRace(sub.getRace())
												:sub;
				
				AbstractPerk racePerk = new AbstractPerk(20,
						false,
						Util.capitaliseSentence(mainSubspecies?sub.getRace().getName(false):subToUse.getName(null))+"知识",
						PerkCategory.LUST,
						null,
						sub.getColour(null),
						Util.newHashMapOfValues(
								new Value<>(subToUse.getDamageMultiplier(), 10)),
						null) {
					@Override
					public String getDescription(GameCharacter owner) {
						return UtilText.parse(owner, "[npc.NameHasFull]掌握着"+(mainSubspecies?sub.getRace().getNamePlural(false):subToUse.getNamePlural(null))+"的高阶知识，在与其战斗时可以造成更高的伤害。");
					}
					@Override
					public String getSVGString(GameCharacter owner) {
						return subToUse.getSVGString(null);
					}
					@Override
					public Colour getColour() {
						return subToUse.getColour(null);
					}
					@Override
					public boolean isHiddenPerk() {
						return true;
					}
				};
//				System.out.println("Added perk: "+Subspecies.getIdFromSubspecies(subToUse)+" "+racePerk.getName(null)+" "+racePerk.hashCode());
				perkToIdMap.put(racePerk, Subspecies.getIdFromSubspecies(subToUse));
				idToPerkMap.put(Subspecies.getIdFromSubspecies(subToUse), racePerk);
				allPerks.add(racePerk);
				hiddenPerks.add(racePerk);
				subspeciesKnowledgePerks.add(racePerk);
			}
		}
		subspeciesPerksGenerated = true;
		hiddenPerks.sort((p1, p2) -> p1.getRenderingPriority()-p2.getRenderingPriority());
	}
	
	public static AbstractPerk getSubspeciesRelatedPerk(AbstractSubspecies subspecies) {
		if(!subspeciesPerksGenerated) {
			generateSubspeciesPerks();
		}
		
		AbstractSubspecies subToUse = 
				subspecies.getDamageMultiplier()==AbstractSubspecies.getMainSubspeciesOfRace(subspecies.getRace()).getDamageMultiplier()
					?AbstractSubspecies.getMainSubspeciesOfRace(subspecies.getRace())
					:subspecies;
		
		return Perk.getPerkFromId(Subspecies.getIdFromSubspecies(subToUse));
	}
	
	public static List<AbstractPerk> getAllPerks() {
		if(!subspeciesPerksGenerated) {
			generateSubspeciesPerks();
		}
		return allPerks;
	}
	
	public static List<AbstractPerk> getHiddenPerks() {
		if(!subspeciesPerksGenerated) {
			generateSubspeciesPerks();
		}
		return hiddenPerks;
	}

	public static List<AbstractPerk> getSubspeciesKnowledgePerks() {
		if(!subspeciesPerksGenerated) {
			generateSubspeciesPerks();
		}
		return subspeciesKnowledgePerks;
	}
}
