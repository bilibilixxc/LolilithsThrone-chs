package com.lilithsthrone.game.dialogue.places;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;

/**
 * @since 0.1.0
 * @version 0.1.69
 * @author Innoxia
 */
public class JunglePlaces {

	public static final DialogueNode PATH = new DialogueNode("丛林小径", "", false) {
		/**
		 * 
		 */

		@Override
		public String getContent() {
			return "TODO 沿着狭窄的小道前行……";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode CLUB = new DialogueNode("夜店", "一处地点。", false) {
		/**
		 * 
		 */

		@Override
		public String getContent() {
			return "TODO";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode BROTHEL = new DialogueNode("妓院", "一处地点。", false) {
		/**
		 * 
		 */

		@Override
		public String getContent() {
			return "TODO";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode TENTACLE_QUEENS_LAIR = new DialogueNode("触手女王的巢穴", "一处地点。", false) {
		/**
		 * 
		 */

		@Override
		public String getContent() {
			return "TODO";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode DENSE_JUNGLE = new DialogueNode("密林", "一处地点。", false) {
		/**
		 * 
		 */

		@Override
		public String getContent() {
			return "TODO";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	// Entrances and exits:

	public static final DialogueNode JUNGLE_ENTRANCE = new DialogueNode("", "旅行到御城区。", false) {
		/**
		 * 
		 */

		@Override
		public String getContent() {
			return "路边的半块杂草丛生的标识告诉你这条路将通往御城区。";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("御城区", "旅行到御城区。(这个功能将在稍后添加！)", null);

			} else {
				return null;
			}
		}
	};
}
