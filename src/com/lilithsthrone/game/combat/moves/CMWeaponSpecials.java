package com.lilithsthrone.game.combat.moves;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.7.8
 * @version 0.3.7.8
 * @author Innoxia
 */
public class CMWeaponSpecials {
	
	public static AbstractCombatMove MKAR_MAG_DUMP = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "清空弹匣",
            2,
            2,
            CombatMoveType.ATTACK,
            DamageType.PHYSICAL,
            "moves/mag_dump",
            Util.newArrayListOfValues(PresetColour.BASE_ORANGE),
            false,
            true,
            false,
			null) {
		private int getBulletDamage() {
			return 21_000;
		}
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasWeaponEquipped(WeaponType.getWeaponTypeFromId("innoxia_gun_mkar")), "仅适用于装备了MKAR的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"全自动清空整个30发弹匣，每一发命中的子弹造成"+getFormattedDamage(damageType, getBulletDamage(), target, false, isTargetAtMaximumLust(target))+"伤害。"
            				+ "<br/>[style.italicsGood(目标为所有敌人！)]");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以转为全自动，对敌人清空整个30发弹匣，每一发命中的子弹造成"+getFormattedDamage(damageType, getBulletDamage(), null, false, false)+"伤害。"
            				+ "<br/>[style.italicsGood(目标为所有敌人！)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            
            StringBuilder attackDesc = new StringBuilder();
            StringBuilder attackEffects = new StringBuilder();
            
            int bulletsHit = 30 - 5 - Util.random.nextInt(6); // Hit with 20-25 bullets.
            int bulletsPerEnemy = bulletsHit/enemies.size();
            
            String weaponName = "";
    		for(AbstractWeapon weapon : source.getMainWeaponArray()) {
    			if(weapon.getCombatMoves().contains(this)) {
    				weaponName = weapon.getName();
    				break;
    			}
    		}
    		if(weaponName.isEmpty()) {
	    		for(AbstractWeapon weapon : source.getOffhandWeaponArray()) {
	    			if(weapon.getCombatMoves().contains(this)) {
	    				weaponName = weapon.getName();
	    				break;
	    			}
	    		}
    		}
    		if(weaponName.isEmpty()) {
    			weaponName = "步枪";
    		}
            
        	attackDesc.append(UtilText.parse(source,
        			"[npc.Name]将"+weaponName+"的选择掣推至全自动位，瞄准后扣动了扳机，释放出震耳欲聋的枪林弹雨，向着"
        				+(enemies.size()==1?UtilText.parse(target, "[npc.name]"):"[npc.her]的敌人")+"而去！"));
            for(int i=0; i<enemies.size(); i++) {
            	GameCharacter enemy = enemies.get(i);
            	int finalBullets = Math.max(1, bulletsPerEnemy-Util.random.nextInt(3));
            	boolean maxLust = isTargetAtMaximumLust(target);
                Value<String, Integer> damageValue = damageType.damageTarget(source, enemy, getBulletDamage()*finalBullets);
            	attackDesc.append(UtilText.parse(enemy,
            			"<br/>[npc.NameIsFull]被[style.boldTerrible("+finalBullets+")]发子弹命中"+"！"+damageValue.getKey()));
            	if(i>0) {
            		attackEffects.append("<br/>");
            	}
            	attackEffects.append(UtilText.parse(enemy, "[npc.Name]受到了"+getFormattedDamage(damageType, damageValue.getValue(), enemy, true, maxLust)+"伤害！"));
            }
            
            return formatAttackOutcome(source, target,
            		attackDesc.toString(),
            		attackEffects.toString(),
            		null,
                	null);
        }
    };
    

	public static AbstractCombatMove BR14_MAG_DUMP = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "清空弹匣",
            2,
            2,
            CombatMoveType.ATTACK,
            DamageType.PHYSICAL,
            "moves/mag_dump",
            Util.newArrayListOfValues(PresetColour.BASE_ORANGE),
            false,
            true,
            false,
			null) {
		private int getBulletDamage() {
			return 26_000;
		}
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasWeaponEquipped(WeaponType.getWeaponTypeFromId("innoxia_gun_br14")), "仅适用于装备了BR14的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"全自动清空整个20发弹匣，每一发命中的子弹造成"+getFormattedDamage(damageType, getBulletDamage(), target, false, isTargetAtMaximumLust(target))+"伤害。"
            				+ "<br/>[style.italicsGood(目标为所有敌人！)]");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以转为全自动，对敌人清空整个20发弹匣，每一发命中的子弹造成"+getFormattedDamage(damageType, getBulletDamage(), null, false, false)+"伤害。"
            				+ "<br/>[style.italicsGood(目标为所有敌人！)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            
            StringBuilder attackDesc = new StringBuilder();
            StringBuilder attackEffects = new StringBuilder();
            
            int bulletsHit = 20 - 5 - Util.random.nextInt(11); // Hit with 5-15 bullets.
            int bulletsPerEnemy = bulletsHit/enemies.size();

            String weaponName = "";
    		for(AbstractWeapon weapon : source.getMainWeaponArray()) {
    			if(weapon.getCombatMoves().contains(this)) {
    				weaponName = weapon.getName();
    				break;
    			}
    		}
    		if(weaponName.isEmpty()) {
	    		for(AbstractWeapon weapon : source.getOffhandWeaponArray()) {
	    			if(weapon.getCombatMoves().contains(this)) {
	    				weaponName = weapon.getName();
	    				break;
	    			}
	    		}
    		}
    		if(weaponName.isEmpty()) {
    			weaponName = "步枪";
    		}
    		
        	attackDesc.append(UtilText.parse(source,
        			"[npc.Name]将"+weaponName+"的选择掣拨向全自动位，瞄准后扣动了扳机，释放出震耳欲聋的枪林弹雨，向着"
        				+(enemies.size()==1?UtilText.parse(target, "[npc.name]"):"[npc.her]的敌人")+"而去！"));
            for(int i=0; i<enemies.size(); i++) {
            	GameCharacter enemy = enemies.get(i);
            	int finalBullets = Math.max(1, bulletsPerEnemy-Util.random.nextInt(3));
            	boolean maxLust = isTargetAtMaximumLust(target);
                Value<String, Integer> damageValue = damageType.damageTarget(source, enemy, getBulletDamage()*finalBullets);
            	attackDesc.append(UtilText.parse(enemy,
            			"<br/>[npc.NameIsFull]被[style.boldTerrible("+finalBullets+")]发子弹命中"+"！"+damageValue.getKey()));
            	if(i>0) {
            		attackEffects.append("<br/>");
            	}
            	attackEffects.append(UtilText.parse(enemy, "[npc.Name]受到了"+getFormattedDamage(damageType, damageValue.getValue(), enemy, true, maxLust)+"伤害！"));
            }
            
            return formatAttackOutcome(source, target,
            		attackDesc.toString(),
            		attackEffects.toString(),
            		null,
                	null);
        }
    };
    
    public static AbstractCombatMove FAUXMAS_MAG_DUMP = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "清空弹匣",
            2,
            2,
            CombatMoveType.ATTACK,
            DamageType.PHYSICAL,
            "moves/mag_dump",
            Util.newArrayListOfValues(PresetColour.BASE_ORANGE),
            false,
            true,
            false,
			null) {
		private int getBulletDamage() {
			return 18_000;
		}
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasWeaponEquipped(WeaponType.getWeaponTypeFromId("innoxia_gun_famase")), "仅适用于装备了法格玛斯的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"全自动清空整个25发弹匣，每一发命中的子弹造成"+getFormattedDamage(damageType, getBulletDamage(), target, false, isTargetAtMaximumLust(target))+"伤害。"
            				+ "<br/>[style.italicsGood(目标为所有敌人！)]");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以转为全自动，对敌人清空整个25发弹匣，每一发命中的子弹造成"+getFormattedDamage(damageType, getBulletDamage(), null, false, false)+"伤害。"
            				+ "<br/>[style.italicsGood(目标为所有敌人！)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            
            StringBuilder attackDesc = new StringBuilder();
            StringBuilder attackEffects = new StringBuilder();
            
            int bulletsHit = 25 - 5 - Util.random.nextInt(6); // Hit with 15-20 bullets.
            int bulletsPerEnemy = bulletsHit/enemies.size();
            
            String weaponName = "";
    		for(AbstractWeapon weapon : source.getMainWeaponArray()) {
    			if(weapon.getCombatMoves().contains(this)) {
    				weaponName = weapon.getName();
    				break;
    			}
    		}
    		if(weaponName.isEmpty()) {
	    		for(AbstractWeapon weapon : source.getOffhandWeaponArray()) {
	    			if(weapon.getCombatMoves().contains(this)) {
	    				weaponName = weapon.getName();
	    				break;
	    			}
	    		}
    		}
    		if(weaponName.isEmpty()) {
    			weaponName = "步枪";
    		}
            
        	attackDesc.append(UtilText.parse(source,
        			"[npc.Name]将"+weaponName+"的选择掣推至全自动位，瞄准后扣动了扳机，释放出震耳欲聋的枪林弹雨，向着"
        				+(enemies.size()==1?UtilText.parse(target, "[npc.name]"):"[npc.her]的敌人")+"！"));
            for(int i=0; i<enemies.size(); i++) {
            	GameCharacter enemy = enemies.get(i);
            	int finalBullets = Math.max(1, bulletsPerEnemy-Util.random.nextInt(3));
            	boolean maxLust = isTargetAtMaximumLust(target);
                Value<String, Integer> damageValue = damageType.damageTarget(source, enemy, getBulletDamage()*finalBullets);
            	attackDesc.append(UtilText.parse(enemy,
            			"<br/>[npc.NameIsFull]被[style.boldTerrible("+finalBullets+")]发子弹命中"+"！"+damageValue.getKey()));
            	if(i>0) {
            		attackEffects.append("<br/>");
            	}
            	attackEffects.append(UtilText.parse(enemy, "[npc.Name]受到了"+getFormattedDamage(damageType, damageValue.getValue(), enemy, true, maxLust)+"伤害！"));
            }
            
            return formatAttackOutcome(source, target,
            		attackDesc.toString(),
            		attackEffects.toString(),
            		null,
                	null);
        }
    };

    public static AbstractCombatMove INKY_SUMMON = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "召唤墨墨",
            100,
            1,
            CombatMoveType.ATTACK,
            DamageType.LUST,
            "statusEffects/inky_summon",
            Util.newArrayListOfValues(PresetColour.BASE_PURPLE),
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.INKY_ATTACK, 6))) {
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasWeaponEquipped(WeaponType.getWeaponTypeFromId("innoxia_pen_inky")), "仅适用于装备了“墨墨的钢笔”的角色。");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return UtilText.parse(source, target,
            		"对着[npc2.name]释放出墨墨。这只小奥术实体每回合会愉悦地造成"+getFormattedDamage(DamageType.LUST, 15, null, false, false)+"点伤害，同时会使[npc2.her]的行动点减少1点！");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            return UtilText.parse(source,
            		"对着目标释放出墨墨。这只小奥术实体每回合会愉悦地造成"+getFormattedDamage(DamageType.LUST, 15, null, false, false)+"点伤害，同时会使目标的行动点减少1点！");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            
            return formatAttackOutcome(source, target,
            		"[npc.Name]将笔帽转了三圈，然后将它拔开，释放出某种非常特殊的物质。"
            				+ "一团紫色的液体从露出的笔尖上滴落，迅速膨胀成一个章鱼一样的小生物。"
            				+ "这只被称为墨墨的奥术生命悬浮在空中，眼睛里充满了爱心，它盯着[npc2.name]，发出一小声快乐的呻吟。"
            				+ "<br/><br/>"
            				+ "墨墨径直飞向[npc2.name]，用触手环抱住[npc2.herHim]，开始下流地揉捏爱抚起来，发出了更多快乐的叫声。"
            				+ "很显而易见的是，[npc2.namePos]将这只奥术生物从[npc2.herHim]自己身上拉下来的任何尝试都注定要失败。"
            				+ "每当[npc2.she]试图抓住这只小章鱼，[npc2.her]的[npc.hands]都会从它的流体身躯上滑开，引得这只下流生物发出可爱的咯咯笑声。"
            				+ "<br/><br/>"
            				+ "尽管墨墨的触手既令人分心又让人兴奋，但[npc2.name]却对它毫无办法，"
            					+ "只好尽可能地无视它，等待它耗尽奥术能量……",
            		"墨墨现在全神贯注地对[npc2.name]释放情欲！",
            		null,
                	null);
        }
    };
}
