package com.lilithsthrone.LolificationProject.Lexi.dialogue.npcDialogue.unique;

import com.lilithsthrone.LolificationProject.Lexi.Lexicon;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.LolificationProject.Lexi.character.npc.fields.Cecilia;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
// * @since 0.2.5
 * @version 0.1.0
 * @author Lexi
 */
public class CeciliaDialogue {
	
	public static final DialogueNode CECILIA_APPEARS = new DialogueNode("Grassland Wilderness", "", true) {
		
		@Override
		public String getAuthor() {
			return "Lexi";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_APPEARS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().hasFlag(Lexicon.ceciliaMet)) {
					return new Response("Wait", "Wait for Cecilias imminent arrival.", CECILIA_APPEARS_REPEAT_ENCOUNTER);
				} else {
					return new Response("Look around", "Take a look around.", CECILIA_LOOK);
				}
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Nope", "Quickly backtrack.") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_AVOID"));
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						Main.game.getNpc(Cecilia.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);

					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode CECILIA_LOOK = new DialogueNode("Grassland Wilderness", "", true, true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_LOOK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("A branch?", "Inspect the weird branch.", CECILIA_BRANCH) {
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode CECILIA_BRANCH = new DialogueNode("Grassland Wilderness", "", true, true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_BRANCH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Ambush??", "DODGE.", CECILIA_AMBUSH) {
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode CECILIA_AMBUSH = new DialogueNode("Grassland Wilderness", "", true, true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_AMBUSH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Get Up!", "Recover from the tackle!", CECILIA_RECOVER) {
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode CECILIA_RECOVER = new DialogueNode("Grassland Wilderness", "", true, true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_RECOVER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight",
						"You're not going to let this manticore knock you around!",
						Main.game.getNpc(Cecilia.class),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_COMBAT_PC_OPENING")),
								new Value<>(Main.game.getNpc(Cecilia.class), UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_COMBAT_CECILIA_OPENING")))) {
				};

			} else if (index == 2) {
				return new Response("Talk", "Decide to try talking to her. Maybe there was a misunderstanding?", CECILIA_AMBUSH_TALK);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode CECILIA_AMBUSH_TALK = new DialogueNode("Grassland Wilderness", "", true, true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_AMBUSH_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your way.", null) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(Lexicon.ceciliaDisabled, true);
					}
				};

			} else {
				return null;
			}
		}
	};


	public static final DialogueNode CECILIA_APPEARS_REPEAT_ENCOUNTER = new DialogueNode("Grassland Wilderness", "", true, true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_APPEARS_REPEAT_ENCOUNTER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight!",
						"Mock brawl against Cecilia!",
						Main.game.getNpc(Cecilia.class),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_COMBAT_PC_OPENING_REPEAT")),
								new Value<>(Main.game.getNpc(Cecilia.class), UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_COMBAT_CECILIA_OPENING_REPEAT")))) {
				};

			} else {
				return null;
			}
		}
	};













	
	public static final DialogueNode COMBAT_PLAYER_LOSS = new DialogueNode("Grassland Wilderness", "", true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_LOSS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getNpc(Cecilia.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
						Main.game.getDialogueFlags().setFlag(Lexicon.ceciliaMet, true);
						Main.game.getNpc(Cecilia.class).setPlayerKnowsName(true);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode COMBAT_PLAYER_LOSS_REPEAT = new DialogueNode("Grassland Wilderness", "", true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_LOSS_REPEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getNpc(Cecilia.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode COMBAT_PLAYER_WIN = new DialogueNode("Grassland Wilderness", "", true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_WIN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Explanation", "Demand an explanation from this strange manticore-girl.", COMBAT_PLAYER_WIN_EXPLANATION) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(Lexicon.ceciliaMet, true);
						Main.game.getNpc(Cecilia.class).setPlayerKnowsName(true);
					}
				};

			} else if(index == 2) {
				return new Response("Compensation", "Demand monetary compensation from this strange manticore-girl.", COMBAT_PLAYER_WIN_MONEY) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(Lexicon.ceciliaMet, true);
						Main.game.getNpc(Cecilia.class).setPlayerKnowsName(true);
					}
				};
			} else if (index == 3 ) {
				return new Response("'Compentsation'?",
						"Are you sure this is a good idea? The manticore-girl doesnt exactly look defeated.", COMBAT_PLAYER_WIN_SEX_WARNING) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(Lexicon.ceciliaMet, true);
						Main.game.getNpc(Cecilia.class).setPlayerKnowsName(true);
					}
				};
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode COMBAT_PLAYER_WIN_REPEAT = new DialogueNode("Grassland Wilderness", "", true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_WIN_REPEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Money", "See if Cecilia can spare some coin for the victor.", COMBAT_PLAYER_WIN_MONEY_REPEAT) {
				};
			} else if (index == 2 ) {
				if(Main.game.getDialogueFlags().hasFlag(Lexicon.ceciliaFucked)) {
					return new Response("Sex",
							"All that fighting has got you worked up.", COMBAT_PLAYER_WIN_SEX_CHOICE) {
					};
				} else {
					return new Response("Sex?",
							"Are you sure this is a good idea? The manticore-girl doesnt exactly look defeated.", COMBAT_PLAYER_WIN_SEX_WARNING) {
					};
				}

			} else if ((index == 3 ) && Main.game.getDialogueFlags().hasFlag(Lexicon.ceciliaFucked)) {
				return new Response("Explanation?",
						"Finally ask for an explanation why shes been picking fights with you.", COMBAT_PLAYER_WIN_EXPLANATION) {
				};
			} else
            return null;
        }
	};

	public static final DialogueNode COMBAT_PLAYER_WIN_SEX_WARNING = new DialogueNode("Grassland Wilderness", "", true, true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_WIN_SEX_WARNING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Yes",
						"Even better, you don't mind at all.",
						false, false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Cecilia.class), SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_EAGER;
								}
								return SexPace.DOM_NORMAL;
							}
						},
						null,
						null, AFTER_SEX, UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_WIN_AFTER_SEX")) {
				};

			} else if(index == 2) {
				return new ResponseSex("'No'",
						"You definitely certainly have a problem with it.",
						false, false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Cecilia.class), SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_RESISTING;
								}
								return SexPace.DOM_ROUGH;
							}
						},
						null,
						null, AFTER_SEX, UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_WIN_AFTER_SEX")) {
				};

			} else if(index == 3) {
				return new Response("Nevermind",
						"You've changed your mind.", COMBAT_PLAYER_WIN_SEX_REFUSE) {
				};
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode COMBAT_PLAYER_WIN_SEX_REFUSE = new DialogueNode("Grassland Wilderness", "", true, true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_WIN_SEX_REFUSE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getNpc(Cecilia.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AFTER_SEX = new DialogueNode("Grassland Wilderness", "", true, true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "AFTER_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(Lexicon.ceciliaFucked, true);
						Main.game.getNpc(Cecilia.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode COMBAT_PLAYER_WIN_MONEY = new DialogueNode("Grassland Wilderness", "", true, true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_WIN_MONEY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getNpc(Cecilia.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode COMBAT_PLAYER_WIN_EXPLANATION = new DialogueNode("Grassland Wilderness", "", true, true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_WIN_EXPLANATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getNpc(Cecilia.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode COMBAT_PLAYER_WIN_MONEY_REPEAT = new DialogueNode("Grassland Wilderness", "", true, true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_WIN_MONEY_REPEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Carry on your way...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getNpc(Cecilia.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode COMBAT_PLAYER_WIN_SEX_CHOICE = new DialogueNode("Grassland Wilderness", "", true, true) {

		@Override
		public String getAuthor() {
			return "Lexi";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_WIN_SEX_CHOICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Eager",
						"You're quite eager for it.",
						false, false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Cecilia.class), SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_EAGER;
								}
								return SexPace.DOM_NORMAL;
							}
						},
						null,
						null, AFTER_SEX, UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_WIN_AFTER_SEX_REPEAT")) {
				};

			} else if(index == 2) {
				return new ResponseSex("Not at all",
						"You definitely certainly don't want it.",
						false, false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Cecilia.class), SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_RESISTING;
								}
								return SexPace.DOM_ROUGH;
							}
						},
						null,
						null, AFTER_SEX, UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_WIN_AFTER_SEX_REPEAT")) {
				};

			} else if(index == 3) {
				return new ResponseSex("Gently",
						"Please be gentle Cecilia-senpai.",
						false, false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Cecilia.class), SexSlotLyingDown.LAP_PILLOW)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if (character.isPlayer()) {
									return SexPace.SUB_EAGER;
								}
								return SexPace.DOM_GENTLE;
							}
						},
						null,
						null, AFTER_SEX, UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "COMBAT_PLAYER_WIN_AFTER_SEX_GENTLE")) {
				};
			} else {
				return null;
			}
		}
	};
}
