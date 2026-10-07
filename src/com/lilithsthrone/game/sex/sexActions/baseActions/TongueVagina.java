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
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.3.2
 * @author Innoxia
 */
public class TongueVagina {

	// -- Methods for multiple ongoing characters:
	
	static List<GameCharacter> getOngoingCharacters(GameCharacter characterReceivingCunnilingus) {
		return new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterReceivingCunnilingus, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
	}

	private static List<GameCharacter> getCharactersForParsing(GameCharacter characterReceivingCunnilingus) {
		List<GameCharacter> characters = Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
		for(GameCharacter c : getOngoingCharacters(characterReceivingCunnilingus)) {
			if(!characters.contains(c)) {
				characters.add(c);
			}
		}
		return characters;
	}
	
	private static String getOngoingNames(GameCharacter characterReceivingCunnilingus, GameCharacter... charactersToExclude) {
		List<String> names = new ArrayList<>();
		List<GameCharacter> exclusions = Arrays.asList(charactersToExclude);
		for(GameCharacter c : getOngoingCharacters(characterReceivingCunnilingus)) {
			if(!exclusions.contains(c)) {
				names.add(UtilText.parse(c, "[npc.name]"));
			}
		}
		return Util.stringsToStringList(names, false);
	}
	
	public static GameCharacter getPrimaryCunnilingusPerformer(GameCharacter characterReceivingCunnilingus) {
		if(Main.sex.getOngoingActionsMap(characterReceivingCunnilingus).get(SexAreaOrifice.VAGINA).isEmpty()) {
			return null;
		}
		return Main.sex.getOngoingActionsMap(characterReceivingCunnilingus).get(SexAreaOrifice.VAGINA).keySet().iterator().next();
	}
	
	// ---
	
	public static final SexAction HERM_FUN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
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
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
					&& Main.sex.isPenetrationTypeFree(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]不想忽略[npc2.namePos]其他的性器，给予了[npc2.her][npc2.pussy+]一个温柔的吻，"
									+"随后后缩，开始缓慢地吮吸舔吻着[npc2.her][npc2.cock+]的[npc2.cockHead]。",

							"[npc.Name]决定给予[npc2.namePos][npc2.cock]一些关注，缓慢地舔舐起[npc2.her][npc2.pussy+]的肉棒，"
									+"随后后缩，开始舔吻吮吸[npc2.her][npc2.cock+]的[npc2.cockHead]。",

							"[npc.Name]往后缩，给予了[npc2.namePos][npc2.pussy+]一个温柔的吻，"
									+"将[npc.her][npc.lips+]凑到[npc2.her][npc2.cock+]的[npc2.cockHead]，随后将[npc2.herHim]吞入[npc.her]嘴中。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]不想忽略[npc2.namePos]其他的性器，给予[npc2.her][npc2.pussy+]一个湿润的吻，"
									+"随后后缩，开始渴望地吮吸舔吻[npc2.her][npc2.cock+]的[npc2.cockHead]。",

							"[npc.Name]决定给予[npc2.namePos][npc2.cock]一些关注，绵长又湿润地舔舐起[npc2.her][npc2.pussy+]的肉棒，"
									+"随后后缩，开始渴望地舔吻吮吸着[npc2.her][npc2.cock+]的[npc2.cockHead]。",

							"[npc.Name]往后缩，给予[npc2.namePos][npc2.pussy+]一个湿润的吻，"
									+"将[npc.her][npc.lips+]凑到[npc2.her][npc2.cock+][npc2.cockHead]，随后渴望地将[npc2.herHim]吞入[npc.her]嘴中。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]不想忽略[npc2.namePos]其他的性器，给予[npc2.her][npc2.pussy+]一个粗暴的吻，"
									+"随后往后缩，开始强势地吮吸舔吻着[npc2.her][npc2.cock+]的[npc2.cockHead]。",

							"[npc.Name]决定给予[npc2.namePos][npc2.cock]一些关注，绵长又粗暴舔舐起[npc2.her][npc2.pussy+]的肉棒，"
									+"随后往后缩，开始强势地舔吻吮吸着[npc2.her][npc2.cock+]的[npc2.cockHead]",

							"[npc.Name]往后缩，粗暴地亲了一口[npc2.namePos][npc2.pussy+]，"
									+"将[npc.her][npc.lips+]凑到[npc2.her][npc2.cock+]的[npc2.cockHead]，随后强势地将[npc2.herHim]吞入口中。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]不想忽略[npc2.namePos]其他的性器，给予[npc2.her][npc2.pussy+]一个湿润的吻，"
									+"随后后缩，开始渴望地吮吸舔吻[npc2.her][npc2.cock+]的[npc2.cockHead]。",

							"[npc.Name]决定给予[npc2.namePos][npc2.cock]一些关注，绵长又湿润地舔舐起[npc2.her][npc2.pussy+]的肉棒，"
									+"随后后缩，开始渴望地舔吻吮吸着[npc2.her][npc2.cock+]的[npc2.cockHead]。",

							"[npc.Name]往后缩，给予[npc2.namePos][npc2.pussy+]一个湿润的吻，"
									+"将[npc.her][npc.lips+]凑到[npc2.her][npc2.cock+][npc2.cockHead]，随后渴望地将[npc2.herHim]吞入[npc.her]嘴中。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]不想忽略[npc2.namePos]其他的性器，给予了[npc2.her][npc2.pussy+]一个吻，"
									+ "随后往后缩，开始吮吸舔吻起[npc2.her][npc2.cock+]的[npc2.cockHead]。",

							"[npc.Name]决定给予[npc2.namePos][npc2.cock]一些关注，舔舐起[npc2.her][npc2.pussy+]的肉棒，"
									+"随后后缩，开始舔吻吮吸[npc2.her][npc2.cock+]的[npc2.cockHead]。",

