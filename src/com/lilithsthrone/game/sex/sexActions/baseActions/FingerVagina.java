package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
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
 * @version 0.4.9.8
 * @author Innoxia
 */
public class FingerVagina {
	
	public static final SexAction FINGER_INSEMINATION_ONGOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "手指授精";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]捞起你身上的精液并将其塞进你的小穴深处。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTotalAmountCummedOn(Main.sex.getCharacterPerformingAction())>0
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()));
		}
		private String getRandomCharacterCumDescription(boolean withName) {
			Set<GameCharacter> charactersCummedOnPerformer = Main.sex.getAmountCummedOnByPartners(Main.sex.getCharacterPerformingAction()).keySet();
			
			GameCharacter character = Util.randomItemFrom(charactersCummedOnPerformer);
			
			if(character==Main.sex.getCharacterPerformingAction()) {
				return UtilText.parse(character, (withName?"[npc.her]自己的":"")+"[npc.cum+]");
			} else {
				return UtilText.parse(character, (withName?"[npc.namePos] ":"")+"[npc.cum+]");
			}
		}
		@Override
		public String getDescription() {
			return "[npc.name]感到"+getRandomCharacterCumDescription(true)+"从[npc.her][npc.skin]上滑落，[npc.her]的脑海里突然冒出一个有趣的想法。"
						+ "[npc.she]伸出手抓住[npc2.namePos][npc2.hand]，狞笑着从[npc.her][npc.pussy+]中拉出[npc2.her][npc2.fingers]。"
						+ "[npc.name]将[npc2.her]那[npc2.fingers]引导向喷射在[npc.her]身上的"+getRandomCharacterCumDescription(false)+"，舀起一些新鲜的种子汁。"
						+ "[npc.she]对收集到的液体量感到满意，便将[npc2.her]的[npc2.fingers]伸回了[npc.her]欲求不满的小穴。"
						+ "<br/>"
						+ "[npc.Name]咧嘴笑着，[npc.she]感觉到[npc2.namePos]手指上的"+getRandomCharacterCumDescription(false)+"被深深地送进了[npc.her]的小穴，"
							+"[npc2.name][npc.moansVerb+]着，用滑滑的精液作润滑，再次指交自己。"
						+ "[npc.name]将臀部抵在[npc2.namePos]的[npc2.hand]上，感觉到自己已被授精，发出了饥渴而颤抖的[npc.moan]。";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			Map<GameCharacter, Integer> cumProvidersToTotalCum = new HashMap<>();
			for(Entry<GameCharacter, Map<InventorySlot, Integer>> cumDetails : new HashMap<>(Main.sex.getAmountCummedOnByPartners(Main.sex.getCharacterPerformingAction())).entrySet()) {
				for(Entry<InventorySlot, Integer> areaDetails : cumDetails.getValue().entrySet()) {
					int amountOfCumUsed = Math.min(5, areaDetails.getValue());
					cumProvidersToTotalCum.putIfAbsent(cumDetails.getKey(), 0);
					cumProvidersToTotalCum.put(cumDetails.getKey(), cumProvidersToTotalCum.get(cumDetails.getKey())+amountOfCumUsed);
					Main.sex.incrementAmountCummedOn(cumDetails.getKey(), Main.sex.getCharacterPerformingAction(), areaDetails.getKey(), -amountOfCumUsed); // Remove the cum
				}
			}
			for(Entry<GameCharacter, Integer> e : cumProvidersToTotalCum.entrySet()) {
				sb.append(Main.sex.getCharacterPerformingAction().ingestFluid(e.getKey(), e.getKey().getCum(), SexAreaOrifice.VAGINA, e.getValue()));
			}
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character==Main.sex.getCharacterPerformingAction()) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_PREGNANCY);
			} else if(character==Main.sex.getCharacterTargetedForSexAction(this)) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD, Fetish.FETISH_IMPREGNATION);
			}
			return null;
		}
	};
	public static final SexAction FINGER_INSEMINATION_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "手指授精";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]捞起你身上的精液并将其塞进你的小穴深处。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTotalAmountCummedOn(Main.sex.getCharacterPerformingAction())>0
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()));
		}
		private String getRandomCharacterCumDescription(boolean withName) {
			Set<GameCharacter> charactersCummedOnPerformer = Main.sex.getAmountCummedOnByPartners(Main.sex.getCharacterPerformingAction()).keySet();
			
			GameCharacter character = Util.randomItemFrom(charactersCummedOnPerformer);
			
			if(character==Main.sex.getCharacterPerformingAction()) {
				return UtilText.parse(character, (withName?"[npc.her]自己的":"")+"[npc.cum+]");
			} else {
				return UtilText.parse(character, (withName?"[npc.namePos] ":"")+"[npc.cum+]");
			}
		}
		@Override
		public String getDescription() {
			return "[npc.name]感到"+getRandomCharacterCumDescription(true)+"从[npc.her][npc.skin]上滑落，[npc.her]的脑海里突然冒出一个有趣的想法。"
						+"接着坏笑着抓住[npc2.namePos]的[npc2.hand]。"
					+ "[npc.name]将[npc2.her]那[npc2.fingers]引导向喷射在[npc.her]身上的"+getRandomCharacterCumDescription(false)+"，舀起一些新鲜的种子汁。"
					+ "[npc.she]对收集到的液体量感到满意，便将[npc2.her]的[npc2.fingers]伸回了[npc.her]欲求不满的小穴。"
					+ "<br/>"
					+ "[npc.Name]咧嘴笑着，[npc.she]感觉到[npc2.namePos]手指上的"+getRandomCharacterCumDescription(false)+"被深深地送进了[npc.her]的小穴，"
						+"[npc2.name][npc.moansVerb+]着，用滑滑的精液作润滑，再次指交自己。"
					+ "[npc.name]将臀部抵在[npc2.namePos]的[npc2.hand]上，感觉到自己已被授精，发出了饥渴而颤抖的[npc.moan]。";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			Map<GameCharacter, Integer> cumProvidersToTotalCum = new HashMap<>();
			for(Entry<GameCharacter, Map<InventorySlot, Integer>> cumDetails : new HashMap<>(Main.sex.getAmountCummedOnByPartners(Main.sex.getCharacterPerformingAction())).entrySet()) {
				for(Entry<InventorySlot, Integer> areaDetails : cumDetails.getValue().entrySet()) {
					int amountOfCumUsed = Math.min(5, areaDetails.getValue());
					cumProvidersToTotalCum.putIfAbsent(cumDetails.getKey(), 0);
					cumProvidersToTotalCum.put(cumDetails.getKey(), cumProvidersToTotalCum.get(cumDetails.getKey())+amountOfCumUsed);
					Main.sex.incrementAmountCummedOn(cumDetails.getKey(), Main.sex.getCharacterPerformingAction(), areaDetails.getKey(), -amountOfCumUsed); // Remove the cum
				}
			}
			for(Entry<GameCharacter, Integer> e : cumProvidersToTotalCum.entrySet()) {
				sb.append(Main.sex.getCharacterPerformingAction().ingestFluid(e.getKey(), e.getKey().getCum(), SexAreaOrifice.VAGINA, e.getValue()));
			}
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character==Main.sex.getCharacterPerformingAction()) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_PREGNANCY);
			} else if(character==Main.sex.getCharacterTargetedForSexAction(this)) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD, Fetish.FETISH_IMPREGNATION);
			}
			return null;
		}
	};
	
	public static final SexAction STROKE_PUSSY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "轻抚小穴";
		}

		@Override
		public String getActionDescription() {
			return "向下探索[npc2.namePos]的[npc2.legs]之间并开始轻抚[npc2.her][npc2.pussy+]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.VAGINA)){

				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]探到[npc2.namePos][npc2.legs]间，[npc.fingers+]描摹着[npc2.namePos]的[npc2.topClothing(VAGINA)]，"
										+ "[npc.she]温柔地按下去，隔着[npc2.lowClothing(VAGINA)]摩擦着[npc2.namePos][npc2.pussy+]，发出一声轻柔的[npc.moan]。",

								"[npc.name]轻声[npc.moan]，探到[npc2.namePos][npc2.legs]间，"
										+ "[npc.she]温柔地伸出[npc.fingers]，隔着[npc2.namePos]的[npc2.lowClothing(VAGINA)]磨蹭[npc2.her][npc2.pussy+]。",

								"[npc.Name]用[npc.fingers+]在[npc2.namePos][npc2.legs]间缓缓挑逗着，[npc.she]温柔地向下压去，"
										+ "隔着[npc2.lowClothing(VAGINA)]摩擦着[npc2.namePos][npc2.pussy+]，发出轻柔的[npc.a_moan+]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]探到[npc2.namePos][npc2.legs]间，[npc.fingers+]描摹着[npc2.namePos]的[npc2.topClothing(VAGINA)]，"
										+ "[npc.she]急切地按下去，隔着[npc2.lowClothing(VAGINA)]摩擦着[npc2.namePos][npc2.pussy+]，发出[npc.a_moan+]。",

								"[npc.name]发出[npc.a_moan+]，探到[npc2.namePos][npc2.legs]之间，"
										+ "[npc.she]急切地伸出[npc.fingers]，隔着[npc2.namePos]的[npc2.lowClothing(VAGINA)]磨蹭[npc2.her][npc2.pussy+]。",

								"[npc.name]用[npc.fingers+]在[npc2.namePos][npc2.legs]间急切地挑逗着，[npc.she]开始向下压去，"
										+ "隔着[npc2.lowClothing(VAGINA)]摩擦着[npc2.namePos][npc2.pussy+]，发出[npc.a_moan+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]探到[npc2.namePos][npc2.legs]间，[npc.fingers+]粗暴地描摹着[npc2.namePos]的[npc2.topClothing(VAGINA)]，"
										+ "[npc.she]有力地按下去，隔着[npc2.lowClothing(VAGINA)]迅速地摩擦着[npc2.namePos][npc2.pussy+]，发出[npc.a_moan+]。",

								"[npc.name]发出[npc.a_moan+]，探到[npc2.namePos][npc2.legs]之间，"
										+ "[npc.she]粗暴地伸出[npc.fingers]，迅速地隔着[npc2.namePos]的[npc2.lowClothing(VAGINA)]磨蹭[npc2.her][npc2.pussy+]。",

								"[npc.name]用[npc.fingers+]在[npc2.namePos][npc2.legs]间粗暴地挑逗着，[npc.she]开始向下压去，"
										+ "隔着[npc2.lowClothing(VAGINA)]迅速地摩擦着[npc2.namePos][npc2.pussy+]，发出[npc.a_moan+]。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]探到[npc2.namePos][npc2.legs]间，[npc.fingers+]描摹着[npc2.namePos]的[npc2.topClothing(VAGINA)]，"
										+ "[npc.she]急切地按下去，隔着[npc2.lowClothing(VAGINA)]摩擦着[npc2.namePos][npc2.pussy+]，发出[npc.a_moan+]。",

								"[npc.name]发出[npc.a_moan+]，探到[npc2.namePos][npc2.legs]之间，"
										+ "[npc.she]急切地伸出[npc.fingers]，隔着[npc2.namePos]的[npc2.lowClothing(VAGINA)]磨蹭[npc2.her][npc2.pussy+]。",

								"[npc.name]用[npc.fingers+]在[npc2.namePos][npc2.legs]间急切地挑逗着，[npc.she]开始向下压去，"
										+ "隔着[npc2.lowClothing(VAGINA)]摩擦着[npc2.namePos][npc2.pussy+]，发出[npc.a_moan+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]探到[npc2.namePos][npc2.legs]间，[npc.fingers+]描摹着[npc2.namePos]的[npc2.topClothing(VAGINA)]，"
										+ "[npc.she]按下去，隔着[npc2.lowClothing(VAGINA)]摩擦着[npc2.namePos][npc2.pussy+]，发出[npc.a_moan+]。",

								"[npc.name]发出[npc.a_moan+]，探到[npc2.namePos][npc2.legs]之间，"
										+ "[npc.she]伸出[npc.fingers]，隔着[npc2.namePos]的[npc2.lowClothing(VAGINA)]磨蹭[npc2.her][npc2.pussy+]。",

								"[npc.Name]用[npc.fingers+]在[npc2.namePos][npc2.legs]间挑逗着，[npc.she]向下压去，"
										+ "隔着[npc2.lowClothing(VAGINA)]摩擦着[npc2.namePos][npc2.pussy+]，发出[npc.a_moan+]。"));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"伴随着[npc2.namePos][npc2.hips]的温柔起伏，一声轻柔的[npc2.moan]从[npc2.her][npc2.lips+]间飘出，"
											+ "[npc2.she]温柔地鼓励[npc.Name]继续爱抚[npc2.her][npc2.pussy+]。",
	
									"作为回应，[npc2.Name]开始温柔地摆动[npc2.her]的[npc2.hips]，"
											+ "温柔地将[npc2.herself]压到[npc.namePos][npc.fingers]上，[npc2.she]恳求[npc.herHim]继续。",
	
									"[npc2.Name]温柔地拱着[npc2.hips] ，抵住[npc.namePos]的[npc.fingers]，"
											+ "[npc2.she]享受着[npc.namePos]触摸[npc2.her][npc2.pussy+]，温柔地[npc2.moaning]。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]间飘出一阵[npc2.a_moan+]，开始急切地扭动着[npc2.hips]，"
											+ "[npc2.she]饥渴地鼓励[npc.Name]继续爱抚[npc2.her][npc2.pussy+]。",
	
									"作为回应，[npc2.Name]急切地上下扭动[npc2.her]的[npc2.hips]，"
											+ "急切地将[npc2.herself]压到[npc.namePos][npc.fingers]上，[npc2.she]恳求[npc.herHim]继续。",
	
									"[npc2.Name]急切地拱着[npc2.hips] ，抵住[npc.namePos]的[npc.fingers]，"
											+ "[npc2.she]享受着[npc.namePos]触摸[npc2.her][npc2.pussy+]，发出[npc2.moaning]。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]间飘出一阵[npc2.a_moan+]，开始猛烈地扭动着[npc2.hips]，"
											+ "[npc2.she]粗暴地命令[npc.Name]继续爱抚[npc2.her][npc2.pussy+]。",
	
									"作为回应，[npc2.Name]慌乱地上下扭[npc2.hips]，"
											+ "粗暴地将[npc2.herself]压到[npc.namePos][npc.fingers]上，[npc2.she]要求[npc.she]继续。",
	
									"[npc2.Name]激烈地拱着[npc2.hips] ，抵住[npc.namePos]的[npc.fingers]，"
											+ "[npc2.she]享受地用[npc2.pussy+]粗暴磨蹭着[npc.Name]，发出[npc2.moaning]。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]间飘出一阵[npc2.a_moan+]，开始急切地扭动着[npc2.hips]，"
											+ "[npc2.she]饥渴地鼓励[npc.Name]继续爱抚[npc2.her][npc2.pussy+]。",
	
									"作为回应，[npc2.Name]上下扭[npc2.hips]，"
											+ "急切地将[npc2.herself]压到[npc.namePos][npc.fingers]上，[npc2.she]恳求[npc.herHim]继续。",
	
									"[npc2.Name]急切地拱着[npc2.hips] ，抵住[npc.namePos]的[npc.fingers]，"
											+ "[npc2.she]享受着[npc.namePos]触摸[npc2.her][npc2.pussy+]，发出[npc2.moaning]。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]间飘出一阵[npc2.a_moan+]，将[npc2.hips]压向[npc.namePos]的[npc.hand]，"
											+ "[npc2.she]鼓励[npc.Name]继续爱抚[npc2.her][npc2.pussy+]。",
	
									"作为回应，[npc2.Name]上下扭[npc2.hips]，"
											+ "将[npc2.herself]压到[npc.namePos][npc.fingers]上，[npc2.she]恳求[npc.herHim]继续。",
	
									"[npc2.Name]拱着[npc2.hips] ，抵住[npc.namePos]的[npc.fingers]，"
											+ "[npc2.she]享受着[npc.namePos]触摸[npc2.her][npc2.pussy+]，发出[npc2.moaning]。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]将[npc2.hips]从[npc.namePos]的触摸下抽离，"
											+ "恳求[npc.name]停止触摸[npc2.herHim]。",
	
									"[npc2.Name]拉开[npc2.her][npc2.hips]作为回应，"
											+"[npc2.she]控制不住[npc2.a_sob+]，恳请[npc.name]放过自己。",
	
									"[npc2.name]努力把[npc.namePos]的[npc.fingers]从[npc2.hips]上挪开。"
											+"[npc2.she][npc2.sobbing]着请求[npc.Name]放过自己。"));
							break;
						default:
							break;
					}
				}
				
				return UtilText.nodeContentSB.toString();
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]探到[npc2.namePos][npc2.legs]间，[npc.fingers+]描摹着[npc2.her][npc2.pussy+]，"
										+ "开始温柔地爱抚[npc2.namePos][npc2.labia+]，发出一声轻柔的[npc.moan]。",

								"[npc.name]轻声[npc.moan]，探到[npc2.namePos][npc2.legs]间，"
										+ "[npc.she]温柔地用[npc.fingers]摸向[npc2.namePos][npc2.labia+]，轻抚[npc2.her][npc2.pussy+]。",

								"[npc.Name]用[npc.fingers+]在[npc2.namePos][npc2.legs]间缓缓挑逗着，[npc.she]温柔地向下压去，"
										+ "开始爱抚[npc2.namePos][npc2.pussy+]，发出一声轻柔的[npc.moan]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]探到[npc2.namePos][npc2.legs]间，[npc.fingers+]急切地描摹着[npc2.her][npc2.pussy+]，"
										+ "开始贪婪地爱抚[npc2.namePos][npc2.labia+]，发出[npc.a_moan+]。",

								"[npc.name]发出[npc.a_moan+]，探到[npc2.namePos][npc2.legs]之间，"
										+ "[npc.she]急切地用[npc.fingers]摸向[npc2.namePos][npc2.labia+]，轻抚[npc2.her][npc2.pussy+]。",

								"[npc.Name]用[npc.fingers+]在[npc2.namePos][npc2.legs]间挑逗着，[npc.she]急切地向下压去，"
										+ "开始愉悦地爱抚[npc2.namePos][npc2.pussy+]，发出[npc.a_moan+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]把手伸向[npc2.namePos][npc2.legs]间，粗暴地刺激[npc2.her][npc2.pussy+]，"
										+ "[npc.she]发出[npc.a_moan+]，加速按压着[npc2.namePos][npc2.labia+]。",

								"[npc.name]发出[npc.a_moan+]，探到[npc2.namePos][npc2.legs]之间，"
										+ "[npc.she]贪婪地用[npc.fingers]摸向[npc2.namePos][npc2.labia+]，粗暴地磨蹭[npc2.her][npc2.pussy+]。",

								"[npc.Name]粗暴地将[npc.fingers+]挤入[npc2.namePos][npc2.legs]间，粗鲁地压下去，"
										+ "发出[npc.a_moan+]，开始贪婪地爱抚[npc2.her][npc2.pussy+]。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]探到[npc2.namePos][npc2.legs]间，[npc.fingers+]急切地描摹着[npc2.her][npc2.pussy+]，"
										+ "开始贪婪地爱抚[npc2.namePos][npc2.labia+]，发出[npc.a_moan+]。",

								"[npc.name]发出[npc.a_moan+]，探到[npc2.namePos][npc2.legs]之间，"
										+ "[npc.she]急切地用[npc.fingers]摸向[npc2.namePos][npc2.labia+]，轻抚[npc2.her][npc2.pussy+]。",

								"[npc.Name]用[npc.fingers+]在[npc2.namePos][npc2.legs]间挑逗着，[npc.she]急切地向下压去，"
										+ "开始愉悦地爱抚[npc2.namePos][npc2.pussy+]，发出[npc.a_moan+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]探到[npc2.namePos][npc2.legs]间，[npc.fingers+]描摹着[npc2.her][npc2.pussy+]，"
										+ "开始爱抚[npc2.namePos][npc2.labia+]，发出[npc.a_moan+]。",

								"[npc.name]发出[npc.a_moan+]，探到[npc2.namePos][npc2.legs]之间，"
										+ "[npc.she]用[npc.fingers]摸向[npc2.namePos][npc2.labia+]，轻抚[npc2.her][npc2.pussy+]。",

								"[npc.Name]用[npc.fingers+]在[npc2.namePos][npc2.legs]间挑逗着，[npc.she]向下压去，"
										+ "开始爱抚[npc2.namePos][npc2.pussy+]，发出[npc.a_moan+]。"));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"伴随着[npc2.namePos][npc2.hips]的温柔起伏，一声轻柔的[npc2.moan]从[npc2.her][npc2.lips+]间飘出，"
											+ "[npc2.she]温柔地鼓励[npc.Name]继续爱抚[npc2.her][npc2.pussy+]。",
	
									"作为回应，[npc2.Name]开始温柔地摆动[npc2.her]的[npc2.hips]，"
											+ "温柔地将[npc2.herself]压到[npc.namePos][npc.fingers]上，[npc2.she]恳求[npc.herHim]继续。",
	
									"[npc2.Name]温柔地拱着[npc2.hips] ，抵住[npc.namePos]的[npc.fingers]，"
											+ "[npc2.she]享受着[npc.her]触摸[npc2.her][npc2.pussy+]，温柔地[npc2.moaning]。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]间飘出一阵[npc2.a_moan+]，开始急切地扭动着[npc2.hips]，"
											+ "[npc2.she]饥渴地鼓励[npc.Name]继续爱抚[npc2.her][npc2.pussy+]。",
	
									"作为回应，[npc2.Name]急切地上下扭动[npc2.her]的[npc2.hips]，"
											+ "急切地将[npc2.herself]压到[npc.namePos][npc.fingers]上，[npc2.she]恳求[npc.herHim]继续。",
	
									"[npc2.Name]急切地拱着[npc2.hips] ，抵住[npc.namePos]的[npc.fingers]，"
											+"[npc2.she]享受着[npc.name]刺激自己[npc2.pussy+]，[npc2.moaning+]出声。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]间飘出一阵[npc2.a_moan+]，开始猛烈地扭动着[npc2.hips]，"
											+ "[npc2.she]粗暴地命令[npc.Name]继续爱抚[npc2.her][npc2.pussy+]。",
	
									"作为回应，[npc2.Name]慌乱地上下扭[npc2.hips]，"
											+ "粗暴地将[npc2.herself]压到[npc.namePos][npc.fingers]上，[npc2.she]要求[npc.she]继续。",
	
									"[npc2.Name]激烈地拱着[npc2.hips] ，抵住[npc.namePos]的[npc.fingers]，"
											+"[npc2.she]享受着[npc.name]粗暴地刺激自己[npc2.pussy+]，[npc2.moaning+]出声。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]间飘出一阵[npc2.a_moan+]，开始急切地扭动着[npc2.hips]，"
											+ "[npc2.she]饥渴地鼓励[npc.Name]继续爱抚[npc2.her][npc2.pussy+]。",
	
									"作为回应，[npc2.Name]急切地上下扭动[npc2.her]的[npc2.hips]，"
											+ "急切地将[npc2.herself]压到[npc.namePos][npc.fingers]上，[npc2.she]恳求[npc.herHim]继续。",
	
									"[npc2.Name]急切地拱着[npc2.hips] ，抵住[npc.namePos]的[npc.fingers]，"
											+"[npc2.she]享受着[npc.name]刺激自己[npc2.pussy+]，[npc2.moaning+]出声。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips+]间飘出一阵[npc2.a_moan+]，将[npc2.hips]压向[npc.namePos]的[npc.hand]，"
											+ "[npc2.she]鼓励[npc.Name]继续爱抚[npc2.her][npc2.pussy+]。",
	
									"作为回应，[npc2.Name]上下扭[npc2.hips]，"
											+ "将[npc2.herself]压到[npc.namePos][npc.fingers]上，[npc2.she]恳求[npc.herHim]继续。",
	
									"[npc2.Name]拱着[npc2.hips] ，抵住[npc.namePos]的[npc.fingers]，"
											+"[npc2.she]享受着[npc.name]刺激自己[npc2.pussy+]，[npc2.moaning+]出声。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]将[npc2.hips]从[npc.namePos]的触摸下抽离，"
											+ "恳求[npc.name]停止触摸[npc2.herHim]。",
	
									"[npc2.Name]拉开[npc2.her][npc2.hips]作为回应，"
											+"[npc2.she]控制不住[npc2.a_sob+]，恳请[npc.name]放过自己。",
	
									"[npc2.name]努力把[npc.namePos]的[npc.fingers]从[npc2.hips]上挪开。"
											+"[npc2.she][npc2.sobbing]着恳求[npc.name]放过自己。"));
							break;
						default:
							break;
					}
				}
				
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.VAGINA)) {
				Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
			}
		}
	};
	
	public static final SexAction FINGERING_PROSTATE_MASSAGE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo();
		}
		
		@Override
		public String getActionTitle() {
			return "前列腺按摩";
		}

		@Override
		public String getActionDescription() {
			return "你将[npc.fingers]伸进[npc2.namePos][npc2.pussy+]，开始抚摸[npc2.her]的前列腺。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]温柔地把手掌伸向[npc2.namePos]的[npc2.labia+]，"
								+"把手指伸入[npc2.name][npc2.pussy]，温柔地按压摩擦着子宫。",
							"[npc.Name]轻叹出声，温柔地不断把[npc.fingers+]深入[npc2.namePos][npc2.pussy+]，"
								+ "然后将它们屈起，开始有节奏地爱抚和按摩[npc2.her]的前列腺。",
							"[npc.name]慢慢地将[npc.fingers+]尽可能深地插入[npc2.namePos][npc2.pussy+]，"
									+ "[npc.she]卷曲手指，开始温柔地按摩[npc2.namePos]的前列腺，轻轻地发出[npc.a_moan+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地把手掌怼向[npc2.namePos]的[npc2.labia+]，"
								+"把手指使劲塞进[npc2.name][npc2.pussy]，粗鲁地按压刺激着子宫。",
							"[npc.Name]喟叹出声，使劲把[npc.fingers+]塞进[npc2.namePos][npc2.pussy+]深处，"
								+ "然后将它们屈起，开始积极地爱抚和按摩前列腺。",
							"[npc.name]使劲把[npc.fingers+]尽可能深塞入[npc2.namePos][npc2.pussy+]，"
									+ "[npc.her]卷曲手指，开始粗暴地按摩[npc2.namePos]的前列腺，发出一阵[npc.a_moan+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]把手掌伸向[npc2.namePos][npc2.labia+]，"
								+"[npc.her]用力地把手指塞进[npc2.name][npc2.pussy]深处，粗鲁地按压刺激着子宫。",
							"[npc.Name]发出[npc.a_moan+]，把手指伸入[npc2.namePos][npc2.pussy+]深处，"
								+ "然后将它们屈起，开始爱抚和按摩前列腺。",
							"[npc.name]将[npc.fingers+]尽可能深地插入[npc2.namePos][npc2.pussy+]，"
									+ "[npc.her]卷曲手指，开始按摩[npc2.namePos]的前列腺，发出一阵[npc.a_moan+]。"));
					break;
				default: // Normal dom and eager sub:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.her]急切地将手推向[npc2.namePos][npc2.labia+]，"
								+ "[npc.name]将[npc.fingers]滑入[npc2.her]的[npc2.pussy]深处，然后屈起手指，开始迅速地爱抚和按摩前列腺。",
							"伴随着[npc.a_moan+]，[npc.name]急切地用[npc.fingers+]尽可能深入[npc2.namePos][npc2.pussy+]，"
								+ "然后将它们屈起，开始急切地爱抚和按摩前列腺。",
							"[npc.name]兴奋地将[npc.fingers+]尽可能深地伸入[npc2.namePos][npc2.pussy+]，"
									+ "[npc.name]卷曲手指，开始大力地按摩[npc2.her]的前列腺，发出一阵[npc.a_moan+]。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]受到突如其来的刺激，不由得发出[npc2.a_moan+]，温柔地晃动[npc2.labia+]迎合着[npc.namePos]的[npc.hand]。",
								"瞬息之间，[npc2.name]不由自主发出[npc2.a_moan+]，想得到更多，[npc2.her]再次顶腰以迎合[npc.namePos]的触碰。",
								"[npc2.name]发出了一阵[npc2.a_moan+]，温柔地将[npc2.her]的[npc2.labia+]推向[npc.namePos]的[npc.hand]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]受到突如其来的刺激，不由得发出[npc2.a_moan+]，粗暴地晃动[npc2.labia+]迎合着[npc.namePos]的[npc.hand]。",
								"瞬息之间，[npc2.name]不由自主发出[npc2.a_moan+]，[npc2.she]用力地挺动[npc2.labia+]，迎合着[npc.namePos]的触摸。",
								"[npc2.name]发出了[npc2.a_moan+]，使劲将[npc2.labia+]冲向[npc.namePos]的[npc.hand]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]受到突如其来的刺激，不由得发出[npc2.a_moan+]，晃动着[npc2.labia+]迎合[npc.namePos]的[npc.hand]。",
								"瞬息之间，[npc2.name]不由自主发出[npc2.a_moan+]，渴求更多，[npc2.her]扭腰以撞上了[npc.namePos]的触碰。",
								"[npc2.name]发出了一阵[npc2.a_moan+]，将[npc2.her]的[npc2.labia+]推向[npc.namePos]的[npc.hand]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]受到强加的刺激，无法自制地发出[npc2.a_moan+]，绝望地尝试将[npc2.labia+]从[npc.namePos]的[npc.hand]边挪开。",
								"瞬息之间，[npc2.name]不情愿地发出[npc2.a_moan+]，尽管身体叫嚣着更多，[npc2.her]绝望地试图把腰从[npc.namePos]的触碰中拉开。",
								"[npc2.name]发出了[npc2.a_moan+]，拼命地企图将[npc2.labia+]远离[npc.namePos]的[npc.hand]。"));
						break;
					default: // Normal dom and eager sub:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]受到突如其来的刺激，不由得发出[npc2.a_moan+]，晃动着[npc2.labia+]迎合[npc.namePos]的[npc.hand]。",
								"瞬息之间，[npc2.name]不由自主发出[npc2.a_moan+]，渴求更多，[npc2.her]急切地扭腰，迎向[npc.namePos]的触碰。",
								"[npc2.name]发出了一阵[npc2.a_moan+]，拼命地将[npc2.her]的[npc2.labia+]推向[npc.namePos]的[npc.hand]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "指交[npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.fingers]插进[npc2.namePos][npc2.pussy+]并开始扣[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.fingers+]缓慢地挑逗着[npc2.namePos]的[npc2.labia]，将手指缓慢地没入[npc2.her][npc2.pussy+]，发出一阵小小地[npc.a_moan+]。",

							"[npc.Name]将[npc.her][npc.fingers+]压入[npc2.namePos][npc2.legs+]间，"
									+ "[npc.she]以缓慢而稳定的力度，轻柔地将指部深深地插入[npc2.namePos][npc2.pussy+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.fingers+]急切地挑逗着[npc2.namePos]的[npc2.labia]，将手指贪婪地没入[npc2.her][npc2.pussy+]，发出一阵[npc.a_moan+]。",

							"[npc.Name]将[npc.her][npc.fingers+]急切地压入[npc2.namePos][npc2.legs+]间，"
									+ "[npc.she]以难以撼动的推力，贪婪地将指部插进[npc2.namePos][npc2.pussy+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]发出[npc.a_moan+]，用[npc.fingers+]粗暴地挤压着[npc2.namePos][npc2.labia+]，接着用力将手指捅入[npc2.her][npc2.pussy+]。",

							"[npc.Name][npc.fingers+]粗鲁地在[npc2.namePos][npc2.legs+]间游走，"
									+ "用力前推，贪婪地将指部深深插入[npc2.her][npc2.pussy+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.fingers+]急切地挑逗着[npc2.namePos]的[npc2.labia]，将手指贪婪地没入[npc2.her][npc2.pussy+]，发出一阵[npc.a_moan+]。",

							"[npc.Name]将[npc.her][npc.fingers+]急切地压入[npc2.namePos][npc2.legs+]间，"
									+ "[npc.she]以难以撼动的推力，贪婪地将指部插进[npc2.namePos][npc2.pussy+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.fingers+]挑逗着[npc2.namePos]的[npc2.labia]，将手指没入[npc2.her][npc2.pussy+]，发出一阵[npc.a_moan+]。",

							"[npc.Name]将[npc.her][npc.fingers+]压入[npc2.namePos][npc2.legs+]间，然后轻轻一推，就把[npc.her]的手指插入了[npc2.her][npc2.pussy+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.namePos]的[npc.fingers]进入了自己的身体，不禁漏出轻柔的[npc2.a_moan+]，"
										+ "[npc2.she]温柔地将[npc2.hips]压向[npc.namePos]的[npc.hand]，让[npc.namePos]的[npc.fingers]更加深入自己[npc2.pussy+]。",
	
								"[npc2.name]轻柔地[npc2.moan]，开始温柔地将[npc2.hips]压向[npc.namePos]的[npc.hand]，"
										+ "鼓励[npc.name]将[npc.fingers]更深地插入[npc2.her][npc2.pussy+]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.namePos]的[npc.fingers]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.she]急切地将[npc2.hips]压向[npc.namePos]的[npc.hand]，热切地让[npc.namePos]的[npc.fingers]更加深入自己[npc2.pussy+]。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地将[npc2.hips]压向[npc.namePos]的[npc.hand]，"
										+ "饥渴地鼓励[npc.Name]将[npc.fingers]更深地插入[npc2.her][npc2.pussy+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.namePos]的[npc.fingers]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.she]将[npc2.hips]激烈地压向[npc.namePos]的[npc.hand]，粗暴地强迫[npc.namePos]的[npc.fingers]更加深入自己[npc2.pussy+]。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始猛烈地将[npc2.ass]压向[npc.namePos]的[npc.hand]，"
										+ "粗暴地强迫[npc.name]将[npc.fingers]更深地插入[npc2.her][npc2.pussy+]。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.namePos]的[npc.fingers]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.she]急切地将[npc2.hips]压向[npc.namePos]的[npc.hand]，热切地让[npc.namePos]的[npc.fingers]更加深入自己[npc2.pussy+]。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始急切地将[npc2.hips]压向[npc.namePos]的[npc.hand]，"
										+ "饥渴地鼓励[npc.Name]将[npc.fingers]更深地插入[npc2.her][npc2.pussy+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.namePos]的[npc.fingers]进入了自己的身体，不禁漏出一声[npc2.a_moan+]，"
										+ "[npc2.she]将[npc2.hips]压向[npc.namePos]的[npc.hand]，让[npc.namePos]的[npc.fingers]更加深入自己[npc2.pussy+]。",
	
								"伴随着一声[npc2.a_moan+]，[npc2.name]开始将[npc2.hips]压向[npc.namePos]的[npc.hand]，"
										+ "鼓励[npc.name]将[npc.fingers]更深地插入[npc2.her][npc2.pussy+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]感受到[npc.namePos]的[npc.fingers]进入了自己的身体，不禁漏出一声[npc2.a_sob+]，"
										+ "[npc2.she]徒劳地挣扎扭身，试图让[npc2.hips]远离讨厌的插入物，不断[npc2.sobbing]着反抗。",
	
								"[npc2.name]发出[npc2.a_sob+]，徒劳地挣扎扭身，试图拔出讨厌的插入物，"
										+ "[npc2.sobbing]并挣扎抵抗着[npc.name]，因为[npc.her]正试图将[npc.fingers]深入自己[npc2.pussy+]。"));
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
							"作为回应，[npc2.name]急切地挺起[npc2.hips]，"
									+ "[npc2.she]发出一声愉悦的[npc2.moan]，热切地乞求着[npc.Name]继续指交[npc2.herHim]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一声愉悦的[npc2.moan]，"
									+"接着饥渴地配合着[npc.name]的节奏晃动着腰，乞求[npc.her]继续。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地磨蹭[npc.namePos]的[npc.hand]，"
									+ "做出有助于[npc.fingers]更深地插入[npc2.her][npc2.pussy+]的动作，急切地乞求[npc.Name]继续。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]一直尝试着收缩[npc2.pussy]来摆脱[npc.namePos]讨厌的触碰，"
									+ "[npc2.name]无力地反抗着[npc.namePos]插进来的[npc.fingers]，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然继续指交[npc2.her][npc2.pussy+]。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试不让[npc.name]触碰自己，"
									+ "但[npc.name]还是将[npc.fingers+]插入了[npc2.her][npc2.pussy+]，[npc2.she]奋力反抗着[npc.name]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]慢慢地将[npc2.hips]压向[npc.namePos]的[npc.hand]作为回应，"
								+ "[npc2.she]发出一声轻柔的[npc2.moan]，开始温柔地乞求[npc.Name]继续指交[npc2.herHim]。",
	
						" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
								+ "[npc2.she]温柔地将[npc2.hips]压向[npc.NamePos]的[npc.hand]，乞求[npc.Name]继续指交[npc2.herHim]。",
	
						"[npc2.name]愉悦地[npc2.moaning]着，缓缓地磨蹭[npc.namePos]的[npc.hand]，"
								+ "做出有助于[npc.fingers]更深地插入[npc2.her][npc2.pussy+]的动作，柔和地[npc2.moaning]着让[npc.Name]继续。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.Name]粗暴地将[npc2.hips]压向[npc.namePos]的[npc.hand]，"
									+ "[npc2.she]粗鲁地命令[npc.name]继续指交[npc2.herHim]，发出一阵[npc2.a_moan+]。",
	
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]粗鲁地将[npc2.hips]压向[npc.NamePos]的[npc.hand]，专横地命令[npc.Name]继续指交[npc2.herHim]。",
	
							"[npc2.Name]粗鲁地摩擦着[npc.namePos]的[npc.hand]，"
									+ "强迫[npc.name]将[npc.fingers]深深插入[npc2.her][npc2.pussy+]，命令[npc.name]继续。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.hips]压向[npc.namePos]的[npc.hand]作为回应，"
									+ "[npc2.she]乞求[npc.name]继续指交[npc2.herHim]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "接着，渴求更多的触碰，[npc2.name]扭动着腰，祈求[npc.name]继续用手指玩弄[npc2.herHim]。",
		
							"[npc2.name][npc2.moaning+]着，开始磨蹭[npc.namePos]的[npc.hand]，"
									+ "做出有助于[npc.fingers]更深地插入[npc2.her][npc2.pussy+]的动作，乞求[npc.Name]继续。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "指交(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地指交[npc2.namePos][npc2.pussy+]。";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]温柔地将[npc.fingers+]插入[npc2.namePos][npc2.pussy+]，[npc.she]弯起手指按摩阴道壁，缓缓地指交[npc2.name]。",

					"[npc.name]缓缓地用[npc.hand]摸向[npc2.namePos]的腹股沟，"
							+ "开始温柔地将[npc.fingers+]深深插入[npc2.namePos][npc2.pussy+]，[npc.she]温柔地指交[npc2.name]，不禁漏出一小声[npc.moan]。",

					"[npc.name]轻声[npc.moan]，伸手探向[npc2.namePos]腿间，缓缓把[npc.fingers+]探入[npc2.her][npc2.pussy+]内。"));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "指交";
		}

		@Override
		public String getActionDescription() {
			return "继续指交[npc2.namePos][npc2.pussy+]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.fingers+]深深插入[npc2.namePos][npc2.pussy+]，然后将指部弯起，"
							+ "开始按摩阴道壁，[npc.she]热切地指交着[npc2.name]。",

					"[npc.Name]把手伸向[npc2.namePos]的小腹，急切地把[npc.fingers+]伸入[npc2.pussy+]深处，"
							+ "开始迅速地指交[npc2.herHim]，不禁发出[npc.a_moan+]。",

					"将[npc.her][npc.hand]急切地压入[npc2.namePos][npc2.legs+]间，"
							+ "发出[npc.a_moan+]，然后亢奋地将[npc.her][npc.fingers+]深深插入[npc2.her][npc2.pussy+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "指交(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地指交[npc2.namePos][npc2.pussy+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地把[npc.fingers+]深入[npc2.namePos][npc2.pussy+]里，然后屈指，"
							+ "开始粗暴地按摩阴道壁，[npc.she]无情地指交着[npc2.name]。",

					"[npc.Name]粗暴地把手伸向[npc2.namePos]小腹，接着用力把[npc.fingers+]捅入[npc2.pussy+]深处，"
							+ "开始粗暴地指交[npc2.herHim]，不禁发出[npc.a_moan+]。",

					"将[npc.her][npc.hand]激烈地压入[npc2.namePos][npc2.legs+]间，"
							+ "[npc.name]发出[npc.a_moan+]，粗暴地把[npc.fingers+]捅入[npc2.name][npc2.pussy+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "指交";
		}

		@Override
		public String getActionDescription() {
			return "继续指交[npc2.namePos][npc2.pussy+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]将[npc.fingers+]插入[npc2.namePos][npc2.pussy+]，在里面弯起手指，开始按摩阴道壁，集中精力指交[npc2.name]。",

					"[npc.name]用[npc.hand]摸向[npc2.namePos]的下体，将[npc.fingers+]深深插入[npc2.namePos][npc2.pussy+]，"
							+ "开始集中精神指交[npc2.herHim]，不禁发出[npc.a_moan+]。",

					"[npc.Name]发出[npc.a_moan+]，把手伸向[npc2.namePos]腿间，用[npc.fingers+]不断探索[npc2.pussy+]深处。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "指交(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地指交[npc2.namePos][npc2.pussy+]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.fingers+]深深插入[npc2.namePos][npc2.pussy+]，然后将指部弯起，"
							+ "开始按摩阴道壁，[npc.she]热切地指交着[npc2.name]。",

					"[npc.Name]把手伸向[npc2.namePos]的小腹，急切地把[npc.fingers+]伸入[npc2.pussy+]深处，"
							+ "开始迅速地指交[npc2.herHim]，不禁发出[npc.a_moan+]。",
					
					"将[npc.her][npc.hand]急切地压入[npc2.namePos][npc2.legs+]间，"
							+ "发出[npc.a_moan+]，然后亢奋地将[npc.her][npc.fingers+]深深插入[npc2.her][npc2.pussy+]。"));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};

	public static final SexAction FINGERING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗指交";
		}

		@Override
		public String getActionDescription() {
			return "尝试把你的[npc.fingers]从[npc2.namePos][npc2.vagina+]中拔出。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
									+"[npc.her]呜咽着，努力想要把手指从[npc2.namePos][npc2.vagina+]中抽出，但浑身无力。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将自己[npc.fingers+]从[npc2.namePos][npc2.vagina+]中抽离。",

							"[npc.name]拼命地尝试把自己[npc.fingers+]从[npc2.namePos][npc2.vagina+]中抽离，"
									+ "[npc2.name]牢牢控制住[npc.namePos]的[npc.hand]，[npc.Name]绝望地[npc.sob]着，"
									+ "然后[npc2.she]温柔地将自己的[npc2.hips]向后压，强迫[npc.namePos]的[npc.fingers]深深插入[npc2.her][npc2.pussy+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
									+"[npc.her]呜咽着，努力想要把手指从[npc2.namePos][npc2.vagina+]中抽出，但浑身无力。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将自己[npc.fingers+]从[npc2.namePos][npc2.vagina+]中抽离。",

							"[npc.name]拼命地尝试把自己[npc.fingers+]从[npc2.namePos][npc2.vagina+]中抽离，"
									+ "[npc2.name]牢牢控制住[npc.namePos]的[npc.hand]，[npc.Name]绝望地[npc.sob]着，"
									+ "[npc2.she]急切地扭动[npc2.hips+]，强迫[npc.namePos]的[npc.fingers]深入[npc2.her][npc2.pussy+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]再也抑制不住泪水，眼泪夺眶而出，"
									+"[npc.her]呜咽着，努力想要把手指从[npc2.namePos][npc2.vagina+]中抽出，但浑身无力。",

							"[npc.name]发出一阵[npc.a_sob+]，拼命地尝试将自己[npc.fingers+]从[npc2.namePos][npc2.vagina+]中抽离。",

							"[npc.name]拼命地尝试把自己[npc.fingers+]从[npc2.namePos][npc2.vagina+]中抽离，"
									+ "[npc2.name]牢牢控制住[npc.namePos]的[npc.hand]，[npc.Name]绝望地[npc.sob]着，"
									+ "接着粗暴地顶[npc2.hips+]，强迫[npc.name]把手指插入自己[npc2.pussy+]。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FINGERING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止指交";
		}

		@Override
		public String getActionDescription() {
			return "把你的[npc.fingers]从[npc2.namePos][npc2.pussy+]拔出去，停止指交[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗暴地将[npc.fingers]从[npc2.namePos][npc2.pussy+]中拉出，"
									+ "狠狠地捏了一下[npc2.her][npc2.clit+]，然后将[npc.hand]从[npc2.her]的下体挪开。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.fingers]从[npc2.her][npc2.pussy+]中猛抽出来，结束了粗暴指交。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.fingers]从[npc2.namePos][npc2.pussy+]中抽出，"
									+ "轻轻地捏了一下[npc2.her][npc2.clit+]，然后将[npc.hand]从[npc2.her]的下体挪开。",

							"[npc.name]最后一次深深插入[npc2.name]，然后将[npc.fingers]从[npc2.her][npc2.pussy+]中拔出来，结束了指交。"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]的[npc2.pussy]因为抽出手指的刺激产生快感，使得[npc2.name]喘气连连。稍微缓了一下，[npc2.she]继续啜泣着挣扎抵抗。",

							"[npc.Name]强行从[npc2.pussy+]抽出手指，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc.fingers+]从[npc2.namePos][npc2.pussy+]里拔出，[npc2.Name]发出一阵[npc2.a_moan+]。",

							"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]渴望得到[npc.namePos]的更多关注。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	public static final SexAction FINGERED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "开始指交";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]开始指交你[npc.pussy+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓紧[npc2.namePos]的[npc2.hand]，温柔地引导[npc2.her]探索自己的[npc.labia+]，"
									+"接着[npc2.her][npc.moan]着，把[npc2.name]的手指推入自己[npc.pussy+]。",

							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，引导着[npc2.her]的[npc2.fingers]滑向自己[npc.legs+]之间，并缓慢地摩擦了起来，"
									+"[npc.name]温柔地把[npc2.her][npc2.name]的手指推入自己[npc.pussy+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢抓住[npc2.namePos]的[npc2.hand]，急切地将[npc2.namePos]那[npc2.fingers]引导到自己[npc.labia+]，"
									+ "发出[npc.a_moan+]，贪婪地将[npc2.namePos]的指部插入[npc.her][npc.pussy+]。",

							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，引导着[npc2.her]的[npc2.fingers]滑向自己[npc.legs+]之间，"
									+ "急切地压力下，[npc.name]贪婪地将[npc2.her]的手指引向[npc.her][npc.pussy+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢钳制住[npc2.namePos]的[npc2.hand]，用[npc2.namePos]那[npc2.fingers]磨蹭自己[npc.labia+]，"
									+ "发出[npc.a_moan+]，粗暴地将[npc2.namePos]的指部插入[npc.her][npc.pussy+]。",

							"[npc.name]抓住[npc2.namePos]的[npc2.hand]，激烈地将[npc2.namePos]那[npc2.fingers]拉向自己[npc.legs+]之间，"
									+ "然后霸道地猛推，[npc.she]粗暴地将[npc2.namePos]的指部插进[npc.her][npc.pussy+]。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢抓住[npc2.namePos]的[npc2.hand]，急切地将[npc2.namePos]那[npc2.fingers]引导到自己[npc.labia+]，"
									+ "发出[npc.a_moan+]，贪婪地将[npc2.namePos]的指部插入[npc.her][npc.pussy+]。",

							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，引导着[npc2.her]的[npc2.fingers]滑向自己[npc.legs+]之间，"
									+ "急切地压力下，[npc.name]贪婪地将[npc2.her]的手指引向[npc.her][npc.pussy+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]牢牢抓住[npc2.namePos]的[npc2.hand]，将[npc2.namePos]那[npc2.fingers]引导到自己[npc.labia+]，"
									+ "发出[npc.a_moan+]，将[npc2.namePos]的指部插入[npc.her][npc.pussy+]。",

							"[npc.Name]抓住[npc2.namePos]的[npc2.hands]，引导着[npc2.her]的[npc2.fingers]滑向自己[npc.legs+]之间，"
									+ "急切地压力下，[npc.name]将[npc2.her]的手指引向[npc.her][npc.pussy+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.name]的身体，不禁发出轻柔的[npc2.moan]，然后弯起[npc2.fingers]，开始温柔地指交[npc.her][npc.pussy+]。",
	
								"[npc2.name]轻柔地[npc2.moan]着，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]温柔地按摩[npc.NamePos]的阴道壁，开始指交[npc.NamePos][npc.pussy+]。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.name]的身体，不禁发出一阵[npc2.a_moan+]，然后弯起[npc2.fingers]，开始急切地指交[npc.her][npc.pussy+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]急切地按摩[npc.NamePos]的阴道壁，开始指交[npc.NamePos][npc.pussy+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出[npc2.a_moan+]，进入了[npc.Name]的身体，为了提醒[npc.herHim]谁才是主导者，"
										+"[npc2.name]粗暴地把手指推入[npc.her][npc.pussy+]，屈指，开始指交。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]无情地指交[npc.namePos][npc.pussy+]，宣告着自己的支配权。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.name]的身体，不禁发出一阵[npc2.a_moan+]，然后弯起[npc2.fingers]，开始急切地指交[npc.her][npc.pussy+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]急切地按摩[npc.NamePos]的阴道壁，开始指交[npc.NamePos][npc.pussy+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]进入[npc.name]的身体，不禁发出一阵[npc2.a_moan+]，然后弯起[npc2.fingers]，开始指交[npc.her][npc.pussy+]。",
	
								"[npc2.name]发出一阵[npc2.a_moan+]，将[npc2.fingers+]在[npc.Name]体内弯起，"
										+ "[npc2.she]按摩着[npc.NamePos]的阴道壁，开始指交[npc.NamePos][npc.pussy+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]紧紧裹住[npc2.name]那[npc2.fingers]，[npc2.her]不禁发出一声[npc2.a_sob+]，"
										+ "[npc2.she]挣扎着，想要从[npc.namePos]的紧握中抽出自己的[npc2.hand]。",
	
								"[npc2.name]发出[npc2.a_sob+]，挣扎着从[npc.namePos]的紧握中抽出手，"
										+ "[npc2.her]请求[npc.Name]不要再强迫自己把手指深入[npc.her][npc.pussy+]内。"));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "被指交(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地享受[npc2.namePos][npc2.fingers+]指交你[npc.pussy+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"温柔地在[npc2.namePos]的[npc2.hand]上蹭动[npc.hips]，"
							+"[npc.Name]轻轻地喘着气，配合[npc2.namePos]把[npc2.fingers+]伸入自己[npc.pussy+]。",

					"伴随着一声轻柔的[npc.moan]，[npc.Name]温柔地将[npc.hips]扭向[npc2.namePos]的[npc2.hand]，"
							+ "强迫[npc2.namePos][npc2.fingers+]在[npc.her][npc.pussy+]中插得更深。",

					"慢慢地把[npc.hips]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.namePos]缓缓喘气，抓着[npc2.namePos][npc2.fingers+]深入自己[npc.pussy+]。"));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "被指交";
		}

		@Override
		public String getActionDescription() {
			return "享受[npc2.namePos][npc2.fingers+]指交你[npc.pussy+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.hips]压向[npc2.namePos]的[npc2.hand]，"
							+ "竭力地让[npc2.namePos][npc2.fingers+]深深插入自己[npc.pussy+]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，竭力地将[npc.hips]扭向[npc2.namePos]的[npc2.hand]，"
							+ "强迫[npc2.namePos][npc2.fingers+]在[npc.her][npc.pussy+]中插得更深。",

					"[npc.name]将[npc.hips]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.fingers+]插入自己[npc.pussy+]深处。"));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "被指交(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地强迫[npc2.namePos][npc2.fingers+]深入你[npc.pussy+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"粗暴地把[npc.hips]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.Name]呼吸越来越重，强迫[npc2.namePos]把[npc2.fingers+]粗暴地捅入自己[npc.pussy+]深处。",

					"[npc.Name]发出一阵[npc.a_moan+]，强有力地将[npc.hips]扭向[npc2.namePos]的[npc2.hand]，"
							+ "强迫[npc2.namePos][npc2.fingers+]在[npc.her][npc.pussy+]中插得更深。",

					"[npc.name]粗暴地将[npc.hips]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.fingers+]插入自己[npc.pussy+]深处。"));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "被指交";
		}

		@Override
		public String getActionDescription() {
			return "享受[npc2.namePos][npc2.fingers+]指交你[npc.pussy+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.hips]压向[npc2.namePos]的[npc2.hand]，"
							+ "竭力地让[npc2.namePos][npc2.fingers+]深深插入自己[npc.pussy+]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，竭力地将[npc.hips]扭向[npc2.namePos]的[npc2.hand]，"
							+ "强迫[npc2.namePos][npc2.fingers+]在[npc.her][npc.pussy+]中插得更深。",

					"[npc.name]将[npc.hips]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.fingers+]插入自己[npc.pussy+]深处。"));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "被指交(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地将你的臀部压向[npc2.namePos]的[npc2.hand]，让[npc2.she]指交你[npc.pussy+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]急切地将[npc.hips]压向[npc2.namePos]的[npc2.hand]，"
							+ "竭力地让[npc2.namePos][npc2.fingers+]深深插入自己[npc.pussy+]，发出一阵[npc.a_moan+]。",

					"[npc.Name]发出一阵[npc.a_moan+]，竭力地将[npc.hips]扭向[npc2.namePos]的[npc2.hand]，"
							+ "强迫[npc2.namePos][npc2.fingers+]在[npc.her][npc.pussy+]中插得更深。",

					"[npc.name]将[npc.hips]顶向[npc2.namePos]的[npc2.hand]，"
							+ "[npc.lips+]间迸发出一阵[npc.a_moan+]，[npc.her]设法让[npc2.namePos][npc2.fingers+]插入自己[npc.pussy+]深处。"));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗指交";
		}

		@Override
		public String getActionDescription() {
			return "努力让[npc2.namePos]的[npc2.fingers]远离你[npc.pussy+]。";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]的眼泪夺眶而出。"
									+"[npc.namePos]泪腺失控，喘息中夹带着哭声。[npc.she]想要把[npc2.namePos]的[npc2.fingers]拉出[npc.pussy+]，却无能为力。",

							"[npc.namePos]呜咽着，拼命地扭腰想要从[npc2.namePos]的触碰中逃脱，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.fingers+]依然温柔地在[npc.her][npc.pussy+]里滑进滑出。",

							"[npc.name]拼命地尝试把[npc.hips]从[npc2.namePos]的[npc2.hand]边挪开，"
									+ "[npc2.namePos][npc2.fingers+]依然温柔地滑进[npc.namePos][npc.pussy+]深处，使得[npc.Name]难以抑制地[npc.sob]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]的眼泪夺眶而出。"
									+"[npc.namePos]泪腺失控，喘息中夹带着哭声。[npc.she]想要把[npc2.namePos]贪婪探求的[npc2.fingers]拉出[npc.pussy+]，却无能为力。",

							"[npc.namePos]呜咽着，拼命地扭腰想要从[npc2.namePos]的触碰中逃脱，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.fingers+]依然急切地在[npc.her][npc.pussy+]里滑进滑出。",

							"[npc.name]拼命地尝试把[npc.hips]从[npc2.namePos]的[npc2.hand]边挪开，"
									+ "[npc2.namePos][npc2.fingers+]依然饥渴地插入[npc.namePos][npc.pussy+]深处，使得[npc.Name]难以抑制地[npc.sob]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]的眼泪夺眶而出。"
									+"[npc.namePos]泪腺失控，喘息中夹带着哭声。[npc.she]想要把[npc2.namePos]粗暴抽插的[npc2.fingers]拉出[npc.pussy+]，却无能为力。",

							"[npc.namePos]呜咽着，拼命地扭腰想要从[npc2.namePos]的触碰中逃脱，"
									+ "[npc.she]绝望地挣扎着，但[npc2.namePos][npc2.fingers+]依然粗暴地在[npc.her][npc.pussy+]里抽送爆操。",

							"[npc.name]拼命地尝试把[npc.hips]从[npc2.namePos]的[npc2.hand]边挪开，"
									+ "[npc2.namePos][npc2.fingers+]依然粗暴撞进[npc.namePos][npc.pussy+]深处，使得[npc.Name]难以抑制地[npc.sob]。"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止被指交";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]从你[npc.pussy+]拔出[npc2.her]的[npc2.fingers]。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]猛地将[npc2.namePos]那[npc2.fingers]从自己[npc.pussy+]里抽出，[npc.she]愤怒地咆哮着，命令[npc2.name]停止指交。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her][npc2.scent+]，然后猛地将[npc2.her]那[npc2.fingers]从自己[npc.pussy+]中抽出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos]那[npc2.fingers]从自己[npc.pussy+]里抽出，[npc.she]发出一阵[npc.a_moan+]，告诉[npc2.name]停止指交。",

							"[npc.Name]倚靠在[npc2.name]身上，呼吸着[npc2.her][npc2.scent+]，然后将[npc2.her]那[npc2.fingers]从自己[npc.pussy+]中抽出。"));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]松了一口气，但当[npc2.she]意识到[npc.name]还没完全满足时，又发出了一阵[npc2.a_sob+]。",

							"[npc2.name]发出一阵[npc2.a_sob+]，继续反抗并挣扎着，但[npc.name]依然牢牢地将[npc2.she]固定在原位。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]不再让[npc2.name]玩弄自己[npc.pussy+]，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",

							"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]想更多关注[npc.namePos][npc.pussy+]的渴望。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
