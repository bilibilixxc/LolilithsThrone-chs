package com.lilithsthrone.game.combat;

import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.LolificationProject.combat.CombatExtras;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.AppliedStatusEffect;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.moves.CombatMove;
import com.lilithsthrone.game.combat.moves.CombatMoveType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

/**
 * Call initialiseCombat() before using.
 *
 * @since 0.1.0
 * @version 0.4.9
 * @author Innoxia, Irbynx
 */
public class Combat {
	
	private NPC activeNPC;
	private GameCharacter targetedAlly;
	private GameCharacter targetedEnemy;
	private NPC enemyLeader;
	
	private List<NPC> allies = new ArrayList<>();
	private List<NPC> enemies = new ArrayList<>();
	private List<NPC> allCombatants = new ArrayList<>();
	private List<GameCharacter> activeCombatants; // A list of combatants who are still active in the fight. This is updated at the very end of each combat turn, and removes characters which have been defeated during the last turn.
	
	private float escapeChance = 0;
	private boolean submitBlocked = false;
	private Map<GameCharacter, Float> totalDamageTaken;
	private int turn = 0;
	private boolean attemptedEscape = false;
	private boolean escaped = false;
	private boolean playerVictory = false;
	private StringBuilder postCombatStringBuilder = new StringBuilder();
	
	private StringBuilder combatTurnResolutionStringBuilder = new StringBuilder();

	private Map<GameCharacter, GameCharacter> preferredTargets;
	
	private Map<GameCharacter, Stack<Float>> manaBurnStack;
	
	private Map<GameCharacter, Map<AbstractStatusEffect, Integer>> statusEffectsToApply;
	
	private Map<GameCharacter, List<String>> combatContent;
	private Map<GameCharacter, List<String>> predictionContent;
	private Map<GameCharacter, List<String>> escapeDescriptionMap;
	
	private Map<GameCharacter, List<Value<GameCharacter, AbstractItem>>> itemsToBeUsed;

	// Maps characters -> inventory slots (to track which slot the weapon was thrown from) -> weapon type and number of weapon type that has been thrown
	private Map<GameCharacter, Map<InventorySlot, Map<AbstractWeapon, Integer>>> weaponsThrownDuringTurn;
	private Map<GameCharacter, Map<InventorySlot, Map<AbstractWeapon, Integer>>> weaponsThrownDuringCombat;
	private Map<GameCharacter, Map<InventorySlot, AbstractWeaponType>> thrownWeaponsDepleted; // Only for use in UI rendering
	
	// Used if the ResponseCombat which initialises combat came from an external dialogue file:
	private DialogueNode playerPostVictoryDialogue;
	private DialogueNode playerPostDefeatDialogue;
	
	
	public Combat() {
	}

	public void initialiseCombat(
			List<NPC> allies,
			boolean addElementalsToAllies,
			NPC enemyLeader,
			List<NPC> enemies,
			Map<GameCharacter, String> openingDescriptions) {
		initialiseCombat(allies,
				addElementalsToAllies,
				enemyLeader,
				enemies,
				openingDescriptions,
				false,
				false);
	}
	/**
	 * @param allies A list of allies who are fighting with you. <b>Do not include Main.game.getPlayer() in this!</b>
	 * @param enemies A list of enemies you're fighting. The first enemy in the list is considered the leader.
	 * @param escapePercentage The base chance of escaping in this combat situation. TODO
	 * @param openingDescriptions A map of opening descriptions for characters. If a description is not provided, one is generated automatically.
	 * @param escapeBlocked Whether or not escape action is blocked during this combat.
	 */
	public void initialiseCombat(
			List<NPC> allies,
			boolean addElementalsToAllies,
			NPC enemyLeader,
			List<NPC> enemies,
			Map<GameCharacter, String> openingDescriptions,
			boolean escapeBlocked,
			boolean submitBlocked) {
		
		// These should be set manually after initialising combat
		playerPostVictoryDialogue = null;
		playerPostDefeatDialogue = null;
		
		allCombatants = new ArrayList<>();
		this.allies = new ArrayList<>();
		this.enemyLeader = enemyLeader;
		this.enemies = new ArrayList<>();
		activeCombatants = new ArrayList<>();

		predictionContent = new HashMap<>();
		combatContent = new HashMap<>();
		escapeDescriptionMap = new HashMap<>();
		itemsToBeUsed = new HashMap<>();
		preferredTargets = new HashMap<>();
		manaBurnStack = new HashMap<>();
		statusEffectsToApply = new HashMap<>();
		
		predictionContent.put(Main.game.getPlayer(), new ArrayList<>());
		itemsToBeUsed.put(Main.game.getPlayer(), new ArrayList<>());
		manaBurnStack.put(Main.game.getPlayer(), new Stack<>());
		statusEffectsToApply.put(Main.game.getPlayer(), new HashMap<>());
		combatContent.put(Main.game.getPlayer(), new ArrayList<>());
		activeCombatants.add(Main.game.getPlayer());

		weaponsThrownDuringTurn = new HashMap<>();
		weaponsThrownDuringCombat = new HashMap<>();
		thrownWeaponsDepleted = new HashMap<>();
		resetWeaponsThrownDuringTurn(Main.game.getPlayer());
		resetWeaponsThrownDuringCombat(Main.game.getPlayer());
		resetThrownWeaponsDepleted(Main.game.getPlayer());
		
		if(addElementalsToAllies) {
			if(Main.game.getPlayer().isElementalSummoned()) {
				this.addAlly(Main.game.getPlayer().getElemental());
				Main.game.getPlayer().getElemental().setLocation(Main.game.getPlayer(), false);
			}
			if(allies!=null){
				for(NPC ally : allies) {
					this.addAlly(ally);
					if(ally.isElementalSummoned()) {
						this.addAlly(ally.getElemental());
						ally.getElemental().setLocation(ally, false);
					}
				}
			}
		}
		for(NPC enemy : enemies) {
			this.addEnemy(enemy);
			if(enemy.isElementalSummoned()) {
				this.addEnemy(enemy.getElemental());
				enemy.getElemental().setLocation(enemy, false);
			}
		}
		enemies.sort((enemy1, enemy2) -> enemy2.getLevel()-enemy1.getLevel());
		
		
		targetedEnemy = enemies.get(0);
		targetedAlly = Main.game.getPlayer();
		activeNPC = enemies.get(0);

		attemptedEscape = false;
		escaped = false;
		playerVictory = false;
				
		totalDamageTaken = new HashMap<>();
		turn = 0;
		postCombatStringBuilder.setLength(0);
		combatTurnResolutionStringBuilder.setLength(0);
		
		if(escapeBlocked) {
			escapeChance = 0;
		} else {
			escapeChance = ((NPC) enemies.get(0)).getEscapeChance();
			if (Main.game.getPlayer().hasTrait(Perk.RUNNER, true)) {
				escapeChance *= 1.5f;
			} else if (Main.game.getPlayer().hasTrait(Perk.RUNNER_2, true)) {
				escapeChance *= 2f;
			}
			if(escapeChance >0 && Main.game.getPlayer().hasTrait(Perk.JOB_ATHLETE, true)) {
				escapeChance = 100;
			}
			if(escapeChance >0 && Main.game.getPlayer().getSubspecies()==Subspecies.CAT_MORPH_CHEETAH) {
				boolean cheetahEnemy = false;
				for(GameCharacter enemy : getEnemies(Main.game.getPlayer())) {
					if(enemy.getSubspecies()==Subspecies.CAT_MORPH_CHEETAH) {
						cheetahEnemy = true;
					}
				}
				if(!cheetahEnemy) {
					escapeChance = 100;
				}
			}
		}
		
		this.submitBlocked = submitBlocked;
		
		String startingEffect = "";
		
		if(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)) {
			Main.game.getPlayer().addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 11);
			startingEffect = Spell.getBasicStatusEffectApplication(Main.game.getPlayer(), true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 10)));
			
		} else if(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2)) {
			Main.game.getPlayer().addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 11);
			startingEffect = Spell.getBasicStatusEffectApplication(Main.game.getPlayer(), true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 10)));
			
		} else if(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1)) {
			Main.game.getPlayer().addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION, 11);
			startingEffect = Spell.getBasicStatusEffectApplication(Main.game.getPlayer(), true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION, 10)));
		}
		
		// Soldier:
		if(Main.game.getPlayer().hasTrait(Perk.JOB_SOLDIER, true)) {
			Main.game.getPlayer().addStatusEffect(StatusEffect.COMBAT_JOB_SOLDIER, 2);
			combatContent.get(Main.game.getPlayer()).add(
					"你在第一回合中造成的任意"+Attribute.HEALTH_MAXIMUM.getName()+"伤害都会[style.boldExcellent(加倍)]，这是由于你的"
						+ "<b style='color:"+Perk.JOB_SOLDIER.getColour().toWebHexString()+";'>"+Perk.JOB_SOLDIER.getName(Main.game.getPlayer())+"</b>能力。");
		}
		
		combatContent.get(Main.game.getPlayer()).add(
				openingDescriptions!=null && openingDescriptions.containsKey(Main.game.getPlayer())
					?openingDescriptions.get(Main.game.getPlayer())
					:"你准备好行动了……");
		
		String pregProtection = getPregnancyProtectionText(Main.game.getPlayer());
		if(!pregProtection.isEmpty()) {
			combatContent.get(Main.game.getPlayer()).add(pregProtection);
		}
		
		combatContent.get(Main.game.getPlayer()).add(startingEffect);
		
		for(NPC combatant : allCombatants) {
			startingEffect="";
			if(combatant.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)) {
				combatant.addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 11);
				startingEffect = Spell.getBasicStatusEffectApplication(combatant, true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 10)));
				
			} else if(combatant.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2)) {
				combatant.addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 11);
				startingEffect = Spell.getBasicStatusEffectApplication(combatant, true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 10)));
				
			} else if(combatant.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1)) {
				combatant.addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION, 11);
				startingEffect = Spell.getBasicStatusEffectApplication(combatant, true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION, 10)));
			}

			// Soldier:
			if(combatant.hasTrait(Perk.JOB_SOLDIER, true)) {
				combatant.addStatusEffect(StatusEffect.COMBAT_JOB_SOLDIER, 2);
				combatContent.get(combatant).add(UtilText.parse(combatant,
						"第一个回合中，[npc.name]造成的任何"+Attribute.HEALTH_MAXIMUM.getName()+"伤害都会[style.boldExcellent(翻倍)]，这多亏了[npc.her]"
								+ "<b style='color:"+Perk.JOB_SOLDIER.getColour().toWebHexString()+";'>"+Perk.JOB_SOLDIER.getName(combatant)+"</b>能力。"));
			}
			
			combatContent.get(combatant).add(UtilText.parse(combatant,
					openingDescriptions!=null && openingDescriptions.containsKey(combatant)
						?openingDescriptions.get(combatant)
						:"[npc.Name]准备行动……"));
			
			pregProtection = getPregnancyProtectionText(combatant);
			if(!pregProtection.isEmpty()) {
				combatContent.get(combatant).add(pregProtection);
			}
			
			combatContent.get(combatant).add(startingEffect);
		}
		
		Main.game.getPlayer().calculateStatusEffects(0); // Calculate status effects to make sure combat SEs are initialised before selecting moves
		Main.game.getPlayer().resetSelectedMoves();
		Main.game.getPlayer().resetMoveCooldowns();
		applyNewTurnShielding(Main.game.getPlayer());
		Main.game.getPlayer().setRemainingAP(Main.game.getPlayer().getMaxAP(), null, null);
		
		combatTurnResolutionStringBuilder.append(getCharactersTurnDiv(Main.game.getPlayer(), getTurn()==0?"准备":"", combatContent.get(Main.game.getPlayer())));

		Main.game.setInCombat(true);
		
		for(NPC npc : allCombatants) {
			npc.calculateStatusEffects(0); // Calculate status effects to make sure combat SEs are initialised before selecting moves
			combatTurnResolutionStringBuilder.append(getCharactersTurnDiv(npc, getTurn()==0?"准备":"", combatContent.get(npc)));
			
			npc.resetSelectedMoves();
			npc.resetDefaultMoves(); // Resetting to take into account any newly obtained moves. Does not do anything to player party members.
			npc.resetMoveCooldowns();
			applyNewTurnShielding(npc);
			npc.setRemainingAP(npc.getMaxAP(), null, null);
			// Sets up NPC ally/enemy lists that include player
			List<GameCharacter> npcAllies = getAllies(npc);
			List<GameCharacter> npcEnemies = getEnemies(npc);
			
//			System.out.println(npc.getName());
//			for(AbstractCombatMove move : npc.getAvailableMoves()) {
//				System.out.println(move.getIdentifier());
//			}
//			System.out.println("---");
//			for(AbstractCombatMove move : npc.getEquippedMoves()) {
//				System.out.println(move.getIdentifier());
//			}
			
			// Selects the moves
			npc.selectMoves(npcEnemies, npcAllies);
			predictionContent.put(npc, npc.getMovesPredictionString(npcEnemies, npcAllies));
		}
		
		// I don't know why openInventory() was being called here, so I commented it out in v0.4.6.8. It caused a bug that was hard to reproduce but which threw this error:
			//Exception in thread "JavaFX Application Thread" java.lang.NullPointerException
			//at com.lilithsthrone.rendering.RenderingEngine.getInventoryIconsForPage(RenderingEngine.java:954)
			//at com.lilithsthrone.rendering.RenderingEngine.getInventoryDiv(RenderingEngine.java:797)
