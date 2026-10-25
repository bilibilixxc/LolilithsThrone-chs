package com.lilithsthrone.LolificationProject.Lexi.combat;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.moves.CombatMoveCategory;
import com.lilithsthrone.game.combat.moves.CombatMoveType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

import java.util.List;

public class LexiSpecialAttack {
    public static AbstractCombatMove TAIL_SPIKE_LAUNCH = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "tail spike launch",
            2,
            3,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/tail_swipe",
            false,
            true,
            false,
            Util.newHashMapOfValues(new Util.Value<AbstractStatusEffect, Integer>(StatusEffect.DAZED, 2))) {
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
        public Util.Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Util.Value<>(
                    (source.getTailType().isSuitableForAttack() && source.getTailLength(false)>=100)
                            || source.getLegConfiguration()== LegConfiguration.TAIL_LONG,
                    "可用于拥有合适的尾巴且尾巴长度至少为 [units.sizeShort(100)] 的角色，或拥有 '"+LegConfiguration.TAIL_LONG.getName()+"' 型下半身的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
                    "向 " + (target==null?"[npc.her] 目标":"[npc2.name]") + " 挥出雷霆般的一记尾击，造成 "
                            + getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " 点伤害。");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source,
                    "[npc.Name] 可以用 [npc.her] [npc.tailRace] 尾巴向 [npc.her] 目标挥出雷霆一击，造成基础 " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " 点伤害。");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Util.Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));

            return formatAttackOutcome(source, target,
                    "[npc.Name] [npc.verb(turn)] 侧过身，借助惯性将 [npc.her] 巨大的 [npc.tailRace] 尾巴径直抽向 [npc2.name]！"+damageValue.getKey(),
                    "[npc2.Name] 受到了 " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " 点伤害！",
                    (isCrit
                            ?"[npc.NamePos] 的尾击格外凌厉有效！"
                            :null),
                    "'" + StatusEffect.DAZED.getName(target) + "' 的持续时间翻倍！");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
            return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues(
                    (source!=null?UtilText.parse(source,"[npc.NamePos]"):"攻击者的")+" 尾巴至少要有 '<span style='color:"+ PenetrationGirth.FIVE_THICK.getColour().toWebHexString()+";'>"+PenetrationGirth.FIVE_THICK.getName()+"</span>'。");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(source.getTailGirth().getValue()>=PenetrationGirth.FIVE_THICK.getValue()) {
                return true;
            }
            return false;
        }
    };
}