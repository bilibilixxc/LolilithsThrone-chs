package com.lilithsthrone.game.dialogue.story;

import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.npc.submission.Takahashi;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3
 * @version 0.3.4
 * @author Innoxia
 */
public class LyssiethReveal {

	public static final DialogueNode ENTRANCE_WITH_ELIZABETH = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "ENTRANCE_WITH_ELIZABETH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("跟随", "让伊丽莎白领着你进入莉西丝的王座间。", FORWARDS_1) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1));
						Main.game.getNpc(Elizabeth.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FORWARDS_1 = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "FORWARDS_1");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续跟着伊丽莎白。", FORWARDS_2) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1));
						Main.game.getNpc(Elizabeth.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FORWARDS_2 = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "FORWARDS_2");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进入", "你做好与莉西丝见面的准备，跟随伊丽莎白走进开着的门。",
						FORWARDS_3) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1));
						Main.game.getNpc(Elizabeth.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_GATE);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FORWARDS_3 = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "FORWARDS_3");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("莉西丝的办公室", "让[siren.name]将你介绍给莉西丝。", OFFICE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1));
						Main.game.getNpc(DarkSiren.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("防御", "莉西丝似乎想要攻击你！准备防御！", OFFICE_REACTION);
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_REACTION = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("抵抗", "不要放弃！抵抗莉西丝的法术，努力站起来。", OFFICE_REACTION_BETRAYAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION_RESIST"));
					}
				};
				
			} else if(index==2) {
				return new Response("屈服", "好吧……下跪……就屈服吧……", OFFICE_REACTION_BETRAYAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION_SUBMIT"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_REACTION_BETRAYAL = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION_BETRAYAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("站起", "听从莉西丝的指示，站起身子。", OFFICE_REACTION_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_REACTION_END = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION_END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("向前", "走到莉西丝身边，让让她把你和[siren.name]传送到莉莱雅的家里。", OFFICE_TELEPORT);
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_TELEPORT = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_TELEPORT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("到达", "你们三个到达了莉西丝的实验室中。", OFFICE_TELEPORT_ARRIVE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(DarkSiren.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Lyssieth.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Arthur.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_TELEPORT_ARRIVE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_TELEPORT_ARRIVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("亚瑟", "听听亚瑟的理论。", LAB_ARTHUR_THEORY);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_ARTHUR_THEORY = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ARTHUR_THEORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("什么？！", "对于莉西丝说出的事实表达震惊。", LAB_WORLD_REVEAL);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_WORLD_REVEAL = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_WORLD_REVEAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("莉莱雅", "虽然[siren.name]和亚瑟似乎都轻松地接受了事实，但莉莱雅看起来很苦恼，像是有话要说。", LAB_LILAYA_ANGERY);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_LILAYA_ANGERY = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_LILAYA_ANGERY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("安慰", "安慰莉莱雅，说她仍旧还算是你认识的那个人，让她冷静下来。", LAB_LILAYA_CALMED_DOWN);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_LILAYA_CALMED_DOWN = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_LILAYA_CALMED_DOWN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked1)) {
					return new Response("世界", "你已经问过莉西丝这个世界其他地方的事情了。", null);
				} else {
					return new Response("世界", "询问莉西丝为什么世界上其他地方没有采取行动。", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_WORLD"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked1, true);
							AbstractItemEffectType.getBookEffect(Main.game.getPlayer(), Subspecies.LILIN, null, false);
						}
					};
				}
				
			} if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked2)) {
					return new Response("背叛", "你已经问过莉西丝为什么选择背叛莉莉丝了。", null);
				} else {
					return new Response("背叛", "询问莉西丝她为什么要背叛莉莉丝。", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_BETRAYAL"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked2, true);
						}
					};
				}
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked3)) {
					return new Response("人类", "莉莱雅已经问过人类转化的问题了。", null);
				} else {
					return new Response("人类", "莉莱雅想要问询人类被转化成各种样子，是依照了什么规律。", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_PEOPLE"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked3, true);
						}
					};
				}
				
			} else if(index==4) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked4)) {
					return new Response("法术", "[siren.Name]已经问过莉西丝这种扭曲现实的法术了。", null);
				} else {
					return new Response("法术", "[siren.Name]想要询问有关莉莉丝这个扭曲现实的法术的问题。", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_SPELL"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked4, true);
						}
					};
				}
				
			} else if(index==5) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked5)) {
					return new Response("逆转", "亚瑟已经问过莉西丝，是否有可能逆转莉西丝的法术。", null);
				} else {
					return new Response("逆转", "亚瑟想要询问是否有可能逆转莉西丝的法术。", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_REVERSAL"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked5, true);
						}
					};
				}
				
			} else if(index==6) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked1)
						|| !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked2)
						|| !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked3)
						|| !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked4)
						|| !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked5)) {
					return new Response("继续", "在继续之前，你应该先问问莉西丝法术的事情。", null);
				} else {
					return new Response("继续", "莉西丝没时间再回答其他问题了。", LAB_QUESTION_END);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode LAB_QUESTION = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_LILAYA_CALMED_DOWN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LAB_QUESTION_END = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("解放", "告诉大家你想击败莉莉丝，将世界从她的暴政下解放。", LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_LIBERATE"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), 25));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionLiberate, true);
					}
				};
				
			} else if(index==2) {
				return new Response("篡位", "说你想击败莉莉丝，并且取代她成为御城区的统治者。", LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_USURP"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionUsurp, true);
					}
				};
				
			} else if(index==3) {
				return new Response("加入", "说你想加入莉莉丝。", LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_JOIN"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), -20));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionJoin, true);
					}
				};
				
			} else if(index==4) {
				return new Response("没什么想说",
						"你对于拯救世界的屁话没什么兴趣。什么都不做，把这个世界的命运交给那些更有精力的家伙可简单多了。",
						LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_NOTHING"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionNothing, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_ENDING = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("不可能", "询问莉西丝你怎么才能击败一位莉琳长老，还有一支由恶魔半人马组成的军队。", LAB_ENDING_MINOTALLYS);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_ENDING_MINOTALLYS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_MINOTALLYS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("妖狐？", "询问梅拉克西丝谁是妖狐。", LAB_ENDING_SIREN_HELP);
			}
			return null;
		}
	};

	public static final DialogueNode LAB_ENDING_SIREN_HELP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Takahashi.class).setPlayerKnowsName(true);
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_SIREN_HELP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("返回", "跟莉西丝回到办公室，让莉莱雅和[siren.name]留在这。", LAB_ENDING_RETURN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						Main.game.getNpc(Lyssieth.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Arthur.class).returnToHome();
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_ENDING_RETURN = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_RETURN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getRace()==Race.HUMAN) {
					return new Response("拒绝做爱",
							"告诉莉西丝你不想做爱，如果她需要高潮，那自慰就好了。",
							LAB_ENDING_RETURN_DECLINE_SEX) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_RETURN_DECLINE_SEX_HUMAN"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(250, false));
						}
					};
				} else {
					return new Response("拒绝做爱",
							"告诉莉西丝你没兴趣跟她做爱，直接让她将能力注入你的灵气中。",
							LAB_ENDING_RETURN_DECLINE_SEX) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_RETURN_DECLINE_SEX"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(250, false));
						}
					};
				}
				
			} else if(index==2) {
				return new ResponseSex("小穴",
						"告诉莉西丝你想跟她做爱，而且想要作为主导者使用她的蜜穴。",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE))),
						null,
						null,
						POWER_VISION,
						UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "SEX_PUSSY"));
				
			} else if(index==3) {
				return new ResponseSex("肉棒",
						"告诉莉西丝你想跟她做爱，让她长出一根鸡巴，作为主导者来干你。",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))),
						null,
						null,
						POWER_VISION,
						UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "SEX_COCK")) {
					@Override
					public void effects() {
						((Lyssieth) Main.game.getNpc(Lyssieth.class)).growCock(PenisType.HUMAN);
					}
				};
			}
			return null;
		}
	};
	