//		Main.mainController.openInventory();
	}

	public void setCharacterTurnContent(GameCharacter character, List<String> descriptions) {
		combatContent.put(character, descriptions);
	}
	
	private String getCharactersTurnDiv(GameCharacter character, String title, List<String> descriptions) {
		String effects = applyEffects(character);
		StringBuilder sb = new StringBuilder();
		
		boolean enemy = enemies.contains(character);
		
		sb.append("<div class='container-full-width' style='text-align:center; box-sizing: border-box; border:6px solid "+(enemy?PresetColour.GENERIC_MINOR_BAD:PresetColour.GENERIC_MINOR_GOOD).getShades()[0]+"; border-radius:5px;'>");

			sb.append(
					"<div class='container-full-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; text-align:center;'>"
						+ "<b style='color:"+character.getFemininity().getColour().toWebHexString()+";'>"
							+ UtilText.parse(character, "[npc.Name]")
						+ "</b>"
					+ "</div>");
		
			sb.append("<div class='container-full-width'>");
				for(String s : descriptions) {
					if(!s.isEmpty()) {
						sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+PresetColour.BACKGROUND.toWebHexString()+";'>"+s+"</div>");
					}
				}
				if(!effects.isEmpty()) {
					sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+PresetColour.BACKGROUND.toWebHexString()+";'>"+effects+"</div>");
				}
			sb.append("</div>");
				
		sb.append("</div>");
		
		return sb.toString();
	}
	
	/**
	 * Ends combat, removing status effects and handling post-combat experience gains and loot drops.
	 * 
	 * @param playerVictory
	 */
	public void endCombat(boolean playerVictory) {
		
		postCombatStringBuilder.setLength(0);
		
		this.playerVictory = playerVictory;

		for(NPC enemy : enemies) {
			enemy.removeFlag(NPCFlagValue.playerEscapedLastCombat);
		}
		
		if (playerVictory) {
			// Give the player experience and money if they won:
			int xp = 0;
			int money = 0;
			for(NPC enemy : enemies) {
				xp+=enemy.getExperienceFromVictory();
				money+=enemy.getLootMoney();
				enemy.setLostCombatCount(enemy.getLostCombatCount()+1);
			}
			
			for(NPC ally : allies) {
				if(!(ally.isElemental())) {
					postCombatStringBuilder.append(ally.incrementExperience(xp, true));
				}
			}
			
			postCombatStringBuilder.append(Main.game.getPlayer().incrementExperience(xp, true));
			
			if (money > 0) {
				postCombatStringBuilder.append(Main.game.getPlayer().incrementMoney(money));
			}
			
			// Apply loot drop:
			Map<AbstractCoreItem, Integer> lootedItemsMap = new HashMap<>();
			
			for(NPC enemy : enemies) {
				if(enemy.getLootItems()!=null) {
					for(AbstractCoreItem item : enemy.getLootItems()) {
						lootedItemsMap.putIfAbsent(item, 0);
						lootedItemsMap.put(item, lootedItemsMap.get(item)+1);
						if(item instanceof AbstractItem) {
							Main.game.getPlayer().addItem((AbstractItem) item, false, true);
						} else if(item instanceof AbstractWeapon) {
							Main.game.getPlayer().addWeapon((AbstractWeapon) item, false);
						} else if(item instanceof AbstractClothing) {
							Main.game.getPlayer().addClothing((AbstractClothing) item, false);
						}
					}
				}
			}

			List<String> itemsLooted = new ArrayList<>();
			for(Entry<AbstractCoreItem, Integer> entry : lootedItemsMap.entrySet()) {
				itemsLooted.add("<b style='color:"+entry.getKey().getRarity().getColour().toWebHexString()+";'>"+entry.getKey().getName()+"</b>"+(entry.getValue()>1?" <b>(x"+entry.getValue()+")</b>":""));
			}
			if(!itemsLooted.isEmpty()) {
				postCombatStringBuilder.append("<div class='container-full-width' style='text-align:center;'>你[style.boldGood(获得了)]" + Util.stringsToStringList(itemsLooted, false) +"！</div>");
			}
			// Apply essence drops:
			boolean essenceDropFound = false;
			int totalEssencesGained = 0;
			for(NPC enemy : enemies) {
				int essencesGained = enemy.getLootEssenceDrops();
				totalEssencesGained += essencesGained;
				if(essencesGained>0) {
					if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essencePostCombatDiscovered)) {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.essencePostCombatDiscovered);
						
						if(!essenceDropFound) {
							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
								postCombatStringBuilder.append(
										UtilText.parse(enemy,
										"<p>"
										+ "<i>"
											+ "[npc.Name]踉踉跄跄地退后，败下阵来，但还没等你庆祝胜利的喜悦，周围的世界便模糊起来。"
											+ "[npc.her]口中发出的喘息声也逐渐变弱，变成了闷响；就仿佛潜在水下听着[npc.her]说话一样。"
											+ "你晃了晃脑袋，但并没有清醒过来，于是低头看向[npc.name]，是不是[npc.sheIs]也同样收到了影响，可随后你却惊讶地瞪大了眼睛。"
										+ "</p>"
										+ "<p>"
											+ "一股粉色的光芒在[npc.her]的身体周围浮现，就跟你在莉莱雅实验室做测试时的那个一模一样。"
											+ "你很快就明白过来，你不知为何能够看到[npc.namePos]的奥术灵气了，于是着迷了似的，直勾勾地盯着一块不大不小的碎片缓缓从[npc.herHim]周围碎裂开来。"
											+ "等到完全与灵气的其他部分脱离，那能量的碎片便突然仿佛有意识地向你飞来。"
										+ "</p>"
										+ "<p>"
											+ "你被自己所处的奇妙状态拖慢了反应速度，头晕目眩，根本没有机会躲闪，当碎片接触到你的胸膛时，竟直直刺入了身体。"
											+ "碎片进入身体的那一刻，你附近的世界便瞬间恢复了清晰，你这才反应过来自己用力地吸着气，那能量似乎融入了你自己的奥术灵气中。"
										+ "</p>"
										+ "<p>"
											+ "你又低头看向[npc.name]，前一秒还在身边忽隐忽现的粉色能量场已经没了踪迹，[npc.she]似乎完全没有看到你刚才目睹的一切。"
											+ "你觉得最好还是去找莉莱雅，问问刚才发生了什么，但现在你要先想想该怎么处理这个麻烦的[npc.race]……"
										+ "</i>"
										+ "</p>"
										+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"")));
								
							} else {
								postCombatStringBuilder.append(
										UtilText.parse(enemy,
										"<p>"
										+ "<i>"
											+ "[npc.Name]踉踉跄跄地退后，败下阵来，但还没等你庆祝胜利的喜悦，周围的世界便模糊起来。"
											+ "[npc.her]口中发出的喘息声也逐渐变弱，变成了闷响；就仿佛潜在水下听着[npc.her]说话一样。"
											+ "你晃了晃脑袋，但并没有清醒过来，于是低头看向[npc.name]，是不是[npc.sheIs]也同样收到了影响，可随后你却惊讶地瞪大了眼睛。"
										+ "</p>"
										+ "<p>"
											+ "一股粉色的光芒在[npc.her]的身体周围浮现，就跟你在莉莱雅实验室做测试时的那个一模一样。"
											+ "你很快就明白过来，你不知为何能够看到[npc.namePos]的奥术灵气了，于是着迷了似的，直勾勾地盯着一块不大不小的碎片缓缓从[npc.herHim]周围碎裂开来。"
											+ "等到完全与灵气的其他部分脱离，那能量的碎片便突然仿佛有意识地向你飞来。"
										+ "</p>"
										+ "<p>"
											+ "你被自己所处的奇妙状态拖慢了反应速度，头晕目眩，根本没有机会躲闪，当碎片接触到你的胸膛时，竟直直刺入了身体。"
											+ "碎片进入身体的那一刻，你附近的世界便瞬间恢复了清晰，你这才反应过来自己用力地吸着气，那能量似乎融入了你自己的奥术灵气中。"
										+ "</p>"
										+ "<p>"
											+ "你又低头看向[npc.name]，前一秒还在身边忽隐忽现的粉色能量场已经没了踪迹，[npc.she]似乎完全没有看到你刚才目睹的一切。"
											+ "你忽然回忆起莉莱雅告诉你有关吸收精华的事情，而且这种行为对双方都没有任何损害。"
											+ "你长舒一口气，将注意力又转移回这个麻烦的[npc.race]身上……"
										+ "</i>"
										+ "</p>"));
							}
						}
					}
				}
			}
			
			if(totalEssencesGained>0) {
				totalEssencesGained *= Math.round(ModProps.essenceMultiplier / 100f);
				postCombatStringBuilder.append(
						"<div class='container-full-width' style='text-align:center;'>"
								+ Main.game.getPlayer().incrementEssenceCount(totalEssencesGained, true)
						+ "</div>"
						+ "</br>");
			}
			
		} else { // Player lost combat:
			int xpGain = (Main.game.getPlayer().getLevel()*2);
			
			for(NPC enemy : enemies) {
				if(!(enemy.isElemental())) {
					postCombatStringBuilder.append(enemy.incrementExperience(xpGain, true));
				}
			}
			
			long money = Main.game.getPlayer().getMoney();
			int moneyLoss = (-enemyLeader.getLootMoney()/2)*enemies.size();
			if(moneyLoss!=0 && enemyLeader.isLootingPlayerAfterCombat()) {
				Main.game.getPlayer().incrementMoney(moneyLoss);
				postCombatStringBuilder.append("<div class='container-full-width' style='text-align:center;'>你[style.boldBad(失去了)]" + UtilText.formatAsMoney(Math.abs(Main.game.getPlayer().getMoney()==0?money:moneyLoss)) + "！</div>");
			}
			
			for(NPC enemy : enemies) {
				enemy.setWonCombatCount(enemy.getWonCombatCount()+1);
			}
		}
		
		// Remove elementals:
		for(GameCharacter combatant : getAllCombatants(true)) {
			if(combatant.isElementalSummoned()) {
				combatant.getElemental().returnToHome();
				if((playerVictory && getEnemies(Main.game.getPlayer()).contains(combatant))
						 || (!playerVictory && !getEnemies(Main.game.getPlayer()).contains(combatant))) {
					combatant.setElementalSummoned(false);
					postCombatStringBuilder.append(UtilText.parse(combatant, combatant.getElemental(),
							"<div class='container-full-width' style='text-align:center;'><i>"
								+ "[npc.NamePos]的元素体<span style='colour:"+combatant.getElemental().getFemininity().getColour().toWebHexString()+";'>[npc2.name]</span>"
									+ "完全耗尽了能量，于是被[style.italicsBad(驱散)]了！"
							+ "</i></div>"));
				} else { 
					postCombatStringBuilder.append(UtilText.parse(combatant, combatant.getElemental(),
							"<div class='container-full-width' style='text-align:center;'><i>"
								+ "[npc.NamePos]的元素体<span style='colour:"+combatant.getElemental().getFemininity().getColour().toWebHexString()+";'>[npc2.name]</span>"
									+ "耗尽了能量，于是[style.italicsArcane(变回了被动形态)]！"
							+ "</i></div>"));
				}
			}
		}
		
		Main.game.setInCombat(false);
		
		// Sort out effects after combat:
		for(GameCharacter character : getAllCombatants(true)) {
			// Handle thrown weapons:
			boolean anyWeaponsRecovered = false;
        	for(int i=0; i<Math.min(character.getArmRows(), character.getMainWeaponArray().length); i++) {
				for(Entry<AbstractWeapon, Integer> entry : weaponsThrownDuringCombat.get(character).get(InventorySlot.mainWeaponSlots[i]).entrySet()) {
					AbstractWeapon weapon = entry.getKey();
					for(int count=0; count<entry.getValue(); count++) {
						if(Math.random()*100 <= weapon.getWeaponType().getOneShotChanceToRecoverAfterCombat()) {
							if(character.getMainWeapon(i)==null) {
								character.equipMainWeapon(weapon, i, false);
							} else {
								character.addWeapon(weapon, 1, false, false);
							}
							if(!anyWeaponsRecovered) {
								postCombatStringBuilder.append("<div class='container-full-width' style='text-align:center;'><i>");
								if(character.isPlayer()) {
									postCombatStringBuilder.append("[style.boldGood(单发武器装填：)]");
								} else {
									postCombatStringBuilder.append(UtilText.parse(character, "[style.boldGood([npc.name]单发武器装填：)]"));
								}
								postCombatStringBuilder.append("<br/>");
								postCombatStringBuilder.append(Util.capitaliseSentence(weapon.getDisplayName(true)));
								anyWeaponsRecovered = true;
							} else {
								postCombatStringBuilder.append("，"+Util.capitaliseSentence(weapon.getDisplayName(true)));
							}
						}
					}
				}
			}
        	for(int i=0; i<Math.min(character.getArmRows(), character.getOffhandWeaponArray().length); i++) {
				for(Entry<AbstractWeapon, Integer> entry : weaponsThrownDuringCombat.get(character).get(InventorySlot.offhandWeaponSlots[i]).entrySet()) {
					AbstractWeapon weapon = entry.getKey();
					for(int count=0; count<entry.getValue(); count++) {
						if(Math.random()*100 <= weapon.getWeaponType().getOneShotChanceToRecoverAfterCombat()) {
							if(character.getOffhandWeapon(i)==null) {
								character.equipOffhandWeapon(weapon, i, false);
							} else {
								character.addWeapon(weapon, 1, false, false);
							}
							if(!anyWeaponsRecovered) {
								postCombatStringBuilder.append("<div class='container-full-width' style='text-align:center;'><i>");
								if(character.isPlayer()) {
									postCombatStringBuilder.append("[style.boldGood(单发武器装填：)]");
								} else {
									postCombatStringBuilder.append(UtilText.parse(character, "[style.boldGood([npc.name]单发武器装填：)]"));
								}
								postCombatStringBuilder.append("<br/>");
								postCombatStringBuilder.append(Util.capitaliseSentence(weapon.getDisplayName(true)));
								anyWeaponsRecovered = true;
							} else {
								postCombatStringBuilder.append("，"+Util.capitaliseSentence(weapon.getDisplayName(true)));
							}
						}
					}
				}
			}
			if(anyWeaponsRecovered) {
				postCombatStringBuilder.append("</i></div>");
			}
//        	resetWeaponsThrownDuringCombat(character); // This gets reset when combat is initialised anyway, and it might be useful for post-combat dialogue to reference thrown/one-shot weapons?
			
			if(enemies.contains(character)) {
				character.setMana(character.getAttributeValue(Attribute.MANA_MAXIMUM));
				character.setHealth(character.getAttributeValue(Attribute.HEALTH_MAXIMUM));
			} else {
				character.setMana(Math.max(character.getMana(), 5));
				character.setHealth(Math.max(character.getHealth(), 5));
			}
			character.clearCombatStatusEffects();
		}
		
		Main.game.getTextStartStringBuilder().append(postCombatStringBuilder.toString());
	}

	private String npcStatus() {
		return "";
	}

	// DIALOGUES:
	public DialogueNode startCombat() {
		return ENEMY_ATTACK;
	}
	
	public boolean isCombatantDefeated(GameCharacter character) {
		return (character.getHealth() <= 0 || (character.getLust()>=100 && character.isVulnerableToLustLoss()));
	}
	
	public boolean isOpponent(GameCharacter character, GameCharacter target) {
		if(allies.contains(character) || character.isPlayer()) {
			return enemies.contains(target);
		} else {
			return allies.contains(target) || target.isPlayer();
		}
	}
	
	private boolean isAlliedPartyDefeated() {
		for(NPC ally : allies) {
			if(!isCombatantDefeated(ally)) {
				return false;
			}
		}
		return isCombatantDefeated(Main.game.getPlayer());
	}
	
	private boolean isEnemyPartyDefeated() {
		for(NPC enemy : enemies) {
			if(!isCombatantDefeated(enemy)) {
				return false;
			}
		}
		return true;
	}
	
	private Response getEndCombatDialogue(boolean applyEffects, boolean playerVictory) {
		if(playerVictory && getPlayerPostVictoryDialogue()!=null) {
			return new Response("胜利", "你赢了！", getPlayerPostVictoryDialogue());
		}
		if(!playerVictory && getPlayerPostDefeatDialogue()!=null) {
			return new Response("失败", "你输了！", getPlayerPostDefeatDialogue());
		}
		return enemyLeader.endCombat(applyEffects, playerVictory);
	}

	public final DialogueNode ITEM_USED = new DialogueNode("战斗", "使用物品。", true) {
		@Override
		public String getLabel() {
			return getCombatLabel();
		}
		@Override
		public String getHeaderContent() {
			return npcStatus();
		}
		@Override
		public String getContent() {
			return getCombatContent();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (isEnemyPartyDefeated()) {
					return new ResponseEffectsOnly("胜利", "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>你赢了！</span>"){
						@Override
						public void effects() {
							endCombat(true);
							Main.game.setContent(getEndCombatDialogue(true, true));
						}
					};
				} else {
					return new Response("继续", "战斗继续。", ENEMY_ATTACK){
						@Override
						public void effects() {
							endCombatTurn();//TODO test
						}
					};
				}
				
			} else {
				return null;
			}
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.NORMAL;
		}
	};

	public final DialogueNode SUBMIT = new DialogueNode("战斗", "屈服", true) {

		@Override
		public String getLabel() {
			return getCombatLabel();
		}

		@Override
		public String getHeaderContent() {
			return npcStatus();
		}

		@Override
		public String getContent() {
			return UtilText.parse(enemyLeader,
							"<p>"
									+ "你确定要向[npc.Name]<b>屈服</b>吗？<b>这将导致你战斗失败，[npc.herHim]可以对你为所欲为！</b>"
							+ "</p>");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("屈服",
						UtilText.parse(enemyLeader,
								"向[npc.name]屈服。<span style='color:" + PresetColour.GENERIC_TERRIBLE.toWebHexString() + ";'>这将导致你输掉当前的战斗！</span>"),
						SUBMIT_CONFIRM){
					@Override
					public void effects() {
						StringBuilder sb = new StringBuilder();
						
						sb.append(getCharactersTurnDiv(Main.game.getPlayer(), "屈服",
								Util.newArrayListOfValues(UtilText.parse(enemyLeader,
									"你在[npc.name]面前跪下，顺从地垂下了脑袋，咕哝道，"
										+ "[pc.speech(我不想再打了，我投降。)]"))));

						sb.append(getCharactersTurnDiv(enemyLeader, "胜利",
								Util.newArrayListOfValues(UtilText.parse(enemyLeader,
									"[npc.Name]放出得胜的大笑，上前去接受了你的屈服……"))));
						
						Main.game.getTextStartStringBuilder().append(sb.toString());
					}
				};
				
			} else if (index == 0) {
				return new Response("取消", "继续战斗。", ENEMY_ATTACK);
				
			}else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.NORMAL;
		}
	};
	public final DialogueNode SUBMIT_CONFIRM = new DialogueNode("战斗", "屈服", true) {

		@Override
		public String getLabel() {
			return getCombatLabel();
		}

		@Override
		public String getHeaderContent() {
			return npcStatus();
		}

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("继续", UtilText.parse(enemyLeader, "你等待[npc.Name]行动。")){
					@Override
					public void effects() {
						endCombat(false);
						Main.game.setResponseTab(0);
						Main.game.setContent(getEndCombatDialogue(true, false));
					}
				};
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.NORMAL;
		}
	};

	public final DialogueNode ENEMY_ATTACK = new DialogueNode("战斗", "敌人向你发起反击。", true) {

		@Override
		public String getLabel() {
			return getCombatLabel();
		}

		@Override
		public String getHeaderContent() {
			return npcStatus();
		}

		@Override
		public String getContent() {
			return getCombatContent();
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(enemyLeader.interruptCombatSpecialCase()!=null) {
				return null;
			}
			if(index==0) {
				return "核心动作";
				
			} else if(index==1) {
				return "基础动作";
				
			} else if(index==2) {
				return "特殊动作";
				
			} else if(index==3) {
				return "法术";
				
			} else if(index==4) {
				return "命令";
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			//TODO
			if(enemyLeader.interruptCombatSpecialCase()!=null) {
				if(index == 1) {
					return enemyLeader.interruptCombatSpecialCase();
				}
				return null;
			}
			if(escaped) {
				if (index == 1) {
					return new ResponseEffectsOnly("逃脱！", "你逃走了！"){
						@Override
						public void effects() {
							enemyLeader.applyEscapeCombatEffects();
							for(NPC enemy : enemies) {
								enemy.addFlag(NPCFlagValue.playerEscapedLastCombat);
							}
							Main.game.setInCombat(false);
							Main.game.setResponseTab(0);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
				} else {
					return null;
				}
				
			} else if(isEnemyPartyDefeated()) {
				if (index == 1) {
					return new ResponseEffectsOnly("胜利", UtilText.parse(enemyLeader, "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>你打败了[npc.name]！</span>")){
						@Override
						public void effects() {
							endCombat(true);
							Main.game.setResponseTab(0);
							Main.game.setContent(getEndCombatDialogue(true, true));
						}
					};
				}
				return null;
				
			} else if(isAlliedPartyDefeated()) {
				if (index == 1) {
					return new ResponseEffectsOnly("败北", "你被打败了！"){
						@Override
						public void effects() {
							endCombat(false);
							Main.game.setResponseTab(0);
							Main.game.setContent(getEndCombatDialogue(true, false));
						}
					};
				}
				return null;
				
			} else if(Main.game.getPlayer().isStunned()) {
				if (index == 1) {
					return new Response("眩晕！", "本回合你无法行动！", ENEMY_ATTACK){
						@Override
						public void effects() {
							combatContent.put(Main.game.getPlayer(), Util.newArrayListOfValues("你被眩晕了，无法做出动作！"));
							endCombatTurn();
						}
					};
				}
				return null;
				
			} else if(isCombatantDefeated(Main.game.getPlayer())) {
				if (index == 1) {
					return new Response("观战", "你已败了，便只能眼睁睁地看着伙伴继续战斗！", ENEMY_ATTACK){
						@Override
						public void effects() {
							combatContent.put(Main.game.getPlayer(), Util.newArrayListOfValues("你已败了，便只能眼睁睁地看着伙伴继续战斗！"));
							endCombatTurn();
						}
					};
				}
				return null;
			}

			List<GameCharacter> pcEnemies = getEnemies(Main.game.getPlayer());
			List<GameCharacter> pcAllies = getAllies(Main.game.getPlayer());
			pcAllies.add(Main.game.getPlayer());

			if(index == 0) {
				return new Response("结束回合",
						Main.game.getPlayer().getRemainingAP()<=0?"结束当前回合":"要结束当前回合吗？你还有未使用的行动点！",
						ENEMY_ATTACK){
					@Override
					public void effects() {
						endCombatTurn();
					}
					@Override
					public Colour getHighlightColour() {
						if(Main.game.getPlayer().getRemainingAP() > 0) {
							return PresetColour.GENERIC_BAD;
						} else {
							return PresetColour.GENERIC_GOOD;
						}
					}
				};
				
			} else if(index<=10 || index>14) {
				
				int moveIndex =
						index<=10
							?index-1
							:index-5;
				
				if(responseTab==0 || responseTab==1) {
					moveIndex =
							index<=8
								?index-1
								:index-7;
					
					if(index==9) {
						if(Main.combat.isSubmitBlocked()) {
							return new Response("屈服", "这是一场无法放弃的战斗！", null);
						} else {
							return new Response("屈服",
									(getEnemies(Main.game.getPlayer()).size()==1
										?"向对手投降，任人肆意摆弄为所欲为。"
										:"向对手投降，任人肆意摆弄为所欲为。"),
									SUBMIT);
						}
						
					} else if(index==10) {
						if (escapeChance == 0) {
							return new Response("逃跑", "这是你无法逃避的战斗！", null);
							
						} else if(!Main.game.getPlayer().isAbleToEscape()) {
							return new Response("逃跑", Main.game.getPlayer().getUnableToEscapeDescription(), null);
							
						} else {
							return new Response("逃跑",
									"试图逃跑。<br/><br/>"
									+ (escapeChance==100 && Main.game.getPlayer().hasTrait(Perk.JOB_ATHLETE, true)
										?"你发动<b style='color:"+Perk.JOB_ATHLETE.getColour().toWebHexString()+";'>"
													+Perk.JOB_ATHLETE.getName(Main.game.getPlayer())+"</b>的效果，从战斗中轻松地脱身了！"
										:(escapeChance==100 && Main.game.getPlayer().getSubspecies()==Subspecies.CAT_MORPH_CHEETAH
												?"还好你有猎豹化形的躯体，轻而易举地就从战斗中脱身了！"
												:""))
									+"你有"+escapeChance+"%机率逃脱！",
									ENEMY_ATTACK){
								@Override
								public void effects() {
									escape(Main.game.getPlayer());
									endCombatTurn();
								}
							};
						}

					}
				}
						
				if(responseTab==0) {
					if(Main.game.getPlayer().getEquippedMoves().size()>moveIndex) {
						AbstractCombatMove move = Main.game.getPlayer().getEquippedMoves().get(moveIndex);
						
						return getMoveResponse(move, pcEnemies, pcAllies);
						
					} else if(index<=8) {
						return new Response("-",
								"这是空闲的核心战斗动作栏位。在战斗外可以通过打开手机菜单，选择“战斗动作”，以添加核心战斗动作。",
								null);
					}
					
				} else if(responseTab==1) {
					if(Main.game.getPlayer().getAvailableBasicMoves().size()>moveIndex) {
						AbstractCombatMove move = Main.game.getPlayer().getAvailableBasicMoves().get(moveIndex);
						
						return getMoveResponse(move, pcEnemies, pcAllies);
					}
					
				} else if(responseTab==2) {
					if(Main.game.getPlayer().getAvailableSpecialMoves().size()>moveIndex) {
						AbstractCombatMove move = Main.game.getPlayer().getAvailableSpecialMoves().get(moveIndex);
						
						return getMoveResponse(move, pcEnemies, pcAllies);
					}
					
				} else if(responseTab==3) {
					if(Main.game.getPlayer().getAvailableSpellMoves().size()>moveIndex) {
						AbstractCombatMove move = Main.game.getPlayer().getAvailableSpellMoves().get(moveIndex);
						
						return getMoveResponse(move, pcEnemies, pcAllies);
					}
					
				} else if(responseTab==4) {
					String costDescription = "<br/>[style.colourMinorGood(这是一项自由动作，不会消耗任何AP，也不会结束回合。)]";
					//TODO set behaviour and recalculate moves
					
					for(int i=1; i<=CombatBehaviour.values().length; i++) {
						if(index==i) {
							CombatBehaviour behaviour = CombatBehaviour.values()[i-1];
							
							if(targetedAlly.isPlayer()) {
								return new Response(Util.capitaliseSentence(behaviour.getName()), "你不能对自己发号施令！", null);
							}
							return new Response(
									Util.capitaliseSentence(behaviour.getName()),
									(targetedAlly.getCombatBehaviour()==behaviour
										?"[style.italicsMinorGood("+behaviour.getDescription(targetedAlly)+")]"
												+ UtilText.parse(targetedAlly, "<br/>[style.italics(你可以选择该动作，让[npc.name]重新决定行动。)]")
										:behaviour.getOrderDescription(targetedAlly)+costDescription),
									ENEMY_ATTACK) {
								@Override
								public Colour getHighlightColour() {
									if(targetedAlly.getCombatBehaviour()==behaviour) {
										return PresetColour.GENERIC_GOOD;
									}
									return super.getHighlightColour();
								}
								@Override
								public void effects() {
									targetedAlly.setCombatBehaviour(behaviour);
									
									// Sets up NPC ally/enemy lists that include player
									List<GameCharacter> npcAllies= getAllies(targetedAlly);
									List<GameCharacter> npcEnemies = getEnemies(targetedAlly);
									npcAllies.removeIf((character)->isCombatantDefeated(character));
									npcEnemies.removeIf((character)->isCombatantDefeated(character));
									
									// Figures out the new moves
									int i = 0;
									for(Value<GameCharacter, AbstractCombatMove> move : targetedAlly.getSelectedMoves()) {
										move.getValue().performOnDeselection(i,
												targetedAlly,
												move.getKey(),
												new ArrayList<>(npcEnemies),
												new ArrayList<>(npcAllies));
										targetedAlly.setCooldown(move.getValue().getIdentifier(), 0);
										i++;
									}
									targetedAlly.resetSelectedMoves();
									targetedAlly.setRemainingAP(targetedAlly.getMaxAP(), npcEnemies, npcAllies);
									targetedAlly.selectMoves(npcEnemies, npcAllies);
									
									predictionContent.put(targetedAlly, targetedAlly.getMovesPredictionString(npcEnemies, npcAllies));
								}
							};
						}
					}
					// Change targets:
					if(index==CombatBehaviour.values().length+1) {
						if(targetedAlly.isPlayer()) {
							return new Response("循环选取", "你不能对自己发号施令！", null);
						}
						return new Response(
								"循环选取",
								UtilText.parse(targetedAlly, "告诉[npc.name]改变攻击对象。[npc.She]正在瞄准[style.colourBad("
									+ (getPreferredTarget(targetedAlly)==null
										?"任意角色"
										:UtilText.parse(getPreferredTarget(targetedAlly), "[npc.name]"))
									+")]。")
									+costDescription,
								ENEMY_ATTACK) {
							@Override
							public void effects() {
								// Sets up NPC ally/enemy lists that include player
								List<GameCharacter> npcAllies= getAllies(targetedAlly);
								List<GameCharacter> npcEnemies = getEnemies(targetedAlly);
								npcAllies.removeIf((character)->isCombatantDefeated(character));
								npcEnemies.removeIf((character)->isCombatantDefeated(character));
								
								// Figures out the new moves
								int i = 0;
								for(Value<GameCharacter, AbstractCombatMove> move : targetedAlly.getSelectedMoves()) {
									move.getValue().performOnDeselection(i,
											targetedAlly,
											move.getKey(),
											new ArrayList<>(npcEnemies),
											new ArrayList<>(npcAllies));
									targetedAlly.setCooldown(move.getValue().getIdentifier(), 0);
									i++;
								}
								
								// Set the preferred target:
								int currentTargetIndex = 0;
								if(getPreferredTarget(targetedAlly)!=null) {
									currentTargetIndex = enemies.indexOf(getPreferredTarget(targetedAlly));
								}
								List<GameCharacter> enemiesDoubled = new ArrayList<>(enemies);
								enemiesDoubled.addAll(enemies);
								for(int enemyIdx=0; enemyIdx<enemiesDoubled.size(); enemyIdx++) {
									GameCharacter enemyAtIndex = enemiesDoubled.get(enemyIdx);
									if(!isCombatantDefeated(enemyAtIndex) && getPreferredTarget(targetedAlly)!=enemyAtIndex && (enemyIdx>currentTargetIndex || getPreferredTarget(targetedAlly)==null)) {
										setPreferredTarget(targetedAlly, enemyAtIndex);
										break;
									}
								}
								
								targetedAlly.resetSelectedMoves();
								targetedAlly.setRemainingAP(targetedAlly.getMaxAP(), npcEnemies, npcAllies);
								targetedAlly.selectMoves(npcEnemies, npcAllies);
								
								predictionContent.put(targetedAlly, targetedAlly.getMovesPredictionString(npcEnemies, npcAllies));
							}
						};
					}
					// Clear target:
					if(index==CombatBehaviour.values().length+2) {
						if(targetedAlly.isPlayer()) {
							return new Response("清空选取", "你不能对自己发号施令！", null);
						}
						if(getPreferredTarget(targetedAlly)==null) {
							return new Response("清空选取", UtilText.parse(targetedAlly, "[npc.Name]已经选好想选的目标了！"), null);
						}
						return new Response(
								"清空选取",
								UtilText.parse(targetedAlly, "告诉[npc.name]随意选取角色。[npc.She]正在瞄准[style.colourBad("
									+ (getPreferredTarget(targetedAlly)==null
										?"任意角色"
										:UtilText.parse(getPreferredTarget(targetedAlly), "[npc.name]"))
									+")]。")
									+costDescription,
								ENEMY_ATTACK) {
							@Override
							public void effects() {
								// Sets up NPC ally/enemy lists that include player
								List<GameCharacter> npcAllies= getAllies(targetedAlly);
								List<GameCharacter> npcEnemies = getEnemies(targetedAlly);
								npcAllies.removeIf((character)->isCombatantDefeated(character));
								npcEnemies.removeIf((character)->isCombatantDefeated(character));
								
								// Figures out the new moves
								int i = 0;
								for(Value<GameCharacter, AbstractCombatMove> move : targetedAlly.getSelectedMoves()) {
									move.getValue().performOnDeselection(i,
											targetedAlly,
											move.getKey(),
											new ArrayList<>(npcEnemies),
											new ArrayList<>(npcAllies));
									targetedAlly.setCooldown(move.getValue().getIdentifier(), 0);
									i++;
								}
								
								// Set the preferred target:
								setPreferredTarget(targetedAlly, null);
								
								targetedAlly.resetSelectedMoves();
								targetedAlly.setRemainingAP(targetedAlly.getMaxAP(), npcEnemies, npcAllies);
								targetedAlly.selectMoves(npcEnemies, npcAllies);
								
								predictionContent.put(targetedAlly, targetedAlly.getMovesPredictionString(npcEnemies, npcAllies));
							}
						};
					}
				}
				
			} else if(index==11) {
				return new ResponseEffectsOnly("[style.colourGood(目标：)] "+(getTargetedAlliedCombatant().isPlayer()?"你自己":Util.capitaliseSentence(getTargetedAlliedCombatant().getName())),
						"你可以使用该动作或点击屏幕左侧的名称，循环浏览目标盟友。") {
					@Override
					public void effects() {
						List<GameCharacter> alliesPlusPlayer = Util.newArrayListOfValues(Main.game.getPlayer());
						alliesPlusPlayer.addAll(getAllies(Main.game.getPlayer()));
						if(alliesPlusPlayer.size()==1) {
							return;
						}
						for(int i=0; i<alliesPlusPlayer.size(); i++) {
							if(alliesPlusPlayer.get(i).equals(getTargetedAlliedCombatant())) {
								if(i+1<alliesPlusPlayer.size()) {
									setTargetedCombatant(alliesPlusPlayer.get(i+1));
									break;
								} else {
									setTargetedCombatant(alliesPlusPlayer.get(0));
									break;
								}
							}
						}
					}
				};

			} else if(index==12) {
				return new ResponseEffectsOnly("[style.colourBad(目标：)] "+Util.capitaliseSentence(getTargetedCombatant().getName()),
						"你可以使用该动作或点击屏幕右侧的名称，循环浏览目标敌人。") {
					@Override
					public void effects() {
						List<GameCharacter> playerEnemies = getEnemies(Main.game.getPlayer());
						if(playerEnemies.size()==1) {
							return;
						}
						for(int i=0; i<playerEnemies.size(); i++) {
							if(playerEnemies.get(i).equals(getTargetedCombatant())) {
								if(i+1<playerEnemies.size()) {
									setTargetedCombatant(playerEnemies.get(i+1));
									break;
								} else {
									setTargetedCombatant(playerEnemies.get(0));
									break;
								}
							}
						}
					}
				};

			} else if(index==14) {
				return new Response("重置",
						Main.game.getPlayer().getSelectedMoves().size()==0
								?"你还没有选择任何动作，所以无法重置！"
								:"重置所选动作，让你可以为该回合选择其他行动。",
							Main.game.getPlayer().getSelectedMoves().size()==0
								?null
								:ENEMY_ATTACK) {
					@Override
					public void effects() {
						if(Main.game.isInCombat()) {
							int i = 0;
							for(Value<GameCharacter, AbstractCombatMove> move : Main.game.getPlayer().getSelectedMoves()) {
								move.getValue().performOnDeselection(i,
										Main.game.getPlayer(),
										move.getKey(),
										new ArrayList<>(enemies),
										new ArrayList<>(allies));
								Main.game.getPlayer().setCooldown(move.getValue().getIdentifier(), 0);
								i++;
							}
						}
						Main.game.getPlayer().resetSelectedMoves();
						Main.game.getPlayer().setRemainingAP(Main.game.getPlayer().getMaxAP(), pcEnemies, pcAllies);
						predictionContent.put(Main.game.getPlayer(), new ArrayList<>());
					}
				};

			}
			
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.NORMAL;
		}
	};
	
	private Response getMoveResponse(AbstractCombatMove move, List<GameCharacter> pcEnemies, List<GameCharacter> pcAllies) {
		GameCharacter moveTarget = move.isCanTargetAllies()||move.isCanTargetSelf()?getTargetedAlliedCombatant():getTargetedCombatant();

		int selectedMoveIndex = Main.game.getPlayer().getSelectedMoves().size();
		
		String rejectionReason = move.isUsable(selectedMoveIndex, Main.game.getPlayer(), moveTarget, pcEnemies, pcAllies);
		if(rejectionReason != null) {
			return new Response(Util.capitaliseSentence(move.getName(selectedMoveIndex, Main.game.getPlayer())),
								rejectionReason,
					null);
		}
		StringBuilder moveStatblock = new StringBuilder();
		
		boolean isCrit = move.canCrit(selectedMoveIndex, Main.game.getPlayer(), moveTarget, pcEnemies, pcAllies);
		
		if(move.getStatusEffects(Main.game.getPlayer(), moveTarget, isCrit)!=null && !move.getStatusEffects(Main.game.getPlayer(), moveTarget, isCrit).isEmpty()) {
			for(Entry<AbstractStatusEffect, Integer> entry : move.getStatusEffects(Main.game.getPlayer(), moveTarget, isCrit).entrySet()) {
				moveStatblock.append("施加<b style='color:"+entry.getKey().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getName(moveTarget))+"</b>"
						+ "<b>"+entry.getValue()+ "回合" +"</b><br/>");
			}
		}
		
		StringBuilder critText = new StringBuilder();
		critText.append("<br/>[style.colourCrit(暴击要求)]：");
		for(String s : move.getCritRequirements(Main.game.getPlayer(), moveTarget, pcEnemies, pcAllies)) {
			critText.append(s);
		}
		
		String predictionTooltip = move.getPrediction(selectedMoveIndex, Main.game.getPlayer(), moveTarget, pcEnemies, pcAllies);
		
		return new Response(Util.capitaliseSentence(move.getName(selectedMoveIndex, Main.game.getPlayer())),
			moveStatblock.toString()
				+ predictionTooltip
				+ critText.toString(),
			ENEMY_ATTACK){
			@Override
			public void effects() {
				Main.game.getPlayer().selectMove(Main.game.getPlayer().getSelectedMoves().size(), move, moveTarget, pcEnemies, pcAllies);
				// Reset prediction content as this selected move may have altered the prediction of previous moves:
				predictionContent.put(Main.game.getPlayer(), new ArrayList<>());
				int i=0;
				for(Value<GameCharacter, AbstractCombatMove> selectedMove : Main.game.getPlayer().getSelectedMoves()) {
					predictionContent.get(Main.game.getPlayer()).add(selectedMove.getValue().getPrediction(i, Main.game.getPlayer(), selectedMove.getKey(), pcEnemies, pcAllies));
					i++;
				}
			}
			@Override
			public Colour getHighlightColour() {
				return move.getColourByDamageType(selectedMoveIndex, Main.game.getPlayer());
			}
			@Override
			public AbstractCombatMove getAssociatedCombatMove() {
				return move;
			}
		};
	}
	
	public List<String> applyExtraAttackEffects(GameCharacter attacker, GameCharacter target, Attack attackType, AbstractWeapon weapon, boolean isHit, boolean isCritical) {
		List<String> extraAttackEffects = new ArrayList<>();
		
		if(target.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES_3)
				&& (attackType==Attack.MAIN || attackType==Attack.OFFHAND || attackType==Attack.DUAL)
				&& (weapon==null || weapon.getWeaponType().isMelee())) {
			float cloakOfFlamesDamage = Math.round(5 * (1 + (target.getAttributeValue(Attribute.DAMAGE_FIRE)/100f)));
			cloakOfFlamesDamage = (Math.round(cloakOfFlamesDamage*10))/10f;
			if (cloakOfFlamesDamage < 1) {
				cloakOfFlamesDamage = 1;
			}
			attacker.incrementHealth(-cloakOfFlamesDamage);
			
			if(attacker.isPlayer()) {
				extraAttackEffects.add(UtilText.parse(target, "你受到了<b>"+cloakOfFlamesDamage+"</b>[style.boldFire(火焰伤害)]，由[npc.namePos]的[style.boldFire(火焰之环)]造成！"));
			} else {
				if(target.isPlayer()) {
					extraAttackEffects.add(UtilText.parse(attacker, "[npc.Name]受到了<b>"+cloakOfFlamesDamage+"</b>[style.boldFire(火焰伤害)]，由你的[style.boldFire(火焰之环)]造成！"));
				} else {
					extraAttackEffects.add(UtilText.parse(attacker, target, "[npc1.Name]受到了<b>"+cloakOfFlamesDamage+"</b>[style.boldFire(火焰伤害)]，由[npc2.namePos]的[style.boldFire(火焰之环)]造成！"));
				}
			}
		}
		
		if(isCritical && target.hasStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST)) {
			target.removeStatusEffect(StatusEffect.RAIN_CLOUD);
			target.removeStatusEffect(StatusEffect.RAIN_CLOUD_CLOUDBURST);
			target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DEEP_CHILL);
			target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR);
			target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST);
			
			addStatusEffectToApply(target, StatusEffect.RAIN_CLOUD_CLOUDBURST, 6);
			
			extraAttackEffects.add(UtilText.parse(target, "由于[npc.name]被暴击集中，[npc.her]头顶的雨云尺寸增大了，并且突然爆发出汹涌的云流！"));
			
			extraAttackEffects.add(Spell.getBasicStatusEffectApplication(target, false, Util.newHashMapOfValues(new Value<>(StatusEffect.RAIN_CLOUD_CLOUDBURST, 6))));
			
		}
		
		if(attacker.isPlayer() && attacker.hasFetish(Fetish.FETISH_SADIST) && isCritical && isHit) {
			extraAttackEffects.add(
							"由于你的[style.boldFetish(施虐性癖)]，击中他人要害带来的快感化成了一份奥术精华！<br/>"
							+Main.game.getPlayer().incrementEssenceCount(1, false));
		}
		
		if(target.isPlayer() && target.hasFetish(Fetish.FETISH_MASOCHIST) && isCritical && isHit) {
			extraAttackEffects.add(
							"由于你的[style.boldFetish(受虐性癖)]，被击中要害带来的快感化成了一份奥术精华！<br/>"
							+Main.game.getPlayer().incrementEssenceCount(1, false));
		}

		extraAttackEffects.add(CombatExtras.applyExtraAttackEffects(attacker, target, attackType, isHit, isCritical));
		return extraAttackEffects;
	}

	private void escape(GameCharacter escapee) {
		attemptedEscape = true;
		
		boolean allEnemiesStunned = true;
		if(escapee.isPlayer() || getAllies(Main.game.getPlayer()).contains(escapee)) {
			for(GameCharacter enemy : getEnemies(Main.game.getPlayer())) {
				if(!enemy.isStunned()) {
					allEnemiesStunned = false;
				}
			}
		} else {
			if(Main.game.getPlayer().isStunned()) {
				allEnemiesStunned = false;
			}
			for(GameCharacter ally : getAllies(Main.game.getPlayer())) {
				if(ally.isStunned()) {
					allEnemiesStunned = false;
				}
			}
		}
		
		escapeDescriptionMap = new HashMap<>();
		StringBuilder escapeDescription = new StringBuilder();
		if(allEnemiesStunned) {
			escaped = true;
			escapeDescription.append("所有的敌人都被震慑，所以你轻松脱身了！");
		} else if (Util.random.nextInt(100) < escapeChance) {
			escaped = true;
			escapeDescription.append("你顺利脱身了！");
		} else {
			escapeDescription.append("你没能逃掉！");
		}
		escapeDescriptionMap.put(escapee, Util.newArrayListOfValues(escapeDescription.toString()));
		
		for(GameCharacter combatant : getAllCombatants(true)) {
			if(getAllies(escapee).contains(combatant) || combatant.equals(escapee)) {
				int i = 0;
				for(Value<GameCharacter, AbstractCombatMove> move : combatant.getSelectedMoves()) {
					move.getValue().performOnDeselection(i,
							combatant,
							move.getKey(),
							new ArrayList<>(enemies),
							new ArrayList<>(allies));
					combatant.setCooldown(move.getValue().getIdentifier(), 0);
					i++;
				}
				combatant.resetSelectedMoves();
				combatant.setRemainingAP(combatant.getMaxAP(), getEnemies(combatant), getAllies(combatant));
				predictionContent.put(combatant, new ArrayList<>());
				if(escaped && !combatant.equals(escapee)) {
					escapeDescriptionMap.put(combatant,
							Util.newArrayListOfValues(UtilText.parse(combatant, "[npc.Name]逃离了！")));
				}
			} else {
				if(escaped) {
					escapeDescriptionMap.put(combatant,
							Util.newArrayListOfValues(UtilText.parse(combatant, "[npc.Name]试图阻止你逃跑，但失败了！")));
				}
			}
		}
		
		if(escaped) {
			// Remove elementals:
			for(GameCharacter combatant : getAllCombatants(true)) {
				if(combatant.isElementalSummoned()) {
					combatant.getElemental().returnToHome();
					escapeDescription.append(UtilText.parse(combatant, combatant.getElemental(),
							"<p style='text-align:center;'><i>"
								+ "[npc.NamePos]的元素体<span style='colour:"+combatant.getElemental().getFemininity().getColour().toWebHexString()+";'>[npc2.name]</span>"
									+ "耗尽了能量，于是[style.italicsArcane(变回了被动形态)]！"
							+ "</i></p>"));
				}
			}
		}
	}

	/**
	 * Calculations for enemy attack.
	 * @param character The character performing an attack turn.
	 * @return true if the character is able to perform an attack, false if they cannot (due to being defeated, stunned, or attempting to escape).
	 */
	private boolean attackCharacter(GameCharacter character) {
		if(escaped) {
			combatContent.put(character, escapeDescriptionMap.get(character));
			return false;
		}
		
		if(character.isPlayer()) {
			if (!activeCombatants.contains(character)) {
				combatContent.put(character,
						Util.newArrayListOfValues(UtilText.parse(character, "<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>被击败了！</span>")));
				return false;
				
			} else if(attemptedEscape) {
				combatContent.put(character,
						Util.newArrayListOfValues(UtilText.parse(character, "<span style='color:"+PresetColour.GENERIC_MINOR_BAD.toWebHexString()+";'>你没能逃跑！</span>")));
				return false;
				
			}
			
		} else if(allies.contains(character)) {
			if (!activeCombatants.contains(character)) {
				combatContent.put(character,
						Util.newArrayListOfValues(UtilText.parse(character, "<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>被击败了！</span>")));
				return false;
				
			} else if(attemptedEscape) {
				combatContent.put(character,
						Util.newArrayListOfValues(UtilText.parse(character, "<span style='color:"+PresetColour.GENERIC_MINOR_BAD.toWebHexString()+";'>[npc.Name]没能和你逃跑！</span>")));
				return false;
				
			}
			
		} else {
			if (!activeCombatants.contains(character)) {
				combatContent.put(character,
						Util.newArrayListOfValues(UtilText.parse(character, "<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>被击败了！</span>")));
				return false;
			}
		}
		
		if(character.isStunned()) {
			combatContent.put(character,
					Util.newArrayListOfValues(UtilText.parse(character, "[npc.NameIsFull]被眩晕了，无法做出动作！")));

			character.resetSelectedMoves();
			return false;
		}
		
		return true;
	}

	private StringBuilder endTurnStatusEffectText = new StringBuilder();
	
	private void applyNewTurnShielding(GameCharacter character) {
	    character.resetShields();
	    
	    int bonusEnergyShielding = Math.round(character.getAttributeValue(Attribute.ENERGY_SHIELDING));
		character.incrementShields(DamageType.HEALTH, bonusEnergyShielding);
		
		DamageType[] damageTypes = new DamageType[] {DamageType.PHYSICAL, DamageType.FIRE, DamageType.ICE, DamageType.POISON};
		for(DamageType dt : damageTypes) {
			character.incrementShields(dt, Math.round(character.getAttributeValue(dt.getResistAttribute())));
		}
		
	    character.incrementShields(DamageType.LUST, Math.round(character.getAttributeValue(DamageType.LUST.getResistAttribute())));
	}
	
	public void endCombatTurn() {
		combatTurnResolutionStringBuilder.setLength(0);
		
		List<GameCharacter> combatants = getAllCombatants(true); // To avoid concurrent modification when the 'summon elemental' spell adds combatants.
		
		// Perform moves based on following ordering, so that all defensive and support abilities are applied before attacks start landing:
		CombatMoveType[] order = new CombatMoveType[] {CombatMoveType.DEFEND, CombatMoveType.SPELL, CombatMoveType.ATTACK};
		for(GameCharacter character : getAllCombatants(true)) {
			combatContent.put(character, new ArrayList<>());
		}
		for(int i=0;i<order.length;i++) {
			for(GameCharacter character : combatants) {
				if(attackCharacter(character)) {
					List<GameCharacter> npcAllies = getAllies(character);
					List<GameCharacter> npcEnemies = getEnemies(character);
					// Performs the actions
					character.performMoves(order[i], combatContent.get(character), npcEnemies, npcAllies);
				}
			}
			// After all defensive and supportive moves have been made, apply the queued up status effects before the attacks start hitting, as they should only be defensive or supportive-based.
			if(i==2) {
				for(GameCharacter character : combatants) {
					for(Entry<AbstractStatusEffect, Integer> entry : statusEffectsToApply.get(character).entrySet()) {
						character.addStatusEffect(entry.getKey(), entry.getValue()+1);// Add 1 to the status effect duration, as it gets immediately decremented by 1 within the getCharactersTurnDiv() method (as it calls the applyEffects() method).
					}
					statusEffectsToApply.put(character, new HashMap<>());
				}
			}
		}
		for(GameCharacter character : combatants) {
			// Handle thrown weapons:
			StringBuilder thrownWeaponsRecoveredDescriptions = new StringBuilder();
        	for(int i=0; i<Math.min(character.getArmRows(), character.getMainWeaponArray().length); i++) {
				for(Entry<AbstractWeapon, Integer> entry : weaponsThrownDuringTurn.get(character).get(InventorySlot.mainWeaponSlots[i]).entrySet()) {
					AbstractWeapon weapon = entry.getKey();
					for(int count=0; count<entry.getValue(); count++) {
						if(Math.random()*100 <= weapon.getWeaponType().getOneShotChanceToRecoverAfterTurn()) {
							if(character.getMainWeapon(i)==null) {
								character.equipMainWeapon(weapon, i, false);
							} else {
								character.addWeapon(weapon, 1, false, false);
							}
							incrementWeaponsThrownDuringCombat(character, InventorySlot.mainWeaponSlots[i], weapon, -1);
							if(thrownWeaponsRecoveredDescriptions.length()==0) {
								thrownWeaponsRecoveredDescriptions.append("[style.boldGood(单发武器装填：)]");
							}
							thrownWeaponsRecoveredDescriptions.append("<br/>");
							thrownWeaponsRecoveredDescriptions.append(weapon.getWeaponType().getOneShotEndTurnRecoveryDescription(character).trim());
						}
					}
				}
			}
        	for(int i=0; i<Math.min(character.getArmRows(), character.getOffhandWeaponArray().length); i++) {
				for(Entry<AbstractWeapon, Integer> entry : weaponsThrownDuringTurn.get(character).get(InventorySlot.offhandWeaponSlots[i]).entrySet()) {
					AbstractWeapon weapon = entry.getKey();
					for(int count=0; count<entry.getValue(); count++) {
						if(Math.random()*100 <= weapon.getWeaponType().getOneShotChanceToRecoverAfterTurn()) {
							if(character.getOffhandWeapon(i)==null) {
								character.equipOffhandWeapon(weapon, i, false);
							} else {
								character.addWeapon(weapon, 1, false, false);
							}
							incrementWeaponsThrownDuringCombat(character, InventorySlot.offhandWeaponSlots[i], weapon, -1);
							if(thrownWeaponsRecoveredDescriptions.length()==0) {
								thrownWeaponsRecoveredDescriptions.append("[style.boldGood(单发武器装填：)]");
							}
							thrownWeaponsRecoveredDescriptions.append("<br/>");
							thrownWeaponsRecoveredDescriptions.append(weapon.getWeaponType().getOneShotEndTurnRecoveryDescription(character).trim());
						}
					}
				}
			}
        	resetWeaponsThrownDuringTurn(character);
        	combatContent.get(character).add(thrownWeaponsRecoveredDescriptions.toString());
        	
			combatTurnResolutionStringBuilder.append(getCharactersTurnDiv(character, getTurn()==0?"准备":"", combatContent.get(character)));
			
			character.resetSelectedMoves();
		}
		
		attemptedEscape = false;
		
		// End turn effects:
		for(GameCharacter character : combatants) {
			for(Entry<AbstractStatusEffect, Integer> entry : statusEffectsToApply.get(character).entrySet()) {
				character.addStatusEffect(entry.getKey(), entry.getValue());
			}
			statusEffectsToApply.put(character, new HashMap<>());
			
			List<GameCharacter> npcAllies = getAllies(character);
			List<GameCharacter> npcEnemies = getEnemies(character);
			
			applyNewTurnShielding(character);
			character.lowerMoveCooldowns();
			character.setRemainingAP(character.getMaxAP(), npcEnemies, npcAllies);
			
			if(isCombatantDefeated(character)) {
				if(activeCombatants.remove(character)) {
					List<String> vampyres = new ArrayList<>();
					boolean playerVampyre = false;
					float manaAbsorbed = Math.round(character.getMana()/2);
					for(GameCharacter c2 : combatants) {
						if(!isCombatantDefeated(c2) && c2.hasTraitActivated(Perk.ARCANE_VAMPYRISM)) {
							if(c2.isPlayer()) {
								vampyres.add(0, UtilText.parse(c2,"[npc.name]"));
								playerVampyre = true;
							} else {
								vampyres.add(UtilText.parse(c2,"[npc.name]"));
							}
							c2.incrementMana(manaAbsorbed);
						}
					}
					if(!vampyres.isEmpty()) {
						character.setMana(manaAbsorbed);
	
						predictionContent.put(character, Util.newArrayListOfValues(
										UtilText.parse(character,
												"[style.boldArcane("+(Util.capitaliseSentence(Perk.ARCANE_VAMPYRISM.getName(Main.game.getPlayer())))+":)]<br/>"
														+Util.capitaliseSentence(Util.stringsToStringList(vampyres, false))+"吸收了"
														+"[npc.namePos]剩余灵气的一半，"
														+ (enemies.contains(character)
																?"[style.colourGood("
																:"[style.colourBad(")
														+"获得了 "+manaAbsorbed+"灵气)]！")));
					} else {
						predictionContent.put(character, Util.newArrayListOfValues("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>战败……</span>"));
					}
					
				} else {
					predictionContent.put(character, Util.newArrayListOfValues("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>战败……</span>"));
				}
				
			} else if(character.isStunned()) {
				predictionContent.put(character, Util.newArrayListOfValues("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>眩晕！</span>"));
				
			} else {
				if(character.isPlayer()) {
					predictionContent.put(character, new ArrayList<>());
					
				} else {
					npcAllies.removeIf((c)->isCombatantDefeated(c));
					npcEnemies.removeIf((c)->isCombatantDefeated(c));
					
					// Figures out new moves for NPCs:
					character.selectMoves(npcEnemies, npcAllies);
					predictionContent.put(character, character.getMovesPredictionString(npcEnemies, npcAllies));
				}
			}
		}
		
		if(isCombatantDefeated(targetedEnemy)) {
			for(NPC enemy : enemies) {
				if(!isCombatantDefeated(enemy)) {
					targetedEnemy = enemy;
					break;
				}
			}
		}
		
		if(isCombatantDefeated(targetedAlly)) {
			targetedAlly = Main.game.getPlayer();
		}
		
		turn++;
	}

	private String getShieldsDisplayValue(AbstractAttribute att, int shields) {
		String valueForDisplay = String.valueOf(shields);
		if(att.isInfiniteAtUpperLimit() && shields>=att.getUpperLimit()) {
			valueForDisplay = UtilText.getInfinitySymbol(false);
		}
		return valueForDisplay;
	}
	
	private String getTitleResources(GameCharacter character) {
		int apRemaining = character.getRemainingAP();
		StringBuilder sb = new StringBuilder();
		
		sb.append("<b>(<span style='color:"+(apRemaining==0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD).toWebHexString()+";'>"+apRemaining+"</span>/"+character.getMaxAP()+" AP)</b>");
		
		sb.append("<div class='container-full-width' style='text-align:center;'>");
		
		boolean shieldsFound = false;
		int shields = character.getShields(DamageType.HEALTH);
		if(shields!=0) {
			shieldsFound = true;
			sb.append("<div style='display:inline-block; float:none; margin:auto; padding:0 2px; background-color:"+PresetColour.BACKGROUND.toWebHexString()+"; border-radius:5px; width:auto; position:relative;'>"
							+"<span style='color:"+DamageType.HEALTH.getColour().toWebHexString()+";'>"+UtilText.getShieldSymbol()+"</span> "+(shields<0?"[style.colourDisabled("+shields+")]":getShieldsDisplayValue(Attribute.ENERGY_SHIELDING, shields))
							+ "<div class='overlay' id='"+character.getId()+"_COMBAT_SHIELD_"+DamageType.HEALTH+"' style='cursor:default;'></div>"
						+ "</div>");
		}

		DamageType[] damageTypes = new DamageType[] {DamageType.PHYSICAL, DamageType.FIRE, DamageType.ICE, DamageType.POISON};
		for(DamageType dt : damageTypes) {
			shields = character.getShields(dt);
			if(shields!=0) {
				if(shieldsFound) {
					sb.append(" | ");
				}
				shieldsFound = true;
				sb.append(
						"<div style='display:inline-block; float:none; margin:auto; padding:0 2px; background-color:"+PresetColour.BACKGROUND.toWebHexString()+"; border-radius:5px; width:auto; position:relative;'>"
							+"<span style='color:"+dt.getColour().toWebHexString()+";'>"+UtilText.getShieldSymbol()+"</span> "+(shields<0?"[style.colourDisabled("+shields+")]":getShieldsDisplayValue(dt.getResistAttribute(), shields))
							+ "<div class='overlay' id='"+character.getId()+"_COMBAT_SHIELD_"+dt+"' style='cursor:default;'></div>"
						+ "</div>");
			}
		}

		shields = character.getShields(DamageType.LUST);
		if(shields!=0) {
			if(shieldsFound) {
				sb.append(" | ");
			}
			shieldsFound = true;
			sb.append(
					"<div style='display:inline-block; float:none; margin:auto; padding:0 2px; background-color:"+PresetColour.BACKGROUND.toWebHexString()+"; border-radius:5px; width:auto; position:relative;'>"
						+"<span style='color:"+DamageType.LUST.getColour().toWebHexString()+";'>"+UtilText.getShieldSymbol()+"</span> "+(shields<0?"[style.colourDisabled("+shields+")]":getShieldsDisplayValue(DamageType.LUST.getResistAttribute(), shields))
						+ "<div class='overlay' id='"+character.getId()+"_COMBAT_SHIELD_"+DamageType.LUST+"' style='cursor:default;'></div>"
					+ "</div>");
		}

		sb.append("</div>");
		
		return sb.toString();
	}
	
	private String getCombatContent() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='text-align:center; box-sizing: border-box; border:6px solid "+PresetColour.BASE_WHITE.toWebHexString()+"; border-radius:5px;'>");

			sb.append("<div class='container-full-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; text-align:center;'><b>计划</b></div>");
		
			sb.append("<div class='container-half-width'>");
				
				sb.append("[style.boldGood(你)]"+ getTitleResources(Main.game.getPlayer()));
				if(predictionContent.get(Main.game.getPlayer()).isEmpty()) {
					sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+PresetColour.BACKGROUND.toWebHexString()+";'>[style.colourDisabled(没有选定行动……)]</div>");
				} else {
					for(String s : predictionContent.get(Main.game.getPlayer())) {
						sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+PresetColour.BACKGROUND.toWebHexString()+";'>"+s+"</div>");
					}
				}
				sb.append("</br>");
			
				for(GameCharacter ally : getAllies(Main.game.getPlayer())) {
					sb.append(UtilText.parse(ally, "</br>[style.boldMinorGood([npc.Name])]")+ getTitleResources(ally));
					for(String s : predictionContent.get(ally)) {
						sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+PresetColour.BACKGROUND.toWebHexString()+";'>"+s+"</div>");
					}
					sb.append("</br>");
				}
			sb.append("</div>");

			sb.append("<div class='container-half-width'>");
			for(GameCharacter enemy : getEnemies(Main.game.getPlayer())) {
				sb.append(UtilText.parse(enemy, (enemyLeader.equals(enemy)?"[style.boldBad([npc.Name])]":"</br>[style.boldMinorBad([npc.Name])]"))+ getTitleResources(enemy));
				for(String s : predictionContent.get(enemy)) {
					sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+PresetColour.BACKGROUND.toWebHexString()+";'>"+s+"</div>");
				}
				sb.append("</br>");
			}
			sb.append("</div>");
			
		sb.append("</div>");

		sb.append(combatTurnResolutionStringBuilder.toString());
		
		return sb.toString(); 
	}
	
	private String applyEffects(GameCharacter character) {
		endTurnStatusEffectText = new StringBuilder();
		List<AbstractStatusEffect> effectsToRemove = new ArrayList<>();
		for (AppliedStatusEffect appliedSe : character.getAppliedStatusEffects()) {
			AbstractStatusEffect se = appliedSe.getEffect();
			if (se.isCombatEffect()) {
				appliedSe.setSecondsPassed(turn);
				StringBuilder s = new StringBuilder();
				if(appliedSe.getEffect().getEffectInterval()<=0 || ((turn-appliedSe.getLastTimeAppliedEffect())>appliedSe.getEffect().getEffectInterval())) {
					if(appliedSe.getEffect().getEffectInterval()<=0) {
						s.append(se.applyEffect(character, 1, appliedSe.getSecondsPassed()));
						
					} else {
						for(int i=0; i<((Main.game.getSecondsPassed()-appliedSe.getLastTimeAppliedEffect())/appliedSe.getEffect().getEffectInterval()); i++) {
							if(s.length()>0) {
								s.append("<br/>");
							}
							s.append(se.applyEffect(character, 1, appliedSe.getSecondsPassed()));
						}
					}
					
					appliedSe.setLastTimeAppliedEffect(Main.game.getSecondsPassed());
					if(s.length()!=0) {
						endTurnStatusEffectText.append("<p><b style='color: " + se.getColour().toWebHexString() + "'>" + Util.capitaliseSentence(se.getName(character)) + ":</b>" + s.toString()+ "</p>");
					}
				}
				
				
//				String effectString = se.applyEffect(character, 1, appliedSe.getSecondsPassed());
//				if(!effectString.isEmpty()) {
//					endTurnStatusEffectText.append("<p><b style='color: " + se.getColour().toWebHexString() + "'>" + Util.capitaliseSentence(se.getName(character)) + ":</b> " + effectString+ "</p>");
//				}
				if(character.getStatusEffectDuration(se)>=0) { // Don't decrement below -1
					character.setCombatStatusEffectDuration(se, character.getStatusEffectDuration(se) - 1);
				}
				if (character.getStatusEffectDuration(se) == 0) { // Do not remove special effects (i.e. ones set at -1 duration)
					effectsToRemove.add(se);
				}
			}
		}
		for (AbstractStatusEffect se : effectsToRemove) {
			endTurnStatusEffectText.append(character.removeStatusEffectCombat(se));
		}
		return endTurnStatusEffectText.toString();
	}

	// Utility methods:
	private String getCombatLabel() {
		if(turn==0) {
			return "战斗：开始";
		} else {
			return "战斗：第"+turn+"回合";
		}
	}
	
	/**
	 * @return The enemy NPC which the player is targeting. Use CombatMove's getPreferredTarget for NPC targeting.
	 */
	public GameCharacter getTargetedCombatant() {
		return targetedEnemy;
	}

	/**
	 * @return The allied NPC which the player is targeting. Use COmbatMove's getPreferredTarget for NPC targeting.
	 */
	public GameCharacter getTargetedAlliedCombatant() {
		return targetedAlly;
	}

	public void setTargetedCombatant(GameCharacter targetedCombatant) {
		if(getEnemies(Main.game.getPlayer()).contains(targetedCombatant)) {
			targetedEnemy = targetedCombatant;
		} else {
			targetedAlly = targetedCombatant;
		}
	}
	
	public NPC getActiveNPC() {
		return activeNPC;
	}

	public void setActiveNPC(NPC activeNPC) {
		this.activeNPC = activeNPC;
	}

	public void resetItemsToBeUsed(GameCharacter character) {
		itemsToBeUsed.put(character, new ArrayList<>());
	}
	
	public List<Value<GameCharacter, AbstractItem>> getItemsToBeUsed(GameCharacter user) {
		return itemsToBeUsed.get(user);
	}

	public void addItemToBeUsed(GameCharacter user, GameCharacter target, AbstractItem item) {
		itemsToBeUsed.get(user).add(new Value<>(target, item));
		//TODO test combatmove
		predictionContent.get(Main.game.getPlayer()).add(CombatMove.ITEM_USAGE.getPrediction(user.getSelectedMoves().size(), user, target, getEnemies(user), getAllies(user)));
		Main.game.getPlayer().selectMove(user.getSelectedMoves().size(), CombatMove.ITEM_USAGE, target, getEnemies(user), getAllies(user));
	}
	
	// Thrown weapon methods:
	
	public void resetWeaponsThrownDuringTurn(GameCharacter character) {
		weaponsThrownDuringTurn.put(character, new HashMap<>());
		for(InventorySlot slot : InventorySlot.allWeaponSlots) {
			weaponsThrownDuringTurn.get(character).put(slot, new HashMap<>());
		}
	}
	
	public Map<AbstractWeapon, Integer> getWeaponsThrownDuringTurn(GameCharacter user, InventorySlot slot) {
		return weaponsThrownDuringTurn.get(user).get(slot);
	}

	public void incrementWeaponsThrownDuringTurn(GameCharacter user, InventorySlot slot, AbstractWeapon weapon, int increment) {
		weaponsThrownDuringTurn.get(user).get(slot).putIfAbsent(weapon, 0);
		weaponsThrownDuringTurn.get(user).get(slot).put(weapon, weaponsThrownDuringTurn.get(user).get(slot).get(weapon)+increment);
	}
	
	public void resetWeaponsThrownDuringCombat(GameCharacter character) {
		weaponsThrownDuringCombat.put(character, new HashMap<>());
		for(InventorySlot slot : InventorySlot.allWeaponSlots) {
			weaponsThrownDuringCombat.get(character).put(slot, new HashMap<>());
		}
	}
	
	public Map<AbstractWeapon, Integer> getWeaponsThrownDuringCombat(GameCharacter user, InventorySlot slot) {
		return weaponsThrownDuringCombat.get(user).get(slot);
	}

	public void incrementWeaponsThrownDuringCombat(GameCharacter user, InventorySlot slot, AbstractWeapon weapon, int increment) {
		weaponsThrownDuringCombat.get(user).get(slot).putIfAbsent(weapon, 0);
		weaponsThrownDuringCombat.get(user).get(slot).put(weapon, weaponsThrownDuringCombat.get(user).get(slot).get(weapon)+increment);
	}

	public void resetThrownWeaponsDepleted(GameCharacter character) {
		thrownWeaponsDepleted.put(character, new HashMap<>());
		for(InventorySlot slot : InventorySlot.allWeaponSlots) {
			thrownWeaponsDepleted.get(character).put(slot, null);
		}
	}
	
	public AbstractWeaponType getThrownWeaponsDepleted(GameCharacter user, InventorySlot slot) {
		return thrownWeaponsDepleted.get(user).get(slot);
	}

	public void addThrownWeaponsDepleted(GameCharacter user, InventorySlot slot, AbstractWeaponType weapon) {
		thrownWeaponsDepleted.get(user).put(slot, weapon);
	}

	public void removeThrownWeaponsDepleted(GameCharacter user, InventorySlot slot) {
		thrownWeaponsDepleted.get(user).put(slot, null);
	}
	
	public String getPregnancyProtectionText(GameCharacter character) {
			return (character.isVisiblyPregnant()
					?UtilText.parse(character, "一股强大的奥术能量场保护了[npc.namePos]的孕肚，保证其未降生的后代不受任何损害。")
					:"");
	}

	public List<GameCharacter> getAllCombatants(boolean includePlayer) {
		List<GameCharacter> returnList = new ArrayList<>(allCombatants);
		if(includePlayer) {
			returnList.add(Main.game.getPlayer());
		}
		return returnList;
	}
	
	public void addAlly(NPC ally) {
		if(!allies.contains(ally)) {
			allies.add(ally);
			allCombatants.add(ally);
			ally.resetMoveCooldowns();
			
			predictionContent.put(ally, new ArrayList<>());
			itemsToBeUsed.put(ally, new ArrayList<>());
			manaBurnStack.put(ally, new Stack<>());
			statusEffectsToApply.put(ally, new HashMap<>());
			combatContent.put(ally, new ArrayList<>());
			activeCombatants.add(ally);
			
			resetWeaponsThrownDuringTurn(ally);
			resetWeaponsThrownDuringCombat(ally);
			resetThrownWeaponsDepleted(ally);
			
			if(Main.game.isInCombat()) {
				List<GameCharacter> npcAllies = getAllies(ally);
				List<GameCharacter> npcEnemies = getEnemies(ally);
				
				applyNewTurnShielding(ally);
				ally.setRemainingAP(ally.getMaxAP(), npcEnemies, npcAllies);
				
				npcAllies.removeIf((c)->isCombatantDefeated(c));
				npcEnemies.removeIf((c)->isCombatantDefeated(c));
				
				// Figures out new moves for NPCs:
				ally.selectMoves(npcEnemies, npcAllies);
				predictionContent.put(ally, ally.getMovesPredictionString(npcEnemies, npcAllies));
			}
		}
	}
	
	public void addEnemy(NPC enemy) {
		if(!enemies.contains(enemy)) {
			enemies.add(enemy);
			allCombatants.add(enemy);
			enemy.resetMoveCooldowns();
			enemy.setFoughtPlayerCount(enemy.getFoughtPlayerCount()+1);
			
			predictionContent.put(enemy, new ArrayList<>());
			itemsToBeUsed.put(enemy, new ArrayList<>());
			manaBurnStack.put(enemy, new Stack<>());
			statusEffectsToApply.put(enemy, new HashMap<>());
			combatContent.put(enemy, new ArrayList<>());
			activeCombatants.add(enemy);
	
			resetWeaponsThrownDuringTurn(enemy);
			resetWeaponsThrownDuringCombat(enemy);
			resetThrownWeaponsDepleted(enemy);
			
			if(Main.game.isInCombat()) {
				List<GameCharacter> npcAllies = getAllies(enemy);
				List<GameCharacter> npcEnemies = getEnemies(enemy);
				
				applyNewTurnShielding(enemy);
				enemy.setRemainingAP(enemy.getMaxAP(), npcEnemies, npcAllies);
				
				npcAllies.removeIf((c)->isCombatantDefeated(c));
				npcEnemies.removeIf((c)->isCombatantDefeated(c));
				
				// Figures out new moves for NPCs:
				enemy.selectMoves(npcEnemies, npcAllies);
				predictionContent.put(enemy, enemy.getMovesPredictionString(npcEnemies, npcAllies));
			}
		}
	}
	
	/**
	 * @return A list of this combatant's allies. <b>Does not include</b> the combatant themselves.
	 */
	public List<GameCharacter> getAllies(GameCharacter combatant) {
		List<GameCharacter> returnList = new ArrayList<>();
		
		if(combatant.isPlayer()) {
			returnList.addAll(allies);
			
		} else if(allies.contains(combatant)) {
			returnList.add(Main.game.getPlayer());
			returnList.addAll(allies);
			
		} else {
			returnList.addAll(enemies);
		}
		
		returnList.remove(combatant);
		
		return returnList;
	}

	public List<GameCharacter> getEnemies(GameCharacter combatant) {
		List<GameCharacter> returnList = new ArrayList<>();
		
		if(combatant.isPlayer()) {
			returnList.addAll(enemies);
			
		} else if(allies.contains(combatant)) {
			returnList.addAll(enemies);
			
		} else {
			returnList.add(Main.game.getPlayer());
			returnList.addAll(allies);
		}
		
		return returnList;
	}

	/**
	 * @param target The character whose party member will be returned.
	 * @return A random member of the target's party. WIll attempt to return a member that isn't the target, but if the target's party only contains them, will return the target. 
	 */
	public GameCharacter getRandomAlliedPartyMember(GameCharacter target) {
		List<GameCharacter> possibleTargets = new ArrayList<>();
		for(GameCharacter character : getAllies(target)) {
			possibleTargets.add(character);
		}
		if(possibleTargets.size() == 0) {
			return target;
		}
		return possibleTargets.get(Util.random.nextInt(possibleTargets.size()));
	}

	public int getTurn() {
		return turn;
	}

	public float getTotalDamageTaken(GameCharacter character) {
		totalDamageTaken.putIfAbsent(character, 0f);
		return totalDamageTaken.get(character);
	}

	public void setTotalDamageTaken(GameCharacter character, float damage) {
		totalDamageTaken.put(character, damage);
	}

	public void incrementTotalDamageTaken(GameCharacter character, float increment) {
		setTotalDamageTaken(character, getTotalDamageTaken(character) + increment);
	}

	public boolean isCharacterVictory(GameCharacter character) {
		if(getEnemies(character).contains(Main.game.getPlayer())) {
			return !playerVictory;
		}
		return playerVictory;
	}
	
	/**
	 * @return true if the last combat that took place resulted in the player's victory.
	 */
	public boolean isPlayerVictory() {
		return playerVictory;
	}

	public void setupManaBurnStackForOutOfCombat(GameCharacter character) {
		manaBurnStack = new HashMap<>();
		manaBurnStack.put(character, new Stack<>());
	}
	
	public Map<GameCharacter, Stack<Float>> getManaBurnStack() {
		return manaBurnStack;
	}

	/**
	 * Set target to null to remove preferred targeting behaviour.
	 */
	public void setPreferredTarget(GameCharacter character, GameCharacter target) {
		if(target==null) {
			preferredTargets.remove(character);
		} else {
			preferredTargets.put(character, target);
		}
	}
	
	/**
	 * Will typically be null, unless a target has been manually set. If the preferred target is defeated, this will return null.
	 */
	public GameCharacter getPreferredTarget(GameCharacter character) {
		if(!preferredTargets.containsKey(character) || isCombatantDefeated(preferredTargets.get(character))) {
			return null;
		}
		return preferredTargets.get(character);
	}
	
	public void addStatusEffectToApply(GameCharacter target, AbstractStatusEffect effect, int duration) {
		statusEffectsToApply.get(target).put(effect, duration);
//		statusEffectsToApply.get(target).putIfAbsent(effect, 0);
//		
//		statusEffectsToApply.get(target).put(effect, statusEffectsToApply.get(target).get(effect)+duration);
	}

	public Map<GameCharacter, Map<AbstractStatusEffect, Integer>> getStatusEffectsToApply() {
		return statusEffectsToApply;
	}

	public DialogueNode getPlayerPostVictoryDialogue() {
		return playerPostVictoryDialogue;
	}

	public void setPlayerPostVictoryDialogue(DialogueNode playerPostVictoryDialogue) {
		this.playerPostVictoryDialogue = playerPostVictoryDialogue;
	}

	public DialogueNode getPlayerPostDefeatDialogue() {
		return playerPostDefeatDialogue;
	}

	public void setPlayerPostDefeatDialogue(DialogueNode playerPostDefeatDialogue) {
		this.playerPostDefeatDialogue = playerPostDefeatDialogue;
	}

	public boolean isSubmitBlocked() {
		return submitBlocked;
	}
}
