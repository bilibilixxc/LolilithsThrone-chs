package com.lilithsthrone.game.dialogue.utils;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.5.1
 * @version 0.3.7.5
 * @author Innoxia
 */
public class SpellManagement {

    private static GameCharacter spellOwner;
    private static GameCharacter spellTarget;
    private static DialogueNode dialogueReturn;
    
    private static DialogueNode spellScreenAfterCasting;
    
    private static Spell spell;
    
    public static GameCharacter getSpellOwner() {
        if(spellOwner==null) {
            return Main.game.getPlayer();
        }
        return spellOwner;
    }

    public static GameCharacter getSpellTarget() {
        if(spellTarget==null) {
            return Main.game.getPlayer();
        }
        return spellTarget;
    }

    public static DialogueNode getDialogueReturn() {
        return dialogueReturn;
    }
    
    public static void setSpellOwner(GameCharacter spellOwner, DialogueNode dialogueReturn) {
        SpellManagement.spellOwner = spellOwner;
        SpellManagement.spellTarget = spellOwner;
        SpellManagement.dialogueReturn = dialogueReturn;
    }
    
    public static DialogueNode castSpell(Spell spell) {
    	SpellManagement.spellScreenAfterCasting = Main.game.getCurrentDialogueNode();
    	SpellManagement.spell = spell;
    	
    	spell.performOnSelection(0, getSpellOwner(), getSpellTarget(), null, getSpellOwner().getParty()); // Handles aura cost
    	
		Main.game.getTextStartStringBuilder().append(
				"<p style='text-align:center;'>"
					+ "<b>施放“<span style='color:"+spell.getSpellSchool().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(spell.getName())+"</span>”:</b>"
					+ "<br/>"
					+spell.applyEffect(getSpellOwner(), getSpellTarget(), true, false)
				+"</p>");
		
    	return SPELL_CAST_DIALOGUE;
    }
    
