package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
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
 * @since 0.1.84
 * @version 0.2.9
 * @author Innoxia
 */
public class PenisBreasts {
	
	public static final SexAction FORCE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "推入口中";
		}

		@Override
		public String getActionDescription() {
			return "前后推动你的[npc.hips]并将你[npc.cock+]的[npc.cockHead]强制塞入[npc2.namePos]的口中。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Special check for NPCs, as this action can hit penis, breast, and oral fetishes.
			// Positive penis + breast desires can outweigh a negative oral one and thus make NPCs use this action, even though it makes no sense if they hate the oral fetish
			if(!Main.sex.getCharacterPerformingAction().isPlayer()) {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isNegative()) {
					return false;
				}
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterPerformingAction().getPenisRawSizeValue()>=6
					&& Main.sex.isOrificeFree(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)
//					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"温柔地在[npc2.namePos]的[npc2.breasts]之间向前推进，"
									+ "[npc.name]将[npc.her][npc.cock+]一直推到[npc2.her]的嘴部，然后用[npc.cockHead]强硬地撬开[npc2.her][npc2.lips]。",

							"[npc.name]缓慢地前推[npc.hips]，将[npc.cock+]强行挤入[npc2.namePos][npc2.breasts+]之间，"
									+ "[npc.cockHead+]持续推进直至挤开[npc2.her][npc2.lips+]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"在[npc2.namePos]的[npc2.breasts]间饥渴地推进，"
									+ "[npc.name]将[npc.her][npc.cock+]一直推到[npc2.her]的嘴部，然后用[npc.cockHead]强硬地撬开[npc2.her][npc2.lips]。",

							"[npc.name]贪婪地推进[npc.her]的[npc.hips]，[npc.her][npc.cock+]强行挤入[npc2.namePos][npc2.breasts+]之间，"
									+ "[npc.cockHead+]持续推进直至挤开[npc2.her][npc2.lips+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"粗暴地在[npc2.namePos]的[npc2.breasts]之间向前推进，"
									+ "[npc.her][npc.cock+]猛撞在[npc2.her]的嘴上，用[npc.cockHead]强硬地撬开[npc2.her][npc2.lips]。",

							"[npc.name]猛烈地向前推撞着[npc.her]的[npc.hips]，将[npc.her][npc.cock+]挤入[npc2.namePos][npc2.breasts+]之间，"
									+ "[npc.cockHead+]呼啸着朝[npc2.her][npc2.lips+]推进过去。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"在[npc2.namePos]的[npc2.breasts]之间向前推进，"
									+ "[npc.name]将[npc.her][npc.cock+]一直推到[npc2.her]的嘴部，然后用[npc.cockHead]强硬地撬开[npc2.her][npc2.lips]。",

							"[npc.name]前推[npc.hips]，将[npc.cock+]强行挤入[npc2.namePos][npc2.breasts+]之间，"
									+ "[npc.cockHead+]持续推进直至挤开[npc2.her][npc2.lips+]。"));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]对[npc.her]的热忱咧嘴一笑，然后张开嘴，充满爱意地吮吸了一下[npc.her][npc.cock]的[npc.cockHead]，接着退了回来，"
										+ "不过在那之前还亲吻了一下最前端。",
	
								"[npc2.Name]张开嘴来接受[npc.her][npc.cock+]，给予了[npc.cockHead]一个又热又湿的吮吸，然后抬起头来，在最前端留下了一个轻柔的吻。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]从口中漏出了一声愉悦的[npc2.moan]，然后急切地张开嘴，充满爱意地吸吮了一下[npc.her][npc.cock]的[npc.cockHead]，"
										+ "[npc2.name]接着在最前端留下了一个湿吻才退了回去。",
	
								"[npc2.Name]饥渴地张开嘴来接受[npc.her][npc.cock+]，给予了[npc.cockHead]一个又热又湿的吮吸，然后抬起头来，在最前端留下了一个热情的吻。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]从口中漏出了[npc2.a_moan+]后，立刻张开嘴，霸道地吸吮了一下[npc.her][npc.cock]的[npc.cockHead]，"
										+ "[npc2.name]接着在最前端留下了一个粗暴的吻才退了回去。",
	
								"[npc2.Name]立即张开嘴来接受[npc.her][npc.cock+]，霸道地吮吸了一下[npc.cockHead]，然后抬起头来，在最前端留下了一个粗暴的吻。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]从口中漏出了一声轻轻的[npc2.moan]，然后张开嘴，顺从地吸吮了一下[npc.her][npc.cock]的[npc.cockHead]，"
										+ "[npc2.name]接着在最前端轻点了一个吻后退了回去。",
	
								"[npc2.Name]张开嘴来接受[npc.her][npc.cock+]，顺从地吮吸了一下[npc.cockHead]，然后抬起头来，在最前端快速地吻了一下。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]从口中漏出了[npc2.a_moan+]后，试着将[npc2.her]的嘴从[npc.her][npc.cock]的[npc.cockHead]上移开，"
										+ "[npc2.name]拼命地央求[npc.herHim]放过[npc2.name]。",
	
								"[npc2.Name]把[npc2.her]的头向后扭，试着把[npc.her][npc.cock+]从嘴上推开，泪水开始涌上[npc2.her]的[npc2.eyes]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH);
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
			}
			if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
			}
			return null;
		}
		
	};
	
	public static final SexAction TAKE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "乳交入嘴";
			} else {
				return "贫乳乳交入嘴";
			}
		}

		@Override
		public String getActionDescription() {
			return "把你的头向前推并将[npc2.namePos][npc2.cock+]的[npc2.cockHead]含入口中。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Special check for NPCs, as this action can hit penis, breast, and oral fetishes.
			// Positive penis + breast desires can outweigh a negative oral one and thus make NPCs use this action, even though it makes no sense if they hate the oral fetish
			if(!Main.sex.getCharacterPerformingAction().isPlayer()) {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_ORAL_GIVING).isNegative()) {
					return false;
				}
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).getPenisRawSizeValue()>=6
					&& Main.sex.isOrificeFree(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)
					&& Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)
