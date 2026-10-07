package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimbo;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimboCompanion;
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
public class HarpyNestBimbo {

	public static final DialogueNode HARPY_NEST_BIMBO_TALK = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(我来这里是要跟你谈谈最近的骚乱。执法者极力保持此处的安宁，希望你能让你们的族群稍微冷静下来，我们会很感激的，)]"
						+ "你想要晓之以理，但[bimboHarpy.name]只是翻了个白眼，发出了几声恼怒的啧声作为回应。"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.speechNoEffects(什么啊，这完全不是我的错！)]"
						+ "她摆了摆手，继续说道，"
						+ "[bimboHarpy.speechNoEffects(要不然，那个，你还是别在这吱哇乱叫了！不然我，呃，可要叫[bimboHarpyCompanion.name]来好好教训你一下了！)]"
					+ "</p>"
					+ "<p>"
						+ "看样子[bimboHarpy.name]不准备讲道理。"
						+ "你必须另想方法说服这群哈比平静下来，或者用武力让他们安静。"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("无脑大胸女王", "你应该让整个巢穴的人臣服，成为他们的女王！你试着插话，但这个婊子完全没听！", HARPY_NEST_BIMBO_QUEEN,
						Util.newArrayListOfValues(Fetish.FETISH_BIMBO), null, null, Femininity.FEMININE_STRONG, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimboPacified);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.HARPY_MATRIARCH_BIMBO_LOLLIPOP), false, true));
						
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
						HARPY_NEST_BIMBO_FIGHT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
					
			} else if (index == 0) {
				return new Response("离开", "告诉[bimboHarpy.name]你晚点回来。", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "你认为现在不是干这件事的好机会，便转身走下楼梯。"
									+ "你离开时，听到[bimboHarpy.name]正向哈比抱怨，"
									+ "[bimboHarpy.speechNoEffects(额！这！到底是怎么回事！？)]"
								+ "</p>"
								+ "<p>"
									+ "无视掉她的话，你继续走下台阶，穿过平台，很快意识到你回到巢穴的边缘"
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_UGLY = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "你被这个无脑大胸的碧池对你的态度惹恼了，决定给她点颜色看看。"
						+ "[pc.speech(要不你停止冒犯所有人，然后拿个袋子遮住你那张丑脸如何？)]"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.NamePos]的脸涨的通红，伴随尖锐的叫声，她向同伴叫喊着，"
						+ "[bimboHarpy.speechNoEffects([bimboHarpyCompanion.Name]！抓住[pc.herHim]！给我抓住[pc.herHim]！没有人可以跟我这么说话！)]"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpyCompanion.name]的愤怒与[bimboHarpy.name]不相上下，迫切地想要取悦族长，纵身一跃，带着怒火冲了上来。"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗",
						"[bimboHarpyCompanion.Name]急忙听从族长的吩咐！"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(坏结局：)]如果输了这场战斗，哈比们就再也不会放你走了！"
								:""),
						Main.game.getNpc(HarpyBimboCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_QUEEN = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "受够了这个婊子的态度，你向前走，一边走一边看着[bimboHarpyCompanion.name]，说道，"
						+ "[pc.speechNoEffects(说起来，你真的要听一整天吗？照我说的做，懂了吗？！现在，来给你们的新女王下跪吧！)]"
					+ "</p>"
					+ "<p>"
						+ "你美丽的五官和强大的奥术灵气似乎对这些哈比产生了强烈的影响。"
						+ "与他们的族长用同样的方式说话，似乎就足以动摇[bimboHarpy.namePos]的追随者并站在你这边。"
						+ "在她意识到自己在做什么之前，[bimboHarpyCompanion.name]就已经冲上前服从你的命令了。"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.speechNoEffects([bimboHarpyCompanion.Name]？！你到底在干什么？给我回来！)][bimboHarpy.name]吼道。"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpyCompanion.Name]停在你们俩中间，困惑地来回张望。"
						+ "你注意到，哈比群中的其他人并没有来保护他们的族长，而是在观望，等着看[bimboHarpyCompanion.name]决定怎么做。"
					+ "</p>"
					+"<p>"
						+ "[pc.speechNoEffects([bimboHarpyCompanion.Name]！)]你喊道。[pc.speechNoEffects(过来给我跪下，就<i>现在</i>！)]"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.name]不知道该如何反应，难以置信地看着她的同伴冲上前，顺从地跪在你面前。"
						+ "其余的哈比也很快跟了上来，因为他们认识到你是一个更强大的女性，更重要的是，你比他们现在的族长更有魅力。"
					+ "</p>"
					+ "<p>"
						+ "当刚刚发生的一切开始变得清晰起来时，[bimboHarpy.name]迅速向前跑去，推开[bimboHarpyCompanion.name]，取代她的位置跪在你面前。"
						+ "她向前走了几步，抬头看着你的眼睛。"
						+ "[bimboHarpy.speechNoEffects(嘿！<i>我</i>才是这里最漂亮的！当……当然，除了你之外！请让我成为你的私人宠物吧！)]"
					+ "</p>"
					+ "<p>"
						+ "你对着你的新宠物哈比傻笑，与她说她想听的话，"
						+ "[pc.speechNoEffects(好乖的小宠物！你真的是超级聪明！我就知道你会理解你的新家的！)]"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.Name]靠近你，发出顺从的咪咪叫声，并抬头看着你继续说下去，"
						+ "[pc.speechNoEffects(现在我没有时间管理这个巢，所以你要继续做这些事。"
						+ "你得记住谁才是真正的老大！现在，你的首要任务是，让所有这些哈皮都冷静下来之类的！"
						+ "如果我听说他们惹了麻烦……你就会嗯~从我的宠物降级，明白吗？)]"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.speechNoEffects(是的，主人！)][bimboHarpy.name]喊道。[bimboHarpy.speechNoEffects(我会管好他们的！哦！哦！还有！主人！我有个特别的棒棒糖给你！)]"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.name]拿出一个粉白相间的旋转棒棒糖递给你。"
						+ "[bimboHarpy.speechNoEffects(这是我给，给所有新成员的礼物！它会把，把你变成我们中的一员！)]"
						+ "你接过棒棒糖，以表示对[bimboHarpy.namePos]的认可，但你不确定是否真的会使用它……"
					+ "</p>"
					+ "<p>"
						+ "多亏了你的无脑大胸的性格和美丽的外貌，你才能不战而屈人之兵，征服[bimboHarpy.namePos]巢穴！"
						+ "俯视着这位无脑大胸的族长，你在想是否应该公开向所有这些哈皮证明谁才是这里的主宰者……"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("做爱", "在[bimboHarpy.name]面前，与她发生支配型性爱。",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyBimbo.class)),
						null,
						null), HARPY_NEST_BIMBO_AFTER_SEX, "<p>"
							+ "你急于在族群面前明示[harpyBimbo.name]的地位，于是伸手抓住了她的翅膀。"
							+ "拉着她站起来，你走上前去，在她[harpyBimbo.lips+]上深深一吻，引来周围无脑大胸的哈比们一连串兴奋的咯咯笑声。"
						+ "</p>"
						+ "<p>"
							+ "[harpyBimbo.Name]回应你的支配举动，用她的翅膀缠住你的后背，在你身上磨蹭，热情地回吻你……"
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("离开", "告诉[bimboHarpy.name]你晚点回来。", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "你决定结束这里的事情，转身下楼。"
									+ "当你离开时，你听到[bimboHarpy.name]正对着哈比们大喊大叫，"
									+ "[bimboHarpy.speechNoEffects(给我安静！<i>[pc.SheIs]</i>是这里的新领袖，听得明白吗？！)]"
								+ "</p>"
								+ "<p>"
									+ "你听到后微微一笑，便继续走下台阶，穿过主平台，很快察觉自己回到巢穴边缘。"
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_FIGHT = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "受够了[bimboHarpy.namePos]的态度，你提出了最后的要求，"
						+ "[pc.speech(你得平息巢内的动荡，否则我会摆平你！)]"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpy.NamePos]脸涨得通红，朝同伴尖利地啸叫，"
						+ "[bimboHarpy.speechNoEffects([bimboHarpyCompanion.Name]，给这个[pc.race]上一课，喂，<i>没人</i>能这样跟我讲话！)]"
					+ "</p>"
					+ "<p>"
						+ "[bimboHarpyCompanion.name]大吼一声冲上前来，她迫切地想要取悦族长，发起了猛烈的攻势。"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗",
						"[bimboHarpyCompanion.Name]急忙听从族长的吩咐！"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(坏结局：)]如果输了这场战斗，哈比们就再也不会放你走了！"
								:""),
						Main.game.getNpc(HarpyBimboCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_FIGHT_BEAT_GF = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "[bimboHarpy.Name]发出怒啸，[bimboHarpyCompanion.name]瘫倒在地，被打败了。"
						+ "你意识都周围有些哈比开始不安地面面相觑，甚至有些都缓缓挪到你这边的平台上来。"
						+ "看起来他们是想对冲赌注，如果能证明自己的实力，他们就会支持你。"
					+ "</p>"
					+ "<p>"
						+ "你无暇在乎哈比这善变的天性，因为[bimboHarpy.Name]已突然猛冲而来，尖叫道，"
						+ "[bimboHarpy.speech(你要为此付出代价！)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗",
						"[bimboHarpy.Name]怒气冲冲地向你袭来！"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(坏结局：)]如果输了这场战斗，哈比们就再也不会放你走了！"
								:""),
						Main.game.getNpc(HarpyBimbo.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_FIGHT_BEAT_BIMBO = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "[bimboHarpy.NamePos]刚刚还在欢呼呐喊的无脑大胸的哈比圈，看到自己的族长瘫倒在地，顿时鸦雀无声。"
						+ "你向前走去，俯视着她可怜的样子，听到她发出情欲的呻吟，跪在地上，"
						+ "[bimboHarpy.speechNoEffects(嗷！你怎么这么强大！你，你究竟是谁？！)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(你们的新领袖，)]你回答道。"
						+ "[pc.speech(我不在乎你们平时怎么选出族群领袖；但现在这里我才是老大！)]"
					+ "</p>"
					+ "<p>"
						+ "你的奥术灵气显然对[bimboHarpy.name]有强烈的影响，她回应时还发出一声淫乱的呻吟，"
						+ "[bimboHarpy.speechNoEffects(明，明白"+(Main.game.getPlayer().isFeminine()?"主人":"主人")+"！我，我会带来非常多好处，主人！)]"
					+ "</p>"
					+ "<p>"
						+ "剩下的哈比见到族长都臣服于你，也跪下身子，在你发号施令的时候敬畏地低下了头，"
						+ "[pc.speech(你们必须安静下来，懂了吗？！不许再内部争斗，也不许袭击途径哈比之巢的路人！)]"
					+ "</p>"
					+ "<p>"
						+ "你的话语深入人心，并得到一片热切的同意。"
						+ "[bimboHarpy.name]举着个粉白相间的圈圈棒棒糖，摇摇晃晃地靠近你。"
						+ "[bimboHarpy.speechNoEffects(主人！如果您想，想变得和我们一样，就舔一下这个！"
								+ "我保证一切都在你们的掌控之中！我们都会好起来的，对吧，姑娘们？)]"
					+ "</p>"
					+ "<p>"
						+ "无脑大胸的哈比们发出赞成的呼喊声。你弯腰接过棒棒糖，肯定着[bimboHarpy.namePos]的提议，但你不确定自己会不会真的用它……"
					+ "</p>"
					+ "<p>"
						+ "由于战胜了[bimboHarpy.name]以及奥术灵气的力量，你镇压了[bimboHarpy.namePos]的巢穴！"
						+ "俯视着这位无脑大胸的族长，你在想是否应该公开向所有这些哈皮证明谁才是这里的主宰者……"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("做爱", "和[bimboHarpy.name]支配性做爱。",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyBimbo.class)),
						null,
						null), HARPY_NEST_BIMBO_AFTER_SEX, "<p>"
							+ "你急于在族群面前明示[harpyBimbo.name]的地位，于是伸手抓住了她的翅膀。"
							+ "拉着她站起来，你走上前去，在她的[harpyBimbo.lips+]上深深一吻，引来周围无脑大胸的哈比们一连串兴奋的咯咯笑声。"
						+ "</p>"
						+ "<p>"
							+ "[harpyBimbo.Name]回应你的支配举动，用她的翅膀缠住你的后背，在你身上磨蹭，热情地回吻你……"
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("离开", "告诉[bimboHarpy.name]你晚点回来。", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "你决定结束这里的事情，转身下楼。"
									+ "当你离开时，你听到[bimboHarpy.name]正对着哈比们大喊大叫，"
									+ "[bimboHarpy.speechNoEffects(给我安静！<i>[pc.SheIs]</i>是这里的新领袖，听得明白吗？！)]"
								+ "</p>"
								+ "<p>"
									+ "你听到后微微一笑，便继续走下台阶，穿过主平台，很快察觉自己回到巢穴边缘。"
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_AFTER_SEX = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyBimbo.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyBimbo.class)) >= Main.game.getNpc(HarpyBimbo.class).getOrgasmsBeforeSatisfied()) {
				return "<p>"
							+ "你从[bimboHarpy.name]身旁退开，她瘫软在地，因"+(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyBimbo.class)) > 1?"多次":"")+"高潮而失神。"
							+ "周围的哈比们们看完了整个过程，在你解决了她们的族长后，纷纷跪下表示臣服。"
						+ "</p>";
			} else {
				return "<p>"
							+ "你从[bimboHarpy.name]身边退开，她瘫倒在地，发现你已经结束后，不禁发出一声失落的呜咽。"
							+ "她羽翼覆盖的双手伸向两腿之间，发了疯似的开始自慰，想要结束这段你带来的折磨”。"
							+ "周围的哈比们目睹了一切，在你解决了她们的族长后，纷纷跪下表示臣服。"
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("离开", "玩够了，你决定离开。", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "你告诉[bimboHarpy.name]认清自己的地位，下楼穿过平台。转瞬间又到了[bimboHarpy.namePos]巢穴的边缘。"
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
}
