package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.CondomFailure;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.OrgasmEncourageBehaviour;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.69
 * @version 0.4.11.1
 * @author Innoxia
 */
public class GenericOrgasms {
	
	public static boolean isTakingCock(GameCharacter character, GameCharacter penetrator) {
		return !Collections.disjoint(
				Main.sex.getOngoingSexAreas(penetrator, SexAreaPenetration.PENIS, character),
				Util.newArrayListOfValues(
						SexAreaOrifice.VAGINA,
						SexAreaOrifice.ANUS,
						SexAreaOrifice.NIPPLE,
						SexAreaOrifice.MOUTH,
						SexAreaOrifice.SPINNERET,
						SexAreaOrifice.TUSSY,
						SexAreaOrifice.URETHRA_PENIS,
						SexAreaOrifice.URETHRA_VAGINA,
						SexAreaOrifice.BREAST,
						SexAreaOrifice.ARMPITS,
						SexAreaOrifice.THIGHS,
						SexAreaPenetration.FOOT));
	}
	
	/**
	 * For SexAreaPenetration.PENIS checks, this only works if the penis is not a dildo (as this method is only used for forced creampie checks, in which case dildos shouldn't be counted)
	 */
	private static boolean isTakingCockInOrifice(GameCharacter character, GameCharacter penetrator, List<SexAreaInterface> orificesToCheck) {
		// Only check for penis-to-penis if it's a real cock
		List<SexAreaInterface> copyList = new ArrayList<>(orificesToCheck);
		if(!isRealPenisFuckingCharacter(character, penetrator)) {
			copyList.remove(SexAreaPenetration.PENIS);
		}
		
		return !Collections.disjoint(
				Main.sex.getOngoingSexAreas(penetrator, SexAreaPenetration.PENIS, character),
				copyList);
	}
	
	private static boolean isRealPenisFuckingCharacter(GameCharacter character, GameCharacter penetrator) {
		return Main.sex.getCharacterOngoingSexArea(penetrator, SexAreaPenetration.PENIS).contains(character)
				&& penetrator.hasPenisIgnoreDildo();
	}

	public static boolean isCumTargetRequirementsMet(SexActionInterface sexAction, OrgasmCumTarget cumTarget) {
		OrgasmCumTarget preferredPulloutTarget = Main.sex.getInitialSexManager().getCharacterPullOutOrgasmCumTarget(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
		SexAreaOrifice penisPenetratingArea = Main.sex.getFirstOngoingSexAreaOrifice(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		boolean isPenetratingInternalOrifice = penisPenetratingArea!=null && penisPenetratingArea.isInternalOrifice();
		
		if(!Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).contains(cumTarget)
				|| (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotGeneric.MISC_WATCHING && cumTarget.isRequiresPartner())
				|| !Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
				|| !Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)
				// If the character is wearing a condom, then they will always creampie if penetrating an orifice, otherwise allow them to choose an area to cum onto if their condom breaks:
				|| (Main.sex.getCharacterPerformingAction().isWearingCondom()
						&& (sexAction.getCondomFailure(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))==CondomFailure.NONE
							|| isPenetratingInternalOrifice))
				|| (!Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0)
				|| (preferredPulloutTarget!=null && preferredPulloutTarget!=cumTarget)) {
			return false;
		}
		
		return true;
	}

	private static boolean isCharacterTotallyImmobilised(GameCharacter character) {
		return Main.sex.isCharacterImmobilised(character) && Main.sex.isCharacterInanimateFromImmobilisation(character);
	}
	
	private static boolean isPerformingCharacterTotallyImmobilised() {
		return isCharacterTotallyImmobilised(Main.sex.getCharacterPerformingAction());
	}
	
	private static boolean isTargetedCharacterTotallyImmobilised(SexActionInterface sexAction) {
		return isCharacterTotallyImmobilised(Main.sex.getCharacterTargetedForSexAction(sexAction));
	}
	
	private static String getPositionPreparation(GameCharacter characterOrgasming, GameCharacter characterTargeted) {
		if(characterTargeted!=null) {
			String orgasmText = Main.sex.getSexPositionSlot(characterOrgasming).getOrgasmDescription(characterOrgasming, characterTargeted);
			return UtilText.parse(characterOrgasming, characterTargeted, orgasmText);
			
		} else {
			String orgasmText = Main.sex.getSexPositionSlot(characterOrgasming).getOrgasmDescription(characterOrgasming, Main.sex.getTargetedPartner(characterOrgasming));
			return UtilText.parse(characterOrgasming, Main.sex.getTargetedPartner(characterOrgasming), orgasmText);
		}
	}
	
	private static String getAhegaoDescription(GameCharacter characterOrgasming, GameCharacter characterTargeted) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>");
		if(characterOrgasming.isPlayer()) {
			sb.append("[npc.name]迎来高潮，[npc.she]情不自禁地发出了一声极其响亮、颤抖的[npc.moan]。"
					+ "刹那间，你的大脑一片空白，你失去了自我控制，被一阵阵冲刷而来的快感淹没，你无意之间发现自己露出了超级高潮脸。"
					+ "[npc.tongue+]搭出来，脸颊涨得通红，止不住地翻起白眼。"
					+ "[npc.Name]脸上挂着夸张的狂喜表情，又发出迷乱的[npc.moan]，准备经受即将到来的高潮的全部冲击。");
			
		} else {
			sb.append("[npc.name]迎来了高潮，发出极其大声、还在颤抖着的[npc.moan]。"
					+ "刹那间，大脑一片空白，[npc.her]失去了自我控制，被一阵阵冲刷而来的快感淹没，[npc.her]无意之间发现自己露出了超级高潮脸。"
					+ "[npc.tongue+]搭出来，脸颊涨得通红，止不住地翻起白眼。"
					+ "[npc.Name]脸上挂着夸张的狂喜表情，又发出迷乱的[npc.moan]，准备经受即将到来的高潮的全部冲击。");
		}
		sb.append("</p>");
		
		return UtilText.parse(characterOrgasming, characterTargeted, sb.toString());
	}
	
	private static String getPenisOrgasmModifierDescriptionPostfix(GameCharacter targetedCharacter) {
		if(targetedCharacter.isAsleep()) {
			return UtilText.returnStringAtRandom(
					"，但即便有着这额外的刺激，[npc2.she]也没有醒来。",
					"，但[npc2.she]仍然熟睡着。");
			
		} else if(isCharacterTotallyImmobilised(targetedCharacter)) {
			return UtilText.returnStringAtRandom(
					"，但即便有着这额外的刺激，[npc2.she]也没有做出任何反应。",
					"，但[npc2.she]仍然保持安静且一动不动。");
			
		} else {
			return "，引得[npc2.herHim]连连发出[npc2.a_moan+]。";
		}
	}
	
	private static StringBuilder genericOrgasmSB = new StringBuilder();
	
	public static String getGenericPenisOrgasmDescription(SexActionInterface sexAction, GameCharacter characterOrgasming, GameCharacter characterTargeted, OrgasmCumTarget cumTarget, CondomFailure condomFailure, boolean isSecondaryCreampieTarget) {
		genericOrgasmSB.setLength(0);

		SexAreaInterface contactingArea = null;
		if(!Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).isEmpty()) {
			contactingArea = Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).get(0);
		}
		
		if(!isSecondaryCreampieTarget) {
			if(!characterOrgasming.equals(characterTargeted)) { // Do not append this part if the target is the same person as the performer
				if(Main.sex.getCreampieLockedBy().containsKey(characterOrgasming)) {
					GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(characterOrgasming).getKey();
					Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(characterOrgasming).getValue();
					String orgasmPreventionDesc = "拔出";
					if(Main.sex.getOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS, lockingCharacter).contains(SexAreaPenetration.PENIS)) {
						orgasmPreventionDesc = "将[npc.her][npc.cock+]从[npc2.namePos]体内拔出"; // frotting variation
					}
					
					if(bodypart == Torso.class) {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
								"[npc.nameIsFull]被[npc2.name]紧紧贴在身上，无法"+orgasmPreventionDesc+"，只得发出[npc.a_moan+]，游走于高潮的边缘。"));
						
					} else if(bodypart == Arm.class) {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
								"[npc.nameIsFull]被[npc2.namePos]的[npc2.arms]箍住后腰，无法"+orgasmPreventionDesc+"，只得发出[npc.a_moan+]，游走于高潮的边缘。"));
						
					} else if(bodypart == Leg.class) {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
								"[npc.nameIsFull]被[npc2.namePos]的[npc2.legs]箍住后腰，无法"+orgasmPreventionDesc+"，只得发出[npc.a_moan+]，游走于高潮的边缘。"));
						
					} else if(bodypart == Tail.class) {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
								"[npc.nameIsFull]被[npc2.namePos]的[npc2.tail]箍住后腰，无法"+orgasmPreventionDesc+"，只得发出[npc.a_moan+]，游走于高潮的边缘。"));
						
					} else if(bodypart == Tail.class) {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
								"[npc.nameIsFull]被[npc2.namePos][npc2.wingSize]的[npc2.wings]箍住身子，无法"+orgasmPreventionDesc+"，只得发出[npc.a_moan+]，游走于高潮的边缘。"));
						
					} else if(bodypart == Tentacle.class) {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
								"[npc.nameIsFull]被[npc2.namePos]的[npc2.tentacles]箍住后腰，无法"+orgasmPreventionDesc+"，只得发出[npc.a_moan+]，游走于高潮的边缘。"));
					}
				}
			}
			
		} else {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.PENIS).get(0);
			
			if(contactingArea!=null) {
				switch((SexAreaOrifice)contactingArea) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
					case TUSSY:
						break;
					case ANUS:
						genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterPenetrated, characterTargeted),
								"[npc.Name]才半数射入[npc2.namePos][npc2.asshole+]，尚在高潮冲击中，却突然拔了出来，"
										+ "然后快速移动到[npc3.name]身旁，将[npc.cock+][npc.cockHead+]顶入[npc3.her][npc3.asshole+]。"));
						break;
					case VAGINA:
						genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterPenetrated, characterTargeted),
								"[npc.Name]才半数射入[npc2.namePos][npc2.pussy+]，尚在高潮冲击中，却突然拔了出来，"
										+ "然后快速移动到[npc3.name]身旁，将[npc.cock+][npc.cockHead+]顶入[npc3.her][npc3.pussy+]。"));
						break;
				}
			}
		}
		
		if(characterTargeted==null || (cumTarget!=OrgasmCumTarget.INSIDE && cumTarget!=OrgasmCumTarget.INSIDE_SWITCH_DOUBLE)) {
			List<String> modifiers = new ArrayList<>();
			for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
				switch(mod) {
					case FLARED:
						if(characterOrgasming.hasPenisModifier(mod)) {
							modifiers.add("[npc.namePos][npc.cock]肥厚平坦的顶部充血胀大，[npc.she]感觉[npc.balls+]骤然缩紧，要开始射精了。");
						}
						break;
					case KNOTTED:
						if(characterOrgasming.hasPenisModifier(mod)) {
							modifiers.add("[npc.namePos][npc.cock]根部肥厚的结充血膨大，[npc.she]感觉到[npc.balls+]缩紧，准备射精。");
						}
						break;
					case TENTACLED:
						if(characterOrgasming.hasPenisModifier(mod)) {
							modifiers.add("[npc.namePos][npc.cock]上连着的小触手疯狂扭动，[npc.balls+]骤然缩紧，精液窜出。");
						}
						break;
					case BARBED:
					case BLUNT:
					case PREHENSILE:
					case RIBBED:
					case SHEATHED:
					case TAPERED:
					case VEINY:
					case OVIPOSITOR:
						break;
				}
			}

			List<GameCharacter> ongoingProstateStimulators = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER));
			if(characterOrgasming.hasVagina()) {
				ongoingProstateStimulators = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
			}
			if(!modifiers.isEmpty()) {
				if(!ongoingProstateStimulators.isEmpty() && !isCharacterTotallyImmobilised(ongoingProstateStimulators.get(0))) {
					if(ongoingProstateStimulators.get(0).equals(characterOrgasming)) {
						genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0),
								"这时，[npc.name]弯曲[npc.fingers+]，深入[npc.her]"+(characterOrgasming.hasVagina()?"[npc.pussy+]":"[npc.asshole+]")+"，"
									+ "快速地抚摸按摩[npc.her]的前列腺，尝试从自己身上尽可能榨取更多[npc.cum]。"
									+ "[npc.her]的身体立刻对刺激有了反应，"));
					} else {
						genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0), characterOrgasming,
								"这时，[npc.name]弯曲[npc.fingers+]，深入[npc2.namePos]"+(characterOrgasming.hasVagina()?"[npc2.pussy+]":"[npc2.asshole+]")+"，"
									+ "快速地抚摸按摩[npc2.her]的前列腺，尝试从[npc2.herHim]身上尽可能榨取更多[npc2.cum]。"
									+ "[npc2.her]的身体立刻对刺激有了反应，"));
					}
				} else {
					genericOrgasmSB.append("");
				}
				genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
				
			} else {
				if(!ongoingProstateStimulators.isEmpty() && !isCharacterTotallyImmobilised(ongoingProstateStimulators.get(0))) {
					if(ongoingProstateStimulators.get(0).equals(characterOrgasming)) {
						genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0),
								"这时，[npc.name]弯曲[npc.her][npc.fingers+]，深入[npc.her]"+(characterOrgasming.hasVagina()?"[npc.pussy+]":"[npc.asshole+]")+"，"
										+ "快速地抚摸按摩[npc.her]的前列腺，尝试从自己身上尽可能榨取更多[npc.cum]。"
										+ "[npc.her]的身体立刻对刺激有了反应，[npc.cock+]抽动着，"
											+ "[npc.balls+]骤然缩紧，精液窜出。"));
					} else {
						genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0), characterOrgasming,
								"这时，[npc.name]弯曲[npc.fingers+]，深入[npc2.namePos]"+(characterOrgasming.hasVagina()?"[npc2.pussy+]":"[npc2.asshole+]")+"，"
									+ "快速地抚摸按摩[npc2.her]的前列腺，尝试从[npc2.herHim]身上尽可能榨取更多[npc2.cum]。"
									+ "[npc2.her]的身体立刻对刺激有了反应，[npc2.cock+]抽动几下，"
										+ "感到[npc2.balls+]骤然缩紧，开始射精。"));
					}
				} else {
					genericOrgasmSB.append("[npc.NamePos][npc.cock+]颤动着，[npc.balls+]骤然缩紧，精液窜出。");
				}
			}
			
			if(characterTargeted!=null && contactingArea!=null) {
				genericOrgasmSB.append("<br/>");
				
				boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
				boolean sleeping = characterOrgasming.isAsleep();
				boolean immobileTarget = isCharacterTotallyImmobilised(characterTargeted);
				boolean sleepingTarget = characterTargeted.isAsleep();
				boolean selfTargeting = characterOrgasming.equals(characterTargeted);
				
				if(contactingArea.isOrifice()) {
					if(immobile) {
						if(sleeping) {
							genericOrgasmSB.append("[npc.Name]还在睡，而且[npc.her][npc.cock+]蹭着[npc2.namePos]的"+((SexAreaOrifice) contactingArea).getName(characterTargeted));
						} else {
							genericOrgasmSB.append("[npc.Name]还是完全没反应，而且[npc.her][npc.cock+]蹭着[npc2.namePos]的"+((SexAreaOrifice) contactingArea).getName(characterTargeted));
						}
						
					} else {
						switch((SexAreaOrifice) contactingArea) {
							case ARMPITS:
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos][npc2.armpit+]中抽出[npc.cock+]");
								break;
							case ANUS:
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos][npc2.asshole+]拔出[npc.cock+]");
								break;
							case ASS:
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos]的屁股蛋里抽出[npc.her][npc.cock+]");
								break;
							case BREAST:
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos][npc2.breasts+]间抽出[npc.cock+]");
								break;
							case BREAST_CROTCH:
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos][npc2.crotchBoobs+]间抽出[npc.cock+]");
								break;
							case MOUTH:
								if(!Main.sex.getCreampieLockedBy().containsKey(characterOrgasming)) {
									GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(characterOrgasming);
									genericOrgasmSB.append(UtilText.parse(characterOrgasming, primary, "[npc.Name]从[npc2.namePos]嘴里抽出[npc.cock+]"));
								} else {
									genericOrgasmSB.append("[npc.Name]从[npc2.namePos]嘴里抽出[npc.cock+]");
								}
								break;
							case NIPPLE:
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos][npc2.nipple+]中抽出[npc.cock+]");
								break;
							case NIPPLE_CROTCH:
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos][npc2.crotchNipple+]中抽出[npc.cock+]");
								break;
							case THIGHS:
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos]的大腿缝中抽出[npc.cock+]");
								break;
							case URETHRA_PENIS:
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos][npc2.penisUrethra+]中抽出[npc.cock+]");
								break;
							case URETHRA_VAGINA:
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos][npc2.vaginaUrethra+]中抽出[npc.cock+]");
								break;
							case VAGINA:
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos][npc2.pussy+]中抽出[npc.cock+]");
								break;
							case SPINNERET:
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos][npc2.spinneret+]中抽出[npc.cock+]");
								break;
							case TUSSY:
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.tussy+]");
								break;
						}
					}
				
					if(!characterOrgasming.getPenisModifiers().isEmpty() && !immobile) {
						switch(characterOrgasming.getPenisModifiers().get(Util.random.nextInt(characterOrgasming.getPenisModifiers().size()))) {
							case BARBED:
								genericOrgasmSB.append("，然后伸[npc.hand]在[npc.her]敏感的小倒刺上捋来抚去。");
								break;
							case BLUNT:
								genericOrgasmSB.append("，然后摸向下体，疯狂自慰起来，[npc.hand]从根到头撸动着[npc.cock]，挑逗着钝头。");
								break;
							case FLARED:
								genericOrgasmSB.append("，然后摸向下体，疯狂自慰起来，[npc.hand]从根到头撸动着[npc.cock]，挑逗着宽大的平头。");
								break;
							case KNOTTED:
								genericOrgasmSB.append("，然后摸向下体，疯狂自慰起来，[npc.hand]从根到头撸动着[npc.cock]，磨蹭着膨大的结。");
								break;
							case PREHENSILE:
								genericOrgasmSB.append("，然后摸向下体，疯狂自慰起来；"
										+ "[npc.hand]上下套弄灵活可控的[npc.cock]，将它弯起来抵着[npc.fingers]。");
								break;
							case RIBBED:
								genericOrgasmSB.append("，然后伸[npc.hand]在[npc.cock]凹凸不平的螺纹上搓来搓去。");
								break;
							case SHEATHED:
								genericOrgasmSB.append("，然后摸向下体，疯狂自慰起来；"
											+ "[npc.hand]上下套弄着[npc.cock]，带下了鞘，然后又撸回到[npc.cockHead+]上。");
								break;
							case TAPERED:
								genericOrgasmSB.append("，然后摸向下体，疯狂自慰起来，[npc.hand]从根到头撸动着[npc.cock]，挑逗着锥头。");
								break;
							case TENTACLED:
								genericOrgasmSB.append("，然后摸向下体，疯狂自慰起来，[npc.hand]在[npc.cock]侧面蠕动的小触手上搓来搓去。");
								break;
							case VEINY:
								genericOrgasmSB.append("，然后伸向下体，[npc.hand]上下套弄着脉状[npc.cock]。");
								break;
							case OVIPOSITOR:
								genericOrgasmSB.append("，然后伸向下体，[npc.hand]上下套弄着那[npc.cock+]。");
								break;
						}
						
					} else {
						if(immobile) {
							if(sleeping) {
								genericOrgasmSB.append("。");
							} else {
								genericOrgasmSB.append("，完美演绎着一个无生命的性爱玩偶。");
							}
						} else {
							genericOrgasmSB.append("，然后摸向下体，疯狂自慰起来，[npc.hand]从根到头撸动着[npc.cock]，挑逗着[npc.cockHead]。");
						}
					}
					
				} else {
					switch((SexAreaPenetration) contactingArea) {
						case CLIT:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append("[npc.Name]还在睡，而且[npc.her][npc.cock+]蹭着[npc2.namePos][npc2.pussy+]。");
								} else {
									genericOrgasmSB.append("[npc.Name]继续一动不动，而且[npc.her][npc.cock+]蹭着[npc2.namePos][npc2.pussy+]，完美演绎着一个无生命的性爱玩偶。");
								}
							} else {
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos][npc2.pussy+]中抽出[npc.cock+]，然后抚向下体，疯狂自慰起来，"
										+ "[npc.hand]从根到头撸动着[npc.cock]，挑逗着[npc.cockHead]。");
							}
							break;
						case FINGER:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append("[npc.Name]还在睡，而且");
								} else {
									genericOrgasmSB.append("[npc.Name]还是完全没反应，而且");
								}
								if(immobileTarget) {
									if(sleepingTarget) {
										genericOrgasmSB.append("[npc2.name]也睡着，[npc2.fingers+]环着[npc.namePos][npc.cock+]。");
									} else {
										genericOrgasmSB.append(" [npc2.name]同样一动不动，[npc2.fingers+]环着[npc.namePos][npc.cock+]。");
									}
								} else {
									genericOrgasmSB.append(
											UtilText.returnStringAtRandom(
													"[npc2.name]继续磨蹭[npc.cock+]。",
													selfTargeting?null:"[npc2.name]继续给[npc.herHim]手冲，直到[npc.her]高潮。",
													"[npc2.name]在高潮时继续磨蹭着[npc.her][npc.cock+]。"));
								}
							} else {
								genericOrgasmSB.append(
										UtilText.returnStringAtRandom(
												"[npc.Name]发出[npc.a_moan+]，向前拱[npc.hips+]",
												"[npc.Name]一边发出[npc.a_moan+]，一边向前拱[npc.hips+]",
												"向前拱着[npc.hips]，[npc.Name]发出[npc.a_moan+]"));
								if(immobileTarget) {
									if(sleepingTarget) {
										genericOrgasmSB.append("[npc2.name]还在睡，[npc2.fingers+]环着[npc.namePos][npc.cock+]。");
									} else {
										genericOrgasmSB.append("[npc2.name]纹丝不动，[npc2.fingers+]环着[npc.namePos][npc.cock+]。");
									}
								} else {
									genericOrgasmSB.append(
											UtilText.returnStringAtRandom(
													"[npc2.name]继续磨蹭着[npc.her][npc.cock+]。",
													selfTargeting?null:"[npc2.name]继续给[npc.herHim]手冲，直到[npc.her]高潮。",
													"而且[npc2.name]在高潮时继续磨蹭着[npc.her][npc.cock+]。"));
								}
							}
							break;
						case PENIS:
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case FOOT:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append("[npc.Name]还在睡");
								} else {
									genericOrgasmSB.append("[npc.Name]还是完全没反应");
								}
							} else {
								genericOrgasmSB.append("拱起[npc.hips]，[npc.Name]发出[npc.a_moan+]");
							}
							if(immobileTarget) {
								if(sleepingTarget) {
									genericOrgasmSB.append("[npc2.namePos][npc2.feet+]包围着[npc.her][npc.cock+]。");
								} else {
									genericOrgasmSB.append("[npc2.name]用[npc2.feet+]包围着[npc.namePos][npc.cock+]。");
								}
							} else {
								genericOrgasmSB.append("[npc2.name]继续用[npc2.feet+]刺激着[npc.her][npc.cock+]。");
							}
							break;
						case TONGUE:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append("[npc.Name]还在睡，而且[npc.her][npc.cock+]蹭着[npc2.namePos][npc2.lips+]。");
								} else {
									genericOrgasmSB.append("[npc.Name]继续一动不动，而且[npc.her][npc.cock+]蹭着[npc2.namePos][npc2.lips+]，完美演绎着一个无生命的性爱玩偶。");
								}
							} else {
								genericOrgasmSB.append("[npc.Name]从[npc2.namePos]嘴里抽出[npc.cock+]，然后抚向下体，疯狂自慰起来，"
										+ "[npc.hand]从根到头撸动着[npc.cock]，挑逗着[npc.cockHead]。");
							}
							break;
					}
				}
				
			}
			
			
		} else if(cumTarget==OrgasmCumTarget.INSIDE || cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE) {
			List<String> modifiers = new ArrayList<>();
			
			boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
			boolean sleeping = characterOrgasming.isAsleep();
			boolean immobileTarget = isCharacterTotallyImmobilised(characterTargeted); //TODO
			
			String penisModPostfix = getPenisOrgasmModifierDescriptionPostfix(characterTargeted);
			
			List<GameCharacter> ongoingProstateStimulators = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER));
			if(characterOrgasming.hasVagina()) {
				ongoingProstateStimulators = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
			}
			ongoingProstateStimulators.removeIf(c->Main.sex.getSexPace(c)==SexPace.SUB_RESISTING);
			if(!ongoingProstateStimulators.isEmpty() && !isCharacterTotallyImmobilised(ongoingProstateStimulators.get(0))) {
				if(ongoingProstateStimulators.get(0).equals(characterOrgasming)) {
					genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0),
							"这时，[npc.name]弯曲[npc.fingers+]，深入[npc.her]"+(characterOrgasming.hasVagina()?"[npc.pussy+]":"[npc.asshole+]")+"，"
									+ "快速地抚摸按摩[npc.her]的前列腺，尝试从[npc.herself]尽可能榨取更多[npc.cum]。"));
				} else {
					genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0), characterOrgasming,
							"这时，[npc.name]弯曲[npc.her][npc.fingers+]，深入[npc2.namePos]"+(characterOrgasming.hasVagina()?"[npc2.pussy+]":"[npc2.asshole+]")+"，"
								+ "快速地抚摸按摩[npc2.her]的前列腺，尝试从[npc2.herHim]尽可能榨取更多[npc2.cum]。"));
				}
			}
			
			if(contactingArea!=null) {
				if(contactingArea.isOrifice()) {
					switch((SexAreaOrifice)contactingArea) {
						case ANUS:
						case NIPPLE:
						case NIPPLE_CROTCH:
						case VAGINA:
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
						case SPINNERET:
                        case TUSSY:
							// ...
							String orificeName =
								(contactingArea == SexAreaOrifice.SPINNERET
									?"丝囊"
									:(contactingArea == SexAreaOrifice.VAGINA
										?"[npc2.pussy]"
										:(contactingArea == SexAreaOrifice.ANUS
											?"[npc2.asshole]"
											:(contactingArea == SexAreaOrifice.NIPPLE
													?"[npc2.nipple(true)]"
													:(contactingArea == SexAreaOrifice.NIPPLE_CROTCH
															?"[npc2.crotchNipple]"
															:(contactingArea == SexAreaOrifice.TUSSY
																?"[npc2.tussy]"
																	:"urethra"))))));
							String orificeNamePlusDescriptor =
									(contactingArea == SexAreaOrifice.SPINNERET
										?"的丝囊穴"
										:(contactingArea == SexAreaOrifice.VAGINA
											?"[npc2.pussy+]"
											:(contactingArea == SexAreaOrifice.ANUS
												?"[npc2.asshole+]"
												:(contactingArea == SexAreaOrifice.NIPPLE
														?"[npc2.nipple+]"
														:(contactingArea == SexAreaOrifice.NIPPLE_CROTCH
																?"[npc2.crotchNipple+]"
																:(contactingArea == SexAreaOrifice.TUSSY
																	?"[npc2.tussy+]"
																		:"urethra"))))));
							
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append("[npc2.name]还在睡着，[npc.name]继续把[npc.cock+]深深送入[npc2.namePos]"+orificeNamePlusDescriptor+"。");
								} else {
									genericOrgasmSB.append("眼看没有反应，[npc.name]继续把[npc.cock+]深入[npc2.namePos]"+orificeNamePlusDescriptor+"。");
								}
								
							} else if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
								if(cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE) {
									if(!isSecondaryCreampieTarget) {
										GameCharacter secondaryTarget = getSecondaryCreampieTarget(characterTargeted, (SexAreaOrifice) contactingArea);
										if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
											genericOrgasmSB.append("[npc.name]向前送腰，[npc.cock+]完全刺入[npc2.namePos]"+orificeNamePlusDescriptor+"。");
											if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), secondaryTarget)) {
												genericOrgasmSB.append("想要把[npc.her]迅速肿大的结留着给"+(UtilText.parse(characterOrgasming, secondaryTarget, "[npc2.namePos]"+orificeNamePlusDescriptor))+"，"
																+ "[npc.she]抑制住顶到[npc2.name]里面的想法，只是浅浅磨蹭着"+orificeName+"。");
											} else {
												genericOrgasmSB.append("[npc.she]不想被锁在[npc2.namePos]体内，于是抑制住了把正在迅速膨大的结撞进[npc2.herHim]"+orificeName+"内的冲动。");
											}
											
										} else if(characterOrgasming.isWantingToFullyPenetrate(characterTargeted, SexAreaPenetration.PENIS)) {
											genericOrgasmSB.append("[npc.Name]向前送腰，感觉[npc.cock+]捅到了[npc2.namePos]"+orificeNamePlusDescriptor+"最里面。");
											if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), secondaryTarget)) {
												genericOrgasmSB.append("迷乱地把迅速肿大的结埋进什么里，[npc.Name]看向"
														+(UtilText.parse(characterOrgasming, secondaryTarget, "[npc2.namePos]"+orificeNamePlusDescriptor))+"，发出兴奋的[npc.moan]。");
											} else {
												genericOrgasmSB.append("[npc.She]发出失望的[npc.moan]，意识到"
														+ "迅速膨大的结没法撞进[npc2.namePos]的"+orificeName+"。");
											}
											
										} else {
											genericOrgasmSB.append("[npc.Name]不想让[npc2.namePos]不舒服，便忍住了，没把整根[npc.cock]顶进[npc2.her]"+orificeNamePlusDescriptor+"里。");
										}
										
									} else {
										if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
											genericOrgasmSB.append("[npc.Name]向前送腰，冲撞着[npc.cock+]根部现在完全膨大的结，完全操进[npc2.namePos]"+orificeNamePlusDescriptor+"。"
													+ "现在它完全充血硬起，几乎不可能挤进去。但[npc.name]边充满决心地[npc.moan]，边猛地向前顶入，"
														+ "[npc.she]下了蛮力，肥厚的结恣意挺进[npc2.her]"+orificeNamePlusDescriptor+"，[npc2.name]轻颤着喊出声来。");
										} else {
											genericOrgasmSB.append("[npc.she]不想被锁在[npc2.namePos]体内，于是抑制住了把正在迅速膨大的结撞进[npc2.herHim]"+orificeName+"内的冲动。");
										}
									}
									
								} else {
									if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
										genericOrgasmSB.append("[npc.name]向前挺身，把[npc.cock+]根部膨大的结猛塞进[npc2.namePos]"+orificeNamePlusDescriptor+"。"
												+ "它早已肿胀过头，[npc.she]初次探进时没能成功操进里面，"
													+ "[npc.she]悻悻缩回下体，又猛地挺腰，[npc.hips]向前，努力撑开[npc2.her]"+orificeNamePlusDescriptor+"，粗大的结没入深处。");
									} else {
										genericOrgasmSB.append("[npc.she]不想被锁在[npc2.namePos]体内，于是抑制住了把正在迅速膨大的结撞进[npc2.herHim]"+orificeName+"内的冲动。");
									}
								}
								
							} else {
								if(cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE && isSecondaryCreampieTarget) { 
									genericOrgasmSB.append("把[npc.cock+]没入[npc2.namePos]"+orificeNamePlusDescriptor+"，"
											+ "[npc.name]发出[npc.a_moan+]，准备在[npc2.herHim]体内高潮。");
								} else {
									genericOrgasmSB.append("[npc.name]把[npc.cock+]埋进[npc2.namePos]"+orificeNamePlusDescriptor+"，边发出[npc.a_moan+]，边在[npc2.herHim]里面抽动。");
								}
							}
							
							modifiers.clear();
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Name]继续轻柔地操干着，倒刺扎入[npc2.namePos]"+orificeName+"的内壁"
													+(immobileTarget?"。":"让[npc2.herHim]发出了[npc2.a_moan+]。"));
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.isPlayer() && !sleeping) {
												modifiers.add("你感觉到自己宽大的平头[npc.cock]胀起来，封住了[npc2.her]"+orificeName+"深处你的[npc.cum]。");
											} else {
												modifiers.add("[npc.namePos]宽大的平头[npc.cock]胀起来，封住了[npc2.namePos]"+orificeName+"深处的[npc.cum]。");
											}
										}
										break;
									case RIBBED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.isPlayer() && !sleeping) {
												modifiers.add("你感到自己有螺纹的[npc.cock]在[npc2.name]"+orificeName+"的包裹下有力地泵动"+penisModPostfix);
											} else {
												modifiers.add("[npc.namePos]的[npc.cock]螺纹刮蹭着[npc2.namePos]"+orificeName+"的内壁"+penisModPostfix);
											}
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.isPlayer() && !sleeping) {
												modifiers.add("你感到[npc.cock]外侧扭曲蠕动的触手肆意揉弄着[npc2.her]的"+orificeName+penisModPostfix);
											} else {
												modifiers.add("[npc.namePos][npc.cock]外侧扭曲蠕动的触手肆意揉弄着[npc2.namePos]的"+orificeName+penisModPostfix);
												
											}
										}
										break;
									case BLUNT:
									case KNOTTED:
									case PREHENSILE:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							
							if(!modifiers.isEmpty()) {
								genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
							}
							
							if(!immobile && characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
								if(cumTarget!=OrgasmCumTarget.INSIDE_SWITCH_DOUBLE || isSecondaryCreampieTarget) {
									if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
										genericOrgasmSB.append("[npc.hips]与[npc2.namePos]的"+orificeName+"紧密贴合，[npc.name]发出[npc.a_moan+]，结完全充血胀开，不能再大。" );
										if(immobileTarget) {
											genericOrgasmSB.append("[npc.She]又蹭出一点，拖着[npc2.name]走；");
										} else {
											genericOrgasmSB.append("[npc.She]又蹭出一点，[npc2.name]被拖走，惊叫出声；");
										}
										genericOrgasmSB.append("显而易见，[npc.cock]正牢牢锁在[npc2.her]那"+orificeNamePlusDescriptor+"内。");
									}
								}
							}
							break;
	
						case ARMPITS:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append("[npc.name]还在睡，[npc.cock+]夹在[npc2.namePos]的腋窝间。");
								} else {
									genericOrgasmSB.append("[npc.name]仍然完全一动不动，[npc.cock+]夹在[npc2.namePos]腋窝间。");
								}
							} else {
								genericOrgasmSB.append("[npc.Name][npc.cock+]在[npc2.namePos]的腋窝内打转，[npc.she]感受着它的抽搐，发出[npc.a_moan+]。");
							}
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]的动作更让[npc.cock]两侧的倒刺扎入[npc2.namePos][npc2.arm+]"+penisModPostfix);
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("平平的顶部肿胀起来，淫靡地刮蹭着[npc2.namePos]的[npc2.arm]"+penisModPostfix);
										}
										break;
									case KNOTTED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]的肥结肿胀起来，每下顶入，都会撞到[npc2.namePos][npc2.arm+]"+penisModPostfix);
										}
										break;
									case PREHENSILE:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Name]利用自己灵活控制肉棒的能力，每次插入都会伸到[npc2.namePos][npc2.arm+]附近"+penisModPostfix);
										}
										break;
									case RIBBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]有螺纹的柱身每下塞入，都会顶到[npc2.namePos][npc2.arm+]"+penisModPostfix);
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("柱身上的小触手扭动着，按摩[npc2.namePos][npc2.arm+]"+penisModPostfix);
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							break;
							
						case ASS:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append("[npc.name]还在睡，[npc.cock+]夹在[npc2.namePos][npc2.assSize]的屁股蛋间。");
								} else {
									genericOrgasmSB.append("[npc.name]仍然完全一动不动，[npc.cock+]夹在[npc2.namePos][npc2.assSize]的屁股蛋间。");
								}
							} else {
								genericOrgasmSB.append("[npc.Name][npc.cock+]继续在[npc2.namePos][npc2.assSize]的屁股蛋间操弄，[npc.she]发出[npc.a_moan+]，感受着肉棒的颤动。");
							}
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]的动作更让[npc.cock]两侧的倒刺扎入[npc2.namePos][npc2.ass]"+penisModPostfix);
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("平平的顶部肿胀起来，将[npc2.namePos]的细缝操得更宽"+penisModPostfix);
										}
										break;
									case KNOTTED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]的肥结肿胀起来，每下顶入，都会扩张[npc2.namePos]的[npc2.asshole]"+penisModPostfix);
										}
										break;
									case PREHENSILE:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Name]利用自己灵活控制肉棒的能力，每次插入都会伸到[npc2.namePos]后背附近"+penisModPostfix);
										}
										break;
									case RIBBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]有螺纹的柱身每下塞入，都会顶到[npc2.namePos]的[npc2.asshole]"+penisModPostfix);
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("柱身上的小触手扭动着，按摩[npc2.namePos][npc2.asshole]"+penisModPostfix);
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							break;
							
						case BREAST:
							if(characterTargeted.hasBreasts()) {
								if(immobile) {
									if(sleeping) {
										genericOrgasmSB.append("[npc2.name]还在睡，用[npc2.breasts+]夹着[npc.her][npc.cock+]。");
									} else {
										genericOrgasmSB.append("[npc2.name]一动不动，保持[npc2.breasts+]夹住[npc.Name][npc.cock+]的姿势。");
									}
								} else {
									genericOrgasmSB.append("[npc.Name][npc.cock+]继续在[npc2.namePos][npc2.breasts+]间操弄，[npc.she]发出[npc.a_moan+]，感受着肉棒的颤动。");
								}
								
								for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
									switch(mod) {
										case BARBED:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add("[npc.Her]的动作更让[npc.cock]两侧的倒刺扎入[npc2.namePos]胸部"+penisModPostfix);
											}
											break;
										case FLARED:
											if(characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add("平平的顶部肿胀起来，淫靡地刮蹭着[npc2.namePos]的[npc2.breasts+]"+penisModPostfix);
											}
											break;
										case KNOTTED:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add("[npc.Her]的肥结肿胀起来，每下顶入，都会扩开些[npc2.namePos][npc2.breasts+]"+penisModPostfix);
											}
											break;
										case PREHENSILE:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add("[npc.Name]利用自己灵活控制肉棒的能力，将它弯曲，交替抚触着[npc2.namePos]胸部两侧"+penisModPostfix);
											}
											break;
										case RIBBED:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add("[npc.Her]有螺纹的柱身每下塞入，都会顶到[npc2.namePos][npc2.breasts+]"+penisModPostfix);
											}
											break;
										case TENTACLED:
											if(characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add("柱身上的小触手扭动着，按摩[npc2.namePos][npc2.breasts+]"+penisModPostfix);
											}
											break;
										case BLUNT:
										case SHEATHED:
										case TAPERED:
										case VEINY:
										case OVIPOSITOR:
											break;
									}
								}
								
							} else {
								if(immobile) {
									if(sleeping) {
										genericOrgasmSB.append("[npc.name]还在睡，[npc.cock+]歇在[npc2.namePos]的平胸上。");
									} else {
										genericOrgasmSB.append("[npc.name]仍然完全一动不动，[npc.cock+]歇在[npc2.namePos]的平胸上。");
									}
								} else {
									genericOrgasmSB.append("[npc.Name][npc.cock+]在[npc2.namePos]的平胸上打转，[npc.she]感受着它的抽搐，发出[npc.a_moan+]。");
								}
								
								for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
									switch(mod) {
										case BARBED:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add("[npc.Her]的动作更让[npc.cock]上面的倒刺尖利地扎入[npc2.namePos]的身体"+penisModPostfix);
											}
											break;
										case FLARED:
											if(characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add("平平的顶部肿胀起来，淫靡地刮蹭着[npc2.namePos]的身体"+penisModPostfix);
											}
											break;
										case KNOTTED:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add("[npc.Her]的肥结肿胀起来，每下顶入，都会撞到[npc2.namePos]的身体"+penisModPostfix);
											}
											break;
										case PREHENSILE:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add("[npc.Name]利用自己灵活控制肉棒的能力，弯曲它来蹭[npc2.namePos]的胸部"
														+penisModPostfix);
											}
											break;
										case RIBBED:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add("[npc.Her]有螺纹的柱身每下塞入，都会顶到[npc2.namePos]的身体"+penisModPostfix);
											}
											break;
										case TENTACLED:
											if(characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add("柱身上的小触手扭动着，按摩[npc2.namePos]的身体"+penisModPostfix);
											}
											break;
										case BLUNT:
										case SHEATHED:
										case TAPERED:
										case VEINY:
										case OVIPOSITOR:
											break;
									}
								}
							}
							break;
							
						case BREAST_CROTCH:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append("[npc.name]还在睡，[npc.cock+]夹在[npc2.namePos][npc2.crotchBoobs+]间。");
								} else {
									genericOrgasmSB.append("[npc.name]仍然完全一动不动，[npc.cock+]夹在[npc2.namePos][npc2.crotchBoobs+]间。");
								}
							} else {
								genericOrgasmSB.append("[npc.Name][npc.cock+]在[npc2.namePos][npc2.crotchBoobs+]间打转，[npc.she]感受着它的抽搐，发出[npc.a_moan+]。");
							}
							
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]的动作更让[npc.cock]两侧的倒刺扎入[npc2.namePos][npc2.crotchBoobs]"+penisModPostfix);
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("平平的顶部肿胀起来，淫靡地刮蹭着[npc2.namePos]的[npc2.crotchBoobs+]"+penisModPostfix);
										}
										break;
									case KNOTTED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]的肥结肿胀起来，每下顶入，都会扩开些[npc2.namePos][npc2.crotchBoobs+]"+penisModPostfix);
										}
										break;
									case PREHENSILE:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Name]利用自己灵活控制肉棒的能力，将它弯曲，交替抚触着[npc2.namePos][npc2.crotchBoobs]"+penisModPostfix);
										}
										break;
									case RIBBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]有螺纹的柱身每下塞入，都会顶到[npc2.namePos]的[npc2.crotchBoobs+]"+penisModPostfix);
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("柱身上的小触手扭动着，按摩[npc2.namePos]的[npc2.crotchBoobs+]"+penisModPostfix);
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							break;
							
						case MOUTH:
							GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(characterOrgasming);
							if(!Main.sex.getCreampieLockedBy().containsKey(characterOrgasming) && !characterTargeted.equals(primary)) {
								if(immobile) { // Doesn't make sense if characterTargeted is resisting...
									if(sleeping) {
										genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
												" [npc.NamePos]的[npc.cock+]在最后一刻从[npc3.namePos]喉咙里滑出，"
														+ "[npc2.name]俯身，动作很小地开始享受[npc.she]的小嘴，没有惊动[npc.she]。"));
									} else {
										genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
												"[npc.her]感觉精关一紧，把[npc.cock+]从[npc3.name]喉咙里拔出，"
														+ "[npc2.name]俯身插进[npc.she]的小嘴，对毫无反应的[npc.she]为所欲为。"));
									}
									
								} else {
									genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
											"[npc.Name]才不想让[npc3.namePos]第一个高潮，便把[npc.cock+]从[npc3.herHim]喉咙里抽出来了。"));
											
									if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
										if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
											genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
													"[npc.cockHead+]正对着[npc2.namePos]的嘴，向前突刺"
														+ "[npc.cock+]直顶入[npc2.namePos]嗓子眼，快速膨胀的结顺带冲撞着[npc2.lips+]。"
													+ "它早已肿胀过头，[npc.she]初次探进时没能成功操进[npc2.mouth]里，"
														+ "[npc.she]悻悻缩回下体，又猛地挺腰，[npc.hips]向前挺进，成功把充血肿大的结填进[npc2.her][npc2.lips]。"
													+ "<br/>"
													+ "[npc.name]感觉到它轻而易举地操入，便发出[npc.a_moan+]，下体紧贴在[npc2.namePos][npc2.face+]上，"
														+ "锁结完全胀开，将[npc.cock+]牢牢锁在[npc2.namePos]嗓子眼里。"));
											
										} else {
											if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
												genericOrgasmSB.append(UtilText.parse(characterOrgasming, characterTargeted,
														"[npc.cockHead+]正对上[npc2.namePos]的嘴，[npc.she]挺进向前，直到快速膨胀的结撞上[npc2.her][npc2.lips+]。"
														+ "[npc.Name]不想被锁在[npc2.her]的喉咙里，控制自己没把充血膨大的结填进[npc2.lips]。"));
												
											} else if(characterOrgasming.isWantingToFullyPenetrate(characterTargeted, SexAreaPenetration.PENIS)) {
												genericOrgasmSB.append("[npc.cockHead+]正对上[npc2.namePos]的嘴，[npc.she]挺身向前，"
														+ "却只感觉到[npc2.her]的喉咙不够深，吞不进整根[npc.cock+]，快速膨大的结没能塞进[npc2.her]嘴里。");
												
											} else {
												genericOrgasmSB.append("[npc.cockHead+]正对上[npc2.namePos]的嘴，[npc.she]挺身向前，"
														+ "但[npc.she]不想让[npc2.herHim]不舒服，于是忍住了，没把整根[npc.cock]捅进[npc2.her]喉咙里。");
											}
										}
										
									} else {
										if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
											genericOrgasmSB.append("[npc.cockHead+]正对上[npc2.namePos]的嘴，[npc.she]一顶身子，[npc.cock+]尽根没入，深深顶进[npc2.her]的喉咙。"
													+ "根部蹭着[npc2.her]的[npc2.lips]，[npc.Name]发出[npc.a_moan+]，感受着[npc.cock+]在[npc2.herHim]体内颤动。");
										} else {
											genericOrgasmSB.append("[npc.cockHead+]正对上[npc2.namePos]的嘴，[npc.she]一顶身子，颤动的[npc.cock]没入[npc2.namePos]喉咙里。");
										}
									}
								}
								
							} else {
								if(immobile) {
									if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
										genericOrgasmSB.append("[npc.her]更进一步，把[npc.cock+]连根塞入"+(sleeping?"熟睡的":"毫无反应的")+"[npc2.name]喉咙中。");
									} else {
										genericOrgasmSB.append("[npc.her]更进一步，把[npc.cock+]狠狠怼进"+(sleeping?"熟睡的":"毫无反应的")+"[npc2.name]喉咙中。");
									}
									
								} else {
									if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
										if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
											genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
													"[npc.Name]发出[npc.a_moan+]，[npc.cock+]猛干进[npc2.namePos]的喉咙，"
															+ "快速膨胀的结顺带冲撞着[npc2.her][npc2.lips+]。"
													+ "它早已肿胀过头，[npc.she]初次探进时没能成功操进[npc2.mouth]里，"
														+ "[npc.she]悻悻缩回下体，又猛地挺腰，[npc.hips]向前挺进，成功把充血肿大的结填进[npc2.her][npc2.lips]。"
													+ "<br/>"
													+ "[npc.name]感觉到它轻而易举地操入，便发出[npc.a_moan+]，下体紧贴在[npc2.namePos][npc2.face+]上，"
														+ "锁结完全胀开，将[npc.cock+]牢牢锁在[npc2.namePos]嗓子眼里。"));
											
										} else {
											if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
												genericOrgasmSB.append(UtilText.parse(characterOrgasming, characterTargeted,
														"[npc.Name]挺进着发出[npc.a_moan+]，迅速膨大的结顶起了[npc2.her][npc2.lips+]。"
														+ "[npc.Name]不想被锁在[npc2.her]的喉咙里，控制自己没把充血膨大的结填进[npc2.lips]。"));
												
											} else if(characterOrgasming.isWantingToFullyPenetrate(characterTargeted, SexAreaPenetration.PENIS)) {
												genericOrgasmSB.append("[npc.Name]发出[npc.a_moan+]，开始向前推进，"
														+ "却只感觉到[npc2.her]的喉咙不够深，吞不进整根[npc.cock+]，快速膨大的结没能塞进[npc2.her]嘴里。");
												
											} else {
												genericOrgasmSB.append("[npc.Name]发出[npc.a_moan+]，开始向前推进，"
														+ "但[npc.she]不想让[npc2.herHim]不舒服，于是忍住了，没把整根[npc.cock]捅进[npc2.her]喉咙里。");
											}
										}
											
									} else {
										if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
											genericOrgasmSB.append("[npc.cock+]向深处顶，尽根没入[npc2.namePos]的喉咙，[npc.Name]发出[npc.a_moan+]，感受着它在[npc2.herHim]体内的颤动。");
										} else {
											genericOrgasmSB.append("[npc.Name]把[npc.cock+]埋进[npc2.namePos]的喉咙，发出[npc.a_moan+]，感受着它在[npc2.herHim]体内颤动。");
										}
									}
								}
							}
							
							modifiers.clear();
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Name]继续轻柔地操干着，倒刺扎入[npc2.namePos]的喉咙"
													+ (immobileTarget?"。":"堵住了[npc2.herHim]气管，让她发出沉闷地[npc2.moan]。"));
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.isPlayer()) {
												modifiers.add("你感觉到自己宽大的平头[npc.cock]胀起来，把[npc.cum]封在了[npc2.her]嗓子眼深处。");
											} else {
												modifiers.add("[npc.namePos]宽大的平头[npc.cock]胀起来，把[npc.cum]封在了[npc2.namePos]嗓子眼深处。");
											}
										}
										break;
									case RIBBED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.isPlayer()) {
												modifiers.add("你用螺纹[npc.cock]在[npc2.her]喉咙里不停搅动"
														+ (immobileTarget?"，[npc2.she]还是没有做出任何反应。":"，令[npc2.herHim]发出沉闷的[npc2.moan]."));
											} else {
												modifiers.add("[npc.namePos]有螺纹的[npc.cock]在[npc2.namePos]嗓子眼里有力地泵动"
														+ (immobileTarget?"，[npc2.she]还是没有做出任何反应。":"，令[npc2.herHim]发出沉闷的[npc2.moan]。"));
											}
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.isPlayer()) {
												modifiers.add("你感觉[npc.cock]上蠕动的触手在[npc2.her]的咽喉中耸动"
														+ (immobileTarget?"，[npc2.she]还是没有做出任何反应。":"，令[npc2.herHim]发出沉闷的[npc2.moan]。"));
											} else {
												modifiers.add("[npc.namePos][npc.cock]上附生的触手不断按压刺激着[npc2.her]的咽喉内壁"
														+ (immobileTarget?"，[npc2.she]还是没有做出任何反应。":"，令[npc2.herHim]发出沉闷的[npc2.moan]。"));
											}
										}
										break;
									case BLUNT:
									case KNOTTED:
									case PREHENSILE:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							
							if(!modifiers.isEmpty()) {
								genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
							}
							break;
							
						case THIGHS:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append("[npc2.name]还在睡，用屁股夹着[npc.her][npc.cock+]。");
								} else {
									genericOrgasmSB.append("[npc2.name]一动不动，保持臀部夹住[npc.Name][npc.cock+]的姿势。");
								}
							} else {
								genericOrgasmSB.append("[npc.Name][npc.cock+]继续在[npc2.namePos]股间操弄，[npc.she]发出[npc.a_moan+]，肉棒颤动了起来。");
							}

							modifiers.clear();
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.cock]两侧的倒刺随着[npc.her]动作刮蹭着[npc2.namePos][npc2.legs+]"+penisModPostfix);
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("平坦的龟头更加膨胀，将[npc2.legs+]挤得更开"+penisModPostfix);
										}
										break;
									case KNOTTED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]的肥结肿胀起来，每下顶入，都会撞到[npc2.namePos][npc2.legs+]"+penisModPostfix);
										}
										break;
									case PREHENSILE:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Name]利用自己灵活控制肉棒的能力，每次插入都会伸到[npc2.namePos][npc2.legs+]附近"+penisModPostfix);
										}
										break;
									case RIBBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]有螺纹的柱身每下塞入，都会顶到[npc2.namePos]的[npc2.legs+]"+penisModPostfix);
										}
										break;
									case TENTACLED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("柱身上的小触手扭动着，按摩[npc2.namePos]的[npc2.legs+]"+penisModPostfix);
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							if(!modifiers.isEmpty()) {
								genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
							}
							break;
					}
					
				} else {
					switch((SexAreaPenetration)contactingArea) {
						case CLIT:
							break;
						case FINGER:
							if(characterOrgasming.equals(characterTargeted)) {
								if(immobile) {
									if(sleeping) {
										genericOrgasmSB.append("[npc.Name]还在睡，而且用[npc.her][npc.hand]握着[npc.her][npc.cock+]。");
									} else {
										genericOrgasmSB.append("[npc.Name]继续一动不动，而且用[npc.her][npc.hand]握着[npc.her][npc.cock+]，完美演绎着一个无生命的性爱玩偶。");
									}
								} else {
									genericOrgasmSB.append("[npc.Name][npc.cock+]在[npc.her]的手中抽插，[npc.she]感受着它的抽搐，发出[npc.a_moan+]。");
								}
							} else {
								genericOrgasmSB.append("[npc.Name][npc.cock+]在[npc2.namePos]的[npc2.hand]里抽插，[npc.she]感受着它的抽搐，发出[npc.a_moan+]。");
							}

							modifiers.clear();
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.equals(characterTargeted)) {
												modifiers.add("[npc.Her]的动作更让[npc.cock]两侧的倒刺扎入[npc.namePos][npc.fingers+]，引得[npc.herHim]发出[npc.a_moan+]。");
											} else {
												modifiers.add("[npc.Her]的动作更让[npc.cock]两侧的倒刺扎入[npc2.namePos][npc2.fingers+]"+penisModPostfix);
											}
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.equals(characterTargeted)) {
												modifiers.add("平平的顶部肿胀起来，将[npc.fingers+]挤得更开"
														+(immobile
															?"环绕着[npc.her][npc.cock+]。"
															:"，却反而使[npc.herHim]发出了[npc.a_moan+]。"));
											} else {
												modifiers.add("平平的顶部肿胀起来，将[npc2.fingers+]挤得更开"+penisModPostfix);
											}
										}
										break;
									case KNOTTED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.equals(characterTargeted)) {
												modifiers.add("[npc.Her]的肥结肿胀起来，每下顶入，都狂撞着自己[npc.fingers+]，引得[npc.herHim]连连发出[npc.a_moan+]。");
											} else {
												modifiers.add("[npc.Her]的肥结肿胀起来，每下顶入，都会撞到[npc2.namePos]的[npc2.fingers+]"+penisModPostfix);
											}
										}
										break;
									case PREHENSILE:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.equals(characterTargeted)) {
												modifiers.add("[npc.Name]利用自己灵活控制肉棒的能力，每次插入都会伸到自己[npc.fingers+]附近，"
														+ "却反而使[npc.herHim]发出了[npc.a_moan+]。");
											} else {
												modifiers.add("[npc.Name]利用自己灵活控制肉棒的能力，每次插入都会伸到[npc2.namePos][npc2.fingers+]附近"+penisModPostfix);
											}
										}
										break;
									case RIBBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.equals(characterTargeted)) {
												modifiers.add("[npc.Her]有螺纹的柱身每下塞入，都会顶到自己[npc.fingers+]，引得[npc.herHim]每次都发出[npc.a_moan+]。");
											} else {
												modifiers.add("[npc.Her]有螺纹的柱身每下塞入，都会顶到[npc2.namePos]的[npc2.fingers+]"+penisModPostfix);
											}
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.equals(characterTargeted)) {
												modifiers.add("柱身上的小触手扭动着，每次顶入都顺带按摩[npc.her][npc.fingers+]，"+(immobile?".":"引得[npc.herHim]每次都发出[npc.a_moan+]。"));
											} else {
												modifiers.add("柱身上的小触手扭动着，每次顶入都顺带按摩[npc2.namePos][npc2.fingers+]"+penisModPostfix);
											}
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							if(!modifiers.isEmpty()) {
								genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
							}
							break;
						case PENIS:
							genericOrgasmSB.append("[npc.Name]继续用[npc.cock+]磨蹭着[npc2.namePos][npc2.cock+]，[npc.she]发出[npc.a_moan+]，感受着它的颤动。");

							modifiers.clear();
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]的动作使得[npc.her][npc.cock]两侧的倒刺刮蹭着[npc2.namePos]的柱身"+penisModPostfix);
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]平平的顶部肿胀起来，刮蹭着[npc2.namePos]的柱身"+penisModPostfix);
										}
										break;
									case KNOTTED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]的肥结肿胀起来，每下顶入，都会撞到[npc2.namePos]的柱身"+penisModPostfix);
										}
										break;
									case PREHENSILE:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.name]利用自己灵活控制肉棒的能力，将它弯曲，环绕着[npc2.namePos]的柱身"+penisModPostfix);
										}
										break;
									case RIBBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.Her]有螺纹的柱身每下塞入，都会顶到[npc2.namePos]的柱身"+penisModPostfix);
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add("[npc.her]柱身上的小触手扭动着，按摩着[npc2.namePos]的柱身"+penisModPostfix);
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							if(!modifiers.isEmpty()) {
								genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
							}
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case FOOT://TODO modifiers
							if(immobile) {
								if(Main.sex.isDoubleFootJob(characterTargeted)) {
									genericOrgasmSB.append("[npc2.name]"+(sleeping?"还在睡":"一动不动")+", [npc.name]保持[npc.cock+]夹在[npc2.namePos][npc2.feet+]间的姿势。");
								} else {
									genericOrgasmSB.append("[npc2.name]"+(sleeping?"还在睡":"一动不动")+", [npc.name]保持[npc.cock+]压在[npc2.namePos][npc2.feet+]间的姿势。");
								}
							} else {
								if(Main.sex.isDoubleFootJob(characterTargeted)) {
									genericOrgasmSB.append("[npc.Name][npc.cock+]继续在[npc2.namePos][npc2.feet+]间操弄，[npc.she]发出[npc.a_moan+]，感受着肉棒的颤动。");
								} else {
									genericOrgasmSB.append("[npc.Name][npc.cock+]在[npc2.namePos][npc2.foot+]上磨蹭，[npc.she]感受到它的抽搐，发出[npc.a_moan+]。");
								}
							}
							break;
						case TONGUE:
							break;
					}
				}
			}
		}
		
		genericOrgasmSB.append("[npc.her][npc.balls+]骤然缩紧，");
		genericOrgasmSB.append(getCumQuantityDescription(characterOrgasming));
		if(characterOrgasming.getPenisRawOrgasmCumQuantity()>0) {
			genericOrgasmSB.append(cumTargetDescription(sexAction, characterOrgasming, characterTargeted, cumTarget, condomFailure, isSecondaryCreampieTarget));
		}
