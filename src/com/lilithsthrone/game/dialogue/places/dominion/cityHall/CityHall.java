package com.lilithsthrone.game.dialogue.places.dominion.cityHall;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class CityHall {
	
	private static NPC lodger;
	
	private static String getImportRow(String name) {
		String baseName = Util.getFileName(name);
		String identifier = Util.getFileIdentifier(name);
		
		return "<tr>"
				+ "<td style='min-width:200px;'>"
					+ baseName
				+ "</td>"
				+ "<td>"
					+ "<div class='saveLoadButton' id='IMPORT_LODGER_" + identifier + "' style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>导出</div>"
				+ "</td>"
				+ "</tr>";
	}

	public static void setupLodger(NPC lodger) {
		CityHall.lodger = lodger;
		Main.game.setActiveNPC(lodger);
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("门厅", "，", false) {

		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "OUTSIDE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getHourOfDay()>=9 && Main.game.getHourOfDay()<=16) {
					return new Response("进入", "御城区市政厅对公众开放，所以你可以随意进出。", CITY_HALL_FOYER) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_ENTRANCE, false);
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "ENTRY"));
						}
					};
					
				} else {
					return new Response("进入","御城区市政厅现在不对公众开放， 所以如果你需要办理任何事务， 请在早上九点到下午四点之间再来。", null);
				}
				
			}
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_FOYER = new DialogueNode("前厅", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_FOYER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "穿过旋转玻璃门，回到御城区。", OUTSIDE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_CITY_HALL, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_INFORMATION_DESK = new DialogueNode("咨询台", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_INFORMATION_DESK");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("修改姓名", "询问其中一个接待能不能修改自己的姓名。", CITY_HALL_INFORMATION_DESK_NAME_CHANGE);
				
			} else if(index == 2) {
				return new Response("地产", "询问其中一个接待，能否在御城区买房或者租房。", CITY_HALL_INFORMATION_DESK_PROPERTY);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_INFORMATION_DESK_NAME_CHANGE = new DialogueNode("咨询台", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_INFORMATION_DESK_NAME_CHANGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("修改姓名", "你已经问过改名的事了。", null);
				
			} else if(index == 2) {
				return new Response("地产", "询问其中一个接待，能否在御城区买房或者租房。", CITY_HALL_INFORMATION_DESK_PROPERTY);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_INFORMATION_DESK_PROPERTY = new DialogueNode("咨询台", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_INFORMATION_DESK_PROPERTY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("修改姓名", "询问其中一个接待能不能修改自己的姓名。", CITY_HALL_INFORMATION_DESK_NAME_CHANGE);
				
			} else if(index == 2) {
				return new Response("地产", "你已经问过在御城区买房、租房的事情了。", null);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_CORRIDOR = new DialogueNode("走廊", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 15;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_WAITING_AREA = new DialogueNode("等待室", "", false) {
		@Override
		public void applyPreParsingEffects() {
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_WAITING_AREA_LODGER_QUEST"));
				Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
				
			} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_WAITING_AREA_LODGER_QUEST"));
				
			} else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_WAITING_AREA_LODGER_NO_ROOM"));
				
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_WAITING_AREA_LODGER_AVAILABLE"));
			}
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.cityHallLodgerBoardSeen, true);
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_WAITING_AREA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					return new Response("房客", "莉莱雅还没有允许你在她的宅邸里接待房客，现在去看告示板也没有意义。", null);
				}
				if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
					return new Response("房客", "没有房客能够入住的空房间，现在去看告示板也没有意义。", null);
				}
				return new Response("房客", "仔细瞧瞧告示牌，有没有正在寻求住处的房客。", CITY_HALL_WAITING_AREA_LODGER_LIST);
			}
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_WAITING_AREA_LODGER_LIST = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_WAITING_AREA_LODGER_LIST"));
			UtilText.nodeContentSB.append(
					"<p style='text-align:center;'>"
						//+ "<b>Seeking Lodging</b>"
						+ "<div class='container-full-width' style='margin-bottom:0; text-align:center;'>"
							+ "<div style='width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "名字"
							+ "</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>性别</b>"
							+"</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>种族</b>"
							+"</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+PresetColour.BASE_BROWN.toWebHexString()+";'>工作</b>"
							+"</div>"
							+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "接近"
							+ "</div>"
						+ "</div>");
			
			List<NPC> charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			
			Collections.sort(charactersPresent, (e1, e2) -> e1.getName(true).compareTo(e2.getName(true)));
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width' style='margin-bottom:0; text-align:center;'>"
								+ "<b>找不到任何房客……</b>"
						+ "</div>");
				
			} else {
				int i=0;
				for(NPC slave : charactersPresent){
					boolean alternateBackground = i%2==0;
					
					UtilText.nodeContentSB.append(UtilText.parse(slave,
							"<div class='container-full-width inner' style='margin-bottom:0;"+(alternateBackground?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"'>"
								+ "<div style='width:40%; float:left; margin:0; padding:0; text-align:center;'>"
									+ "<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+slave.getName(true)+"</span>"
								+ "</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ UtilText.parse(slave, "[npc.Gender(true)]")
								+"</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ "<span style='color:"+slave.getSubspecies().getColour(slave).toWebHexString()+";'>"+UtilText.parse(slave, "[npc.Race]")+"</span>"
								+"</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ Util.capitaliseSentence(slave.getOccupation().getName(slave))
								+"</div>"
								+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0;'>"
									+ "<div id='"+slave.getId()+"_LODGER' class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getPeopleIcon()+"</div></div>"
								+ "</div>"
							+ "</div>"
							));
					i++;
				}
			}
			
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("导入", "查看导入界面的角色。", LODGER_IMPORT);
				
			} else if(index==0) {
				return new Response("返回", "从告示牌边退开。", CITY_HALL_WAITING_AREA);
			}
			return null;
		}
	};
	
	public static final DialogueNode LODGER_IMPORT = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();

			saveLoadSB.append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "LODGER_IMPORT_START"));
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
				return new Response("返回", "回到上一界面。", CITY_HALL_WAITING_AREA_LODGER_LIST);
			}
			return null;
		}
	};

	public static final DialogueNode CITY_HALL_APPROACH_LODGER = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_APPROACH_LODGER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("提供房间",
						"告诉[npc.name]你想给[npc.herHim]提供一个住的地方。",
						LODGER_GIVEN_ROOM) {
					@Override
					public void effects() {
						Cell c = OccupancyUtil.getFreeRoomForOccupant();
						lodger.setLocation(c.getType(), c.getLocation(), true);
						Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
						Main.game.getPlayer().addFriendlyOccupant(lodger);
						Main.game.getTextEndStringBuilder().append(lodger.setAffection(Main.game.getPlayer(), 25));
					}
				};
				
			} else if(index == 2) {
				return new Response("离开",
						"你对[npc.name]的第一印象不太妙，决定还是不给[npc.herHim]提供住处了……",
						LODGER_DENIED);
			}
			return null;
		}
	};

	public static final DialogueNode LODGER_DENIED = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "LODGER_DENIED"));
			Main.game.banishNPC(lodger);
		}
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
			if (index == 1) {
				return new Response("继续", "你让[npc.name]住进了新房间，便继续今天的其他计划了……", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode LODGER_GIVEN_ROOM = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "LODGER_GIVEN_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "你让[npc.name]住进了新房间，便继续今天的其他计划了……", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_OFFICE = new DialogueNode("私人办公室", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CITY_HALL_STAIRS = new DialogueNode("楼梯", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "CITY_HALL_STAIRS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
}
