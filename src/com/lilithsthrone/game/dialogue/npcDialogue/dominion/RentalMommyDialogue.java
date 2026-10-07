package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.5
 * @version 0.4.2.2
 * @author Innoxia
 */
public class RentalMommyDialogue {

	private static NPC getMommy() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNode ENCOUNTER = new DialogueNode("租赁妈咪", "", true) {
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.mommyFound)) {
				return UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "ENCOUNTER_REPEAT");
			} else {
				return UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "ENCOUNTER");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<50) {
					return new Response("租用"+UtilText.formatAsMoneyUncoloured(50, "span"), "你的钱不够……", null);
				}
				return new Response("租用"+UtilText.formatAsMoney(50, "span"), "请租赁妈咪，花一些时间把你的头枕在她的腿上。", MOMMYS_EXTRAS) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-50));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.mommyFound, true);
					}
				};
				
			} else if(index==2) {
				return new Response("拒绝", "告诉租赁妈咪，你现在没兴趣雇用她。", ENCOUNTER) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "DECLINE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.mommyFound, true);
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode MOMMYS_EXTRAS = new DialogueNode("租赁妈咪", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS"));
			
			if(Main.game.isLactationContentEnabled()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS_OFFER_BREASTFEEDING"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<1000) {
					return new Response("服从型性爱"+UtilText.formatAsMoneyUncoloured(1000, "span"), "你钱不够！", null);
				}
				return new ResponseSex("服从型性爱"+UtilText.formatAsMoney(1000, "span"), "跟“妈咪”进她家，让她作为支配方和你做爱。",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(getMommy()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						AFTER_SEX_MOMMY_AS_DOM,
						UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY_SEX_SUB")
							+ UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY")
							+ UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_SEX_SUB")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-1000);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getMoney()<2000) {
					return new Response("支配型性爱"+UtilText.formatAsMoneyUncoloured(2000, "span"), "你钱不够！", null);
				}
				return new ResponseSex("支配型性爱"+UtilText.formatAsMoney(2000, "span"), "跟“妈咪”进她家，作为支配方和她做爱。",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMommy()),
						null,
						null) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						AFTER_SEX_MOMMY_AS_SUB,
						UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY_SEX_DOM")
							+ UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY")
							+ UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_SEX_DOM")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-2000);
					}
				};
				
			} else if(index==3) {
				if(Main.game.getPlayer().getMoney()<500) {
					return new Response("哺乳"+UtilText.formatAsMoneyUncoloured(500, "span"), "你没有足够的钱！", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("哺乳"+UtilText.formatAsMoneyUncoloured(500, "span"), "你不能使用你的嘴，所以“妈咪”不能给你喂奶！", null);
				}
				return new Response("哺乳"+UtilText.formatAsMoney(500, "span"), "跟着“妈咪”进屋，由她喂奶。", MOMMYS_EXTRAS_BREASTFEEDING) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-500);
						getMommy().setAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer(), true);
						getMommy().setAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer(), true);
						Main.game.getPlayer().ingestFluid(getMommy(), getMommy().getMilk(), SexAreaOrifice.MOUTH, 500);
					}
				};
				
			} else if(index==4) {
				if(Main.game.getPlayer().getMoney()<1000) {
					return new Response("公开母乳喂养 "+UtilText.formatAsMoneyUncoloured(1000, "span"), "你没有足够的钱做这个！", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("公开哺乳"+UtilText.formatAsMoneyUncoloured(1000, "span"), "你不能使用你的嘴，所以“妈咪”不能给你喂奶！", null);
				}
				return new Response("公开哺乳"+UtilText.formatAsMoney(1000, "span"), "在众目睽睽之下，继续坐在长椅上，由“妈咪”喂奶。", MOMMYS_EXTRAS_BREASTFEEDING_PUBLIC) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-1000);
						getMommy().setAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer(), true);
						getMommy().setAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer(), true);
						Main.game.getPlayer().incrementFetishExperience(Fetish.FETISH_LACTATION_OTHERS, 25);
						Main.game.getPlayer().incrementFetishExperience(Fetish.FETISH_EXHIBITIONIST, 10);
						Main.game.getPlayer().ingestFluid(getMommy(), getMommy().getMilk(), SexAreaOrifice.MOUTH, 500);
					}
				};
				
			} else if(index==5) {
				return new Response("拒绝", "拒绝“妈咪”的额外邀请，然后离开。", ENCOUNTER) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS_DECLINED"));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode MOMMYS_EXTRAS_BREASTFEEDING = new DialogueNode("租赁妈咪", "", true, true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY_BREASTFEEDING"));
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY"));
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS_BREASTFEEDING"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(index==1) {
				return new Response("离开", "感谢妈咪，然后告辞。", MOMMYS_EXTRAS_BREASTFEEDING) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MOMMYS_EXTRAS_BREASTFEEDING_PUBLIC = new DialogueNode("租赁妈咪", "", true, true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS_BREASTFEEDING_PUBLIC"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(index==1) {
				return new Response("离开", "感谢妈咪，然后告辞。", MOMMYS_EXTRAS_BREASTFEEDING_PUBLIC) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_MOMMY_AS_DOM = new DialogueNode("结束", "租赁妈咪真是物有所值，性爱结束了……", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getNumberOfOrgasms(getMommy()) >= getMommy().getOrgasmsBeforeSatisfied()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_ORGASMED"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_NO_ORGASM"));
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "AFTER_SEX_MOMMY_AS_DOM_END"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(index==1) {
				return new Response("离开", "感谢妈咪，然后告辞。", AFTER_SEX_MOMMY_AS_DOM) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_MOMMY_AS_SUB = new DialogueNode("结束", "租赁妈咪真是物有所值，性爱结束了……", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getNumberOfOrgasms(getMommy()) >= getMommy().getOrgasmsBeforeSatisfied()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_ORGASMED"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_NO_ORGASM"));
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "AFTER_SEX_MOMMY_AS_SUB_END"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(index==1) {
				return new Response("离开", "感谢妈咪，然后告辞。", AFTER_SEX_MOMMY_AS_SUB) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
				};
			} else {
				return null;
			}
		}
	};
}