//		if(sexAction==GENERIC_ORGASM_OVIPOSITOR_PENIS_EGG_LAYING && characterOrgasming.equals(Main.sex.getCharacterLayingEggs())) {
//			genericOrgasmSB.append(eggLayingTargetDescription(SexAreaPenetration.PENIS, characterOrgasming, characterTargeted, condomFailure));
//		}
		
		
		if((cumTarget==OrgasmCumTarget.INSIDE || (cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE && isSecondaryCreampieTarget))
				&& characterTargeted!=null
				&& contactingArea.isOrifice()
				&& ((SexAreaOrifice)contactingArea).isInternalOrifice()
				&& characterOrgasming.getPenisRawOrgasmCumQuantity()>0
				&& (!characterOrgasming.isWearingCondom() || condomFailure!=CondomFailure.NONE)) {
			boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
			boolean immobileTarget = isCharacterTotallyImmobilised(characterTargeted);
			boolean sleepingTarget = characterTargeted.isAsleep();
			if(contactingArea==SexAreaOrifice.VAGINA
					&& !characterTargeted.isPregnant()
					&& characterTargeted.isAbleToBeImpregnated()
					&& characterOrgasming.isImpregnationPhysicallyPossible()
					&& characterOrgasming.isVirile(Attribute.VIRILITY)
					&& characterTargeted.isImpregnationPhysicallyPossible()
					&& characterTargeted.isFertile()
					&& !immobile) {
				if(immobileTarget) {
					if(sleepingTarget) {
						genericOrgasmSB.append("<br/>[npc.namePos][npc.cum+]射入了[npc2.namePos]子宫深处，[npc2.Name]仍然保持着沉睡。");
					} else {
						genericOrgasmSB.append("<br/>[npc2.name]纹丝不动，[npc2.she]清晰地感受到[npc.namePos][npc.cum+]灌进了子宫");
					}
				} else {
					if(Main.sex.getSexPace(characterTargeted)==SexPace.SUB_RESISTING || characterTargeted.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
						genericOrgasmSB.append("<br/>[npc2.Name]眼神涣散地啜泣，[npc2.she]清晰地感受到[npc.namePos][npc.cum+]灌进了子宫");
					} else {
						genericOrgasmSB.append("<br/>[npc2.Name][npc2.moansVerb+]，[npc2.she]清晰地感受到[npc.namePos][npc.cum+]灌进了子宫");
					}
				}
				if(!characterOrgasming.isMute() && characterOrgasming.getFetishDesire(Fetish.FETISH_IMPREGNATION).isPositive()) {
					if(immobileTarget) {
						genericOrgasmSB.append("， 沉默着就像[npc.name]一样 ");
					} else {
						if(Main.sex.getSexPace(characterTargeted)==SexPace.SUB_RESISTING || characterTargeted.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
							genericOrgasmSB.append("，发出哀羞懊恼的叫喊，与此同时[npc.Name]");
						} else {
							genericOrgasmSB.append("，发出低俗淫荡的呜咽，与此同时[npc.Name]");
						}
					}
					if(Main.sex.getSexPace(characterOrgasming)==SexPace.DOM_ROUGH
							&& characterOrgasming.hasFetish(Fetish.FETISH_SADIST)
							&& !Main.sex.isDom(characterTargeted)) {
						genericOrgasmSB.append(UtilText.returnStringAtRandom(
								"[npc.moansVerb]着说，[npc.speechNoEffects(准备怀孕吧，你这个[npc2.bitch+]！)]",
								"调戏道，[npc.speechNoEffects(准备好怀上我的崽了吗，[npc2.bitch]……)]",
								"调戏道，[npc.speechNoEffects(很快你就会怀上我的孩子了，[npc2.bitch]……)]",
								"挑逗道，[npc.speechNoEffects(我要干你干到怀孕，[npc2.bitch]……)]",
								"挑逗道，[npc.speechNoEffects(我要把你干到怀孕，[npc2.bitch]……)]"));
						
					} else {
						genericOrgasmSB.append(UtilText.returnStringAtRandom(
								"[npc.moansVerb]着说，[npc.speechNoEffects(怀孕吧！)]",
								"挑逗道，[npc.speechNoEffects(你这样子会怀孕的哦……)]",
								"挑逗道，[npc.speechNoEffects(你很快就会怀上我的种啦……)]",
								"挑逗道，[npc.speechNoEffects(我这样会把你操怀孕呢……)]",
								"挑逗道，[npc.speechNoEffects(你就要怀上我的宝宝了哦……)]"));
					}
				} else {
					genericOrgasmSB.append("。");
				}
				
			} else {
				if(immobileTarget) {
					if(sleepingTarget) {
						genericOrgasmSB.append("<br/>[npc.namePos][npc.cum+]射入了[npc2.namePos]体内，[npc2.name]仍然保持着沉睡。");
					} else {
						genericOrgasmSB.append("<br/>[npc2.name]纹丝不动，[npc2.she]清晰地感受到[npc.namePos][npc.cum+]深深灌注了[npc2.herHim]。");
					}
				} else {
					if(Main.sex.getSexPace(characterTargeted)==SexPace.SUB_RESISTING) {
						genericOrgasmSB.append("<br/>[npc2.Name]眼神涣散地啜泣，[npc2.she]清晰地感受到[npc.namePos][npc.cum+]深深灌注了[npc2.herHim]。");
					} else {
						genericOrgasmSB.append("<br/>[npc2.Name][npc2.moansVerb+]，[npc2.she]清晰地感受到[npc.namePos][npc.cum+]深深灌注了[npc2.herHim]。");
					}
				}
			}
		}
		
		if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)
				&& (cumTarget==OrgasmCumTarget.INSIDE || (cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE && isSecondaryCreampieTarget))
				&& Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)
				&& contactingArea.isOrifice()
				&& ((SexAreaOrifice)contactingArea).isInternalOrifice()) {
			genericOrgasmSB.append("<br/>"
					+ "哪怕[npc.namePos][npc.balls+]早已把储藏尽数射出，灌进[npc2.name]体内。结仍然膨大肿胀着，"
					+ "#IF(npc2.isPlayer() || npc.isPlayer())"
						+ "牢牢锁住，你们合二为一。"
					+ "#ELSE"
						+ "让[npc.herHim]牢牢锁住伴侣，融合在一起。"
					+ "#ENDIF"
					+ "几分钟后，它才开始小下来。“啵”的一声，[npc.her]终于拔出了[npc.cock+]。");
		}
		
		if(characterTargeted!=null) {
			return UtilText.parse(characterOrgasming, characterTargeted, genericOrgasmSB.toString());
		} else {
			return UtilText.parse(characterOrgasming, genericOrgasmSB.toString());
		}
	}
	
	private static String getCumQuantityDescription(GameCharacter characterOrgasming) {
		String targetName = "你";
		if(!characterOrgasming.isPlayer()) {
			targetName = "[npc.namePos]";
		}
		String cumQuantityDescription = targetName+"[npc.cum+]喷射而出";
		
		switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
			case ZERO_NONE:
				cumQuantityDescription = "哪怕一滴[npc.cum]都没有……";
				break;
			case ONE_TRICKLE:
				cumQuantityDescription = "勉强射出几滴[npc.cum+]";
				break;
			case TWO_SMALL_AMOUNT:
				cumQuantityDescription = "喷出一小股[npc.cum+]";
				break;
			case THREE_AVERAGE:
				cumQuantityDescription = targetName+"[npc.cum+]喷射而出";
				break;
			case FOUR_LARGE:
				cumQuantityDescription = targetName+"射出[npc.cum+]";
				break;
			case FIVE_HUGE:
				cumQuantityDescription = targetName+"射出[npc.cum+]";
				break;
			case SIX_EXTREME:
				cumQuantityDescription = targetName+"迸出[npc.cum+]";
				break;
			case SEVEN_MONSTROUS:
				cumQuantityDescription = targetName+"迸出[npc.cum+]";
				break;
		}
		return UtilText.parse(characterOrgasming, cumQuantityDescription);
	}
	
	private static String cumTargetDescription(SexActionInterface sexAction, GameCharacter characterOrgasming, GameCharacter target, OrgasmCumTarget targetArea, CondomFailure condomFailure, boolean isSecondaryCreampieTarget) {
		StringBuilder cumTargetSB = new StringBuilder();
		
		if(!isSecondaryCreampieTarget) {
			if(characterOrgasming.isWearingCondom()) {
				cumTargetSB.append(UtilText.parse(characterOrgasming, "，落在了避孕套里。"));
				
				switch(condomFailure) {
					case CUM_OVERLOAD:
						cumTargetSB.append(UtilText.parse(characterOrgasming,
								"事实证明，避孕套无法承受[npc.her]高潮的威力，在膨胀并充满[npc.her]大量[npc.cum+]后，它突然爆开，[npc.her]的精液漏了出来"));
						break;
					case MINERAL_OIL_CUM:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								"尽管这个避孕套本身的强度足以承受[npc.her]射出的精液，但[npc2.namePos]那矿物油质地的精液可以溶解橡胶材质，避孕套的耐久大打折扣"
										+ "在膨胀并充满[npc.namePos][npc.cum+]后，它突然爆开，[npc.her]的精液漏了出来"));
						break;
					case MINERAL_OIL_GIRLCUM:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								"尽管这个避孕套本身的强度足以承受[npc.her]射出的精液，但[npc2.namePos]那矿物油质地的爱液可以溶解橡胶材质，避孕套的耐久大打折扣"
										+ "在膨胀并充满[npc.namePos][npc.cum+]后，它突然爆开，[npc.her]的精液漏了出来"));
						break;
					case MINERAL_OIL_MILK:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								"尽管这个避孕套本身的强度足以承受[npc.her]射出的精液，但[npc2.namePos]那矿物油质地的乳汁可以溶解橡胶材质，避孕套的耐久大打折扣"
										+ "在膨胀并充满[npc.namePos][npc.cum+]后，它突然爆开，[npc.her]的精液漏了出来"));
						break;
					case MINERAL_OIL_SALIVA:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								"尽管这个避孕套本身的强度足以承受[npc.her]射出的精液，但[npc2.namePos]那矿物油质地的唾液可以溶解橡胶材质，避孕套的耐久大打折扣"
										+ "在膨胀并充满[npc.namePos][npc.cum+]后，它突然爆开，[npc.her]的精液漏了出来"));
						break;
					case MINERAL_OIL_SELF_CUM:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								"尽管这个避孕套本身的强度足以承受[npc.Name]射出的精液，但[npc.her]那矿物油质地的精液片刻间溶解了橡胶材质的避孕套，"
										+ "它突然爆开，里面容纳的[npc.her]的精液漏了出来"));
						break;
					case SABOTAGED:
						cumTargetSB.append(UtilText.parse(characterOrgasming,
								" 这个避孕套已经被暗中做了手脚，在膨胀并充满[npc.namePos][npc.cum+]后，它突然爆开，[npc.her]的精液漏了出来"));
						break;
					case NONE:
					case EGG_LAYING:
						return cumTargetSB.toString();
				}
				
			}
		}
		
		if(!characterOrgasming.isCoverableAreaExposed(CoverableArea.PENIS)) {
			if(characterOrgasming.isPlayer()) {
				return "进你的[npc.lowClothing(PENIS)]。";
			} else {
				return UtilText.parse(characterOrgasming, "进[npc.her]的[npc.lowClothing(PENIS)]。");
			}
		}

		
		List<CoverableArea> areasCummedOn = new ArrayList<>();
		if(target!=null) {
			areasCummedOn = sexAction.getAreasCummedOn(characterOrgasming, target);
		}
		
		List<AbstractClothing> targetAreaClothingCummedOn = new ArrayList<>();
		if(areasCummedOn!=null) {
			for(CoverableArea ca : areasCummedOn) {
				if(!target.isCoverableAreaExposed(ca)) {
					if(targetArea.isRequiresPartner()) {
						targetAreaClothingCummedOn.addAll(getClothingCummedOn(target, ca));
					} else {
						targetAreaClothingCummedOn.addAll(getClothingCummedOn(characterOrgasming, ca));
					}
				}
			}
			targetAreaClothingCummedOn = new ArrayList<>(new HashSet<>(targetAreaClothingCummedOn)); // Remove duplicates
		}
		
		boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
		boolean sleeping = characterOrgasming.isAsleep();
		
		switch(targetArea) {
			case ARMPITS:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("流过[npc2.namePos][npc2.armpit+]。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.namePos]的手臂上，");
						} else {
							sb.append("[npc.Name]沉默不语，任凭[npc.cum+]飞溅到[npc2.namePos]手臂上，");
						}
					} else {
						sb.append("[npc.Name]咧嘴一笑，[npc.cum+]飞溅到了[npc2.namePos]的手臂上，");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append("[npc2.name]依旧熟睡着，任由液体顺着[npc2.armSkin+]流下。");
						} else {
							sb.append("[npc2.Name]毫无反应，任精液顺着[npc2.armSkin+]流下手臂，");
						}
					} else {
						sb.append("[npc2.she]情不自禁发出[npc2.a_moan]，任精液顺着[npc2.armSkin+]流下手臂，");
					}
					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case ASS:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("流过[npc2.namePos][npc2.ass+]。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.namePos]裸露的臀部上，");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.namePos]裸露的臀部上，");
						}
					} else {
						sb.append("[npc.Name]咧嘴一笑，[npc.cum+]飞溅到了[npc2.namePos]裸露的臀部上，");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append("[npc2.she]仍然沉睡");
						} else {
							sb.append("[npc2.she]毫无反应");
						}
					} else {
						sb.append("[npc2.she]情难自抑发出[npc2.a_moan]，感受着液体");
					}
					if(target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
						sb.append("从[npc2.her]的[npc2.assSkin+]中缓缓流出。");
					} else if(target.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
						sb.append("从[npc2.her]的[npc2.assSkin+]和泄殖腔中流出。");
					} else {
						sb.append("从[npc2.her][npc2.asshole+]中缓缓渗出。");
					}
					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case BACK:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("流到了[npc2.namePos]背上。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，");
						}
					} else {
						sb.append("[npc.Name]咧嘴一笑，[npc.cum+]飞溅到了[npc2.name]身上，");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append("[npc2.she]仍然沉睡，任精液在[npc2.skin+]上流淌。");
						} else {
							sb.append("[npc2.she]毫无反应，任精液在[npc2.skin+]上流下");
						}
					} else {
						sb.append("随着精液从[npc2.skin+]上流下，[npc2.she]难以自禁发出[npc2.a_moan]。");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case BREASTS:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("弄得[npc2.namePos]的[npc2.breasts]上到处都是。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，");
						}
					} else {
						sb.append("[npc.Name]咧嘴一笑，[npc.cum+]飞溅到了[npc2.name]身上，");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append("[npc2.she]仍然沉睡，任精液在[npc2.breastsSkin+]上流淌。");
						} else {
							sb.append("[npc2.she]毫无反应，任精液在[npc2.breastsSkin+]上流淌。");
						}
					} else {
						sb.append("随着精液在[npc2.breastsSkin+]上流淌，[npc2.she]情难自禁发出[npc2.a_moan]。");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case FACE:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("流到了[npc2.namePos][npc2.face+]上。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，");
						}
					} else {
						sb.append("[npc.Name]咧嘴一笑，[npc.cum+]飞溅到了[npc2.name]身上，");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append("[npc2.she]仍然沉睡，任精液在[npc2.faceSkin+]上流淌。");
						} else {
							sb.append("[npc2.she]毫无反应，任精液在[npc2.faceSkin+]上流淌。");
						}
					} else {
						sb.append("随着精液在脸上流淌，[npc2.she]情不自禁发出[npc2.a_moan]。");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case FLOOR:
				return "遍地。";
			case STOMACH:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("流到[npc2.namePos]肚子上。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，");
						}
					} else {
						sb.append("[npc.Name]咧嘴一笑，[npc.cum+]飞溅到了[npc2.name]身上，");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append("[npc2.she]仍然沉睡，任精液在[npc2.skin+]上流淌。");
						} else {
							sb.append("[npc2.she]毫无反应，任精液在[npc2.skin+]上流下");
						}
					} else {
						sb.append("随着精液从[npc2.skin+]上流下，[npc2.she]难以自禁发出[npc2.a_moan]。");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case GROIN:
			case INSIDE:
			case INSIDE_SWITCH_DOUBLE:
				// Use this GROIN section only if the INSIDE or INSIDE_SWITCH_DOUBLE is a frotting event
				boolean isFrotting = false;
				if(targetArea==OrgasmCumTarget.INSIDE || targetArea==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE) {
					if(!Main.sex.getOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS, target).contains(SexAreaPenetration.PENIS)) {
						break;
					}
					isFrotting = true;
				}
				if(!isFrotting) { // If it's not frotting, set the target to the GROIN target, otherwise, keep it as-is as it will already be accounting for ongoing PENIS actions to make sure the target is the one being cummed inside of
					target = Main.sex.getTargetedPartner(characterOrgasming);
				}
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					String groinText = "腹股沟。";
					if(target.hasPenisIgnoreDildo()) {
						if(target.hasVagina()) {
							if(target.getGenitalArrangement()==GenitalArrangement.CLOACA || (isFrotting && target.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND)) {
								groinText = "[npc2.cock]，[npc2.pussy]和[npc2.asshole]。";
							} else if(target.getGenitalArrangement()==GenitalArrangement.NORMAL) {
								groinText = "[npc2.cock]和[npc2.pussy]。";
							}
						} else {
							if(target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								groinText = "[npc2.cock]和[npc2.asshole]。";
							} else if(target.getGenitalArrangement()==GenitalArrangement.NORMAL) {
								groinText = "[npc2.cock+]。";
							}
						}
						
					} else if(target.hasVagina()) {
						if(target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
							groinText = "[npc2.pussy]和[npc2.asshole]。";
						} else if(target.getGenitalArrangement()==GenitalArrangement.NORMAL) {
							groinText = "[npc2.pussy+]。";
						}
						
					} else {
						groinText = " 无性别的下体。";
					}

					StringBuilder sb = new StringBuilder();
					sb.append("溅满了[npc2.namePos]的"+groinText);
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，");
						}
					} else {
						sb.append("[npc.Name]咧嘴一笑，[npc.cum+]飞溅到了[npc2.name]身上，");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append("[npc2.she]仍然沉睡，任精液在腹股沟上流淌。");
						} else {
							sb.append("[npc2.she]毫无反应，任精液在腹股沟上流淌。");
						}
					} else {
						sb.append("随着精液在腹股沟上流淌，[npc2.she]难以自制发出[npc2.a_moan]。");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
				
			case HAIR:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					List<String> areas = new ArrayList<>();
					areas.add("头");
					if(target.hasHair()) {
						areas.add("[npc2.hair(true)]");
					}
					if(target.hasHorns()) {
						areas.add("[npc2.horns]");
					}

					StringBuilder sb = new StringBuilder();
					sb.append("溅满了[npc2.namePos]的"+Util.stringsToStringList(areas, false)+"。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，");
						}
					} else {
						sb.append("[npc.Name]咧嘴一笑，[npc.cum+]飞溅到了[npc2.name]身上，");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append("[npc2.she]仍然沉睡，任精液流过脸颊。");
						} else {
							sb.append("[npc2.she]毫无反应，任精液在脸上流淌。");
						}
					} else {
						sb.append("随着精液从脸上流下，[npc2.she]情难自禁发出[npc2.a_moan]。");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case LEGS:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("弄得[npc2.namePos][npc2.legs]上到处都是。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，");
						}
					} else {
						sb.append("[npc.Name]咧嘴一笑，[npc.cum+]飞溅到了[npc2.name]身上，");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append("[npc2.she]仍然沉睡，任精液在[npc2.legsSkin+]上流淌。");
						} else {
							sb.append("[npc2.she]毫无反应，任精液在[npc2.legsSkin+]上流淌。");
						}
					} else {
						sb.append("随着精液在[npc2.legsSkin+]上流淌，[npc2.she]情难自禁发出[npc2.a_moan]。");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case FEET:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("流得[npc2.namePos][npc2.feet+]上到处都是。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，");
						}
					} else {
						sb.append("[npc.Name]咧嘴一笑，[npc.cum+]飞溅到了[npc2.name]身上，");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append("[npc2.she]仍然沉睡，任精液在[npc2.toes+]上流淌。");
						} else {
							sb.append("[npc2.she]毫无反应，任精液在[npc2.toes+]上流淌。");
						}
					} else {
						sb.append("随着精液从[npc2.toes+]上流下，[npc2.she]情难自禁发出[npc2.a_moan]。");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case WALL:
				return "全在[pc.wall]上。";
				
			case SELF_GROIN:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
						
				} else {
					String groinText = "腹股沟。";
					if(characterOrgasming.hasPenisIgnoreDildo()) {
						if(characterOrgasming.hasVagina()) {
							groinText = "[npc.cock]和[npc.pussy]。";
						} else {
							groinText = "[npc.cock+]。";
						}
					} else if(characterOrgasming.hasVagina()) {
						groinText = "[npc.pussy+]。";
					} else {
						groinText = " 无性别的下体。";
					}

					StringBuilder sb = new StringBuilder();
					sb.append("流满了[npc.her]的"+groinText);
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，并且当它开始流过[npc.her][npc.skin]时，没有任何醒来的迹象。");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，并且当它开始流过[npc.her][npc.skin]时，没有任何反应。");
						}
					} else {
						sb.append("当[npc.Name][npc.cum+]飞溅到[npc.herHim]身上时，[npc.her]咧嘴一笑，当[npc.she]感觉到[npc.her][npc.skin]的流动时，[npc.she]情不自禁地发出[npc.a_moan]。");
					}
					return UtilText.parse(characterOrgasming, sb.toString());
				}
				
			case SELF_STOMACH:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("流到[npc.namePos]肚子上。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，并且当它开始流过[npc.her][npc.skin]时，没有任何醒来的迹象。");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，并且当它开始流过[npc.her][npc.skin]时，没有任何反应。");
						}
					} else {
						sb.append("当[npc.Name][npc.cum+]飞溅到[npc.herHim]身上时，[npc.her]咧嘴一笑，当[npc.she]感觉到[npc.her][npc.skin]的流动时，[npc.she]情不自禁地发出[npc.a_moan]。");
					}
					return UtilText.parse(characterOrgasming, sb.toString());
				}
				
			case SELF_LEGS:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("流到[npc.namePos][npc.legs]上。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，并且当它开始流过[npc.her][npc.skin]时，没有任何醒来的迹象。");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，并且当它开始流过[npc.her][npc.skin]时，没有任何反应。");
						}
					} else {
						sb.append("当[npc.Name][npc.cum+]飞溅到[npc.herHim]身上时，[npc.her]咧嘴一笑，当[npc.she]感觉到[npc.her][npc.skin]的流动时，[npc.she]情不自禁地发出[npc.a_moan]。");
					}
					return UtilText.parse(characterOrgasming, sb.toString());
				}

			case SELF_FEET:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("流满了[npc.her][npc.feet+]。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，并且当它开始流过[npc.her][npc.toes+]时，没有任何醒来的迹象。");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，并且当它开始流过[npc.her][npc.toes+]时，没有任何反应。");
						}
					} else {
						sb.append("当[npc.Name][npc.cum+]飞溅到[npc.herHim]身上时，[npc.her]咧嘴一笑，当[npc.she]感觉到[npc.her][npc.toes+]的流动时，[npc.she]情不自禁地发出[npc.a_moan]。");
					}
					return UtilText.parse(characterOrgasming, sb.toString());
				}
				
			case SELF_BREASTS:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("溅满了[npc.her]的[npc.breasts]。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，并且当它开始流过[npc.her][npc.breastsSkin+]时，没有任何醒来的迹象。");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，并且当它开始流过[npc.her][npc.breastsSkin+]时，没有任何反应。");
						}
					} else {
						sb.append("当[npc.Name][npc.cum+]飞溅到[npc.herHim]身上时，[npc.her]咧嘴一笑，当[npc.she]感觉到[npc.her][npc.breastsSkin+]的流动时，[npc.she]情不自禁地发出[npc.a_moan]。");
					}
					return UtilText.parse(characterOrgasming, sb.toString());
				}
			case SELF_FACE:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("流过[npc.namePos][npc.face+]。");
					if(immobile) {
						if(sleeping) {
							sb.append("[npc.Name]保持熟睡，[npc.cum+]飞溅到了[npc2.name]身上，并且当它开始流过[npc.her][npc.faceSkin+]时，没有任何醒来的迹象。");
						} else {
							sb.append("[npc.Name]保持沉默，[npc.cum+]飞溅到了[npc2.name]身上，并且当它开始流过[npc.her][npc.faceSkin+]时，没有任何反应。");
						}
					} else {
						sb.append("当[npc.Name][npc.cum+]飞溅到[npc.herHim]身上时，[npc.her]咧嘴一笑，当[npc.she]感觉到[npc.her][npc.faceSkin+]的流动时，[npc.she]情不自禁地发出[npc.a_moan]。");
					}
					return UtilText.parse(characterOrgasming, sb.toString());
				}

			case SELF_HANDS:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
				} else {
					return UtilText.parse(characterOrgasming,
							"弄得[npc.namePos][npc.hands]上到处都是。");
				}
				
			case LILAYA_PANTIES:
				LilayasRoom.lilayasPanties.setDirty(null, true);
				return UtilText.parse(characterOrgasming,
						"直射莉莱雅的内裤。"
						+ "你看着自己[pc.cum+]射在柔软的布料上，忍不住发出[pc.a_moan+]，"
							+ "你想像着你的恶魔[lilaya.relation(pc)]将这件沾满精液的内衣套在她火热的阴户上时脸红的样子，又套弄了几下你[pc.cock+]。");
		}
		
		// Continued description for cumming inside:
		
		List<SexAreaInterface> areasConteacted = Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS);
		if(!areasConteacted.isEmpty()) { // Do an empty check as when using a condom, ongoing actions are stopped (Sex.java#2721), then after that the sex action override is checked, which triggers this method again
			SexAreaInterface areaContacted = areasConteacted.get(0);
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
						if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							cumTargetSB.append("弄得[npc2.namePos][npc2.armpit+]和[npc2.arm+(true)]上到处都是。");
		
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append("几秒后，[npc2.name]就意识到[npc.nameIs]远远没打算停下来，只过了一小会，"
											+ "[npc2.her][npc2.arm+(true)]完全浸在[npc.cum+]中。");
									break;
								default:
									break;
							}
						}
						break;
						
					case ANUS:
						cumTargetSB.append("深深灌入[npc2.namePos][npc2.asshole+]。");
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append("[npc.nameIs]只休息了一下，就继续动了起来，");
								if(isCharacterTotallyImmobilised(target)) {
									if(target.isAsleep()) {
										cumTargetSB.append("在[npc2.namePos]的[npc2.asshole]中把[npc.cum+]尽数射出，没有弄醒[npc2.name]。");
									} else {
										cumTargetSB.append("在[npc2.namePos]的[npc2.asshole]中把[npc.cum+]尽数射出，而[npc2.name]对此毫无反应。");
									}
								} else {
									cumTargetSB.append("在[npc2.namePos]的[npc2.asshole]中把[npc.cum+]尽数射出，搞得[npc2.name][npc2.a_moan]不断。");
								}
								
								cumTargetSB.append(((targetArea!=OrgasmCumTarget.INSIDE_SWITCH_DOUBLE || isSecondaryCreampieTarget) && !immobile
														?"[npc.Name]让[npc.her]的[npc.cock]深深地插入了[npc2.her]的屁股，[npc.moaning+]着，等待[npc.her]的[npc.balls]彻底射空。"
														:""));
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
							float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS)
									+ (targetArea==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE
										?characterOrgasming.getPenisRawOrgasmCumQuantity()/2
										:characterOrgasming.getPenisRawOrgasmCumQuantity());
							cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case ASS:
						if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							cumTargetSB.append("弄得[npc2.namePos]后背和[npc2.ass+]上到处都是。");
	
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append("[npc.nameIs]只休息了一下，就继续动了起来，在[npc2.namePos][npc2.ass+]上射出了厚厚一层[npc.cum+]。");
									break;
								default:
									break;
							}
						}
						break;
						
					case BREAST:
						if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							if(target.hasBreasts()) {
								cumTargetSB.append("弄得[npc2.namePos][npc2.breasts+]和脸上到处都是。");
							} else {
								cumTargetSB.append("弄得[npc2.namePos]的平胸和脸上到处都是。");
							}
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append("[npc.nameIs]只休息了一下，就继续动了起来，在[npc2.namePos]躯干上射出了厚厚一层[npc.cum+]。");
									break;
								default:
									break;
							}
						}
						break;
						
					case BREAST_CROTCH:
						if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							cumTargetSB.append("弄得[npc2.namePos][npc2.crotchBoobs+]和腹股沟上到处都是。");
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append("几秒后，很明显[npc.nameIs]远远没打算停下来，只过了一小会，[npc2.namePos]的胯和肚子都完全浸在[npc.cum+]中。");
									break;
								default:
									break;
							}
						}
						break;
						
					case MOUTH:
						if(target.isPlayer()) {
							cumTargetSB.append("深深地插进你的喉咙，你意识到");
							if(isCharacterTotallyImmobilised(target)) {
								if(target.isAsleep()) {
									cumTargetSB.append("仍然呼呼大睡，任[npc.cum+]从肚子上淌下。");
								} else {
									cumTargetSB.append("仍然毫无反应，任[npc.cum+]从肚子上淌下。");
								}
							} else {
								cumTargetSB.append("伴随着[npc.cum+]从肚子上淌下，你压抑不住自己的情欲，发出出模糊的呜咽声。");
							}
							switch(characterOrgasming.getCumFlavour()) {
								case BEER:
									cumTargetSB.append("[npc.namePos]的味道是不常见的啤酒风味");
									break;
								case CHOCOLATE:
									cumTargetSB.append("[npc.namePos]是甜蜜的巧克力风味");
									break;
								case CUM:
									cumTargetSB.append("味道咸咸的");
									break;
								case GIRL_CUM:
									cumTargetSB.append("[npc.namePos]不寻常的甜味");
									break;
								case HONEY:
									cumTargetSB.append("[npc.namePos]甜蜜的蜂蜜风味");
									break;
								case MILK:
									cumTargetSB.append("[npc.namePos]的味道是不常见的牛奶风味");
									break;
								case MINT:
									cumTargetSB.append("[npc.namePos]薄荷风味");
									break;
								case PINEAPPLE:
									cumTargetSB.append("[npc.namePos]甜蜜的菠萝风味");
									break;
								case BUBBLEGUM:
									cumTargetSB.append("[npc.namePos]水果风味且起泡");
									break;
								case STRAWBERRY:
									cumTargetSB.append("[npc.namePos]草莓风味的甜蜜感");
									break;
								case VANILLA:
									cumTargetSB.append("[npc.namePos]香草风味的");
									break;
								case CHERRY:
									cumTargetSB.append("[npc.namePos]甜蜜的樱桃风味");
									break;
								case COFFEE:
									cumTargetSB.append("[npc.namePos]浓郁微苦的咖啡风味");
									break;
								case TEA:
									cumTargetSB.append("[npc.namePos]茶叶风味");
									break;
								case MAPLE:
									cumTargetSB.append("[npc.namePos]甜蜜的枫浆口味");
									break;
								case CINNAMON:
									cumTargetSB.append("[npc.namePos]肉桂风味");
									break;
								case LEMON:
									cumTargetSB.append("[npc.namePos]酸酸的柠檬风味");
									break;
								case ORANGE:
									cumTargetSB.append("[npc.namePos]柑橘香的甜橙风味");
									break;
								case GRAPE:
									cumTargetSB.append("[npc.namePos]葡萄风味");
									break;
								case MELON:
									cumTargetSB.append("[npc.namePos]蜜瓜风味");
									break;
								case COCONUT:
									cumTargetSB.append("[npc.namePos]可可风味");
									break;
								case BLUEBERRY:
									cumTargetSB.append("[npc.namePos]蓝莓风味");
									break;
								case BANANA:
									cumTargetSB.append("[npc.namePos]香蕉风味");
									break;
								case FLAVOURLESS:
									cumTargetSB.append("[npc.namePos]的精液完全无味，你");
									break;
							}
							if(characterOrgasming.getCumFlavour()!=FluidFlavour.FLAVOURLESS) {
								cumTargetSB.append("精液喷射在你的[npc2.tongue]上，你");
							}
							
							if(target.hasFetish(Fetish.FETISH_CUM_ADDICT) || Main.sex.getCharactersRequestingCreampie().contains(target) || Main.sex.getCharactersRequestingKnot().contains(target)) {
								cumTargetSB.append(" " + UtilText.returnStringAtRandom("贪婪地","饥渴地"));
							} else {
								cumTargetSB.append("别无选择，只能");
							}
							cumTargetSB.append("大口大口地尽可能喝下");
							if(!characterOrgasming.getCumModifiers().isEmpty()) {
								switch(characterOrgasming.getCumModifiers().get(Util.random.nextInt(characterOrgasming.getCumModifiers().size()))) { //TODO specials for ALCOHOLIC & HALLUCINOGENIC
									case ADDICTIVE:
										cumTargetSB.append("美味而让人上瘾的[npc.cum]。");
										break;
									case ALCOHOLIC:
									case ALCOHOLIC_WEAK:
										cumTargetSB.append("含酒精的[npc.cum]。");
										break;
									case BUBBLING:
										cumTargetSB.append("泡沫腾涌的[npc.cum]。");
										break;
									case HALLUCINOGENIC:
										cumTargetSB.append("致幻的[npc.cum]。");
										break;
									case MUSKY:
										cumTargetSB.append("雄性味道的[npc.cum]。");
										break;
									case SLIMY:
										cumTargetSB.append("粘滑的[npc.cum]。");
										break;
									case STICKY:
										cumTargetSB.append("粘稠的[npc.cum]。");
										break;
									case VISCOUS:
										cumTargetSB.append("浓厚粘稠的[npc.cum]。");
										break;
									case MINERAL_OIL:
										cumTargetSB.append("美味的[npc.cum]。");
										break;
								}
							} else {
								cumTargetSB.append("[npc.cum]。");
							}
							
						} else {
							cumTargetSB.append("深深灌入[npc2.namePos]的喉咙。");
						}
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append("[npc.nameIs]只休息了一下，就继续动了起来，");
								if(isCharacterTotallyImmobilised(target)) {
									if(target.isAsleep()) {
										cumTargetSB.append(" 而[npc2.name]还在熟睡");
									} else {
										cumTargetSB.append(" 而[npc2.name]毫无反应");
									}
								} else {
									cumTargetSB.append("发出狂乱而含糊的[npc2.moan]");
								}
								cumTargetSB.append("[npc.namePos][npc.cum+]尽数射出，从[npc2.her]的嘴角处满溢滴落。");
								if(!immobile) {
									cumTargetSB.append("[npc.Name]让[npc.her]的[npc.cock]深深地插入了[npc2.her]的喉咙，[npc.moaning+]着，等待[npc.her]的[npc.balls]彻底射空。");
								}
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
							float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case NIPPLE:
						if(target.isPlayer()) {
							cumTargetSB.append("深深地插进你[pc.breasts+]"+(isCharacterTotallyImmobilised(target)?".":"，你发现自己在低喘呻吟，感觉[npc.cum+]完全灌注进[pc.breasts+]。"));
						} else {
							cumTargetSB.append("深深灌入[npc2.namePos][npc2.breasts+]。");
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent)) {
							float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getBreastInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case NIPPLE_CROTCH:
						cumTargetSB.append("深深灌入[npc2.namePos][npc2.crotchBoobs+]。");
						
						if(Main.getProperties().hasValue(PropertyValue.inflationContent)) {
							float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE_CROTCH) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getBreastCrotchInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case THIGHS:
						if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							cumTargetSB.append("整个夹进[npc2.namePos]股间。");
							
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append("几秒后，很明显[npc.nameIs]远远没打算停下来，只过了一小会，"
											+ "[npc2.namePos][npc2.legs+]完全浸在[npc.cum+]中。");
									break;
								default:
							}
						}
						break;
						
					case URETHRA_PENIS: case URETHRA_VAGINA:
						if(target.isPlayer()) {
							cumTargetSB.append("深深顶入你的尿道。");
						} else {
							cumTargetSB.append("深深顶入[npc2.namePos]的尿道。");
						}
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append("几秒后，很明显[npc.nameIs]远远没打算停下来，只过了一小会，[npc.her][npc.cum+]尽数射出，从[npc2.her]吃不下的尿道里滴滴嗒嗒地淌出来。");
								if(!immobile) {
									cumTargetSB.append("[npc.Name]让[npc.cock]深深地插入了[npc2.herHim]，[npc.moaning+]着，等待[npc.balls]彻底射空。");
								}
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
							float cumAmount = target.getTotalFluidInArea((SexAreaOrifice) areaContacted) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case VAGINA:
						if(target.isPlayer()) {
							if(!target.isVisiblyPregnant()) {
								cumTargetSB.append("深深塞入你待插的子宫"+(isCharacterTotallyImmobilised(target)?".":"，你发现自己正呜咽呻吟，惊惧着[npc.cum+]会不会让你怀上宝宝。"));
							} else {
								cumTargetSB.append("深深顶进你饥渴的[pc.pussy]"+(isCharacterTotallyImmobilised(target)?".":"， 你发现自己正轻颤着呻吟，感觉[npc.cum+]灌注进体内。"));
							}
						} else {
							if(!target.isVisiblyPregnant()) {
								cumTargetSB.append("深深灌进[npc2.namePos]等待已久的子宫。");
							} else {
								cumTargetSB.append("深深灌入[npc2.namePos][npc2.pussy+]。");
							}
						}
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append("几秒后，很明显[npc.nameIs]远远没打算停下来，只过了一小会，[npc.her][npc.cum+]尽数射出，从[npc2.her][npc2.pussy+]里滴滴嗒嗒地淌出来。");
								cumTargetSB.append(((targetArea!=OrgasmCumTarget.INSIDE_SWITCH_DOUBLE || isSecondaryCreampieTarget) && !immobile
											?"[npc.Name]让[npc.cock]深深地插入了[npc2.her]的[npc2.pussy]，[npc.moaning+]着，等待[npc.balls]彻底射空。"
											:""));
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
							float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.VAGINA)
									+ (targetArea==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE
										?characterOrgasming.getPenisRawOrgasmCumQuantity()/2
										:characterOrgasming.getPenisRawOrgasmCumQuantity());
							cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
						}
						break;
	
					case SPINNERET:
						cumTargetSB.append("深深灌入[npc2.namePos]的丝囊穴。");
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append("几秒后，很明显[npc.nameIs]远远没打算停下来，只过了一小会，"
										+ "[npc.cum+]尽数射出，从[npc2.her]吃不下的丝囊穴里滴滴嗒嗒地淌出来。");
								if(!immobile) {
									cumTargetSB.append("[npc.Name]让[npc.cock]深深地插入了[npc2.herHim]，[npc.moaning+]着，等待[npc.balls]彻底射空。");
								}
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent)) {
							float cumAmount = target.getTotalFluidInArea((SexAreaOrifice) areaContacted) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getSpinneretInflationText(characterOrgasming, target, cumAmount));
						}
						break;
					case TUSSY:
						cumTargetSB.append(" deep into [npc2.namePos] [npc2.tussy].");
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append("几秒后，很明显[npc.nameIs]远远没打算停下来，只过了一小会，"
										+ " [npc.her] [npc.cum+] backs up and starts drooling out of [npc2.namePos] [npc2.tussy+].");
								if(!immobile) {
									cumTargetSB.append(" [npc.Name] [npc.verb(keep)] [npc.her] [npc.cock] hilted inside of [npc2.herHim], [npc.moaning+] as [npc.she] [npc.verb(wait)] for [npc.her] [npc.balls] to run dry.");
								}
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent)) {
							float cumAmount = target.getTotalFluidInArea((SexAreaOrifice) areaContacted) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getTussyInflationText(characterOrgasming, target, cumAmount));
						}
						break;
				}
	
				switch(target.getBodyMaterial()) {
					case AIR:
					case ARCANE:
					case WATER:
					case SLIME:
						cumTargetSB.append("<br/>"
								+ "由于[npc2.namePos]的身体由半透明的"+target.getBodyMaterial().getName()+"组成，"
										+ "你看到[npc.namePos][npc.cum+]在[npc2.herHim]身体里射出后扩散开来的精雾。");
						break;
					case FIRE:
					case FLESH:
					case ICE:
					case RUBBER:
					case STONE:
					case SILICONE:
						break;
				}
				
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
						break;
					case FINGER:
						if(characterOrgasming.equals(target)) {
							if (!targetAreaClothingCummedOn.isEmpty()) {
								return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
							} else {
								cumTargetSB.append("弄得[npc.her][npc.fingers+]上到处都是。");
								
								switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
									case SIX_EXTREME:
									case SEVEN_MONSTROUS:
										cumTargetSB.append("几秒后，[npc.her][npc.hands+]完全浸在[npc.cum+]里面。");
										break;
									default:
										break;
								}
							}
						} else if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							cumTargetSB.append("弄得[npc2.namePos][npc2.fingers+]上到处都是。");
							
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append("几秒后，很明显[npc.nameIs]远远没打算停下来，只过了一小会，"
											+ "[npc2.namePos][npc2.hands+]完全浸在[npc.cum+]中。");
									break;
								default:
									break;
							}
						}
						break;
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case FOOT:
						if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							cumTargetSB.append("弄得[npc2.namePos][npc2.toes+]上到处都是。");
							
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append(" After a few seconds, it becomes clear that [npc.nameIs] not even close to stopping, and after just a moment more,"
											+ "[npc2.namePos][npc2.feet+]完全浸在[npc.cum+]中。");
									break;
								default:
									break;
							}
						}
						break;
					case TONGUE:
						break;
				}
			}
		}
		
		if(target!=null) {
			return UtilText.parse(characterOrgasming, target, cumTargetSB.toString());
			
		} else {
			return UtilText.parse(characterOrgasming, cumTargetSB.toString());
		}
	}
	
	
	private static List<AbstractClothing> getClothingCummedOn(GameCharacter target, CoverableArea area) {
		List<AbstractClothing> clothingList = new ArrayList<>();
		for(InventorySlot slot : area.getAssociatedInventorySlots(target)) {
			clothingList.addAll(target.getVisibleClothingConcealingSlot(slot));
		}
		return new ArrayList<>(new HashSet<>(clothingList));
	}

	private static List<InventorySlot> getNakedAreasCummedOn(GameCharacter target, CoverableArea area) {
		Set<InventorySlot> areaList = new HashSet<>();
		List<InventorySlot> slotsTargeted = new ArrayList<>(area.getAssociatedInventorySlots(target));
		
		// Remove slots which don't make any sense to be cummed on:
		slotsTargeted.remove(InventorySlot.ANKLE); // This is covered by the SOCK slot
		slotsTargeted.remove(InventorySlot.TORSO_OVER); // This is covered by the TORSO_UNDER slot
		
		for(InventorySlot slot : slotsTargeted) {
			if(target.getVisibleClothingConcealingSlot(slot).isEmpty()) {
				areaList.add(slot);
			}
		}
		areaList.removeIf((covArea) -> !covArea.isPhysicallyAvailable(target));
		return new ArrayList<>(areaList);
	}
	
	private static String getClothingCummedOnText(GameCharacter characterOrgasming, GameCharacter target, List<CoverableArea> areas, List<AbstractClothing> clothing) {
		List<InventorySlot> nakedAreas = new ArrayList<>();
		for(CoverableArea area : areas) {
			nakedAreas.addAll(getNakedAreasCummedOn(target, area));
		}
		nakedAreas = new ArrayList<>(new HashSet<>(nakedAreas));
		
		boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
		StringBuilder sb = new StringBuilder();
		
		if(nakedAreas.isEmpty()) {
			sb.append("溅满了[npc2.namePos]的"+Util.clothesToStringList(clothing, false)+"。");
		} else {
			sb.append("遍布[npc2.namePos]的"+Util.clothesToStringList(clothing, false)+"以及[npc2.her]暴露的"+Util.inventorySlotsToParsedStringList(nakedAreas, target)+"。");
		}
		if(!immobile) {
			sb.append("[npc.Name]咧嘴一笑，[npc.cum+]飞溅到了[npc2.name]身上，把[npc2.her]的装束弄得一团糟。");
		}
		return UtilText.parse(characterOrgasming, target, sb.toString());
	}

	private static String getClothingCummedOnText(GameCharacter characterOrgasming, List<CoverableArea> areas, List<AbstractClothing> clothing) {
		List<InventorySlot> nakedAreas = new ArrayList<>();
		for(CoverableArea area : areas) {
			nakedAreas.addAll(getNakedAreasCummedOn(characterOrgasming, area));
		}
		nakedAreas = new ArrayList<>(new HashSet<>(nakedAreas));

		boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
		StringBuilder sb = new StringBuilder();
		
		if(nakedAreas.isEmpty()) {
			sb.append("溅满了[npc.her]的"+Util.clothesToStringList(clothing, false)+"。");
		} else {
			sb.append("遍布[npc.her]的"+Util.clothesToStringList(clothing, false)+"以及[npc.her]暴露的"+Util.inventorySlotsToParsedStringList(nakedAreas, characterOrgasming)+"。" );
		}

		if(!immobile) {
			sb.append("[npc.She]发出[npc.a_moan+]，[npc.cum+]飞溅到了[npc.her]的衣物上，把[npc.her]的装束弄得一团糟。");
		}
		return UtilText.parse(characterOrgasming, sb.toString());
	}
	
	private static String getInflationText(GameCharacter characterOrgasming, GameCharacter target, float cumAmount) {
		boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
		
		if(characterOrgasming.isPlayer()) {
			if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
				return ("<br/>你看到[npc2.namePos]的肚子因为你所注入的大量精液而膨胀到一个巨大到夸张的尺寸。"
						+ (!immobile?"把一只[npc.hand]放在[npc2.her]的大肚子上，你咧嘴一笑，心想[npc2.she]现在看起来就像怀胎十月了一样。":""));
				
			} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
				return ("<br/>你看到[npc2.namePos]的肚子因为你注入的大量精液而膨胀了起来。"
						+ (!immobile?"把一只[npc.hand]放在[npc2.her]的肚子上，你咧嘴一笑，心想[npc2.she]现在看起来就像怀孕了一样。":""));
				
			} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
				return ("<br/>你看到[npc2.namePos]的肚子因为你注入的精液而微微膨胀。"
						+ (!immobile?"把一只[npc.hand]放在[npc2.her]的肚子上，你咧嘴一笑，心想[npc2.she]现在看起来就像刚怀孕了一样。":""));
			}
		} else {
			if(target.isPlayer()) {
				if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
					return ("<br/>大量的精液注入你体内，你感觉自己的肚子膨胀到了一个巨大而夸张的尺寸。"
							+ (!immobile?"把一只[npc.hand]放在你的肚子上，[npc.name]咧嘴一笑，表示你现在看起来就像怀胎十月了一样。":""));
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
					return ("<br/>大量精液涌入体内，让你感到胃部有些胀痛。"
							+ (!immobile?"把一只[npc.hand]放在你的肚子上，[npc.name]咧嘴一笑，表示你现在看起来就像怀孕了一样。":""));
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
					return ("<br/>大量精液涌入体内，让你感到胃部有些膨大。"
							+ (!immobile?"把一只[npc.hand]放在你的肚子上，[npc.name]咧嘴一笑，表示你现在看起来就像刚怀孕了一样。":""));
				}
			} else {
				if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
					return ("<br/>[npc2.NamePos]的肚子因为[npc.namePos]所注入的大量精液而膨胀到一个巨大到夸张的尺寸。"
							+ (!immobile?"把一只[npc.hand]放在[npc2.her]的肚子上，[npc.race]咧嘴一笑，表示[npc2.name]现在看起来就像怀胎十月了一样。":""));
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
					return ("<br/>[npc2.NamePos]的肚子因为[npc.namePos]注入的大量精液而膨胀了起来。"
							+ (!immobile?"把一只[npc.hand]放在[npc2.her]的肚子上，[npc.race]咧嘴一笑，表示[npc2.name]现在看起来就像怀孕了一样。":""));
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
					return ("<br/>[npc2.NamePos]的肚子因为[npc.namePos]注入的精液而微微膨胀。"
							+ (!immobile?"把一只[npc.hand]放在[npc2.her]的肚子上，[npc.race]咧嘴一笑，表示[npc2.name]现在看起来就像刚怀孕了一样。":""));
				}
			}
		}
		return "";
	}
	
	private static String getSpinneretInflationText(GameCharacter characterOrgasming, GameCharacter target, float cumAmount) {
		if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
			return ("<br/>[npc2.NamePos]的"+(target.hasTailSpinneret()?"[npc2.tail]":"腹部")+"因为[npc.nameHas]在[npc2.her]的丝囊穴里注入的大量精液而膨胀到一个巨大到夸张的尺寸。");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
			return ("<br/>[npc2.NamePos]的"+(target.hasTailSpinneret()?"[npc2.tail]":"腹部")+"因为[npc.nameHas]在[npc2.her]的丝囊穴里注入的大量精液而膨胀了起来。");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
			return ("<br/>[npc2.NamePos]的"+(target.hasTailSpinneret()?"[npc2.tail]":"腹部")+"因为[npc.nameHas]在[npc2.her]的丝囊穴里注入的精液而微微膨胀。");
		}
		return "";
	}

	private static String getTussyInflationText(GameCharacter characterOrgasming, GameCharacter target, float cumAmount) {
		if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
			return ("<br/>[npc2.NamePos] "+(target.hasTussy()?"[npc2.tail]":"tip")+" swells out to a massive, over-inflated size as it distends from the sheer amount of cum that [npc.nameHas] pumped inside of [npc2.her] [npc2.tussy].");

		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
			return ("<br/>[npc2.NamePos] "+(target.hasTussy()?"[npc2.tail]":"tip")+" swells out as it distends from the huge amount of cum that [npc.nameHas] pumped inside of [npc2.her] [npc2.tussy].");

		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
			return ("<br/>[npc2.NamePos] "+(target.hasTussy()?"[npc2.tail]":"tip")+" swells out a little as it distends from the sheer amount of cum that [npc.nameHas] pumped inside of [npc2.her] [npc2.tussy].");
		}
		return "";
	}
	
	private static String getBreastInflationText(GameCharacter characterOrgasming, GameCharacter target, float cumAmount) {
		if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
			return ("<br/>[npc2.NamePos]的[npc2.breasts]因为[npc.nameHas]所注入的大量精液而膨胀到一个巨大到夸张的尺寸。");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
			return ("<br/>[npc2.NamePos]的[npc2.breasts]因为[npc.nameHas]注入的大量精液而膨胀了起来。");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
			return ("<br/>[npc2.NamePos]的[npc2.breasts]因为[npc.nameHas]注入的精液而微微膨胀。");
		}
		return "";
	}
	
	private static String getBreastCrotchInflationText(GameCharacter characterOrgasming, GameCharacter target, float cumAmount) {
		if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
			return ("<br/>[npc2.NamePos]的[npc2.crotchBoobs]因为[npc.nameHas]所注入的大量精液而膨胀到一个巨大到夸张的尺寸。");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
			return ("<br/>[npc2.NamePos]的[npc2.crotchBoobs]因为[npc.nameHas]注入的大量精液而膨胀了起来。");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
			return ("<br/>[npc2.NamePos]的[npc2.crotchBoobs]因为[npc.nameHas]注入的精液而微微膨胀。");
		}
		return "";
	}
	
	private static String getGenericVaginaOrgasmDescription(SexActionInterface sexAction, GameCharacter characterOrgasming, OrgasmCumTarget targetArea) {
		boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
		boolean sleeping = characterOrgasming.isAsleep();
		
		genericOrgasmSB.setLength(0);
		
		if(immobile) {
			genericOrgasmSB.append("渴望、战栗的热流冲向[npc.namePos][npc.pussy+]，");
			if(sleeping) {
				genericOrgasmSB.append("但即便如此，[npc.name]仍沉沉睡去，甚至连[npc.herHim]身上涌起的狂喜的浪潮都不足以唤醒[npc.herHim]。");
			} else {
				genericOrgasmSB.append("但即便如此，[npc.name]也完全保持沉默，一动不动，对[npc.herHim]身上涌起的狂喜浪潮毫无反应。");
			}
		} else {
			genericOrgasmSB.append("渴望、战栗的热流冲向[npc.namePos][npc.pussy+]，[npc.she]疯狂地尖叫，强烈、纯粹的快感冲过了[npc.herHim]。");
		}
		
		GameCharacter characterPenetrating = null;
		if(Main.sex.getCharacterOngoingSexArea(characterOrgasming, SexAreaOrifice.VAGINA).size()>0) {
			characterPenetrating = Main.sex.getCharacterOngoingSexArea(characterOrgasming, SexAreaOrifice.VAGINA).get(0);
		}
		SexAreaPenetration penetration = Main.sex.getFirstOngoingSexAreaPenetration(characterOrgasming, SexAreaOrifice.VAGINA);
		
		if(characterPenetrating!=null && penetration!=null) {
			boolean immobilePenetrator = isCharacterTotallyImmobilised(characterPenetrating);
			boolean sleepingPenetrator = characterPenetrating.isAsleep();
			boolean playerInvolved = characterOrgasming.isPlayer() || characterPenetrating.isPlayer();
					
			switch(penetration) {
				case FINGER:
					if(immobile) {
						if(characterOrgasming.equals(characterPenetrating)) {
							genericOrgasmSB.append("[npc.her]更进一步，把[npc.fingers]狠狠怼进"+(sleeping?"熟睡的":"毫无反应的")+"[npc2.name][npc.pussy+]中。");
						} else {
							if(immobilePenetrator) {
								genericOrgasmSB.append("[npc2.NamePos][npc2.fingers+]持续塞着[npc.namePos][npc.pussy+]在[npc.her]高潮时，伴随两个"
										+(playerInvolved?"你":"他")+"仍然"+(sleeping&&sleepingPenetrator?"沉睡":"一动不动")+"。");
							} else {
								genericOrgasmSB.append("[npc2.namePos][npc2.fingers+]持续操弄着[npc2.namePos]因为高潮而更加敏感的[npc.pussy+]，"
										+ "但在这样的刺激下，[npc.she]仍然"+(sleeping?"沉睡不醒":"一动不动")+"。");
							}
						}
					} else {
						if(characterOrgasming.isPlayer()) {
							if(characterPenetrating.isPlayer()) {
								genericOrgasmSB.append("你将手指在自己[npc.pussy+]内蜷曲，意乱情迷地抚弄着，不停勾起手指，"
										+ "阴道收缩挤压着你插入的手指，你发出一连串高亢的呻吟声。");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append("你压抑不住快感，发出兴奋的尖叫，阴道内的肌肉也不短收缩，紧紧吸住了其中插入的手指。");
								} else {
									genericOrgasmSB.append("[npc2.NamePos]的手指继续在你[npc.pussy+]里推来挤去，阴道内的肌肉忽然收缩，紧紧吸住了插入的手指，你也不住地发出高声的呻吟。");
								}
							}
						} else {
							if(characterOrgasming.equals(characterPenetrating)) {
								genericOrgasmSB.append("[npc.NamePos]阴道内的肌肉骤然紧缩，紧紧吸住[npc.her]插入的手指，"
										+ "在强烈快感的驱使下，[npc.she]继续抚摸挑逗着阴蒂，发出一连串[npc.moans+]。");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append("[npc2.NamePos]继续用手指操弄着[npc.namePos][npc.pussy+]，"
											+"genericOrgasmSB.append(操的[npc.her]压抑不住快感，发出兴奋的尖叫。而[npc.she]阴道内的肌肉也不短收缩，紧紧吸住了其中插入的手指。");
								} else {
									genericOrgasmSB.append("[npc.NamePos]阴道内的肌肉骤然紧缩，紧紧吸住[npc2.namePos]插入的手指，"
											+ "[npc2.she]继续磨蹭挑弄着阴蒂，[npc.her][npc.lips+]间更是[npc.moans+]不断。");
								}
							}
						}
					}
					break;
				case PENIS:
					if(immobile) {
						if(immobilePenetrator) {
							genericOrgasmSB.append("[npc2.NamePos][npc2.cock+]继续抽插着[npc.namePos]因为高潮更加敏感[npc.pussy+]，伴随两根"
									+(playerInvolved?"你":"他")+"仍然"+(sleeping&&sleepingPenetrator?"沉睡":"一动不动")+"。");
						} else {
							genericOrgasmSB.append("[npc2.Name]在[npc.namePos]高潮时持续操着[npc.her][npc.pussy+]，尽管有这样的刺激，[npc.she]仍"
									+(sleeping?"深度睡眠":"完全不动")+"。");
						}
						
					} else {
						if(characterOrgasming.isPlayer()) {
							if(characterPenetrating.isPlayer()) {
								genericOrgasmSB.append("你在高潮时继续操自己，阴道的肌肉紧紧包裹挤压你[npc.cock+]，引得你发出了一系列高亢的呻吟。");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append("[npc2.NamePos][npc2.cock+]在你高潮的时候依旧插在你[pc.pussy+]中，");
								} else {
									genericOrgasmSB.append("[npc2.Name]在你高潮时继续操着你[pc.pussy+]，");
								}
								genericOrgasmSB.append("你阴道的肌肉紧紧包裹挤压着[npc2.her][npc2.penis+]，引得你发出了一阵高亢的呻吟。");
							}
						} else {
							if(characterOrgasming.equals(characterPenetrating)) {
								genericOrgasmSB.append("[npc.Name]在[npc.her]高潮时继续操着自己[npc.pussy+]，"
										+ "[npc.her]的阴道收缩挤压着自己[npc.penis+]，让[npc.herHim]发出一连串高亢的呻吟声。");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append("[npc2.NamePos][npc2.cock+]在[npc.namePos]高潮的时候依旧插在[npc.her][npc.pussy+]中，");
								} else {
									genericOrgasmSB.append("[npc2.Name]在[npc.namePos]高潮时持续操着[npc.her][npc.pussy+]，");
								}
								genericOrgasmSB.append("[npc.her]的阴道收缩挤压着[npc2.her][npc2.penis+]，引得[npc.herHim]发出一连串高亢的呻吟声。");
							}
						}
					}
					break;
				case TAIL:
					if(immobile) {
						if(immobilePenetrator) {
							genericOrgasmSB.append("[npc2.NamePos][npc2.tail+]继续抽插着[npc.namePos]因为高潮更加敏感[npc.pussy+]，伴随两根"
									+(playerInvolved?"你":"他")+"仍然"+(sleeping&&sleepingPenetrator?"沉睡":"一动不动")+"。");
						} else {
							genericOrgasmSB.append("[npc2.NamePos]用尾巴继续抽插着[npc.namePos]因为高潮更加敏感[npc.pussy+]，不过[npc.she]还是"
									+(sleeping?"深度睡眠":"完全不动")+"。");
						}
						
					} else {
						if(characterOrgasming.isPlayer()) {
							if(characterPenetrating.isPlayer()) {
								genericOrgasmSB.append("你在高潮时继续尾交着自己，阴道的肌肉紧紧包裹挤压着侵入的异物，引得你发出了一连串高亢的呻吟。");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append("[npc2.NamePos][npc2.tail+]继续干着你因为高潮而更加敏感的[pc.pussy+]，");
								} else {
									genericOrgasmSB.append("[npc2.Name]用尾巴继续操着你因为高潮而更加敏感的[pc.pussy+]，");
								}
								genericOrgasmSB.append("随后阴道的肌肉紧紧包裹挤压着入侵的物体，你发出了一连串高亢的呻吟。");
							}
						} else {
							if(characterOrgasming.equals(characterPenetrating)) {
								genericOrgasmSB.append("[npc.Name]在[npc.her]高潮时继续尾交着自己[npc.pussy+]，"
										+ "[npc.her]的阴道收缩挤压着侵入的异物，让[npc.herHim]发出一连串高亢的呻吟声。");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append("[npc2.NamePos][npc2.tail+]继续操弄着[npc.namePos]因为高潮而更加敏感的[npc.pussy+]，");
								} else {
									genericOrgasmSB.append("[npc2.NamePos]用尾巴继续操干着[npc.namePos]因为高潮而更加敏感的[npc.pussy+]，");
								}
								genericOrgasmSB.append("[npc.her]的阴道收缩挤压着侵入的异物，让[npc.herHim]发出一连串高亢的呻吟声。");
							}
						}
					}
					break;
				case TONGUE:
					if(immobile) {
						if(immobilePenetrator) {
							genericOrgasmSB.append("[npc2.NamePos]的舌头继续在[npc.namePos]因高潮更加敏感[npc.pussy+]深处舔弄着，伴随两根"
									+(playerInvolved?"你":"他")+"仍然"+(sleeping&&sleepingPenetrator?"沉睡":"一动不动")+"。");
						} else {
							genericOrgasmSB.append("[npc2.name]用继续舔吻[npc.namePos]因高潮更加敏感的阴蒂，不过[npc.she]还是"
									+(sleeping?"深度睡眠":"完全不动")+"。");
						}
						
					} else {
						if(characterOrgasming.isPlayer()) {
							if(characterPenetrating.isPlayer()) {
								genericOrgasmSB.append("你在高潮时继续舔吻着自己的阴蒂，阴道也由于快感而颤抖收缩着，引得你发出了一连串高亢的呻吟。");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append("[npc2.NamePos]的舌头继续在你因高潮而更加敏感的[pc.pussy+]深处舔弄着，");
								} else {
									genericOrgasmSB.append("[npc2.name]在你高潮时继续舔吻着你的阴蒂，");
								}
								genericOrgasmSB.append("你的阴道由于快感而颤抖收缩着，让你发出了一连串高亢的呻吟。");
							}
						} else {
							if(characterOrgasming.equals(characterPenetrating)) {
								genericOrgasmSB.append("[npc.Name]在[npc.she]高潮时继续舔吻着自己的阴蒂，"
										+ "[npc.her]的阴道由于快感而颤抖收缩着，让[npc.herHim]发出了一连串高亢的呻吟。");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append("[npc2.NamePos]的舌头继续在[npc.namePos]因高潮而更加敏感的[npc.pussy+]深处舔弄着，");
								} else {
									genericOrgasmSB.append("[npc2.Name]在[npc.she]高潮时继续舔吻着[npc.namePos]的阴蒂，");
								}
								genericOrgasmSB.append("[npc.her]的阴道由于快感而颤抖收缩着，让[npc.herHim]发出了一连串高亢的呻吟。");
							}
						}
					}
					break;
				case TENTACLE:
					if(immobile) {
						if(immobilePenetrator) {
							genericOrgasmSB.append("[npc2.NamePos][npc2.tentacle+]玩弄着[npc.namePos]因高潮更加敏感[npc.pussy+]，伴随两根"
									+(playerInvolved?"你":"他")+"仍然"+(sleeping&&sleepingPenetrator?"沉睡":"一动不动")+"。");
						} else {
							genericOrgasmSB.append("[npc2.NamePos]用继续触手操干[npc.namePos]因高潮更加敏感[npc.pussy+]，不过[npc.she]还是"
									+(sleeping?"深度睡眠":"完全不动")+"。");
						}
						
					} else {
						if(characterOrgasming.isPlayer()) {
							if(characterPenetrating.isPlayer()) {
								genericOrgasmSB.append("你在高潮时继续触手交着自己，阴道的肌肉紧紧包裹挤压着侵入的异物，引得你发出了一连串高亢的呻吟。");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append("[npc2.NamePos][npc2.tentacle+]在你高潮的时候依旧插在你[pc.pussy+]中，");
								} else {
									genericOrgasmSB.append("[npc2.Name]在你高潮时继续触手交着你[pc.pussy+]，");
								}
								genericOrgasmSB.append("随后阴道的肌肉紧紧包裹挤压着入侵的物体，你发出了一连串高亢的呻吟。");
							}
						} else {
							if(characterOrgasming.equals(characterPenetrating)) {
								genericOrgasmSB.append("[npc.Name]在[npc.her]高潮时继续触手交自己[npc.pussy+]，"
										+ "[npc.her]的阴道收缩挤压着侵入的异物，让[npc.herHim]发出一连串高亢的呻吟声。");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append("[npc2.NamePos][npc2.tentacle+]在[npc.namePos]高潮的时候依旧插在[npc.her][npc.pussy+]中，");
								} else {
									genericOrgasmSB.append("[npc2.Name]在[npc.namePos]高潮时继续触手交着[npc.her][npc.pussy+]，");
								}
								genericOrgasmSB.append("[npc.her]的阴道收缩挤压着侵入的异物，让[npc.herHim]发出一连串高亢的呻吟声。");
							}
						}
					}
					break;
				case CLIT:
					if(immobile) {
						if(immobilePenetrator) {
							genericOrgasmSB.append("[npc2.NamePos][npc2.clit+]玩弄着[npc.namePos]因高潮更加敏感[npc.pussy+]，伴随两根"
									+(playerInvolved?"你":"他")+"仍然"+(sleeping&&sleepingPenetrator?"沉睡":"一动不动")+"。");
						} else {
							genericOrgasmSB.append("[npc2.NamePos]用继续阴蒂操干[npc.namePos]因高潮更加敏感[npc.pussy+]，不过[npc.she]仍然"
									+(sleeping?"深度睡眠":"完全不动")+"。");
						}
						
					} else {
						if(characterOrgasming.isPlayer()) {
							if(characterPenetrating.isPlayer()) {
								genericOrgasmSB.append("你在高潮时继续阴蒂自交着，阴道的肌肉紧紧包裹挤压你[npc.clit+]，让你发出了一连串高亢的呻吟。");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append("[npc2.NamePos][npc2.clit+]玩弄着你因高潮而更加敏感的[npc.pussy+]，");
								} else {
									genericOrgasmSB.append("[npc2.NamePos]用继续阴蒂操干着你因高潮而更加敏感的[npc.pussy+]，");
								}
								genericOrgasmSB.append("接着你因为快感娇喘连连，而阴道肌肉收缩，绞着[npc2.clit+]不松。");
							}
						} else {
							if(characterOrgasming.equals(characterPenetrating)) {
								genericOrgasmSB.append("[npc.Name]在[npc.her]高潮时继续阴蒂交着自己[npc.pussy+]，"
										+ "[npc.her]的阴道收缩挤压着自己[npc.clit+]，让[npc.herHim]发出一连串高亢的呻吟声。");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append("[npc2.NamePos][npc2.clit+]操着[npc.namePos]因为高潮而更加敏感的[npc.pussy+]，");
								} else {
									genericOrgasmSB.append("[npc2.NamePos]用继续阴蒂操干[npc.namePos]因高潮而更加敏感的[npc.pussy+]，");
								}
								genericOrgasmSB.append("操得[npc.her]快感不断，抑制不住兴奋地呻吟，阴道肌肉收缩，狠狠地绞住插入的[npc2.clit+]不松。");
							}
						}
					}
					break;
				case FOOT: //TODO
					break;
			}
			
		} else { // No penetration:
			boolean pluggedVagina = false;
			for(AbstractClothing c : characterOrgasming.getClothingCurrentlyEquipped()) {
				if(c.getItemTags().contains(ItemTag.PLUGS_VAGINA)) {
					pluggedVagina = true;
					if(immobile) {
						if(sleeping) {
							genericOrgasmSB.append("[npc.Name]保持着沉睡，阴道肌肉收缩挤压着插入[npc.pussy]的"+c.getName()+"。");
						} else {
							genericOrgasmSB.append("[npc.Name]一动不动，阴道肌肉收缩挤压着插入[npc.pussy]的"+c.getName()+"。");
						}
					} else {
						genericOrgasmSB.append("[npc.NamePos][npc.pussy+]用力收紧，"
								+ "[npc.her]的阴道收缩挤压着插入[npc.pussy]的"+c.getName()+"，引得[npc.herHim]发出一连串高亢的呻吟声。");
					}
					break;
				}
			}
			if(!pluggedVagina) {
				SexAreaPenetration analPenetrator = Main.sex.getFirstOngoingSexAreaPenetration(characterOrgasming, SexAreaOrifice.ANUS);
				Set<GameCharacter> tribbingPartners = Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT);
				
				if(characterOrgasming.getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()
						&& analPenetrator!=null
						&& analPenetrator.isTakesVirginity()) {
					GameCharacter characterPenetratingAss = Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.ANUS, analPenetrator).iterator().next();
					genericOrgasmSB.append(UtilText.parse(characterOrgasming, characterPenetratingAss,
							"[npc.NamePos][npc.pussy+]紧缩起来，尽管并没有被插入，但[npc.sheIs]依然心醉神迷。因为[npc.sheIs]"
								+(Main.sex.getSexPace(characterOrgasming)==SexPace.SUB_RESISTING?"主要关注":"热爱")
								+"这种[npc2.namePos]的"+analPenetrator.getName(characterPenetratingAss)+"在[npc.her]屁股里的感觉。"));
					
				} else if(tribbingPartners!=null && !tribbingPartners.isEmpty()) {
					GameCharacter tribbingPartner = tribbingPartners.iterator().next();
					if(immobile) {
						if(sleeping) {
							genericOrgasmSB.append(UtilText.parse(characterOrgasming, tribbingPartner,
									"[npc.Name]保持着沉睡，[npc2.namePos][npc2.pussy+]不断磨蹭、挤压、碰撞着[npc.her]的[npc.pussy]。"));
						} else {
							genericOrgasmSB.append(UtilText.parse(characterOrgasming, tribbingPartner,
									"[npc.Name]一动不动，[npc2.namePos][npc2.pussy+]不断磨蹭、挤压、碰撞着[npc.her]的[npc.pussy]。"));
						}
					} else {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, tribbingPartner,
								"[npc.NamePos][npc.pussy+]突然紧缩起来，[npc.she]全身心投入"
									+ "与[npc2.namePos][npc2.pussy+]磨蹭着，不禁发出[npc.a_moan+]。"));
					}
					
				} else {
					genericOrgasmSB.append("[npc.NamePos][npc.pussy+]忽然紧缩起来，但随之而来的空虚感却让[npc.her]顿时有些失望，几乎压倒了股间传来的快感。");
				}
			}
		}
		
		if(targetArea == OrgasmCumTarget.LILAYA_PANTIES && !Main.game.getPlayer().hasPenisIgnoreDildo()) {
			genericOrgasmSB.append("你高声娇喘着，把莉莱雅的内裤凑到脸前，呼吸着你恶魔[lilaya.relation(pc)]雌性味道浓重的芬芳，脑中满是她把这柔软布料贴在蜜穴上的样子。");
		}
		
		if(characterOrgasming.isVaginaSquirter()) {
			List<String> ejaculateDescriptors = new ArrayList<>();
			for(FluidModifier mod : FluidModifier.values()) {
				if(characterOrgasming.hasGirlcumModifier(mod)) {
					ejaculateDescriptors.add(mod.getName());
				}
			}
			ejaculateDescriptors.add("湿润的");
			genericOrgasmSB.append("<br/>[npc.namePos][npc.pussy+]无法控制地痉挛并愉悦的颤动，突然喷射出大量热且"+Util.randomItemFrom(ejaculateDescriptors)+"淫液");

			if(characterPenetrating!=null && penetration!=null) {
				boolean immobilePenetrator = isCharacterTotallyImmobilised(characterPenetrating);
				boolean sleepingPenetrator = characterPenetrating.isAsleep();
				switch(penetration) {
					case CLIT:
						genericOrgasmSB.append("，有助于润滑[npc2.namePos][npc2.clit+]。");
						break;
					case FINGER:
						if(immobilePenetrator) {
							genericOrgasmSB.append("，在[npc2.namePos][npc2.fingers+]"+(sleepingPenetrator?"狠狠地":"被操纵着")+"深入[npc.pussy]时，起到润滑作用。");
						} else {
							genericOrgasmSB.append("，[npc2.namePos][npc2.fingers+]依然在[npc.her]的[npc.pussy]里抽插，造成滑溜、湿润的水声。");
						}
						break;
					case FOOT:
						genericOrgasmSB.append("，一路流到了[npc2.namePos][npc2.feet+]上。");
						break;
					case PENIS:
						if(immobilePenetrator) {
							genericOrgasmSB.append("，在[npc2.namePos][npc2.cock+]"+(sleepingPenetrator?"狠狠地":"被操纵着")+"深入[npc.pussy]时，起到润滑作用。");
						} else {
							genericOrgasmSB.append("，[npc2.namePos][npc2.cock+]依然在[npc.her]的[npc.pussy]里抽插，造成淫荡、湿漉漉的水声。");
						}
						break;
					case TAIL:
						if(immobilePenetrator) {
							genericOrgasmSB.append("，在[npc2.namePos][npc2.tail+(true)]"+(sleepingPenetrator?"狠狠地":"被操纵着")+"深入[npc.pussy]时，起到润滑作用。");
						} else {
							genericOrgasmSB.append("，[npc2.namePos][npc2.tail+(true)]依然在[npc.her]的[npc.pussy]里抽插，造成淫荡、湿漉漉的水声。");
						}
						break;
					case TENTACLE:
						if(immobilePenetrator) {
							genericOrgasmSB.append("，在[npc2.namePos][npc2.tentacle+(true)]"+(sleepingPenetrator?"狠狠地":"被操纵着")+"深入[npc.pussy]时，起到润滑作用。");
						} else {
							genericOrgasmSB.append("，[npc2.namePos][npc2.tentacle+(true)]依然在[npc.her]的[npc.pussy]里抽插，造成淫荡、湿漉漉的水声。");
						}
						break;
					case TONGUE:
						if(immobilePenetrator) {
							genericOrgasmSB.append("，流过[npc2.namePos]"+(sleepingPenetrator?"狠狠地":"被操纵着")+"深入[npc.pussy]的舌头。");
						} else {
							genericOrgasmSB.append("，覆盖了[npc2.name][npc2.tongue+]。");
						}
						break;
				}
			} else {
				genericOrgasmSB.append("。");
			}
			
			if(targetArea == OrgasmCumTarget.LILAYA_PANTIES) {
				genericOrgasmSB.append("<br/>你迅速将莉莱雅的内裤放到两腿之间，直接对着它喷射了出来，你不禁发出[pc.a_moan+]。");
				LilayasRoom.lilayasPanties.setDirty(null, true);
				
			} else {
				AbstractClothing vaginaClothing = Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA);
				if(vaginaClothing!=null) {
					if(!vaginaClothing.getItemTags().contains(ItemTag.PLUGS_VAGINA)
							&& !vaginaClothing.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
						if(immobile) {
							if(sleeping) {
								genericOrgasmSB.append("[npc.She]保持沉睡，而"
										+vaginaClothing.getName()+"浸上淫液，弄脏了。");
							} else {
								genericOrgasmSB.append("[npc.she]一动不动，就像没感觉到"
										+vaginaClothing.getName()+"迅速被[npc.her]的液体脏污了。");
							}
						} else {
							genericOrgasmSB.append("[npc.She]发出深沉的叹息，感觉到"
									+vaginaClothing.getName()+"迅速被[npc.her]的液体脏污了。");
						}
						
					} else {
						genericOrgasmSB.append("因为[npc.her]的"+vaginaClothing.getName()+"封住了[npc.pussy]，所以没东西被[npc.her]的液体脏污。");
					}
					vaginaClothing.setDirty(Main.sex.getCharacterPerformingAction(), true);
					
				} else {
					Set<GameCharacter> charactersEatingOut = new HashSet<>(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
					charactersEatingOut.addAll(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaOrifice.MOUTH));
					
					for(GameCharacter character : charactersEatingOut) { // Should only be one character
						if(isCharacterTotallyImmobilised(character)) {
							genericOrgasmSB.append(UtilText.parse(characterOrgasming, character,
									"[npc.namePos]潮吹不断，体液喷溅而出，射到了[npc2.namePos]正对[npc.pussy+]的脸上和嘴里。"));
						} else {
							genericOrgasmSB.append(UtilText.parse(characterOrgasming, character,
									"[npc2.nameIs]给[npc.namePos]做着口交，液体喷溅到了[npc2.her]的嘴里，弄得[npc2.face]上到处都是。"));
						}
					}
				}
			}
		}
		
