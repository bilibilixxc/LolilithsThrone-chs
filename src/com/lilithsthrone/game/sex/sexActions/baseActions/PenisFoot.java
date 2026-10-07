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
public class PenisFoot {

	// Foot tease
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "接受[npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]用[npc2.foot]摩擦你的[npc.cock]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.HOOFS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name][npc.Eagerly]将[npc2.namePos]的蹄子引向自己的[npc.cock+]，小心的将脚掌压在[npc.her]的生殖器上，"
										+ "然后将[npc.her][npc.hips]向前推进，开始接受蹄交。",
	
								"[npc.name]将[npc.cock]的[npc.cockHead+]顶到[npc2.namePos]坚硬的蹄子上，"
										+ "然后小心地把蹄掌压上阴茎，接受蹄交。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始用[npc2.name]的[npc2.foot]，[npc2.Name]发出[npc2.a_sob+]，并且，"
											+ "泪水不停地从[npc2.face]上淌下，[npc2.she]撕心裂肺地哀求[npc.herHim]停下。",
		
									"[npc2.name]发出[npc2.a_sob+]，试着将[npc.name]推开；"
											+ "讨厌的[npc.cock]在[npc2.foot]上滑动，眼泪顺着[npc2.face]流了下来。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始使用[npc2.name]的[npc2.foot]，[npc2.Name]发出[npc2.a_moan+]，"
											+"然后饥渴地抚弄[npc.cock+]。",
		
