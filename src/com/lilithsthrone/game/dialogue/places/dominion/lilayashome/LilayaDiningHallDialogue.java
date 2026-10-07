package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.4.7.1
 * @version 0.4.7.1
 * @author Innoxia
 */
public class LilayaDiningHallDialogue {

	private static GameCharacter waiter;
	
	private static SexManagerDefault getDessertSexManager(AbstractSexPosition position,
			Map<GameCharacter, SexSlot> domSlots,
			Map<GameCharacter, SexSlot> subSlots,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(true,
				position,
				domSlots,
				subSlots) {
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return exposeAtStartOfSexMap;
			}
			@Override
			public String getDeskName() {
				return "餐桌";
			}
		};
	}
	
	public static final DialogueNode ROOM_DINING_HALL = new DialogueNode("", "", false) {
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
			StringBuilder sb = new StringBuilder();
			
			sb.append(LilayaHomeGeneric.getBaseRoomDescription());
			
			sb.append("<p>"
						+ "<b style='color:"+PlaceUpgrade.LILAYA_DINING_HALL.getColour().toWebHexString()+";'>"+PlaceUpgrade.LILAYA_DINING_HALL.getName()+"</b><br/>"
						+ PlaceUpgrade.LILAYA_DINING_HALL.getRoomDescription(Main.game.getPlayerCell())
					+ "</p>");
			
			for(AbstractPlaceUpgrade up : Main.game.getPlayerCell().getPlace().getPlaceUpgrades()) {
				if(!up.isCoreRoomUpgrade()) {
					sb.append("<p>"
								+ "<b style='color:"+up.getColour().toWebHexString()+";'>"+up.getName()+"</b><br/>"
								+ up.getRoomDescription(Main.game.getPlayerCell())
							+ "</p>");
				}
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
			
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
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
				
			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("人员管理", "进入奴隶和友人住客的管理界面。", OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("管理人员", "在进入该界面前，你需要获得贩奴许可；莉莱雅要是允许你带人或玩偶住下的话那也行。",  null);
				}
				
			} else if (index == 3) {
				if(Main.game.getOccupancyUtil().getCharactersCurrentlyAtJob(SlaveJob.KITCHEN).isEmpty()) {
					return new Response("用餐", "你至少需要有一名奴隶担任厨师才能点餐……",  null);
					
				} else if(LilayaHomeGeneric.getSlavesAndOccupantsPresent().isEmpty()) {
					return new Response("用餐", "你至少需要有一名奴隶担任餐厅服务员才能点餐……",  null);
					
				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("用餐", "你需要能够使用嘴巴才能点餐……",  null);
					
				} else {
					return new Response("点餐", "坐在餐桌旁点餐。", ROOM_DINING_HALL_ORDER_MEAL);
				}
				
			}
			
			int indexPresentStart = 4;
			if(index-indexPresentStart<slavesAssignedToRoom.size()) {
				NPC character = slavesAssignedToRoom.get(index-indexPresentStart);
				if(charactersPresent.contains(character) || (character.getHomeCell().equals(Main.game.getPlayerCell()) && Main.game.getPlayer().getCompanions().contains(character))) {
					return LilayaHomeGeneric.interactWithNPC(character);
					
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
	};
	
	public static final DialogueNode ROOM_DINING_HALL_ORDER_MEAL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_ORDER_MEAL", LilayaHomeGeneric.getSlavesAndOccupantsPresent().get(0));
		}
		@Override
		public Response getResponse(int responseTab, int index) {

			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
			if (index == 0) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("不介意", "改变主意，决定最终不吃饭。", ROOM_DINING_HALL);
				}
				
			}
			int indexPresentStart = 1;
			if(index-indexPresentStart<slavesAssignedToRoom.size()) {
				NPC character = slavesAssignedToRoom.get(index-indexPresentStart);
				if(charactersPresent.contains(character) || (character.getHomeCell().equals(Main.game.getPlayerCell()) && Main.game.getPlayer().getCompanions().contains(character))) {
					return new Response(
							UtilText.parse(character, "[npc.Name]"),
							UtilText.parse(character, "叫来[npc.name]，让[npc.herHim]来为你服务，然后点餐。"),
							ROOM_DINING_HALL_RECEIVE_MEAL) {
						@Override
						public Colour getHighlightColour() {
							return character.getFemininity().getColour();
						}
						@Override
						public void effects() {
							waiter = character;
							Main.game.getPlayer().applyFoodConsumed(ROOM_DINING_HALL_RECEIVE_MEAL.getSecondsPassed()/60);
							Main.game.getPlayer().applyDrinkConsumed(ROOM_DINING_HALL_RECEIVE_MEAL.getSecondsPassed()/60);
						}
					};
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode ROOM_DINING_HALL_RECEIVE_MEAL = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_RECEIVE_MEAL", waiter);
		}
		@Override
		public Response getResponse(int responseTab, int index) {

			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
			if (index == 1) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("完成", "吃完你的饭。", ROOM_DINING_HALL);
				}
				
			} else if(index==2) {
				if(!waiter.hasPenis()) {
					return new Response("甜点(口交)", UtilText.parse(waiter, "因为[npc.name]没有鸡巴，你不能享用他的“甜点”……"), null);
					
				} else if(!waiter.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("甜点(口交)", UtilText.parse(waiter, "[npc.Name]的肉棒受到限制，所以你不能享用这道甜点……"), null);
					
				} else {
					return new ResponseSex("甜点(口交)", UtilText.parse(waiter, "让[npc.name]去厨房把自己的肉棒变成一道甜点，再回来让你舔。"), 
							true, false,
							getDessertSexManager(
									waiter.isTaur()
										?SexPosition.SITTING
										:SexPosition.OVER_DESK,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), waiter.isTaur()?SexSlotSitting.SITTING:SexSlotDesk.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(waiter, waiter.isTaur()?SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL:SexSlotDesk.OVER_DESK_ON_BACK)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)),
											new Value<>(waiter, Util.newArrayListOfValues(CoverableArea.PENIS)))),
							SlaveDialogue.getDominantSpectators(),
							SlaveDialogue.getSubmissiveSpectators(),
							AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_RECEIVE_MEAL_DESSERT_COCK", waiter)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), waiter, PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					};
				}
				
			} else if(index==3) {
				if(!waiter.hasVagina()) {
					return new Response("甜点(舔阴)", UtilText.parse(waiter, "[npc.name]没有阴户，你无法享用这道甜点……"), null);
					
				} else if(!waiter.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response("甜点(舔阴)", UtilText.parse(waiter, "[npc.Name]无法使用[npc.her]的阴户，你无法享用这道甜点……"), null);
					
				} else {
					return new ResponseSex("甜点(舔阴)", UtilText.parse(waiter, "让[npc.name]去厨房把自己的小穴变成一道甜点，再回来让你舔。"), 
							true, false,
							getDessertSexManager(
									waiter.isTaur()
										?SexPosition.SITTING
										:SexPosition.OVER_DESK,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), waiter.isTaur()?SexSlotSitting.SITTING:SexSlotDesk.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(waiter, waiter.isTaur()?SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL:SexSlotDesk.OVER_DESK_ON_BACK)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)),
											new Value<>(waiter, Util.newArrayListOfValues(CoverableArea.VAGINA)))),
							SlaveDialogue.getDominantSpectators(),
							SlaveDialogue.getSubmissiveSpectators(),
							AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_RECEIVE_MEAL_DESSERT_PUSSY", waiter)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), waiter, TongueVagina.CUNNILINGUS_START, false, true));
						}
					};
				}
				
			} else if(index==4 && Main.game.isAnalContentEnabled()) {
				if(!waiter.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
					return new Response("甜点(舔肛)", UtilText.parse(waiter, "[npc.Name]无法使用[npc.her]的肛门，你无法享用这道甜点……"), null);
					
				} else {
					return new ResponseSex("甜点(舔肛)", UtilText.parse(waiter, "让[npc.name]去厨房把自己的菊穴变成一道甜点，再回来让你舔。"), 
							true, false,
							getDessertSexManager(
									waiter.isTaur()
										?SexPosition.SITTING
										:SexPosition.OVER_DESK,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), waiter.isTaur()?SexSlotSitting.SITTING:SexSlotDesk.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(waiter, waiter.isTaur()?SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL:SexSlotDesk.OVER_DESK_ON_FRONT)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)),
											new Value<>(waiter, Util.newArrayListOfValues(CoverableArea.ANUS)))),
							SlaveDialogue.getDominantSpectators(),
							SlaveDialogue.getSubmissiveSpectators(),
							AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_RECEIVE_MEAL_DESSERT_ASS", waiter)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), waiter, TongueAnus.ANILINGUS_START, false, true));
						}
					};
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_SEX = new DialogueNode("返回", "", true) {
		@Override
		public String getDescription(){
			return "你已经满足了，可以离开让[npc.name]休息一下。";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_AFTER_SEX", waiter);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("继续", "决定接下来做什么。", ROOM_DINING_HALL);
				
			} else {
				return null;
			}
		}
	};
}
