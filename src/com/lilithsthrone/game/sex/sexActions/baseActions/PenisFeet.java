package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.10
 * @version 0.4
 * @author Innoxia
 */
public class PenisFeet {

	// Foot tease
	
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "接受[npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "将你[npc.cock+]插入[npc2.namePos]的[npc2.feet]间，开始操它们。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.HOOFS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name][npc.Eagerly]将[npc2.namePos]的蹄子引向自己的[npc.cock+]的一侧，小心的将脚掌压在[npc.her]的生殖器上，"
										+ "然后将[npc.her][npc.hips]向前推进，开始接受蹄交。",
	
								"[npc.name]把[npc.cockHead+]顶到[npc2.namePos]坚硬的蹄子上，"
										+ "然后小心地将[npc2.her]的脚掌压在一起，并开始接受蹄交。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始用[npc2.name]的足窝，[npc2.Name]发出[npc2.a_sob+]，并且，"
											+ "泪水不停地从[npc2.her]的[npc2.face]上淌下，[npc2.she]撕心裂肺地哀求[npc.herHim]停下。",
		
									"伴随[npc2.a_sob+]，[npc2.name]试着将[npc.name]推开；"
											+ "讨厌的[npc.cock]在[npc2.her]的[npc2.feet]间抽插，眼泪顺着[npc2.her]的[npc2.face]流了下来。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始使用[npc2.Name]的[npc2.feet]，[npc2.Name]发出[npc2.a_moan+]，"
											+ "然后饥渴地在[npc.her][npc.cock+]周围上下滑动。",
		
									"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地在[npc.namePos][npc.cock+]上来回磨蹭[npc2.her]的[npc2.feet]。"));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TALONS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]饥渴地将[npc2.her]鸟一样的脚在[npc.her][npc.cock+]附近蜷曲着，并确保[npc2.her]的尖爪子没有对着[npc.her]的生殖器，"
										+ "然后将[npc.her][npc.hips]向前推进，开始接受[npc2.footjob]。",
	
								"[npc.Name]用[npc2.namePos]脚掌摩擦着[npc.her][npc.cock]的[npc.cockHead+]。"
										+ "然后把[npc2.her]鸟一样的脚放到[npc.her][npc.cock+]上，开始接受[npc2.a_footjob]。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始用[npc2.name]的足窝，[npc2.Name]发出[npc2.a_sob+]，并且，"
											+ "泪水不停地从[npc2.her]的[npc2.face]上淌下，[npc2.she]撕心裂肺地哀求[npc.herHim]停下。",
		
									"伴随[npc2.a_sob+]，[npc2.name]试着将[npc.name]推开；"
											+ "讨厌的[npc.cock]在[npc2.her]的[npc2.feet]间抽插，眼泪顺着[npc2.her]的[npc2.face]流了下来。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始使用[npc2.Name]的[npc2.feet]，[npc2.Name]发出[npc2.a_moan+]，"
											+ "然后饥渴地在[npc.her][npc.cock+]周围上下滑动。",
		
									"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地在[npc.namePos][npc.cock+]上来回磨蹭[npc2.her]的[npc2.feet]。"));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TENTACLE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]急切地并拢[npc2.namePos][npc2.feet+]，将[npc.cock+][npc.cockHead+]压了上去，"
										+ "急切地向前一挺[npc.her]的[npc.hips]，开始操那形成的狭缝。",
										
								"[npc.Name]用[npc2.namePos][npc2.feet+]摩擦着[npc.her][npc.cock]的[npc.cockHead+]。"
										+ "急切地将它们压在一起，开始操那形成的狭缝。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始用[npc2.name]的足窝，[npc2.Name]发出[npc2.a_sob+]，并且，"
											+ "泪水不停地从[npc2.her]的[npc2.face]上淌下，[npc2.she]撕心裂肺地哀求[npc.herHim]停下。",
		
									"伴随[npc2.a_sob+]，[npc2.name]试着将[npc.name]推开；"
											+ "讨厌的[npc.cock]在[npc2.her]的[npc2.feet]间抽插，眼泪顺着[npc2.her]的[npc2.face]流了下来。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始使用[npc2.Name]的[npc2.feet]，[npc2.Name]发出[npc2.a_moan+]，"
											+ "然后饥渴地在[npc.her][npc.cock+]周围上下滑动。",
		
									"[npc2.name]发出[npc2.a_moan+]，开始[npc2.eagerly]在[npc.namePos][npc.cock+]上来回磨蹭[npc2.her]的[npc2.feet]。"));
							break;
					}
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name][npc.Eagerly]将[npc2.namePos][npc2.feet+]并拢，用[npc.her][npc.cock+]的[npc.cockHead+]紧贴在[npc2.her]的脚心上，"
										+ "急切地向前一挺[npc.her]的[npc.hips]，开始操那形成的狭缝。",
	
								"[npc.Name]用[npc2.namePos][npc2.feet+]摩擦着[npc.her][npc.cock]的[npc.cockHead+]。"
										+ "急切地将[npc2.her]的脚掌压在一起，开始操那形成的狭缝。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始用[npc2.name]的足窝，[npc2.Name]发出[npc2.a_sob+]，并且，"
											+ "泪水不停地从[npc2.her]的[npc2.face]上淌下，[npc2.she]撕心裂肺地哀求[npc.herHim]停下。",
		
									"伴随[npc2.a_sob+]，[npc2.name]试着将[npc.name]推开；"
											+ "讨厌的[npc.cock]在[npc2.her]的[npc2.feet]间抽插，眼泪顺着[npc2.her]的[npc2.face]流了下来。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始使用[npc2.Name]的[npc2.feet]，[npc2.Name]发出[npc2.a_moan+]，"
											+ "然后饥渴地在[npc.her][npc.cock+]周围上下滑动。",
		
									"[npc2.name]发出[npc2.a_moan+]，开始[npc2.eagerly]在[npc.namePos][npc.cock+]上来回磨蹭[npc2.her]的[npc2.feet]。"));
							break;
					}
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
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]开始急切地在[npc.namePos][npc.cock+]上来回磨蹭[npc2.her][npc2.feet+]，"
									+ "[npc2.she]热情地给[npc.name]做[npc2.footjob]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.name]急切地用[npc2.feet+]来回磨蹭[npc.namePos][npc.cock+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地在[npc.namePos][npc.cock+]上来回磨蹭[npc2.her][npc2.feet+]，热情地[npc2.footjob]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要逃离[npc.namePos]的[npc.cock]，但没能成功，"
									+ "[npc2.she]发出了一阵[npc2.a_sob+]，无力地乞求着[npc.name]停止使用自己的[npc2.feet]，泪水顺着[npc2.face]流下。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "[npc2.she]恳求[npc.name]放过自己的[npc2.feet]，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
		
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]远离[npc2.her]的[npc2.feet]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]开始在[npc.namePos][npc.cock+]上来回磨蹭[npc2.her][npc2.feet+]，"
									+ "[npc2.she]给[npc.name]做[npc2.footjob]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.name]用[npc2.feet+]来回磨蹭[npc.namePos][npc.cock+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，在[npc.namePos][npc.cock+]上来回磨蹭[npc2.her][npc2.feet+]，提供着[npc2.footjob]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]开始温柔地在[npc.namePos][npc.cock+]上来回磨蹭[npc2.her][npc2.feet+]，"
									+ "[npc2.she]充满爱意地给[npc.herHim]做[npc2.footjob]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.name]温柔地用[npc2.feet+]来回磨蹭[npc.namePos][npc.cock+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，轻轻地在[npc.namePos][npc.cock+]上来回磨蹭[npc2.feet+]，温柔地[npc2.footjob]。"));
					break;
				case DOM_ROUGH:
					if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.HOOFS)) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]开始粗暴地在[npc.namePos][npc.cock+]上来回蹭[npc2.feet]作为回应，"
										+ "毫不在意[npc2.her]如此暴力又坚硬的蹄子会让[npc.herHim]很不舒服。",
			
								"[npc2.name]激烈地用坚硬的蹄子来回磨蹭[npc.namePos][npc.cock+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。"
										+ "丝毫不在意接受如此粗暴的[npc2.footjob]会有多么不舒服。",
			
								"[npc2.name]在愉悦中[npc2.moaning]，粗暴地在[npc.namePos][npc.cock+]周围来回磨蹭自己坚硬的蹄子，"
										+ "[npc2.she]粗暴地强制[npc2.footjob][npc.herHim]，同时肆意嘲弄着[npc.herHim]。"));
						
					} else if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.TALONS)) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]开始粗暴地在[npc.namePos][npc.cock+]上来回蹭[npc2.feet]作为回应，"
										+ "[npc2.she]毫不在意自己锋利的爪子不停靠近，几乎要割伤[npc.name]。",
			
								"[npc2.name]激烈地用鸟一般的爪子来回磨蹭[npc.namePos][npc.cock+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。"
										+ "[npc2.she]丝毫不在意自己的爪子太过锋利，几乎要割伤[npc.name]。",
			
								"[npc2.name]愉悦地[npc2.moaning]着，粗暴地在[npc.namePos][npc.cock+]周围来回磨蹭自己鸟一样的爪子，"
										+ "[npc2.she]肆意嘲弄着[npc.herHim]，锋利的爪子不断靠近，几乎快要割伤[npc.name]。"));
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]开始粗暴地在[npc.namePos][npc.cock+]上来回蹭[npc2.feet+]，"
										+ "[npc2.she]猛烈地给[npc.herHim]做[npc2.footjob]，发出一阵[npc2.a_moan+]。",
			
								"[npc2.name]激烈地用[npc2.feet+]来回磨蹭[npc.namePos][npc.cock+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
			
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地在[npc.namePos][npc.cock+]上来回磨蹭[npc2.feet+]，强势地提供[npc2.footjob]。"));
					}
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc2.footjob](温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地操[npc2.namePos]的[npc2.feet]。";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"在[npc2.namePos]的[npc2.feet]之间温柔地滑动[npc.her][npc.cock+]，"
							+ "[npc.name]开始平稳地前后摇摆[npc.her]的[npc.hips]，缓缓地操[npc2.her][npc2.feet+]，每次推入都会发出一阵轻微的[npc.moan]。",

					"在[npc2.namePos][npc2.feet+]之间温柔地滑动[npc.her][npc.cock+]，"
							+ "[npc.name]开始轻轻地向前挺动[npc.hips]，在温柔地接受[npc2.a_footjob]时发出轻微的[npc.moan]。",

					"[npc.name]轻轻地将[npc2.namePos][npc2.feet+]并拢，发出一声轻微的[npc.moan]，开始温柔地前后摆动[npc.hips]，"
							+ "[npc.she]呼吸着[npc2.namePos]的[npc2.scent]，缓缓地接受[npc2.a_footjob]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc2.footjob](普通)";
		}

		@Override
		public String getActionDescription() {
			return "继续操[npc2.namePos]的[npc2.feet]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.her][npc.cock+]急切地在[npc2.namePos]的[npc2.feet]间滑动，"
							+ "[npc.name]开始竭力地向前挺动[npc.hips]，在贪婪地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.name]饥渴地用[npc.cock+]在[npc2.namePos][npc2.feet+]间滑动，"
							+ "[npc.name]开始疯狂地向前挺动[npc.hips]，在贪婪地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.name]贪婪地合拢[npc2.namePos][npc2.feet+]，并在饥渴地前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
							+ "在[npc.she]积极地接受[npc2.a_footjob]时呼吸着[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc2.footjob](粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地操[npc2.namePos]的[npc2.feet]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"粗暴地用[npc.her][npc.cock+]在[npc2.namePos]的[npc2.feet]之间冲撞，"
							+ "[npc.name]开始支配性地向前挺动[npc.hips]，在激烈地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.her][npc.cock+]激烈地在[npc2.namePos]的[npc2.feet]间滑动，"
							+ "[npc.name]开始猛烈地向前挺动[npc.hips]，在粗暴地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.Name]支配地将[npc2.namePos][npc2.feet+]压在一起，开始前后拍打[npc.her]的[npc.hips]，发出[npc.a_moan+]，"
							+ "在[npc.she]粗暴地接受[npc2.a_footjob]时呼吸着[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc2.footjob](普通)";
		}

		@Override
		public String getActionDescription() {
			return "继续操[npc2.namePos]的[npc2.feet]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.cock+]挤入[npc2.namePos]的[npc2.feet]之间，"
							+ "[npc.name]开始向前挺动[npc.hips]，在贪婪地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.name]将[npc.cock+]挤入[npc2.namePos][npc2.feet+]之间，"
							+ "[npc.name]向前挺[npc.hips]，在开心地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.name]合拢[npc2.namePos][npc2.feet+]，并在前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
							+ "在[npc.she]接受[npc2.a_footjob]时呼吸着[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc2.footjob](渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地操[npc2.namePos]的[npc2.feet]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.her][npc.cock+]急切地在[npc2.namePos]的[npc2.feet]间滑动，"
							+ "[npc.name]开始竭力地向前挺动[npc.hips]，在贪婪地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.name]饥渴地用[npc.cock+]在[npc2.namePos][npc2.feet+]间滑动，"
							+ "[npc.name]开始疯狂地向前挺动[npc.hips]，在贪婪地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.name]贪婪地合拢[npc2.namePos][npc2.feet+]，并在饥渴地前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
							+ "在[npc.she]积极地接受[npc2.a_footjob]时呼吸着[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc2.footjob](抵抗)";
		}

		@Override
		public String getActionDescription() {
			return "试图让你的[npc.cock]远离[npc2.namePos][npc2.feet+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]缓缓将[npc2.her]的[npc2.feet]压向[npc.herHim]，继续温柔地迫使[npc.herHim]接受[npc2.a_footjob]。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "牢牢地将[npc.herHim]固定在原位，"
									+ "强迫[npc.herHim]接受[npc2.a_footjob]，温柔地将[npc2.feet]顶在[npc.her]的腹股沟上。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，请求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.Moaning]着，完全无视了[npc.her]的反抗，"
									+ "强迫[npc.herHim]接受[npc2.a_footjob]，缓缓地顶着[npc.herHim]磨蹭身体。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]猛烈地将[npc2.her]的[npc2.feet]压向[npc.herHim]，继续粗暴地强迫[npc.herHim]接受[npc2.a_footjob]。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "霸道地将[npc.herHim]固定在原位，"
									+ "强迫[npc.herHim]接受[npc2.a_footjob]，粗暴地将[npc2.feet]顶在[npc.her]的腹股沟上。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，哀求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.Moaning]着，完全无视了[npc.her]的反抗，"
									+ "强迫[npc.herHim]接受[npc2.a_footjob]，粗暴地顶着[npc.herHim]磨蹭身体。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]急切地将[npc2.her]的[npc2.feet]压向[npc.herHim]，继续剧烈地强迫[npc.herHim]接受[npc2.a_footjob]。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "牢牢地将[npc.herHim]固定在原位，"
									+ "强迫[npc.herHim]接受[npc2.a_footjob]，急切地将[npc2.feet]顶在[npc.her]的腹股沟上。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，哀求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.Moaning]着，完全无视了[npc.her]的反抗，"
									+ "愉悦地强迫[npc.herHim]接受[npc2.a_footjob]，急切地顶着[npc.herHim]磨蹭身体。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "停止[npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "将你[npc.cock+]从[npc2.namePos]的脚趾缝中拔出。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将[npc.cock+]从[npc2.namePos][npc2.feet+]间拉出，"
									+ "霸道地用[npc.cock]的[npc.cockHead]最后一次上下磨蹭[npc2.her][npc2.toes+]，然后将[npc.hips]移开。",

							"[npc.Name]最后一次深深插入到[npc2.namePos][npc2.feet+]之间，之后后退，结束了[npc2.footjob]。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.cock+]从[npc2.namePos][npc2.feet+]间滑出，"
									+ "[npc.she]最后一次用[npc.her][npc.cock]的[npc.cockHead]拍打[npc2.her][npc2.toes+]，然后收回[npc.hips]。",

							"[npc.Name]最后一次推进[npc2.namePos][npc2.feet+]之间，之后后退，结束了[npc2.footjob]。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.name]拔出的时候，[npc2.Name]忍不住发出一声[npc2.sob+]，"
										+ "[npc2.she]仍然不停地哭泣并反抗着，恳求[npc.name]就这样放过自己。",
	
								"发出[npc2.a_sob+]，[npc2.name]仍然挣扎着试图摆脱[npc.name]，当[npc2.she]哀求对方放过自己时，眼泪忍不住像小溪一样从[npc2.her]的[npc2.face]上淌下。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.name]将[npc.cock+]拔出时，[npc2.Name]发出[npc2.a_moan+]，渴求[npc.name]的更多“照顾”。",
	
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]对[npc.namePos][npc.cock+]的渴望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "将[npc2.namePos][npc2.cock+]夹在你的[npc.feet]中间，开始给[npc2.herHim][npc.a_footjob]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.HOOFS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"随着一阵[npc.a_moan+]，[npc.Name]急切地在[npc2.namePos][npc2.cock+]上上下滑动[npc.her]的硬蹄，"
								+ "然后小心翼翼地把它们按在[npc2.her]的[npc2.cock]两侧，开始给[npc2.herHim][npc.a_footjob]。",

						"[npc.Name]在[npc2.namePos][npc2.cock+]上摩擦[npc.her]的硬蹄，小心地将它们压在一起，"
								+ "在开始给[npc2.herHim][npc.a_footjob]时发出[npc.moaning+]。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]强行用[npc.feet+]窝夹紧[npc2.her]的[npc2.cock]，[npc2.Name]忍不住发出[npc2.a_sob+]，"
											+ "[npc2.she]挣扎着，拼命试着推开[npc.name]。",
	
									"[npc.Name]强行将[npc2.name]的[npc2.cock]深深插入自己的[npc.feet]间，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]急切地向前挺动[npc2.hips]，开始急切地操[npc.her]的[npc.feet]并发出[npc2.a_moan+]。",
	
									"随着一阵[npc2.a_moan+]，[npc2.name]饥渴地向前一挺[npc2.her]的[npc2.hips]，在[npc2.her][npc2.cock+]前滑动[npc.namePos][npc.feet+]。"));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TALONS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]发出[npc.a_moan+]，[npc.eagerly]用爪子略过[npc2.namePos][npc2.cock+]，"
								+ "然后小心翼翼地把它们环绕在[npc2.her]的[npc2.cock]上，开始给[npc2.herHim][npc.a_footjob]。",

						"[npc.Name]在[npc2.namePos][npc2.cock+]上摩擦[npc.her]的鸟爪，小心地包裹着[npc2.her]的阴茎，"
								+ "在开始给[npc2.herHim][npc.a_footjob]时发出[npc.moaning+]。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]强行用[npc.feet+]窝夹紧[npc2.her]的[npc2.cock]，[npc2.Name]忍不住发出[npc2.a_sob+]，"
											+ "[npc2.she]挣扎着，拼命试着推开[npc.name]。",
	
									"[npc.Name]强行将[npc2.name]的[npc2.cock]深深插入自己的[npc.feet]间，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]急切地向前挺动[npc2.hips]，开始急切地操[npc.her]的[npc.feet]并发出[npc2.a_moan+]。",
	
									"随着一阵[npc2.a_moan+]，[npc2.name]饥渴地向前一挺[npc2.her]的[npc2.hips]，在[npc2.her][npc2.cock+]前滑动[npc.namePos][npc.feet+]。"));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TENTACLE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"随着一阵[npc.a_moan+]，[npc.Name]急切地用[npc.her][npc.feet+]略过[npc2.namePos][npc2.cock+]，"
								+ "然后[npc.eagerly]把它们环绕在[npc2.her]的[npc2.cock]上，开始给[npc2.herHim][npc.a_footjob]。",

						"[npc.Name]用[npc.her][npc.feet+]摩擦[npc2.namePos][npc2.cock+]，急切地将它们压在一起，"
								+ "在开始给[npc2.herHim][npc.a_footjob]时发出[npc.moaning+]。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]强行用[npc.feet+]窝夹紧[npc2.her]的[npc2.cock]，[npc2.Name]忍不住发出[npc2.a_sob+]，"
											+ "[npc2.she]挣扎着，拼命试着推开[npc.name]。",
	
									"[npc.Name]强行将[npc2.name]的[npc2.cock]深深插入自己的[npc.feet]间，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]急切地向前挺动[npc2.hips]，开始急切地操[npc.her]的[npc.feet]并发出[npc2.a_moan+]。",
	
									"随着一阵[npc2.a_moan+]，[npc2.name]饥渴地将[npc2.her][npc2.hips]向前一挺，在[npc.namePos][npc.feet+]之前滑动[npc2.her][npc2.cock+]"));
							break;
					}
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"随着一阵[npc.a_moan+]，[npc.Name]急切地用[npc.her][npc.feet+]脚掌略过[npc2.namePos][npc2.cock+]，"
								+ "然后[npc.eagerly]把它们环绕在[npc2.her]的[npc2.cock]上，开始给[npc2.herHim][npc.a_footjob]。",

						"[npc.Name]用[npc.her][npc.feet+]摩擦[npc2.namePos][npc2.cock+]，急切地将它们压在一起，"
								+ "在开始给[npc2.herHim][npc.a_footjob]时发出[npc.moaning+]。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]强行用[npc.feet+]窝夹紧[npc2.her]的[npc2.cock]，[npc2.Name]忍不住发出[npc2.a_sob+]，"
											+ "[npc2.she]挣扎着，拼命试着推开[npc.name]。",
	
									"[npc.Name]强行将[npc2.name]的[npc2.cock]深深插入自己的[npc.feet]间，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]急切地向前挺动[npc2.hips]，开始急切地操[npc.her]的[npc.feet]并发出[npc2.a_moan+]。",
	
									"随着一阵[npc2.a_moan+]，[npc2.name]饥渴地将[npc2.her][npc2.hips]向前一挺，在[npc.namePos][npc.feet+]之前滑动[npc2.her][npc2.cock+]"));
							break;
					}
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
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]贪婪地将自己[npc2.cock+]插到[npc.namePos][npc.feet+]间，"
									+ "[npc2.she]发出[npc2.a_moan+]，热情地接受[npc.a_footjob]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]急切地将[npc2.cock+]在[npc.namePos]的[npc.feet]之间抽插。",
									
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地将[npc2.cock+]插入[npc.namePos][npc.feet+]之间。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.cock]从[npc.namePos]的[npc.feet]间抽出，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.cock+]在自己[npc.feet+]间继续抽插。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.cock]从[npc.namePos][npc.feet+]中抽离。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.cock+]插到[npc.namePos][npc.feet+]间，"
									+ "在[npc2.she]接受[npc.a_footjob]时发出[npc2.a_moan+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将自己[npc2.cock+]在[npc.namePos]的[npc.feet]之间抽插。",
									
							"[npc2.name]愉悦地[npc2.moaning]着，开始将[npc2.cock+]插入[npc.namePos][npc.feet+]之间。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将自己[npc2.cock+]插到[npc.namePos][npc.feet+]间，"
									+ "[npc2.she]发出一声轻柔的[npc2.moan]，开始享受[npc.a_footjob]。",
		
							"[npc2.name]温柔地将[npc2.cock+]挺入[npc.namePos]的[npc.feet]间，口中飘出一声轻柔的[npc2.moan]。",
									
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地将[npc2.cock+]插入[npc.namePos][npc.feet+]之间。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.cock+]插到[npc.namePos][npc.feet+]间，"
									+ "在[npc2.she]粗暴地接受[npc.a_footjob]时发出[npc2.a_moan+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地将[npc2.cock+]在[npc.namePos]的[npc.feet]之间抽插。",
									
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地将[npc2.cock+]插入[npc.namePos][npc.feet+]之间。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob](温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地让[npc2.namePos][npc2.cock+]插你的[npc.feet]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]温柔地在[npc2.namePos][npc2.cock+]周围上下滑动[npc.her]的[npc.feet]，给予[npc2.herHim][npc.a_footjob]，发出一阵柔软的[npc.moan]。",

					"随着一阵柔软的[npc.moan]，[npc.Name]开始温柔地在[npc2.namePos][npc2.cock+]周围上下滑动[npc.her][npc.feet+]。",

					"[npc.Name]缓慢地将[npc.her][npc.feet+]压在[npc2.namePos][npc2.cock+]周围，基于[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.namePos][npc2.cock+]操你的[npc.feet]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]急切地用[npc.feet]夹着[npc2.namePos][npc2.cock+]来回磨蹭，开始为[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，开心地用[npc.feet+]来回磨蹭[npc2.namePos][npc2.cock+]。",

					"[npc.Name]急切地用[npc.feet+]夹着[npc2.namePos][npc2.cock+]来回挤压，开始为[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob](粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地强迫[npc2.namePos][npc2.cock+]在你的[npc.feet]之间来回磨蹭。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]粗暴地用[npc.her][npc.feet+]在[npc2.namePos][npc2.cock+]周围上下滑动，给予[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。",
					"[npc.Name]发出一阵[npc.a_moan+]，激烈地用[npc.feet+]来回磨蹭[npc2.namePos][npc2.cock+]。",
					"[npc.Name]粗暴地将[npc.her][npc.feet+]压在[npc2.namePos][npc2.cock+]周围，给予[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "并拢你[npc.feet+]夹住[npc2.namePos]的[npc2.cock]，给[npc2.herHim][npc.a_footjob]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]用[npc.feet]夹着[npc2.namePos][npc2.cock+]来回磨蹭，开始为[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，开心地用[npc.feet+]来回磨蹭[npc2.namePos][npc2.cock+]。",

					"[npc.Name]用[npc.feet+]夹着[npc2.namePos][npc2.cock+]来回挤压，开始为[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob](渴求)";
		}

		@Override
		public String getActionDescription() {
			return "并拢你[npc.feet+]夹住[npc2.namePos]的[npc2.cock]，热切的给[npc2.herHim][npc.footjob]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]急切地用[npc.feet]夹着[npc2.namePos][npc2.cock+]来回磨蹭，开始为[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，开心地用[npc.feet+]来回磨蹭[npc2.namePos][npc2.cock+]。",

					"[npc.Name]急切地用[npc.feet+]夹着[npc2.namePos][npc2.cock+]来回挤压，开始为[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob](抗拒)";
		}

		@Override
		public String getActionDescription() {
			return "努力让你[npc.feet+]远离[npc2.namePos][npc2.cock+]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"眼泪慢慢涌上[npc.eyes]，再也无法忍住哭意，[npc.Name]一阵阵地啜泣起来，"
									+ "无视这徒劳的反抗，[npc2.namePos]的[npc2.cock]继续温柔地的操着[npc.her]的[npc.feet]。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.feet]从[npc2.namePos]的[npc2.cock]上拉开，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然从容地在[npc.her]的[npc.feet]间继续抽插。",

							"[npc.name]拼命地尝试将[npc.feet]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然温柔地在[npc.her][npc.feet]间抽插。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到泪水涌上自己的眼窝，下一瞬间，[npc.she]开始啜泣起来，"
									+ "无视这徒劳的反抗，[npc2.namePos]的[npc2.cock]继续急切地的操着[npc.her]的[npc.feet]。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.feet]从[npc2.namePos]的[npc2.cock]上拉开，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然急切地在[npc.her]的[npc.feet]间继续抽插。",

							"[npc.name]拼命地尝试将[npc.feet]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然急切地在[npc.her][npc.feet]间抽插。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"眼泪慢慢涌上[npc.eyes]，再也无法忍住哭意，[npc.Name]一阵阵地啜泣起来，"
									+ "无视这徒劳的反抗，[npc2.namePos]的[npc2.cock]继续粗暴地的操着[npc.her]的[npc.feet]。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.feet]从[npc2.namePos]的[npc2.cock]上拉开，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然粗暴地在[npc.her]的[npc.feet]间继续抽插。",

							"[npc.name]拼命地尝试将[npc.feet]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然粗暴地在[npc.her][npc.feet]间抽插。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "停止提供[npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.cock]从你的[npc.feet]之间拔出来。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]的[npc2.cock]从自己的[npc.feet]间抽出，[npc.she]愤怒地咆哮着，命令[npc2.name]停下。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]的[npc2.cock]从自己的[npc.feet]间抽出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]的[npc2.cock]从自己的[npc.feet]间抽出，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]停下。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc2.her]的[npc2.cock]从自己的[npc.feet]间抽出。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]松了一口气，但当[npc2.she]意识到[npc.name]还没满足时，又发出了一阵[npc2.a_sob+]。",
	
								"[npc2.name]发出一阵[npc2.a_sob+]，继续反抗并挣扎着，但[npc.name]依然牢牢地将[npc2.she]固定在原位。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续抽插[npc.namePos][npc.feet]的渴望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	

}
