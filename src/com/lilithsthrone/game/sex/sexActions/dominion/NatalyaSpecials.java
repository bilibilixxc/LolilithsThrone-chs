package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class NatalyaSpecials {

	public static final SexAction PARTNER_DEMAND_FACIAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP
						|| (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.DOMINION_PARK && !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)))
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDemandedFacial)
					&& Main.game.getNpc(Natalya.class).getArousal()>=ArousalLevel.FOUR_PASSIONATE.getMinimumValue();
		}
		@Override
		public String getActionTitle() {
			return "要求颜射";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public String getDescription() {
			return "[natalya.cockGirth]且兽性十足的鸡巴开始被你的[pc.hands]疯狂撸动，娜塔莉亚的呼吸越来越急促沉重，她疯狂地用一只前蹄拍打地面并大声叫喊，"
					+ "[natalya.speechNoEffects(啊~！~哦！~我快去了！~啊啊！~作为奖励，你就……~哦！~ ……你就用脸接下这整整一发吧！~姆嗯！~)]";
		}
		@Override
		public void applyEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDemandedFacial, true);
		}
	};
	
	public static final SexAction PLAYER_PREPARE_FACIAL = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP
						|| (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.DOMINION_PARK && !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "准备([style.colourMinorGood(颜射)])";
		}
		@Override
		public String getActionDescription() {
			return "[style.colourMinorGood(听从娜塔莉亚的命令)]，将[pc.face]摆在她马肉棒的正前方，接受一场淋漓尽致的颜射。";
		}
		@Override
		public String getDescription() {
			return "娜塔莉亚淫靡地呻吟一声，喝道，[natalya.speechNoEffects(啊~！我快要……~哦哦！~……快要射了！听我的命令，让我弄在你脸上！)]"
					+ "<br/><br/>"
					+ "那[natalya.race][natalya.cockGirth]的鸡巴在你的[pc.hands]里猛烈地颤抖着，你于是照做，挪到了肉棒前方。"
					+ "你继续揉弄着主人那散发着淫味的滚烫肉棒，将先走液涂遍了各处，同时用面部直直地对准了那平坦的龟头，准备好接受你的奖励……";
		}
		@Override
		public void applyEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.playerReceivedNatalyaFacial, true);
		}
		@Override
		public int getActionRenderingPriority() {
			return 1;
		}
	};
	
	public static final SexAction PLAYER_PREPARE_NO_FACIAL = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP
						|| (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.DOMINION_PARK && !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "准备([style.colourMinorBad(地面)])";
		}
		@Override
		public String getActionDescription() {
			return "你[style.colourMinorBad(违抗了娜塔莉亚的命令)]，并没有接受颜射，而是把她的鸡巴歪向一旁，让她射在地上。";
		}
		@Override
		public String getDescription() {
			return "娜塔莉亚淫靡地呻吟一声，喝道，[natalya.speechNoEffects(奴隶！我快要……~哦哦！~……快要射了！听我的命令，让我泻在你脸上！)]"
					+ "<br/><br/>"
					+ "那[natalya.race][natalya.cockGirth]的鸡巴在你的[pc.hands]里猛烈地颤抖着，你并不准备照做，反而躲到了一旁。"
					+ "你继续揉弄着主人那散发着淫味的滚烫肉棒，将先走液涂遍了各处，把那平坦的龟头偏向了一旁，让她泻在地上……";
		}
		@Override
		public void applyEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.playerReceivedNatalyaFacial, false);
		}
	};
}
