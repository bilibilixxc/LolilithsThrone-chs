package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.managers.universal.SMMasturbation;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.5
 * @version 0.4.4.1
 * @author Innoxia
 */
public class LilayasRoom {
	
	public static AbstractClothing lilayasPanties;
	
	public static final DialogueNode ROOM_LILAYA = new DialogueNode("莉莱雅的房间", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "EXTERIOR");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			if (index == 1) {
				if(!Main.game.isExtendedWorkTime()) {
					return new Response("莉莱雅的房间", "房门现在紧锁着……", null);
				}
				return new Response("莉莱雅的房间", "看看莉莱雅的房间。", ROOM_LILAYA_INSIDE);

			}  else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROOM_LILAYA_INSIDE = new DialogueNode("莉莱雅的房间", "。", true) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "INTERIOR_ENTRY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("离开", "退回到转角处。", ROOM_LILAYA);

			} else if (index == 1) {
				return new Response("内裤", "看看莉莱雅的那堆内裤。", PANTIES,
						Util.newArrayListOfValues(Fetish.FETISH_INCEST), CorruptionLevel.TWO_HORNY,
						null, null, null) {
					@Override
					public void effects() {
						List<AbstractClothingType> panties = new ArrayList<>();
						panties.add(ClothingType.getClothingTypeFromId("innoxia_groin_lacy_panties"));
						panties.add(ClothingType.getClothingTypeFromId("innoxia_groin_panties"));
						panties.add(ClothingType.getClothingTypeFromId("innoxia_groin_shimapan"));
						panties.add(ClothingType.getClothingTypeFromId("innoxia_groin_crotchless_panties"));
						
						lilayasPanties = Main.game.getItemGen().generateClothing(panties.get(Util.random.nextInt(panties.size())), false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PANTIES = new DialogueNode("莉莱雅的房间", "。", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "PANTIES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 0) {
				return new Response("Leave", "退回到转角处。", ROOM_LILAYA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "PANTIES_EXIT"));
					}
				};

			} else if (index == 1) {
				return new ResponseSex("拿内裤自慰", "用莉莱雅的内裤作为自慰材料。",
						true, true,
						new SMMasturbation(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMasturbation.KNEELING_PANTIES))) {
							@Override
							public String applyEndSexEffects() {
								return Main.game.getPlayer().addClothing(LilayasRoom.lilayasPanties, 1, false, true);
							}
						},
						null,
						null, PANTIES_POST_MASTURBATION, UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "PANTIES_MASTURBATION"));

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PANTIES_POST_MASTURBATION = new DialogueNode("结束", "你结束自慰时，不禁在想接下来该拿莉莱雅的内裤做什么……", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "PANTIES_POST_MASTURBATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("藏起", "快速藏到莉莱雅的床下，顺道拿上内裤。", HIDE);

			} else if (index == 2) {
				return new Response("逃走", "迅速寻找出路，顺道拿上内裤。", FLEE);

			} else if (index == 3) {
				return new Response("享受内裤", "萝丝肯定不会来这的……你觉得就这样继续享受莉莱雅的内裤不会出事。", CAUGHT) {
					@Override
					public void effects() {
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_LILAYA);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), -25));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HIDE = new DialogueNode("莉莱雅的房间", "。", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "HIDE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "萝丝现在已经离开了，你可以安全的回到走廊里了。", ROOM_LILAYA);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode FLEE = new DialogueNode("莉莱雅的房间", "。", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "FLEE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("你的房间", "回到走廊里，前往你的房间。") {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CAUGHT = new DialogueNode("莉莱雅的房间", "。", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "CAUGHT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("道歉", "对萝丝道歉。", APOLOGY) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), 10));
					}
				};
					
			} else if(index==2) {
				return new Response("威胁", "威胁萝丝，告诉她要是敢跟莉莱雅说这事，她肯定会后悔的。", THREATEN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), -25));
					}
				};

			} else if (index == 3) {
				return new ResponseEffectsOnly("离开", "随便扯点借口，从萝丝身旁冲走，回到你的房间。") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "CAUGHT_FLEE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode APOLOGY = new DialogueNode("莉莱雅的房间", "。", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "APOLOGY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("恳求", "求求萝丝不要告诉莉莱雅。", BEG) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), 15));
					}
				};
					
			} else if (index == 2) {
				return new ResponseEffectsOnly("离开", "让萝丝不要把事情说给莉莱雅，然后冲回自己的房间里。") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "APOLOGY_FLEE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BEG = new DialogueNode("莉莱雅的房间", "。", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "BEG");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("屈服", "放任萝丝将你推回床上上你",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
						null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Rose.class), SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public  boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								return !character.isPlayer();
							}
							@Override
							public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
								return Util.newHashMapOfValues(
										new Value<>(
												Main.game.getNpc(Rose.class),
												Util.newArrayListOfValues(
														SexAreaOrifice.VAGINA,
														SexAreaOrifice.ANUS,
														SexAreaOrifice.MOUTH)));
							}
						},
						null,
						null,
						AFTER_ROSE_AS_DOM,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "ROSE_AS_DOM")){
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), 15));
						if(Main.game.getNpc(Rose.class).getClothingInSlot(InventorySlot.GROIN)!=null) {
							Main.game.getNpc(Rose.class).unequipClothingIntoVoid(Main.game.getNpc(Rose.class).getClothingInSlot(InventorySlot.GROIN), true, Main.game.getNpc(Rose.class));
						}
						Main.game.getNpc(Rose.class).displaceClothingForAccess(CoverableArea.PENIS, null);
						Main.game.getNpc(Rose.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_penis_strapon", PresetColour.CLOTHING_PURPLE_DARK, false), true, Main.game.getNpc(Rose.class));
					}
				};
					
			} else if (index == 2) {
				return new ResponseEffectsOnly("拒绝", "回绝萝丝的提议，冲回你自己的房间。") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "BEG_FLEE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_ROSE_AS_DOM = new DialogueNode("结束", "她感觉已经“惩罚”够你了，便决定结束这场性爱。", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "AFTER_ROSE_AS_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("离开", "匆匆回到你的房间内。") {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(Rose.class).equipClothing();
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode THREATEN = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "THREATEN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("等待", "按萝丝说的做，等莉莱雅回到房间来跟你当面对质。", THREATEN_WAIT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, false);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_LILAYA);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("逃跑", "你冲回了自己的房间，没等莉莱雅来对峙。") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "THREATEN_FLEE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode THREATEN_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "THREATEN_WAIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("屈服", "你按莉莱雅说的做，屈服并向萝丝道歉，让她决定该如何惩罚你……", THREATEN_SUBMIT) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_SEX;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, false);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), 25));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
					
			}
			//TODO needs more options to allow player to fuck Rose?
//			else if (index == 2) {
//				return new Response("Dominate",
//						"Without apologising, dominantly tell Lilaya and Rose that you did what you did because you were feeling particularly horny, and that now that they're here, they should help you to get some relief.",
//						DOMINATE,
//						Util.newArrayListOfValues(Fetish.FETISH_DOMINANT),
//						null, null, null, null) {
//					@Override
//					public Colour getHighlightColour() {
//						return PresetColour.GENERIC_SEX_AS_DOM;
//					}
//				};
//				
//			}
			else if (index == 2) {
				return new ResponseEffectsOnly("道歉", "你并不愿面对莉莱雅的怒火，迅速地道了歉，冲回了自己的房间。") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, false);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Rose.class).incrementAffection(Main.game.getPlayer(), 15));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "THREATEN_APOLOGISE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			}
			return null;
		}
	};
	
	public static final DialogueNode THREATEN_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "THREATEN_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("莉莱雅的小穴", "跟萝丝说你很喜欢莉莱雅小穴的味道。",
						true, false,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Rose.class), SexSlotAllFours.BEHIND),
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotAllFours.IN_FRONT)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								return partner.equals(Main.game.getNpc(Rose.class)) && Main.sex.isSatisfiedFromOrgasms(Main.game.getNpc(Rose.class), true) && Main.sex.isSatisfiedFromOrgasms(Main.game.getNpc(Lilaya.class), true);
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(targetedCharacter.isPlayer()) {
									return getMainSexPreference(character, targetedCharacter);
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.equals(Main.game.getNpc(Rose.class)) && targetedCharacter.isPlayer()) {
									if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
									} else if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
									}
								}
								if(character.equals(Main.game.getNpc(Lilaya.class)) && targetedCharacter.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
								}
								return super.getMainSexPreference(character, targetedCharacter);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public  boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								return !character.isPlayer();
							}
							@Override
							public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
								return Util.newHashMapOfValues(
										new Value<>(
												Main.game.getNpc(Rose.class),
												Util.newArrayListOfValues(
														SexAreaOrifice.VAGINA,
														SexAreaOrifice.ANUS,
														SexAreaOrifice.MOUTH)));
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.equals(Main.game.getNpc(Lilaya.class));
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								Map<GameCharacter, List<CoverableArea>> exposeMap = new HashMap<>();
								
								if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
									exposeMap.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
								}
								if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
									exposeMap.putIfAbsent(Main.game.getPlayer(), new ArrayList<>());
									exposeMap.get(Main.game.getPlayer()).add(CoverableArea.VAGINA);
								} else if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
									exposeMap.putIfAbsent(Main.game.getPlayer(), new ArrayList<>());
									exposeMap.get(Main.game.getPlayer()).add(CoverableArea.ANUS);
								}
								
								return exposeMap;
							}
						},
						null,
						null,
						AFTER_LILAYA_AND_ROSE_AS_DOMS,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "LILAYA_AND_ROSE_AS_DOMS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
									?new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), TongueVagina.CUNNILINGUS_START, false, true)
									:null,
								Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
									?new InitialSexActionInformation(Main.game.getNpc(Rose.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true)
									:(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
										?new InitialSexActionInformation(Main.game.getNpc(Rose.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true)
										:null));
					}
					@Override
					public void effects() {
						if(Main.game.getNpc(Rose.class).getClothingInSlot(InventorySlot.GROIN)!=null) {
							Main.game.getNpc(Rose.class).unequipClothingIntoVoid(Main.game.getNpc(Rose.class).getClothingInSlot(InventorySlot.GROIN), true, Main.game.getNpc(Rose.class));
						}
						Main.game.getNpc(Rose.class).displaceClothingForAccess(CoverableArea.PENIS, null);
						Main.game.getNpc(Rose.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_penis_strapon", PresetColour.CLOTHING_PURPLE_DARK, false), true, Main.game.getNpc(Rose.class));
					}
				};
					
			} else if(index==2) {
				return new ResponseSex("莉莱雅的阴茎", "跟萝丝说你很想让莉莱雅长根家伙让你尝尝。",
						true, false,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Rose.class), SexSlotAllFours.BEHIND),
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotAllFours.IN_FRONT)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								return partner.equals(Main.game.getNpc(Rose.class)) && Main.sex.isSatisfiedFromOrgasms(Main.game.getNpc(Rose.class), true) && Main.sex.isSatisfiedFromOrgasms(Main.game.getNpc(Lilaya.class), true);
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(targetedCharacter.isPlayer()) {
									return getMainSexPreference(character, targetedCharacter);
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.equals(Main.game.getNpc(Rose.class)) && targetedCharacter.isPlayer()) {
									if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
									} else if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
									}
								}
								if(character.equals(Main.game.getNpc(Lilaya.class)) && targetedCharacter.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
								}
								return super.getMainSexPreference(character, targetedCharacter);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public  boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								return !character.isPlayer();
							}
							@Override
							public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
								return Util.newHashMapOfValues(
										new Value<>(
												Main.game.getNpc(Rose.class),
												Util.newArrayListOfValues(
														SexAreaOrifice.VAGINA,
														SexAreaOrifice.ANUS,
														SexAreaOrifice.MOUTH)));
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.equals(Main.game.getNpc(Lilaya.class));
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								Map<GameCharacter, List<CoverableArea>> exposeMap = new HashMap<>();
								
								if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
									exposeMap.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
								}
								if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
									exposeMap.putIfAbsent(Main.game.getPlayer(), new ArrayList<>());
									exposeMap.get(Main.game.getPlayer()).add(CoverableArea.VAGINA);
								} else if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
									exposeMap.putIfAbsent(Main.game.getPlayer(), new ArrayList<>());
									exposeMap.get(Main.game.getPlayer()).add(CoverableArea.ANUS);
								}
								
								return exposeMap;
							}
						},
						null,
						null,
						AFTER_LILAYA_AND_ROSE_AS_DOMS,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "LILAYA_AND_ROSE_AS_DOMS_PENIS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
									?new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), PenisMouth.GIVING_BLOWJOB_START, false, true)
									:null,
								Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
									?new InitialSexActionInformation(Main.game.getNpc(Rose.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true)
									:(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
										?new InitialSexActionInformation(Main.game.getNpc(Rose.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true)
										:null));
					}
					@Override
					public void effects() {
						Main.game.getNpc(Lilaya.class).setVaginaType(VaginaType.NONE);
						((Lilaya)Main.game.getNpc(Lilaya.class)).growCock();
						
						if(Main.game.getNpc(Rose.class).getClothingInSlot(InventorySlot.GROIN)!=null) {
							Main.game.getNpc(Rose.class).unequipClothingIntoVoid(Main.game.getNpc(Rose.class).getClothingInSlot(InventorySlot.GROIN), true, Main.game.getNpc(Rose.class));
						}
						Main.game.getNpc(Rose.class).displaceClothingForAccess(CoverableArea.PENIS, null);
						Main.game.getNpc(Rose.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_penis_strapon", PresetColour.CLOTHING_PURPLE_DARK, false), true, Main.game.getNpc(Rose.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_LILAYA_AND_ROSE_AS_DOMS = new DialogueNode("结束", "萝丝觉得对你的“惩罚”已经足够了，便结束了这场性爱。", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "AFTER_LILAYA_AND_ROSE_AS_DOMS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("离开", "匆匆回到你的房间内。") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "AFTER_LILAYA_AND_ROSE_AS_DOMS_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode DOMINATE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "DOMINATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("操莉莱雅", "与萝丝和莉莱雅做爱，作支配位。",
						true, false,
						new SMAllFours(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lilaya.class), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
						},
						Util.newArrayListOfValues(Main.game.getNpc(Rose.class)),
						null,
						AFTER_LILAYA_AS_SUB,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "LILAYA_AS_SUB_START"));
					
			} else if (index == 2) {
				return new ResponseEffectsOnly("离开", "回绝萝丝的提议，回到你的房间里。") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "DOMINATE_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_LILAYA_AS_SUB = new DialogueNode("结束", "你已经跟莉莱雅享乐够了，决定结束这场性爱。", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "AFTER_ROSE_AND_LILAYA_AS_SUBS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("离开", "回到你的房间。") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayasRoom", "AFTER_ROSE_AND_LILAYA_AS_SUBS_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.setContent(new Response("", "", Main.game.getPlayerCell().getDialogue(false)));
					}
				};
			}
			return null;
		}
	};
}
