package com.lilithsthrone.game.dialogue.encounters;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.1
 * @version 0.3.7.3
 * @author Innoxia
 */
public class SubmissionEncounterDialogue {

	public static final DialogueNode FIND_ITEM = new DialogueNode("垃圾堆", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "FIND_ITEM")
					+ "<p style='text-align:center;'>"
						+ "<b>"
						+ AbstractEncounter.getRandomItem().getDisplayName(true)
						+ "</b>"
					+ "</p>";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("拿走", "把" + AbstractEncounter.getRandomItem().getName() + "加入你的物品栏。", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem((AbstractItem) AbstractEncounter.getRandomItem(), true, true));
					}
				};
				
			} else if (index == 2) {
				return new Response("离开", "把" + AbstractEncounter.getRandomItem().getName() + "放在地上。", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};
}
