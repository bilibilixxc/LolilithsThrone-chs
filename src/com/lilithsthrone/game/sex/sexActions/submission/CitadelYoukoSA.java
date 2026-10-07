package com.lilithsthrone.game.sex.sexActions.submission;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.submission.Takahashi;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.12
 * @version 0.2.12
 * @author Innoxia
 */
public class CitadelYoukoSA {
	
	public static final SexAction ORGASM_DENIED = new SexAction(
			SexActionType.ORGASM_DENIAL,
			ArousalIncrease.NEGATIVE_MAJOR,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "拒绝";
		}

		@Override
		public String getActionDescription() {
			return "正当你快要高潮时，[citadelArcanist.name]突然把你锁住并逼你冷静下来！";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Takahashi.class))
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotGeneric.MISC_WATCHING;
		}

		@Override
		public String getDescription() {
			return "[npc.Name]觉得自己的胯下的温度疯狂上升，即将高潮时，[npc.she]兴奋地大喊着，[npc.speech(哦！快要！快要来了！啊啊啊啊！)]"
						+ "<br/><br/>"
					+ "正听到这，[citadelArcanist.name]发出咯咯的笑声：[citadelArcanist.speechNoEffects(你个笨蛋！我难道没和你说过你不许高潮吗！？)]"
						+ "<br/><br/>"
					+"[npc.name]还没来得及说话，[citadelArcanist.name]用她的奥术将[npc.herHim]困在原地。"
					+ "[npc.Name]拼命地用股间磨蹭着[citadelArcanist.namePos]的腿，但那狡猾的妖狐却只是后撤一步，耐心等待[npc.namePos]的性欲减退。"
					+ "[npc.sheHas]冷静下来后，[citadelArcanist.name]便立刻松开了[npc.herHim]，用小穴压着[npc.her]的脸，取笑道，"
					+ "[citadelArcanist.speechNoEffects(笨蛋！轮到我享受了！)]";
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			}
		}
	};

}
