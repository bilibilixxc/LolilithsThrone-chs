package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
 * @since 0.1.79
 * @version 0.3.5
 * @author Innoxia
 */
public class TongueMouth {
	
	public static final SexAction KISS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isOrificeNonSelfOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH);
		}
		
		@Override
		public String getActionTitle() {
			return "开始接吻";
		}

		@Override
		public String getActionDescription() {
			return"你将[npc.lips]贴向[npc2.namePos]的嘴，与[npc2.herHim]亲热起来。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.COWGIRL)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.COWGIRL_REVERSE)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SITTING_IN_LAP)) {
				
				if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]俯身沉向[npc2.namePos]的[npc2.penis]，[npc.asshole+]紧紧吸纳着[npc2.penisGirth][npc2.penis+]，发出[npc.a_moan+]。"
								+"[npc.Name]俯下身去，[npc.moan]逐渐减弱，双手抓住[npc2.namePos]的头，"
									+"随后将[npc.lips+]贴向[npc2.hers]并送出一个热情的吻。",
									
							"[npc.Name]发出[npc.a_moan+]，滑向[npc2.namePos][npc2.penis+]，随后俯身并将自己压向[npc2.her]的[npc2.breasts]。"
									+"[npc.Name]闻着[npc2.namePos]的[npc2.scent+]，[npc.lips+]贴向[npc2.hers]并热情地开始吻[npc2.herHim]。",
							
							"[npc.Name]俯下身并将[npc2.herHim]拉入一个狂乱的吻，让[npc2.namePos][npc2.penis+]更深地进入自己[npc.asshole+]。",
							
							"[npc.Name]沉下身，在将[npc2.namePos]的[npc2.penis+]插入自己[npc.asshole+]时发出一声[npc.a_moan+]，"
							+"随后俯身将[npc.her][npc.lips+]贴向[npc2.hers]的。"));
					
				} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]俯身沉向[npc2.namePos]的[npc2.penis]，那[npc.vagina+]紧紧吸纳着[npc2.penisGirth][npc2.penis+]，发出[npc.a_moan+]。"
								+"[npc.Name]俯下身去，[npc.moan]逐渐减弱，双手抓住[npc2.namePos]的头，"
									+"随后将[npc.lips+]贴向[npc2.hers]并送出一个热情的吻。",
									
							"[npc.Name]发出[npc.a_moan+]，滑向[npc2.namePos][npc2.penis+]，随后俯身并将自己压向[npc2.her]的[npc2.breasts]。"
									+"[npc.Name]闻着[npc2.namePos]的[npc2.scent+]，[npc.lips+]贴向[npc2.hers]并热情地开始吻[npc2.herHim]。",
							
							"[npc.Name]让[npc2.namePos][npc2.penis+]滑入[npc.her][npc.vagina+]的更深处，随后俯下身子，将[npc2.herHim]拖入了一场狂乱的吻。",
							
							"[npc.Name]沉下身，在将[npc2.namePos]的[npc2.penis+]插入[npc.her][npc.vagina+]时发出一声[npc.a_moan+]，"
							+"随后俯身将[npc.her][npc.lips+]贴向[npc2.hers]。"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]俯下身，将[npc.her][npc.lips+]贴向[npc2.namePos]的嘴并送出一个热情的吻。",
							
							"[npc.Name]露出一抹笑容，俯身投入[npc2.namePos]的[npc2.breasts]中，呼吸着[npc2.scent]并将[npc.her][npc.lips+]贴向[npc2.hers]。",
							
							"[npc.Name]俯身向前，口中冒出[npc.a_moan+]，紧接着将[npc.her][npc.lips+]贴了上去，开启了一段饥渴的热吻。"));
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]将[npc2.her][npc2.tongue]推向[npc.namePos]的口中，"
										+"[npc2.she]将[npc2.her][npc2.lips+]贴向[npc.hers]的，饥渴地回应着[npc.namePos]的爱意。",
								
								"[npc2.name]发出[npc2.a_moan]，倾身靠向[npc.Name]，"
										+"[npc2.she]饥渴地将[npc2.her][npc2.tongue]突破了[npc.her][npc.lips+]，口中满是模糊的嗯嗯啊啊声。",
								
								"[npc2.name]愉悦地[npc2.moaning]着，倾身靠向[npc.Name]，"
										+"[npc2.she]将[npc2.her][npc2.lips+]贴向[npc.namePos]的，欣然将[npc2.her][npc2.tongue]送入对方口中。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]想要躲开，一边[npc2.sobbing]一边难受地扭动着身子，抗拒着[npc.Name]的强奸。",
								
								"[npc2.NamePos][npc2.sob+]在口中化作了一段模糊的闷响，[npc2.she]努力想要躲开，不安地晃动着身子， 抗拒着[npc.Name]的强奸。",
								
								"[npc2.name]发出[npc2.a_sob+]，徒劳地挣扎扭身，试图将[npc.Name]推开，"
										+ "即使对方全力抵抗，[npc.name]依然将[npc.tongue]强行挤过了[npc2.her][npc2.lips]，"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]开心地将[npc2.her][npc2.tongue]推向[npc.namePos]的口中，"
										+"[npc2.she]将[npc2.her][npc2.lips+]饥渴地贴向[npc.hers]的，愉快地[npc2.moaning]着，贪婪地回应着[npc.her]的爱意。",
								
								"[npc2.name]发出急切的[npc2.moan]，相互摩挲着下体，"
										+"[npc2.she]贪婪地将[npc2.her][npc2.tongue]突破了[npc.her][npc.lips+]，口中满是模糊的嗯嗯啊啊声。",
								
								"[npc2.name]愉悦地[npc2.Moaning]着，迫不及待地将[npc2.lips+]贴向[npc.namePos]的，欣然将[npc2.tongue]送入对方口中。"));
							break;
					}
				}
			
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {// Face-to-wall penetration descriptions:
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]捏住[npc2.namePos]的下巴，把[npc2.her]的头偏向一边，然后倾身向前，"
									+ "用自己[npc.lips+]贴向[npc2.hers]的，将[npc.she]拉入一个温柔的吻。",
							
							"[npc.Name]俯身紧贴住[npc2.namePos]的后背，呼吸着[npc2.her][npc2.scent+]。"
									+ "[npc.she]伸手把[npc2.namePos]头转向一边，[npc.lips+]温柔地贴向[npc2.hers]。",
							
							"[npc.Name]靠在[npc2.namePos]背上，将[npc2.her]微微转向一边，然后温柔地拉着[npc2.herHim]轻吻。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]向上抓住[npc2.namePos]的下巴，将[npc2.her]的头部拽向一边，"
									+ "接着用[npc.lips+]压住[npc2.hers]，有力地亲吻着[npc2.herHim]",

							"[npc.Name]俯身紧贴住[npc2.namePos]的后背，呼吸着[npc2.her][npc2.scent+]。"
									+ "[npc.she]伸出手，粗暴地把[npc2.namePos]头拽向一边，[npc.lips+]有力地贴住[npc2.hers]。",
											
							"[npc.Name]俯身紧贴住[npc2.namePos]的后背，把[npc2.her]头拽过来，接着粗暴地把[npc.her][npc.tongue+]插入[npc2.her]喉咙里。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]捏住[npc2.namePos]的下巴，倾身向前，"
									+ "急切地用自己[npc.lips+]贴向[npc2.hers]的，将[npc2.herHim]拉入一个热情的吻。",

							"[npc.Name]俯身紧贴住[npc2.namePos]的后背，呼吸着[npc2.her][npc2.scent+]。"
									+ "[npc.she]伸手把[npc2.namePos]头转向一边，热情地将[npc.lips+]贴向[npc2.hers]。",
									
							"[npc.Name]靠在[npc2.namePos]背上，将[npc2.her]微微转向一边，然后将[npc2.name]拉入了一场深沉的热吻。"));
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]将[npc2.her][npc2.tongue]推向[npc.name]的口中，"
										+"[npc2.she]将[npc2.her][npc2.lips+]牢牢贴向[npc.hers]的，饥渴地回应着[npc.her]的爱意。",
								
								"[npc2.name]发出[npc2.a_moan]，向后靠向[npc.Name]，"
										+"[npc2.she]饥渴地将[npc2.her][npc2.tongue]突破了[npc.her][npc.lips+]，口中满是模糊的嗯嗯啊啊声。",
								
								"[npc2.name]愉悦地[npc2.moaning]着，倾身靠向[npc.Name]，"
										+"[npc2.she]将[npc2.her][npc2.lips+]贴向[npc.hers]，欣然将[npc2.her][npc2.tongue]送入对方口中。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]想要躲开，一边[npc2.sobbing]一边难受地扭动着身子，抗拒着[npc.Name]的强奸。",
								
								"[npc2.NamePos][npc2.sob+]在口中化作了一段模糊的闷响，[npc2.she]努力想要躲开，不安地晃动着身子， 抗拒着[npc.Name]的强奸。",
								
								"[npc2.name]发出[npc2.a_sob+]，徒劳地挣扎扭身，试图将[npc.Name]推开，"
										+ "即使对方全力抵抗，[npc.name]依然将[npc.tongue]强行挤过了[npc2.her][npc2.lips]。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]开心地将[npc2.her][npc2.tongue]推向[npc.namePos]的口中，"
										+"[npc2.she]将[npc2.her][npc2.lips+]饥渴地贴向[npc.hers]的，愉快地[npc2.moaning]着，贪婪地回应着[npc.her]的爱意。",
								
								"[npc2.name]发出急切的[npc2.moan]，相互摩挲着下体，"
										+"[npc2.she]贪婪地将[npc2.her][npc2.tongue]突破了[npc.her][npc.lips+]，口中满是模糊的嗯嗯啊啊声。",
							
								"[npc2.name]愉悦[npc2.Moaning]，拼命磨蹭着[npc.name]，"
										+"[npc2.she]迫不及待地将[npc2.her][npc2.lips+]牢牢贴向[npc.hers]的，欣然将[npc2.tongue]送入对方口中。"));
							break;
					}
				}
				
			} else { // Default penetration descriptions:
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]凝望着[npc2.namePos][npc2.eyes]，俯身将[npc.her][npc.lips+]抵在[npc2.hers]上，开始温柔地亲吻[npc2.herHim]。",
							
							"[npc.Name]倚靠在[npc2.name]身上，嗅着[npc2.her][npc2.scent+]，温柔地将[npc.lips+]贴向[npc2.her]的。",
							
							"[npc.Name]靠在[npc2.namePos][npc2.breasts+]上，将[npc2.her]微微转向一边，然后温柔地拉着[npc2.herHim]爱意地吻。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]凝望着[npc2.namePos][npc2.eyes]，俯身将[npc.her][npc.lips+]抵在[npc2.hers]上，开始热情地亲吻[npc2.herHim]。",
							
							"[npc.Name]倚靠在[npc2.name]身上，嗅着[npc2.her][npc2.scent+]，急切地将[npc.lips+]贴向[npc2.her]的。",
							
							"[npc.Name]靠在[npc2.namePos][npc2.breasts+]前，把头微微转向一边，然后急切地将[npc2.name]拉入了一场深沉的热吻。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]凝望着[npc2.namePos][npc2.eyes]，俯身粗暴地将[npc.her][npc.lips+]压在[npc2.hers]上，开始有力地亲吻[npc2.herHim]。",
							
							"[npc.Name]倚靠在[npc2.name]身上，嗅着[npc2.her][npc2.scent+]，粗暴地将[npc.lips+]贴向[npc2.her]的。",
							
							"[npc.Name]靠在[npc2.namePos][npc2.breasts+]上，将[npc2.her]微微转向一边，然后粗暴地拉着[npc2.herHim]粗暴地吻。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]凝望着[npc2.namePos][npc2.eyes]，迫不及待地俯下身将[npc.her][npc.lips+]抵在[npc2.hers]上，送出一个热情的吻。",
							
							"[npc.Name]倚靠在[npc2.name]身上，嗅着[npc2.her][npc2.scent+]，急切地将[npc.lips+]贴向[npc2.her]的。",
							
							"[npc.Name]靠在[npc2.namePos][npc2.breasts+]前，把头微微转向一边，然后急切地将[npc2.name]拉入了一场深沉的热吻。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]凝望着[npc2.namePos][npc2.eyes]，俯下身将[npc.her][npc.lips+]抵在[npc2.hers]上，送出一个热情的吻。",
							
							"[npc.Name]倚靠在[npc2.name]身上，嗅着[npc2.her][npc2.scent+]，愉悦地将[npc.lips+]贴向[npc2.her]的。",
							
							"[npc.Name]靠在[npc2.namePos][npc2.breasts+]前，把头微微转向一边，然后急切地将[npc2.name]拉入了一场深沉的湿吻。"));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]将[npc2.her][npc2.tongue]慢慢地推向[npc.namePos]的口中，"
										+ "伸出手温柔地爱抚着[npc.her][npc.face]，[npc2.she]也欣然回应着[npc.her]的爱意。",
								
								"[npc2.Name]发出哼声，倾身靠向[npc.name]，"
										+ "[npc2.her]温柔地将[npc2.tongue]送入[npc.her]口中，模糊了[npc.her][npc.moans]。",
								
								"[npc2.name]赞许地[npc2.moaning]着，倾身靠向[npc.Name]，"
										+"[npc2.she]将[npc2.her][npc2.lips+]温柔地贴向[npc.hers]，缓慢地将[npc2.her][npc2.tongue]送入对方口中。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]将[npc2.her][npc2.tongue]推向[npc.namePos]的口中，"
										+"[npc2.she]将[npc2.her][npc2.lips+]贪婪地贴向[npc.hers]的，饥渴地回应着[npc.her]的爱意。",
								
								"[npc2.name]发出一声赞许的[npc2.moan]，倾身靠向[npc.name]，"
										+ "[npc2.her]急切地将[npc2.tongue]推入[npc.her]口中，模糊了[npc.her][npc.moans]。",
										
								"[npc2.name]赞许地[npc2.moaning]着，倾身靠向[npc.Name]，"
										+"[npc2.she]将[npc2.her][npc2.lips+]有力地贴向[npc.hers]，急切地将[npc2.her][npc2.tongue]送入对方口中。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]强硬地将[npc2.her][npc2.tongue]推入[npc.namePos]的口中，"
										+"[npc2.she]将[npc2.her][npc2.lips+]粗暴地贴向[npc.hers]的，贪婪地回应着[npc.her]的爱意。",
								
								"[npc2.name]发出赞许的[npc2.moan]，相互激烈地摩挲着，"
										+ "[npc2.her]急切地将[npc2.tongue]推入[npc.her]口中，[npc2.her][npc2.lips+]模糊了[npc.her][npc.moans]。",
								
								"[npc2.name]发出赞许的[npc2.Moaning]，磨蹭着[npc.name]，"
										+"[npc2.she]强硬地将[npc2.her][npc2.lips+]贴向[npc.hers]，粗暴地将[npc2.her][npc2.tongue]送入对方口中。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]开心地将[npc2.her][npc2.tongue]推向[npc.namePos]的口中，"
										+"[npc2.she]将[npc2.her][npc2.lips+]贪婪地贴向[npc.hers]的，愉快地[npc2.moaning]着，贪婪地回应着[npc.her]的爱意。",
								
								"[npc2.name]发出急切的[npc2.moan]，拼命磨蹭着[npc.name]，"
										+"[npc2.she]贪婪地将[npc2.her][npc2.tongue]突破了[npc.her][npc.lips+]，口中满是模糊的嗯嗯啊啊声。",
								
								"[npc2.name]愉悦[npc2.Moaning]，拼命磨蹭着[npc.name]，"
										+"[npc2.she]将[npc2.her][npc2.lips+]牢牢贴向[npc.hers]，欣然将[npc2.her][npc2.tongue]伸入对方口中。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]将[npc2.her][npc2.tongue]推向[npc.namePos]的口中，"
										+"[npc2.she]将[npc2.her][npc2.lips+]贴向[npc.hers]的，饥渴地回应着[npc.her]的爱意。",
								
								"[npc2.name]发出一声[npc2.a_moan]，倾身靠向[npc.Name]，"
										+"[npc2.she]饥渴地将[npc2.her][npc2.tongue]突破了[npc.her][npc.lips+]，口中满是模糊的嗯嗯啊啊声。",
								
								"[npc2.name]愉悦地[npc2.moaning]着，倾身靠向[npc.Name]，"
										+"[npc2.she]将[npc2.her][npc2.lips+]贴向[npc.hers]，欣然将[npc2.her][npc2.tongue]送入对方口中。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]想要躲开，一边[npc2.sobbing]一边难受地扭动着身子，抗拒着[npc.Name]的强奸。",
								
								"[npc2.NamePos][npc2.sob+]在口中化作了一段模糊的闷响，[npc2.she]努力想要躲开，不安地晃动着身子， 抗拒着[npc.Name]的强奸。",
								
								" 伴随着[npc2.a_sob+]，[npc2.she]试图从[npc.name]身边挣脱出去，但这也只是徒劳的反抗，"
										+ "即使对方全力抵抗，[npc.name]依然用[npc.her][npc.tongue]推开了[npc2.her][npc2.lips]中"));
							break;
						default:
							break;
					}
				}
			
			}
			
			//TODO describe tongue modifiers
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
					return(UtilText.returnStringAtRandom(
						"[npc2.name]发出迫不及待的[npc2.moan]作为回应，"
								+"[npc2.she]亢奋地将[npc2.her][npc2.lips+]贴向[npc.hers]的，饥渴地回应着[npc.her]的爱意。",
						
						"伴随着欢愉的[npc2.a_moan]，[npc2.name]也将身子尽量靠上去，急切地将[npc2.tongue]突破了[npc.her][npc.lips+]，狂乱地亲吻着。",
					
						"[npc2.name]紧贴着[npc.name]，发出愉悦的[npc2.a_moan]，"
								+ "[npc2.she]也用[npc2.her][npc2.lips+]作回应，热情地摩擦着[npc.her]的嘴唇，"));
				case SUB_RESISTING:
					return(UtilText.returnStringAtRandom(
						"[npc2.name]发出[npc2.a_sob+]作为回应，在[npc2.she]抗拒地扭动时，尽力尝试着将[npc.Name]推开。",
						
						"[npc2.name]发出一阵[npc2.a_sob+]，徒劳地挣扎着，试图将[npc.name]推开，但[npc.she]仍然在[npc2.her]紧闭的[npc2.lips]上留下了一连串温柔的吻。",
						
						"[npc2.Name]拼命地试图挣脱，尝试推开[npc.Name]，但[npc.Name]继续猥亵着[npc2.herHim]，"
								+ "每当[npc2.she]感受到[npc.namePos]令人厌恶的亲吻，就不禁发出一声低沉的[npc2.sob]。"));
				case DOM_GENTLE:
					return(UtilText.returnStringAtRandom(
						"[npc2.name]发出轻柔的[npc2.moan]作为回应，柔和地亲吻着[npc.namePos][npc.lips+]，温柔地把[npc2.tongue]送入[npc.her]嘴里。",
						
						"[npc2.name]发出轻柔的[npc2.moan]，俯身贴向[npc.name]，温柔地用[npc2.her][npc2.tongue]推开[npc.her][npc.lips+]，展现着[npc.herHis]爱意。",
						
						"[npc2.name]压住[npc.name]，发出一声轻柔的[npc2.moan]，"
								+"[npc2.she]慢慢地将[npc2.her][npc2.tongue]送入[npc.her]口中，发出模糊的嗯嗯啊啊声。"));
				case DOM_ROUGH:
					return(UtilText.returnStringAtRandom(
						"[npc2.name]低吼着，用[npc2.her][npc2.lips]粗暴地摩擦[npc.her]的嘴唇，将舌头深深插入[npc.her]的喉咙。",
						
						"[npc.Name]威胁性咆哮了一声，粗暴地磨蹭着[npc.name]，"
								+ "[npc2.she]只在乎自己的快乐，贪婪地将[npc2.tongue]深入[npc.her]喉咙。",
						
						"[npc2.name]咧嘴一笑回应[npc.namePos]的示爱，激烈地摩擦[npc2.her][npc2.lips]，发出一阵粗暴地咆哮，"
								+ "[npc2.she]贪婪地用舌头操着[npc.her]喉咙，在其嘴里发出一阵[npc2.moaning]。"));
				default:
					return(UtilText.returnStringAtRandom(
						"作为回应，[npc2.Name]的口中漏出了[npc2.moan]，"
								+ "[npc2.her]将自己推向[npc.Name]，开心地将[npc2.tongue]伸进[npc.her]嘴里作为其示爱的回应。",
						
						"随着一阵[npc2.a_moan]，[npc2.name]倾入[npc.Name]，将[npc2.tongue]滑过[npc.her]的[npc.lips]，与[npc.herHim]坠入激情的吻。",
						
						"[npc2.name]发出一阵[npc2.a_moan]，倾入[npc.Name]，将[npc2.her][npc2.lips+]压向[npc.her]的嘴，回以一吻。"));
			}
		}
		return "";
	}
	
	public static final SexAction KISS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "接吻(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "与[npc2.name]接吻(温柔)。";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]依然靠在[npc2.namePos]背上，温柔地将[npc.her][npc.lips+]压向[npc2.her]，在其嘴上留下一串柔软的吻。",
					
					"[npc.Name]温柔地靠在[npc2.namePos]的背上，呼吸着[npc2.her]的[npc2.scent]，在[npc2.her][npc2.lips+]留下一串柔软的吻。",
					
					"[npc.Name]缓慢地靠在[npc2.namePos]的背上，温柔地将[npc2.herHim]固定靠墙，倾在[npc2.her]的肩膀，温柔地亲吻着[npc2.her][npc2.lips+]。"));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]温柔地将[npc.her][npc.lips+]压向[npc2.nameHers]，给予[npc2.her]的嘴一长串轻柔的吻。",
					
					"[npc.Name]温柔地靠在[npc2.namePos]的背上，呼吸着[npc2.her][npc2.scent+]，在[npc2.her][npc2.lips+]留下一串柔软的吻。",
					
					"[npc.Name]温柔地将自己压向[npc2.namePos][npc2.breasts+]，头倾向一遍，温柔地吻着[npc2.her][npc2.lips+]。"));
			
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "亲吻[npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "继续与[npc2.name]接吻。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]依然靠在[npc2.namePos]背上，饥渴地将[npc.her][npc.lips+]压向[npc2.her]，在其嘴上留下一串激情的吻。",
					
					"[npc.Name]饥渴地靠在[npc2.namePos]的背上，呼吸着[npc2.her]的[npc2.scent]，在[npc2.her][npc2.lips+]留下一串激情的吻。",
					
					"[npc.Name]饥渴地靠在[npc2.namePos]的背上，将[npc2.herHim]固定靠墙，倾在[npc2.her]的肩膀，热情地亲吻着[npc2.her][npc2.lips+]。"));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]饥渴地将[npc.her][npc.lips+]压向[npc2.nameHers]，给予[npc2.her]的嘴一长串热情的吻。",
					
					"[npc.Name]饥渴地靠在[npc2.namePos]的背上，呼吸着[npc2.her][npc2.scent+]，在[npc2.her][npc2.lips+]留下一串柔软的吻。",
					
					"[npc.Name]饥渴地靠在[npc2.namePos]背上，将[npc2.her]微微转向一边，然后热情地将[npc2.name]拉入了一场深沉的热吻。"));
			
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "接吻(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "与[npc2.name]接吻(粗暴)。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]粗暴地摩擦[npc2.namePos]的后背，激烈地将自己[npc.lips+]压向[npc2.her]，贪婪地将[npc.tongue]深入[npc2.her]的喉咙。",
					
					"[npc.Name]粗暴地靠在[npc2.namePos]的背上，呼吸着[npc2.her]的[npc2.scent]，在[npc2.her][npc2.lips+]留下一串激情的吻。",
					
					"[npc.Name]粗暴地压在[npc2.namePos]背上，"
							+ "[npc.she]激烈地将[npc2.herHim]靠在墙上，靠在[npc2.her]的肩膀上，将自己[npc.tongue+]深入[npc2.her]喉咙。"));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]激烈地将自己[npc.lips+]压向[npc2.nameHers]，贪婪地将[npc.tongue]深入[npc2.her]喉咙。",
					
					"[npc.Name]粗暴地磨蹭[npc2.name]，嗅着[npc2.her][npc2.scent+]，贪婪地用舌头操着[npc2.her]的嘴。",
					
					"[npc.Name]粗暴地将自己压向[npc2.namePos][npc2.breasts+]，将[npc.tongue]深入[npc2.her]的喉咙。"));
			
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction KISS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "亲吻[npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "继续与[npc2.name]接吻。";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]依然靠在[npc2.namePos]背上，将[npc.her][npc.lips+]压向[npc2.her]，在其嘴上留下一串激情的吻。",
					
					"[npc.Name]靠在[npc2.namePos]的背上，呼吸着[npc2.her]的[npc2.scent]，在[npc2.her][npc2.lips+]留下一串激情的吻。",
					
					"[npc.Name]靠在[npc2.namePos]的背上，将[npc2.herHim]固定靠墙，倾在[npc2.her]的肩膀，热情地亲吻着[npc2.her][npc2.lips+]。"));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]将自己[npc.lips+]压向[npc2.nameHers]，在其嘴上留下一连串激情的吻。",
					
					"[npc.Name]靠在[npc2.namePos]的背上，呼吸着[npc2.her][npc2.scent+]，在[npc2.her][npc2.lips+]留下一串柔软的吻。",
					
					"[npc.Name]将自己压向[npc2.namePos][npc2.breasts+]，头倾向一遍，热情地吻着[npc2.her][npc2.lips+]。"));
			
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "接吻(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "渴求地与[npc2.name]接吻。";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]依然靠在[npc2.namePos]背上，饥渴地将[npc.her][npc.lips+]压向[npc2.her]，在其嘴上留下一串激情的吻。",
					
					"[npc.Name]饥渴地靠在[npc2.namePos]的背上，呼吸着[npc2.her]的[npc2.scent]，在[npc2.her][npc2.lips+]留下一串激情的吻。",
					
					"[npc.Name]饥渴地靠在[npc2.namePos]的背上，将[npc2.herHim]固定靠墙，倾在[npc2.her]的肩膀，热情地亲吻着[npc2.her][npc2.lips+]。"));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]饥渴地将[npc.her][npc.lips+]压向[npc2.nameHers]，给予[npc2.her]的嘴一长串热情的吻。",
					
					"[npc.Name]饥渴地靠在[npc2.namePos]的背上，呼吸着[npc2.her][npc2.scent+]，在[npc2.her][npc2.lips+]留下一串柔软的吻。",
					
					"[npc.Name]饥渴地靠在[npc2.namePos]背上，将[npc2.her]微微转向一边，然后热情地将[npc2.name]拉入了一场深沉的热吻。"));
			
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抗拒接吻";
		}

		@Override
		public String getActionDescription() {
			return "抗拒与[npc2.namePos]接吻。";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"泪水在[npc.Name]的眼眶中打转，发出一阵[npc.a_sob+]，[npc.her]尝试把[npc2.name]强迫地吻推开。",
					
					"[npc2.namePos][npc2.scent+]摧垮了[npc.Name]的感官，[npc.her]低声[npc.sob]，"
							+ "[npc.herHim]急切地推开[npc2.herHim]，但徒劳无功，[npc2.her]依然袭击着[npc.her]的嘴。",
					
					"[npc.Name]急切地试图推开[npc2.name]，但[npc2.she]继续亲吻着[npc.Sobbing]中的[npc.Name]。"));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name]发出一阵抚慰的[npc2.moan]，无视[npc.namePos]的抵抗，温柔地吻着[npc.her][npc.lips+]，尝试让其冷静。",
						
						"[npc2.name]发出一声柔软的[npc2.a_moan]，倾身靠向[npc.Name]，"
								+ "[npc2.she]无视[npc.her]的[npc.sobs]，温柔但是决绝地将[npc2.tongue]滑过[npc.her]不情愿的[npc.lips]，深入[npc.her]的嘴中。",
						
						"[npc2.name]发出一阵抚慰的[npc2.moan]，将自己压向[npc.Name]，"
								+ "[npc2.she]用自己[npc2.lips+]压抑着[npc.her]的[npc.sobs]，继续吻着[npc.herHim]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"作为[npc.her]抵抗的回应，[npc2.Name]的口中漏出一阵愤怒的咆哮，"
								+ "[npc.her]粗暴地用嘴摩擦[npc2.her]的[npc2.lips]，粗暴地将舌头深入其喉咙。",
						
						"随着一阵愤怒的咆哮，[npc2.name]粗暴地摩擦着[npc.Name]，无视[npc.her]的[npc.sobs]，将[npc2.tongue]深入[npc.her]的喉咙。",
						
						"[npc2.name]无视[npc.her]的抵抗，继续粗暴地用舌头操着[npc.her]不情愿的喉咙，发出一阵粗糙的咆哮。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]发出一阵[npc2.a_moan]，无视[npc.namePos]的抗议，继续在其[npc.lips+]留下一串激情的吻。",
						
						"[npc2.name]发出一声[npc2.a_moan]，倾身靠向[npc.name]，"
								+ "[npc2.she]无视[npc.her]的[npc.sobs]，坚决地将[npc2.tongue]滑过[npc.her]不情愿的[npc.lips]，深入[npc.her]的嘴中。",
						
						"[npc2.name]发出一阵[npc2.a_moan+]，将自己压向[npc.Name]，继续热情地吻[npc.herHim]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止接吻";
		}

		@Override
		public String getActionDescription() {
			return "推开[npc2.namePos]，停止与[npc2.herHim]接吻。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]向后一退，推开[npc2.name]，结束了[npc.her]的吻。",
						
						"[npc.Name]突然粗暴地推开[npc2.name]，结束亲吻。",
						
						"[npc.Name]退后一步，粗暴地推开[npc2.herHim]，打断了[npc.her]的吻。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]凝视着[npc2.namePos]的[npc2.eyes]，被推开时咧嘴一笑。",
						
						"[npc.Name]突然后退，结束了[npc.her]的吻。",
						
						"[npc.Name]推开[npc2.name]，移开自己[npc.lips+]，打断[npc.her]的吻。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name]叹了口气，很快继续[npc2.sob]并挣扎着抵抗[npc.Name]，试图摆脱舒服。",
							
							"[npc2.name]发出一阵[npc2.a_sob+]，徒劳地挣扎着，试图将[npc.Name]推开，[npc2.she]不安地反抗扭身，但[npc.name]依然牢牢地将[npc2.she]固定在原位。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]向后退去，[npc2.name]发出了一小声哀鸣，似乎是在表示自己还想要更多“照顾”。",
							
							"一阵狂乱的哀鸣从[npc2.namePos]的[npc2.lips]间流出，渴望着[npc.namePos]更多的关注。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
