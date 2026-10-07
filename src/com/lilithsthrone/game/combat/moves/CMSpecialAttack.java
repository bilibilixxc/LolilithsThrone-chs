package com.lilithsthrone.game.combat.moves;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.DamageVariance;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class CMSpecialAttack {
	
	public static AbstractCombatMove HORSE_KICK = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "蹄踢",
            1,
            2,
            1,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            DamageVariance.NONE,
            "moves/hoof_kick",
            Util.newArrayListOfValues(
            		PresetColour.DAMAGE_TYPE_PHYSICAL,
            		PresetColour.BASE_RED),
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.DAZED, 1)),
            Util.newHashMapOfValues()) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, (source.getUnarmedDamage() * 2 * (source.isLegMovementHindered()?0.1f:1)));
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), false);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getLegType().getFootType().equals(FootType.HOOFS), "仅适用于拥有蹄子的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"对" + (target==null?"[npc.her]的目标":"[npc2.name]") + "发出一击有力的蹄踢，造成"
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, false), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (source.isLegMovementHindered()?"[style.italicsMinorBad(由于衣物阻碍了腿部运动，造成的伤害降低为原来的10%！)]":""));
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]使用其强健的腿部向目标发出一记有力的踢击，造成" + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + "基础伤害。"
            				+ "[style.italicsMinorBad(如果衣物阻碍了腿部运动，造成的伤害将会降低为原来的10%。)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean maxLust = isTargetAtMaximumLust(target);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            Value<String, Integer> critDamageValue = new Value<>("", 0);
            if(isCrit) {
            	critDamageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit)/2); // Second kick damage from the crit.
            }
            
            return formatAttackOutcome(source, target,
            		(source.isLegMovementHindered()
            				?"由于衣物阻碍了腿部运动，[npc.name]难以用力，只能对[npc2.name]造成极小的伤害……"
            				:"[npc.Name]侧过身去，接着就用蹄子朝[npc2.name]发出了一记有力的踢击！")
            			+damageValue.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc.Name]又迅速接上一下，试图突破[npc2.namePos]的格挡！"+critDamageValue.getKey()
            			:null),
                	"[npc2.Name]受到了额外" + getFormattedDamage(damageType, critDamageValue.getValue(), target, true, maxLust) + "伤害！");
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 "蹄踢未能突破"+(target!=null?UtilText.parse(target,"[npc.namePos]"):"目标")+"的护盾。");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	int damage = getDamage(turnIndex, source, target, false);
            int potentialDamage = getDamageType(turnIndex, source).shieldCheckNoDamage(source, target, damage);
            if(potentialDamage<=0) {// != damage) {
                return true;
            }
            return false;
        }
    };

	public static AbstractCombatMove CAT_SCRATCH = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "抓挠",
            1,
            1,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/scratch",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VULNERABLE, 2))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, ((source.getUnarmedDamage()*1.5f) * (source.isArmMovementHindered()?0.5f:1)));
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getArmType().equals(ArmType.CAT_MORPH), "仅适用于拥有猫类爪子的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"对" + (target==null?"[npc.her]的目标":"[npc2.name]") + "发出一记凶残的抓挠，造成"
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (source.isArmMovementHindered()?"[style.italicsMinorBad(由于衣物阻碍了手臂运动，造成的伤害降低为原来的50%！)]":""));
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]用猫科的爪子向目标发出一记凶残的爪击，造成" + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + "基础伤害。"
            				+ "[style.italicsMinorBad(如果衣物阻碍了手臂运动，造成的伤害将会降低为原来的50%。)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		(source.isArmMovementHindered()
            				?"由于衣物阻碍了手臂运动，[npc.name]难以用力，只能对[npc2.name]造成一半的伤害……"
            				:"[npc.Name]将爪子从[npc.her]猫般的人形手上伸缩出来，迅速冲向前去，试图向[npc2.name]抓去！")+damageValue.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc.NamePos]的爪击格外有效！"
            			:null),
                	"“"+StatusEffect.VULNERABLE.getName(target)+"”的持续时间翻倍了！");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues((target!=null?UtilText.parse(target,"[npc.NameHasFull]"):"防守者剩余")+"没有"+getDamageType(0, source).getNameColoured()+"护盾。");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return target.getShields(getDamageType(turnIndex, source))<=0;
        }
    };

	public static AbstractCombatMove TAIL_SWIPE = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "扫尾",
            2,
            3,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/tail_swipe",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.DAZED, 2))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage()*3;
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(
            		(source.getTailType().isSuitableForAttack() && source.getTailLength(false)>=100)
            			|| source.getLegConfiguration()==LegConfiguration.TAIL_LONG,
            		"仅适用于拥有足够大小尾巴(长度至少[units.sizeShort(100)])或拥有“"+LegConfiguration.TAIL_LONG.getName()+"”下半身的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"对" + (target==null?"[npc.her] target":"[npc2.name]") + "挥出一记雷霆万钧的扫尾，造成"
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]使用[npc.tailRace]尾巴向目标使出一记雷霆万钧的扫尾，造成" + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + "基础伤害。");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		"[npc.Name]侧过身去，接力扫出了[npc.her]巨大的[npc.tailRace]尾巴，直直向着[npc2.name]而去！"+damageValue.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
        				?"[npc.NamePos]的扫尾格外有效！"
            			:null),
                    "“"+StatusEffect.DAZED.getName(target)+"”的持续时间翻倍了！");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues(
            		(source!=null?UtilText.parse(source,"[npc.NamePos]"):"攻击者")+"的尾巴至少需要达到“<span style='color:"+PenetrationGirth.FIVE_THICK.getColour().toWebHexString()+";'>"+PenetrationGirth.FIVE_THICK.getName()+"</span>”。");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(source.getTailGirth().getValue()>=PenetrationGirth.FIVE_THICK.getValue()) {
                return true;
            }
            return false;
        }
    };

	public static AbstractCombatMove SQUIRREL_SCRATCH = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "松鼠抓挠",
            1,
            1,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/scratch_double",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VULNERABLE, 1))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, source.getUnarmedDamage() * (source.isArmMovementHindered()?0.5f:1));
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getArmType().equals(ArmType.SQUIRREL_MORPH), "仅适用于拥有松鼠爪子的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"对" + (target==null?"[npc.her]的目标":"[npc2.name]") + "发出一记凶残的抓挠，造成"
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, false), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (source.isArmMovementHindered()?"[style.italicsMinorBad(由于衣物阻碍了手臂运动，造成的伤害降低为原来的50%！)]":""));
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]用锋利的爪子向目标发出一记凶残的爪击，造成" + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + "基础伤害。"
            				+ "[style.italicsMinorBad(如果衣物阻碍了手臂运动，造成的伤害将会降低为原来的50%。)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            int dealtCritDamage2 = 0;
            int dealtCritDamage3 = 0;
            if(isCrit) {
            	dealtCritDamage2 = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, false)).getValue()/2;
            	dealtCritDamage3 = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, false)).getValue()/2;
            }
            
            return formatAttackOutcome(source, target,
            		(source.isArmMovementHindered()
            				?"由于衣物阻碍了手臂运动，[npc.name]难以用力，只能对[npc2.name]造成一半的伤害……"
            				:"[npc.Name]将爪子从[npc.her]松鼠般的人形手上伸缩出来，迅速冲向前去，试图向[npc2.name]抓去！")+damageValue.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc.Name]又迅速朝[npc2.name]抓了两下！"
            			:null),
                	"[npc2.Name]受到了额外"+getFormattedDamage(damageType, dealtCritDamage2, target, true, maxLust)+"以及"+getFormattedDamage(damageType, dealtCritDamage3, target, true, maxLust)+"伤害！");
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues((target!=null?UtilText.parse(target,"[npc.NameHasFull]"):"防守者剩余")+"没有"+getDamageType(0, source).getNameColoured()+"护盾。");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return target.getShields(getDamageType(turnIndex, source))<=0;
        }
    };

	public static AbstractCombatMove WOLF_SAVAGE = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "狂野一击",
            6,
            3,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/savage_attack",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CRIPPLE, 3))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
		@Override
        public int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, source.getUnarmedDamage() * 4 * (source.isArmMovementHindered()?0.5f:1));
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getArmType().getRace().equals(Race.WOLF_MORPH), "仅适用于拥有狼化形手臂的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"对" + (target==null?"[npc.her]的目标":"[npc2.name]") + "发出一连串狂野的爪击，造成"
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (source.isArmMovementHindered()?"[style.italicsMinorBad(由于衣物阻碍了手臂运动，造成的伤害降低为原来的50%！)]":""));
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]用锋利的爪子向目标发出一连串狂野的爪击，造成" + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + "基础伤害。"
            				+ "[style.italicsMinorBad(如果衣物阻碍了手臂运动，造成的伤害将会降低为原来的50%。)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		(source.isArmMovementHindered()
            				?"由于衣物阻碍了手臂运动，[npc.name]难以用力，只能对[npc2.name]造成一半的伤害……"
            				:"[npc.Name]长嚎一声后便向[npc2.name]冲去，锋利的爪子掠过[npc2.her]的身体，造成了严重的伤害。")+damageValue.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc.namePos]凶狠的攻击让[npc2.name]猝不及防！"
            			:null),
                    "“"+StatusEffect.CRIPPLE.getName(target)+"”的持续时间翻倍了！");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 "在战斗的第一回合使用。");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Main.combat.getTurn()==0;
        }
    };

	public static AbstractCombatMove ANTLER_HEADBUTT = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "鹿角头槌",
            1,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/antlers",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.DAZED, 2))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage()*2;
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getHornType().equals(HornType.REINDEER_RACK), "仅适用于拥有多叉鹿角的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"对" + (target==null?"[npc.her]的目标":"[npc2.name]") + "使出一记大力头槌，造成"
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]用鹿角向目标发出一记大力头槌，造成" + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + "基础伤害。");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		"[npc.Name]带着巨大的力量纵身一跃，用前额向着[npc2.namePos]的身体砸去，鹿角狠狠地重击了[npc2.herHim]。"+damageValue.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			? "[npc.NamePos]的头槌格外有效！"
            			:null),
                    "“"+StatusEffect.DAZED.getName(target)+"”的持续时间翻倍了！");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues((source!=null?UtilText.parse(source,"[npc.NameIsFull]"):"攻击者")+"需要比"+(target!=null?UtilText.parse(target,"[npc.name]"):"目标")+"高至少50%。");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(source.getHeightValue()>target.getHeightValue()*1.5f) {
                return true;
            }
            return false;
        }
    };

	public static AbstractCombatMove COW_HEADBUTT = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "角撞头槌",
            1,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/horns",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.DAZED, 2))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage()*2;
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasHorns() && !source.getHornType().equals(HornType.REINDEER_RACK), "仅适用于拥有角的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"对" + (target==null?"[npc.her]的目标":"[npc2.name]") + "使出一记大力头槌，造成"
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]用角向目标发出一记大力头槌，造成" + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + "基础伤害。");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		"[npc.Name]带着巨大的力量纵身一跃，用前额向着[npc2.namePos]的身体砸去，角狠狠地重击了[npc2.herHim]。"+damageValue.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			? "[npc.NamePos]的头槌格外有效！"
            			:null),
                    "“"+StatusEffect.DAZED.getName(target)+"”的持续时间翻倍了！");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues((source!=null?UtilText.parse(source,"[npc.NameIsFull]"):"攻击者")+"需要比"+(target!=null?UtilText.parse(target,"[npc.name]"):"目标")+"高至少50%。");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(source.getHeightValue()>target.getHeightValue()*1.5f) {
                return true;
            }
            return false;
        }
    };
    
    public static AbstractCombatMove BITE = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "兽性撕咬",
            1,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/bite",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CRIPPLE, 2))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
    	@Override
        public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
    		Map<AbstractStatusEffect, Integer> effects = Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CRIPPLE, 2));

            if(caster.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH)) {
            	effects.put(StatusEffect.POISONED, 6);
            }
            if(caster.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH_LUST)) {
            	effects.put(StatusEffect.POISONED_LUST, 6);
            }
            
        	if(isCritical) {
        		return effects;
        	}
    		return effects;
    	}
    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(!source.isCoverableAreaExposed(CoverableArea.MOUTH)) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}
    	@Override
        public int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage() * 2 * (!source.isCoverableAreaExposed(CoverableArea.MOUTH)?0:1);
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(
				!Collections.disjoint(source.getFaceTypeTags(), Util.newArrayListOfValues(
						BodyPartTag.FACE_MUZZLE,
						BodyPartTag.FACE_FANGS,
//						BodyPartTag.FACE_SHARK_TEETH,
						BodyPartTag.FACE_BEAK
				)),
				"仅适用于拥有动物拟人面部的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"对" + (target==null?"[npc.her]的目标":"[npc2.name]") + "发出一记兽性的撕咬，造成"
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害"
            				+ (source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH)
        						?"并施加6回合“中毒”。"
        						:(source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH_LUST)
                					?"并施加6回合“欲毒”。"
                					:"。"))
            				+ (!source.isCoverableAreaExposed(CoverableArea.MOUTH)?"[style.italicsBad(由于嘴部被衣物阻挡，伤害降低至0%！)]":""));
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]用动物拟人的面部向目标使出一记兽性的撕咬，造成" + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + "基础伤害"
            				+ (source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH)
        						?"并施加6回合“中毒”。"
        						:(source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH_LUST)
                					?"并施加6回合“欲毒”。"
                					:"。"))
            				+ "[style.italicsBad(由于嘴部被衣物阻挡，伤害降低至0%！)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		(!source.isCoverableAreaExposed(CoverableArea.MOUTH)
            				?"由于嘴部被衣物阻挡，[npc.nameIsFull]无法使用兽性撕咬造成伤害……"
            				:"[npc.Name]带着巨大的力量纵身一跃，试图咬住[npc2.name]！"
            					+ "最终落在了[npc2.her]的[npc2.arm]上，"
										+ "在对方挣脱前，用"+(source.getFaceType()==FaceType.HARPY?"锐利的喙":"野兽般的利齿")+"造成了严重的伤害。"
	            				+ (source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH)
	            						?"被[npc.namePos]毒性的尖牙咬住后，[npc2.namehasFull]的体内被注入了毒液！"
	            						:(source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH_LUST)
	                    					?"被[npc.namePos]毒性的尖牙咬住后，[npc2.namehasFull]的体内被注入了欲毒毒液！"
	                    					:"")))
            			+damageValue.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc.NamePos]的兽性撕咬格外有效！"
            			:null),
                    "“"+StatusEffect.CRIPPLE.getName(target)+"”的持续时间翻倍了！");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues((target!=null?UtilText.parse(target,"[npc.NameHasFull]"):"防守者还剩")+"不到25%"+Attribute.HEALTH_MAXIMUM.getColouredName("span")+"。");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(target.getHealthPercentage()<0.25f) {
                return true;
            }
            return false;
        }
    };

    public static AbstractCombatMove TALON_SLASH = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "鸟爪挥击",
            1,
            1,
            10,
            CombatMoveType.ATTACK,
            DamageType.PHYSICAL,
            DamageVariance.HIGH,
            "moves/talon_slash",
            Util.newArrayListOfValues(PresetColour.RACE_HARPY),
            false,
            true,
            false,
            Util.newHashMapOfValues(),
            Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VULNERABLE, 2))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
