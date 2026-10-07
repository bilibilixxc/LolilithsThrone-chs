package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.EnforcerAlleywayDialogue;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.8.3
 * @version 0.3.8.3
 * @author Innoxia
 */
public class EnforcerPatrolSA {

	public static final SexAction DEMON_TF_REACTION = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())
					&& !EnforcerAlleywayDialogue.isDemonRevealed()
					&& Main.game.getPlayer().getRace()==Race.DEMON;
		}
		@Override
		public String getActionTitle() {
			return "对恶魔作出反应";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getDescription() {
			return "[npc.name]看到你转化成恶魔形态，震惊地喘息与尖叫，"
					+ "[npc.speechNoExtraEffects(你是个恶魔？！很抱歉，[pc.maam]，我、我之前不知道！)]"
					+ "<br/><br/>"
					+ "反过来抓住了[npc.race]，你呻吟着，[pc.speech(来嘛，别停下！继续把我当成一个肮脏弱小的人类一样对待啊！)]"
					+ "<br/><br/>"
					+ "[npc.sheIs]按你所说的做，继续对你进行“腔体搜查”，[npc.moaning]，[npc.speech(如果这是你想要的话，那也行……)]";
		}
		@Override
		public void applyEffects() {
			EnforcerAlleywayDialogue.setDemonRevealed(true);
		}
	};
}
