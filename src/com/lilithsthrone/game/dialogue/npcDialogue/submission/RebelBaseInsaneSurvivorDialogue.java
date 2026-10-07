package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.21
 * @version 0.3.21
 * @author DSG
 */
public class RebelBaseInsaneSurvivorDialogue {
	
	private static NPC getAttacker() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNode INSANE_SURVIVOR_ATTACK = new DialogueNode("活着的幽灵", "角落里的奇怪身影。", true) {
		@Override
		public String getAuthor() {
				return "DSG";
		}
		@Override
		public int getSecondsPassed() {
				return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "ATTACK", getAttacker());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗", "[npc.She]似乎笃定要跟你战斗了。搞定[npc.herHim]。",
						getAttacker(), 
						Util.newHashMapOfValues(
						new Value<>(getAttacker(), UtilText.parse(getAttacker(), "[npc.speech(我他妈杀了你，你个恶魔渣滓！)]"))));
			}
			else if (index == 2) {
				return new Response("对话", "你感觉[npc.she]已经失去理智，但不妨一试。", INSANE_SURVIVOR_TALK_ATTEMPT);
			}
			else if (index == 3 && Main.game.getPlayer().getMoney() <= 0) {
				return new Response("献上钱财", "你的口袋空空如也，根本无从给出。", null);
			}
			else if (index == 3) {
				return new Response("献上钱财", "虽然[npc.she]不是为了钱而来，但试一下也不会更糟……对吗？", INSANE_SURVIVOR_BRIBE_ATTEMPT);
			}
			else if (index == 4) {
				return new Response("投降", "[npc.Her]一身的服装虽然已经破破烂烂，看上去确实不是一般货色。你可能赢不了。", INSANE_SURVIVOR_SURRENDER_ATTEMPT);
			}
			else {
				return null;
			}
		}	 
	};
	
	public static final DialogueNode INSANE_SURVIVOR_TALK_ATTEMPT = new DialogueNode("交谈企图", "", true) {
		@Override
		public String getAuthor() {
				return "DSG";
		}
		@Override
		public int getSecondsPassed() {
				return 10;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "TALK", getAttacker());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗", "根本没法跟[npc.She]讲理。准备防卫！",
						getAttacker(), 
						Util.newHashMapOfValues(
						new Value<>(getAttacker(), UtilText.parse(getAttacker(), "[npc.speech(我他妈杀了你，你个恶魔渣滓！)]"))));
			}
			return null;
		}
	};
	
	public static final DialogueNode INSANE_SURVIVOR_BRIBE_ATTEMPT = new DialogueNode("行贿企图", "", true) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "BRIBE", getAttacker());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗", "[npc.She]根本没法收买。准备防卫！",
						getAttacker(), 
						Util.newHashMapOfValues(
						new Value<>(getAttacker(), UtilText.parse(getAttacker(), "[npc.speech(我他妈杀了你，你个恶魔渣滓！)]"))));
			}
			return null;
		}
	};
	
	public static final DialogueNode INSANE_SURVIVOR_SURRENDER_ATTEMPT = new DialogueNode("已投降！", "懂得什么时候退缩。", true) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "SURRENDER", getAttacker());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("自我防卫", "[npc.She]觉得你不是真心投降，你别无选择。",
						getAttacker(), 
						Util.newHashMapOfValues(
						new Value<>(getAttacker(), UtilText.parse(getAttacker(), "[npc.speech(我他妈杀了你，你个恶魔渣滓！)]"))));
			}
			return null;
		}
	};
	
	public static final DialogueNode INSANE_SURVIVOR_VICTORY = new DialogueNode("胜利！", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "VICTORY", getAttacker()));
			Main.game.banishNPC(getAttacker());
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.rebelBaseInsaneSurvivorEncountered);
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "已经没有其他人的踪迹。或许该继续你的旅程了……", Main.game.getDefaultDialogue(false));
			}
			return null;
		}	   
	};
	
	public static final DialogueNode INSANE_SURVIVOR_DEFEATED = new DialogueNode("落败！", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "DEFEATED", getAttacker()));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-5000));
			Main.game.banishNPC(getAttacker());
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.rebelBaseInsaneSurvivorEncountered);
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "已经没有其他人的踪迹。或许该继续你的旅程了……", Main.game.getDefaultDialogue(false));
			}
			return null;
		}  
	};
	
}
