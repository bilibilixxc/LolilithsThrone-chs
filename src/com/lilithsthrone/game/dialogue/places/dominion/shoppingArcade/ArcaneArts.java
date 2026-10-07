package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Vicky;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.dominion.SMVicky;
import com.lilithsthrone.game.sex.managers.dominion.SMVickyOverDesk;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFoot;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.game.sex.sexActions.dominion.VickySpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.4.7.8
 * @author Innoxia
 */
public class ArcaneArts {
	
	private static AbstractItemType mealItemType;
	private static int mealResponseIndex = 0;
	private static List<Value<String, String>> mealResponses;
	private static int helpResponseIndex = 0;
	private static List<Value<String, String>> helpResponses;
	
	private static boolean vickyRefused = false;
	private static boolean vickyHadSex = false;
	
	static {
		mealResponses = new ArrayList<>();
		mealResponses.add(new Value<>("给她喂食", "听从维姬的命令，给她切肉，一口一口喂给她。"));
		mealResponses.add(new Value<>("受抚摸", "听从维姬的命令，在她身边跪下来，让她可以边吃边抚摸你。"));
		mealResponses.add(new Value<>("按摩", "听从维姬的命令，在她吃的时候帮她按摩肩背。"));

		helpResponses = new ArrayList<>();
		helpResponses.add(new Value<>("整理货架", "听从维姬的命令，帮她整理货架。"));
		helpResponses.add(new Value<>("扫地", "听从维姬的命令，帮她清扫商店的地面。"));
		helpResponses.add(new Value<>("整理武器", "听从维姬的命令，整理将要售卖的武器。"));
	}
	
	private static Vicky getVicky() {
		return (Vicky) Main.game.getNpc(Vicky.class);
	}
	
	private static boolean isInApartment() {
		return Main.game.getDialogueFlags().hasFlag("innoxia_vicky_apartment");
	}
	
	private static DialogueNode getPostSexScene() {
		if(isInApartment()) {
			return VICKY_APARTMENT_POST_SEX;
		}
		return VICKY_POST_SEX;
	}
	
	private static boolean isMuskMarked() {
		return Main.game.getPlayer().isMarkedByMuskMarkerCharacter(getVicky());
	}
	
