package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantDialogue;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.3
 * @version 0.3.5.1
 * @author Innoxia
 */
public class CharactersPresentDialogue {

	public static String menuContent;
	public static String menuTitle;
	public static NPC characterViewed = null;
	
	private static NPC targetedCharacterForSex;
	private static NPC companionCharacter;
	
	public static void resetContent(GameCharacter characterViewed) {
		if(characterViewed==null) {
			CharactersPresentDialogue.characterViewed = Main.game.getCharactersPresent().get(0);
		} else {
			CharactersPresentDialogue.characterViewed = (NPC) characterViewed;
		}
		menuTitle = "在场角色 ("+Util.capitaliseSentence(CharactersPresentDialogue.characterViewed.getName(true))+")";
		menuContent = ((NPC) CharactersPresentDialogue.characterViewed).getCharacterInformationScreen(true);

		if(Main.game.getPlayer().hasCompanion(CharactersPresentDialogue.characterViewed)) {
			if(CharactersPresentDialogue.characterViewed.isSlave() && CharactersPresentDialogue.characterViewed.getOwner().isPlayer()) {
				SlaveDialogue.initDialogue((NPC) CharactersPresentDialogue.characterViewed, true);
			} else {
				OccupantDialogue.initDialogue((NPC) CharactersPresentDialogue.characterViewed, false, true);
			}
			CompanionManagement.initManagement(MENU, 2, CharactersPresentDialogue.characterViewed);
		}
		
//		Main.game.setActiveNPC(characterViewed);
		targetedCharacterForSex = (NPC) CharactersPresentDialogue.characterViewed;

		if(Main.game.getPlayer().getCompanions().size()>1) {
			companionCharacter = (NPC) Main.game.getPlayer().getMainCompanion();
			if(Objects.equals(getCompanionCharacter(), targetedCharacterForSex)) {
				companionCharacter = (NPC) Main.game.getPlayer().getCompanions().stream().filter((npc) -> !npc.equals(getCharacterViewed())).findFirst().get();
			}
		} else {
			companionCharacter = null;
		}
	}

	private static NPC getCharacterViewed() {
		return characterViewed;
	}
	
	private static NPC getTargetedCharacterForSex() {
		if(!Main.game.getCharactersPresent().contains(targetedCharacterForSex)) {
			targetedCharacterForSex = (NPC) CharactersPresentDialogue.characterViewed;
			if(Objects.equals(getCompanionCharacter(), targetedCharacterForSex)) {
				if(Main.game.getPlayer().getCompanions().size()>1) {
					companionCharacter = (NPC) Main.game.getPlayer().getCompanions().stream().filter((npc) -> !npc.equals(getCharacterViewed())).findFirst().get();
				}
			}
		}
		return targetedCharacterForSex;
	}
	
	private static NPC getCompanionCharacter() {
		if(!Main.game.getCharactersPresent().contains(companionCharacter)) {
			if(Main.game.getPlayer().getCompanions().size()>1) {
				companionCharacter = (NPC) Main.game.getPlayer().getMainCompanion();
				if(Objects.equals(companionCharacter, targetedCharacterForSex)) {
					companionCharacter = (NPC) Main.game.getPlayer().getCompanions().stream().filter((npc) -> !npc.equals(getCharacterViewed())).findFirst().get();
				}
			} else {
				companionCharacter = null;
			}
		}
		return companionCharacter;
	}

	private static String getTextFilePath() {
		if(targetedCharacterForSex.isRelatedTo(Main.game.getPlayer())) {
			return "characters/offspring/slave";
		} else {
			return "misc/slaveDialogue";
		}
	}
	
	public static final DialogueNode MENU = new DialogueNode("", "", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.CHARACTERS_PRESENT;
		}
		
		@Override
		public String getLabel() {
			return menuTitle;
		}

