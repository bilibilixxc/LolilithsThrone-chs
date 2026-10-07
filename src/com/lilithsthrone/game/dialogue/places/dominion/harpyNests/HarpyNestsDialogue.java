package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.7.3
 * @author Innoxia
 */
public class HarpyNestsDialogue {
	
	public static final DialogueNode OUTSIDE = new DialogueNode("哈比之巢", "哈比之巢", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/generic", "OUTSIDE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("哈比之巢", "上去到哈比之巢。", PlaceType.HARPY_NESTS_ENTRANCE_ENFORCER_POST.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/generic", "OUTSIDE_ENTRY"));
						Main.game.getPlayer().setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_ENTRANCE_ENFORCER_POST, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENTRANCE_ENFORCER_POST = new DialogueNode("执法者岗哨", ".", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/generic", "ENTRANCE_ENFORCER_POST");
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasHarpyNestAccess);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return null;
			}
			if (index == 1) {
				return new Response("街道", "下去到御城区街道。", PlaceType.DOMINION_HARPY_NESTS_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						if(Main.game.getPlayer().isAbleToFly()) {
							Main.game.getTextStartStringBuilder()
								.append("<p>Stepping back out through the same door you used to enter the Enforcer post, you")
								.append(!Main.game.getPlayer().isAbleToFlyFromExtraParts() ? " spread your wings and" : "")
								.append(" swoop back down to street level.</p>");
						} else {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "你再次进入将你带到执法者岗哨的电梯，很快就回到了街道上。"
									+ "</p>");
						}
						
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HARPY_NESTS_ENTRANCE, false);
					}
				};
			}
			
			List<Response> responses = new ArrayList<>();
			
			if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasHarpyNestAccess)) {
				responses.add(
						new Response("请求通行", "到桌子前询问你能否进入哈比之巢。", ENTRANCE_ENFORCER_POST_ASK_FOR_ACCESS) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().values.add(DialogueFlagValue.hasHarpyNestAccess);
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.getLoreBook(Subspecies.HARPY)), false, true));
							}
						});
				
			} else {
				if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_HARPY_PACIFICATION)) {
					responses.add(
							new Response("愤怒的哈比", "询问其中一个执法者哈比之巢最近出什么幺蛾子了。", ENTRANCE_ENFORCER_POST_ASK_ABOUT_RIOTS));
					
				} else {
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_REWARD && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)) {
						responses.add(
								new Response("报告", "向执法者报告你已经将三位族长平息下来了。", ENTRANCE_ENFORCER_POST_COMPLETED_PACIFICATION) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.SIDE_UTIL_COMPLETE));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(5000));
										Main.game.getDialogueFlags().setSavedLong("angry_harpies_completed", Main.game.getMinutesPassed());
									}
								});
						
					} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)){
						responses.add(
								new Response("报告", "你还没能把三位族长都平息下来！", null));
					}
				}
			}
			
			if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_BUYING_BRAX)
					&& Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_LOLLIPOPS
					&& !Main.game.getPlayer().hasItemType(ItemType.CANDI_CONTRABAND)) {
				responses.add(
						new Response("坎迪的棒棒糖", "询问附近的执法者，知不知道坎迪要求的那箱棒棒糖。", ENTRANCE_ENFORCER_POST_CANDIS_LOLLIPOPS) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.CANDI_CONTRABAND), false));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_DELIVER_LOLLIPOPS));
					}
				});
				
			}
			
			for(int i=0;i<responses.size();i++) {
				if(index-2==i) {
					return responses.get(i);
				}
			}
			
			return null;
			
		}
	};
	
	public static final DialogueNode ENTRANCE_ENFORCER_POST_ASK_FOR_ACCESS = new DialogueNode("执法者岗哨", ".", true) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasHarpyNestAccess);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/generic", "ENTRANCE_ENFORCER_POST_ASK_FOR_ACCESS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE_ENFORCER_POST.getResponse(0, index);
		}
	};
	
	public static final DialogueNode ENTRANCE_ENFORCER_POST_ASK_ABOUT_RIOTS = new DialogueNode("执法者岗哨", ".", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/generic", "ENTRANCE_ENFORCER_POST_ASK_ABOUT_RIOTS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("跟随", "按照马男说的做，跟随他", ENTRANCE_ENFORCER_POST_ASK_ABOUT_RIOTS_NEXT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_HARPY_PACIFICATION));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE_ENFORCER_POST_ASK_ABOUT_RIOTS_NEXT = new DialogueNode("执法者岗哨", ".", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/generic", "ENTRANCE_ENFORCER_POST_ASK_ABOUT_RIOTS_NEXT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("离开", "你在同意帮助平息哈比之巢后，离开了马男的办公室……", ENTRANCE_ENFORCER_POST) {
					@Override
					public void effects() {
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/harpyNests/generic", "ENTRANCE_ENFORCER_POST_ASK_ABOUT_RIOTS_NEXT_LEAVE"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE_ENFORCER_POST_COMPLETED_PACIFICATION = new DialogueNode("执法者岗哨", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/generic", "ENTRANCE_ENFORCER_POST_COMPLETED_PACIFICATION");
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasHarpyNestAccess);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE_ENFORCER_POST.getResponse(0, index);
		}
	};
	

	public static final DialogueNode ENTRANCE_ENFORCER_POST_CANDIS_LOLLIPOPS = new DialogueNode("执法者岗哨", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/generic", "ENTRANCE_ENFORCER_POST_CANDIS_LOLLIPOPS");
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasHarpyNestAccess);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE_ENFORCER_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WALKWAY = new DialogueNode("通道", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/generic", "WALKWAY"));
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/generic", "WALKWAY_CORE", new ArrayList<>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)) {
					return new ResponseEffectsOnly(
							"挑事",
							"虽然你已经平息了哈比的巢穴，但你找到一只想打架的哈比肯定不难……"){
								@Override
								public int getSecondsPassed() {
									return 30*60;
								}
								@Override
								public void effects() {
									DialogueNode dn = Encounter.HARPY_NEST_LOOK_FOR_TROUBLE.getRandomEncounter(true);
									Main.game.setContent(new Response("", "", dn));
								}
							};
							
				} else {
					return AbstractEncounter.exploreArea("通道");
				}
			} else if (index == 2) {
				return AbstractEncounter.useOffspringMap();
			}
			return null;
		}
	};
	
	public static final DialogueNode WALKWAY_BRIDGE = new DialogueNode("桥路", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/generic", "WALKWAY_BRIDGE"));
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/generic", "WALKWAY_CORE", new ArrayList<>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WALKWAY.getResponse(responseTab, index);
		}
	};
	
}
