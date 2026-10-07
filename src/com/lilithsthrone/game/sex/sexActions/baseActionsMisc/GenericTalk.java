package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.3
 * @version 0.4.2.1
 * @author Innoxia
 */
public class GenericTalk {

	public static final SexAction STOP_RAPE_PLAY = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			null) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer()
					|| (!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_NON_CON_DOM).isPositive()
							&& !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST)))
					&& !Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING
					&& !Main.sex.isSexPaceForced(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterTargetedForSexAction(this).getLustLevel().isResistingFromRapePlay(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionTitle() {
			return "停止强奸play";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return"因为你的嘴被堵住了，不能直接告诉[npc2.name]不用再假装反抗了，但你仍然能够发出不赞同的声音来让[npc2.her]知道你希望[npc2.herHim]停下来。";
			}
			return "告诉[npc2.name]停止假装反抗。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append("[npc.namePos]即使的嘴被堵住了，也还能发出不赞同的声音，以此给[npc2.name]传递停止假装反抗的信号。");
				
			} else {
				sb.append("[npc.Name]并不喜欢[npc2.namePos]的行为，明确地对[npc2.herHim]表示让其停止假装反抗。");
			}

			sb.append("[npc2.name]虽然发出失望的[npc2.moan]，但还是决定同意[npc.namePos]的请求，停止强奸play。");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.setCharacterBannedFromRapePlay(Main.sex.getCharacterTargetedForSexAction(this), true);
		}
	};

	public static final SexAction ALLOW_RAPE_PLAY = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			null) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer()
						|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_NON_CON_DOM).isPositive()
						|| Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST))
					&& !Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
					&& Main.game.isNonConEnabled()
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING
					&& !Main.sex.isSexPaceForced(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.isCharacterBannedFromRapePlay(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterTargetedForSexAction(this).hasFetish(Fetish.FETISH_NON_CON_SUB);
		}
		@Override
		public String getActionTitle() {
			return "允许强奸play";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "即使你的嘴被堵住了，你仍然能够发出暗示性的声响来让[npc2.her]明白：如果[npc2.she]想要，[npc2.she]可以假装反抗。";
			}
			return "告诉[npc2.name]：如果[npc2.she]想要[npc.she]可以假装反抗。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append("即使[npc.namePos]的嘴被堵住了，[npc.sheIs]仍能够发出暗示性的声响来给[npc2.name]一个信号：如果[npc2.she]想[npc2.she]可以假装反抗。");
				
			} else {
				sb.append("[npc.Name]想让事情变得更变态，告诉[npc2.name]：如果[npc2.she]想要，[npc2.she]可以假装反抗。");
			}

			sb.append("[npc2.name]发出兴奋的[npc2.moan]，花了一点时间来决定是否继续假装反抗……");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.setCharacterBannedFromRapePlay(Main.sex.getCharacterTargetedForSexAction(this), false);
		}
	};
	
	public static final SexAction ROUGH_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isSadisticAction() {
			return true;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DOMINANT) || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST))
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "威胁地咆哮";
			}
			return "侮辱的话语";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "因为你的嘴被堵住了，没法告诉[npc2.name][npc2.sheIs]就是你的肉便器，但还是可以发出一些侵略性的低吼声让[npc2.her]知道你还没出戏。";
			}
			return "告诉[npc2.name][npc2.sheIs]就是你的肉便器。";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"[npc.namePos]的嘴被堵住了，不能讲话。但[npc.she]想让[npc2.name]知道，[npc2.sheIs]只不过是自己的婊子，便发出侵略性的咆哮声。",
						"因为嘴被堵住了不能讲话，[npc.nameIsFull]决定发出深沉威胁的咆哮，让[npc2.name]明白，[npc2.sheIs]还是自己的贱婊子。",
						"[npc.Name]并没有被目前无说话能力吓倒，而是发出了特别威胁的咆哮，让[npc2.name]知道[npc2.sheIs]会被当成可怜的肉便器。",
						"虽然[npc.her]的嘴被锁住了，不能说话，"
								+ "[npc.nameIsFull]毫不犹豫地发出最具威胁性的咆哮之一，让[npc2.name]明白，自己会被当做顺从婊子对待。"));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SADIST).isPositive()) {
					sb.append(UtilText.returnStringAtRandom(
							"[npc.Name]露出邪恶的笑容，朝着[npc2.name]咆哮，",
							"[npc.NamePos]的声音充满了虐待狂的欢愉，[npc.she]咆哮道，"));
				} else {
					sb.append(UtilText.returnStringAtRandom(
							"[npc.Name]对自己笑笑，冲着[npc2.name]咆哮道，",
							"[npc.Name]发出最为支配的声音。咆哮道，"));
				}
				
				sb.append(Main.sex.getRoughTalk(Main.sex.getCharacterPerformingAction()));
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction SUBMISSIVE_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SUBMISSIVE);
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "服从式呜叫";
			}
			return "服从式交谈";
		}
			
		@Override
		public SexActionPriority getPriority() {
			if(!Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getLastUsedPlayerAction()==ROUGH_TALK && Math.random()<0.5f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "你的嘴被堵住了，不能告诉[npc2.name]你是[npc2.her]的顺服婊子，但你仍然可以发出可怜的呜叫，让对方知道你仍然是[npc2.her]忠诚的婊子。";
			}
			return "告诉[npc2.name]你是[npc2.her]的顺服婊子，想被当作一文不值的贱婊子操。";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"[npc.namePos]的嘴被堵住了，不能讲话，但仍然想让[npc2.name]通过声音知道自己很享受，便发出可怜的呜叫声。",
						"[npc.nameIsFull]的嘴被锁住了，不能讲话，转而决定发出难以置信的顺服且淫靡的呜叫，让[npc2.name]知道[npc.sheIs]现在很享受。",
						"[npc.Name]并没有被目前无说话能力吓倒，而是发出了可怜且淫靡的呜叫，让[npc2.name]知道[npc2.sheIs]乐意继续当顺服的婊子。",
						"虽然[npc.her]的嘴被锁住了，不能说话，"
								+ "[npc.nameIsFull]毫不犹豫地发出最可悲的呜叫之一，让[npc2.name]明白，[npc.sheIs]乐意被当成[npc2.her]的顺服婊子。"));
				
			} else {
				sb.append(
						UtilText.returnStringAtRandom(
						"[npc.Name]咬住[npc.lip]，努力让自己看起来尽可能顺服，哭喊道，",
						"[npc.Name]摆出最天真无邪的样子，[npc.moaning]，"));
	
				sb.append(Main.sex.getSubmissiveTalk(Main.sex.getCharacterPerformingAction()));
			}
			
			return sb.toString();
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction LOVING_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			null) {
		@Override
		public boolean isLovingAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "充满爱意的叹息";
			}
			return "爱的告白";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "因为你的嘴被锁住了，不能告诉[npc2.name]你也爱[npc2.herHim]，但你仍然能发出情意绵绵的低吟，让[npc2.her]明白你的感受。";
			}
			return "温柔地告诉[npc2.name]你爱[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"[npc.namePos]的嘴被锁住了，不能讲话，但还想向[npc2.name]传达自己的感情，便发出充满爱意的叹息声。",
						"因为嘴被堵住了不能讲话，[npc.nameIsFull]决定发出柔和且轻浮的叹息，让[npc2.name]明白[npc.she]爱[npc2.herHim]。",
						"[npc.Name]并没有被目前无说话能力吓倒，而是发出温柔却情意浓浓的叹息，让[npc2.name]知道[npc2.sheIs]爱着[npc2.herHim]。"));
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"脸上挂着充满爱意的笑容，",
						"情意绵绵地凝视着[npc2.name]，"));
				sb.append(UtilText.returnStringAtRandom(
						"[npc.name]轻柔地",
						"[npc.name]温柔地"));
				sb.append(UtilText.returnStringAtRandom(
						"叹息，",
						"耳语，"));
				
				sb.append(Main.sex.getLovingTalk(Main.sex.getCharacterPerformingAction()));
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction LOVING_REPLY = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			null) {
		@Override
		public boolean isLovingAction() {
			return true;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getLastUsedSexAction(Main.sex.getCharacterTargetedForSexAction(this))==LOVING_TALK;
		}
		@Override
		public String getActionTitle() {
			return "爱的答复";
		}
		@Override
		public SexActionPriority getPriority() {
			if(!Main.sex.getCharacterPerformingAction().isPlayer() && Math.random()<0.8f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "你的嘴被堵住了，不能告诉[npc2.name]你也爱[npc2.herHim]，但你可以发出温柔的叹息，让[npc2.her]明白你的感受。";
			}
			return "告诉[npc2.name]你也爱[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"[npc.namePos]的嘴被堵住了，不能讲话。但[npc.she]想向[npc2.name]表示自己的感情得到了回报，便发出低哑的示爱声。",
						"因为嘴被堵住了不能讲话，[npc.nameIsFull]决定发出示爱的呻吟，让[npc2.name]明白，[npc.she]同样爱着[npc2.name]。",
						"[npc.Name]并没有被目前无说话能力吓倒，而是发出情意绵绵的呻吟，让[npc2.name]知道[npc.she]也爱[npc2.herHim]。"));
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"冲着[npc2.name]喜悦地笑起来，",
						"发出愉悦的呻吟，"));
				sb.append(UtilText.returnStringAtRandom(
						"[npc.name]情意绵绵地",
						"[npc.name]热烈地"));
				sb.append(UtilText.returnStringAtRandom(
						"回答，",
						"回复，"));
				
				sb.append(Main.sex.getLovingResponseTalk(Main.sex.getCharacterPerformingAction()));
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction ASKING_FOR_ROUGH_SEX = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "看起来很可怜";
			}
			return "请求粗暴性爱";
		}

		private boolean isAcceptingRequest() {
			return Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())
