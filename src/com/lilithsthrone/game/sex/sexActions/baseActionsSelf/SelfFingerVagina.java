package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
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
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfFingerVagina {
	
	public static final SexAction FINGER_INSEMINATION_ONGOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "手指授精(自己)";
		}
		@Override
		public String getActionDescription() {
			return "捞起你身上的精液并将其塞进你的小穴深处。";
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
						+ " 把[npc.her]的[npc.fingers]从[npc.her]的[npc.pussy+]里抽出来，[npc.she][npc.verb(reach)]向"+getRandomCharacterCumDescription(false)+"，它溅在[npc.her]的身上，然后舀起一些新鲜的精液。"
						+ "[npc.she]对收集到的液体量感到满意，便将[npc.fingers]伸回[npc.her]欲求不满的小穴。"
						+ "<br/>"
						+ "[npc.Name][npc.verb(grin)]，因为[npc.she][npc.verb(feel)]到"+getRandomCharacterCumDescription(false)+"沾在[npc.her]的手指上，正被深深推入[npc.her]的小穴，"
							+"[npc.she][npc.moansVerb+]着，用滑滑的精液作润滑，再次指交自己。"
						+ "[npc.name]将臀部抵在自己的[npc.hand]上，感觉到自己已被授精，发出了饥渴而颤抖的[npc.moan]。";
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
			return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_PREGNANCY, Fetish.FETISH_CUM_STUD, Fetish.FETISH_IMPREGNATION);
		}
	};
	public static final SexAction FINGER_INSEMINATION_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "手指授精(自己)";
		}
		@Override
		public String getActionDescription() {
			return "捞起你身上的精液并将其塞进你的小穴深处。";
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
					+ " 把[npc.her]的[npc.fingers]抬向"+getRandomCharacterCumDescription(false)+"，它溅在[npc.her]的身上，[npc.she][npc.verb(scoop)]起一些新鲜的精液。"
					+ "[npc.she]对收集到的液体量感到满意，便将[npc.fingers]伸入[npc.her]欲求不满的小穴。"
					+ "<br/>"
					+ "[npc.Name][npc.verb(grin)]，因为[npc.she][npc.verb(feel)]到"+getRandomCharacterCumDescription(false)+"沾在[npc.her]的手指上，正被深深推入[npc.her]的小穴，"
						+"[npc.she][npc.moansVerb+]着，用滑滑的精液作润滑，开始指交自己。"
					+ "[npc.name]将臀部抵在自己的[npc.hand]上，感觉到自己已被授精，发出了饥渴而颤抖的[npc.moan]。";
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
			return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_PREGNANCY, Fetish.FETISH_CUM_STUD, Fetish.FETISH_IMPREGNATION);
		}
	};
	
	public static final SexAction SELF_FINGER_VAGINA_SPREAD_PUSSY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "扒开小穴";
		}

		@Override
		public String getActionDescription() {
			return "用你的[npc.fingers]扒开小穴。";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)) {
				return (UtilText.returnStringAtRandom(
						"[npc.name]将[npc.fingers]伸回自己的[npc.legs]间，玩弄着[npc.pussy+]口，"
								+ "随后[npc.she]用指头扒开自己的阴唇，发出一阵[npc.a_moan+]。",
						"[npc.Name]把[npc.fingers]伸到[npc.legs]间向下摸索着，轻轻地[npc.moaning]并用两根指头扒开了[npc.her][npc.pussy+]。",
						"[npc.name]的指尖滑过[npc.her][npc.pussy+]，[npc.she]在稍微晃动屁股时发出了[npc.a_moan+]，接着用[npc.her]指头扒开了[npc.her]的阴唇。",
						"[npc.Name]急切地用[npc.her]的[npc.fingers]滑过[npc.her]欲求不满的[npc.pussy]，[npc.moaning+]着用[npc.her]的手指扒开[npc.her]柔软的褶皱。"));
			} else {
				return (UtilText.returnStringAtRandom(
						"[npc.name]将[npc.fingers]探向自己的[npc.legs]，用[npc.fingers]挑逗着[npc.pussy+]口，"
								+ "随后[npc.she]用指头扒开自己的阴唇，发出一阵[npc.a_moan+]。",
						"[npc.Name]把[npc.fingers]下伸到[npc.legs]间摸索着，轻轻地[npc.moaning]并用两根指头扒开了[npc.her][npc.pussy+]。",
						"[npc.name]将[npc.her]的指尖滑过[npc.her][npc.pussy+]，在用指尖扒开[npc.her]阴唇时发出了[npc.a_moan+]。",
						"[npc.Name]急切地用[npc.her]的[npc.fingers]滑过[npc.her]欲求不满的[npc.pussy]，[npc.moaning+]着用[npc.her]的手指扒开[npc.her]柔软的褶皱。"));
			}
		}

		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
	};
	
	public static final SexAction SELF_FINGER_VAGINA_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "指交[npc.herself]";
		}

		@Override
		public String getActionDescription() {
			return "开始指交[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return (UtilText.returnStringAtRandom(
					"[npc.name]将手伸到[npc.her]的[npc.legs]之间，手指抚弄着[npc.her][npc.pussy+]的入口，而后又将手指深入内部并发出[npc.a_moan+]。",
					"[npc.Name]把手指向下探入[npc.legs]间，轻轻地[npc.moaning]，并将两根手指插入[npc.her]那诱人[npc.pussy+]。",
					"[npc.name]的指尖滑过[npc.her]那被冷落的[npc.pussy]，[npc.she]发出一阵[npc.a_moan+]并把手指深入内部，开始指交自己。",
					"[npc.Name]急切地将[npc.her]的手指插进[npc.her]那欲求不满的[npc.pussy]，[npc.moaning+]着将[npc.her]的手指弯曲伸入[npc.herself]的体内并开始以“勾引人的”动作抚摸自己。"));
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_VAGINA_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "指交(温柔)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "温柔地指交[npc.her]那[npc.pussy+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]将[npc.fingers]慢慢向自己[npc.pussy+]深处推入，[npc.lips+]间溢出[npc.A_moan+]。",
					"[npc.name]用[npc.fingers]在[npc.her][npc.pussy+]里温柔地抽插，开始发出一连串愉悦的[npc.moans]，有节奏地指交自己。",
					"[npc.fingers]在[npc.pussy]里蜷起，[npc.name]开始抚摸自己的阴道壁，不自觉地发出呜咽声。",
					"[npc.Name]沉溺于腿间自我满足的愉悦中，[npc.tail]在[npc.her][npc.pussy+]内温柔地插进抽出。");
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "指交自慰";
		}

		@Override
		public String getActionDescription() {
			return "全神贯注地指交[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos][npc.lips+]间溢出[npc.a_moan+]，欲求不满地把[npc.fingers]往[npc.pussy+]更深处塞了塞。",
					"[npc.name]用[npc.fingers]在[npc.her][npc.pussy+]里抽插着，开始发出一连串愉悦的[npc.moans]，有节奏地指交自己。",
					"[npc.fingers]在[npc.pussy]里蜷起，[npc.name]开始抚摸自己的阴道壁，不自觉地发出[npc.a_moan]。",
					"[npc.Name]沉溺于腿间自我满足的愉悦中，[npc.fingers]插进抽出[npc.her][npc.pussy+]。");
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_VAGINA_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "指交(粗暴)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地指交[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]把[npc.fingers]深入那[npc.pussy+]，[npc.lips+]间发出[npc.moan]，然后粗暴地操起自己。",
					"[npc.name]用[npc.fingers]在[npc.her][npc.pussy+]里粗暴地抽插，开始发出一连串愉悦的[npc.moans]，无情地指交自己。",
					"[npc.fingers]在[npc.pussy]里强硬地蜷起，[npc.name]开始粗暴地抚摸自己的阴道壁，不自觉地发出[npc.a_moan]。", 
					"[npc.Name]沉溺于腿间自我满足的愉悦中，[npc.fingers]粗暴地操弄着[npc.her][npc.pussy+]。");
		}
	};
	
	public static final SexAction SUB_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return "指交自慰";
		}

		@Override
		public String getActionDescription() {
			return "全神贯注地指交[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos][npc.lips+]间溢出[npc.a_moan+]，欲求不满地把[npc.fingers]往[npc.pussy+]更深处塞了塞。",
					"[npc.name]用[npc.fingers]在[npc.her][npc.pussy+]里抽插着，开始发出一连串愉悦的[npc.moans]，有节奏地指交自己。",
					"[npc.fingers]在[npc.pussy]里蜷起，[npc.name]开始抚摸自己的阴道壁，不自觉地发出[npc.a_moan]。",
					"[npc.Name]沉溺于腿间自我满足的愉悦中，[npc.fingers]插进抽出[npc.her][npc.pussy+]。");
		}
	};
	
	public static final SexAction SUB_SELF_FINGER_VAGINA_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "指交(渴求)(自己)";
		}

		@Override
		public String getActionDescription() {
			return "指交自己(渴求)";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.namePos]急切地把[npc.fingers]深入那[npc.pussy+]，[npc.lips+]间发出[npc.moan]，然后狂乱地指交起自己。",
					"[npc.name]用[npc.fingers]在[npc.her][npc.pussy+]里热情地抽插着，开始发出一连串愉悦的[npc.moans]，疯狂地指交自己。",
					"[npc.fingers]在[npc.pussy]里迷乱地蜷起，[npc.name]开始急切地抚摸自己的阴道壁，不自觉地发出[npc.a_moan]。",
					"[npc.Name]沉溺于腿间自我满足的愉悦中，[npc.fingers]急切地操弄着[npc.her][npc.pussy+]。");
		}

	};
	
	public static final SexAction SELF_FINGER_VAGINA_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "停止指交(自己)";
		}

		@Override
		public String getActionDescription() {
			return "停止指交[npc.herself]。";
		}

		@Override
		public String getDescription() {
			return "[npc.Name]发出[npc.a_groan+]，手指从[npc.pussy+]里拔出。";
		}
	};
}
