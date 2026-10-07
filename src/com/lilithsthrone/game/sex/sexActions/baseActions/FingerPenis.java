package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class FingerPenis {
	
	private static boolean isCharacterPrecumming(GameCharacter character) {
		return character.getArousal() >= character.getPenisCumStorage().getArousalNeededToStartPreCumming();
	}
	
	//TODO grope cock
	
	public static final SexAction FONDLE_BALLS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isInternalTesticles()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public String getActionTitle() {
			return "爱抚蛋蛋";
		}
		@Override
		public String getActionDescription() {
			return "爱抚并且戏耍[npc2.namePos][npc2.balls+]。";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name]将[npc.herself]支在[npc.arm]上，准备用[npc.her]空闲的[npc.hand]来揉搓与挤压[npc2.namePos][npc2.balls+]。",
						"[npc.Name]发出[npc.a_moan+]，用一只[npc.arm]支撑起自己，然后伸手开始抚摸和玩弄[npc2.namePos][npc2.balls+]。",
						"[npc.Name]用一只[npc.arm]撑起自己，[npc.her][npc.fingers+]放在[npc2.namePos]腹股沟上，咧嘴笑着，[npc.she]开始抚摸捧起[npc2.namePos][npc2.balls+]。",
						"[npc.Name]用一只[npc.arm]撑起自己，另一只[npc.hand]开始抚摸和玩弄[npc2.namePos][npc2.balls+]，"
								+"[npc.moaning]着，为[npc2.namePos][npc2.cock+]的跳动兴奋不已。");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name]把手放到[npc2.namePos][npc2.legs]间，不停揉搓挤压着[npc2.her][npc2.balls+]。",
						"[npc2.Name]看着自己[npc2.balls+]被[npc.Name]用[npc.hand]不停玩弄，发出满足而[npc2.a_moan+]。",
						"[npc.Name]将[npc.her][npc.fingers+]放在[npc2.namePos][npc2.balls+]上，开始抚摸并握住它们，随着[npc2.namePos][npc2.cock+]的抽动发出[npc.a_moan+]。");
			}
		}
	};
	

	public static final SexAction COCK_MASTURBATING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public String getActionTitle() {
			return "开始手交";
		}
		@Override
		public String getActionDescription() {
			return "向下摸索，开始抚摸[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]探到[npc2.namePos][npc2.legs]间，[npc.fingers]绕着[npc2.her][npc2.cock+]打圈，"
									+ "[npc.she]发出轻柔的[npc.moan]，缓缓地在柱身上来回套弄。",

							"[npc.Name]将[npc.hands]探到[npc2.namePos][npc2.legs]间，"
									+ "抓住[npc2.namePos][npc2.cock+]，开始缓慢地为[npc2.herHim]打飞机。",

							"[npc.Name]用[npc.fingers]挑逗着[npc2.namePos][npc2.cock+]，温柔地在抽动的柱身上来回套弄，发出一声柔和的[npc.moan]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]探到[npc2.namePos][npc2.legs]间，[npc.fingers]渴欲地绕着[npc2.her][npc2.cock+]打圈，"
									+ "[npc.she]发出[npc.a_moan+]，快速撸动着柱身。",

							"[npc.Name]将[npc.hands]探到[npc2.namePos][npc2.legs]间，"
									+ "抓住[npc2.namePos][npc2.cock+]，开始急切地为[npc2.herHim]打飞机。",

							"[npc.Name]用[npc.fingers]挑逗着[npc2.namePos][npc2.cock+]，迅速地在抽动的柱身上来回套弄，发出[npc.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]探到[npc2.namePos][npc2.legs]间，[npc.fingers]强行绕着[npc2.her][npc2.cock+]打圈，"
									+ "[npc.she]发出[npc.a_moan+]，用力地撸动着柱身。",

							"[npc.Name]将[npc.hands]探到[npc2.namePos][npc2.legs]间，"
									+ "粗鲁地抓住[npc2.namePos][npc2.cock+]，开始激烈地为[npc2.herHim]打飞机。",

							"[npc.Name]发出[npc.a_moan+]，用力地上下移动[npc.fingers]，粗鲁地撸动着[npc2.namePos]抽动的[npc2.cock+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]探到[npc2.namePos][npc2.legs]间，[npc.fingers]渴欲地绕着[npc2.her][npc2.cock+]打圈，"
									+ "[npc.she]发出[npc.a_moan+]，快速撸动着柱身。",

							"[npc.Name]将[npc.hands]探到[npc2.namePos][npc2.legs]间，"
									+ "抓住[npc2.namePos][npc2.cock+]，开始急切地为[npc2.herHim]打飞机。",

							"[npc.Name]用[npc.fingers]挑逗着[npc2.namePos][npc2.cock+]，迅速地在抽动的柱身上来回套弄，发出[npc.a_moan+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]探到[npc2.namePos][npc2.legs]间，[npc.fingers]绕着[npc2.her][npc2.cock+]打圈，"
									+ "[npc.she]发出[npc.a_moan+]，在柱身上来回套弄。",

							"[npc.Name]将[npc.hands]探到[npc2.namePos][npc2.legs]间，"
									+ "抓住[npc2.namePos][npc2.cock+]，开始为[npc2.herHim]打飞机。",

							"[npc.Name]用[npc.fingers]挑逗着[npc2.namePos][npc2.cock+]，开始在抽动的柱身上来回套弄，发出[npc.a_moan+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]轻轻呻吟着，温柔地摆动[npc2.hips]摩擦着[npc.namePos]的手掌。"
										+"感受着自己[npc2.cock+]在[npc.her]手中不断泵动。",
	
								"[npc2.name]轻柔地[npc2.moan]，开始缓慢地将[npc2.hips]压向[npc.name]，让[npc.she]触摸，"
										+ "享受[npc.her][npc.fingers+]在自己[npc2.cock+]上下撸动的快感。",
	
								"[npc2.Name]慢慢摆动[npc2.hips]，摩擦着[npc.namePos]的[npc.hand]，"
										+"[npc2.she][npc2.moaning]着，感受手指在[npc2.cock+]上轻轻划过的感觉。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]，饥渴地摆动[npc2.hips]摩擦着[npc.namePos]的[npc.hand]，"
										+"感受着自己[npc2.cock+]在[npc.her]手中不断泵动。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地将[npc2.hips]顶向[npc.name]，让[npc.she]触摸，"
										+ "享受[npc.her][npc.fingers+]在自己[npc2.cock+]上下撸动的快感。",
								
								"[npc2.Name]饥渴地摆动[npc2.hips]，摩擦着[npc.namePos]的[npc.hand]，"
										+"[npc2.she][npc2.moaning]着，感受手指在[npc2.cock+]上用力擦过的感觉。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]，用力地晃动[npc2.hips]摩擦着[npc.namePos]的[npc.hand]，"
										+"咆哮着命令[npc.name]继续服侍自己[npc2.cock+]。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始粗暴地将[npc2.hips]顶向[npc.name]，让[npc.she]触摸，"
										+ "[npc2.name]让[npc.herHim]用[npc.hand]握住[npc2.namePos][npc2.cock+]，命令[npc.herHim]不许停下来。",
	
								"[npc2.Name]用力摆动[npc2.hips]，强行摩擦着[npc.namePos]的[npc.hand]，"
										+"[npc2.she][npc2.moaning]着，要求[npc.name]继续侍奉。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]，饥渴地摆动[npc2.hips]摩擦着[npc.namePos]的[npc.hand]，"
										+"感受着自己[npc2.cock+]在[npc.her]手中不断泵动。",
								
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地将[npc2.hips]顶向[npc.name]，让[npc.she]触摸，"
										+ "享受[npc.her][npc.fingers+]在自己[npc2.cock+]上下撸动的快感。",
	
								"[npc2.Name]饥渴地摆动[npc2.hips]，摩擦着[npc.namePos]的[npc.hand]，"
										+"[npc2.she][npc2.moaning]着，感受手指在[npc2.cock+]上用力擦过的感觉。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]，不断摆动[npc2.hips]摩擦着[npc.namePos]的[npc.hand]，"
										+"感受着自己[npc2.cock+]在[npc.her]手中不断泵动。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始将[npc2.hips]顶向[npc.name]，让[npc.she]触摸，"
										+ "享受[npc.her][npc.fingers+]在自己[npc2.cock+]上下撸动的快感。",
	
								"[npc2.Name]摆动[npc2.hips]，摩擦着[npc.namePos]的[npc.hand]，"
										+"[npc2.she][npc2.moaning]着，感受手指在[npc2.cock+]上用力擦过的感觉。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]小声呜咽着，想要挣脱[npc.namePos]强硬的触碰，却只能屈服于[npc2.cock]带来的快感。",
	
								"[npc2.name]发出[npc2.a_sob+]，试图逃离[npc.Name]，不断地挣扎并抗议，[npc2.her]硬邦邦的[npc2.cock]暴露了[npc2.her]正在发情。",
	
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，[npc2.she]试图反抗[npc.namePos]令人不快的触摸。"));
						break;
					default:
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
					return (UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]拱起[npc2.hips]，"
									+ "[npc2.she]发出一声愉悦的[npc2.moan]，热切地乞求着[npc.Name]继续为[npc2.herHim]手交。",
		
							"[npc2.namePos][npc2.lips]间爆发出一声愉悦的[npc2.moan]，"
									+ "[npc2.she]饥渴地挺起[npc2.hips]，让[npc.name]触摸，乞求[npc.Name]继续为[npc2.herHim]手交。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，向前挺起[npc2.hips+]，急切地恳求[npc.Name]继续为[npc2.herHim]手交。"));
				case SUB_RESISTING:
					return (UtilText.returnStringAtRandom(
							"[npc2.name][npc2.a_sob]，挣扎着想要摆脱[npc.namePos]的触碰，却浑身无力，",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，"
									+"[npc2.she]挣扎着想要推开[npc.namePos]，停止被手交，却浑身无力。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试"
									+ "不让[npc.name]触碰自己的[npc2.hips]，但[npc.name]仍在强行为[npc2.herHim]手交，[npc2.she]奋力反抗着[npc.name]。"));
				case DOM_GENTLE:
					return (UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]拱起[npc2.hips]，[npc2.she]发出一声愉悦的[npc2.moan]，热切地乞求着[npc.Name]继续为[npc2.herHim]手交。",
	
							"[npc2.namePos][npc2.lips]间爆发出一声愉悦的[npc2.moan]，"
									+ "[npc2.she]温柔地挺起[npc2.hips]，让[npc.name]触摸，乞求[npc.Name]继续为[npc2.herHim]手交。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地挺起[npc2.hips+]，恳求[npc.Name]继续为[npc2.herHim]手交。"));
				case DOM_ROUGH:
					return (UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]拱起[npc2.hips]，[npc2.she]发出一声愉悦的[npc2.moan]，命令[npc.Name]继续为[npc2.herHim]手交。",
	
							"[npc2.namePos][npc2.lips]间爆发出一声愉悦的[npc2.moan]，"
									+ "[npc2.she]粗暴地挺起[npc2.hips]，让[npc.name]触摸，命令[npc.Name]继续为[npc2.herHim]手交。",
	
							"[npc2.name]愉悦地[npc2.moaning]着，积极地猛推[npc2.hips+]，命令[npc.Name]继续为[npc2.herHim]手交。"));
				case SUB_NORMAL:
					return (UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]拱起[npc2.hips]，[npc2.she]发出一声愉悦的[npc2.moan]，乞求[npc.Name]继续为[npc2.herHim]手交。",
		
							"[npc2.namePos]的[npc2.lips]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]挺起[npc2.hips]，让[npc.name]触摸，恳求[npc.Name]继续为[npc2.herHim]手交。",
		
							"[npc2.name][npc2.moaning+]着，挺起自己的[npc2.hips]，恳求[npc.Name]继续为[npc2.herHim]手交。"));
			}
		}
		return "";
	}
	
	public static final SexAction COCK_MASTURBATING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "手交(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "继续温柔地给[npc2.name]手交。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]温柔地用[npc.her][npc.hand]在[npc2.namePos][npc2.penisGirth]阴茎上来回磨蹭，将[npc2.precum+]涂抹到整根阴茎上。",
						
						"[npc.Name]用手指蹭着[npc2.namePos]的[npc2.precum+]做润滑剂，在[npc2.her][npc2.cock+]上来回划过。",
						
						"满溢地[npc.moan]着，[npc.she]温柔地撸动着[npc2.namePos]开始渗出先走液的[npc2.cock]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]温柔地用[npc.fingers+]握住了[npc2.namePos][npc2.cock+]，[npc.hand]缓慢撸动着[npc2.her][npc2.penisGirth]的肉棒。",

						"[npc.Name]轻轻地用握住[npc2.namePos]的[npc2.cock+]，"
								+ "向前倾身，吸了一口[npc2.her][npc2.scent+]，然后用[npc.fingers+]在[npc2.her][npc2.penisGirth]的柱身上撸动。",

						"[npc.Name]温柔地贴在[npc2.name]身上，轻柔地用[npc.she][npc.hand+]上下撸动[npc2.namePos][npc2.cock+]，发出[npc.a_moan+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "手交";
		}
		@Override
		public String getActionDescription() {
			return "继续给[npc2.name]手交。";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]急切地用[npc.her][npc.hand]在[npc2.namePos][npc2.penisGirth]阴茎上来回磨蹭，将[npc2.precum+]涂抹到整根阴茎上。",
						
						"[npc.Name]兴奋地用手指蹭着[npc2.namePos]的[npc2.precum+]做润滑剂，在[npc2.her][npc2.cock+]上来回撸动着。",
						
						"[npc.Name]发出[npc.a_moan+]，快速撸动着[npc2.namePos]溢出先走液的[npc2.cock]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]饥渴地用[npc.fingers+]握住了[npc2.namePos][npc2.cock+]，[npc.hand]迅速撸动着[npc2.her][npc2.penisGirth]的肉棒。",
	
						"[npc.Name]急切地上下移动着[npc.hand]，撸动着[npc2.namePos]的[npc2.cock+]，"
								+ "向前倾身，吸了一口[npc2.her][npc2.scent+]，然后饥渴地用[npc.fingers+]在[npc2.her][npc2.penisGirth]的柱身上撸动。",
	
						"[npc.Name]开心地贴在[npc2.name]身上，迅速地用[npc.she][npc.hand+]上下撸动[npc2.namePos][npc2.cock+]，发出[npc.a_moan+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "手交(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地给[npc2.name]手交。";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name粗暴地用[npc.her][npc.hand]在[npc2.namePos][npc2.penisGirth]阴茎上来回磨蹭，将[npc2.precum+]涂抹到整根阴茎上。",
						
						"[npc.Name]抓了一把[npc2.namePos]的[npc2.precum+]做润滑剂，粗鲁地撸动着[npc2.her][npc2.cock+]。",
						
						"[npc.Name]发出[npc.a_moan+]，粗暴地撸动着[npc2.namePos]溢出先走液的[npc2.cock]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]紧紧地抓住[npc2.namePos][npc2.cock+]，用力地撸动着[npc2.penisGirth]的茎身。",
	
						"[npc.Name]粗暴地抓住[npc2.namePos][npc2.cock+]，"
								+ "向前倾身，吸了一口[npc2.her][npc2.scent+]，然后粗暴地用[npc.fingers+]在[npc2.her][npc2.penisGirth]的柱身上撸动。",
	
						"[npc.Name]粗暴地贴在[npc2.name]身上，积极地用[npc.hand+]上下撸动着[npc2.namePos][npc2.cock+]那[npc2.penisGirth]的茎身，发出[npc.a_moan+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "手交";
		}
		@Override
		public String getActionDescription() {
			return "继续给[npc2.namePos]手交。";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]用[npc.her][npc.hand]在[npc2.namePos][npc2.penisGirth]阴茎上来回磨蹭，将[npc2.precum+]涂抹到整根阴茎上。",
						
						"[npc.Name]用手指沾了一点[npc2.namePos]的[npc2.precum+]做润滑剂，在[npc2.her][npc2.cock+]上来回撸动着。",
						
						"[npc.Name]发出[npc.a_moan+]，上下撸动着[npc2.namePos]溢出先走液的[npc2.cock]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]抓住[npc2.namePos][npc2.cock+]，撸动着[npc2.penisGirth]的茎身。",
	
						"[npc.Name]抓住[npc2.namePos][npc2.cock+]，"
								+ "向前倾身，吸了一口[npc2.her][npc2.scent+]，然后用[npc.fingers+]上下撸动[npc2.her][npc2.penisGirth]的柱身。",
	
						"[npc.Name]紧紧贴在[npc2.name]身上，用[npc.she][npc.hand+]上下撸动[npc2.namePos][npc2.cock+]，发出[npc.a_moan+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_COCK_MASTURBATING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "手交(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "热情地给[npc2.name]手交。";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]急切地用[npc.her][npc.hand]在[npc2.namePos][npc2.penisGirth]阴茎上来回磨蹭，将[npc2.precum+]涂抹到整根阴茎上。",
						
						"[npc.Name]兴奋地用手指蹭着[npc2.namePos]的[npc2.precum+]做润滑剂，在[npc2.her][npc2.cock+]上来回撸动着。",
						
						"[npc.Name]发出[npc.a_moan+]，快速撸动着[npc2.namePos]溢出先走液的[npc2.cock]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]饥渴地用[npc.fingers+]握住了[npc2.namePos][npc2.cock+]，[npc.hand]迅速撸动着[npc2.her][npc2.penisGirth]的肉棒。",
	
						"[npc.Name]急切地上下移动着[npc.hand]，撸动着[npc2.namePos]的[npc2.cock+]，"
								+ "向前倾身，吸了一口[npc2.her][npc2.scent+]，然后饥渴地用[npc.fingers+]在[npc2.her][npc2.penisGirth]的柱身上撸动。",
	
						"[npc.Name]开心地贴在[npc2.name]身上，迅速地用[npc.she][npc.hand+]上下撸动[npc2.namePos][npc2.cock+]，发出[npc.a_moan+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "抗拒手交";
		}
		@Override
		public String getActionDescription() {
			return "努力让你的[npc.hand]远离[npc2.namePos][npc2.cock+]。";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
							+"[npc.name]发出一阵[npc.a_sob+]，虚弱地尝试将自己的[npc.hand]从[npc2.namePos][npc2.cock+]上挪开。",

					"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将自己的[npc.hand]从[npc2.namePos][npc2.cock+]上挪开。",

					"[npc.Name]痛哭着，拼命地想要收回[npc2.namePos][npc2.cock+]上的[npc.hand]。"));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]先[npc.name]一步行动，主动抓住[npc.Name]，温柔但坚决地强迫[npc.she]给自己手交。",

							"[npc2.Name]迅速靠过去，抓紧[npc.namePos]的手，温柔地引导着继续撸动自己的[npc2.cock+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]先[npc.name]一步行动，主动抓住[npc.her]的[npc.hand]，坚决地强迫[npc.she]给自己手交。",

							"[npc2.Name]迅速靠过去，抓住[npc.namePos]的手，拉回去继续撸动自己的[npc2.cock+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]先[npc.name]一步行动，粗暴地抓住[npc.namePos]的[npc.hand]，强迫[npc.herHim]继续给自己手交。",

							"[npc2.Name]迅速靠过去，抓住[npc.namePos]的手，粗暴地强迫[npc.her]继续撸动自己的[npc2.cock+]。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction COCK_MASTURBATING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止手交";
		}
		@Override
		public String getActionDescription() {
			return "松开[npc2.namePos][npc2.cock+]，停止手交[npc2.herHim]。";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗鲁地捏了一下[npc2.namePos]的[npc2.cockHead]，从[npc2.cock+]上收回了手指。",

							"[npc.Name]深呼吸，品味着[npc2.namePos][npc2.scent+]，收回了[npc.fingers]。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]捏了一下[npc2.namePos]的[npc2.cockHead]，从[npc2.cock+]上收回了手指。",

							"[npc.Name]深呼吸，品味着[npc2.namePos][npc2.scent+]，收回了[npc.fingers]。"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]松了一口气，然后继续反抗着[npc.name]，又发出了一阵[npc2.a_sob+]。",

							"[npc2.name]发出[npc2.a_sob+]，继续反抗并挣扎着，但[npc.name]依然将[npc2.she]固定在原位。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]停止玩弄[npc2.namePos][npc2.cock+]，[npc2.Name]发出一阵[npc2.a_moan+]。",

							"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]渴望得到[npc.namePos]的更多关注。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction COCK_MASTURBATED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "开始手交";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]开始给你手交。";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]温柔但牢固地抓住[npc2.namePos]的[npc2.hand]，将[npc2.namePos]那[npc2.fingers]引导到自己的[npc.cock+]，"
									+ "发出轻轻地[npc.a_moan+]，让[npc2.namePos]开始为自己手交。",
							
							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，引导[npc2.her]的[npc2.fingers]握住自己[npc.cock+]并缓慢地撸了起来，"
									+ "[npc.she]温柔地让[npc2.namePos]开始为自己手交。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢抓住[npc2.namePos]的[npc2.hand]，急切地将[npc2.namePos]那[npc2.fingers]引导到自己[npc.cock+]，"
									+ "发出[npc.a_moan+]，贪婪地让[npc2.namePos]开始为自己手交。",

							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，急切地引导[npc2.her]的[npc2.fingers]滑向自己[npc.cock+]并缓慢地撸了起来，"
									+ "[npc.she]贪婪地让[npc2.namePos]开始为自己手交。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢钳制住[npc2.namePos]的[npc2.hand]，用[npc2.namePos]那[npc2.fingers]磨蹭自己[npc.cock+]，"
									+ "发出[npc.a_moan+]，粗暴地让[npc2.namePos]开始为自己手交。",

							"[npc.name]抓住[npc2.namePos]的[npc2.hand]，激烈地将[npc2.namePos]那[npc2.fingers]拉向[npc.her][npc.cock+]，然后霸道地猛推，"
									+ "[npc.she]粗暴地强迫[npc2.namePos]开始为自己手交。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢抓住[npc2.namePos]的[npc2.hand]，急切地将[npc2.namePos]那[npc2.fingers]引导到自己[npc.cock+]，"
									+ "发出[npc.a_moan+]，贪婪地让[npc2.namePos]开始为自己手交。",

							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，急切地引导[npc2.namePos]的[npc2.fingers]滑向自己[npc.cock+]并缓慢地撸了起来，"
									+ "[npc.she]贪婪地让[npc2.namePos]开始为自己手交。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢抓住[npc2.namePos]的[npc2.hand]，将[npc2.namePos]那[npc2.fingers]引导到自己[npc.cock+]，"
									+ "发出[npc.a_moan+]，让[npc2.namePos]开始为自己手交。",

							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，引导着[npc2.namePos]的[npc2.fingers]滑向自己[npc.cock+]并缓慢地撸了起来，"
									+ "[npc.she]让[npc2.namePos]开始为自己手交。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出和缓的[npc2.moan]，温柔地抽插起[npc.cock+]。",
	
								"[npc2.name]轻柔地[npc2.moan]，开始慢慢地用[npc2.hand]上下撸动[npc.namePos][npc.cock+]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]饥渴地抚摸[npc.namePos][npc.cock+]，发出一阵[npc2.a_moan+]。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始愉悦地用[npc2.hand]上下撸动[npc.namePos][npc.cock+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]激烈地爱抚着[npc.namePos]的[npc.cock]，忘我地发出一阵[npc2.a_moan+]，为了提醒[npc.Name]谁才是主导者，"
										+ "[npc2.she]粗暴地挤压着[npc.namePos]抽动的阴茎。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始粗暴地用[npc2.hand]上下撸动[npc.namePos][npc.cock+]，"
										+ "[npc2.she]无情地挤压着[npc.namePos]抽动的阴茎，宣告着自己的支配权。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]饥渴地抚摸[npc.namePos][npc.cock+]，发出一阵[npc2.a_moan+]。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始愉悦地用[npc2.hand]上下撸动[npc.namePos][npc.cock+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]开始抚摸[npc.namePos][npc.cock+]，发出一阵[npc2.a_moan+]。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始用[npc2.hand]上下撸动[npc.namePos][npc.cock+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]强迫[npc2.name]手交，[npc2.her]不禁发出一声[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着，想要从[npc.namePos]的紧握中抽出自己的[npc2.hand]。",
	
								"[npc2.name]发出[npc2.a_sob+]，挣扎着从[npc.namePos]的紧握中抽出手，"
										+"[npc2.she]乞求着停止手交。"));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "接受手交(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "温柔地享受[npc2.namePos]的手交。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]温柔地将[npc.hips]压向[npc2.namePos]的[npc2.hand]，让[npc2.name]继续为[npc.herHim]手交，发出了一阵轻柔地[npc.a_moan+]。",

					"[npc.name]发出一阵轻柔地[npc.a_moan+]，将[npc.hips]温柔地挺向[npc2.namePos]的[npc2.hand]，享受着[npc2.namePos]的手交。",

					"[npc.name]缓慢地将[npc.hips]顶向[npc2.namePos]的[npc2.hand]，"
							+ "口中的轻叹体现着[npc.namePos]十分享受[npc2.namePos]的服侍。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "接受手交";
		}
		@Override
		public String getActionDescription() {
			return "享受[npc2.namePos]的手交。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]急切地将[npc.hips]压向[npc2.namePos]的[npc2.hand]，让[npc2.name]继续为[npc.herHim]手交，发出了一阵[npc.a_moan+]。",

					"[npc.name]发出一阵[npc.a_moan+]，亢奋地将[npc.hips]挺向[npc2.namePos]的[npc2.hand]，享受着[npc2.namePos]的手交。",

					"[npc.name]急切地将[npc.hips]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]的动作表明了[npc.she]很高兴得到[npc2.namePos]的手交。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "接受手交(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地享受[npc2.namePos]的手交。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]发出[npc.a_moan+]，粗鲁地将[npc.hips]压向[npc2.namePos]的[npc2.hand]，让[npc2.name]继续为[npc.herHim]手交。",

					"[npc.name]发出一阵[npc.a_moan+]，将[npc.hips]积极地挺向[npc2.namePos]的[npc2.hand]，享受着[npc2.namePos]的手交。",

					"[npc.name]粗暴地将[npc.hips]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]的动作表明了[npc.she]很高兴得到[npc2.namePos]的手交。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "接受手交";
		}
		@Override
		public String getActionDescription() {
			return "享受[npc2.namePos]的手交。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]将[npc.hips]压向[npc2.namePos]的[npc2.hand]，让[npc2.name]继续为[npc.herHim]手交，发出了一阵[npc.a_moan+]。",

					"[npc.name]发出一阵[npc.a_moan+]，将[npc.hips]挺向[npc2.namePos]的[npc2.hand]，享受着[npc2.namePos]的手交。",

					"[npc.name]将[npc.hips]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]的动作表明了[npc.she]很高兴得到[npc2.namePos]的手交。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "接受手交(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "饥渴地享受[npc2.namePos]的手交。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]急切地将[npc.hips]压向[npc2.namePos]的[npc2.hand]，让[npc2.name]继续为[npc.herHim]手交，发出了一阵[npc.a_moan+]。",

					"[npc.name]发出一阵[npc.a_moan+]，亢奋地将[npc.hips]挺向[npc2.namePos]的[npc2.hand]，享受着[npc2.namePos]的手交。",

					"[npc.name]急切地将[npc.hips]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]的动作表明了[npc.she]很高兴得到[npc2.namePos]的手交。"));

			return UtilText.nodeContentSB.toString();
		}

	};

	public static final SexAction COCK_MASTURBATED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "接受手交(抗拒)";
		}
		@Override
		public String getActionDescription() {
			return "试着让你[npc.cock+]远离[npc2.namePos]的[npc2.hand]。";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
							+ "[npc.she]发出[npc.a_sob+]，想要从[npc2.namePos][npc2.hand]中抽身。",

					"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将自己[npc.cock+]从[npc2.namePos]的[npc2.hand]中抽离。",

					"[npc.she]痛苦地[npc.sobsVerb]着，拼命地想要将自己[npc.cock+]从[npc2.namePos]的[npc2.hand]中抽离。"));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]先[npc.name]一步行动，温柔地握住了[npc.namePos][npc.cock+]，"
									+ "让[npc.name]别无选择，只能留在原处被迫享受[npc2.she]提供的手交。",

							"[npc2.Name]控制好力度，温柔而牢牢地抓住[npc.namePos][npc.cock+]继续撸动，防止[npc.her]逃跑。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]先[npc.name]一步行动，握住了[npc.namePos][npc.cock+]，"
									+ "让[npc.name]别无选择，只能留在原处被迫享受[npc2.she]提供的手交。",

							"[npc2.Name]牢牢抓住[npc.namePos][npc.cock+]继续撸动，防止[npc.her]逃跑。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]先[npc.name]一步行动，粗暴地抓住了[npc.namePos][npc.cock+]，"
									+ "让[npc.name]别无选择，只能留在原处被迫享受[npc2.she]提供的手交。",

							"[npc2.Name]更加用力抓紧[npc.namePos][npc.cock+]继续撸动，防止[npc.her]逃跑。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止接受手交";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.hand]从你的[npc.cock]上挪开。";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]那[npc2.fingers]从自己[npc.cock+]上拉开，[npc.she]愤怒地咆哮着，命令[npc2.name]停止手交。",
	
							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]那[npc2.fingers]从自己[npc2.cock+]上拉开。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]那[npc2.fingers]从自己[npc.cock+]上拉开，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]停止手交。",
	
							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc2.her]的[npc2.hand]从自己[npc2.cock+]上拉开。"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]松了一口气，但当[npc2.she]意识到[npc.name]还没完全满足时，又发出了一阵[npc2.a_sob+]。",
	
							"[npc2.name]发出一阵[npc2.a_sob+]，继续反抗并挣扎着，但[npc.name]依然牢牢地将[npc2.she]固定在原位。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]不再让[npc2.name]触摸自己[npc.cock+]，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
	
							"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]想更多关注[npc.namePos][npc.cock+]的渴望。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
