package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
 * @since 0.1.88
 * @version 0.2.8
 * @author Innoxia
 */
public class TongueAnus {

	public static final SexAction ANILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "开始吻肛";
		}

		@Override
		public String getActionDescription() {
			return "把你的[npc.tongue]伸进[npc2.namePos][npc2.asshole+]，开始吻肛。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]将[npc.her][npc.lips+]压向[npc2.namePos][npc2.assCloaca+]，并给予[npc2.her]屁股一长串轻柔的吻，"
									+"随后缓慢但又坚定地将[npc.tongue+]伸进[npc2.her][npc2.asshole+]。",

							"[npc.Name]绵长而湿润地舔吮着[npc2.asshole+]，随后温柔地将[npc.tongue+]向更深处推进，给予[npc2.namePos][npc2.assCloaca+]一长串轻柔的吻。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]饥渴地将[npc.her][npc.lips+]压向[npc2.namePos][npc2.assCloaca+]，给予[npc2.her]屁股一长串热情的吻，"
									+"随后饥渴地将[npc.tongue+]伸入[npc2.her][npc2.asshole+]。",

							"[npc.Name]饥渴地舔吮着[npc2.asshole+]，随后贪婪地将[npc.tongue+]推向更深处，给予[npc2.namePos][npc2.assCloaca+]一长串热情的吻。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗暴地在[npc2.namePos][npc2.assCloaca+]摩擦[npc.her][npc.lips+]，给予[npc2.her]的屁股一长串有力的吻，"
									+"随后贪婪地将[npc.tongue+]伸入[npc2.her][npc2.asshole+]。",

							"[npc.Name]粗暴地舔吮着[npc2.her][npc2.asshole+]，随后给予[npc2.namePos][npc2.assCloaca+]一长串有力的吻，贪婪地将[npc.tongue+]向更深处推进。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]饥渴地将[npc.her][npc.lips+]压向[npc2.namePos][npc2.assCloaca+]，给予[npc2.her]屁股一长串热情的吻，"
									+"随后饥渴地将[npc.tongue+]伸入[npc2.her][npc2.asshole+]。",

							"[npc.Name]饥渴地舔吮着[npc2.asshole+]，随后贪婪地将[npc.tongue+]推向更深处，给予[npc2.namePos][npc2.assCloaca+]一长串热情的吻。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]将[npc.her][npc.lips+]压向[npc2.namePos][npc2.assCloaca+]，给予[npc2.her]屁股一长串的吻，随后将[npc.tongue+]伸进[npc2.her][npc2.asshole+]。",

							"[npc.Name]绵长而湿润地舔吮[npc2.asshole+]，随后将[npc.tongue+]向更深处推进，长吻着[npc2.namePos][npc2.assCloaca+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出轻柔的[npc2.moan]作为回应，温柔地将[npc2.assCloaca]压向[npc.namePos]的[npc.face]，期待着对方继续做下去。",
	
								"[npc2.name]将[npc2.assCloaca]温柔地抵在[npc.namePos][npc.face]上，回应起口交，大声地[npc2.moanVerb]，祈求[npc.herHim]继续做下去。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]作为回应，渴望地将[npc2.her][npc2.assCloaca]压向[npc.namePos][npc.face]以祈求[npc.herHim]继续做下去。",
	
								"[npc2.name]将[npc2.assCloaca]急切地抵在[npc.namePos][npc.face]上，回应起口交，大声地[npc2.moanVerb]，祈求[npc.herHim]继续做下去。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]作为回应，粗暴地将[npc2.her][npc2.assCloaca]推向[npc.namePos][npc.face]以命令[npc.herHim]继续做下去。",
	
								"[npc2.name]粗暴地将[npc2.assCloaca]推向[npc.namePos]的[npc.face]，饥渴地回应起口交，大声地[npc2.moanVerb]，要求[npc.herHim]继续做下去。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]作为回应，渴望地将[npc2.her][npc2.assCloaca]压向[npc.namePos][npc.face]以祈求[npc.herHim]继续做下去。",
	
								"[npc2.name]将[npc2.assCloaca]急切地抵在[npc.namePos][npc.face]上，回应起口交，大声地[npc2.moanVerb]，祈求[npc.herHim]继续做下去。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]作为回应，将[npc2.her][npc2.assCloaca]压向[npc.namePos][npc.face]以祈求[npc.herHim]继续做下去。",
	
								"[npc2.name]将[npc2.assCloaca]抵在[npc.namePos][npc.face]上，回应起口交，大声地[npc2.moanVerb]，祈求[npc.herHim]继续做下去。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]拼命地尝试扭动远离[npc.namePos]的嘴巴接触，[npc2.she][npc2.sobbing]扭动，乞求放过自己。",
	
								"[npc2.namePos]嘴间爆发出一阵[npc2.A_sob+]，[npc2.she]一边反抗着[npc.Name]，一边乞求[npc.Name]将[npc.tongue]从[npc2.her]的菊穴挪开。"));
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
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]急切地将[npc2.hips]向后压，"
									+ "[npc2.she]用[npc2.asshole+]压住[npc.namePos][npc.lips+]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]急切地用[npc2.hips]压向[npc.namePos]的[npc.face]，乞求[npc.Name]继续侍奉[npc2.her]的[npc2.assCloaca]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地用[npc2.assCloaca+]磨蹭[npc.namePos]的[npc.face]，"
									+ "然后急切地请求[npc.herHim]将[npc.tongue+]尽可能深地伸入[npc2.her][npc2.asshole+]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.assCloaca]从[npc.namePos]那讨厌的[npc.tongue]下缩回，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然亲吻舔弄[npc2.her][npc2.asshole+]。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地试图远离[npc.namePos]的[npc.tongue]，"
									+ "[npc.name]不断舔弄着[npc2.her][npc2.asshole+]，[npc2.she]奋力反抗着[npc.name]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]温柔地将[npc2.hips]向后压，"
									+ "[npc2.she]发出一声轻柔的[npc2.moan]，将[npc2.asshole+]压向[npc.namePos][npc.lips+]。",
		
							" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
									+ "[npc2.she]温柔地用[npc2.hips]压向[npc.namePos]的[npc.face]，乞求[npc.Name]继续侍奉[npc2.her]的[npc2.assCloaca]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，轻柔地用[npc2.assCloaca+]磨蹭[npc.namePos]的[npc.face]，"
									+ "然后急切地请求[npc.herHim]将[npc.tongue+]尽可能深地伸入[npc2.her][npc2.asshole+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]粗暴地将[npc2.hips]往后推，"
									+ "[npc2.she]用[npc2.asshole+]蹭着[npc.namePos][npc.lips+]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]粗鲁地用[npc2.hips]碾向[npc.namePos]的[npc.face]，命令[npc.Name]继续侍奉[npc2.her]的[npc2.assCloaca]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，激烈地用[npc2.assCloaca+]磨蹭[npc.namePos]的[npc.face]，"
									+ "然后积极地命令[npc.herHim]继续用[npc.tongue+]尽可能深入[npc2.her][npc2.asshole+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]将[npc2.hips]向后压，"
									+ "[npc2.she]用[npc2.asshole+]压向[npc.namePos][npc.lips+]，发出一阵[npc2.a_moan]。",
		
							"[npc2.namePos][npc2.lips+]间飘出一阵[npc2.A_moan+]，"
									+ "[npc2.she]用[npc2.hips]压向[npc.namePos]的[npc.face]，乞求[npc.Name]继续侍奉[npc2.her]的[npc2.assCloaca]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.assCloaca+]磨蹭[npc.namePos]的[npc.face]，"
									+ "然后请求[npc.herHim]将[npc.tongue+]尽可能深地伸入[npc2.her][npc2.asshole+]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction ANILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "吻肛(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地舔弄[npc2.namePos][npc2.asshole+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"用[npc.tongue+]尽可能温柔地深入[npc2.namePos][npc2.assCloaca+]，"
							+ "[npc.name]将[npc.her][npc.lips+]压向[npc2.her][npc2.asshole+]，并发出了一声低沉的[npc.moan]。",

					"将[npc.tongue+]从[npc2.namePos][npc2.asshole+]中收回，[npc.name]开始温柔地舔吻着[npc2.namePos][npc2.assCloaca+]，"
							+ "随后[npc.her][npc.tongue]再次缓慢地滑入[npc2.her][npc2.asshole+]。",

					"[npc.name]将[npc.tongue+]从[npc2.namePos][npc2.asshole+]中抽出，开始慢慢地亲吻摩擦[npc2.namePos][npc2.assCloaca+]，"
							+ "然后身体前倾，温柔地将[npc.tongue]深深插入[npc2.her][npc2.asshole+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "吻肛";
		}

		@Override
		public String getActionDescription() {
			return "继续在[npc2.namePos][npc2.asshole+]里抽送你的[npc.tongue]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"用[npc.tongue+]尽可能急切地深入[npc2.namePos][npc2.assCloaca+]，"
							+ "[npc.name]将[npc.her][npc.lips+]压向[npc2.her][npc2.asshole+]，并发出了一声低沉的[npc.moan]。",

					"将[npc.tongue+]从[npc2.namePos][npc2.asshole+]中收回，[npc.name]开始急切地舔吻着[npc2.namePos][npc2.assCloaca+]，"
							+ "随后[npc.her][npc.tongue]再次贪婪地滑入[npc2.her][npc2.asshole+]。",

					"[npc.name]将[npc.tongue+]从[npc2.namePos][npc2.asshole+]中抽出，开始开心地亲吻摩擦[npc2.namePos][npc2.assCloaca+]，"
							+ "然后身体前倾，热情地将[npc.tongue]深深插入[npc2.her][npc2.asshole+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "吻肛(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地将你的舌头伸入[npc2.namePos][npc2.asshole+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"粗暴地用[npc.tongue+]尽可能深入[npc2.namePos][npc2.assCloaca+]，"
							+ "[npc.name]将[npc.her][npc.lips+]蹭向[npc2.her][npc2.asshole+]，并发出了一声低沉的[npc.moan]。",

					"将[npc.tongue+]从[npc2.namePos][npc2.asshole+]中收回，[npc.name]开始粗暴地舔吻着[npc2.namePos][npc2.assCloaca+]，"
							+ "随后[npc.tongue]再次粗暴地滑入[npc2.her][npc2.asshole+]中。",

					"[npc.name]将[npc.tongue+]从[npc2.namePos][npc2.asshole+]中抽出，开始激烈地亲吻摩擦[npc2.namePos][npc2.assCloaca+]，"
							+ "然后身体前倾，粗暴地将[npc.tongue]深深插入[npc2.her][npc2.asshole+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ANILINGUS_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗吻肛";
		}

		@Override
		public String getActionDescription() {
			return "努力把你的[npc.tongue]从[npc2.namePos][npc2.asshole+]里拔出来。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]试图把[npc.face]移开，但[npc2.name]继续将[npc2.asshole+]温柔地压在[npc.namePos][npc.lips+]上，"
									+ "[npc2.name]牢牢地将[npc.herHim]固定在原位，紧紧贴着[npc.herHim]。",

							"伴随着一声[npc.a_sob+]，[npc.Name]试着推开[npc2.name]，但[npc2.name]迅速地将[npc.her]的[npc.face]拉回了自己[npc2.assCloaca+]，"
									+ "完全无视了[npc.her]的挣扎，温柔地磨蹭着[npc.herHim]。",

							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把嘴从[npc2.namePos][npc2.assCloaca+]边挪开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边将[npc2.asshole+]压向[npc.her][npc.lips+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]试图把[npc.face]移开，但[npc2.name]继续将[npc2.asshole+]粗暴地压在[npc.namePos][npc.lips+]上，"
									+ "[npc2.name]牢牢地将[npc.herHim]固定在原位，粗暴地贴着[npc.herHim]。",

							"伴随着一声[npc.a_sob+]，[npc.Name]试着推开[npc2.name]，但[npc2.name]猛烈地将[npc.her]的[npc.face]拉回了自己[npc2.assCloaca+]，"
									+ "完全无视了[npc.her]的挣扎，粗暴地磨蹭着[npc.herHim]。",

							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把嘴从[npc2.namePos][npc2.assCloaca+]边挪开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边粗暴地将[npc2.asshole+]压向[npc.her][npc.lips+]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]试图把[npc.face]移开，但[npc2.name]继续将[npc2.asshole+]急切地压在[npc.namePos][npc.lips+]上，"
									+ "[npc2.name]牢牢地将[npc.herHim]固定在原位，紧紧贴着[npc.herHim]。",

							"伴随着一声[npc.a_sob+]，[npc.Name]试着推开[npc2.name]，但[npc2.name]迅速地将[npc.her]的[npc.face]拉回了自己[npc2.assCloaca+]，"
									+ "完全无视了[npc.her]的挣扎，急切地磨蹭着[npc.herHim]。",

							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把嘴从[npc2.namePos][npc2.assCloaca+]边挪开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边急切地将[npc2.asshole+]压向[npc.her][npc.lips+]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "吻肛";
		}

		@Override
		public String getActionDescription() {
			return "继续在[npc2.namePos][npc2.asshole+]里抽送你的[npc.tongue]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"用[npc.tongue+]尽可能深入[npc2.namePos][npc2.assCloaca+]，"
							+ "[npc.name]将[npc.her][npc.lips+]压向[npc2.her][npc2.asshole+]，并发出了一声低沉的[npc.moan]。",

					"将[npc.tongue+]从[npc2.namePos][npc2.asshole+]中收回，[npc.name]开始舔吻着[npc2.namePos][npc2.assCloaca+]，"
							+ "随后[npc.her][npc.tongue]再次滑入[npc2.her][npc2.asshole+]。",

					"[npc.name]将[npc.tongue+]从[npc2.namePos][npc2.asshole+]中抽出，开始亲吻摩擦[npc2.namePos][npc2.assCloaca+]，"
							+ "然后身体前倾，将[npc.tongue]深深插入[npc2.her][npc2.asshole+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "吻肛(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地在[npc2.namePos][npc2.asshole+]里抽送你的[npc.tongue]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"用[npc.tongue+]尽可能急切地深入[npc2.namePos][npc2.assCloaca+]，"
							+ "[npc.name]将[npc.her][npc.lips+]压向[npc2.her][npc2.asshole+]，并发出了一声低沉的[npc.moan]。",

					"将[npc.tongue+]从[npc2.namePos][npc2.asshole+]中收回，[npc.name]开始急切地舔吻着[npc2.namePos][npc2.assCloaca+]，"
							+ "随后[npc.her][npc.tongue]再次贪婪地滑入[npc2.her][npc2.asshole+]。",

					"[npc.name]将[npc.tongue+]从[npc2.namePos][npc2.asshole+]中抽出，开始开心地亲吻摩擦[npc2.namePos][npc2.assCloaca+]，"
							+ "然后身体前倾，热情地将[npc.tongue]深深插入[npc2.her][npc2.asshole+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ANILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止吻肛";
		}

		@Override
		public String getActionDescription() {
			return "把你的[npc.tongue]从[npc2.namePos][npc2.asshole+]里缩回来，停止吻肛。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"以一个粗暴的舔舐作为结束，随后[npc.name]把[npc.her]的[npc.face]从[npc2.namePos][npc2.asshole+]旁移开了。",
	
							"给了[npc2.namePos][npc2.asshole+]一个粗暴的亲吻作为结束，[npc.name]将[npc.her]的[npc.face]从[npc2.her][npc2.assCloaca+]旁移开了。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"以一个舔舐作为结束，随后[npc.name]把[npc.her]的[npc.face]从[npc2.namePos][npc2.asshole+]旁移开了。",
	
							"给了[npc2.namePos][npc2.asshole+]一个湿润的亲吻作为结束，[npc.name]将[npc.her]的[npc.face]从[npc2.her][npc2.assCloaca+]旁移开了。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]继续挣扎着，[npc2.sobbing]并不舒服地扭动，[npc2.she]意识到[npc.Name]还没有完全和[npc2.herHim]结束。",
		
								"[npc2.name]意识到[npc.she]还没有完全放过自己，继续挣扎[npc2.sobbing]，"
										+ "[npc2.she]恳求[npc.name]放过自己，泪水如小溪般从[npc2.her]的[npc2.face]上流下。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]将[npc.tongue+]从[npc2.namePos][npc2.assCloaca+]挪开，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
								
								"[npc.Name]不再舔弄[npc2.namePos][npc2.asshole+]，[npc2.namePos][npc2.lips+]间飘出一阵[npc2.A_moan+]，暴露了[npc2.she]渴望得到[npc.namePos]的更多关注。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction RECEIVING_ANILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "接受吻肛";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]开始舔你[npc.asshole+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"将[npc.her][npc.assCloaca+]轻轻地压在[npc2.namePos]的[npc2.face]，"
									+ "[npc.Name]开始缓慢地用[npc.her][npc.asshole+]摩擦[npc2.her][npc2.lips+]，发出一阵柔软的[npc.moan]。",

							"[npc2.name]移动[npc.her][npc.hips]以便自己的[npc2.face]能强行深入[npc.her][npc.assCloaca+]，"
									+ "[npc.name]开始温柔地将[npc.her][npc.asshole+]压向[npc2.her][npc2.lips+]，发出一阵柔软的[npc.moan]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"将[npc.her][npc.assCloaca+]急切地压在[npc2.namePos]的[npc2.face]，"
									+ "[npc.Name]开始拼命地用[npc.her][npc.asshole+]摩擦[npc2.her][npc2.lips+]，发出一阵[npc.a_moan+]。",

							"[npc2.name]移动[npc.her][npc.hips]以便自己的[npc2.face]能强行深入[npc.her][npc.assCloaca+]，"
									+ "[npc.name]开始饥渴地将[npc.her][npc.asshole+]压向[npc2.her][npc2.lips+]，发出一阵[npc.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将自己[npc.assCloaca+]装向[npc2.namePos][npc2.face]，"
									+ "[npc.Name]激烈地将[npc.her][npc.asshole+]在[npc2.her][npc2.lips+]上摩擦，发出一阵[npc.a_moan+]。",

							"[npc2.name]移动[npc.her][npc.hips]以便自己的[npc2.face]能强行深入[npc.her][npc.assCloaca+]，"
									+ "[npc.name]开始粗暴地将[npc.her][npc.asshole+]在[npc2.her][npc2.lips+]上摩擦，发出一阵[npc.a_moan+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"将[npc.her][npc.assCloaca+]急切地压在[npc2.namePos]的[npc2.face]，"
									+ "[npc.Name]开始拼命地用[npc.her][npc.asshole+]摩擦[npc2.her][npc2.lips+]，发出一阵[npc.a_moan+]。",

							"[npc2.name]移动[npc.her][npc.hips]以便自己的[npc2.face]能强行深入[npc.her][npc.assCloaca+]，"
									+ "[npc.name]开始饥渴地将[npc.her][npc.asshole+]压向[npc2.her][npc2.lips+]，发出一阵[npc.a_moan+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"把[npc.her][npc.assCloaca+]压在[npc2.namePos][npc2.face]上，"
									+ "[npc.Name]开始用[npc.her][npc.asshole+]在[npc2.her][npc2.lips+]上摩擦，发出一阵[npc.a_moan+]。",

							"[npc2.name]移动[npc.her][npc.hips]以便自己的[npc2.face]能强行深入[npc.her][npc.assCloaca+]，"
									+ "[npc.name]开始将[npc.her][npc.asshole+]压向[npc2.her][npc2.lips+]，发出一阵[npc.a_moan+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]缓慢地将[npc2.her][npc2.tongue+]滑入[npc.namePos][npc.asshole+]，温柔地舔舐亲吻[npc.her][npc.assCloaca+]，发出一阵沉闷的[npc2.moan]。",
	
								"[npc2.name]温柔地将[npc2.tongue]滑出，将[npc2.her][npc2.lips+]压向[npc.assCloaca+]，留下一个长而缓慢的舔舐，"
										+ "[npc2.name]开始在[npc.namePos][npc.asshole+]留下一系列温柔的吻，发出一阵沉闷的[npc2.moan]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]贪婪地将[npc2.her][npc2.tongue+]滑入[npc.namePos][npc.asshole+]，开始开心地舔舐亲吻[npc.her][npc.assCloaca+]，发出一阵沉闷的[npc2.moan]。",
	
								"贪婪地用[npc2.tongue]滑过，将[npc.assCloaca+]压向[npc2.her][npc2.lips+]，留下一串悠长湿润的舔舐，"
										+ "[npc2.name]在[npc.namePos][npc.asshole+]留下一串热情的吻，发出一阵沉闷的[npc2.moan]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]激烈地将[npc2.tongue+]插入[npc.namePos][npc.asshole+]，"
										+ "[npc2.she]粗暴地舔舐亲吻[npc.her][npc.assCloaca+]，发出一阵沉闷的[npc2.moan]。",
	
								"贪婪地将[npc2.her][npc2.tongue]推出，压向[npc2.her][npc2.lips+]，留下一串粗糙湿润的舔舐，"
										+ "[npc2.name]开始在[npc.namePos][npc.asshole+]留下一串有力地亲吻，发出一阵沉闷的[npc2.moan]。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]贪婪地将[npc2.her][npc2.tongue+]滑入[npc.namePos][npc.asshole+]，开始开心地舔舐亲吻[npc.her][npc.assCloaca+]，发出一阵沉闷的[npc2.moan]。",
	
								"贪婪地用[npc2.tongue]滑过，将[npc.assCloaca+]压向[npc2.her][npc2.lips+]，留下一串悠长湿润的舔舐，"
										+ "[npc2.name]在[npc.namePos][npc.asshole+]留下一串热情的吻，发出一阵沉闷的[npc2.moan]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name][npc2.tongue+]滑过[npc.namePos][npc.asshole+]，开始舔舐亲吻[npc.her][npc.assCloaca+]，发出一阵沉闷的[npc2.moan]。",
	
								"[npc2.her]将[npc.assCloaca+]压向[npc2.her]，[npc2.tongue]在其上留下遗传悠长湿润的舔舐，"
										+ "[npc2.name]开始在[npc.namePos][npc.asshole+]留下一系列亲吻，发出一阵沉闷的[npc2.moan]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]在绝望中挣扎，[npc2.her][npc2.lips+]被强迫压向[npc.Name][npc.asshole+]，发出一阵[npc2.a_sob+]。",
	
								"[npc2.namePos]在绝望中挣扎[npc2.sobbing]，在面对[npc.Name]将[npc.her][npc.assCloaca+]压向自己的[npc2.face]时的挣扎毫无用处。"));
						break;
					default:
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
							"[npc2.Name]贪婪地将自己[npc2.tongue+]深深插入[npc.namePos][npc.asshole+]，"
									+ "[npc2.she]发出一声含糊不清的[npc2.moan]，将[npc2.lips+]压向[npc.namePos][npc.assCloaca+]。",
		
							"[npc2.namePos]嘴里流出一声低沉的[npc2.moan]，"
									+ "并且，饥渴地将[npc2.her][npc2.lips+]压向[npc.namePos][npc.assCloaca+]，开始将[npc2.her][npc2.tongue+]深入[npc.namePos][npc.asshole+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地用[npc2.lips+]压向[npc.namePos]的[npc.assCloaca]，"
									+ "饥渴地将[npc.her][npc.tongue+]尽可能深的探入[npc.her][npc.asshole+]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.face]从[npc.namePos]的[npc.assCloaca]边移开，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然将自己[npc.assCloaca+]压向[npc2.her][npc2.face+]。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试远离[npc.namePos]的[npc.assCloaca]，"
									+ "[npc.name]将[npc.asshole+]压向[npc2.her][npc2.lips+]，[npc2.she]奋力反抗着[npc.name]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将[npc2.tongue+]滑入[npc.namePos][npc.asshole+]深处，"
									+ "[npc2.she]发出一声含糊不清的柔和[npc2.moan]，将[npc2.lips+]压向[npc.namePos][npc.assCloaca+]。",
		
							"[npc2.namePos]的口中飘出一声含糊不清的[npc2.moan]，"
									+ "[npc2.she]温柔地将[npc2.lips+]压向[npc.namePos][npc.assCloaca+]，开始缓慢地将[npc2.tongue+]深深插入[npc.namePos][npc.asshole+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地用[npc2.lips+]压向[npc.namePos]的[npc.assCloaca]，"
									+ "随后温柔地将[npc2.her][npc2.tongue+]尽可能深地探入[npc.her][npc.asshole+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.tongue+]深深插入[npc.namePos][npc.asshole+]，"
									+ "[npc2.she]发出一声含糊不清的[npc2.moan]，将[npc2.lips+]压向[npc.namePos][npc.assCloaca+]。",
		
							"[npc2.namePos]的口中飘出一声含糊不清的[npc2.moan]，"
									+ "[npc2.she]粗鲁地将[npc2.lips+]压向[npc.namePos][npc.assCloaca+]，开始粗暴地将[npc2.tongue+]深深插入[npc.namePos][npc.asshole+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，激烈地用[npc2.lips+]压向[npc.namePos]的[npc.assCloaca]，"
									+ "然后积极地用[npc2.her][npc2.tongue+]尽可能深入[npc.her][npc.asshole+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.tongue+]深深插入[npc.namePos][npc.asshole+]，"
									+ "[npc2.she]发出一声含糊不清的[npc2.moan]，将[npc2.lips+]压向[npc.namePos][npc.assCloaca+]。",
		
							"[npc2.namePos]嘴里流出一声低沉的[npc2.moan]，"
									+ "[npc2.she]将[npc2.lips+]压向[npc.namePos][npc.assCloaca+]，开始将[npc2.tongue+]深深插入[npc.namePos][npc.asshole+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始将[npc2.lips+]压向[npc.namePos]的[npc.assCloaca]，"
									+ "然后用[npc2.her][npc2.tongue+]尽可能深入[npc.her][npc.asshole+]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RECEIVING_ANILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "接受吻肛(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地将你[npc.assCloaca+]压向[npc2.namePos]的脸，让[npc2.her][npc2.tongue+]伸进你[npc.asshole+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"将[npc.her][npc.assCloaca+]轻轻地压到[npc2.namePos]的[npc2.face+]，"
							+ "让[npc2.namePos][npc2.lips+]坚定的抵向自己[npc.asshole+]，发出一阵轻柔地[npc.moan]。",

					"[npc.Name]轻声[npc.moan]，将[npc.assCloaca+]压向[npc2.namePos][npc2.face+]，温柔地用自己[npc.asshole+]磨蹭[npc2.her][npc2.lips+]。",

					"[npc.Name]温柔地将[npc.her][npc.asshole+]在[npc2.namePos][npc2.lips]上摩擦，发出一阵柔软的[npc.moan]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "接受吻肛";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地将你[npc.assCloaca+]压向[npc2.namePos]的脸，让[npc2.her][npc2.tongue+]伸进你[npc.asshole+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"将[npc.her][npc.assCloaca+]急切地压到[npc2.namePos]的[npc2.face]，"
							+ "[npc.Name]坚定的将[npc.her][npc.asshole+]抵向[npc2.her][npc2.lips+]，发出一阵[npc.a_moan+]。.",

					"[npc.Name][npc.moan]，饥渴地将[npc.assCloaca+]压向[npc2.namePos][npc2.face+]，用自己[npc.asshole+]磨蹭[npc2.her][npc2.lips+]。",

					"[npc.Name]饥渴地将[npc.her][npc.asshole+]压向[npc2.namePos][npc2.lips]，发出一阵[npc.a_moan+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "接受吻肛(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地用你[npc.asshole+]磨蹭[npc2.namePos][npc2.tongue+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.assCloaca+]粗暴地压在[npc2.face+]上，"
							+ "[npc.Name]粗暴地将[npc.her][npc.asshole+]冲向[npc2.her][npc2.lips+]，发出一阵[npc.a_moan+]。",

					"[npc.Name][npc.moan]，粗暴地将[npc.assCloaca+]压向[npc2.namePos][npc2.face+]，激烈地用自己[npc.asshole+]磨蹭[npc2.her][npc2.lips+]。",

					"[npc.Name]粗暴地将[npc.her][npc.asshole+]在[npc2.namePos][npc2.lips]摩擦，发出一阵[npc.a_moan+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "接受吻肛(抗拒)";
		}

		@Override
		public String getActionDescription() {
			return "努力让你[npc.asshole+]远离[npc2.namePos][npc2.tongue+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name][npc.a_sob+]着，绝望地试图将[npc.her]的[npc.assCloaca]从[npc2.namePos]的[npc2.face]上拉开，但失败了。"
									+ "发出一阵[npc.a_sob+]，[npc2.name]温柔地将[npc2.her][npc2.tongue]滑过[npc.her][npc.asshole+]。",

							"[npc.Name]急切地试图从[npc2.namePos][npc2.lips+]移开自己[npc.asshole+]，发出一阵[npc.a_sob+]。"
									+ "[npc2.name]无视[npc.her]的抵抗，将[npc.Name]固定，在[npc.her][npc.assCloaca+]留下一个柔软的吻，"
									+"随后温柔地将[npc2.her][npc2.tongue+]伸入[npc.her][npc.asshole+]。",

							"随着一阵[npc.a_sob+]，[npc.Name]拼命地尝试将自己[npc.assCloaca+]移开[npc2.namePos][npc2.lips+]，但被牢牢固定，"
									+ "[npc2.she]忽视抵抗，温柔地将[npc2.her][npc2.tongue]挺入[npc.her][npc.asshole+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name][npc.a_sob+]着，绝望地试图将[npc.her]的[npc.assCloaca]从[npc2.namePos]的[npc2.face]上拉开，但失败了。"
									+ "发出一阵[npc.a_sob+]，[npc2.name]粗暴地将[npc2.tongue]深入[npc.her][npc.asshole+]。",

							"[npc.Name]急切地试图从[npc2.namePos][npc2.lips+]移开自己[npc.asshole+]，发出一阵[npc.a_sob+]。"
									+ "[npc2.name]无视[npc.her]的抵抗，将其固定，在[npc.her][npc.assCloaca+]留下一个湿润的吻，"
									+"随后粗暴地将[npc2.her][npc2.tongue+]伸入[npc.her][npc.asshole+]。",

							"随着一阵[npc.a_sob+]，[npc.Name]拼命地试图将[npc2.namePos][npc2.lips+]从自己[npc.assCloaca+]上移开，但被牢牢固定，"
									+ "[npc2.she]忽视[npc.her]的抵抗，继续粗暴地将[npc2.tongue]挺进[npc.her][npc.asshole+]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name][npc.a_sob+]着，绝望地试图将[npc.her]的[npc.assCloaca]从[npc2.namePos]的[npc2.face]上拉开，但失败了。"
									+ "发出一阵[npc.a_sob+]，[npc2.name]贪婪地将[npc2.tongue]深入[npc.her][npc.asshole+]。",

							"[npc.Name]急切地试图从[npc2.namePos][npc2.lips+]移开自己[npc.asshole+]，发出一阵[npc.a_sob+]。"
									+"但被[npc2.name]无视反抗摁住，热情地吻着自己的[npc.assCloaca+]，"
									+"随后被[npc2.her][npc2.tongue+]贪婪地侵犯[npc.her][npc.asshole+]。",

							"随着一阵[npc.a_sob+]，[npc.Name]拼命地尝试将自己[npc.assCloaca+]移开[npc2.namePos][npc2.lips+]，但被牢牢固定，"
									+ "但被[npc2.name]无视反抗摁住，饥渴地用舌头侵犯着[npc.asshole+]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_ANILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "接受吻肛";
		}

		@Override
		public String getActionDescription() {
			return "将你[npc.asshole+]压向[npc2.namePos][npc2.face+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"将[npc.her][npc.assCloaca+]压到[npc2.namePos]的[npc2.face]，"
							+ "[npc.Name]坚定的将[npc.her][npc.asshole+]抵向[npc2.her][npc2.lips+]，发出一阵[npc.a_moan+]。.",

					"[npc.Name][npc.moan]，将[npc.assCloaca+]压向[npc2.namePos][npc2.face+]，用自己[npc.asshole+]磨蹭[npc2.her][npc2.lips+]。",

					"[npc.Name]发出[npc.a_moan+]，用[npc.asshole+]趁着[npc2.namePos][npc2.lips]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "接受吻肛(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地用你[npc.asshole+]磨蹭[npc2.namePos][npc2.tongue+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"将[npc.her][npc.assCloaca+]急切地压到[npc2.namePos]的[npc2.face]，"
							+ "[npc.Name]坚定的将[npc.her][npc.asshole+]抵向[npc2.her][npc2.lips+]，发出一阵[npc.a_moan+]。.",

					"[npc.Name]发出[npc.a_moan+]，饥渴地用[npc.assCloaca+]蹭着[npc2.namePos]npc2.face+]，贪婪地用其[npc.asshole+]摩擦[npc2.her][npc2.lips+]。",

					"[npc.Name]发出[npc.a_moan+]，饥渴地在[npc2.namePos][npc2.lips]上蹭着[npc.asshole+]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止接受吻肛";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.her][npc2.tongue+]从你[npc.asshole+]里拔出来。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]的龟头从自己[npc.asshole+]里抽出，命令[npc2.name]停止吻肛。",

							"粗暴地再一次在[npc2.namePos][npc2.face]上蹭了下[npc.asshole+]，[npc.Name]挪开了[npc.assCloaca]，结束了吻肛。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]的龟头从自己[npc.asshole+]里抽出，命令[npc2.name]停止吻肛。",

							"再一次在[npc2.namePos][npc2.face]上蹭了下[npc.asshole+]，[npc.Name]挪开了[npc.assCloaca]，结束了吻肛。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]意识到[npc.name]远未满足，控制不足眼泪，哀哭出声。",
	
								"[npc2.name]继续奋力反抗着[npc.Name]，[npc2.she]发出一阵[npc2.a_sob+]，乞求[npc.name]放过自己。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]不情愿地舔弄[npc.namePos][npc.asshole+]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
	
								"[npc2.name]看着[npc.Name]远离自己欲求不满地发出[npc2.a_moan+]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
