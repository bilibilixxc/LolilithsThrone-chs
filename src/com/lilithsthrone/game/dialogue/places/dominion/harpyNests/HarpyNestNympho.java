package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.dominion.HarpyNympho;
import com.lilithsthrone.game.character.npc.dominion.HarpyNymphoCompanion;
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
public class HarpyNestNympho {

	public static final DialogueNode HARPY_NEST_NYMPHO_TALK = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(我只想要，)]你说道，"
						+ "[pc.speech(你控制好自己的巢穴。执法者极力保持此处的安宁，这个巢穴的成员难辞其咎！)]"
					+ "</p>"
					+ "<p>"
						+ "[harpyNympho.speech(我的小玩具去别的地方找乐子也不是<i>我的</i>错啊！)][harpyNympho.name]回应道。"
						+ "[harpyNympho.speech(哦，还有，你有点打扰到我们了，能不能转过身去找别人的麻烦！[harpyNymphoCompanion.Name]！给这个粗鲁的[pc.race]指一条出去的路吧！)]"
					+ "</p>"
					+ "<p>"
						+ "[harpyNymphoCompanion.name]已经走上前来，你意识到[harpyNympho.name]不会乖乖就范的。"
						+ "你必须另想方法说服这群哈比平静下来，或者用武力让他们安静。"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("性瘾女王", "你忽然觉得有些可怜这位族长，她必须要无数次地跟一成不变的几位男性做爱。是时候让她明白什么才是真正的性瘾了！", HARPY_NEST_NYMPHO_QUEEN,
						null, null, Util.newArrayListOfValues(Perk.NYMPHOMANIAC), Femininity.FEMININE_STRONG, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.nymphoPacified);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.HARPY_MATRIARCH_NYMPHO_LOLLIPOP), false, true));
						
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
						HARPY_NEST_NYMPHO_FIGHT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
					
			} else if (index == 0) {
				return new Response("离开", "告诉[harpyNympho.name]你晚点回来。", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "你认为现在不是对峙的最佳时机，于是转身离开了高台。"
									+ "正离开时，你听见[harpyNympho.name]在背后向[harpyNymphoCompanion.name]抱怨道，"
									+ "[harpyNympho.speech(真是浪费时间！来吧[harpyNymphoCompanion.name]，接下来用我的嘴巴吧！)]"
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
	
	public static final DialogueNode HARPY_NEST_NYMPHO_UGLY = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "你意识到必须动用暴力才能让[harpyNympho.name]控制好巢穴，于是准备跟她对峙，"
						+ "[pc.speech(好吧，如果你不愿意让巢穴平静下来，至少要同意在巢穴边围一圈围墙吧？"
						+ "我可不想经过巢穴的时候看到你这么难看的哈比。)]" //Innoxia's next-level witticisms!
					+ "</p>"
					+ "<p>"
						+ "[harpyNympho.Name]怒不可遏地尖叫起来，猛地扇动双翼，向同伴喊道，"
						+ "[harpyNympho.speech([harpyNymphoCompanion.Name]！给我抓住[pc.herHim]！抓住[pc.herHim]！这个混蛋！[pc.She]竟敢说我丑！)]"
					+ "</p>"
					+ "<p>"
						+ "[harpyNymphoCompanion.name]的怒气与[harpyNympho.name]不相上下，迫切地想要取悦族长，纵身一跃，带着怒火冲了上来。"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗",
						"[harpyNymphoCompanion.Name]按照族长的吩咐冲将上来！"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(坏结局：)]如果输了这场战斗，哈比们就再也不会放你走了！"
								:""),
						Main.game.getNpc(HarpyNymphoCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_NYMPHO_QUEEN = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() { // Mock her for only having one partner at once, she gets flustered, you describe multiple harpies fucking her, she breaks and falls to her knees admitting that you're better than she is
			return "<p>"
					+ "[pc.speech(所以说，你一次就只跟一个男宠一起吗？)]你带着讥讽的语气问道。"
					+ "[pc.speech(毕竟，你应该知道自己不止如此吧，是不是？)]"
				+ "</p>"
				+ "<p>"
					+ "一瞬间，[harpyNympho.name]似乎被你的话搞得有些措手不及，磕磕绊绊地回应，"
					+ "[harpyNympho.speech(这，这个，我是说，我只是为了让每只哈比都与众不同！)]"
				+ "</p>"
				+ "<p>"
					+ "话音刚落，你的身边便已经响起了窸窸窣窣的交流声，你注意到那群雄性哈比都凑了上来，仔细听着。"
					+ "你刚一踏入[harpyNympho.namePos]的巢穴，就能感受到整个族群的目光落在了你曼妙的身姿上，而如今你正高声批评着他们族长的性技，"
						+ "更是把他们的注意力都吸引了过来。"
				+ "</p>"
				+ "<p>"
					+ "你极尽可能摆出了性感的姿势，继续道，"
					+ "[pc.speech(你知道吗，你可在囤积各处美貌惊人的哈比留作己用，但每次却只跟一个搞？！"
					+ "实在是太不公平了，你们不觉得吗？)]"
				+ "</p>"
				+ "<p>"
					+ "你转身向着鸟群问出了灵魂一问，而整个族群都一齐回应了你。"
					+ "你优美的身姿和强大的奥术灵气显然对这些哈比产生了强烈影响，随着如此劲爆的宣言，整个族群都投向了你。"
				+ "</p>"
				+ "<p>"
					+ "[harpyNympho.speech(可，可是，我该怎么做呢？)][harpyNympho.name]焦虑地问道。"
					+ "[harpyNympho.speech(我都没觉得竟然这么不公平……)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(哦，你能做的可太多了，)]你向她走近，说了起来。"
					+ "[pc.speech(例如同时用上小穴、屁股、嘴巴。或者让整个巢穴都排队来一个个品尝你下面淫荡的蜜穴。)]"
				+ "</p>"
				+ "<p>"
					+ "你说着便伸出[pc.hand]抓住了[harpyNympho.namePos]暴露的下体。"
					+ "她尖声叫着，把身子朝你靠过来，呻吟道，"
					+ "[harpyNympho.speech(没错……我会的……还有吗？)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(我觉得你现在该做什么也很明显了吧，)]你在她耳边低语。"
					+ "[pc.speech(跪下来，接受我为统治者。)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyNympho.name]也早注意到整个族群都已经被你的话语迷惑，只得在你面前跪了下来。"
					+ "她抬起那充满欲火的大眼睛，呻吟道，[harpyNympho.speech(主人，请教导我吧！"
							+ "我会让族群平静下来的！请主人告诉我，该怎么做吧！)]"
				+ "</p>"
				+ "<p>"
					+ "见到哈比族长在面前屈服，你不禁发出一声轻笑。"
					+ "[pc.speech(乖孩子！那从此往后，我说东不准往西，懂了吗？！)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyNympho.Name]先前诺动了几下，一边呻吟一边回应道，"
					+ "[harpyNympho.speech(明白，主人！我紧跟您的话语！"
							+ "我有一物相赠，请主人收下！)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyNympho.name]取出一根肉棒样子的棒棒糖，递给了你。"
					+ "[harpyNympho.speech(主人，这会让您看起来很像我！如，如果您想和我一样，那就……)]"
					+ "你拿来这作为[harpyNympho.namePos]臣服象征的棒棒糖，但并不确定是否真的要用在自己身上……"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(真乖！)]你说。[pc.speech(现在，该去让巢穴安静下来了，不是吗？)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyNympho.speech(是，主人！如果我让他们同时使用我，我就能减轻所有人的压力！)]"
				+ "</p>"
				+ "<p>"
					+ "多亏了你对性爱的沉迷、强大的奥术灵气和姣好的外貌，不动拳脚就镇压了[harpyNympho.namePos]的巢穴！"
					+ "你低头看着还在喘息的哈比族长，思索起是否需要来一场一对一的教学……"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("性爱", "和[harpyNympho.name]来一场支配型性爱。",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyNympho.class)),
						null,
						null), HARPY_NEST_NYMPHO_AFTER_SEX, "<p>"
							+ "你迫切地想要帮[harpyNympho.namePos]满足她对性爱的渴求，于是伸[pc.hand]抓住了她的翅膀。"
							+ "你把她拉了起来，上前在她[harpyNympho.lips+]上留下了一记深吻。"
						+ "</p>"
						+ "<p>"
							+ "[harpyNympho.Name]发出一声兴奋的尖叫，回应着你支配的举动，随后便用翅膀将你包裹住，热切地回报着你的吻……"
						+ "</p>");
						
			} else if (index == 0) {
				return new Response("离开", "决定告辞。", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "你觉得这里没有别的事情了，便原路返回。"
									+ "离开时，你听见[harpyNympho.name]对其他哈比喊道，"
									+ "[harpyNympho.speech(都听见新领袖说的了吧！你们三个，一起来！)]"
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
	
	public static final DialogueNode HARPY_NEST_NYMPHO_FIGHT = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "你意识到[harpyNympho.name]不会跟你讲理的，于是最后警告道，"
					+ "[pc.speech(要不你就自己让巢穴平静下来，不然最后我也会让你照我说的做的！)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyNympho.Name]怒不可遏地尖叫起来，向同伴喊道，"
					+ "[harpyNympho.speech([harpyNymphoCompanion.Name]，把这个[pc.race]叉出去！<i>还没人</i>这么跟我说过话后还能逃过一劫的！)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyNymphoCompanion.name]迫切地想要取悦族长，立刻飞奔而起，带着怒火冲了上来。"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗",
						"[harpyNymphoCompanion.Name]按照族长的吩咐冲将上来！"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(坏结局：)]如果输了这场战斗，哈比们就再也不会放你走了！"
								:""),
						Main.game.getNpc(HarpyNymphoCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_NYMPHO_FIGHT_BEAT_BF = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "[harpyNympho.Name]看到[harpyNymphoCompanion.name]败下阵来，倒地不起，顿时怒啸一声。"
					+ "你意识都周围有些哈比开始不安地面面相觑，甚至有些都缓缓挪到你这边的平台上来。"
					+ "看起来他们都准备好了，如果你能击败族长，他们就会转而支持你。"
				+ "</p>"
				+ "<p>"
					+ "你无暇思索哈比这善变的天性，因为[harpyNympho.Name]已经突然直冲而来，大喊道，"
					+ "[harpyNympho.speech(你会付出代价的！)]"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗",
						"[harpyNympho.Name]怒气冲冲地向你袭来！"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(坏结局：)]如果输了这场战斗，哈比们就再也不会放你走了！"
								:""),
						Main.game.getNpc(HarpyNympho.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_NYMPHO_FIGHT_BEAT_NYMPHO = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			return "<p>"
					+ "[harpyNympho.NamePos]的族群前一秒钟还在为族长加油打气，下一秒看到她被打倒在地后，就顿时鸦雀无声。"
					+ "你向前一步，低头俯视着那团粉色的羽毛，这是一声色情的淫叫从[harpyNympho.namePos]的口中溢出，她连忙起身跪了下去。"
					+ "[harpyNympho.speech(你，你实在……太强大了……)]"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(没错，)]你答应道，"
					+ "[pc.speech(我不关心你们平时是怎么选出族群领袖的；但现在这里归我管！)]"
				+ "</p>"
				+ "<p>"
					+ "你的奥术灵气显然对[harpyNympho.name]拥有强烈的影响，她回答你的时候又发出了一声淫荡的呻吟，"
					+ "[harpyNympho.speech(明，明白，主人！我听从您的指示！)]"
				+ "</p>"
				+ "<p>"
					+ "剩下的哈比见到族长都臣服于你，也跪下身子，在你发号施令的时候敬畏地低下了头，"
					+ "[pc.speech(你们必须安静下来，懂了吗？！不许再内部争斗，也不许袭击途径哈比之巢的路人！)]"
				+ "</p>"
				+ "<p>"
					+ "四周的鸟群立马响起一连串急切的赞同声，你低头看着脚下被打败的族长，嘴角不禁扬起。"
					+ "[harpyNympho.name]凑近了一点，拿出一根粉色屌状的棒棒糖。"
					+ "[harpyNympho.speechNoEffects(主人！请收下一根我特制的棒棒糖！这会让您看起来很像我！如，如果您愿意的话，那就……)]"
					+ "你拿来这作为[harpyNympho.namePos]臣服象征的棒棒糖，但并不确定是否真的要用在自己身上……"
				+ "</p>"
				+ "<p>"
					+ "[pc.speech(真乖！)]你说。[pc.speech(现在，该去让巢穴安静下来了，不是吗？)]"
				+ "</p>"
				+ "<p>"
					+ "[harpyNympho.speech(明白，主人！您随意使唤！)]"
				+ "</p>"
				+ "<p>"
					+ "由于战胜了[harpyNympho.name]以及奥术灵气的力量，你镇压了[harpyNympho.namePos]的巢穴！"
					+ "你低头看着那位哈比族长，思索起是否需要向其他哈比公开展示一下你的统治权……"
				+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("性爱", "和[harpyNympho.name]来一场支配型性爱。",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyNympho.class)),
						null,
						null), HARPY_NEST_NYMPHO_AFTER_SEX, "<p>"
							+ "你急于向巢穴展示谁才是统治者，于是伸[pc.hand]抓住了[harpyNympho.namePos]的翅膀。"
							+ "你把她拉了起来，上前在她[harpyNympho.lips+]上留下了一记深吻。"
						+ "</p>"
						+ "<p>"
							+ "[harpyNympho.Name]发出一声兴奋的尖叫，回应着你支配的举动，随后便用翅膀将你包裹住，热切地回报着你的吻……"
						+ "</p>");
							
				} else if (index == 0) {
					return new Response("离开", "告诉[harpyNympho.name]你晚点回来。", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior")) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "你觉得这里没有别的事情了，便原路返回。"
									+ "离开时，你听见[harpyNympho.name]对其他哈比喊道，"
									+ "[harpyNympho.speech(那是我们的新领袖！放尊重点！)]"
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
	
	public static final DialogueNode HARPY_NEST_NYMPHO_AFTER_SEX = new DialogueNode("哈比之巢", ".", true) {

		@Override
		public String getLabel() {
			return "[harpyNympho.NamePos]的巢穴";
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyNympho.class)) >= Main.game.getNpc(HarpyNympho.class).getOrgasmsBeforeSatisfied()) {
				return "<p>"
							+ "你从[harpyNympho.name]身边退开，她瘫倒在地，强烈的高潮让她精疲力竭。"
							+ "附近的哈比目睹了一切，发现你跟族长结束后，都齐齐跪了下来。"
						+ "</p>";
			} else {
				return "<p>"
							+ "你从[harpyNympho.name]身边退开，她瘫倒在地，发现你已经结束后，不禁发出一声失落的呜咽。"
							+ "她羽翼覆盖的双手伸向两腿之间，发了疯似的开始自慰，想要结束这段你带来的折磨”。"
							+ "附近的哈比目睹了一切，发现你跟族长结束后，都齐齐跪了下来。"
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("离开", "玩够了，你决定离开。", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "让[harpyNympho.name]乖乖服从后，你从平台上走了下来，很快就又一次回到了巢穴的边缘。"
								+ "</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
}
