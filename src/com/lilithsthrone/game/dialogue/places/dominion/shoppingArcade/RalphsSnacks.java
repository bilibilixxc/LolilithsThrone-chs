package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.dominion.SMRalphDiscount;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.82
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RalphsSnacks {
	
	private static void resetDiscountCheck() {
		// if 3 days have passed, reset discount:
		if((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID)) >= (60*24*3)){
			Main.game.getDialogueFlags().ralphDiscount=0;
		}
	}
	
	public static final DialogueNode EXTERIOR = new DialogueNode("拉尔夫小吃店(外部)", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "EXTERIOR");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ShoppingArcadeDialogue.getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					if(!Main.game.isExtendedWorkTime()) {
						return new Response("进入", "“拉尔夫小吃店”现在打烊了。如果你想进去转转，得等营业时间再来。", null);
					}
					return new Response("进入", "进入拉尔夫小吃店。", INTERIOR){
						@Override
						public void effects() {
							resetDiscountCheck();
						}
					};
				}
			}
			return ShoppingArcadeDialogue.getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode INTERIOR = new DialogueNode("拉尔夫小吃店", "-", true) {

		@Override
		public String getContent() {
			
			if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.ralphIntroduced)) {
				return "<p>"
							+ "你推开门走了进去，一声欢迎的铃声响起，让你下意识向上瞧了一眼。"
							+ "[ralph.speech(你好啊！有什么事情说就是了！)]一位马男在柜台后面朝你喊道。"
						+ "</p>"
						+ "<p>"
							+ "你谢过了那马男，他应该就是拉尔夫，开始在商店里环顾四周。"
							+ "大多数商品都没什么特殊之处，就是各种随处可见的食物饮料。"
							+ "但真正让这家商店与众不同的，确是那些特别展示出来的注入奥术的消耗品。"
							+ "价格并没有标出来，只是有个标签上写着：“这些物品请向拉尔夫寻求帮助”。"
						+ "</p>";
				
			} else {
				UtilText.nodeContentSB.setLength(0);
				
				UtilText.nodeContentSB.append("<p>"
							+ "你再一次走进来，这个购物中心唯一售卖食品的地方。"
							+ "从外面看，这家店就像是一间旧式的糖果店，巨大的玻璃窗展示着形色各异的食物和饮料。"
							+ "“拉尔夫小吃店”几个字用金色的草书写着挂在入口正上，你推开门走了进去，一枚小铃铛叮铃作响，欢迎着你的到来。"
						+ "</p>"
						+ "<p>"
						+ (Main.game.getDialogueFlags().getSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID)>0
								?"你进入商店时，拉尔夫朝眨了眨眼，从那个是已经十分熟悉的柜台后喊道，"
									+"[ralph.speech(啊，这不是我最爱的老顾客吗！如果有什么要帮忙的，我想你知道该怎么问的！)]"
								:"[ralph.speech(又见面了！有什么事情说就是了！)]那个熟悉的马男又从柜台后朝你喊道。")
						+ "</p>"
						+ "<p>"
							+ "你谢过拉尔夫，开始在商店里打量起来。"
							+ "大多数商品都没什么特殊之处，就是各种随处可见的食物饮料。"
							+ "但真正让这家商店与众不同的，确是那些特别展示出来的注入奥术的消耗品。"
							+ "价格并没有标出来，只是有个标签上写着：“这些物品请向拉尔夫寻求帮助”。"
						+ "</p>");
					
				if(((Ralph)Main.game.getNpc(Ralph.class)).isDiscountActive()){
					UtilText.nodeContentSB.append("<p>"
									+ "<b>拉尔夫会给你</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+Main.game.getDialogueFlags().ralphDiscount+"%</b>的<b>折扣！</b>"
								+ "</p>");
				}
				
				return UtilText.nodeContentSB.toString();
				
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseTrade("跟拉尔夫交易", "问一下拉尔夫那些展示出来的特别消耗品。", Main.game.getNpc(Ralph.class)){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
						resetDiscountCheck();
					}
				};
				
			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("折扣", "询问拉尔夫能不能给你一点折扣。", INTERIOR_ASK_FOR_DISCOUNT){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
							resetDiscountCheck();
						}
					};
					
				} else {
					return new Response("折扣", "只有在能使用嘴巴的时候才能从拉尔夫那里取得折扣！", null);
					
				}

			} else if (index == 3
					&& Main.game.getPlayer().hasQuest(QuestLine.SIDE_BUYING_BRAX)
					&& Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_LIPSTICK
					&& !Main.game.getPlayer().hasItemType(ItemType.CANDI_HUNDRED_KISSES)) {
					return new Response("坎迪的口红",
							!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.ralphAskedAboutHundredKisses)
								?"询问拉尔夫有没有“百万之吻”，愿不愿意卖给你。"
								:"询问拉尔夫那盒“百万之吻”还卖不卖。",
							CANDI_LIPSTICK) {
						@Override
						public void effects() {
							resetDiscountCheck();
						}
					};

			} else if (index == 0) {
				return new Response("离开", "离开拉尔夫的商店。", EXTERIOR){
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
						resetDiscountCheck();
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_ASK_FOR_DISCOUNT = new DialogueNode("拉尔夫小吃店", "-", true, true) {
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().getSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID)>0){
				return "<p>"
						+"你望向商店的另一边，只见满面春光的拉尔夫正在柜台后向你招手。"
						+ "他巨大的马屌的样子从你的脑中一闪而过，那深深送入咽喉的感觉，让你不禁轻声呻吟出来……"
						+ "他宽大平坦的龟头在你的嘴唇间肆虐……那微咸的先走液的味道让你口水直流……"
					+ "</p>"
					+ "<p>"
						+"一个熟悉的声音突然将你从白日梦中唤醒，"
						+ "[ralph.speech(嘿，你没事吧？)]"
					+ "</p>"
					+ "<p>"
					 	+ "你深深沉迷于幻想之中，没有注意到拉尔夫已经走了过来。你一时间措手不及，不假思索地表示自己没事。"
					 	+ "你转身看到那肌肉充盈的马男时，发现他的眼神已经在你身上游走，你觉得他早就知道你在幻想什么了。"
						+ "你低头瞄了一眼，他的股间已经撑起了格外明显的帐篷，没等你对拉尔夫勃起的景象做出反应，他便已经迅速上前一步，把你按在了墙上。"
					+ "</p>"
					+ "<p>"
						+ "[ralph.speech(还想再尝尝吗？)]他狡猾地问道，接着笑得更加奸诈，身体也更近一分。"
						+ "你感受到他灼热的呼吸扑打在你的脸上，那马男继续道，"
						+ "[ralph.speech(那就让我在你可爱的喉咙里释放一下吧，这几天商店里所有的东西都给你七五折。)]"
					+ "</p>"
					+ "<p>"
						+ "拉尔夫又踏前一步，把他燥热的身子跟你紧贴在一起，他深黑色的巨大马屌颤动不已的样子，在你的脑海中挥之不去，"
							+ "他裤子上的帐篷已经碰到了你的腿，你的回应顿时脱口而出。"
					+ "</p>";
				
			}else{
				return "<p>"
							+ "你觉得商品的价格都被抬高了，远超人们认为的合理价格。"
							+ "你瞄向商店的另一边，只见满面春光的拉尔夫正在柜台后向你招手"
							+ "在他迷人的微笑和友好的面孔的激励下，你决定问一问他，有没有什么方法可以让你打折购买这些特殊的物品，于是向他走去。"
						+ "</p>"
						+ "<p>"
							+ "拉尔夫看到你走了过来，直接高声喊道，[ralph.speech(你好啊！我能帮到你什么吗？)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(你好，不知道你能不能给我看看这些物品的价格，)]你回应道。" 
						+ "</p>"
						+ "<p>"
							+ "拉尔夫兴高采烈地把你带回了特殊展柜前，告诉了你这些物品的价格，你的怀疑坐实了。"
							+ "虽然有些东西价格还算合适，但越是少见的食物就越是溢价严重。"
							+ "你看着这位友善的店主，便问他能不能稍稍降一下价格。"
						+ "</p>"
						+ "<p>"
							+ "[ralph.speech(哼嗯，)]他朝你扫了一眼，"
							+ "[ralph.speech(我觉得还是有办法的……)]"
						+ "</p>"
						+ "<p>"
							+ "你注意到他的笑容不再留有一丝善意，眼睛也在你身上来回游走起来，你觉得这马男一定是在想什么不好的事情。"
							+ "你低头瞄了一眼，他的股间已经撑起了格外明显的帐篷，没等你对拉尔夫勃起的景象做出任何反应，他便已经迅速上前一步，把你按在了墙上。"
						+ "</p>"
						+ "<p>"
							+ "[ralph.speech(想尝尝吗？)]他狡黠地问道，接着邪魅一笑，又凑近了些。"
							+ "你感受到他灼热的呼吸扑打在你的脸上，那马男继续道，"
							+ "[ralph.speech(那就让我在你可爱的喉咙里释放一下吧，这几天商店里所有的东西都给你七五折。)]"
						+ "</p>"
						+ "<p>"
							+ "拉尔夫又踏前一步，把他燥热的身子跟你紧贴在一起，他深黑色的巨大马屌颤动不已的样子，在你的脑海中挥之不去，"
								+ "他裤子上的帐篷已经碰到了你的腿，你的回应顿时脱口而出。"
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("同意", "按拉尔夫说的做，吮吸他的鸡巴。", Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING),
						null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, true,
						new SMRalphDiscount(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Ralph.class), SexSlotUnique.RALPH_DOM)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.RALPH_SUB))),
						null,
						null,
						AFTER_SEX,
						"<p>"
							+ UtilText.parsePlayerSpeech("好吧，我会去做的。") + "你回应道，抬头看到拉尔夫笑得更欢了。" + "</p>" + "<p>"
							+ "他将身子靠了过来，你以为他要亲上来了，但却并非如此，他只是抓住你的" + Main.game.getPlayer().getArmNameSingular() + "，把你拖向了他的桌子旁。"
							+ "过程中他便开始告诉你接下来将要发生的事情。"
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("你跪在桌子下面就好，而且不要妄想我主动做什么，明白了吗？", Main.game.getNpc(Ralph.class))
							+ "他问完后，你肯定地回答了他，他便继续道，"
							+ UtilText.parseSpeech("这家店是很体面的，有人进来的时候你就保持安静！每有一个顾客听见你的声音，那我就要从折扣中扣除半折。", Main.game.getNpc(Ralph.class))
						+ "</p>"
						+ "<p>"
							+ "此时拉尔夫已经带你到了柜台的后面，你发现柜台下方有一块空间，"
							+(Main.game.getPlayer().isTaur()
								?"即使是你[pc.legRace]的下半身，这块地方也足够你勉强缩身钻进去。"
								:"刚好够你舒服地跪在下面。")
							+ "柜台前面是实心的，在商店的其他位置都看不到你，你意识到只要保持安静，就不会有顾客注意到发生了什么。"
							+ "拉尔夫用双手按住了你的肩头，你觉得现在再反悔已经来不及了，于是就任他把你按了下去。"
							+ "你向后挪动了一点，进入了柜台底下的空间，拉尔夫此时也走了上来，裤子上那庞大的鼓包正顶在你的脸上。"
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("别忘了关注一下我的蛋蛋，", Main.game.getNpc(Ralph.class)) + "你听到他命令道。"
						+ "</p>"
						+ "<p>"
							+ "你正准备回答，却听到商店正门想起了铃声，这说明有顾客来了。"
							+ "你又听见了拉尔夫那熟悉而友善的招呼，同时他也把腰向前一顶，显然是想告诉你该开始了。"
							+ "你没有太多躲闪的空间，看样子为了得到折扣，你必须要用嘴豁出去了。"
						+ "</p>"
						+ "<p>"
							+ "那顾客移动到了商店的另一边，拉尔夫趁机伸手，解开了裤子的纽扣。"
							+ "他迅速一拽，就把裤子连同着内裤一起拉到了脚踝处。"
							+ "你看到那根巨物立刻弹了出来，坚硬的马屌抵在了你的下巴上，你不禁瞪大了双眼。"
							+ "他那对巨大的黑皮玉袋荡悠在那根野兽样子的肉棒之下，你幻想着接下来的场面，登时倒吸一口凉气……"
						+ "</p>" 
						+ "<p>" 
							+ "<b>现在柜台附近</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>没有顾客</b>。"
							+ "<b>你将会获得</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>25%</b>的<b>折扣。</b>"
						+ "</p>"){
					@Override
					public void effects() {
						Main.game.getNpc(Ralph.class).setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getNpc(Ralph.class).displaceClothingForAccess(CoverableArea.PENIS, null);
						SexFlags.customerAtCounter = false;
						SexFlags.customerTurnAppearance = 0;
					}
				};
				
			} else if (index == 2) {
				return new Response("拒绝", "告诉他你不愿意。", INTERIOR_REFUSE_DISCOUNT_CONDITIONS);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_REFUSE_DISCOUNT_CONDITIONS = new DialogueNode("拉尔夫小吃店", "-", true) {

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().getSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID)>0){
				return "<p>"
						+ "[pc.speech(不必了，)]你强迫自己如此说道，从这个淫荡的店主身边躲开了。"
					+ "</p>"
					+ "<p>"
						+ "[ralph.speech(好吧，但你想要的话，我随时都可以满足你！)]他笑道，说完便转身回到了柜台后面，"
						+ "[ralph.speech(还有什么别的需要就告诉我。)]" 
					+ "</p>"
					+ "<p>"
						+ "你继续浏览起店里商品，可拉尔夫巨大的黑色马屌却总是在你脑内萦绕。"
					+ "</p>";
				
			}else{
				return "<p>"
							+ "[pc.speech(不必了，)]你说道，从这个淫荡的店主身边躲开了。"
						+ "</p>"
						+ "<p>"
							+ "[ralph.speech(好吧，问问又没有坏处！)]他笑道，说完便转身回到了柜台后面。"
							+ "[ralph.speech(还有什么别的需要就告诉我。)]" 
						+ "</p>"
						+ "<p>"
							+ "你继续浏览起店里商品，但你脑中却总是不时想起拉尔夫胯下巨大的鼓起。"
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return INTERIOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("购物", "继续浏览拉尔夫商店里的商品。", true) {
		
		@Override
		public String getContent() {
			return "<p>"
						+ "拉尔夫也继续经营店铺了，你又走到转化消耗品的位置，思考着要不要利用折扣买些东西。"
						+ "拉尔夫确定附近没有别人在看着，便向你的身体投来了淫荡的目光，你敢肯定如果自己想要，随时都能说服他再给你一次“折扣”。"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续浏览拉尔夫店内的商品。", RalphsSnacks.INTERIOR);
			} else {
				return null;
			}
		}
	};
	
	private static int getLipstickPrice() {
		int price = 50000;
		
		price *= (100-Main.game.getDialogueFlags().ralphDiscount)/100f;
		
		return price;
	}
	
	public static final DialogueNode CANDI_LIPSTICK = new DialogueNode("拉尔夫小吃店", "-", true) {

		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().ralphDiscount), true);
			UtilText.addSpecialParsingString(Util.intToString(getLipstickPrice()), false);
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "CANDI_LIPSTICK");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<getLipstickPrice()) {
					return new Response("支付"+UtilText.formatAsMoneyUncoloured(getLipstickPrice(), "span"), "你付不起"+UtilText.formatAsMoney(getLipstickPrice(), "span")+"来交换“百万之吻”！", null);
				}
				return new Response("支付"+UtilText.formatAsMoney(getLipstickPrice(), "span"),
						"付给拉尔夫"+UtilText.formatAsMoney(getLipstickPrice(), "span")+"以交换“百万之吻”。",
						CANDI_LIPSTICK_PURCHASE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-getLipstickPrice()));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.CANDI_HUNDRED_KISSES), false));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_DELIVER_LIPSTICK));
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphAskedAboutHundredKisses);
					}
				};

			} else if (index == 2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ralphDailyBred)) {
					return new Response("接受配种", "拉尔夫还忙着经营店铺，没时间让你怀孕。或许明天再来请求交配吧。", null);
					
				} else if(!Main.game.getPlayer().hasVagina()) {
					return new Response("接受配种", "你没有阴道，只能付给拉尔夫"+UtilText.formatAsMoney(getLipstickPrice(), "span")+"来交换那盒“百万之吻”。", null);
					
				} else if(Main.game.getPlayer().isPregnant()) {
					return new Response("接受配种", "你已经怀孕了，只能付给拉尔夫"+UtilText.formatAsMoney(getLipstickPrice(), "span")+"来交换那盒“百万之吻”。", null);
					
				} else if(Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)) {
					return new Response("接受配种", "你的子宫里已经装满了卵，只能付给拉尔夫"+UtilText.formatAsMoney(getLipstickPrice(), "span")+"来交换那盒“百万之吻”。", null);
					
				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response("接受配种", "你无法使用自己的阴道，只能付给拉尔夫"+UtilText.formatAsMoney(getLipstickPrice(), "span")+"来交换那盒“百万之吻”。", null);
				}
				return new ResponseSex("接受配种",
						"告诉拉尔夫可以让他跟你配种，来换取那盒“百万之吻”。",
						Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY),
						null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true,
						false,
						new SexManagerDefault(
								SexPosition.OVER_DESK,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Ralph.class), SexSlotDesk.BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))) {
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.ONGOING_ONLY;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Ralph.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(character.isPlayer()) {
									return super.getCharacterOrgasmBehaviour(character);
								}
								return OrgasmBehaviour.CREAMPIE;
							}
						},
						null,
						null,
						AFTER_BREEDING,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "CANDI_LIPSTICK_START_BREEDING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Ralph.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getNpc(Ralph.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getNpc(Ralph.class), false);
						Main.game.getNpc(Ralph.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getPlayer(), false);
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphAskedAboutHundredKisses);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ralphDailyBred, true);
					}
				};

			} else if (index == 0) {
				return new Response("拒绝", "告诉拉尔夫你得思考一下……", BACK_TO_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "CANDI_LIPSTICK_PURCHASE_DECLINE"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "AFTER_BREEDING_INTERIOR"));
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphAskedAboutHundredKisses);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CANDI_LIPSTICK_PURCHASE = new DialogueNode("拉尔夫小吃店", "-", true) {

		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().ralphDiscount), true);
			UtilText.addSpecialParsingString(Util.intToString(getLipstickPrice()), false);
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "CANDI_LIPSTICK_PURCHASE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return INTERIOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_BREEDING = new DialogueNode("结束", "你的子宫里已经装满了他活跃的精液，拉尔夫很满足。", true) {

		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Main.game.getNpc(Ralph.class).useItem(Main.game.getItemGen().generateItem(ItemType.PREGNANCY_TEST), Main.game.getPlayer(), false), true);
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "AFTER_BREEDING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isPregnant() && Objects.equals(Main.game.getPlayer().getPregnantLitter().getFather(), Main.game.getNpc(Ralph.class))) {
					return new Response("成功",
							"你不仅自豪地成为了那盒限定款“百万之吻”的拥有者，还做了拉尔夫"
									+"孩子的母亲！",
									BACK_TO_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "AFTER_BREEDING_SUCCESS"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.CANDI_HUNDRED_KISSES), false));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_DELIVER_LIPSTICK));
						}
					};
					
				} else {
					return new Response("失败……",
							"拉尔夫没能让你怀孕，所以也不愿意将那盒“百万之吻”交给你……",
							BACK_TO_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "AFTER_BREEDING_FAILURE"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode BACK_TO_INTERIOR = new DialogueNode("拉尔夫小吃店", "-", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return INTERIOR.getResponse(responseTab, index);
		}
	};
}
