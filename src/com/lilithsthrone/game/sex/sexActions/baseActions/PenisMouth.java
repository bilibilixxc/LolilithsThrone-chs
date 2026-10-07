package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
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
 * @version 0.3.4.5
 * @author Innoxia
 */
public class PenisMouth {
	
	// -- Methods for multiple ongoing characters:
	
	private static List<GameCharacter> getOngoingCharacters(GameCharacter characterReceivingBlowjob) {
		return new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterReceivingBlowjob, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
	}

	private static List<GameCharacter> getMultipleOngoingCharactersForParsing(GameCharacter characterReceivingBlowjob) {
		List<GameCharacter> characters = Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
		for(GameCharacter c : getOngoingCharacters(characterReceivingBlowjob)) {
			if(!characters.contains(c)) {
				characters.add(c);
			}
		}
		return characters;
	}
	
	private static String getOngoingNames(GameCharacter characterReceivingBlowjob, GameCharacter... charactersToExclude) {
		List<String> names = new ArrayList<>();
		List<GameCharacter> exclusions = Arrays.asList(charactersToExclude);
		for(GameCharacter c : getOngoingCharacters(characterReceivingBlowjob)) {
			if(!exclusions.contains(c)) {
				names.add(UtilText.parse(c, "[npc.name]"));
			}
		}
		return Util.stringsToStringList(names, false);
	}
	
	public static GameCharacter getPrimaryBlowjobPerformer(GameCharacter characterReceivingBlowjob) {
		if(Main.sex.getOngoingActionsMap(characterReceivingBlowjob).get(SexAreaPenetration.PENIS).isEmpty()) {
			return null;
		}
		return Main.sex.getOngoingActionsMap(characterReceivingBlowjob).get(SexAreaPenetration.PENIS).keySet().iterator().next();
	}
	
	private static GameCharacter getSecondaryBlowjobPerformer(GameCharacter characterReceivingBlowjob) {
		if(Main.sex.getOngoingActionsMap(characterReceivingBlowjob).get(SexAreaPenetration.PENIS).size()<2) {
			return null;
		}
		return new ArrayList<>(Main.sex.getOngoingActionsMap(characterReceivingBlowjob).get(SexAreaPenetration.PENIS).keySet()).get(1);
	}
	
	// ---
	
