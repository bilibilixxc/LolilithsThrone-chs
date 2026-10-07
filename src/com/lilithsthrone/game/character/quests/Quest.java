package com.lilithsthrone.game.character.quests;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.fields.Aurokaris;
import com.lilithsthrone.game.character.npc.fields.Lunexis;
import com.lilithsthrone.game.character.npc.fields.Ursa;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.BraxOffice;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4.6.3
 * @author Innoxia
 */
public enum Quest {
	
	
	// Main quests:

	MAIN_PROLOGUE(QuestType.MAIN, 1, 5) {
		@Override
		public String getName() {
			return "熬过今晚";
		}
		@Override
		public String getDescription() {
			return "你答应莉莉姨妈要参加她博物馆新展览的开幕式。你必须熬过今晚的无聊时光。";
		}
		@Override
		public String getCompletedDescription() {
			return "你在博物馆度过的夜晚比你想的要漫长得多。"
					+ "一个名叫莉莉丝的神秘恶魔诱骗你穿过一个神奇的入口，进入一个平行宇宙。"
					+ "你在一条陌生的街道上醒来，之后被半恶魔“莉莱雅”从危难中救了出来。"
					+ "她似乎是这个宇宙中莉莉姨妈的异面同位体。她把你安置在她家里，作为协助她实验的回报。";
		}
	},