	public static final DialogueNode EXTERIOR = new DialogueNode("奥术艺术(外部)", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "EXTERIOR");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ShoppingArcadeDialogue.getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					if(Main.game.isWorkTime()) {
						return new Response("进入", "进入奥术艺术。", SHOP_WEAPONS) {
							@Override
							public void effects() {
								vickyRefused = false;
								vickyHadSex = false;
							}
						};
					} else {
						return new Response("进入", "奥术艺术目前关门。如果你想在这里购物的话，得过一会再来。", null);
					}
				}
			}
			
			return ShoppingArcadeDialogue.getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_WEAPONS = new DialogueNode("奥术艺术", "-", true) {
		@Override
		public void applyPreParsingEffects() {
			if(!vickyRefused
					&& getVicky().getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>=3
					&& (Main.game.isMuskContentEnabled()
							?!isMuskMarked()
							:!Main.game.getDialogueFlags().hasFlag("innoxia_vicky_claimed"))) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_DOMINATE"));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS"));
			}
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				Main.game.getPlayer().setCharacterReactedToPregnancy(getVicky(), true);
			}
			if(getVicky().isVisiblyPregnant()) {
				getVicky().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
			}
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
		public String getResponseTabTitle(int index) {
			if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_claimed") && (!Main.game.isMuskContentEnabled() || isMuskMarked()) && !vickyRefused) {
				if(index==0) {
					return "购物";
				} else if(index==1) {
					return "取悦";
				}
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			
			// Domination responses:
			
			if(!vickyHadSex // So that this doesn't trigger immediately after having sex (which calls SHOP_WEAPONS.getResponse())
					&& !vickyRefused // So that this doesn't repeatedly trigger when refusing
					&& getVicky().getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>=3
					&& (Main.game.isMuskContentEnabled()
							?!isMuskMarked()
							:!Main.game.getDialogueFlags().hasFlag("innoxia_vicky_claimed"))) {
				if(index==1) {
					if(Main.game.isMuskContentEnabled()) {
						return new ResponseSex("获得标记", "向维姬屈服，让她射满你的全身，被她浓重的淫味所标记。",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVicky(
										SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
										new SexType(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
										new SexType(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
										null,
										Util.newArrayListOfValues(CoverableArea.PENIS)) {
									@Override
									public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
										return OrgasmBehaviour.PULL_OUT;
									}
									@Override
									public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
										if(!character.isPlayer()) {
											return OrgasmCumTarget.FACE;
										}
										return super.getCharacterPullOutOrgasmCumTarget(character, target);
									}
									@Override
									public boolean isPartnerWantingToStopSex(GameCharacter partner) {
										if(partner instanceof Vicky && Main.sex.isSatisfiedFromOrgasms(partner, true)) {
											return true;
										}
										return super.isPartnerWantingToStopSex(partner);
									}
									@Override
									public List<SexActionInterface> getUniqueSexClasses(GameCharacter character) {
										return Util.newArrayListOfValues(VickySpecials.VICKY_MARKING_ORGASM);
									}
								},
								null,
								null,
								VICKY_CLAIMED_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_MARKING_SEX")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getVicky(), FingerPenis.COCK_MASTURBATING_START, false, true));
							}
						};
						
					} else {
						return new ResponseSex("顺从", "屈服于维姬，让她随心所欲地操你。",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVicky(
										SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE)),
										null, null, null, null),
								null,
								null,
								VICKY_CLAIMED_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_SUBMIT_SEX"));
					}
					
				} else if(index==2) {
					return new Response("拒绝", "拒绝屈服于维姬。", VICKY_REFUSED) {
						@Override
						public void effects() {
							vickyRefused = true;
						}
					};
				}
				return null;
			}
			
			// Standard responses:

			if (index == 0) {
				return new Response("离开", "离开奥术艺术，回到购物中心。", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
					}
				};
			}
			
			if(!Main.game.getDialogueFlags().hasFlag("innoxia_vicky_claimed") || responseTab==0) {
				if(index == 1) {
					return new ResponseTrade("武器", "走到柜台前，看看维姬有什么武器卖。", getVicky()) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
							
							getVicky().clearNonEquippedInventory(false);
							
							for (Entry<AbstractWeapon, Integer> weapon : (getVicky()).getWeaponsForSale().entrySet()) {
								if(getVicky().isInventoryFull()) {
									break;
								}
								getVicky().addWeapon(weapon.getKey(), weapon.getValue(), false, false);
							}
						}
					};
					
				} else if (index == 2) {
					return new ResponseTrade("药剂和法术", "走到柜台前，看看维姬有什么药剂、精华和法术卖。", getVicky()) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);

							getVicky().clearNonEquippedInventory(false);
							
							for (Entry<AbstractItem, Integer> item : (getVicky()).getItemsForSale().entrySet()) {
								if(getVicky().isInventoryFull()) {
									break;
								}
								getVicky().addItem(item.getKey(), item.getValue(), false, false);
							}
						}
					};
					
				} else if (index == 3) {
					if((getVicky()).getClothingForSale().isEmpty()) {
						return new Response("衣物", "维姬现在没有衣物在售卖。", null);
					}
					return new ResponseTrade("衣物", "走到柜台前，看看维姬有什么衣物卖。", getVicky()) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);

							getVicky().clearNonEquippedInventory(false);
							
							for (Entry<AbstractClothing, Integer> clothing : (getVicky()).getClothingForSale().entrySet()) {
								if(getVicky().isInventoryFull()) {
									break;
								}
								getVicky().addClothing(clothing.getKey(), clothing.getValue(), false, false);
							}
						}
					};
				}
				
				// After Vicky has claimed the player, sexual actions are under the 'Pleasure' tab
				if(!Main.game.getDialogueFlags().hasFlag("innoxia_vicky_claimed")) {
					if(index==5) {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursPackageObtained)) {
							if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HYPNO_WATCH)==Quest.SIDE_HYPNO_WATCH_VICKY) {
								if(Main.game.getPlayer().isInventoryFull()) {
									return new Response("亚瑟的包裹", "你的物品栏没有多余的空间放置包裹了！", null);
									
								} else {
									return new Response("亚瑟的包裹", "告诉维姬你是来拿亚瑟的包裹的。", ARTHURS_PACKAGE) {
										@Override
										public void effects() {
											Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
										}
									};
								}
								
							} else {
								return null;
							}
							
						} else {
							if((!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
									&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
								return new Response("献出身体",
										"维姬需要能接触你的"
											+ (Main.game.isAnalContentEnabled()?"肛门":"")
											+ (Main.game.getPlayer().hasVagina()?(Main.game.isAnalContentEnabled()?"或":"")+"阴道":"")+"！",
										null);
								
							} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
								return new Response("献出身体",
										"维姬今天已经操过你了，没时间继续跟你纠缠……",
										null);
								
							} else {
								return new ResponseSex("献上身体", "让维姬使用你的身体。",
										Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
										true, false,
										new SMVickyOverDesk(
												Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))),
										null,
										null,
										getPostSexScene(),
										UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_OFFER_BODY"));
							}
						}
						
					} else if (index == 10 && Main.getProperties().hasValue(PropertyValue.nonConContent) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursPackageObtained)) {
						if((!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
								&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
							return new Response("不安地离开",
									"维姬需要能接触你的"
										+ (Main.game.isAnalContentEnabled()?"肛门":"")
										+ (Main.game.getPlayer().hasVagina()?(Main.game.isAnalContentEnabled()?"或":"")+"阴道":"")+"！",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
							return new Response("不安地离开",
									"维姬今天已经操过你了，没时间继续跟你纠缠……",
									null);
							
						} else {
							return new ResponseSex("不安地离开",
									"维姬吓坏你了……转身从她的注视中逃离吧。"
											+ "<br/>[style.boldBad(你觉得这样会导致一场非自愿的性爱……)]",
									Util.newArrayListOfValues(
											Fetish.FETISH_SUBMISSIVE,
											Fetish.FETISH_NON_CON_SUB), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
									false, false,
									new SMVickyOverDesk(
											Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))) {
										@Override
										public SexPace getStartingSexPaceModifier(GameCharacter character) {
											if(character.isPlayer()) {
												return SexPace.SUB_RESISTING;
											}
											return null;
										}
									},
									null,
									null,
									VICKY_POST_SEX_RAPE,
									UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_RAPE"));
						}
					}
				}
				
			} else if(responseTab==1) {
				if(index==1) { // Offer cunnilingus:
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("主动舔阴",
								"你需要能够使用嘴巴才能舔维姬的下面！",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
						return new Response("主动舔阴",
								"维姬今天已经操过你了，没时间继续跟你纠缠……",
								null);
						
					} else {
						return new ResponseSex("主动舔阴", "告诉维姬你想舔她下面。",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVicky(
										isInApartment()
											?SexPosition.SITTING
											:SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(getVicky(), isInApartment()?SexSlotSitting.SITTING:SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), isInApartment()?SexSlotSitting.PERFORMING_ORAL:SexSlotStanding.PERFORMING_ORAL)),
										new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										Util.newArrayListOfValues(CoverableArea.MOUTH),
										Util.newArrayListOfValues(CoverableArea.VAGINA)),
								null,
								null,
								getPostSexScene(),
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_OFFER_CUNNILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getVicky(), TongueVagina.CUNNILINGUS_START, false, true));
							}
						};
					}
				}
				if(index==2) { // Offer blowjob:
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("主动口交",
								"你需要能够使用嘴巴才能给维姬口交！",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
						return new Response("主动口交",
								"维姬今天已经操过你了，没时间继续跟你纠缠……",
								null);
						
					} else {
						return new ResponseSex("主动口交", "告诉维姬你想给她口交。",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVicky(
										isInApartment()
											?SexPosition.SITTING
											:SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(getVicky(), isInApartment()?SexSlotSitting.SITTING:SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), isInApartment()?SexSlotSitting.PERFORMING_ORAL:SexSlotStanding.PERFORMING_ORAL)),
										new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										Util.newArrayListOfValues(CoverableArea.MOUTH),
										Util.newArrayListOfValues(CoverableArea.PENIS)),
								null,
								null,
								getPostSexScene(),
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_OFFER_BLOWJOB")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getVicky(), PenisMouth.GIVING_BLOWJOB_START, false, true));
							}
						};
					}
				}
				if(index==3) { // Offer pussy:
					if(!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						return new Response("献上小穴",
								!Main.game.getPlayer().hasVagina()
									?"你没有阴道……"
									:"维姬需要能够使用你的阴道才能干你！",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
						return new Response("献上小穴",
								"维姬今天已经操过你了，没时间继续跟你纠缠……",
								null);
						
					} else {
						return new ResponseSex("献上小穴", "让维姬操你的小穴。",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVicky(
										SexPosition.OVER_DESK,
										Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT)),
										new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
										new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
										Util.newArrayListOfValues(CoverableArea.VAGINA),
										Util.newArrayListOfValues(CoverableArea.PENIS)),
								null,
								null,
								getPostSexScene(),
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_OFFER_PUSSY")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							}
						};
					}
				}
				if(index==4 && Main.game.isAnalContentEnabled()) { // Offer ass:
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						return new Response("献上屁股",
								"维姬需要能够使用你的屁眼才能干你！",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
						return new Response("献上屁股",
								"维姬今天已经操过你了，没时间继续跟你纠缠……",
								null);
						
					} else {
						return new ResponseSex("献上屁股", "让维姬操你的屁股。",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVicky(
										SexPosition.OVER_DESK,
										Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT)),
										new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
										new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
										Util.newArrayListOfValues(CoverableArea.VAGINA),
										Util.newArrayListOfValues(CoverableArea.ANUS)),
								null,
								null,
								getPostSexScene(),
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_OFFER_ASS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
							}
						};
					}
				}
				if(Main.game.isAnalContentEnabled()?index==5:index==4) {
					if(!Main.game.getPlayer().hasPenis() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						return new Response("献上鸡巴",
								!Main.game.getPlayer().hasPenis()
									?"你没有阴茎，所以无法献给维姬！"
									:"维姬需要能够使用你的阴茎！",
								null);
						
					} else if(!getVicky().getAffectionLevel(Main.game.getPlayer()).isGreaterThan(AffectionLevel.POSITIVE_TWO_LIKE)) {
						return new Response("献上鸡巴",
								"维姬对你的好感还不够，不愿意让你的鸡巴上她的身……"
								+ "<br/>[style.italicsMinorBad(需要维姬对你的好感至少达到"+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()
									+"，目前为"+((int)getVicky().getAffection(Main.game.getPlayer()))+"！)]",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
						return new Response("献上鸡巴",
								"维姬今天已经操过你了，没时间继续跟你纠缠……",
								null);
						
					} else {
						return new Response("献上鸡巴",
								"询问维姬你能否使用自己的肉棒。",
								VICKY_PET_OFFER_COCK) {
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
				}
				// Non-sex:
				if(index==6) {
					if(Main.game.getHourOfDay()>15) {
						return new Response("提供午饭",
								"时间太晚了，不能给维姬提供午饭……"
								+ "<br/>[style.italicsMinorBad(她只会在[style.time(16)]前吃饭。)]",
								null);
						
					} else if(!Main.game.getPlayer().hasItemType("innoxia_race_wolf_meat_and_marrow")
							&& !Main.game.getPlayer().hasItemType("innoxia_race_panther_panthers_delight")) {
						return new Response(
								Main.game.isMorning()
									?"提供早饭"
									:"提供午饭",
								"维姬只喜欢吃肉，你需要拥有“"
									+ItemType.getItemTypeFromId("innoxia_race_wolf_meat_and_marrow").getName(false)
									+"”或者“"
									+ItemType.getItemTypeFromId("innoxia_race_panther_panthers_delight").getName(false)
									+"”才能供她食用……",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_fed")) {
							return new Response(
								Main.game.isMorning()
									?"提供早饭"
									:"提供午饭",
								"你今天已经给维姬提供过食物了，如果还想再这么做需要等到明天……",
								null);
							
					} else {
						AbstractItemType itemTypeBeingOffered = ItemType.getItemTypeFromId("innoxia_race_wolf_meat_and_marrow");
						mealItemType = itemTypeBeingOffered;
						if(!Main.game.getPlayer().hasItemType("innoxia_race_wolf_meat_and_marrow")) {
							itemTypeBeingOffered = ItemType.getItemTypeFromId("innoxia_race_panther_panthers_delight");
							mealItemType = itemTypeBeingOffered;
						}
						return new Response(
								Main.game.isMorning()
									?"提供早饭"
									:"提供午饭",
								"将物品栏的“"+itemTypeBeingOffered.getName(false)+"”提供给维姬。",
								VICKY_PET_OFFER_MEAL);
					}
				}
				if(index==7) {
					if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_helped")) {
						return new Response("提供帮助",
								"你今天已经帮过维姬了，如果还想提供帮助需要等到明天……",
								null);
						
					} else {
						return new Response("提供帮助",
								"询问维姬你能帮她做什么。",
								VICKY_PET_OFFER_HELP);
					}
				}
				if(index==8) {
					if(!getVicky().getAffectionLevel(Main.game.getPlayer()).isGreaterThan(AffectionLevel.POSITIVE_TWO_LIKE)) {
						return new Response("夜间陪伴",
								"维姬还不够喜欢你，不愿意晚上让你陪伴……"
								+ "<br/>[style.italicsMinorBad(需要维姬对你的好感至少达到"+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()
									+"，目前为"+((int)getVicky().getAffection(Main.game.getPlayer()))+"！)]",
								null);
						
					} else if(Main.game.getHourOfDay()<16) {
						return new Response("夜间陪伴",
								"时间太早了，还不能给维姬提供夜间陪伴……"
								+ "<br/>[style.italicsMinorBad(她只会在[style.time(16)]后才会考虑这个建议。)]",
								null);
						
					} else {
						return new Response("夜间陪伴",
								"询问维姬你是否能在今晚给她一些陪伴。",
								VICKY_PET_OFFER_COMPANY);
					}
				}
				if (index == 10 && Main.getProperties().hasValue(PropertyValue.nonConContent)) {
					if((!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
							&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
						return new Response("不安地离开",
								"维姬需要能接触你的"
									+ (Main.game.isAnalContentEnabled()?"肛门":"")
									+ (Main.game.getPlayer().hasVagina()?(Main.game.isAnalContentEnabled()?"或":"")+"阴道":"")+"！",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
						return new Response("不安地离开",
								"维姬今天已经操过你了，没时间继续跟你纠缠……",
								null);
						
					} else {
						return new ResponseSex("不安地离开",
								"维姬吓坏你了……转身从她的注视中逃离吧。"
								+ "<br/>[style.boldBad(你觉得这样会导致一场非自愿的性爱……)]",
								Util.newArrayListOfValues(
										Fetish.FETISH_SUBMISSIVE,
										Fetish.FETISH_NON_CON_SUB), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
								false, false,
								new SMVickyOverDesk(
										Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))) {
									@Override
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(character.isPlayer()) {
											return SexPace.SUB_RESISTING;
										}
										return null;
									}
								},
								null,
								null,
								VICKY_POST_SEX_RAPE,
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_RAPE"));
					}
				}
			}
			
			
			return null;
		}
	};
	
	public static final DialogueNode ARTHURS_PACKAGE = new DialogueNode("奥术艺术", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()>=100) {
					return new Response("支付("+UtilText.formatAsMoney(100, "span")+")", "支付维姬100火币。", ARTHURS_PACKAGE_BOUGHT) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.arthursPackageObtained, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.ARTHURS_PACKAGE), false, true));
							Main.game.getPlayer().incrementMoney(-100);
						}
					};
				} else {
					return new Response("支付("+UtilText.formatAsMoneyUncoloured(100, "span")+")", "你的钱不够支付费用！", null);	
				}
				
			} else if (index == 2) {
				if((!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
						&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
					return new Response("献出身体",
							"维姬需要能接触你的"
								+ (Main.game.isAnalContentEnabled()?"肛门":"")
								+ (Main.game.getPlayer().hasVagina()?(Main.game.isAnalContentEnabled()?"或":"")+"阴道":"")+"！",
							null);
					
				} else {
					return new ResponseSex("献上身体", "让维姬用你的身体代替费用。",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
							true, false,
							new SMVickyOverDesk(
									Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))),
							null,
							null,
							VICKY_POST_SEX_PACKAGE,
							UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE_SEX"));
					
				}
				
			} else if (index == 3 && Main.getProperties().hasValue(PropertyValue.nonConContent)) {
				if((!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
						&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
					return new Response("弱气地拒绝",
							"维姬需要能接触你的"
								+ (Main.game.isAnalContentEnabled()?"肛门":"")
								+ (Main.game.getPlayer().hasVagina()?(Main.game.isAnalContentEnabled()?"或":"")+"阴道":"")+"！",
							null);
					
				} else {
					return new ResponseSex(
							"弱气地拒绝",
							"面对着如此恐怖的角色，你拒绝不出口……试图挣脱离开……"
									+ "<br/>[style.boldBad(你觉得这样会导致一场非自愿的性爱……)]",
							Util.newArrayListOfValues(
									Fetish.FETISH_SUBMISSIVE,
									Fetish.FETISH_NON_CON_SUB),
							null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							false, false,
							new SMVickyOverDesk(
									Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							null,
							VICKY_POST_SEX_RAPE_PACKAGE,
							UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE_RAPE"));
				}
				
			} else if (index == 0) {
				return new Response("离开", "离开奥术艺术，回到购物中心。", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE_LEAVE"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ARTHURS_PACKAGE_BOUGHT = new DialogueNode("奥术艺术", "-", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE_BOUGHT");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode VICKY_POST_SEX_PACKAGE = new DialogueNode("奥术艺术", "-", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_daily_sex", true);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_POST_SEX_PACKAGE");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VICKY_POST_SEX_RAPE_PACKAGE = new DialogueNode("奥术艺术", "-", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_daily_sex", true);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_POST_SEX_RAPE_PACKAGE");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VICKY_POST_SEX = new DialogueNode("结束", "维姬爽够了……", true) {
		@Override
		public void applyPreParsingEffects() {
			vickyHadSex = true;
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_daily_sex", true);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_POST_SEX");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VICKY_POST_SEX_RAPE = new DialogueNode("结束", "维姬爽够了……", true) {
		@Override
		public void applyPreParsingEffects() {
			vickyHadSex = true;
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_daily_sex", true);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_POST_SEX_RAPE");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VICKY_CLAIMED_AFTER_SEX = new DialogueNode("结束", "维姬跟你搞完了……", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_claimed", true);
			if(Main.game.isMuskContentEnabled()) {
				Main.game.getPlayer().addMuskMarkerCharacter(getVicky()); // Just to make sure that the player was marked
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_CLAIMED_AFTER_SEX");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VICKY_REFUSED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_REFUSED");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	// Vicky dominated player content:

	public static final DialogueNode VICKY_PET_OFFER_COCK = new DialogueNode("奥术艺术", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COCK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("手淫", "让维姬帮你手淫。",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, false,
						new SMVicky(
								isInApartment()
									?SexPosition.SITTING
									:SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(getVicky(), isInApartment()?SexSlotSitting.SITTING:SexSlotAgainstWall.STANDING_WALL)), // TODO test positions interaction
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), isInApartment()?SexSlotSitting.SITTING_TWO:SexSlotAgainstWall.BACK_TO_WALL)),
								new SexType(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS),
								new SexType(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								null),
						null,
						null,
						getPostSexScene(),
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COCK_HANDJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATING_START, false, true));
					}
				};
			}
			if(index==2) {
				return new ResponseSex("口交", "让维姬给你口交。",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, false,
						new SMVicky(
								isInApartment()
									?(Main.game.getPlayer().isTaur()?SexPosition.STANDING:SexPosition.SITTING)
									:SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(getVicky(), isInApartment()?(Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotSitting.PERFORMING_ORAL):SexSlotAgainstWall.PERFORMING_ORAL_WALL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), isInApartment()?(Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING):SexSlotAgainstWall.BACK_TO_WALL)),
								new SexType(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
								new SexType(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								null),
						null,
						null,
						getPostSexScene(),
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COCK_BLOWJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
			}
			if(index==3) {
				return new ResponseSex("小穴", "告诉维姬你想插进她的小穴里。",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, false,
						new SMVicky(
								SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotLyingDown.COWGIRL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
								new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
								new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newArrayListOfValues(CoverableArea.VAGINA)),
						null,
						null,
						getPostSexScene(),
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COCK_PUSSY")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
					}
				};
			}
			if(index==4 && Main.game.isAnalContentEnabled()) {
				return new ResponseSex("后穴", "告诉维姬你想操她屁股。",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, false,
						new SMVicky(
								SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotLyingDown.COWGIRL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
								new SexType(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
								new SexType(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newArrayListOfValues(CoverableArea.ANUS)),
						null,
						null,
						getPostSexScene(),
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COCK_ANAL")) { //TODO virginity loss handling
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
					}
				};
			}
			if(Main.game.isFootContentEnabled() && (Main.game.isAnalContentEnabled()?index==5:index==4)) {
				return new ResponseSex("足交", "告诉维姬你想让她给你足交。",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, false,
						new SMVicky(
								isInApartment()
									?SexPosition.SITTING
									:SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(getVicky(), isInApartment()?SexSlotSitting.SITTING:SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), isInApartment()?SexSlotSitting.PERFORMING_ORAL:SexSlotStanding.PERFORMING_ORAL)),//TODO test sitting position
								new SexType(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS),
								new SexType(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newArrayListOfValues(CoverableArea.FEET)),
						null,
						null,
						getPostSexScene(),
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COCK_FOOTJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), PenisFoot.FOOT_JOB_SINGLE_GIVING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VICKY_PET_OFFER_MEAL = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().removeItemByType(mealItemType, 1, true);
			mealResponseIndex = Util.random.nextInt(mealResponses.size());
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_fed", true);
			if(mealResponseIndex==0) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_DEMAND_FEED_HER"));
			} else if(mealResponseIndex==1) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_DEMAND_PETTINGS"));
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_DEMAND_MASSAGE"));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(mealItemType.getName(false), true);
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(mealResponses.get(mealResponseIndex).getKey(),
						mealResponses.get(mealResponseIndex).getValue(),
						VICKY_PET_OFFER_MEAL_END);
			}
			return null;
		}
	};

	public static final DialogueNode VICKY_PET_OFFER_MEAL_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(getVicky().incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			UtilText.addSpecialParsingString(mealItemType.getName(false), true);
			if(mealResponseIndex==0) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_FEED_HER"));
			} else if(mealResponseIndex==1) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_PETTINGS"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_MASSAGE"));
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_END"));
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VICKY_PET_OFFER_HELP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			helpResponseIndex = Util.random.nextInt(helpResponses.size());
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_helped", true);
			if(helpResponseIndex==0) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_DEMAND_STOCK_SHLEVES"));
			} else if(helpResponseIndex==1) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_DEMAND_SWEEP_FLOOR"));
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_DEMAND_ORGANISE_WEAPONS"));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(helpResponses.get(helpResponseIndex).getKey(),
						helpResponses.get(helpResponseIndex).getValue(),
						VICKY_PET_OFFER_HELP_END);
			}
			return null;
		}
	};

	public static final DialogueNode VICKY_PET_OFFER_HELP_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(getVicky().incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(helpResponseIndex==0) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_STOCK_SHLEVES"));
			} else if(helpResponseIndex==1) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_SWEEP_FLOOR"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_ORGANISE_WEAPONS"));
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_END"));
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VICKY_PET_OFFER_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VICKY_PET_OFFER_COMPANY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COMPANY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待",
						"等维姬下班……",
						VICKY_PET_OFFER_COMPANY_APARTMENT);
			}
			return null;
		}
	};

	public static final DialogueNode VICKY_PET_OFFER_COMPANY_APARTMENT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_apartment", true);
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_daily_sex", false); // So that sex options are available after the shower
			Main.game.getTextEndStringBuilder().append(getVicky().incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(18 * 60) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COMPANY_APARTMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("淋浴", "跟维姬一起淋浴。",
						true, false,
						new SMVicky(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE)),
								null, null, null, null) {
							@Override
							public boolean isWashingScene() {
								return true;
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return true;
							}
							@Override
							public List<AbstractSexPosition> getAllowedSexPositions() {
								return Util.newArrayListOfValues(
										SexPosition.STANDING,
										SexPosition.AGAINST_WALL);
							}
						},
						null,
						null,
						VICKY_APARTMENT_POST_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COMPANY_APARTMENT_SHOWER_SEX"));
			}
			return null;
		}
	};
	
	public static final DialogueNode VICKY_APARTMENT_POST_SHOWER_SEX = new DialogueNode("结束", "维姬爽够了……", true) {
		@Override
		public void applyPreParsingEffects() {
			getVicky().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240);
			getVicky().equipUnderwear();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_APARTMENT_POST_SHOWER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("聊天",
						"跟维姬放松地聊会儿天……",
						VICKY_APARTMENT_SLEEP);
			} else if(index<=6) {
				return SHOP_WEAPONS.getResponse(1, index-1);
			}
			return null;
		}
	};

	public static final DialogueNode VICKY_APARTMENT_POST_SEX = new DialogueNode("结束", "维姬爽够了……", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_APARTMENT_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("聊天",
						"跟维姬放松地聊会儿天……",
						VICKY_APARTMENT_SLEEP);
			}
			return null;
		}
	};
	
	public static final DialogueNode VICKY_APARTMENT_SLEEP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_APARTMENT_SLEEP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("睡觉",
						"看起来维姬不会给你选择，你今晚必须在她那里过夜了……",
						VICKY_APARTMENT_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode VICKY_APARTMENT_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(7*60) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_APARTMENT_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开",
						"跟维姬道别，回到购物中心。",
						VICKY_APARTMENT_END_LEAVE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag("innoxia_vicky_apartment", false);
						Main.game.getPlayer().setNearestLocation(PlaceType.SHOPPING_ARCADE_PATH);
						getVicky().equipClothing();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VICKY_APARTMENT_END_LEAVE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_APARTMENT_END_LEAVE");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return PlaceType.SHOPPING_ARCADE_PATH.getDialogue(false).getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.SHOPPING_ARCADE_PATH.getDialogue(false).getResponse(responseTab, index);
		}
	};
}
