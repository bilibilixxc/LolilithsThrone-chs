package com.lilithsthrone.game.dialogue.places.dominion.nightlife;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.AlcoholLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.DominionClubNPC;
import com.lilithsthrone.game.character.npc.dominion.Jules;
import com.lilithsthrone.game.character.npc.dominion.Kalahari;
import com.lilithsthrone.game.character.npc.dominion.Kruger;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.dominion.SMJulesCockSucking;
import com.lilithsthrone.game.sex.managers.dominion.SMKrugerChair;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHole;
import com.lilithsthrone.game.sex.managers.dominion.toiletStall.SMStallSex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMSitting;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.population.Population;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class NightlifeDistrict {
	
	private static boolean isSearchingForASub = true;
	private static Gender clubberGender;
	private static AbstractSubspecies clubberSubspecies;
	private static RaceStage clubberRaceStage;
	
	private static boolean isClubOpen(int minutesPassedForNextScene) {
		return !((Main.game.getMinutesPassed()+minutesPassedForNextScene) % (24 * 60) >= (60 * 5) && (Main.game.getMinutesPassed()+minutesPassedForNextScene) % (24 * 60) < (60 * 19));
	}
	
	public static boolean isSearchingForASub() {
		return isSearchingForASub;
	}

	public static List<GameCharacter> getClubbersPresent() {
		List<GameCharacter> clubbers = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		// Do not filter by checking for DominionClubNPC classes, as imported clubbers are not of this class
		clubbers.removeIf((npc) -> npc.isUnique() || npc.isSlave() || Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())); // So that when player takes clubber home, slaves/occupants in player's room are not added to this list
		return clubbers;
	}
	
	public static List<GameCharacter> getGloryHoleCharacters() {
		List<GameCharacter> characters = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		characters.removeIf((npc) -> !(npc instanceof GenericSexualPartner));
		return characters;
	}

	
	public static List<GameCharacter> getSavedClubbers(boolean submissiveClubbers) {
		List<GameCharacter> clubbers = new ArrayList<>(Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_CLUB_HOLDING_CELL));
		
		clubbers.removeIf((npc) -> (submissiveClubbers
						?npc.hasPersonalityTrait(PersonalityTrait.CONFIDENT)
						:!npc.hasPersonalityTrait(PersonalityTrait.CONFIDENT)));
		
		return clubbers;
	}
	
	private static boolean hasPartner() {
		return !getClubbersPresent().isEmpty();
	}
	
	public static GameCharacter getPartner() {
		return getClubbersPresent().get(0);
	}
	
	public static boolean isPartnerSub() {
		return !getPartner().hasPersonalityTrait(PersonalityTrait.CONFIDENT);
	}

	private static void spawnClubbers(boolean submissiveClubbers) {
		NPC clubber = new DominionClubNPC(clubberGender, clubberSubspecies, clubberRaceStage, false);
				
		if(Math.random()<0.4f) {
			clubber.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				clubber.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				clubber.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}
		
		if(submissiveClubbers) {
			clubber.removePersonalityTrait(PersonalityTrait.SELFISH);
			clubber.removePersonalityTrait(PersonalityTrait.BRAVE);
			clubber.removePersonalityTrait(PersonalityTrait.CONFIDENT);
			if(Math.random()<0.5) {
				clubber.addPersonalityTrait(PersonalityTrait.SHY);
			}
			if(clubber.getFetishDesire(Fetish.FETISH_SUBMISSIVE).isNegative()) {
				clubber.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.TWO_NEUTRAL);
			}
			clubber.removeFetish(Fetish.FETISH_DOMINANT);
			if(clubber.getFetishDesire(Fetish.FETISH_DOMINANT).isPositive()) {
				clubber.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.TWO_NEUTRAL);
			}
			
		} else {
			double rnd = Math.random();
			clubber.removePersonalityTrait(PersonalityTrait.SHY);
			clubber.addPersonalityTrait(PersonalityTrait.CONFIDENT);
			if(rnd<0.33f) {
				clubber.addPersonalityTrait(PersonalityTrait.KIND);
			} else if(rnd<0.66f) {
				clubber.addPersonalityTrait(PersonalityTrait.SELFISH);
			}
			if(clubber.getFetishDesire(Fetish.FETISH_DOMINANT).isNegative()) {
				clubber.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.TWO_NEUTRAL);
			}
			clubber.removeFetish(Fetish.FETISH_SUBMISSIVE);
			if(clubber.getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive()) {
				clubber.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.TWO_NEUTRAL);
			}
		}
		
		try {
			Main.game.addNPC(clubber, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		if(Main.game.getPlayer().getNonElementalCompanions().isEmpty()) {
//			// spawn 1 or 2
//		} else {
//			// spawn 2
//			// spawn second one with companion's preferences
//		}
	}
	
	public static void saveClubbers() {
		for(GameCharacter clubber : getClubbersPresent()) {
			clubber.setLocation(WorldType.EMPTY, PlaceType.GENERIC_CLUB_HOLDING_CELL);
		}
	}
	
	public static void removeClubbers() {
		for(GameCharacter clubber : getClubbersPresent()) {
			Main.game.banishNPC((NPC) clubber);
		}
	}
	
	private static String getClubberStatus(int secondsPassedForNextScene, boolean isDominantPartner) {
		StringBuilder sb = new StringBuilder();
		
		if(hasPartner()) {
			GameCharacter clubber = getClubbersPresent().get(0);
			
			AffectionLevel al = clubber.getAffectionLevel(Main.game.getPlayer());
			if(isDominantPartner) {
				al = AffectionLevel.getAffectionLevelFromValue(domPartnerNightlyAffection);
			}
			
			sb.append("<p style='text-align:center;'><i>");
			switch(al) {
				case NEGATIVE_FIVE_LOATHE:
				case NEGATIVE_FOUR_HATE:
				case NEGATIVE_THREE_STRONG_DISLIKE:
				case NEGATIVE_TWO_DISLIKE:
					sb.append("[npc.Name]看起来<i style='color:"+al.getColour().toWebHexString()+";'>愤怒而沮丧</i>，如果你继续对待[npc.herHim]这么糟，[npc.herHim]就会离开。");
					break;
				case NEGATIVE_ONE_ANNOYED:
					sb.append("[npc.Name]看起来<i style='color:"+al.getColour().toWebHexString()+";'>有些不开心</i>，已经开始感受到不悦。");
					break;
				case ZERO_NEUTRAL:
				case POSITIVE_ONE_FRIENDLY:
					sb.append("[npc.Name]看起来<i style='color:"+al.getColour().toWebHexString()+";'>很高兴</i>，没有理由离开你。");
					break;
				case POSITIVE_TWO_LIKE:
				case POSITIVE_THREE_CARING:
					sb.append("[npc.Name]看起来<i style='color:"+al.getColour().toWebHexString()+";'>很愉悦</i>。");
					break;
				case POSITIVE_FOUR_LOVE:
				case POSITIVE_FIVE_WORSHIP:
					sb.append("[npc.NameIsFull]<i style='color:"+al.getColour().toWebHexString()+";'>急切地想与你做爱</i>。");
					break;
			}
			
			if(clubber.getAlcoholLevelValue()>0) {
				sb.append("</br>");
					switch(clubber.getAlcoholLevel()) {
						case ZERO_SOBER:
							break;
						case ONE_TIPSY:
							sb.append("[npc.Name]有些<i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>醉了</i>。");
							break;
						case TWO_MERRY:
							sb.append("[npc.Name]很<i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>开心</i>。");
							break;
						case THREE_DRUNK:
							sb.append("[npc.Name]<i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>喝大了</i>。");
							break;
						case FOUR_HAMMERED:
							sb.append("[npc.Name]<i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>酩酊大醉</i>。再给[npc.herHim]喝酒可不是个好主意。");
							break;
						case FIVE_WASTED:
							sb.append("[npc.Name]<i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>烂醉如泥</i>。"+(isPartnerSub()?"[npc.She]看起来快晕过去了。":""));
							break;
					}
			}

			sb.append("</br>");
			if(likesSex(clubber, isDominantPartner)) {
				sb.append("你能看出[npc.she]想和你做爱……");
				
			} else if(likesGroping(clubber, isDominantPartner)) {
				if(isDominantPartner) {
					sb.append("你能看出来[npc.sheIs]想尝试和你肢体接触……");
				} else {
					sb.append("你能看出[npc.sheIs]想和你肢体接触……");
				}
				
			} else if(likesKiss(clubber, isDominantPartner)) {
				if(isDominantPartner) {
					sb.append("你能看出[npc.sheIs]随时会亲你……");
				} else {
					sb.append("你看出[npc.she]可以接吻……");
				}
				
			} else {
				if(isDominantPartner) {
					sb.append("你看出[npc.sheIs]不愿意更进一步……");
				} else {
					sb.append("你更进一步之前最好先和[npc.herHim]聊聊……");
				}
			}
			
			sb.append("</i></p>");
			
		}
		
		int minutesPassedForNextScene = secondsPassedForNextScene/60;
		if(isEndConditionMet(minutesPassedForNextScene)) {
			sb.append(getEndingStatus(minutesPassedForNextScene));
		}
		
		return UtilText.parse(getClubbersPresent(), sb.toString());
	}
	
	private static int getKalahariBreakTimeLeft() {
		return Math.max(0, (int) (35 - (Main.game.getMinutesPassed() - Main.game.getDialogueFlags().getSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID))));
	}

	private static boolean isPartnerLeaving(boolean isDominantPartner) {
		if(isDominantPartner) {
			return hasPartner() && domPartnerNightlyAffection<AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue();
		}
		return hasPartner() && getPartner().getAffection(Main.game.getPlayer())<AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue();
	}
	
	private static boolean isPartnerPassingOut() {
		return hasPartner() && isPartnerSub() && getPartner().getAlcoholLevelValue()>=AlcoholLevel.FIVE_WASTED.getMaximumValue()-0.05f;
	}
	
	private static String getEndingStatus(int minutesPassedForNextScene) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'>");
		if(isPartnerLeaving(false)) {
			sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible([npc.Name]离开了你！)]</br>"
					+ "[npc.Name]受够了，不耐烦地挥挥[npc.hand]，转身扔下了你！"));
		
		} else if(isPartnerPassingOut()) {
			sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible([npc.Name]崩溃了！)]</br>"
					+ "[npc.name]喝了太多酒，一头栽在地上！"));
			
		} else if(!isClubOpen(minutesPassedForNextScene)) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_VIP_AREA)) {
				sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible(暂停营业)]</br>"
						+ "夜店内所有的灯突然明灭闪烁，背景音乐停止播放，克鲁格咆哮着，"
							+ "[kruger.speech(看来到了晚上关门的时间了。走吧，[pc.name]。我们可以下次再说。)]"));
				
			} else {
				sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible(暂停营业)]</br>"
						+ "夜店内所有的灯突然明灭闪烁，背景音乐停止播放。你听见朱勒斯喊道，[jules.speech(关门了！各位都请离开！)]"));
			}
		}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	private static boolean isEndConditionMet(int minutesPassedForNextScene) {
		return isPartnerLeaving(false) || isPartnerPassingOut() || (!isClubOpen(minutesPassedForNextScene) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules));
	}
	
	private static Response getEndResponse(int index, int minutesPassedForNextScene) {
		if(isPartnerLeaving(false)) {
			if(index==1) {
				return new Response("继续", UtilText.parse(getClubbersPresent(), "或许你该对[npc.Name]好点……"), Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						if(isPartnerSub()) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_PARTNER_LEAVES", getClubbersPresent()));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_PARTNER_DOM_LEAVES", getClubbersPresent()));
						}
						removeClubbers();
					}
				};
			}
			
		} else if(isPartnerPassingOut()) {
			if(index==1) {
				return new Response("继续", UtilText.parse(getClubbersPresent(), "[npc.Name]崩溃了！"), Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_PARTNER_TOO_WASTED", getClubbersPresent()));
						saveClubbers();
					}
				};
			}
			
		} else if(!isClubOpen(minutesPassedForNextScene)) {
			if(hasPartner()) {
				if(index==1) {
					return new ResponseEffectsOnly("说再见",
							UtilText.parse(getClubbersPresent(), "闭店时间到了，你该离开夜店了。和[npc.Name]再见然后回到御城区的“夜生活”区。"
									+ "</br>[style.italicsGood(保存该角色，你们可以在夜店再次巧遇。)]")) {
						@Override
						public void effects() {
							saveClubbers();
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} else if(index==2) {
					if(likesSex(getPartner(), false)) {
						return new Response("邀请回家",
								UtilText.parse(getClubbersPresent(), "闭店时间到了，你该离开夜店了。让[npc.Name]和你一起去你的地方。"),
								RoomPlayer.AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME) {
							@Override
							public void effects() {
								for(GameCharacter clubber : getClubbersPresent()) {
									clubber.setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
								}
								
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
							}
						};
					} else {
						return new Response("邀请回家", UtilText.parse(getClubbersPresent(),
								"闭店时间到了，你该离开夜店了。[npc.Name]不想跟你去你的地方……"), null);
					}
					
				} else if(index==3) {
					return new ResponseEffectsOnly("失去陪伴",
							UtilText.parse(getClubbersPresent(), "闭店时间到了，你该离开夜店了。告诉[npc.name]你要离开，而后回到御城区的“夜生活”区。"
									+ "</br>[style.italicsBad(从游戏里移除该角色。)]")) {
						@Override
						public void effects() {
							removeClubbers();
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				}
				
			} else {
				if(index==1) {
					return new ResponseEffectsOnly("离开", "闭店时间到了，你该离开夜店了。") {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, false);
						}
					};
				}
			}
			return null;
			
		}
		return null;
	}
	
	private static String getKalahariStatus(boolean withBreakTime, int secondsPassedForNextScene) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'><i>");
		sb.append("卡拉哈利还有"+(getKalahariBreakTimeLeft()-5)+"分钟就会离开。");
		sb.append("</i></p>");
		
		int minutesPassedForNextScene = secondsPassedForNextScene/60;
		if(isEndConditionMet(minutesPassedForNextScene)) {
			sb.append(getEndingStatus(minutesPassedForNextScene));
		}
		
		return sb.toString();
	}
	
	private static boolean likesKiss(GameCharacter clubber, boolean isDominantPartner) {
		return (isDominantPartner?domPartnerNightlyAffection:clubber.getAffection(Main.game.getPlayer()))>=AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue()
				|| clubber.getAlcoholLevelValue()>0;
	}
	
	private static boolean likesGroping(GameCharacter clubber, boolean isDominantPartner) {
		return (isDominantPartner?domPartnerNightlyAffection:clubber.getAffection(Main.game.getPlayer()))>=AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue()
				|| clubber.getAlcoholLevelValue()>AlcoholLevel.TWO_MERRY.getMinimumValue();
	}
	
	private static boolean likesSex(GameCharacter clubber, boolean isDominantPartner) {
		return (isDominantPartner?domPartnerNightlyAffection:clubber.getAffection(Main.game.getPlayer()))>=AffectionLevel.POSITIVE_THREE_CARING.getMedianValue()
				|| clubber.getAlcoholLevelValue()>AlcoholLevel.THREE_DRUNK.getMinimumValue();
	}
	
	private static final float KALAHARI_SELL_MODIFIER = 1.2f;

	public static final DialogueNode OUTSIDE = new DialogueNode("夜生活", "夜生活", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(!isClubOpen(0)) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_DAY_STORM"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_DAY"));
				}
				
			} else {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_NIGHT_STORM"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_NIGHT"));
				}
			}
			
			if(Main.game.getDialogueFlags().hasFlag("innoxia_hannah_training_complete")) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_LIGHTS_OUT"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!isClubOpen(0)) {
					return new Response("饮水洼", UtilText.parse("夜店“饮水洼”，目前关门了。入口处的指示牌告诉你，开放时间为每晚[unit.time(19)]-[unit.time(05)]。"), null);
				} else {
					return new Response("饮水洼", "夜店“饮水洼”，正在开张。如果你想可以走进去。", WATERING_HOLE_ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, false);
							Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_ENTRANCE);
						}
					};
				}
				
			} else if(index==2 && Main.game.getDialogueFlags().hasFlag("innoxia_hannah_training_complete")) {
				if(!Main.game.isHourBetween(18, 4)) {
					return new Response("影液", UtilText.parse("“影液”酒吧已经关门了。门口的牌子告诉你它每晚[unit.time(18)]到[unit.time(04)]开门。"), null);
					
				} else {
					return new Response("影液",
							"酒吧，“影液”，正在开张。如果你想的话，可以进去。",
							DialogueManager.getDialogueFromId("innoxia_places_dominion_nightlife_lights_out_exit_initial_entry"));
				}
			}
			return null;
		}
	};

	public static final DialogueNode WATERING_HOLE_ENTRANCE = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules) || isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules)) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.julesIntroduced)) {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_REPEAT", getClubbersPresent());
				}
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_PASSED")
						+ getClubberStatus(this.getSecondsPassed(), false);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!hasPartner()) {
				if(index==0) {
					return new ResponseEffectsOnly("出口",
							"离开“饮水洼夜店”，回到了御城区的“夜生活区”。") {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules)) {
					if(index==1) {
						return new Response("等待", "耐心地排队进入夜店。", WATERING_HOLE_ENTRANCE_WAITING) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
								Main.game.getPlayer().setNearestLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_MAIN_AREA, false);
							}
						};
						
					} else if(index==2) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Response("吮吸肉棒", "你的嘴此刻被限制，不能吸朱勒斯的肉棒！", null);
						}
						return new ResponseSex("吮吸鸡巴", "在所有人面前吮吸朱勒斯的鸡巴，来排到队首。",
								true,
								false,
								new SMJulesCockSucking(
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Jules.class), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
								null,
								null,
								AFTER_JULES_BLOWJOB,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_START_BLOWJOB")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suckedJulesCock, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
								
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Jules.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
							}
						};
						
					} else if(index==3 && Main.game.getPlayer().getRace()==Race.DEMON) {
						return new Response("插队", "使用你恶魔的身份插队.", WATERING_HOLE_ENTRANCE_SKIP_QUEUE) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
								Main.game.getPlayer().setNearestLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_MAIN_AREA, false);
							}
						};
						
					}
					return null;
					
				} else { // Passed Jules:
					if(index==1 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.suckedJulesCock)) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.fuckedJulesTonight)) {
							return new Response("朱勒斯", "你今晚已经与朱勒斯好好的干一次了，他可没时间再来一次……", null);
						}
						return new ResponseSex("朱勒斯", "告诉朱勒斯你想让他“正确地操你”……",
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getNpc(Jules.class)),
										Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								AFTER_JULES_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_JULES_SEX", NightlifeDistrict.getClubbersPresent())){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.fuckedJules, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.fuckedJulesTonight, true);
							}
						};
						
					}
				}
				
			} else {
				if(index==1) {
					return new ResponseEffectsOnly("说再见",
							UtilText.parse(getClubbersPresent(), "和[npc.name]告别，回到御城区的“夜生活”区。"
									+ "</br>[style.italicsGood(保存该角色，你们可以在夜店再次巧遇。)]")) {
						@Override
						public void effects() {
							saveClubbers();
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} else if(index==2) {
					if(likesSex(getPartner(), false)) {
						return new Response("邀请回家", UtilText.parse(getClubbersPresent(), "带[npc.name]回到你的房间。"), RoomPlayer.AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME) {
							@Override
							public void effects() {
								for(GameCharacter clubber : getClubbersPresent()) {
									clubber.setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
								}
								
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
								Main.game.setRequestAutosave(false);
							}
						};
					} else {
						return new Response("邀请回家", UtilText.parse(getClubbersPresent(), "[npc.Name]看起来没兴趣跟你回你的地方，你需要跟[npc.herHim]更多接触一点……"), null);
					}
					
				} else if(index==3) {
					return new ResponseEffectsOnly("失去陪伴",
							UtilText.parse(getClubbersPresent(), "在回到御城区名为“夜生活”区之前，告诉[npc.Name]你要走了。"
									+ "</br>[style.italicsBad(从游戏里移除该角色。)]")) {
						@Override
						public void effects() {
							removeClubbers();
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} 
				return null;
				
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_ENTRANCE_WAITING = new DialogueNode("饮水洼夜店", "", false, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_WAITING")
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_ENTRANCE_SKIP_QUEUE = new DialogueNode("饮水洼夜店", "", false, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_SKIP_QUEUE")
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_JULES_BLOWJOB = new DialogueNode("结束", "朱勒斯做够了。", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AFTER_JULES_BLOWJOB")
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_JULES_SEX = new DialogueNode("结束", "朱勒斯做够了。", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AFTER_JULES_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_MAIN = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN", getClubbersPresent())
					+getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(hasPartner()) {
				if(index==1) {
					return new Response("交谈", UtilText.parse(getClubbersPresent(), "和[npc.name]聊聊，更了解[npc.herHim]一点。"), WATERING_HOLE_MAIN_TALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_TALK.getSecondsPassed(), false));
						}
					};
					
				} else if(index==2) {
					return new Response("调情", UtilText.parse(getClubbersPresent(), "夸赞[npc.namePos]的外貌并开始与[npc.herHim]调情。"), WATERING_HOLE_MAIN_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_FLIRT.getSecondsPassed(), false));
						}
					};
					
				} else if(index==3) {
					return new Response("亲吻",
							UtilText.parse(getClubbersPresent(), "靠近几步亲吻[npc.name]。"+(likesKiss(getPartner(), false)?"":"</br>[style.italicsBad([npc.She]可能不会有什么好脸色！)]")),
							WATERING_HOLE_MAIN_KISS) {
						@Override
						public void effects() {
							if(likesKiss(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_KISS", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KISS_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_KISS.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_KISS_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_KISS.getSecondsPassed(), false));
								
							}
						}
					};
					
				} else if(index==4) {
					return new Response("爱抚",
							UtilText.parse(getClubbersPresent(), "磨蹭[npc.name]并开始摸索[npc.herHim]。"+(likesGroping(getPartner(), false)?"":"</br>[style.italicsBad([npc.She]可能不会有什么好脸色！)]")),
							WATERING_HOLE_MAIN_GROPE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_GROPE", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_GROPE_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 20));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_GROPE.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_GROPE_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_GROPE.getSecondsPassed(), false));
							}
						}
					};
					
				} if(index==9) {
					return new Response("道别",
							UtilText.parse(getClubbersPresent(), "告诉[npc.name]，你要离开一会儿，但希望以后能再次见到[npc.herHim]。"
									+ "</br>[style.italicsGood(保存该角色，可以再次在夜店遇到。)]"),
							WATERING_HOLE_MAIN_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==10) {
					return new Response("失去陪伴",
							UtilText.parse(getClubbersPresent(), "找个借口赶[npc.name]走。</br>[style.italicsBad(在游戏中移除该角色。)]"),
							WATERING_HOLE_MAIN_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("搜寻(作为支配方)",
							"寻找可以接近并开始进行交流的人。这将使你处于主导地位，你可以带领伴侣在夜店里四处游玩。",
							WATERING_HOLE_SEARCH_GENDER) {
						@Override
						public void effects() {
							isSearchingForASub = true;
						}
					};
					
				} else if(index==2) {
					if(clubberGender==null || clubberSubspecies==null) {
						return new Response("再次搜寻(作为服从方)", "你需要先在夜店里找一次！", null);
					} else {
						return new Response("再次搜寻(作为服从方)",
								"重复上次的搜索，寻找可以接近并开始交谈的人。("+Util.capitaliseSentence(clubberGender.getName())+" "+clubberSubspecies.getName(null)+")",
								WATERING_HOLE_SEARCH_GENERATE) {
							@Override
							public void effects() {
								isSearchingForASub = true;
								spawnClubbers(true);
							}
						};
					}
					
				} else if(index==3) {
					if(getSavedClubbers(true).isEmpty()) {
						return new Response("联系人(作为支配方)", "你还没有遇见能再次约到夜店的人……", null);
						
					} else {
						return new Response("联系人(作为支配方)",
								"寻找你之前在夜店见过的人。这将使你处于主导地位，你可以带领伴侣在夜店里四处游玩。",
								WATERING_HOLE_CONTACTS) {
							@Override
							public void effects() {
								isSearchingForASub = true;
							}
						};
					}
					
				} else if(index==4) {
					return new Response("导入(作为支配方)",
							"查看角色导入界面。"
								+ "在以下界面中导入的角色将被标记为顺从者，并在导入后显示在“联系人(作为支配者)”列表中。",
							WATERING_HOLE_IMPORT) {
						@Override
						public void effects() {
							isSearchingForASub = true;
						}
					};
					
				} if(index==6) {
					return new Response("搜寻(作为服从方)",
							"在夜店的主区域闲逛，试图吸引你感兴趣那位的注意。"
									+ "这将使你成为服从角色，你的对象会在夜店引导你。",
							WATERING_HOLE_SEARCH_GENDER) {
						@Override
						public void effects() {
							isSearchingForASub = false;
						}
					};
					
				} else if(index==7) {
					if(clubberGender==null || clubberSubspecies==null) {
						return new Response("重复搜索(作为服从方)", "你需要已经搜索过夜店！", null);
					} else {
						return new Response("重复搜寻(作为服从方)",
								"重复你上一次的搜索，寻找能吸引你目光的人。("+Util.capitaliseSentence(clubberGender.getName())+""+clubberSubspecies.getName(null)+")",
								WATERING_HOLE_SEARCH_GENERATE_DOM) {
							@Override
							public void effects() {
								isSearchingForASub = false;
								spawnClubbers(false);
								resetPreviousBehaviour(); 
							}
						};
					}
					
				} else if(index==8) {
					if(getSavedClubbers(false).isEmpty()) {
						return new Response("联系人(作为服从方)", "你还没有遇见能再次约到夜店的人……", null);
						
					} else {
						return new Response("联系人(作为服从方)",
								"寻找一位你之前在夜店见过的主导方联系人。这将使你处于服从地位，由你的伴侣带领你在夜店里四处游玩。",
								WATERING_HOLE_CONTACTS_DOM) {
							@Override
							public void effects() {
								isSearchingForASub = false;
							}
						};
					}
					
				} else if(index==9) {
					return new Response("导入(作为服从方)",
							"查看角色导入界面。"
								+ "在以下屏幕中导入的角色将被标记为主要角色，导入后将显示在“联系人(作为服从方)”列表中。",
							WATERING_HOLE_IMPORT) {
						@Override
						public void effects() {
							isSearchingForASub = false;
						}
					};
				}
				
				return null;
			}
		}
	};
	
	public static final DialogueNode WATERING_HOLE_MAIN_TALK = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_TALK", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TALK_CONTENT", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_MAIN_FLIRT = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_FLIRT", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FLIRT_CONTENT", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_MAIN_KISS = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_MAIN_GROPE = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_MAIN_LOSE_COMPANY = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEARCH_GENDER = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 0*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENDER");
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "[style.colourFeminine(阴柔)]";
				
			} else if(index==1) {
				return "[style.colourMasculine(肌肉发达)]";
				
			} else if(index==2) {
				return "[style.colourAndrogynous(中性)]";
				
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("返回", "决定不打量有没有人接近。", Main.game.getDefaultDialogue(false));
			}
			int count = 1;
			for(Gender gender : Gender.values()) {
				if((responseTab==0 && gender.getType()==PronounType.FEMININE)
						|| (responseTab==1 && gender.getType()==PronounType.MASCULINE)
						|| (responseTab==2 && gender.getType()==PronounType.NEUTRAL)) {
					if(count==index) {
						return new Response(Util.capitaliseSentence(gender.getName()),
								"在狂欢的人群中寻找"+UtilText.generateSingularDeterminer(gender.getName())+" "+gender.getName()+"。"
									+ "("+(gender.getGenderName().isHasBreasts()?"[style.colourGood(胸部)]":"[style.colourBad(胸部)]")+"，"
										+(gender.getGenderName().isHasPenis()?"[style.colourGood(肉棒)]":"[style.colourBad(肉棒)]")+"，"
										+(gender.getGenderName().isHasVagina()?"[style.colourGood(阴道)]":"[style.colourBad(阴道)]")+")",
								WATERING_HOLE_SEARCH_RACE) {
							@Override
							public void effects() {
								clubberGender = gender;
							}
						};
					}
					count++;
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEARCH_RACE = new DialogueNode("饮水洼夜店", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_RACE");
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "[style.colourTfPartial(似兽)]";
			} else if(index == 1) {
				return "[style.colourTfMinor(泛兽)]";
			} else if(index == 2) {
				return "[style.colourTfLesser(亚兽)]";
			} else if(index == 3) {
				return "[style.colourTfGreater(纯兽)]";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("返回", "决定找个其他性别的。", WATERING_HOLE_SEARCH_GENDER);
			}
			int count = 1;
			
			Set<AbstractSubspecies> subspeciesSet = new HashSet<>();
			for(Population pop : Main.game.getPlayer().getLocationPlace().getPlaceType().getPopulation()) {
				subspeciesSet.addAll(pop.getSpecies().keySet());
			}
			if(!subspeciesSet.isEmpty()) {
				List<AbstractSubspecies> sortedSubspecies = new ArrayList<>(subspeciesSet);
				sortedSubspecies.sort((s1, s2) -> s1.getRace().getName(false).compareTo(s2.getRace().getName(false)));
				for(AbstractSubspecies subspecies : sortedSubspecies) {
					if(count==index) {
						return new Response(Util.capitaliseSentence(subspecies.getName(null)),
								"在狂欢的人群中寻找"+UtilText.generateSingularDeterminer(subspecies.getName(null))+" "+subspecies.getName(null)+"。",
								(isSearchingForASub
										?WATERING_HOLE_SEARCH_GENERATE
										:WATERING_HOLE_SEARCH_GENERATE_DOM)) {
							@Override
							public void effects() {
								switch(responseTab) {
									case 0:
										clubberRaceStage = RaceStage.PARTIAL;
										break;
									case 1:
										clubberRaceStage = RaceStage.PARTIAL_FULL;
										break;
									case 2:
										clubberRaceStage = RaceStage.LESSER;
										break;
									default:
										clubberRaceStage = RaceStage.GREATER;
										break;
								}
								clubberSubspecies = subspecies;
								spawnClubbers(isSearchingForASub);
							}
						};
					}
					count++;
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEARCH_GENERATE = new DialogueNode("饮水洼夜店", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(isPartnerSub()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENERATE", getClubbersPresent())
						+getClubberStatus(this.getSecondsPassed(), false);
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENERATE_DOM", getClubbersPresent())
						+getClubberStatus(this.getSecondsPassed(), false);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_CONTACTS = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 0*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_CONTACTS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("返回", "决定不再找你以前邂逅过的人。", WATERING_HOLE_MAIN);
			}
			int count = 1;
			for(GameCharacter character : getSavedClubbers(true)) {
				if(count==index) {
					if(!character.isAttractedTo(Main.game.getPlayer())) {
						return new Response(character.getName(true),
								UtilText.parse(character, "[npc.Name][style.colourBad(没被你吸引)]，不愿意和你在夜店里消磨时间。<br/>([npc.She]是[npc.a_fullRace(true)]。)"),
								null);
					}
					if(Main.game.getMinutesPassed()-((NPC)character).getLastTimeEncountered()<12*60) {
						return new Response(character.getName(true),
								UtilText.parse(character, "你今晚已经在夜店遇见过[npc.name]了，[style.colourBad(你明天才能再次邂逅[npc.herHim])]。"),
								null);
					}
					return new Response(character.getName(true),
							UtilText.parse(character, "在狂欢的人群中寻找[npc.Name]。<br/>([npc.She]是[npc.a_fullRace(true)]。)"),
							WATERING_HOLE_FIND_CONTACT) {
						@Override
						public void effects() {
							character.setLocation(WorldType.NIGHTLIFE_CLUB, Main.game.getPlayer().getLocation(), false);
							if(character.getTotalTimesHadSex(Main.game.getPlayer()) == 0) {
								character.setAffection(Main.game.getPlayer(), 5);
							}
						}
					};
				}
				count++;
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_FIND_CONTACT = new DialogueNode("饮水洼夜店", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FIND_CONTACT", getClubbersPresent())
					+getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_IMPORT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
			
			if(isSearchingForASub) {
				saveLoadSB.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_IMPORT_DOM"));
			} else {
				saveLoadSB.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_IMPORT_SUB"));
			}
			saveLoadSB.append(
					"<p>"
						+ "<table align='center'>");
			Main.getSlavesForImport().sort(Comparator.comparingLong(File::lastModified).reversed());
			
			for(File f : Main.getSlavesForImport()){
				saveLoadSB.append(getImportRow(f.getName()));
			}
			
			saveLoadSB.append("</table>"
					+ "</p>"
					+ "<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("返回", "回到上一界面。", Main.game.getDefaultDialogue());
			}
			return null;
		}
	};
	
	private static String getImportRow(String name) {
		String baseName = Util.getFileName(name);
		String identifier = Util.getFileIdentifier(name);
		
		return "<tr>"
				+ "<td style='min-width:200px;'>"
					+ baseName
				+ "</td>"
				+ "<td>"
					+ "<div class='saveLoadButton' id='IMPORT_CLUBBER_" + identifier + "' style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>导出</div>"
				+ "</td>"
				+ "</tr>";
	}
	
	public static final DialogueNode WATERING_HOLE_LOITER_GENERATE = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			List<GameCharacter> clubbers = getClubbersPresent();
			
			if(clubbers.size()==1) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_LOITER_GENERATE", clubbers);
				
			} else {
				if(Main.game.getPlayer().getNonElementalCompanions().isEmpty()) {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_LOITER_GENERATE_TWO", clubbers);
					
				} else {
					clubbers.addAll(Main.game.getPlayer().getNonElementalCompanions());
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_LOITER_GENERATE_TWO_WITH_COMPANION", clubbers);
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_SEATING = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(hasPartner()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_WITH_PARTNER", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(hasPartner()) {
				if(index==1) {
					return new Response("交谈", UtilText.parse(getClubbersPresent(), "和[npc.name]聊聊，更了解[npc.herHim]一点。"), WATERING_HOLE_SEATING_TALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_TALK.getSecondsPassed(), false));
						}
					};
					
				} else if(index==2) {
					return new Response("调情", UtilText.parse(getClubbersPresent(), "夸赞[npc.namePos]的外貌并开始与[npc.herHim]调情。"), WATERING_HOLE_SEATING_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_FLIRT.getSecondsPassed(), false));
						}
					};
					
				} else if(index==3) {
					boolean bothBipeds = true;
					if(Main.game.getPlayer().isTaur() || getPartner().isTaur()) {
						bothBipeds = false;
					}
					return new Response( // If both partners are bipeds, play footsie. If not, feeling up occurs instead.
							bothBipeds
								?"用脚轻轻摩擦"
								:"爱抚",
							UtilText.parse(getClubbersPresent(),
									(bothBipeds
										?"轻轻将你的[pc.foot]探进[npc.namePos]的下体。"
										:"将自己压在[npc.name]上，开始摸索[npc.herHim]。")
									+(likesGroping(getPartner(), false)?"":"</br>[style.italicsBad([npc.She]可能不会有什么好脸色！)]")),
							WATERING_HOLE_SEATING_FOOTSIE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_FOOTSIE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_FOOTSIE.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_FOOTSIE_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_FOOTSIE.getSecondsPassed(), false));
								
							}
						}
					};

				} else if(index==4) {
					if(!getPartner().isAttractedTo(Main.game.getPlayer())) {
						return new Response("做爱(支配)",
								UtilText.parse(getClubbersPresent(), "[npc.Name]对你[style.colorBad(不感兴趣)]，因此不愿意与你发生性关系……"),
								null);
					}
					if(likesSex(getPartner(), false)) {
						SexManagerDefault sm = new SMSitting(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public List<AbstractSexPosition> getAllowedSexPositions() {
								return Util.newArrayListOfValues(SexPosition.SITTING);
							}
						};
						
						if(Main.game.getPlayer().isTaur()) { // Player is a taur/arachnid:
							sm = new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_SUBMISSIVE))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
								@Override
								public List<AbstractSexPosition> getAllowedSexPositions() {
									return Util.newArrayListOfValues(SexPosition.STANDING, SexPosition.SITTING);
								}
							};
						}
						return new ResponseSex("做爱(支配)",
								UtilText.parse(getClubbersPresent(), "你再也无法抗拒[npc.name]了！开始与[npc.herHim]进行支配型性爱。"),
								true, true,
								sm,
								null,
								null,
								WATERING_HOLE_SEATING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_DOM", getClubbersPresent()));
						
					} else {
						return new Response("做爱(支配)",
								UtilText.parse(getClubbersPresent(), "你再也无法抗拒[npc.name]了！开始与[npc.herHim]进行支配型性爱。</br>[style.italicsBad([npc.She]可能不会有什么好脸色！)]"),
								WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED) {
							@Override
							public void effects() {
								if(!getClubbersPresent().get(0).hasFetish(Fetish.FETISH_NON_CON_SUB)) {
									Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), -25));
								}
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED.getSecondsPassed(), false));
							}
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} else if(index==5) {
					if(!getPartner().isAttractedTo(Main.game.getPlayer())) {
						return new Response("做爱(顺从)",
								UtilText.parse(getClubbersPresent(), "[npc.Name]对你[style.colorBad(不感兴趣)]，因此不愿意与你发生性关系……"),
								null);
					}
					if(likesSex(getPartner(), false)) {
						SexManagerDefault sm = new SMSitting(
								Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public List<AbstractSexPosition> getAllowedSexPositions() {
								return Util.newArrayListOfValues(SexPosition.SITTING);
							}
						};
						
						if(Main.game.getPlayer().isTaur()) {
							if(getPartner().isTaur()) { // Both taurs/arachnids:
								sm = new SexManagerDefault(
										SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
									@Override
									public List<AbstractSexPosition> getAllowedSexPositions() {
										return Util.newArrayListOfValues(SexPosition.STANDING, SexPosition.SITTING);
									}
								};
							}
							
						} else if(getPartner().isTaur()) { // Partner is a taur/arachnid:
							sm = new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), getPartner().hasPenis()?SexSlotStanding.PERFORMING_ORAL:SexSlotStanding.PERFORMING_ORAL_BEHIND))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
								@Override
								public List<AbstractSexPosition> getAllowedSexPositions() {
									return Util.newArrayListOfValues(SexPosition.STANDING, SexPosition.SITTING);
								}
							};
						}
						return new ResponseSex(
								"做爱(顺从)",
								UtilText.parse(getClubbersPresent(), "你再也无法抗拒[npc.name]了！与[npc.herHim]开始服从型性爱。"),
								true, true,
								sm,
								null,
								null,
								WATERING_HOLE_SEATING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_SUB", getClubbersPresent()));
						
					} else {
						return new Response("做爱(顺从)",
								UtilText.parse(getClubbersPresent(), "你再也无法抗拒[npc.name]了！与[npc.herHim]开始服从型性爱。</br>[style.italicsBad([npc.She]可能不会有什么好脸色！)]"),
								WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED.getSecondsPassed(), false));
							}
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} if(index==9) {
					return new Response("道别",
							UtilText.parse(getClubbersPresent(), "告诉[npc.name]，你要离开一会儿，但希望以后能再次见到[npc.herHim]。"
									+ "</br>[style.italicsGood(保存该角色，可以再次在夜店遇到。)]"),
							WATERING_HOLE_SEATING_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==10) {
					return new Response("失去陪伴",
							UtilText.parse(getClubbersPresent(), "找个借口赶[npc.name]走。</br>[style.italicsBad(在游戏中移除该角色。)]"),
							WATERING_HOLE_SEATING_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
					
				}
				return null;
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_TALK = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_TALK", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TALK_CONTENT", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_FLIRT = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_FLIRT", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FLIRT_CONTENT", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_FOOTSIE = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_AFTER_SEX = new DialogueNode("结束", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getClubbersPresent(), "[npc.Name]已经享受够了……");
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getPartner())>=getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX", getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_NO_ORGASM", getClubbersPresent());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("再见一面",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.name]你想和[npc.herHim]再见一面。</br>"
								+ "[style.italicsGood(保存该角色，然后就有机会在夜店中再度邂逅。)]"),
						WATERING_HOLE_SEATING_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_SEE_AGAIN", getClubbersPresent()));
						saveClubbers();
					}
				};
				
			} else if(index==2) {
				return new Response("还是算了(委婉)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "做出不置可否的回应，暗自希望不要再看到[npc.name]。</br>[style.italicsBad(将此角色从游戏中删除。)]"),
						WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN", getClubbersPresent()));
						removeClubbers();
					}
				};
				
			} else if(index==3) {
				return new Response("还是算了(直接)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "残忍地告诉[npc.name]，你只是想操[npc.herHim]。</br>[style.italicsBad(在游戏中移除该角色。)]"),
						WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", getClubbersPresent()));
						removeClubbers();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_AFTER_SEX_SEE_AGAIN = new DialogueNode("饮水洼夜店", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN = new DialogueNode("饮水洼夜店", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_LOSE_COMPANY = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_BAR = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced);
		}

		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR", getClubbersPresent());
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_REPEAT", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced)) {
				return getEndResponse(index, 0);
			}
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced)) {
				if(index==1) {
					return new Response("酒保", "狮女酒保向你问好。", WATERING_HOLE_BAR_KALAHARI_INTRO) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariIntroduced, true);
						}
					};
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("卡拉哈利", "卡拉哈利停住脚步和你打招呼。", WATERING_HOLE_BAR_KALAHARI) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI", getClubbersPresent()));
							Main.game.getNpc(Kalahari.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
					};
				} else {
					return null;
				}
			}
		}
	};
	
	private static String getDrinkEffects(AbstractItem drink) {
		StringBuilder sb = new StringBuilder();
		
		for(ItemEffect ie : drink.getEffects()) {
			for(String desc : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
				sb.append("</br>"+desc);
			}
		}
		
		return sb.toString();
	}
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_INTRO = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_INTRO", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(hasPartner()) {
				if(index==0) {
					return "自己";
					
				} else if(index==1) {
					return UtilText.parse(getClubbersPresent(), "[npc.Name]");
					
				}
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(!hasPartner() || responseTab==0) {
				if(index==1) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("水("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "你连瓶"+drink.getName(false, false)+"都买不起！", null);
						
					} else {
						return new Response("水("+UtilText.formatAsMoney(price, "span")+")", "向卡拉哈利要一瓶"+drink.getName(false, false)+"."+getDrinkEffects(drink), WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>"
											+ "[pc.speech(我能买瓶"+drink.getName(false, false)+"吗？)]你的喊声穿透了夜店的噪音，卡拉哈利听见了。"
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(当然可以哈！)]狮子回答，[kalahari.speech(要"+Util.intToString(price)+"火币。)]"
										+ "</p>"
										+ "<p>"
											+ "你给卡拉哈利交了钱，看着她回身从吧台后的冰箱里拿出一瓶"+drink.getName(false, false)+"。"
											+ "她将冷饮放在你面前，后退了一步，身体前倾，露出她的乳沟，并向你抛了一个俏皮的媚眼。"
											+ "[kalahari.speech(请慢用！)]"
										+ "</p>"
										+ "<p>"
											+ "你在夜店的嘈杂声中大声道谢，拧开瓶盖，把塑料瓶凑到[pc.lips]边。"
											+ "一股淡淡的甜味告诉你，这不是普通的水，当你仰起头开始喝这清凉的液体时，香草的味道扑面而来。"
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==2) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("啤酒("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "你连一瓶"+drink.getName(false, false)+"都买不起！", null);
						
					} else {
						return new Response("啤酒("+UtilText.formatAsMoney(price, "span")+")", "找卡拉哈利买瓶"+drink.getName(false, false)+"喝。"+getDrinkEffects(drink), WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>"
											+ "[pc.speech(我能买瓶"+drink.getName(false, false)+"吗？)]你的喊声穿透了夜店的噪音，卡拉哈利听见了。"
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(当然可以哈！)]狮子回答，[kalahari.speech(要"+Util.intToString(price)+"火币。)]"
										+ "</p>"
										+ "<p>"
											+ "你给卡拉哈利交了钱，看着她回身从吧台后的冰箱里拿出一瓶"+drink.getName(false, false)+"。"
											+ "她朝你走过来，向前倾身。她把冰镇啤酒放在你面前，俏皮地抛了个媚眼，似乎不慎露出了下乳沟。"
											+ "卡拉哈利拿起旁边的开瓶器，打开瓶盖，然后把瓶子从吧台上推给你。"
											+ "[kalahari.speech(请慢用！)]"
										+ "</p>"
										+ "<p>"
											+ "你在夜店的嘈杂声中大声道谢，拿起冰凉的瓶子，凑到[pc.lips]边。"
											+ "当你开始大口大口地喝时，你会发现它的味道与你喝过的其他啤酒完全不同，它更像是一种含糖能量饮料，而不是什么酒精饮料。"
											+ "当最后几滴顺着喉咙滑下时，一股奇异的浓烈余味在舌尖萦绕。"
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==3) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "你连一杯"+drink.getName(false, false)+"都买不起！", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+"("+UtilText.formatAsMoney(price, "span")+")",
								"问卡拉哈利要一杯"+drink.getName(false, false)+"。"+getDrinkEffects(drink),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>"
											+ "[pc.speech(能给我来杯"+drink.getName(false, false)+"吗？)]你的喊声盖过了夜店的噪音，卡拉哈利听见了。"
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(当然可以哈！)]狮子回答，[kalahari.speech(要"+Util.intToString(price)+"火币。)]"
										+ "</p>"
										+ "<p>"
											+ "你给卡拉哈利交了钱，看着她回身从吧台后的冰箱里拿出一瓶"+drink.getName(false, false)+"。"
											+ "她走过来，倾身靠近你，边朝你抛媚眼，边从吧台上拿了个干净杯子，似乎不慎露出了下乳沟。"
											+ "狮女把杯子列在你面前，倒出一份奶油酒精饮料，从吧台上朝你滑过去。"
											+ "[kalahari.speech(请慢用！)]"
										+ "</p>"
										+ "<p>"
											+ "你声音盖过夜店的噪音，大喊一声谢谢，便抓过杯子，靠在[pc.lips]边。"
											+ "饮料散发出浓郁的奶香味，你贪婪地喝下凉爽的液体，"
												+ "惊奇地发现，它喝起来和如同闻上去那般美味。"
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==4) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "你连一杯"+drink.getName(false, false)+"都买不起！", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+"("+UtilText.formatAsMoney(price, "span")+")",
								"问卡拉哈利要一杯"+drink.getName(false, false)+"。"+getDrinkEffects(drink),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>"
											+ "[pc.speech(能给我来杯"+drink.getName(false, false)+"吗？)]你的喊声盖过了夜店的噪音，卡拉哈利听见了。"
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(当然可以哈！)]狮子回答，[kalahari.speech(要"+Util.intToString(price)+"火币。)]"
										+ "</p>"
										+ "<p>"
											+ "你交了钱，看卡拉哈利转过身，从吧台后的其中一个架子上取下一瓶"+drink.getName(false, false)+"。"
											+ "她走过来，倾身靠近你，边朝你抛媚眼，边从吧台上拿了个干净杯子，似乎不慎露出了下乳沟。"
											+ "雌狮把酒杯放在你面前，倒出一杯烈酒，然后把它从吧台上滑向你。"
											+ "[kalahari.speech(好好享用！)]"
										+ "</p>"
										+ "<p>"
											+ "在夜店的嘈杂声中，你大声道谢，然后拿起酒杯，凑到自己的[pc.lips]边。"
											+ "威士忌散发出浓郁的气味，当你开始饮用这种酒时，你会发现它的味道与刺鼻的香气几乎完全一致。"
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==5) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "你买不起这杯"+drink.getName(false, false)+"！", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "span")+")",
								"向卡拉哈利要一杯"+drink.getName(false, false)+"。"+getDrinkEffects(drink),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>"
											+ "[pc.speech(我能来一杯"+drink.getName(false, false)+"吗？)]你在夜店的喧闹声中向卡拉哈利喊道。"
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(当然可以哈！)]狮子回答，[kalahari.speech(要"+Util.intToString(price)+"火币。)]"
										+ "</p>"
										+ "<p>"
											+ "你把钱交给卡拉哈利，看着她转身从吧台后面的架子上拿起一瓶"+drink.getName(false, false)+"。"
											+ "她后退一步，身体前倾，露出她的乳沟，向你抛了一个俏皮的媚眼，同时从吧台下拿起一个干净的杯子。"
											+ "雌狮把酒杯放在你面前，倒出一杯烈酒，然后把它从吧台上滑向你。"
											+ "[kalahari.speech(好好享用！)]"
										+ "</p>"
										+ "<p>"
											+ "你在夜店的嘈杂声中大声道谢，然后拿起酒杯，凑到[pc.lips]边，"
												+ "然后再仰起头，快速地大口喝下这金色的液体。"
											+ "当最后几滴酒液滑入喉咙时，你会发现酒精饮料中弥漫着一股淡淡的雄性味道的干涩感，这种令人不悦的回味会持续一段时间。"
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==6) {
					if(!hasPartner()) {
						return new Response("交谈", "身体前倾，和卡拉哈利说一会儿话。", WATERING_HOLE_BAR_KALAHARI_TALK) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 5));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KALAHARI_TALK.getSecondsPassed(), false));
							}
						};
					} else {
						return new Response("交谈", UtilText.parse(getClubbersPresent(), "当[npc.name]和你在一起时，你不能和卡拉哈利对话！"), null);
					}
					
				} else if(index==7) {
					if(!hasPartner()) {
						return new Response("调情", "开始与卡拉哈利调情。", WATERING_HOLE_BAR_KALAHARI_FLIRT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 10));
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer(), true);
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KALAHARI_FLIRT.getSecondsPassed(), false));
							}
						};
					} else {
						return new Response("调情", UtilText.parse(getClubbersPresent(), "你在[npc.name]身旁的时候，不能与卡拉哈利调情！"), null);
					}
					
				} else if(index==8) {
					if(hasPartner()) {
						return new Response("休息", UtilText.parse(getClubbersPresent(), "当[npc.name]和你在一起时，你不能要求卡拉哈利和你一起休息！"), null);
						
					} else if(Main.game.getMinutesPassed() - Main.game.getDialogueFlags().getSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID) < 60 * 12) {
							return new Response("休息", "卡拉哈利今晚的休息时间已经用完了！", null);
							
					} else if(!likesKiss(Main.game.getNpc(Kalahari.class), false)) {
						return new Response("休息", "你对卡拉哈利还不够了解，不能要求她和你一起度过休息时间。先试着和她聊聊天，调调情……", null);
						
					} else {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.krugerIntroduced)) {
							return new Response("休息", "问问卡拉哈里是否有休息时间，是否愿意和你一起度过。", WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO) {
								@Override
								public void effects() {
									Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.krugerIntroduced, true);
									Main.game.getDialogueFlags().setSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID, Main.game.getMinutesPassed());
									Main.game.getNpc(Kruger.class).setAffection(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue()-7);
								}
							};
							
						} else {
							return new Response("休息", "问问卡拉哈利是否有休息时间，是否愿意再和你一起在贵宾区放松一下。", WATERING_HOLE_BAR_KALAHARI_BREAK) {
								@Override
								public void effects() {
									Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getDialogueFlags().setSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID, Main.game.getMinutesPassed());
								}
							};
						}
					}
					
				}
				
			} else {
				GameCharacter clubber = getClubbersPresent().get(0);
				
				if(index==1) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("水("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "你连瓶"+drink.getName(false, false)+"都买不起！", null);
						
					} else {
						return new Response("水("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "向卡拉哈利要一瓶"+drink.getName(false, false)+"给[npc.name]。"+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>"
											+ "[pc.speech(我能买瓶"+drink.getName(false, false)+"吗？)]你的喊声穿透了夜店的噪音，卡拉哈利听见了。"
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(当然可以哈！)]狮子回答，[kalahari.speech(要"+Util.intToString(price)+"火币。)]"
										+ "</p>"
										+ "<p>"
											+ "你把钱交给卡拉哈利，看着她转身从吧台后面的冰箱里拿出一瓶"+drink.getName(false, false)+"。"
											+ "她将冷饮放在你面前，后退了一步，身体前倾，露出她的乳沟，并向你抛了一个俏皮的媚眼。"
											+ "[kalahari.speech(好好享用！)]"
										+ "</p>"
										+ "<p>"
											+ "你在夜店的嘈杂声中大声道谢，然后把酒瓶递给[npc.name]。"
											+ "[pc.speech(你看起来需要喝点水，给你。)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(谢谢，没错……我渴极了，)][npc.name]回应道，然后拧开瓶盖，大口喝水。"
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 1)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==2) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("啤酒("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "你连一瓶"+drink.getName(false, false)+"都买不起！", null);
						
					} else {
						return new Response("啤酒("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "向卡拉哈利要一瓶"+drink.getName(false, false)+"给[npc.name]。"+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>"
											+ "[pc.speech(我能买瓶"+drink.getName(false, false)+"吗？)]你的喊声穿透了夜店的噪音，卡拉哈利听见了。"
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(当然可以哈！)]狮子回答，[kalahari.speech(要"+Util.intToString(price)+"火币。)]"
										+ "</p>"
										+ "<p>"
											+ "你把钱交给卡拉哈利，看着她转身从吧台后面的冰箱里拿出一瓶"+drink.getName(false, false)+"。"
											+ "她把冰啤酒放在你面前，后退一步，身体前倾，露出她的乳沟，并向你抛了一个俏皮的媚眼。"
											+ "卡拉哈利拿起旁边的开瓶器，打开瓶盖，然后把瓶子从吧台上推给你。"
											+ "[kalahari.speech(好好享受！)]"
										+ "</p>"
										+ "<p>"
											+ "你在夜店的嘈杂声中大声道谢，然后把酒瓶递给[npc.name]。"
											+ "[pc.speech(你看起来需要来一杯，给你。)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(谢谢！是啊，我都快渴死了，)][npc.name]说道，然后举起酒瓶，对准[npc.her]的[npc.lips]咕嘟咕嘟地灌下啤酒。"
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 2)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==3) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "你买不起这杯"+drink.getName(false, false)+"！", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "为[npc.name]向卡拉哈利要一杯"+drink.getName(false, false)+"。"+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>"
											+ "[pc.speech(我能来一杯"+drink.getName(false, false)+"吗？)]你在夜店的喧闹声中向卡拉哈利喊道。"
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(当然可以哈！)]狮子回答，[kalahari.speech(要"+Util.intToString(price)+"火币。)]"
										+ "</p>"
										+ "<p>"
											+ "你把钱交给卡拉哈利，看着她转身从吧台后面的冰箱里拿出一瓶"+drink.getName(false, false)+"。"
											+ "她后退一步，身体前倾，露出她的乳沟，向你抛了一个俏皮的媚眼，同时从吧台下拿起一个干净的杯子。"
											+ "雌狮把酒杯放在你面前，倒出一杯奶油酒精饮料，然后把它从吧台上滑向你。"
											+ "[kalahari.speech(好好享受！)]"
										+ "</p>"
										+ "<p>"
											+ "你在夜店的嘈杂声中大声道谢，把酒杯递给[npc.name]。"
											+ "[pc.speech(你看起来需要来一杯，给你。)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(Aww，谢谢！我喜欢猫猫幻想！)][npc.name]高兴地回应道，然后举起酒杯对准[npc.her]的[npc.lips]，大口喝下了这杯乳白色的酒精饮料。"
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 3)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==4) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "你买不起这杯"+drink.getName(false, false)+"！", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "为[npc.name]向卡拉哈利要一杯"+drink.getName(false, false)+"。"+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>"
											+ "[pc.speech(我能来一杯"+drink.getName(false, false)+"吗？)]你在夜店的喧闹声中向卡拉哈利喊道。"
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(当然可以哈！)]狮子回答，[kalahari.speech(要"+Util.intToString(price)+"火币。)]"
										+ "</p>"
										+ "<p>"
											+ "你把钱交给卡拉哈利，看着她转身从吧台后面的架子上拿起一瓶"+drink.getName(false, false)+"。"
											+ "她后退一步，身体前倾，露出她的乳沟，向你抛了一个俏皮的媚眼，同时从吧台下拿起一个干净的杯子。"
											+ "雌狮把酒杯放在你面前，倒出一杯烈酒，然后把它从吧台上滑向你。"
											+ "[kalahari.speech(好好享受！)]"
										+ "</p>"
										+ "<p>"
											+ "你在夜店的嘈杂声中大声道谢，把酒杯递给[npc.name]。"
											+ "[pc.speech(你看起来需要来一杯，给你。)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(嘿，谢谢，[pc.name]！头狼威士忌最棒了！)][npc.name]高兴地回应道，然后举起酒杯对着[npc.her]的[npc.lips]大口喝下了这杯烈酒。"
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 4)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==5) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "你买不起这杯"+drink.getName(false, false)+"！", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "为[npc.name]向卡拉哈利要一杯"+drink.getName(false, false)+"。"+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>"
											+ "[pc.speech(我能来一杯"+drink.getName(false, false)+"吗？)]你在夜店的喧闹声中向卡拉哈利喊道。"
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(当然可以哈！)]狮子回答，[kalahari.speech(要"+Util.intToString(price)+"火币。)]"
										+ "</p>"
										+ "<p>"
											+ "你把钱交给卡拉哈利，看着她转身从吧台后面的架子上拿起一瓶"+drink.getName(false, false)+"。"
											+ "她后退一步，身体前倾，露出她的乳沟，向你抛了一个俏皮的媚眼，同时从吧台下拿起一个干净的杯子。"
											+ "雌狮把酒杯放在你面前，倒出一杯烈酒，然后把它从吧台上滑向你。"
											+ "[kalahari.speech(好好享受！)]"
										+ "</p>"
										+ "<p>"
											+ "你在夜店的嘈杂声中大声道谢，把酒杯递给[npc.name]。"
											+ "[pc.speech(你看起来需要来一杯，给你。)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(谢谢，[pc.name]！这朗姆酒是最棒的！)][npc.name]高兴地回应道，然后举起酒杯对着[npc.her]的[npc.lips]，大口喝下了这杯烈酒。"
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 5)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==6) {
					return new Response("交谈", UtilText.parse(getClubbersPresent(), "与[npc.name]交谈，以便更好地了解[npc.herHim]。"), WATERING_HOLE_BAR_TALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_TALK.getSecondsPassed(), false));
						}
					};
					
				} else if(index==7) {
					return new Response("调情", UtilText.parse(getClubbersPresent(), "开始与[npc.name]调情。"), WATERING_HOLE_BAR_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_FLIRT.getSecondsPassed(), false));
						}
					};
					
				} else if(index==8) {
					return new Response("亲吻",
							UtilText.parse(getClubbersPresent(), "靠近几步亲吻[npc.name]。"+(likesKiss(getPartner(), false)?"":"</br>[style.italicsBad([npc.She]可能不会有什么好脸色！)]")),
							WATERING_HOLE_BAR_KISS) {
						@Override
						public void effects() {
							if(likesKiss(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KISS", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KISS_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KISS.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KISS_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KISS.getSecondsPassed(), false));
								
							}
						}
					};
					
				} else if(index==9) {
					return new Response("爱抚",
							UtilText.parse(getClubbersPresent(), "将自己压在[npc.name]身上，开始摸索[npc.herHim]。"+(likesGroping(getPartner(), false)?"":"</br>[style.italicsBad([npc.She]可能不会有什么好脸色！)]")),
							WATERING_HOLE_BAR_GROPE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_GROPE", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_GROPE_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_GROPE.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_GROPE_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_GROPE.getSecondsPassed(), false));
								
							}
						}
					};
					
				} else if(index==11) {
					return new Response("道别",
							UtilText.parse(getClubbersPresent(), "告诉[npc.name]，你要离开一会儿，但希望以后能再次见到[npc.herHim]。"
									+ "</br>[style.italicsGood(保存该角色，可以再次在夜店遇到。)]"),
							WATERING_HOLE_BAR_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==12) {
					return new Response("失去陪伴",
							UtilText.parse(getClubbersPresent(), "找个借口赶[npc.name]走。</br>[style.italicsBad(在游戏中移除该角色。)]"),
							WATERING_HOLE_BAR_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
				}
			}
				
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_TALK = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_TALK", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TALK_CONTENT", getClubbersPresent());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_FLIRT = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_FLIRT", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FLIRT_CONTENT", getClubbersPresent());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KISS = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_GROPE = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_LOSE_COMPANY = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_DRINK = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};

	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_TALK = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_TALK");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_FLIRT = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_FLIRT")
					+ "<p>"
						+ Main.game.getNpc(Kalahari.class).getBreastDescription()
					+ "</p>"
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_FLIRT_END");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO")
					+ getKalahariStatus(true, this.getSecondsPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(getKalahariBreakTimeLeft()<=0) {
				if(index==1) {
					return new Response("休息结束", "卡拉哈利的休息时间用完了。让她回去继续工作吧。", WATERING_HOLE_KALAHARI_BREAK_OUT_OF_TIME) {
						@Override
						public void effects() {
							Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_BAR);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, false);
							Main.game.getNpc(Kalahari.class).equipClothing();
						}
					};
				}
				
			} else {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("交谈", "卡拉哈利现在只对做爱感兴趣！", null);
						
					} else {
						return new Response("交谈", "与卡拉哈利交谈，以便进一步了解她。", WATERING_HOLE_KALAHARI_BREAK_TALK) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 5));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_TALK.getSecondsPassed()));
							}
						};
					}
					
				} else if(index==2) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("调情", "卡拉哈利现在只想做爱！", null);
						
					} else {
						return new Response("调情", "开始与卡拉哈利调情。", WATERING_HOLE_KALAHARI_BREAK_FLIRT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 10));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_FLIRT.getSecondsPassed()));
							}
						};
					}
					
				} else if(index==3) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("亲吻", "卡拉哈利现在只对做爱感兴趣！", null);
						
					} else if(!likesKiss(Main.game.getNpc(Kalahari.class), false)) {
						return new Response("亲吻", "看得出来，卡拉哈利现在不想接吻。最好先多了解她一点。", null);
						
					} else {
						return new Response("接吻", "向前探身和卡拉哈利接吻。", WATERING_HOLE_KALAHARI_BREAK_KISS) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 15));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_KISS.getSecondsPassed()));
							}
						};
					}
					
				} else if(index==4) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("爱抚", "卡拉哈利现在只对做爱感兴趣！", null);
						
					} else if(!likesGroping(Main.game.getNpc(Kalahari.class), false)) {
						return new Response("爱抚", "你可以看出，卡拉哈利会对任何支配她的举动反应强烈。最好先花点时间和她调调情。", null);
						
					} else {
						return new Response("爱抚", "把自己压在卡拉哈利身上，开始爱抚她。", WATERING_HOLE_KALAHARI_BREAK_GROPE) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 20));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_GROPE.getSecondsPassed()));
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer(), true);
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer(), true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, true);
								Main.game.getNpc(Kalahari.class).displaceClothingForAccess(CoverableArea.BREASTS, null);
								Main.game.getNpc(Kalahari.class).displaceClothingForAccess(CoverableArea.VAGINA, null);
							}
						};
					}
					
				} else if(index==5) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("做爱(支配)", "在与卡拉哈利发生性关系之前，你需要让她完全进入状态。", null);
					} else {
						SexManagerDefault sm = new SMSitting(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kalahari.class), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						};
						if(Main.game.getPlayer().isTaur()) { // Player is a taur/arachnid:
							sm = new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kalahari.class), SexSlotStanding.STANDING_SUBMISSIVE))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
							};
						}
						
						return new ResponseSex("做爱(支配)",
								!Main.game.getPlayer().isTaur()
									?"把卡拉哈利拉到你的大腿上，开始和她进行支配型性爱。"
									:"站起来，一边把卡拉哈利拉到她的脚边，一边开始和她进行支配型性爱。",
								true, true,
								sm,
								null,
								null,
								WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_SEX_AS_DOM"));
					}
					
				}
				//TODO requires several improvements to sex AI and positioning first
