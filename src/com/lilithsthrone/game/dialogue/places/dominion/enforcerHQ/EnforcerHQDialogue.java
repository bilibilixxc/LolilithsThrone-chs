package com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.npc.dominion.Elle;
import com.lilithsthrone.game.character.npc.dominion.Wes;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.WesQuest;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.dominion.SMBraxDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.9.4
 * @author Innoxia
 */
public class EnforcerHQDialogue {
	
	public static void obtainBraxAsSlave() {
		Main.game.getPlayer().addSlave(Main.game.getNpc(Brax.class));
		
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.bimbofiedBrax)) {
			Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, false), false);
			Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_PINK, false), false);
			Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), false);
			
			Main.game.getNpc(Brax.class).setObedience(50);
		} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feminisedBrax)) {
			Main.game.getNpc(Brax.class).setObedience(-20);
		} else {
			Main.game.getNpc(Brax.class).setObedience(-80);
		}
		
		AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLUE, null, false);
		jacket.setSticker("collar", "tab_ip");
		jacket.setSticker("name", "name_brax");
		jacket.setSticker("ribbon", "ribbon_brax");
		Main.game.getNpc(Brax.class).addClothing(jacket, false);
		
		Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), false);

		AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false);
		hat.setSticker("badge", "badge_dominion");
		Main.game.getNpc(Brax.class).addClothing(hat, false);
		
		Main.game.getNpc(Brax.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
	}

	public static final DialogueNode EXTERIOR = new DialogueNode("执法者总部", "执法者总部", false) {
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "EXTERIOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进入", "穿过场地进入执法者总部。", PlaceType.ENFORCER_HQ_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_ENTRANCE, false);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_2) {
					if(Main.game.getHourOfDay()<16 || Main.game.getHourOfDay()>=18) {
						return new Response("埃勒",
								"背叛韦斯，等埃勒下班后将一切都告诉她……"
									 +"<br/>你只能在埃勒下班的时候做这件事，也就是[style.italicsMinorBad([units.time(16)]-[units.time(18)])]之间",
								null);
					}
					
					return new Response("埃勒", "背叛韦斯，等埃勒下班后将一切都告诉她……", WesQuest.APPROACH_ELLE);
				}
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES)) {
					return new Response("进入(征用区)", "穿过场地，通过执法者入口进入总部。", PlaceType.ENFORCER_HQ_ENFORCER_ENTRANCE.getDialogue(false)){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_ENFORCER_ENTRANCE, false);
						}
					};
				}
			} 
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CORRIDOR"));
			try {
				if(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1).getPlace().getPlaceType()==PlaceType.ENFORCER_HQ_BRAXS_OFFICE) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CORRIDOR_BRAX_WARNING"));
				}
			} catch(Exception ex) {
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode CORRIDOR_PLAIN = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			try {
				if(Vector2i.getDistance(Main.game.getPlayer().getLocation(), Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(PlaceType.ENFORCER_HQ_ENFORCER_ENTRANCE).getLocation())<=2) {
					return  UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CORRIDOR_PLAIN_ENFORCER_ENTRANCE");
				}
			} catch(Exception ex) {
			}
			
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CORRIDOR_PLAIN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "离开执法者总部。", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WAITING_AREA = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "WAITING_AREA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode STAIRCASE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "STAIRCASE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CELLS_OFFICE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CELLS_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CELL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CELL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode GUARDED_DOOR = new DialogueNode("有安保的门", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public boolean isTravelDisabled() {
			return (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.accessToEnforcerHQ) || Main.game.isBraxMainQuestComplete())
					&& !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "GUARDED_DOOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1 && isTravelDisabled()) {
				return new Response("退开", "除了照执法者说的去做，你不觉得还有别的选择。", PlaceType.ENFORCER_HQ_WAITING_AREA.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "GUARDED_DOOR_STEP_BACK"));
						
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LOCKED_DOOR = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "LOCKED_DOOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("退开", "除了从锁着的门前退开，你不觉得还有别的选择……", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "LOCKED_DOOR_STEP_BACK"));
						
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CORRIDOR, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode REQUISITIONS_DOOR = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "REQUISITIONS_DOOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && isTravelDisabled()) {
				return new Response("退开", "除了从锁着的门前退开，你不觉得还有别的选择……", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "LOCKED_DOOR_STEP_BACK"));
						
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode RECEPTION_DESK = new DialogueNode("前台", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_3_WES) {
				long days = 7-((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID))/(60*24));
				UtilText.addSpecialParsingString(Util.intToString((int) days)+"天", true);
				sb.append(UtilText.parseFromXMLFile("characters/dominion/wes", "RECEPTION_DESK_WES"));
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_3_ELLE) {
				long days = 7-((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID))/(60*24));
				UtilText.addSpecialParsingString(Util.intToString((int) days)+"天", true);
				sb.append(UtilText.parseFromXMLFile("characters/dominion/wes", "RECEPTION_DESK_ELLE"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.isWorkTime()) {
				if(index==0) {
					return new Response("退开",
							"没有可以交谈的人，只能从台前退开。",
							WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA, false);
						}
					};
					
				} else if(index==5) {
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_2) {
						return new Response("匿名密报", "你需要等到坎迪工作时才能把奥术录像机存放在这里。", null);
					}
				}
				return null;
			}
			
			if(index==0) {
				return new Response("退开",
						"告诉坎迪你过会儿再来，然后从台前推开，让她继续化妆。",
						WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA, false);
						if(!Main.game.getNpc(CandiReceptionist.class).isCharacterReactedToPregnancy(Main.game.getPlayer()) && Main.game.getNpc(CandiReceptionist.class).isVisiblyPregnant()) {
							Main.game.getNpc(CandiReceptionist.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
						if(Main.game.getNpc(Brax.class).isSlave() && Main.game.getNpc(Brax.class).getOwner().equals(Main.game.getNpc(CandiReceptionist.class))) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
						}
					}
				};
			}
			
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_BUYING_BRAX)) {
				if(index==1) {
					if(Main.game.getSecondsPassed()-Main.game.getDialogueFlags().getSavedLong(CandiReceptionist.CANDI_SEX_TIMER_ID)>60*60*12) {
						return new ResponseSex("帮助坎迪",
								"同意帮坎迪解决性欲过剩的问题。",
								null, null, null, null, null, null,
								true,
								true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(CandiReceptionist.class), SexSlotStanding.STANDING_SUBMISSIVE))),
								null,
								null,
								AFTER_SEX_CANDI,
								UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "START_SEX_CANDI")) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(CandiReceptionist.class).isCharacterReactedToPregnancy(Main.game.getPlayer()) && Main.game.getNpc(CandiReceptionist.class).isVisiblyPregnant()) {
									Main.game.getNpc(CandiReceptionist.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
							}
						};
						
					} else {
						return new Response("帮助坎迪",
								"你最近已经帮过坎迪解决性欲过剩的问题了，但是下一次她乞求你跟她做爱不过也就是几个小时的事……",
								null);
					}
				}
				
			} else if(Main.game.isBraxMainQuestComplete()) {
				if(Main.game.getNpc(Brax.class).isSlave() && Main.game.getNpc(Brax.class).getOwner().equals(Main.game.getNpc(CandiReceptionist.class))) {
					if (index == 1) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
							return new ResponseSex("惩罚[brax.name]", "与[brax.name]进行支配式性爱。",
									false, false,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
									null,
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK_PUNISH_BRAX")) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
							return new ResponseSex("惩罚[brax.name]", "与[brax.name]进行支配式性爱。",
									false, false,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
									null,
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK_PUNISH_BREE")){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else {
							return new ResponseSex("惩罚[brax.name]", "与[brax.name]进行支配式性爱。",
									false, false,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
									null,
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK_PUNISH_BRANDI")){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
						}
						
					} else if (index == 2) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
							return new ResponseSex("被[brax.name]惩罚", "让[brax.name]在你的身上释放怒火。", Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
									null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
									true, false,
									new SMBraxDoggy(
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
									null,
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK_PUNISHED_BY_BRAX")){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else {
							return new Response("被[brax.name]惩罚", "[brax.Name]已经过于顺从，无法再惩罚你。如果你想跟[brax.him]做爱，就必须主导。", null);
						}
						
					} else if (index == 3) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
							return new Response("雌化[brax.name]", "将[brax.name]转化为狼女。", INTERIOR_SECRETARY_BRAX_FEMINISE){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
							return new Response("让[brax.name]变成无脑大胸", "将[brax.name]转化为脑子空空的无脑大胸。", INTERIOR_SECRETARY_BRAX_BIMBOFY){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
						}
						
					} else if (index == 4) {
						if(!Main.game.getPlayer().isHasSlaverLicense()) {
							return new Response("购买[brax.name]", "由于你没有贩奴许可，无法拥有奴隶，所以就算坎迪想卖[brax.name]你也买不了。", null);
						}
						if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_BUYING_BRAX)) {
							return new Response("购买[brax.name]", "询问坎迪是否愿意把[brax.name]卖给你。", BUYING_BRAX_INITIAL){
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_BUYING_BRAX));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(500));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_START
								|| Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_DELIVER_PERFUME) {
							if(Main.game.getPlayer().hasItemType(ItemType.CANDI_PERFUMES)) {
								return new Response("交出香水", "把你从凯特那边拿来的香水交给坎迪。", BUYING_BRAX_PERFUME_DELIVERY){
									@Override
									public void effects() {
										Main.game.getPlayer().removeItemByType(ItemType.CANDI_PERFUMES);
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().removedItemFromInventoryText(ItemType.CANDI_PERFUMES));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_LOLLIPOPS));
									}
								};
								
							} else {
								return new Response("交出香水", "你需要先从购物中心处“魅魔的秘密”拿到坎迪的香水，才能交给她！", null);
							}
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_LOLLIPOPS
								|| Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_DELIVER_LOLLIPOPS) {
							if(Main.game.getPlayer().hasItemType(ItemType.CANDI_CONTRABAND)) {
								return new Response("交出棒棒糖", "把你从哈比之巢执法点取回来的违禁品棒棒糖交给坎迪。", BUYING_BRAX_LOLLIPOP_DELIVERY){
									@Override
									public void effects() {
										Main.game.getPlayer().removeItemByType(ItemType.CANDI_CONTRABAND);
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().removedItemFromInventoryText(ItemType.CANDI_CONTRABAND));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_LIPSTICK));
									}
								};
								
							} else {
								return new Response("交出棒棒糖", "你需要先从哈比之巢执法点取回来违禁品棒棒糖，才能交给她！", null);
							}
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_LIPSTICK
								|| Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_DELIVER_LIPSTICK) {
							if(Main.game.getPlayer().hasItemType(ItemType.CANDI_HUNDRED_KISSES)) {
								return new Response("交出口红", "将那盒“百万之吻”交给坎迪。", BUYING_BRAX_LIPSTICK_DELIVERY){
									@Override
									public void effects() {
										Main.game.getPlayer().removeItemByType(ItemType.CANDI_HUNDRED_KISSES);
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().removedItemFromInventoryText(ItemType.CANDI_HUNDRED_KISSES));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.SIDE_UTIL_COMPLETE));
										
										obtainBraxAsSlave();
									}
								};
								
							} else {
								return new Response("交出口红", "你需要先从购物中心处“拉尔夫小吃店”拿到那盒“百万之吻”，才能交给她！", null);
							}
						}
					}
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_C_WOLFS_DEN && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.accessToEnforcerHQ)) {
				if (index == 1) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_BIMBO)) {
						return new Response("跟坎迪打招呼", "哎呀，天哪，她怎么这么漂亮呢！", INTERIOR_SECRETARY_BIMBO);
						
					} else {
						return new Response("跟坎迪打招呼", "打声招呼，引起她的注意。", INTERIOR_SECRETARY,
								null, null, null, null, null);
					}
				}
			}
			

			if(index==5) {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_2) {
					return new Response("匿名密报",
							"询问坎迪是否能匿名提交犯罪证据，这样就能将内有埃勒与帮派交易录像的奥术录像机存放在这里了。",
							WesQuest.CANDI_ELLE_EVIDENCE);
				}
				
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_3_WES) {
					if(Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID)<60*24*7) {
						long days = 7-((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID))/(60*24));
						return new Response("韦斯",
								"你将奥术录像机匿名提交还没有一个周，所以不应该去见韦斯……"
									+ "<br/>你还需要再等[style.italicsMinorBad("+days+"天"+")]！",
								null);
						
					} else {
						return new Response("韦斯",
								"你将奥术录像机匿名提交已经一周有余，可以告诉坎迪你是来见韦斯的了。",
								WesQuest.INTRO_HQ_WES);
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_3_ELLE) {
					if(Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID)<60*24*7) {
						long days = 7-((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID))/(60*24));
						return new Response("埃勒",
								"你将奥术录像机匿名提交还没有一个周，所以不应该去见埃勒……"
									+ "<br/>你还需要再等[style.italicsMinorBad("+days+"天"+")]！",
								null);
						
					} else {
						return new Response("埃勒",
								"你将奥术录像机匿名提交还没有一个周，所以不应该告诉坎迪你是来见埃勒的。",
								WesQuest.INTRO_HQ_ELLE);
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY = new DialogueNode("执法者总部", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("[brax.name]", "告诉她你是来找[brax.name]的。", INTERIOR_SECRETARY_BRAX){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.accessToEnforcerHQ);
					}
				};

			} else if (index == 0) {
				return new Response("退开",
						"告诉坎迪你过会儿再来，然后从台前推开，让她继续化妆。",
						WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_LEAVE"));
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
						}
					};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX = new DialogueNode("执法者总部", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("退开",
						"你已经得到了需要的东西，可以从前台退开，让这胸大无脑的执法者继续化妆了。",
						WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BIMBO = new DialogueNode("执法者总部", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BIMBO");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("[brax.name]啥的", "告诉她你是来找[brax.name]的。", INTERIOR_SECRETARY_BRAX_BIMBO){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.accessToEnforcerHQ);
					}
				};

			} else if (index == 0) {
				return new Response("退开",
						"告诉坎迪你过会儿再来，然后从台前推开，让她继续化妆。",
						WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BIMBO_LEAVE"));
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
						}
					};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_BIMBO = new DialogueNode("执法者总部", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBO");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("退开",
						"你已经得到了需要的东西，可以从前台退开，让这胸大无脑的执法者继续化妆了。",
						WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_FEMINISE = new DialogueNode("执法者总部", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("布瑞",
						"你和坎迪强行给[brax.name]灌下了他自己的药水，把他变成了一个叫做布瑞的狼女。",
						INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
						Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feminisedBrax, true);
						Main.game.getNpc(Brax.class).setName(new NameTriplet("布瑞", "布瑞", "布瑞"));
						
						Main.game.getNpc(Brax.class).removeFetish(Fetish.FETISH_DOMINANT);
						Main.game.getNpc(Brax.class).addFetish(Fetish.FETISH_SUBMISSIVE);
						
						Main.game.getNpc(Brax.class).setFemininity(75);
						Main.game.getNpc(Brax.class).setBreastSize(CupSize.C.getMeasurement());
						
						Main.game.getNpc(Brax.class).setBreastRows(3);
						
						Main.game.getNpc(Brax.class).setHipSize(HipSize.THREE_GIRLY.getValue());
						Main.game.getNpc(Brax.class).setAssSize(AssSize.FOUR_LARGE.getValue());
						Main.game.getNpc(Brax.class).setPenisType(PenisType.NONE);
						Main.game.getNpc(Brax.class).setVaginaType(VaginaType.WOLF_MORPH);
						Main.game.getNpc(Brax.class).setVaginaWetness(Wetness.ONE_SLIGHTLY_MOIST.getValue());
						Main.game.getNpc(Brax.class).setVaginaElasticity(OrificeElasticity.ONE_RIGID.getValue());
						Main.game.getNpc(Brax.class).setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_OLIVE), false);
