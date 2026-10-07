package com.lilithsthrone.game.combat;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.3.4.5
 * @version 0.3.4.5
 * @author Innoxia
 */
public enum CombatBehaviour {
	
	BALANCED("均衡",
			"让[npc.name]使用感觉最合适的战斗动作。",
			"[npc.NameIsFull]正在使用感觉最合适的战斗动作。"),
	
	ATTACK("攻击",
			"让[npc.name]更加好斗，倾向于使用主手武器和攻击型法术，而非防御能力。",
			"只要有可能，[npc.name]便会选择输出，倾向于使用伤害攻击而非防御能力。"),
	
	DEFEND("防守",
			"让[npc.name]更注重防守，倾向于使用能够避免伤害的能力。",
			"只要有可能，[npc.name]便会选择防守，倾向于使用各种能力避免来袭的伤害。"),
	
	SEDUCE("诱惑",
			"让[npc.name]开始诱惑敌人，而非使用伤害法术或武器攻击。",
			"只要有可能，[npc.name]便会专注于诱惑敌人，而不是使用攻击或防御能力。"),
	
	SPELLS("法术",
			"让[npc.name]专注于随其心意使用各类法术。",
			"只要有可能，[npc.name]便会专注于随其心意地释放法术。"),
	
	SUPPORT("支援",
			"让[npc.name]专注于使用能够辅助盟友的法术或能力。",
			"只要有可能，[npc.name]便会使用能够协助盟友的法术或能力。");

	String name;
	String orderDescription;
	String description;
	
	private CombatBehaviour(String name, String orderDescription, String description) {
		this.name = name;
		this.orderDescription = orderDescription;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getOrderDescription(GameCharacter character) {
		return UtilText.parse(character, orderDescription);
	}

	public String getDescription(GameCharacter character) {
		return UtilText.parse(character, description);
	}

}
