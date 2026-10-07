package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.FootType;
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
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class FootMouth {

	// Actions related to someone worshipping feet:
	
	public static final SexAction FOOT_ORAL_GIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "崇拜[npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "将[npc.lips]贴在[npc2.namePos][npc2.feet]上，开始亲吻和舔舐。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			boolean foundFootType = false;
			if(Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.HOOFS)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]急切地低下头，[npc.lips+]按在[npc2.namePos]坚硬的蹄子上，开始热情地亲吻舔舐。",
							"[npc.Name]低头把[npc.lips+]急切地按在[npc2.namePos]坚硬的蹄子上，开始轻吻舔舐。"));
					
				} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TALONS)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]急切地低下头，[npc.lips+]按在[npc2.namePos]的鸟爪上，开始热情地亲吻舔舐。",
							"[npc.Name]低头把[npc.lips+]急切地按在[npc2.namePos]的鸟爪上，开始轻吻舔舐。"));
					
				} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TENTACLE)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]急切地低下头，[npc.lips+]按在[npc2.namePos]的触手上，开始热情地亲吻舔舐。",
							"[npc.Name]低头把[npc.lips+]急切地按在[npc2.namePos]的触手上，开始轻吻舔舐。"));
					
				}
			}
			if(!foundFootType) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]急切地低下头，[npc.lips+]按在[npc2.namePos][npc2.feet+]上，开始热情地亲吻舔舐。",
						"[npc.Name]低头把[npc.lips+]急切地按在[npc2.namePos][npc2.feet+]上，开始轻吻舔舐。"));
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]这样做的时候，[npc2.Name]发出[npc2.a_sob+]，流着泪求[npc.herHim]住手。",
							"[npc2.name]边发出[npc2.a_sob+]边试图挣脱[npc.name]，但无济于事，只能眼睁睁看着[npc.name]宠爱自己的[npc2.feet]，呜咽不已。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"当[npc.name]开始宠爱[npc2.Name]的[npc2.feet]时，[npc2.Name]发出[npc2.a_moan+]，"
									+ "紧接着又急切地把[npc2.feet]往[npc.her][npc.face]上按，鼓励[npc.herHim]继续。",
							"于是，[npc2.name]开始急切地把[npc2.feet]往[npc.namePos][npc.face]上按，"
									+ "因为自己的[npc2.feet(true)]得到宠爱，[npc2.she]感到十分愉快并发出[npc2.a_moan+]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
				case SUB_NORMAL:
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"于是[npc2.Name]开始用[npc2.her][npc2.feet+]急切地在[npc.namePos][npc.face]上来回磨蹭，"
									+ "[npc2.she]感受到自己的[npc2.feet(true)]被疯狂爱上，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
							"[npc2.name]急切地用[npc2.feet+]来回磨蹭[npc.namePos][npc.face+]，一声[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
							"[npc2.name]愉快地呻吟着， 因为知道[npc.namePos]疯狂爱着[npc2.her][npc2.feet+]，就迫不及待地把脚往[npc.namePos]嘴上按。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要挣脱[npc.name]，但没能成功，只得无力地恳求[npc.herHim]放过自己的脚，一边发出[npc2.a_sob+]。",
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试将[npc.namePos]的[npc.face]推离自己的[npc2.feet]。",
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，[npc2.name]无力地尝试将[npc2.feet]远离[npc.namePos]的[npc.face]。"));
					break;
				case DOM_ROUGH:
					boolean foundFootType = false;
					if(Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
						if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.HOOFS)) {
							foundFootType = true;
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name]开始粗暴地在[npc.namePos]的[npc.face]上来回磨蹭[npc2.her][npc2.feet+]作为回应，"
											+ "不在意[npc2.her]如此粗暴地使用硬蹄子会让[npc.herHim]非常不舒服。",
									"[npc2.name][npc2.roughly]用坚硬的蹄子来回磨蹭[npc.namePos][npc.face+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。"
											+ "丝毫不在意[npc.herHim]会有多不舒服。",
									"高兴的[npc2.Moaning]，[npc2.name]饥渴地将[npc2.her]的硬蹄抵住[npc2.namePos]的嘴，"
											+ "肆意嘲弄着[npc.herHim]，[npc2.she]崇拜着[npc2.her][npc2.feet]。"));
							
						} else if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.TALONS)) {
							foundFootType = true;
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name]开始粗暴地在[npc.namePos]的[npc.face]上来回磨蹭[npc2.her][npc2.feet+]作为回应，"
											+ "[npc2.she]毫不在意自己锋利的爪子不停靠近，几乎要割伤[npc.name]。",
									"[npc2.name][npc2.roughly]用鸟一般的爪子来回磨蹭[npc.namePos][npc.face+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。"
											+ "[npc2.she]丝毫不在意自己的爪子太过锋利，几乎要割伤[npc.name]。",
									"高兴地[npc2.Moaning]，[npc2.name]饥渴地将[npc2.her]的像鸟一样的脚爪抵住[npc2.namePos]的嘴，"
											+ "[npc2.she]肆意嘲弄着[npc.herHim]，锋利的爪子不断靠近，几乎快要割伤[npc.name]。"));
							
						}
					}
					if(!foundFootType) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"于是[npc2.Name]开始用[npc2.her][npc2.feet+]急切地在[npc.namePos][npc.face]上来回磨蹭，"
										+ "[npc2.she]感受到自己的[npc2.feet(true)]被疯狂爱上，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
								"[npc2.name]急切地用[npc2.feet+]来回磨蹭[npc.namePos][npc.face+]，一声[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
								"[npc2.name]愉快地呻吟着， 因为知道[npc.namePos]疯狂爱着[npc2.her][npc2.feet+]，就迫不及待地把脚往[npc.namePos]嘴上按。"));
					}
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_ORAL_GIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "崇拜[npc2.feet(true)](温柔)";
		}
		@Override
		public String getActionDescription() {
			return "温柔地亲吻舔舐[npc2.namePos][npc2.feet+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"在[npc2.namePos]的[npc2.feet]上留下一连串温柔地亲吻后，[npc.name]发出一连串轻柔的[npc.moans]声，开始充满爱意地舔舐它们。",
					"[npc.Name]轻轻地亲吻舔舐[npc2.namePos][npc2.toes+]，怜爱地侍奉[npc2.namePos]的[npc2.feet(true)]，发出一声轻微的[npc.moan]。",
					"[npc.Name]把自己[npc.lips+]贴在[npc2.namePos]的[npc2.feet]上，发出一声轻微的[npc.moan]，然后开始充满爱意地亲吻和舔舐。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "崇拜[npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "继续舔吻[npc2.namePos][npc2.feet+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"在[npc2.namePos]的[npc2.feet]上留下一连串狂热地亲吻后，[npc.name]发出一连串的[npc.moans+]，开始热情地舔舐它们。",
					"[npc.name]激情地对着[npc2.namePos][npc2.toes+]又亲又舔，发出[npc.a_moan+]，愉悦地侍奉着[npc2.namePos]的[npc2.feet(true)]。",
					"[npc.Name]把自己[npc.lips+]贴在[npc2.namePos]的[npc2.feet]上，发出[npc.a_moan+]，然后开始急切地亲吻和舔舐。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "崇拜[npc2.feet(true)](粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地亲吻舔舐[npc2.namePos][npc2.feet+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"在[npc2.namePos]的[npc2.feet]上留下一串强势的亲吻后，[npc.name]连声[npc.moans+]，开始粗暴地舔舐它们。",
					"[npc.Name]支配性地崇拜着[npc2.namePos]的[npc2.feet(true)]，在粗暴地亲吻和舔舐[npc2.namePos][npc2.toes+]时发出[npc.a_moan+]。",
					"[npc.Name]把[npc.her][npc.lips+]贴在[npc2.namePos]的[npc2.feet]上，发出了[npc.a_moan+]，然后开始激烈地亲吻和舔舐。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "崇拜[npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "继续吻舔[npc2.namePos][npc2.feet+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"在[npc2.namePos]的[npc2.feet]上留下一连串亲吻后，[npc.name]发出一连串的[npc.moans+]，开始舔舐它们。",
					"[npc.Name]崇拜着[npc2.namePos]的[npc2.feet(true)]，在亲吻和舔舐[npc2.namePos][npc2.toes+]时发出[npc.a_moan+]。",
					"[npc.Name]把[npc.lips+]贴在[npc2.namePos]的[npc2.feet]上，发出[npc.a_moan+]，然后开始亲吻和舔舐。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "崇拜[npc2.feet(true)](渴求)";
		}
		@Override
		public String getActionDescription() {
			return "急切地亲吻舔舐[npc2.namePos][npc2.feet+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"在[npc2.namePos]的[npc2.feet]上留下一连串狂热地亲吻后，[npc.name]发出一连串的[npc.moans+]，开始热情地舔舐它们。",
					"[npc.name]激情地对着[npc2.namePos][npc2.toes+]又亲又舔，发出[npc.a_moan+]，愉悦地侍奉着[npc2.namePos]的[npc2.feet(true)]。",
					"[npc.Name]把[npc.lips+]贴在[npc2.namePos]的[npc2.feet]上，发出了[npc.a_moan+]，然后开始急切地亲吻和舔舐。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "崇拜[npc2.feet(true)](抵抗)";
		}
		@Override
		public String getActionDescription() {
			return "试图让你的[npc.face]远离[npc2.namePos][npc2.feet+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]发出[npc.a_sob+]，绝望地试图将[npc.her][npc.face+]从[npc2.namePos][npc2.feet+]上拉开，但失败了。",
					"[npc.namePos][npc2.lips]间爆发出一阵[npc.a_sob+]，[npc.she]无力地尝试将[npc2.namePos][npc2.feet+]推离自己的[npc.face]。",
					"悲痛地[npc.Sobbing]，[npc.name]虚弱地反抗着，请求[npc2.name]放过[npc2.her]的[npc2.feet]。"));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"完全无视[npc.namePos]的抗议，[npc2.name]温柔而坚定地将[npc2.her]的[npc2.foot]顶在[npc.her]的嘴上，迫使[npc.herHim]继续崇拜[npc2.her][npc2.foot+]。",
							"完全无视[npc.namePos]的抗议，[npc2.name]高兴地轻轻[npc2.moaning]，继续把[npc2.her][npc2.feet+]推向[npc.her]的[npc.face]。",
							"没有理会[npc2.namePos]的挣扎，[npc2.she]把[npc2.her][npc2.foots+]推到[npc.namePos]的嘴上，[npc2.name]发出[npc2.a_moon+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"完全无视[npc.namePos]的抗议，[npc2.name]支配地将[npc2.her]的[npc2.feet]顶在[npc.her]的嘴上，迫使[npc.herHim]继续崇拜[npc2.her][npc2.foot+]。",
							"高兴地[npc2.Moaning+]，[npc2.name]完全无视[npc.namePos]的抗议，继续粗暴地用[npc2.her][npc2.foots+]在[npc.her][npc.face]上摩擦着。",
							"没有理会[npc2.namePos]的挣扎，[npc2.she]激烈地把[npc2.her][npc2.foots+]推到[npc.namePos]的嘴上，[npc2.name]发出[npc2.a_moon+]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"完全无视[npc.namePos]的抗议，[npc2.name]继续坚决地将[npc2.her]的[npc2.feet]顶在[npc.her]的嘴上，迫使[npc.herHim]继续崇拜[npc2.her][npc2.foot+]。",
							"高兴地[npc2.Moaning+]，[npc2.name]完全无视[npc.namePos]的抗议，继续坚决地用[npc2.her][npc2.foots+]在[npc.her][npc.face]上摩擦着。",
							"没有理会[npc2.namePos]的挣扎，[npc2.she]把[npc2.her][npc2.foots+]推到[npc.namePos]的嘴上，[npc2.name]发出[npc2.a_moon+]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止崇拜[npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "把你的[npc.face]从[npc2.namePos]的[npc2.feet]处移开并不再舔它们。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"在[npc2.namePos][npc2.feet+]上种下最后一个粗糙的吻后，[npc.name]抬起了头，结束对[npc.her]的崇拜。",
							"[npc.name]强行舔舐了[npc2.namePos][npc2.toes+]最后一次，然后抽身而退，结束了对[npc2.namePos][npc2.feet+]的崇拜。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"在[npc2.namePos][npc2.feet+]上种下最后一个吻后，[npc.name]抬起了头，结束对[npc.her]的崇拜。",
							"[npc.name]贪婪地舔舐了[npc2.namePos][npc2.toes+]最后一次，然后抽身而退，结束了对[npc2.namePos][npc2.feet+]的崇拜。"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]挪开身体，[npc2.Name]不禁释然地抽噎了一声，然后满怀希望地询问这场磨难是否已经结束。",
							"发出[npc2.a_sob+]，[npc2.name]仍然挣扎着试图摆脱[npc.name]，当[npc2.she]哀求对方放过自己时，眼泪忍不住像小溪一样从[npc2.her]的[npc2.face]上淌下。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"当[npc.name]推开[npc2.her]时，[npc2.Name]发出[npc2.a_moan+]，渴求[npc.her]的更多“照顾”。",
							"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]对[npc.namePos][npc.tongue+]的渴望。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Actions related to someone receiving foot worship:
	
	public static final SexAction FOOT_ORAL_RECEIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "被侍奉[npc.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]低头靠近你的[npc.feet]，开始用嘴侍奉它们。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			boolean foundFootType = false;
			if(Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
				if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.HOOFS)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"随着[npc.a_moon+]，[npc.name]急切地将[npc2.namePos]的头推向[npc.her]的硬蹄，让[npc2.herHim]亲吻它们，开始嘴巴崇拜[npc.her][npc.feet+]。",
							"[npc.name]将硬蹄压向[npc2.namePos]的头部，让[npc2.name]亲吻并用嘴侍奉[npc.her][npc.feet+]，发出了一阵[npc.a_moan+]。"));
					
				} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TALONS)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出[npc.a_moan+], 急切地将[npc2.namePos]的头压向自己鸟状的脚上，让[npc2.herHim]亲吻爪子并侍奉[npc.feet+]。",
							"[npc.Name]将[npc2.namePos]的头推向[npc.her]鸟状的脚，让[npc2.herHim]亲吻爪子并用嘴巴侍奉[npc.her][npc.feet+]，情不自禁发出[npc.moanVerb+]。"));
					
				} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TENTACLE)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出[npc.a_moan+]，急切地将[npc2.namePos]的头推向[npc.her]的触手，让[npc2.namePos]亲吻并用嘴巴侍奉它们。",
							"[npc.Name]将[npc2.namePos]的头推向[npc.her]的触手，让[npc2.name]亲吻并用嘴巴侍奉它们，发出[npc.moanVerb+]。"));
				}
			}
			if(!foundFootType) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]发出[npc.a_moan+]，急切地将[npc2.namePos]的头部推向[npc.her]的[npc.feet]，让[npc2.name]亲吻并开始用嘴巴侍奉它们。",
						"[npc.name]将[npc.feet]压向[npc2.namePos]的头部，让[npc2.name]亲吻并用嘴侍奉它们，发出了一阵[npc.a_moan+]。"));
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]饥渴地将[npc.feet]压向[npc2.namePos]的嘴巴，[npc2.Name]发出[npc2.a_sob+]，绝望地尝试远离。",
								"[npc.Name]急切地将[npc.feet+]压向[npc2.name]的嘴巴，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵[npc2.a_moan+]，饥渴地亲吻并舔舐[npc.namePos]的[npc.feet]。",
								"[npc2.name]发出[npc2.a_moan+]，急切地开始亲吻舔舐[npc.namePos][npc.feet+]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
				case SUB_NORMAL:
				case DOM_GENTLE:
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]急切地舔舐吮吸[npc.namePos][npc.toes+]，饥渴地侍奉[npc.her][npc.feet+]，发出一阵[npc2.a_moan+]。",
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]急切地亲吻舔舐着[npc.namePos][npc.feet+]。",
							"[npc2.name]兴奋地呻吟着，饥渴地捧着[npc.namePos][npc.feet+]，在上面留下了一串虔诚的吻。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.face]从[npc.namePos]的[npc.feet]边移开，[npc2.she]徒劳地挣扎着，发出[npc2.a_sob+]。",
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试将[npc.namePos]的[npc.feet]推离自己的脸。",
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.face]从[npc.namePos][npc.feet+]中抽离。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_ORAL_RECEIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "被侍奉[npc.feet(true)](温柔)";
		}
		@Override
		public String getActionDescription() {
			return "温柔地将你[npc.feet+]按向[npc2.namePos]的[npc2.face]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]温柔地将[npc.her][npc.feet+]推向[npc2.namePos][npc2.face]，被侍奉时发出一阵柔软的[npc.moan]。",
					"随着一阵柔软的[npc.moan]，[npc.Name]温柔地将[npc.her][npc.feet+]推向[npc2.namePos][npc2.lips+]，除了侍奉不给[npc2.herHim]其他选择。",
					"[npc.Name]缓慢地将[npc.her][npc.feet+]推向[npc2.namePos][npc2.face]，被侍奉时不仅发出一阵柔软的[npc.moan]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "被侍奉[npc.Feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "继续将你[npc.feet+]按向[npc2.namePos]的[npc2.face]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]急切地将[npc.her][npc.feet+]推向[npc2.namePos][npc2.face]，被嘴巴侍奉时发出[npc.a_moan+]。",
					"随着一阵[npc.a_moan+]，[npc.Name]急切地将[npc.her][npc.feet+]推向[npc2.namePos][npc2.lips+]，让[npc2.herHim]除了继续侍奉外没有任何选择。",
					"[npc.Name]急切地将[npc.her][npc.feet+]压向[npc2.namePos][npc2.face]，被嘴巴侍奉时不禁发出[npc.a_moan+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "被侍奉[npc.feet(true)](粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地将你[npc.feet+]按向[npc2.namePos]的[npc2.face]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]急切地将[npc.her][npc.feet+]推向[npc2.namePos][npc2.face]，被嘴巴侍奉时发出[npc.a_moan+]。",
					"随着一阵[npc.a_moan+]，[npc.Name]急切地将[npc.her][npc.feet+]推向[npc2.namePos][npc2.lips+]，让[npc2.herHim]除了继续侍奉外没有任何选择。",
					"[npc.Name]急切地将[npc.her][npc.feet+]压向[npc2.namePos][npc2.face]，被嘴巴侍奉时不禁发出[npc.a_moan+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "被侍奉[npc.Feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "继续将你[npc.feet+]按向[npc2.namePos]的[npc2.face]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.feet+]压向[npc2.namePos]的[npc2.face]，让[npc2.name]用嘴侍奉它们，[npc.she]发出了一阵[npc.a_moan+]。",
					"随着一阵[npc.a_moan+]，[npc.Name]将[npc.her][npc.feet+]压向[npc2.namePos][npc2.lips+]，让[npc2.herHim]除了继续侍奉外没有任何选择。",
					"[npc.Name]将[npc.her][npc.feet+]压向[npc2.namePos][npc2.face]，在被侍奉时不禁发出一阵[npc.a_moan+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "被侍奉[npc.feet(true)](渴求)";
		}
		@Override
		public String getActionDescription() {
			return "饥渴地将你[npc.feet+]按向[npc2.namePos]的[npc2.face]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]急切地将[npc.her][npc.feet+]推向[npc2.namePos][npc2.face]，被嘴巴侍奉时发出[npc.a_moan+]。",
					"随着一阵[npc.a_moan+]，[npc.Name]急切地将[npc.her][npc.feet+]推向[npc2.namePos][npc2.lips+]，让[npc2.herHim]除了继续侍奉外没有任何选择。",
					"[npc.Name]急切地将[npc.her][npc.feet+]压向[npc2.namePos][npc2.face]，被嘴巴侍奉时不禁发出[npc.a_moan+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "被侍奉[npc.feet(true)](抵抗)";
		}
		@Override
		public String getActionDescription() {
			return "试图让你[npc.feet+]远离[npc2.namePos][npc2.face+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]发出[npc.a_sob+]，绝望地试图将[npc.her]的[npc.feet]从[npc2.namePos]的[npc2.face]上拉开，但失败了。",
					"[npc.namePos][npc2.lips]间爆发出一阵[npc.a_sob+]，[npc.she]无力地尝试将[npc2.namePos][npc2.face+]推离自己的[npc.feet]。",
					"[npc.Name]在痛苦中[npc.Sobbing]，虚弱地挣扎着，向[npc2.name]乞求把[npc2.her]的嘴从[npc.her][npc.feet]上移开。"));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]完全无视[npc.namePos]的抗议，温柔而坚定地继续侍奉[npc.her][npc.feet+]，在其上留下了一串舔舐和亲吻。",
							"[npc2.name]在愉悦中温柔地[npc2.moaning]，完全忽视[npc.namePos]的抗议，继续嘴巴侍奉[npc.her][npc.feet+]。",
							"[npc2.name]完全不理[npc.namePos]的挣扎，将[npc2.her][npc2.lips+]抵向[npc.namePos][npc.feet+]，发出一阵[npc2.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]完全忽视[npc.namePos]的抗议，继续支配地侍奉[npc.her][npc.feet+]，在其上留下一连串粗糙的舔舐和亲吻。",
							"[npc2.name]在愉悦中[npc2.moaning+]，完全忽视[npc.namePos]的抗议，继续激烈地侍奉[npc.her][npc.feet+]。",
							"[npc2.name]完全不理[npc.namePos]的挣扎，激烈地将[npc2.her][npc2.lips+]压向[npc.namePos][npc.feet+]，发出一阵[npc2.a_moan+]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]完全忽视[npc.namePos]的抗议，继续急切地侍奉[npc.her][npc.feet+]，在其上留下一连串的亲吻和舔舐。",
							"[npc2.name]在愉悦中[npc2.moaning+]，完全忽视[npc.namePos]的抗议，继续嘴巴侍奉[npc.her][npc.feet+]。",
							"[npc2.name]完全不理[npc.namePos]的挣扎，将[npc2.her][npc2.lips+]抵向[npc.namePos][npc.feet+]，发出一阵[npc2.a_moan+]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止被侍奉[npc.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "将你[npc.feet+]抽离[npc2.namePos][npc2.face+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]最后一次粗暴地将[npc.her][npc.feet+]推向[npc2.namePos]的脸后，推开它们并结束了[npc2.her]的嘴巴侍奉。",
						"[npc.Name]在[npc2.namePos]的脸上摩擦[npc.her][npc.feet+]，然后突然将它们移开[npc2.her]的嘴，发出一阵讥笑的[npc.moan]。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]最后一次将[npc.her][npc.feet+]推向[npc2.namePos]的脸后，推开它们并结束了[npc2.her]的嘴巴侍奉。",
						"[npc.Name]最后一次将[npc.feet+]压向[npc2.namePos]的脸，发出了[npc.a_moan+]，然后将它们从[npc2.her]的嘴边挪开。"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]松了一口气，但当[npc2.she]意识到[npc.name]还没满足时，又发出了一阵[npc2.a_sob+]。",
						"[npc2.name]发出一阵[npc2.a_sob+]，继续反抗并挣扎着，但[npc.name]依然牢牢地将[npc2.she]固定在原位。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续亲吻舔舐[npc.namePos]的[npc.feet]的渴望。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

}