//		if(sexAction==GENERIC_ORGASM_OVIPOSITOR_CLIT_EGG_LAYING && characterOrgasming.equals(Main.sex.getCharacterLayingEggs())) {
//			genericOrgasmSB.append(eggLayingTargetDescription(SexAreaPenetration.CLIT, characterOrgasming, Main.sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.CLIT).get(0), null));
//		}
		if(immobile) {
			if(sleeping) {
				genericOrgasmSB.append("<br/><br/>[npc.Name]在熟睡中高潮迭起，体液喷溅而出，产生淫荡的声音，"
						+"不过没有任何醒来的迹象。");
			} else {
				genericOrgasmSB.append("<br/><br/>作为一个标准的性爱玩偶，[npc.Name]十分安静，一动不动，只有下身不断喷溅爱液表现出[npc.her]高潮，"
						+"却好像也不需要休息。");
			}
		} else {
			genericOrgasmSB.append("<br/><br/>[npc.name]心满意足地叹了一口气，高潮逐渐消退，深呼吸，调整自己的气息。");
		}
		
		if(characterPenetrating!=null) {
			return UtilText.parse(characterOrgasming, characterPenetrating, genericOrgasmSB.toString());
		} else {
			return UtilText.parse(characterOrgasming, genericOrgasmSB.toString());
		}
	}
	
	private static String getGenericMoundOrgasm(GameCharacter characterOrgasming) {
		if(isCharacterTotallyImmobilised(characterOrgasming)) {
			if(characterOrgasming.isAsleep()) {
				return UtilText.parse(characterOrgasming,
						"[npc.Name]在熟睡中发出下流的呻吟，身体被纯粹的快感冲刷着。"
								+"[npc.her]保持沉睡，但无性征的下体里，肌肉开始收缩痉挛，强烈的假性高潮席卷而来，"
								+ "但即使是这样强烈的假性高潮也不能将[npc.herHim]从沉睡中唤醒。");
			} else {
				return UtilText.parse(characterOrgasming,
						"[npc.Name]仍然一动不动，完全看不出身体正在被无尽的快感冲刷着。"
								+ "[npc.her]无性征的下体里，肌肉开始收缩痉挛，但[npc.she]仍然完美演绎着一个无生命性爱玩偶的职责，"
								+"尽管正处于假性高潮之中，[npc.her]也没有发出任何的声音。");
			}
		}
		if(characterOrgasming.isPlayer()) {
			return "随着一声刺耳的尖叫，你的双腿不住颤抖着，浪潮般的纯粹极乐冲刷着你的全身。"
					+ "你无性征的下体里，肌肉开始收缩痉挛，强烈的假高潮席卷而来，你满足地不住喘息着。";
			
		} else {
			return UtilText.parse(characterOrgasming,
					"随着一声刺耳的尖叫，[npc.Name]双腿不住颤抖着，浪潮般的纯粹极乐冲刷着[npc.her]的全身。"
							+ "[npc.her]无性征的下体里，肌肉开始收缩痉挛，强烈的假高潮席卷而来，[npc.sheIs]满足地不住喘息着。");
		}
		
	}
	
	public static String getGenericOrgasmDescription(SexActionInterface sexAction, GameCharacter characterOrgasming, OrgasmCumTarget target) {
		StringBuilder descriptionSB = new StringBuilder();

		GameCharacter characterPenetrated = null;
		
		if(!Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).isEmpty()) {
			characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).get(0);
			
			List<GameCharacter> charactersPenetrated = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, areaContacted));
			if(charactersPenetrated.contains(Main.sex.getTargetedPartner(characterOrgasming))) {
				characterPenetrated = Main.sex.getTargetedPartner(characterOrgasming);
			}
		}
		
		// Position:
		descriptionSB.append("<p>");
		if(isCharacterTotallyImmobilised(characterOrgasming)) {
			if(characterOrgasming.isAsleep()) {
				descriptionSB.append(UtilText.parse(characterOrgasming,
						"[npc.Name]达到了高潮，[npc.she]发出了[npc.a_moan+]，除了这些声音以及短暂地扭动了一下之外，[npc.she]仍保持着沉睡。"));
			} else {
				descriptionSB.append(UtilText.parse(characterOrgasming,
						"[npc.Name]的高潮如此强烈，本应该发出[npc.a_moan]；但[npc.her]却毫无反应，一声不吭。"));
			}
			
		} else {
			descriptionSB.append(getPositionPreparation(characterOrgasming, characterPenetrated!=null?characterPenetrated:Main.sex.getTargetedPartner(characterOrgasming)));
		}
		descriptionSB.append("</p>");
		
		if(characterOrgasming.hasTraitActivated(Perk.AHEGAO) && !isCharacterTotallyImmobilised(characterOrgasming)) {
			descriptionSB.append(getAhegaoDescription(characterOrgasming, Main.sex.getTargetedPartner(characterOrgasming)));
		}
		
		if(characterOrgasming.hasPenisIgnoreDildo()) {
			descriptionSB.append("<p>");
				descriptionSB.append(getGenericPenisOrgasmDescription(sexAction, characterOrgasming, characterPenetrated, target, sexAction.getCondomFailure(characterOrgasming, characterPenetrated), false));
			descriptionSB.append("</p>");
			
			if(target==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE) {
				GameCharacter secondaryTarget = getSecondaryCreampieTarget(characterPenetrated, (SexAreaOrifice) Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0));

				descriptionSB.append("<p>");
					descriptionSB.append(getGenericPenisOrgasmDescription(sexAction, characterOrgasming, secondaryTarget, target, sexAction.getCondomFailure(characterOrgasming, characterPenetrated), true));
				descriptionSB.append("</p>");
			}
		}
		