//					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.SIXTY_NINE);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.namePos]的[npc2.cock]向上滑入[npc.namePos]的[npc.breasts]之间，[npc.name]温柔地将[npc.face]朝着它压下去，"
									+ "张开[npc.lips+]，将[npc2.cockHead]含入口中。",

							"[npc.name]缓缓地下压[npc.face]，"
									+ "将[npc2.namePos][npc2.cock+]的[npc2.cockHead]含入口中，同时，[npc2.name]在[npc.her][npc.breasts+]之间向上猛推。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.namePos]的[npc2.cock]向上滑入[npc.namePos]的[npc.breasts]之间，[npc.name]饥渴地将[npc.face]朝着它压下去，"
									+ "贪婪地张开[npc.lips+]，将[npc2.cockHead]含入口中。",

							"[npc.name]饥渴地下压[npc.face]，"
									+ "贪婪地将[npc2.namePos][npc2.cock+]的[npc2.cockHead]含入口中，同时，[npc2.name]在[npc.her][npc.breasts+]之间向上猛推。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.namePos]的[npc2.cock]向上滑入[npc.namePos]的[npc.breasts]之间，[npc.name]将[npc.face]朝着它压下去，"
									+ "贪婪地张开[npc.lips+]，将[npc2.cockHead]含入口中。",

							"[npc.name]将[npc.face]向下压，"
									+ "贪婪地将[npc2.namePos][npc2.cock+]的[npc2.cockHead]含入口中，同时，[npc2.name]在[npc.her][npc.breasts+]之间向上猛推。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.namePos]的[npc2.cock]向上滑入[npc.namePos]的[npc.breasts]之间，[npc.name]将[npc.face]朝着它压下去，"
									+ "张开[npc.lips+]，将[npc2.cockHead]含入口中。",

							"[npc.name]将[npc.face]向下压，"
									+ "将[npc2.namePos][npc2.cock+]的[npc2.cockHead]含入口中，同时，[npc2.name]在[npc.her][npc.breasts+]之间向上猛推。"));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]对[npc.her]的热忱咧嘴一笑，然后温柔地把[npc2.her]的[npc2.cock]推入[npc.her]口中，"
										+ "[npc.name]任凭[npc.herHim]对[npc2.cockHead]又吸又吻了一会儿，然后抽回去继续操[npc.her][npc.breasts+]。",
	
								"[npc2.Name]缓缓地将[npc2.cock+]推入[npc.her]口中，"
										+ "容许[npc.herHim]给予[npc2.cockHead]一个又热又湿的吮吸，然后抽回去继续操[npc.her][npc.breasts+]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]对[npc.her]的热忱咧嘴一笑，然后饥渴地把[npc2.her]的[npc2.cock]推入[npc.her]口中，"
										+ "[npc.name]任凭[npc.herHim]对[npc2.cockHead]又吸又吻了一会儿，然后抽回去继续操[npc.her][npc.breasts+]。",
	
								"[npc2.Name]饥渴地将[npc2.cock+]推入[npc.her]口中，"
										+ "容许[npc.herHim]给予[npc2.cockHead]一个又热又湿的吮吸，然后抽回去继续操[npc.her][npc.breasts+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]对自己的热忱咧嘴一笑，然后粗暴地把[npc2.her]的[npc2.cock]推入[npc.namePos]口中，"
										+ "[npc.name]容许[npc.herHim]对[npc2.cockHead]又吸又吻了一会儿，然后抽回去继续侵略[npc.her][npc.breasts+]。",
	
								"[npc2.Name]粗暴地将[npc2.cock+]推入[npc.her]口中，"
										+ "容许[npc.herHim]给予[npc2.cockHead]一个又热又湿的吮吸，然后抽回去继续侵略[npc.her][npc.breasts+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出一声轻轻的[npc2.moan]，然后把[npc2.her]的[npc2.cock]推入[npc.namePos]口中，当[npc.Name]对[npc2.cockHead]又吸又吻时，[npc2.she]喘息着，"
										+ "然后[npc2.herHim]才被容许抽回并继续操[npc.her][npc.breasts+]。",
	
								"[npc2.Name]将[npc2.cock+]推入[npc.her]口中，"
										+ "当[npc.she]给予[npc2.cockHead]一个又热又湿的吮吸时喘息起来，然后[npc2.herHim]才被允许抽回并继续操[npc.her][npc.breasts+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]从口中漏出了[npc.a_moan+]后，试着将[npc2.her]的[npc2.cock]从[npc.namePos]的嘴上移开，"
										+ "[npc2.she]拼命地乞求[npc.herHim]放过[npc2.herHim]。",
	
								"[npc2.Name]试着拉回[npc2.her]的[npc2.hips]，但[npc.name]把[npc2.herHim]控制在原地，"
										+ "在吮吸了[npc2.her][npc2.cock+]的[npc2.cockHead]一会儿后，才终于允许[npc2.herHim]退开。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH);
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
			}
			if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
			}
			return null;
		}
		
	};
	
	
	public static final SexAction FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "开始乳交";
			} else {
				return "开始贫乳乳交";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "将你[npc.cock+]滑入[npc2.namePos][npc2.breasts+]间并开始操它们。";
			} else {
				return "开始在[npc2.namePos]的胸部上摩擦你[npc.cock+]。";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]往下伸手，抓住了[npc2.namePos][npc2.breasts+]，温柔地将它们挤压在一起，"
										+ "[npc.her]的[npc.cock]对准了[npc2.her]的乳沟，然后向前滑动并开始操[npc2.her]的[npc2.breasts]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]往下伸手，[npc.her][npc.fingers]贪婪地陷入[npc2.namePos][npc2.breasts+]之中，饥渴地将它们挤压在一起，"
										+ "[npc.her]的[npc.cock]对准了[npc2.her]的乳沟，然后向前滑动并开始狂热地操[npc2.her]的[npc2.breasts]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]往下伸手，[npc.her][npc.fingers]粗暴地陷入[npc2.namePos][npc2.breasts+]之中，强有力地将它们挤压在一起，"
										+ "[npc.her]的[npc.cock]对准了[npc2.her]的乳沟，然后猛烈地向前冲击并开始急速地操[npc2.her]的[npc2.breasts]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]往下伸手，抓住了[npc2.namePos][npc2.breasts+]，将它们挤压在一起，"
										+ "[npc.her]的[npc.cock]对准了[npc2.her]的乳沟，然后向前滑动并开始操[npc2.her]的[npc2.breasts]。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]往下伸手，[npc.her][npc.fingers]贪婪地陷入[npc2.namePos][npc2.breasts+]之中，饥渴地将它们挤压在一起，"
										+ "[npc.her]的[npc.cock]对准了[npc2.her]的乳沟，然后向前滑动并开始狂热地操[npc2.her]的[npc2.breasts]。"));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为回应，[npc2.Name]的口中漏出了一声愉悦而细小的[npc2.moan]，"
											+ "当[npc2.she]鼓励[npc.herHim]继续的时候，伸手帮忙把[npc2.her]的[npc2.breasts]挤在一起。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为回应，[npc2.Name]的口中漏出了[npc2.a_moan+]，"
											+ "当[npc2.she]愉悦地鼓励[npc.herHim]继续的时候，快速地伸手帮忙把[npc2.her]的[npc2.breasts]挤在一起。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为回应，[npc2.Name]的口中漏出了[npc2.a_moan+]，"
											+ "当[npc2.she]霸道地鼓励[npc.herHim]继续的时候，伸手帮忙把[npc2.her]的[npc2.breasts]死死地挤在一起。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为回应，[npc2.Name]的口中漏出了[npc2.a_moan+]，"
											+ "当[npc2.she]愉悦地鼓励[npc.herHim]继续的时候，快速地伸手帮忙把[npc2.her]的[npc2.breasts]挤在一起。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为回应，[npc2.Name]的口中漏出了一声细小的[npc2.moan]，"
											+ "然后[npc2.she]在鼓励[npc.herHim]继续的同时，伸手帮忙把[npc2.her]的[npc2.breasts]挤压在一起。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为回应，[npc2.Name]的口中漏出了[npc2.a_moan+]，"
											+ "当[npc2.she]乞求[npc.herHim]停下的时候，无力地伸出手去，试图将[npc.herHim]推离[npc2.her]的[npc2.breasts]。"));
							break;
						default:
							break;
					}
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]往下伸手，抓住了[npc2.namePos][npc2.breasts+]，温柔地试着将它们挤压在一起，"
										+ "[npc.her]的[npc.cock]对准了[npc2.her]浅浅的乳沟，然后向前滑动并开始在[npc2.her]的胸部摩擦。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]往下伸手，对着[npc2.namePos][npc2.breasts+]又挤又摸，尽全力将它们挤压在一起，"
										+ "[npc.her]的[npc.cock]对准了[npc2.her]浅浅的乳沟，然后向前滑动并开始在[npc2.her]的胸部饥渴地摩擦。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]往下伸手，粗鲁地对着[npc2.namePos][npc2.breasts+]又挤又摸，尽全力将它们挤压在一起，"
										+ "[npc.her]的[npc.cock]对准了[npc2.her]浅浅的乳沟，然后向前滑动并开始在[npc2.her]的胸部激烈地摩擦。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]往下伸手，对着[npc2.namePos][npc2.breasts+]又挤又摸，尽全力将它们挤压在一起，"
										+ "[npc.her]的[npc.cock]对准了[npc2.her]浅浅的乳沟，然后向前滑动并开始在[npc2.her]的胸部摩擦。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]往下伸手，对着[npc2.namePos][npc2.breasts+]又挤又摸，尽全力将它们挤压在一起，"
										+ "[npc.her]的[npc.cock]对准了[npc2.her]浅浅的乳沟，然后向前滑动并开始在[npc2.her]的胸部饥渴地摩擦。"));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出了一声愉悦而细小的[npc2.moan]，伸出手去试图帮忙把[npc2.her][npc2.breastSize]的[npc2.breasts]挤压在一起，"
											+ "[npc2.she]同时鼓励着[npc.name]去操[npc2.her]的乳房。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出[npc2.a_moan+]，立刻伸出手来帮忙把[npc2.her][npc2.breastSize]的[npc2.breasts]挤压在一起，"
											+ "[npc2.she]同时愉悦地鼓励着[npc.herHim]去操[npc2.her]的乳房。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出[npc2.a_moan+]，伸出手来试图帮忙强制把[npc2.her][npc2.breastSize]的[npc2.breasts]挤压在一起，"
											+ "[npc2.she]同时霸道地命令[npc.herHim]去操[npc2.her]的乳房。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出[npc2.a_moan+]，立刻伸出手来帮忙把[npc2.her][npc2.breastSize]的[npc2.breasts]挤压在一起，"
											+ "[npc2.she]同时愉悦地鼓励着[npc.herHim]去操[npc2.her]的乳房。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出了一声细小的[npc2.moan]，然后伸出手来试图帮忙[npc2.her][npc2.breastSize]的[npc2.breasts]挤压在一起，"
											+ "[npc2.she]同时鼓励着[npc.herHim]去操[npc2.her]的乳房。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为回应，[npc2.Name]的口中漏出了[npc2.a_moan+]，"
											+ "当[npc2.she]乞求[npc.herHim]停下的时候，无力地伸出手去，试图将[npc.herHim]推离[npc2.her][npc2.breastSize]的[npc2.breasts]。"));
							break;
						default:
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]将手压在[npc2.namePos]的躯干上，改变身位，把[npc.her]的[npc.cock]置于[npc2.her]胸前，"
										+ "然后向前滑动并开始在[npc2.her]的身体上摩擦。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]将手压在[npc2.namePos]的躯干上，改变身位，把[npc.her]的[npc.cock]置于[npc2.her]胸前，"
										+ "然后向前滑动并开始在[npc2.her]的身体上饥渴地摩擦。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]俯身将手压粗暴地在[npc2.namePos]的躯干上，改变身位，把[npc.her]的[npc.cock]置于[npc2.her]胸前，"
										+ "然后向前滑动并开始在[npc2.her]的身体上剧烈地摩擦。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]将手压在[npc2.namePos]的躯干上，改变身位，把[npc.her]的[npc.cock]置于[npc2.her]胸前，"
										+ "然后向前滑动并开始在[npc2.her]的身体上摩擦。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]俯身将手压在[npc2.namePos]的躯干上，改变身位，把[npc.her]的[npc.cock]置于[npc2.her]胸前，"
										+ "然后向前滑动并开始在[npc2.her]的身体上饥渴地摩擦。"));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出了一声愉悦而细小的[npc2.moan]，挺起[npc2.her]胸部来鼓励[npc.herHim]继续。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出了[npc2.a_moan+]，急切地挺起[npc2.her]胸部来愉悦地鼓励[npc.herHim]继续。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出了[npc2.a_moan+]，猛地挺起[npc2.her]胸部来霸道地命令[npc.herHim]继续。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出了[npc2.a_moan+]，挺起[npc2.her]胸部来愉悦地鼓励[npc.herHim]继续。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出了一声轻微的[npc2.moan]，挺起[npc2.her]胸部来鼓励[npc.herHim]继续。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为回应，[npc2.Name]的口中漏出了[npc2.a_moan+]，"
											+ "无力地伸出手去尝试将[npc.name]推离[npc2.herHim]，并乞求放过自己。"));
							break;
						default:
							break;
					}
				}
			}
				
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(Main.sex.getCharacterTargetedForSexAction(action).isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]热切地将[npc2.her][npc2.breasts+]挤在一起，"
										+ "[npc2.she]鼓励[npc.name]继续操[npc2.her]的乳沟，发出一阵[npc2.a_moan+]。",
			
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]急切地把[npc2.her][npc2.breastSize]的[npc2.breasts]挤在一起，"
										+ "[npc2.she]鼓励着[npc.name]继续在[npc2.her]的乳沟间来回磨蹭[npc.her][npc.cock+]。",
			
								"[npc2.name]愉悦地[npc2.moaning]着，热切地用[npc2.breasts+]包裹住[npc.namePos][npc.cock+]，"
										+ "然后乞求[npc.herHim]继续操[npc2.her]那如枕头般柔软的山丘。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]拼命想要逃离[npc.namePos]的[npc.cock]，但没能成功，"
										+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地乞求[npc.Name]放过自己的[npc2.breasts]，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
			
								"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
										+ "[npc2.she]恳求[npc.name]放过自己的[npc2.breasts]，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
			
								"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
										+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]远离[npc2.her]的[npc2.breasts]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]将[npc2.her][npc2.breasts+]挤在一起，"
										+ "[npc2.she]鼓励[npc.name]继续操[npc2.her]的乳沟，发出一阵[npc2.a_moan+]。",
			
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]把[npc2.her][npc2.breastSize]的[npc2.breasts]挤压在一起，"
										+ "[npc2.she]鼓励着[npc.name]继续在[npc2.her]的乳沟间来回磨蹭[npc.her][npc.cock+]。",
			
								"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.breasts+]包裹住[npc.namePos][npc.cock+]，"
										+ "然后乞求[npc.herHim]继续操[npc2.her]那如枕头般柔软的山丘。"));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]温柔地将[npc2.her][npc2.breasts+]挤在一起，"
										+ "[npc2.she]发出一声柔和的[npc2.moan]，鼓励[npc.name]继续操[npc2.her]的乳沟。",
			
								" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
										+ "[npc2.she]温柔地把[npc2.her][npc2.breastSize]的[npc2.breasts]挤压在一起，"
										+ "[npc2.she]鼓励着[npc.name]继续在[npc2.her]的乳沟间来回磨蹭[npc.her][npc.cock+]。",
			
								"[npc2.name]愉悦地[npc2.moaning]着，轻柔地用[npc2.breasts+]包裹住[npc.namePos][npc.cock+]，"
										+ "然后乞求[npc.herHim]继续操[npc2.her]那如枕头般柔软的山丘。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]粗暴地将[npc2.her][npc2.breasts+]挤压在一起，"
										+ "[npc2.she]命令[npc.name]继续操[npc2.her]的乳沟，发出一阵[npc2.a_moan+]。",
			
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]激烈地把[npc2.her][npc2.breastSize]的[npc2.breasts]挤压在一起，"
										+ "[npc2.she]命令[npc.name]继续在[npc2.her]的乳沟间上下抽插[npc.her][npc.cock+]。",
			
								"[npc2.name]愉悦地呻吟着，霸道地用[npc2.her][npc2.breasts+]包裹住[npc.namePos][npc.cock+]，"
										+ "然后勒令[npc.herHim]继续操[npc2.her]那如枕头般柔软的山丘。"));
						break;
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(action).hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]热切地试着将[npc2.her]小小的[npc2.breasts]挤在一起，"
										+ "[npc2.she]鼓励[npc.name]继续操[npc2.her]浅浅的乳沟，发出一阵[npc2.a_moan+]。",
			
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]急切地试着把[npc2.her][npc2.breastSize]的[npc2.breasts]挤压在一起，"
										+ "[npc2.she]鼓励着[npc.name]继续在[npc2.her]的狭小乳沟间来回磨蹭[npc.her][npc.cock+]。",
			
								"[npc2.name]愉悦地[npc2.moaning]着，热切地把[npc2.breasts+]压向[npc.namePos][npc.cock+]两侧，"
										+ "然后乞求[npc.herHim]继续操[npc2.her]小小的乳沟。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]拼命想要逃离[npc.namePos]的[npc.cock]，但没能成功，"
										+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地乞求[npc.Name]放过自己的[npc2.breasts]，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
			
								"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
										+ "[npc2.she]恳求[npc.name]放过自己的[npc2.breasts]，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
			
								"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
										+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]远离[npc2.her]的[npc2.breasts]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]试着将[npc2.her][npc2.breasts+]挤在一起，"
										+ "[npc2.she]鼓励[npc.name]继续操[npc2.her]浅浅的乳沟，发出一阵[npc2.a_moan+]。",
			
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]把[npc2.her][npc2.breastSize]的[npc2.breasts]挤压在一起，"
										+ "[npc2.she]鼓励着[npc.name]继续在[npc2.her]的狭小乳沟间来回磨蹭[npc.her][npc.cock+]。",
			
								"[npc2.name]愉悦地[npc2.moaning]着，开始将[npc2.breasts+]压向[npc.namePos][npc.cock+]两侧，"
										+ "然后乞求[npc.herHim]继续操[npc2.her]小小的乳沟。"));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]温柔地试着将[npc2.her][npc2.breasts+]挤在一起，"
										+ "[npc2.she]发出一声轻柔的[npc2.moan]，鼓励[npc.name]继续操[npc2.her]浅浅的乳沟。",
			
								" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
										+ "[npc2.she]温柔地把[npc2.her][npc2.breastSize]的[npc2.breasts]挤压在一起，"
										+ "[npc2.she]鼓励着[npc.name]继续在[npc2.her]的狭小乳沟间来回磨蹭[npc.her][npc.cock+]。",
			
								"[npc2.name]愉悦地[npc2.moaning]着，温柔地把[npc2.breasts+]压向[npc.namePos][npc.cock+]两侧，"
										+ "然后乞求[npc.herHim]继续操[npc2.her]小小的乳沟。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]粗暴地试着将[npc2.her][npc2.breasts+]挤压在一起，"
										+ "[npc2.she]命令[npc.name]继续操[npc2.her]浅浅的乳沟，发出一阵[npc2.a_moan+]。",
			
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]激烈地把[npc2.her][npc2.breastSize]的[npc2.breasts]挤压在一起，"
										+ "[npc2.she]命令[npc.name]继续在[npc2.her]的贫瘠的乳沟间上下抽插[npc.her][npc.cock+]。",
			
								"[npc2.name]愉悦地呻吟着，霸道地将[npc2.her][npc2.breasts+]压向[npc.namePos][npc.cock+]两侧，"
										+ "然后命令[npc.herHim]继续操[npc2.her]小小的乳沟。"));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]热切地挺起[npc2.her]的平胸，"
										+ "[npc2.she]鼓励[npc.name]继续操弄[npc2.her]的躯干，发出一阵[npc2.a_moan+]。",
			
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]热切地挺起平坦的胸部，"
										+ "[npc2.she]鼓励着[npc.name]继续在[npc2.her]的躯干上来回磨蹭[npc.her][npc.cock+]。",
			
								"[npc2.name]愉悦地[npc2.moaning]着，急切地尝试用贫瘠的胸部尽最大可能包裹住[npc.namePos][npc.cock+]，"
										+ "然后放弃了，乞求[npc.herHim]继续操弄[npc2.her]的躯干。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]拼命想要逃离[npc.namePos]的[npc.cock]，但没能成功，"
										+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地乞求[npc.Name]放过自己的[npc2.breasts]，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
			
								"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
										+ "[npc2.she]恳求[npc.name]放过自己的[npc2.breasts]，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
			
								"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
										+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]远离[npc2.her]的[npc2.breasts]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]挺起[npc2.her]的平胸，"
										+ "[npc2.she]鼓励[npc.name]继续操弄[npc2.her]的躯干，发出一阵[npc2.a_moan+]。",
			
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]挺起平坦的胸部，"
										+ "[npc2.she]鼓励着[npc.name]继续在[npc2.her]的躯干上来回磨蹭[npc.her][npc.cock+]。",
	
								"[npc2.name]愉悦地呻吟着，倾尽全力，试着用[npc2.her]平胸[npc2.breasts+]包裹住[npc.namePos][npc.cock+]，"
										+ "然后放弃了，乞求[npc.herHim]继续操弄[npc2.her]的躯干。"));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]温柔地挺起[npc2.her]的平胸，"
										+ "[npc2.she]发出一声轻柔的[npc2.moan]，鼓励[npc.name]继续操弄[npc2.her]的躯干。",
			
								" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
										+ "[npc2.she]温柔地挺起平坦的胸部，"
										+ "[npc2.she]鼓励着[npc.name]继续在[npc2.her]的躯干上来回磨蹭[npc.her][npc.cock+]。",
	
								"[npc2.name]愉悦地呻吟着，倾尽全力，温柔地试着用[npc2.her]平胸[npc2.breasts+]包裹住[npc.namePos][npc.cock+]，"
										+ "然后放弃了，乞求[npc.herHim]只要继续操弄[npc2.her]的躯干就好。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]粗暴地推起[npc2.her]的平胸，"
										+ "[npc2.she]命令[npc.name]继续操弄[npc2.her]的躯干，发出一阵[npc2.a_moan+]。",
			
								"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
										+ "[npc2.she]用力地挺起平坦的胸部，"
										+ "[npc2.she]命令着[npc.name]继续在[npc2.her]的躯干上上下抽插[npc.her][npc.cock+]。",
	
								"[npc2.name]愉悦地呻吟着，倾尽全力，粗暴地试着用[npc2.her]平胸[npc2.breasts+]包裹住[npc.namePos][npc.cock+]，"
										+ "然后放弃了，命令[npc.herHim]只能继续操弄[npc2.her]的躯干才行。"));
						break;
				}
			}
		}
		return "";
	}
	

	public static final SexAction FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "乳交(温柔)";
			} else {
				return "贫乳乳交(温柔)";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "温柔地操[npc2.namePos][npc2.breasts+]。";
			} else {
				return "温柔地在[npc2.namePos]的平胸上摩擦你[npc.cock+]。";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"在[npc2.namePos][npc2.breasts+]之间温柔地滑动[npc.her][npc.cock+]，"
								+ "[npc.name]开始缓缓地前后挺动[npc.her]的[npc.hips]，缓缓地操[npc2.her]的乳沟，每次推入都会发出一阵轻微的[npc.moan]。",

						"[npc.her][npc.cock+]温柔地推入[npc2.namePos][npc2.breasts+]之间的乳沟，"
								+ "[npc.name]开始轻柔地将[npc.her]的[npc.hips]向前推，当[npc.she]温柔地操着[npc2.her]的[npc2.breasts]时，漏出[npc.moans+]。",

						"[npc.name]轻柔地将[npc2.namePos][npc2.breasts+]挤在一起，并在温柔地前后抽动[npc.her]的[npc.hips]时发出一声小小的[npc.moan]，"
								+ "[npc.she]一边缓缓地操着[npc2.her]的乳沟，一边吸入着[npc2.her]的[npc2.scent]。"));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"在[npc2.namePos]的小[npc2.breasts+]之间温柔地滑动[npc.her][npc.cock+]，"
								+ "[npc.name]开始缓缓地前后挺动[npc.her]的[npc.hips]，缓缓地操[npc2.her]小巧的乳沟，每次推入都会发出一阵轻微的[npc.moan]。",

						"[npc.cock+]温柔地推入[npc2.namePos][npc2.breasts+]之间那微小的乳沟，"
								+ "[npc.name]开始轻柔地将[npc.her]的[npc.hips]向前推，当[npc.she]温柔地在用[npc2.her]的胸部上下摩擦时，漏出[npc.moans+]。",

						"[npc.name]轻柔地试着将[npc2.namePos][npc2.breastSize][npc2.breasts]挤在一起，并在温柔地前后抽动[npc.her]的[npc.hips]时发出一声小小的[npc.moan]，"
								+ "[npc.she]耐心地品味着[npc2.her]的[npc2.scent]，在[npc2.her]胸部缓慢地摩擦[npc.cock+]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]温柔地用[npc.cock+]在[npc2.namePos]平坦的胸部上磨蹭着，"
								+ "[npc.name]开始前后扭动[npc.her]的[npc.hips]，缓缓地摩擦[npc2.her]的躯干，每次推入都会发出[npc.moan]。",

						"[npc.name]温柔地用[npc.cock+]推压着[npc2.namePos]平坦[npc2.breasts+]，"
								+ "[npc.name]开始轻柔地将[npc.her]的[npc.hips]向前推，当[npc.she]温柔地在用[npc2.her]的胸部上下摩擦时，漏出[npc.moans+]。",

						"[npc.name]温柔地揉搓着[npc2.namePos]的平胸，并在轻轻地前后抽动[npc.her]的[npc.hips]时发出轻声[npc.a_moan]，"
								+ "[npc.she]在[npc2.her]躯干摩擦[npc.her][npc.cock+]时，缓慢地吸入[npc2.her][npc2.scent]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "乳交";
			} else {
				return "贫乳乳交";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "继续操[npc2.namePos][npc2.breasts+]。";
			} else {
				return "继续用[npc2.namePos]的胸部乳交。";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.her][npc.cock+]饥渴地在[npc2.namePos][npc2.breasts+]间滑动，"
								+ "[npc.name]开始疯狂地前后扭动[npc.hips]，贪婪地操着[npc2.her]的躯干，每次推入都会发出一阵[npc.a_moan+]。",

						"[npc.name]将[npc.cock+]不顾一切地推入[npc2.namePos][npc2.breasts+]之间的乳沟，"
								+ "[npc.name]开始竭力地将[npc.hips]向前推，愉悦地操着[npc2.her]的[npc2.breasts]，漏出[npc.moans+]。",

						"[npc.name]贪婪地将[npc2.namePos]的[npc2.breasts]挤在一起，并在发狂地前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
								+ "[npc.she]在急切地操[npc2.her]的乳沟的同时，吸入[npc2.her]的[npc2.scent]。"));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.her][npc.cock+]饥渴地在[npc2.namePos]的小[npc2.breasts+]间滑动，"
								+ "[npc.name]开始疯狂地前后扭动[npc.hips]，贪婪地操[npc2.her]小巧的乳沟，每次推入都会发出一阵[npc.a_moan+]。",

						"[npc.name]将[npc.cock+]不顾一切地推入[npc2.namePos][npc2.breasts+]之间那微小的乳沟，"
								+ "[npc.name]开始竭力地将[npc.hips]向前推，愉悦地上下摩擦着[npc2.her]的胸部，漏出[npc.moans+]。",

						"[npc.name]贪婪地试着将[npc2.namePos]的[npc2.breastSize][npc2.breasts]挤在一起，并在发狂地前后抽动[npc.her][npc.hips]时发出[npc.a_moan+]，"
								+ "[npc.she]贪婪地品味着[npc2.her]的[npc2.scent]，在[npc2.her]胸部急切地摩擦[npc.cock+]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]饥渴地用[npc.cock+]在[npc2.namePos]平坦的胸部上磨蹭着，"
								+ "[npc.name]开始疯狂地前后扭动[npc.hips]，贪婪地摩擦[npc2.her]的躯干，每次猛推都会发出一阵[npc.a_moan+]。",

						"[npc.name]将[npc.cock+]不顾一切地推压在[npc2.namePos]平坦[npc2.breasts+]，"
								+ "[npc.name]开始竭力地将[npc.hips]向前推，愉悦地上下摩擦着[npc2.her]的胸部，漏出[npc.moans+]。",

						"[npc.name]贪婪地摸索着[npc2.namePos]的平胸，疯狂地前后抽动[npc.her]的[npc.hips]，发出[npc.a_moan+]，"
								+ "[npc.she]贪婪地品味着[npc2.her]的[npc2.scent]，在[npc2.her]身上急切地摩擦[npc.cock+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "乳交(粗暴)";
			} else {
				return "贫乳乳交(粗暴)";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "粗暴地操[npc2.namePos][npc2.breasts+]。";
			} else {
				return "粗暴地用你[npc.cock+]摩擦[npc2.namePos]的平胸。";
			}
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.her][npc.cock+]粗暴地在[npc2.namePos][npc2.breasts+]间滑动，"
								+ "[npc.name]开始猛烈地前后扭动[npc.hips]，用力地操着[npc2.her]的乳沟，每次猛推都会发出一阵[npc.a_moan+]。",

						"[npc.her][npc.cock+]粗暴地插入[npc2.namePos][npc2.breasts+]的乳沟，"
								+ "[npc.name]开始粗暴地将[npc.her]的[npc.hips]向前推，当[npc.she]支配地操着[npc2.her]的[npc2.breasts]时，漏出[npc.moans+]。",

						"[npc.name]贪婪地将[npc2.namePos]的[npc2.breasts]挤在一起，并在支配地前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
								+ "[npc.she]一边粗暴地操着[npc2.her]的乳沟，一边吸入着[npc2.her]的[npc2.scent]。"));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.her][npc.cock+]粗暴地在[npc2.namePos]小小的[npc2.breasts]间滑动，"
								+ "[npc.name]开始猛烈地前后扭动[npc.hips]，用力地操[npc2.her]小巧的乳沟，每次猛推都会发出一阵[npc.a_moan+]。",

						"[npc.her][npc.cock+]粗暴地插入[npc2.namePos][npc2.breasts+]之间那微小的乳沟，"
								+ "[npc.name]开始粗暴地向前拱自己的[npc.hips]，支配地上下摩擦着[npc2.her]的胸部时，漏出[npc.moans+]。",

						"[npc.name]贪婪地试着将[npc2.namePos]的[npc2.breastSize][npc2.breasts]挤在一起，并在支配地前后抽动[npc.her][npc.hips]时发出[npc.a_moan+]，"
								+ "[npc.she]贪婪地品味着[npc2.her]的[npc2.scent]，在[npc2.her]胸部粗暴地摩擦[npc.cock+]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]粗暴地用[npc.cock+]在[npc2.namePos]平坦的胸部上磨蹭着，"
								+ "[npc.name]开始猛烈地前后扭动[npc.hips]，用力地摩擦[npc2.her]的躯干，每次猛推都会发出一阵[npc.a_moan+]。",

						"[npc.her][npc.cock+]粗暴地推压在[npc2.namePos]平坦[npc2.breasts+]，"
								+ "[npc.name]开始粗暴地向前拱自己的[npc.hips]，支配地上下摩擦着[npc2.her]的胸部时，漏出[npc.moans+]。",

						"[npc.name]贪婪地摸索着[npc2.namePos]的平胸，支配地前后抽动[npc.her]的[npc.hips]，发出[npc.a_moan+]，"
								+ "[npc.she]在[npc2.her]躯干粗暴地摩擦[npc.her][npc.cock+]时，吸入[npc2.her][npc2.scent]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "乳交";
			} else {
				return "贫乳乳交";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "继续操[npc2.namePos][npc2.breasts+]。";
			} else {
				return "继续在[npc2.namePos]的平胸上摩擦。";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]将[npc.cock+]滑入[npc2.namePos][npc2.breasts+]之间，"
								+ "[npc.name]开始前后扭动[npc.hips]，操着[npc2.her]的乳沟，每次猛推都会发出一阵[npc.a_moan+]。",

						"[npc.name]将[npc.cock+]推入[npc2.namePos][npc2.breasts+]之间的乳沟，"
								+ "开始将[npc.hips]向前推，愉悦地操着[npc2.her]的[npc2.breasts]，漏出[npc.moans+]。",

						"[npc.name]将[npc2.namePos]的[npc2.breasts]挤在一起，并在前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
								+ "[npc.she]一边操着[npc2.her]的乳沟，一边吸入着[npc2.her]的[npc2.scent]。"));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]将[npc.cock+]滑入[npc2.namePos]的小[npc2.breasts]之间，"
								+ "[npc.name]开始前后扭动[npc.hips]，操着[npc2.her]小巧的乳沟，每次猛推都会发出一阵[npc.a_moan+]。",

						"[npc.name]将[npc.cock+]推入[npc2.namePos][npc2.breasts+]之间那微小的乳沟，"
								+ "[npc.she]开始把[npc.hips]向前推，愉悦地上下摩擦[npc2.her]的胸部，漏出[npc.moans+]。",

						"[npc.name]试图将[npc2.namePos]的[npc2.breastSize][npc2.breasts]挤在一起，并在前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
								+ "[npc.she]在[npc2.her]胸部摩擦[npc.her][npc.cock+]时，吸入[npc2.her]的[npc2.scent]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]用[npc.cock+]磨蹭[npc2.namePos]平坦的胸部，"
								+ "[npc.name]开始前后扭动[npc.hips]，摩擦着[npc2.her]的躯干，每次猛推都会发出一阵[npc.a_moan+]。",

						"[npc.name]将[npc.cock+]压在[npc2.namePos]平坦的[npc2.breasts]上，"
								+ "[npc.she]开始把[npc.hips]向前推，愉悦地上下摩擦[npc2.her]的胸部，漏出[npc.moans+]。",

						"[npc.name]摸索着[npc2.namePos]的平胸，并在前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
								+ "[npc.she]在[npc2.her]躯干摩擦[npc.her][npc.cock+]时，吸入[npc2.her][npc2.scent]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "乳交(渴求)";
			} else {
				return "贫乳乳交(渴求)";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "饥渴地操[npc2.namePos][npc2.breasts+]。";
			} else {
				return "饥渴地在[npc2.namePos]的平胸上摩擦。";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.her][npc.cock+]饥渴地在[npc2.namePos][npc2.breasts+]间滑动，"
								+ "[npc.name]开始疯狂地前后扭动[npc.hips]，贪婪地操着[npc2.her]的躯干，每次推入都会发出一阵[npc.a_moan+]。",

						"[npc.name]将[npc.cock+]不顾一切地推入[npc2.namePos][npc2.breasts+]之间的乳沟，"
								+ "[npc.name]开始竭力地将[npc.hips]向前推，愉悦地操着[npc2.her]的[npc2.breasts]，漏出[npc.moans+]。",

						"[npc.name]贪婪地将[npc2.namePos]的[npc2.breasts]挤在一起，并在发狂地前后抽动[npc.her]的[npc.hips]时发出[npc.a_moan+]，"
								+ "[npc.she]在急切地操[npc2.her]的乳沟的同时，吸入[npc2.her]的[npc2.scent]。"));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.her][npc.cock+]饥渴地在[npc2.namePos]的小[npc2.breasts+]间滑动，"
								+ "[npc.name]开始疯狂地前后扭动[npc.hips]，贪婪地操[npc2.her]小巧的乳沟，每次推入都会发出一阵[npc.a_moan+]。",

						"[npc.name]将[npc.cock+]不顾一切地推入[npc2.namePos][npc2.breasts+]之间那微小的乳沟，"
								+ "[npc.name]开始竭力地将[npc.hips]向前推，愉悦地上下摩擦着[npc2.her]的胸部，漏出[npc.moans+]。",

						"[npc.name]贪婪地试着将[npc2.namePos]的[npc2.breastSize][npc2.breasts]挤在一起，并在发狂地前后抽动[npc.her][npc.hips]时发出[npc.a_moan+]，"
								+ "[npc.she]贪婪地品味着[npc2.her]的[npc2.scent]，在[npc2.her]胸部急切地摩擦[npc.cock+]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]饥渴地用[npc.cock+]在[npc2.namePos]平坦的胸部上磨蹭着，"
								+ "[npc.name]开始疯狂地前后扭动[npc.hips]，贪婪地摩擦[npc2.her]的躯干，每次猛推都会发出一阵[npc.a_moan+]。",

						"[npc.name]将[npc.cock+]不顾一切地推压在[npc2.namePos]平坦[npc2.breasts+]，"
								+ "[npc.name]开始竭力地将[npc.hips]向前推，愉悦地上下摩擦着[npc2.her]的胸部，漏出[npc.moans+]。",

						"[npc.name]贪婪地摸索着[npc2.namePos]的平胸，疯狂地前后抽动[npc.her]的[npc.hips]，发出[npc.a_moan+]，"
								+ "[npc.she]贪婪地品味着[npc2.her]的[npc2.scent]，在[npc2.her]身上急切地摩擦[npc.cock+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "抗拒给予乳交";
			} else {
				return "抗拒给予贫乳乳交";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "试着把你的[npc.cock]从[npc2.namePos][npc2.breasts+]处移开。";
			} else {
				return "试着把你的[npc.cock]从[npc2.namePos]的胸部移开。";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]急切地试着将[npc.her][npc.cock+]从[npc2.namePos]的乳沟里拔出，但[npc2.she]牢牢地把[npc.herHim]控制在原地。"
										+ "在把[npc2.her][npc2.breasts+]挤压在一起的同时温柔地提醒[npc.herHim]，[npc2.she]可以做[npc2.she]想做的任何事。",

								"[npc.Name]慌忙地试着从[npc2.namePos][npc2.breasts+]处移开，但是[npc2.she]牢牢地把[npc.herHim]控制在原地，"
										+ "[npc2.she]无视了[npc.name]无望的抵抗，轻柔地[npc2.moaning]。",

								"[npc.name]试图从[npc2.namePos]的乳沟抽离，但[npc2.namePos]对[npc.she]的控制却过于强力，眼泪开始涌上[npc.her]的[npc.eyes]，"
										+ "[npc2.name]柔声地[npc2.moaning]，用力在[npc2.breasts+]之间压迫[npc.her][npc.cock+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]急切地试着将[npc.her][npc.cock+]从[npc2.namePos]的乳沟里拔出，但[npc2.she]粗暴地把[npc.herHim]控制在原地，"
										+ "在把[npc2.her][npc2.breasts+]挤压在一起的同时咆哮着说[npc2.she]可以对[npc.herHim]做[npc2.she]想做的任何事。",

								"[npc.Name]慌忙地试着从[npc2.namePos][npc2.breasts+]处移开，但是[npc2.she]粗暴地把[npc.herHim]控制在原地，"
										+ "[npc2.she]无视了[npc.her]徒劳的抵抗，[npc.moaning+]",

								"[npc.name]试图从[npc2.namePos]的乳沟抽离，但[npc2.namePos]对[npc.she]的控制却过于强力，眼泪开始涌上[npc.her]的[npc.eyes]，"
										+ "[npc2.name][npc2.moaning+]着，粗暴地在[npc2.breasts+]之间压迫[npc.her][npc.cock+]。"));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]急切地试着将[npc.her][npc.cock+]从[npc2.namePos]的乳沟里拔出，但[npc2.she]牢牢地把[npc.herHim]控制在原地。"
										+ "在把[npc2.her][npc2.breasts+]挤压在一起的同时，[npc2.moaning]着说[npc2.she]可以做[npc2.she]想做的任何事。",

								"[npc.Name]慌忙地试着从[npc2.namePos][npc2.breasts+]处移开，但是[npc2.she]牢牢地把[npc.herHim]控制在原地，"
										+ "[npc2.she]无视了[npc.name]徒劳的抵抗，[npc2.moaning+]着",

								"[npc.name]试图从[npc2.namePos]的乳沟抽离，但[npc2.namePos]对[npc.she]的控制却过于强力，眼泪开始涌上[npc.her]的[npc.eyes]，"
										+ "[npc2.name][npc2.moaning+]着，急切地在[npc2.breasts+]之间压迫[npc.her][npc.cock+]。"));
						break;
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]急切地试着将[npc.her][npc.cock+]从[npc2.name]浅浅的乳沟里拔出，但[npc2.she]牢牢地把[npc.herHim]控制在原地，"
										+ "在试着把[npc2.her][npc2.breasts+]挤压在一起的同时温柔地提醒[npc.herHim]，[npc2.she]可以做[npc2.she]想做的任何事。",

								"[npc.Name]慌忙地试着从[npc2.namePos][npc2.breastSize]的[npc2.breasts]处移开，但是[npc2.she]牢牢地把[npc.herHim]控制在原地，"
										+ "[npc2.she]无视了[npc.name]无望的抵抗，轻柔地[npc2.moaning]。",

								"[npc.name]试图从[npc2.namePos]挤出的浅乳沟中抽离，但[npc2.name]对[npc.she]的控制却过于强力，眼泪开始涌上[npc.her]的[npc.eyes]，"
										+ "[npc2.name]柔声地[npc2.moaning]，用力在[npc2.breasts+]之间压迫[npc.her][npc.cock+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]急切地试着将[npc.her][npc.cock+]从[npc2.name]浅浅的乳沟里拔出，但[npc2.she]粗暴地把[npc.herHim]控制在原地，"
										+ "在试着把[npc2.her][npc2.breasts+]挤压在一起的同时，咆哮着说[npc2.she]可以对[npc.herHim]做[npc2.she]想做的任何事。",

								"[npc.Name]慌忙地试着从[npc2.namePos][npc2.breastSize]的[npc2.breasts]处移开，但是[npc2.she]粗暴地把[npc.herHim]控制在原地，"
										+ "[npc2.she]无视了[npc.her]徒劳的抵抗，[npc.moaning+]",

								"[npc.name]试图从[npc2.namePos]挤出的浅乳沟中抽离，但[npc2.name]对[npc.she]的控制却过于强力，眼泪开始涌上[npc.her]的[npc.eyes]，"
										+ "[npc2.name][npc2.moaning+]着，粗暴地在[npc2.breasts+]之间压迫[npc.her][npc.cock+]。"));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]急切地试着将[npc.her][npc.cock+]从[npc2.name]浅浅的乳沟里拔出，但[npc2.she]牢牢地把[npc.herHim]控制在原地，"
										+ "在试着把[npc2.her][npc2.breasts+]挤压在一起的同时，[npc2.moaning]着说[npc2.she]可以做[npc2.she]想做的任何事。",

								"[npc.Name]慌忙地试着从[npc2.namePos][npc2.breastSize]的[npc2.breasts]处移开，但是[npc2.she]牢牢地把[npc.herHim]控制在原地，"
										+ "[npc2.she]无视了[npc.her]徒劳的抵抗，[npc.moaning+]",

								"[npc.name]试图从[npc2.namePos]挤出的浅乳沟中抽离，但[npc2.name]对[npc.she]的控制却过于强力，眼泪开始涌上[npc.her]的[npc.eyes]，"
										+ "[npc2.name][npc2.moaning+]着，急切地在[npc2.breasts+]之间压迫[npc.her][npc.cock+]。"));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]急切地试着将[npc.her][npc.cock+]从[npc2.namePos]的平胸处移开，但[npc2.she]牢牢地把[npc.herHim]控制在原地，"
										+ "在摩擦着[npc.herHim]的同时温柔地[npc2.moanVerb]着说，[npc2.she]可以做[npc2.she]想做的任何事。",

								"[npc.Name]慌忙地试着从[npc2.namePos]的胸部移开，但是[npc2.she]牢牢地把[npc.herHim]控制在原地，"
										+ "[npc2.she]无视了[npc.name]无望的抵抗，轻柔地[npc2.moaning]。",

								"[npc.name]试图从[npc2.namePos]的身上抽离，但[npc2.name]对[npc.she]的控制却过于强力，眼泪开始涌上[npc.her]的[npc.eyes]，"
										+ "[npc2.name]柔声地[npc2.moaning]，有力地用胸部磨蹭[npc.her][npc.cock+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]急切地试着将[npc.her][npc.cock+]从[npc2.namePos]的胸部移开，但[npc2.she]牢牢地把[npc.herHim]控制在原地，"
										+ "[npc2.she]在激烈地摩擦着[npc.herHim]的同时[npc2.she]咆哮着说，[npc2.she]可以做[npc2.she]想做的任何事。",

								"[npc.Name]慌忙地试着从[npc2.namePos]的胸部移开，但是[npc2.she]粗暴地把[npc.herHim]控制在原地，"
										+ "[npc2.she]无视了[npc.name]徒劳的抵抗，[npc2.moaning+]着",

								"[npc.name]试图从[npc2.namePos]的身上抽离，但[npc2.name]对[npc.she]的控制却过于强力，眼泪开始涌上[npc.her]的[npc.eyes]，"
										+ "[npc2.name][npc2.moaning+]着，粗暴地用胸部磨蹭[npc.her][npc.cock+]。"));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]急切地试着将[npc.her][npc.cock+]从[npc2.namePos]的胸部移开，但[npc2.name]牢牢地把[npc.herHim]控制在原地，"
										+ "[npc2.she]在摩擦着[npc.herHim]的同时[npc2.moanVerb]着说，[npc2.she]可以做[npc2.she]想做的任何事。",

								"[npc.Name]慌忙地试着从[npc2.namePos]的胸部移开，但是[npc2.she]牢牢地把[npc.herHim]控制在原地，"
										+ "[npc2.she]无视了[npc.name]徒劳的抵抗，[npc2.moaning+]着",

								"[npc.name]试图从[npc2.namePos]的身上抽离，但[npc2.name]对[npc.she]的控制却过于强力，眼泪开始涌上[npc.her]的[npc.eyes]，"
										+ "[npc2.name][npc2.moaning+]着，急切地用胸部磨蹭[npc.her][npc.cock+]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "停止接受乳交";
			} else {
				return "停止接受贫乳乳交";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "把你[npc.cock+]从[npc2.namePos][npc2.breasts+]处移开，停止操它们。";
			} else {
				return "把你[npc.cock+]从[npc2.namePos]的胸部移开，停止摩擦[npc2.herHim]。";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"粗暴地把[npc2.name]推开，"
										+ "[npc.name]把[npc.her][npc.cock+]从[npc2.her]的乳沟里拔出，并且告诉[npc2.herHim]，[npc.sheHas]已经操够了[npc2.her][npc2.breasts+]。",

								"粗暴地把[npc.her][npc.cock+]从[npc2.namePos]的乳沟里拔出，[npc.name]告诉[npc2.herHim]，[npc.sheHas]已经操够了[npc2.her][npc2.breasts+]。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]把[npc.her][npc.cock+]从[npc2.namePos]的乳沟里拔出，并且告诉[npc2.name]，[npc.sheHas]已经操够了[npc2.her][npc2.breasts+]。",

								"把[npc.her][npc.cock+]从[npc2.namePos]的乳沟里拔出，[npc.name]告诉[npc2.name]，[npc.sheHas]已经操够了[npc2.her][npc2.breasts+]。"));
						break;
				}
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]粗暴地把[npc2.name]推开，把[npc.her][npc.cock+]从[npc2.her]浅浅的乳沟里移开"
										+ "然后告诉[npc2.herHim]，[npc.sheHas]已经操够了[npc2.her]的[npc2.breastSize][npc2.breasts]。",

								"粗暴地把[npc.her][npc.cock+]从[npc2.namePos]浅浅的乳沟里移开，"
										+ "[npc.name]告诉[npc2.herHim]，[npc.sheHas]已经操够了[npc2.her]的[npc2.breastSize][npc2.breasts]。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]把[npc.her][npc.cock+]从[npc2.namePos]浅浅的乳沟里移开"
										+ "然后告诉[npc2.herHim]，[npc.sheHas]已经操够了[npc2.her]的[npc2.breastSize][npc2.breasts]。",

								"把[npc.her][npc.cock+]从[npc2.namePos]浅浅的乳沟里移开，"
										+ "[npc.name]告诉[npc2.name]，[npc.sheHas]已经操够了[npc2.her]的[npc2.breastSize][npc2.breasts]。"));
						break;
				}
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]粗暴地把[npc2.name]推开，把[npc.her][npc.cock+]从[npc2.her]的胸部移开，并且告诉[npc2.herHim]，[npc.sheHas]已经受够了摩擦[npc2.herHim]这件事。",

								"粗暴地把[npc.her][npc.cock+]从[npc2.namePos]的胸部移开，[npc.name]告诉[npc2.herHim]，[npc.sheHas]已经受够了摩擦[npc2.herHim]这件事。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]把[npc.her][npc.cock+]从[npc2.namePos]的胸部移开，并且告诉[npc2.herHim]，[npc.sheHas]已经受够了摩擦[npc2.herHim]这件事。",

								"把[npc.her][npc.cock+]从[npc2.namePos]的胸部移开，[npc.name]告诉[npc2.herHim]，[npc.sheHas]已经受够了摩擦[npc2.herHim]这件事。"));
						break;
				}
			}
			
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]乞求[npc.herHim]放过自己，[npc2.herHim][npc2.moaning+]着，继续奋力抵抗着[npc.herHim]。",
	
								"伴随着[npc2.a_moan+]，[npc2.name]乞求[npc.herHim]放过[npc2.herHim]，在[npc2.she]虚弱地尝试着把[npc.herHim]推开时，泪水从[npc2.her][npc2.eyes]涌出。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一声[npc2.a_moan+]，暴露了[npc2.her]希望[npc.herHim]继续下去的欲望。",
	
								"伴随着[npc2.a_moan+]，[npc2.name]乞求[npc.herHim]继续使用[npc2.herHim]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction USING_COCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "提供乳交";
			} else {
				return "提供贫乳乳交";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "用[npc2.namePos][npc2.cock+]操[npc.namePos][npc.breasts+]。";
			} else {
				return "用[npc2.namePos][npc2.cock+]磨蹭你平平的胸脯。";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"温柔地握住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]的乳沟，滑动起来，"
								+ "[npc.she]将[npc.her][npc.breasts+]挤压到一起并开始让[npc2.herHim]操乳头。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"急切地握住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]的乳沟，滑动起来，"
								+ "[npc.she]将[npc.her][npc.breasts+]挤压到一起并开始热情地让[npc2.herHim]操乳头。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"粗暴地抓住[npc2.namePos][npc2.cock+]，[npc.Name]拉它到[npc.her]的乳沟，滑动起来，"
								+ "[npc.she]将[npc.her][npc.breasts+]挤压到一起并开始让[npc2.herHim]有力地操乳头。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"握住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]的乳沟，滑动起来，"
								+ "[npc.she]将[npc.her][npc.breasts+]挤压到一起并开始让[npc2.herHim]操乳头。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"急切地握住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]的乳沟，滑动起来，"
								+ "[npc.she]将[npc.her][npc.breasts+]挤压到一起并开始热情地让[npc2.herHim]操乳头。"));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为回应，[npc2.Name]的口中漏出了一声愉悦而细小的[npc2.moan]，"
											+ "当[npc2.she]鼓励[npc.herHim]继续的时候，帮忙把[npc2.her]的[npc2.breasts]挤在一起。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为回应，[npc2.Name]从口中漏出了[npc2.a_moan+]，"
											+ "当[npc2.she]热心地鼓励[npc.herHim]继续的时候，急切地帮忙把[npc2.her]的[npc2.breasts]挤在一起。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为回应，[npc2.Name]从口中漏出了[npc2.a_moan+]，"
											+ "当[npc2.she]鼓励[npc.herHim]继续的时候，粗暴地帮忙把[npc2.her]的[npc2.breasts]挤在一起。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" 作为回应，[npc2.Name]的口中漏出了[npc2.a_moan+]，"
											+ "当[npc2.she]热心地鼓励[npc.herHim]继续的时候，急切地帮忙把[npc2.her]的[npc2.breasts]挤在一起。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]的口中漏出了[npc2.moan]，"
											+ "在温顺地请求[npc.herHim]继续的时候，[npc2.she]帮忙把[npc.namePos]的[npc2.breasts]挤在一起。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name]发出了[npc2.a_moan+]，"
											+ "[npc2.she]乞求着[npc.Name]停下，她虚弱地试着推开[npc.herHim]。"));
							break;
						default:
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"温柔地握住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]平坦的乳沟，滑动起来，"
										+ "[npc.she]尽最大努力去将[npc.her][npc.breastSize][npc.breasts+]挤压到一起以便让[npc2.herHim]操乳头。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"急切地握住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]平坦的乳沟，滑动起来，"
										+ "[npc.she]尽最大努力去将[npc.her][npc.breastSize][npc.breasts+]挤压到一起以便让[npc2.herHim]热情地操乳头。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"粗暴地抓住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]平坦的乳沟，滑动起来，"
										+ "[npc.she]尽最大努力去将[npc.her][npc.breastSize][npc.breasts+]挤压到一起以便让[npc2.herHim]有力地操乳头。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"握住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]平坦的乳沟，滑动起来，"
										+ "[npc.she]尽最大努力去将[npc.her][npc.breastSize][npc.breasts+]挤压到一起以便让[npc2.herHim]操乳头。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"急切地握住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]平坦的乳沟，滑动起来，"
										+ "[npc.she]尽最大努力去将[npc.her][npc.breastSize][npc.breasts+]挤压到一起以便让[npc2.herHim]热情地操乳头。"));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出了一声愉悦而细小的[npc2.moan]，温柔地插入[npc.namePos]胸部当[npc2.she]鼓励[npc.herHim]继续时。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出了一声[npc2.a_moan+]，急切地插入[npc.namePos]胸部当[npc2.she]热心地鼓励[npc.herHim]继续时。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出[npc2.a_moan+]作为回应，粗暴地插入[npc.namePos]胸部当[npc2.she]要求[npc.herHim]继续时。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出了一声[npc2.a_moan+]，饥渴地插入[npc.namePos]的胸部，[npc2.name]热心地鼓励[npc.herHim]继续。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出[npc2.a_moan+]作为回应，插入[npc.namePos]胸部当[npc2.she]温顺地请求[npc.herHim]继续时。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出[npc2.a_moan+]作为回应，在[npc2.she]乞求[npc.herHim]停下时，虚弱地尝试着将[npc.Name]推开。"));
							break;
						default:
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"温柔地握住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]平坦的胸部，滑动起来，"
										 + "[npc.she]用躯干摩擦着[npc2.namePos][npc2.cock+]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"急切地抓住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]平坦的胸部，滑动起来，"
										+ "[npc.she]亢奋地躯干摩擦着[npc2.namePos][npc2.cock+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"粗暴地抓住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]平坦的胸部，滑动起来，"
										+ "[npc.she]激烈地用躯干摩擦着[npc2.namePos][npc2.cock+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"握住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]平坦的胸部，滑动起来，"
										 + "[npc.she]用躯干摩擦着[npc2.namePos][npc2.cock+]。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"急切地握住[npc2.namePos][npc2.cock+]，[npc.Name]引导它到[npc.her]平坦的胸部，滑动起来，"
										+ "[npc.she]亢奋地躯干摩擦着[npc2.namePos][npc2.cock+]。"));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出了一声愉悦而细小的[npc2.moan]，温柔地插入[npc.namePos]胸部当[npc2.she]鼓励[npc.herHim]继续时。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出了一声[npc2.a_moan+]，急切地插入[npc.namePos]胸部当[npc2.she]热心地鼓励[npc.herHim]继续时。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出[npc2.a_moan+]作为回应，粗暴地插入[npc.namePos]胸部当[npc2.she]要求[npc.herHim]继续时。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"作为回应，[npc2.Name]从口中漏出了一声[npc2.a_moan+]，饥渴地插入[npc.namePos]的胸部，[npc2.name]热心地鼓励[npc.herHim]继续。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出[npc2.a_moan+]作为回应，插入[npc.namePos]胸部当[npc2.she]温顺地请求[npc.herHim]继续时。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]发出[npc2.a_moan+]作为回应，在[npc2.she]乞求[npc.herHim]停下时，虚弱地尝试着将[npc.Name]推开"));
							break;
						default:
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};

	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]贪婪地将自己[npc2.cock+]插入[npc.namePos][npc.breasts+]，"
										+ "[npc2.she]发出[npc2.a_moan+]，亢奋地操干[npc.namePos]的乳沟。",
			
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]插进[npc.namePos][npc.breasts+]之间。",
										
								"[npc2.name]愉悦地[npc2.moaning]着，急切地用[npc2.cock+]在[npc.namePos][npc.breasts+]之间进进出出。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]无法将[npc2.cock]从[npc.namePos]的[npc.breasts+]间抽出，"
										+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
			
								"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
										+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.cock+]在自己[npc.breasts+]之间继续抽插。",
			
								"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.cock]从[npc.namePos][npc.breasts+]中抽离。"));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]温柔地将自己[npc2.cock+]插入[npc.namePos][npc.breasts+]，"
										+ "[npc2.she]发出一声轻柔的[npc2.moan]，开始操干[npc.namePos]的乳沟。",
			
								"[npc2.name]温柔地将[npc2.cock+]挺入[npc.namePos][npc.breasts+]间，口中飘出一声轻柔的[npc2.moan]。",
										
								"[npc2.name]愉悦地[npc2.moaning]着，在[npc.namePos][npc.breasts+]中温柔地抽送着[npc2.cock+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]粗暴地将[npc2.cock+]插到[npc.namePos][npc.breasts+]间，"
										+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地操干[npc.namePos]的乳沟。",
			
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地将[npc2.cock+]插进[npc.namePos][npc.breasts+]之间。",
										
								"[npc2.name]愉悦地[npc2.moaning]着，粗鲁地用[npc2.cock+]前后磨蹭[npc.namePos][npc.breasts+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]将[npc2.cock+]深深插入[npc.namePos][npc.breasts+]，"
										+ "[npc2.she]发出[npc2.a_moan+]，操干着[npc.namePos]的乳沟。",
			
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]插进[npc.namePos][npc.breasts+]之间。",
										
								"[npc2.name]愉悦地[npc2.moaning]着，用[npc2.cock+]前后磨蹭[npc.namePos][npc.breasts+]。"));
						break;
				}
				
			} else if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]贪婪地将自己[npc2.cock+]插入[npc.Name]挤出的小小乳沟，"
										+ "[npc2.she]发出[npc2.a_moan+]，亢奋地操干[npc.namePos][npc.breasts+]。",
			
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]插进[npc.namePos][npc.breastSize]的[npc.breasts]间。",
										
								"[npc2.name]愉悦地[npc2.moaning]着，急切地用[npc2.cock+]在[npc.namePos][npc.breasts+]之间浅浅的乳沟里进进出出。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]无法将[npc2.cock]从[npc.namePos]的[npc.breasts+]间抽出，"
										+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
			
								"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
										+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.cock+]在自己[npc.breasts+]之间继续抽插。",
			
								"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.cock]从[npc.namePos][npc.breasts+]中抽离。"));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]温柔地将自己[npc2.cock+]插入[npc.Name]挤出的小小乳沟，"
										+ "[npc2.she]发出一声轻柔的[npc2.moan]，开始操干[npc.namePos][npc.breasts+]。",
			
								"[npc2.name]温柔地将[npc2.cock+]挺入[npc.namePos][npc.breastSize]的[npc.breasts]间，口中飘出一声轻柔的[npc2.moan]。",
										
								"[npc2.name]愉悦地[npc2.moaning]着，温柔地用[npc2.cock+]在[npc.namePos][npc.breasts+]之间浅浅的乳沟里进进出出。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]粗暴地将自己[npc2.cock+]插入[npc.Name]挤出的小小乳沟，"
										+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地操干[npc.namePos][npc.breasts+]。",
			
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地将[npc2.cock+]插进[npc.namePos][npc.breastSize]的[npc.breasts]间。",
										
								"[npc2.name]愉悦地[npc2.moaning]着，粗暴地用[npc2.cock+]在[npc.namePos][npc.breasts+]之间浅浅的乳沟里进进出出。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]将自己[npc2.cock+]插入[npc.Name]挤出的小小乳沟，"
										+ "[npc2.she]发出[npc2.a_moan+]，操干着[npc.namePos][npc.breasts+]。",
			
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]插进[npc.namePos][npc.breastSize]的[npc.breasts]间。",
										
								"[npc2.name]愉悦地[npc2.moaning]着，用[npc2.cock+]在[npc.namePos][npc.breasts+]之间浅浅的乳沟里进进出出。"));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]贪婪地将自己[npc2.cock+]顶住[npc.namePos]平坦的胸脯，"
										+ "[npc2.she]发出[npc2.a_moan+]，热情地磨蹭[npc.namePos]的躯干。",
			
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]顶在[npc.namePos]的平胸上。",
										
								"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地用[npc2.cock+]磨蹭[npc.namePos]平坦的胸部。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]无法将[npc2.cock]从[npc.namePos]平坦的胸部间抽出，"
										+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
			
								"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
										+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.cock+]在平胸中继续抽插。",
			
								"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.cock]从[npc.namePos]的平胸上抽离。"));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]温柔地将自己[npc2.cock+]顶住[npc.namePos]平坦的胸脯，"
										+ "[npc2.she]发出一声轻柔的[npc2.moan]，磨蹭着[npc.namePos]的躯干。",
			
								"[npc2.name]温柔地用[npc2.cock+]上下磨蹭[npc.namePos]平坦的胸部，口中飘出一声轻柔的[npc2.moan]。",
										
								"[npc2.name]愉悦地[npc2.moaning]着，温柔地用[npc2.cock+]磨蹭[npc.namePos]平坦的胸部。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]粗暴地将自己[npc2.cock+]顶住[npc.namePos]平坦的胸脯，"
										+ "[npc2.she]发出[npc2.a_moan+]，粗鲁地磨蹭[npc.namePos]的躯干。",
			
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地用[npc2.cock+]来回磨蹭[npc.namePos]平坦的胸部。",
										
								"[npc2.name]愉悦地[npc2.moaning]着，粗暴地用[npc2.cock+]磨蹭[npc.namePos]平坦的胸部。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]将自己[npc2.cock+]顶住[npc.namePos]平坦的胸脯，"
										+ "[npc2.she]发出[npc2.a_moan+]，磨蹭着[npc.namePos]的躯干。",
			
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]顶在[npc.namePos]的平胸上。",
										
								"[npc2.name]愉悦地[npc2.moaning]着，用[npc2.cock+]磨蹭着[npc.namePos]平坦的胸部。"));
						break;
				}
			}
		}
		return "";
	}
	
	public static final SexAction PERFORMING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "提供乳交(温柔)";
			} else {
				return "提供贫乳乳交(温柔)";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "温柔地用你[npc.breasts+]取悦[npc2.namePos][npc2.cock+]。";
			} else {
				return "用你平平的胸脯温柔地取悦[npc2.namePos][npc2.cock+]。";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]把[npc.breasts+]挤到一起，裹住[npc2.namePos][npc2.cock+]，"
								+ "[npc.name]温柔地上下移动身子，一边轻声[npc.moaning]，一边用自己的乳沟提供服务。",

						"[npc.Name]用[npc.breasts+]温柔地裹住了[npc2.namePos][npc2.cock+]，缓缓上下甩动奶子，"
								+ "[npc.she]发出轻柔的[npc.moan]，情意绵绵地让[npc2.herHim]操奶子。",

						"[npc.Name]发出轻柔的[npc.moan]，把[npc.breasts+]挤到一起，"
								+ "[npc.she]将[npc2.namePos][npc2.cock+]裹在枕头般的小丘中，情意绵绵地让[npc2.herHim]操奶子。"));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]把[npc.breasts+]挤到一起，蹭着[npc2.namePos][npc2.cock+]侧面，"
									+ "[npc.name]温柔地上下移动身子，一边轻声[npc.moaning]，一边尽力用好自己的小小乳沟。",

							"[npc.Name]温柔地用[npc.her][npc.breastSize]的[npc.breasts]蹭着[npc2.namePos][npc2.cock+]侧面，缓缓乳摇着，"
									+ "[npc.she]发出轻柔的[npc.moan]，情意绵绵地努力让[npc2.herHim]操奶子。",

							"[npc.Name]发出轻柔的[npc.moan]，把[npc.breasts+]挤到一起，"
									+ "用[npc.she]有的那点浅浅乳沟，尽全力取悦着[npc2.namePos][npc2.cock+]。"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]用[npc.fingers+]握住[npc2.namePos][npc2.cock+]，温柔地上下动起身子，"
									+ "[npc.she]一边轻声[npc.moaning+]，一边推出平胸，磨蹭着[npc2.name]。",

							"[npc.Name]温柔地用[npc.fingers+]握住[npc2.namePos][npc2.cock+]，上下移动身子，"
									+ "[npc.she]将平胸挤到一起，努力想让[npc2.herHim]操奶子。",

							"[npc.Name]发出柔和的[npc.moan]，[npc.fingers+]环住[npc2.namePos][npc2.cock+]，"
									+ "然后顶上平胸，让[npc2.herHim]来模拟操奶子"));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "提供乳交";
			} else {
				return "提供贫乳乳交";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "用你[npc.breasts+]取悦[npc2.namePos][npc2.cock+]。";
			} else {
				return "用你平平的胸脯取悦[npc2.namePos][npc2.cock+]。";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]愉悦地把[npc.breasts+]挤到一起，裹住[npc2.namePos][npc2.cock+]，"
								+ "[npc.name]热情地上下移动身子，一边[npc.moaning+]，一边用乳沟饥渴地让[npc2.herHim]操奶子。",

						"[npc.Name]饥渴地用[npc.breasts+]裹住了[npc2.namePos][npc2.cock+]，竭力上下甩动奶子，"
								+ "[npc.she]发出[npc.a_moan+]，热情地让[npc2.herHim]操奶子。",

						"[npc.Name]发出[npc.a_moan+]，愉悦地把[npc.breasts+]挤到一起，"
								+ "[npc.she]将[npc2.namePos][npc2.cock+]裹在枕头般的小丘中，饥渴地让[npc2.herHim]操奶子。"));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]愉悦地把[npc.breasts+]挤到一起，蹭着[npc2.namePos][npc2.cock+]侧面，"
									+ "[npc.name]热情地上下移动身子，一边[npc.moaning+]，一边尽力用好自己的小小乳沟。",

							"[npc.Name]饥渴地用[npc.her][npc.breastSize]的[npc.breasts]蹭着[npc2.namePos][npc2.cock+]侧面，竭力乳摇着，"
									+ "[npc.she]发出[npc.a_moan+]，热情地努力让[npc2.herHim]操奶子。",

							"[npc.Name]发出[npc.a_moan+]，愉悦地把[npc.breasts+]挤到一起，"
									+ "用[npc.she]有的那点浅浅乳沟，尽全力取悦着[npc2.namePos][npc2.cock+]。"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]愉悦地用[npc.fingers+]握住[npc2.namePos][npc2.cock+]，热情地上下动起身子，"
									+ "[npc.she]一边[npc.moaning+]，一边推出平胸，迷乱地磨蹭着[npc2.name]。",

							"[npc.Name]饥渴地用[npc.fingers+]握住了[npc2.namePos][npc2.cock+]，竭力上下移动身子，"
									+ "[npc.she]将平胸挤到一起，努力想让[npc2.herHim]操奶子。",

							"[npc.Name]发出[npc.a_moan+]，开心地用[npc.fingers+]环住[npc2.namePos][npc2.cock+]，"
									+ "然后顶上平胸，饥渴地让[npc2.herHim]来模拟操奶子"));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "提供乳交(粗暴)";
			} else {
				return "提供贫乳乳交(粗暴)";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "用你[npc.breasts+]粗暴地取悦[npc2.namePos][npc2.cock+]。";
			} else {
				return "用你平平的胸脯粗暴地取悦[npc2.namePos][npc2.cock+]。";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]粗暴地把[npc.breasts+]挤到一起，裹住[npc2.namePos][npc2.cock+]，"
								+ "[npc.name]迅速上下移动身子，一边[npc.moaning+]，一边用乳沟让[npc2.herHim]支配地操奶子。",

						"[npc.Name]支配性地用[npc.breasts+]裹住[npc2.namePos][npc2.cock+]，粗暴地上下移动身子，"
								+ "[npc.she]发出[npc.a_moan+]，让[npc2.herHim]强硬地操奶子。",

						"[npc.Name]发出[npc.a_moan+]，强硬地把[npc.breasts+]挤到一起，"
								+ "[npc.she]将[npc2.namePos][npc2.cock+]裹在枕头般的小丘中，让[npc2.herHim]支配地操奶子。"));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地把[npc.breasts+]挤到一起，蹭着[npc2.namePos][npc2.cock+]侧面，"
									+ "[npc.name]迅速上下移动身子，一边[npc.moaning+]，一边尽力用好自己的小小乳沟。",

							"[npc.Name]支配地用[npc.her][npc.breastSize]的[npc.breasts]蹭着[npc2.namePos][npc2.cock+]侧面，粗暴乳摇着，"
									+ "[npc.she]发出[npc.a_moan+]，努力让[npc2.herHim]强硬地操奶子。",

							"[npc.Name]发出[npc.a_moan+]，强硬地把[npc.breasts+]挤到一起，"
									+ "用[npc.she]有的那点浅浅乳沟，尽全力取悦着[npc2.namePos][npc2.cock+]。"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗暴地用[npc.fingers+]握住[npc2.namePos][npc2.cock+]，粗鲁地上下动起身子，"
									+ "[npc.she]一边[npc.moaning+]，一边推出平胸，强硬地磨蹭着[npc2.name]。",

							"[npc.Name]支配性地用[npc.fingers+]握住[npc2.namePos][npc2.cock+]，粗暴地上下移动身子，"
									+ "[npc.she]将平胸挤到一起，努力想让[npc2.herHim]操奶子。",

							"[npc.Name]发出[npc.a_moan+]，[npc.fingers+]强硬地环住[npc2.namePos][npc2.cock+]，"
									+ "然后顶上平胸，粗鲁地让[npc2.herHim]来模拟操奶子"));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "提供乳交";
			} else {
				return "提供贫乳乳交";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "用你[npc.breasts+]取悦[npc2.namePos][npc2.cock+]。";
			} else {
				return "用你平平的胸脯取悦[npc2.namePos][npc2.cock+]。";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]把[npc.breasts+]挤到一起，裹住[npc2.namePos][npc2.cock+]，"
								+ "[npc.name]上下移动身子，一边[npc.moaning+]，一边用乳沟让[npc2.herHim]操奶子。",

						"[npc.Name]用[npc.breasts+]裹住了[npc2.namePos][npc2.cock+]，上下甩动奶子，"
								+ "[npc.she]发出[npc.a_moan+]，让[npc2.herHim]操奶子。",

						"[npc.Name]发出[npc.a_moan+]，把[npc.breasts+]挤到一起，"
								+ "[npc.she]将[npc2.namePos][npc2.cock+]裹在枕头般的小丘中，让[npc2.herHim]操奶子。"));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]愉悦地把[npc.breasts+]挤到一起，蹭着[npc2.namePos][npc2.cock+]侧面，"
									+ "[npc.name]上下移动身子，一边[npc.moaning+]，一边尽力用好自己的小小乳沟。",

							"[npc.Name]用[npc.her][npc.breastSize]的[npc.breasts]蹭着[npc2.namePos][npc2.cock+]侧面，乳摇着，"
									+ "[npc.she]发出[npc.a_moan+]，努力让[npc2.herHim]操奶子。",

							"[npc.Name]发出[npc.a_moan+]，把[npc.breasts+]挤到一起，"
									+ "用[npc.she]有的那点浅浅乳沟，尽全力取悦着[npc2.namePos][npc2.cock+]。"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]愉悦地用[npc.fingers+]握住[npc2.namePos][npc2.cock+]，上下动起身子，"
									+ "[npc.she]一边[npc.moaning+]，一边推出平胸，磨蹭着[npc2.name]。",

							"[npc.Name]用[npc.fingers+]握住[npc2.namePos][npc2.cock+]，上下移动身子，"
									+ "[npc.she]将平胸挤到一起，努力想让[npc2.herHim]操奶子。",

							"[npc.Name]发出[npc.a_moan+]，[npc.fingers+]环住[npc2.namePos][npc2.cock+]，"
									+ "然后顶上平胸，让[npc2.herHim]来模拟操奶子"));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "提供乳交(渴求)";
			} else {
				return "提供贫乳乳交(渴求)";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "用你[npc.breasts+]饥渴地取悦[npc2.namePos][npc2.cock+]。";
			} else {
				return "用你平平的胸脯饥渴地取悦[npc2.namePos][npc2.cock+]。";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]愉悦地把[npc.breasts+]挤到一起，裹住[npc2.namePos][npc2.cock+]，"
								+ "[npc.name]热情地上下移动身子，一边[npc.moaning+]，一边用乳沟饥渴地让[npc2.herHim]操奶子。",

						"[npc.Name]饥渴地用[npc.breasts+]裹住了[npc2.namePos][npc2.cock+]，竭力上下甩动奶子，"
								+ "[npc.she]发出[npc.a_moan+]，热情地让[npc2.herHim]操奶子。",

						"[npc.Name]发出[npc.a_moan+]，愉悦地把[npc.breasts+]挤到一起，"
								+ "[npc.she]将[npc2.namePos][npc2.cock+]裹在枕头般的小丘中，饥渴地让[npc2.herHim]操奶子。"));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]愉悦地把[npc.breasts+]挤到一起，蹭着[npc2.namePos][npc2.cock+]侧面，"
									+ "[npc.name]热情地上下移动身子，一边[npc.moaning+]，一边尽力用好自己的小小乳沟。",

							"[npc.Name]饥渴地用[npc.her][npc.breastSize]的[npc.breasts]蹭着[npc2.namePos][npc2.cock+]侧面，竭力乳摇着，"
									+ "[npc.she]发出[npc.a_moan+]，热情地努力让[npc2.herHim]操奶子。",

							"[npc.Name]发出[npc.a_moan+]，愉悦地把[npc.breasts+]挤到一起，"
									+ "用[npc.she]有的那点浅浅乳沟，尽全力取悦着[npc2.namePos][npc2.cock+]。"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]愉悦地用[npc.fingers+]握住[npc2.namePos][npc2.cock+]，热情地上下动起身子，"
									+ "[npc.she]一边[npc.moaning+]，一边推出平胸，迷乱地磨蹭着[npc2.name]。",

							"[npc.Name]饥渴地用[npc.fingers+]握住了[npc2.namePos][npc2.cock+]，竭力上下移动身子，"
									+ "[npc.she]将平胸挤到一起，努力想让[npc2.herHim]操奶子。",

							"[npc.Name]发出[npc.a_moan+]，开心地用[npc.fingers+]环住[npc2.namePos][npc2.cock+]，"
									+ "然后顶上平胸，饥渴地让[npc2.herHim]来模拟操奶子"));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "抵抗提供乳交";
			} else {
				return "抵抗提供贫乳乳交";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "努力让你[npc.breasts+]远离[npc2.namePos][npc2.cock+]。";
			} else {
				return "努力让你平平的胸脯远离[npc2.namePos][npc2.cock+]。";
			}
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]发出[npc.moan+]，努力将自己[npc.breasts+]拉远[npc2.namePos][npc2.cock+]，"
								+ "然后祈求[npc2.herHim]放开自己。",

						"[npc.Name]发出[npc.a_moan+]，虚弱地试图推开[npc2.name]，"
								+ "徒劳地哭泣着，[npc2.her][npc2.cock+]却继续顶入那[npc.breasts+]间。",

						"[npc.Name]发出[npc.a_moan+]，努力将[npc2.name]从自己身边推开，"
								+ "泪珠从[npc.her]脸颊滚下，[npc2.she]却继续在乳沟间抽插自己[npc2.cock+]。"));
				
			} else if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]发出[npc.moan+]，努力将自己[npc.breastSize][npc.breasts]从[npc2.namePos][npc2.cock+]旁移走，"
								+ "然后祈求[npc.herHim]放开自己。",

						"[npc.Name]发出[npc.a_moan+]，虚弱地试图推开[npc2.name]，"
								+ "徒劳地哭泣着，[npc2.her][npc2.cock+]却继续顶入那[npc.breastSize][npc.breasts+]间。",

						"[npc.Name]发出[npc.a_moan+]，努力将[npc2.name]从自己身边推开，"
								+ "泪珠从[npc.her]脸颊滚下，[npc2.she]却继续在小乳沟间抽插自己[npc2.cock+]。"));
						
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]发出[npc.moan+]，努力将自己的平胸拉远[npc2.namePos][npc2.cock+]，"
								+ "然后祈求[npc.herHim]放开自己。",

						"[npc.Name]发出[npc.a_moan+]，虚弱地试图推开[npc2.name]，"
								+ "徒劳地哭泣着，[npc2.her][npc2.cock+]却继续顶入平胸间。",

						"[npc.Name]发出[npc.a_moan+]，努力将[npc2.name]从自己身边推开，"
								+ "泪珠从[npc.her]脸颊滚下，[npc2.she]却继续在身上抽插自己[npc2.cock+]。"));
			}
			
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "停止提供乳交";
			} else {
				return "停止提供贫乳乳交";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "把[npc2.namePos]的[npc2.cock]从你[npc.breasts+]推远。";
			} else {
				return "把[npc2.namePos]的[npc2.cock]从你平平的胸脯推远。";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]粗暴地推开[npc2.name]，威胁性地命令[npc2.herHim]不要再操[npc.breasts+]了。",

								"[npc.Name]威胁性咆哮了一声，粗暴地推开[npc2.name]，让[npc2.herHim]不要再操[npc.breasts+]了。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]推开[npc2.name]，让[npc2.herHim]别再操[npc.breasts+]了。",

								"[npc.Name]最后[npc.moan]了一声，推开[npc2.name]，让[npc2.herHim]不要再蹭[npc.breasts+]了。"));
						break;
				}
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]粗暴地推开[npc2.name]，威胁性地命令[npc2.herHim]不要再蹭胸部了。",

								"[npc.Name]威胁性咆哮了一声，粗暴地推开[npc2.name]，让[npc2.herHim]不要再蹭胸部了。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]推开[npc2.name]，让[npc2.herHim]别再蹭胸部了。",

								"[npc.Name]最后[npc.moan]了一声，推开[npc2.name]，让[npc2.herHim]不要再蹭胸部了。"));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]乞求[npc.herHim]放过[npc2.herHim]，[npc2.moaning+]，继续奋力抵抗着[npc.herHim]。",
	
								"[npc2.name]发出[npc2.a_moan+]，乞求[npc.Name]放过自己，又虚弱地尝试着把[npc.herHim]推开，泪水从[npc2.her][npc2.eyes]涌出。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]止不住地发出[npc2.a_moan+]，背叛了自己的欲望，更多地寻求[npc.namePos]的关注。",
	
								"伴随着[npc2.a_moan+]，[npc2.name]乞求[npc.herHim]继续使用[npc2.herHim]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
