package com.lilithsthrone.game.combat.moves;

import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishLevel;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4.4.2
 * @author Innoxia
 */
public class CMFetishAttack {
	
	private static int getFetishAttackBaseDamage(AbstractFetish associatedFetish, GameCharacter source, boolean isCrit) {
		int baseDamage = 3;
		FetishLevel fetishLevel = source.getFetishLevel(associatedFetish);
		baseDamage += fetishLevel.getBonusTeaseDamage();
        return baseDamage * (isCrit?3:1);
	}
	
	public static AbstractCombatMove TEASE_ANAL_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "屁穴荡妇挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_anal_receiving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_ANAL_RECEIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_ANAL_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(暴击)]: ":"")
            		+ "[npc.Name]允许[npc2.name]使用[npc.her]的屁眼，造成"+getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target))+"伤害。"
    				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
					(UtilText.returnStringAtRandom(
						"[npc.Name]转过身，向[npc2.name]展示[npc.her][npc.ass+]，拍了一巴掌并发出[npc.moaning]，"
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(你想来挤挤吗？)]"
									:"[npc.speech(好空虚！我的屁穴正渴望着你的鸡巴！)]"),
						"[npc.Name]转过身，挤压并摸索[npc.her][npc.ass+]，[npc.moansVerb]，"
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(为什么你不来试试？)]"
									:"[npc.speech(我的屁穴需要鸡巴！)]"),
						"[npc.Name]转过身，双手不断摸索并分开[npc.her]的臀颊，[npc.moansVerb], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(来玩嘛！)]"
									:"[npc.speech(求你了，我想要你的鸡巴插入我的屁穴！)]"),
						"[npc.Name]转过身，双手分开[npc.her]的屁穴，[npc.moansVerb]，"
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(你知道你想来试试！)]"
									:"[npc.speech(我的淫屁穴<i>想要</i>你的鸡巴！)]")))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
                	"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_ANAL_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "肛门挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_anal_giving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_ANAL_GIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_ANAL_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(暴击)]: ":"")
            			+ "[npc.Name]告诉[npc2.name][npc.sheIs]会使用[npc2.her]的屁穴，造成"+getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target))+"伤害。"
            			+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name]朝[npc2.name]咧嘴一笑，[npc.her]的目光移动到[npc2.her][npc2.ass+]，发出[npc.moaning]，"
    							+"[npc.speech(你的屁穴看起来需要好好操操！)]",
    						"[npc.Name]饥渴地盯着[npc2.namePos][npc2.ass+]，[npc.moaning]，"
    							+"[npc.speech(我要狠狠地操你的屁穴！)]",
    						"[npc.name]色迷迷地凝视着[npc2.namePos][npc2.ass+]，发出[npc.a_moan+]，"
    							+"[npc.speech(我要把那可爱的屁股打得稀巴烂！)]"))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
                	"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };

    public static AbstractCombatMove TEASE_VAGINAL_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "淫穴荡妇挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_vaginal_receiving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_VAGINAL_RECEIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_VAGINAL_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasVagina(), "仅适用于同时拥有小穴和"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(暴击)]: ":"")
            			+ "[npc.Name]允许[npc2.name]使用[npc.her]的淫穴，造成"+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            			+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }
        
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成"
            				+ getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name]的[npc.hands]在[npc.legs]间抚动，[npc.moansVerb]，"
								+ "[npc.speech(我的淫穴渴望你的抚摸！)]",
						"[npc.Name]的一只[npc.hand]滑到[npc.legs]间，[npc.moansVerb]，"
								+ "[npc.speech(过来操我的小穴！)]",
						"[npc.Name][npc.hips+]微微一挺，[npc.moansVerb]，"
								+ "[npc.speech(我已经湿了！快来操我的小淫穴！)]",
						"[npc.Name]向[npc2.name]眨眨眼，[npc.moansVerb]，"
								+ "[npc.speech(我的淫穴<i>想要</i>一点关注！)]"))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
                	"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };

    public static AbstractCombatMove TEASE_VAGINAL_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "阴道挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_vaginal_giving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_VAGINAL_GIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_VAGINAL_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).hasVagina()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasVagina() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(暴击)]: ":"")
		            		+ "[npc.Name]告诉[npc2.name][npc.sheIs]会使用[npc2.her]的小穴，造成"+getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target))+"伤害。"
	            			+ (!target.hasVagina()
            					?"[style.italicsMinorBad([npc2.name]没有阴道！伤害降低至1！)]"
            					:(target.getFetishDesire(oppositeFetish).isNegative()
                    					?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]"
                            			:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标没有阴穴，或者他们不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(!target.hasVagina() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name]朝[npc2.name]咧嘴一笑，[npc.her]的目光移动到[npc2.her]的[npc2.legs]间，发出[npc.moaning]，"
							+"[npc.speech(你的小穴看起来需要好好操操！)]",
						"[npc.Name]饥渴地盯着[npc2.namePos][npc2.legs]间，[npc.moaning]，"
							+"[npc.speech(我要狠狠地操你的小穴！)]",
						"[npc.name]色迷迷地凝视着[npc2.namePos][npc2.legs]间，发出[npc.a_moan+]，"
							+"[npc.speech(我要把那可爱的小穴操得稀巴烂！)]"))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };

    public static AbstractCombatMove TEASE_INCEST = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "乱伦挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_incest",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_INCEST;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_INCEST;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).isRelatedTo(source)) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.isRelatedTo(source) || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            if(target.isRelatedTo(source)) {
	            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(暴击)]: ":"")
	            			+ "[npc.Name]告诉[npc2.name]，[npc.sheIs]要和[npc2.herHim]来一场禁断之操，造成"
	            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
	            			+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
            } else {
	            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(暴击)]: ":"")
	            			+ "[npc.Name]告诉[npc2.name][npc.she]喜欢乱伦，造成了"
	            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ "[style.italicsMinorBad(因为[npc2.name]与你无关！伤害降低为1！)]");
            }
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标与你没有关系，或他们对“"+oppositeFetish.getName(null)+"”感到厌恶，伤害将降低为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(!target.isRelatedTo(source) || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            if(target.isRelatedTo(source)) {
    			String dialogue = "";
    			Set<Relationship> rel = source.getRelationshipsTo(target);
    			if(rel.contains(Relationship.Child)) {
    				dialogue = UtilText.returnStringAtRandom(
    						"让我来好~好~地~照顾你吧， [npc.mommy]！",
    						"拜托，[npc2.mommy]！我只是想<i>真切</i>地照顾好你！",
    						"[npc2.Mommy]！！我只是想让你明白我有多么爱你！");
    			} else if(rel.contains(Relationship.Parent)) {
    				dialogue = UtilText.returnStringAtRandom(
    						"让[npc.mommy]来好~好~地~疼爱你吧！",
    						"别担心亲爱的，[npc.mommy]会好~好~地~疼爱你的！",
    						"[npc.Mommy]只是想让你明白[npc.she]有多爱你！");
    			} else if(rel.contains(Relationship.Sibling)) {
    				dialogue = UtilText.returnStringAtRandom(
    						"让你的[npc.sis]来好~好~地~疼爱你吧！",
    						"别担心[npc2.sis]，我会好~好~地~疼爱你的！",
    						"拜托，[npc2.sis]！我只是想让你明白我有多爱你！");
    			} else {
    				dialogue = UtilText.returnStringAtRandom(
    						"让[npc.mommy]来好~好~地~疼爱你吧！",
    						"别担心亲爱的，[npc.mommy]会好~好~地~疼爱你的！",
    						"[npc.Mommy]只是想让你明白[npc.she]有多爱你！");
    			}
            	
                return formatAttackOutcome(source, target,
                		(UtilText.returnStringAtRandom(
        						"[npc1.Name]朝[npc2.name]咧嘴一笑，将[npc1.hips]一顶，叫喊道："
        								+ "[npc1.speech("+dialogue+")]",
        						"[npc1.name]将[npc1.hands]下到自己的腹股沟，呼唤着[npc2.name]，"
        								+ "[npc1.speech("+dialogue+")]",
        						"[npc1.Name]将[npc1.hips]一顶，朝着[npc2.name]发出一阵渴望的呻吟，"
        								+ "[npc1.speech("+dialogue+")]"))+dealtDamage.getKey(),
                		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
                		(isCrit
                			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
                			:null),
                		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
            	
            	
            } else {
                return formatAttackOutcome(source, target,
                		(UtilText.returnStringAtRandom(
        						"[npc1.Name]朝[npc2.name]咧嘴一笑，将[npc1.hips]一顶，叫喊道："
        								+ "[npc1.speech(我好骚啊，最爱和亲戚上床！想想我会对你做什么吧！)]",
        						"[npc1.name]将[npc1.hands]下到自己的腹股沟，呼唤着[npc2.name]，"
        								+ "[npc1.speech(可惜你不是我的[npc2.sister]什么的；没有什么比德国骨科更刺激的了！)]",
        						"[npc1.Name]将[npc1.hips]一顶，朝着[npc2.name]发出一阵渴望的呻吟，"
        								+ "[npc1.speech(真希望你是我的亲人；最爱乱伦！)]"))+dealtDamage.getKey(),
                		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
                		(isCrit
                			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
                			:null),
                		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
            }
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标")+"有"+oppositeFetish.getName(target)+"性癖与你相关。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish) && target.isRelatedTo(source);
        }
    };
    
    public static AbstractCombatMove TEASE_CUM_STUD = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "精液公畜挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_cum",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_CUM_STUD;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_CUM_ADDICT;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
            				+ "[npc.Name]告诉[npc2.name]，要给[npc2.herHim]尝尝自己的精液，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name]朝[npc2.name]咧嘴一笑，将[npc.hips]一顶，叫喊道："
    								+ "[npc.speech(要被我的[npc.cum]灌满啦。真等不及了！)]",
    						"[npc.her]的[npc.hands]在胯部摸来摸去，[npc.name]向[npc2.name]呼喊，"
    								+ "[npc.speech(我猜，你也迫不及待想尝尝我的[npc.cum]吧！)]",
    						"[npc.Name]的[npc.hips]向[npc2.name]一顶，发出一阵渴望的呻吟，"
    								+ "[npc.speech(我等不及用[npc.cum]填满你啦！)]"))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_CUM_ADDICT = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "精液成瘾挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_cum_addict",
            Util.newArrayListOfValues(PresetColour.CLOTHING_WHITE),
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_CUM_ADDICT;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_CUM_STUD;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).hasPenis()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasPenisIgnoreDildo() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
    	            		+ "[npc.Name]告诉[npc2.name][npc.she]想要[npc2.her]的精液，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (!target.hasPenisIgnoreDildo()
            						?"[style.italicsMinorBad([npc2.name]没有阴茎！伤害降低至1！)]"
            						:(target.getFetishDesire(oppositeFetish).isNegative()
                    						?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]"
                            				:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标没有阴茎，或者他们不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(!target.hasPenisIgnoreDildo() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name]发出一声[npc.a_moan+]，表现[npc.her]的精液渴望。"
    								+ "[npc.speech(啊呜！我需要精液！我好久没吃了呢！)]",
    						"[npc.Name]发出饥渴的哀鸣，恳求道："
    								+ "[npc.speech(呜姆姆！我想要精液！)]",
    						"[npc.Name]发出一阵可怜的呜叫，恳求道："
    								+ "[npc.speech(我饿得不行啦！把精液都射给我嘛！)]",
    						"[npc.Name]发出一阵可怜的呜叫，恳求尝尝，"
    								+ "[npc.speech(求你了！射给我！)]"))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_PENIS_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "阴茎上瘾挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_cock_addict",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_PENIS_RECEIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_PENIS_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).hasPenis()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasPenisIgnoreDildo() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
    	            		+ "[npc.Name]祈求尝尝[npc2.namePos]的鸡巴，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (!target.hasPenisIgnoreDildo()
            						?"[style.italicsMinorBad([npc2.name]没有阴茎！伤害降低至1！)]"
            						:(target.getFetishDesire(oppositeFetish).isNegative()
                    						?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]"
                            				:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标没有阴茎，或者他们不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(!target.hasPenisIgnoreDildo() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name]盯着[npc2.namePos]的[npc2.legs]间，发出一阵呻吟，[npc.speech(我等不急要尝尝你的[npc2.cock]了！)]",
						"[npc.Name]饥渴地盯着[npc2.namePos][npc2.legs]间，发出一阵呻吟：[npc.speech(真想让你的鸡巴在我里面抽插啊！)]",
						"[npc.Name]向[npc2.name]咧嘴一笑，舔了舔[npc.her][npc.lips+]，向下盯着[npc2.namePos]的胯部，发出一阵声音，[npc.speech(我会好好照顾你的鸡巴的！)]"))
            			+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_PENIS_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "阴茎公畜挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_dick_dealer",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_PENIS_GIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_PENIS_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasPenisIgnoreDildo(), "仅适用于同时拥有阴茎和"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(暴击)]: ":"")
	            			+ "[npc.Name]告诉[npc2.name]，要给[npc2.herHim]尝尝[npc.her][npc.cock+]，造成"
	            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name]的[npc.a_hand]滑到[npc.her]的[npc.legs]间，抓着胯部并呻吟道：[npc.speech(等不及超超你了！)]",
						"[npc.Name]的[npc.hips]向[npc2.name]一顶，发出一阵呻吟，[npc.speech(来尝尝我的鸡巴！)]",
						"[npc.Name]向[npc2.name]咧嘴一笑，发出一阵呻吟，[npc.speech(你会爱上我的鸡巴！)]"))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_LEG_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "秀腿者挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_strutter",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_STRUTTER;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_LEG_LOVER;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasPenisIgnoreDildo(), "仅适用于同时拥有大腿和"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(暴击)]: ":"")
            			+ "[npc.Name]让[npc2.name]用[npc.her]的腿，造成"+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            			+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }
        
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成"
            				+ getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name]的[npc.hands]在[npc.legs]间抚动，[npc.moansVerb]，"
								+ "[npc.speech(我的腿好想被你摸摸哦！)]",
						"[npc.Name]一只[npc.hand]滑到下面，摸着[npc.her]的[npc.legs]，[npc.moansVerb]，"
								+ "[npc.speech(来操我的腿缝嘛！)]",
						"[npc.Name]举起一条[npc.legs+]，[npc.moansVerb]，"
								+ "[npc.speech(和我的腿还有腿缝操一操吧！)]",
						"[npc.Name]向[npc2.name]眨眨眼，[npc.moansVerb]，"
								+ "[npc.speech(我的腿缝<i>想要</i>更多关注！)]"))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
                	"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_LEG_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "恋腿者挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_leg_lover",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_LEG_LOVER;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_STRUTTER;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasLegs() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(暴击)]: ":"")
		            		+ "[npc.Name]告诉[npc2.name]，[npc.sheIs]要用用[npc2.her]的[npc2.legs]，"
		            		+ "造成"+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
	            			+ (!target.hasLegs()
            					?"[style.italicsMinorBad(由于[npc2.name]没有腿，伤害降低至1！)]"
            					:(target.getFetishDesire(oppositeFetish).isNegative()
                    					?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]"
                            			:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标没有腿，或者他们不喜欢"+oppositeFetish.getName(null)+" 性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(!target.hasLegs() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name]朝[npc2.name]咧嘴一笑，[npc.her]的目光移动到[npc2.her]的[npc2.legs]上，发出[npc.moaning]，"
							+"[npc.speech(你的腿窝看起来需要好好操操！)]",
						"[npc.Name]饥渴地盯着[npc2.namePos]的[npc2.legs]，[npc.moaning]，"
							+"[npc.speech(我要狠狠地操你的腿！)]",
						"[npc.name]色迷迷地凝视着[npc2.namePos][npc2.legs]，发出[npc.a_moan+]，"
							+"[npc.speech(我要把这可爱的腿砸到地上！)]"))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
	public static AbstractCombatMove TEASE_FOOT_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "顺从之足挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_foot_receiving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_FOOT_RECEIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_FOOT_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
            				+ "[npc.Name]祈求[npc2.name]用[npc2.her]的脚，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name]饥渴地盯着[npc2.namePos]的[npc2.feet]，[npc.moanVerb]，[npc.speech(我等不及体验你的脚在我身上的感觉了！)]",
    						"[npc.Name]咬住[npc.lip]，凝视着[npc2.namePos]的[npc2.feet]，[npc.speech(让我膜拜你的[npc2.feet]吧！)]",
    						"[npc.Name]冲着[npc2.name]笑笑，舔了舔[npc.lips+]，视线下移，停到[npc2.namePos][npc2.feet]上，而后[npc.moanVerb]，"
    								+ "[npc.speech(我只想膜拜你的[npc2.feet]！)]"))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_FOOT_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "支配之足挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_foot_giving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_FOOT_GIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_FOOT_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(暴击)]: ":"")
	            			+ "[npc.Name]告诉[npc2.name]，[npc.sheIs]要用用[npc.her]的[npc.feet]，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name]抬起一条[npc.legs]，将[npc.her]的[npc.foot]伸向[npc2.name]的[npc2.face]。[npc.speech(跪下，然后吻我的[npc.feet]！)]",
    						"[npc.Name]边朝着[npc2.name]伸出一只[npc.feet+]，边冲着[npc2.her][npc.moanVerb]，[npc.speech(你不久就会来舔我的脚趾了！)]",
    						"[npc.Name]冲着[npc2.name]笑笑，[npc.moanVerb]，[npc.speech(爬过来亲吻我的脚吧！)]"))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
	public static AbstractCombatMove TEASE_ARMPIT_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "腋穴荡妇挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_armpit_receiving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_ARMPIT_RECEIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_ARMPIT_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
            				+ "[npc.Name]祈求[npc2.name]用[npc2.her]的腋窝，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name]举起[npc.arms]，[npc.moanVerb]，[npc.speech(等不及让你来使用我的腋窝了！)]",
    						"[npc.Name]咬住[npc.lip]，然后举起[npc.arm]，展示着[npc.her][npc.armpits+]，[npc.speech(你会爱上我的腋窝的！)]",
    						"[npc.Name]冲着[npc2.name]笑笑，舔了舔[npc.lips+]，举起[npc.arm]展示着[npc.her][npc.armpit+]，然后[npc.moanVerb]，"
    								+ "[npc.speech(我只是想让你膜拜我的小洞！)]")
            		)+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_ARMPIT_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "腋窝爱好者挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_armpit_giving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_ARMPIT_GIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_ARMPIT_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(暴击)]: ":"")
	            			+ "[npc.Name]告诉[npc2.name]，[npc.sheIs]要用用[npc.her]的[npc.armpits]，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name]朝[npc2.name]咧嘴一笑，[npc.her]的目光移动到[npc2.her]的[npc2.arms]，发出[npc.moaning]，"
    								+"[npc.speech(你的腋窝看起来需要好好操操！)]",
							"[npc.Name]饥渴地盯着[npc2.namePos]的[npc2.armpits]，[npc.moaning]，"
								+"[npc.speech(想用用你的窝！)]",
							"[npc.name]色迷迷地凝视着[npc2.namePos]的[npc2.arms]，发出[npc.a_moan+]，"
								+"[npc.speech(我等不及要用你性感的小洞爽一爽了！)]")
            		)+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_ORAL_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "口交挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_oral_receiving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_ORAL_RECEIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_ORAL_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
                    		+ "[npc.Name]祈求[npc2.name]给[npc.herHim]口交，造成"
                    			+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name]向[npc2.name]咧嘴一笑，在[npc.she][npc.moansVerb]时，凝视着[npc2.her][npc2.lips+]，"
    							+"[npc.speech(我等不及使用你的[npc2.lips]了！)]",
    						"[npc.Name]饥渴地盯着[npc2.namePos][npc2.lips+]，[npc.moaning]，"
    							+"[npc.speech(你的舌头是属于我[npc.legs]间的！)]",
    						"[npc.name]色迷迷地凝视着[npc2.namePos][npc2.lips+]，发出[npc.a_moan+]，"
    							+"[npc.speech(我会好好使用你的[npc2.lips]哦！)]"))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_ORAL_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "口技服务挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_oral_giving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_ORAL_GIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_ORAL_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
    	            		+ "[npc.Name]告诉[npc2.name]，[npc.sheIs]打算给[npc2.herHim]口交，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name]张开嘴，[npc.tongue]搭在外面，一只[npc.hands]摆出暗示性的手势，"
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(你知道吗，我的舌技很好哦！)]"
    									:"[npc.speech(想知道我能吞多深吗？)]"),
    						"[npc.Name]张开嘴，[npc.tongue]搭在外面，一只[npc.hands]摆出暗示性的手势，"
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(你会爱上我舌头的感觉哦！)]"
    									:"[npc.speech(想把你的鸡巴插进我喉咙里吗？)]"),
    						"[npc.Name]张开嘴，[npc.tongue]搭在外面，一只[npc.hands]摆出暗示性的手势，"
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(我最擅长舔女人下面了哦！要试试吗？)]"
    									:"[npc.speech(我可最擅长口交啦！要试试吗？)]"),
    						"[npc.Name]张开嘴，[npc.tongue]搭在外面，一只[npc.hands]摆出暗示性的手势，"
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(你知道你想试试我的舌头呢！)]"
    									:"[npc.speech(你知道你想嗦我的肉棒吧！)]")))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_BREASTS_OTHERS = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "乳房爱好者挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_breasts_others",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_BREASTS_OTHERS;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_BREASTS_SELF;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).hasBreasts()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasBreasts() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
                    		+ "[npc.Name]祈求摸摸[npc2.namePos][npc2.breasts+]，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (!target.hasBreasts()
            						?"[style.italicsMinorBad(由于[npc2.name]没有乳房，伤害降低至1！)]"
            						:(target.getFetishDesire(oppositeFetish).isNegative()
                    						?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]"
                            				:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标没有乳房，或者他们不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(!target.hasBreasts() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            String attackText = "";
            if(target.hasBreasts()) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
						"[npc.Name]向[npc2.name]咧嘴一笑，在[npc.she][npc.moansVerb]时，凝视着[npc2.her][npc2.breasts+]，"
							+"[npc.speech(我忍不了想摸摸你的[npc2.breasts]了！)]",
						"[npc.Name]饥渴地盯着[npc2.namePos][npc2.breasts+]，[npc.moaning],"
							+"[npc.speech(我要和他们开心开心！)]",
						"[npc.name]色迷迷地凝视着[npc2.namePos][npc2.breasts+]，发出[npc.a_moan+]，"
								+"[npc.speech(我要和你这些[npc2.breasts+]开心开心！)]")));
				
			} else {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
							"凝视着[npc2.namePos]的胸部，[npc.name]发出恼怒地声音，"
									+ "[npc.speech(希望你长了一对漂亮的奶子让我玩玩！)]",
							"[npc.Name]对着[npc2.namePos][npc2.breasts+]发出哀鸣，"
									+ "[npc.speech(嗷……希望你长了些漂亮的奶子让我玩玩！)]")));
			}
            
            return formatAttackOutcome(source, target,
            		attackText+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_BREASTS = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "奶子挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_breasts_self",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_BREASTS_SELF;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_BREASTS_OTHERS;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasBreasts(), "仅适用于同时拥有乳房和"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
            				+ "[npc.Name]挑逗着[npc2.name]，承诺让[npc2.she]摸摸[npc.her]的奶子，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.name]把[npc.breasts+]推在一起，身体前倾，向[npc2.name]眨巴眼，"
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(来一起玩啊~！)]"
    									:"[npc.speech(来嘛，我给你来点胸夹！)]"),
    						"[npc.name]把[npc.breasts+]推在一起，身体前倾，向[npc2.name]眨巴眼，"
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(想找点乐子吗？)]"
    									:"[npc.speech(来嘛，你知道你想要的！)]"),
    						"[npc.her]的[npc.hands]妩媚地在[npc.breasts+]上摸来摸去，[npc.name]轻咬嘴唇，向[npc2.name]撅着嘴，"
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(不想来玩嘛~？)]"
    									:"[npc.speech(我打赌你肯定想来摸摸！)]"),
    						"[npc.her]的[npc.hands]妩媚地在[npc.breasts+]上摸来摸去，[npc.name]轻咬嘴唇，向[npc2.name]撅着嘴，"
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(来嘛！一起快乐快乐！)]"
    									:"[npc.speech(~嗷！~我的奶头硬了~！)]")))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_LACTATION_OTHERS = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "乳汁爱好者挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_lactation_others",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_LACTATION_OTHERS;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_LACTATION_SELF;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || this.getPreferredTarget(source, enemies, allies).getBreastRawMilkStorageValue()==0) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if((target.getBreastRawMilkStorageValue()==0 && (!target.hasBreastsCrotch() || target.getBreastCrotchRawMilkStorageValue()==0))
            	|| target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
            				+ "[npc.Name]祈求尝尝[npc2.namePos][npc2.breasts+]的乳汁，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getBreastRawMilkStorageValue()==0 && (!target.hasBreastsCrotch() || target.getBreastCrotchRawMilkStorageValue()==0)
            						?"[style.italicsMinorBad(由于[npc2.name]没有在泌乳，伤害降低至1！)]"
            						:(target.getFetishDesire(oppositeFetish).isNegative()
                    						?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]"
                            				:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标没有在泌乳，或者他们不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getBreastRawMilkStorageValue()==0 || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));
            }
            
            String attackText = "";
            if(target.hasBreasts()) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
							"[npc.Name]向[npc2.name]咧嘴一笑，在[npc.she][npc.moansVerb]时，凝视着[npc2.her][npc2.breasts+]，"
								+"[npc.speech(我要挤你的奶开心开心！)]",
							"[npc.Name]饥渴地盯着[npc2.namePos][npc2.breasts+]，[npc.moaning]，"
								+"[npc.speech(我忍不了想给你挤挤奶了！)]",
							"[npc.name]色迷迷地凝视着[npc2.namePos][npc2.breasts+]，发出[npc.a_moan+]，"
									+"[npc.speech(我要挤你这些[npc2.breasts+]的奶开心开心！)]")));
				
            } else if(target.hasBreastsCrotch() && target.getBreastCrotchRawMilkStorageValue()>0) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
							"[npc.Name]向[npc2.name]咧嘴一笑，在[npc.she][npc.moansVerb]时，凝视着[npc2.her][npc2.crotchBoobs+]，"
								+"[npc.speech(我要挤你的腹乳开心开心！)]",
							"[npc.Name]饥渴地盯着[npc2.namePos][npc2.crotchBoobs+]，[npc.moaning]，"
								+"[npc.speech(我忍不了想给你挤挤奶了！)]",
							"[npc.name]色迷迷地凝视着[npc2.namePos][npc2.crotchBoobs+]，发出[npc.a_moan+]，"
									+"[npc.speech(我要挤你这些[npc2.breasts+]的奶开心开心！)]")));
            	
			} else {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
							"凝视着[npc2.namePos]的胸部，[npc.name]发出恼怒地声音，"
									+ "[npc.speech(希望你长了一对漂亮的奶子让我挤挤！)]",
							"[npc.Name]对着[npc2.namePos][npc2.breasts+]发出哀鸣，"
									+ "[npc.speech(嗷……希望你长了些漂亮的奶子让我挤挤！)]")));
			}
            
            return formatAttackOutcome(source, target,
            		attackText+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_LACTATION = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "泌乳挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_lactation_self",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_LACTATION_SELF;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_LACTATION_OTHERS;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if((source.getBreastRawMilkStorageValue()==0 && (!source.hasBreastsCrotch() || source.getBreastCrotchRawMilkStorageValue()==0))
            		|| target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && (source.hasBreasts() || source.hasBreastsCrotch()),
            		"角色需拥有乳房且拥有"+associatedFetish.getName(source)+"性癖。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
            				+ "[npc.Name]告诉[npc2.name]，[npc.sheIs]要[npc2.herHim]尝尝[npc.her]的乳汁，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()
            						?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]"
            						:(source.getBreastRawMilkStorageValue()==0 && (!source.hasBreastsCrotch() || source.getBreastCrotchRawMilkStorageValue()==0)
            							?"[style.italicsMinorBad(由于[npc.nameIsFull]没有在泌乳，伤害降低至1！)]"
            							:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果[npc.nameIsFull]没有在泌乳，或者目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            if(source.hasBreasts()) {
                return formatAttackOutcome(source, target,
                		(UtilText.returnStringAtRandom(
        						"[npc.name]把[npc.breasts+]推在一起，身体前倾，向[npc2.name]噘噘嘴，"
        								+ "[npc.speech(我的奶子好涨！来嘛，我需要挤挤！)]",
        						"[npc.name]把[npc.breasts+]推在一起，身体前倾，向[npc2.name]眨巴眼并发出一声哀嚎，"
        								+ "[npc.speech(我的奶要溢出来了！快帮帮我~！)]",
        						"[npc.her]的[npc.hands]妩媚地在[npc.breasts+]上摸来摸去，[npc.name]轻咬嘴唇，向[npc2.name]撅着嘴，"
        								+ "[npc.speech(来嘛，我需要挤挤！)]",
        						"[npc.her]的[npc.hands]妩媚地在[npc.breasts+]上摸来摸去，[npc.name]轻咬嘴唇，向[npc2.name]撅着嘴，"
        								+ "[npc.speech(~嗷！~我的奶要溢出来了！快帮帮我~！)]"))+dealtDamage.getKey(),
                		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
                		(isCrit
                			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
                			:null),
                		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
            	
            } else { // Crotch boobs:
                return formatAttackOutcome(source, target,
                		(UtilText.returnStringAtRandom(
        						"[npc.name]向[npc2.name]展示[npc.her][npc.crotchBoobs+]，噘噘嘴并发出哀嚎，"
        								+ "[npc.speech(我的奶子好涨！来嘛，我需要挤挤！)]",
        						"向[npc2.name]展示[npc.her][npc.crotchBoobs+]，[npc.name]眨眨眼并发出哀嚎，"
        								+ "[npc.speech(我的奶要溢出来了！快帮帮我~！)]",
        						(source.isTaur()
        							?null
        							:"[npc.her]的[npc.hands]妩媚地在[npc.breasts+]上摸来摸去，[npc.name]轻咬嘴唇，向[npc2.name]撅着嘴，"
        								+ "[npc.speech(来嘛，我需要挤挤！)]"),
        						(source.isTaur()
        							?null
        							:"[npc.her]的[npc.hands]妩媚地在[npc.breasts+]上摸来摸去，[npc.name]轻咬嘴唇，向[npc2.name]撅着嘴，"
    									+ "[npc.speech(~嗷！~我的奶要溢出来了！快帮帮我~！)]")))
                				+dealtDamage.getKey(),
                		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
                		(isCrit
                			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
                			:null),
                		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
            	
            }
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_FERTILITY = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "生育挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_pregnancy",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_PREGNANCY;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_IMPREGNATION;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasBreasts(), "仅适用于同时拥有小穴和"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
                    		+ "[npc.Name]告诉[npc2.name][npc.sheIs]有多能生，造成 "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            String attackText = "";
            if(source.isVisiblyPregnant()) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
							(source.isTaur()
								?"[npc.name]转向一边，展露出[npc.her][npc.legRace]身体上鼓起的孕肚，"
								:"[npc.name]用[npc.hands]抚摸着[npc.her]的孕肚，")
							+"[npc.she]向[npc2.name]眨了眨眼，[npc.moansVerb]着说，"
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(让我来告诉你搞大肚子的最佳姿势！)]"
									:"[npc.speech(想不想操一个怀孕[npc.girl]？)]"),
						"[npc.Name]挺起[npc.her]的孕肚，对[npc2.name]咯咯笑，"
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(我会告诉你我是怎么怀孕的！)]"
									:"[npc.speech(操怀孕[npc.girls]是世间美好之事！来嘛，我给你看看！)]"),
						(source.isTaur()
								?"[npc.name]对着[npc2.name]展示自己下半身[npc.legRace]的身体，一边炫耀着[npc.her]的孕肚，一边[npc.moansVerb]着说，"
								:"[npc.name]用[npc.hands]抚摸着[npc.her]的孕肚，向[npc2.name]噘噘嘴，咯咯笑着说，")
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(来感受感受！)]"
									:"[npc.speech(怀孕[npc.girls]是最好玩的！)]"),
						"[npc.name]摆出姿势，吸引[npc2.name]注意到[npc.her]的孕肚，咬了咬嘴唇，[npc.moansVerb]着说，"
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(来嘛，为什么不来找点乐子？！)]"
									:"[npc.speech(想知道操怀孕[npc.girl]有多爽吗？)]"))));
				
			} else {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
						(source.isTaur()
							?"[npc.name]炫耀着[npc.her]平坦的腹部，"
							:"[npc.name]用[npc.hands]抚摸着平坦的腹部，")
							+"[npc.she]咬着[npc.lip]看向[npc2.name]，发出哀鸣，"
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(嗷……我真希望我怀孕了！)]"
									:"[npc.speech(我好想怀孕啊！快来把我灌成奶油泡芙！)]"),
						(source.isTaur()
							?"[npc.name]使对方察觉到自己平坦的腹部，"
							:"[npc.name]用[npc.hands]滑过自己平坦的腹部，")
							+ "[npc.she]对着[npc2.name]噘了噘嘴，[npc.moansVerb]着说，"
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(嗷……我真希望我怀孕了……)]"
									:"[npc.speech(快来把你的孩子们注入我肚子里！)]"),
						(source.isTaur()
							?"[npc.name]炫耀着[npc.her]平坦的腹部，"
							:"[npc.name]用[npc.hands]滑过自己平坦的腹部，")
							+"[npc.she]对着[npc2.name]噘了噘嘴，[npc.moansVerb]着说，"
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(我真希望我怀孕了……)]"
									:"[npc.speech(快来往我子宫里注满你美味的精液！我好想怀孕啊！)]"),
						(source.isTaur()
								?"[npc.name]使对方察觉到自己平坦的腹部，"
								:"[npc.name]用[npc.hands]抚摸着平坦的腹部，")
							+"[npc.she]咬着[npc.lip]看向[npc2.name]，发出哀鸣，"
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(我真希望我怀孕了……)]"
									:"[npc.speech(快来交配！我的子宫在等着你的种子！)]"))));
			}
            
            return formatAttackOutcome(source, target,
            		attackText+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_VIRILITY = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "生殖挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_impregnation",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_IMPREGNATION;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_PREGNANCY;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasBreasts(), "仅适用于同时拥有阴茎和"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
                    		+ "[npc.Name]告诉[npc2.name]，[npc.sheIs]要种种[npc2.herHim]，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            String attackText = "";
			if(target.isVisiblyPregnant()) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
						"[npc.name]向下抓住[npc.her]的胯部，向[npc2.name]眨巴眼，"
								+ "[npc.speech(可惜你已经怀孕了……但这不妨碍我用[npc.cum+]把你灌成奶油泡芙！)]",
						"[npc.name]用[npc.hands]在胯部摸来摸去，向[npc2.name]眨眨眼，"
								+ "[npc.speech(你可能已经怀孕了，但这不妨碍我给你来一记漂亮的内射！)]",
						"[npc.name]用[npc.hands]抚摸着[npc.her]的胯部，向[npc2.name]咧嘴一笑，"
								+ "[npc.speech(别以为怀孕了就能阻止我用[npc.cum+]把你灌成奶油泡芙！)]",
						"[npc.name]向下抓住[npc.her]的胯部，向[npc2.name]咧嘴一笑，"
								+ "[npc.speech(可惜你已经怀孕了……但我依然用[npc.cum+]把你灌成奶油泡芙！)]")));
				
			} else {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
						"[npc.name]向下抓住[npc.her]的胯部，向[npc2.name]眨巴眼，"
								+ (target.getAppearsAsGender().isFeminine() || (target.isAreaKnownByCharacter(CoverableArea.VAGINA, source) && target.hasVagina())
									?"[npc.speech(想怀孕吗？我的精液正渴望着填满你的子宫！)]"
									:"[npc.speech(或许我应该给你变个小穴，然后搞大你的肚子！)]"),
						"[npc.name]用[npc.hands]在胯部摸来摸去，向[npc2.name]眨眨眼，"
								+ (target.getAppearsAsGender().isFeminine() || (target.isAreaKnownByCharacter(CoverableArea.VAGINA, source) && target.hasVagina())
									?"[npc.speech(我的种相当厉害，我要把你一发入魂！)]"
									:"[npc.speech(如果你有小穴就好了，我会好好给你种一发！)]"),
						"[npc.name]用[npc.hands]抚摸着[npc.her]的胯部，向[npc2.name]咧嘴一笑，"
								+ (target.getAppearsAsGender().isFeminine() || (target.isAreaKnownByCharacter(CoverableArea.VAGINA, source) && target.hasVagina())
									?"[npc.speech(我的种相当厉害，我要把你一发入魂！)]"
									:"[npc.speech(或许我应该给你变个紧屄，然后一发入魂搞大肚子！)]"),
						"[npc.name]向下抓住[npc.her]的胯部，向[npc2.name]咧嘴一笑，"
								+ (target.getAppearsAsGender().isFeminine() || (target.isAreaKnownByCharacter(CoverableArea.VAGINA, source) && target.hasVagina())
									?"[npc.speech(我要把你操到怀孕！)]"
									:"[npc.speech(我得给你变个紧穴，然后把你操到怀孕！)]"))));
			}
            
            return formatAttackOutcome(source, target,
            		attackText+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_DOMINANT = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "支配挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_dominant",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_DOMINANT;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_SUBMISSIVE;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
            				+ "[npc.Name]告诉[npc2.name]，[npc.sheIs]要支配[npc2.herHim]，造成"
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append(UtilText.returnStringAtRandom(
						"[npc.name]朝[npc2.name]咧嘴淫笑，咆哮着说",
						"[npc.her]的脸上露出邪恶的笑容，[npc.name]朝着[npc2.name]咆哮，",
						"[npc.name]气势逼人地瞪着[npc2.name]，咆哮道，",
						"[npc.Name]好色地盯着[npc2.name]，发出威胁的咆哮， "));
        	 sb.append(UtilText.returnStringAtRandom(
				"[npc.speech(婊子，我要把你好好的、狠狠的操一遍！)]",
				"[npc.speech(你会被操得终身难忘！)]",
				"[npc.speech(你要变成我温顺的小荡妇了！)]",
				"[npc.speech(我要把你变成我的母狗！)]",
				"[npc.speech(你很快就要变成我的母狗了！)]",
				"[npc.speech(等我收拾完你，你就会变成一个哭唧唧的小母狗了！)]",
				"[npc.speech(我会狠狠的操你，而你会叫的像个小母狗！)]"));
            sb.append(dealtDamage.getKey());
            
            return formatAttackOutcome(source, target,
            		sb.toString(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_SUBMISSIVE = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "屈服挑逗",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_submissive",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_SUBMISSIVE;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_DOMINANT;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "仅适用于拥有"+associatedFetish.getName(source)+"性癖的角色。");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(暴击)]: ":"")
                    		+ "[npc.Name]告诉[npc2.name][npc.she]多么喜欢被支配，造成 "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + "伤害。"
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?"[style.italicsMinorBad(因为[npc2.name]不喜欢"+oppositeFetish.getName(source)+"性癖，伤害降低至1！)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name]可以用[npc.her]的"+associatedFetish.getName(source)+"性癖挑逗[npc.her]的目标，造成" + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + "伤害。"
            				+ "[style.italicsMinorBad(如果目标不喜欢"+oppositeFetish.getName(null)+"性癖，伤害会下降为1。)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name]低下[npc.her]的头以表屈服，用天真无邪的大眼睛看着，"
    								+ "[npc.speech(我会做个好[npc.girl]！我发誓！)]",
    						"[npc.Name]轻咬[npc.lip]，把[npc.feet]拖来拖去，努力装作很弱小的样子，"
    								+ "[npc.speech(我什么都听你的！)]",
    						"[npc.Name]把[npc.feet]拖来拖去，装作很小巧的样子，色迷迷地盯着[npc2.name]，"
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(求你了！像对待小母狗一样对待我！)]"
    									:"[npc.speech(求你了！让我当你的小肉便器！)]"),
    						"[npc.Name]色迷迷地盯着[npc2.namePos][npc2.eyes]，摆出一副天真无邪的样子，"
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(我会做你的小奴隶！)]"
    									:"[npc.speech(我会是一个优秀的小鸡巴套！我发誓)]")))+dealtDamage.getKey(),
            		"[npc2.Name]受到了" + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + "伤害！",
            		(isCrit
            			?"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！"
            			:null),
            		"[npc2.NameIsFull]极度欲火攻心，受到了三倍伤害！");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"目标拥有")+oppositeFetish.getName(target)+"性癖。");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
}