//		if(sexAction==GENERIC_ORGASM_OVIPOSITOR_CLIT_EGG_LAYING) {
//			characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.CLIT).get(0);
//			genericOrgasmSB.append("<p>"
//										+ eggLayingTargetDescription(SexAreaPenetration.CLIT, characterOrgasming, characterPenetrated, null)
//									+"</p>");
//		}
		
		if(characterOrgasming.hasVagina()) {
			descriptionSB.append("<p>");
				descriptionSB.append(getGenericVaginaOrgasmDescription(sexAction, characterOrgasming, target));
			descriptionSB.append("</p>");
		}
		
		if(!characterOrgasming.hasPenisIgnoreDildo() && !characterOrgasming.hasVagina()) {
			descriptionSB.append("<p>");
				descriptionSB.append(getGenericMoundOrgasm(characterOrgasming));
			descriptionSB.append("</p>");
		}
		
		return descriptionSB.toString();
	}
	
	// Doesn't have penis (or penis is not exposed), and isn't being vaginally penetrated:
	public static final SexAction PLAYER_GENERIC_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "高潮";
		}

		@Override
		public String getActionDescription() {
			return "你已经到达了快感的极限，再无法阻止高潮的到来。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (!Main.game.getPlayer().hasPenisIgnoreDildo() || !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) || Main.game.getPlayer().isWearingCondom())
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return Main.game.getPlayer().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.game.getPlayer()).get(0), false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.game.getPlayer().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.game.getPlayer()).get(0), true).applyEffects();
			if (Main.game.getPlayer().hasPenisIgnoreDildo()
					&& !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Main.game.getPlayer().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE) {
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(Main.game.getPlayer(), true);
			}
		}
		
		@Override
		public boolean endsSex() {
			return Main.game.getPlayer().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.game.getPlayer()).get(0), false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_CREAMPIE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeCreampied() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).contains(lockingCharacter)) {
					characterPenetrated = lockingCharacter;
				}
				
			} else { // If not locked, can choose who to cum inside:
				List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
				if(charactersPenetrated.contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					characterPenetrated = Main.sex.getCharacterTargetedForSexAction(this);
				}
			}
			
			return characterPenetrated;
		}
		private SexAreaInterface getAreaToBeCreampied() {
			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeCreampied()).get(0);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			boolean isPenetratingSuitableOrifice  = false;
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ASS:
					case THIGHS:
						return false;
					case ARMPITS:
					case BREAST:
					case BREAST_CROTCH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case ANUS:
					case MOUTH:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case VAGINA:
					case SPINNERET:
					case TUSSY:
						isPenetratingSuitableOrifice = true;
						break;
				}
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
					case PENIS:
					case TAIL:
					case TENTACLE:
					case TONGUE:
						break;
					case FINGER:
					case FOOT:
						isPenetratingSuitableOrifice = true;
						break;
				}
			}
			
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			
			if(isPerformingCharacterTotallyImmobilised()) {
				return true;
			}
			
			// Will not use if obeying pull out requests:
			if(((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
						&& Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.KNOT)
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction()) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			boolean knotRequestObeyed = false;
			for(GameCharacter knotRequester : Main.sex.getCharactersRequestingKnot()) {
				if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterPerformingAction(), knotRequester)) {
					knotRequestObeyed = true; // If there is a knot requester who they're listening to, give priority to knotting
					break;
				}
			}
			if(isPerformingCharacterTotallyImmobilised()) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE
					|| (Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.KNOT && !GENERIC_ORGASM_KNOTTING.isBaseRequirementsMet())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(getAreaToBeCreampied()==SexAreaOrifice.VAGINA
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant()) {
				return SexActionPriority.LOW;
			}
			if((Math.random()<0.66f
//					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| (getAreaToBeCreampied()==SexAreaOrifice.VAGINA && Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)))
				&& !knotRequestObeyed) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return "被迫内射！";
					
				} else if(bodypart == Arm.class) {
					return "拥抱禁锢！";
					
				} else if(bodypart == Leg.class) {
					return "叉腿禁锢！";
					
				} else if(bodypart == Tail.class) {
					return "尾巴禁锢！";
					
				} else if(bodypart == Wing.class) {
					return "翅膀禁锢！";
					
				} else if(bodypart == Tentacle.class) {
					return "触手禁锢！";
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
						return "腋窝高潮";
					case ANUS:
						return "肛门内射";
					case ASS:
						return "尻交高潮";
					case BREAST:
						if(characterPenetrated.hasBreasts()) {
							return "乳交高潮";
						} else {
							return "贫乳乳交高潮";
						}
					case BREAST_CROTCH:
						return "乳交高潮";
					case MOUTH:
						return "深喉";
					case NIPPLE: case NIPPLE_CROTCH:
						return "乳头内射";
					case THIGHS:
						return "腿交高潮";
					case URETHRA_PENIS: case URETHRA_VAGINA:
						return "尿道内射";
					case VAGINA:
						return "内射";
					case SPINNERET:
						return "丝囊内射";
                    case TUSSY:
						return "Tussy creampie";
				}
				
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
						break;
					case FINGER:
						return "手交高潮";
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case FOOT:
						return "足交高潮";
					case TONGUE:
						break;
				}
			}
			return "内射";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter character = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return UtilText.parse(character,
							"[npc.NameIsFull]占据有利位置，强迫你射在[npc.herHim]里面！你处在高潮边缘，没时间尝试推开[npc.herHim]！");
					
				} else if(bodypart == Arm.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.her][npc.arms+]抱着你的下背部，强迫你射在里面！"
							+ "你处在高潮边缘，没时间尝试从[npc.herHim]的掌握中解脱！");
					
				} else if(bodypart == Leg.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.her][npc.legs+]抱着你的下背部，强迫你射在里面！"
							+ "你处在高潮边缘，没时间尝试从[npc.herHim]的掌握中解脱！");
					
				} else if(bodypart == Tail.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.her]"+(character.getTailCount()>1?"[npc.tails+]":"[npc.tail]")+"缠着你的下背部，强迫你射在里面！"
							+ "你处在高潮边缘，没时间尝试从[npc.herHim]的掌握中解脱！");
					
				} else if(bodypart == Wing.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.wingSize]的[npc.wings]包裹着你的身体，强迫你射在里面！"
							+ "你处在高潮边缘，没时间尝试从[npc.herHim]的掌握中解脱！");
					
				} else if(bodypart == Tentacle.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.her][npc.tentacles+]缠着你的下背部，强迫你射在里面！"
							+ "你处在高潮边缘，没时间尝试从[npc.herHim]的掌握中解脱！");
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			String returnString = "你达到了极限，再也无法承受快感的冲击。你内射了[npc2.name]。";
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
						returnString = "继续在高潮时用[npc.cock+]蹭着[npc2.namePos][npc2.armpit+]，射得[npc2.her]手臂和奶子上都是你[npc.cum+]。";
						break;
					case ANUS:
						if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(Main.game.isPenetrationLimitationsEnabled() && Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
								returnString = "[style.italicsBad([npc2.NamePos]的肛门不够深，塞不进去你的结)]，"
										+ "所以你只能把自己[npc.cock+]深深插进了[npc2.her]的屁股里，用[npc.cum+]灌满了[npc2.herHim]。";
							} else {
								returnString = "不再硬塞你的结，只把[npc.cock+]深深顶进[npc2.namePos][npc2.asshole+]，然后填满[npc.cum+]。";
							}
							
						} else {
							returnString = "把你[npc.cock+]深深插进[npc2.namePos][npc2.asshole+]，用[npc.cum+]灌满了[npc2.herHim]。";
						}
						break;
					case ASS:
						returnString = "你维持着尻交的姿势享受高潮，射得[npc2.name]后背和屁股上到处都是[npc.cum+]。";
						break;
					case BREAST:
						if(characterPenetrated.hasBreasts()) {
							returnString = "在高潮时继续操干[npc2.namePos]的乳房，射得[npc2.her]奶子和[npc2.face]上都是你[npc.cum+]。";
						} else {
							returnString = "在高潮时继续用[npc.cock+]磨蹭[npc2.namePos]的平胸，射得[npc2.her]胸和脸上都是你[npc.cum+]。";
						}
						break;
					case BREAST_CROTCH:
						returnString = "在高潮时继续用[npc.cock+]操干[npc2.namePos][npc2.crotchBoobs]，射得[npc2.her]肚子，[npc2.crotchBoobs]和胯部上都是你[npc.cum+]。";
						break;
					case MOUTH:
						returnString = "把你[npc.cock+]深深插进[npc2.namePos]的喉咙，用[npc.cum+]填满[npc2.her]的肚子。";
						break;
					case NIPPLE:
						returnString = "把你[npc.cock+]深深插进[npc2.namePos][npc2.nipple+]，用[npc.cum+]填满[npc2.her]的乳房。";
						break;
					case NIPPLE_CROTCH:
						returnString = "把你[npc.cock+]深深插进[npc2.namePos][npc2.crotchNipple+]，用[npc.cum+]填满[npc2.her][npc2.crotchBoob]。";
						break;
					case THIGHS:
						returnString = "在高潮时继续用[npc.cock+]操干[npc2.namePos]股间，射得[npc2.her][npc2.legs]上都是你[npc.cum+]。";
						break;
					case URETHRA_PENIS:
						returnString = "将你[npc.cock+]深深地推进[npc2.namePos][npc2.cock+]尿道，你把[npc.cum+]射满了[npc2.her]的膀胱。";
						break;
					case URETHRA_VAGINA:
						returnString = "将你[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]中，你把[npc2.her]的膀胱注满了你[npc.cum+]。";
						break;
					case VAGINA:
						if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(Main.game.isPenetrationLimitationsEnabled() && Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
								returnString = "[style.italicsBad([npc2.NamePos]的小穴不够深，塞不进去你的结)]，"
										+ "所以你只能把自己[npc.cock+]深深插进了[npc2.her]的阴部，用[npc.cum+]灌满"
										+(characterPenetrated.isPregnant() || characterPenetrated.hasIncubationLitter(SexAreaOrifice.VAGINA)
												?"[npc2.herHim]"
												:"[npc2.her]的子宫")
										+ "。";
							} else {
								returnString = "不再硬塞你的结，只把[npc.cock+]深深顶进[npc2.namePos][npc2.pussy+]，然后用[npc.cum+]填满"
										+(characterPenetrated.isPregnant() || characterPenetrated.hasIncubationLitter(SexAreaOrifice.VAGINA)
												?"[npc2.herHim]"
												:"[npc2.her]的子宫")
										+ "。";
							}
							
						} else {
							returnString = "把你[npc.cock+]深深插进[npc2.namePos][npc2.pussy+]，用[npc.cum+]填满[npc2.her]的"
										+(characterPenetrated.isPregnant() || characterPenetrated.hasIncubationLitter(SexAreaOrifice.VAGINA)
												?"[npc2.pussy]"
												:"子宫")
										+ "。";
						}
						break;
					case SPINNERET:
						returnString = "把你[npc.cock+]深深插进[npc2.namePos]的丝囊，用[npc.cum+]填满[npc2.her]。";
						break;
					case TUSSY:
						returnString = "Push your [npc.cock+] deep into [npc2.namePos] [npc2.tussy+], before filling [npc2.herHim] with your [npc.cum+].";
						break;
				}
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
						break;
					case FINGER:
						if(Main.sex.getCharacterPerformingAction().equals(characterPenetrated)) {
							returnString = "继续撸动着你的肉棒，然后将[npc.cum+]射满你[npc.hand+]。";
						} else {
							returnString = "继续享受着[npc2.name]手交所带来的快感，然后微微抽出，将你[npc.cum+]喷射到[npc2.her][npc2.hand+]上。";
						}
						break;
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case FOOT:
						returnString = "继续专注于[npc2.namePos][npc2.footjob]带来的快感，然后将[npc.cum+]喷射到[npc2.her][npc2.feet+]上。";
						break;
					case TONGUE:
						break;
				}
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEndEffects();
			return "";
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(characterPenetrated)) {
//				System.out.println(cumProvider.getNameIgnoresPlayerKnowledge()+" "+Main.sex.getCharacterPerformingAction().getNameIgnoresPlayerKnowledge());
				return Util.newArrayListOfValues(areaContacted);
				
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& ((cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumTarget).isEmpty())
						|| (cumTarget.equals(cumProvider) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumProvider).isEmpty()))) {
				SexAreaInterface areaContacted = getAreaToBeCreampied();
				
				if(!areaContacted.isOrifice()) {
					switch((SexAreaPenetration)areaContacted) {
						case CLIT:
							break;
						case FINGER:
							return Util.newArrayListOfValues(
									CoverableArea.HANDS);
						case FOOT:
							return Util.newArrayListOfValues(
									CoverableArea.FEET);
						case PENIS:
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case TONGUE:
							break;
					}
				} else {
					switch((SexAreaOrifice)areaContacted) {
						case ARMPITS:
							return Util.newArrayListOfValues(
									CoverableArea.ARMPITS);
						case ANUS:
							break;
						case ASS:
							if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								return Util.newArrayListOfValues(
										CoverableArea.ASS);
							} else {
								return Util.newArrayListOfValues(
										CoverableArea.ASS,
										CoverableArea.ANUS);
							}
						case BREAST:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.MOUTH);
						case BREAST_CROTCH:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS_CROTCH,
									CoverableArea.NIPPLES_CROTCH,
									CoverableArea.STOMACH,
									CoverableArea.PENIS,
									CoverableArea.VAGINA);
						case MOUTH:
							break;
						case NIPPLE:
							break;
						case NIPPLE_CROTCH:
							break;
						case THIGHS:
							return Util.newArrayListOfValues(
									CoverableArea.LEGS);
						case URETHRA_PENIS:
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							break;
						case SPINNERET:
							break;
                        case TUSSY:
							break;
					}
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).isEndsSex()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)//TODO should be moved out into pregnancy roulette character method
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	public static final SexAction GENERIC_ORGASM_KNOTTING = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterToBeKnotted() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).contains(lockingCharacter)) {
					characterPenetrated = lockingCharacter;
				}
				
			} else { // If not locked, can choose who to cum inside:
				List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
				if(charactersPenetrated.contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					characterPenetrated = Main.sex.getCharacterTargetedForSexAction(this);
				}
			}
			
			return characterPenetrated;
		}
		private SexAreaInterface getAreaToBeKnotted() {
			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeKnotted()).get(0);
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					|| !Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()
					|| Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeKnotted()).isEmpty()) {
				return false;
			}

			SexAreaInterface areaContacted = getAreaToBeKnotted();
			GameCharacter characterPenetrated = getCharacterToBeKnotted();
			
			if(!areaContacted.isOrifice() || !((SexAreaOrifice)areaContacted).isInternalOrifice()) {
				return false;
			}
			
			// Cannot use if orifice is not deep enough
			// (Does not take into account willingness to fully penetrate, as the orgasming character is assumed to want to knot even if not normally willing to deeply penetrate.)
			if(Main.game.isPenetrationLimitationsEnabled()
					&& Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
				return false;
			}
			
			// Will not use if obeying pull out requests:
			if(((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
						&& Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.KNOT)
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction()) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			// If immobile, cannot force knot inside:
			if(isPerformingCharacterTotallyImmobilised()) {
				return false;
			}
			
			return true;
		}
		
		@Override
		public SexActionPriority getPriority() { // Has same priority as normal creampie:
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.KNOT) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(getAreaToBeKnotted()==SexAreaOrifice.VAGINA
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant()) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f
//					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| (getAreaToBeKnotted()==SexAreaOrifice.VAGINA && Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION))) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return "强制内射(锁结！)";
					
				} else if(bodypart == Arm.class) {
					return "拥抱禁锢(锁结！)";
					
				} else if(bodypart == Leg.class) {
					return "叉腿禁锢(锁结！)";
					
				} else if(bodypart == Tail.class) {
					return "尾巴禁锢(锁结！)";
					
				} else if(bodypart == Wing.class) {
					return "翅膀禁锢(锁结！)";
					
				} else if(bodypart == Tentacle.class) {
					return "触手禁锢(锁结！)";
				}
			}
			
			return UtilText.parse(getCharacterToBeKnotted(), "锁结[npc.herHim]");
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter character = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return UtilText.parse(character,
							"[npc.NameIsFull]占据有利位置，强迫你射在[npc.herHim]里面！"
							+ "给[npc.herHim]想要的，锁结[npc.herHim]吧！");
					
				} else if(bodypart == Arm.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.her][npc.arms+]抱着你的下背部，强迫你射在里面！"
							+ "给[npc.herHim]想要的，锁结[npc.herHim]吧！");
					
				} else if(bodypart == Leg.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.her][npc.legs+]抱着你的下背部，强迫你射在里面！"
							+ "给[npc.herHim]想要的，锁结[npc.herHim]吧！");
					
				} else if(bodypart == Tail.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.her]"+(character.getTailCount()>1?"[npc.tails+]":"[npc.tail]")+"缠着你的下背部，强迫你射在里面！"
							+ "给[npc.herHim]想要的，锁结[npc.herHim]吧！");
					
				} else if(bodypart == Wing.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.wingSize]的[npc.wings]包裹着你的身体，强迫你射在里面！"
							+ "给[npc.herHim]想要的，锁结[npc.herHim]吧！");
					
				} else if(bodypart == Tentacle.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull]紧紧地用[npc.her][npc.tentacles+]缠着你的下背部，强迫你射在里面！"
							+ "给[npc.herHim]想要的，锁结[npc.herHim]吧！");
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeKnotted();
			SexAreaInterface areaContacted = getAreaToBeKnotted();
			String returnString = "你达到了极限，再也无法承受快感的冲击。把结塞进[npc2.namePos]体内"+areaContacted.getName(characterPenetrated)+"用你[npc.cum+]填满[npc2.herHim]。";
			
			switch((SexAreaOrifice)areaContacted) {
				case ANUS:
					returnString = "将你的鸡巴尽根没入[npc2.namePos][npc2.asshole+]，膨胀阴茎结，将你锁在[npc2.herHim]体内，直到你将[npc2.her]体内灌满[npc.cum+]。";
					break;
				case MOUTH:
					returnString = "将你的鸡巴尽根没入[npc2.namePos]的喉咙，膨胀阴茎结，将你锁在[npc2.herHim]体内，直到你将[npc2.her]的肚子灌满[npc.cum+]。";
					break;
				case NIPPLE:
					returnString = "将你的鸡巴尽根没入[npc2.namePos][npc2.nipple+]，膨胀阴茎结，将你锁在[npc2.herHim]体内，直到你将[npc2.her]的乳房灌满[npc.cum+]。";
					break;
				case NIPPLE_CROTCH:
					returnString = "将你的鸡巴尽根没入[npc2.namePos][npc2.crotchNipple+]，膨胀阴茎结，将你锁在[npc2.herHim]体内，直到你将[npc2.her]的[npc2.crotchBoob]灌满[npc.cum+]。";
					break;
				case URETHRA_PENIS:
					returnString = "你的整根鸡巴完全推进了[npc2.namePos][npc2.cock+]尿道里，紧接着，你的阴茎结膨胀起来，将你锁在[npc2.herHim]体内，直到你将[npc2.her]的膀胱灌满了你[npc.cum+]。";
					break;
				case URETHRA_VAGINA:
					returnString = "你的整根鸡巴完全推进了[npc2.namePos][npc2.pussy+]尿道里，紧接着，你的阴茎结膨胀起来，将你锁在[npc2.herHim]体内，直到你将[npc2.her]的膀胱灌满了你[npc.cum+]。";
					break;
				case VAGINA:
					returnString = "将你的鸡巴尽根没入[npc2.namePos][npc2.pussy+]，膨胀阴茎结，"
							+ "将你锁在[npc2.herHim]体内，直到你用[npc.cum+]灌满[npc2.her]的"
										+(characterPenetrated.isPregnant() || characterPenetrated.hasIncubationLitter(SexAreaOrifice.VAGINA)
												?"[npc2.pussy]"
												:"子宫")
										+ "。";
					break;
				default:
					return "锁结错误！(Code 1)";
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, false).getDescription();
		}
		
		@Override
		public String applyPreParsingEffects() {
			Main.sex.addCharactersKnottedTogether(Main.sex.getCharacterPerformingAction(), getCharacterToBeKnotted()); // Added so that the generic orgasm description parses it as a knotting action.
			return "";
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects() {
			// Moved to start of Main.sex.endSexTurn(), so that there's time to stretch.
//			Main.sex.removeCharactersKnottedTogether(Main.sex.getCharacterPerformingAction()); // Remove as the generic orgasm description has already been parsed.
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEndEffects();
			return "";
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			SexAreaInterface areaContacted = getAreaToBeKnotted();
			GameCharacter characterPenetrated = getCharacterToBeKnotted();
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(characterPenetrated)) {
				return Util.newArrayListOfValues(areaContacted);
					
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).isEndsSex()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	
	private static Map<GameCharacter, SexSlot> getSuitableSecondaryCreampieTargets(GameCharacter targetedCharacter) {
		Map<GameCharacter, SexSlot> suitableSecondaryCharacters = null;
		if(Main.sex.isDom(Main.sex.getCharacterPerformingAction())) {
			suitableSecondaryCharacters = new HashMap<>(Main.sex.getSubmissiveParticipants(false));
		} else {
			suitableSecondaryCharacters = new HashMap<>(Main.sex.getDominantParticipants(false));
		}
		suitableSecondaryCharacters.remove(targetedCharacter);
		
		return suitableSecondaryCharacters;
	}
	
	private static GameCharacter getSecondaryCreampieTarget(GameCharacter targetedCharacter, SexAreaOrifice areaContacted) {
		Map<GameCharacter, SexSlot> suitableSecondaryCharacters = getSuitableSecondaryCreampieTargets(targetedCharacter);
		
		GameCharacter secondaryTarget = null;
		
		for(Entry<GameCharacter, SexSlot> entry : suitableSecondaryCharacters.entrySet()) {
//			System.out.println(entry.getKey().getName(true)+" check");
			if((areaContacted!=SexAreaOrifice.VAGINA || entry.getKey().hasVagina()) && Main.sex.isOrificeFree(entry.getKey(), areaContacted)) {
//				System.out.println(entry.getKey().getName(true)+" free");
				try {
					if(Main.sex.getPosition().getSexInteractions(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()), Main.sex.getSexPositionSlot(entry.getKey())).getInteractions().get(SexAreaPenetration.PENIS).contains(areaContacted)) {
						secondaryTarget = entry.getKey();
						break;
					}
				} catch(Exception ex) {
					// getInteractions() map might not contain key SexAreaPenetration.PENIS
				}
			}
		}
		
		return secondaryTarget;
	}

	public static final SexAction GENERIC_ORGASM_DOUBLE_CREAMPIE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()) {
				return false;
			}
			if(Main.sex.getCharacterPerformingAction().getPenisCumStorage()==CumProduction.ZERO_NONE) {
				return false;
			}
			Map<GameCharacter, SexSlot> suitableSecondaryCharacters = getSuitableSecondaryCreampieTargets(Main.sex.getCharacterTargetedForSexAction(this));
			if(suitableSecondaryCharacters.isEmpty()) {
				return false;
			}
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			if(areaContacted.isPenetration()) {
				return false;
			}
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			if(secondaryTarget==null) {
				return false;
			}
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case THIGHS:
					case BREAST:
					case BREAST_CROTCH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case MOUTH:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
                    case TUSSY:
						return false;
					case ANUS:
					case VAGINA:
						if(!secondaryTarget.isOrificeTypeExposed((SexAreaOrifice) areaContacted)
								|| !((SexAreaOrifice) areaContacted).isFree(secondaryTarget)) {
							return false;
						}
						break;
				}
			} else {
				return false;
			}
			
			// Will not use if obeying the player and player asked for pull out:
			if(((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
						&& Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.KNOT)
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT
				|| Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) { // Cannot double creampie if someone is forcing creampie.
				return false;
			}

			// If immobile, cannot move to double-creampie:
			if(isPerformingCharacterTotallyImmobilised()) {
				return false;
			}
			
			return true;
		}
		
		@Override
		public SexActionPriority getPriority() {
			boolean knotRequestObeyed = false;
			for(GameCharacter knotRequester : Main.sex.getCharactersRequestingKnot()) {
				if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterPerformingAction(), knotRequester)) {
					knotRequestObeyed = true; // If there is a knot requester who they're listening to, give priority to knotting
					break;
				}
			}
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE
					|| (Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.KNOT && !GENERIC_ORGASM_KNOTTING.isBaseRequirementsMet())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
					&& (!Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant() || !getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).isVisiblyPregnant())) {
				return SexActionPriority.LOW;
			}
			if((Math.random()<0.5f
//					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
							&& Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)))
				&& !knotRequestObeyed) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionTitle() {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
                    case TUSSY:
						break;
					case ANUS:
						return "双重肛门内射";
					case VAGINA:
						return "双重内射";
				}
			}
			return "双重内射";
		}

		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryCharacterPenetrated = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			String returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
					"你达到了极限，再也无法承受快感的冲击。把一半精液内射到[npc.name]体内后，你把屌拔了出来，把另外一半灌给了[npc2.name]。");
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
                    case TUSSY:
						break;
					case ANUS:
						if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(Main.game.isPenetrationLimitationsEnabled() && Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
								returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
										"[style.italicsBad([npc2.NamePos]的肛门不够深，塞不进去你的结)]，"
										+"把一半的精液内射到[npc.Name]体内之后，你狠狠地插进[npc2.her]的屁股，把剩下的部分灌了进去。");
								
							} else {
								returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
										"你在[npc.namePos]的[npc.asshole]外成结，朝里灌了一半精液，然后拔了出来，"
												+"接着插进[npc2.name]的[npc2.asshole]里迅速射完另外一半，之后拔了出来。你射的很快，以免成结锁住。");
							}
							
						} else {
							returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
									"你达到了极限，再也无法承受快感的冲击。把一半精液内射入[npc.name]的[npc.asshole]，然后迅速拔了出来，把另外一半灌进了[npc2.namePos][npc2.asshole+]。");
						}
						break;
					case VAGINA:
						if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(Main.game.isPenetrationLimitationsEnabled() && Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
								returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
										"[style.italicsBad([npc2.NamePos]的小穴不够深，塞不进去你的结)]，"
										+"你在把一半精液内射到[npc.Name]之后，用力把[pc.cock+]捅进[npc2.her]小穴然后射了个爽。");
								
							} else {
								returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
										"你在[npc.namePos]的[npc.pussy]之外成结，然后把一半精液射了进去，"
												+"你迅速地在[npc2.name]的[npc2.pussy]射出了另外一半，然后拔了出来，以免成结。");
							}
							
						} else {
							returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
									"你达到了极限，再也无法承受快感的冲击。你把一半精液灌进了[npc.namePos][npc.pussy+]里，然后迅速插进[npc2.namePos]的小穴灌进去另外一半。");
						}
						break;
				}
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);

			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEndEffects();
			
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), areaContacted);
			Main.sex.applyOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, areaContacted, true);
			
			if(areaContacted==SexAreaOrifice.VAGINA) {
				return Main.sex.applyPenetrationEffects(PenisVagina.PENIS_FUCKING_START, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, SexAreaOrifice.VAGINA);
			} else {
				return Main.sex.applyPenetrationEffects(PenisAnus.PENIS_FUCKING_START, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, SexAreaOrifice.ANUS);
			}
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && (cumTarget.equals(characterPenetrated) || cumTarget.equals(secondaryTarget))) {
				return Util.newArrayListOfValues(areaContacted);
					
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& (cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) || cumTarget.equals(secondaryTarget))) {
	
				if(!areaContacted.isOrifice()) {
					switch((SexAreaPenetration)areaContacted) {
						case CLIT:
							break;
						case FINGER:
							return Util.newArrayListOfValues(
									CoverableArea.HANDS);
						case FOOT:
							return Util.newArrayListOfValues(
									CoverableArea.FEET);
						case PENIS:
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case TONGUE:
							break;
					}
				} else {
					switch((SexAreaOrifice)areaContacted) {
						case ARMPITS:
						case ANUS:
							break;
						case ASS:
							if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								return Util.newArrayListOfValues(
										CoverableArea.ASS);
							} else {
								return Util.newArrayListOfValues(
										CoverableArea.ASS,
										CoverableArea.ANUS);
							}
						case BREAST:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.MOUTH);
						case BREAST_CROTCH:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS_CROTCH,
									CoverableArea.NIPPLES_CROTCH,
									CoverableArea.STOMACH,
									CoverableArea.PENIS,
									CoverableArea.VAGINA);
						case MOUTH:
							break;
						case NIPPLE:
							break;
						case NIPPLE_CROTCH:
							break;
						case THIGHS:
							return Util.newArrayListOfValues(
									CoverableArea.LEGS);
						case URETHRA_PENIS:
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							break;
						case SPINNERET:
							break;
                        case TUSSY:
							break;
					}
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).isEndsSex()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	public static final SexAction GENERIC_ORGASM_DOUBLE_KNOTTING = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					|| !Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
				return false;
			}
			
			if(Main.sex.getCharacterPerformingAction().getPenisCumStorage()==CumProduction.ZERO_NONE) {
				return false;
			}
			
			Map<GameCharacter, SexSlot> suitableSecondaryCharacters = getSuitableSecondaryCreampieTargets(Main.sex.getCharacterTargetedForSexAction(this));
			if(suitableSecondaryCharacters.isEmpty()) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			if(!areaContacted.isOrifice()) {
				return false;
			}

			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			if(secondaryTarget==null) {
				return false;
			}
			
			switch((SexAreaOrifice)areaContacted) {
				case ARMPITS:
				case ASS:
				case THIGHS:
				case BREAST:
				case BREAST_CROTCH:
				case NIPPLE:
				case NIPPLE_CROTCH:
				case MOUTH:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
				case SPINNERET:
                case TUSSY:
					return false;
				case ANUS:
				case VAGINA:
					if(!secondaryTarget.isOrificeTypeExposed((SexAreaOrifice) areaContacted)
							|| !((SexAreaOrifice) areaContacted).isFree(secondaryTarget)) {
						return false;
					}
					break;
			}
			
			// Cannot use if orifice is not deep enough
			// (Does not take into account willingness to fully penetrate, as the orgasming character is assumed to want to knot even if not normally willing to deeply penetrate.)
			if(Main.game.isPenetrationLimitationsEnabled()
					&& Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, secondaryTarget, (SexAreaOrifice)areaContacted, false)) {
				return false;
			}
			
			// Will not use if obeying the player and player asked for pull out:
			if(((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
						&& Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.KNOT)
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT
				|| Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) { // Cannot double creampie if someone is forcing creampie.
				return false;
			}

			// If immobile, cannot move to double-creampie:
			if(isPerformingCharacterTotallyImmobilised()) {
				return false;
			}
			
			return true;
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.KNOT) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
					&& (!Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant() || !getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).isVisiblyPregnant())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.5f
