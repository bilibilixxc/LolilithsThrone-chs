package com.lilithsthrone.LolificationProject.Archer.sex;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.8.5
 * @version 0.4.8.5
 */
public class SelfClitMouth {

	public static final SexAction SELF_CLIT_MOUTH_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return super.isBaseRequirementsMet()
                    && Main.sex.getPosition().isSelfOralAvailable(Main.sex.getCharacterPerformingAction())
                    && Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis()
                    && !this.isForbiddenArea(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)
                    && Main.sex.getCharacterPerformingAction().getVaginaRawClitorisSizeValue()>20
                    || (Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.HYPERMOBILITY)
                    || Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_1));
		}

		@Override
		public String getActionTitle() {
			return "开始吸吮自己的阴蒂";
		}

		@Override
		public String getActionDescription() {
			return "弯下身，开始取悦[npc.herself]，将[npc.her][npc.clit+]含入自己的嘴里。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]展现出惊人的柔韧性弯下腰，将[npc.her][npc.clit+]的[npc.clitTip+]对准自己的嘴巴并[npc.verb(press)]，然后慢慢地将其含入嘴里。",
					"[npc.Name][npc.verb(bend)]下身体，展现出惊人的柔韧性，带着愉悦发出一声[npc.moaning]，将[npc.her][npc.lips+]贴在[npc.her][npc.clit+]顶端，[npc.verb(slide)]入嘴中深处，然后[npc.verb(start)]为[npc.herself]服务。",
					"[npc.Name]考验着自己的柔韧性，弯下身将[npc.her]悸动的[npc.clit+]引导进[npc.her][npc.lips+]之间，随着[npc.she]开始吸吮而发出一声[npc.a_moan+]。"));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction DOM_SELF_CLIT_MOUTH_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {

		@Override
		public String getActionTitle() {
			return "温柔地自口淫";
		}

		@Override
		public String getActionDescription() {
			return "温柔地为[npc.herself]服务。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]温柔地在[npc.her][npc.clit+]上起伏摆动，吸吮着[npc.her]自己的[npc.clitTip+]，发出一声含糊的[npc.moan]。",
					"[npc.Name]慢慢地将[npc.her][npc.clit+]在嘴里进进出出，随着[npc.she]温柔地吸吮自己[npc.clitTip+]而发出一连串愉悦的、含糊的[npc.moans]。",
					"[npc.Name]俯下身轻轻取悦[npc.herself]，用舌头绕着[npc.clitTip+]打转，然后再次将其深深含入嘴里。");
		}
	};

	public static final SexAction DOM_SELF_CLIT_MOUTH_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {

		@Override
		public String getActionTitle() {
			return "自口淫";
		}

		@Override
		public String getActionDescription() {
			return "继续为[npc.herself]服务。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]急切地在[npc.her][npc.clit+]上起伏摆动，吸吮着[npc.her]自己的[npc.clitTip+]，发出一声含糊的[npc.moan]。",
					"[npc.Name]将[npc.her][npc.clit+]在嘴里进进出出，随着[npc.she]吸吮自己[npc.clitTip+]而发出一连串愉悦的、含糊的[npc.moans]。",
					"[npc.Name]俯下身取悦[npc.herself]，用舌头绕着[npc.clitTip+]打转，然后再次将其深深含入嘴里。");
		}
	};

	public static final SexAction DOM_SELF_CLIT_MOUTH_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "粗暴地自口淫";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地为[npc.herself]服务。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]粗暴地在[npc.her][npc.clit+]上起伏摆动，吸吮着[npc.her]自己的[npc.clitTip+]，发出一声含糊的[npc.moan]。",
					"[npc.Name]猛烈地将[npc.her][npc.clit+]在嘴里进进出出，随着[npc.she]贪婪地吸吮自己[npc.clitTip+]而发出一连串绝望的、含糊的[npc.moans]。",
					"[npc.Name]猛地将[npc.herself]扑向[npc.her][npc.clit+]，用舌头绕着[npc.clitTip+]打转，然后用力将其深深捅入喉咙。");
		}
	};

	public static final SexAction SUB_SELF_CLIT_MOUTH_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return "自口淫";
		}

		@Override
		public String getActionDescription() {
			return "继续为[npc.herself]服务。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]在[npc.her][npc.clit+]上起伏摆动，吸吮着[npc.her]自己的[npc.clitTip+]，发出一声含糊的[npc.moan]。",
					"[npc.Name]将[npc.her][npc.clit+]在嘴里进进出出，随着[npc.she]吸吮自己[npc.clitTip+]而发出一连串愉悦的、含糊的[npc.moans]。",
					"[npc.Name]俯下身取悦[npc.herself]，用舌头绕着[npc.clitTip+]打转，然后再次将其深深含入嘴里。");
		}
	};

	public static final SexAction SUB_SELF_CLIT_MOUTH_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "渴望地自口淫";
		}

		@Override
		public String getActionDescription() {
			return "渴望地为[npc.herself]服务。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]急切地在[npc.her][npc.clit+]上起伏摆动，吸吮着[npc.her]自己的[npc.clitTip+]，发出一声绝望的、含糊的[npc.moan]。",
					"[npc.Name]不顾一切地将[npc.her][npc.clit+]在嘴里进进出出，随着[npc.she]贪婪地吸吮自己[npc.clitTip+]而发出一连串愉悦的、含糊的[npc.moans]。",
					"[npc.Name]将[npc.herself]向[npc.her][npc.clit+]顶去，用舌头绕着[npc.clitTip+]打转，然后拼命将其深深捅入喉咙。");
		}
	};

	public static final SexAction SELF_CLIT_MOUTH_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "停止自口淫";
		}

		@Override
		public String getActionDescription() {
			return "把[npc.her][npc.clit+]从嘴里抽出，停止为[npc.herself]服务。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]最后舔了一下，便把[npc.her][npc.face]从[npc.her][npc.clit+]旁移开，在将[npc.her][npc.clit+]从嘴里抽出的同时发出一声[npc.a_moan+]。",
					"[npc.Name]给[npc.her][npc.clitTip+]最后一记湿润的亲吻，然后把脸从[npc.her][npc.clit+]旁移开。");
		}
	};
}