package com.lilithsthrone.game.combat.spells;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.EffectBenefit;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.effects.TreeEntry;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.DamageVariance;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.moves.CombatMoveType;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.utils.SpellManagement;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public enum Spell {

	// FIRE:
	
	FIREBALL(false,
			SpellSchool.FIRE,
			SpellType.OFFENSIVE,
			DamageType.FIRE,
			false,
			"火球术",
			"fireball",
			"召唤一个奥术火焰球，向目标发射。",
			30,
			DamageVariance.LOW,
			75,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.FIREBALL_1,
					SpellUpgrade.FIREBALL_2,
					SpellUpgrade.FIREBALL_3),
			null, null) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_1)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.LINGERING_FLAMES, 2));
			} else {
				return new HashMap<>();
			}
		}
		
		@Override
		public int getDamage(GameCharacter caster) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_2) && !caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_3)) {
				return 15;
			}
			return 30;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return getFormattedSpellDamageRange(caster, target, enemies, allies);
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			
			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			if(caster.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
	    		cost = Main.combat.getManaBurnStack().get(caster).remove(0);
			}
			
			descriptionSB.setLength(0);

			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"永恒之火啊，我将体内漩涡的封印解除！出来吧，毁灭之焰！",
													"从火焰的面纱之外，我听到了奥术的召唤！现在，通过我，地狱降临！",
													"黑暗的虚空啊，我命令你粉碎古老的封印，让地狱之火永世不熄！去吧，红莲怒火！"),
											"你在[pc.arm]周围召唤出一个奥术火焰漩涡，将其原始力量集中到一团熊熊燃烧的火球，然后向自己发射！",
											"你在[pc.arm]周围召唤出一个奥术火焰漩涡，将其原始力量集中到一团熊熊燃烧的火球，然后向[npc.Name]发射！",
											"",
											"[npc.she]在[npc.arm]周围召唤出一个奥术火焰漩涡，将其原始力量集中到一团熊熊燃烧的火球，然后向你发射！",
											"[npc1.her]在[npc1.arm]周围召唤出一个奥术火焰漩涡，将其原始力量集中到一团熊熊燃烧的火球，然后向[npc2.name]发射！")
								);
			if(caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_2)) {
				descriptionSB.append("刚发射，火球便立刻一分为二！");
			}
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(applyDamage(caster, target, damage));

				if(caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_1)) {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
				
				// Second fireball:
				if(caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_2)) {
					damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
					GameCharacter secondaryTarget = Main.combat.getRandomAlliedPartyMember(target);
					
					if(secondaryTarget.equals(target)) {
						descriptionSB.append("<br/>"
								+"第二枚火球转向，对"+UtilText.parse(target,"[npc.name]")+"二次连击！");
						
						descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
						descriptionSB.append(applyDamage(caster, target, damage));
						
					} else {
						descriptionSB.append("<br/>"
								+"第二枚火球忽然转向，击中了"+(secondaryTarget.isPlayer()?"你":UtilText.parse(secondaryTarget,"[npc.name]"))+"！");
						
						descriptionSB.append(getDamageDescription(caster, secondaryTarget, damage, isHit, isCritical));
						descriptionSB.append(applyDamage(caster, secondaryTarget, damage));
						applyStatusEffects(caster, secondaryTarget, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, secondaryTarget, isHit, isCritical));
					}
					
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			
			return descriptionSB.toString();
		}
	},
	
	FLASH(false,
			SpellSchool.FIRE,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.FIRE,
			false,
			"闪光术",
			"flash",
			"产生耀眼的闪光，使目标眼花缭乱。",
			0,
			DamageVariance.LOW,
			50,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.FLASH, 1)),
			Util.newArrayListOfValues(
					SpellUpgrade.FLASH_1,
					SpellUpgrade.FLASH_2,
					SpellUpgrade.FLASH_3),
			null,
			Util.newArrayListOfValues("[style.colourExcellent(眩晕)]目标，使其[style.colourTerrible(-1)][style.colourActionPoints(行动点)]！")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FLASH_1)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.FLASH_1, 1));
			} else {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.FLASH, 1));
			}
		}
		
		@Override
		public int getBaseCost(GameCharacter caster) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FLASH_3)) {
				return 25;
			} else {
				return 50;
			}
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FLASH_1)) {
				return "造成眩晕，[style.colourTerrible(-2)]行动点！";
			}
			return "造成眩晕，[style.colourTerrible(-1)]行动点！";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			
			float cost = getModifiedCost(caster);
			if(caster.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
	    		cost = Main.combat.getManaBurnStack().get(caster).remove(0);
			}
			
			descriptionSB.setLength(0);

			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"沉寂于吾体内的力量啊，历经数千载封印已然苏醒！现在，请见证宇宙诞生时耀眼的怒火吧！",
													"百万星体之光耀仍不及我爆发出的力量！奥术封印解除，将我面前的蝼蚁致盲吧！",
													"烈日之怒火，皎月之凝视，愿苍天见证我的力量！望见世界的尽头吧，绝望吧！"),
											"你一甩手腕，便在你的面前召唤出一道炫目的闪光！",
											"你一甩手腕，便在[npc.namePos]的面前召唤出一道炫目的闪光！",
											"",
											"[npc.Name]一甩手腕，便在[npc.her]的面前召唤出一道炫目的闪光！",
											"[npc1.name]一甩手腕，便在[npc2.namePos]的面前召唤出一道炫目的闪光！")
								);
			if(caster.hasSpellUpgrade(SpellUpgrade.FLASH_2)) {
				descriptionSB.append("另一道光芒从第一道中分出，搜寻着另一个目标！");
			}

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				
				// Second flash:
				if(caster.hasSpellUpgrade(SpellUpgrade.FLASH_2)) {
					GameCharacter secondaryTarget = Main.combat.getRandomAlliedPartyMember(target);
					
					if(secondaryTarget.equals(target)) {
						descriptionSB.append("<br/>"
								+"第二道闪光没能找到其他目标，迅速消散了……");
						
					} else {
						descriptionSB.append("<br/>"
								+"第二道闪光冲向"+UtilText.parse(secondaryTarget,"[npc.namePos]的面前，令其[npc.herHim]也眼花缭乱起来！"));

						descriptionSB.append(getDamageDescription(caster, secondaryTarget, 0, isHit, isCritical));
						applyStatusEffects(caster, secondaryTarget, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, secondaryTarget, isHit, isCritical));
					}
					
				}
				
			} else {
				descriptionSB.append("<p style='text-align:center;'>"
						+ "[style.italicsBad(闪光没能命中！)]"
						+ "</p>");
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			
			return descriptionSB.toString();
		}
	},
	
	CLOAK_OF_FLAMES(false,
			SpellSchool.FIRE,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.FIRE,
			true,
			"火焰斗篷",
			"cloak_of_flames",
			"为目标披上奥术火焰的保护斗篷，使其获得更好的冰火抗性。",
			0,
			DamageVariance.LOW,
			50,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES, 3)),
			Util.newArrayListOfValues(
					SpellUpgrade.CLOAK_OF_FLAMES_1,
					SpellUpgrade.CLOAK_OF_FLAMES_2,
					SpellUpgrade.CLOAK_OF_FLAMES_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_FIRE, 5),
					new Value<>(Attribute.RESISTANCE_ICE, 10)),
			Util.newArrayListOfValues("持续[style.colourGood(3回合)]")) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_3)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES_3, 3));
				
			} else if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_2)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES_2, 3));
				
			} else if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_1)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES_1, 3));
				
			} else {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES, 3));
			}
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "给予目标用于保护的火焰斗篷。";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			if(caster.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
	    		cost = Main.combat.getManaBurnStack().get(caster).remove(0);
			}
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"在我的力量之下，冰川将融，千星失色！啊，群星之怒，我命令汝庇护[npc.master]！",
													"汝需知晓，地狱之火臣服于我的一举一动，我自火焰位面的炽热深渊中，召唤出保护我免受一切伤害的力量！",
													"以火焰与狂怒的力量，我将释放烈日的地狱之火！来吧，奥术的狱火，庇护你的[npc.master]，击退任何胆敢袭来的家伙！"),
											"你[pc.arm]一挥，便在身边召唤出一件用于保护的奥术火焰斗篷！",
											"你[pc.arm]一挥，便在[npc.Name]身边召唤出一件用于保护的奥术火焰斗篷！",
											"[npc.Name][npc.arm]一挥，便在身边召唤出一件用于保护的奥术火焰斗篷！",
											"[npc.Name][npc.arm]一挥，便在你身边召唤出一件用于保护的奥术火焰斗篷！",
											"[npc1.name][npc1.arm]一挥，便在[npc2.name]身边召唤出一件用于保护的奥术火焰斗篷！"));
			
			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			if(isHit) {
				target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES);
				target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES_1);
				target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES_2);
				target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES_3);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_FIRE(false,
			SpellSchool.FIRE,
			SpellType.SUMMON,
			DamageType.FIRE,
			true,
			"元素之火",
			"elemental_fire",
			"将元素体与烈火学派绑定，召唤出实体形态的火元素。",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.ELEMENTAL_FIRE_1,
					SpellUpgrade.ELEMENTAL_FIRE_2,
					SpellUpgrade.ELEMENTAL_FIRE_3A,
					SpellUpgrade.ELEMENTAL_FIRE_3B),
			null, Util.newArrayListOfValues("以[style.colourSchoolFire(火焰)]的形态召唤[style.colourArcane(元素)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name]没有学会该法术，所以无法释放！"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "被俘虏时无法释放法术！"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "战斗中只能以战斗动作释放法术！"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_FIRE?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "只能在中立场景释放法术！");
				
			} else if(owner.getMana()<this.getModifiedCost(owner) && !owner.isSpellSchoolSpecialAbilityUnlocked(SpellSchool.FIRE)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name]需要至少<b>"+this.getModifiedCost(owner)+"</b>[style.boldMana(灵气)]才能释放该法术！"));
				
			} else {
				String description = owner.isPlayer()?"将元素体与烈火学派绑定后召唤！":"让[npc.name]将元素体与烈火学派绑定后召唤！";
				String cost = "将消耗<b>"+this.getModifiedCost(owner)+"</b>[style.boldMana(灵气)]！";
				if(owner.getMana()<this.getModifiedCost(owner)) {
					cost = "将消耗<b>"+Math.round((owner.getMana()-this.getModifiedCost(owner))*-0.25f)+"</b>[style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]！";
				}
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "以火焰的形态召唤元素。";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			if(caster.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
	    		cost = Main.combat.getManaBurnStack().get(caster).remove(0);
			}
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}
			
			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.FIRE);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
									?Util.randomItemFrom(
									Util.newArrayListOfValues(
										"[npc.speech(古老的火焰仪式下，我召唤出地狱与愤怒的化身！回应你[npc.master]的召唤吧，[npc2.name]，经历百万位面的焚烧，服从我的意志！)] ",
										"[npc.speech(愿封印于我体内数千载的力量一朝迸发！通过永恒的链接，我向火焰位面发出召唤，出来吧，[npc2.name]！)] ",
										"[npc.speech(让火焰吞噬一切，愿我体内的狱火迸发而出吧！烈焰与怒火，这是[npc.master]召唤！服从我，现身吧，[npc2.name]！)] "))
									:"")
								+(caster.isPlayer()
									?"随着炫目的闪光和火焰的爆裂声，你将元素体[npc2.name]与烈火学派绑定！"
									:"随着炫目的闪光和火焰的爆裂声，[npc1.name]将元素体[npc2.name]与烈火学派绑定！")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(火焰的古老仪式下，我召唤出地狱与愤怒的化身！回应你[npc.master]的召唤吧，[npc2.name]，经历百万位面的焚烧，服从我的意志！)] ",
											"[npc.speech(愿封印于我体内数千载的力量一朝迸发！通过永恒的链接，我向火焰位面发出召唤，出来吧，[npc2.name]！)] ",
											"[npc.speech(让火焰吞噬一切，愿我体内的狱火迸发而出吧！烈焰与怒火，这是[npc.master]的召唤！服从我，现身吧，[npc2.name]！)] "))
										:"")
								+(caster.isPlayer()
									?"随着炫目的闪光和火焰的爆裂声，你召唤出元素体[npc2.name]，并与烈火学派绑定！"
									:"随着炫目的闪光和火焰的爆裂声，[npc1.name]召唤出元素体[npc2.name]，并与烈火学派绑定！")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			
			return descriptionSB.toString();
		}
	},
	
	// WATER:

	ICE_SHARD(false,
			SpellSchool.WATER,
			SpellType.OFFENSIVE,
			DamageType.ICE,
			false,
			"冰刃术",
			"ice_shard",
			"召唤一枚冰霜裂片，向目标发射。",
			25,
			DamageVariance.LOW,
			35,
			null, Util.newArrayListOfValues(
							SpellUpgrade.ICE_SHARD_1,
							SpellUpgrade.ICE_SHARD_2,
							SpellUpgrade.ICE_SHARD_3), null, null) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_3) && isCritical) {
					return Util.newHashMapOfValues(
							new Value<AbstractStatusEffect, Integer>(StatusEffect.FREEZING_FOG, 3),
							new Value<AbstractStatusEffect, Integer>(StatusEffect.FROZEN, 1));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_1)){
					return Util.newHashMapOfValues(
							new Value<AbstractStatusEffect, Integer>(StatusEffect.FREEZING_FOG, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return getFormattedSpellDamageRange(caster, target, enemies, allies);
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"见证无光虚无的恐怖吧！冰雪的呼唤，雪暴的狂怒，释放吧，我的奥术之力！",
													"我已经挣脱体内的枷锁，我将用力量，冻结整个宇宙！狂风、冰雹、寒冰，听我号令，无畏地冲锋吧！",
													"在冻结的虚空之外，我的力量自此迸发！我将展现这力量，跨越冻结的混沌领域的界限！"),
											"你从空气的水汽中召唤出一道旋转的水涡，将能量集中后便将其冻结，制造出一块冰刃，随后向自己身上投去！",
											"你从空气的水汽中召唤出一道旋转的水涡，将能量集中后便将其冻结，制造出一块冰刃，随后向[npc.Name]身上投去！",
											"",
											"[npc1.name]从空气的水汽中召唤出一道旋转的水涡，将能量集中后便将其冻结，制造出一块冰刃，随后向你身上投去！",
											"[npc1.name]从空气的水汽中召唤出一道旋转的水涡，将能量集中后便将其冻结，制造出一块冰刃，随后向[npc2.name]身上投去！")
								);
			
			if(isHit && isCritical && caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_2)) {
				descriptionSB.append("冰刃破空而过时爆发出一阵冻结的雾气");
				if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_3)) {
					descriptionSB.append("，把周围的一切都冰封在了薄薄的冰层中！");
				} else {
					descriptionSB.append("！");
				}
			}
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(applyDamage(caster, target, damage));
				}

				if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_1)) {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
		
		public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("目标拥有“冻人寒雾”状态效果。");
	    }
		
		//Differs from normal version; spells have special crit requirements.
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return target.hasStatusEffect(StatusEffect.FREEZING_FOG) || Main.combat.getStatusEffectsToApply().get(target).containsKey(StatusEffect.FREEZING_FOG);
		}
	},

	RAIN_CLOUD(false,
			SpellSchool.WATER,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.ICE,
			false,
			"雨云术",
			"rain_cloud",
			"在目标头顶召唤一小团奥术魔法雨云，削弱他们的施法能力。",
			0,
			DamageVariance.LOW,
			33,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.RAIN_CLOUD_1,
					SpellUpgrade.RAIN_CLOUD_2,
					SpellUpgrade.RAIN_CLOUD_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.SPELL_COST_MODIFIER, -25)), Util.newArrayListOfValues("持续[style.colourGood(3回合)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.RAIN_CLOUD_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.RAIN_CLOUD_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.RAIN_CLOUD_DOWNPOUR, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.RAIN_CLOUD_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.RAIN_CLOUD_DEEP_CHILL, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.RAIN_CLOUD, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "在目标头顶召唤一团雨云。";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(//TODO chuuni three from here
													"愿苍穹震怒，洪水席卷大地！我将展现体内的洪荒之力，撕裂天空，将你送入水之坟墓！"),
											"你高挥[pc.arm]，在你自己头顶召唤出一团雨云！",
											"你高挥[pc.arm]，在[npc.namePos]头顶召唤出一团雨云！",
											"",
											"[npc.Name]高挥[npc.arm]，在你头顶召唤出一团雨云！",
											"[npc.Name]高挥[npc.arm]，在[npc2.namePos]头顶召唤出一团雨云！"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_CLOUDBURST);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DEEP_CHILL);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	SOOTHING_WATERS(false,
			SpellSchool.WATER,
			SpellType.DEFENSIVE_HEAL,
			DamageType.ICE,
			true,
			"抚慰之水",
			"soothing_waters",
			"召唤一团注入了安抚奥术的水，恢复饮用者的"+Attribute.HEALTH_MAXIMUM.getName()+"。",
			0,
			DamageVariance.LOW,
			100,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.SOOTHING_WATERS_1_CLEAN,
					SpellUpgrade.SOOTHING_WATERS_2_CLEAN,
					SpellUpgrade.SOOTHING_WATERS_1,
					SpellUpgrade.SOOTHING_WATERS_2,
					SpellUpgrade.SOOTHING_WATERS_3),
			null, Util.newArrayListOfValues("[style.boldGood(恢复)]20%[style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> getSpellUpgradeTree() {
			return Spell.soothingWatersUpgradeTree;
		}
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name]没有学会该法术，所以无法释放！"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "被俘虏时无法释放法术！"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "战斗中只能以战斗动作释放法术！"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_WATER?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "只能在中立场景释放法术！");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name]需要至少<b>"+this.getModifiedCost(owner)+"</b>[style.boldMana(灵气)]才能释放该法术！"));
				
			} else {
				String description = owner.isPlayer()?"释放“抚慰之水”":"让[npc.name]释放“抚慰之水”";
				if(owner==target) {
					description+=" 目标为[npc.herself]！";
				} else {
					description+="目标为[npc2.name]！";
				}
				String cost = "将消耗<b>"+this.getModifiedCost(owner)+"</b>[style.boldMana(灵气)]！";
				return new Value<>(true, UtilText.parse(owner, target, description+"<br/>"+cost));
			}
		}
		@Override
		public int getAPCost() {
			return 3;
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "治愈目标";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"水流、溪水、河川、海洋啊，我呼唤这川流不息的循环！我将力量释放于汝，带来永恒生命的源泉！"),
											"你将[pc.hand]轻轻一挥，便召唤出一颗小球大小的治愈之水，并将其一饮而尽。",
											"你将[pc.hand]轻轻一挥，便召唤出一颗小球大小的治愈之水，送到[npc.Name]身边供其饮用。",
											"[npc.Name]将[npc.hand]轻轻一挥，便召唤出一颗小球大小的治愈之水，并将其一饮而尽。",
											"[npc.Name]将[npc.hand]轻轻一挥，便召唤出一颗小球大小的治愈之水，送到你身边供你饮用。",
											"[npc.Name]将[npc.hand]轻轻一挥，便召唤出一颗小球大小的治愈之水，送到[npc2.name]身边供其饮用。"));

			if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_3) ) {
				descriptionSB.append("从原本的球体中分裂出了一颗更小的水球！");
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_3)) {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));

					descriptionSB.append("<br/>"
								+ "水球恢复了"+UtilText.parse(target,"[npc.name]")
									+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f)+Attribute.HEALTH_MAXIMUM.getColouredName("b")+"和"
									+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f)+Attribute.MANA_MAXIMUM.getColouredName("b")+"！");
					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f));
					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f);
					