//				else if(index==6) {
//					if(!Main.game.getPlayer().isFeminine()) {
//						return new Response("Kruger threesome", "Kruger is gynephilic, so wouldn't be interested in a threesome with you and Kalahari.", null);
//						
//					} else {
//						return new ResponseSex("Kruger threesome", "Have a threesome with Kruger as the dom, and you and Kalahari as the subs.",
//								true, true,
//								new SMKrugerThreesome(
//										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kruger.class), SexPositionSlot.DOGGY_BEHIND)),
//										Util.newHashMapOfValues(
//												new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
//												new Value<>(Main.game.getNpc(Kalahari.class), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
//								WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX,
//								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_SEX_AS_SUB"));
//					}
//					
//				}
				else if(index==0) {
					return new Response("完成", "让卡拉哈利回去工作。", WATERING_HOLE_KALAHARI_BREAK_END) {
						@Override
						public void effects() {
							Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_BAR);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, false);
							Main.game.getNpc(Kalahari.class).equipClothing();
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_BREAK = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK")
					+ getKalahariStatus(true, this.getSecondsPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	

	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_TALK = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_FLIRT = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_FLIRT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_KISS = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_KISS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_GROPE = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_GROPE")
					+ "<p>"
						+ Main.game.getNpc(Kalahari.class).getBreastDescription()
					+ "</p>"
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_GROPE_AFTER_BREAST_REVEAL")
						+ Main.game.getNpc(Kalahari.class).getVaginaDescription()
					+ "</p>"
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_GROPE_AFTER_PUSSY_REVEAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX = new DialogueNode("结束", "卡拉哈利得回去工作……", true) {
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Kalahari.class))>=Main.game.getNpc(Kalahari.class).getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "回到夜店的主要区域。", WATERING_HOLE_MAIN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_MAIN_AREA, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_END = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_OUT_OF_TIME = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_OUT_OF_TIME");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode WATERING_HOLE_VIP = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.krugerIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_BLOCKED", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.krugerIntroduced)) {
				if(index==1) {
					if(hasPartner()) {
						return new Response("克鲁格", UtilText.parse(getClubbersPresent(), "当[npc.name]和你在一起时，你不能和克鲁格说话。"), null);
						
					} else if(Main.game.getNpc(Kruger.class).getLastTimeHadSex() >= Main.game.getMinutesPassed()-(60*12)) {
						return new Response("克鲁格", "你今晚已经和克鲁格做过爱了。"
								+ "虽然你不介意和他多玩一会儿，但你也看得出来，如果你继续去找他，他的耐心很快就会被消磨殆尽。"
								+ "你可以明晚再来骑他的鸡巴。", null);
						
					} else {
						return new Response("克鲁格", "走到克鲁格跟前打个招呼。", WATERING_HOLE_VIP_KRUGER) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER"));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER.getSecondsPassed(), false));
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Kruger.class), true);
							}
						};
					}
				}
				return null;
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER = new DialogueNode("饮水洼夜店", "", true) {
		
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
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(index==1) {
				return new Response("交谈", "跟克鲁格谈一会儿话。", WATERING_HOLE_VIP_KRUGER_TALK) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kruger.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_TALK.getSecondsPassed(), false));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isFeminine()) {
					return new Response("调情", "与克鲁格调情。", WATERING_HOLE_VIP_KRUGER_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kruger.class).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_FLIRT.getSecondsPassed(), false));
						}
					};
				} else {
					return new Response("调情", "克鲁格是女性恋，所以对你不感性趣。", null);
				}
				
			} else if(index==3) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("亲吻", "克鲁格是女性恋，所以没兴趣和你发生性关系。", null);
					
				} else if(!likesKiss(Main.game.getNpc(Kruger.class), false)) {
					return new Response("亲吻", "克鲁格似乎对亲吻你不感兴趣。", null);
					
				} else {
					return new Response("亲吻", "看得出来，克鲁格想吻你。向前倾，让他吻你。", WATERING_HOLE_VIP_KRUGER_KISSED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kruger.class).incrementAffection(Main.game.getPlayer(), 15));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_KISSED.getSecondsPassed(), false));
						}
					};
				}
				
			} else if(index==4) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("爱抚", "克鲁格是女性恋，所以没兴趣和你发生性关系。", null);
					
				} else if(!likesGroping(Main.game.getNpc(Kruger.class), false)) {
					return new Response("爱抚", "克鲁格现在似乎对爱抚你不感兴趣。", null);
					
				} else {
					return new Response("爱抚", "看得出来，克鲁格想和你有一些身体接触。走到他身边，让他爱抚你。", WATERING_HOLE_VIP_KRUGER_FELT_UP) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kruger.class).incrementAffection(Main.game.getPlayer(), 20));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_FELT_UP.getSecondsPassed(), false));
						}
					};
					
				}
				
			} else if(index==5) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("做爱(顺从)", "克鲁格是女性恋，所以对你不感性趣。", null);
					
				} else if(!likesSex(Main.game.getNpc(Kruger.class), false)) {
					return new Response("做爱(顺从)", "克鲁格现在不想和你做爱。", null);
					
				} else {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
							&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
							&& (!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))) {
						return new Response("做爱(顺从)",
								"克鲁格需要能够使用"
									+ (Main.game.getPlayer().hasVagina()
										?(Main.game.isAnalContentEnabled()
											?"你的嘴，小穴或者肛门"
											:"你的嘴或小穴")
										:(Main.game.isAnalContentEnabled()
											?"你的嘴或者肛门"
											:"你的嘴"))
								+ "来和你做爱！",
								null);
					}
					return new ResponseSex("做爱(顺从)", "告诉克鲁格你想被他操……",
							true, true,
							new SMKrugerChair(
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kruger.class), SexSlotSitting.SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
							null,
							null,
							WATERING_HOLE_VIP_KRUGER_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_SEX_AS_SUB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kruger.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
							} else if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kruger.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, true, true));
							} else {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kruger.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, true, true));
							}
						}
					};
				}
				
			} else if(index==0) {
				return new Response("离开", "跟克鲁格说你得走了。", WATERING_HOLE_VIP_KRUGER_LEAVE);
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_TALK = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP_KRUGER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_FLIRT = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_FLIRT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP_KRUGER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_KISSED = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_KISSED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP_KRUGER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_FELT_UP = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_FELT_UP");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP_KRUGER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_AFTER_SEX = new DialogueNode("结束", "克鲁格和你做完了，进入了不应期，你准备好被他从身上推开。", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_LEAVE = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_LEAVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(hasPartner()) {
				if(index==1) {
					return new Response("跳舞", UtilText.parse(getClubbersPresent(), "与[npc.namePos]跳一会儿舞。"), WATERING_HOLE_DANCE_FLOOR_DANCE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_DANCE_FLOOR_DANCE.getSecondsPassed(), false));
						}
					};
					
				} else if(index==2) {
					return new Response("亲吻",
							UtilText.parse(getClubbersPresent(), "靠近几步亲吻[npc.name]。"+(likesKiss(getPartner(), false)?"":"</br>[style.italicsBad([npc.She]可能不会有什么好脸色！)]")),
							WATERING_HOLE_DANCE_FLOOR_KISS) {
						@Override
						public void effects() {
							if(likesKiss(getPartner(), false)) {
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_KISS", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KISS_CONTENT", getClubbersPresent()));
								Main.game.getTextStartStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_KISS_REJECTED", getClubbersPresent()));
								Main.game.getTextStartStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -15));
								
							}
						}
					};
					
				} else if(index==3) {
					return new Response("爱抚",
							UtilText.parse(getClubbersPresent(), "磨蹭[npc.name]并开始摸索[npc.herHim]。"+(likesGroping(getPartner(), false)?"":"</br>[style.italicsBad([npc.She]可能不会有什么好脸色！)]")),
							WATERING_HOLE_DANCE_FLOOR_GROPE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner(), false)) {
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_GROPE", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_GROPE_CONTENT", getClubbersPresent()));
								Main.game.getTextStartStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 25));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_GROPE_REJECTED", getClubbersPresent()));
								Main.game.getTextStartStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								
							}
						}
					};
					
				} else if(index==9) {
					return new Response("道别",
							UtilText.parse(getClubbersPresent(), "告诉[npc.name]，你要离开一会儿，但希望以后能再次见到[npc.herHim]。"
									+ "</br>[style.italicsGood(保存该角色，可以再次在夜店遇到。)]"),
							WATERING_HOLE_DANCE_FLOOR_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==10) {
					return new Response("停止作伴",
							UtilText.parse(getClubbersPresent(), "找个借口赶[npc.name]走。</br>[style.italicsBad(在游戏中移除该角色。)]"),
							WATERING_HOLE_DANCE_FLOOR_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("跳舞", "跳一会舞。", WATERING_HOLE_DANCE_FLOOR_DANCE);
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR_DANCE = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(hasPartner()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_DANCE", getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_DANCE_SOLO");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR_KISS = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR_GROPE = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR_LOSE_COMPANY = new DialogueNode("饮水洼夜店", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_TOILETS = new DialogueNode("厕所", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(index==1) {
				if(Main.game.getCurrentDialogueNode()==WATERING_HOLE_TOILETS_USE) {
					return new Response("厕所", "你已经在厕所了……", null);
				}
				return new Response("厕所", "使用厕所。", WATERING_HOLE_TOILETS_USE);
				
			} else if(index==2) {
				if(Main.game.getCurrentDialogueNode()==WATERING_HOLE_TOILETS_WASH) {
					return new Response("清洗", "你已经洗过了……", null);
				}
				List<InventorySlot> washSlots = Util.newArrayListOfValues(InventorySlot.HEAD, InventorySlot.EYES, InventorySlot.MOUTH, InventorySlot.NECK, InventorySlot.HAIR, InventorySlot.FINGER, InventorySlot.HAND, InventorySlot.WRIST);
				return new Response("洗澡",
						"用洗手池清洗手和脸。"
							+ "<br/>[style.italicsGood(这将清理你的"+Util.inventorySlotsToParsedStringList(washSlots, Main.game.getPlayer())+"，以及穿着在这些栏位上的衣物。)]"
							+ "<br/>[style.italicsMinorBad(<b>不会</b>为同伴清理。)]",
							WATERING_HOLE_TOILETS_WASH) {
					@Override
					public void effects() {
						for(InventorySlot slot : washSlots) {
							Main.game.getPlayer().removeDirtySlot(slot, true);
							AbstractClothing c = Main.game.getPlayer().getClothingInSlot(slot);
							if(c!=null) {
								c.setDirty(Main.game.getPlayer(), false);
							}
						}
					}
				};
				
			}
			
			if(hasPartner()) {
				if(index==3) {
					if(!getPartner().isAttractedTo(Main.game.getPlayer())) {
						return new Response("隔间做爱",
								UtilText.parse(getClubbersPresent(), "[npc.Name]对你[style.colorBad(不感兴趣)]，因此不愿意与你发生性关系……"),
								null);
					}
					if(likesSex(getPartner(), false)) {
						return new ResponseSex("隔间做爱", UtilText.parse(getClubbersPresent(), "试着让[npc.name]在厕所的一个隔间里做爱。"),
								true, true,
								new SMStallSex(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_SUBMISSIVE))) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								null,
								null,
								WATERING_HOLE_TOILETS_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_SEX", getClubbersPresent()));
						
					} else {
						if(getClubbersPresent().get(0).hasFetish(Fetish.FETISH_NON_CON_SUB)) {
							return new ResponseSex("隔间“强暴”", UtilText.parse(getClubbersPresent(),
										"尽管[npc.name]现在似乎对做爱没什么兴趣，但[npc.her]的“"+Fetish.FETISH_NON_CON_SUB.getName(getClubbersPresent().get(0))+"”性癖"
												+ "意味着[npc.she]愿意在其中一个厕所隔间与你进行强奸play。"),
									false, false,
									new SMStallSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_SUBMISSIVE))) {
										@Override
										public boolean isPublicSex() {
											return false;
										}
										@Override
										public boolean isRapePlayBannedAtStart(GameCharacter character) {
											return false;
										}
									},
									null,
									null,
									WATERING_HOLE_TOILETS_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_RAPE", getClubbersPresent()));
							
						} else {
							return new Response("隔间做爱",
									UtilText.parse(getClubbersPresent(), "试着让[npc.name]在厕所的一个隔间里做爱。</br>[style.italicsBad([npc.She]可能不会有什么好脸色！)]"),
									WATERING_HOLE_TOILETS_SEX_REJECTED) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), -25));
									Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_TOILETS_SEX_REJECTED.getSecondsPassed(), false));
								}
								@Override
								public boolean isSexHighlight() {
									return true;
								}
							};
						}
					}
					
				} else if(index==4) {
					// If the partner wants normal sex, add the option for rape play to index 4 instead of 3:
					if(likesSex(getPartner(), false) && getClubbersPresent().get(0).hasFetish(Fetish.FETISH_NON_CON_SUB)) {
						return new ResponseSex("隔间“强暴”", UtilText.parse(getClubbersPresent(),
								"尽管[npc.name]愿意与你进行常规性爱，但你也可以利用[npc.her]的“"+Fetish.FETISH_NON_CON_SUB.getName(getClubbersPresent().get(0))+"”性癖"
										+ "让[npc.herHim]在其中一个厕所隔间与你进行强奸play。"),
							false, false,
							new SMStallSex(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_SUBMISSIVE))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
								@Override
								public boolean isRapePlayBannedAtStart(GameCharacter character) {
									return false;
								}
							},
							null,
							null,
							WATERING_HOLE_TOILETS_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_RAPE_PLAY", getClubbersPresent()));
					}
				}
				
			} else {
				if(index==3) {
					boolean penisAvailable = Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
					boolean vaginaAvailable = Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
					
					if((penisAvailable && !Main.game.getPlayer().isTaur()) || vaginaAvailable) {
						return new Response("寻欢洞(使用)",
								"厕所的几个隔间上有寻欢洞。走上前去，让另一侧的人为你服务。",
								WATERING_HOLE_TOILETS_GLORY_HOLE_USING_GET_READY) {
							@Override
							public void effects() {
								spawnSubGloryHoleNPC("陌生人");
							}
						};
						
					} else if(penisAvailable && Main.game.getPlayer().isTaur()) {
						return new Response("寻欢洞(使用)",
								"由于你[pc.legRace]身躯的构造，你找不到一个使用寻欢洞的合适姿势……",
								null);
						
					} else {
						return new Response("寻欢洞(使用)",
								"你无法使用你的生殖器，所以无法在寻欢洞接受服务。",
								null);
					}
					
				} else if(index==4) {
					if((Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true))
							|| (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
							|| (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))) {
						return new Response("寻欢洞(服务)",
								"厕所的几个隔间上有寻欢洞。跪下来，准备为从洞里出来的东西服务。",
								WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_GET_READY) {
							@Override
							public void effects() {
								spawnDomGloryHoleNPC("陌生人");
								spawnDomGloryHoleNPC("陌生人");
							}
						};
					
					} else {
						return new Response("寻欢洞(服务)",
								"你无法使用嘴巴、生殖器或肛门，所以无法在寻欢洞为陌生人服务。",
								null);
					}
					
				} else if(index==5) {
					if(Main.game.getCurrentDialogueNode()==WATERING_HOLE_TOILETS_POSTERS) {
						return new Response("海报", "你已经在看海报了……", null);
					}
					return new Response("海报", "看看海报。", WATERING_HOLE_TOILETS_POSTERS);
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_GLORY_HOLE_USING_GET_READY = new DialogueNode("厕所", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_USING_GET_READY", getGloryHoleCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开", "你又想了一下，其实你不怎么想让陌生人用你的隐私部位寻欢作乐……", WATERING_HOLE_TOILETS) {
					@Override
					public void effects() {
						for(GameCharacter npc : getGloryHoleCharacters()) {
							Main.game.banishNPC((NPC) npc);
						}
					}
				};
				
			} else if(index==1) {
				List<GameCharacter> characters = getGloryHoleCharacters();
				
				return new ResponseSex("使用寻欢洞", UtilText.parse(characters.get(0), "按照[npc.name]说的做，走到寻欢洞前。"),
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
								Util.newHashMapOfValues(new Value<>(characters.get(0), SexSlotUnique.GLORY_HOLE_KNEELING))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						WATERING_HOLE_TOILETS_GLORY_HOLE_USING_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_START_USING", characters));
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_GLORY_HOLE_USING_POST_SEX = new DialogueNode("厕所", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_USING_POST_SEX", getGloryHoleCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "走出摊位。", WATERING_HOLE_TOILETS) {
					@Override
					public void effects() {
						for(GameCharacter npc : getGloryHoleCharacters()) {
							Main.game.banishNPC((NPC) npc);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_GET_READY = new DialogueNode("厕所", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_GET_READY", getGloryHoleCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开", "你又想了一下，其实你不怎么想吸陌生人的屌……", WATERING_HOLE_TOILETS) {
					@Override
					public void effects() {
						for(GameCharacter npc : getGloryHoleCharacters()) {
							Main.game.banishNPC((NPC) npc);
						}
					}
				};
				
			} else if(index==1) {
				List<GameCharacter> characters = getGloryHoleCharacters();
				
				return new ResponseSex("开始(关门)", "关上门，你开始为面前的鸡巴服务，并给自己留出一些私人空间。",
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(
										new Value<>(characters.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE),
										new Value<>(characters.get(1), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_KNEELING))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_START_SERVICING", characters));
				
			} else if(index==2) {
				List<GameCharacter> characters = getGloryHoleCharacters();
				
				return new ResponseSex("开始(公开展示)", "让门开着，这样厕所里的人都能旁观你是怎么服务他人的。",
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(
										new Value<>(characters.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE),
										new Value<>(characters.get(1), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_KNEELING))) {
						},
						null,
						null,
						WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_START_SERVICING_PUBLIC", characters));
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX = new DialogueNode("厕所", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.sex.isPublicSex()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX_PUBLIC", getGloryHoleCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX", getGloryHoleCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "走出摊位。", WATERING_HOLE_TOILETS) {
					@Override
					public void effects() {
						for(GameCharacter npc : getGloryHoleCharacters()) {
							Main.game.banishNPC((NPC) npc);
						}
					}
				};
			}
			return null;
		}
	};
	
	private static String gloryholeNpcNameDescriptor ="";
	private static void spawnDomGloryHoleNPC(String genericName) {
		NPC npc = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, true), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false, (s)->s.isNonBiped()) {
			@Override
			public void turnUpdate() {
				if(this.getGenitalArrangement()==GenitalArrangement.NORMAL) { // Hide ass areas if normal genitals (not entirely sure why this was added...)
					this.setAreaKnownByCharacter(CoverableArea.ASS, Main.game.getPlayer(), false);
					this.setAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer(), false);
				}
			}
		};
		
		npc.setRaceConcealed(true);
		
		double rnd = Math.random();
		if(rnd<0.1f && !gloryholeNpcNameDescriptor.equals("wasted")) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), npc, false);
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), npc, false);
			gloryholeNpcNameDescriptor ="昏迷";
			npc.setGenericName("烂醉的"+genericName);
			
		} else if(Math.random()<0.3f && !gloryholeNpcNameDescriptor.equals("drunk")) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey"), npc, false);
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_horse_equine_cider"), npc, false);
			gloryholeNpcNameDescriptor ="醉酒";
			npc.setGenericName("醉酒的"+genericName);
			
		} else if(Math.random()<0.4f && !gloryholeNpcNameDescriptor.equals("tipsy")) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_horse_equine_cider"), npc, false);
			gloryholeNpcNameDescriptor ="微醺";
			npc.setGenericName("微醺的"+genericName);
			
		} else {
			gloryholeNpcNameDescriptor = Main.game.getCharacterUtils().setGenericName(npc, genericName, Util.newArrayListOfValues(gloryholeNpcNameDescriptor));
		}
		
		npc.setDescription("[npc.Name]是饮水洼的顾客之一，[npc.her]想从夜店的舞池离开休息一下，于是走进了厕所，发现你正在寻欢洞为人服务……");
		
		if(Math.random()<0.4f) {
			npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}
		npc.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.TWO_NEUTRAL);
		npc.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
		npc.removeFetish(Fetish.FETISH_NON_CON_SUB);
		if(npc.hasVagina()) {
			npc.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		if(npc.hasPenis()) {
			npc.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
		}

		npc.unequipAllClothingIntoVoid(true, true);
		
		npc.setPenisVirgin(false);
		npc.setVaginaVirgin(false);
		
		npc.setAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer(), false);
		try {
			Main.game.addNPC(npc, false);
			Main.game.setActiveNPC(npc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void spawnSubGloryHoleNPC(String genericName) {
		NPC npc = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, false), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false, (s)->s.isNonBiped());
		
		npc.setRaceConcealed(true);
		
		List<String> descriptors;
		double rnd = Math.random();
		if(rnd<0.1f) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), npc, false);
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), npc, false);
			descriptors = Util.newArrayListOfValues("烂醉如泥", "酩酊大醉");
			
		} else if(Math.random()<0.3f) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey"), npc, false);
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_horse_equine_cider"), npc, false);
			descriptors = Util.newArrayListOfValues("醉醺醺");
			
		} else if(Math.random()<0.4f) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_horse_equine_cider"), npc, false);
			descriptors = Util.newArrayListOfValues("微醺");
			
		} else {
			descriptors = Util.newArrayListOfValues("淫靡", "狂乱", "浪荡");
		}
		npc.setGenericName(Util.randomItemFrom(descriptors)+"的"+genericName);
		
		npc.setDescription("[npc.Name]是饮水洼的顾客之一，[npc.her]想从夜店的舞池离开休息一下，于是走进了厕所，在寻欢洞为人服务……");
		
		if(Math.random()<0.4f) {
			npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}
		npc.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.TWO_NEUTRAL);
		npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
		npc.removeFetish(Fetish.FETISH_NON_CON_SUB);
		if(npc.hasVagina()) {
			npc.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		if(Math.random()>0.75f) {
			npc.addFetish(Fetish.FETISH_ORAL_GIVING);
		}
		
		npc.unequipAllClothingIntoVoid(true, true);
		
		npc.setPenisVirgin(false);
		npc.setVaginaVirgin(false);
		npc.setAssVirgin(false);
		npc.setFaceVirgin(false);
		
		npc.setAllAreasKnownByCharacter(Main.game.getPlayer(), false);
		
		try {
			Main.game.addNPC(npc, false);
			Main.game.setActiveNPC(npc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final DialogueNode WATERING_HOLE_TOILETS_AFTER_SEX = new DialogueNode("结束", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getClubbersPresent(), "[npc.Name]已经享受够了……");
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getPartner())>=getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_NO_ORGASM", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("再见一面",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.name]你想和[npc.herHim]再见一面。</br>"
								+ "[style.italicsGood(保存该角色，然后就有机会在夜店中再度邂逅。)]"),
						WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN", getClubbersPresent()));
						saveClubbers();
					}
				};
				
			} else if(index==2) {
				return new Response("还是算了(委婉)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "做出不置可否的回应，暗自希望不要再看到[npc.name]。</br>[style.italicsBad(将此角色从游戏中删除。)]"),
						WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_DO_NOT_SEE_AGAIN", getClubbersPresent()));
						removeClubbers();
					}
				};
				
			} else if(index==3) {
				return new Response("还是算了(直接)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "残忍地告诉[npc.name]，你只是想操[npc.herHim]。</br>[style.italicsBad(在游戏中移除该角色。)]"),
						WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", getClubbersPresent()));
						removeClubbers();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN = new DialogueNode("饮水洼夜店", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_SEX_REJECTED = new DialogueNode("厕所", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_SEX_REJECTED", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_USE = new DialogueNode("厕所", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_USE", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_TOILETS_WASH = new DialogueNode("厕所", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_WASH", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_TOILETS_POSTERS = new DialogueNode("厕所", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_POSTERS", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};
	
	// Dom partner:
	
	public static final DialogueNode WATERING_HOLE_SEARCH_GENERATE_DOM = new DialogueNode("饮水洼夜店", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENERATE_DOM", getClubbersPresent())
					+getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DOM_PARTNER_REACT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_CONTACTS_DOM = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 0*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_CONTACTS_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("返回", "决定不再找你以前邂逅过的人。", WATERING_HOLE_MAIN);
			}
			int count = 1;
			for(GameCharacter character : getSavedClubbers(false)) {
				if(count==index) {
					if(!character.isAttractedTo(Main.game.getPlayer())) {
						return new Response(character.getName(true),
								UtilText.parse(character, "[npc.Name][style.colourBad(没被你吸引)]，不愿意和你在夜店里消磨时间。<br/>([npc.She]是[npc.a_fullRace(true)]。)"),
								null);
					}
					if(Main.game.getMinutesPassed()-((NPC)character).getLastTimeEncountered()<12*60) {
						return new Response(character.getName(true),
								UtilText.parse(character, "你今晚已经在夜店遇见过[npc.name]了，[style.colourBad(你明天才能再次邂逅[npc.herHim])]。"),
								null);
					}
					return new Response(character.getName(true),
							UtilText.parse(character, "在狂欢的人群中寻找[npc.Name]。<br/>([npc.She]是[npc.a_fullRace(true)]。)"),
							WATERING_HOLE_FIND_CONTACT_DOM) {
						@Override
						public void effects() {
							character.setLocation(WorldType.NIGHTLIFE_CLUB, Main.game.getPlayer().getLocation(), false);
//							character.setAffection(Main.game.getPlayer(), 5);
							domPartnerNightlyAffection = 5;
							resetPreviousBehaviour(); 
						}
					};
				}
				count++;
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_FIND_CONTACT_DOM = new DialogueNode("饮水洼夜店", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FIND_CONTACT_DOM", getClubbersPresent())
					+getClubberStatus(this.getSecondsPassed(), true);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DOM_PARTNER_REACT.getResponse(responseTab, index);
		}
	};

	
	private static ClubberBehaviour currentBehaviour = ClubberBehaviour.INTRODUCTION;
	private static int turnsAtPlace = 0;
	private static boolean buyingDrinks = true;
	private static int domPartnerNightlyAffection = 0;
	
	private static String incrementDominantPartnerAffection(int increment) {
		domPartnerNightlyAffection += increment;
		domPartnerNightlyAffection = Math.max(-100, Math.min(100, domPartnerNightlyAffection));
		if(domPartnerNightlyAffection<=getPartner().getAffection(Main.game.getPlayer()) && domPartnerNightlyAffection<100) {
			return "";
		}
		return getPartner().incrementAffection(Main.game.getPlayer(), increment);
	}
	
	private static void resetPreviousBehaviour() {
		currentBehaviour = ClubberBehaviour.INTRODUCTION;
		turnsAtPlace = 0;
		buyingDrinks = true;
		domPartnerNightlyAffection = 0;
	}
	
	private static AbstractPlaceType getCurrentPlaceType() {
		return Main.game.getPlayer().getLocationPlace().getPlaceType();
	}
	
	private static boolean isWillingToMoveLocation() {
		return turnsAtPlace>=2 || currentBehaviour == ClubberBehaviour.INTRODUCTION;
	}
	
	private static boolean isPartnerOfferingDrinks() {
		if(buyingDrinks) {
			if(getPartner().hasPersonalityTrait(PersonalityTrait.SELFISH)) {
				return Main.game.getPlayer().getAlcoholLevelValue() < AlcoholLevel.FOUR_HAMMERED.getMinimumValue();
				
			} else if(getPartner().hasPersonalityTrait(PersonalityTrait.KIND)) {
				return Main.game.getPlayer().getAlcoholLevelValue() < AlcoholLevel.ONE_TIPSY.getMinimumValue();
				
			} else {
				return Main.game.getPlayer().getAlcoholLevelValue() < AlcoholLevel.THREE_DRUNK.getMinimumValue();
			}
		}
		return false;
	}
	
	/*
		Sleazy - bar, dance floor, toilets
			Wants you wasted
			Doesn't care about talking
			Likes feeling up/kissing
			Wants toilet sex
		Normal - bar, dance floor, seating area
			Wants you drunk
			Needs to talk -> flirt -> kiss -> grope
			Seating area sex
		Nice - bar, seating area, home
			Wants you tipsy
			Needs to talk -> flirt -> kiss
			Invites you home
	 */
	
	private static ClubberBehaviour getClubberBehaviour() {
		
		if(isPartnerLeaving(true)) {
			return ClubberBehaviour.LEAVES;
		}
		
		if(getPartner().hasPersonalityTrait(PersonalityTrait.SELFISH)) { // Only goes to: Bar, dance floor, and toilets.
			if(currentBehaviour!=ClubberBehaviour.BAR_DRINK
				&& isPartnerOfferingDrinks()
				&& (getCurrentPlaceType()==ClubberBehaviour.BAR_DRINK.getPlaceType() || isWillingToMoveLocation())) {
				return ClubberBehaviour.BAR_DRINK;
			}
			
			if(isWillingToMoveLocation() && likesSex(getPartner(), true)) {
				return ClubberBehaviour.TOILETS;
				
			}
			if(!isWillingToMoveLocation()) {
				if(getCurrentPlaceType()==ClubberBehaviour.DANCE_GROPE.getPlaceType()) {
					if(currentBehaviour!=ClubberBehaviour.DANCE_GROPE) {
						return ClubberBehaviour.DANCE_GROPE;
					} else {
						return ClubberBehaviour.DANCE_KISS;
					}
				} else {
					if(likesKiss(getPartner(), true)) {
						if(currentBehaviour!=ClubberBehaviour.BAR_GROPE) {
							return ClubberBehaviour.BAR_GROPE;
						} else {
							return ClubberBehaviour.BAR_KISS;
						}
					} else {
						if(currentBehaviour!=ClubberBehaviour.BAR_FLIRT) {
							return ClubberBehaviour.BAR_FLIRT;
						} else {
							return ClubberBehaviour.BAR_KISS;
						}
					}
				}
				
			} else {
				if(getCurrentPlaceType()!=ClubberBehaviour.DANCE_GROPE.getPlaceType()) {
					if(currentBehaviour!=ClubberBehaviour.DANCE_GROPE) {
						return ClubberBehaviour.DANCE_GROPE;
					} else {
						return ClubberBehaviour.DANCE_KISS;
					}
				} else {
					if(likesKiss(getPartner(), true)) {
						if(currentBehaviour!=ClubberBehaviour.BAR_GROPE) {
							return ClubberBehaviour.BAR_GROPE;
						} else {
							return ClubberBehaviour.BAR_KISS;
						}
					} else {
						if(currentBehaviour!=ClubberBehaviour.BAR_FLIRT) {
							return ClubberBehaviour.BAR_FLIRT;
						} else {
							return ClubberBehaviour.BAR_KISS;
						}
					}
				}
			}
			
		} else if(getPartner().hasPersonalityTrait(PersonalityTrait.KIND)) { // Only goes to: Bar, seating area.
			if(currentBehaviour!=ClubberBehaviour.BAR_DRINK
					&& isPartnerOfferingDrinks()
					&& (getCurrentPlaceType()==ClubberBehaviour.BAR_DRINK.getPlaceType() || isWillingToMoveLocation())) {
				return ClubberBehaviour.BAR_DRINK;
			}
			
			if(likesSex(getPartner(), true)) {
				if(getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_INVITE_HOME.getPlaceType()) {
					return ClubberBehaviour.SIT_DOWN_INVITE_HOME;
				} else {
					return ClubberBehaviour.BAR_INVITE_HOME;
				}
			}
			if(likesGroping(getPartner(), true)) {
				if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_FOOTSIE.getPlaceType())) {
					if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_FOOTSIE) {
						return ClubberBehaviour.SIT_DOWN_FOOTSIE;
					} else {
						return ClubberBehaviour.SIT_DOWN_KISS;
					}
					
				} else {
					if(currentBehaviour!=ClubberBehaviour.BAR_GROPE) {
						return ClubberBehaviour.BAR_GROPE;
					} else {
						return ClubberBehaviour.BAR_KISS;
					}
				}
			}
			if(likesKiss(getPartner(), true)) {
				if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_KISS.getPlaceType())) {
					if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_KISS) {
						return ClubberBehaviour.SIT_DOWN_KISS;
					} else {
						return ClubberBehaviour.SIT_DOWN_FLIRT;
					}
					
				} else {
					if(currentBehaviour!=ClubberBehaviour.BAR_KISS) {
						return ClubberBehaviour.BAR_KISS;
					} else {
						return ClubberBehaviour.BAR_FLIRT;
					}
				}
			}
			if((getCurrentPlaceType()==ClubberBehaviour.BAR_FLIRT.getPlaceType() || isWillingToMoveLocation()) && isPartnerOfferingDrinks()) {
				if(currentBehaviour!=ClubberBehaviour.BAR_FLIRT) {
					return ClubberBehaviour.BAR_FLIRT;
				} else {
					return ClubberBehaviour.BAR_TALK;
				}
				
			} else {
				if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_FLIRT) {
					return ClubberBehaviour.SIT_DOWN_FLIRT;
				} else {
					return ClubberBehaviour.SIT_DOWN_TALK;
				}
			}
				
		} else { // Only goes to: Bar, dance floor, and seating area.
			if(currentBehaviour!=ClubberBehaviour.BAR_DRINK
					&& isPartnerOfferingDrinks()
					&& (getCurrentPlaceType()==ClubberBehaviour.BAR_DRINK.getPlaceType() || isWillingToMoveLocation())) {
				return ClubberBehaviour.BAR_DRINK;
			}
			
			if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_SEX.getPlaceType()) && likesSex(getPartner(), true)) {
				return ClubberBehaviour.SIT_DOWN_SEX;
			}
			if(likesGroping(getPartner(), true)) {
				if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_FOOTSIE.getPlaceType())) {
					if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_FOOTSIE) {
						return ClubberBehaviour.SIT_DOWN_FOOTSIE;
					} else {
						return ClubberBehaviour.SIT_DOWN_KISS;
					}
					
				} else if(getCurrentPlaceType()==ClubberBehaviour.DANCE_GROPE.getPlaceType()) {
					if(currentBehaviour!=ClubberBehaviour.DANCE_GROPE) {
						return ClubberBehaviour.DANCE_GROPE;
					} else {
						return ClubberBehaviour.DANCE_KISS;
					}
					
				} else {
					if(currentBehaviour!=ClubberBehaviour.BAR_GROPE) {
						return ClubberBehaviour.BAR_GROPE;
					} else {
						return ClubberBehaviour.BAR_KISS;
					}
				}
			}
			if(likesKiss(getPartner(), true)) {
				if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_KISS.getPlaceType())) {
					if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_KISS) {
						return ClubberBehaviour.SIT_DOWN_KISS;
					} else {
						return ClubberBehaviour.SIT_DOWN_FLIRT;
					}
					
				} else if(getCurrentPlaceType()==ClubberBehaviour.DANCE_GROPE.getPlaceType()) {
					if(currentBehaviour!=ClubberBehaviour.DANCE_KISS) {
						return ClubberBehaviour.DANCE_KISS;
					} else {
						return ClubberBehaviour.DANCE;
					}
					
				} else {
					if(currentBehaviour!=ClubberBehaviour.BAR_KISS) {
						return ClubberBehaviour.BAR_KISS;
					} else {
						return ClubberBehaviour.BAR_FLIRT;
					}
				}
			}
			if(getCurrentPlaceType()==ClubberBehaviour.DANCE.getPlaceType() || !isWillingToMoveLocation()) { // If dancing, just move back to bar
				if(currentBehaviour!=ClubberBehaviour.BAR_FLIRT) {
					return ClubberBehaviour.BAR_FLIRT;
				} else {
					return ClubberBehaviour.BAR_TALK;
				}
				
			} else {
				return ClubberBehaviour.DANCE;
			}
		}
	}
	
	private static void applyBehaviourEffects() {
		ClubberBehaviour newBehaviour = getClubberBehaviour();
		
		turnsAtPlace++;

		if(newBehaviour==ClubberBehaviour.LEAVES) {
			currentBehaviour = newBehaviour;
			return;
		}
		
		if(currentBehaviour.getPlaceType()!=newBehaviour.getPlaceType()) {
			if(newBehaviour.getPlaceType().equals(PlaceType.WATERING_HOLE_BAR)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_CHANGE_LOCATION_BAR", getClubbersPresent()));
				
			} else if(newBehaviour.getPlaceType().equals(PlaceType.WATERING_HOLE_DANCE_FLOOR)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_CHANGE_LOCATION_DANCE_FLOOR", getClubbersPresent()));
				
			} else if(newBehaviour.getPlaceType().equals(PlaceType.WATERING_HOLE_SEATING_AREA)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_CHANGE_LOCATION_SEATING_AREA", getClubbersPresent()));
				
			} else if(newBehaviour.getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_CHANGE_LOCATION_TOILETS", getClubbersPresent()));
			}

			turnsAtPlace=0;
		}
		
		currentBehaviour = newBehaviour;
		
		Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, currentBehaviour.getPlaceType());
	}
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER = new DialogueNode("饮水洼夜店", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			ClubberBehaviour behaviour = currentBehaviour;

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_"+behaviour.toString(), getClubbersPresent()));

			UtilText.nodeContentSB.append(getClubberStatus(this.getSecondsPassed(), true));
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
//			if(isEndConditionMet(0)) {
//				return getEndResponse(index, 0);
//			}
			
			ClubberBehaviour behaviour = currentBehaviour;
			
			switch(behaviour) {
				case LEAVES:
					if(index==9) {
						return new Response("道别",
								UtilText.parse(getClubbersPresent(), "告诉[npc.name]，尽管你今晚对[npc.herHim]的态度如此恶劣，但你希望能再次见到[npc.herHim]。"
										+ "</br>[style.italicsGood(保存该角色，可以再次在夜店遇到。)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_LEAVES_SAVE_CLUBBER", getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==10) {
						return new Response("停止作伴",
								UtilText.parse(getClubbersPresent(), "告诉[npc.name]你不想再见到[npc.herHim]。</br>[style.italicsBad(在游戏中移除该角色。)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_LEAVES_LOSE_COMPANY", getClubbersPresent()));
								removeClubbers();
							}
						};
					}
					break;
				case BAR_DRINK:
					if(getPartner().hasPersonalityTrait(PersonalityTrait.SELFISH)) {
						if(index==1) {
							// Accept rum
							return new Response("接受朗姆酒",
									UtilText.parse(NightlifeDistrict.getClubbersPresent(), "接受[npc.name]给你点的那杯黑鼠朗姆酒。<br/>"
											+getDrinkEffects(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"))),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									getPartner().incrementAlcoholLevel(-0.05f); // TO stop them from drinking to collapse
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_ACCEPT_RUM", getClubbersPresent()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum").applyEffect(getPartner(), getPartner()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
								}
							};
							
						} else if(index==2) {
							// Refuse rum
							return new Response("拒绝朗姆酒",
									UtilText.parse(NightlifeDistrict.getClubbersPresent(), "拒绝[npc.Name]给你点的那杯黑鼠朗姆酒。"),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_REFUSE_RUM", getClubbersPresent()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-15));
									buyingDrinks = false;
								}
							};
						}
						
					} else if(getPartner().hasPersonalityTrait(PersonalityTrait.KIND)) {
						if(index==1) {
							// Accept Feline's fancy/beer
							return new Response((Main.game.getPlayer().isFeminine()
										?"接受猫猫幻想"
										:"接受犬根宝"),
									(Main.game.getPlayer().isFeminine()
										?UtilText.parse(NightlifeDistrict.getClubbersPresent(), "接受[npc.Name]给你点的那杯猫猫幻想。<br/>"
												+getDrinkEffects(Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy")))
										:UtilText.parse(NightlifeDistrict.getClubbersPresent(), "接受[npc.Name]给你点的那杯犬根宝。<br/>"
												+getDrinkEffects(Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush")))),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									if(Main.game.getPlayer().isFeminine()) {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_ACCEPT_FELINES_FANCY", getClubbersPresent()));
										Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									} else {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_ACCEPT_BEER", getClubbersPresent()));
										Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									}
									if(getPartner().isFeminine()) {
										Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy").applyEffect(getPartner(), getPartner()));
									} else {
										Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush").applyEffect(getPartner(), getPartner()));
									}
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
								}
							};
						} else if(index==2) {
							// Refuse Feline's fancy/beer
							return new Response((Main.game.getPlayer().isFeminine()
										?"拒绝猫猫幻想"
										:"拒绝犬根宝"),
									(Main.game.getPlayer().isFeminine()
											?UtilText.parse(NightlifeDistrict.getClubbersPresent(), "拒绝[npc.Name]给你点的那杯猫猫幻想。")
											:UtilText.parse(NightlifeDistrict.getClubbersPresent(), "拒绝[npc.Name]给你点的那瓶犬根宝。")),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									if(Main.game.getPlayer().isFeminine()) {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_REFUSE_FELINES_FANCY", getClubbersPresent()));
									} else {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_REFUSE_BEER", getClubbersPresent()));
									}
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water").applyEffect(getPartner(), getPartner()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-5));
									buyingDrinks = false;
								}
							};
						}
						
					} else {
						if(index==1) {
							// Accept whiskey
							return new Response("接受威士忌",
									UtilText.parse(NightlifeDistrict.getClubbersPresent(), "接受[npc.Name]给你点的那杯头狼威士忌。<br/>"
										+getDrinkEffects(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey"))),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_ACCEPT_WOLF_WHISKEY", getClubbersPresent()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey").applyEffect(getPartner(), getPartner()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
								}
							};
							
						} else if(index==2) {
							// Refuse whiskey
							return new Response("拒绝威士忌",
									UtilText.parse(NightlifeDistrict.getClubbersPresent(), "拒绝[npc.Name]给你点的那杯头狼威士忌。"),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_REFUSE_WOLF_WHISKEY", getClubbersPresent()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water").applyEffect(getPartner(), getPartner()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-10));
									buyingDrinks = false;
								}
							};
						}
					}
					break;
				case BAR_FLIRT:
					if(index==1) {
						// Flirt back
						return new Response("调情回去",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "积极回应[npc.namePos]的调情话语，并接着与[npc.herHim]调情。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_FLIRT_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(10));
							}
						};
						
					} else if(index==2) {
						// Shut down
						return new Response("表示蔑视",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "对[npc.namePos]的调情言论做出负面回应，并让[npc.herHim]停止调情。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_FLIRT_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-10));
							}
						};
					}
					break;
				case BAR_GROPE:
					if(index==1) {
						// Let them grope you
						return new Response("屈服",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "靠在[npc.name]身上，让[npc.herHim]爱抚你。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_GROPE_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(20));
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.PENIS, getPartner(), true);
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.VAGINA, getPartner(), true);
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("推开",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "让[npc.name]离你远点。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_GROPE_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-20));
							}
						};
					}
					break;
				case BAR_INVITE_HOME:
					if(index==1) {
						return new Response("跟回家",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "跟着[npc.name]回到[npc.her]家，[npc.sheIs]肯定想和你做爱……"),
								WATERING_HOLE_DOM_PARTNER_TAKEN_HOME) {
							@Override
							public void effects() {
								
								for(GameCharacter clubber : getClubbersPresent()) {
									if(!clubber.getHomeLocationPlace().getPlaceType().equals(PlaceType.DOMINION_BOULEVARD)) {
										clubber.setRandomLocation(WorldType.DOMINION, PlaceType.DOMINION_BOULEVARD, true);
									} else {
										clubber.returnToHome();
									}
									Main.game.getPlayer().setLocation(WorldType.DOMINION, clubber.getLocation(), false);
									Main.game.setRequestAutosave(false); // Autosaving when moving world here will cause the NPC to disappear when loaded
								}
								
							}
						};
						
					} else if(index==4) {
						return new Response("拒绝(温柔)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.name]你没兴趣再去[npc.her]那里了，但希望下次能在夜店再次见到[npc.herHim]。"
										+ "</br>[style.italicsGood(保存该角色，可以再次在夜店遇到。)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BAR_INVITE_HOME_REFUSE_GENTLE", NightlifeDistrict.getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==5) {
						return new Response("拒绝(苛刻)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "直言不讳地告诉[npc.Name]，什么都比跟[npc.herHim]做爱有意思。"
										+ "</br>[style.italicsBad(将该角色从游戏中移除。)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BAR_INVITE_HOME_REFUSE_RUDE", NightlifeDistrict.getClubbersPresent()));
								NightlifeDistrict.removeClubbers();
							}
						};
						
					} else {
						return null;
					}
					
				case BAR_KISS:
					if(index==1) {
						// Let them kiss you
						return new Response("亲吻", 
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "靠着[npc.name]，开始和[npc.herHim]亲热。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_KISS_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("推开",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "推开[npc.name]并告诉[npc.herHim]让[npc.her]管好[npc.herself]的[npc.lips]。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_KISS_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-15));
							}
						};
					}
					break;
				case BAR_TALK:
					if(index==1) {
						// Continue conversation
						return new Response("继续谈话",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "继续愉悦地与[npc.name]交谈。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_TALK_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(5));
							}
						};
					} else if(index==2) {
						// Show boredom
						return new Response("保持沉默",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "在[npc.Name]停顿时，发出不置可否的哼哼声。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_TALK_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-5));
							}
						};
					}
					break;
				case DANCE:
					if(index==1) {
						// Dance
						return new Response("跳舞",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "走进舞池，开始与[npc.name]跳舞。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(10));
							}
						};
					} else if(index==2) {
						// Step back
						return new Response("拒绝",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.name]你没兴趣和[npc.herHim]跳舞。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-10));
							}
						};
					}
					break;
				case DANCE_GROPE:
					if(index==1) {
						// Let them grope you
						return new Response("屈服",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "靠在[npc.name]身上，让[npc.herHim]爱抚你。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_GROPE_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(20));
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.PENIS, getPartner(), true);
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.VAGINA, getPartner(), true);
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("推开",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "让[npc.name]离你远点。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_GROPE_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-20));
							}
						};
					}
					break;
				case DANCE_KISS:
					if(index==1) {
						// Let them kiss you
						return new Response("亲吻", 
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "靠着[npc.name]，开始和[npc.herHim]亲热。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_KISS_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("推开",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "推开[npc.name]并告诉[npc.herHim]管好自己的[npc.lips]。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_KISS_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-15));
							}
						};
					}
					break;
				case INTRODUCTION:
					break;
				case SIT_DOWN_FLIRT:
					if(index==1) {
						// Flirt back
						return new Response("调情回去",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "积极回应[npc.namePos]的调情话语，并接着与[npc.herHim]调情。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_FLIRT_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(10));
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("表示蔑视",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "对[npc.namePos]的调情言论做出负面回应，并让[npc.herHim]停止调情。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_FLIRT_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-10));
							}
						};
					}
					break;
				case SIT_DOWN_FOOTSIE:
					boolean bothBipeds = true;
					if(Main.game.getPlayer().isTaur() || getPartner().isTaur()) {
						bothBipeds = false;
					}
					// If both partners are bipeds, play footsie. If not, feeling up occurs instead.
					if(index==1) {
						// Enjoy
						return new Response(
								bothBipeds
									?"用脚轻轻摩擦"
									:"顺从",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(),
										bothBipeds
											?"让[npc.namePos]的[npc.foot]一直伸到你的下体，并开始回应[npc.her]的调情动作。"
											:"将你的身体背靠着[npc.name]，让[npc.herHim]爱抚你。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_FOOTSIE_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(20));
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.PENIS, getPartner(), true);
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.VAGINA, getPartner(), true);
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("移开",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(),
										bothBipeds
											?"拉开[npc.namePos][npc.foot]，然后愤怒地让[npc.herHim]住手。"
											:"挣开[npc.namePos]的触摸，愤怒地让[npc.herHim]住手。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_FOOTSIE_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-20));
							}
						};
					}
					break;
				case SIT_DOWN_INVITE_HOME:
					if(index==1) {
						return new Response("跟回家",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "跟着[npc.name]回到[npc.her]家，[npc.sheIs]肯定想和你做爱……"),
								WATERING_HOLE_DOM_PARTNER_TAKEN_HOME) {
							@Override
							public void effects() {
								
								for(GameCharacter clubber : getClubbersPresent()) {
									if(!clubber.getHomeLocationPlace().getPlaceType().equals(PlaceType.DOMINION_BOULEVARD)) {
										clubber.setRandomLocation(WorldType.DOMINION, PlaceType.DOMINION_BOULEVARD, true);
									} else {
										clubber.returnToHome();
									}
									Main.game.getPlayer().setLocation(WorldType.DOMINION, clubber.getLocation(), false);
									Main.game.setRequestAutosave(false); // Autosaving when moving world here will cause the NPC to disappear when loaded
								}
							}
						};
						
					} else if(index==4) {
						return new Response("拒绝(温柔)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.name]你没兴趣再去[npc.her]那里了，但希望下次能在夜店再次见到[npc.herHim]。"
										+ "</br>[style.italicsGood(保存该角色，可以再次在夜店遇到。)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "SIT_DOWN_INVITE_HOME_REFUSE_GENTLE", NightlifeDistrict.getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==5) {
						return new Response("拒绝(苛刻)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "直言不讳地告诉[npc.Name]，什么都比跟[npc.herHim]做爱有意思。"
										+ "</br>[style.italicsBad(将该角色从游戏中移除。)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "SIT_DOWN_INVITE_HOME_REFUSE_RUDE", NightlifeDistrict.getClubbersPresent()));
								NightlifeDistrict.removeClubbers();
							}
						};
						
					} else {
						return null;
					}
					
				case SIT_DOWN_KISS:
					if(index==1) {
						// Let them kiss you
						return new Response("亲吻",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "让[npc.name]开始亲你。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_KISS_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("推开",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "拒绝[npc.namePos]亲吻你的企图，并推开[npc.herHim]。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_KISS_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-15));
							}
						};
					}
					break;
				case SIT_DOWN_SEX:
					if(index==1) {
						SexManagerDefault sm = new SMSitting(
								Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public List<AbstractSexPosition> getAllowedSexPositions() {
								return Util.newArrayListOfValues(SexPosition.SITTING);
							}
						};

						if(getPartner().isTaur()) { // Partner is a taur/arachnid:
							sm = new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
								@Override
								public List<AbstractSexPosition> getAllowedSexPositions() {
									return Util.newArrayListOfValues(SexPosition.STANDING, SexPosition.SITTING);
								}
							};
						}
						
						return new ResponseSex("做爱(作为服从方)",
								UtilText.parse(getClubbersPresent(), "按[npc.Name]说的做，和[npc.herHim]做服从型性爱。"),
								true, true,
								sm,
								null,
								null,
								WATERING_HOLE_SEATING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_SEX_START", getClubbersPresent()));
						
					} else if(index==4) {
						return new Response("拒绝(温柔)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.Name]你现在不想和[npc.herHim]做爱，不过希望下次还能在夜店里遇见[npc.herHim]。"
										+ "</br>[style.italicsGood(保存该角色，可以再次在夜店遇到。)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_SEX_REFUSE_GENTLE", NightlifeDistrict.getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==5) {
						return new Response("拒绝(苛刻)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "直言不讳地告诉[npc.Name]，什么都比跟[npc.herHim]做爱有意思。"
										+ "</br>[style.italicsBad(将该角色从游戏中移除。)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_SEX_REFUSE_RUDE", NightlifeDistrict.getClubbersPresent()));
								NightlifeDistrict.removeClubbers();
							}
						};
						
					} else {
						return null;
					}
					
				case SIT_DOWN_TALK:
					if(index==1) {
						// Continue conversation
						return new Response("继续谈话",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "继续愉悦地与[npc.name]交谈。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_TALK_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(5));
							}
						};
					} else if(index==2) {
						// Show boredom
						return new Response("保持沉默",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "在[npc.Name]停顿时，发出不置可否的哼哼声。"),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_TALK_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-5));
							}
						};
					}
					break;
				case TOILETS:
					if(index==1) {
						return new ResponseSex("隔间做爱", UtilText.parse(getClubbersPresent(), "让[npc.Name]在其中一个厕所隔间操你。"),
								true, true,
								new SMStallSex(
										Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								null,
								null,
								WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_SEX_START", getClubbersPresent()));
						
					} else if(index==4) {
						return new Response("拒绝(温柔)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.Name]你现在不想和[npc.herHim]做爱，不过希望下次还能在夜店里遇见[npc.herHim]。"
										+ "</br>[style.italicsGood(保存该角色，可以再次在夜店遇到。)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_SEX_START_REFUSE_GENTLE", NightlifeDistrict.getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==5) {
						return new Response("拒绝(苛刻)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "直言不讳地告诉[npc.Name]，什么都比跟[npc.herHim]做爱有意思。"
										+ "</br>[style.italicsBad(将该角色从游戏中移除。)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_SEX_START_REFUSE_RUDE", NightlifeDistrict.getClubbersPresent()));
								NightlifeDistrict.removeClubbers();
							}
						};
						
					} else {
						return null;
					}
			}
			
			if(behaviour!=ClubberBehaviour.LEAVES) {
				if(index==9) {
					return new Response("道别",
							UtilText.parse(getClubbersPresent(), "告诉[npc.name]，你要离开一会儿，但希望以后能再次见到[npc.herHim]。"
									+ "</br>[style.italicsGood(保存该角色，可以再次在夜店遇到。)]"),
							UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==10) {
					return new Response("停止作伴",
							UtilText.parse(getClubbersPresent(), "找个借口赶[npc.name]走。</br>[style.italicsBad(在游戏中移除该角色。)]"),
							UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER_REACT = new DialogueNode("饮水洼夜店", "", true, true) {
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), true);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(index==1) {
				return new Response("继续", UtilText.parse(getClubbersPresent(), "看看[npc.name]接下来打算干什么……"), WATERING_HOLE_DOM_PARTNER) {
					@Override
					public void effects() {
						applyBehaviourEffects();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER_TAKEN_HOME = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getLabel() {
			return UtilText.parse(NightlifeDistrict.getClubbersPresent(), "[npc.NamePos]的公寓");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME", NightlifeDistrict.getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("做爱", UtilText.parse(NightlifeDistrict.getClubbersPresent(), "和[npc.name]做服从型性爱。"),
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(NightlifeDistrict.getClubbersPresent().get(0)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_SEX", NightlifeDistrict.getClubbersPresent()));
				
			} else if(index==4) {
				return new Response("拒绝",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.Name]，你真的不想回[npc.her]的地方去做爱……"
								+ "</br>[style.italicsGood(保存该角色，可以再次在夜店遇到。)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole",
								"WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_CHANGE_MIND", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.saveClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==5) {
				return new Response("恼怒地拒绝",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.Name]，你接受邀请去[npc.her]的地方的时候，根本没预料会有这些事！"
								+ "</br>[style.italicsBad(将该角色从游戏中移除。)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole",
								"WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_CHANGE_MIND_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getLabel() {
			return UtilText.parse(NightlifeDistrict.getClubbersPresent(), "[npc.NamePos]的公寓");
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(NightlifeDistrict.getPartner())>=NightlifeDistrict.getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX", NightlifeDistrict.getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX_NO_ORGASM", NightlifeDistrict.getClubbersPresent());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("再见一面",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.name]你想和[npc.herHim]再见一面。</br>"
								+ "[style.italicsGood(保存该角色，然后就有机会在夜店中再度邂逅。)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.saveClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==2) {
				return new Response("还是算了(委婉)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "做出不置可否的回应，暗自希望不要再看到[npc.name]。</br>[style.italicsBad(将此角色从游戏中删除。)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==3) {
				return new Response("还是算了(直接)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "残忍地告诉[npc.name]，你只是想操[npc.herHim]。</br>[style.italicsBad(在游戏中移除该角色。)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX = new DialogueNode("结束", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getClubbersPresent(), "[npc.Name]已经享受够了……");
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(NightlifeDistrict.getPartner())>=NightlifeDistrict.getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX", NightlifeDistrict.getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX_NO_ORGASM", NightlifeDistrict.getClubbersPresent());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("再见一面",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.name]你想和[npc.herHim]再见一面。</br>"
								+ "[style.italicsGood(保存该角色，然后就有机会在夜店中再度邂逅。)]"),
						PlaceType.WATERING_HOLE_TOILETS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.saveClubbers();
					}
				};
				
			} else if(index==2) {
				return new Response("还是算了(委婉)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "做出不置可否的回应，暗自希望不要再看到[npc.name]。</br>[style.italicsBad(将此角色从游戏中删除。)]"),
						PlaceType.WATERING_HOLE_TOILETS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX_DO_NOT_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
					}
				};
				
			} else if(index==3) {
				return new Response("还是算了(直接)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "残忍地告诉[npc.name]，你只是想操[npc.herHim]。</br>[style.italicsBad(在游戏中移除该角色。)]"),
						PlaceType.WATERING_HOLE_TOILETS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode UTIL_NEUTRAL_DIALOGUE_NO_TEXT = new DialogueNode("", "", false) {
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
			return Main.game.getDefaultDialogue().getResponse(responseTab, index);
		}
	};
	
}
