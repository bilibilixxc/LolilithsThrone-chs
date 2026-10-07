package com.lilithsthrone.game.sex.sexActions.submission;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.9
 * @version 0.3.9
 * @author Innoxia
 */
public class SAMurkSpecials {

	/**
	 * Special action for Murk preparing for the player's orgasm.
	 */
	public static final SexAction GENERIC_PREPARATION_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getCharacterOrgasming()!=null
					&& Main.sex.getCharacterOrgasming().isPlayer()
					&& Main.game.getPlayer().isCaptive();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public String getActionTitle() {
			return "励志挤奶工";
		}
		@Override
		public String getActionDescription() {
			return "你能感觉到[npc2.name]快高潮了。鼓励[npc2.herHim]为你喷出爱液。";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterOrgasming()).contains(SexAreaOrifice.MOUTH)) {
				return UtilText.returnStringAtRandom(
						"默克感觉到你的口穴被他肥大、跳动的阴茎操得快要达到高潮，便粗暴地将他黝黑的阴茎完全插入你的喉咙，并发出一声闷哼，",
						"默克意识到你只要给他口交就会射精，于是他加快了节奏，猛烈地将他火热的阴茎插入你的喉咙深处，同时发出阵阵闷哼，",
						"默克意识到你即将达到高潮，一边哼哼一边继续无情地操你的喉咙，")
						+ "[murk.speechNoEffects("
						+ UtilText.returnStringAtRandom(
								"就是这样，荡妇！~好好体会我又肥又臭的鸡巴操你喉咙的感觉，然后射吧！",
								"来吧，你这个荡妇！~在我又肥又臭的鸡巴上高潮吧！",
								"你只要给我口交就会射精吗？！~来吧，荡妇！射给我吧！")
						+")]";
				
			} else if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterOrgasming()).contains(SexAreaOrifice.VAGINA)) {
				return UtilText.returnStringAtRandom(
						"默克感觉到你[pc.pussy+]开始不受控制地挤压着他肥大、跳动的阴茎，他粗暴地将黝黑的阴茎完全插入你流水[pc.pussy+]，并发出一声闷哼，",
						"默克感受着你疯狂痉挛的穴道，意识到你快要去了。他加快节奏，一边闷哼，一边猛烈地将他滚烫的鸡巴深深插入你[pc.pussy+] ，",
						"你[pc.pussy+]开始疯狂地夹紧并挤压默克肥大的[murk.cockColour]阴茎。胖乎乎的鼠男意识到你即将达到高潮，深深咕哝了一声，")
						+ "[murk.speechNoEffects("
						+ UtilText.returnStringAtRandom(
								"就是这样，荡妇！~姆嗯！~我那又肥又臭的鸡巴和你那丑陋的屄做爱的感觉会让你爽到高潮！",
								"继续，你这个荡妇！~啊！~我那又肥又臭的鸡巴会让你的丑屄高潮！",
								"你的丑屄要被我又肥又臭的鸡巴捅到高潮了？！~姆嗯！~那就继续，荡妇！给我去！")
						+")]";
				
			} else if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterOrgasming()).contains(SexAreaOrifice.ANUS)) {
				return UtilText.returnStringAtRandom(
						"默克感觉到你[pc.asshole+]开始不受控制地挤压他那肥大、跳动的阴茎。他粗暴地将黝黑的阴茎完全插入你湿润的洞中，并发出一声闷哼，",
						"默克从你屁眼的疯狂痉挛中意识到你即将高潮，于是他加快了节奏，猛烈地将火热的阴茎插入你[pc.asshole+]的深处并发出一声闷哼，",
						"你[pc.asshole+]开始疯狂地夹紧并挤压默克肥大的[murk.cockColour]阴茎。胖乎乎的鼠男意识到你即将达到高潮，并深深地咕哝了一声，")
						+ "[murk.speechNoEffects("
						+ UtilText.returnStringAtRandom(
								"就是这样，荡妇！~姆嗯！~我那又肥又臭的鸡巴和你屁眼做爱的感觉会让你爽到高潮！",
								"继续，你这个屁穴荡妇！~啊！~我那又肥又臭的鸡巴会让你高潮！",
								"你的屁眼要被我又肥又臭的鸡巴捅到高潮了？！~姆嗯！~那就继续，荡妇！给我去！")
						+")]";
				
			} else {
				return "当默克意识到你即将达到高潮时，他粗暴地磨蹭着你，冷笑道：[murk.speechNoEffects(来吧，荡妇！他妈的给我高潮！)]"; //This should never be reached.
			}
		}
	};
}
