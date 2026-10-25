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
	
	public static final DialogueNode CECILIA_APPEARS = new DialogueNode("野草旷野", "", true) {
		
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
					return new Response("等待", "等待塞西莉亚的来临。", CECILIA_APPEARS_REPEAT_ENCOUNTER);
				} else {
					return new Response("环顾四周", "环顾一下周围。", CECILIA_LOOK);
				}
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("算了吧", "迅速原路返回。") {
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

	public static final DialogueNode CECILIA_LOOK = new DialogueNode("野草旷野", "", true, true) {

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
				return new Response("一根树枝？", "检查那根奇怪的树枝。", CECILIA_BRANCH) {
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode CECILIA_BRANCH = new DialogueNode("野草旷野", "", true, true) {

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
				return new Response("伏击？？", "躲开！", CECILIA_AMBUSH) {
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode CECILIA_AMBUSH = new DialogueNode("野草旷野", "", true, true) {

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
				return new Response("站起来！", "从扑击中恢复过来！", CECILIA_RECOVER) {
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode CECILIA_RECOVER = new DialogueNode("野草旷野", "", true, true) {

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
				return new ResponseCombat("战斗",
						"你可不会让这只蝎狮把你打倒！",
						Main.game.getNpc(Cecilia.class),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_COMBAT_PC_OPENING")),
								new Value<>(Main.game.getNpc(Cecilia.class), UtilText.parseFromXMLFile("Lexi/characters/fields/cecilia", "CECILIA_COMBAT_CECILIA_OPENING")))) {
				};

			} else if (index == 2) {
				return new Response("搭话", "试着和她谈谈，也许这只是一场误会？", CECILIA_AMBUSH_TALK);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode CECILIA_AMBUSH_TALK = new DialogueNode("野草旷野", "", true, true) {

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
				return new Response("继续", "继续上路。", null) {
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


	public static final DialogueNode CECILIA_APPEARS_REPEAT_ENCOUNTER = new DialogueNode("野草旷野", "", true, true) {

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
				return new ResponseCombat("战斗！",
						"与塞西莉亚来一场切磋扭打！",
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













	
	public static final DialogueNode COMBAT_PLAYER_LOSS = new DialogueNode("野草旷野", "", true) {

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
				return new Response("继续", "继续上路。", null){
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

	public static final DialogueNode COMBAT_PLAYER_LOSS_REPEAT = new DialogueNode("野草旷野", "", true) {

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
				return new Response("继续", "继续上路。", null){
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
	
	public static final DialogueNode COMBAT_PLAYER_WIN = new DialogueNode("野草旷野", "", true) {

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
				return new Response("解释", "向这个奇怪的蝎狮女孩索要解释。", COMBAT_PLAYER_WIN_EXPLANATION) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(Lexicon.ceciliaMet, true);
						Main.game.getNpc(Cecilia.class).setPlayerKnowsName(true);
					}
				};

			} else if(index == 2) {
				return new Response("补偿", "向这个奇怪的蝎狮女孩索要金钱补偿。", COMBAT_PLAYER_WIN_MONEY) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(Lexicon.ceciliaMet, true);
						Main.game.getNpc(Cecilia.class).setPlayerKnowsName(true);
					}
				};
			} else if (index == 3 ) {
				return new Response("'补偿'？",
						"你确定这是个好主意吗？这只蝎狮女孩看起来并没有完全被打败。", COMBAT_PLAYER_WIN_SEX_WARNING) {
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

	public static final DialogueNode COMBAT_PLAYER_WIN_REPEAT = new DialogueNode("野草旷野", "", true) {

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
				return new Response("金钱", "看看塞西莉亚能否为胜利者拿出一些金币。", COMBAT_PLAYER_WIN_MONEY_REPEAT) {
				};
			} else if (index == 2 ) {
				if(Main.game.getDialogueFlags().hasFlag(Lexicon.ceciliaFucked)) {
					return new Response("做爱",
						"刚才的打斗让你兴奋起来了。", COMBAT_PLAYER_WIN_SEX_CHOICE) {
					};
				} else {
					return new Response("做爱？",
							"你确定这是个好主意吗？这只蝎狮女孩看起来并没有完全被打败。", COMBAT_PLAYER_WIN_SEX_WARNING) {
					};
				}

			} else if ((index == 3 ) && Main.game.getDialogueFlags().hasFlag(Lexicon.ceciliaFucked)) {
				return new Response("解释？",
						"终于可以问问她为什么老是找你打架了。", COMBAT_PLAYER_WIN_EXPLANATION) {
				};
			} else
            return null;
        }
	};

	public static final DialogueNode COMBAT_PLAYER_WIN_SEX_WARNING = new DialogueNode("野草旷野", "", true, true) {

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
				return new ResponseSex("愿意",
						"再好不过了，你完全不介意。",
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
				return new ResponseSex("'不行'",
						"你绝对、肯定觉得这有问题。",
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
				return new Response("算了",
						"你改变主意了。", COMBAT_PLAYER_WIN_SEX_REFUSE) {
				};
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode COMBAT_PLAYER_WIN_SEX_REFUSE = new DialogueNode("野草旷野", "", true, true) {

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
				return new Response("继续", "继续上路。", null){
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

	public static final DialogueNode AFTER_SEX = new DialogueNode("野草旷野", "", true, true) {

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
				return new Response("继续", "继续上路。", null){
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

	public static final DialogueNode COMBAT_PLAYER_WIN_MONEY = new DialogueNode("野草旷野", "", true, true) {

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
				return new Response("继续", "继续上路。", null){
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

	public static final DialogueNode COMBAT_PLAYER_WIN_EXPLANATION = new DialogueNode("野草旷野", "", true, true) {

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
				return new Response("继续", "继续上路。", null){
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

	public static final DialogueNode COMBAT_PLAYER_WIN_MONEY_REPEAT = new DialogueNode("野草旷野", "", true, true) {

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
				return new Response("继续", "继续上路。", null){
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

	public static final DialogueNode COMBAT_PLAYER_WIN_SEX_CHOICE = new DialogueNode("野草旷野", "", true, true) {

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
				return new ResponseSex("渴望",
						"你相当渴望这件事。",
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
				return new ResponseSex("完全不",
						"你绝对、肯定不想要这件事。",
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
				return new ResponseSex("温柔点",
						"塞西莉亚前辈，请温柔一点。",
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