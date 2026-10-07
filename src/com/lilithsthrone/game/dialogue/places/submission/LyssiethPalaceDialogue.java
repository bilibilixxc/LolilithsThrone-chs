package com.lilithsthrone.game.dialogue.places.submission;

import java.util.List;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMLilayaDemonTF;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethDemonTF;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.game.sex.sexActions.submission.SALyssiethSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3
 * @version 0.3.9.9
 * @author Innoxia
 */
public class LyssiethPalaceDialogue {
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ENTRANCE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开",
						"离开莉西丝的宫殿，回到屈城区。",
						!Main.game.getDialogueFlags().hasFlag("innoxia_elizabeth_routine_started")
							?ENTRANCE_LEAVING_FIRST_TIME
							:ENTRANCE_LEAVING) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag("innoxia_elizabeth_routine_started")) {
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_GATE);
							
						} else {
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_GATE);
							Main.game.getPlayer().setNearestLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_CAVERN, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ENTRANCE_LEAVING"));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE_LEAVING_FIRST_TIME = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ENTRANCE_LEAVING_FIRST_TIME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续",
						"和伊丽莎白道别，继续往前走……",
						PlaceType.SUBMISSION_LILIN_PALACE_CAVERN.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag("innoxia_elizabeth_routine_started", true);
						Main.game.getPlayer().setNearestLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_CAVERN, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ENTRANCE_LEAVING_FIRST_TIME_END"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE_LEAVING = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.SUBMISSION_LILIN_PALACE_CAVERN.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("", "", false) {
		@Override
		public boolean isTravelDisabled() {
			
			return Main.game.getNpc(Elizabeth.class).getWorldLocation()==WorldType.LYSSIETH_PALACE && Main.game.getNpc(Elizabeth.class).getLocation().getDistanceToVector(Main.game.getPlayer().getLocation())<=1;
		}
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getContent() {
			if(Main.game.getNpc(Elizabeth.class).getWorldLocation()==WorldType.LYSSIETH_PALACE && Main.game.getNpc(Elizabeth.class).getLocation().getDistanceToVector(Main.game.getPlayer().getLocation())<=1) {
				return UtilText.parseFromXMLFile("acexp/submission/elizabeth", "INTRO");
			}
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Elizabeth.class).getWorldLocation()==WorldType.LYSSIETH_PALACE && Main.game.getNpc(Elizabeth.class).getLocation().getDistanceToVector(Main.game.getPlayer().getLocation())<=1) {
				return DialogueManager.getDialogueFromId("acexp_submission_palace_elizabeth").getResponse(responseTab, index);
			}
			return null;
		}
	};

	public static final DialogueNode WINDOWS = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "WINDOWS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode HALL = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "HALL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode STAIRCASE = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "STAIRCASE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode SIREN_OFFICE = new DialogueNode("", "", true) {

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(DarkSiren.class))) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE"));
				
				if(Main.game.getPlayer().hasCompanions()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_COMPANION", Main.game.getPlayer().getMainCompanion()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_NO_COMPANION"));
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_MERAXIS_ABSENT"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进入", "敲门并进入莉西丝的办公室。", LYSSIETH_OFFICE_ENTER) {
					@Override
					public void effects() {
						conversationIndex = 0;
						if(Main.game.getCharactersPresent().contains(Main.game.getNpc(DarkSiren.class))) {
							Main.game.getNpc(DarkSiren.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						if(Main.game.getPlayer().hasCompanions()) {
							for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
								companion.setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE, false);
							}
						}
					}
				};
				
			} else if(index==6
					&& Main.game.getCharactersPresent().contains(Main.game.getNpc(DarkSiren.class))
					&& Main.game.getNpc(Lilaya.class).getRaceStage()==RaceStage.GREATER
					&& Main.game.getNpc(DarkSiren.class).getRaceStage()!=RaceStage.GREATER) {
				return new Response("恶魔",
						"告诉梅拉克西丝，她应该向完全的恶魔转化。<br/>[style.italicsDemon(这将永久使梅拉克西丝转化为完全的恶魔！)]",
						MERAXIS_DEMON_TF_START) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.RACE_DEMON;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_START"));
					}
				};
				
			} else if(index==0) {
				return new Response("离开", "走出办公室。", PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false)) {
						@Override
						public void effects() {
							if(Main.game.getCharactersPresent().contains(Main.game.getNpc(DarkSiren.class))) {
								Main.game.getNpc(DarkSiren.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode MERAXIS_DEMON_TF_START = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.meraxisRepeatDemonTF, true);
			Main.game.getNpc(DarkSiren.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
			
			Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
			Main.game.getNpc(Lilaya.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
			Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
			
			((Lyssieth)Main.game.getNpc(Lyssieth.class)).setLilinBody();
			
			if(Main.game.getPlayer().hasCompanions()) {
				for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
					companion.setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE, false);
				}
			}
			
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).addFetish(Fetish.FETISH_INCEST, true));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getNpc(Lyssieth.class), 75));
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_START_CORE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("准备好了", "梅拉克西丝自甘堕落沉迷乱伦后，已经准备好跟你们三个一起做爱，转化成恶魔了。", MERAXIS_DEMON_TF_ORIFICE_SELECTION);
				
			} else if(index==2) {
				return new Response("走到外面",
						"你不想和你的妈妈和姐妹做爱。于是走向外面，进入了梅拉克西丝的办公室，等待着他们三个做完。",
						MERAXIS_DEMON_TF_WAIT_IN_OFFICE){
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setDaughterToFullDemon(DarkSiren.class);
						Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						if(Main.game.isAnalContentEnabled()) {
							Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						} else {
							Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
						}
						Main.game.getNpc(Lyssieth.class).setStartingBody(false);
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MERAXIS_DEMON_TF_ORIFICE_SELECTION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_ORIFICE_SELECTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_LILAYA_DEMON_TF_SEX.getResponse(responseTab, index); // Orifice selection.
		}
	};
	
	public static final DialogueNode MERAXIS_DEMON_TF_WAIT_IN_OFFICE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_WAIT_IN_OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("恶魔梅拉克西丝",
						"梅拉克西丝走出来莉西丝的办公室，让你看看她的变化。",
						MERAXIS_DEMON_TF_WAIT_IN_OFFICE_FINISHED) {
						@Override
						public void effects() {
							Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode MERAXIS_DEMON_TF_WAIT_IN_OFFICE_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_WAIT_IN_OFFICE_FINISHED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("innoxia_meraxis_demon_tf_teleported"))) {
				if(index==1) {
					return new Response("传送",
							"你和梅拉克西丝传送回了伊利斯的红龙酒馆。",
							DialogueManager.getDialogueFromId("innoxia_places_fields_elis_tavern_f0_meraxis_post_demon_tf")) {
							@Override
							public void effects() {
								// Reset offspring to full demons:
								if(Main.game.getNpc(DarkSiren.class).isPregnant()) {
									for(OffspringSeed offspring : Main.game.getNpc(DarkSiren.class).getPregnantLitter().getOffspringSeed()) {
										offspring.setBody(offspring.getGender(), Main.game.getNpc(DarkSiren.class), Main.game.getNpc(DarkSiren.class).getPregnantLitter().getFather());
									}
									Main.game.getNpc(DarkSiren.class).getPregnantLitter().generateBirthedDescription();
								}
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
							}
						};
					
				} else {
					return null;
				}
				
			} else {
				return SIREN_OFFICE.getResponse(responseTab, index); // Standard actions.
			}
		}
	};

	private static void updateLyssiethPregnancyReactions() {
		Main.game.getNpc(Lyssieth.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Lyssieth.class), true);
	}
	
	private static int conversationIndex = 0;
	private static boolean dominantRepeatSex = true;
	
	public static final DialogueNode LYSSIETH_OFFICE_ENTER = new DialogueNode("", "", true) {

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().hasCompanions()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_ENTER_COMPANION", Main.game.getPlayer().getMainCompanion()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_ENTER"));
			}
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_ENTER_MAIN"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("露内特", conversationIndex==1?"你已经问过莉西丝她妹妹的事情了。":"询问莉西丝她妹妹露内特的事情。", conversationIndex==1?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 1;
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_LUNETTE"));
					}
				};
				
			} else if(index==2) {
				return new Response("家族", conversationIndex==2?"你已经问过莉西丝家族的事情了。":"询问莉西丝她女儿、姐妹和母亲的事情。", conversationIndex==2?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 2;
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_FAMILY"));
						Main.game.getTextEndStringBuilder().append(AbstractItemEffectType.getBookEffect(Main.game.getPlayer(), Subspecies.ELDER_LILIN, null, false));
						Main.game.getTextEndStringBuilder().append(AbstractItemEffectType.getBookEffect(Main.game.getPlayer(), Subspecies.LILIN, null, false));
					}
				};
				
			} else if(index==3) {
				return new Response("大天使", conversationIndex==3?"你已经问过莉西丝大天使的事情了。":"询问莉西丝大天使的事情。", conversationIndex==3?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 3;
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.statueTruthRevealed, true);
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_ANGELS"));
					}
				};
				
			} else if(index==4) {
				return new Response("人类", conversationIndex==4?"你已经问过莉西丝她为何如此痴迷于人类了。":"询问莉西丝为何如此痴迷于人类。", conversationIndex==4?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 4;
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_HUMANS"));
					}
				};
				
			} else if(index==5) {
				return new Response("回顾", conversationIndex==5?"你已经让莉西丝回顾过整个事件了，是如何将你原本的世界扭曲成了如今的样子"
						:"请莉西丝短暂回顾一下整个事件，是如何将你原本的世界扭曲成了如今的样子。", conversationIndex==5?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 5;
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_RECAP"));
					}
				};

			} else if(index==6) {
				return new Response("支配型性爱", "告诉莉西丝你想作为主导者上了她。", SEX_REPEAT_CHOICES) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_SEX_AS_DOM;
					}
					@Override
					public void effects() {
						dominantRepeatSex = true;
						conversationIndex = 0;
						updateLyssiethPregnancyReactions();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_REPEAT_SEX_DOM"));
					}
				};
				
			} else if(index==7) {
				return new Response("服从型性爱", "告诉莉西丝你想向她屈服，让她作为主导方上了你。", SEX_REPEAT_CHOICES) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_SEX;
					}
					@Override
					public void effects() {
						dominantRepeatSex = false;
						conversationIndex = 0;
						updateLyssiethPregnancyReactions();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_REPEAT_SEX_SUB"));
					}
				};
				
			} else if(index==8 && Main.game.getPlayer().getSubspeciesOverrideRace()!=Race.DEMON) {
				if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.CORRUPTION_PERK_5)) {
					return new Response("成为恶魔", "你还不够堕落，无法转变为恶魔……<br/>[style.italicsBad(需要腐化度至少达到95。)]", null);
					
				} else if(Main.game.getPlayer().getLegConfiguration()!=LegConfiguration.BIPEDAL) {
					return new Response("成为恶魔", "你的腿部需要时双足，才能让莉西丝将你变为恶魔……", null);
					
				} else {
					return new Response("成为恶魔", "告诉莉西丝你想让她将你变为恶魔。", DEMON_TF) {
						@Override
						public void effects() {
							conversationIndex = 0;
							updateLyssiethPregnancyReactions();
						}
					};
				}
				
			} else if(index==11) { // Teleport
				if(Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth") && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
					return new Response("莉莱雅的实验室", "莉西丝不愿意面对她女儿，除非你坦白自己已经是全恶魔。你必须自己走回实验室……", null);
				}

				if((Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaBirthNews))
						&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					return new Response("莉莱雅的实验室", "在莉莱雅孕期结束之前，莉西丝不愿意将你传送到她女儿的实验室……", null);
				}
				
				return new Response("莉莱雅的实验室", "请求莉西丝将你传送回莉莱雅的实验室。", LAB_TELEPORT) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
					}
				};
			}
			if(index==0) {
				return new Response("离开", "退出莉西丝的办公室。", SIREN_OFFICE_LEAVE) {
						@Override
						public void effects() {
							conversationIndex = 0;
							updateLyssiethPregnancyReactions();
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LYSSIETH_OFFICE_TALK = new DialogueNode("", "", true) {

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}

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
			return LYSSIETH_OFFICE_ENTER.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode LAB_TELEPORT = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasCompanions()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LAB_TELEPORT_COMPANION", Main.game.getPlayer().getMainCompanion());
			}
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LAB_TELEPORT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Lab.LAB_ENTRY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SIREN_OFFICE_LEAVE = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.game.getNpc(DarkSiren.class).getHomeWorldLocation()==WorldType.LYSSIETH_PALACE) {
				if(Main.game.getPlayer().hasCompanions()) {
					return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_LEAVE_COMPANION", Main.game.getPlayer().getMainCompanion());
				} else {
					return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_LEAVE");
				}
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_LEAVE_MERAXIS_ABSENT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "退出[siren.namePos]的办公室。", PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode SEX_REPEAT_CHOICES = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			GameCharacter domCharacter = dominantRepeatSex?Main.game.getPlayer():Main.game.getNpc(Lyssieth.class);
			GameCharacter subCharacter = dominantRepeatSex?Main.game.getNpc(Lyssieth.class):Main.game.getPlayer();
			
			if(index==1) {
				return new ResponseSex("使用小穴",
						"告诉莉西丝你想使用她的小穴。",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(domCharacter, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(subCharacter, SexSlotStanding.STANDING_SUBMISSIVE))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(targetedCharacter.hasPenis()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
									}
								}
								return character.getMainSexPreference(targetedCharacter);
							}
						},
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_PUSSY_"+(dominantRepeatSex?"DOM":"SUB"))) {
					@Override
					public void effects() {
						Main.game.getNpc(Lyssieth.class).setPenisType(PenisType.NONE);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("长出肉棒",
						"告诉莉西丝你想让她长出肉棒。",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(domCharacter, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(subCharacter, SexSlotStanding.STANDING_SUBMISSIVE))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(targetedCharacter.hasVagina()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
										
									} else if(Main.game.isAnalContentEnabled()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
									}
								}
								return character.getMainSexPreference(targetedCharacter);
							}
						},
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_COCK_"+(dominantRepeatSex?"DOM":"SUB"))) {
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).growCock(PenisType.HUMAN);
					}
				};

			} else if(index==3 && Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")) {
				return new ResponseSex("使用小穴(莉琳)",
						"告诉莉西丝你想让她转化为莉琳形态，然后使用她的小穴。",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(domCharacter, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(subCharacter, SexSlotStanding.STANDING_SUBMISSIVE))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(targetedCharacter.hasPenis()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
									}
								}
								return character.getMainSexPreference(targetedCharacter);
							}
						},
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_LILIN_FORM")
							+ UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_PUSSY_"+(dominantRepeatSex?"DOM":"SUB"))) {
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setLilinBody();
						Main.game.getNpc(Lyssieth.class).setPenisType(PenisType.NONE);
					}
				};
				
			} else if(index==4 && Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")) {
				return new ResponseSex("长出肉棒(莉琳)",
						"告诉莉西丝你想让她转化为莉琳形态，然后让她长出肉棒。",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(domCharacter, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(subCharacter, SexSlotStanding.STANDING_SUBMISSIVE))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(targetedCharacter.hasVagina()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
										
									} else if(Main.game.isAnalContentEnabled()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
									}
								}
								return character.getMainSexPreference(targetedCharacter);
							}
						},
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_LILIN_FORM")
							+ UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_COCK_"+(dominantRepeatSex?"DOM":"SUB"))) {
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setLilinBody();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))>=Main.game.getNpc(Lyssieth.class).getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_SEX_NOT_SATISFIED");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "退出莉西丝的办公室。", SIREN_OFFICE_LEAVE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						Main.game.getNpc(Lyssieth.class).setStartingBody(false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DEMON_TF = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("绝不松口", "在莉西丝犹豫时步步紧逼，劝她按照你的要求做。", DEMON_TF_CONTINUE);
				
			} else if(index==2){
				return new Response("表示理解", "不再继续要求，告诉莉西丝你也不想强迫她做不愿做的事情。", DEMON_TF_CHANGE_MIND) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_RELENT"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DEMON_TF_CONTINUE = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_CONTINUE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("确信",
						"告诉莉西丝你已经想好了，想要<b>永久</b>变为恶魔，也理解为了达到目的，必须要她上了你才行……"
								+ "<br/>[style.italicsSex(莉西丝将会长出肉棒，并在性爱中使用……)]",
						DEMON_TF_START) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.RACE_DEMON;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag("innoxia_child_of_lyssieth", true);
						// Lyssieth strips and transforms to full lilin
						((Lyssieth) Main.game.getNpc(Lyssieth.class)).setLilinBody();
						for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
							c.setSealed(false);
						}
						Main.game.getPlayer().setAllAreasKnownByCharacter(Main.game.getNpc(Lyssieth.class), true);
						Main.game.getNpc(Lyssieth.class).setAllAreasKnownByCharacter(Main.game.getPlayer(), true);
					}
				};
				
			} else if(index==2) {
				return new Response("确信(只用小穴)",
						"告诉莉西丝你已经想好了，想要<b>永久</b>变为恶魔，也理解为了达到目的，必须要她上了你才行……"
								+ "<br/>[style.italicsSex(告诉莉西丝不用长出肉棒……)]",
						DEMON_TF_START) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.RACE_DEMON;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag("innoxia_child_of_lyssieth", true);
						// Lyssieth strips and transforms to full lilin
						((Lyssieth) Main.game.getNpc(Lyssieth.class)).setLilinBody();
						Main.game.getNpc(Lyssieth.class).setPenisType(PenisType.NONE);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethNoCockDemonTF, true);
						
						for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
							c.setSealed(false);
						}
						Main.game.getPlayer().setAllAreasKnownByCharacter(Main.game.getNpc(Lyssieth.class), true);
						Main.game.getNpc(Lyssieth.class).setAllAreasKnownByCharacter(Main.game.getPlayer(), true);
					}
				};
				
			} else if(index==3){
				return new Response("改变主意", "告诉莉西丝你改变主意了，并不想让她把你变成恶魔。", DEMON_TF_CHANGE_MIND) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_CONTINUE_CHANGE_MIND"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DEMON_TF_CHANGE_MIND = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LYSSIETH_OFFICE_TALK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DEMON_TF_START = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("顺从",
						(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethNoCockDemonTF)
								?"按照莉西丝说的，跪下来给她舔阴。"
								:"按照莉西丝说的，先将她的肉棒撸到勃起，再跪下来给她口交。")
							+ "<br/>[style.italicsFeminine(莉西丝高潮时会让你变得更加女性化！)]",
						true,
						false,
						new SMLyssiethDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethNoCockDemonTF)) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
						} else {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
						}
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("支配",
						"拒绝莉西丝的要求，强迫她来给你口交。<br/>"
						+ (!Main.game.getPlayer().isFeminine()
								?"[style.italicsMasculine(莉西丝高潮时会让你变得更加男性化！)]"
								:"[style.italicsDisabled(不会造成女性化程度改变。)]")
						+(!Main.game.getPlayer().hasPenis() && !Main.game.getPlayer().hasVagina()
								?(Main.game.getPlayer().isFeminine()
										?"<br/>[style.italicsTfSexual(莉西丝将会赐予你恶魔阴道。)]"
										:"<br/>[style.italicsTfSexual(莉西丝将会赐予你恶魔阴茎。)]")
								:""),
						true,
						true,
						new SMLyssiethDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_DEMON_TF_SEX,
						"") {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(Main.game.getPlayer().hasPenis() || (!Main.game.getPlayer().hasPenis() && !Main.game.getPlayer().hasVagina() && !Main.game.getPlayer().isFeminine())) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lyssieth.class), PenisMouth.BLOWJOB_START, false, true));
						} else {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lyssieth.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
						}
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT"));
						if(!Main.game.getPlayer().hasPenis() && !Main.game.getPlayer().hasVagina()) {
							if(Main.game.getPlayer().isFeminine()) {
								SALyssiethSpecials.playerGrowDemonicVagina();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT_GROW_VAGINA"));
							} else {
								SALyssiethSpecials.playerGrowDemonicPenis();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT_GROW_PENIS"));
							}
						} else if(Main.game.getPlayer().hasPenis()) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT_SERVICE_PENIS"));
							
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT_SERVICE_VAGINA"));
						}
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEMON_TF_SEX = new DialogueNode("结束", "多亏了莉西丝，你现在是恶魔了！", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lyssieth.class).setAffection(Main.game.getPlayer(), 75));
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("安慰", "莉西丝似乎有点担心，自己的半恶魔女儿看到你的样子会有些反应。你安慰她说不会有事的。", AFTER_DEMON_TF_SEX_SURNAMES) {
					@Override
					public void effects() {
						// Resolve Lilaya pregnancy so that her reactions aren't too complicated:
						if(Main.game.getNpc(Lilaya.class).isPregnant()) {
							Main.game.getNpc(Lilaya.class).endPregnancy(true);
						}
						// Lyssieth back to human
						Main.game.getNpc(Lyssieth.class).setStartingBody(false);
						Main.game.getNpc(Lyssieth.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEMON_TF_SEX_SURNAMES = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX_SURNAMES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("改变姓氏", "告诉莉西丝，你会继承她的姓氏，从今往后你将被称为“[pc.name]·莉西丝马尔图拉尼”。", AFTER_DEMON_TF_SEX_SURNAME) {
					@Override
					public void effects() {
						Main.game.getPlayer().setSurname("莉西丝马尔图拉尼");
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX_TAKE_SURNAME"));
					}
				};
				
			} else if(index==2) {
				return new Response("保留姓氏", "告诉莉西丝你要保留自己现在的姓氏。", AFTER_DEMON_TF_SEX_SURNAME) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX_DO_NOT_TAKE_SURNAME"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEMON_TF_SEX_SURNAME = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开",
						"离开莉西丝的办公室，来到相连的办公室兼等候室。",
						AFTER_DEMON_TF_SIREN_OFFICE_LEAVE) {
					@Override
					public void effects() {
						Main.game.getNpc(Lyssieth.class).cleanAllDirtySlots(true);
						Main.game.getNpc(Lyssieth.class).cleanAllClothing(true, false);
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_DEMON_TF_SIREN_OFFICE_LEAVE = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.game.getNpc(DarkSiren.class).getHomeWorldLocation()==WorldType.LYSSIETH_PALACE) {
				if(Main.game.getPlayer().hasCompanions()) {
					return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SIREN_OFFICE_LEAVE_COMPANION", Main.game.getPlayer().getMainCompanion());
				} else {
					return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SIREN_OFFICE_LEAVE");
				}
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SIREN_OFFICE_LEAVE_MERAXIS_ABSENT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "去往莉西丝宫殿的走廊。", PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LILAYA_DEMON_TF_START = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("梅拉克西丝",
						Main.game.getNpc(DarkSiren.class).getHomeWorldLocation()==WorldType.LYSSIETH_PALACE
							?"莉莱雅告诉你其他的姐妹进入办公室。"
							:"等待莉西丝跟梅拉克西丝一起传送回办公室。",
						LILAYA_DEMON_TF_MERAXIS_CHOICE) {
						@Override
						public void effects() {
							Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LILAYA_DEMON_TF_MERAXIS_CHOICE = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_CHOICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("同时转化", "说服梅拉克西丝接受纯恶魔转化。", LILAYA_DEMON_TF_SEX_CHOICE) {
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setLilinBody();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_CHOICE"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.TWO_NEUTRAL, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).addFetish(Fetish.FETISH_INCEST, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).addFetish(Fetish.FETISH_INCEST, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Lyssieth.class), 75));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getNpc(Lyssieth.class), 75));
					}
				};
				
			} else if(index==2) {
				return new Response("仅限莉莱雅",
						"告诉梅拉克西丝如果不愿意的话，也不必转化。<br>[style.italics(你可以随后改变注意，说服梅拉克西丝转化为纯恶魔。)]",
						LILAYA_DEMON_TF_SEX_CHOICE) {
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setLilinBody();
						Main.game.getNpc(DarkSiren.class).returnToHome();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SOLO_SEX_CHOICE"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.TWO_NEUTRAL, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).addFetish(Fetish.FETISH_INCEST, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Lyssieth.class), 75));
					}
				};
			}
			return null;
		}
	};
	
	private static boolean isMeraxisBeingTransformed() {
		return Main.game.getNpc(DarkSiren.class).getLocationPlace().getPlaceType().equals(PlaceType.LYSSIETH_PALACE_OFFICE);
	}
	
	private static void returnCompanionsToLab() {
		if(Main.game.getPlayer().hasCompanions()) {
			for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
				companion.setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
			}
		}
	}
	
	public static final DialogueNode LILAYA_DEMON_TF_SEX_CHOICE = new DialogueNode("", "", true, true) {
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
				return new Response("准备好了", "莉莱雅自甘堕落沉迷乱伦后，已经准备好跟你和她的母亲一起做爱，转化成恶魔了。", LILAYA_DEMON_TF_ORIFICE_SELECTION);
				
			} else if(index==2) {
				return new Response("走到外面",
						isMeraxisBeingTransformed()
							?"你并不想跟母亲和姐妹一起做爱，于是走向外面，进入了梅拉克西丝的办公室，在外面等她们三个结束。"
							:"你并不想跟母亲和姐妹一起做爱，于是走向外面，进入了梅拉克西丝的办公室，在外面等她们两个结束。",
						LILAYA_DEMON_TF_WAIT_IN_OFFICE){
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setDaughterToFullDemon(Lilaya.class);
						Main.game.getNpc(Lilaya.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						if(isMeraxisBeingTransformed()) {
							((Lyssieth)Main.game.getNpc(Lyssieth.class)).setDaughterToFullDemon(DarkSiren.class);
							Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							if(Main.game.isAnalContentEnabled()) {
								Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							} else {
								Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							}
						}
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						returnCompanionsToLab();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LILAYA_DEMON_TF_ORIFICE_SELECTION = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(isMeraxisBeingTransformed()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_ORIFICE_SELECTION"); // Meraxis gets embarrassed and wants to only watch
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SOLO_ORIFICE_SELECTION");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("操小穴", "因为你不能使用你的鸡巴，所以你没法操莉莱雅的小穴……", null);
				}
				return new ResponseSex("操小穴",
						"告诉你的姐妹，你想操她的小穴，你妈妈在后面操她的屁股。"
						+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
								?"<br/>[style.italicsTfSex(你会长出恶魔肉棒操她。)]"
								:""),
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_FUCK_PUSSY")) {
					@Override
					public void effects() {
						if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
							Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
							Main.game.getPlayer().setPenisSize(20);
							Main.game.getPlayer().setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), PenisVagina.PENIS_FUCKING_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
					}
				};
				
			} else if(index==2) {
				if(!Main.game.isAnalContentEnabled()) {
					return null;
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("操屁眼", "因为你不能使用自己的鸡巴，所以你没法操莉莱雅的屁眼……", null);
				}
				return new ResponseSex("操屁股",
						"告诉你的姐妹，你想在后面操她的屁股，你妈妈操她的小穴。"
						+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
								?"<br/>[style.italicsTfSex(你会长出恶魔肉棒操她。)]"
								:""),
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_FUCK_ASS")) {
					@Override
					public void effects() {
						if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
							Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
							Main.game.getPlayer().setPenisSize(20);
							Main.game.getPlayer().setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), PenisAnus.PENIS_FUCKING_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
					}
				};
				
			} else if(index==6) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("提供舔阴", "你的嘴被锁住了，你不能舔莉莱雅的小穴……", null);
				}
				return new ResponseSex("提供舔阴",
						"告诉你的姐妹你想舔她的小穴，你的母亲在后面操她的屁股。",
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_GIVE_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), TongueVagina.CUNNILINGUS_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
					}
				};
				
			} else if(index==7) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("献上口交", "你的嘴被锁住了，你不能嗦莉莱雅的肉棒……", null);
				}
				return new ResponseSex("给人口交",
						"告诉姐妹你想吮吸她的肉棒，你的母亲在后面操她的小穴。"
								+ "<br/>[style.italicsTfSex(莉莱雅会长出恶魔肉棒给你吸。)]",
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_GIVE_BLOWJOB")) {
					@Override
					public void effects() {
						if(!Main.game.getNpc(Lilaya.class).hasPenisIgnoreDildo()) {
							Main.game.getNpc(Lilaya.class).setPenisType(PenisType.DEMON_COMMON);
							Main.game.getNpc(Lilaya.class).setPenisSize(20);
							Main.game.getNpc(Lilaya.class).setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
							Main.game.getNpc(Lilaya.class).setPenisCumStorage(100);
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
					}
				};
				
			} else if(index==8 && Main.game.isAnalContentEnabled()) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("提供吻肛", "你的嘴被锁住了，你不能舔莉莱雅的肛门……", null);
				}
				return new ResponseSex("提供吻肛",
						"告诉你的姐妹你想舔她的屁股，你的母亲操她的小穴。",
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_GIVE_ANILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), TongueAnus.ANILINGUS_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
					}
				};
				
			} else if(index==11
					&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
					&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				return new ResponseSex("使用[pc.fingers]",
						"你的阴茎和嘴都被锁住，不得不指交你姐妹的小穴，你的母亲操她的屁股。",
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_FINGERING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), FingerVagina.FINGERING_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_LILAYA_DEMON_TF_SEX = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(isMeraxisBeingTransformed()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_LILAYA_DEMON_TF_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_LILAYA_DEMON_TF_SOLO_SEX");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isMeraxisBeingTransformed()) {
				if(index==1) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("操小穴",
								Main.game.getNpc(DarkSiren.class).isVaginaVirgin()
									?"因为你无法控制自己的阴茎，所以你没法夺走梅拉克西丝的贞操……"
									:"因为你不能使用自己的阴茎，所以你没法操梅拉克西丝的小穴……",
								null);
					}
					return new ResponseSex("操小穴",
							(Main.game.getNpc(DarkSiren.class).isVaginaVirgin()
								?"告诉你的姐妹，你会夺取她的童贞，你的母亲会在后面操她的屁股，莉莱雅会使用她的嘴。"
								:"告诉你的姐妹，你想操她的小穴，你的母亲在后面操她的屁股，莉莱雅使用她的嘴。")
							+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
									?"<br/>[style.italicsTfSex(你会长出恶魔肉棒操她。)]"
									:""),
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.MISSIONARY),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.BESIDE)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_FUCK_PUSSY")) {
						@Override
						public void effects() {
							if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
								Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
								Main.game.getPlayer().setPenisSize(20);
								Main.game.getPlayer().setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
							}
							Main.game.getNpc(Lilaya.class).setPenisType(PenisType.NONE);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(DarkSiren.class), Main.game.getNpc(Lilaya.class), TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
						}
					};
					
				} else if(index==2) {
					if(!Main.game.isAnalContentEnabled()) {
						return null;
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("操屁眼", "因为你不能使用自己的阴茎，所以你没法操梅拉克西丝的屁眼……", null);
					}
					return new ResponseSex("操屁股",
							(Main.game.getNpc(DarkSiren.class).isAnalVirgin()
								?"告诉你的姐妹，你会夺取她的肛门童贞，你的母亲会操她的小穴，莉莱雅会使用她的嘴。"
								:"告诉你的姐妹你会操她的屁股，你的母亲会操她的小穴，莉莱雅会使用她的嘴。")
							+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
									?"<br/>[style.italicsTfSex(你会长出恶魔肉棒操她。)]"
									:""),
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.BESIDE)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_FUCK_ASS")) {
						@Override
						public void effects() {
							if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
								Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
								Main.game.getPlayer().setPenisSize(20);
								Main.game.getPlayer().setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
							}
							Main.game.getNpc(Lilaya.class).setPenisType(PenisType.NONE);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(DarkSiren.class), Main.game.getNpc(Lilaya.class), TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
						}
					};
					
				} else if(index==3) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						return new Response("被舔阴", "你的小穴不可触及，所以梅拉克西丝没法舔你的下体……", null);
					}
					return new ResponseSex("被舔阴",
							"告诉你的姐妹，你想使用她的嘴，让你母亲操她的小穴。莉莱雅撅起了屁股。"
									+ (!Main.game.getPlayer().hasVagina()
											?"<br/>[style.italicsTfSex(你会长出恶魔小穴给她舔。)]"
											:""),
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.BESIDE),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_RECEIVE_ORAL_CUNNILINGUS")) {
						@Override
						public void effects() {
							if(!Main.game.getPlayer().hasVagina()) {
								Main.game.getPlayer().setVaginaType(VaginaType.DEMON_COMMON);
							}
							if(!Main.game.getNpc(Lilaya.class).hasPenisIgnoreDildo()) {
								Main.game.getNpc(Lilaya.class).setPenisType(PenisType.DEMON_COMMON);
								Main.game.getNpc(Lilaya.class).setPenisSize(20);
								Main.game.getNpc(Lilaya.class).setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
								Main.game.getNpc(Lilaya.class).setPenisCumStorage(100);
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(DarkSiren.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						}
					};
					
				} else if(index==4) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("被口交", "你无法控制自己的阴茎，所以梅拉克西丝不能给你口交……", null);
					}
					new ResponseSex("被口交",
							"告诉你的姐妹，你想使用她的嘴，让你母亲操她的小穴。莉莱雅撅起了屁股。"
									+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
											?"<br/>[style.italicsTfSex(你会长出恶魔肉棒操她。)]"
											:""),
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.BESIDE),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_RECEIVE_ORAL_BLOWJOB")) {
						@Override
						public void effects() {
							if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
								Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
								Main.game.getPlayer().setPenisSize(20);
								Main.game.getPlayer().setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
							}
							if(!Main.game.getNpc(Lilaya.class).hasPenisIgnoreDildo()) {
								Main.game.getNpc(Lilaya.class).setPenisType(PenisType.DEMON_COMMON);
								Main.game.getNpc(Lilaya.class).setPenisSize(20);
								Main.game.getNpc(Lilaya.class).setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
								Main.game.getNpc(Lilaya.class).setPenisCumStorage(100);
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(DarkSiren.class), PenisMouth.BLOWJOB_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						}
					};
					
				}  else if(index==5
						&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
						&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new ResponseSex("跪在前面",
							"你的生殖器被限制，只能跪在梅拉克西丝身前，当莉莱雅和莉西丝操她时用其他的方式爽一爽。",
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.BESIDE),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_KNEELING_NO_ACCESS")) {
						@Override
						public void effects() {
							if(!Main.game.getNpc(Lilaya.class).hasPenisIgnoreDildo()) {
								Main.game.getNpc(Lilaya.class).setPenisType(PenisType.DEMON_COMMON);
								Main.game.getNpc(Lilaya.class).setPenisSize(20);
								Main.game.getNpc(Lilaya.class).setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
								Main.game.getNpc(Lilaya.class).setPenisCumStorage(100);
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						}
					};
				} 
				
			} else {
				if(index==1) {
					return new Response("莉西丝", "看着对面的莉西丝，看看她的反应。", LILAYA_DEMON_TF_FINISHED_REACTION) {
							@Override
							public void effects() {
								Main.game.getNpc(Lyssieth.class).setStartingBody(false);
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_LILAYA_DEMON_TF_SOLO_SEX_FINISHED_REACTION"));
							}
						};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_MERAXIS_DEMON_TF_SEX = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_MERAXIS_DEMON_TF_REPEAT_SEX");
			}
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_MERAXIS_DEMON_TF_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("莉西丝",
						"看着对面的莉西丝，看看她的反应。",
						LILAYA_DEMON_TF_FINISHED_REACTION) {
						@Override
						public void effects() {
							Main.game.getNpc(Lyssieth.class).setStartingBody(false);
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_MERAXIS_DEMON_TF_REPEAT_SEX_FINISHED_REACTION"));
								if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("innoxia_meraxis_demon_tf_teleported"))) {
									Main.game.getNpc(DarkSiren.class).returnToHome();
								}
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_LILAYA_DEMON_TF_SEX_FINISHED_REACTION"));
							}
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode LILAYA_DEMON_TF_WAIT_IN_OFFICE = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			if(isMeraxisBeingTransformed()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_WAIT_IN_OFFICE");
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SOLO_WAIT_IN_OFFICE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(
						isMeraxisBeingTransformed()
							?"恶魔姐妹"
							:"恶魔莉莱雅",
						isMeraxisBeingTransformed()
							?"返回莉西丝的办公室，看看莉莱雅和梅拉克西丝的变化。"
							:"返回莉西丝的办公室，看看莉莱雅的变化。",
						LILAYA_DEMON_TF_FINISHED_REACTION) {
						@Override
						public void effects() {
							if(isMeraxisBeingTransformed()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_FINISHED_REACTION_OFFICE_END"));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SOLO_FINISHED_REACTION_OFFICE_END"));
							}
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
							Main.game.getNpc(DarkSiren.class).returnToHome();
							returnCompanionsToLab();
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LILAYA_DEMON_TF_FINISHED_REACTION = new DialogueNode("", "", true, true) {
		
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
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("innoxia_meraxis_demon_tf_teleported"))) {
					return new Response("传送",
							"你和梅拉克西丝传送回了伊利斯的红龙酒馆。",
							DialogueManager.getDialogueFromId("innoxia_places_fields_elis_tavern_f0_meraxis_post_demon_tf")) {
							@Override
							public void effects() {
								// Reset offspring to full demons:
								if(Main.game.getNpc(DarkSiren.class).isPregnant()) {
									for(OffspringSeed offspring : Main.game.getNpc(DarkSiren.class).getPregnantLitter().getOffspringSeed()) {
										offspring.setBody(offspring.getGender(), Main.game.getNpc(DarkSiren.class), Main.game.getNpc(DarkSiren.class).getPregnantLitter().getFather());
									}
									Main.game.getNpc(DarkSiren.class).getPregnantLitter().generateBirthedDescription();
								}
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
							}
						};
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
					return new Response(
							Main.game.getNpc(DarkSiren.class).getHomeWorldLocation()==WorldType.LYSSIETH_PALACE
								?"梅拉克西丝的办公室"
								:"继续",
							Main.game.getNpc(DarkSiren.class).getHomeWorldLocation()==WorldType.LYSSIETH_PALACE
								?"你和梅拉克西丝回到了办公兼等候室。"
								:"你发现自己回到了办公兼等候室。",
							LILAYA_DEMON_TF_END) {
							@Override
							public void effects() {
								// Reset offspring to full demons:
								if(Main.game.getNpc(DarkSiren.class).isPregnant()) {
									for(OffspringSeed offspring : Main.game.getNpc(DarkSiren.class).getPregnantLitter().getOffspringSeed()) {
										offspring.setBody(offspring.getGender(), Main.game.getNpc(DarkSiren.class), Main.game.getNpc(DarkSiren.class).getPregnantLitter().getFather());
									}
									Main.game.getNpc(DarkSiren.class).getPregnantLitter().generateBirthedDescription();
								}
								Main.game.getNpc(DarkSiren.class).returnToHome();
								Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
							}
						};
						
				} else {
					return new Response("莉莱雅的实验室", "你和莉莱雅回到了她的实验室。", LILAYA_DEMON_TF_END) {
							@Override
							public void effects() {
								// Reset offspring to full demons:
								if(Main.game.getNpc(DarkSiren.class).isPregnant()) {
									for(OffspringSeed offspring : Main.game.getNpc(DarkSiren.class).getPregnantLitter().getOffspringSeed()) {
										offspring.setBody(offspring.getGender(), Main.game.getNpc(DarkSiren.class), Main.game.getNpc(DarkSiren.class).getPregnantLitter().getFather());
									}
									Main.game.getNpc(DarkSiren.class).getPregnantLitter().generateBirthedDescription();
								}
	
								// Reset offspring to full demons:
								if(Main.game.getNpc(Lilaya.class).isPregnant()) {
									for(OffspringSeed offspring : Main.game.getNpc(Lilaya.class).getPregnantLitter().getOffspringSeed()) {
										offspring.setBody(offspring.getGender(), Main.game.getNpc(Lilaya.class), Main.game.getNpc(Lilaya.class).getPregnantLitter().getFather());
									}
									Main.game.getNpc(Lilaya.class).getPregnantLitter().generateBirthedDescription();
								}
								
								Main.game.getNpc(DarkSiren.class).returnToHome();
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
								Main.game.getPlayer().setNearestLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR, false);
							}
						};
				}
			}
			return null;
		}
	};

	public static final DialogueNode LILAYA_DEMON_TF_END = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lyssieth.class).setStartingBody(false);
		}
		@Override
		public boolean isTravelDisabled() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF);
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_END");
			}
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
				return SIREN_OFFICE.getResponse(responseTab, index);
			}
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
}
