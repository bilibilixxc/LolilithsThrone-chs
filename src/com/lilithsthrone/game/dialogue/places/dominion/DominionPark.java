package com.lilithsthrone.game.dialogue.places.dominion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.dominion.SMDominionExpress;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.7.1
 * @version 0.4.7.1
 * @author Innoxia
 */
public class DominionPark {
	
	public static final DialogueNode PARK = new DialogueNode("公园", "。", false) {
		@Override
		public String getAuthor() {
			return "Kumiko";
		}
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "<p>"
						+ "御城区的这一区域被一个巨大的公园所占据，公园里枝繁叶茂，与城市的其他地方相比，这里的空气非常清新。"
						+ "公园由几块交替分布的草坪和林地组成，被一系列蜿蜒曲折的小径连接起来。"
						+ "公园一角有一个小湖，湖边有一小块野花地。"
						+ "在一个区域为没有准备野餐的人们设立了几个食品摊位。"
					+ "</p>"
					+ "<p>"
						+ "最值得一提的是公园最中心的莉莉丝巨大雕像。"
						+ "刻在白色大理石上的淫荡微笑几乎让你感觉它是在嘲笑你，你不禁觉得自己不想在这里久留。"
					+ "</p>"
					+ "<p>"
						+ "你现在没什么理由在这儿闲逛，但要是有其他人陪着你，这将是一个消磨下午时光的好地方；当然，是能忽略那座雕像的前提下……"
					+ "</p>";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("玫瑰园", "右手边有一个美丽的玫瑰园。走过去仔细看看吧。", PARK_ROSE_GARDEN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_hair_rose", false), false));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PARK_ROSE_GARDEN = new DialogueNode("公园", "。", false, true) {
		@Override
		public String getAuthor() {
			return "Innoxia";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return "<p>"
					+ "你发现自己的注意力被公园入口附近的一个小玫瑰园吸引住了。"
					+ "走过去一看，你发现有人在边界前放了一块小牌子，上面写着："
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ "<i>"
						+ "<b>威廉的玫瑰园</b><br/>"
						+ "请随意享用这些玫瑰！"
						+ "我希望你或你的伴侣能像我一样，从种植它们中获得快乐。<br/>"
						+ "——威廉"
					+ "</i>"
				+ "</p>"
				+ "<p>"
					+ "你环顾四周，但没发现附近有可能是这个“威廉”的人。"
					+ "你将注意力重新集中到他的玫瑰园，决定按照他的标志行事，向前一步，从最近的花丛中摘下一朵玫瑰花。"
				+ "</p>";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("玫瑰园", "你已经从花园里摘过玫瑰了。", null);
			} else {
				return null;
			}
		}
	};
	
	
	// Natalya encounter:
//	#IF(pc.getClothingInSlot(IS_NECK)!=null && pc.getClothingInSlot(IS_NECK).getClothingType().getId().equals('innoxia_neck_filly_choker'))
	