							"[npc.Name]往后缩，亲了一口[npc2.namePos][npc2.pussy+]，"
									+"将[npc.her][npc.lips+]凑到[npc2.her][npc2.cock+]的[npc2.cockHead]，随后将[npc2.herHim]吞入[npc.her]嘴中。"));
					break;
				default:
					break;
			}
			
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一声柔和的[npc2.moan]，"
										+"温柔地把[npc2.cock]推入[npc.namePos]嘴里，随后[npc.name]决定将注意力重新转回[npc2.namePos][npc2.pussy+]。",
	
								"[npc2.name]温柔地在[npc.namePos]嘴里抽插着[npc2.her][npc2.cock]，发出轻柔的[npc2.moan]，"
										+"随后[npc.name]决定转回关注[npc2.namePos][npc2.pussy+]。",
	
								"[npc2.name]轻声[npc2.moaning]，温柔地将[npc2.her][npc2.hips]顶向[npc.namePos][npc.face]好一会儿，"
										+ "然后[npc.she]把口腔的注意力重新调整到自己[npc2.pussy+]上。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出了[npc2.a_moan+]，"
										+"渴望地将[npc2.cock]推入[npc.namePos]嘴中，随后[npc.name]决定将注意力重新转回[npc2.namePos][npc2.pussy+]。",
	
								"[npc2.name]饥渴地在[npc.namePos]嘴中抽插[npc2.her][npc2.cock]，发出一声[npc2.a_moan+]，"
										+"随后[npc.name]决定转回关注[npc2.namePos][npc2.pussy+]。",
	
								"[npc2.name][npc2.Moaning+]，饥渴地将[npc2.her][npc2.hips]顶向[npc.namePos][npc.face]好一会儿，"
										+ "然后[npc.she]把口腔的注意力重新调整到自己[npc2.pussy+]上。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出了[npc2.a_moan+]，"
										+"粗暴地把[npc2.cock]插入[npc.namePos]嘴中，随后[npc.name]决定将注意力重新转回[npc2.namePos][npc2.pussy+]。",
	
								"[npc2.name]粗暴地将[npc2.cock]插入[npc.namePos]口中，发出[npc2.a_moan+]，"
										+"随后[npc.name]决定转回关注[npc2.namePos][npc2.pussy+]。",
	
								"[npc2.name]粗暴地用[npc2.hips]顶着[npc.namePos][npc.face]好一会儿，[npc2.moaning+]着，"
										+ "然后[npc.she]把口腔的注意力重新调整到自己[npc2.pussy+]上。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出了[npc2.a_moan+]，"
										+"渴望地将[npc2.cock]推入[npc.namePos]嘴中，随后[npc.name]决定将注意力重新转回[npc2.namePos][npc2.pussy+]。",
	
								"[npc2.name]饥渴地在[npc.namePos]嘴中抽插[npc2.her][npc2.cock]，发出一声[npc2.a_moan+]，"
										+"随后[npc.name]决定转回关注[npc2.namePos][npc2.pussy+]。",
	
								"[npc2.name]饥渴地将[npc2.hips]顶向[npc.namePos]的[npc.face]好一会儿，[npc2.Moaning+]，"
										+ "然后[npc.she]把口腔的注意力重新调整到自己[npc2.pussy+]上。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]发出了[npc2.a_moan+]，"
										+"把[npc2.cock]推入[npc.namePos]嘴中，随后[npc.name]决定将注意力重新转回[npc2.namePos][npc2.pussy+]。",
	
								"[npc2.name]决定转回关注[npc2.her][npc2.pussy+]，在[npc.namePos]嘴中抽插[npc2.her]的[npc2.cock]，发出[npc2.a_moan+]。",
	
								"[npc2.name]将[npc2.her][npc2.hips]顶向[npc.namePos][npc.face]好一会儿，[npc2.Moaning+]，"
										+ "然后[npc.she]把口腔的注意力重新调整到自己[npc2.pussy+]上。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]不舒服地[npc2.Sobbing]和蠕动着，拼命地尝试远离[npc.Name]，"
										+"[npc.she]使注意力重回[npc2.her][npc2.pussy+]，同时祈求着能自己一个人呆着。",
	
								"[npc2.name]因为[npc.name]将注意力重新放在[npc2.her][npc2.pussy+]而不舒服地蠕动，发出[npc2.a_sob+]，尝试推开[npc.Name]。",
	
								"[npc2.name]因[npc.Name]将注意重新放回[npc2.her][npc2.pussy+]而发出[npc2.a_sob+]，[npc2.face]上的泪如流水倾泻。"));
						break;
					default:
						break;
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"吮吸了一会儿[npc2.namePos][npc2.cock+]之后，[npc.name]把注意力重新调整到自己[npc2.pussy+]上。",
						"享受了一会儿[npc2.namePos][npc2.cock+]的滋味后，[npc.name]把注意力集中在[npc2.her][npc2.pussy+]上。"));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction TWINTAIL_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
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
				return "双[npc.hand]分别抓住[npc2.namePos]的两条马尾，把[npc2.her]的[npc2.face]摁在你[npc.pussy+]上。";
			} else {
				return "双[npc.hand]分别抓住[npc2.namePos]的两条麻花辫，把[npc2.her]的[npc2.face]摁在你[npc.pussy+]上。";
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
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.hand]握住[npc2.namePos]的"+style+"，随后温柔地将[npc2.her]向前拉，迫使[npc2.herHim]将[npc2.her]那[npc2.tongue+]深深插入自己的[npc.pussy]当中。",
							"[npc.Name]俯下身轻柔但坚定地抓住[npc2.namePos]的每一个"+style+"，缓缓将[npc2.herHim]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]给[npc.herHim]舔阴。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos]的"+style+"，"
									+ "然后温柔地将[npc2.herHim]向前拉，让[npc.name]把[npc.labia+]按到[npc2.her]的[npc2.mouth]上。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]粗鲁地用[npc.hand]抓住[npc2.namePos]的"+style+"，粗暴地将[npc2.her]的头拽过来，强迫[npc2.herHim]将[npc2.tongue+]伸入[npc.her][npc.pussy]深处。",
							"[npc.Name]双手向下抓住[npc2.namePos]的"+style+"，无情地将[npc2.her]的头部拽向自己的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]给[npc.herHim]舔阴。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰抓住[npc2.namePos]的"+style+"，"
									+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.name]把[npc.labia+]按到[npc2.her]的[npc2.mouth]上。"));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]用[npc.hand]握住[npc2.namePos]的"+style+"，随后狠狠地将[npc2.her]向前拉，迫使[npc2.herHim]将[npc2.her]那[npc2.tongue+]深深插入自己的[npc.pussy]当中。",
							"[npc.Name]俯下身紧紧地抓住[npc2.namePos]的每一个"+style+"，坚定地将[npc2.herHim]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]给[npc.herHim]舔阴。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos]的"+style+"，"
									+ "然后平稳地将[npc2.herHim]向前拉，让[npc.name]把[npc.labia+]按到[npc2.her]的[npc2.mouth]上。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]温柔地对[npc.pussy+]又舔又亲，[npc.namePos]腹股沟出传来了模糊的嗯嗯啊啊声，"
										+ "[npc2.her]最终推开并喘息着，[npc.Name]立刻松手不再抓着[npc2.her]的[npc2.hair(true)]。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，用[npc2.her]的[npc2.tongue]舔舐着[npc.namePos][npc.pussy+]，"
										+ "顺从地舔弄[npc.herHim]的小穴，直到[npc.she]最终松开抓住[npc2.her][npc2.hair(true)]的手。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]垂下眸子，忍受了一小会[npc.namePos]的大胆举动，"
										+ "然后把[npc2.her]的头扭过来，厉声提醒[npc.herHim]谁才是主导者。",
								"[npc2.namePos]威胁似的低吼着，那震动的喉咙却同时带来了额外的快感，"
										+ "尽管如此，[npc.name]还是被吓得立即松开[npc2.hair(true)]，让[npc2.herHim]从自己[npc.pussy+]里拔出去。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"蜷缩着的[npc2.her]合上[npc2.eyes]，[npc2.name]徒劳地抵抗着，拍打#IF(npc.isPlayer())你的#ELSE[npc.her]折磨者的#ENDIF股间，"
										+ "[npc.her]最终能够哭出来，大口喘着气，[npc.Name]立刻松手不再抓着[npc2.her]的[npc2.hair(true)]。",
								"[npc2.namePos]压抑的哭泣和抽噎只会给#IF(npc.isPlayer())你#ELSE折磨[npc.her]的人#ENDIF带来额外的快感，"
										+ "但是，在花了几秒钟的时间拳打脚踢[npc.namePos]股间之后，"
										+ "在[npc2.her][npc2.hair(true)]被放开时，[npc2.she]终于取得了一个小小的胜利。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]在[npc.namePos]腹股沟处沉闷地[npc2.moans+]，饥渴地舔舐亲吻[npc.her][npc.pussy+]，"
										+ "[npc2.her]最终推开并喘息着，[npc.Name]立刻松手不再抓着[npc2.her]的[npc2.hair(true)]。",
								"[npc2.name]开心地哼哼，愉悦着[npc2.moaning]，用[npc2.her]的[npc2.tongue]舔舐[npc.namePos][npc.pussy+]，"
										+ "顺从地舔弄[npc.herHim]的小穴，直到[npc.she]最终松开抓住[npc2.her][npc2.hair(true)]的手。"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "揪耳朵";
		}
		@Override
		public String getActionDescription() {
			return "双[npc.hand]分别抓住[npc2.namePos]的两只[npc2.ears+]，把[npc2.her]的[npc2.face]摁在你[npc.pussy+]上。";
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
							"[npc.name]抓住[npc2.namePos][npc2.ear+]，温柔地将[npc2.her]向前拉，迫使[npc2.herHim]将[npc2.her]那[npc2.tongue+]深深插入自己的[npc.pussy]当中。",
							"[npc.Name]俯下身轻柔但坚定地抓住[npc2.namePos][npc2.ears+]，缓缓将[npc2.her]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]给[npc.herHim]舔阴。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.ears+]，"
									+ "然后温柔地将[npc2.herHim]向前拉，让[npc.name]把[npc.labia+]按到[npc2.her]的[npc2.mouth]上。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗暴地抓着[npc2.namePos][npc2.ears+]，粗暴地向前猛拉[npc2.her]的脑袋，强迫将[npc2.her][npc2.tongue+]深入[npc.her][npc.pussy]。",
							"[npc.Name]双手向下抓住[npc2.namePos][npc2.ears+]，无情地将[npc2.her]的头部拽向自己的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]给[npc.herHim]舔阴。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰抓住[npc2.namePos][npc2.ears+]，"
									+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.name]把[npc.labia+]按到[npc2.her]的[npc2.mouth]上。"));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"在抓住[npc2.namePos]两只[npc2.ears+]后，[npc.name]狠狠地拉着[npc2.her]的头，迫使[npc2.herHim]将[npc2.her]那[npc2.tongue+]深深插入[npc.her]的[npc.pussy]当中。",
							"[npc.Name]俯下身紧紧地抓住[npc2.namePos][npc2.ears+]，坚定地将[npc2.herHim]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]给[npc.herHim]舔阴。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.ears+]，"
									+ "然后平稳地将[npc2.herHim]向前拉，让[npc.name]把[npc.labia+]按到[npc2.her]的[npc2.mouth]上。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]温柔地对[npc.pussy+]又舔又亲，[npc.namePos]腹股沟出传来了模糊的嗯嗯啊啊声，"
										+ "[npc2.her]最终推开并喘息着，[npc.Name]立刻松手不再抓着[npc2.her]的[npc2.ears]。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，用[npc2.her]的[npc2.tongue]舔舐着[npc.namePos][npc.pussy+]，"
										+ "顺从地舔弄[npc.herHim]的小穴，直到[npc.she]最终松开捏住[npc2.her][npc2.ears]的手。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]垂下眸子，忍受了一小会[npc.namePos]的大胆举动，"
										+ "然后把[npc2.her]的头扭过来，厉声提醒[npc.herHim]谁才是主导者。",
								"[npc2.namePos]威胁似的低吼着，那震动的喉咙却同时带来了额外的快感，"
										+ "尽管如此，[npc.name]还是被吓得立即松开[npc2.ears]，让[npc2.herHim]从自己[npc.pussy+]里拔出去。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"蜷缩着的[npc2.her]合上[npc2.eyes]，[npc2.name]徒劳地抵抗着，拍打#IF(npc.isPlayer())你的#ELSE[npc.her]折磨者的#ENDIF股间，"
										+ "[npc.her]最终能够哭出来，大口喘着气，[npc.Name]立刻松手不再抓着[npc2.her]的[npc2.ears]。",
								"[npc2.namePos]压抑的哭泣和抽噎只会给#IF(npc.isPlayer())你#ELSE折磨[npc.her]的人#ENDIF带来额外的快感，"
										+ "但是，在花了几秒钟的时间拳打脚踢[npc.namePos]股间之后，"
										+ "在[npc2.her][npc2.ears]被放开时，[npc2.she]终于取得了一个小小的胜利。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]在[npc.namePos]腹股沟处沉闷地[npc2.moans+]，饥渴地舔舐亲吻[npc.her][npc.pussy+]，"
										+ "[npc2.her]最终推开并喘息着，[npc.Name]立刻松手不再抓着[npc2.her]的[npc2.ears]。",
								"[npc2.name]开心地哼哼，愉悦着[npc2.moaning]，用[npc2.her]的[npc2.tongue]舔舐[npc.namePos][npc.pussy+]，"
										+ "顺从地舔弄[npc.herHim]的小穴，直到[npc.she]最终松开捏住[npc2.her][npc2.ears]的手。"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "抓住[npc2.horns]";
		}
		@Override
		public String getActionDescription() {
			return "双[npc.hand]分别抓住[npc2.namePos]的两根[npc2.horns+]，把[npc2.her]的[npc2.face]摁在你[npc.pussy+]上。";
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
							"在抓住[npc2.namePos][npc2.ears+]后，[npc.name]温柔地拉着[npc2.her]的头，迫使[npc2.herHim]将[npc2.her]那[npc2.tongue+]深深插入[npc.her]的[npc.pussy]当中。",
							"[npc.Name]俯下身轻柔但坚定地抓住[npc2.namePos][npc2.horns+]，缓缓将[npc2.her]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]给[npc.herHim]舔阴。",
							"[npc.name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.horns+]，"
									+ "然后温柔地将[npc2.herHim]向前拉，让[npc.name]把[npc.labia+]按到[npc2.her]的[npc2.mouth]上。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗暴地抓着[npc2.namePos][npc2.horns+]，粗暴地向前猛拉[npc2.her]的脑袋，强迫将[npc2.her][npc2.tongue+]深入[npc.her][npc.pussy]。",
							"[npc.Name]双手向下抓住[npc2.namePos][npc2.horns+]，无情地将[npc2.her]的头部拽向自己的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]给[npc.herHim]舔阴。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰抓住[npc2.namePos][npc2.horns+]，"
									+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.name]把[npc.labia+]按到[npc2.her]的[npc2.mouth]上。"));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"在抓住[npc2.namePos][npc2.ears+]后，[npc.name]狠狠地拉着[npc2.her]的头，迫使[npc2.herHim]将[npc2.her]那[npc2.tongue+]深深插入[npc.her]的[npc.pussy]当中。",
							"[npc.Name]俯下身紧紧地抓住[npc2.namePos][npc2.horns+]，坚定地将[npc2.herHim]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]给[npc.herHim]舔阴。",
							"[npc.name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.horns+]，"
									+ "然后平稳地将[npc2.herHim]向前拉，让[npc.name]把[npc.labia+]按到[npc2.her]的[npc2.mouth]上。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]温柔地对[npc.pussy+]又舔又亲，[npc.namePos]腹股沟出传来了模糊的嗯嗯啊啊声，"
										+ "[npc2.her]最终推开并喘息着，[npc.Name]立刻松手不再抓着[npc2.her]的[npc2.horns]。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，用[npc2.her]的[npc2.tongue]舔舐着[npc.namePos][npc.pussy+]，"
										+ "顺从地舔弄[npc.herHim]的小穴，直到[npc.she]最终松开抓住[npc2.her][npc2.horns]的手。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]垂下眸子，忍受了一小会[npc.namePos]的大胆举动，"
										+ "然后把[npc2.her]的头扭过来，厉声提醒[npc.herHim]谁才是主导者。",
								"[npc2.namePos]威胁似的低吼着，那震动的喉咙却同时带来了额外的快感，"
										+ "尽管如此[npc.name]还是被吓得立即松开[npc2.horns]，让[npc2.herHim]从自己[npc.pussy+]里拔出去。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"蜷缩着的[npc2.her]合上[npc2.eyes]，[npc2.name]徒劳地抵抗着，拍打#IF(npc.isPlayer())你的#ELSE[npc.her]折磨者的#ENDIF股间，"
										+ "[npc.her]最终能够哭出来，大口喘着气，[npc.Name]立刻松手不再抓着[npc2.her]的[npc2.horns]。",
								"[npc2.namePos]压抑的哭泣和抽噎只会给#IF(npc.isPlayer())你#ELSE折磨[npc.her]的人#ENDIF带来额外的快感，"
										+ "但是，在花了几秒钟的时间拳打脚踢[npc.namePos]股间之后，"
										+ "在[npc2.her][npc2.horns]被放开时，[npc2.she]终于取得了一个小小的胜利。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]在[npc.namePos]腹股沟处沉闷地[npc2.moans+]，饥渴地舔舐亲吻[npc.her][npc.pussy+]，"
										+ "[npc2.her]最终推开并喘息着，[npc.Name]立刻松手不再抓着[npc2.her]的[npc2.horns]。",
								"[npc2.name]开心地哼哼，愉悦着[npc2.moaning]，用[npc2.her]的[npc2.tongue]舔舐[npc.namePos][npc.pussy+]，"
										+ "顺从地舔弄[npc.herHim]的小穴，直到[npc.she]最终松开抓住[npc2.her][npc2.horns]的手。"));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "抓住[npc2.antennae]";
		}
		@Override
		public String getActionDescription() {
			return "双[npc.hand]分别抓住[npc2.namePos]的两根[npc2.antennae+]，把[npc2.her]的[npc2.face]摁在你[npc.pussy+]上。";
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
							"在抓住[npc2.namePos]的[npc2.antennae+]后，[npc.name]温柔地将[npc2.her]向前拉，迫使[npc2.herHim]将[npc2.her]那[npc2.tongue+]深深插入[npc.her]的[npc.pussy]当中。",
							"[npc.Name]俯下身轻柔但坚定地抓住[npc2.namePos][npc2.antennae+]，缓缓将[npc2.her]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]给[npc.herHim]舔阴。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.antennae+]，"
									+ "然后温柔地将[npc2.herHim]向前拉，让[npc.name]把[npc.labia+]按到[npc2.her]的[npc2.mouth]上。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗暴地抓着[npc2.namePos][npc2.antennae+]，粗暴地向前猛拉[npc2.her]的脑袋，强迫将[npc2.her][npc2.tongue+]深入[npc.her][npc.pussy]。",
							"[npc.Name]双手向下抓住[npc2.namePos][npc2.antennae+]，无情地将[npc2.her]的头部拽向自己的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]给[npc.herHim]舔阴。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰抓住[npc2.namePos][npc2.antennae+]，"
									+ "然后猛烈地将[npc2.herHim]向前拉扯，让[npc.name]把[npc.labia+]按到[npc2.her]的[npc2.mouth]上。"));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"在抓住[npc2.namePos]的[npc2.antennae+]后，[npc.name]温柔地将[npc2.her]的[npc.hand]向前拽，迫使[npc2.herHim]将[npc2.her]那[npc2.tongue+]深深插入[npc.her]的[npc.pussy]当中。",
							"[npc.Name]俯下身紧紧地抓住[npc2.namePos][npc2.antennae+]，坚定地将[npc2.herHim]拉近[npc.her]的小腹，"
									+ "[npc.she]发出[npc.a_moan+]，强迫[npc2.herHim]给[npc.herHim]舔阴。",
							"[npc.Name]发出[npc.a_moan+]，弯下腰握住[npc2.namePos][npc2.antennae+]，"
									+ "然后平稳地将[npc2.herHim]向前拉，让[npc.name]把[npc.labia+]按到[npc2.her]的[npc2.mouth]上。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]温柔地对[npc.pussy+]又舔又亲，[npc.namePos]腹股沟出传来了模糊的嗯嗯啊啊声，"
										+ "[npc2.her]最终推开并喘息着，[npc.Name]立刻松手不再抓着[npc2.her]的[npc2.antennae]。",
								"[npc2.name]轻柔地闷哼着，发出[npc2.moaning]，用[npc2.her]的[npc2.tongue]舔舐着[npc.namePos][npc.pussy+]，"
										+ "顺从地舔弄[npc.herHim]的小穴，直到[npc.she]最终松开抓住[npc2.her]触须的手。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]垂下眸子，忍受了一小会[npc.namePos]的大胆举动，"
										+ "然后把[npc2.her]的头扭过来，厉声提醒[npc.herHim]谁才是主导者。",
								"[npc2.namePos]威胁似的低吼着，那震动的喉咙却同时带来了额外的快感，"
										+ "尽管如此，[npc.name]还是被吓得立即松开[npc2.antennae]，让[npc2.herHim]从自己[npc.pussy+]里拔出去。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"蜷缩着的[npc2.her]合上[npc2.eyes]，[npc2.name]徒劳地抵抗着，拍打#IF(npc.isPlayer())你的#ELSE[npc.her]折磨者的#ENDIF股间，"
										+ "[npc.her]最终能够哭出来，大口喘着气，[npc.Name]立刻松手不再抓着[npc2.her]的[npc2.antennae]。",
								"[npc2.namePos]压抑的哭泣和抽噎只会给#IF(npc.isPlayer())你#ELSE折磨[npc.her]的人#ENDIF带来额外的快感，"
										+ "但是，在花了几秒钟的时间拳打脚踢[npc.namePos]股间之后，"
										+ "在[npc2.her][npc2.antennae]被放开时，[npc2.she]终于取得了一个小小的胜利。"));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]在[npc.namePos]腹股沟处沉闷地[npc2.moans+]，饥渴地舔舐亲吻[npc.her][npc.pussy+]，"
										+ "[npc2.her]最终推开并喘息着，[npc.Name]立刻松手不再抓着[npc2.her]的[npc2.antennae]。",
								"[npc2.name]开心地哼哼，愉悦着[npc2.moaning]，用[npc2.her]的[npc2.tongue]舔舐[npc.namePos][npc.pussy+]，"
										+ "顺从地舔弄[npc.herHim]的小穴，直到[npc.she]最终松开抓住[npc2.her]触须的手。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CUNNILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "开始舔阴";
		}
		@Override
		public String getActionDescription() {
			return "把你的[npc.tongue]伸进[npc2.namePos][npc2.pussy+]，开始舔阴。";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]将[npc.her][npc.lips+]压向[npc2.namePos][npc2.pussy]，并给予[npc2.her][npc2.labia+]一长串轻柔的吻，"
									+"随后缓慢但又坚定地将[npc.tongue+]伸进[npc2.her][npc2.pussy+]。",

							"[npc.Name]绵长而湿润地舔吮着[npc2.labia+]，随后温柔地将[npc.tongue+]向更深处推进，给予[npc2.namePos][npc2.pussy+]一长串轻柔的吻。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]饥渴地将[npc.her][npc.lips+]压向[npc2.namePos][npc2.pussy]，在[npc2.her][npc2.labia+]上留下一串热情的吻，"
									+"随后饥渴地将[npc.tongue+]伸入[npc2.her][npc2.pussy+]。",

							"[npc.Name]饥渴地舔吮着[npc2.labia+]，随后贪婪地将[npc.tongue+]推向更深处，给予[npc2.namePos][npc2.pussy+]一长串热情的吻。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗暴地在[npc2.namePos][npc2.pussy]摩擦[npc.her][npc.lips+]，给予[npc2.her][npc2.labia+]一长串有力的吻，"
									+"随后贪婪地将[npc.tongue+]伸入[npc2.her][npc2.pussy+]。",

							"[npc.Name]有力地亲吻着[npc2.labia+]，随后贪婪地将[npc.tongue+]推向更深处，给予[npc2.namePos][npc2.pussy+]一长串粗糙的舔舐。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]饥渴地将[npc.her][npc.lips+]压向[npc2.namePos][npc2.pussy]，在[npc2.her][npc2.labia+]上留下一串热情的吻，"
									+"随后饥渴地将[npc.tongue+]伸入[npc2.her][npc2.pussy+]。",

							"[npc.Name]饥渴地舔吮着[npc2.labia+]，随后贪婪地将[npc.tongue+]推向更深处，给予[npc2.namePos][npc2.pussy+]一长串热情的吻。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]将[npc.her][npc.lips+]压向[npc2.namePos][npc2.pussy]，并给予[npc2.her][npc2.labia+]一长串轻柔的吻，将[npc.her][npc.tongue+]滑入[npc2.her][npc2.pussy+]。",

							"[npc.Name]湿润地舔吮着[npc2.labia+]，随后将[npc.tongue+]向更深处推进，给予[npc2.namePos][npc2.pussy+]一长串轻柔的吻。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出轻柔的[npc2.moan]作为回应，温柔地将[npc2.hips]压向[npc.namePos]的[npc.face]，期待着对方继续做下去。",
	
								"[npc2.name]温柔地将[npc2.hips]抵在[npc.namePos][npc.face]上，回应起口交，大声地[npc2.moanVerb]，祈求[npc.herHim]继续做下去。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]作为回应，饥渴地将[npc2.her]的[npc2.hips]压向[npc.namePos]的[npc.face]以祈求[npc.herHim]继续做下去。",
	
								"[npc2.name]急切地将[npc2.hips]抵在[npc.namePos][npc.face]上，回应起口交，大声地[npc2.moanVerb]，祈求[npc.herHim]继续做下去。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]作为回应，粗暴地将[npc2.her][npc2.hips]推向[npc.namePos][npc.face]以命令[npc.herHim]继续做下去。",
	
								"[npc2.name]粗暴地将[npc2.hips]推向[npc.namePos]的[npc.face]，饥渴地回应起口交，大声地[npc2.moanVerb]，要求[npc.herHim]继续做下去。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]作为回应，饥渴地将[npc2.her]的[npc2.hips]压向[npc.namePos]的[npc.face]以祈求[npc.herHim]继续做下去。",
	
								"[npc2.name]急切地将[npc2.hips]抵在[npc.namePos][npc.face]上，回应起口交，大声地[npc2.moanVerb]，祈求[npc.herHim]继续做下去。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出[npc2.a_moan+]作为回应，将[npc2.her][npc2.hips]压向[npc.namePos][npc.face]以祈求[npc.herHim]继续做下去。",
	
								"[npc2.name]将[npc2.hips]抵在[npc.namePos][npc.face]上，回应起口交，大声地[npc2.moanVerb]，祈求[npc.herHim]继续做下去。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]拼命地尝试扭动远离[npc.namePos]的嘴巴接触，[npc2.she][npc2.sobbing]扭动，乞求放过自己。",
	
								"[npc2.namePos]嘴间爆发出一阵[npc2.A_sob+]，[npc2.she]一边反抗着[npc.Name]，一边乞求[npc.Name]将[npc.tongue]从[npc2.her]的小穴挪开。"));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};

	public static final SexAction CUNNILINGUS_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
		
		//--- Additional methods: ---

		private List<GameCharacter> getOngoingCharacters() {
			return TongueVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return TongueVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		private String getOngoingNames() {
			return TongueVagina.getOngoingNames(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			int size = getOngoingCharacters().size();
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				size--;
			}
			return size>0;
		}
		
		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();

			int size = getOngoingCharacters().size();
			
			map.put("one character performing cunnilingus", size==1);
			map.put("only ongoing vagina-actions are oral", size==Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).size());
			map.put("[npc.namePos] mouth to be exposed", Main.sex.getCharacterPerformingAction().isOrificeTypeExposed(SexAreaOrifice.MOUTH));
			map.put("[npc.namePos] mouth to be free", SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterPerformingAction()));
			
			return map;
		}
		
		//------
		
		@Override
		public String getActionTitle() {
			return "加入舔阴";
		}
		@Override
		public String getActionDescription() {
			return "协助"+getOngoingNames()+"舔弄[npc2.namePos][npc2.pussy+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"[npc.Name]想要加入"+getOngoingNames()+"，一起为[npc2.namePos][npc2.pussy+]口交，于是移动到"+(getOngoingCharacters().size()==1?"[npc3.herHim]":"他们")+"旁边"
								+"，前倾并在[npc2.her][npc2.labia+]一边上留下温柔地亲吻。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"[npc.Name]想要加入"+getOngoingNames()+"，一起为[npc2.namePos][npc2.pussy+]口交，于是爬到"+(getOngoingCharacters().size()==1?"[npc3.herHim]":"他们")+"旁边"
								+"，前倾并在[npc2.her][npc2.labia+]一边上留下粗糙的亲吻。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"[npc.Name]想要加入"+getOngoingNames()+"，一起为[npc2.namePos][npc2.pussy+]口交，于是移动到"+(getOngoingCharacters().size()==1?"[npc3.herHim]":"他们")+"旁边"
								+"，前倾并在[npc2.her][npc2.labia+]一边上留下快速地亲吻。"));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"[npc.Name]想要加入"+getOngoingNames()+"，一起为[npc2.namePos][npc2.pussy+]口交，于是饥渴地移动到"+(getOngoingCharacters().size()==1?"[npc3.herHim]":"他们")+"旁边"
								+"，前倾并在[npc2.her][npc2.labia+]一边上留下热情，温润的亲吻。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								"[npc2.Name]温柔地[npc2.moan]着，示意"+getOngoingNames()+" 先停一下，"
										+ "[npc2.she]允许[npc.Name]占主导地位亲吻舔舐[npc2.her][npc2.pussy+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								"[npc2.Name]温柔地[npc2.moan]着，粗暴地命令"+getOngoingNames()+"稍微移开一点，"
										+ "[npc2.she]命令[npc.Name]占主导地位亲吻舔舐[npc2.her][npc2.pussy+]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								"[npc2.Name][npc2.moan]着，示意"+getOngoingNames()+" 先停一下，"
										+ "[npc2.she]允许[npc.Name]占主导地位亲吻舔舐[npc2.her][npc2.pussy+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								" [npc2.Name]苦痛狂乱的[npc2.moan]"
										+"在[npc.Name]推开"+getOngoingNames()+"以便于[npc.her]舔吻自己[npc2.pussy+]时达到了顶点。"));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								"[npc2.Name][npc2.moan]着，示意"+getOngoingNames()+" 先停一下，"
										+ "[npc2.she]开心地鼓励[npc.Name]占主导地位亲吻舔舐[npc2.her][npc2.pussy+]。"));
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
							"作为回应，[npc2.name]急切地将[npc2.hips]向后压，"
									+ "[npc2.she]用[npc2.pussy+]压向[npc.namePos][npc.lips+]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]急切地用[npc2.hips]压向[npc.namePos]的[npc.face]，乞求[npc.Name]继续侍奉[npc2.her]的[npc2.pussy]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地用[npc2.labia+]磨蹭[npc.namePos]的[npc.face]，"
									+ "然后急切地请求[npc.herHim]将[npc.tongue+]尽可能深地伸入[npc2.her][npc2.pussy+]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.hips]从[npc.namePos]那讨厌的[npc.tongue]下缩回，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然亲吻舔弄[npc2.her][npc2.pussy+]。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地试图远离[npc.namePos]的[npc.tongue]，"
									+ "[npc.name]不断舔弄着[npc2.her][npc2.pussy+]，[npc2.she]奋力反抗着[npc.name]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]温柔地将[npc2.hips]向后压，"
									+ "[npc2.she]发出一声轻柔的[npc2.moan]，将[npc2.pussy+]压向[npc.namePos][npc.lips+]。",
		
							" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
									+ "[npc2.she]温柔地用[npc2.hips]压向[npc.namePos]的[npc.face]，乞求[npc.Name]继续侍奉[npc2.her]的[npc2.pussy]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，轻柔地用[npc2.labia+]磨蹭[npc.namePos]的[npc.face]，"
									+ "然后急切地请求[npc.herHim]将[npc.tongue+]尽可能深地伸入[npc2.her][npc2.pussy+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]粗暴地将[npc2.hips]往后推，"
									+ "[npc2.she]用[npc2.pussy+]蹭着[npc.namePos][npc.lips+]，发出一阵[npc2.a_moan+]。",
		
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]粗鲁地用[npc2.hips]碾向[npc.namePos]的[npc.face]，命令[npc.Name]继续侍奉[npc2.her]的[npc2.pussy]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，激烈地用[npc2.labia+]磨蹭[npc.namePos]的[npc.face]，"
									+ "然后积极地命令[npc.herHim]继续用[npc.tongue+]尽可能深入[npc2.her][npc2.pussy+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"作为回应，[npc2.name]将[npc2.hips]向后压，"
									+ "[npc2.she]用[npc2.pussy+]压向[npc.namePos][npc.lips+]，同时发出一阵[npc2.a_moan]。",
		
							"[npc2.namePos][npc2.lips+]间飘出一阵[npc2.A_moan+]，"
									+ "[npc2.she]用[npc2.hips]压向[npc.namePos]的[npc.face]，乞求[npc.Name]继续侍奉[npc2.her]的[npc2.pussy]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.labia+]磨蹭[npc.namePos]的[npc.face]，"
									+ "然后请求[npc.herHim]将[npc.tongue+]尽可能深地伸入[npc2.her][npc2.pussy+]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "舔阴(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "温柔地舔弄[npc2.namePos][npc2.pussy+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(target);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]头贴着"+TongueVagina.getOngoingNames(target, performer)+"，"
								+"对着[npc2.namePos][npc2.pussy+]露出的部分温柔地舔吻。",
						"[npc.name]头贴着"+TongueVagina.getOngoingNames(target, performer)+"发出[npc.moan]，"
								+"倾身嗅吻着[npc2.namePos][npc2.labia+]剩下的部分。",
						"[npc.name]头紧贴着"+TongueVagina.getOngoingNames(target, performer)
							+"，[npc.name]极尽所能地温顺亲舔着[npc2.namePos][npc2.labia+]所有露出来的地方。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"用[npc.tongue+]尽可能温柔地深入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]将[npc.her][npc.lips+]压向[npc2.her][npc2.labia+]，并发出了一声低沉的[npc.moan]。",
						"将[npc.tongue+]从[npc2.namePos][npc2.pussy+]中收回，[npc.name]开始温柔地舔吻着[npc2.namePos][npc2.labia+]，"
								+ "随后[npc.her][npc.tongue]再次缓慢地滑入[npc2.her][npc2.pussy+]。",
						"[npc.name]将[npc.tongue+]从[npc2.namePos][npc2.pussy+]中抽出，开始缓慢地亲吻摩擦[npc2.namePos][npc2.labia+]，"
								+ "然后身体前倾，温柔地将[npc.tongue]深深插入[npc2.her][npc2.pussy+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "舔阴";
		}
		@Override
		public String getActionDescription() {
			return "继续在[npc2.namePos][npc2.pussy+]里抽送你的[npc.tongue]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(target);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]头贴着"+TongueVagina.getOngoingNames(target, performer)+"，"
								+"对着[npc2.namePos][npc2.pussy+]剩下露出的部分饥渴地[npc.Name]舔吻。",
						"[npc.Name][npc.a_moan+]，亢奋地头靠头贴着"+TongueVagina.getOngoingNames(target, performer)+"，"
								+"倾身饥渴地嗅吻着[npc2.namePos][npc2.labia+]剩下的部分。",
						"[npc.name]头紧贴着"+TongueVagina.getOngoingNames(target, performer)
							+"，[npc.name]极尽所能地饥渴亲舔着[npc2.namePos][npc2.labia+]所有露出来的地方。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"用[npc.tongue+]尽可能急切地深入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]将[npc.her][npc.lips+]压向[npc2.her][npc2.labia+]，并发出了一声低沉的[npc.moan]。",
						"将[npc.tongue+]从[npc2.namePos][npc2.pussy+]中收回，[npc.name]开始饥渴地舔吻着[npc2.namePos][npc2.labia+]，"
								+ "随后[npc.her][npc.tongue]再次贪婪地滑入[npc2.her][npc2.pussy+]。",
						"[npc.name]将[npc.tongue+]从[npc2.namePos][npc2.pussy+]中抽出，开始开心地亲吻摩擦[npc2.namePos][npc2.labia+]，"
								+ "然后身体前倾，热情地将[npc.tongue]深深插入[npc2.her][npc2.pussy+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "舔阴(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地将你的舌头伸入[npc2.namePos][npc2.pussy+]。";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(target);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]头贴着"+TongueVagina.getOngoingNames(target, performer)+"，"
								+"对着[npc2.namePos][npc2.pussy+]露出的部分粗暴地舔吻。",
						"[npc.Name][npc.a_moan+]，激烈地头靠头贴着"+TongueVagina.getOngoingNames(target, performer)+"，"
								+"倾身粗暴地嗅吻着[npc2.namePos][npc2.labia+]剩下的部分。",
						"[npc.name]头紧贴着"+TongueVagina.getOngoingNames(target, performer)
							+"，[npc.name]极尽所能地粗暴亲舔着[npc2.namePos][npc2.labia+]所有露出来的地方。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"粗暴地用[npc.tongue+]尽可能深入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]将[npc.her][npc.lips+]蹭向[npc2.her][npc2.labia+]，并发出了一声低沉的[npc.moan]。",
						"将[npc.tongue+]从[npc2.namePos][npc2.pussy+]中收回，[npc.name]开始粗暴地舔吻着[npc2.namePos][npc2.labia+]，"
								+ "随后[npc.tongue]再次粗暴地滑入[npc2.her][npc2.pussy+]中。",
						"[npc.name]将[npc.tongue+]从[npc2.namePos][npc2.pussy+]中抽出，开始激烈地亲吻摩擦[npc2.namePos][npc2.labia+]，"
								+ "然后身体前倾，粗暴地将[npc.tongue]深深插入[npc2.her][npc2.pussy+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CUNNILINGUS_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "抵抗舔阴";
		}
		@Override
		public String getActionDescription() {
			return "努力把你的[npc.tongue]从[npc2.namePos][npc2.pussy+]里拔出来。";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]试图把[npc.face]移开，但[npc2.name]继续将[npc2.pussy+]温柔地压在[npc.namePos][npc.lips+]上，"
									+ "[npc2.name]牢牢地将[npc.herHim]固定在原位，紧紧贴着[npc.herHim]。",

							"伴随着一声[npc.a_sob+]，[npc.Name]试着推开[npc2.name]，但[npc2.name]迅速地将[npc.her]的[npc.face]拉回了自己[npc2.pussy+]，"
									+ "完全无视了[npc.her]的挣扎，温柔地磨蹭着[npc.herHim]。",

							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把嘴从[npc2.namePos][npc2.pussy+]边挪开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边将[npc2.pussy+]压向[npc.her][npc.lips+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]试图把[npc.face]移开，但[npc2.name]继续将[npc2.pussy+]粗暴地压在[npc.namePos][npc.lips+]上，"
									+ "[npc2.name]牢牢地将[npc.herHim]固定在原位，粗暴地贴着[npc.herHim]。",

							"伴随着一声[npc.a_sob+]，[npc.Name]试着推开[npc2.name]，但[npc2.name]猛烈地将[npc.her]的[npc.face]拉回了自己[npc2.pussy+]，"
									+ "完全无视了[npc.her]的挣扎，粗暴地磨蹭着[npc.herHim]。",

							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把嘴从[npc2.namePos][npc2.pussy+]边挪开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边粗暴地将[npc2.pussy+]压向[npc.her][npc.lips+]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]试图把[npc.face]移开，但[npc2.name]继续将[npc2.pussy+]急切地压在[npc.namePos][npc.lips+]上，"
									+ "[npc2.name]牢牢地将[npc.herHim]固定在原位，紧紧贴着[npc.herHim]。",

							"伴随着一声[npc.a_sob+]，[npc.Name]试着推开[npc2.name]，但[npc2.name]迅速地将[npc.her]的[npc.face]拉回了自己[npc2.pussy+]，"
									+ "完全无视了[npc.her]的挣扎，急切地磨蹭着[npc.herHim]。",

							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把嘴从[npc2.namePos][npc2.pussy+]边挪开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边急切地将[npc2.pussy+]压向[npc.her][npc.lips+]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "舔阴";
		}
		@Override
		public String getActionDescription() {
			return "继续在[npc2.namePos][npc2.pussy+]里抽送你的[npc.tongue]。";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(target);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]头贴着"+TongueVagina.getOngoingNames(target, performer)+"，"
								+"对着[npc2.namePos][npc2.pussy+]露出的部分舔吻。",
						"[npc.Name][npc.a_moan+]，头靠头贴着"+TongueVagina.getOngoingNames(target, performer)+"，"
								+"倾身嗅吻着[npc2.namePos][npc2.labia+]剩下的部分。",
						"[npc.name]头紧贴着"+TongueVagina.getOngoingNames(target, performer)
							+"，[npc.name]极尽所能地亲舔着[npc2.namePos][npc2.labia+]所有露出来的地方。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"用[npc.tongue+]尽可能深入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]将[npc.her][npc.lips+]压向[npc2.her][npc2.labia+]，并发出了一声低沉的[npc.moan]。",
						"将[npc.tongue+]从[npc2.namePos][npc2.pussy+]中收回，[npc.name]开始舔吻着[npc2.namePos][npc2.labia+]，"
								+ "随后[npc.her][npc.tongue]再次滑入[npc2.her][npc2.pussy+]。",
						"[npc.name]将[npc.tongue+]从[npc2.namePos][npc2.pussy+]中抽出，开始亲吻摩擦[npc2.namePos][npc2.labia+]，"
								+ "然后身体前倾，将[npc.tongue]深深插入[npc2.her][npc2.pussy+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "舔阴(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "饥渴地在[npc2.namePos][npc2.pussy+]里抽送你的[npc.tongue]。";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(target);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]头贴着"+TongueVagina.getOngoingNames(target, performer)+"，"
								+"对着[npc2.namePos][npc2.pussy+]剩下露出的部分饥渴地[npc.Name]舔吻。",
						"[npc.Name][npc.a_moan+]，亢奋地头靠头贴着"+TongueVagina.getOngoingNames(target, performer)+"，"
								+"倾身饥渴地嗅吻着[npc2.namePos][npc2.labia+]剩下的部分。",
						"[npc.name]头紧贴着"+TongueVagina.getOngoingNames(target, performer)
							+"，[npc.name]极尽所能地饥渴亲舔着[npc2.namePos][npc2.labia+]所有露出来的地方。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"用[npc.tongue+]尽可能急切地深入[npc2.namePos][npc2.pussy+]，"
								+ "[npc.name]将[npc.her][npc.lips+]压向[npc2.her][npc2.labia+]，并发出了一声低沉的[npc.moan]。",
						"将[npc.tongue+]从[npc2.namePos][npc2.pussy+]中收回，[npc.name]开始饥渴地舔吻着[npc2.namePos][npc2.labia+]，"
								+ "随后[npc.her][npc.tongue]再次贪婪地滑入[npc2.her][npc2.pussy+]。",
						"[npc.name]将[npc.tongue+]从[npc2.namePos][npc2.pussy+]中抽出，开始开心地亲吻摩擦[npc2.namePos][npc2.labia+]，"
								+ "然后身体前倾，热情地将[npc.tongue]深深插入[npc2.her][npc2.pussy+]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CUNNILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止舔阴";
		}
		@Override
		public String getActionDescription() {
			return "把你的[npc.tongue]从[npc2.namePos][npc2.pussy+]里缩回来，停止舔阴。";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"以一个粗暴的舔舐作为结束，随后[npc.name]把[npc.her]的[npc.face]从[npc2.namePos][npc2.pussy+]旁移开了。",
	
							"给了[npc2.namePos][npc2.labia+]一个粗暴的亲吻作为结束，[npc.name]将[npc.her]的[npc.face]从[npc2.her][npc2.pussy+]旁移开了。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"以一个舔舐作为结束，随后[npc.name]把[npc.her]的[npc.face]从[npc2.namePos][npc2.pussy+]旁移开了。",
	
							"给了[npc2.namePos][npc2.labia+]一个湿润的亲吻作为结束，[npc.name]将[npc.her]的[npc.face]从[npc2.her][npc2.pussy+]旁移开了。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]继续挣扎着，[npc2.sobbing]并不舒服地扭动，[npc2.she]意识到[npc.Name]还没有完全和[npc2.herHim]结束。",
		
								"[npc2.name]意识到[npc.she]还没有完全放过自己，继续挣扎[npc2.sobbing]，"
										+ "[npc2.she]恳求[npc.name]放过自己，泪水如小溪般从[npc2.her]的[npc2.face]上流下。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]将[npc.tongue+]从[npc2.namePos][npc2.pussy+]挪开，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
								
								"[npc.Name]不再舔弄[npc2.namePos][npc2.pussy+]，[npc2.namePos][npc2.lips+]间飘出一阵[npc2.A_moan+]，暴露了[npc2.she]渴望得到[npc.namePos]的更多关注。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction RECEIVING_CUNNILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "接受舔阴";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]开始舔[npc.namePos][npc.pussy+]。";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.namePos]把[npc.labia+]温柔地压在[npc2.namePos][npc2.face]上，"
									+ "发出一阵柔软的[npc.moan]，开始缓慢地将其[npc.pussy+]在[npc2.her][npc2.lips+]上摩擦。",

							"[npc.name]不断移动[npc.hips]，以便[npc2.namePos][npc2.face]压着[npc.her][npc.labia+]，"
									+ "[npc.Name]发出一阵柔软的[npc.moan]，开始温柔地将其[npc.pussy+]压向[npc2.her][npc2.lips+]。"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]把[npc.labia+]饥渴地压在[npc2.namePos][npc2.face]上，"
									+ "发出一阵[npc.moan]，开始拼命地将其[npc.pussy+]在[npc2.her][npc2.lips+]上摩擦。",

							"[npc.name]不断移动[npc.hips]，以便[npc2.namePos][npc2.face]压着[npc.her][npc.labia+]，"
									+ "[npc.Name]发出一阵[npc.moan]，开始饥渴地将其[npc.pussy+]压向[npc2.her][npc2.lips+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"粗暴地用[npc.her][npc.labia+]撞击[npc2.namePos][npc2.face]，"
									+ "[npc.Name]发出一阵[npc.moan]，开始激烈地将其[npc.pussy+]在[npc2.her][npc2.lips+]上摩擦。",

							"[npc.name]不断移动[npc.hips]，以便[npc2.namePos][npc2.face]压着[npc.her][npc.labia+]，"
									+ "[npc.Name]发出一阵[npc.moan]，开始粗暴地将其[npc.pussy+]在[npc2.her][npc2.lips+]上摩擦。"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]把[npc.labia+]饥渴地压在[npc2.namePos][npc2.face]上，"
									+ "发出一阵[npc.moan]，开始拼命地将其[npc.pussy+]在[npc2.her][npc2.lips+]上摩擦。",

							"[npc.name]不断移动[npc.hips]，以便[npc2.namePos][npc2.face]压着[npc.her][npc.labia+]，"
									+ "[npc.Name]发出一阵[npc.moan]，开始饥渴地将其[npc.pussy+]压向[npc2.her][npc2.lips+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"将[npc.her][npc.labia+]压在[npc2.namePos][npc2.face]上，"
									+ "[npc.Name]发出一阵[npc.moan]，开始将其[npc.pussy+]在[npc2.her][npc2.lips+]上摩擦。",

							"[npc.name]不断移动[npc.hips]，以便[npc2.namePos][npc2.face]压着[npc.her][npc.labia+]，"
									+ "[npc.Name]发出一阵[npc.moan]，开始将其[npc.pussy+]压向[npc2.her][npc2.lips+]。"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]缓慢地将其[npc2.tongue+]滑过[npc.namePos][npc.pussy+]，发出一阵[npc2.moan]，开始温柔地舔舐亲吻[npc.her][npc.labia+]。",
	
								"温柔地将[npc2.tongue]滑出，在[npc.labia+]上留下一串悠长缓慢的舔舐，"
										+ "[npc2.name]发出一阵沉闷的[npc2.moan]，开始在[npc.namePos][npc.pussy+]留下一连串温柔的吻。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]贪婪地将其[npc2.tongue+]滑入[npc.namePos][npc.pussy+]，发出一阵沉闷的[npc2.moan]，开始开心地舔舐亲吻[npc.her][npc.labia+]。",
	
								"贪婪地将[npc2.tongue]滑出，在[npc.labia+]上留下一串悠长缓慢的舔舐，"
										+ "[npc2.name]发出一阵沉闷的[npc2.moan]，开始在[npc.namePos][npc.pussy+]留下一连串激情的吻。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]激烈地将[npc2.tongue+]深深插入[npc.namePos][npc.pussy+]，"
										+ "[npc2.she]发出一声含糊不清的[npc2.moan]，开始粗暴地亲舔[npc.her][npc.labia+]。",
	
								"贪婪地将[npc2.tongue]滑出，在[npc.labia+]上留下一串粗糙温润的舔舐，"
										+ "[npc2.name]发出一阵沉闷的[npc2.moan]，开始在[npc.namePos][npc.pussy+]留下一连串有力的吻。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]贪婪地将其[npc2.tongue+]滑入[npc.namePos][npc.pussy+]，发出一阵沉闷的[npc2.moan]，开始开心地舔舐亲吻[npc.her][npc.labia+]。",
	
								"贪婪地将[npc2.tongue]滑出，在[npc.labia+]上留下一串悠长缓慢的舔舐，"
										+ "[npc2.name]发出一阵沉闷的[npc2.moan]，开始在[npc.namePos][npc.pussy+]留下一连串激情的吻。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]将其[npc2.tongue+]滑过[npc.namePos][npc.pussy+]，发出一阵[npc2.moan]，开始舔舐亲吻[npc.her][npc.labia+]。",
	
								"将[npc2.tongue]滑出，在[npc.labia+]上留下一串悠长湿润的舔舐，"
										+ "[npc2.name]发出一阵沉闷的[npc2.moan]，开始在[npc.namePos][npc.pussy+]留下一连串吻。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]在绝望中挣扎，发出一阵[npc2.a_sob+]。[npc.Name]将其[npc.pussy+]压向[npc2.her][npc2.lips+]。",
	
								"[npc2.name]在绝望中[npc2.sobbing]、挣扎，其抵抗完全无效。[npc.Name]将[npc.her][npc.labia+]压向了[npc2.her]的[npc2.face]。"));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
		
		//--- Additional methods: ---

		private List<GameCharacter> getOngoingCharacters() {
			return TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return TongueVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction());
		}
		
		private String getOngoingNames() {
			return TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			int size = TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size();
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				size--;
			}
			return size>0;
		}
		
		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();

			int size = getOngoingCharacters().size();
			
			map.put("one character performing cunnilingus", size==1);
			map.put("only ongoing vagina-actions are oral", size==Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).size());
			map.put("[npc2.namePos] mouth to be exposed", Main.sex.getCharacterTargetedForSexAction(this).isOrificeTypeExposed(SexAreaOrifice.MOUTH));
			map.put("[npc2.namePos] mouth to be free", SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterTargetedForSexAction(this)));
			
			return map;
		}
		
		//------
		
		@Override
		public String getActionTitle() {
			return "加入舔阴";
		}
		
		@Override
		public String getActionDescription() {
			return "让[npc2.name]加入"+getOngoingNames()+"一起舔你的下面。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"[npc.Name]对只有"+getOngoingNames()+"舔[npc.herHim]这件事感到并不满足，[npc.she]让"+(getOngoingCharacters().size()==1?"[npc3.name]":"他们")+"后退，暂时移开一点，"
										+ "然后温柔地将[npc2.namePos]的脸向前拉，让[npc2.name]把[npc2.lips+]贴到[npc.her]沾满唾液的[npc.labiaSize][npc.labia]上。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"[npc.Name]对只有"+getOngoingNames()+"舔[npc.herHim]这件事感到并不满足，[npc.she]将"+(getOngoingCharacters().size()==1?"[npc3.name]":"他们")
									+"推开，命令"+(getOngoingCharacters().size()==1?"[npc3.name]":"他们")+"暂时移到一旁，"
									+ "然后粗暴地将[npc2.namePos]的脸向前拉，强迫[npc2.name]把[npc2.lips+]贴到[npc.her]沾满唾液的[npc.labiaSize][npc.labia]上。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"[npc.Name]对只有"+getOngoingNames()+"舔[npc.herHim]这件事感到并不满足，[npc.she]让"+(getOngoingCharacters().size()==1?"[npc3.name]":"他们")+"后退，暂时移开一点，"
										+ "然后将[npc2.namePos]的脸向前拉，让[npc2.name]把[npc2.lips+]贴到[npc.her]沾满唾液的[npc.labiaSize][npc.labia]上。"));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"[npc.Name]对只有"+getOngoingNames()+"舔[npc.herHim]这件事感到并不满足，[npc.she]让"+(getOngoingCharacters().size()==1?"[npc3.name]":"他们")+"后退，暂时移开一点，"
										+ "然后急切地将[npc2.namePos]的脸向前拉，让[npc2.name]把[npc2.lips+]贴到[npc.her]沾满唾液的[npc.labiaSize][npc.labia]上。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵沉闷的[npc2.moan]，加入"+getOngoingNames()+"，充满爱意地舔舐亲吻[npc.namePos][npc.pussy+]。",
								"随着一阵柔软沉闷的[npc2.moan]，[npc2.name]加入"+getOngoingNames()+"，温柔地将[npc2.tongue]在[npc.namePos][npc.pussy]上下滑动。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵沉闷的[npc2.moan]，加入"+getOngoingNames()+"，粗暴地舔舐亲吻[npc.namePos][npc.pussy+]。",
								"[npc2.name]发出一阵[npc2.a_moan+]，和"+getOngoingNames()+"一起粗暴地用[npc2.tongue]上下舔弄[npc.namePos][npc.pussy]。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵沉闷的[npc2.moan]，加入"+getOngoingNames()+"，开始舔舐亲吻[npc.namePos][npc.pussy+]。",
								"[npc2.name]发出一阵[npc2.a_moan+]，和"+getOngoingNames()+"一起用[npc2.tongue]上下舔弄[npc.namePos][npc.pussy]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵沉闷的[npc2.sob]，拼命地尝试推开[npc.namePos]的腹股沟。",
								"[npc2.Name]发出低沉的[npc2.sob]，慌乱地努力拔着[npc.namePos][npc.pussy+]，"));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出一阵沉闷的[npc2.moan]，加入"+getOngoingNames()+"，开始亢奋地舔舐亲吻[npc.namePos][npc.pussy+]。",
								"[npc2.name]发出一阵[npc2.a_moan+]，急切地和"+getOngoingNames()+"一起用[npc2.tongue]上下舔弄[npc.namePos][npc.pussy]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]贪婪地将[npc2.tongue+]深深插入[npc.namePos][npc.pussy+]，"
									+ "[npc2.she]发出一声含糊不清的[npc2.moan]，将[npc2.lips+]压向[npc.namePos][npc.labia+]。",
		
							"[npc2.namePos]嘴里流出一声低沉的[npc2.moan]，"
									+ "同时，饥渴地将其[npc2.lips+]压向[npc.namePos][npc.labia+]，开始用其[npc2.tongue+]深入[npc.namePos][npc.pussy+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地用[npc2.lips+]压向[npc.namePos]的[npc.labia]，"
									+ "然后急切地用[npc2.her][npc2.tongue+]尽可能深入[npc.her][npc.pussy+]。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.face]从[npc.namePos]的[npc.labia]边移开，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
		
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然将自己[npc.labia+]压向[npc2.her][npc2.face+]。",
		
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地试图远离[npc.namePos]的[npc.labia]，"
									+ "[npc.name]将[npc.pussy+]压向[npc2.her][npc2.lips+]，[npc2.she]奋力反抗着[npc.name]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将[npc2.tongue+]滑入[npc.namePos][npc.pussy+]深处，"
									+ "[npc2.she]发出一声含糊不清的柔和[npc2.moan]，将[npc2.lips+]压向[npc.namePos][npc.labia+]。",
		
							"[npc2.namePos]的口中飘出一声含糊不清的[npc2.moan]，"
									+ "同时，温柔地将其[npc2.lips+]压向[npc.namePos][npc.labia+]，开始缓慢地将其[npc2.tongue+]深入[npc.namePos][npc.pussy+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地用[npc2.lips+]压向[npc.namePos]的[npc.labia]，"
									+ "随后温柔地将[npc2.her][npc2.tongue+]尽可能深地探入[npc.her][npc.pussy+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.tongue+]深深插入[npc.namePos][npc.pussy+]，"
									+ "[npc2.she]发出一声含糊不清的[npc2.moan]，将[npc2.lips+]压向[npc.namePos][npc.labia+]。",
		
							"[npc2.namePos]的口中飘出一声含糊不清的[npc2.moan]，"
									+ "[npc2.she]粗鲁地将[npc2.lips+]压向[npc.namePos][npc.labia+]，开始粗暴地将[npc2.tongue+]深深插入[npc.namePos][npc.pussy+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，激烈地用[npc2.lips+]压向[npc.namePos]的[npc.labia]，"
									+ "随后积极地将[npc2.her][npc2.tongue+]尽可能深地插入[npc.her][npc.pussy+]。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.tongue+]深深插入[npc.namePos][npc.pussy+]，"
									+ "[npc2.she]发出一声含糊不清的[npc2.moan]，将[npc2.lips+]压向[npc.namePos][npc.labia+]。",
		
							"[npc2.namePos]嘴里流出一声低沉的[npc2.moan]，"
									+ "同时，将其[npc2.lips+]压向[npc.namePos][npc.labia+]，开始用其[npc2.tongue+]深入[npc.namePos][npc.pussy+]。",
		
							"[npc2.name]愉悦地[npc2.moaning]着，开始将[npc2.lips+]压向[npc.namePos]的[npc.labia]，"
									+ "然后用[npc2.her][npc2.tongue+]尽可能深入[npc.her][npc.pussy+]。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RECEIVING_CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
		@Override
		public String getActionTitle() {
			return "接受舔阴(温柔)";
		}
		@Override
		public String getActionDescription() {
			return "温柔地将你[npc.labia+]压向[npc2.namePos]的[npc2.lips]，让[npc2.her][npc2.tongue+]伸进你[npc.pussy+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"让"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"靠边一点，"
									+ "[npc.Name]温柔地将[npc2.namePos]的头部前压，将其[npc2.lips+]压向自己[npc.labia+]，"
									+ "发出一阵柔软的[npc.moan]，逐步用[npc.her][npc.pussy+]摩擦[npc2.her][npc2.face]。",
							"温柔地指示"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让[npc2.name]主导，"
									+ "[npc.Name]缓慢地推动[npc.hips]到[npc2.her][npc2.face]，发出一阵柔软的[npc.moan]，让[npc2.herHim]口自己。",
							"[npc.Name]让"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"给[npc2.name]让开位置，然后[npc.she]温柔地将[npc.hips+]压向[npc2.namePos]的[npc2.face]，"
									+ "[npc.she]发出轻柔的[npc.moan]，开始让[npc2.name]舔弄自己[npc.pussy+]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]把[npc.labia+]温柔地压在[npc2.namePos][npc2.face+]上，"
								+ "发出一阵柔软的[npc.moan]，开始坚决地将其[npc.pussy+]在[npc2.her][npc2.lips+]上摩擦。",
						"[npc.Name]轻声[npc.moan]，将[npc.labia+]压向[npc2.namePos][npc2.face+]，温柔地用自己[npc.pussy+]磨蹭[npc2.her][npc2.lips+]。",
						"[npc.Name]发出一阵柔软的[npc.moan]，温柔地用[npc.her][npc.pussy+]摩擦[npc2.namePos][npc2.lips]。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
		@Override
		public String getActionTitle() {
			return "接受舔阴";
		}
		@Override
		public String getActionDescription() {
			return "饥渴地将你[npc.labia+]压向[npc2.namePos]的脸，让[npc2.her][npc2.tongue+]伸进你[npc.pussy+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"让"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"靠边一点，"
									+ "[npc.Name]饥渴地将[npc2.namePos]的头部前压，将其[npc2.lips+]压向自己[npc.labia+]，"
									+ "发出一阵[npc.a_moan+]，拼命地用[npc.her][npc.pussy+]摩擦[npc2.her][npc2.face]。",
							"坚定地指示"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让[npc2.name]主导，"
									+ "[npc.Name]饥渴地推动[npc.hips]到[npc2.her][npc2.face]，发出一阵[npc.a_moan+]，让[npc2.herHim]口自己。",
							"[npc.Name]让"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"给[npc2.name]让开位置，然后[npc.she]急切地将[npc.hips+]压向[npc2.namePos]的[npc2.face]，"
									+ "[npc.she]发出[npc.a_moan+]，开始让[npc2.name]舔弄自己[npc.pussy+]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.namePos]把[npc.labia+]饥渴地压在[npc2.namePos][npc2.face+]上，"
								+ "发出一阵[npc.a_moan+]，开始坚决地将其[npc.pussy+]在[npc2.her][npc2.lips+]上摩擦。",
						"随着一阵[npc.a_moan+]，[npc.Name]饥渴地将自己[npc.labia+]压向[npc2.namePos][npc2.face+]，贪婪地用其[npc.pussy+]摩擦[npc2.her][npc2.lips+]。",
						"伴随着一阵[npc.a_moan+]，[npc.name]饥渴地把[npc.her][npc.pussy+]顶着[npc2.namePos]的[npc2.lips]摩擦。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
		@Override
		public String getActionTitle() {
			return "接受舔阴(粗暴)";
		}
		@Override
		public String getActionDescription() {
			return "粗暴地将你[npc.pussy+]压向[npc2.namePos][npc2.tongue+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"命令"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"挪到一边，"
									+ "[npc.Name]粗暴地将[npc2.namePos]的头部前压，将其[npc2.lips+]压向自己[npc.labia+]，"
									+ "发出一阵[npc.a_moan+]，激烈地用[npc.her][npc.pussy+]摩擦[npc2.her][npc2.face]。",
							"坚定地指示"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让[npc2.name]主导，"
									+ "[npc.Name]粗暴地推动[npc.hips]到[npc2.her][npc2.face]，发出一阵[npc.a_moan+]，让[npc2.herHim]口自己。",
							"[npc.Name]让"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"给[npc2.name]让开位置，然后[npc.she]粗暴地将[npc.hips+]压向[npc2.namePos]的[npc2.face]，"
									+ "[npc.she]发出[npc.a_moan+]，开始强迫[npc2.name]舔弄自己[npc.pussy+]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]把[npc.labia+]粗暴地压在[npc2.namePos][npc2.face+]上，"
								+ "发出一阵[npc.a_moan+]，开始粗暴地将其[npc.pussy+]在[npc2.her][npc2.lips+]上冲撞。",
						"[npc.Name]轻声[npc.moan]，将[npc.labia+]压向[npc2.namePos][npc2.face+]，温柔地用自己[npc.pussy+]磨蹭[npc2.her][npc2.lips+]。",
						"伴随着一阵[npc.a_moan+]，[npc.name]粗暴地把[npc.her][npc.pussy+]顶着[npc2.namePos]的[npc2.lips]摩擦。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "抵抗被舔阴";
		}
		@Override
		public String getActionDescription() {
			return "努力让你[npc.pussy+]远离[npc2.namePos][npc2.tongue+]。";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地尝试将[npc.her][npc.labia]从[npc2.namePos][npc2.face]推开，"
									+ "发出一阵[npc.a_sob+]，[npc2.name]温柔地将其[npc2.tongue]滑入[npc.her][npc.pussy+]深处。",

							"[npc.Name]发出一阵[npc.a_sob+]，急切地尝试将其[npc.pussy+]从[npc2.namePos][npc2.lips+]推开。"
									+ "[npc2.name]无视[npc.her]的抵抗，固定住[npc.Name]，在其[npc.labia+]上种下一串柔软的吻。"
									+"随后温柔地将[npc2.her][npc2.tongue+]伸入[npc.her][npc.pussy+]。",

							"随着一阵[npc.a_sob+]，[npc.Name]拼命地尝试将其[npc.labia+]从[npc2.namePos][npc2.lips+]推开，但后者将[npc.herHim]牢牢固定，"
									+ "但被[npc2.name]无视反抗摁住，温柔地用舌头侵犯着[npc.pussy+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地尝试将[npc.her][npc.labia]从[npc2.namePos][npc2.face]推开，"
									+ "发出一阵[npc.a_sob+]，[npc2.name]粗暴地将其[npc2.tongue]滑入[npc.her][npc.pussy+]深处。",

							"[npc.Name]发出一阵[npc.a_sob+]，急切地尝试将其[npc.pussy+]从[npc2.namePos][npc2.lips+]推开。"
									+ "[npc2.name]无视[npc.her]的抵抗，固定住[npc.Name]，在其[npc.labia+]上种下一串湿润的吻。"
									+"随后粗暴地将[npc2.her]noun.[npc2.tongue+]插入[npc.her][npc.pussy+]。",

							"随着一阵[npc.a_sob+]，[npc.Name]拼命地尝试将其[npc.labia+]从[npc2.namePos][npc2.lips+]推开，但后者将[npc.herHim]牢牢固定，"
									+ "但被[npc2.name]无视反抗摁住，粗暴地用舌头侵犯着[npc.pussy+]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地尝试将[npc.her][npc.labia]从[npc2.namePos][npc2.face]推开，"
									+ "发出一阵[npc.a_sob+]，[npc2.name]贪婪地将其[npc2.tongue]滑入[npc.her][npc.pussy+]深处。",

							"[npc.Name]发出一阵[npc.a_sob+]，急切地尝试将其[npc.pussy+]从[npc2.namePos][npc2.lips+]推开。"
									+ "[npc2.name]无视[npc.her]的抵抗，固定住[npc.Name]，在其[npc.labia+]上种下一串激情的吻。"
									+"随后贪婪地将[npc2.her]noun.[npc2.tongue+]伸入[npc.her][npc.pussy+]。",

							"随着一阵[npc.a_sob+]，[npc.Name]拼命地尝试将其[npc.labia+]从[npc2.namePos][npc2.lips+]推开，但后者将[npc.herHim]牢牢固定，"
									+ "但被[npc2.name]无视反抗摁住，饥渴地用舌头侵犯着[npc.pussy+]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "接受舔阴";
		}
		@Override
		public String getActionDescription() {
			return "将你[npc.pussy+]压向[npc2.namePos][npc2.face+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"让"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"靠边一点，"
									+ "[npc.Name]将[npc2.namePos]的头部前压，将其[npc2.lips+]压向自己[npc.labia+]，"
									+ "发出一阵[npc.a_moan+]，用[npc.her][npc.pussy+]摩擦[npc2.her][npc2.face]。",
							"叫"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让[npc2.name]主导，"
									+ "[npc.Name]饥渴地推动[npc.hips]到[npc2.her][npc2.face]，发出一阵[npc.a_moan+]，让[npc2.herHim]口自己。",
							"[npc.Name]让"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"给[npc2.name]让开位置，然后[npc.she]将[npc.hips+]压向[npc2.namePos]的[npc2.face]，"
									+ "[npc.she]发出[npc.a_moan+]，开始让[npc2.name]舔弄自己[npc.pussy+]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"将[npc.her][npc.labia+]压在[npc2.namePos][npc2.face+]上，"
								+ "发出一阵[npc.a_moan+]，开始坚决地将其[npc.pussy+]在[npc2.her][npc2.lips+]上摩擦。",
						"随着一阵[npc.a_moan+]，[npc.Name]将自己[npc.labia+]压向[npc2.namePos][npc2.face+]，用其[npc.pussy+]摩擦[npc2.her][npc2.lips+]。",
						"伴随着一阵[npc.a_moan+]，[npc.name]把[npc.her][npc.pussy+]顶着[npc2.namePos]的[npc2.lips]摩擦。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "接受舔阴(渴求)";
		}
		@Override
		public String getActionDescription() {
			return "饥渴地用你[npc.pussy+]磨蹭[npc2.namePos][npc2.tongue+]。";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"让"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"靠边一点，"
									+ "[npc.Name]饥渴地将[npc2.namePos]的头部前压，将其[npc2.lips+]压向自己[npc.labia+]，"
									+ "发出一阵[npc.a_moan+]，拼命地用[npc.her][npc.pussy+]摩擦[npc2.her][npc2.face]。",
							"坚定地指示"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"让[npc2.name]主导，"
									+ "[npc.Name]饥渴地推动[npc.hips]到[npc2.her][npc2.face]，发出一阵[npc.a_moan+]，让[npc2.herHim]口自己。",
							"[npc.Name]让"+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+"给[npc2.name]让开位置，然后[npc.she]急切地将[npc.hips+]压向[npc2.namePos]的[npc2.face]，"
									+ "[npc.she]发出[npc.a_moan+]，开始让[npc2.name]舔弄自己[npc.pussy+]。"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.namePos]把[npc.labia+]饥渴地压在[npc2.namePos][npc2.face+]上，"
								+ "发出一阵[npc.a_moan+]，开始坚决地将其[npc.pussy+]在[npc2.her][npc2.lips+]上摩擦。",
						"随着一阵[npc.a_moan+]，[npc.Name]饥渴地将自己[npc.labia+]压向[npc2.namePos][npc2.face+]，贪婪地用其[npc.pussy+]摩擦[npc2.her][npc2.lips+]。",
						"伴随着一阵[npc.a_moan+]，[npc.name]饥渴地把[npc.her][npc.pussy+]顶着[npc2.namePos]的[npc2.lips]摩擦。"));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "停止接受舔阴";
		}
		@Override
		public String getActionDescription() {
			return "让[npc2.name]把[npc2.her][npc2.tongue+]从你[npc.pussy+]里拔出来。";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗暴地将[npc2.namePos]的头从自己[npc.pussy+]拉开，命令[npc2.herHim]停止提供舔阴。",

							"[npc.Name]最后一次粗暴地将自己[npc.pussy+]在[npc2.namePos][npc2.face]上摩擦，之后推开[npc.hips]，结束舔阴。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]将[npc2.namePos]的头从自己[npc.pussy+]推开，告诉[npc2.herHim]停止提供舔阴。",

							"[npc.Name]最后一次将自己[npc.pussy+]压向[npc2.namePos]的[npc2.face]，之后推开[npc.her]的[npc.hips]，结束了舔阴。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"泪水从[npc2.name][npc2.face]划过，意识到[npc.nameIsFull]没有在[npc2.her]身上完成，发出一阵[npc2.a_sob+]。",
	
								"[npc2.name]继续奋力反抗着[npc.Name]，[npc2.she]发出一阵[npc2.a_sob+]，乞求[npc.name]放过自己。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]想给予[npc.namePos][npc.pussy+]更多口交的渴望。",
	
								"[npc.Name]走开了，但[npc2.name]还未满足，发出一阵[npc2.a_moan+]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

}
