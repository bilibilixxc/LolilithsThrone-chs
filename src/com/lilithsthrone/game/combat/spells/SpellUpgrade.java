package com.lilithsthrone.game.combat.spells;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.4
 * @version 0.2.4
 * @author Innoxia
 */
public enum SpellUpgrade {

	// Fire:
	
	FIREBALL_1(true,
			1,
			SpellSchool.FIRE,
			"fireball_lingering_flames",
			"不灭烈焰",
			"火球术击中目标后，奥术火焰仍会在其身上燃烧一段时间。",
			null, Util.newArrayListOfValues(
					"每回合<b>5</b>[style.colourFire(火焰伤害)]，持续[style.colourGood(2回合)]")),
	FIREBALL_2(2,
			SpellSchool.FIRE,
			"fireball_twin_comets",
			"双生彗星",
			"火球术在释放后会立刻分为两道彗星，似乎自身拥有思想，会尽量优先攻击不同目标。",
			null, Util.newArrayListOfValues(
					"发射[style.colourExcellent(两发)]火球，每发造成<b>15</b>[style.colourFire(火焰伤害)]")),
	FIREBALL_3(5,
			SpellSchool.FIRE,
			"fireball_burning_fury",
			"灼热之怒",
			"火球术的双生彗星现在狂怒地燃烧着，造成更多伤害。",
			null, Util.newArrayListOfValues(
					"双生彗星现在每个造成<b>30</b>[style.colourFire(火焰伤害)]")),
	
	
	FLASH_1(true,
			2,
			SpellSchool.FIRE,
			"flash_secondary_sparks",
			"次生辉光",
			"在第一道闪光之后，还会有一连串炫目的闪光在目标面前迸发。",
			null, Util.newArrayListOfValues(
					"闪光术现在[style.colourExcellent(眩晕)]目标，使其[style.colourTerrible(-2)][style.colourActionPoints(行动点)]")),
	FLASH_2(5,
			SpellSchool.FIRE,
			"flash_arcing_flash",
			"弧状闪光",
			"第二道闪光从第一道中分出，搜寻下一个目眩的目标。",
			null, Util.newArrayListOfValues(
					"释放[style.colourExcellent(两道)]闪光")),
	FLASH_3(3,
			SpellSchool.FIRE,
			"flash_efficient_burn",
			"充分燃烧",
			"施法者将能量集中在为更小、更集中的爆发中，便可以降低闪光术的消耗，并且不影响原有的效果。",
			null, Util.newArrayListOfValues(
					"基础消耗[style.colourExcellent(降低)]至[style.boldMana(25)]灵气")),
	
	
	CLOAK_OF_FLAMES_1(true,
			2,
			SpellSchool.FIRE,
			"cloak_of_flames_incendiary",
			"引燃",
			"每进行一次打击，火焰斗篷中就会射出一串火焰，烧伤敌人。造成的伤害受到穿戴者火系伤害加成和目标的火焰护盾数量影响。",
			null, Util.newArrayListOfValues(
					"施法者每有一级，徒手攻击伤害额外+1",
					"徒手攻击造成[style.boldFire(火焰伤害)]")),
	CLOAK_OF_FLAMES_2(1,
			SpellSchool.FIRE,
			"cloak_of_flames_inferno",
			"地狱烈焰",
			"火焰斗篷赐予了穿戴者神秘的知识，能够尽可能多地造成火焰伤害。",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(获得)]+25[style.boldFire(火焰伤害)]")),
	CLOAK_OF_FLAMES_3(2,
			SpellSchool.FIRE,
			"cloak_of_flames_ring_of_fire",
			"火焰之环",
			"任何接近火焰斗篷的敌人都会迎上烈火的复仇。造成的伤害受到穿戴者火系伤害加成和攻击者的火焰护盾数量影响。",
			null, Util.newArrayListOfValues(
					"<b>近战</b>或<b>徒手</b>攻击者会受到<b>5</b>[style.colourFire(火焰伤害)]")),

	
	ELEMENTAL_FIRE_1(true,
			5,
			SpellSchool.FIRE,
			"elemental_fire_wildfire",
			"野火",
			"召唤而来的元素体赐予了所有盟友神秘的知识，该如何更好地驾驭元素火焰。",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(所有盟友获得)]+20[style.boldFire(火焰伤害)]")),
	ELEMENTAL_FIRE_2(5,
			SpellSchool.FIRE,
			"elemental_fire_burning_desire",
			"点燃欲望",
			"火元素能够操纵奥术中的性欲属性，并点燃敌人心中和脑中对于性爱的渴望。",
			null, Util.newArrayListOfValues(
					"[style.colourTerrible(所有敌人遭受)]-25[style.boldLust("+Attribute.RESISTANCE_LUST.getName()+")]")),
	ELEMENTAL_FIRE_3A(5,
			SpellSchool.FIRE,
			"elemental_fire_servant_of_fire",
			"烈焰仆佣",
			"召唤者向烈火学派宣誓效忠，当元素体被绑定至此形态时，能够随意从召唤者处获取能量。",
			null, Util.newArrayListOfValues(
					"被召唤时:",
					"[style.colourFire(元素)]: +100%[style.colourExcellent(非魅惑伤害)]",
					"[style.colourArcane(施法者)]: -50%[style.colourHealth(最大"+Attribute.HEALTH_MAXIMUM.getName()+")]")) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_FIRE_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(与“烈焰束缚”互斥！)]";
			} else {
				return "[style.boldBad(与“烈焰束缚”互斥！)]";
			}
		}
	},
	ELEMENTAL_FIRE_3B(10,
			SpellSchool.FIRE,
			"elemental_fire_binding_of_fire",
			"烈焰束缚",
			"召唤者对烈火学派拥有绝对统治，当元素体被绑定至此形态时，能够强制其分享深藏的秘密。",
			null, Util.newArrayListOfValues(
					"被召唤时:",
					"[style.colourArcane(施法者)]: +25"+Attribute.DAMAGE_FIRE.getColouredName("b"),
					"[style.colourArcane(施法者)]: +10"+Attribute.RESISTANCE_FIRE.getColouredName("b"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_FIRE_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(与“烈焰仆佣”互斥！)]";
			} else {
				return "[style.boldBad(与“烈焰仆佣”互斥！)]";
			}
		}
	},
	
	// Water:

	ICE_SHARD_1(true,
			1,
			SpellSchool.WATER,
			"ice_shard_freezing_fog",
			"冻人寒雾",
			"冰刃受到撞击后会炸开，使目标周围的空气中充满奥术冰晶。随后这些晶体会迅速凝结成冰雾，削弱目标的施法能力。",
			null, Util.newArrayListOfValues(
					"<b>-20</b>"+Attribute.SPELL_COST_MODIFIER.getColouredName("b")+"，持续[style.colourGood(3回合)]")),
	ICE_SHARD_2(2,
			SpellSchool.WATER,
			"ice_shard_cold_snap",
			"寒冰骤击",
			"冰刃术在穿过上次碰撞后生成的冻人寒雾时，空气中的冰晶也会爆炸，造成额外伤害。",
			null, Util.newArrayListOfValues(
					"冰刃术[style.boldExcellent(会暴击)]受到冻人寒雾影响的目标")),
	ICE_SHARD_3(5,
			SpellSchool.WATER,
			"ice_shard_deep_freeze",
			"深度冷冻",
			"冻人寒雾中的冰晶爆炸时，会瞬间将附近的任何物体包裹在薄冰中，瞬间将其束缚在原地。",
			null, Util.newArrayListOfValues(
					"当冰刃术[style.boldExcellent(暴击)]受到冻人寒雾影响的目标时，",
					"该目标会[style.colourTerrible(-1)][style.colourActionPoints(行动点)]")),

	RAIN_CLOUD_1(true,
			1,
			SpellSchool.WATER,
			"rain_cloud_deep_chill",
			"深度寒意",
			"奥术雨水将会渗入目标的骨髓，由内而外地冻寒目标。",
			null, Util.newArrayListOfValues(
					"为雨云术添加<b>-25</b>"+Attribute.RESISTANCE_ICE.getColouredName("b")+" 效果")),
	RAIN_CLOUD_2(3,
			SpellSchool.WATER,
			"rain_cloud_downpour",
			"倾盆大雨",
			"成片的奥术暴雨冲刷过目标的眼睛，使其攻击偶尔会未命中。",
			null, Util.newArrayListOfValues(
					"为雨云术添加<b>-5</b>"+Attribute.ENERGY_SHIELDING.getColouredName("b")+"效果")),
	RAIN_CLOUD_3(5,
			SpellSchool.WATER,
			"rain_cloud_cloud_burst",
			"膨胀云层",
			"雨云会将目标的恼与怒转化为能量，每当目标受到暴击时，雨云的强度和体积就会增加。",
			null, Util.newArrayListOfValues(
					"如果雨云术的目标被任何暴击攻击击中：",
					"持续时间会重置为[style.colourGood(6回合)]",
					"<b>并且</b>"+Attribute.SPELL_COST_MODIFIER.getColouredName("b")+"减益会提升至-50")),

	SOOTHING_WATERS_1_CLEAN(true,
			2,
			SpellSchool.WATER,
			"soothing_waters_clean",
			"清洁之水",
			"抚慰之水的能力流遍目标的身体及其衣物，清洁上面的所有肮脏液体。",
			null, Util.newArrayListOfValues(
					"抚慰之水能够[style.boldAqua(清洁所有穿着的衣物)]",
					"抚慰之水能够[style.boldAqua(清洁身体上的所有液体)]")),
	SOOTHING_WATERS_2_CLEAN(true,
			5,
			SpellSchool.WATER,
			"soothing_waters_clean_orifices",
			"深度清洁",
			"抚慰之水将会深度清洗过目标的身体，清理所有脏污的腔穴。",
			null, Util.newArrayListOfValues(
					"抚慰之水能够[style.boldAqua(从所有腔穴中移除[style.fluid(250)]液体)]")),
	SOOTHING_WATERS_1(true,
			5,
			SpellSchool.WATER,
			"soothing_waters_arcane_springs",
			"奥术之泉",
			"注入抚慰之水中的奥术之力大幅增加，使得该法术能够额外恢复目标的灵气。",
			null, Util.newArrayListOfValues(
					"抚慰之水能够[style.boldExcellent(额外)]恢复<b>20%</b>"+Attribute.MANA_MAXIMUM.getColouredName("b"))),
	SOOTHING_WATERS_2(10,
			SpellSchool.WATER,
			"soothing_waters_rejuvenation",
			"妙水回春",
			"奥术完全驾驭了水作为生命之源的特性，能够为抚慰之水的目标恢复大量能量。",
			null, Util.newArrayListOfValues(
					"舒缓之水的恢复量[style.boldExcellent(提高至)]<b>40%</b>"+Attribute.HEALTH_MAXIMUM.getColouredName("b"))),
	SOOTHING_WATERS_3(10,
			SpellSchool.WATER,
			"soothing_waters_bouncing_orb",
			"弹跳水球",
			"施法后，抚慰之水会分裂成数个小球，每一个都会寻找一名盟友为其恢复。",
			null, Util.newArrayListOfValues(
					"抚慰之水会恢复[style.boldExcellent(所有盟友)]的<b>10%</b>"+Attribute.HEALTH_MAXIMUM.getColouredName("b")+"和<b>10%</b>"+Attribute.MANA_MAXIMUM.getColouredName("b"))),

	ELEMENTAL_WATER_1(true,
			5,
			SpellSchool.WATER,
			"elemental_water_crashing_waves",
			"惊涛骇浪",
			"水元素会持续发出冰冷的水流冲击敌人，使其更容易受到寒冰的攻击。",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(所有盟友获得)]+20[style.boldIce(寒冷伤害)]")),
	ELEMENTAL_WATER_2(5,
			SpellSchool.WATER,
			"elemental_water_calm_waters",
			"古井无波",
			"若盟友即将升起欲望，水元素则会在其脑内投射平静水面的景象，帮助其控制性欲的获得。",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(所有盟友获得)]+5"+Attribute.RESISTANCE_LUST.getColouredName("b"))),
	ELEMENTAL_WATER_3A(5,
			SpellSchool.WATER,
			"elemental_water_servant_of_water",
			"水波仆佣",
			"召唤者向激流学派宣誓效忠，当元素体被绑定至此形态时，能够随意从召唤者处获取能量。",
			null, Util.newArrayListOfValues(
					"被召唤时:",
					"[style.colourIce(元素)]: +100%[style.colourExcellent(非魅惑伤害)]",
					"[style.colourArcane(施法者)]: -50%[style.colourHealth(最大"+Attribute.HEALTH_MAXIMUM.getName()+")]")) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_WATER_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(与“水波束缚”互斥！)]";
			} else {
				return "[style.boldBad(与“水波束缚”互斥！)]";
			}
		}
	},
	ELEMENTAL_WATER_3B(10,
			SpellSchool.WATER,
			"elemental_water_binding_of_water",
			"水波束缚",
			"召唤者对激流学派拥有绝对统治，当元素体被绑定至此形态时，能够强制其分享深藏的秘密。",
			null, Util.newArrayListOfValues(
					"被召唤时:",
					"[style.colourArcane(施法者)]: +25"+Attribute.DAMAGE_ICE.getColouredName("b"),
					"[style.colourArcane(施法者)]: +10"+Attribute.RESISTANCE_ICE.getColouredName("b"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_WATER_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(与“水波仆佣”互斥！)]";
			} else {
				return "[style.boldBad(与“水波仆佣”互斥！)]";
			}
		}
	},
	
	// Air:

	POISON_VAPOURS_1(true,
			2,
			SpellSchool.AIR,
			"poison_vapours_choking_haze",
			"窒息雾霾",
			"毒云术的气团更加浓厚，变成令人窒息的雾霾，使目标的攻击偶尔会未命中。",
			null, Util.newArrayListOfValues(
					"毒云术会额外<b>-5</b>"+Attribute.ENERGY_SHIELDING.getColouredName("b"))),
	POISON_VAPOURS_2(5,
			SpellSchool.AIR,
			"poison_vapours_arcane_sickness",
			"奥术疾疫",
			"毒云术被注入了强大的奥术疾疫，会持续消耗目标的奥术灵气。",
			null, Util.newArrayListOfValues(
					"毒云术每回合额外消耗目标<b>10</b>"+Attribute.MANA_MAXIMUM.getColouredName("b"))),
	POISON_VAPOURS_3(2,
			SpellSchool.AIR,
			"poison_vapours_weakening_cloud",
			"弱化之云",
			"毒云术开始渗入目标体内，显著削弱了他们的攻击能力。",
			null, Util.newArrayListOfValues(
					"毒云术会额外<b>-15</b>"+Attribute.DAMAGE_PHYSICAL.getColouredName("b"),
					"毒云术会额外<b>-25</b>"+Attribute.CRITICAL_DAMAGE.getColouredName("b"))),

	VACUUM_1(true,
			5,
			SpellSchool.AIR,
			"vacuum_secondary_voids",
			"次生虚空",
			"真空术力量增大，导致目标踉踉跄跄，难以发动攻击。",
			null,
			Util.newArrayListOfValues(
					"真空术现在会",
					"<b>-15</b>"+Attribute.CRITICAL_DAMAGE.getColouredName("b"),
					"<b>-10</b>"+Attribute.ENERGY_SHIELDING.getColouredName("b"))),
	VACUUM_2(2,
			SpellSchool.AIR,
			"vacuum_suction",
			"抽吸",
			"真空术现在非常强力，有概率将目标的随机外层衣物吸到地上。<br/>"
				+"[style.italicsMinorBad(脱衣服只会影响非特殊NPC。)]",
			null,
			Util.newArrayListOfValues(
					"真空术现在会",
					"<b>-25</b>"+Attribute.CRITICAL_DAMAGE.getColouredName("b"),
					"<b>-20</b>"+Attribute.ENERGY_SHIELDING.getColouredName("b"),
					"每回合有<b>10%</b>的概率[style.boldExcellent(脱掉)]衣物")),
	VACUUM_3(4,
			SpellSchool.AIR,
			"vacuum_total_void",
			"完全真空",
			"真空术具有压倒性的力量，现在有很大概率将目标的随机外层衣物吸到地上。<br/>"
				+"[style.italicsMinorBad(脱衣服只会影响非特殊NPC。)]",
			null, Util.newArrayListOfValues(
					"真空术现在会",
					"<b>-25</b>"+Attribute.CRITICAL_DAMAGE.getColouredName("b"),
					"<b>-20</b>"+Attribute.ENERGY_SHIELDING.getColouredName("b"),
					"抽吸的脱衣概率[style.boldExcellent(提高至)]<b>25%</b>")),

	PROTECTIVE_GUSTS_1(true,
			1,
			SpellSchool.AIR,
			"protective_gusts_guiding_wind",
			"指引之风",
			"在奥术力量的引导下，被召唤的风会在关键时刻对目标施加气压，帮助目标避开来袭的攻击，或是发动更强大的攻击。",
			null, Util.newArrayListOfValues(
					"护体之风的增益提高至<b>+2</b>"+Attribute.ENERGY_SHIELDING.getColouredName("b"),
					"护体之风会额外<b>+10</b>"+Attribute.DAMAGE_PHYSICAL.getColouredName("b"))),
	PROTECTIVE_GUSTS_2(1,
			SpellSchool.AIR,
			"protective_gusts_focused_blast",
			"气浪聚合",
			"每当目标攻击时，奥术之风都会向前推进，帮助目标尽可能多地造成伤害。",
			null, Util.newArrayListOfValues(
					"护体之风的增益提高至<b>+3</b>"+Attribute.ENERGY_SHIELDING.getColouredName("b"),
					"护体之风会额外<b>+25</b>"+Attribute.CRITICAL_DAMAGE.getColouredName("b"))),
	PROTECTIVE_GUSTS_3(2,
			SpellSchool.AIR,
			"protective_gusts_lingering_presence",
			"不息之风",
			"护体之风把能量节约给最需要的时刻，这使得它能在更长时间内辅助目标。",
			null, Util.newArrayListOfValues(
					"护体之风现在持续[style.boldGood(5回合)]")),

	ELEMENTAL_AIR_1(true,
			5,
			SpellSchool.AIR,
			"elemental_air_whirlwind",
			"旋风",
			"风元素向前召唤出一股涡流旋风，扰乱并摇晃所有敌人。",
			null, Util.newArrayListOfValues(
					"[style.colourTerrible(所有敌人遭受)]-5"+Attribute.ENERGY_SHIELDING.getColouredName("b"))),
	ELEMENTAL_AIR_2(5,
			SpellSchool.AIR,
			"elemental_air_vitalising_scents",
			"活力香氛",
			"风元素用活力香氛包围着盟友，为他们注入所需的能量，以躲避来袭的攻击，或是发动更强大的攻击。",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(所有盟友获得)]+10"+Attribute.DAMAGE_PHYSICAL.getColouredName("b"),
					"[style.colourExcellent(所有盟友获得)]+5"+Attribute.ENERGY_SHIELDING.getColouredName("b"))),
	ELEMENTAL_AIR_3A(5,
			SpellSchool.AIR,
			"elemental_air_servant_of_air",
			"气旋仆佣",
			"召唤者向大气学派宣誓效忠，当元素体被绑定至此形态时，能够随意从召唤者处获取能量。",
			null, Util.newArrayListOfValues(
					"被召唤时:",
					"[style.colourAir(元素)]: +100%[style.colourExcellent(非魅惑伤害)]",
					"[style.colourArcane(施法者)]: -50%[style.colourHealth(最大"+Attribute.HEALTH_MAXIMUM.getName()+")]")) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_AIR_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(与“气旋束缚”互斥！)]";
			} else {
				return "[style.boldBad(与“气旋束缚”互斥！)]";
			}
		}
	},
	ELEMENTAL_AIR_3B(10,
			SpellSchool.AIR,
			"elemental_air_binding_of_air",
			"气旋束缚",
			"召唤者对大气学派拥有绝对统治，当元素体被绑定至此形态时，能够强制其分享深藏的秘密。",
			null, Util.newArrayListOfValues(
					"被召唤时:",
					"[style.colourArcane(施法者)]: +25"+Attribute.DAMAGE_POISON.getColouredName("b"),
					"[style.colourArcane(施法者)]: +10"+Attribute.RESISTANCE_POISON.getColouredName("b"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_AIR_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(与“气旋仆佣”互斥！)]";
			} else {
				return "[style.boldBad(与“气旋仆佣”互斥！)]";
			}
		}
	},

	// Earth:

	SLAM_1(true,
			2,
			SpellSchool.EARTH,
			"slam_ground_shake",
			"震地",
			"重击术造成伤害后继续向下撞击地面，导致目标脚下的地面震动。",
			null, Util.newArrayListOfValues(
					"<b>-10</b>"+Attribute.ENERGY_SHIELDING.getColouredName("b")+"，持续[style.colourGood(2回合)]")),
	SLAM_2(2,
			SpellSchool.EARTH,
			"slam_aftershock",
			"余波",
			"就在震感开始减弱之际，一股强大的力量波从地面上升而出，击中目标。",
			null, Util.newArrayListOfValues(
					"在震地结束后造成<b>5</b>"+Attribute.DAMAGE_PHYSICAL.getColouredName("b"))),
	SLAM_3(5,
			SpellSchool.EARTH,
			"slam_earthquake",
			"地震",
			"猛击对地面的冲击之大，令所有敌人都遭受了“震地”的效应。",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(所有敌人)]遭受“震地”的影响")),

	TELEKENETIC_SHOWER_1(true,
			5,
			SpellSchool.EARTH,
			"telekinetic_shower_mind_over_matter",
			"物随心动",
			"施法者使用念力来抓取和回收已被投掷的物体，使得念力之雨可以持续相当长的时间。",
			null, Util.newArrayListOfValues(
					"念力之雨现在持续[style.boldGood(6回合)]")),
	TELEKENETIC_SHOWER_2(1,
			SpellSchool.EARTH,
			"telekinetic_shower_precision_strikes",
			"精确打击",
			"念力之雨的每次攻击都能精准地绕过并削弱目标的物理防御。",
			null, Util.newArrayListOfValues(
					"念力之雨会额外<b>-20</b>"+Attribute.RESISTANCE_PHYSICAL.getColouredName("b"))),
	TELEKENETIC_SHOWER_3(10,
			SpellSchool.EARTH,
			"telekinetic_shower_unseen_force",
			"无形之力",
			"每当念力之雨的投掷物撞击目标时，就会释放出爆炸性的力量，造成可观的伤害。",
			null, Util.newArrayListOfValues(
					"念力之雨的伤害[style.colourExcellent(翻倍)]至每回合<b>50</b>"+Attribute.DAMAGE_PHYSICAL.getColouredName("b"))),

	STONE_SHELL_1(true,
			1,
			SpellSchool.EARTH,
			"stone_shell_shifting_sands",
			"流沙",
			"磐石之壳有时会分解变换成流沙，然后在其他位置迅速重塑以迷惑敌人。",
			null, Util.newArrayListOfValues(
					"磐石之壳会额外<b>+2</b>"+Attribute.ENERGY_SHIELDING.getColouredName("b"))),
	STONE_SHELL_2(2,
			SpellSchool.EARTH,
			"stone_shell_hardened_carapace",
			"外壳硬化",
			"磐石之壳内部出现了第二层硬化的石头，大幅增加了目标的物理护盾。",
			null, Util.newArrayListOfValues(
					"磐石之壳的增益提高至<b>+10</b>"+Attribute.RESISTANCE_PHYSICAL.getColouredName("b"))),
	STONE_SHELL_3(1,
			SpellSchool.EARTH,
			"stone_shell_explosive_finish",
			"爆破终结",
			"磐石之壳内储藏着念力，当效果最终结束时，这股能量会以爆破冲击的形式释放出来。",
			null, Util.newArrayListOfValues(
					"磐石之壳效果结束时[style.colourExcellent(所有敌人)]受到<b>10</b>"+Attribute.DAMAGE_PHYSICAL.getColouredName("b"))),

	ELEMENTAL_EARTH_1(true,
			5,
			SpellSchool.EARTH,
			"elemental_earth_rolling_stone",
			"滚石",
			"土元素发出力量波来提高其盟友造成的伤害。",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(所有盟友获得)]+15"+Attribute.DAMAGE_PHYSICAL.getColouredName("b"),
					"[style.colourExcellent(所有盟友获得)]+25"+Attribute.CRITICAL_DAMAGE.getColouredName("b"))),
	ELEMENTAL_EARTH_2(5,
			SpellSchool.EARTH,
			"elemental_earth_hardening",
			"硬化",
			"土元素用念力控制岩石碎片环绕所有盟友，以此提供保护。",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(所有盟友获得)]+10"+Attribute.RESISTANCE_PHYSICAL.getColouredName("b"))),
	ELEMENTAL_EARTH_3A(5,
			SpellSchool.EARTH,
			"elemental_earth_servant_of_earth",
			"沙土仆佣",
			"召唤者向大地学派宣誓效忠，当元素体被绑定至此形态时，能够随意从召唤者处获取能量。",
			null, Util.newArrayListOfValues(
					"被召唤时:",
					"[style.colourEarth(元素)]: +100%[style.colourExcellent(非魅惑伤害)]",
					"[style.colourArcane(施法者)]: -50%[style.colourHealth(最大"+Attribute.HEALTH_MAXIMUM.getName()+")]")) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_EARTH_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(与“沙土束缚”互斥！)]";
			} else {
				return "[style.boldBad(与“沙土束缚”互斥！)]";
			}
		}
	},
	ELEMENTAL_EARTH_3B(10,
			SpellSchool.EARTH,
			"elemental_earth_binding_of_earth",
			"沙土束缚",
			"召唤者对大地学派拥有绝对统治，当元素体被绑定至此形态时，能够强制其分享深藏的秘密。",
			null, Util.newArrayListOfValues(
					"被召唤时:",
					"[style.colourArcane(施法者)]: +25"+Attribute.DAMAGE_PHYSICAL.getColouredName("b"),
					"[style.colourArcane(施法者)]: +10"+Attribute.RESISTANCE_PHYSICAL.getColouredName("b"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_EARTH_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(与“沙土仆佣”互斥！)]";
			} else {
				return "[style.boldBad(与“沙土仆佣”互斥！)]";
			}
		}
	},

	// Arcane:

	ARCANE_AROUSAL_1(true,
			5,
			SpellSchool.ARCANE,
			"arcane_arousal_overwhelming_lust",
			"性欲超载",
			"投射到目标脑海中的图像变得特别淫荡和堕落。",
			null, Util.newArrayListOfValues(
					"奥术狂欲的伤害[style.colourExcellent(翻倍)]至<b>30</b>"+Attribute.DAMAGE_LUST.getColouredName("b"))),
	ARCANE_AROUSAL_2(2,
			SpellSchool.ARCANE,
			"arcane_arousal_lustful_distraction",
			"情迷欲乱",
			"发送到目标脑海中的淫秽图像会持续一段时间，导致他们分心。",
			null, Util.newArrayListOfValues(
					"目标[style.boldBad(受到)]-15"+Attribute.ENERGY_SHIELDING.getColouredName("b")+"，持续[style.boldGood(2回合)]")),
	ARCANE_AROUSAL_3(2,
			SpellSchool.ARCANE,
			"arcane_arousal_dirty_promises",
			"淫誓欲言",
			"与淫秽图像一同投射的还有幻觉般的低语，它向目标许诺只要顺从，就能享受一段美好的时光……",
			null, Util.newArrayListOfValues(
					"目标[style.boldBad(额外受到)]-25"+Attribute.RESISTANCE_LUST.getColouredName("b"),
					"持续时间增加至[style.boldGood(3回合)]")),

	TELEPATHIC_COMMUNICATION_1(true,
			5,
			SpellSchool.ARCANE,
			"telepathic_communication_echoing_moans",
			"回荡呻吟",
			"心灵低语的效果现在可以持续很长一段时间。另外，施术者总能在出现麻烦的第一时间就使用它。",
			null, Util.newArrayListOfValues(
					"战斗开始时心灵低语就[style.boldExcellent(已经激活)]",
					"持续时间增加至[style.boldGood(10回合)]")),
	TELEPATHIC_COMMUNICATION_2(3,
			SpellSchool.ARCANE,
			"telepathic_communication_projected_touch",
			"触摸投射",
			"心灵低语的施法者现在能投射出幻影般的力量，目标脑海中充满了呻吟，同时也能感受到幽灵般的亲吻。",
			null, Util.newArrayListOfValues(
					"增益效果[style.colourExcellent(翻倍)]至<b>30</b>"+Attribute.DAMAGE_LUST.getColouredName("b"))),
	TELEPATHIC_COMMUNICATION_3(3,
			SpellSchool.ARCANE,
			"telepathic_communication_power_of_suggestion",
			"言语暗示",
			"心灵低语的施法者能够将强有力的暗示话语投射到任何他们想要诱惑的人的脑海中，使他们放松警惕。",
			null, Util.newArrayListOfValues(
					"[style.boldLust(挑逗)]对目标[style.boldExcellent(造成)]-25"+Attribute.RESISTANCE_PHYSICAL.getColouredName("b")+"，持续[style.boldGood(2回合)]")),

	ARCANE_CLOUD_1(true,
			3,
			SpellSchool.ARCANE,
			"arcane_cloud_lightning",
			"奥术雷霆",
			"粉紫交加的奥术闪电从奥术云朵中射出，让目标脑海中充斥着淫秽的想法。",
			null, Util.newArrayListOfValues(
					"每回合额外造成<b>5</b>"+Attribute.DAMAGE_LUST.getColouredName("b"))),
	ARCANE_CLOUD_2(5,
			SpellSchool.ARCANE,
			"arcane_cloud_thunder",
			"奥术雷鸣",
			"目标耳朵里传来阵阵奥术雷鸣，大幅增加了他们受到的性欲伤害。",
			null, Util.newArrayListOfValues(
					Attribute.DAMAGE_LUST.getColouredName("b")+"[style.boldExcellent(增加至)]每回合<b>15</b>")),
	ARCANE_CLOUD_3(10,
			SpellSchool.ARCANE,
			"arcane_cloud_localised_storm",
			"地域风暴",
			"奥术之云成长为地域风暴，会对所有敌人造成性欲伤害。",
			null, Util.newArrayListOfValues(
					"[style.boldExcellent(所有敌人)]每回合受到<b>15</b>"+Attribute.DAMAGE_LUST.getColouredName("b"))),

	CLEANSE_1(true,
			5,
			SpellSchool.ARCANE,
			"cleanse_selective_cleanse",
			"选择性清洁",
			"净化能量现在能够分辨持续状态效果的种类，它能只去除盟友的负面效果和敌人的正面效果。",
			null, Util.newArrayListOfValues(
					"只会去除[style.boldExcellent(盟友)]的[style.boldTerrible(负面)]效果",
					"只会去除[style.boldTerrible(敌人)]的[style.boldExcellent(正面)]效果")),
	CLEANSE_2(5,
			SpellSchool.ARCANE,
			"cleanse_arcane_duality",
			"奥术二元",
			"闪烁的奥术能量之盾被召唤到净化术的目标周围。这种能量能够检测目标是盟友还是敌人，并以此选择保护还是弱化他们。",
			null, Util.newArrayListOfValues(
					"+/-5"+Attribute.RESISTANCE_PHYSICAL.getColouredName("b"),
					"+/-5"+Attribute.RESISTANCE_LUST.getColouredName("b"),
					"+/-5"+Attribute.RESISTANCE_FIRE.getColouredName("b"),
					"+/-5"+Attribute.RESISTANCE_ICE.getColouredName("b"),
					"+/-5"+Attribute.RESISTANCE_POISON.getColouredName("b"),
					"持续[style.boldGood(3回合)]")),
	CLEANSE_3(2,
			SpellSchool.ARCANE,
			"cleanse_arcane_will",
			"奥术意志",
			"施法者钢铁般的意志投射到奥术二元的效果中，显著增加了护盾的持续时间。",
			null, Util.newArrayListOfValues(
					"奥术二元的持续时间增加至[style.boldGood(6回合)]")),

	STEAL_1(true,
			5,
			SpellSchool.ARCANE,
			"steal_stripper",
			"脱衣舞者",
			"窃取术的力量增加，可以剥去目标当前所穿的衣物。",
			null, Util.newArrayListOfValues(
					"窃取术可以[style.boldExcellent(进一步)]以外层衣物为目标")),
	STEAL_2(10,
			SpellSchool.ARCANE,
			"steal_disarm",
			"武装解除",
			"窃取术现在足够强大，可以将目标手中的武器传送走。",
			null, Util.newArrayListOfValues(
					"窃取术可以[style.boldExcellent(进一步)]以装备的武器为目标")),
	STEAL_3A(5,
			SpellSchool.ARCANE,
			"steal_deep_reach",
			"触及至深",
			"窃取术的力量再次增加，即使施法者看不到，也可以把任意衣物作为目标。",
			null, Util.newArrayListOfValues(
					"窃取术可以[style.boldExcellent(进一步)]以任意衣物为目标")),
	STEAL_3B(10,
			SpellSchool.ARCANE,
			"steal_panty_snatcher",
			"内裤强盗",
			"施法者精炼并完善了这个咒语，它总是会优先偷取目标的内裤。",
			null, Util.newArrayListOfValues(
					"窃取术[style.boldExcellent(优先)]以腹股沟栏位的装备为目标")),

	TELEPORT_1(true,
			10,
			SpellSchool.ARCANE,
			"teleport_arcane_arrival",
			"奥术抵达",
			"传送抵达时，传送术的目标会释放出一股催情的奥术能量。",
			null, Util.newArrayListOfValues(
					"对随机敌人造成<b>5</b>"+Attribute.DAMAGE_LUST.getColouredName("b"))),
	TELEPORT_2(15,
			SpellSchool.ARCANE,
			"teleport_mass_teleportation",
			"大规模传送",
			"施法者现在可以将传送术应用于附近任意多的人。",
			null, Util.newArrayListOfValues(
					"传送术可以将[style.colourExcellent(所有盟友)]视为目标",
					"同伴[style.colourExcellent(不再阻碍)]地图传送")),
	TELEPORT_3(10,
			SpellSchool.ARCANE,
			"teleport_rebounding_teleportation",
			"回溯传送",
			"施法前创造一处奥术锚点，传送的目标能够在数秒后返回原处。",
			null, Util.newArrayListOfValues(
					"传送术的持续时间增加至[style.boldGood(2回合)]",
					"奥术抵达每回合都会造成<b>5</b>"+Attribute.DAMAGE_LUST.getColouredName("b"))),

	LILITHS_COMMAND_1(true,
			10,
			SpellSchool.ARCANE,
			"liliths_command_overpowering_presence",
			"压迫身形",
			"莉莉丝本人压迫感十足的形象将会投射到目标的脑海中，大幅提高法术生效的可能。",
			null, Util.newArrayListOfValues(
					"成功概率[style.colourExcellent(提高至)]50%")),
	LILITHS_COMMAND_2(15,
			SpellSchool.ARCANE,
			"liliths_command_demonic_servants",
			"恶魔仆佣",
			"目标会看到莉莉丝本人命令其臣服的幻象。幻象十分逼真，连恶魔也可能受到该法术效果的影响。",
			null, Util.newArrayListOfValues(
					"莉莉丝之律令现在[style.colourExcellent(能够影响恶魔)]",
					"成功概率[style.colourExcellent(提高至)]75%")),
	LILITHS_COMMAND_3(20,
			SpellSchool.ARCANE,
			"liliths_command_ultimate_power",
			"终极力量",
			"投射到目标脑海中的莉莉丝的幻象强大而逼真，无人可能抵挡其效果。",
			null, Util.newArrayListOfValues(
					"成功概率[style.colourExcellent(提高至)]100%")),

	ELEMENTAL_ARCANE_1(true,
			5,
			SpellSchool.ARCANE,
			"elemental_arcane_lewd_encouragements",
			"淫乱激励",
			"奥术元素将会在所有盟友脑内释放出一段淫乱激励，使其能够做出更加变态、诱人的行为。",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(所有盟友获得)]+15"+Attribute.DAMAGE_LUST.getColouredName("b"))),
	ELEMENTAL_ARCANE_2(5,
			SpellSchool.ARCANE,
			"elemental_arcane_caressing_touch",
			"爱抚之触",
			"奥术元素伸出虚幻的触手，对附近的敌人进行骚扰和抚摸。",
			null, Util.newArrayListOfValues(
					"[style.boldTerrible(所有敌人遭受)]-15"+Attribute.RESISTANCE_LUST.getColouredName("b"))),
	ELEMENTAL_ARCANE_3A(5,
			SpellSchool.ARCANE,
			"elemental_arcane_servant_of_arcane",
			"奥术仆佣",
			"召唤者向奥术学派宣誓效忠，当元素体被绑定至此形态时，能够随意从召唤者处获取能量。",
			null, Util.newArrayListOfValues(
					"被召唤时:",
					"[style.colourArcane(元素)]: +100%[style.colourExcellent(非魅惑伤害)]",
					"[style.colourArcane(施法者)]: -50%[style.colourHealth(最大"+Attribute.HEALTH_MAXIMUM.getName()+")]")) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_ARCANE_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(与“奥术束缚”互斥！)]";
			} else {
				return "[style.boldBad(与“奥术束缚”互斥！)]";
			}
		}
	},
	ELEMENTAL_ARCANE_3B(10,
			SpellSchool.ARCANE,
			"elemental_arcane_binding_of_arcane",
			"奥术束缚",
			"召唤者对奥术学派拥有绝对统治，当元素体被绑定至此形态时，能够强制其分享深藏的秘密。",
			null, Util.newArrayListOfValues(
					"被召唤时:",
					"[style.colourArcane(施法者)]: +25"+Attribute.DAMAGE_LUST.getColouredName("b"),
					"[style.colourArcane(施法者)]: +10"+Attribute.RESISTANCE_LUST.getColouredName("b"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_ARCANE_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(与“奥术仆佣”互斥！)]";
			} else {
				return "[style.boldBad(与“奥术仆佣”互斥！)]";
			}
		}
	};

	
	private boolean isAlwaysAvailable;
	private int pointCost;
	private SpellSchool spellSchool;
	private String name;
	private String description;

	private HashMap<AbstractAttribute, Integer> attributeModifiers;
	private List<String> extraEffects;
	private List<String> modifiersList;
	
	private String SVGString;
	
	private SpellUpgrade(int pointCost,
			SpellSchool spellSchool,
			String pathName,
			String name,
			String description,
			HashMap<AbstractAttribute, Integer> attributeModifiers,
			List<String> extraEffects) {
		this(false, pointCost, spellSchool, pathName, name, description, attributeModifiers, extraEffects);
	}
	
	private SpellUpgrade(boolean isAlwaysAvailable,
			int pointCost,
			SpellSchool spellSchool,
			String pathName,
			String name,
			String description,
			HashMap<AbstractAttribute, Integer> attributeModifiers,
			List<String> extraEffects) {
		
		this.isAlwaysAvailable = isAlwaysAvailable;
		this.pointCost = pointCost;
		this.spellSchool = spellSchool;
		this.name = name;
		this.description = description;
		
		this.attributeModifiers = attributeModifiers;
		this.extraEffects = extraEffects;
		
		modifiersList = new ArrayList<>();
		
		if (attributeModifiers != null) {
			for (Entry<AbstractAttribute, Integer> e : attributeModifiers.entrySet())
				modifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b>"
						+ "<b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
		}
		
		if (extraEffects != null) {
			modifiersList.addAll(extraEffects);
		}
		
		try {
			if(!pathName.isEmpty()) {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/combat/spell/upgrade/" + pathName + ".svg");
				if(is==null) {
					System.err.println("Error! SpellUpgrade icon file does not exist (Trying to read from '"+pathName+"')!");
				}
				SVGString = Util.inputStreamToString(is);
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAlwaysAvailable() {
		return isAlwaysAvailable;
	}
	
	public int getPointCost() {
		return pointCost;
	}

	public boolean isAvailable(GameCharacter caster) {
		return true;
	}
	
	public String getUnavailableReason(GameCharacter caster) {
		return "";
	}
	
	public SpellSchool getSpellSchool() {
		return spellSchool;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getModifiersAsStringList() {
		return modifiersList;
	}

	public HashMap<AbstractAttribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public List<String> getExtraEffects() {
		return extraEffects;
	}

	public String getSVGString() {
		return SVGString;
	}
	
}
