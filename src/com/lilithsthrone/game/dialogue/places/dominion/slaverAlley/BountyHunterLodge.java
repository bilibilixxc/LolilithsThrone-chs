package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.SupplierLeader;
import com.lilithsthrone.game.character.npc.dominion.SupplierPartner;
import com.lilithsthrone.game.character.npc.fields.Angelixx;
import com.lilithsthrone.game.character.npc.fields.Sleip;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.KaysWarehouse;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class BountyHunterLodge {

	private static final float BARMAN_BUY_MODIFIER = 0.75f;
	
	private static final Map<String, String> barConsumablesMap = Util.newHashMapOfValues(
			new Value<>("innoxia_race_squirrel_round_nuts", "BAR_NUTS"),
			new Value<>("innoxia_race_dog_canine_crush", "BAR_BEER"),
			new Value<>("innoxia_race_horse_equine_cider", "BAR_CIDER"),
			new Value<>("dsg_race_bear_vodka", "BAR_VODKA"),
			new Value<>("innoxia_race_wolf_wolf_whiskey", "BAR_WHISKEY"),
			new Value<>("innoxia_race_rat_black_rats_rum", "BAR_RUM"));

	private static Response getDobermannsUpstairsSexResponse(int startIndex, int index, String title) {
		if(index == startIndex) {
			title = title.replaceAll("sex_type_replacement", "口交");
			
			if(!KaysWarehouse.isPlayerMouthFree()) {
				return new Response(title,
						"你无法使用嘴巴，所以也就不能给这两个杜宾口交……",
						null);
				
			} else {
				return KaysWarehouse.getDobermannsSexResponse(SexPosition.SITTING,
						SexSlotSitting.SITTING, SexAreaOrifice.MOUTH,
						SexSlotSitting.SITTING, SexAreaPenetration.FINGER,
						SexSlotSitting.PERFORMING_ORAL,
						title,
						"主动提出舔这两个杜宾男的鸡巴……",
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEX_OFFER_ORAL"));
			}
			
		} else if(index == startIndex+1) {
			title = title.replaceAll("sex_type_replacement", "串肉串");
			
			if(!KaysWarehouse.isPlayerMouthFree()) {
				return new Response(title,
						"你无法使用自己的嘴巴，所以不能跟杜宾兄弟玩“串肉串”……",
						null);
				
			} else if(!KaysWarehouse.isPlayerAssFree() && !KaysWarehouse.isPlayerVaginaFree()) {
				return new Response(title,
						"你无法使用自己的屁股"+(Main.game.getPlayer().hasVagina()?"或小穴":"")+"，所以不能跟杜宾兄弟玩“串肉串”……",
						null);
				
			} else {
				return KaysWarehouse.getDobermannsSexResponse(SexPosition.ALL_FOURS,
						SexSlotAllFours.BEHIND, KaysWarehouse.isPlayerVaginaFree()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS,
						SexSlotAllFours.IN_FRONT, SexAreaOrifice.MOUTH,
						SexSlotAllFours.ALL_FOURS,
						title,
						"主动提出让杜宾兄弟跟你玩“串肉串”……",
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEX_OFFER_SPITROAST"));
			}
			
		} else if(index == startIndex+2) {
			title = title.replaceAll("sex_type_replacement", "骑乘");
			
			if(!KaysWarehouse.isPlayerAssFree() && !KaysWarehouse.isPlayerVaginaFree()) {
				return new Response(title,
						"你无法使用后穴"+(Main.game.getPlayer().hasVagina()?"或小穴":"")+"，所以无法跟这两个杜宾骑乘……",
						null);
				
			} else {
				return KaysWarehouse.getDobermannsSexResponse(SexPosition.LYING_DOWN,
						SexSlotLyingDown.LYING_DOWN, KaysWarehouse.isPlayerVaginaFree()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS,
						SexSlotLyingDown.MISSIONARY, KaysWarehouse.isPlayerAssFree()?SexAreaOrifice.ANUS:SexAreaOrifice.VAGINA,
						SexSlotLyingDown.COWGIRL,
						title,
						"主动提出跟这两个杜宾来骑乘位……",
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEX_OFFER_RIDE"));
			}
			
		} else if(index == startIndex+3 && Main.game.isNipplePenEnabled()) {
			title = title.replaceAll("sex_type_replacement", "操乳头");
			
			if(!KaysWarehouse.isPlayerNippleFuckFree()) {
				return new Response(title,
						Main.game.getPlayer().isBreastFuckableNipplePenetration()
							?"由于你无法使用足以插入的乳头，所以不能提供给杜宾兄弟……"
							:"由于你没有足以插入的乳头，所以不能提供给杜宾兄弟……",
						null);
				
			} else {
				return KaysWarehouse.getDobermannsSexResponse(SexPosition.STANDING,
						SexSlotStanding.STANDING_DOMINANT, SexAreaOrifice.NIPPLE,
						SexSlotStanding.STANDING_DOMINANT_TWO, SexAreaOrifice.NIPPLE,
						SexSlotStanding.PERFORMING_ORAL,
						title,
						"主动提出让两个杜宾男来操你的乳头……",
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEX_OFFER_NIPPLES"));
			}
		}
		return null;
	}
	
	public static final DialogueNode ENTRANCE_INITITAL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "ENTRANCE_INITITAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("离开", "退回到奴隶巷里。", PlaceType.SLAVER_ALLEY_BOUNTY_HUNTERS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "ENTRANCE_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_BOUNTY_HUNTERS, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FLOOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "FLOOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1 && Main.game.getCharactersPresent().contains(Main.game.getNpc(SupplierLeader.class))) {
				return new Response("杜宾们", "走上前去，跟沃尔夫冈和卡尔说些什么……", DOBERMANNS);
			}
			return null;
		}
	};

	public static final DialogueNode DOBERMANNS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!KaysWarehouse.isSexAvailable()) {
					return new Response("诱惑", "由于你无法使用任何腔穴，所以不能诱惑这对杜宾兄弟……", null);
				}
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.dobermannDefeatSeduced) || Main.game.getNpc(SupplierLeader.class).getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>0) {
					return new Response("诱惑",
							"主动再让这对杜宾兄弟操你……"
								+ "<br/>"
								+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_DOMINANT)
										?"[style.italicsSexDom(由于你)]"
												+ "[style.italicsMinorGood(拥有"+Fetish.FETISH_DOMINANT.getName(Main.game.getPlayer())+"性癖)]"
												+ "[style.italicsSexDom(，你可以在接下来的性爱场景中选择支配或者服从。)]"
										:"[style.italicsSex(由于你)]"
												+ "[style.italicsMinorBad(未拥有"+Fetish.FETISH_DOMINANT.getName(Main.game.getPlayer())+"性癖)]"
												+ "[style.italicsSex(，所以只能在接下来的性爱场景中选择服从。)]"),
							DOBERMANNS_UPSTAIRS) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEDUCE_REPEAT"));
						}
					};
					
				} else {
					return new Response("诱惑",
							"诱惑这对杜宾兄弟跟你做爱……"
									+ "<br/>"
									+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_DOMINANT)
											?"[style.italicsSexDom(由于你)]"
													+ "[style.italicsMinorGood(拥有"+Fetish.FETISH_DOMINANT.getName(Main.game.getPlayer())+"性癖)]"
													+ "[style.italicsSexDom(，你可以在接下来的性爱场景中选择支配或者服从。)]"
											:"[style.italicsSex(由于你)]"
													+ "[style.italicsMinorBad(未拥有"+Fetish.FETISH_DOMINANT.getName(Main.game.getPlayer())+"性癖)]"
													+ "[style.italicsSex(，所以只能在接下来的性爱场景中选择服从。)]"),
							DOBERMANNS_UPSTAIRS,
							null,
							null,
							Util.newArrayListOfValues(Perk.MALE_ATTRACTION, Perk.OBJECT_OF_DESIRE),
							null,
							null) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEDUCE"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatSeduced, true);
						}
					};
				}
				
			} else if(index==2) {
				return new Response("离开", "从桌子旁走开，回到酒馆。", FLOOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_LEAVE"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DOBERMANNS_UPSTAIRS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE_UPSTAIRS, PlaceType.BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM_DOBERMANNS);
			Main.game.getNpc(SupplierLeader.class).setLocation(Main.game.getPlayer(), true);
			Main.game.getNpc(SupplierPartner.class).setLocation(Main.game.getPlayer(), true);
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
				return new ResponseSex("干爆他们",
						"你将沃尔夫冈和卡尔推倒，让他们并排摆出狗爬式的姿势，准备好一同享乐……",
						Util.newArrayListOfValues(Fetish.FETISH_DOMINANT),
						null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								null,
								null,
								ResponseTag.PREFER_DOGGY),
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_UPSTAIRS_FUCK_THEM"));
			}
			return getDobermannsUpstairsSexResponse(2, index, "服从(sex_type_replacement)");
		}
	};

	public static final DialogueNode DOBERMANNS_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("下楼", "走下楼梯回到旅店一楼……", STAIRS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_AFTER_SEX_DOWNSTAIRS"));
						Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE, PlaceType.BOUNTY_HUNTER_LODGE_STAIRS);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SEATING = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "SEATING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1 && Main.game.getCharactersPresent().contains(Main.game.getNpc(Silence.class))) {
				return new Response("默", "走过去跟默聊天……<br/>[style.italicsBad(有默和影参与的小任务后续将会添加！)]", null);
			}
			return null;
		}
	};
	
	public static final DialogueNode STAIRS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "STAIRS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("上楼", "走上楼梯，进入酒馆的二楼。", UPSTAIRS_STAIRS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "STAIRS_UPSTAIRS"));
						Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE_UPSTAIRS, PlaceType.BOUNTY_HUNTER_LODGE_UPSTAIRS_STAIRS);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode BOUNTY_BOARD = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "BOUNTY_BOARD");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("悬赏令", "仔细看一眼当前有什么悬赏。<br/>[style.italicsMinorBad(后续更新中添加！)]", null);
			}
			return null;
		}
	};
	
	public static final DialogueNode BAR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "BAR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("注册", "告诉应侍你想注册成为正式的赏金猎人。<br/>[style.italicsMinorBad(后续更新中添加！)]", null);
			}
			
			List<Response> responses = new ArrayList<>();
			
			for(Entry<String, String> entry : barConsumablesMap.entrySet()) {
				AbstractItem consumable = Main.game.getItemGen().generateItem(entry.getKey());
				int price = consumable.getPrice(BARMAN_BUY_MODIFIER);
				
				if(Main.game.getPlayer().getMoney()<price) {
					responses.add(new Response(consumable.getName()+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")",
							"你没有"+Util.intToString(price)+"所需的火币，不能选择"+consumable.getName(true, false)+"……",
							null));
					
				} else {
					responses.add(new Response(consumable.getName()+" ("+UtilText.formatAsMoney(price, "span")+")",
							"给出"+Util.intToString(price)+"火币，选择"+consumable.getName(true, false)+"。",
							BAR_CONSUME) {
						@Override
						public void effects() {
							UtilText.addSpecialParsingString(Util.intToString(price), true);
							UtilText.addSpecialParsingString(consumable.getName(), false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", entry.getValue()));
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().useItem(consumable, Main.game.getPlayer(), false, true));
						}
					});
				}
			}
			
			if(index>0 && index-2<responses.size()) {
				return responses.get(index-2);
			}
			
			return null;
		}
	};

	public static final DialogueNode BAR_CONSUME = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return BAR.getResponse(responseTab, index);
		}
	};
	
	// First floor:
	
	public static final DialogueNode UPSTAIRS_CORRIDOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode UPSTAIRS_STAIRS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_STAIRS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("下楼", "走下楼梯，回到旅店一楼。", STAIRS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_STAIRS_DOWNSTAIRS"));
						Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE, PlaceType.BOUNTY_HUNTER_LODGE_STAIRS);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode UPSTAIRS_ROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	// Content for Angelixx & sons was moved out of here, but these locations should still work fine
	
	public static final DialogueNode UPSTAIRS_ROOM_ANGELIXX = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).contains(Main.game.getNpc(Angelixx.class))) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_ROOM_ANGELIXX");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isHourBetween(9, 18)) {
					return new Response("安吉莉克丝",
							"敲敲门，与安吉莉克丝见面。"
							+ "<br/>[style.italicsMinorBad(不久后会添加！)]",
							null);
					
				} else {
					return new Response("安吉莉克丝",
							"安吉莉克丝这个时间不在这里……"
							+ "<br/><i>等到[units.time(9)]-[units.time(18)]之间再回来与安吉莉克丝见面。</i>",
							null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode UPSTAIRS_ROOM_SLEIP_NIR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).contains(Main.game.getNpc(Sleip.class))) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_ROOM_SLEIP_NIR");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isHourBetween(9, 18)) {
					return new Response("斯雷普和尼尔",
							"敲敲门，与斯雷普和尼尔见面。"
							+ "<br/>[style.italicsMinorBad(不久后会添加！)]",
							null);
					
				} else {
					return new Response("斯雷普和尼尔",
							"斯雷普和尼尔这个时间不在这里……"
							+ "<br/><i>等到[units.time(9)]-[units.time(18)]之间再回来与安吉莉克丝见面。</i>",
							null);
				}
			}
			return null;
		}
	};
	
	
}