//					|| Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_DOMINANT).isPositive()
					|| Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_SADIST).isPositive()
					|| Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS);
		}
		
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "因为你的嘴被堵上了，你没法直接让[npc2.name]对你粗暴点。"
						+ "相反，你可以让自己看起来尽可能地娇小可怜，来引诱[npc2.herHim]这样做。"
						+ "<br/>"
						+ (isAcceptingRequest()
								?"[style.italicsGood("
								:"[style.italicsBad(")
						+"需要[npc2.name]拥有“"+Fetish.FETISH_DOMINANT.getName(Main.sex.getCharacterTargetedForSexAction(this))+"”性癖，"
							+ "喜欢“"+Fetish.FETISH_SADIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"”性癖，"
							+ "或者因为你有已激活的“"+Perk.CONVINCING_REQUESTS.getName(Main.sex.getCharacterPerformingAction())+"”特质。)]";
			}
			return "告诉[npc2.name]，像对待一文不值的婊子那样对待你。"
						+ "<br/>"
						+ (isAcceptingRequest()
								?"[style.italicsGood("
								:"[style.italicsBad(")
						+"需要[npc2.name]拥有“"+Fetish.FETISH_DOMINANT.getName(Main.sex.getCharacterTargetedForSexAction(this))+"”性癖，"
							+ "喜欢“"+Fetish.FETISH_SADIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"”性癖，"
							+ "或者因为你有已激活的“"+Perk.CONVINCING_REQUESTS.getName(Main.sex.getCharacterPerformingAction())+"”特质。)]";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.DOM_ROUGH
					&& (Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_MASOCHIST) || Main.sex.getCharacterPerformingAction().isPlayer());
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"[npc.Name]的嘴被锁住了，不能直接回答，转而"
								+ "[npc.she]发出可怜的呜叫，努力让自己看起来尽可能娇小，来引诱[npc2.name]粗暴地对待[npc.herHim]。",
						"[npc.Name]努力让自己看起来尽可能娇小可怜，发出高音调的哀叫，来引诱[npc2.name]粗暴地对待[npc.her]。",
						"[npc.Name]渴望被当成贱婊子，努力让自己看起来尽可能娇小，同时发出可怜且淫靡的哀叫。",
						"[npc.Name]嘴被锁住了，不能讲话，只得把能想到的事都做一遍，努力让[npc2.name]更粗暴些对待自己，"
								+ "让自己看起来尽可能娇小可怜，发出高音调的哀叫。"));
				
			} else {
				sb.append(
						UtilText.returnStringAtRandom(
						"端出最为取悦的声音，[npc.name]祈求[npc2.name]更粗暴些，",
						"[npc.Name]咬住[npc.lip]，努力让自己看起来尽可能天真无邪，乞求道，"));
				
				sb.append(UtilText.returnStringAtRandom(
						"[npc.speech(来嘛！我就是下贱的小骚货！就这样对我！)]",
						"[npc.speech(我是你的小浪货！求你了，操进来吧～)]",
						"[npc.speech(把我当成贱婊子吧！我喜欢粗暴点的呢！)]",
						"[npc.speech(你不想玩坏我吗……？来嘛！我们大干一场，粗暴点！)]"));
			}
			
			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
				if(isAcceptingRequest()) {
					sb.append("<br/><br/>"
							+UtilText.returnStringAtRandom(
								"[npc2.name]听到你这么问，满足且愉悦地笑起来，发出威胁的咆哮，回答道，",
								"[npc2.name]发出深沉的咆哮回应着你的请求，肯定地回应，"));

					sb.append(UtilText.returnStringAtRandom(
							"[npc2.speech(哇，你就是想这样吧，小婊子？好哦，我会把你当成下贱的荡妇对待哦！)]",
							"[npc2.speech(你真的想要这样吗，嗯？那就好了，准备好变成我的下贱肉便器吧！)]",
							"[npc2.speech(你这个小婊子！行吧，我很愿意把你干到口吐白沫晕过去呢！)]"));
					
				} else {
					sb.append("<br/><br/>"
							+UtilText.returnStringAtRandom(
								"[npc2.name]听到你问这个，皱起眉头叹了口气，回答，",
								"[npc2.name]回应着你的请求，失望地叹了口气，否定地回答，"));

					sb.append(UtilText.returnStringAtRandom(
							"[npc2.speech(对不起，不过我还没准备好那样子……我们就维持现状可以吗？)]",
							"[npc2.speech(我可不是会做那种事的人……我们就维持现状比较好。)]",
							"[npc2.speech(对不起……不过我可不是会做那种事的人……)]"));
				}
			}
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterPerformingAction().isPlayer() && isAcceptingRequest()) {
				Main.sex.setSexPace(Main.sex.getCharacterTargetedForSexAction(this), SexPace.DOM_ROUGH);
			}
		}
	};
	
	public static final SexAction ASKING_FOR_GENTLE_SEX = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "担忧地看着";
			}
			return "反对粗鲁的对待";
		}
		
		private boolean isAcceptingRequest() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasFetish(Fetish.FETISH_SADIST)
					&& (Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())
						|| Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive()
						|| Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_SADIST).isNegative());
		}
		
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "因为你的嘴被堵上了，你没法直接让[npc2.name]对你温柔点。"
						+ "相反，你可以让自己看起来很担心，来让[npc2.herHim]慢下来。"
						+ "<br/>"
						+ (isAcceptingRequest()
								?"[style.italicsGood("
								:"[style.italicsBad(")
						+"需要[npc.name]拥有“"+Fetish.FETISH_SUBMISSIVE.getName(Main.sex.getCharacterTargetedForSexAction(this))+"”性癖，"
							+ "讨厌“"+Fetish.FETISH_SADIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"”性癖，"
							+ "或者因为你有已激活的“"+Perk.CONVINCING_REQUESTS.getName(Main.sex.getCharacterPerformingAction())+"”特性。)]";
			}
			return "让[npc2.name]对你温柔点。"
					+ "<br/>"
					+ (isAcceptingRequest()
							?"[style.italicsGood("
							:"[style.italicsBad(")
					+"需要[npc.name]拥有“"+Fetish.FETISH_SUBMISSIVE.getName(Main.sex.getCharacterTargetedForSexAction(this))+"”性癖，"
						+ "讨厌“"+Fetish.FETISH_SADIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"”性癖，"
						+ "或者因为你有已激活的“"+Perk.CONVINCING_REQUESTS.getName(Main.sex.getCharacterPerformingAction())+"”特性。)]";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.DOM_ROUGH
					&& (Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_MASOCHIST).isNegative() || Main.sex.getCharacterPerformingAction().isPlayer());
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"[npc.Name]的嘴被锁住了，不能直接回答，转而"
								+ "扬起眉毛，发出忧虑的呜叫，努力说服[npc2.name]慢一点，温柔地对待[npc.herHim]。",
						"摆出担忧的神情，[npc.Name]发出焦虑的哀叫，努力传达给[npc2.name]，让[npc2.she]更温柔些。",
						"[npc.Name]想要被更温柔些对待，便扬起眉毛，发出不安的哀鸣声。",
						"[npc.Name]嘴被锁住了，不能讲话，只得把能想到的事都做一遍，努力让[npc2.name]更温柔些对待自己，"
								+ "露出焦急的神情，担心地呜叫着。"));
				
			} else {
				sb.append(
						UtilText.returnStringAtRandom(
						"端出最为取悦的声音，[npc.name]劝[npc2.name]冷静点，",
						"[npc.Name]扬起眉毛，摆出担忧的神色，问道，"));
				
				sb.append(UtilText.returnStringAtRandom(
						"[npc.speech(不要，不要这么用力可以吗？我想要更温柔点的呢……)]",
						"[npc.speech(喂喂，慢点！你就不能轻点吗？)]",
						"[npc.speech(你能对我温柔点吗？这有点太刺激了……)]",
						"[npc.speech(呜啊！求求你慢点好吗？我以为你会温柔点的……)]"));
			}

			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
				if(isAcceptingRequest()) {
					sb.append("<br/><br/>"
							+UtilText.returnStringAtRandom(
								"[npc2.name]听到你问这个，开心地笑起来，舒了口气后回答，",
								"[npc2.name]发出轻柔的[npc2.moan]应和着你的请求，肯定地回应，"));

					sb.append(UtilText.returnStringAtRandom(
							"[npc2.speech(吼吼，那就是你想要的？唔唔，我也很乐意慢点……)]",
							"[npc2.speech(我是能受得了啦……不过可以再慢点吗？)]",
							"[npc2.speech(好像也不错呢……我会温柔点对待你的……)]"));
					
				} else {
					sb.append("<br/><br/>"
							+UtilText.returnStringAtRandom(
								"[npc2.name]听到你问这个，皱起眉头啧啧几声，回答，",
								"[npc2.name]发出反对的啧啧声，回应着你的请求，[npc2.name]否定地回答，"));

					sb.append(UtilText.returnStringAtRandom(
							"[npc2.speech(哼哼，我可不是为了那些温柔的废话来的。你只要学会这样就好了！)]",
							"[npc2.speech(那些温情脉脉的狗屎话我可是一点没听。你只要顺着我的心意来就好了。)]",
							"[npc2.speech(哇哦，那可不行。别再要那些温情脉脉的废话了！)]"));
				}
			}
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterPerformingAction().isPlayer() && isAcceptingRequest()) {
				Main.sex.setSexPace(Main.sex.getCharacterTargetedForSexAction(this), SexPace.DOM_GENTLE);
			}
		}
	};
	
}
