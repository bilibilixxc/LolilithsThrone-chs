package com.lilithsthrone.world.places;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaDiningHallDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaDressingRoomDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaMilkingRoomDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaOfficeDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaSlaveLoungeDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaSpa;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomArthur;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;


/**
 * @since 0.1.85
 * @version 0.3.9
 * @author Innoxia
 */
public class PlaceUpgrade {

	//**** MISC. UPGRADES ****//
	
	public static final AbstractPlaceUpgrade SLAVERY_ADMINISTRATION_CELLS = new AbstractPlaceUpgrade(true,
			PresetColour.GENERIC_ARCANE,
			"叮当作响的隔间",
			"-",
			"",
			"芬奇没有花任何精力来维护奴隶管理局下面的牢房，因此，任何被关在这里的奴隶都会很快失去他们的好感和服从。",
			200,
			0,
			0,
			1000,
			-0.5f,
			-0.5f,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_EMPTY_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_GREY,
			"空房间",
			"萝丝会把这个房间恢复原状，这样就不适合你的奴隶居住了。",
			"这个房间是空的，如果你想把你的奴隶安置在这里，就需要进行改造。",
			"这间房没人入住。尽管被萝丝打扫得干净整洁，一尘不染，但遗憾的是，这间房似乎并没有发挥出它应有的潜力……",
			2000,
			0,
			0,
			0,
			0,
			0,
			null) {
		
		@Override
		public boolean isSlaverUpgrade() {
			return false;
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(LILAYA_MILKING_ROOM)) {
				Main.game.getOccupancyUtil().removeMilkingRoom(Main.game.getOccupancyUtil().getMilkingRoom(c.getType(), c.getLocation()));
			}
			
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_EMPTY_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			boolean nonCompanionCharactersPresent = false;
			if(cell.getCharactersPresentIds()!=null) {
				for(String id : cell.getCharactersPresentIds()) {
					if(!id.equals(Main.game.getPlayer().getId()) && !Main.game.getPlayer().getCompanionsId().contains(id)) {
						nonCompanionCharactersPresent = true;
					}
				}
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty() || nonCompanionCharactersPresent) {
				return new Value<>(false, "改造或出售开始前，这个房间需要被清空并且闲置。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_ARTHUR_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.RACE_HUMAN,
			"亚瑟的房间",
			"帮萝丝把奥术使用仪器移动到该房间以便于让房间更适合亚瑟居住。<b>这是个永久的修改，并且无法撤销！</b>",
			"这个房间现在属于亚瑟，他把它当作自己的个人实验室兼卧室。",
			"这间房没人入住。尽管被萝丝打扫得干净整洁，一尘不染，但遗憾的是，这间房似乎并没有发挥出它应有的潜力……",
			0,
			0,
			0,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			return RoomArthur.ROOM_ARTHUR_INSTALLATION;
		}
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			place.setPlaceType(PlaceType.LILAYA_HOME_ARTHUR_ROOM);
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_ARTHUR_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
			c.getPlace().setName("亚瑟的房间");
			if(Main.game.isStarted()) {
				Main.game.getNpc(Arthur.class).setLocation(c.getType(), c.getLocation(), true);
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_2_A_INTO_THE_DEPTHS)
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_ARTHUR_ROOM).isEmpty()
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_ARTHUR_ROOM).isEmpty()) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "改造开始前，这个房间需要被清空。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};


	//**** PLAYER'S ROOM UPGRADES ****//

	public static final AbstractPlaceUpgrade LILAYA_PLAYER_ROOM_BED = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_GOLD,
			"帝皇尺寸的床",
			"将你现在的特大号床换成一张巨大的“帝皇尺寸床”。[style.italicsGood(这将提高在房间里休息所获得的“充分休息”加成。)]",
			"原来的特大号床被巨大的“帝皇尺寸床”所取代。[style.italicsGood(在该房间休息会获得增强的“充分休息”状态！)]",
			"原来的特大号床被巨大的“帝皇尺寸床”所取代，靠着墙也非常抢眼。"
					+ "舒适的床垫、松软的枕头和温暖的羽绒被确保你在睡过之后总是感觉充分休息了。",
			10000,
			-5000,
			0,
			0,
			0.2f,
			-0.1f,
			null) {
		public Value<Boolean, String> getAvailability(Cell cell) {
			return new Value<>(true, "");
		}
	};

	//**** DOLL CLOSET ****//
	
	public static final AbstractPlaceUpgrade LILAYA_DOLL_CLOSET = new AbstractPlaceUpgrade(true,
			PresetColour.GENERIC_ARCANE,
			"玩偶贮藏室",
			"萝丝会清空这个房间的家具，用于储存你拥有的所有性爱玩偶。",
			"这个房间已经被改装成适合放置性爱玩偶的地方。",
			"你已经清空了这个房间，如此一来就能够储存你拥有的所有性爱玩偶。",
			250,
			0,
			0,
			12,
			0,
			0,
			null) {
		@Override
		public boolean isSlaverUpgrade() {
			return false;
		}
		
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomDoll", PresetColour.RACE_DOLL, PresetColour.BASE_WHITE, PresetColour.BASE_WHITE);
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DOLL_STORAGE)) {
				return new Value<>(false, "要安装一间玩偶贮藏室，你需要先征得莉莱雅的同意才能进行升级。");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "改造开始前，这个房间需要被清空。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_DOLL_CLOSET) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};
	
	//**** GUEST ROOM ****//
	
	public static final AbstractPlaceUpgrade LILAYA_GUEST_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.GENERIC_ARCANE,
			"客房",
			"萝丝将为你的一位客人准备这个房间，使其适合一个人居住。"
					+ "虽然这不是最经济的选择，但这个房间的住客无疑会喜欢拥有自己的私人空间。",
			"这个房间已被改装成适合你的客人居住的地方。",
			"你已付费将这间客房改建为基本客房。"
					+ "一张铺着素白羽绒被的单人床靠在一面墙边。"
					+ "床边有一个简单的床头柜，上面有一盏奥术台灯。"
					+ "除此之外，其他家具就只有一个木制衣柜和抽屉柜。",
			2000,
			0,
			100,
			1,
			0,
			0,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomGuest", PresetColour.BASE_GREEN_LIGHT);
		}
		@Override
		public boolean isSlaverUpgrade() {
			return false;
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
				return new Value<>(false, "要安装一间客卧，你需要先找一个人邀请他和你一起住，然后征得莉莱雅的同意才能进行升级。");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "改造或出售开始前，这个房间需要被清空。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_GUEST_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};

	
	//**** SLAVE UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.GENERIC_ARCANE,
			"奴隶房",
			"萝丝会像她对待任何其他客人那样准备这个房间，使其适合为一位你的奴隶居住。"
					+ "如果你打算拥有大量奴隶，这虽然不是最经济的选择，但这个房间的住客无疑会喜欢拥有自己的私人空间。",
			"这个房间已被改建成适合安置你的一名奴隶的地方。",
			"你付钱将这个房间改装成了基本奴隶宿舍。"
					+ "一张铺着素白羽绒被的单人床靠在一面墙边。"
					+ "床边有一个简单的床头柜，上面有一盏奥术台灯。"
					+ "除此之外，其他家具就只有一个木制衣柜和抽屉柜。",
			2000,
			0,
			100,
			1,
			0,
			0,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlave", PresetColour.BASE_CRIMSON);
		}
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return "你付钱将这个房间改装成了基本奴隶宿舍。"
						+ "一张舒适的，铺着温暖松软的羽绒被的双人床靠在一面墙边。"
						+ "床边有一个简单的床头柜，上面有一盏奥术台灯。"
						+ "除此之外，这里只有一个木制衣柜和抽屉柜。";
				
			} else if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED)) {
				return "你付钱将这个房间改装成了基本奴隶宿舍。"
						+ "一张铺着一张薄毯子的不舒适的单人床靠在一面墙边。"
						+ "床边有一个简单的床头柜，上面有一盏奥术台灯。"
						+ "除此之外，这里只有一个木制衣柜和抽屉柜。";
		
			}else {
				return "你付钱将这个房间改装成了基本奴隶宿舍。"
						+ "一张铺着素白羽绒被的单人床靠在一面墙边。"
						+ "床边有一个简单的床头柜，上面有一盏奥术台灯。"
						+ "除此之外，这里只有一个木制衣柜和抽屉柜。";
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "改造开始前，这个房间需要被清空。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_SLAVE_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_DOUBLE = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_MAGENTA,
			"双人奴隶房",
			"萝丝会像她对待任何其他客人那样准备这个房间，使其适合为两位你的奴隶居住。"
					+ "这样做更省钱，但住客们无疑会因为需要与其它奴隶分享私人空间而沮丧，"
						+ "同时也给他们留下了密谋反抗你的机会。",
			"这个房间已被改装成适合两个你的奴隶居住的地方。",
			"你出资将房间改装成了双人间。"
					+ "一对单人床，铺着素白的羽绒被，分别靠在相对的两面墙边。"
					+ "每张床边都有一个简单的床头柜，上面有一盏奥术台灯。"
					+ "除此之外，这里只有一个木质衣柜和抽屉柜。",
			3500,
			0,
			100,
			2,
			-0.05f,
			-0.05f,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveDouble", PresetColour.BASE_MAGENTA);
		}
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return "你出资将房间改装成了双人间。"
							+ "两张双人床，铺着温暖松软的羽绒被，分别靠在相对的两面墙边。"
							+ "每张床边都有一个简单的床头柜，上面有一盏奥术台灯。"
							+ "除此之外，其他家具就只有一对木制衣柜和两个抽屉柜。";
				
			} else if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED)) {
				return "你出资将房间改装成了双人间。"
						+ "两张不太舒服的单人床，只铺上了薄薄的毯子，靠着墙边。"
						+ "每张床边都有一个简单的床头柜，上面有一盏奥术台灯。"
						+ "除此之外，这里只有一个木质衣柜和抽屉柜。";
		
			}else {
				return "你出资将房间改装成了双人间。"
					+ "一对单人床，铺着素白的羽绒被，分别靠在相对的两面墙边。"
					+ "每张床边都有一个简单的床头柜，上面有一盏奥术台灯。"
					+ "除此之外，这里只有一个木质衣柜和抽屉柜。";
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty() && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)) {
				return new Value<>(false, "改造或出售开始前，这个房间需要被清空。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)) {
				place.removePlaceUpgrade(c, LILAYA_SLAVE_ROOM);
				
			} else {
				for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
					if(upgrade != LILAYA_SLAVE_ROOM_DOUBLE) {
						place.removePlaceUpgrade(c, upgrade);
					}
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_QUADRUPLE = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_MAGENTA,
			"四人奴隶房",
			"萝丝会像她对待任何其他客人那样准备这个房间，使其适合为四位你的奴隶居住。"
					+ "虽然比起每个奴隶一个单独的房间更加经济，但住在其中的人肯定不太乐意，因为必须要跟其他奴隶分享个人空间，"
						+ "同时也给他们留下了很多密谋反抗你的机会。",
			"这个房间已被改建成适合安置四名奴隶的地方。",
			"你出资将房间改装成了四人间。"
					+ "四张单人床，铺着素白色的羽绒被，靠在墙边。"
					+ "每张床边都有一个简单的床头柜，上面有一盏奥术台灯。"
					+ "除此之外，其他家具就只有一对木制衣柜和两个抽屉柜。",
			6000,
			0,
			100,
			4,
			-0.1f,
			-0.2f,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveQuadruple", PresetColour.BASE_MAGENTA);
		}
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return "你出资将房间改装成了四人间。"
						+ "四张双人床，铺着温暖松软的羽绒被，靠在墙边。"
						+ "每张床边都有一个简单的床头柜，上面有一盏奥术台灯。"
						+ "除此之外，其他家具就只有四个木制衣柜和抽屉柜。";
				
			} else if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED)) {
				return "你出资将房间改装成了四人间。"
						+ "四张不太舒服的小床，只铺上了薄薄的毯子，靠着墙边。"
						+ "每张床边都有一个简单的床头柜，上面有一盏奥术台灯。"
						+ "除此之外，其他家具就只有一对木制衣柜和两个抽屉柜。";
		
			}else {
				return "你出资将房间改装成了四人间。"
					+ "四张单人床，铺着素白色的羽绒被，靠在墙边。"
					+ "每张床边都有一个简单的床头柜，上面有一盏奥术台灯。"
					+ "除此之外，这里只有一个木质衣柜和抽屉柜。";
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()
					 && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)
					 && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				return new Value<>(false, "改造开始前，这个房间需要被清空。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)) {
				place.removePlaceUpgrade(c, LILAYA_SLAVE_ROOM);
				
			} else if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				place.removePlaceUpgrade(c, LILAYA_SLAVE_ROOM_DOUBLE);
				
			} else {
				for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
					if(upgrade != LILAYA_SLAVE_ROOM_QUADRUPLE) {
						place.removePlaceUpgrade(c, upgrade);
					}
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_DOWNGRADE_BED = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_BAD,
			"小铁床",
			"将该房间的床替换为钢框架的床，上面铺一张不舒服的床垫，一个硬枕头和一条薄毯。"
					+ "这些改动无疑会让住在其中的人意识到他们确确实实成为了奴隶。",
			"房间内安放着一张单人小床，床上有一张不舒服的床垫、一个硬枕头和一条薄毯。",
			"房间的一侧放着一张单人小床，床上有一张不舒服的床垫、一个硬枕头和一条薄毯。"
					+ "给房间里的住户提供如此不适的休息场所，肯定会强化他们是你的奴隶的想法，但同时他们必然也会更讨厌你……",
			250,
			100,
			-10,
			0,
			-0.1f,
			0.2f,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_QUADRUPLE)) {
				return "房间中的四张单人床已经被替换为钢框架的床，只剩下不舒服的床垫、僵硬的枕头和单薄的毯子。"
						+ "给房间里的住户提供如此不适的休息场所，肯定会进一步让他们接受自己不过是你的奴隶，但同时他们必然也会更讨厌你……";
				
			} else if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				return "房间中的两张单人床已经被替换为钢框架的床，只剩下不舒服的床垫、僵硬的枕头和单薄的毯子。"
						+ "给房间里的住户提供如此不适的休息场所，肯定会进一步让他们接受自己不过是你的奴隶，但同时他们必然也会更讨厌你……";
				
			} else {
				return "房间中的小型单人床已经被替换为钢框架的床，只剩下不舒服的床垫、硬枕头和薄毯。"
						+ "给房间里的住户提供如此不适的休息场所，肯定会进一步让他们接受自己不过是你的奴隶，但同时他们必然也会更讨厌你……";
			}
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return new Value<>(false, "在安装“小铁床”之前，必须先卸下“双人床”升级件。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_UPGRADE_BED = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_GOOD,
			"双人床",
			"在该房间中安置双人床，配备有更舒服的床垫、蓬松的枕头和温暖的羽绒被。"
					+ "房间里的住户肯定会感激这项改造。",
			"房间内安放着一张双人床，配备有舒适的床垫、蓬松的枕头和温暖的羽绒被。",
			"房间的一侧放着一张双人床，床上有一张舒服的床垫、一个蓬松的枕头和一张温暖的羽绒被。"
					+ "给房间里的住户提供如此舒适的休息场所，肯定会让他们更喜欢你，但这种奢华可能会令其忘记自己的地位……",
			500,
			200,
			25,
			0,
			0.2f,
			-0.1f,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_QUADRUPLE)) {
				return "房间中的四张单人床已经被替换为双人床，配备了舒适的床垫、蓬松的枕头和温暖的羽绒被。"
						+ "给房间里的住户提供如此舒适的休息场所，肯定会让他们更喜欢你，但这种奢华可能会令其忘记自己的地位……";
				
			} else if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				return "房间中的两张单人床已经被替换为双人床，配备了舒适的床垫、蓬松的枕头和温暖的羽绒被。"
						+ "给房间里的住户提供如此舒适的休息场所，肯定会让他们更喜欢你，但这种奢华可能会令其忘记自己的地位……";
				
			} else {
				return "房间的一侧放着一张双人床，配备了舒适的床垫、蓬松的枕头和温暖的羽绒被。"
						+ "给房间里的住户提供如此舒适的休息场所，肯定会让他们更喜欢你，但这种奢华可能会令其忘记自己的地位……";
			}
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOWNGRADE_BED)) {
				return new Value<>(false, "在安装“双人床”之前，必须先卸下“小铁床”升级件。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"服从训练器",
			"莉莱雅问你是否愿意考虑在这个房间里安装她的一个实验装置，即所谓的服从训练器。"
					+ "这个特别的附加装置是一块发光的大水晶，需要放置在房间的中央。"
					+ "每当房间里的人有不听话的念头时，水晶就会发射出令人震惊的奥术能量，从而以可以想象到的最具侵犯性的方式训练奴隶的服从性。",
			"你在这个房间里安装了莉莱雅的一个实验装置，即所谓的服从训练器。"
					+ "房间中央放置了一块巨大的发光水晶。"
					+ "每当房间里的人有不听话的念头时，水晶就会发射出令人震惊的奥术能量，从而以可以想象到的最具侵犯性的方式训练奴隶的服从性。",
			"莉莱雅的一个实验装置，即所谓的“服从训练器”，就安装在这个房间的中央。"
					+ "服从训练器的外形是一块发光的大水晶，它会向附近任何胆敢有不服从想法的奴隶发射一道震撼人心的奥术能量弹。"
					+ "虽然这种训练方法在训练服从性方面非常有效，但任何接受过这种侵入式训练的奴隶都肯定会在不久后厌恶你……",
			10000,
			500,
			250,
			0,
			-0.2f,
			0.4f,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_DOG_BOWLS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_BAD,
			"狗碗",
			"用放在地上的狗碗给房间里的人送饭。"
					+ "被迫以这种屈辱的方式进食肯定会让你的奴隶们讨厌你，但这也会强调一个事实，那就是他们只不过是你的财产而已。",
			"这个房间的地上放着金属狗碗，房间里的人就是用这些狗碗吃饭的。"
					+ "被迫以如此屈辱的方式进食会让你的奴隶们讨厌你，但同时也是在强调他们不过是你的财产这一事实。",
			"房间的一角摆放着一系列金属狗碗，房间里的人就是从这些狗碗里进食和喝水的。"
					+ "被迫四脚着地，像狗一样进食，这让你的奴隶们不喜欢你，但同时，这也让他们明白，他们不过是你的财产……",
			100,
			0,
			10,
			0,
			-0.2f,
			0.25f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_ROOM_SERVICE)) {
				return new Value<>(false, "在使用“狗碗”之前，必须先取消“客房服务”升级。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_ROOM_SERVICE = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"客房服务",
			"你可以为这间客房的住客提供无限量的客房服务。"
					+ "这并不是大多数主人对待奴隶的方式，虽然这样做肯定会让住客更喜欢你，但也会花费大量的维护费用，还会对他们的服从性产生一些负面影响……",
			"你为这间客房的住客提供了无限量的客房服务。"
					+ "这肯定会让他们更喜欢你，但也要花费相当多的维护费用，对你的奴隶的服从性也有负面影响……",
			"一辆小推车上堆放着几个空银盘和玻璃杯，这证明住在这里的奴隶们正在充分利用你为他们提供的无限量客房服务。"
					+ "这肯定会让他们更喜欢你，但让他们拥有这样的奢侈品也会对他们的服从性产生负面影响，更不用说这对你的经济造成的损害了……",
			500,
			0,
			250,
			0,
			0.4f,
			-0.2f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOG_BOWLS)) {
				return new Value<>(false, "在使用“客房服务”之前，必须先移除“狗碗”升级。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"奥术仪器",
			"允许莉莱雅在这个房间里安装奥术感应器，这样她就能收集到关于你的奴隶的灵气的有用数据。"
					+ "你的奴隶们会觉得这是对他们仅有的个人空间的侵犯，他们对你的感情也会因此受到影响。",
			"这个房间周围安装了一系列奥术传感器，让莉莱雅能够收集到关于你的奴隶的灵气的有用数据。"
					+ "你的奴隶会觉得这是对他们仅有的个人空间的侵犯，他们对你的感情也会因此受到影响。",
			"作为降低房间维护成本的交换条件，莉莱雅在房间周围安装了几台奥术仪器，通过这些仪器，她可以收集任何被安置在这里的奴隶的数据。"
					+ "其中有几个会发出非常安静的嗡嗡声，再加上它们微弱的紫色光芒，使它们变得相当具有侵扰性，并会使居住者对你的好感产生负面影响。",
			500,
			100,
			-25,
			0,
			-0.1f,
			0f,
			null) {
	};

	//**** DUNGEON UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_MAGENTA,
			"地牢隔间",
			"-",
			"这隔间恰好能容纳四个倒霉的奴隶。",
			"-",
			0,
			0,
			0,
			4,
			-0.15f,
			0.2f,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveQuadruple", PresetColour.BASE_MAGENTA);
		}
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("隔间的墙壁、地面和天花板都是用灰色的大石头砌成的，给人一种阴沉、压抑的感觉。"
					+ "隔间的两侧墙壁上固定着几个金属烛台，每个烛台上都有一个奥术火把。"
					+ "永不熄灭的烛火不时明灭跳跃，在房间内投下舞动的影子。"
					+ "厚重的木门上有一扇带铁栅栏的小窗，这样就可以在安全的走廊上监视被关在里面的奴隶。");
			
			if(!place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_DUNGEON_CELL_UPGRADE_BED) && !place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_DUNGEON_CELL_DOWNGRADE_BED)) {
				sb.append("<br/><br/>"
						+ "隔间里有四张钢架小床，每张床上都铺着薄床垫，放着干瘪的枕头和破旧的毯子。"
						+ "床边的老旧木箱充作简易床头柜使用。"
						+ "除了床和板条箱，只余四壁。");
			}
			
			return sb.toString();
		}
	};
	

	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_DOWNGRADE_BED = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_BAD,
			"稻草被褥",
			"拆除该囚室的钢架床并以稻草堆代之。"
					+ "给牢房里的奴隶们提供如此不舒适的睡觉场所，肯定会让他们更加意识到他们不过是你的财产，但与此同时，他们肯定会更加讨厌你……",
			"这个房间里的床已被移走，取而代之的是一堆稻草。",
			"房间的一侧放着一张单人小床，床上有一张不舒服的床垫、一个硬枕头和一条薄毯。"
					+ "给这个牢房的奴隶们提供这么不舒服的地方睡觉，无疑是在强化他们只是你的财产这一事实，但与此同时，他们也开始更加讨厌你了……",
			100,
			25,
			0,
			0,
			-0.1f,
			0.2f,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			return "这个房间里的四张钢架床已全部拆除，取而代之的是一堆稻草。"
					+ "用来代替床头柜的旧木箱也被包装稻草的简易木框所取代。"
					+ "给牢房里的奴隶们提供如此不舒适的睡觉场所，肯定会让他们更加意识到他们不过是你的财产，但与此同时，他们肯定会更加讨厌你……";
				
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_UPGRADE_BED)) {
				return new Value<>(false, "在安装“稻草被褥”之前，必须先移除“改良的床上用品”升级版。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_UPGRADE_BED = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_GOOD,
			"改良的床上用品",
			"拆掉床上的薄床垫、瘪枕头和破毯子，换上更舒适的替代品。"
					+ "为这个牢房的奴隶提供更高的舒适度肯定会让他们更喜欢你，尽管这种相对的奢华可能会让他们忘记自己的地位……",
			"牢房里的四张床的床垫、枕头和毯子都换成了更舒适的替代品。",
			"牢房里的四张床的床垫、枕头和毯子都换成了更舒适的替代品。"
					+ "为这个牢房的奴隶提供更高的舒适度肯定会让他们更喜欢你，尽管这种相对的奢华让他们忘记了自己的地位……",
			250,
			50,
			5,
			0,
			0.05f,
			-0.05f,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			return "牢房里的四张床的床垫、枕头和毯子都换成了更舒适的替代品。"
					+ "为这个牢房的奴隶提供更高的舒适度肯定会让他们更喜欢你，尽管这种相对的奢华可能会让他们忘记自己的地位……";
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_DOWNGRADE_BED)) {
				return new Value<>(false, "在安装“改良的床上用品”之前，必须先移除“稻草被褥”升级。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};

	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_DOG_BOWLS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_BAD,
			"狗碗",
			"用放在地上的狗碗给牢房里的奴隶送饭。"
					+ "被迫以这种屈辱的方式进食肯定会让你的奴隶们讨厌你，但这也会强调一个事实，那就是他们只不过是你的财产而已。",
			"地上放着金属狗碗，牢房里的奴隶就是用这些狗碗吃饭的。",
			"地上放着四对金属狗碗，牢房里的奴隶们要用这些狗碗吃饭喝水。"
					+ "被迫四脚着地，像狗一样进食，这让你的奴隶们不喜欢你，但同时，这也让他们明白，他们不过是你的财产……",
			100,
			0,
			10,
			0,
			-0.2f,
			0.25f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_DECENT_FOOD)) {
				return new Value<>(false, "在使用“狗碗”之前，必须先移除“体面食物”升级。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_DECENT_FOOD = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_GOOD,
			"体面的食物",
			"用像样的热食取代为牢房奴隶提供的冰冷无味的饭菜。"
					+ "为这个牢房的奴隶们提供更美味的食物肯定会让他们更喜欢你，尽管这种相对的奢侈可能会让他们忘记自己的地位……",
			"你们为牢房的奴隶们换上了热气腾腾的饭菜。",
			"你们为牢房的奴隶们换上了热气腾腾的饭菜。"
					+ "给这个牢房的奴隶们提供更美味的食物肯定会让他们更喜欢你，尽管这种相对的奢侈让他们忘记了自己的地位……",
			250,
			0,
			100,
			0,
			0.1f,
			-0.1f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_DOG_BOWLS)) {
				return new Value<>(false, "在使用“体面食物”之前，必须先移除“狗碗”升级。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};

	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_ROPES = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_TERRIBLE,
			"绳缚装置",
			"在牢房的墙壁上拴上结实的绳索，用它们将奴隶们捆绑到位。"
					+ "这将阻止你的奴隶相互交流，如果处于顺从状态，他们在开始性爱场景时将会固定获得“绳缚”的效果。",
			"牢房的墙壁上拴着结实的绳子，用来捆绑牢房里的奴隶。",
			"牢房的墙壁上拴着结实的绳子，用来捆绑牢房里的奴隶。"
					+ "这妨碍了你的奴隶们相互交流，使他们更加听话，但代价是更加不喜欢你……",
			250,
			100,
			10,
			0,
			-0.2f,
			0.15f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_CHAINS)) {
				return new Value<>(false, "在使用“绳缚装置”之前，必须先移除“链缚装置”升级。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public ImmobilisationType getImmobilisationType() {
			return ImmobilisationType.ROPE;
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_CHAINS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_TERRIBLE,
			"链缚装置",
			"在牢房的墙壁上安装金属链，用它们将奴隶们捆绑到位。"
					+ "这将阻止你的奴隶相互交流，如果处于顺从状态，他们在开始性爱场景时将会固定获得“链缚”的效果。",
			"牢房的墙壁上已经绑上了金属链条，用来捆绑牢房里的奴隶。",
			"牢房的墙壁上已经绑上了金属链条，用来捆绑牢房里的奴隶。"
					+ "这妨碍了你的奴隶们相互交流，使他们更加听话，但代价是更加不喜欢你……",
			500,
			250,
			25,
			0,
			-0.25f,
			0.3f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_ROPES)) {
				return new Value<>(false, "在使用“链缚装置”之前，必须先卸下“绳缚装置”升级。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public ImmobilisationType getImmobilisationType() {
			return ImmobilisationType.CHAINS;
		}
	};
	
	
	//**** MILKING UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_ORANGE,
			"挤奶室",
			"在该房间安装挤奶机器，允许你的[style.colourGood(八个)]奴隶被安排在此工作，每人都会被挤出乳汁或者精液。<br/>"
					+ "<i>乳汁："+Units.fluid(MilkingRoom.BASE_MILKING_AMOUNT)+"每小时<br/>"
					+ "精液："+Units.fluid(MilkingRoom.BASE_CUM_MILKING_AMOUNT)+"每小时<br/>"
					+ "爱液"+Units.fluid(MilkingRoom.BASE_GIRLCUM_MILKING_AMOUNT)+"每小时</i>",
			"这个房间已被改建成适合榨取[style.colourGood(八名)]奴隶的乳汁和精液的地方。",
			"这个房间已被改建成一处特殊产奶间，可以榨取八名奴隶的各种体液。"
					+ "左手边的墙上排列着四台机器，而另一边则是另外四台。",
			10000,
			0,
			500,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaMilkingRoomDialogue.MILKING_ROOM;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomMilking", PresetColour.BASE_YELLOW_LIGHT);
		}
		@Override
		public String getRoomDescription(Cell c) {
			MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(c.getType(), c.getLocation());
			
			return room.getRoomDescription();
		}
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_MILKING_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
			if(Main.game.getOccupancyUtil().getMilkingRoom(c.getType(), c.getLocation())==null) {
				Main.game.getOccupancyUtil().addMilkingRoom(new MilkingRoom(c.getType(), c.getLocation()));
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "改造或出售开始前，这个房间需要被清空。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_ARTISAN_MILKERS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"手工挤奶机",
			"你可以将该房间中的标准挤奶装置替换为极其昂贵的手工挤奶机。"
					+ "虽然奴隶使用起来会更舒服，但这种挤奶机器最初是设计用来展示的，实用性不佳，导致挤奶量相比常规机器略微降低。<br/>"
					+ "<i>产乳"+Units.fluid(MilkingRoom.ARTISAN_MILKING_AMOUNT)+"每小时<br/>"
					+ "精液："+Units.fluid(MilkingRoom.ARTISAN_CUM_MILKING_AMOUNT)+" 每小时<br/>"
					+ "爱液："+Units.fluid(MilkingRoom.ARTISAN_GIRLCUM_MILKING_AMOUNT)+"每小时</i>",
			"你已经在该房间安装了手工挤奶机。"
					+ "有幸被锁在这些机器里的奴隶们肯定会感激你。<br/>"
					+ "<i>产乳"+Units.fluid(MilkingRoom.ARTISAN_MILKING_AMOUNT)+"每小时<br/>"
					+ "精液："+Units.fluid(MilkingRoom.ARTISAN_CUM_MILKING_AMOUNT)+" 每小时<br/>"
					+ "爱液："+Units.fluid(MilkingRoom.ARTISAN_GIRLCUM_MILKING_AMOUNT)+"每小时</i>",
			"奥术驱动的手工挤奶机器已经安放在该房间，周围的空气中充满了柔和、悦耳的轰鸣声。"
					+ "尽管用起来相比常规挤奶机器更加舒适，但似乎最初是设计用来展示的，实用性不佳，你的奴隶肯定会觉得开心，但挤奶量相比平常略低。",
			2500,
			500,
			500,
			0,
			1f,
			0.5f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)) {
				return new Value<>(false, "在安装“手工挤奶机”之前，必须先移除“工业挤奶机”升级程序。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"工业挤奶机",
			"你可以将该房间中的标准挤奶装置替换为工业级的机器。"
					+ "虽然这些挤奶机对于被挤奶的奴隶来说不那么舒适，但却能最大限度地提高产奶量和利润。<br/>"
					+ "<i>产乳："+Units.fluid(MilkingRoom.INDUSTRIAL_MILKING_AMOUNT)+"每小时<br/>"
					+ "精液："+Units.fluid(MilkingRoom.INDUSTRIAL_CUM_MILKING_AMOUNT)+" 每小时<br/>"
					+ "爱液："+Units.fluid(MilkingRoom.INDUSTRIAL_GIRLCUM_MILKING_AMOUNT)+" 每小时</i>",
			"你已经在该房间安装了工业挤奶机器。"
					+ "不幸被锁在这些机器里的奴隶们肯定会憎恨你。<br/>"
					+ "<i>产乳："+Units.fluid(MilkingRoom.INDUSTRIAL_MILKING_AMOUNT)+"每小时<br/>"
					+ "精液："+Units.fluid(MilkingRoom.INDUSTRIAL_CUM_MILKING_AMOUNT)+" 每小时<br/>"
					+ "爱液："+Units.fluid(MilkingRoom.INDUSTRIAL_GIRLCUM_MILKING_AMOUNT)+" 每小时</i>",
			"奥术驱动的工业挤奶机器已经安放在该房间，周围的空气中充满了连续不断的轰鸣声。"
					+ "尽管这些机器能够最大化产奶量，但被绑在这些装置上肯定不算舒服，无论哪个奴隶在这里被挤奶肯定都会对你心生怨念……",
			1500,
			500,
			1000,
			0,
			-1f,
			0.5f,
			null) {
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_MILKING_ROOM_ARTISAN_MILKERS)) {
				return new Value<>(false, "在安装“工业挤奶机”之前，必须先移除“手工挤奶机”升级。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_MILK_EFFICIENCY = new AbstractPlaceUpgrade(false,
			PresetColour.MILK,
			"榨乳乐",
			"生产挤奶机的公司还提供一系列售后市场升级产品。其中之一是“榨乳乐”，它可将挤奶机的最高每小时挤奶效率提高一倍。",
			"你为这个挤奶室的挤奶机加装了可选的“榨乳乐”，使它们每小时的最高挤奶效率提高了一倍。",
			"每台机器上的标准吸盘已被选配的“榨乳乐”取代，这种吸盘可将每小时的最大挤奶量提高一倍。",
			500,
			100,
			200,
			0,
			0,
			0,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_CUM_EFFICIENCY = new AbstractPlaceUpgrade(false,
			PresetColour.CUM,
			"魅魔管",
			"生产挤奶机的公司还提供一系列售后市场升级产品。其中之一是“魅魔管”，它能将挤奶机的最高每小时榨精效率提高一倍。",
			"你为这个房间的挤奶机加装了可选的“魅魔管”，使它们每小时的最高榨精效率提高了一倍。",
			"每台挤奶机上的标准挤奶管已被选配的“魅魔管”所取代，这使得每小时的最大榨精量翻了一番。",
			500,
			100,
			200,
			0,
			0,
			0,
			null) {
	};

	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_GIRLCUM_EFFICIENCY = new AbstractPlaceUpgrade(false,
			PresetColour.GIRLCUM,
			"仙女泵",
			"生产挤奶机的公司还提供一系列售后市场升级产品。其中之一是“仙女泵”，它能将挤奶机每小时的最大挤爱液效率提高一倍。",
			"你为这个房间的挤奶机加装了可选的“仙女泵”，使它们每小时的最大挤爱液效率提高了一倍。",
			"每台机器上的标准阴道泵已被选配的“仙女泵”取代，这种泵可将每小时提取的爱液量增加一倍。",
			500,
			100,
			200,
			0,
			0,
			0,
			null) {
	};

	
	//**** OFFICE UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_OFFICE = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_TEAL,
			"办公室",
			"由于莉莱雅的实验室经常需要订购受到严格管制的外来材料，因此她每个月都需要完成大量的文书工作。"
					+ "让萝丝把这个房间的家具换成办公桌、椅子和文件柜，你就可以把它变成一个办公场所，你的[style.colorGood(四)]个奴隶就可以拿钱为她完成这些工作。"
					+ "[style.italicsGood(你还可以在办公室查阅“住户名单”！)]",
			"这个房间已被改建成办公室，有足够的办公桌和空间来舒适地容纳[style.colorGood(四)]名工作人员。"
					+ "[style.italicsGood(在办公室时，你还可以访问“住户名单”！)]",
			"为了帮助莉莱雅处理大量与外来材料收购有关的文书工作，你把这个房间改建成了一个可容纳四人的办公室。"
					+ "除了与莉莱雅严格监管的采购相关的表格外，分配到这里的工作人员还负责在“住户名单”中保存记录，你可以随时在这里查阅。",
			8000,
			500,
			250,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaOfficeDialogue.ROOM_OFFICE;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomOffice", PresetColour.BASE_LILAC);
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_OFFICE).isEmpty()
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_OFFICE).isEmpty()) {
				return new Value<>(false, "没有足够的证明材料，不能证明有理由设立一个以上的办公室。");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "改造或出售开始前，这个房间需要被清空。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_OFFICE) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_OFFICE_EXECUTIVE_UPGRADE = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_GOLD,
			"行政办公室",
			"让萝丝为这间办公室配备她能弄到的最豪华、最奢侈的家具。"
					+ "虽然这间办公室一定会让任何被分配到这里的员工感到肃然起敬，但御城区最好的家具并不便宜……",
			"你为这间办公室配备了最高档的家具，这一定会让被派到这里工作的人对你肃然起敬。",
			"你已经为这间办公室配备了金钱所能买到的最豪华、最奢侈的家具。"
					+ "四个工位都有自己的雕刻精美的红木办公桌，办公桌后面的墙壁上固定着书架，上面摆满了手工制作的皮革装订的唱片书籍。",
			500_000,
			-200_000,
			50,
			0,
			0.25f,
			1f,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_OFFICE_COFFEE_MACHINE = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_BROWN_DARK,
			"咖啡机",
			"萝丝告诉你，她知道有一种极好的奥术咖啡机可以购买。"
					+ "这台机器可以提供各种冷热咖啡饮品，这样的机器一定会受到办公室工作人员的青睐。",
			"你为这间办公室购买了一台奥术咖啡机，分配到这里工作的奴隶们对此非常感激。",
			"房间的一角摆放着一台弧形咖啡机，能够提供各种冷热咖啡饮品。",
			5000,
			100,
			250,
			0,
			0.1f,
			0.05f,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_OFFICE_PARTITIONING_WALLS = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_TAN,
			"隔断墙",
			"你可以在这间办公室里设置一些隔断墙，这样就可以给分配到这里的四名员工每人提供自己的私人空间，让他们可以继续工作。",
			"你在这间办公室里设置了几面隔断墙，为每位员工提供了更多的私人空间，让他们可以安静地处理分配给他们的工作。",
			"这间办公室的四个工位都用隔墙分隔开来，给员工们提供了一些私人空间，让他们可以安静地完成分配给他们的任务。",
			2500,
			500,
			0,
			0,
			0.05f,
			0,
			null) {
	};

	
	//**** DRESSING ROOM UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_DRESSING_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_GOLD,
			"更衣室",
			"虽然每间卧室都足够容纳一两个衣柜，但能有一整个房间专门存放衣服也不错。",
			"这个特殊的房间被改造成了更衣室，拥有足够的衣柜空间来存放数百件衣物。",
			"你将这个特定房间改造成了专用更衣室。"
					+ "几十个衣柜为数百件衣物提供了储存空间。",
			5000,
			250,
			50,
			0,
			0,
			0,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			StringBuilder sb = new StringBuilder();
			sb.append(super.getRoomDescription(c));
			
			if(!place.getPlaceUpgrades().contains(LILAYA_DRESSING_ROOM_LYSSIETH_WARDROBE)) {
				sb.append("莉西丝的衣柜，由象牙雕刻而成，并用黄金装饰，在这间房间里占据着显眼的位置。"
						+ "其独特的附魔能让你仅凭想象就能凭空创造物品，但目前无法使用，而且让莉莱雅来修理会非常昂贵。");
			}
			
			return sb.toString();
		}
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.dressingRoomLyssiethsWardrobeActivated)) {
				return LilayaDressingRoomDialogue.INSTALLATION;
			}
			return super.getInstallationDialogue(c);
		}
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaDressingRoomDialogue.ROOM_DRESSING_ROOM;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomDressingRoom", PresetColour.BASE_GOLD);
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_DRESSING_ROOM).isEmpty()
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_DRESSING_ROOM).isEmpty()) {
				return new Value<>(false, "你只能拥有一个更衣室。");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "改造或出售开始前，这个房间需要被清空。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_DRESSING_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.dressingRoomLyssiethsWardrobeActivated)) {
				place.addPlaceUpgrade(c, LILAYA_DRESSING_ROOM_LYSSIETH_WARDROBE);
			}
		}
	};

	public static final AbstractPlaceUpgrade LILAYA_DRESSING_ROOM_LYSSIETH_WARDROBE = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"莉西丝的衣柜",
			"让莉莱雅重新为莉西丝那特殊的衣柜附魔后，将拥有不可思议的力量——能创造出几乎任何你能想象到的武器或衣物。"
					+ "<br/>[style.italicsGood(这将为你和你的奴隶解锁“服装”功能。)]",
			"一尊由象牙雕琢、黄金装饰的非凡衣柜，在更衣室里占据着最显眼的位置。"
					+ "这件独特的家具由莉琳长老莉西丝委托打造，拥有不可思议的力量，几乎能创造出你所能想象的任何武器或衣物。"
					+ "<br/>[style.italicsGood(莉西丝的衣柜将为你和你的奴隶解锁“服装”功能。)]",
			"一尊由象牙雕琢、黄金装饰的非凡衣柜，在更衣室里占据着最显眼的位置。"
					+ "这件独特的家具由莉琳长老莉西丝委托打造，拥有不可思议的力量，几乎能创造出你所能想象的任何武器或衣物。",
			250_000,
			0,
			0,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			return LilayaDressingRoomDialogue.WARDROBE_ACTIVATION;
		}
		@Override
		public Value<Boolean, String> getRemovalAvailability(Cell cell) {
			return new Value<>(false, "你不能移除莉西丝的衣柜。");
		}
	};
	

	//**** SPA UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_SPA = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_AQUA,
			"水疗中心",
			"通过彻底拆除和重新装修这个房间，可以将其改造成一个私人水疗中心。"
					+ "毫无疑问，这将是这座豪宅的一大亮点，但重新铺设地热泉水管道以建造室内泳池的费用将极其昂贵……"
					+ "<br/>[style.italicsbad(只能安装一个水疗中心，而且不能拆除，因此在安装之前，请确保你希望在该地块上安装水疗中心！)]",
			"这个房间被彻底翻新，改建成了一间豪华的私人水疗中心。"
					+ "在大理石地面中央，有一连串大水池，每个池子里都装满了从地热泉中汲取的温水。",
			"这个房间被彻底的翻新，改建成了一间豪华的私人水疗中心，内有私人淋浴间和更衣间。"
					+ "在大理石地面中央，有一连串大水池，每个池子里都装满了从地热泉中汲取的温水。"
					+ "房间里罗列着许多舒适的躺椅，如果你不想泡在泳池里，这些躺椅也是你放松和休憩的好去处。",
			1_500_000,
			250_000,
			500,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			LilayaSpa.setCellInstallation(c);
			return LilayaSpa.SPA_INSTALLATION;
		}
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaSpa.SPA_RECEPTION;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSpaReception", PresetColour.BASE_BLUE_STEEL);
		}
		@Override
		public boolean isSlaverUpgrade() {
			return false;
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_SPA).isEmpty()
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_SPA).isEmpty()) {
				return new Value<>(false, "你只能建造一个水疗中心！");
			}
			int size = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).WORLD_WIDTH;
			if(cell.getType()!=WorldType.LILAYAS_HOUSE_GROUND_FLOOR
					|| cell.getLocation().getY()<2
					|| (Math.abs(cell.getLocation().getY()-size)>2 && Math.min(cell.getLocation().getX(), Math.abs(size-cell.getLocation().getX()))>2)) {
				return new Value<>(false, "水疗中心只能建在一楼的北侧、东侧或西侧！");
			}
			if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& (Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0) || Main.game.getNpc(Lilaya.class).isPregnant())) {
				return new Value<>(false, "莉莱雅需要先解决怀孕的问题！");
			}
			if(Lab.isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
				return new Value<>(false, "你得先和莉莱雅谈谈你的恶魔形态！");
			}
			if(!Main.game.isExtendedWorkTime()) {
				return new Value<>(false, "你只能在莉莱雅醒着的时候扩建水疗中心！");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "改造开始前，这个房间需要被清空。");
			}
			
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public void applyInstallationEffects(Cell cell) {
			GenericPlace place = cell.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_SPA) {
					place.removePlaceUpgrade(cell, upgrade);
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SPA_SAUNA = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_ROSE,
			"桑拿房(拓展)",
			"水疗中心还有相当大的扩建空间……"
				+ "<br/>如果你愿意，你可以向莉莱雅建议，桑拿房将是水疗中心的一个重要补充。",
			"",
			"",
			150_000,
			25_000,
			100,
			0,
			0.25f,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			return LilayaSpa.SPA_SAUNA_INSTALLATION;
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return new Value<>(false, "你需要等待水疗中心扩建工程完工后才能扩建它！");
			}
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION)!=null) {
				return new Value<>(false, "你需要等待游泳池扩建工程完工后再开始这个工程！");
			}
			if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& (Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0) || Main.game.getNpc(Lilaya.class).isPregnant())) {
				return new Value<>(false, "莉莱雅需要先解决怀孕的问题！");
			}
			if(Lab.isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
				return new Value<>(false, "你得先和莉莱雅谈谈你的恶魔形态！");
			}
			if(!Main.game.isExtendedWorkTime()) {
				return new Value<>(false, "你只能在莉莱雅醒着的时候扩建桑拿房！");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public Value<Boolean, String> getRemovalAvailability(Cell cell) {
			return new Value<>(false, "这项升级不能被取消！");
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SPA_POOL = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_BLUE_LIGHT,
			"游泳池(拓展)",
			"水疗中心还有相当大的扩建空间……"
				+ "<br/>如果你愿意，可以向莉莱雅建议，室内游泳池将是水疗中心的重要补充。",
			"",
			"",
			300_000,
			50_000,
			100,
			0,
			0.25f,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			return LilayaSpa.SPA_POOL_INSTALLATION;
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return new Value<>(false, "你需要等待水疗中心扩建工程完工后才能扩建它！");
			}
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION)!=null) {
				return new Value<>(false, "你需要等待桑拿房扩建工程完工后再开始这个工程！");
			}
			if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& (Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0) || Main.game.getNpc(Lilaya.class).isPregnant())) {
				return new Value<>(false, "莉莱雅需要先解决怀孕的问题！");
			}
			if(Lab.isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
				return new Value<>(false, "你得先和莉莱雅谈谈你的恶魔形态！");
			}
			if(!Main.game.isExtendedWorkTime()) {
				return new Value<>(false, "你只能在莉莱雅醒着的时候扩建桑拿房！");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public Value<Boolean, String> getRemovalAvailability(Cell cell) {
			return new Value<>(false, "这项升级不能被取消！");
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SPA_BAR = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_ORANGE,
			"酒吧",
			"在水疗中心的泳池区建造一个酒吧，确保备齐各类烈酒和软饮。"
					+ "冷藏和补货成本意味着维持运转可不便宜，不过……",
			"你已经在水疗中心的泳池区建造一个了酒吧，确保备齐各类烈酒和软饮。"
					+ "不过冷藏和补货的成本意味着维持它并不便宜……",
			"",
			15_000,
			5_000,
			500,
			0,
			0.1f,
			-0.2f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return new Value<>(false, "你需要等待水疗中心扩建工程完工后才能升级它！");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};

	//**** DINING HALL UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_DINING_HALL = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_ORANGE_LIGHT,
			"餐厅",
			"莉莱雅和萝丝在实验室里用餐，因此大宅里没有专门的餐厅。"
					+ "一张合适的大桌子和十二把椅子就能解决这个问题……",
			"这个房间被改建成了餐厅，配有一张长木桌和十二把椅子。",
			"这个房间被改建成了餐厅，配有一张长木桌和十二把椅子。"
					+ "虽然莉莱雅和萝丝很可能会继续保持在实验室用餐的习惯，但没有什么能阻止你们利用这个房间。",
			6000,
			250,
			50,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaDiningHallDialogue.ROOM_DINING_HALL;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomDining", PresetColour.BASE_ORANGE_LIGHT);
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
//			if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_DINING_HALL).isEmpty()
//					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_DINING_HALL).isEmpty()) {
//				return new Value<>(false, "There's no need for more than one dining hall.");
//			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "改造开始前，这个房间需要被清空。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_DINING_HALL) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};

	//**** SLAVE LOUNGE UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_LOUNGE = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_GREEN_LIME,
			"奴隶休息室",
			"除了使用各自的房间，你的奴隶有时也会选择去奴隶休息室放松。"
					+ "把这个房间改建成奴隶休息室，可以让你的奴隶们在休息时间来这里休息。",
			"这个房间已被改装成供奴隶们使用的休息室。"
					+ "在不睡觉或工作的时候，只要他们拥有“"+SlavePermissionSetting.GENERAL_HOUSE_FREEDOM.getName()+"”权限，他们可能会选择来这里放松一个小时左右。",
			"这个房间已被改装成供奴隶们使用的休息室。"
					+ "在不睡觉或工作的时候，只要他们拥有“"+SlavePermissionSetting.GENERAL_HOUSE_FREEDOM.getName()+"”权限，他们可能会选择来这里放松一个小时左右。",
			5000,
			250,
			50,
			0,
			0.5f,
			0,
			null) {
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaSlaveLoungeDialogue.ROOM_SLAVE_LOUNGE;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveLounge", PresetColour.BASE_GREEN_LIME);
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
//			if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_DINING_HALL).isEmpty()
//					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_DINING_HALL).isEmpty()) {
//				return new Value<>(false, "There's no need for more than one slave lounge.");
//			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "改造开始前，这个房间需要被清空。");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_SLAVE_LOUNGE) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};
	
	private static ArrayList<AbstractPlaceUpgrade> coreRoomUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> guestRoomUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> dungeonCellUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> slaveQuartersUpgradesSingle;
	private static ArrayList<AbstractPlaceUpgrade> slaveQuartersUpgradesDouble;
	private static ArrayList<AbstractPlaceUpgrade> slaveQuartersUpgradesQuadruple;
	private static ArrayList<AbstractPlaceUpgrade> milkingRoomUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> officeUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> spaUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> diningHallUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> slaveLoungeUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> dressingRoomUpgrades;
	
	
	public static ArrayList<AbstractPlaceUpgrade> getCoreRoomUpgrades() {
		return coreRoomUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getGuestRoomUpgrades() {
		return guestRoomUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getDungeonCellUpgrades() {
		return dungeonCellUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getSlaveQuartersUpgradesSingle() {
		return slaveQuartersUpgradesSingle;
	}
	
	public static ArrayList<AbstractPlaceUpgrade> getSlaveQuartersUpgradesDouble() {
		return slaveQuartersUpgradesDouble;
	}

	public static ArrayList<AbstractPlaceUpgrade> getSlaveQuartersUpgradesQuadruple() {
		return slaveQuartersUpgradesQuadruple;
	}
	
	public static ArrayList<AbstractPlaceUpgrade> getMilkingUpgrades() {
		return milkingRoomUpgrades;
	}
	
	public static ArrayList<AbstractPlaceUpgrade> getOfficeUpgrades() {
		return officeUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getSpaUpgrades() {
		return spaUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getDiningHallUpgrades() {
		return diningHallUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getSlaveLoungeUpgrades() {
		return slaveLoungeUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getDressingRoomUpgrades() {
		return dressingRoomUpgrades;
	}
	
	
	static {
		coreRoomUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_DOLL_CLOSET,
				PlaceUpgrade.LILAYA_GUEST_ROOM,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE,
				
				PlaceUpgrade.LILAYA_SLAVE_LOUNGE,
				
				PlaceUpgrade.LILAYA_SPA,
				
				PlaceUpgrade.LILAYA_OFFICE,
				PlaceUpgrade.LILAYA_DRESSING_ROOM,
				
				PlaceUpgrade.LILAYA_MILKING_ROOM,
				PlaceUpgrade.LILAYA_DINING_HALL,
				
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);

		guestRoomUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_EMPTY_ROOM);

		dungeonCellUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_DUNGEON_CELL_DECENT_FOOD,
				PlaceUpgrade.LILAYA_DUNGEON_CELL_DOG_BOWLS,
				
				PlaceUpgrade.LILAYA_DUNGEON_CELL_UPGRADE_BED,
				PlaceUpgrade.LILAYA_DUNGEON_CELL_DOWNGRADE_BED,

				PlaceUpgrade.LILAYA_DUNGEON_CELL_ROPES,
				PlaceUpgrade.LILAYA_DUNGEON_CELL_CHAINS,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER);
				
		slaveQuartersUpgradesSingle = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ROOM_SERVICE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOG_BOWLS,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER,

				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE,
				PlaceUpgrade.LILAYA_EMPTY_ROOM,
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);
		
		slaveQuartersUpgradesDouble = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ROOM_SERVICE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOG_BOWLS,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER,

				PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE,
				PlaceUpgrade.LILAYA_EMPTY_ROOM,
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);
		
		slaveQuartersUpgradesQuadruple = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ROOM_SERVICE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOG_BOWLS,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER,
				
				PlaceUpgrade.LILAYA_EMPTY_ROOM,
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);
		
		milkingRoomUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS,
				PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS,

				PlaceUpgrade.LILAYA_MILKING_ROOM_MILK_EFFICIENCY,
				PlaceUpgrade.LILAYA_MILKING_ROOM_CUM_EFFICIENCY,
				PlaceUpgrade.LILAYA_MILKING_ROOM_GIRLCUM_EFFICIENCY,
				
				PlaceUpgrade.LILAYA_EMPTY_ROOM);
		
		officeUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_OFFICE_EXECUTIVE_UPGRADE,
				PlaceUpgrade.LILAYA_OFFICE_COFFEE_MACHINE,
				PlaceUpgrade.LILAYA_OFFICE_PARTITIONING_WALLS,
				
				PlaceUpgrade.LILAYA_EMPTY_ROOM);
		
		spaUpgrades = Util.newArrayListOfValues(
				//TODO
//				PlaceUpgrade.LILAYA_SPA_SAUNA,
//				PlaceUpgrade.LILAYA_SPA_POOL,
				PlaceUpgrade.LILAYA_SPA_BAR);
		
		diningHallUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_EMPTY_ROOM);
		
		slaveLoungeUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_EMPTY_ROOM);
		
		dressingRoomUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_DRESSING_ROOM_LYSSIETH_WARDROBE,
				
				PlaceUpgrade.LILAYA_EMPTY_ROOM);
	}
	

	private static List<AbstractPlaceUpgrade> allPlaceUpgrades = new ArrayList<>();
	private static Map<AbstractPlaceUpgrade, String> placeUpgradeToIdMap = new HashMap<>();
	private static Map<String, AbstractPlaceUpgrade> idToPlaceUpgradeMap = new HashMap<>();

	public static List<AbstractPlaceUpgrade> getAllPlaceUpgrades() {
		return allPlaceUpgrades;
	}
	
	public static AbstractPlaceUpgrade getPlaceUpgradeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToPlaceUpgradeMap.keySet());
		return idToPlaceUpgradeMap.get(id);
	}

	public static String getIdFromPlaceUpgrade(AbstractPlaceUpgrade placeType) {
		return placeUpgradeToIdMap.get(placeType);
	}
	
	static {
		Field[] fields = PlaceUpgrade.class.getFields();
		
		for(Field f : fields) {
			if(AbstractPlaceUpgrade.class.isAssignableFrom(f.getType())) {
				AbstractPlaceUpgrade placeType;
				try {
					placeType = ((AbstractPlaceUpgrade) f.get(null));

					placeUpgradeToIdMap.put(placeType, f.getName());
					idToPlaceUpgradeMap.put(f.getName(), placeType);
					allPlaceUpgrades.add(placeType);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
