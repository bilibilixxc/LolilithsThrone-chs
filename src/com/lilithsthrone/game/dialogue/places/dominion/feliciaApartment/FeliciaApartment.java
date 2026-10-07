package com.lilithsthrone.game.dialogue.places.dominion.feliciaApartment;

import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.npc.dominion.Felicia;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.DemonHome;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4.1
 * @author DSG (edited by Innoxia)
 */
public class FeliciaApartment {
	
	private static Felicia getFelicia() {
		return ((Felicia)Main.game.getNpc(Felicia.class));
	}
	
	public static final DialogueNode ENTRYWAY = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "ENTRYWAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "离开菲利希亚的家。", DemonHome.DEMON_HOME_ARTHURS_APARTMENT) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FELICIA_BEDROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "FELICIA_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode BATHROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "BATHROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode KITCHEN = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "KITCHEN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode DINING_AREA = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "DINING_AREA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode LIVING_AREA = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "LIVING_AREA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && getFelicia().getLocation().equals(Main.game.getPlayer().getLocation())) {
				return new Response("交谈", "跟菲利希亚聊聊。", FELICIA_GREETINGS_APPROACH);			
			}
			return null;
		}
	};
	
	public static final DialogueNode HALLWAY = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "HALLWAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode FELICIA_GREETINGS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "FELICIA_GREETINGS");		
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TALK_MENU.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode FELICIA_GREETINGS_APPROACH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "FELICIA_GREETINGS_APPROACH");		
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TALK_MENU.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode FELICIA_GOODBYE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "FELICIA_GOODBYE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ARTHUR_WHEREABOUTS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "ARTHUR_WHEREABOUTS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
					return new Response("否定", "告诉她你还没找到人。", ARTHUR_WHEREABOUTS_NO);
					
				} else {
					return new Response("承认", "将好消息告诉给她。", FeliciaApartment.ARTHUR_WHEREABOUTS_YES) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 30));
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode ARTHUR_WHEREABOUTS_NO = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "ARTHUR_WHEREABOUTS_NO");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TALK_MENU.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ARTHUR_WHEREABOUTS_YES = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaToldAboutArthur, true);
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "ARTHUR_WHEREABOUTS_YES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TALK_MENU.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TALK_MENU = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {   
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_MENU");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("亚瑟……", "询问亚瑟的事情。", TALK_MENU_ARTHUR);
				
			} else if (index == 2) {
				return new Response("问问她自己", "问问[felicia.name]自己的事情。", TALK_MENU_ABOUT_HER);
				
			} else if (index == 3) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaLewdTalkAborted)) {
					return new Response("放荡", "问[felicia.name]下流的问题。", TALK_LEWD);
				} else {
					return new Response("放荡", "再问一次或许不是个好主意。", null);
				}
				
			} else if (index == 4) {
				if (getFelicia().getAffection(Main.game.getPlayer()) >= AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()) {
					return new Response("抚摸", "问[felicia.name]能不能摸她。", TALK_PET);
				} else {
					return new Response("抚摸", "[felicia.Name]可能不会太喜欢。", null);
				}
				
			} else if (index == 0) {
				return new Response("离开", "先离开菲利希亚身边。", FELICIA_GOODBYE);
			}
			return null;
		}
	};
	
	public static final DialogueNode TALK_MENU_ARTHUR = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_MENU_ARTHUR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("个性", "亚瑟是什么样的人？", TALK_ARTHUR_PERSONALITY) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaAskedArthurPersonality)) {
							Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 5));
						}
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaAskedArthurPersonality, true);
					}
				};
				
			} else if (index == 2) {
				return new Response("爱好", "亚瑟平时喜欢做什么事情？", TALK_ARTHUR_HOBBIES) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaAskedArthurHobbies)) {
							Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 5));
						}
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaAskedArthurHobbies, true);
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "询问其他的话题。", TALK_MENU);
			}
			return null;
		}
	};
	
	public static final DialogueNode TALK_MENU_ABOUT_HER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_MENU_ABOUT_HER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("姓氏", "问问菲利希亚那不同寻常的姓氏。", TALK_ABOUT_HER_NAME) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaAskedAboutHerSurname)) {
							Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 5));
						}
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaAskedAboutHerSurname, true);
					}
				};
				
			} else if (index == 2) {
				return new Response("她的住处", "东西怎么这么少？", TALK_ABOUT_HER_PLACE) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaAskedAboutHerPlace)) {
							Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 5));
						}
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaAskedAboutHerPlace, true);
					}
				};
				
			} else if (index == 3) {
				return new Response("皮毛", "她怎么做到这么毛茸茸的？", TALK_ABOUT_HER_FUR) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaAskedAboutHerFur)) {
							Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 5));
						}
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaAskedAboutHerFur, true);
					}
				};
				
			} else if (index == 4) {
				return new Response("喜爱的商店", "她喜欢去哪购物？", TALK_ABOUT_HER_FAVORITE_STORE) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaAskedAboutHerFavoriteStore)) {
							Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), 5));
						}
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaAskedAboutHerFavoriteStore, true);
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "询问其他的话题。", TALK_MENU);
			}
			return null;
		}
	};
	
	public static final DialogueNode TALK_ARTHUR_PERSONALITY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_ARTHUR_PERSONALITY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TALK_MENU_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TALK_ARTHUR_HOBBIES = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_ARTHUR_HOBBIES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TALK_MENU_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TALK_ABOUT_HER_NAME = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_ABOUT_HER_NAME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TALK_MENU_ABOUT_HER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TALK_ABOUT_HER_PLACE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_ABOUT_HER_PLACE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TALK_MENU_ABOUT_HER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TALK_ABOUT_HER_FUR = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_ABOUT_HER_FUR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TALK_MENU_ABOUT_HER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TALK_ABOUT_HER_FAVORITE_STORE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_ABOUT_HER_FAVORITE_STORE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TALK_MENU_ABOUT_HER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TALK_LEWD = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_LEWD");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "那其他人呢？", TALK_LEWD_CONTINUE);
				
			} else if (index == 2) {
				return new Response("放弃", "接受暗示，聊聊别的事情。", TALK_MENU) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaLewdTalkAborted, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode TALK_LEWD_CONTINUE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_LEWD_CONTINUE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("施压", "在这个王国里，还能有人这么纯洁吗？", TALK_LEWD_PUSH) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(getFelicia().incrementAffection(Main.game.getPlayer(), -5));
					}
				};
				
			} else if (index == 2) {
				return new Response("放弃", "接受暗示，聊聊别的事情。", TALK_MENU) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaLewdTalkAborted, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode TALK_LEWD_PUSH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_LEWD_PUSH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("深挖",
						"你<i>必须要</i>知道！"
						+ "<br/>[style.italicsBad(你暗自疑虑着这事结局会变得很糟糕！)]",
						TALK_LEWD_DIG_DEEP) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_BAD;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(getFelicia().setAffection(Main.game.getPlayer(), -100));
					}
				};
				
			} else if (index == 2) {
				return new Response("放弃", "接受暗示，聊聊别的事情。", TALK_MENU) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaLewdTalkAborted, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode TALK_LEWD_DIG_DEEP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_LEWD_DIG_DEEP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "你今天已经伤够人了。", DemonHome.DEMON_HOME_ARTHURS_APARTMENT) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ARTHUR);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feliciaRejectedPlayer, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode TALK_PET = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/feliciaApartment/apartment", "TALK_PET");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TALK_MENU.getResponse(responseTab, index);
		}
	};
	
}