//						Main.game.getNpc(Brax.class).setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
						
						Main.game.getNpc(Brax.class).setHeight(175);
						
						Main.game.getNpc(Brax.class).setVaginaVirgin(true);

						Main.game.getNpc(Brax.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_ACCESSORIES));
					}
				};
				
			} if (index == 2) {
				return new Response("布瑞(扶她)",
						"你和坎迪强行给[brax.name]灌下了他自己的药水，把他变成了一个叫做布瑞的狼女扶她。",
						INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
						Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feminisedBrax, true);
						Main.game.getNpc(Brax.class).setName(new NameTriplet("布瑞", "布瑞", "布瑞"));
						
						Main.game.getNpc(Brax.class).removeFetish(Fetish.FETISH_DOMINANT);
						Main.game.getNpc(Brax.class).addFetish(Fetish.FETISH_SUBMISSIVE);
						
						Main.game.getNpc(Brax.class).setFemininity(75);
						Main.game.getNpc(Brax.class).setBreastSize(CupSize.C.getMeasurement());
						
						Main.game.getNpc(Brax.class).setBreastRows(3);
						
						Main.game.getNpc(Brax.class).setHipSize(HipSize.THREE_GIRLY.getValue());
						Main.game.getNpc(Brax.class).setAssSize(AssSize.FOUR_LARGE.getValue());
						Main.game.getNpc(Brax.class).setVaginaType(VaginaType.WOLF_MORPH);
						Main.game.getNpc(Brax.class).setVaginaWetness(Wetness.ONE_SLIGHTLY_MOIST.getValue());
						Main.game.getNpc(Brax.class).setVaginaElasticity(OrificeElasticity.ONE_RIGID.getValue());
						Main.game.getNpc(Brax.class).setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_OLIVE), false);

