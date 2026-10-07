package com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7.5
 * @version 0.3.7.5
 * @author Innoxia
 */
public class Warehouses {
	
	public static final DialogueNode WAREHOUSE_DISTRICT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/warehouses", "WAREHOUSE_DISTRICT"));
			
			boolean businessFound = false;
			if(Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD) || Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD_STAMPED)) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "WAREHOUSE_DISTRICT_DOMINION_EXPRESS"));
				businessFound = true;
			}
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_1_STOCK_ISSUES)) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "WAREHOUSE_DISTRICT_KAYS_TEXTILES"));//TODO
				businessFound = true;
			}
			if(!businessFound){
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/warehouses", "WAREHOUSE_DISTRICT_NO_BUSINESS"));
			}
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			if((Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD) || Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD_STAMPED))
					&& !Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_NATALYA)) {
				if(!Main.game.isExtendedWorkTime() && !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
					responses.add(new Response("御城速递",
									"御城速递现在不接待来访公众。若想再见到娜塔莉亚，需要在[unit.time(6)]-[unit.time(22)]之间再来。",
									null));
				} else {
					responses.add(new Response("御城速递",
									"进入娜塔莉亚的快递公司“御城速递”的主仓库。",
									DominionExpress.INITIAL_ENTRANCE) {
								@Override
								public void effects() {
									Main.game.getPlayer().setLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_EXIT);
								}
							});
				}
			}
			
			// Nyan quest:
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_1_STOCK_ISSUES)) {
				if(!Main.game.isExtendedWorkTime()) {
					responses.add(new Response(WorldType.TEXTILES_WAREHOUSE.getName(),
									"“"+WorldType.TEXTILES_WAREHOUSE.getName()+"”的仓库入口旁挂着一个标签，上面表示只在[unit.time(6)]-[unit.time(22)]开门。"
										+ "如果你想进去，最好晚点再来……",
									null));
					
				} else {
					responses.add(new Response(WorldType.TEXTILES_WAREHOUSE.getName(),
							"进入存放业务的仓库'"+WorldType.TEXTILES_WAREHOUSE.getName()+"'。",
							KaysWarehouse.INITIAL_ENTRY) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_ENTRANCE);
//							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "INITIAL_ENTRY"));
						}
					});
				}
			}
			
			if(index>0 && index-1<responses.size()) {
				return responses.get(index-1);
			}
			
			return null;
		}
	};

}
