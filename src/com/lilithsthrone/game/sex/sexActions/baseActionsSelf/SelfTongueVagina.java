package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

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
 * @since 0.1.79
 * @version 0.4.8.5
 * @author Innoxia
 */
public class SelfTongueVagina {

	public static final SexAction SELF_CUNNILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition().isSelfOralAvailable(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isPenetrationNonSelfOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS) // cannot autocunnilingus if have a penis and using it
					&& (Main.sex.getCharacterPerformingAction().getTongueLengthValue()>20
                    || Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.HYPERMOBILITY)
                    || Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_1));
		}
		@Override
		public String getActionTitle() {
			return "开始自我舔阴";
		}
		@Override
		public String getActionDescription() {
			return "把你的[npc.tongue]伸进你[npc.pussy+]里，开始舔自己的阴部。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]拥有令人惊叹的柔韧性，[npc.she]将身体弯下",
					"[npc.name]炫耀着自己的身体有多么柔软，[npc.she]将身体几乎对折般弯下腰",
					"[npc.name]测试着自己身体的柔韧性，[npc.she]将腰慢慢弯下"));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.lips+]压向自己的[npc.pussy]。"
							+ "在[npc.her][npc.labia+]上留下一连串轻柔的吻，[npc.she]缓慢而坚定地将[npc.tongue+]伸进[npc.her][npc.pussy+]里。",

							"在[npc.her][npc.labia+]上留下一连串轻柔的吻。"
							+ "[npc.She]绵长而湿润地舔吮着[npc.pussy+]，随后温柔地将[npc.tongue+]向深处推进。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"急切地将[npc.lips+]压向自己的[npc.pussy]。"
							+ "在[npc.her][npc.labia+]上留下一连串深情的吻，[npc.she]饥渴地将[npc.tongue+]伸进[npc.her][npc.pussy+]。",

							"在[npc.her][npc.labia+]上留下一连串深情的吻。"
							+ "[npc.She]饥渴地舔吮着[npc.pussy+]，随后温柔地将[npc.tongue+]向深处推进。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"粗暴地用[npc.lips+]磨蹭着自己的[npc.pussy]。"
							+ "在[npc.her][npc.labia+]上留下一连串有力的吻，[npc.she]贪婪地将[npc.tongue+]伸进[npc.her][npc.pussy+]。",

							"在[npc.her][npc.labia+]上留下一连串有力的吻。"
							+ "[npc.She]粗鲁地舔吮着[npc.pussy+]，随后贪婪地将[npc.tongue+]向深处推进。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"急切地将[npc.lips+]压向自己的[npc.pussy]。"
							+ "在[npc.her][npc.labia+]上留下一连串深情的吻，[npc.she]饥渴地将[npc.tongue+]伸进[npc.her][npc.pussy+]。",

							"在[npc.her][npc.labia+]上留下一连串深情的吻。"
							+ "[npc.She]饥渴地舔吮着[npc.pussy+]，随后温柔地将[npc.tongue+]向深处推进。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.lips+]压向自己的[npc.pussy]。"
							+ "在[npc.her][npc.labia+]上留下一连串吻，[npc.she]将[npc.tongue+]伸进[npc.her][npc.pussy+]。",

							"在[npc.her][npc.labia+]上留下一连串吻。"
							+ "[npc.She]湿润地舔吮着[npc.pussy+]，随后将[npc.tongue+]向深处推进。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "自我舔阴(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "温柔地舔你[npc.pussy+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"温柔地用[npc.tongue+]尽可能深入自己[npc.pussy+]，"
							+ "[npc.name]将[npc.her][npc.lips+]压向自己[npc.labia+]，并发出了一声低沉的[npc.moan]。",
					"将[npc.tongue+]从自己[npc.pussy+]中收回，[npc.name]开始温柔地舔吻着[npc.her][npc.labia+]，"
							+ "随后[npc.her][npc.tongue]再次缓慢地滑入[npc.her][npc.pussy+]。",
					"[npc.name]将[npc.tongue+]从自己[npc.pussy+]中抽出，开始慢慢地亲吻摩擦自己[npc.labia+]，"
							+ "随后再次温柔地将[npc.tongue]深入自己[npc.pussy+]。"));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "自我舔阴";
		}
		@Override
		public String getActionDescription() {
			return "继续在[npc.pussy+]里抽送你的[npc.tongue]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"急切地用[npc.tongue+]尽可能深入自己[npc.pussy+]，"
							+ "[npc.name]将[npc.her][npc.lips+]压向自己[npc.labia+]，并发出了一声低沉的[npc.moan]。",
					"[npc.name]将[npc.tongue+]从自己[npc.pussy+]中收回，开始急切地舔吻着自己[npc.labia+]，"
							+ "随后贪婪地将[npc.tongue]再次滑入自己[npc.pussy+]中。",
					"[npc.name]将[npc.tongue+]从自己[npc.pussy+]中抽出，开始开心地亲吻摩擦自己[npc.labia+]，"
							+ "随后再次亢奋地将[npc.tongue]深入自己[npc.pussy+]。"));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "自我舔阴(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地在[npc.pussy+]里抽送你的舌头。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"粗暴地用[npc.tongue+]尽可能深入自己[npc.pussy+]，"
							+ "[npc.name]用[npc.her][npc.lips+]摩擦自己[npc.labia+]，并发出了一声低沉的[npc.moan]。",
					"将[npc.tongue+]从自己[npc.pussy+]中收回，[npc.name]开始粗暴地舔吻着[npc.her][npc.labia+]，"
							+ "随后[npc.tongue]再次粗暴地滑入[npc.pussy+]中。",
					"将[npc.tongue+]从自己[npc.pussy+]中抽出，[npc.name]开始激烈地亲吻摩擦[npc.her][npc.labia+]，"
							+ "随后再次粗暴地用[npc.tongue]深入自己[npc.pussy+]。"));
		
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "自我舔阴";
		}
		@Override
		public String getActionDescription() {
			return "继续在[npc.pussy+]里抽送你的[npc.tongue]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"用[npc.tongue+]尽可能深入自己[npc.pussy+]，"
							+ "[npc.name]将[npc.her][npc.lips+]压向自己[npc.labia+]，并发出了一声低沉的[npc.moan]。",
					"将[npc.tongue+]从自己[npc.pussy+]中收回，[npc.name]开始舔吻着[npc.her][npc.labia+]，"
							+ "随后[npc.tongue]再次滑入自己[npc.pussy+]中。",
					"[npc.name]将[npc.tongue+]从自己[npc.pussy+]中抽出，开始亲吻摩擦自己[npc.labia+]，"
							+ "随后再次用[npc.tongue]深入自己[npc.pussy+]。"));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "自我舔阴(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "饥渴地[npc.pussy+]里抽送你的[npc.tongue]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"急切地用[npc.tongue+]尽可能深入自己[npc.pussy+]，"
							+ "[npc.name]将[npc.her][npc.lips+]压向自己[npc.labia+]，并发出了一声低沉的[npc.moan]。",
					"[npc.name]将[npc.tongue+]从自己[npc.pussy+]中收回，开始急切地舔吻着自己[npc.labia+]，"
							+ "随后贪婪地将[npc.tongue]再次滑入自己[npc.pussy+]中。",
					"[npc.name]将[npc.tongue+]从自己[npc.pussy+]中抽出，开始开心地亲吻摩擦自己[npc.labia+]，"
							+ "随后再次亢奋地将[npc.tongue]深入自己[npc.pussy+]。"));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "停止自我舔阴";
		}
		@Override
		public String getActionDescription() {
			return "把你的[npc.tongue]从[npc.pussy+]里缩回来，停止了自我舔阴。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"以一个粗暴的舔舐作为结束，随后[npc.name]把[npc.her]的[npc.face]从自己[npc.pussy+]旁移开了。",
	
							"给了[npc.her][npc.labia+]一个粗暴的亲吻作为结束，[npc.name]将[npc.her]的[npc.face]从自己[npc.pussy+]旁移开了。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"以一个舔舐作为结束，随后[npc.name]把[npc.her]的[npc.face]从自己[npc.pussy+]旁移开了。",
	
							"给了[npc.her][npc.labia+]一个湿润的亲吻作为结束，[npc.name]将[npc.her]的[npc.face]从自己[npc.pussy+]旁移开了。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