		@Override
		public String getContent() {
			return menuContent;
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getPlayer().hasCompanion(characterViewed)) {
				if(index == 0) {
					return "特性";
				} else if(index == 1) {
					return UtilText.parse("[style.colourSex(性交)]");
				} else if(index == 2) {
					return UtilText.parse("[style.colourCompanion(管理)]");
				}
				
			} else {
				if(index == 0) {
					return "特性";
				} else if(index == 1) {
					return "性技能";
				} else if(index == 2) {
					return "管理";
				}
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {

			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			Collections.sort(charactersPresent, (c1, c2) -> Main.game.getPlayer().hasCompanion(c1)?1:0);
			
			if(responseTab==0) {
				if (index == 0) {
					return new ResponseEffectsOnly("返回", "停止浏览在场角色并回到游戏。"){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							Main.mainController.openCharactersPresent();
						}
					};
					
				} else if (index <= charactersPresent.size()) {
					String title = "[npc.Name]";
					String description = "仔细地打量一下[npc.name]。";
					
					if(charactersPresent.get(index - 1).equals(characterViewed)) {
						if(!charactersPresent.get(index - 1).isRaceConcealed() || charactersPresent.get(index - 1).isPlayerKnowsName()) {
							title = "[style.colourDisabled([npc.Name])]";
							description = "你已经在看[npc.name]了！";
						}else {
							title = "[style.colourDisabled(未知人物)]";
							description = "你不知道这人长什么样！";
						}
							
						
					} else if(Main.game.getPlayer().hasCompanion(charactersPresent.get(index - 1))) {
						title = "[style.colourCompanion([npc.Name])]";
						description = "仔细地打量一下你的[style.colourCompanion(同伴)]，[npc.name]。";
					}
					
					return new Response(
							UtilText.parse(charactersPresent.get(index - 1), title),
							UtilText.parse(charactersPresent.get(index - 1), description),
							charactersPresent.get(index - 1).equals(characterViewed)?null:MENU) {
						@Override
						public void effects() {
							characterViewed = charactersPresent.get(index-1);
							menuTitle = "在场角色 ("+Util.capitaliseSentence(charactersPresent.get(index - 1).getName(true))+")";
							menuContent = ((NPC) charactersPresent.get(index - 1)).getCharacterInformationScreen(true);
						}
					};
					
				} else {
					return null;
				}
				
			} else if (responseTab==1 && Main.game.getPlayer().hasCompanion(characterViewed)) {
				if (index == 0) {
					return new ResponseEffectsOnly("返回", "停止浏览在场角色并回到游戏。"){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							Main.mainController.openCharactersPresent();
						}
					};
				} 
				if(index>0 && index<5 && !characterViewed.isCompanionAvailableForSex(true)) {
					if(index==1) {
						return new Response("性交", characterViewed.getCompanionSexRejectionReason(true), null);
					}
					return null;
				}
				if(index>5 && index<10 && !characterViewed.isCompanionAvailableForSex(false)) {
					if(index==6) {
						return new Response("服从式性爱", characterViewed.getCompanionSexRejectionReason(false), null);
					}
					return null;
				}
				if(characterViewed.isSlave() && characterViewed.getOwner().isPlayer()) {
					return SlaveDialogue.SLAVE_START.getResponse(responseTab, index);
				} else {
					return OccupantDialogue.OCCUPANT_START.getResponse(responseTab, index);
				}
				
			} else if(responseTab==2 && Main.game.getPlayer().hasCompanion(characterViewed)) {
				return CompanionManagement.getManagementResponses(index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("离开", "", true) {
		
		@Override
		public String getDescription(){
			return "你已经爽过了，离开这里让[npc.name]恢复一下吧。";
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().size()>2) {
				List<GameCharacter> parsingCharacters = new ArrayList<>(Main.sex.getAllParticipants());
				parsingCharacters.remove(Main.game.getPlayer());
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_THREESOME", parsingCharacters);
				
			} else if(Main.sex.getNumberOfOrgasms(getCharacterViewed()) >= getCharacterViewed().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX", getTargetedCharacterForSex());
				
			} else {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_NO_ORGASM", getTargetedCharacterForSex());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "决定接下来该做什么。", AFTER_SEX) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.setActiveNPC(null);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	

	public static final DialogueNode PERKS = new DialogueNode("", "", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.CHARACTERS_PRESENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(getCharacterViewed(), "[npc.NamePos]的天赋树");
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parse(characterViewed,
					"<details>"
							+ "<summary>[style.boldPerk(天赋与特性信息)]</summary>"
							+ "[style.colourPerk(天赋)](圆形图标)对于[npc.namePos]的属性有永久提升。<br/>"
							+ "[style.colourPerk(特性)](方形图标)为[npc.Name]提供特殊效果。"
								+ "与天赋不同，<b>特性在加入“生效特性”栏之前不会有任何效果</b>。<br/>"
							+ "天赋需需要天赋点数解锁。[npc.Name]每当升级时便会获得一点天赋点数，并且每五级获得额外两点天赋点数。<br/><br/>"
							+ "除了这些通过天赋点解锁的天赋以外，还存在着一些通过特殊事件解锁的特殊隐藏天赋。"
					+ "</details>"));
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(characterViewed, true));
			
			UtilText.nodeContentSB.append("</div>");
			
			if(!characterViewed.isElemental() && !characterViewed.isDoll()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='padding:8px; text-align:center;'>"
							+ "<i>请注意此天赋树仍在施工，并非最终版本，仅用于展示概念！</i>"
						+ "</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 6) {
				return new Response("天赋树", UtilText.parse(characterViewed, "你正在分配天赋点数。"), null);
				
			} else if(index==7) {
				return new Response("重置天赋", "重置[npc.namePos]所有的天赋和特性，退回所有消耗的天赋点。(临时可用，由于天赋树仍在开发中)", PERKS) {
					@Override
					public void effects() {
						characterViewed.resetPerksMap(false, false);
					}
				};
			}
			
			return MENU.getResponse(responseTab, index);
		}
	};
}
