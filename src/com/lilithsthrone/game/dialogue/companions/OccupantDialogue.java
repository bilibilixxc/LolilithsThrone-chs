package com.lilithsthrone.game.dialogue.companions;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaSpa;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.time.DateAndTime;
import com.lilithsthrone.utils.time.SolarElevationAngle;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.2.10
 * @version 0.3.5.1
 * @author Innoxia
 */
public class OccupantDialogue {

	private static NPC occupant;
	private static GameCharacter characterForSex;
	private static NPC characterForSexSecondary;
	private static List<NPC> charactersPresent;
	private static boolean isApartment;
	private static boolean confirmKickOut;
	private static boolean initFromCharactersPresent;

	private static int sleepTimeInMinutes = 240;
	
	public static void initDialogue(NPC targetedOccupant, boolean isApartment, boolean initFromCharactersPresent) {
		if(Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL) {
			Main.game.saveDialogueNode();
		}
		
		if(isApartment) {
			CompanionManagement.initManagement(OCCUPANT_APARTMENT, 2, targetedOccupant);
		} else if(targetedOccupant.isAtWork() || targetedOccupant.isAtHome()) {
			CompanionManagement.initManagement(OCCUPANT_START, 2, targetedOccupant);
		}
		
		occupant = targetedOccupant;
		characterForSex = targetedOccupant;

		characterForSexSecondary = null;
		charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
		charactersPresent.removeIf((npc) -> !Main.game.getPlayer().getCompanions().contains(npc) && (!npc.isSlave() || !npc.getOwner().isPlayer()) && !Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()));
		if(charactersPresent.size()>1) {
			if(charactersPresent.contains(Main.game.getPlayer().getMainCompanion()) && !occupant().equals(Main.game.getPlayer().getMainCompanion())) {
				characterForSexSecondary = (NPC) Main.game.getPlayer().getMainCompanion();
				
			} else {
				characterForSexSecondary = charactersPresent.stream().filter((npc) -> !npc.equals(occupant())).findFirst().get();
			}
		}
		
		OccupantDialogue.isApartment = isApartment;
		Main.game.getDialogueFlags().setFlag("innoxia_friendly_occupant_apartment", isApartment);
		
		
		confirmKickOut = false;
		
