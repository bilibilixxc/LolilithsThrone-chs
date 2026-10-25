package com.lilithsthrone.LolificationProject.combat;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.moves.CombatMoveCategory;
import com.lilithsthrone.game.combat.moves.CombatMoveType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

import java.util.List;

public class NewCombatMoves {
    public static final AbstractCombatMove hugAttack = new AbstractCombatMove(CombatMoveCategory.BASIC,
            "hug",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "moves/tease",
            false,
            true,
            false,
            null) {

        public int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 10;
        }

        public int getDamage(GameCharacter source, GameCharacter target, boolean critical) {
            return Attack.calculateSeductionDamage(source, target, getBaseDamage(source, critical), critical);
        }

        @Override
        public Util.Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Util.Value<>(true, "基础招式，所有角色均可使用。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            return UtilText.parse(source, target,
                    (isCrit ? "[style.colourExcellent(暴击)]: " : "")
                            + "<span style='color:" + this.getColour().toWebHexString() + ";'>拥抱</span> "
                            + " [npc2.name]，造成" + getFormattedDamage(getDamageType(turnIndex, source), getDamage(source, target, isCrit), target, false, target.getLust() == 1f) + "点伤害。");
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return "拥抱你的敌人，对其造成"
                    + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false)
                    + "点基础伤害。";
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            StringBuilder sb = new StringBuilder();

            DamageType finalDt = getDamageType(turnIndex, source);

            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            Util.Value<String, Integer> damageValue = getDamageType(turnIndex, source).damageTarget(source, target, getDamage(source, target, isCrit));
            int lustDamage = damageValue.getValue();

            sb.append(formatAttackOutcome(source, target,
                    getHugDescription(target),
                    "[npc2.Name]受到了" + getFormattedDamage(finalDt, lustDamage, target, true, target.getLust() == 1f) + "点伤害！",
                    isCrit ? "" : null,
                    isCrit ? "[npc2.Name][npc2.verb(feel)]极其兴奋！" : ""));

            if (source.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION)) {
//                Combat.addStatusEffectToApply(target, StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED, 3);
                sb.append(Spell.getBasicStatusEffectApplication(target, false, Util.newHashMapOfValues(new Util.Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED, 2))));
            }

            return sb.toString();
        }
    };

    private static String getHugDescription(GameCharacter target) {
        return UtilText.returnStringAtRandom(
                "[npc.Name]跑到[npc2.name]面前，给了对方一个大大的拥抱",
                "[npc.Name]跑到[npc2.name]面前给了对方一个大大的拥抱，同时揉捏着[npc2.her] [npc2.ass+]并[npc.moansVerb]，"
                        + (target.getAppearsAsGender().isFeminine()
                        ? "[npc.speech(你不过来试试吗？)]"
                        : "[npc.speech(我需要鸡巴！)]")/*,
                "[npc.Name] ran up to [npc2.name] before giving them a big hug, whilst using both hands to grope and spread apart [npc.her] ass cheeks as [npc.she] [npc.moansVerb], "
                        + (target.getAppearsAsGender().isFeminine()
                        ?"[npc.speech(Come and play!)]"
                        :"[npc.speech(Please, I want your cock in my ass!)]"),
                "[npc.Name] ran up to [npc2.name] before giving them a big hug, whilst spreading apart [npc.her] ass cheeks with both hands as [npc.she] [npc.moansVerb], "
                        + (target.getAppearsAsGender().isFeminine()
                        ?"[npc.speech(You know you want a go!)]"
                        :"[npc.speech(My slutty asshole <i>needs</i> your cock!)]")*/);
    }
}