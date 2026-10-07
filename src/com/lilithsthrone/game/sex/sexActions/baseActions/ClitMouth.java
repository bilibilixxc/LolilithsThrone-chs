package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4
 * @author Innoxia
 */
public class ClitMouth {
	
	public static final SexAction FORCE_SUCK_CLIT = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public Map<SexAreaInterface, SexAreaInterface> getSexAreaInteractions() {
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
			} else {
				return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH));
			}
		}
		@Override
		public SexActionType getActionType(){
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE).contains(Main.sex.getCharacterTargetedForSexAction(this))
					|| Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return SexActionType.ONGOING;
			} else {
				return SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED;
			}
		}
		@Override
		public String getActionTitle() {
			return "关照阴蒂";
		}
		@Override
		public String getActionDescription() {
			return "[npc2.name]将会亲吻并吮吸你的阴蒂。";
		}
		@Override
		public String getDescription() {
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							"[npc.name]移动[npc.her][npc.hips]，重新摆正[npc.herself]，轻轻地用[npc.her][npc.clit+]磨蹭着[npc2.namePos][npc2.lips+]。",
							
							"随着[npc.her][npc.hips]的快速移动，[npc.name]发出一声轻柔的[npc.moan]。[npc.she]轻轻地将[npc.her][npc.clit+]压向[npc2.namePos][npc2.tongue+]。",
							
							"将[npc.her][npc.pussy+]轻轻地压在[npc2.namePos]的嘴唇，"
									+ "[npc.name]重新摆正[npc.herself]以便[npc.sheIs]将[npc.her][npc.clit+]向下对准[npc2.namePos][npc2.lips+]摩擦。");

				case SUB_EAGER:
				case DOM_NORMAL:
					return UtilText.returnStringAtRandom(
							"[npc.name]移动[npc.her]的[npc.hips]，重新摆正[npc.herself]，急切地用[npc.her][npc.clit+]磨蹭着[npc2.namePos][npc2.lips+]。",
							
							"随着[npc.her][npc.hips]的快速移动，[npc.name]发出[npc.a_moan+]。[npc.she]贪婪地将[npc.her][npc.clit+]压向[npc2.namePos][npc2.tongue+]。",
							
							"将[npc.her][npc.pussy+]急切地压在[npc2.namePos]的嘴唇，"
									+ "[npc.name]重新摆正[npc.herself]以便[npc.sheIs]将[npc.her][npc.clit+]向下对准[npc2.namePos][npc2.lips+]摩擦。");
					
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							"[npc.name]移动[npc.her]的[npc.hips]，重新摆正[npc.herself]，粗鲁地用[npc.her][npc.clit+]磨蹭着[npc2.namePos][npc2.lips+]。",
							
							"随着[npc.her][npc.hips]的快速移动，[npc.name]发出[npc.a_moan+]。[npc.she]粗鲁地将[npc.her][npc.clit+]压向[npc2.namePos][npc2.tongue+]。",
							
							"将[npc.her][npc.pussy+]激烈地压在[npc2.namePos]的嘴唇，"
									+ "[npc.name]重新摆正[npc.herself]以便[npc.sheIs]粗鲁地将[npc.her][npc.clit+]向下对准[npc2.namePos][npc2.lips+]摩擦。");
					
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							"[npc.name]移动[npc.her]的[npc.hips]，重新摆正[npc.herself]，用[npc.her][npc.clit+]磨蹭着[npc2.namePos][npc2.lips+]。",
							
							"随着[npc.her][npc.hips]的快速移动，[npc.name]发出[npc.a_moan+]。[npc.she]将[npc.her][npc.clit+]压向[npc2.namePos][npc2.tongue+]。",
							
							"将[npc.her][npc.pussy+]压在[npc2.namePos]的嘴唇，"
									+ "[npc.name]重新摆正[npc.herself]以便[npc.sheIs]将[npc.her][npc.clit+]向下对准[npc2.namePos][npc2.lips+]摩擦。");
					
				default:
					break;
			}
			
			return "";
		}
	};
	
	public static final SexAction SUCK_CLIT = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public Map<SexAreaInterface, SexAreaInterface> getSexAreaInteractions() {
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
			} else {
				return Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT));
			}
		}
		@Override
		public SexActionType getActionType(){
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
					|| Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return SexActionType.ONGOING;
			} else {
				return SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED;
			}
		}
		@Override
		public String getActionTitle() {
			return "吸吮阴蒂";
		}
		@Override
		public String getActionDescription() {
			return "吸吮[npc2.namePos][npc2.clit+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]慢慢地让[npc.tongue]滑过[npc2.namePos][npc2.pussy+]，将[npc.her][npc.lips+]抵住[npc2.her][npc2.clit+]，然后开始轻轻地吮吸和亲吻它。",

							"[npc.name]将[npc.tongue]缓慢游移过[npc2.namePos][npc2.clit+]，"
									+ "[npc.she]让[npc.her][npc.lips+]压过来，开始轻轻地亲吻并吮吸[npc2.her][npc2.clit+]。",

							"[npc.name]轻柔地亲吻并舔舐着[npc2.namePos][npc2.pussy+]，慢慢地来到[npc2.her][npc2.clit+]，"
									+ "并用[npc.tongue+]进行一系列轻柔的舔舐。[npc.she]开始关注于取悦[npc2.her][npc2.clit+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]急切地让[npc.tongue]滑过[npc2.namePos][npc2.pussy+]，将[npc.her][npc.lips+]抵住[npc2.her][npc2.clit+]，然后开始轻轻地吮吸和亲吻它。",

							"[npc.name]持久地舔弄着，濡湿了一大片，[npc.her]将[npc.tongue]向上游移，舐过[npc2.namePos][npc2.clit+]，"
									+ "[npc.she]让[npc.her][npc.lips+]压过来，开始急切地亲吻并吮吸[npc2.her][npc2.clit+]。",

							"[npc.name]急切地亲吻并舔舐着[npc2.namePos][npc2.pussy+]，慢慢地来到[npc2.her][npc2.clit+]，"
									+ "用[npc.tongue+]舔湿一大片，又开始取悦[npc2.her][npc2.clit+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"粗暴地用[npc.tongue+]拉扯着[npc2.namePos][npc2.pussy+]，"
									+ "[npc.name]用力地让[npc.her][npc.lips+]抵着[npc2.her][npc2.clit+]，然后开始霸道地吮吸和亲吻它。",

							"[npc.name]用一个粗暴而潮湿的舔舐让[npc.tongue]向上滑过[npc2.namePos][npc2.clit+]，"
									+ "[npc.she]让[npc.her][npc.lips+]压过来，开始用力地亲吻并吮吸[npc2.her][npc2.clit+]。",

							"[npc.name]粗暴地亲吻并舔舐着[npc2.namePos][npc2.pussy+]，慢慢地来到[npc2.her][npc2.clit+]，"
									+ "用[npc.tongue+]贪婪地舔湿一大片，又开始取悦[npc2.her][npc2.clit+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]急切地让[npc.tongue]滑过[npc2.namePos][npc2.pussy+]，将[npc.her][npc.lips+]抵住[npc2.her][npc2.clit+]，然后开始轻轻地吮吸和亲吻它。",

							"[npc.name]持久地舔弄着，濡湿了一大片，[npc.her]将[npc.tongue]向上游移，舐过[npc2.namePos][npc2.clit+]，"
									+ "[npc.she]让[npc.her][npc.lips+]压过来，开始急切地亲吻并吮吸[npc2.her][npc2.clit+]。",

							"[npc.name]急切地亲吻并舔舐着[npc2.namePos][npc2.pussy+]，慢慢地来到[npc2.her][npc2.clit+]，"
									+ "用[npc.tongue+]舔湿一大片，又开始取悦[npc2.her][npc2.clit+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]让[npc.tongue]滑过[npc2.namePos][npc2.pussy+]，将[npc.her][npc.lips+]抵住[npc2.her][npc2.clit+]，然后开始轻轻地吮吸和亲吻它。",

							"[npc.name]持久地舔弄着，濡湿了一大片，[npc.her]将[npc.tongue]向上游移，舐过[npc2.namePos][npc2.clit+]，"
									+ "[npc.she]让[npc.her][npc.lips+]压过来，开始亲吻并吮吸[npc2.her][npc2.clit+]。",

							"[npc.name]亲吻并舔舐着[npc2.namePos][npc2.pussy+]，慢慢地来到[npc2.her][npc2.clit+]，"
									+ "用[npc.tongue+]舔湿一大片，又开始取悦[npc2.her][npc2.clit+]。"));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一声柔和的[npc2.moan]，温柔地将[npc2.hips]压向[npc.namePos]的[npc.face]，高声呼喊让对方继续做下去。",
	
								"一声颤抖的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘出，"
										+ "并轻柔地让[npc2.her]的[npc2.pussy]抵向[npc.namePos]的[npc.face]。[npc2.she]向[npc.herHim]恳求着不要停。",
	
								"[npc2.her]轻柔地抬起[npc2.hips]放到[npc.namePos]的[npc.face]前。[npc2.name]轻轻地[npc2.moan]着，想要乞求[npc.herHim]继续。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]，急切地抬起[npc2.her]的[npc2.hips]来到[npc.namePos]的[npc.face]前，[npc2.she]哭着求[npc.herHim]继续。",
	
								"一声颤抖的[npc2.moan]从[npc2.namePos][npc2.lips+]逸出，"
										+ "然后饥渴地将[npc2.her]的[npc2.pussy]压到[npc.namePos][npc.face]上，[npc2.she]求着[npc.herHim]不要停。",
	
								"[npc2.name]急切地把[npc2.hips]压向[npc.namePos]的[npc.face]，发出[npc2.a_moan+]，恳求[npc.name]继续。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]，粗鲁地将[npc2.her]的[npc2.hips]向着[npc.namePos]的[npc.face]碾去，命令[npc.name]继续。",
	
								"一声颤抖的[npc2.moan]从[npc2.namePos][npc2.lips+]逸出，"
										+ "[npc2.she]粗鲁地将[npc2.pussy]碾向[npc.namePos]的[npc.face]，命令[npc.name]不要停。",
	
								"[npc2.her]将[npc2.hips]粗鲁地碾向[npc.namePos]的[npc.face]，[npc2.name]发出一声[npc2.a_moan+]，要求[npc.herHim]继续。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]，急切地抬起[npc2.her]的[npc2.hips]来到[npc.namePos]的[npc.face]前，[npc2.she]哭着求[npc.herHim]继续。",
	
								"一声颤抖的[npc2.moan]从[npc2.namePos][npc2.lips+]逸出，"
										+ "然后饥渴地将[npc2.her]的[npc2.pussy]压到[npc.namePos][npc.face]上，[npc2.she]求着[npc.herHim]不要停。",
	
								"[npc2.name]急切地把[npc2.hips]压向[npc.namePos]的[npc.face]，发出[npc2.a_moan+]，恳求[npc.name]继续。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]，抬高[npc2.her]的[npc2.hips]来到[npc.namePos][npc.face]前，[npc2.she]哭着求[npc.herHim]继续。",
	
								"一声颤抖的[npc2.moan]从[npc2.namePos][npc2.lips+]逸出，"
										+ "然后，[npc2.her]将[npc2.pussy]压向[npc.namePos]的[npc.face]，[npc2.she]乞求[npc.herHim]不要停。",
	
								"[npc2.her]抬高[npc2.hips]压向[npc.namePos]的[npc.face]，[npc2.name]发出[npc2.a_moan+]，恳求[npc.herHim]继续。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]拼命地试图将[npc2.her]的[npc2.pussy]推离[npc.namePos]的[npc.face]，"
										+ "[npc2.she]发出一阵[npc2.a_sob+]，恳求[npc.name]就这样放过[npc2.herHim]。",
	
								"[npc2.her]泪流满面，[npc2.name]拼命抵抗着[npc.Name]，"
										+ "[npc2.she]哭得更大声了，试图将[npc2.her]的[npc2.pussy]远离入侵者的[npc.tongue]",
	
								"[npc2.name]啜泣着，[npc2.eyes]中蓄满泪水。[npc2.her]乞求[npc.Name]放过自己，"
										+ "每次[npc2.she]感觉[npc.namePos][npc.tongue+]舔过[npc2.her][npc2.clit+]时，都会疯狂地试图将[npc2.her]的[npc2.hips]拉回来。"));
						break;
					default:
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Ongoing penetrative actions:
	

	public static final SexAction TWINTAIL_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "拽住双马尾";
			} else {
				return "拽住双麻花辫";
			}
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "抓住[npc2.namePos]的双马尾，把[npc2.her]摁在你[npc.clit+]上。";
			} else {
				return "抓住[npc2.namePos]的双麻花辫，把[npc2.her]摁在你[npc.clit+]上。";
			}
		}
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HAIR)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& (Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS || Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_BRAIDS)
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairLength().isSuitableForPulling()
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairType().isAbleToBeGrabbedInSex();
		}
		@Override
		public String getDescription() {
			
			String style = Main.sex.getCharacterTargetedForSexAction(this).getHairStyle().getName(Main.sex.getCharacterTargetedForSexAction(this));
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]握住[npc2.namePos]的"+style+"，温柔地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her][npc.clit+]直到底部。",
							"[npc.Name]俯下身轻柔但坚定地抓住[npc2.namePos]的每一个"+style+"，缓缓将[npc2.herHim]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.clit+]深喉。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos]的"+style+"，"
									+ "然后将[npc2.herHim]轻轻向前拉，让[npc.her][npc.clit+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her]肉结":"根部")+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗鲁地抓住[npc2.namePos]的"+style+"，残暴地将[npc2.her]的头部拉扯过来，强迫[npc2.herHim]把[npc.her][npc.clit+]从根部全都吞下。",
							"[npc.Name]向下抓住[npc2.namePos]的"+style+"，无情地将[npc2.her]的头部拽向自己的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.clit+]深喉。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰抓住[npc2.namePos]的"+style+"，"
									+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.her][npc.clit+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]握住[npc2.namePos]的"+style+"，紧紧地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her][npc.clit+]直到底部。",
							"[npc.Name]俯下身紧紧地抓住[npc2.namePos]的每一个"+style+"，坚定地将[npc2.herHim]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.clit+]深喉。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos]的"+style+"，"
									+ "然后将[npc2.herHim]坚定地向前拉，让[npc.her][npc.clit+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her]肉结":"根部")+"."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.her]的[npc2.tongue]轻轻摩挲着[npc.namePos][npc.clit]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.clit+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，用[npc2.her]的[npc2.tongue]轻轻摩挲着[npc.namePos][npc.clit]的下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.clit+]最终从[npc2.her]喉咙中拔出。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]垂下眸子，忍受了一小会[npc.namePos]的大胆举动，"
										+ "然后把[npc2.her]的头扭过来，厉声提醒[npc.herHim]谁才是主导者。",
								"[npc2.namePos]威胁似的低吼着，那震动的喉咙却同时带来了额外的快感，"
										+ "尽管如此，[npc.name]还是被吓得立即松开[npc2.hair(true)]，将自己[npc.clit+]从[npc2.her]的喉咙拔出来。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"蜷缩着的[npc2.her]合上[npc2.eyes]，[npc2.name]徒劳地抵抗着，拍打#IF(npc.isPlayer())你的#ELSE[npc.her]折磨者的#ENDIF股间，"
										+ "[npc.name]从[npc2.her]的喉咙中抽出[npc.her][npc.clit+]的瞬间，终于可以哭着喘息了。",
								"[npc2.namePos]压抑的哭泣和抽噎只会给#IF(npc.isPlayer())你#ELSE折磨[npc.her]的人#ENDIF带来额外的快感，"
										+ "但是，在花了几秒钟的时间拳打脚踢[npc.namePos]股间之后，"
										+ "[npc2.she]终于取得了一个小小的胜利，在[npc2.her][npc2.hair(true)]被放开且[npc.namePos][npc.clit+]暂时从[npc2.her]的喉咙里滑了出来时。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.her]的[npc2.tongue]摩挲着[npc.namePos][npc.clit]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.clit+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，[npc2.tongue]亲密地摩挲着[npc.namePos]的[npc.clit]下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.clit+]最终从[npc2.her]喉咙中拔出。"));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction EAR_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "揪耳朵";
		}

		@Override
		public String getActionDescription() {
			return "抓住[npc2.namePos][npc2.ears+]，把[npc2.her]摁在你[npc.clit+]上。";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HEAD)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& (Main.sex.getCharacterTargetedForSexAction(this).getEarType().isAbleToBeUsedAsHandlesInSex());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"抓住[npc2.namePos][npc2.ears+]，[npc.Name]温柔地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her][npc.clit+]直到底部。", 
							"[npc.Name]俯下身轻柔但坚定地抓住[npc2.namePos][npc2.ears+]，缓缓将[npc2.her]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.clit+]深喉。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.ears+]，"
									+ "然后将[npc2.herHim]轻轻向前拉，让[npc.her][npc.clit+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗鲁地抓住[npc2.namePos][npc2.ears+]，残暴地将[npc2.her]的头部拉扯过来，强迫[npc2.herHim]把[npc.her][npc.clit+]从根部全都吞下。",
							"[npc.Name]向下抓住[npc2.namePos][npc2.ears+]，无情地将[npc2.her]的头部拽向自己的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.clit+]深喉。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰抓住[npc2.namePos][npc2.ears+]，"
									+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.her][npc.clit+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]握住[npc2.namePos][npc2.ears+]，紧紧地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her][npc.clit+]直到底部。",
							"[npc.Name]俯下身紧紧地抓住[npc2.namePos][npc2.ears+]，坚定地将[npc2.herHim]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.clit+]深喉。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.ears+]，"
									+ "然后将[npc2.herHim]坚定地向前拉，让[npc.her][npc.clit+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her]肉结":"根部")+"."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name][npc2.eyes]里涌出泪水，花了一会儿功夫用[npc2.tongue]轻轻摩挲着[npc.namePos][npc.clit]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.clit+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，用[npc2.her]的[npc2.tongue]轻轻摩挲着[npc.namePos][npc.clit]的下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.clit+]最终从[npc2.her]喉咙中拔出。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]垂下眸子，忍受了一小会[npc.namePos]的大胆举动，"
										+ "然后把[npc2.her]的头扭过来，厉声提醒[npc.herHim]谁才是主导者。",
								"[npc2.namePos]威胁似的低吼着，那震动的喉咙却同时带来了额外的快感，"
										+ "尽管如此，[npc.name]还是被吓得立即松开[npc2.hair(true)]，将自己[npc.clit+]从[npc2.her]的喉咙拔出来。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"蜷缩着的[npc2.her]合上[npc2.eyes]，[npc2.name]徒劳地抵抗着，拍打#IF(npc.isPlayer())你的#ELSE[npc.her]折磨者的#ENDIF股间，"
										+ "[npc.name]从[npc2.her]的喉咙中抽出[npc.her][npc.clit+]的瞬间，终于可以哭着喘息了。",
								"[npc2.namePos]压抑的哭泣和抽噎只会给#IF(npc.isPlayer())你#ELSE折磨[npc.her]的人#ENDIF带来额外的快感，"
										+ "但是，在花了几秒钟的时间拳打脚踢[npc.namePos]股间之后，"
										+ "[npc2.she]终于取得了一个小小的胜利，在[npc2.her][npc2.hair(true)]被放开且[npc.namePos][npc.clit+]暂时从[npc2.her]的喉咙里滑了出来时。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.her]的[npc2.tongue]摩挲着[npc.namePos][npc.clit]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.clit+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，[npc2.tongue]亲密地摩挲着[npc.namePos]的[npc.clit]下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.clit+]最终从[npc2.her]喉咙中拔出。"));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction GRAB_HORNS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "抓住[npc2.horns]";
		}

		@Override
		public String getActionDescription() {
			return "抓住[npc2.namePos][npc2.horns+]，把[npc2.her]摁在你[npc.clit+]上。";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HORNS)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& Main.sex.getCharacterTargetedForSexAction(this).isHornsAbleToBeUsedAsHandlesInSex();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]握住[npc2.namePos][npc2.horns+]，温柔地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her][npc.clit+]直到底部。",
							"[npc.Name]俯下身轻柔但坚定地抓住[npc2.namePos][npc2.horns+]，缓缓将[npc2.her]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.clit+]深喉。",
							"[npc.name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.horns+]，"
									+ "然后将[npc2.herHim]轻轻向前拉，让[npc.her][npc.clit+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her]肉结":"根部")+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗鲁地抓住[npc2.namePos][npc2.horns+]，残暴地将[npc2.her]的头部拉扯过来，强迫[npc2.herHim]把[npc.her][npc.clit+]从根部全都吞下。",
							"[npc.Name]向下抓住[npc2.namePos][npc2.horns+]，无情地将[npc2.her]的头部拽向自己的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.clit+]深喉。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰抓住[npc2.namePos][npc2.horns+]，"
									+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.her][npc.clit+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her]肉结":"根部")+"."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]握住[npc2.namePos][npc2.horns+]，紧紧地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her][npc.clit+]直到底部。",
							"[npc.Name]俯下身紧紧地抓住[npc2.namePos][npc2.horns+]，坚定地将[npc2.herHim]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.clit+]深喉。",
							"[npc.name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.horns+]，"
									+ "然后将[npc2.herHim]坚定地向前拉，让[npc.her][npc.clit+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her]肉结":"根部")+"."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.her]的[npc2.tongue]轻轻摩挲着[npc.namePos][npc.clit]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.clit+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，用[npc2.her]的[npc2.tongue]轻轻摩挲着[npc.namePos][npc.clit]的下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.clit+]最终从[npc2.her]喉咙中拔出。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]垂下眸子，忍受了一小会[npc.namePos]的大胆举动，"
										+ "然后把[npc2.her]的头扭过来，厉声提醒[npc.herHim]谁才是主导者。",
								"[npc2.namePos]威胁似的低吼着，那震动的喉咙却同时带来了额外的快感，"
										+ "尽管如此，[npc.name]还是被吓得立即松开[npc2.hair(true)]，将自己[npc.clit+]从[npc2.her]的喉咙拔出来。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"蜷缩着的[npc2.her]合上[npc2.eyes]，[npc2.name]徒劳地抵抗着，拍打#IF(npc.isPlayer())你的#ELSE[npc.her]折磨者的#ENDIF股间，"
										+ "[npc.name]从[npc2.her]的喉咙中抽出[npc.her][npc.clit+]的瞬间，终于可以哭着喘息了。",
								"[npc2.namePos]压抑的哭泣和抽噎只会给#IF(npc.isPlayer())你#ELSE折磨[npc.her]的人#ENDIF带来额外的快感，"
										+ "但是，在花了几秒钟的时间拳打脚踢[npc.namePos]股间之后，"
										+ "[npc2.she]终于取得了一个小小的胜利，在[npc2.her][npc2.hair(true)]被放开且[npc.namePos][npc.clit+]暂时从[npc2.her]的喉咙里滑了出来时。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.her]的[npc2.tongue]摩挲着[npc.namePos][npc.clit]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.clit+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，[npc2.tongue]亲密地摩挲着[npc.namePos]的[npc.clit]下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.clit+]最终从[npc2.her]喉咙中拔出。"));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction GRAB_ANTENNAE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "抓住[npc2.antennae]";
		}

		@Override
		public String getActionDescription() {
			return "抓住[npc2.namePos][npc2.antennae+]，把[npc2.her]摁在你[npc.clit+]上。";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HORNS)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& Main.sex.getCharacterTargetedForSexAction(this).isAntennaeAbleToBeUsedAsHandlesInSex();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]握住[npc2.namePos][npc2.antennae+]，温柔地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her][npc.clit+]直到底部。",
							"[npc.Name]俯下身轻柔但坚定地抓住[npc2.namePos][npc2.antennae+]，缓缓将[npc2.her]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.clit+]深喉。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.antennae+]，"
									+ "然后将[npc2.herHim]轻轻向前拉，让[npc.her][npc.clit+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her]肉结":"根部")+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗鲁地抓住[npc2.namePos][npc2.antennae+]，残暴地将[npc2.her]的头部拉扯过来，强迫[npc2.herHim]把[npc.her][npc.clit+]从根部全都吞下。",
							"[npc.Name]向下抓住[npc2.namePos][npc2.antennae+]，无情地将[npc2.her]的头部拽向自己的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.clit+]深喉。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰抓住[npc2.namePos][npc2.antennae+]，"
									+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.her][npc.clit+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her]肉结":"根部")+"."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]握住[npc2.namePos][npc2.antennae+]，紧紧地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her][npc.clit+]直到底部。",
							"[npc.Name]俯下身紧紧地抓住[npc2.namePos][npc2.antennae+]，坚定地将[npc2.herHim]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.clit+]深喉。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.antennae+]，"
									+ "然后将[npc2.herHim]坚定地向前拉，让[npc.her][npc.clit+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"[npc.her]肉结":"根部")+"."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name][npc2.eyes]里涌出泪水，花了一会儿功夫用[npc2.tongue]轻轻摩挲着[npc.namePos][npc.clit]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.clit+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，用[npc2.her]的[npc2.tongue]轻轻摩挲着[npc.namePos][npc.clit]的下缘，"
										+"[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.clit+]最终从[npc2.her]喉咙中拔出。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]垂下眸子，忍受了一小会[npc.namePos]的大胆举动，"
										+ "然后把[npc2.her]的头扭过来，厉声提醒[npc.herHim]谁才是主导者。",
								"[npc2.namePos]威胁似的低吼着，那震动的喉咙却同时带来了额外的快感，"
										+ "尽管如此，[npc.name]还是被吓得立即松开[npc2.hair(true)]，将自己[npc.clit+]从[npc2.her]的喉咙拔出来。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"蜷缩着的[npc2.her]合上[npc2.eyes]，[npc2.name]徒劳地抵抗着，拍打#IF(npc.isPlayer())你的#ELSE[npc.her]折磨者的#ENDIF股间，"
										+ "[npc.name]从[npc2.her]的喉咙中抽出[npc.her][npc.clit+]的瞬间，终于可以哭着喘息了。",
								"[npc2.namePos]压抑的哭泣和抽噎只会给#IF(npc.isPlayer())你#ELSE折磨[npc.her]的人#ENDIF带来额外的快感，"
										+ "但是，在花了几秒钟的时间拳打脚踢[npc.namePos]股间之后，"
										+ "[npc2.she]终于取得了一个小小的胜利，在[npc2.her][npc2.hair(true)]被放开且[npc.namePos][npc.clit+]暂时从[npc2.her]的喉咙里滑了出来时。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.her]的[npc2.tongue]摩挲着[npc.namePos][npc.clit]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.clit+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，[npc2.tongue]亲密地摩挲着[npc.namePos]的[npc.clit]下缘，"
										+"[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.clit+]最终从[npc2.her]喉咙中拔出。"));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};

	public static final SexAction THROAT_MUSCLE_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "收紧喉穴";
		}

		@Override
		public String getActionDescription() {
			return "用你肌肉发达的喉咙挤弄包裹着[npc2.namePos]的[npc2.clit]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getFaceOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"[npc.Name]发出一阵低沉的[npc.moan]，继续专心用[npc.her]肌肉极致发达的喉咙挤弄包裹着[npc2.namePos][npc2.clit+]。",
						isTargetedCharacterInanimate()
							?null
							:"[npc.Name]发出一声低沉的[npc.moan]，当[npc.she]专注于控制[npc.her]喉咙内侧的额外肌肉时。"
								+ "[npc.Name]挤弄包裹着口中[npc2.namePos][npc2.clit+]，让[npc2.herHim]不禁漏出一声愉悦的呻吟。",
						"[npc.Name]发出一连串低沉的[npc.moans]，继续专心用[npc.her]肌肉极致发达的喉咙挤弄包裹着[npc2.namePos][npc2.clit+]。",
						"伴随着一阵低沉的[npc.moan]，[npc.name]专心控制喉咙深处发达的肌肉，用它们紧紧地抓住和按摩[npc2.namePos][npc2.clit+]。");
		}
	};
	
	public static final SexAction CLIT_ORAL_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "接受阴蒂口交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.clit+]插进[npc2.namePos]嘴里，[npc2.herHim]开始吮吸。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]伸手抓住[npc2.namePos]头部，将其[npc.clit][npc.clitTip+]移向[npc2.her][npc2.lips+]，"
										+ "轻轻地将[npc.her]的[npc.hips]向前推，将[npc.her][npc.clit+]滑入[npc2.her]的嘴里。",

								"[npc.name]伸手扒住[npc2.namePos]的头部，将其[npc.clit][npc.clitTip+]紧紧贴住[npc2.namePos][npc2.lips+]，"
										+ "[npc.she]缓慢地在[npc2.her]的[npc2.face]上抽动[npc.her]的[npc.hips]，将[npc.her][npc.clit+]温柔地滑入[npc2.her]的嘴里。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]伸手抓住[npc2.namePos]头部，将其[npc.clit][npc.clitTip+]移向[npc2.her][npc2.lips+]，"
										+ "急切地将[npc.her]的[npc.hips]向前推，将[npc.her][npc.clit+]滑入[npc2.her]的嘴里。",

								"[npc.name]伸手扒住[npc2.namePos]的头部，将其[npc.clit][npc.clitTip+]紧紧贴住[npc2.namePos][npc2.lips+]，"
										+ "[npc.she]急切地在[npc2.her]的[npc2.face]上抽动[npc.her]的[npc.hips]，将[npc.her][npc.clit+]滑入[npc2.her]的嘴里。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]伸手抓住[npc2.namePos]头部，将其[npc.clit][npc.clitTip+]移向[npc2.her][npc2.lips+]，"
										+ "粗暴地向前猛拱[npc.hips]，将那[npc.clit+]深深插入[npc2.her]嘴里。",

								"[npc.name]伸手扒住[npc2.namePos]的头部，将其[npc.clit][npc.clitTip+]紧紧贴住[npc2.namePos][npc2.lips+]，"
										+ "然后粗暴地用[npc.hips]向[npc2.her]的[npc2.face]猛拱，将自己[npc.clit+]深深插入[npc2.her]的嘴里。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]伸手抓住[npc2.namePos]头部，将其[npc.clit][npc.clitTip+]移向[npc2.her][npc2.lips+]，"
										+ "然后用[npc.hips]向前推，将自己[npc.clit+]滑入[npc2.her]的嘴里。",

								"[npc.name]伸手扒住[npc2.namePos]的头部，将其[npc.clit][npc.clitTip+]紧紧贴住[npc2.namePos][npc2.lips+]，"
										+ "然后用[npc.hips]向[npc2.her]的[npc2.face]顶去，将自己[npc.clit+]滑入[npc2.her]的嘴里。"));
						break;
					default:
						break;
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]稍微往后退一点，慢慢地将[npc.her][npc.clit+]放低到[npc2.namePos]嘴里，"
										+ "然后温柔地将[npc.clitTip+]推向[npc2.her][npc2.lips+]，压在[npc2.her][npc2.face+]上。",

								"[npc.name]温柔地把[npc.clit+]放到[npc2.namePos]嘴里，慢慢地张开[npc.legs]，"
										+ "强行把[npc.clitTip+]推向[npc2.her][npc2.lips+]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]稍微往后退一点，迅速地将[npc.her][npc.clit+]放低到[npc2.namePos]嘴里，"
										+ "然后急切地将[npc.clitTip+]推向[npc2.her][npc2.lips+]，压在[npc2.her][npc2.face+]上。",

								"[npc.name]快速地把[npc.clit+]拉到[npc2.namePos]嘴里，慢慢地张开[npc.legs]，"
										+ "强行把[npc.clitTip+]急切地推向[npc2.her][npc2.lips+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]稍微往后退一点，粗暴地将[npc.her][npc.clit+]按在[npc2.namePos]嘴上摩擦，"
										+ "然后激烈地将[npc.clitTip+]推向[npc2.her][npc2.lips+]，压在[npc2.her][npc2.face+]上。",

								"[npc.name]粗暴地把[npc.clit+]拉到[npc2.namePos]嘴里，慢慢地张开[npc.legs]"
										+ "强行把[npc.clitTip+]推向[npc2.her][npc2.lips+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]稍微往后退一点，迅速地将[npc.her][npc.clit+]放低到[npc2.namePos]嘴里，"
										+ "然后将[npc.clitTip+]推向[npc2.her][npc2.lips+]，压在[npc2.her][npc2.face+]上。",

								"[npc.name]快速地把[npc.clit+]拉到[npc2.namePos]嘴里，慢慢地张开[npc.legs]，"
										+ "强行把[npc.clitTip+]推向[npc2.her][npc2.lips+]。"));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"将自己[npc.clit][npc.clitTip+]移动到[npc2.namePos][npc2.lips+]上。"
										+ "[npc.Name]轻轻地将[npc.her]的[npc.hips]向前推，将[npc.her][npc.clit+]滑入[npc2.her]的嘴里。",

								"将自己[npc.clit][npc.clitTip+]顶到[npc2.namePos][npc2.lips+]上。"
										+ "[npc.Name]慢慢地将[npc.her]的[npc.hips]推向[npc2.her]的[npc2.face]，将[npc.her][npc.clit+]滑入[npc2.her]的嘴里。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"将自己[npc.clit][npc.clitTip+]移动到[npc2.namePos][npc2.lips+]上。"
										+ "[npc.Name]急切地将[npc.her][npc.hips]向前推，将[npc.her][npc.clit+]滑入[npc2.her]的嘴里。",

								"将自己[npc.clit][npc.clitTip+]顶到[npc2.namePos][npc2.lips+]上。"
										+ "[npc.Name]急切地将[npc.her][npc.hips]向前推，将[npc.her][npc.clit+]滑入[npc2.her]的嘴里。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"将自己[npc.clit][npc.clitTip+]移动到[npc2.namePos][npc2.lips+]上。"
										+ "[npc.Name]粗暴地将[npc.her][npc.hips]向前推，强制将[npc.her][npc.clit+]滑入[npc2.her]的嘴里。",

								"将自己的[npc.clit][npc.clitTip+]顶到[npc2.namePos][npc2.lips+]上，"
										+"然后粗暴地用[npc.hips]向[npc2.her][npc2.face]猛冲，将自己[npc.clit+]深深插入[npc2.her]的嘴里。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"将自己[npc.clit][npc.clitTip+]移动到[npc2.namePos][npc2.lips+]上。"
										+ "[npc.Name]轻轻地将[npc.her]的[npc.hips]向前推，将[npc.her][npc.clit+]滑入[npc2.her]的嘴里。",

								"将自己[npc.clit][npc.clitTip+]顶到[npc2.namePos][npc2.lips+]上。"
										+ "[npc.Name]慢慢地将[npc.her]的[npc.hips]推向[npc2.her]的[npc2.face]，将[npc.her][npc.clit+]滑入[npc2.her]嘴里。"));
						break;
					default:
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵低沉地[npc2.a_moan+]，然后缓慢地向前伸头，开始温柔地吮吸[npc.namePos][npc.clit+]。",
	
								"[npc2.name]轻柔，低沉地[npc2.moan]，温柔地向前伸头，"
										+ "把[npc2.her][npc2.lips+]包裹在[npc.namePos][npc.clit+]周围，然后开始口交。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵[npc2.a_moan+]，急切地向前伸头，开始愉悦地吮吸[npc.namePos][npc.clit+]。",
	
								"[npc2.name]急切地[npc2.moan]，开心地向前伸头，"
										+ "把[npc2.her][npc2.lips+]包裹在[npc.namePos][npc.clit+]周围，然后开始口交。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵[npc2.a_moan+]，然后向前伸头，粗暴地开始吮吸[npc.namePos][npc.clit+]。",
	
								"[npc2.name]急切地[npc2.moan]，将头部努力伸向前，"
										+ "把[npc2.her][npc2.lips+]包裹在[npc.namePos][npc.clit+]周围，然后开始口交。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵[npc2.a_moan+]，然后向前伸头，开始吮吸[npc.namePos][npc.clit+]。",
	
								"[npc2.name]低沉地[npc2.moan]，向前伸头，"
										+ "把[npc2.her][npc2.lips+]包裹在[npc.namePos][npc.clit+]周围，然后开始口交。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出低沉的[npc2.sob]， 被[npc.namePos][npc.clit+]呛在喉咙里，喘不过气来，慌乱地努力将头远离[npc.her]的腹股沟。",
	
								"[npc2.Name]发出低沉的[npc2.sob]，慌乱地努力拔着[npc.namePos][npc.clit+]，"
										+ "呛水感和窒息感让[npc2.she]局促不安地挣扎着，反抗着[npc.herHim]。"));
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
							"[npc2.Name]渴盼地用[npc2.lips+]裹住[npc.namePos][npc.clit+]，"
									+ "发出含糊不清的[npc2.moan]，亢奋地上下甩头。",
		
							"[npc2.namePos]的口中飘出一声极其低沉的[npc2.moan]，"
									+ "还用[npc2.lips+]热切地裹住[npc.namePos][npc.clit+]，继续亢奋地给[npc.herHim]口交。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，热切地用[npc2.lips+]含住[npc.namePos][npc.clit+]，"
									+ "急切地吮吸舔吻着[npc.clit]，继续发出含糊不清的声音。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要逃离[npc.namePos]的[npc.clit]，但没能成功，"
									+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地发出含糊不清的抗议声，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
		
							"[npc2.namePos]口中漏出一声低沉的[npc2.sob]，[npc2.she]无力地尝试逃脱，"
									+ "[npc.namePos][npc.clit+]仍然来回磨蹭着[npc2.her][npc2.lips+]，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
		
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地反抗着[npc.Name]，发出含糊不清的抗议声。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]用[npc2.lips+]裹住[npc.namePos][npc.clit+]，"
									+ "发出相当含混的[npc2.moan]，上下甩起头来。",
		
							"[npc2.namePos]的口中飘出一声极其低沉的[npc2.moan]，"
									+ "还用[npc2.lips+]裹住[npc.namePos][npc.clit+]，继续专心给[npc.herHim]口交。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.lips+]含住[npc.namePos][npc.clit+]，"
									+ "吮吸舔吻着[npc.clit]，继续发出含糊不清的声音。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name]用[npc2.lips+]温柔地裹住[npc.namePos][npc.clit+]，"
									+ "发出相当含混的[npc2.moan]，慢慢上下甩起头来。",
		
							"[npc2.namePos]的口中飘出一声极其低沉的[npc2.moan]，"
									+ "还用[npc2.lips+]裹住[npc.namePos][npc.clit+]，继续满怀爱意地专心给[npc.herHim]口交。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.lips+]慢慢含住[npc.namePos][npc.clit+]，"
									+ "温柔地吮吸舔吻着[npc.clit]，继续发出含糊不清的声音。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]激烈地用[npc2.lips+]含住[npc.namePos][npc.clit+]，"
									+ "发出相当含混的[npc2.moan]，粗暴地上下甩起头来。",
		
							"[npc2.namePos]的口中飘出一声极其低沉的[npc2.moan]，"
									+ "还强行用[npc2.lips+]裹住[npc.namePos][npc.clit+]，继续专心给[npc.herHim]暴力口交。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地用[npc2.lips+]含住[npc.namePos][npc.clit+]，"
									+ "激烈地吮吸舔吻着[npc.clit]，继续发出含糊不清的声音。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction CLIT_ORAL_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "口交阴蒂(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "接着抓住，让[npc2.name]吮吸你[npc.clit+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name][npc.clit+]温柔地划过[npc2.namePos][npc2.lips+]，"
								+ "[npc.she]发出柔和的[npc.moan]，将[npc.hips]稳稳顶到[npc2.her][npc2.face]上。",
						"[npc.Name]慢慢地将[npc.hips]向前推向[npc2.namePos]的[npc2.face]，温柔地干着[npc2.name]的喉咙，不禁漏出一小声[npc.moan]。",
						"[npc.name]温柔地将[npc.hips+]拱上[npc2.namePos]的[npc2.face]，"
								+ "接受着[npc2.her]的口交，发出了轻轻的[npc.moan]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "接受阴蒂口交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.clit+]按在[npc2.namePos]脸上，鼓励[npc2.herHim]接着吸。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]急切地将[npc.clit+]顶进[npc2.namePos][npc2.lips+]间，"
								+ "发出[npc.a_moan+]，将[npc.hips]贪婪地顶到[npc2.her][npc2.face]上。",
						"[npc.Name]急切地将[npc.hips]顶向[npc2.namePos]的[npc2.face]，饥渴地侵犯着[npc2.her]的喉咙，发出[npc.a_moan+]。",
						"[npc.name]亢奋地将[npc.hips+]拱上[npc2.namePos]的[npc2.face]，"
								+ "开心地接受着[npc2.her]的口交，发出了轻轻的[npc.moan]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "深喉";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.clit+]粗暴地插进[npc2.namePos]的喉咙，好好深喉[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			List<String> descriptions = new ArrayList<>();

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.STANDING)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SITTING)) {

				for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getClitorisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("[npc.name]猛然间粗鲁地将[npc.her][npc.clit+]深深插入[npc2.namePos]的喉咙。"
												+ "用[npc.hands]把住[npc2.her]的头，[npc.she]开始粗鲁地操[npc2.her]的[npc2.face]"
												+ (isTargetedCharacterInanimate()
														?"，[npc.she]一边笑着一边用[npc.her]的阴茎上的倒刺一次又一次地刮过[npc2.namePos]的喉咙。"
														:"，随着[npc.namePos]的阴茎上的倒刺一再刮伤[npc2.her]的喉咙，这引起了泪水从[npc2.her]的[npc2.eyes]流出。"));
							break;
						case FLARED:
							descriptions.add("[npc.name]猛然间粗鲁地将[npc.her][npc.clit+]深深插入[npc2.namePos]的喉咙。"
												+ "用[npc.hands]把住[npc2.her]的头，[npc.she]开始粗鲁地操[npc2.her]的[npc2.face]"
												+ (isTargetedCharacterInanimate()
														?"，[npc.she]一边笑着一边用[npc.her]肥厚平坦的龟头在[npc2.namePos]的喉咙来回磨蹭。"
														:"，随着[npc.namePos]的肥厚平坦的龟头强行在[npc2.her]的喉咙中来回磨蹭，泪水从[npc2.namePos]的[npc2.eyes]里流出。"));
							break;
						case KNOTTED:
							descriptions.add("[npc.name]猛然间粗鲁地将[npc.her][npc.clit+]深深插入[npc2.namePos]的喉咙。"
												+ "用[npc.hands]把住[npc2.her]的头，[npc.she]开始粗鲁地操[npc2.her]的[npc2.face]"
												+ (isTargetedCharacterInanimate()
														?"，[npc.she]一边笑着一边用[npc.her]的结反复顶在[npc2.namePos]的[npc2.lips+]上。"
														:"，随着[npc.namePos]反复用[npc.her]的结猛烈地撞击着[npc2.her]的[npc2.lips+]，泪水从[npc2.namePos]的[npc2.eyes]里流出。"));
							break;
						default:
							break;
					}
				}
				
				descriptions.add("[npc.Name]抓住[npc2.namePos]头部的一边，在[npc2.name]反应过来发生了什么之前，"
									+ "[npc.sheIs]粗暴地用[npc.clit+]在[npc2.her]的脸部小穴猛烈地抽送。");
				descriptions.add("[npc.Name]发出[npc.a_moan+]，[npc.clit+]猛捅进[npc2.namePos]的喉咙。"
									+ "[npc2.she]试图强忍住泪水，[npc.Name]却开始迅速地前后扭动[npc.hips]，"
									+ "双手按住[npc2.namePos]头部，[npc.she]无情地操着[npc2.her]的[npc2.face]。");
				descriptions.add("[npc.name]抓住[npc2.namePos]头部的一边，猛地将[npc2.her]的脸拉到[npc.her]的腹股沟，"
									+ "将[npc.clit+]深深插入[npc2.her]的喉咙，并开始粗鲁地操干[npc2.her]的脸。");
				descriptions.add("[npc.name]有力地将[npc.clit+]捅入[npc2.namePos]的喉咙。"
									+ "粘稠的唾液"+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, LubricationType.PRECUM)?"和先走液":"")
									+"从[npc2.name]嘴角淌出，[npc.name]退了退，让[npc2.name]缓了口气，然后继续猛然操向[npc2.her]的[npc2.face]。");
				
				return Util.randomItemFrom(descriptions);
				
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				
				for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getClitorisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("[npc.name]对着[npc2.namePos]的头部分开双腿，粗鲁地坐了下去，将[npc.her][npc.clit+]深深塞入[npc2.namePos]的喉咙。"
												+ "根部蹭了下[npc2.her][npc2.lips+]，然后[npc.she]粗暴地操起[npc2.her]的脸，"
												+ "引得[npc2.name]发出含糊的[npc2.moan]，挣扎着喘息，"
												+ "而[npc2.she]在[npc.Name]下颤抖着，感受到[npc.her][npc.clit+]那一排倒刺刮着自己的喉咙。");
							break;
						case BLUNT:
							break;
						case FLARED:
							descriptions.add("[npc.name]对着[npc2.namePos]的头部分开双腿，粗鲁地坐了下去，将[npc.her][npc.clit+]深深塞入[npc2.namePos]的喉咙。"
												+ "根部蹭了下[npc2.her][npc2.lips+]，然后[npc.she]粗暴地操起[npc2.her]的脸，"
												+ "引得[npc2.name]发出含糊的[npc2.moan]，挣扎着喘息，"
												+ "而[npc2.she]在[npc.Name]下颤抖着，感受到[npc.her]平头[npc.clit+]撕扯着自己的喉咙。");
							break;
						case KNOTTED:
							descriptions.add("[npc.name]对着[npc2.namePos]的头部分开双腿，粗鲁地坐了下去，将[npc.her][npc.clit+]深深塞入[npc2.namePos]的喉咙。"
												+ "根部蹭了下[npc2.her][npc2.lips+]，然后[npc.she]粗暴地操起[npc2.her]的脸，"
												+ "引得[npc2.name]发出含糊的[npc2.moan]，挣扎着喘息，"
												+ "而[npc2.she]在[npc.Name]下颤抖着，感受到[npc.her][npc.clit+]底那粗壮的结反复击打着自己的喉咙。");
							break;
						case PREHENSILE:
						case RIBBED:
						case SHEATHED:
						case TAPERED:
						case TENTACLED:
						case VEINY:
						case OVIPOSITOR:
							break;
					}
				}
				
				if(Main.sex.getCharacterPerformingAction().hasLegs()) {
					descriptions.add("[npc.Name]跪坐在仍迷迷糊糊的[npc2.namePos]脸上，"
							+ "[npc.sheIs]粗暴地用[npc.clit+]在[npc2.her]的脸部小穴猛烈地抽送。");
					
				} else {
					descriptions.add("[npc.Name]把[npc.her]的腹股沟压向[npc2.namePos]的脸，在[npc2.she]明白发生什么之前粗暴地将[npc.clit+]深深插入[npc2.her]的喉咙，"
							+ "[npc.sheIs]粗暴地用[npc.clit+]在[npc2.her]的脸部小穴猛烈地抽送。");
				}
				

				descriptions.add("[npc.Name]发出[npc.a_moan+]，[npc.clit+]猛捅进[npc2.namePos]的喉咙。"
									+ "[npc2.she]试图强忍住泪水，[npc.Name]却开始猛烈地来回磨蹭[npc.hips]，"
									+"随心所欲地操着[npc2.name]的嘴，爽得不断[npc.moans+]。");

				descriptions.add("[npc.Name]压向[npc2.namePos]的脸，粗暴地将[npc.clit+]深深插入[npc2.her]的喉咙，"
									+ "发出[npc.a_moan+]，开始猛烈地前后撞击[npc.hips]，无情地操干[npc2.her]的脸。");

				descriptions.add("[npc.name]有力地将[npc.clit+]捅入[npc2.namePos]的喉咙。"
									+"看着一缕缕粘稠的唾液"+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, LubricationType.PRECUM)?"混合着精液":"")
									+"从嘴角淌下，[npc.Name]一口气把[npc2.her]抱了起来，"
										+"停了一下，等[npc2.name]缓了口气，又狠狠地压着[npc2.her]向下，就以一个更快地节奏操了起来。");

				return Util.randomItemFrom(descriptions);
				
			} else {
				UtilText.nodeContentSB.setLength(0);
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]用力把[npc.clit+]塞入[npc2.namePos][npc2.lips+]，"
									+ "发出[npc.a_moan+]，粗暴地将[npc.hips]顶向[npc2.name]的[npc2.face]。",
							"[npc.Name]发出[npc.a_moan+]，用力的朝[npc2.namePos]的脸顶[npc.hips]，粗暴地侵犯着[npc2.her]的喉咙。",
							"[npc.name]侵略十足地顶[npc.hips+]撞向[npc2.namePos]的脸，"
									+"粗暴地持续怼着，操得[npc.Name][npc.a_moan+]。"));
				
				UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
				
				return UtilText.nodeContentSB.toString();
			
			}
		}
		
	};
	
	public static final SexAction CLIT_ORAL_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "抗拒阴蒂口交";
		}

		@Override
		public String getActionDescription() {
			return "努力让你[npc.clit+]远离[npc2.namePos]的嘴。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name][npc.a_sob+]着，拼命地想要把[npc2.namePos]的脸推开，可惜[npc2.namePos]虽然动作温柔，态度却十分坚定地"
									+"抓住自己，被迫看着自己[npc.herHim][npc.clit+]在[npc2.she]的嘴里抽插，"
									+"承受着强加的口交。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.clit+]从[npc2.namePos]的嘴里拔出来。"
									+ "却浑身无力被[npc2.her]紧紧地抓住，只能任凭[npc2.name]动作温柔却无视自己的挣扎，继续用[npc.clit+]操干着自己的嘴。",
	
							"[npc.Name]啜泣着，想把[npc.clit+]从[npc2.namePos]的嘴里拔出，却被[npc2.her]狠狠地按住"
									+ "只能无奈地接受着[npc.herHim]强迫口交。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name][npc.a_sob+]着，拼命地想要把[npc2.namePos]的脸推开，可惜[npc2.namePos]粗暴地抓住，"
									+"用咆哮威胁着停止抵抗，只能仍由[npc2.name]用力地吞吐着[npc.clit+]。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.clit+]从[npc2.namePos]的嘴里拔出来。"
									+ "但[npc2.name]却一边紧紧地抓着[npc.her]，用咆哮威胁[npc.her]停止抵抗，一边继续吮吸着[npc.her][npc.clit+]。",
	
							"[npc.Name]啜泣着，想要把自己[npc.clit+]从[npc2.namePos]的嘴里拔出，却因为[npc2.namePos]抓的太紧而没有成功，"
									+ "只能无奈地接受着[npc.herHim]强迫口交。"));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name][npc.a_sob+]着，拼命地想要把[npc2.namePos]的脸推开，可惜[npc2.namePos]狠狠地抓紧自己，"
									+ "饥渴地用嘴吞吐着自己[npc.clit+]，承受着强迫口交。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.clit+]从[npc2.namePos]的嘴里拔出来。"
									+"但是[npc2.name]却把自己狠狠抓住，无视自己若有若无的抵抗饥渴地吮吸着[npc.clit+]。",
	
							"[npc.Name]啜泣着，想要把自己[npc.clit+]从[npc2.namePos]的嘴里拔出，却因为[npc2.namePos]抓的太紧而没有成功，"
									+ "只能无奈地接受着[npc.herHim]强迫口交。"));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]发出[npc.a_sob+]，拼命地想要推开[npc2.namePos]的脸，却被[npc2.she]抓住，"
								+"只能被迫承受着强加的口交。",

						"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.clit+]从[npc2.namePos]的嘴里拔出来。"
								+"但是[npc2.name]却把自己狠狠抓住，无视自己若有若无的抵抗饥渴地吮吸着[npc.clit+]。",

						"[npc.Name]发出[npc.Sobbing+]，想要把[npc.clit+]从[npc2.namePos]嘴里拔出，却被[npc2.her]狠狠抓住，"
								+"只能看着[npc2.name]无视自己抵抗强行给自己口交。"));
			}
			
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "接受阴蒂口交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.clit+]按在[npc2.namePos]脸上，鼓励[npc2.herHim]接着吸。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]将[npc.clit+]顶进[npc2.namePos][npc2.lips+]间，"
								+ "发出[npc.a_moan+]，将[npc.hips]顶到[npc2.her][npc2.face]上。",
						"[npc.Name]将[npc.hips]顶向[npc2.namePos]的[npc2.face]，侵犯着[npc2.her]的喉咙，发出[npc.a_moan+]。",
						"将[npc.hips+]拱上[npc2.namePos]的[npc2.face]，"
								+ "开心地接受着[npc2.her]的口交，发出了轻轻的[npc.moan]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "阴蒂口交(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地将你的[npc.hips]压向[npc2.namePos]的脸，将[npc.clit+]插入[npc2.her]的喉咙。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]急切地将[npc.clit+]顶进[npc2.namePos][npc2.lips+]间，"
								+ "发出[npc.a_moan+]，将[npc.hips]贪婪地顶到[npc2.her][npc2.face]上。",
						"[npc.Name]急切地将[npc.hips]顶向[npc2.namePos]的[npc2.face]，饥渴地侵犯着[npc2.her]的喉咙，发出[npc.a_moan+]。",
						"[npc.name]亢奋地将[npc.hips+]拱上[npc2.namePos]的[npc2.face]，"
								+ "开心地接受着[npc2.her]的口交，发出了轻轻的[npc.moan]。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_ORAL_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止阴蒂口交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.clit+]从[npc2.namePos]嘴里抽出来，停止被[npc2.her]口交。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				UtilText.nodeContentSB.append("[npc.Name]挺起膝盖，使得自己[npc.clit+]在[npc2.namePos]口中滑进滑出，"
							+ "从[npc2.namePos][npc2.lips+]间带出一丝丝唾液，溅到[npc2.her]的脸上。");
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]粗暴地再一次把[npc.clit+]挺入[npc2.namePos]喉咙深处，然后就收[npc.hips]拔了出来，"
										+"笑着看[npc2.name]呛出精液不断吸气的样子。",

								"[npc.Name]粗暴地挺腰，对着[npc2.namePos]的脸冲刺，每一次都把[npc.clit+]连根插入[npc2.namePos]喉咙深处，又全部抽了出来。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]将[npc.clit+]从[npc2.namePos]口中抽出，[npc.she]发出一阵[npc.a_moan+]，结束了这次口交。",

								"[npc.name]发出[npc.a_moan+]，缩回头，把[npc.her][npc.clit+]从[npc2.namePos]嘴里完全滑了出来。"));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]小声啜泣着，苦苦哀求[npc.Name]放过自己。",
	
								"[npc2.name]挣扎着想要挣脱[npc.namePos]的控制，"
										+"泪流满面地哀求[npc.herHim]放过自己。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一声[npc2.a_moan+]，暴露了[npc2.her]希望继续吮吸[npc.namePos][npc.clit+]的欲望。",
	
								"[npc2.Name][npc2.moansVerb]，感受着[npc.name]从[npc2.her]嘴里拔出，努力抑制想继续吮吸那[npc2.clit+]的欲望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction GIVING_CLIT_ORAL_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "吸吮阴蒂";
		}

		@Override
		public String getActionDescription() {
			return "把[npc2.namePos][npc2.clit+]含进嘴里，开始吮吸。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos]喘着粗气，俯身低头埋在[npc2.namePos][npc2.legs]间，"
										+"积极地含住[npc2.clit+]不断亲吻，重点照顾着[npc2.clitTip+]。",

								"[npc.name]埋头在[npc2.namePos][npc2.legs]间，"
										+"地含住[npc2.clit+]，细致地把[npc2.clit+]舔了个遍。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos]喘着粗气，饥渴地俯身低头埋在[npc2.namePos][npc2.legs]间，"
										+"贪婪而积极地含住[npc2.clit+]不断亲吻，重点照顾着[npc2.clitTip+]。",

								"将[npc.her]的头急切地压入[npc2.namePos][npc2.legs]间，"
										+"饥渴地含住[npc2.clit+]，慢慢地把[npc2.clit+]舔了个遍。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos]喘着粗气，动作麻利地俯身低头埋在[npc2.namePos][npc2.legs]间，"
										+"用力粗暴地含住[npc2.clit+]不断亲吻，重点照顾着[npc2.clitTip+]，弄出了咕噜噜的水声。",

								"[npc.name]埋头于[npc2.namePos][npc2.legs]间，"
										+"用力地含住[npc2.clit+]，粗暴地把[npc2.clit+]舔了个遍。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos]喘着粗气，俯身低头埋在[npc2.namePos][npc2.legs]间，"
										+"含住[npc2.clit+]不断亲吻，重点照顾着[npc2.clitTip+]。",

								"[npc.name]埋头于[npc2.namePos][npc2.legs]间，"
										+"含住[npc2.clit+]，细致而又黏湿地把[npc2.clit+]舔了个遍。"));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"把[npc2.namePos][npc2.clitTip+]含在唇间，"
										+ "[npc.Name]慢慢地将[npc2.clit]含进嘴里，发出带着水声含糊不清的呻吟，开始为[npc2.name]口交。",

								"用[npc.lips+]包裹住[npc2.namePos][npc2.clitTip+]，"
										+"[npc.name]含糊不清地[npc.moan]着，开始给[npc.her]口交。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"用[npc.lips+]含着[npc2.namePos][npc2.clitTip+]，"
										+"[npc.name]饥渴地含入[npc2.namePos]的[npc2.clit]，带着含糊不清的[npc.moan]开始为[npc2.name]口交。",

								"用[npc.lips+]包裹住[npc2.namePos][npc2.clitTip+]，"
										+"[npc.name]含糊不清地[npc.moan]着，饥渴地给[npc.her]口交。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"用[npc.lips+]含着[npc2.namePos][npc2.clitTip+]，"
										+"[npc.name]用力地含入[npc2.namePos]的[npc2.clit]，带着含糊不清的[npc.moan]强迫给[npc2.name]口交。",

								"粗暴地抿着[npc2.namePos][npc2.clitTip+]，"
										+"[npc.name]含糊不清地[npc.moan]着，开始给[npc.her]口交。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"用[npc.lips+]含着[npc2.namePos][npc2.clitTip+]，"
										+"[npc.name]顺势把[npc2.namePos]的[npc2.clit]吞入嘴中，带着含糊不清的[npc.moan]开始给[npc2.name]口交。",

								"用[npc.lips+]包裹住[npc2.namePos][npc2.clitTip+]，"
										+"[npc.name]含糊不清地[npc.moan]着，饥渴地给[npc.her]口交。"));
						break;
					default:
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]将[npc2.hips]温柔地顶向[npc.namePos]的[npc.face]，让[npc.name]温柔地吮吸[npc2.her][npc2.clit+]，[npc2.Moaning+]着。",
	
								"慢慢地摆动[npc2.hips]，"
										+ "[npc2.name]发出柔和的[npc2.moan]，享受着[npc.namePos][npc.lips+]上下抚弄整根[npc2.clit+]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]将[npc2.hips]贪婪地顶向[npc.namePos]的[npc.face]，让[npc.name]吮吸[npc2.her][npc2.clit+]，[npc2.Moaning+]着。",
	
								"积极地摆动[npc2.hips]，"
										+"[npc2.name]发出[npc2.a_moan+]，享受着[npc2.clit+]上[npc.namePos][npc.lips+]反复摩擦着的快感。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]将[npc2.hips]粗暴地顶向[npc.namePos]的[npc.face]，让[npc.name]吮吸[npc2.her][npc2.clit+]，[npc2.Moaning+]着。",
	
								"粗鲁地摆动[npc2.hips]，"
										+"[npc2.name]发出[npc2.a_moan+]，享受着[npc2.clit+]上[npc.namePos][npc.lips+]反复摩擦着的快感。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]将[npc2.hips]顶向[npc.namePos]的[npc.face]，让[npc.name]吮吸[npc2.her][npc2.clit+]，[npc2.Moaning+]着。",
	
								"摆动着[npc2.hips]，"
										+"[npc2.name]发出[npc2.a_moan+]，享受着[npc2.clit+]上[npc.namePos][npc.lips+]反复摩擦着的快感。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出[npc2.a_sob+]，想要把[npc2.clit]从[npc.namePos]嘴里拔出，却只能无能为力地接受着强迫口交。",
	
								"[npc2.name]脸上泪水流下，和口水混在一起，颤抖着抵抗着强迫被口交带来的禁忌的快感。[npc2.she]带着啜泣声和口腔中含着东西产生的水声，哀求[npc.name]不要继续了。"));
						break;
					default:
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
	};
	
	// TODO add descriptions of kissing knot, flared head pushing down throat, tentacles, ribs and barbs
	
	public static final SexAction GIVING_CLIT_ORAL_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.CLIT);
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "深喉";
		}

		@Override
		public String getActionDescription() {
			return "把[npc2.namePos][npc2.clit+]吞得尽可能深。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗暴地用嘴含住[npc2.namePos][npc2.clit+]，"
									+ "接着尽可能将之吞入自己的喉咙。",

							"随着一声柔软的，含混不清的[npc.moan]，[npc.name]小心翼翼地前倾，"
									+ "[npc.her]张开[npc.lips+]并尽可能地把[npc2.namePos][npc2.clit+]深入[npc.her]的喉咙深处。",

							"慢慢把[npc.her]的脑袋向前滑动，[npc.name]温柔地张开她[npc.lips+]，以便将[npc2.namePos][npc2.clit+]深入[npc.her]的喉咙。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"饥渴地用[npc.lips+]把嘴里的[npc2.clit+]完全包裹住，[npc.name]将头快速地往里推。"
									+ "贪婪地把[npc2.namePos][npc2.clit+]尽可能深地插入[npc.she]的喉咙",

							"随着一声含混不清的[npc.moan+]，[npc.name]急切地前倾，"
									+ "[npc.her]饥渴地张开[npc.lips+]并尽可能地把[npc2.namePos][npc2.clit+]深入[npc.her]的喉咙深处。",

							"[npc.name]将脑袋贪婪地往下滑动，欣然张开她[npc.lips+]，将[npc2.namePos][npc2.clit+]深入[npc.her]的喉咙。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name][npc.lips+]将[npc2.clit+]猛然包裹住，[npc.her]把头粗暴地向下推压，"
									+"强迫让[npc2.namePos][npc2.clit+]尽可能地插入喉咙深处。",

							"随着一声含混不清的[npc.moan+]，[npc.name]迅速前倾，"
									+ "[npc.her]粗暴地张开[npc.lips+]并迫使[npc2.namePos][npc2.clit+]深入[npc.her]的喉咙深处。",

							"激烈地将[npc.her]的脑袋向下推动，[npc.name]张开[npc.her][npc.lips+]，迫使[npc2.namePos][npc2.clit+]深深推入[npc.her]的喉咙。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"用[npc.lips+]把嘴里的[npc2.clit+]完全包裹住，[npc.name]将头快速地往里推。"
									+ "把[npc2.namePos][npc2.clit+]尽可能深地插入[npc.she]的喉咙",

							"随着一声含混不清的[npc.moan+]，[npc.name]前倾，"
									+ "[npc.her]张开[npc.lips+]并尽可能地把[npc2.namePos][npc2.clit+]深入[npc.her]的喉咙深处。",

							"[npc.name]将脑袋往下滑动，张开她[npc.lips+]，将[npc2.namePos][npc2.clit+]深入[npc.her]的喉咙。"));
					break;
				default:
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				default:
					return UtilText.returnStringAtRandom(
							"[npc2.Name]贪婪地将自己[npc2.clit+]深入[npc.namePos]的喉咙，"
									+ "[npc2.she]亢奋地发出一声含糊不清的[npc2.moan]，享受着[npc.namePos]的口交。",
							"[npc2.she]开始将[npc2.her][npc2.clit+]深深插入[npc.namePos]喉咙，一声含糊不清的[npc2.moan]从[npc2.namePos]的嘴里漏出。",
							"[npc2.name]愉悦地[npc2.moaning]着，热切地挺动[npc2.her][npc2.clit+]，拼命要插入[npc.namePos]喉咙的最深处。");
				case SUB_RESISTING:
					return UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.clit]从[npc.namePos]的口中拔出，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.clit+]在自己的喉咙中继续抽插。",
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.clit]从[npc.namePos]口中抽离。");
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将[npc2.clit+]滑入[npc.namePos]的喉咙深处，"
									+ "[npc2.she]发出一声含糊不清的柔和[npc2.moan]，享受着[npc.namePos]的口交。",
							"[npc2.name]慢慢地将[npc2.clit+]深入[npc.namePos]的喉咙，口中飘出一声含糊不清的[npc2.moan]。",
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地用[npc2.clit+]深入[npc.namePos]的喉咙。");
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.clit+]深深插入[npc.namePos]的喉咙，"
									+ "[npc2.she]发出一声含糊不清的[npc2.moan]，享受着[npc.namePos]的口交。",
							"[npc2.name]暴力地将[npc2.clit+]深深插入[npc.namePos]的喉咙，口中飘出一声含糊不清的[npc2.moan]。",
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地顶着[npc2.her][npc2.clit+]，拼命要插入[npc.namePos]喉咙最深处。");
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.clit+]深深插入[npc.namePos]的喉咙，"
									+ "[npc2.she]发出一声含糊不清的[npc2.moan]，享受着[npc.namePos]的口交。",
							"[npc2.she]开始将[npc2.her][npc2.clit+]深深插入[npc.namePos]喉咙，一声含糊不清的[npc2.moan]从[npc2.namePos]的嘴里漏出。",
							"[npc2.name]愉悦地[npc2.moaning]着，开始将[npc2.clit+]深深插入[npc.namePos]的喉咙。");
			}
		}
		return "";
	}
	
	public static final SexAction GIVING_CLIT_ORAL_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "吸吮阴蒂(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "吸吮[npc2.namePos][npc2.clit+](温柔)。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"温柔地用[npc.lips+]包裹住[npc2.namePos][npc2.clit+]后，[npc.name]把头上下移动着，深情地给[npc2.namePos]口交。",
					"随着一声柔软的，含混不清的[npc.moan]，[npc.name]开始和缓地上下移动[npc.her]的脑袋，"
							+ "用[npc.lips+]包裹住[npc2.namePos][npc2.clit+]后，[npc.name]把头上下移动着，深情地给[npc2.namePos]口交。",
					"慢慢地把头上下移动，[npc.name]温柔地用[npc.her][npc.lips+]包裹住[npc2.namePos][npc2.clit+]，为[npc2.namePos]口交。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_CLIT_ORAL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "吸吮阴蒂";
		}

		@Override
		public String getActionDescription() {
			return "迫切地吸吮[npc2.namePos][npc2.clit+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"急切地用[npc.lips+]包裹住[npc2.namePos][npc2.clit+]后，[npc.name]把头迅速地上下移动着，热情地给[npc2.namePos]口交。",
					"随着一声含混不清的[npc.moan]，[npc.name]开始迅速地上下移动起[npc.her]的脑袋，"
							+ "贪婪地用[npc.lips+]包裹住[npc2.namePos][npc2.clit+]后，[npc.name]把头上下移动着，给[npc2.namePos]口交。",
					"迅速地把头上下移动，[npc.name]温柔地用[npc.her][npc.lips+]饥渴地包裹住[npc2.namePos][npc2.clit+]，为[npc2.namePos]如饥似渴地口交。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_CLIT_ORAL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "吸吮阴蒂(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "吸吮[npc2.namePos][npc2.clit+](粗暴)。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"用力用[npc.lips+]包裹住[npc2.namePos][npc2.clit+]后，[npc.name]把头上下移动着，带着侵略性地给[npc2.namePos]口交。",
					"随着一声含混不清的[npc.moan]，[npc.name]开始激烈地上下移动[npc.her]的脑袋，"
							+ "粗暴地用[npc.lips+]包裹住[npc2.namePos][npc2.clit+]后，[npc.name]把头上下移动着，给[npc2.namePos]口交。",
					"粗暴地把头上下移动，[npc.name]支配地用[npc.her][npc.lips+]包裹住[npc2.namePos][npc2.clit+]，为[npc2.namePos]口交。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抗拒吸吮阴蒂";
		}

		@Override
		public String getActionDescription() {
			return "努力把[npc2.namePos][npc2.clit+]挤出你的嘴。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.sob]因为强制口交变得模糊不清。[npc.Name]挣扎着，想挣脱[npc2.name]"
										+"却被[npc2.her]慢慢地抓回，只能感受着[npc2.clit+]在唇间进进出出。",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把头缩回来，"
										+ "喉咙深处爆发出声嘶力竭的抗议声，但[npc2.name]依然温柔地将自己[npc2.clit+]推入[npc.her]的喉咙。",
								"[npc.Name]痛苦地[npc.sobbing]，挣扎着，想推开[npc2.name]，"
										+"却浑身无力，只能被[npc2.her]温柔地拉回来，感受着沾满口水和泪水[npc2.clit+]在自己嘴里进进出出。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.sob]因为强制口交变得模糊不清。[npc.Name]挣扎着，对抗着[npc2.name]"
										+ "却被[npc2.her]粗暴地抓回，只能感受着[npc2.clit+]在唇间进进出出。",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把头缩回来，"
										+ "喉咙深处爆发出声嘶力竭的抗议声，但[npc2.name]依然粗暴地将自己[npc2.clit+]推入[npc.her]的喉咙。",
								"[npc.Name]痛苦地[npc.sobbing]，挣扎着，想推开[npc2.name]，"
										+"却浑身无力，只能被[npc2.her]粗暴地拉回来，感受着沾满口水和泪水[npc2.clit+]在自己嘴里进进出出。"));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.sob]因为强制口交变得模糊不清。[npc.Name]挣扎着，想挣脱[npc2.name]"
										+ "却被[npc2.her]饥渴地抓回，只能感受着[npc2.cock+]在唇间进进出出。",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把头缩回来，"
										+ "喉咙深处爆发出声嘶力竭的抗议声，但[npc2.name]依然急切地将自己[npc2.clit+]推入[npc.her]的喉咙。",
								"[npc.Name]痛苦地[npc.sobbing]，挣扎着，想推开[npc2.name]，"
										+"却浑身无力，只能被[npc2.her]饥渴地拉回来，感受着沾满口水和泪水[npc2.clit+]在自己嘴里进进出出。"));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.sob]因为强制口交变得模糊不清。[npc.Name]挣扎着，想挣脱[npc2.name]"
								+ "却被[npc2.her]抓回，只能感受着[npc2.clit+]在唇间进进出出。",
						"伴随着一声[npc.a_sob+]，[npc.Name]试着把头缩回来，"
								+ "喉咙深处爆发出声嘶力竭的抗议声，但[npc2.name]依然将自己[npc2.clit+]深深插在[npc.her]的喉咙里。",
						"[npc.Name]痛苦地[npc.sobbing]，挣扎着，想推开[npc2.name]，"
								+"却浑身无力，只能被[npc2.her]温柔地拉回来，感受着沾满口水和泪水[npc2.clit+]在自己嘴里进进出出。"));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction GIVING_CLIT_ORAL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "吸吮阴蒂";
		}

		@Override
		public String getActionDescription() {
			return "接着吮吸[npc2.namePos][npc2.clit+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]用[npc.lips+]包裹住[npc2.namePos][npc2.clit+]后，[npc.name]把头迅速地上下移动着，给[npc2.namePos]口交。",
					"随着一声含混不清的[npc.moan]，[npc.name]开始迅速地上下移动起[npc.her]的脑袋，"
							+ "用[npc.lips+]包裹住[npc2.namePos][npc2.clit+]后，[npc.name]把头上下移动着，给[npc2.namePos]口交。",
					"[npc.Name]迅速地把头上下移动，用[npc.her][npc.lips+]包裹住[npc2.namePos][npc2.clit+]，为[npc2.namePos]如饥似渴地口交。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_CLIT_ORAL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "吮吸阴蒂(迫切)";
		}

		@Override
		public String getActionDescription() {
			return "迫切地吸吮[npc2.namePos][npc2.clit+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"急切地用[npc.lips+]包裹住[npc2.namePos][npc2.clit+]后，[npc.name]把头迅速地上下移动着，热情地给[npc2.namePos]口交。",
					"随着一声含混不清的[npc.moan]，[npc.name]开始迅速地上下移动起[npc.her]的脑袋，"
							+ "贪婪地用[npc.lips+]包裹住[npc2.namePos][npc2.clit+]后，[npc.name]把头上下移动着，给[npc2.namePos]口交。",
					"迅速地把头上下移动，[npc.name]温柔地用[npc.her][npc.lips+]饥渴地包裹住[npc2.namePos][npc2.clit+]，为[npc2.namePos]如饥似渴地口交。"));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_CLIT_ORAL_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止吸吮阴蒂";
		}

		@Override
		public String getActionDescription() {
			return "把[npc2.namePos][npc2.clit+]吐出去，停止吮吸。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"粗暴地将[npc2.namePos][npc2.clit+]往喉咙最深处向下一压，[npc.name]接着把头拉了回来，快速地宣告了这次口交的结束。",

							"把[npc.face]猛然贴近[npc2.namePos]的下体，迫使[npc2.clit+]深深推入[npc.her]的喉咙，"
									+ "然后完全收回，让[npc2.herHim]从[npc.her]口中滑出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos][npc2.clit+]从自己口中抽出，[npc.she]发出一阵[npc.a_moan+]，结束了这次口交侍奉。",

							"[npc.name]发出[npc.a_moan+]，缩回头，把[npc2.namePos][npc2.clit+]从嘴里完全滑了出来。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]奋力反抗着[npc.Name]，发出一阵[npc2.a_sob+]，恳请[npc.name]放过自己。",
	
								"泪水从[npc2.namePos]的脸颊滚落，[npc2.she]努力挣扎反抗着[npc.namePos]的控制，发出[npc2.a_sob+]，乞求[npc.herHim]放过自己。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]，暴露了[npc2.her]希望[npc.Name]继续吮吸自己[npc.clit+]的欲望。",
	
								"[npc2.Name][npc2.moansVerb]，感受着[npc.name]从[npc2.her]腹股沟里拔出，努力抑制想用[npc.lips+]再次环住那[npc2.clit+]的欲望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
