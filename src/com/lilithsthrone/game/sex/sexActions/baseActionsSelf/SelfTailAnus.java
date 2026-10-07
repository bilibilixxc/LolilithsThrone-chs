package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

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
 * @since 0.1.79
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfTailAnus {
	
	public static final SexAction SELF_TAIL_ANUS_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "尾巴肛交(自己)";
		}

		@Override
		public String getActionDescription() {
			return "开始用[npc.tail+]操自己的[npc.ass]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]将[npc.tail]伸向[npc.ass]，尾巴尖挑逗着[npc.asshole+]，愉悦地[npc.moaning]着，贯入自己的身体。",
					"[npc.Name]将[npc.tail]伸向[npc.ass]，愉悦地[npc.moaning]，强行突入[npc.her]那诱人的[npc.asshole]。",
					"[npc.her][npc.tail+]尖尖蹭着被忽视的[npc.asshole]，突然深贯而入，[npc.name]溢出[npc.a_moan+]，开始自我尾交。",
					"[npc.Name]急切地将[npc.tail+]深深塞进自己欲求不满的[npc.asshole]，迭声[npc.moans+]，开始自我尾交。");
		}
	};
	
	public static final SexAction DOM_SELF_TAIL_ANUS_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "尾巴肛交(温柔)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地用[npc.tail+]操自己的[npc.ass]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.tail]深入自己[npc.asshole+]，[npc.lips+]间发出[npc.moan]。",
					"[npc.name]用[npc.tail]在自己[npc.asshole+]里温柔地抽插，开始发出一连串愉悦的[npc.moans]，慢慢操起自己的[npc.ass]。",
					"[npc.Name]缓缓地将[npc.tail]插入自己[npc.asshole]的深处，发出了轻微的呜咽声，[npc.she]"
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
							? "温柔地用它按摩着自己的前列腺。"
							: "温柔地操干自己[npc.ass+]。"),
					"[npc.name]专心取悦起[npc.her][npc.ass+]，温柔地用[npc.tail]在[npc.her][npc.asshole+]中抽插。");
		}
		
	};
	
	public static final SexAction DOM_SELF_TAIL_ANUS_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "尾巴肛交(自己)";
		}

		@Override
		public String getActionDescription() {
			return "专心用[npc.tail+]操自己的[npc.ass]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.tail]贪婪地深入自己[npc.asshole+]，[npc.lips+]间发出[npc.moan]。",
					"[npc.name]用[npc.tail]在自己[npc.asshole+]里抽插着，开始发出一连串愉悦的[npc.moans]，慢慢操起自己的[npc.ass]。",
					"[npc.Name]将[npc.tail]深深插入自己的[npc.asshole]，不禁发出一阵[npc.a_moan]，[npc.she]"
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
							? "用它按摩着自己的前列腺。"
							: "不断地操干自己[npc.ass+]。"),
					"[npc.Name]专心取悦起[npc.her][npc.ass+]，用[npc.tail]在[npc.her][npc.asshole+]中抽插。");
		}
	};
	
	public static final SexAction DOM_SELF_TAIL_ANUS_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "尾巴肛交(粗暴)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地用[npc.tail+]操自己的[npc.ass]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.tail]深入那[npc.asshole+]，[npc.lips+]间发出[npc.moan]，然后粗暴地操起自己的[npc.ass]。",
					"[npc.name]用[npc.tail]在自己[npc.asshole+]里粗暴地抽插着，开始发出一连串愉悦的[npc.moans]，无情地操起自己的[npc.ass]。",
					"[npc.Name]激烈地将[npc.tail]深深插入自己的[npc.asshole]，不禁发出一阵[npc.a_moan]，[npc.she]"
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
							? "开始粗暴地用它按摩自己的前列腺。"
							: "粗暴地操干自己[npc.ass+]。"),
					"[npc.Name]专心取悦起[npc.her][npc.ass+]，粗暴地用[npc.tail]在[npc.her][npc.asshole+]中抽插。");
		}

	};
	
	public static final SexAction SUB_SELF_TAIL_ANUS_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "尾巴肛交(自己)";
		}

		@Override
		public String getActionDescription() {
			return "专心用[npc.tail+]操自己的[npc.ass]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.tail]贪婪地深入自己[npc.asshole+]，[npc.lips+]间发出[npc.moan]。",
					"[npc.name]用[npc.tail]在自己[npc.asshole+]里抽插着，开始发出一连串愉悦的[npc.moans]，慢慢操起自己的[npc.ass]。",
					"[npc.Name]将[npc.tail]深深插入自己的[npc.asshole]，不禁发出一阵[npc.a_moan]，[npc.she]"
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
							? "用它按摩着自己的前列腺。"
							: "不断地操干自己[npc.ass+]。"),
					"[npc.Name]专心取悦起[npc.her][npc.ass+]，用[npc.tail]在[npc.her][npc.asshole+]中抽插。");
		}
	};
	
	public static final SexAction SUB_SELF_TAIL_ANUS_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "尾巴肛交(渴求)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "开始急切地用[npc.tail+]操自己的[npc.ass]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.tail]深入那[npc.asshole+]，[npc.lips+]间发出[npc.moan]，然后狂乱地操起自己的[npc.ass]。",
					"[npc.name]用[npc.tail]在自己[npc.asshole+]里热情地抽插着，开始发出一连串愉悦的[npc.moans]，疯狂地操起自己的[npc.ass]。",
					"[npc.Name]饥渴地将[npc.tail]深深插入自己的[npc.asshole]，不禁发出一阵[npc.a_moan]，[npc.she]"
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
							? "开始急切地用它按摩自己的前列腺。"
							: "急切地操干自己[npc.ass+]。"),
					"[npc.Name]专心取悦起[npc.her][npc.ass+]，急切地用[npc.tail]在[npc.her][npc.asshole+]中抽插。");
		}
	};
	
	public static final SexAction SELF_TAIL_ANUS_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "停止尾巴肛交(自己)";
		}

		@Override
		public String getActionDescription() {
			return "停止用[npc.her]的[npc.tail]操自己。";
		}

		@Override
		public String getDescription() {
			return "[npc.name][npc.a_groan+]着，将[npc.tail+]顶进自己[npc.asshole+]。";
		}
	};
}
