package com.lilithsthrone.game.dialogue.places.dominion.helenaHotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFoot;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class HelenaHotel {
	
//	- Post-Helena's romance quest
//		Scarlett is present during extended work hours, but Helena is only there during normal work hours (she works late to 21:00 on Fridays)
//		If evening, and not an arcane storm:
//			Can go on date to an upmarket restaurant beneath Harpy Nests (turns out Helena owns it, and it's connected to her nest)
//			Scarlett accompanies, but has to wait out of sight
//				She says you don't have the opportunity to impress her by buying the meal; everything here is free for her
//				Affection increases instead by acting firmly but kindly to her
//				You can also lose affection by belittling her or implying that other harpies are more attractive (or other such obviously bad things)
//			Use actions to increase (or decrease) affection. Can increase by about 10 per date.
//				If have a rose, can give to her.
//				She only likes white roses. Offering any other colour decreases affection.
//			Can then ask out on date every Friday between 17:00 and 21:00
//			Once affection >=75, you can talk to Helena about her sex life. She is very flustered and hesitantly admits that she's never had sex. (Believing herself to be too good for anyone)
//			You can take her up to her penthouse apartment and have sex with her.
//				She suggests having Scarlett demonstrate what she wants on one of her maids. (If accept, the limits of what Helena wants are displayed to the player)
//					First time, she will only accept kissing/groping.
//						She gains oral fetishes afterwards
//					Second time, she comes out in lingerie, and allows you to play with her naked breasts (dominantly lets the player fuck her breasts if player has cock).
//						Gains self-breasts fetish
//					Third time, she lets you perform oral on her
//				After oral, she says that this is as far she wants to go - wants to keep her virginity
//					Can convince for giving you oral
//					Can convince for anal (hard if she is still virgin; if virginity taken, she is pretty slutty)
//						She gains anal receiving fetish afterwards (becoming lusty maiden)
//					Can ask Scarlett to set up romantic date in her apartment for taking her virginity
//						Can either let her dom at her own pace, or dom her
//							Gives her dom or sub fetish
//					After virginity taken
//						Wears black lingerie
//						Wears different dress - comments that it's easier to lift up for you
//						Unlocks sex in store, makes her a lot lewder
//						Can convince her to let Scarlett have sex with her as well
//							If convinced, can sometimes find store closed. Entering will lead to scene where Scarlett is fucking Helena (with strapon if female)
//							She says it would be nice for Scarlett to be bigger; other harpies prefer small feminine penises, but she wants to get fucked by a big cock (player has option to increase Scarlett's penis size)
//						Can be convinced to perform or receive under-table oral during date
	

	private static HelenaConversationTopic talkTopic = HelenaConversationTopic.SLAVES;
	private static boolean firstKissScene = false;
	
	private static SexManagerInterface getScarlettOralSexManager() {
		return new SexManagerDefault(
				false,
				SexPosition.SITTING,
				Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), SexSlotSitting.SITTING)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))) {
			@Override
			public boolean isPublicSex() {
				return false;
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				if(character.isPlayer()) {
					return SexControl.ONGOING_ONLY;
				}
				return super.getSexControl(character);
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return Util.newHashMapOfValues(
						new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA)),
						new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)));
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					if(Main.game.getNpc(Scarlett.class).hasPenis()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
					} else {
						return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
					}
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return getForeplayPreference(character, targetedCharacter);
				}
				return character.getMainSexPreference(targetedCharacter);
			}
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				if(!character.isPlayer()) {
					return OrgasmBehaviour.CREAMPIE; // Doesn't want to cover player in cum before Helena's date
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				return Main.sex.isSatisfiedFromOrgasms(partner, true);
			}
		};
	}
	
	private static String incrementHelenaAffection(float affection) {
		float currentAffection = Main.game.getNpc(Helena.class).getAffection(Main.game.getPlayer());
		if(affection<0) {
			if(currentAffection+affection < AffectionLevel.POSITIVE_TWO_LIKE.getMinimumValue()) {
				return ""; // Don't drop affection below 'like' level.
			}
		}
		if(currentAffection>=100) {
			return "";
		}
		return Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), affection);
	}

	public static SexManagerInterface getHelenaSexManager(boolean helenaDom,
			AbstractSexPosition position,
			SexSlot helenaSlot,
			SexSlot playerSlot,
			SexType helenaPreference,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return getHelenaSexManager(helenaDom, false, !helenaDom, position, helenaSlot, null, playerSlot, helenaPreference, null, null, null, exposeAtStartOfSexMap);
	}
	
	public static SexManagerInterface getHelenaSexManager(boolean helenaDom,
			boolean scarlettDom,
			boolean playerDom,
			AbstractSexPosition position,
			SexSlot helenaSlot,
			SexSlot scarlettSlot,
			SexSlot playerSlot,
			SexType helenaToPlayerPreference,
			SexType helenaToScarlettPreference,
			SexType scarlettToPlayerPreference,
			SexType scarlettToHelenaPreference,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(
				false,
				position,
				Util.newHashMapOfValues(
						(helenaDom
							?new Value<>(Main.game.getNpc(Helena.class), helenaSlot)
							:null),
						(scarlettDom && scarlettSlot!=null
								?new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)
								:null),
						(playerDom
								?new Value<>(Main.game.getPlayer(), playerSlot)
								:null)),
				Util.newHashMapOfValues(
						(!helenaDom
							?new Value<>(Main.game.getNpc(Helena.class), helenaSlot)
							:null),
						(!scarlettDom && scarlettSlot!=null
								?new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)
								:null),
						(!playerDom
								?new Value<>(Main.game.getPlayer(), playerSlot)
								:null))) {
			@Override
			public boolean isPlayerAbleToStopSex() {
				return helenaToPlayerPreference==null;
			}
			@Override
			public boolean isAbleToSkipSexScene() {
				return true;
			}
			@Override
			public boolean isPublicSex() {
				return false;
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
				return clothingToEquip.isCondom(); // Do not allow toy use
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return helenaToPlayerPreference==null;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return exposeAtStartOfSexMap;
			}
			@Override
			public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
				return character.equals(Main.game.getNpc(Helena.class));
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(character.equals(Main.game.getNpc(Helena.class))) {
					if(targetedCharacter.isPlayer() && helenaToPlayerPreference!=null) {
						return helenaToPlayerPreference;
					} else if(targetedCharacter.equals(Main.game.getNpc(Scarlett.class)) && helenaToScarlettPreference!=null) {
						return helenaToScarlettPreference;
					}
				} else if(character.equals(Main.game.getNpc(Scarlett.class))) {
					if(targetedCharacter.isPlayer() && scarlettToPlayerPreference!=null) {
						return scarlettToPlayerPreference;
					} else if(targetedCharacter.equals(Main.game.getNpc(Helena.class)) && scarlettToHelenaPreference!=null) {
						return scarlettToHelenaPreference;
					}
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(character.equals(Main.game.getNpc(Helena.class))) {
					if(targetedCharacter.isPlayer() && helenaToPlayerPreference!=null) {
						return helenaToPlayerPreference;
					} else if(targetedCharacter.equals(Main.game.getNpc(Scarlett.class)) && helenaToScarlettPreference!=null) {
						return helenaToScarlettPreference;
					}
				} else if(character.equals(Main.game.getNpc(Scarlett.class))) {
					if(targetedCharacter.isPlayer() && scarlettToPlayerPreference!=null) {
						return scarlettToPlayerPreference;
					} else if(targetedCharacter.equals(Main.game.getNpc(Helena.class)) && scarlettToHelenaPreference!=null) {
						return scarlettToHelenaPreference;
					}
				}
				return character.getMainSexPreference(targetedCharacter);
			}
			@Override
			public Map<GameCharacter, List<SexType>> getSexTypesBannedMap() {
				Map<GameCharacter, List<SexType>> bannedMap = new HashMap<>();
				bannedMap.put(Main.game.getNpc(Helena.class), new ArrayList<>());
				bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS));
				bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS));
				if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TENTACLE));
				}
				if(Main.game.getNpc(Helena.class).isAssVirgin()) {
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TAIL));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE));
				}
				if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE));
				}
				return bannedMap;
			}
			@Override
			public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
				if(helenaToPlayerPreference!=null
						&& helenaToPlayerPreference.getPerformingSexArea()==SexAreaOrifice.ANUS
						&& helenaToPlayerPreference.getTargetedSexArea()==SexAreaPenetration.PENIS) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getNpc(Helena.class), new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
					return map;
				}
				if(helenaToScarlettPreference!=null
						&& helenaToScarlettPreference.getPerformingSexArea()==SexAreaOrifice.ANUS
						&& helenaToScarlettPreference.getTargetedSexArea()==SexAreaPenetration.PENIS) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getNpc(Helena.class), new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Scarlett.class), Util.newHashSetOfValues(LubricationType.SALIVA));
					return map;
				}
				if(Main.game.getNpc(Helena.class).isVaginaVirgin()
						&& helenaToPlayerPreference!=null
						&& helenaToPlayerPreference.getPerformingSexArea()==SexAreaOrifice.VAGINA
						&& helenaToPlayerPreference.getTargetedSexArea()==SexAreaPenetration.PENIS) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getNpc(Helena.class), new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).put(SexAreaOrifice.VAGINA, new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).get(SexAreaOrifice.VAGINA).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
					map.get(Main.game.getNpc(Helena.class)).get(SexAreaOrifice.VAGINA).put(Main.game.getNpc(Helena.class), Util.newHashSetOfValues(LubricationType.GIRLCUM));
					return map;
				}
				return super.getStartingWetAreas();
			}
		};
	}

	
	
	// Generic dialogue:
	
	public static final DialogueNode HOTEL_TRAVEL_TO_NEST = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotel", "HOTEL_TRAVEL_TO_NEST");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.HARPY_NESTS_HELENAS_NEST.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HOTEL_TRAVEL_TO_DOMINION = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotel", "HOTEL_TRAVEL_TO_DOMINION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.DOMINION_HELENA_HOTEL.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	
	// Helena dates:
	
	public static final DialogueNode DATE_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().removeAllCompanions(true);
			// Reset date flags:
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetup, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetupEatenOut, false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("走吧", "和海伦娜走回她的公寓。", DATE_TRAVEL) {
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
					@Override
					public void effects() {
						if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HELENA_HOTEL)==null) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_WALK"));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_WALK_REPEAT"));
						}
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("飞行", "和海伦娜飞回她的公寓。", DATE_TRAVEL) {
						@Override
						public int getSecondsPassed() {
							return 5*60;
						}
						@Override
						public void effects() {
							if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HELENA_HOTEL)==null) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_FLY"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_FLY_REPEAT"));
							}
						}
					};
				}
				return new Response("飞行", "你没有飞行能力……", null);
				
			} else if(index==3) {
				if(Main.game.getPlayer().isTaur()) {
					return new Response("让她骑上来", "让海伦娜骑到你的背上，带她回到公寓。", DATE_TRAVEL) {
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public void effects() {
							if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HELENA_HOTEL)==null) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_RIDE"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_RIDE_REPEAT"));
							}
							Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
						}
					};
				}
				return new Response("让她骑上来", "你不是一个半兽身人，所以没办法让海伦娜骑到你的背上……", null);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode DATE_TRAVEL = new DialogueNode("金羽毛酒店", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Set place 2 tiles left of harpy nests as Helena's hotel:
			if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HELENA_HOTEL)==null) {
				Vector2i vec = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HARPY_NESTS_ENTRANCE).getLocation();
				vec.setX(vec.getX()-2);
				Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_HELENA_HOTEL);
				Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_HELENA_HOTEL.getName());
				Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setDiscovered(true);
				Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setTravelledTo(true);
			}
			
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HELENA_HOTEL);
			Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL");
		}
		@Override
		public Response getResponse(int responseTab, int index) { // Scarlett interactions:
			if(index==1) {
				return new Response("闲聊", "在等海伦娜回来的时候，跟斯嘉丽聊聊天。", DATE_RESTAURANT_START) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_SMALL_TALK"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("等待", "等待海伦娜回来。", DATE_RESTAURANT_START) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_WAIT"));
					}
				};
				
			} else if(index==3
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateVirginityTalk)) {// Romantic setup for taking virginity or repeat of scene:
				if(Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.GIFT_ROSE_BOUQUET))<3) {
					return new Response("浪漫陈设", "你的物品栏至少要有[style.boldMinorBad(三个"+ItemType.GIFT_ROSE_BOUQUET.getNamePlural(false)+")]才能选择此项！", null);
				}
				if((!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
						 && (!Main.game.getPlayer().hasPenis() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true))) {
					return new Response("浪漫陈设", "你[style.colourBad(需要阴茎或阴道)]，并且[style.colourBad(能够触碰你的生殖器)]，才能选择此项！", null);
				}
				return new Response("浪漫陈设", "请斯嘉丽在海伦娜的公寓里布置浪漫的场景，等你俩约会回来后过去。", DATE_RESTAURANT_ROMANTIC_SETUP);
				
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_ROMANTIC_SETUP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()) {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_AGREED");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_DECLINED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()) {
				if(index==1) {
					return new Response("谢谢她", "感谢斯嘉丽同意你的要求。", DATE_RESTAURANT_START) {
						@Override
						public int getSecondsPassed() {
							return 5*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_THANKS"));
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.GIFT_ROSE_BOUQUET), 3, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetup, true);
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("拒绝", "拒绝斯嘉丽的条件。", DATE_RESTAURANT_START) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_REFUSED"));
						}
					};
					
				} else if(index==2) {
					if(Main.game.getPlayer().getMoney()<1000) {
						return new Response("给她("+UtilText.formatAsMoneyUncoloured(1000, "span")+")", "你没有足够的钱付给斯嘉丽……", null);
					}
					return new Response("付给她("+UtilText.formatAsMoney(1000, "span")+")", "付给斯嘉丽一千火币，让她在海伦娜的公寓里布置出浪漫场景。", DATE_RESTAURANT_START) {
						@Override
						public int getSecondsPassed() {
							return 5*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_PAID"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-1000));
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.GIFT_ROSE_BOUQUET), 3, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetup, true);
						}
					};
					
				} else if(index==3) {
					if(Main.game.getNpc(Scarlett.class).hasPenis()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Response("吮吸肉棒", "你的嘴此刻被限制，不能吸斯嘉丽的肉棒……", null);
						}
						return new ResponseSex(
								"吮吸肉棒",
								"按斯嘉丽说的做，赶紧给她口交，让她在海伦娜的公寓里布置浪漫场景。",
								true,
								false,
								getScarlettOralSexManager(),
								null,
								null,
								DATE_RESTAURANT_ROMANTIC_SETUP_AFTER_ORAL,
								UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_START_BLOWJOB")) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							}
						};
						
					} else {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Response("舔阴", "因为不能使用你的嘴，你不能给斯嘉丽舔阴……", null);
						}
						return new ResponseSex(
								"舔阴",
								"按斯嘉丽说的做，赶紧给她口交，让她在海伦娜的公寓里布置浪漫场景。",
								true,
								false,
								getScarlettOralSexManager(),
								null,
								null,
								DATE_RESTAURANT_ROMANTIC_SETUP_AFTER_ORAL,
								UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_START_CUNNILINGUS")) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						};
					}
					
				}
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_ROMANTIC_SETUP_AFTER_ORAL = new DialogueNode("完成", "现在斯嘉丽高潮了，她爽够了。", true) {
		@Override
		public void applyPreParsingEffects() { //TODO test
			Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.GIFT_ROSE_BOUQUET), 3, true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetup, true);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.MOUTH, true);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.EYES, true);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.HAIR, true);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.HEAD, true);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.NECK, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_AFTER_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待", "等待海伦娜到来。", DATE_RESTAURANT_START) {
					@Override
					public int getSecondsPassed() {
						return 3*60;
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_START = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
			((Helena)Main.game.getNpc(Helena.class)).applyDressForDate();
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("葡萄酒", "告诉海伦娜和哈比服务生，你也打算在今晚喝葡萄酒。", DATE_RESTAURANT_TALKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START_WINE"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.1f));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(2));
					}
				};
				
			} else if(index==2) {
				return new Response("清水", "问哈比要饮用的清水。", DATE_RESTAURANT_TALKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START_WATER"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-2));
					}
				};
				
			} else if(index==3) {
				return new Response("啤酒", "问哈比要一瓶啤酒。", DATE_RESTAURANT_TALKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START_BEER"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.05f));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-2));
					}
				};
				
			} else if(index==4) {
				return new Response("威士忌", "问哈比要一杯威士忌酒。", DATE_RESTAURANT_TALKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START_WHISKEY"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.4f));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-2));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_TALKING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			talkTopic = HelenaConversationTopic.getRandomTopic();
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAlcoholLevel(0.1f));
		}
		@Override
		public int getSecondsPassed() {
			return 40*60;
		}
		@Override
		public String getContent() {
			// One of several talk topics:
			if(Main.game.getDialogueFlags().hasHelenaConversationTopic(talkTopic)) {
				return (UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_TALKING_"+talkTopic+"_REPEAT"));
			} else {
				return (UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_TALKING_"+talkTopic));
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			switch(talkTopic) {
				case SLAVES:
					if(index==1) {
						return new Response("自然", "告诉海伦娜，你认为自然会有人做奴隶。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_SLAVES_NATURAL"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("必要", "告诉海伦娜你认为奴隶制在像这样自治的社会里是必要的。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_SLAVES_NECESSITY"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("错误", "告诉海伦娜奴隶制在道德上是错误的，总有一天会被废除。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_SLAVES_WRONG"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
					
				case HARPY_NESTS:
					if(index==1) {
						return new Response("女王", "告诉海伦娜，应该有个女王统治所有的哈比巢穴，而她会是那个承重者。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPY_NESTS_HELENA_TOP"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("平衡", "告诉海伦娜，哈比之巢似乎已经找到了某种稳定的平衡，所以无需改变。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPY_NESTS_BALANCE"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("拆除", "告诉海伦娜，哈比之巢的社会结构应该被拆除，再被完全重造。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPY_NESTS_BAD"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
					
				case RACES:
					if(index==1) {
						return new Response("哈比", "告诉海伦娜，毋庸置疑，哈比是最美丽的种族，而她是族里最动人的。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_RACES_HARPIES"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("其他", "告诉海伦娜，你更喜欢哈比以外的种族。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_RACES_OTHER"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("个性", "告诉海伦娜你并不在乎种族，你更受他人的人格吸引，而非长相。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_RACES_PERSONALITY"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
					
				case HARPIES:
					if(index==1) {
						return new Response("淑女", "告诉海伦娜，斯嘉丽是个得体有礼的伴侣。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPIES_CONTROL"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("粗鲁", "告诉海伦娜，斯嘉丽粗野无礼，但至少她在海伦娜面前表现得很好。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPIES_RUDE"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("婊子", "告诉海伦娜，斯嘉丽完全是一个淫荡的婊子。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPIES_BITCH"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
					
				case BUSINESS:
					if(index==1) {
						return new Response("奉承", "告诉海伦娜，只有像她这样能力出众的人才能扭转局面。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_BUSINESS_FLATTER"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("邀功", "告诉海伦娜，事情发展得这么顺利，至少有你的一份功劳。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_BUSINESS_CREDIT"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("惊讶", "告诉海伦娜，你对它的成功感到惊讶；你从未想过海伦娜的想法会真的成功。", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_BUSINESS_SURPRISE"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
			}
			return null;
		}
	};
	
	// You can bring up a topic to discuss over pudding
	public static final DialogueNode DATE_RESTAURANT_PLAYER_TOPIC = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().addHelenaConversationTopic(talkTopic);
		}
		@Override
		public int getSecondsPassed() {
			return 45*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("海伦娜", "和海伦娜聊天，问问她这周末的计划。", DATE_RESTAURANT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC_HELENA"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
					}
				};
				
			} else if(index==2) {
				return new Response("奥术", "把话题转向奥术。", DATE_RESTAURANT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC_ARCANE"));
					}
				};
				
			} else if(index==3) {
				return new Response("政治", "和海伦娜聊聊莉莉丝和她统治御城区的条例。", DATE_RESTAURANT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC_POLITICS"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
					}
				};
				
			} else if(index==4 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateSexLifeTalk)) {
				if(Main.game.getNpc(Helena.class).getAffection(Main.game.getPlayer())<70) {
					return new Response("性生活",
							"你能看出海伦娜还没开放到和你聊她的性生活……"
									+ "<br/>[style.italicsMinorBad(要求海伦娜对你的好感度达到或超过70。目前是"+Main.game.getNpc(Helena.class).getAffection(Main.game.getPlayer())+"。)]",
							null);
				}
				return new Response("性生活", "和海伦娜聊聊她的性生活。", DATE_RESTAURANT_END_SEX_TALK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC_SEX_LIFE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateSexLifeTalk, true);
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
					}
				};
				
			} else if(index==5) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaGift)) {
					return new Response("送礼", "你已经给海伦娜送过礼物了……", null);
					
				} else {
					return new Response("送礼",
							"送海伦娜一份礼物(打开礼物选择界面)。"
									+ "<br/>[style.italicsMinorGood(给她送礼物后，你可以回到这个场景，与她讨论其他话题。)]",
							DATE_RESTAURANT_PLAYER_TOPIC) {
						@Override
						public DialogueNode getNextDialogue() {
							return GiftDialogue.getGiftDialogue(Main.game.getNpc(Helena.class), DATE_RESTAURANT_GIFT, 0);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_GIFT = new DialogueNode("赠送礼物", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DATE_RESTAURANT_PLAYER_TOPIC.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().applyFoodConsumed(30);
			Main.game.getPlayer().applyDrinkConsumed(30);
			Main.game.getNpc(Helena.class).applyFoodConsumed(30);
			Main.game.getNpc(Helena.class).applyDrinkConsumed(30);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("陪同", "陪同海伦娜回到她的顶层公寓。", DATE_RESTAURANT_END_HOME) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_ENTRANCE);
						Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup)) {
							Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
						}
					}
				};
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup)) {
					return new Response("离开", "斯嘉丽在海伦娜的公寓里为你布置了这么浪漫的一幕，你可不想现在离开！", null);
				}
				return new Response("离开", "跟海伦娜告别，跟她说你期望着能早日再会。", DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateFirstDateComplete, true);
						Main.game.getNpc(Helena.class).returnToHome();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_GOODBYE"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_END_SEX_TALK = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DATE_RESTAURANT_END.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_END_HOME = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(10));
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup)) {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME_ROMANCE");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup)) {
				if(index==1) {
					return new Response("卧室",
							Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)
								?"海伦娜带你穿过走廊，来到她的卧室。"
								:"带海伦娜穿过走廊，来到她的卧室。",
							DATE_APARTMENT_BEDROOM) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_ROMANTIC_SCENE_BEDROOM"));
						}
					};
				}
				
			} else if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
				if(index==1) {
					return new Response("卧室", "海伦娜没有拒绝，她拉着你走向卧室……", DATE_APARTMENT_BEDROOM) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_HELENA_DOM"));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("道别", "与海伦娜道别，返回御城区。", DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME_GOODBYE"));
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateSexLifeTalk)) {
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-1));
							}
						}
					};
					
				} else if(index==2) {
					return new Response("离别吻", "亲亲海伦娜的脸颊，和她说再见。", DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME_GOODBYE_KISS"));
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateSexLifeTalk)) {
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						}
					};
					
				} else if(index==3) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateSexLifeTalk)) {
						return new Response("进入",
								"你想，海伦娜如果能先坦诚地和你聊聊她的性生活，就会邀请你进屋了……"
										+ "<br/>[style.italicsMinorBad(下次你和海伦娜约会时，如果她对你的好感度达到70或以上，你应该问问她的性生活情况，以解锁这一行动。)]",
								null);
					}
					return new Response("进入", "接受海伦娜的邀请进去。", DATE_APARTMENT_START) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_LOUNGE);
							Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(10));
						}
					};
					
				} else if(index==4 && ((Helena)Main.game.getNpc(Helena.class)).isSlutty()) {
					return new Response("卧室", "直接带海伦娜进卧室，让饥渴的她享受性爱。", DATE_APARTMENT_BEDROOM) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME_BEDROOM"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_START = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			firstKissScene = false;
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_START"));
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateApartmentSeen, true);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			if(index==0) {
				if(Main.game.getCurrentDialogueNode()==DATE_APARTMENT_COFFEE) {
					return new Response("离开", "既然你现在有咖啡了，是时候离开了……", DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_START_LEAVE"));
						}
					};
					
				} else {
					return new Response("离开", "你在离开前至少得弄到点咖啡……", null);
				}
			}
			
			if(Main.game.getCurrentDialogueNode()==DATE_APARTMENT_KISS) {
				responses.add(new Response("咖啡", "海伦娜现在不想停下喝咖啡。", null));
				
			} else if(Main.game.getCurrentDialogueNode()==DATE_APARTMENT_COFFEE) {
				responses.add(new Response("咖啡", "你已经和海伦娜喝过咖啡了……", null));
				
			} else {
				responses.add(new Response("咖啡", "好吧，你就是为这个来的，不是吗？", DATE_APARTMENT_COFFEE));
			}
			
			if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
				responses.add(new Response("初吻",
						"海伦娜如此这般对待你，你觉得有机会夺走她的初吻……",
						DATE_APARTMENT_KISS) {
					@Override
					public void effects() {
						firstKissScene = true;
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE, true));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(10));
					}
				});
				
			} else {
				if(((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
					responses.add(new Response("亲热", "海伦娜虽然肯定不满足于接吻，但还是很乐意和你在沙发上亲热一番。", DATE_APARTMENT_KISS));
					
				} else {
					responses.add(new Response("亲热", "既然海伦娜已经发现了接吻的乐趣，你知道她一定愿意和你在沙发上亲热一番。", DATE_APARTMENT_KISS));
				}
			}
			
			if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
				responses.add(new Response("卧室", "海伦娜还没准备好。也许如果你能夺走她的初吻，下次你来的时候她就会愿意和你进入卧室了。", null));
				
			} else {
				responses.add(new Response("卧室",
						((Helena) Main.game.getNpc(Helena.class)).isSlutty()
							?"给海伦娜她迫切想要的东西并建议你们俩去她的卧室。"
							:"问海伦娜是否愿意和你一起去她的卧室。",
						DATE_APARTMENT_BEDROOM) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_START_BEDROOM"));
					}
				});
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_LEAVE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HELENA_HOTEL);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateFirstDateComplete, true);
			Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
			Main.game.getNpc(Helena.class).equipClothing();
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
			if(index==1) {
				return new Response("继续", "继续前行，进入御城区的街道。", PlaceType.DOMINION_HELENA_HOTEL.getDialogue(false));
			}
			return null;
		}
	};

	public static final DialogueNode DATE_APARTMENT_COFFEE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_COFFEE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DATE_APARTMENT_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode DATE_APARTMENT_KISS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			if(firstKissScene) {
				return 30*60;
			}
			return 5*60;
		}
		@Override
		public String getContent() {
			if(firstKissScene) {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS_FIRST");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(firstKissScene) {
				if(index==1) {
					return new Response("离开",
							"刚刚发生的一切让海伦娜完全不知所措，她跑掉了，需要时间来恢复。也许下次你再和她约会时，她会愿意和你进一步发展……",
							DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS_FIRST_LEAVE"));
						}
					};
				}
				
			} else {
				if(index==0) {
					return new Response("离开", "跟海伦娜说你得走了。", DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS_LEAVE"));
							Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
						}
					};
					
				} else if(index==1) {
					return new Response("卧室", "和海伦娜去她的卧室。", DATE_APARTMENT_BEDROOM) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS_BEDROOM"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_BEDROOM = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_HELENA_BEDROOM);
			Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
			((Helena)Main.game.getNpc(Helena.class)).applyLingerie();
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
			List<Response> responses = new ArrayList<>();
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetupEatenOut)
					&& Main.game.getNpc(Helena.class).isVaginaVirgin()) { // If romantic scene and Helena is still a virgin, you are led into giving oral:
				responses.add(new ResponseSex(
						"舔阴",
						"将海伦娜推到床上，把你的头放在她的两腿之间，然后开始舔舐她的下体。",
						true,
						true,
						getHelenaSexManager(false,
								SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY_ORAL,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
						null,
						null,
						DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX,
						UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_ROMANCE_PERFORM_CUNNILINGUS")) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetupEatenOut, true);
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
				});
				
			} else { // This is not a romantic scene, so give all options:
				responses.add(new ResponseSex(
						"指交她",
						"手指抽插海伦娜的阴部，同时为她的乳房口交。",
						true,
						true,
						getHelenaSexManager(false,
								Main.game.getPlayer().isTaur()
									?SexPosition.STANDING
									:SexPosition.SITTING,
								Main.game.getPlayer().isTaur()
									?SexSlotStanding.STANDING_SUBMISSIVE
									:SexSlotSitting.SITTING_IN_LAP,
								Main.game.getPlayer().isTaur()
									?SexSlotStanding.STANDING_DOMINANT
									:SexSlotSitting.SITTING,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.TONGUE),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.NIPPLES, CoverableArea.VAGINA)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
						null,
						null,
						DATE_APARTMENT_BEDROOM_AFTER_SEX,
						(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE))==0
							?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_FINGERING_FIRST_TIME")
							:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_FINGERING_EXPERIENCED"))) {
					@Override
					public void effects() {
						if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
						}
						if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_BREASTS_SELF).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE));
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueNipple.SUCKLE_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), FingerVagina.FINGERING_START, false, true));
					}
				});
				
				if(Main.game.getPlayer().hasPenis()) {
					if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_BREASTS_SELF).isPositive()) {
						responses.add(new Response("接受乳交", "海伦娜还没准备好和你做到这步……", null));
						
					} else if(Main.game.getPlayer().isTaur()) {
						responses.add(new Response("接受乳交", "由于你是半兽身人，海伦娜无法找到合适的姿势给你做乳交……", null));
						
					} else {
						responses.add(new ResponseSex(
								"接受乳交",
								"海伦娜把乳房挤在一起，给你乳交。",
								true,
								true,
								getHelenaSexManager(false,
										SexPosition.SITTING, SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.NIPPLES)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.PENIS))==0
									?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_PAIZURI_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_PAIZURI_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_BREASTS_SELF)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_BREASTS_SELF));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisBreasts.FUCKING_START, false, true));
							}
						});
					}
				}
				
				if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_BREASTS_SELF).isPositive()) {
					responses.add(new Response("进行舔阴", "海伦娜还没准备好和你走到这一步……", null));
					
				} else {
					responses.add(new ResponseSex(
							"舔阴",
							"将海伦娜推到床上，把你的头放在她的两腿之间，然后开始舔舐她的下体。",
							true,
							true,
							getHelenaSexManager(false,
									SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE))==0
								?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_PERFORM_CUNNILINGUS_FIRST_TIME")
								:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_PERFORM_CUNNILINGUS_EXPERIENCED"))) {
						@Override
						public void effects() {
							if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
							}
							if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_RECEIVING));
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});
				}
	
				if(Main.game.getPlayer().hasVagina()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						responses.add(new Response("接受舔阴", "由于无法接触到你的阴道，海伦娜无法为你口交……", null));
						
					} else if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
						responses.add(new Response("被舔阴", "海伦娜还没准备好和你如此亲昵……", null));
						
					} else {
						responses.add(new ResponseSex(
								"接受舔阴",
								"让海伦娜跪下，舔舐你的下体。",
								true,
								true,
								getHelenaSexManager(false,
										Main.game.getPlayer().isTaur()
											?SexPosition.STANDING
											:SexPosition.LYING_DOWN,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.PERFORMING_ORAL_BEHIND
											:SexSlotLyingDown.MISSIONARY_ORAL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.STANDING_DOMINANT
											:SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))==0
									?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_RECEIVE_CUNNILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_RECEIVE_CUNNILINGUS_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						});
					}
				}
	
				if(Main.game.getPlayer().hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("被口交", "你的阴茎不可被触及，所以海伦娜没法给你口交……", null));
						
					} else if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
						responses.add(new Response("接受口交", "海伦娜还没准备好和你走到这一步……", null));
						
					} else {
						responses.add(new ResponseSex(
								"接受口交",
								"让海伦娜跪下来为你口交。",
								true,
								true,
								getHelenaSexManager(false,
										Main.game.getPlayer().isTaur()
											?SexPosition.STANDING
											:SexPosition.SITTING,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.PERFORMING_ORAL
											:SexSlotSitting.PERFORMING_ORAL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.STANDING_DOMINANT
											:SexSlotSitting.SITTING,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))==0
									?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_RECEIVE_BLOWJOB_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_RECEIVE_BLOWJOB_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisMouth.BLOWJOB_START, false, true));
							}
						});
					}
				}

				boolean sixtyNinePenisFocus = Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
				
				if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)
						|| !Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
					responses.add(new Response("六九式", "海伦娜还没准备好和你走到这一步……", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("六九式", "由于你是半兽身人，你无法找到合适的姿势与海伦娜进行六九式……", null));
					
				} else if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
					responses.add(new ResponseSex(
							"六九式",
							"提议海伦娜坐在你的脸上，弯下腰来互相为对方口交……",
							true, true,
							getHelenaSexManager(true,
									SexPosition.LYING_DOWN, SexSlotLyingDown.SIXTY_NINE, SexSlotLyingDown.LYING_DOWN,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, sixtyNinePenisFocus?SexAreaPenetration.PENIS:SexAreaOrifice.VAGINA),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH, sixtyNinePenisFocus?CoverableArea.PENIS:CoverableArea.VAGINA)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", sixtyNinePenisFocus?"DATE_APARTMENT_BEDROOM_SIXTY_NINE_PENIS_HELENA_DOM":"DATE_APARTMENT_BEDROOM_SIXTY_NINE_HELENA_DOM")) {
						@Override
						public void effects() {
							if(sixtyNinePenisFocus && !Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
							} else {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
							}
							if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), sixtyNinePenisFocus?PenisMouth.GIVING_BLOWJOB_START:TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
						}
					});
					
				} else {
					responses.add(new ResponseSex(
							"六九式",
							"提议你坐在海伦娜的脸上，弯下腰互相为对方口交……",
							true, true,
							getHelenaSexManager(false,
									SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.SIXTY_NINE,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, sixtyNinePenisFocus?SexAreaPenetration.PENIS:SexAreaOrifice.VAGINA),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH, sixtyNinePenisFocus?CoverableArea.PENIS:CoverableArea.VAGINA)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", sixtyNinePenisFocus?"DATE_APARTMENT_BEDROOM_SIXTY_NINE_PENIS_HELENA_SUB":"DATE_APARTMENT_BEDROOM_SIXTY_NINE_HELENA_SUB")) {
						@Override
						public void effects() {
							if(sixtyNinePenisFocus && !Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
							} else {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
							}
							if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), sixtyNinePenisFocus?PenisMouth.BLOWJOB_START:TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});
				}
				
				if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
					responses.add(new Response("剪刀式体位", "海伦娜还没准备好和你走到这一步……", null));
					
				} else if(!Main.game.getPlayer().hasVagina()) {
					responses.add(new Response("剪刀式体位", "你没有阴道，所以你没法和海伦娜玩剪刀式……", null));

				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					responses.add(new Response("剪刀式体位", "你的阴部不可被触及，所以你不能和海伦娜玩剪刀式……", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("剪刀式体位", "你的下肢是兽态，所以找不到跟海伦娜剪刀式的合适姿势……", null));
					
				} else {
					if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
						responses.add(new ResponseSex(
								"剪刀式体位",
								"提议你们两个互相剪刀式体位……",
								true, true,
								getHelenaSexManager(true,
										SexPosition.LYING_DOWN, SexSlotLyingDown.SCISSORING, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT))==0
										?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_SCISSORING_DOM_FIRST_TIME")
										:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_SCISSORING_DOM_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), ClitClit.TRIBBING_START, false, true));
							}
						});
						
					} else {
						responses.add(new ResponseSex(
								"剪刀式体位",
								"提议你们两个互相剪刀式体位……",
								true, true,
								getHelenaSexManager(false,
										SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.SCISSORING,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT))==0
									?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_SCISSORING_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_SCISSORING_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), ClitClit.TRIBBING_START, false, true));
							}
						});
					}
				}
				
				if(!Main.game.getNpc(Helena.class).isVaginaVirgin()) {
					if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("骑乘位", "因为你没有阴茎，所以不能被海伦娜骑……", null));

						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("骑乘位", "因为你无法使用你的阴茎，所以你无法被海伦娜骑……", null));
							
						} else {
							responses.add(new ResponseSex(
									"骑乘位",
									"让海伦娜支配你的鸡巴",
									true, true,
									getHelenaSexManager(true,
											SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_COWGIRL")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
								}
							});
						}
						
					} else { // SUBMISSIVE:
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("传教士体位", "因为你没有阴茎，所以你不能用传教士体位操海伦娜……", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("传教士体位", "由于你无法使用你的阴茎，你也就无法与海伦娜做爱……", null));
							
						} else {
							responses.add(new ResponseSex(
									"传教士体位",
									"以传教士体位与海伦娜做爱。",
									true, true,
									getHelenaSexManager(false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_MISSIONARY")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
						}
						
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("后入式", "你没有阴茎，不能后入海伦娜……", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("后入式", "你不能使用自己的阴茎，不能后入海伦娜……", null));
							
						} else {
							responses.add(new ResponseSex(
									"后入式",
									"以后入式的姿势支配性地操海伦娜。",
									true, true,
									getHelenaSexManager(false,
											SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_DOGGY")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
						}
						
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("授种式", "因为你没有阴茎，所以你不能操海伦娜……", null));
							
						} else if(Main.game.getNpc(Helena.class).isVisiblyPregnant()) {
							responses.add(new Response("授种式", "由于海伦娜已经有了一个涨起的大肚子，她不会希望你把她按到这个位置的……", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("授种式", "由于你无法使用你的阴茎，你也就无法与海伦娜做爱……", null));
							
						} else {
							responses.add(new ResponseSex(
									"授种式",
									"将海伦娜按倒在地，立即开始给她配种。",
									true, true,
									getHelenaSexManager(false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MATING_PRESS,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_MATING_PRESS")) {
								@Override
								public void effects() {
									if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()) {
										Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.THREE_LIKE));
									}
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
						}
					}
					
				} else {
					if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
						responses.add(new Response("童贞", "海伦娜在接受你的口交之前是不会想讨论失去贞操的问题的……", null));
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateVirginityTalk)) {
						responses.add(new Response(
								"贞操",
								"海伦娜已经告诉你她不想失去贞操。"
									+ "<br/>[style.italicsMinorGood(也许在下次和海伦娜约会时，你可以给斯嘉丽安排点事情……)]",
								null));
						
					} else {
						responses.add(new Response("贞操", "询问海伦娜是否愿意把她的第一次给你。", DATE_APARTMENT_BEDROOM) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_VIRGINITY_TALK"));
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateVirginityTalk, true);
							}
						});
					}
				}
				
				
				// Anal:
				if(Main.game.isAnalContentEnabled()) {
					if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
						responses.add(new Response("进行舔肛", "直到你给海伦娜舔阴之前，她都不会接受舔肛。", null));
						
					} else if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
						responses.add(new ResponseSex(
								"吻肛颜面骑乘",
								"让海伦娜坐在你的脸上，然后你可以舔弄她的菊穴。",
								true, true,
								getHelenaSexManager(true,
										SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))==0
									?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_ANILINGUS_DOM_FIRST_TIME") // She makes it clear that she will never return the favour
									:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_ANILINGUS_DOM_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
							}
						});
						
					} else {
						responses.add(new ResponseSex(
								"进行舔肛",
								"让海伦娜将屁股交给你，这样你就可以舔弄她的肛门。",
								true, true,
								getHelenaSexManager(false,
										SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND_ORAL,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))==0
									?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_ANILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_ANILINGUS_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_RECEIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueAnus.ANILINGUS_START, false, true));
							}
						});
					}
					
					if(Main.game.getNpc(Helena.class).isAssVirgin()) {
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("夺走肛门贞操", "由于你没有肉棒，无法夺走海伦娜肛门的贞操……", null));
							
						} else if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
							responses.add(new Response("夺走肛门贞操",
									"海伦娜对失去肛门贞操不感兴趣。如果你先舔肛让她放松下来，她或许会同意……",
									null));
							
						} else {
							responses.add(new ResponseSex(
									"夺走肛门贞操",
									"询问能否夺走海伦娜肛门的贞操……",
									true, true,
									getHelenaSexManager(false,
											Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)
												?SexPosition.LYING_DOWN
												:SexPosition.ALL_FOURS,
											Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)
												?SexSlotLyingDown.COWGIRL
												:SexSlotAllFours.ALL_FOURS,
											Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)
												?SexSlotLyingDown.LYING_DOWN
												:SexSlotAllFours.BEHIND,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_ANAL_VIRGINITY")) {
								@Override
								public void effects() {
									if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_PENIS_RECEIVING)) {
										Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_PENIS_RECEIVING));
									}
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ANAL_RECEIVING));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
									} else {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
									}
								}
							});
						}
						
					} else {
						if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
							if(!Main.game.getPlayer().hasPenis()) {
								responses.add(new Response("骑乘位(肛门)", "由于你没有阴茎，无法让海伦娜肛门骑乘……", null));
							} else {
								responses.add(new ResponseSex(
										"骑乘位(肛门)",
										"让海伦娜的肛门支配你的肉棒。",
										true, true,
										getHelenaSexManager(false,
												SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
										null,
										null,
										DATE_APARTMENT_BEDROOM_AFTER_SEX,
										UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_COWGIRL_ANAL")) {
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
									}
								});
							}
							
						} else {
							if(!Main.game.getPlayer().hasPenis()) {
								responses.add(new Response("后入式(肛门)", "因为你没有阴茎，所以不能后入海伦娜的肛门……", null));
							} else {
								responses.add(new ResponseSex(
										"后入式(肛门)",
										"以后入式体位支配性地突入海伦娜的肛门。",
										true, true,
										getHelenaSexManager(false,
												SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
										null,
										null,
										DATE_APARTMENT_BEDROOM_AFTER_SEX,
										UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_DOGGY_ANAL")) {
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
									}
								});
							}
						}
					}
				}
				
				if(Main.game.isFootContentEnabled() && Main.game.getPlayer().hasPenis()) {
					if(Main.game.getPlayer().isTaur()) {
						responses.add(new Response("爪交", "由于你是半兽身人，你无法找到合适姿势让海伦娜给你爪交……", null));
						
					} else {
						responses.add(new ResponseSex(
								"爪交",
								"让海伦娜用爪子帮你手淫。",
								true, true,
								getHelenaSexManager(true,
										SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_TALONJOB")) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_FOOT_GIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_FOOT_GIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisFoot.FOOT_JOB_SINGLE_GIVING_START, false, true));
							}
						});
					}
				}
				
				// TODO Extras:
				// Scarlett threesome
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			return null;
		}
	};

	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX = new DialogueNode("结束", "海伦娜似乎做够了，脱身而去……", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开",
						Main.game.getPlayer().hasPenis()
							?"你决定今晚不夺走海伦娜的贞操，在她身边躺一会后，告诉她你要离开。"
							:"你决定今晚不对海伦娜做任何事，在她身边躺一会后，告诉她你要离开。",
						DATE_APARTMENT_LEAVE) {
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX_LEAVE"));
					}
				};
				
			} else if(index==1) {
				return new Response("淋浴&睡觉",
						"你决定不夺走海伦娜的贞操，在一起淋浴后，你和她一同上床入睡。"
								+ "<br/>[style.italicsGood(从所有腔穴中清理<b>最多"+Units.fluid(500)+"</b>液体。)]"
								+ "<br/>[style.italicsGood(将<b>仅会</b>清理当前装备的衣物。)]",
						DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP);
			}
			
			if(Main.game.getPlayer().hasPenis()) {
				if(index==2) {
					return new ResponseSex(
							"夺走贞操(作为服从方)",
							"让海伦娜骑上肉棒，夺走自己贞操。<br/>[style.italicsSub(这会让海伦娜永久获得支配者性癖！)]",
							true, true,
							getHelenaSexManager(true,
									SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX_VIRGINITY_HELENA_DOM")) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_DOMINANT));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_VAGINAL_RECEIVING));
							if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_PENIS_RECEIVING)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_PENIS_RECEIVING));
							}
							Main.game.getNpc(Helena.class).removePersonalityTrait(PersonalityTrait.INNOCENT);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
						}
					};
					
				} else if(index==3) {
					return new ResponseSex(
							"夺走贞操(作为支配方)",
							(Main.game.getPlayer().isTaur()
									?"骑上海伦娜，以后入式体位夺走她的贞操。" 
									:"以传教士体位夺走海伦娜的贞操。")
								+ "<br/>[style.italicsSub(这会让海伦娜永久获得顺从的性癖！)]",
							true, true,
							getHelenaSexManager(false,
									Main.game.getPlayer().isTaur()
										?SexPosition.ALL_FOURS
										:SexPosition.LYING_DOWN,
									Main.game.getPlayer().isTaur()
										?SexSlotAllFours.ALL_FOURS
										:SexSlotLyingDown.LYING_DOWN,
									Main.game.getPlayer().isTaur()
										?SexSlotAllFours.BEHIND
										:SexSlotLyingDown.MISSIONARY,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX_VIRGINITY_HELENA_SUB")) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_SUBMISSIVE));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_VAGINAL_RECEIVING));
							if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_PENIS_RECEIVING)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_PENIS_RECEIVING));
							}
							Main.game.getNpc(Helena.class).removePersonalityTrait(PersonalityTrait.INNOCENT);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
					};
					
				} else if(index==4) {
					return new ResponseSex(
							"挣脱她",
							(Main.game.getPlayer().isTaur()
									?"骑上海伦娜，以后入式体位虐待性地夺走她的贞操。"
									:"以传教士体位虐待性地夺走海伦娜的贞操。")
								+ "<br/>[style.italicsSub(这会让海伦娜永久获得顺从和受虐狂的性癖！)]",
							Util.newArrayListOfValues(Fetish.FETISH_SADIST),
							null,
							CorruptionLevel.FIVE_CORRUPT,
							null,
							null,
							null,
							true, true,
							getHelenaSexManager(false,
									Main.game.getPlayer().isTaur()
										?SexPosition.ALL_FOURS
										:SexPosition.LYING_DOWN,
									Main.game.getPlayer().isTaur()
										?SexSlotAllFours.ALL_FOURS
										:SexSlotLyingDown.LYING_DOWN,
									Main.game.getPlayer().isTaur()
										?SexSlotAllFours.BEHIND
										:SexSlotLyingDown.MISSIONARY,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX_VIRGINITY_HELENA_SUB_SADIST")) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_SUBMISSIVE));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_VAGINAL_RECEIVING));
							if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_PENIS_RECEIVING)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_PENIS_RECEIVING));
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_MASOCHIST));
							Main.game.getNpc(Helena.class).removePersonalityTrait(PersonalityTrait.INNOCENT);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
					};
				}
				
			} else {
				return DATE_APARTMENT_BEDROOM.getResponse(responseTab, index-1);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_SEX = new DialogueNode("结束", "海伦娜似乎做够了，脱身而去……", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaBedroomFromNest)) {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_FROM_NEST");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaBedroomFromNest)) { // Helena & the player have entered her bedroom directly from the nest, and so should return there:
				if(index==1) {
					return new Response("离开",
							"按照海伦娜要求去做，随后离开她的住处。",
							DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_LEAVE_NEST"));
							
							Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
							Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
							Main.game.getNpc(Helena.class).equipClothing();
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaBedroomFromNest, false);
						}
					};
				}
				
			} else {
				if(index==0) {
					return new Response("离开",
							"告诉海伦娜你马上就要离开……",
							DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_LEAVE"));
							Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-1));
						}
					};
					
				} else if(index==1) {
					return new Response("淋浴&睡觉",
							"和海伦娜一起淋浴，然后和她上床睡觉。"
								+ "<br/>[style.italicsGood(从所有腔穴中清理<b>最多"+Units.fluid(500)+"</b>液体。)]"
								+ "<br/>[style.italicsGood(将<b>仅会</b>清理当前装备的衣物。)]",
							DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().cleanAllClothing(false, false);
			Main.game.getPlayer().cleanAllDirtySlots(true);
			Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
			((Helena)Main.game.getNpc(Helena.class)).applyDressForMorning();
		}
		@Override
		public int getSecondsPassed() {
			return 10 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("醒来",
						"晨间，你在海伦娜的床上醒来……",
						DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP_WAKE_UP);
			} 
			return null;
		}
	};
	

	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP_WAKE_UP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(9*60) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP_WAKE_UP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开",
						"告诉海伦娜你马上就要离开……",
						DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP_LEAVE"));
					}
				};
				
			} else if(index==1) {
				return new Response("早餐",
						"接受海伦娜的请求和她一起享用早餐，随后便离开这。",
						DATE_APARTMENT_BREAKFAST) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP_BREAKFAST"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(2));
					}
				};
				
			} else if(index==2 && ((Helena)Main.game.getNpc(Helena.class)).isSlutty()) {
				if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
					return new ResponseSex(
							"晨间性爱",
							"让海伦娜掌握主动权跟你做爱。",
							true, true,
							getHelenaSexManager(true,
									SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
									null,
									Util.newHashMapOfValues()),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING_COWGIRL")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueMouth.KISS_START, false, true));
						}
					};
					
				} else {
					return new ResponseSex(
							"晨间性爱",
							"让你掌握主动权跟海伦娜做爱。",
							true, true,
							getHelenaSexManager(false,
									SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY,
									null,
									Util.newHashMapOfValues()),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING_MISSIONARY")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							if(!Main.game.getPlayer().isTaur()) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueMouth.KISS_START, false, true));
							}
							return super.getInitialSexActions();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING = new DialogueNode("结束", "海伦娜似乎做够了，脱身而去……", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().cleanAllClothing(false, false);
			Main.game.getPlayer().cleanAllDirtySlots(true);
			Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开",
						"告诉海伦娜你现在要离开……",
						DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING_LEAVE"));
					}
				};
				
			} else if(index==1) {
				return new Response("早餐",
						"接受海伦娜的请求和她一起享用早餐，随后便离开这。",
						DATE_APARTMENT_BREAKFAST) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING_BREAKFAST"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(2));
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_BREAKFAST = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_DINING_ROOM);
			Main.game.getNpc(Helena.class).setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_DINING_ROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进食",
						"和海伦娜享用早餐后，你返回到御城区",
						DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BREAKFAST_LEAVE"));
					}
				};
			}
			return null;
		}
	};
	
	
}