//						Main.game.getNpc(Brax.class).setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
						
						Main.game.getNpc(Brax.class).setHeight(175);
						
						Main.game.getNpc(Brax.class).setVaginaVirgin(true);

						Main.game.getNpc(Brax.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_ACCESSORIES));
					}
				};
				
			} else if (index == 0) {
				return new Response("离开", "改变主意，不要动[brax.name]。", WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_CHANGE_MIND"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED = new DialogueNode("执法者总部", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("跟[brax.name]做爱", "跟[brax.name]做爱。",
						false, false,
						new SMStanding(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED_SEX"));
				
			} else if(index==2) {
				return new Response("拒绝", "决定还是不跟布瑞做爱。", WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED_NO_SEX"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_BIMBOFY = new DialogueNode("执法者总部", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("布兰迪", "将布瑞转化为脑子空空的无脑大胸，布兰迪。", INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
						Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimbofiedBrax);
						Main.game.getNpc(Brax.class).setName(new NameTriplet("布兰迪", "布兰迪", "布兰迪"));
						
						Main.game.getNpc(Brax.class).addFetish(Fetish.FETISH_BIMBO);
						
						Main.game.getNpc(Brax.class).setFemininity(100);
						Main.game.getNpc(Brax.class).setBreastSize(CupSize.KK.getMeasurement());
						Main.game.getNpc(Brax.class).setHipSize(HipSize.SEVEN_ABSURDLY_WIDE.getValue());
						Main.game.getNpc(Brax.class).setAssWetness(Wetness.SIX_SOPPING_WET.getValue());
						Main.game.getNpc(Brax.class).setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
						Main.game.getNpc(Brax.class).setAssSize(AssSize.SEVEN_GIGANTIC.getValue());
						
						Main.game.getNpc(Brax.class).setVaginaWetness(Wetness.SIX_SOPPING_WET.getValue());
						Main.game.getNpc(Brax.class).setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());

						Main.game.getNpc(Brax.class).setHeight(162);

//						Main.game.getNpc(Brax.class).setHairLength(HairLength.FIVE_ABOVE_ASS.getMedianValue());
//						Main.game.getNpc(Brax.class).setSkinCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, CoveringPattern.NONE, PresetColour.COVERING_BLEACH_BLONDE, false, PresetColour.COVERING_BLEACH_BLONDE, false), true);
						
						Main.game.getNpc(Brax.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_ACCESSORIES));
					}
				};
				
			} else if (index == 2) {
				return new Response("布兰迪(扶她)", "将布瑞转化为脑子空空的无脑大胸扶她，布兰迪。",
						INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
						Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimbofiedBrax);
						Main.game.getNpc(Brax.class).setName(new NameTriplet("布兰迪", "布兰迪", "布兰迪"));
						
						Main.game.getNpc(Brax.class).addFetish(Fetish.FETISH_BIMBO);
						
						Main.game.getNpc(Brax.class).setFemininity(100);
						Main.game.getNpc(Brax.class).setBreastSize(CupSize.KK.getMeasurement());
						Main.game.getNpc(Brax.class).setHipSize(HipSize.SEVEN_ABSURDLY_WIDE.getValue());
						Main.game.getNpc(Brax.class).setAssWetness(Wetness.SIX_SOPPING_WET.getValue());
						Main.game.getNpc(Brax.class).setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
						Main.game.getNpc(Brax.class).setAssSize(AssSize.SEVEN_GIGANTIC.getValue());

						Main.game.getNpc(Brax.class).setPenisType(PenisType.WOLF_MORPH);
						Main.game.getNpc(Brax.class).setVaginaWetness(Wetness.SIX_SOPPING_WET.getValue());
						Main.game.getNpc(Brax.class).setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());

						Main.game.getNpc(Brax.class).setHeight(162);

