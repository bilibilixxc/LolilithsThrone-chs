package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
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
 * @since 0.1.90
 * @version 0.2.9
 * @author Innoxia
 */
public class PenisAss {
	
	public static final SexAction TEASE_COCK_OVER_ASS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "尻交挑逗";
		}

		@Override
		public String getActionDescription() {
			return "将[npc.cockHead]在[npc2.namePos]的臀肉间滑动。";
		}
		
		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]温柔地将[npc2.namePos]的[npc2.assSize]臀肉挤到中间，在臀缝中缓慢地上下抽送[npc.her][npc.cock+]的[npc.cockHead]。",

							"温柔地捧着[npc2.namePos][npc2.ass+]，[npc.name]缓慢地将[npc2.her]的臀肉挤到中间，然后开始在臀缝中玩弄着[npc.her][npc.cock+]。",

							"温柔地捧着[npc2.namePos]的臀肉，[npc.name]慢慢地将它们挤到中间，"
									+ "然后开始用[npc.her][npc.cock+]的[npc.cockHead]上下玩弄形成的臀缝。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗鲁地将[npc2.namePos][npc2.assSize]屁股的臀肉推到一起，开始野蛮地将[npc.cock+]的[npc.cockHead]在形成的臀缝中上下抽送。",

							"用力抓着[npc2.namePos][npc2.ass+]，[npc.name]横蛮地将[npc2.her]的臀肉挤到中间，然后开始用[npc.her][npc.cock+]玩弄形成的臀缝。",

							"使劲抓着[npc2.namePos]的两瓣臀肉，[npc.name]粗暴地将它们挤到一起，"
									+ "然后开始在形成的臀缝中蹭磨[npc.her][npc.cock+]的[npc.cockHead]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]贪婪地将[npc2.namePos][npc2.assSize]屁股的臀肉推到一起，开始渴求地将[npc.cock+]的[npc.cockHead]在形成的臀缝中上下抽送。",

							"紧紧地抓着[npc2.namePos][npc2.ass+]，[npc.name]激动地将[npc2.her]的臀肉挤到中间，然后开始用[npc.her][npc.cock+]在臀缝中挑逗。",

							"贪婪地抓着[npc2.namePos]的两瓣臀肉，[npc.name]渴求地将它们挤到中间，"
									+ "然后开始激动地把[npc.her][npc.cock+]的[npc.cockHead]放在臀缝间挑逗。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos][npc2.assSize]屁股的臀肉推到一起，开始渴求地将[npc.cock+]的[npc.cockHead]在形成的臀缝中上下抽送。",

							"紧紧地抓着[npc2.namePos][npc2.ass+]，[npc.name]激动地将[npc2.her]的臀肉挤到中间，然后开始用[npc.her][npc.cock+]在臀缝中挑逗。",

							"抓着[npc2.namePos]的两瓣臀肉，[npc.name]将它们挤到中间，"
									+ "然后开始激动地把[npc.her][npc.cock+]的[npc.cockHead]放在臀缝间挑逗。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos]期待着[npc.namePos][npc.cock+]插进[npc2.her][npc2.ass+]，一声轻柔的[npc2.moan]从[npc2.her][npc2.lips+]间飘出。",
									"[npc2.name]感觉到[npc.namePos][npc.cock+]将要插入自己[npc2.ass+]，不禁发出一阵[npc2.a_moan+]。",
									"[npc2.name]预感到[npc.namePos][npc.cock+]要深深插进自己[npc2.ass+]，不禁痛苦地[npc2.moanVerb]着。"));
							break;
						case DOM_ROUGH:
						case SUB_NORMAL:
						case DOM_NORMAL:
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]热切地预见[npc.namePos][npc.cock+]将要插进[npc2.her]的[npc2.ass+]，一声[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
									"[npc2.name]迫不及待地感受到[npc.namePos][npc.cock+]将要插入自己[npc2.ass+]，不禁发出一阵[npc2.a_moan+]。",
									"[npc2.name]预感到[npc.namePos][npc.cock+]要深深插进自己[npc2.ass+]，不禁痛苦地[npc2.moanVerb]着。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，恐惧[npc.namePos][npc.cock+]插进[npc2.her][npc2.ass+]的感觉。",
									"[npc2.name]预感到[npc.namePos][npc.cock+]将要插入自己[npc2.ass+]，不禁发出一阵[npc2.a_sob+]。",
									"[npc2.name]预感到[npc.namePos][npc.cock+]要深深撞入自己[npc2.ass+]，不禁痛苦地[npc2.sobVerb]着。"));
							break;
					}
					
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]间飘出一声轻柔的[npc2.moan]，[npc2.speech(就是这样，把我的屁股当飞机杯用！)]",
									"[npc2.Name]放出一声温柔的娇喘，祈求着说，[npc2.speech(别停，继续用我的屁股！)]",
									"[npc2.Name]一边欢快地娇喘，一边请求，[npc2.speech(就是这样！操我的屁股！)]"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，[npc2.speech(别停！继续用我的屁股，再加点劲！)]",
									"[npc2.Name]发出一声迷乱的[npc2.moan]，咆哮着说，[npc2.speech(就是这样用我的屁股，臭婊子！)]",
									"[npc2.Name]愉悦地喘息，同时[npc2.she]命令，[npc2.speech(操我的屁股！给我马上！)]"));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]爆发出一阵[npc2.A_moan+]，[npc2.speech(就是这样！用我的屁股来取悦你的肉棒吧！)]",
									"[npc2.Name]放出一声迷乱的[npc2.moan]，祈求着说，[npc2.speech(别停！求求你，快用我的屁股吧！)]",
									"[npc2.Name]一边愉悦地喘息，一边哀求，[npc2.speech(哈❤操我屁股！我需要你的肉棒！)]"));
							break;
						case SUB_RESISTING:
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.namePos][npc2.lips+]间迸发出[npc2.A_sob+]，[npc2.speech(不！不要！求求你，放过我！)]",
										"[npc2.Name]发出一声绝望的[npc2.sob]，苦苦乞求，[npc2.speech(求求你！不要这样做！放过我！)]",
										"[npc2.Name]痛苦地[npc2.sobsVerb]，[npc2.she]哀求道，[npc2.speech(不要！快停下！求求你快走！)]"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]飘出[npc2.A_moan+]，[npc2.speech(就是这样，用我的屁股！)]",
									"[npc2.Name]发出一声[npc2.moan]，祈求着说，[npc2.speech(求你了！用我的屁股！)]",
									"[npc2.Name]一边不停娇喘一边哀求你，[npc2.speech(来嘛，用我的屁股！)]"));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ASS);
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			}
		}
	};
	
	public static final SexAction FORCE_COCK_OVER_ASS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "尻交挑逗";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.namePos][npc2.cock]的[npc2.cockHead]在你的肉瓣中抚动。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			boolean canReachPenis = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaPenetration.PENIS)) {
					canReachPenis = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaPenetration.PENIS).contains(SexAreaPenetration.FINGER)) {
					canReachPenis = true;
				}
			} catch(Exception ex) {
			}
			if(!canReachPenis) { // No available finger-penis actions, so can't reach penis
				return false;
			}
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"稍微挪动了一下[npc.her]的[npc.hips]，[npc.name]将屁股向后伸，并将[npc.her]的臀肉用力挤到中间，"
									+ "然后在形成的臀缝中缓慢地抚动[npc2.namePos][npc2.cock+]的[npc2.cockHead]。",

							"温柔地捧着[npc.her]的[npc.ass]，[npc.name]慢慢地将[npc.her]的臀肉推到中间，"
									+ "然后开始让[npc2.namePos][npc2.cock+]玩弄臀缝。",

							"温柔地摸着[npc.her]的两瓣臀肉，[npc.name]慢慢地推到一起，"
									+ "然后让[npc2.her][npc2.cock+]的[npc2.cockHead]在形成的臀缝挑弄。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"稍微挪动了[npc.her]的[npc.hips]，[npc.name]将屁股向后伸出，并且用力地将[npc.her]的臀肉收紧，"
									+ "然后开始强蛮地用形成的臀缝上下抚动[npc2.namePos][npc2.cock+]的[npc2.cockHead]。",

							"紧紧抓住[npc.her]的[npc.ass]，[npc.name]粗鲁地将[npc.her]的臀肉挤到中间，"
									+ "然后开始用臀缝强迫性地挑逗[npc2.namePos][npc2.cock+]。",

							"紧紧抓着[npc.her]的两瓣臀肉，[npc.name]使劲地将它们推到一起，"
									+ "然后开始粗鲁地用形成的臀缝挑逗[npc2.namePos][npc2.cock+]的[npc2.cockHead]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"稍微挪动了一下[npc.her]的[npc.hips]，[npc.name]将屁股向后伸出，饥渴地将[npc.her]的屁股挤到一起，"
									+ "然后愉快地让[npc2.namePos][npc2.cock+]的[npc2.cockHead]在形成的臀缝中抚动。",

							"紧紧地抓着[npc.her]的[npc.ass]，[npc.name]慢慢将[npc.her]的臀肉挤到中间，"
									+ "然后开始用臀缝饥渴地挑逗[npc2.namePos][npc2.cock+]。",

							"紧紧抓着[npc.her]的两瓣臀肉，[npc.name]饥渴地将它们推到一起，"
									+ "然后开始用形成的臀缝挑逗[npc2.namePos][npc2.cock+]的[npc2.cockHead]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"稍微挪动了一下[npc.her]的[npc.hips]，[npc.name]将屁股向后伸出，饥渴地将[npc.her]的屁股挤到一起，"
									+ "然后愉快地让[npc2.namePos][npc2.cock+]的[npc2.cockHead]在形成的臀缝中抚动。",

							"紧紧地抓着[npc.her]的[npc.ass]，[npc.name]慢慢将[npc.her]的臀肉挤到中间，"
									+ "然后开始用臀缝饥渴地挑逗[npc2.namePos][npc2.cock+]。",

							"紧紧抓着[npc.her]的两瓣臀肉，[npc.name]饥渴地将它们推到一起，"
									+ "然后开始用形成的臀缝挑逗[npc2.namePos][npc2.cock+]的[npc2.cockHead]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos]期待着将[npc2.cock+]插进[npc.namePos][npc.ass+]，一声轻柔的[npc2.moan]从[npc2.her][npc2.lips+]间飘出。",
									"随着[npc2.she]准备将[npc2.her][npc2.cock+]插进[npc.namePos][npc.ass+]里，[npc2.Name]发出了温柔地的[npc2.moan]。",
									"[npc2.name]想像着操[npc.namePos][npc.ass+]的触感，发出了温柔喜悦的[npc2.moansVerb]。"));
							break;
						case DOM_ROUGH:
						case SUB_EAGER:
						case DOM_NORMAL:
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出，随即[npc2.she]急切地攥住了[npc.namePos][npc.hand+]，",
									"随着[npc2.she]准备急切地将[npc2.her][npc2.cock+]插进[npc.namePos][npc.ass+]里，[npc2.Name]发出了[npc2.a_moan+]。",
									"[npc2.name]想像着操[npc.namePos][npc.ass+]的触感，发出了兴奋喜悦的[npc2.moansVerb]。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_sob+]，恐惧[npc.namePos][npc.cock+]插进[npc2.her][npc2.ass+]可能的感觉。",
									"[npc2.name]预感到自己[npc2.cock+]要被迫插入[npc.namePos][npc.ass+]，不禁发出一阵[npc2.a_sob+]。",
									"[npc2.name]预感到自己[npc2.cock+]要被迫插入[npc.namePos][npc.ass+]，不禁痛苦地[npc2.sobVerb]着。"));
							break;
					}
					
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]间飘出一声轻柔的[npc2.moan]，[npc2.speech(求你了！让我操你的屁股！)]",
									"[npc2.Name]放出一声温柔的娇喘，祈求着说，[npc2.speech(就是这样，我需要你操我的屁股！)]",
									"[npc2.Name]一边愉快地喘息，一边请求，[npc2.speech(好舒服！求求你让我插你！求求了！)]"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]中爆发出一阵[npc2.A_moan+]，[npc2.speech(啊哈，你这个下贱的婊子！你想我插你的屁股是吗？)]",
									"[npc2.Name]发出一声迷乱的[npc2.moan]，咆哮着说，[npc2.speech(你想我猛插你的屁股是吧，你这个肮脏的婊子？！)]",
									"[npc2.Name]一边愉悦地喘息，一边咆哮着说，[npc2.speech(你的屁股忍不住想被插了是吧，婊子？！)]"));
							break;
						case SUB_EAGER:
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]中爆发出一阵[npc2.A_moan+]，[npc2.speech(好耶！我一直都想试试你的屁股！)]",
									"[npc2.Name]发出一声迷乱的[npc2.moan]，对[npc.name]说，[npc2.speech(确实，你的屁股需要好好操一操！)]",
									"[npc2.Name]一边愉悦地喘息，一边喊着，[npc2.speech(我现在就要操你的屁股！)]"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]飘出[npc2.A_moan+]，[npc2.speech(好耶！让我操你的美臀！)]",
									"[npc2.Name]发出一声[npc2.moan]，大喊，[npc2.speech(求你了！让我操你的屁股！)]",
									"[npc2.Name]大声喘气，[npc2.she]对[npc.herHim]说，[npc2.speech(拜托，让我操你！)]"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.her][npc2.lips+]中发出[npc2.A_sob+]，[npc2.speech(不！不要！求求你，离我远点！)]",
									"[npc2.Name]发出一声绝望的[npc2.sob]，苦苦乞求，[npc2.speech(求求你！不要这样做！放过我！)]",
									"[npc2.Name]痛苦地[npc2.sobsVerb]，[npc2.she]哀求道，[npc2.speech(不要！快停下！求求你快走！)]"));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ASS);
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
			}
		}
	};
	
	
	public static final SexAction ASS_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "开始尻交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.cock+]放在[npc2.namePos]的臀肉间滑动。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]慢慢地将[npc2.namePos][npc2.assSize]屁股的臀肉推到一起，将[npc.cock+][npc.cockHead]顶在[npc2.namePos]的[npc2.ass]上，"
									+ "然后开始在形成的臀缝中温柔地抽送[npc.her][npc.cock+]。",

							"[npc.Name]将[npc.her][npc.cock][npc.cockHead+]柔缓地抵在[npc2.namePos][npc2.ass+]上，"
									+ "[npc.she]温柔地将[npc2.her]的臀肉挤到一起，然后开始操形成的臀缝。"));
					break;
				case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]急切地将[npc2.namePos][npc2.assSize]屁股的臀肉推到一起，将[npc.cock+][npc.cockHead]顶在[npc2.namePos]的[npc2.ass]上，"
									+ "然后开始贪婪地将[npc.her]的[npc.hips]推向自己的胯部，开始操臀缝。",

							"[npc.Name]将[npc.her][npc.cock][npc.cockHead+]抵在[npc2.namePos][npc2.ass+]上，"
									+ "然后急切地将[npc2.her]的臀肉挤到一起，开始操这个形成的臀缝。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗鲁地将[npc2.namePos][npc2.assSize]屁股的臀肉推到一起，用力地将[npc.cock+][npc.cockHead+]顶在[npc2.namePos]的[npc2.ass]上，"
									+ "然后暴力地[npc.her]的[npc.hips]推向自己，开始操形成的臀缝。",

							"[npc.Name]将[npc.her][npc.cock][npc.cockHead+]抵在[npc2.namePos][npc2.ass+]上，"
									+ "然后开始粗鲁地挤压[npc2.her]的臀肉到一起，大力操弄形成的肉缝。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos][npc2.assSize]屁股的臀肉推到一起，用[npc.cock+][npc.cockHead+]顶在[npc2.namePos]的[npc2.ass]上，"
									+ "然后把[npc.her]的[npc.hips]推向自己，开始操臀缝。",

							"[npc.Name]将[npc.her][npc.cock][npc.cockHead+]抵在[npc2.namePos][npc2.ass+]上，"
									+ "然后将[npc2.her]的臀肉推到一起，操起了臀缝。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]开始使用[npc2.Name]的臀部，[npc2.her]发出一声轻柔的[npc2.moan]，"
										+ "[npc2.she]轻轻地扭动[npc2.hips+]，让[npc.namePos][npc.cock+]更加深入自己的臀缝。",
	
								"[npc2.name]轻柔地[npc2.moan]，开始温柔地将[npc2.ass]压向[npc.namePos]的胯部，"
										+ "将[npc.her][npc.cock+]更深地插入自己的臀缝。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.name]开始用[npc2.her]的屁股时，[npc2.Name]发出[npc2.a_moan+]，"
										+ "[npc2.she]饥渴地扭动[npc2.hips+]，让[npc.namePos][npc.cock+]更加深入自己的臀缝。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地将[npc2.ass]撞向[npc.namePos]的胯部，"
										+ "饥渴地让[npc.namePos][npc.cock+]挺入[npc2.her]的臀缝深处。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.name]开始用[npc2.her]的屁股时，[npc2.Name]发出[npc2.a_moan+]，"
										+ "[npc2.she]暴力地将[npc2.her][npc2.hips+]顶向[npc.herHim]，粗鲁地将[npc.cock+]往[npc2.her]的臀缝间挤入。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始猛烈地将[npc2.ass]撞向[npc.namePos]的胯部，"
										+ "粗暴地迫使[npc.herHim]把[npc.her][npc.cock+]深深地插入[npc2.her]的臀缝之间。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.name]开始用[npc2.her]的屁股时，[npc2.Name]发出[npc2.a_moan+]，"
										+ "[npc2.she]饥渴地扭动[npc2.hips+]，让[npc.namePos][npc.cock+]更加深入自己的臀缝。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地将[npc2.ass]撞向[npc.namePos]的胯部，"
										+ "饥渴地让[npc.namePos][npc.cock+]挺入[npc2.her]的臀缝深处。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"当[npc.name]开始用[npc2.her]的屁股时，[npc2.Name]发出[npc2.a_moan+]，"
										+ "[npc2.she]扭动[npc2.hips+]，让[npc.namePos][npc.cock+]更加深入自己的臀缝中。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始将[npc2.ass]压向[npc.namePos]的胯部，"
										+ "让[npc.namePos][npc.cock+]挺入[npc2.her]的臀缝深处。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]开始用[npc2.her]的屁股，[npc2.Name]发出[npc2.a_sob+]，并且，"
										+ "泪水不停地从[npc2.her]的[npc2.face]上淌下，[npc2.she]撕心裂肺地哀求[npc.herHim]停下。",
	
								"伴随[npc2.a_sob+]，[npc2.name]试着将[npc.name]推开；"
										+ "但[npc.namePos]不请自来的[npc.cock]却在臀缝中插得更深，泪水从[npc2.her]的[npc2.face]上流了下来。"));
						break;
					default:
						break;
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};

	private static String getTargetedCharacterResponse(SexAction action) {
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"作为回应，[npc2.Name]热切地上下扭动[npc2.her]的[npc2.hips]，"
								+ "[npc2.she]控制着[npc.namePos][npc.cock+]在[npc2.her]的臀缝间进进出出，发出一阵[npc2.a_moan+]。",
	
						"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
								+ "然后，[npc2.she]热切地扭动[npc2.her]的[npc2.hips]，让[npc.namePos][npc.cock+]在[npc2.her][npc2.ass+]的臀缝中上下抚动。",
	
						"[npc2.name]愉悦地[npc2.moaning]着，热切地前后摆动[npc2.her][npc2.hips+]，"
								+ "做出有助于[npc.cock+]在[npc2.her]臀缝间来回磨蹭的动作，乞求[npc.Name]继续尻交[npc2.herHim]。"));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.name]拼命想要逃离[npc.namePos]的[npc.cock]，但没能成功，"
								+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地乞求[npc.Name]从自己[npc2.ass+]里拔出来，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
	
						"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
								+ "[npc2.she]恳求[npc.name]从自己[npc2.ass+]里拔出来，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
	
						"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
								+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]从[npc2.her][npc2.ass+]里拔出来。"));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"作为回应，[npc2.Name]开始上下摆动[npc2.her]的[npc2.hips]，"
								+ "[npc2.she]控制着[npc.namePos][npc.cock+]在[npc2.her]的臀缝间进进出出，发出一阵[npc2.a_moan+]。",
	
						"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
								+ "之后，摇动着[npc2.her]的[npc2.hips]，[npc2.she]用[npc2.her][npc2.ass+]的臀缝上下挑弄[npc.namePos][npc.cock+]。",
	
						"[npc2.name]一边愉悦地[npc2.Moaning]，一边前后摆动[npc2.her][npc2.hips+]，"
								+ "做出有助于[npc.cock+]在[npc2.her]臀缝间来回磨蹭的动作，乞求[npc.Name]继续尻交[npc2.herHim]。"));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"作为回应，[npc2.Name]开始温柔地上下摆动[npc2.her]的[npc2.hips]，"
								+ "[npc2.she]发出一声轻柔的[npc2.moan]，控制着[npc.namePos][npc.cock+]在[npc2.her]臀缝中进进出出。",
	
						"[npc2.namePos][npc2.lips+]间飘出一声轻柔的[npc2.moan]，"
								+ "[npc2.she]缓缓地扭动[npc2.hips]，让[npc.namePos][npc.cock+]在[npc2.her][npc2.ass+]臀缝间上下抽送。",
	
						"[npc2.name]愉悦地[npc2.Moaning]着，温柔地前后摆动[npc2.hips+]，"
								+ "做出有助于[npc.cock+]在[npc2.her]臀缝间来回磨蹭的动作，乞求[npc.Name]继续尻交[npc2.herHim]。"));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"作为回应，[npc2.Name]开始激烈地上下摆动[npc2.her]的[npc2.hips]，"
								+ "[npc2.she]控制着[npc.namePos][npc.cock+]在[npc2.her]的臀缝间抽插进出，发出一阵[npc2.a_moan+]。",
	
						"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
								+ "[npc2.she]粗鲁地摇动[npc2.hips]，迫使[npc.namePos][npc.cock+]在[npc2.her][npc2.ass+]的臀缝间上下抽送。",
	
						"[npc2.name]高兴地[npc2.Moaning]，霸道地前后摆动[npc2.her][npc2.hips+]，"
								+ "强迫[npc.name]将[npc.cock+]在[npc2.her]臀缝间来回磨蹭，命令[npc.name]继续尻交[npc2.herHim]。"));
				break;
		}
		return "";
	}
	
	public static final SexAction ASS_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "尻交(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地抽插[npc2.namePos]的臀缝。";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"在[npc2.namePos]的臀缝之间温柔地滑动[npc.her][npc.cock+]，"
							+ "[npc.name]开始前后摇摆[npc.hips]，缓缓地尻交[npc2.name]，每次推入都会发出一阵轻微的[npc.moan]。",

					"在[npc2.namePos]的臀缝之间温柔地滑动[npc.her][npc.cock+]，"
							+ "[npc.name]开始温柔地将[npc.her][npc.hips]向前推，当[npc.she]愉悦地尻交着[npc2.herHim]时，漏出[npc.moans+]。",

					"[npc.name]轻轻地将[npc2.namePos][npc2.ass+]挤在一起，并在温柔地前后抽动[npc.her]的[npc.hips]时发出一小声[npc.moan]，"
							+ "[npc.she]在柔缓地尻交[npc2.herHim]时，将鼻子贴近吸入[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "尻交(普通)";
		}

		@Override
		public String getActionDescription() {
			return "继续抽插[npc2.namePos]的臀缝。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"热切地在[npc2.namePos]的臀缝之间抽插[npc.her][npc.cock+]，"
							+ "[npc.name]开始大力地前后挺动[npc.her]的[npc.hips]，贪婪地尻交[npc2.name]，每次推入都会发出一阵[npc.a_moan+]。",

					"[npc.name]拼命地用[npc.cock+]在[npc2.namePos]的臀缝之间抽插，"
							+ "[npc.name]开始疯狂地挺进[npc.her]的[npc.hips]，贪婪地尻交着[npc2.herHim]时，发出[npc.moans+]。",

					"[npc.name]贪婪地将[npc2.namePos][npc2.ass+]挤在一起，并在发狂地前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
							+ "[npc.she]在激烈地尻交[npc2.herHim]时，将鼻子贴近吸入[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "尻交(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地操[npc2.namePos]的臀缝。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"粗暴地用[npc.her][npc.cock+]冲撞[npc2.namePos]的臀缝，"
							+ "[npc.name]开始支配性地前后挺动[npc.her]的[npc.hips]，用力地尻交[npc2.name]，每次推入都会发出一阵[npc.a_moan+]。",

					"强行将[npc.her][npc.cock+]插入[npc2.namePos][npc2.ass+]的臀缝之间，"
							+ "[npc.name]开始暴力地挺进[npc.her]的[npc.hips]，粗鲁地尻交着[npc2.herHim]，每次猛击都发出[npc.a_moan+]。",

					"[npc.name]霸道地将[npc2.namePos][npc2.ass+]挤在一起，来回撞击[npc.her]的[npc.hips]，同时发出[npc.a_moan+]，"
							+ "[npc.she]在粗鲁地尻交[npc2.herHim]时，将鼻子顶在对方身上吸入[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "尻交(普通)";
		}

		@Override
		public String getActionDescription() {
			return "继续抽插[npc2.namePos]的臀缝。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.cock+]挤入[npc2.namePos]的臀缝，"
							+ "[npc.name]前后挺进[npc.her]的[npc.hips]，贪婪地尻交着[npc2.name]，每次猛推都会发出[npc.a_moan+]。",

					"[npc.name]将[npc.cock+]插入[npc2.namePos][npc2.ass+]的臀缝之间，"
							+ "[npc.name]开始挺进[npc.her]的[npc.hips]，舒爽地尻交着[npc2.herHim]，同时发出[npc.moans+]。",

					"[npc.name]将[npc2.namePos][npc2.ass+]挤在一起，并在前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
							+ "[npc.she]在尻交[npc2.herHim]时吸入[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "尻交(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "渴求地操[npc2.namePos]的臀缝。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"热切地在[npc2.namePos]的臀缝之间抽插[npc.her][npc.cock+]，"
							+ "[npc.name]开始大力地前后挺动[npc.her]的[npc.hips]，贪婪地尻交[npc2.name]，每次推入都会发出一阵[npc.a_moan+]。",

					"[npc.name]拼命地用[npc.cock+]在[npc2.namePos]的臀缝之间抽插，"
							+ "[npc.name]开始疯狂地挺进[npc.her]的[npc.hips]，贪婪地尻交着[npc2.herHim]时，发出[npc.moans+]。",

					"[npc.name]贪婪地将[npc2.namePos][npc2.ass+]挤在一起，并在发狂地前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
							+ "[npc.she]在激烈地尻交[npc2.herHim]时，将鼻子贴近吸入[npc2.her]的[npc2.scent]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "反抗尻交";
		}

		@Override
		public String getActionDescription() {
			return "试着让你的[npc.cock]远离[npc2.namePos][npc2.ass+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]慢慢地将自己的[npc2.ass]撞向[npc.herHim]，继续温柔地强迫[npc.her][npc.cock+]在[npc2.her]的臀缝中抽送。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "牢牢地将[npc.herHim]固定在原位，"
									+ "温柔地将[npc2.ass]推向[npc.namePos]的胯部，强行将[npc.her][npc.cock+]夹在臀缝中。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，请求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.Moaning]着，完全无视了[npc.her]的反抗，"
									+ "将[npc.her][npc.cock+]深深陷入自己的臀缝中，缓缓地顶着[npc.herHim]磨蹭身体。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]强蛮地将自己的[npc2.ass]撞向[npc.herHim]，继续粗鲁地强迫[npc.her][npc.cock+]在[npc2.her]的臀缝中抽送。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "霸道地将[npc.herHim]固定在原位，"
									+ "强蛮地将[npc2.her]的[npc2.ass]推向[npc.her]的胯部，[npc2.she]粗暴地强迫[npc.namePos][npc.cock+]夹在[npc2.her]臀缝中。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，哀求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.Moaning]着，完全无视了[npc.her]的反抗，"
									+ "强迫[npc.her][npc.cock+]深深插入自己的臀缝，粗暴地顶着[npc.herHim]磨蹭身体。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管拼命尝试，[npc.name]依然无法将自己[npc.cock+]抽离[npc2.name]，焦急地发出了[npc.a_sob+]。无视[npc.her]的反抗，"
									+ "[npc2.she]热切地将自己的[npc2.ass]快速撞向[npc.herHim]，强迫[npc.her][npc.cock+]在[npc2.her]的臀缝中抽送。",

							"[npc.namePos][npc.lips]间爆发出一阵[npc.A_sob+]，[npc.she]无力地尝试推开[npc2.name]，但[npc2.name]完全无视了[npc.her]的反抗，"
									+ "牢牢地将[npc.herHim]固定在原位，"
									+ "热切地将[npc2.her]的[npc2.ass]推向[npc.her]的胯部，[npc2.she]饥渴地将[npc.namePos][npc.cock+]夹在[npc2.her]臀缝中。",

							"[npc.name]悲痛地[npc.Sobbing]，虚弱地反抗着[npc2.name]，哀求[npc2.name]放过[npc.her]的[npc.cock]。"
									+ "[npc2.name]愉悦地[npc2.Moaning]着，完全无视了[npc.her]的反抗，"
									+ "愉悦地强迫[npc.her][npc.cock+]插入自己的臀缝，急切地顶着[npc.herHim]磨蹭身体。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止尻交";
		}

		@Override
		public String getActionDescription() {
			return "将你[npc.cock+]从[npc2.namePos]的臀缝中拔出。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将[npc.cock+]从[npc2.namePos][npc2.ass+]的臀缝中扯出，"
									+ "霸道地在[npc2.her][npc2.ass+]的臀缝中最后一次来回磨蹭[npc.cock]的[npc.cockHead]，然后将[npc.hips]移开。",

							"作为尻交的结尾，[npc.name]最后一次深深插进[npc2.namePos][npc2.ass+]，之后才将对方推开。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.cock+]从[npc2.namePos][npc2.ass+]臀缝间滑出，"
									+ "[npc.she]最后一次用[npc.her][npc.cock]的[npc.cockHead]拍打[npc2.her]的[npc2.ass]，然后收回[npc.hips]。",

							"最后一次深深地插进[npc2.namePos][npc2.ass+]的臀缝后，[npc.name]将家伙抽出，结束了这次尻交。"));
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
	
	
	public static final SexAction USING_COCK_AGAINST_ASS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "开始被尻交";
		}

		@Override
		public String getActionDescription() {
			return "强迫[npc2.namePos][npc2.cock+]插进你[npc.ass+]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"发出一声轻柔的[npc.moan]，[npc.name]慢慢地将[npc2.namePos][npc2.cock+]滑进[npc.her][npc.ass+]间，"
									+ "然后轻轻地将[npc.her]的臀肉推到一起，强迫着把[npc2.her]的[npc2.cock]放进形成的臀缝中。",

							"将[npc.her][npc.ass+]对准[npc2.namePos][npc2.cock+]，[npc.name]慢慢地把[npc.her]的臀肉挤到一起，"
									+ "当[npc.she]将[npc2.her]的[npc2.cock]塞进形成的臀缝时，忍不住轻柔地[npc.moaning]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]发出一阵[npc.a_moan+]，热切地让[npc2.namePos][npc2.cock+]滑向[npc.her][npc.ass+]，"
									+ "然后饥渴地将[npc.her]的臀瓣推到一起，并强迫[npc2.her]的[npc2.cock]插进形成的臀缝中。",

							"把自己[npc.ass+]对准[npc2.namePos][npc2.cock+]，[npc.name]急切地将自己的臀肉推到一起，"
									+ "[npc.she]使劲将[npc2.her]的[npc2.cock]塞进臀缝中，忍不住[npc.moaning+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]发出一阵[npc.a_moan+]，粗暴地让[npc2.namePos][npc2.cock+]滑向[npc.her][npc.ass+]，"
									+ "然后贪婪地将[npc.her]的臀肉推到一起，并野蛮地强迫[npc2.her]的[npc2.cock]插进形成的臀缝中。",

							"把自己[npc.ass+]对准[npc2.namePos][npc2.cock+]，[npc.name]粗暴地将自己的臀肉推到一起，"
									+ "当[npc.she]使劲将[npc2.her]的[npc2.cock]塞进臀缝中的时候，忍不住[npc.moaning+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]发出一阵[npc.a_moan+]，让[npc2.namePos][npc2.cock+]滑弄[npc.her][npc.ass+]，"
									+ "然后饥渴地将[npc.her]的臀瓣推到一起，并强迫[npc2.her]的[npc2.cock]插进形成的臀缝中。",

							"把自己[npc.ass+]对准[npc2.namePos][npc2.cock+]，[npc.name]急切地将自己的臀肉推到一起，"
									+ "当[npc.she]使劲将[npc2.her]的[npc2.cock]塞进臀缝中的时候，忍不住[npc.moaning+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]放出一声轻柔的[npc2.moan]，当[npc2.she]开始操[npc.her]的臀缝时，急切地朝着对方挺进自己的[npc2.hips]。",
	
								"[npc2.name]轻柔地[npc2.moan]，温柔地挺进自己的[npc2.hips]，将自己[npc2.cock+]插进[npc.namePos][npc.ass+]臀缝里。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]忍不住发出[npc2.a_moan+]，非常急切地朝对方挺进自己的[npc2.hips]，使劲地操[npc.her]臀缝。",
	
								"[npc2.name]一边发出[npc2.a_moan+]，一边急切地将自己的[npc2.hips]撞向对方，把[npc2.cock+]深深埋入[npc.namePos][npc.ass+]的臀缝里。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]忘我地发出一阵[npc2.a_moan+]，为了提醒[npc.name]谁才是主导者，"
										+ "[npc2.she]粗暴地撞击[npc2.hips]，无情地暴插[npc.her]的臀缝。",
	
								"[npc2.name]发出[npc2.a_moan+]，粗暴地挺进自己的[npc2.hips]，"
										+ "[npc2.she]无情地暴操[npc.namePos]的臀缝，宣告着自己的支配权。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]忍不住发出[npc2.a_moan+]，朝对方挺进自己的[npc2.hips]，使劲地操[npc.her]臀缝。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.hips]撞向对方，把[npc2.cock+]深深埋入[npc.namePos][npc.ass+]的臀缝里。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]强行用[npc.ass+]夹紧[npc2.her]的[npc2.cock]，[npc2.Name]忍不住发出[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着，拼命试着推开[npc.name]。",
	
								"[npc.name]强行将[npc2.name]的[npc2.cock]深深塞入自己的臀缝，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]贪婪地将自己[npc2.cock+]撞入[npc.namePos][npc.ass+]的深处，"
								+ "使劲尻交着[npc.herHim]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
	
						"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]深深插进[npc.namePos][npc.assSize][npc.ass]的臀缝中。",
								
						"[npc2.name]愉悦地[npc2.moaning]着，急切地用[npc2.cock+]前后磨蹭[npc.namePos][npc.ass+]的臀缝。"));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.name]无法将[npc2.cock]从[npc.namePos]的[npc.ass]中拔出，"
								+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
	
						"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
								+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.cock+]在自己[npc.ass+]的臀缝间继续抽插。",
	
						"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.cock]从[npc.namePos][npc.ass+]之间抽离。"));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]温柔地将自己[npc2.cock+]伸入[npc.namePos][npc.ass+]的臀缝中，"
								+ "[npc2.she]发出一声轻柔的[npc2.moan]，开始尻交[npc.name]。",
	
						"[npc2.name]温柔地将[npc2.cock+]挺入[npc.namePos][npc.ass][npc.assSize]的臀肉间，口中飘出一声轻柔的[npc2.moan]。",
								
						"[npc2.name]愉悦地[npc2.moaning]着，在[npc.namePos][npc.ass+]的臀缝中猛插自己[npc2.cock+]。"));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]粗暴地将[npc2.cock+]深深插入[npc.namePos][npc.ass+]，"
								+ "粗鲁地尻交着[npc.herHim]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
	
						"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地将[npc2.cock+]深深插进[npc.namePos][npc.assSize][npc.ass]的臀缝中。",
								
						"[npc2.name]愉悦地[npc2.moaning]着，粗鲁地在[npc.namePos][npc.ass+]的臀缝中猛插自己[npc2.cock+]。"));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]将[npc2.cock+]深深插入[npc.namePos][npc.ass+]，"
								+ "[npc2.she]尻交着[npc.herHim]，潮水般的快感让[npc2.she]忍不住发出[npc2.a_moan+]。",
	
						"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]深深插进[npc.namePos][npc.assSize][npc.ass]的臀缝中。",
								
						"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.cock+]前后磨蹭[npc.namePos][npc.ass+]的臀缝。"));
				break;
		}
		return "";
	}
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "被尻交(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地让[npc2.namePos][npc2.cock+]插你的臀缝。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一声轻柔的[npc.moan]，温柔地将[npc.hips]向后拱起，让[npc2.namePos][npc2.cock+]在自己[npc.ass+]中插得更深。",

					"[npc.name]发出一声轻柔的[npc.moan]，温柔地向后拱起[npc.hips]，使得[npc2.namePos][npc2.cock+]在[npc.her]的臀缝中前后抽插。",

					"一边慢慢地将自己的[npc.hips]往后送，"
							+ "一声轻柔的娇喘从[npc.namePos][npc.lips+]间发出，[npc.her]设法让[npc2.namePos][npc2.cock+]在自己[npc.ass+]中上下抽动。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "被尻交";
		}

		@Override
		public String getActionDescription() {
			return "使用[npc2.namePos][npc2.cock+]来满足自己的臀缝。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一阵[npc.a_moan+]，热切地向后拱起[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己[npc.ass+]的臀缝中插得更深。",

					"[npc.name]发出一阵[npc.a_moan+]，贪婪地将自己的[npc.hips]往后推，迫使[npc2.namePos][npc2.cock+]在[npc.her]的臀缝中前后抽插。",

					"[npc.name]饥渴地向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.she]贪婪地设法让[npc2.namePos][npc2.cock+]在自己[npc.ass+]的臀缝之间来回磨蹭。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "被尻交(粗鲁)";
		}

		@Override
		public String getActionDescription() {
			return "用你的臀缝粗暴地吞进吐出[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"发出[npc.a_moan+]，[npc.name]暴力地将自己的[npc.hips]向后撞去，强行让[npc2.namePos][npc2.cock+]在自己[npc.ass+]中插得更深。",

					"[npc.name]发出一阵[npc.a_moan+]，粗暴地向后猛撞[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己臀缝中前后抽插。",

					"[npc.name]粗鲁地向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.she]霸道地设法让[npc2.namePos][npc2.cock+]在自己[npc.ass+]的臀缝之间来回磨蹭。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "被尻交";
		}

		@Override
		public String getActionDescription() {
			return "将你[npc.ass+]抵向[npc2.name]，迫使[npc2.her]的[npc2.cock]插进你的臀缝。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一阵[npc.a_moan+]，将[npc.hips]向后拱起，强迫[npc2.namePos][npc2.cock+]在自己[npc.ass+]的臀缝中插得更深。",

					"[npc.name]发出一阵[npc.a_moan+]，将自己的[npc.hips]往后推，迫使[npc2.namePos][npc2.cock+]在[npc.her]的臀缝中前后抽插。",

					"[npc.name]向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.she]设法让[npc2.namePos][npc2.cock+]在自己[npc.ass+]的臀缝之间来回磨蹭。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "尻交(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "急切地将你[npc.ass+]抵向[npc2.name]，迫使[npc2.her]的[npc2.cock]插进你的臀缝。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]发出一阵[npc.a_moan+]，热切地向后拱起[npc.hips]，强迫[npc2.namePos][npc2.cock+]在自己[npc.ass+]的臀缝中插得更深。",

					"[npc.name]发出一阵[npc.a_moan+]，贪婪地将自己的[npc.hips]往后推，迫使[npc2.namePos][npc2.cock+]在[npc.her]的臀缝中前后抽插。",

					"[npc.name]饥渴地向后挺起[npc.hips]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.she]贪婪地设法让[npc2.namePos][npc2.cock+]在自己[npc.ass+]的臀缝之间来回磨蹭。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_AGAINST_ASS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "反抗尻交";
		}

		@Override
		public String getActionDescription() {
			return "努力让你[npc.ass+]远离[npc2.namePos][npc2.cock+]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"眼泪慢慢涌上[npc.eyes]，再也无法忍住哭意，[npc.Name]一阵阵地啜泣起来，"
									+ "试着推开[npc2.namePos]的[npc2.cock]但徒劳无功，[npc2.name]继续温柔地操着[npc.her]的臀瓣。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.ass]从[npc2.namePos]的[npc2.cock]抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然从容地在[npc.her]的臀缝间继续抽插。",

							"[npc.name]拼命地尝试将[npc.ass]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然温柔地在[npc.her]臀缝间深深抽插。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]感到泪水涌上自己的眼窝，下一瞬间，[npc.she]开始啜泣起来，"
									+ "试着推开[npc2.namePos]的[npc2.cock]但徒劳无功，[npc2.name]热切地依然操着[npc.her]的臀缝。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.ass]从[npc2.namePos]的[npc2.cock]抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然急切地在[npc.her]的臀缝间继续抽插。",

							"[npc.name]拼命地尝试将[npc.ass]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然急切地在[npc.her]臀缝间深深抽插。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"眼泪慢慢涌上[npc.eyes]，再也无法忍住哭意，[npc.Name]一阵阵地啜泣起来，"
									+ "试着推开[npc2.namePos]的[npc2.cock]但徒劳无功，[npc2.she]保持粗暴的节奏操着[npc.her]的臀缝。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将[npc.ass]从[npc2.namePos]的[npc2.cock]抽离，"
									+ "[npc.she]绝望地挣扎着，但[npc2.name]依然粗暴地在[npc.her]的臀缝间继续抽插。",

							"[npc.name]拼命地尝试将[npc.ass]挪开，[npc.she]痛苦地[npc.sobVerb]着，但[npc2.namePos][npc2.cock+]依然粗暴地在[npc.her]臀缝间深深抽插。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止尻交";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.her]的[npc2.cock]从你的臀缝中拔出来。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]的[npc2.cock]从自己的臀缝间抽出，[npc.she]愤怒地咆哮着，命令[npc2.name]停下。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后猛地将[npc2.her]的[npc2.cock]从自己的臀缝中抽出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]的[npc2.cock]从自己的臀缝间抽出，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]停下。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her]的[npc2.scent]，然后将[npc2.her]的[npc2.cock]从自己的臀缝中抽出。"));
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
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续抽插[npc.namePos]臀沟的渴望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	
}
