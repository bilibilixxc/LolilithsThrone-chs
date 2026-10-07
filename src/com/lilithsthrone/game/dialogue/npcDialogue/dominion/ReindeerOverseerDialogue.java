package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.96
 * @version 0.3.9
 * @author Innoxia
 */
public class ReindeerOverseerDialogue {
	
	private static NPC getReindeer() {
		return Main.game.getActiveNPC();
	}
	
	private static Response getDefaultResponses(int index) {
		if(index == 1) {
			return new ResponseTrade("交易",
					UtilText.parse(getReindeer(), "问问[npc.name]都卖什么圣诞礼物。"),
					getReindeer()) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().addReindeerEncountered(getReindeer().getId());
				}
			};
			
		} else if(index == 2) {
			if(Main.game.getDialogueFlags().hasWorkedForReindeer(getReindeer().getId())) {
				return new Response("工作",
						UtilText.parse(getReindeer(), "你今天已经帮[npc.name]完成了所有工作，如果还想帮忙就等到明天再来。"),
						null);
				
			} else {
				return new Response("工作", "主动提出帮驯鹿化形工作。", ENCOUNTER_WORK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().addReindeerEncountered(getReindeer().getId());
						Main.game.getDialogueFlags().addReindeerDailyWorkedFor(getReindeer().getId());
					}
				};
			}
			
		} else if(index == 3) {
			if(!Main.game.getDialogueFlags().hasWorkedForReindeer(getReindeer().getId())) {
				return new Response("缓解压力",
						UtilText.parse(getReindeer(), "[npc.Name]忙着手头上的工作。如果你能先帮一把，[npc.she]或许就有事件跟你做了……"),
						null);
				
			} else if(!getReindeer().isAttractedTo(Main.game.getPlayer())) {
				return new Response("缓解压力",
						UtilText.parse(getReindeer(), "[npc.Name]并没有被你吸引，因而[npc.sheIs]也不愿意跟你做爱……"),
						null);
				
			} else {
				return new ResponseSex("缓解压力",
							UtilText.parse(getReindeer(), "询问[npc.name]愿不愿意跟你宣泄一番。"),
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getReindeer()),
							null,
							null) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
							},
							AFTER_SEX,
							UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "SEX_START")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().addReindeerEncountered(getReindeer().getId());
						}
					};
			}
			
		} else if(index==0) {
			return new Response("离开",
					UtilText.parse(getReindeer(), "告诉[npc.name]你可能之后再回来，然后离开。"),
					ENCOUNTER_START){
				@Override
				public void effects() {
					Main.game.getDialogueFlags().addReindeerEncountered(getReindeer().getId());
				}
				@Override
				public DialogueNode getNextDialogue(){
					return Main.game.getDefaultDialogue(false);
				}
			};
		}
		return null;
	}
	
	public static final DialogueNode ENCOUNTER_START = new DialogueNode("驯鹿监工", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_START", getReindeer());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getDefaultResponses(index);
		}
	};
	
	public static final DialogueNode ENCOUNTER_WORK = new DialogueNode("驯鹿监工", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK", getReindeer());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("铲雪",
						UtilText.parse(getReindeer(), "告诉[npc.name]你可以跟其他工人一起铲雪。"
								+ "<br/>表现和报酬基于你的[style.italicsStrength("+Attribute.MAJOR_PHYSIQUE.getName()+")]。"),
						ENCOUNTER_WORK_FINISHED) {
					@Override
					public void effects() {
						int money = 100 + (int)(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)*1.5f);
						UtilText.addSpecialParsingString(Util.intToString(money), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK_SHOVEL_SNOW", getReindeer()));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(money));
					}
				};
				
			} else if(index==2) {
				return new Response("使用放热板",
						UtilText.parse(getReindeer(), "告诉[npc.name]你可以拿一块放热板，一行一行地除雪。"
								+ "<br/>表现和报酬基于你的[style.italicsIntelligence("+Attribute.MAJOR_ARCANE.getName()+")]。"),
						ENCOUNTER_WORK_FINISHED) {
					@Override
					public void effects() {
						int money = 100 + (int)(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)*1.5f);
						UtilText.addSpecialParsingString(Util.intToString(money), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK_HEAT_STAVE", getReindeer()));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(money));
					}
				};
				
			} else if(index==3) {
				return new Response("“激励”",
						UtilText.parse(getReindeer(), "告诉[npc.name]你最适合递送饮料和“激励”工人。"
								+ "<br/>表现和报酬基于你的[style.italicsCorruption("+Attribute.MAJOR_CORRUPTION.getName()+")]。"),
						ENCOUNTER_WORK_FINISHED) {
					@Override
					public void effects() {
						int money = 100 + (int)(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION)*1.5f);
						UtilText.addSpecialParsingString(Util.intToString(money), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK_ENCOURAGEMENT", getReindeer()));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(money));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENCOUNTER_WORK_FINISHED = new DialogueNode("驯鹿监工", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60 * 4*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK_FINISHED", getReindeer());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getDefaultResponses(index);
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("结束", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getReindeer(), "[npc.Name]已经爽够了，性爱就此结束……");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "AFTER_SEX", getReindeer());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getDefaultResponses(index);
		}
	};
}