//						Main.game.getNpc(Brax.class).setHairLength(HairLength.FIVE_ABOVE_ASS.getMedianValue());
//						Main.game.getNpc(Brax.class).setSkinCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, CoveringPattern.NONE, PresetColour.COVERING_BLEACH_BLONDE, false, PresetColour.COVERING_BLEACH_BLONDE, false), true);
						
						Main.game.getNpc(Brax.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_ACCESSORIES));
					}
				};
				
			} else if (index == 0) {
				return new Response("离开", "改变主意，不要动布瑞。", WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_CHANGE_MIND"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED = new DialogueNode("执法者总部", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("跟布兰迪做爱", "跟布兰迪做爱。",
						true, false,
						new SMStanding(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED_SEX"));

			} else if(index==2) {
				return new Response("拒绝", "决定还是不跟布兰迪做爱。",  WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED_NO_SEX"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("完事", "返回前台。", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "AFTER_SEX"));
			sb.append(WAITING_AREA.getContent());
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WAITING_AREA.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEX_CANDI = new DialogueNode("完事", "退回到前台的另一侧。", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "AFTER_SEX_CANDI");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开",
						Main.game.getNpc(CandiReceptionist.class).isSatisfiedFromLastSex()
							?"让坎迪自己清理好身子，退回等待区。"
							:"让坎迪自己解决，退回等待区。",
						WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode BUYING_BRAX_INITIAL = new DialogueNode("前台", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_INITIAL");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return RECEPTION_DESK.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BUYING_BRAX_PERFUME_DELIVERY = new DialogueNode("前台", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_PERFUME_DELIVERY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return RECEPTION_DESK.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BUYING_BRAX_LOLLIPOP_DELIVERY = new DialogueNode("前台", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_LOLLIPOP_DELIVERY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return RECEPTION_DESK.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BUYING_BRAX_LIPSTICK_DELIVERY = new DialogueNode("前台", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_LIPSTICK_DELIVERY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getSecondsPassed()-Main.game.getDialogueFlags().getSavedLong(CandiReceptionist.CANDI_SEX_TIMER_ID)>60*60*12) {
					return new ResponseSex("帮助坎迪",
							"同意帮坎迪解决性欲过剩的问题。",
							null, null, null, null, null, null,
							true,
							true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(CandiReceptionist.class), SexSlotStanding.STANDING_SUBMISSIVE))),
							null,
							null,
							AFTER_SEX_CANDI,
							UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "START_SEX_CANDI"));
					
				} else {
					return new Response("帮助坎迪", "你最近已经帮过坎迪解决性欲过剩的问题了，但是下一次她乞求你跟她做爱不过也就是几个小时的事……", null);
				}
				
			} else 
				if(index==2) {
					return new Response("拒绝", "拒绝帮助坎迪解决性欲过剩的问题，退回等待区。", WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "HELP_CANDI_DENIED"));
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
						}
					};
				}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE_ENFORCER = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "ENTRANCE_ENFORCER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "离开执法者总部。", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode REQUISITIONS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Elle.class))
					&& !Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Wes.class))) {
				return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "REQUISITIONS");
				
			} else {
				return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_POST_QUEST");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Elle.class))
					&& !Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Wes.class))) {
				return null;
				
			} else {
				if(index==1) {
					return new Response("响铃",
							Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_WES, Quest.WES_3_ELLE)
								?"按响请购台的铃铛，引起埃勒的注意。"
								:"按响请购台的铃铛，引起韦斯的注意。",
							WesQuest.REQUISITIONS_INTERACTION);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_QUARTERMASTER = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "OFFICE_QUARTERMASTER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
}