//					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
							&& Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION))) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionTitle() {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
					case TUSSY:
						break;
					case ANUS:
						return "双重肛门锁结";
					case VAGINA:
						return "双重锁结";
				}
			}
			return "双重锁结";
		}

		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryCharacterPenetrated = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			String returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
					"你达到了极限，再也无法承受快感的冲击。你内射[npc.name]到一半，然后迅速拔出来，用锁结锁住了[npc2.name]，并在[npc2.herHim]体内射干净。");
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
                    case TUSSY:
						break;
					case ANUS:
						returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
								"你在[npc.namePos]的[npc.asshole]外成结，朝里灌了一半精液，然后拔了出来，"
										+"接着迅速把鸡巴连着结一起塞进[npc2.namePos][npc2.asshole]里，然后射了个爽");
						break;
					case VAGINA:
						returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
								"你在[npc.namePos][npc.pussy+]外成结，然后把一半的精液关了进去，"
										+"接着迅速把鸡巴连着结一起塞进[npc2.namePos][npc2.pussy+]里，然后射了个爽");
						break;
				}
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, false).getDescription();
		}

		@Override
		public String applyPreParsingEffects() {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			Main.sex.addCharactersKnottedTogether(Main.sex.getCharacterPerformingAction(), secondaryTarget); // Added so that the generic orgasm description parses it as a knotting action.
			return "";
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);

			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEndEffects();
			
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), areaContacted);
			Main.sex.applyOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, areaContacted, true);
			
			return Main.sex.applyPenetrationEffects(PenisVagina.PENIS_FUCKING_START, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, SexAreaOrifice.VAGINA);
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && (cumTarget.equals(characterPenetrated) || cumTarget.equals(secondaryTarget))) {
				return Util.newArrayListOfValues(areaContacted);
					
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& (cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) || cumTarget.equals(secondaryTarget))) {
	
				if(!areaContacted.isOrifice()) {
					switch((SexAreaPenetration)areaContacted) {
						case CLIT:
							break;
						case FINGER:
							return Util.newArrayListOfValues(
									CoverableArea.HANDS);
						case FOOT:
							return Util.newArrayListOfValues(
									CoverableArea.FEET);
						case PENIS:
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case TONGUE:
							break;
					}
				} else {
					switch((SexAreaOrifice)areaContacted) {
						case ARMPITS:
						case ANUS:
							break;
						case ASS:
							if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								return Util.newArrayListOfValues(
										CoverableArea.ASS);
							} else {
								return Util.newArrayListOfValues(
										CoverableArea.ASS,
										CoverableArea.ANUS);
							}
						case BREAST:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.MOUTH);
						case BREAST_CROTCH:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS_CROTCH,
									CoverableArea.NIPPLES_CROTCH,
									CoverableArea.STOMACH,
									CoverableArea.PENIS,
									CoverableArea.VAGINA);
						case MOUTH:
							break;
						case NIPPLE:
							break;
						case NIPPLE_CROTCH:
							break;
						case THIGHS:
							return Util.newArrayListOfValues(
									CoverableArea.LEGS);
						case URETHRA_PENIS:
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							break;
						case SPINNERET:
							break;
						case TUSSY:
							break;
					}
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).isEndsSex()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	public static void applyGenericPullOutEffects(SexActionInterface sexAction, OrgasmCumTarget orgasmCumTarget) {
		if(sexAction!=null && orgasmCumTarget!=null) { // null check for external SexActions using this method via Sex.java
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(sexAction, orgasmCumTarget, true).applyEffects();
		}
		GameCharacter characterPenetrated = null;
		SexAreaInterface areaContacted = null;
		if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
			characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, characterPenetrated, areaContacted);
		}
	}
	
	public static final SexAction GENERIC_ORGASM_FLOOR = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.FLOOR);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在地上";
				}
				return "外射(地上)";
			}
			return "射在地上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了地上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FLOOR, false).getDescription();
		}

		@Override
		public SexActionPriority getPriority() {
			// Seemed a little random to have this behaviour...
//			if(!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isNegative()) {
//				return SexActionPriority.LOW; // Prefer cumming on someone if they don't dislike cumming.
//			}
			return super.getPriority();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.FLOOR);
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FLOOR, false).isEndsSex();
		}
	};
	
	
	public static final SexAction GENERIC_ORGASM_WALL = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.WALL);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在[pc.wall]上";
				}
				return "外射([pc.wall])";
			}
			return "射在[pc.wall]上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了[pc.wall]上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.WALL, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.WALL);
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.WALL, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_ASS = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.ASS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在屁股上";
				}
				return "外射(肛门)";
			}
			return "射在屁股上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了[npc2.namePos][npc2.ass+]上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ASS, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.ASS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return Util.newArrayListOfValues(
							CoverableArea.ASS);
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
					return Util.newArrayListOfValues(
							CoverableArea.ASS,
							CoverableArea.ANUS,
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				} else {
					return Util.newArrayListOfValues(
							CoverableArea.ASS,
							CoverableArea.ANUS);
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ASS, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_GROIN = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.GROIN);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在腹股沟上";
				}
				return "外射(腹股沟)";
			}
			return "射在腹股沟上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了[npc2.namePos]的腹股沟上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.GROIN);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return Util.newArrayListOfValues(
							CoverableArea.ANUS,
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
					
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
					return Util.newArrayListOfValues(
							CoverableArea.THIGHS);
					
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.NORMAL) {
					return Util.newArrayListOfValues(
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_GROIN = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_GROIN);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在自己的腹股沟上";
				}
				return "外射(自己的腹股沟)";
			}
			return "射在自己的腹股沟上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了自己的腹股沟。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_GROIN, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_GROIN);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return Util.newArrayListOfValues(
							CoverableArea.ANUS,
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.NORMAL) {
					return Util.newArrayListOfValues(
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_GROIN, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_BREASTS = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.BREASTS);
		}
		
		@Override
		public String getActionTitle() {
			String breasts = "乳房";
			if(!Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				breasts = "胸部";
			}
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射精到"+breasts;
				}
				return "外射("+breasts+")";
			}
			return "射到"+breasts;
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了[npc2.namePos]的[npc2.breasts]上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BREASTS, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.BREASTS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.NIPPLES,
						CoverableArea.BREASTS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BREASTS, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_BREASTS = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_BREASTS);
		}
		
		@Override
		public String getActionTitle() {
			String breasts = "乳房";
			if(!Main.sex.getCharacterPerformingAction().hasBreasts()) {
				breasts = "胸部";
			}
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交(射到自己的"+breasts+"上)";
				}
				return "外射(自己的"+breasts+")";
			}
			return "射到自己的"+breasts;
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了自己[npc.breasts+]上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_BREASTS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_BREASTS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.NIPPLES,
						CoverableArea.BREASTS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_BREASTS, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_FACE = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.FACE);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在脸上";
				}
				return "外射(面部)";
			}
			return "面部";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了[npc2.namePos]脸上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FACE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.FACE);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FACE, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_FACE = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_FACE);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交(射在自己脸上)";
				}
				return "外射(自己的脸)";
			}
			return "射在自己脸上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了自己的[npc.face+]上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FACE, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_FACE);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FACE, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_HANDS = new SexAction(GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_HANDS);
		}

		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在自己的手上";
				}
				return "外射(自己的手)";
			}
			return "射在自己的手上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了自己的[npc.hands]上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_HANDS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_HANDS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.HANDS);
			}
			return null;
		}

		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_HANDS, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_HAIR = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.HAIR);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在[npc2.hair(true)]里";
				}
				return "外射([npc2.hair(true)])";
			}
			return "射在[npc2.hair(true)]上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了[npc2.namePos]的[npc2.hair(true)]上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.HAIR, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.HAIR);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.HAIR);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.HAIR, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_STOMACH = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.STOMACH);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在肚子上";
				}
				return "外射(肚子)";
			}
			return "射在肚子上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了[npc2.namePos]肚子上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.STOMACH, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.STOMACH);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.STOMACH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.STOMACH, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_SELF_STOMACH = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_STOMACH);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在自己的肚子上";
				}
				return "外射(自己的肚子)";
			}
			return "射在自己肚子上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了自己肚子上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_STOMACH, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_STOMACH);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.STOMACH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_STOMACH, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_LEGS = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.LEGS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在[npc2.legs]上";
				}
				return "外射([npc2.legs])";
			}
			return "射在[npc2.legs]上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了[npc2.namePos][npc2.legs]上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.LEGS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.LEGS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.LEGS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.LEGS, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_LEGS = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_LEGS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在自己的[npc.legs]上";
				}
				return "外射(自己的[npc.legs])";
			}
			return "射在自己[npc.legs]上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了自己[npc.legs+]上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_LEGS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_LEGS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.LEGS,
						CoverableArea.THIGHS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_LEGS, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_FEET = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()).hasFeet() && isCumTargetRequirementsMet(this, OrgasmCumTarget.FEET);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在[npc2.feet]上";
				}
				return "外射([npc2.feet])";
			}
			return "射在[npc2.feet]上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了[npc2.namePos][npc2.feet+]上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FEET, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.FEET);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.FEET);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FEET, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_FEET = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasFeet() && isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_FEET);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在自己的[npc.feet]上";
				}
				return "外射(自己的[npc.feet])";
			}
			return "射在自己[npc.feet]上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了自己[npc.feet+]上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FEET, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_FEET);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.FEET);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FEET, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_BACK = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.BACK);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在后背上";
				}
				return "外射(后背)";
			}
			return "射在后背上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了[npc2.namePos]背上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BACK, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.BACK);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.BACK);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BACK, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_ARMPITS = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.isArmpitContentEnabled() && isCumTargetRequirementsMet(this, OrgasmCumTarget.ARMPITS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "手交后射在腋窝上";
				}
				return "外射(腋窝)";
			}
			return "射在腋窝上";
		}

		@Override
		public String getActionDescription() {
			return "你达到了极限，再也无法承受快感的冲击。你直接射在了[npc2.namePos]的腋窝上。";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ARMPITS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.ARMPITS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.ARMPITS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ARMPITS, false).isEndsSex();
		}
	};
	
	
	
	// PREPARATIONS:

	public static final SexAction GENERIC_PREPARATION_PREPARE_IMMOBILE_SILENCE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isPerformingCharacterTotallyImmobilised();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isAsleep()) {
				return "好困……";
			} else {
				return "保持不动";
			}
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isAsleep()) {
				return "[npc2.NameIsFull]马上就要高潮了，但你睡得太沉，[npc2.her]的高潮不可能将你唤醒……";
			} else {
				return "你能感觉到[npc2.name]马上就要高潮了。保持静止，不要做出任何反应。";
			}
		}
		@Override
		public String getDescription() {
			String description = "";
			if(Main.sex.getCharacterPerformingAction().isAsleep()) {
				description = "在[npc2.name]准备达到高潮时，[npc.name]在熟睡，没有醒来的迹象。";
			} else {
				description = "作为性爱玩偶，[npc.Name]能感觉到[npc2.nameIsFull]马上就要高潮了，不过也不会有任何动作。";
			}
			GameCharacter orgasming = Main.sex.getCharacterOrgasming();
			if(orgasming==null) {
				orgasming = Main.sex.getCharacterTargetedForSexAction(this);
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), orgasming, description);
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& !isPerformingCharacterTotallyImmobilised();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW;
		}
		@Override
		public String getActionTitle() {
			return "准备";
		}
		@Override
		public String getActionDescription() {
			return "你能感觉到[npc2.name]马上就要高潮了。做好准备。";
		}
		@Override
		public String getDescription() {
			String description = "";
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					description = "[npc.Name]发出轻柔的[npc.moan]，催动着氛围，准备让[npc2.name]高潮。";
					break;
				case DOM_NORMAL:
					description = "[npc.Name]发出[npc.a_moan+]，准备迎接[npc2.name]高潮的到来。";
					break;
				case DOM_ROUGH:
					description = "[npc.Name]发出[npc.a_moan+]，准备迎接[npc2.name]高潮的到来。";
					break;
				case SUB_EAGER:
					description = "[npc.Name]发出[npc.a_moan+]，准备迎接[npc2.name]高潮的到来。";
					break;
				case SUB_NORMAL:
					description = "[npc.Name]发出[npc.a_moan+]，准备迎接[npc2.name]高潮的到来。";
					break;
				case SUB_RESISTING:
					if(Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
						description = "并不像[npc.Name]看到那样，[npc.Name]发出[npc.a_moan+]时，意识到[npc2.nameIs]要高潮了。";
					} else {
						description = "[npc.Name]十分绝望，想要推开即将高潮的[npc2.name]，却只能发出[npc.a_moan+]。";
					}
					break;
			}
			GameCharacter orgasming = Main.sex.getCharacterOrgasming();
			if(orgasming==null) {
				orgasming = Main.sex.getCharacterTargetedForSexAction(this);
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), orgasming, description);
		}
	};
	
	private static boolean isAreaFuckedByTarget(SexAction sexAction, GameCharacter characterFucked, SexAreaInterface areaFucked) {
		return Main.sex.getAllOngoingSexAreas(characterFucked, areaFucked).contains(SexAreaPenetration.PENIS)
				&& Main.sex.getCharacterOngoingSexArea(characterFucked, areaFucked).contains(Main.sex.getCharacterTargetedForSexAction(sexAction));
	}
	
	private static boolean isSpecialCreampieLockConditionMet(SexAction sexAction, GameCharacter characterProvidingCreampie, GameCharacter characterReceivingCreampie, SexAreaInterface areaFucked) {
		//Do not allow if sex manager has special pull out conditions:
		if(Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(characterProvidingCreampie)==OrgasmBehaviour.PULL_OUT) {
			return false;
		}
		if(!Main.sex.getInitialSexManager().isForceCreampieAllowed(characterProvidingCreampie, characterReceivingCreampie)) {
			return false;
		}
		if(Main.sex.isCharacterImmobilised(characterReceivingCreampie)) {
			return false;
		}
		if(areaFucked==SexAreaOrifice.MOUTH) {
			return (PenisMouth.getPrimaryBlowjobPerformer(characterProvidingCreampie).equals(characterReceivingCreampie));
		}
		if(areaFucked==SexAreaOrifice.VAGINA) {
			if(!characterReceivingCreampie.isPlayer() && characterReceivingCreampie.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
				return false;
			}
		}
		return true;
	}
	
	public static final SexAction GENERIC_PREPARATION_ASK_FOR_CREAMPIE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}
		
		@Override
		public String getActionTitle() {
			if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return "接着操";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
				return "请求射精";

			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
				return "请求射在[npc.breasts]上";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
				return "请求射在[npc.feet]上";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
				return "请求射在[npc.armpit]上";
				
			} else {
				return "请求内射";
			}
		}

		@Override
		public String getActionDescription() {
			if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return "你察觉到，[npc2.name]要高潮了。让[npc2.herHim]高潮的时候继续用假屌干你。";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
				return "你察觉到[npc2.name]要高潮了。让[npc2.herHim]通通射在你的肚子里。";

			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
				return "你察觉到[npc2.name]快高潮了。让[npc2.herHim]通通射在你那[pc.breasts+]上。";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
				return "你能感到[npc2.name]快高潮了。让[npc2.herHim]全射在你[pc.feet+]上。";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
				return "你能感到[npc2.name]快高潮了。让[npc2.herHim]全射在你[pc.armpit+]上。";
				
			} else {
				return "你察觉到[npc2.name]要高潮了。让[npc2.herHim]尽管射出来。";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised()
					&& !isTargetedCharacterTotallyImmobilised(this);
		}

		@Override
		public SexActionPriority getPriority() {
			if(getCharacterBeingFucked()==Main.sex.getCharacterPerformingAction()) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				switch(behaviour) {
					case CREAMPIE:
						return SexActionPriority.UNIQUE_MAX;
					case DEFAULT:
					case KNOT:
						break;
					case NO_ENCOURAGE:
					case PULL_OUT:
						return SexActionPriority.LOW;
				}
			}
			
			if((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
					&& !Main.sex.getCharacterPerformingAction().isVisiblyPregnant())
				|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
				return SexActionPriority.NORMAL;
				
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
				if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
					sb.append("尽管无法说话，[npc.name]设法用一系列呜咽声传达[npc.she]想要[npc2.name]继续");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append("耸动着[npc.pussy+]，继续刺激着高潮中的[npc2.name]。");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append("耸动着[npc.asshole+]，继续刺激着高潮中的[npc2.name]。");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append("耸动着[npc.nipple+]，继续刺激着高潮中的[npc2.name]。");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append("耸动着[npc.spinneret+]，继续刺激着高潮中的[npc2.name]。");

					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
						sb.append(" pounding [npc.her] [npc.tussy+] as [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						sb.append("耸动着[npc.breasts+]，继续刺激着高潮中的[npc2.name]。");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						sb.append("耸动着[npc.armpit+]，继续刺激着高潮中的[npc2.name]。");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						sb.append("耸动着[npc.feet+]，继续刺激着高潮中的[npc2.name]。");
						
					} else {
						sb.append("晃动着身体，继续刺激着高潮中的[npc2.name]。");
					}
	
				} else {
					sb.append("尽管无法说话，[npc.name]设法用一系列呜咽声传达[npc.she]想要");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append("[npc2.she]在[npc.pussy+]里射了个爽。");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append("[npc2.she]在[npc.asshole+]里射了个爽。");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append("[npc2.she]在[npc.nipple+]里射了个爽。");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append("[npc2.she]在[npc.spinneret+]里射了个爽。");
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
						sb.append(" [npc2.name] to cum in [npc.her] [npc.tussy+].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						sb.append("[npc2.she]在[npc.breasts+]里射了个爽。");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						sb.append("[npc2.she]在[npc.feet+]间射了个爽。");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						sb.append("[npc2.she]在[npc.armpit+]里射了个爽。");
						
					} else {
						sb.append("[npc2.namePos]的精液。");
					}
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
					sb.append("[npc.name]被操得眼神迷乱，浪叫连连，在淫荡的呻吟间总算艰难地组织语言，大喊着求[npc2.name]继续，");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append("耸动着[npc.pussy+]，继续刺激着高潮中的[npc2.name]，[npc.speech(好爽！就是这样！用力操我！)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append("耸动着[npc.asshole+]，继续刺激着高潮中的[npc2.name]，[npc.speech(好爽！就是这样！用力操我的屁眼！)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append("耸动着[npc.nipple+]，继续刺激着高潮中的[npc2.name]，[npc.speech(好爽！就是这样！用力操我的乳穴！)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append("耸动着[npc.spinneret+]，继续刺激着高潮中的[npc2.name]，[npc.speech(好爽！就是这样！用力操我的丝穴！)]");
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
						sb.append(" pounding [npc.her] [npc.tussy+] as [npc2.she] [npc2.verb(orgasm)], [npc.speech(Fuck! Yes! Keep fucking my tussy!)]");

					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						sb.append("耸动着[npc.breasts+]，继续刺激着高潮中的[npc2.name]，[npc.speech(好爽！就是这样！用力操我的奶子！)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						sb.append("耸动着[npc.pussy+]，继续刺激着高潮中的[npc2.name]，[npc.speech(好爽！就是这样！用力操我的[npc.feet]！)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						sb.append("耸动着[npc.armpit+]，继续刺激着高潮中的[npc2.name]，[npc.speech(好爽！就是这样！用力操我的腋穴！)]");
						
					} else {
						sb.append("耸动着身体，继续刺激着高潮中的[npc2.name]，[npc.speech(好爽！就是这样！用力操我！)]");
					}
	
				} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					sb.append("[npc.name]被操得眼神迷乱，浪叫连连，在淫荡的呻吟间总算艰难地组织语言，大喊着求[npc2.name]射进来，");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append("[npc.speech(别拔出去了"+(petName?"，[#npc.getPetName(npc2)]":"")+"！你能填满这个套吗！)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append("[npc.speech(别拔出去了"+(petName?"，[#npc.getPetName(npc2)]":"")+"！你能填满这个套吗！)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append("[npc.speech(别拔出去了"+(petName?"，[#npc.getPetName(npc2)]":"")+"！你能填满这个套吗！)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append("[npc.speech(别拔出去了"+(petName?"，[#npc.getPetName(npc2)]":"")+"！你能填满这个套吗！)]");
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
						sb.append(" [npc.speech(Finish inside of me if you want"+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");

					} else {
						sb.append("[npc.speech(来！射给我吧"+(petName?"，[#npc.getPetName(npc2)]":"")+"！全喷进套套里吧！)]");
					}
					
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					sb.append("在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，狂叫着求[npc2.name]射出来，");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(((Main.sex.getCharacterPerformingAction().isVisiblyPregnant() || !Main.sex.getCharacterPerformingAction().isImpregnationPhysicallyPossible())
										?"[npc.speech(操啊！射在我里面"+(petName?"，[#npc.getPetName(npc2)]":"")+"！我好想要你的精液！)]"
										:"[npc.speech(给我授种吧"+(petName?"，[#npc.getPetName(npc2)]":"")+"！射在我里面！我好想要你的精液！)]"));
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" [npc.speech(操啊！射我里面"+(petName?", [#npc.getPetName(npc2)]":"")+"！好想要你的精液！)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" [npc.speech(操啊！射我里面"+(petName?", [#npc.getPetName(npc2)]":"")+"！好想要你的精液！)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" [npc.speech(操啊！射我里面"+(petName?", [#npc.getPetName(npc2)]":"")+"！好想要你的精液！)]");
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
						sb.append(" [npc.speech(Fuck! Cum in me"+(petName?", [#npc.getPetName(npc2)]":"")+"! I need your cum!)]");

					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						sb.append(" [npc.speech(射出来！"+(petName?"，[#npc.getPetName(npc2)]":"")+"！射得我满奶子都是！)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						sb.append("[npc.speech(操！太棒了！全射到我的[npc.feet]上吧"+(petName?"，[#npc.getPetName(npc2)]":"")+"！)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
						sb.append(" [npc.speech(射出来！"+(petName?"，[#npc.getPetName(npc2)]":"")+"！让我尝尝！)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						sb.append("[npc.speech(操！太棒了！全射到我的[npc.armpit]上吧"+(petName?"，[#npc.getPetName(npc2)]":"")+"！)]");
						
					} else {
						sb.append("[npc.speech(射给我吧"+(petName?"，[#npc.getPetName(npc2)]":"")+"！别拔出去哦！)]");
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+"[npc2.name]听到[npc.Name]的问题，笑了笑，发出愉悦的[npc2.moan]，让[npc.Name]明白，这正中下怀。");
						
					} else {
						sb.append("<br/><br/>"
								+"[npc2.her]听到[npc.Name]这么问，皱起眉头，"
								+ "[npc2.name]发出否定的[npc2.moan]，让[npc.Name]明白，[npc2.name]根本不想听[npc.sheHasFull]说这些。");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"[npc2.name]听到[npc.Name]的问题，笑了笑，迅速积极地回答，",
									"在听清[npc.nameIs]问[npc2.herHim]后发出了[npc.a_moan+]，[npc2.name]回应道，"));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(好吧，如你所愿！)]",
								"[npc2.speech(感觉很适合我呢！)]",
								"[npc2.speech(没问题呀！我会喜欢上这个的！)]"));
						
					} else {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"[npc2.name]听到[npc.Name]问这个，皱起眉头让[npc.herHim]闭嘴，",
									"[npc2.name]很显然不想听[npc.NameIs]问这些关于[npc2.herHim]的事，便回复说，"));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(我会为所欲为哦！)]",
								"[npc2.speech(我才不听你的！)]",
								"[npc2.speech(我会为所欲为哦！)]"));
					}
				}
			}
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.getCharactersRequestingCreampie().add(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if ((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterPerformingAction()))
					|| (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(SexAreaPenetration.PENIS)
							&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(Main.sex.getCharacterPerformingAction()))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_BREASTS_SELF);
				} else {
					fetishes.add(Fetish.FETISH_BREASTS_OTHERS);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_FOOT_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_FOOT_RECEIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ARMPIT_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ARMPIT_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	

	public static final SexAction GENERIC_PREPARATION_ASK_FOR_KNOT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}
		
		@Override
		public String getActionTitle() {
			return "请求锁结";
		}

		@Override
		public String getActionDescription() {
			return "你察觉到[npc2.name]要高潮了。让[npc2.herHim]锁结，然后灌满。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisModifier(PenetrationModifier.KNOTTED)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !Collections.disjoint(
							Util.newArrayListOfValues(
									SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.SPINNERET, SexAreaOrifice.TUSSY, SexAreaOrifice.NIPPLE, SexAreaOrifice.NIPPLE_CROTCH, SexAreaOrifice.URETHRA_PENIS, SexAreaOrifice.URETHRA_VAGINA),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()))
					&& !isPerformingCharacterTotallyImmobilised()
					&& !isTargetedCharacterTotallyImmobilised(this);
		}

		@Override
		public SexActionPriority getPriority() {
			if(getCharacterBeingFucked()==Main.sex.getCharacterPerformingAction()) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				switch(behaviour) {
					case KNOT:
						return SexActionPriority.UNIQUE_MAX;
					case DEFAULT:
					case CREAMPIE:
						break;
					case NO_ENCOURAGE:
					case PULL_OUT:
						return SexActionPriority.LOW;
				}
			}
			
			if((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
					&& !Main.sex.getCharacterPerformingAction().isVisiblyPregnant())
				|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
				return SexActionPriority.NORMAL;
				
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
				sb.append("尽管[npc.name]无法说话，但[npc.she]仍设法用一系列呜咽声传达[npc.she]想要被[npc2.name]锁结并射进");
				
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					sb.append("[npc.her][npc.pussy+]里。");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					sb.append("[npc.her][npc.asshole+]里。");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					sb.append("[npc.her][npc.nipple+]里。");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH)) {
					sb.append("[npc.her][npc.nipple+]里。");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					sb.append("[npc.her][npc.spinneret+]里。");
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					sb.append(" [npc.her] [npc.tussy+].");

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					sb.append("[npc.her][npc.urethraPenis+]里。");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					sb.append("[npc.her][npc.urethraVagina+]里。");
					
				} else {
					sb.append("[npc.herHim]体内");
				}
				
			} else {
				if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					sb.append("[npc.name]被操得眼神迷乱，浪叫连连，在淫荡的呻吟间总算艰难地组织语言，大喊着求[npc2.name]在体内成结然后射进");
					
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append("[npc.her][npc.pussy+]，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！就在我的[npc.pussy]里射出来，你能填满这个套吗？)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append("[npc.her][npc.asshole+]，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！就在我的屁股里射出来，你能填满这个套吗？)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append("[npc.her][npc.nipple+]，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！就在我的乳头里射出来，你能填满这个套吗？)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH)) {
						sb.append("[npc.her][npc.nippleCrotch+]，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！就在我这射出来，你能填满这个套吗？)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append("[npc.her][npc.spinneret+]，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！就在我的丝穴里射出来，你能填满这个套吗？)]");
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
						sb.append(" [npc.her] [npc.tussy+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside me and fill up that condom!)]");

					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						sb.append("[npc.her][npc.urethraPenis+]，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！就在我的尿道里射出来，你能填满这个套吗？)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						sb.append("[npc.her][npc.urethraVagina+]，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！就在我的尿道里射出来，你能填满这个套吗？)]");
	
					} else {
						sb.append("[npc.herHim]体内，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！就在里面射出来，你能填满这个套吗？)]");
					}
					
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}
					
					sb.append("[npc.name]被操得眼神迷乱，浪叫连连，在淫荡的呻吟间总算艰难地组织语言，大喊着求[npc2.name]在体内成结然后射进");
					
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append("[npc.her][npc.pussy+]，"
									+(Main.sex.getCharacterPerformingAction().isVisiblyPregnant()
											?"[npc.speech(锁结我"+(petName?"，[#npc.getPetName(npc2)]":"")+"！然后灌满我的小穴！)]"
											:"[npc.speech(锁结我"+(petName?"，[#npc.getPetName(npc2)]":"")+"！然后灌满我的小穴！让我怀上你的崽！)]"));
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append("[npc.her][npc.asshole+]，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！灌满我的屁眼！)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append("[npc.her][npc.nipple+]，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！灌满我的乳头小穴！)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH)) {
						sb.append("[npc.her][npc.nippleCrotch+]，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！就在这射出来！)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append("[npc.her][npc.spinneret+]，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！就在这射出来！)]");
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
						sb.append(" [npc.her] [npc.tussy+], [npc.speech(Knot me"+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside me!)]");

					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						sb.append("[npc.her][npc.urethraPenis+]，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！就在这射出来！)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						sb.append("[npc.her][npc.urethraVagina+]，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！就在这射出来！)]");
	
					} else {
						sb.append("[npc.herHim]体内，[npc.speech(让我感受你的结"+(petName?"，[#npc.getPetName(npc2)]":"")+"！就在这射出来！)]");
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+"[npc2.name]听到[npc.Name]的问题，笑了笑，发出愉悦的[npc2.moan]，让[npc.Name]明白，这正中下怀。");
						
					} else {
						sb.append("<br/><br/>"
								+"[npc2.her]听到[npc.Name]这么问，皱起眉头，"
								+ "[npc2.name]发出否定的[npc2.moan]，让[npc.Name]明白，[npc2.name]根本不想听[npc.sheHasFull]说这些。");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"[npc2.name]听到[npc.Name]的问题，笑了笑，迅速积极地回答，",
									"在听清[npc.nameIs]问[npc2.herHim]后发出了[npc.a_moan+]，[npc2.name]回应道，"));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(好吧，如你所愿！)]",
								"[npc2.speech(感觉很适合我呢！)]",
								"[npc2.speech(没问题呀！我会喜欢上这个的！)]"));
						
					} else {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"[npc2.name]听到[npc.Name]问这个，皱起眉头让[npc.herHim]闭嘴，",
									"[npc2.name]很显然不想听[npc.NameIs]问这些关于[npc2.herHim]的事，便回复说，"));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(我会为所欲为哦！)]",
								"[npc2.speech(我才不听你的！)]",
								"[npc2.speech(我会为所欲为哦！)]"));
					}
				}
			}
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.getCharactersRequestingKnot().add(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if ((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterPerformingAction()))
					|| (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(SexAreaPenetration.PENIS)
							&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(Main.sex.getCharacterPerformingAction()))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_BREASTS_SELF);
				} else {
					fetishes.add(Fetish.FETISH_BREASTS_OTHERS);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_FOOT_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_FOOT_RECEIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ARMPIT_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ARMPIT_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	private static void applyBasePenisOrgasmRequestsReset() {
		Main.sex.getCharactersRequestingCreampie().remove(Main.sex.getCharacterPerformingAction());
		Main.sex.getCharactersRequestingKnot().remove(Main.sex.getCharacterPerformingAction());
		Main.sex.getCharactersRequestingPullout().remove(Main.sex.getCharacterPerformingAction());
	}
	
	private static SexActionPriority getBaseForceCreampiePriority(SexActionInterface sexAction) {
		if((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
				&& Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(sexAction)))) {
			if(!Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()) {
					return SexActionPriority.HIGH;
				}
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					return SexActionPriority.LOW;
				}
			}
		}
		
		if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
			return SexActionPriority.HIGH;
			
		} else {
			return SexActionPriority.LOW;
		}
	}
	
	private static String getForcedCreampieSpeech(SexAction sexAction) {
		boolean knowsName = (!Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getCharacterPerformingAction().isPlayerKnowsName())
							|| (!Main.sex.getCharacterTargetedForSexAction(sexAction).isPlayer() && Main.sex.getCharacterTargetedForSexAction(sexAction).isPlayerKnowsName());
		
		boolean performerSpeechMuffled = Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute();
		
		if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(sexAction))) {
			if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
					return (performerSpeechMuffled
								?"[npc.she]嘴被堵住了，只能发出模糊不清的[npc.moan]，要准备好被内射了。"
								:"伴随着歇斯底里的尖叫，[npc.she]大叫着：[npc.speechNoExtraEffects(~啊！~我抓住你了！射在我"+(knowsName?"里，[npc2.name]":"里面")+"！哦！~ ~啊！~给我你的子种们！)]");
				}
				return (performerSpeechMuffled
								?"[npc.she]嘴被堵住了，只能发出模糊不清的[npc.moan]，要准备好被内射了。"
								:"[npc.she]发出迷乱的[npc.moan]，尖叫道，[npc.speechNoExtraEffects(~嗯~啊~射到我的小穴里！"+(knowsName?"，[npc2.name]":"")+"！啊！用你的精液喂饱我！)]");

			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
				return (performerSpeechMuffled
								?"[npc.she]嘴被堵住了，只能发出模糊不清的[npc.moan]，要准备好被内射了。"
								:"[npc.she]发出迷乱的[npc.moan]，尖叫道，[npc.speechNoExtraEffects(~嗯~啊~射到我的屁眼里！"+(knowsName?"，[npc2.name]":"")+"！啊！用你的精液喂饱我！)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
				return (performerSpeechMuffled
							?"[npc.she]嘴被堵住了，只能发出模糊不清的[npc.moan]，要准备好被内射了。"
							:"[npc.she]发出迷乱的[npc.moan]，尖叫道，[npc.speechNoExtraEffects(~嗯！~啊！~射到我的丝囊里！"+(knowsName?"，[npc2.name]":"")+"！啊！用你的精液喂饱我！)]");
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
				return (performerSpeechMuffled
							?"[npc.she]嘴被堵住了，只能发出模糊不清的[npc.moan]，要准备好被内射了。"
							:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoExtraEffects(~Ooh!~ ~Yes!~ Cum in my tussy"+(knowsName?", [npc2.name]":"")+"! ~Aah!~ Give me a nice big creampie!)]");

			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
				return (performerSpeechMuffled
								?"[npc.she]嘴被堵住了，只能发出模糊不清的[npc.moan]，要准备好被内射了。"
								:"[npc.she]发出迷乱的[npc.moan]，尖叫道，[npc.speechNoExtraEffects(~嗯~啊~射出来！"+(knowsName?"，[npc2.name]":"")+"！啊！用你的精液填满我的蛋蛋！)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
				return (performerSpeechMuffled
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(prepare)] to receive [npc.her] creampie."
								:"[npc.she]发出迷乱的[npc.moan]，尖叫道，[npc.speechNoExtraEffects(~嗯~啊~射出来！"+(knowsName?"，[npc2.name]":"")+"！啊！用你的精液喂饱我！)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
				return (performerSpeechMuffled
							?"[npc.she]嘴被堵住了，只能发出模糊不清的[npc.moan]，要准备好被[npc2.name]射在[npc.her]的阴茎上了。"
							:"[npc.she]发出迷乱的[npc.moan]，尖叫道，[npc.speechNoExtraEffects(就是这样！~嗯！~射满我的鸡巴"+(knowsName?"，[npc2.name]":"")+"！)]");
		
			}
			
		} else { // Dildo:
			if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
				return (performerSpeechMuffled
								?"[npc.she]嘴被堵住了，只能发出模糊不清的[npc.moan]，要准备好被[npc2.namePos]的玩具填满了。"
								:"[npc.she]发出迷乱的[npc.moan]，尖叫道，[npc.speechNoExtraEffects(嗯~啊~把它狠狠地塞到我的小穴里"+(knowsName?"，[npc2.name]":"")+"！)]");

			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
				return (performerSpeechMuffled
								?"[npc.she]嘴被堵住了，只能发出模糊不清的[npc.moan]，要准备好被[npc2.namePos]的玩具填满了。"
								:"[npc.she]发出迷乱的[npc.moan]，尖叫道，[npc.speechNoExtraEffects(嗯~啊~把它狠狠地塞到我的屁眼里"+(knowsName?"，[npc2.name]":"")+"！)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
				return (performerSpeechMuffled
						?"[npc.she]嘴被堵住了，只能发出模糊不清的[npc.moan]，要准备好被[npc2.namePos]的玩具填满了。"
						:"[npc.she]发出迷乱的[npc.moan]，尖叫道，[npc.speechNoExtraEffects(嗯~啊~把它狠狠地塞到我的丝囊里"+(knowsName?"，[npc2.name]":"")+"！)]");
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
				return (performerSpeechMuffled
						?"[npc.she]嘴被堵住了，只能发出模糊不清的[npc.moan]，要准备好被[npc2.namePos]的玩具填满了。"
						:" With a desperate [npc.moan], [npc.she] [npc.verb(cry)] out, [npc.speechNoExtraEffects(~Ooh!~ ~Yes!~ Fill my tussy with that toy"+(knowsName?", [npc2.name]":"")+"!)]");

			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
				return (performerSpeechMuffled
								?"[npc.she]嘴被堵住了，只能发出模糊不清的[npc.moan]，要准备好被[npc2.namePos]的玩具填满了。"
								:"[npc.she]发出迷乱的[npc.moan]，尖叫道，[npc.speechNoExtraEffects(嗯~啊~把它狠狠地塞到我的鸡巴里"+(knowsName?"，[npc2.name]":"")+"！)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
				return (performerSpeechMuffled
								?" With [npc.her] mouth being blocked, [npc.sheIs] only able to make a very muffled [npc.moan] as [npc.she] [npc.verb(feel)] [npc.herself] being filled by [npc2.namePos] toy."
								:"[npc.she]发出迷乱的[npc.moan]，尖叫道，[npc.speechNoExtraEffects(嗯~啊~把它狠狠地塞到进来"+(knowsName?"，[npc2.name]":"")+"！)]");
			}
		}
		
		return "";
	}
	
	
	public static final SexAction GENERIC_PREPARATION_FORCE_CREAMPIE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		private boolean isFrottingOrgasm() {
			return isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			if(isFrottingOrgasm()) {
				return "强制磨枪高潮";
			}
			return "强制内射";
		}

		@Override
		public String getActionDescription() {
			if(isFrottingOrgasm()) {
				return "你能感觉到[npc2.name]快高潮了。凭借你有利的位置强迫[npc2.herHim]在你[pc.cock+]上射精。";
			}
			return "你能感觉到[npc2.name]快高潮了。凭借你有利的位置强迫[npc2.herHim]在你体内射精。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			boolean frottingOrgasm = isFrottingOrgasm();
			
			boolean allowed = (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas)
						|| frottingOrgasm)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Torso.class,
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised();
			
