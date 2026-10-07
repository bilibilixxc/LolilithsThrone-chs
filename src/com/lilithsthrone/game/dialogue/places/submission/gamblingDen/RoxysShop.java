package com.lilithsthrone.game.dialogue.places.submission.gamblingDen;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.submission.Roxy;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.Dice;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DiceFace;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMRoxyPussyLicker;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
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
 * @version 0.3.21
 * @author Innoxia, DSG
 */
public class RoxysShop {
    
        public static final String REBEL_BASE_ROXY_TIMER = "rebel_base_roxy_timer";

	private static boolean isAddictedToRoxy() {
		Addiction ratGCumAdd = Main.game.getPlayer().getAddiction(FluidType.GIRL_CUM_RAT_MORPH);
		return ratGCumAdd!=null && ratGCumAdd.getProviderIDs().contains(Main.game.getNpc(Roxy.class).getId());
	}
	
	public static final DialogueNode TRADER_EXTERIOR = new DialogueNode("罗克西的妙妙盒", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_EXTERIOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进入", "[pc.Step]进“罗克西妙妙屋”并环顾四周……", TRADER);
			}
			return null;
		}
	};
	
	public static final DialogueNode TRADER = new DialogueNode("罗克西的妙妙盒", "", true) {
		@Override
		public void applyPreParsingEffects() {
			ItemEffectType.CIGARETTE.applyEffect(null, null, null, 0, Main.game.getNpc(Roxy.class), Main.game.getNpc(Roxy.class), null);
		}
		@Override
		public String getContent() {
			if(Main.game.getNpc(Vengar.class).isSlave() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyVengarOwnerIntroduced)) {
				StringBuilder sb = new StringBuilder();
				sb.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_VENGAR_INTRO_START"));
				if(isAddictedToRoxy()) {
					sb.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_VENGAR_INTRO_ADDICTED"));
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyAddicted)) {
					sb.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_VENGAR_INTRO_BEATEN_ADDICTION"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_VENGAR_INTRO_NOT_ADDICTED"));
				}
				sb.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_VENGAR_INTRO_END"));
				return sb.toString();
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
				if(isAddictedToRoxy()) {
					return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_REPEAT_ADDICT");
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyAddicted)) {
					return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_REPEAT_BEATEN_ADDICTION");
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_REPEAT");
				}
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开", "离开罗克西的商店……", PlaceType.GAMBLING_DEN_CORRIDOR.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
						Main.game.getPlayer().setNearestLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_CORRIDOR, false);
					}
				};
				
			} else if (index == 1) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
					return new Response("拒绝", "告诉罗克西你只是想逛逛商店。", TRADER_REPLY_NO){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
						}
					};
					
				} else {
					return new ResponseTrade("交易", "跟罗克西交易。", Main.game.getNpc(Roxy.class)){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
						}
					};
				}
				
			} else if (index == 2) {
				if(isAddictedToRoxy()) { // Repeat oral:
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("平息戒断("+UtilText.formatAsMoneyUncoloured(1000, "span")+")",
								"只有能使用自己的嘴巴才能侍奉罗克西！",
								null);
						
					} else if(Main.game.getPlayer().getMoney()<1000) {
						return new Response("平息戒断("+UtilText.formatAsMoneyUncoloured(1000, "span")+")",
								"罗克西要求一千火币，你没有这么多！",
								null);
						
					} else {
						return new ResponseSex("平息戒断("+UtilText.formatAsMoney(1000, "span")+")",
								"你迫切地需要她那成瘾性爱液的缓解，于是同意<b>付给洛克斯1000火币</b>，让她坐在你的脸上一个小时。",
								Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMRoxyPussyLicker(
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Roxy.class), SexSlotLyingDown.FACE_SITTING)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
								null,
								null,
								AFTER_ROXY_SEX_ADDICT,
								UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "ROXY_SEX_START_ADDICT")){
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-1000));
								Main.game.getNpc(Roxy.class).incrementMoney(1000);
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Roxy.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						};
					}
					
				} else { // Perform oral:
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new ResponseSex("同意舔她", "同意让罗克西坐在你的脸上，让你给她舔到高潮，以此来换取一件随机商品。",
								Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMRoxyPussyLicker(
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Roxy.class), SexSlotLyingDown.FACE_SITTING)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
								null,
								null,
								AFTER_ROXY_SEX,
								UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "ROXY_SEX_START")){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Roxy.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						};
						
					} else {
						return new Response("同意舔她", "只有能使用自己的嘴巴才能侍奉罗克西！", null);
					}
				}
				
			} else if(index==3 && Main.game.getNpc(Vengar.class).isSlave()) {
				return new Response("文加", "询问罗克西你能不能跟文加谈谈。", VENGAR);
				
				
			} else if(index==4
//					&& Main.game.getDialogueFlags().values.contains(DialogueFlagValue.axelExplainedVengar)
					&& Main.game.getPlayer().hasQuest(QuestLine.SIDE_REBEL_BASE_FIREBOMBS)
					&& !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_REBEL_BASE_FIREBOMBS)
					&& !Main.game.getPlayer().isQuestFailed(QuestLine.SIDE_REBEL_BASE_FIREBOMBS)) {
				if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE_FIREBOMBS, Quest.REBEL_BASE_FIREBOMBS_FINISH)) {
					if(Main.game.getPlayer().hasWeaponType(WeaponType.getWeaponTypeFromId("dsg_hlf_weap_pbomb"), true)) {
						return new Response("燃烧弹", "给罗克西看看你拿来的燃烧弹，询问是否有渠道。<br/>[style.boldBad(你会失去一个燃烧弹。)] ", FIREBOMBS) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE_FIREBOMBS, Quest.REBEL_BASE_FIREBOMBS_FINISH));
								Main.game.getDialogueFlags().setSavedLong(REBEL_BASE_ROXY_TIMER, Main.game.getMinutesPassed());
								
								// Shuffle at least one instance of the arcane firebomb into the player's inventory if they've got one equipped but none in their inventory
								if (!Main.game.getPlayer().hasWeaponType(WeaponType.getWeaponTypeFromId("dsg_hlf_weap_pbomb"), false)) {
									int armRow = 0;
									boolean fireBombShuffled = false;
									for (AbstractWeapon weapon : Main.game.getPlayer().getMainWeaponArray()) {
										if (weapon.getWeaponType().equals(WeaponType.getWeaponTypeFromId("dsg_hlf_weap_pbomb"))) {
											Main.game.getPlayer().unequipMainWeapon(armRow, false, false);
											break;
										}
										armRow++;
									}
									if (!fireBombShuffled) {
										for (AbstractWeapon weapon : Main.game.getPlayer().getOffhandWeaponArray()) {
											if (weapon.getWeaponType().equals(WeaponType.getWeaponTypeFromId("dsg_hlf_weap_pbomb"))) {
												Main.game.getPlayer().unequipOffhandWeapon(armRow, false, false);
												break;
											}
											armRow++;
										}
									}
								}
								
								Main.game.getPlayer().removeWeapon(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("dsg_hlf_weap_pbomb")));
							}
						};
						
					} else {
						return new Response("燃烧弹",
								"你身上没带燃烧弹，只能用语言跟罗克西描述，希望对方能找到人复刻出来。"
										+ "[style.boldBad(不过最好还是带来一份实物。)]",
								FIREBOMBS_FAILED) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_BAD;
							}
						};
					}
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_REBEL_BASE_FIREBOMBS, Quest.REBEL_BASE_FIREBOMBS_START)) {
					if((Main.game.getMinutesPassed() - Main.game.getDialogueFlags().getSavedLong(REBEL_BASE_ROXY_TIMER)) < 2880) { // Roxy needs 2 days to get firebombs
						return new Response("燃烧弹", "罗克西还没有进货燃烧弹。", null);
					} else {
						return new Response("燃烧弹", "距离你询问罗克西燃烧弹的事情已经过了两天，该看看了。", FIREBOMBS_COMPLETE);
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode FIREBOMBS = new DialogueNode("罗克西的妙妙盒", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "FIREBOMBS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode FIREBOMBS_COMPLETE = new DialogueNode("罗克西的妙妙盒", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
               Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE_FIREBOMBS, Quest.SIDE_UTIL_COMPLETE));
            Main.game.getNpc(Roxy.class).addWeapon(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_pbomb"), 10, false, false);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "FIREBOMBS_COMPLETE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode FIREBOMBS_FAILED = new DialogueNode("罗克西的妙妙盒", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestFailed(QuestLine.SIDE_REBEL_BASE_FIREBOMBS, Quest.REBEL_BASE_FIREBOMBS_FAILED));
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "FIREBOMBS_FAILED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TRADER_REPLY_NO = new DialogueNode("罗克西的妙妙盒", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_REPLY_NO");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_ROXY_SEX = new DialogueNode("罗克西起身", "罗克西做够了，站起身来。根本不关心你到底爽没爽。", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "AFTER_ROXY_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("摇", "看罗克西摇骰子。", AFTER_ROXY_SEX_ITEM_OBTAINED) {
					@Override
					public void effects() {
						Dice d = new Dice(Util.newHashMapOfValues(new Value<>(DiceFace.ONE, 8f), new Value<>(DiceFace.TWO, 4f), new Value<>(DiceFace.THREE, 2f)));
						d.roll();
						int d1 = d.getFace().getValue();
						d.roll();
						int d2 = d.getFace().getValue();
						AbstractItem item;
						
						int dTotal = d1 + d2;
						if(dTotal<=3) {
							item = Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water");
						} else if(dTotal<=5) {
							item = Main.game.getItemGen().generateItem("innoxia_race_bat_fruit_bats_juice_box");
						} else if(dTotal<=7) {
							item = Main.game.getItemGen().generateItem(ItemType.MOTHERS_MILK);
						} else if(dTotal<=9) {
							item = Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum");
						} else if(dTotal<=11) {
							item = Main.game.getItemGen().generateItem("innoxia_race_rat_brown_rats_burger");
						} else {
							item = Main.game.getItemGen().generateItem("innoxia_race_human_bread_roll");
						}
						
						UtilText.addSpecialParsingString(Util.intToString(d1), true);
						UtilText.addSpecialParsingString(Util.intToString(d2), false);
						UtilText.addSpecialParsingString(Util.intToString(dTotal), false);
						UtilText.addSpecialParsingString(item.getName(true, false), false);
						UtilText.addSpecialParsingString(item.getName(), false);
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "AFTER_ROXY_SEX_DICE_ROLL"));
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(item, false));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_ROXY_SEX_ITEM_OBTAINED = new DialogueNode("罗克西的妙妙盒", "", false, true) {
		
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
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_ROXY_SEX_ADDICT = new DialogueNode("罗克西起身", "罗克西做够了，站起身来。根本不关心你到底爽没爽。", false) {

		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "AFTER_ROXY_SEX_ADDICT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	
	private static int VENGAR_SUB_SEX_COST = 1000;
	private static int VENGAR_SUB_DOM_COST = 1500;
	
	public static final DialogueNode VENGAR = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "VENGAR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<VENGAR_SUB_DOM_COST) {
					return new Response("开干("+UtilText.formatAsMoney(VENGAR_SUB_DOM_COST, "span")+")", "你的钱不够支付跟文加来一场支配型性爱的费用。", null);
				}
				return new ResponseSex(
						"开干("+UtilText.formatAsMoney(VENGAR_SUB_DOM_COST, "span")+")",
						"告诉罗克西你想付钱操文加一顿。",
						true,
						false,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer()),
								Util.newArrayListOfValues(
										Main.game.getNpc(Vengar.class)),
								null,
								null){
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(targetedCharacter.hasPenis()) {
										if(Main.game.isAnalContentEnabled()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
										}
									}
								}
								return super.getMainSexPreference(character, targetedCharacter);
							}
						},
						AFTER_VENGAR_SEX,
						UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "VENGAR_SEX_DOM")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-VENGAR_SUB_DOM_COST));
						Main.game.getNpc(Roxy.class).incrementMoney(VENGAR_SUB_DOM_COST);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getMoney()<VENGAR_SUB_SEX_COST) {
					return new Response("被干("+UtilText.formatAsMoney(VENGAR_SUB_SEX_COST, "span")+")", "你的钱不够支付跟文加来一场服从型性爱的费用。", null);
				}
				return new ResponseSex(
						"被干("+UtilText.formatAsMoney(VENGAR_SUB_SEX_COST, "span")+")",
						"告诉罗克西你想付钱被文加操一顿。",
						true,
						false,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(
										Main.game.getPlayer()),
								null,
								null){
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vengar.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(targetedCharacter.hasVagina()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
									} else if(Main.game.isAnalContentEnabled()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
									}
								}
								return super.getMainSexPreference(character, targetedCharacter);
							}
						},
						AFTER_VENGAR_SEX,
						UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "VENGAR_SEX_SUB")) {
					@Override
					public void effects() {
						AbstractClothing cage = Main.game.getNpc(Vengar.class).getClothingInSlot(InventorySlot.PENIS);
						if(cage!=null) {
							Main.game.getNpc(Vengar.class).forceUnequipClothingIntoVoid(Main.game.getNpc(Roxy.class), cage);
						}
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-VENGAR_SUB_SEX_COST));
						Main.game.getNpc(Roxy.class).incrementMoney(VENGAR_SUB_SEX_COST);
					}
				};
				
			} else if(index==3 && !Main.game.getNpc(Vengar.class).isFeminine()) {
				if(Main.game.getPlayer().getEssenceCount()<100 || !Main.game.getPlayer().hasItemType(ItemType.FETISH_UNREFINED)) {
					return new Response("娘化",
							"告诉罗克西，如果把文加变成个姑娘就能让他更听话些。"
							+ "<br/>要求: "
							+ (Main.game.getPlayer().getEssenceCount()<100?"[style.italicsBad(":"[style.italicsGood(")
							+"至少100精华)]以及"
							+ (Main.game.getPlayer().hasItemType(ItemType.FETISH_UNREFINED)
									?"[style.italicsBad("
									:"[style.italicsGood(")
								+"[#ITEM_FETISH_UNREFINED.getDeterminer()][#ITEM_FETISH_UNREFINED.getName(false)])]。",
							null);
				}
				return new Response("娘化",
						"告诉罗克西，如果把文加变成个姑娘就能让他更听话些。"
						+ "<br/>将消耗: [style.italicsArcane(100精华)]以及[style.italicsMinorGood(一瓶[#ITEM_FETISH_UNREFINED.getName(false)])]。",
						VENGAR_SISSIFY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "VENGAR_SISSIFY"));
						Main.game.getTextStartStringBuilder().append(((Vengar)Main.game.getNpc(Vengar.class)).applySissification());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "VENGAR_SISSIFY_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(-100, false));
						Main.game.getPlayer().removeItemByType(ItemType.FETISH_UNREFINED);
						Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>[style.italicsMinorBad(你失去了<b>一瓶</b>[#ITEM_FETISH_UNREFINED.getName(false)]！)]</p>");
					}
				};
				
			} else if(index==0) {
				return new Response("返回", "决定不对文加做什么。", TRADER);
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_VENGAR_SEX = new DialogueNode("结束", "你和文加做完了……", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Vengar.class).equipClothing();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.isDom(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "AFTER_VENGAR_SEX_AS_DOM");
			}
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "AFTER_VENGAR_SEX_AS_SUB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VENGAR_SISSIFY = new DialogueNode("", "", true) {
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
			return VENGAR.getResponse(responseTab, index);
		}
	};
}
