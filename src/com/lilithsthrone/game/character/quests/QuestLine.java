package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.TreeNode;

/**
 * @since 0.1.1
 * @version 0.4
 * @author Innoxia
 */
public enum QuestLine {

	// Main quests:

	MAIN("莉莉丝的王座", "你已完成当前版本的所有主线任务！",
			QuestType.MAIN,
			QuestTree.mainQuestTree),

	// Side quests:

//	SIDE_ITEM_DISCOVERY("Item Discovery", "You have found all the different items that are in this version!",
//			QuestType.SIDE,
//			Quest.SIDE_DISCOVER_ALL_ITEMS),
//
//	SIDE_RACE_DISCOVERY("Race Discovery", "You have found all the different races that are in this version!",
//			QuestType.SIDE,
//			Quest.SIDE_DISCOVER_ALL_RACES),

	SIDE_ENCHANTMENT_DISCOVERY("精华与附魔", "你现在知道该如何使用精华来制作和调整附魔物品！",
			QuestType.SIDE,
			QuestTree.enchantmentTree),

	SIDE_FIRST_TIME_PREGNANCY("一夜大肚", "在莉莱雅的帮助下，你成功地完成了你人生中的第一次分娩。有了第一次，或许就会有更多次……",
			QuestType.SIDE,
			QuestTree.pregnancyTree),

	SIDE_FIRST_TIME_INCUBATION("卵生", "你成功地孕育、产下并孵化了种在你体内的蛋！",
			QuestType.SIDE,
			QuestTree.incubationTree),

	SIDE_SLAVERY("奴隶主", "多亏了莉莱雅的推荐信，你成功获得了梦寐以求的贩奴许可！",
			QuestType.SIDE,
			QuestTree.slaveryTree),

	SIDE_ACCOMMODATION("衣食住行", "莉莱雅很乐意让你使用空余的房间，为你的朋友或家人提供住宿，条件是你必须支付他们的支出费用……",
			QuestType.SIDE,
			QuestTree.accommodationTree),

	SIDE_DOLL_STORAGE("储存玩偶", "莉莱雅同意你使用空闲房间来存放你购买的所有玩偶……",
			QuestType.SIDE,
			QuestTree.dollStorageTree),

	SIDE_HYPNO_WATCH("亚瑟的实验", "你协助亚瑟完成了实验，制作出能够改变倾向的催眠怀表，现在已经收入你的囊中！",
			QuestType.SIDE,
			QuestTree.hypnoWatchTree),

	SIDE_ARCANE_LIGHTNING("奥术闪电", "亚瑟能够将奥术闪电的秘密从你给他的圆球中提取出来，让你学会了两个十分强大的魔法。",
			QuestType.SIDE,
			QuestTree.arcaneLightningTree),
	
	SIDE_HARPY_PACIFICATION("愤怒的哈比", "你成功安抚了三位哈比族长，现在可以在哈比之巢通行无阻！",
			QuestType.SIDE,
			QuestTree.angryHarpyTree),

	SIDE_SLIME_QUEEN("史莱姆女王", "你解决了史莱姆女王！",
			QuestType.SIDE,
			QuestTree.slimeQueenTree),

	SIDE_TELEPORTATION("传送的麻烦", "你习得传送法术，然后逃离了执法者仓库。",
			QuestType.SIDE,
			QuestTree.teleportingTree),

	SIDE_DADDY("好奇的淫梦魔", "你解决了那个对莉莱雅感兴趣的恶魔[daddy.name]。",
			QuestType.SIDE,
			QuestTree.daddyTree),

	SIDE_BUYING_BRAX("收购一匹狼", "你把坎迪的一系列麻烦任务都搞定了，终于买到了[brax.name]。",
			QuestType.SIDE,
			QuestTree.buyingBraxTree),

	SIDE_VENGAR("暴君文加", "你解决了文加，阿克塞尔不用再担心了。",
			QuestType.SIDE,
			QuestTree.vengarTree),


	SIDE_WES("胡作非为的执法者", "你成功解决了执法者军需官。",
			QuestType.SIDE,
			QuestTree.wesTree),
        
	SIDE_REBEL_BASE("摸金校尉", "你成功从废弃的叛军藏匿点逃了出来。",
		QuestType.SIDE,
		QuestTree.rebelBaseTree),

	SIDE_REBEL_BASE_FIREBOMBS("香辣肉丸", "你现在有了源源不断的奥术燃烧弹供应。呃，虽然高于市场价。",
		QuestType.SIDE,
		QuestTree.rebelBaseFirebombTree),
	
	SIDE_EISEK_STALL("暴屠暴徒", "你帮艾瑟克收拾了他的摊位。",
		    QuestType.SIDE,
		    QuestTree.eisekStallTree),
	
	SIDE_EISEK_MOB("暴徒心理学", "你说服了纠缠艾瑟克的暴徒，让他们放他一马。",
		    QuestType.SIDE,
		    QuestTree.eisekMobTree),
	
	SIDE_EISEK_SILLYMODE("龙类爱好者", "你遇到了一群迷恋龙类的怪人，并清空了他们的地牢。",
		    QuestType.SIDE,
		    QuestTree.eisekSillyModeTree),

	SIDE_OGLIX_BEER_BARRELS("奶啤妓富豪", "你为奥格利克斯找来了更多的桶，你可以从附近的小巷找四个罪犯送到她那里，成为新的奶啤妓！",
			QuestType.SIDE,
			QuestTree.beerBarrelTree),

	SIDE_LUNEXIS_ESCAPE("服侍露内克西丝", "你听从了主人的命令，确保她能逃脱，这注定了你以后的命运就是成为她的一只私人鸡巴套……",
			QuestType.SIDE,
			QuestTree.lunexisEscapeTree),

	SIDE_DOLL_FACTORY("处理玩偶", "你揭示了“洛维耶纳奢侈品店”出售的高级性爱玩偶制作方式的真相……",
			QuestType.SIDE,
			QuestTree.dollFactoryTree),
	
	// Romance quests:
	
	RELATIONSHIP_NYAN_HELP("供货难题", "你帮助妮安解决了供货商的问题。",
			QuestType.RELATIONSHIP,
			QuestTree.nyanTree),

	ROMANCE_HELENA("族长的帮手", "你成功完成了海伦娜给你的每一项任务，作为奖励，你可以从她那里定制奴隶，还可以在每周五晚上和她约会。",
			QuestType.RELATIONSHIP,
			QuestTree.helenaTree),

	ROMANCE_NATALYA("[style.Mule]训练", "你完成了娜塔莉亚主人的训练，现在是一匹合格的[style.mule]，为御城速递的半人马奴隶提供性服务。",
			QuestType.RELATIONSHIP,
			QuestTree.natalyaTree),

	ROMANCE_MONICA("莫妮卡的吸奶器", "你成功找回了莫妮卡的定制吸奶器，因此她非常感激你。",
			QuestType.RELATIONSHIP,
			QuestTree.monicaTree),
	;

	private String name, completedDescription;
	private QuestType type;
	private TreeNode<Quest> questTree;

	private QuestLine(String name, String completedDescription, QuestType type, TreeNode<Quest> questTree) {
		this.name = name;
		this.completedDescription = completedDescription;
		this.type = type;
		this.questTree = questTree;
	}

	public String getName() {
		return name;
	}

	public String getCompletedDescription() {
		return completedDescription;
	}

	public QuestType getType() {
		return type;
	}

	public TreeNode<Quest> getQuestTree() {
		return questTree;
	}

}
