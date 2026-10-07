package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class RoseHandHolding {
	
	public static final SexAction PLAYER_HAND_MASSAGE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "手部按摩";
		}
		@Override
		public String getActionDescription() {
			return "按摩萝丝的手。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你牵起了萝丝的一只手，用[pc.fingers+]轻柔地按在她的手背上，对着她吹弹可破的肌肤揉搓、按摩着。",
					"你握住萝丝的手，缓慢、镇定地对着她的手轻轻按摩起来。",
					"你我这萝丝的手，慢慢地施加上一点压力，开始了一段精致的按摩，渐渐地便沉浸在她完美无暇皮肤的精妙触感中。",
					"你集中万分注意力，轻柔地按摩起萝丝的手来，在你接触到她如天使般柔嫩的皮肤时，不禁发出了一小声[pc.moans]。");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_INTERLOCKING_FINGERS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "十指相扣";
		}
		@Override
		public String getActionDescription() {
			return "与萝丝的手十指相扣。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你牵起萝丝的手，两人的手指交叉起来，接着你便色眯眯地轻轻握住了她的手。",
					"你用一个流畅缓和的动作，握住了萝丝的手，将手指偷偷滑进她的指缝之间，直到紧紧地扣在其中。",
					"发出一声清楚的[pc.moan]后，你将手指滑进了萝丝的指缝间，十分淘气地紧紧握住了她的手。",
					"你色兮兮地伸出手，一下子抓住了萝丝那双完美的纤手，接着便十指相扣，你拼尽全力才压制住即将脱口而出的喘息。");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_NAIL_FOCUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "揉捏指甲";
		}
		@Override
		public String getActionDescription() {
			return "轻柔地揉捏萝丝的指甲。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"萝丝的指甲上印着一抹淡粉色，你意识到自己不受控制地想让指尖在其美丽无暇的表面上滑过。",
					"你用指尖在萝丝对应手指的指甲上缓慢滑动着，感受到其精心呵护的美甲之后，满足地长叹一声，进行了一场惊世骇俗的淫秽表演。",
					"萝丝确实用心照料着她的巧手，她没有说谎，你将指尖滑过她静心呵护的指甲，脑子里不断地想着，要有这么完美的手，该要费多少功夫。",
					"萝丝在指甲上涂的那抹淡粉色与她白皙的皮肤相得益彰，你用自己的手指对着那修长的指甲揉来搓去，惊叹于世上竟有如此完美之物。");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction FINGER_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "轻抚手指";
		}
		@Override
		public String getActionDescription() {
			return "轻柔地抚摸萝丝的手指。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你紧紧抓住萝丝一根纤细、柔美的手指，用指尖缓慢地来回磨蹭着，惊叹于那完美无瑕的柔嫩皮肤。",
					"你猛地伸出手来，握住了萝丝的手指，用指尖上下摩挲着，口中不禁发出一声欣喜的呻吟。",
					"你将自己的手指滑过萝丝的手指，接触到她天使般柔嫩的皮肤后，激动得发出了一小声呜咽。",
					"你用指尖在萝丝每一根纤细柔美的手指上翻飞，拼尽全力地领悟着她的双手是何等精美。");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_LICK_PALM = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "舔舐手掌";
		}
		@Override
		public String getActionDescription() {
			return "舔舐萝丝的手掌。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isOrificeFree(Main.game.getPlayer(), SexAreaOrifice.MOUTH);
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你将萝丝的一只手抬到嘴边，[pc.lips+]与掌心精细的皮肤贴在一起，接着你的[pc.tongue]便在她柔嫩的皮肤上滑过。",
					"你将萝丝的手举到嘴边，对着她柔软的掌心，献上了一次长久、缓慢的舔舐，汗液微咸的味道混合着女性的香气冲入你的鼻腔，让你发出了一声淫荡的[pc.moan]。",
					"你握住萝丝的手，将其举到嘴边，接着便轻轻地舔舐起那柔软的掌心。汗液清淡的味道混合着雌性的香气冲击着你的[pc.tongue]，你屈服了，发出一声颤抖的[pc.moan]。",
					"轻声[pc.moan]后，你举起萝丝天使般的纤手，放在了[pc.lips+]边，接着对着她柔软的掌心，献上了一次缓慢、精巧的舔舐。");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_START_SUCKING = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "吮吸手指";
		}
		@Override
		public String getActionDescription() {
			return "开始吮吸萝丝的手指。";
		}
		@Override
		public String getDescription() {
			return "在一场惊世骇俗的极端下流的表演后——这通常只能在最犄角旮旯处的电子小报中出现——你决定要将事情推向新的进程。"
					+ "你抓住萝丝天使般柔美的手，缓缓地举到了嘴边。"
					+ "萝丝也意识到你接下来的行为，纯粹是对于你采取的意料之外的结果感到期待，她倚靠在墙上，目睹着这一切的发生，"
						+ "随着她的手指逐渐向你仿佛无底的口中靠近时，她还是呻吟喘息起来。"
					+ "<br/><br/>"
					+ "温热的呼吸落在了她敏感的指尖上，你脑中忽然闪过一段思绪，“我做的太过了吗？”，但如今已经不能轻易回头了，"
						+ "于是你下定决心，将萝丝的手指穿过你的嘴唇，伸入了口中。"
					+ "因而你将自己降阶为了一等下流的变态，你很清楚自己无法再变回从前那个人了，继续吮吸着萝丝的手指。";
		}
	};
	
	public static final SexAction PLAYER_GENTLE_SUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "和缓地吮吸";
		}
		@Override
		public String getActionDescription() {
			return "和缓地吮吸萝丝的手指。";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你轻轻地吮吸亲吻着萝丝纤细而阴柔的手指，急切地用舌头舔过，品味着汗液与女性香气混合的清淡味道。",
					"你缓缓地吮吸着，舌头轮流经过萝丝每一根完美的手指，在用嘴巴取悦着她的纤手时发出了[pc.moans+]。",
					"你下定决心要让萝丝好好舒服一下，便轻柔地吮吸亲吻起她每一根柔美的手指，陶醉于这种能够提供异常快乐的经历中。",
					"你用[pc.lips+]包裹住萝丝天使般的手指，对着如此完美的事物不断轻柔地吸吮、亲吻着，忽然一股微咸的汗味抵达了你的舌根，你不禁发出一声轻微的[pc.moaning]。");
		}
	};
	
	public static final SexAction PLAYER_INTENSE_SUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "用力地吮吸";
		}
		@Override
		public String getActionDescription() {
			return "用力地吮吸萝丝的手指。";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你还没有反应过来自己正在做什么，便已经沉浸于吮吸萝丝手指带来的异常快感中了，随着一声淫荡的叫喊，你开始越来越用力地吮吸起来。",
					"随着强烈的吮吸，你将[pc.lips+]紧紧贴在萝丝优美的手指上，发了疯似的亲吻舔舐起来，",
					"你决定将吮吸手指的淫荡行为提一段速度，发出一声按捺不住的喊叫后，你贪婪地将舌头缠绕过她的每一根手指。",
					"萝丝的手指在你的口中显得柔软又温暖，而你却再也按捺不住，发了疯似的对着她完美的手指又亲又舔。");
		}
	};
	
	public static final SexAction PLAYER_STOP_SUCKING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "停止吮吸";
		}
		@Override
		public String getActionDescription() {
			return "停止吮吸萝丝的手指。";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你无法忍受自己吮吸手指的淫荡行为，终于将萝丝柔软而漂亮的手指从你嘴里吐出来了。"
					+ "一段粘稠的唾液缓缓地从你的嘴里落下，在你的嘴唇和萝丝的指尖搭起了一座桥，但很快便断开了，滴落在脚下的地上。");
		}
	};
	
	// Rose:
	
	public static final SexAction PARTNER_MOAN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "呻吟";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"萝丝发出无法抑制的声音，",
					"萝丝发出了一声格外淫荡的呻吟，",
					"萝丝紧紧地盯着你，不受控制地呻吟着，",
					"萝丝下意识地发出了一声颤抖的呻吟，"));
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[rose.speech(~啊啊！~没错！别停！)]",
					"[rose.speech(对对对~啊啊！~来吧！)]",
					"[rose.speech(太棒了！~啊啊！~继续！)]",
					"[rose.speech(哦！~啊啊！~就是这样！继续！)]",
					"[rose.speech(~啊啊！~太棒了，太棒了！！！)]",
					"[rose.speech(~啊啊！~就是这样！继续！~啊啊！~)]"));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_PANTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "喘息";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"在你不断的刺激下，萝丝似乎只得喘息、[rose.moan]着。",
					"你高超的握手技巧似乎让萝丝应接不暇，随着欲望的高涨，萝丝只得急促地喘息着。",
					"萝丝的眼睛上翻了一瞬间，舌头从嘴里伸出，一声绵长的呻吟不受控制地发了出来。",
					"随着一声无法抑制的[rose.moan]，萝丝开始喘息起来，只见她[rose.tongue+]从口中滑出，一定是努力接受着你给予她的快感。");
		}
	};
	
	public static final SexAction PARTNER_TABLE_BRACE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "挺住";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"萝丝差点瘫倒在身旁的桌面上，膝盖一阵酥软，勉强支撑着。",
					"随着一声轻声[rose.moan]，萝丝依靠在墙上苦苦支撑，应付着高涨的性欲。",
					"萝丝背靠着墙壁，发出一声颤抖的[rose.moan]，显然已经不能靠潜意识站稳。",
					"萝丝依靠在附近的桌面上，将这件家具作为支撑，你高超的握手技巧带来的欢愉无法抵挡，显然让她保持站立都很吃力。");
		}
	};
	
	public static final SexAction PARTNER_SLIDE_FINGERS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "滑动手指";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"伴随着一声淫靡的[rose.moan]，萝丝开始将手指在你的嘴里摆来摆去，此等下流的场面你在最狂野的梦境中也不曾见过。",
					"萝丝缓缓地在你的口中滑动着手指，接着开始揉搓你[pc.lips+]，你感受到她柔嫩的皮肤后，不禁发出一声呜咽。",
					"萝丝将手指在你的嘴中伸进伸出，又绕着你的舌头打转，接着发出一声不受控制的[rose.moan]。",
					"在这场无与伦比的变态表演中，萝丝开始将手指从你的嘴里伸进伸出，又对着你[pc.lips+]揉来捏去，让你尽情感受她柔软到不可思议的皮肤。");
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())>=Main.sex.getCharacterPerformingAction().getOrgasmsBeforeSatisfied()
					&& Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>=Main.game.getPlayer().getOrgasmsBeforeSatisfied();
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "随着一声满足的叹息，萝丝从你的紧握中抽出手来，含情脉脉地盯着你的眼睛，借口离开了，"
					+ "[rose.speech(我觉得不能再继续了！我得休息一下，不过有时间肯定可以再做的！)]";
		}

		@Override
		public void applyEffects() {
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PLAYER_STOP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			if(Main.sex.isSpectator(Main.game.getPlayer()) && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				return "停止观看";
			}
			return Main.sex.isMasturbation()
					?"停止自慰"
					:"停止性爱";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.isSpectator(Main.game.getPlayer()) && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				return "你离开了，不再观看面前呈现的性爱场景。"
						+ "<br/>由于该场景已经正式发生，仍会对所有参与者[style.boldSex(施加所有应施加的效果)]。";
			}
			return Main.sex.isMasturbation()
					?"结束你的自慰。"
					:"停止与[npc2.name]做爱。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getInitialSexManager().isPlayerAbleToStopSex()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getDescription() {
			return "你觉得已经足够，撤开后不再继续性爱……";
		}
		@Override
		public SexParticipantType getParticipantType() {
			return Main.sex.isMasturbation() || Main.sex.isSpectator(Main.game.getPlayer())
					?SexParticipantType.SELF
					:SexParticipantType.NORMAL;
		}
		@Override
		public boolean endsSex() {
			return true;
		}
		@Override
		public String applyEndEffects(){
			return "";
		}
	};
}