//			System.out.println(allowed+":");
//			System.out.println("1: "+(Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()));
//			
//			try {
//				System.out.println("2: "+isSpecialCreampieLockConditionMet(
//						this,
//						Main.sex.getCharacterTargetedForSexAction(this),
//						Main.sex.getCharacterPerformingAction(),
//						Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0)));
//	
//				System.out.println("3: "+Main.sex.getPosition().isForcedCreampieEnabled(
//						Torso.class,
//						Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
//						Main.sex.getCharacterPerformingAction(),
//						Main.sex.getCharacterTargetedForSexAction(this)));
//			} catch(Exception ex) {
//			}
//			System.out.println("4: "+(Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING));
			
			return allowed;
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫[npc2.namePos][npc2.cock+]深深插入[npc.her][npc.pussy+]。"
								+ getForcedCreampieSpeech(this);
					}
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫[npc2.her][npc2.cock+]深深插入[npc.her][npc.pussy+]。"
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫[npc2.her][npc2.cock+]深深插入[npc.her][npc.asshole+]。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫[npc2.her][npc2.cock+]深深插入[npc.her]鸡巴的尿道。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫[npc2.her][npc2.cock+]深深插入[npc.her]淫穴的尿道。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫[npc2.her][npc2.cock+]深深插入[npc.her]的丝囊穴。"
							+ getForcedCreampieSpeech(this);
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] [npc.tussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫[npc2.her][npc2.cock+]深深插入[npc.her]的喉咙。"
							+ "[npc.she]狂乱且咕噜噜地[npc.moan]着，准备吞下即将射进体内的所有精液。";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫自己[npc.cock+]压向[npc2.namePos]的阴茎。"
							+ getForcedCreampieSpeech(this);
				}
				return "错误：强制内射未计算在内。请联系Innoxia！";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫[npc2.her][npc2.cock+]深深插入[npc.her][npc.pussy+]。"
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫[npc2.her][npc2.cock+]深深插入[npc.her][npc.asshole+]。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫[npc2.her][npc2.cock+]深深插入[npc.her]鸡巴的尿道。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫[npc2.her][npc2.cock+]深深插入[npc.her]淫穴的尿道。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫[npc2.her][npc2.cock+]深深插入[npc.her]的丝囊穴。"
							+ getForcedCreampieSpeech(this);
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] [npc.verb(use)] [npc.her] advantageous position to force [npc2.her] [npc2.cock+] deep into [npc.her] [npc.tussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]用[npc.her]有利的位置强迫[npc2.her][npc2.cock+]深深插入[npc.her]的喉咙。";
				}
				return "错误：强制内射未计入。请联系Innoxia！";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Torso.class));
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_HUG_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "抱锁";
		}

		@Override
		public String getActionDescription() {
			return "你能感觉到[npc2.name]快高潮了。你迅速用[npc.arms]环住[npc2.her]的下半身，紧紧地把[npc2.herHim]抱向你，强迫[npc2.herHim]在你体内射精。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& !Main.sex.getCharacterPerformingAction().isArmMovementHindered()
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Arm.class,
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised();
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
								+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
								+ getForcedCreampieSpeech(this);
					}
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
								+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
								+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
								+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]深深插入[npc.her][npc.asshole+]。"
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
								+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]深深插入[npc.her]的阴茎尿道。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
							+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]深深插入[npc.her]的小穴尿道。"
						+ getForcedCreampieSpeech(this);
				
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
							+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]深深插入[npc.her]的丝囊。"
						+ getForcedCreampieSpeech(this);
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
							+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.tussy+]."
						+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
								+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]深深顶入[npc.her]的喉咙。"
							+ "[npc.she]狂乱且咕噜噜地[npc.moan]着，准备吞下即将射进体内的所有精液。";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
							+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]抵住[npc.hers]的阴茎。"
							+ getForcedCreampieSpeech(this);
				}
				return "错误：抱锁区域未计算在内。请联系Innoxia！";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
							+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
							+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]深深插入[npc.her][npc.asshole+]。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
							+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]深深插入[npc.her]的阴茎尿道。"
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
							+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]深深插入[npc.her]的小穴尿道。"
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.arms]抱住[npc2.her]的下背部。"
							+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]深深插入[npc.her]的丝囊。"
						+ getForcedCreampieSpeech(this);
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
							+ " and, tightly hugging [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.tussy]."
						+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.her] lower back,"
								+ "然后，紧紧搂住[npc2.herHim]，强迫[npc2.cock+]深深顶入[npc.her]的喉咙。";
				}
				return "错误：抱锁区域未计算在内。请联系Innoxia！";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Arm.class));
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_LEG_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "叉腿禁锢";
		}

		@Override
		public String getActionDescription() {
			return "你能感到[npc2.name]快高潮了。你迅速用[npc.legs]夹住[npc2.herHim]，强迫[npc2.herHim]在你体内射精。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& !Main.sex.getCharacterPerformingAction().isLegMovementHindered()
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)>=2
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Leg.class,
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised();
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "[npc.name]注意到[npc2.nameIsFull]要射精了，便用[npc.legs]快速抱住[npc2.her]的下背部。"
								+ "强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
								+ getForcedCreampieSpeech(this);
					}
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便用[npc.legs]快速抱住[npc2.her]的下背部。"
								+ "强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
								+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便用[npc.legs]快速抱住[npc2.her]的下背部。"
								+ "强迫[npc2.cock+]深深插入[npc.her][npc.asshole+]。"
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便用[npc.legs]快速抱住[npc2.her]的下背部。"
								+ "强迫[npc2.cock+]深深插入[npc.her]的阴茎尿道。"
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便用[npc.legs]快速抱住[npc2.her]的下背部。"
								+ "强迫[npc2.cock+]深深插入[npc.her]的小穴尿道。"
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便用[npc.legs]快速抱住[npc2.her]的下背部。"
							+ "强迫[npc2.cock+]深深插入[npc.her]的丝囊。"
							+ getForcedCreampieSpeech(this);
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便用[npc.legs]快速抱住[npc2.her]的下背部。"
							+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] [npc.tussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) { // This shouldn't really ever be encountered:
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便用[npc.legs]快速锁住[npc2.her]的下背部，发出一阵渴望、沉闷的[npc.moan]。";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
							+ "强迫[npc2.her][npc2.cock+]抵住[npc.hers]的阴茎。"
							+ getForcedCreampieSpeech(this);
				}
				return "错误：腿锁区域未计算在内。请联系Innoxia！";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便迅速用[npc.legs]锁住[npc2.her]的下背部，"
								+ "强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便迅速用[npc.legs]锁住[npc2.her]的下背部，"
								+ "强迫[npc2.cock+]深深插入[npc.her][npc.asshole+]。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便迅速用[npc.legs]锁住[npc2.her]的下背部，"
								+ "强迫[npc2.cock+]深深插入[npc.her]的阴茎尿道。"
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便迅速用[npc.legs]锁住[npc2.her]的下背部，"
								+ "强迫[npc2.cock+]深深插入[npc.her]的小穴尿道。"
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便迅速用[npc.legs]锁住[npc2.her]的下背部，"
							+ "强迫[npc2.cock+]深深插入[npc.her]的丝囊。"
							+ getForcedCreampieSpeech(this);
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to orgasm, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.legs] around [npc2.her] lower back,"
							+ " forcing [npc2.her] [npc2.cock+] deep into [npc.her] [npc.tussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) { // This shouldn't really ever be encountered:
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便用[npc.legs]快速环住[npc2.her]的下背部，发出渴望、沉闷的[npc.moan]。";
				}
				return "错误：腿锁区域未计算在内。请联系Innoxia！";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Leg.class));
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
			} 
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_TAIL_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "尾锁";
		}

		@Override
		public String getActionDescription() {
			return "你能感觉到[npc2.name]马上就要高潮了。"
					+ "你迅速用你灵活可控的[npc.tail]环住对方的下背部，紧紧拉近距离，强迫[npc2.herHim]射在里面。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& Main.sex.getCharacterPerformingAction().hasTail()
					&& Main.sex.getCharacterPerformingAction().getTailType().isPrehensile()
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL)>=1
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Tail.class,
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised();
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
								+ getForcedCreampieSpeech(this);
					}
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.asshole+]。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的阴茎尿道。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的小穴尿道。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的丝囊。"
						+ getForcedCreampieSpeech(this);
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.tussy+]."
						+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的喉咙。"
							+ "[npc.she]狂乱且咕噜噜地[npc.moan]着，准备吞下即将射进体内的所有精液。";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.her][npc2.cock+]抵在[npc.hers]的阴茎上。"
							+ getForcedCreampieSpeech(this);
				}
				return "错误：尾锁区域未计算在内。请联系Innoxia！";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.asshole+]。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的阴茎尿道。"
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的小穴尿道。"
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tail]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的丝囊。"
						+ getForcedCreampieSpeech(this);
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.tussy+]."
						+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tail] around [npc2.her] lower back,"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的喉咙。";
				}
				return "错误：尾锁区域未计算在内。请联系Innoxia！";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Tail.class));
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_WING_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "翼锁";
		}

		@Override
		public String getActionDescription() {
			return "你能感觉到[npc2.name]马上就要高潮了。"
					+ "你迅速用你[npc.wingSize][npc.wings]环住对方的身体，紧紧拉近距离，强迫[npc2.herHim]射在里面。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& Main.sex.getCharacterPerformingAction().hasWings()
					&& Main.sex.getCharacterPerformingAction().getWingSize().getValue()>=WingSize.THREE_LARGE.getValue()
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Wing.class,
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised();
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
								+ getForcedCreampieSpeech(this);
					}
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.asshole+]。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的阴茎尿道。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的小穴尿道。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的丝囊。"
						+ getForcedCreampieSpeech(this);
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.tussy+]."
						+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的喉咙。"
							+ "[npc.she]狂乱且咕噜噜地[npc.moan]着，准备吞下即将射进体内的所有精液。";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.her][npc2.cock+]抵在[npc.hers]的阴茎上。"
							+ getForcedCreampieSpeech(this);
				}
				return "错误：尾锁区域未计算在内。请联系Innoxia！";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.asshole+]。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的阴茎尿道。"
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的小穴尿道。"
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her][npc.wingSize]的[npc.wings]包住[npc2.her]的身体。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的丝囊。"
						+ getForcedCreampieSpeech(this);
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.tussy+]."
						+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] around [npc2.her] body,"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的喉咙。";
				}
				return "错误：尾锁区域未计算在内。请联系Innoxia！";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Wing.class));
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_TENTACLE_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "触手捆绑";
		}

		@Override
		public String getActionDescription() {
			return "你能感觉到[npc2.name]马上就要高潮了。"
					+ "你迅速用[npc.tentacles]环住对方的下背部，紧紧拉近距离，强迫[npc2.herHim]射在里面。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& Main.sex.getCharacterPerformingAction().hasTentacle()
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TENTACLE)>=1
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Tentacle.class,
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised();
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "[npc.name]注意到[npc2.nameIsFull]要射精了，便快速用[npc.tentacles]缠住[npc2.her]的下背部。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
								+ getForcedCreampieSpeech(this);
					}
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tentacles]缠住[npc2.her]的下背部。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便快速用[npc.tentacles]缠住[npc2.her]的下背部。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.asshole+]。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便快速用[npc.tentacles]缠住[npc2.her]的下背部。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的阴茎尿道。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tentacles]缠住[npc2.her]的下背部。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的小穴尿道。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便快速用[npc.tentacles]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的丝囊。"
						+ getForcedCreampieSpeech(this);
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tentacles]缠住[npc2.her]的下背部。"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.tussy+]."
						+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tentacles]缠住[npc2.her]的下背部。"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的喉咙。"
							+ "[npc.she]狂乱且咕噜噜地[npc.moan]着，准备吞下即将射进体内的所有精液。";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便快速用[npc.tentacles]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.her][npc2.cock+]抵在[npc.hers]的阴茎上。"
							+ getForcedCreampieSpeech(this);
				}
				return "错误：尾锁区域未计算在内。请联系Innoxia！";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便快速用[npc.tentacles]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.pussy+]。"
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tentacles]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her][npc.asshole+]。"
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tentacles]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的阴茎尿道。"
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，[npc.she]快速用[npc.her]的[npc.tentacles]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的小穴尿道。"
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "[npc.name]注意到[npc2.nameIsFull]要射精了，便快速用[npc.tentacles]缠住[npc2.her]的下背部。"
							+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的丝囊。"
						+ getForcedCreampieSpeech(this);
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
							+ " and, pulling [npc2.herHim] forwards, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] deep into [npc.her] [npc.tussy+]."
						+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "As [npc.name] [npc.verb(realise)] that [npc2.nameIsFull] about to cum, [npc.she] quickly [npc.verb(wrap)] [npc.her] [npc.tentacles] around [npc2.her] lower back,"
								+ "然后，把[npc2.herHim]向前一推，强迫[npc2.cock+]深深插入[npc.her]的喉咙。";
				}
				return "错误：尾锁区域未计算在内。请联系Innoxia！";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Tentacle.class));
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	
	public static final SexAction GENERIC_PREPARATION_ASK_FOR_PULL_OUT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}
		
		@Override
		public String getActionTitle() {
			return "请求拔出";
		}

		@Override
		public String getActionDescription() {
			if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return "你能感到[npc2.name]快高潮了。让[npc2.herHim]高潮的时候把[npc2.her]的假阳具从你体内抽出去。";
			}
			return "你能感到[npc2.name]快高潮了。让[npc2.herHim]在射精之前从你体内抽出去。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(isPerformingCharacterTotallyImmobilised() || isTargetedCharacterTotallyImmobilised(this)) {
				return false;
			}
			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
				return isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
						&& !Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
						&& !Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
				
			} else {
				if(GENERIC_PREPARATION_ASK_FOR_CREAMPIE.isBaseRequirementsMet()
						&& GENERIC_PREPARATION_ASK_FOR_CREAMPIE.getPriority().getValue()>=this.getPriority().getValue()) {
					return false; // Do not ask for pullout if they have ask for creampie available as well.
				}
				
				return isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
						&& (Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING
							|| ((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
									?!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()
									:true)
							&& (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
									?!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
									:true)));
			}
		}

		@Override
		public SexActionPriority getPriority() {
			if(getCharacterBeingFucked()==Main.sex.getCharacterPerformingAction()) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				switch(behaviour) {
					case DEFAULT:
						break;
					case NO_ENCOURAGE:
					case CREAMPIE:
					case KNOT:
						return SexActionPriority.LOW;
					case PULL_OUT:
						return SexActionPriority.UNIQUE_MAX;
				}
			}
			
			if((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
						&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo())
					&& !Main.sex.getCharacterPerformingAction().isVisiblyPregnant())
				|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()) {
				return SexActionPriority.HIGH;
				
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), null);
		}

		@Override
		public String getDescription() {
			if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING) {
				if(Main.sex.getCharacterPerformingAction().isMute()) {
					if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).isEmpty()) { // Non-orifice penetration
						return "尽管[npc.namePos]无法说话，但[npc.she]痛苦的挣扎清楚地表明了[npc.she]希望[npc2.name]将[npc2.cock+]抽离[npc.herhim]。";
					} else { // Orifice penetration
						SexAreaInterface areaPenetrated = Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0);
						if(areaPenetrated.isOrifice() && ((SexAreaOrifice)areaPenetrated).isInternalOrifice()) {
							return "尽管[npc.namePos]无法说话，但[npc.she]痛苦的挣扎清楚地表明了[npc.she]希望[npc2.name]从[npc.herhim]体内抽离。";
						} else {
							return "尽管[npc.namePos]无法说话，但[npc.she]痛苦的挣扎清楚地表明了[npc.she]希望[npc2.name]将[npc2.cock+]抽离[npc.herhim]。";
						}
					}
					
				} else {
					if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
						if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).isEmpty()) { // Non-orifice penetration
							return "[npc.Name]绝望地挣扎着，试图推开[npc2.name]，[npc.she]哭喊着说，"
									+ "[npc.speech(不要！求你了！快把你的[npc2.cock]拿开！)]";
						} else { // Orifice penetration
							SexAreaInterface areaPenetrated = Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0);
							if(areaPenetrated.isOrifice() && ((SexAreaOrifice)areaPenetrated).isInternalOrifice()) {
								return "[npc.Name]绝望地挣扎着，试图推开[npc2.name]，[npc.she]哭喊着说，"
										+ " [npc.speech(不！求你了！把你的[npc2.cock]拔出去！)]";
								
							} else {
								return "[npc.Name]绝望地挣扎着，试图推开[npc2.name]，[npc.she]哭喊着说，"
										+ "[npc.speech(不要！求你了！快把[npc2.cock]从我的"+areaPenetrated.getName(Main.sex.getCharacterPerformingAction(), true)+"上拿开！)]";
							}
						}
					
					} else {
						if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).isEmpty()) { // Non-orifice penetration
							return "[npc.Name]绝望地挣扎着，试图推开[npc2.name]，[npc.she]哭喊着说，[npc.speech(不！求你了！把你的鸡巴拔出去！)]";
						} else { // Orifice penetration
							SexAreaInterface areaPenetrated = Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0);
							if(areaPenetrated.isOrifice() && ((SexAreaOrifice)areaPenetrated).isInternalOrifice()) {
								if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
									return "[npc.Name]绝望地挣扎着，试图推开[npc2.name]，[npc.she]哭喊着说，"
											+ "[npc.speech(别！求你了！快拔出去！避孕套可能会在里面破掉的！)]";
								} else {
									return "[npc.Name]绝望地挣扎着，试图推开[npc2.name]，[npc.she]哭喊着说，"
											+ "[npc.speech(别！求你了！快从我的"+areaPenetrated.getName(Main.sex.getCharacterPerformingAction(), true)+"里拔出去！别射在里面啊！)]";
								}
							} else {
								return "[npc.Name]绝望地挣扎着，试图推开[npc2.name]，[npc.she]哭喊着说，"
										+"[npc.speech(不要！求你了！快把肉棒从我的"+areaPenetrated.getName(Main.sex.getCharacterPerformingAction(), true)+"上拿开！)]";
							}
						}
					}
				}
			}
			
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
				if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "虽然不能说话，"
								+ "[npc.Name]努力连声呜咽祈求，让[npc2.name]明白[npc.she]不想被射在[npc.pussy+]里。";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "虽然不能说话，"
								+ "[npc.Name]努力连声呜咽祈求，还有动作示意，表示想要[npc2.name]从那[npc.asshole+]里拔出去。";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "虽然不能说话，"
								+ "[npc.Name]努力连声呜咽祈求，还有动作示意，表示想要[npc2.name]从那[npc.nipple+]里拔出去。";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "虽然不能说话，"
								+ "[npc.Name]努力连声呜咽祈求，还有动作示意，表示想要[npc2.name]从那[npc.spinneret+]里拔出去。";
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
						return "虽然不能说话，"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.tussy+].";
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "虽然不能说话，"
								+ "[npc.Name]努力连声呜咽祈求，还有动作示意，表示想要[npc2.name]远离[npc.her]那[npc.breasts+]。";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "虽然不能说话，"
								+ "[npc.Name]努力连声呜咽祈求，还有动作示意，表示想要[npc2.name]远离[npc.her]那[npc.feet+]。";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "虽然不能说话，"
								+ "[npc.Name]努力连声呜咽祈求，还有动作示意，表示想要[npc2.name]远离[npc.her]那[npc.armpit+]。";
						
					} else {
						return "虽然不能说话，"
								+ "[npc.Name]努力连声呜咽祈求，让[npc2.name]明白[npc.she]想要自己高潮前拔出去。";
					}
	
				} else {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "虽然不能说话，"
								+ "[npc.Name]努力连声呜咽祈求，让[npc2.name]明白[npc.she]不想被射在[npc.pussy+]里，要在射之前拔出去。";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "虽然不能说话，"
								+ "[npc.Name]努力连声呜咽祈求，让[npc2.name]明白[npc.she]不想被射在[npc.asshole+]里，要在射之前拔出去。";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "虽然不能说话，"
								+ "[npc.Name]努力连声呜咽祈求，让[npc2.name]明白[npc.she]不想被射在[npc.nipple+]里，要在射之前拔出去。";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "虽然不能说话，"
								+ "[npc.Name]努力连声呜咽祈求，让[npc2.name]明白[npc.she]不想被射在[npc.spinneret+]里，要在射之前拔出去。";
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
						return "虽然不能说话，"
								+ " [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.tussy+] before [npc2.she] [npc2.verb(cum)].";

					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "虽然不能说话，"
								+ "[npc.Name]努力连声呜咽祈求，让[npc2.name]明白[npc.she]不想被射在[npc.breasts+]上，要在射之前挪开。";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Although not able to speak,"
								+ "[npc.Name]努力连声呜咽祈求，让[npc2.name]明白[npc.she]不想被射在[npc.feet+]上，要在射之前挪开。";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "Although not able to speak,"
								+ "[npc.Name]努力连声呜咽祈求，让[npc2.name]明白[npc.she]不想被射在[npc.armpit+]上，要在射之前挪开。";
						
					} else {
						return "虽然不能说话，[npc.Name]努力连声呜咽祈求，还有动作示意，表达[npc.her]不想要[npc2.namePos]的精液。";
					}
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)
							|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+"[npc.speech(快拔出去！把那个假阳具从我身体里拿走！)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+"[npc.speech(快拔出去！把那个假阳具从我身体里拿走！)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+"[npc.speech(快拔出去！把那个假阳具从我身体里拿走！)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+"[npc.speech(快拔出去！把那个假阳具从我身体里拿走！)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+"[npc.speech(快拔出去！把那个假阳具从我身体里拿走！)]";
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+"[npc.speech(Pull out! Get that dildo out of me!)]";

					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+"[npc.speech(快拔出去！把那个假阳具从我身旁拿开！)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+"[npc.speech(把那假阳具从我面前拿开！)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+"[npc.speech(把那假阳具从我面前拿开！)]";
						
					} else {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算哭喊出声，朝着[npc2.namePos]的[npc2.cock]大叫，"
								+ "[npc.speech(快拔出去！把那个假阳具从我嘴里拿走！)]";
					}
					
				} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(快拔出来！我可不想冒风险，我怕套套在小穴里面破掉！)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(快拔出来！我可不想冒风险，我怕套套破掉！)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(快拔出来！我可不想冒风险，我怕套套破掉！)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(快拔出来！我可不想冒风险，我怕套套在屁股里面破掉！)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(快拔出来！我可不想冒风险，我怕套套在乳头里面破掉！)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(快拔出来！我可不想冒风险，我怕套套在丝囊里面破掉！)]";
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(Pull out! I don't want that condom breaking in my tussy!)]";

					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(上一边去！我可不想套套破在奶子上！)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(上一边去！我可不想套套破在[npc.feet]上！)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(上一边去！我可不想套套破在[npc.armpit]上！)]";
						
					} else {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算哭喊出声，朝着[npc2.namePos]的[npc2.cock]大叫，"
								+ "[npc.speech(快拔出去！求你了！)]";
					}
					
				} else {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+(Main.sex.getCharacterPerformingAction().isVisiblyPregnant()
										|| Main.sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.MENOPAUSE)
//										|| !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
									?"[npc.speech(快拔出去！我可不想你射在里面！)]"
									:"[npc.speech(拔出来！我不想怀孕啊！)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(快拔出去！别射在我里面，求你了！)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(快拔出去！别射在我里面，求你了！)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(快拔出去！别射在我屁股里，求你了！)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(快拔出去！别射在我乳头里，求你了！)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(快拔出去！别射在我丝囊里，求你了！)]";
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(Pull out! Don't cum in my [npc.tussy+]! Don't do it!)]";

					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(上一边去！别射在我奶子上！你敢！)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+ "[npc.speech(上一边去！别射在我[npc.feet]上！你敢！)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
								+ "[npc.speech(上一边去！别射在我[npc.armpit]里！你敢！)]";
						
					} else {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算哭喊出声，朝着[npc2.namePos]的[npc2.cock]大叫，"
								+ "[npc.speech(快拔出去！我可不想吃你的精液！)]";
					}
				}
			}
		}
	};

	public static final SexAction GENERIC_PREPARATION_ASK_FOR_FACIAL = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "请求面交";
		}
		@Override
		public String getActionDescription() {
			return "你能感到[npc2.name]快高潮了。让[npc2.herHim]全射在你脸上。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			if(isPerformingCharacterTotallyImmobilised() || isTargetedCharacterTotallyImmobilised(this)) {
				return false;
			}
			if(!Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()) {
				return false;
			}
			boolean cockFaceInteractionAvailable = false;
			try {
				cockFaceInteractionAvailable = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getAvailableCumTargets().contains(OrgasmCumTarget.FACE);
			} catch(Exception ex) {
				// No available penis-mouth actions, so can't reach face
			}
			if(!cockFaceInteractionAvailable) { // Check for reverse if not found:
				try {
					cockFaceInteractionAvailable = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
							.get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
							.getProvidedCumTargets().contains(OrgasmCumTarget.FACE);
				} catch(Exception ex) {
				}
			}
			if(!cockFaceInteractionAvailable || Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
				return false;
			}
			
			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
				return true;
				
			} else { // Do not ask for pullout if they are resisting or want to ask for a creampie.
				if((GENERIC_PREPARATION_ASK_FOR_CREAMPIE.isBaseRequirementsMet() && GENERIC_PREPARATION_ASK_FOR_CREAMPIE.getPriority().getValue()>=this.getPriority().getValue())) {
					return false;
				}
				
				return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative();
			}
		}
		// Just let them use standard behaviour weighting for this one
		@Override
		public SexActionPriority getPriority() {
			if(!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
				return SexActionPriority.LOW;
			} else {
				return super.getPriority();
			}
		}
		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), OrgasmCumTarget.FACE);
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
				return "虽然不能说话，[npc.Name]努力连声呜咽祈求，还有动作示意，[npc.her]想被弄在脸上。";
				
			} else {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+"[npc.speech(快拔出来！全射在我脸上吧！)]";

				} else {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow [npc.verb(manage)] to formulate a sentence as [npc.she] [npc.verb(cry)] out to [npc2.name], "
							+ "[npc.speech(我想你射在我脸上！)]";
				}
			}
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD);
			}
		}
	};
	
	public static final SexAction PLAYER_PREPARATION_ASK_FOR_NOTHING = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "无请求";
		}

		@Override
		public String getActionDescription() {
			return "你能感觉到[npc2.name]快高潮了。不对[npc2.herHim]提出任何要求，看看[npc2.sheHasFull]打算对你怎么做。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Main.game.getPlayer(), Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !isPerformingCharacterTotallyImmobilised();
					//&& !isTargetedCharacterTotallyImmobilised(this);
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
		}

		@Override
		public String getDescription() {
			if(!isRealPenisFuckingCharacter(Main.game.getPlayer(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "你继续[npc.moaning+]，感受着[npc2.namePos][npc2.cock+]在你[npc.pussy+]进进出出，你想知道[npc2.she]会抽出来，还是会继续操你直到[npc2.her]高潮。";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "你继续[npc.moaning+]，感受着[npc2.namePos][npc2.cock+]在你[npc.asshole+]进进出出，你想知道[npc2.she]会抽出来，还是会继续操你直到[npc2.her]高潮。";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "你继续[npc.moaning+]，感受着[npc2.namePos][npc2.cock+]在你[npc.nipple+]进进出出，你想知道[npc2.she]会抽出来，还是会继续操你直到[npc2.her]高潮。";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "你继续[npc.moaning+]，感受着[npc2.namePos][npc2.cock+]在你[npc.spinneret+]进进出出，你想知道[npc2.she]会抽出来，还是会继续操你直到[npc2.her]高潮。";
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "You continue [npc.moaning+] as [npc2.namePos] [npc2.cock+] continues to slam in and out of your [npc.tussy+], and you wonder if [npc2.sheIs] going to pull out or carry on fucking you through [npc2.her] orgasm.";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
						return "你继续[npc.moaning+]，感受着[npc2.namePos][npc2.cock+]在你[npc.breasts+]间进进出出，"
								+"接着你开始想象[npc2.her]在高潮时继续操你[npc.breasts+]";
					} else {
						return "你继续[npc.moaning+]，感受着[npc2.namePos][npc2.cock+]在你躯干上来回磨蹭，"
								+ "接着你开始想象[npc2.her]在高潮时继续操你的平胸";
					}
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "你一边[npc.moaning+]，一边继续给[npc.Name][pc.a_footjob]，你不太确定[npc2.sheIs]是会退缩，还是会在高潮时继续操你的[npc.feet]。";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
					return "你继续[npc.moaning+]，感受着[npc2.Name]操着你[pc.armpit+]，你想知道[npc2.she]会抽出来，还是会在高潮时继续操你的腋窝。";
					
				} else {
					return "你继续[npc.moaning+]，感受着[npc2.name]继续用[npc2.her][npc2.cock+]操你，你想知道[npc2.she]会抽出来，还是会继续操你直到[npc2.her]高潮。";
				}
				
			} else {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "你继续[npc.moaning+]，感受着[npc2.namePos][npc2.cock+]在你[npc.pussy+]里开始不自主地抽动，你想知道[npc2.she]会抽出来，"
							+(Main.game.getPlayer().isVisiblyPregnant()
									?"还是会给你灌成新鲜的泡芙……"
									:"用[npc2.cum+]灌满你的子宫……");

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "你继续[npc.moaning+]，感受着[npc2.namePos][npc2.cock+]在你[npc.asshole+]里开始不自主地抽动，你想知道[npc2.she]会抽出来，还是会给你灌成新鲜的菊穴泡芙……";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "你继续[npc.moaning+]，感受着[npc2.namePos][npc2.cock+]在你[npc.nipple+]里进进出出，你想着[npc2.sheIs]会不会给你灌成新鲜的乳头泡芙……";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "你继续[npc.moaning+]，感受着[npc2.namePos][npc2.cock+]在你[npc.spinneret+]里进进出出，你想着[npc2.sheIs]会不会给你灌成新鲜的纺器泡芙……";
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.TUSSY)) {
					return "You continue [npc.moaning+] as you feel [npc2.namePos] [npc2.cock+] start to twitch inside your [npc.tussy+], and you wonder if [npc2.sheIs] going to pull out or give you a fresh tussy creampie...";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
						return "你继续[npc.moaning+]，感受着[npc2.namePos][npc2.cock+]在你[npc.breasts+]间进进出出，你想着[npc2.sheIs]会不会射满你[npc.breasts+]……";
					} else {
						return "你继续[npc.moaning+]，感受着[npc2.namePos][npc2.cock+]在你躯干上来回磨蹭，你想知道[npc2.she]会退回去，还是会射满你平坦的胸部。";
					}
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "你继续[npc.moaning+]着，给[npc.Name][pc.a_footjob]，你不太确定[npc2.sheIs]是会退缩，还是会射满你的[npc.feet]。";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "你继续[npc.moaning+]着，让[npc.Name]操你[pc.armpit+]，你不太确定[npc2.sheIs]是会退缩，还是会射满你的胸口。";
					
				} else {
					return "你继续[npc.moaning+]，感受着[npc2.namePos][npc2.cock+]抽插，你想着[npc2.sheIs]是会抽出来，还是会用[npc2.cum+]填满你……";
				}
			}
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_DENIAL = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.NEGATIVE_MAJOR,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(isPerformingCharacterTotallyImmobilised()) {
				return false;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotGeneric.MISC_WATCHING
					|| Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction())
					|| Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterTargetedForSexAction(this))) {
				return false;
			}
			if(Main.sex.isDom(Main.sex.getCharacterPerformingAction()) && !Main.sex.isCharacterDeniedOrgasm(Main.sex.getCharacterTargetedForSexAction(this))) {
				if(Main.sex.getCharacterPerformingAction().isPlayer()) {
					return true;
					
				} else {
					return !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this)) // Doms will not deny other doms.
							&& Main.sex.isReadyToOrgasm(Main.sex.getCharacterTargetedForSexAction(this)) // check if really orgasming
							&& Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DENIAL); // Only allow denial fetishists to use this action
				}
			}
			return false;
		}
		
		@Override
		public SexActionPriority getPriority() {
//			if(Main.sex.getCharacterPerformingAction().isPlayer() || !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DENIAL)) {
//				return SexActionPriority.LOW;
//			}
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getActionTitle() {
			return "禁止";
		}

		@Override
		public String getActionDescription() {
			return "你能感觉到[npc2.name]马上就要高潮了。不要让[npc2.herHim]得到高潮。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isCharacterTotallyImmobilised(Main.sex.getCharacterTargetedForSexAction(this))) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"从[npc2.namePos]睡梦中的呜呜声和喘息来看，[npc.name]很清楚[npc2.sheIs]即将在睡梦中达到高潮。",
							"[npc2.namePos]在睡梦中发出淫荡无比的声音，暗示着[npc2.her]马上就要高潮。"));
					
					UtilText.nodeContentSB.append("<br/><br/>");
					
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"听到这些，[npc.name]停止移动，使[npc2.namePos]的刺激戛然而止，以阻止[npc2.herHim]达到[npc2.her]的高潮。",
							"[npc.Name]听到这些，很快停下来，"
									+ "以确保[npc2.nameIsFull]不受任何刺激，防止[npc2.herHim]达到高潮。"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"尽管[npc2.Name]总是一动不动，但[npc.Name]有一种直觉[npc2.she]快要高潮了。",
							"尽管[npc2.Name]保持着完全的静止状态，但[npc.Name]有一种直觉[npc2.she]快要高潮了。"));
					
					UtilText.nodeContentSB.append("<br/><br/>");

					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"于是[npc.name]停了下来，不再给[npc2.namePos]任何刺激，阻止[npc2.herHim]达到高潮。",
							"[npc.she]意识到这些，很快停下来，"
									+ "以确保[npc2.nameIsFull]不受任何刺激，防止[npc2.herHim]达到高潮。"));
				}
				
			} else {
				//TODO fetishes and player-specific descriptions
				
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"从[npc2.namePos]绝望、低沉的呜呜声和喘息来看，[npc.name]很清楚[npc2.sheIs]即将达到高潮。",
									"[npc2.Name]淫荡无比的声音暗示着[npc2.sheIs]马上就要高潮了。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"从[npc2.namePos]兴奋而低沉的[npc2.moans]的来看，[npc.name]很清楚[npc2.sheIs]即将达到高潮。",
									"[npc2.Name]色气无比的[npc2.moan]暗示着[npc2.sheIs]马上就要高潮了。"));
							break;
					}
					
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name]欲望缠身，十分饥渴，大喊着哀求到，[npc2.speech(求求了！让我射出来吧！)]",
									"[npc2.Name]欲望缠身，色气无比，颤抖着淫叫道，[npc2.speech(不要！求求了！让我射出来吧！)]",
									"[npc2.name]欲望缠身，淫荡地叫着，[npc2.speech(别这样！让我射……让我射出来！)]"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name]开始迷乱地喘息[npc2.moaning]，然后脱口而出，[npc2.speech(好棒！你要把我弄去了！)]",
									"[npc2.name]欲望缠身，淫荡地叫着，[npc2.speech(就是这样！让我射……让我射出来！)]",
									"[npc2.name]发出无比色情的[npc2.moan]，惊叫道：[npc2.speech(就是这样！嗯啊~！我……我要去了！)]"));
							break;
					}
				}
				
				UtilText.nodeContentSB.append("<br/><br/>");
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"听到这些，[npc.name]迅速牢牢抓住[npc2.namePos][npc2.arms]，然后将[npc2.herHim]按在原地，阻止[npc2.herHim]刺激[npc2.herself]。",
								"[npc.name]听到了，就迅速地抓住[npc2.namePos]的[npc2.arms]，"
										+"强按住[npc2.herHim]，阻止[npc2.herHim]达到高潮。",
								"[npc.name]听到[npc2.namePos]马上要高潮了，就迅速地抓住[npc2.sheIs]的[npc2.arms]，固定住[npc2.sheIs]不能动弹，以阻止高潮。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"听到这些，[npc.name]迅速用力握住[npc2.namePos][npc2.arms]，然后粗暴地把[npc2.herHim]按在原地，不让[npc2.herHim]刺激[npc2.herself]。",
								"[npc.name]听到了，就粗暴地抓住[npc2.namePos]的[npc2.arms]，"
										+"强迫[npc2.herHim]固定不动，防止[npc2.herHim]达到高潮。",
								"[npc.name]听到[npc2.namePos]马上要高潮了，就粗暴地抓住[npc2.sheIs]的[npc2.arms]，强迫[npc2.sheIs]不能动弹，以阻止高潮。"));
						break;
					default:
						break;
				}
	
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isPlayer()) {
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"被[npc.Name]抓着冷静下来后，你发现自己不会被强制高潮，松了口气，"
											+"继续哭着请求[npc.she]放开你。"));
							
						} else {
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"被[npc.Name]抓着冷静下来后，[npc2.name]发现自己不会被强制高潮，松了口气，"
											+"继续哭着请求[npc.her]放开自己。"));
						}
						break;
					default:
						if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_DENIAL_SELF).isPositive()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"被抓着冷静下来以后，[npc2.name]沉溺于寸止的感觉，发出了淫荡的声音，"
												+ "当寸止带来的兴奋退去以后，色气十足的轻叹很快就变成了欲求不满的[npc2.moan]。"));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"临近高潮时被寸止让[npc2.name]无比沮丧，当[npc2.her]被迫冷静下来时，发出了狂乱的低声啜泣，"
												+ "显而易见，[npc2.she]疯狂地想高潮。"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_DENIAL_SELF).isPositive()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"被抓着冷静下来以后，[npc2.name]沉溺于寸止的感觉，发出了淫荡的声音，"
												+ "[npc2.she]高潮退去，[npc2.moansVerb]，"));
		
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.speech(真棒……感觉太好了……只有得到你允许我才会去，[npc.Name]……)]",
										"[npc2.speech(好的……我是你的，我永远不会违抗你的意愿，[npc.Name]……)]"));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"临近高潮时被寸止让[npc2.name]无比沮丧，当[npc2.her]被迫冷静下来时，发出了狂乱的哭泣声，"));
		
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.speech(不要！我要去了！我要高潮了！)]",
										"[npc2.speech(求求你了！马上！马上就要去了！)]"));
							}
						}
						break;
				}
			}
			
			UtilText.nodeContentSB.append("<p style='text-align:center'>"
						+ "<i>[npc2.NamePos]的高潮被[style.boldBad(禁止了)]！</i>"
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.addCharacterDeniedOrgasm(Main.sex.getCharacterTargetedForSexAction(this));
			
			Main.sex.incrementNumberOfDeniedOrgasms(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), 1);
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			}
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_ENCOURAGE_CREAMPIE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}
		
		private boolean isSelfFucking() {
			return Main.sex.getCharacterTargetedForSexAction(this)==getCharacterBeingFucked();
		}
		
		@Override
		public String getActionTitle() {
			if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return "鼓励操弄";
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
				return "鼓励深喉";

			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), "鼓励射在[npc.breasts]上");
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), "鼓励射在[npc.feet]上");
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), "鼓励射在[npc.armpit]上");
				
			} else {
				return "鼓励内射";
			}
		}

		@Override
		public String getActionDescription() {
			if(isSelfFucking()) {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
					return UtilText.parse(getCharacterBeingFucked(), "你发现[npc.name]就要高潮了。鼓励[npc.her]用假阳具继续干自己干到高潮。");
	
				} else {
					return UtilText.parse(getCharacterBeingFucked(), "你发现[npc.name]就要高潮了。鼓励[npc.her]继续干自己干到高潮。");
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"你能感到[npc2.name]快高潮了。让[npc2.herHim]高潮的时候继续用假阳具操[npc.Name]。");
	
				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"你能感到[npc2.name]快高潮了。让[npc2.herHim]用精液填满[npc.namePos]的肚子吧。");
	
				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"你能感到[npc2.name]快高潮了。让[npc2.herHim]全射在[npc.namePos][npc.breasts+]上吧。");
	
				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"你能感到[npc2.name]快高潮了。让[npc2.herHim]全射在[npc.namePos][npc.feet+]上吧。");
					
				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"你能感到[npc2.name]快高潮了。让[npc2.herHim]全射在[npc.namePos][npc.armpit+]上吧。");
					
				} else {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"你能感到[npc2.name]快高潮了。让[npc2.herHim]用精液填满[npc.Name]吧。");
				}
			}
		}

		@Override
		public SexActionPriority getPriority() {
			OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
			switch(behaviour) {
				case CREAMPIE:
					return SexActionPriority.UNIQUE_MAX;
				case DEFAULT:
				case KNOT:
					break;
				case NO_ENCOURAGE:
				case PULL_OUT:
					return SexActionPriority.LOW;
			}
			return super.getPriority();
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(isPerformingCharacterTotallyImmobilised() || Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			if(getCharacterBeingFucked()!=null
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction())
					&& !isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)) //TODO?
					&& !Collections.disjoint(
							Util.newArrayListOfValues(
									SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.SPINNERET, SexAreaOrifice.TUSSY, SexAreaOrifice.BREAST, SexAreaPenetration.FOOT,
									SexAreaOrifice.ARMPITS, SexAreaOrifice.NIPPLE, SexAreaOrifice.NIPPLE_CROTCH, SexAreaOrifice.URETHRA_PENIS, SexAreaOrifice.URETHRA_VAGINA),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, getCharacterBeingFucked()))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				return behaviour != OrgasmEncourageBehaviour.NO_ENCOURAGE;
			}
			return false;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			String targetHerHim = "[npc3.herHim]";
			String targetHer = "[npc3.her]";
			if(getCharacterBeingFucked().isPlayer()) {
				targetHerHim = "[pc.herHim]"; // Otherwise it gets parsed as 'you'
				targetHer = "[pc.her]"; // Otherwise it gets parsed as 'you'
			}
			
			if(isSelfFucking()) {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
					sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
							+"[npc.name]允许[npc2.herHim]继续干自己干到高潮。");
				
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
						sb.append("[npc.name]顺从欲望，大喊道让[npc2.name]继续干自己干到高潮，"
								+"[npc.speech(别停下来"+(petName?"，[#npc.getPetName(npc2)]":"")+"！继续！)]");
						
					} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						sb.append("[npc.name]顺从欲望，大喊道让[npc2.name]继续干自己干到高潮，"
								+" [npc.speech(别停下来"+(petName?"，[#npc.getPetName(npc2)]":"")+"！射满那个套！)]");
						
					} else {
						sb.append("[npc.Name]顺从欲望，大喊着让[npc2.name]继续操自己操到高潮，");

						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							if(getCharacterBeingFucked().isVisiblyPregnant()) {
								sb.append(" [npc.speech(别拔出来"+(petName?"，[#npc.getPetName(npc2)]":"")+"！用精液填满你的小穴！)]");
							} else {
								sb.append("[npc.speech(别拔出来"+(petName?"，[#npc.getPetName(npc2)]":"")+"！用精液填满你的小穴，把自己干到怀孕！)]");
							}
						} else {
							sb.append(" [npc.speech(别拔出来"+(petName?"，[#npc.getPetName(npc2)]":"")+"！继续！)]");
						}
					}
				}
				
			} else {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
					if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想要[npc2.herHim]一直操[npc3.namePos][npc3.pussy+]，[npc2.name]高潮了。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想要[npc2.herHim]一直操[npc3.namePos][npc3.asshole+]，[npc2.name]高潮了。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想要[npc2.herHim]一直操[npc3.namePos][npc3.nipple+]，[npc2.name]高潮了。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想要[npc2.herHim]一直操[npc3.namePos][npc3.spinneret+]，[npc2.name]高潮了。");
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to keep fucking [npc3.namePos] [npc3.tussy+] as [npc2.she] [npc2.verb(orgasm)].");

						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想要[npc2.herHim]一直操[npc3.namePos][npc3.breasts+]，[npc2.name]高潮了。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想要[npc2.herHim]一直操[npc3.namePos][npc3.feet+]，[npc2.name]高潮了。");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想要[npc2.herHim]一直操[npc3.namePos][npc3.armpit+]，[npc2.name]高潮了。");
							
						} else {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想要[npc2.herHim]一直操[npc3.name]，[npc2.name]高潮了。");
						}
		
					} else {
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想让[npc2.herHim]射在[npc3.namePos][npc3.pussy+]里面。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想让[npc2.herHim]射在[npc3.namePos][npc3.asshole+]里面。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想让[npc2.herHim]射在[npc3.namePos][npc3.nipple+]里面。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想让[npc2.herHim]射在[npc3.namePos][npc3.spinneret+]里面。");
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to cum inside [npc3.namePos] [npc3.tussy+].");

						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想让[npc2.herHim]射得[npc3.namePos][npc3.breasts+]上面到处都是。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想让[npc2.herHim]射得[npc3.namePos][npc3.feet+]上到处都是。");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想让[npc2.herHim]射得[npc3.namePos][npc3.armpit+]上到处都是。");
							
						} else {
							sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
									+ "[npc.she]想让[npc2.herHim]射在[npc3.name]里面。");
						}
					}
					
				} else {
					if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.pussy+]，[npc2.name]高潮了，"
									+"[npc.speech(太棒了！就是这样！一直操下去吧！来操"+targetHerHim+"！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.asshole+]，[npc2.name]高潮了，"
									+"[npc.speech(太棒了！就是这样！一直操下去吧！来操"+targetHer+"的[npc3.ass]！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.nipple+]，[npc2.name]高潮了，"
									+"[npc.speech(太棒了！就是这样！一直操下去吧！来操"+targetHer+"的[npc3.breasts]！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.spinneret+]，[npc2.name]高潮了，"
									+"[npc.speech(太棒了！就是这样！一直操下去吧！来操"+targetHer+"的[npc3.spinneret]！)]");
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY).contains(getCharacterBeingFucked())) {
							sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.tussy+] as [npc2.she] [npc2.verb(orgasm)],"
									+" [npc.speech(Yes! That's right! Don't stop fucking "+targetHer+" [npc3.tussy]!)]");

						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.breasts+]，[npc2.name]高潮了，"
									+"[npc.speech(太棒了！就是这样！一直操下去吧！来操"+targetHer+"的奶子！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.feet+]，[npc2.name]高潮了，"
									+"[npc.speech(太棒了！就是这样！一直操下去吧！来操"+targetHer+"的[npc3.feet]！)]");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.armpit+]，[npc2.name]高潮了，"
									+"[npc.speech(太棒了！就是这样！一直操下去吧！来操"+targetHer+"的胸口！)]");
							
						} else {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos]，[npc2.name]高潮了，"
									+"[npc.speech(太棒了！就是这样！一直操下去吧！来操"+targetHerHim+"！)]");
						}
		
					} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						boolean petName = false;
						if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
							petName = true;
						}
						
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.pussy+]，[npc2.name]高潮了，"
									+"[npc.speech(就在"+targetHerHim+(petName?"里面完事吧，#npc.getPetName(npc2)]":"里面完事吧")+"！要全都射进套套里哦！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.asshole+]，[npc2.name]高潮了，"
									+"[npc.speech(就在"+targetHerHim+(petName?"里面完事吧，#npc.getPetName(npc2)]":"里面完事吧")+"！要全都射进套套里哦！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.nipple+]，[npc2.name]高潮了，"
									+"[npc.speech(就在"+targetHerHim+(petName?"里面完事吧，#npc.getPetName(npc2)]":"里面完事吧")+"！要全都射进套套里哦！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.spinneret+]，[npc2.name]高潮了，"
									+"[npc.speech(就在"+targetHerHim+(petName?"里面完事吧，#npc.getPetName(npc2)]":"里面完事吧")+"！要全都射进套套里哦！)]");
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY).contains(getCharacterBeingFucked())) {
							sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.tussy+] as [npc2.she] [npc2.verb(orgasm)],"
									+" [npc.speech(Finish inside of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Spurt it all out into that condom!)]");

						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.breasts+]，[npc2.name]高潮了，"
									+ "[npc.speech(这才对嘛"+(petName?"，[#npc.getPetName(npc2)]":"")+"！要全都射进套套里哦！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.feet+]，[npc2.name]高潮了，"
									+ "[npc.speech(这才对嘛"+(petName?"，[#npc.getPetName(npc2)]":"")+"！要全都射进套套里哦！)]");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.armpit+]，[npc2.name]高潮了，"
									+ "[npc.speech(这才对嘛"+(petName?"，[#npc.getPetName(npc2)]":"")+"！要全都射进套套里哦！)]");
							
						} else {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos]，[npc2.name]高潮了，"
									+ "[npc.speech(这才对嘛"+(petName?"，[#npc.getPetName(npc2)]":"")+"！要全都射进套套里哦！)]");
						}
						
					} else {
						boolean petName = false;
						if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
							petName = true;
						}
		
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.pussy+]，[npc2.name]高潮了，"
									+(getCharacterBeingFucked().isVisiblyPregnant()
											?"[npc.speech(操啊！射进"+targetHerHim+(petName?"里面，[#npc.getPetName(npc2)]":"里面")+"！用精液灌满"+targetHer+"的小穴哦！)]"
											:"[npc.speech(要给"+targetHerHim+(petName?"播种哦，[#npc.getPetName(npc2)]":"播种哦")+"！用精液填满"+targetHer+"的小穴，顺带锁结"+targetHerHim+"吧！)]"));
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.asshole+]，[npc2.name]高潮了，"
									+"[npc.speech(操啊！射进"+targetHerHim+(petName?"里面，[#npc.getPetName(npc2)]":"里面")+"！用精液灌满"+targetHer+"的屁股哦！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.nipple+]，[npc2.name]高潮了，"
									+"[npc.speech(操啊！射进"+targetHerHim+(petName?"里面，[#npc.getPetName(npc2)]":"里面")+"！用精液灌满"+targetHer+"的乳头哦！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.spinneret+]，[npc2.name]高潮了，"
									+"[npc.speech(操啊！射在"+targetHerHim+(petName?"里面，[#npc.getPetName(npc2)]":"里面")+"！用精液灌满"+targetHer+"的丝囊吧！)]");
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY).contains(getCharacterBeingFucked())) {
							sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] out for [npc2.name] to keep pounding [npc3.namePos] [npc3.tussy+] as [npc2.she] [npc2.verb(orgasm)],"
									+" [npc.speech(Fuck! Cum in "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Fill "+targetHer+" tussy with your cum!)]");

						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.breasts+]，[npc2.name]高潮了，"
									+ "[npc.speech(太棒了！为了"+targetHerHim+(petName?"射精吧，[#npc.getPetName(npc2)]":"射精吧")+"！射满"+targetHer+"的奶子！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.feet+]，[npc2.name]高潮了，"
									+ "[npc.speech(太棒了！为了"+targetHerHim+(petName?"射精吧，[#npc.getPetName(npc2)]":"射精吧")+"！射满"+targetHer+"的[npc3.feet]！)]");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos][npc3.armpit+]，[npc2.name]高潮了，"
									+ "[npc.speech(太棒了！为了"+targetHerHim+(petName?"射精吧，[#npc.getPetName(npc2)]":"射精吧")+"！射满"+targetHer+"的胸口！)]");
							
						} else {
							sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，持续猛撞着[npc3.namePos]，[npc2.name]高潮了，"
									+ "[npc.speech(太棒了！为了"+targetHerHim+(petName?"射精吧，[#npc.getPetName(npc2)]":"射精吧")+"！别拔出去哦！)]");
						}
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+"[npc2.name]听到[npc.Name]的问题，笑了笑，发出愉悦的[npc2.moan]，让[npc.Name]明白，这正中下怀。");
						
					} else {
						sb.append("<br/><br/>"
								+"[npc2.her]听到[npc.Name]这么问，皱起眉头，"
								+ "[npc2.name]发出否定的[npc2.moan]，让[npc.Name]明白，[npc2.name]根本不想听[npc.sheHasFull]说这些。");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"[npc2.name]听到[npc.Name]的问题，笑了笑，迅速积极地回答，",
									"在听清[npc.nameIs]问[npc2.herHim]后发出了[npc.a_moan+]，[npc2.name]回应道，"));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(好吧，如你所愿！)]",
								"[npc2.speech(感觉很适合我呢！)]",
								"[npc2.speech(没问题呀！我会喜欢上这个的！)]"));
						
					} else {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"[npc2.name]听到[npc.Name]问这个，皱起眉头让[npc.herHim]闭嘴，",
									"[npc2.name]很显然不想听[npc.NameIs]问这些关于[npc2.herHim]的事，便回复说，"));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(我会为所欲为哦！)]",
								"[npc2.speech(我才不听你的！)]",
								"[npc2.speech(我会为所欲为哦！)]"));
					}
				}
			}
			
			return UtilText.parse(
					Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked()),
					sb.toString());
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingCreampie().add(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			fetishes.add(Fetish.FETISH_VOYEURIST);
			return fetishes;
		}
	};
	

	public static final SexAction GENERIC_PREPARATION_ENCOURAGE_KNOT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}

		private boolean isSelfFucking() {
			return Main.sex.getCharacterTargetedForSexAction(this)==getCharacterBeingFucked();
		}
		
		@Override
		public String getActionTitle() {
			return "鼓励锁结";
		}

		@Override
		public String getActionDescription() {
			if(isSelfFucking()) {
				return UtilText.parse(getCharacterBeingFucked(), "你意识到[npc.name]快要高潮了。鼓励[npc.herHim]在自己体内成结然后射出来。");
				
			} else {
				if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"你能感到[npc2.name]快高潮了。让[npc2.herHim]在[npc.Name]嘴里锁结，用精液灌满[npc.her]的肚子吧。");
	
				} else {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"你能感到[npc2.name]快高潮了。让[npc2.herHim]在[npc.Name]里面锁结，用精液填满[npc.Name]吧。");
				}
			}
		}

		@Override
		public SexActionPriority getPriority() {
			OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
			switch(behaviour) {
				case KNOT:
					return SexActionPriority.UNIQUE_MAX;
				case DEFAULT:
				case CREAMPIE:
					break;
				case NO_ENCOURAGE:
				case PULL_OUT:
					return SexActionPriority.LOW;
			}
			return super.getPriority();
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(isPerformingCharacterTotallyImmobilised() || Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			if(getCharacterBeingFucked()!=null
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisModifier(PenetrationModifier.KNOTTED)
					&& !isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)) //TODO?
					&& !Collections.disjoint(
							Util.newArrayListOfValues(
									SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.SPINNERET, SexAreaOrifice.TUSSY, SexAreaOrifice.NIPPLE, SexAreaOrifice.NIPPLE_CROTCH, SexAreaOrifice.URETHRA_PENIS, SexAreaOrifice.URETHRA_VAGINA),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, getCharacterBeingFucked()))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				return behaviour != OrgasmEncourageBehaviour.NO_ENCOURAGE;
			}
			return false;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			String targetHerHim = "[npc3.herHim]";
			String targetHer = "[npc3.her]";
			if(getCharacterBeingFucked().isPlayer()) {
				targetHerHim = "[pc.herHim]"; // Otherwise it gets parsed as 'you'
				targetHer = "[pc.her]"; // Otherwise it gets parsed as 'you'
			}

			if(isSelfFucking()) {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
					sb.append("[npc.Name]很清楚自己想要什么，便发出一连串闷哼，努力向[npc2.name]传达着，"
							+"[npc.she]想要[npc2.name]在自己体内成结，然后高潮。");
				
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						sb.append("[npc.name]顺从欲望，大喊着让[npc2.name]在自己体内成结然后高潮，"
								+"[npc.speech(别拔出来"+(petName?"，[#npc.getPetName(npc2)]":"")+"！用精液填满你的小穴，把自己干到怀孕！)]");
						
					} else {
						sb.append("[npc.Name]顺从欲望，大喊着让[npc2.name]高潮时在自己体内成结，");

						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							if(getCharacterBeingFucked().isVisiblyPregnant()) {
								sb.append("[npc.speech(别拔出来"+(petName?"，[#npc.getPetName(npc2)]":"")+"！用结锁住自己然后用精液填满你的小穴！)]");
							} else {
								sb.append("[npc.speech(别拔出来"+(petName?"，[#npc.getPetName(npc2)]":"")+"！用结锁住自己然后把自己干到怀孕！)]");
							}
						} else {
							sb.append("[npc.speech(别拔出来"+(petName?"，[#npc.getPetName(npc2)]":"")+"！把自己干到怀孕！)]");
						}
					}
				}
				
			} else {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
					sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
							+ "[npc.she]想让[npc2.herHim]在[npc3.name]里面锁结，深深射进");
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("[npc3.pussy+]。");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append("[npc3.asshole+]。");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append("[npc3.nipple+]。");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH).contains(getCharacterBeingFucked())) {
						sb.append("[npc3.nippleCrotch+]。");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append("[npc3.spinneret+]。");
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.her] [npc3.tussy+].");

					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS).contains(getCharacterBeingFucked())) {
						sb.append("[npc3.urethraPenis+]。");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA).contains(getCharacterBeingFucked())) {
						sb.append("[npc3.urethraVagina+]。");
	
					} else {
						sb.append("[npc3.herHim]里面。");
					}
					
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						boolean petName = false;
						if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
							petName = true;
						}
	
						sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，让[npc2.her]把锁结塞进");
						
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.pussy+]，深深射进[npc3.herHim]体内，[npc.speech(锁结"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"吧！在"+targetHerHim+"里面射干净，填满避孕套哦！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.asshole+]，深深射进[npc3.herHim]体内，[npc.speech(锁结"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"吧！在"+targetHerHim+"里面射干净，填满避孕套哦！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.nipple+]，深深射进[npc3.herHim]体内，[npc.speech(锁结"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"吧！在"+targetHerHim+"里面射干净，填满避孕套哦！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.nippleCrotch+]，深深射进[npc3.herHim]体内，[npc.speech(锁结"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"吧！在"+targetHerHim+"里面射干净，填满避孕套哦！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.spinneret+]，深深射进[npc3.herHim]体内，[npc.speech(锁结"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"吧！在"+targetHerHim+"里面射干净，填满避孕套哦！)]");
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.tussy+] and cum deep inside of [npc3.herHim], [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and fill up that condom!)]");

						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.urethraPenis+]，深深射进[npc3.herHim]体内，[npc.speech(锁结"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"吧！在"+targetHerHim+"里面射干净，填满避孕套哦！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.urethraVagina+]，深深射进[npc3.herHim]体内，[npc.speech(锁结"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"吧！在"+targetHerHim+"里面射干净，填满避孕套哦！)]");
		
						} else {
							sb.append("[npc3.name]，深深射进[npc3.herHim]体内，[npc.speech(锁结"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"吧！在"+targetHerHim+"里面射干净，填满避孕套哦！)]");
						}
						
					} else {
						boolean petName = false;
						if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
							petName = true;
						}
	
						sb.append("[npc.Name]很清楚自己想要什么，冲[npc2.name]叫嚷着，让[npc2.her]把锁结塞进");
						
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.pussy+]，深深灌注了[npc3.herHim]，"
										+(getCharacterBeingFucked().isVisiblyPregnant()
											?"[npc.speech(锁住"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"！射在"+targetHerHim+"里面，用你的精液装满"+targetHer+"的小穴吧！)]"
											:"[npc.speech(锁结"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"！用你的浓精填塞满"+targetHer+"的小穴，给"+targetHerHim+"播种哦！)]"));
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.asshole+]，深深灌注了[npc3.herHim]，"
										+ "[npc.speech(锁住"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"！射在"+targetHerHim+"里面，用你的精液装满"+targetHer+"的屁股吧！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.nipple+]，深深灌注了[npc3.herHim]，"
										+ "[npc.speech(锁住"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"！射在"+targetHerHim+"里面，用你的精液装满"+targetHer+"的乳头吧！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.nippleCrotch+]，深深灌注了[npc3.herHim]，"
										+ "[npc.speech(锁住"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"！射在"+targetHerHim+"里面，用你的精液装满"+targetHer+"的乳头吧！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.spinneret+]，深深灌注了[npc3.herHim]，"
										+ "[npc.speech(锁住"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"！射在"+targetHerHim+"里面，用你的精液装满"+targetHer+"的丝囊吧！)]");
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.tussy+] and cum deep inside of [npc3.herHim],"
										+ " [npc.speech(Knot "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Finish inside of "+targetHerHim+" and stuff "+targetHer+" tussy with your cum!)]");

						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.urethraPenis+]，深深灌注了[npc3.herHim]，"
										+ "[npc.speech(锁住"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"！射在"+targetHerHim+"里面，用你的精液填满"+targetHer+"的蛋蛋吧！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc3.namePos][npc3.urethraVagina+]，深深灌注了[npc3.herHim]，"
										+ "[npc.speech(锁住"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"！射在"+targetHerHim+"里面，用你的精液装满"+targetHer+"的膀胱吧！)]");
		
						} else {
							sb.append("[npc3.name]，深深灌注了[npc3.herHim]，"
										+ "[npc.speech(锁住"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"！射在"+targetHerHim+"里面，用你的精液塞满"+targetHer+"吧！)]");
						}
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+"[npc2.name]听到[npc.Name]的问题，笑了笑，发出愉悦的[npc2.moan]，让[npc.Name]明白，这正中下怀。");
						
					} else {
						sb.append("<br/><br/>"
								+"[npc2.her]听到[npc.Name]这么问，皱起眉头，"
								+ "[npc2.name]发出否定的[npc2.moan]，让[npc.Name]明白，[npc2.name]根本不想听[npc.sheHasFull]说这些。");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"[npc2.name]听到[npc.Name]的问题，笑了笑，迅速积极地回答，",
									"在听清[npc.nameIs]问[npc2.herHim]后发出了[npc.a_moan+]，[npc2.name]回应道，"));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(好吧，如你所愿！)]",
								"[npc2.speech(感觉很适合我呢！)]",
								"[npc2.speech(没问题呀！我会喜欢上这个的！)]"));
						
					} else {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"[npc2.name]听到[npc.Name]问这个，皱起眉头让[npc.herHim]闭嘴，",
									"[npc2.name]很显然不想听[npc.NameIs]问这些关于[npc2.herHim]的事，便回复说，"));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(我会为所欲为哦！)]",
								"[npc2.speech(我才不听你的！)]",
								"[npc2.speech(我会为所欲为哦！)]"));
					}
				}
			}
			
			return UtilText.parse(
					Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked()),
					sb.toString());
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingKnot().add(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			fetishes.add(Fetish.FETISH_VOYEURIST);
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_ENCOURAGE_PULL_OUT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}

		private boolean isSelfFucking() {
			return Main.sex.getCharacterTargetedForSexAction(this)==getCharacterBeingFucked();
		}
		
		@Override
		public String getActionTitle() {
			return "鼓励拔出去";
		}

		@Override
		public String getActionDescription() {
			if(isSelfFucking()) {
				return UtilText.parse(getCharacterBeingFucked(), "你发现[npc.Name]快要高潮了。鼓励他在高潮时拔出去。");
				
			} else {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"你看出[npc2.name]很快就要高潮了，便让[npc2.herHim]高潮的时候把假阳具从[npc.Name]体内抽出去。");
				}
				return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
						"你看出[npc2.name]很快就要高潮了，便让[npc2.herHim]高潮的时候从[npc.Name]体内抽出去。");
			}
		}

		@Override
		public SexActionPriority getPriority() {
			OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
			switch(behaviour) {
				case DEFAULT:
					break;
				case NO_ENCOURAGE:
				case CREAMPIE:
				case KNOT:
					return SexActionPriority.LOW;
				case PULL_OUT:
					return SexActionPriority.UNIQUE_MAX;
			}
			return super.getPriority();
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(isPerformingCharacterTotallyImmobilised() || Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			if(getCharacterBeingFucked()!=null
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())
					&& !isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& !Collections.disjoint(
							Util.newArrayListOfValues(SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.SPINNERET, SexAreaOrifice.TUSSY, SexAreaOrifice.BREAST, SexAreaPenetration.FOOT, SexAreaOrifice.ARMPITS),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, getCharacterBeingFucked()))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				return behaviour != OrgasmEncourageBehaviour.NO_ENCOURAGE;
			}
			return false;
		}