	public static final DialogueNode NATALYA_ENCOUNTER_START = new DialogueNode("突然半人马", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaParkEncounter, true);
			Main.game.getNpc(Natalya.class).setLocation(Main.game.getPlayer(), false);
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
				if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.PENIS, null);
				} else {
					Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.ANUS, null);
				}
				
			} else {
				Main.game.getNpc(Natalya.class).setPlayerKnowsName(false);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
				return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_FILLY_START");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_START");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
				Natalya natalya = (Natalya) Main.game.getNpc(Natalya.class);
				if(index==1) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new ResponseSex(
								"吻肛",
								UtilText.parse(natalya, "跪倒在娜塔莉亚身后，给她吻肛。"),
								true,
								false,
								// This sex manager should be suitable
								new SMDominionExpress(SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(natalya, SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
										Util.newHashMapOfValues(new Value<>(natalya, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
										Util.newHashMapOfValues(
												new Value<>(natalya, Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								AFTER_FILLY_SEX_ANAL,
								UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_START_FILLY_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), natalya, TongueAnus.ANILINGUS_START, false, true));
							}
						};
						
					} else {
						return new ResponseSex(
								"手交",
								UtilText.parse(natalya, "跪在娜塔莉亚身旁，给她手交。"),
								true,
								false,
								// This sex manager should be suitable
								new SMDominionExpress(SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(natalya, SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
										Util.newHashMapOfValues(new Value<>(natalya, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER))),
										Util.newHashMapOfValues(
												new Value<>(natalya, Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								AFTER_FILLY_SEX_HANDJOB,
								UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_START_FILLY_HANDJOB")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), natalya, FingerPenis.COCK_MASTURBATING_START, false, true));
							}
						};
					}
				}
				
			} else {
				if(index==2) {
					return new Response("离开",
							"你看不下去了，转身继续着你的旅程。"
								+ "<br/><i>你可能在其他位置再次遇到该角色……</i>",
								NATALYA_ENCOUNTER_END_EARLY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_START_END_EARLY"));
						}
					};
					
				} else if(index==1) {
					if(!Main.game.isAnalContentEnabled()) {
						return new Response("看着她",
								"你真的不想再看到这些了……"
										+ "<br/>[style.italicsMinorBad(这个角色的场景涉及肛门内容，只要你的“肛门内容”设置被关闭，这个场景就会被禁用。)]",
								null);
					}
					return new Response("一旁观看", "看着这个半人马把屁眼向着拖车的棱角上顶……", NATALYA_ENCOUNTER_CAUGHT);
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode NATALYA_ENCOUNTER_CAUGHT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_CAUGHT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("离开",
						"你并不觉得自己要给这只女性半人马道歉，只是转身继续着你的旅程。"
							+ "<br/><i>你可能会在别的地方再遇到她……</i>",
							NATALYA_ENCOUNTER_END_EARLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_CAUGHT_END_EARLY"));
					}
				};
				
			} else if(index==1) {
				return new Response("道歉", "告诉她你很抱歉。", NATALYA_ENCOUNTER_APOLOGY);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode NATALYA_ENCOUNTER_APOLOGY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_APOLOGY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("离开",
						"你已经给这只女性半人马道过歉了，并不想再次道歉，于是你转身继续你的旅程"
							+ "<br/><i>你可能会在别的地方再遇到她……</i>",
						NATALYA_ENCOUNTER_END_EARLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_APOLOGY_END_EARLY"));
					}
				};
				
			} else if(index==1) {
				return new Response("跟随", "按这个女性半人马说的做，跟她一起到灌木丛后面去。<br/>[style.italicsSex(你预感到可能会看到十分下流的场面……)]", NATALYA_ENCOUNTER_BUSHES) {
					@Override
					public void effects() {
						Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.PENIS, null);
						Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.ANUS, null);
						Main.game.getNpc(Natalya.class).setPlayerKnowsName(true);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode NATALYA_ENCOUNTER_BUSHES = new DialogueNode("娜塔莉亚的命令", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("离开", "转身离开，让娜塔莉亚自己解决问题，不提供帮助。", NATALYA_ENCOUNTER_END_EARLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_END_EARLY"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
				
			} else if(index==1) {
				return new Response("屈服",
						"听从那盛气凌人的[natalya.race]的命令，称呼她为“主人”，并且在她身后跪下，将假吊塞进她蓬松的尻穴中……",
						NATALYA_ENCOUNTER_BUSHES_SUBMIT) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.playerSubmittedToNatalyaInPark, true); //TODO text variation in interview & Helena quest encounter
						((Natalya)Main.game.getNpc(Natalya.class)).insertDildo();
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode NATALYA_ENCOUNTER_BUSHES_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("开始撸管", "听从主人娜塔莉亚的命令，开始撸动她粗壮的马屌。",
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))) {
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
							public boolean isAbleToSkipSexScene() {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								return false;
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
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
							public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
								Map<GameCharacter, List<SexAreaInterface>> map = new HashMap<>();
								map.put(Main.game.getPlayer(), Util.newArrayListOfValues(SexAreaOrifice.MOUTH, SexAreaOrifice.NIPPLE, SexAreaOrifice.BREAST));
								map.put(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(SexAreaOrifice.ANUS, SexAreaPenetration.FOOT));
								return map;
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								return OrgasmBehaviour.PULL_OUT;
							}
							@Override
							public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
								if(!character.isPlayer()) {
									if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerReceivedNatalyaFacial)) {
										return OrgasmCumTarget.FACE;
									} else {
										return OrgasmCumTarget.FLOOR;
									}
								}
								return super.getCharacterPullOutOrgasmCumTarget(character, target);
							}
						},
						null,
						null,
						NATALYA_ENCOUNTER_BUSHES_SUBMIT_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_SUBMIT_START_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), FingerPenis.COCK_MASTURBATING_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode NATALYA_ENCOUNTER_BUSHES_SUBMIT_POST_SEX = new DialogueNode("结束", "娜塔莉亚主人和你做完了。", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_SUBMIT_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("跟随", "跟随主人娜塔莉亚，看看半人马有没有完成工作。", NATALYA_ENCOUNTER_END) {
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_SUBMIT_POST_SEX_END"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode NATALYA_ENCOUNTER_END_EARLY = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Natalya.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return PARK.getContent();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PARK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode NATALYA_ENCOUNTER_END = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Natalya.class).returnToHome();
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.NATALYA_BUSINESS_CARD), false));
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
			if(index==1) {
				return new Response("继续", "既然主人娜塔莉亚已经离开了，你只好继续在公园中走动……", PARK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_END_PARK"));
					}
				};
			}
			return null;
		}
	};
	
	// Mule:

	public static final DialogueNode AFTER_FILLY_SEX_ANAL = new DialogueNode("结束", "娜塔莉亚主人和你做完了。", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "AFTER_FILLY_SEX_ANAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("跟随", "跟随主人娜塔莉亚，看看半人马有没有完成工作。", NATALYA_ENCOUNTER_FILLY_END) {
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_FILLY_END"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_FILLY_SEX_HANDJOB = new DialogueNode("结束", "娜塔莉亚主人和你做完了。", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "AFTER_FILLY_SEX_HANDJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_FILLY_SEX_ANAL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode NATALYA_ENCOUNTER_FILLY_END = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Natalya.class).returnToHome();
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
			if(index==1) {
				return new Response("继续", "既然主人娜塔莉亚已经离开了，你只好继续在公园中走动……", PARK);
			}
			return null;
		}
	};
}
