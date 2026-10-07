package com.lilithsthrone.game.dialogue.places.dominion.nyansApartment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.NyanMum;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.nyan.SMNyanSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class NyanApartment {

	private static GameCharacter activeSexPartner;
	
	public static GameCharacter getActiveSexPartner() {
		if(activeSexPartner==null) {
			return getNyan();
		}
		return activeSexPartner;
	}

	public static void setActiveSexPartner(GameCharacter activeSexPartner) { //TODO remember to call this...
		NyanApartment.activeSexPartner = activeSexPartner;
	}

	private static Nyan getNyan() {
		return ((Nyan)Main.game.getNpc(Nyan.class));
	}

	private static NyanMum getNyanMum() {
		return ((NyanMum)Main.game.getNpc(NyanMum.class));
	}
	
	private static void travelTo(AbstractWorldType worldType, AbstractPlaceType placeType) {
		Main.game.getPlayer().setLocation(worldType, placeType);
		getNyan().setLocation(Main.game.getPlayer(), false);
	}

	public static final DialogueNode VISIT_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "VISIT_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "HALLWAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ENTRANCE_HALL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "ENTRANCE_HALL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode NYAN_BEDROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "NYAN_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ENSUITE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "ENSUITE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode SPARE_BEDROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SPARE_BEDROOM");
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "BATHROOM");
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "KITCHEN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode DINING_ROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DINING_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode LOUNGE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "LOUNGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SOLO_SEX_FOREPLAY = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanCreampied, false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			responses.add(
				new ResponseSex("舔阴", "舔舐她的阴部，给淫荡小猫女想要的。",
					true, true,
					new SMNyanSex(
							SexPosition.SITTING,
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL)),
							Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotSitting.SITTING))) {
						@Override
						public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(character.isPlayer()) {
								return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
							} else {
								return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
							}
						}
						@Override
						public boolean isCharacterStartNaked(GameCharacter character) {
							return character.isPlayer();
						}
					},
					null,
					null,
					SOLO_SEX_MAIN,
					UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
				@Override
				public List<InitialSexActionInformation> getInitialSexActions() {
					return Util.newArrayListOfValues(
							new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true));
				}
			});
			
			responses.add(
				new ResponseSex("指交", UtilText.parse(getActiveSexPartner(), "把[npc.name]压倒在床，边亲吻边指交。"),
					true, true,
					new SMNyanSex(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN_TWO)),
							Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotLyingDown.LYING_DOWN))) {
						@Override
						public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(character.isPlayer()) {
								return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
							} else {
								return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
							}
						}
						@Override
						public boolean isCharacterStartNaked(GameCharacter character) {
							return character.isPlayer();
						}
					},
					null,
					null,
					SOLO_SEX_MAIN,
					UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_FINGERING" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
				@Override
				public List<InitialSexActionInformation> getInitialSexActions() {
					return Util.newArrayListOfValues(
							new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueMouth.KISS_START, false, true),
							new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), FingerVagina.FINGERING_START, false, true));
				}
			});
			
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("六九式"+(Main.game.getPlayer().hasVagina()?"(口交)":""),
							UtilText.parse(getActiveSexPartner(), "你无法控制自己的肉棒，你不能和[npc.name]玩六九式！"),
							null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("六九式"+(Main.game.getPlayer().hasVagina()?"(口交)":""), "因为你[pc.legRace]的下半身，你不能采用这个体位！", null));
							
				} else {
					responses.add(
							new ResponseSex("六九式"+(Main.game.getPlayer().hasVagina()?" (blowjob)":""),
									UtilText.parse(getActiveSexPartner(), "让[npc.name]跨坐在你脸上，她屈身吸吮你的鸡巴，你也舔着她的下面。"),
									true, true,
									new SMNyanSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotLyingDown.SIXTY_NINE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_SIXTY_NINE_BLOWJOB" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response("六九式"+(Main.game.getPlayer().hasPenis()?"(舔阴)":""),
							UtilText.parse(getActiveSexPartner(), "你无法控制自己的阴部，你不能和[npc.name]玩六九式！"),
							null)); 
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("六九式"+(Main.game.getPlayer().hasVagina()?" (舔阴)":""), "因为你[pc.legRace]的下半身，你不能采用这个体位！", null));
							
				} else {
					responses.add(
							new ResponseSex("六九式"+(Main.game.getPlayer().hasPenis()?"(舔阴)":""),
									UtilText.parse(getActiveSexPartner(), "让[npc.name]跨坐在你脸上，她屈身舔着你的下面，你也回敬她。"),
									true, true,
									new SMNyanSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotLyingDown.SIXTY_NINE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_SIXTY_NINE_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("被口交",
							UtilText.parse(getActiveSexPartner(), "你无法控制自己的阴茎，你不能让[npc.name]吸你的鸡巴！"),
							null)); 
				} else {
					responses.add(
							new ResponseSex("被口交",
									UtilText.parse(getActiveSexPartner(), "让[npc.name]吸你的鸡巴。"),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotSitting.PERFORMING_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_BLOWJOB" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "[npc.Paizuri]"),
							UtilText.parse(getActiveSexPartner(), "你无法控制自己的阴茎，所以你没法让[npc.name]给你乳交！"),
							null)); 
				} else {
					responses.add(
							new ResponseSex(UtilText.parse(getActiveSexPartner(), "[npc.Paizuri]"),
									UtilText.parse(getActiveSexPartner(),
											getActiveSexPartner().isBreastFuckablePaizuri()
												?"让[npc.name]跪下，露出她[npc.breasts+]包裹你的鸡巴，你和她乳交。"
												:"让[npc.name]跪下，露出她[npc.breasts+]包裹你的鸡巴，你能在乳穴里抽插。"),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotSitting.PERFORMING_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.BREAST);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.NIPPLES)));
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_PAIZURI" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisBreasts.FUCKING_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response("被舔阴",
							UtilText.parse(getActiveSexPartner(), "你的阴道不可被触及，所以你不能让[npc.Name]给你舔阴！"),
							null)); 
				} else {
					responses.add(
							new ResponseSex("被舔阴",
									UtilText.parse(getActiveSexPartner(), "让[npc.name]舔舐你的下体。"),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotSitting.PERFORMING_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_RECEIVE_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			
			responses.add(
					new ResponseSex("颜面骑乘",
							UtilText.parse(getActiveSexPartner(), "让[npc.name]骑在你脸上，你舔舐着她的下体。"), 
							true, true,
							new SMNyanSex(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
									Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotLyingDown.FACE_SITTING))) {
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
								}
								@Override
								public boolean isCharacterStartNaked(GameCharacter character) {
									return character.isPlayer();
								}
							},
							null,
							null,
							SOLO_SEX_MAIN,
							UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_FACE_SITTING" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});

			if(Main.game.isAnalContentEnabled()) {
				if((getActiveSexPartner() instanceof Nyan) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk)) {
						responses.add(new Response("提议肛交", "问问妮安愿不愿意跟你尝试肛门的新玩法。", SOLO_FOREPLAY_ANAL_TALK));
					
				} else if((getActiveSexPartner() instanceof NyanMum) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumAnalTalk)) {
					responses.add(new Response("提议肛交", "问问[nyanmum.name]愿不愿意跟你尝试肛门的新玩法。", SOLO_FOREPLAY_ANAL_TALK));
					
				} else {
					responses.add(
							new ResponseSex("吻肛",
								"让淫荡的猫女向你展示屁股，为她吻肛。",
								true, true,
								new SMNyanSex(
										SexPosition.ALL_FOURS,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND_ORAL)),
										Util.newHashMapOfValues(
												new Value<>(getActiveSexPartner(), SexSlotAllFours.ALL_FOURS))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
										}
									}
									@Override
									public boolean isCharacterStartNaked(GameCharacter character) {
										return true;
									}
									@Override
									public SexControl getSexControl(GameCharacter character) {
										if(character.isPlayer()) {
											return SexControl.FULL;
										}
										return SexControl.ONGOING_ONLY;
									}
								},
								null,
								null,
								SOLO_SEX_MAIN,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_PERFORM_ANILINGUS"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueAnus.ANILINGUS_START, false, true));
							}
						});
				}
			}
			if(index > 0 && index - 1 < responses.size()) {
				return responses.get(index - 1);
			}
			return null;
		}
	};

	public static final DialogueNode SOLO_FOREPLAY_ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(getActiveSexPartner() instanceof Nyan) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumAnalTalk, true);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_ANAL_TALK"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SOLO_SEX_FOREPLAY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SOLO_SEX_MAIN = new DialogueNode("继续", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getActiveSexPartner(), "[npc.Name]已经享受够前戏了……");
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("传教士体位", UtilText.parse(getActiveSexPartner(), "因为你不能使用自己的阴茎，所以你没法操[npc.name]！"), null)); 
					
				} else {
					responses.add(
							new ResponseSex("传教士体位",
									getActiveSexPartner() instanceof Nyan && getNyan().isVaginaVirgin()
										?"在夺走妮安的贞操前，先让她躺下，向你展示自己。"
										:UtilText.parse(getActiveSexPartner(), "在操她前，先让[npc.name]向后躺下，向你展示自己。"),
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotLyingDown.LYING_DOWN))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_MISSIONARY" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("后入式", UtilText.parse(getActiveSexPartner(), "因为你不能使用自己的阴茎，所以你不能操[npc.name]！"), null)); 
					
				} else {
					responses.add(
							new ResponseSex("后入式",
									getActiveSexPartner() instanceof Nyan && getNyan().isVaginaVirgin()
										?"在夺走妮安的贞操之前，让她先四肢着地，向你展示自己。"
										:UtilText.parse(getActiveSexPartner(), "在操[npc.Name]前，让她先四肢着地，向你展示自己。"),
									true, true,
									new SMNyanSex(
											SexPosition.ALL_FOURS,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotAllFours.ALL_FOURS))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_DOGGY" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("骑乘位", UtilText.parse(getActiveSexPartner(), "因为你不能使用自己的阴茎，所以你没法操[npc.Name]！"), null)); 
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("骑乘位", "因为你[pc.legRace]的下半身，你不能采用这个体位……", null)); 
					
				} else {
					responses.add(
							new ResponseSex("骑乘位",
									getActiveSexPartner() instanceof Nyan && getNyan().isVaginaVirgin()
										?"让她自己骑上你的阴茎，给出贞操。"
										:UtilText.parse(getActiveSexPartner(), "让[npc.name]用骑乘位骑上你的阴茎。"),
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotLyingDown.COWGIRL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_COWGIRL" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getActiveSexPartner(), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("被口交", "你的阴茎不可被触及，所以你没法被口交！", null)); 
				} else {
					responses.add(
							new ResponseSex("接受口交", UtilText.parse(getActiveSexPartner(), "让[npc.name]口你的阴茎。"),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotLyingDown.MISSIONARY_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_BLOWJOB" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getActiveSexPartner(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "跟([npc.name]玩剪刀式体位，磨蹭下体。)"),
							UtilText.parse(getActiveSexPartner(), "你的阴部不可被触及，所以你不能和[npc.name]玩剪刀式！"),
							null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "剪刀式体位([npc.name]在下)"), "因为你[pc.legRace]的下半身，你不能采用这个体位……", null));
					
				} else {
					responses.add(
							new ResponseSex(UtilText.parse(getActiveSexPartner(), "跟[npc.name]玩剪刀式体位，磨蹭下体。"),
									UtilText.parse(getActiveSexPartner(), "让[npc.name]躺好，跟她玩剪刀式体位。"),
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.SCISSORING)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotLyingDown.LYING_DOWN))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_SCISSOR_TOP" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), ClitClit.TRIBBING_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "剪刀式([npc.name]在上)"), UtilText.parse(getActiveSexPartner(), "你的阴部不可触及，所以你不能和[npc.name]玩剪刀式！"), null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "剪刀式体位([npc.name]在上)"), "因为你[pc.legRace]的下半身，你不能采用这个体位……", null));
					
				} else {
					responses.add(
							new ResponseSex(UtilText.parse(getActiveSexPartner(), "剪刀式体位([npc.name]在上)"),
									UtilText.parse(getActiveSexPartner(), "躺好，让[npc.name]跟你用剪刀式体位做。"),
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotLyingDown.SCISSORING))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_SCISSOR_BOTTOM" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), ClitClit.TRIBBING_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("被舔阴", "你的阴部不可触及，所以你不能被舔阴！", null)); 
					
				} else {
					responses.add(
							new ResponseSex("被舔阴",
									UtilText.parse(getActiveSexPartner(), "让[npc.name]舔舐你的下体。"),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotLyingDown.MISSIONARY_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_RECEIVE_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
				responses.add(
						new ResponseSex("舔阴",
							"让欲火中烧的猫女展示小穴，将她舔上高潮。",
							true, true,
							new SMNyanSex(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY_ORAL)),
									Util.newHashMapOfValues(
											new Value<>(getActiveSexPartner(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
								}
								@Override
								public boolean isCharacterStartNaked(GameCharacter character) {
									return true;
								}
								@Override
								public SexControl getSexControl(GameCharacter character) {
									if(character.isPlayer()) {
										return SexControl.FULL;
									}
									return SexControl.ONGOING_ONLY;
								}
							},
							null,
							null,
							POST_SOLO_SEX,
							UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_PERFORM_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if((getActiveSexPartner() instanceof Nyan) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk)) {
						responses.add(new Response("提议肛交", "问问妮安愿不愿意跟你尝试肛门的新玩法。", SOLO_SEX_ANAL_TALK));
					
				} else if((getActiveSexPartner() instanceof NyanMum) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumAnalTalk)) {
					responses.add(new Response("Ask about anal", "问问[nyanmum.name]愿不愿意跟你尝试肛门的新玩法。", SOLO_SEX_ANAL_TALK));
					
				} else {
					responses.add(
							new ResponseSex("吻肛",
								"让饥渴的猫女展示菊穴，为她舔肛。",
								true, true,
								new SMNyanSex(
										SexPosition.ALL_FOURS,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND_ORAL)),
										Util.newHashMapOfValues(
												new Value<>(getActiveSexPartner(), SexSlotAllFours.ALL_FOURS))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
										}
									}
									@Override
									public boolean isCharacterStartNaked(GameCharacter character) {
										return true;
									}
									@Override
									public SexControl getSexControl(GameCharacter character) {
										if(character.isPlayer()) {
											return SexControl.FULL;
										}
										return SexControl.ONGOING_ONLY;
									}
								},
								null,
								null,
								POST_SOLO_SEX,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_PERFORM_ANILINGUS"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueAnus.ANILINGUS_START, false, true));
							}
						});
					if(Main.game.getPlayer().hasPenis()) {
						if(!penisAccess) {
							responses.add(new Response("肛交", UtilText.parse(getActiveSexPartner(), "因为你不能使用自己的阴茎，所以你不能操[npc.name]！"), null)); 
							
						} else {
							responses.add(
									new ResponseSex("肛交",
											getActiveSexPartner() instanceof Nyan && getNyan().isAssVirgin()
												?"让妮安四肢着地，献上自己，你可以夺取她的肛门童贞。"
												:"让[pc.name]四肢着地，献上自己，你可以操她的屁眼。",
											true, true,
											new SMNyanSex(
													SexPosition.ALL_FOURS,
													Util.newHashMapOfValues(
															new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
													Util.newHashMapOfValues(
															new Value<>(getActiveSexPartner(), SexSlotAllFours.ALL_FOURS))) {
												@Override
												public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
													if(character.isPlayer()) {
														return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
													} else {
														return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
													}
												}
												@Override
												public boolean isCharacterStartNaked(GameCharacter character) {
													return true;
												}
												@Override
												public SexControl getSexControl(GameCharacter character) {
													if(character.isPlayer()) {
														return SexControl.FULL;
													}
													return SexControl.ONGOING_ONLY;
												}
											},
											null,
											null,
											POST_SOLO_SEX,
											UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_ANAL" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											return Util.newArrayListOfValues(
													new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisAnus.PENIS_FUCKING_START, false, true));
										}
									});
						}
					}
				}
			}
			
			if(index > 0 && index - 1 < responses.size()) {
				return responses.get(index - 1);
			}
			return null;
		}
	};

	public static final DialogueNode SOLO_SEX_ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(getActiveSexPartner() instanceof Nyan) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumAnalTalk, true);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_ANAL_TALK"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SOLO_SEX_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode POST_SOLO_SEX = new DialogueNode("完成", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getActiveSexPartner(), "[npc.Name]满意地结束了性爱……");
		}
		@Override
		public void applyPreParsingEffects() {
			getActiveSexPartner().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanCreampied, getNyan().hasStatusEffect(StatusEffect.PREGNANT_0));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_SOLO_SEX" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("睡觉", UtilText.parse(getActiveSexPartner(), "让[npc.name]和你一起在她床上休息。"), POST_SOLO_SEX_SLEEP);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_SOLO_SEX_SLEEP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
			getNyan().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
				getNyanMum().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			}
			Main.game.getPlayer().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_SOLO_SEX_SLEEP" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("厨房", "走向厨房……", POST_SOLO_SEX_KITCHEN);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_SOLO_SEX_KITCHEN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().equipClothing();
			getNyan().wearApron(true);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().wearCasual();
			}
			Main.game.getPlayer().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanCreampied)) {
				UtilText.addSpecialParsingString(ItemEffectType.PREGNANCY_TEST.applyEffect(null, null, null, 0, getNyan(), getNyan(), null), true);
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied)) {
				UtilText.addSpecialParsingString(ItemEffectType.PREGNANCY_TEST.applyEffect(null, null, null, 0, getNyanMum(), getNyanMum(), null), true);
			}
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_SOLO_SEX_KITCHEN"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM")));
//			if((Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanCreampied) && getNyan().isPregnant())
//					|| (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied) && getNyanMum().isPregnant())) {
//				sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "PREGNANCY_ADDITION"));
//			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("告别",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)
							?"亲亲妮安，和[nyanmum.name]来告别，告诉对方很快就会再见。"
							:"和妮安吻别，跟她说你很快会回来见她。",
						POST_SEX_MORNING_NO_CONTENT) {
					@Override
					public void effects() {
						getNyan().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						getNyan().wearApron(false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_SOLO_SEX_LEAVE"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM")));
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_SEX_MORNING_NO_CONTENT = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			}
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NYAN_APARTMENT);
		}
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
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOUBLE_SEX_FOREPLAY = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanCreampied, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumCreampied, false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(index==1) {
				return new ResponseSex("舔阴", "让淫荡的猫女们向你展示阴部，为她们舔阴。",
						true, true,
						new SMNyanSex(
								SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY_ORAL)),
								Util.newHashMapOfValues(
										new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
										new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								return getMainSexPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
								} else if(character instanceof Nyan) {
									if(targetedCharacter.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
									}
								} else {
									if(targetedCharacter.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
									}
								}
							}
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								return partner instanceof NyanMum && Main.sex.getNumberOfOrgasms(getNyan())>=1 && Main.sex.getNumberOfOrgasms(getNyanMum())>=1;
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.isPlayer();
							}
						},
						null,
						null,
						DOUBLE_SEX_MAIN,
						UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true),
								new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("指交", "把饥渴的猫女们推倒在[nyanmum.namePos]的床上，开始为她们指交。",
						true, true,
						new SMNyanSex(
								SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY_ORAL)),
								Util.newHashMapOfValues(
										new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
										new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								return getMainSexPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
								}
							}
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								return partner instanceof NyanMum && Main.sex.getNumberOfOrgasms(getNyan())>=1 && Main.sex.getNumberOfOrgasms(getNyanMum())>=1;
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.isPlayer();
							}
						},
						null,
						null,
						DOUBLE_SEX_MAIN,
						UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_FINGERING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueMouth.KISS_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), FingerVagina.FINGERING_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), FingerVagina.FINGERING_START, false, true));
					}
				};
			}
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("被口交", "你的阴茎不可被触及，所以你没法被口交！", null)); 
				} else {
					responses.add(
							new ResponseSex("被口交", "让妮安和[nyanmum.name]为你口交。",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.SITTING,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotSitting.PERFORMING_ORAL),
													new Value<>(getNyanMum(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_TWO:SexSlotSitting.PERFORMING_ORAL_TWO))) {
										@Override
										public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
											return getMainSexPreference(character, targetedCharacter);
										}
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isPartnerWantingToStopSex(GameCharacter partner) {
											return partner instanceof NyanMum && Main.sex.isOrgasmCountMet(Main.game.getPlayer(), 1, true);
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									DOUBLE_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_RECEIVE_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START_ADDITIONAL, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response("被舔阴", "你的阴部不可触及，所以你不能被舔阴！", null)); 
				} else {
					responses.add(
							new ResponseSex("舔阴", "让妮安和[nyanmum.name]舔你小穴。",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotLyingDown.MISSIONARY_ORAL),
													new Value<>(getNyanMum(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO:SexSlotLyingDown.MISSIONARY_ORAL_TWO))) {
										@Override
										public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
											return getMainSexPreference(character, targetedCharacter);
										}
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public boolean isPartnerWantingToStopSex(GameCharacter partner) {
											return partner instanceof NyanMum && Main.sex.isOrgasmCountMet(Main.game.getPlayer(), 1, true);
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									DOUBLE_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_RECEIVE_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START_ADDITIONAL, false, true));
								}
							});
				}
			}
			if(Main.game.isAnalContentEnabled()) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumAnalTalk)) {
					responses.add(new Response("提议肛交", "问问妮安和[nyanmum.name]愿不愿意跟你尝试肛门的新玩法。", DOUBLE_FOREPLAY_ANAL_TALK));
					
				} else {
					responses.add(
							new ResponseSex("吻肛", "让这些饥渴的猫女们为你展现肛穴，给她们舔肛。",
								true, true,
								new SMNyanSex(
										SexPosition.ALL_FOURS,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND_ORAL)),
										Util.newHashMapOfValues(
												new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
												new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else if(character instanceof Nyan) {
											if(targetedCharacter.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
											}
										} else {
											if(targetedCharacter.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
											}
										}
									}
									@Override
									public boolean isPartnerWantingToStopSex(GameCharacter partner) {
										return partner instanceof NyanMum && Main.sex.getNumberOfOrgasms(getNyan())>=1 && Main.sex.getNumberOfOrgasms(getNyanMum())>=1;
									}
									@Override
									public boolean isCharacterStartNaked(GameCharacter character) {
										return character.isPlayer();
									}
								},
								null,
								null,
								DOUBLE_SEX_MAIN,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueAnus.ANILINGUS_START, false, true),
										new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
							}
						});
				}
			}
			
			if(index > 0 && index - 3 < responses.size()) {
				return responses.get(index - 3);
			}
			return null;
		}
	};

	public static final DialogueNode DOUBLE_FOREPLAY_ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumAnalTalk, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_ANAL_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOUBLE_SEX_FOREPLAY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOUBLE_SEX_MAIN = new DialogueNode("继续", "妮安和[nyanmum.name]看起来已经享受够前戏了……", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("传教士体位(妮安)", "因为你不能使用自己的阴茎，所以你没法操妮安或者[nyanmum.name]！", null)); 
					
				} else {
					responses.add(
							new ResponseSex("传教士体位(妮安)",
									getNyan().isVaginaVirgin()
										?"让妮安和[nyanmum.name]在床上躺好，展示胴体，然后夺走妮安的贞操。"
										:"让妮安和[nyanmum.name]在床上躺好，向你呈现小穴，然后插入妮安的小穴。",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
													new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_MISSIONARY_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisVagina.PENIS_FUCKING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyanMum(), SelfFingerVagina.SELF_FINGER_VAGINA_PENETRATION, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("传教士体位([nyanmum.name])", "因为你不能使用自己的阴茎，所以你没法操妮安或者[nyanmum.name]！", null)); 
					
				} else {
					responses.add(
							new ResponseSex("传教士体位([nyanmum.name])", "让妮安和[nyanmum.name]在床上躺好，向你展示小穴，然后插入[nyanmum.namePos][nyanmum.pussy+]。",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
													new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_MISSIONARY_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), PenisVagina.PENIS_FUCKING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyan(), getNyan(), SelfFingerVagina.SELF_FINGER_VAGINA_PENETRATION, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("后入式(妮安)", "因为你无法控制自己的阴茎，所以你不能操妮安或者[nyanmum.name]！", null)); 
					
				} else {
					responses.add(
							new ResponseSex("后入式(妮安)",
									getNyan().isVaginaVirgin()
										?"让妮安和[nyanmum.name]四肢着地，向你献上自己，然后夺走妮安的贞操。"
										:"让妮安和[nyanmum.name]四肢着地，向你献上自己，然后插入妮安的小穴。",
									true, true,
									new SMNyanSex(
											SexPosition.ALL_FOURS,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
													new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_DOGGY_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisVagina.PENIS_FUCKING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyanMum(), SelfFingerVagina.SELF_FINGER_VAGINA_PENETRATION, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("后入式([nyanmum.name])", "因为你不能使用自己的阴茎，所以你没法操妮安或[nyanmum.name]！", null)); 
					
				} else {
					responses.add(
							new ResponseSex("后入式([nyanmum.name])", "让妮安和[nyanmum.name]四肢着地，向你展示小穴，然后插入[nyanmum.namePos][nyanmum.pussy+]。",
									true, true,
									new SMNyanSex(
											SexPosition.ALL_FOURS,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
													new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_DOGGY_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), PenisVagina.PENIS_FUCKING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyan(), getNyan(), SelfFingerVagina.SELF_FINGER_VAGINA_PENETRATION, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("骑乘位(妮安)", "因为你不能使用自己的阴茎，所以你没法操妮安或者[nyanmum.name]！", null)); 
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("骑乘位(妮安)", "因为你[pc.legRace]的下半身，你不能采用这个体位……", null)); 
					
				} else {
					responses.add(
							new ResponseSex("骑乘位(妮安)",
									getNyan().isVaginaVirgin()
										?"夺走妮安的贞操，让她骑上你的阴茎，同时让[nyanmum.name]坐在你的脸上。"
										:"让妮安骑上你的阴茎，同时让[nyanmum.name]坐在你的脸上。",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.COWGIRL),
													new Value<>(getNyanMum(), SexSlotLyingDown.FACE_SITTING_REVERSE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_COWGIRL_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("骑乘位([nyanmum.name])", "因为你不能使用自己的阴茎，所以你不能操妮安或[nyanmum.name]！", null)); 
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("骑乘位([nyanmum.name])", "因为你[pc.legRace]的下半身，你不能采用这个体位……", null)); 
					
				} else {
					responses.add(
							new ResponseSex("骑乘位([nyanmum.name])", "让[nyanmum.name]骑上你的阴茎，同时让妮安坐在你的脸上。",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.FACE_SITTING_REVERSE),
													new Value<>(getNyanMum(), SexSlotLyingDown.COWGIRL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_COWGIRL_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("被口交", "你的阴茎不可被触及，所以你没法被口交！", null)); 
				} else {
					responses.add(
							new ResponseSex("被口交", "让妮安和[nyanmum.name]口你的阴茎。",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotLyingDown.MISSIONARY_ORAL),
													new Value<>(getNyanMum(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_TWO:SexSlotLyingDown.MISSIONARY_ORAL_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START_ADDITIONAL, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response("剪刀式(妮安在下)", "你的阴部不可触及，所以你没法和妮安玩剪刀式！", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("剪刀式体位(妮安在下)", "因为你[pc.legRace]的下半身，你不能采用这个体位……", null));
					
				} else {
					responses.add(
							new ResponseSex("剪刀式体位(妮安下体)", "让妮安躺好，跟她玩剪刀式，同时让[nyanmum.name]坐在她的脸上。",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.SCISSORING)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
													new Value<>(getNyanMum(), SexSlotLyingDown.FACE_SITTING_REVERSE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_SCISSOR_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), ClitClit.TRIBBING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), TongueMouth.KISS_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("剪刀式([nyanmum.name]在下)", "你的阴部不可被触及，所以你不能和[nyanmum.name]玩剪刀式！", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("剪刀式体位([nyanmum.name]在下)", "因为你[pc.legRace]的下半身，你不能采用这个体位……", null));
					
				} else {
					responses.add(
							new ResponseSex("剪刀式体位([nyanmum.name]下体)", "让[nyanmum.name]躺好，跟她玩剪刀式，同时让[nyan.name]坐在她的脸上。",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.SCISSORING)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.FACE_SITTING_REVERSE),
													new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_SCISSOR_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), ClitClit.TRIBBING_START, false, true),
											new InitialSexActionInformation(getNyan(), getNyanMum(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueMouth.KISS_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("剪刀式体位 (妮安在上)", "你的阴部不可触及，所以你没法跟妮安玩剪刀式体位！", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("剪刀式体位(妮安在上)", "因为你[pc.legRace]的下半身，你不能采用这个体位……", null));
					
				} else {
					responses.add(
							new ResponseSex("接受剪刀式(妮安)", "躺好，让妮安跟你玩剪刀式，同时让[nyanmum.name]坐在你的脸上。",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.SCISSORING),
													new Value<>(getNyanMum(), SexSlotLyingDown.FACE_SITTING_REVERSE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_SCISSORED_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), ClitClit.TRIBBING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("剪刀式体位([nyanmum.name]在上)", "你的阴部不可触及，所以你没法和[nyanmum.name]剪刀式体位！", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("剪刀式体位([nyanmum.name]在上)", "因为你[pc.legRace]的下半身，你不能采用这个体位……", null));
					
				} else {
					responses.add(
							new ResponseSex("剪刀式体位([nyanmum.name]在上)", "向后仰躺，让妮安和你剪刀式磨镜，[nyanmum.name]坐在你脸上。",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.FACE_SITTING_REVERSE),
													new Value<>(getNyanMum(), SexSlotLyingDown.SCISSORING))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_SCISSORED_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), ClitClit.TRIBBING_START, false, true),
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("被舔阴", "你的阴部不可触及，所以你不能被舔阴！", null)); 
					
				} else {
					responses.add(
							new ResponseSex("被舔阴", "让妮安和[nyanmum.name]舔舐你的下体。",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotLyingDown.MISSIONARY_ORAL),
													new Value<>(getNyanMum(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO:SexSlotLyingDown.MISSIONARY_ORAL_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE);
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_RECEIVE_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
				responses.add(
						new ResponseSex("舔阴", "让这些饥渴的猫女们向你展示小穴，给她们舔阴。",
							true, true,
							new SMNyanSex(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY_ORAL)),
									Util.newHashMapOfValues(
											new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
											new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
									} else if(character instanceof Nyan) {
										if(targetedCharacter.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
										}
									} else {
										if(targetedCharacter.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
										}
									}
								}
								@Override
								public boolean isCharacterStartNaked(GameCharacter character) {
									return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
								}
								@Override
								public SexControl getSexControl(GameCharacter character) {
									if(character.isPlayer()) {
										return SexControl.FULL;
									}
									return SexControl.ONGOING_ONLY;
								}
							},
							null,
							null,
							POST_DOUBLE_SEX,
							UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
						}
					});
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumAnalTalk)) {
					responses.add(new Response("寻求肛交", "询问妮安和[nyanmum.name]能不能和你做点玩弄菊花的事。", DOUBLE_SEX_ANAL_TALK));
					
				} else {
					responses.add(
							new ResponseSex("吻肛", "让这些欲火中烧的猫女展示菊穴，给她们舔肛。",
								true, true,
								new SMNyanSex(
										SexPosition.ALL_FOURS,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND_ORAL)),
										Util.newHashMapOfValues(
												new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
												new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else if(character instanceof Nyan) {
											if(targetedCharacter.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
											}
										} else {
											if(targetedCharacter.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
											}
										}
									}
									@Override
									public boolean isCharacterStartNaked(GameCharacter character) {
										return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
									}
									@Override
									public SexControl getSexControl(GameCharacter character) {
										if(character.isPlayer()) {
											return SexControl.FULL;
										}
										return SexControl.ONGOING_ONLY;
									}
								},
								null,
								null,
								POST_DOUBLE_SEX,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueAnus.ANILINGUS_START, false, true),
										new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
							}
						});
					
					if(Main.game.getPlayer().hasPenis()) {
						if(!penisAccess) {
							responses.add(new Response("肛交(妮安)", "你不能使用自己的阴茎，所以你没法操妮安的屁眼！", null)); 
							
						} else {
							responses.add(
									new ResponseSex("肛交(妮安)",
											getNyan().isAssVirgin()
												?"让妮安和[nyanmum.name]四肢着地，向你展示菊穴，然后夺走妮安菊穴的贞操。"
												:"让妮安和[nyanmum.name]四肢着地，向你展示肛穴，然后插入妮安的肛穴。",
											true, true,
											new SMNyanSex(
													SexPosition.ALL_FOURS,
													Util.newHashMapOfValues(
															new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
													Util.newHashMapOfValues(
															new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
															new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
												@Override
												public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
													if(character.isPlayer()) {
														if(targetedCharacter instanceof Nyan) {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
														}
													} else if(character instanceof Nyan) {
														if(targetedCharacter.isPlayer()) {
															return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
														}
													} else {
														if(targetedCharacter.isPlayer()) {
															return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
														}
													}
												}
												@Override
												public boolean isCharacterStartNaked(GameCharacter character) {
													return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
												}
												@Override
												public SexControl getSexControl(GameCharacter character) {
													if(character.isPlayer()) {
														return SexControl.FULL;
													}
													return SexControl.ONGOING_ONLY;
												}
											},
											null,
											null,
											POST_DOUBLE_SEX,
											UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_ANAL_NYAN")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											return Util.newArrayListOfValues(
													new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisAnus.PENIS_FUCKING_START, false, true),
													new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
													new InitialSexActionInformation(getNyanMum(), getNyanMum(), SelfFingerVagina.SELF_FINGER_VAGINA_PENETRATION, false, true));
										}
									});
						}
						if(!penisAccess) {
							responses.add(new Response("肛交([nyanmum.name])", "因为你不能使用自己的阴茎，所以你没法操[nyanmum.namePos]的屁眼！", null)); 
							
						} else {
							responses.add(
									new ResponseSex("肛交([nyanmum.name])",
											"让妮安和[nyanmum.name]四肢着地，向你展示菊穴，然后插入[nyanmum.namePos]的菊穴。",
											true, true,
											new SMNyanSex(
													SexPosition.ALL_FOURS,
													Util.newHashMapOfValues(
															new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
													Util.newHashMapOfValues(
															new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
															new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
												@Override
												public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
													if(character.isPlayer()) {
														if(targetedCharacter instanceof Nyan) {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
														}
													} else if(character instanceof Nyan) {
														if(targetedCharacter.isPlayer()) {
															return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
														}
													} else {
														if(targetedCharacter.isPlayer()) {
															return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
														}
													}
												}
												@Override
												public boolean isCharacterStartNaked(GameCharacter character) {
													return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
												}
												@Override
												public SexControl getSexControl(GameCharacter character) {
													if(character.isPlayer()) {
														return SexControl.FULL;
													}
													return SexControl.ONGOING_ONLY;
												}
											},
											null,
											null,
											POST_DOUBLE_SEX,
											UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_ANAL_NYANMUM")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											return Util.newArrayListOfValues(
													new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), PenisAnus.PENIS_FUCKING_START, false, true),
													new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
													new InitialSexActionInformation(getNyan(), getNyan(), SelfFingerVagina.SELF_FINGER_VAGINA_PENETRATION, false, true));
										}
									});
						}
					}
				}
			}
			if(index==0 && responses.size()>14) {
				return responses.get(14);
			}
			if(index > 0) {
				if(index>14) {
					if(index < responses.size()) {
						return responses.get(index);
					}
				} else if(index - 1 < responses.size()) {
					return responses.get(index - 1);
				}
			}
			return null;
		}
	};

	public static final DialogueNode DOUBLE_SEX_ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumAnalTalk, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_ANAL_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOUBLE_SEX_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode POST_DOUBLE_SEX = new DialogueNode("结束", "妮安和[nyanmum.name]都已满足，该结束这场性爱了……", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			getNyanMum().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanCreampied, getNyan().hasStatusEffect(StatusEffect.PREGNANT_0));
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumCreampied, getNyanMum().hasStatusEffect(StatusEffect.PREGNANT_0));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_DOUBLE_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("睡觉", "让猫女们好好休息，跟她们一块入眠。", POST_DOUBLE_SEX_SLEEP);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DOUBLE_SEX_SLEEP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
			getNyan().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
				getNyanMum().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			}
			Main.game.getPlayer().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_DOUBLE_SEX_SLEEP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("厨房", "前往厨房……", POST_DOUBLE_SEX_KITCHEN);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DOUBLE_SEX_KITCHEN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().equipClothing();
			getNyan().wearApron(true);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().wearCasual();
			}
			Main.game.getPlayer().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied)) {
				UtilText.addSpecialParsingString(ItemEffectType.PREGNANCY_TEST.applyEffect(null, null, null, 0, getNyanMum(), getNyanMum(), null), true);
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanCreampied)) {
				UtilText.addSpecialParsingString(ItemEffectType.PREGNANCY_TEST.applyEffect(null, null, null, 0, getNyan(), getNyan(), null), !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied));
			} 
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_DOUBLE_SEX_KITCHEN"));
//			if((Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanCreampied) && getNyan().isPregnant())
//					|| (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied) && getNyanMum().isPregnant())) {
//				sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "PREGNANCY_ADDITION"));
//			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("道别", "跟妮安和[nyanmum.name]吻别，告诉她们你会很快再来见她们的。", POST_SEX_MORNING_NO_CONTENT) {
					@Override
					public void effects() {
						getNyan().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						getNyanMum().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						getNyan().wearApron(false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_DOUBLE_SEX_LEAVE"));
					}
				};
				
			}
			return null;
		}
	};
}