//		@Override
//		public SexActionPriority getPriority() {
//			if((Main.sex.getAllContactingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
//					&& Main.sex.getCharacterContactingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
//					&& (Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
//						&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo())
//					&& !Main.sex.getCharacterPerformingAction().isVisiblyPregnant())
//				|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()) {
//				return SexActionPriority.HIGH;
//			} else {
//				return SexActionPriority.LOW;
//			}
//		}
		
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			String targetHerHim = "[npc3.herHim]";
			String targetHer = "[npc3.her]";
			String targetSheHas = "[npc3.SheHas]";
			if(getCharacterBeingFucked().isPlayer()) {
				targetHerHim = "[pc.herHim]"; // Otherwise it gets parsed as 'you'
				targetHer = "[pc.her]"; // Otherwise it gets parsed as 'you'
				targetSheHas = "[pc.SheHas]"; // Otherwise it gets parsed as 'you'
			}

			if(isSelfFucking()) {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
					sb.append("Knowing exactly what it is [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.name]"
							+"[npc.name]允许[npc2.herHim]继续干自己干到高潮。");
				
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this)) || Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						sb.append("[npc.Name]不想看[npc2.name]在高潮时继续干自己，大喊着让[npc2.herHim]停下来，"
								+" [npc.speech(拔出去"+(petName?"，[#npc.getPetName(npc2)]":"")+"！)]");
						
					} else {
						sb.append("[npc.Name]不想看[npc2.name]在高潮时继续干自己，大喊着让[npc2.herHim]停下来，");

						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							if(getCharacterBeingFucked().isVisiblyPregnant()) {
								sb.append("[npc.speech(拔出去"+(petName?"，[#npc.getPetName(npc2)]":"")+"！别射你小穴里！)]");
							} else {
								sb.append("[npc.speech(拔出去"+(petName?"，[#npc.getPetName(npc2)]":"")+"！别把自己搞怀孕了！)]");
							}
						} else {
							sb.append("[npc.speech(拔出去"+(petName?"，[#npc.getPetName(npc2)]":"")+"！)]");
						}
					}
				}
				
			} else {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
					if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]操[npc3.namePos][npc3.pussy+]到高潮，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快拔出来。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]操[npc3.namePos][npc3.asshole+]到高潮，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快拔出来。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]操[npc3.namePos][npc3.nipple+]到高潮，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快拔出来。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]操[npc3.namePos][npc3.spinneret+]到高潮，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快拔出来。");
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY).contains(getCharacterBeingFucked())) {
							sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.tussy+] through [npc2.her] orgasm, [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
									+ "赶快拔出来。");

						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]操[npc3.namePos][npc3.breasts+]到高潮，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快拔出来。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]操[npc3.namePos][npc3.feet+]到高潮，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快抽远点。");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]操[npc3.namePos][npc3.armpit+]到高潮，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快抽远点。");
							
						} else {
							sb.append("[npc.Name]不想让[npc2.name]操[npc3.name]到高潮，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快拔出来。");
						}
		
					} else {
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]灌满[npc3.namePos][npc3.pussy+]，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快拔出来。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]灌满[npc3.namePos][npc3.asshole+]，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快拔出来。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]灌满[npc3.namePos][npc3.nipple+]，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快拔出来。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]灌满[npc3.namePos][npc3.spinneret+]，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快拔出来。");
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY).contains(getCharacterBeingFucked())) {
							sb.append("Not wanting [npc2.name] to cum inside [npc3.namePos] [npc3.tussy+], [npc.name] [npc.verb(make)] a series of muffled cries as [npc.she] [npc.verb(try)] to convey to [npc2.herHim]"
									+ "赶快拔出来。");

						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]射得[npc3.namePos][npc3.breasts+]上到处都是，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快拔出来。");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]射得[npc3.namePos][npc3.feet+]上到处都是，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]射得[npc3.namePos][npc3.armpit+]上到处都是，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ "赶快抽远点。");
							
						} else {
							sb.append("[npc.Name]不想让[npc2.name]射得[npc3.name]身上到处都是，便发出一串压抑的呼喊，示意[npc2.herHim]"
									+ " that [npc.she] [npc.verb(want)] [npc2.herHim] to pull out.");
						}
					}
					
				} else {
					if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.name]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech("+targetSheHas+"已经够了！要射的时候从"+targetHer+"小穴里拔出来！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.name]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech("+targetSheHas+"已经够了！要射的时候从"+targetHer+"屁股里拔出来！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.name]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech("+targetSheHas+"已经够了！要射的时候从"+targetHer+"奶头里拔出来！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.name]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech("+targetSheHas+"已经够了！要射的时候从"+targetHer+"丝囊里拔出来！)]");
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.name]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+" [npc.speech("+targetSheHas+" had enough! Pull out of "+targetHer+" tussy when you're going to cum!)]");

						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.name]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech("+targetSheHas+"已经够了！要射的时候从"+targetHer+"奶子上挪开！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.name]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech("+targetSheHas+"已经够了！要射的时候从"+targetHer+"脚上挪开！)]");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.name]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech("+targetSheHas+"已经够了！要射的时候从"+targetHer+"胸口挪开！)]");
							
						} else {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.name]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech("+targetSheHas+"已经够了！要射的时候从"+targetHerHim+"里面拔出来！)]");
						}
		
					} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						boolean petName = false;
						if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
							petName = true;
						}
						
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.namePos][npc3.pussy+]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech(从"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"里面拔出来！我想看你的避孕套胀起来呢！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.namePos][npc3.asshole+]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech(从"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"里面拔出来！我想看你的避孕套胀起来呢！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.namePos][npc3.face+]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech(从"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"里面拔出来！我想看你的避孕套胀起来呢！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.namePos][npc3.nipple+]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech(从"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"里面拔出来！我想看你的避孕套胀起来呢！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.namePos][npc3.spinneret+]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech(从"+targetHerHim+(petName?"，[#npc.getPetName(npc2)]":"")+"里面拔出来！我想看你的避孕套胀起来呢！)]");
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY).contains(getCharacterBeingFucked())) {
							sb.append("Not wanting [npc2.name] to continue fucking [npc3.namePos] [npc3.tussy+] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
									+" [npc.speech(Pull out of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! I want to see your condom inflating!)]");

						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.namePos][npc3.breasts+]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+ "[npc.speech(快"+(petName?"从[#npc.getPetName(npc2)]那":"")+"退出来！我想看着你的避孕套胀起来！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.namePos][npc3.feet+]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+ "[npc.speech(快"+(petName?"从[#npc.getPetName(npc2)]那":"")+"退出来！我想看着你的避孕套胀起来！)]");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]一直操[npc3.namePos][npc3.armpit+]到高潮，便叫嚷着要[npc2.herHim]停下，"
									+ "[npc.speech(快"+(petName?"从[#npc.getPetName(npc2)]那":"")+"退出来！我想看着你的避孕套胀起来！)]");
							
						} else {
							sb.append("Not wanting [npc2.name] to continue fucking [npc3.name] through [npc2.her] orgasm, [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
									+ "[npc.speech(快"+(petName?"从[#npc.getPetName(npc2)]那":"")+"退出来！我想看着你的避孕套胀起来！)]");
						}
						
					} else {
						boolean petName = false;
						if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
							petName = true;
						}
		
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]射在[npc3.name]里面，便叫嚷着要[npc2.herHim]停下，"
									+(getCharacterBeingFucked().isVisiblyPregnant()
											?"[npc.speech(我不想你射在"+targetHerHim+(petName?"里面，[#npc.getPetName(npc2)]":"里面")+"！从"+targetHer+"穴里拔出来！)]"
											:"[npc.speech(我不想让"+targetHerHim+"怀孕"+(petName?"，[#npc.getPetName(npc2)]":"")+"！射之前从"+targetHer+"小穴里拔出来！)]"));
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]射在[npc3.name]里面，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech(我不想你射在"+targetHerHim+(petName?"里面，[#npc.getPetName(npc2)]":"里面")+"！从"+targetHer+"屁眼里拔出来！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]射在[npc3.name]里面，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech(我不想你射在"+targetHerHim+(petName?"里面，[#npc.getPetName(npc2)]":"里面")+"！从"+targetHer+"喉咙里拔出来！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]射在[npc3.name]里面，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech(我不想你射在"+targetHerHim+(petName?"里面，[#npc.getPetName(npc2)]":"里面")+"！从"+targetHer+"乳头里拔出来！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]射在[npc3.name]里面，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech(我不想你射在"+targetHerHim+(petName?"里面，[#npc.getPetName(npc2)]":"里面")+"！从"+targetHer+"丝穴里拔出来！)]");
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.TUSSY).contains(getCharacterBeingFucked())) {
							sb.append("Not wanting [npc2.name] to cum inside of [npc3.name], [npc.name] [npc.verb(cry)] out for [npc2.herHim] to stop,"
									+" [npc.speech(I don't want you cumming inside of "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Pull out of "+targetHer+" tussy!)]");

						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]射在[npc3.name]上面，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech(我不想你射在"+targetHer+"奶子上"+(petName?"，[#npc.getPetName(npc2)]":"")+"！从"+targetHerHim+"身上挪开！)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]射在[npc3.name]上面，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech(我不想你射在"+targetHer+"[npc3.feet]上"+(petName?"，[#npc.getPetName(npc2)]":"")+"！从"+targetHerHim+"身上挪开！)]");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("[npc.Name]不想让[npc2.name]射在[npc3.name]上面，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech(我不想你射在"+targetHer+"胸口上"+(petName?"，[#npc.getPetName(npc2)]":"")+"！从"+targetHerHim+"身上挪开！)]");
							
						} else {
							sb.append("[npc.Name]不想让[npc2.name]射在[npc3.name]上面，便叫嚷着要[npc2.herHim]停下，"
									+"[npc.speech(我不想你射在"+targetHer+"身上"+(petName?"，[#npc.getPetName(npc2)]":"")+"！从"+targetHerHim+"身旁挪开！)]");
						}
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+"[npc2.name]听到[npc.Name]的问题，笑了笑，发出愉悦的[npc2.moan]，让[npc.Name]明白，这正中下怀。");
						
					} else {
						sb.append("<br/><br/>"
								+"[npc2.her]听到[npc.Name]这么问，皱起眉头，"
								+ "[npc2.name]发出否定的[npc2.moan]，让[npc.Name]明白，[npc2.name]根本不想听[npc.sheHasFull]说这些。");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"[npc2.name]听到[npc.Name]的问题，笑了笑，迅速积极地回答，",
									"在听清[npc.nameIs]问[npc2.herHim]后发出了[npc.a_moan+]，[npc2.name]回应道，"));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(好吧，如你所愿！)]",
								"[npc2.speech(感觉很适合我呢！)]",
								"[npc2.speech(没问题呀！我会喜欢上这个的！)]"));
						
					} else {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"[npc2.name]听到[npc.Name]问这个，皱起眉头让[npc.herHim]闭嘴，",
									"[npc2.name]很显然不想听[npc.NameIs]问这些关于[npc2.herHim]的事，便回复说，"));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(我会为所欲为哦！)]",
								"[npc2.speech(我才不听你的！)]",
								"[npc2.speech(我会为所欲为哦！)]"));
					}
				}
			}
			
			return UtilText.parse(
					Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked()),
					sb.toString());
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), null);
		}
	};
	
	
	// PARTNER
	
	// Doesn't have penis (or penis is not exposed), and isn't being vaginally penetrated:
	public static final SexAction PARTNER_GENERIC_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "高潮";
		}
		@Override
		public String getActionDescription() {
			return "你已经到达了快感的极限，再无法阻止高潮的到来。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW;
		}
		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), false).getDescription();
		}
		@Override
		public void applyEffects() {
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), true).applyEffects();
			if (Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					&& !Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.sex.getCharacterPerformingAction().isWearingCondom()
					&& Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE) {
				Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(Main.sex.getCharacterPerformingAction(), true);
			}
		}
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_DENIED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.NEGATIVE_MAJOR,
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
			return Main.sex.isCharacterDeniedOrgasm(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "拒绝！";
		}
		@Override
		public String getActionDescription() {
			return "你在最后一刻被拒绝了！";
		}
		@Override
		public String getDescription() {
			if(isPerformingCharacterTotallyImmobilised()) {
				if(Main.sex.getCharacterPerformingAction().isAsleep()) {
					return "[npc.Name]在睡梦中不舒服地动了动，但除此之外，[npc.she]对于自己被拒绝高潮就没有什么别的反应了。";
				} else {
					return "[npc.Name]保持着完全的静止，对于自己被拒绝高潮没有任何反应。";
				}
			}
			
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom("[npc.Name]发出苦恼的闷哭声，显然心情很差。");
					default:
						if(Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DENIAL_SELF)) {
							return UtilText.returnStringAtRandom("[npc.Name]色气无比的声音，表现出[npc.she]十分享受寸止的感觉。");
						} else {
							return UtilText.returnStringAtRandom("[npc.Name]发出沮丧的闷哭声，显然狂乱地想要被允许高潮。");
						}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom("[npc.speech(你可爽了！就把我扔一边不管了！)][npc.name]尖叫着，[npc.speech(别，别再让我这样了！)]");
					default:
						if(Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DENIAL_SELF)) {
							return UtilText.returnStringAtRandom(
									"[npc.speech(就差一点！)][npc.name]哀鸣道。"
									+ "[npc.her]色情的语气让人明白，[npc.sheIs]其实很享受被拒绝的感觉，只是在扮演[npc.she]的角色，嬉皮笑脸地提出抗议，"
									+ "[npc.speech(下次，下次要去！)]");
						} else {
							return UtilText.returnStringAtRandom("[npc.speech(明明！明明就差一点！)][npc.name]挣扎大哭，[npc.speech(下次，下次要去！)]");
						}
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterDeniedOrgasm(Main.sex.getCharacterPerformingAction());
			
			SexFlags.playerPreparedForCharactersOrgasm.remove(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			}
		}
	};

}
