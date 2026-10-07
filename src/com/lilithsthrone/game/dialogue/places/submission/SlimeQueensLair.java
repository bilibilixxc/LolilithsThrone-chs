package com.lilithsthrone.game.dialogue.places.submission;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardFire;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardIce;
import com.lilithsthrone.game.character.npc.submission.SlimeQueen;
import com.lilithsthrone.game.character.npc.submission.SlimeRoyalGuard;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMBath;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.2
 * @author Innoxia
 */
public class SlimeQueensLair {
	
	private static void resetTower() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsDefeated, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeated, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeatReacted, false);
	}
	
	public static final DialogueNode ENTRANCE = new DialogueNode("门厅", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ENTRANCE"));
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsIntroduced)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[style.italicsBad(你意识到，如果在这里战斗的话就没有撤退可言了！)]"
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "推开塔的前门，走到外边。", BatCaverns.SLIME_LAKE_ISLAND){
					@Override
					public void effects() {
						resetTower();
						Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "LEAVE_TOWER"));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode GUARD_POST = new DialogueNode("岗哨", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsDefeated)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsBluffed);
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_AREA"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_DEFEATED"));
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_PACIFIED"));
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsIntroduced)) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsBluffed)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_REPEAT_BLUFFED"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_REPEAT"));
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsBluffed)) {
				if(index==1) {
					return new Response("交谈", UtilText.parse(Main.game.getNpc(SlimeGuardFire.class), Main.game.getNpc(SlimeGuardIce.class), "询问[npc1.name]和[npc2.name]是如何守护史莱姆女王的。"), GUARD_POST_TALK);
						
				} else if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE) && index==2){
					return new ResponseCombat("战斗",
							"直接攻击[slimeFire.name]和[slimeIce.name]！",
							Main.game.getNpc(SlimeGuardFire.class),
							Util.newArrayListOfValues(Main.game.getNpc(SlimeGuardFire.class), Main.game.getNpc(SlimeGuardIce.class)),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(我真不敢相信你竟会被我的故事给骗了！)]你在大笑两声之后摆出了战斗的架势！[pc.speech(我会给你们上上一课，那就是不要轻易相信别人！)]"),
									new Value<>(Main.game.getNpc(SlimeGuardFire.class), "[slimeFire.speech(姐姐与我会和你好好玩玩的！)][slimeFire.name]在冷笑着的同时握紧了手上的武器。"),
									new Value<>(Main.game.getNpc(SlimeGuardIce.class), "[slimeIce.speech(你会为欺骗我们而后悔的！)][slimeIce.name]边喊边走到兄弟身边，准备战斗。")));
					
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)?index==2:index==3) {
					return new ResponseSex("并排后入",
							"让[slimeFire.name]和[slimeIce.name]并排四肢着地趴在你面前，然后开始后入他们。",
							null, null, null, null, null, null,
							true, false,
							new SMAllFours(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(SlimeGuardIce.class), SexSlotAllFours.ALL_FOURS),
											new Value<>(Main.game.getNpc(SlimeGuardFire.class), SexSlotAllFours.ALL_FOURS_TWO))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
							},
							null,
							null, AFTER_SLIME_GUARD_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SIDE_BY_SIDE"));
				
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)?index==3:index==4) {
					return new ResponseSex("开始接受“串肉串”",
							"让[slimeFire.name]和[slimeIce.name]“串肉串”",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
							true, true,
							new SMAllFours(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(SlimeGuardIce.class), SexSlotAllFours.IN_FRONT),
											new Value<>(Main.game.getNpc(SlimeGuardFire.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
							},
							null,
							null, AFTER_SLIME_GUARD_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SPITROASTED"));
				} else {
					return null;
				}
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsDefeated)) {
				if(index==1) {
					return new ResponseSex("并排后入",
							"让[slimeFire.name]和[slimeIce.name]并排四肢着地趴在你面前，然后开始后入他们。",
							null, null, null, null, null, null,
							true, false,
							new SMAllFours(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(SlimeGuardIce.class), SexSlotAllFours.ALL_FOURS),
											new Value<>(Main.game.getNpc(SlimeGuardFire.class), SexSlotAllFours.ALL_FOURS_TWO))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
							},
							null,
							null, AFTER_SLIME_GUARD_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SIDE_BY_SIDE"));
				
				} else if(index==2) {
					return new ResponseSex("开始接受“串肉串”",
							"让[slimeFire.name]和[slimeIce.name]“串肉串”",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
							true, true,
							new SMAllFours(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(SlimeGuardIce.class), SexSlotAllFours.IN_FRONT),
											new Value<>(Main.game.getNpc(SlimeGuardFire.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
							},
							null,
							null, AFTER_SLIME_GUARD_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SPITROASTED"));
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new ResponseCombat("战斗",
							"奋力冲过这些史莱姆！",
							Main.game.getNpc(SlimeGuardFire.class),
							Util.newArrayListOfValues(Main.game.getNpc(SlimeGuardFire.class), Main.game.getNpc(SlimeGuardIce.class)),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(如果你想打架的，那就来吧！)]你在大喊鼓足气势的同时做好了战斗的准备。"),
									new Value<>(Main.game.getNpc(SlimeGuardFire.class), "[slimeFire.speech(姐姐与我会和你好好玩玩的！)][slimeFire.name]冷笑着的同时攥紧手上的烈焰附魔剑。"),
									new Value<>(Main.game.getNpc(SlimeGuardIce.class), "[slimeIce.speech(你会后悔的！)][slimeIce.name]边喊边走到兄弟身边，准备战斗。"))){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsIntroduced, true);
						}
					};
					
				} else if(index==2) {
					if(Main.game.getNpc(SlimeGuardFire.class).getFoughtPlayerCount()>0) {
						return new Response("史莱姆哄骗", "现在才想办法唬住这两人也太晚了，毕竟你们已经交过手了……", null);
						
					} else if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
						return new Response("史莱姆哄骗", "假扮史莱姆女王的新臣民之一。", GUARD_POST_SLIME_BLUFF) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsBluffed, true);
							}
						};
						
					} else {
						return new Response("史莱姆哄骗", "这样做的前提是你必须是个史莱姆！", null);
					}
					
				} else if(index==3) {
					if(Main.game.getNpc(SlimeGuardFire.class).getFoughtPlayerCount()>0) {
						return new Response("假装是新来的仆人", "现在才想办法唬住这两人也太晚了，毕竟你们已经交过手了……", null);
						
					} else if(Main.game.getPlayer().getHistory()==Occupation.BUTLER) {
						return new Response("假装是新来的仆人", "利用你在管家工作中获得的知识来唬住这些家伙。", GUARD_POST_BUTLER_BLUFF) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsBluffed, true);
							}
						};
						
					} else if(Main.game.getPlayer().getHistory()==Occupation.MAID) {
						return new Response("假装是新来的女仆", "利用你在女仆工作中获得的经验来唬住这些家伙。", GUARD_POST_MAID_BLUFF) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsBluffed, true);
							}
						};
						
					} else {
						return new Response("假装是新来的仆人", "你没有掌握相关的经验！(需要“管家”或者“女仆”经验。)", null);
					}
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode GUARD_POST_SLIME_BLUFF = new DialogueNode("岗哨", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SLIME_BLUFF")
					+UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BLUFF_SUCCESS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode GUARD_POST_BUTLER_BLUFF = new DialogueNode("岗哨", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_BUTLER_BLUFF")
					+UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BLUFF_SUCCESS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode GUARD_POST_MAID_BLUFF = new DialogueNode("岗哨", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_MAID_BLUFF")
					+UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BLUFF_SUCCESS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode GUARD_POST_TALK = new DialogueNode("岗哨", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SLIME_GUARD_SEX_AS_DOM = new DialogueNode("结束", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_GUARD_SEX_AS_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SLIME_GUARD_SEX_AS_SUB = new DialogueNode("结束", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_GUARD_SEX_AS_SUB");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLIME_GUARDS_COMBAT_PLAYER_VICTORY = new DialogueNode("胜利", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_GUARDS_COMBAT_PLAYER_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "", SLIME_GUARDS_COMBAT_PLAYER_VICTORY_CONTINUE);
			} else if(index==2 || index==3) {
				return GUARD_POST.getResponse(responseTab, index-1);
			}
			return null;
		}
	};
	
	public static final DialogueNode SLIME_GUARDS_COMBAT_PLAYER_VICTORY_CONTINUE = new DialogueNode("胜利", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_GUARDS_COMBAT_PLAYER_VICTORY_CONTINUE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLIME_GUARDS_COMBAT_PLAYER_DEFEAT = new DialogueNode("落败", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_GUARDS_COMBAT_PLAYER_DEFEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("接受“串肉串”",
						"让[slimeFire.name]和[slimeIce.name]与你一起玩……",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						false, false,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(SlimeGuardIce.class), SexSlotAllFours.IN_FRONT),
										new Value<>(Main.game.getNpc(SlimeGuardFire.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
						},
						null,
						null, AFTER_SLIME_GUARD_SEX_DEFEATED, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SPITROASTED_UPON_DEFEAT"));
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SLIME_GUARD_SEX_DEFEATED = new DialogueNode("结束", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_GUARD_SEX_DEFEATED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Kicked Out", "[slimeFire.name]把你从前门丢了出去。", BatCaverns.SLIME_LAKE_ISLAND){
					@Override
					public void effects() {
						resetTower();
						Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_GUARD_SEX_DEFEATED_KICKED_OUT"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	
	
	public static final DialogueNode STAIRCASE_UP = new DialogueNode("螺旋楼梯", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "STAIRCASE_UP");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("上楼", "沿着旋转楼梯上到二楼。"){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLIME_QUEENS_LAIR_FIRST_FLOOR, PlaceType.SLIME_QUEENS_LAIR_STAIRS_DOWN);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode STAIRCASE_DOWN = new DialogueNode("螺旋楼梯", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "STAIRCASE_DOWN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("下楼", "沿着螺旋楼梯下到一楼。"){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLIME_QUEENS_LAIR_GROUND_FLOOR, PlaceType.SLIME_QUEENS_LAIR_STAIRS_UP);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("走廊", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ROOM = new DialogueNode("卧室", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode STORAGE_VATS = new DialogueNode("蒸馏厂", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "STORAGE_VATS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ROYAL_GUARD_POST = new DialogueNode("皇家岗哨", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)
					&& (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeated) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeatReacted));
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeated)) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_DEFEATED");
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_PACIFIED");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardIntroduced)) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_REPEAT");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeated)) {
				boolean defeatReacted = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeatReacted);
				if(index==(!defeatReacted?2:1)) {
					return new ResponseSex("做爱",
							UtilText.parse(Main.game.getNpc(SlimeRoyalGuard.class), "让[slimeRoyalGuard.name]趴在[slimeRoyalGuard.his]脚边，然后准备操[slimeRoyalGuard.him]。"),
							null, null, null, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
							null,
							null), AFTER_SLIME_ROYAL_GUARD_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_BEGIN_SEX_AS_DOM")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeatReacted, true);
						}
					};
				
				} else if(index==(!defeatReacted?3:2)) {
					return new ResponseSex("顺从",
							UtilText.parse(Main.game.getNpc(SlimeRoyalGuard.class), "让[slimeRoyalGuard.name]操你。"),
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SLIME_ROYAL_GUARD_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_BEGIN_SEX_AS_SUB")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeatReacted, true);
						}
					};
					
				} else if(!defeatReacted && index==1) {
					return new Response("继续",
							"继续穿过塔楼。",
							CORRIDOR) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX()-1, Main.game.getPlayer().getLocation().getY()));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeatReacted, true);
						}
					};
					
				} else {
					return null;
				}
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				if(index==1) {
					return new Response("交谈", "询问[slimeRoyalGuard.name]是如何守卫史莱姆女王的。", ROYAL_GUARD_POST_TALK);
					
				} else if(index==2) {
					return new ResponseCombat("格斗比赛",
							"答应[slimeRoyalGuard.namePos]提出的格斗比赛，并约定胜者可以对败者的身体为所欲为。",
							Main.game.getNpc(SlimeRoyalGuard.class),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(好吧，那我们就切磋一下，)]你说，做好了战斗准备。[pc.speech(记住你说的话，如果你输了的话，你的身体就任由我处置了。)]"),
									new Value<>(Main.game.getNpc(SlimeRoyalGuard.class), "[slimeRoyalGuard.speech(哈！)][slimeRoyalGuard.name]激动地说：[slimeRoyalGuard.speech(我可是很期待奖品呢！)]")));
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new ResponseCombat("战斗",
							"防御[slimeRoyalGuard.name]的攻击。",
							Main.game.getNpc(SlimeRoyalGuard.class),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(我打倒过比你更强大的敌人！)]你大喊，摆出了战斗姿态。"),
									new Value<>(Main.game.getNpc(SlimeRoyalGuard.class), "[slimeRoyalGuard.speech(没有人比我更强大！)][slimeRoyalGuard.name]大喊，[slimeRoyalGuard.speech(你会被打得屁滚尿流！)]")));
					
				} else if(index==2) {
					if(Main.game.getNpc(SlimeRoyalGuard.class).getFoughtPlayerCount()>0) {
						return new Response("恭维", "他已经和[slimeRoyalGuard.name]交手过一次，所以现在异常警觉，自然也不会上你的当……", null);
						
					} else {
						return new Response("恭维", "恭维[slimeRoyalGuard.namePos]，让[slimeRoyalGuard.him]露出破绽。", ROYAL_GUARD_POST_ADMIRE) {
							@Override
							public void effects() {
								Main.game.getNpc(SlimeRoyalGuard.class).setHealthPercentage(0.8f);
							}
						};
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode ROYAL_GUARD_POST_TALK = new DialogueNode("皇家岗哨", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROYAL_GUARD_POST_ADMIRE = new DialogueNode("皇家岗哨", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_ADMIRE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("战斗",
						"防御[slimeRoyalGuard.name]的攻击。",
						Main.game.getNpc(SlimeRoyalGuard.class),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(很好，那我就和你打一架。)]你说完，后退并摆出战斗姿势。"),
								new Value<>(Main.game.getNpc(SlimeRoyalGuard.class), "[slimeRoyalGuard.speech(好极了！现在保护好你自己吧！)][slimeRoyalGuard.name]怒吼，而后再次发起进攻。")));
				
			} else if(index==2) {
				if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.FEROCIOUS_WARRIOR) || Main.game.getPlayer().getHistory()==Occupation.SOLDIER) {
					return new Response("指导", "告诉[slimeRoyalGuard.name]哪里做错了，以此来吓唬[slimeRoyalGuard.he]并借此让[slimeRoyalGuard.him]露出破绽。", ROYAL_GUARD_POST_ADMIRE_INSTRUCT) {
						@Override
						public void effects() {
							Main.game.getNpc(SlimeRoyalGuard.class).setHealthPercentage(0.4f);
						}
					};
					
				} else {
					return new Response("指导",
							"你对战斗的理解还不够高，无法为[slimeRoyalGuard.name]提供任何建议。"
									+ "(需要"+Perk.FEROCIOUS_WARRIOR.getName(Main.game.getPlayer())+"天赋，或者曾经有“军人”的经历。)",
							null);
				}
				
			} else if(index==3) {
				if(Main.game.getPlayer().hasTraitActivated(Perk.NYMPHOMANIAC) || Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LUST)>=50) {
					return new Response("诱惑", "诱惑[slimeRoyalGuard.name]，煽动[slimeRoyalGuard.him]进行表演，从而让[slimeRoyalGuard.him]耗尽力气。", ROYAL_GUARD_POST_ADMIRE_SEDUCE) {
						@Override
						public void effects() {
							Main.game.getNpc(SlimeRoyalGuard.class).setLustNoText(80);
						}
					};
					
				} else {
					return new Response("诱惑", "你的魅惑技巧不足。(需要启用“性瘾成狂”特质，或者性欲伤害加成超过50。)", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROYAL_GUARD_POST_ADMIRE_INSTRUCT = new DialogueNode("皇家岗哨", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_ADMIRE_INSTRUCT");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("战斗",
						"由于你已经让[slimeRoyalGuard.name]筋疲力尽，现在的[slimeRoyalGuard.he]更容易被击败了！",
						Main.game.getNpc(SlimeRoyalGuard.class),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(当然，我会教会你的。)]你在说完之后摆出了战斗姿势。"),
								new Value<>(Main.game.getNpc(SlimeRoyalGuard.class), "[slimeRoyalGuard.speech(准备……准备好……自卫吧！)][slimeRoyalGuard.name]大口喘着气。")));
				
			} else if(index==2) {
				if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE) > Main.game.getNpc(SlimeRoyalGuard.class).getAttributeValue(Attribute.MAJOR_PHYSIQUE)) {
					return new Response("优势在我", "你已经让[slimeRoyalGuard.name]精疲力尽，很容易便可以压倒过[slimeRoyalGuard.him]。", ROYAL_GUARD_POST_ADMIRE_INSTRUCT_OVERPOWER) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeated, true);
						}
					};
					
				} else {
					return new Response("优势在我", "尽管你已经让[slimeRoyalGuard.name]精疲力尽，但还是不够强壮，力量无法胜过[slimeRoyalGuard.him]。(需要你的体格超过[slimeRoyalGuard.his]。)", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROYAL_GUARD_POST_ADMIRE_INSTRUCT_OVERPOWER = new DialogueNode("皇家岗哨", "", false, true) {

		@Override
		public boolean isTravelDisabled() {
			return ROYAL_GUARD_POST.isTravelDisabled();
		}
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_ADMIRE_INSTRUCT_OVERPOWER");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	

	public static final DialogueNode ROYAL_GUARD_POST_ADMIRE_SEDUCE = new DialogueNode("皇家岗哨", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_ADMIRE_SEDUCE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("战斗",
						"由于你已经让[slimeRoyalGuard.name]筋疲力尽，现在的[slimeRoyalGuard.he]更容易被击败了！",
						Main.game.getNpc(SlimeRoyalGuard.class),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(好吧，那我就跟你打一场……)]你叹了口气，准备好战斗。"),
								new Value<>(Main.game.getNpc(SlimeRoyalGuard.class), "[slimeRoyalGuard.speech(太，太完美了！)][slimeRoyalGuard.name]已经被你的挑逗弄得心慌意乱，兴奋不已。")));
				
			} else if(index==2) {
				return new ResponseSex("服从性爱",
						UtilText.parse(Main.game.getNpc(SlimeRoyalGuard.class), "再推[slimeRoyalGuard.name]一把，通过一场性爱彻底让他精力尽失。"),
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null) {
							@Override
							public boolean isPlayerAbleToStopSex() {
								return false;
							}
						},
						AFTER_SLIME_ROYAL_GUARD_SEX_SEDUCTION,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair",
						"ROYAL_GUARD_SEX_SEDUCTION")) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeated, true);
					}
				};
					
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AFTER_SLIME_ROYAL_GUARD_SEX_AS_DOM = new DialogueNode("结束", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_AS_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SLIME_ROYAL_GUARD_SEX_AS_SUB = new DialogueNode("结束", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_AS_SUB");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SLIME_ROYAL_GUARD_SEX_SEDUCTION = new DialogueNode("结束", "", false) {

		@Override
		public boolean isTravelDisabled() {
			return ROYAL_GUARD_POST.isTravelDisabled();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_SEDUCTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLIME_ROYAL_GUARD_COMBAT_PLAYER_VICTORY = new DialogueNode("胜利", "", false) {
		
		@Override
		public boolean isTravelDisabled() {
			return ROYAL_GUARD_POST.isTravelDisabled();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_COMBAT_PLAYER_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLIME_ROYAL_GUARD_COMBAT_PLAYER_DEFEAT = new DialogueNode("落败", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_COMBAT_PLAYER_DEFEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("做爱",
						"[slimeRoyalGuard.Name]上前来想跟你共赴巫山……",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null), AFTER_SLIME_ROYAL_GUARD_SEX_DEFEATED, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_COMBAT_PLAYER_DEFEAT_SEX"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SLIME_ROYAL_GUARD_SPARRING_PLAYER_VICTORY = new DialogueNode("胜利", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_SPARRING_PLAYER_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("做爱",
						UtilText.parse(Main.game.getNpc(SlimeRoyalGuard.class), "让[slimeRoyalGuard.name]趴在[slimeRoyalGuard.his]脚边，然后准备操[slimeRoyalGuard.him]。"),
						null, null, null, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
						null,
						null), AFTER_SLIME_ROYAL_GUARD_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_BEGIN_SEX_AS_DOM"));
			
			} else if(index==2) {
				return new ResponseSex("顺从",
						UtilText.parse(Main.game.getNpc(SlimeRoyalGuard.class), "让[slimeRoyalGuard.name]操你。"),
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null), AFTER_SLIME_ROYAL_GUARD_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_BEGIN_SEX_AS_SUB"));
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SLIME_ROYAL_GUARD_SPARRING_PLAYER_DEFEAT = new DialogueNode("落败", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_SPARRING_PLAYER_DEFEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("做爱",
						"[slimeRoyalGuard.Name]上前来想跟你共赴巫山……",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SLIME_ROYAL_GUARD_SEX_SPARRING_DEFEATED,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_COMBAT_PLAYER_DEFEAT_SEX"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SLIME_ROYAL_GUARD_SEX_DEFEATED = new DialogueNode("结束", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_DEFEATED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被丢出去", "[slimeRoyalGuard.name]把你从正门丢了出去。", BatCaverns.SLIME_LAKE_ISLAND){
					@Override
					public void effects() {
						resetTower();
						Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_DEFEATED_KICKED_OUT"));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SLIME_ROYAL_GUARD_SEX_SPARRING_DEFEATED = new DialogueNode("结束", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_SPARRING_DEFEATED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode BED_CHAMBER = new DialogueNode("寝宫", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
//		@Override
//		public boolean isTravelDisabled() {
//			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE);
//		}
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BED_CHAMBER_PACIFIED");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BED_CHAMBER");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				return SLIME_QUEEN_CONVINCE.getResponse(responseTab, index);
				
			} else {
				if(index==1) {
					return new Response("说服", "说服[slimeQueen.name]不要再让其他史莱姆转化屈城区隧道里的人们了。", SLIME_QUEEN_CONVINCE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeQueenConvinced, true);
							
							AbstractClothing crown = Main.game.getNpc(SlimeQueen.class).getClothingInSlot(InventorySlot.HEAD);
							if(crown!=null) {
								Main.game.getNpc(SlimeQueen.class).unequipClothingIntoVoid(crown, true, Main.game.getNpc(SlimeQueen.class));
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_head_slime_queens_tiara", false), true));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_SIX_CONVINCE));
						}
					};
					
				} else if(index==2) {
					return new Response("暴力", "如果她敬酒不吃吃罚酒，那[slimeQueen.namePos]就尽管来吧。把她推倒在床上，强迫她放弃计划。", SLIME_QUEEN_FORCE,
							Util.newArrayListOfValues(Fetish.FETISH_SADIST), Fetish.FETISH_SADIST.getAssociatedCorruptionLevel(), null, null, null) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeQueenForced, true);

							AbstractClothing crown = Main.game.getNpc(SlimeQueen.class).getClothingInSlot(InventorySlot.HEAD);
							if(crown!=null) {
								Main.game.getNpc(SlimeQueen.class).unequipClothingIntoVoid(crown, true, Main.game.getNpc(SlimeQueen.class));
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_head_slime_queens_tiara", false), true));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_FORCE));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_SIX_FORCE));
						}
					};
					
				} else if(index==3) {
					return new Response("协助", "你并不想阻止她，反而向凯瑟琳提供协助。<b>这将大幅增加在屈城区遇到史莱姆的几率。</b>", SLIME_QUEEN_SUBMIT,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeQueenHelped, true);

							AbstractClothing crown = Main.game.getNpc(SlimeQueen.class).getClothingInSlot(InventorySlot.HEAD);
							if(crown!=null) {
								Main.game.getNpc(SlimeQueen.class).unequipClothingIntoVoid(crown, true, Main.game.getNpc(SlimeQueen.class));
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_head_slime_queens_tiara", false), true));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_SUBMIT));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_SIX_SUBMIT));
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode SLIME_QUEEN_CONVINCE = new DialogueNode("寝宫", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_CONVINCE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "无视[slimeQueen.namePos]挑逗的淫叫声，离开。", SLIME_QUEEN_LEAVE);
				
			} else if(index==2) {
				return new ResponseSex("“强奸”",
						UtilText.parse(Main.game.getNpc(SlimeQueen.class), "顺应[slimeQueen.namePos]的幻想，强上了她。"),
						null, null, null, null, null, null,
						true, false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(SlimeQueen.class), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public boolean isRapePlayBannedAtStart(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						AFTER_SLIME_QUEEN_SEX, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_SEX_START"));
				
			} else if(index==3) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					return new Response("粘液浴",
							"与[slimeQueen.Name]鸳鸯戏水。"
									+ "<br/>[style.italicsExcellent(清理所有腔穴中的<b>全部</b>液体。)]"
									+ "<br/>[style.italicsGood(将<b>仅会</b>清理当前装备的衣物。)]",
							SLIME_QUEEN_BATH);
					
				} else {
					return new Response("粘液浴",
							"与[slimeQueen.name]鸳鸯戏水。"
								+ "<br/>[style.boldTerrible(警告:)][style.boldSlime(这将会使你变成史莱姆！)]"
								+ "<br/>[style.italicsExcellent(清理所有腔穴中的<b>全部</b>液体。)]"
								+ "<br/>[style.italicsGood(将<b>仅会</b>清理当前装备的衣物。)]",
							SLIME_QUEEN_BATH_TRANSFORM) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.TRANSFORMATION_GENERIC;
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setBodyMaterial(BodyMaterial.SLIME));
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_BATH_TRANSFORM_END"));
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode SLIME_QUEEN_FORCE = new DialogueNode("寝宫", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_FORCE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLIME_QUEEN_CONVINCE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SLIME_QUEEN_SUBMIT = new DialogueNode("寝宫", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_SUBMIT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLIME_QUEEN_CONVINCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLIME_QUEEN_BATH = new DialogueNode("寝宫", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_BATH, 240+30));
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_BATH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "回绝[slimeQueen.namePos]挑逗的淫叫声，离开。", SLIME_QUEEN_LEAVE);
				
			} else if(index==2) {
				return new ResponseSex("“强奸”",
						UtilText.parse(Main.game.getNpc(SlimeQueen.class), "顺应[slimeQueen.namePos]的幻想，强上了她。"),
						null, null, null, null, null, null,
						true, false,
						new SMBath(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(SlimeQueen.class), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isRapePlayBannedAtStart(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						AFTER_SLIME_QUEEN_SEX_BATH,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_BATH_SEX_START"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SLIME_QUEEN_BATH_TRANSFORM = new DialogueNode("寝宫", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_BATH_TRANSFORM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLIME_QUEEN_BATH.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLIME_QUEEN_LEAVE = new DialogueNode("寝宫", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.SLIME_QUEENS_LAIR_FIRST_FLOOR, PlaceType.SLIME_QUEENS_LAIR_CORRIDOR, false);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_LEAVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SLIME_QUEEN_TALK = new DialogueNode("寝宫", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return BED_CHAMBER.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode AFTER_SLIME_QUEEN_SEX_BATH = new DialogueNode("结束", "", true) {
		@Override
		public String getDescription() {
			return "在玩够了之后，也是时候该结束这一切了……";
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(SlimeQueen.class))>=Main.game.getNpc(SlimeQueen.class).getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_QUEEN_SEX_BATH");
			} else {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_QUEEN_SEX_BATH_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return BED_CHAMBER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SLIME_QUEEN_SEX = new DialogueNode("结束", "", true) {
		@Override
		public String getDescription() {
			return "在玩够了之后，也是时候该结束这一切了……";
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(SlimeQueen.class))>=Main.game.getNpc(SlimeQueen.class).getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_QUEEN_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_QUEEN_SEX_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return BED_CHAMBER.getResponse(responseTab, index);
		}
	};
}
