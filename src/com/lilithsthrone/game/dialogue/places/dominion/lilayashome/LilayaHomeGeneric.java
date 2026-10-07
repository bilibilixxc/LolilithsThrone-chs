package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantDialogue;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.managers.dominion.SMRoseHands;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.BaseColour;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.75
 * @version 0.3.9
 * @author Innoxia
 */
public class LilayaHomeGeneric {
	
	static Response interactWithNPC(GameCharacter slave) {
		return new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "和[npc.name]互动。"), slave.isSlave()?SlaveDialogue.SLAVE_START:OccupantDialogue.OCCUPANT_START) {
			@Override
			public Colour getHighlightColour() {
				return slave.getFemininity().getColour();
			}
			@Override
			public void effects() {
				if(slave.isSlave()) {
					SlaveDialogue.initDialogue((NPC) slave, false);
				} else {
					OccupantDialogue.initDialogue((NPC) slave, false, false);
				}
			}
		};
		
	}
	
	public static void dailyUpdate() {
		if(Main.game.getDialogueFlags().hasSavedLong(LilayaSpa.SPA_CONSTRUCTTION_TIMER_ID)) {
			Cell constructionCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
			if(constructionCell!=null) {
				long dayInstalled = Main.game.getDialogueFlags().getSavedLong(LilayaSpa.SPA_CONSTRUCTTION_TIMER_ID);
				if(Main.game.getDayNumber()-dayInstalled >=7) {
					constructionCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_SPA);
					Main.game.getDialogueFlags().removeSavedLong(LilayaSpa.SPA_CONSTRUCTTION_TIMER_ID);
				}
			}
		}
	}
	
	public static List<NPC> getSlavesAndOccupantsPresent() {
		List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
		charactersPresent.removeIf((character) -> character.isElemental());
		return charactersPresent;
	}
	
	private static boolean isPlayerHasDolls() {
		return Main.game.getPlayer().getSlavesOwnedAsCharacters().stream().anyMatch(slave-> slave != null && slave.isDoll());
	}
	
	public static String getLilayasHouseStandardResponseTabs(int i) {
		AbstractPlaceType playerPlaceType = Main.game.getPlayer().getLocationPlace().getPlaceType();
		switch(i) {
			case 0:
				return "行动";
			case 1:
				return "快速旅行";
			case 2:
				if(playerPlaceType==PlaceType.LILAYA_HOME_ROOM_PLAYER) {
					return "浴室";
				}
				if(isPlayerHasDolls()
						&& (playerPlaceType==PlaceType.LILAYA_HOME_ENTRANCE_HALL
							|| playerPlaceType==PlaceType.LILAYA_HOME_CORRIDOR
							|| playerPlaceType==PlaceType.LILAYA_HOME_GARDEN
							|| playerPlaceType==PlaceType.LILAYA_HOME_FOUNTAIN
							|| playerPlaceType==PlaceType.LILAYA_HOME_STAIR_DOWN
							|| playerPlaceType==PlaceType.LILAYA_HOME_STAIR_DOWN_SECONDARY
							|| playerPlaceType==PlaceType.LILAYA_HOME_STAIR_UP
							|| playerPlaceType==PlaceType.LILAYA_HOME_STAIR_UP_SECONDARY)) {
					return "玩偶安置";
				}
				break;
				
		}
		return null;
	}
	
	public static Response getLilayasHouseDollStationResponses(int index) {
		if(index==0) {
			index = 15;
		} else if(index<15) {
			index--;
		}
		List<GameCharacter> dolls = Main.game.getPlayer().getSlavesOwnedAsCharacters().stream().filter(slave->slave.isDoll()).collect(Collectors.toList());
		List<Response> responses = new ArrayList<>();
		for(int i=0; i<dolls.size(); i++) {
			GameCharacter doll = dolls.get(i);
			boolean alreadyActive = doll.getSlaveStationWorldType()==Main.game.getPlayer().getWorldLocation() && Main.game.getPlayer().getLocation().equals(doll.getSlaveStationLocation());
			responses.add(new Response(
					UtilText.parse(doll, "[npc.Name]"),
					UtilText.parse(doll, 
							"将这个地块设置为<span style='color:"+doll.getFemininity().getColour().toWebHexString()+"'>[npc.name]"+(doll.hasSurname()?" [npc.surname]":"")+"</span>扮演雕像时的安置地点。"
							+ "<br/>[style.italics("
							+ (doll.getSlaveStationWorldType()==null
								?"[npc.name]没有指定安置地点，[npc.sheHasFull]会随机选择一个走廊地块作为安置地点。"
								:(alreadyActive
										?"[style.colourExcellent(这个地块已被指定为[npc.namePos]的安置地点。)]"
										:"[style.colourMinorGood(这个地块不是[npc.namePos]的安置地点，[npc.she]已经被指定在其它位置了。)]"))
							+ ")]"),
					Main.game.getDefaultDialogue(false)) {
				@Override
				public Colour getHighlightColour() {
					if(doll.getSlaveStationWorldType()==null) {
						return super.getHighlightColour();
					} else if(!alreadyActive) {
						return PresetColour.GENERIC_MINOR_GOOD;
					} else {
						return PresetColour.GENERIC_EXCELLENT;
					}
				}
				@Override
				public void effects() {
					doll.setSlaveStationWorldType(Main.game.getPlayer().getWorldLocation());
					doll.setSlaveStationLocation(Main.game.getPlayer().getLocation());
				}
			});
		}
		if(responses.size()<=index) {
			return null;
		}
		return responses.get(index);
	}
	
	public static Response getLilayasHouseFastTravelResponses(int index) {
		 if (index == 1) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_PLAYER) {
				return new Response("你的房间", "你已经在你的房间里了，所以不需要快速旅行……", null);
			}
			return new Response("你的房间", "快速旅行到你的房间", PlaceType.LILAYA_HOME_ROOM_PLAYER.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
					Main.game.setResponseTab(0);
				}
			};

		} else if (index == 2) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_LAB) {
				return new Response("莉莱雅的实验室", "你已经在莉莱雅的实验室里了，所以不需要快速旅行……", null);
			}
			return new Response("莉莱雅的实验室", "快速旅行到莉莱雅的实验室", PlaceType.LILAYA_HOME_LAB.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					Main.game.setResponseTab(0);
				}
			};
			
		} else if (index == 3) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_KITCHEN) {
				return new Response("厨房", "你已经在厨房里了，所以不需要快速旅行……", null);
			}
			return new Response("厨房", "快速旅行到厨房", PlaceType.LILAYA_HOME_KITCHEN.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_KITCHEN, false);
					Main.game.setResponseTab(0);
				}
			};
			
		} else if (index == 4) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_LIBRARY) {
				return new Response("图书馆", "你已经在图书馆里了，所以不需要快速旅行……", null);
			}
			return new Response("图书馆", "快速旅行到图书馆", PlaceType.LILAYA_HOME_LIBRARY.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LIBRARY, false);
					Main.game.setResponseTab(0);
				}
			};
			
		} else if (index == 5) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_ENTRANCE_HALL) {
				return new Response("门厅", "你已经在门厅了，不需要快速旅行……", null);
			}
			return new Response("门厅", "快速旅行到门厅。",PlaceType.LILAYA_HOME_ENTRANCE_HALL.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
					Main.game.setResponseTab(0);
				}
			};
		}
		
		return null;
	}
	
	private static Response getRoomResponse(int responseTab, int index) {
		AbstractPlaceUpgrade coreUpgrade = null;
		for(AbstractPlaceUpgrade pu : Main.game.getPlayer().getLocationPlace().getPlaceUpgrades()) {
			if(pu.isCoreRoomUpgrade()) {
				coreUpgrade = pu;
			}
		}
		
		if(responseTab==1) {
			return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
		}
		
		List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
		List<NPC> slavesAssignedToRoom = new ArrayList<>();
		
		if(coreUpgrade==PlaceUpgrade.LILAYA_OFFICE
				|| coreUpgrade==PlaceUpgrade.LILAYA_SPA) {
			slavesAssignedToRoom.addAll(charactersPresent);
			
		} else {
			for(String slave : Util.mergeLists(Main.game.getPlayer().getFriendlyOccupants(), Main.game.getPlayer().getSlavesOwned())) {
				try {
					NPC slaveNPC = (NPC)Main.game.getNPCById(slave);
					if(slaveNPC != null && (slaveNPC.getHomeWorldLocation()==Main.game.getPlayer().getWorldLocation() && slaveNPC.getHomeLocation().equals(Main.game.getPlayer().getLocation()))) {
						slavesAssignedToRoom.add(slaveNPC);
					}
				} catch (Exception e) {
					Util.logGetNpcByIdError("getRoomResponse()", slave);
					e.printStackTrace();
				}
			}
		}
//		charactersPresent.removeIf((characterPresent) -> Main.game.getPlayer().hasCompanion(characterPresent) && !slavesAssignedToRoom.contains(characterPresent));
		
		if(index==0) {
			return null;
			
		} else if (index == 1) {
			if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
				return new Response("管理房间", "进入该房间的管理界面。", OccupantManagementDialogue.ROOM_UPGRADES) {
					@Override
					public void effects() {
						OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
					}
				};
			} else {
				return new Response("管理房间", "在进入该界面前，你需要获得贩奴许可；莉莱雅要是允许你带人或玩偶住下的话那也行。",  null);
			}
			
		}  else if (index == 2) {
			if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
				return new Response("人员管理", "进入奴隶和友人住客的管理界面。", CORRIDOR) {
					@Override
					public DialogueNode getNextDialogue() {
						return OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null);
					}
					@Override
					public void effects() {
						CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
					}
				};
			} else {
				return new Response("管理人员", "在进入该界面前，你需要获得贩奴许可；莉莱雅要是允许你带人或玩偶住下的话那也行。",  null);
			}
		}
		
		int indexPresentStart = 3;
		
		if(coreUpgrade==PlaceUpgrade.LILAYA_OFFICE) {
			indexPresentStart = 4;
			if(index==3) {
				return new Response("住户名单", "打开住户名单，从中可以管理所有的房间、奴隶和友好居民。", CORRIDOR) {
					@Override
					public DialogueNode getNextDialogue() {
						return OccupantManagementDialogue.getSlaveryOverviewDialogue(null);
					}
					@Override
					public void effects() {
						CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
					}
				};
			}
		}
		
		if(coreUpgrade==PlaceUpgrade.LILAYA_SPA) {
			indexPresentStart = 4;
			if(index==3) {
				return LilayaSpa.SPA_RECEPTION.getResponse(responseTab, index);
			}
		}
		
		if(index-indexPresentStart<slavesAssignedToRoom.size()) {
			NPC character = slavesAssignedToRoom.get(index-indexPresentStart);
			if(charactersPresent.contains(character) || (character.getHomeCell().equals(Main.game.getPlayerCell()) && Main.game.getPlayer().getCompanions().contains(character))) {
				return interactWithNPC(character);
			} else {
				return new Response(UtilText.parse(character, "[npc.Name]"), 
						UtilText.parse(character, "虽然这里是[npc.namePos]的房间，但[npc.sheIs]"
								+(character.getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_LOUNGE)
										?"现在正在奴隶休息室休息。"
										:"现在正在外面工作。")), null);
			}
		}
		
		return null;
	}
	
	public static String getBaseRoomDescription() {
		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR) {
			return "<p>"
						+ "这个房间的一面墙上有一系列的大窗户，当门打开时，大量的自然光就会从窗户射入走廊。"
						+ "透过这些窗户，可以看到御城区繁忙街道上熙熙攘攘的景象。"
					+ "</p>";
			
		} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR) {
			return "<p>"
						+ "这个房间的一面墙上有一系列的大窗户，当门打开时，大量的自然光就会从窗户射入走廊。"
						+ "透过这些窗户，可以看到御城区繁忙街道上熙熙攘攘的景象。"
					+ "</p>";
			
		} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR) {
			return "<p>"
						+ "这个房间有一对法式门，将它与毗邻的庭院花园相连。"
						+ "通过这些门和它们旁边的窗户，可以看到花园的很大一部分。"
					+ "</p>";
			
		} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR) {
			return "<p>"
						+ "这个房间的一面墙上有一系列的大窗户，当门打开时，大量的自然光就会从窗户射入走廊。"
						+ "透过这些窗户可以俯视庭院花园。"
					+ "</p>";
		}
		return "";
	}
	
	public static String getRoomModificationsDescription(boolean includeDefaultRoomDescription) {
		GenericPlace place = Main.game.getPlayer().getLocationPlace();
		
		for(AbstractPlaceUpgrade pu : place.getPlaceUpgrades()) {
			DialogueNode dn = pu.getRoomDialogue(Main.game.getPlayerCell());
			if(dn!=null) {
				return dn.getContent();
			}
		}
		
		StringBuilder roomSB = new StringBuilder();
		
		if(includeDefaultRoomDescription) {
			roomSB.append(getBaseRoomDescription());
		}
		
		for(AbstractPlaceUpgrade pu : PlaceUpgrade.getAllPlaceUpgrades()) { // For consistent ordering.
			if(place.getPlaceUpgrades().contains(pu)) {
				roomSB.append(formatRoomUpgrade(pu));
			}
		}
		
		return roomSB.toString();
	}
	
	public static String getRoomCharactersPresentDescription() {
		List<NPC> charactersHome = Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell());
		StringBuilder sb = new StringBuilder();
		
		if(!charactersHome.isEmpty()) {
			sb.append("<p>");
			boolean first = true;
			for(NPC npc : charactersHome) {
				if(!first) {
					sb.append("<br/>");
				}
				sb.append("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>");
				sb.append(UtilText.parse(npc, "[npc.Name]"));
				sb.append("</span>");
				sb.append("");
				
				if(Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())) { // Friendly occupant:
					if(!Main.game.getCharactersPresent().contains(npc)) {
						sb.append(UtilText.parse(npc,
									"现在[style.colourMinorBad(不在这)]，你简单扫视房间，寻找任何可能和[npc.herHim]有关的线索，你看到一张小纸条被落在[npc.her]的床头柜上。"
								+"走过去捡起它，你读道："
								+"</p>"+
								"<p style='text-align:center;'><i>"
								+"嗨，[pc.name]！<br/>"));
						if(npc.hasJob()) {
							sb.append("我现在在外面工作，我的工作时间是从"+npc.getHistory().getWorkHourStart()+":00到"+npc.getHistory().getWorkHourEnd()+":00，"
									+npc.getHistory().getStartDay().getDisplayName(TextStyle.FULL, Locale.CHINESE)+"-"+npc.getHistory().getEndDay().getDisplayName(TextStyle.FULL, Locale.CHINESE)+"<br/>");
						} else {
							sb.append(UtilText.parse(npc, "我现在正在府上帮忙<br/>"));
						}
						sb.append(UtilText.parse(npc,
								"- [npc.Name]"
								+"</i>"
							+ "</p>"
							+ "<p>"));
						sb.append(UtilText.parse(npc, "<i>[npc.Name]在[style.time("+npc.getSleepStartHour()+")]-[style.time("+npc.getSleepEndHour()+")]之间睡觉。</i>"));
						
					} else {
						sb.append(UtilText.parse(npc, "现在[style.colourMinorGood(在这里)]，"));
						if(npc.isAsleep()) {
							sb.append(UtilText.parse(npc, "但[npc.sheIs]目前[style.colourSleep(在睡觉)]……"));
						} else {
							sb.append(UtilText.parse(npc, " 如果你愿意，你可以和[npc.herHim]互动……"));
						}
						sb.append("<br/>");
						sb.append(UtilText.parse(npc, "<i>[npc.Name]在[style.time("+npc.getSleepStartHour()+")]-[style.time("+npc.getSleepEndHour()+")]之间睡觉。</i>"));
					}
					
				} else { // Slave:
					if(!Main.game.getCharactersPresent().contains(npc)) {
						sb.append(UtilText.parse(npc, "现在[style.colourMinorBad(不在这里)]。"));
						if(npc.isAtWork()) {
							String jobName = npc.getSlaveJob(Main.game.getHourOfDay()).getName(npc);
							sb.append(UtilText.parse(npc, "[npc.sheIs]此时的工作是"+jobName+"。"));
						} else {
							boolean houseFree = npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_HOUSE_FREEDOM);
							boolean outsideFree = npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_OUTSIDE_FREEDOM);
							if(houseFree) {
								if(outsideFree) {
									sb.append("所以一定在府上或者外面御城区的某个地方……");
								} else {
									sb.append("所以一定在府上的某个地方……");
								}
							} else if(outsideFree) {
								sb.append("所以一定在外面御城区的某个地方……");
							} else {
								sb.append("但很快会回到这里……");
							}
						}
						
					} else  {
						sb.append(UtilText.parse(npc, "现在[style.colourMinorGood(在这里)]，"));
						if(npc.isAsleep()) {
							sb.append(UtilText.parse(npc, "但[npc.sheIs]目前[style.colourSleep(在睡觉)]……"));
						} else {
							sb.append(UtilText.parse(npc, " 如果你愿意，你可以和[npc.herHim]互动……"));
						}
					}
				}
				first = false;
			}
			sb.append("</p>");
		}
		return sb.toString();
	}

	public static String getSlavePresentDescription(GameCharacter slave) {
		return getSlavePresentDescription(slave, "", "", "", "", "");
	}
	/**
	 * Descriptions should fit into:<br/>
	 * <i>'She '</i> + <code>desc</code><br/>
	 * and<br/>
	 * <i>'As you've instructed her to crawl, she is down on all fours, and '</i> + <code>desc</code>
	 */
	public static String getSlavePresentDescription(GameCharacter slave, String minimumObedienceText, String lowObedienceText, String neutralObedienceText, String highObedienceText, String maximumObedienceText) {
		StringBuilder sb = new StringBuilder();
		sb.append("<p>");
		
		if(slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.DOLL_STATUE) {
			sb.append(UtilText.parse(slave, "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.name]</b>被命令假扮成一座雕像，目前正在这片区域。"));
			
			for(SlaveJobSetting sjs : slave.getSlaveJobSettings(SlaveJob.DOLL_STATUE)) {
				switch(sjs) {
					case DOLL_STATUE_ALL_FOURS:
						sb.append(UtilText.parse(slave, "[npc.SheIsFull]四肢跪地"));
						break;
					case DOLL_STATUE_ARTISTIC:
						sb.append(UtilText.parse(slave, "[npc.SheIsFull]摆出艺术性姿势"));
						break;
					case DOLL_STATUE_ATTENTION:
						sb.append(UtilText.parse(slave, "[npc.SheIsFull]立正站定"));
						break;
					case DOLL_STATUE_BRIDGE:
						sb.append(UtilText.parse(slave, "[npc.nameHasFull]向后下腰，做出桥式体操的姿势"));
						break;
					case DOLL_STATUE_MISSIONARY:
						sb.append(UtilText.parse(slave, "[npc.SheIsFull]张开[npc.legs]向后躺下"));
						break;
					case DOLL_STATUE_SQUATTING:
						sb.append(UtilText.parse(slave, "[npc.SheIsFull]双膝分开并蹲下，[npc.hands]放在头后面"));
						break;
					case DOLL_STATUE_STANDING_SPLIT:
						sb.append(UtilText.parse(slave, "[npc.SheIsFull]单腿站立，另一条腿竖直举起"));
						break;
					default:
						break;
				}
			}
			sb.append(UtilText.parse(slave, "，保持着完全的静止。"));
			
		} else {
			sb.append(UtilText.parse(slave, "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.name]</b>被安排做"+(slave.getSlaveJob(Main.game.getHourOfDay()).getName(slave))
					+"，目前正在这片区域。"));
			
			if(slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)) {
				sb.append(UtilText.parse(slave,
						"由于你命令[npc.herHim]爬行，[npc.sheIs]趴在地上，然后"));
			} else {
				sb.append(UtilText.parse(slave,
						"[npc.She]"));
			}
			switch(slave.getObedience()) {
				case NEGATIVE_FIVE_REBELLIOUS: case NEGATIVE_FOUR_DEFIANT: case NEGATIVE_THREE_STRONG_INSUBORDINATE:
					sb.append(UtilText.parse(slave, minimumObedienceText));
					break;
				case NEGATIVE_ONE_DISOBEDIENT:  case NEGATIVE_TWO_UNRULY:
					sb.append(UtilText.parse(slave, lowObedienceText));
					break;
				case ZERO_FREE_WILLED:
					sb.append(UtilText.parse(slave, neutralObedienceText));
					break;
				case POSITIVE_ONE_AGREEABLE: case POSITIVE_TWO_OBEDIENT:
					sb.append(UtilText.parse(slave, highObedienceText));
					break;
				case POSITIVE_THREE_DISCIPLINED: case POSITIVE_FOUR_DUTIFUL: case POSITIVE_FIVE_SUBSERVIENT:
					sb.append(UtilText.parse(slave, maximumObedienceText));
					break;
			}
			sb.append("</p>");
		}
		
		return sb.toString();
	}

	private static String formatRoomUpgrade(AbstractPlaceUpgrade upgrade) {
		return "<p>"
				+ "<b style='color:"+upgrade.getColour().toWebHexString()+";'>"+upgrade.getName()+"</b><br/>"
				+ upgrade.getRoomDescription(Main.game.getPlayerCell())
			+ "</p>";
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getLabel() {
			return "莉莱雅的家 - 街道";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			NPC characterAnsweringDoor;
			Optional<NPC> guardAtEntrance =
					Main.game.getCharactersPresent(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL).stream().filter(
							npc->npc.isSlave() && npc.isAtWork() && npc.hasSlaveJobSetting(SlaveJob.SECURITY, SlaveJobSetting.SECURITY_ANSWER_DOOR)).findFirst();
			if(guardAtEntrance.isPresent()) {
				characterAnsweringDoor = guardAtEntrance.get();
			} else {
				characterAnsweringDoor = Main.game.getNpc(Rose.class);
			}
			
			if (index == 1) {
				LocalDateTime time = Main.game.getDateNow();
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.daddyFound)
						&& !Main.game.getPlayer().getFetishDesire(Fetish.FETISH_INCEST).isNegative()
						&& Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN) // Only trigger after having met Lyssieth
						&& Main.game.isExtendedWorkTime()
						&& time.getMonth().equals(Month.JUNE) && time.getDayOfMonth()>=14 && time.getDayOfMonth()<=21) { // Father's day timing, 3rd week of June
					return new Response("进入", UtilText.parse(characterAnsweringDoor, "敲门并等[npc.name]让你进去。"), DaddyDialogue.FIRST_ENCOUNTER) {
						@Override
						public void effects() {
							Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.daddyFound, true);
						}
					};
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.daddySendingReward)) {
					return new Response("进入", UtilText.parse(characterAnsweringDoor, "敲门并等待萝丝让你进去。"), DADDY_PACKAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.daddySendingReward, false);
							
							if(characterAnsweringDoor.equals(Main.game.getNpc(Rose.class))) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE_DADDY_PACKAGE"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE_DADDY_PACKAGE_SECURITY", characterAnsweringDoor));
							}
							
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);

							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.TELEKENETIC_SHOWER)), false, true));
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.getSpellScrollType(SpellSchool.EARTH)), 5, false, true));
						}
					};
					
				} else {
					return new Response("进入", UtilText.parse(characterAnsweringDoor, "敲门并等待萝丝让你进去。"), PlaceType.LILAYA_HOME_ENTRANCE_HALL.getDialogue(false)){
						@Override
						public void effects() {
							if(characterAnsweringDoor.equals(Main.game.getNpc(Rose.class))) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE_KNOCK_ON_DOOR"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE_KNOCK_ON_DOOR_SECURITY", characterAnsweringDoor));
							}
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	public static final DialogueNode DADDY_PACKAGE = new DialogueNode("门厅", "", true) {

		@Override
		public int getSecondsPassed() {
			return 1*60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续探索莉莱雅的家。", ENTRANCE_HALL);
			}
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("走廊", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "CORRIDOR"));
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append("<p>"
							+ "这条走廊现在很冷清，似乎没什么可做的。"
						+ "</p>");
			} else {
				for(NPC slave : charactersPresent) {
					SlaveJob job = slave.getSlaveJob(Main.game.getHourOfDay());
					if(job==SlaveJob.CLEANING) {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave,
								"甚至懒得假装[npc.sheIs]在打扫。",
								"正在心猿意马地清扫地毯。",
								"正在擦拭踢脚线。",
								"正忙着抛光地板。",
								"正忠实地擦拭、抛光和清洁这个区域中的一切"));
						
					} else if(job==SlaveJob.SECURITY) {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave,
								"甚至懒得假装[npc.sheIs]在看守。",
								"正心猿意马地看守着。",
								"正机警地关注着所有风吹草动。",
								"正高度紧张且机警地关注着所有风吹草动。",
								"正高度紧张且认真负责地关注着所有风吹草动。"));
					} else {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			if(index==0) {
				return null;
				
			} else if(index-1<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-1);
				return interactWithNPC(slave);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROOM_WINDOW = new DialogueNode("房间", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return getRoomModificationsDescription(true)
					+ getRoomCharactersPresentDescription();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_GARDEN_GROUND_FLOOR = new DialogueNode("园景房", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return getRoomModificationsDescription(true)
					+ getRoomCharactersPresentDescription();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_GARDEN = new DialogueNode("园景房", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return getRoomModificationsDescription(true)
					+ getRoomCharactersPresentDescription();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DUNGEON_CELL = new DialogueNode("地牢牢房", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return getRoomModificationsDescription(true);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode BIRTHING_ROOM = new DialogueNode("产房", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "莉莱雅改造了这个房间，如今它成了合适的产房。"
					+ "</p>"
					+ "<p>"
						+ "与这座房子的大多数房间不同，这个房间的地面铺设着干净洁白的瓷砖，却没铺地毯。"
						+ "一张看起来出奇现代化的产床靠在墙边，除此之外，几乎没有其他医疗设备。"
						+ "房间的角落散落着几把舒适的椅子，但除了椅子们和另一个角落的饮料柜之外，房间没有其他家具。"
					+ "</p>"
					+ "<p>"
						+ "换做别人家，发现一个专门的产房可能会有点让人震惊，但莉莱雅似乎参与了各种奇怪的事情，"
							+ "所以你只是把它当作异世界的又一个怪现象。"
					+ "</p>";
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
			return null;
		}
	};
	
	public static final DialogueNode KITCHEN = new DialogueNode("厨房", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			UtilText.nodeContentSB.append("<p>"
						+ "与莉莱雅家的其他房间情况相同，这间厨房比你以前踏足过的任何厨房都大得多。"
						+ "房间的边缘摆放着一排木制橱柜，橱柜顶部铺有抛光的花岗岩，一对长长的独立工作台设置在宽敞的房间中央。"
						+ "放置的三台铸铁烤箱、铺就的原木地板和缺乏现代化设备的内饰，赋予了厨房一种相当复古的观感。"
					+ "</p>"
					+ "<p>"
						+ "房间的一侧有个敞开的门，透过门的缝隙，你看到一排冰箱、冷冻设备和食品储藏柜。"
						+ "品类各异、品种繁多的配料和食材摆在开放式货架上，你不禁对储藏数量和品种多样性感到惊讶。"
					+ "</p>");
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append("<p>"
							+ "厨房现在很冷清，似乎没什么可做的。"
						+ "</p>");
			} else {
				for(NPC slave : charactersPresent) {
					if(slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.KITCHEN) {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave,
								"很明显没在烹饪任何东西。更糟糕的是，[npc.she]并不在乎你发现[npc.herHim]偷懒，[npc.her]转过身去。",
								"正在厨房角落心猿意马地准备食物。",
								"正在厨房的其中一台烤箱旁忙着烹饪食物。",
								"目前正在准备食物。你能发现[npc.sheIs]非常努力地保证[npc.sheIs]干得很好。",
								"正在忠实地为莉莱雅做饭，你注意到[npc.sheIs]很用心地准备饭菜，来讨你恶魔[lilaya.relation(pc)]的欢心。"));
					} else {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
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
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			if(index==0) {
				return null;
				
			} else if(index-1<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-1);
				return interactWithNPC(slave);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROOM_ROSE = new DialogueNode("萝丝的房间", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			if(!Main.game.isExtendedWorkTime()
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.dressingRoomLyssiethsWardrobeActivated)
					&& (!Main.game.getDialogueFlags().hasSavedLong("innoxia_lilaya_kitty_time_seen")
							|| (Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("innoxia_lilaya_kitty_time_seen") > 60*60*24*7))) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/room_rose", "ROOM_ROSE_KITTY");
			}
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/room_rose", "ROOM_ROSE");
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
					return new Response("呼叫萝丝", "挂在萝丝门旁的牌子写明了不要打扰她，如果你想和她谈什么事情，最好白天再来。", null);
				}
				
				return new Response("呼叫萝丝", "莉莱雅的女仆萝丝总是近在咫尺。如果你按响她卧室门旁的小铃，她肯定会迅速赶来。", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/room_rose", "ROOM_ROSE_INITIAL_CALL");
						
						Main.game.getDialogueFlags().values.remove(DialogueFlagValue.auntHomeJustEntered);
						Main.game.getNpc(Rose.class).setLocation(Main.game.getActiveWorld().getWorldType(), Main.game.getPlayer().getLocation(), false);
					}
				};
			}
			
			if(index==2
					&& !Main.game.isExtendedWorkTime()
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.dressingRoomLyssiethsWardrobeActivated)
					&& (!Main.game.getDialogueFlags().hasSavedLong("innoxia_lilaya_kitty_time_seen")
							|| (Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("innoxia_lilaya_kitty_time_seen") > 60*60*24*7))) {
				return new Response("锁眼",
						"透过锁眼看看能否找到那声巨大猫叫的来源。",
						DialogueManager.getDialogueFromId("innoxia_places_dominion_lilayas_home_room_rose_lilaya_kitty")) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setSavedLong("innoxia_lilaya_kitty_time_seen", Main.game.getSecondsPassed());
					}
				};
			}
			
			return null;
		}
	};
	
	
	private static String roseContent = "";
	private static boolean giftedRose = false;
	public static final DialogueNode AUNT_HOME_ROSE = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return roseContent;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("莉莱雅", "向萝丝打听她的主人莉莱雅。", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_LILAYA");
					}
				};

			} else if (index == 2) {
				return new Response("奴役", "问问萝丝她怎么变成奴隶的。", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_SLAVE");
					}
				};

			} else if (index == 3) {
				return new Response("世界", "让萝丝给你讲些关于这个世界的事情。", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_WORLD");
					}
				};

			} else if (index == 4) {
				return new Response("职责", "问问萝丝她的职责是什么。", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_DUTIES");
					}
				};

			} else if (index == 5) {
				if(Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_hair_rose"), false) && !giftedRose) {
					return new Response("送上玫瑰", "送给萝丝一朵物品栏中的玫瑰。", AUNT_HOME_ROSE) {
						@Override
						public void effects() {
							roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_OFFER_ROSE");
							Main.game.getPlayer().removeClothingByType(ClothingType.getClothingTypeFromId("innoxia_hair_rose"));
							giftedRose = true;
						}
					};
					
				} else if(giftedRose) {
					return new Response("萝丝的手", "你之前一直没注意到过，萝丝的手竟然如此美妙……", ROSE_HANDS) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							giftedRose = false;
						}
					};
					
				} else {
					return new Response("送上玫瑰", "你没有玫瑰可以送给萝丝。", null);
				}
				
			} else if (index == 6
					&& (Main.game.getNpc(Lilaya.class).isPregnant() && Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getPlayer()))) {

				if(Main.game.getPlayer().hasItemType(ItemType.MOTHERS_MILK)) {
					return new Response("母亲的乳汁", "从物品中给萝丝一瓶母亲的乳汁，并请她把它给莉莱雅，以缩短她的孕期。", AUNT_HOME_ROSE) {
						@Override
						public void effects() {
							giftedRose = false;
							roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_MOTHERS_MILK");
							Main.game.getPlayer().removeItemByType(ItemType.MOTHERS_MILK);
							Main.game.getNpc(Lilaya.class).useItem(Main.game.getItemGen().generateItem(ItemType.MOTHERS_MILK), Main.game.getNpc(Lilaya.class), false);
						}
					};
					
				} else {
					return new Response("母亲的乳汁", "如果你的物品中有 “母亲的乳汁”，你可以要求萝丝把它给莉莱雅，以缩短她的孕期。", null);
				}
				
			} else if (index == 0) {
				return new Response("取消", "让萝丝继续工作。", ROOM_ROSE) {
					@Override
					public void effects() {
						giftedRose = false;
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					}
					
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode ROSE_HANDS = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "萝丝的手";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_HANDS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("牵手", "警告：该内容包括牵手、吮吸手指、舔舐手掌等细节极端的描述。"
						+ "<b>请记住，开始游戏之前必须阅读游戏声明！</b><b style='color:"+BaseColour.CRIMSON.toWebHexString()+";'>限制18岁以上游玩！</b>",
						true, false,
						new SMRoseHands(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.HAND_SEX_DOM_ROSE)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Rose.class), SexSlotUnique.HAND_SEX_SUB_ROSE))),
						null,
						null,
						END_HAND_SEX);

			} else {
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return true;
		}
	};
	
	public static final DialogueNode END_HAND_SEX = new DialogueNode("恢复", "你和萝丝都因手拉手疲惫不堪。", true) {
		@Override
		public String getContent() {
			return "<p>"
						+ "萝丝蹒跚地走过去，拿起她的小羽毛扫帚。她朝你投来妩媚的眼神，咬了咬嘴唇，然后匆匆离开前往房子的另一边。毫无疑问，她想从你们极其亲密的手拉手时刻中恢复过来。"
					+ "</p>"
					+ "<p>"
						+ "你发出一声精疲力竭的叹息，瘫倒在房间的床上。你回想着自己刚刚的奇妙经历。"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "你终于从与萝丝的手拉手状态中恢复过来。", RoomPlayer.ROOM){
					@Override
					public void effects() {
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					}
					
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode GARDEN = new DialogueNode("园景房", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "GARDEN"));
			
			if(!charactersPresent.isEmpty()) {
				for(NPC slave : charactersPresent) {
					if(slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.GARDEN) {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave,
								"很明显地没在干园艺工作。更糟糕的是，[npc.she]并不在乎你发现[npc.herHim]在偷懒，[npc.her]转过身去。",
								"正心猿意马地修剪树篱。",
								"正在忙着沿着一条蜿蜒穿过花园的小路在花园中除草。",
								"正在忙着修整玫瑰丛。你可以看到[npc.sheIs]非常努力地确保[npc.sheIs]剪掉每一朵凋零的玫瑰。",
								"忠实地种植鳞茎并拔掉杂草。你注意到[npc.sheIs]特别小心地将每一颗鳞茎都种在了恰当的位置。"));
					} else {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			if(index==0) {
				return null;
				
			} else if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaGardenPickRose)) {
					return new Response("采摘玫瑰", "你今天已经摘了一朵玫瑰，你担心摘太多会惹恼莉莱雅……", null);
				} else {
					return new Response("采摘玫瑰", "从繁茂的玫瑰丛中摘一朵花。", GARDEN) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaGardenPickRose, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "GARDEN_PICK ROSE"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_hair_rose", false), false));
						}
						public boolean isStripContent() {
							return true;
						}
					};
				}
				
			} else if(index-2<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-2);
				return new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "和[npc.name]互动。"), SlaveDialogue.SLAVE_START) {
					@Override
					public Colour getHighlightColour() {
						return slave.getFemininity().getColour();
					}
					@Override
					public void effects() {
						SlaveDialogue.initDialogue((NPC) slave, false);
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode FOUNTAIN = new DialogueNode("喷水池", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "FOUNTAIN"));
			
			if(!charactersPresent.isEmpty()) {
				for(NPC slave : charactersPresent) {
					if(slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.GARDEN) {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave,
								"很明显地没在干园艺工作。更糟糕的是，[npc.she]并不在乎你发现[npc.herHim]在偷懒，[npc.her]转过身去。",
								"正心猿意马地修剪树篱。",
								"正在忙着沿着一条蜿蜒穿过花园的小路在花园中除草。",
								"正在忙着修整玫瑰丛。你可以看到[npc.sheIs]非常努力地确保[npc.sheIs]剪掉每一朵凋零的玫瑰。",
								"忠实地种植鳞茎并拔掉杂草。你注意到[npc.sheIs]特别小心地将每一颗鳞茎都种在了恰当的位置。"));
					} else {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			if(index==0) {
				return null;
			}

			List<Response> responses = new ArrayList<>();
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("acexp_dungeon_garden_access_found"))) {
				responses.add(new Response("莉莱雅的地牢",
						"按下隐藏按钮开启通往莉莱雅地牢的秘密通道。",
						DialogueManager.getDialogueFromId("acexp_dominion_lilaya_dungeon_stairsUp_garden")) {
					@Override
					public void effects() {
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "DUNGEON_OPENS_FOUNTAIN"));
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("acexp/dominion/lilaya_dungeon", "DUNGEON_ENTRY"));
						Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("acexp_dungeon"), PlaceType.getPlaceTypeFromId("acexp_dungeon_stairs_garden"), false);
					}
				});
				
			}
			for(int i=0; i<charactersPresent.size(); i++) {
				GameCharacter slave = charactersPresent.get(i);
				responses.add(new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "和[npc.name]互动。"), SlaveDialogue.SLAVE_START) {
					@Override
					public Colour getHighlightColour() {
						return slave.getFemininity().getColour();
					}
					@Override
					public void effects() {
						SlaveDialogue.initDialogue((NPC) slave, false);
					}
				});
			}
			if(responses.size()>index-1) {
				return responses.get(index-1);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE_HALL = new DialogueNode("门厅", "。", false) {
		private boolean fiammettaMessage = false;
		@Override
		public void applyPreParsingEffects() {
			fiammettaMessage = Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_6
					&& Main.game.isDayTime()
					&& Main.game.getDialogueFlags().hasSavedLong("fia_factory_finished")
					&& (Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("fia_factory_finished") > (2 * 24* 60 * 60)); // 2 days

			if(fiammettaMessage) {
				Main.game.getNpc(Rose.class).setLocation(Main.game.getPlayer());
			}
		}
		@Override
		public int getSecondsPassed() {
			if(fiammettaMessage) {
				return 60;
			}
			return 10;
		}
		@Override
		public boolean isTravelDisabled() {
			return fiammettaMessage;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			if(fiammettaMessage) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/doll_quest", "ENTRANCE_HALL_FIAMMETTA"));

				return sb.toString();
			}
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ENTRANCE_HALL"));
			
			if(!charactersPresent.isEmpty()) {
				for(NPC slave : charactersPresent) {
					if(slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.SECURITY) {
						sb.append(getSlavePresentDescription(slave,
								"甚至懒得假装[npc.sheIs]在看守。",
								"正心猿意马地看守着。",
								"正机警地关注着所有风吹草动。",
								"正高度紧张且机警地关注着所有风吹草动。",
								"正高度紧张且认真负责地关注着所有风吹草动。"));
					} else {
						sb.append(getSlavePresentDescription(slave));
					}
				}
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(fiammettaMessage) {
				return null;
			}
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(fiammettaMessage) {
				if(index==1) {
					return new Response("跟随萝丝", "跟着萝丝去图书馆看看客人是谁。", DialogueManager.getDialogueFromId("innoxia_places_dominion_lilayas_home_doll_quest_start"));
				}
				return null;
			}
			
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}

			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				return new Response("离开", "离开莉莱雅的家", PlaceType.DOMINION_AUNTS_HOME.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME, false);
					}
				};

			} else if(index-2<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-2);
				return new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "和[npc.name]互动。"), SlaveDialogue.SLAVE_START) {
					@Override
					public Colour getHighlightColour() {
						return slave.getFemininity().getColour();
					}
					@Override
					public void effects() {
						SlaveDialogue.initDialogue((NPC) slave, false);
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode STAIRCASE_UP = new DialogueNode("上楼", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "STAIRCASE_UP"));
			for(NPC slave : getSlavesAndOccupantsPresent()) {
				sb.append(getSlavePresentDescription(slave));
			}
			return sb.toString();
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				return new Response("上楼", "上到二楼", PlaceType.LILAYA_HOME_STAIR_DOWN.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_STAIR_DOWN, false);
					}
				};
				
			} else if(index-2<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-2);
				return interactWithNPC(slave);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode STAIRCASE_UP_SECONDARY = new DialogueNode("上楼", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "CORRIDOR"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "STAIRCASE_UP_SECONDARY"));
			for(NPC slave : getSlavesAndOccupantsPresent()) {
				sb.append(getSlavePresentDescription(slave));
			}
			return sb.toString();
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				return new Response("上楼", "上到二楼", STAIRCASE_DOWN_SECONDARY){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, Main.game.getPlayer().getLocation(), false);
					}
				};
			} else if(index-2<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-2);
				return interactWithNPC(slave);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode STAIRCASE_DOWN = new DialogueNode("下楼", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "STAIRCASE_DOWN"));
			for(NPC slave : getSlavesAndOccupantsPresent()) {
				sb.append(getSlavePresentDescription(slave));
			}
			return sb.toString();
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				return new Response("下楼", "回到一楼",PlaceType.LILAYA_HOME_STAIR_UP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_STAIR_UP, false);
					}
				};
			} else if(index-2<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-2);
				return interactWithNPC(slave);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode STAIRCASE_DOWN_SECONDARY = new DialogueNode("下楼", "。", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "CORRIDOR"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "STAIRCASE_DOWN_SECONDARY"));
			for(NPC slave : getSlavesAndOccupantsPresent()) {
				sb.append(getSlavePresentDescription(slave));
			}
			return sb.toString();
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				return new Response("下楼", "回到一楼", STAIRCASE_UP_SECONDARY){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, Main.game.getPlayer().getLocation(), false);
					}
				};
			} else if(index-2<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-2);
				return interactWithNPC(slave);
			}
			
			return null;
		}
	};
}
