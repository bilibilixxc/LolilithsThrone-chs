package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionary;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionarySealed;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMCultistKneeling;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.88
 * @version 0.3.7.9
 * @author Innoxia
 */
public class CultistDialogue {

	private static NPC getCultist() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNode ENCOUNTER_START = new DialogueNode("魔女出现！", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_START", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "找个借口摆脱这个讨厌的异教徒。", ENCOUNTER_START) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_START_LEAVE", getCultist()));
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else if(index==2) {
				return new Response("小教堂", "按照狂信者的要求，跟着她走到附近的小巷子里。会出什么问题呢？", ENCOUNTER_CHAPEL) {
					@Override
					public void effects() {
						// Pull up dress:
						getCultist().displaceClothingForAccess(CoverableArea.PENIS, null);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL = new DialogueNode("魔女的教堂", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("战斗", "你别无选择，只能战斗！", getCultist(), Util.newHashMapOfValues(
						new Value<>(Main.game.getPlayer(), "你告诉魅魔你不感兴趣，正如你所料，她开始攻击了！"),
						new Value<>(getCultist(), "[npc.Name]拿起扫帚，大喊一声，[npc.speech(你<i>竟敢</i>拒绝我的馈赠！那我就要硬塞给你！)]")));
				
			} else if(index==2) {
				return new ResponseSex("接受", "你双膝跪地，准备给她口交。",
						true, true,
						new SMCultistKneeling(
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.KNEELING_RECEIVING_ORAL_CULTIST)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.KNEELING_PERFORMING_ORAL_CULTIST))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_ORAL_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_ORAL_SEX", getCultist())) {
					@Override
					public void effects() {
						// Remove seals so that player can get access to mouth:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.MOUTH, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.MOUTH, true);
							}
						}
					}
				};
				
			} else if(index == 3) {
				if(Main.game.getPlayer().hasVagina()) {
					return new ResponseSex("献上小穴", "作为替代，给[npc.name]献上你的小穴。", Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY),
							null, Fetish.FETISH_PREGNANCY.getAssociatedCorruptionLevel(), null, null, null,
							true, false,
							new SMAltarMissionary(
									Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
							},
							null,
							null,
							ENCOUNTER_CHAPEL_POST_VAGINAL_SEX,
							UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_VAGINAL_SEX", getCultist())) {
						@Override
						public void effects() {
							((Cultist)getCultist()).setRequestedAnal(false);
							
							// Remove seals so that player can get access to vagina:
							if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
								while (clothing != null) {
									clothing.setSealed(false);
									System.out.println(clothing.getName());
									clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
								}
							}
						}
					};
					
				} else {
					return new Response("献上小穴", "你需要拥有阴道，才能献给[npc.name]……", null);
				}
				
			} else if(index==4) {
				return new ResponseSex("献上屁眼", "作为替代，给[npc.name]献上你的屁眼。", Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING),
						null, Fetish.FETISH_ANAL_RECEIVING.getAssociatedCorruptionLevel(), null, null, null,
						true, false,
						new SMAltarMissionary(
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_ANAL_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_ANAL_SEX", getCultist())) {
					@Override
					public void effects() {
						((Cultist)getCultist()).setRequestedAnal(true);
						
						// Remove seals so that player can get access to vagina:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							}
						}
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_REPEAT = new DialogueNode("魔女的教堂", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Pull up dress:
			getCultist().displaceClothingForAccess(CoverableArea.PENIS, null);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_REPEAT", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENCOUNTER_CHAPEL.getResponse(0, index);
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_LEAVING = new DialogueNode("魔女的教堂", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_LEAVING", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "离开教堂，回到御城区街头。", ENCOUNTER_CHAPEL_LEAVING) {
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
			
			} else if(index==10) {
				return new Response(
						"移除角色",
						"赶[npc.name]走"
							+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]",
						ENCOUNTER_CHAPEL_LEAVING) {
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
						Main.game.banishNPC(getCultist());
					}
				};
			
			} else {
				return null;
			}
		}
		
		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_COMBAT_VICTORY = new DialogueNode("魔女的教堂", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_VICTORY", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "转身走向门口。", ENCOUNTER_CHAPEL_LEAVING) {
					@Override
					public void effects(){
						Colour colour = PresetColour.CLOTHING_BLACK;
						if(getCultist().getClothingInSlot(InventorySlot.TORSO_UNDER)!=null && getCultist().getClothingInSlot(InventorySlot.TORSO_UNDER).getColour(0)==PresetColour.CLOTHING_WHITE) {
							 colour = PresetColour.CLOTHING_WHITE;
						}
						
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", colour, PresetColour.CLOTHING_GOLD, colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", colour, PresetColour.CLOTHING_GOLD, colour, false));
						Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
					}
				};
			
			} else if(index==2) {
				return new ResponseSex("性爱", "决定不用[npc.namePos]的扫帚封印住[npc.herHim]，而是来一场普通的支配型性爱……",
						true, false,
						new SMAltarMissionary(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_DOM_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_VICTORY_SEX", getCultist())) {
				};
			
			} else if(index == 3) {
				return new ResponseSex("魔女封锁术", "用她的扫把，在她身上施放魔女的封锁。",
						false, false,
						new SMAltarMissionarySealed(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
								Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
								map.put(ImmobilisationType.WITCH_SEAL, Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Util.newHashSetOfValues(getCultist()))));
								return map;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_VICTORY_BROOMSTICK_SEAL_SEX", getCultist())) {
				};
				
			} else if (index == 4) {
				return new Response("完全转化",
						"仔细观察[npc.name]会将自己转化成什么样……",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getCultist());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_COMBAT_LOSS = new DialogueNode("魔女的教堂", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_LOSS", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("魔女的玩具", "你完全无法行动，只得眼睁睁地看着魔女将你用作玩具。",
						false, false,
						new SMAltarMissionarySealed(
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
								Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
								map.put(ImmobilisationType.WITCH_SEAL, Util.newHashMapOfValues(new Value<>(getCultist(), Util.newHashSetOfValues(Main.game.getPlayer()))));
								return map;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_LOSS_SEX", getCultist())) {
					@Override
					public void effects() {
						((Cultist)getCultist()).setRequestedAnal(false);
						
						// Remove seals so that player can get access to vagina:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
							}
						}
						// Remove seals so that player can get access to anus:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							}
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_ORAL_SEX = new DialogueNode("性交之后", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "[npc.Name]已经让你口交爽过了……");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_ORAL_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "转身走向门口。", ENCOUNTER_CHAPEL_POST_ORAL_SEX) {
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
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_VAGINAL_SEX = new DialogueNode("性交之后", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "[npc.Name]已经操你的小穴爽过了……");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_VAGINAL_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "转身走向门口。", ENCOUNTER_CHAPEL_POST_VAGINAL_SEX) {
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
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_ANAL_SEX = new DialogueNode("性交之后", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "[npc.Name]已经操你的屁股爽过了……");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_ANAL_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续你的旅程。", ENCOUNTER_CHAPEL_POST_VAGINAL_SEX){
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
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX = new DialogueNode("性交之后", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "[npc.Name]已经操够你了……");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续你的旅程。", ENCOUNTER_CHAPEL_POST_ORAL_SEX){
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
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_DOM_SEX = new DialogueNode("性交之后", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "你操够[npc.Name]了……");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_DOM_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "转身走向门口。", ENCOUNTER_CHAPEL_LEAVING) {
					@Override
					public void effects(){
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX = new DialogueNode("性交之后", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "转身走向门口。", ENCOUNTER_CHAPEL_LEAVING) {
					@Override
					public void effects(){
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