//					descriptionSB.append("<br/>"
//											+ UtilText.parse(target, "One of the small orbs circles around to heal [npc.name] for a second time, restoring a total of "
//																		+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.5f)+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and "
//																		+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.3f)+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!"));
//					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.5f));
//					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.3f);
					
					if(Main.game.isInCombat()) {
						List<GameCharacter> alliesPlusCaster = new ArrayList<>(Main.combat.getAllies(caster));
						alliesPlusCaster.add(caster);
						for(GameCharacter combatant : alliesPlusCaster) {
							descriptionSB.append("<br/>"
									+ UtilText.parse(combatant, "较小的水球飞向[npc.name]，恢复了[npc.herHim]"
																+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.1f)+Attribute.HEALTH_MAXIMUM.getColouredName("b")+"和"
																+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.1f)+Attribute.MANA_MAXIMUM.getColouredName("b")+"！"));
							descriptionSB.append(applyDamage(caster, combatant, -combatant.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.1f));
							combatant.incrementMana(combatant.getAttributeValue(Attribute.MANA_MAXIMUM)*0.1f);
							if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_1_CLEAN)) {
								descriptionSB.append(UtilText.parse(combatant, "<br/>[npc.NamePos]的身体和穿着的衣物都借助法术[style.colourAqua(清理干净)]了！"));
								combatant.cleanAllClothing(false, false);
								combatant.cleanAllDirtySlots(true);
							}
							if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_2_CLEAN)) {
								descriptionSB.append(UtilText.parse(combatant, "<br/>[npc.NamePos]的身体借助法术[style.colourAqua(完全清理)]了！"));
								combatant.drainTotalFluidsStored(SexAreaOrifice.ANUS, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.VAGINA, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.NIPPLE, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.NIPPLE_CROTCH, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.URETHRA_PENIS, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, 250);
							}
						}
					}
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_2)) {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
					descriptionSB.append("<br/>"
								+ "水球恢复了"+UtilText.parse(target,"[npc.name]")
									+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f)+Attribute.HEALTH_MAXIMUM.getColouredName("b")+"和"
									+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f)+Attribute.MANA_MAXIMUM.getColouredName("b")+"！");
					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f));
					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f);
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_1)) {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
					descriptionSB.append("<br/>"
								+ "水球恢复了"+UtilText.parse(target,"[npc.name]")
									+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f)+Attribute.HEALTH_MAXIMUM.getColouredName("b")+"和"
									+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f)+Attribute.MANA_MAXIMUM.getColouredName("b")+"！");
					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f));
					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f);
					
				} else {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
					descriptionSB.append("<br/>"
								+ "水球恢复了"+UtilText.parse(target,"[npc.name]")
									+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f)+Attribute.HEALTH_MAXIMUM.getColouredName("b")+"！");
					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f));
				}
				if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_1_CLEAN)) {
					descriptionSB.append(UtilText.parse(target, "<br/>[npc.NamePos]的身体和穿着的衣物都借助法术[style.colourAqua(清理干净)]了！"));
					target.cleanAllClothing(false, false);
					target.cleanAllDirtySlots(true);
				}
				if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_2_CLEAN)) {
					descriptionSB.append(UtilText.parse(target, "<br/>[npc.NamePos]的身体借助法术[style.colourAqua(完全清理)]了！"));
					target.drainTotalFluidsStored(SexAreaOrifice.ANUS, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.NIPPLE, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.NIPPLE_CROTCH, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_PENIS, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, 250);
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			
			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_WATER(false,
			SpellSchool.WATER,
			SpellType.SUMMON,
			DamageType.ICE,
			true,
			"元素之水",
			"elemental_water",
			"将元素体与激流学派绑定，召唤出实体形态的水元素。",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.ELEMENTAL_WATER_1,
					SpellUpgrade.ELEMENTAL_WATER_2,
					SpellUpgrade.ELEMENTAL_WATER_3A,
					SpellUpgrade.ELEMENTAL_WATER_3B),
			null, Util.newArrayListOfValues("以[style.colourSchoolWater(水流)]的形态召唤[style.colourArcane(元素)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name]没有学会该法术，所以无法释放！"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "被俘虏时无法释放法术！"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "战斗中只能以战斗动作释放法术！"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_WATER?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "只能在中立场景释放法术！");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name]需要至少<b>"+this.getModifiedCost(owner)+"</b>[style.boldMana(灵气)]才能释放该法术！"));
				
			} else {
				String description = owner.isPlayer()?"将元素体与激流学派绑定后召唤！":"让[npc.name]将元素体与激流学派绑定后召唤！";
				String cost = "将消耗<b>"+this.getModifiedCost(owner)+"</b>[style.boldMana(灵气)]！";
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "以水流的形态召唤元素。";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}

			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.WATER);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(海与天的古老仪式下，我召唤出永恒的激流！回应你[npc.master]的召唤吧，[npc2.name]，经历百万位面的淹没，服从我的意志！)] ",
											"[npc.speech(愿封印于我体内数千载的力量一朝迸发！通过永恒的链接，水流听我号令，出来吧，[npc2.name]！)] ",
											"[npc.speech(让惊涛袭来，愿我体内的海啸迸发而出吧！洪流与暴雨，这是[npc.master]的召唤！服从我，现身吧，[npc2.name]！)] "))
										:"")
								+ (caster.isPlayer()
									?"随着巨大的浪涛声，你将元素体[npc2.name]与激流学派绑定！"
									:"随着巨大的浪涛声，[npc1.name]将元素体[npc2.name]与激流学派绑定！")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(海与天的古老仪式下，我召唤出永恒的激流！回应你[npc.master]的召唤吧，[npc2.name]，经历百万位面的淹没，服从我的意志！)] ",
											"[npc.speech(愿封印于我体内数千载的力量一朝迸发！通过永恒的链接，水流听我号令，出来吧，[npc2.name]！)] ",
											"[npc.speech(让惊涛袭来，愿我体内的海啸迸发而出吧！洪流与暴雨，这是[npc.master]的召唤！服从我，现身吧，[npc2.name]！)] "))
										:"")
								+ (caster.isPlayer()
									?"随着巨大的浪涛声，你召唤出元素体[npc2.name]，并与激流学派绑定！"
									:"随着巨大的浪涛声，[npc1.name]召唤出元素体[npc2.name]，并与激流学派绑定！")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	// AIR:
	
	POISON_VAPOURS(false,
			SpellSchool.AIR,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.POISON,
			false,
			"毒云术",
			"poison_vapours",
			"在目标周围召唤一团有毒气体。",
			0,
			DamageVariance.LOW,
			50,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.POISON_VAPOURS_1,
					SpellUpgrade.POISON_VAPOURS_2,
					SpellUpgrade.POISON_VAPOURS_3),
			null,
			Util.newArrayListOfValues("每回合造成<b>25</b>[style.colourPoison(毒素伤害)]，持续[style.colourGood(3回合)]")) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.POISON_VAPOURS_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.POISON_VAPOURS_WEAKENING_CLOUD, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.POISON_VAPOURS_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.POISON_VAPOURS_ARCANE_SICKNESS, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.POISON_VAPOURS_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.POISON_VAPOURS_CHOKING_HAZE, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.POISON_VAPOURS, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "在目标周围召唤一团毒云";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"打破这封印，让真正的力量释放而出吧！来吧，阴霾与烟雾，让气流听我号令！"),
											"你[pc.arm]一扫，便在自己身边召唤出一团毒性蒸汽云！",
											"你[pc.arm]一扫，便在[npc.Name]身边召唤出一团毒性蒸汽云！",
											"",
											"[npc.her][npc.arm]一扫，便在你身边召唤出一团毒性蒸汽云！",
											"[npc.her][npc.arm]一扫，便在[npc2.name]身边召唤出一团毒性蒸汽云！"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.POISON_VAPOURS_WEAKENING_CLOUD);
				target.removeStatusEffect(StatusEffect.POISON_VAPOURS_ARCANE_SICKNESS);
				target.removeStatusEffect(StatusEffect.POISON_VAPOURS_CHOKING_HAZE);
				target.removeStatusEffect(StatusEffect.POISON_VAPOURS);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	VACUUM(false,
			SpellSchool.AIR,
			SpellType.OFFENSIVE_STATUS_EFFECT_MINOR_DAMAGE,
			DamageType.PHYSICAL,
			false,
			"真空术",
			"vacuum",
			"创造一部分真空环境，先通过吸力造成少量伤害，并在停留在周围，继续阻碍目标的行动。",
			5,
			DamageVariance.LOW,
			60,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.VACUUM_1,
					SpellUpgrade.VACUUM_2,
					SpellUpgrade.VACUUM_3),
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5)),
			Util.newArrayListOfValues("持续[style.colourGood(4回合)]")) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.VACUUM_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VACUUM_TOTAL_VOID, 4));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.VACUUM_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VACUUM_SUCTION, 4));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.VACUUM_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VACUUM_SECONDARY_VOIDS, 4));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VACUUM, 4));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "在目标周围召唤出一部分干扰的真空。";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"比黑色更加深邃的黑暗！我召唤不灭的虚无，穿越时空的裂缝，让我的力量展现出来吧！"),
											"你紧握拳头，在自己身边召唤出一部分真空！",
											"你紧握拳头，在[npc.Name]身边召唤出一部分真空！",
											"",
											"[npc.Name]紧握拳头，在你身边召唤出一部分真空！",
											"[npc.Name]紧握拳头，在[npc2.name]身边召唤出一部分真空！"));

			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(applyDamage(caster, target, damage));

				target.removeStatusEffect(StatusEffect.VACUUM_TOTAL_VOID);
				target.removeStatusEffect(StatusEffect.VACUUM_SUCTION);
				target.removeStatusEffect(StatusEffect.VACUUM_SECONDARY_VOIDS);
				target.removeStatusEffect(StatusEffect.VACUUM);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	PROTECTIVE_GUSTS(false,
			SpellSchool.AIR,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.PHYSICAL,
			true,
			"护体之风",
			"protective_gusts",
			"召唤仁慈之风保护目标，并帮助引导目标攻击。",
			0,
			DamageVariance.LOW,
			50,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.PROTECTIVE_GUSTS_1,
					SpellUpgrade.PROTECTIVE_GUSTS_2,
					SpellUpgrade.PROTECTIVE_GUSTS_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_POISON, 5),
					new Value<>(Attribute.ENERGY_SHIELDING, 1)),
			Util.newArrayListOfValues("持续[style.colourGood(3回合)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS_FOCUSED_BLAST, 5));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS_FOCUSED_BLAST, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS_GUIDING_WIND, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "召唤一道仁慈之风保护目标。";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"大气，服从我的命令！我将释放出真正的力量，狂风与我的意志同一！"),
											"你将[pc.arms]张向两边，召唤出一道仁慈之风保护着你！",
											"你将[pc.arms]张开向两边，召唤出一道仁慈之风保护着[npc.Name]！",
											"[npc.Name]将[npc.arms]张向两边，召唤出一道仁慈之风保护自己！",
											"[npc.Name]将[npc.arms]张开向两边，召唤出一道仁慈之风保护着你！",
											"[npc.Name]将[npc.arms]张开向两边，召唤出一道仁慈之风保护着[npc2.name]！"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS_FOCUSED_BLAST);
				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS_GUIDING_WIND);
				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_AIR(false,
			SpellSchool.AIR,
			SpellType.SUMMON,
			DamageType.PHYSICAL,
			true,
			"元素之风",
			"elemental_air",
			"将元素体与大气学派绑定，召唤出实体形态的风元素。",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.ELEMENTAL_AIR_1,
					SpellUpgrade.ELEMENTAL_AIR_2,
					SpellUpgrade.ELEMENTAL_AIR_3A,
					SpellUpgrade.ELEMENTAL_AIR_3B),
			null, Util.newArrayListOfValues("以[style.colourArcane(元素)]的形态召唤[style.colourSchoolAir(风)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name]没有学会该法术，所以无法释放！"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "被俘虏时无法释放法术！"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "战斗中只能以战斗动作释放法术！"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_AIR?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "只能在中立场景释放法术！");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name]需要至少<b>"+this.getModifiedCost(owner)+"</b>[style.boldMana(灵气)]才能释放该法术！"));
				
			} else {
				String description = owner.isPlayer()?"将元素体与大气学派绑定后召唤！":"让[npc.name]将元素体与大气学派绑定后召唤！";
				String cost = "将消耗<b>"+this.getModifiedCost(owner)+"</b>[style.boldMana(灵气)]！";
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "以大气的形态召唤元素。";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}

			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.AIR);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(狂风的古老仪式下，我召唤出那台风！回应你[npc.master]的召唤吧，[npc2.name]，经历百万位面的呼啸，服从我的意志！)] ",
											"[npc.speech(愿封印于我体内数千载的力量一朝迸发！通过永恒的链接，我向大气发出召唤，出来吧，[npc2.name]！)] ",
											"[npc.speech(让狂风袭来，愿我体内的飓风迸发而出吧！风暴与混沌，这是[npc.master]的召唤！服从我，现身吧，[npc2.name]！)] "))
										:"")
								+ (caster.isPlayer()
									?"随着震撼的风声，你将元素体[npc2.name]与大气学派绑定！"
									:"随着震撼的风声，[npc1.name]将元素体[npc2.name]与大气学派绑定！")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(狂风的古老仪式下，我召唤出那台风！回应你[npc.master]的召唤吧，[npc2.name]，经历百万位面的呼啸，服从我的意志！)] ",
											"[npc.speech(愿封印于我体内数千载的力量一朝迸发！通过永恒的链接，我向大气发出召唤，出来吧，[npc2.name]！)] ",
											"[npc.speech(让狂风袭来，愿我体内的飓风迸发而出吧！风暴与混沌，这是[npc.master]的召唤！服从我，现身吧，[npc2.name]！)] "))
										:"")
								+ (caster.isPlayer()
									?"随着震撼的风声，你召唤出元素体[npc2.name]，并与大气学派绑定！"
									:"随着震撼的风声，[npc1.name]召唤出元素体[npc2.name]，并与大气学派绑定！")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	// EARTH:
	
	SLAM(false,
			SpellSchool.EARTH,
			SpellType.OFFENSIVE,
			DamageType.PHYSICAL,
			false,
			"重击术",
			"slam",
			"召唤一股碾压的力量波，猛击目标。",
			40,
			DamageVariance.LOW,
			60,
			null, Util.newArrayListOfValues(
							SpellUpgrade.SLAM_1,
							SpellUpgrade.SLAM_2,
							SpellUpgrade.SLAM_3), null, null) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.SLAM_AFTER_SHOCK, 2));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.SLAM_GROUND_SHAKE, 2));
					
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return getFormattedSpellDamageRange(caster, target, enemies, allies);
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"山峦震颤，大地纵裂！我将释放力量，无穷之力将任我使役，令你灰飞烟灭！"),
											"你重重地向下一挥，便召唤出一道纯粹的力波，砸向了自己！",
											"你重重地向下一挥，便召唤出一道纯粹的力波，砸向了[npc.Name]！",
											"",
											"[npc.Name]重重地向下一挥，便召唤出一道纯粹的力波，砸向了你！",
											"[npc.Name]重重地向下一挥，便召唤出一道纯粹的力波，砸向了[npc2.name]！")
								);
			
			if(isHit) {
				if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_3)) {
					descriptionSB.append("力随后击向地面，造成了剧烈的地震！");
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_1)) {
					descriptionSB.append("力随后击向地面，造成了明显的震动！");
				}
			}
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(applyDamage(caster, target, damage));
				}

				if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_3)) {
					for(GameCharacter combatant : Main.combat.getEnemies(caster)) {
						applyStatusEffects(caster, combatant, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
					}
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_1)) {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	TELEKENETIC_SHOWER(false,
			SpellSchool.EARTH,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.PHYSICAL,
			false,
			"念力之雨",
			"telekinetic_shower",
			"将周围的小物体举起至空中，一齐向目标砸去。",
			0,
			DamageVariance.LOW,
			125,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.TELEKENETIC_SHOWER_1,
					SpellUpgrade.TELEKENETIC_SHOWER_2,
					SpellUpgrade.TELEKENETIC_SHOWER_3),
			null, Util.newArrayListOfValues("每回合造成<b>25</b>[style.colourPhysical(物理伤害)]，持续[style.colourGood(3回合)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEKENETIC_SHOWER_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER_UNSEEN_FORCE, 6));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEKENETIC_SHOWER_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER_PRECISION_STRIKES, 6));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEKENETIC_SHOWER_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER, 6));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "向目标发射出一些小物体，并干扰对方。";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);

			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"宇宙星辰之力听我号令！在这位面的涡流中，我将扭曲时空的边界！"),
											"你抬起[pc.arms]，身边各种小物件便同时飞起至半空，接着就向你自己砸去！",
											"你抬起[pc.arms]，身边各种小物件便同时飞起至半空，接着就向[npc.Name]砸去！",
											"",
											"[npc.Name]抬起[npc.arms]，身边各种小物件便同时飞起至半空，接着就向你砸来！",
											"[npc.Name]抬起[npc.arms]，身边各种小物件便同时飞起至半空，接着就向[npc2.name]砸去！"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.TELEKENETIC_SHOWER);
				target.removeStatusEffect(StatusEffect.TELEKENETIC_SHOWER_PRECISION_STRIKES);
				target.removeStatusEffect(StatusEffect.TELEKENETIC_SHOWER_UNSEEN_FORCE);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	STONE_SHELL(false,
			SpellSchool.EARTH,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.PHYSICAL,
			true,
			"磐石之壳",
			"stone_shell",
			"在目标周围召唤一层用于保护的磐石。",
			0,
			DamageVariance.LOW,
			25,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.STONE_SHELL_1,
					SpellUpgrade.STONE_SHELL_2,
					SpellUpgrade.STONE_SHELL_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5)),
			Util.newArrayListOfValues("持续[style.colourGood(3回合)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.STONE_SHELL_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.STONE_SHELL_EXPLOSIVE_FINISH, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.STONE_SHELL_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.STONE_SHELL_HARDENED_CARAPACE, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.STONE_SHELL_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.STONE_SHELL_SHIFTING_SANDS, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.STONE_SHELL, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "召唤一道护盾来保护你的目标";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"星辰之力的禁锢已经结束！我将释放力量，大地也将听从[npc.master]的召唤！"),
											"你伸出[pc.hand]向前一甩，便召唤出漂浮的石盾，从接下来的攻击中保护着你！",
											"你伸出[pc.hand]向前一甩，便召唤出漂浮的石盾，从接下来的攻击中保护着[npc.Name]！",
											"[npc.Name]伸出[npc.hand]向前一甩，便召唤出漂浮的石盾，从接下来的攻击中保护着[npc.herHim]！",
											"[npc.Name]伸出[npc.hand]向前一甩，便召唤出漂浮的石盾，从接下来的攻击中保护着你！",
											"[npc.Name]伸出[npc.hand]向前一甩，便召唤出漂浮的石盾，从接下来的攻击中保护着[npc2.name]！"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.removeStatusEffectCombat(StatusEffect.STONE_SHELL_EXPLOSIVE_FINISH));
				target.removeStatusEffect(StatusEffect.STONE_SHELL_HARDENED_CARAPACE);
				target.removeStatusEffect(StatusEffect.STONE_SHELL_SHIFTING_SANDS);
				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_EARTH(false,
			SpellSchool.EARTH,
			SpellType.SUMMON,
			DamageType.PHYSICAL,
			false,
			"元素之土",
			"elemental_earth",
			"将元素体与大地学派绑定，召唤出实体形态的土元素。",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.ELEMENTAL_EARTH_1,
					SpellUpgrade.ELEMENTAL_EARTH_2,
					SpellUpgrade.ELEMENTAL_EARTH_3A,
					SpellUpgrade.ELEMENTAL_EARTH_3B),
			null, Util.newArrayListOfValues("以[style.colourArcane(元素)]的形态召唤[style.colourSchoolAir(土)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name]没有学会该法术，所以无法释放！"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "被俘虏时无法释放法术！"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "战斗中只能以战斗动作释放法术！"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_EARTH?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "只能在中立场景释放法术！");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name]需要至少<b>"+this.getModifiedCost(owner)+"</b>[style.boldMana(灵气)]才能释放该法术！"));
				
			} else {
				String description = owner.isPlayer()?"将元素体与大地学派绑定后召唤！":"让[npc.name]将元素体与大地学派绑定后召唤！";
				String cost = "将消耗<b>"+this.getModifiedCost(owner)+"</b>[style.boldMana(灵气)]！";
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "以大地的形态召唤元素。";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
//				System.out.println(caster.getName());
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}

			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.EARTH);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(砂石的古老仪式下，我召唤出那地震！回应你[npc.master]的召唤吧，[npc2.name]，经历百万位面的冲撞，服从我的意志！)] ",
											"[npc.speech(愿封印于我体内数千载的力量一朝迸发！通过永恒的链接，我向大地发出召唤，出来吧，[npc2.name]！)] ",
											"[npc.speech(让地面震动，愿我体内的力量迸发而出吧！巨石与群山，这是[npc.master]的召唤！服从我，现身吧，[npc2.name]！)] "))
										:"")
								+ (caster.isPlayer()
									?"随着岩石与瓦砾的撞击声，你将元素体[npc2.name]与大地学派绑定！"
									:"随着岩石与瓦砾的撞击声，[npc1.name]将元素体[npc2.name]与大地学派绑定！")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(砂石的古老仪式下，我召唤出那地震！回应你[npc.master]的召唤吧，[npc2.name]，经历百万位面的冲撞，服从我的意志！)] ",
											"[npc.speech(愿封印于我体内数千载的力量一朝迸发！通过永恒的链接，我向大地发出召唤，出来吧，[npc2.name]！)] ",
											"[npc.speech(让地面震动，愿我体内的力量迸发而出吧！巨石与群山，这是[npc.master]的召唤！服从我，现身吧，[npc2.name]！)] "))
										:"")
								+ (caster.isPlayer()
									?"随着岩石与瓦砾的撞击声，你召唤出元素体[npc2.name]，并与大地学派绑定！"
									:"随着岩石与瓦砾的撞击声，[npc1.name]召唤出元素体[npc2.name]，并与大地学派绑定！")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	// ARCANE:
	
	ARCANE_AROUSAL(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"奥术狂欲",
			"arcane_arousal",
			"使目标看到一段由奥术构成的激情画面。",
			15,
			DamageVariance.LOW,
			50,
			null, Util.newArrayListOfValues(
							SpellUpgrade.ARCANE_AROUSAL_1,
							SpellUpgrade.ARCANE_AROUSAL_2,
							SpellUpgrade.ARCANE_AROUSAL_3), null, null) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_AROUSAL_DIRTY_PROMISES, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_AROUSAL_LUSTFUL_DISTRACTION, 2));
					
				}
			}
			return new HashMap<>();
		}

		@Override
		public int getDamage(GameCharacter caster) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_1)) {
				return 30;
			}
			return 15;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return getFormattedSpellDamageRange(caster, target, enemies, allies);
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);

			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"以奥术最本质的面貌，我要以莉莉丝的神言加之你身！看哪，这就是胆敢直视我的芸芸众生的命运！"),
											"你集中奥术能量，在自己脑内投射了一副激情的画面。",
											"你集中奥术能量，在[npc.namePos]脑内投射了一副激情的画面。",
											"",
											"[npc.Name]集中奥术能量，在你脑内投射了一副激情的画面！",
											"[npc.Name]集中奥术能量，在[npc2.namePos]脑内投射了一副激情的画面！"));
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(target.incrementLust(damage, true));
				}
				
				if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2)) {
					target.removeStatusEffect(StatusEffect.ARCANE_AROUSAL_DIRTY_PROMISES);
					target.removeStatusEffect(StatusEffect.ARCANE_AROUSAL_LUSTFUL_DISTRACTION);
					
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	TELEPATHIC_COMMUNICATION(false,
			SpellSchool.ARCANE,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.PHYSICAL,
			true,
			"心灵低语",
			"telepathic_communication",
			"施法者向目标的脑海中投射一段魅惑之声。",
			0,
			DamageVariance.LOW,
			75,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.TELEPATHIC_COMMUNICATION_1,
					SpellUpgrade.TELEPATHIC_COMMUNICATION_2,
					SpellUpgrade.TELEPATHIC_COMMUNICATION_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 15)), Util.newArrayListOfValues("持续[style.colourGood(5回合)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 10));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 10));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION, 10));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION, 5));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "直接在目标脑内发出低语。";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"我驾驭着所有位面，而如今将动摇时空的界限！苍穹也当聆听我的声音，并深陷绝望！"),
											"你集中奥术能量，使得自己的思想能够直接投射到他人的脑内！",
											"你集中奥术能量，使得[npc.namePos]的思想能够直接投射到他人的脑内！",
											"[npc.Name]集中奥术能量，使得自己的思想能够直接投射到他人的脑内！",
											"[npc.Name]集中奥术能量，使得你的思想能够直接投射到他人的脑内！",
											"[npc1.name]集中奥术能量，使得[npc2.namePos]的思想能够直接投射到他人的脑内！"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				target.removeStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH);
				target.removeStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION);
				target.removeStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ARCANE_CLOUD(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.LUST,
			false,
			"奥术之云",
			"arcane_cloud",
			"在目标的头顶召唤一股充满奥术的风暴。",
			0,
			DamageVariance.LOW,
			150,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.ARCANE_CLOUD_1,
					SpellUpgrade.ARCANE_CLOUD_2,
					SpellUpgrade.ARCANE_CLOUD_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -25)), Util.newArrayListOfValues("持续[style.colourGood(3回合)]")) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_CLOUD_LOCALISED_STORM, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_CLOUD_ARCANE_THUNDER, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_CLOUD_ARCANE_LIGHTNING, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_CLOUD, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "在目标头顶召唤一团奥术之云。";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"啊，压抑着我无穷力量的黑暗封印，如今已经灰飞烟灭！现身吧，奥术之源，为我的敌人送上最终审判吧！"),
											"你高挥[pc.arm]，在你自己头顶召唤出一团奥术之云！",
											"你高挥[pc.arm]，在[npc.namePos]头顶召唤出一团奥术之云！",
											"",
											"[npc.Name]高挥[npc.arm]，在你头顶召唤出一团奥术之云！",
											"[npc.Name]高挥[npc.arm]，在[npc2.namePos]头顶召唤出一团奥术之云！"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD_LOCALISED_STORM);
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD_ARCANE_THUNDER);
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD_ARCANE_LIGHTNING);
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	CLEANSE(true,
			SpellSchool.ARCANE,
			SpellType.DEFENSIVE_STATUS_EFFECT_CLEAR,
			DamageType.PHYSICAL,
			true,
			"净化术",
			"cleanse",
			"一股奥术能量的波动向目标袭去，移除对方所有的状态效果。",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.CLEANSE_1,
					SpellUpgrade.CLEANSE_2,
					SpellUpgrade.CLEANSE_3),
			null, Util.newArrayListOfValues(
					"从目标盟友或敌人身上[style.colourGood(移除所有)]",
					"战斗状态效果")) {
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "从目标盟友和敌人身上移除所有战斗状态效果。";
		}
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && Main.game.isInCombat()) {
				AbstractStatusEffect effect = StatusEffect.ARCANE_DUALITY_POSITIVE;
				
				if(Main.combat.getEnemies(caster).contains(target)) {
					effect = StatusEffect.ARCANE_DUALITY_NEGATIVE;
				}
				
				
				if(caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(effect, 6));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(effect, 3));
					
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"以莉莉丝之力，我将打破世界的幻象！碎裂吧，时间与空间，我的奥术将主宰一切，现实也将为之扭曲！"),
											"你伸出[pc.hand]向前一甩，便在自己身边召唤出一阵爆炸，充满了净化的奥术能量！",
											"你伸出[pc.hand]向前一甩，便在[npc.Name]身边召唤出一阵爆炸，充满了净化的奥术能量！",
											"[npc.Name]伸出[npc.hand]向前一甩，便在自己身边召唤出一阵爆炸，充满了净化的奥术能量！",
											"[npc.Name]伸出[npc.hand]向前一甩，便在你身边召唤出一阵爆炸，充满了净化的奥术能量！",
											"[npc.Name]伸出[npc.hand]向前一甩，便在[npc2.name]身边召唤出一阵爆炸，充满了净化的奥术能量！")
								);

			descriptionSB.append(UtilText.parse(this.getPreferredTarget(caster, enemies, allies),
					"那股能量随后便喷射向[npc.Name]周围，引发了爆炸！"));
			
			
			// If attack hits, apply damage and effects: TODO
			if (isHit) {
				List<AbstractStatusEffect> effectsToRemove = new ArrayList<>();
				// Remove status effects from ally:
				for(AbstractStatusEffect se : target.getStatusEffects()) {
					if(se.isCombatEffect() && ((se.getBeneficialStatus()==EffectBenefit.BENEFICIAL && !caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_1)) || se.getBeneficialStatus()!=EffectBenefit.BENEFICIAL)) {
						effectsToRemove.add(se);
					}
				}
				for(AbstractStatusEffect se : effectsToRemove) {
					descriptionSB.append(target.removeStatusEffectCombat(se));
				}
				// Remove status effects from enemy:
				effectsToRemove.clear();
				for(AbstractStatusEffect se : this.getPreferredTarget(caster, enemies, allies).getStatusEffects()) {
					if(se.isCombatEffect() && (se.getBeneficialStatus()==EffectBenefit.BENEFICIAL || (se.getBeneficialStatus()!=EffectBenefit.BENEFICIAL && !caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_1)))) {
						effectsToRemove.add(se);
					}
				}
				for(AbstractStatusEffect se : effectsToRemove) {
					descriptionSB.append(this.getPreferredTarget(caster, enemies, allies).removeStatusEffectCombat(se));
				}
				
				descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	STEAL(true,
			SpellSchool.ARCANE,
			SpellType.MISC,
			DamageType.PHYSICAL,
			false,
			"窃取术",
			"steal",
			"传送术的低阶形态，该法术允许施法者从对方的物品栏中偷取一件物品。",
			0,
			DamageVariance.LOW,
			100,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.STEAL_1,
					SpellUpgrade.STEAL_2,
					SpellUpgrade.STEAL_3A,
					SpellUpgrade.STEAL_3B),
			null, Util.newArrayListOfValues("从目标的物品栏中[style.colourExcellent(偷取)]随机物品")) {
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "窃取目标的物品。";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"交织的时空也将听我号令！宇宙位面，服从我的命令吧，仇敌之物将归我所有！"),
											"你伸出[pc.hand]向前一甩，然后攥紧了拳头，便将奥术能量传导出去，偷取了一件你自己的物品……",
											"你伸出[pc.hand]向前一甩，然后攥紧了拳头，便将奥术能量传导出去，偷取了一件[npc.namePos]的物品！",
											"",
											"[npc.Name]伸出[npc.hand]向前一甩，然后攥紧了拳头，便将奥术能量传导出去，偷取了一件你的物品！",
											"[npc.Name]伸出[npc.hand]向前一甩，然后攥紧了拳头，便将奥术能量传导出去，偷取了一件[npc2.namePos]的物品！"));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				boolean stealItem = false;
				boolean mainWeaponSteal = false;
				boolean offhandWeaponSteal = false;
				AbstractClothing clothingToSteal = null;
				
				if(caster.hasSpellUpgrade(SpellUpgrade.STEAL_3B)) {
					clothingToSteal = target.getClothingInSlot(InventorySlot.GROIN);
					if(clothingToSteal != null && !clothingToSteal.isSealed()) {
						target.forceUnequipClothingIntoVoid(caster, clothingToSteal);
						descriptionSB.append("<br/>"
								+getCastDescription(caster, target,
										null,
										"你窃取了自己的"+clothingToSteal.getName()+"……",
										"你窃取了[npc.Name]正穿在身上的"+clothingToSteal.getName()+"，害[npc.sheIs]惊叫一声，[npc.speech(你，你个流氓！)]",
										"",
										"[npc.Name]窃取了你正穿在身上的"+clothingToSteal.getName()+"，你不禁惊叫一声，[pc.speech(你，你个流氓！)]",
										"[npc1.Name]窃取了[npc2.name]正穿在身上的"+clothingToSteal.getName()+"，害[npc2.sheIs]惊叫一声，[npc2.speech(你，你个流氓！)]")
								+ "<br/>");
						clothingToSteal.setName(target.getNameIgnoresPlayerKnowledge() + "'" +clothingToSteal.getName());
						descriptionSB.append(caster.addClothing(clothingToSteal, true));
					}
				}
				
				if(clothingToSteal==null) {
					if(caster.hasSpellUpgrade(SpellUpgrade.STEAL_3A)) {
						List<AbstractClothing> nonSealedClothing = new ArrayList<>();
						for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
							if(!c.isSealed()) {
								nonSealedClothing.add(c);
							}
						}
						if(!nonSealedClothing.isEmpty()) {
							clothingToSteal = nonSealedClothing.get(Util.random.nextInt(nonSealedClothing.size()));
						}
						
					} else if(caster.hasSpellUpgrade(SpellUpgrade.STEAL_1)) {
						List<AbstractClothing> nonSealedOuterClothing = new ArrayList<>();
						for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
							if(!c.isSealed() && target.isAbleToUnequip(c, false, target)) {
								nonSealedOuterClothing.add(c);
							}
						}
						if(!nonSealedOuterClothing.isEmpty()) {
							clothingToSteal = nonSealedOuterClothing.get(Util.random.nextInt(nonSealedOuterClothing.size()));
						}
						
					}
					
					int mainWeaponIndex = 0;
					AbstractWeapon mainWeapon = null;
					int offhandWeaponIndex = 0;
					AbstractWeapon offhandWeapon = null;
					List<Integer> weaponIndexes = new ArrayList<>();
					for(int i=0;i<target.getMainWeaponArray().length; i++) {
						if(target.getMainWeapon(i)!=null) {
							weaponIndexes.add(i);
						}
					}
					if(!weaponIndexes.isEmpty()) {
						mainWeaponIndex = Util.randomItemFrom(weaponIndexes);
						mainWeapon = target.getMainWeapon(mainWeaponIndex);
					}
					weaponIndexes = new ArrayList<>();
					for(int i=0;i<target.getOffhandWeaponArray().length; i++) {
						if(target.getOffhandWeapon(i)!=null) {
							weaponIndexes.add(i);
						}
					}
					if(!weaponIndexes.isEmpty()) {
						offhandWeaponIndex = Util.randomItemFrom(weaponIndexes);
						offhandWeapon = target.getOffhandWeapon(offhandWeaponIndex);
					}
						
					if(caster.hasSpellUpgrade(SpellUpgrade.STEAL_2)) {
						mainWeaponSteal = mainWeapon!=null;
						offhandWeaponSteal = offhandWeapon!=null;
					}
					
					stealItem = target.getInventorySlotsTaken()>0;
					
				
					double rnd = Math.random();
					
					if(mainWeaponSteal && (rnd<0.2 || (!offhandWeaponSteal && !stealItem && clothingToSteal==null))) {
						target.unequipMainWeapon(mainWeaponIndex, true, target.isPlayer());
						descriptionSB.append("<br/>"
								+ getCastDescription(caster, target,
										null,
										"你窃取了自己的"+mainWeapon.getName()+"……",
										"你从[npc.namePos]的[npc.hands]上窃取了"+mainWeapon.getName()+"！",
										"",
										"[npc.Name]从你的[pc.hands]上窃取了"+mainWeapon.getName()+"！",
										"[npc1.name]从[npc2.namePos]的[npc2.hands]上窃取了"+mainWeapon.getName()+"！")
								+ "<br/>"
								+ caster.addWeapon(mainWeapon, true));
						
					} else if(offhandWeaponSteal && (rnd<0.2 || (!stealItem && clothingToSteal==null))) {
						target.unequipOffhandWeapon(offhandWeaponIndex, true, target.isPlayer());
						descriptionSB.append("<br/>"
								+ getCastDescription(caster, target,
										null,
										"你窃取了自己的"+offhandWeapon.getName()+"……",
										"你从[npc.namePos]的[npc.hands]上窃取了"+offhandWeapon.getName()+"！",
										"",
										"[npc.Name]从你的[pc.hands]上窃取了"+offhandWeapon.getName()+"！",
										"[npc1.name]从[npc2.namePos]的[npc2.hands]上窃取了"+offhandWeapon.getName()+"！")
								+ "<br/>"
								+ caster.addWeapon(offhandWeapon, true));
						
					} else if(stealItem && (rnd<0.5 || (clothingToSteal==null))) {
						AbstractItem item = null;
						if(!target.getAllItemsInInventory().isEmpty()) {
							item = Util.randomItemFrom(target.getAllItemsInInventory().keySet());
						}
						AbstractWeapon weapon = null;
						if(!target.getAllWeaponsInInventory().isEmpty()) {
							weapon = Util.randomItemFrom(target.getAllWeaponsInInventory().keySet());
						}
						AbstractClothing clothing = null;
						if(!target.getAllClothingInInventory().isEmpty()) {
							clothing = Util.randomItemFrom(target.getAllClothingInInventory().keySet());
						}
						double itemStealRnd = Math.random();
						if(item!=null && (itemStealRnd<0.33 || (weapon==null && clothing==null))) {
							target.removeItem(item);
							descriptionSB.append("<br/>"
									+ getCastDescription(caster, target,
											null,
											"你窃取了自己的"+item.getName()+"……",
											"你从[npc.namePos]的物品栏窃取了"+item.getName()+"！",
											"",
											"[npc.Name]从你的物品栏窃取了"+item.getName()+"！",
											"[npc1.Name]从[npc2.namePos]的物品栏窃取了"+item.getName()+"！")
									+ "<br/>"
									+ caster.addItem(item, false));
							
						} else if(weapon!=null && (itemStealRnd<0.66 || (clothing==null))) {
							target.removeWeapon(weapon);
							descriptionSB.append("<br/>"
									+ getCastDescription(caster, target,
											null,
											"你窃取了自己的"+weapon.getName()+"……",
											"你从[npc.namePos]的物品栏窃取了"+weapon.getName()+"！",
											"",
											"[npc.Name]从你的物品栏窃取了"+weapon.getName()+"！",
											"[npc1.Name]从[npc2.namePos]的物品栏窃取了"+weapon.getName()+"！")
									+ "<br/>"
									+ caster.addWeapon(weapon, false));
							
						} else {
							target.removeClothing(clothing);
							descriptionSB.append("<br/>"
									+ getCastDescription(caster, target,
											null,
											"你窃取了自己的"+clothing.getName()+"……",
											"你从[npc.namePos]的物品栏窃取了"+clothing.getName()+"！",
											"",
											"[npc.Name]从你的物品栏窃取了"+clothing.getName()+"！",
											"[npc1.Name]从[npc2.namePos]的物品栏窃取了"+clothing.getName()+"！")
									+ "<br/>"
									+ caster.addClothing(clothing, false));
						}
						
					} else if(clothingToSteal!=null) {
						target.forceUnequipClothingIntoVoid(caster, clothingToSteal);
						descriptionSB.append("<br/>"
								+ getCastDescription(caster, target,
										null,
										"你窃取了自己的"+clothingToSteal.getName()+"……",
										"你窃取了[npc.Name]正穿在身上的"+clothingToSteal.getName()+"，害[npc.sheIs]惊叫一声！",
										"",
										"[npc.Name]窃取了你正穿在身上的"+clothingToSteal.getName()+"，你不禁惊叫一声！",
										"[npc1.Name]窃取了[npc2.name]正穿在身上的"+clothingToSteal.getName()+"，害[npc2.sheIs]惊叫一声！")
								+ "<br/>");
						clothingToSteal.setName(target.getNameIgnoresPlayerKnowledge() + "'" +clothingToSteal.getName());
						descriptionSB.append(caster.addClothing(clothingToSteal, true));
						
					} else {
						descriptionSB.append("<br/>[style.italicsDisabled(没有东西可偷……)]");
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
		
		@Override
		public Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> getSpellUpgradeTree() {
			return Spell.spellStealUpgradeTree;
		}
	},
	
	TELEPORT(true,
			SpellSchool.ARCANE,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.PHYSICAL,
			true,
			"传送术",
			"teleport",
			"目标将会被传送至敌人背后，闪避概率大大提高。",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.TELEPORT_1,
					SpellUpgrade.TELEPORT_2,
					SpellUpgrade.TELEPORT_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.ENERGY_SHIELDING, 100)), Util.newArrayListOfValues(
					"持续[style.colourGood(1回合)]",
					"[style.colourExcellent(解锁)]地图传送",
					"同伴[style.colourTerrible(会阻碍)]地图传送")) {
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "传送至目标身后。";
		}

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPORT_ARCANE_ARRIVAL, 2));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPORT_ARCANE_ARRIVAL, 1));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPORT, 1));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);

			if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_2) && !allies.isEmpty()) {
				descriptionSB.append(getCastDescription(caster, target,
						Util.newArrayListOfValues(
								"我已然，穿越千百个位面，跨越万亿个位面！距离与时间不过是庞然巨物前微不足道的装饰！"),
						"你用一只[pc.hands]迅速切划过空气，便将自己的盟友都传送到了敌人身后！",
						"你用一只[pc.hands]迅速切划过空气，便将自己的盟友都传送到了敌人身后！",
						"[npc.Name]用一只[npc.hands]迅速切划过空气，便将自己的盟友都传送到了敌人身后！",
						"[npc.Name]用一只[npc.hands]迅速切划过空气，便将自己的盟友都传送到了敌人身后！",
						"[npc.Name]用一只[npc.hands]迅速切划过空气，便将自己的盟友都传送到了敌人身后！"));
				
			} else {
				descriptionSB.append(getCastDescription(caster, target,
						Util.newArrayListOfValues(
								"我已然，穿越千百个位面，跨越万亿个位面！距离与时间不过是庞然巨物前微不足道的装饰！"),
						"你用一只[pc.hands]迅速切划过空气，便将自己传送到了敌人身后！",
						"你用一只[pc.hands]迅速切划过空气，便将[npc.Name]传送到了[npc.her]的敌人身后！",
						"[npc.Name]用一只[npc.hands]迅速切划过空气，便将自己传送到了[npc.her]的敌人身后！",
						"[npc.Name]用一只[npc.hands]迅速切划过空气，便将你传送到了你的敌人身后！",
						"[npc.Name]用一只[npc.hands]迅速切划过空气，便将[npc2.name]传送到了[npc2.her]的敌人身后！"));
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.TELEPORT_ARCANE_ARRIVAL);
				target.removeStatusEffect(StatusEffect.TELEPORT);
				
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_2)) {
					applyStatusEffects(caster, caster, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, caster, isHit, isCritical));
					
					for(GameCharacter combatant : Main.combat.getAllies(caster)) {
						applyStatusEffects(caster, combatant, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
					}
					
				} else {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	LILITHS_COMMAND(true,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.PHYSICAL,
			false,
			"莉莉丝之律令",
			"liliths_command",
			"为施法者的言语注入莉莉丝之力，强迫对手立刻屈服。",
			0,
			DamageVariance.LOW,
			400,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.LILITHS_COMMAND_1,
					SpellUpgrade.LILITHS_COMMAND_2,
					SpellUpgrade.LILITHS_COMMAND_3),
			null,
			Util.newArrayListOfValues("[style.colourGood(25%)]的概率使目标[style.colourExcellent(立刻屈服)]")) {
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "强迫目标屈服。";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"莉莉丝由我现出神力！跨越时间和空间的鸿沟，她的命令永无尽头，你必将服从！"),
											"",
											"你从奥术灵气中攫取了大量的能量，将莉莉丝本人的圣言注入[npc.name]的脑内，命令其屈服。",
											"",
											"[npc.Name]从奥术灵气中攫取了大量的能量，将莉莉丝本人的圣言注入你的脑内，命令你屈服。",
											"[npc1.name]从奥术灵气中攫取了大量的能量，将莉莉丝本人的圣言注入[npc2.namePos]的脑内，命令其屈服。"));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				boolean success = false;
				if(caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_3)) {
					success = true;
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_2)) {
					success = Math.random()<0.75f;
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_1)) {
					success = Math.random()<0.5f && target.isVulnerableToArcaneStorm();
					
				} else {
					success = Math.random()<0.25f && target.isVulnerableToArcaneStorm();
				}
				
				if(success) {
					target.setHealthPercentage(0);
					target.setManaPercentage(0);
					target.setLustNoText(100);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
					if(target.isPlayer()) {
						descriptionSB.append(
								"<br/>"
								+ "你突然意识到自己正直面众恶魔之主。"
								+ "莉莉丝只是一伸手指，向着地面，等你反应过来，自己已经跪在了她面前。"
								+ "你油然而生一股取悦对方的想法，于是抬头看向莉莉丝的眼眸，[pc.moan]着，[pc.speech(求您了，莉莉丝……随意地使用我吧……我是您忠诚的奴隶……)]");
						
					} else {
						descriptionSB.append(UtilText.parse(target,
								"<br/>"
								+ "[npc.Name]受到莉莉丝之律令的压迫，跪了下来。"
										+ "[npc.she]开始抚摸着自己的身子，一边发出[npc.a_moan+]，一边祈求道，[npc.speech(求您了，莉莉丝……随意地使用我吧……我是您忠诚的奴隶……)]"));
					}
					
				} else {
					if(target.isPlayer()) {
						descriptionSB.append(
								"<br/>你连忙晃了晃脑袋，向后一跳，成功抵抗了莉莉丝之律令！");
						
					} else if(target.isVulnerableToArcaneStorm() || !caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_2)) {
						descriptionSB.append(UtilText.parse(target, "<br/>[npc.Name]连忙晃了晃脑袋，向后一跳，成功抵抗了莉莉丝之律令！"));
					} else {
						descriptionSB.append(UtilText.parse(target, "<br/>[npc.Name]咧嘴一笑，嘲讽道，[npc.speech(这种小把戏可吓不倒我这种[npc.a_race]！)]"));
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_ARCANE(false,
			SpellSchool.ARCANE,
			SpellType.SUMMON,
			DamageType.LUST,
			false,
			"元素奥术",
			"elemental_arcane",
			"将元素体与奥术学派绑定，召唤出实体形态的奥术元素。",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.ELEMENTAL_ARCANE_1,
					SpellUpgrade.ELEMENTAL_ARCANE_2,
					SpellUpgrade.ELEMENTAL_ARCANE_3A,
					SpellUpgrade.ELEMENTAL_ARCANE_3B),
			null, Util.newArrayListOfValues("以[style.colourArcane(奥术)]的形态召唤[style.colourArcane(元素)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name]没有学会该法术，所以无法释放！"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "被俘虏时无法释放法术！"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "战斗中只能以战斗动作释放法术！"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_ARCANE?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "只能在中立场景释放法术！");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name]需要至少<b>"+this.getModifiedCost(owner)+"</b>[style.boldMana(灵气)]才能释放该法术！"));
				
			} else {
				String description = owner.isPlayer()?"将元素体与奥术学派绑定后召唤！":"让[npc.name]将元素体与奥术学派绑定后召唤！";
				String cost = "将消耗<b>"+this.getModifiedCost(owner)+"</b>[style.boldMana(灵气)]！";
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "以奥术的形态召唤元素。";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}

			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.ARCANE);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(魔鬼与恶魔的古老仪式下，我召唤出那奥术！回应你[npc.master]的召唤吧，[npc2.name]，经历百万位面的征服，服从我的意志！)] ",
											"[npc.speech(愿封印于我体内数千载的力量一朝迸发！通过永恒的链接，我向奥术本身发出召唤，出来吧，[npc2.name]！)] ",
											"[npc.speech(让奥术为我所用，愿我体内的力量迸发而出吧！奥术的精魂，这是[npc.master]的召唤！服从我，现身吧，[npc2.name]！)] "))
										:"")
								+ (caster.isPlayer()
									?"随着一道紫色的奥术闪电，你将元素体[npc2.name]与奥术学派绑定！"
									:"随着一道紫色的奥术闪电，[npc1.name]将元素体[npc2.name]与奥术学派绑定！")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(魔鬼与恶魔的古老仪式下，我召唤出那奥术！回应你[npc.master]的召唤吧，[npc2.name]，经历百万位面的征服，服从我的意志！)] ",
											"[npc.speech(愿封印于我体内数千载的力量一朝迸发！通过永恒的链接，我向奥术本身发出召唤，出来吧，[npc2.name]！)] ",
											"[npc.speech(让奥术为我所用，愿我体内的力量迸发而出吧！奥术的精魂，这是[npc.master]的召唤！服从我，现身吧，[npc2.name]！)] "))
										:"")
								+ (caster.isPlayer()
									?"随着一道紫色的奥术闪电，你召唤出元素体[npc2.name]，并与奥术学派绑定！"
									:"随着一道紫色的奥术闪电，[npc1.name]召唤出元素体[npc2.name]，并与奥术学派绑定！")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	// FROM WEAPONS:
	
	WITCH_SEAL(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.MISC,
			false,
			"魔女封锁术",
			"spell_witch_seal",
			"再目标身上附加奥术封印，使其失去行动能力。",
			0,
			DamageVariance.NONE,
			80,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.WITCH_SEAL, 1)),
			null,
			null,
			Util.newArrayListOfValues(
					"[style.colourExcellent(封印)]目标，[style.colourTerrible(-3)][style.colourActionPoints(行动点)]！")) {
		
		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "封印使行动点[style.colourTerrible(-3)]！";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			descriptionSB.setLength(0);
			
			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"曾经禁锢我的奥术之力的封印，听我号令！让敌方屈服于绝对服从的黑暗之下吧，自此无所遁逃！"),
										"",
										"你将奥术之力集中在扫把上，召唤出一道强大的封印，把[npc.name]控制在原地！",
										"",
										"[npc.Name]将奥术之力集中在扫把上，召唤出一道强大的封印，把你控制在原地！",
										"[npc1.name]将奥术之力集中在扫把上，召唤出一道强大的封印，把[npc2.name]控制在原地！"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			if(isHit) {
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	WITCH_CHARM(false,
			SpellSchool.ARCANE,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.MISC,
			true,
			"魔女魅惑术",
			"spell_witch_charm",
			"对目标释放奥术附魔，只要有人看向他，便无法抵挡其魅力。",
			0,
			DamageVariance.NONE,
			40,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.WITCH_CHARM, 5)),
			null,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 25)), Util.newArrayListOfValues("持续[style.colourGood(5回合)]")) {
		
		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "增加诱惑伤害。";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			descriptionSB.setLength(0);
			
			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"现实也将由我无穷的力量所扭曲！凝视容颜者心魂俱惑，眼前将是自己内心真正的渴望！"),
										"你将奥术之力集中在扫把上，在自己身上施加了迷人的魅惑！",
										"你将奥术之力集中在扫把上，在[npc.Name]身上施加了迷人的魅惑！",
										"[npc.Name]将奥术之力集中在扫把上，在自己身上施加了迷人的魅惑！",
										"[npc.Name]将奥术之力集中在扫把上，在你身上施加了迷人的魅惑！",
										"[npc.Name]将奥术之力集中在扫把上，在[npc2.name]身上施加了迷人的魅惑！"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			if(isHit) {
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	

	
	DARK_SIREN_SIRENS_CALL(false,
			SpellSchool.AIR,
			SpellType.OFFENSIVE_STATUS_EFFECT_MINOR_DAMAGE,
			DamageType.PHYSICAL,
			false,
			"塞壬的呼唤",
			"dark_siren_sirens_call",
			"发出山崩地裂的尖啸使大地崩坏，裂缝中涌出的毒气会窒息附近的所有敌人。",
			10,
			DamageVariance.NONE,
			200,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.BANEFUL_FISSURE, 10)),
			null,
			null,
			Util.newArrayListOfValues(
					"每回合造成<b>25</b>[style.colourPoison(毒素伤害)]，持续[style.colourGood(10回合)]",
					"影响[style.colourExcellent(所有敌人)]")) {

		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return getFormattedSpellDamageRange(caster, target, enemies, allies);
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			descriptionSB.setLength(0);

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"地底的强者们，听从你们[npc.master]的号令！在时间的尽头开辟一条黑暗的鸿沟，让窒息绝望的剧毒瘴气喷涌而出吧！"),
										"",
										"你集中镰刀中巨大的奥术力量，向[npc.namePos][npc.feet]下方的地面砸去，劈开大地并召唤出毒气！",
										"",
										"[npc.Name]集中镰刀中巨大的奥术力量，向你[pc.feet]下方的地面砸去，劈开大地并召唤出毒气！",
										"[npc.Name]集中镰刀中巨大的奥术力量，向[npc2.namePos][npc2.feet]下方的地面砸去，劈开大地并召唤出毒气！"));

			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(applyDamage(caster, target, damage));
				}
			}
			
			for(GameCharacter combatant : Main.combat.getEnemies(caster)) {
				applyStatusEffects(caster, combatant, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
//			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	

	LIGHTNING_SPHERE_DISCHARGE(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"电能释放",
			"arcane_lightning_sphere_discharge",
			"奥术闪电球从持用者处吸取了少量灵气，爆发出一阵催情的奥术闪电，击中了周围[style.colourBad(包括施法者)]的所有人。",
			10,
			DamageVariance.MEDIUM,
			50,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					"影响[style.colourExcellent(所有敌人)]",
					"影响[style.colourTerrible(施法者)]",
					"影响[style.colourTerrible(所有盟友)]")) {

		@Override
		public int getAPCost() {
			return 1;
		}

		@Override
		public int getCooldown() {
			return 2;
		}
		
		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "造成[style.colourDmgLust("
					+Attack.getMinimumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+"-"
					+Attack.getMaximumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+damageType.getName()
					+ ")]"
					+ "伤害，对[style.colourExcellent(所有敌人)]<i>以及</i>[style.colourTerrible(所有盟友，包括施法者)]生效。";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			descriptionSB.setLength(0);

			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"永恒而广袤的力量啊，被封印成千上万年后，终于得以释放！目睹我唤出的奥术风暴，成为为情欲而狂的傀儡，屈服吧！"),
										"你将部分灵气导入到奥术闪电球中，强制其以电能释放的形式放出一些能量！",
										"你将部分灵气导入到奥术闪电球中，强制其以电能释放的形式放出一些能量！",
										"[npc.Name]将部分灵气导入到奥术闪电球中，强制其以电能释放的形式放出一些能量！",
										"[npc.Name]将部分灵气导入到奥术闪电球中，强制其以电能释放的形式放出一些能量！",
										"[npc.Name]将部分灵气导入到奥术闪电球中，强制其以电能释放的形式放出一些能量！"));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {
				for(GameCharacter combatant : Main.combat.getAllCombatants(true)) {
					float damage = Attack.calculateSpellDamage(caster, combatant, damageType, this.getDamage(caster), damageVariance, isCritical);
					descriptionSB.append(getDamageDescription(caster, combatant, damage, isHit, isCritical));
					if(damage>0) {
						descriptionSB.append(applyDamage(caster, combatant, damage));
					}
					applyStatusEffects(caster, combatant, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
				}
				
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
		
		@Override
	    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("无法暴击。");
	    }

		@Override
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return false;
		}
	},
	
	LIGHTNING_SPHERE_OVERCHARGE(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"电能过载",
			"arcane_lightning_sphere_overcharge",
			"奥术闪电球从持用者处吸取了大量灵气，爆发出一阵狂野的催情奥术闪电，击中了周围[style.colourBad(包括施法者)]的所有人。",
			30,
			DamageVariance.HIGH,
			250,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					"影响[style.colourExcellent(所有敌人)]",
					"影响[style.colourTerrible(施法者)]",
					"影响[style.colourTerrible(所有盟友)]")) {

		@Override
		public int getAPCost() {
			return 3;
		}

		@Override
		public int getCooldown() {
			return 10;
		}
		
		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "造成[style.colourDmgLust("
					+Attack.getMinimumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+"-"
					+Attack.getMaximumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+damageType.getName()
					+ ")]"
					+ "伤害，对[style.colourExcellent(所有敌人)]<i>以及</i>[style.colourTerrible(所有盟友，包括施法者)]生效。";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			descriptionSB.setLength(0);

			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"永恒而广袤的力量啊，被封印成千上万年后，终于得以释放！目睹我唤出的奥术风暴，成为为情欲而狂的傀儡，屈服吧！"),
										"你将部分灵气导入到奥术闪电球中，强制其以强烈电能释放的形式放出能量！",
										"你将部分灵气导入到奥术闪电球中，强制其以强烈电能释放的形式放出能量！",
										"[npc.Name]将部分灵气导入到奥术闪电球中，强制其以强烈电能释放的形式放出能量！",
										"[npc.Name]将部分灵气导入到奥术闪电球中，强制其以强烈电能释放的形式放出能量！",
										"[npc.Name]将部分灵气导入到奥术闪电球中，强制其以强烈电能释放的形式放出能量！"));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {

				for(GameCharacter combatant : Main.combat.getAllCombatants(true)) {
					float damage = Attack.calculateSpellDamage(caster, combatant, damageType, this.getDamage(caster), damageVariance, isCritical);
					descriptionSB.append(getDamageDescription(caster, combatant, damage, isHit, isCritical));
					if(damage>0) {
						descriptionSB.append(applyDamage(caster, combatant, damage));
					}
					applyStatusEffects(caster, combatant, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
				}
				
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
		
		@Override
	    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("无法暴击。");
	    }

		@Override
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return false;
		}
	},
	
	ARCANE_CHAIN_LIGHTNING(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"连锁闪电",
			"arcane_lightning_chain",
			"施法者能召唤出噼啪作响的奥术闪电，在目标间跳跃，使每个被击中的人都无法控制地兴奋起来。",
			15,
			DamageVariance.MEDIUM,
			40,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					"影响[style.colourExcellent(所有敌人)]")) {
		@Override
		public int getAPCost() {
			return 1;
		}
		@Override
		public int getCooldown() {
			return 2;
		}
		@Override
		public boolean isSpellBook() {
			return false;
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "造成[style.colourDmgLust("
					+Attack.getMinimumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+"-"
					+Attack.getMaximumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+damageType.getName()
					+ ")]"
					+ "伤害，对[style.colourExcellent(所有敌人)]生效。";
		}
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			descriptionSB.setLength(0);

			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"永恒而广袤的力量啊，被封印成千上万年后，终于得以释放！目睹我唤出的奥术风暴，成为为情欲而狂的傀儡，屈服吧！"),
													"你稍微将精力集中在灵气上，随后释放出一道噼啪作响的奥术闪电！",
													"你稍微将精力集中在灵气上，随后释放出一道噼啪作响的奥术闪电！",
													"[npc.Name]稍微将精力集中在灵气上，随后释放出一道噼啪作响的奥术闪电！",
													"[npc.Name]稍微将精力集中在灵气上，随后释放出一道噼啪作响的奥术闪电！",
													"[npc.Name]稍微将精力集中在灵气上，随后释放出一道噼啪作响的奥术闪电！"));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {
				for(GameCharacter combatant : Main.combat.getAllCombatants(true)) {
					if(Main.combat.isOpponent(caster, combatant)) {
						float damage = Attack.calculateSpellDamage(caster, combatant, damageType, this.getDamage(caster), damageVariance, isCritical);
						descriptionSB.append(getDamageDescription(caster, combatant, damage, isHit, isCritical));
						if(damage>0) {
							descriptionSB.append(applyDamage(caster, combatant, damage));
						}
						applyStatusEffects(caster, combatant, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
					}
				}
				
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
		@Override
	    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("无法暴击。");
	    }
		@Override
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return false;
		}
	},
	
	ARCANE_LIGHTNING_SUPERBOLT(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"究极闪电",
			"arcane_lightning_superbolt",
			"施法者召唤出强大的奥术闪电，向目标发射，受击者无不高潮绝顶。",
			50,
			DamageVariance.HIGH,
			200,
			null,
			null,
			null,
			Util.newArrayListOfValues()) {
		@Override
		public int getAPCost() {
			return 3;
		}
		@Override
		public int getCooldown() {
			return 10;
		}
		@Override
		public boolean isSpellBook() {
			return false;
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return getFormattedSpellDamageRange(caster, target, enemies, allies);
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			descriptionSB.setLength(0);

			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"永恒而广袤的力量啊，被封印成千上万年后，终于得以释放！目睹我唤出的奥术末日，成为为情欲而狂的傀儡，屈服吧！"),
													"你稍微将精力集中在灵气上，随后释放出一道噼啪作响的强力奥术闪电束，冲向了你自己！",
													"你稍微将精力集中在灵气上，随后释放出一道噼啪作响的强力奥术闪电束，冲向了[npc.Name]！",
													"[npc.Name]稍微将精力集中在灵气上，随后释放出一道噼啪作响的强力奥术闪电束，冲向了自己！",
													"[npc.Name]稍微将精力集中在灵气上，随后释放出一道噼啪作响的强力奥术闪电束，冲向了你！",
													"[npc.Name]稍微将精力集中在灵气上，随后释放出一道噼啪作响的强力奥术闪电束，冲向了[npc2.name]！"));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {
				float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
				descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
				if(damage>0) {
					descriptionSB.append(applyDamage(caster, target, damage));
				}
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
		
		@Override
	    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("无法暴击。");
	    }

		@Override
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return false;
		}
	};
	
	private static Map<SpellSchool, List<Spell>> spellsFromSchoolMap = new HashMap<>();
	
	static {
		for(SpellSchool school : SpellSchool.values()) {
			spellsFromSchoolMap.put(school, new ArrayList<>());
		}
		for(Spell s : Spell.values()) {
			spellsFromSchoolMap.get(s.getSpellSchool()).add(s);
		}
	}
	
	public static Map<SpellSchool, List<Spell>> getSpellsFromSchoolMap() {
		return spellsFromSchoolMap;
	}
	
	
	private static StringBuilder descriptionSB = new StringBuilder();
	
	protected static Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> spellStealUpgradeTree;
	protected static Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> soothingWatersUpgradeTree;
	static {
		spellStealUpgradeTree = new HashMap<>();

		spellStealUpgradeTree.put(0, new ArrayList<>());
		spellStealUpgradeTree.get(0).add(new TreeEntry<>(SpellSchool.ARCANE, 0, SpellUpgrade.STEAL_1));

		spellStealUpgradeTree.put(1, new ArrayList<>());
		spellStealUpgradeTree.get(1).add(new TreeEntry<>(SpellSchool.ARCANE, 1, SpellUpgrade.STEAL_2));
		spellStealUpgradeTree.get(1).get(0).addLink(spellStealUpgradeTree.get(0).get(0));

		spellStealUpgradeTree.put(2, new ArrayList<>());
		spellStealUpgradeTree.get(2).add(new TreeEntry<>(SpellSchool.ARCANE, 2, SpellUpgrade.STEAL_3A));
		spellStealUpgradeTree.get(2).get(0).addLink(spellStealUpgradeTree.get(1).get(0));

		spellStealUpgradeTree.get(2).add(new TreeEntry<>(SpellSchool.ARCANE, 2, SpellUpgrade.STEAL_3B));
		spellStealUpgradeTree.get(2).get(1).addLink(spellStealUpgradeTree.get(2).get(0));
		
		soothingWatersUpgradeTree = new HashMap<>();

		soothingWatersUpgradeTree.put(0, new ArrayList<>());
		soothingWatersUpgradeTree.get(0).add(new TreeEntry<>(SpellSchool.WATER, 0, SpellUpgrade.SOOTHING_WATERS_1));
		soothingWatersUpgradeTree.get(0).add(new TreeEntry<>(SpellSchool.WATER, 0, SpellUpgrade.SOOTHING_WATERS_1_CLEAN));

		soothingWatersUpgradeTree.put(1, new ArrayList<>());
		soothingWatersUpgradeTree.get(1).add(new TreeEntry<>(SpellSchool.WATER, 1, SpellUpgrade.SOOTHING_WATERS_2));
		soothingWatersUpgradeTree.get(1).get(0).addLink(soothingWatersUpgradeTree.get(0).get(0));
		soothingWatersUpgradeTree.get(1).add(new TreeEntry<>(SpellSchool.WATER, 1, SpellUpgrade.SOOTHING_WATERS_2_CLEAN));

		soothingWatersUpgradeTree.put(2, new ArrayList<>());
		soothingWatersUpgradeTree.get(2).add(new TreeEntry<>(SpellSchool.WATER, 2, SpellUpgrade.SOOTHING_WATERS_3));
		soothingWatersUpgradeTree.get(2).get(0).addLink(soothingWatersUpgradeTree.get(1).get(0));
	}
	
	
	private boolean forbiddenSpell;
	private SpellSchool spellSchool;
	private SpellType type;
	protected DamageType damageType;
	private boolean beneficial;
	
	private String name;
	private String description;
	
	protected int damage;
	protected int spellCost;
	protected DamageVariance damageVariance;
	private Map<AbstractStatusEffect, Integer> statusEffects;
	
	private List<SpellUpgrade> upgradeList;
	private Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> spellUpgradeTree;
	
	private HashMap<AbstractAttribute, Integer> attributeModifiers;
	private List<String> extraEffects;
	private List<String> modifiersList;

	private String pathName;
	private String SVGString;

	private Spell(boolean forbiddenSpell,
			SpellSchool spellSchool,
			SpellType type,
			DamageType damageType,
			boolean beneficial,
			String name,
			String pathName,
			String description,
			int damage,
			DamageVariance damageVariance,
			int spellCost,
			Map<AbstractStatusEffect, Integer> statusEffects,
			List<SpellUpgrade> upgradeList,
			HashMap<AbstractAttribute, Integer> attributeModifiers,
			List<String> extraEffects) {
		
		this.forbiddenSpell = forbiddenSpell;
		
		this.spellSchool = spellSchool;
		this.type = type;
		this.damageType = damageType;
		this.beneficial = beneficial;
		
		this.name = name;
		this.description = description;

		this.damage = damage;
		this.damageVariance = damageVariance;
		
		this.spellCost = spellCost;
		
		if(statusEffects == null) {
			this.statusEffects = new HashMap<>();
		} else {
			this.statusEffects = statusEffects;
		}
		
		spellUpgradeTree = new HashMap<>();
		this.upgradeList = upgradeList;
		initialiseBasicSpellUpgradeTree(upgradeList);

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
		
		
		// SVG:
		this.pathName = pathName;
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/combat/spell/" + pathName + ".svg");
			if(is==null) {
				System.err.println("Error! Spell icon file does not exist (Trying to read from '"+pathName+"')!");
			}
			SVGString = Util.inputStreamToString(is);
			
			SVGString = SvgUtil.colourReplacement(this.toString(), damageType.getMultiplierAttribute().getColour(), SVGString);
			
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    protected boolean isTargetAtMaximumLust(GameCharacter target) {
    	return target!=null && target.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX);
    }
	
	private void initialiseBasicSpellUpgradeTree(List<SpellUpgrade> upgradeList) {
		if(upgradeList!=null) {
			for(int i = 0; i<upgradeList.size(); i++) {
				spellUpgradeTree.put(i, new ArrayList<>());
				spellUpgradeTree.get(i).add(new TreeEntry<>(spellSchool, i, upgradeList.get(i)));

				if(i==upgradeList.size()-1 && upgradeList.size()==4) {
					spellUpgradeTree.get(i-1).add(new TreeEntry<>(spellSchool, i-1, upgradeList.get(i)));
					spellUpgradeTree.get(i-2).get(0).addLink(spellUpgradeTree.get(i-1).get(1));
					
				} else if(i!=0) {
					spellUpgradeTree.get(i).get(0).addLink(spellUpgradeTree.get(i-1).get(0));
				}
			}
		}
	}

	public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
		return applyEffect(caster, target, null, null, isHit, isCritical);
	}

	public abstract String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical);

	public List<String> getModifiersAsStringList() {
		return modifiersList;
	}
	
	public boolean isSpellBook() {
		return true;
	}
	
	public boolean isForbiddenSpell() {
		return forbiddenSpell;
	}

	public SpellSchool getSpellSchool() {
		return spellSchool;
	}

	public SpellType getType() {
		return type;
	}

	public DamageType getDamageType() {
		return damageType;
	}
	
	public boolean isBeneficial() {
		return beneficial;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription(GameCharacter source) {
		return description;
	}

	public int getDamage(GameCharacter caster) {
		return damage;
	}

	public DamageVariance getDamageVariance() {
		return damageVariance;
	}

	/**
	 * @return The basic spell cost, not taking into account the caster's spell efficiency.
	 */
	public int getBaseCost(GameCharacter caster) {
		return spellCost;
	}
	
	/**
	 * @param caster The person casting the spell.
	 * @return The cost of casting this spell as it relates to the caster. i.e. This spell's basic spell cost, reduced by the caster's spell efficiency.
	 */
	public float getModifiedCost(GameCharacter caster) {
		float calculatedCost = getBaseCost(caster);
		
		calculatedCost *= ((100 - caster.getAttributeValue(Attribute.SPELL_COST_MODIFIER)) / 100f);
		
		// Round float value to nearest 1 decimal place:
		return (Math.round(calculatedCost*10))/10f;
	}
	
	public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
		return statusEffects;
	}

	/**
	 * @return A list of all available SpellUpgrades for this Spell. <b>You should most likely be checking getSpellUpgradeTree() instead!</b>
	 */
	public List<SpellUpgrade> getUpgradeList() {
		return upgradeList;
	}
	
	public Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> getSpellUpgradeTree() {
		return spellUpgradeTree;
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

	public String getPathName() {
		return pathName;
	}
	
	protected void applyStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
		for (Entry<AbstractStatusEffect, Integer> se : getStatusEffects(caster, target, isCritical).entrySet()) {
			Main.combat.addStatusEffectToApply(target, se.getKey(), se.getValue() * (caster.isPlayer() && caster.hasTrait(Perk.JOB_MUSICIAN, true)?2:1) * (isCritical?2:1));
		}
	}

	protected String getFormattedSpellDamageRange(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		return "造成<span style='color:"+getDamageType().getColour().toWebHexString()+";'>"
				+Math.round(Attack.getMinimumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance()))
				+"-"
				+Math.round(Attack.getMaximumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance()))
				+damageType.getName()
				+ "</span>伤害";
	}
	
	protected String getDamageDescription(GameCharacter caster, GameCharacter target, float damage, boolean isHit, boolean isCritical) {
		StringBuilder damageCostDescriptionSB = new StringBuilder();
		
		boolean appliesEffects = !this.getStatusEffects(caster, target, isCritical).isEmpty();

		damageCostDescriptionSB.append("<br/>");
			if (caster.isPlayer()) {
				if (isCritical) {
					if(!isHit) {
						damageCostDescriptionSB.append("[style.italicsBad(你没有命中！)]");
					} else {
						if(damage>0) {
							damageCostDescriptionSB.append(UtilText.parse(target,
									"你击中了[npc.Name]的[style.boldExcellent(要害)]，造成<b>" + damage + "</b>" + damageType.getMultiplierAttribute().getColouredName("b") + "！"));
						}
						if(appliesEffects) {
							damageCostDescriptionSB.append("你的法术造成了[style.boldExcellent(暴击)]，持续时间翻倍！");
						}
					}
				} else {
					if(!isHit) {
						damageCostDescriptionSB.append("[style.italicsBad(你没有命中！)]");
					} else {
						if(damage>0) {
							damageCostDescriptionSB.append(UtilText.parse(target,
									"你击中了[npc.Name]，造成<b>" + damage + "</b>" + damageType.getMultiplierAttribute().getColouredName("b") + "！"));
						}
					}
				}
				
			} else {
				if (isCritical) {
					if(!isHit) {
						damageCostDescriptionSB.append(UtilText.parse(caster, "[style.italicsBad([npc1.Name]没有命中！)]"));
					} else {
						if(damage>0) {
							damageCostDescriptionSB.append(UtilText.parse(caster, target,
									"[npc1.name]击中了" + (target.isPlayer()?"你":"[npc2.name]")+"的[style.boldExcellent(要害)]，造成<b>" + damage + "</b>" + damageType.getMultiplierAttribute().getColouredName("b") + "！"));
						}
						if(appliesEffects) {
							damageCostDescriptionSB.append(UtilText.parse(caster, "[npc.Name]的法术造成了[style.boldExcellent(暴击)]，持续时间翻倍！"));
						}
					}
				} else {
					if(!isHit) {
						damageCostDescriptionSB.append(UtilText.parse(caster, "[style.italicsBad([npc1.Name]没有命中！)]"));
					} else {
						if(damage>0) {
							damageCostDescriptionSB.append(UtilText.parse(caster, target,
									"[npc1.name]击中了" + (target.isPlayer()?"你":"[npc2.name]")+"，造成<b>" + damage + "</b>" + damageType.getMultiplierAttribute().getColouredName("b") + "！"));
						}
					}
				}
			}
			if(damageCostDescriptionSB.toString().toString().equals("<br/>")) {
				return "";
			}
		
		return damageCostDescriptionSB.toString();
	}
	
	protected String applyDamage(GameCharacter caster, GameCharacter target, float damage) {
		return this.getDamageType().damageTarget(caster, target, (int)damage).getKey();
//		return target.incrementHealth(caster, -damage);
	}
	
	protected String getStatusEffectApplication(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
		StringBuilder damageCostDescriptionSB = new StringBuilder();

		if (this.getStatusEffects(caster, target, isCritical) != null && !this.getStatusEffects(caster, target, isCritical).isEmpty() && isHit) {
			damageCostDescriptionSB.append(
					"<br/>"+UtilText.parse(target,
								(!target.isPlayer()
									? "[npc.Name]现在"
									: "你现在")
								+(this.isBeneficial()
										?"受到"
										:"受到")));
			
			int i = 0;
			for (Entry<AbstractStatusEffect, Integer> seEntry : this.getStatusEffects(caster, target, isCritical).entrySet()) {
				if (i != 0) {
					if (i == statusEffects.size() - 1) {
						damageCostDescriptionSB.append("、");
					} else {
						damageCostDescriptionSB.append("的影响。");
					}
				}
				damageCostDescriptionSB.append("持续<b>" + seEntry.getValue() * (caster.isPlayer() && caster.hasTrait(Perk.JOB_MUSICIAN, true)?2:1) * (isCritical?2:1)
						+ "</b>回合"
						+(caster.hasTrait(Perk.JOB_MUSICIAN, true)
								?"(由于<b style='color:"+Perk.JOB_MUSICIAN.getColour().toWebHexString()+";'>"+Perk.JOB_MUSICIAN.getName(caster)+"</b>而[style.boldExcellent(翻倍)])"
								:"")
						+ "<b style='color:" + seEntry.getKey().getColour().toWebHexString() + ";'>" + seEntry.getKey().getName(target) + "</b>");
				i++;
			}
			damageCostDescriptionSB.append("的影响。");
		}
		
		return damageCostDescriptionSB.toString();
	}
	
	public static String getBasicStatusEffectApplication(GameCharacter target, boolean beneficial, Map<AbstractStatusEffect, Integer> statusEffects) {
		StringBuilder damageCostDescriptionSB = new StringBuilder();

		damageCostDescriptionSB.append(
				"<br/>"+UtilText.parse(target,
							(!target.isPlayer()
								? "[npc.She]现在"
								: "你现在")
							+(beneficial
									?"受到"
									:"受到")));
		
		int i = 0;
		for (Entry<AbstractStatusEffect, Integer> seEntry : statusEffects.entrySet()) {
			if (i != 0) {
				if (i == statusEffects.size() - 1) {
					damageCostDescriptionSB.append("、");
				} else {
					damageCostDescriptionSB.append("、");
				}
			}
			damageCostDescriptionSB.append("<b>" + seEntry.getValue() + "</b>回合<b style='color:" + seEntry.getKey().getColour().toWebHexString() + ";'>" + seEntry.getKey().getName(target) + "</b>");
			i++;
		}
		damageCostDescriptionSB.append("的影响。");
		
		return damageCostDescriptionSB.toString();
	}

	protected String getCostDescription(GameCharacter caster, float cost) {
		if(cost<0) {
			return "<br/>释放该法术需要消耗"+(caster.isPlayer()?"你":UtilText.parse(caster, "[npc.name]"))+"<b>"
					+ -cost + "</b><b style='color:" + Attribute.HEALTH_MAXIMUM.getColour().toWebHexString() + ";'>"+Attribute.HEALTH_MAXIMUM.getName()+"</b>！</b>";
		} else {
			return "<br/>释放该法术需要消耗"+(caster.isPlayer()?"你":UtilText.parse(caster, "[npc.name]"))+"<b>"
					+ cost + "</b><b style='color:" + Attribute.MANA_MAXIMUM.getColour().toWebHexString() + ";'>灵气</b>！</b>";
		}
	}

	/**
	 * Utility method for returning appropriate cast description based on the identity of caster and target. Variable names should be self-explanatory.
	 */
	private static String getCastDescription(GameCharacter caster, GameCharacter target,
			List<String> chuuniDialogue,
			String playerSelfCast,
			String playerCastOnNPC,
			String NPCSelfCast,
			String NPCCastOnPlayer,
			String NPCCastOnNPC) {
		StringBuilder sb = new StringBuilder();
		
		if(caster.hasTraitActivated(Perk.CHUUNI) && chuuniDialogue!=null) {
			sb.append(UtilText.parse(caster, target, "[npc.speech("+Util.randomItemFrom(chuuniDialogue)+")]</br>"));
		}
		if(caster.isPlayer()) {
			if(target.isPlayer()) {
				sb.append(playerSelfCast);
			} else {
				sb.append(UtilText.parse(target, playerCastOnNPC));
			}
		} else {
			if(target.isPlayer()) {
				sb.append(UtilText.parse(caster, NPCCastOnPlayer));
			} else {
				if(target.equals(caster)) {
					sb.append(UtilText.parse(caster, NPCSelfCast));
				} else {
					sb.append(UtilText.parse(caster, target, NPCCastOnNPC));
				}
			}
		}
		return sb.toString();
	}
	
	// Rendering:
	
	private static final int ROWS = 3;
	
	private static StringBuilder treeSB = new StringBuilder();
	private static StringBuilder spellSB = new StringBuilder();
	private static StringBuilder lineSB = new StringBuilder();
	private static StringBuilder entrySB = new StringBuilder();
	

	public static String getSpellMiscTreeDisplay(GameCharacter character, GameCharacter target) {
		treeSB.setLength(0);

		treeSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
				+ "<div class='container-full-width' style='text-align:center;'><h6 style='color:"+PresetColour.DAMAGE_TYPE_SPELL.toWebHexString()+";'>秘术</h6></div>");
		
		for(Spell spell : Spell.values()) {
			if(!spell.isSpellBook()) { // Only append special spells obtained from weapons & other sources
				treeSB.append("<div class='container-full-width' style='border:1px solid "+(character.hasSpell(spell, true)?PresetColour.DAMAGE_TYPE_SPELL:PresetColour.BASE_GREY_DARK).toWebHexString()+"; width:25%; padding:0; margin:0;'>");
					treeSB.append(appendSpell(character, target, -1, spell, true));
				treeSB.append("</div>");
			}
		}
		
		treeSB.append("</div>");
		
		return treeSB.toString();
	}
	
	public static String getSpellTreesDisplay(SpellSchool school, GameCharacter character, GameCharacter target) {
		treeSB.setLength(0);
		appendSpellSchool(school, character, target);
		return treeSB.toString();
	}
	
	private static void appendSpellSchool(SpellSchool spellSchool, GameCharacter character, GameCharacter target) {
		treeSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
				+ "<div class='container-full-width' style='text-align:center;'><h6 style='color:"+spellSchool.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(spellSchool.getName())+"</h6>"
						+"<b style='color:"+spellSchool.getColour().toWebHexString()+";'>"+character.getSpellUpgradePoints(spellSchool)+"</b><b>可用升级点数</b></div>");
		
		for(Spell spell : Spell.getSpellsFromSchoolMap().get(spellSchool)) {
			if(spell.isSpellBook()) { // Do not append spells obtained from weapons & other sources
				boolean fullyUpgraded = character.isSpellFullyUpgraded(spell);
				
				if(!spell.getSpellUpgradeTree().isEmpty()) {
					treeSB.append("<div class='container-full-width' style='border:1px solid "+(fullyUpgraded?spell.getSpellSchool().getColour():PresetColour.BASE_GREY_DARK).toWebHexString()+"; width:25%; padding:0; margin:0;'>");
						for(int row=-1; row<ROWS; row++) {
							treeSB.append(appendSpell(character, target, row, spell, false));
						}
					treeSB.append("</div>");
				}
			}
		}
		
		treeSB.append("</div>");
	}
	
	private static String appendSpell(GameCharacter character, GameCharacter target, int row, Spell spell, boolean miscSpell) {
		spellSB.setLength(0);

		spellSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
			if(row==-1) {
				boolean hasSpell = character.hasSpell(spell, miscSpell);
				boolean forbidden = spell.isForbiddenSpell();
				
				spellSB.append("<div class='square-button "+(!hasSpell?" disabled":"")+"' style='width:50%; margin:8px 25% 4px 25%; cursor: default; "
										+(hasSpell
												?"border-color:"+spell.getSpellSchool().getColour().toWebHexString()+";"
												:"")+"' id='SPELL_TREE_"+spell+"'>"
									+ "<div class='square-button-content' style='cursor: default;'>"+spell.getSVGString()+"</div>"
									+ (!hasSpell
										?(forbidden
											?"<div class='overlay disabled-dark' style='cursor:default;'></div>"
											:"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.8); '></div>")
										:"")
								+ "</div>");
				
				Value<Boolean, String> useDesc = spell.getSpellCastOutOfCombatDescription(character, target);
				spellSB.append("<div class='normal-button "+(useDesc.getKey()?"":"disabled")+"' id='SPELL_TREE_CAST_"+spell+"' style='width:50%; margin:8px 25% "+(miscSpell?"8px":"0")+" 25%; text-align:center;'>");
				spellSB.append("施法");
				spellSB.append("</div>");
				
			} else {
				List<TreeEntry<SpellSchool, SpellUpgrade>> upgradesList = spell.getSpellUpgradeTree().get(row);
				int size = upgradesList.size();
				
				spellSB.append(getHorizontalLine(character, spell, row));
				for(TreeEntry<SpellSchool, SpellUpgrade> entry : upgradesList) {
					spellSB.append(getUpgradeEntry(character, spell, entry, size));
				}
			}
		spellSB.append("</div>");
		return spellSB.toString();
	}
	
	private static String getHorizontalLine(GameCharacter character, Spell spell, int row) {
		lineSB.setLength(0);
		
		for(TreeEntry<SpellSchool, SpellUpgrade> entry : spell.getSpellUpgradeTree().get(row)) {
			float entryX = getX(spell, entry.getRow(), entry);
			for(TreeEntry<SpellSchool, SpellUpgrade> siblingEntry : entry.getSiblingLinks()) {
				float siblingX = getX(spell, siblingEntry.getRow(), siblingEntry);
				lineSB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
						+ "<svg width='100%' height='100%'><line x1='"+siblingX+"%' y1='50%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineSiblingColour(character, spell, entry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
			}
			for(TreeEntry<SpellSchool, SpellUpgrade> parentEntry : entry.getParentLinks()) {
				float parentX = getX(spell, parentEntry.getRow(), parentEntry);
				String colour = getPerkLineParentColour(character, spell, entry).toWebHexString();
						
				if(Math.abs(entryX-parentX)>0.01f) {
					lineSB.append("<div style='width:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events: none;'>"
							+ "<svg style='padding:0; margin:0;' width='100%'><line x1='"+entryX+"%' y1='0' x2='"+parentX+"%' y2='0' stroke='"+colour+"' stroke-width='4px'/></svg></div>");
				}
			}
		}
		
		return lineSB.toString();
	}
	
	private static float getMargin(int size) {
		return (100-(size*40))/(size*2f);
	}
	
	private static float getX(Spell spell, int row, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		List<TreeEntry<SpellSchool, SpellUpgrade>> list = spell.getSpellUpgradeTree().get(row);
		int size = list.size();
		float marginSize = getMargin(size);
		int column = list.indexOf(entry);
		
		return ((marginSize*(column)*2)+(column*40)+20+marginSize);
	}
	
	private static String getUpgradeEntry(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> perkEntry, int size) {
		
		entrySB.setLength(0);

		boolean forbidden = spell.isForbiddenSpell();
		boolean hasUpgrade = character.hasSpellUpgrade(perkEntry.getEntry());
		boolean isUpgradeAvailable = isSpellUpgradeAvailable(character, spell, perkEntry);
		
		// Append up/down lines:
		float entryX = getX(spell, perkEntry.getRow(), perkEntry);
		if(!perkEntry.getParentLinks().isEmpty()) {
			entrySB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
					+ "<svg width='100%' height='100%'><line x1='"+entryX+"%' y1='0%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineParentColour(character, spell, perkEntry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
		}
		if(!perkEntry.getChildLinks().isEmpty()) {
			entrySB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
					+ "<svg width='100%' height='100%'><line x1='"+entryX+"%' y1='100%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineChildColour(character, spell, perkEntry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
		}
		
		entrySB.append("<div class='square-button round"+(!hasUpgrade && !isUpgradeAvailable?" disabled":"")+"' style='width:40%; margin:8px "+getMargin(size)+"%; "
										+ (character.hasSpellUpgrade(perkEntry.getEntry())
											?"cursor: default; border-color:"+perkEntry.getCategory().getColour().toWebHexString()+";"
											:(!perkEntry.getEntry().isAvailable(character) //|| character.getSpellUpgradePoints(perkEntry.getCategory()) < perkEntry.getEntry().getPointCost()
												?"cursor: default; border-color:"+PresetColour.GENERIC_BAD.toWebHexString()+";"
												:""))
										+"' id='SPELL_UPGRADE_"+perkEntry.getEntry()+"'>"
							+ "<div class='square-button-content'>"+perkEntry.getEntry().getSVGString()+"</div>"
							+ (!hasUpgrade && !isUpgradeAvailable
								?(forbidden
										?"<div class='overlay disabled-dark' style='border-radius:50%;'></div>"
										:"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.8); border-radius:50%; cursor: default;'></div>")
								:(!hasUpgrade
									?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.6); border-radius:50%; cursor:pointer;'></div>"
									:""))
						+ "</div>");
		
		return entrySB.toString();
	}
	
	public static boolean isSpellUpgradeAvailable(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		if(character.hasSpell(spell) && entry.getEntry().isAlwaysAvailable()) {
			return true;
		}
		if(!entry.getEntry().isAvailable(character)) {
			return false;
		}
		if(!character.hasSpellUpgrade(entry.getEntry())) {
			for(TreeEntry<SpellSchool, SpellUpgrade> linkedEntry : entry.getLinks()) {
				if(character.hasSpellUpgrade(linkedEntry.getEntry())) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static Colour getPerkLineParentColour(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		boolean parentOwned = false;
		for(TreeEntry<SpellSchool, SpellUpgrade> parent : entry.getParentLinks()) {
			if(character.hasSpellUpgrade(parent.getEntry())) {
				parentOwned = true;
				break;
			}
		}
		
		return character.hasSpellUpgrade(entry.getEntry()) && parentOwned
				?entry.getCategory().getColour()
				:isSpellUpgradeAvailable(character, spell, entry)
					?PresetColour.BASE_GREY
					:PresetColour.TEXT_GREY_DARK;
	}
	
	private static Colour getPerkLineChildColour(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		boolean childOwned = false;
		boolean childAvailable = false;
		for(TreeEntry<SpellSchool, SpellUpgrade> child : entry.getChildLinks()) {
			if(character.hasSpellUpgrade(child.getEntry())) {
				childOwned = true;
			}
			if(isSpellUpgradeAvailable(character, spell, child)) {
				childAvailable = true;
			}
		}
		
		return character.hasSpellUpgrade(entry.getEntry()) && childOwned
				?entry.getCategory().getColour()
				:childAvailable
					?PresetColour.BASE_GREY
					:PresetColour.TEXT_GREY_DARK;
	}
	
	private static Colour getPerkLineSiblingColour(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		boolean siblingOwned = false;
		boolean siblingAvailable = false;
		for(TreeEntry<SpellSchool, SpellUpgrade> sibling : entry.getSiblingLinks()) {
			if(character.hasSpellUpgrade(sibling.getEntry())) {
				siblingOwned = true;
			}
			if((isSpellUpgradeAvailable(character, spell, sibling) && character.hasSpellUpgrade(entry.getEntry()))
					|| (isSpellUpgradeAvailable(character, spell, entry) && character.hasSpellUpgrade(sibling.getEntry()))) {
				siblingAvailable = true;
			}
		}
		
		return isSpellUpgradeAvailable(character, spell, entry) && siblingOwned
				?entry.getCategory().getColour()
				:siblingAvailable
					?PresetColour.BASE_GREY
					:PresetColour.TEXT_GREY_DARK;
	}

	// Combat maneuver compatibility
	// These functions are almost identical to the ones in CombatMove class,  with modifications to fit spells as necessary. Refer to CombatMove class for information.

	public int getAPCost() {
		return 1; // Normally just 1 AP.
	}

	public int getCooldown() {
		return 0; // Normally no CD.
	}

	public boolean isCanTargetEnemies() {
		return !isBeneficial();
	}

	public boolean isCanTargetAllies() {
		return isBeneficial();
	}

	public boolean isCanTargetSelf() {
		return isBeneficial();
	}

	public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
		if(!owner.hasSpell(this)) {
			return new Value<>(false, UtilText.parse(owner, "[npc.Name]没有学会该法术，所以无法释放！"));
			
		} else if(Main.game.isInCombat()) {
			return new Value<>(false, UtilText.parse(owner, "战斗中只能以战斗动作释放法术！"));
		}
		
		return new Value<>(false, "该法术只能在战斗中释放！");
	}

	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if(isCanTargetAllies() && allies.isEmpty()) {
			return 0.0f;
		}
		
		if(this.getType().isStatusEffectFocus()) { // If this spell is just for the application of a status effect, do not use it if all targets already have that status effect:
			boolean noEffect = true;
			if(isCanTargetEnemies()) { // Enemy status effect application:
				Set<GameCharacter> survivingEnemies = new HashSet<>(enemies);
				survivingEnemies.removeIf(enemy -> Main.combat.isCombatantDefeated(enemy));
//				System.out.println(survivingEnemies.size());
				enemyLoop:
				for(GameCharacter enemy : survivingEnemies) {
					List<AbstractStatusEffect> statusEffects = new ArrayList<>(this.getStatusEffects(source, enemy, false).keySet());
					if(!statusEffects.isEmpty()) {
						for(AbstractStatusEffect se : statusEffects) {
							if(!enemy.hasStatusEffect(se)) {
								boolean alreadyTargetedWithThisSpell = false;
								for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
									if(move.getKey()==enemy && move.getValue().getAssociatedSpell()==this) {
										alreadyTargetedWithThisSpell = true;
										break;
									}
								}
								if(!alreadyTargetedWithThisSpell) {
									noEffect = false;
//									System.out.println(source.getName()+" | "+this.getName());
									break enemyLoop;
								}
							}
						}
					}
				}
				
			} else {
				Set<GameCharacter> survivingAllies = new HashSet<>(allies);
				survivingAllies.add(source);
				survivingAllies.removeIf(ally -> Main.combat.isCombatantDefeated(ally));
				allyLoop:
				for(GameCharacter ally : survivingAllies) {
					List<AbstractStatusEffect> statusEffects = new ArrayList<>(this.getStatusEffects(source, ally, false).keySet());
					if(!statusEffects.isEmpty()) {
						for(AbstractStatusEffect se : statusEffects) {
							if(!ally.hasStatusEffect(se)) {
								boolean alreadyTargetedWithThisSpell = false;
								for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
									if(move.getKey()==ally && move.getValue().getAssociatedSpell()==this) {
										alreadyTargetedWithThisSpell = true;
										break;
									}
								}
								if(!alreadyTargetedWithThisSpell) {
									noEffect = false;
									break allyLoop;
								}
							}
						}
					}
				}
			}
			if(noEffect) {
				return 0;
			}
		}
		
		int behaviourMultiplier = 1;
		if(source.getCombatBehaviour()==CombatBehaviour.ATTACK && !this.isBeneficial()) {
			behaviourMultiplier = 2;
		}
		if(source.getCombatBehaviour()==CombatBehaviour.SUPPORT && this.isBeneficial()) {
			behaviourMultiplier = 10;
		}
		if(source.getCombatBehaviour()==CombatBehaviour.SPELLS) {
			behaviourMultiplier = 10;
		}
		return (0.2f*behaviourMultiplier) - 0.2f * source.getSelectedMovesByType(CombatMoveType.SPELL);
	}

	public GameCharacter getPreferredTarget(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if(Main.game.isInCombat() && source.isPlayer()) {
			return Main.combat.getTargetedCombatant();
		}
		
		if(Main.game.isInCombat()) {
			GameCharacter preferredTarget = Main.combat.getPreferredTarget(source);
	    	if(preferredTarget!=null && !Main.combat.isCombatantDefeated(preferredTarget)) {
	    		return preferredTarget;
	    	}
		}
		
		if(isCanTargetEnemies()) {
			if(AbstractCombatMove.shouldBlunder()) {
				return enemies.get(Util.random.nextInt(enemies.size()));
				
			} else {
				float lowestHP = -1;
				GameCharacter potentialCharacter = null;
				
				if(this.getType().isStatusEffectFocus()) { // If this spell is primarily concerned with applying a status effect, only use it on targets who do not already have that status effect:
					Set<GameCharacter> survivingEnemies = new HashSet<>(enemies);
					survivingEnemies.removeIf(enemy -> Main.combat.isCombatantDefeated(enemy));
					enemyLoop:
					for(GameCharacter enemy : survivingEnemies) {
						List<AbstractStatusEffect> statusEffects = new ArrayList<>(this.getStatusEffects(source, enemy, false).keySet());
						if(!statusEffects.isEmpty()) {
							for(AbstractStatusEffect se : statusEffects) {
								if(!enemy.hasStatusEffect(se)) {
									boolean alreadyTargetedWithThisSpell = false;
									for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
										if(move.getKey()==enemy && move.getValue().getAssociatedSpell()==this) {
											alreadyTargetedWithThisSpell = true;
											break;
										}
									}
									if(!alreadyTargetedWithThisSpell) {
										potentialCharacter = enemy;
										break enemyLoop;
									}
								}
							}
						}
					}
				}
				
				if(potentialCharacter==null) {
					for(GameCharacter character : enemies) {
						if(lowestHP == -1 || character.getHealth() < lowestHP) {
							potentialCharacter = character;
							lowestHP = character.getHealth();
						}
					}
				}
				return potentialCharacter;
			}
		}
		if(isCanTargetAllies() && !allies.isEmpty()) {
			if(AbstractCombatMove.shouldBlunder()) {
				return allies.get(Util.random.nextInt(allies.size()));
				
			} else {
				float lowestHP = -1;
				GameCharacter potentialCharacter = null;
				
				if(this.getType().isStatusEffectFocus()) { // If this spell is primarily concerned with applying a status effect, only use it on targets who do not already have that status effect:
					Set<GameCharacter> survivingAllies = new HashSet<>(allies);
					survivingAllies.add(source);
					survivingAllies.removeIf(ally -> Main.combat.isCombatantDefeated(ally));
					allyLoop:
					for(GameCharacter ally : survivingAllies) {
						List<AbstractStatusEffect> statusEffects = new ArrayList<>(this.getStatusEffects(source, ally, false).keySet());
						if(!statusEffects.isEmpty()) {
							for(AbstractStatusEffect se : statusEffects) {
								if(!ally.hasStatusEffect(se)) {
									boolean alreadyTargetedWithThisSpell = false;
									for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
										if(move.getKey()==ally && move.getValue().getAssociatedSpell()==this) {
											alreadyTargetedWithThisSpell = true;
											break;
										}
									}
									if(!alreadyTargetedWithThisSpell) {
										potentialCharacter = ally;
										break allyLoop;
									}
								}
							}
						}
					}
				}
				
				if(potentialCharacter==null) {
					for(GameCharacter character : allies) {
						if(lowestHP == -1 || character.getHealth() < lowestHP) {
							potentialCharacter = character;
							lowestHP = character.getHealth();
						}
					}
				}
				return potentialCharacter;
			}
		}
		return source;
	}
	
	public abstract String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies);
	
	public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
        StringBuilder predictionSB = new StringBuilder();
        
        predictionSB.append(
				(isCrit?"[style.colourExcellent(暴击)]: ":"")
				+ "对[npc2.name]<span style='color:" + getSpellSchool().getColour().toWebHexString() + ";'>施放法术“"+ getName() + "”</span>"
				+ "。");

    	if(getSpellSchool()==SpellSchool.FIRE && source.hasStatusEffect(StatusEffect.FIRE_MANA_BURN) && Main.combat.getManaBurnStack().get(source).size()>0 && Main.combat.getManaBurnStack().get(source).peek()<0) {
    		predictionSB.append("<br/>将会消耗<b style='color:"+PresetColour.ATTRIBUTE_HEALTH.toWebHexString()+";'>"+Units.round((-Main.combat.getManaBurnStack().get(source).peek()), 1)+Attribute.HEALTH_MAXIMUM.getName()+"</b>"
    				+ "([style.colourFire("+StatusEffect.FIRE_MANA_BURN.getName(source)+")])。");
    	} else {
    		predictionSB.append("<br/>将会消耗<b style='color:"+PresetColour.ATTRIBUTE_MANA.toWebHexString()+";'>"+this.getModifiedCost(source)+"灵气</b>。");
    	}
    	
        predictionSB.append("<br/><i>"+getBasicEffectsString(source, target, enemies, allies)+"</i>");
        
        return UtilText.parse(source, target, predictionSB.toString());
	}

	public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.applyEffect(source, target, enemies, allies, true, isCrit));
		
		if(isCrit && !this.isBeneficial() && source.hasPerkAnywhereInTree(Perk.ARCANE_CRITICALS)) {
			sb.append(UtilText.parse(source, "<br/>[npc.NamePos]的法术[style.boldExcellent(暴击)]使"+(target.isPlayer()?"你":UtilText.parse(target, "[npc.name]"))+"承受了[style.boldArcane(奥术弱点)]的效果！"));
			target.addStatusEffect(StatusEffect.ARCANE_WEAKNESS, 2);
			sb.append(
					UtilText.parse(target,
							"<br/>[npc.NameIsFull]现在受到了<b style='color:"+StatusEffect.ARCANE_WEAKNESS.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(StatusEffect.ARCANE_WEAKNESS.getName(target))+"</b>"
									+ "的影响，持续<b>两回合</b>！"));
		}
		
		return sb.toString();
	}

	// Applies mana cost effects here. If overridden, don't forget to super call it unless it's a free spell.
	public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if(getSpellSchool() == SpellSchool.FIRE && source.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
			if(!Main.game.isInCombat()) {
				Main.combat.setupManaBurnStackForOutOfCombat(source);
			}
			Main.combat.getManaBurnStack().get(source).push(source.burnMana(getModifiedCost(source)));
			
		} else {
			source.incrementMana(-getModifiedCost(source));
		}
	}
	
    public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
    	if(getSpellSchool() == SpellSchool.FIRE && source.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
    		float amount = Main.combat.getManaBurnStack().get(source).pop();
    		if(amount<0) {
        		source.incrementHealth(-amount);
    		} else {
    			source.incrementMana(amount);
    		}
		} else {
			source.incrementMana(getModifiedCost(source));
		}
    }

	public void applyDisruption(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		// Override. Note that disrupted spells don't disrupt their mana.
	}

	//TODO combine these two methods:
	
    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
    	List<String> critReqs = new ArrayList<>();

    	if(this.getSpellSchool() == SpellSchool.FIRE) {
    		critReqs.add(UtilText.parse(source, "[npc.NamePos]的"+Attribute.HEALTH_MAXIMUM.getName()+"低于25%。"));
    	} else {
        	return Util.newArrayListOfValues("是唯一的行动。");
    	}
    	
    	return critReqs;
    }
	
	//Differs from normal version; spells have special crit requirements.
	public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if(this.getSpellSchool() == SpellSchool.FIRE) {
			return source.getHealthPercentage()<=0.25f; // Fire school spells crit when below 25% health.
		} else {
			return source.getSelectedMoves().size()<=1;
		}
	}
}
