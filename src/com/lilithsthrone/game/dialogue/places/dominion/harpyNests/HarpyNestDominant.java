package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominant;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominantCompanion;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.8
 * @version 0.4.3.2
 * @author Innoxia
 */
public class HarpyNestDominant {

	public static final DialogueNode HARPY_NEST_DOMINANT_TALK = new DialogueNode("哈比之巢", "", true) {

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "[pc.speech(我只是想跟你聊聊哈比之巢最近的骚乱。执法者极力保持此处的安宁，你的族群似乎难逃其咎，)]"
					+ "你想要晓之以理，但[dominantHarpy.name]却只是重重跺了几下爪子，用愤怒的哼声打断了你。"
				+ "</p>"
				+ "<p>"
					+ "[dominantHarpy.speech(你他妈说什么？！你<i>好大的胆子</i>在我的地盘这么跟我说话！)]"
					+ "她不断拍打着翅膀，恼怒地吼叫道，"
					+ "[dominantHarpy.speech(你要是不想变成[dominantHarpyCompanion.namePos]的下一个肉便器，就识相赶紧滚！我今天心情好，你就偷着乐吧！)]"
				+ "</p>"
				+ "<p>"
					+ "看样子[dominantHarpy.name]不想听你再说了。"
					+ "你必须另想方法说服这群哈比平静下来，或者用武力让他们安静。"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("夺了鸟位", "你的耐心已经耗尽。是时候让这婊子见识一下谁才是老大了！", HARPY_NEST_DOMINANT_QUEEN,
						Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, null, Femininity.FEMININE_STRONG, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.dominantPacified);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.HARPY_MATRIARCH_DOMINANT_PERFUME), false, true));
						
						if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_ONE) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_TWO));
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_TWO) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_THREE));
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_THREE) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_REWARD));
						}
					}
				};
					
			} else if (index == 2) {
				return new Response("暴力解决",
						"如果想让这群哈比冷静下来，就必须要动用武力了……"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(坏结局：)]如果输了这场战斗，哈比们就再也不会放你走了！"
								:""),
						HARPY_NEST_DOMINANT_FIGHT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
					
			} else if (index == 0) {
				return new Response("离开", "下定决心离开[harpyDominant.namePos]的巢穴。", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "认为现在不是跟[harpyDominant.name]对峙的好时机，你转身逃离了高台。"
									+ "你离开时，只听见[harpyDominant.name]对[harpyDominantCompanion.name]低声吼道，"
									+ "[harpyDominant.speechNoEffects(这算个什么事？！算了算了！[harpyDominantCompanion.Name]，你继续跟那些肉便器玩去吧……)]"
								+ "</p>"
								+ "<p>"
									+ "你无视了她的废话，继续穿过平台，又一次回到了巢穴的边缘。"
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_UGLY = new DialogueNode("哈比之巢", "", true) {

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "你的脑海中突然冒出一个机智的反驳方式，没等你细想，便大声说道，"
					+ "[pc.speech(我不过只是不想再看到你那张丑脸罢了！)]" //Innoxia's next-level witticisms!
				+ "</p>"
				+ "<p>"
					+ "[harpyDominant.Name]怒不可遏地尖叫起来，猛地扇动双翼，向同伴喊道，"
					+ "[harpyDominant.speech([harpyDominantCompanion.Name]！给我操死[pc.herHim]！打到[pc.herHim]抬不起头来！混蛋！干掉[pc.herHim]啊！)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyDominantCompanion.name]的怒气与[harpyDominant.name]不相上下，迫切地想要取悦族长，纵身一跃，带着怒火冲了上来。"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗",
						"[harpyDominantCompanion.Name]按照族长的吩咐冲将上来！"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(坏结局：)]如果输了这场战斗，哈比们就再也不会放你走了！"
								:""),
						Main.game.getNpc(HarpyDominantCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_QUEEN = new DialogueNode("哈比之巢", "", true) {

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(你要是<i>再</i>敢这么跟我说话，那我可要让你的小宠物把你当成她下一个肉便器了！说起来，)]"
						+ "你盯着[harpyDominantCompanion.Name]，高喊道，"
						+ "[pc.speech(为什么不在女王面前下跪！但愿你<i>他妈</i>能有个好借口！)]"
					+ "</p>"
					+ "<p>"
						+ "你刚一踏入[harpyDominant.namePos]的巢穴，便注意到自己优美的羽毛和强大的奥术灵气对族群产生了强烈影响。"
						+ "你学着族长高高在上的口气高喊道，四下顿时响起骚乱的窃语声。"
						+ "[dominantHarpyCompanion.name]丝毫不像惹怒你，连忙冲上前来，在你面前俯身跪下。"
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.speech([harpyDominantCompanion.Name]！你个没教养的母狗！回来，<i>立刻马上</i>！)][harpyDominant.name]尖叫道。"
					+ "</p>"
					+ "<p>"
						+ "[harpyDominantCompanion.Name]反而更向你挪过来，深深地将脑袋埋了下去，小声地呜咽着。"
						+ "[harpyDominantCompanion.speech(主，主人，我愿意听从您的一切！)]"
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.name]一时不知该如何反应，难以置信地眼睁睁看着其他哈比都跟随着[harpyDominantCompanion.namePos]倒戈了。"
						+ "一整个族群都转向了你，五体投地地深跪下去，认定你远胜过当前的族长，更为强大，更重要的是，更为迷人。"
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.name]眼见自己失去了族群的拥戴，气急败坏地跳下高台。"
						+ "猛地冲上前来，恼怒地抓住[harpyDominantCompanion.namePos]的衣领，将她凑你面前硬是拉走了。"
						+ "你正想命令拥护者上前护驾，[harpyDominant.name]却跃上前来，丝滑地在你面前跪下。"
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.speech(主人！)][harpyDominant.name]喊道。"
						+ "[harpyDominant.speech(我愿协助您管理族群！求主人让我为您所用吧！)]"
					+ "</p>"
					+ "<p>"
						+ "见到哈比族长在面前屈服，你禁不住露出一抹哂笑。"
						+ "在重组族群和向你臣服两个选择中，[harpyDominant.name]显然选择了后者，你为了奖励她的顺从，于是满足了她的愿望，"
						+ "[pc.speech(好孩子！那从此往后，我说东不准往西，懂了吗？！)]"
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.Name]向前挪动了几下，啜泣着低声下气地回应道，"
						+ "[harpyDominant.speech(明白，主人！我紧跟您的命令！"
								+ "我就是您的乖孩子！求主人收下这瓶特制的香水，当作我向您臣服的象征吧！)]"
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.name]取出一瓶暗红色的香水，递给了你。"
						+ "[harpyDominant.speech(主人若是愿意，这香水能让您变成我们的一份子！)]"
						+ "你拿来这作为[harpyDominant.namePos]臣服象征的香水，但并不确定是否真的要用在自己身上……"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(真乖！)]你说道。[pc.speech(现在，让巢穴平静下来！如果我<i>再听到</i>有人报告哈比闹事，就饶不了你！)]"
					+ "</p>"
					+ "<p>"
						+ "[harpyDominant.speech(是，主人！我会好好管教他们的！不需要您费心！)]"
					+ "</p>"
					+ "<p>"
						+ "多亏了你盛气凌人的性格和姣好的外貌，不动拳脚就镇压了[harpyDominant.namePos]的巢穴！"
						+ "你低头看着已经十分顺从的哈比族长，思索起是否需要向其他哈比公开展示一下你的统治权……"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("性爱", "和[harpyDominant.name]来一场支配型性爱。",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyDominant.class)),
						null,
						null), HARPY_NEST_DOMINANT_AFTER_SEX, "<p>"
							+ "你急于在族群面前明示[harpyDominant.name]的地位，于是伸[pc.hand]抓住了她的翅膀。"
							+ "你把她拉了起来，上前在她[harpyDominant.lips+]上留下了一记深吻。"
						+ "</p>"
						+ "<p>"
							+ "[harpyDominant.Name]也只是发出一声驯服的呜咽，回应着你支配的举动，随后便用翅膀将你包裹住，热切地回报着你的吻……"
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("离开", "决定告辞。", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "你觉得这里没有别的事情了，便原路返回。"
									+ "离开时，听见[harpyDominant.name]向着一只哈比大吼道，"
									+ "[harpyDominant.speech(<i>[pc.SheIs]</i>就是这里的新领袖，你这个没用的婊子！[harpyDominantCompanion.Name]！看样子你又有新玩具了！)]"
								+ "</p>"
								+ "<p>"
									+ "你听到后不过微微窃笑，便继续穿过主平台，很快就又一次回到了巢穴的边缘。"
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_FIGHT = new DialogueNode("哈比之巢", "", true) {

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "你别无他法，只得盯着[harpyDominant.name]最后警告道，"
					+ "[pc.speech(要不你就自己让巢穴平静下来，不然最后我也会让你照我说的做的！)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyDominant.Name]怒不可遏地尖叫起来，向同伴喊道，"
					+ "[harpyDominant.speechNoEffects([harpyDominantCompanion.Name]给这无礼的[pc.race]一点教训！<i>还没人</i>这么跟我说过话后还能逃过一劫的！)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyDominantCompanion.name]迫切地想要取悦族长，大喝一声，带着怒火冲了上来。"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗",
						"[harpyDominantCompanion.Name]按照族长的吩咐冲将上来！"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(坏结局：)]如果输了这场战斗，哈比们就再也不会放你走了！"
								:""),
						Main.game.getNpc(HarpyDominantCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_FIGHT_BEAT_PET = new DialogueNode("哈比之巢", "", true) {

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "[harpyDominant.Name]看到[harpyDominantCompanion.name]败下阵来，倒地不起，顿时火光冲天。"
					+ "你意识都周围有些哈比开始不安地面面相觑，甚至有些都缓缓挪到你这边的平台上来。"
					+ "看起来他们是想对冲赌注，如果能证明自己的实力，他们就会支持你。"
				+ "</p>"
				+ "<p>"
					+ "你无暇思索哈比这善变的天性，因为[harpyDominant.Name]已经从高台上飞跃而下，尖叫道，"
					+ "[harpyDominant.speech(你这混蛋！我要让你付出代价！)]"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗",
						"[harpyDominant.Name]怒气冲冲地向你袭来！"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(坏结局：)]如果输了这场战斗，哈比们就再也不会放你走了！"
								:""),
						Main.game.getNpc(HarpyDominant.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_FIGHT_BEAT_DOMINANT = new DialogueNode("哈比之巢", "", true) {

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "与[harpyDominant.NamePos]亲近的内层哈比亲眼看到族长被打倒在地，顿时噤若寒蝉。"
					+ "你向前一步，低头看着她可怜的样子，这时一声低沉的呻吟声从她的唇间冒出，她连忙起身跪了下去。"
					+ "[harpyDominant.speechNoEffects(你，你真是太强大了……你到底是谁？！)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(是你的新主人，)]你回答完，便扭头看向周围的哈比。"
					+ "[pc.speech(你们谁有异议吗？！)]"
				+ "</p>"
				+ "<p>"
					+ "你的奥术灵气显然对[harpyDominant.name]拥有强烈的影响，她呼唤你的时候又发出了一声淫荡的呻吟。"
					+ "[harpyDominant.speech(主，主人！我的孩子们会乖乖的，不必担心！)]"
				+ "</p>"
				+ "<p>"
					+ "剩下的哈比见到族长都臣服于你，在你发号施令的时候也敬畏地低下了头，"
					+ "[pc.speech(你们必须安静下来，懂了吗？！不许再内部争斗，也不许袭击途径哈比之巢的路人！)]"
				+ "</p>"
				+ "<p>"
					+ "听到哈比一齐回应的时候，你还是不禁笑了起来。"
					+ "[harpyDominant.Name]让整个族群都心甘情愿地服从于你后，慢慢挪动上前，低声下气地细声说道，"
					+ "[harpyDominant.speech(主，主人！我紧跟您的命令！"
							+ "我就是您的乖孩子！请收下这瓶特制的香水，当作我向您臣服的象征吧！)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyDominant.name]取出一瓶暗红色的香水，递给了你。"
					+ "[harpyDominant.speech(主人若是愿意，这香水能让您变成我们的一份子！)]"
					+ "你拿来这作为[harpyDominant.namePos]臣服象征的香水，但并不确定是否真的要用在自己身上……"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(真乖！)]你说道。[pc.speech(现在，让巢穴平静下来！如果我<i>再听到</i>有人报告哈比闹事，就饶不了你！)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyDominant.speech(是，主人！我会好好管教他们的！不需要您费心！)]"
				+ "</p>"
				+ "<p>"
					+ "由于战胜了[harpyDominant.name]以及奥术灵气的力量，你镇压了[harpyDominant.namePos]的巢穴！"
					+ "你低头看着已经十分顺从的哈比族长，思索起是否需要向其他哈比公开展示一下你的统治权……"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("性爱", "和[harpyDominant.name]来一场支配型性爱。",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyDominant.class)),
						null,
						null), HARPY_NEST_DOMINANT_AFTER_SEX, "<p>"
							+ "你急于在族群面前明示[harpyDominant.name]的地位，于是伸[pc.hand]抓住了她的翅膀。"
							+ "你把她拉了起来，上前在她[harpyDominant.lips+]上留下了一记深吻。"
						+ "</p>"
						+ "<p>"
							+ "[harpyDominant.Name]也只是发出一声驯服的呜咽，回应着你支配的举动，随后便用翅膀将你包裹住，热切地回报着你的吻……"
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("离开", "告诉[harpyDominant.name]你晚点回来。", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "你觉得这里没有别的事情了，便原路返回。"
									+ "离开时，听见[harpyDominant.name]向着一只哈比大吼道，"
									+ "[harpyDominant.speech(<i>[pc.SheIs]</i>就是这里的新领袖，你这个没用的婊子！[harpyDominantCompanion.Name]！看样子你又有新玩具了！)]"
								+ "</p>"
								+ "<p>"
									+ "你听到后不过微微窃笑，便继续穿过主平台，很快就又一次回到了巢穴的边缘。"
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_AFTER_SEX = new DialogueNode("哈比之巢", "", true) {

		@Override
		public String getLabel() {
			return "[harpyDominant.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyDominant.class)) >= Main.game.getNpc(HarpyDominant.class).getOrgasmsBeforeSatisfied()) {
				return "<p>"
							+ "你从[harpyDominant.name]身边退开，她瘫倒在地，强烈的高潮让她精疲力竭。"
							+ "附近的哈比目睹了一切，发现你跟族长结束后，都齐齐跪了下来。"
						+ "</p>";
			} else {
				return "<p>"
							+ "你从[harpyDominant.name]身边退开，她瘫倒在地，发现你已经结束后，不禁发出一声失落的呜咽。"
							+ "她立刻将长满羽毛的手伸到双腿之间，开始发了疯似的自慰起来，想要结束这段你带来的“折磨”。"
							+ "附近的哈比目睹了一切，发现你跟族长结束后，都齐齐蹲了下来。"
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("离开", "玩够了，你决定离开。", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "你让[harpyDominant.name]认清自己的地位后，便穿过平台，转瞬间就又到了巢穴边缘……"
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
}
