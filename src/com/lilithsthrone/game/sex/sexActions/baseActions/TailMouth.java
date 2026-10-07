package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
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
public class TailMouth {
	
	// Character performing action is receiving the face-fucking:
	
	public static final SexAction TAIL_THROAT_FUCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "被吸吮尾巴";
		}
		@Override
		public String getActionDescription() {
			return "把你[npc.tail+(true)]滑进[npc2.namePos]的喉咙，让[npc2.herHim]给你口交。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]慢慢用[npc.tail(true)][npc.tailTip+]挑逗着[npc2.namePos][npc2.lips+]，"
									+ "发出轻微的[npc.moan]，缓缓地向前推，将[npc.tail+(true)]没入[npc2.namePos][npc2.asshole+]里。",
							"[npc.Name]将其[npc.tail(true)][npc.tailTip+]抵住[npc2.namePos][npc2.lips+]，"
									+ "[npc.she]以缓慢而稳定的力度，轻柔地将它深深地插入[npc2.namePos]的喉咙。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tail(true)][npc.tailTip+]饥渴地挑逗着[npc2.namePos][npc2.lips+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.tail+(true)]深深插入[npc2.her]的喉咙。",
							"[npc.Name]将其[npc.tail(true)][npc.tailTip+]抵住[npc2.namePos][npc2.lips+]，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos]的喉咙。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tail(true)][npc.tailTip+]粗暴地磨蹭[npc2.namePos][npc2.lips+]，"
									+ "发出[npc.a_moan+]，然后粗暴地向前猛推，将[npc.tail+(true)]深深插入[npc2.her]的喉咙。",
							"[npc.Name]将其[npc.tail(true)][npc.tailTip+]抵住[npc2.namePos][npc2.lips+]，"
									+ "用力前推，粗暴地将它深深插入[npc2.her]的喉咙。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tail(true)][npc.tailTip+]饥渴地挑逗着[npc2.namePos][npc2.lips+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，贪婪地将[npc.tail+(true)]深深插入[npc2.her]的喉咙。",
							"[npc.Name]将其[npc.tail(true)][npc.tailTip+]抵住[npc2.namePos][npc2.lips+]，"
									+ "[npc.she]以难以撼动的推力，急切地将它插进[npc2.namePos]的喉咙。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.tail(true)][npc.tailTip+]挑逗着[npc2.namePos][npc2.lips+]，"
									+ "发出[npc.a_moan+]，然后主动向前迎上，将[npc.tail+(true)]深深插入[npc2.her]的喉咙。",
							"[npc.Name]将其[npc.tail(true)][npc.tailTip+]抵住[npc2.namePos][npc2.lips+]，"
									+ "[npc.she]小幅度前推，将它深深插入[npc2.namePos]的喉咙。"));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]吮吸着[npc.namePos][npc.tail+(true)]，模糊不清地[npc2.moan]着，"
										+ "[npc2.she]温柔地向前伸头，让它在自己喉咙里插得更深。",
								"[npc2.name]轻柔地[npc2.moan]，温柔地将头部压向前，"
										+ "将[npc.namePos][npc.tail+(true)]更深地插入自己的喉咙。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]吮吸着[npc.namePos][npc.tail+(true)]，模糊不清地[npc2.moan]着，"
										+ "[npc2.she]粗暴地向前伸头，强迫它在自己喉咙里插得更深。",
								"[npc2.name]发出带着水声的[npc2.moan]，向前伸头，"
										+"用力把[npc.namePos][npc.tail+(true)]咽到喉咙深部。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]吮吸着[npc.namePos][npc.tail+(true)]，模糊不清地[npc2.moan]着，"
										+ "[npc2.her]急切地向前伸头，让它在自己喉咙里插得更深。",
								"[npc2.name]发出带着水声的[npc2.moan]，兴奋而急切地前后摆动着头部，"
										+"把[npc.namePos][npc.tail+(true)]推入喉咙深部。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]吮吸着[npc.namePos][npc.tail+(true)]，模糊不清地[npc2.moan]着，"
										+ "[npc2.she]向前伸头，让它在自己喉咙里插得更深。",
								"[npc2.name]发出带着水声的[npc2.moan]，兴奋地前后摆动着头部，"
										+"把[npc.namePos][npc.tail+(true)]推入喉咙深部。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]尝到了[npc.namePos][npc.tail(true)]的味道，不禁漏出一声[npc2.a_sob+]，"
										+ "泪水不停地从[npc2.her]的[npc2.face]上淌下，[npc2.she]徒劳地挣扎扭身，试图拔出讨厌的插入物。",
								"[npc2.Name]试图摆脱侵犯，但终究也是徒劳无功。"
										+"眼泪流下，[npc2.she]喉间发出带着水声的哭声。"));
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
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]饥渴地向前伸头作为回应，"
									+"伴随着喉咙中不时漏出带着水声的呻吟，[npc2.Name]亢奋地配合[npc.namePos]把[npc.tail+(true)]吞得更深。",
							"[npc2.namePos][npc2.lips+]间漏出带着水声的[npc2.moan]，"
									+"，接着，[npc2.Name]饥渴地把头前移配合[npc.namePos]把[npc.tail+(true)]吞得更深。",
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地将头向前压，以便强迫[npc.namePos][npc.tail+(true)]深深插入[npc2.her]的喉咙。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要挣脱[npc.namePos]的[npc.tail(true)]，但没能成功，"
									+"眼泪流下，带着水声的哭音从喉中泄出，[npc2.Name]想把喉咙中的尾巴拉出却无能为力。",
							"[npc2.namePos][npc2.lips]间漏出一声低沉的[npc2.sob]，[npc2.she]无力地尝试逃脱，"
									+ "[npc2.her]的喉咙被[npc.namePos][npc.tail+(true)]侵犯，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地反抗着，尝试将[npc.NamePos]的[npc.tail(true)]拔出[npc2.her]的喉咙。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]向前伸头作为回应，"
									+"伴随着喉咙中不时漏出带着水声的呻吟，[npc2.Name]配合[npc.namePos]把[npc.tail+(true)]吞得更深。",
							"[npc2.namePos][npc2.lips+]间漏出带着水声的[npc2.moan]，"
									+"接着，[npc2.she]向前摆头，配合[npc.namePos]把[npc.tail+(true)]吞到喉咙深处。",
							"[npc2.name]愉悦地[npc2.moaning]着，开始将头向前压，以便强迫[npc.namePos][npc.tail+(true)]深深插入[npc2.her]的喉咙。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]缓缓地向前伸头，"
									+"伴随着喉咙中不时漏出带着水声的呻吟，[npc2.Name]温柔地配合[npc.namePos]把[npc.tail+(true)]吞得更深。",
							"[npc2.namePos][npc2.lips+]间轻轻地漏出带着水声的[npc2.moan]，"
									+ "[npc2.she]缓缓地向前伸头，让[npc.namePos][npc.tail+(true)]得以深深插入[npc2.her]的喉咙。",
							"[npc2.name]兴奋地[npc2.moaning]着，慢慢向前摆头配合[npc.namePos]把尾巴吞到深处。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]粗暴地将头部向前压，"
									+"伴随着喉咙中不时漏出带着水声的呻吟，[npc2.Name]支配地把[npc.namePos]的[npc.tail+(true)]吞得更深。",
							"[npc2.namePos][npc2.lips+]间漏出带着水声的[npc2.moan]，"
									+ "[npc2.she]粗鲁地将头向前压，暴力地让[npc.namePos][npc.tail+(true)]深深插入[npc2.her]的喉咙。",
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地将头向前压，以便让[npc.namePos][npc.tail+(true)]深深插入[npc2.her]的喉咙。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction TAIL_THROAT_FUCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "被吮吸尾巴(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "把你的[npc.tail(true)]温柔地在[npc2.namePos]的喉咙里抽插。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]柔和地将[npc.tail+(true)]深深插入[npc2.namePos]的喉咙，"
							+ "[npc.she]开始不断地抽插，每次温柔的推入都会发出一小声[npc.moan]。",
					"[npc.name]慢慢地将[npc.tail+(true)]插入[npc2.namePos]的喉咙，"
							+ "开始温柔地抽插，[npc.she]轻柔地接受着[npc2.namePos]的口交，不时就会漏出一小声[npc.moans+]。",
					"[npc.name]将[npc.tail+(true)]滑入[npc2.namePos]的喉咙，"
							+ "发出轻微的[npc.moan]，开始温柔地抽送打桩，一边呼吸着[npc2.namePos]的[npc2.scent]，一边慢慢地对[npc2.herHim]进行尾巴口交。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
				
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_THROAT_FUCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "被吮吸尾巴";
		}
		@Override
		public String getActionDescription() {
			return "继续在[npc2.namePos]的喉咙里抽送你[npc.tail+(true)]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.tail+(true)]深深插入[npc2.namePos]的喉咙，"
							+ "[npc.name]开始亢奋地抽插，每次兴奋地推动都会发出[npc.a_moan+]。",
					"[npc.name]亢奋地将[npc.tail+(true)]插入[npc2.namePos]的喉咙，"
							+ "开始亢奋地抽插打桩，[npc.she]贪婪地接受着[npc2.namePos]的口交，不时就会漏出一阵[npc.a_moan+]。",
					"[npc.name]将[npc.tail+(true)]深深插入[npc2.namePos]的喉咙，"
							+ "[npc.Name]开始急切地抽送打桩，不时漏出一阵[npc.a_moan+]，[npc.she]一边嗅着[npc2.namePos]的[npc2.scent]，一边亢奋地用尾巴操着[npc2.her]的脸。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "被吮吸尾巴(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "用你[npc.tail+(true)]粗暴地抽插[npc2.namePos]的喉咙。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]粗暴地将[npc.tail+(true)]深深插入[npc2.namePos]的喉咙，"
							+ "开始粗鲁地抽插打桩，每次猛推都会发出[npc.a_moan+]。",
					"[npc.name]猛烈地将[npc.tail+(true)]插入[npc2.namePos]的喉咙，"
							+ "开始粗暴地抽送打桩，激烈地接受着[npc2.namePos]的口交，发出一阵[npc.a_moan+]。",
					"[npc.name]激烈地将[npc.tail+(true)]深深插入[npc2.namePos]的喉咙，"
							+ "开始粗鲁地抽送打桩，[npc.she]嗅着[npc2.namePos]的[npc2.scent]，暴力地尾交[npc2.namePos]的脸，不时漏出[npc.a_moan+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "被吮吸尾巴";
		}
		@Override
		public String getActionDescription() {
			return "继续操[npc2.namePos]的喉咙。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.tail+(true)]深深插入[npc2.namePos]的喉咙，"
							+ "开始抽插打桩，每次推动都会发出[npc.a_moan+]。",
					"[npc.name]将[npc.tail+(true)]插入[npc2.namePos]的喉咙，"
							+ "开始抽插打桩，[npc.she]接受着[npc2.namePos]的口交，不时就会漏出[npc.a_moan+]。",
					"[npc.name]将[npc.tail+(true)]深深插入[npc2.namePos]的喉咙，"
							+ "[npc.Name]开始抽送打桩，不时漏出一阵[npc.a_moan+]，[npc.she]一边嗅着[npc2.namePos]的[npc2.scent]，一边用尾巴操着[npc2.her]的脸。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "被吮吸尾巴(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "让你[npc.tail+(true)]在[npc2.namePos]的喉咙里渴求地塞进拔出。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.tail+(true)]深深插入[npc2.namePos]的喉咙，"
							+ "[npc.name]开始亢奋地抽插，每次兴奋地推动都会发出[npc.a_moan+]。",
					"[npc.name]亢奋地将[npc.tail+(true)]插入[npc2.namePos]的喉咙，"
							+ "开始亢奋地抽插打桩，[npc.she]贪婪地接受着[npc2.namePos]的口交，不时就会漏出一阵[npc.a_moan+]。",
					"[npc.name]将[npc.tail+(true)]深深插入[npc2.namePos]的喉咙，"
							+ "[npc.Name]开始急切地抽送打桩，不时漏出一阵[npc.a_moan+]，[npc.she]一边嗅着[npc2.namePos]的[npc2.scent]，一边亢奋地用尾巴操着[npc2.her]的脸。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "抗拒被吮吸尾巴";
		}
		@Override
		public String getActionDescription() {
			return "努力把你的[npc.tail(true)]从[npc2.namePos]的喉咙里拔出来。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]尝试将[npc.tail(true)]从[npc2.namePos]的嘴里拔出来，"
									+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就温柔地抓住它，然后轻柔地把它扭回[npc2.namePos]的喉咙里。",
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.tail(true)]从[npc2.name]的身体里拔出来，"
									+ "但[npc2.name]马上就抓住了它，并温柔地把它扭回[npc2.her]的喉咙里。",
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.tail(true)]从[npc2.namePos][npc2.lips+]里拔出来，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边温柔地将[npc.her][npc.tail+(true)]压向自己的喉咙。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]尝试将[npc.tail(true)]从[npc2.namePos]的嘴里拔出来，"
									+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就粗暴地抓住它，并蛮横地将它扭回[npc2.namePos]的喉咙里。",
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.tail(true)]从[npc2.name]的身体里拔出来，"
									+ "但[npc2.name]马上就抓住了它，并粗暴地把它扭回[npc2.her]的喉咙里。",
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.tail(true)]从[npc2.namePos][npc2.lips+]里拔出来，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边粗暴地将[npc.her][npc.tail+(true)]压向自己的喉咙。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]尝试将[npc.tail(true)]从[npc2.namePos]的嘴里拔出来，"
									+ "但[npc.her]终究是白费力气，因为[npc2.name]马上就牢牢地抓住它，然后热切地把它扭回[npc2.namePos]的喉咙里。",
							"伴随着一声[npc.a_sob+]，[npc.Name]试着把[npc.tail(true)]从[npc2.name]的身体里拔出来，"
									+ "但[npc2.name]马上就抓住了它，并急切地把它扭回[npc2.her]的喉咙里。",
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.tail(true)]从[npc2.namePos][npc2.lips+]里拔出来，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边将[npc.her][npc.tail+(true)]压向自己的喉咙。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止被吮吸尾巴";
		}
		@Override
		public String getActionDescription() {
			return "把你[npc.tail+(true)]从[npc2.namePos]的喉咙里拔出来，停止和[npc2.herHim]口交。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将[npc.tail+(true)]从[npc2.namePos]口中拉出，"
									+ "霸道地用[npc.tailTip+]最后一次上下磨蹭[npc2.her][npc2.lips+]，然后将它移开。",
							"[npc.Name]在[npc2.name]喉咙中抽动了一下[npc.tail+(true)]就把它从[npc2.name]嘴里拔了出来，结束了这场深喉。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.tail(true)]从[npc2.namePos]口中抽出，"
									+ "用[npc.tailTip]最后一次上下磨蹭[npc2.her][npc2.lips+]，然后将它移开。",
							"在最后一次深入[npc2.name]的喉咙后，[npc.name]随后将[npc.her]那[npc.tail+(true)]从[npc2.her]的嘴里抽了出来。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.Name]从[npc2.her]的喉咙里拔出来时，[npc2.name]忍不住漏出一声[npc2.sob+]，"
										+ "[npc2.she]仍然不停地哭泣，无力地反抗着[npc.name]。",
								"[npc2.name]发出一阵[npc2.a_sob+]，拼命地挣扎着反抗[npc.Name]，将面部抽离[npc.Name]，泪水如小溪般从[npc2.face]上流了下来。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.name]将[npc.tail+(true)]拔出[npc2.her]的喉咙时，[npc2.Name]发出[npc2.a_moan+]，渴求[npc.name]的更多“照顾”。",
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]无比渴望得到[npc.namePos]的更多关注。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Character performing action is doing the face-fucking:
	
	public static final SexAction USING_TAIL_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "吮吸尾巴";
		}
		@Override
		public String getActionDescription() {
			return "[npc2.namePos][npc2.tail+(true)]滑进你的喉咙，开始使用你口交。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tail(true)]，慢慢地将它引导到自己[npc.lips+]，"
									+"伴随着带着水声的[npc.moan]，[npc.her]努力张大嘴巴放松喉咙把尾巴慢慢地含入更深处。",
							"[npc.name]抓住[npc2.namePos]的[npc2.tail(true)]，把它对准自己张开的[npc.lips]，"
									+ "慢慢地伸头向前，温柔地将它深深吞入自己的喉咙。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tail(true)]，急切地将它引导到自己[npc.lips+]，"
									+ "[npc.she]漏出一阵[npc.a_moan+]，然后张大嘴巴，贪婪地将它深深吞入自己的喉咙。",
							"[npc.name]抓住[npc2.namePos]的[npc2.tail(true)]，急躁地把它对准自己张开的[npc.lips]，"
									+ "迅速地向前摆动头部，急切地将它深深吞入自己的喉咙。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tail(true)]，粗暴地将它抓到自己[npc.lips+]，"
									+ "[npc.she]漏出一阵[npc.a_moan+]，然后张大嘴巴，激烈地将它深深吞入自己的喉咙。",
							"[npc.Name]抓住[npc2.namePos]的[npc2.tail(true)]，张开嘴用力把它塞了进去，"
									+ "迅速地向前摆动头部，粗暴地将它深深吞入自己的喉咙"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的[npc2.tail(true)]，将它引导到自己[npc.lips+]，"
									+ "[npc.she]漏出一阵[npc.a_moan+]，然后张大嘴巴，将它深深吞入自己的喉咙。",
							"[npc.name]抓住[npc2.namePos]的[npc2.tail(true)]，把它对准自己张开的[npc.lips]，"
									+ "向前摆动头部，将它深深吞入自己的喉咙。"));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出轻柔的[npc2.moan]作为回应，"
										+ "[npc2.she]温柔地将[npc2.tail(true)]向前顶，开始有节奏地尾交[npc.namePos]的喉咙。",
								"[npc2.name]轻柔地[npc2.moan]，温柔地挺进自己的[npc2.tail(true)]，"
										+ "将它深深插进[npc.namePos]的喉咙，开始有节奏地尾交[npc.herHim]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]的口中漏出了[npc2.moan+]，"
										+"把自己的[npc2.tail(true)]饥渴地推入[npc.namePos]喉咙深处，有节奏地抽插起来。",
								"[npc2.name]发出一阵[npc2.a_moan+]，愉悦地挺进自己的[npc2.tail(true)]，"
										+ "将它深深插进[npc.namePos]的喉咙，开始有节奏地尾交[npc.herHim]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]的口中漏出了[npc2.moan+]，"
										+"[npc.Name]粗暴地吞吐着[npc2.name]的[npc2.tail(true)]，提醒[npc2.her]谁在主导这场性爱。",
								"[npc2.name]发出一阵[npc2.a_moan+]，粗暴地猛插自己的[npc2.tail(true)]，"
										+"随着[npc2.name]激烈地抽插，[npc1.she]把尾巴咽入喉咙深处。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]的口中漏出了[npc2.moan+]，"
										+"把自己的[npc2.tail(true)]推入[npc.namePos]喉咙深处，有节奏地抽插起来。",
								"[npc2.name]发出一阵[npc2.a_moan+]，愉悦地挺进自己的[npc2.tail(true)]，"
										+ "将它深深插进[npc.namePos]的喉咙，开始有节奏地尾交[npc.herHim]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]紧紧裹住[npc2.name]的[npc2.tail(true)]，[npc2.name]不禁发出一声[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着推开[npc.name]，拼命地想把自己[npc2.tail+(true)]从对方的喉咙里拔出来。",
								"[npc.Name]强行将[npc2.name]的[npc2.tail(true)]深深插入自己的喉咙，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
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
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]贪婪地将自己[npc2.tail+(true)]深入[npc.namePos]的喉咙，"
									+ "[npc2.she]发出[npc2.a_moan+]，亢奋地用尾巴操干[npc.namePos]的面部。",
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.tail+(true)]深深插入[npc.namePos]的喉咙。",
							"[npc2.name]愉悦地[npc2.moaning]着，热切地挺动[npc2.her][npc2.tail+(true)]，拼命要插入[npc.namePos]喉咙的最深处。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.tail(true)]从[npc.namePos]的[npc.lips]间拔出，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.tail+(true)]在自己的喉咙中继续抽插。",
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.tail(true)]从[npc.namePos]的喉咙中抽离。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将[npc2.tail+(true)]滑入[npc.namePos]的喉咙深处，"
									+ "[npc2.she]发出柔和的[npc2.moan]，轻柔地用尾巴操干[npc.namePos]的面部。",
							"[npc2.namePos]的唇间飘出一阵[npc2.A_moan+]，[npc2.she]将[npc2.tail+(true)]深深插入[npc.namePos]的喉咙。",
							"[npc2.name]愉悦地[npc2.moaning]着，轻柔地挺动[npc2.tail+(true)]，尽可能深地插入[npc.namePos]的喉咙。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.tail+(true)]深深插入[npc.namePos]的喉咙，"
									+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地用尾巴操干[npc.namePos]的面部。",
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地将[npc2.tail+(true)]深深插入[npc.namePos]的喉咙。",
							"[npc2.name]愉悦地[npc2.moaning]着，控制着[npc2.tail+(true)]尽可能深地插入[npc.namePos]的喉咙。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.tail+(true)]深深插入[npc.namePos]的喉咙，"
									+ "[npc2.she]用尾巴操干[npc.namePos]的面部，发出一阵[npc2.a_moan+]。",
							"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.tail+(true)]深深插入[npc.namePos]的喉咙。",
							"[npc2.name]愉悦地[npc2.moaning]着，开始挺动[npc2.tail+(true)]，尽可能深地插入[npc.namePos]的喉咙。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction SUCKING_TAIL_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "吸吮尾巴(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "温柔地吮吸[npc2.namePos][npc2.tail+(true)]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声轻柔的[npc.moan]，温柔地向前伸头配合[npc2.namePos]把[npc2.tail+(true)]插到自己的喉咙深处。",
					"[npc.Name]轻哼出声，慢慢向前摆头，把[npc2.namePos]的[npc2.tail+(true)]引向自己喉咙深处",
					"[npc.name]缓慢地把龟头前推，"
							+ "[npc.lips+]间飘出一声轻柔的[npc.moan]，[npc.her]设法让[npc2.namePos][npc2.tail+(true)]深深插入[npc.her]的喉咙。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SUCKING_TAIL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "吮吸尾巴";
		}
		@Override
		public String getActionDescription() {
			return "吮吸[npc2.namePos][npc2.tail+(true)]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"伴随着喉咙中不时漏出带着水声的呻吟，[npc.Name]饥渴地向前摆头，配合[npc2.namePos]把[npc2.tail+(true)]插得更深。",
					"[npc.name]发出一阵[npc.a_moan+]，亢奋地向前伸头，强迫[npc2.namePos][npc2.tail+(true)]在自己喉咙中插得更深。",
					"[npc.name]竭力地向前伸头，"
							+"伴随着喉咙中不时漏出带着水声的呻吟，[npc.Name]开心地把[npc2.namePos]的[npc2.tail+(true)]吞得更深。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SUCKING_TAIL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "吸吮尾巴(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地吮吸[npc2.namePos][npc2.tail+(true)]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声[npc.a_moan+]，用力向前伸头，强迫[npc2.namePos]把[npc2.tail+(true)]塞得更深。",
					"[npc.name]发出一阵[npc.a_moan+]，粗暴地向前伸头，强迫[npc2.namePos][npc2.tail+(true)]在自己喉咙中插得更深。",
					"[npc.name]粗暴地把龟头前推，"
							+"伴随着喉咙中不时漏出带着水声的呻吟，[npc.Name]粗暴地把[npc2.namePos]的[npc2.tail+(true)]吞得更深。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SUCKING_TAIL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "吮吸尾巴";
		}
		@Override
		public String getActionDescription() {
			return "吮吸[npc2.namePos][npc2.tail+(true)]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"伴随着喉咙中不时漏出带着水声的呻吟，[npc.Name]饥渴地向前摆头，配合[npc2.namePos]把[npc2.tail+(true)]插得更深。",
					"[npc.name]发出一阵[npc.a_moan+]，亢奋地向前伸头，强迫[npc2.namePos][npc2.tail+(true)]在自己喉咙中插得更深。",
					"[npc.name]竭力地向前伸头，"
							+"伴随着喉咙中不时漏出带着水声的呻吟，[npc.Name]开心地把[npc2.namePos]的[npc2.tail+(true)]吞得更深。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SUCKING_TAIL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "吮吸尾巴(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "饥渴地用你的喉咙吞吐[npc2.namePos][npc2.tail+(true)]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"伴随着喉咙中不时漏出带着水声的呻吟，[npc.Name]饥渴地向前摆头，配合[npc2.namePos]把[npc2.tail+(true)]插得更深。",
					"[npc.name]发出一阵[npc.a_moan+]，亢奋地向前伸头，强迫[npc2.namePos][npc2.tail+(true)]在自己喉咙中插得更深。",
					"[npc.name]竭力地向前伸头，"
							+"伴随着喉咙中不时漏出带着水声的呻吟，[npc.Name]开心地把[npc2.namePos]的[npc2.tail+(true)]吞得更深。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "抗拒吸吮尾巴";
		}
		@Override
		public String getActionDescription() {
			return "努力远离[npc2.namePos][npc2.tail+(true)]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
									+ "[npc.she]无力地试着将[npc2.namePos][npc2.tail(true)]从自己喉咙里拔出来。",
							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将头部从[npc2.namePos]令人憎恶的性器抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.tail+(true)]依然从容地在[npc.her]的喉咙里滑进滑出。",
							"[npc.name]拼命地尝试把头挪开，"
									+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.tail+(true)]依然温柔地滑进[npc.her]的喉咙深处。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
									+ "[npc.she]无力地试着将[npc2.namePos][npc2.tail(true)]从自己喉咙里拔出来。",
							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将头部从[npc2.namePos]令人憎恶的性器抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.tail+(true)]依然疯狂地在[npc.her]的喉咙里抽送爆操。",
							"[npc.name]拼命地尝试把头挪开，"
									+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.tail+(true)]依然贪婪地插入[npc.her]的喉咙深处。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到眼泪不住地涌上[npc.eyes]，难以抑制的哭意让[npc.she]发出一阵[npc.a_sob+]，"
									+ "[npc.she]无力地试着将[npc2.namePos][npc2.tail(true)]从自己喉咙里拔出来。",
							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将头部从[npc2.namePos]令人憎恶的性器抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.tail+(true)]依然粗暴地在[npc.her]的喉咙里抽送爆操。",
							"[npc.name]拼命地尝试把头挪开，"
									+ "[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.tail+(true)]依然激烈地插入[npc.her]的喉咙深处。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止吮吸尾巴";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.her]的[npc2.tail(true)]从你的喉咙里拔出来。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]的[npc2.tail(true)]从自己喉咙里抽出，[npc.she]愤怒地咆哮着，让[npc2.name]认清谁才是主导者。",
							"[npc.Name]倚靠在[npc2.name]身上，呼吸了一会[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]的[npc2.tail(true)]从自己喉咙里抽出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]的[npc2.tail(true)]从自己喉咙里抽出，[npc.she]发出一阵[npc.a_moan+]，让[npc2.name]明白[npc.she]很享受这个过程。",
							"[npc.Name]倚靠在[npc2.name]身上，呼吸了一会[npc2.her]的[npc2.scent]，然后将[npc2.her]的[npc2.tail(true)]从自己喉咙里抽出。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]松了一口气，但当[npc2.she]意识到[npc.name]还没完全满足时，又发出了一阵[npc2.a_sob+]。",
								"[npc2.name]发出一阵[npc2.a_sob+]，继续反抗并挣扎着，但[npc.name]依然牢牢地将[npc2.she]固定在原位。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]不再让[npc2.name]尾交自己的喉咙，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续尾交[npc.namePos]喉咙的渴望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction THROAT_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "收紧喉穴";
		}
		@Override
		public String getActionDescription() {
			return "用你肌肉发达的喉咙挤弄包裹着[npc2.namePos]的[npc2.tail(true)]。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getFaceOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]发出一阵[npc.a_moan+]，继续专心用[npc.her]肌肉极致发达的喉咙挤弄包裹着[npc2.namePos][npc2.tail+(true)]。",
					(!isTargetedCharacterInanimate()
						?"[npc.Name]发出一阵[npc.a_moan+]，继续专心控制[npc.her]喉咙内部极致发达的肌肉。"
							+ "[npc.Name]挤弄包裹着[npc2.namePos][npc2.tail+(true)]，让[npc2.herHim]不禁漏出一声愉悦的呻吟。"
						:""),
					"[npc.Name]以稳定的节奏[npc.moans]着，[npc.she]继续专心"
							+ "用[npc.her]肌肉极致发达的喉咙挤弄包裹着[npc2.namePos][npc2.tail+(true)]。",
					"[npc.Name]发出一阵[npc.a_moan+]，专心控制喉咙内里极致发达的肌肉，"
							+ "[npc.she]挤弄按摩着[npc2.namePos][npc2.tail+(true)]时，不禁愉悦地尖叫一声。");
		}
	};
}
