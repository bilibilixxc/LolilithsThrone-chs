package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.79
 * @version 0.4.11.2
 * @author Innoxia
 */
public class PartnerTalk {
	
	public static final SexAction PARTNER_DIRTY_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING) {
				return CorruptionLevel.ZERO_PURE;
			} else {
				return CorruptionLevel.ONE_VANILLA;
			}
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					default:
						return Main.sex.getCharacterPerformingAction().isFeminine()?"饥渴地呜咽":"饥渴地呻吟";
					case DOM_ROUGH:
						return "狂野咆哮";
					case SUB_RESISTING:
						return "抗议地哀叫";
				}
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				default:
					return "说骚话";
				case DOM_ROUGH:
					return "侮辱";
				case SUB_RESISTING:
					return "乞求停下";
			}
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					default:
						return UtilText.returnStringAtRandom(
								"[npc.namePos]的嘴被堵住了，[npc.she]没法对[npc2.name]说骚话，只能发出一连串模糊而淫靡的[npc.moans]。",
								"[npc.nameIsFull]的嘴巴现在被封住了，只能发出一连串模糊而下流的[npc.moans]，让[npc2.name]知道[npc.sheIs]现在十分享受。",
								"[npc.Name]想让[npc2.name]知道自己现在很享受，但嘴巴却被封住了，只得发出一串模糊而又极其下流的[npc.moans]。",
								"尽管[npc.nameIsFull]的嘴巴被封住了，但还是能发出一连串模糊而淫靡的[npc.moans]，让[npc2.name]知道[npc.sheIs]现在很享受。");
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.namePos]的嘴被堵住了，[npc.she]没法侮辱[npc2.name]，只能发出一连串模糊而嚣张的[npc.moans]。",
								"[npc.nameIsFull]受被堵住的[npc.her]嘴所限，只能够发出一阵低沉而粗犷的咆哮来让[npc2.name]知道[npc.sheIs]仍然牢牢掌握着一切。",
								"[npc.Name]虽想让[npc2.name]知道[npc.sheIs]仍牢牢掌握着一切，但因为[npc.her]嘴被堵住了，只能通过发出一阵威胁性的低沉的咆哮勉强表达自己的想法。",
								"即使[npc.her]嘴被堵住了，[npc.nameIsFull]仍能够发出一阵低沉的咆哮来让[npc2.name]知道[npc.sheIs]仍牢牢地掌握着一切。");
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"[npc.namePos]的嘴被堵住了，[npc.she]只能依靠发出一连串可怜的呜咽和低沉的啜泣声来代替求饶。",
								"因为[npc.her]的嘴目前被堵住了，[npc.nameIsFull]只能靠发出一连串低沉的啜泣与痛苦的呜咽来让[npc2.name]知道[npc.she]想被放走。",
								"[npc.Name]想让[npc2.name]知道[npc.she]想被放走，但受[npc.her]被堵住的嘴所限，只能靠发出一连串痛苦的呜咽与低沉的哭声来勉强表达自己的想法。",
								"即使[npc.her]嘴被堵住了，[npc.nameIsFull]仍能够靠发出一连串痛苦的呜咽声与低沉的啜泣声来让[npc2.name]知道[npc.she]想让现在这件事停下来。");
				}
			}

			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return UtilText.returnStringAtRandom(
						"[npc.name]不想吵醒[npc2.name]，悄悄地玩笑道，",
						"[npc.name]压低声音以免吵醒[npc2.name]，轻声低语道，")
						+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
			}
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"[npc.name]回过头看向[npc2.name]，[npc.lips+]间滑出一阵[npc.a_moan+]，",
								"[npc.Name]转头看向[npc2.name]，发出[npc.a_moan+]然后大喊，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"[npc.Name]惊慌地从[npc2.name]身边爬开，[npc.sobsVerb+]。[npc2.she]抓住[npc.hips+][npc.hips+]，拉了回来，",
								"[npc.Name]尝试躲开[npc2.name]，发出[npc.a_sob+]，但被[npc2.she]牢牢定住，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					default: 
						return UtilText.returnStringAtRandom(
								"[npc.namePos]回头看向[npc2.name]，[npc.lips+]间溢出[npc.a_moan]，",
								"[npc.Name]转头看向[npc2.name]，发出[npc.a_moan]然后大喊，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				if(Main.sex.getCharacterPerformingAction().isTaur()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_GENTLE:
							return UtilText.returnStringAtRandom(
									"[npc.Name]轻柔地唤着[npc2.name]，跪在[npc.herHim]身下，",
									"[npc2.name]跪在[npc.Name]的兽态[npc.legRace]身体下，[npc.name]轻轻叹了口气，")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
						case DOM_ROUGH:
							return UtilText.returnStringAtRandom(
									"[npc.Name]朝着[npc2.name][npc.moansVerb+]，随即跪在[npc.herHim]身下，",
									"[npc2.name]跪在[npc.Name]的兽态[npc.legRace]身体下，[npc.name][npc.moansVerb+]，")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
						default: 
							return UtilText.returnStringAtRandom(
									"[npc.Name]朝着[npc2.name][npc.moansVerb+]，随即跪在[npc.herHim]身下，",
									"[npc2.name]跪在[npc.Name]的兽态[npc.legRace]身体下，[npc.name][npc.moansVerb+]，")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					}
				
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_GENTLE:
							return UtilText.returnStringAtRandom(
									"[npc.Name]低头看向[npc2.name]，[npc2.name]正跪在[npc.herHim]身下，",
									"低头看向[npc2.name]，[npc.name]叹了口气，")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
						case DOM_ROUGH:
							return UtilText.returnStringAtRandom(
									"[npc.Name]朝[npc2.name]坏笑了下，便跪在[npc.herHim]身下，",
									"朝[npc2.name]坏笑了下，[npc.name][npc.moansVerb+]，")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
						default: 
							return UtilText.returnStringAtRandom(
									"[npc.Name]低头看向[npc2.name]，[npc2.name]正跪在[npc.herHim]身下，",
									"低头看向[npc2.name]，[npc.name][npc.moansVerb]，")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					}
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"[npc.Name]抬头瞥了一眼[npc2.name]，[npc.moansVerb]，",
								"抬头看向[npc2.name]，[npc.name][npc.moansVerb]，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"[npc.Name]抬眼看[npc2.name]，发出[npc.a_sob+]，",
								"抬头看向[npc2.name]，[npc.name]发出[npc.a_sob+]，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					default: 
						return UtilText.returnStringAtRandom(
								"[npc.Name]抬头瞥了一眼[npc2.name]，说，",
								"抬头看向[npc2.name]，[npc.name]说，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc.Name]低头看向躺在身下的[npc2.name]，俯身对[npc2.herHim]说，",
								"[npc.name]低头看着躺在身下的[npc2.she]，俯身说，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.Name]低头看向躺在身下的[npc2.name]，冲[npc2.herHim]低咆道，",
								"[npc.name]低头看着躺在身下的[npc2.name]，俯身咆哮，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					default: 
						return UtilText.returnStringAtRandom(
								"[npc.Name]低头看向躺在身下的[npc2.name]，俯身冲[npc2.herHim][npc.moansVerb]，",
								"[npc.Name]低头看向躺在身下的[npc2.name]，冲[npc2.herHim][npc.moansVerb]，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc.Name]发出柔和的[npc.moan]，",
								"[npc.Name]温柔地叹了口气，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.Name]发出粗野的咆哮，然后大声讲道，",
								"[npc.Name]威胁地咆哮，而后说道，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"一阵渴望的[npc.moan]从[npc.namePos][npc.lips+]滑出，",
								"[npc.Name]发出迷乱的[npc.moan]，随后对[npc2.name]说，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"[npc.she]反抗着[npc2.name]，[npc.lips+]中流出抗议的呜咽声，",
								"[npc.Name]发出沉闷的呜咽，想从[npc2.name]身边逃离，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					default: // DOM_NORMAL, SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"[npc.namePos][npc.lips+]间窜出[npc.A_moan]，",
								"[npc.Name]发出[npc.a_moan]，然后对着[npc2.name]说，")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
				}
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
				}
				
			} else {
				if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
				}
			}

			return null;
		}
	};
	
}