    private static Response getResponses1To9(int index) {
    	if(index==1) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_EARTH) {
    			return new Response("土", UtilText.parse(getSpellOwner(), "你已经在浏览[npc.namePos]的土系法术！"), null);
    		}
			return new Response("土", UtilText.parse(getSpellOwner(), "浏览[npc.namePos]的法术并在大地学派中升级。"), CHARACTER_SPELLS_EARTH);
			
		} else if(index==2) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_WATER) {
    			return new Response("水", UtilText.parse(getSpellOwner(), "你已经在浏览[npc.namePos]的水系法术！"), null);
    		}
			return new Response("水", UtilText.parse(getSpellOwner(), "浏览[npc.namePos]的法术并在激流学派中升级。"), CHARACTER_SPELLS_WATER);
			
		} else if(index==3) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_FIRE) {
    			return new Response("火", UtilText.parse(getSpellOwner(), "你已经在浏览[npc.namePos]的火系法术！"), null);
    		}
			return new Response("火", UtilText.parse(getSpellOwner(), "浏览[npc.namePos]的法术并在烈火学派中升级。"), CHARACTER_SPELLS_FIRE);
			
		} else if(index==4) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_AIR) {
    			return new Response("风", UtilText.parse(getSpellOwner(), "你已经在浏览[npc.namePos]的风系法术！"), null);
    		}
			return new Response("风", UtilText.parse(getSpellOwner(), "浏览[npc.namePos]的法术并在大气学派中升级。"), CHARACTER_SPELLS_AIR);
			
		} else if(index==5) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_ARCANE) {
    			return new Response("奥术", UtilText.parse(getSpellOwner(), "你已经在浏览[npc.namePos]的奥术系法术！"), null);
    		}
			return new Response("奥术", UtilText.parse(getSpellOwner(), "浏览[npc.namePos]的法术并在奥术系学派中升级。"), CHARACTER_SPELLS_ARCANE);
			
		} else if(index==6) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_MISC) {
    			return new Response("秘术", UtilText.parse(getSpellOwner(), "你已经在浏览秘术了！"), null);
    		}
			return new Response("秘术", UtilText.parse(getSpellOwner(), "浏览从武器或特殊事件中获取的秘术。"), CHARACTER_SPELLS_MISC);
			
		} else if(index==9) {
			return new ResponseEffectsOnly(
					UtilText.parse(getSpellTarget(), "目标：<b style='color:"+getSpellTarget().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
					"循切换选择施法目标。") {
				@Override
				public void effects() {
					List<GameCharacter> companions = Util.newArrayListOfValues(Main.game.getPlayer());
					companions.addAll(Main.game.getPlayer().getCompanions());
					if(!companions.isEmpty()) {
						for(int i=0; i<companions.size();i++) {
							if(companions.get(i).equals(getSpellTarget())) {
								if(i==companions.size()-1) {
									spellTarget = companions.get(0);
									break;
									
								} else {
									spellTarget = companions.get(i+1);
									break;
								}
							}
						}
					}
					Main.game.updateResponses();
				}
			};
		}
    	return null;
    }

    public static final DialogueNode CHARACTER_SPELLS_ARCANE = new DialogueNode("奥术法术", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.ARCANE, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.ARCANE.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldArcane(奥术学派能力：)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)
									?"[style.colourDisabled("+SpellSchool.ARCANE.getPassiveBuff()+")]<br/>(至少需要知道<b>三</b>个奥术系法术以解锁。)"
									:"[style.colourGood("+SpellSchool.ARCANE.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=9) {
				return getResponses1To9(index);
				
			} else if(index==10) {
				return new Response("重置奥术系", UtilText.parse(getSpellOwner(), "重置[npc.namePos]的奥术法术升级，恢复使用的点数，但法术不会重置。"), CHARACTER_SPELLS_ARCANE) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.ARCANE);
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "返回到上一个界面", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	
	public static final DialogueNode CHARACTER_SPELLS_EARTH = new DialogueNode("土系法术", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.EARTH, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.EARTH.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldEarth(大地学派能力：)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
									?"[style.colourDisabled("+SpellSchool.EARTH.getPassiveBuff()+")]<br/>(至少需要知道<b>三</b>个土系法术以解锁。)"
									:"[style.colourGood("+SpellSchool.EARTH.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=9) {
				return getResponses1To9(index);
				
			}  else if(index==10) {
				return new Response("重置土系", UtilText.parse(getSpellOwner(), "重置[npc.namePos]的土系法术升级，恢复使用的点数，但法术不会重置。"), CHARACTER_SPELLS_EARTH) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.EARTH);
					}
				};
				
			} else if(index == 0) {
				return new Response("返回", "返回到上一个界面", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	
	public static final DialogueNode CHARACTER_SPELLS_WATER = new DialogueNode("水系法术", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.WATER, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.WATER.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldWater(激流学派能力：)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.WATER)
									?"[style.colourDisabled("+SpellSchool.WATER.getPassiveBuff()+")]<br/>(至少需要知道<b>三</b>个水系法术以解锁。)"
									:"[style.colourGood("+SpellSchool.WATER.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=9) {
				return getResponses1To9(index);
				
			} else if(index==10) {
				return new Response("重置水系", UtilText.parse(getSpellOwner(), "重置[npc.namePos]的水系法术升级，恢复使用的点数，但法术不会重置。"), CHARACTER_SPELLS_WATER) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.WATER);
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "返回到上一界面", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	
	public static final DialogueNode CHARACTER_SPELLS_AIR = new DialogueNode("风系法术", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.AIR, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.AIR.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldAir(大气学派能力：)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.AIR)
									?"[style.colourDisabled("+SpellSchool.AIR.getPassiveBuff()+")]<br/>(至少需要知道<b>三</b>个风系法术以解锁。)"
									:"[style.colourGood("+SpellSchool.AIR.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=9) {
				return getResponses1To9(index);
				
			}  else if(index==10) {
				return new Response("重置风系", UtilText.parse(getSpellOwner(), "重置[npc.namePos]的风系法术升级，恢复使用的点数，但法术不会重置。"), CHARACTER_SPELLS_AIR) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.AIR);
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "返回到上一界面", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	
	public static final DialogueNode CHARACTER_SPELLS_FIRE = new DialogueNode("火系法术", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.FIRE, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.FIRE.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldFire(烈火学派能力：)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.FIRE)
									?"[style.colourDisabled("+SpellSchool.FIRE.getPassiveBuff()+")]<br/>(至少需要知道<b>三</b>个火系法术以解锁。)"
									:"[style.colourGood("+SpellSchool.FIRE.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=9) {
				return getResponses1To9(index);
				
			} else if(index==10) {
				return new Response("重置火系", UtilText.parse(getSpellOwner(), "重置[npc.namePos]的火系法术升级，恢复使用的点数，但法术不会重置。"), CHARACTER_SPELLS_FIRE) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.FIRE);
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "返回到上一界面", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};

	public static final DialogueNode CHARACTER_SPELLS_MISC = new DialogueNode("隐秘法术", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellMiscTreeDisplay(getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+ "虽然有许多法术在相对容易获得的法术书中得到了详细的解释，但也有一些法术没有被编目并记录在案。"
							+ "尽管这些难以捉摸的法术都属于法术的五大主要学派之一，但只在极少数的魔法书中有所提及，它们通常被归类为“秘术”。"
							+ "<br/><br/>"
							+ "虽然这些魔法无法从书中学习，但可以通过装备特殊的魔法武器来使用。"
							+ "也有可能通过独特的事件获得这些法术……"
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldSpell(秘术)]从特殊武器或独特事件中获取。"
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=9) {
				return getResponses1To9(index);
				
			} else if (index == 0) {
				return new Response("返回", "返回到上一界面", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	

	public static final DialogueNode SPELL_CAST_DIALOGUE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getLabel() {
			return Util.capitaliseSentence(spell.getName());
		}
		@Override
		public String getContent(){
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "回到法术管理界面。", spellScreenAfterCasting);
			}
			return null;
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
}
