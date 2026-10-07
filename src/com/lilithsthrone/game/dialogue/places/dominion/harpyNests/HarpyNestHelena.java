package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaHotel;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public class HarpyNestHelena {
	
	private static float randomChance = 0f;
	
	private static SexManagerInterface getScarlettSexManager(AbstractSexPosition position,
			SexSlot scarlettSlot,
			SexSlot playerSlot,
			SexType scarlettPreference,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap,
			String publicSexStartDescription) {
		return new SexManagerDefault(
				false,
				position,
				Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), playerSlot))) {
			@Override 
			public String getPublicSexStartingDescription() {
				return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
							+ publicSexStartDescription
						+ "</p>";
			}
			@Override
			public String getRandomPublicSexDescription() {
				return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
							+ UtilText.parse(Main.sex.getTargetedPartner(Main.game.getPlayer()),
								UtilText.returnStringAtRandom(
									"跟着斯嘉丽的几只哈比有时窃笑，有时对你们的表演指指点点。",
									"有个哈比看着你们做爱，跟身边的朋友满口黄腔地评论了起来。",
									"好几只哈比对你们的表演评价起来。",
									"一群哈比看着你继续跟[npc.Name]做爱，互相嬉笑私语起来。",
									"你瞄了一眼，发现有几个哈比已经一边看着你和斯嘉丽一边抚摸起自己"))
						+"</p>";
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				if(partner.getWorldLocation()==WorldType.HARPY_NEST) { // If this is a scene in the nest, Scarlett stops after cumming.
					return super.isPartnerWantingToStopSex(partner);
				}
				return Main.sex.isSatisfiedFromOrgasms(partner, true) && (Main.sex.isOrgasmCountMet(Main.game.getPlayer(), 1, true) || Main.sex.getNumberOfOrgasms(partner)>=3);
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				if(character.isPlayer()) {
					return SexControl.ONGOING_ONLY;
				}
				return super.getSexControl(character);
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
				return clothingToEquip.isCondom();
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character){
				return character.getWorldLocation()!=WorldType.HARPY_NEST;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
				return character.getWorldLocation()!=WorldType.HARPY_NEST;
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isCharacterStartNaked(GameCharacter character) {
				return !character.isPlayer() && character.getWorldLocation()==WorldType.HELENAS_APARTMENT;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return exposeAtStartOfSexMap;
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer() && scarlettPreference!=null) {
					return scarlettPreference;
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
					return OrgasmBehaviour.CREAMPIE;
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
		};
	}
	
	private static void applyScarlettFuckedEffects() {
		Main.game.getNpc(Scarlett.class).returnToHome();
		Main.game.getNpc(Scarlett.class).equipClothing();
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.scarlettGoneHome, true);
		if(Main.game.getNpc(Scarlett.class).hasVagina()) {
			if(((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer() || Math.random()<0.8f) { // If Scarlett likes the player, she won't let anyone else get her pregnant. Also 80% chance for her to force her followers to pull out or use a condom.
				Main.game.getNpc(Scarlett.class).calculateGenericSexEffects(
						true, true, null, Subspecies.HARPY, Subspecies.HARPY, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED, GenericSexFlag.PREVENT_CREAMPIE);
			} else {
				Main.game.getNpc(Scarlett.class).calculateGenericSexEffects(
						true, true, null, Subspecies.HARPY, Subspecies.HARPY, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED);
			}
		}
	}
	
	public static final DialogueNode HELENAS_NEST_EXTERIOR = new DialogueNode("海伦娜的巢", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_EXTERIOR_STORM");
			} else if(!Main.game.isExtendedWorkTime()) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_EXTERIOR_SLEEPING");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_EXTERIOR");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
					return new Response("海伦娜",
							"海伦娜的族群正躲在巢下的建筑中躲避风暴。等奥术风暴过去后再来。",
							null);
					
				} else if(!Main.game.isExtendedWorkTime()) {
					return new Response("面见海伦娜",
							Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA)
								?"海伦娜和她的族群都在巢下的建筑中睡觉。如果想跟海伦娜谈话，应该去她在奴隶巷的商店。"
								:"海伦娜和她的族群都在巢下的建筑中睡觉。如果想跟海伦娜谈话，应该白天再来。",
							null);
					
				} else {
					if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_HELENA) {
						return new Response("海伦娜", "走上高台见见海伦娜。", HELENAS_NEST_MAIN_QUEST);
						
					} else if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA) && Main.game.getCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
						return new Response("海伦娜", "走上高台见见海伦娜。", HELENAS_NEST);
						
					}  else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA)) {
						return new Response("海伦娜", "海伦娜飞去奴隶巷了！你得去那找她。", null);
						
					} else {
						return new Response("海伦娜", "你没有合适的理由和海伦娜说话。", null);
					}
				}
				
			} else if(index==2) {
				if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
					if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
						return new Response("斯嘉丽",
								"由于外面奥术风暴正在肆虐，四家了和其余的巢内居民都在下面的建筑中躲避。"
										+ "如果想和她说话，要等奥术风暴过去后再来。",
								null);
						
					} else if(!Main.game.isExtendedWorkTime()) {
						return new Response("斯嘉丽",
								"斯嘉丽和族群的其余成员都在巢下的建筑中睡觉。如果想跟她谈话，应该白天再来。",
								null);
						
					} else {
						if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
							return new Response("斯嘉丽", "朝着被一群哈比包围的斯嘉丽走过去，打个招呼。", HELENAS_NEST_MEETING_SCARLETT);
						}
						return new Response("斯嘉丽", "朝着斯嘉丽坐着的位置走过去，打个招呼。", HELENAS_NEST_MEETING_SCARLETT);
					}
				}
				
			} else if(index==5 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateFirstDateComplete)) {
				return new Response("御城区", "使用电梯从“金羽毛”酒店上到御城区。", HelenaHotel.HOTEL_TRAVEL_TO_DOMINION) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HELENA_HOTEL);
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST = new DialogueNode("海伦娜的巢", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("斯嘉丽的麻烦", "告诉海伦娜斯嘉丽经营奴隶生意失败了。", HELENAS_NEST_MAIN_QUEST_SCARLETT);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_SCARLETT = new DialogueNode("海伦娜的巢", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_SCARLETT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("不接受惩罚", "不替斯嘉丽承受她的惩罚。", HELENAS_NEST_MAIN_QUEST_NO_PUNISHMENT) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
						Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
					}
				};
				
			} else if(index==2) {
				return new Response("接受惩罚", "替斯嘉丽接受她的惩罚。", HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT,
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_MASOCHIST),
						CorruptionLevel.THREE_DIRTY,
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.punishedByHelena);
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_NO_PUNISHMENT = new DialogueNode("海伦娜的巢", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_NO_PUNISHMENT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "离开海伦娜的巢。", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_LEAVING"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("跟着她飞", "跟着海伦娜起飞。", HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT);
					
				} else {
					return new Response("跟着她飞", "你不会飞行，必须走着去奴隶巷。", null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT = new DialogueNode("海伦娜的巢", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("忍耐", "尽量保持沉默，忍受惩罚。", HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENDURE) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
						Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
					}
				};
				
			} else if(index==2) {
				return new Response("挣扎", "开始挣扎，因不适而哭泣。", HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_STRUGGLE) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
						Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
					}
				};
				
			} else if(index==3) {
				return new Response("乞求更多", "乞求被惩罚。", HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENJOY,
						Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST),
						CorruptionLevel.THREE_DIRTY,
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
						Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENDURE = new DialogueNode("海伦娜的巢", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENDURE"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_END"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "离开海伦娜的巢。", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_LEAVING"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("跟着她飞", "跟着海伦娜起飞。", HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT);
					
				} else {
					return new Response("跟着她飞", "你不会飞行，必须走着去奴隶巷。", null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_STRUGGLE = new DialogueNode("海伦娜的巢", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_STRUGGLE"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_END"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "离开海伦娜的巢。", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_LEAVING"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("跟着她飞", "跟着海伦娜起飞。", HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT);
					
				} else {
					return new Response("跟着她飞", "你不会飞行，必须走着去奴隶巷。", null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENJOY = new DialogueNode("海伦娜的巢", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENJOY"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_END"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "离开海伦娜的巢。", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_LEAVING"));
						
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("跟着她飞", "跟着海伦娜起飞。", HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT);
					
				} else {
					if(Main.game.getPlayer().isAbleToFly()) {
						return new Response("跟着她飞", "你的同伴不能飞行，所以必须徒步前往奴隶巷……", null);
					} else {
						return new Response("跟着她飞", "你不会飞行，必须徒步前往奴隶巷……", null);
					}
				}
			}
			
			return null;
			
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT = new DialogueNode("海伦娜的巢", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("斯嘉丽的商店", "你到达了斯嘉丽的商店。", PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	// Meeting with Helena after completing her romance quest:
	
	public static final DialogueNode HELENAS_NEST = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开", "告别海伦娜并离开她的巢。", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						if(((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaSlutSeen, true);
						}
					}
				};
				
			} else if(index==1) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_NEST_TALK) {
					return new Response("对话", "你今天已经跟海伦娜谈过话了……", null);
				}
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaNestTalkedTo)) {
					return new Response("对话", "你今天已经跟海伦娜在她巢里谈过话了……", null);
				}
				return new Response("交谈", "询问海伦娜她怎么样。", HELENAS_NEST_TALK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaNestTalkedTo, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 2));
					}
				};
				
			} else if(index==2 && ((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaNestFucked)) {
					return new Response("公寓", "你今天已经跟海伦娜在巢里做过了，她没再有多余的时间……", null);
				}
				return new Response("公寓", "同意海伦娜的提议，让她“带着你去公寓逛逛”。<br/>[style.italicsSex(随后会与她做爱……)]", HELENAS_NEST_APARTMENT_BEDROOM) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaNestFucked, true);
						if(((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaSlutSeen, true);
						}
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_TALK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_NEST.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_NEST_APARTMENT_BEDROOM = new DialogueNode("海伦娜的卧室", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_HELENA_BEDROOM);
			Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaBedroomFromNest, true);
			((Helena)Main.game.getNpc(Helena.class)).applyLingerie();
		}
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_APARTMENT_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HelenaHotel.DATE_APARTMENT_BEDROOM.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MEETING_SCARLETT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_HELENA_QUEST_COMPLETE"));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT"));
			}
			Main.game.getNpc(Scarlett.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			if(index==0) {
				return new Response("离开",
						((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()
							?"告诉斯嘉丽你只是想来打个招呼，你现在得走了。"
							:"你不想再忍受斯嘉丽那张臭嘴，便告诉他你得走了。",
						HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_STEP_BACK_POST_QUEST"));
							
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_STEP_BACK"));
						}
					}
				};
			}
			
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
				if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
					responses.add(new Response("仆从",
							"斯嘉丽并没有被你吸引，不想让你做她的仆从……",
							null));
					
				} else if(Main.game.getDayMinutes()>19*60) {
					responses.add(new Response("仆从",
							"现在再当斯嘉丽的仆从有些太晚了。你应该另找一天[units.time(19)]之前来……",
							null));
					
				} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) // Performing oral
						|| (Main.game.getNpc(Scarlett.class).hasVagina() && Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) // Fucking Scarlett
						|| (Main.game.getNpc(Scarlett.class).hasVagina() && Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) // Scissoring
						|| (Main.game.isAnalContentEnabled() && Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) // Fucking Scarlett's ass
						|| (Main.game.getNpc(Scarlett.class).hasPenis() && Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) // Getting anally fucked
						|| (Main.game.getNpc(Scarlett.class).hasPenis() && Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) // Getting fucked
						) {
					responses.add(new Response("仆从",
							((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()
								?"告诉斯嘉丽你愿意今天做一天她的仆从，来回报她对你的好意。"
								:"做一天斯嘉丽的仆从，换一次上她的机会。",
							HELENAS_NEST_SCARLETTS_SERVANT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getPlayer().removeAllCompanions(true);
						}
					});
					
				} else {
					responses.add(new Response("仆从",
							"你需要能够使用自己的嘴巴或者下体，才能做斯嘉丽的仆从……",
							null));
				}
				
				if(((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.scarlettRelaxed)) {
						responses.add(new Response("放松",
								"斯嘉丽今天已经跟你一起放松过了。如果还想再跟她度过一段时光，得明天再来。",
								null));
					} else {
						responses.add(new Response("放松",
								"接受斯嘉丽的邀请，坐下来跟她一起放松一会儿。",
								HELENAS_NEST_SCARLETT_RELAX) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.scarlettRelaxed, true);
							}
						});
					}
				}
				
			} else {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) { // Scarlett has a penis (this should always be the case, as Scarlett only loses her vagina after Helena's romance quest is complete):
					if(Main.game.isAnalContentEnabled()) {
						if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
							responses.add(new Response("献出屁股", "斯嘉丽并没有被你吸引，所以不愿意跟你做爱。", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							responses.add(new Response("献出屁股", "斯嘉丽只对干你的屁股感兴趣，由于你无法使用该部位，她就没兴趣跟你做爱了。", null));
							
						} else {
							responses.add(new ResponseSex("献出屁股",
									"告诉斯嘉丽，如果她愿意，她可以随便干你屁股。",
									true, false,
									new SMAllFours(
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
										@Override
										public boolean isPublicSex() {
											return false;
										}
										@Override
										public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
											return getMainSexPreference(character, targetedCharacter);
										}
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.equals(Main.game.getNpc(Scarlett.class))) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
											}
											return character.getMainSexPreference(targetedCharacter);
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.equals(Main.game.getNpc(Scarlett.class));
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
											map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS));
											return map;
										}
									},
									null,
									null,
									AFTER_SCARLETT_SEX,
									UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "START_SCARLETT_SEX")) {
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, true, true));
								}
							});
						}
						
					} else { // If anal content is off, Scarlett will fuck the player's pussy or receive oral:
						if(Main.game.getPlayer().hasVagina()) {
							if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
								responses.add(new Response("献出小穴", "斯嘉丽并没有被你吸引，所以不愿意跟你做爱。", null));
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								responses.add(new Response("献出小穴", "斯嘉丽只对操你的小穴感兴趣，由于你无法使用该部位，她就没兴趣跟你做爱了。", null));
								
							} else {
								responses.add(new ResponseSex("献出小穴",
										"告诉斯嘉丽，如果她愿意，她可以随便操你的小穴。",
										true, false,
										new SMAllFours(
												Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), SexSlotAllFours.BEHIND)),
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
											@Override
											public boolean isPublicSex() {
												return false;
											}
											@Override
											public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
												return getMainSexPreference(character, targetedCharacter);
											}
											@Override
											public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
												if(character.equals(Main.game.getNpc(Scarlett.class))) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
												return character.getMainSexPreference(targetedCharacter);
											}
											@Override
											public boolean isCharacterStartNaked(GameCharacter character) {
												return character.equals(Main.game.getNpc(Scarlett.class));
											}
											@Override
											public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
												Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
												map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
												return map;
											}
										},
										null,
										null,
										AFTER_SCARLETT_SEX,
										UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "START_SCARLETT_SEX_VAGINA")) {
									public List<InitialSexActionInformation> getInitialSexActions() {
										return Util.newArrayListOfValues(
												new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, true, true));
									}
								});
							}
							
						} else {
							if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
								responses.add(new Response("提供口交", "斯嘉丽并没有被你吸引，所以不愿意跟你做爱。", null));
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
								responses.add(new Response("提供口角", "斯嘉丽只对口交感兴趣，由于你无法使用该部位，她就没兴趣跟你做爱了。", null));
								
							} else {
								responses.add(new ResponseSex("提供口交",
										"告诉斯嘉丽，如果她愿意，你就可以给她舔鸡巴。",
										true, false,
										new SMStanding(
												Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), SexSlotStanding.STANDING_DOMINANT)),
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))) {
											@Override
											public boolean isPublicSex() {
												return false;
											}
											@Override
											public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
												return getMainSexPreference(character, targetedCharacter);
											}
											@Override
											public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
												if(character.equals(Main.game.getNpc(Scarlett.class))) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
												}
												return character.getMainSexPreference(targetedCharacter);
											}
											@Override
											public boolean isCharacterStartNaked(GameCharacter character) {
												return character.equals(Main.game.getNpc(Scarlett.class));
											}
											@Override
											public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
												Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
												map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
												return map;
											}
										},
										null,
										null,
										AFTER_SCARLETT_SEX,
										UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "START_SCARLETT_SEX_ORAL")) {
									public List<InitialSexActionInformation> getInitialSexActions() {
										return Util.newArrayListOfValues(
												new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
									}
								});
							}
						}
					}
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
				responses.add(new Response("海伦娜", "告诉斯嘉丽，海伦娜又要她回到奴隶巷的商店。", HELENAS_NEST_MEETING_SCARLETT_TO_SHOP) {
					@Override
					public void effects() {
						Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
						Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaScarlettToldToReturn, true);
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
	
	public static final DialogueNode HELENAS_NEST_MEETING_SCARLETT_TO_SHOP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_TO_SHOP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("退开", "你在这的工作已经完成了，现在无事可做，只能准备返回海伦娜的商店里……", HELENAS_NEST_EXTERIOR);
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("跟着她飞", "跟着斯嘉丽起飞。", ScarlettsShop.ROMANCE_SHOP_CORE) {
						@Override
						public void effects() {
							// Move them both here to make sure they haven't gone due to time ticking over into night time when player arrives:
							Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
							Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
							Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
							Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_TO_SHOP_FLY_AFTER"));
						}
					};
					
				} else {
					if(Main.game.getPlayer().isAbleToFly()) {
						return new Response("跟着她飞", "你的同伴无法飞行，所以不能跟着斯嘉丽一起飞……", null);
					} else {
						return new Response("跟着她飞", "你无法飞行，所以不能跟着斯嘉丽一起飞……", null);
					}
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SCARLETT_SEX = new DialogueNode("结束", "斯嘉丽暂时做够了……", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getSexTypeCount(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))>0
					|| Main.sex.getSexTypeCount(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaOrifice.MOUTH))>0) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SEX_ORAL");
				
			} else if(Main.sex.getSexTypeCount(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SEX_VAGINA");
			}
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "按斯嘉丽说的做，离开巢穴……", HELENAS_NEST_EXTERIOR);
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETT_RELAX = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETT_RELAX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
					return new Response("公寓", "斯嘉丽并没有被你吸引，所以不愿意邀请你去她的房间待一会儿……", null);
				}
				return new Response("公寓",
						"接受斯嘉丽的邀请，去她的房间待一会儿。",
						HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().removeAllCompanions(true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETT_RELAX_APARTMENT"));
					}
				};
				
			} else if(index==2) {
				return new Response(
						!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())
							?"离开"
							:"回绝",
						!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())
							?"跟斯嘉丽道别，离开海伦娜的巢。"
							:"告诉斯嘉丽你今天还有其他事情，随后便离开了海伦娜的巢。",
						HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETT_RELAX_LEAVE"));
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			responses.add(new Response("背部按摩", "选择给斯嘉丽按摩背部……", HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE) {
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
				}
			});
			
			responses.add(new Response("梳理翅膀", "选择帮斯嘉丽梳理翅膀……", HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS) {
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
				}
			});
			
			if(Main.game.isFootContentEnabled()) {
				responses.add(new Response("鸟爪", "选择给斯嘉丽按摩那鸟一样的脚……", HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				});
			}

			if(index!=0 && index-1<responses.size()) {
				return responses.get(index-1);
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE"));
			Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(Main.game.getNpc(Scarlett.class).getClothingBlockingCoverableAreaAccess(CoverableArea.BACK, false), true, Main.game.getNpc(Scarlett.class));
		}
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("退开", "退到一旁，等待斯嘉丽的奖励。", HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE_HOLD_BACK"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("口交", "因为不能使用你的嘴，你不能给斯嘉丽口交……", null);
					}
					return new ResponseSex(
							"口交",
							"接受你的奖励，含住斯嘉丽的肉棒，让其他哈比继续服侍她……",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									"斯嘉丽的哈比随从继续服侍着她，你则开始舔她的肉棒……"),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE_BLOWJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					};
					
				} else {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("舔阴", "因为不能使用你的嘴，你不能给斯嘉丽口交……", null);
					}
					return new ResponseSex(
							"舔阴",
							"接受你的奖励，侍奉斯嘉丽的小穴，让其他哈比继续服侍她……",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									"斯嘉丽的哈比随从继续服侍着她，你则开始舔她的小穴……"),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("退开", "退到一旁，等待斯嘉丽的奖励。", HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS_HOLD_BACK"));
					}
				};
				
			} else if(index==2)  {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					return new ResponseSex(
							"手交",
							"接受你的奖励，给斯嘉丽手淫，让其他哈比继续服侍她……",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS))),
									"斯嘉丽的哈比随从继续服侍着她，你则开始撸动她的肉棒……"),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS_HANDJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), FingerPenis.COCK_MASTURBATING_START, false, true));
						}
					};
					
				} else {
					return new ResponseSex(
							"指交她",
							"接受你的奖励，用手指插入斯嘉丽的小穴，让其他哈比继续服侍她……",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))),
									"斯嘉丽的哈比随从继续服侍着她，你则开始扣她的小穴……"),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS_FINGERING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), FingerVagina.FINGERING_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("退开", "退到一旁，等待斯嘉丽的奖励。", HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE_HOLD_BACK"));
						Main.game.getNpc(Scarlett.class).setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_CLEAR));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("口交", "因为不能使用你的嘴，你不能给斯嘉丽口交……", null);
					}
					return new ResponseSex(
							"口交",
							"接受你的奖励，含住斯嘉丽的肉棒，让其他哈比继续服侍她……",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									"斯嘉丽的哈比随从继续服侍着她，你则开始舔她的肉棒……"),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE_BLOWJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					};
					
				} else {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("舔阴", "因为不能使用你的嘴，你不能给斯嘉丽口交……", null);
					}
					return new ResponseSex(
							"舔阴",
							"接受你的奖励，侍奉斯嘉丽的小穴，让其他哈比继续服侍她……",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									"斯嘉丽的哈比随从继续服侍着她，你则开始舔她的小穴……"),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					};
				}
				
			} else if(index==3) {
				if(!Main.game.getPlayer().hasPenis()) {
					return new Response("爪交", "你没有阴茎，所以不能让斯嘉丽给你爪交……", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("爪交", "你无法使用自己的阴茎，所以不能让斯嘉丽给你爪交……", null);
				}
				return new ResponseSex(
						"爪交",
						"接受你的奖励，让斯嘉丽用鸟爪给你撸管，其他哈比则继续服侍她……",
						true,
						false,
						getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS))),
								"斯嘉丽的哈比随从继续服侍着她，她则开始用鸟爪撸动你的肉棒……"),
						null,
						null,
						AFTER_SCARLETT_SERVANT_SEX,
						UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE_TALONJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisFeet.FOOT_JOB_DOUBLE_RECEIVING_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SCARLETT_SERVANT_SEX = new DialogueNode("结束", "斯嘉丽暂时做够了……", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SERVANT_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "继续前往海伦娜巢穴的入口。", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						applyScarlettFuckedEffects();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			randomChance = (float) Math.random();
			Main.game.getNpc(Scarlett.class).equipClothing();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(21*60) * 60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("反悔", "告诉斯嘉丽你不做了，然后准备离开……", HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_LEAVE);
				
			} else if(index==1) {
				return new Response("吻足",
						"亲吻斯嘉丽的脚。<br/>[style.italicsExcellent(如果你这么做，她就一定会选你做伴侣！)]",
						HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_KISS_FEET"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_CHOSEN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
					}
				};
				
			} else if(index==2) {
				return new Response("鞠躬",
						"在斯嘉丽面前鞠躬。<br/>[style.italicsGood(如果你这么做，她有可能选择你作为伴侣，但不是一定。)]",
						randomChance<0.75f
							?HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX
							:HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_NOT_CHOSEN) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						if(randomChance<0.75f) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_BOW_DOWN"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_CHOSEN"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
							
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_BOW_DOWN_NOT_CHOSEN"));
						}
					}
				};
				
			} else if(index==3) {
				return new Response("奉承",
						"尽全力奉承讨好斯嘉丽。<br/>[style.italicsMinorGood(如果你这么做，她或许会选择你作为伴侣，或许也不会。)]",
						randomChance<0.5f
							?HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX
							:HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_NOT_CHOSEN) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						if(randomChance<0.5f) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_FLATTER"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_CHOSEN"));
							
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_FLATTER_NOT_CHOSEN"));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_LEAVE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_LEAVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "离开海伦娜的巢。", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						applyScarlettFuckedEffects();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_NOT_CHOSEN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			applyScarlettFuckedEffects();
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
				return new Response("离开", "离开巢穴，希望下次决定去当斯嘉丽的仆人时能被选中……", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getNpc(Scarlett.class).returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Scarlett.class).returnToHome();
			Main.game.getPlayer().setLocation(Main.game.getNpc(Scarlett.class), false);
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
			
			responses.add(new ResponseSex(
					"没有偏好",
					"告诉斯嘉丽对于她操你的方式，你没有什么偏好，她可以自己决定，然后开始……",
					true,
					false,
					getScarlettSexManager(SexPosition.STANDING, SexSlotStanding.STANDING_DOMINANT, SexSlotStanding.STANDING_SUBMISSIVE,
							null,
							Util.newHashMapOfValues(),
							""),
					null,
					null,
					AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
					UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_NO_PREFERENCE")) {
			});
			
			if(Main.game.getNpc(Scarlett.class).hasPenis()) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					responses.add(new Response("口交", "你无法使用自己的嘴巴，不能舔斯嘉丽的肉棒……", null));
					
				} else {
					responses.add(new ResponseSex(
							"口交",
							"告诉斯嘉丽，你想舔她的鸡巴。",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									""),
							null,
							null,
							AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_BLOWJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					});
				}
				
				if(Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						responses.add(new Response("肛交", "你无法使用自己的屁股，所以不能让斯嘉丽给你肛交……", null));
						
					} else {
						responses.add(new ResponseSex(
								"肛交",
								"让斯嘉丽干你的屁股。",
								true,
								false,
								getScarlettSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS))),
										""),
								null,
								null,
								AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
								UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_ANAL")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
							}
						});
					}
				}
				
				if(Main.game.getPlayer().hasVagina()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						responses.add(new Response("挨操", "你无法使用自己的小穴，斯嘉丽不能插入你……", null));
						
					} else {
						responses.add(new ResponseSex(
								"挨操",
								"让斯嘉丽操你的逼。",
								true,
								false,
								getScarlettSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA))),
										""),
								null,
								null,
								AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
								UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_VAGINAL")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							}
						});
					}
				}
				
			} else { // Scarlett has a pussy:
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					responses.add(new Response("舔阴", "你无法使用自己的嘴巴，所以不能给斯嘉丽舔阴……", null));
					
				} else {
					responses.add(new ResponseSex(
							"舔阴",
							"告诉斯嘉丽你想品尝她的下面。",
							true,
							false,
							getScarlettSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LYING_DOWN,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									""),
							null,
							null,
							AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});
				}

				if(Main.game.getPlayer().hasVagina()) {
					if(Main.game.getPlayer().isTaur()) {
						responses.add(new Response("剪刀式", "你的下肢是兽态，所以找不到跟斯嘉丽剪刀式的合适姿势……", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("剪刀式", "你无法使用自己的小穴，不能让斯嘉丽跟你剪刀式……", null));
						
					} else {
						responses.add(new ResponseSex(
								"剪刀式",
								"询问斯嘉丽能不能跟她做剪刀式。",
								true,
								false,
								getScarlettSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.SCISSORING, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA))),
										""),
								null,
								null,
								AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
								UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SCISSORING")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), ClitClit.TRIBBING_START, false, true));
							}
						});
					}
				}
				
				if(Main.game.getPlayer().hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("操她", "你无法使用自己的阴茎，所以不能提出请求，要操斯嘉丽的小穴……", null));
						
					} else {
						responses.add(new ResponseSex(
								"操她",
								"告诉斯嘉丽你想插进她的小穴里。",
								true,
								false,
								getScarlettSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS))),
										""),
								null,
								null,
								AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
								UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_FUCK_HER")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisVagina.PENIS_FUCKING_START, false, true));
							}
						});
					}
					
					if(Main.game.isAnalContentEnabled()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("肛交", "你无法使用自己的阴茎，所以不能提出请求，操斯嘉丽的屁股……", null));
							
						} else {
							responses.add(new ResponseSex(
									Main.game.getNpc(Scarlett.class).isAssVirgin()
										?"夺取肛门贞操"
										:"肛交",
									Main.game.getNpc(Scarlett.class).isAssVirgin()
										?"询问斯嘉丽能不能将肛门贞操交给你，让你草她的屁股。"
										:"询问斯嘉丽你能不能操他的屁股。",
									true,
									false,
									getScarlettSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS))),
											""),
									null,
									null,
									AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
									UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_FUCK_HER_ASS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisAnus.PENIS_FUCKING_START, false, true));
								}
							});
						}
					}
				}
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX = new DialogueNode("结束", "斯嘉丽暂时做够了……", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.scarlettGoneHome, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开", "告诉斯嘉丽你现在得离开，然后乘电梯返回海伦娜的巢……", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
					}
				};
				
			} else if(index==1) {
				return new Response("过夜", "同意跟斯嘉丽过夜。", AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX_SLEEP_OVER) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getPlayer().setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX_SLEEP_OVER = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(60*8) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX_SLEEP_OVER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续走向海伦娜的巢的出口……", HELENAS_NEST_EXTERIOR);
			}
			return null;
		}
	};
	
}