		OccupantDialogue.initFromCharactersPresent = initFromCharactersPresent;
	}
	
	
	private static void exitDialogue() {
		Main.game.getDialogueFlags().setManagementCompanion(null);
	}
	
	private static DialogueNode getAfterSexDialogue() {
		if(initFromCharactersPresent) {
			return CharactersPresentDialogue.AFTER_SEX;
		} else if(isApartment) {
			return APARTMENT_AFTER_SEX;
		} else {
			return AFTER_SEX;
		}
	}
	
	private static NPC occupant() {
//		return Main.game.getActiveNPC();
		return occupant;
	}
	
	private static boolean hasJob() {
		return occupant().hasJob();
	}
	
	private static void applyReactionReset() {
		if(occupant().isVisiblyPregnant()){
			occupant().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(occupant(), true);
		}
		occupant().removeFlag(NPCFlagValue.occupantHasNewJob);
		confirmKickOut = false;
	}

	public static String getTextFilePath() {
		if(occupant().isRelatedTo(Main.game.getPlayer())) {
			return "characters/offspring/occupant";
		} else {
			return "misc/friendlyOccupantDialogue";
		}
	}

	private static String getThreesomeTextFilePath() {
		if(characterForSex.isRelatedTo(Main.game.getPlayer()) || (characterForSexSecondary!=null && characterForSexSecondary.isRelatedTo(Main.game.getPlayer()))) {
			return "characters/offspring/occupant";
		} else {
			return "misc/friendlyOccupantDialogue";
		}
	}
	
	private static boolean isCompanionSexPublic() {
		return !isApartment
				&& Main.game.getPlayer().getLocationPlace().isPopulated()
				&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_SEATING_AREA)
				&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS);
	}

	private static String getOccupantStartCoreContent() {
		StringBuilder sb = new StringBuilder();
		
		if(Main.game.getPlayer().getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START", occupant()));
		} else {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_WORKING", occupant()));
		}
		
		if(occupant().isVisiblyPregnant()) {
			if(!occupant().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_PREGNANCY_REVEAL", occupant()));
			} else {
				sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_STILL_PREGNANT", occupant()));
			}
		}
		
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			if(!Main.game.getPlayer().isCharacterReactedToPregnancy(occupant())) {
				sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_PLAYER_PREGNANCY", occupant()));
			} else {
				sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_CONTINUED_PLAYER_PREGNANCY", occupant()));
			}
		}
		
		if(occupant().hasFlag(NPCFlagValue.occupantHasNewJob)) {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_FINISH_WITH_NEW_JOB", occupant()));
			
		} else {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_FINISH", occupant()));
		}
		
		sb.append(occupant().getPlayerRelationStatusDescription());
		
		return sb.toString();
	}
	
	public static final DialogueNode OCCUPANT_START_NO_CONTENT = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_START = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(occupant().isAsleep()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_SLEEPING", occupant()));
				
			} else {
				if(Main.game.getPlayer().getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_ENTRY", occupant()));
				}
				UtilText.nodeContentSB.append(getOccupantStartCoreContent());
			}
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "互动";
			} else if(index == 1) {
				return UtilText.parse("[style.colourSex(做爱)]");
			} else if(index == 2) {
				return UtilText.parse("[style.colourCompanion(管理)]");
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(occupant().isAsleep()) {
				if(responseTab == 1) {
					if(index == 1) {
						if(!occupant().hasTrait(Perk.HEAVY_SLEEPER, true)) {
							return new Response("睡奸", UtilText.parse(characterForSex, "[npc.Name]并不是重度沉睡者，因此你不能对[npc.herHim]进行睡奸……"), null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("睡奸", UtilText.parse(characterForSex, "[npc.Name]没被你吸引，你不能强迫[npc.herHim]和你做爱……"), null);
							
						} else if(characterForSex.getSexConsensualCount(Main.game.getPlayer())==0) {
							return new Response("睡奸", UtilText.parse(characterForSex, "在睡奸[npc.name]之前，你必须要和[npc.herHim]至少做过一次爱……"), null);
							
						} else {
							return new ResponseSex("睡奸",
									UtilText.parse(characterForSex,
											isApartment
												?"[npc.sheIs]是一个重度沉睡者，你可以悄悄溜进[npc.namePos]的公寓，在不吵醒[npc.herHim]的情况下偷偷与[npc.herHim]发生性行为……"
												:"[npc.sheIs]是一个重度沉睡者，你可以悄悄溜进[npc.namePos]的房间，在不吵醒[npc.herHim]的情况下偷偷与[npc.herHim]发生性行为……"), 
									true, false,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											null,
											null,
											ResponseTag.PREFER_MISSIONARY) {
										@Override
										public boolean isPublicSex() {
											return false;
										}
										@Override
										public SexPace getStartingSexPaceModifier(GameCharacter character) {
											if(character.isPlayer()) {
												return SexPace.DOM_GENTLE;
											}
											return super.getStartingSexPaceModifier(character);
										}
										@Override
										public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
											Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
											map.put(ImmobilisationType.SLEEP, new HashMap<>());
											map.get(ImmobilisationType.SLEEP).put(Main.game.getPlayer(), Util.newHashSetOfValues(characterForSex));
											return map;
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					}
					
				} else {
					if(index == 1) {
						return new Response(UtilText.parse(occupant(), "叫醒[npc.herHim]"),
								UtilText.parse(occupant(),
										"把[npc.name]叫醒，这样你就可以和[npc.herHim]进行互动。"
										+ "<br/>[style.italicsMinorBad(要是把[npc.She]吵醒了，[npc.herHim]肯定会很生气的……)]"),
								OCCUPANT_START_NO_CONTENT) {
							@Override
							public void effects() {
								Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_ENTRY", occupant()));
								
								occupant().wakeUp();

								if (ModProps.hasValue(PropertyValue.wakeAffectionLoss)) {
									Main.game.appendToTextEndStringBuilder(UtilText.parse(occupant(), "<p style='text-align:center;'>[style.italicsMinorBad([npc.Name]不想被叫醒……)]</p>"));
									Main.game.appendToTextEndStringBuilder(occupant().incrementAffection(Main.game.getPlayer(), -1));
								}
								Main.game.appendToTextEndStringBuilder(getOccupantStartCoreContent());
							}
						};
					}
				}
				if (index == 0) {
					return new Response("离开",
							UtilText.parse(occupant(), "离开，让[npc.name]好好休息"),
							Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							applyReactionReset();
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
				}
				return null;

			} else {
				if(responseTab == 0) {
					if (index == 1) {
						if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkLife)) {
							return new Response("生活", UtilText.parse(occupant(), "询问[npc.name]过去的生活。"), OCCUPANT_TALK_LIFE) {
								@Override
								public void effects() {
									applyReactionReset();
									occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkLife);
									Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
						} else {
							return new Response("生活", UtilText.parse(occupant(), "你已经问过[npc.name]过去的生活了。"), null);
						}
						
					} else if (index == 2) {
						if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkJob)) {
							return new Response(hasJob()
										?"就业"
										:(occupant().getDesiredJobs().isEmpty()
											?"失业"
											:"找工作"),
									UtilText.parse(occupant(),
											hasJob()
											?"询问[npc.Name][npc.her]的工作。"
											:(occupant().getDesiredJobs().isEmpty()
												?"询问[npc.name]是否愿意继续待业。"
												:"询问[npc.name]工作找得怎么样。")),
									OCCUPANT_TALK_JOB) {
								@Override
								public void effects() {
									applyReactionReset();
									occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkJob);
									Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
							
						} else {
							return new Response("工作", UtilText.parse(occupant(),
									hasJob()?"你已经问过[npc.name]今天的工作了。":"你今天已经问过[npc.name]工作找得怎么样了。"), null);
						}
						
					} else if (index == 3) {
						if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkLilaya)) {
							return new Response("莉莱雅", UtilText.parse(occupant(), "询问[npc.name][npc.her]和莉莱雅与萝丝的互动。"), OCCUPANT_TALK_LILAYA) {
								@Override
								public void effects() {
									applyReactionReset();
									occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkLilaya);
									Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
						} else {
							return new Response("莉莱雅", UtilText.parse(occupant(), "你今天已经问过[npc.name][npc.her]和莉莱雅与萝丝的互动了。"), null);
						}
						
					} else if (index == 4) {
						if(Main.game.getPlayer().getSlavesOwned().size()==0) {
							return new Response("奴隶", "你没有奴隶，所以不能和[npc.name]讨论。", null);
							
						} else if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkSlaves)) {
							return new Response("奴隶", UtilText.parse(occupant(), "询问[npc.name][npc.her]和你的奴隶的互动。"), OCCUPANT_TALK_SLAVES) {
								@Override
								public void effects() {
									applyReactionReset();
									occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkSlaves);
									Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
						} else {
							return new Response("奴隶", UtilText.parse(occupant(), "你今天已经问过[npc.name][npc.her]和你的奴隶的互动了。"), null);
						}
						
					} else if (index == 5 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
						if(!Main.game.getPlayer().hasCompanion(occupant())) {
							if(!occupant().isCompanionAvailable(Main.game.getPlayer())) {
								return new Response("加入队伍",
										UtilText.parse(occupant(), "[npc.Name]不能加入你的队伍！"),
										null);
									
							} else if(Main.game.getPlayer().canHaveMoreCompanions()) {
								return new Response("加入队伍",
										UtilText.parse(occupant(), "问一问[npc.name]想不想陪你一阵。"),
										OCCUPANT_START){
									@Override
									public void effects() {
										applyReactionReset();
										Main.game.getPlayer().addCompanion(occupant());
									}
								};
							} else {
								return new Response("加入队伍",
										"你的队伍满员了！",
										null);
							}
							
						} else {
							return new Response("从队伍移除",
									UtilText.parse(occupant(), "命令[npc.name]离开你的队伍。"),
									OCCUPANT_START){
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getPlayer().removeCompanion(occupant());
								}
							};
						}
						
					} else if (index == 6) {
						if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantHugged)) {
							if(!occupant().getAffectionLevel(Main.game.getPlayer()).isGreaterThan(AffectionLevel.POSITIVE_TWO_LIKE)) {
								return new Response("拥抱",
										UtilText.parse(occupant(),
												"[npc.Name]不太喜欢你，不想给你一个拥抱。"
												+ "<br/>[style.italicsMinorBad(要求[npc.name]对你拥有至少'"+AffectionLevel.POSITIVE_THREE_CARING.getName()+"'的好感。)]"),
										null);
							}
							
							return new Response("拥抱", UtilText.parse(occupant(), "给[npc.name]一个大大的拥抱。"), OCCUPANT_HUG) {
								@Override
								public void effects() {
									applyReactionReset();
									occupant().NPCFlagValues.add(NPCFlagValue.occupantHugged);
									Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
						} else {
							return new Response("拥抱", UtilText.parse(occupant(), "你今天已经拥抱[npc.name]了。"), null);
						}
						
					} else if (index == 7) {
						if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantPet)) {
							if(!occupant().getAffectionLevel(Main.game.getPlayer()).isGreaterThan(AffectionLevel.POSITIVE_THREE_CARING)) {
								return new Response("摸头",
										UtilText.parse(occupant(),
												"[npc.Name]不太喜欢你，不想被你摸[npc.herHim]的头。"
												+ "<br/>[style.italicsMinorBad(要求[npc.name]对你拥有至少'"+AffectionLevel.POSITIVE_FOUR_LOVE.getName()+"'的好感。)]"),
										null);
							}
							return new Response("爱抚", UtilText.parse(occupant(), "充满爱意地摸摸[npc.name]。"), OCCUPANT_PETTINGS) {
								@Override
								public void effects() {
									applyReactionReset();
									occupant().NPCFlagValues.add(NPCFlagValue.occupantPet);
									Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
						} else {
							return new Response("爱抚", UtilText.parse(occupant(), "你今天已经爱抚过[npc.Name]了。"), null);
						}
						
					} else if(index==8 && Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)!=null) {
						if(!LilayaSpa.isGuestAbleToEquipSwimwear(occupant())) {
							return new Response("邀请去水疗中心",
									UtilText.parse(occupant(), "[npc.name]穿不了泳装，没法去水疗中心……"
										+ "<br/><i>解封[npc.her]的衣服应该会好些……</i>"),
									null);
						}
						return new Response("邀请去水疗中心",
								UtilText.parse(occupant(),
										"问一问[npc.name]想不想陪你去你在莉莱雅的宅邸中建好的水疗中心。"
										+ "<br/>[style.italicsBlueLight(你将一直穿着现在的衣服，所以如果你想换适宜的泳装就现在换上！)]"),
								LilayaSpa.SPA_GUEST_INVITE) {
							@Override
							public void effects() {
								applyReactionReset();
								LilayaSpa.initGuestAtSpa(occupant());
								Main.game.getDialogueFlags().setManagementCompanion(null);
							}
						};
						
					} else if (index == 10) {
						if(hasJob()) {
							return new Response("搬出去",
									UtilText.parse(occupant(),
											"告诉[npc.name]你觉得[npc.her]最好还是搬出去。<br/>"
											+ "[style.italics(你可以在下一个场景中保留或移除这个角色。)]"),
									OCCUPANT_MOVE_OUT) {
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getDialogueFlags().setManagementCompanion(null);
								}
							};
							
						} else {
							if(confirmKickOut) {
								return new Response("确认移除",
										UtilText.parse(occupant(),
												"告诉[npc.name]你想让[npc.herHim]离开。<br/>"
												+ "[style.italicsBad(从游戏中永久删除该角色。)]"),
										OCCUPANT_KICK_OUT) {
									@Override
									public Colour getHighlightColour() {
										return PresetColour.GENERIC_NPC_REMOVAL;
									}
									@Override
									public void effects() {
										applyReactionReset();
										Main.game.getDialogueFlags().setManagementCompanion(null);
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_KICK_OUT", occupant()));
										Main.game.getPlayer().removeFriendlyOccupant(occupant());
										Main.game.banishNPC(occupant());
									}
								};
								
							} else {
								return new ResponseEffectsOnly("踢出",
										UtilText.parse(occupant(),
												"告诉[npc.name]你想让[npc.herHim]离开。<br/>"
													+ "[style.italicsMinorBad(做出选择后，你需要再次点击以确认从游戏中永久删除该角色。)]")) {
									@Override
									public void effects() {
										confirmKickOut = true;
									}
								};
							}
						}
						
					} else if (index == 11) {
						return new Response(
								hasJob()
									?"提议跳槽"
									:"介绍工作",
								UtilText.parse(occupant(),
										hasJob()
											?"告诉[npc.name][npc.her]现在的工作"+UtilText.addDeterminer(occupant.getHistory().getName(occupant()))+"不适合[npc.herHim]，[npc.she]应该找一份新工作。"
											:"告诉[npc.name]你觉得[npc.she]适合去找什么工作。"),
								OCCUPANT_JOB_SUGGESTION) {
							@Override
							public void effects() {
								applyReactionReset();
							}
						};
						
					} else if(index==12 && occupant().hasJob()) {
						return new Response("[style.colourBad(辞职)]",
								UtilText.parse(occupant(), "让[npc.name]辞去[npc.her][npc.a_job]工作，并让[npc.herHim]在你想到其他主意前待业。"),
								OCCUPANT_JOB_QUIT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_JOB_QUIT", occupant()));
								occupant().setHistory(Occupation.NPC_UNEMPLOYED);
								occupant().clearDesiredJobs();
							}
						};
						
					} else if (index == 0) {
						return new Response("离开", UtilText.parse(occupant(), "告诉[npc.name]你会换个时间找[npc.herHim]。"), Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.setResponseTab(0);
								applyReactionReset();
								Main.game.getDialogueFlags().setManagementCompanion(null);
							}
						};
					}
					
					return null;
				
				} else if(responseTab == 1) {
					if (index == 1) {
						if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("做爱", UtilText.parse(characterForSex, "[npc.Name]没被你吸引……"), null);
							
						} else {
							return new ResponseSex("做爱",
									UtilText.parse(characterForSex, "和[npc.name]做爱。"), 
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											Main.game.getPlayer().getCompanions(),
											null) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
								}
							};
						}
						
					} else if (index == 2) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("“串肉串”(在前)", "得有第三个人在场，你才能开始“串肉串”……", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("“串肉串”(在前)", "你不能以自己为目标应用这个动作！", null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("“串肉串”(在前)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.name]和[npc2.name]都对你不感兴趣……"), null);
							} else {
								return new Response("“串肉串”(在前)", UtilText.parse(characterForSex, "[npc.Name]没被你吸引，不愿意参加三人行……"), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("“串肉串”(在前)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name]没被你吸引，[npc.she]和[npc2.name]都不想和你三人行……"), null);
							
						} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
							return new Response("“串肉串”(在前)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name]没被[npc2.name]吸引，他们不想彼此接触，所以都不会参加三人行……"),
									null);
	
						} else if(!characterForSex.isAttractedTo(characterForSexSecondary)) {
							return new Response("“串肉串”(在前)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc2.name]没被[npc2.name]吸引，他们不想彼此接触，所以都不会参加三人行……"),
									null);
							
						} else {
							return new ResponseSex(
									"“串肉串”(在前)",
									UtilText.parse(characterForSex, characterForSexSecondary, "移动到[npc.name]前面，[npc2.name]玩弄[npc.her]后面的时候，你可以使用[npc.her]的嘴。"),
									null, null, null, null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(characterForSexSecondary, Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											null,
											null,
											ResponseTag.PREFER_DOGGY) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROAST_FRONT_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					
					} else if (index == 3) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("“串肉串”(在后)", "得有第三个人在场，你才能开始“串肉串”……", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("“串肉串”(在后)", "你不能以自己为目标应用这个动作！", null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("“串肉串”(在后)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.name]和[npc2.name]都没被你吸引……"), null);
							} else {
								return new Response("“串肉串”(在后)", UtilText.parse(characterForSex, "[npc.Name]没被你吸引，不愿意参加三人行……"), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("“串肉串”(在后)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name]没被你吸引，[npc.she]和[npc2.name]都不想参加三人行……"), null);
							
						} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
							return new Response("“串肉串”(在前)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name]没被[npc2.name]吸引，他们不想彼此接触，所以都不会参加三人行……"),
									null);
	
						} else if(!characterForSex.isAttractedTo(characterForSexSecondary)) {
							return new Response("“串肉串”(在前)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc2.name]没被[npc2.name]吸引，他们不想彼此接触，所以都不会参加三人行……"),
									null);
							
						} else {
							return new ResponseSex(
									"“串肉串”(在后)",
									UtilText.parse(characterForSex, characterForSexSecondary, "移动到[npc.name]背后，[npc2.name]使用[npc.her]的嘴的时候，你可以玩弄[npc.her]的后面。"),
									null, null, null, null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer(), characterForSexSecondary),
											Util.newArrayListOfValues(characterForSex),
											null,
											null,
											ResponseTag.PREFER_DOGGY) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROAST_BEHIND_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					
					} else if (index == 4) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("并排后入(作为支配方)", "得有第三个人在场，你才能开始并排后入……", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("并排后入(作为支配方)", "你不能以自己为目标应用这个动作！", null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("并排后入(在上)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.name]和[npc2.name]都没被你吸引……"), null);
							} else {
								return new Response("并排后入(在上)", UtilText.parse(characterForSex, "[npc.Name]没被你吸引，不想参加三人行……"), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("并排后入(在上)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name]没被你吸引，[npc.she]和[npc2.name]都不想跟你三人行……"), null);
							
						} else {
							return new ResponseSex("并排后入(在上)",
									UtilText.parse(characterForSex, characterForSexSecondary, "让[npc.name]和[npc2.name]四体投地，跪在[npc.Name]身后，准备并排后入他们。"),
									null, null, null, null, null, null,
									true, false,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex, characterForSexSecondary),
											null,
											null,
											ResponseTag.PREFER_DOGGY) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SIDE_BY_SIDE_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					
					} else if (index == 6) {
						if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("服从型性爱",
									UtilText.parse(characterForSex, "[npc.Name]不是很想和你做爱，因此你需要做支配方……"),
									null);
							
						} else {
							return new ResponseSex("服从性爱",
									UtilText.parse(characterForSex, "和[npc.name]来一场服从型性爱。"), 
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(characterForSex),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Main.game.getPlayer().getCompanions()) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_AS_SUB_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
						}
						
					} else if (index == 7) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("接受“串肉串”(在前)", "得有第三个人在场，你才能接受“串肉串”……", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("“串肉串”(在前)", "你不能以自己为目标应用这个动作！", null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("接受“串肉串”(在前)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.name]和[npc2.name]都对你不感兴趣……"), null);
							} else {
								return new Response("接受“串肉串”(在前)", UtilText.parse(characterForSex, "[npc.Name]没被你吸引，不愿意参加三人行……"), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("接受“串肉串”(在前)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name]没被你吸引，[npc.she]和[npc2.name]都不想和你三人行……"), null);
							
						} else {
							return new ResponseSex(
									"接受“串肉串”(在前)",
									UtilText.parse(characterForSex, characterForSexSecondary, "你四肢着地，面朝[npc.name]，[npc2.name]玩弄你的后面的时候，[npc.she]就可以使用你的嘴了。"),
									null, null, null, null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(characterForSexSecondary, characterForSex),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											null,
											ResponseTag.PREFER_DOGGY) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROASTED_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					
					} else if (index == 8) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("“串肉串”(在后)", "得有第三个人在场，你才能开始“串肉串”……", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("接受“串肉串”(在后)", "你不能以自己为目标应用这个动作！", null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("接受“串肉串”(在后)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.name]和[npc2.name]都没被你吸引……"), null);
							} else {
								return new Response("接受“串肉串”(在后)", UtilText.parse(characterForSex, "[npc.Name]没被你吸引，不愿意和你三人行……"), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("接受“串肉串”(在后)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name]没被你吸引，[npc.she]和[npc2.name]都不愿意和你三人行……"), null);
							
						} else {
							return new ResponseSex(
									"接受“串肉串”(在后)",
									UtilText.parse(characterForSex, characterForSexSecondary, "你四肢跪地，将身后展现给[npc.name]，这样在[npc.she]干你的时候[npc2.name]可以同时使用你的嘴巴。"),
									null, null, null, null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(characterForSex, characterForSexSecondary),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											null,
											ResponseTag.PREFER_DOGGY) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROASTED_START", characterForSexSecondary, characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					
					} else if (index == 9) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("并排后入(在下)", UtilText.parse(characterForSex, "你需要第三个人在场，才能被他们或者[npc.name]操……"), null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("并排后入(作为服从方)", "你不能以自己为目标应用这个动作！", null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("并排后入(在下)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.name]和[npc2.name]都没被你吸引……"), null);
							} else {
								return new Response("并排后入(在下)", UtilText.parse(characterForSex, "[npc.Name]没被你吸引，不想参加三人行……"), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("并排后入(在下)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name]没被你吸引，[npc.she]和[npc2.name]都不愿意三人行……"), null);
							
						} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
							return new Response("并排后入(作为服从方)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name]没被[npc2.name]吸引，他们不想彼此接触，所以都不会参加三人行……"),
									null);
	
						} else if(!characterForSex.isAttractedTo(characterForSexSecondary)) {
							return new Response("并排后入(作为服从方)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc2.name]没被[npc2.name]吸引，他们不想彼此接触，所以都不会参加三人行……"),
									null);
							
						} else {
							return new ResponseSex("并排后入(在下)",
									UtilText.parse(characterForSex, characterForSexSecondary, "在[npc2.name]身边四肢跪地，这样[npc.name]就可以跪在你们身后，跟挨在一起的你们两个做爱了。"),
									null, null, null, null, null, null,
									true, false,
									new SMGeneric(
											Util.newArrayListOfValues(characterForSex),
											Util.newArrayListOfValues(Main.game.getPlayer(), characterForSexSecondary),
											null,
											null,
											ResponseTag.PREFER_DOGGY) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SIDE_BY_SIDE_AS_SUB_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					
					} else if(index==11) {
						if(characterForSexSecondary!=null) {
							return new ResponseEffectsOnly(
									UtilText.parse(characterForSex, "目标：<b style='color:"+characterForSex.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
									"循环切换选择群交角色。") {
								@Override
								public void effects() {
									if(charactersPresent.size()>1) {
										for(int i=0; i<charactersPresent.size();i++) {
											if(charactersPresent.get(i).equals(characterForSex)) {
												if(i==charactersPresent.size()-1) {
													characterForSex = charactersPresent.get(0);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSexSecondary = charactersPresent.get(1);
													}
												} else {
													characterForSex = charactersPresent.get(i+1);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSexSecondary = charactersPresent.get((i+2)<charactersPresent.size()?(i+2):0);
													}
													break;
												}
											}
										}
									}
									Main.game.updateResponses();
								}
							};
							
						} else {
							return new Response(
									UtilText.parse(characterForSex, "目标：<b>[npc.Name]</b>"),
									"循环切换选择群交目标角色。<br/>[style.italicsBad(若要解锁该动作，需要带着一名同伴！)]",
									null); 
						}
						
					} else if(index==12) {
						if(characterForSexSecondary!=null) {
							return new ResponseEffectsOnly(
									UtilText.parse(characterForSexSecondary, "次目标：<b style='color:"+characterForSexSecondary.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
									"循环切换选择群交次目标角色。") {
								@Override
								public void effects() {
									if(charactersPresent.size()>1) {
										for(int i=0; i<charactersPresent.size();i++) {
											if(charactersPresent.get(i).equals(characterForSexSecondary)) {
												if(i==charactersPresent.size()-1) {
													characterForSexSecondary = charactersPresent.get(0);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSex = charactersPresent.get(1);
													}
												} else {
													characterForSexSecondary = charactersPresent.get(i+1);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSex = charactersPresent.get((i+2)<charactersPresent.size()?(i+2):0);
													}
												}
												break;
											}
										}
									}
									Main.game.updateResponses();
								}
							};
	
						} else {
							return new Response(
									UtilText.parse(characterForSex, "次目标：<b>[npc.Name]</b>"),
									"循环切换选择群交次目标角色。<br/>[style.italicsBad(若要解锁该动作，需要带着一名同伴！)]",
									null);
						}
	
					} else if (index == 0) {
						return new Response("离开", UtilText.parse(occupant(), "告诉[npc.name]你会换个时间找[npc.herHim]。"), Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.setResponseTab(0);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "LEAVING", occupant()));
								applyReactionReset();
								Main.game.getDialogueFlags().setManagementCompanion(null);
							}
						};
						
					} else  {
						return null;
					}
					
					
				} else if(responseTab == 2) {
					return CompanionManagement.getManagementResponses(index);
					
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode OCCUPANT_TALK_LIFE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"与[npc.Name]交谈");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			//TODO talk about life, family, friends, stories
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_LIFE", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_TALK_JOB = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"与[npc.Name]交谈");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(hasJob()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_JOB", occupant()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_JOB_HUNTING", occupant()));
			}
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_TALK_LILAYA = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"与[npc.Name]交谈");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_LILAYA", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_TALK_SLAVES = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"与[npc.Name]交谈");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			String id = Util.randomItemFrom(Main.game.getPlayer().getSlavesOwned());
			try {
				NPC slave = (NPC) Main.game.getNPCById(id);
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_SLAVES", occupant(), slave));

			} catch (Exception e) {
				Util.logGetNpcByIdError("OCCUPANT_TALK_SLAVES.getContent()", id);
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_SLAVES_NULL_SLAVE", occupant()));
			}
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OCCUPANT_HUG = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"拥抱[npc.Name]");
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_HUG", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}
		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OCCUPANT_PETTINGS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(), "爱抚[npc.Name]");
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_PETTINGS", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}
		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OCCUPANT_JOB_SUGGESTION = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"与[npc.Name]交谈");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(hasJob()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_JOB_SUGGESTION_CHANGE", occupant()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_JOB_SUGGESTION", occupant()));
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_JOB_SUGGESTION_MECHANICS", occupant()));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Occupation> availableOccuaptions = new ArrayList<>();
			for(Occupation occ : Occupation.values()) {
				if(!occ.isAvailableToPlayer()
						&& occ.isAvailable(occupant())
						&& occ!=Occupation.NPC_UNEMPLOYED
						&& !occ.isLowlife()) {
					availableOccuaptions.add(occ);
				}
			}
			
			if(index==0) {
				return new Response("返回",
						UtilText.parse(occupant(), "还是不告诉[npc.name]你觉得[npc.she]应该找份什么工作了。"),
						isApartment
							?OCCUPANT_APARTMENT
							:OCCUPANT_START);
				
			} else if(index==1) {
				return new Response("[style.colourGood(全选)]",
						UtilText.parse(occupant(), "选择所有类型的工作，让[npc.name]都可以去找。"),
						OCCUPANT_JOB_SUGGESTION) {
					@Override
					public void effects() {
						for(Occupation occ : availableOccuaptions) {
							occupant().addDesiredJob(occ);
						}
					}
				};
				
			} else if(index==2) {
				return new Response("[style.colourBad(不做选择)]",
						occupant().hasJob()
							?UtilText.parse(occupant(), "不为[npc.name]选择要找的工作，这会让[npc.herHim]仍然做一个[npc.a_job]。")
							:UtilText.parse(occupant(), "不为[npc.name]选择要找的工作，这会让[npc.herHim]继续失业。"),
							OCCUPANT_JOB_SUGGESTION) {
					@Override
					public void effects() {
						occupant().clearDesiredJobs();
					}
				};
				
			} else if(index-3<availableOccuaptions.size()) {
				Occupation job = availableOccuaptions.get(index-3);
				String jobName = job.getName(occupant());
				if(occupant().getHistory().equals(job)) {
					return new Response(Util.capitaliseSentence(jobName),
							UtilText.parse(occupant(), "[npc.Name]已经是个"+jobName+"了。"),
							null);
					
				} else if(occupant().getDesiredJobs().contains(job)) {
					return new Response(Util.capitaliseSentence(jobName),
							UtilText.parse(occupant(), "告诉[npc.name]不要再找"+jobName+"的工作了。"),
							OCCUPANT_JOB_SUGGESTION) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_MINOR_GOOD;
						}
						@Override
						public void effects() {
							occupant().removeDesiredJob(job);
						}
					};
					
				} else {
					return new Response(Util.capitaliseSentence(jobName),
							UtilText.parse(occupant(), "告诉[npc.name][npc.she]应该找个"+jobName+"的工作。"),
							OCCUPANT_JOB_SUGGESTION) {
						@Override
						public void effects() {
							occupant().addDesiredJob(job);
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode OCCUPANT_JOB_QUIT = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"与[npc.Name]交谈");
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("结束", "", true) {
		
		@Override
		public String getDescription(){
			return "躺在[npc.namePos]的沙发上，缓一口气。";
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().size()>2) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_THREESOME", occupant(), characterForSexSecondary);
				
			} else if(Main.sex.getNumberOfOrgasms(occupant()) >= occupant().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX", occupant());
				
			} else {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_NO_ORGASM", occupant());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开",
						UtilText.parse(occupant(),
								occupant().isAsleep()
									?"让[npc.name]继续睡觉，离开[npc.her]的"+(isApartment?"公寓":"房间")+"."
									:"给[npc.name]一些时间休息"),
						Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "LEAVE_AFTER_SEX", occupant()));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OCCUPANT_KICK_OUT = new DialogueNode("逐客", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_MOVE_OUT = new DialogueNode("搬出", "", true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_MOVE_OUT", occupant());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("拜访公寓", "告诉[npc.name]你想看看[npc.her]的新公寓，并且跟着[npc.herHim]前往。<br/>"
						+ "[style.italicsGood(保存该角色，将其移动至御城区任意街道或主干道区块。)]",
						OCCUPANT_MOVE_OUT_APARTMENT) {
					@Override
					public void effects() {
						occupant().setRandomUnoccupiedLocation(WorldType.DOMINION, true, PlaceType.DOMINION_STREET, PlaceType.DOMINION_STREET_HARPY_NESTS, PlaceType.DOMINION_BOULEVARD);
						occupant().setHomeLocation();
						OccupantDialogue.isApartment = true;
						Main.game.getDialogueFlags().setManagementCompanion(occupant());
						Main.game.getPlayer().setLocation(occupant().getWorldLocation(), occupant().getLocation(), false);
					}
				};
				
			} else if(index==10) {
				if(confirmKickOut) {
					return new Response("确认移除", "告诉[npc.name][npc.she]应当继续自己的新生活。<br/>"
							+ "[style.italicsBad(从游戏中永久删除该角色。)]",
							OCCUPANT_KICK_OUT) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_MOVE_OUT_REMOVE_CHARACTER", occupant()));
							Main.game.getPlayer().removeFriendlyOccupant(occupant());
							Main.game.banishNPC(occupant());
							confirmKickOut = false;
						}
					};
					
				} else {
					return new ResponseEffectsOnly("移除角色", "告诉[npc.name][npc.she]应当继续自己的新生活。<br/>"
							+ "[style.italicsMinorBad(选择该行动后，你需要再次确认以将该角色永久移出游戏。(不可撤销！))]") {
						@Override
						public void effects() {
							confirmKickOut = true;
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OCCUPANT_MOVE_OUT_APARTMENT = new DialogueNode("搬出", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(occupant(), "[npc.NamePos]的公寓");
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_MOVE_OUT_APARTMENT", occupant());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};

	private static String getOccupantApartmentCoreContent() {
		StringBuilder sb = new StringBuilder();
		
		if(Main.game.getPlayer().getCompanions().contains(occupant())) {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_AS_COMPANION", occupant()));
			
		} else if(occupant().isAtHome()) {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START", occupant()));
			
			if(occupant().isAsleep()) {
				sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_SLEEPING", occupant()));
				
			} else {
				if(occupant().isVisiblyPregnant() && occupant().isCharacterPossiblyFather(Main.game.getPlayer().getId())) {
					if(!occupant().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
						sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_PREGNANCY_REVEAL", occupant()));
					} else {
						sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_STILL_PREGNANT", occupant()));
					}
				}
				
				if(Main.game.getPlayer().isVisiblyPregnant() && Main.game.getPlayer().isCharacterPossiblyFather(occupant().getId())) {
					if(!Main.game.getPlayer().isCharacterReactedToPregnancy(occupant())) {
						sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_PLAYER_PREGNANCY", occupant()));
					} else {
						sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_CONTINUED_PLAYER_PREGNANCY", occupant()));
					}
				}
				
				sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_FINISH", occupant()));
				
				sb.append(occupant().getPlayerRelationStatusDescription());
			}
			
		} else {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_NOT_AT_HOME", occupant()));
		}
		
		return sb.toString();
	}

	public static final DialogueNode OCCUPANT_APARTMENT_NO_CONTENT = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return UtilText.parse(occupant(), "[npc.NamePos]的公寓");
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT = new DialogueNode("搬出", "", true) {//TODO
		@Override
		public String getLabel() {
			return UtilText.parse(occupant(), "[npc.NamePos]的公寓");
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getOccupantApartmentCoreContent());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(occupant().isAtHome()) {
				if(index == 0) {
					return "互动";
				} else if(index == 1) {
					return UtilText.parse("[style.colourSex(做爱)]");
				} else if(index == 2) {
					return UtilText.parse("[style.colourCompanion(管理)]");
				}
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!occupant().isAtHome()) {
				if(index==1) {
					return new Response("离开",
							UtilText.parse(occupant(), "[npc.name]现在不在家，你无事可做，只能返回御城区。"),
							Main.game.getDefaultDialogue(false));
				}
				return null;
			}
			
			if(occupant().isAsleep()) {
				if(index == 1) {
					return new Response(UtilText.parse(occupant(), "叫醒[npc.herHim]"),
							UtilText.parse(occupant(),
									"把[npc.name]叫醒，这样你就可以和[npc.herHim]进行互动。"
									+ "<br/>[style.italicsMinorBad(要是把[npc.She]吵醒了，[npc.herHim]肯定会很生气的……)]"),
							OCCUPANT_APARTMENT_NO_CONTENT) {
						@Override
						public void effects() {
							Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_ENTRY", occupant()));
							
							occupant().wakeUp();
							if (ModProps.hasValue(PropertyValue.wakeAffectionLoss)) {
								Main.game.appendToTextEndStringBuilder(UtilText.parse(occupant(), "<p style='text-align:center;'>[style.italicsMinorBad([npc.Name]不想被叫醒……)]</p>"));
								Main.game.appendToTextEndStringBuilder(occupant().incrementAffection(Main.game.getPlayer(), -1));
							}
							Main.game.appendToTextEndStringBuilder(getOccupantApartmentCoreContent());
						}
					};
					
				} else if (index == 0) {
					return new Response("离开",
							UtilText.parse(occupant(), "离开，让[npc.name]好好休息"),
							Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							applyReactionReset();
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
				}
				return null;
			}
			
			if(responseTab == 0) {
				if (index == 1) {
					if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkLife)) {
						return new Response("生活", UtilText.parse(occupant(), "询问[npc.name]过去的生活。"), OCCUPANT_APARTMENT_TALK_LIFE) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkLife);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					} else {
						return new Response("生活", UtilText.parse(occupant(), "你已经问过[npc.name]过去的生活了。"), null);
					}
					
				} else if (index == 2) {
					if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkJob)) {
						return new Response("工作",
								UtilText.parse(occupant(), "问问[npc.name][npc.her]的工作。"),
								OCCUPANT_APARTMENT_TALK_JOB) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkJob);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
						
					} else {
						return new Response("工作", UtilText.parse(occupant(), "你今天已经问过[npc.Name][npc.her]的工作了。"), null);
					}
					
				}
				else if (index == 3) {
					return new Response("休息",
							UtilText.parse(occupant(), "询问[npc.name]能不能在[npc.her]的沙发上小睡四个小时。")
							+ "不仅会恢复"+Attribute.HEALTH_MAXIMUM.getName()+"与"+Attribute.MANA_MAXIMUM.getName()+", 还能获得“充分休息”效果。",
							OCCUPANT_APARTMENT_SLEEP_OVER){
						@Override
						public void effects() {
							sleepTimeInMinutes = 240;
							RoomPlayer.applySleep(sleepTimeInMinutes);
						}
					};

				} else if (index == 4) {
					int timeUntilChange = Main.game.getMinutesUntilNextMorningOrEvening() + 5; // Add 5 minutes so that if the days are drawing in, you don't get stuck in a loop of always sleeping to sunset/sunrise
					LocalDateTime[] sunriseSunset = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
					return new Response("休息到" + (Main.game.isDayTime() ? "日落" : "日出"),
							UtilText.parse(occupant(), "询问[npc.name]能不能在[npc.her]的沙发上睡")
								+ (timeUntilChange >= 60 ?timeUntilChange / 60 + "小时" : "")
								+ (timeUntilChange % 60 != 0 ? timeUntilChange % 60 + "分钟" : "")
								+ (Main.game.isDayTime()
										? "，即日落后五分钟("+Units.time(sunriseSunset[1].plusMinutes(5))+")。"
										: "，即日出后五分钟("+Units.time(sunriseSunset[0].plusMinutes(5))+")。")
								+ "不仅会恢复"+Attribute.HEALTH_MAXIMUM.getName()+"与"+Attribute.MANA_MAXIMUM.getName()+", 还能获得“充分休息”效果。",
								OCCUPANT_APARTMENT_SLEEP_OVER){
						@Override
						public void effects() {
							sleepTimeInMinutes = timeUntilChange;
							RoomPlayer.applySleep(sleepTimeInMinutes);
						}
					};

				} else if (index == 5 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
					if(!Main.game.getPlayer().hasCompanion(occupant())) {
						
						if(!occupant().isCompanionAvailable(Main.game.getPlayer())) {
							return new Response("加入队伍",
									UtilText.parse(occupant(), "[npc.Name]不能加入你的队伍！"),
									null);
								
						} else if(Main.game.getPlayer().canHaveMoreCompanions()) {
							return new Response("加入队伍",
									UtilText.parse(occupant(), "问一问[npc.name]想不想陪你一阵。"),
									OCCUPANT_APARTMENT){
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getPlayer().addCompanion(occupant());
								}
							};
							
						} else {
							return new Response("加入队伍",
									"你的队伍满员了！",
									null);
						}
						
					} else {
						return new Response("从队伍移除",
								"命令[npc.name]从你的队伍里离开。",
								OCCUPANT_APARTMENT){
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getPlayer().removeCompanion(occupant());
							}
						};
					}
					
				} else if (index == 6) {
					return new Response(
							hasJob()
								?"提议跳槽"
								:"介绍工作",
							UtilText.parse(occupant(),
									hasJob()
										?"告诉[npc.name][npc.her]现在的工作"+UtilText.addDeterminer(occupant.getHistory().getName(occupant()))+"不适合[npc.herHim]，[npc.she]应该找一份新工作。"
										:"告诉[npc.name]你觉得[npc.she]适合去找什么工作。"),
							OCCUPANT_JOB_SUGGESTION) {
						@Override
						public void effects() {
							applyReactionReset();
						}
					};

				} else if (index == 8) {
					return new Response("调闹钟", "调个手机闹钟，这样你可以在特定的时间醒来。", RoomPlayer.ROOM_SET_ALARM) {
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
						}
					};

				} else if (index == 9) {
					long alarmTime = Main.game.getDialogueFlags().getSavedLong("player_phone_alarm");
					if(alarmTime >= 0) {
						String alarmTimeStr = Main.game.getDisplayTime(LocalTime.ofSecondOfDay(alarmTime*60));
						int timeUntilAlarm = Main.game.getMinutesUntilTimeInMinutes((int)alarmTime-1)+1; // -1+1 is so we get 1440 instead of 0
						return new Response("睡到闹钟响(" + alarmTimeStr + ")",
								"询问[npc.name]能不能在[npc.her]的沙发上睡" + (timeUntilAlarm >= 60 ? timeUntilAlarm / 60 + "小时，" : "")
										+ (timeUntilAlarm % 60 != 0 ? timeUntilAlarm % 60 + " 分，" : "")
										+ "睡到闹钟响起。除了恢复" + Attribute.HEALTH_MAXIMUM.getName() + "与" + Attribute.MANA_MAXIMUM.getName() + "，还能获得“充分休息”效果。",
								OCCUPANT_APARTMENT_SLEEP_OVER) {
							@Override
							public void effects() {
								sleepTimeInMinutes = timeUntilAlarm;
								RoomPlayer.applySleep(sleepTimeInMinutes);
							}
						};
					} else {
						return new Response("睡到闹钟响(未设定)", "<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>你还没有设定闹钟！</span>", null);
					}
					
				} else if (index == 10) {
					if(confirmKickOut) {
						return new Response("确认移除", UtilText.parse(occupant(), "告诉[npc.name]你得继续自己的路，再也不会见到[npc.herHim]了。<br/>"
								+ "[style.italicsBad([npc.name]将会从游戏中永久删除！)]"),
								OCCUPANT_APARTMENT_REMOVE) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_NPC_REMOVAL;
							}
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_REMOVE", occupant()));
								Main.game.getPlayer().removeFriendlyOccupant(occupant());
								Main.game.banishNPC(occupant());
								confirmKickOut = false;
							}
						};
						
					} else {
						return new ResponseEffectsOnly("移除角色", UtilText.parse(occupant(), "告诉[npc.name]你得继续自己的路，再也不会见到[npc.herHim]了。<br/>"
								+ "[style.italicsMinorBad(做出选择后，你需要再次点击以确认从游戏中永久删除该角色。)]")) {
							@Override
							public void effects() {
								confirmKickOut = true;
							}
						};
					}
					
				} else if (index == 0) {
					return new Response("离开", UtilText.parse(occupant(), "告诉[npc.name]你会换个时间找[npc.herHim]。"), Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							applyReactionReset();
							exitDialogue();
						}
					};
					
				} else {
					return null;
				}
			
			} else if(responseTab == 1) {
				if (index == 0) {
					return new Response("离开", UtilText.parse(occupant(), "告诉[npc.name]你会换个时间找[npc.herHim]。"), Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_LEAVING", occupant()));
							applyReactionReset();
							exitDialogue();
						}
					};
					
				} else {
					return OCCUPANT_START.getResponse(responseTab, index);
				}
				
			} else if(responseTab == 2) {
				return CompanionManagement.getManagementResponses(index);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_TALK_LIFE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_TALK_LIFE", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_TALK_JOB = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_TALK_JOB", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_SLEEP_OVER = new DialogueNode("", "", true) {

//		@Override
//		public int getSecondsPassed() {
//			return sleepTimeInMinutes*60;
//		}
		
		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_SLEEP_OVER", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("醒来", "过了一会，你醒了……", OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(!occupant().isAtHome()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP_ALONE", occupant()));
			
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP", occupant()));
				UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			}
			
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!occupant().isAtHome()) {
				if (index == 1) {
					return new Response("外面", "你已经到了外面，御城区的街道上。", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							exitDialogue();
						}
					};
				}
				return null;
			}
			
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_REMOVE = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			exitDialogue();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode APARTMENT_AFTER_SEX = new DialogueNode("结束", "", true) {
		
		@Override
		public String getDescription(){
			return "躺在[npc.namePos]的沙发上，缓一口气。";
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().size()>2) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_AFTER_SEX_THREESOME", occupant(), Main.game.getPlayer().getCompanions().get(0));

			} else if(Main.sex.getNumberOfOrgasms(occupant()) >= occupant().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_AFTER_SEX", occupant());
				
			} else {
				return UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_AFTER_SEX_NO_ORGASM", occupant());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", 
						UtilText.parse(occupant(),
								occupant().isAsleep()
									?"让[npc.name]继续睡觉，离开[npc.her]的"+(isApartment?"公寓":"房间")+"."
									:"给[npc.name]一些时间休息"),
						Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_LEAVE_AFTER_SEX", occupant()));
						exitDialogue();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
