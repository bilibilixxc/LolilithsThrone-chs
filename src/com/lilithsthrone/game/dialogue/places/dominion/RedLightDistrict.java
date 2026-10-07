package com.lilithsthrone.game.dialogue.places.dominion;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Angel;
import com.lilithsthrone.game.character.npc.dominion.Bunny;
import com.lilithsthrone.game.character.npc.dominion.Loppy;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayProstituteDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public class RedLightDistrict {
	
	public static final DialogueNode OUTSIDE = new DialogueNode("红灯区", "红灯区", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/redLightDistrict", "OUTSIDE"));
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/redLightDistrict", "OUTSIDE_STORM"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/redLightDistrict", "OUTSIDE_NO_STORM"));
			}
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/redLightDistrict", "OUTSIDE_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("天使之吻", "走进大型妓院“天使之吻”。", PlaceType.ANGELS_KISS_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelIntroduced)) {
							Main.game.getNpc(Angel.class).setLocation(WorldType.ANGELS_KISS_GROUND_FLOOR, PlaceType.ANGELS_KISS_ENTRANCE, false);
						}
						Main.game.getPlayer().setLocation(WorldType.ANGELS_KISS_GROUND_FLOOR, PlaceType.ANGELS_KISS_ENTRANCE, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_ENTRANCE = new DialogueNode("入口", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelIntroduced);
		}
		
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ENTRANCE");
				
			} else {
				UtilText.nodeContentSB.setLength(0);
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ENTRANCE_REPEAT"));

				List<NPC> charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
				charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
				
				if(charactersPresent.isEmpty()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ENTRANCE_REPEAT_EMPTY"));
				}else {
					UtilText.nodeContentSB.append(UtilText.parse(charactersPresent.get(0), UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ENTRANCE_REPEAT_STAFFED")));
				}
				
				return UtilText.nodeContentSB.toString();
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelIntroduced)) {
				if (index == 1) {
					return new Response("继续", "安吉尔留你自己探索天使之吻……", ANGELS_KISS_ENTRANCE){
						@Override
						public void effects() {
							Main.game.getNpc(Angel.class).setLocation(WorldType.ANGELS_KISS_GROUND_FLOOR, PlaceType.ANGELS_KISS_OFFICE, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.angelIntroduced, true);
						}
					};
				}
				
			} else {
				if (index == 1) {
					return new Response("离开", "离开妓院，回到御城区。", PlaceType.DOMINION_RED_LIGHT_DISTRICT.getDialogue(false)){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_RED_LIGHT_DISTRICT, false);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ANGELS_KISS_CORRIDOR = new DialogueNode("走廊", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ANGELS_KISS_STAIRS_UP = new DialogueNode("上楼", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "STAIRS_UP");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("上楼", "走上楼梯到达妓院的二楼", PlaceType.ANGELS_KISS_STAIRCASE_DOWN.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ANGELS_KISS_FIRST_FLOOR, PlaceType.ANGELS_KISS_STAIRCASE_DOWN, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_STAIRS_DOWN = new DialogueNode("下楼", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "STAIRS_DOWN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("下楼", "走下楼梯回到妓院的一楼", PlaceType.ANGELS_KISS_STAIRCASE_UP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ANGELS_KISS_GROUND_FLOOR, PlaceType.ANGELS_KISS_STAIRCASE_UP, false);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode ANGELS_KISS_BEDROOM = new DialogueNode("卧室", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM"));
			
			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			
			if(charactersPresent.size()>1){
				NPC prostitute = charactersPresent.get(0);
				NPC client = charactersPresent.get(1);
				for(NPC npc : charactersPresent) {
					if(npc instanceof GenericSexualPartner) {
						client = npc;
					} else {
						prostitute = npc;
						Main.game.setActiveNPC(npc);
					}
				}
				
				
				if(prostitute.isSlave() && prostitute.getOwner().isPlayer()) {
					//TODO append description of sex type
//					SlaveryEventLogEntry currentSex = null;
//					long time = 0;
//					for(SlaveryEventLogEntry entry : Main.game.getSlaveryEventLog().get(Main.game.getDayNumber())) {
//						if(entry.getSlaveID().equals(prostitute.getId()) && entry.getTime()>time) {
//							currentSex = entry;
//						}
//					}
//					System.out.println(currentSex.getTags().get(0));
					
					return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_OCCUPIED_SLAVE_SEX", Util.newArrayListOfValues(prostitute, client)); //TODO need obedience/affection variations

				} else {
					return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_OCCUPIED_SEX", Util.newArrayListOfValues(prostitute, client));
				}
				
			} else if(charactersPresent.isEmpty()) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
					if(Main.game.getPlayer().getWorldLocation()==WorldType.ANGELS_KISS_GROUND_FLOOR) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_EMPTY_WHORE_SELF_GROUND_FLOOR"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_EMPTY_WHORE_SELF"));
					}
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_EMPTY"));
				}
				
			} else {
				Main.game.setActiveNPC(charactersPresent.get(0));
				if(charactersPresent.get(0).isSlave() && charactersPresent.get(0).getOwner().isPlayer()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_OCCUPIED_SLAVE", Util.newArrayListOfValues(charactersPresent.get(0)))); //TODO need obedience/affection variations
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_OCCUPIED", Util.newArrayListOfValues(charactersPresent.get(0))));
				}
			}
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			
			if(!charactersPresent.isEmpty()) {
				int cost = 300;
				NPC npc = charactersPresent.get(0);

				if(charactersPresent.size()<=1){
					if(charactersPresent.get(0).isSlave() && charactersPresent.get(0).getOwner().isPlayer()) {
						if (index == 1) {
								return new ResponseSex("做爱",
										UtilText.parse(npc, "跟[npc.name]做爱，你作为主导方。"),
										true, false,
										new SMGeneric(
												Util.newArrayListOfValues(Main.game.getPlayer()),
												Util.newArrayListOfValues(npc),
										null,
										null) {
											public SexPace getStartingSexPaceModifier(GameCharacter character) {
												if(!character.isPlayer()) {
													return SexPace.SUB_NORMAL;
												}
												return super.getStartingSexPaceModifier(character);
											}
										},
										AFTER_SEX_PROSTITUTE,
										UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_PROSTITUTE_SLAVE_SEX"));
							
						} else if (index == 2) {
								return new ResponseSex("服从型性爱",
										UtilText.parse(npc, "让[npc.name]主导，跟你来一场服从型性爱。"),
										true, false,
										new SMGeneric(
												Util.newArrayListOfValues(npc),
												Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null){
											public SexPace getStartingSexPaceModifier(GameCharacter character) {
												if(!character.isPlayer()) {
													return SexPace.DOM_NORMAL;
												}
												return super.getStartingSexPaceModifier(character);
											}
										},
										AFTER_SEX_PROSTITUTE,
										UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_PROSTITUTE_SLAVE_SEX_SUB"));
							
						}
						
						
					} else {
						if (index == 1) {
							if(Main.game.getPlayer().getMoney()<cost) {
								return new Response("做爱("+UtilText.formatAsMoney(cost, "span")+")",
										UtilText.parse(npc, "你没有"+cost+"火币，付不起[npc.name]的嫖资。"), null);
								
							} else {
								return new ResponseSex("做爱("+UtilText.formatAsMoney(cost, "span")+")",
										UtilText.parse(npc, "付[npc.name]"+cost+"个火币，与[npc.herHim]来一场支配型性爱。"),
										true, false,
										new SMGeneric(
												Util.newArrayListOfValues(Main.game.getPlayer()),
												Util.newArrayListOfValues(npc),
										null,
										null){
											public SexPace getStartingSexPaceModifier(GameCharacter character) {
												if(!character.isPlayer()) {
													return SexPace.SUB_NORMAL;
												}
												return super.getStartingSexPaceModifier(character);
											}
										},
										AFTER_SEX_PROSTITUTE,
										UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_PROSTITUTE_SEX")) {
									@Override
									public void effects() {
										Main.game.getPlayer().incrementMoney(-cost);
									}
								};
							}
							
						} else if (index == 2) {
							if(Main.game.getPlayer().getMoney()<cost) {
								return new Response("服从型性爱("+UtilText.formatAsMoney(cost, "span")+")",
										UtilText.parse(npc, "你没有"+cost+"火币，所以付不起跟[npc.name]服从型性爱的钱。"), null);
								
							} else {
								return new ResponseSex("服从型性爱("+UtilText.formatAsMoney(cost, "span")+")",
										UtilText.parse(npc, "付给[npc.Name]"+cost+"火币，让[npc.herHim]主导跟你来一场服从型性爱。"),
										true, false,
										new SMGeneric(
												Util.newArrayListOfValues(npc),
												Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null){
											public SexPace getStartingSexPaceModifier(GameCharacter character) {
												if(!character.isPlayer()) {
													return SexPace.DOM_NORMAL;
												}
												return super.getStartingSexPaceModifier(character);
											}
										},
										AFTER_SEX_PROSTITUTE,
										UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_PROSTITUTE_SEX_SUB")) {
									@Override
									public void effects() {
										Main.game.getPlayer().incrementMoney(-cost);
									}
								};
							}
							
						} else if (index == 5) {
							int fineAmount = AlleywayProstituteDialogue.getModifiedFineAmount(npc);
							if(Main.game.getPlayer().getMoney()<fineAmount) {
								return new Response("移除("+UtilText.formatAsMoney(fineAmount, "span")+")",
										UtilText.parse(npc, "你没有"+Util.intToString(fineAmount)+"火币，付不起[npc.namePos]的债务。"),
										null);
							} else {
								return new Response(
										"移除("+UtilText.formatAsMoney(fineAmount, "span")+")",
										UtilText.parse(npc, "尽管[npc.name]已经不在逮捕令上了，但[npc.she]还是得交齐罚金。交给[npc.name]足够偿还剩下的款项的钱财，[npc.herHim]便可以一身轻松地离开这个城市了。"
												+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]"),
										ANGELS_KISS_PROSTITUTE_REMOVAL_PAID) {
									@Override
									public Colour getHighlightColour() {
										return PresetColour.GENERIC_NPC_REMOVAL;
									}
								};
							}
						}
					}
				}
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained) && Main.game.getPlayer().getWorldLocation()==WorldType.ANGELS_KISS_FIRST_FLOOR) {
				if(index==1) {
					return new Response("出卖身体(服从)", "告诉安吉尔你想做服从的一方，等待顾客出现。", ANGELS_KISS_SELL_SELF_SUB){
						@Override
						public void effects() {
							NPC npc = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, false), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
							if(Math.random()<0.4f) {
								npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
							} else {
								if(Main.game.getPlayer().isFeminine()) {
									npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
								} else {
									npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
								}
							}
							npc.removeFetish(Fetish.FETISH_SUBMISSIVE);
							npc.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
							try {
								Main.game.addNPC(npc, false);
								Main.game.setActiveNPC(npc);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					};
					
				} else if(index==2) {
					return new Response("出卖身体(支配)", "告诉安吉尔你想做支配的一方，等待顾客出现。", ANGELS_KISS_SELL_SELF_DOM){
						@Override
						public void effects() {
							NPC npc = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, false), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
							if(Math.random()<0.4f) {
								npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
							} else {
								if(Main.game.getPlayer().isFeminine()) {
									npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
								} else {
									npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
								}
							}
							npc.removeFetish(Fetish.FETISH_DOMINANT);
							npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
							try {
								Main.game.addNPC(npc, false);
								Main.game.setActiveNPC(npc);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PROSTITUTE = new DialogueNode("卧室", "挣脱[npc.namePos]的魔爪。", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			List<NPC> charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			
			if(Main.sex.getNumberOfOrgasms(charactersPresent.get(0))==0) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "PROSTITUTE_AFTER_SEX_NO_ORGASM");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "PROSTITUTE_AFTER_SEX");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ANGELS_KISS_SELL_SELF_SUB = new DialogueNode("卧室", "。", true) {

		@Override
		public int getSecondsPassed() {
			return 25*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_SUB");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			int payment = 2000;
			
			if (index == 1) {
				return new ResponseSex("做爱("+UtilText.formatAsMoney(2000, "span")+")",
						"同意以"+2000+"火币的价格跟[npc.name]做爱。",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getActiveNPC()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SEX_SELL_SELF_SUB,
						UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_SUB_START")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(payment);
					}
				};
				
			} else if(index == 2) {
				return new Response("拒绝", "告诉[npc.name]你对[npc.sheHasFull]的主意不感兴趣……", ANGELS_KISS_SELL_SELF_DECLINE){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DECLINE")
								+UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DECLINE_SUB"));
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_SELL_SELF_DOM = new DialogueNode("卧室", "。", true) {

		@Override
		public int getSecondsPassed() {
			return 25*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			int payment = 2000;
			
			if (index == 1) {
				return new ResponseSex("做爱("+UtilText.formatAsMoney(2000, "span")+")",
						"同意以"+2000+"火币的价格跟[npc.name]做爱。",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getActiveNPC()),
						null,
						null), AFTER_SEX_SELL_SELF_DOM, UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DOM_START")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(payment);
					}
				};
				
			} else if(index == 2) {
				return new Response("拒绝", "告诉[npc.name]你对[npc.sheHasFull]的主意不感兴趣……", ANGELS_KISS_SELL_SELF_DECLINE){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DECLINE")
								+UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DECLINE_DOM"));
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_SELL_SELF_DECLINE = new DialogueNode("卧室", "。", false, true) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ANGELS_KISS_BEDROOM.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEX_SELL_SELF_DOM = new DialogueNode("卧室", "挣脱[npc.namePos]的魔爪。", true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DOM_AFTER_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("稍作休整", "经历了一场酣畅淋漓的性爱后，你需要恢复一下。", ANGELS_KISS_BEDROOM){
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};

			} else if (index == 2) {
				return new Response("清洗", "快速冲个澡清洁自己。", ANGELS_KISS_BEDROOM){
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SHOWER, 240+30));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_SELL_SELF_SUB = new DialogueNode("卧室", "挣脱[npc.namePos]的魔爪。", true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_SUB_AFTER_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("稍作休整", "经历了一场酣畅淋漓的性爱后，你需要恢复一下。", ANGELS_KISS_BEDROOM){
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};

			} else if (index == 2) {
				return new Response("清洗", "快速冲个澡清洁自己。", ANGELS_KISS_BEDROOM){
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SHOWER, 240+30));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_BEDROOM_BUNNY = new DialogueNode("兔兔的卧室", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.bunnyIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ANGELS_KISS_BEDROOM_BUNNY");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ANGELS_KISS_BEDROOM_BUNNY_REPEAT");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("进入", "进入兔兔的房间打招呼。", ANGELS_KISS_BEDROOM_BUNNY_ENTER);
			}
			return null;
		}
	};
	
	public static final DialogueNode ANGELS_KISS_BEDROOM_BUNNY_ENTER = new DialogueNode("兔兔的卧室", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.bunnyIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ANGELS_KISS_BEDROOM_BUNNY_ENTER");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ANGELS_KISS_BEDROOM_BUNNY_ENTER_REPEAT");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			int cost = 1500;
			int threesomeCost = 5000;
			
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<cost) {
					return new Response("做爱("+UtilText.formatAsMoney(cost, "span")+")", "你没有"+cost+"火币，付不起钱跟兔兔做爱。", null);
					
				} else {
					return new ResponseSex("做爱("+UtilText.formatAsMoney(cost, "span")+")",
							"付"+cost+"火币跟兔兔做爱。",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Bunny.class)),
							null,
							null), AFTER_SEX_BUNNY, UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_SEX")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.bunnyIntroduced, true);
							if(Main.game.getNpc(Bunny.class).isVisiblyPregnant()) {
								Main.game.getNpc(Bunny.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							Main.game.getPlayer().incrementMoney(-cost);
						}
					};
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().getMoney()<threesomeCost) {
					return new Response("三人行("+UtilText.formatAsMoney(threesomeCost, "span")+")", "你没有"+threesomeCost+"火币，付不起钱跟兔兔和耷耷一起做爱。", null);
					
				} else {
					return new ResponseSex("三人行("+UtilText.formatAsMoney(threesomeCost, "span")+")",
							"付上"+threesomeCost+"火币，跟兔兔和耷耷一起做爱。",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Bunny.class), Main.game.getNpc(Loppy.class)),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_BUNNY_THREESOME,
							UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_THREESOME")
							+(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.loppyIntroduced)
									?UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_THREESOME_LOPPY_INTRODUCED")
									:UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_THREESOME_LOPPY_NOT_INTRODUCED"))) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.bunnyIntroduced, true);
							if(Main.game.getNpc(Bunny.class).isVisiblyPregnant()) {
								Main.game.getNpc(Bunny.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.loppyIntroduced, true);
							if(Main.game.getNpc(Loppy.class).isVisiblyPregnant()) {
								Main.game.getNpc(Loppy.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							Main.game.getNpc(Loppy.class).setLocation(WorldType.ANGELS_KISS_FIRST_FLOOR, PlaceType.ANGELS_KISS_BEDROOM_BUNNY, false);
							Main.game.getPlayer().incrementMoney(-threesomeCost);
						}
					};
				}
				
			} else if(index == 3) {
				return new Response("谢绝", "你现在没兴趣嫖兔兔……", PlaceType.ANGELS_KISS_CORRIDOR.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.ANGELS_KISS_FIRST_FLOOR, PlaceType.ANGELS_KISS_CORRIDOR, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.bunnyIntroduced, true);
						if(Main.game.getNpc(Bunny.class).isVisiblyPregnant()) {
							Main.game.getNpc(Bunny.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_DECLINE"));
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_SEX_BUNNY = new DialogueNode("结束", "挣脱兔兔的魔爪。", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Bunny.class)) == 0) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "AFTER_SEX_BUNNY_NO_ORGASM");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "AFTER_SEX_BUNNY");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_BUNNY_THREESOME = new DialogueNode("结束", "挣脱兔兔的魔爪。", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "AFTER_SEX_BUNNY_THREESOME");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ANGELS_KISS_BEDROOM_LOPPY = new DialogueNode("耷耷的卧室", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.loppyIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ANGELS_KISS_BEDROOM_LOPPY");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ANGELS_KISS_BEDROOM_LOPPY_REPEAT");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("进入", "进入耷耷的房间打招呼。", ANGELS_KISS_BEDROOM_LOPPY_ENTER);
			}
			return null;
		}
	};
	public static final DialogueNode ANGELS_KISS_BEDROOM_LOPPY_ENTER = new DialogueNode("耷耷的卧室", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.loppyIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ANGELS_KISS_BEDROOM_LOPPY_ENTER");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ANGELS_KISS_BEDROOM_LOPPY_ENTER_REPEAT");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			int cost = 2000;
			int dominantCost = 2500;
			int threesomeCost = 5000;
			
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<cost) {
					return new Response("做爱("+UtilText.formatAsMoney(cost, "span")+")", "你没有"+cost+"火币，付不起钱跟耷耷做爱。", null);
					
				} else {
					return new ResponseSex("做爱("+UtilText.formatAsMoney(cost, "span")+")",
							"付上"+cost+"火币跟耷耷做爱。",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Loppy.class)),
							null,
							null), AFTER_SEX_LOPPY, UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_SEX")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.loppyIntroduced, true);
							if(Main.game.getNpc(Loppy.class).isVisiblyPregnant()) {
								Main.game.getNpc(Loppy.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							Main.game.getPlayer().incrementMoney(-cost);
						}
					};
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().getMoney()<dominantCost) {
					return new Response("服从型性爱("+UtilText.formatAsMoney(dominantCost, "span")+")", "你没有"+dominantCost+"火币，没法跟耷耷来一场服从型性爱。", null);
					
				} else {
					return new ResponseSex("服从型性爱("+UtilText.formatAsMoney(dominantCost, "span")+")",
							"付上"+dominantCost+"火币，让耷耷占据主导权上了你。",
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(Loppy.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SEX_LOPPY, UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_SEX_SUBMISSIVE")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.loppyIntroduced, true);
							if(Main.game.getNpc(Loppy.class).isVisiblyPregnant()) {
								Main.game.getNpc(Loppy.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							Main.game.getPlayer().incrementMoney(-dominantCost);
						}
					};
				}
				
			} else if (index == 3) {
				if(Main.game.getPlayer().getMoney()<threesomeCost) {
					return new Response("三人行("+UtilText.formatAsMoney(threesomeCost, "span")+")", "你没有"+threesomeCost+"火币，付不起钱跟兔兔和耷耷一起做爱。", null);
					
				} else {
					return new ResponseSex("三人行("+UtilText.formatAsMoney(threesomeCost, "span")+")",
							"付上"+threesomeCost+"火币，跟耷耷和兔兔一起做爱。",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Loppy.class), Main.game.getNpc(Bunny.class)),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_LOPPY_THREESOME,
							UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_THREESOME")
							+(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.bunnyIntroduced)
									?UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_THREESOME_BUNNY_INTRODUCED")
									:UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_THREESOME_BUNNY_NOT_INTRODUCED"))) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.bunnyIntroduced, true);
							if(Main.game.getNpc(Bunny.class).isVisiblyPregnant()) {
								Main.game.getNpc(Bunny.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.loppyIntroduced, true);
							if(Main.game.getNpc(Loppy.class).isVisiblyPregnant()) {
								Main.game.getNpc(Loppy.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							Main.game.getNpc(Bunny.class).setLocation(WorldType.ANGELS_KISS_FIRST_FLOOR, PlaceType.ANGELS_KISS_BEDROOM_LOPPY, false);
							Main.game.getPlayer().incrementMoney(-threesomeCost);
						}
					};
				}
				
			} else if(index == 4) {
				return new Response("谢绝", "你现在没兴趣嫖耷耷……", PlaceType.ANGELS_KISS_CORRIDOR.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.ANGELS_KISS_FIRST_FLOOR, PlaceType.ANGELS_KISS_CORRIDOR, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.loppyIntroduced, true);
						if(Main.game.getNpc(Loppy.class).isVisiblyPregnant()) {
							Main.game.getNpc(Loppy.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_DECLINE"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_LOPPY = new DialogueNode("结束", "挣脱耷耷的魔爪。", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Loppy.class)) == 0) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_AFTER_SEX_NO_ORGASM");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_AFTER_SEX");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	
	public static final DialogueNode AFTER_SEX_LOPPY_THREESOME = new DialogueNode("结束", "挣脱耷耷的魔爪。", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_AFTER_THREESOME");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ANGELS_KISS_OFFICE = new DialogueNode("安吉尔的办公室", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelsOfficeIntroduced);
		}
		
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelsOfficeIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE");
			} else {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
					return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE_REPEAT");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE_REPEAT_WITH_LICENSE");
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelsOfficeIntroduced)) {
				if (index == 1) {
					return new Response("继续", "既然安吉尔向你提出了她的交易，你可以问一些其他的问题……", ANGELS_KISS_OFFICE_CONTINUE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.angelsOfficeIntroduced, true);
							Main.game.updateResponses();
						}
					};
				}
				
			} else {
				if (index == 1) {
					return new Response("卖淫", "问问安吉尔御城区有关卖淫的法律。", ANGELS_KISS_OFFICE_PROSTITUTION);
					
				} else if (index == 2) {
					return new Response("了解过往", "打听安吉尔的过去，了解她是怎么拥有天使之吻的。", ANGELS_KISS_OFFICE_BACKGROUND);
					
				} else if (index == 3) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
						if(Main.game.getPlayer().getMoney()<5000) {
							return new Response("许可证("+UtilText.formatAsMoney(5000, "span")+")", "你的钱不足以购入卖淫许可证！", null);
						} else {
							return new Response("许可证("+UtilText.formatAsMoney(5000, "span")+")", "同意安吉尔的交易，买下卖淫许可证。", ANGELS_KISS_OFFICE_LICENSE_PURCHASE) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.prostitutionLicenseObtained, true);
									Main.game.getPlayer().incrementMoney(-5000);
								}
							};
						}
						
					} else if(Main.game.getDialogueFlags().hasFlag("acexp_horny_angel_found")) {
						return DialogueManager.getDialogueFromId("acexp_dominion_angel_office_misc_sex_access_node").getResponse(0, 1);
					}
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ANGELS_KISS_OFFICE_CONTINUE = new DialogueNode("安吉尔的办公室", "。", false, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE_CONTINUE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ANGELS_KISS_OFFICE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ANGELS_KISS_OFFICE_PROSTITUTION = new DialogueNode("安吉尔的办公室", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE_PROSTITUTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("卖淫", "你已经跟安吉尔谈过卖淫的事情了。", null);
				
			} else {
				return ANGELS_KISS_OFFICE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_OFFICE_BACKGROUND = new DialogueNode("安吉尔的办公室", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE_BACKGROUND");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 2) {
				return new Response("了解过往", "你正在听安吉尔讲述她的过去。", null);
				
			} else {
				return ANGELS_KISS_OFFICE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_OFFICE_LICENSE_PURCHASE = new DialogueNode("安吉尔的办公室", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE_LICENSE_PURCHASE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ANGELS_KISS_OFFICE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ANGELS_KISS_PROSTITUTE_REMOVAL_PAID = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			NPC npc = charactersPresent.get(0);
			UtilText.addSpecialParsingString(Util.intToString(AlleywayProstituteDialogue.getModifiedFineAmount(npc)), true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "PROSTITUTE_REMOVAL_PAID", npc));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-AlleywayProstituteDialogue.getModifiedFineAmount(npc)));
			Main.game.banishNPC(npc);
		}
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "你对能帮到御城区陷入困境的市民感到高兴，继续出发……", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static List<NPC> getProstitutes(boolean includeSlaves) {
		List<NPC> prostitutes = new ArrayList<>();
		Cell[][] grid = Main.game.getWorlds().get(WorldType.ANGELS_KISS_GROUND_FLOOR).getGrid();
		for(int i = 0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)) {
					List<NPC> charactersPresent = Main.game.getCharactersPresent(WorldType.ANGELS_KISS_GROUND_FLOOR, new Vector2i(i, j));
					charactersPresent.removeIf(NPC->NPC.getHistory() != Occupation.NPC_PROSTITUTE);
					if(!charactersPresent.isEmpty()) {
						prostitutes.add(charactersPresent.get(0));
					}
				}
			}
		}
		if (includeSlaves) {
			grid = Main.game.getWorlds().get(WorldType.ANGELS_KISS_FIRST_FLOOR).getGrid();
			for(int i = 0; i<grid.length; i++) {
				for(int j=0; j<grid[0].length; j++) {
					if(grid[i][j].getPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)) {
						List<NPC> charactersPresent = Main.game.getCharactersPresent(WorldType.ANGELS_KISS_FIRST_FLOOR, new Vector2i(i, j));
						charactersPresent.removeIf(NPC->!NPC.isSlave());
						if (!charactersPresent.isEmpty()) {
							prostitutes.add(charactersPresent.get(0));
						}
					}
				}
			}
		}
		return prostitutes;
	}
	
	public static List<NPC> getVisitors() {
		List<NPC> visitors = new ArrayList<>();
		Cell[][] grid = Main.game.getWorlds().get(WorldType.ANGELS_KISS_GROUND_FLOOR).getGrid();
		for(int i = 0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)) {
					List<NPC> charactersPresent = Main.game.getCharactersPresent(WorldType.ANGELS_KISS_GROUND_FLOOR, new Vector2i(i, j));
					visitors.addAll(charactersPresent);
				}
			}
		}
		grid = Main.game.getWorlds().get(WorldType.ANGELS_KISS_FIRST_FLOOR).getGrid();
		for(int i = 0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)) {
					List<NPC> charactersPresent = Main.game.getCharactersPresent(WorldType.ANGELS_KISS_FIRST_FLOOR, new Vector2i(i, j));
					visitors.addAll(charactersPresent);
				}
			}
		}
		visitors.removeIf(npc->
				!(npc instanceof GenericSexualPartner)
				|| npc.isSlave()
				|| Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()));
		return visitors;
	}
	
	public static boolean isSpaceForMoreProstitutes() {
		return getProstitutes(false).size()<10;
	}
	
	public static void prostituteUpdate() {
		for (NPC visitor : getVisitors()) {
			if(!visitor.equals(Main.game.getActiveNPC())) { // Do not delete NPCs who are visiting the player when the player is whoring themselves out, as they get cleaned up automatically
				Main.game.banishNPC(visitor);
			}
		}
		for (NPC prostitute : getProstitutes(true)) {
			if (Main.game.isLipstickMarkingEnabled()
					&& !prostitute.isSlave()
					&& !Main.game.getPlayer().getFriendlyOccupants().contains(prostitute.getId())
					&& prostitute.getLipstick().getPrimaryColour() != PresetColour.COVERING_NONE) {
				prostitute.addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
			}
			
//			List<NPC> charactersPresent = new ArrayList<>(Main.game.getCharactersPresent(prostitute.getWorldLocation(), prostitute.getLocation()));
//			charactersPresent.removeAll(Main.game.getPlayer().getCompanions());
//			charactersPresent.remove(prostitute);
//			if(!charactersPresent.isEmpty()) {
//				for(NPC npc : charactersPresent) {
//					if(npc instanceof GenericSexualPartner) {
//						Main.game.banishNPC(npc);
//					}
//				}
//				
//			}
			if(Math.random()<0.33f) { // Add client:
				GenericSexualPartner partner = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, false), prostitute.getWorldLocation(), prostitute.getLocation(), false);
				try {
					Main.game.addNPC(partner, false, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