//            return (int) Math.max(1, 20 * (source.isLegMovementHindered()?0.1f:1)); // kerambit damage
            return (int) Math.max(1, source.getUnarmedDamage() * 1.2f * (source.isLegMovementHindered()?0.1f:1));
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), false);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getLegType().getFootType().equals(FootType.TALONS), "仅适用于拥有鸟类利爪的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
                    "用鸟类的利爪向" + (target==null?"[npc.her]的目标i":"[npc2.name]") + "发出一记有力的爪击，造成"
                            + getFormattedDamage(damageType, getDamage(turnIndex, source, target, false), target, false, isTargetAtMaximumLust(target)) + "伤害。"
                            + (source.isLegMovementHindered()?"[style.italicsMinorBad(由于衣物阻碍了腿部运动，造成的伤害降低为原来的10%！)]":""));
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source,
                    "[npc.Name]用锐利的鸟爪向目标发出一击有力的爪击，造成"
                            + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + "基础伤害。"
                            + "[style.italicsMinorBad(如果衣物阻碍了腿部运动，造成的伤害将会降低为原来的10%。)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean maxLust = isTargetAtMaximumLust(target);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            Value<String, Integer> critDamageValue = new Value<>("", 0);
            if(isCrit) {
                critDamageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit)/2); // Second slash damage from the crit.
            }

            return formatAttackOutcome(source, target,
                    (source.isLegMovementHindered()
                            ?"由于衣物阻碍了腿部运动，[npc.name]难以利用利爪，只能对[npc2.name]造成极小的伤害……"
                            :"[npc.Name]腾空而起，接着伸直利爪迅速降落，向着[npc2.name]发出了有力的爪击！")
                            +damageValue.getKey(),
                    "[npc2.Name]受到了" + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + "伤害！",
                    (isCrit
                            ?"[npc.Name]又迅速接上一下，试图突破[npc2.namePos]的格挡！"+critDamageValue.getKey()
                            :null),
                    "[npc2.Name]受到了额外" + getFormattedDamage(damageType, critDamageValue.getValue(), target, true, maxLust) + "伤害！");
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues(
                    "利爪的攻击突破了"+(target!=null?UtilText.parse(target,"[npc.namePos]"):"目标")+"的护盾。");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            int damage = getDamage(turnIndex, source, target, false);
            int potentialDamage = getDamageType(turnIndex, source).shieldCheckNoDamage(source, target, damage);
            if(potentialDamage>0) {
                return true;
            }
            return false;
        }
    };
}
