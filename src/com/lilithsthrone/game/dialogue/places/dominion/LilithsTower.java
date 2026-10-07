package com.lilithsthrone.game.dialogue.places.dominion;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class LilithsTower {
	
	public static final DialogueNode OUTSIDE = new DialogueNode("莉莉丝之塔", "", false) {
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilithsTower/exterior", "OUTSIDE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("靠近", "靠近拱门，看看能否进入塔内。", LILITHS_DISTRICT_APPROACH);
			}
			return null;
		}
	};
	
	public static final DialogueNode LILITHS_DISTRICT_APPROACH = new DialogueNode("莉莉丝之塔", "", false, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilithsTower/exterior", "LILITHS_DISTRICT_APPROACH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
}