//	private static void setPlayerAsLyssieth() {
//		PlayerCharacter player = new PlayerCharacter(
//				new NameTriplet("Lyssieth"),
//				1000,
//				null,
//				Gender.F_V_B_FEMALE,
//				Subspecies.DEMON,
//				RaceStage.GREATER,
//				WorldType.LYSSIETH_PALACE,
//				PlaceType.LYSSIETH_PALACE_OFFICE);
//		player.setSurname("Lilithmartuilani");
//		player.setDescription("One of the seven elder Lilin, you are one of the most powerful beings in existence.");
//		player.setSubspeciesOverride(Subspecies.ELDER_LILIN);
//		player.getBody().calculateRace(player);
//		player.setAttribute(Attribute.MAJOR_PHYSIQUE, 100);
//		player.setAttribute(Attribute.MAJOR_ARCANE, 100);
//		player.setAttribute(Attribute.MAJOR_CORRUPTION, 100);
//		Main.game.setPlayer(player);
//	}
	
	public static final DialogueNode LAB_ENDING_RETURN_DECLINE_SEX = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("幻象", "你看到了奇怪的幻象，你仿佛变成了莉西丝……", POWER_VISION) {
					@Override
					public void effects() {
//						setPlayerAsLyssieth();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POWER_VISION = new DialogueNode("幻象", "你看到了奇怪的幻象，你是莉西丝……", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "POWER_VISION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("清醒", "你恢复了意识，仍在莉西丝的办公室。", POWER_EXPLANATION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addSpecialPerk(Perk.POWER_OF_LYSSIETH_4));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POWER_EXPLANATION = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "POWER_EXPLANATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("膝枕", "接受莉西丝的建议，继续躺在她的大腿上，跟她讲述你看到的幻象。", POWER_EXPLANATION_CONTINUE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "POWER_EXPLANATION_LAP"));
					}
				};
				
			} else if(index==2) {
				return new Response("站起", "你觉得不太舒服。站起身后跟她讲述你看到的幻象。", POWER_EXPLANATION_CONTINUE) { // But why
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "POWER_EXPLANATION_STAND_UP"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POWER_EXPLANATION_CONTINUE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "走出莉西丝的办公室，让她把[siren.name]带回来。", END_SIREN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						if(Main.game.getNpc(DarkSiren.class).getAffection(Main.game.getPlayer())<0) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getPlayer(), 0));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode END_SIREN = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "END_SIREN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("地图", "拿走递给你的地图。", END_FINAL) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_3_ELIS));
						Main.game.getTextEndStringBuilder().append(
								"<div class='container-full-width' style='text-align:center;'>"
										+ "[style.colourExcellent(你解锁了世界地图！)]<br/>"
										+ "<i>可以通过手机的地图菜单看到，或者行进至御城区的任何一处出口地块，选择“世界旅行”菜单。</i>"
								+ "</div>");
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode END_FINAL = new DialogueNode("", "", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "END_FINAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false).getResponse(responseTab, index);
		}
	};
}
