package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
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
public class PlayerTalk {
	
	public static final SexAction DIRTY_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Main.sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
				return CorruptionLevel.ZERO_PURE;
			} else {
				return CorruptionLevel.ONE_VANILLA;
			}
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					default:
						return Main.sex.getCharacterPerformingAction().isFeminine()?"饥渴地呻吟":"饥渴地哼唧";
					case DOM_ROUGH:
						return "狂野咆哮";
					case SUB_RESISTING:
						return "抗议地哀叫";
				}
			}
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
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
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					default:
						return "因为你的嘴被堵上了，你没法对[npc2.name]说骚话，但是<i>还能</i>发出一些情欲的"+(Main.sex.getCharacterPerformingAction().isFeminine()?"呻吟":"低吟") + "。";
					case DOM_ROUGH:
						return "因为你的嘴被堵上了，你没法对[npc2.name]说什么很过分的淫语，但是<i>还能</i>发出一些低吼。";
					case SUB_RESISTING:
						return "因为你的嘴被堵上了，你没法乞求[npc2.name]停下来，但是<i>还能</i>发出一些抗拒的叫喊表示自己的不满。";
				}
			}
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				default:
					return "对[npc2.name]说骚话。";
				case DOM_ROUGH:
					return "对[npc2.name]说过分的话。";
				case SUB_RESISTING:
					return "求求[npc2.herHim]别操了。";
			}
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
								"[npc.her]的嘴被堵住了。[npc.nameIsFull]只能靠发出一连串低沉粗哑的咆哮让[npc2.name]知道[npc.sheIs]仍牢牢掌握着一切。",
								"[npc.Name]想要让[npc2.name]知道[npc.sheIs]仍然掌握着局势，但由于嘴被堵住，[npc.sheIs]只能发出一连串威吓的粗哑咆哮。",
								"[npc.her]虽然被堵住嘴，但仍然成功发出一连串低沉粗哑的咆哮，让[npc2.name]知道[npc.sheIs]仍然掌握着局势。");
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"因为[npc.namePos]的嘴被堵住了，[npc.she]除了求饶外唯一能做的就只有发出一连串可怜的呜咽与低沉的啜泣。",
								"因为[npc.her]的嘴目前被堵住了，[npc.nameIsFull]只能靠发出一连串低沉的啜泣与痛苦的呜咽来让[npc2.name]知道[npc.she]想被放走。",
								"由于[npc.her]嘴被堵住了，[npc.Name]只能靠发出一连串痛苦的呜咽和低沉的抽泣来让[npc2.name]知道[npc.she]渴望被放。",
								"即使[npc.her]嘴被堵住了，[npc.nameIsFull]仍然成功地发出一连串痛苦的呜咽与低沉的啜泣来让[npc2.name]知道[npc.she]希望现在这件事停下来。");
				}
			}
			
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return UtilText.returnStringAtRandom(
						"[npc.name]不想吵醒[npc2.name]，悄悄地玩笑道，",
						"[npc.name]压低声音以免吵醒[npc2.name]，轻声低语道，")
						+ Main.sex.getDirtyTalk(Main.game.getPlayer());
			}
			
			if(Main.sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.ALL_FOURS)) {
				
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"你往后转头去看[npc2.name]，[npc.lips+]中传出一阵[npc.a_moan+]，",
								"你转过头去看[npc2.name]，发出[npc.a_moan+]随后大喊，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"你绝望地尝试去躲开[npc2.name]，然而[npc2.she]抓住你的[npc.hips]并在你[npc.sob]时一把把你拉回，",
								"你尝试躲开[npc2.name]，然而[npc2.she]在你发出[npc.a_sob+]时紧紧地抓住了你，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: 
						return UtilText.returnStringAtRandom(
								"你转过头去看[npc2.name]，[npc.a_moan]从你的[npc.lips+]中传出，",
								"你转过头去看[npc2.name]，发出[npc.a_moan]后大喊，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"你俯视着跪在你身下的[npc2.name]，",
								"你俯视着[npc2.name]，叹了一口气，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"你看着跪在你身下的[npc2.name]，对[npc2.she]咧嘴笑笑，",
								"你俯视着[npc2.name]，咆哮道，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: 
						return UtilText.returnStringAtRandom(
								"你俯视着跪在你身下的[npc2.name]，",
								"你俯视着[npc2.name]，[npc.moan]，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"你一边抬起眼看[npc2.name]，一边对[npc2.herHim][npc.moan]，",
								"你看着上边的[npc2.name]，对[npc2.herHim][npc.moan]起来，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"你抬眼看[npc2.name]，发出[npc.a_sob+]，",
								"你抬头看着在你之上的[npc2.name]，发出[npc.a_sob+]，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: 
						return UtilText.returnStringAtRandom(
								"你抬眼看着[npc2.name]，开口对[npc2.herHim]说话，",
								"你抬头看着上面的[npc2.name]，开口对[npc2.herHim]说话，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.SIXTY_NINE)) {
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc2.name]躺在你身下，你回过头去看[npc2.she]，并对[npc2.herHim]说，",
								"[npc2.name]躺在你下面，你回过头看[npc2.she]，并对[npc2.herHim]说，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc2.name]躺在你下面，你回过头看[npc2.she]，并对[npc2.herHim]咆哮，",
								"[npc2.name]躺在你下面，你回过头看[npc2.she]，并对[npc2.herHim]咆哮，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: 
						return UtilText.returnStringAtRandom(
								"[npc2.name]躺在你下面，你回过头看[npc2.she]，并对[npc2.herHim][npc.moanVerb]，",
								"[npc2.name]躺在你下面，你回过头看[npc2.she]，对[npc2.herHim][npc.moanVerb]，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"你发出轻柔的[npc.moan]，",
								"你温柔地叹了口气，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"你粗暴地咆哮，随后大声说，",
								"你威胁地咆哮，而后说道，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"一阵渴望的[npc.moan]从你的[npc.lips+]滑出，",
								"你发出迷乱的[npc.moan]，随后对[npc2.name]说，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"你反抗着[npc2.name]，[npc.lips+]中流出抗议的呜咽声，",
								"你发出沉闷的呜咽，想从[npc2.name]身边逃离，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: // DOM_NORMAL, SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"一声[npc.A_moan]从[npc.lips+]间爆发而出，",
								"你发出一声[npc.a_moan]，对着[npc2.name]说道，")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
			
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
				}
			} else {
				if(Main.sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
				}
			}
			
			return null;
		}
	};
	
	public static String getOfferResponse(boolean denied, SexPace sexPace, String areaDescription) {
		if (denied) {
			switch(sexPace) {
				case DOM_GENTLE:
					return(
							"[npc2.Name]看到你恳求[npc2.herHim]去使用你的"+areaDescription+"，眉头紧皱，不容置疑地回应道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(那可不行。别再说了。)]",
											"[npc2.speech(别再叫我用你的"+areaDescription+"了，之后不准再说。)]"));
					
				case DOM_NORMAL:
					return(
							"[npc2.Name]看到你恳求[npc2.herHim]去使用你的"+areaDescription+"，眉头一皱，气势汹汹地向你低吟道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(别以为能让我用你的"+areaDescription+"！把这些胡话留给你自己吧！)]",
											"[npc2.speech(我愿意怎么用你就怎么用！别再说了！)]"));
					
				case DOM_ROUGH:
					return(
							"[npc2.Name]看到你恳求[npc2.herHim]去使用你的"+areaDescription+"，眉头紧皱，[npc2.she]气势汹汹地立刻反驳道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(闭嘴，你个贱货！你这种骚婊子想要"+areaDescription+"挨操，我偏就不听！)]",
											"[npc2.speech(你的身子我想用哪儿就用哪儿，你个臭婊子！别再说了！)]"));
					
				case SUB_NORMAL:
				case SUB_EAGER:
					return(
							"[npc2.Name]看到你恳求[npc2.herHim]去使用你的"+areaDescription+"，眉头一皱，失望地咕哝了一声，回应道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(我一点也不想用你的"+areaDescription+"……)]",
											"[npc2.speech(可是我不想用你的"+areaDescription+"啊……)]"));
					
				case SUB_RESISTING:
					return(
							"[npc2.Name]看到你恳求[npc2.herHim]去使用你的"+areaDescription+"，[npc.a_sob]着拼命反抗，哭喊道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(求你了，我不想啊！快放了我！)]",
											"[npc2.speech(不要！把你的"+areaDescription+"拿开！)]"));
					
			}
			
		} else {
			switch(sexPace) {
				case DOM_GENTLE:
					return(
							"[npc2.Name]看到你恳求[npc2.herHim]去使用你的"+areaDescription+"，顿时眉开眼笑，轻声[npc2.moaning]着，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(哎呀，如果你真这么想，那当然，我就用用你的"+areaDescription+"……)]",
											"[npc2.speech(想让我用你的"+areaDescription+"，哼……既然你这么坚持……)]"));
					
				case DOM_NORMAL:
					return(
							"[npc2.Name]看到你恳求[npc2.herHim]去使用你的"+areaDescription+"，顿时眉开眼笑，[npc2.moaning]着，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(姆嗯，既然你想要，那我就用用你的"+areaDescription+"！)]",
											"[npc2.speech(想让我用你的"+areaDescription+"，嗯？我倒是不介意！)]"));
					
				case DOM_ROUGH:
					return(
							"[npc2.Name]看到你恳求[npc2.herHim]去使用你的"+areaDescription+"，顿时眉开眼笑，咆哮道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(真是个贱货！忍不住想让"+areaDescription+"挨操了是吧？！行，我倒是愿意玩玩！)]",
											"[npc2.speech(你个下流的妓女，求着我用你的"+areaDescription+"！那就让你如愿以偿，骚婊子！)]"));
					
				case SUB_NORMAL:
				case SUB_EAGER:
					return(
							"[npc2.Name]看到你恳求[npc2.herHim]去使用你的"+areaDescription+"，顿时眉开眼笑，[npc2.moaning]着，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(姆嗯，好啊，那我就用用你的"+areaDescription+"吧……)]",
											"[npc2.speech(我当然可以用你的"+areaDescription+"了！)]"));
					
				case SUB_RESISTING:
					return(
							"[npc2.Name]看到你恳求[npc2.herHim]去使用你的"+areaDescription+"，[npc.a_sob]着拼命反抗，哭喊道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(求你了，我不想啊！快放了我！)]",
											"[npc2.speech(不要！把你的"+areaDescription+"拿开！)]"));
					
			}
		}
		return "";
	}
	
	public static String getRequestResponse(boolean denied, SexPace sexPace, String areaDescription) {
		if (denied) {
			switch(sexPace) {
				case DOM_GENTLE:
					return(
							"[npc2.Name]看到你求着要用[npc2.her]的"+areaDescription+"，眉头紧皱，不容置疑地回应道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(那可不行。别再说了。)]",
											"[npc2.speech(别再求着要用我的"+areaDescription+"了，之后不准再说。)]"));
					
				case DOM_NORMAL:
					return(
							"[npc2.Name]看到你求着要用[npc2.her]的"+areaDescription+"，眉头紧皱，气势汹汹地向你低吟道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(别以为能用我的"+areaDescription+"！把这些胡话留给自己吧！)]",
											"[npc2.speech(我想用哪里我说了算！别再说了！)]"));
					
				case DOM_ROUGH:
					return(
							"[npc2.Name]看到你求着要用[npc2.her]的"+areaDescription+"，眉头紧皱，[npc2.she]气势汹汹地立刻反驳道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(闭嘴，你个贱货！你这种骚婊子想用"+areaDescription+"？我偏就不听！)]",
											"[npc2.speech(我想用哪儿就用哪儿，你个臭婊子！别再说了！)]"));
					
				case SUB_NORMAL:
				case SUB_EAGER:
					return(
							"[npc2.Name]看到你求着要用[npc2.her]的"+areaDescription+"，眉头一皱，失望地咕哝了一声，回应道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(可我一点也不想让你用"+areaDescription+"……)]",
											"[npc2.speech(可是我不想让你用"+areaDescription+"啊……)]"));
					
				case SUB_RESISTING:
					return(
							"[npc2.Name]看到你求着要用[npc2.her]的"+areaDescription+"，[npc.a_sob]着拼命反抗，哭喊道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(求你了，我不想啊！快放了我！)]",
											"[npc2.speech(不要！把你的"+areaDescription+"拿开！)]"));
					
			}
			
		} else {
			switch(sexPace) {
				case DOM_GENTLE:
					return(
							"[npc2.Name]看到你求着要用[npc2.her]的"+areaDescription+"，顿时眉开眼笑，轻声[npc2.moaning]着，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(没问题啊，那就用我的"+areaDescription+"吧！)]",
											"[npc2.speech(我当然可以用上"+areaDescription+"了！那就来吧……)]"));
					
				case DOM_NORMAL:
					return(
							"[npc2.Name]看到你求着要用[npc2.her]的"+areaDescription+"，顿时眉开眼笑，[npc2.moaning]着，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(没问题啊，那就用我的"+areaDescription+"吧！)]",
											"[npc2.speech(没问题啊，那就用我的"+areaDescription+"吧！)]"));
					
				case DOM_ROUGH:
					return(
							"[npc2.Name]看到你求着要用[npc2.her]的"+areaDescription+"，顿时眉开眼笑，咆哮道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(真是个贱货！忍不住想尝尝我"+areaDescription+"的滋味了吧？！行，我倒是愿意玩玩！)]",
											"[npc2.speech(你个下流的妓女，求着想尝一番我"+areaDescription+"的味道！那就让你如愿以偿，骚婊子！)]"));
					
				case SUB_NORMAL:
				case SUB_EAGER:
					return(
							"[npc2.Name]看到你求着要用[npc2.her]的"+areaDescription+"，顿时眉开眼笑，[npc2.moaning]着，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(没问题啊，那就用我的"+areaDescription+"吧！)]",
											"[npc2.speech(没问题啊，那就用我的"+areaDescription+"吧！)]"));
					
				case SUB_RESISTING:
					return(
							"[npc2.Name]看到你求着要用[npc2.her]的"+areaDescription+"，[npc.a_sob]着拼命反抗，哀求道，"
									+UtilText.returnStringAtRandom(
											"[npc2.speech(求你了，我不想啊！快放了我！)]",
											"[npc2.speech(不要！把你的"+areaDescription+"拿开！)]"));
					
			}
		}
		return "";
	}
	
	public static final SexAction PLAYER_OFFER_VAGINAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "献上小穴";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "撅起屁股，努力引诱[npc2.name]使用你的小穴。";
			}
			return "告诉[npc2.name]，你想让[npc2.herHim]使用你的小穴。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& Main.game.getPlayer().hasVagina()
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"因为[npc.namePos]的嘴被锁住了，[npc.she]只得努力朝[npc2.herHim]扭起[npc.hips]，让[npc2.herHim]来使用自己[npc.pussy+]。",
								"因为嘴被堵住了，所以[npc.name]说不出想法，只能扭起[npc.hips]，来吸引[npc2.name]使用自己[npc.pussy+]。")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("你摆出最为可怜的表情，哀求[npc2.name]，"
								+UtilText.returnStringAtRandom(
										"[npc.speech(求求你，求求你玩弄我的小穴！)]",
										"[npc.speech(玩弄我的小穴……求你了！)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"小穴"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA));
			}
		}
	};
	
	public static final SexAction PLAYER_REQUEST_VAGINAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "寻求小穴";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "更贴近[npc2.namePos]的小穴，来示意[npc2.herHim]你想要什么。";
			}
			return "告诉[npc2.name]你想使用[npc2.her]的小穴。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null))
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"因为[npc.namePos]的嘴被锁住了，[npc.she]只得努力靠近[npc2.namePos][npc2.pussy+]，示意[npc2.herHim]自己对什么感兴趣。",
								"因为嘴被堵住了，所以[npc.name]说不出想法，只能靠近[npc2.namePos][npc2.pussy+]，来示意自己想要什么。")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("你摆出最为可怜的表情，哀求[npc2.name]，"
								+UtilText.returnStringAtRandom(
										"[npc.speech(求你了，我想操你的穴！)]",
										"[npc.speech(我想用用你的小穴，让我用嘛！)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getRequestResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"小穴"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null));
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_ANAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "献上屁股";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				 return "撅起屁股，努力引诱[npc2.name]使用你的屁股。";
			}
			return "告诉[npc2.name]，你想让[npc2.herHim]使用你的屁股。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"因为[npc.namePos]的嘴被锁住了，[npc.she]只得努力朝[npc2.herHim]扭起[npc.hips]，让[npc2.herHim]来使用自己[npc.ass+]。",
								"因为嘴被堵住了，所以[npc.name]说不出想法，只能向后扭起[npc.hips]，来吸引[npc2.name]使用自己[npc.ass+]。")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("你摆出最为可怜的表情，哀求[npc2.name]，"
								+UtilText.returnStringAtRandom(
										"[npc.speech(来嘛！来使用我的屁股！)]",
										"[npc.speech(用用我的屁股，求求你了！)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"屁股"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
					return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING, Fetish.FETISH_PURE_VIRGIN);
					
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
				}
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_REQUEST_ANAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "寻求肛门";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "更贴近[npc2.namePos]的屁股，来示意[npc2.herHim]你想要什么。";
			}
			return "告诉[npc2.name]你想使用[npc2.her]的屁眼。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null))
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"因为[npc.namePos]的嘴被锁住了，[npc.she]只得努力靠近[npc2.namePos][npc2.ass+]，示意[npc2.herHim]自己对什么感兴趣。",
								"因为嘴被堵住了，所以[npc.name]说不出想法，只能靠近[npc2.namePos][npc2.ass+]，来示意自己想要什么。")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("你摆出最为可怜的表情，哀求[npc2.name]，"
								+UtilText.returnStringAtRandom(
										"[npc.speech(求你了，我想用用你的屁股！)]",
										"[npc.speech(我想用你的屁眼，求你了！)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getRequestResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"屁股"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null));
			}
		}

		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_ORAL = new SexAction(
			SexActionType.SPEECH,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "提供口交";
		}

		@Override
		public String getActionDescription() {
			return "告诉[npc2.name]，你想让[npc2.herHim]使用你的嘴。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("你摆出最为可怜的表情，哀求[npc2.name]，"
							+UtilText.returnStringAtRandom(
									"[npc.speech(来嘛，用我的嘴！)]",
									"[npc.speech(用我的嘴，求你了！)]")
							+"<br/><br/>");
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"嘴"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(
						new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH),
						new SexType(SexParticipantType.NORMAL, null, SexAreaPenetration.TONGUE)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_REQUEST_ORAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "要求口交";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "更贴近[npc2.namePos]的嘴，来示意[npc2.herHim]你想要什么。";
			}
			return "告诉[npc2.name]你想使用[npc2.her]的嘴。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null))
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"因为[npc.namePos]的嘴被锁住了，[npc.she]只得努力靠近[npc2.namePos]的[npc2.face]，示意[npc2.herHim]自己对什么感兴趣。",
								"因为嘴被堵住了，所以[npc.name]说不出想法，只能靠近[npc2.namePos]的[npc2.face]，来示意自己想要什么。")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("你摆出最为可怜的表情，哀求[npc2.name]，"
								+UtilText.returnStringAtRandom(
										"[npc.speech(求你了，我想用你的嘴！)]",
										"[npc.speech(用用你的嘴，求求你了！)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getRequestResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"嘴"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(
						new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null),
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, null)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_NIPPLE = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "献上乳头性交";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "挺起胸部，努力引诱[npc2.name]来操你[npc.nipples+]。";
			}
			return "告诉[npc2.name]，你想让[npc2.herHim]使用你[npc.nipples+]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& Main.game.getPlayer().isBreastFuckableNipplePenetration()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"因为[npc.namePos]的嘴被锁住了，[npc.she]只得努力朝[npc2.name]摇起胸部，让[npc2.herHim]来使用那[npc.nipples+]。",
								"因为嘴被堵住了，所以[npc.name]说不出想法，只能摇起[npc.chest]，来吸引[npc2.name]使用那[npc.nipples+]。")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("你摆出最为可怜的表情，哀求[npc2.name]，"
								+UtilText.returnStringAtRandom(
										"[npc.speech(来嘛。来操我的乳头！)]",
										"[npc.speech(操我的乳头嘛，求你了！)]")
								+"<br/><br/>");
			}
									
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"[npc.nipples]"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_PAIZURI = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "献上乳交";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "挺起胸部，努力引诱[npc2.name]来操你[npc.breasts+]。";
			}
			return "告诉[npc2.name]，你想让[npc2.herHim]使用你[npc.breasts+]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& Main.game.getPlayer().isBreastFuckablePaizuri()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.BREASTS, true)
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"因为[npc.namePos]的嘴被锁住了，[npc.she]只得努力朝[npc2.name]摇起胸部，让[npc2.herHim]来使用那[npc.breasts+]。",
								"因为嘴被堵住了，所以[npc.name]说不出想法，只能摇起[npc.chest]，来吸引[npc2.name]使用那[npc.breasts+]。")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("你摆出最为可怜的表情，哀求[npc2.name]，"
								+UtilText.returnStringAtRandom(
										"[npc.speech(求你了，操我的奶子！)]",
										"[npc.speech(操我的胸部好不好！)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"胸部"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_NAIZURI = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "献上贫乳乳交";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "挺起胸部，努力引诱[npc2.name]把[npc2.cock]插进来。";
			}
			return "告诉[npc2.name]，你想让[npc2.her]把[npc2.cock]插入胸部。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& !Main.game.getPlayer().isBreastFuckablePaizuri()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.BREASTS, true)
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"因为[npc.namePos]的嘴被锁住了，[npc.she]只得努力朝[npc2.name]摇起胸部，让[npc2.herHim]来使用它。",
								"因为嘴被堵住了，所以[npc.name]说不出想法，只能摇起[npc.chest]，来吸引[npc2.name]使用它。")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("你摆出最为可怜的表情，哀求[npc2.name]，"
								+UtilText.returnStringAtRandom(
										"[npc.speech(来嘛，来用肉棒蹭我的胸部！)]",
										"[npc.speech(蹭蹭我的胸，求你了！)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"胸部"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
}