									"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地在[npc.namePos][npc.cock+]上来回磨蹭自己的[npc2.foot]。"));
							break;
					}
				}
					
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TALONS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]急切地将[npc2.her]鸟爪一样的脚在[npc.her][npc.cock+]附近蜷曲伸展着，并确保[npc2.her]的尖爪子没有对着[npc.her]的生殖器，"
										+ "然后将[npc.hips]向前推进，开始接受[npc2.a_footjob]。",
	
								"[npc.name]将[npc.cock]的[npc.cockHead+]在[npc2.namePos]鸟爪一样的脚上摩擦，"
										+ "然后把[npc2.her]的爪子放到[npc.her][npc.cock+]上，开始接受[npc2.a_footjob]。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始用[npc2.name]的[npc2.foot]，[npc2.Name]发出[npc2.a_sob+]，并且，"
											+ "泪水不停地从[npc2.face]上淌下，[npc2.she]撕心裂肺地哀求[npc.herHim]停下。",
		
									"[npc2.name]发出[npc2.a_sob+]，试着将[npc.name]推开；"
											+ "讨厌的[npc.cock]在[npc2.foot]上滑动，眼泪顺着[npc2.face]流了下来。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始使用[npc2.name]的[npc2.foot]，[npc2.Name]发出[npc2.a_moan+]，"
											+ "然后饥渴地抚来弄去[npc.cock+]。",
		
									"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地在[npc.namePos][npc.cock+]上来回磨蹭自己的[npc2.foot]。"));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TENTACLE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name][npc.Eagerly]将[npc2.namePos][npc2.foot+]引向下体，自己[npc.cock+][npc.cockHead+]紧贴其上，"
									+ "然后[npc.eagerly]让[npc2.herHim]用[npc2.foot]摩擦自己的阴茎。",

						"[npc.name]将[npc.cock]的[npc.cockHead+]顶到[npc2.namePos][npc2.foot+]上，"
									+ "然后饥渴地贴住磨蹭[npc.cock+]。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始用[npc2.name]的[npc2.foot]，[npc2.Name]发出[npc2.a_sob+]，并且，"
											+ "泪水不停地从[npc2.face]上淌下，[npc2.she]撕心裂肺地哀求[npc.herHim]停下。",
		
									"[npc2.name]发出[npc2.a_sob+]，试着将[npc.name]推开；"
											+ "讨厌的[npc.cock]在[npc2.foot]上滑动，眼泪顺着[npc2.face]流了下来。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始使用[npc2.name]的[npc2.foot]，[npc2.Name]发出[npc2.a_moan+]，"
											+ "然后饥渴地抚来弄去[npc.cock+]。",
		
									"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地在[npc.namePos][npc.cock+]上来回磨蹭自己的[npc2.foot]。"));
							break;
					}
				}
					
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name][npc.Eagerly]将[npc2.namePos][npc2.foot+]引向自己的下体，[npc.cock+]的[npc.cockHead+]紧贴在脚心上，"
									+ "然后[npc.eagerly]让[npc2.herHim]用[npc2.foot]摩擦自己的阴茎。",

						"[npc.name]将[npc.cock]的[npc.cockHead+]顶到[npc2.namePos][npc2.foot+]上，"
									+ "然后饥渴地贴住[npc2.her]的脚掌上磨蹭[npc.cock+]。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始用[npc2.name]的[npc2.foot]，[npc2.Name]发出[npc2.a_sob+]，并且，"
											+ "泪水不停地从[npc2.face]上淌下，[npc2.she]撕心裂肺地哀求[npc.herHim]停下。",
		
									"[npc2.name]发出[npc2.a_sob+]，试着将[npc.name]推开；"
											+ "讨厌的[npc.cock]在[npc2.foot]上滑动，眼泪顺着[npc2.face]流了下来。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]开始使用[npc2.name]的[npc2.foot]，[npc2.Name]发出[npc2.a_moan+]，"
											+"然后饥渴地抚弄[npc.cock+]。",
		
									"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地在[npc.namePos][npc.cock+]上来回磨蹭自己的[npc2.foot]。"));
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
							"作为回应，[npc2.Name]开始急切地在[npc.namePos][npc.cock+]上来回磨蹭[npc2.foot+]，"
									+ "[npc2.she]热情地给[npc.name]做[npc2.footjob]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.name]迫不及待地用[npc2.foot+]来回磨蹭[npc.namePos][npc.cock+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地在[npc.namePos][npc.cock+]上来回磨蹭自己[npc2.foot+]，热情地提供[npc2.footjob]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要把[npc2.leg]远离[npc.namePos]的[npc.cock]，但没能成功，"
									+ "[npc2.she]发出了一阵[npc2.a_sob+]，无力地乞求着[npc.name]停止使用自己的[npc2.foot]，泪水顺着[npc2.face]流下。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "[npc2.she]恳求[npc.name]放过自己的[npc2.foot]，泪水如小溪般从[npc2.face]上流下。",
		
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]远离[npc2.her]的[npc2.foot]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]开始在[npc.namePos][npc.cock+]上来回磨蹭[npc2.foot+]，"
									+ "[npc2.she]给[npc.name]做[npc2.footjob]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.name]用[npc2.foot+]来回磨蹭[npc.namePos][npc.cock+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，在[npc.namePos][npc.cock+]上来回磨蹭[npc2.her][npc2.feet+]，提供着[npc2.footjob]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]开始温柔地在[npc.namePos][npc.cock+]上来回磨蹭[npc2.foot+]，"
									+ "[npc2.she]充满爱意地给[npc.herHim]做[npc2.footjob]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.name]温柔地用[npc2.foot+]来回磨蹭[npc.namePos][npc.cock+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，轻轻地在[npc.namePos][npc.cock+]上来回磨蹭[npc2.foot+]，温柔地[npc2.footjob]。"));
					break;
				case DOM_ROUGH:
					if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.HOOFS)) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]开始粗暴地在[npc.namePos][npc.cock+]上来回磨蹭自己的[npc2.foot]作为回应，"
										+ "毫不在意[npc2.her]如此暴力又坚硬的蹄子会让自己很不舒服。",
			
								"[npc2.name]激烈地用坚硬的蹄子来回磨蹭[npc.namePos][npc.cock+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。"
										+ "丝毫不在意接受如此粗暴的[npc2.footjob]会有多么不舒服。",
			
								"[npc2.name]愉悦地[npc2.moaning]着，粗暴地在[npc.namePos][npc.cock+]上来回磨蹭自己坚硬的蹄子，"
										+ "[npc2.she]粗暴地强制[npc2.footjob][npc.herHim]，同时肆意嘲弄着[npc.herHim]。"));
						
					} else if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.TALONS)) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]开始粗暴地在[npc.namePos][npc.cock+]上来回磨蹭自己的[npc2.foot]作为回应，"
										+ "[npc2.she]毫不在意自己锋利的爪子不停靠近，几乎要割伤[npc.name]。",
			
								"[npc2.name]激烈地用鸟一般的爪子来回磨蹭[npc.namePos][npc.cock+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出，"
										+ "[npc2.she]丝毫不在意自己的爪子太过锋利，几乎要割伤[npc.name]。",
			
								"[npc2.name]愉悦地[npc2.moaning]着，粗暴地在[npc.namePos][npc.cock+]上来回磨蹭自己鸟一样的爪子，"
										+ "[npc2.she]肆意嘲弄着[npc.herHim]，锋利的爪子不断靠近，几乎快要割伤[npc.name]。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]开始粗暴地在[npc.namePos][npc.cock+]上来回磨蹭[npc2.foot+]，"
										+ "[npc2.she]猛烈地给[npc.herHim]做[npc2.footjob]，发出一阵[npc2.a_moan+]。",
			
								"[npc2.name]激烈地用[npc2.foot+]来回磨蹭[npc.namePos][npc.cock+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
			
								"[npc2.name]愉悦地[npc2.moaning]着，粗暴地在[npc.namePos][npc.cock+]上来回磨蹭自己[npc2.foot+]，强势地提供[npc2.footjob]。"));
					}
					
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc2.footjob](温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地接受[npc2.name]的[npc2.a_footjob]";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"在[npc2.namePos][npc2.foot]上温柔地来回磨蹭[npc.cock+]，"
							+ "[npc.name]开始平稳地前后摇摆[npc.her]的[npc.hips]，缓缓地操[npc2.her][npc2.feet+]，每次推入都会发出一阵轻微的[npc.moan]。",

					"用[npc2.namePos][npc2.foot+]温柔地来回磨蹭[npc.cock+]，"
							+ "[npc.name]开始轻轻地向前挺动[npc.hips]，在温柔地接受[npc2.a_footjob]时发出轻微的[npc.moan]。",

					"轻轻地将[npc.her][npc.cock+]顶在[npc2.namePos][npc2.foot+]上，[npc.name]发出一声轻微的[npc.moan]，开始温柔地前后摆动[npc.hips]，"
							+ "在[npc.she]缓缓地接受[npc2.a_footjob]时呼吸着[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc2.footjob](普通)";
		}

		@Override
		public String getActionDescription() {
			return "继续接受[npc2.name]的[npc2.a_footjob]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"急切地将自己[npc.cock+]在[npc2.namePos][npc2.foot+]上来回磨蹭，"
							+ "[npc.name]开始竭力地向前挺动[npc.hips]，在贪婪地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.name]急切地将[npc.cock+]在[npc2.namePos][npc2.foot+]上来回磨蹭，"
							+ "[npc.name]开始疯狂地向前挺动[npc.hips]，在贪婪地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.name]贪婪地将[npc.cock+]顶在[npc2.namePos][npc2.foot+]上，发出一阵[npc.a_moan+]，开始拼命地前后摆动[npc.hips]，"
							+ "在[npc.she]积极地接受[npc2.a_footjob]时呼吸着[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc2.footjob](粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地接受着[npc2.name]的[npc2.a_footjob].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"对着[npc2.namePos]的[npc2.foot]粗暴地抽插[npc.her][npc.cock+]，"
							+ "[npc.name]开始支配性地向前挺动[npc.hips]，在激烈地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"强硬地将自己[npc.cock+]在[npc2.namePos][npc2.foot+]上来回磨蹭，"
							+ "[npc.name]开始猛烈地向前挺动[npc.hips]，在粗暴地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"支配性地将自己[npc.cock+]顶在[npc2.namePos][npc2.foot+]上，[npc.name]发出一声[npc.a_moan+]，开始猛烈地前后摆动[npc.hips]，"
							+ "在[npc.she]粗暴地接受[npc2.a_footjob]时呼吸着[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc2.footjob](普通)";
		}

		@Override
		public String getActionDescription() {
			return "继续接受[npc2.name]的[npc2.a_footjob]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.cock+]在[npc2.namePos]的[npc2.foot]上来回磨蹭，"
							+ "[npc.name]开始向前挺动[npc.hips]，在贪婪地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.name]将[npc.cock+]在[npc2.namePos][npc2.foot+]上来回磨蹭，"
							+ "[npc.name]向前挺动[npc.hips]，在开心地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.name]将[npc.cock+]顶在[npc2.namePos][npc2.foot+]上，发出一声[npc.a_moan+]，开始前后摆动[npc.hips]，"
							+ "在[npc.she]接受[npc2.a_footjob]时呼吸着[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc2.footjob](渴求)";
		}

		@Override
		public String getActionDescription() {
			return "急切地接受着[npc2.name]的[npc2.a_footjob].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"急切地将自己[npc.cock+]在[npc2.namePos][npc2.foot+]上来回磨蹭，"
							+ "[npc.name]开始大力地向前挺动[npc.hips]，在贪婪地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.name]急切地将[npc.cock+]在[npc2.namePos][npc2.foot+]上来回磨蹭，"
							+ "[npc.name]开始疯狂地向前挺动[npc.hips]，在贪婪地接受[npc2.a_footjob]时发出[npc.a_moan+]。",

					"[npc.name]贪婪地将[npc.cock+]顶在[npc2.namePos][npc2.foot+]上，发出一阵[npc.a_moan+]，开始拼命地前后摆动[npc.hips]，"
							+ "在[npc.she]积极地接受[npc2.a_footjob]时呼吸着[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "[npc2.footjob](抵抗)";
		}

		@Override
		public String getActionDescription() {
			return "试图让你的[npc.cock]远离[npc2.namePos][npc2.foot+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]缓慢而坚定地将[npc2.foot]下压踩在[npc.her][npc.cock+]上。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "牢牢地将[npc.herHim]固定在原位，"
									+ "温柔地把[npc2.foot]下压踩在[npc.her][npc.cock+]上。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，请求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.moaning]着，完全无视[npc.her]的抗议，缓缓地将自己的[npc2.foot]下压踩在[npc.her][npc.cock+]上。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]粗暴地用[npc2.foot]下压踩踏[npc.her][npc.cock+]。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "霸道地将[npc.herHim]固定在原位，"
									+ "粗暴地把[npc2.foot]下压踩在[npc.her][npc.cock+]上。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，哀求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.moaning]着，完全无视[npc.her]的抗议，粗暴地将自己的[npc2.foot]下压踩在[npc.her][npc.cock+]上。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]急切地用[npc2.foot]下压踩踏[npc.her][npc.cock+]。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "牢牢地将[npc.herHim]固定在原位，"
									+ "急切地把[npc2.foot]下压踩在[npc.her][npc.cock+]上。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，哀求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.moaning]着，完全无视[npc.her]的抗议，急切地将自己的[npc2.foot]下压踩在[npc.her][npc.cock+]上。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "停止[npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "将你[npc.cock+]抽离[npc2.namePos]的[npc2.foot]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将[npc.cock+]从[npc2.namePos][npc2.foot+]上拉开，"
									+ "霸道地用[npc.cock]的[npc.cockHead]最后一次上下磨蹭[npc2.her][npc2.toes+]，然后将[npc.hips]移开。",

							"作为[npc2.footjob]的结尾，[npc.name]最后一次饥渴地摩擦着[npc2.namePos][npc2.foot+]，之后才将对方推开。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.cock+]滑离[npc2.namePos][npc2.foot+]，"
									+ "[npc.she]最后一次用[npc.her][npc.cock]的[npc.cockHead]拍打[npc2.her][npc2.toes+]，然后收回[npc.hips]。",

							"作为[npc2.footjob]的结尾，[npc.name]最后一次推向[npc2.namePos][npc2.foot+]，之后才将对方推开。"));
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
	
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "将你的[npc.foot]按在[npc2.namePos][npc2.cock+]，开始给[npc2.herHim][npc.a_footjob]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.HOOFS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]发出[npc.a_moan+]，[npc.eagerly]用坚硬的蹄子踩向[npc2.namePos][npc2.cock+]，"
								+"然后仔细而[npc.eagerly]地在[npc2.her]柱体上来回撸动。",

						"[npc.name]将自己坚硬的蹄子下移至[npc2.namePos]腹股沟处，[npc.eagerly]按在[npc2.her][npc2.cock+]上，"
								+ "在开始给[npc2.herHim][npc.a_footjob]时发出[npc.moaning+]。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]强迫[npc2.her]的[npc2.cock]插进自己[npc.foot]间，[npc2.Name]忍不住发出[npc2.a_sob+]，"
											+ "[npc2.she]挣扎着，拼命试着推开[npc.name]。",
	
									"[npc.Name]强行将[npc.foot]压向[npc2.name]的[npc2.cock]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]急切地向前挺动[npc2.hips]，开始急切地磨蹭[npc.her]的[npc.foot]并发出[npc2.a_moan+]。",
	
									"[npc2.name]一边发出[npc2.a_moan+]，一边急切地将[npc2.hips]撞向对方，把[npc2.cock+]深深埋入[npc.namePos][npc.foot+]间。"));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TALONS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]发出[npc.a_moan+]，[npc.eagerly]用爪子踩向[npc2.namePos][npc2.cock+]，"
								+ "然后小心翼翼地将[npc.her]的爪子环绕在[npc2.her]的[npc2.cock]上，开始给[npc2.herHim][npc.a_footjob]。",

						"[npc.Name]把鸟爪伸到[npc2.namePos]的腹股沟处，[npc.eagerly]用爪子抓住[npc2.her][npc2.cock+]，"
								+ "在开始给[npc2.herHim][npc.a_footjob]时发出[npc.moaning+]。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]强迫[npc2.her]的[npc2.cock]插进自己[npc.foot]间，[npc2.Name]忍不住发出[npc2.a_sob+]，"
											+ "[npc2.she]挣扎着，拼命试着推开[npc.name]。",
	
									"[npc.Name]强行将[npc.foot]压向[npc2.name]的[npc2.cock]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]急切地向前挺动[npc2.hips]，开始急切地磨蹭[npc.her]的[npc.foot]并发出[npc2.a_moan+]。",
	
									"[npc2.name]一边发出[npc2.a_moan+]，一边急切地将[npc2.hips]撞向对方，把[npc2.cock+]深深埋入[npc.namePos][npc.foot+]间。"));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TENTACLE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]发出[npc.a_moan+]，[npc.eagerly]用[npc.foot+]踩向[npc2.namePos][npc2.cock+]，"
								+ "然后[npc.eagerly]地在[npc2.her]柱体上来回撸动。",

						"[npc.name]将自己[npc.foot+]下移至[npc2.namePos]腹股沟处，[npc.eagerly]按在[npc2.her][npc2.cock+]上，"
								+ "在开始给[npc2.herHim][npc.a_footjob]时发出[npc.moaning+]。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]强迫[npc2.her]的[npc2.cock]插进自己[npc.foot]间，[npc2.Name]忍不住发出[npc2.a_sob+]，"
											+ "[npc2.she]挣扎着，拼命试着推开[npc.name]。",
	
									"[npc.Name]强行将[npc.foot]压向[npc2.name]的[npc2.cock]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]急切地向前挺动[npc2.hips]，开始急切地磨蹭[npc.her]的[npc.foot]并发出[npc2.a_moan+]。",
	
									"[npc2.name]一边发出[npc2.a_moan+]，一边急切地将[npc2.hips]撞向对方，把[npc2.cock+]深深埋入[npc.namePos][npc.foot+]间。"));
							break;
					}
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]发出[npc.a_moan+]，[npc.eagerly]用[npc.foot+]踩向[npc2.namePos][npc2.cock+]，"
								+ "然后[npc.eagerly]地用脚掌在[npc2.her]柱体上来回撸动。",

						"[npc.Name]把[npc.foot+]伸到[npc2.namePos]的腹股沟处，[npc.eagerly]用脚心抵住[npc2.her][npc2.cock+]，"
								+ "在开始给[npc2.herHim][npc.a_footjob]时发出[npc.moaning+]。"));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc.name]强迫[npc2.her]的[npc2.cock]插进自己[npc.foot]间，[npc2.Name]忍不住发出[npc2.a_sob+]，"
											+ "[npc2.she]挣扎着，拼命试着推开[npc.name]。",
	
									"[npc.Name]强行将[npc.foot]压向[npc2.name]的[npc2.cock]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]急切地向前挺动[npc2.hips]，开始急切地磨蹭[npc.her]的[npc.foot]并发出[npc2.a_moan+]。",
	
									"[npc2.name]一边发出[npc2.a_moan+]，一边急切地将[npc2.hips]撞向对方，把[npc2.cock+]深深埋入[npc.namePos][npc.foot+]间。"));
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
							"[npc2.Name]贪婪地将自己[npc2.cock+]推向[npc.namePos][npc.foot+]，"
									+ "[npc2.she]发出[npc2.a_moan+]，热情地接受[npc.a_footjob]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]急切地将[npc2.cock+]顶在[npc.namePos]的[npc.foot]上。",
									
							"[npc2.name]喜悦地[npc2.moaning]着，急切地将自己[npc2.cock+]顶在[npc.namePos][npc.foot+]上。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.cock]从[npc.namePos]的[npc.foot]间抽出，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.cock+]在自己[npc.foot+]中继续抽插。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.cock]从[npc.namePos][npc.foot+]中抽离。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将自己[npc2.cock+]推向[npc.namePos][npc.foot+]，"
									+ "在[npc2.she]接受[npc.a_footjob]时发出[npc2.a_moan+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将自己[npc2.cock+]顶在[npc.namePos]的[npc.foot]上。",
									
							"[npc2.name]喜悦地[npc2.moaning]着，将自己[npc2.cock+]顶在[npc.namePos][npc.foot+]上。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将自己[npc2.cock+]推向[npc.namePos][npc.foot+]，"
									+ "[npc2.she]发出一声轻柔的[npc2.moan]，开始享受[npc.a_footjob]。",
		
							"[npc2.name]温柔地将[npc2.cock+]挺入[npc.namePos]的[npc.foot]间，口中飘出轻柔的[npc2.moan]。",
									
							"[npc2.name]喜悦地[npc2.moaning]着，温柔地将自己[npc2.cock+]顶在[npc.namePos][npc.foot+]上。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.cock+]推向[npc.namePos][npc.foot+]，"
									+ "在[npc2.she]粗暴地接受[npc.a_footjob]时发出[npc2.a_moan+]。",
		
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地将[npc2.cock+]顶在[npc.namePos]的[npc.foot]上。",
									
							"[npc2.name]喜悦地[npc2.moaning]着，粗暴地将自己[npc2.cock+]顶在[npc.namePos][npc.foot+]上。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob](温柔)";
		}

		@Override
		public String getActionDescription() {
			return "将你的[npc.foot]温柔地抵在[npc2.namePos][npc2.cock+]上。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]温柔地用[npc.foot]上下摩擦[npc2.namePos][npc2.cock+]，发出一阵轻柔的[npc.moan]，开始为[npc2.herHim][npc.a_footjob]。",

					"[npc.name]发出一声轻柔的[npc.moan]，温柔地用[npc.foot+]来回磨蹭[npc2.namePos][npc2.cock+]。",

					"[npc.Name]发出[npc.a_moan+]，慢慢地用[npc.foot+]踩向[npc2.namePos][npc2.cock+]，开始为[npc2.herHim]提供[npc.a_footjob]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "将你的[npc.foot]抵在[npc2.namePos][npc2.cock+]上。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]急切地用[npc.foot]来回磨蹭[npc2.namePos][npc2.cock+]，开始为[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，愉悦地用[npc.foot+]来回磨蹭[npc2.namePos][npc2.cock+]。",

					"[npc.Name]急切地用[npc.foot+]压向[npc2.namePos][npc2.cock+]，开始为[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob](粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地将你的[npc.foot]压在[npc2.namePos][npc2.cock+]上踩踏。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]发出[npc.a_moan+]，粗暴地用[npc.foot]上下摩擦[npc2.namePos][npc2.cock+]，开始为[npc2.herHim][npc.a_footjob]。",
					"[npc.Name]发出一阵[npc.a_moan+]，激烈地用[npc.foot+]来回磨蹭[npc2.namePos][npc2.cock+]。",
					"[npc.Name]发出[npc.a_moan+]，粗暴地用[npc.foot+]踩向[npc2.namePos][npc2.cock+]，开始为[npc2.herHim][npc.a_footjob]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "将你的[npc.foot]抵在[npc2.namePos][npc2.cock+]上。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]用[npc.foot]来回磨蹭[npc2.namePos][npc2.cock+]，开始为[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，愉悦地用[npc.foot+]来回磨蹭[npc2.namePos][npc2.cock+]。",

					"[npc.Name]用[npc.foot+]压向[npc2.namePos][npc2.cock+]，开始为[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob](渴求)";
		}

		@Override
		public String getActionDescription() {
			return "急切地将你的[npc.foot]踩在[npc2.namePos][npc2.cock+]上。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]急切地用[npc.foot]来回磨蹭[npc2.namePos][npc2.cock+]，开始为[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，愉悦地用[npc.foot+]来回磨蹭[npc2.namePos][npc2.cock+]。",

					"[npc.Name]急切地用[npc.foot+]压向[npc2.namePos][npc2.cock+]，开始为[npc2.herHim][npc.a_footjob]，发出一阵[npc.a_moan+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供[npc.footjob](抗拒)";
		}

		@Override
		public String getActionDescription() {
			return "尝试将你[npc.foot+]抽离[npc2.namePos][npc2.cock+]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"眼泪慢慢涌上[npc.eyes]，再也无法忍住哭意，[npc.Name]一阵阵地啜泣起来，"
									+ "无视这徒劳的反抗，[npc2.namePos]的[npc2.cock]继续温柔地的操着[npc.foot]。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.foot]从[npc2.namePos]的[npc2.cock]上拉开，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然从容地操着[npc.her]的[npc.foot]。",

							"[npc.name]拼命地尝试将[npc.foot]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然温柔地磨蹭着[npc.her]的[npc.foot]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到泪水涌上自己的眼窝，下一瞬间，[npc.she]开始啜泣起来，"
									+ "无视这徒劳的反抗，[npc2.namePos]的[npc2.cock]继续急切地的操着[npc.foot]。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.foot]从[npc2.namePos]的[npc2.cock]上拉开，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然急切地操着[npc.her]的[npc.foot]。",

							"[npc.name]拼命地尝试将[npc.foot]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然急切地磨蹭着[npc.her]的[npc.foot]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"眼泪慢慢涌上[npc.eyes]，再也无法忍住哭意，[npc.Name]一阵阵地啜泣起来，"
									+ "无视这徒劳的反抗，[npc2.namePos]的[npc2.cock]继续粗暴地的操着[npc.foot]。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.foot]从[npc2.namePos]的[npc2.cock]上拉开，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然粗暴地操着[npc.her]的[npc.foot]。",

							"[npc.name]拼命地尝试将[npc.foot]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然粗暴地磨蹭着[npc.her]的[npc.foot]。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "停止提供[npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.cock]从你的[npc.foot]旁边拿开。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]发出一声气势汹汹的咆哮，把[npc.foot]从[npc2.namePos][npc2.cock+]上移开，并命令[npc2.herHim]不要再碰自己的[npc.feet]。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后迅速地将[npc.foot]远离[npc2.her][npc2.cock+]。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]发出一声[npc.a_moan+]，把[npc.foot]从[npc2.namePos][npc2.cock+]上移开，并告诉[npc2.herHim]不要再碰自己的[npc.feet]。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc.foot]远离[npc2.her][npc2.cock+]。"));
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
								"[npc2.namePos][npc2.lips+]间漏出[npc2.A_moan+]，暴露了[npc2.she]还想继续抽插[npc.feet]的渴望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	

}
