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
public class SelfPenisMouth {
	
	public static final SexAction SELF_BLOWJOB_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition().isSelfOralAvailable(Main.sex.getCharacterPerformingAction())
                    && (Main.sex.getCharacterPerformingAction().getPenisRawSizeValue()>20
                    || Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.HYPERMOBILITY)
					|| Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_1));
		}
		@Override
		public String getActionTitle() {
			return "开始自我口交";
		}
		@Override
		public String getActionDescription() {
			return "你准备好要用嘴取悦自己了，于是弯下腰来将[npc.cock+]放入自己口中。";
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
							"将[npc.her][npc.lips+]吻上[npc.her][npc.cock]上[npc.cockHead+]。"
									+ "慢慢将[npc.cock]含入口中并开始取悦[npc.herself]，[npc.she]发出含混不清的[npc.moan]。",

							"[npc.her]用[npc.lips+]包裹起[npc.cock]上[npc.cockHead+]。"
									+ "发出一声含混不清的[npc.moan]，[npc.she]用嘴温柔地取悦起[npc.herself]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"将[npc.her][npc.lips+]吻上[npc.her][npc.cock]上[npc.cockHead+]。"
									+ "饥渴难耐地将[npc.her]的[npc.cock]吞入口中，[npc.she]呜咽着含混不清的[npc.moan]并开始给[npc.herself]带来一场愉悦的口交。",

							"[npc.her]用[npc.lips+]包裹起[npc.cock]上[npc.cockHead+]。"
									+ "发出一声含混不清的[npc.moan]，[npc.she]饥渴地用嘴为[npc.herself]带来狂热的欢愉。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"将[npc.her][npc.lips+]吻上[npc.her][npc.cock]上[npc.cockHead+]。"
									+ "极度渴求地将[npc.her]的[npc.cock]吞入口中，[npc.she]呜咽着含混不清的[npc.moan]并开始为[npc.herself]带来一场愉悦的口交。",

							"[npc.her]用[npc.lips+]包裹起[npc.cock]上[npc.cockHead+]。"
									+ "发出一声含混不清的[npc.moan]，[npc.she]贪婪地用嘴给[npc.herself]粗暴地口交起来。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"将[npc.her][npc.lips+]吻上[npc.her][npc.cock]上[npc.cockHead+]。"
									+ "将[npc.cock]含入口中，[npc.she]发出含混不清的[npc.moan]并开始取悦[npc.herself]。",

							"[npc.her]用[npc.lips+]包裹起[npc.cock]上[npc.cockHead+]。"
									+ "发出一声含混不清的[npc.moan]，[npc.she]开始给[npc.herself]进行口交。"));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
	};
	
	// TODO add descriptions of kissing knot, flared head pushing down throat, tentacles, ribs and barbs
	
	public static final SexAction SELF_BLOWJOB_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "深喉";
		}
		@Override
		public String getActionDescription() {
			return "将你[npc.cock+]尽可能地往深处送。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"温柔地用[npc.lips+]包裹住[npc.her]自己的[npc.cock]，[npc.name]把头往前推了推。"
									+ "尽可能地将它深入[npc.her]的喉咙。",

							"随着一声柔软的，含混不清的[npc.moan]，[npc.name]小心翼翼地尽可能将腰弯下，"
									+ "[npc.her]张开[npc.lips+]并尽可能地把[npc.her][npc.cock+]深入[npc.her]的喉咙深处。",

							"慢慢把[npc.her]的脑袋向前滑动，[npc.name]温柔地张开她[npc.lips+]，以便将[npc.her][npc.cock+]深入[npc.her]的喉咙。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"饥渴地用[npc.lips+]把[npc.cock]完全包裹住，[npc.name]将头快速地往里推。"
									+ "贪婪地将它尽可能地吞入[npc.her]的喉咙深处。",

							"随着一声含混不清的[npc.moan]，[npc.name]饥渴地把腰弯到了极限，"
									+ "[npc.her]张开[npc.lips+]并极度渴求着把[npc.her][npc.cock+]深入[npc.her]的喉咙深处。",

							"[npc.name]将脑袋贪婪地往下滑动，欣然张开她[npc.lips+]，将[npc.her][npc.cock+]深入[npc.her]的喉咙。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name][npc.lips+]将自己的[npc.cock]猛然包裹住，[npc.her]把头粗暴地向下推压。"
									+ "迫使它尽可能地深入[npc.her]的喉咙深处。",

							"随着一声含混不清的[npc.moan]，[npc.name]迅速地把腰弯到了极限，"
									+ "[npc.her]张开[npc.lips+]并粗暴地把[npc.her][npc.cock+]尽可能地深入[npc.her]的喉咙深处。",

							"激烈地将[npc.her]的脑袋向下推动，[npc.name]张开[npc.her][npc.lips+]，迫使[npc.her][npc.cock+]深深推入[npc.her]的喉咙。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.lips+]把[npc.her]的[npc.cock]完全包裹住，并把头快速地往里推送。"
									+ "尽可能地将它深入[npc.her]的喉咙。",

							"随着一声含混不清的[npc.moan]，[npc.name]把腰尽可能地弯下来，"
									+ "[npc.her]张开[npc.lips+]并尽可能地把[npc.her][npc.cock+]深入[npc.her]的喉咙深处。",

							"[npc.name]将脑袋往下滑动，张开她[npc.lips+]，将[npc.her][npc.cock+]深入[npc.her]的喉咙。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "自我口交(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "吸吮你[npc.cock+](温柔)。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"温柔地用[npc.lips+]包裹住自己[npc.cock+]后，[npc.name]把头上下移动着，深情地口交自己。",
					"随着一声柔软的，含混不清的[npc.moan]，[npc.name]开始和缓地上下移动[npc.her]的脑袋，"
							+ "当[npc.her]给[npc.herself]口交时，[npc.her]用[npc.lips+]包裹住了[npc.cock+]。",
					"慢慢地把头上下移动，[npc.name]温柔地用[npc.her][npc.lips+]包裹住[npc.her][npc.cock+]，为[npc.herself]口交。"));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "自我口交";
		}
		@Override
		public String getActionDescription() {
			return "饥渴地吸吮你[npc.cock+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"饥渴地用[npc.lips+]包裹住[npc.her]自己[npc.cock+]后，[npc.name]立刻将头上下移动起来，给[npc.herself]进行起了狂热的口交。",
					"随着一声含混不清的[npc.moan]，[npc.name]开始迅速地上下移动起[npc.her]的脑袋，"
							+ "当[npc.her]给[npc.herself]口交时，[npc.her]用贪婪[npc.lips+]一口包住[npc.cock+]。",
					"[npc.name]极度渴求地用[npc.lips+]包裹住[npc.her][npc.cock+]，然后脑袋立刻上下运动起来，给[npc.herself]如饥似渴地口交着。"));
		
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "自我口交(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地吸吮你[npc.cock+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"用力地将[npc.lips+]缠绕上[npc.her][npc.cock+]，[npc.name]将脑袋大幅度地上下移动起来，粗暴地为[npc.herself]口交着。",
					"随着一声含混不清的[npc.moan]，[npc.name]开始激烈地上下移动[npc.her]的脑袋，"
							+ "当[npc.her]给[npc.herself]口交时，[npc.her]粗暴地用[npc.lips+]裹起了[npc.her][npc.cock+]。",
					"粗暴地上下移动脑袋，[npc.name]支配性地用[npc.her][npc.lips+]包裹住[npc.her][npc.cock+]，强制[npc.she]给[npc.herself]口交。"));
		
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "自我口交";
		}
		@Override
		public String getActionDescription() {
			return "继续吸吮你[npc.cock+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"用[npc.lips+]包裹住[npc.her]自己[npc.cock+]后，[npc.name]立刻将头上下移动起来，给[npc.herself]进行口交。",
					"随着一声含混不清的[npc.moan]，[npc.name]开始迅速地上下移动起[npc.her]的脑袋，"
							+ "当[npc.her]给[npc.herself]口交时，[npc.her]用[npc.lips+]包裹住了[npc.cock+]。",
					"[npc.name]用[npc.lips+]包裹住[npc.her][npc.cock+]，然后脑袋立刻上下运动起来，给[npc.herself]进行口交。"));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "自我口交(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "饥渴地吸吮你[npc.cock+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"饥渴地用[npc.lips+]包裹住[npc.her]自己[npc.cock+]后，[npc.name]立刻将头上下移动起来，给[npc.herself]进行起了狂热的口交。",
					"随着一声含混不清的[npc.moan]，[npc.name]开始迅速地上下移动起[npc.her]的脑袋，"
							+ "当[npc.her]给[npc.herself]口交时，[npc.her]用贪婪[npc.lips+]一口包住[npc.cock+]。",
					"[npc.name]极度渴求地用[npc.lips+]包裹住[npc.her][npc.cock+]，然后脑袋立刻上下运动起来，给[npc.herself]如饥似渴地口交着。"));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "停止自我口交";
		}
		@Override
		public String getActionDescription() {
			return "停下来并把你[npc.cock+]从嘴里拔了出来";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"粗暴地将[npc.her][npc.cock+]往喉咙最深处向下一压，[npc.name]接着把头拉了回来，快速地宣告了这次自我愉悦的结束。",

							"把[npc.face]猛然推进[npc.her]自己的根部，[npc.name]迫使[npc.her][npc.cock+]深深推入[npc.her]的喉咙中，"
									+ "然后完全退回，[npc.her]结束了给自己的口交。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将自己[npc.cock+]从口中滑出，发出一阵[npc.a_moan+]，结束了这次自我口交。",

							"[npc.name]发出[npc.a_moan+]，缩回头，把[npc.her][npc.cock+]从嘴里完全滑了出来。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