	MAIN_1_A_LILAYAS_TESTS(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "莉莱雅的测试";
		}
		@Override
		public String getDescription() {
			return "你可以随时到莉莱雅的实验室找她，她会在那里继续对你进行测试。也许她能找到送你回家的办法？";
		}
		@Override
		public String getCompletedDescription() {
			return "莉莱雅又对你进行了一些测试，但没有老同事亚瑟的帮助，她的研究无法取得进展。";
		}
		@Override
		public void applySkipQuestEffects() {
			((Arthur) Main.game.getNpc(Arthur.class)).generateNewTile();
		}
	},

	MAIN_1_B_DEMON_HOME(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "寻找亚瑟：恶魔之家";
		}
		@Override
		public String getDescription() {
			return "莉莱雅告诉你，她的老同事亚瑟会更了解传送门中使用的魔法类型。"
					+ "不过，莉莱雅看起来对亚瑟很不待见。你的任务就是去找到他，让他给莉莱雅好好道歉，这样她才会容许他一起共事。"
					+ "亚瑟住在本市“恶魔之家”区的“圣尔蒂旅馆”，可以去那里找他。";
		}
		@Override
		public String getCompletedDescription() {
			return "你来到亚瑟家才发现他被抓了。御城区执法者以涉嫌密谋反对莉莉丝的罪名逮捕了他，并移交至执法者总部。";
		}
		@Override
		public void applySkipQuestEffects() {
			// No effects applied
		}
	},

	MAIN_1_C_WOLFS_DEN(QuestType.MAIN, 3, 20) {
		@Override
		public String getName() {
			return "寻找亚瑟：勇闯狼穴";
		}
		@Override
		public String getDescription() {
			return "亚瑟被御城区的执法者逮捕，并被带到了执法者总部。"
					+ "看来你得去那儿进一步询问详情，并想办法救出亚瑟。";
		}
		@Override
		public String getCompletedDescription() {
			return "你被迫面对向督查执法者，布拉克斯。"
					+ "还好你打得过他！但随后便是一个噩耗：亚瑟已被卖为奴隶！";
		}
		@Override
		public void applySkipQuestEffects() {
			BraxOffice.setBraxsPostQuestStatus(false);
			BraxOffice.givePlayerEnforcerUniform(null,-1);
		}
	},

	MAIN_1_D_SLAVERY(QuestType.MAIN, 3, 10) {
		@Override
		public String getName() {
			return "寻找亚瑟：沦为奴隶";
		}
		@Override
		public String getDescription() {
			return "在击败布拉克斯后，你发现了亚瑟的情报，他被当做奴隶卖给了叫斯嘉丽的奴隶贩子。"
					+ "你必须前往奴隶巷，找到斯嘉丽，并想办法解救亚瑟。";
		}
		@Override
		public String getCompletedDescription() {
			return "你在奴隶巷找到了哈比斯嘉丽，她是你见过的最粗鲁的人之一。";
		}
		@Override
		public void applySkipQuestEffects() {
			// No effects applied
		}
	},
	
	MAIN_1_E_REPORT_TO_HELENA(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "寻找亚瑟：报告海伦娜";
		}
		@Override
		public String getDescription() {
			return "在奴隶巷找到斯嘉丽后，你发现亚瑟已经不在她手里了。"
					+ "她要你去哈比之巢向她的族长海伦娜报告，她的生意完全失败了。不然的话她半点口风都不会漏给你。";
		}
		@Override
		public String getCompletedDescription() {
			return "你把斯嘉丽的麻烦报告给了她的族长海伦娜。"
					+ "她似乎对斯嘉丽没有多少同情，很快就飞过去和她当面谈了。";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
			Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
		}
	},
	
	MAIN_1_F_SCARLETTS_FATE(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "寻找亚瑟：斯嘉丽的命运";
		}
		@Override
		public String getDescription() {
			return "你需要回到斯嘉丽的店里，看看她现在怎么样了。希望海伦娜没有对她太苛刻，她现在会愿意告诉你亚瑟发生了什么事……";
		}
		@Override
		public String getCompletedDescription() {
			return "你回到斯嘉丽的商店，却发现海伦娜已经奴役了她！";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
			Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMedianValue());
			Main.game.getNpc(Scarlett.class).resetInventory(true);
			AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false);
			collar.setSealed(true);
			Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(collar, true, Main.game.getNpc(Helena.class));
			Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_PINK, false), true, Main.game.getNpc(Helena.class));
		}
	},
	
	MAIN_1_G_SLAVERY(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "寻找亚瑟：奴隶身份";
		}
		@Override
		public String getDescription() {
			return "海伦娜愿意把斯嘉丽卖给你，这似乎是你获得所需信息的唯一途径。"
					+ "要购买斯嘉丽，你需要持有贩奴许可。";
		}
		@Override
		public String getCompletedDescription() {
			return "海伦娜把斯嘉丽卖给了你。如此一来，你即可命令斯嘉丽告诉你，亚瑟到底发生了什么事。";
		}
		@Override
		public void applySkipQuestEffects() {
			AbstractClothing ballgag = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.MOUTH);
			if (ballgag != null) {
				ballgag.setSealed(false);
				Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(ballgag, true, Main.game.getNpc(Helena.class));
			}
			
			// Complete slavery side quest:
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLAVERY)) {
				Main.game.getPlayer().startQuest(QuestLine.SIDE_SLAVERY);
			}
			List<Quest> slaverSkipQuests = Util.newArrayListOfValues(
					Quest.SIDE_SLAVER_NEED_RECOMMENDATION,
					Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED,
					Quest.SIDE_UTIL_COMPLETE);
			for(int i=0; i<slaverSkipQuests.size()-1; i++) {
				Quest q = slaverSkipQuests.get(i);
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY)==q) {
					q.applySkipQuestEffects();
					Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLAVERY, slaverSkipQuests.get(i+1));
				}
			}
			
			Main.game.getNpc(Scarlett.class).setAffection(Main.game.getNpc(Helena.class), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
			Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.NEGATIVE_FOUR_DEFIANT.getMedianValue());
			Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
			Main.game.getPlayer().addSlave(Main.game.getNpc(Scarlett.class));
			
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
			
			((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
		}
	},
	
	MAIN_1_H_THE_GREAT_ESCAPE(QuestType.MAIN, 10, 200) {
		@Override
		public String getName() {
			return "寻找亚瑟：逃离魔爪";
		}
		@Override
		public String getDescription() {
			return "原来，亚瑟被卖给了一个极其危险的恶魔，名叫扎拉尼克斯，他住在恶魔之家。"
					+ "你需要前往恶魔之家营救亚瑟！";
		}
		@Override
		public String getCompletedDescription() {
			return "打败扎拉尼克斯后，你救出了亚瑟，并把他带回了莉莱雅的家。";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
			Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
		}
	},
	
	MAIN_1_I_ARTHURS_TALE(QuestType.MAIN, 1, 30) {
		@Override
		public String getName() {
			return "寻找亚瑟：来龙去脉";
		}

		@Override
		public String getDescription() {
			return "既然你已经从扎拉尼克斯的魔掌中救出了亚瑟，就应该回到莉莱雅的家，从他那里了解事情的前因后果。";
		}

		@Override
		public String getCompletedDescription() {
			return "亚瑟解释了他是如何涉足传送法术这门禁术的。"
					+ "扎拉尼克斯通过他的一名特工发现了这件事，并毫不费力地以叛国罪将亚瑟奴役。"
					+ "既然你救了他，他也很想报答你，找出送你回家的方法。";
		}
		@Override
		public void applySkipQuestEffects() {
			Cell arthurRoomCell = Lab.addArthurRoom();
			Main.game.getNpc(Arthur.class).setLocation(arthurRoomCell, true);
		}
	},
	
	// This quest is no longer used, but is left here for old version support
	MAIN_1_J_ARTHURS_ROOM(QuestType.MAIN, 1, 30) {
		@Override
		public String getName() {
			return "寻找亚瑟：他的房间";
		}
		@Override
		public String getDescription() {
			return "莉莱雅真的不想让亚瑟待在她的实验室里，她委托你帮萝丝找个合适的房间安排亚瑟住下。<br/>"
					+ "<i>进入莉莱雅的家的一个空房间，通过房间管理窗口将其升级为 “亚瑟的卧室”。</i>";
		}
		@Override
		public String getCompletedDescription() {
			return "你为亚瑟找到了一个合适的房间，并在萝丝的帮助下，把大量的奥术仪器搬进了他的新实验室兼卧室。";
		}
	},
	
	
	MAIN_2_A_INTO_THE_DEPTHS(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "进入屈城区";
		}
		@Override
		public String getDescription() {
			return "亚瑟能够解释你被传送到这个新世界的机制，但他似乎对一些细节有所保留。"
					+ "他说，一旦他确定发生了什么事，他就会把一切都解释清楚，但要做到这一点，他需要和七位莉琳长老中的一位谈谈。"
					+ "经过一番争论，莉莱雅同意说服她的母亲帮忙，但必须由你来传话。<br/>"
					+ "<i>前往屈城区水下都市，求见莉莱雅的母亲，莉西丝。</i>";
		}
		@Override
		public String getCompletedDescription() {
			return "根据亚瑟的建议，你冒险进入了屈城区，发现了莉西丝宫殿的位置。";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING), false);
		}
	},
	
	MAIN_2_B_SIRENS_CALL(QuestType.MAIN, 25, 300) {
		@Override
		public String getName() {
			return "塞壬的呼唤";
		}
		@Override
		public String getDescription() {
			return "莉西丝宫殿门口的守卫告诉你，她现在不接待任何访客。"
					+ "要得到她的接见，唯一的办法就是解决她的麻烦女儿“暗夜塞壬”。"
					+ "目前，她住在屈城区中心隧道的一个石头要塞里，并从那里派出小恶魔团伙恐吓无辜市民。</br>"
					+ "无论是堂堂正正地决斗，抑或是插圈弄套地算计，只要你能奴役她，便能得到莉西丝的接见。";
		}
		@Override
		public String getCompletedDescription() {
			return "你设法奴役了莉西丝的麻烦女儿，因此赢得觐见莉西丝的机会！";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
			ImpCitadelDialogue.clearFortress(false);
			// Set tunnels to be cleared manually, as they haven't been cleared when skipping quests:
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaDefeated, true);
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
					character.returnToHome();
				}
			}
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesDefeated, true);
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
					character.returnToHome();
				}
			}
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesDefeated, true);
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)) {
					character.returnToHome();
				}
			}
		}
	},
	
	MAIN_2_C_SIRENS_FALL(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "塞壬陷落";
		}

		@Override
		public String getDescription() {
			return "返回莉西丝的宫殿告知守卫，你已奴役了“暗夜塞壬”。"
					+ "这应该足以让你觐见莉西丝了。";
		}

		@Override
		public String getCompletedDescription() {
			return "因为奴役了“暗夜塞壬”，莉西丝宫殿门口的守卫允许你进入宫殿觐见她。";
		}
		@Override
		public void applySkipQuestEffects() {
			if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
			}
			if(!Main.game.getPlayer().hasClothingType(ClothingType.FINGER_LYSSIETHS_RING, true)) {
				Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(ClothingType.FINGER_LYSSIETHS_RING, false), false);
			}
		}
	},
	
	MAIN_2_D_MEETING_A_LILIN(QuestType.MAIN, 1, 100) {
		@Override
		public String getName() {
			return "面见莉琳";
		}

		@Override
		public String getDescription() {
			return "前往莉西丝的宫殿，追寻最终答案：你为什么会在这里，如何才能回到原来的世界。";
		}

		@Override
		public String getCompletedDescription() {
			return "莉西丝透露，这个世界其实就是原来的世界，莉莉丝从镜子中被释放出来后，将现实的历史改写了";
		}
		@Override
		public void applySkipQuestEffects() {
			((DarkSiren)Main.game.getNpc(DarkSiren.class)).postDefeatReset();
			AbstractItemEffectType.getBookEffect(Main.game.getPlayer(), Subspecies.LILIN, null, false);
			Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), 25);
			Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 10);
			Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), 10);
			Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), 10);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionLiberate, true);
			if(Main.game.getNpc(DarkSiren.class).getAffection(Main.game.getPlayer())<0) {
				Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getPlayer(), 0);
			}
		}
	},
	
	MAIN_3_ELIS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "目的地：伊利斯";
		}

		@Override
		public String getDescription() {
			return "莉西丝告诉你，想打败莉琳长老——半天马人露内特，你需要得到米诺塔莉丝的帮助，她是统治伊利斯镇的莉琳。"
					+ "梅拉克西丝建议向妖狐寻求帮助，不过也得等你到了伊利斯再做打算。";
		}
		@Override
		public String getCompletedDescription() {
			return "莉西丝告诉你，想打败莉琳长老——半天马人露内特，你需要得到米诺塔莉丝的帮助，她是统治伊利斯镇的莉琳。"
					+ "为此，你第一次离开了御城区……";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_B_MEETING_MERAXIS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "到红龙酒馆";
		}

		@Override
		public String getDescription() {
			return "当你离开御城区时，梅拉克西丝走了过来。她让你到伊利斯的红龙酒馆来找她，那里离小镇的东大门不远。"
					+ "梅拉克西丝还说，等你来了，她会安排你与米诺塔莉丝见面，也会给你安排好住处。";
		}

		@Override
		public String getCompletedDescription() {
			return "你在红龙酒馆见到了梅拉克西丝，她在旅店的一楼租了一间屋子，作为你的住所。";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_C_MEETING_MINOTALLYS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "与米诺塔莉丝会面";
		}

		@Override
		public String getDescription() {
			return "梅拉克西丝安排你与米诺塔莉丝会面，共商威胁——伊利斯城的露内特。"
					+ "在[units.time(9)]-[units.time(18)]间的任意时刻，告知梅拉克西丝你已经准备好参加会面了。";
		}

		@Override
		public String getCompletedDescription() {
			return "你与梅拉克西丝前往了伊利斯的市镇议会，前去见米诺塔莉丝。而在那，你同时见到了她的个人助理，阿里昂。"
					+ "米诺塔莉丝对弗洛伊田野的现状矢口否认，并表示只有当特弥斯库拉受到某种威胁时，她才会考虑采取行动。";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_D_TO_THEMISCYRA(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "前往特弥斯库拉";
		}

		@Override
		public String getDescription() {
			return "你同意与梅拉克西丝一同前往特弥斯库拉，去确认此地是否受露内特的军队威胁。"
					+ "当你准备好之后，你需要与梅拉克西丝见面，让她带你前往那里。";
		}

		@Override
		public String getCompletedDescription() {
			return "你与梅拉克西丝一同前往特弥斯库拉，但在抵达前，你便发现那里已遭露内特的军队摧毁了！";
		}
		@Override
		public void applySkipQuestEffects() {
			if(Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra"))==null) {
				Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(11, 32).getPlace().setPlaceType(PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra"));
			}
		}
	},
	
	MAIN_3_E_THEMISCYRA_ATTACK(QuestType.MAIN, 1, 250) {
		@Override
		public String getName() {
			return "保护女王";
		}
		@Override
		public String getDescription() {
			return "在与梅拉克西丝分别后，你跟一名名为奥罗卡利斯的亚马逊牛女一同合作。"
					+ "你需要穿越特弥斯库拉，找到梅拉克西丝，以及乌萨——亚马逊人的女王，她应当在宫殿内。";
		}
		@Override
		public String getCompletedDescription() {
			return "与奥罗卡利斯结伴而行，你穿越了特弥斯库拉，找到了梅拉克西丝，且在宫殿前的广场上找到了乌萨。"
					+ "在遇到露内特军队的首领露内克西丝后，梅拉克西丝将你们五人传送回了伊利斯的市镇议会，而米诺塔莉丝终于相信露内特会对伊利斯造成威胁。";
		}
		@Override
		public void applySkipQuestEffects() {
			if(Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_elis_town")).getCell(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"))==null) {
				Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_elis_town")).getCell(10, 20).getPlace().setPlaceType(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"));
			}
			Main.game.getNpc(Ursa.class).setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"), true);
			Main.game.getNpc(Aurokaris.class).setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"), true);
			Main.game.getNpc(Lunexis.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
		}
	},
	
	MAIN_3_F_PREPARING_ELIS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "SWORD的援助";
		}
		@Override
		public String getDescription() {
			return "由于露内特准备在不久的将来攻打伊利斯，你告诉米诺塔莉丝你会帮助组织城镇的防御。"
					+ "你需要前往伊利斯的执法者站，向SWORD执法者小组寻求帮助。";
		}
		@Override
		public String getCompletedDescription() {
			return "你前往伊利斯的执法局，请求SWORD执法者小组帮助整顿城镇的防御。";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_G_SWORD_SCAPEGOAT(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "SWORD的代罪羔羊";
		}
		@Override
		public String getDescription() {
			return "SWORD执法者告诉你，为了让他们帮助你，你首先要帮助他们。"
					+ "你要跟去执行一项行动，阻止一位莉琳长老的女儿，这样如果他们被认出来，你就能帮他们摆脱困境。"
					+ "你需要在周二晚上[units.time(17)]之后与他们在执法局会面，以开始行动。";
		}
		@Override
		public String getCompletedDescription() {
			return "你在周二晚上与SWORD执法者会面，跟随他们一起行动，阻止一位莉琳长老的女儿。";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_H_SWORD_MISSION(QuestType.MAIN, 25, 250) {
		@Override
		public String getName() {
			return "阻止魅魔";
		}
		@Override
		public String getDescription() {
			return "在SWORD执法者清理魅魔总部的时候，你需要和他们待在一起。"
					+ "你不需要战斗，但你若给执法者们一些支援的话，可能会赢得他们的感谢。";
		}
		@Override
		public String getCompletedDescription() {
			return "你与SWORD执法者协力合作，清理着魅魔的总部。"
					+ "虽然她逃脱了，但你也彻底中止了以她为首的非法奴役活动。";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_I_ELIS_DEFENCE_PLAN(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "报告米诺塔莉丝";
		}
		@Override
		public String getDescription() {
			return "既然SWORD执法者们已经在准备城镇的防御工事了，你需要回到市政厅向米诺塔莉丝汇报此事。";
		}
		@Override
		public String getCompletedDescription() {
			return "在市政厅与米诺塔莉丝、乌萨、锤子和幽灵以及梅拉克西丝会面后，一个防御伊利斯免受露内特侵袭的计划制定了出来。";
		}
	},
	
	MAIN_3_J_FINDING_TAKAHASHI(QuestType.MAIN, 30, 25) {
		@Override
		public String getName() {
			return "寻找高桥";
		}
		@Override
		public String getDescription() {
			return "梅拉克西丝相信，在她的妖狐朋友高桥的帮助下，她能争取到妖狐们的援助。"
					+ " 最有可能找到高桥的地方是梅拉克西丝在心林高地的旧藏身处。"
					+ " 准备好之后，你需要与梅拉克西丝交谈，前往心林高地寻找她的朋友。";
		}
		@Override
		public String getCompletedDescription() {
			return "你发现梅拉克西丝的秘密藏身处已经被一只七尾妖狐占据，她抓住了高桥并将其当作仆人使唤。"
					+ " 在被发现之后，那只七尾妖狐逃走了，梅拉克西丝和高桥得以重新夺回她们的旧藏身处。";
		}
	},
	
	MAIN_3_K_WEAPONS_CACHE(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "夺回武器";
		}
		@Override
		public String getDescription() {
			return "[style.italicsBad(主线任务暂时到此为止！很快就会更新！)]"
					+"<br/>"
					+"梅拉克西丝从莉西丝那里偷走的枪械现在落在了一只九尾妖狐手中。"
					+ " 高桥知道这只妖狐住在哪里，所以等你准备好之后，你们三人就可以出发去夺回这些武器了。";
		}
		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	
	MAIN_3_L_YOUKO_LILIN(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "妖狐莉琳";
		}
		@Override
		public String getDescription() {
			return "武器已经到手，伊利斯的防御现在只差能够抵御露内特女儿们所使用法术的奥术使用者了。"
					+ " 为了让妖狐帮忙，你需要找到两位妖狐莉琳，说服她们助你一臂之力。";
		}
		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	
	MAIN_3_M_DEFENCE_OF_ELIS(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "";
		}
		@Override
		public String getDescription() {
			return "妖狐的援助已经确保，奥术滑膛枪的储备也已到手，剩下要做的就是引发露内特对城镇的进攻了。";
		}
		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	
	MAIN_3_N_DEFEAT_LUNETTE(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "";
		}
		@Override
		public String getDescription() {
			return "对伊利斯的进攻已经开始了！多亏了你、妖狐以及装备了滑膛枪的民兵的努力，露内特的女儿们已经被击退，但长老莉琳本人仍需解决。"
					+ " 现在就看你、梅拉克西丝和米诺塔莉丝能否击败她的半人马形态了.";
		}
		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	
	MAIN_3_O_DEFEAT_LUNETTE2(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "";
		}
		@Override
		public String getDescription() {
			return "你成功击败了露内特，但就在她的半人马身躯轰然倒地之际，她转化成了真正的恶魔形态。"
					+ " 你需要拖住她，直到莉西丝赶来制服她的姐妹.";
		}
		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	
	//"[style.italicsBad(This is as far as the main quest goes for now! It will be updated soon!)]"
	//+"<br/>"

	// Side Quests:

	SIDE_UTIL_COMPLETE(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "任务完成！";
		}

		@Override
		public String getDescription() {
			return "任务完成！";
		}

		@Override
		public String getCompletedDescription() {
			return "任务完成！";
		}
	},
	
	SIDE_DISCOVER_ALL_ITEMS(QuestType.SIDE, 1, 100) {
		@Override
		public String getName() {
			return "圆满主义者";
		}

		@Override
		public String getDescription() {
			return "在这个新世界里，有许多奇特的物品。你在想自己能否把它们都找出来……";
		}

		@Override
		public String getCompletedDescription() {
			return "你已经发现了所有可以找到的物品！";
		}
	},

	SIDE_DISCOVER_ALL_RACES(QuestType.SIDE, 1, 100) {
		@Override
		public String getName() {
			return "圆满主义者";
		}

		@Override
		public String getDescription() {
			return "这个世界上似乎有很多奇怪的新种族。你想知道自己是否能发现全部的种族……";
		}

		@Override
		public String getCompletedDescription() {
			return "你们发现了所有可以找到的种族！";
		}
	},
	
	
	// For when you discover your first essence:
	
	SIDE_ENCHANTMENTS_LILAYA_HELP(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "向莉莱雅寻求帮助";
		}

		@Override
		public String getDescription() {
			return "你最近感觉有一股奇怪的力量进入了你的身体，虽然它似乎没有产生任何明显的影响，但你还是应该去检查一下。"
					+ "莉莱雅肯定会知道更多，也许你应该去和她谈谈。";
		}

		@Override
		public String getCompletedDescription() {
			return "莉莱雅告诉你，你可以从其他人的奥术灵气当中收集“精华”。"
					+ "她似乎有点担心你能做到这一点，因为通常只有莉琳才能用这种方式收集精华……";
		}
	},

	// For the first time you get pregnant:
	
	SIDE_PREGNANCY_CONSULT_LILAYA(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "莉莱雅最清楚";
		}

		@Override
		public String getDescription() {
			return "这不可能……你怀孕了？你<b>怀孕</b>了！莉莱雅肯定知道该怎么做？！";
		}

		@Override
		public String getCompletedDescription() {
			return "莉莱雅设法让你平静下来，并安慰你说，在这个世界上，怀孕并不像在老家那样是件大事。";
		}
	},
	
	SIDE_PREGNANCY_LILAYA_THE_MIDWIFE(QuestType.SIDE, 1, 20) {
		@Override
		public String getName() {
			return "助产士莉莱雅";
		}

		@Override
		public String getDescription() {
			return "莉莱雅说，只要你准备好了，她就能帮你生产。你需要等到肚子不再变大后，再去找莉莱雅生产。";
		}

		@Override
		public String getCompletedDescription() {
			return "莉莱雅帮你生了孩子。她说，如果你再次怀孕，她可以随时帮忙。";
		}
	},
	
	// When getting eggs implanted in you for the first time:
	
	SIDE_INCUBATION_WAITING(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "活体孵卵器";
		}
		@Override
		public String getDescription() {
			return "毫无疑问，你的体内已经植入了一窝卵！除了等待它们成熟，然后产下它们之外，你别无他法……";
		}
		@Override
		public String getCompletedDescription() {
			return "你等待植入体内的卵子成熟，然后成功产卵并孵化了这些卵！";
		}
	},
	
