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
public class SelfTongueAnus {

	public static final SexAction SELF_TONGUE_ANUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition().isSelfOralAvailable(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getCharacterPerformingAction().getTongueLengthValue()>30
                    && !this.isForbiddenArea(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)
                    || Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.HYPERMOBILITY)
					|| Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_1));
		}
		@Override
		public String getActionTitle() {
			return "开始自舔肛门";
		}
		@Override
		public String getActionDescription() {
			return "滑动自己的[npc.tongue]插入自己的[npc.ass+]，开始对自己进行舔肛。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]展现出令人惊叹的柔韧性，弯下身体，",
					"[npc.name]炫耀着自己是多么身手矫健，把身体对折过去，",
					"[npc.name]考验着自己的柔韧性，弯下身体，"));

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(press)] 把[npc.her] [npc.lips+]紧贴在自己[npc.ass]上。"
							+ " 在[npc.her] [npc.asshole+]上落下一连串轻柔的亲吻，同时[npc.she]缓慢而坚定地把[npc.her] [npc.tongue+]滑入[npc.her] [npc.asshole+]中。",

							"[npc.verb(Plant)] 一连串轻柔的亲吻在[npc.her] [npc.asshole+]上面。"
							+ " [npc.She]随后对着[npc.her] [npc.ass+]来了一记又长又湿的舔舐，接着温柔地把[npc.her] [npc.tongue+]探入深处。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"急切地[npc.verb(press)] 把[npc.her] [npc.lips+]紧贴在自己[npc.ass]上。"
							+ " 在[npc.her] [npc.asshole+]上落下一连串热烈的亲吻，同时[npc.she]不顾一切地把[npc.her] [npc.tongue+]滑入[npc.her] [npc.asshole+]中。",

							"[npc.verb(Plant)] 一连串热烈的亲吻在[npc.her] [npc.asshole+]上面。"
							+ " [npc.She]随后对着[npc.her] [npc.ass+]来了一记饥渴的舔舐，接着贪婪地把[npc.her] [npc.tongue+]探入深处。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"粗暴地[npc.verb(grind)] 着把[npc.her] [npc.lips+]紧贴在自己[npc.ass]上。"
							+ " 在[npc.her] [npc.asshole+]上落下一连串用力的亲吻，同时[npc.she]贪婪地把[npc.her] [npc.tongue+]滑入[npc.her] [npc.asshole+]中。",

							"[npc.verb(Plant)] 一连串用力的亲吻在[npc.her] [npc.asshole+]上面。"
							+ " [npc.She]随后对着[npc.her] [npc.ass+]来了一记粗暴的舔舐，接着贪婪地把[npc.her] [npc.tongue+]探入深处。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"急切地[npc.verb(press)] 把[npc.her] [npc.lips+]紧贴在自己[npc.ass]上。"
							+ " 在[npc.her] [npc.asshole+]上落下一连串热烈的亲吻，同时[npc.she]满怀激情地把[npc.her] [npc.tongue+]滑入[npc.her] [npc.asshole+]中。",

							"[npc.verb(Plant)] 一连串热烈的亲吻在[npc.her] [npc.asshole+]上面。"
							+ " [npc.She]随后对着[npc.her] [npc.ass+]来了一记饥渴的舔舐，接着贪婪地把[npc.her] [npc.tongue+]探入深处。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(press)] 把[npc.her] [npc.lips+]紧贴在自己[npc.ass]上。"
							+ " 在[npc.her] [npc.asshole+]上落下一连串亲吻，同时[npc.she][npc.verb(slide)] 把[npc.her] [npc.tongue+]滑入[npc.her] [npc.asshole+]中。",

							"[npc.verb(Plant)] 一连串亲吻在[npc.her] [npc.asshole+]上面。"
							+ " [npc.She]随后对着[npc.her] [npc.ass+]来了一记湿润的舔舐，接着把[npc.her] [npc.tongue+]探入深处。"));
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_ANUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "温柔地自舔肛门";
		}
		@Override
		public String getActionDescription() {
			return "温柔地舔弄自己的[npc.ass+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]温柔地把[npc.her] [npc.tongue+]尽可能深地顶入[npc.her] [npc.asshole+]中，"
							+ " 把[npc.her] [npc.lips+]紧贴在[npc.her] [npc.ass+]上，并发出一声闷住的[npc.moan]。",
					"[npc.name]把[npc.her] [npc.tongue+]从[npc.her] [npc.asshole+]中抽出来，先温柔地亲吻并舔弄[npc.her] [npc.asshole+]，"
							+ " 随后重新向前，把[npc.her] [npc.tongue]缓慢地滑入[npc.her] [npc.asshole+]中。",
					"[npc.name]把[npc.her] [npc.tongue+]从[npc.her] [npc.asshole+]中抽出来，开始慢慢地亲吻并蹭弄[npc.her] [npc.asshole+]，"
							+ " 接着再一次温柔地把[npc.her] [npc.tongue]深深顶入[npc.her] [npc.asshole+]中。"));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_ANUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "自舔肛门";
		}
		@Override
		public String getActionDescription() {
			return "继续把[npc.tongue]顶入自己的[npc.ass+]中。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地把[npc.her] [npc.tongue+]尽可能深地顶入[npc.her] [npc.asshole+]中，"
							+ " 把[npc.her] [npc.lips+]紧贴在[npc.her] [npc.ass+]上，并发出一声闷住的[npc.moan]。",
					"[npc.name]把[npc.her] [npc.tongue+]从[npc.her] [npc.asshole+]中抽出来，先急切地亲吻并舔弄[npc.her] [npc.asshole+]，"
							+ " 随后重新向前，贪婪地把[npc.her] [npc.tongue]滑入[npc.her] [npc.asshole+]中。",
					"[npc.name]把[npc.her] [npc.tongue+]从[npc.her] [npc.asshole+]中抽出来，开始开心地亲吻并蹭弄[npc.her] [npc.asshole+]，"
							+ " 接着再一次热情地把[npc.her] [npc.tongue]深深顶入[npc.her] [npc.asshole+]中。"));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_ANUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "粗暴地自舔肛门";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地把舌头顶入自己[npc.ass+]中。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]粗暴地把[npc.her] [npc.tongue+]尽可能深地顶入[npc.her] [npc.asshole+]中，"
							+ " 把[npc.her] [npc.lips+]碾磨般紧贴在[npc.her] [npc.ass+]上，并发出一声闷住的[npc.moan]。",
					"[npc.name]把[npc.her] [npc.tongue+]从[npc.her] [npc.asshole+]中抽出来，先粗暴地亲吻并舔弄[npc.her] [npc.asshole+]，"
							+ " 随后重新向前，猛烈地把[npc.her] [npc.tongue]顶入[npc.her] [npc.asshole+]中。",
					"[npc.name]把[npc.her] [npc.tongue+]从[npc.her] [npc.asshole+]中抽出来，开始用力地亲吻并舔弄[npc.her] [npc.asshole+]，"
							+ " 接着再一次粗暴地把[npc.her] [npc.tongue]深深顶入[npc.her] [npc.asshole+]中。"));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_ANUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "自舔肛门";
		}
		@Override
		public String getActionDescription() {
			return "继续把[npc.tongue]顶入自己的[npc.ass+]中。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]把[npc.her] [npc.tongue+]尽可能深地顶入[npc.her] [npc.asshole+]中，"
							+ " 把[npc.her] [npc.lips+]紧贴在[npc.her] [npc.ass+]上，并发出一声闷住的[npc.moan]。",
					"[npc.name]把[npc.her] [npc.tongue+]从[npc.her] [npc.asshole+]中抽出来，先亲吻并舔弄[npc.her] [npc.asshole+]，"
							+ " 随后重新向前，把[npc.her] [npc.tongue]滑入[npc.her] [npc.asshole+]中。",
					"[npc.name]把[npc.her] [npc.tongue+]从[npc.her] [npc.asshole+]中抽出来，开始亲吻并蹭弄[npc.her] [npc.asshole+]，"
							+ " 接着再一次把[npc.her] [npc.tongue]深深顶入[npc.her] [npc.asshole+]中。"));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_ANUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "渴望地自舔肛门";
		}
		@Override
		public String getActionDescription() {
			return "渴望地把[npc.tongue]顶入自己的[npc.ass+]中。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地把[npc.her] [npc.tongue+]尽可能深地顶入[npc.her] [npc.asshole+]中，"
							+ " 把[npc.her] [npc.lips+]紧贴在[npc.her] [npc.ass+]上，并发出一声闷住的[npc.moan]。",
					"[npc.name]把[npc.her] [npc.tongue+]从[npc.her] [npc.asshole+]中抽出来，先急切地亲吻并舔弄[npc.her] [npc.asshole+]，"
							+ " 随后重新向前，贪婪地把[npc.her] [npc.tongue]滑入[npc.her] [npc.asshole+]中。",
					"[npc.name]把[npc.her] [npc.tongue+]从[npc.her] [npc.asshole+]中抽出来，开始开心地亲吻并蹭弄[npc.her] [npc.asshole+]，"
							+ " 接着再一次热情地把[npc.her] [npc.tongue]深深顶入[npc.her] [npc.asshole+]中。"));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_TONGUE_ANUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "停止自舔肛门";
		}
		@Override
		public String getActionDescription() {
			return "把[npc.tongue]从自己的[npc.ass+]中抽出来，停止自舔肛门。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]最后来了一记粗暴的舔舐，便把[npc.her] [npc.face]从[npc.her] [npc.ass+]旁移开。",

							"[npc.name]给[npc.her] [npc.asshole+]最后一记粗暴的亲吻，然后把[npc.her] [npc.face]从[npc.her] [npc.ass+]旁移开。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]最后又来了一记舔舐，便把[npc.her] [npc.face]从[npc.her] [npc.ass+]旁移开。",

							"[npc.name]给[npc.her] [npc.asshole+]最后一记湿润的亲吻，然后把[npc.her] [npc.face]从[npc.her] [npc.ass+]旁移开。"));
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
	};

}