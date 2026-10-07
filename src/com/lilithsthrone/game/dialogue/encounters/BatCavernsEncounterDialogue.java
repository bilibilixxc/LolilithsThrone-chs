package com.lilithsthrone.game.dialogue.encounters;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.4
 * @version 0.3.21
 * @author Innoxia, DSG
 */
public class BatCavernsEncounterDialogue {

	public static final DialogueNode FIND_ITEM = new DialogueNode("丢弃的物品", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR) || Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.BAT_CAVERN_LIGHT)) {
				if(((AbstractItem) AbstractEncounter.getRandomItem()).getItemType()==ItemType.MUSHROOM) {
					return UtilText.parseFromXMLFile("places/submission/batCaverns", "FIND_MUSHROOMS_LIGHT")
							+ "<p style='text-align:center;'>"
								+ "<b>"+ AbstractEncounter.getRandomItem().getDisplayName(true)+ "</b>"
							+ "</p>";
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/batCaverns", "FIND_ITEM_LIGHT")
							+ "<p style='text-align:center;'>"
								+ "<b>"+ AbstractEncounter.getRandomItem().getDisplayName(true)+ "</b>"
							+ "</p>";
				}
				
			} else {
				if(((AbstractItem) AbstractEncounter.getRandomItem()).getItemType()==ItemType.MUSHROOM) {
					return UtilText.parseFromXMLFile("places/submission/batCaverns", "FIND_MUSHROOMS_DARK")
							+ "<p style='text-align:center;'>"
								+ "<b>"+ AbstractEncounter.getRandomItem().getDisplayName(true)+ "</b>"
							+ "</p>";
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/batCaverns", "FIND_ITEM_DARK")
							+ "<p style='text-align:center;'>"
								+ "<b>"+ AbstractEncounter.getRandomItem().getDisplayName(true)+ "</b>"
							+ "</p>";
				}
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("拿走", "把" + AbstractEncounter.getRandomItem().getName() + "加入你的物品栏。", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem((AbstractItem) AbstractEncounter.getRandomItem(), true, true));
					}
				};
				
			} else if (index == 2) {
				return new Response("离开", "把" + AbstractEncounter.getRandomItem().getName() + "留在地上。", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};
		
	public static final DialogueNode REBEL_BASE_DISCOVERED = new DialogueNode("奇怪的拉杆", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE);
			Main.game.getPlayerCell().getPlace().setName(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE.getName());
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_HANDLE_REFUSED));
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DISCOVERED");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("拉动拉杆", "能有什么问题呢？", REBEL_BASE_DOOR_NO_PASS);

			} else if (index == 2) {
				return new Response("不拉动", "拉动洞穴中未知的拉杆从来不会发生什么好事。", PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE.getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_DOOR_NO_PASS = new DialogueNode("奇怪的拉杆", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_ONE));
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_NO_PASS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("用力拉", "拉杆纹丝不动，看来你需要个密码。", null);
					
			} else if (index == 2) {
				return new Response("不拉动", "去寻找密码吧。", PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE.getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_PASSWORD_ONE = new DialogueNode("日记碎片", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO));
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.BAT_CAVERN_DARK ||
				Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rebelBaseDarkPassFound, true);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rebelBaseLightPassFound, true);
			}
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.BAT_CAVERN_LIGHT) {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_LIGHT"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_DARK"));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_ONE"));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "这只是密码的一部分，还需要找到其他的", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_PASSWORD_TWO = new DialogueNode("另一块日记碎片", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_COMPLETE));
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.BAT_CAVERN_LIGHT) {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_LIGHT"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_DARK"));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_TWO"));
			
			return sb.toString();	   
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "你已经找到了两部分密码，准备好之后就可以回到那个神秘的拉杆处了。", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_PASSWORD_SEARCH_FAILED = new DialogueNode("真不幸", "", false, true) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {			
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_SEARCH_FAIL");   
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_PASSWORD_SILLY = new DialogueNode("时间宝贵", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.SIDE_UTIL_COMPLETE));
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {			
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_SILLY");   
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "你赢了。万岁。", Main.game.getDefaultDialogue(false)) {
				    @Override
				    public void effects() {
						Main.game.getWorlds().get(WorldType.BAT_CAVERNS).getCell(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE).getPlace().setPlaceType(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR);
						Main.game.getWorlds().get(WorldType.BAT_CAVERNS).getCell(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR).getPlace().setName(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR.getName());
				    }
				};
			}
			return null;
		}
	};
}