	public static final SexAction COCK_SLAP = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);

			return Util.newArrayListOfValues(performer, primary, target);
		}
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "鸡巴掴";
		}

		@Override
		public String getActionDescription() {
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(Main.sex.getCharacterPerformingAction());
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			return "把[npc.cock]从[npc2.namePos]嘴里抽出来，然后用它扇"+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+"脸。";
		}

		@Override
		public String getDescription() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				return UtilText.returnStringAtRandom(
							"[npc.name]低头笑看着"+PenisMouth.getOngoingNames(performer)+"，稍一抽身就将那[npc.cock+]从[npc2.namePos]嘴里滑出。"
								+"[npc3.name]抬起头看向[npc.herHim]，[npc.she]用[npc.her]的坚硬肉棒啪啪啪地拍打[npc3.her]的脸颊，溅起淫靡的水花"
									+(Main.sex.hasLubricationTypeFromAnyone(target, SexAreaOrifice.MOUTH, LubricationType.PRECUM)?"缀满浓精的":"湿乎乎的")
									+"唾液从[npc3.her]脸上流过，随后[npc.her][npc.cock+]插入了[npc3.her]喉咙。",
	
							"[npc.Name]后退几步，从[npc2.namePos]的嘴中抽出[npc.her][npc.cock+]，"
									+ "然后继续用沾满口水的[npc1.cockHead]蹭着"+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+"的脸颊。"
									+ "[npc.Name]抓住了[npc3.name]因惊叫而张开嘴的机会，引导[npc.cock]的[npc.cockHead]撬开[npc3.her][npc3.lips+]，"
									+ "接着将它深深插入[npc3.her]的喉咙。",
	
							"[npc.Name]#IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF退后，允许[npc.her][npc.cock+]从[npc2.namePos]的口腔中滑出。"
								+ "[npc3.nameIs]抬头看向[npc.herHim]，讶异地发现[npc.name]突然用"
									+(Main.sex.hasLubricationTypeFromAnyone(target, SexAreaOrifice.MOUTH, LubricationType.PRECUM)?"粘乎乎":"沾满唾液")+"的鸡巴扇起[npc3.her]的脸"
									+ "，随后强行将[npc.her][npc.cock+]插入[npc3.her]的喉咙。",
	
							"[npc.cock+]迅速抽离[npc2.namePos]的嘴，[npc.name]边握住鸡巴根，边拥来"+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+"的头。"
								+"[npc3.name]抬起头看向[npc.herHim]，[npc.she]甩动着肉棒拍打[npc3.her]的脸颊，溅起淫靡的水花。"
									+(Main.sex.hasLubricationTypeFromAnyone(target, SexAreaOrifice.MOUTH, LubricationType.PRECUM)?"粘乎乎":"沾满唾液")+"的鸡巴扇起[npc3.her]的脸颊。"
								+ " 当[npc3.name]震惊地[npc3.verb(open)][npc3.her]的嘴时，[npc.name][npc.verb(use)]这个机会，把[npc.her]的[npc.cock+]捅进[npc3.her]的喉咙.");
				
			} else {
				return UtilText.returnStringAtRandom(
							"[npc.Name]退回[npc.hips]，从[npc2.namePos]的嘴中抽出[npc.her][npc.cock+]。"
								+""+(primary.equals(target)?"[npc2.she]":"[npc3.name]")+"还没做出反应，[npc.she]就迅速用肉棒拍打着[npc3.her]的脸颊，唾液溅起淫靡的水花"
								+(Main.sex.hasLubricationTypeFromAnyone(performer, SexAreaPenetration.PENIS, LubricationType.PRECUM)?"和先走液":"")
								+"从[npc3.her]脸上流过，随后[npc.her][npc.cock+]插入了[npc3.her]喉咙。",
								
							"[npc.Name]后退几步，从[npc2.namePos]的嘴中抽出[npc.her][npc.cock+]，"
									+ "[npc.she]发出[npc.a_moan+]，继续把沾满口水的肉棒拍打在"+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+"的[npc3.face]上，"
									+"随后将[npc.her]肉筋跳动的[npc.cock+]插入[npc3.her]的喉咙。",
	
							"[npc.Name]对[npc2.namePos]咧嘴一笑，从[npc.her]的口中抽出[npc.cock+]，然后拍向[npc2.her]的脸"
									+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+" [npc2.face]。"
								+ "随着"+(Main.sex.hasLubricationTypeFromAnyone(performer, SexAreaPenetration.PENIS, LubricationType.PRECUM)?"缀满浓精的":"湿乎乎的")
									+"感觉到口水从脸颊上淌下，[npc3.name]睁开眼睛，吃惊地发现[npc.Name]正把[npc.cock]深入自己喉咙，",
									
							"[npc.Name]迅速退腰，从[npc2.namePos]的口中抽出[npc.cock+]，然后把纤细的肉棒拍在[npc2.her]脸上"
									+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+"脸颊。"
								+ " 在[npc3.name]反应过来之前，[npc.name]突然[npc.verb(push)][npc.her]的[npc.hips]向前，将[npc.her]的[npc.cock+]狠狠捅进[npc3.her]的喉咙.");
			}
		}
	};
	
	public static final SexAction FORCE_BALLS_FOCUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public void applyEffects(){
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				GameCharacter performer = Main.sex.getCharacterPerformingAction();
				GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
				GameCharacter secondary = PenisMouth.getSecondaryBlowjobPerformer(performer);
				if(primary.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
					Main.sex.setPrimaryOngoingCharacter(secondary, performer, SexAreaPenetration.PENIS);
				}
			}
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isInternalTesticles()
					&& Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "照顾蛋蛋";
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]关照一下你[npc.balls+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			int rnd = Util.random.nextInt(4);
			
			String[] start;
			String[] mid = new String[] {};
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				GameCharacter secondary = PenisMouth.getSecondaryBlowjobPerformer(performer);
				if(!primary.equals(target)) {
					start = new String[] {
							"[npc2.namePos][npc2.lips+]仍裹着[npc.her][npc.cock+]，",
							"[npc.name]将[npc.cock+]插入[npc2.namePos]的喉咙，",
							"[npc.Name]将[npc.cock+]滑入[npc2.namePos]的口中，",
							"[npc.name]将[npc.cock+]滑入[npc2.namePos]的喉咙，"};
					UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, primary),
							start[rnd]));
					
				} else {
					start = new String[] {
							"示意[npc2.name]退后，让[npc3.name]含住自己的[npc.cock+]，",
							"[npc.her]从[npc2.name]口中抽出[npc.cock+]，然后饥渴地塞进[npc3.namePos]的喉咙，",
							"[npc.her]的[npc.cock+]从[npc2.name]口中滑出，紧跟着插入[npc3.namePos]的喉咙，",
							"[npc.name]将[npc.cock+]从[npc2.namePos]的喉咙中抽出，转而朝向[npc3.namePos]的嘴巴，"};
					UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, primary, secondary),
							start[rnd]));
				}
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						mid = new String[] {
								"[npc.she]缓步向前，直到[npc.balls+]温柔地贴到[npc3.namePos][npc3.lips+]上。",
								"[npc.Name]摆正位置，让[npc.her][npc.balls+]轻轻压到[npc3.namePos][npc3.lips+]上。",
								"接着[npc.she]调整姿势，以便于[npc3.namePos]将[npc3.lips+]轻轻靠向自己[npc.balls+]。",
								"[npc.Name]摆正位置，让[npc.her][npc.balls+]轻轻压到[npc3.namePos][npc3.lips+]上。"};
						break;
					case DOM_ROUGH:
						mid = new String[] {
								"[npc.she]缓步向前，直到[npc.balls+]粗暴地压到[npc3.namePos][npc3.lips+]上。",
								"[npc.Name]摆正位置，让[npc.her][npc.balls+]粗暴地压到[npc3.namePos][npc3.lips+]上。",
								"接着[npc.she]调整姿势，以便于[npc3.namePos]用[npc3.lips+]粗暴地磨蹭自己[npc.balls+]。",
								"[npc.Name]摆正位置，强行将[npc.her][npc.balls+]粗暴地压到[npc3.namePos][npc3.lips+]上。"};
						break;
					case SUB_NORMAL:
						mid = new String[] {
								"[npc.Name]缓步向前，直到[npc.balls+]紧贴在[npc3.namePos][npc3.lips+]上。",
								"[npc.Name]摆正位置，让[npc.balls+]压到[npc3.namePos][npc3.lips+]上。",
								"接着[npc.she]调整姿势，以便于[npc3.namePos]将[npc3.lips+]靠向自己[npc.balls+]。",
								"[npc.Name]摆正位置，强制将[npc.her][npc.balls+]压到[npc3.namePos][npc3.lips+]上。"};
						break;
					default: // Dom normal and sub eager:
						mid = new String[] {
								"[npc.she]缓步向前，直到[npc.balls+]贴到[npc3.namePos][npc3.lips+]上。",
								"[npc.Name]摆正位置，让[npc.balls+]压到[npc3.namePos][npc3.lips+]上。",
								"接着[npc.she]调整姿势，以便于[npc3.namePos]将[npc3.lips+]靠向自己[npc.balls+]。",
								"[npc.Name]摆正位置，强制将[npc.her][npc.balls+]压到[npc3.namePos][npc3.lips+]上。"};
						break;
				}
				UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, primary, target),
						mid[rnd]));
				
			} else {
				start = new String[] {
						"[npc.Name]退回[npc.hips]，让[npc.her][npc.cock+]从[npc2.namePos]的口中滑出，",
						"[npc.name]将自己[npc.cock+]从[npc2.namePos]口中抽出，",
						"[npc.Name]从[npc2.namePos]嘴里抽出[npc.cock+]，",
						"[npc.name]将自己[npc.cock+]迅速地从[npc2.namePos]口中抽出，"};
				UtilText.nodeContentSB.append(start[rnd]);

				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						mid = new String[] {
								"接着[npc.she]缓步向前，温柔地把[npc.balls+]贴到[npc2.namePos][npc2.lips+]上。",
								"[npc.Name]摆正位置，让[npc.her][npc.balls+]轻轻压到[npc2.lips+]上。",
								"接着[npc.she]调整姿势，以便于[npc2.namePos]将[npc2.lips+]轻轻靠向自己[npc.balls+]。",
								"[npc.Name]摆正位置，让[npc.her][npc.balls+]轻轻压到[npc2.namePos][npc2.lips+]上。"};
						break;
					case DOM_ROUGH:
						mid = new String[] {
								"接着[npc.she]缓步向前，粗暴地用[npc.balls+]摩擦着[npc2.namePos][npc2.lips+]。",
								"[npc.Name]摆正位置，让[npc.balls+]粗暴地压到[npc2.her][npc2.lips+]上。",
								"接着[npc.she]调整姿势，以便于[npc2.namePos]用[npc2.lips+]粗暴地磨蹭自己[npc.balls+]。",
								"[npc.Name]摆正位置，强行将[npc.her][npc.balls+]粗暴地压到[npc2.namePos][npc2.lips+]上。"};
						break;
					case SUB_NORMAL:
						mid = new String[] {
								"接着[npc.she]缓步向前，把[npc.balls+]贴向[npc2.namePos][npc2.lips+]。",
								"[npc.Name]摆正位置，让[npc.her][npc.balls+]压到[npc2.lips+]上。",
								"接着[npc.she]调整姿势，以便于[npc2.namePos]将[npc2.lips+]靠向自己[npc.balls+]。",
								"[npc.Name]摆正位置，强制将[npc.her][npc.balls+]压到[npc2.namePos][npc2.lips+]上。"};
						break;
					default: // Dom normal and sub eager:
						mid = new String[] {
								"接着[npc.she]缓步向前，把[npc.balls+]贴向[npc2.namePos][npc2.lips+]。",
								"[npc.Name]摆正位置，让[npc.her][npc.balls+]压到[npc2.lips+]上。",
								"接着[npc.she]调整姿势，以便于[npc2.namePos]将[npc2.lips+]靠向自己[npc.balls+]。",
								"[npc.Name]摆正位置，强制将[npc.her][npc.balls+]压到[npc2.namePos][npc2.lips+]上。"};
						break;
				}
				UtilText.nodeContentSB.append(mid[rnd]);
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]伸出[npc2.tongue+]，温柔地亲吻舔舐着[npc.namePos][npc.balls+]，使得[npc.a_moan+]断断续续从[npc.her][npc.lips+]间飘出。",
								"[npc2.name]温柔地舔着[npc.namePos][npc.balls+]，用舌尖在上面打着转，使得[npc.a_moan+]断断续续从[npc.her]的[npc.lips+]中飘出。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]饥渴地弹出[npc2.tongue+]，贪婪地舔着[npc.namePos][npc.balls+]，使得[npc.a_moan+]断断续续从[npc.her][npc.lips+]中飘出。",
								"[npc2.name]贪婪地舔着[npc.namePos][npc.balls+]，玩弄着睾丸，使得[npc.a_moan+]断断续续从[npc.her]的[npc.lips+]中飘出。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]伸出[npc2.tongue+]，粗鲁地亲吻舔舐着[npc.namePos][npc.balls+]，使得[npc.a_moan+]断断续续从[npc.her][npc.lips+]间飘出。",
								"[npc2.name]粗鲁地舔着[npc.namePos][npc.balls+]，舔得囊袋不住晃动，使得[npc.a_moan+]断断续续从[npc.her]的[npc.lips+]中飘出。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]伸出[npc2.tongue+]，亲吻舔舐着[npc.namePos][npc.balls+]，使得[npc.a_moan+]断断续续从[npc.her][npc.lips+]中飘出。",
								"[npc2.name]亲吻舔舐着[npc.namePos][npc.balls+]，使得[npc.her]的口中飘出一阵[npc.a_moan+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]拼命地试图挣脱，但[npc.namePos][npc.balls+]依然继续磨蹭着[npc2.her][npc2.lips+]，[npc2.she]发出一阵[npc2.a_sob+]。",
								" [npc2.Name]发出一声压抑的[npc2.sob]，在抵抗挣脱[npc.herHim]的同时拼命想要挣脱[npc.namePos]那[npc.balls+]。"));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction SUCK_BALLS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects(){
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
				GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
				GameCharacter secondary = PenisMouth.getSecondaryBlowjobPerformer(target);
				if(primary.equals(Main.sex.getCharacterPerformingAction())) {
					Main.sex.setPrimaryOngoingCharacter(secondary, target, SexAreaPenetration.PENIS);
				}
			}
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isInternalTesticles()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "舔蛋蛋";
		}

		@Override
		public String getActionDescription() {
			return "亲吻舔舐一会[npc2.namePos]的[npc2.balls]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			int rnd = Util.random.nextInt(4);
			
			String[] start;
			String[] mid = new String[] {};
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				GameCharacter secondary = PenisMouth.getSecondaryBlowjobPerformer(target);
				if(!primary.equals(performer)) {
					start = new String[] {
							"[npc2.namePos][npc2.cock+]仍然探索着[npc3.namePos]的喉咙，",
							"[npc3.name]在[npc2.namePos]大腿上抬起头，恰好看到[npc.name]靠过来在[npc2.namePos][npc2.cock+]上亲了一下，",
							"[npc2.name]让自己的[npc2.cock+]滑入[npc3.namePos]的嘴，",
							"确保不要妨碍[npc3.name]上下晃动着头服侍[npc2.namePos]的[npc2.cock+]，"};
					UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
							start[rnd]));
					
				} else {
					start = new String[] {
							"后撤些让[npc3.name]将[npc2.namePos][npc2.cock+]完全吞进嘴里，",
							"[npc.Name]与[npc3.name]换位，让[npc3.her]把[npc2.namePos]的[npc2.cock+]含入口中，",
							"[npc.name]将[npc2.namePos][npc2.cock+]从口中抽出，然后换[npc3.name]吞下这黏滑的阴茎，",
							"[npc2.namePos]的[npc2.cock+]从[npc.name]口中抽出，接着让[npc3.name]吮吸了起来，"};
					UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, secondary),
							start[rnd]));
				}
				
			} else {
				start = new String[] {
						"[npc.name]将[npc2.namePos][npc2.cock+]从[npc.her]口中完全抽出，",
						"[npc.Name]让[npc2.namePos][npc2.cock+]从自己口中滑出，",
						"[npc.name]将[npc2.namePos][npc2.cock+]从口中抽出，",
						"[npc.name]先把[npc2.namePos][npc2.cock+]从[npc.her]嘴里抽出，"};
				UtilText.nodeContentSB.append(start[rnd]);
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					mid = new String[] {
							"[npc.name]低下头，温柔地对着[npc2.name][npc2.balls+]又舔又吸。",
							" 接着[npc.she]低下头，用[npc.lips+]温柔地对着[npc2.name][npc2.balls+]亲吻舔舐。",
							"[npc.name]低下头，温柔地对[npc2.her][npc2.balls+]亲吻舔舐。",
							"[npc.name]低下头，温柔地亲了一下[npc2.name][npc2.balls+]，然后将之紧紧含住。"};
					break;
				case DOM_ROUGH:
					mid = new String[] {
							"[npc.name]低下头，粗暴地亲吻舔弄[npc2.her][npc2.balls+]。",
							" 接着[npc.she]低下头，用[npc.lips+]粗暴地亲吻舔弄[npc2.her][npc2.balls+]。",
							"[npc.name]低下头，粗暴地亲吻舔弄[npc2.her][npc2.balls+]。",
							"[npc.name]低下头，粗暴地亲了一下[npc2.name][npc2.balls+]，然后将之紧紧含住。"};
					break;
				case SUB_NORMAL:
					mid = new String[] {
							"[npc.name]低下头，开始对[npc2.her][npc2.balls+]亲吻舔舐。",
							" 接着[npc.she]低下头，用[npc.lips+]对着[npc2.name][npc2.balls+]亲吻舔舐。",
							"[npc.name]低下头，开始对[npc2.her][npc2.balls+]亲吻舔舐。",
							"[npc.name]低下头，亲了一下[npc2.name][npc2.balls+]，然后将之紧紧含住。"};
					break;
				default: // Dom normal and sub eager:
					mid = new String[] {
							"[npc.name]低下头，开始对[npc2.her][npc2.balls+]急切地亲吻舔舐。",
							" 接着[npc.she]低下头，用[npc.lips+]饥渴地对着[npc2.name][npc2.balls+]亲吻舔舐。",
							"[npc.name]低下头，开始对[npc2.her][npc2.balls+]急切地亲吻舔舐。",
							"[npc.name]低下头，然后开始对[npc2.her][npc2.balls+]急切地亲吻舔舐。"};
					break;
			}
			UtilText.nodeContentSB.append(UtilText.parse(performer, target, mid[rnd]));
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction LICK_HEAD = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "舔龟头";
		}

		@Override
		public String getActionDescription() {
			return "对着[npc2.namePos][npc2.cock+]的[npc2.cockHead+]又亲又舔。";
		}

		@Override
		public String getDescription() {

			List<String> descriptions = new ArrayList<>();

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				List<String> descriptionsEnd = new ArrayList<>();
				
				for(PenetrationModifier pm : Main.sex.getCharacterTargetedForSexAction(this).getPenisModifiers()) {
					switch(pm) {
						case FLARED:
							descriptions.add("[npc2.namePos]抬起[npc3.namePos]的头，把自己[npc2.cock+]插进[npc3.her]嘴里，而[npc.name]迅速移到[npc3.her]的位置。"
									+"[npc.she]低头，对着[npc2.namePos][npc2.cock+]又宽又平的龟头热情地又亲又舔。");
							break;
						default:
							break;
					}
				}
				descriptions.add("[npc.name][npc.moan]着，声音中透露着急不可耐。[npc.her]一把拉开[npc3.namePos]，张开嘴靠近[npc2.namePos][npc2.cockHead+]。"
						+"满意地[npc.moan]着，[npc.she]对着[npc2.cockHead+]又亲又舔。");
				descriptions.add("[npc.Name]一把拉开[npc3.namePos]，准备加入进来，"
						+ "[npc.she]发出[npc.a_moan+]并取代[npc3.her]的位置，开始吮吸和亲吻[npc2.namePos][npc2.cock+][npc2.cockHead+]。");
				descriptions.add("[npc.Name]把[npc3.namePos]拉到一边，接着，"
						+"[npc.she]聚精会神地对着近在唇边[npc2.cockHead+]又亲又舔。");

				// If not resisting
				switch(Main.sex.getSexPace(primary)) {
					case SUB_RESISTING:
						descriptionsEnd.add(" Happy to have been pushed aside, [npc3.name] [npc3.verb(try)] to stay quiet as [npc3.she] [npc3.verb(attempt)] to shuffle further away, but, realising that [npc3.she] [npc3.verb(need)] some 'encouragement',"
								+ " [npc.name] soon [npc.verb(pull)] back from [npc2.namePos] crotch, before grabbing [npc3.name] and pushing [npc3.her] mouth down onto [npc2.namePos] [npc2.cock+] once more.");
						descriptionsEnd.add(" Relieved to have been pushed out of [npc3.her] position, [npc3.name] [npc3.verb(try)] to get away from [npc2.name], but, seeing what's happening,"
								+ " [npc.name] quickly [npc.verb(put)] an end to [npc.her] oral servicing, before grabbing [npc3.name] and forcing [npc3.herHim] to swallow [npc2.namePos] [npc2.cock+] once again.");
						break;
					default:
						descriptionsEnd.add(" Not happy with having been left to service the sides of [npc2.namePos] shaft, [npc3.name] [npc3.verb(allow)] [npc.name] to have [npc.her] fun for a few moments,"
								+ " before pushing [npc.her] aside and once again taking [npc2.namePos] [npc2.cock] down [npc3.her] throat.");
						descriptionsEnd.add(" Impatient to regain [npc3.her] position, [npc3.name] [npc3.verb(let)] [npc.name] orally service [npc2.name] in this manner for a short while,"
								+ " before pushing [npc.herHim] back out of the way and taking [npc2.namePos] [npc2.cock+] into [npc3.her] mouth once more.");
						break;
				}
				
				return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
						Util.randomItemFrom(descriptions))
						+ UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								Util.randomItemFrom(descriptionsEnd));
				
			} else {
				for(PenetrationModifier pm : Main.sex.getCharacterTargetedForSexAction(this).getPenisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("[npc.Name]稍稍仰头，感受着[npc2.namePos][npc2.cock]上的倒刺划过喉咙，发出带着水声的[npc.moan]。"
									+"感受着[npc2.name][npc2.cockHead+]轻轻拍打着自己[npc.lips+]，[npc.she]忍不住热情地对着[npc2.cock+]又亲又舔。");
							break;
						case FLARED:
							descriptions.add("[npc.Name]仰头，感受[npc2.namePos][npc2.cock+]划出嘴巴。"
									+"[npc.she]低头，把嘴唇贴在又宽又平的龟头上，然后对着[npc2.namePos][npc2.cock+]热情地又亲又舔。");
							break;
						default:
							break;
					}
				}
				descriptions.add("[npc.Name]发出[npc.a_moan+]，向后仰头，张开[npc.lips+]含住[npc2.namePos][npc2.cockHead+]，然后开始舔吻[npc2.cock+]。");
				descriptions.add("[npc.Name]转过头，发出[npc.a_moan+]，开始吮吸和亲吻[npc2.namePos][npc2.cock+][npc2.cockHead+]。");
				descriptions.add("[npc.Name]稍稍仰头，感受着[npc2.namePos][npc2.cock+]一大半退出口中，然后，"
						+"[npc.she]集中注意力在[npc.tongue+]上，尽可能认真地舔吻[npc2.cockHead+]。");
				
				return Util.randomItemFrom(descriptions);
			}
		}
		
	};
	
	public static final SexAction HERM_FUN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.VAGINA)
					&& Main.sex.isOrificeFree(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
				return "扶她乐趣";
			} else {
				return "双性乐趣";
			}
		}

		@Override
		public String getActionDescription() {
			return "同时取悦[npc2.namePos][npc2.cock+]和[npc2.pussy+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]不想忽略[npc2.namePos]其他的性器，给予了[npc2.her][npc2.cock+]的[npc2.cockHead]一个温柔的吮吸，"
									+"随后后缩，开始缓慢地舔吻着[npc2.her][npc2.pussy+]。",

							"[npc.name]决定给予[npc2.namePos][npc2.pussy]一些关注，缓慢地舔舐起[npc2.her][npc2.cock+]，"
									+ "随后[npc.her]的[npc.tongue+]温柔地滑入[npc2.her][npc2.pussy+]中。",

							"[npc.Name]往下探，给予[npc2.namePos][npc2.cockHead]一个温柔的吻，"
									+"把[npc.lips+]贴向[npc2.name][npc2.pussy+]，然后慢慢把[npc.tongue+]伸入[npc2.labia+]。"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]不想忽略[npc2.namePos]其他的性器，给予了[npc2.her][npc2.cock+]的[npc2.cockHead]一个湿润的吮吸，"
									+"随后后缩，开始急切地舔吻着[npc2.her][npc2.pussy+]。",

							"[npc.Name]决定给予[npc2.namePos][npc2.pussy]一些关注，绵长又湿润地舔舐起[npc2.her][npc2.cock+]，"
									+ "随后[npc.her]的[npc.tongue+]急切地滑入[npc2.her][npc2.pussy+]中。",

							"[npc.Name]往下探，给予[npc2.namePos][npc2.cockHead]一个湿润的吻，"
									+"把[npc.lips+]贴向[npc2.name][npc2.pussy+]，然后饥渴地把[npc.tongue+]伸入[npc2.labia+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]不想忽略[npc2.namePos]其他的性器，给予了[npc2.her][npc2.cock+]的[npc2.cockHead]一个湿润的吮吸，"
									+"随后后缩，开始粗暴地舔吻着[npc2.her][npc2.pussy+]。",

							"[npc.Name]决定给予[npc2.namePos][npc2.pussy]一些关注，舔舐起[npc2.her][npc2.cock+]，"
									+ "随后[npc.her]的[npc.tongue+]支配地插入[npc2.her][npc2.pussy+]中。",

							"[npc.Name]往下探，给予[npc2.namePos][npc2.cockHead]一个粗暴的吻，"
									+"把[npc.lips+]贴向[npc2.name][npc2.pussy+]，然后支配地把[npc.tongue+]伸入[npc2.labia+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]不想忽略[npc2.namePos]其他的性器，给予了[npc2.her][npc2.cock+]的[npc2.cockHead]一个湿润的吮吸，"
									+"随后后缩，开始舔吻着[npc2.her][npc2.pussy+]。",

							"[npc.Name]决定给予[npc2.namePos][npc2.pussy]一些关注，绵长又湿润地舔舐起[npc2.her][npc2.cock+]，"
									+ "随后[npc.her]的[npc.tongue+]滑入[npc2.her][npc2.pussy+]中。",

							"[npc.Name]往下探，给予[npc2.namePos][npc2.cockHead]一个湿润的吻，"
									+"把[npc.lips+]贴向[npc2.name][npc2.pussy+]，然后把[npc.tongue+]伸入[npc2.labia+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一声柔和的[npc2.moan]，"
										+"温柔地把[npc2.pussy+]压向[npc.namePos][npc.lips+]，随后[npc.name]决定将注意力重新转回[npc2.namePos][npc2.cock+]。",
	
								"[npc2.name]轻轻地[npc2.moan]着，温柔地把[npc2.pussy+]挺近[npc.namePos]的[npc.face]，不过[npc.Name]现在对[npc2.cock+]更有兴趣。",
	
								"[npc2.name]轻声[npc2.moaning]，温柔地将[npc2.her][npc2.hips]顶向[npc.namePos][npc.face]好一会儿，"
										+ "然后[npc.she]把口腔的注意力重新调整到[npc2.her][npc2.cock+]上。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出了[npc2.a_moan+]，"
										+"急切地把[npc2.pussy+]压向[npc.namePos][npc.lips+]，随后[npc.name]决定将注意力重新转回[npc2.namePos][npc2.cock+]。",
	
								"[npc2.name]轻轻地[npc2.moan]着，饥渴地把[npc2.pussy+]挺近[npc.namePos]的[npc.face]，不过[npc.Name]现在对[npc2.cock+]更有兴趣。",
	
								"[npc2.name][npc2.Moaning+]，饥渴地将[npc2.her][npc2.hips]顶向[npc.namePos][npc.face]好一会儿，"
										+ "然后[npc.she]把口腔的注意力重新调整到[npc2.her][npc2.cock+]上。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出了[npc2.a_moan+]，"
										+ "粗暴地用[npc2.pussy+]蹭着[npc.namePos][npc.lips+]，不过[npc.Name]现在对[npc2.cock+]更有兴趣。",
	
								"[npc2.name]发出[npc2.a_moan+]，粗暴地用[npc2.pussy+]蹭着[npc.namePos]的[npc.face]，不过[npc.Name]现在对[npc2.cock+]更有兴趣。",
	
								"[npc2.name]粗暴地用[npc2.hips]顶着[npc.namePos][npc.face]好一会儿，[npc2.moaning+]着，"
										+ "然后[npc.she]把口腔的注意力重新调整到[npc2.her][npc2.cock+]上。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出了[npc2.a_moan+]，"
										+"把[npc2.pussy+]压向[npc.namePos][npc.lips+]，随后[npc.name]决定将注意力重新转回[npc2.namePos][npc2.cock+]。",
	
								"[npc2.name]发出[npc2.a_moan+]，饥渴地用[npc2.pussy+]蹭着[npc.namePos]的[npc.face]，不过[npc.Name]现在对[npc2.cock+]更有兴趣。",
	
								"[npc2.name]将[npc2.her][npc2.hips]顶向[npc.namePos][npc.face]好一会儿，[npc2.Moaning+]，"
										+ "然后[npc.she]把口腔的注意力重新调整到[npc2.her][npc2.cock+]上。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]不舒服地[npc2.Sobbing]和蠕动着，拼命地尝试远离[npc.herHim]，"
										+"[npc.she]使注意力重回[npc2.her][npc2.cock+]，同时祈求着能自己一个人呆着。",
	
								"[npc2.name][npc2.a_sob]着，战栗着尝试推开对[npc2.cock+]产生兴趣的[npc.Name]。",
	
								"[npc2.name]因[npc.Name]将注意重新放回[npc2.her][npc2.cock+]而发出[npc2.a_sob+]，[npc2.face]上的泪如流水倾泻。"));
						break;
					default:
						break;
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"享受了一会儿[[npc2.name]之后，[npc.name]把注意力重新调整到[npc2.her][npc2.cock+]上。",
						"享受了一会儿[npc2.namePos][npc2.pussy+]的滋味后，[npc.name]把注意力集中在[npc2.her][npc2.cock+]上。"));
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction TWINTAIL_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
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
				return "抓住[npc2.namePos]的双马尾，把[npc2.her]摁在你[npc.cock+]上。";
			} else {
				return "抓住[npc2.namePos]的双麻花辫，把[npc2.her]摁在你[npc.cock+]上。";
			}
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
			
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc.Name]让[npc3.name]让开收回自己[npc.cock+]，然后抓住[npc2.namePos]"+style+"，"
										+ "慢慢地把[npc2.her]拉过来让[npc.her]把[npc.cock+]一含到底。",
								"[npc.Name]缓缓推开[npc3.name]，而后俯下身轻柔但坚定地抓住[npc2.namePos]的"+style+"，坚定地将[npc2.herHim]拉近自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.name]温柔地推开[npc3.name]，然后弯下腰握住[npc2.namePos]的"+style+"，"
										+ "然后将[npc2.herHim]缓慢地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc.Name]推开[npc3.name]收回自己[npc.cock+]，然后抓住[npc2.namePos]"+style+"，"
										+ "用力地向[npc2.her]的脸顶去，强迫[npc2.herHim]把自己[npc.cock+]连根吞入。",
								"[npc.name]粗暴地推开[npc3.name]，弯下腰握住[npc2.namePos]的"+style+"，无情地将[npc2.her]的头部拽向自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.name]粗暴地按倒[npc3.name]，弯下腰握住[npc2.namePos]的"+style+"，"
										+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。")));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc.Name]让[npc3.name]让开收回自己[npc.cock+]，然后抓住[npc2.namePos]"+style+"，"
										+ "紧紧地把[npc2.her]的头拉过来强迫[npc2.herHim]把自己[npc.cock+]连根吞入。",
								"[npc.Name]推开[npc3.name]，而后俯下身坚定地抓住[npc2.namePos]的"+style+"，坚定地将[npc2.herHim]拉近自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.Name]推开[npc3.name]，然后抓住[npc2.namePos]"+style+","
										+ "然后将[npc2.herHim]坚定地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]握住[npc2.namePos]的"+style+"，温柔地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her]的[npc.cock+]直到底部。",
								"[npc.Name]俯下身轻柔但坚定地抓住[npc2.namePos]的每一个"+style+"，缓缓将[npc2.herHim]拉近[npc.her]的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos]的"+style+"，"
										+ "然后将[npc2.herHim]温柔地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"粗暴地抓住[npc2.namePos]"+style+"，用力地向[npc2.her]的脸顶去，强迫[npc2.herHim]把自己[npc.cock+]连根吞入。",
								"[npc.Name]向下抓住[npc2.namePos]的"+style+"，无情地将[npc2.her]的头部拽向自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.Name]发出[npc.a_moan+]，弯下腰抓住[npc2.namePos]的"+style+"，"
										+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]握住[npc2.namePos]的"+style+"，紧紧地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her]的[npc.cock+]直到底部。",
								"[npc.Name]俯下身紧紧地抓住[npc2.namePos]的每一个"+style+"，坚定地将[npc2.herHim]拉近[npc.her]的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos]的"+style+"，"
										+ "然后将[npc2.herHim]坚定地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.tongue]轻轻摩挲着[npc.namePos][npc.cock]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.cock+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，用[npc2.her]的[npc2.tongue]轻轻摩挲着[npc.namePos][npc.cock]的下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.cock+]最终从[npc2.her]喉咙中拔出。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]垂下眸子，忍受了一小会[npc.namePos]的大胆举动，"
										+ "然后把[npc2.her]的头扭过来，厉声提醒[npc.herHim]谁才是主导者。",
								"[npc2.namePos]威胁似地低吼着，那震动的喉咙却同时带来了额外的快感，"
										+ "尽管如此，[npc.name]还是被吓得立即松开[npc2.hair(true)]，将自己[npc.cock+]从[npc2.her]的喉咙拔出来。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"蜷缩着的[npc2.her]合上[npc2.eyes]，[npc2.name]徒劳地抵抗着，拍打#IF(npc.isPlayer())你的#ELSE[npc.her]折磨者的#ENDIF股间，"
										+ "[npc.name]从[npc2.her]的喉咙中抽出[npc.her][npc.cock+]的瞬间，终于可以哭着喘息了。",
								"[npc2.namePos]压抑的哭泣和抽噎只会给#IF(npc.isPlayer())你#ELSE折磨[npc.her]的人#ENDIF带来额外的快感，"
										+ "但是，在花了几秒钟的时间拳打脚踢[npc.namePos]股间之后，"
										+ "[npc2.she]终于取得了一个小小的胜利，在[npc2.her][npc2.hair(true)]被放开且[npc.namePos][npc.cock+]暂时从[npc2.her]的喉咙里滑了出来时。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.tongue]摩挲着[npc.namePos][npc.cock]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.cock+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]开心地闷哼着，发出[npc2.moaning]，[npc2.tongue]亲密地摩挲着[npc.namePos]的[npc.cock]下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.cock+]最终从[npc2.her]喉咙中拔出。"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "揪耳朵";
		}

		@Override
		public String getActionDescription() {
			return "抓住[npc2.namePos]的[npc2.ears+]，把[npc2.her]摁在你[npc.cock+]上。";
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

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc.Name]让[npc3.name]让开收回自己[npc.cock+]，然后抓住[npc2.namePos][npc2.ears+]，"
										+ "慢慢地把[npc2.her]拉过来让[npc.her]把[npc.cock+]一含到底。",
								"[npc.Name]缓缓推开[npc3.name]，而后俯下身轻柔但坚定地抓住[npc2.namePos][npc2.ears+]，缓缓将[npc2.her]拉近自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.Name]温柔地推开[npc3.name]，然后抓住[npc2.namePos][npc2.ears+]，"
										+ "然后将[npc2.herHim]缓慢地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc.Name]推开[npc3.name]收回[npc.cock+]，然后抓住[npc2.namePos][npc2.ears+]，"
										+ "用力地向[npc2.her]的脸顶去，强迫[npc2.herHim]把自己[npc.cock+]连根吞入。",
								"[npc.name]粗暴地推开[npc3.name]，弯下腰握住[npc2.namePos][npc2.ears+]，无情地将[npc2.her]的头部拽向自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.name]粗暴地推开[npc3.name]，弯下腰握住[npc2.namePos][npc2.ears+]，"
										+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。")));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc.Name]让[npc3.name]让开收回[npc.cock+]，然后抓住[npc2.namePos][npc2.ears+]，"
										+ "紧紧地把[npc2.her]的头拉过来强迫[npc2.herHim]把自己[npc.cock+]连根吞入。",
								"[npc.Name]推开[npc3.name]，而后俯下身坚定地抓住[npc2.namePos][npc2.ears+]，稳稳地将[npc2.herHim]拉近自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.name]推开[npc3.name]，弯下腰握住[npc2.namePos][npc2.ears+]，"
										+ "然后将[npc2.herHim]坚定地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"在抓住[npc2.namePos][npc2.ears+]后，[npc.Name]温柔地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her]那[npc.cock+]直到底部。", 
								"[npc.Name]俯下身轻柔但坚定地抓住[npc2.namePos][npc2.ears+]，缓缓将[npc2.her]拉近[npc.her]的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.ears+]，"
										+ "然后将[npc2.herHim]温柔地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]粗鲁地抓住[npc2.namePos][npc2.ears+]，残暴地将[npc2.her]的头部拉扯过来，强迫[npc2.herHim]把[npc.her][npc.cock+]从根部全都吞下。",
								"[npc.Name]向下抓住[npc2.namePos][npc2.ears+]，无情地将[npc2.her]的头部拽向自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.Name]发出[npc.a_moan+]，弯下腰抓住[npc2.namePos][npc2.ears+]，"
										+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]握住[npc2.namePos][npc2.ears+]，紧紧地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her][npc.cock+]直到底部。",
								"[npc.Name]俯下身紧紧地抓住[npc2.namePos][npc2.ears+]，坚定地将[npc2.herHim]拉近[npc.her]的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.ears+]，"
										+ "然后将[npc2.herHim]坚定地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.tongue]轻轻摩挲着[npc.namePos][npc.cock]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.cock+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，用[npc2.her]的[npc2.tongue]轻轻摩挲着[npc.namePos][npc.cock]的下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.cock+]最终从[npc2.her]喉咙中拔出。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]垂下眸子，忍受了一小会[npc.namePos]的大胆举动，"
										+ "然后把[npc2.her]的头扭过来，厉声提醒[npc.herHim]谁才是主导者。",
								"[npc2.namePos]威胁似地低吼着，那震动的喉咙却同时带来了额外的快感，"
										+ "尽管如此，[npc.name]还是被吓得立即松开[npc2.hair(true)]，将自己[npc.cock+]从[npc2.her]的喉咙拔出来。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"蜷缩着的[npc2.her]合上[npc2.eyes]，[npc2.name]徒劳地抵抗着，拍打#IF(npc.isPlayer())你的#ELSE[npc.her]折磨者的#ENDIF股间，"
										+ "[npc.name]从[npc2.her]的喉咙中抽出[npc.her][npc.cock+]的瞬间，终于可以哭着喘息了。",
								"[npc2.namePos]压抑的哭泣和抽噎只会给#IF(npc.isPlayer())你#ELSE折磨[npc.her]的人#ENDIF带来额外的快感，"
										+ "但是，在花了几秒钟的时间拳打脚踢[npc.namePos]股间之后，"
										+ "[npc2.she]终于取得了一个小小的胜利，在[npc2.her][npc2.hair(true)]被放开且[npc.namePos][npc.cock+]暂时从[npc2.her]的喉咙里滑了出来时。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.tongue]摩挲着[npc.namePos][npc.cock]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.cock+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]开心地闷哼着，发出[npc2.moaning]，[npc2.tongue]亲密地摩挲着[npc.namePos]的[npc.cock]下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.cock+]最终从[npc2.her]喉咙中拔出。"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "抓住[npc2.horns]";
		}

		@Override
		public String getActionDescription() {
			return "抓住[npc2.namePos]的[npc2.horns+]，把[npc2.her]摁在你[npc.cock+]上。";
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

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc.Name]让[npc3.name]让开收回[npc.cock+]，然后抓住[npc2.namePos][npc2.horns+]，"
										+ "慢慢地把[npc2.her]拉过来让[npc.her]把[npc.cock+]一含到底。",
								"[npc.Name]缓缓推开[npc3.name]，而后俯下身轻柔但坚定地抓住[npc2.namePos][npc2.horns+]，缓缓将[npc2.her]拉近自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.name]温柔地推开[npc3.name]，弯下腰握住[npc2.namePos][npc2.horns+]，"
										+ "然后将[npc2.herHim]缓慢地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc.Name]推开[npc3.name]收回[npc.cock+]，然后粗鲁地抓着[npc2.namePos][npc2.horns+]，"
										+ "用力地向[npc2.her]的脸顶去，强迫[npc2.herHim]把自己[npc.cock+]连根吞入。",
								"[npc.name]粗暴地推开[npc3.name]，弯下腰握住[npc2.namePos][npc2.horns+]，无情地将[npc2.her]的头部拽向自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.name]粗暴地推开[npc3.name]，弯下腰握住[npc2.namePos][npc2.horns+]，"
										+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。")));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc.Name]让[npc3.name]让开收回[npc.cock+]，然后抓住[npc2.namePos][npc2.horns+]，"
										+ "紧紧地把[npc2.her]的头拉过来强迫[npc2.herHim]把自己[npc.cock+]连根吞入。",
								"[npc.Name]推开[npc3.name]，而后俯下身坚定地抓住[npc2.namePos][npc2.horns+]，缓缓地将[npc2.herHim]拉近自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.name]推开[npc3.name]，弯下腰握住[npc2.namePos][npc2.horns+]，"
										+ "然后将[npc2.herHim]坚定地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]握住[npc2.namePos][npc2.horns+]，温柔地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her][npc.cock+]直到根部。",
								"[npc.Name]俯下身轻柔但坚定地抓住[npc2.namePos][npc2.horns+]，缓缓将[npc2.her]拉近[npc.her]的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.horns+]，"
										+ "然后将[npc2.herHim]温柔地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]粗鲁地抓住[npc2.namePos][npc2.horns+]，残暴地将[npc2.her]的头部拉扯过来，强迫[npc2.herHim]把[npc.her][npc.cock+]从根部全都吞下。",
								"[npc.Name]向下抓住[npc2.namePos][npc2.horns+]，无情地将[npc2.her]的头部拽向自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.Name]发出[npc.a_moan+]，弯下腰抓住[npc2.namePos][npc2.horns+]，"
										+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]握住[npc2.namePos][npc2.horns+]，紧紧地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her][npc.cock+]直到根部。",
								"[npc.Name]俯下身紧紧地抓住[npc2.namePos][npc2.horns+]，坚定地将[npc2.herHim]拉近[npc.her]的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.horns+]，"
										+ "然后将[npc2.herHim]坚定地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.tongue]轻轻摩挲着[npc.namePos][npc.cock]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.cock+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，用[npc2.her]的[npc2.tongue]轻轻摩挲着[npc.namePos][npc.cock]的下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.cock+]最终从[npc2.her]喉咙中拔出。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]垂下眸子，忍受了一小会[npc.namePos]的大胆举动，"
										+ "然后把[npc2.her]的头扭过来，厉声提醒[npc.herHim]谁才是主导者。",
								"[npc2.namePos]威胁似地低吼着，那震动的喉咙却同时带来了额外的快感，"
										+ "尽管如此，[npc.name]还是被吓得立即松开[npc2.hair(true)]，将自己[npc.cock+]从[npc2.her]的喉咙拔出来。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"蜷缩着的[npc2.her]合上[npc2.eyes]，[npc2.name]徒劳地抵抗着，拍打#IF(npc.isPlayer())你的#ELSE[npc.her]折磨者的#ENDIF股间，"
										+ "[npc.name]从[npc2.her]的喉咙中抽出[npc.her][npc.cock+]的瞬间，终于可以哭着喘息了。",
								"[npc2.namePos]压抑的哭泣和抽噎只会给#IF(npc.isPlayer())你#ELSE折磨[npc.her]的人#ENDIF带来额外的快感，"
										+ "但是，在花了几秒钟的时间拳打脚踢[npc.namePos]股间之后，"
										+ "[npc2.she]终于取得了一个小小的胜利，在[npc2.her][npc2.hair(true)]被放开且[npc.namePos][npc.cock+]暂时从[npc2.her]的喉咙里滑了出来时。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.tongue]摩挲着[npc.namePos][npc.cock]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.cock+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]开心地闷哼着，发出[npc2.moaning]，[npc2.tongue]亲密地摩挲着[npc.namePos]的[npc.cock]下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.cock+]最终从[npc2.her]喉咙中拔出。"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "抓住[npc2.antennae]";
		}

		@Override
		public String getActionDescription() {
			return "抓住[npc2.namePos][npc2.antennae+]，把[npc2.her]摁在你[npc.cock+]上。";
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

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc.Name]让[npc3.name]让开收回[npc.cock+]，然后抓住[npc2.namePos][npc2.antennae+]，"
										+ "慢慢地把[npc2.her]拉过来让[npc.her]把[npc.cock+]一含到底。",
								"[npc.Name]缓缓推开[npc3.name]，而后俯下身轻柔但坚定地抓住[npc2.namePos][npc2.antennae+]，缓缓地将[npc2.herHim]拉近自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.name]温柔地推开[npc3.name]，弯下腰握住[npc2.namePos][npc2.antennae+]，"
										+ "然后将[npc2.herHim]缓慢地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc.Name]推开[npc3.name]让开收回[npc.cock+]，然后粗暴地抓住[npc2.namePos][npc2.antennae+]，"
										+ "用力地向[npc2.her]的脸顶去，强迫[npc2.herHim]把自己[npc.cock+]连根吞入。",
								"[npc.name]粗暴地推开[npc3.name]，弯下腰握住[npc2.namePos][npc2.antennae+]，无情地将[npc2.her]的头部拽向自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.name]粗暴地推开[npc3.name]，弯下腰握住[npc2.namePos][npc2.antennae+]，"
										+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。")));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc.Name]让[npc3.name]让开收回[npc.cock+]，然后把着[npc2.namePos][npc2.antennae+]，"
										+ "紧紧地把[npc2.her]的头拉过来强迫[npc2.herHim]把自己[npc.cock+]连根吞入。",
								"[npc.Name]推开[npc3.name]，而后俯下身坚定地抓住[npc2.namePos][npc2.antennae+]，坚定地将[npc2.herHim]拉近自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.name]推开[npc3.name]，弯下腰握住[npc2.namePos][npc2.antennae+]，"
										+ "然后将[npc2.herHim]坚定地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]握住[npc2.namePos][npc2.antennae+]，温柔地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her][npc.cock+]直到根部。",
								"[npc.Name]俯下身轻柔但坚定地抓住[npc2.namePos][npc2.antennae+]，缓缓将[npc2.her]拉近[npc.her]的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.antennae+]，"
										+ "然后将[npc2.herHim]温柔地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]粗鲁地抓住[npc2.namePos][npc2.antennae+]，残暴地将[npc2.her]的头部拉扯过来，强迫[npc2.herHim]把[npc.her][npc.cock+]从根部全都吞下。",
								"[npc.Name]向下抓住[npc2.namePos][npc2.antennae+]，无情地将[npc2.her]的头部拽向自己的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.Name]发出[npc.a_moan+]，弯下腰抓住[npc2.namePos][npc2.antennae+]，"
										+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]握住[npc2.namePos][npc2.antennae+]，紧紧地把[npc2.her]拉过来，迫使[npc2.herHim]吞下[npc.her][npc.cock+]直到底部。",
								"[npc.Name]俯下身紧紧地抓住[npc2.namePos][npc2.antennae+]，坚定地将[npc2.herHim]拉近[npc.her]的小腹，"
										+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]用[npc.her][npc.cock+]深喉。",
								"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.antennae+]，"
										+ "然后将[npc2.herHim]坚定地向前拉，让[npc.her][npc.cock+]塞进[npc2.her]的喉咙，使[npc2.her][npc2.lips+]紧贴着"
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her]的锁结":"根部")+"。"));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.tongue]轻轻摩挲着[npc.namePos][npc.cock]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.cock+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，用[npc2.her]的[npc2.tongue]轻轻摩挲着[npc.namePos][npc.cock]的下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.cock+]最终从[npc2.her]喉咙中拔出。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]垂下眸子，忍受了一小会[npc.namePos]的大胆举动，"
										+ "然后把[npc2.her]的头扭过来，厉声提醒[npc.herHim]谁才是主导者。",
								"[npc2.namePos]威胁似地低吼着，那震动的喉咙却同时带来了额外的快感，"
										+ "尽管如此，[npc.name]还是被吓得立即松开[npc2.hair(true)]，将自己[npc.cock+]从[npc2.her]的喉咙拔出来。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"蜷缩着的[npc2.her]合上[npc2.eyes]，[npc2.name]徒劳地抵抗着，拍打#IF(npc.isPlayer())你的#ELSE[npc.her]折磨者的#ENDIF股间，"
										+ "[npc.name]从[npc2.her]的喉咙中抽出[npc.her][npc.cock+]的瞬间，终于可以哭着喘息了。",
								"[npc2.namePos]压抑的哭泣和抽噎只会给#IF(npc.isPlayer())你#ELSE折磨[npc.her]的人#ENDIF带来额外的快感，"
										+ "但是，在花了几秒钟的时间拳打脚踢[npc.namePos]股间之后，"
										+ "[npc2.she]终于取得了一个小小的胜利，在[npc2.her][npc2.hair(true)]被放开且[npc.namePos][npc.cock+]暂时从[npc2.her]的喉咙里滑了出来时。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.her]的[npc2.eyes]里涌出泪水，[npc2.name]花了一会儿功夫用[npc2.tongue]摩挲着[npc.namePos][npc.cock]的下缘，"
										+ "[npc.she]从[npc2.her]的喉咙中抽出[npc.her][npc.cock+]的瞬间，终于可以大口喘气了。",
								"[npc2.name]开心地闷哼着，发出[npc2.moaning]，[npc2.tongue]亲密地摩挲着[npc.namePos]的[npc.cock]下缘，"
										+ "[npc2.her]顺从地屏住呼吸，直到[npc.her][npc.cock+]最终从[npc2.her]喉咙中拔出。"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "收紧喉穴";
		}

		@Override
		public String getActionDescription() {
			return "用你肌肉发达的喉咙挤弄包裹着[npc2.namePos]的[npc2.cock]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getFaceOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"[npc.Name]发出一阵低沉的[npc.moan]，继续专心用[npc.her]肌肉极致发达的喉咙挤弄包裹着[npc2.namePos][npc2.cock+]。",
						(!isTargetedCharacterInanimate()
							?"[npc.Name]发出一声低沉的[npc.moan]，当[npc.she]专注于控制[npc.her]喉咙内侧的额外肌肉时。"
								+ "[npc.Name]挤弄包裹着口中[npc2.namePos][npc2.cock+]，让[npc2.herHim]不禁漏出一声愉悦的呻吟。"
							:""),
						"[npc.Name]发出一连串低沉的[npc.moans]，继续专心用[npc.her]肌肉极致发达的喉咙挤弄包裹着[npc2.namePos][npc2.cock+]。",
						"随着一声闷闷的[npc.moan]，[npc.name][npc.verb(focus)]于控制[npc.her]的喉咙深处的额外肌肉，将它们向下紧夹，并按摩[npc2.namePos][npc2.cock+].");
		}
	};
	
	public static final SexAction BLOWJOB_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "接受口交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.cock+]插进[npc2.namePos]嘴里，让[npc2.herHim]开始为你口交。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]伸手抓住[npc2.namePos]头部，将其[npc.cock][npc.cockHead+]移向[npc2.her][npc2.lips+]，"
										+ "然后轻轻地将[npc.her]的[npc.hips]向前推，将[npc.her][npc.cock+]滑入[npc2.her]的嘴里。",

								"[npc.name]伸手扒住[npc2.namePos]的头部，将其[npc.cock][npc.cockHead+]紧紧贴住[npc2.namePos][npc2.lips+]，"
										+ "[npc.she]缓慢地在[npc2.her]的[npc2.face]上抽动[npc.her]的[npc.hips]，将[npc.her][npc.cock+]温柔地滑入[npc2.her]的嘴里。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]伸手抓住[npc2.namePos]头部，将其[npc.cock][npc.cockHead+]移向[npc2.her][npc2.lips+]，"
										+ "然后急切地将[npc.her]的[npc.hips]向前推，将[npc.her][npc.cock+]滑入[npc2.her]的嘴里。",

								"[npc.name]伸手扒住[npc2.namePos]的头部，将其[npc.cock][npc.cockHead+]紧紧贴住[npc2.namePos][npc2.lips+]，"
										+ "[npc.she]急切地在[npc2.her]的[npc2.face]上抽动[npc.her]的[npc.hips]，将[npc.her][npc.cock+]滑入[npc2.her]的嘴里。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]伸手抓住[npc2.namePos]头部，将其[npc.cock][npc.cockHead+]移向[npc2.her][npc2.lips+]，"
										+ "然后粗暴地向前猛拱[npc.hips]，将那[npc.cock+]深深插入[npc2.her]嘴里。",

								"[npc.name]伸手扒住[npc2.namePos]的头部，将其[npc.cock][npc.cockHead+]紧紧贴住[npc2.namePos][npc2.lips+]，"
										+ "然后粗暴地用[npc.hips]向[npc2.her]的[npc2.face]猛拱，将自己[npc.cock+]深深插入[npc2.her]的嘴里。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]伸手抓住[npc2.namePos]头部，将其[npc.cock][npc.cockHead+]移向[npc2.her][npc2.lips+]，"
										+ "然后将[npc.her]的[npc.hips]向前推，将[npc.her][npc.cock+]滑入[npc2.her]的嘴里。",

								"[npc.name]伸手扒住[npc2.namePos]的头部，将其[npc.cock][npc.cockHead+]紧紧贴住[npc2.namePos][npc2.lips+]，"
										+ "[npc.she]在[npc2.her]的[npc2.face]上抽动[npc.her]的[npc.hips]，将[npc.her][npc.cock+]滑入[npc2.her]的嘴里。"));
						break;
					default:
						break;
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]稍微往后退一点，慢慢地将[npc.her][npc.cock+]放低到[npc2.namePos]嘴里，"
										+ "然后温柔地将[npc.cockHead+]推向[npc2.her][npc2.lips+]，压在[npc2.her][npc2.face+]上。",

								"[npc.name]温柔地把[npc.cock+]放到[npc2.namePos]嘴里，慢慢地张开[npc.legs]，"
										+ "强行把[npc.cockHead+]推向[npc2.her][npc2.lips+]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]稍微往后退一点，迅速地将[npc.her][npc.cock+]放低到[npc2.namePos]嘴里，"
										+ "然后急切地将[npc.cockHead+]推向[npc2.her][npc2.lips+]，压在[npc2.her][npc2.face+]上。",

								"[npc.name]快速地把[npc.cock+]拉到[npc2.namePos]嘴里，慢慢地张开[npc.legs]，"
										+ "强行把[npc.cockHead+]急切地推向[npc2.her][npc2.lips+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]稍微往后退一点，粗暴地将[npc.her][npc.cock+]按在[npc2.namePos]嘴上摩擦，"
										+ "然后激烈地将[npc.cockHead+]推向[npc2.her][npc2.lips+]，压在[npc2.her][npc2.face+]上。",

								"[npc.name]粗暴地把[npc.cock+]拉到[npc2.namePos]嘴里，慢慢地张开[npc.legs]"
										+ "强行把[npc.cockHead+]推向[npc2.her][npc2.lips+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]稍微往后退一点，迅速地将[npc.her][npc.cock+]放低到[npc2.namePos]嘴里，"
										+ "然后将[npc.cockHead+]推向[npc2.her][npc2.lips+]，压在[npc2.her][npc2.face+]上。",

								"[npc.name]快速地把[npc.cock+]拉到[npc2.namePos]嘴里，慢慢地张开[npc.legs]，"
										+ "强行把[npc.cockHead+]推向[npc2.her][npc2.lips+]。"));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]在[npc2.namePos][npc2.lips+]上滑动着[npc.cockHead+]，"
										+ "[npc.Name]轻轻地将[npc.her]的[npc.hips]向前推，将[npc.her][npc.cock+]滑入[npc2.her]的嘴里。",

								"[npc.name]把[npc.cockHead+]推向[npc2.namePos][npc2.lips+]，"
										+ "[npc.Name]慢慢地将[npc.her]的[npc.hips]推向[npc2.her]的[npc2.face]，将[npc.her][npc.cock+]滑入[npc2.her]的嘴里。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]在[npc2.namePos][npc2.lips+]上滑动着[npc.cockHead+]，"
										+ "[npc.Name]急切地将[npc.her][npc.hips]向前推，将[npc.her][npc.cock+]滑入[npc2.her]的嘴里。",

								"[npc.name]把[npc.cockHead+]推向[npc2.namePos][npc2.lips+]，"
										+ "[npc.Name]急切地将[npc.her][npc.hips]向前推，将[npc.her][npc.cock+]滑入[npc2.her]的嘴里。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]在[npc2.namePos][npc2.lips+]上滑动着[npc.cockHead+]，"
										+ "[npc.Name]粗暴地将[npc.her][npc.hips]向前推，强制将[npc.her][npc.cock+]滑入[npc2.her]的嘴里。",

								"[npc.name]在[npc2.namePos][npc2.lips+]上蹭着[npc.cockHead+]，"
										+"然后粗暴地用[npc.hips]向[npc2.her][npc2.face]猛冲，将自己[npc.cock+]深深插入[npc2.her]的嘴里。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]在[npc2.namePos][npc2.lips+]上滑动着[npc.cockHead+]，"
										+ "[npc.Name]将[npc.her]的[npc.hips]向前推，将[npc.her][npc.cock+]滑入[npc2.her]的嘴里。",

								"[npc.name]把[npc.cockHead+]推向[npc2.namePos][npc2.lips+]，"
										+ "[npc.Name]将[npc.her][npc.hips]向前推，将[npc.her][npc.cock+]滑入[npc2.her]的嘴里。"));
						break;
					default:
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵低沉地[npc2.a_moan+]，然后缓慢地向前伸头，开始温柔地吮吸[npc.namePos][npc.cock+]。",
	
								"[npc2.name]轻柔，低沉地[npc2.moan]，温柔地向前伸头，"
										+ "把[npc2.her][npc2.lips+]包裹在[npc.namePos][npc.cock+]周围，然后开始口交。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵[npc2.a_moan+]，急切地向前伸头，开始愉悦地吮吸[npc.namePos][npc.cock+]。",
	
								"[npc2.name]急切地[npc2.moan]，开心地向前伸头，"
										+ "把[npc2.her][npc2.lips+]包裹在[npc.namePos][npc.cock+]周围，然后开始口交。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵[npc2.a_moan+]，然后向前伸头，粗暴地开始吮吸[npc.namePos][npc.cock+]。",
	
								"[npc2.name]急切地[npc2.moan]，将头部努力伸向前，"
										+ "把[npc2.her][npc2.lips+]包裹在[npc.namePos][npc.cock+]周围，然后开始粗暴地口交。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵[npc2.a_moan+]，然后向前伸头，开始吮吸[npc.namePos][npc.cock+]。",
	
								"[npc2.name]低沉地[npc2.moan]，向前伸头，"
										+ "把[npc2.her][npc2.lips+]包裹在[npc.namePos][npc.cock+]周围，然后开始口交。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出低沉的[npc2.sob]， 被[npc.namePos][npc.cock+]呛在喉咙里，喘不过气来，慌乱地努力将头远离[npc.her]的腹股沟。",
	
								"[npc2.Name]发出低沉的[npc2.sob]，慌乱地努力拔着[npc.namePos][npc.cock+]，"
										+ "呛水感和窒息感让[npc2.she]局促不安地挣扎着，反抗着[npc.herHim]。"));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		//--- Additional methods: ---

		private List<GameCharacter> getOngoingCharacters() {
			return PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return PenisMouth.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterPerformingAction());
		}
		
		private String getOngoingNames() {
			return PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).contains(Main.sex.getCharacterPerformingAction())) {
				return false; // Do not allow additional blowjobs if the performing character is performing autofellatio
			}
			int size = PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size();
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				size--;
			}
			return size>0;
		}
		
		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();

			int size = getOngoingCharacters().size();

			map.put("[npc.nameIsFull] not performing autofellatio", !PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).contains(Main.sex.getCharacterPerformingAction()));
			map.put("one or two characters performing blowjob", size>0 && size<3);
			map.put("only ongoing penis-actions are blowjobs", size==Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).size());
			map.put("[npc2.namePos] mouth to be exposed", Main.sex.getCharacterTargetedForSexAction(this).isOrificeTypeExposed(SexAreaOrifice.MOUTH));
			map.put("[npc2.namePos] mouth to be free", SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterTargetedForSexAction(this)));
			
			return map;
		}
		
		//------
		
		@Override
		public String getActionTitle() {
			return "加入口交";
		}
		
		@Override
		public String getActionDescription() {
			return "让[npc2.name]加入"+getOngoingNames()+"一起给你口交。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"[npc.Name]对只有"+getOngoingNames()+"吮吸[npc.her]的[npc.cock]这件事感到并不满足，[npc.she]让"+(getOngoingCharacters().size()==1?"[npc3.name]":"他们")+"后退，暂时移开一点，"
										+ "然后将[npc.her]沾满唾液的[npc.cock+]的[npc.cockHead]顶在[npc2.namePos][npc2.lips+]上，温柔地将它送入[npc2.namePos]口中。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"[npc.Name]对只有"+getOngoingNames()+"吮吸[npc.her]的[npc.cock]这件事感到并不满足，[npc.she]将"+(getOngoingCharacters().size()==1?"[npc3.name]":"他们")+"推开，"
										+ "然后将[npc.her]沾满唾液的[npc.cock+]的[npc.cockHead]顶在[npc2.namePos][npc2.lips+]上，粗暴地将它塞入[npc2.namePos]口中。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"[npc.Name]对只有"+getOngoingNames()+"吮吸[npc.her]的[npc.cock]这件事感到并不满足，[npc.she]让"+(getOngoingCharacters().size()==1?"[npc3.name]":"他们")+"后退，暂时移开一点，"
										+ "然后将[npc.her]沾满唾液的[npc.cock+]的[npc.cockHead]顶在[npc2.namePos][npc2.lips+]上，然后将它送入[npc2.namePos]口中。"));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"[npc.Name]对只有"+getOngoingNames()+"吮吸[npc.her]的[npc.cock]这件事感到并不满足，[npc.she]让"+(getOngoingCharacters().size()==1?"[npc3.name]":"他们")+"后退，暂时移开一点，"
										+ "然后急切地将[npc.her]沾满唾液的[npc.cock+]的[npc.cockHead]顶在[npc2.namePos][npc2.lips+]上，迅速地将它送入[npc2.namePos]口中。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]慢慢地向前伸头加入到"+getOngoingNames()+"中，开始吮吸[npc.namePos][npc.cock+]，发出带着水声的[npc2.moan]。",
								"[npc2.name]轻柔，低沉地[npc2.moan]，温柔地向前伸头，"
										+"和"+getOngoingNames()+"一起，用[npc2.lips+]包裹着[npc.namePos][npc.cock+]，开始为[npc.herHim]口交。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]粗鲁地向前伸头加入到"+getOngoingNames()+"中，强迫[npc.namePos][npc.cock+]塞入自己喉咙，发出带着水声的[npc2.moan]。",
								"[npc2.name]急切地[npc2.moan]，将头部努力伸向前，"
										+"和"+getOngoingNames()+"一起，用[npc2.lips+]包裹着[npc.namePos][npc.cock+]，开始粗暴地给[npc.herHim]口交。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵[npc2.a_moan+]，然后向前伸头，开始与"+getOngoingNames()+"一起吮吸[npc.namePos][npc.cock+]。",
								"[npc2.name]低沉地[npc2.moan]，向前伸头，"
										+"和"+getOngoingNames()+"一起，用[npc2.lips+]包裹着[npc.namePos][npc.cock+]，开始为[npc.herHim]口交。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出低沉的[npc2.sob]， 被[npc.namePos][npc.cock+]呛在喉咙里，喘不过气来，慌乱地努力将头远离[npc.her]的腹股沟。",
								"[npc2.Name]发出低沉的[npc2.sob]，慌乱地努力拔着[npc.namePos][npc.cock+]，"
										+ "呛水感和窒息感让[npc2.she]局促不安地挣扎着，反抗着[npc.herHim]。"));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵[npc2.a_moan+]，急切地向前伸头，开始愉悦地与"+getOngoingNames()+"一起吮吸[npc.namePos][npc.cock+]。",
								"[npc2.name]急切地[npc2.moan]，开心地向前伸头，"
										+"和"+getOngoingNames()+"一起，用[npc2.lips+]包裹着[npc.namePos][npc.cock+]，开始为[npc.herHim]口交。"));
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
							"[npc2.name]饥渴地张开[npc2.lips+]包裹住[npc.namePos][npc.cock+]，"
									+ "发出含糊不清的[npc2.moan]，亢奋地上下甩头。",
		
							"[npc2.namePos]的口中飘出一声极其低沉的[npc2.moan]，"
									+"然后饥渴地张开嘴唇含住[npc.namePos][npc.cock+]，热情地侍奉着龟头。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，热切地用[npc2.lips+]含住[npc.namePos][npc.cock+]，"
									+ "急切地吮吸舔吻着[npc.cock]，继续发出含糊不清的声音。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]拼命想要逃离[npc.namePos]的[npc.cock]，但没能成功，"
									+ "[npc2.she]发出一阵[npc2.a_sob+]，无力地发出含糊不清的抗议声，泪水如小溪般在[npc2.her]的[npc2.face]上流了下来。",
		
							"[npc2.namePos]口中漏出一声低沉的[npc2.sob]，[npc2.she]无力地尝试逃脱，"
									+ "[npc.namePos][npc.cock+]仍然来回磨蹭着[npc2.her][npc2.lips+]，泪水如小溪般从[npc2.her]的[npc2.face]上流下。",
		
							"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
									+ "[npc2.she]无力地反抗着[npc.Name]，发出含糊不清的抗议声。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]张开[npc2.lips+]包裹住[npc.namePos][npc.cock+]，"
									+ "发出相当含混的[npc2.moan]，上下甩起头来。",
		
							"[npc2.namePos]的口中飘出一声极其低沉的[npc2.moan]，"
									+"然后张开嘴唇含住[npc.namePos][npc.cock+]侍奉着龟头。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.lips+]含住[npc.namePos][npc.cock+]，"
									+ "吮吸舔吻着[npc.cock]，继续发出含糊不清的声音。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name]用[npc2.lips+]温柔地裹住[npc.namePos][npc.cock+]，"
									+ "发出相当含混的[npc2.moan]，慢慢上下甩起头来。",
		
							"[npc2.namePos]的口中飘出一声极其低沉的[npc2.moan]，"
									+"然后温柔地地张开嘴唇含住[npc.namePos][npc.cock+]，热情地侍奉着龟头。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始温柔地用[npc2.lips+]含住[npc.namePos][npc.cock+]，"
									+ "温柔地吮吸舔吻着[npc.cock]，继续发出含糊不清的声音。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]激烈地用[npc2.lips+]含住[npc.namePos][npc.cock+]，"
									+ "发出相当含混的[npc2.moan]，粗暴地上下甩起头来。",
		
							"[npc2.namePos]的口中飘出一声极其低沉的[npc2.moan]，"
									+"然后用力张开嘴唇含住[npc.namePos][npc.cock+]，粗暴地玩弄着龟头。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地用[npc2.lips+]含住[npc.namePos][npc.cock+]，"
									+ "激烈地吮吸舔吻着[npc.cock]，继续发出含糊不清的声音。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "接受口交(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "接着抓住，让[npc2.name]吮吸你[npc.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]让"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让开一点，温柔地在[npc2.namePos][npc2.lips+]上划过[npc.cock+]，"
									+ "[npc.she]发出柔和的[npc.moan]，将[npc.hips]稳稳顶到[npc2.her][npc2.face]上。",
							"[npc.Name]示意"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让[npc2.name]主导，"
									+"[npc.Name]慢慢地将[npc.hips]向前推向[npc2.namePos]的[npc2.face]，有节奏地干着[npc2.name]的喉咙，不禁漏出一小声[npc.moan]。",
							"[npc.Name]让"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"给[npc2.name]让开位置，然后[npc.she]温柔地将[npc.hips+]压向[npc2.namePos]的[npc2.face]，"
									+ "[npc.she]发出轻柔的[npc.moan]，开始让[npc2.name]吸吮自己[npc.cock+]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name][npc.cock+]温柔地划过[npc2.namePos][npc2.lips+]，"
									+ "[npc.she]发出柔和的[npc.moan]，将[npc.hips]稳稳顶到[npc2.her][npc2.face]上。",
							"[npc.Name]慢慢地将[npc.hips]向前推向[npc2.namePos]的[npc2.face]，温柔地干着[npc2.name]的喉咙，不禁漏出一小声[npc.moan]。",
							"温柔地将[npc.hips+]拱上[npc2.namePos]的[npc2.face]，"
									+ "接受着[npc2.her]的口交，发出了轻轻的[npc.moan]。"));
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "接受口交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.cock+]按在[npc2.namePos]脸上，鼓励[npc2.herHim]接着吸。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]让"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让开一点，饥渴地在[npc2.namePos][npc2.lips+]上划过[npc.cock+]，"
									+ "发出[npc.a_moan+]，将[npc.hips]贪婪地顶到[npc2.her][npc2.face]上。",
							"不耐烦地指示"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让[npc2.name]主导，"
									+ "[npc.Name]急切地将[npc.hips]顶向[npc2.namePos]的[npc2.face]，发出[npc.a_moan+]，饥渴地侵犯着[npc2.her]的喉咙。",
							"[npc.Name]让"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"给[npc2.name]让开位置，然后[npc.she]热切地将[npc.hips+]压向[npc2.namePos]的[npc2.face]，"
									+ "[npc.she]发出[npc.a_moan+]，开始让[npc2.name]吸吮自己[npc.cock+]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地将[npc.cock+]顶进[npc2.namePos][npc2.lips+]间，"
									+ "发出[npc.a_moan+]，将[npc.hips]贪婪地顶到[npc2.her][npc2.face]上。",
							"[npc.Name]急切地将[npc.hips]顶向[npc2.namePos]的[npc2.face]，饥渴地侵犯着[npc2.her]的喉咙，发出[npc.a_moan+]。",
							"[npc.name]亢奋地将[npc.hips+]拱上[npc2.namePos]的[npc2.face]，"
									+ "开心地接受着[npc2.her]的口交，发出了轻轻的[npc.moan]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "深喉";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.cock+]粗暴地插进[npc2.namePos]的喉咙，好好深喉[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			List<String> descriptions = new ArrayList<>();

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.STANDING)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SITTING)) {

				if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
					
					UtilText.nodeContentSB.setLength(0);
					
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"强制"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让开，",
								"不耐烦地喊"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让[npc2.name]主导，",
								"命令"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"给[npc2.name]让开位置，"));
					
					for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getPenisModifiers()) {
						switch(pm) {
							case BARBED:
								descriptions.add("[npc.name]把[npc.cock+]顶入[npc2.namePos]喉咙深处。"
													+ "用[npc.hands]把住[npc2.her]的头，[npc.she]开始粗鲁地操[npc2.her]的[npc2.face]，"
													+ "随着[npc.namePos]的阴茎上的倒刺一再刮伤[npc2.her]的喉咙，这引起了泪水从[npc2.her]的[npc2.eyes]流出。");
								break;
							case FLARED:
								descriptions.add("[npc.name]把[npc.cock+]顶入[npc2.namePos]喉咙深处。"
													+ "用[npc.hands]把住[npc2.her]的头，[npc.she]开始粗鲁地操[npc2.her]的[npc2.face]，"
													+ "随着[npc.namePos]的肥厚平坦的龟头强行在[npc2.her]的喉咙中来回磨蹭，泪水从[npc2.namePos]的[npc2.eyes]里流出。");
								break;
							case KNOTTED:
								descriptions.add("[npc.name]猛然间粗鲁地将[npc.cock+]顶入[npc2.namePos]的喉咙深处。"
													+ "用[npc.hands]把住[npc2.her]的头，[npc.she]开始粗鲁地操[npc2.her]的[npc2.face]，"
													+ "随着[npc.namePos]反复用[npc.her]的结猛烈地撞击着[npc2.her]的[npc2.lips+]，泪水从[npc2.namePos]的[npc2.eyes]里流出。");
								break;
							default:
								break;
						}
					}
					
					descriptions.add("[npc.name]抓住[npc2.namePos]头部的一边，在[npc2.name]反应过来发生了什么之前，"
										+ "[npc.sheIs]粗暴地用[npc.cock+]在[npc2.her]的脸部小穴猛烈地抽送。");
					descriptions.add("[npc.Name]把[npc.cock+]猛捅进[npc2.namePos]的喉咙。"
										+ "[npc2.she]试图强忍住泪水，[npc.Name]却开始迅速地前后扭动[npc.hips]，"
										+ "双手按住[npc2.namePos]头部，[npc.she]无情地操着[npc2.her]的[npc2.face]。");
					descriptions.add("[npc.name]猛地将[npc2.name]的脸拉向自己腹股沟，"
										+ "将[npc.cock+]捅入[npc2.her]喉咙深处，粗鲁地抽插起来。");
					descriptions.add("[npc.name]粗暴地将[npc.cock+]捅入[npc2.namePos]的喉咙深处。"
										+ "看着粘稠的唾液"+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM)?"和先走液":"")
										+"从[npc2.name]嘴角淌出，[npc.name]退了退，让[npc2.name]缓了口气，然后继续猛然操向[npc2.her]的[npc2.face]。");

					
					return UtilText.nodeContentSB.toString()
							+Util.randomItemFrom(descriptions);
					
				} else {
					for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getPenisModifiers()) {
						switch(pm) {
							case BARBED:
								descriptions.add("[npc.name]猛然间粗鲁地将[npc.her][npc.cock+]深深插入[npc2.namePos]的喉咙。"
													+ "用[npc.hands]把住[npc2.her]的头，[npc.she]开始粗鲁地操[npc2.her]的[npc2.face]，"
													+ "随着[npc.namePos]的阴茎上的倒刺一再刮伤[npc2.her]的喉咙，这引起了泪水从[npc2.her]的[npc2.eyes]流出。");
								break;
							case FLARED:
								descriptions.add("[npc.name]猛然间粗鲁地将[npc.her][npc.cock+]深深插入[npc2.namePos]的喉咙。"
													+ "用[npc.hands]把住[npc2.her]的头，[npc.she]开始粗鲁地操[npc2.her]的[npc2.face]，"
													+ "随着[npc.namePos]的肥厚平坦的龟头强行在[npc2.her]的喉咙中来回磨蹭，泪水从[npc2.namePos]的[npc2.eyes]里流出。");
								break;
							case KNOTTED:
								descriptions.add("[npc.name]猛然间粗鲁地将[npc.her][npc.cock+]深深插入[npc2.namePos]的喉咙。"
													+ "用[npc.hands]把住[npc2.her]的头，[npc.she]开始粗鲁地操[npc2.her]的[npc2.face]，"
													+ "随着[npc.namePos]反复用[npc.her]的结猛烈地撞击着[npc2.her]的[npc2.lips+]，泪水从[npc2.namePos]的[npc2.eyes]里流出。");
								break;
							default:
								break;
						}
					}
					
					descriptions.add("[npc.Name]抓住[npc2.namePos]头部的一边，在[npc2.name]反应过来发生了什么之前，"
										+ "[npc.sheIs]粗暴地用[npc.cock+]在[npc2.her]的脸部小穴猛烈地抽送。");
					descriptions.add("[npc.Name]发出[npc.a_moan+]，把[npc.cock+]连根插入[npc2.namePos]喉咙。"
										+ "[npc2.she]试图强忍住泪水，[npc.Name]却开始迅速地前后扭动[npc.hips]，"
										+ "双手按住[npc2.namePos]头部，[npc.she]无情地操着[npc2.her]的[npc2.face]。");
					descriptions.add("[npc.name]抓住[npc2.namePos]头部的一边，猛地将[npc2.her]的脸拉到[npc.her]的腹股沟，"
										+ "将[npc.cock+]捅入[npc2.her]喉咙深处，粗鲁地抽插起来。");
					descriptions.add("[npc.name]有力地一顶，将[npc.cock+]捅入[npc2.namePos]喉咙深处。"
										+ "看着粘稠的唾液"+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM)?"和先走液":"")
										+"从[npc2.name]嘴角淌出，[npc.name]退了退，让[npc2.name]缓了口气，然后继续猛然操向[npc2.her]的[npc2.face]。");
					
					return Util.randomItemFrom(descriptions);
				}
				
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				
				for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getPenisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("[npc.Name]跪坐在[npc2.namePos]脸上，粗暴地向下一顶，把[npc.cock+]狠狠捅入[npc2.namePos]喉咙。"
												+ "根部蹭了下[npc2.her][npc2.lips+]，然后[npc.she]粗暴地操起[npc2.her]的脸，"
												+ "引得[npc2.name]发出含糊的[npc2.moan]，挣扎着喘息，"
												+"被[npc.cock+]上的倒刺刮蹭着喉咙，在自己身下颤抖不已。");
							break;
						case BLUNT:
							break;
						case FLARED:
							descriptions.add("[npc.Name]跪坐在[npc2.namePos]脸上，粗暴地向下一顶，把[npc.cock+]狠狠捅入[npc2.namePos]喉咙。"
												+ "根部蹭了下[npc2.her][npc2.lips+]，然后[npc.she]粗暴地操起[npc2.her]的脸，"
												+ "引得[npc2.name]发出含糊的[npc2.moan]，挣扎着喘息，"
												+"被宽大而平坦的龟头刮蹭着[npc2.name]喉咙，在[npc.Name]身下颤抖不已。");
							break;
						case KNOTTED:
							descriptions.add("[npc.Name]跪坐在[npc2.namePos]脸上，粗暴地向下一顶，把[npc.cock+]狠狠捅入[npc2.namePos]喉咙。"
												+ "根部蹭了下[npc2.her][npc2.lips+]，然后[npc.she]粗暴地操起[npc2.her]的脸，"
												+ "引得[npc2.name]发出含糊的[npc2.moan]，挣扎着喘息，"
												+"被[npc.cock+]底部膨胀的结刮蹭着[npc2.name]喉咙，在[npc.Name]身下颤抖不已。");
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
							+ "[npc.sheIs]粗暴地用[npc.cock+]在[npc2.her]的脸部小穴猛烈地抽送。");
					
				} else {
					descriptions.add("[npc.Name]向下把腹股沟压在仍迷迷糊糊的[npc2.namePos]脸上，"
							+ "[npc.sheIs]粗暴地用[npc.cock+]在[npc2.her]的脸部小穴猛烈地抽送。");
				}

				descriptions.add("[npc.Name]发出[npc.a_moan+]，把[npc.cock+]连根插入[npc2.namePos]喉咙。"
									+"无视强忍泪水的[npc2.name]，[npc.Name]快速地前后摆动[npc.hips]，"
									+"随心所欲地操着[npc2.name]的嘴，爽得不断[npc.moans+]。");

				descriptions.add("[npc.Name]压向[npc2.namePos]的脸，粗暴地将[npc.cock+]深深插入[npc2.her]的喉咙，"
									+ "发出[npc.a_moan+]，开始猛烈地前后撞击[npc.hips]，无情地操干[npc2.her]的脸。");

				descriptions.add("[npc.name]有力地一顶，将[npc.cock+]捅入[npc2.namePos]喉咙深处。"
									+"粘稠"+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM)?"又混合着精液":"")
									+"唾液从嘴角淌下，[npc.Name]一口气把[npc2.her]举了起来，"
										+"停了一下等[npc2.name]缓了口气，就以一个更快地节奏操了起来。");

				return Util.randomItemFrom(descriptions);
				
			} else {
				UtilText.nodeContentSB.setLength(0);

				if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"强迫"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让开，[npc.Name]粗暴地在[npc2.namePos][npc2.lips+]上划过[npc.cock+]，"
										+ "发出[npc.a_moan+]，粗暴地将[npc.hips]顶向[npc2.name]的[npc2.face]。",
								"喊"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让[npc2.name]主导，"
										+ "[npc.Name]发出[npc.a_moan+]，将[npc.hips]顶向[npc2.namePos]的[npc2.face]，侵犯着[npc2.her]的喉咙。",
								"[npc.Name]要求"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"给[npc2.name]让开位置，然后侵略十足地对着[npc2.her]的脸摆动[npc.hips+]，"
										+ "[npc.she]发出[npc.a_moan+]，用力地撞向[npc2.name]的脸。"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]粗暴地把[npc.cock+]怼向[npc2.namePos][npc2.lips+]，"
										+ "发出[npc.a_moan+]，粗暴地将[npc.hips]顶向[npc2.name]的[npc2.face]。",
								"[npc.Name]发出[npc.a_moan+]，用力的朝[npc2.namePos]的脸顶[npc.hips]，粗暴地侵犯着[npc2.her]的喉咙。",
								"[npc.name]侵略十足地顶[npc.hips+]撞向[npc2.namePos]的脸，"
										+"粗暴地持续怼着，操得[npc.Name][npc.a_moan+]。"));
				}
				UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
				
				return UtilText.nodeContentSB.toString();
			
			}
		}
		
	};
	
	public static final SexAction BLOWJOB_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "抵抗口交";
		}

		@Override
		public String getActionDescription() {
			return "努力远离[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"[npc.Name][npc.a_sob]着，拼命地想要把[npc2.namePos]的脸推开，可惜[npc2.namePos]虽然动作温柔，态度却十分坚定地"
											+"抓着他，在[npc3.name]给[npc.her]强制口交时，温柔地亲吻着[npc.her][npc.cock+]的周边以及[npc.balls]。",
									"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock+]从[npc3.namePos]的嘴里拔出来。"
											+"可惜[npc2.NamePos]抓的很紧，只能看着[npc2.she]在[npc3.name]吞吐着[npc.cock]时，温柔地"
											+ "亲吻着露在外面的[npc.cock+]。",
									"[npc.Name][npc.Sobbing+]着，想要从[npc3.namePos]嘴中抽回[npc.cock+]，但是[npc2.namePos]抓得太紧让自己动弹不得，"
											+ "只能看着[npc2.name]在[npc3.name]在自己大腿前上下摆动着头部时，"
											+ "温柔地亲吻着露在外面的[npc.cock]。")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"[npc.Name]小声[npc.a_sob]着，拼命地尝试挪开自己的腰，然而[npc2.name]粗暴地"
											+"抓着[npc.herHim]，在[npc3.name]无视自己意愿口交时，有力地亲吻着自己露出来[npc.cock+]和[npc.balls]。",
									"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock+]从[npc3.namePos]的嘴里拔出来。"
											+"可惜[npc2.namePos]抓得太紧，只能看着[npc2.namePos]在[npc3.namePos]的嘴唇在[npc.her]的[npc.cock]上上下移动时，"
											+"发出威胁性的咆哮。",
									"[npc.Name]发出[npc.Sobbing+]，想要从[npc3.namePos]口中抽回[npc.cock+]，但是[npc2.namePos]抓的太紧，"
											+ "只能看着[npc2.name]在[npc3.name]在自己大腿前上下摆动着头部时，"
											+"粗暴地亲吻着露在外面的[npc.cock]。")));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"[npc.Name]发出[npc.Sobbing+]，想要从[npc2.namePos]口中抽回[npc.cock+]，但是[npc2.namePos]紧紧地"
											+"抓着[npc.herHim]，在[npc3.name]无视自己意愿口交时，有力地亲吻着自己露出来[npc.cock+]和[npc.balls]。",
									"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock+]从[npc3.namePos]的嘴里拔出来。"
											+"可惜[npc2.NamePos]抓的很紧，只能看着[npc2.she]在[npc3.name]吞吐着[npc.cock]时，"
											+ "亲吻着露在外面的[npc.cock+]。",
									"[npc.Name][npc.Sobbing+]着，想要从[npc3.namePos]嘴中抽回[npc.cock+]，但是[npc2.namePos]抓得太紧让自己动弹不得，"
											+ "只能看着[npc2.name]在[npc3.name]在自己大腿前上下摆动着头部时，"
											+ "饥渴地亲吻着露在外面的[npc.cock+]。")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name][npc.a_sob]着，拼命地想要把[npc2.namePos]的脸推开，可惜[npc2.namePos]虽然动作温柔，态度却十分坚定地"
									+"抓着他，慢慢地用[npc2.her][npc2.lips+]滑过[npc.her][npc.cock+]，仍未停止这场强制的口交。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock+]从[npc2.namePos]的嘴里拔出来。"
									+"可惜[npc2.NamePos]抓的很紧，只能看着[npc2.she]动作温柔，态度坚定地继续吸着自己[npc.cock+]，无视自己无力的挣扎。",
	
							"[npc.Name][npc.Sobbing+]着，想要从[npc2.namePos]嘴中抽回[npc.cock+]，但是[npc2.namePos]抓得太紧让自己动弹不得"
									+ "只能一边毫无意义地挣扎，一边看[npc2.name]继续温柔而缓慢地为自己口交。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出[npc.a_sob+]，拼命地想要把[npc2.namePos]的脸推开，可惜[npc2.namePos]粗暴地抓住自己，"
									+"一边整根吞吐着自己[npc.cock+]，一边咆哮着威胁自己不要做无用挣扎。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock+]从[npc2.namePos]的嘴里拔出来。"
									+"可惜[npc2.her]抓的太紧，只能看着[npc2.her]一边整根吞吐着自己[npc.cock+]，一边咆哮着发出威胁。",
	
							"[npc.Name][npc.Sobbing+]着，想要从[npc2.namePos]嘴中抽回[npc.cock+]，但是[npc2.namePos]抓得太紧让自己动弹不得"
									+"只能看着[npc2.name]继续粗鲁地用力为自己口交。"));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出[npc.a_sob+]，拼命地想要把[npc2.namePos]的脸推开，可惜[npc2.namePos]狠狠地抓紧自己，"
									+"饥渴地吞吐着整根[npc.cock+]，无视自己意愿继续口交着。",
	
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把[npc.cock+]从[npc2.namePos]的嘴里拔出来。"
									+"可惜[npc2.her]抓的太紧，只能看着[npc2.she]无视自己无力地挣扎，饥渴地吮吸着自己的[npc.cock+]。",
	
							"[npc.Name][npc.Sobbing+]着，想要从[npc2.namePos]嘴中抽回[npc.cock+]，但是[npc2.namePos]抓得太紧让自己动弹不得"
									+"只能看着[npc2.name]无视自己无用的挣扎，饥渴地给自己口交。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "接受口交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.cock+]按在[npc2.namePos]脸上，鼓励[npc2.herHim]接着吸。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]让"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让开，把自己的[npc.cock+]塞入[npc2.namePos][npc2.lips+]间，"
									+ "发出[npc.a_moan+]，将[npc.hips]顶到[npc2.her][npc2.face]上。",
							"示意"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让[npc2.name]主导，"
									+ "[npc.name]发出[npc.a_moan+]，对着[npc2.name]的[npc2.face]顶[npc.hips]，侵犯着[npc2.her]的喉咙。",
							"[npc.Name]让"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"给[npc2.name]让开位置，然后[npc.she]将[npc.hips+]压向[npc2.namePos]的[npc2.face]，"
									+ "[npc.she]发出[npc.a_moan+]，开始让[npc2.name]吸吮自己[npc.cock+]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]将[npc.cock+]顶进[npc2.namePos][npc2.lips+]间，"
									+ "发出[npc.a_moan+]，将[npc.hips]顶到[npc2.her][npc2.face]上。",
							"[npc.Name]将[npc.hips]顶向[npc2.namePos]的[npc2.face]，侵犯着[npc2.her]的喉咙，发出[npc.a_moan+]。",
							"将[npc.hips+]拱上[npc2.namePos]的[npc2.face]，"
									+ "开心地接受着[npc2.her]的口交，发出了轻轻的[npc.moan]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "接受口交(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "饥渴地将你的[npc.hips]压向[npc2.namePos]的脸，将[npc.cock+]插入[npc2.her]的喉咙。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]让"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让开急切地将[npc.cock+]顶进[npc2.namePos][npc2.lips+]间，"
									+ "发出[npc.a_moan+]，将[npc.hips]贪婪地顶到[npc2.her][npc2.face]上。",
							"不耐烦地指示"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让[npc2.name]主导，"
									+ "[npc.Name]急切地将[npc.hips]顶向[npc2.namePos]的[npc2.face]，发出[npc.a_moan+]，饥渴地侵犯着[npc2.her]的喉咙。",
							"[npc.Name]让"+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"给[npc2.name]让开位置，然后[npc.she]热切地将[npc.hips+]压向[npc2.namePos]的[npc2.face]，"
									+ "[npc.she]发出[npc.a_moan+]，开始让[npc2.name]吸吮自己[npc.cock+]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地将[npc.cock+]顶进[npc2.namePos][npc2.lips+]间，"
									+ "发出[npc.a_moan+]，将[npc.hips]贪婪地顶到[npc2.her][npc2.face]上。",
							"[npc.Name]急切地将[npc.hips]顶向[npc2.namePos]的[npc2.face]，饥渴地侵犯着[npc2.her]的喉咙，发出[npc.a_moan+]。",
							"[npc.name]亢奋地将[npc.hips+]拱上[npc2.namePos]的[npc2.face]，"
									+ "开心地接受着[npc2.her]的口交，发出了轻轻的[npc.moan]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction BLOWJOB_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止接受口交";
		}

		@Override
		public String getActionDescription() {
			return "把你[npc.cock+]从[npc2.namePos]的嘴里拔出去，停止接受[npc2.herHim]的口交。";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				UtilText.nodeContentSB.append("[npc.name]挺起膝盖，使得自己[npc.cock+]在[npc2.namePos]口中滑进滑出。"
							+"龟头拔出时带出一丝晶莹的细线，又在插入时飞溅到[npc2.namePos]脸上。");
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]粗暴地再一次把[npc.cock+]插入[npc2.namePos]喉咙深处，然后就退[npc.hips]拔了出来，"
										+"笑着看[npc2.name]呛出精液不断吸气的样子。",

								"[npc.name]再一次顶[npc.hips]把[npc.cock+]强行插入[npc2.namePos]喉咙深处，然后就完全从[npc2.namePos]嘴里退了出来。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]将[npc.cock+]从[npc2.namePos]口中抽出，[npc.she]发出一阵[npc.a_moan+]，结束了这次口交。",

								"[npc.name]发出[npc.a_moan+]，缩回头，把[npc.her][npc.cock+]从[npc2.namePos]嘴里完全滑了出来。"));
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
								"[npc2.name]发出一声[npc2.a_moan+]，暴露了[npc2.her]希望继续吮吸[npc.namePos][npc.cock+]的欲望。",
	
								"[npc2.Name][npc2.moansVerb]，感受着[npc.name]从[npc2.her]嘴里拔出，努力抑制想继续吮吸那[npc.cock+]的欲望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction GIVING_BLOWJOB_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "提供口交";
		}

		@Override
		public String getActionDescription() {
			return "把[npc2.namePos][npc2.cock+]插进你的嘴里，开始为[npc2.herHim]口交。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos]喘着粗气，俯身低头埋在[npc2.namePos][npc2.legs]间，"
										+"热情十足地亲吻着[npc2.cockHead+]，然后把[npc2.her][npc2.cock+]含在口中。",

								"[npc.name]埋头在[npc2.namePos][npc2.legs]间，"
										+"耐心细致地将[npc2.cock+]舔了个遍，然后把[npc2.her][npc2.cockHead+]含入口中。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos]喘着粗气，饥渴地俯身低头埋在[npc2.namePos][npc2.legs]间，"
										+"热情十足地亲吻着[npc2.cockHead+]，然后把[npc2.her][npc2.cock+]含在口中。",

								"将[npc.her]的头急切地压入[npc2.namePos][npc2.legs]间，"
										+"[npc.name]耐心细致地亲吻着把[npc2.cock+]润了个遍，然后贪婪地把[npc2.her][npc2.cockHead+]含入口中。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos]喘着粗气，动作麻利地俯身低头埋在[npc2.namePos][npc2.legs]间，"
										+"粗暴地亲吻着[npc2.cockHead+]，然后强行把[npc2.her][npc2.cock+]吞在口中。",

								"[npc.name]埋头于[npc2.namePos][npc2.legs]间，"
										+"粗暴地亲吻着把[npc2.cock+]润了个遍，然后用力把[npc2.her][npc2.cockHead+]含入口中。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos]喘着粗气，俯身低头埋在[npc2.namePos][npc2.legs]间，"
										+"亲吻着[npc2.cockHead+]，然后把[npc2.her][npc2.cock+]含入口中。",

								"[npc.name]埋头于[npc2.namePos][npc2.legs]间，"
										+"耐心地亲吻着把[npc2.cock+]润了个遍，然后用力把[npc2.her][npc2.cockHead+]含入口中。"));
						break;
					default:
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]把[npc2.namePos][npc2.cockHead+]含在[npc.lips+]间，"
										+"慢慢地把[npc2.cock+]吞入嘴中，漏出带着水声的[npc.moan]，开始为[npc2.herHim]口交。",

								"[npc.name]温柔地打开[npc.lips+]包裹住[npc2.namePos][npc2.cockHead+]，"
										+"含糊不清地[npc.moan]着，开始为[npc2.herHim]口交。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]开心地打开[npc.lips+]包裹住[npc2.namePos][npc2.cockHead+]，"
										+"含糊不清地[npc.moan]着，情绪高涨地开始为[npc2.herHim]口交。",

								"[npc.name]饥渴地打开[npc.lips+]包裹住[npc2.namePos][npc2.cockHead+]，"
										+"含糊不清地[npc.moan]着，带着热情开始为[npc2.herHim]口交。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]用力地打开[npc.lips+]包裹住[npc2.namePos][npc2.cockHead+]，"
										+"含糊不清地[npc.moan]着，无视[npc.she]意愿开始强制口交。",

								"[npc.name][npc.lips+]将[npc2.cockHead+]猛然包裹住，"
										+ "[npc.Name]发出一声含混不清的[npc.moan]，[npc.she]开始粗暴地给[npc.herself]进行口交。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name]打开[npc.lips+]包裹住[npc2.namePos][npc2.cockHead+]，"
										+"含糊不清地[npc.moan]着，开始为[npc2.herHim]口交。",

								"[npc.name]打开[npc.lips+]包裹住[npc2.namePos][npc2.cockHead+]，"
										+"含糊不清地[npc.moan]着，饥渴地开始为[npc2.herHim]口交。"));
						break;
					default:
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]将[npc2.hips]温柔地顶向[npc.namePos]的[npc.face]，让[npc.name]温柔地吮吸[npc2.her][npc2.cock+]，[npc2.Moaning+]着。",
	
								"慢慢地摆动[npc2.hips]，"
										+ "[npc2.name]发出柔和的[npc2.moan]，享受着[npc.namePos][npc.lips+]上下抚弄整根[npc2.cock+]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]贪婪地将[npc2.hips]顶向[npc.namePos]的[npc.face]，让[npc.name]吮吸[npc2.her][npc2.cock+]，[npc2.Moaning+]着。",
	
								"积极地摆动[npc2.hips]，"
										+ "[npc2.name]发出[npc2.a_moan+]，享受着[npc.namePos][npc.lips+]上下抚弄整根[npc2.cock+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]将[npc2.hips]粗暴地顶向[npc.namePos]的[npc.face]，让[npc.name]吮吸[npc2.her][npc2.clit+]，[npc2.Moaning+]着。",
	
								"粗鲁地摆动[npc2.hips]，"
										+ "[npc2.name]发出[npc2.a_moan+]，享受着[npc.namePos][npc.lips+]上下抚弄整根[npc2.cock+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]将[npc2.hips]顶向[npc.namePos]的[npc.face]，让[npc.name]吮吸[npc2.her][npc2.cock+]，[npc2.Moaning+]着。",
	
								"摆动着[npc2.hips]，"
										+ "[npc2.name]发出[npc2.a_moan+]，享受着[npc.namePos][npc.lips+]上下抚弄整根[npc2.cock+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc.a_sob+]，努力不被强迫进行的口交影响，含糊不清地哀求[npc.namePos]不要再含着自己的[npc2.cock]了。",
	
								"[npc2.name]脸上泪水流下，和口水混在一起，颤抖着抵抗着强迫被口交带来的禁忌的快感。[npc2.she]带着啜泣声和口腔中含着东西产生的水声，哀求[npc.name]不要继续了。"));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction GIVING_BLOWJOB_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
		}
		
		//--- Additional methods: ---

		private List<GameCharacter> getOngoingCharacters() {
			return PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return PenisMouth.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		private String getOngoingNames() {
			return PenisMouth.getOngoingNames(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return false; // Do not allow additional blowjobs if the targeted character is performing autofellatio
			}
			int size = getOngoingCharacters().size();
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				size--;
			}
			return size>0;
		}
		
		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();

			int size = getOngoingCharacters().size();

			map.put("[npc2.nameIsFull] not performing autofellatio", !PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).contains(Main.sex.getCharacterTargetedForSexAction(this)));
			map.put("one or two characters performing blowjob", size>0 && size<3);
			map.put("only ongoing penis-actions are blowjobs", size==Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS).size());
			map.put("[npc.namePos] mouth to be exposed", Main.sex.getCharacterPerformingAction().isOrificeTypeExposed(SexAreaOrifice.MOUTH));
			map.put("[npc.namePos] mouth to be free", SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterPerformingAction()));
			
			return map;
		}
		
		//------
		
		@Override
		public String getActionTitle() {
			return "加入口交";
		}

		@Override
		public String getActionDescription() {
			return "加入"+getOngoingNames()+"为[npc2.herHim]口交，协助舔弄[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							getOngoingNames()+"的口交勾起了[npc.Name]的欲望，[npc.Name]靠近"
								+"[npc2.name][npc2.penisGirth][npc2.cock+]，温柔地"+(getOngoingCharacters().size()==1?"和[npc3.herHim]一起":"交错着")+"亲吻着柱身。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							getOngoingNames()+"的口交勾起了[npc.Name]的欲望，[npc.Name]挤近"
								+"[npc2.name][npc2.penisGirth][npc2.cock+]，温柔地"+(getOngoingCharacters().size()==1?"和[npc3.herHim]一起":"交错着")+"亲吻着柱身。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							getOngoingNames()+"的口交勾起了[npc.Name]的欲望，[npc.Name]靠近"
								+"[npc2.name][npc2.penisGirth][npc2.cock+]，利落地"+(getOngoingCharacters().size()==1?"和[npc3.herHim]一起":"交错着")+"亲吻着柱身。"));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							getOngoingNames()+"的口交勾起了[npc.Name]的欲望，[npc.Name]饥渴地挤向"
								+"[npc2.name][npc2.penisGirth][npc2.cock+]，热情地"+(getOngoingCharacters().size()==1?"和[npc3.herHim]一起":"交错着")+"亲吻着柱身。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								"[npc2.Name][npc2.moan]着，慢慢地示意"+getOngoingNames()+" 先停一下，"
										+"让[npc.Name]先把自己[npc2.cock+]含进嘴里。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								"[npc2.Name][npc2.moan]着，满意地示意"+getOngoingNames()+" 先停一下，强迫[npc.Name]把自己[npc2.cock+]含进嘴里。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								"[npc2.Name][npc2.moan]着，示意"+getOngoingNames()+" 先停一下，"
										+"让[npc.Name]先把自己[npc2.cock+]含进嘴里。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								" [npc2.Name]苦痛狂乱的[npc2.moan]"
										+"在[npc.Name]推开"+getOngoingNames()+"把自己的[npc2.cock+]含入口中时达到了顶点。"));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								"[npc2.Name][npc2.moan]着，示意"+getOngoingNames()+" 先停一下，"
										+"高兴地鼓励[npc.Name]把自己[npc2.cock+]含进嘴里。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	// TODO add descriptions of kissing knot, flared head pushing down throat, tentacles, ribs and barbs
	
	public static final SexAction GIVING_BLOWJOB_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
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
			return "把[npc2.namePos][npc2.cock+]吞得尽可能深。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"把[npc3.name]推开，[npc.Name]温柔地张开[npc.lips+]含住[npc2.namePos][npc2.cock+]，"
											+"向前伸头，把它吞得尽可能深。",
									"轻轻[npc.moan]着，[npc.Name]推开[npc3.name]，自己凑到[npc2.namePos][npc2.cock+]前，"
											+"张开[npc.lips+]并尽可能地把[npc2.namePos][npc2.cock+]插入[npc.her]的喉咙深处。",
									"[npc.Name]推开[npc3.name]，慢慢伸头"
											+"张开[npc.lips+]把[npc2.cock+]吞到喉咙深处。")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"[npc.Name]毫不客气地把[npc3.name]推开，粗暴地张开[npc.lips+]含住[npc2.namePos][npc2.cock+]，"
											+"向前伸头强行把它尽可能往喉咙深处塞。",
									"[npc.Name]发出[npc.a_moan+]，粗暴地将[npc3.name]推离[npc2.namePos][npc2.cock+]，迅速倾身向前，"
											+"张开[npc.lips+]尽可能用力把[npc2.namePos][npc2.cock+]往喉咙深处塞去。",
									"[npc.Name]富有侵略性地把[npc3.name]推开，向前伸头"
											+ "贪婪地张开[npc.lips+]强行把[npc2.namePos][npc2.cock+]塞进喉咙深处。")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"把[npc3.name]推开，[npc.Name]张开[npc.lips+]含住[npc2.namePos][npc2.cock+]，"
											+"向前伸头，把它吞得尽可能深。",
									"[npc.Name]发出[npc.a_moan+]，把[npc3.name]推离[npc2.namePos][npc2.cock+]，然后倾身向前，"
											+"张开[npc.lips+]并尽可能地把[npc2.namePos][npc2.cock+]插入[npc.her]的喉咙深处。",
									"[npc.Name]把[npc3.name]挪开，向前伸头"
											+"张开[npc.lips+]把[npc2.namePos][npc2.cock+]塞进喉咙深处。")));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"[npc.Name]把[npc3.name]推开，饥渴地张嘴含住[npc2.namePos][npc2.cock+]，"
											+"把它往喉咙深处贪婪地咽着。",
									"[npc.Name]发出[npc.a_moan+]，迅速推开[npc3.name]，饥渴地靠向[npc2.namePos][npc2.cock+]，"
											+ "饥渴地张开[npc.lips+]并尽可能地把[npc2.namePos][npc2.cock+]吞入[npc.her]的喉咙深处。",
									"[npc.Name]不耐烦地挪开[npc3.name]，向前伸头，"
											+"贪婪地张开嘴把[npc2.namePos][npc2.cock+]含进喉咙深处。")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"温柔地用嘴包裹着[npc2.cock+]，[npc.Name]伸头，"
										+ "把[npc2.namePos][npc2.cock+]尽可能深地插入[npc.she]的喉咙",
	
								"随着一声柔软的，含混不清的[npc.moan]，[npc.name]小心翼翼地前倾，"
										+ "[npc.her]张开[npc.lips+]并尽可能地把[npc2.namePos][npc2.cock+]深入[npc.her]的喉咙深处。",
	
								"慢慢把[npc.her]的脑袋向前滑动，[npc.name]温柔地张开她[npc.lips+]，以便将[npc2.namePos][npc2.cock+]深入[npc.her]的喉咙。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"饥渴地用[npc.lips+]把嘴里的[npc2.cock+]完全包裹住，[npc.name]将头快速地往里推。"
										+ "贪婪地把[npc2.namePos][npc2.cock+]尽可能深地插入[npc.she]的喉咙",
	
								"随着一声含混不清的[npc.moan+]，[npc.name]急切地前倾，"
										+ "[npc.her]饥渴地张开[npc.lips+]并尽可能地把[npc2.namePos][npc2.cock+]深入[npc.her]的喉咙深处。",
	
								"[npc.name]将脑袋贪婪地往下滑动，欣然张开她[npc.lips+]，将[npc2.namePos][npc2.cock+]深入[npc.her]的喉咙。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name][npc.lips+]将[npc2.cock+]猛然包裹住，[npc.her]把头粗暴地向下推压。"
										+ "强迫[npc2.namePos][npc2.cock+]尽可能深地插入[npc.she]的喉咙",
	
								"随着一声含混不清的[npc.moan+]，[npc.name]迅速前倾，"
										+ "[npc.her]粗暴地张开[npc.lips+]并迫使[npc2.namePos][npc2.cock+]深入[npc.her]的喉咙深处。",
	
								"激烈地将[npc.her]的脑袋向下推动，[npc.name]张开[npc.her][npc.lips+]，迫使[npc2.namePos][npc2.cock+]深深推入[npc.her]的喉咙。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"用[npc.lips+]把嘴里的[npc2.cock+]完全包裹住，[npc.name]将头快速地往里推。"
										+ "把[npc2.namePos][npc2.cock+]尽可能深地插入[npc.she]的喉咙",
	
								"随着一声含混不清的[npc.moan+]，[npc.name]前倾，"
										+ "[npc.her]张开[npc.lips+]并尽可能地把[npc2.namePos][npc2.cock+]深入[npc.her]的喉咙深处。",
	
								"[npc.Name]伸头张开[npc.lips+]，把[npc2.namePos][npc2.cock+]吞入喉咙深处。"));
						break;
					default:
						break;
				}
				
				UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			}
			
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(action);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					default:
						return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"[npc2.Name]贪婪地将自己[npc2.cock+]深入[npc3.namePos]的喉咙，"
											+ "[npc2.she]发出[npc2.a_moan+]，热情地接受群体口交。",
									"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]挺动[npc2.cock+]，与[npc3.namePos]一起深深插入[npc.namePos]的喉咙。",
									"[npc2.name]愉悦地[npc2.moaning]着，热切地挺动[npc2.her][npc2.cock+]，拼命要插入[npc3.namePos]喉咙的最深处。"));
					case SUB_RESISTING:
						return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc2.name]无法将[npc2.cock]从"+PenisMouth.getOngoingNames(target)+"中抽出，"
										+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
								"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开"+PenisMouth.getOngoingNames(target)+"，"
										+ "即使[npc2.her]全力抵抗，[npc2.her][npc2.cock+]依然被无情地玩弄着。",
								"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.cock]从"+PenisMouth.getOngoingNames(target)+"中抽离。"));
					case DOM_GENTLE:
						return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc2.Name]温柔地将[npc2.cock+]滑入[npc3.namePos]的喉咙深处，"
										+ "[npc2.she]发出柔和的[npc2.moan]，享受着群体口交。",
								"[npc2.name]温柔地将[npc2.cock+]深入[npc3.namePos]的喉咙，口中飘出一声轻柔的[npc2.moan]。",
								"[npc2.name]愉悦地[npc2.moaning]着，温柔地用[npc2.cock+]深入[npc3.namePos]的喉咙。"));
					case DOM_ROUGH:
						return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc2.Name]粗暴地将[npc2.cock+]深深插入[npc3.namePos]的喉咙，"
										+ "[npc2.she]接受着群体口交，不禁发出一阵[npc2.a_moan+]。",
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]粗暴地挺动[npc2.cock+]，与[npc3.namePos]一起深深插入[npc.namePos]的喉咙。",
								"[npc2.name]愉悦地[npc2.moaning]着，粗暴地顶着[npc2.her][npc2.cock+]，拼命要插入[npc3.namePos]喉咙最深处。"));
					case SUB_NORMAL:
						return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"[npc2.Name]将[npc2.cock+]深深插入[npc3.namePos]的喉咙，"
										+ "[npc2.she]接受着群体口交，不禁发出一阵[npc2.a_moan+]。",
								"[npc2.namePos]的唇间飘出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]深深插入[npc3.namePos]的喉咙。",
								"[npc2.name]愉悦地[npc2.moaning]着，开始将[npc2.cock+]深深插入[npc3.namePos]的喉咙。"));
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					default:
						return UtilText.returnStringAtRandom(
								"[npc2.Name]贪婪地将自己[npc2.cock+]深入[npc.namePos]的喉咙，"
										+ "[npc2.she]发出[npc2.a_moan+]，亢奋地接受口交。",
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]深深插入[npc.namePos]的喉咙。",
								"[npc2.name]愉悦地[npc2.moaning]着，热切地挺动[npc2.her][npc2.cock+]，拼命要插入[npc.namePos]喉咙的最深处。");
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"[npc2.name]无法将[npc2.cock]从[npc.namePos]的口中抽出，"
										+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
								"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
										+ "即使对方全力抵抗，[npc.name]依然迫使[npc2.her][npc2.cock+]在自己的喉咙中继续抽插。",
								"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试将[npc2.cock]从[npc.namePos]口中抽离。");
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc2.Name]温柔地将[npc2.cock+]滑入[npc.namePos]的喉咙深处，"
										+ "[npc2.she]发出柔和的[npc2.moan]，享受着[npc.namePos]的口交。",
								"[npc2.namePos]的唇间飘出一阵[npc2.A_moan+]，[npc2.she]慢慢地将[npc2.cock+]深深插入[npc.namePos]的喉咙。",
								"[npc2.name]愉悦地[npc2.moaning]着，温柔地用[npc2.cock+]深入[npc.namePos]的喉咙。");
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc2.Name]粗暴地将[npc2.cock+]深深插入[npc.namePos]的喉咙，"
										+ "[npc2.she]接受着口交，不禁发出一阵[npc2.a_moan+]。",
								"[npc2.namePos]的唇间飘出一阵[npc2.A_moan+]，[npc2.she]猛烈地将[npc2.cock+]深深插入[npc.namePos]的喉咙。",
								"[npc2.name]愉悦地[npc2.moaning]着，粗暴地顶着[npc2.her][npc2.cock+]，拼命要插入[npc.namePos]喉咙最深处。");
					case SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"[npc2.Name]将[npc2.cock+]深深插入[npc.namePos]的喉咙，"
										+ "[npc2.she]接受着口交，不禁发出一阵[npc2.a_moan+]。",
								"[npc2.namePos]的唇间迸发出一阵[npc2.A_moan+]，[npc2.she]将[npc2.cock+]深深插入[npc.namePos]的喉咙。",
								"[npc2.name]愉悦地[npc2.moaning]着，开始将[npc2.cock+]深深插入[npc.namePos]的喉咙。");
				}
			}
		}
		return "";
	}
	
	public static final SexAction GIVING_BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "口交(温柔)";
		}

		@Override
		public String getActionDescription() {
			return "吸吮[npc2.namePos][npc2.cock+](温柔)。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]头贴着"+PenisMouth.getOngoingNames(target, performer)+"，"
								+"对着[npc2.namePos][npc2.cock+]露出的部分温柔地舔吻。",
						"[npc.name]头贴着"+PenisMouth.getOngoingNames(target, performer)+"发出[npc.moan]，"
								+"倾身嗅吻着[npc2.namePos][npc2.cock+]剩下的部分。",
						"[npc.name]头紧贴着"+PenisMouth.getOngoingNames(target, performer)
							+"，[npc.name]极尽所能地温顺亲舔着[npc2.namePos][npc2.cock+]所有露出来的地方。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"温柔地用[npc.lips+]包裹住[npc2.namePos][npc2.cock+]后，[npc.name]把头上下移动着，深情地给[npc2.namePos]口交。",
						"随着一声柔软的，含混不清的[npc.moan]，[npc.name]开始和缓地上下移动[npc.her]的脑袋，"
								+"含住[npc2.namePos][npc2.cock+]的上部吮吸着。",
						"慢慢地把头上下移动，[npc.name]温柔地用[npc.her][npc.lips+]包裹住[npc2.namePos][npc2.cock+]，为[npc2.namePos]口交。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "提供口交";
		}

		@Override
		public String getActionDescription() {
			return "迫切地吸吮[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]十分开心，头紧贴着"+PenisMouth.getOngoingNames(target, performer)+"，"
								+"对着[npc2.namePos][npc2.cock+]剩下露出的部分饥渴地[npc.Name]舔吻。",
						"[npc.Name]发出[npc.a_moan]，不耐烦地头靠头贴着"+PenisMouth.getOngoingNames(target, performer)+"，"
								+"倾身饥渴地嗅吻着[npc2.namePos][npc2.cock+]剩下的部分。",
						"[npc.name]头紧贴着"+PenisMouth.getOngoingNames(target, performer)
							+"，[npc.name]极尽所能地贪婪亲舔着[npc2.namePos][npc2.cock+]所有露出来的地方。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"急切地用[npc.lips+]包裹住[npc2.namePos][npc2.cock+]后，[npc.name]把头迅速地上下移动着，热情地给[npc2.namePos]口交。",
						"随着一声含混不清的[npc.moan]，[npc.name]开始迅速地上下移动起[npc.her]的脑袋，"
								+"含住[npc2.namePos][npc2.cock+]的上部贪婪地吮吸着。",
						"迅速地把头上下移动，[npc.name]温柔地用[npc.her][npc.lips+]饥渴地包裹住[npc2.namePos][npc2.cock+]，为[npc2.namePos]如饥似渴地口交。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "口交(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地吮吸[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]不耐烦地弯腰贴着"+PenisMouth.getOngoingNames(target, performer)+"的头，"
								+"对着[npc2.namePos][npc2.cock+]露出的部分粗暴地舔吻。",
						"[npc.Name]充满侵略性地头贴头靠着"+PenisMouth.getOngoingNames(target, performer)+"，发出[npc.a_moan+]，"
								+"粗暴地用[npc.lips+]挑逗着[npc2.namePos][npc2.cock+]剩下的部分。",
						"[npc.name]头紧贴着"+PenisMouth.getOngoingNames(target, performer)
							+"，[npc.name]激烈地亲舔着[npc2.namePos][npc2.cock+]所有露出来的地方。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"用力地将[npc.lips+]缠绕上[npc2.namePos][npc2.cock+]，[npc.name]将脑袋大幅度地上下移动起来，粗暴地为[npc2.herHim]口交着。",
						"随着一声含混不清的[npc.moan]，[npc.name]开始激烈地上下移动[npc.her]的脑袋，"
								+"张开嘴粗暴地含着[npc2.namePos][npc2.cock+]的上部吮吸着。",
						"[npc.Name]粗暴地上下移动着头部，支配地吞吐着[npc2.namePos][npc2.cock+]，强行给[npc2.herHim]口交。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗提供口交";
		}

		@Override
		public String getActionDescription() {
			return "努力把[npc2.namePos][npc2.cock+]挤出你的嘴。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.sob]因为强制口交变得模糊不清。[npc.Name]挣扎着，对抗着[npc2.name]"
										+"温柔的压制。但身体却屈服于欲望，抽搐着背叛了思维。只能痛苦而兴奋地，和"+PenisMouth.getOngoingNames(target, performer)+"一起取悦着[npc2.cock+]",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把头缩回来，远离[npc2.namePos][npc2.cock+]，"
										+ "但完全是白费力气，[npc2.name]温柔但强而有力地将[npc.her]抓到"+PenisMouth.getOngoingNames(target, performer)+"。",
								"[npc.Name]痛苦地[npc.sobbing]，挣扎着，想推开[npc2.name]，"
										+ "却浑身无力，只能被[npc2.her]迅速拉回到"+PenisMouth.getOngoingNames(target, performer)+"旁边，服侍沾满口水和泪水[npc2.cock+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.sob]因为强制口交变得模糊不清。[npc.Name]挣扎着，对抗着[npc2.name]"
										+"粗暴的压制。但身体却屈服于欲望，抽搐着背叛了思维。只能痛苦而兴奋地，和"+PenisMouth.getOngoingNames(target, performer)+"一起取悦着[npc2.cock+]",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把头缩回来，远离[npc2.namePos][npc2.cock+]，"
										+ "但完全是白费力气，[npc2.name]粗暴且强而有力地将[npc.her]抓到"+PenisMouth.getOngoingNames(target, performer)+"。",
								"[npc.Name]痛苦地[npc.sobbing]，挣扎着，想推开[npc2.name]，"
										+ "却浑身无力，只能被[npc2.her]强制摁回到"+PenisMouth.getOngoingNames(target, performer)+"旁边，服侍沾满口水和泪水[npc2.cock+]。"));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.sob]因为强制口交变得模糊不清。[npc.Name]挣扎着，对抗着[npc2.name]"
										+"饥渴的压制。但身体却屈服于欲望，抽搐着背叛了思维。只能痛苦而兴奋地，和"+PenisMouth.getOngoingNames(target, performer)+"一起取悦着[npc2.cock+]",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把头缩回来，远离[npc2.namePos][npc2.cock+]，"
										+ "但完全是白费力气，被[npc2.name]牢牢地着和"+PenisMouth.getOngoingNames(target, performer)+"一起取悦自己。",
								"[npc.Name]痛苦地[npc.sobbing]，挣扎着，想推开[npc2.name]，"
										+ "却浑身无力，只能被[npc2.her]迅速拉回到"+PenisMouth.getOngoingNames(target, performer)+"旁边，服侍沾满口水和泪水[npc2.cock+]。"));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.sob]因为强制口交变得模糊不清。[npc.Name]挣扎着，想挣脱[npc2.name]"
										+ "却被[npc2.her]慢慢地抓回，只能感受着[npc2.cock+]在唇间进进出出。",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把头缩回来，"
										+ "喉咙深处爆发出声嘶力竭的抗议声，但[npc2.name]依然温柔地将自己[npc2.cock+]推入[npc.her]的喉咙。",
								"[npc.Name]痛苦地[npc.sobbing]，挣扎着，想推开[npc2.name]，"
										+ "却浑身无力，只能被[npc2.her]温柔地拉回来，感受着沾满口水和泪水[npc2.cock+]在自己嘴里进进出出。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.sob]因为强制口交变得模糊不清。[npc.Name]挣扎着，想挣脱[npc2.name]，"
										+ "缺被[npc2.her]用力抓回[npc2.cock+]边，只能感受沾满口水和泪水的肉棒在自己唇间进进出出。",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把头缩回来，"
										+ "喉咙深处爆发出声嘶力竭的抗议声，但[npc2.name]依然粗暴地将自己[npc2.cock+]推入[npc.her]的喉咙。",
								"[npc.Name]痛苦地[npc.sobbing]，挣扎着，想推开[npc2.name]，"
										+ "却浑身无力，只能被[npc2.her]粗暴地拉回来，感受着沾满口水和泪水[npc2.cock+]在自己嘴里进进出出。"));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.sob]因为强制口交变得模糊不清。[npc.Name]挣扎着，想挣脱[npc2.name]"
										+"却被[npc2.her]饥渴地抓回，只能感受着沾满泪水和口水[npc2.cock+]在自己唇间进进出出。",
								"伴随着一声[npc.a_sob+]，[npc.Name]试着把头缩回来，"
										+ "喉咙深处爆发出声嘶力竭的抗议声，但[npc2.name]依然急切地将自己[npc2.cock+]推入[npc.her]的喉咙。",
								"[npc.Name]痛苦地[npc.sobbing]，挣扎着，想推开[npc2.name]，"
										+ "却浑身无力，只能被[npc2.her]饥渴地拉回来，感受着沾满口水和泪水[npc2.cock+]在自己嘴里进进出出。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction GIVING_BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "提供口交";
		}

		@Override
		public String getActionDescription() {
			return "接着吮吸[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]头贴着"+PenisMouth.getOngoingNames(target, performer)+"，"
								+"开始舔吻[npc2.namePos][npc2.cock+]露出的部分。",
						"[npc.Name][npc.a_moan]着，头靠头贴着"+PenisMouth.getOngoingNames(target, performer)+"，"
								+"倾身嗅吻着[npc2.namePos][npc2.cock+]剩下的部分。",
						"[npc.name]头紧贴着"+PenisMouth.getOngoingNames(target, performer)
							+"极尽所能地亲舔着[npc2.namePos][npc2.cock+]所有露出来的地方。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]张开[npc.lips+]，含住[npc2.namePos][npc2.cock+]，然后迅速地上下晃动着头给[npc2.herHim]口交。",
						"随着一声含混不清的[npc.moan]，[npc.name]开始迅速地上下移动起[npc.her]的脑袋，"
								+"含住[npc2.namePos][npc2.cock+]的上部吮吸着龟头。",
						"[npc.Name]张开[npc.lips+]，含住[npc2.namePos][npc2.cock+]，然后迅速地上下晃动着头给[npc2.herHim]口交。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "口交(渴求)";
		}

		@Override
		public String getActionDescription() {
			return "迫切地吸吮[npc2.namePos][npc2.cock+]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]十分开心，头紧贴着"+PenisMouth.getOngoingNames(target, performer)+"，"
								+"对着[npc2.namePos][npc2.cock+]剩下露出的部分饥渴地[npc.Name]舔吻。",
						"[npc.Name]发出[npc.a_moan]，不耐烦地头靠头贴着"+PenisMouth.getOngoingNames(target, performer)+"，"
								+"倾身饥渴地嗅吻着[npc2.namePos][npc2.cock+]剩下的部分。",
						"[npc.name]头紧贴着"+PenisMouth.getOngoingNames(target, performer)
							+"，[npc.name]极尽所能地贪婪亲舔着[npc2.namePos][npc2.cock+]所有露出来的地方。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"急切地用[npc.lips+]包裹住[npc2.namePos][npc2.cock+]后，[npc.name]把头迅速地上下移动着，热情地给[npc2.namePos]口交。",
						"随着一声含混不清的[npc.moan]，[npc.name]开始迅速地上下移动起[npc.her]的脑袋，"
								+"贪婪地打开[npc.lips+]，含住[npc2.namePos][npc2.cock+]，吮吸着龟头。",
						"迅速地把头上下移动，[npc.name]温柔地用[npc.her][npc.lips+]饥渴地包裹住[npc2.namePos][npc2.cock+]，为[npc2.namePos]如饥似渴地口交。"));
			}

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止口交";
		}

		@Override
		public String getActionDescription() {
			return "将[npc2.namePos][npc2.cock+]从口中拔出，停止口交[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"粗暴地将[npc2.namePos][npc2.cock+]往喉咙最深处向下一压，[npc.name]接着把头拉了回来，快速地宣告了这次口交的结束。",

							"把[npc.face]猛然贴近[npc2.namePos]的下体，迫使[npc2.cock+]深深推入[npc.her]的喉咙，"
									+ "然后完全收回，让[npc2.herHim]从[npc.her]口中滑出。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]将[npc2.namePos][npc2.cock+]从自己口中抽出，[npc.she]发出一阵[npc.a_moan+]，结束了这次口交侍奉。",

							"[npc.name]发出[npc.a_moan+]，缩回头，把[npc2.namePos][npc2.cock+]从嘴里完全滑了出来。"));
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
								"[npc2.name]发出一声[npc2.a_moan+]，暴露了[npc2.her]希望[npc.Name]继续吮吸自己[npc2.cock+]的欲望。",
	
								"[npc2.Name][npc2.moansVerb]，感受着[npc.name]从[npc2.her]腹股沟里拔出，努力抑制想用[npc.lips+]再次环住那[npc2.cock+]的欲望。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