//	SIDE_INCUBATION_LILAYA_HELP(QuestType.SIDE, 1, 20) {
//		@Override
//		public String getName() {
//			return "Egg-laying assistance";
//		}
//		@Override
//		public String getDescription() {
//			return "Lilaya said that she'd be able to help you lay your eggs whenever you're ready. You're going to need to wait until they're ready to be hatched, then you can go and see Lilaya to lay them.";
//		}
//		@Override
//		public String getCompletedDescription() {
//			return "Lilaya helped you to lay your eggs. She said that if ever you get implanted with eggs again, she can always help out.";
//		}
//	},
	
	// Getting a slaver license:
	
	SIDE_SLAVER_NEED_RECOMMENDATION(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "推荐信";
		}

		@Override
		public String getDescription() {
			return "在奴隶管理大楼询问如何获得奴隶执照后，你发现首先需要一封推荐信。莉莱雅应该能帮上忙。";
		}

		@Override
		public String getCompletedDescription() {
			return "莉莱雅给了你一封推荐信，更重要的是，她还提出让你在她的宅邸里安置你的奴隶。";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.finchIntroduced);
		}
	},
	
	SIDE_SLAVER_RECOMMENDATION_OBTAINED(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "递交信件";
		}

		@Override
		public String getDescription() {
			return "现在你已经从莉莱雅那里拿到了推荐信，你应该回到奴隶巷的奴隶管理大楼，把它交给[finch.name]。";
		}

		@Override
		public String getCompletedDescription() {
			return "你向[finch.name]提交了推荐信，并在支付费用后获得了奴隶许可证！";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.SLAVER_LICENSE), false));
		}
	},
	
	// Accommodation:
	
	SIDE_ACCOMMODATION_NEED_LILAYAS_PERMISSION(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "女房东莉莱雅";
		}

		@Override
		public String getDescription() {
			return "莉莱雅的豪宅里有很多空房间，可以用作客人的住所。你应该问问她，你是否可以用这些房间来安置你的亲朋好友。";
		}

		@Override
		public String getCompletedDescription() {
			return "莉莱雅允许你使用空房间安置你的朋友和家人，条件是你必须支付由此产生的费用。";
		}
	},

	// Doll storage:
	
	SIDE_DOLL_STORAGE_ASK_FOR_SPACE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "玩偶去往何方？";
		}
		@Override
		public String getDescription() {
			return "虽然莉莱雅的豪宅里有不少可以存放玩偶的空房间，但在你把玩偶带回家之前，最好还是先征得她的同意……";
		}
		@Override
		public String getCompletedDescription() {
			return "莉莱雅允许你使用空闲房间来存放你购买的所有玩偶。";
		}
	},
	
	// Other:
	
	SIDE_HYPNO_WATCH_VICKY(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "奥术艺术的订单";
		}

		@Override
		public String getDescription() {
			return "亚瑟告诉你，扎拉尼克斯指示他寻找一种改变人性取向的奥术方法。"
					+ "虽然他向你保证，他本人无意使用这种物品，但亚瑟确实表示有兴趣完成他的研究，"
						+ "并让你去购物中心里的“奥术艺术”店取一份特别订单。";
		}

		@Override
		public String getCompletedDescription() {
			return "你从“奥术艺术”那里取回了包裹，并把它带回给亚瑟。";
		}
	},
	
	SIDE_HYPNO_WATCH_TEST_SUBJECT(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "测试用具";
		}

		@Override
		public String getDescription() {
			return "莉莱雅按照亚瑟的指示给怀表附魔后，她问是否可以在你身上测试一下……";
//					+ " You could either offer yourself, or, if you own any slaves, offer one of those to Arthur instead.";
		}

		@Override
		public String getCompletedDescription() {
			return "催眠怀表似乎起了作用，不过莉莱雅在它产生永久性效果之前就停止了测试。"
					+ "她警告说，催眠怀表会对目标的思想施加强烈的堕落效果，在将怀表交给你之前，她顺便解除了上面的附魔。";
		}
	},
	
	
	LIGHTNING_SPELL_1_PAYMENT(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "力量的代价";
		}
		@Override
		public String getDescription() {
			return "亚瑟向你展示了在执法者仓库找到的奥术闪电球，并告诉你可以学习其中的闪电法术秘密。"
					+ "尽管由此得到的法术将比闪电球的威力更强大，"
						+ "亚瑟向你解释，这样的汲取不止需要极大量的奥术精华，同时也会永远移除球体固有的法术。"
					+ "<br/>当你可以并愿意这样做时，给亚瑟一个奥术闪电球并让他从你的灵气内汲取500奥术精华。";
		}
		@Override
		public String getCompletedDescription() {
			return "你给了亚瑟从执法者仓库找来的奥术闪电球，同时让他从你的灵气中汲取了500奥术精华。"
					+ "作为交换，你被许诺了一个强大的奥术闪电法术";
		}
	},
	
	LIGHTNING_SPELL_2_WAITING(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "闪电提取";
		}
		@Override
		public String getDescription() {
			return "亚瑟告诉你，从闪电球内获取奥术闪电的秘密需要一些时间。"
					+ "你需要在两周后回来找他，看看他的研究得到了什么成果……";
		}
		@Override
		public String getCompletedDescription() {
			return "亚瑟兴奋地告诉你，他不仅从闪电球上提取出两种奥术闪电的秘密，"
					+ "而且还能将剩余力量转移到一个较小的水晶中，并将其镶在一个戒指上给了你。";
		}
	},
	
	
	
	// Angry Harpies:
	
	HARPY_PACIFICATION_ONE(QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "混乱中的巢穴";
		}

		@Override
		public String getDescription() {
			return "执法者告诉你，哈比之巢目前非常危险。"
					+ "经过进一步的询问，你发现能让三位族长平静下来的人可以获得丰厚的奖励。";
		}

		@Override
		public String getCompletedDescription() {
			return "你已经控制了其中一个哈比巢穴！";
		}
	},
	HARPY_PACIFICATION_TWO(QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "解决一个，还剩两个";
		}

		@Override
		public String getDescription() {
			return "你已经成功的“安抚”了一位哈比族长，但还有两位等着你去“安抚”呢！";
		}

		@Override
		public String getCompletedDescription() {
			return "你已经控制了两个哈比巢穴！";
		}
	},
	HARPY_PACIFICATION_THREE(QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "还剩一位族长";
		}

		@Override
		public String getDescription() {
			return "你已经成功的“安抚”了两位位哈比族长，还剩最后一位！";
		}

		@Override
		public String getCompletedDescription() {
			return "你已经控制了全部主要哈比巢穴！";
		}
	},
	HARPY_PACIFICATION_REWARD(QuestType.SIDE, 6, 50) {
		@Override
		public String getName() {
			return "哈比"+(Main.game.getPlayer().isFeminine()?"女王":"之王");
		}

		@Override
		public String getDescription() {
			return "回到执法者岗哨报告任务已经完成。";
		}

		@Override
		public String getCompletedDescription() {
			return "在告知执法者你已经控制了所有主要哈比巢穴后，执法者恢复了常规巡逻。你现在可以在哈比之巢通行无阻了！";
		}
	},
	
	
	
	// Slime Queen:
	
	SLIME_QUEEN_ONE(QuestType.SIDE, 10, 25) {
		@Override
		public String getName() {
			return "麻烦的史莱姆们";
		}

		@Override
		public String getDescription() {
			return "你刚来到屈城区时，便遇到一位名叫克莱尔的执法者。她告诉你隧道里正在发生的情况。"
					+ "史莱姆正在袭击无辜的旅行者，并把他们变成也变成史莱姆。于是史莱姆就越来越多。"
					+ "如果你向执法者提供凶猛史莱姆来源的线索，就能获得五千火币奖励。"
					+ "<br/>"
					+ "<p style='text-align:center;'><i>你需要通过在<b>屈城区的隧道</b>中打倒一只史莱姆，以获取更多线索。</i></p>";
		}

		@Override
		public String getCompletedDescription() {
			return "隧道里遇到的一只史莱姆告诉你，有位“史莱姆女王”命令它们转化其他人。";
		}
	},
	
	SLIME_QUEEN_TWO(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "汇报";
		}

		@Override
		public String getDescription() {
			return "你应该向屈城区的执法者汇报关于这个“史莱姆女王”的事情。";
		}

		@Override
		public String getCompletedDescription() {
			return "听完报告的执法者告诉你，他们以前听说过关于史莱姆女王的传言，但从未在屈城区中发现过踪迹。"
					+ "他们建议你去蝙蝠洞窟看看，如果你能找到女王并结束她的阴谋，他们还会再给你两万火币。";
		}
	},
	
	SLIME_QUEEN_THREE(QuestType.SIDE, 15, 25) {
		@Override
		public String getName() {
			return "寻找史莱姆女王";
		}

		@Override
		public String getDescription() {
			return "前往蝙蝠洞窟，寻找传说中的史莱姆女王。";
		}

		@Override
		public String getCompletedDescription() {
			return "在史莱姆湖的中央，你发现了史莱姆女王的巢穴！";
		}
	},
	
	SLIME_QUEEN_FOUR(QuestType.SIDE, 20, 50) {
		@Override
		public String getName() {
			return "面对女王";
		}

		@Override
		public String getDescription() {
			return "前往塔顶寻找史莱姆女王。";
		}

		@Override
		public String getCompletedDescription() {
			return "你在塔顶找到了史莱姆女王。";
		}
	},
	
	SLIME_QUEEN_FIVE_SUBMIT(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "帮助女王";
		}

		@Override
		public String getDescription() {
			return "你决定协助史莱姆女王把屈城区的人口都转化成史莱姆的计划。";
		}

		@Override
		public String getCompletedDescription() {
			return "你决定帮助史莱姆女王实现她的计划，并且答应欺骗执法者，好让他们相信她不再是一个威胁。";
		}
	},
	
	SLIME_QUEEN_SIX_SUBMIT(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "最终报告";
		}

		@Override
		public String getDescription() {
			return "跟克莱尔回报史莱姆女王不再是麻烦了。";
		}

		@Override
		public String getCompletedDescription() {
			return "你告诉克莱尔史莱姆女王不再是威胁，并且收到了两千火币作为报酬。"
				+ "现在随着你的女王免于执法者的调查，屈城区变成史莱姆的天堂只剩下时间问题了！";
		}
	},
	
	SLIME_QUEEN_FIVE_CONVINCE(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "说服女王";
		}

		@Override
		public String getDescription() {
			return "你决定要说服史莱姆女王放弃她的计划。";
		}

		@Override
		public String getCompletedDescription() {
			return "你说服了凯瑟琳放弃了她把屈城区的所有人都转化成史莱姆的计划。";
		}
	},
	
	SLIME_QUEEN_SIX_CONVINCE(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "最终报告";
		}

		@Override
		public String getDescription() {
			return "跟克莱尔回报史莱姆女王不再是麻烦了。";
		}

		@Override
		public String getCompletedDescription() {
			return "你告诉克莱尔史莱姆女王不再是一个威胁，并且收到了两千火币作为报酬。";
		}
	},
	
	SLIME_QUEEN_FIVE_FORCE(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "胁迫女王";
		}

		@Override
		public String getDescription() {
			return "胁迫史莱姆女王放弃她的计划。";
		}

		@Override
		public String getCompletedDescription() {
			return "你迫使凯瑟琳放弃了她把屈城区的所有人都转化成史莱姆的计划。";
		}
	},
	
	SLIME_QUEEN_SIX_FORCE(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "最终报告";
		}

		@Override
		public String getDescription() {
			return "跟克莱尔回报史莱姆女王不再是麻烦了。";
		}

		@Override
		public String getCompletedDescription() {
			return "你告诉克莱尔史莱姆女王不再是一个威胁，并且收到了两千火币作为报酬。";
		}
	},
	
	
	// Teleporting:
	
	TELEPORTING_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "从仓库中逃脱";
		}

		@Override
		public String getDescription() {
			return "意外传送到了‘SWORD’执法者部门的存储仓库，现在你和克莱尔需要避免被发现，并且安全离开。";
		}

		@Override
		public String getCompletedDescription() {
			return "你和克莱尔打算设法从SWORD的仓库中离开。";
		}
	},

	TELEPORTING_CAUGHT(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "忍受拘禁";
		}

		@Override
		public String getDescription() {
			if(Main.game.isNonConEnabled()) {
				return "你在SWORD的仓库中被执法者击败了，现在你被判处锁在奴隶巷的公共刑具中的惩罚。忍受这一磨难直到克莱尔前来营救你……";
			} else {
				return "你在SWORD的仓库中被执法者击败了，现在你被判处在执法者总部的监狱中监禁的惩罚。忍受这一煎熬直到克莱尔前来营救你……";
			}
		}

		@Override
		public String getCompletedDescription() {
			if(Main.game.isNonConEnabled()) {
				return "你在SWORD的仓库中被执法者击败了，现在你被判处锁在奴隶巷的公共刑具中数个小时，直到克莱尔现身来释放你。";
			} else {
				return "你在SWORD的仓库中被执法者击败了，现在你被判处在执法者总部的监狱几个小时的监禁，直到克莱尔前来释放你。";
			}
		}
	},
	
	
	// Daddy:
	
	DADDY_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "与[daddy.name]见面";
		}

		@Override
		public String getDescription() {
			return "萝丝要求你去见[daddy.name]，说服[daddy.herHim]别再来打扰莉莱雅。"
					+ "([daddy.He]只在[daddy.his]恶魔之家的公寓中的"+Units.time(LocalTime.of(18, 00))+"和"+Units.time(LocalTime.of(21, 00))+"有空。)";
		}

		@Override
		public String getCompletedDescription() {
			return "遵照萝丝的吩咐，你和[daddy.name]在[daddy.her]恶魔之家的公寓中见了面。";
		}
	},
	
	DADDY_MEETING(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "与[daddy.name]共进晚餐";
		}

		@Override
		public String getDescription() {
			return "[daddy.Name]坚持要在晚餐时解释[daddy.her]的动机。你要么接受[daddy.her]的提议，要么直截了当地拒绝[daddy.herHim]并要求[daddy.she]离莉莱雅远点。";
		}

		@Override
		public String getCompletedDescription() {
			return "你给了[daddy.name]关于共进晚餐请求的回答。";
		}
	},
	
	DADDY_REFUSED(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "[daddy.Name]被拒绝";
		}

		@Override
		public String getDescription() {
			return "你告诉[daddy.name]，你对于跟[daddy.herHim]一起出去吃饭没有丝毫兴趣，并且[daddy.sheIs]永远都不要再来打扰莉莱雅了。";
		}

		@Override
		public String getCompletedDescription() {
			return "你告诉[daddy.name]，你对于跟[daddy.herHim]一起出去吃饭没有丝毫兴趣，并且[daddy.sheIs]永远都不要再来打扰莉莱雅了。";
		}
	},
	
	DADDY_REFUSED_2(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "[daddy.Name]被拒绝";
		}

		@Override
		public String getDescription() {
			return "你告诉[daddy.name]，你对说服莉莱雅和[daddy.herHim]见面没有丝毫兴趣，并且[daddy.she]永远都不要再来打扰你的[lilaya.relation(pc)]了。";
		}

		@Override
		public String getCompletedDescription() {
			return "你告诉[daddy.name]，你对说服莉莱雅和[daddy.herHim]见面没有丝毫兴趣，并且[daddy.she]永远都不要再来打扰你的[lilaya.relation(pc)]了。";
		}
	},
	
	DADDY_ACCEPTED(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "说服莉莱雅";
		}

		@Override
		public String getDescription() {
			return "你同意说服莉莱雅来和[daddy.name]共进晚餐，然后劝说她请莉西丝来和[daddy.herHim]见面。";
		}

		@Override
		public String getCompletedDescription() {
			return "你说服了莉莱雅和[daddy.name]共进晚餐，条件是你和她一起去。";
		}
	},
	
	DADDY_LILAYA_MEETING(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "莉莱雅与[daddy.name]的约会";
		}

		@Override
		public String getDescription() {
			return "莉莱雅同意和[daddy.name]共进晚餐，现在你需要陪着她，确保晚上能顺利进行。";
		}

		@Override
		public String getCompletedDescription() {
			return "你和莉莱雅一起与[daddy.name]共进晚餐，她给[daddy.herHim]带去了关于莉西丝爱情偏好的坏消息，不过她似乎很喜欢[daddy.herHim]……";
		}
	},
	
	
	// Buying Brax:
	
	BUYING_BRAX_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "收集香水";
		}

		@Override
		public String getDescription() {
			return "坎迪说她会考虑把[brax.name]卖给你，但在给出明确答案之前，她想让你去购物中心“魅魔的秘密”商店那里取她订购的香水。";
		}

		@Override
		public String getCompletedDescription() {
			return "你从魅魔的秘密那里购买并取得了坎迪订购的香水。";
		}
	},
	
	BUYING_BRAX_DELIVER_PERFUME(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "运送香水";
		}

		@Override
		public String getDescription() {
			return "你已经获得了瓶装香水，现在你需要把它们送到执法者总部的坎迪那里。";
		}

		@Override
		public String getCompletedDescription() {
			return "你把坎迪的瓶装香水交到了她手上。";
		}
	},
	
	BUYING_BRAX_LOLLIPOPS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "违禁棒棒糖";
		}

		@Override
		public String getDescription() {
			return "坎迪说她愿意把布拉克斯卖给你，但她需要思考一下[brax.sheIs]值多少钱。"
					+ "她让你从哈比之巢的执法者检查站拿来一盒违禁棒棒糖，之后她会给你报价。";
		}

		@Override
		public String getCompletedDescription() {
			return "你从哈比之巢的执法者检查站取回了棒棒糖。";
		}
	},
	
	BUYING_BRAX_DELIVER_LOLLIPOPS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "坎迪的棒棒糖";
		}

		@Override
		public String getDescription() {
			return "你拿到了那盒违禁棒棒糖，现在你需要把它们交给执法者总部的坎迪。";
		}

		@Override
		public String getCompletedDescription() {
			return "你把那盒违禁棒棒糖交给了坎迪，她似乎根本不在意印在上面的警告。";
		}
	},
	
	BUYING_BRAX_LIPSTICK(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "价值一狼的口红";
		}

		@Override
		public String getDescription() {
			return "坎迪说布拉克斯太珍贵了，不能仅仅为了些许火币而出售，但她愿意用布拉克斯换取同样珍贵的东西：一盒叫做“百万之吻”的限量版口红。"
					+ "显然，坎迪发现了仅存的一盒待售口红的下落，在购物中心一家名为“拉尔夫小吃店”的商店里。";
		}

		@Override
		public String getCompletedDescription() {
			return "你从拉尔夫那里取回了那盒“百万之吻”";
		}
	},
	
	BUYING_BRAX_DELIVER_LIPSTICK(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "在狼的门前";
		}

		@Override
		public String getDescription() {
			return "你拿到了那盒“百万之吻”，剩下的就是把它交给坎迪，以换取[brax.name]的所有权。";
		}

		@Override
		public String getCompletedDescription() {
			return "你把那盒“百万之吻”交给了坎迪，最终获得了奖品：[brax.name]的所有权。";
		}
	},

	
	// Vengar:
	
	VENGAR_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "鼠窟";
		}
		@Override
		public String getDescription() {
			return "你同意帮助阿克塞尔对付文加——屈城区最大、最危险团伙的头目。你可以直接去他的藏身处“鼠窟”，或是先向克莱尔寻求帮助。";
		}
		@Override
		public String getCompletedDescription() {
			return "你使用阿克塞尔给的密码，得以进入文加的藏身处：鼠窟。";
		}
	},
	
	VENGAR_ONE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "与文加见面";
		}
		@Override
		public String getDescription() {
			return "为了找到文加，你需要在"+Units.time(LocalDateTime.of(1, 1, 1, 6, 0))+"和"+Units.time(LocalDateTime.of(1, 1, 1, 22, 0))+"的时间段到主厅。";
		}
		@Override
		public String getCompletedDescription() {
			return "你发现文加坐在主厅的宝座上，接近他之后，你要么选择加入他的帮派，要么会被他的鼠女保镖攻击。";
		}
	},
	
	VENGAR_TWO_CONFLICT(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "冲突";
		}
		@Override
		public String getDescription() {
			return "选择了挑战文加，现在你需要在战斗中击败他，才能在他的帮派中占据主导地位。";
		}
		@Override
		public String getCompletedDescription() {
			return "你成功击败了文加，但在你进一步行动前，SWORD就开始了对鼠窟的突袭。";
		}
	},
	
	VENGAR_TWO_COOPERATION(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "阿克塞尔的臣服";
		}
		@Override
		public String getDescription() {
			return "文加透露，一段时间以来，他一直想专注于自己合法的朗姆酒厂业务，但他如果轻易地放过阿克塞尔就会失去帮派成员的尊重。"
					+ "作为结束勒索的交换，你同意说服阿克塞尔来向文加表明他的顺从。";
		}
		@Override
		public String getCompletedDescription() {
			return "你成功说服阿克塞尔去鼠窟向文加表示臣服。"
					+ "陪同他去那里，你能够做些什么来影响鳄男的遭遇。";
		}
	},
	
	VENGAR_TWO_ENFORCERS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "突袭";
		}
		@Override
		public String getDescription() {
			return "在确认文加就藏在鼠窟后，你启动了回声石向在等待着的SWORD执法者发出开始突袭的信号。";
		}
		@Override
		public String getCompletedDescription() {
			return "SWORD的执法者成功突袭了鼠窟并逮捕了文加。";
		}
	},
	
	VENGAR_THREE_COOPERATION_END(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "文加的末路";
		}
		@Override
		public String getDescription() {
			return "[axel.name]已经向文加表示臣服，剩下要做的就是返回赌场……";
		}
		@Override
		public String getCompletedDescription() {
			return "在[axel.name]向文加表示臣服后，一群SWORD执法者出现了，他们突袭了鼠窟并逮捕了那鼠男！";
		}
	},

	VENGAR_THREE_END(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "回去找阿克塞尔";
		}
		@Override
		public String getDescription() {
			return "文加已经被解决了，你需要回去找阿克塞尔，让他知道发生了什么。";
		}
		@Override
		public String getCompletedDescription() {
			return "你回到阿克塞尔身边，告诉他之后情况会如何发展。";
		}
	},
	
	VENGAR_OPTIONAL_CLAIRE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "克莱尔的帮助";
		}
		@Override
		public String getDescription() {
			return "你认为让克莱尔了解情况是最好的选择，于是向她询问执法者是否能帮上忙。"
					+ "看来，有一支SWORD小队已经准备好突袭鼠窟了，但他们需要在发动攻击之前确认文加在里面。"
					+ "克莱尔给了你一块回声石，如果你想让执法者支援你，就激活它。";
		}
		@Override
		public String getCompletedDescription() {
			return "你认为让克莱尔了解情况是最好的选择，于是向她询问执法者是否能帮上忙。"
					+ "看来，有一支SWORD小队已经准备好突袭鼠窟了，但他们需要在发动攻击之前确认文加在里面。"
					+ "克莱尔给了你一块回声石，如果你想让执法者支援你，就激活它。";
		}
	},

	// Wes:

	WES_FAIL(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "错失机会";
		}
		@Override
		public String getDescription() {
			return "在告知韦斯利你无意帮助他调查之后，这个狐男消失了，你可以确定他再也不会寻求你的帮助了……";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	WES_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "请求帮助";
		}
		@Override
		public String getDescription() {
			return "穿行于御城区时，一名神秘的SWORD秘密执法者向你求助。"
					+ "他想和你在[units.time(13)]到[units.time(14)]于购物中心的古董店外见面。";
		}
		@Override
		public String getCompletedDescription() {
			return "你答应帮助韦斯利调查他的上级警官。";
		}
	},

	WES_1(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "寻找埃勒";
		}
		@Override
		public String getDescription() {
			return "韦斯告诉你，他的警官上司，[elle.name](简称“埃勒”)，每周三都会带上夜视镜去午休很长时间，回来时鞋子总是湿的。"
					+ "你需要找到她，并用韦斯给你的奥术录像机记录下所有罪证……"
					+ "<br/>她的[style.colourOrange(午休时间异常长)]。"
					+ "<br/>她只在[style.colourOrange(星期三)]出现这种情况。"
					+ "<br/>她随身携带一套[style.colourOrange(夜视镜)]。"
					+ "<br/>之后，她的[style.colourOrange(鞋子总是湿的)]，有时[style.colourOrange(上面沾有发光残留物)]。"
					+ "<br/>[style.italicsMinorGood(可以询问莉莱雅以获得目标地点的提示。)]";
		}
		@Override
		public String getCompletedDescription() {
			return "你推断出埃勒在蝙蝠洞窟做着见不得人的生意，并设法收集到了她向一个危险的犯罪帮派出售武器的证据。";
		}
	},

	WES_2(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "使用证据";
		}
		@Override
		public String getDescription() {
			return "你的奥术录像机记录有埃勒腐败的罪证，你有两种可选的处理方式。"
					+ "你可以把它匿名举报给克莱尔或坎迪，或者如果你想背叛韦斯并站在埃勒这边，"
						+ "你也可以在[units.time(16)]到[units.time(18)]于执法者总部外等待埃勒，在下班时间向[elle.race]表明一切。";
		}
		@Override
		public String getCompletedDescription() {
			return "你要利用收集的证据给韦斯和埃勒的全部交易画上句号。";
		}
	},

	WES_3_WES(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "支持韦斯";
		}
		@Override
		public String getDescription() {
			return "你决定按照韦斯的要求做，并将指控证据匿名举报给执法者。"
					+ "事情解决至少要一周时间，之后你可以在[units.time(9)]到[units.time(17)]之间去执法者总部看望韦斯。";
		}
		@Override
		public String getCompletedDescription() {
			return "你支持了韦斯，在匿名提交指控证据后，你在执法者总部遇到了这狐男，他被提拔来接替埃勒的工作。"
					+ "他有了新的权限，作为对你帮助的回报，他允许你进入执法者总部的军需处。"
					+ "你可以在[units.time(9)]到[units.time(17)]之间不受限制地去执法者总部拜访韦斯。";
		}
	},

	WES_3_ELLE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "支持埃勒";
		}
		@Override
		public String getDescription() {
			return "你决定背叛韦斯，并将一切都告诉埃勒。"
					+ "多亏了你的意外帮助，[elle.race]承诺，你可以在至少一周后回到执法者总部，她会给你奖励……";
		}
		@Override
		public String getCompletedDescription() {
			return "你支持了埃勒，你回到执法者总部与她会面，发现韦斯被奴役了。"
					+ "[elle.race]允许你进入执法者总部的军需处，以此来表达对你的感谢。"
					+ "你可以在[units.time(9)]到[units.time(17)]之间不受限制地去执法者总部拜访埃勒(以及韦斯)。";
		}
	},

	
	// Rebel Base for HLF Quest
	
	REBEL_BASE_HANDLE_REFUSED(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "拉下拉杆！";
		}
		@Override
		public String getDescription() {
			return "你在蝙蝠洞窟里发现了一个奇怪的拉杆。没人知道它是干什么用的。";
		}
		@Override
		public String getCompletedDescription() {
			return "拉下拉杆不是最佳选择，但你还是这么做了。";
		}
	},

	REBEL_BASE_PASSWORD_PART_ONE(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "拉下拉杆，得到谜题";
		}
		@Override
		public String getDescription() {
			return "奇怪的拉杆要求你输入某种未知的密码。也许在附近搜索一下就能发现线索。";
		}
		@Override
		public String getCompletedDescription() {
			return "你找到的半张日记纸上说密码是两个单词，你只能辨认一个单词，另一个被撕掉了。";
		}
	},

	REBEL_BASE_PASSWORD_PART_TWO(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "填空题";
		}
		@Override
		public String getDescription() {
			return "另一半密码一定在另外半张日记纸上，也许能在附近找到。";
		}
		@Override
		public String getCompletedDescription() {
			return "你找全了两部分密码，它们一起构成了短语“RUAT CAELUM”(哪怕天崩地裂)。";
		}
	},

	REBEL_BASE_PASSWORD_COMPLETE(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "芝麻开门";
		}
		@Override
		public String getDescription() {
			return "有了完整的密码，你可以再次尝试挑战拉杆了。";
		}
		@Override
		public String getCompletedDescription() {
			return "你发现拉杆其实连接着一扇门，它通向一个从蝙蝠洞窟中分离出来的隐藏洞穴。";
		}
	},

	REBEL_BASE_EXPLORATION(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "洞穴大冒险";
		}
		@Override
		public String getDescription() {
			return "没人知道这隐藏洞穴内有何物或通往何处。或许你可以进入洞穴寻找答案。";
		}
		@Override
		public String getCompletedDescription() {
			return "你发现这个隐藏的洞穴是一个早已消失的反抗军组织的藏身之地。从种种特征来看，他们最终没能获胜。";
		}
	},

	REBEL_BASE_ESCAPE(QuestType.SIDE,
			15,
			100) {
		@Override
		public String getName() {
			return "逃出生天";
		}
		@Override
		public String getDescription() {
			return "赶在洞穴完全坍塌之前，是时候逃走了。";
		}
		@Override
		public String getCompletedDescription() {
			return "你安然无恙地逃出了洞穴。洞穴里其它东西现在都被永远埋葬了。";
		}
	},

	REBEL_BASE_FAILED(QuestType.SIDE,
			15,
			0) {
		@Override
		public String getName() {
			return "临阵退缩";
		}
		@Override
		public String getDescription() {
			return "你安然无恙地逃出了洞穴，但洞穴里隐藏的所有秘密，现在都永远被埋葬了。";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	REBEL_BASE_FIREBOMBS_START(QuestType.SIDE,
			1,
			5) {
		@Override
		public String getName() {
			return "点燃引信";
		}
		@Override
		public String getDescription() {
			return "你从神秘洞窟中得到的燃烧弹可以在战斗中派上用场。你需要找到一个能制造燃烧弹或者有燃烧弹渠道的人。";
		}
		@Override
		public String getCompletedDescription() {
			return "罗克西答应帮你调查燃烧弹的获取渠道。";
		}
	},

	REBEL_BASE_FIREBOMBS_FINISH(QuestType.SIDE,
			1,
			5) {
		@Override
		public String getName() {
			return "鼠人火力";
		}
		@Override
		public String getDescription() {
			return "罗克西需要两天时间才能获得新的燃烧弹。你最好到时候回来找她。";
		}
		@Override
		public String getCompletedDescription() {
			return "不知为什么，罗克西没有骗你，你得到了一批燃烧弹。";
		}
	},

	REBEL_BASE_FIREBOMBS_FAILED(QuestType.SIDE,
			1,
			0) {
		@Override
		public String getName() {
			return "不情愿的罗克西";
		}
		@Override
		public String getDescription() {
			return "罗克西手头没有样品，她无法理解何为燃烧弹，亦或是她不想在仿制燃烧弹这件事上浪费时间……";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	//Eisek Quests
	
	EISEK_STALL_QUEST_STAGE_ONE(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "收集材料";
		}
		@Override
		public String getDescription() {
			return "你已经知道了艾瑟克装修摊位所需的材料以及他的新招牌上想要的内容。现在你只需要从小镇附近的商贩那收集这些材料来给他一个惊喜。也许附近会有一家布店？";
		}
		@Override
		public String getCompletedDescription() {
			return "你向莫妮卡订购了一个新招牌和一些布料。";
		}
	},
	
	EISEK_STALL_QUEST_STAGE_TWO(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "这里需要一个遮阳棚";
		}
		@Override
		public String getDescription() {
			return "等待莫妮卡完成你的订单的时候，你需要找一些木棍来搭建遮阳棚。或许当地的铁匠可以拆卸长柄武器的柄？";
		}
		@Override
		public String getCompletedDescription() {
			return "你已经向因苏和黑尔订购了一些斧头的柄。";
		}
	},
	
	EISEK_STALL_QUEST_STAGE_THREE(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "一切都准备就绪。";
		}
		@Override
		public String getDescription() {
			return "你应该在一天后确认黑尔的订单，在三天后确认莫妮卡的订单都是否准备好了。";
		}
		@Override
		public String getCompletedDescription() {
			return "你已经收集了所需的全部材料。";
		}
	},
	
	EISEK_STALL_QUEST_STAGE_FOUR(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "全部组装起来";
		}
		@Override
		public String getDescription() {
			return "你已经有办法去改造艾瑟克的摊位了。下次见面的时候告诉他吧。";
		}
		@Override
		public String getCompletedDescription() {
			return "你可以肯定地说，艾瑟克对你为他所做的一切感到非常高兴，他的摊位看起来比以往任何时候都好。";
		}
	},
	
	EISEK_MOB_QUEST_STAGE_ONE(QuestType.SIDE,
			10,
			25) {
		@Override
		public String getName() {
			return "以一敌众";
		}
		@Override
		public String getDescription() {
			return "艾瑟克解释了为什么会有暴徒追杀他，但是他并不认识他们。如果你想要确保这些暴徒不会再来，你应该去找到他们并与其对峙。"
					+ "<br/>他们似乎都是本地人，可能在镇上找找就可以找到。";
		}
		@Override
		public String getCompletedDescription() {
			return  "通过一点点运气和暴徒贴在墙上的全彩海报，你找到并进入了他们的据点。";
		}
	},
	
	EISEK_MOB_QUEST_STAGE_TWO(QuestType.SIDE,
			10,
			100) {
		@Override
		public String getName() {
			return "离那龙远一点！";
		}
		@Override
		public String getDescription() {
			return "你已经找到了暴徒的据点，是时候处理他们了。";
		}
		@Override
		public String getCompletedDescription() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_intimidate"))) {
			    return "你决定用你那令人生畏的力量去说服暴徒远离艾瑟克。";
			} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_intimidate_arcane"))) {
			    return "你决定用你的奥术能力去说服暴徒远离艾瑟克。";
			} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_persuade"))) {
			    if(!Main.game.isSillyModeEnabled()) {
			    	return "你用感人的话语说服了暴徒远离艾瑟克。";
			    } else {
			    	return "你用事实和富有逻辑的话语推翻了暴徒的观点。";
			    }
			} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_seduce"))) {
			    return "你精通欲望魔法，利用它煽动了一场淫趴，从而说服了暴徒远离艾瑟克。";
			} else {
			    return "你没能说服暴徒放过艾瑟克。";
			}
		}
	},
	
	EISEK_MOB_QUEST_STAGE_TWO_FAILED(QuestType.SIDE,
			10,
			0) {
		@Override
		public String getName() {
			return "抛弃";
		}
		@Override
		public String getDescription() {
			return "你没能成功说服暴徒放过艾瑟克。你应该回去告诉他这个坏消息因为你处理不了那些暴徒。";
		}
		@Override
		public String getCompletedDescription() {
			return "你没能说服暴徒放过艾瑟克。";
		}
	},
	
	EISEK_MOB_QUEST_STAGE_THREE_FAILED(QuestType.SIDE,
			10,
			0) {
		@Override
		public String getName() {
			return "坏消息";
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String getCompletedDescription() {
			return "尽管他想试图掩盖，但他还是对于有暴徒想暗算他而感到非常不安。";
		}
	},
	
	EISEK_MOB_QUEST_STAGE_THREE(QuestType.SIDE,
			10,
			250) {
		@Override
		public String getName() {
			return "好消息";
		}
		@Override
		public String getDescription() {
			return "你已经解决了暴徒，现在该回去告诉艾瑟克这个好消息了。";
		}
		@Override
		public String getCompletedDescription() {
			return "尽管他试图掩盖，但还是表现出了对于不会有暴徒打扰他的高兴。你甚至因此获得了一颗稀有的火龙果。";
		}
	},
	
	EISEK_SILLYMODE_QUEST_STAGE_ONE(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "古怪的人群";
		}
		@Override
		public String getDescription() {
			return "你遇到另一群对艾瑟克异常痴迷的暴徒，虽然无果而终，但你还是想看看这群人到底怎么回事。";
		}
		@Override
		public String getCompletedDescription() {
			return "你进入了他们聚集的地下室。";
		}
	},
	
	EISEK_SILLYMODE_QUEST_STAGE_TWO(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "暗黑地牢";
		}
		@Override
		public String getDescription() {
			return "你尾随地下居民返回了他们的地下室，决定进去看看。遗憾的是他们不太喜欢你的非法入侵行为，拦住了你的去路。";
		}
		@Override
		public String getCompletedDescription() {
			return "你很接近出口了，只剩下一个障碍……";
		}
	},
	
	EISEK_SILLYMODE_QUEST_STAGE_THREE(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "地牢已通关";
		}
		@Override
		public String getDescription() {
			return "你击败了这古怪团伙的首领，剩下要做的就是离开这里了。";
		}
		@Override
		public String getCompletedDescription() {
			return "你成功逃离了地牢，让这群蠢货明白了谁才是老大。";
		}
	},
	
	// Fetching beer barrels for Oglix:
	
	OGLIX_BEER_BARRELS_1(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "喀戎的桶";
		}
		@Override
		public String getDescription() {
			return "你同意帮奥格利克斯扩大奶啤妓业务，接下来的任务是前往酒馆“半人马之剑”，问问店主喀戎有没有闲置的备用桶。"
					+ "如果他拒绝帮忙，戈利克斯吩咐你告诉半人马“戈利克斯让你当只乖小马”。";
		}
		@Override
		public String getCompletedDescription() {
			return "多亏了特殊咒语“戈利克斯让你当只乖小马”，你才说服喀戎把四个备用桶送到奥格利克斯的酒馆。";
		}
	},
	
	OGLIX_BEER_BARRELS_2(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "乖小马报告";
		}
		@Override
		public String getDescription() {
			return "在取得喀戎的帮助后，你需要回去通知奥格利克斯，喀戎选择成为戈利克斯的“乖小马”。";
		}
		@Override
		public String getCompletedDescription() {
			return "你回去找到奥格利克斯，告诉她你成功了。"
					+ "奥格利克斯获得了额外四个桶，可以用来锁住新的奶啤妓，她希望你从附近小巷的犯罪者中寻找合适的人选。"
					+ "此外，她说如果你想知道“戈利克斯让你当只乖小马”这句话的意思，就在[units.time(6)]-[units.time(7)]之间偷偷到她酒馆后面来……";
		}
	},

	
	// Helping Lunexis to escape:
	
	LUNEXIS_ESCAPE(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "释放露内克西丝";
		}
		@Override
		public String getDescription() {
			return "你屈服于露内克西丝，发誓要做她忠诚的鸡巴套，你的新主人命令你帮助她逃离监禁。"
					+ "为了报复将她传送到伊利斯的人，半人马设计了一个计划，你要说服梅拉克西丝将你们三人传送回特弥斯库拉。"
					+ "一旦回到那里，你的主人就会奖励你，让你成为其中一个私人鸡巴套奴隶……";
		}
		@Override
		public String getCompletedDescription() {
			return "你说服了梅拉克西丝，将她自己连同你和露内克西丝一起传送回特弥斯库拉。"
					+ "回到那里，你的欺骗就昭然若揭了。尽管梅拉克西丝试图战斗，但很快就被制服，你的主人利用她赢回了半人马部队动摇的忠诚。";
		}
	},

	LUNEXIS_ESCAPE_FAILED(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "背叛露内克西丝";
		}
		@Override
		public String getDescription() {
			return "你决定背叛露内克西丝，向梅拉克西丝坦白一切，之后你被禁止与恶魔半人马有任何进一步的接触。";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	
	// Doll factory quests:
	
	DOLL_FACTORY_1(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "调查洛维耶纳奢侈品店";
		}
		@Override
		public String getDescription() {
			return "安吉莉克丝的日记里提到，被绑架的难民都被传送到了御城区的“洛维耶纳奢侈品店”。"
					+ "如果你想了解安吉莉克丝的受害者经历了什么，那你需要调查这家商店……";
		}
		@Override
		public String getCompletedDescription() {
			return "你来到“洛维耶纳奢侈品店”，试图查明安吉莉克丝绑架的难民是否被带到了那里。"
					+ "虽然什么都没发现，但当你准备离开时，一个名叫菲亚梅塔的记者接近你并提供了帮助。";
		}
	},
	
	DOLL_FACTORY_2(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "突破进入";
		}
		@Override
		public String getDescription() {
			return "菲亚梅塔知道有条暗道能够进入洛维耶纳奢侈品店后方，她认定被绑架的难民就在那里，做奴隶劳工。"
					+ "你也没有其他方式得知下面究竟发生了什么，于是同意了她的计划，并约定[units.time(1)]-[units.time(4)]之间在商店附近见面。";
		}
		@Override
		public String getCompletedDescription() {
			return "你在“洛维耶纳奢侈品店”外见到了菲亚梅塔，并在没有触发报警系统的情况下从后面闯入了房屋。";
		}
	},
	
	DOLL_FACTORY_3(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "追根究底";
		}
		@Override
		public String getDescription() {
			return "你已经成功进入洛维耶纳奢侈品店的后方，搜查一下被绑架的难民的所在。";
		}
		@Override
		public String getCompletedDescription() {
			return "你发现洛维耶纳奢侈品店的后方有一台巨大的电梯，坐电梯下去后，竟找到御城区地下隐藏的一个巨型设施。";
		}
	},
	
	DOLL_FACTORY_4(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "收集证据";
		}
		@Override
		public String getDescription() {
			return "你和菲亚梅塔需要寻找难民遭遇的蛛丝马迹。"
					+ "肯定能在某个地方找到账本、机器原理图或者诸如此类的文件……";
		}
		@Override
		public String getCompletedDescription() {
			return "你收集到了确凿证据，能证明拉特里克斯和安吉莉克丝参与了绑架与非法奴役，并且得知了洛维耶纳的玩偶的恐怖制造方法。";
		}
	},
	
	DOLL_FACTORY_5(QuestType.SIDE, 30, 250) {
		@Override
		public String getName() {
			return "该离开了";
		}
		@Override
		public String getDescription() {
			return "既然菲亚已经收集到足够证据将报道发表，你们两个现在需要从工厂中逃离……";
		}
		@Override
		public String getCompletedDescription() {
			return "你和菲亚成功从地下工厂逃了出来。";
		}
	},
	
	DOLL_FACTORY_5_DOLLIFIED(QuestType.SIDE, 30, 250) {
		@Override
		public String getName() {
			return "要变成玩偶了！";
		}
		@Override
		public String getDescription() {
			return "你被安吉莉克丝抓住了，她将你变成了玩偶！你现在急需找到菲亚，在她的帮助下取消这个可怕的转化。"
					+ "<br/>[style.italics(你需要搜寻并调查工厂内四个)][style.italicsExcellent(黄色)][style.italics(的地块来寻找菲亚。)]";
		}
		@Override
		public String getCompletedDescription() {
			return "安吉莉克丝把你转化成了玩偶，不过你设法找到了菲亚并逆转了玩偶化进程，之后你和菲亚一起逃离了工厂。";
		}
	},
	
	DOLL_FACTORY_6(QuestType.SIDE, 30, 500) {
		@Override
		public String getName() {
			return "菲亚梅塔的消息";
		}
		@Override
		public String getDescription() {
			return "菲亚梅塔保证会写一篇文章，将你们二人在洛维耶纳奢侈品店目睹的一些公之于众。"
					+ "她还说好了只要做好发表的准备，就马上给你消息。"
					+"<br/>[style.italicsMinorGood(已经过了好几天，白天的时候检查一下莉莱雅宅邸的门厅吧。)]";
		}
		@Override
		public String getCompletedDescription() {
			return "你并没有等到菲亚梅塔的消息，反而遇到了赛拉特里克斯。"
					+ "她利用自己与莉莱雅的亲密关系，将你逼入困境并要求你签署一份文件，宣称菲亚梅塔说的都是谎话。";
		}
	},
	
	//TODO
	
	DOLL_FACTORY_7A(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "诚信至上";
		}
		@Override
		public String getDescription() {
			return "你拒绝与赛拉特里克斯同流合污，反而发誓必定会讲出真相，为菲亚梅塔文章中所写的一切背书。"
					+ "赛拉特里克斯说她会将商店关停一到两周的时间，以等待公众的愤怒平息，之后她便迅速地离开了宅邸。"
					+ "<br/>[style.italicsMinorGood(你打算在洛维耶纳奢侈品店重新开业之后，回去看看玩偶们怎么样了……)]";
		}
		@Override
		public String getCompletedDescription() {
			return "你拒绝与赛拉特里克斯同流合污，反而发誓必定会讲出真相，为菲亚梅塔文章中所写的一切背书。"
					+ "洛维耶纳奢侈品店关停一周后，你又一次回到了这里，你发现安吉莉克丝被认定为了非法奴役事件背后的主谋，"
						+ "而赛拉特里克斯现在只被允许将最恶劣的罪犯转化为玩偶。"
					+ "<br/>"
					+ "此外，赛拉特里克斯并不想与你为敌，她热切地希望能留住你这名顾客，甚至将安吉莉克丝的处置权也交到了你的手里……";
		}
	},
	
	DOLL_FACTORY_7B(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "多好的生意";
		}
		@Override
		public String getDescription() {
			return "你背弃了菲亚梅塔的信任，在文件上签下了名字，宣称报告中谎话连篇。"
					+ "赛拉特里克斯对你的决定十分满意，邀请你在商店重新开业后再光顾一次，她为你准备了些特别的奖励……"
					+ "<br/>[style.italicsMinorGood(等过几天洛维耶纳奢侈品店重新开业之后，你打算再去一趟……)]";
		}
		@Override
		public String getCompletedDescription() {
			return "你背弃了菲亚梅塔的信任，在文件上签下了名字，宣称报告中谎话连篇。"
					+ "作为报答，赛拉特里克斯可以让你只花一笔小钱就帮你把你的奴隶转化为玩偶，如果你将其放在店中售卖，她也会交给你应得的钱。";
		}
	},
	
	
	// Romance quests:

	RELATIONSHIP_NYAN_1_STOCK_ISSUES(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "帮助妮安";
		}
		@Override
		public String getDescription() {
			return "妮安解释说，由于供货商突然失去联系，附魔服装的货源被掐断了。"
					+ "显而易见，这供货商以前和妮安关系不错，他的反常举动让紧张的猫女怀疑他遭遇什么可怕的事情。<br/>"
					+ "也许你可以帮助妮安找出供货商的下落？";
		}
		@Override
		public String getCompletedDescription() {
			return "你向妮安伸出援手，助她查清附魔服装供应商到底遭遇了什么。";
		}
	},
	
	RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "救凯";
		}
		@Override
		public String getDescription() {
			return "妮安告诉你，她的供货商[kay.nameFull]在御城区的仓库开展业务。"
					+ "你需要前往城市西偏北的地方，找到凯的仓库，了解他为什么突然切断了与妮安的所有联系。";
		}
		@Override
		public String getCompletedDescription() {
			return "你找到了凯的仓库，发现他的生意实际上已经被一对恃强凌弱的赏金猎人抢走了。";
		}
	},
	
	RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS(QuestType.RELATIONSHIP, 10, 100) {
		@Override
		public String getName() {
			return "赏金恶霸兄弟";
		}
		@Override
		public String getDescription() {
			return "沃尔夫冈和卡尔是一对赏金猎人，受凯的雇佣来保护他的仓库，但他们背叛了雇主，实际上全盘控制住了他的生意来往。"
					+ "不管用什么办法，你都得说服这些杜宾恶霸，让他们离凯远点……";
		}
		@Override
		public String getCompletedDescription() {
			return "你搞定了沃尔夫冈和卡尔，让他们滚回奴隶巷的“赏金猎人小屋”。现在，你既保住了凯的生意，又让妮安恢复了附魔服饰的库存。"
					+ "凯表示永远不会忘记你的恩情，欢迎你随时来找他。";
		}
	},
	
	RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "妮安的奖励";
		}
		@Override
		public String getDescription() {
			return "你已经拯救了凯的生意，现在该回去告诉妮安发生了什么了。";
		}
		@Override
		public String getCompletedDescription() {
			return "妮安高兴极了，不仅付了事先约定的报酬，还表示商店以后都会给你提供七五折的终身优惠。"
					+ "她还透露自己是单身，笨笨地向你搭讪……";
		}
	},
	
	
	
	ROMANCE_HELENA_FAILED(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "盛怒的族长";
		}

		@Override
		public String getDescription() {
			return "在你拒绝把斯嘉丽卖给海伦娜后，这位哈比族长放弃了她经营奴隶买卖的计划并愤怒的回巢了。"
					+ "她走之前非常无情的辱骂了你，你可以肯定她再也不想见到你了……";
		}

		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	ROMANCE_HELENA_1_OFFER_HELP(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "提供帮助";
		}

		@Override
		public String getDescription() {
			return "在向海伦娜打听过她的生意后，你发现她只能勉强维持着此处的运转。"
					+ "在海伦娜表达了她对于商店改造的想法后，她表示她根本没有时间也没有兴趣完成这个工作。"
					+ "或许你可以提供点帮助？";
		}

		@Override
		public String getCompletedDescription() {
			return "你向海伦娜伸出援手，帮助商店进行改善。";
		}
	},

	ROMANCE_HELENA_2_PURCHASE_PAINT(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "购买颜料";
		}

		@Override
		public String getDescription() {
			return "海伦娜提及了她想做的第一件事，重新粉刷整个店铺的外观。"
					+ "哈比没有给你提供任何资金，且希望你去“阿格斯的DIY仓库”买一罐“紫星”牌金色颜料。"
					+ "在购买后，你需要回去找海伦娜。"
					+ "<br/><i>(“阿格斯的DIY仓库”可以在奴隶巷的南侧找到，就在运河旁边。)</i>";
		}

		@Override
		public String getCompletedDescription() {
			return "你从“阿格斯的DIY仓库”处购买了需求物品，并返回去找海伦娜。";
		}
	},

	ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "室外装饰工(1/3)";
		}

		@Override
		public String getDescription() {
			return "在你买到金色油漆后你回到海伦娜的商店，她要求你尽快开始重新粉刷她的商店外部……";
		}

		@Override
		public String getCompletedDescription() {
			return "你刮掉了海伦娜商店前面的旧油漆。";
		}
	},

	ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "室外装饰工(2/3)";
		}

		@Override
		public String getDescription() {
			return "你需要在营业时间回到海伦娜的商店来确认你的下一个任务是什么……";
		}

		@Override
		public String getCompletedDescription() {
			return "你重新粉刷了海伦娜商店的正面，并且收到了娜塔莉亚送来的家具。";
		}
	},

	ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "室外装饰工(3/3)";
		}

		@Override
		public String getDescription() {
			return "你需要在营业时间回到海伦娜的商店来确认你的下一个任务是什么……";
		}

		@Override
		public String getCompletedDescription() {
			return "在哈比族长的监督下，你在她的店铺门上用金字写下“海伦娜精品店”。";
		}
	},

	ROMANCE_HELENA_4_SCARLETTS_RETURN(QuestType.RELATIONSHIP, 1, 100) {
		@Override
		public String getName() {
			return "斯嘉丽归来";
		}

		@Override
		public String getDescription() {
			boolean slave = Main.game.getNpc(Scarlett.class).isSlave() || Main.game.getNpc(Scarlett.class).getHomeWorldLocation()==WorldType.EMPTY;
			boolean playerOwner = Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer();
			return "海伦娜向你透露道她计划将她的奴隶店变成一个可以让客户定制奴隶的地方。"
					+ "她想让她以前的礼仪老师来训练这些奴隶，那个人正是斯嘉丽的姐妹。"
					+ (slave
						?"她给海伦娜的条件是让她任性的妹妹获得自由，保证不再奴役她而且让她继续工作……"
							+ "<br/>"
							+(playerOwner
								?"你把斯嘉丽带给海伦娜并卖给她……"
								:"你需要找到斯嘉丽并从她的新主人那里买下她。据海伦娜所说，她是被购物中心里的古董店老板买走的。")
						:"她给海伦娜的条件是让她任性的妹妹获得自由，并保证不再奴役她……"
							+ "<br/>"
							+ "你需要去海伦娜的巢找到斯嘉丽，让她回到海伦娜身边……");
		}
		
		@Override
		public String getCompletedDescription() {
			return "根据海伦娜以前的礼仪教练的意愿，斯嘉丽从今以后将担任哈比族长的个人助理。";
		}
	},

	ROMANCE_HELENA_5_SCARLETT_TRAINER(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "哈比的帮手";
		}

		@Override
		public String getDescription() {
			return "两名哈比早早离开去拜访斯嘉丽的姐妹了。你需要在营业时间回到海伦娜的商店，看看你的下一个任务是什么……";
		}

		@Override
		public String getCompletedDescription() {
			return "你回到海伦娜的商店，发现一切都安排妥当，准备好让哈比族长接待顾客了。然而，在此之前，你还需要帮她做一些事情……";
		}
	},

	ROMANCE_HELENA_6_ADVERTISING(QuestType.RELATIONSHIP, 1, 15) {
		@Override
		public String getName() {
			return "广告活动";
		}

		@Override
		public String getDescription() {
			return "你拿到了六张炫耀海伦娜美貌的附魔海报，你的任务是把它们贴在奴隶巷的入口处，为她的商店做广告。";
		}

		@Override
		public String getCompletedDescription() {
			return "你在奴隶巷的入口处张贴了“海伦娜精品店”的海报。";
		}
	},

	ROMANCE_HELENA_7_GRAND_OPENING_PREPARATION(QuestType.RELATIONSHIP, 1, 15) {
		@Override
		public String getName() {
			return "为盛大开业做准备";
		}

		@Override
		public String getDescription() {
			return "贴完海报后，斯嘉丽出现了，带你返回海伦娜的商店。"
					+ "你的新任务是为明天的盛大开业做好准备，这意味着你要彻夜工作……";
		}

		@Override
		public String getCompletedDescription() {
			return "你和斯嘉丽为商店的盛大开业做好了准备。";
		}
	},

	ROMANCE_HELENA_8_FINISH(QuestType.RELATIONSHIP, 1, 100) {
		@Override
		public String getName() {
			return "调制饮料";
		}

		@Override
		public String getDescription() {
			return "海伦娜不想让斯嘉丽在开业典礼上惹麻烦，所以让你们两个留在里屋为客人调制饮料。";
		}

		@Override
		public String getCompletedDescription() {
			return "你和斯嘉丽一直在里屋调制饮料，直到开业典礼结束。"
					+ "海伦娜终于回应了你的努力，她告诉你，作为奖励，她愿意让你带她去约会……";
		}
	},
	
	

	ROMANCE_NATALYA_FAILED_INTERVIEW(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "面试失败";
		}
		@Override
		public String getDescription() {
			return "你拒绝按照娜塔莉亚在面试中的要求行事，你被赶出了御城速递，并被告知永远不要回来……";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	ROMANCE_NATALYA_FAILED_CONTRACT(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "合同被拒";
		}
		@Override
		public String getDescription() {
			return "你拒绝签订娜塔莉亚提供给你的合同，你被赶出了御城速递，并被告知永远不要回来……";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	ROMANCE_NATALYA_1_INTERVIEW_START(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "接受面试";
		}
		@Override
		public String getDescription() {
			return "娜塔莉亚是御城速递的马场主，她给你提供了一个面试“[style.mule]”职位的机会。";
		}
		@Override
		public String getCompletedDescription() {
			return "你接受了娜塔莉亚的邀请，进行御城速递“[style.mule]”职位的面试。";
		}
	},

	ROMANCE_NATALYA_2_CONTRACT_SIGNED(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "娜塔莉亚的[style.Mule]";
		}
		@Override
		public String getDescription() {
			return "你接受了“[style.mule]”职位的面试邀请，接下来需要成功通过并签订合同。";
		}
		@Override
		public String getCompletedDescription() {
			return "你成功通过了娜塔莉亚的面试，在签订合同后，你被告知需要转化为[style.a_shemale]半兽人。";
		}
	},
	
	ROMANCE_NATALYA_3_TRAINING_1(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "[style.Mule]训练";
		}
		@Override
		public String getDescription() {
			return "娜塔莉亚告诉你，训练的第一阶段是让你给一名半人马奴隶反复进行口交。";
		}
		@Override
		public String getCompletedDescription() {
			return "在转化为[style.a_shemale]半兽人后，[style.mule]训练开始，你吮吸了御城速递一名不听管教的半人马奴隶的鸡巴。";
		}
	},

	ROMANCE_NATALYA_4_TRAINING_2(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "更多的训练";
		}
		@Override
		public String getDescription() {
			return "再一次，娜塔莉亚告诉你第二天回来继续训练，内容包括学习如何爱上舔肛。";
		}
		@Override
		public String getCompletedDescription() {
			return "训练的第二阶段是涂上色彩鲜艳的口红，给娜塔莉亚主人进行吻肛。";
		}
	},

	ROMANCE_NATALYA_5_TRAINING_3(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "最终训练";
		}
		@Override
		public String getDescription() {
			return "娜塔莉亚命令你明天回来结束训练，内容包括给一个半人马奴隶舔肛，然后被娜塔莉亚和这个奴隶骑上并肛交。";
		}
		@Override
		public String getCompletedDescription() {
			return "在对一个半人马奴隶进行舔肛，然后被他们骑上并肛交后，娜塔莉亚宣布你的[style.mule]训练已经完成。";
		}
	},

	ROMANCE_MONICA_1_TO_THE_FARM(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "去往农场";
		}
		@Override
		public String getDescription() {
			return "你主动提出帮莫妮卡找回定制吸奶器，她告诉你可以去她以前的工作场所寻找，那是一个位于伊利斯东北部，名为“艾弗利克斯乳业”的农场。"
					+ "你必须前往这个农场，询问莫妮卡的定制吸奶器的下落……";
		}
		@Override
		public String getCompletedDescription() {
			return "你找到了莫妮卡以前工作的农场，索要牛女的定制吸奶器。你获准与农场主会面……";
		}
	},

	ROMANCE_MONICA_2_UNREASONABLE_DEMAND(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "不情之请";
		}
		@Override
		public String getDescription() {
			return "你与农场主艾弗利克斯会面，她是一名外表贪婪又傲慢的魅魔。"
					+ "虽然她承认吸奶器毫无价值，但她还是要求你给她一大笔钱，或者签署一份可疑的合同来换取它……";
		}
		@Override
		public String getCompletedDescription() {
			return "你设法从艾弗利克斯处获得了莫妮卡的定制吸奶器。";
		}
	},

	ROMANCE_MONICA_3_THE_JOURNEY_HOME(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "回家之路";
		}
		@Override
		public String getDescription() {
			return "现在莫妮卡的定制吸奶器已经到了你的手里，剩下要做的就是还给它的主人了。";
		}
		@Override
		public String getCompletedDescription() {
			return "你将莫妮卡的定制吸奶器还给了她，这让她喜出望外。";
		}
	},
	;

	private int level, experienceReward;
	private QuestType questType;

	private Quest(QuestType questType, int level, int experienceReward) {
		this.questType = questType;

		this.level = level;
		this.experienceReward = experienceReward;
	}

	public abstract String getName();

	public abstract String getDescription();

	public abstract String getCompletedDescription();
	
	public void applySkipQuestEffects() {	
	}
	
	public int getLevel() {
		return level;
	}

	public QuestType getQuestType() {
		return questType;
	}

	public int getExperienceReward() {
		return (int)(experienceReward * 2 * (ModProps.xpMultiplier / 100f));
	}
	
	public static Quest getQuestFromId(String quest) {
		if(quest.equalsIgnoreCase("MAIN_3_A_FINDING_THE_YOUKO")) {
			return Quest.MAIN_3_ELIS;
		}
		if(quest.equalsIgnoreCase("MAIN_3_D_TO_THEMISCRYA")) {
			return Quest.MAIN_3_D_TO_THEMISCYRA;
		}
		if(quest.equalsIgnoreCase("MAIN_3_I_ARION_REPORT")) {
			return Quest.MAIN_3_I_ELIS_DEFENCE_PLAN;
		}
		
		return Quest.valueOf(quest);
	}

}
