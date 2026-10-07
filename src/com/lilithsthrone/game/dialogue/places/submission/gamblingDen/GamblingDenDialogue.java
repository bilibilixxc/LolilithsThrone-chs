package com.lilithsthrone.game.dialogue.places.submission.gamblingDen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Axel;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePoker;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.submission.SMAxel;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.5.5
 * @author Innoxia
 */
public class GamblingDenDialogue {
	
	private static final int REWARD_AMOUNT = 50_000;
	
	public static final DialogueNode ENTRANCE = new DialogueNode("入口", "", false) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)
					|| Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_THREE_END;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_THREE_END) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "ENTRANCE_END_VENGAR_QUEST");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "ENTRANCE_REPEAT");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "ENTRANCE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_THREE_END) {
				if(index==1) {
					return new Response("继续", "[axel.Name]长叹一声，转身看向你。", AXEL_VENGAR_RESOLUTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.SIDE_UTIL_COMPLETE));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(REWARD_AMOUNT));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Axel.class).setAffection(Main.game.getPlayer(), 40));
						}
					};
				}
					
			} else {
				if(index==1) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)) {
						return new Response("继续", "继续探索赌场。", ENTRANCE){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
							}
						};
						
					} else {
						return new Response("离开", "推开门，回到屈城区。", SubmissionGenericPlaces.GAMBLING_DEN){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
								Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_GAMBLING_DEN);
							}
						};
					}
					
				} else if(index==2) {
					return new Response("[axel.name]", "接近[axel.name]打个招呼。", AXEL){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
						}
					};
				
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_RESOLUTION = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_RESOLUTION", Main.game.getPlayer().getMainCompanion());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("[axel.name]的办公室", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_AXEL = new DialogueNode("[axel.name]的办公室", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE);
			Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE);
			for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
				companion.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_AXEL", Main.game.getPlayer().getMainCompanion());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised)) {
				boolean attracted = Main.game.getNpc(Axel.class).isAttractedTo(Main.game.getPlayer());
				if(index==1) {
					if(!attracted) {
						return new Response("支配", "[axel.name]不愿意跟你做爱，因为[axel.she]只会被男性化角色吸引。", null);
					}
					return new ResponseSex(
							"支配",
							"告诉[axel.name]你很想征服[axel.herHim]……",
							true,
							true,
							new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_SUBMISSIVE))) {
							},
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_LEXA_DOMINATE"));
					
					
				} else if(index==2) {
					if(!attracted) {
						return new Response("屈服", "[axel.name]不愿意跟你做爱，因为[axel.she]只会被男性化角色吸引。", null);
					}
					return new ResponseSex(
							"屈服",
							"告诉[axel.name][axel.sheIs]可以随意地支配你……",
							true,
							true,
							new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
							},
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_LEXA_SUBMIT"));
					
					
				} else if(index==3) {
					// Cage on/off
					if(Main.game.getNpc(Axel.class).getClothingInSlot(InventorySlot.PENIS)!=null) {
						return new Response("贞操笼: [style.colourMinorGood(已装备)]",
								"[axel.Name]正戴着贞操笼。如果你想的话可以摘下来……"
										+ "<br/>[style.italicsMinorGood(你可以随时自由地给[axel.namePos]装上或摘下贞操笼。)]",
								OFFICE_WITH_LEXA_CAGE) {
							@Override
							public void effects() {
								((Axel)Main.game.getNpc(Axel.class)).applyCage(false, Main.game.getPlayer());
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CAGE_OFF"));
							}
						};
						
					} else {
						return new Response("贞操笼: [style.colourMinorBad(解除)]",
								"[axel.Name]没有戴着贞操笼。如果你想的话可以给[axel.her]戴上……"
										+ "<br/>[style.italicsMinorGood(你可以随时自由地给[axel.namePos]装上或摘下贞操笼。)]",
								OFFICE_WITH_LEXA_CAGE) {
							@Override
							public void effects() {
								((Axel)Main.game.getNpc(Axel.class)).applyCage(true, Main.game.getPlayer());
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CAGE_ON"));
							}
						};
					}
					
				} else if(index==6) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised)) {
						return new Response("衣物",
								"告诉[axel.name][axel.she]应该选另一套衣服穿。",
								OFFICE_WITH_LEXA_CLOTHING);
					}
					return new Response("雌化",
							"告诉[axel.name]你会帮[axel.herHim]理解自身的潜力，将其完全雌化。"
									+ "<br/>[style.italicsArcane(这是永久且无法逆转的转化！)]",
							OFFICE_WITH_LEXA_FEMINISE);
					
				} else if(index==7
						&& Main.game.getPlayer().isFeminine()
						&& !Main.game.getNpc(Axel.class).getSexualOrientation().isAttractedToFeminine()) {
					if(!Main.game.getPlayer().hasItemType(ItemType.ORIENTATION_HYPNO_WATCH)) {
						return new Response("催眠怀表", "你没有催眠怀表，所以无法让[axel.name]变成双性恋……", null);
					}
					return new Response("催眠怀表",
							"使用催眠怀表让[axel.name]变成双性恋。"
									+ "<br/>[style.italicsArcane(这是永久且无法逆转的改变！)]",
							OFFICE_WITH_LEXA_HYPNO) {
						@Override
						public void effects() {
							Main.game.getNpc(Axel.class).setSexualOrientation(SexualOrientation.AMBIPHILIC);
						}
					};
					
				} else if(index==0) {
					return new Response("离开", "不对[axel.name]做什么事情，回到赌场。", ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
							Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_AXEL_LEAVE", Main.game.getPlayer().getMainCompanion()));
						}
					};
					
				}
				
			} else {
				if(index==1) {
					return new ResponseSex(
							"支配",
							"告诉[axel.name]你很想征服[axel.herHim]……",
							true,
							true,
							new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_SUBMISSIVE))) {
							},
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_AXEL_DOMINATE"));
					
					
				} else if(index==2) {
					return new ResponseSex(
							"顺从",
							"告诉[axel.name][axel.sheIs]可以随意地支配你……",
							true,
							true,
							new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
							},
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_AXEL_SUBMIT"));
					
					
				} else if(index==6) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("提供口交", "你无法使用自己的嘴巴，所以不能舔[axel.namePos]的屌……", null);
					}
					return new ResponseSex(
							"提供口交",
							"跟[axel.name]来一场性爱，跪下身子开始舔他的鸡巴……",
							true,
							true,
							new SMAxel(
									SexPosition.STANDING,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newArrayListOfValues(CoverableArea.PENIS),
									Util.newArrayListOfValues(CoverableArea.MOUTH),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_AXEL_ORAL_PERFORMING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							return list;
						}
					};
					
				} else if(index==7) {
					boolean cockAccess = Main.game.getPlayer().hasPenisIgnoreDildo() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
					boolean pussyAccess = Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
					
					if(!cockAccess && !pussyAccess) {
						return new Response("接受口交", "[axel.name]无法接触到你的任何生殖器，所以也就不能给你口交……", null);
					}
					return new ResponseSex(
							"接受口交",
							cockAccess
								?"跟[axel.name]来一场性爱，让他跪下身子开始舔你的鸡巴……"
								:"跟[axel.name]来一场性爱，让他跪下身子给你舔阴……",
							true,
							true,
							new SMAxel(
									SexPosition.STANDING,
									cockAccess
										?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)
										:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
									Util.newArrayListOfValues(CoverableArea.MOUTH),
									cockAccess
										?Util.newArrayListOfValues(CoverableArea.PENIS)
										:Util.newArrayListOfValues(CoverableArea.VAGINA),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_AXEL_ORAL_RECEIVING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							if(cockAccess) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
							} else {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
							}
							return list;
						}
					};
					
				} else if(index==8 && Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("提供舔肛", "你无法使用自己的嘴巴，所以不能给[axel.name]舔肛……", null);
					}
					return new ResponseSex( 
							"提供舔肛",
							"跟[axel.name]来一场性爱，让他趴在桌子上，你来给他舔肛……",
							true,
							true,
							new SMAxel(
									SexPosition.OVER_DESK,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
									Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS),
									Util.newArrayListOfValues(CoverableArea.MOUTH),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotDesk.OVER_DESK_ON_FRONT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.PERFORMING_ORAL))),
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_AXEL_ANILINGUS_PERFORMING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
							list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Axel.class), FingerPenis.COCK_MASTURBATING_START, false, true));
							return list;
						}
					};
					
				} else if(index==9 && Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						return new Response("接受舔肛", "你无法使用自己的肛门，所以[axel.name]不能给你舔肛……", null);
					}
					return new ResponseSex(
							"接受舔肛",
							"跟[axel.name]来一场性爱，让他来给你舔肛……",
							true,
							true,
							new SMAxel(
									SexPosition.AGAINST_WALL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS),
									Util.newArrayListOfValues(CoverableArea.MOUTH),
									Main.game.getPlayer().hasPenis()
										?Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS)
										:(Main.game.getPlayer().hasVagina()
											?Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.VAGINA)
											:Util.newArrayListOfValues(CoverableArea.ANUS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotAgainstWall.PERFORMING_ORAL_WALL)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.FACE_TO_WALL))),
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_AXEL_ANILINGUS_RECEIVING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true));
							if(Main.game.getPlayer().hasPenis()) {
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Axel.class), FingerPenis.COCK_MASTURBATED_START, false, true));
							} else if(Main.game.getPlayer().hasVagina()) {
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Axel.class), FingerVagina.FINGERED_START, false, true));
							}
							return list;
						}
					};
					
				} else if(index==0) {
					return new Response("离开", "不对[axel.name]做什么事情，回到赌场。", ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
							Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_AXEL_LEAVE", Main.game.getPlayer().getMainCompanion()));
						}
					};
					
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_AXEL_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised)) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_LEXA_SEX");
			}
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_AXEL_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "离开[axel.namePos]的办公室，回到赌场。", ENTRANCE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
						Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_AXEL_SEX_LEAVE"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_CAGE = new DialogueNode("", "", true) {
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
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_HYPNO = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_HYPNO");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_FEMINISE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("喝(无补充)",
						"让[axel.name]喝下药水，不附加其他内容，将[axel.herHim]娘化，并且把肉棒缩小为微型的阴蒂肉棒。",
						OFFICE_WITH_LEXA_FEMINISE_APPLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_FEMINISE_APPLY_ZERO"));
						((Axel)Main.game.getNpc(Axel.class)).applyFeminisation(PenetrationGirth.TWO_NARROW, PenisLength.ZERO_MICROSCOPIC, TesticleSize.ZERO_VESTIGIAL, CumProduction.ONE_TRICKLE);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelFeminised, true);
					}
				};
				
			} else if(index==2) {
				return new Response("喝(半数补充)",
						"让[axel.name]喝下药水，附加半数补充剂，将[axel.herHim]娘化，但保留正常大小的肉棒。",
						OFFICE_WITH_LEXA_FEMINISE_APPLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_FEMINISE_APPLY_HALF"));
						((Axel)Main.game.getNpc(Axel.class)).applyFeminisation(PenetrationGirth.THREE_AVERAGE, PenisLength.TWO_AVERAGE, TesticleSize.TWO_AVERAGE, CumProduction.ONE_TRICKLE);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelFeminised, true);
					}
				};
				
			} else if(index==3) {
				return new Response("喝(全数补充)",
						"让[axel.name]喝下药水，附加全部的补充剂，将[axel.herHim]娘化，并让肉棒保持原本的大小。",
						OFFICE_WITH_LEXA_FEMINISE_APPLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_FEMINISE_APPLY_FULL"));
						((Axel)Main.game.getNpc(Axel.class)).applyFeminisation(PenetrationGirth.FIVE_THICK, PenisLength.FOUR_HUGE, TesticleSize.FOUR_HUGE, CumProduction.FOUR_LARGE);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelFeminised, true);
					}
				};
				
			} else if(index==0) {
				return new Response("反悔",
						"决定还是不要娘化[axel.name]，在办公室里做些别的事情。",
						OFFICE_WITH_LEXA_FEMINISE_BACK_OUT);
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE_BACK_OUT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_FEMINISE_BACK_OUT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE_APPLY = new DialogueNode("", "", true) {
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
				return new Response("同意",
						"你没理由不帮帮[axel.name]，选一件要穿的衣服。",
						OFFICE_WITH_LEXA_FEMINISE_APPLY_CLOTHING);
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE_APPLY_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_FEMINISE_APPLY_CLOTHING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("女性化",
						"让[axel.name]穿上女性化的衣服。",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_FEMININE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingFeminine, true);
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			} else if(index==2) {
				return new Response("女仆",
						"让[axel.name]穿上女仆装。",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_MAID"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingMaid, true);
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			} else if(index==3) {
				return new Response("妓女",
						"让[axel.name]穿上格外挑逗的服装，一眼就能让人看出[axel.sheIs]是个淫荡的站街女。",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_WHORE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingWhore, true);
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingFeminine)) {
					return new Response("女性化", "让[axel.name]穿上女性化的衣服。", null);
				}
				return new Response("女性化",
						"让[axel.name]穿上女性化的衣服。",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_FEMININE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingFeminine, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingMaid, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingWhore, false);
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingMaid)) {
					return new Response("女仆", "[axel.Name]正在穿着女仆装。", null);
				}
				return new Response("女仆",
						"让[axel.name]穿上女仆装。",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_MAID"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingFeminine, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingMaid, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingWhore, false);
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingWhore)) {
					return new Response("妓女", "[axel.Name]正在穿着站街女的服装。", null);
				}
				return new Response("妓女",
						"让[axel.name]穿上格外挑逗的服装，一眼就能让人看出[axel.sheIs]是个淫荡的站街女。",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_WHORE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingFeminine, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingMaid, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingWhore, true);
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			} else if(index==0) {
				return new Response("改变主意",
						"决定还是不让[axel.name]换衣服了，在办公室里做些别的事情。",
						OFFICE_WITH_LEXA_CLOTHING_BACK_OUT);
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_WITH_LEXA_CLOTHING_BACK_OUT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_BACK_OUT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL = new DialogueNode("与[axel.name]交谈", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("生意", "跟[axel.name]谈谈生意。", AXEL_BUSINESS);
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
					return new Response("罗克西", "向[axel.name]打听罗克西。", AXEL_ROXY) {
						@Override
						public void effects() {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelMentionedVengar)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_ROXY_REPEAT"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_ROXY"));
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelMentionedVengar, true);
						}
					};
					
				} else {
					return new Response("罗克西", "你得先和罗克西交谈，才能跟她打听[axel.name]。", null);
				}
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelMentionedVengar) && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_VENGAR)) { // Initial asking/quest start:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelExplainedVengar)) {
						return new Response("提供帮助",
								"告诉[axel.name]你愿意帮[axel.herHim]解决文加。",
								AXEL_VENGAR) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_SIDE;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_OFFER_HELP"));
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_VENGAR));
							}
						};
						
					} else {
						return new Response("文加", "向[axel.name]打听文加。", AXEL_VENGAR) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR"));
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelExplainedVengar, true);
							}
						};
					}
					
				} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_VENGAR)
						&& Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_TWO_COOPERATION) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelToldSubmit)) {
						return new Response("拜访文加", "告诉[axel.name]你准备好和[axel.herHim]去拜访文加了。", AXEL_VENGAR_VISIT) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_SIDE;
							}
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
								Main.game.getNpc(Axel.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
								Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
								Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
							}
						};
						
					} else {
						return new Response("文加的要求", "告诉[axel.name]文加提出的要求。", AXEL_VENGAR_SUBMIT) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelToldSubmit, true);
							}
						};
					}
					
				} else if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified)
							|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised)) {
						return new Response("办公室", "告诉莱克萨你想跟[lexa.herHim]去办公室里“私聊”。", OFFICE_WITH_AXEL);
						
					} else {
						if(Main.game.getPlayer().isFeminine()) {
							return new Response("办公室", "你看得出来阿克塞尔并不会被你这么女性化的角色吸引，并不愿意跟你一起去办公室。", null);
						}
						return new Response("办公室", "询问阿克塞尔你们两个能否去办公室“私聊”，当作他对你协助的谢礼。", OFFICE_WITH_AXEL) {
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
				}
				
			} else if(index==0) {
				return new Response("返回", "道别之后走回入口处。", ENTRANCE);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_BUSINESS = new DialogueNode("与[axel.name]交谈", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_BUSINESS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("生意", "你已经跟[axel.name]谈过生意了。", null);
			}
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL_ROXY = new DialogueNode("与[axel.name]交谈", "", true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("罗克西", "你已经问过[axel.name]罗克西的事情了。", null);
			}
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL_VENGAR = new DialogueNode("与[axel.name]交谈", "", true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_SUBMIT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("拜访文加", "告诉[axel.name]你准备好和[axel.herHim]去拜访文加了。", AXEL_VENGAR_VISIT) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.QUEST_SIDE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Axel.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
					}
				};
				
			} else if(index==2) {
				return new Response("稍等", "告诉[axel.name]你还没准备好跟[axel.herHim]去见文加，等到时候会通知[axel.herHim]的。", AXEL_VENGAR);
				
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "跟着[axel.name]和默前去文加的大厅时，思考一下影的警告。", AXEL_VENGAR_VISIT_KNEEL) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Axel.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 30));
						
						Main.game.getNpc(Axel.class).unequipAllClothingIntoVoid(true, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT_KNEEL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_KNEEL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("阻止[axel.name]", "阻止[axel.name]喝下下了药的朗姆酒。", AXEL_VENGAR_VISIT_STOPPED);
				
			} else if(index==2) {
				return new Response("闭口不言",
						"并不出言阻止[axel.name]喝下下了药的朗姆酒，让[axel.herHim]被转化成娘化版本。",
						AXEL_VENGAR_VISIT_SISSIFIED) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.ANDROGYNOUS;
					}
					@Override
					public void effects() {
						((Axel)Main.game.getNpc(Axel.class)).applySissification();
						((Axel)Main.game.getNpc(Axel.class)).applyCage(true, Main.game.getPlayer());
						((Axel)Main.game.getNpc(Axel.class)).setName(new NameTriplet("莱克萨", "莱克萨", "莱克萨"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelSissified, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT_STOPPED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_STOPPED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "跟[axel.name]回到赌场。", AXEL_VENGAR_VISIT_RETURN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_STOPPED_RETURN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT_SISSIFIED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("够了！", "阻止文加得寸进尺，带[axel.name]返回赌场。", AXEL_VENGAR_VISIT_RETURN) {
					@Override
					public void effects() {
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED_RETURN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END));
					}
				};
				
			} else if(index==2) {
				return new Response("静观其变", "让文加再进一步……", AXEL_VENGAR_VISIT_SISSIFIED_FINISH);
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT_SISSIFIED_FINISH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED_FINISH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("够了！", "告诉文加已经足够了，带[axel.name]返回赌场。", AXEL_VENGAR_VISIT_RETURN) {
					@Override
					public void effects() {
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED_RETURN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isFeminine()) {
					return new Response("操了莱克萨", "莱克萨没兴趣跟你这么女性化的角色做爱……", null);
				}
				return new ResponseSex(
						"操了莱克萨",
						"接受文加的建议，在大厅的众人面前操了莱克萨。",
						true,
						false,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Axel.class), SexSlotAllFours.ALL_FOURS))),
						Util.newArrayListOfValues(
								Main.game.getNpc(Vengar.class),
								Main.game.getNpc(Shadow.class),
								Main.game.getNpc(Silence.class)),
						null,
						AFTER_AXEL_SISSIFIED_SEX,
						UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED_FINISH_PLAYER_FUCK"));
				
			} else if(index==3) {
				return new ResponseSex(
						"看着文加",
						"跟大厅中其他人一起，看着“莱克萨”被文加操。",
						true,
						false,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Axel.class), SexSlotAllFours.ALL_FOURS))),
						Util.newArrayListOfValues(
								Main.game.getPlayer(),
								Main.game.getNpc(Shadow.class),
								Main.game.getNpc(Silence.class)),
						null,
						AFTER_AXEL_SISSIFIED_SEX,
						UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED_FINISH_VENGAR_FUCK"));
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_AXEL_SISSIFIED_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_AXEL_SISSIFIED_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "陪着雷克萨回到赌场。", AXEL_VENGAR_VISIT_RETURN) {
					@Override
					public void effects() {
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_AXEL_SISSIFIED_SEX_RETURN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_VISIT_RETURN = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return ""; // Appended by lead-in dialogues
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续",
						"继续穿过隧道……",
						AXEL_VENGAR_VISIT_RETURN_NEXT);
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_VISIT_RETURN_NEXT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
			Main.game.getNpc(Silence.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_RETURN_NEXT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("接受护送",
						"执法者护送着你和[axel.name]到了最近的执法者岗哨……",
						AXEL_VENGAR_VISIT_RETURN_ENFORCERS);
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_VISIT_RETURN_ENFORCERS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).returnToHome();
			Main.game.getNpc(Silence.class).returnToHome();
			Main.game.getPlayer().setNearestLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_ENTRANCE, false);
			Main.game.getNpc(Axel.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 3*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_RETURN_ENFORCERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("赌场",
						"陪着[axel.name]回到赌场……",
						AXEL_VENGAR_VISIT_RETURN_ENFORCERS_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_VISIT_RETURN_ENFORCERS_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			RatWarrensDialogue.applyRatWarrensRaid();
			Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
			Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_RETURN_ENFORCERS_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("报酬",
						"等[axel.name]从办公室拿来你的报酬。",
						AXEL_VENGAR_VISIT_RETURN_FINISH) {
					@Override
					public void effects() {
						((Shadow)Main.game.getNpc(Shadow.class)).moveToBountyHunterLodge();
						((Silence)Main.game.getNpc(Silence.class)).moveToBountyHunterLodge();
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(REWARD_AMOUNT));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Axel.class).setAffection(Main.game.getPlayer(), 40));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.SIDE_UTIL_COMPLETE));
						((Axel)Main.game.getNpc(Axel.class)).applyFeminisationCosmetics();
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_VISIT_RETURN_FINISH = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(REWARD_AMOUNT), true);
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_RETURN_FINISH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("赌场", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<10) {
					return new Response("老虎机("+UtilText.formatAsMoney(10, "span")+")", "你的钱不够玩老虎机！", null);
				} else {
					return new Response("老虎机("+UtilText.formatAsMoney(10, "span")+")", "朝最近的老虎机里丢10火币，拉下拉杆。", SLOT_MACHINE) {
						@Override
						public void effects() {
							
							Map<AbstractSubspecies, Integer> slotMachineValues = Util.newHashMapOfValues(
									new Value<>(Subspecies.HUMAN, 5),
									new Value<>(Subspecies.IMP, 10),
									new Value<>(Subspecies.DOG_MORPH, 25),
									new Value<>(Subspecies.CAT_MORPH, 25),
									new Value<>(Subspecies.COW_MORPH, 50),
									new Value<>(Subspecies.DEMON, 100),
									new Value<>(Subspecies.ELEMENTAL_ARCANE, 500));
							
							Map<AbstractSubspecies, Integer> slotMachineValueProbabilities = Util.newHashMapOfValues(
									new Value<>(Subspecies.HUMAN, 16),
									new Value<>(Subspecies.IMP, 8),
									new Value<>(Subspecies.DOG_MORPH, 2),
									new Value<>(Subspecies.CAT_MORPH, 2),
									new Value<>(Subspecies.COW_MORPH, 2),
									new Value<>(Subspecies.DEMON, 1),
									new Value<>(Subspecies.ELEMENTAL_ARCANE, 1));
							
							Main.game.getTextEndStringBuilder().append(
									"<p>"
										+ "你准备在老虎机上试试运气，于是走到最近的机器旁，在硬币口里投了十火币。"
									+ "</p>"
									+ Main.game.getPlayer().incrementMoney(-10)
									+"<p>"
										+ "拉下拉杆后，你看着前面的三个转盘快速旋转，慢慢停在了三张确定的图片上……"
									+ "</p>");
							

							Main.game.getTextEndStringBuilder().append("<div class='container-full-width'>");
							
								Main.game.getTextEndStringBuilder().append("<div class='container-half-width' style='position:relative; text-align:center;'>"
										+ "<p style='width:100%'><b>老虎机结果:</b></p>");
								
								List<AbstractSubspecies> races = new ArrayList<>(slotMachineValues.keySet());
								
								List<AbstractSubspecies> results = new ArrayList<>();
	
								boolean winner = false;
								if(Math.random()<0.32f) {
									AbstractSubspecies s = Util.getRandomObjectFromWeightedMap(slotMachineValueProbabilities);
									for(int i=0; i<3; i++) {
										results.add(s);
									}
									winner=true;
									
								} else {
									for(int i=0; i<3; i++) {
										AbstractSubspecies s = races.get(Util.random.nextInt(races.size()));
										results.add(s);
										if(i==0) {
											races.remove(s);
										}
									}
									Collections.shuffle(results);
								}
								
								for(AbstractSubspecies r : results) {
									Main.game.getTextEndStringBuilder().append(
											"<div class='modifier-icon' style='width:31.3%; margin:0 1%; border:3px solid "+(winner?PresetColour.GENERIC_EXCELLENT.toWebHexString():"")+"; display:inline-block;'>"
													+"<div class='modifier-icon-content'>"+r.getSVGString(null)+"</div>"
											+ "</div>");
								}
								if(winner) {
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
													+ "[style.colourExcellent(你赢了！)]<br/>三个"+results.get(0).getNamePlural(null)+"代表"+UtilText.formatAsMoney(slotMachineValues.get(results.get(0)), "span")+"！"
											+ "</p>");
								} else {
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
													+ "[style.colourTerrible(你输了！)]"
											+ "</p>");
								}
								
								Main.game.getTextEndStringBuilder().append("</div>");
								Main.game.getTextEndStringBuilder().append("<div class='container-half-width' style='position:relative; text-align:center;'>"
										+"<p style='text-align:center;'>");
							
									for(Entry<AbstractSubspecies, Integer> entry : slotMachineValues.entrySet()) {
										Main.game.getTextEndStringBuilder().append("<span style='color:"+entry.getKey().getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getNamePlural(null))+"</span>: "
												+UtilText.formatAsMoney(entry.getValue(), "span")+"<br/>");
									}
												
									Main.game.getTextEndStringBuilder().append("</p>");
								Main.game.getTextEndStringBuilder().append("</div>");
							Main.game.getTextEndStringBuilder().append("</div>");
								
							if(winner) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(slotMachineValues.get(results.get(0))));
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "你赢下了"+Util.intToString(slotMachineValues.get(results.get(0)))+"，你思索着是该见好就收继续赶路，还是乘胜追击再试一次……"
										+ "</p>");
							} else {
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "你输掉了十火币，你思索着该收手止损继续赶路，还是再来一次争求一胜……"
										+ "</p>");
							}
						}
					};
				}
			}
			return null;
		}
	};
	

	public static final DialogueNode SLOT_MACHINE = new DialogueNode("赌场", "", false) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CORRIDOR.getResponse(responseTab, index);
		}
		
	};
	
	
	public static final DialogueNode GAMBLING = new DialogueNode("骰子扑克桌", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "GAMBLING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<NPC> gamblers = Main.game.getNonCompanionCharactersPresent();
			
			if(index==0) {
				return null;
				
			} else if(index==gamblers.size()+1){
				return new Response("规则", "阅读附近展示骰子扑克规则的标志。", GAMBLING_RULES);
				
			} else {
				try {
					gamblers.sort((g1, g2) -> ((GamblingDenPatron) g1).getTable().compareTo(((GamblingDenPatron) g2).getTable()));
				} catch(Exception ex) {
				}
				
				if(index-1<gamblers.size()) {
					NPC gambler = gamblers.get(index-1);
					DicePokerTable table = 
							(gambler instanceof GamblingDenPatron && ((GamblingDenPatron) gambler).getTable()!=null)
								?((GamblingDenPatron) gambler).getTable()
								:DicePokerTable.COPPER;
					int buyIn = table.getInitialBet()+table.getRaiseAmount();
					if(Main.game.getPlayer().getMoney()>=buyIn) {
						return new ResponseEffectsOnly(
								"<span style='color:"+table.getColour().toWebHexString()+";'>"+UtilText.parse(gambler, "[npc.Name]")+"</span> ("+UtilText.formatAsMoney(buyIn, "span")+")",
								UtilText.parse(gambler,
										"跟[npc.name]来一把骰子扑克。入注金额为"+UtilText.formatAsMoney(table.getInitialBet(), "span")
										+"，但想要加注则还需要"+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+"。")) {
							@Override
							public void effects() {
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, GAMBLING, "misc/dicePoker")));
							}
						};
						
					} else {
						return new Response(gambler.getName(true)+" ("+UtilText.formatAsMoneyUncoloured(buyIn, "span")+")",
								"入注金额为"+UtilText.formatAsMoney(table.getInitialBet(), "span")
								+"，但想要加注则还需要"+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+"。所以你钱不够在这个桌子上赌！",
								null);
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode GAMBLING_RULES = new DialogueNode("骰子扑克桌", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "GAMBLING_RULES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("返回", "读完规则后，从一旁退开。", GAMBLING);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MALE_STALLS = new DialogueNode("男性配种台", "", false) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_MALE_STALLS"));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsBreeder)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsMother)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_STALLS_KNOWLEDGE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_STALLS_NO_KNOWLEDGE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_FUTA_STALLS = new DialogueNode("扶她配种台", "", false) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_FUTA_STALLS"));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsBreeder)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsMother)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_STALLS_KNOWLEDGE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_STALLS_NO_KNOWLEDGE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
}
