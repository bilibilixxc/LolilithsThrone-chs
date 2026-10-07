package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.5
 * @version 0.3.4
 * @author Innoxia
 */
public class TunnelSlimeDialogue {
	
	private static NPC getSlime() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNode ATTACK = new DialogueNode("敌袭！", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_INTRO"));
			
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME"));
					if(getSlime().isAttractedTo(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_OFFER_SEX"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_NO_SEX"));
					}
					
				}
				// Slime transformation descriptions are appended in SubmissionAttacker getEncounterDialogue() method.
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					if (index == 1) {
						return new ResponseCombat("战斗",
								"你不希望让[npc.name]将其他人转化为史莱姆。给[npc.herHim]来点教训！", Main.game.getActiveNPC(),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), "[pc.speech(在我的眼皮底下，就别想躲在这隧道里了！)]你大喝一声，冲了上去！"),
										new Value<>(getSlime(), "[npc.speech(叛徒！你该遭报应了！)][npc.Name]高喊着，准备展开自卫。")));
						
					} else if (index == 2) {
						return new Response("离开", "继续你的旅途。", ATTACK) {
							@Override
							public void effects(){
								if(getSlime().isAttractedTo(Main.game.getPlayer())) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME_TURN_DOWN_SEX"));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME_LEAVE"));
								}
							}
							@Override
							public DialogueNode getNextDialogue(){
								return Main.game.getDefaultDialogue(false);
							}
						};
						
					} else if (index == 3) {
						if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
							return new Response("支配型性爱", "[npc.Name]并没有被你吸引！", null);
							
						} else if(getSlime().hasFetish(Fetish.FETISH_DOMINANT) && !getSlime().hasFetish(Fetish.FETISH_SUBMISSIVE)) {
							return new Response("支配型性爱", "[npc.Name]并不愿意让你做支配方。", null);
							
						} else {
							return new ResponseSex("支配型性爱", "同[npc.name]做爱，并占据支配地位。",
									null, null, null,
									null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(getSlime()),
									null,
									null), AFTER_SLIME_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_DOM"));
						}
						
					} else if (index == 4) {
						if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
							return new Response("服从型性爱", "[npc.Name]并没有被你吸引！", null);
							
						} else if(getSlime().hasFetish(Fetish.FETISH_SUBMISSIVE) && !getSlime().hasFetish(Fetish.FETISH_DOMINANT)) {
							return new Response("服从型性爱", "[npc.Name]并不愿意让你做服从方。", null);
							
						} else {
							return new ResponseSex("服从型性爱", "做服从的一方，跟[npc.name]做爱。",
									null, null, null,
									null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(getSlime()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null), AFTER_SLIME_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_SUB"));
						}
						
					} else {
						return null;
					}
					
				} else {
					if (index == 1) {
						return new ResponseCombat("战斗",
								"挺身而出，与[npc.name]决一胜负！", Main.game.getActiveNPC(),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), ""),
										new Value<>(getSlime(), "")));
						
					} else if (index == 2) {
						return new Response("献上财物", "[npc.Name]对你的钱一点也不感兴趣，[npc.she]只想把你转化成史莱姆！", null);
						
					} else if (index == 3) {
						return new Response("屈服", "让[npc.name]把你转化成史莱姆！", TRANSFORMED) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.TRANSFORMATION_GENERIC;
							}
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(getSlime().useItem(Main.game.getItemGen().generateItem("RACE_INGREDIENT_SLIME"),
										Main.game.getPlayer(), false, true));
								if(getSlime().isAttractedTo(Main.game.getPlayer())) {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMED_SLIME_OFFER_SEX"));
								} else {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMED_SLIME_NO_SEX"));
								}
							}
						};
						
					} else {
						return null;
					}
				}
				
			} else {
				if (index == 1) {
					return new ResponseCombat("战斗",
							"挺身而出，与[npc.name]决一胜负！", Main.game.getActiveNPC(),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), ""),
									new Value<>(getSlime(), "")));
				
				} else if (index == 2) {
					if(Main.game.getPlayer().getMoney()<Main.game.getDialogueFlags().getMuggerDemand2()) {
						return new Response("给钱("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand2(), "span")+")",
								"你没有足够的钱给[npc.name]。你只能选择战斗，或是向[npc.herHim]献上身体！", null);
					} else {
						return new Response("给钱("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand2(), "span")+")",
								"给了[npc.name]"+Util.intToString(Main.game.getDialogueFlags().getMuggerDemand2())+"火币，你最终得以脱身。", OFFER_MONEY) {
							@Override
							public void effects() {
								Main.game.getPlayer().incrementMoney(-250);
							}
						};
					}
					
				} else if (index == 3) {
					if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("献出身体", "向[npc.name]献出身体，以避免发生暴力对抗。",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getSlime()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null) {
									@Override
									public SexControl getSexControl(GameCharacter character) {
										if(character.isPlayer()) {
											return SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS;
										}
										return super.getSexControl(character);
									}
								},
								AFTER_SLIME_SEX_AS_SUB,
								UtilText.parseFromXMLFile("places/submission/tunnelSlime", "OFFER_BODY"));
					} else {
						return new Response("献出身体", "你可以看出来[npc.name]对和你做爱完全不感兴趣。你只能选择给[npc.herHim]一些财物，或是做好战斗的准备！", null);
					}
					
				} else {
					return null;
				}
				
			}
			
		}
	};
	
	public static final DialogueNode ATTACK_REPEAT = new DialogueNode("敌袭！", "", true) {
		
		@Override
		public String getContent() {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					UtilText.nodeContentSB.setLength(0);
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_REPEAT_TRANSFORMER_PLAYER_SLIME"));
					if(getSlime().isAttractedTo(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_OFFER_SEX"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_NO_SEX"));
					}
					return UtilText.nodeContentSB.toString();
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_REPEAT_TRANSFORMER");
				}
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_REPEAT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ATTACK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ATTACK_PREGNANCY_REVEAL = new DialogueNode("敌袭！", "", true) {
		
		@Override
		public String getContent() {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					UtilText.nodeContentSB.setLength(0);
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_PREGNANCY_REVEAL_TRANSFORMER_PLAYER_SLIME"));
					if(getSlime().isAttractedTo(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_OFFER_SEX"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_NO_SEX"));
					}
					return UtilText.nodeContentSB.toString();
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_PREGNANCY_REVEAL_TRANSFORMER");
				}
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_PREGNANCY_REVEAL");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ATTACK.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode AFTER_SLIME_SEX_AS_DOM = new DialogueNode("返回", "", true) {
		
		@Override
		public String getDescription(){
			return "你已经爽过了，离开这里让[npc.name]恢复一下吧。";
		}

		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getSlime()) >= getSlime().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_DOM");
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_DOM_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", AFTER_SLIME_SEX_AS_DOM){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SLIME_SEX_AS_SUB = new DialogueNode("被使用", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return "[npc.Name]已经跟你爽过了。";
		}

		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getSlime()) >= getSlime().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_SUB");
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_SUB_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", AFTER_SLIME_SEX_AS_SUB){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode TRANSFORMED = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "继续你的旅途。", TRANSFORMED) {
					@Override
					public void effects(){
						if(getSlime().isAttractedTo(Main.game.getPlayer())) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME_TURN_DOWN_SEX"));
						}
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else if (index == 2) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
					return new Response("支配型性爱", "[npc.Name]并没有被你吸引！", null);
					
				} else if(getSlime().hasFetish(Fetish.FETISH_DOMINANT) && !getSlime().hasFetish(Fetish.FETISH_SUBMISSIVE)) {
					return new Response("支配型性爱", "[npc.Name]并不愿意让你做支配方。", null);
					
				} else {
					return new ResponseSex("支配型性爱", "同[npc.name]做爱，并占据支配地位。",
							null, null, null,
							null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
							null,
							null), AFTER_SLIME_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_DOM"));
				}
				
			} else if (index == 3) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
					return new Response("服从型性爱", "[npc.Name]并没有被你吸引！", null);
					
				} else if(getSlime().hasFetish(Fetish.FETISH_SUBMISSIVE) && !getSlime().hasFetish(Fetish.FETISH_DOMINANT)) {
					return new Response("服从型性爱", "[npc.Name]并不愿意让你做服从方。", null);
					
				} else {
					return new ResponseSex("服从型性爱", "做服从的一方，跟[npc.name]做爱。",
							null, null, null,
							null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(getSlime()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SLIME_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_SUB"));
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode RESIST_TRANSFORMED = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "RESIST_TRANSFORMED"));
			
			if(getSlime().isAttractedTo(Main.game.getPlayer()) && getSlime().isWillingToRape() && Main.game.isNonConEnabled()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "RESIST_TRANSFORMED_RAPE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "RESIST_TRANSFORMED_NO_RAPE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape() && Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new ResponseSex("做爱",
							"[npc.Name]强行压住了你……",
							false, false,
							new SMLyingDown(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
							null,
							null, AFTER_SLIME_SEX_AS_SUB, "<p>"
								+ "你躺了下来，向[npc.name]表示屈服，面对着[npc.she]用舌头探进你的喉咙，你也并不反抗。"
								+ "过了一会儿，[npc.she]就坐了起来，在你的[pc.lips]留下了一大摊粘液。"
								+ "[npc.speech(真是个婊子！明白你是什么地位了吗！给我乖乖地享受吧……)]"
							+ "</p>");
					
				} else if (index == 2) {
					return new ResponseSex("做爱(渴求)",
							"[npc.Name]强行压住了你……",
							false, false,
							new SMLyingDown(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							null, AFTER_SLIME_SEX_AS_SUB, "<p>"
								+ "You eagerly surrender yourself to [npc.name], raising your head to help [npc.herHim] tongue-fuck your throat."
								+ " After a moment, [npc.she] sits back up, leaving a wet pool of slime all over your [pc.lips]."
								+ " [npc.speech(Good bitch! You know your place already! Now be a good [pc.girl] and try to enjoy this...)]"
							+ "</p>");
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("抵抗做爱",
							"[npc.Name]强行压住了你……",
							false, false,
							new SMLyingDown(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							null, AFTER_SLIME_SEX_AS_SUB, "<p>"
								+ "You try to turn your face away, but [npc.name] simply reaches up to grip both sides of your head, holding you still as [npc.she] tongue-fucks your throat."
								+ " After a moment, [npc.she] sits back up, leaving a wet pool of slime all over your [pc.lips]."
								+ " [npc.speech(Stupid bitch! You can resist as much as you want! I'm not going to stop until I'm satisfied!)]"
							+ "</p>");
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("继续", "继续你的旅程。", AFTER_COMBAT_PLAYER_DEFEAT){
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	

	public static final DialogueNode OFFER_MONEY = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "OFFER_MONEY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", OFFER_MONEY){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	// Standard combat:
	

	public static final DialogueNode AFTER_COMBAT_PLAYER_VICTORY = new DialogueNode("胜利", "", true) {

		@Override
		public String getDescription() {
			return "你打败了[npc.name]！";
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_ENEMY_WANTS_SEX");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			boolean noSex = getSlime().isPostCombatNoSex();
			boolean wantsSex = getSlime().isPostCombatWantsSex();
			boolean rapePlay = getSlime().isPostCombatRapePlay();
		
			if (index == 1) {
				return new Response("继续",
						"继续前行……"
							+ (getSlime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)
									?UtilText.parse(getSlime(), "<br/>[style.italicsBad([npc.Name]将会从游戏中永久移除。)]")
									:""),
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						if(getSlime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						return super.getHighlightColour();
					}
					@Override
					public void effects() {
						if(getSlime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getSlime());
						}
					}
				};
				
			} else if (index == 2) {
				if(noSex) {
					return new Response("做爱", "[npc.Name]不想和你做爱！", null);
					
				} else if(wantsSex) {
					return new ResponseSex(rapePlay?"强奸play":"做爱",
							"反正这<i>正是</i>[npc.she]想要的！",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
							null,
							null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", rapePlay?"AFTER_COMBAT_VICTORY_RAPE":"AFTER_COMBAT_VICTORY_SEX", getSlime()));
					
				} else {
					return new ResponseSex(
							"强奸[npc.herHim]",
							"[npc.she]竟敢对你发起攻击，就该受罚……",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
							null,
							null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_RAPE", getSlime()));
				}
				
			} else if (index == 3) {
				if(noSex) {
					return new Response("做爱(温柔)", "[npc.Name]不想和你做爱！", null);
					
				} else if(wantsSex){
					return new ResponseSex(rapePlay?"强奸play (温柔)":"做爱(温柔)",
							"反正这<i>正是</i>[npc.she]想要的！",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", rapePlay?"AFTER_COMBAT_VICTORY_RAPE_GENTLE":"AFTER_COMBAT_VICTORY_SEX_GENTLE", getSlime()));
					
				} else {
					return new ResponseSex("强奸[npc.herHim](温柔)", "[npc.She]竟敢对你发起攻击，就该受罚……",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getSlime()));
				}
				
			} else if (index == 4) {
				if(noSex) {
					return new Response("做爱(粗暴)", "[npc.Name]对你不感兴趣！", null);
					
				} else if(wantsSex){
					return new ResponseSex(rapePlay?"强奸play (粗暴)":"做爱(粗暴)",
							"反正这<i>正是</i>[npc.she]想要的！",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", rapePlay?"AFTER_COMBAT_VICTORY_RAPE_ROUGH":"AFTER_COMBAT_VICTORY_SEX_ROUGH", getSlime()));
					
				} else {
					return new ResponseSex("强奸[npc.herHim](粗暴)", "[npc.She]竟敢对你发起攻击，就该受罚……",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getSlime()));
				}
				
			} else if (index == 5) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer()) || getSlime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response("屈服",
							"你无法对[npc.name]表示屈服，因为[npc.sheHasFull]没兴趣跟你做爱！",
							null);
				} else {
					return new ResponseSex("顺从",
							"你不太确定现在该做什么……也许最好让[npc.name]来决定接下来做什么？",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getSlime()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null),
							AFTER_SLIME_SEX_AS_SUB,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getSlime()));
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("物品栏", "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具伸出手……"){
					@Override
					public void effects() {
						Main.mainController.openInventory(getSlime(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			}
			//TODO
//			else if (index == 7) {
//				if(slime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
//					return new Response("Talk", "After betraying [npc.namePos] trust, [npc.she] will never want to talk to you again.", null);
//					
//				} else {
//					return new Response("Talk", "Talk to [npc.name] and ask [npc.herHim] why [npc.she] attacked you.", AFTER_COMBAT_VICTORY_TALK){
//						@Override
//						public void effects() {
//							slime().setPlayerKnowsName(true);
//							Main.game.getTextEndStringBuilder().append(slime().setAffection(Main.game.getPlayer(), 10));
//						}
//					};
//				}
//				
//			} 
			else if (index == 8 && getSlime().isAbleToSelfTransform()) {
				return new Response("转化[npc.herHim]",
						"仔细观察[npc.name]会将自己转化成什么样……",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getSlime());
					}
				};
				
			} else if (index == 10 && !getSlime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"移除角色",
						UtilText.parse(getSlime(), "赶[npc.name]走。"
								+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]"),
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_BANISH_NPC", getSlime()));
						Main.game.banishNPC(getSlime());
					}
				};
				
			} else {
				return null;
			}
			
		}
	};

	public static final DialogueNode AFTER_COMBAT_PLAYER_DEFEAT = new DialogueNode("落败", "", true) {
		
		@Override
		public String getDescription() {
			return "你被[npc.name]打败了！";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT"));
			
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_TRANSFORMER"));
				
			} else {
				if(getSlime().isAttractedTo(Main.game.getPlayer()) && getSlime().isWillingToRape() && Main.game.isNonConEnabled()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_RAPE"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_NO_RAPE"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("抵抗");
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("抵抗",
								"由于你<b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b>的性癖，你乐于被转化，所以无法抵抗！",
								null);
					} else {
						return new Response("抵抗", "把[npc.namePos]手上那瓶奇怪的液体打落。", RESIST_TRANSFORMED);
					}
					
				} else if (index == 2) {
					return new Response("屈服", "让[npc.name]把你转化成史莱姆！", TRANSFORMED) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.TRANSFORMATION_GENERIC;
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getSlime().useItem(Main.game.getItemGen().generateItem("RACE_INGREDIENT_SLIME"),
									Main.game.getPlayer(), false, true));
						}
					};
					
				} else {
					return null;
				}
				
			} else if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape() && Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new ResponseSex("做爱",
							"[npc.Name]强行压住了你……",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getActiveNPC()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SLIME_SEX_AS_SUB, "<p>"
								+ "你躺了下来，向[npc.name]表示屈服，面对着[npc.she]用舌头探进你的喉咙，你也并不反抗。"
								+ "过了一会儿，[npc.she]就坐了起来，在你的[pc.lips]留下了一大摊粘液。"
								+ "[npc.speech(真是个婊子！明白你是什么地位了吗！给我乖乖地享受吧……)]"
							+ "</p>");
					
				} else if (index == 2) {
					return new ResponseSex("做爱(渴求)",
							"[npc.Name]强行压住了你……",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getActiveNPC()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER), AFTER_SLIME_SEX_AS_SUB, "<p>"
								+ "你急切地向[npc.name]表示了屈服，甚至仰起头来，让[npc.herHim]用舌头更顺利的探进你的喉咙。"
								+ "过了一会儿，[npc.she]就坐了起来，在你的[pc.lips]留下了一大摊粘液。"
								+ "[npc.speech(真是个婊子！明白你是什么地位了吗！给我乖乖地享受吧……)]"
							+ "</p>");
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("抵抗做爱",
							"[npc.Name]强行压住了你……",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getActiveNPC()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING), AFTER_SLIME_SEX_AS_SUB, "<p>"
								+ "你想要把头撇开，但[npc.name]却直接紧紧钳住了你的脑袋，将舌头探进了你的喉咙。"
								+ "过了一会儿，[npc.she]就坐了起来，在你的[pc.lips]留下了一大摊粘液。"
								+ "[npc.speech(你这婊子蠢得可以！随你怎么反抗！我不满足是不会停的！)]"
							+ "</p>");
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("继续", "继续你的旅程。", AFTER_COMBAT_PLAYER_DEFEAT){
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("返回", "", true) {
		
		@Override
		public String getDescription(){
			return "你已经爽过了，离开这里让[npc.name]恢复一下吧。";
		}

		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getSlime()) >= getSlime().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_DOM");
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_DOM_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", AFTER_SEX_VICTORY){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("物品栏", "现在你可以随意拿取[npc.namePos]衣服和道具，谁也拦不了你……"){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"移除角色",
						UtilText.parse(getSlime(), "赶[npc.name]走。"
								+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]"),
						AFTER_COMBAT_PLAYER_VICTORY){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
