package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import com.lilithsthrone.game.character.npc.dominion.Finch;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.2 (content from 0.1.0)
 * @version 0.4
 * @author Innoxia
 */
public class SlaveryAdministration {

	private static int slaverLicenseCost = 5000;
	
	private static Finch getFinch() {
		return (Finch) Main.game.getNpc(Finch.class);
	}
	
	public static final DialogueNode SLAVERY_ADMINISTRATION_EXTERIOR = new DialogueNode("奴隶管理局", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/slaveryAdministration", "SLAVERY_ADMINISTRATION_EXTERIOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("进入", "走进奴隶管理局。", SLAVERY_ADMINISTRATION);

			} else if(index==2) {
				if(Main.game.getCurrentDialogueNode()==SLAVERY_ADMINISTRATION_POSTERS) {
					return new Response("海报", "你已经仔细看过海报了……", null);
				}
				return new Response("海报", "仔细看一下张贴在奴隶管理局建筑墙上的海报。", SLAVERY_ADMINISTRATION_POSTERS);
			}
			return null;
		}
	};

	public static final DialogueNode SLAVERY_ADMINISTRATION_POSTERS = new DialogueNode("奴隶管理局", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/slaveryAdministration", "SLAVERY_ADMINISTRATION_POSTERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVERY_ADMINISTRATION_EXTERIOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLAVERY_ADMINISTRATION = new DialogueNode("奴隶管理局", ".", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/slaveryAdministration", "SLAVERY_ADMINISTRATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isHasSlaverLicense()) {
				if (index == 1) {
					return new ResponseTrade("交易", "购买奴役相关物品。", getFinch());

				} else if (index == 2 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.finchFreedomTalk)) {
					return new Response("释放奴隶", "询问芬奇如果要释放奴隶，需要经过什么流程。", SLAVE_FREEDOM_TALK) {
						@Override
						public void effects() {
							getFinch().addItem(Main.game.getItemGen().generateItem("innoxia_slavery_freedom_certification"), 10, false, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.finchFreedomTalk, true);
						}
					};

				} else if (index == 5) {
					return new Response("奴隶管理", "打开奴隶管理界面。", SLAVERY_ADMINISTRATION) {
						@Override
						public boolean isTradeHighlight() {
							return true;
						}
						@Override
						public DialogueNode getNextDialogue() {
							CompanionManagement.initManagement(null, 0, null);
							return OccupantManagementDialogue.getSlaveryManagementDialogue(null, null);
						}
					};

				} else if (index == 0) {
					return new Response("离开", "回到外边。", SLAVERY_ADMINISTRATION_EXTERIOR);

				} else {
					return null;
				}
			} else {
				if (index == 1) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLAVERY)) {
						return new Response("贩奴许可", "询问芬奇获得贩奴许可的事情。", SLAVERY_ADMINISTRATION_ASK_ABOUT_SLAVER_LICENSE) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_SLAVERY));
								Main.game.getDialogueFlags().values.add(DialogueFlagValue.finchIntroduced);
							}
						};
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY) == Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED) {
						if(Main.game.getPlayer().getMoney() >= slaverLicenseCost) {
							return new Response("递交信件(<span style='color:" + PresetColour.CURRENCY_GOLD.toWebHexString() + ";'>" + UtilText.getCurrencySymbol() + "</span> "+slaverLicenseCost+")",
									"将你从莉莱雅处得到的推荐信交给芬奇，然后上交"+slaverLicenseCost+"火币以获取贩奴许可。",
									SLAVERY_ADMINISTRATION_SLAVER_LICENSE_OBTAINED) {
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-slaverLicenseCost);
								}
							};
						} else {
							return new Response("递交信件(" + UtilText.getCurrencySymbol() + ""+slaverLicenseCost+")", "你没有足够的钱来买贩奴许可！你至少需要"+slaverLicenseCost+"火币。", null);
						}
						
					} else {
						return new Response("递交信件(" + UtilText.getCurrencySymbol() + ""+slaverLicenseCost+")", "你需要先从莉莱雅那里拿到推荐信！", null);
						
					}
					
				} else if (index == 0) {
					return new Response("离开", "回到外边。", SLAVERY_ADMINISTRATION_EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.finchIntroduced);
						}
					};

				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode SLAVERY_ADMINISTRATION_ASK_ABOUT_SLAVER_LICENSE = new DialogueNode("奴隶管理局", ".", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/slaveryAdministration", "SLAVERY_ADMINISTRATION_ASK_ABOUT_SLAVER_LICENSE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVERY_ADMINISTRATION.getResponse(0, index);
		}
	};
	
	public static final DialogueNode SLAVERY_ADMINISTRATION_SLAVER_LICENSE_OBTAINED = new DialogueNode("奴隶管理局", ".", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/slaveryAdministration", "SLAVERY_ADMINISTRATION_SLAVER_LICENSE_OBTAINED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("规章", "让[finch.name]给你解释一下规矩。", SLAVERY_ADMINISTRATION_SLAVER_LICENSE_OBTAINED_RULES) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLAVERY, Quest.SIDE_UTIL_COMPLETE));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.SLAVER_LICENSE), false));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SLAVERY_ADMINISTRATION_SLAVER_LICENSE_OBTAINED_RULES = new DialogueNode("奴隶管理局", ".", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/slaveryAdministration", "SLAVERY_ADMINISTRATION_SLAVER_LICENSE_OBTAINED_RULES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVERY_ADMINISTRATION.getResponse(0, index);
		}
	};

	public static final DialogueNode SLAVE_FREEDOM_TALK = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/slaveryAdministration", "SLAVE_FREEDOM_TALK_END"));
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/slaveryAdministration", "SLAVE_FREEDOM_TALK_END_START_ACCOMMODATION_QUEST"));
				Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(ItemType.getItemTypeFromId("innoxia_slavery_freedom_certification").getValue()), true);
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/slaveryAdministration", "SLAVE_FREEDOM_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVERY_ADMINISTRATION.getResponse(0, index);
		}
	};
	
}
