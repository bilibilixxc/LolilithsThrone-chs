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
                    "Available to characters who have a suitable tail which is at least [units.sizeShort(100)] long, or a '"+LegConfiguration.TAIL_LONG.getName()+"' lower body.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
                    "Deliver a thunderous tail-smack to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
                            + getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " damage.");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source,
                    "[npc.Name] can use [npc.her] [npc.tailRace]-tail to deliver a thunderous smack to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " damage.");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Util.Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));

            return formatAttackOutcome(source, target,
                    "[npc.Name] [npc.verb(turn)] to one side, using the momentum to smack [npc.her] huge [npc.tailRace]-tail straight into [npc2.name]!"+damageValue.getKey(),
                    "[npc2.Name] took " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " damage!",
                    (isCrit
                            ?"[npc.NamePos] tail swipe is particularly effective!"
                            :null),
                    "The duration of '"+StatusEffect.DAZED.getName(target)+"' is doubled!");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
            return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues(
                    (source!=null?UtilText.parse(source,"[npc.NamePos]"):"Attacker's")+" tail is at least '<span style='color:"+ PenetrationGirth.FIVE_THICK.getColour().toWebHexString()+";'>"+PenetrationGirth.FIVE_THICK.getName()+"</span>'.");
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
